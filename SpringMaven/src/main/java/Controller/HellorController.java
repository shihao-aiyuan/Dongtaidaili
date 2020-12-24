package Controller;

import excception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.transform.Result;

/**
 * @author shihao
 * @create 2019-10-18 14:19
 */
@Controller
@RequestMapping("hello")
public class HellorController {

    @RequestMapping("say")
    public  String  sayhellor(){
        return "hellor";
    }


    @GetMapping("/test")
    public String test(String s){
        if(s==null){
            throw new MyException(500,"参数不能为空");
        }

        int a=10;
        int b=a/0;
        return  a+"我不能除以0";

    }
}
