package com.bookdepository.controller;

import com.bookdepository.controller.main.Attributes;
import com.bookdepository.model.Addresses;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/addresses")
public class AddressesCont extends Attributes {

    @GetMapping
    public String Addresses(Model model) {
        AddAttributesAddresses(model);
        return "addresses";
    }

    @PostMapping("/add")
    public String AddressesAdd(@RequestParam String address) {
        addressesRepo.save(new Addresses(address));
        return "redirect:/addresses";
    }

    @PostMapping("/edit/{id}")
    public String AddressesEdit(@RequestParam String address, @PathVariable Long id) {
        Addresses addresses = addressesRepo.getReferenceById(id);
        addresses.setAddress(address);
        addressesRepo.save(addresses);
        return "redirect:/addresses";
    }

    @GetMapping("/delete/{id}")
    public String AddressesDelete(@PathVariable Long id) {
        addressesRepo.deleteById(id);
        return "redirect:/addresses";
    }
}
