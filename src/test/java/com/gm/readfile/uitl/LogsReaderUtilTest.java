package com.gm.readfile.uitl;

import org.junit.Test;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/1/6
 */
public class LogsReaderUtilTest {

    @Test
    public void readFromFile(){
        String str = LogsReaderUtil.readFromFile("D:\\server.log","SetInfoController","param","lessonid");
        LogsReaderUtil.writeIntoFile(str,"D:\\server1.log",false);

    }

}