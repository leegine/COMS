head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.20.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondDomesticApplyProductListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募銘柄一覧リクエスト(WEB3BondDomesticApplyProductListRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/02 韓斌 (中訊) 新規作成 仕様変更・モデル227
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3BondDomesticApplyProductListRequestTest extends TestBaseForMock
{

    public WEB3BondDomesticApplyProductListRequestTest(String arg0)
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
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyProductListRequestTest.class);
    
    WEB3BondDomesticApplyProductListRequest l_request = 
        new WEB3BondDomesticApplyProductListRequest();

    /*
     * Test method for 'webbroker3.bd.message.WEB3BondDomesticApplyProductListRequest.validate()'
     */
    public void testValidate1()
    {
        final String STR_METHOD_NAME = " testValidate1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.pageIndex = null;
        
        try
        {
            l_request.validate();
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }
    
    
    public void testValidate2()
    {
        final String STR_METHOD_NAME = " testValidate2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.pageIndex = "jp";
        
        try
        {
            l_request.validate();
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }
    
    public void testValidate3()
    {
        final String STR_METHOD_NAME = " testValidate3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.pageIndex = "123";
        l_request.pageSize = null;
        
        try
        {
            l_request.validate();
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02224, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }
    
    public void testValidate4()
    {
        final String STR_METHOD_NAME = " testValidate4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.pageIndex = "123";
        l_request.pageSize = "jp";
        
        try
        {
            l_request.validate();
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals( WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }
    
    public void testValidate5()
    {
        final String STR_METHOD_NAME = " testValidate5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.pageIndex = "123";
        l_request.pageSize = "123";
        
        try
        {
            l_request.validate();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }

}
@
