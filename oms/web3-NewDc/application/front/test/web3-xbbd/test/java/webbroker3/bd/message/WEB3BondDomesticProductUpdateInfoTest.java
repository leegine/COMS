head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.20.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondDomesticProductUpdateInfoTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (国内債券銘柄更新情報)<BR>
 * 国内債券銘柄更新情報<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3BondDomesticProductUpdateInfoTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticProductUpdateInfoTest.class);
    WEB3BondDomesticProductUpdateInfo l_info = new WEB3BondDomesticProductUpdateInfo();
    
    public WEB3BondDomesticProductUpdateInfoTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testValidate_case0001()
    {
        final String STR_METHOD_NAME = "testValidate_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("取扱区分が未指定です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_case0002()
    {
        final String STR_METHOD_NAME = "testValidate_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "1";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("取扱区分の値が存在しないコード値です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0003()
    {
        final String STR_METHOD_NAME = "testValidate_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "0";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("売買区分が未指定です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0004()
    {
        final String STR_METHOD_NAME = "testValidate_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("売買区分が未指定です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0005()
    {
        final String STR_METHOD_NAME = "testValidate_case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "2";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("売買区分の値が存在しないコード値です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0006()
    {
        final String STR_METHOD_NAME = "testValidate_case0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("応募開始日（WEB3)が未指定です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0007()
    {
        final String STR_METHOD_NAME = "testValidate_case0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("応募終了日（WEB3)が未指定です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0008()
    {
        final String STR_METHOD_NAME = "testValidate_case0008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("応募開始日（インターネット)が未指定です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_case0009()
    {
        final String STR_METHOD_NAME = "testValidate_case0009()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("応募終了日（インターネット)が未指定です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0010()
    {
        final String STR_METHOD_NAME = "testValidate_case0010()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("受渡日が未指定です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0011()
    {
        final String STR_METHOD_NAME = "testValidate_case0011()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("銘柄名（WEB3)が未指定です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0012()
    {
        final String STR_METHOD_NAME = "testValidate_case0012()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "12345678901234567890123456789012345678901234567890123456789012345";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("銘柄名（WEB3)のサイズが不正です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0013()
    {
        final String STR_METHOD_NAME = "testValidate_case0013()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012345678901234567890123456789012345678901234";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("申込単位が未指定です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0014()
    {
        final String STR_METHOD_NAME = "testValidate_case0014()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "123456789012";
            l_info.applyUnit = "123456789012";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("申込単位のサイズが不正です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0015()
    {
        final String STR_METHOD_NAME = "testValidate_case0015()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234jkkl";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("申込単位が整数以外の値です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0016()
    {
        final String STR_METHOD_NAME = "testValidate_case0016()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "000000000";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("申込単位が0以下の値です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0017()
    {
        final String STR_METHOD_NAME = "testValidate_case0017()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234567";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("最低額面が未指定です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0018()
    {
        final String STR_METHOD_NAME = "testValidate_case0018()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234567";
            l_info.minFaceAmount = "1234567890123";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("最低額面のサイズが不正です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0019()
    {
        final String STR_METHOD_NAME = "testValidate_case0019()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234567";
            l_info.minFaceAmount = "123sadf";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("最低額面が整数以外の値です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0020()
    {
        final String STR_METHOD_NAME = "testValidate_case0020()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234567";
            l_info.minFaceAmount = "-1";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("最低額面が0より小さい値です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0021()
    {
        final String STR_METHOD_NAME = "testValidate_case0021()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234567";
            l_info.minFaceAmount = "123456";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("最高額面が未指定です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0022()
    {
        final String STR_METHOD_NAME = "testValidate_case0022()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234567";
            l_info.minFaceAmount = "123456";
            l_info.maxFaceAmount = "12345678901235";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("最高額面のサイズが不正です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0023()
    {
        final String STR_METHOD_NAME = "testValidate_case0023()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234567";
            l_info.minFaceAmount = "123456";
            l_info.maxFaceAmount = "12345kjskd";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("最高額面が整数以外の値です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0024()
    {
        final String STR_METHOD_NAME = "testValidate_case0024()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234567";
            l_info.minFaceAmount = "123456";
            l_info.maxFaceAmount = "-1";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("最高額面が0より小さい値です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0025()
    {
        final String STR_METHOD_NAME = "testValidate_case0025()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234567";
            l_info.minFaceAmount = "123456";
            l_info.maxFaceAmount = "1234567";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("目論見書閲覧チェック区分が未指定です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0026()
    {
        final String STR_METHOD_NAME = "testValidate_case0026()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234567";
            l_info.minFaceAmount = "123456";
            l_info.maxFaceAmount = "1234567";
            l_info.prospectusCheckDiv = "2";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("目論見書閲覧チェック区分が存在しないコード値です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0027()
    {
        final String STR_METHOD_NAME = "testValidate_case0027()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234567";
            l_info.minFaceAmount = "123456";
            l_info.maxFaceAmount = "1234567";
            l_info.prospectusCheckDiv = "0";
            l_info.validate();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0028()
    {
        final String STR_METHOD_NAME = "testValidate_case0028()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234567";
            l_info.minFaceAmount = "123456";
            l_info.maxFaceAmount = "1234567";
            l_info.prospectusCheckDiv = "1";
            l_info.validate();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testValidate_case0029()
    {
        final String STR_METHOD_NAME = "testValidate_case0029()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234.123";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("申込単位が整数以外の値です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0030()
    {
        final String STR_METHOD_NAME = "testValidate_case0030()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "-1";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("申込単位が0以下の値です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0031()
    {
        final String STR_METHOD_NAME = "testValidate_case0031()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234567";
            l_info.minFaceAmount = "123456";
            l_info.maxFaceAmount = "12345.12";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("最高額面が整数以外の値です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0032()
    {
        final String STR_METHOD_NAME = "testValidate_case0032()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_info.tradeHandleDiv = "2";
            l_info.dealingType = "3";
            l_info.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_info.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_info.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_info.productNameWEB3 = "1234567890123456789012890";
            l_info.applyUnit = "1234567";
            l_info.minFaceAmount = "123.12";
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("最低額面が整数以外の値です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
