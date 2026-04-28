package com.dy.dev.service;

import com.dy.dev.dao.UserRepository;
import com.dy.dev.dto.PageRequest;
import com.dy.dev.dto.UserCreateEditDto;
import com.dy.dev.dto.UserFilter;
import com.dy.dev.dto.UserReadDto;
import com.dy.dev.dto.entity.User;
import com.dy.dev.mapper.UserCreateEditMapper;
import com.dy.dev.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.dy.dev.util.CriteriaUtil.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public Page<UserReadDto> findAll(UserFilter userFilter, Pageable pageable) {
        Specification<User> specification = Specification
                .where(firstNameLike(userFilter.firstName()))
                .and(lastNameLike(userFilter.lastName()))
                .and(birthDateBefore(userFilter.birthDate()));

        return userRepository.findAll(specification, pageable)
                .map(userMapper::map);
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findById(Integer id) {
        return userRepository.findById(id).map(userMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto user) {
        return Optional.of(user)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Integer id, UserCreateEditDto userCreateEditDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userCreateEditDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
