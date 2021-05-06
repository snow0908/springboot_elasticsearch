package com.snow.learn.controller;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileCopy {

    /**
     * 复制单个文件
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            //文件存在时
            if (oldfile.exists()) {
                //读入原文件
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    //字节数 文件大小
                    bytesum += byteread;
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }


    public static void htmlToPdf() {
        StringBuilder strline = new StringBuilder("");
//        File fin = new File("C:\\Users\\Administrator\\Desktop\\共享数据质量报告(1)\\共享数据质量报告\\index.html");
        File fin = new File("C:\\Users\\ASUS\\Desktop\\test\\文书.html");
        try (RandomAccessFile accessFile = new RandomAccessFile(fin, "r");
             FileChannel fcin = accessFile.getChannel();
        ){
            Charset charset = Charset.forName("UTF-8");
            int bufSize = 100000;
            ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);
            String enterStr = "\n";
            byte[] bs = new byte[bufSize];

            StringBuilder strBuf = new StringBuilder("");
            while (fcin.read(rBuffer) != -1) {
                int rSize = rBuffer.position();
                rBuffer.rewind();
                rBuffer.get(bs);
                rBuffer.clear();
                String tempString = new String(bs, 0, rSize,charset);
                tempString = tempString.replaceAll("\r", "");

                int fromIndex = 0;
                int endIndex = 0;
                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {
                    String line = tempString.substring(fromIndex, endIndex);
                    line = strBuf.toString() + line;
                    strline.append(line.trim());

                    strBuf.delete(0, strBuf.length());
                    fromIndex = endIndex + 1;
                }
                if (rSize > tempString.length()) {
                    strline.append(tempString.substring(fromIndex, tempString.length()));
                    strBuf.append(tempString.substring(fromIndex, tempString.length()));
                } else {
                    strline.append(tempString.substring(fromIndex, rSize));
                    strBuf.append(tempString.substring(fromIndex, rSize));
                }
            }
            System.out.println(strline.toString().replaceAll("\"", "'"));
            String htmlString="";
            //注意这里为啥要写这个，主要是替换成这样的字体，如果不设置中文有可能显示不出来。
            htmlString = strline.toString().replaceAll("\"", "'").replaceAll("<style>", "<style>body{font-family:SimSun;font-size:14px;}");
            //生成PDF文件的路径
            OutputStream os = new FileOutputStream("C:\\Users\\ASUS\\Desktop\\test\\in.pdf");
            ITextRenderer renderer = new ITextRenderer();
            ITextFontResolver font = renderer.getFontResolver();
            //添加中文识别，这里是设置的宋体，Linux下要换成对应的字体
//            font.addFont("C:/WINDOWS/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            renderer.setDocumentFromString(htmlString.toString());

            renderer.layout();
            renderer.createPDF(os);
            renderer.finishPDF();


        } catch (Exception e) {

        }

    }
    /*public static void main(String[] args) {
        htmlToPdf();
//        String oldPath="D:\\a-pdf\\WST-482-2016卫生信息共享文档编制规范.pdf";
//        String newPath="E:\\a-pdf\\WST-482-2016卫生信息共享文档编制规范.pdf";
//        copyFile(oldPath,newPath);
    }*/
}
