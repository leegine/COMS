head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.00.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoChangeSettleContractOrderSpecTest_ES.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ԍϒ������e
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/14�@@�Ј���(���u)
*/
package webbroker3.ifo;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;

import webbroker3.mock.TestBaseForMock;

/**
 * �ԍϒ������e
 * @@author �Ј���
 *
 */
public class WEB3IfoChangeSettleContractOrderSpecTest_ES extends TestBaseForMock
{

    public WEB3IfoChangeSettleContractOrderSpecTest_ES(String name)
    {
        super(name);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * create�ԍϒ������e
     *
     */
    public void test_createIfoChangeSettleContractOrderSpec_0001()
    {
        long l_lngOrderId = 0L;
        long l_lngOrderUnitId = 0L; 
        double l_dblChangeLimitPrice = 0; 
        SettleContractEntry[]  l_settleContractEntries = null; 
        IfoOrderExecutionConditionType l_changeExecCondType = null;
        Date l_datChangeExpirationDate = new Date();
        Date l_datOrderBizDate = new Date();
        String l_strOrderCond = "00";
        String l_strOrderCondOperator = "00";
        String l_strStopPriceType = "00";
        double l_dblStopOrderPrice = 0;
        double l_dblWLimitPrice = 0;
        IfoOrderExecutionConditionType l_wLimitExecCondType = null;
        String l_strWLimitEnableStatusDiv = "00";
        String l_strExpirationDateType = "00";
        boolean l_blnEveningSessionCarryoverFlag = true;
        
        WEB3IfoChangeSettleContractOrderSpec l_spec = null;
        
        l_spec = WEB3IfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                l_lngOrderId,
                l_lngOrderUnitId,
                l_dblChangeLimitPrice,
                l_settleContractEntries,
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
