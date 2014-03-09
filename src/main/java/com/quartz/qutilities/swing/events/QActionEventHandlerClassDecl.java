/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.events;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
@Retention (RetentionPolicy.RUNTIME)
public @interface QActionEventHandlerClassDecl
{
    Class handlerClass();
    String actionName() default "";
}
