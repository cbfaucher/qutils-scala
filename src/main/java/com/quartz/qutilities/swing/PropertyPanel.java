package com.quartz.qutilities.swing;

import javax.swing.*;
import java.awt.*;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class PropertyPanel extends JPanel
{

	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	final private GridBagLayout layout;

	///////////////////////////////////////
	////    CONSTRUCTORS

	public PropertyPanel()
	{
		layout = new GridBagLayout();
		this.setLayout(layout);
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	public void addProperty(JComponent pLeft, JComponent pRight)
	{
		//  left
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		this.layout.setConstraints(pLeft, gbc);
        this.add(pLeft);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		this.layout.setConstraints(pRight, gbc);
		this.add(pRight);

//        GridBagConstraints gbc = new GridBagConstraints(
//                0, GridBagConstraints.RELATIVE,
//                2, GridBagConstraints.RELATIVE,
//                0, 0,
//                GridBagConstraints.EAST,
//                GridBagConstraints.NONE,
//                new Insets(2, 2, 2, 2),
//                3, 3);
//		this.layout.setConstraints(pLeft, gbc);
//        this.add(pLeft);
//
//		//  right
//		gbc.gridx = 1;  //  next
//		gbc.weightx = 1.0;
//		gbc.weighty = 1.0;
//		gbc.anchor = GridBagConstraints.WEST;
//		gbc.fill = GridBagConstraints.BOTH;
//        this.layout.setConstraints(pRight, gbc);
//		this.add(pRight);
	}

	///////////////////////////////////////
	////    INNER CLASSES
}
