package com.shiliang.icor.test.leetcode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName Demo09.java
 * @Description TODO
 * @createTime 2021年04月25日 17:10:00
 */
public class Demo09 {
    public static void main(String[] args) throws IOException {
        File file = new File(""+File.separator+"test.txt");
        Writer out = new FileWriter(file);
        String str="Hello World";
        out.write(str);
    }
}
