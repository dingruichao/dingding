package com.ruhua.web.controller;

import com.ruhua.common.utils.json.JsonUtils;
import com.ruhua.domain.common.base.JDResult;
import com.ruhua.domain.constants.ServiceResponseConstants;
import com.ruhua.domain.geo.Coordinate;
import com.ruhua.domain.geo.POI;
import com.ruhua.service.LocationService;
import com.ruhua.web.interceptor.Login;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 与地理位置有关
 * @author lijing3
 *
 */
@Controller
@RequestMapping(value="/location")
public class LocationController extends BaseController {
	Logger logger = Logger.getLogger(LocationController.class);
    @Resource
    private LocationService locationService;
	/**
	 * 根据关键字查找周边的poi信息
     * @return
     */
    @RequestMapping(value = "/findSurroundedPOI.json")
    @ResponseBody
    @Login(required = true)
    public JSONPObject findSurroundedPOI(HttpServletRequest request,HttpServletResponse response,int type,
                                         Coordinate lnglat,@RequestParam String callback) {
    	lnglat = locationService.transLngLat(lnglat, 1, 5);//客户端直接用百度地图获取的百度坐标
        JDResult result = new JDResult(true, ServiceResponseConstants.FAILURE.getCode()
                ,ServiceResponseConstants.FAILURE.getMsg());
        List<POI> poiList = locationService.getPOIList(type,lnglat);
        logger.info("list:="+ JsonUtils.toJson(poiList));
        if(poiList != null && poiList.size() >= 0) {
            result.setRetCode(ServiceResponseConstants.SUCCESS.getCode());
            result.setRetMsg(ServiceResponseConstants.SUCCESS.getMsg());
            result.setData(poiList);
        }
        return new JSONPObject(callback, result);
    }
    
}
