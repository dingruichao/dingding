package com.ruhua.service.impl;

import com.ruhua.dao.UserDao;
import com.ruhua.dao.UserFriendsDao;
import com.ruhua.domain.common.PageBean;
import com.ruhua.domain.common.UserFriendsCriteria;
import com.ruhua.domain.user.*;
import com.ruhua.domain.user.PlanUsersDto;
import com.ruhua.domain.user.dto.PinchePlanDto;
import com.ruhua.domain.user.dto.UserFriendsDto;
import com.ruhua.service.UserFriendsService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingrc on 2015/5/22.
 */
@Service
public class UserFriendsServiceImpl implements UserFriendsService {
    Logger logger = Logger.getLogger(PinchePlanServiceImpl.class);
    @Resource
    private UserFriendsDao userFriendsDao;
    @Resource
    private UserDao userDao;
    public PageBean<UserFriendsDto> getUserFriendsList(UserFriendsCriteria criteria){
        com.ruhua.domain.common.PageBean<UserFriendsDto> pageBean =new PageBean<UserFriendsDto>();
        pageBean.setRowStart(criteria.getRowStart());
        pageBean.setPageSize(criteria.getPageSize());
        pageBean.setCurrentPage(criteria.getPageNo());
        long total=userFriendsDao.pageQueryCriteriaCount(criteria);
        pageBean.setTotalCount(total);
        List<UserFriends> dbList=userFriendsDao.pageQueryCriteria(criteria);
        if(dbList!=null && dbList.size()>0){
            List<UserFriendsDto> retList=new ArrayList<UserFriendsDto>();
            for(UserFriends pojo:dbList){

                UserFriendsDto dto=new UserFriendsDto();
                BeanUtils.copyProperties(pojo, dto);

                try {
                    UserInfo userInfo = userDao.queryUserInfoByUserId(dto.getUserId());
                    dto.setUserInfo(userInfo);
                }catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    UserInfo userInfo = userDao.queryUserInfoByUserId(dto.getFriendUserId());
                    dto.setFriendUserInfo(userInfo);
                }catch (Exception e){
                    e.printStackTrace();
                }
                retList.add(dto);
            }
            pageBean.setCurrentCount(retList.size());
            pageBean.setResultList(retList);
        }
        return pageBean;

    }

    public boolean insert(UserFriendsDto dto){
        UserFriends entity =new UserFriends();
        BeanUtils.copyProperties(dto,entity);
        UserFriends db=userFriendsDao.getByBizKey(entity);
        if(null!=db){
            return true;
        }
        return userFriendsDao.insert(entity);
    }
    public boolean deleteByBizKey(UserFriendsDto dto){
        UserFriends entity =new UserFriends();
        BeanUtils.copyProperties(dto,entity);
        return userFriendsDao.deleteByBizKey(entity);

    }
    public boolean deleteById(Long id){
        return userFriendsDao.deleteById(id);

    }
    public boolean update(UserFriendsDto dto){
        UserFriends entity=new UserFriends();
        BeanUtils.copyProperties(dto,entity);
        boolean db=userFriendsDao.updateByKey(entity);
        return db;
    }

}
