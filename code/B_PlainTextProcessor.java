// Part of: "Karma Literature Review", Copyrights Kevin Riehl 2024, <kriehl@ethz.ch>

import java.io.File;
import tools.EasyWordRemover;
import tools.TextTool;
import tools.Tool;

public class PlainTextProcessor 
{
	static String txt_folder = "KarmaCitingPapers/2_TXTs";
	static String des_folder = "KarmaCitingPapers/3_TXTs";
	static EasyWordRemover ewr = new EasyWordRemover();

	public static String preserveP2P(String inp) {
		String out = inp.toLowerCase();
		out = out.replace("peer to peer", "peerTopeer");
		out = out.replace("p2p", "peerTopeer");
		out = out.replace("p-2-p", "peerTopeer");
		out = out.replace("p-to-p", "peerTopeer");
		out = out.replace("peer-to-peer", "peerTopeer");
		return out;
	}
	public static void main(String[] args) throws Exception
	{
	//	Get TXT Folders
		String[] folders = new File(txt_folder).list();
	//	Get TXT Files
		//	Analyse Files
		Tool.prot("plainTextAnalysis.txt", "file,removedReferences");
		String[] files = new File(txt_folder).list();
		for(int f = 0;f<files.length;f++)
		{
			if(files[f].endsWith(".txt")) {
				String text = Tool.load_file(txt_folder+"/"+files[f]);
				int lengthOrig = text.length();
				boolean changed = false;
				String text2 = TextTool.removeReferences(text);
				if(text2.length()!=text.length())
					changed = true;
				text = text2;
				text = TextTool.removeDuplicatLines(text);
				text = preserveP2P(text);
				
				text = TextTool.plainTEXTfilter(text);
				text = TextTool.removeNewLinesTabsDoubleSpace(text);
				text = text.toLowerCase();
				text = ewr.deleteEasyWords(text);
				text = text.replaceAll("\\?"," ");
				System.out.println(text.contains("?"));
				int lengthNew = text.length();
				Tool.saveToTXT(des_folder+"/"+files[f], text);
				Tool.prot("plainTextAnalysis.txt", files[f]+"\t"+changed+"\t"+lengthOrig+"\t"+lengthNew);
				System.out.println(" ("+(f+1)+"/"+files.length+")\t"+txt_folder+"/"+files[f]);
				System.out.println(Tool.getTime());
			}
		}
	}
}
