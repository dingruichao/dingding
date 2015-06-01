package com.ruhua.web.controller;

import com.ruhua.common.map.GeoUtils;
import com.ruhua.common.utils.DateFormatUtils;
import com.ruhua.dao.PlanUsersDao;
import com.ruhua.dao.UserDao;
import com.ruhua.domain.common.base.JDResult;
import com.ruhua.domain.common.PageBean;
import com.ruhua.domain.common.PinchePlanCriteria;
import com.ruhua.domain.common.PlanUsersCriteria;
import com.ruhua.domain.constants.ServiceResponseConstants;
import com.ruhua.domain.geo.Coordinate;
import com.ruhua.domain.user.BaseInfo;
import com.ruhua.domain.user.PlanUsers;
import com.ruhua.domain.user.PlanUsersDto;
import com.ruhua.domain.user.UserInfo;
import com.ruhua.domain.user.dto.PinchePlanDto;
import com.ruhua.service.PinchePlanService;
import com.ruhua.web.interceptor.Login;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lijing3
 * Date: 14-11-8
 * Time: 下午2:05
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/plan")
public class PinchePlanController extends BaseController {

    private Logger logger = Logger.getLogger(PinchePlanController.class);

    @Resource
    private PinchePlanService pinchePlanService;

    @Resource
    private UserDao userDao;

    @Resource
    private PlanUsersDao planUsersDao;

    @RequestMapping(value = "/pinchePlanList.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject queryNearPinchePlanList(HttpServletRequest request,HttpServletResponse response,Coordinate lnglat,
                                               PinchePlanCriteria criteria,@RequestParam String callback) throws Exception {
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
        criteria.setPageSize(50);
        criteria.setPageNo(1);

        PageBean<PinchePlanDto> pageBean=pinchePlanService.getPinchePlanList(criteria);
        result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
        result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
        result.setData(pageBean);
        return new JSONPObject(callback, result);
    }
    @RequestMapping(value = "/queryMyPinchePlanList.json", method = RequestMethod.GET)
     @ResponseBody
     public JSONPObject queryMyPinchePlanList(HttpServletRequest request,HttpServletResponse response,Coordinate lnglat,
                                              PinchePlanCriteria criteria,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true, ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());
        BaseInfo userInfo = getLoginAccount();
        if(StringUtils.isEmpty(userInfo.getEmail())) {
            return new JSONPObject(callback, result);
        }
        UserInfo allUserInfo = userDao.queryUserInfoByEmailNoPic(userInfo.getEmail());
        criteria.setCreateUser(allUserInfo.getId());
        criteria.setRowStart(0);
        criteria.setPageSize(50);
        criteria.setPageNo(1);

        PageBean<PinchePlanDto> pageBean=pinchePlanService.getPinchePlanList(criteria);
        result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
        result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
        result.setData(pageBean);
        return new JSONPObject(callback, result);
    }
    @RequestMapping(value = "/queryMyInPlanList.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject queryMyInPlanList(HttpServletRequest request,HttpServletResponse response,Coordinate lnglat,
                                             String  userId,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true, ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());
        BaseInfo userInfo = getLoginAccount();

        List<PinchePlanDto> list=pinchePlanService.queryMyInPlanList(Integer.valueOf(userId));
        result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
        result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
        result.setData(list);
        return new JSONPObject(callback, result);
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param param
     * @param callback
     * @return
     * @throws Exception
     */
    @Login(required = true)
    @RequestMapping(value = "/createPinchePlan.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject createPinchePlan(HttpServletRequest request,HttpServletResponse response,
                                   PinchePlanDto param,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true,ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());

        if(pinchePlanService.insert(param)) {
            result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
            result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
        }
        return new JSONPObject(callback, result);
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param param
     * @param callback
     * @return
     * @throws Exception
     */
    @Login(required = true)
    @RequestMapping(value = "/insertPlanUsers.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject insertPlanUsers(HttpServletRequest request,HttpServletResponse response,
                                        PlanUsersDto param,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true,ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());
        try {
            param.setState(0);
            Integer ret=pinchePlanService.insertPlanUsers(param);
            if (null!=ret) {
                result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
                result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());

            }else{

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new JSONPObject(callback, result);
    }

    @RequestMapping(value = "/queryPlanUsersList.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject queryPlanUsersList(HttpServletRequest request,HttpServletResponse response,Coordinate lnglat,
                                               PlanUsersCriteria criteria,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true, ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());

        criteria.setRowStart(0);
        criteria.setPageSize(50);
        List<PlanUsers> puList= planUsersDao.pageQueryCriteria(criteria);
        List<PlanUsersDto> uList=new ArrayList<PlanUsersDto>();
        PlanUsersDto faqiRen= new PlanUsersDto();
        PinchePlanDto pinchePlanDto=pinchePlanService.getPinchePlanById(criteria.getPlanId());

        faqiRen.setUserInfo(pinchePlanDto.getCreateUserInfo());
        faqiRen.setLat(pinchePlanDto.getLat());
        faqiRen.setLng(pinchePlanDto.getLng());
        faqiRen.setAddAddress(pinchePlanDto.getJiheAddress());
        faqiRen.setSaySamething(pinchePlanDto.getRemark());
        faqiRen.setIsCreateUser(1);
        faqiRen.setState(2);
        uList.add(faqiRen);
        for(PlanUsers p:puList){
            PlanUsersDto dto=new PlanUsersDto();
            BeanUtils.copyProperties(p,dto);
            UserInfo allUserInfo = userDao.queryUserInfoByUserId(Integer.valueOf(p.getUserId()+""));
            dto.setUserInfo(allUserInfo);
            uList.add(dto);

        }

        result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
        result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
        result.setData(uList);
        return new JSONPObject(callback, result);
    }
    @Login(required = true)
    @RequestMapping(value = "/getPinchePlan.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject getPinchePlan(HttpServletRequest request,HttpServletResponse response,
                                     String  planId,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true, ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());

        PinchePlanDto dto= pinchePlanService.getPinchePlanById(Long.valueOf(planId));

        result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
        result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
        result.setData(dto);
        return new JSONPObject(callback, result);
    }

    @Login(required = true)
    @RequestMapping(value = "/modifyPinchePlan.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject modifyPinchePlan(HttpServletRequest request,HttpServletResponse response,
                                        PinchePlanDto  dto,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true, ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());

        if(pinchePlanService.updatePinchePlan(dto)){
            result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
            result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
            result.setData(dto);
        }

        return new JSONPObject(callback, result);
    }
    @Login(required = true)
    @RequestMapping(value = "/modifyPlanUsers.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject modifyPlanUsers(HttpServletRequest request,HttpServletResponse response,
                                        PlanUsersDto  dto,@RequestParam String callback) throws Exception {
        JDResult result = new JDResult(true, ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());

        if(pinchePlanService.updatePlanUsers(dto)){
            result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
            result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
            result.setData(dto);
        }

        return new JSONPObject(callback, result);
    }

}