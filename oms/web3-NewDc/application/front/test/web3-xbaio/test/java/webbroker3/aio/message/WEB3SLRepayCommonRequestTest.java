head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SLRepayCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 証券担保ローン返済申込共通リクエストのテストクラス(WEB3SLRepayCommonRequestTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/09/23 何文敏 (中訊) 新規作成
*/
package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 証券担保ローン返済申込共通リクエスト
 * @@author 何文敏
 *
 */

public class WEB3SLRepayCommonRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SLRepayCommonRequestTest.class);
    
    WEB3SLRepayApplyCommonRequest l_request = new WEB3SLRepayApplyCommonRequest();

    public WEB3SLRepayCommonRequestTest(String arg0)
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
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("返済額が未指定です。", l_ex.getErrorMessage());
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
            l_request.repayAmt = "sdkjfl";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("返済額が数字以外の値です。", l_ex.getErrorMessage());
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
            l_request.repayAmt = "１２３５４";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("返済額が数字以外の値です。", l_ex.getErrorMessage());
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
            l_request.repayAmt = "0";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("返済額の値が0以下です。", l_ex.getErrorMessage());
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
            l_request.repayAmt = "-123";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("返済額の値が0以下です。", l_ex.getErrorMessage());
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
            l_request.repayAmt = "1234567890123";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("返済額のサイズが不正です。", l_ex.getErrorMessage());
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
            l_request.repayAmt = "123456";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("返済予定日が未指定です。", l_ex.getErrorMessage());
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
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = WEB3DateUtility.getDate("20070809", "yyyyMMdd");
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("返済予定日が未指定です。", l_ex.getErrorMessage());
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
