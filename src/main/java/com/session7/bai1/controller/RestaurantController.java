package com.session7.bai1.controller;

import com.session7.bai1.model.RestaurantProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/merchant")
public class RestaurantController {

    @GetMapping("/update-profile")
    public String showForm(Model model) {
        model.addAttribute("restaurantProfile", new RestaurantProfile());
        return "profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("restaurantProfile") RestaurantProfile profile, Model model) {
        model.addAttribute("profileData", profile);
        System.out.println("Lưu thành công quán: " + profile.getName());
        return "success";
    }
}

// http://localhost:8080/merchant/update-profile