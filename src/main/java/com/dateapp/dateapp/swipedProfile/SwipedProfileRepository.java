package com.dateapp.dateapp.swipedProfile;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SwipedProfileRepository extends CrudRepository<SwipedProfile, Long> {
    List<SwipedProfile> findAllBySwipedProfile_IdAndSwipeDirection(long id, String swipeDirection);
    void deleteSwipedProfileByUser_IdAndSwipedProfile_Id(Long userId, Long swipedUserId);
    List<SwipedProfile> findAllByUser_IdOrSwipedProfile_Id(long userId, long swipedProfileId);


}
