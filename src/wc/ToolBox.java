package wc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.regex.Pattern;

public class ToolBox {
	
	public void countChar(File file) {
		FileReader fileReader = null;
		int numberOfChars = 0;
		try {
			fileReader = new FileReader(file);
			while(fileReader.read() > 1) {
				numberOfChars++;
			}
		}catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(null != fileReader) {
				try {
					fileReader.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("字符数: "+numberOfChars+"，文件:"+file.getAbsolutePath());
	}
	
	public void countWord(File file) throws IOException {
		int numberOfWords = 0;
		try {
			FileReader fileReader = new FileReader(file); //FileNotFound
			BufferedReader bufferedReader = new BufferedReader(fileReader); //IO
			String line = bufferedReader.readLine();
			while (line != null) {
				String[] wordArray = line.split(" ");
				numberOfWords += wordArray.length;
				line = bufferedReader.readLine();   //!!!不会自动de,如果此句放到while里就不用
			}
			bufferedReader.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	
		System.out.println("词数: "+numberOfWords+"，文件："+file.getAbsolutePath());
	}
	public void countLine(File file,String args) throws IOException {
		int totleNumberOfLines = 0;
		int numberOfBlankLines = 0;
		int numberofNoteLine = 0;
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
			LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
			String line = lineNumberReader.readLine();
			while(line != null) {
				totleNumberOfLines++;
				if(line.matches("\\s*\\p{Graph}\\s*") || line.equals(""))
					numberOfBlankLines++;
				String regex = "[^]";
				//Pattern noteLinePattern = Pattern.compile("(regrex)+");
				Pattern noteLinePattern = Pattern.compile("\\W.s*//|^//.*$|^/\\*.*\\*/$|^/\\*\\*.*$|^\\*.*$|.*\\*/$");
				//Pattern noteLinePattern = Pattern.compile("((\\W.s*//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+");
				if(noteLinePattern.matcher(line).find())
					numberofNoteLine++;
				//if(line.matches(regex))
					
				line = lineNumberReader.readLine();                //都一起算了
			}
			lineNumberReader.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(null != fileReader)
				try {
					fileReader.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
		}
		if(args.equals("-a")) {
			System.out.println("文件："+file.getAbsolutePath());
			System.out.println("空行: "+numberOfBlankLines);
			System.out.println("代码行: "+(totleNumberOfLines-numberOfBlankLines-numberofNoteLine));
			System.out.println("注释行: "+numberofNoteLine);
		}
		else
			System.out.println("行数： "+totleNumberOfLines+"，文件:"+file.getAbsolutePath());
	}
}