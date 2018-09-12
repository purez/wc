# 软件工程作业一：WC JAVA实现

## 项目相关要求

 + 基本要求
    - wc.exe -c file.c     //返回文件 file.c 的字符数（实现）
    - wc.exe -w file.c    //返回文件 file.c 的词的数目（实现）
    - wc.exe -l file.c      //返回文件 file.c 的行数（实现）
 + 扩展功能
    - -s   递归处理目录下符合条件的文件（实现）
    - -a   返回更复杂的数据（代码行 / 空行 / 注释行）（实现）
 + 高级功能
    -  -x 参数。这个参数单独使用。如果命令行有这个参数，则程序会显示图形界面，用户可以通过界面选取单个文件，程序就会显示文件的字符数、行数等全部统计信息。（未实现）

[Github仓库：https//github.com/purez/wc](htttps://github.com/purez/wc)

## 解题思路
 将程序需要调用的三个函数（分别统计字符数、词数和行数）封装成一个类` ToolBox `，在另一个类 ` Test ` 中实现 `main` 方法，处理外部输出的各种参数，并调用对应的统计函数完成数据的统计。

## 遇到的问题及解决方法
 1. 开始对如何界定一个代码源文件中的字符、词，以及代码行、空行、注释行这个问题上存在一些疑问，通过和同学讨论，很快就解决了。
 2. 因为对正则表达式一知半解，所以在匹配词、空行和注释行上遇到了不小困难，通过翻书、网上搜索、请教同学等方式恶补了正则表达式的有关知识。 
 3. 对于扩展功能处理目录下符合条件的文件，选择了使用 ` FileFliter `。

## 设计及关键代码
 + 统计字符数

 ```Java
 fileReader = new FileReader(file);
	while(fileReader.read() > 1) {
		numberOfChar++;
	}
 ```

 + 统计词数
   
 ```Java
 String line = bufferedReader.readLine();
	while (line != null) {
		line.replaceAll("[\\p{Nd}\\u4e00-\\uffe5\\p{Punct}\\s]", " ");//替换特殊字符
		String[] wordArray = line.split(" "); //根据空格切割
		numberOfWord += wordArray.length;
		line = bufferedReader.readLine();
	}
 ```

 + 统计行数

 ```Java
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
 ```
 + 通配符扩展功能的实现

 ```Java
 if(args[0].equals("-s") || args[1].equals("-s")) {
  	String prefix = args[indexOfPath].substring(args[indexOfPath].lastIndexOf("."));//获取后缀
  	String currentPath = System.getProperty("user.dir");	//获取当前路径
  	FileFilter fileFilter = new FileFilter() {    //过滤器
		@Override
		public boolean accept(File arg0) {
			// TODO Auto-generated method stub
			if(arg0.getName().endsWith(prefix))
				return true;
			return false;			
		}
	};
}
File[] files = new File(currentPath).listFiles(fileFilter);//获取目录下所有文件并过滤
 ```

## 测试
 test0、test1、test2分别是空文件、包含一个字符的文件、只有一行的文件，test.java是一个典型的代码文件，如下

![test.java](/home/lixia1855/Pictures/Screenshot from 2018-09-12 19-02-02.png)

打开终端，输入一下命令测试部分功能：

> java -jar wc.jar -c ./testFiles/test.java && java -jar wc.jar -w ./testFiles/test.java && java -jar wc.jar -l ./testFiles/test.java



> java -jar ../wc.jar -s -a *.java



> java -jar ../wc.jar -s -c *.java

结果如下：

![test.java](/home/lixia1855/Pictures/Screenshot from 2018-09-12 19-01-15.png) 

## 代码覆盖率
![coverage](/home/lixia1855/Pictures/Screenshot from 2018-09-11 17-51-16.png)
	
`ToolBox`类中有较多的`try-catch`语句未被执行

## PSP表格

| PSP2.1                                  | Personal Software Process Stages        | 预估耗时（分钟）    | 实际耗时（分钟）    |
|-----------------------------------------|-----------------------------------------|------------------|----------------- |
| Planning                                | 计划                                    |  30                |   60               |
| · Estimate                              | · 估计这个任务需要多少时间              |     30             |    30              |
| Development                             | 开发                                    |   480               |   600            |
| · Analysis                              | · 需求分析 (包括学习新技术)             |    300              |  360                |
| · Design Spec                           | · 生成设计文档                          |   30               |    30              |
| · Design Review                         | · 设计复审 (和同事审核设计文档)         |        60          |         60         |
| · Coding Standard                       | · 代码规范 (为目前的开发制定合适的规范) |            30      |           30       |
| · Design                                | · 具体设计                              |       30           |        30          |
| · Coding                                | · 具体编码                              |       180           |         240         |
| · Code Review                           | · 代码复审                              |       30           |          30        |
| · Test                                  | · 测试（自我测试，修改代码，提交修改）  |            120      |             120     |
| Reporting                               | 报告                                    |      60            |           60       |
| · Test Report                           | · 测试报告                              |       30           |           30       |
| · Size Measurement                      | · 计算工作量                            |       30           |            30      |
| · Postmortem & Process Improvement Plan | · 事后总结, 并提出过程改进计划          |          30        |            30      |
| 合计                                    |                                         |      1470            |        1760          |


## 项目小结
 这一次的作业还是让我学习了不少新的知识，也增加了代码经验，编程能力应该也得到了一点提高。但是由于缺乏训练和经验，我的代码拙于设计，基本毫无优点可言，从软件工程的许多观点看上去也是幼稚和低级的，希望能在日后的学习和锻炼中得到提高。
  
