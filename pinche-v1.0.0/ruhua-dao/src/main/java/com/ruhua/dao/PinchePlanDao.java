package com.ruhua.dao;



import com.ruhua.domain.common.PinchePlanCriteria;
import com.ruhua.domain.user.PinchePlan;

import java.util.List;

/**
 * Created with GenerateCode.
 * User:
 * DateTime: 2015-05-14T16:24:30.4507575+08:00
 * To change this template use File | Settings | File Templates.
 */
public interface PinchePlanDao {


    public List<PinchePlan> pageQueryCriteria(PinchePlanCriteria criteria);
    public long pageQueryCriteriaCount(PinchePlanCriteria criteria);
    public boolean insertPinchePlan(PinchePlan entity);
    public PinchePlan getById(Long id);
    public boolean updatePinchePlanByKey(PinchePlan entity);
}
