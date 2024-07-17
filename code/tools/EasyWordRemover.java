// Part of: "Karma Literature Review", Copyrights Kevin Riehl 2024, <kriehl@ethz.ch>
package tools;

import java.util.LinkedList;

public class EasyWordRemover 
{
//	Attributes
	private final static String WORD_LIST_PATH = "resources/stopWordList/StopWordList.txt";
	private static LinkedList<String> words;
	
//	Constructor
	public EasyWordRemover()
	{
		words = new LinkedList<String>();
		String[] ws = {};
		try 
		{
			ws = Tool.load_file(WORD_LIST_PATH).split("\n");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		for(int i = 0;i<ws.length;i++)
		{
			words.add(ws[i].toLowerCase());
		}
	}
	
//	Methods
	public String deleteEasyWords(String text)
	{
		text = text.toLowerCase();
		for(int i = 0;i<words.size();i++)
		{
			text = text.replace(" "+words.get(i)+" ", " ");
		}
		return text;
	}
}
