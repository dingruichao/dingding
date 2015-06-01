package com.ruhua.dao.impl;


import com.ruhua.dao.BaseDao;
import com.ruhua.dao.PinchePlanDao;
import com.ruhua.domain.common.PinchePlanCriteria;
import com.ruhua.domain.user.PinchePlan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with GenerateCode.
 * User:
 * DateTime: 2015-05-14T16:24:30.4767856+08:00
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class PinchePlanDaoImpl extends BaseDao implements PinchePlanDao {


    public List<PinchePlan> pageQueryCriteria(PinchePlanCriteria criteria){
        return queryForList("PinchePlanMapper.pageQueryCriteria", criteria);
    }

    public long pageQueryCriteriaCount(PinchePlanCriteria criteria){
        Long count= (Long)super.queryForObject("PinchePlanMapper.pageQueryCriteriaCount",criteria);
        return count;
    }

    public boolean insertPinchePlan(PinchePlan entity) {
        return super.insert("PinchePlanMapper.insertPinchePlan",entity);
    }
    public PinchePlan getById(Long id){
        PinchePlan pinchePlan= (PinchePlan)super.queryForObject("PinchePlanMapper.getById",id);
        return pinchePlan;
    }
    public boolean updatePinchePlanByKey(PinchePlan entity){
        return super.update("PinchePlanMapper.updatePinchePlanByKey", entity);
    }


}
