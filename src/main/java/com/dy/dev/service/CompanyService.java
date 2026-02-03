package com.dy.dev.service;

import com.dy.dev.dao.CompanyRepository;
import com.dy.dev.listener.dto.CompanyReadDto;
import com.dy.dev.listener.events.AccessType;
import com.dy.dev.listener.events.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final CompanyRepository companyRepository;

    @Autowired
    private ClientService clientService;

    public Optional<CompanyReadDto> findById(Integer id) {
        System.out.println("ClientService: " + clientService);
        Optional<CompanyReadDto> companyReadDto = companyRepository.findById(id)
                .map(dto -> new CompanyReadDto(id));
        companyReadDto.ifPresent(companyDto -> applicationEventPublisher.publishEvent(new EntityEvent(companyDto, AccessType.READ)));
        return companyReadDto;
    }
}
