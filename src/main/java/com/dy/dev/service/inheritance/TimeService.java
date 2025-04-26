package com.dy.dev.service.inheritance;

import com.dy.dev.service.CompanyService;
import org.springframework.stereotype.Component;

@Component
public class TimeService extends StrategyService {

    protected TimeService(CompanyService companyService) {
        super(companyService);
    }

    public void getServiceInfo() {
        System.out.println("Company Service: " + companyService);
    }
}
