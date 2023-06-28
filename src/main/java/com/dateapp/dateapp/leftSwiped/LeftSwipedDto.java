package com.dateapp.dateapp.leftSwiped;

class LeftSwipedDto {
    private long userId;
    private long leftSwipedProfileId;

    public LeftSwipedDto() {
    }

    public LeftSwipedDto(long userId, long leftSwipedProfileId) {
        this.userId = userId;
        this.leftSwipedProfileId = leftSwipedProfileId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getLeftSwipedProfileId() {
        return leftSwipedProfileId;
    }

    public void setLeftSwipedProfileId(long leftSwipedProfileId) {
        this.leftSwipedProfileId = leftSwipedProfileId;
    }
}
