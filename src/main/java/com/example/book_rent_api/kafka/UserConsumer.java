package com.example.book_rent_api.kafka;

import com.example.book_rent_api.model.LocalUser;
import com.example.book_rent_api.repository.LocalUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookConsumer.class);

    private final LocalUserRepository localUserRepository;

    @Autowired
    public UserConsumer(LocalUserRepository localUserRepository) {
        this.localUserRepository = localUserRepository;
    }

    @KafkaListener(topics = "users-events", groupId = "users-events-group1")
    public void consume(Map<String, Object> message) {
        LOGGER.info("Message received from topic -> {}", message);

        String method = (String) message.get("method");
        Long id = ((Number) message.get("id")).longValue();

        if ("POST".equalsIgnoreCase(method)) {
            if (!localUserRepository.existsById(id)) {
                LocalUser localUser = new LocalUser();
                localUser.setUserId(id);
                localUserRepository.save(localUser);
                LOGGER.info("User with ID {} added.", id);
            } else {
                LOGGER.info("User with ID {} already exists.", id);
            }
        } else if ("DELETE".equalsIgnoreCase(method)) {
            if (localUserRepository.existsById(id)) {
                localUserRepository.deleteById(id);
                LOGGER.info("User with ID {} deleted.", id);
            } else {
                LOGGER.info("User with ID {} does not exist.", id);
            }
        } else {
            LOGGER.warn("Unknown method: {}", method);
        }
    }
}

