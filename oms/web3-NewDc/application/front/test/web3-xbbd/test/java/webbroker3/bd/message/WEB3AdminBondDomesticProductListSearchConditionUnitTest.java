head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.20.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondDomesticProductListSearchConditionUnitTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券銘柄一覧検索条件(WEB3AdminBondDomesticProductListSearchConditionUnitTest.java)
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
public class WEB3AdminBondDomesticProductListSearchConditionUnitTest
    extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminBondDomesticProductListSearchConditionUnitTest.class);

    /**
     * 管理者国内債券銘柄一覧検索条件
     */
    WEB3AdminBondDomesticProductListSearchConditionUnit l_searchConditionUnit = null;

    /**
    *
    * @@param arg0
    */
    public WEB3AdminBondDomesticProductListSearchConditionUnitTest(String arg0)
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

        l_searchConditionUnit =
            new WEB3AdminBondDomesticProductListSearchConditionUnit();

        l_searchConditionUnit.bondType = null;

        try
        {
            l_searchConditionUnit.validate();

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

    /**
     *
     */
    public void testValidate_case0002()
    {

        String STR_METHOD_NAME = " testValidate_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_searchConditionUnit =
            new WEB3AdminBondDomesticProductListSearchConditionUnit();

        l_searchConditionUnit.bondType = "11";

        try
        {
            l_searchConditionUnit.validate();

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

    /**
     *
     */
    public void testValidate_case0003()
    {

        String STR_METHOD_NAME = " testValidate_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_searchConditionUnit =
            new WEB3AdminBondDomesticProductListSearchConditionUnit();

        l_searchConditionUnit.bondType = "12";

        try
        {
            l_searchConditionUnit.validate();

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

    /**
     *
     */
    public void testValidate_case0004()
    {

        String STR_METHOD_NAME = " testValidate_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_searchConditionUnit =
            new WEB3AdminBondDomesticProductListSearchConditionUnit();

        l_searchConditionUnit.bondType = "0";

        try
        {
            l_searchConditionUnit.validate();

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
    public void testValidate_case0005()
    {

        String STR_METHOD_NAME = " testValidate_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_searchConditionUnit =
            new WEB3AdminBondDomesticProductListSearchConditionUnit();

        l_searchConditionUnit.bondType = null;
        l_searchConditionUnit.tradeHandleDiv = null;

        try
        {
            l_searchConditionUnit.validate();

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

    /**
     *
     */
    public void testValidate_case0006()
    {

        String STR_METHOD_NAME = " testValidate_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_searchConditionUnit =
            new WEB3AdminBondDomesticProductListSearchConditionUnit();

        l_searchConditionUnit.bondType = "11";
        l_searchConditionUnit.tradeHandleDiv = "0";

        try
        {
            l_searchConditionUnit.validate();

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

    /**
     *
     */
    public void testValidate_case0007()
    {

        String STR_METHOD_NAME = " testValidate_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_searchConditionUnit =
            new WEB3AdminBondDomesticProductListSearchConditionUnit();

        l_searchConditionUnit.bondType = "12";
        l_searchConditionUnit.tradeHandleDiv = "2";

        try
        {
            l_searchConditionUnit.validate();

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

    /**
     *
     */
    public void testValidate_case0008()
    {

        String STR_METHOD_NAME = " testValidate_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_searchConditionUnit =
            new WEB3AdminBondDomesticProductListSearchConditionUnit();

        l_searchConditionUnit.bondType = "11";
        l_searchConditionUnit.tradeHandleDiv = "1";

        try
        {
            l_searchConditionUnit.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02844, l_exBE.getErrorInfo());

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

}
@
