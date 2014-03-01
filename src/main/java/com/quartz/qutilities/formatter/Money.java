/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.formatter;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class Money
{
    final public String currency;
    final public float amount;

    public Money(String pCurrency, float pAmount)
    {
        currency = pCurrency;
        amount = pAmount;
    }

    public float getAmount()
    {
        return this.amount;
    }

    public String getCurrency()
    {
        return currency;
    }
}
