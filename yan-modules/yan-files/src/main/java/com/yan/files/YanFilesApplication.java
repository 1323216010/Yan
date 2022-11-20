package com.yan.files;

import com.yan.common.security.annotation.EnableCustomConfig;
import com.yan.common.security.annotation.EnableRyFeignClients;
import com.yan.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 文件模块
 *
 * @author yan
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class YanFilesApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YanFilesApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  文件模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
            " .-------.       ____     __        \n" +
            " |  _ _   \\      \\   \\   /  /    \n" +
            " | ( ' )  |       \\  _. /  '       \n" +
            " |(_ o _) /        _( )_ .'         \n" +
            " | (_,_).' __  ___(_ o _)'          \n" +
            " |  |\\ \\  |  ||   |(_,_)'         \n" +
            " |  | \\ `'   /|   `-'  /           \n" +
            " |  |  \\    /  \\      /           \n" +
            " ''-'   `'-'    `-..-'              ");
    }
}
