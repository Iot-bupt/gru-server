package com.sumory.gru.spear.common;

import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class MsgUtil {

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

    public static boolean GenerateImage(String imgStr){
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

            String imgFilePath = "G://test.txt";
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch (Exception e){
            return false;
        }
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

}
