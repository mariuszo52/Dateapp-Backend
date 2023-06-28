package com.dateapp.dateapp.like;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface LikeRepository extends CrudRepository<Like, Long> {

    List<Like> findAllByReceivingUser_Id(Long id);
    List<Like> findAllBySendingUser_Id(Long id);
}
