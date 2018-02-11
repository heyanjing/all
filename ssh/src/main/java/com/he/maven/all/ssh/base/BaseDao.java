package com.he.maven.all.ssh.base;

import com.alibaba.fastjson.JSON;
import com.he.maven.all.ssh.base.core.Guava;
import com.he.maven.all.ssh.base.hibernate.Java8ResultTransformer;
import com.he.maven.all.ssh.base.hibernate.MapResultTransformer;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * dao的实现类可以继承该类从而使实现类拥有更多的方法
 */
@Transactional
public class BaseDao<T> {
    private static final Logger log = LoggerFactory.getLogger(BaseDao.class);
    public static final Integer PAGE_NUMBER = 1;
    public static final Integer PAGE_SIZE = 20;
    @PersistenceContext
    protected EntityManager entityManager;
    protected Class<T> entityClass;

    private Class getClassGenricType(final Class clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            log.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            log.warn("Index: " + index + ", Sizex of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            log.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }
        return (Class) params[index];
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @PostConstruct
    public void init() {
        this.entityClass = this.getClassGenricType(this.getClass(), 0);
    }

    /**
     * pageMapListBySql
     */
    //region Description
    public Page<Map<String, Object>> pageMapListBySql(String sql, Integer pageNumber, Integer pageSize) {
        return this.basePageMapListBySql(sql, pageNumber, pageSize, null);
    }

    public Page<Map<String, Object>> pageMapListBySql(String sql, Integer pageNumber, Integer pageSize, Object... params) {
        return this.basePageMapListBySql(sql, pageNumber, pageSize, params);
    }

    public Page<Map<String, Object>> pageMapListBySql(String sql, Integer pageNumber, Integer pageSize, Map<String, ?> params) {
        return this.basePageMapListBySql(sql, pageNumber, pageSize, params);
    }

    private Page<Map<String, Object>> basePageMapListBySql(String sql, Integer pageNumber, Integer pageSize, Object params) {
        // MEINFO:2018/2/11 10:27  看前端框架的分页第一页传入的pageNumber是0还是1(假设为1)如果为0，将该参数改为0 ，PAGE_NUMBER=0
        if (pageNumber < 1) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize < 1) {
            pageSize = PAGE_SIZE;
        }
        Map<String, Query> queryMap = this.createPageQuery(sql, SqlType.SQL, null, pageNumber, pageSize, params);
        Query count_query = queryMap.get("count_query");
        Query data_query = queryMap.get("data_query");
        data_query.unwrap(SQLQuery.class).setResultTransformer(MapResultTransformer.INSTANCE);
        Long count = Long.valueOf(count_query.getSingleResult().toString());
        List<Map<String, Object>> data = data_query.getResultList();
        return new PageImpl<>(data, PageRequest.of(pageNumber - 1, pageSize), count);
    }
    //endregion

    /**
     * findMapListBySql
     */
    //region Description
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findMapListBySql(String sql) {
        Query query = this.createSqlQuery(sql, null);
        query.unwrap(SQLQuery.class).setResultTransformer(MapResultTransformer.INSTANCE);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findMapListBySql(String sql, Object... params) {
        Query query = this.createSqlQuery(sql, params);
        query.unwrap(SQLQuery.class).setResultTransformer(MapResultTransformer.INSTANCE);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findMapListBySql(String sql, Map<String, ?> params) {
        Query query = this.createSqlQuery(sql, params);
        query.unwrap(SQLQuery.class).setResultTransformer(MapResultTransformer.INSTANCE);
        return query.getResultList();
    }
    //endregion
    /**
     * pageEntityClassBySql
     */
    //region Description
    public <E> Page<E> pageEntityClassBySql(String sql, Class<E> entityClass, Integer pageNumber, Integer pageSize) {
        return this.basePageEntityClassBySql(sql,entityClass, pageNumber, pageSize, null);
    }

    public <E> Page<E> pageEntityClassBySql(String sql, Class<E> entityClass, Integer pageNumber, Integer pageSize, Object... params) {
        return this.basePageEntityClassBySql(sql,entityClass, pageNumber, pageSize, params);
    }

    public <E> Page<E> pageEntityClassBySql(String sql, Class<E> entityClass, Integer pageNumber, Integer pageSize, Map<String, ?> params) {
        return this.basePageEntityClassBySql(sql,entityClass, pageNumber, pageSize, params);
    }
    private <E> Page<E> basePageEntityClassBySql(String sql, Class<E> entityClass, Integer pageNumber, Integer pageSize, Object params) {
        // MEINFO:2018/2/11 10:27  看前端框架的分页第一页传入的pageNumber是0还是1(假设为1)如果为0，将该参数改为0 ，PAGE_NUMBER=0
        if (pageNumber < 1) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize < 1) {
            pageSize = PAGE_SIZE;
        }
        Map<String, Query> queryMap = this.createPageQuery(sql, SqlType.SQL, null, pageNumber, pageSize, params);
        Query count_query = queryMap.get("count_query");
        Query data_query = queryMap.get("data_query");
        data_query.unwrap(org.hibernate.Query.class).setResultTransformer(Java8ResultTransformer.aliasToBean(entityClass));
        Long count = Long.valueOf(count_query.getSingleResult().toString());
        List<E> data = data_query.getResultList();
        return new PageImpl<>(data, PageRequest.of(pageNumber - 1, pageSize), count);
    }
    //endregion
    /**
     * findEntityClassBySql
     */
    //region Description
    @SuppressWarnings("unchecked")
    public <E> List<E> findEntityClassBySql(String sql, Class<E> entityClass) {
        Query query = this.createSqlQuery(sql, null);
        query.unwrap(org.hibernate.Query.class).setResultTransformer(Java8ResultTransformer.aliasToBean(entityClass));
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public <E> List<E> findEntityClassBySql(String sql, Class<E> entityClass, Object... params) {
        Query query = this.createSqlQuery(sql, params);
        query.unwrap(org.hibernate.Query.class).setResultTransformer(Java8ResultTransformer.aliasToBean(entityClass));
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public <E> List<E> findEntityClassBySql(String sql, Class<E> entityClass, Map<String, ?> params) {
        Query query = this.createSqlQuery(sql, params);
        query.unwrap(org.hibernate.Query.class).setResultTransformer(Java8ResultTransformer.aliasToBean(entityClass));
        return query.getResultList();
    }
    //endregion

    /**
     * createPageQuery
     */
    //region Description
    private Map<String, Query> createPageQuery(String sql, SqlType queryType, Class<?> clazz, Integer pageNumber, Integer pageSize, Object params) {
        Map<String, Query> queryMap = Guava.newHashMap();
        Query count_query;
        Query data_query;
        if (SqlType.SQL.equals(queryType)) {
            String count_hql = "select count(*)  from (" + sql + ") temp";
            count_query = this.createSqlQuery(count_hql, null, params);
            data_query = this.createSqlQuery(sql, clazz, params);
        } else {
            String count_hql = "SELECT COUNT(*) " + StringUtils.substring(sql, StringUtils.indexOfIgnoreCase(sql, "from", 0));
            count_query = this.createJpqlQuery(count_hql, null, params);
            data_query = this.createJpqlQuery(sql, clazz, params);
        }
        Integer offset = (pageNumber - 1) * pageSize;
        data_query = data_query.setFirstResult(offset).setMaxResults(pageSize);
        queryMap.put("count_query", count_query);
        queryMap.put("data_query", data_query);
        return queryMap;
    }
    //endregion

    /**
     * pageBySql
     */
    //region Description
    public Page<T> pageBySql(String sql, Integer pageNumber, Integer pageSize) {
        return this.pageBySql(sql, this.entityClass, pageNumber, pageSize, null);
    }

    public Page<T> pageBySql(String sql, Integer pageNumber, Integer pageSize, Object... params) {
        return this.pageBySql(sql, this.entityClass, pageNumber, pageSize, params);
    }

    public Page<T> pageBySql(String sql, Integer pageNumber, Integer pageSize, Map<String, ?> params) {
        return this.pageBySql(sql, this.entityClass, pageNumber, pageSize, params);
    }

    private Page<T> pageBySql(String sql, Class<?> clazz, Integer pageNumber, Integer pageSize, Object params) {
        // MEINFO:2018/2/11 10:27  看前端框架的分页第一页传入的pageNumber是0还是1(假设为1)如果为0，将该参数改为0 ，PAGE_NUMBER=0
        if (pageNumber < 1) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize < 1) {
            pageSize = PAGE_SIZE;
        }
        Map<String, Query> queryMap = this.createPageQuery(sql, SqlType.SQL, clazz, pageNumber, pageSize, params);
        Query count_query = queryMap.get("count_query");
        Query data_query = queryMap.get("data_query");
        Long count = Long.valueOf(count_query.getSingleResult().toString());
        List<T> data = data_query.getResultList();
        return new PageImpl<>(data, PageRequest.of(pageNumber - 1, pageSize), count);
    }
    //endregion

    /**
     * pageByJpql
     */
    //region Description
    public Page<T> pageByJpql(String sql, Integer pageNumber, Integer pageSize) {
        return this.pageByJpql(sql, this.entityClass, pageNumber, pageSize, null);
    }

    public Page<T> pageByJpql(String sql, Integer pageNumber, Integer pageSize, Object... params) {
        return this.pageByJpql(sql, this.entityClass, pageNumber, pageSize, params);
    }

    public Page<T> pageByJpql(String sql, Integer pageNumber, Integer pageSize, Map<String, ?> params) {
        return this.pageByJpql(sql, this.entityClass, pageNumber, pageSize, params);
    }

    private Page<T> pageByJpql(String jpql, Class<?> clazz, Integer pageNumber, Integer pageSize, Object params) {
        // MEINFO:2018/2/11 10:27  看前端框架的分页第一页传入的pageNumber是0还是1(假设为1)如果为0，将该参数改为0 ，PAGE_NUMBER=0
        if (pageNumber < 1) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize < 1) {
            pageSize = PAGE_SIZE;
        }
        Map<String, Query> queryMap = this.createPageQuery(jpql, SqlType.JPQL, clazz, pageNumber, pageSize, params);
        Query count_query = queryMap.get("count_query");
        Query data_query = queryMap.get("data_query");
        Long count = Long.valueOf(count_query.getSingleResult().toString());
        List<T> data = data_query.getResultList();
        return new PageImpl<>(data, PageRequest.of(pageNumber - 1, pageSize), count);
    }
    //endregion

    /**
     * findBySql
     */
    //region Description
    public List<T> findBySql(String sql) {
        return this.createSqlQuery(sql, this.entityClass, null).getResultList();
    }

    public List<T> findBySql(String sql, Object... params) {
        return this.createSqlQuery(sql, this.entityClass, params).getResultList();
    }

    public List<T> findBySql(String sql, Map<String, ?> params) {
        return this.createSqlQuery(sql, this.entityClass, params).getResultList();
    }
    //endregion

    /**
     * findByJpql
     */
    //region Description
    public List<T> findByJpql(String jpql) {
        return this.createJpqlQuery(jpql, this.entityClass, null).getResultList();
    }

    public List<T> findByJpql(String jpql, Object... params) {
        return this.createJpqlQuery(jpql, this.entityClass, params).getResultList();
    }

    public List<T> findByJpql(String jpql, Map<String, ?> params) {
        return this.createJpqlQuery(jpql, this.entityClass, params).getResultList();
    }
    //endregion

    /**
     * createJpqlQuery   createSqlQuery
     */
    //region Description
    private Query createJpqlQuery(String jpql, Class<?> clazz, Object params) {
        return this.createQuery(jpql, SqlType.JPQL, clazz, params);
    }

    private Query createSqlQuery(String sql, Class<?> clazz, Object params) {
        return this.createQuery(sql, SqlType.SQL, clazz, params);
    }

    private Query createSqlQuery(String sql, Object params) {
        return this.createQuery(sql, SqlType.SQL, null, params);
    }

    private Query createQuery(String sql, SqlType queryType, Class<?> clazz, Object params) {
        Query query;
        if (queryType.equals(SqlType.SQL)) {
            if (clazz != null) {
                query = getEntityManager().createNativeQuery(sql, clazz);
            } else {
                query = getEntityManager().createNativeQuery(sql);
            }
        } else {
            query = getEntityManager().createQuery(sql);
        }
        if (params != null) {
            if (params instanceof Map) {
                log.debug("Map型参数");
                Map<String, Object> map = (Map<String, Object>) params;
                for (Map.Entry<String, Object> param : map.entrySet()) {
                    query.setParameter(param.getKey(), param.getValue());
                }
            } else {
                log.debug("List型参数");
                Object[] args = (Object[]) params;
                for (int i = 0; i < args.length; i++) {
                    query.setParameter(i, args[i]);
                }
            }
            log.debug(JSON.toJSONString(params));
        }
        return query;
    }
    //endregion

    enum SqlType {
        SQL, HQL, JPQL
    }
}
