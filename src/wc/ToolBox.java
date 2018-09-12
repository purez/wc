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
		int numberOfChar = 0;
		try {
			fileReader = new FileReader(file);
			while(fileReader.read() > 1) {
				numberOfChar++;
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
		System.out.println("字符数: "+numberOfChar+"，文件:"+file.getAbsolutePath());
	}
	
	public void countWord(File file) throws IOException {
		int numberOfWord = 0;
		try {
			FileReader fileReader = new FileReader(file); //FileNotFound
			BufferedReader bufferedReader = new BufferedReader(fileReader); //IO
			String line = bufferedReader.readLine();
			while (line != null) {
				line.replaceAll("[\\p{Nd}\\u4e00-\\uffe5\\p{Punct}\\s]", " ");
				String[] wordArray = line.split(" ");
				numberOfWord += wordArray.length;
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	
		System.out.println("词数: "+numberOfWord+"，文件："+file.getAbsolutePath());
	}
	public void countLine(File file,String args) throws IOException {
		int numberOfAnnotatedLine = 0;
		int numberOfBlankLine = 0;
		int numberOfCodeLine = 0;
		
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
            boolean comm = false;
            String line;
            while((line = bufferedReader.readLine()) != null) {
                if(comm) {	//是否匹配多行注释
                	if(line.matches(".*\\*/\\s*")) {
                		comm = false;
                	} 
                numberOfAnnotatedLine++;
                }else {
                	if(line.matches("\\s*\\p{Graph}\\s*") || line.matches("\\s+")) { //匹配空白字符行、单字符行
                		numberOfBlankLine++;
                	}else if(line.matches("\\s*}?\\s*//.*") || line.matches(".*/\\*.*\\*/.*")) {//单行注释//、/* */
                		numberOfAnnotatedLine++;
                	}else if(line.matches(".*/\\*.*")) {//匹配*
                		comm = true;
                		numberOfAnnotatedLine++;
                	}else {
						numberOfCodeLine++;
					}
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		int totleNumberOfLine = numberOfAnnotatedLine + numberOfBlankLine + numberOfCodeLine;
		if(args.equals("-a")) {
			System.out.println("文件："+file.getAbsolutePath());
			System.out.println("空行: "+numberOfBlankLine+", 代码行: "+numberOfCodeLine+", 注释行: "+numberOfAnnotatedLine);
		}
		else
			System.out.println("行数： "+totleNumberOfLine+"，文件:"+file.getAbsolutePath());
		
	}
}