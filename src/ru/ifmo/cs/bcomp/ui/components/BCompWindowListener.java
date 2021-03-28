package ru.ifmo.cs.bcomp.ui.components;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class BCompWindowListener implements ComponentListener
{
	public void componentResized(ComponentEvent e) 
	{
		Component comp = e.getComponent();
		
		if (comp instanceof Window)
		{
			((Window) comp).setLocationRelativeTo(null);
		}
	}

	public void componentMoved(ComponentEvent e) {}

	public void componentShown(ComponentEvent e) {}

	public void componentHidden(ComponentEvent e) {}
	
	public static BCompWindowListener getWindowListener()
	{
		return new BCompWindowListener();
	}
}
