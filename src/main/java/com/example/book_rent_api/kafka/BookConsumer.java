package com.example.book_rent_api.kafka;

import com.example.book_rent_api.model.LocalBook;
import com.example.book_rent_api.repository.LocalBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BookConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookConsumer.class);

    private final LocalBookRepository localBookRepository;

    @Autowired
    public BookConsumer(LocalBookRepository localBookRepository) {
        this.localBookRepository = localBookRepository;
    }

    @KafkaListener(topics = "books-events", groupId = "books-events-group1")
    public void consume(Map<String, Object> message) {
        LOGGER.info("Message received from topic -> {}", message);

        String method = (String) message.get("method");
        Long id = ((Number) message.get("id")).longValue();

        if ("POST".equalsIgnoreCase(method)) {
            if (!localBookRepository.existsById(id)) {
                LocalBook newBook = new LocalBook();
                newBook.setBookId(id);
                localBookRepository.save(newBook);
                LOGGER.info("Book with ID {} added.", id);
            } else {
                LOGGER.info("Book with ID {} already exists.", id);
            }
        } else if ("DELETE".equalsIgnoreCase(method)) {
            if (localBookRepository.existsById(id)) {
                localBookRepository.deleteById(id);
                LOGGER.info("Book with ID {} deleted.", id);
            } else {
                LOGGER.info("Book with ID {} does not exist.", id);
            }
        } else {
            LOGGER.warn("Unknown method: {}", method);
        }
    }
}
