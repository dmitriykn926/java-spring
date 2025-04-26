package com.dy.dev.dao;

import com.dy.dev.dto.ConnectionPool;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
//@Scope(value = BeanDefinition.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConnectionRepository implements CrudRepository<ConnectionRepository.ConnectionRead, Integer> {

    @Qualifier("connectionPool")
    private final ConnectionPool connectionPool;

    public Optional<ConnectionRead> findById(Integer id) {
        ConnectionRead value = new ConnectionRead();
        value.setConnectionId(123);
        return Optional.of(value);
    }

    //    @Transactional
    public void findAll() {

    }

    @Override
    public String toString() {
        return "CompanyRepository{" +
                "connectionPool=" + connectionPool;
    }

    @Override
    public Optional<Integer> findById(ConnectionRead id) {
        return Optional.empty();
    }

    @Component
//    @Transactional
    public static class ConnectionRead {

        private Integer connectionId;

        void setConnectionId(Integer connectionId) {
            this.connectionId = connectionId;
        }

//        @Autowired(required = false)
//        public ConnectionRead(Integer connectionId) {
//            this.connectionId = connectionId;
//        }
    }
}
