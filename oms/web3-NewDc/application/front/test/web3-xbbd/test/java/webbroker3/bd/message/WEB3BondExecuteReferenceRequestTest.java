head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.20.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondExecuteReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券注文約定照会リクエスト(WEB3BondExecuteReferenceRequest.java)
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
public class WEB3BondExecuteReferenceRequestTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondExecuteReferenceRequestTest.class);

    /**
     * 債券注文約定照会リクエスト
     */
    private WEB3BondExecuteReferenceRequest l_bondExecuteReferenceRequest = null;

    /**
     *
     * @@param arg0
     */
    public WEB3BondExecuteReferenceRequestTest(String arg0)
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
    public void testWEB3BondExecuteReferenceRequest_case0001()
    {
        String STR_METHOD_NAME = " testWEB3BondExecuteReferenceRequest_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_bondExecuteReferenceRequest = new WEB3BondExecuteReferenceRequest();

        assertEquals("1", l_bondExecuteReferenceRequest.productDiv);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testValidate_case0001()
    {

        String STR_METHOD_NAME = " testValidate_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_bondExecuteReferenceRequest = new WEB3BondExecuteReferenceRequest();

        l_bondExecuteReferenceRequest.referenceType = "0";

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[1];
        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productName";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;
        l_bondExecuteReferenceRequest.sortKeys = l_bondSortKeys;

        l_bondExecuteReferenceRequest.pageIndex = "1";
        l_bondExecuteReferenceRequest.pageSize = "8";
        l_bondExecuteReferenceRequest.productDiv = null;

        try
        {
            l_bondExecuteReferenceRequest.validate();

            assertEquals("1", l_bondExecuteReferenceRequest.productDiv);

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
    public void testValidate_case0002()
    {

        String STR_METHOD_NAME = " testValidate_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_bondExecuteReferenceRequest = new WEB3BondExecuteReferenceRequest();

        l_bondExecuteReferenceRequest.referenceType = "0";

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[1];
        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productName";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;
        l_bondExecuteReferenceRequest.sortKeys = l_bondSortKeys;

        l_bondExecuteReferenceRequest.pageIndex = "1";
        l_bondExecuteReferenceRequest.pageSize = "8";
        l_bondExecuteReferenceRequest.productDiv = "1";

        try
        {
            l_bondExecuteReferenceRequest.validate();

            assertEquals("1", l_bondExecuteReferenceRequest.productDiv);

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
    public void testValidate_case0003()
    {

        String STR_METHOD_NAME = " testValidate_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_bondExecuteReferenceRequest = new WEB3BondExecuteReferenceRequest();

        l_bondExecuteReferenceRequest.referenceType = "0";

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[1];
        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productName";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;
        l_bondExecuteReferenceRequest.sortKeys = l_bondSortKeys;

        l_bondExecuteReferenceRequest.pageIndex = "1";
        l_bondExecuteReferenceRequest.pageSize = "8";
        l_bondExecuteReferenceRequest.productDiv = "2";

        try
        {
            l_bondExecuteReferenceRequest.validate();

            assertEquals("2", l_bondExecuteReferenceRequest.productDiv);

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
    public void testValidate_case0004()
    {

        String STR_METHOD_NAME = " testValidate_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_bondExecuteReferenceRequest = new WEB3BondExecuteReferenceRequest();

        l_bondExecuteReferenceRequest.referenceType = "0";

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[1];
        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productName";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;
        l_bondExecuteReferenceRequest.sortKeys = l_bondSortKeys;

        l_bondExecuteReferenceRequest.pageIndex = "1";
        l_bondExecuteReferenceRequest.pageSize = "8";
        l_bondExecuteReferenceRequest.productDiv = "3";

        try
        {
            l_bondExecuteReferenceRequest.validate();

            assertEquals("3", l_bondExecuteReferenceRequest.productDiv);

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
    public void testValidate_case0005()
    {

        String STR_METHOD_NAME = " testValidate_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_bondExecuteReferenceRequest = new WEB3BondExecuteReferenceRequest();

        l_bondExecuteReferenceRequest.referenceType = "0";

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[1];
        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productName";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;
        l_bondExecuteReferenceRequest.sortKeys = l_bondSortKeys;

        l_bondExecuteReferenceRequest.pageIndex = "1";
        l_bondExecuteReferenceRequest.pageSize = "8";
        l_bondExecuteReferenceRequest.productDiv = "4";

        try
        {
            l_bondExecuteReferenceRequest.validate();

            assertEquals("4", l_bondExecuteReferenceRequest.productDiv);

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
    public void testValidate_case0006()
    {

        String STR_METHOD_NAME = " testValidate_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_bondExecuteReferenceRequest = new WEB3BondExecuteReferenceRequest();

        l_bondExecuteReferenceRequest.referenceType = "0";

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[1];
        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productName";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;
        l_bondExecuteReferenceRequest.sortKeys = l_bondSortKeys;

        l_bondExecuteReferenceRequest.pageIndex = "1";
        l_bondExecuteReferenceRequest.pageSize = "8";
        l_bondExecuteReferenceRequest.productDiv = "5";

        try
        {
            l_bondExecuteReferenceRequest.validate();

            assertEquals("5", l_bondExecuteReferenceRequest.productDiv);

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
    public void testValidate_case0007()
    {

        String STR_METHOD_NAME = " testValidate_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_bondExecuteReferenceRequest = new WEB3BondExecuteReferenceRequest();

        l_bondExecuteReferenceRequest.referenceType = "0";

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[1];
        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productName";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;
        l_bondExecuteReferenceRequest.sortKeys = l_bondSortKeys;

        l_bondExecuteReferenceRequest.pageIndex = "1";
        l_bondExecuteReferenceRequest.pageSize = "8";
        l_bondExecuteReferenceRequest.productDiv = "0";

        try
        {
            l_bondExecuteReferenceRequest.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01068, l_exBE.getErrorInfo());

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

        l_bondExecuteReferenceRequest = new WEB3BondExecuteReferenceRequest();

        l_bondExecuteReferenceRequest.referenceType = "0";

        WEB3BondSortKey[] l_bondSortKeys = new WEB3BondSortKey[1];
        WEB3BondSortKey l_bondSortKey = new WEB3BondSortKey();
        l_bondSortKey.keyItem = "productName";
        l_bondSortKey.ascDesc = "A";
        l_bondSortKeys[0] = l_bondSortKey;
        l_bondExecuteReferenceRequest.sortKeys = l_bondSortKeys;

        l_bondExecuteReferenceRequest.pageIndex = "1";
        l_bondExecuteReferenceRequest.pageSize = "8";
        l_bondExecuteReferenceRequest.productDiv = "";

        try
        {
            l_bondExecuteReferenceRequest.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01068, l_exBE.getErrorInfo());

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
