package wc;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class ToolBox {
	
	public void countChar(File file) throws IOException {
		int numberOfChars = 0;
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		while(bufferedReader.readLine() != null) {
			String[] wordArray = bufferedReader.readLine().toString().split("\\s+");
			for(int i = 0;i < wordArray.length;i++) {
				numberOfChars += wordArray[i].length();
			}
		}
		bufferedReader.close();
		System.out.println("字符数: "+numberOfChars+"，文件:"+file.getName());
	}
	
	public void countWord(File file) throws IOException {
		int numberOfWords = 0;
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		while(bufferedReader.readLine() != null) {
			String[] wordArray = bufferedReader.readLine().toString().split("\\s*");
			numberOfWords += wordArray.length;
		}
		bufferedReader.close();
		System.out.println("词数: "+numberOfWords+"，文件："+file.getName());
	}
	
	public void countLine(File file,String args) throws IOException {
		int totleNumberOfLines = 0;
		int numberOfBlankLines = 0;
		int numberofCommentLine = 0;
		LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
		while(lineNumberReader.readLine() != null) {
			//if(lineNumberReader.readLine().matches(""))
				//numberOfBlankLines++;
			//if(lineNumberReader.readLine().matches(""))
				//numberofCommentLine++;
			totleNumberOfLines++;
		}
		lineNumberReader.close();
		if(args.equals("-a")) {
			System.out.println("文件："+file.getName());
			System.out.println("空行: "+numberOfBlankLines);
			System.out.println("代码行: "+(totleNumberOfLines-numberOfBlankLines-numberofCommentLine));
			System.out.println("注释行: "+numberofCommentLine);
		}
		else
			System.out.println("行数： "+totleNumberOfLines+"，文件:"+file.getName());
	}
}