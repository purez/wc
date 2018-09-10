package wc;

import wc.ToolBox;
import java.io.*;

public class Test {

	public static void main(String[] args) throws IOException {  //不做差错处理，保证正确输入
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
  		}   //基本功能
  		
  		//扩展功能
  		if(args[0].equals("-s") || args[1].equals("-s")) {
  			String currentPath = Test.class.getResource("").getPath();
  			String prefix = args[indexOfPath].substring(args[indexOfPath].lastIndexOf("."));
  			//String currentPath = System.getProperty("user.dir");
  			//System.out.println(currentPath);
  			FileFilter fileFilter = new FileFilter() {
				@Override
				public boolean accept(File arg0) {  //参数是具体文件名
					// TODO Auto-generated method stub
					if(arg0.getName().endsWith(prefix))
						return true;
					return false;
				}
			};
			
			File[] files = new File(currentPath).listFiles(fileFilter);
  			
  			if(args[0].equals("-c") || args[1].equals("-c")) {
				for(File file2 : files) {
					toolBox.countChar(file2.getAbsoluteFile());
				}
			}
			if(args[0].equals("-w") || args[1].equals("-w")) {
				for(File file2 : files) {
					toolBox.countWord(file2.getAbsoluteFile());
				}
			}
			if(args[0].equals("-l") || args[1].equals("-l")) {
				for(File file2 : files) {
					toolBox.countLine(file2.getAbsoluteFile(), "-a".toString());
				}
			}
  		}
  		
  	}
	
}