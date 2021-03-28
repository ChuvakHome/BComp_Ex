package ru.ifmo.cs.bcomp.translator;

import java.awt.Font;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Translator 
{
	private static Map<String, Integer> addressCommands = new HashMap<>();
	private static Map<String, Integer> unaddressCommands = new HashMap<>();
	private static Map<String, Integer> ioCommands = new HashMap<>();
	
	static
	{
		unaddressCommands.put("NOP", 0x0000);
		unaddressCommands.put("HLT", 0x0100);
		
		unaddressCommands.put("CLA", 0x0200);
		unaddressCommands.put("NOT", 0x0280);
		
		unaddressCommands.put("CLC", 0x0300);
		unaddressCommands.put("CMC", 0x0380);
		
		unaddressCommands.put("ROL", 0x0400);
		unaddressCommands.put("ROR", 0x0480);
		
		unaddressCommands.put("ASL", 0x0500);
		unaddressCommands.put("ASR", 0x0580);
		
		unaddressCommands.put("SXTB", 0x0600);
		unaddressCommands.put("SWAB", 0x0680);
		
		unaddressCommands.put("INC", 0x0700);
		unaddressCommands.put("DEC", 0x0740);
		unaddressCommands.put("NEG", 0x0780);
		
		unaddressCommands.put("POP", 0x0800);
		
		unaddressCommands.put("POPF", 0x0900);
		
		unaddressCommands.put("RET", 0x0A00);
		
		unaddressCommands.put("IRET", 0x0B00);
		
		unaddressCommands.put("PUSH", 0x0C00);
		
		unaddressCommands.put("PUSHD", 0x0D00);
		
		unaddressCommands.put("SWAP", 0x0E00);

		addressCommands.put("AND", 0x2);
		addressCommands.put("OR", 0x3);
		addressCommands.put("ADD", 0x4);
		addressCommands.put("ADC", 0x5);
		addressCommands.put("SUB", 0x6);
		addressCommands.put("CMP", 0x7);
		addressCommands.put("LOOP", 0x8);
		addressCommands.put("LD", 0xA);
		addressCommands.put("SWAM", 0xB);
		addressCommands.put("JUMP", 0xC);
		addressCommands.put("CALL", 0xD);
		addressCommands.put("ST", 0xE);
		
		addressCommands.put("BEQ", 0xF0);
		addressCommands.put("BNE", 0xF1);
		addressCommands.put("BMI", 0xF2);
		addressCommands.put("BPL", 0xF3);
		
		addressCommands.put("BLO", 0xF4);
		addressCommands.put("BCS", 0xF4);
		
		addressCommands.put("BHIS", 0xF5);
		addressCommands.put("BCC", 0xF5);
		
		addressCommands.put("BVS", 0xF6);
		addressCommands.put("BVC", 0xF7);
		addressCommands.put("BLT", 0xF8);
		addressCommands.put("BGE", 0xF9);
		
		addressCommands.put("BR", 0xCE);
		
		ioCommands.put("DI", 0x1000);
		ioCommands.put("EI", 0x1100);
		ioCommands.put("IN", 0x1200);
		ioCommands.put("OUT", 0x1300);
		ioCommands.put("INT", 0x1800);
		ioCommands.put("IRET", 0x0B00);
	}
	
	public static void translate(File scriptFile)
	{
		if (scriptFile != null && scriptFile.exists())
		{
			try
			{
				Scanner scn = new Scanner(scriptFile);
				
				while (scn.hasNextLine())
					System.out.println(translate(scn.nextLine()));
				
				scn.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	public static void translate(File scriptFile, PrintStream printStream)
	{
		if (scriptFile != null && scriptFile.exists())
		{
			try
			{
				Scanner scn = new Scanner(scriptFile);
				
				String line;
				
				while (scn.hasNextLine())
				{
					line = translate(scn.nextLine());
					
					printStream.println(line);
					System.out.println(line);
				}
				
				scn.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	public static String translate(String line)
	{
		if (line != null)
		{
			String[] parts = line.split("\\s");
			String result = "";
			
			if (parts.length <= 2)
			{
				String command = parts[0];
				String cell = "";
				
				if (parts.length == 2)
				{
					cell = parts[1];
					
					if (cell.startsWith("#"))
					{
						cell = cell.substring(1);
						
						if (cell.indexOf('_') > 0)
						{
							parts = cell.split("_");
							
							if (parts.length == 2)
								cell = hex(Integer.parseInt(parts[0], Integer.parseInt(parts[1])));
						}
						
						cell = "F" + normalize(cell, 2);
					}
					else
						cell = normalize(cell, 3);
				}
				
				if (unaddressCommands.containsKey(command))
					result = hex(unaddressCommands.get(command));
				else if (addressCommands.containsKey(command))
					result = hex(addressCommands.get(command)) + cell;
				else if (ioCommands.containsKey(command))
					result = hex(ioCommands.get(command));
				else
					return line;
				
				while (result.length() < 4)
					result = "0" + result;
				
				return result;
			}
		}
		
		return line;
	}
	
	private static String hex(int i)
	{
		return Integer.toHexString(i).toUpperCase();
	}
	
	private static String normalize(String s, int len)
	{
		while (s.length() < len)
			s = "0" + s;
		
		return s;
	}
}
