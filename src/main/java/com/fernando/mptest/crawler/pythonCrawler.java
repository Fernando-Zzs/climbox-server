package com.fernando.mptest.crawler;

import org.junit.Test;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

@Component
public class pythonCrawler {

    //十分钟一次 600000
    @Scheduled(fixedRate = 6000)
    public void crawler(){
        try {
            System.out.println("In java : python crawler run");
            //这个方法是类似隐形开启了命令执行器，输入指令执行python脚本
            String command = " python " +
                    "E:\\Github\\climbox-server1\\src\\main\\resources\\pythonScript\\" +
                    "load.py";


            Process process = Runtime.getRuntime()
                    .exec(command);

            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String result = input.readLine();
            input.close();
            ir.close();
            int re = process.waitFor();
            System.out.println(result);


        } catch (IOException | InterruptedException e) {
            System.out.println("调用python脚本并读取结果时出错：" + e.getMessage());
        }
    }

}
