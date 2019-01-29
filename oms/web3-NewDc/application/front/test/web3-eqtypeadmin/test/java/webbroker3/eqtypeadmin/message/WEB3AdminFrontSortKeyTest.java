head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.52.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFrontSortKeyTest.java;


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
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFrontSortKeyTest extends TestBaseForMock
{
    public WEB3AdminFrontSortKeyTest(String name)
    {
        super(name);
    }

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontSortKeyTest.class);
    
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    WEB3AdminFrontSortKey l_adminFrontSortKey = new WEB3AdminFrontSortKey();
    public void test_validate_C0001()
    {
        String STR_METHOD_NAME = "test_getFindPossibleMarketCode_C0001()";
        log.entering(STR_METHOD_NAME);
        l_adminFrontSortKey.keyItem = "no keyItem";
        try
        {
            l_adminFrontSortKey.validate();
            fail();
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
        }
    }
    
    public void test_validate_C0002()
    {
        String STR_METHOD_NAME = "test_getFindPossibleMarketCode_C0002()";
        log.entering(STR_METHOD_NAME);
        l_adminFrontSortKey.keyItem = "createdTimestamp";
        try
        {
            l_adminFrontSortKey.validate();
            
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void test_validate_C0003()
    {
        String STR_METHOD_NAME = "test_getFindPossibleMarketCode_C0003()";
        log.entering(STR_METHOD_NAME);
        l_adminFrontSortKey.keyItem = "dataClassCode";
        try
        {
            l_adminFrontSortKey.validate();
            
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void test_validate_C0004()
    {
        String STR_METHOD_NAME = "test_getFindPossibleMarketCode_C0004()";
        log.entering(STR_METHOD_NAME);
        l_adminFrontSortKey.keyItem = "accountCode";
        try
        {
            l_adminFrontSortKey.validate();
            
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    public void test_validate_C0005()
    {
        String STR_METHOD_NAME = "test_getFindPossibleMarketCode_C0005()";
        log.entering(STR_METHOD_NAME);
        l_adminFrontSortKey.keyItem = "branchCode";
        try
        {
            l_adminFrontSortKey.validate();
            
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void test_validate_C0006()
    {
        String STR_METHOD_NAME = "test_getFindPossibleMarketCode_C0006()";
        log.entering(STR_METHOD_NAME);
        l_adminFrontSortKey.keyItem = "productCode";
        try
        {
            l_adminFrontSortKey.validate();
            
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void test_validate_C0007()
    {
        String STR_METHOD_NAME = "test_getFindPossibleMarketCode_C0007()";
        log.entering(STR_METHOD_NAME);
        l_adminFrontSortKey.keyItem = "virtualServerNumber";
        try
        {
            l_adminFrontSortKey.validate();
            
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void test_validate_C0008()
    {
        String STR_METHOD_NAME = "test_getFindPossibleMarketCode_C0008()";
        log.entering(STR_METHOD_NAME);
        l_adminFrontSortKey.keyItem = null;
        try
        {
            l_adminFrontSortKey.validate();
            
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
}
@
