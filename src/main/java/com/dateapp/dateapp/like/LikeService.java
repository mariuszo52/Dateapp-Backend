package com.dateapp.dateapp.like;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
class LikeService {
    private final LikeRepository likeRepository;

    LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }
    List<ReceivedLikeDto> getAllUserReceivedLikes(long userId) {
        return likeRepository.findAllByReceivingUser_Id(userId).stream()
                .map(LikeMapper::map)
                .sorted((o1, o2) -> {
                    if (o1.getTime().isBefore(o2.getTime())) {
                        return 1;
                    }
                    if (o2.getTime().isBefore(o1.getTime())) {
                        return -1;
                    }
                    return 0;
                }).toList();
    }
}
