package com.dy.dev.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageRequest<T>(List<T> content, Metadata metadata) {

    public static <T> PageRequest<T> of(Page<T> page) {
        Metadata metadata = new Metadata(page.getNumber(), page.getSize(), page.getTotalElements());
        return new PageRequest<>(page.getContent(), metadata);
    }

    public record Metadata(int page, int size, long totalElements) {
    }
}
