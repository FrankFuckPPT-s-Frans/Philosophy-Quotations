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
        Scanner in = new Scanner(Paths.get("D:\\Project\\数据库\\Bilibili技术学习交流群10.24.txt"), "UTF-8");
        while (in.hasNextLine()) {
            String temp = in.nextLine();
            if (temp.length() > 25 && temp.substring(temp.length() - 11, temp.length() - 1).equals("1191367564")) {
                String nextLen = in.nextLine();
                builder.append(temp + '\n' + nextLen + '\n');
            }
        }
        System.out.println(builder.toString());
        PrintWriter out = new PrintWriter("C:\\Users\\YangQing\\Desktop\\Frank语录10.24[带日期].txt", "UTF-8");
        out.print(builder.toString());
        out.close();
    }

    public static void second() throws IOException {
        // 去除日期，方便人工查找有用的信息
        StringBuilder builder = new StringBuilder();
        Scanner in = new Scanner(Paths.get("C:\\Users\\YangQing\\Desktop\\Frank语录10.24[带日期].txt"), "UTF-8");
        while (in.hasNextLine()) {
            String temp = in.nextLine();
            if (temp.length() > 5 && temp.substring(0, 4).equals("2020")) {
                String nextLen = in.nextLine();
                builder.append(nextLen).append('\n');
            }
        }
        System.out.println(builder.toString());
        PrintWriter out = new PrintWriter("C:\\Users\\YangQing\\Desktop\\Frank语录10.24[去除日期].txt", "UTF-8");
        out.print(builder.toString());
        out.close();
    }

    public static void third() throws IOException {
        // 一句一句连接起来，生成符合格式要求的.md文件（提前先打好标点符号）
        StringBuilder builder = new StringBuilder();
        Scanner in = new Scanner(Paths.get("C:\\Users\\YangQing\\Desktop\\新建文本文档.txt"), "UTF-8");
        boolean lineFlag = true;
        int index = 6;
        builder.append("### ").append(index++).append(".\n");
        while (in.hasNextLine()) {
            String temp = in.nextLine();
            if (temp.contains("-")) {
                builder.append("> ————");
            } else if(lineFlag) {
                builder.append("**");
                lineFlag = false;
            }
            builder.append(temp);
            if ((temp.contains("。") || temp.contains("？")) && temp.length() >= 4) {
                builder.append("**\n\n");
                lineFlag = true;
            } else if (temp.contains("-")) {
                builder.append("\n>\n> ----");
            }
            if (temp.contains("-") && in.hasNextLine()) {
                builder.append("\n\n\n\n").append("### ").append(index++).append(".\n");
            }
        }
        System.out.println(builder.toString());
        PrintWriter out = new PrintWriter("C:\\Users\\YangQing\\Desktop\\Frank语录10.24[完全版].md", "UTF-8");
        out.print(builder.toString());
        out.close();
    }
}
