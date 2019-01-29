head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.12.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTRegistCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者金商法@交付閲覧登録完了リクエストのテストクラス(WEB3AdminFPTRegistCompleteRequestTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/10/10 周墨洋 (中訊) 新規作成 仕様変更・モデルNo.001
*/

package webbroker3.docadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者金商法@交付閲覧登録完了リクエスト)<BR>
 * 管理者金商法@交付閲覧登録完了リクエストのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminFPTRegistCompleteRequestTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminFPTListReferenceRequestTest.class);

    /**
     * 管理者金商法@交付閲覧登録完了リクエスト
     */
    WEB3AdminFPTRegistCompleteRequest l_request = null;

    /**
     * @@param arg0
     */
    public WEB3AdminFPTRegistCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     *
     */
    public void testValidate_case0001()
    {

        String STR_METHOD_NAME = " testValidate_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTRegistCompleteRequest();

        l_request.branchCode = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_exBE.getErrorInfo());

            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    /**
     *
     */
    public void testValidate_case0002()
    {

        String STR_METHOD_NAME = " testValidate_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTRegistCompleteRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        l_request.documentCategoryList[0] = new WEB3FPTDocumentCategoryDetailsInfoUnit();
        l_request.documentCategoryList[0].batoProductCodeAdminInfo = new WEB3FPTBatoProductCodeAdminInfoUnit[1];
        l_request.documentCategoryList[0].batoProductCodeAdminInfo[0] = new WEB3FPTBatoProductCodeAdminInfoUnit();
        l_request.documentCategoryList[0].documentDivList = new WEB3FPTDocumentDivAdminInfoUnit();
        l_request.documentCategoryList[0].documentDivList.documentDiv = "123";
        l_request.documentCategoryList[0].documentDivList.docuCheckDiv = new String();
        l_request.documentCategoryList[0].documentDivList.docuCheckDiv = "12";
        l_request.documentCategoryList[0].batoProductCodeAdminInfo[0].batoProductCode = new String();
        l_request.documentCategoryList[0].batoProductCodeAdminInfo[0].batoProductCode = "1234";
        l_request.documentCategoryList[0].batoProductCodeAdminInfo[0].batoProductCode = "1234567";
        l_request.documentCategoryList[0].batoProductCodeAdminInfo[0].validFlag = "0";

        try
        {
            this.l_request.validate();

            assertTrue(true);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

}
@
