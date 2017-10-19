package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
//        String tag = args[0];
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String name = reader.readLine();
//        reader.close();
        String tag = "span";
        String name = "D:\\1\\3.txt";
        String tagStart = "<" + tag;
        String tagEnd = "</" + tag + ">";
        BufferedReader freader = new BufferedReader(new FileReader(name));
        String line = "";
        while (freader.ready()){
            String line1 = freader.readLine();
            line +=line1;
        }
//        int i = line.indexOf(tagStart);
//        int i1 = line.indexOf(tagEnd);
//        String s = line.substring(i, i1+tagEnd.length());
//        System.out.println(s);
//        String pattern = tagStart + ;
//        Pattern p = Pattern.compile("<span>\\D*?</span>");
        String q1 = "\\<span.*?\\>\\D*?\\</span\\>";
        String q2 = "(<\\w*>)?<span>\\D*?</span>(</\\D*>)?";
        String q3 = "\\<(/?[^\\>]+)\\>";
//        String q4 = "(?=(<span>(?:(?1)|<(?!\\/span>)|[^<]*+)*+<\\/span>))";
        String q5 = "(<|(</))" + tag + "(>|\\s)|(\\n)";

        Pattern p = Pattern.compile(q5);
//        Pattern p = Pattern.compile("<span\\D*?>(<\\D*>)?(<span>)?\\D*?(</span>)?(</\\D*>)?</span>");
//        Pattern p = Pattern.compile("(<span\\D*?>)(<\\D*>(<span\\D*?>))?\\D*((</span>)</\\D*>)?(</span>)");
//        Pattern p = Pattern.compile("(<span\\D*?>)\\D*?(<\\D*?><span>\\D*?</span></\\D*?>)?(<\\D*>\\D*?</\\D*>)?</span>");
        Matcher m = p.matcher(line);

        while(m.find()) {
//            System.out.println(m.group());
//            System.out.println(line.substring(m.start(), m.end()));
        }
    }
}
