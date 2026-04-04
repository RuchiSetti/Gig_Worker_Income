package com.guidewire.gigsuraksha.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.service.workflowservice;

import java.util.UUID;

@RestController
@RequestMapping("/api/workflow")
public class workflowcontroller {

    @Autowired
    private workflowservice service;

    @PostMapping("/run/{partnerId}")
    public String runFlow(@PathVariable UUID partnerId) {
        service.executeFullFlow(partnerId);
        return "Full Flow Executed";
    }
}
