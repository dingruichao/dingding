package com.ruhua.service;

import com.ruhua.domain.common.PageBean;
import com.ruhua.domain.common.UserSettingCriteria;
import com.ruhua.domain.user.UserInfo;
import com.ruhua.domain.user.UserSetting;

/**
 * Created by dingrc on 2015/5/15.
 */
public interface UserService {
    public PageBean<UserInfo> getUserSettingList(UserSettingCriteria criteria) ;
}
