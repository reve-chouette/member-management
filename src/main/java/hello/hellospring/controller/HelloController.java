package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
    }

    /* MVC 방식 */
    @GetMapping("hello-mvc") //localhost:8000
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-spring")
    @ResponseBody // return the data directly into the body part of http. HttpMessageConverter operates instead of viewResolver.
    public String helloString(@RequestParam("name") String name){
        return "hello"+name; //In this case, only "hello spring" is printed on the browser.
    }

    /* API 방식(JSON) */
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name=name;
        }
    }
}
