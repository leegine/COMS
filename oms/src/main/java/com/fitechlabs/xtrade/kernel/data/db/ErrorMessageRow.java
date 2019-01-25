// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ErrorMessageRow.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;

public interface ErrorMessageRow
    extends Row
{

    public abstract long getErrorMessageId();

    public abstract boolean getErrorMessageIdIsSet();

    public abstract String getErrorCode();

    public abstract boolean getErrorCodeIsSet();

    public abstract String getErrorTag();

    public abstract boolean getErrorTagIsSet();

    public abstract String getLocalizedText();

    public abstract String getInitialText();

    public abstract boolean getInitialTextIsSet();

    public abstract String getContextText();

    public abstract String getErrorClass();

    public abstract boolean getErrorClassIsSet();

    public static final RowType TYPE = new RowType("error_message", "config");

}
