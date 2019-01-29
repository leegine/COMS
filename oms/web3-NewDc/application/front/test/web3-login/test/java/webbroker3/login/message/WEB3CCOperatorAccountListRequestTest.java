head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.35.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3CCOperatorAccountListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CCオペレータ対象顧客一覧リクエストのテストクラス(WEB3CCOperatorAccountListRequestTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 周墨洋 (中訊) 新規作成 モデルNo.039
*/

package webbroker3.login.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (CCオペレータ対象顧客一覧リクエスト)<BR>
 * CCオペレータ対象顧客一覧リクエストのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3CCOperatorAccountListRequestTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3CCOperatorAccountListRequestTest.class);

    /**
     * CCオペレータ対象顧客一覧リクエスト
     */
    private WEB3CCOperatorAccountListRequest l_ccOperatorAccountListRequest = null;

    /**
     * @@param arg0
     */
    public WEB3CCOperatorAccountListRequestTest(String arg0)
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

        l_ccOperatorAccountListRequest = new WEB3CCOperatorAccountListRequest();

        l_ccOperatorAccountListRequest.sortKeys = null;

        try
        {
            l_ccOperatorAccountListRequest.validate();

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

        l_ccOperatorAccountListRequest =
            new WEB3CCOperatorAccountListRequest();

        WEB3TraderAccountInfosSortKey[] l_traderAccountInfosSortKey = {};
        l_ccOperatorAccountListRequest.sortKeys = l_traderAccountInfosSortKey;

        try
        {
            l_ccOperatorAccountListRequest.validate();

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

        l_ccOperatorAccountListRequest =
            new WEB3CCOperatorAccountListRequest();

        WEB3TraderAccountInfosSortKey[] l_traderAccountInfosSortKeys =
            new WEB3TraderAccountInfosSortKey[1];

        WEB3TraderAccountInfosSortKey l_traderAccountInfosSortKey =
            new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = null;
        l_traderAccountInfosSortKey.ascDesc = "A";
        l_traderAccountInfosSortKeys[0] = l_traderAccountInfosSortKey;

        l_ccOperatorAccountListRequest.sortKeys = l_traderAccountInfosSortKeys;

        try
        {
            l_ccOperatorAccountListRequest.validate();

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

        l_ccOperatorAccountListRequest =
            new WEB3CCOperatorAccountListRequest();

        WEB3TraderAccountInfosSortKey[] l_traderAccountInfosSortKeys =
            new WEB3TraderAccountInfosSortKey[3];

        WEB3TraderAccountInfosSortKey l_traderAccountInfosSortKey =
            new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "acceptCode";
        l_traderAccountInfosSortKey.ascDesc = "A";
        l_traderAccountInfosSortKeys[0] = l_traderAccountInfosSortKey;

        l_traderAccountInfosSortKey = new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "nameKana";
        l_traderAccountInfosSortKey.ascDesc = "D";
        l_traderAccountInfosSortKeys[1] = l_traderAccountInfosSortKey;

        l_traderAccountInfosSortKey = new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "branchCode";
        l_traderAccountInfosSortKey.ascDesc = "A";
        l_traderAccountInfosSortKeys[2] = l_traderAccountInfosSortKey;

        l_ccOperatorAccountListRequest.sortKeys = l_traderAccountInfosSortKeys;

        try
        {
            l_ccOperatorAccountListRequest.validate();

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

        l_ccOperatorAccountListRequest =
            new WEB3CCOperatorAccountListRequest();

        WEB3TraderAccountInfosSortKey[] l_traderAccountInfosSortKeys =
            new WEB3TraderAccountInfosSortKey[2];

        WEB3TraderAccountInfosSortKey l_traderAccountInfosSortKey =
            new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "acceptCode";
        l_traderAccountInfosSortKey.ascDesc = "A";
        l_traderAccountInfosSortKeys[0] = l_traderAccountInfosSortKey;

        l_traderAccountInfosSortKey = new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "nameKana";
        l_traderAccountInfosSortKey.ascDesc = "D";
        l_traderAccountInfosSortKeys[1] = l_traderAccountInfosSortKey;

        l_ccOperatorAccountListRequest.sortKeys = l_traderAccountInfosSortKeys;
        l_ccOperatorAccountListRequest.pageIndex = null;

        try
        {
            l_ccOperatorAccountListRequest.validate();

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

        l_ccOperatorAccountListRequest =
            new WEB3CCOperatorAccountListRequest();

        WEB3TraderAccountInfosSortKey[] l_traderAccountInfosSortKeys =
            new WEB3TraderAccountInfosSortKey[2];

        WEB3TraderAccountInfosSortKey l_traderAccountInfosSortKey =
            new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "acceptCode";
        l_traderAccountInfosSortKey.ascDesc = "A";
        l_traderAccountInfosSortKeys[0] = l_traderAccountInfosSortKey;

        l_traderAccountInfosSortKey = new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "nameKana";
        l_traderAccountInfosSortKey.ascDesc = "D";
        l_traderAccountInfosSortKeys[1] = l_traderAccountInfosSortKey;

        l_ccOperatorAccountListRequest.sortKeys = l_traderAccountInfosSortKeys;
        l_ccOperatorAccountListRequest.pageIndex = "a";

        try
        {
            l_ccOperatorAccountListRequest.validate();

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

        l_ccOperatorAccountListRequest =
            new WEB3CCOperatorAccountListRequest();

        WEB3TraderAccountInfosSortKey[] l_traderAccountInfosSortKeys =
            new WEB3TraderAccountInfosSortKey[2];

        WEB3TraderAccountInfosSortKey l_traderAccountInfosSortKey =
            new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "acceptCode";
        l_traderAccountInfosSortKey.ascDesc = "A";
        l_traderAccountInfosSortKeys[0] = l_traderAccountInfosSortKey;

        l_traderAccountInfosSortKey = new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "nameKana";
        l_traderAccountInfosSortKey.ascDesc = "D";
        l_traderAccountInfosSortKeys[1] = l_traderAccountInfosSortKey;

        l_ccOperatorAccountListRequest.sortKeys = l_traderAccountInfosSortKeys;
        l_ccOperatorAccountListRequest.pageIndex = "-99";

        try
        {
            l_ccOperatorAccountListRequest.validate();

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

        l_ccOperatorAccountListRequest =
            new WEB3CCOperatorAccountListRequest();

        WEB3TraderAccountInfosSortKey[] l_traderAccountInfosSortKeys =
            new WEB3TraderAccountInfosSortKey[2];

        WEB3TraderAccountInfosSortKey l_traderAccountInfosSortKey =
            new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "acceptCode";
        l_traderAccountInfosSortKey.ascDesc = "A";
        l_traderAccountInfosSortKeys[0] = l_traderAccountInfosSortKey;

        l_traderAccountInfosSortKey = new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "nameKana";
        l_traderAccountInfosSortKey.ascDesc = "D";
        l_traderAccountInfosSortKeys[1] = l_traderAccountInfosSortKey;

        l_ccOperatorAccountListRequest.sortKeys = l_traderAccountInfosSortKeys;
        l_ccOperatorAccountListRequest.pageIndex = "0";

        try
        {
            l_ccOperatorAccountListRequest.validate();

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

        l_ccOperatorAccountListRequest =
            new WEB3CCOperatorAccountListRequest();

        WEB3TraderAccountInfosSortKey[] l_traderAccountInfosSortKeys =
            new WEB3TraderAccountInfosSortKey[2];

        WEB3TraderAccountInfosSortKey l_traderAccountInfosSortKey =
            new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "acceptCode";
        l_traderAccountInfosSortKey.ascDesc = "A";
        l_traderAccountInfosSortKeys[0] = l_traderAccountInfosSortKey;

        l_traderAccountInfosSortKey = new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "nameKana";
        l_traderAccountInfosSortKey.ascDesc = "D";
        l_traderAccountInfosSortKeys[1] = l_traderAccountInfosSortKey;

        l_ccOperatorAccountListRequest.sortKeys = l_traderAccountInfosSortKeys;
        l_ccOperatorAccountListRequest.pageIndex = "1";
        l_ccOperatorAccountListRequest.pageSize = null;

        try
        {
            l_ccOperatorAccountListRequest.validate();

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

        l_ccOperatorAccountListRequest =
            new WEB3CCOperatorAccountListRequest();

        WEB3TraderAccountInfosSortKey[] l_traderAccountInfosSortKeys =
            new WEB3TraderAccountInfosSortKey[2];

        WEB3TraderAccountInfosSortKey l_traderAccountInfosSortKey =
            new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "acceptCode";
        l_traderAccountInfosSortKey.ascDesc = "A";
        l_traderAccountInfosSortKeys[0] = l_traderAccountInfosSortKey;

        l_traderAccountInfosSortKey = new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "nameKana";
        l_traderAccountInfosSortKey.ascDesc = "D";
        l_traderAccountInfosSortKeys[1] = l_traderAccountInfosSortKey;

        l_ccOperatorAccountListRequest.sortKeys = l_traderAccountInfosSortKeys;
        l_ccOperatorAccountListRequest.pageIndex = "1";
        l_ccOperatorAccountListRequest.pageSize = "b";

        try
        {
            l_ccOperatorAccountListRequest.validate();

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

        l_ccOperatorAccountListRequest =
            new WEB3CCOperatorAccountListRequest();

        WEB3TraderAccountInfosSortKey[] l_traderAccountInfosSortKeys =
            new WEB3TraderAccountInfosSortKey[2];

        WEB3TraderAccountInfosSortKey l_traderAccountInfosSortKey =
            new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "acceptCode";
        l_traderAccountInfosSortKey.ascDesc = "A";
        l_traderAccountInfosSortKeys[0] = l_traderAccountInfosSortKey;

        l_traderAccountInfosSortKey = new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "nameKana";
        l_traderAccountInfosSortKey.ascDesc = "D";
        l_traderAccountInfosSortKeys[1] = l_traderAccountInfosSortKey;

        l_ccOperatorAccountListRequest.sortKeys = l_traderAccountInfosSortKeys;
        l_ccOperatorAccountListRequest.pageIndex = "1";
        l_ccOperatorAccountListRequest.pageSize = "-11";

        try
        {
            l_ccOperatorAccountListRequest.validate();

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

        l_ccOperatorAccountListRequest =
            new WEB3CCOperatorAccountListRequest();

        WEB3TraderAccountInfosSortKey[] l_traderAccountInfosSortKeys =
            new WEB3TraderAccountInfosSortKey[2];

        WEB3TraderAccountInfosSortKey l_traderAccountInfosSortKey =
            new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "acceptCode";
        l_traderAccountInfosSortKey.ascDesc = "A";
        l_traderAccountInfosSortKeys[0] = l_traderAccountInfosSortKey;

        l_traderAccountInfosSortKey = new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "nameKana";
        l_traderAccountInfosSortKey.ascDesc = "D";
        l_traderAccountInfosSortKeys[1] = l_traderAccountInfosSortKey;

        l_ccOperatorAccountListRequest.sortKeys = l_traderAccountInfosSortKeys;
        l_ccOperatorAccountListRequest.pageIndex = "1";
        l_ccOperatorAccountListRequest.pageSize = "0";

        try
        {
            l_ccOperatorAccountListRequest.validate();

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

        l_ccOperatorAccountListRequest =
            new WEB3CCOperatorAccountListRequest();

        WEB3TraderAccountInfosSortKey[] l_traderAccountInfosSortKeys =
            new WEB3TraderAccountInfosSortKey[2];

        WEB3TraderAccountInfosSortKey l_traderAccountInfosSortKey =
            new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "acceptCode";
        l_traderAccountInfosSortKey.ascDesc = "A";
        l_traderAccountInfosSortKeys[0] = l_traderAccountInfosSortKey;

        l_traderAccountInfosSortKey = new WEB3TraderAccountInfosSortKey();
        l_traderAccountInfosSortKey.keyItem = "nameKana";
        l_traderAccountInfosSortKey.ascDesc = "D";
        l_traderAccountInfosSortKeys[1] = l_traderAccountInfosSortKey;

        l_ccOperatorAccountListRequest.sortKeys = l_traderAccountInfosSortKeys;
        l_ccOperatorAccountListRequest.pageIndex = "1";
        l_ccOperatorAccountListRequest.pageSize = "1";

        try
        {
            l_ccOperatorAccountListRequest.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            assertTrue(true);
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
