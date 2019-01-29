head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.12.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTListReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者金商法@交付閲覧一覧照会リクエストのテストクラス(WEB3AdminFPTListReferenceRequestTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/10/10 周墨洋 (中訊) 新規作成 仕様変更・モデルNo.001
*/

package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者金商法@交付閲覧一覧照会リクエスト)<BR>
 * 管理者金商法@交付閲覧一覧照会リクエストのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminFPTListReferenceRequestTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminFPTListReferenceRequestTest.class);

    /**
     * 管理者金商法@交付閲覧一覧照会リクエスト
     */
    WEB3AdminFPTListReferenceRequest l_request = null;

    /**
     * @@param arg0
     */
    public WEB3AdminFPTListReferenceRequestTest(String arg0)
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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.branchCode = new String[]{"abc"};

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
    public void testValidate_case0002()
    {

        String STR_METHOD_NAME = " testValidate_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.branchCode = new String[]{"10.2"};

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.branchCode = new String[]{"-18"};

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "abc";
        l_request.branchCode = new String[]{"381"};

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
    public void testValidate_case0005()
    {

        String STR_METHOD_NAME = " testValidate_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "10.2";
        l_request.branchCode = new String[]{"381"};

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
    public void testValidate_case0006()
    {

        String STR_METHOD_NAME = " testValidate_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "-18";
        l_request.branchCode = new String[]{"381"};

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
    public void testValidate_case0007()
    {

        String STR_METHOD_NAME = " testValidate_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "01234";
        l_request.branchCode = new String[]{"381"};

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
    public void testValidate_case0008()
    {

        String STR_METHOD_NAME = " testValidate_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "0123456";
        l_request.branchCode = new String[]{"381"};

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
//    public void testValidate_case0009()
//    {
//
//        String STR_METHOD_NAME = " testValidate_case0008()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        this.l_request = new WEB3AdminFPTListReferenceRequest();
//
//        l_request.acceptCode = "012345";
//        l_request.branchCode = new String[]{"381"};
//
//        try
//        {
//            this.l_request.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (WEB3BaseException l_exBE)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00815, l_exBE.getErrorInfo());
//
//            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (Exception l_exE)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_exE);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }

    /**
     *
     */
//    public void testValidate_case0010()
//    {
//
//        String STR_METHOD_NAME = " testValidate_case0010()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        this.l_request = new WEB3AdminFPTListReferenceRequest();
//
//        l_request.acceptCode = "012345";
//        l_request.branchCode = new String[]{"381"};
//
//        try
//        {
//            this.l_request.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (WEB3BaseException l_exBE)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00815, l_exBE.getErrorInfo());
//
//            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (Exception l_exE)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_exE);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }

    /**
     *
     */
//    public void testValidate_case0011()
//    {
//
//        String STR_METHOD_NAME = " testValidate_case0011()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        this.l_request = new WEB3AdminFPTListReferenceRequest();
//
//        l_request.acceptCode = "012345";
//        l_request.branchCode = new String[]{"381"};
//
//        try
//        {
//            this.l_request.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (WEB3BaseException l_exBE)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00815, l_exBE.getErrorInfo());
//
//            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (Exception l_exE)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_exE);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }

    /**
     *
     */
//    public void testValidate_case0012()
//    {
//
//        String STR_METHOD_NAME = " testValidate_case0012()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        this.l_request = new WEB3AdminFPTListReferenceRequest();
//
//        l_request.acceptCode = "012345";
//        l_request.branchCode = new String[]{"381"};
//
//        try
//        {
//            this.l_request.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (WEB3BaseException l_exBE)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00439, l_exBE.getErrorInfo());
//
//            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (Exception l_exE)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_exE);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }

    /**
     *
     */
//    public void testValidate_case0013()
//    {
//
//        String STR_METHOD_NAME = " testValidate_case0013()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        this.l_request = new WEB3AdminFPTListReferenceRequest();
//
//        l_request.acceptCode = "012345";
//        l_request.branchCode = new String[]{"381"};
//
//        try
//        {
//            this.l_request.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (WEB3BaseException l_exBE)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00439, l_exBE.getErrorInfo());
//
//            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (Exception l_exE)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_exE);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }

    /**
     *
     */
//    public void testValidate_case0014()
//    {
//
//        String STR_METHOD_NAME = " testValidate_case0014()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        this.l_request = new WEB3AdminFPTListReferenceRequest();
//
//        l_request.acceptCode = "012345";
//        l_request.branchCode = new String[]{"381"};
//
//        try
//        {
//            this.l_request.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (WEB3BaseException l_exBE)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00439, l_exBE.getErrorInfo());
//
//            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (Exception l_exE)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_exE);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }

    /**
     *
     */
//    public void testValidate_case0015()
//    {
//
//        String STR_METHOD_NAME = " testValidate_case0015()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        this.l_request = new WEB3AdminFPTListReferenceRequest();
//
//        l_request.acceptCode = "012345";
//        l_request.branchCode = new String[]{"381"};
//
//        try
//        {
//            this.l_request.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (WEB3BaseException l_exBE)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00439, l_exBE.getErrorInfo());
//
//            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (Exception l_exE)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_exE);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }

    /**
     *
     */
    public void testValidate_case0016()
    {

        String STR_METHOD_NAME = " testValidate_case0016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = WEB3DateUtility.getDate("20071010", "yyyyMMdd");
        l_request.docuDeliDateTo = WEB3DateUtility.getDate("20071009", "yyyyMMdd");

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02947, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = WEB3DateUtility.getDate("20071010", "yyyyMMdd");
        l_request.docuDeliDateTo = WEB3DateUtility.getDate("20071009", "yyyyMMdd");

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02947, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = null;
        l_request.docuDeliDateTo = null;
        l_request.pageIndex = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = WEB3DateUtility.getDate("20071010", "yyyyMMdd");
        l_request.docuDeliDateTo = null;
        l_request.pageIndex = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = null;
        l_request.docuDeliDateTo = WEB3DateUtility.getDate("20071009", "yyyyMMdd");
        l_request.pageIndex = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = null;
        l_request.docuDeliDateTo = null;
        l_request.pageIndex = "abc";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = null;
        l_request.docuDeliDateTo = null;
        l_request.pageIndex = "2.2";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = null;
        l_request.docuDeliDateTo = null;
        l_request.pageIndex = "0";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = null;
        l_request.docuDeliDateTo = null;
        l_request.pageIndex = "-10";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = null;
        l_request.docuDeliDateTo = null;
        l_request.pageIndex = "2";
        l_request.pageSize = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02224, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = null;
        l_request.docuDeliDateTo = null;
        l_request.pageIndex = "2";
        l_request.pageSize = "abc";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = null;
        l_request.docuDeliDateTo = null;
        l_request.pageIndex = "2";
        l_request.pageSize = "3.3";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = null;
        l_request.docuDeliDateTo = null;
        l_request.pageIndex = "2";
        l_request.pageSize = "0";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = null;
        l_request.docuDeliDateTo = null;
        l_request.pageIndex = "2";
        l_request.pageSize = "-13";

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = null;
        l_request.docuDeliDateTo = null;
        l_request.pageIndex = "2";
        l_request.pageSize = "10";
        l_request.sortKeys = null;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = null;
        l_request.docuDeliDateTo = null;
        l_request.pageIndex = "2";
        l_request.pageSize = "10";
        WEB3AdminFPTSortKey[] l_adminFPTSortKeys = new WEB3AdminFPTSortKey[]{};
        l_request.sortKeys = l_adminFPTSortKeys;

        try
        {
            this.l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_exBE.getErrorInfo());

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

        this.l_request = new WEB3AdminFPTListReferenceRequest();

        l_request.acceptCode = "012345";
        l_request.branchCode = new String[]{"381"};
        l_request.docuDeliDateFrom = null;
        l_request.docuDeliDateTo = null;
        l_request.pageIndex = "2";
        l_request.pageSize = "10";
        WEB3AdminFPTSortKey[] l_adminFPTSortKeys =
            new WEB3AdminFPTSortKey[]{new WEB3AdminFPTSortKey()};
        l_request.sortKeys = l_adminFPTSortKeys;

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
