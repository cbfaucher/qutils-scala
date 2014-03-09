package com.quartz.qutilities.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A bunch of utilities methods for Swing stuff.
 *
 * @author fauchc02
 * @since 2005-01-17 15:05:30
 */
final public class SwingUtils {

    static private Font defaultFont = new Font("Arial", Font.PLAIN, 12);

    static private Font defaultMonospacedFont = new Font("Courier New", Font.PLAIN, 12);

    /**
     * centers the frame in the center of the screen
     *
     * @param pFrame Frame to center
     */
    static public void centerWindow(Window pFrame) {
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        center.x = center.x - (int) pFrame.getSize().getWidth()/2;
        center.y = center.y - (int) pFrame.getSize().getHeight()/2;
        pFrame.setLocation(center);
    }

    static public JMenu createJMenu(JMenuBar pMenuBar, String pMenuText, char pMnemonic)
    {
        final JMenu menu = new JMenu(pMenuText);
        menu.setMnemonic(pMnemonic);

        if (defaultFont != null) menu.setFont(defaultFont);

        pMenuBar.add(menu);
        return menu;
    }

    static public JMenuItem createJMenuItem(JMenu pMenu, String pText, char pMnemonic, KeyStroke pAccelerator, ActionListener pActionListener)
    throws IllegalArgumentException
    {
        if (pMenu == null) throw new IllegalArgumentException("Menu is missing.");
        if (pText == null) throw new IllegalArgumentException("Missing text for menu item.");

        final JMenuItem menuItem = new JMenuItem(pText, pMnemonic);

        if (pAccelerator != null) menuItem.setAccelerator(pAccelerator);
        if (pActionListener != null) menuItem.addActionListener(pActionListener);
        if (defaultFont != null) menuItem.setFont(defaultFont);

        pMenu.add(menuItem);

        return menuItem;
    }

    public static Font getDefaultFont()
    {
        return defaultFont;
    }

    public static void setDefaultFont(Font pDefaultFont)
    {
        defaultFont = pDefaultFont;
    }

    public static Font getDefaultMonospacedFont()
    {
        return defaultMonospacedFont;
    }

    public static void setDefaultMonospacedFont(Font pDefaultMonospacedFont)
    {
        defaultMonospacedFont = pDefaultMonospacedFont;
    }

    public static void invokeLaterIfNeeded(Runnable pRunnable)
    {
        if (SwingUtilities.isEventDispatchThread())
        {
            pRunnable.run();
        }
        else
        {
            SwingUtilities.invokeLater(pRunnable);
        }
    }

    public static void resizeWindow(Window pWindow, float hRatio, float vRatio)
    {
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        pWindow.setSize((int) (dim.getWidth() * hRatio), (int) (dim.getHeight() * vRatio));
    }
}
