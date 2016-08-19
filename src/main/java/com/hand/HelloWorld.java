package com.hand;

import com.hand.pojo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class HelloWorld {
//framework will inject attribute into model automatically ,also ,we can use httpRequest
    @RequestMapping("/helloworld")
    public String sayHello(HttpServletRequest request, @RequestHeader("Accept-Encoding") String encoding, Customer customer){
        String name= (String) request.getParameter("name");
        System.out.println(name+"  hello, auto-filling pojo");
        return "success";
    }
//test of modelmap
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }

    @RequestMapping(value = "/Customer", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("student", "command", new Customer());
    }

    @RequestMapping(value = "/addCustomer")
    public String addStudent(@ModelAttribute("SpringWeb")Customer customer,
                             ModelMap model) {
        model.addAttribute("name", customer.getName());
        model.addAttribute("age", customer.getAge());
        model.addAttribute("id", customer.getId());
        return "hello";
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:finalPage";
    }

    @RequestMapping(value = "/finalPage", method = RequestMethod.GET)
    public String finalPage() {
        return "finalPage";
    }

    //test redirect to static page
    @RequestMapping("/static")
    public String staticPage(){
        return "redirect:/views/staticPage.html";
    }

//    here is the test of exception handler,SpringException.class need to be defined by you.
    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    @ExceptionHandler({SpringException.class})
    public String addCustomer( @ModelAttribute("HelloWeb")Customer student,
                              ModelMap model) {
        if(student.getName().length() < 5 ){
            throw new SpringException("Given name is too short");
        }else{
            model.addAttribute("name", student.getName());
        }
        if( student.getAge() < 10 ){
            throw new SpringException("Given age is too low");
        }else{
            model.addAttribute("age", student.getAge());
        }
        model.addAttribute("id", student.getId());
        return "hello";
    }
}
