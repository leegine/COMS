head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.00.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOpenContractChangeSpecTest_ES.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoOpenContractChangeSpec
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/11　@孟亜南(中訊)
*/
package webbroker3.ifo;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;

import webbroker3.mock.TestBaseForMock;

public class WEB3IfoOpenContractChangeSpecTest_ES extends TestBaseForMock
{

    public WEB3IfoOpenContractChangeSpecTest_ES(String name)
    {
        super(name);
    }

    /**
     * 新規建訂正内容
     */
    public void test_createIfoOpenContractChangeSpec_0001()
    {
        long l_lngOrderId = 0L;
        long l_lngOrderUnitId = 0L;
        double l_dblQuantityAfterChange = 0D;
        double l_dblLimitPriceAfterChange = 0D;
        IfoOrderExecutionConditionType l_changeExecCondType = new IfoOrderExecutionConditionType(1,"");
        Date l_datChangeExpirationDate = new Date();
        Date l_datOrderBizDate = new Date();
        String l_strOrderCond = "00";
        String l_strOrderCondOperator = "00";
        String l_strStopPriceType = "00";
        double l_dblStopOrderPrice = 0D;
        double l_dblWLimitPrice = 0D;
        IfoOrderExecutionConditionType l_wLimitExecCondType = new IfoOrderExecutionConditionType(1,"");
        String l_strWLimitEnableStatusDiv = "00";
        String l_strExpirationDateType = "00";
        boolean l_blnEveningSessionCarryoverFlag = true;
        
        WEB3IfoOpenContractChangeSpec l_spec = WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
            l_lngOrderId,
            l_lngOrderUnitId,
            l_dblQuantityAfterChange,
            l_dblLimitPriceAfterChange,
            l_changeExecCondType,
            l_datChangeExpirationDate,
            l_datOrderBizDate,
            l_strOrderCond,
            l_strOrderCondOperator,
            l_strStopPriceType,
            l_dblStopOrderPrice,
            l_dblWLimitPrice,
            l_wLimitExecCondType,
            l_strWLimitEnableStatusDiv,
            l_strExpirationDateType,
            l_blnEveningSessionCarryoverFlag);
        
        assertTrue(l_spec.getEveningSessionCarryoverFlag());
        
        l_spec.setEveningSessionCarryoverFlag(false);
        
        assertFalse(l_spec.getEveningSessionCarryoverFlag());
        
    }
}
@
