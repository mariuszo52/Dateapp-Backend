package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {
}
