package com.sumory.gru.spear.common;

import sun.misc.BASE64Decoder;

import java.io.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;


public class MsgUtil {

    public static void saveFile(InputStream inputStream) throws IOException{
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File("G://test.gif")));
        byte[] bytes = new byte[1024];
        while (bufferedInputStream.read(bytes) != -1){
            bufferedOutputStream.write(bytes,0,1000);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }

    public static boolean GenerateFile(String imgStr,String filename){
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i<b.length;++i){
                if (b[i] < 0){
                    b[i] +=256;
                }
            }

            String FilePath = "E://GruTest//" + filename; //文件路径可以随时修改为可保存的路径
            OutputStream out = new FileOutputStream(FilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static List<String> readToString(String filename) throws IOException{
        List<String> lines=new ArrayList<String>();
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"));
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        return lines;
    }

    /*
    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }*/

    /**
     * 处理前端触发下载请求的方法
     * @return
     */
    public static OutputStream downloadFile(){
        OutputStream out = null;
        int MsgContentType = 0;
        String file = "";
        switch (MsgContentType){
            case 0:file = "G://StringFile.dat";break;
            case 1:file = "G://image.dat";break;
            case 2:file = "G://audio.dat";break;
            case 3:file = "G://vidio.dat";break;
            default:break;
        }
        try{
            FileInputStream fileInputStream = new FileInputStream(file);

            byte buffer[] = new byte[1024*1024];
            int len = 0;
            while ((len = fileInputStream.read(buffer)) > 0){
                out.write(buffer,0,len);
            }
            fileInputStream.close();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return out;
    }

    /**
     * 处理下载完成后文件的删除
     */

}

