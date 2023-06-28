package com.dateapp.dateapp.like;

class LikeMapper {
    public static ReceivedLikeDto map(Like like) {
        return new ReceivedLikeDto(
                like.getId(),
                like.getTime(),
                like.getSendingUser().getId(),
                like.getReceivingUser().getId(),
                like.getSendingUser().getUserInfo().getUrl(),
                like.getSendingUser().getUserInfo().getFirstName()
        );
    }


    }
