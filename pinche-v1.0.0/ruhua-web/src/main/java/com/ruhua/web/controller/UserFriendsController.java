package com.ruhua.web.controller;

import com.ruhua.dao.UserDao;
import com.ruhua.domain.common.PageBean;
import com.ruhua.domain.common.PinchePlanCriteria;
import com.ruhua.domain.common.UserFriendsCriteria;
import com.ruhua.domain.common.base.JDResult;
import com.ruhua.domain.constants.ServiceResponseConstants;
import com.ruhua.domain.geo.Coordinate;
import com.ruhua.domain.user.BaseInfo;
import com.ruhua.domain.user.UserFriends;
import com.ruhua.domain.user.UserInfo;
import com.ruhua.domain.user.dto.PinchePlanDto;
import com.ruhua.domain.user.dto.UserFriendsDto;
import com.ruhua.service.UserFriendsService;
import com.ruhua.web.interceptor.Login;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: lijing3
 * Date: 14-11-8
 * Time: 下午2:05
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/userFriends")
public class UserFriendsController extends BaseController {

    private Logger logger = Logger.getLogger(UserFriendsController.class);

    @Resource
    private UserFriendsService userFriendsService;

    @Resource
    private UserDao userDao;

    @RequestMapping(value = "/getMyFriendsList.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject getMyFriendsList(HttpServletRequest request,HttpServletResponse response,Coordinate lnglat,
                                               @RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true, ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());
        BaseInfo userInfo = getLoginAccount();
        UserInfo allUserInfo = userDao.queryUserInfoByEmailNoPic(userInfo.getEmail());
        UserFriendsCriteria criteria=new UserFriendsCriteria();
        criteria.setUserId(allUserInfo.getId());
        criteria.setRowStart(0);
        criteria.setPageSize(50);
        criteria.setPageNo(1);

        PageBean<UserFriendsDto> pageBean=userFriendsService.getUserFriendsList(criteria);
        result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
        result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
        result.setData(pageBean);
        return new JSONPObject(callback, result);
    }
    @RequestMapping(value = "/addUserFriend.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject addUserFriend(HttpServletRequest request,HttpServletResponse response,UserFriendsDto dto,
                                        @RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true, ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());
        BaseInfo userInfo = getLoginAccount();
        UserInfo allUserInfo = userDao.queryUserInfoByEmailNoPic(userInfo.getEmail());
        dto.setUserId(allUserInfo.getId());

        if(userFriendsService.insert(dto)){
            result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
            result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
            result.setData(dto);
        }

        return new JSONPObject(callback, result);
    }
    @Login(required = true)
    @RequestMapping(value = "/delUserFriend.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject delUserFriend(HttpServletRequest request,HttpServletResponse response,UserFriendsDto dto,
                                     @RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true, ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());


        if(userFriendsService.deleteById(dto.getId())){
            result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
            result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
        }

        return new JSONPObject(callback, result);
    }


}