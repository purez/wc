package wc;

import wc.ToolBox;
import java.io.*;

public class Test {
	public static void main(String[] args) throws IOException {
		
		int index = 1;
		while(args[index].length()==2)
			index++;
		File f = new File(args[index]);
		
		/*if(f.isDirectory() && args[findLocation(args) - 1].endsWith("s")) {
			System.out.println("us '-u' for directory");   错误处理、有s才递归
		}*/
		
		File[] files = f.listFiles();
		ToolBox toolBox = new ToolBox();
		if(args[0].equals("-c")) {
			//System.out.println("n");
			for(File file : files) {
				toolBox.countChar(file.getAbsoluteFile());
			}
		}
		if(args[0].equals("-w")) {
			for(File file : files) {
				toolBox.countWord(file.getAbsoluteFile());
			}
		}
		if(args[0].equals("-l"))
			for(File file : files) {
				toolBox.countLine(file.getAbsoluteFile(), args[1]);
			}
		
	}
	
}