package com.quartz.qutilities.swing;

import javax.swing.*;
import java.awt.event.ItemListener;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class QComboBox extends JComboBox<Object>
{
    public QComboBox()
    {
    }

    public void setItems(Object ... pItems)
    {
        this.removeAllItems();
        for (Object o : pItems) addItem(o);
    }

    public void setItemListeners(ItemListener ... pListeners)
    {
        for (ItemListener il : getItemListeners()) removeItemListener(il);

        for (ItemListener il : pListeners) addItemListener(il);
    }


}
