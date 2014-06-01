package com.densmolov.controller;

import com.densmolov.entity.NamePhone;
import com.densmolov.service.PhoneBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Controller
public class PhoneBookController {

    private final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    @Autowired
    private PhoneBookService phoneBookService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "forward:/pages/index.html";
    }

    @RequestMapping("/index")
    public String listNamePhones(Map<String, Object> map) {
        map.put("contact", new NamePhone());
        map.put("contactList", phoneBookService.listNamePhones());
        return "redirect:/";
    }

    @RequestMapping(value = "/showData", method = RequestMethod.GET)
    public @ResponseBody
    List<NamePhone> listNamePhones() {
        return phoneBookService.listNamePhones();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNamePhone(@Valid NamePhone namePhone, BindingResult result) {
        if (!result.hasErrors()) {
            phoneBookService.addNamePhone(namePhone);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public @ResponseBody boolean getFlag() {
        return phoneBookService.getFlag();
    }

}
