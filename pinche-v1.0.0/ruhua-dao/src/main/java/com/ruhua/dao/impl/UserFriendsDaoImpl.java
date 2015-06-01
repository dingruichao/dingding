package com.ruhua.dao.impl;


import com.ruhua.dao.BaseDao;
import com.ruhua.dao.PinchePlanDao;
import com.ruhua.dao.UserFriendsDao;
import com.ruhua.domain.common.PinchePlanCriteria;
import com.ruhua.domain.common.UserFriendsCriteria;
import com.ruhua.domain.user.PinchePlan;
import com.ruhua.domain.user.UserFriends;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with GenerateCode.
 * User:
 * DateTime: 2015-05-14T16:24:30.4767856+08:00
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserFriendsDaoImpl extends BaseDao implements UserFriendsDao {


    @Override
    public List<UserFriends> pageQueryCriteria(UserFriendsCriteria criteria) {
        return queryForList("UserFriendsMapper.pageQueryCriteria", criteria);
    }

    @Override
    public long pageQueryCriteriaCount(UserFriendsCriteria criteria) {
        Long count= (Long)super.queryForObject("UserFriendsMapper.pageQueryCriteriaCount",criteria);
        return count;
    }

    @Override
    public boolean insert(UserFriends entity) {
        return super.insert("UserFriendsMapper.insertUserFriends",entity);
    }

    @Override
    public UserFriends getById(Long id) {
        UserFriends db= (UserFriends)super.queryForObject("UserFriendsMapper.getById",id);
        return db;
    }

    @Override
    public boolean updateByKey(UserFriends entity) {
        return super.update("UserFriendsMapper.updateByKey", entity);
    }

    public UserFriends getByBizKey(UserFriends entity){
        UserFriends db= (UserFriends)super.queryForObject("UserFriendsMapper.getByBizKey",entity);
        return db;
    }
    public boolean deleteByBizKey(UserFriends entity){
        return super.delete("UserFriendsMapper.deleteByBizKey", entity);
    }
    public boolean deleteById(Long id){
        return super.delete("UserFriendsMapper.deleteById", id);
    }
}
