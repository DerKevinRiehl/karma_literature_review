// Part of: "Karma Literature Review", Copyrights Kevin Riehl 2024, <kriehl@ethz.ch>
package tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tool 
{
	public static String getTime()
	{
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
	}
	public static void saveMem(String file, int nr)
	{
		Tool.saveToTXT("mem/"+file, ""+nr+"\n"+getTime());
	}
	public static int getMem(String file) throws NumberFormatException, Exception
	{
		if(new File("mem/"+file).exists())
		{
			String s = Tool.load_file("mem/"+file).split("\n")[0];
			return Integer.parseInt(s);
		}
		else
		{
			Tool.saveToTXT(file, "0\n"+getTime());
			return 0;
		}
	}
	public static void saveToTXT(String file, String txt)
	{
		try
		{
			PrintWriter pw = new PrintWriter(new FileWriter(new File(file)));
			pw.print(txt);
			pw.close();
		}
		catch(Exception e){e.printStackTrace();}
	}
	public static String remSpaceFront(String str)
	{
		while(str.startsWith(" "))
		{
			str = str.substring(1);
		}
		return str;
	}
	public static String remSpaceEnd(String str)
	{
		while(str.endsWith(" "))
		{
			str = str.substring(0,str.length()-1);
		}
		return str;
	}
	public static int countStr(String str, String findStr)
	{
		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1){

		    lastIndex = str.indexOf(findStr,lastIndex);

		    if(lastIndex != -1){
		        count ++;
		        lastIndex += findStr.length();
		    }
		}
		return count;
	}
	public static String cutFront(String txt, String a)
	{
		return txt.substring(txt.indexOf(a)+a.length());
	}
	public static String cutEnd(String txt, String a)
	{
		return txt.substring(0,txt.indexOf(a));
	}
	public static final String getHTML_Code_Cookie(String url)
	{
		String pageText = "";
		try
		{
			HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("GET");
			con.addRequestProperty("Cookie","registration=Cooki_Value_Here;profile-data=Cookie_Value_Here");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
			String inputLine;
			while ((inputLine = in.readLine()) != null) 
				pageText = pageText + inputLine+"\n";
			in.close();
		} 
		catch (Exception e) 
		{
			System.out.println(">> Fehler in \"Tool.getHTML_Code()\"");
			e.printStackTrace();
			return "-1";
		}

		pageText = pageText.replace("ï¿½?", "-");
		pageText = pageText.replace("â€�", "-");
		return pageText;
	}

	public static final String getHTML_Code_Google(String url)
	{
		String pageText = "";
		try
		{
			HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("GET");
			con.addRequestProperty("Cookie","registration=Cooki_Value_Here;profile-data=Cookie_Value_Here");
			con.addRequestProperty("User-Agent", "Mozilla");
			con.addRequestProperty("Accept", "text/html,text/plain");
			con.addRequestProperty("Accept-Language", "en-us,en");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
			String inputLine;
			while ((inputLine = in.readLine()) != null) 
				pageText = pageText + inputLine+"\n";
			in.close();
		} 
		catch (Exception e) 
		{
			System.out.println(">> Fehler in \"Tool.getHTML_Code()\"");
			e.printStackTrace();
			System.exit(0);
		}

		pageText = pageText.replace("ï¿½?", "-");
		pageText = pageText.replace("â€�", "-");
		return pageText;
	}
	public static final String getHTML_Code(String url)
	{
		String pageText = "";
		
		String line = "";
		
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));

			while ((line = br.readLine()) != null)
			{
				pageText += line+"\n";
			}
			
			br.close();
		}
		catch (Exception e)
		{
			System.out.println(">> Fehler in \"Tool.getHTML_Code()\"");
			e.printStackTrace();
			System.exit(0);;
		}
		
		return pageText;
	}
	public static String two_digit(int n)
	{
		if(n<10)
			return "0"+n;
		else
			return ""+n;
	}
	public static String three_digit(int n)
	{
		if(n<10)
			return "00"+n;
		else if(n<100)
			return "0"+n;
		else
			return ""+n;
	}
	public static String four_digit(int n)
	{
		if(n<10)
			return "000"+n;
		else if(n<100)
			return "00"+n;
		else if(n<1000)
			return "0"+n;
		else
			return ""+n;
	}
	public static String five_digit(int n)
	{
		if(n<10)
			return "0000"+n;
		else if(n<100)
			return "000"+n;
		else if(n<1000)
			return "00"+n;
		else if(n<10000)
			return "0"+n;
		else
			return ""+n;
	}
	public static String two_comma(double d)
	{
		NumberFormat formatter = new DecimalFormat("#00.00");     
		return ""+(formatter.format(d));	
	}
	public static String load_file(String file) throws Exception
	{
		String content = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		String line = br.readLine();
		while(line!=null)
		{
			content = content+line+"\n";
			line = br.readLine();
		}
		br.close();
		return content;
	}
	public static boolean onlyNumbers(String s)
	{
		try{Integer.parseInt(s);return true;}catch(Exception e){return false;}
	}
	public static String correct_html_chars(String s)
	{
		String text = s;
		
		text = text.replace("<sub>", "");
		text = text.replace("</sub>", "");
		text = text.replace("<em>", "");
		text = text.replace("</em>", "");

		text = text.replace("&mdash;", " Ã¢â‚¬ï¿½? ");
		text = text.replace("&rsquo;", " Ã¢â‚¬â„¢ ");
		text = text.replace("&lsquo;", " Ã¢â‚¬Ëœ ");
		text = text.replace("&rdquo;", " Ã¢â‚¬? ");
		text = text.replace("&ldquo;", " Ã¢â‚¬Å“ "); 
		
		text = text.replace("&amp;", "&");
		text = text.replace("&amp:", "&");
		
		text = text.replace("ÃƒÆ’Ã…â€œ", "ÃƒÅ“");
		text = text.replace("ÃƒÆ’Ã‚Â¡", "ÃƒÂ¡");
		text = text.replace("Ãƒâ€¦Ã‚Âª", "Ã…Âª");
		text = text.replace("ÃƒÆ’Ã‚Â¤", "ÃƒÂ¤");
		text = text.replace("ÃƒÂ¢Ã¢â‚¬Å¡Ã‚Â¬", "Ã¢â€šÂ¬");
		text = text.replace("ÃƒÂ¢Ã¢â€šÂ¬Ã¢â€žÂ¢", "Ã¢â‚¬â„¢");
		text = text.replace("ÃƒÆ’Ã‚Â©", "ÃƒÂ©");
		text = text.replace("Ãƒâ€¦Ã‚Â ", "Ã…Â ");
		text = text.replace("ÃƒÆ’Ã‚Â¶", "ÃƒÂ¶");
		text = text.replace("ÃƒÆ’Ã‚Â£", "ÃƒÂ£");
		text = text.replace("ÃƒÂ¢Ã¢â€šÂ¬?", "Ã¢â‚¬?");
		text = text.replace("ÃƒÂ¢Ã¢â€šÂ¬Ã…â€œ", "Ã¢â‚¬Å“");
		text = text.replace("ÃƒÆ’Ã‚Â«", "ÃƒÂ«");
		text = text.replace("ÃƒÆ’Ã‚Â¼", "ÃƒÂ¼");

		text = text.replace(";", " ");

		return text;
	}
	public static String get_article_text_code(int number) throws Exception
	{		
		int actual_decade = (number-number%100)/100;
		String subfolder = "folder_"+Tool.three_digit(actual_decade)+"/";
		
		String page_code = load_file("Articles_html/"+subfolder+Tool.five_digit(number)+".html");
		page_code = correct_html_chars(page_code);
		int begin 	= page_code.indexOf("</h2>")+5;
		int end 	= page_code.indexOf("</div>\n<p class=\"posted\">",begin);
		
		return (page_code.substring(begin,end));
	}
	public static String get_article_text_raw(int number) throws Exception
	{	
		String text_code = get_article_text_code(number);
		
		text_code = text_code.replace("<p>", " ");
		text_code = text_code.replace("</p>", " ");
		text_code = text_code.replace("<blockquote>", " ");
		text_code = text_code.replace("</blockquote>", " ");	
		text_code = text_code.replace("<br>", " ");	
		text_code = text_code.replace("<ul>", " ");
		text_code = text_code.replace("</ul>", " ");		
		text_code = text_code.replace("<li>", " ");
		text_code = text_code.replace("</li>", " ");		
		text_code = text_code.replace("<tbody>", " ");
		text_code = text_code.replace("</tbody>", " ");		
		text_code = text_code.replace("<tr>", " ");
		text_code = text_code.replace("</tr>", " ");		
		text_code = text_code.replace("<td>", " ");
		text_code = text_code.replace("</td>", " ");
		text_code = text_code.replace("<b>", " ");
		text_code = text_code.replace("</b>", " ");
		text_code = text_code.replace("</div>", " ");
		text_code = text_code.replace("</a>", " ");
		text_code = text_code.replace("</table>", " ");
		text_code = text_code.replace("</iframe>", " ");
		text_code = text_code.replace("</script>", " ");
		text_code = text_code.replace("<!--<a id=\"more\"></a>-->", " ");
		text_code = text_code.replace("<!--<a id=\"more\"> </a>-->", " ");
		text_code = text_code.replace("<!--<a id=\"more\">-->", " ");
		text_code = text_code.replace("<!--<a id=\"more\"> -->", " ");

		text_code = text_code.replace("<p", " ");
		text_code = text_code.replace("</p", " ");
		text_code = text_code.replace("<P", " ");
		text_code = text_code.replace("</P", " ");
		text_code = text_code.replace("</li", " ");
		
		int index = 0;
		while(index!=-1)
		{
			index = text_code.indexOf("<");
			int end = text_code.indexOf(">",index);
			
			if(index!=-1&&end!=-1)
			{
				text_code = text_code.substring(0, index)+text_code.substring(end+1,text_code.length());
			}
			else if(index!=-1&&end==-1)
			{
				break;
			}
		}

		text_code = text_code.replace("\n\n", "");
		return text_code;
	}
	public static final void clearFile(String f)
	{
		try
		{
			PrintWriter pw = new PrintWriter(new FileWriter(new File(f)));
			pw.print("");
			pw.close();
		}
		catch(Exception e)
		{
			
		}
	}
	public static final void prot(String f, String l)
	{
		try
		{
			PrintWriter pw = new PrintWriter(new FileWriter(new File(f),true));
			pw.println(l);
			pw.close();
		}
		catch(Exception e)
		{
			
		}
	}
	public static final void downloadFile(String FILE_URL, String FILE_NAME)
	{
		try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) 
		{
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) 
			{
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
