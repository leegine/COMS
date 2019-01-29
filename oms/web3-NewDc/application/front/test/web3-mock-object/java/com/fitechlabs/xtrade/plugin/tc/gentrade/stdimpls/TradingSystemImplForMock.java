head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.59.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	TradingSystemImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : TradingSystemImplForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

//import webbroker3.ifo.WEB3FuturesOrderManagerImplForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


public class TradingSystemImplForMock extends TradingSystemImpl
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(TradingSystemImplForMock.class);
    
    public TradingSystemImplForMock()
    {
        super();
    }

    public Timestamp getSystemTimestamp()
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[] {}))
        {
            return (Timestamp)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {}).asObject();
        }
        return super.getSystemTimestamp();
    }
    
    public Date getBizDate()
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {}))
            {
            return (Date)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {}).asObject();
            }
        return super.getBizDate();
    }
    
    public String getPreference(String name)
    {
        
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getPreference",
                new Class[] {String.class},
                new Object[]{name});
        
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getPreference",
                new Class[] {String.class}))
        {
            log.debug("com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImplForMock.getPreference(String)");
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getPreference",
                    new Class[] {String.class}).asObject();
        }
        return super.getPreference(name);
    }
}
@
