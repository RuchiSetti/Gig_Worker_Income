package com.guidewire.gigsuraksha.controller;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;

import com.guidewire.gigsuraksha.entity.TrustScoreLog;
import com.guidewire.gigsuraksha.service.trustscorelogservice;

import java.util.List;
import java.util.UUID;

	@RestController
	@RequestMapping("/api/trust-score-log")
	public class trustscorelogcontroller {

		  @Autowired
		    private trustscorelogservice service;

		    @PostMapping("/append")
		    public String appendLog(@RequestParam UUID partnerId,
		                            @RequestParam String event,
		                            @RequestParam Double delta,
		                            @RequestParam Double scoreBefore,
		                            @RequestParam Double scoreAfter,
		                            @RequestParam UUID relatedClaimId) {

		        service.appendLog(partnerId, event, delta, scoreBefore, scoreAfter, relatedClaimId);
		        return "Log Added";
		    }

		    @GetMapping("/history/{partnerId}")
		    public List<TrustScoreLog> getHistory(@PathVariable UUID partnerId) {
		        return service.getHistory(partnerId);
		    }

		    @GetMapping("/rolling/{partnerId}")
		    public List<TrustScoreLog> getRolling30Days(@PathVariable UUID partnerId) {
		        return service.getRolling30Days(partnerId);
		    }
		}
