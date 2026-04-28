package com.dy.dev.rest;

import com.dy.dev.dto.PageRequest;
import com.dy.dev.dto.UserCreateEditDto;
import com.dy.dev.dto.UserFilter;
import com.dy.dev.dto.UserReadDto;
import com.dy.dev.service.CompanyService;
import com.dy.dev.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserRestController {

    private final UserService userService;
    private final CompanyService companyService;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageRequest<UserReadDto> find(UserFilter filter, Pageable pageable) {
        return PageRequest.of(userService.findAll(filter, pageable));
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable("id") Integer id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @PostMapping("/registration")
    @ResponseStatus(CREATED)
    public UserReadDto save(@Valid @RequestBody UserCreateEditDto user) {
        return userService.create(user);
    }

    @PutMapping("/{id}/update")
    public UserReadDto update(@PathVariable("id") Integer id, @RequestBody UserCreateEditDto user) {
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }
}
