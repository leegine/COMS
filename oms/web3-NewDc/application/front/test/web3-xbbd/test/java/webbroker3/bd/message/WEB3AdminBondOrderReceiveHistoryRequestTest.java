head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.21.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondOrderReceiveHistoryRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注文受付履歴照会リクエスト(WEB3AdminBondOrderReceiveHistoryRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/01 韓斌 (中訊) 新規作成 仕様変更・モデル216
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminBondOrderReceiveHistoryRequestTest extends TestBaseForMock
{

    public WEB3AdminBondOrderReceiveHistoryRequestTest(String arg0)
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
        WEB3LogUtility.getInstance(WEB3AdminBondOrderReceiveHistoryRequestTest.class);
    
    WEB3AdminBondOrderReceiveHistoryRequest l_request = 
        new WEB3AdminBondOrderReceiveHistoryRequest();

    /*
     * Test method for 'webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryRequest.validate()'
     */
    public void testValidate1()
    {
        final String STR_METHOD_NAME = " testValidate1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.productID = null;
        try
        {
            l_request.validate();
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_ex.getErrorInfo());
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
        
        l_request.productID = "1";
        l_request.branchCode = null;
        try
        {
            l_request.validate();
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());
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
        
        l_request.productID = "1";
        l_request.branchCode = "1";
        
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
