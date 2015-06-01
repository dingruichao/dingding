package com.ruhua.dao;


import com.ruhua.domain.common.PlanUsersCriteria;
import com.ruhua.domain.user.PinchePlan;
import com.ruhua.domain.user.PlanUsers;

import java.util.List;

/**
 * Created with GenerateCode.
 * User: zhanghuibin
 * DateTime: 2015-05-16T23:51:52.8476489+08:00
 * To change this template use File | Settings | File Templates.
 */
public interface PlanUsersDao {


    /**
     * 插入PlanUsers
     *
     * @param planUsers
     * @return
     */
    public boolean insertPlanUsers(PlanUsers planUsers);


    public PlanUsers getPlanUserByBizKey(PlanUsers planUsers);


    /**
     * 根据主键更新PlanUsers所有字段
     *
     * @param planUsers
     * @return
     */
    public boolean updatePlanUsersByKey(PlanUsers planUsers);

    public long pageQueryCriteriaCount(PlanUsersCriteria criteria);

    public List<PlanUsers> pageQueryCriteria(PlanUsersCriteria criteria);

}
