package ru.ifmo.cs.bcomp.ui.components;

import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ButtonChangeListener implements ChangeListener
{
	public static final Color BUTTON_PRESSED = DisplayStyles.COLOR_TITLE.brighter().brighter();
	
	public void stateChanged(ChangeEvent e) 
	{
		if (e.getSource() instanceof AbstractButton)
		{
			AbstractButton abstractButton = (AbstractButton) e.getSource();
			
			if (abstractButton.getModel().isPressed())
				abstractButton.setBackground(BUTTON_PRESSED);
			else
				abstractButton.setBackground(DisplayStyles.COLOR_VALUE);
		}
	}
	
	public static ButtonChangeListener getChangeListener()
	{
		return new ButtonChangeListener();
	}
}
