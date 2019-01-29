head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.21.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondDomesticApplyInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募入力リクエスト(WEB3BondDomesticApplyInputRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/02 韓斌 (中訊) 新規作成 仕様変更・モデル227
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3BondDomesticApplyInputRequestTest extends TestBaseForMock
{

    public WEB3BondDomesticApplyInputRequestTest(String arg0)
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
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyInputRequestTest.class);
    
    WEB3BondDomesticApplyInputRequest l_request =
        new WEB3BondDomesticApplyInputRequest();
    

    /*
     * Test method for 'webbroker3.bd.message.WEB3BondDomesticApplyInputRequest.validate()'
     */
    public void testValidate1()
    {
        final String STR_METHOD_MAME = " testValidate1()";
        log.entering(TEST_START + STR_METHOD_MAME);
        
        l_request.productId = null;
        try
        {
            l_request.validate();
            fail();
        }
        
        catch(WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_ex.getErrorInfo());
            
        }
        catch(Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME); 
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_MAME);
    }
    public void testValidate2()
    {
        final String STR_METHOD_MAME = " testValidate2()";
        log.entering(TEST_START + STR_METHOD_MAME);
        
        l_request.productId = "1";
        
        try
        {
            l_request.validate();
        }
        
        catch(WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME);
            fail();
            
        }
        catch(Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME); 
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_MAME);
    }

}
@
