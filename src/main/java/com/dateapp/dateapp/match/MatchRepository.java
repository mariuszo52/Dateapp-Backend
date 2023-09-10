package com.dateapp.dateapp.match;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {
    List<Match> findAllByUser_Id(Long userId);

    List<Match> findMatchByChatId(long chatId);

    List<Match> findAllByUser_IdOrMatchedUserId(Long userId, Long matchedId);
}
