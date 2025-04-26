package com.dy.dev.service;

import com.dy.dev.dao.UserRepository;
import com.dy.dev.listener.dto.CompanyReadDto;
import com.dy.dev.listener.events.EntityEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    private static final Integer COMPANY_ID = 1;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private CompanyService companyService;

    @Test
    void findById() {
        doReturn(getCompany(COMPANY_ID)).when(userRepository).findById(COMPANY_ID);

        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);
        assertThat(getCompany(COMPANY_ID)).isEqualTo(actualResult);

        verify(applicationEventPublisher, times(1)).publishEvent(any(EntityEvent.class));
        verifyNoMoreInteractions(applicationEventPublisher);
    }

    private static Optional<CompanyReadDto> getCompany(int id) {
        return Optional.of(new CompanyReadDto(id));
    }

}