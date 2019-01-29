head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.21.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondBalanceReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3BondBalanceReferenceRequestTest extends TestBaseForMock
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceRequestTest.class);

    public WEB3BondBalanceReferenceRequestTest(String arg0)
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

    public void testValidate1()
    {
        final String STR_METHOD_NAME = "testValidate1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3BondBalanceReferenceRequest l_request =
                new WEB3BondBalanceReferenceRequest();
            WEB3BondSortKey l_key = new WEB3BondSortKey();
            l_key.keyItem = "productName";
            l_key.ascDesc = "A";
            l_request.sortKeys = new WEB3BondSortKey[1];
            l_request.sortKeys[0] = l_key;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.referenceType = "6";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00082.error_code, l_ex.getErrorInfo().error_code);
        }
        catch(Exception l_ex)
        {
            fail();
        }
        
    }
    
    public void testValidate2()
    {
        final String STR_METHOD_NAME = "testValidate2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3BondBalanceReferenceRequest l_request = 
                new WEB3BondBalanceReferenceRequest();
            WEB3BondSortKey l_key = new WEB3BondSortKey();
            l_key.keyItem = "productName";
            l_key.ascDesc = "A";
            l_request.sortKeys = new WEB3BondSortKey[1];
            l_request.sortKeys[0] = l_key;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.referenceType = "1";
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("l_ex === " + l_ex.getErrorMessage());
            fail();
        }
        catch(Exception l_ex)
        {
            fail();
        }
        
    }
    
}
@
