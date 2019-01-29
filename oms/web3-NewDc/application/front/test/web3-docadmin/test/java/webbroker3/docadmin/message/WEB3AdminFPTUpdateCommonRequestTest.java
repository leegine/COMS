head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.12.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTUpdateCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者金商法@交付更新共通リクエストのテストクラス(WEB3AdminFPTUpdateCommonRequestTest.java)
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
 * (管理者金商法@交付更新共通リクエスト)<BR>
 * 管理者金商法@交付更新共通リクエストのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminFPTUpdateCommonRequestTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminFPTListReferenceRequestTest.class);

    /**
     * 管理者金商法@交付更新共通リクエスト
     */
    WEB3AdminFPTUpdateCommonRequest l_request = null;

    /**
     * @@param arg0
     */
    public WEB3AdminFPTUpdateCommonRequestTest(String arg0)
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

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

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

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "abc";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_exBE.getErrorInfo());

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
    public void testValidate_case0003()
    {

        String STR_METHOD_NAME = " testValidate_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "-12";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_exBE.getErrorInfo());

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
    public void testValidate_case0004()
    {

        String STR_METHOD_NAME = " testValidate_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "6.6";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_exBE.getErrorInfo());

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
    public void testValidate_case0005()
    {

        String STR_METHOD_NAME = " testValidate_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "12";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834, l_exBE.getErrorInfo());

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
    public void testValidate_case0006()
    {

        String STR_METHOD_NAME = " testValidate_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "1234";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834, l_exBE.getErrorInfo());

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
    public void testValidate_case0007()
    {

        String STR_METHOD_NAME = " testValidate_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00835, l_exBE.getErrorInfo());

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
    public void testValidate_case0008()
    {

        String STR_METHOD_NAME = " testValidate_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "abc";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01043, l_exBE.getErrorInfo());

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
    public void testValidate_case0009()
    {

        String STR_METHOD_NAME = " testValidate_case0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "-99";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01043, l_exBE.getErrorInfo());

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
    public void testValidate_case0010()
    {

        String STR_METHOD_NAME = " testValidate_case0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "8.8";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01043, l_exBE.getErrorInfo());

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
    public void testValidate_case0011()
    {

        String STR_METHOD_NAME = " testValidate_case0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "12345";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00836, l_exBE.getErrorInfo());

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
    public void testValidate_case0012()
    {

        String STR_METHOD_NAME = " testValidate_case0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "1234567";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00836, l_exBE.getErrorInfo());

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
    public void testValidate_case0013()
    {

        String STR_METHOD_NAME = " testValidate_case0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03006, l_exBE.getErrorInfo());

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
    public void testValidate_case0014()
    {

        String STR_METHOD_NAME = " testValidate_case0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        l_request.documentCategoryList[0] = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03006, l_exBE.getErrorInfo());

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
    public void testValidate_case0015()
    {

        String STR_METHOD_NAME = " testValidate_case0015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        l_request.documentCategoryList[0] = new WEB3FPTDocumentCategoryDetailsInfoUnit();
        l_request.documentCategoryList[0].documentDivList = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03007, l_exBE.getErrorInfo());

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
    public void testValidate_case0016()
    {

        String STR_METHOD_NAME = " testValidate_case0016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        l_request.documentCategoryList[0] = new WEB3FPTDocumentCategoryDetailsInfoUnit();
        l_request.documentCategoryList[0].documentDivList = new WEB3FPTDocumentDivAdminInfoUnit();
        l_request.documentCategoryList[0].batoProductCodeAdminInfo = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03008, l_exBE.getErrorInfo());

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
    public void testValidate_case0017()
    {

        String STR_METHOD_NAME = " testValidate_case0017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        l_request.documentCategoryList[0] = new WEB3FPTDocumentCategoryDetailsInfoUnit();
        l_request.documentCategoryList[0].documentDivList = new WEB3FPTDocumentDivAdminInfoUnit();
        l_request.documentCategoryList[0].batoProductCodeAdminInfo = new WEB3FPTBatoProductCodeAdminInfoUnit[1];
        l_request.documentCategoryList[0].documentDivList.documentDiv = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02948, l_exBE.getErrorInfo());

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
    public void testValidate_case0018()
    {

        String STR_METHOD_NAME = " testValidate_case0018()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        l_request.documentCategoryList[0] = new WEB3FPTDocumentCategoryDetailsInfoUnit();
        l_request.documentCategoryList[0].documentDivList = new WEB3FPTDocumentDivAdminInfoUnit();
        l_request.documentCategoryList[0].batoProductCodeAdminInfo = new WEB3FPTBatoProductCodeAdminInfoUnit[1];
        l_request.documentCategoryList[0].documentDivList.documentDiv = "abc";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02941, l_exBE.getErrorInfo());

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
    public void testValidate_case0019()
    {

        String STR_METHOD_NAME = " testValidate_case0019()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        l_request.documentCategoryList[0] = new WEB3FPTDocumentCategoryDetailsInfoUnit();
        l_request.documentCategoryList[0].documentDivList = new WEB3FPTDocumentDivAdminInfoUnit();
        l_request.documentCategoryList[0].batoProductCodeAdminInfo = new WEB3FPTBatoProductCodeAdminInfoUnit[1];
        l_request.documentCategoryList[0].documentDivList.documentDiv = "-12";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02941, l_exBE.getErrorInfo());

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
    public void testValidate_case0020()
    {

        String STR_METHOD_NAME = " testValidate_case0020()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        l_request.documentCategoryList[0] = new WEB3FPTDocumentCategoryDetailsInfoUnit();
        l_request.documentCategoryList[0].documentDivList = new WEB3FPTDocumentDivAdminInfoUnit();
        l_request.documentCategoryList[0].batoProductCodeAdminInfo = new WEB3FPTBatoProductCodeAdminInfoUnit[1];
        l_request.documentCategoryList[0].documentDivList.documentDiv = "5.5";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02941, l_exBE.getErrorInfo());

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
    public void testValidate_case0021()
    {

        String STR_METHOD_NAME = " testValidate_case0021()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        l_request.documentCategoryList[0] = new WEB3FPTDocumentCategoryDetailsInfoUnit();
        l_request.documentCategoryList[0].documentDivList = new WEB3FPTDocumentDivAdminInfoUnit();
        l_request.documentCategoryList[0].batoProductCodeAdminInfo = new WEB3FPTBatoProductCodeAdminInfoUnit[1];
        l_request.documentCategoryList[0].documentDivList.documentDiv = "12345";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02942, l_exBE.getErrorInfo());

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
    public void testValidate_case0022()
    {

        String STR_METHOD_NAME = " testValidate_case0022()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        l_request.documentCategoryList[0] = new WEB3FPTDocumentCategoryDetailsInfoUnit();
        l_request.documentCategoryList[0].documentDivList = new WEB3FPTDocumentDivAdminInfoUnit();
        l_request.documentCategoryList[0].batoProductCodeAdminInfo = new WEB3FPTBatoProductCodeAdminInfoUnit[1];
        l_request.documentCategoryList[0].documentDivList.documentDiv = "123";
        l_request.documentCategoryList[0].documentDivList.docuCheckDiv = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02944, l_exBE.getErrorInfo());

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
    public void testValidate_case0023()
    {

        String STR_METHOD_NAME = " testValidate_case0023()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        l_request.documentCategoryList[0] = new WEB3FPTDocumentCategoryDetailsInfoUnit();
        l_request.documentCategoryList[0].documentDivList = new WEB3FPTDocumentDivAdminInfoUnit();
        l_request.documentCategoryList[0].batoProductCodeAdminInfo = new WEB3FPTBatoProductCodeAdminInfoUnit[1];
        l_request.documentCategoryList[0].documentDivList.documentDiv = "123";
        l_request.documentCategoryList[0].documentDivList.docuCheckDiv = new String();
        l_request.documentCategoryList[0].documentDivList.docuCheckDiv = "abc";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02945, l_exBE.getErrorInfo());

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
    public void testValidate_case0024()
    {

        String STR_METHOD_NAME = " testValidate_case0024()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        l_request.documentCategoryList[0] = new WEB3FPTDocumentCategoryDetailsInfoUnit();
        l_request.documentCategoryList[0].documentDivList = new WEB3FPTDocumentDivAdminInfoUnit();
        l_request.documentCategoryList[0].batoProductCodeAdminInfo = new WEB3FPTBatoProductCodeAdminInfoUnit[1];
        l_request.documentCategoryList[0].documentDivList.documentDiv = "123";
        l_request.documentCategoryList[0].documentDivList.docuCheckDiv = new String();
        l_request.documentCategoryList[0].documentDivList.docuCheckDiv = "-12";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02945, l_exBE.getErrorInfo());

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
    public void testValidate_case0025()
    {

        String STR_METHOD_NAME = " testValidate_case0025()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        l_request.documentCategoryList[0] = new WEB3FPTDocumentCategoryDetailsInfoUnit();
        l_request.documentCategoryList[0].documentDivList = new WEB3FPTDocumentDivAdminInfoUnit();
        l_request.documentCategoryList[0].batoProductCodeAdminInfo = new WEB3FPTBatoProductCodeAdminInfoUnit[1];
        l_request.documentCategoryList[0].documentDivList.documentDiv = "123";
        l_request.documentCategoryList[0].documentDivList.docuCheckDiv = new String();
        l_request.documentCategoryList[0].documentDivList.docuCheckDiv = "5.5";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02945, l_exBE.getErrorInfo());

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
    public void testValidate_case0026()
    {

        String STR_METHOD_NAME = " testValidate_case0026()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

        l_request.branchCode = "381";
        l_request.acceptCode = "225435";
        l_request.docuDeliDate = new Date();
        l_request.documentCategoryList = new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        l_request.documentCategoryList[0] = new WEB3FPTDocumentCategoryDetailsInfoUnit();
        l_request.documentCategoryList[0].documentDivList = new WEB3FPTDocumentDivAdminInfoUnit();
        l_request.documentCategoryList[0].batoProductCodeAdminInfo = new WEB3FPTBatoProductCodeAdminInfoUnit[1];
        l_request.documentCategoryList[0].documentDivList.documentDiv = "123";
        l_request.documentCategoryList[0].documentDivList.docuCheckDiv = new String();
        l_request.documentCategoryList[0].documentDivList.docuCheckDiv = "1234";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02946, l_exBE.getErrorInfo());

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
    public void testValidate_case0027()
    {

        String STR_METHOD_NAME = " testValidate_case0027()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

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
        l_request.documentCategoryList[0].batoProductCodeAdminInfo[0].batoProductCode = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03009, l_exBE.getErrorInfo());

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
    public void testValidate_case0028()
    {

        String STR_METHOD_NAME = " testValidate_case0028()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

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
        l_request.documentCategoryList[0].batoProductCodeAdminInfo[0].batoProductCode = "abc";


        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03010, l_exBE.getErrorInfo());

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
    public void testValidate_case0029()
    {

        String STR_METHOD_NAME = " testValidate_case0029()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

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
        l_request.documentCategoryList[0].batoProductCodeAdminInfo[0].batoProductCode = "-12";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03010, l_exBE.getErrorInfo());

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
    public void testValidate_case0030()
    {

        String STR_METHOD_NAME = " testValidate_case0030()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

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
        l_request.documentCategoryList[0].batoProductCodeAdminInfo[0].batoProductCode = "5.5";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03010, l_exBE.getErrorInfo());

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
    public void testValidate_case0031()
    {

        String STR_METHOD_NAME = " testValidate_case0031()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

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
        l_request.documentCategoryList[0].batoProductCodeAdminInfo[0].batoProductCode = "123";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03011, l_exBE.getErrorInfo());

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
    public void testValidate_case0032()
    {

        String STR_METHOD_NAME = " testValidate_case0032()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

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
        l_request.documentCategoryList[0].batoProductCodeAdminInfo[0].batoProductCode = "12345";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03011, l_exBE.getErrorInfo());

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
    public void testValidate_case0033()
    {

        String STR_METHOD_NAME = " testValidate_case0033()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

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
        l_request.documentCategoryList[0].batoProductCodeAdminInfo[0].batoProductCode = "123456";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03011, l_exBE.getErrorInfo());

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
    public void testValidate_case0034()
    {

        String STR_METHOD_NAME = " testValidate_case0034()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

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
        l_request.documentCategoryList[0].batoProductCodeAdminInfo[0].batoProductCode = "12345678";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03011, l_exBE.getErrorInfo());

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
    public void testValidate_case0035()
    {

        String STR_METHOD_NAME = " testValidate_case0035()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTUpdateCommonRequest();

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
        l_request.documentCategoryList[0].batoProductCodeAdminInfo[0].validFlag = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03012, l_exBE.getErrorInfo());

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
   public void testValidate_case0036()
   {

       String STR_METHOD_NAME = " testValidate_case0036()";
       log.entering(TEST_START + STR_METHOD_NAME);

       this.l_request = new WEB3AdminFPTUpdateCommonRequest();

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
       l_request.documentCategoryList[0].batoProductCodeAdminInfo[0].validFlag = "2";

       try
       {
           this.l_request.validate();

           log.exiting(TEST_END + STR_METHOD_NAME);

           fail();
       }
       catch (WEB3BaseException l_exBE)
       {
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03014, l_exBE.getErrorInfo());

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
  public void testValidate_case0037()
  {

      String STR_METHOD_NAME = " testValidate_case0037()";
      log.entering(TEST_START + STR_METHOD_NAME);

      this.l_request = new WEB3AdminFPTUpdateCommonRequest();

      l_request.branchCode = "381";
      l_request.acceptCode = "225435";
      l_request.docuDeliDate = null;

      try
      {
          this.l_request.validate();

          log.exiting(TEST_END + STR_METHOD_NAME);

          fail();
      }
      catch (WEB3BaseException l_exBE)
      {
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02943, l_exBE.getErrorInfo());

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
   public void testValidate_case0038()
   {

       String STR_METHOD_NAME = " testValidate_case0038()";
       log.entering(TEST_START + STR_METHOD_NAME);

       this.l_request = new WEB3AdminFPTUpdateCommonRequest();

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
