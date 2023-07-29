package com.dateapp.dateapp.message;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    Set<Message> findAllByChat_Id(long chatId);
}
