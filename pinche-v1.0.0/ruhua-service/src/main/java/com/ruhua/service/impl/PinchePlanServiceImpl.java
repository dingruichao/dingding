package com.ruhua.service.impl;

import com.ruhua.dao.PinchePlanDao;
import com.ruhua.dao.PlanUsersDao;
import com.ruhua.dao.UserDao;
import com.ruhua.domain.common.PageBean;
import com.ruhua.domain.common.PinchePlanCriteria;
import com.ruhua.domain.common.PlanUsersCriteria;
import com.ruhua.domain.user.PinchePlan;
import com.ruhua.domain.user.PlanUsers;
import com.ruhua.domain.user.PlanUsersDto;
import com.ruhua.domain.user.UserInfo;
import com.ruhua.domain.user.dto.PinchePlanDto;
import com.ruhua.service.PinchePlanService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingrc on 2015/5/14.
 */
@Service
public class PinchePlanServiceImpl implements PinchePlanService{
    Logger logger = Logger.getLogger(PinchePlanServiceImpl.class);
    @Resource
    private PinchePlanDao pinchePlanDao;
    @Resource
    private UserDao userDao;
    @Resource
    private PlanUsersDao planUsersDao;



    public PageBean<PinchePlanDto> getPinchePlanList(PinchePlanCriteria criteria) {

        com.ruhua.domain.common.PageBean<PinchePlanDto> pageBean =new PageBean<PinchePlanDto>();
        pageBean.setRowStart(criteria.getRowStart());
        pageBean.setPageSize(criteria.getPageSize());
        pageBean.setCurrentPage(criteria.getPageNo());
        long total=pinchePlanDao.pageQueryCriteriaCount(criteria);
        pageBean.setTotalCount(total);
        List<PinchePlan> dbList=pinchePlanDao.pageQueryCriteria(criteria);
        if(dbList!=null && dbList.size()>0){
            List<PinchePlanDto> retList=new ArrayList<PinchePlanDto>();
            for(PinchePlan pojo:dbList){

                PinchePlanDto dto=new PinchePlanDto();
                BeanUtils.copyProperties(pojo,dto);
                if(dto.getGoDate()!=null){

                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    dto.setGoDateYYYYMMDD(df.format(dto.getGoDate()));
                }
                try {
                    UserInfo userInfo = userDao.queryUserInfoByUserId(dto.getCreateUser());
                    dto.setCreateUserInfo(userInfo);
                }catch (Exception e){
                    e.printStackTrace();
                }

                PlanUsersCriteria planUsersCriteria=new PlanUsersCriteria();
                planUsersCriteria.setRowStart(0);
                planUsersCriteria.setPageSize(50);
                planUsersCriteria.setPlanId(dto.getId());
                List<PlanUsersDto> puList= queryPlanUsersList(planUsersCriteria);
                if(puList!=null && puList.size()>0){
                    dto.setPlanUsersList(puList);
                    dto.setPlanUsersNum(puList.size());
                }

                retList.add(dto);

            }
            pageBean.setCurrentCount(retList.size());
            pageBean.setResultList(retList);
        }
        return pageBean;
    }
    public boolean insert(PinchePlanDto dto){
        PinchePlan entity =new PinchePlan();
        BeanUtils.copyProperties(dto,entity);
        return pinchePlanDao.insertPinchePlan(entity);
    }

    public Integer insertPlanUsers(PlanUsersDto dto){

        PlanUsers planUsers=new PlanUsers();
        BeanUtils.copyProperties(dto,planUsers);
        PlanUsers db=planUsersDao.getPlanUserByBizKey(planUsers);
        if(null!=db){
            return 2;
        }
        if( planUsersDao.insertPlanUsers(planUsers)){
            return 1;
        }else{
            return  null;
        }
    }
    public boolean updatePlanUsers(PlanUsersDto dto){

        PlanUsers planUsers=new PlanUsers();
        BeanUtils.copyProperties(dto,planUsers);
        boolean db=planUsersDao.updatePlanUsersByKey(planUsers);
        return db;
    }


    public PinchePlanDto getPinchePlanById(Long id){
        PinchePlan pojo=pinchePlanDao.getById(id);
        PinchePlanDto dto=new PinchePlanDto();
        BeanUtils.copyProperties(pojo,dto);
        if(dto.getGoDate()!=null){

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            dto.setGoDateYYYYMMDD(df.format(dto.getGoDate()));
        }
        try {
            UserInfo userInfo = userDao.queryUserInfoByUserId(dto.getCreateUser());
            PlanUsersCriteria criteria=new PlanUsersCriteria();
            criteria.setPlanId(id);
            criteria.setPageSize(50);
            criteria.setRowStart(0);
           List<PlanUsersDto> list= this.queryPlanUsersList(criteria);
            dto.setPlanUsersList(list);
            dto.setPlanUsersNum(list.size());
            dto.setCreateUserInfo(userInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;
    }
    public boolean updatePinchePlan(PinchePlanDto dto){
        PinchePlan entity=new PinchePlan();
        BeanUtils.copyProperties(dto,entity);
        return pinchePlanDao.updatePinchePlanByKey(entity);

    }

    public List<PlanUsersDto> queryPlanUsersList(PlanUsersCriteria criteria){
        List<PlanUsers> puList= planUsersDao.pageQueryCriteria(criteria);
        List<PlanUsersDto> uList=new ArrayList<PlanUsersDto>();

        for(PlanUsers p:puList){
            PlanUsersDto dto=new PlanUsersDto();
            BeanUtils.copyProperties(p,dto);
            UserInfo allUserInfo = userDao.queryUserInfoByUserId(Integer.valueOf(p.getUserId()+""));
            dto.setUserInfo(allUserInfo);
            dto.setIsCreateUser(0);
            uList.add(dto);

        }
        return uList;
    }


    public  List<PinchePlanDto> queryMyInPlanList(Integer userId){
        List<PinchePlanDto> uList=new ArrayList<PinchePlanDto>();
        PlanUsersCriteria criteria =new PlanUsersCriteria();
        criteria.setRowStart(0);
        criteria.setPageSize(50);
        criteria.setUserId(Long.valueOf(userId));
        List<PlanUsers> puList=planUsersDao.pageQueryCriteria(criteria);
        for(PlanUsers p:puList){
            PinchePlanDto dto=new PinchePlanDto();
            PinchePlan pojo=pinchePlanDao.getById(p.getPlanId());
            BeanUtils.copyProperties(pojo,dto);
            if(dto.getGoDate()!=null){

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                dto.setGoDateYYYYMMDD(df.format(dto.getGoDate()));
            }

            try {
                UserInfo userInfo = userDao.queryUserInfoByUserId(dto.getCreateUser());
                dto.setCreateUserInfo(userInfo);
            }catch (Exception e){
                e.printStackTrace();
            }

            PlanUsersCriteria planUsersCriteria=new PlanUsersCriteria();
            planUsersCriteria.setRowStart(0);
            planUsersCriteria.setPageSize(50);
            planUsersCriteria.setPlanId(dto.getId());
            List<PlanUsersDto> puList2= queryPlanUsersList(planUsersCriteria);
            if(puList2!=null && puList2.size()>0){
                dto.setPlanUsersList(puList2);
                dto.setPlanUsersNum(puList2.size());
            }

            uList.add(dto);

        }
        return uList;
    }

}
