package com.guidewire.gigsuraksha.service;


public interface coverageplanservice {

    void calculatePremium();

    void activatePlan();

    void renewWeekly();

    void cancelPlan();
}
