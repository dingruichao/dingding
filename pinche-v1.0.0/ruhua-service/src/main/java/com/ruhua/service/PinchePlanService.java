package com.ruhua.service;

import com.ruhua.domain.common.PageBean;
import com.ruhua.domain.common.PinchePlanCriteria;
import com.ruhua.domain.common.PlanUsersCriteria;
import com.ruhua.domain.user.PlanUsersDto;
import com.ruhua.domain.user.dto.PinchePlanDto;

import java.util.List;

/**
 * Created by dingrc on 2015/5/14.
 */
public interface PinchePlanService {
    public PageBean<PinchePlanDto> getPinchePlanList(PinchePlanCriteria criteria) ;

    public boolean insert(PinchePlanDto dto);

    public Integer insertPlanUsers(PlanUsersDto dto);
    public boolean updatePlanUsers(PlanUsersDto dto);
    public PinchePlanDto getPinchePlanById(Long id);
    public boolean updatePinchePlan(PinchePlanDto dto);

    public List<PlanUsersDto> queryPlanUsersList(PlanUsersCriteria criteria);
    public  List<PinchePlanDto> queryMyInPlanList(Integer userId);
}
