package ru.ifmo.cs.bcomp.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class ButtonBorder implements Border
{
	private Color borderColor;
	private Insets insets;
	
	public ButtonBorder(Color borderColor)
	{
		this(borderColor, 5, 5, 5, 5);
	}
	
	public ButtonBorder(Color borderColor, int top, int left, int bottom, int right)
	{
		this.borderColor = borderColor != null ? borderColor : Color.WHITE;
		this.insets = new Insets(top, left, bottom, right);
	}
	
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
	{
		g.setColor(borderColor);
		g.drawRect(x, y, width, height);
	}
	
	public boolean isBorderOpaque() 
	{
		return true;
	}
	
	public Insets getBorderInsets(Component c) 
	{
		return insets;
	}
	
	public static ButtonBorder getBorder(Color borderColor)
	{
		return new ButtonBorder(borderColor);
	}
	
	public static ButtonBorder getBorder(Color borderColor, int top, int left, int bottom, int right)
	{
		return new ButtonBorder(borderColor, top, left, bottom, right);
	}
}
