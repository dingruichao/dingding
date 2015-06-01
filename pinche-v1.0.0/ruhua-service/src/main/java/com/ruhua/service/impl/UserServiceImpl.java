package com.ruhua.service.impl;

import com.ruhua.dao.UserDao;
import com.ruhua.domain.common.PageBean;
import com.ruhua.domain.common.UserSettingCriteria;
import com.ruhua.domain.user.PinchePlan;
import com.ruhua.domain.user.UserInfo;
import com.ruhua.domain.user.UserSetting;
import com.ruhua.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingrc on 2015/5/14.
 */
@Service
public class UserServiceImpl implements UserService {
    Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Resource
    private UserDao userDao;

    public PageBean<UserInfo> getUserSettingList(UserSettingCriteria criteria) {

        PageBean<UserInfo> pageBean =new PageBean<UserInfo>();
        pageBean.setRowStart(criteria.getRowStart());
        pageBean.setPageSize(criteria.getPageSize());
        pageBean.setCurrentPage(criteria.getPageNo());
        long total=userDao.pageQueryCriteriaCount(criteria);
        pageBean.setTotalCount(total);
        List<UserSetting> dbList=userDao.pageQueryCriteria(criteria);

        if(dbList!=null && dbList.size()>0){
            List<UserInfo> userInfos=new ArrayList<UserInfo>();
            for(UserSetting u:dbList){
                UserInfo userInfo=null;
                try {
                    userInfo = userDao.queryUserInfoByEmailNoPic(u.getEmail());
                    userInfos.add(userInfo);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            pageBean.setCurrentCount(dbList.size());
            pageBean.setResultList(userInfos);
        }
        return pageBean;
    }
}
