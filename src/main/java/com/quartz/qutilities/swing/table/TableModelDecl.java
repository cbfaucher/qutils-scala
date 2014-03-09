package com.quartz.qutilities.swing.table;

import javax.swing.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Comparator;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface TableModelDecl
{
    static final public class NullPopupMenu extends JPopupMenu{};

    Class<? extends Comparator> defaultComparatorClass();
    Class<? extends JPopupMenu> popupMenuClass() default NullPopupMenu.class;
}
