// Part of: "Karma Literature Review", Copyrights Kevin Riehl 2024, <kriehl@ethz.ch>
package tools;

import java.util.HashSet;
import java.util.Set;

public class TextTool 
{
	public static String removeNewLinesTabsDoubleSpace(String txt)
	{
		String text = txt.replace("\n", " ");
		text = text.replace("\t", " ");
		while(text.contains("  "))
			text = text.replace("  ", " ");
		return text;
	}
	public static String removeReferences(String txt)
	{
		String[] lines = txt.split("\n");
		String text = lines[0];
		for(int i = 1;i<lines.length;i++)
		{
			if(i>=0.6*lines.length)
			{
				if(lines[i].toLowerCase().equals("references"))
				{
					break;
				}
				else
				{
					text = text + "\n" + lines[i];
				}
			}
			else
			{
				text = text + "\n" + lines[i];
			}
		}
		return text;
	}
	public static String plainTEXTfilter(String txt)
	{
		txt = txt.replace(".", " ");
		txt = txt.replace(",", " ");
		txt = txt.replaceAll("\\?", " ");
		txt = txt.replace("!", " ");
		txt = txt.replace(";", " ");
		txt = txt.replace(":", " ");
		txt = txt.replace("(", " ");
		txt = txt.replace(")", " ");
		txt = txt.replace("@", " ");
		txt = txt.replace("/", " ");
		txt = txt.replace("\\", " ");
		txt = txt.replace("#", " ");
		txt = txt.replace("&", " ");
		txt = txt.replace("{", " ");
		txt = txt.replace("$", " ");
		txt = txt.replace("�", " ");
		txt = txt.replace("�", " ");
		txt = txt.replace("�", " ");
		txt = txt.replace("�", " ");
		txt = txt.replace("^", " ");
		txt = txt.replace("|", " ");
		txt = txt.replace("}", " ");
		txt = txt.replace("=", " ");
		txt = txt.replace("[", " ");
		txt = txt.replace("]", " ");
		txt = txt.replace("%", " ");
		txt = txt.replace("<", " ");
		txt = txt.replace(">", " ");
		txt = txt.replace("�", "");
		txt = txt.replace("�", "");
		txt = txt.replace("'", "");
		txt = txt.replace("`", "");
		txt = txt.replace("�", "");
		txt = txt.replace("_", " ");
		txt = txt.replace("�", " ");
		txt = txt.replace("-", " ");
		txt = txt.replace("�", " ");
		txt = txt.replace("�", " ");
		txt = txt.replace("*", " ");
		txt = txt.replace("+", " ");
		txt = txt.replace("�", " ");
		txt = txt.replace("�", " ");	
		txt = txt.replaceAll("[0-9]","");	// remove all numbers
		return txt;
	}
	public static int countUniqueWords(String txt)
	{
		txt = plainTEXTfilter(txt);
		txt = txt.replaceAll("\n", "");
		String[] words = txt.split(" ");
		int counter = 1;
		Set<String> set = new HashSet<String>();
		for(int i = 1;i<words.length;i++)
		{
			if (set.add(words[i])) 
			{
				counter++;
			}
		}
		return counter;
	}
	public static String removeDuplicatLines(String txt)
	{
		String[] lines = txt.split("\n");
		String text = lines[0];
		Set<String> set = new HashSet<String>();
		for(int i = 1;i<lines.length;i++)
		{
			if (set.add(lines[i])) 
			{
				text = text + "\n" + lines[i];
			}
		}
		return text;
	}
}
