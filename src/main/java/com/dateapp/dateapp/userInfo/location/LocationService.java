package com.dateapp.dateapp.userInfo.location;

import com.dateapp.dateapp.userInfo.UserInfo;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import com.dateapp.dateapp.userInfo.UserInfoMapper;
import org.apache.lucene.util.SloppyMath;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.apache.lucene.util.SloppyMath.haversinMeters;
import static org.apache.lucene.util.SloppyMath.haversinSortKey;

@Service
public class LocationService {


    public double calculateDistanceInMetersBetweenUsers(double user1Latitude, double user1Longitude, double user2Latitude, double user2Longitude) {
        double sortKey = haversinSortKey(user1Latitude, user1Longitude, user2Latitude, user2Longitude);
        return haversinMeters(sortKey);

    }

}
