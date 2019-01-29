head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSLProductChangeConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AdminSLProductChangeConfirmRequest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/26 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSLProductChangeConfirmRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminSLProductChangeCompleteRequestTest.class);

    WEB3AdminSLProductChangeConfirmRequest l_request = null;
    public WEB3AdminSLProductChangeConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminSLProductChangeConfirmRequest();
    }

    protected void tearDown() throws Exception
    {
        l_request = null;
        super.tearDown();
    }
    
    public void testValidate_case0001()
    {
        final String STR_METHOD_NAME = "testValidate_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02917, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidate_case0002()
    {
        final String STR_METHOD_NAME = "testValidate_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01444, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidate_case0003()
    {
        final String STR_METHOD_NAME = "testValidate_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02923, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidate_case0004()
    {
        final String STR_METHOD_NAME = "testValidate_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        l_request.changedStockLoanProductInfo = new WEB3SLProductInfoUnit();
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00079, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidate_case0005()
    {
        final String STR_METHOD_NAME = "testValidate_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        l_request.changedStockLoanProductInfo = new WEB3SLProductInfoUnit();
        l_request.changedStockLoanProductInfo.productCode = "abc";
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00815, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidate_case0006()
    {
        final String STR_METHOD_NAME = "testValidate_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        l_request.changedStockLoanProductInfo = new WEB3SLProductInfoUnit();
        l_request.changedStockLoanProductInfo.productCode = "123";
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02394, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidate_case0007()
    {
        final String STR_METHOD_NAME = "testValidate_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        l_request.changedStockLoanProductInfo = new WEB3SLProductInfoUnit();
        l_request.changedStockLoanProductInfo.productCode = "123";
        l_request.changedStockLoanProductInfo.productType = "1";
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01062, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidate_case0008()
    {
        final String STR_METHOD_NAME = "testValidate_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        l_request.changedStockLoanProductInfo = new WEB3SLProductInfoUnit();
        l_request.changedStockLoanProductInfo.productCode = "123";
        l_request.changedStockLoanProductInfo.productType = "1";
        l_request.changedStockLoanProductInfo.productName = "Andy";
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02930, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidate_case0009()
    {
        final String STR_METHOD_NAME = "testValidate_case0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        l_request.changedStockLoanProductInfo = new WEB3SLProductInfoUnit();
        l_request.changedStockLoanProductInfo.productCode = "123";
        l_request.changedStockLoanProductInfo.productType = "1";
        l_request.changedStockLoanProductInfo.productName = "Andy";
        l_request.changedStockLoanProductInfo.qualifiedDiv = "0";
        l_request.changedStockLoanProductInfo.weight = "abc";
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02924, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidate_case0010()
    {
        final String STR_METHOD_NAME = "testValidate_case0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        l_request.changedStockLoanProductInfo = new WEB3SLProductInfoUnit();
        l_request.changedStockLoanProductInfo.productCode = "123";
        l_request.changedStockLoanProductInfo.productType = "1";
        l_request.changedStockLoanProductInfo.productName = "Andy";
        l_request.changedStockLoanProductInfo.qualifiedDiv = "0";
        l_request.changedStockLoanProductInfo.weight = "123";
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01444, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidate_case0011()
    {
        final String STR_METHOD_NAME = "testValidate_case0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        l_request.changedStockLoanProductInfo = new WEB3SLProductInfoUnit();
        l_request.changedStockLoanProductInfo.productCode = "123";
        l_request.changedStockLoanProductInfo.productType = "1";
        l_request.changedStockLoanProductInfo.productName = "Andy";
        l_request.changedStockLoanProductInfo.qualifiedDiv = "0";
        l_request.changedStockLoanProductInfo.weight = "123";
        l_request.changedStockLoanProductInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070929", "yyyyMMdd");
        l_request.changedStockLoanProductInfo.targetPeriodTo = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01446, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidate_case0012()
    {
        final String STR_METHOD_NAME = "testValidate_case0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        l_request.changedStockLoanProductInfo = new WEB3SLProductInfoUnit();
        l_request.changedStockLoanProductInfo.productCode = "123";
        l_request.changedStockLoanProductInfo.productType = "1";
        l_request.changedStockLoanProductInfo.productName = "Andy";
        l_request.changedStockLoanProductInfo.qualifiedDiv = "0";
        l_request.changedStockLoanProductInfo.weight = "123";
        l_request.changedStockLoanProductInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070922", "yyyyMMdd");
        l_request.changedStockLoanProductInfo.targetPeriodTo = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        l_request.changedStockLoanProductInfo.reason = "fgdshfgjgfjdg" +
                "fjsdgfjsdgfjgdsfgdjfgdjsgfjdgfjdg" +
                "sfjsdgfdfdfdfdfdfdfsdffffsfsfcxcd" +
                "sfdfvcvdrsgvrvdxvdrvrbvdfvbdrvrvv" +
                "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv" +
                "vvvvvvvvvvvvvvvdrevsvsvvvsdgerggg" +
                "ggggggggggggggggggggdsjvgiodjfgle" +
                "rngdiovhjernvhjdxkjriohvdhiodhbod" +
                "vnsiodjgoisgodrhgodshgodhghfgdh6h" +
                "thfhdfggggggggggggggggggggggggggg" +
                "ggggggggggggggggggggggggggggggggg" +
                "ggggggggggggg";
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02926, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidate_case0013()
    {
        final String STR_METHOD_NAME = "testValidate_case0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        l_request.changedStockLoanProductInfo = new WEB3SLProductInfoUnit();
        l_request.changedStockLoanProductInfo.productCode = "123";
        l_request.changedStockLoanProductInfo.productType = "1";
        l_request.changedStockLoanProductInfo.productName = "Andy";
        l_request.changedStockLoanProductInfo.qualifiedDiv = "0";
        l_request.changedStockLoanProductInfo.weight = "123";
        l_request.changedStockLoanProductInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070922", "yyyyMMdd");
        l_request.changedStockLoanProductInfo.targetPeriodTo = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        l_request.changedStockLoanProductInfo.reason = "fgdshfgjgfjdg";
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
