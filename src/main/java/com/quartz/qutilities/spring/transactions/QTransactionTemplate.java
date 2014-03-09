/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.spring.transactions;

import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class QTransactionTemplate extends TransactionTemplate
{
    public QTransactionTemplate(PlatformTransactionManager pTransactionManager)
    {
        super(pTransactionManager);
    }

    public <T>T execute(final QTransactionCallback<T> pCallback) throws QTransactionTemplateException
    {
        return (T) execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status)
            {
                try
                {
                    return pCallback.doInTransaction(status);
                }
                catch (Exception e)
                {
                    throw new QTransactionTemplateException(e);
                }
            }
        });
    }
}
