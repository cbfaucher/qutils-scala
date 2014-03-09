/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.events;

import com.quartz.qutilities.logging.ILog;
import com.quartz.qutilities.logging.LogManager;
import com.quartz.qutilities.util.MessageHandler;
import org.apache.commons.lang.ClassUtils;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.EventObject;

/**
 * An event manager based on annotations to deleguate event handling.
 *
 * @author Christian
 * @since Quartz...
 */
public class QEventManager 
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    static private final ILog LOG = LogManager.getLogger(QEventManager.class);

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private MessageHandler defaultMessageHandler = null;
    private QEventManagerHandlerResolver handlerResolver = null;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public QEventManager()
    {
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public void handleEvent(JFrame pParent, EventObject pEventObject)
    {
        handleEvent(pParent, pEventObject, null);
    }

    public MessageHandler getDefaultMessageHandler()
    {
        return defaultMessageHandler;
    }

    public void setDefaultMessageHandler(MessageHandler pDefaultMessageHandler)
    {
        defaultMessageHandler = pDefaultMessageHandler;
    }

    public QEventManagerHandlerResolver getHandlerResolver()
    {
        return handlerResolver;
    }

    public void setHandlerResolver(QEventManagerHandlerResolver pHandlerResolver)
    {
        handlerResolver = pHandlerResolver;
    }

    public void handleEvent(final JFrame pParent, EventObject pEventObject, String pCommand)
    {
        try
        {
            final QActionEventHandlerClassDecl actionEventHandlerClass = pEventObject.getSource().getClass().getAnnotation(QActionEventHandlerClassDecl.class);
            if (actionEventHandlerClass != null)
            {

                final Class aClass = actionEventHandlerClass.handlerClass();
                final QEventHandler handler = (QEventHandler) aClass.getConstructor(new Class[0]).newInstance(null);
                handleEvent(pParent, handler, pEventObject, (pCommand != null ? pCommand : actionEventHandlerClass.actionName()));
                return;
            }

            String command = pCommand;

            if (command == null)
            {
                final QActionEventHandlerNameDecl actionEventHandlerNameDecl = pEventObject.getSource().getClass().getAnnotation(QActionEventHandlerNameDecl.class);
                if (actionEventHandlerNameDecl == null)
                {
                    LOG.error("Event source does not declare @" + ClassUtils.getShortClassName(QActionEventHandlerNameDecl.class));
                    return;
                }
                command = actionEventHandlerNameDecl.handlerName();
            }

            final QEventHandler handler = handlerResolver.resolve(command);

            if (handler == null)
            {
                LOG.error("Unknown command: " + pCommand + ".");
                return;
            }

            handleEvent(pParent, handler, pEventObject, pCommand);

        }
        catch (InstantiationException e)
        {
            LOG.error("Could not handle event.", e);
        }
        catch (IllegalAccessException e)
        {
            LOG.error("Could not handle event.", e);
        }
        catch (InvocationTargetException e)
        {
            LOG.error("Could not handle event.", e);
        }
        catch (NoSuchMethodException e)
        {
            LOG.error("Could not handle event.", e);
        }
        catch (QEventManagerException e)
        {
            LOG.error("Could not handle event.", e);
        }

    }

    protected void handleEvent(final JFrame pParent, final QEventHandler pHandler, EventObject pEventObject, String pCommand)
    {
        if (pHandler instanceof JFrameAware)
        {
            final JFrameAware frameAware = (JFrameAware) pHandler;
            if (pParent != null)
            {
                frameAware.setFrame(pParent);
            }
            else
            {
                System.err.println("WARNING!  No Owning Frame set!  Cannot comply with JFrameAware!");
            }
        }

        pHandler.handleEvent(this, pEventObject, pCommand);
    }

    ///////////////////////////////////////
    ////    INNER CLASSES

}
