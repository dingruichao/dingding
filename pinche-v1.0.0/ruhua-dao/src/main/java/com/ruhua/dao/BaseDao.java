package com.ruhua.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhouhuawei
 * Date: 14-7-08
 * Time: 下午3:22
 */
public class BaseDao<T> extends SqlSessionDaoSupport {
    public Boolean insert(String classMethod, T entity) {
        Boolean flag = (getSqlSession().insert(classMethod, entity) > 0);
        return flag;
    }

    public Boolean update(String classMethod, T entity) {
        Boolean flag = (getSqlSession().update(classMethod, entity) > 0);
        return flag;
    }

    public Boolean delete(String classMethod, T entity) {
        Boolean flag = (getSqlSession().delete(classMethod, entity) > 0);
        return flag;
    }

    public Integer queryCountForObject(String classMethod, Object entity) {
        Object result = getSqlSession().selectOne(classMethod, entity);
        Integer count;
        try {
            count = (null == result) ? 0 : (Integer) result;
        } catch (ClassCastException e) {
            logger.error("sql语句：" + classMethod + "查询结果非数字类型，无法用于查询数量");
            throw e;
        }
        return count;
    }

    public T queryForObject(String classMethod, Object entity) {
        T result = (T) getSqlSession().selectOne(classMethod, entity);
        return result;
    }

    public T queryForObjectNoEntity(String classMethod) {
        T result = (T) getSqlSession().selectOne(classMethod);
        return result;
    }

    /**
     * 没有查询参数entity可传null
     */
    public List queryForList(String classMethod, Object entity) {
        List result = getSqlSession().selectList(classMethod, entity);
        return result;
    }

//    /**
//     * 分页查询函数
//     *
//     * @param queryCountSql 查询记录总条数的sql
//     * @param queryListSql  查询当页记录的sql
//     * @param entity        参数
//     * @param pageRequest   分页参数
//     * @return
//     */
//    public PageBean<T> pageQuery(String queryCountSql, String queryListSql, Object entity, PageBean pageRequest) {
//
//        List<T> list = getSqlSession().selectList(queryListSql, entity,
//                new RowBounds(pageRequest.getStartIndex(), pageRequest.getPageSize()));
//
//        //如果计算数量的sql为空，用list.size代表总数量，若sql非空要专门去查一次总数量
//        Integer totalCount = (queryCountSql == null || queryCountSql.trim().isEmpty()) ?
//                list.size() :
//                queryCountForObject(queryCountSql, entity);
//        PageBean<T> pageResponse = new PageBean<T>(pageRequest.getCurrentPage(),pageRequest.getPageSize(),totalCount, list);
//        return pageResponse;
//    }
}
