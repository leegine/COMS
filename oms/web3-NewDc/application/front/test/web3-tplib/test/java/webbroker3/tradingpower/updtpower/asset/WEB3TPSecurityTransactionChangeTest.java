head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.38.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPSecurityTransactionChangeTest.java;


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
package webbroker3.tradingpower.updtpower.asset;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;

import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


public class WEB3TPSecurityTransactionChangeTest extends TestBaseForMock
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPSecurityTransactionChangeTest.class);
    
    public WEB3TPSecurityTransactionChangeTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * 累積投資かつ、翌日注文受付時間帯かつ約定済の場合
     */
    public void test_calcReflectDay_0001()
    {
        final String STR_METHOD_NAME = "test_calcReflectDay_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3TPSecurityTransactionChange l_change = new WEB3TPSecurityTransactionChange();
        
        l_change.setProductType(ProductTypeEnum.RUITO);
        l_change.setSide(SideEnum.BUY);
        l_change.setExecType("2");
        l_change.setReflectStartDay(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.setEquityNextDayOrderStartDiv(true);
        Date[] l_datBizDate = new Date[]{
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd")};
        l_calcCondition.setBizDate(l_datBizDate);
        l_change.setCalcCondition(l_calcCondition);
        
        
        l_change.calcReflectDay(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        
        assertEquals("0", "" + WEB3DateUtility.compareToDay(l_change.getReflectStartDay(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 累積投資かつ、翌日注文受付時間帯かつ約定済の場合
     */
    public void test_calcReflectDay_0003()
    {
        final String STR_METHOD_NAME = "test_calcReflectDay_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3TPSecurityTransactionChange l_change = new WEB3TPSecurityTransactionChange();
        
        l_change.setProductType(ProductTypeEnum.IFO);
        l_change.setSide(SideEnum.BUY);
        l_change.setExecType("2");
        l_change.setReflectStartDay(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.setEquityNextDayOrderStartDiv(false);
        Date[] l_datBizDate = new Date[]{
                WEB3DateUtility.getDate("20040711","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040711","yyyyMMdd"),
                WEB3DateUtility.getDate("20040711","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd")};
        l_calcCondition.setBizDate(l_datBizDate);
        l_change.setCalcCondition(l_calcCondition);
        
        
        l_change.calcReflectDay(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        
        assertEquals("0", "" + WEB3DateUtility.compareToDay(l_change.getReflectStartDay(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株式かつ、PTS出来終了かつ約定済の場合
     */
    public void test_calcReflectDay_0002()
    {
        final String STR_METHOD_NAME = "test_calcReflectDay_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3TPSecurityTransactionChange l_change = new WEB3TPSecurityTransactionChange();
        
        l_change.setProductType(ProductTypeEnum.EQUITY);
        l_change.setSide(SideEnum.BUY);
        l_change.setExecType("2");
        l_change.setReflectStartDay(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        l_change.setDepositType("0");
        
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.setEquityNextDayOrderStartDiv(true);
        l_calcCondition.setPtsOrderExecutionEndType(true);
        
        Date[] l_datBizDate = new Date[]{
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd")};
        l_calcCondition.setBizDate(l_datBizDate);
        l_change.setCalcCondition(l_calcCondition);
        
        
        l_change.calcReflectDay(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        
        assertEquals("0", "" + WEB3DateUtility.compareToDay(l_change.getReflectStartDay(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株式かつ、PTS出来終了かつ約定済の場合
     */
    public void test_calcReflectDay_0004()
    {
        final String STR_METHOD_NAME = "test_calcReflectDay_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3TPSecurityTransactionChange l_change = new WEB3TPSecurityTransactionChange();
        
        l_change.setProductType(ProductTypeEnum.EQUITY);
        l_change.setSide(SideEnum.BUY);
        l_change.setExecType("2");
        l_change.setReflectStartDay(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        l_change.setDepositType("0");
        
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.setEquityNextDayOrderStartDiv(true);
        l_calcCondition.setPtsOrderExecutionEndType(false);
        
        Date[] l_datBizDate = new Date[]{
                WEB3DateUtility.getDate("20040711","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040711","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd"),
                WEB3DateUtility.getDate("20040716","yyyyMMdd")};
        l_calcCondition.setBizDate(l_datBizDate);
        l_change.setCalcCondition(l_calcCondition);
        
        
        l_change.calcReflectDay(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        
        assertEquals("-5", "" + WEB3DateUtility.compareToDay(l_change.getReflectStartDay(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
