package com.dy.dev.service;

import com.dy.dev.dao.CrudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IService {

    private final Map<Integer, String> map = new HashMap<>();

//    @Autowired
//    @Qualifier("userRepository2")
//    private final Map<String, CrudRepository> crudRepositoryMap;

    @Value("#{T(java.util.Arrays).asList(1,2,3).contains(1)}")
    public boolean host;

    public void initMap() {
        if (map.isEmpty()) {
            log.info("Map is empty");
            map.put(123, "sadfdsaf");
        }

        log.info(map.get(123));
    }

    @Override
    public String toString() {
//        System.out.println(crudRepositoryMap);
        return "UserIService{" +
                "map=" + map +
                ", host='" + host + '\'' +
                '}';
    }
}
