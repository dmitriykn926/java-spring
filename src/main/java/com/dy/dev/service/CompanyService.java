package com.dy.dev.service;

import com.dy.dev.dao.CompanyRepository;
import com.dy.dev.listener.dto.CompanyReadDto;
import com.dy.dev.listener.events.AccessType;
import com.dy.dev.listener.events.EntityEvent;
import com.dy.dev.mapper.CompanyMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Autowired
    private ClientService clientService;

    private final ObjectProvider<MyPrototype>  myPrototypeProvider;

    public Optional<CompanyReadDto> findById(Integer id) {
        System.out.println("ClientService: " + clientService);
        Optional<CompanyReadDto> companyReadDto = companyRepository.findById(id)
                .map(company -> new CompanyReadDto(company.getId(), company.getName()));
        companyReadDto.ifPresent(companyDto -> applicationEventPublisher.publishEvent(new EntityEvent(companyDto, AccessType.READ)));
        return companyReadDto;
    }

    public MyPrototype getMyPrototype() {
        return myPrototypeProvider.getObject();
    }

    public List<CompanyReadDto> findAll() {
        return companyRepository.findAll().stream().map(companyMapper::map).toList();
    }

}
