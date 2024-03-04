package com.longfish.exception;

import com.longfish.pojo.Result;
import com.longfish.utils.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.File;
import java.time.LocalDateTime;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex){

        File file = new File("logs\\exception\\exceptions.txt");

        ex.printStackTrace();

        Logger.log("\n发生错误！\n",file);
        Logger.log("时间："+LocalDateTime.now()+"\n",file);
        Logger.log("错误信息："+ex.getMessage()+"\n",file);

        StackTraceElement[] st = ex.getStackTrace();
        Logger.log("堆栈信息：\n",file);
        for (int i = 0; i < st.length; i++) {
            Logger.log(ex.getStackTrace()[i].toString()+"\n",file);
        }

        return Result.error("发生未知错误，请联系管理员");

    }
}
