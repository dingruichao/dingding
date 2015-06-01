package com.ruhua.dao;



import com.ruhua.domain.common.PinchePlanCriteria;
import com.ruhua.domain.common.UserFriendsCriteria;
import com.ruhua.domain.user.PinchePlan;
import com.ruhua.domain.user.UserFriends;

import java.util.List;

/**
 * Created with GenerateCode.
 * User:
 * DateTime: 2015-05-14T16:24:30.4507575+08:00
 * To change this template use File | Settings | File Templates.
 */
public interface UserFriendsDao {


    public List<UserFriends> pageQueryCriteria(UserFriendsCriteria criteria);
    public long pageQueryCriteriaCount(UserFriendsCriteria criteria);
    public boolean insert(UserFriends entity);
    public UserFriends getById(Long id);
    public boolean updateByKey(UserFriends entity);

    public UserFriends getByBizKey(UserFriends entity);
    public boolean deleteByBizKey(UserFriends entity);
    public boolean deleteById(Long id);
}
