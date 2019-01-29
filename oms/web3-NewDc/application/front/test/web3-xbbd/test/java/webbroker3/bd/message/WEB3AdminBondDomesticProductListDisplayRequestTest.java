head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.21.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondDomesticProductListDisplayRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券銘柄一覧画面表示リクエスト(WEB3AdminBondDomesticProductListDisplayRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/07/13 周墨洋 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 *
 */
public class WEB3AdminBondDomesticProductListDisplayRequestTest
    extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminBondDomesticProductListDisplayRequestTest.class);

    /**
     * 管理者国内債券銘柄一覧画面表示リクエスト
     */
    private WEB3AdminBondDomesticProductListDisplayRequest
        l_adminBondDomesticProductListDisplayRequest = null;

    /**
     * @@param arg0
     */
    public WEB3AdminBondDomesticProductListDisplayRequestTest(String arg0)
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

        l_adminBondDomesticProductListDisplayRequest =
            new WEB3AdminBondDomesticProductListDisplayRequest();

        l_adminBondDomesticProductListDisplayRequest.sortKeys = null;

        try
        {
            l_adminBondDomesticProductListDisplayRequest.validate();

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
    public void testValidate_case0002()
    {

        String STR_METHOD_NAME = " testValidate_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_adminBondDomesticProductListDisplayRequest =
            new WEB3AdminBondDomesticProductListDisplayRequest();

        WEB3BondSortKey[] l_bondSortKeys = {};
        l_adminBondDomesticProductListDisplayRequest.sortKeys = l_bondSortKeys;

        try
        {
            l_adminBondDomesticProductListDisplayRequest.validate();

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
    public void testValidate_case0003()
    {

        String STR_METHOD_NAME = " testValidate_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_adminBondDomesticProductListDisplayRequest =
            new WEB3AdminBondDomesticProductListDisplayRequest();

        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "";
        l_bondSortKey.ascDesc = "";

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[5];
        l_bondSortKeys[0] = l_bondSortKey;
        l_adminBondDomesticProductListDisplayRequest.sortKeys = l_bondSortKeys;

        try
        {
            l_adminBondDomesticProductListDisplayRequest.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_exBE.getErrorInfo());

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

        l_adminBondDomesticProductListDisplayRequest =
            new WEB3AdminBondDomesticProductListDisplayRequest();

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[6];

        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productCode";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productIssueCode";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[1] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "issueDate";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[2] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "maturityDate";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[3] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "coupon";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[4] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "accound_code";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[5] = l_bondSortKey;

        l_adminBondDomesticProductListDisplayRequest.sortKeys = l_bondSortKeys;

        try
        {
            l_adminBondDomesticProductListDisplayRequest.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_exBE.getErrorInfo());

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

        l_adminBondDomesticProductListDisplayRequest =
            new WEB3AdminBondDomesticProductListDisplayRequest();

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[5];

        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productCode";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productIssueCode";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[1] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "issueDate";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[2] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "maturityDate";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[3] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "coupon";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[4] = l_bondSortKey;

        l_adminBondDomesticProductListDisplayRequest.sortKeys = l_bondSortKeys;
        l_adminBondDomesticProductListDisplayRequest.pageIndex = null;

        try
        {
            l_adminBondDomesticProductListDisplayRequest.validate();

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
    public void testValidate_case0006()
    {

        String STR_METHOD_NAME = " testValidate_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_adminBondDomesticProductListDisplayRequest =
            new WEB3AdminBondDomesticProductListDisplayRequest();

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[5];

        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productCode";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productIssueCode";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[1] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "issueDate";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[2] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "maturityDate";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[3] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "coupon";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[4] = l_bondSortKey;

        l_adminBondDomesticProductListDisplayRequest.sortKeys = l_bondSortKeys;
        l_adminBondDomesticProductListDisplayRequest.pageIndex = "a";

        try
        {
            l_adminBondDomesticProductListDisplayRequest.validate();

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
    public void testValidate_case0007()
    {

        String STR_METHOD_NAME = " testValidate_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_adminBondDomesticProductListDisplayRequest =
            new WEB3AdminBondDomesticProductListDisplayRequest();

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[5];

        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productCode";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productIssueCode";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[1] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "issueDate";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[2] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "maturityDate";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[3] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "coupon";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[4] = l_bondSortKey;

        l_adminBondDomesticProductListDisplayRequest.sortKeys = l_bondSortKeys;
        l_adminBondDomesticProductListDisplayRequest.pageIndex = "-99";

        try
        {
            l_adminBondDomesticProductListDisplayRequest.validate();

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
    public void testValidate_case0008()
    {

        String STR_METHOD_NAME = " testValidate_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_adminBondDomesticProductListDisplayRequest =
            new WEB3AdminBondDomesticProductListDisplayRequest();

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[5];

        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productCode";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productIssueCode";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[1] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "issueDate";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[2] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "maturityDate";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[3] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "coupon";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[4] = l_bondSortKey;

        l_adminBondDomesticProductListDisplayRequest.sortKeys = l_bondSortKeys;
        l_adminBondDomesticProductListDisplayRequest.pageIndex = "0";

        try
        {
            l_adminBondDomesticProductListDisplayRequest.validate();

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
    public void testValidate_case0009()
    {

        String STR_METHOD_NAME = " testValidate_case0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_adminBondDomesticProductListDisplayRequest =
            new WEB3AdminBondDomesticProductListDisplayRequest();

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[5];

        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productCode";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productIssueCode";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[1] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "issueDate";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[2] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "maturityDate";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[3] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "coupon";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[4] = l_bondSortKey;

        l_adminBondDomesticProductListDisplayRequest.sortKeys = l_bondSortKeys;
        l_adminBondDomesticProductListDisplayRequest.pageIndex = "1";
        l_adminBondDomesticProductListDisplayRequest.pageSize = null;

        try
        {
            l_adminBondDomesticProductListDisplayRequest.validate();

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
    public void testValidate_case0010()
    {

        String STR_METHOD_NAME = " testValidate_case0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_adminBondDomesticProductListDisplayRequest =
            new WEB3AdminBondDomesticProductListDisplayRequest();

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[5];

        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productCode";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productIssueCode";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[1] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "issueDate";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[2] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "maturityDate";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[3] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "coupon";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[4] = l_bondSortKey;

        l_adminBondDomesticProductListDisplayRequest.sortKeys = l_bondSortKeys;
        l_adminBondDomesticProductListDisplayRequest.pageIndex = "1";
        l_adminBondDomesticProductListDisplayRequest.pageSize = "b";

        try
        {
            l_adminBondDomesticProductListDisplayRequest.validate();

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
    public void testValidate_case0011()
    {

        String STR_METHOD_NAME = " testValidate_case0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_adminBondDomesticProductListDisplayRequest =
            new WEB3AdminBondDomesticProductListDisplayRequest();

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[5];

        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productCode";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productIssueCode";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[1] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "issueDate";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[2] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "maturityDate";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[3] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "coupon";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[4] = l_bondSortKey;

        l_adminBondDomesticProductListDisplayRequest.sortKeys = l_bondSortKeys;
        l_adminBondDomesticProductListDisplayRequest.pageIndex = "1";
        l_adminBondDomesticProductListDisplayRequest.pageSize = "-11";

        try
        {
            l_adminBondDomesticProductListDisplayRequest.validate();

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
    public void testValidate_case0012()
    {

        String STR_METHOD_NAME = " testValidate_case0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_adminBondDomesticProductListDisplayRequest =
            new WEB3AdminBondDomesticProductListDisplayRequest();

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[5];

        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productCode";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productIssueCode";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[1] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "issueDate";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[2] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "maturityDate";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[3] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "coupon";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[4] = l_bondSortKey;

        l_adminBondDomesticProductListDisplayRequest.sortKeys = l_bondSortKeys;
        l_adminBondDomesticProductListDisplayRequest.pageIndex = "1";
        l_adminBondDomesticProductListDisplayRequest.pageSize = "0";

        try
        {
            l_adminBondDomesticProductListDisplayRequest.validate();

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
    public void testValidate_case0013()
    {

        String STR_METHOD_NAME = " testValidate_case0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_adminBondDomesticProductListDisplayRequest =
            new WEB3AdminBondDomesticProductListDisplayRequest();

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[5];

        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productCode";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productIssueCode";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[1] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "issueDate";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[2] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "maturityDate";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[3] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "coupon";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[4] = l_bondSortKey;

        l_adminBondDomesticProductListDisplayRequest.sortKeys = l_bondSortKeys;
        l_adminBondDomesticProductListDisplayRequest.pageIndex = "1";
        l_adminBondDomesticProductListDisplayRequest.pageSize = "1";

        WEB3AdminBondDomesticProductListSearchConditionUnit l_searchConditionUnit =
            new WEB3AdminBondDomesticProductListSearchConditionUnit();

        l_searchConditionUnit.bondType = "000";

        l_adminBondDomesticProductListDisplayRequest.searchCondition = l_searchConditionUnit;

        try
        {
            l_adminBondDomesticProductListDisplayRequest.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02869, l_exBE.getErrorInfo());

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

        l_adminBondDomesticProductListDisplayRequest =
            new WEB3AdminBondDomesticProductListDisplayRequest();

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[5];

        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productCode";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productIssueCode";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[1] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "issueDate";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[2] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "maturityDate";
        l_bondSortKey.ascDesc = "D";
        l_bondSortKeys[3] = l_bondSortKey;

        l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "coupon";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[4] = l_bondSortKey;

        l_adminBondDomesticProductListDisplayRequest.sortKeys = l_bondSortKeys;
        l_adminBondDomesticProductListDisplayRequest.pageIndex = "1";
        l_adminBondDomesticProductListDisplayRequest.pageSize = "1";

        WEB3AdminBondDomesticProductListSearchConditionUnit l_searchConditionUnit =
            new WEB3AdminBondDomesticProductListSearchConditionUnit();

        l_searchConditionUnit.bondType = "11";
        l_searchConditionUnit.tradeHandleDiv = "0";

        l_adminBondDomesticProductListDisplayRequest.searchCondition = l_searchConditionUnit;

        try
        {
            l_adminBondDomesticProductListDisplayRequest.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            assertTrue(true);
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
