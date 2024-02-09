package com.sametkula.webBlog.controller;


import com.sametkula.webBlog.dto.CreateAccountRequest;
import com.sametkula.webBlog.dto.UpdateAccountRequest;
import com.sametkula.webBlog.services.MVCService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MvcController {

    private final MVCService mvcService;

    public MvcController(MVCService mvcService) {
        this.mvcService = mvcService;
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("AllUsers", mvcService.getAllUsers());

        return "index";
    }
    @GetMapping("/profile/{id}")
    public String account(Model model, @PathVariable String id) {

        model.addAttribute("user", mvcService.getUserById(id));

        return "profile";
    }

    @GetMapping("/myprofile")
    public String detailedAccount(Model model) {

        return "MyProfile";
    }
    @PostMapping("/profile")
    public String createAccount(Model model, @RequestBody CreateAccountRequest req) throws Exception {

        model.addAttribute("user", mvcService.createUser(req));

        System.out.println(model.getAttribute("user"));

        return "profile";
    }
    @PutMapping("/profile")
    public String updateAccount(Model model, @RequestBody UpdateAccountRequest req) {

        model.addAttribute("userUpdated", mvcService.updateUser(req));

        return "profile";
    }
    @DeleteMapping("/profile/{id}")
    public String deleteAccount(Model model, @PathVariable String id) {

        mvcService.deleteUser(id);

        return "profile";
    }
}
