package com.dy.dev.dao;

import com.dy.dev.dto.entity.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Long> {


}
