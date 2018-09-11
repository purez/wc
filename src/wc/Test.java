package wc;

import wc.ToolBox;
import java.io.*;

public class Test {

	public static void main(String[] args) throws IOException {
		
		int indexOfPath = 1;
		while(args[indexOfPath].length()==2)
			indexOfPath++;
		File file = new File(args[indexOfPath]);
		ToolBox toolBox = new ToolBox();
		
		if(args[0].equals("-c")) {
  			toolBox.countChar(file);
  		}
  		if(args[0].equals("-w")) {
  			toolBox.countWord(file);
  		}
  		if(args[0].equals("-l")) {
  			toolBox.countLine(file, args[indexOfPath]);
  		}
  		
  		if(args[0].equals("-s") || args[1].equals("-s")) {
  			String currentPath = Test.class.getResource("").getPath();//调试用
  			String prefix = args[indexOfPath].substring(args[indexOfPath].lastIndexOf("."));
  			//String currentPath = System.getProperty("user.dir");//打包用
  			
  			FileFilter fileFilter = new FileFilter() {
				@Override
				public boolean accept(File arg0) {
					// TODO Auto-generated method stub
					if(arg0.getName().endsWith(prefix))
						return true;
					return false;
				}
			};
			
			File[] files = new File(currentPath).listFiles(fileFilter);
			if(files != null) {
				if(args[0].equals("-c") || args[1].equals("-c")) {
					for(File file2 : files) {
						toolBox.countChar(file2);
					}
				}
				if(args[0].equals("-w") || args[1].equals("-w")) {
					for(File file2 : files) {
						toolBox.countWord(file2);
					}
				}
				if(args[0].equals("-l") || args[1].equals("-l")) {
					for(File file2 : files) {
						toolBox.countLine(file2, "args".toString());
					}
				}
				if(args[0].equals("-a") || args[1].equals("-a")) {
					for(File file2 : files) {
						toolBox.countLine(file2, "-a".toString());
					}
				}
			}
  		}
  	}
}