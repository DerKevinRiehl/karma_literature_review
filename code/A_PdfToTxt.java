// Part of: "Karma Literature Review", Copyrights Kevin Riehl 2024, <kriehl@ethz.ch>
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import tools.RobotTools;
import tools.Tool;

public class PdfToTxt {
	public static void main(String[] args) throws IOException {
		String dir = "KarmaCitingPapers/1_PDFs";
		String dir2 = "KarmaCitingPapers/2_TXTs";
		String[] files = new File(dir).list();
		int wait = 10*1000;
		int counter = 0;
		for(String file: files) {
			if(counter<380) {
				counter += 1;
				continue;
			}
			Desktop.getDesktop().open(new File(dir+"/"+file));
			try {Thread.sleep(wait);} catch(Exception e) {}
			RobotTools.keyCTRLAll();
			try {Thread.sleep(wait);} catch(Exception e) {}
			RobotTools.keyCTRLCopy();
			try {Thread.sleep(wait);} catch(Exception e) {}
			String text = RobotTools.getClipBoard();
			Tool.saveToTXT(dir2+"/"+file+".txt", text);
			RobotTools.keyALTF4();
			try {Thread.sleep(wait/2);} catch(Exception e) {}
			counter+=1;
			System.out.println(counter+"/"+files.length+" "+file);
		}
	}
}
