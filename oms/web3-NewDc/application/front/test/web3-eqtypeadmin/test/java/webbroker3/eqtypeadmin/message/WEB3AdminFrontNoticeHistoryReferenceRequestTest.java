head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.53.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFrontNoticeHistoryReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.WEB3EqtypeAdminAppPluginTest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFrontNoticeHistoryReferenceRequestTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EqtypeAdminAppPluginTest.class);
    
    public WEB3AdminFrontNoticeHistoryReferenceRequestTest(String name)
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
    
    public void test_validate_C0001()
    {
        String STR_METHOD_NAME = "test_validate_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFrontNoticeHistoryReferenceRequest l_adminFrontNoticeHistoryReferenceRequest =
            new WEB3AdminFrontNoticeHistoryReferenceRequest();
        l_adminFrontNoticeHistoryReferenceRequest.convertMarketCode = "asd";
        try
        {
            l_adminFrontNoticeHistoryReferenceRequest.validate();
            fail();
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01109,l_ex.getErrorInfo());
//            l_ex.printStackTrace();
        }
    }

    public void test_validate_C0002()
    {
        String STR_METHOD_NAME = "test_validate_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFrontNoticeHistoryReferenceRequest l_adminFrontNoticeHistoryReferenceRequest =
            new WEB3AdminFrontNoticeHistoryReferenceRequest();
        l_adminFrontNoticeHistoryReferenceRequest.convertMarketCode = "asd";
        l_adminFrontNoticeHistoryReferenceRequest.productType = "2";
        
        try
        {
            l_adminFrontNoticeHistoryReferenceRequest.validate();
            fail();
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02395,l_ex.getErrorInfo());
//            l_ex.printStackTrace();
        }
    }


    public void test_validate_C0003()
    {
        String STR_METHOD_NAME = "test_validate_C0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFrontNoticeHistoryReferenceRequest l_adminFrontNoticeHistoryReferenceRequest =
            new WEB3AdminFrontNoticeHistoryReferenceRequest();
        l_adminFrontNoticeHistoryReferenceRequest.convertMarketCode = "asd";
        l_adminFrontNoticeHistoryReferenceRequest.productType = "1";
        l_adminFrontNoticeHistoryReferenceRequest.branchCode = "123";
        l_adminFrontNoticeHistoryReferenceRequest.accountCode = "123456";
        l_adminFrontNoticeHistoryReferenceRequest.productCode = "asd";
        
        try
        {
            l_adminFrontNoticeHistoryReferenceRequest.validate();
            fail();
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00815,l_ex.getErrorInfo());
//            l_ex.printStackTrace();
        }
    }
    public void test_validate_C0004()
    {
        String STR_METHOD_NAME = "test_validate_C0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFrontNoticeHistoryReferenceRequest l_adminFrontNoticeHistoryReferenceRequest =
            new WEB3AdminFrontNoticeHistoryReferenceRequest();
        l_adminFrontNoticeHistoryReferenceRequest.convertMarketCode = "asd";
        l_adminFrontNoticeHistoryReferenceRequest.productType = "1";
        l_adminFrontNoticeHistoryReferenceRequest.branchCode = "123";
        l_adminFrontNoticeHistoryReferenceRequest.accountCode = "123456";
        l_adminFrontNoticeHistoryReferenceRequest.productCode = "123456789";
        
        try
        {
            l_adminFrontNoticeHistoryReferenceRequest.validate();
            fail();
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00439,l_ex.getErrorInfo());
//            l_ex.printStackTrace();
        }
    }
    
    public void test_validate_C0005()
    {
        String STR_METHOD_NAME = "test_validate_C0005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFrontNoticeHistoryReferenceRequest l_adminFrontNoticeHistoryReferenceRequest =
            new WEB3AdminFrontNoticeHistoryReferenceRequest();
        l_adminFrontNoticeHistoryReferenceRequest.convertMarketCode = "asd";
        l_adminFrontNoticeHistoryReferenceRequest.productType = "6";
        l_adminFrontNoticeHistoryReferenceRequest.branchCode = "123";
        l_adminFrontNoticeHistoryReferenceRequest.accountCode = "123456";
        l_adminFrontNoticeHistoryReferenceRequest.productCode = "12345";
        
        try
        {
            l_adminFrontNoticeHistoryReferenceRequest.validate();
            fail();
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00439,l_ex.getErrorInfo());
        }
    }
    
    public void test_validate_C0006()
    {
        String STR_METHOD_NAME = "test_validate_C0006()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFrontNoticeHistoryReferenceRequest l_adminFrontNoticeHistoryReferenceRequest =
            new WEB3AdminFrontNoticeHistoryReferenceRequest();
        l_adminFrontNoticeHistoryReferenceRequest.convertMarketCode = "asd";
        l_adminFrontNoticeHistoryReferenceRequest.productType = "1";
        l_adminFrontNoticeHistoryReferenceRequest.branchCode = "123";
        l_adminFrontNoticeHistoryReferenceRequest.accountCode = "123456";
        l_adminFrontNoticeHistoryReferenceRequest.productCode = "12345";
        
        try
        {
            l_adminFrontNoticeHistoryReferenceRequest.validate();
            fail();
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02217,l_ex.getErrorInfo());
        }
    }
    
    public void test_validate_C0007()
    {
        String STR_METHOD_NAME = "test_validate_C0007()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFrontNoticeHistoryReferenceRequest l_adminFrontNoticeHistoryReferenceRequest =
            new WEB3AdminFrontNoticeHistoryReferenceRequest();
        l_adminFrontNoticeHistoryReferenceRequest.convertMarketCode = "asd";
        l_adminFrontNoticeHistoryReferenceRequest.productType = "6";
        l_adminFrontNoticeHistoryReferenceRequest.branchCode = "123";
        l_adminFrontNoticeHistoryReferenceRequest.accountCode = "123456";
        l_adminFrontNoticeHistoryReferenceRequest.productCode = "123456789";
        
        try
        {
            l_adminFrontNoticeHistoryReferenceRequest.validate();
            fail();
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02217,l_ex.getErrorInfo());
        }
    }
    
    public void test_validate_C0008()
    {
        String STR_METHOD_NAME = "test_validate_C0008()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFrontSortKey l_adminFrontSortKey = new WEB3AdminFrontSortKey();
        l_adminFrontSortKey.keyItem = "not keyItem";

        WEB3AdminFrontNoticeHistoryReferenceRequest l_adminFrontNoticeHistoryReferenceRequest =
            new WEB3AdminFrontNoticeHistoryReferenceRequest();
        
        l_adminFrontNoticeHistoryReferenceRequest.convertMarketCode = "asd";
        l_adminFrontNoticeHistoryReferenceRequest.productType = "6";
        l_adminFrontNoticeHistoryReferenceRequest.branchCode = "123";
        l_adminFrontNoticeHistoryReferenceRequest.accountCode = "123456";
        l_adminFrontNoticeHistoryReferenceRequest.productCode = "123456789";
        l_adminFrontNoticeHistoryReferenceRequest.createdTimestamp = "2007";
        l_adminFrontNoticeHistoryReferenceRequest.createdTimestampFrom = "200703";
        l_adminFrontNoticeHistoryReferenceRequest.createdTimestampTo = "200704";
        WEB3AdminFrontSortKey[] l_adminFrontSortKey1 = {l_adminFrontSortKey};
        l_adminFrontNoticeHistoryReferenceRequest.sortKeys = l_adminFrontSortKey1;
        try
        {
            l_adminFrontNoticeHistoryReferenceRequest.validate();
            fail();
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
        }
    }
    
    public void test_validate_C0009()
    {
        String STR_METHOD_NAME = "test_validate_C0009()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFrontSortKey l_adminFrontSortKey = new WEB3AdminFrontSortKey();
        l_adminFrontSortKey.keyItem = "createdTimestamp";
        
        WEB3AdminFrontSortKey l_adminFrontSortKey1 = new WEB3AdminFrontSortKey();
        l_adminFrontSortKey1.keyItem = "two keyItem";

        WEB3AdminFrontNoticeHistoryReferenceRequest l_adminFrontNoticeHistoryReferenceRequest =
            new WEB3AdminFrontNoticeHistoryReferenceRequest();
        
        l_adminFrontNoticeHistoryReferenceRequest.convertMarketCode = "asd";
        l_adminFrontNoticeHistoryReferenceRequest.productType = "6";
        l_adminFrontNoticeHistoryReferenceRequest.branchCode = "123";
        l_adminFrontNoticeHistoryReferenceRequest.accountCode = "123456";
        l_adminFrontNoticeHistoryReferenceRequest.productCode = "123456789";
        l_adminFrontNoticeHistoryReferenceRequest.createdTimestamp = "2007";
        l_adminFrontNoticeHistoryReferenceRequest.createdTimestampFrom = "200703";
        l_adminFrontNoticeHistoryReferenceRequest.createdTimestampTo = "200704";
        WEB3AdminFrontSortKey[] l_adminFrontSortKeyArray = {l_adminFrontSortKey , l_adminFrontSortKey1};
        l_adminFrontNoticeHistoryReferenceRequest.sortKeys = l_adminFrontSortKeyArray;
        try
        {
            l_adminFrontNoticeHistoryReferenceRequest.validate();
            fail();
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
        }
    }
}
@
