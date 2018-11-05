package com.sumory.gru.spear.common;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class MsgUtil {

    /**
     * 保存四种文件的方法
     * @param Message
     */
    public static void saveStringFile(String Message){
        try {
            FileWriter fileWriter = new FileWriter("G://StringFile.dat");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void saveFile(InputStream inputStream) throws IOException{
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File("G://test.txt")));
        byte[] bytes = new byte[1024];
        while (bufferedInputStream.read(bytes) != -1){
            bufferedOutputStream.write(bytes,0,1000);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }

    public static void saveImageFile(InputStream image){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("G://image.dat"));
            fileOutputStream.write(image.read());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void saveAudioFile(InputStream audioFile){
        try {
            FileOutputStream outputStream = new FileOutputStream(new File("G://audio.dat"));
            outputStream.write(audioFile.read());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void saveVidioFile(InputStream vidioFile){
        try {
            FileOutputStream outputStream = new FileOutputStream(new File("G://vidio.dat"));
            outputStream.write(vidioFile.read());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 处理图片的方法
     * @param file
     * @return
     */
    public static byte[] loadImage(File file){
        byte[] data = null;
        FileInputStream fin = null;
        ByteArrayOutputStream bout = null;
        try{
            fin = new FileInputStream(file);
            bout = new ByteArrayOutputStream((int)file.length());
            byte[] buffer = new byte[1024 * 1024];
            int len = -1;
            while ((len = fin.read(buffer)) != -1){
                bout.write(buffer,0,len);
            }
            data = bout.toByteArray();  //将图片转换为比特数组
            fin.close();
            bout.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    /*
    将比特数组转换为字符串
     */
    public static String byteToString(byte[] data){
        String dataString = null;
        try {
            dataString = new String(data,"ISO-8859-1");
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataString;
    }

    /*
    压缩字符串
     */
    public static String compress(String data){
        String finalData = null;
        try{
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            GZIPOutputStream gout = new GZIPOutputStream(bout);
            gout.write(data.getBytes());
            gout.finish();
            gout.close();
            finalData = bout.toString("ISO-8859-1");
        }catch (Exception e){
            e.printStackTrace();
        }
        return finalData;
    }

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
    public static void DeleteFile(){
        int MsgContentType = 0;
        String filename = "";
        switch (MsgContentType){
            case 0:filename = "G://StringFile.dat";break;
            case 1:filename = "G://image.dat";break;
            case 2:filename = "G://audio.dat";break;
            case 3:filename = "G://vidio.dat";break;
            default:break;
        }
        File file = new File(filename);
        if(!file.exists()){
            System.out.println("删除文件失败：" + filename + "不存在！");
        }else {
            file.deleteOnExit();
        }
    }

}
