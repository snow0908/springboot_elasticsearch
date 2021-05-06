package com.snow.learn.util;

import com.itextpdf.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

public class FileTypeConvertUtil {
    /**
     * 将HTML转成PD格式的文件。html文件的格式比较严格
     * @param htmlFile
     * @param pdfFile
     * @throws Exception
     */
    // <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd ">
    public static void html2pdf(String htmlFile, String pdfFile) throws Exception {
        // step 1
        String url = new File(htmlFile).toURI().toURL().toString();
        System.out.println(url);
        // step 2
        OutputStream os = new FileOutputStream(pdfFile);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);

        // step 3 解决中文支持
        ITextFontResolver fontResolver = renderer.getFontResolver();
        if("linux".equals(getCurrentOperatingSystem())){
            fontResolver.addFont("\\usr\\share\\fonts\\chiness\\simsun.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        }else{
            fontResolver.addFont("C:\\Windows\\Fonts\\simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        }

        renderer.layout();
        renderer.createPDF(os);
        os.close();

        System.out.println("create pdf done!!");

    }

    public static String getCurrentOperatingSystem(){
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("---------当前操作系统是-----------" + os);
        return os;
    }


    /**
     * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！)
     *
     * @param res            原字符串
     * @param filePath 文件路径
     * @return 成功标记
     */
    public  static  boolean string2File(String res, String filePath) {
        boolean flag =  true;
        BufferedReader bufferedReader =  null;
        BufferedWriter bufferedWriter =  null;
        try {
            File distFile =  new File(filePath);
            if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs();
            bufferedReader =  new BufferedReader( new StringReader(res));
            bufferedWriter =  new BufferedWriter( new FileWriter(distFile));
            //字符缓冲区
            char buf[] =  new  char[1024];
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        }  catch (IOException e) {
            e.printStackTrace();
            flag =  false;
            return flag;
        }  finally {
            if (bufferedReader !=  null) {
                try {
                    bufferedReader.close();
                }  catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 文本文件转换为指定编码的字符串
     *
     * @param file         文本文件
     * @param encoding 编码类型
     * @return 转换后的字符串
     * @throws IOException
     */
    public  static String file2String(File file, String encoding) {
        InputStreamReader reader =  null;
        StringWriter writer =  new StringWriter();
        try {
            if (encoding ==  null || "".equals(encoding.trim())) {
                reader =  new InputStreamReader( new FileInputStream(file), encoding);
            }  else {
                reader =  new InputStreamReader( new FileInputStream(file));
            }
            //将输入流写入输出流
            char[] buffer =  new char[1024];
            int n = 0;
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        }  catch (Exception e) {
            e.printStackTrace();
            return  null;
        }  finally {
            if (reader !=  null)
                try {
                    reader.close();
                }  catch (IOException e) {
                    e.printStackTrace();
                }
        }
        //返回转换结果
        if (writer !=  null)
            return writer.toString();
        else  return  null;
    }



    /*public static void main(String[] args) {
        String htmlFile = "C:\\Users\\ASUS\\Desktop\\test\\文书.html";
        String pdfFile = "C:\\Users\\ASUS\\Desktop\\test\\in.pdf";
        try {
            FileTypeConvertUtil.html2pdf(htmlFile, pdfFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    public static void main(String[] args) {
        String htmlString = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> \n" +
                "  <html> \n" +
                "     <head> \n" +
                "   \t\t<title>测试页面</title> \n" +
                "   \t\t<meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"UTF-8\"/> \n" +
                "   \t\t<meta http-equiv=\"pragma\" content=\"no-cache\"/> \n" +
                "   \t\t<meta http-equiv=\"cache-control\" content=\"no-cache\"/> \n" +
                "   \t\t<meta http-equiv=\"expires\" content=\"0\"/> \n" +
                "   \t\t<style type=\"text/css\"> \n" +
                "          [l_mongol_font_replace]  \n" +
                "     \t\t* {\n" +
                "   \t  \t\tmargin: 0px;\n" +
                "   \t\t  } \n" +
                "         .TRzEdit { \n" +
                "          border:0px; \n" +
                "         } \n" +
                "         .TDateTimePickerEx {\n" +
                "             border:0px; \n" +
                "\t\t      } \n" +
                "\t        .TRzLineComboBox{ \n" +
                "             border:0px; \n" +
                "         } \n" +
                "         .TMComboBox{ \n" +
                "            border:0px; \n" +
                "            writing-mode:tb-lr;  \n" +
                "            -webkit-writing-mode:vertical-rl;  \n" +
                "            -webkit-text-orientation:sideways;  \n" +
                "         }  \n" +
                "         .TMDateTimeEdit{ \n" +
                "            border:0px; \n" +
                "            writing-mode:tb-lr;  \n" +
                "            -webkit-writing-mode:vertical-rl;  \n" +
                "            -webkit-text-orientation:sideways;  \n" +
                "         }  \n" +
                "         .TMEdit{    \n" +
                "            border:0px; \n" +
                "            writing-mode:tb-lr;  \n" +
                "            -webkit-writing-mode:vertical-rl;  \n" +
                "            -webkit-text-orientation:sideways;  \n" +
                "         } \n" +
                "         .TMCheckBox{    \n" +
                "            border:0px; \n" +
                "            writing-mode:tb-lr;  \n" +
                "            -webkit-writing-mode:vertical-rl;  \n" +
                "            -webkit-text-orientation:sideways;  \n" +
                "         } \n" +
                "         .TMLabel{    \n" +
                "            border:0px; \n" +
                "            writing-mode:tb-lr;  \n" +
                "            -webkit-writing-mode:vertical-rl;  \n" +
                "            -webkit-text-orientation:sideways;  \n" +
                "         } \n" +
                "         .TMMaskEdit{    \n" +
                "            border:0px; \n" +
                "            writing-mode:tb-lr;  \n" +
                "            -webkit-writing-mode:vertical-rl;  \n" +
                "            -webkit-text-orientation:sideways;  \n" +
                "         } \n" +
                "         .MTRichViewEdit{    \n" +
                "            border:0px; \n" +
                "            writing-mode:tb-lr;  \n" +
                "            -webkit-writing-mode:vertical-rl;  \n" +
                "            -webkit-text-orientation:sideways;  \n" +
                "         } \n" +
                "   \t\t</style> \n" +
                "   \t</head> \n" +
                "   \t<body> \n" +
                " <div id=\"YXVCLPad1\" style=\"border: #000000 0px solid; position: relative;        height: 1029px;        width: 979\">\n" +
                "<div id =\"_PageHeader_\" class=\"TRzPanel\" style=\"position: relative;border:#000000 0px solid; height:142px;\">\n" +
                "<div id =\"Pnl_1\" class=\"TRzPanel\" style=\"position: relative;border-Bottom:#000000 1px solid; height:142px;\">\n" +
                "<pre id=\"TMLabel_27\"  CINNERID=\"00559713\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:101px; width:16px;\">\uE2F2\uE291\uE2BC\uE2EC\uE291\uE2F9</pre>\n" +
                "<pre id=\"TMLabel_26\"  CINNERID=\"00559666\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:85px; width:16px;\">\uE291\uE2B5</pre>\n" +
                "<pre id=\"TMLabel_25\"  CINNERID=\"00559724\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:69px; width:16px;\">\uE28C\uE2FB\uE291\uE2FF </pre>\n" +
                "<pre id=\"TMLabel_28\"  CINNERID=\"00559683\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:117px; width:16px;\">\uE271\uE2F5\uE2B7\uE276\uE2FB\uE2EB\uE275</pre>\n" +
                "<pre id=\"TMLabel_30\"  CINNERID=\"00559698\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:149px; width:16px;\">\uE2D2\uE291\uE327\uE27E\uE31F\uE26A</pre>\n" +
                "<pre id=\"TMLabel_29\"  CINNERID=\"00559702\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:133px; width:16px;\">\uE321\uE27E\uE2B5</pre>\n" +
                "<pre id=\"MLabel3\"  CINNERID=\"00559718\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:16pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:247px; width:22px;\">\uE2C1\uE26D\uE325 </pre>\n" +
                "<pre id=\"TMLabel_24\"  CINNERID=\"00559667\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:53px; width:16px;\">\uE28C\uE2FA\uE26C\uE2B5</pre>\n" +
                "<pre id=\"MLabel5\"  CINNERID=\"00559639\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:16pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:301px; width:22px;\">\uE2FD\uE26C\uE2EC\uE291\uE31D\uE28D </pre>\n" +
                "<pre id=\"MLabel6\"  CINNERID=\"00559640\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:16pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:327px; width:22px;\">\uE2E1\uE26C\uE328\uE291\uE2EE\uE301\uE26C\uE2B5 </pre>\n" +
                "<pre id=\"MLabel7\"  CINNERID=\"00559641\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:16pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:354px; width:22px;\">\uE308\uE276\uE2F5\uE313\uE276\uE2EB\uE2FC\uE276\uE2F9 </pre>\n" +
                "<pre id=\"MLabel4\"  CINNERID=\"00559638\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:16pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:274px; width:22px;\">\uE271\uE2F5\uE2B7\uE276\uE2FB\uE2EB\uE275 \uE310\uE296 </pre>\n" +
                "<pre id=\"TMLabel_23\"  CINNERID=\"00559673\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:37px; width:16px;\">\uE291\uE2B5</pre>\n" +
                "<pre id=\"TMLabel_22\"  CINNERID=\"00559697\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:21px; width:16px;\">\uE2F2\uE291\uE2BC\uE2EC\uE291\uE2F9</pre>\n" +
                "<pre id=\"MLabel_5\"  CINNERID=\"00559690\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:5px; width:16px;\">\uE2A2\uE2C6\uE2AC\uE325 </pre>\n" +
                "<pre id=\"MLabel2\"  CINNERID=\"00559717\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:16pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:221px; width:22px;\">\uE2C2\uE292\uE2FB\uE291\uE2EA\uE26C\uE313\uE291\uE27B </pre>\n" +
                "<pre id=\"MLabel_2\"  CINNERID=\"00559716\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:16pt;font-family: 'Menksoft \uE2CE\uE26C\uE325\uE26A \uE308\uE27E\uE2E8'; top:19px; left:194px; width:22px;\">24\uE315\uE26C\uE2E7 </pre>\n" +
                "<pre id=\"Lable_2\"  CINNERID=\"00559655\"  CELEBM=\"NUL.0\"  class=\"TLabel\"  style=\"position: absolute;color:#000000;font-weight:bold;font-size:16pt;font-family: '黑体'; top:29px; left:382px; width:208px;\">24小时内入出院记录</pre>\n" +
                "<pre id=\"Lable_4\"  CINNERID=\"00559693\"  CELEBM=\"NUL.0\"  class=\"TLabel\"  style=\"position: absolute;color:#000000;font-weight:bold;font-size:13pt;font-family: '黑体'; top:55px; left:5px; width:171px;\">内蒙古国际蒙医医院</pre>\n" +
                "<pre id=\"TMLabel_2\"  CINNERID=\"00559708\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:100px; left:488px; width:16px;\">\uE310\uE2A3</pre>\n" +
                "<pre id=\"TMLabel_3\"  CINNERID=\"00559709\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:100px; left:506px; width:16px;\">\uE2FD\uE26C\uE2EC\uE291\uE2EE\uE301\uE26C\uE2B5</pre>\n" +
                "<pre id=\"TMLabel_31\"  CINNERID=\"00559710\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:100px; left:524px; width:16px;\">\uE2B3\uE291\uE2F5\uE32C\uE325 \uE238 </pre>\n" +
                "<pre id=\"TMLabel_0\"  CINNERID=\"00559706\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:100px; left:26px; width:16px;\">\uE2B1\uE276\uE325\uE274 \uE238 </pre>\n" +
                "<pre id=\"MLabel_15\"  CINNERID=\"00559652\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:100px; left:12px; width:16px;\">\uE28C\uE2C6\uE292\uE2E7 </pre>\n" +
                "<pre id=\"TMLabel_1\"  CINNERID=\"00559707\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:12pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:100px; left:470px; width:16px;\">\uE271\uE2F5\uE2B7\uE276\uE2FB\uE2EB\uE275</pre>\n" +
                "<input id=\"Edit_1\"  CINNERID=\"00559649\"  CELEBM=\"JBX.2\"  class=\"TRzEdit\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute;color:#000000;font-size:11pt;font-family: '宋体'; top:103px; left:47px;  width:121px;\" value =\"云樱桃\">\n" +
                "<input id=\"MEdit_ZYH\"  CINNERID=\"00559703\"  CELEBM=\"ZYX.1\"  class=\"TRzEdit\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute;color:#000000;font-size:11pt;font-family: '宋体'; top:103px; left:544px;  width:90px;\" value =\"0181437\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id =\"_Page_\" class=\"TRzPanel\" style=\"position: relative;border:#000000 0px solid; height:885px;\">\n" +
                "<div id =\"Pnl_25\" class=\"TRzPanel\" style=\"position: absolute;border-left:#000000 1px solid;border-Right:#000000 1px solid; border-Top:#000000 1px solid; border-Bottom:#000000 1px solid; height:885px;width:96px;top:0px;left:532px; \">\n" +
                "<div id =\"RichEdit_2\"  CINNERID=\"00559674\"  CELEBM=\"NUL.0\"  class=\"MTRichViewEdit\"  contentEditAble=false style=\"position: absolute;text-indent:2em;background-color:white; writing-mode:tb-lr; height:849px; width:90px; top:8px; left:3px;\">\n" +
                "<div style=\" direction:ltr; WRITING-MODE: tb-lr\">\n" +
                "<font size=\"4\" color=\"#000000\" face=\"Menksoft \uE2CE\uE26C\uE32C\uE26C\uE2BB \uE308\uE27E\uE2E8\">\n" +
                "<div style=\"TEXT-ALIGN:justify; direction:ltr; WRITING-MODE: tb-lr\"><font face=\"Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8\"><b>&#57969;&#58101;&#58135;&#57982;&#58106;&#57974;&#58091;&#57976;&#58037; &#57997; &#58018;&#58044;&#58091;&#57975;&#58150;&#57974;&#58107;&#58123;&#57971;</b></font>&nbsp;&#57912;&nbsp;&nbsp;&#57969;&#58053;&#57975;&#58132;&#58135;&#57982;&#58131;&#57974;&#58037;&nbsp;&#58121;&#57993;&#58107;&#57993;&#58090;&#57964;&#57979;&nbsp;&#57969;&#58152;&#58074;&#57983;&#58141;&#58019; &nbsp;&#57910;&nbsp;&nbsp;&#58121;&#58001;&#58107;&#58001;&#58090;&#57964;&#57979;&nbsp;&#58121;&#58025;&#58039;&#57974;&#58150;&#57974;&#58077;&#58023; &nbsp;&#58109;&#57982;&#58048;&#58141;&#57979;&nbsp;&#57959;&#58135;&#57960;&#58064;&#57975;&#57979; &#58133;&#57982;&#58114;&#58001;&#58037; &#57997; &#58068;&#58026;&#58144;&#58001;&#58131;&#57974;&#58105; &#57986; &#58109;&#57964;&#57985;&#58141;&#57982;&#58150;&#57964;&#58092;&#58001;&#58107;&#58001;&#58037; &#57910; &#58133;&#57964;&#58090;&#57964;&#58037; &#58110;&#58001;&#58131;&#57964;&#58105; &#58001;&#58037; &#58083;&#57975;&#58101;&#58131;&#58001;&#58105; &#57986; &#57958;&#58151;&#57982;&#58107;&#58090;&#57964;&#58037;<font face=\"宋体\">&nbsp;</font>&#57910; &#58097;&#57964;&#58072;&#57964;&#58054;&#58002;&#58129; &#57986; &#58120;&#57974;&#58095;&#58113;&#57982;&#58132;&#58074;&#57975;&#58141;&#57997; &#57910; &#58121;&#58001;&#58044;&#58090;&#57964;&#58106;&#57964;&#58087; &#58133;&#58025;&#58051; &#58001;&#58037; &#57978;&#58107;&#58090;&#57964;&#58150;&#57964;&#58107;&#58131;&#57971; &#57986; &#57958;&#58074;&#57983;&#58092;&#58001;&#58107;&#58141;&#57997; &#57910; &#58137;&#57984;&#58152;&#58001;&#58091;&#57976;&#58037; &#58120;&#57964;&#58101;&#57982;&#58149; &#57986;&nbsp;&nbsp;&#58109;&#57964;&#57985;&#58141;&#57982;&#58150;&#57964;&#58092;&#58001;&#58107;&#58076;&#57997;&nbsp;&#58049;&#57975;&#58149;<font face=\"宋体\">&nbsp;</font>&#58178;&#57964;&#58131;&#57979;-5&nbsp;&#58178;&#57964;&#58048;&#58131;&#57964;&#58037;-3&nbsp;&#57969;&#58152;&#58131;&#57974;&#58039;&#57979;&nbsp;&#58018;&#58150;&#57974;&#58105;&nbsp;&#58084;&#58001;&#58144;&#57997;-13&nbsp;&#58145;&#57979;&nbsp;&#58018;&#58096;&#58053;&#57973;<font face=\"宋体\">&nbsp;</font>&#57911;</div>\n" +
                "</font>\n" +
                "</div>\n" +
                "\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id =\"Pnl_27\" class=\"TRzPanel\" style=\"position: absolute;border-Top:#000000 1px solid; border-Bottom:#000000 1px solid; height:885px;width:73px;top:0px;left:459px; \">\n" +
                "<pre id=\"MLabel_1\"  CINNERID=\"00559678\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:40px; left:4px; width:20px;\">\uE271\uE2F5\uE2B7\uE276\uE2FB\uE2EB\uE275 \uE310\uE296 \uE2FD\uE26C\uE2EC\uE291\uE2EE\uE301\uE26C\uE2B5 \uE28C\uE2B9\uE291\uE301\uE27B \uE238 </pre>\n" +
                "<div id =\"RichEdit_MYZD\"  CINNERID=\"00559669\"  CELEBM=\"RYJ.40\"  class=\"MTRichViewEdit\"  contentEditAble=false style=\"position: absolute;text-indent:2em;background-color:white; writing-mode:tb-lr; height:669px; width:60px; top:187px; left:0px;\">\n" +
                "<div style=\" direction:ltr; WRITING-MODE: tb-lr\">\n" +
                "<font size=\"4\" color=\"#000000\" face=\"Menksoft \uE2CE\uE26C\uE32C\uE26C\uE2BB \uE308\uE27E\uE2E8\">\n" +
                "<div style=\"TEXT-ALIGN:justify; direction:ltr; WRITING-MODE: tb-lr\">&#58098;&#57993;&#58044;&#58092;&#57993;&#58105; &#57969;&#58101;&#58039;&#57974;&#58107;&#58091;&#57973; &#57955;&#58145;&#57982;&#58037; &#57958;&#58044;&#58072;&#57964;&#58037; &#57989; &#57988;&#58041;&#57993;&#58113;&#57979; &#57912;&nbsp;&nbsp;&#58121;&#57993;&#58107;&#57993;&#58090;&#57964;&#57979;&nbsp;&#57969;&#58152;&#58074;&#57983;&#58077;&#58023;</div>\n" +
                "<div style=\"TEXT-ALIGN:justify; direction:ltr; WRITING-MODE: tb-lr\">&#57969;&#58156;&#58025;&#58152;&#58027;&#58059;&#57973; &#57969;&#58101;&#58039;&#57974;&#58107;&#58091;&#57973; &#57955;&#58145;&#57982;&#58037; &#57958;&#58044;&#58072;&#57964;&#58037; &#57989; &#57988;&#58041;&#57993;&#58113;&#57979; &#57912;&nbsp;<font face=\"宋体\">&#30505;&#26197;&#32508;&#21512;&#24449;</font></div>\n" +
                "</font>\n" +
                "</div>\n" +
                "\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id =\"Pnl_2\" class=\"TRzPanel\" style=\"position: absolute;border-left:#000000 1px solid;border-Top:#000000 1px solid; border-Bottom:#000000 1px solid; height:885px;width:459px;top:0px;left:0px; \">\n" +
                "<div id =\"Pnl_7\" class=\"TRzPanel\" style=\"position: absolute;border-left:#000000 1px solid;border-Right:#000000 1px solid; height:883px;width:266px;top:1px;left:1px; \">\n" +
                "<pre id=\"MLabel_57\"  CINNERID=\"00559696\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:10px; left:93px; width:20px;\">\uE2B1\uE26C\uE302\uE28D </pre>\n" +
                "<pre id=\"MLabel_55\"  CINNERID=\"00559677\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:10px; left:165px; width:20px;\">\uE28C\uE328\uE291\uE2EE\uE2FA\uE26C\uE2EE\uE301\uE26C\uE2B5 \uE271\uE301\uE276\uE2DD\uE2A7 </pre>\n" +
                "<pre id=\"MLabel_56\"  CINNERID=\"00559671\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:10px; left:59px; width:20px;\">\uE315\uE27E\uE2B7\uE26C\uE325 \uE291\uE2B5 \uE27A\uE2FB\uE2EA\uE26C\uE2F9 </pre>\n" +
                "<pre id=\"TMLabel_10\"  CINNERID=\"00559719\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:10px; left:129px; width:20px;\">\uE2A2\uE2C0\uE313\uE2AB\uE301\uE2AB\uE30B\uE276\uE2B5 </pre>\n" +
                "<pre id=\"TMLabel_18\"  CINNERID=\"00559727\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:10px; left:237px; width:20px;\">\uE28C\uE2E7 \uE2B3\uE291\uE30B\uE291\uE2E7 </pre>\n" +
                "<pre id=\"MLabel_58\"  CINNERID=\"00559715\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:10px; left:202px; width:20px;\">\uE266\uE31D\uE27E\uE2F9 </pre>\n" +
                "<pre id=\"TMLabel_4\"  CINNERID=\"00559684\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:10px; left:22px; width:20px;\">\uE284\uE2C6\uE292\uE2E7 \uE2B1\uE276\uE325\uE274 </pre>\n" +
                "<pre id=\"MLabel_62\"  CINNERID=\"00559668\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-weight:bold;font-size:11pt;font-family: '宋体'; top:36px; left:95px; width:16px;\">年龄:</pre>\n" +
                "<pre id=\"MLabel_63\"  CINNERID=\"00559705\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-weight:bold;font-size:11pt;font-family: '宋体'; top:37px; left:204px; width:16px;\">职业:</pre>\n" +
                "<pre id=\"TMLabel_19\"  CINNERID=\"00559695\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-weight:bold;font-size:11pt;font-family: '宋体'; top:67px; left:240px; width:16px;\">籍贯:</pre>\n" +
                "<pre id=\"TMLabel_11\"  CINNERID=\"00559646\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-weight:bold;font-size:11pt;font-family: '宋体'; top:69px; left:130px; width:16px;\">民族:</pre>\n" +
                "<pre id=\"MEdit_NL\"  CINNERID=\"00559657\"  CELEBM=\"ZYX.9\"  class=\"TMEdit\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:11pt;font-family: '楷体_GB2312'; top:73px; left:92px;  width:21px; height:280px;\">63岁</pre>\n" +
                "<pre id=\"TMLabel_5\"  CINNERID=\"00559688\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-weight:bold;font-size:11pt;font-family: '宋体'; top:74px; left:24px; width:16px;\">姓名:</pre>\n" +
                "<pre id=\"MEdit_JG\"  CINNERID=\"00559670\"  CELEBM=\"JBX.19\"  class=\"TMEdit\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:11pt;font-family: '楷体_GB2312'; top:74px; left:202px;  width:21px; height:279px;\">无</pre>\n" +
                "<pre id=\"MLabel_61\"  CINNERID=\"00559664\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-weight:bold;font-size:11pt;font-family: '宋体'; top:91px; left:60px; width:16px;\">性别:</pre>\n" +
                "<pre id=\"TMEdit_2\"  CINNERID=\"00559682\"  CELEBM=\"JBX.5\"  class=\"TMEdit\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:11pt;font-family: '楷体_GB2312'; top:104px; left:237px;  width:21px; height:289px;\">呼和浩特市赛罕区新建西街师大24号楼4单元6号</pre>\n" +
                "<pre id=\"TMEdit_4\"  CINNERID=\"00559721\"  CELEBM=\"JBX.8\"  class=\"TMEdit\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:11pt;font-family: '楷体_GB2312'; top:105px; left:128px;  width:21px; height:248px;\">蒙古族</pre>\n" +
                "<pre id=\"MLabel_60\"  CINNERID=\"00559694\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-weight:bold;font-size:11pt;font-family: '宋体'; top:105px; left:167px; width:16px;\">婚否:</pre>\n" +
                "<pre id=\"MEdit_xm\"  CINNERID=\"00559692\"  CELEBM=\"JBX.2\"  class=\"TMEdit\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:11pt;font-family: '楷体_GB2312'; top:112px; left:22px;  width:21px; height:249px;\">云樱桃</pre>\n" +
                "<pre id=\"MEdit_XB\"  CINNERID=\"00559659\"  CELEBM=\"JBX.3\"  class=\"TMEdit\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:11pt;font-family: '楷体_GB2312'; top:128px; left:57px;  width:21px; height:225px;\">女</pre>\n" +
                "<input id=\"MComboBox_2\"  CINNERID=\"00559680\"  CELEBM=\"NUL.0\"  class=\"TMComboBox\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:11pt;font-family: '楷体_GB2312'; width:21px; top:141px; left:163px;\"  value =\"已婚\">\n" +
                "<pre id=\"TMLabel_8\"  CINNERID=\"00559686\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:403px; left:92px; width:20px;\">\uE271\uE2F5\uE2B7\uE276\uE2FB\uE2EB\uE275 \uE310\uE2A3 \uE2FD\uE26C\uE2EC\uE291\uE2EE\uE301\uE26C\uE2B5 \uE315\uE26C\uE2E7 </pre>\n" +
                "<pre id=\"TMLabel_12\"  CINNERID=\"00559689\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:403px; left:165px; width:20px;\">\uE2B1\uE26C\uE281\uE313\uE26C\uE2C6\uE292\uE327\uE27E\uE30B\uE2A3 \uE315\uE27E\uE2B7\uE26C\uE325 </pre>\n" +
                "<pre id=\"TMLabel_6\"  CINNERID=\"00559654\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:403px; left:58px; width:20px;\">\uE2C1\uE26D\uE281\uE2EC\uE291\uE2FB\uE291\uE2FB\uE2E9\uE26A </pre>\n" +
                "<pre id=\"MLabel_59\"  CINNERID=\"00559720\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:403px; left:21px; width:20px;\">\uE2FD\uE26C\uE2EC\uE291\uE2E9\uE26A \uE2E1\uE26C\uE31D\uE26C\uE325 </pre>\n" +
                "<pre id=\"TMLabel_14\"  CINNERID=\"00559704\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:403px; left:201px; width:20px;\">\uE309\uE289\uE2EA\uE26C\uE317\uE27E\uE2EE\uE301\uE26C\uE2B5 \uE2D4\uE2AA\uE2F5\uE2AB\uE2B5 </pre>\n" +
                "<pre id=\"TMLabel_16\"  CINNERID=\"00559675\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:403px; left:239px; width:20px;\">\uE271\uE2C5\uE277\uE314\uE317\uE27E\uE2FA\uE276\uE2EF\uE301\uE276\uE2B5 \uE28C\uE2FA\uE26C\uE327\uE27E\uE2F9 </pre>\n" +
                "<pre id=\"TMLabel_20\"  CINNERID=\"00559681\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:403px; left:129px; width:20px;\">\uE271\uE2C5\uE277\uE314\uE317\uE27E\uE2B5 \uE28D \uE308\uE276\uE2AB\uE2DA\uE275 \uE321\uE27B \uE308\uE276\uE2F5\uE313\uE276\uE2EB\uE2FC\uE276\uE2EF\uE301\uE276\uE2B5 \uE315\uE26C\uE2E7 </pre>\n" +
                "<pre id=\"TMLabel_7\"  CINNERID=\"00559672\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-weight:bold;font-size:11pt;font-family: '宋体'; top:463px; left:60px; width:16px;\">工作单位:</pre>\n" +
                "<pre id=\"MLabel_64\"  CINNERID=\"00559676\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-weight:bold;font-size:11pt;font-family: '宋体'; top:479px; left:24px; width:16px;\">住址:</pre>\n" +
                "<pre id=\"TMLabel_13\"  CINNERID=\"00559728\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-weight:bold;font-size:11pt;font-family: '宋体'; top:494px; left:167px; width:16px;\">可靠性:</pre>\n" +
                "<pre id=\"TMLabel_15\"  CINNERID=\"00559700\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-weight:bold;font-size:11pt;font-family: '宋体'; top:501px; left:204px; width:16px;\">主诉人:</pre>\n" +
                "<pre id=\"TMLabel_17\"  CINNERID=\"00559647\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-weight:bold;font-size:11pt;font-family: '宋体'; top:508px; left:240px; width:16px;\">发病季节:</pre>\n" +
                "<pre id=\"TMEdit_3\"  CINNERID=\"00559699\"  CELEBM=\"JBX.16\"  class=\"TMEdit\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:11pt;font-family: '宋体'; top:515px; left:22px;  width:21px; height:351px;\">呼和浩特市赛罕区新建西街师大24号楼4单元6号</pre>\n" +
                "<pre id=\"TMEdit_1\"  CINNERID=\"00559662\"  CELEBM=\"ZYX.11\"  class=\"TMEdit\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:11pt;font-family: '宋体'; top:529px; left:58px;  width:21px; height:335px;\">退休移交第一批划账管理单位十</pre>\n" +
                "<pre id=\"TMLabel_9\"  CINNERID=\"00559656\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-weight:bold;font-size:11pt;font-family: '宋体'; top:534px; left:95px; width:16px;\">住院时间:</pre>\n" +
                "<input id=\"MComboBox_1\"  CINNERID=\"00559651\"  CELEBM=\"NUL.0\"  class=\"TMComboBox\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:11pt;font-family: '宋体'; width:21px; top:545px; left:163px;\"  value =\"基本可靠\">\n" +
                "<input id=\"MComboBox_3\"  CINNERID=\"00559658\"  CELEBM=\"NUL.0\"  class=\"TMComboBox\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:11pt;font-family: '宋体'; width:21px; top:552px; left:200px;\"  value =\"患者本人\">\n" +
                "<input id=\"MComboBox_4\"  CINNERID=\"00559714\"  CELEBM=\"NUL.0\"  class=\"TMComboBox\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:11pt;font-family: '宋体'; width:21px; top:574px; left:237px;\"  value =\"夏天\">\n" +
                "<pre id=\"TMLabel_21\"  CINNERID=\"00559687\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-weight:bold;font-size:11pt;font-family: '宋体'; top:593px; left:130px; width:16px;\">病历记录时间:</pre>\n" +
                "<pre id=\"MDateTimeEdit_zysj\"  CINNERID=\"00559650\"  CELEBM=\"ZYX.301\"  class=\"TMDateTimeEdit\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:11pt;font-family: '宋体'; width:21px; height:159px; top:600px; left:91px;\" >2020-07-17 10:23:35</pre>\n" +
                "<pre id=\"MDateTimeEdit_1\"  CINNERID=\"00559665\"  CELEBM=\"RYJ.16\"  class=\"TMDateTimeEdit\"  type=\"text\" readonly=\"readonly\" style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:11pt;font-family: '宋体'; width:21px; height:152px; top:690px; left:126px;\" >2020-07-17 11:30:09</pre>\n" +
                "</div>\n" +
                "<div id =\"Pnl_3\" class=\"TRzPanel\" style=\"position: absolute;border-Right:#000000 1px solid; height:883px;width:35px;top:1px;left:267px; \">\n" +
                "<div id =\"RichEdit_ZS\"  CINNERID=\"00559663\"  CELEBM=\"ZSX\"  class=\"MTRichViewEdit\"  contentEditAble=false style=\"position: absolute;text-indent:2em;background-color:white; writing-mode:tb-lr; height:849px; width:30px; top:8px; left:3px;\">\n" +
                "<div style=\" direction:ltr; WRITING-MODE: tb-lr\">\n" +
                "<font size=\"4\" color=\"#000000\" face=\"Menksoft \uE2CE\uE26C\uE32C\uE26C\uE2BB \uE308\uE27E\uE2E8\">\n" +
                "<div style=\"TEXT-ALIGN:justify; direction:ltr; WRITING-MODE: tb-lr\"><font face=\"Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8\"><b>&#58084;&#58001;&#58001;&#58105; &#58121;&#58001;&#58090;&#57964;&#58135;&#57982;&#58107;&#58123;&#57960;</b></font>&nbsp;&#57912;&nbsp;&#58136;&#57964;&#58053;&#58113;&#57964;&#58152;&#58106;&#57964;&#58089;&#57962;&nbsp;&#58145;&#57982;&#58037;&nbsp;&#58133;&#57982;&#58039;&#57974;&#58152;&#58123;&#57974;&#57979;&nbsp;&#58121;&#57993;&#58107;&#57993;&#58090;&#57964;&#57979;&nbsp;&#57969;&#58152;&#58074;&#57983;&#58141;&#58019;<font face=\"宋体\">&nbsp;</font><font face=\"宋体\">&#57910;</font>&nbsp;&#58121;&#58001;&#58107;&#58001;&#58090;&#57964;&#57979;&nbsp;&#58121;&#58025;&#58039;&#57974;&#58150;&#57974;&#58077;&#58023;&nbsp;&#58109;&#57982;&#58048;&#58141;&#57979;&nbsp;&#58120;&#57964;&#57979;&nbsp;&nbsp;&#58033;&#57982;&#58091;&#57973;&nbsp;&#58081;&#57964;&#58150;&#57964;&#58087;<font face=\"宋体\">&nbsp;</font>&#57911;</div>\n" +
                "</font>\n" +
                "</div>\n" +
                "\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id =\"Pnl_4\" class=\"TRzPanel\" style=\"position: absolute;border-Right:#000000 1px solid; height:883px;width:153px;top:1px;left:302px; \">\n" +
                "<div id =\"RichEdit_XBS\"  CINNERID=\"00559725\"  CELEBM=\"XBS\"  class=\"MTRichViewEdit\"  contentEditAble=false style=\"position: absolute;text-indent:2em;background-color:white; writing-mode:tb-lr; height:849px; width:150px; top:8px; left:0px;\">\n" +
                "<div style=\" direction:ltr; WRITING-MODE: tb-lr\">\n" +
                "<font size=\"4\" color=\"#000000\" face=\"Menksoft \uE2CE\uE26C\uE32C\uE26C\uE2BB \uE308\uE27E\uE2E8\">\n" +
                "<div style=\"TEXT-ALIGN:justify; direction:ltr; WRITING-MODE: tb-lr\"><font face=\"Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8\"><b>&#57969;&#58101;&#58039;&#57974;&#58107;&#58091;&#57973; &#58128;&#58006; &#58109;&#57964;&#58092;&#58001;&#58094;&#58113;&#57964;&#58037; &#58049;&#57965;&#57985;&#58131;&#57964;&#58105;</b></font>&nbsp;&#57912;&nbsp;&#57969;&#58053;&#57975;&#58132;&#58135;&#57982;&#58123;&#57974;&#58037;&nbsp;&#58033;&#57982;&#58091;&#57973;&nbsp;&#58081;&#57964;&#58150;&#57964;&#58087;&nbsp;&#57993;&#58037;&nbsp;&#57969;&#58101;&#57993;&#58038;&#57972;&#58064;&#57975;&#58123;&#57993;&#58152;&#58074;&#57975;&#57979;&nbsp;&#58142;&#57974;&#58131;&#57974;&#58150;&#57974;&#58141;&#58019;&nbsp;&#58138;&#58025;&#58131;&#57974;&#58150;&#57974;&#58095;&#58113;&#57974;&#58037;&nbsp;&#57959;&#58135;&#57960;&nbsp;&#58050;&#57994;&#58107;&#58141;&#58019;&nbsp;&#58121;&#57993;&#58107;&#57993;&#58090;&#57964;&#57979;&nbsp;&#57969;&#58152;&#58074;&#57983;&#58141;&#58019; &nbsp;&#57910;&nbsp;&nbsp;&#58121;&#58001;&#58107;&#58001;&#58090;&#57964;&#57979;&nbsp;&#58121;&#58025;&#58039;&#57974;&#58150;&#57974;&#58077;&#58023; &nbsp;&#58109;&#57982;&#58048;&#58141;&#57979;&nbsp;&#57978;&#58106;&#57974;&#58150;&#57974;&#58095;&#58113;&#57974;&#58037;&nbsp;&#58128;&#57997;&nbsp;&#57996;&#58048;&#58135;&#58001;&#58090;&#57964;&#57979;&nbsp;&#58049;&#57965;&#57985;&#58135;&#57964;&#58090;&#57964;&#58107;&#58131;&#57960;&nbsp;&#58049;&#57963;&nbsp;&#57969;&#58101;&#58135;&#57982;&#58106;&#57974;&#58091;&#57973;&nbsp;&#58064;&#57983;&#58095;&#58113;&#57974;&#58037;&nbsp;&#58018;&#58091;&#57975;&#57979;&nbsp;&#57910;&nbsp;&#58018;&#58041;&#58001;&#58131;&#58001;&#58149;&#58109;&#57982;&#58114;&#58124;&#58156;&#58099;&nbsp;&#58120;&#57964;&#57979;&nbsp;&#57969;&#58101;&#58135;&#57982;&#58106;&#57974;&#58091;&#57973;&nbsp;&#58136;&#57964;&#58113;&#57964;&#58105;&nbsp;&#58064;&#57983;&#58077;&#58023;&nbsp;&#58145;&#57982;&#58037;&nbsp;&#58121;&#57993;&#58113;&#57964;&#58131;&#57960;&nbsp;&#58097;&#57964;&#58037;&nbsp;&#57989;&nbsp;&#57969;&#58101;&#58039;&#57974;&#58107;&#58091;&#57973;&nbsp;&#58145;&#57982;&#58037;&nbsp;&#57969;&#58093;&#58028;&#58131;&#57974;&#58037;&nbsp;&#57969;&#58101;&#58039;&#57974;&#58107;&#58091;&#57973;&nbsp;&#58053;&#57965;&#58149;&nbsp;&#58126;&#57964;&#58101;&#58141;&#57982;&#58037;&nbsp;<font face=\"宋体\">&#65085;</font>&#58121;&#57993;&#58107;&#57993;&#58090;&#57964;&#57979;&nbsp;&#57969;&#58152;&#58074;&#57983;&#58077;&#58023;&nbsp;&#57969;&#58053;&#57975;&#58132;&#58135;&#57982;&#58037;<font face=\"宋体\">&#65086;</font>&nbsp;&#58053;&#57965;&#58149;&nbsp;&#57988;&#58041;&#57993;&#58113;&#57982;&#58106;&#57964;&#58141;&#58019;&nbsp;&#58097;&#57964;&#58037;&nbsp;&#57989;&nbsp;&#58109;&#57964;&#58106;&#57964;&#58090;&#57964;&#58037;&nbsp;&#58128;&#58006;&nbsp;&#57978;&#58150;&#57974;&#58141;&#58019;&nbsp;&#58109;&#57964;&#58092;&#57993;&#58053;&#57963; &#57911;&nbsp;&nbsp;&#57969;&#58053;&#57975;&#58132;&#58135;&#57982;&#58037;&nbsp;&#57989;&nbsp;&#58142;&#57964;&#58054;&#57994;&#58135;&#57960;&nbsp;&#58128;&#58006;&nbsp;&#58068;&#58026;&#58141;&#58027;&#58093;&#58028;&#57989;&nbsp;&#58068;&#58026;&#58113;&#57982;&#58077;&#58023;&nbsp;&#58049;&#57965;&#57985;&#58131;&#57964;&#58105;&nbsp;&#58120;&#57964;&#57979; &#57910;&nbsp;&nbsp;&#57988;&#58114;&#57989;&nbsp;&#57988;&#57993;&#58092;&#57993;&#58076;&#57989;&nbsp;&#58128;&#58006;&nbsp;&#58062;&#57964;&#58072;&#57964;&#58076;&#57989;&nbsp;&#58018;&#58091;&#57975;&#57979; &#57910;&nbsp;&nbsp;&#58018;&#58091;&#57973;&#58064;&#57975;&#58106;&#57971;&nbsp;&#58121;&#57993;&#58131;&#57989;&nbsp;&#57910;&nbsp;&#58018;&#58132;&#58074;&#57976;&#58037;&nbsp;&#58109;&#57982;&#58044;&#58091;&#57976;&#58037;&nbsp;&#58098;&#58025;&#58149;&nbsp;&#58120;&#57974;&#58095;&#58113;&#57979; &#57910;&nbsp;&#57978;&#58131;&#57974;&#58091;&#57976;&#58037;&nbsp;&#57988;&#58101;&#58131;&#57964;&#58090;&#57964;&#58037;&nbsp;&#57989;&nbsp;&#58127;&#57993;&#58150;&#57964;&#58113;&#57982;&#58105;&nbsp;&#57958;&#58039;&#57964;&#57979; &#57911;</div>\n" +
                "</font>\n" +
                "</div>\n" +
                "\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id =\"Pnl_26\" class=\"TRzPanel\" style=\"position: absolute;border-left:#000000 1px solid;border-Top:#000000 1px solid; border-Bottom:#000000 1px solid; height:885px;width:71px;top:0px;left:699px; \">\n" +
                "<pre id=\"MLabel1\"  CINNERID=\"00559679\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:40px; left:6px; width:20px;\">\uE271\uE2F5\uE2B7\uE276\uE2FB\uE2EB\uE275 \uE267\uE317\uE268 \uE2E1\uE26C\uE328\uE291\uE2EE\uE301\uE26C\uE2B5 \uE28C\uE2B9\uE291\uE301\uE27B \uE238 </pre>\n" +
                "<div id =\"RichEdit_6\"  CINNERID=\"00559712\"  CELEBM=\"RYJ.40\"  class=\"MTRichViewEdit\"  contentEditAble=false style=\"position: absolute;text-indent:2em;background-color:white; writing-mode:tb-lr; height:663px; width:60px; top:197px; left:4px;\">\n" +
                "<div style=\" direction:ltr; WRITING-MODE: tb-lr\">\n" +
                "<font size=\"4\" color=\"#000000\" face=\"Menksoft \uE2CE\uE26C\uE32C\uE26C\uE2BB \uE308\uE27E\uE2E8\">\n" +
                "<div style=\"TEXT-ALIGN:justify; direction:ltr; WRITING-MODE: tb-lr\">&#58098;&#57993;&#58044;&#58092;&#57993;&#58105; &#57969;&#58101;&#58039;&#57974;&#58107;&#58091;&#57973; &nbsp;&#57959;&#58135;&#57960; &#58081;&#57964;&#58152;&#58001;&#58094;&#58113;&#57964;&#58037; &#57988;&#58041;&#57993;&#58113;&#57979; &#57912; &nbsp;&#58121;&#57993;&#58107;&#57993;&#58090;&#57964;&#57979;&nbsp;&#57969;&#58152;&#58074;&#57983;&#58077;&#58023;</div>\n" +
                "<div style=\"TEXT-ALIGN:justify; direction:ltr; WRITING-MODE: tb-lr\">&#57969;&#58156;&#58025;&#58152;&#58027;&#58059;&#57973; &#57969;&#58101;&#58039;&#57974;&#58107;&#58091;&#57973;&nbsp;&nbsp;&#57959;&#58135;&#57960;&nbsp;&#58081;&#57964;&#58152;&#58001;&#58094;&#58113;&#57964;&#58037;&nbsp; &#57988;&#58041;&#57993;&#58113;&#57979; &#57912;&nbsp;<font face=\"宋体\">&#30505;&#26197;&#32508;&#21512;&#24449;</font></div>\n" +
                "</font>\n" +
                "</div>\n" +
                "\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id =\"Pnl_17\" class=\"TRzPanel\" style=\"position: absolute;border-Right:#000000 1px solid; border-Top:#000000 1px solid; border-Bottom:#000000 1px solid; height:885px;width:44px;top:0px;left:931px; \">\n" +
                "<pre id=\"MLabel_24\"  CINNERID=\"00559723\"  CELEBM=\"NUL.0\"  class=\"TMLabel\"  style=\"position: absolute; writing-mode:tb-lr;color:#000000;font-size:14pt;font-family: 'Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8'; top:472px; left:9px; width:20px;\">\uE271\uE2F5\uE317\uE27B \uE321\uE27E\uE2B5 \uE2E1\uE26C\uE325 \uE291\uE2B5 \uE2A2\uE301\uE2AB\uE2E8 \uE238 </pre>\n" +
                "<img id =\"Image_1\"  CINNERID=\"00559691\"  CELEBM=\"D02.4\"  class=\"TImage\"  style=\"position: absolute; height:100px; width:21px; top:610px; left:9px;\" src=\"data:image/png;base64,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\">\n" +
                "</div>\n" +
                "<div id =\"Pnl_6\" class=\"TRzPanel\" style=\"position: absolute;border-left:#000000 1px solid;border-Right:#000000 1px solid; border-Top:#000000 1px solid; border-Bottom:#000000 1px solid; height:885px;width:161px;top:0px;left:770px; \">\n" +
                "<div id =\"RichEdit_3\"  CINNERID=\"00559645\"  CELEBM=\"NUL.0\"  class=\"MTRichViewEdit\"  contentEditAble=false style=\"position: absolute;text-indent:2em;background-color:white; writing-mode:tb-lr; height:849px; width:150px; top:8px; left:5px;\">\n" +
                "<div style=\" direction:ltr; WRITING-MODE: tb-lr\">\n" +
                "<font size=\"4\" color=\"#000000\" face=\"Menksoft \uE2CE\uE26C\uE32C\uE26C\uE2BB \uE308\uE27E\uE2E8\">\n" +
                "<div style=\"TEXT-ALIGN:justify; direction:ltr; WRITING-MODE: tb-lr\"><font face=\"Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8\"><b>&#57969;&#58101;&#58039;&#57974;&#58107;&#58091;&#57973; &#57959;&#58135;&#57960; &#58081;&#57964;&#58152;&#58001;&#58094;&#58113;&#57964;&#58037; &#57969;&#58101;&#58135;&#57979; &#58145;&#57982;&#58037; &#58137;&#57984;&#58090;&#57964;&#58054;&#58002;&#58152;&#57979;&nbsp;</b></font>&nbsp;&#57912;&nbsp;</div>\n" +
                "<div style=\"TEXT-ALIGN:justify; direction:ltr; WRITING-MODE: tb-lr\"><font face=\"宋体\">&nbsp;1.</font>&#58018;&#58152;&#58077;&#58028;&#58107;&#58141;&#57982;&#58106;&#57974;&#58037; &#57969;&#58101;&#58135;&#57982;&#58106;&#57974;&#58091;&#57973; &#58064;&#57983;&#58077;&#58023; &#58133;&#57982;&#58076;&#58001;&#58107;&#58123;&#57964;&#57979;<font face=\"宋体\">&nbsp;</font>&#57911;</div>\n" +
                "<div style=\"TEXT-ALIGN:justify; direction:ltr; WRITING-MODE: tb-lr\"><font face=\"宋体\">&nbsp;2.</font>&#57978;&#58131;&#57974;&#58091;&#57975;&#58037; &#57996;&#58100;&#58131;&#57964;&#58090;&#57964;&#58037; &nbsp;&#57910; &#58142;&#57964;&#58054;&#58002;&#58131;&#57964;&#58105; &#58098;&#58012;&#58149; &nbsp;&#57912; &#58068;&#58026;&#58048;&#58131;&#57997; &#58127;&#58001;&#58114;&#58001;&#58107;&#57982;&#58087; &#58049;&#57963; &#58066;&#58001;&#58152;&#58135;&#57960; &#58062;&#57964;&#58106;&#57964;&#58092;&#58001;&#58037; &#58133;&#57982;&#58039;&#57964;&#58152;&#58131;&#57964;&#57979; &#57978;&#58131;&#57974;&#58091;&#57976;&#58037; &#57996;&#58101;&#58131;&#57964;&#58090;&#57964;&#58037; &#57986; &#58133;&#57974;&#58091;&#57975;&#58152;&#58106;&#57974;&#58141;&#57997; &#57910;&nbsp;&nbsp;&#58109;&#57982;&#58044;&#58091;&#57976;&#58037; &#57910;&nbsp;&#58109;&#57982;&#58044;&#58091;&#57975;&#58077;&#58023;&nbsp;&#58128;&#58006;&nbsp;&#58064;&#57983;&#58107;&#58053;&#57975;&#58149;&nbsp;&#57978;&#58131;&#57974;&#58091;&#57976;&#58037;&nbsp;&#57988;&#58101;&#58131;&#57964;&#58090;&#57964;&#58037;&nbsp;&nbsp;&#57989;&nbsp;&#58138;&#57993;&#57985;&#58105;&nbsp;&#57986;&#58138;&#58001;&#58074;&#57983;&#58114;&#58131;&#57964;&#57979;&nbsp;&#57969;&#58131;&#58106;&#57974;&#58141;&#58019; &#57910;&nbsp; &#58049;&#57975;&#58143;&#57972; &#58145;&#57982;&#58037; &#58068;&#58026;&#58131;&#57974;&#58107;&#58091;&#57975;&#58091;&#57976;&#58037; &#58128;&#57997; &#57996;&#58152;&#58001;&#58107;&#58135;&#57964;&#58092;&#58001;&#58107;&#58141;&#57997; &#57910; &#58142;&#57964;&#58131;&#57964;&#58150;&#57964;&#58141;&#57997; &#58138;&#58025;&#58131;&#57974;&#58150;&#57974;&#58077;&#58023; &#57969;&#58135;&#57971; &#58064;&#57983;&#58135;&#57982;&#58144;&#57974;&#58141;&#57997;&nbsp;&#58033;&#57964;&#58099;&nbsp;&#58109;&#57964;&#58106;&#57974;&#58093;&#58028;&#58037;&nbsp;&#57988;&#58152;&#58135;&#57982;&#58037;&nbsp;&#58128;&#58006;&nbsp;&#58110;&#58001;&#58053;&#57983;&#58106;&#57964;&#58038;&#57962; &#57911;&nbsp;</div>\n" +
                "</font>\n" +
                "</div>\n" +
                "\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id =\"Pnl_5\" class=\"TRzPanel\" style=\"position: absolute;border-Top:#000000 1px solid; height:885px;width:71px;top:0px;left:628px; \">\n" +
                "<div id =\"RichEdit_1\"  CINNERID=\"00559643\"  CELEBM=\"NUL.0\"  class=\"MTRichViewEdit\"  contentEditAble=false style=\"position: absolute;text-indent:2em;background-color:white; writing-mode:tb-lr; height:849px; width:60px; top:8px; left:4px;\">\n" +
                "<div style=\" direction:ltr; WRITING-MODE: tb-lr\">\n" +
                "<font size=\"4\" color=\"#000000\" face=\"Menksoft \uE2CE\uE26C\uE32C\uE26C\uE2BB \uE308\uE27E\uE2E8\">\n" +
                "<div style=\"TEXT-ALIGN:justify; direction:ltr; WRITING-MODE: tb-lr\"><font face=\"Menksoft \uE315\uE26C\uE2EA\uE26C\uE2B5 \uE308\uE27E\uE2E8\"><b>&#57969;&#58101;&#58039;&#57974;&#58107;&#58091;&#57973; &#57959;&#58135;&#57960; &#58081;&#57964;&#58152;&#58001;&#58094;&#58113;&#57964;&#58037; &#58049;&#57965;&#57985;&#58131;&#57964;&#58105;</b></font>&nbsp;&#57912;&nbsp;&nbsp;&#57969;&#58053;&#57975;&#58132;&#58135;&#57982;&#58123;&#57974;&#58037;&nbsp;&#58066;&#58001;&#58053;&#57980;&nbsp;&#58145;&#57982;&#58037;&nbsp;&#57996;&#58135;&#57982;&#58149;&nbsp;&#57959;&#58135;&#57960;&nbsp;&#58050;&#58002;&#58107;&#58141;&#57997;&nbsp;&nbsp;&#58018;&#58053;&#57975;&#58149;&nbsp;&#58001;&#58037;&nbsp;&#58121;&#58001;&#58149;&#57962;&nbsp;&#57959;&#58135;&#57960;&nbsp;&#58050;&#58002;&#58107;&#58141;&#57997;&#57969;&#58101;&#58039;&#57974;&#58107;&#58091;&#57973;&nbsp;&#57959;&#58135;&#57960;&nbsp;&#58081;&#57964;&#58152;&#58076;&#57997;&nbsp;&#57986;&nbsp;&#58115;&#57964;&#58090;&#57964;&#58152;&#58131;&#57964;&#58141;&#57997;<font face=\"宋体\">&nbsp;</font>&#57910;&nbsp;&#58120;&#57974;&#58091;&#57975;&#58131;&#58019;&nbsp;&#58120;&#57974;&#58111;&nbsp;&#58001;&#58037;&nbsp;&#57969;&#58101;&#58135;&#57979;&nbsp;&#58145;&#57982;&#58037;&nbsp;&#58136;&#57964;&#58074;&#57983;&#58143;&#57962;&nbsp;&#58049;&#57975;&#58149;&nbsp;&#57969;&#58101;&#58039;&#57974;&#58107;&#58091;&#57973;&nbsp;&#57959;&#58135;&#57960;&nbsp;&#58081;&#57964;&#58152;&#58053;&#57963;<font face=\"宋体\">&nbsp;</font>&#57911;</div>\n" +
                "</font>\n" +
                "</div>\n" +
                "\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id =\"_PageFooter_\" class=\"TRzPanel\" style=\"position: relative;border:#000000 0px solid; height:0px;\">\n" +
                "</div>\n" +
                "</div>\n" +
                " </body> \n" +
                " </html> ";
        String file = "C:\\Users\\ASUS\\Desktop\\test\\24小时入出院.html";
        string2File(htmlString,file);
    }
}
