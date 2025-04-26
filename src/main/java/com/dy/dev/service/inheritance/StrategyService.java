package com.dy.dev.service.inheritance;

import com.dy.dev.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class StrategyService {

    protected final CompanyService companyService;

    @Autowired
    protected StrategyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}
