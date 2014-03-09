package com.quartz.qutilities.events;

import org.apache.commons.lang.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class InstanceOfEventFilter implements IEventFilter
{
    final private List<Class> classes = new ArrayList<Class>();

    public InstanceOfEventFilter(Class ... pClasses)
    {
        setAcceptedClasses(pClasses);
    }

    public void setAcceptedClasses(Class... pClasses)
    {
        classes.clear();

        for (Class c : pClasses) classes.add(c);

        if (classes.isEmpty()) classes.add(Object.class);
    }

    public boolean accepts(QEvent pEvent)
    {
        for (Class c : classes)
        {
            if (ClassUtils.isAssignable(pEvent.getClass(), c)) return true;
        }

        return false;
    }
}
