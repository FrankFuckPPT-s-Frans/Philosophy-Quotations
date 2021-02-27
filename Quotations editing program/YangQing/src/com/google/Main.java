package com.google;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        run();
    }

    public static void run() throws IOException {
        Scanner in = new Scanner(System.in);
        int index = 0;
        System.out.print("输入调用函数编号：");
        if(in.hasNextInt()) {
            index = in.nextInt();
            switch(index) {
                case 1:
                    first();
                    break;
                case 2:
                    second();
                    break;
                case 3:
                    third();
                    break;
            }
        }
    }

    public static void first() throws IOException {
        // 遍历所有行，列出Frank的所有语录（带日期，用于快速查询某条语录的日期）
        StringBuilder builder = new StringBuilder();
        String QQNumber = "";
        Scanner in = new Scanner(Paths.get("D:\\Project\\数据库\\Bilibili技术学习交流群12.01.txt"), "UTF-8");
        while (in.hasNextLine()) {
            String temp = in.nextLine();
            if (temp.contains("1191367564") || temp.contains("210355779")) {
                String nextLen = in.nextLine();
                builder.append(temp + '\n' + nextLen + '\n');
            }
        }
        System.out.println(builder.toString());
        PrintWriter out = new PrintWriter("C:\\Users\\YangQing\\Desktop\\Frank语录12.01[带日期].txt", "UTF-8");
        out.print(builder.toString());
        out.close();
    }

    public static void second() throws IOException {
        // 去除日期，方便人工查找有用的信息
        StringBuilder builder = new StringBuilder();
        Scanner in = new Scanner(Paths.get("C:\\Users\\YangQing\\Desktop\\Frank语录12.01[带日期].txt"), "UTF-8");
        while (in.hasNextLine()) {
            String temp = in.nextLine();
            if (temp.length() > 5 && temp.substring(0, 4).equals("2020")) {
                String nextLen = in.nextLine();
                builder.append(nextLen).append('\n');
            }
        }
        System.out.println(builder.toString());
        PrintWriter out = new PrintWriter("C:\\Users\\YangQing\\Desktop\\Frank语录12.01[去除日期].txt", "UTF-8");
        out.print(builder.toString());
        out.close();
    }

    public static void third() throws IOException {
        // 一句一句连接起来，生成符合格式要求的.md文件（提前先打好标点符号）
        StringBuilder builder = new StringBuilder();
        Scanner in = new Scanner(Paths.get("C:\\Users\\YangQing\\Desktop\\新建文本文档.txt"), "UTF-8");
        boolean lineFlag = true; // 记录是不是行首
        // 初始标题号（可根据需要修改）
        int index = 11;
        // 创建一个三级标题并且连接标题号和一个'.'
        builder.append("### ").append(index++).append(".\n");
        while (in.hasNextLine()) {
            String temp = in.nextLine();
            // 如果那一行里面有'-'就说明是日期，加入特定格式的md代码
            if (temp.contains("-")) {
                builder.append("> ————");
            // 如果是行首，就要在前面加入'**'来加粗整行
            } else if(lineFlag) {
                builder.append("**");
                lineFlag = false; // 更改lineFlag，表示正在读入一行
                /*
                * 之所以弄这么麻烦是因为如果之前在行尾添加了逗号，说明这行不是一整句话
                * 因为一行一句话阅读起来比较连贯，所以加了一些操作
                */
            }
            // 将一行的内容加入文件
            builder.append(temp);
            if ((temp.contains("。") || temp.contains("？")) && temp.length() >= 4) { // 如果行尾是'。'或'？'说明一句话结束了
                // 加入'**'与前面的加粗符号对应，换两行（因为每行内容之间隔个空行阅读会更舒服，而且这个空行在Typora里是比正常的一行窄一点，且不可编辑）
                builder.append("**\n\n");
                lineFlag = true;
            } else if (temp.contains("-")) {
                // 如果是日期行就进行相应的格式化
                builder.append("\n>\n> ----");
            }
            if (temp.contains("-") && in.hasNextLine()) {
                // 每两个标题之间需要添加四个换行（经过测试这样看着最舒服），然后添加新的标题
                builder.append("\n\n\n\n").append("### ").append(index++).append(".\n");
            }
        }
        System.out.println(builder.toString());
        PrintWriter out = new PrintWriter("C:\\Users\\YangQing\\Desktop\\Frank语录12.01[完全版]next.md", "UTF-8");
        out.print(builder.toString());
        out.close();
    }
}
