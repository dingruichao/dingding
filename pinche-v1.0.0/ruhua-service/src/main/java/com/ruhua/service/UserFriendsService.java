package com.ruhua.service;

import com.ruhua.domain.common.PageBean;
import com.ruhua.domain.common.UserFriendsCriteria;
import com.ruhua.domain.user.PlanUsers;
import com.ruhua.domain.user.PlanUsersDto;
import com.ruhua.domain.user.UserFriends;
import com.ruhua.domain.user.dto.UserFriendsDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by dingrc on 2015/5/22.
 */
public interface UserFriendsService {
    public PageBean<UserFriendsDto> getUserFriendsList(UserFriendsCriteria criteria);
    public boolean insert(UserFriendsDto dto);
    public boolean update(UserFriendsDto dto);
    public boolean deleteByBizKey(UserFriendsDto dto);
    public boolean deleteById(Long id);


}
