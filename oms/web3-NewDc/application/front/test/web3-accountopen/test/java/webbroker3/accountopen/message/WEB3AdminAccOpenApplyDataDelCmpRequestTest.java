head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.15.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenApplyDataDelCmpRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者口座開設資料請求データ削除完了リクエスト(WEB3AdminAccOpenApplyDataDelCmpRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 劉仁和(中訊) 新規作成 モデルNo.160
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者口座開設資料請求データ削除完了リクエスト)<BR>
 * 管理者口座開設資料請求データ削除完了リクエスト<BR>
 * <BR>
 * @@author 劉仁和
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelCmpRequestTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyDataDelCmpRequestTest.class);

    WEB3AdminAccOpenApplyDataDelCmpRequest l_request =
        new WEB3AdminAccOpenApplyDataDelCmpRequest();

    public WEB3AdminAccOpenApplyDataDelCmpRequestTest(String arg0)
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
     * 異常
     * 
     * １）　@識別コードのチェック
     *  １－１）　@未入力の場合、
     *   （BUSINESS_ERROR_00829）例外をスローする。
     */
    public void testValidate_C001()
    {
        final String STR_METHOD_NAME = "testValidate_C001()";
        log.entering(STR_METHOD_NAME);
        l_request.requestNumber = null;

        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00829, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 異常
     * 
     *１－２）　@半角数字以外が含まれる場合、
     * （BUSINESS_ERROR_01820）例外をスローする。
     */
    public void testValidate_C002()
    {
        final String STR_METHOD_NAME = "testValidate_C002()";
        log.entering(STR_METHOD_NAME);
        l_request.requestNumber = "1ac2";

        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01820, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 異常
     * 
     * ２）　@確認フラグのチェック
     *  ２－１）　@this.確認フラグが未チェックの場合、
     * （BUSINESS_ERROR_03141）例外をスローする。
     */
    public void testValidate_C003()
    {
        final String STR_METHOD_NAME = "testValidate_C003()";
        log.entering(STR_METHOD_NAME);
        l_request.requestNumber = "0";

        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03141, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 正常
     */
    public void testValidate_C004()
    {
        final String STR_METHOD_NAME = "testValidate_C004()";
        log.entering(STR_METHOD_NAME);
        l_request.requestNumber = "123";
        l_request.checkFlag = "1";

        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            fail();
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
