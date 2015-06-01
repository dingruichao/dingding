package com.ruhua.dao.impl;


import com.ruhua.dao.BaseDao;
import com.ruhua.dao.PlanUsersDao;
import com.ruhua.domain.common.PlanUsersCriteria;
import com.ruhua.domain.user.PinchePlan;
import com.ruhua.domain.user.PlanUsers;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with GenerateCode.
 * User: zhanghuibin
 * DateTime: 2015-05-16T23:51:52.8626611+08:00
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class PlanUsersDaoImpl extends BaseDao implements PlanUsersDao {

    /**
     * 插入PlanUsers
     *
     * @param planUsers
     * @return
     */
    public boolean insertPlanUsers(PlanUsers planUsers){
        return super.insert("PlanUsersMapper.insertPlanUsers", planUsers);
    }

    public PlanUsers getPlanUserByBizKey(PlanUsers planUsers){

        return (PlanUsers) super.queryForObject("PlanUsersMapper.getPlanUserByBizKey",planUsers);
    }



    /**
     * 根据主键更新PlanUsers所有字段
     *
     * @param planUsers
     * @return
     */
    public boolean updatePlanUsersByKey(PlanUsers planUsers){
        return super.update("PlanUsersMapper.updatePlanUsersByKey", planUsers);
    }


    public long pageQueryCriteriaCount(PlanUsersCriteria criteria){
        Long count= (Long)super.queryForObject("PlanUsersMapper.pageQueryCriteriaCount",criteria);
        return count;
    }

    public List<PlanUsers> pageQueryCriteria(PlanUsersCriteria criteria){
        return queryForList("PlanUsersMapper.pageQueryCriteria", criteria);
    }
}
