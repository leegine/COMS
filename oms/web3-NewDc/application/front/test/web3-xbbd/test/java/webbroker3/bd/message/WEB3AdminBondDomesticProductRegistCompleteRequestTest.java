head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.20.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondDomesticProductRegistCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  管理者国内債券銘柄登録完了リクエスト(WEB3AdminBondDomesticProductRegistCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/07/13 周墨洋 (中訊) 新規作成
*/
package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 *
 */
public class WEB3AdminBondDomesticProductRegistCompleteRequestTest
    extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminBondDomesticProductRegistCompleteRequestTest.class);

    /**
     * 管理者国内債券銘柄登録完了リクエスト
     */
    WEB3AdminBondDomesticProductRegistCompleteRequest
        l_adminBondDomesticProductRegistCompleteRequest = null;

    /**
     *
     * @@param arg0
     */
    public WEB3AdminBondDomesticProductRegistCompleteRequestTest(String arg0)
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

        l_adminBondDomesticProductRegistCompleteRequest =
            new WEB3AdminBondDomesticProductRegistCompleteRequest();

        l_adminBondDomesticProductRegistCompleteRequest.productId = null;

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.tradeHandleDiv = "0";
        l_bondDomesticProductUpdateInfo.dealingType = "3";
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 = new Date();
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 = new Date();
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet = new Date();
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet = new Date();
        l_bondDomesticProductUpdateInfo.deliveryDate = new Date();
        l_bondDomesticProductUpdateInfo.productNameWEB3 = "";
        l_bondDomesticProductUpdateInfo.applyUnit = "100";
        l_bondDomesticProductUpdateInfo.minFaceAmount = "1";
        l_bondDomesticProductUpdateInfo.maxFaceAmount = "100";
        l_bondDomesticProductUpdateInfo.prospectusCheckDiv = "0";

        l_adminBondDomesticProductRegistCompleteRequest.bondDomesticProductUpdateInfo =
            l_bondDomesticProductUpdateInfo;

        l_adminBondDomesticProductRegistCompleteRequest.password = null;

        try
        {
            l_adminBondDomesticProductRegistCompleteRequest.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_exBE.getErrorInfo());

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

        l_adminBondDomesticProductRegistCompleteRequest =
            new WEB3AdminBondDomesticProductRegistCompleteRequest();

        l_adminBondDomesticProductRegistCompleteRequest.productId = "1";

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.tradeHandleDiv = "0";
        l_bondDomesticProductUpdateInfo.dealingType = "3";
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 = new Date();
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 = new Date();
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet = new Date();
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet = new Date();
        l_bondDomesticProductUpdateInfo.deliveryDate = new Date();
        l_bondDomesticProductUpdateInfo.productNameWEB3 = "";
        l_bondDomesticProductUpdateInfo.applyUnit = "100";
        l_bondDomesticProductUpdateInfo.minFaceAmount = "1";
        l_bondDomesticProductUpdateInfo.maxFaceAmount = "100";
        l_bondDomesticProductUpdateInfo.prospectusCheckDiv = "0";

        l_adminBondDomesticProductRegistCompleteRequest.bondDomesticProductUpdateInfo =
            l_bondDomesticProductUpdateInfo;

        l_adminBondDomesticProductRegistCompleteRequest.password = null;

        try
        {
            l_adminBondDomesticProductRegistCompleteRequest.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01090, l_exBE.getErrorInfo());

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

        l_adminBondDomesticProductRegistCompleteRequest =
            new WEB3AdminBondDomesticProductRegistCompleteRequest();

        l_adminBondDomesticProductRegistCompleteRequest.productId = "1";

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.tradeHandleDiv = "0";
        l_bondDomesticProductUpdateInfo.dealingType = "3";
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 = new Date();
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 = new Date();
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet = new Date();
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet = new Date();
        l_bondDomesticProductUpdateInfo.deliveryDate = new Date();
        l_bondDomesticProductUpdateInfo.productNameWEB3 = "";
        l_bondDomesticProductUpdateInfo.applyUnit = "100";
        l_bondDomesticProductUpdateInfo.minFaceAmount = "1";
        l_bondDomesticProductUpdateInfo.maxFaceAmount = "100";
        l_bondDomesticProductUpdateInfo.prospectusCheckDiv = "0";

        l_adminBondDomesticProductRegistCompleteRequest.bondDomesticProductUpdateInfo =
            l_bondDomesticProductUpdateInfo;

        l_adminBondDomesticProductRegistCompleteRequest.password = "123456";

        try
        {
            l_adminBondDomesticProductRegistCompleteRequest.validate();

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
