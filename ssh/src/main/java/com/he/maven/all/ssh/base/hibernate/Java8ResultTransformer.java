package com.he.maven.all.ssh.base.hibernate;

import com.he.maven.all.ssh.base.core.converter.Sql2LocalDate;
import com.he.maven.all.ssh.base.core.converter.Sql2LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.hibernate.HibernateException;
import org.hibernate.transform.ResultTransformer;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by heyanjing on 2018/2/9 14:14.
 */
@Slf4j
public class Java8ResultTransformer implements ResultTransformer {
    private final Class<?> resultClass;
    private List<Field> fields = new ArrayList<>();

    public Java8ResultTransformer(final Class<?> resultClass) {
        this.resultClass = resultClass;
        Class tempClass = resultClass;
        int i = 0;
        while (tempClass != null && i < 100) {
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
            i++;
        }
        init();
    }

    public static Java8ResultTransformer aliasToBean(final Class<?> resultClass) {
        return new Java8ResultTransformer(resultClass);
    }

    public void init() {
        ConvertUtils.register(Sql2LocalDate.newInstance(), LocalDate.class);
        ConvertUtils.register(Sql2LocalDateTime.newInstance(), LocalDateTime.class);
    }

    @Override
    public Object transformTuple(final Object[] tuple, final String[] aliases) {
        Object result;

        try {
            result = this.resultClass.newInstance();
            for (int i = 0; i < aliases.length; i++) {
                String alias = aliases[i];
                Object value = tuple[i];
                for (Field field : this.fields) {
                    String fieldName = field.getName();
                    if (fieldName.equalsIgnoreCase(alias)) {
                        BeanUtils.setProperty(result, fieldName, value);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new HibernateException("Could not instantiate resultclass: " + this.resultClass.getName(), e);
        }
        return result;
    }

    @Override
    public List transformList(final List collection) {
        return collection;
    }
}
