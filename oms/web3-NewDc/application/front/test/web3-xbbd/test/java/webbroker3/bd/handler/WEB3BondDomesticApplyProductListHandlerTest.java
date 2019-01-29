head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.51.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondDomesticApplyProductListHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 国内債券応募銘柄一覧ハンドラのテストクラス(WEB3BondDomesticApplyProductListHandlerTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/08/03 周墨洋 (中訊) 新規作成 新規作成 仕様変更・モデルNo.224
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.bd.message.WEB3BondDomesticApplyProductListRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyProductListResponse;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyProductListService;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondDomesticApplyProductListServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (国内債券応募銘柄一覧ハンドラ)<BR>
 * 国内債券応募銘柄一覧ハンドラのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3BondDomesticApplyProductListHandlerTest extends TestBaseForMock
{

    /**
     * 国内債券応募銘柄一覧ハンドラ
     */
    WEB3BondDomesticApplyProductListHandler l_handler = null;

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3BondDomesticApplyProductListHandlerTest.class);

    /**
     * @@param arg0
     */
    public WEB3BondDomesticApplyProductListHandlerTest(String arg0)
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
    public void testBondDomesticApplyProductList_case0001()
    {
        final String STR_METHOD_NAME = " testBondDomesticApplyProductList_case0001()";
        log.entering(STR_METHOD_NAME);

        l_handler = new WEB3BondDomesticApplyProductListHandler();

        try
        {
            WEB3BondDomesticApplyProductListRequest l_request =
                new WEB3BondDomesticApplyProductListRequest();
            Services.unregisterService(WEB3BondDomesticApplyProductListService.class);

            WEB3BondDomesticApplyProductListResponse l_response =
                (WEB3BondDomesticApplyProductListResponse)l_handler.bondDomesticApplyProductList(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3BondDomesticApplyProductListService.class,
                new WEB3BondDomesticApplyProductListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testBondDomesticApplyProductList_case0002()
    {
        final String STR_METHOD_NAME = " testBondDomesticApplyProductList_case0002()";
        log.entering(STR_METHOD_NAME);

        l_handler = new WEB3BondDomesticApplyProductListHandler();

        try
        {

            WEB3BondDomesticApplyProductListRequest l_request =
                new WEB3BondDomesticApplyProductListRequest();
            l_request.pageIndex = null;

            WEB3BondDomesticApplyProductListResponse l_response =
                (WEB3BondDomesticApplyProductListResponse)l_handler.bondDomesticApplyProductList(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testBondDomesticApplyProductList_case0003()
    {
        final String STR_METHOD_NAME = " testBondDomesticApplyProductList_case0003()";
        log.entering(STR_METHOD_NAME);

        l_handler = new WEB3BondDomesticApplyProductListHandler();

        try
        {
            Services.overrideService(WEB3BondDomesticApplyProductListService.class, new WEB3BondDomesticApplyProductListServiceImplForMock());
            
            WEB3BondDomesticApplyProductListRequest l_request =
                new WEB3BondDomesticApplyProductListRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = "10";

            WEB3BondDomesticApplyProductListResponse l_response =
                (WEB3BondDomesticApplyProductListResponse)l_handler.bondDomesticApplyProductList(l_request);

            assertEquals(WEB3BondDomesticApplyProductListResponse.class, l_response.getClass());

            Services.overrideService(WEB3BondDomesticApplyProductListService.class, new WEB3BondDomesticApplyProductListServiceImpl());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    private class WEB3BondDomesticApplyProductListHandlerForMock
        extends WEB3BondDomesticApplyProductListHandler
    {
        /**
         *
         *@@param l_request
         * @@return
         */
        public WEB3BondDomesticApplyProductListResponse bondDomesticApplyProductList(
                WEB3BondDomesticApplyProductListRequest l_request)
            {
                final String STR_METHOD_NAME =
                    " bondDomesticApplyProductList(WEB3BondDomesticApplyProductListRequest)";
                log.entering(STR_METHOD_NAME);

                log.exiting(STR_METHOD_NAME);
                return null;
            }
    }
    
    private class WEB3BondDomesticApplyProductListServiceImplForMock extends WEB3BondDomesticApplyProductListServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            return new WEB3BondDomesticApplyProductListResponse();
        }
    }
}
@
