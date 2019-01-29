head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.16.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenMailAddrRegCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設メールアドレス登録完了リクエスト(WEB3AccOpenMailAddrRegCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 張騰宇(中訊) 新規作成 モデル 162
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenMailAddrRegCompleteRequestTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenMailAddrRegCompleteRequestTest.class);

    public WEB3AccOpenMailAddrRegCompleteRequestTest(String arg0)
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

    /*
     * Test method for 'webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteRequest.validate()'
     */
    //１）　@証券会社コードのチェック 
    //　@１－１）　@未入力の場合、例外をスローする。 
    public void testValidateCase1()
    {
        final String STR_METHOD_NAME = "testValidateCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02775, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //２）　@部店コードのチェック 
    //　@２－１）　@未入力の場合、例外をスローする。 
    public void testValidateCase2()
    {
        final String STR_METHOD_NAME = "testValidateCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.institutionCode = "0D";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //３）　@メールアドレスのチェック 
    //　@３－１）　@未入力の場合、例外をスローする。 
    public void testValidateCase3()
    {
        final String STR_METHOD_NAME = "testValidateCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01700, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //４）　@姓のチェック 
    //　@４－１）　@未入力の場合、例外をスローする。
    public void testValidateCase4()
    {
        final String STR_METHOD_NAME = "testValidateCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
            l_request.mailAddress = "yu@@sinocom.cn";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03167, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //５）　@口座区分のチェック 
    //　@５－１）　@未入力の場合、例外をスローする。 
    public void testValidateCase5()
    {
        final String STR_METHOD_NAME = "testValidateCase5()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
            l_request.mailAddress = "yu@@sinocom.cn";
            l_request.accountFamilyName = "zhang";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00604, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //５）　@口座区分のチェック 
    //　@５－２）　@「0：個人アカウント、1：法@人アカウント」以外の場合、例外をスローする。 
    public void testValidateCase6()
    {
        final String STR_METHOD_NAME = "testValidateCase6()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
            l_request.mailAddress = "yu@@sinocom.cn";
            l_request.accountFamilyName = "zhang";
            l_request.accountType = "2";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00605, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //正常
    public void testValidateCase7()
    {
        final String STR_METHOD_NAME = "testValidateCase7()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
            l_request.mailAddress = "yu@@sinocom.cn";
            l_request.accountFamilyName = "zhang";
            l_request.accountType = "1";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
