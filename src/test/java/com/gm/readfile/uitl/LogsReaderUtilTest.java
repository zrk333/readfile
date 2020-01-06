package com.gm.readfile.uitl;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/1/6
 */
public class LogsReaderUtilTest {

    @Test
    public void readFromFile(){
        String str = LogsReaderUtil.readFromFile("D:\\server.log");
        str.replaceAll("\n  ","");
        str.replaceAll("\n}","}");
//        LogsReaderUtil.writeIntoFile(str,"D:\\server.log",false);
        String regex = "[param]";
        String[] splitStr = str.split("\n");
        StringBuffer sb = new StringBuffer();
        try{
            for(String s : splitStr){
                if(StringUtils.isNotEmpty(s) && containsKeyWord(s,"param","SetInfoController","lessonid")) {
                    if(s.contains(regex)){
                        s = s.substring(s.indexOf(regex)+regex.length());
                        System.out.println(s);
                    }
                    sb.append(s + "\n");
                }
            }
        } catch (Exception e){
        }
//        System.out.println(sb.toString());
    }

    /**
     * 检查某字符串是否包含关键字
     * @param str       字符串
     * @param keywords  关键字数组
     * @return
     */
    private boolean containsKeyWord(String str,String ... keywords) {
        boolean isContainsK = true;
        for(String keyword : keywords){
            if(!str.contains(keyword)){
                isContainsK = false;
                break;
            }
        }
        return isContainsK;
    }

}