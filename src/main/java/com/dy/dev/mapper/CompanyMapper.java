package com.dy.dev.mapper;

import com.dy.dev.dto.entity.Company;
import com.dy.dev.listener.dto.CompanyReadDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements Mapper<Company, CompanyReadDto> {

    @Override
    public CompanyReadDto map(Company object) {
        return new CompanyReadDto(
                object.getId(),
                object.getName()
        );
    }
}
