head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.01.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceServiceInterceptorForTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

public class WEB3EquityMarginExecuteReferenceServiceInterceptorForTest implements Interceptor
{
    public Object onCall(Method arg0, Object[] arg1)
    {
        String STR_METHOD_NAME = "onCall(Method[],Object[])";
        throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            "onCall ERROR");
    }

	public void onReturn(Object arg0, Object arg1)
	{		
	}

	public void onThrowable(Object arg0, Throwable arg1)
	{
	}
}
@
