package com.quartz.qutilities.sql.spring;

import org.apache.commons.dbutils.QueryLoader;
import org.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.Collections;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class QueryLoaderFactoryBean implements FactoryBean
{
    private boolean singleton = true;
    private String queryFileResource;

    public QueryLoaderFactoryBean()
    {

    }

    public void setSingleton(boolean pSingleton)
    {
        singleton = pSingleton;
    }

    public void setQueryFileResource(String pQueryFileResource)
    {
        queryFileResource = pQueryFileResource;
    }

    public Object getObject() throws Exception
    {
        if (queryFileResource == null) throw new IllegalStateException("Queries Resource not set.");

        final QueryLoader ql = new QueryLoader() {};
        return Collections.unmodifiableMap(ql.load(queryFileResource));
    }

    public Class getObjectType()
    {
        return Map.class;
    }

    public boolean isSingleton()
    {
        return singleton;
    }
}
