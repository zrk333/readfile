package com.gm.readfile.uitl;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class LogsReaderUtil {
     
    /**
     * 
     * @param filePath      文件路径的字符串表示形式
     * @param keyWords      查找包含某个关键字的信息：非null为带关键字查询；null为全文显示
     * @return      当文件存在时，返回字符串；当文件不存在时，返回null
     */
    public static String readFromFile(String filePath, String ... keyWords){
        StringBuffer stringBuffer = null;
        File file = new File(filePath);
        if(file.exists()){
            stringBuffer = new StringBuffer();
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;
            String temp = "";
            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                while((temp = bufferedReader.readLine()) != null){
                    if(keyWords.length == 0){
                        stringBuffer.append(temp + "\n");
                    }else{
                        boolean isContainsK = true;
                        for(String keyword : keyWords){
                            if(!temp.contains(keyword)){
                                isContainsK = false;
                            }
                        }
                        if(isContainsK){
                            stringBuffer.append(temp + "\n");
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                log.error(e.getMessage(), e);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }finally{
                try {
                    fileReader.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        if(stringBuffer == null){
            return null;
        }else{
            return stringBuffer.toString();
        }
         
    }
     
    /**
     * 将指定字符串写入文件。如果给定的文件路径不存在，将新建文件后写入。
     * @param logStr       要写入文件的字符串
     * @param filePath      文件路径的字符串表示形式，目录的层次分隔可以是“/”也可以是“\\”
     * @param isAppend      true：追加到文件的末尾；false：以覆盖原文件的方式写入
     */        
      
    public static boolean writeIntoFile(String logStr, String filePath, boolean isAppend){
        boolean isSuccess = true;
        //如有则将"\\"转为"/",没有则不产生任何变化
        String filePathTurn = filePath.replaceAll("\\\\", "/");
        //先过滤掉文件名
        int index = filePathTurn.lastIndexOf("/");
        String dir = filePathTurn.substring(0, index);
        //创建除文件的路径
        File fileDir = new File(dir);
        fileDir.mkdirs();
        //再创建路径下的文件
        File file = null;
        try {
            file = new File(filePath);
            file.createNewFile();
        } catch (IOException e) {
            isSuccess = false;
            log.error(e.getMessage(), e);
        }
        //将logs写入文件
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, isAppend);
            fileWriter.write(logStr);
            fileWriter.flush();
        } catch (IOException e) {
            isSuccess = false;
            log.error(e.getMessage(), e);
        } finally{
            try {
                fileWriter.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
         
        return isSuccess;
    }
    /**
     * 创建文件，如果该文件已存在将不再创建（即不起任何作用）
     * @param filePath       要创建文件的路径的字符串表示形式，目录的层次分隔可以是“/”也可以是“\\”
     * @return      创建成功将返回true；创建不成功则返回false
     */
    public static boolean createNewFile(String filePath){
        boolean isSuccess = true;
        //如有则将"\\"转为"/",没有则不产生任何变化
        String filePathTurn = filePath.replaceAll("\\\\", "/");
        //先过滤掉文件名
        int index = filePathTurn.lastIndexOf("/");
        String dir = filePathTurn.substring(0, index);
        //再创建文件夹
        File fileDir = new File(dir);
        isSuccess = fileDir.mkdirs();
        //创建文件
        File file = new File(filePathTurn);
        try {
            isSuccess = file.createNewFile();
        } catch (IOException e) {
            isSuccess = false;
            log.error(e.getMessage(), e);
        }
 
        return isSuccess;
    }
}