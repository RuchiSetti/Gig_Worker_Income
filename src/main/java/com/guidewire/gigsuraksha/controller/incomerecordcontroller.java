package com.guidewire.gigsuraksha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.incomerecordservice;

import java.time.LocalDate;
import java.util.UUID;


	
	
@RestController
@RequestMapping("/api/income-record")
public class incomerecordcontroller {

    @Autowired
    private incomerecordservice service;

    // ✅ takes partnerId + date
    @PostMapping("/fetch/{partnerId}")
    public String fetchActualIncome(@PathVariable UUID partnerId,
                                    @RequestParam String date) {
        service.fetchActualIncome(partnerId, LocalDate.parse(date));
        return "Income Fetched";
    }

    @PostMapping("/compute/{partnerId}")
    public String computeZScore(@PathVariable UUID partnerId,
                               @RequestParam String date) {
        service.computeZScore(partnerId, LocalDate.parse(date));
        return "Z-Score Computed";
    }

    @PostMapping("/flag/{partnerId}")
    public String flagSignificantDrop(@PathVariable UUID partnerId,
                                      @RequestParam String date) {
        service.flagSignificantDrop(partnerId, LocalDate.parse(date));
        return "Drop Checked";
    }
}
