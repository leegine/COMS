head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.24.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTempRestraintTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : ÅiWEB3TPTempRestraintTest.javaÅj
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/05 ÉgÉEñNç| (íÜêu) êVãKçÏê¨
*/
package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPTempRestraintTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTempRestraintTest.class);
    
    WEB3TPCalcCondition l_calcCondition = null;

    public WEB3TPTempRestraintTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        init();
    }

    protected void tearDown() throws Exception
    {
        l_calcCondition = null;
        super.tearDown();
    }
    
    /**
     * à¯êî.éÛìnì˙ ÅÑ T+5 ÇÃèÍçá
     *
     */
    public void testCalcReflectDay_case0001()
    {
        final String STR_METHOD_NAME = "testCalcReflectDay_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPTempRestraint l_tempRestraint = new WEB3TPTempRestraint();
            l_tempRestraint.setCalcCondition(l_calcCondition);
            Date l_datDeliveryDate = WEB3DateUtility.getDate("20071105", "yyyyMMdd");
            l_tempRestraint.calcReflectDay(l_datDeliveryDate);
            
            assertEquals(WEB3DateUtility.getDate("20071012", "yyyyMMdd"), l_tempRestraint.getReflectStartDay());
            assertEquals(WEB3DateUtility.getDate("20071012", "yyyyMMdd"), l_tempRestraint.getReflectEndDay());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * à¯êî.éÛìnì˙ ÅÉ T+0 ÇÃèÍçá
     *
     */
    public void testCalcReflectDay_case0002()
    {
        final String STR_METHOD_NAME = "testCalcReflectDay_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPTempRestraint l_tempRestraint = new WEB3TPTempRestraint();
            l_tempRestraint.setCalcCondition(l_calcCondition);
            Date l_datDeliveryDate = WEB3DateUtility.getDate("20070905", "yyyyMMdd");
            l_tempRestraint.calcReflectDay(l_datDeliveryDate);
            
            assertEquals(WEB3DateUtility.getDate("20071007", "yyyyMMdd"), l_tempRestraint.getReflectStartDay());
            assertEquals(WEB3DateUtility.getDate("20071012", "yyyyMMdd"), l_tempRestraint.getReflectEndDay());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ÇªÇÃëºÇÃèÍçá
     *
     */
    public void testCalcReflectDay_case0003()
    {
        final String STR_METHOD_NAME = "testCalcReflectDay_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3TPTempRestraint l_tempRestraint = new WEB3TPTempRestraint();
            l_tempRestraint.setCalcCondition(l_calcCondition);
            Date l_datDeliveryDate = WEB3DateUtility.getDate("20071008", "yyyyMMdd");
            l_tempRestraint.calcReflectDay(l_datDeliveryDate);
            
            assertEquals(WEB3DateUtility.getDate("20071008", "yyyyMMdd"), l_tempRestraint.getReflectStartDay());
            assertEquals(WEB3DateUtility.getDate("20071012", "yyyyMMdd"), l_tempRestraint.getReflectEndDay());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateTempRestraint_case0001()
    {
        final String STR_METHOD_NAME = "testCreateTempRestraint_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            double l_dbRestraint = 0;
            String l_strRestraintDiv = "1";
            Date l_datTransactionDate = WEB3DateUtility.getDate("20071005", "yyyyMMdd");
            Date l_datDeliveryDate = WEB3DateUtility.getDate("20071105", "yyyyMMdd");
            WEB3TPTempRestraint l_tempRestraint = 
                WEB3TPTempRestraint.createTempRestraint(l_calcCondition,
                l_dbRestraint,
                l_datTransactionDate,
                l_datDeliveryDate,
                l_strRestraintDiv);
            
            assertEquals(WEB3TPCalcCondition.class, l_tempRestraint.getCalcCondition().getClass());
            assertEquals("0.0", l_tempRestraint.getAmount() + "");
            assertEquals(WEB3DateUtility.getDate("20071005", "yyyyMMdd"), l_tempRestraint.getTransactionDate());
            assertEquals(WEB3DateUtility.getDate("20071012", "yyyyMMdd"), l_tempRestraint.getReflectStartDay());
            assertEquals(WEB3DateUtility.getDate("20071012", "yyyyMMdd"), l_tempRestraint.getReflectEndDay());
            assertEquals("1",l_tempRestraint.getRestraintDiv());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void init()
    {
        l_calcCondition = new WEB3TPCalcCondition();
        Date[] bizDate = new Date[8];
        bizDate[0] = WEB3DateUtility.getDate("20071005", "yyyyMMdd");
        bizDate[1] = WEB3DateUtility.getDate("20071006", "yyyyMMdd");
        bizDate[2] = WEB3DateUtility.getDate("20071007", "yyyyMMdd");
        bizDate[3] = WEB3DateUtility.getDate("20071008", "yyyyMMdd");
        bizDate[4] = WEB3DateUtility.getDate("20071009", "yyyyMMdd");
        bizDate[5] = WEB3DateUtility.getDate("20071010", "yyyyMMdd");
        bizDate[6] = WEB3DateUtility.getDate("20071011", "yyyyMMdd");
        bizDate[7] = WEB3DateUtility.getDate("20071012", "yyyyMMdd");
        l_calcCondition.setBizDate(bizDate);
    }
}
@
