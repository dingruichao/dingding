package com.ruhua.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruhua.common.map.GeoUtils;
import com.ruhua.dao.UserDao;
import com.ruhua.domain.common.base.JDResult;
import com.ruhua.domain.common.PageBean;
import com.ruhua.domain.common.UserSettingCriteria;
import com.ruhua.domain.constants.ServiceResponseConstants;
import com.ruhua.domain.geo.Coordinate;
import com.ruhua.domain.user.*;
import com.ruhua.domain.user.dto.PwdDto;
import com.ruhua.domain.user.dto.UserInfoDto;
import com.ruhua.service.UserService;
import com.ruhua.web.interceptor.Login;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
@RequestMapping("/user")
public class UserController extends BaseController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Resource
    private UserDao userDao;
    @Resource
    private UserService userService;

    @RequestMapping(value = "/createAccount.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject createAccount(HttpServletRequest request,HttpServletResponse response,
                                     BaseInfo baseInfo,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true,ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());
        if(!baseInfo.val())
            return new JSONPObject(callback, result);
        if(userDao.emailCount(baseInfo)) {
            result.setRetCode(ServiceResponseConstants.EMAIL_HAS_CREATED.getCode());
            result.setRetMsg(ServiceResponseConstants.EMAIL_HAS_CREATED.getMsg());
            return new JSONPObject(callback, result);
        }
        if(userDao.mobileCount(baseInfo)) {
            result.setRetCode(ServiceResponseConstants.MOBILE_HAS_CREATED.getCode());
            result.setRetMsg(ServiceResponseConstants.MOBILE_HAS_CREATED.getMsg());
            return new JSONPObject(callback, result);
        }
        if(userDao.saveUserBaseInfo(baseInfo)) {
            result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
            result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
            UserInfo info=userDao.queryUserInfoByEmailNoPic(baseInfo.getEmail());
            org.springframework.beans.BeanUtils.copyProperties(info,baseInfo);
            result.setData(baseInfo);
            setCookie(LoginAccount.USERCOOKINAME, baseInfo, 5 * 365 * 24 * 60 * 60);
        }
        return new JSONPObject(callback, result);
    }

    @RequestMapping(value = "/login.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject login(HttpServletRequest request,HttpServletResponse response,
                                     BaseInfo baseInfo,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true,ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());
        BaseInfo dbBaseInfo=userDao.login(baseInfo);
        if(dbBaseInfo!=null) {
            result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
            result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
           // UserInfo info=userDao.queryUserInfoByEmailNoPic(dbBaseInfo.getEmail());
           // org.springframework.beans.BeanUtils.copyProperties(info,baseInfo);
            result.setData(dbBaseInfo);
            setCookie(LoginAccount.USERCOOKINAME, dbBaseInfo, 5 * 365 * 24 *60*60);
        }
        return new JSONPObject(callback, result);
    }

    @RequestMapping(value = "/logout.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject logout(HttpServletRequest request,HttpServletResponse response,
                             @RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true,ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());
        BaseInfo baseInfo = new BaseInfo();
        setCookie(LoginAccount.USERCOOKINAME,baseInfo,1);
        result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
        result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
        return new JSONPObject(callback, result);
    }

    /**
     * 保存配置
     * @param request
     * @param response
     * @param userSetting
     * @param callback
     * @return
     * @throws Exception
     */
    @Login(required = true)
    @RequestMapping(value = "/saveSetting.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject saveSetting(HttpServletRequest request,HttpServletResponse response,
                             UserSetting userSetting,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true,ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());
        userSetting.setEmail(getLoginAccount().getEmail());
        userSetting.getCommunity().getBytes("UTF-8");
        if(userDao.updateUserSetting(userSetting)) {
            result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
            result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
            UserInfo info=userDao.queryUserInfoByEmailNoPic(userSetting.getEmail());
            BaseInfo baseInfo=new BaseInfo();
            org.springframework.beans.BeanUtils.copyProperties(info,baseInfo);
            result.setData(baseInfo);

        }
        return new JSONPObject(callback, result);
    }

    @Login(required = true)
    @RequestMapping(value = "/modifyPassword.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject modifyPassword(HttpServletRequest request,HttpServletResponse response,
                                      PwdDto pwdDto,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true,ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());
        BaseInfo baseInfo=getLoginAccount();
        baseInfo.setId(pwdDto.getId());
        if(baseInfo.getPass2Md5().equals(pwdDto.getOldPass2Md5())){
            baseInfo.setPass2Md5(pwdDto.getNewPass2Md5());
            if(userDao.updateUserPwd(baseInfo)) {
                result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
                result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());

                result.setData(baseInfo);
                setCookie(LoginAccount.USERCOOKINAME, baseInfo, 5 * 365 * 24 *60*60);

            }
        }else{

            result.setRetCode(ServiceResponseConstants.OLD_PWD_ERROR.getCode());
            result.setRetMsg(ServiceResponseConstants.OLD_PWD_ERROR.getMsg());

            result.setData(baseInfo);
        }


        return new JSONPObject(callback, result);
    }
    /**
     * 保存配置
     * @param request
     * @param response
     * @param userSetting
     * @param callback
     * @return
     * @throws Exception
     */
    @Login(required = true)
    @RequestMapping(value = "/saveSettingLocationInfo.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject saveSettingLocationInfo(HttpServletRequest request,HttpServletResponse response,
                                   UserSetting userSetting,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true,ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());
        userSetting.setEmail(getLoginAccount().getEmail());
        userSetting.getCommunity().getBytes("UTF-8");
        if(userDao.updateUserSettingLocationInfo(userSetting)) {
            result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
            result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
            UserInfo info=userDao.queryUserInfoByEmailNoPic(userSetting.getEmail());
            BaseInfo baseInfo=new BaseInfo();
            org.springframework.beans.BeanUtils.copyProperties(info,baseInfo);
            result.setData(baseInfo);

        }
        return new JSONPObject(callback, result);
    }



    /**
     * 查询用户信息
     * @param request
     * @param response
     * @param baseInfo
     * @param callback
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryUserInfo.json", method = RequestMethod.GET)
    @ResponseBody
    @Login(required = true)
    public JSONPObject queryUserInfo(HttpServletRequest request,HttpServletResponse response,
                                   BaseInfo baseInfo,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true,ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());
        BaseInfo userInfo = getLoginAccount();
        if(StringUtils.isEmpty(userInfo.getEmail())) {
            return new JSONPObject(callback, result);
        }
        UserInfoDto userInfoDto = new UserInfoDto();
        String email = userInfo.getEmail();
        UserInfo allUserInfo = null;
        try {
            allUserInfo = userDao.queryUserInfoByEmail(email);
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(allUserInfo == null) {
            return new JSONPObject(callback, result);
        }
        BeanUtils.copyProperties(userInfoDto,allUserInfo);
        String pic = allUserInfo.getPic();
        if(pic != null && JSONObject.parseObject(pic).get("pic001") != null) {
            userInfoDto.setHeadPic(JSONObject.parseObject(pic).getString("pic001"));
        } else userInfoDto.setHeadPic(null);
        if(StringUtils.isEmpty(allUserInfo.getCommunity())) {
            userInfoDto.setEnterAlert(DateUtil.formatDate(allUserInfo.getCreatetime(), "yyyy年MM月") + "加入");
        } else {
            userInfoDto.setEnterAlert(DateUtil.formatDate(allUserInfo.getCreatetime(),"yyyy年MM月") + "加入 "
                + "地址：<span id='dynymicCommunity'>" + allUserInfo.getCommunity() + "</span>");
        }
        result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
        result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
        result.setData(userInfoDto);
        return new JSONPObject(callback, result);
    }

    @RequestMapping(value = "/nearByUserList.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject queryNearByUserList(HttpServletRequest request,HttpServletResponse response,Coordinate lnglat,
                                               UserSettingCriteria criteria,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true, ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());
        BaseInfo userInfo = getLoginAccount();
        if(StringUtils.isEmpty(userInfo.getEmail())) {
            return new JSONPObject(callback, result);
        }
        UserInfo allUserInfo = userDao.queryUserInfoByEmailNoPic(userInfo.getEmail());
        double[] surrond = GeoUtils.getAround(allUserInfo.getLat(), allUserInfo.getLng(), 5000);


        criteria.setMinLat(surrond[0]);
        criteria.setMaxLat(surrond[1]);
        criteria.setMinLng(surrond[2]);
        criteria.setMaxLng(surrond[3]);

        criteria.setRowStart(0);
        criteria.setPageSize(20);
        criteria.setPageNo(1);

        PageBean<UserInfo> pageBean=userService.getUserSettingList(criteria);
        result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
        result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
        result.setData(pageBean);
        return new JSONPObject(callback, result);
    }

}