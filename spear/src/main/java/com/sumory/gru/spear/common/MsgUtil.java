package com.sumory.gru.spear.common;

import sun.misc.BASE64Decoder;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
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

    public static boolean GenerateFile(String file,String filename) throws SQLException {
        if (file == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(file);
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


    /**
     * 处理下载完成后文件的删除
     */

}

