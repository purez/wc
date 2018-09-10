package wc;

import wc.ToolBox;
import java.io.*;

public class Test {
	public static void main(String[] args) throws IOException {
		int index = 1;
		while(args[index].length()==2)
			index++;
		File f = new File(args[index]);
		ToolBox toolBox = new ToolBox();
		if(f.isDirectory()) {
			
			FileFilter fileFilter = new FileFilter() {
				@Override
				public boolean accept(File arg0) {  //参数是具体文件名
					// TODO Auto-generated method stub
					if(arg0.getName().endsWith(".java"))
						return true;
					return false;
				}
			};
			
			
			File[] files = f.listFiles(fileFilter);
			
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
			if(args[0].equals("-l")) {
				for(File file : files) {
					toolBox.countLine(file.getAbsoluteFile(), args[1]);
				}
			}
		}
	  	else {
	  		if(args[0].equals("-c")) {
	  			toolBox.countChar(f);
	  		}
	  		if(args[0].equals("-w")) {
	  			toolBox.countWord(f);
	  		}
	  		if(args[0].equals("-l")) {
	  			toolBox.countLine(f, args[1]);
	  		}
	  	}		
		
	}
	
}