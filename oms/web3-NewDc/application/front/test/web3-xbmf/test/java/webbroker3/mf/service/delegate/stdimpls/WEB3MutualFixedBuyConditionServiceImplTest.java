head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.11.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFixedBuyConditionServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MutualFixedBuyConditionServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/16 于瀟 (中訊) 新規作成
 */
package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TraderImpl;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3ChangeDivDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3FinInstitutionDivDef;
import webbroker3.common.define.WEB3MFStatusDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BatoInstBranchPrefParams;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.FinInstitutionBankParams;
import webbroker3.gentrade.data.FinInstitutionBankRow;
import webbroker3.gentrade.data.InsiderParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.define.WEB3GentradeBatoSystemFailureFlagDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.gentrade.message.WEB3GentradeMultiCheckResults;
import webbroker3.gentrade.message.WEB3GentradeMultiDocCheckResultUnit;
import webbroker3.mf.WEB3MutualFixedBuyCommonService;
import webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl;
import webbroker3.mf.data.MfFixedBuyingChangeDao;
import webbroker3.mf.data.MfFixedBuyingChangeHistDao;
import webbroker3.mf.data.MfFixedBuyingChangeHistParams;
import webbroker3.mf.data.MfFixedBuyingChangeHistRow;
import webbroker3.mf.data.MfFixedBuyingChangeParams;
import webbroker3.mf.data.MfFixedBuyingChangeRow;
import webbroker3.mf.data.MfFixedBuyingCondParams;
import webbroker3.mf.data.MfFixedBuyingCondRow;
import webbroker3.mf.data.MfFixedBuyingDrawAccountParams;
import webbroker3.mf.data.MfFixedBuyingDrawAccountRow;
import webbroker3.mf.data.MutualFundProductCategoryParams;
import webbroker3.mf.data.MutualFundProductCategoryRow;
import webbroker3.mf.define.WEB3MFFirstDisplayDivDef;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.mf.message.WEB3MutualFixedBuyCommonUnit;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionUnit;
import webbroker3.mf.message.WEB3MutualFixedBuyTotalUnit;
import webbroker3.mf.message.WEB3MutualOrderReferenceRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFixedBuyConditionServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionServiceImplTest.class);

    public WEB3MutualFixedBuyConditionServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
        TestDBUtility.deleteAll(SubAccountRow.TYPE);
        TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
        TestDBUtility.deleteAll(MutualFundProductCategoryRow.TYPE);
        TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
        TestDBUtility.deleteAll(ProductRow.TYPE);
        TestDBUtility.deleteAll(FinInstitutionBankRow.TYPE);
    }

    /**
     * SYSTEM_ERROR_80017
     *
     * リクエストデータ == null的場合
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        try
        {
            l_impl.execute(null);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * リクエストデータ == 投信定時定額買付銘柄条件登録入力リクエスト的場合
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3SubThisImpl l_impl =
            new WEB3SubThisImpl();

        WEB3MutualFixedBuyConditionInputRequest l_request =
            new WEB3MutualFixedBuyConditionInputRequest();

        try
        {
            WEB3MutualFixedBuyConditionInputResponse l_response =
                (WEB3MutualFixedBuyConditionInputResponse)l_impl.execute(l_request);
            assertEquals(l_response.errorMessage, "KO");
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * リクエストデータ == 投信定時定額買付銘柄条件登録確認リクエスト的場合
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3SubThisImpl l_impl =
            new WEB3SubThisImpl();

        WEB3MutualFixedBuyConditionConfirmRequest l_request =
            new WEB3MutualFixedBuyConditionConfirmRequest();

        try
        {
            WEB3MutualFixedBuyConditionConfirmResponse l_response =
                (WEB3MutualFixedBuyConditionConfirmResponse)l_impl.execute(l_request);
            assertEquals(l_response.errorMessage, "KOOK");
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    /**
     * リクエストデータ == 投信定時定額買付銘柄条件登録完了リクエスト的場合
     */
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3SubThisImpl l_impl =
            new WEB3SubThisImpl();

        WEB3MutualFixedBuyConditionCompleteRequest l_request =
            new WEB3MutualFixedBuyConditionCompleteRequest();

        try
        {
            WEB3MutualFixedBuyConditionCompleteResponse l_response =
                (WEB3MutualFixedBuyConditionCompleteResponse)l_impl.execute(l_request);
            assertEquals(l_response.errorMessage, "KOOKKO");
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    protected class WEB3SubThisImpl extends WEB3MutualFixedBuyConditionServiceImpl
    {
        protected WEB3MutualFixedBuyConditionInputResponse inputMutualFixedBuyCondition(
                WEB3MutualFixedBuyConditionInputRequest l_request) throws WEB3BaseException
        {
            WEB3MutualFixedBuyConditionInputResponse l_response =
                new WEB3MutualFixedBuyConditionInputResponse();
            l_response.errorMessage = "KO";
            return l_response;
        }
        protected WEB3MutualFixedBuyConditionConfirmResponse validateMutualFixedBuyCondition(
                WEB3MutualFixedBuyConditionConfirmRequest l_request) throws WEB3BaseException
        {
            WEB3MutualFixedBuyConditionConfirmResponse l_response =
                new WEB3MutualFixedBuyConditionConfirmResponse();
            l_response.errorMessage = "KOOK";
            return l_response;
        }
        protected WEB3MutualFixedBuyConditionCompleteResponse submitMutualFixedBuyCondition(
                WEB3MutualFixedBuyConditionCompleteRequest l_request) throws WEB3BaseException
        {
            WEB3MutualFixedBuyConditionCompleteResponse l_response =
                new WEB3MutualFixedBuyConditionCompleteResponse();
            l_response.errorMessage = "KOOKKO";
            return l_response;
        }
    }
    /**
     * SYSTEM_ERROR_80018
     *
     * l_request == WEB3MutualOrderReferenceRequest的場合
     */
    public void testExecute_C0005()
    {
        final String STR_METHOD_NAME = "testExecute_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3SubThisImpl l_impl =
            new WEB3SubThisImpl();

        WEB3MutualOrderReferenceRequest l_request =
            new WEB3MutualOrderReferenceRequest();

        try
        {
            l_impl.execute(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * BUSINESS_ERROR_03100
     *
     * リクエスト.validate()出現異常的場合
     */
    public void testInputMutualFixedBuyCondition_C0001()
    {
        final String STR_METHOD_NAME = "testInputMutualFixedBuyCondition_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyConditionInputRequest l_request =
            new WEB3MutualFixedBuyConditionInputRequest();
        l_request.firstDisplayDiv = null;
        try
        {
            l_impl.inputMutualFixedBuyCondition(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03100, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * BUSINESS_ERROR_01984
     *
     * is電子鳩障害中の戻り値 == trueの場合
     */
    public void testInputMutualFixedBuyCondition_C0002()
    {
        final String STR_METHOD_NAME = "testInputMutualFixedBuyCondition_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3Sub_InputThisImpl l_impl =
            new WEB3Sub_InputThisImpl();

        WEB3MutualFixedBuyConditionInputRequest l_request =
            new WEB3MutualFixedBuyConditionInputRequest();
        l_request.firstDisplayDiv = WEB3MFFirstDisplayDivDef.DISPLAY;
        l_request.categoryCode = WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_1;
        l_request.batoCheckFlag = true;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            TestDBUtility.deleteAll(BatoInstBranchPrefParams.TYPE);
            BatoInstBranchPrefParams l_batoInstBranchPrefParams = TestDBUtility.getBatoInstBranchPrefRow();
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_BranchRow = TestDBUtility.getBranchRow();
            l_BranchRow.setBranchId(33381);
            TestDBUtility.insertWithDel(l_BranchRow);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);

            TestDBUtility.deleteAll(MutualFundProductCategoryRow.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            Date l_datBizdate = WEB3DateUtility.getDate("20040710", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "calcValidStartDateOrderBizdate",
                new Class[]{String.class, String.class},
                l_datBizdate);

            Date l_datCurrentdate = WEB3DateUtility.getDate("20040710", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "calcValidStartDateCurrentDate",
                new Class[]{String.class, String.class},
                l_datCurrentdate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "isBatoStopping", new Class[]{},
                new Boolean(true));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnitSorted
                = new WEB3MutualFixedBuyConditionUnit[1];

            l_mutualFixedBuyConditionUnitSorted[0] = new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnitSorted[0].increaseBuyAmount = "3";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "sortFixedBuyConditionList", new Class[]
                {WEB3MutualFixedBuyConditionUnit[].class},
                l_mutualFixedBuyConditionUnitSorted);

            l_impl.inputMutualFixedBuyCondition(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01984, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * <分岐処理> リクエスト．カテゴリコードがnull以外の場合
     *
     * リクエスト．種別コード == nullの場合、get上位投信銘柄カテゴリーコードの戻り値(0左詰め3桁)
     *
     * validate複数銘柄目論見書閲覧の戻り値[index]．チェック結果が0： 閲覧済の場合
     *
     * <分岐処理> リクエスト．種別コード == nullの場合
     *
     * 定時定額買付引落口座
     *
     * 金融機@関区分が郵便局の場合
     *
     * 正常結束
     */
    public void testInputMutualFixedBuyCondition_C0003()
    {
        final String STR_METHOD_NAME = "testInputMutualFixedBuyCondition_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3Sub_InputThisImpl l_impl =
            new WEB3Sub_InputThisImpl();

        WEB3MutualFixedBuyConditionInputRequest l_request =
            new WEB3MutualFixedBuyConditionInputRequest();
        l_request.firstDisplayDiv = WEB3MFFirstDisplayDivDef.DISPLAY;
        l_request.categoryCode = WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_1;
        l_request.batoCheckFlag = true;
        try
        {
            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnitSorted
                = new WEB3MutualFixedBuyConditionUnit[1];

            l_mutualFixedBuyConditionUnitSorted[0] = new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnitSorted[0].increaseBuyAmount = "3";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "sortFixedBuyConditionList", new Class[]
                {WEB3MutualFixedBuyConditionUnit[].class},
                l_mutualFixedBuyConditionUnitSorted);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestDBUtility.deleteAll(BatoInstBranchPrefParams.TYPE);
            BatoInstBranchPrefParams l_batoInstBranchPrefParams = TestDBUtility.getBatoInstBranchPrefRow();
            l_batoInstBranchPrefParams.setSystemFailureFlag(WEB3GentradeBatoSystemFailureFlagDef.WORKING);
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_BranchRow = TestDBUtility.getBranchRow();
            l_BranchRow.setBranchId(33381);
            TestDBUtility.insertWithDel(l_BranchRow);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);

            TestDBUtility.deleteAll(MutualFundProductCategoryRow.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            l_mutualFundProductCategoryParams.setCategoryCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);
            l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            l_mutualFundProductCategoryParams.setCategoryCode("01");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(32768);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            l_mutualFundProductParams.setCategoryCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            Date l_datBuyStartDate = WEB3DateUtility.getDate("20080801150001", "yyyyMMddHHmmss");
            l_mutualFundProductParams.setBuyStartDate(l_datBuyStartDate);
            Date l_datBuyEndDate = WEB3DateUtility.getDate("20080801", "yyyyMMdd");
            l_mutualFundProductParams.setBuyEndDate(l_datBuyEndDate);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            Date l_datBizdate = WEB3DateUtility.getDate("20040710", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "calcValidStartDateOrderBizdate",
                new Class[]{String.class, String.class},
                l_datBizdate);

            Date l_datCurrentdate = WEB3DateUtility.getDate("20050710", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "calcValidStartDateCurrentDate",
                new Class[]{String.class, String.class},
                l_datCurrentdate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "isBatoStopping", new Class[]{},
                new Boolean(false));

            WEB3GentradeMultiCheckResults l_result = new WEB3GentradeMultiCheckResults();
            WEB3GentradeMultiDocCheckResultUnit[] l_checkResult = new WEB3GentradeMultiDocCheckResultUnit[5];
            for (int i = 0; i < 5; i++)
            {
                l_checkResult[i] = new WEB3GentradeMultiDocCheckResultUnit();
                l_checkResult[i].checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
            }
            l_result.checkResult = l_checkResult;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateMultiProspectus",
                new Class[]{WEB3GentradeMultiDocCheckResultUnit[].class, boolean.class},
                l_result);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            WEB3MutualFixedBuyConditionInputResponse l_response =
                l_impl.inputMutualFixedBuyCondition(l_request);

            assertEquals(l_response.conditionList[0].increaseBuyAmount, "3");
            //assertEquals(l_response.addConditionList[0].mutualProductCode, "0001000");
            assertEquals(l_response.acountInfo.financialInstitutionCode, "0D");
            assertEquals(l_response.totalList[0].increaseBATotal, "3");
            assertEquals(l_response.categoryList.length, 0);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * <分岐処理> リクエスト．種別コード == nullの場合
     *
     * リクエスト．種別コード == null以外の場合、リクエスト．種別コード
     *
     * validate複数銘柄目論見書閲覧の戻り値[index]．チェック結果が1： 閲覧未済の場合
     *
     * <分岐処理> 定時定額買付引落口座．get金融機@関区分=銀行の場合
     *
     * 金融機@関区分が銀行の場合
     *
     * 正常結束
     */
    public void testInputMutualFixedBuyCondition_C0004()
    {
        final String STR_METHOD_NAME = "testInputMutualFixedBuyCondition_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3Sub_InputThisImpl l_impl =
            new WEB3Sub_InputThisImpl();

        WEB3MutualFixedBuyConditionInputRequest l_request =
            new WEB3MutualFixedBuyConditionInputRequest();
        l_request.firstDisplayDiv = WEB3MFFirstDisplayDivDef.NO_DISPLAY;
        l_request.batoCheckFlag = true;
        l_request.categoryCode = null;
        l_request.typeCode = WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_1;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_BranchRow = TestDBUtility.getBranchRow();
            l_BranchRow.setBranchId(33381);
            TestDBUtility.insertWithDel(l_BranchRow);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            l_mfFixedBuyingDrawAccountParams.setFinInstitutionDiv(WEB3FinInstitutionDivDef.BANK);
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);

            TestDBUtility.deleteAll(MutualFundProductCategoryRow.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(32768);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            l_mutualFundProductParams.setInstitutionCode("0D");
            Date l_datBuyStartDate = WEB3DateUtility.getDate("20080722150001", "yyyyMMddHHmmss");
            l_mutualFundProductParams.setBuyStartDate(l_datBuyStartDate);
            Date l_datBuyEndDate = WEB3DateUtility.getDate("20080722", "yyyyMMdd");
            l_mutualFundProductParams.setBuyEndDate(l_datBuyEndDate);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(FinInstitutionBankRow.TYPE);
            FinInstitutionBankParams l_finInstitutionBankParams =
                TestDBUtility.getFinInstitutionBankRow();
            l_finInstitutionBankParams.setFinInstitutionCode("0D");
            l_finInstitutionBankParams.setFinBranchCode("381");
            TestDBUtility.insertWithDel(l_finInstitutionBankParams);

            Date l_datBizdate = WEB3DateUtility.getDate("20040710", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "calcValidStartDateOrderBizdate",
                new Class[]{String.class, String.class},
                l_datBizdate);

            Date l_datCurrentdate = WEB3DateUtility.getDate("20040404", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "calcValidStartDateCurrentDate",
                new Class[]{String.class, String.class},
                l_datCurrentdate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "isBatoStopping", new Class[]{},
                new Boolean(false));

            WEB3GentradeMultiCheckResults l_result = new WEB3GentradeMultiCheckResults();
            WEB3GentradeMultiDocCheckResultUnit[] l_checkResult = new WEB3GentradeMultiDocCheckResultUnit[5];
            for (int i = 0; i < 5; i++)
            {
                l_checkResult[i] = new WEB3GentradeMultiDocCheckResultUnit();
                l_checkResult[i].checkResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION;
            }
            l_result.checkResult = l_checkResult;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateMultiProspectus",
                new Class[]{WEB3GentradeMultiDocCheckResultUnit[].class, boolean.class},
                l_result);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProductManager", "getUpMutualFundProductCategoryCode", new Class[]
                    {String.class, String.class},
                    "000");

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnitSorted
                = new WEB3MutualFixedBuyConditionUnit[1];

            l_mutualFixedBuyConditionUnitSorted[0] = new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnitSorted[0].increaseBuyAmount = "3";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "sortFixedBuyConditionList", new Class[]
                {WEB3MutualFixedBuyConditionUnit[].class},
                l_mutualFixedBuyConditionUnitSorted);
        
            WEB3MutualFixedBuyConditionInputResponse l_response =
                l_impl.inputMutualFixedBuyCondition(l_request);

            assertEquals(l_response.conditionList[0].increaseBuyAmount, "3");
            assertNull(l_response.addConditionList);
            assertEquals(l_response.acountInfo.financialInstitutionCode, "0D");
            assertEquals(l_response.totalList[0].increaseBATotal, "3");
            assertEquals(l_response.categoryList[0].categoryName, "012");
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }


    protected class WEB3Sub_InputThisImpl extends WEB3MutualFixedBuyConditionServiceImpl
    {
        protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionChange(
            SubAccount l_subAccount, Date l_datValidStartDate) throws WEB3BaseException
        {
            WEB3MutualFixedBuyConditionUnit[] l_units = new WEB3MutualFixedBuyConditionUnit[5];
            for (int i = 0; i < l_units.length; i++)
            {
                l_units[i] = new WEB3MutualFixedBuyConditionUnit();
            }
            Date l_datStartDate = WEB3DateUtility.getDate("20050505", "yyyyMMdd");
            l_units[0].validStartDate = l_datStartDate;
            return l_units;
        }

        protected WEB3MutualFixedBuyTotalUnit[] getFixedBuyTotalUnit(
            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFixedBuyTotalUnit(WEB3MutualFixedBuyConditionUnit[])";
            log.entering(STR_METHOD_NAME);

            WEB3MutualFixedBuyTotalUnit[] l_mutualFixedBuyTotalUnits =
                new WEB3MutualFixedBuyTotalUnit[1];
            l_mutualFixedBuyTotalUnits[0] = new WEB3MutualFixedBuyTotalUnit();
            l_mutualFixedBuyTotalUnits[0].increaseBATotal = "3";

            log.exiting(STR_METHOD_NAME);
            return l_mutualFixedBuyTotalUnits;
        }
    }

    /**
     * SYSTEM_ERROR_80006
     *
     * validate注文受付可能出現異常的場合
     */
    public void testInputMutualFixedBuyCondition_C0005()
    {
        final String STR_METHOD_NAME = "testInputMutualFixedBuyCondition_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyConditionInputRequest l_request =
            new WEB3MutualFixedBuyConditionInputRequest();
        l_request.firstDisplayDiv = WEB3MFFirstDisplayDivDef.NO_DISPLAY;
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);
            l_impl.inputMutualFixedBuyCondition(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * SYSTEM_ERROR_80006
     *
     * validate取引可能顧客出現異常的場合
     */
    public void testInputMutualFixedBuyCondition_C0006()
    {
        final String STR_METHOD_NAME = "testInputMutualFixedBuyCondition_C0006()";
        log.entering(STR_METHOD_NAME);

        WEB3Sub_InputThisImpl l_impl =
            new WEB3Sub_InputThisImpl();

        WEB3MutualFixedBuyConditionInputRequest l_request =
            new WEB3MutualFixedBuyConditionInputRequest();
        l_request.firstDisplayDiv = WEB3MFFirstDisplayDivDef.NO_DISPLAY;
        l_request.batoCheckFlag = true;
        l_request.categoryCode = null;
        l_request.typeCode = WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_1;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            l_mfFixedBuyingDrawAccountParams.setFinInstitutionDiv(WEB3FinInstitutionDivDef.BANK);
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);

            TestDBUtility.deleteAll(MutualFundProductCategoryRow.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            l_mutualFundProductParams.setInstitutionCode("0D");
            Date l_datBuyStartDate = WEB3DateUtility.getDate("20080721150001", "yyyyMMddHHmmss");
            l_mutualFundProductParams.setBuyStartDate(l_datBuyStartDate);
            Date l_datBuyEndDate = WEB3DateUtility.getDate("20080721", "yyyyMMdd");
            l_mutualFundProductParams.setBuyEndDate(l_datBuyEndDate);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(FinInstitutionBankRow.TYPE);
            FinInstitutionBankParams l_finInstitutionBankParams =
                TestDBUtility.getFinInstitutionBankRow();
            l_finInstitutionBankParams.setFinInstitutionCode("0D");
            l_finInstitutionBankParams.setFinBranchCode("381");
            TestDBUtility.insertWithDel(l_finInstitutionBankParams);

            Date l_datBizdate = WEB3DateUtility.getDate("20040710", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "calcValidStartDateOrderBizdate",
                new Class[]{String.class, String.class},
                l_datBizdate);

            Date l_datCurrentdate = WEB3DateUtility.getDate("20060606", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "calcValidStartDateCurrentDate",
                new Class[]{String.class, String.class},
                l_datCurrentdate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "isBatoStopping", new Class[]{},
                new Boolean(false));
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeMultiCheckResults l_result = new WEB3GentradeMultiCheckResults();
            WEB3GentradeMultiDocCheckResultUnit[] l_checkResult = new WEB3GentradeMultiDocCheckResultUnit[5];
            for (int i = 0; i < 5; i++)
            {
                l_checkResult[i] = new WEB3GentradeMultiDocCheckResultUnit();
                l_checkResult[i].checkResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION;
            }
            l_result.checkResult = l_checkResult;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateMultiProspectus",
                new Class[]{WEB3GentradeMultiDocCheckResultUnit[].class, boolean.class},
                l_result);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProductManager", "getUpMutualFundProductCategoryCode", new Class[]
                    {String.class, String.class},
                    "000");

            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(l_processingResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            l_impl.inputMutualFixedBuyCondition(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * SYSTEM_ERROR_80007
     *
     * validate引落口座登録出現異常的場合
     */
    public void testInputMutualFixedBuyCondition_C0007()
    {
        final String STR_METHOD_NAME = "testInputMutualFixedBuyCondition_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3Sub_InputThisImpl l_impl =
            new WEB3Sub_InputThisImpl();

        WEB3MutualFixedBuyConditionInputRequest l_request =
            new WEB3MutualFixedBuyConditionInputRequest();
        l_request.firstDisplayDiv = WEB3MFFirstDisplayDivDef.NO_DISPLAY;
        l_request.batoCheckFlag = true;
        l_request.categoryCode = null;
        l_request.typeCode = WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_1;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            l_mfFixedBuyingDrawAccountParams.setFinInstitutionDiv(WEB3FinInstitutionDivDef.BANK);
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);

            TestDBUtility.deleteAll(MutualFundProductCategoryRow.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            l_mutualFundProductParams.setInstitutionCode("0D");
            Date l_datBuyStartDate = WEB3DateUtility.getDate("20080721150001", "yyyyMMddHHmmss");
            l_mutualFundProductParams.setBuyStartDate(l_datBuyStartDate);
            Date l_datBuyEndDate = WEB3DateUtility.getDate("20080721", "yyyyMMdd");
            l_mutualFundProductParams.setBuyEndDate(l_datBuyEndDate);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(FinInstitutionBankRow.TYPE);
            FinInstitutionBankParams l_finInstitutionBankParams =
                TestDBUtility.getFinInstitutionBankRow();
            l_finInstitutionBankParams.setFinInstitutionCode("0D");
            l_finInstitutionBankParams.setFinBranchCode("381");
            TestDBUtility.insertWithDel(l_finInstitutionBankParams);

            Date l_datBizdate = WEB3DateUtility.getDate("20040710", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "calcValidStartDateOrderBizdate",
                new Class[]{String.class, String.class},
                l_datBizdate);

            Date l_datCurrentdate = WEB3DateUtility.getDate("20060606", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "calcValidStartDateCurrentDate",
                new Class[]{String.class, String.class},
                l_datCurrentdate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "isBatoStopping", new Class[]{},
                new Boolean(false));

            WEB3GentradeMultiCheckResults l_result = new WEB3GentradeMultiCheckResults();
            WEB3GentradeMultiDocCheckResultUnit[] l_checkResult = new WEB3GentradeMultiDocCheckResultUnit[5];
            for (int i = 0; i < 5; i++)
            {
                l_checkResult[i] = new WEB3GentradeMultiDocCheckResultUnit();
                l_checkResult[i].checkResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION;
            }
            l_result.checkResult = l_checkResult;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateMultiProspectus",
                new Class[]{WEB3GentradeMultiDocCheckResultUnit[].class, boolean.class},
                l_result);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProductManager", "getUpMutualFundProductCategoryCode", new Class[]
                    {String.class, String.class},
                    "000");
            WEB3BaseException l_baseException = new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80007, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", new Class[]
                    {String.class, String.class, String.class},
                    l_baseException);

            l_impl.inputMutualFixedBuyCondition(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80007, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testMergeMutualFixedBuyConditionChange_0001()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionChange_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(333812512203L);
            l_mainAccount.setAccountCode("111111");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_mainAccount);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            Date l_datValidStartDate =
                WEB3DateUtility.getDate("200807", "yyyyMM");

            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setValidStartDate(WEB3DateUtility.getDate("20080801", "yyyyMMdd"));
            l_mfFixedBuyingChangeParams.setStatus("1");
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChange(l_subAccount, l_datValidStartDate);
            assertEquals(1, l_mutualFixedBuyConditionUnits.length);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }


    public void testMergeMutualFixedBuyConditionChange_0002()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionChange_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();
        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(333812512203L);
            l_mainAccount.setAccountCode("111111");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_mainAccount);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            Date l_datValidStartDate =
                WEB3DateUtility.getDate("200807", "yyyyMM");

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChange(l_subAccount, l_datValidStartDate);
            assertEquals(0, l_mutualFixedBuyConditionUnits.length);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateMutualFixedBuyConditionChange_0001()
    {
        final String STR_METHOD_NAME = "testUpdateMutualFixedBuyConditionChange_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");
            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);
            TestDBUtility.deleteAll(MfFixedBuyingChangeHistParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(333812512203L);
            l_mainAccount.setAccountCode("111111");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_mainAccount);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            Institution l_institution =
                new WEB3GentradeInstitution(l_institutionParams);
            Trader l_trader = new WEB3GentradeTrader(l_institution, l_traderParams.getTraderCode(), "381");
            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
                new WEB3MutualFixedBuyCommonUnit();
            l_mutualFixedBuyCommonUnit.batoCheckFlag = false;
            l_mutualFixedBuyCommonUnit.changeDiv = "1";
            l_mutualFixedBuyCommonUnit.increaseBuyAmount = "1";
            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = "1";
            l_mutualFixedBuyCommonUnit.mutualProductCode = "1";
            l_mutualFixedBuyCommonUnit.typeCode = "1";
            l_mutualFixedBuyCommonUnit.validStartDate = GtlUtils.getSystemTimestamp();
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "1";
            l_mutualFixedBuyConditionUnit.checkResult = "1";
            l_mutualFixedBuyConditionUnit.debitAccountYM = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "1";
            l_mutualFixedBuyConditionUnit.mutualProductName = "1";
            l_mutualFixedBuyConditionUnit.suspensionFlag = false;
            l_mutualFixedBuyConditionUnit.updateDate = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.validStartDate = GtlUtils.getSystemTimestamp();
            Date l_datBizDate = GtlUtils.getSystemTimestamp();
            Date l_datValidStartDate = GtlUtils.getSystemTimestamp();
            Timestamp l_tsPrizeClosingDate = GtlUtils.getSystemTimestamp();
            Timestamp l_tsCommonClosingDate = GtlUtils.getSystemTimestamp();
            l_impl.updateMutualFixedBuyConditionChange(
                l_subAccount,
                l_trader,
                l_mutualFixedBuyCommonUnit,
                l_mutualFixedBuyConditionUnit,
                l_datBizDate,
                l_datValidStartDate,
                l_tsPrizeClosingDate,
                l_tsCommonClosingDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateMutualFixedBuyConditionChange_0002()
    {
        final String STR_METHOD_NAME = "testUpdateMutualFixedBuyConditionChange_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");
            TestDBUtility.deleteAll(MfFixedBuyingChangeHistParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(333812512203L);
            l_mainAccount.setAccountCode("111111");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_mainAccount);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            Institution l_institution =
                new WEB3GentradeInstitution(l_institutionParams);
            Trader l_trader = new WEB3GentradeTrader(l_institution, l_traderParams.getTraderCode(), "381");
            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
                new WEB3MutualFixedBuyCommonUnit();
            l_mutualFixedBuyCommonUnit.batoCheckFlag = false;
            l_mutualFixedBuyCommonUnit.changeDiv = "1";
            l_mutualFixedBuyCommonUnit.increaseBuyAmount = null;
            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = null;
            l_mutualFixedBuyCommonUnit.mutualProductCode = "1";
            l_mutualFixedBuyCommonUnit.typeCode = "1";
            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200807", "yyyyMM");
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "1";
            l_mutualFixedBuyConditionUnit.checkResult = "1";
            l_mutualFixedBuyConditionUnit.debitAccountYM = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "1";
            l_mutualFixedBuyConditionUnit.mutualProductName = "1";
            l_mutualFixedBuyConditionUnit.suspensionFlag = false;
            l_mutualFixedBuyConditionUnit.updateDate = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.validStartDate = WEB3DateUtility.getDate("200807", "yyyyMM");
            Date l_datBizDate = GtlUtils.getSystemTimestamp();
            Date l_datValidStartDate = WEB3DateUtility.getDate("200807", "yyyyMM");
            Timestamp l_tsPrizeClosingDate = GtlUtils.getSystemTimestamp();
            Timestamp l_tsCommonClosingDate = GtlUtils.getSystemTimestamp();

            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setValidStartDate(WEB3DateUtility.getDate("200807", "yyyyMM"));
            l_mfFixedBuyingChangeParams.setStatus("1");
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);
            l_impl.updateMutualFixedBuyConditionChange(
                l_subAccount,
                l_trader,
                l_mutualFixedBuyCommonUnit,
                l_mutualFixedBuyConditionUnit,
                l_datBizDate,
                l_datValidStartDate,
                l_tsPrizeClosingDate,
                l_tsCommonClosingDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateMutualFixedBuyConditionChange_0003()
    {
        final String STR_METHOD_NAME = "testUpdateMutualFixedBuyConditionChange_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");
            TestDBUtility.deleteAll(MfFixedBuyingChangeHistParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(333812512203L);
            l_mainAccount.setAccountCode("111111");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_mainAccount);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            Institution l_institution =
                new WEB3GentradeInstitution(l_institutionParams);
            Trader l_trader = new WEB3GentradeTrader(l_institution, l_traderParams.getTraderCode(), "381");
            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
                new WEB3MutualFixedBuyCommonUnit();
            l_mutualFixedBuyCommonUnit.batoCheckFlag = false;
            l_mutualFixedBuyCommonUnit.changeDiv = "2";
            l_mutualFixedBuyCommonUnit.increaseBuyAmount = null;
            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = null;
            l_mutualFixedBuyCommonUnit.mutualProductCode = "1";
            l_mutualFixedBuyCommonUnit.typeCode = "1";
            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200807", "yyyyMM");
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "1";
            l_mutualFixedBuyConditionUnit.checkResult = "1";
            l_mutualFixedBuyConditionUnit.debitAccountYM = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "1";
            l_mutualFixedBuyConditionUnit.mutualProductName = "1";
            l_mutualFixedBuyConditionUnit.suspensionFlag = false;
            l_mutualFixedBuyConditionUnit.updateDate = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.validStartDate = WEB3DateUtility.getDate("200806", "yyyyMM");
            Date l_datBizDate = GtlUtils.getSystemTimestamp();
            Date l_datValidStartDate = WEB3DateUtility.getDate("200807", "yyyyMM");
            Timestamp l_tsPrizeClosingDate = GtlUtils.getSystemTimestamp();
            Timestamp l_tsCommonClosingDate = GtlUtils.getSystemTimestamp();

            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setValidStartDate(WEB3DateUtility.getDate("200807", "yyyyMM"));
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);
            l_impl.updateMutualFixedBuyConditionChange(
                l_subAccount,
                null,
                l_mutualFixedBuyCommonUnit,
                l_mutualFixedBuyConditionUnit,
                l_datBizDate,
                l_datValidStartDate,
                l_tsPrizeClosingDate,
                l_tsCommonClosingDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateMutualFixedBuyConditionChange_0004()
    {
        final String STR_METHOD_NAME = "testUpdateMutualFixedBuyConditionChange_0004()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");
            TestDBUtility.deleteAll(MfFixedBuyingChangeHistParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(333812512203L);
            l_mainAccount.setAccountCode("111111");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_mainAccount);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            Institution l_institution =
                new WEB3GentradeInstitution(l_institutionParams);
            Trader l_trader = new WEB3GentradeTrader(l_institution, l_traderParams.getTraderCode(), "381");
            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
                new WEB3MutualFixedBuyCommonUnit();
            l_mutualFixedBuyCommonUnit.batoCheckFlag = false;
            l_mutualFixedBuyCommonUnit.changeDiv = "3";
            l_mutualFixedBuyCommonUnit.increaseBuyAmount = "1";
            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = "1";
            l_mutualFixedBuyCommonUnit.mutualProductCode = "1";
            l_mutualFixedBuyCommonUnit.typeCode = "1";
            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200806", "yyyyMM");
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "1";
            l_mutualFixedBuyConditionUnit.checkResult = "1";
            l_mutualFixedBuyConditionUnit.debitAccountYM = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "1";
            l_mutualFixedBuyConditionUnit.mutualProductName = "1";
            l_mutualFixedBuyConditionUnit.suspensionFlag = false;
            l_mutualFixedBuyConditionUnit.updateDate = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.validStartDate = WEB3DateUtility.getDate("200806", "yyyyMM");
            Date l_datBizDate = GtlUtils.getSystemTimestamp();
            Date l_datValidStartDate = WEB3DateUtility.getDate("200807", "yyyyMM");
            Timestamp l_tsPrizeClosingDate = GtlUtils.getSystemTimestamp();
            Timestamp l_tsCommonClosingDate = GtlUtils.getSystemTimestamp();

            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setValidStartDate(WEB3DateUtility.getDate("200807", "yyyyMM"));
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);
            l_impl.updateMutualFixedBuyConditionChange(
                l_subAccount,
                null,
                l_mutualFixedBuyCommonUnit,
                l_mutualFixedBuyConditionUnit,
                l_datBizDate,
                l_datValidStartDate,
                l_tsPrizeClosingDate,
                l_tsCommonClosingDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateMutualFixedBuyConditionChange_0005()
    {
        final String STR_METHOD_NAME = "testUpdateMutualFixedBuyConditionChange_0005()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");
            TestDBUtility.deleteAll(MfFixedBuyingChangeHistParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(333812512203L);
            l_mainAccount.setAccountCode("111111");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_mainAccount);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            Institution l_institution =
                new WEB3GentradeInstitution(l_institutionParams);
            Trader l_trader = new WEB3GentradeTrader(l_institution, l_traderParams.getTraderCode(), "381");
            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
                new WEB3MutualFixedBuyCommonUnit();
            l_mutualFixedBuyCommonUnit.batoCheckFlag = false;
            l_mutualFixedBuyCommonUnit.changeDiv = "3";
            l_mutualFixedBuyCommonUnit.increaseBuyAmount = "1";
            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = "1";
            l_mutualFixedBuyCommonUnit.mutualProductCode = "1";
            l_mutualFixedBuyCommonUnit.typeCode = "1";
            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200806", "yyyyMM");
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "1";
            l_mutualFixedBuyConditionUnit.checkResult = "1";
            l_mutualFixedBuyConditionUnit.debitAccountYM = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "1";
            l_mutualFixedBuyConditionUnit.mutualProductName = "1";
            l_mutualFixedBuyConditionUnit.suspensionFlag = false;
            l_mutualFixedBuyConditionUnit.updateDate = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.validStartDate = WEB3DateUtility.getDate("200806", "yyyyMM");
            Date l_datBizDate = GtlUtils.getSystemTimestamp();
            Date l_datValidStartDate = WEB3DateUtility.getDate("200807", "yyyyMM");
            Timestamp l_tsPrizeClosingDate = GtlUtils.getSystemTimestamp();
            Timestamp l_tsCommonClosingDate = GtlUtils.getSystemTimestamp();

            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setValidStartDate(WEB3DateUtility.getDate("200807", "yyyyMM"));
            l_mfFixedBuyingChangeParams.setStatus("4");
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);
            l_impl.updateMutualFixedBuyConditionChange(
                l_subAccount,
                null,
                l_mutualFixedBuyCommonUnit,
                l_mutualFixedBuyConditionUnit,
                l_datBizDate,
                l_datValidStartDate,
                l_tsPrizeClosingDate,
                l_tsCommonClosingDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }


    public void testUpdateMutualFixedBuyConditionChange_0006()
    {
        final String STR_METHOD_NAME = "testUpdateMutualFixedBuyConditionChange_0006()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");
            TestDBUtility.deleteAll(MfFixedBuyingChangeHistParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(333812512203L);
            l_mainAccount.setAccountCode("111111");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_mainAccount);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            Institution l_institution =
                new WEB3GentradeInstitution(l_institutionParams);
            Trader l_trader = new WEB3GentradeTrader(l_institution, l_traderParams.getTraderCode(), "381");
            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
                new WEB3MutualFixedBuyCommonUnit();
            l_mutualFixedBuyCommonUnit.batoCheckFlag = false;
            l_mutualFixedBuyCommonUnit.changeDiv = "2";
            l_mutualFixedBuyCommonUnit.increaseBuyAmount = null;
            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = null;
            l_mutualFixedBuyCommonUnit.mutualProductCode = "1";
            l_mutualFixedBuyCommonUnit.typeCode = "1";
            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200807", "yyyyMM");
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "1";
            l_mutualFixedBuyConditionUnit.checkResult = "1";
            l_mutualFixedBuyConditionUnit.debitAccountYM = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "1";
            l_mutualFixedBuyConditionUnit.mutualProductName = "1";
            l_mutualFixedBuyConditionUnit.suspensionFlag = false;
            l_mutualFixedBuyConditionUnit.updateDate = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.validStartDate = WEB3DateUtility.getDate("200806", "yyyyMM");
            Date l_datBizDate = GtlUtils.getSystemTimestamp();
            Date l_datValidStartDate = WEB3DateUtility.getDate("200807", "yyyyMM");
            Timestamp l_tsPrizeClosingDate = GtlUtils.getSystemTimestamp();
            Timestamp l_tsCommonClosingDate = GtlUtils.getSystemTimestamp();

            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);

            l_impl.updateMutualFixedBuyConditionChange(
                l_subAccount,
                null,
                l_mutualFixedBuyCommonUnit,
                l_mutualFixedBuyConditionUnit,
                l_datBizDate,
                l_datValidStartDate,
                l_tsPrizeClosingDate,
                l_tsCommonClosingDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateMutualFixedBuyConditionChange_0007()
    {
        final String STR_METHOD_NAME = "testUpdateMutualFixedBuyConditionChange_0007()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");
            TestDBUtility.deleteAll(MfFixedBuyingChangeHistParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(333812512203L);
            l_mainAccount.setAccountCode("111111");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_mainAccount);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            Institution l_institution =
                new WEB3GentradeInstitution(l_institutionParams);
            Trader l_trader = new WEB3GentradeTrader(l_institution, l_traderParams.getTraderCode(), "381");
            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
                new WEB3MutualFixedBuyCommonUnit();
            l_mutualFixedBuyCommonUnit.batoCheckFlag = false;
            l_mutualFixedBuyCommonUnit.changeDiv = "3";
            l_mutualFixedBuyCommonUnit.increaseBuyAmount = "1";
            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = "1";
            l_mutualFixedBuyCommonUnit.mutualProductCode = "1";
            l_mutualFixedBuyCommonUnit.typeCode = "1";
            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200806", "yyyyMM");
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "1";
            l_mutualFixedBuyConditionUnit.checkResult = "1";
            l_mutualFixedBuyConditionUnit.debitAccountYM = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "1";
            l_mutualFixedBuyConditionUnit.mutualProductName = "1";
            l_mutualFixedBuyConditionUnit.suspensionFlag = false;
            l_mutualFixedBuyConditionUnit.updateDate = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.validStartDate = WEB3DateUtility.getDate("200806", "yyyyMM");
            Date l_datBizDate = GtlUtils.getSystemTimestamp();
            Date l_datValidStartDate = WEB3DateUtility.getDate("200807", "yyyyMM");
            Timestamp l_tsPrizeClosingDate = GtlUtils.getSystemTimestamp();
            Timestamp l_tsCommonClosingDate = GtlUtils.getSystemTimestamp();

            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);

            l_impl.updateMutualFixedBuyConditionChange(
                l_subAccount,
                null,
                l_mutualFixedBuyCommonUnit,
                l_mutualFixedBuyConditionUnit,
                l_datBizDate,
                l_datValidStartDate,
                l_tsPrizeClosingDate,
                l_tsCommonClosingDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateMutualFixedBuyConditionChange_0008()
    {
        final String STR_METHOD_NAME = "testUpdateMutualFixedBuyConditionChange_0008()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");
            TestDBUtility.deleteAll(MfFixedBuyingChangeHistParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(333812512203L);
            l_mainAccount.setAccountCode("111111");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_mainAccount);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            Institution l_institution =
                new WEB3GentradeInstitution(l_institutionParams);
            Trader l_trader = new WEB3GentradeTrader(l_institution, l_traderParams.getTraderCode(), "381");
            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
                new WEB3MutualFixedBuyCommonUnit();
            l_mutualFixedBuyCommonUnit.batoCheckFlag = false;
            l_mutualFixedBuyCommonUnit.changeDiv = "3";
            l_mutualFixedBuyCommonUnit.increaseBuyAmount = "1";
            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = "1";
            l_mutualFixedBuyCommonUnit.mutualProductCode = "1";
            l_mutualFixedBuyCommonUnit.typeCode = "1";
            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200806", "yyyyMM");
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "1";
            l_mutualFixedBuyConditionUnit.checkResult = "1";
            l_mutualFixedBuyConditionUnit.debitAccountYM = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "1";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "1";
            l_mutualFixedBuyConditionUnit.mutualProductName = "1";
            l_mutualFixedBuyConditionUnit.suspensionFlag = false;
            l_mutualFixedBuyConditionUnit.updateDate = GtlUtils.getSystemTimestamp();
            l_mutualFixedBuyConditionUnit.validStartDate = WEB3DateUtility.getDate("200806", "yyyyMM");
            Date l_datBizDate = GtlUtils.getSystemTimestamp();
            Date l_datValidStartDate = WEB3DateUtility.getDate("200807", "yyyyMM");
            Timestamp l_tsPrizeClosingDate = GtlUtils.getSystemTimestamp();
            Timestamp l_tsCommonClosingDate = GtlUtils.getSystemTimestamp();

            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);

            l_impl.updateMutualFixedBuyConditionChange(
                l_subAccount,
                null,
                l_mutualFixedBuyCommonUnit,
                l_mutualFixedBuyConditionUnit,
                l_datBizDate,
                l_datValidStartDate,
                l_tsPrizeClosingDate,
                l_tsCommonClosingDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

//    public void testInsertMutualFixedBuyConditionChange_C0001()
//    {
//        final String STR_METHOD_NAME = "testInsertMutualFixedBuyConditionChange_C0001()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {           
//            WEB3MutualFixedBuyConditionServiceImpl l_serviceImpl =
//                new WEB3MutualFixedBuyConditionServiceImpl();
//
//            Timestamp l_tsSystemTime =
//                new Timestamp(WEB3DateUtility.getDate("20080707112233", "yyyyMMddHHmmss").getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                "getSystemTimestamp",
//                new Class[] {}, 
//                l_tsSystemTime);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
//                "isPrizeAndMonth",
//                new Class[] {String.class, Date.class}, 
//                Boolean.TRUE);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getSessionProperty",
//                new Class[] {String.class},
//                "1");
//        
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchtParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchtParams);
//            
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeHistRow.TYPE);
//
//            //引数をセット
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);
//            
//            Trader l_trader = null;
//            
//            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
//                new WEB3MutualFixedBuyCommonUnit();
//            l_mutualFixedBuyCommonUnit.mutualProductCode = "300100";
//            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200802", "yyyyMM");
//            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = null;
//            l_mutualFixedBuyCommonUnit.increaseBuyAmount = null;
//            l_mutualFixedBuyCommonUnit.changeDiv = "2";
//            
//            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
//                new WEB3MutualFixedBuyConditionUnit();
//            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "60";
//            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "80";
//            
//            Date l_datBizDate = WEB3DateUtility.getDate("20080401", "yyyyMMdd");
//            
//            Date l_datValidStartDate = WEB3DateUtility.getDate("200803", "yyyyMM");
//            
//            Timestamp l_tsPrizeClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080512142800", "yyyyMMddHHmmss").getTime());
//            
//            Timestamp l_tsCommonClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080808200000", "yyyyMMddHHmmss").getTime());
//            
//            l_serviceImpl.insertMutualFixedBuyConditionChange(
//                l_subAccount,
//                l_trader,
//                l_mutualFixedBuyCommonUnit,
//                l_mutualFixedBuyConditionUnit,
//                l_datBizDate,
//                l_datValidStartDate,
//                l_tsPrizeClosingDate,
//                l_tsCommonClosingDate);
//            
//            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
//                MfFixedBuyingChangeDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200803", "yyyyMM").getTime()));
//            
//            MfFixedBuyingChangeHistRow l_mfFixedBuyingChangeHistRow =
//                MfFixedBuyingChangeHistDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200803", "yyyyMM").getTime()),
//                    1);
//            
//            //定時定額買付条件変更
//            assertEquals("0D", l_mfFixedBuyingChangeRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeRow.getProductCode());
//            assertEquals("200803", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(60, l_mfFixedBuyingChangeRow.getMonthlyBuyAmount(), 0);
//            assertEquals(80, l_mfFixedBuyingChangeRow.getIncreaseBuyAmount(), 0);
//            assertEquals("2", l_mfFixedBuyingChangeRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeRow.getOrderChanel());
//            assertEquals(80, l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount(), 0);
//            assertEquals("0", l_mfFixedBuyingChangeRow.getDeleteFlag().intValue() + "");
//            assertEquals("2512246", l_mfFixedBuyingChangeRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            
//            //定時定額買付条件変更履歴
//            assertEquals("0D", l_mfFixedBuyingChangeHistRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeHistRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeHistRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeHistRow.getProductCode());
//            assertEquals("200803", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(60, l_mfFixedBuyingChangeHistRow.getMonthlyBuyAmount(), 0);
//            assertEquals(80, l_mfFixedBuyingChangeHistRow.getIncreaseBuyAmount(), 0);
//            assertEquals("2", l_mfFixedBuyingChangeHistRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getOrderChanel());
//            assertEquals(80, l_mfFixedBuyingChangeHistRow.getFinDrawIncreaseBuyAmount(), 0);
//            assertEquals("0", l_mfFixedBuyingChangeHistRow.getDeleteFlag().intValue() + "");
//            assertEquals("2512246", l_mfFixedBuyingChangeHistRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals(1L, l_mfFixedBuyingChangeHistRow.getSerialNo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    public void testInsertMutualFixedBuyConditionChange_C0002()
//    {
//        final String STR_METHOD_NAME = "testInsertMutualFixedBuyConditionChange_C0002()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {           
//            WEB3MutualFixedBuyConditionServiceImpl l_serviceImpl =
//                new WEB3MutualFixedBuyConditionServiceImpl();
//
//            Timestamp l_tsSystemTime =
//                new Timestamp(WEB3DateUtility.getDate("20080707112233", "yyyyMMddHHmmss").getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                "getSystemTimestamp",
//                new Class[] {}, 
//                l_tsSystemTime);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
//                "isPrizeAndMonth",
//                new Class[] {String.class, Date.class}, 
//                Boolean.TRUE);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getSessionProperty",
//                new Class[] {String.class},
//                "1");
//        
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchtParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchtParams);
//            
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeHistRow.TYPE);
//
//            //引数をセット
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);            
//            Trader l_trader = new TraderImpl(3338111123L, true);
//            
//            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
//                new WEB3MutualFixedBuyCommonUnit();
//            l_mutualFixedBuyCommonUnit.mutualProductCode = "300100";
//            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200802", "yyyyMM");
//            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = "300";
//            l_mutualFixedBuyCommonUnit.increaseBuyAmount = "500";
//            l_mutualFixedBuyCommonUnit.changeDiv = "2";
//            
//            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
//                new WEB3MutualFixedBuyConditionUnit();
//            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "60";
//            l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
//            
//            Date l_datBizDate = WEB3DateUtility.getDate("20080401", "yyyyMMdd");
//            
//            Date l_datValidStartDate = WEB3DateUtility.getDate("200802", "yyyyMM");
//            
//            Timestamp l_tsPrizeClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080512142800", "yyyyMMddHHmmss").getTime());
//            
//            Timestamp l_tsCommonClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080707112233", "yyyyMMddHHmmss").getTime());
//            
//            l_serviceImpl.insertMutualFixedBuyConditionChange(
//                l_subAccount,
//                l_trader,
//                l_mutualFixedBuyCommonUnit,
//                l_mutualFixedBuyConditionUnit,
//                l_datBizDate,
//                l_datValidStartDate,
//                l_tsPrizeClosingDate,
//                l_tsCommonClosingDate);
//            
//            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
//                MfFixedBuyingChangeDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()));
//            
//            MfFixedBuyingChangeHistRow l_mfFixedBuyingChangeHistRow =
//                MfFixedBuyingChangeHistDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()),
//                    1);
//            
//            //定時定額買付条件変更
//            assertEquals("0D", l_mfFixedBuyingChangeRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(300, l_mfFixedBuyingChangeRow.getMonthlyBuyAmount(), 0);
//            assertEquals(500, l_mfFixedBuyingChangeRow.getIncreaseBuyAmount(), 0);
//            assertEquals("2", l_mfFixedBuyingChangeRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeRow.getOrderChanel());
//            assertEquals(true, l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull());
//            assertEquals("0", l_mfFixedBuyingChangeRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            
//            //定時定額買付条件変更履歴
//            assertEquals("0D", l_mfFixedBuyingChangeHistRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeHistRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeHistRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeHistRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(300, l_mfFixedBuyingChangeHistRow.getMonthlyBuyAmount(), 0);
//            assertEquals(500, l_mfFixedBuyingChangeHistRow.getIncreaseBuyAmount(), 0);
//            assertEquals("2", l_mfFixedBuyingChangeHistRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getOrderChanel());
//            assertEquals(true, l_mfFixedBuyingChangeHistRow.getFinDrawIncreaseBuyAmountIsNull());
//            assertEquals("0", l_mfFixedBuyingChangeHistRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeHistRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals(1L, l_mfFixedBuyingChangeHistRow.getSerialNo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testInsertMutualFixedBuyConditionChange_C0003()
//    {
//        final String STR_METHOD_NAME = "testInsertMutualFixedBuyConditionChange_C0003()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {           
//            WEB3MutualFixedBuyConditionServiceImpl l_serviceImpl =
//                new WEB3MutualFixedBuyConditionServiceImpl();
//
//            Timestamp l_tsSystemTime =
//                new Timestamp(WEB3DateUtility.getDate("20080707112233", "yyyyMMddHHmmss").getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                "getSystemTimestamp",
//                new Class[] {}, 
//                l_tsSystemTime);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
//                "isPrizeAndMonth",
//                new Class[] {String.class, Date.class}, 
//                Boolean.TRUE);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getSessionProperty",
//                new Class[] {String.class},
//                "1");
//        
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchtParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchtParams);
//            
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeHistRow.TYPE);
//
//            //引数をセット
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);            
//            Trader l_trader = new TraderImpl(3338111123L, true);
//            
//            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
//                new WEB3MutualFixedBuyCommonUnit();
//            l_mutualFixedBuyCommonUnit.mutualProductCode = "300100";
//            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200802", "yyyyMM");
//            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = null;
//            l_mutualFixedBuyCommonUnit.increaseBuyAmount = null;
//            l_mutualFixedBuyCommonUnit.changeDiv = "1";
//            
//            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
//                new WEB3MutualFixedBuyConditionUnit();
//            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "60";
//            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "80";
//            
//            Date l_datBizDate = WEB3DateUtility.getDate("20080401", "yyyyMMdd");
//            
//            Date l_datValidStartDate = WEB3DateUtility.getDate("200801", "yyyyMM");
//            
//            Timestamp l_tsPrizeClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080512142800", "yyyyMMddHHmmss").getTime());
//            
//            Timestamp l_tsCommonClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080808200000", "yyyyMMddHHmmss").getTime());
//            
//            l_serviceImpl.insertMutualFixedBuyConditionChange(
//                l_subAccount,
//                l_trader,
//                l_mutualFixedBuyCommonUnit,
//                l_mutualFixedBuyConditionUnit,
//                l_datBizDate,
//                l_datValidStartDate,
//                l_tsPrizeClosingDate,
//                l_tsCommonClosingDate);
//            
//            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
//                MfFixedBuyingChangeDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()));
//            
//            MfFixedBuyingChangeHistRow l_mfFixedBuyingChangeHistRow =
//                MfFixedBuyingChangeHistDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()),
//                    1);
//            
//            //定時定額買付条件変更
//            assertEquals("0D", l_mfFixedBuyingChangeRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(true, l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull());
//            assertEquals(0, l_mfFixedBuyingChangeRow.getIncreaseBuyAmount(), 0);
//            assertEquals("1", l_mfFixedBuyingChangeRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeRow.getOrderChanel());
//            assertEquals(0, l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount(), 0);
//            assertEquals("0", l_mfFixedBuyingChangeRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            
//            //定時定額買付条件変更履歴
//            assertEquals("0D", l_mfFixedBuyingChangeHistRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeHistRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeHistRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeHistRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(true, l_mfFixedBuyingChangeHistRow.getMonthlyBuyAmountIsNull());
//            assertEquals(0, l_mfFixedBuyingChangeHistRow.getIncreaseBuyAmount(), 0);
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getOrderChanel());
//            assertEquals(0, l_mfFixedBuyingChangeHistRow.getFinDrawIncreaseBuyAmount(), 0);
//            assertEquals("0", l_mfFixedBuyingChangeHistRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeHistRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals(1L, l_mfFixedBuyingChangeHistRow.getSerialNo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testInsertMutualFixedBuyConditionChange_C0004()
//    {
//        final String STR_METHOD_NAME = "testInsertMutualFixedBuyConditionChange_C0004()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {           
//            WEB3MutualFixedBuyConditionServiceImpl l_serviceImpl =
//                new WEB3MutualFixedBuyConditionServiceImpl();
//
//            Timestamp l_tsSystemTime =
//                new Timestamp(WEB3DateUtility.getDate("20080707112233", "yyyyMMddHHmmss").getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                "getSystemTimestamp",
//                new Class[] {}, 
//                l_tsSystemTime);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
//                "isPrizeAndMonth",
//                new Class[] {String.class, Date.class}, 
//                Boolean.TRUE);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getSessionProperty",
//                new Class[] {String.class},
//                "1");
//        
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchtParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchtParams);
//            
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeHistRow.TYPE);
//
//            //引数をセット
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);            
//            Trader l_trader = new TraderImpl(3338111123L, true);
//            
//            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
//                new WEB3MutualFixedBuyCommonUnit();
//            l_mutualFixedBuyCommonUnit.mutualProductCode = "300100";
//            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200802", "yyyyMM");
//            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = null;
//            l_mutualFixedBuyCommonUnit.increaseBuyAmount = null;
//            l_mutualFixedBuyCommonUnit.changeDiv = "3";
//            
//            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
//                new WEB3MutualFixedBuyConditionUnit();
//            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "60";
//            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "80";
//            
//            Date l_datBizDate = WEB3DateUtility.getDate("20080401", "yyyyMMdd");
//            
//            Date l_datValidStartDate = WEB3DateUtility.getDate("200801", "yyyyMM");
//            
//            Timestamp l_tsPrizeClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080512142800", "yyyyMMddHHmmss").getTime());
//            
//            Timestamp l_tsCommonClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080808200000", "yyyyMMddHHmmss").getTime());
//            
//            l_serviceImpl.insertMutualFixedBuyConditionChange(
//                l_subAccount,
//                l_trader,
//                l_mutualFixedBuyCommonUnit,
//                l_mutualFixedBuyConditionUnit,
//                l_datBizDate,
//                l_datValidStartDate,
//                l_tsPrizeClosingDate,
//                l_tsCommonClosingDate);
//            
//            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
//                MfFixedBuyingChangeDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()));
//            
//            MfFixedBuyingChangeHistRow l_mfFixedBuyingChangeHistRow =
//                MfFixedBuyingChangeHistDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()),
//                    1);
//            
//            //定時定額買付条件変更
//            assertEquals("0D", l_mfFixedBuyingChangeRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(true, l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull());
//            assertEquals(true, l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull());
//            assertEquals("3", l_mfFixedBuyingChangeRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeRow.getOrderChanel());
//            assertEquals(80, l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount(), 0);
//            assertEquals("0", l_mfFixedBuyingChangeRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            
//            //定時定額買付条件変更履歴
//            assertEquals("0D", l_mfFixedBuyingChangeHistRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeHistRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeHistRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeHistRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(true, l_mfFixedBuyingChangeHistRow.getMonthlyBuyAmountIsNull());
//            assertEquals(true, l_mfFixedBuyingChangeHistRow.getIncreaseBuyAmountIsNull());
//            assertEquals("3", l_mfFixedBuyingChangeHistRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getOrderChanel());
//            assertEquals(80, l_mfFixedBuyingChangeHistRow.getFinDrawIncreaseBuyAmount(), 0);
//            assertEquals("0", l_mfFixedBuyingChangeHistRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeHistRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals(1L, l_mfFixedBuyingChangeHistRow.getSerialNo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testInsertMutualFixedBuyConditionChange_C0005()
//    {
//        final String STR_METHOD_NAME = "testInsertMutualFixedBuyConditionChange_C0005()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {           
//            WEB3MutualFixedBuyConditionServiceImpl l_serviceImpl =
//                new WEB3MutualFixedBuyConditionServiceImpl();
//
//            Timestamp l_tsSystemTime =
//                new Timestamp(WEB3DateUtility.getDate("20080707112233", "yyyyMMddHHmmss").getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                "getSystemTimestamp",
//                new Class[] {}, 
//                l_tsSystemTime);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
//                "isPrizeAndMonth",
//                new Class[] {String.class, Date.class}, 
//                Boolean.TRUE);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getSessionProperty",
//                new Class[] {String.class},
//                "1");
//        
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchtParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchtParams);
//            
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeHistRow.TYPE);
//
//            //引数をセット
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);            
//            Trader l_trader = new TraderImpl(3338111123L, true);
//            
//            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
//                new WEB3MutualFixedBuyCommonUnit();
//            l_mutualFixedBuyCommonUnit.mutualProductCode = "300100";
//            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200802", "yyyyMM");
//            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = null;
//            l_mutualFixedBuyCommonUnit.increaseBuyAmount = null;
//            l_mutualFixedBuyCommonUnit.changeDiv = "4";
//            
//            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
//                new WEB3MutualFixedBuyConditionUnit();
//            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "60";
//            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "80";
//            
//            Date l_datBizDate = WEB3DateUtility.getDate("20080401", "yyyyMMdd");
//            
//            Date l_datValidStartDate = WEB3DateUtility.getDate("200801", "yyyyMM");
//            
//            Timestamp l_tsPrizeClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080512142800", "yyyyMMddHHmmss").getTime());
//            
//            Timestamp l_tsCommonClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080808200000", "yyyyMMddHHmmss").getTime());
//            
//            l_serviceImpl.insertMutualFixedBuyConditionChange(
//                l_subAccount,
//                l_trader,
//                l_mutualFixedBuyCommonUnit,
//                l_mutualFixedBuyConditionUnit,
//                l_datBizDate,
//                l_datValidStartDate,
//                l_tsPrizeClosingDate,
//                l_tsCommonClosingDate);
//            
//            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
//                MfFixedBuyingChangeDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()));
//            
//            MfFixedBuyingChangeHistRow l_mfFixedBuyingChangeHistRow =
//                MfFixedBuyingChangeHistDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()),
//                    1);
//            
//            //定時定額買付条件変更
//            assertEquals("0D", l_mfFixedBuyingChangeRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(true, l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull());
//            assertEquals(true, l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull());
//            assertEquals("4", l_mfFixedBuyingChangeRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeRow.getOrderChanel());
//            assertEquals(80, l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount(), 0);
//            assertEquals("0", l_mfFixedBuyingChangeRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            
//            //定時定額買付条件変更履歴
//            assertEquals("0D", l_mfFixedBuyingChangeHistRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeHistRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeHistRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeHistRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(true, l_mfFixedBuyingChangeHistRow.getMonthlyBuyAmountIsNull());
//            assertEquals(true, l_mfFixedBuyingChangeHistRow.getIncreaseBuyAmountIsNull());
//            assertEquals("4", l_mfFixedBuyingChangeHistRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getOrderChanel());
//            assertEquals(80, l_mfFixedBuyingChangeHistRow.getFinDrawIncreaseBuyAmount(), 0);
//            assertEquals("0", l_mfFixedBuyingChangeHistRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeHistRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals(1L, l_mfFixedBuyingChangeHistRow.getSerialNo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testInsertMutualFixedBuyConditionChange_C0006()
//    {
//        final String STR_METHOD_NAME = "testInsertMutualFixedBuyConditionChange_C0006()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {           
//            WEB3MutualFixedBuyConditionServiceImpl l_serviceImpl =
//                new WEB3MutualFixedBuyConditionServiceImpl();
//
//            Timestamp l_tsSystemTime =
//                new Timestamp(WEB3DateUtility.getDate("20080707112233", "yyyyMMddHHmmss").getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                "getSystemTimestamp",
//                new Class[] {}, 
//                l_tsSystemTime);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
//                "isPrizeAndMonth",
//                new Class[] {String.class, Date.class}, 
//                Boolean.TRUE);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getSessionProperty",
//                new Class[] {String.class},
//                "1");
//        
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchtParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchtParams);
//            
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeHistRow.TYPE);
//
//            //引数をセット
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);            
//            Trader l_trader = new TraderImpl(3338111123L, true);
//            
//            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
//                new WEB3MutualFixedBuyCommonUnit();
//            l_mutualFixedBuyCommonUnit.mutualProductCode = "300100";
//            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200802", "yyyyMM");
//            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = null;
//            l_mutualFixedBuyCommonUnit.increaseBuyAmount = null;
//            l_mutualFixedBuyCommonUnit.changeDiv = "2";
//            
//            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
//                new WEB3MutualFixedBuyConditionUnit();
//            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
//            l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
//            
//            Date l_datBizDate = WEB3DateUtility.getDate("20080401", "yyyyMMdd");
//            
//            Date l_datValidStartDate = WEB3DateUtility.getDate("200801", "yyyyMM");
//            
//            Timestamp l_tsPrizeClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080512142800", "yyyyMMddHHmmss").getTime());
//            
//            Timestamp l_tsCommonClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080808200000", "yyyyMMddHHmmss").getTime());
//            
//            l_serviceImpl.insertMutualFixedBuyConditionChange(
//                l_subAccount,
//                l_trader,
//                l_mutualFixedBuyCommonUnit,
//                l_mutualFixedBuyConditionUnit,
//                l_datBizDate,
//                l_datValidStartDate,
//                l_tsPrizeClosingDate,
//                l_tsCommonClosingDate);
//            
//            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
//                MfFixedBuyingChangeDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()));
//            
//            MfFixedBuyingChangeHistRow l_mfFixedBuyingChangeHistRow =
//                MfFixedBuyingChangeHistDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()),
//                    1);
//            
//            //定時定額買付条件変更
//            assertEquals("0D", l_mfFixedBuyingChangeRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(true, l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull());
//            assertEquals(true, l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull());
//            assertEquals("2", l_mfFixedBuyingChangeRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeRow.getOrderChanel());
//            assertEquals(true, l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull());
//            assertEquals("0", l_mfFixedBuyingChangeRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            
//            //定時定額買付条件変更履歴
//            assertEquals("0D", l_mfFixedBuyingChangeHistRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeHistRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeHistRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeHistRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(true, l_mfFixedBuyingChangeHistRow.getMonthlyBuyAmountIsNull());
//            assertEquals(true, l_mfFixedBuyingChangeHistRow.getIncreaseBuyAmountIsNull());
//            assertEquals("2", l_mfFixedBuyingChangeHistRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getOrderChanel());
//            assertEquals(true, l_mfFixedBuyingChangeHistRow.getFinDrawIncreaseBuyAmountIsNull());
//            assertEquals("0", l_mfFixedBuyingChangeHistRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeHistRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals(1L, l_mfFixedBuyingChangeHistRow.getSerialNo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testInsertMutualFixedBuyConditionChange_C0007()
//    {
//        final String STR_METHOD_NAME = "testInsertMutualFixedBuyConditionChange_C0007()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {           
//            WEB3MutualFixedBuyConditionServiceImpl l_serviceImpl =
//                new WEB3MutualFixedBuyConditionServiceImpl();
//
//            Timestamp l_tsSystemTime =
//                new Timestamp(WEB3DateUtility.getDate("20080707112233", "yyyyMMddHHmmss").getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                "getSystemTimestamp",
//                new Class[] {}, 
//                l_tsSystemTime);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
//                "isPrizeAndMonth",
//                new Class[] {String.class, Date.class}, 
//                Boolean.TRUE);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getSessionProperty",
//                new Class[] {String.class},
//                "1");
//        
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchtParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchtParams);
//            
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeHistRow.TYPE);
//
//            //引数をセット
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);            
//            Trader l_trader = new TraderImpl(3338111123L, true);
//            
//            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
//                new WEB3MutualFixedBuyCommonUnit();
//            l_mutualFixedBuyCommonUnit.mutualProductCode = "300100";
//            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200802", "yyyyMM");
//            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = "300";
//            l_mutualFixedBuyCommonUnit.increaseBuyAmount = "500";
//            l_mutualFixedBuyCommonUnit.changeDiv = "2";
//            
//            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
//                new WEB3MutualFixedBuyConditionUnit();
//            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "60";
//            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "80";
//            
//            Date l_datBizDate = WEB3DateUtility.getDate("20080401", "yyyyMMdd");
//            
//            Date l_datValidStartDate = WEB3DateUtility.getDate("200801", "yyyyMM");
//            
//            Timestamp l_tsPrizeClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080512142800", "yyyyMMddHHmmss").getTime());
//            
//            Timestamp l_tsCommonClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080606112233", "yyyyMMddHHmmss").getTime());
//            
//            l_serviceImpl.insertMutualFixedBuyConditionChange(
//                l_subAccount,
//                l_trader,
//                l_mutualFixedBuyCommonUnit,
//                l_mutualFixedBuyConditionUnit,
//                l_datBizDate,
//                l_datValidStartDate,
//                l_tsPrizeClosingDate,
//                l_tsCommonClosingDate);
//            
//            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
//                MfFixedBuyingChangeDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()));
//            
//            MfFixedBuyingChangeHistRow l_mfFixedBuyingChangeHistRow =
//                MfFixedBuyingChangeHistDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()),
//                    1);
//            
//            //定時定額買付条件変更
//            assertEquals("0D", l_mfFixedBuyingChangeRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(300, l_mfFixedBuyingChangeRow.getMonthlyBuyAmount(), 0);
//            assertEquals(500, l_mfFixedBuyingChangeRow.getIncreaseBuyAmount(), 0);
//            assertEquals("2", l_mfFixedBuyingChangeRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeRow.getOrderChanel());
//            assertEquals(true, l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull());
//            assertEquals("0", l_mfFixedBuyingChangeRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            
//            //定時定額買付条件変更履歴
//            assertEquals("0D", l_mfFixedBuyingChangeHistRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeHistRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeHistRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeHistRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(300, l_mfFixedBuyingChangeHistRow.getMonthlyBuyAmount(), 0);
//            assertEquals(500, l_mfFixedBuyingChangeHistRow.getIncreaseBuyAmount(), 0);
//            assertEquals("2", l_mfFixedBuyingChangeHistRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getOrderChanel());
//            assertEquals(true, l_mfFixedBuyingChangeHistRow.getFinDrawIncreaseBuyAmountIsNull());
//            assertEquals("0", l_mfFixedBuyingChangeHistRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeHistRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals(1L, l_mfFixedBuyingChangeHistRow.getSerialNo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testInsertMutualFixedBuyConditionChange_C0008()
//    {
//        final String STR_METHOD_NAME = "testInsertMutualFixedBuyConditionChange_C0008()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {           
//            WEB3MutualFixedBuyConditionServiceImpl l_serviceImpl =
//                new WEB3MutualFixedBuyConditionServiceImpl();
//
//            Timestamp l_tsSystemTime =
//                new Timestamp(WEB3DateUtility.getDate("20080707112233", "yyyyMMddHHmmss").getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                "getSystemTimestamp",
//                new Class[] {}, 
//                l_tsSystemTime);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
//                "isPrizeAndMonth",
//                new Class[] {String.class, Date.class}, 
//                Boolean.TRUE);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getSessionProperty",
//                new Class[] {String.class},
//                "1");
//        
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchtParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchtParams);
//            
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeHistRow.TYPE);
//
//            //引数をセット
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);            
//            Trader l_trader = new TraderImpl(3338111123L, true);
//            
//            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
//                new WEB3MutualFixedBuyCommonUnit();
//            l_mutualFixedBuyCommonUnit.mutualProductCode = "300100";
//            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200802", "yyyyMM");
//            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = "300";
//            l_mutualFixedBuyCommonUnit.increaseBuyAmount = "500";
//            l_mutualFixedBuyCommonUnit.changeDiv = "2";
//            
//            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
//                new WEB3MutualFixedBuyConditionUnit();
//            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "60";
//            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "80";
//            
//            Date l_datBizDate = WEB3DateUtility.getDate("20080401", "yyyyMMdd");
//            
//            Date l_datValidStartDate = WEB3DateUtility.getDate("200801", "yyyyMM");
//            
//            Timestamp l_tsPrizeClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080801000000", "yyyyMMddHHmmss").getTime());
//            
//            Timestamp l_tsCommonClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080808200000", "yyyyMMddHHmmss").getTime());
//            
//            l_serviceImpl.insertMutualFixedBuyConditionChange(
//                l_subAccount,
//                l_trader,
//                l_mutualFixedBuyCommonUnit,
//                l_mutualFixedBuyConditionUnit,
//                l_datBizDate,
//                l_datValidStartDate,
//                l_tsPrizeClosingDate,
//                l_tsCommonClosingDate);
//            
//            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
//                MfFixedBuyingChangeDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()));
//            
//            MfFixedBuyingChangeHistRow l_mfFixedBuyingChangeHistRow =
//                MfFixedBuyingChangeHistDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()),
//                    1);
//            
//            //定時定額買付条件変更
//            assertEquals("0D", l_mfFixedBuyingChangeRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(300, l_mfFixedBuyingChangeRow.getMonthlyBuyAmount(), 0);
//            assertEquals(500, l_mfFixedBuyingChangeRow.getIncreaseBuyAmount(), 0);
//            assertEquals("2", l_mfFixedBuyingChangeRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeRow.getOrderChanel());
//            assertEquals(true, l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull());
//            assertEquals("0", l_mfFixedBuyingChangeRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            
//            //定時定額買付条件変更履歴
//            assertEquals("0D", l_mfFixedBuyingChangeHistRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeHistRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeHistRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeHistRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(300, l_mfFixedBuyingChangeHistRow.getMonthlyBuyAmount(), 0);
//            assertEquals(500, l_mfFixedBuyingChangeHistRow.getIncreaseBuyAmount(), 0);
//            assertEquals("2", l_mfFixedBuyingChangeHistRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getOrderChanel());
//            assertEquals(true, l_mfFixedBuyingChangeHistRow.getFinDrawIncreaseBuyAmountIsNull());
//            assertEquals("0", l_mfFixedBuyingChangeHistRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeHistRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals(1L, l_mfFixedBuyingChangeHistRow.getSerialNo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testInsertMutualFixedBuyConditionChange_C0009()
//    {
//        final String STR_METHOD_NAME = "testInsertMutualFixedBuyConditionChange_C0009()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {           
//            WEB3MutualFixedBuyConditionServiceImpl l_serviceImpl =
//                new WEB3MutualFixedBuyConditionServiceImpl();
//
//            Timestamp l_tsSystemTime =
//                new Timestamp(WEB3DateUtility.getDate("20080707112233", "yyyyMMddHHmmss").getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                "getSystemTimestamp",
//                new Class[] {}, 
//                l_tsSystemTime);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
//                "isPrizeAndMonth",
//                new Class[] {String.class, Date.class}, 
//                Boolean.TRUE);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getSessionProperty",
//                new Class[] {String.class},
//                "1");
//        
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchtParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchtParams);
//            
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeHistRow.TYPE);
//
//            //引数をセット
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);            
//            Trader l_trader = new TraderImpl(3338111123L, true);
//            
//            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
//                new WEB3MutualFixedBuyCommonUnit();
//            l_mutualFixedBuyCommonUnit.mutualProductCode = "300100";
//            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200802", "yyyyMM");
//            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = "300";
//            l_mutualFixedBuyCommonUnit.increaseBuyAmount = "500";
//            l_mutualFixedBuyCommonUnit.changeDiv = "2";
//            
//            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
//                new WEB3MutualFixedBuyConditionUnit();
//            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "60";
//            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "80";
//            
//            Date l_datBizDate = WEB3DateUtility.getDate("20080401", "yyyyMMdd");
//            
//            Date l_datValidStartDate = WEB3DateUtility.getDate("200801", "yyyyMM");
//            
//            Timestamp l_tsPrizeClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080707112233", "yyyyMMddHHmmss").getTime());
//            
//            Timestamp l_tsCommonClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080808200000", "yyyyMMddHHmmss").getTime());
//            
//            l_serviceImpl.insertMutualFixedBuyConditionChange(
//                l_subAccount,
//                l_trader,
//                l_mutualFixedBuyCommonUnit,
//                l_mutualFixedBuyConditionUnit,
//                l_datBizDate,
//                l_datValidStartDate,
//                l_tsPrizeClosingDate,
//                l_tsCommonClosingDate);
//            
//            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
//                MfFixedBuyingChangeDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()));
//            
//            MfFixedBuyingChangeHistRow l_mfFixedBuyingChangeHistRow =
//                MfFixedBuyingChangeHistDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()),
//                    1);
//            
//            //定時定額買付条件変更
//            assertEquals("0D", l_mfFixedBuyingChangeRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(300, l_mfFixedBuyingChangeRow.getMonthlyBuyAmount(), 0);
//            assertEquals(500, l_mfFixedBuyingChangeRow.getIncreaseBuyAmount(), 0);
//            assertEquals("2", l_mfFixedBuyingChangeRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeRow.getOrderChanel());
//            assertEquals(true, l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull());
//            assertEquals("0", l_mfFixedBuyingChangeRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            
//            //定時定額買付条件変更履歴
//            assertEquals("0D", l_mfFixedBuyingChangeHistRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeHistRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeHistRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeHistRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(300, l_mfFixedBuyingChangeHistRow.getMonthlyBuyAmount(), 0);
//            assertEquals(500, l_mfFixedBuyingChangeHistRow.getIncreaseBuyAmount(), 0);
//            assertEquals("2", l_mfFixedBuyingChangeHistRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getOrderChanel());
//            assertEquals(true, l_mfFixedBuyingChangeHistRow.getFinDrawIncreaseBuyAmountIsNull());
//            assertEquals("0", l_mfFixedBuyingChangeHistRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeHistRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals(1L, l_mfFixedBuyingChangeHistRow.getSerialNo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testInsertMutualFixedBuyConditionChange_C0010()
//    {
//        final String STR_METHOD_NAME = "testInsertMutualFixedBuyConditionChange_C0010()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {           
//            WEB3MutualFixedBuyConditionServiceImpl l_serviceImpl =
//                new WEB3MutualFixedBuyConditionServiceImpl();
//
//            Timestamp l_tsSystemTime =
//                new Timestamp(WEB3DateUtility.getDate("20080707112233", "yyyyMMddHHmmss").getTime());
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                "getSystemTimestamp",
//                new Class[] {}, 
//                l_tsSystemTime);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
//                "isPrizeAndMonth",
//                new Class[] {String.class, Date.class}, 
//                Boolean.FALSE);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getSessionProperty",
//                new Class[] {String.class},
//                "1");
//        
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchtParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchtParams);
//            
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
//
//            TestDBUtility.deleteAll(MfFixedBuyingChangeHistRow.TYPE);
//
//            //引数をセット
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);            
//            Trader l_trader = new TraderImpl(3338111123L, true);
//            
//            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit =
//                new WEB3MutualFixedBuyCommonUnit();
//            l_mutualFixedBuyCommonUnit.mutualProductCode = "300100";
//            l_mutualFixedBuyCommonUnit.validStartDate = WEB3DateUtility.getDate("200802", "yyyyMM");
//            l_mutualFixedBuyCommonUnit.monthlyBuyAmount = "300";
//            l_mutualFixedBuyCommonUnit.increaseBuyAmount = "500";
//            l_mutualFixedBuyCommonUnit.changeDiv = "2";
//            
//            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
//                new WEB3MutualFixedBuyConditionUnit();
//            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "60";
//            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "80";
//            
//            Date l_datBizDate = WEB3DateUtility.getDate("20080401", "yyyyMMdd");
//            
//            Date l_datValidStartDate = WEB3DateUtility.getDate("200801", "yyyyMM");
//            
//            Timestamp l_tsPrizeClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080512142800", "yyyyMMddHHmmss").getTime());
//            
//            Timestamp l_tsCommonClosingDate =
//                new Timestamp(WEB3DateUtility.getDate("20080808200000", "yyyyMMddHHmmss").getTime());
            
//            l_serviceImpl.insertMutualFixedBuyConditionChange(
//                l_subAccount,
//                l_trader,
//                l_mutualFixedBuyCommonUnit,
//                l_mutualFixedBuyConditionUnit,
//                l_datBizDate,
//                l_datValidStartDate,
//                l_tsPrizeClosingDate,
//                l_tsCommonClosingDate);
//            
//            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
//                MfFixedBuyingChangeDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDate(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()));
//            
//            MfFixedBuyingChangeHistRow l_mfFixedBuyingChangeHistRow =
//                MfFixedBuyingChangeHistDao.findRowByInstitutionCodeBranchCodeAccountCodeProductCodeValidStartDateSerialNo(
//                    "0D",
//                    "381",
//                    "2512246",
//                    "300100",
//                    new Timestamp(WEB3DateUtility.getDate("200802", "yyyyMM").getTime()),
//                    1);
//            
//            //定時定額買付条件変更
//            assertEquals("0D", l_mfFixedBuyingChangeRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(300, l_mfFixedBuyingChangeRow.getMonthlyBuyAmount(), 0);
//            assertEquals(500, l_mfFixedBuyingChangeRow.getIncreaseBuyAmount(), 0);
//            assertEquals("2", l_mfFixedBuyingChangeRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeRow.getOrderChanel());
//            assertEquals(true, l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull());
//            assertEquals("0", l_mfFixedBuyingChangeRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            
//            //定時定額買付条件変更履歴
//            assertEquals("0D", l_mfFixedBuyingChangeHistRow.getInstitutionCode());
//            assertEquals("381", l_mfFixedBuyingChangeHistRow.getBranchCode());
//            assertEquals("2512246", l_mfFixedBuyingChangeHistRow.getAccountCode());
//            assertEquals("300100", l_mfFixedBuyingChangeHistRow.getProductCode());
//            assertEquals("200802", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getValidStartDate(), "yyyyMM"));
//            assertEquals(300, l_mfFixedBuyingChangeHistRow.getMonthlyBuyAmount(), 0);
//            assertEquals(500, l_mfFixedBuyingChangeHistRow.getIncreaseBuyAmount(), 0);
//            assertEquals("2", l_mfFixedBuyingChangeHistRow.getChangeDiv());
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getStatus());
//            assertEquals("20080401", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getBizDate(), "yyyyMMdd"));
//            assertEquals("20080707112233", 
//                WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getOrderTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("1", l_mfFixedBuyingChangeHistRow.getOrderChanel());
//            assertEquals(true, l_mfFixedBuyingChangeHistRow.getFinDrawIncreaseBuyAmountIsNull());
//            assertEquals("0", l_mfFixedBuyingChangeHistRow.getDeleteFlag().intValue() + "");
//            assertEquals("11123", l_mfFixedBuyingChangeHistRow.getLastUpdater());
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals("20080707112233", 
//                    WEB3DateUtility.formatDate(l_mfFixedBuyingChangeHistRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
//            assertEquals(1L, l_mfFixedBuyingChangeHistRow.getSerialNo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
    
    //0
    public void testGetLastNumber_C0001()
    {
        final String STR_METHOD_NAME = "testInsertMutualFixedBuyConditionChange_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {           
            WEB3MutualFixedBuyConditionServiceImpl l_serviceImpl =
                new WEB3MutualFixedBuyConditionServiceImpl();
            
            TestDBUtility.deleteAll(MfFixedBuyingChangeHistRow.TYPE);
            
            long l_lngSerialNo = l_serviceImpl.getLastNumber(
                "0D",
                "381",
                "2512246",
                "300100",
                WEB3DateUtility.getDate("200802", "yyyyMM"));
            
            assertEquals(1L, l_lngSerialNo); 
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);        
    }
    
    //1
    public void testGetLastNumber_C0002()
    {
        final String STR_METHOD_NAME = "testInsertMutualFixedBuyConditionChange_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {           
            WEB3MutualFixedBuyConditionServiceImpl l_serviceImpl =
                new WEB3MutualFixedBuyConditionServiceImpl();
            
            TestDBUtility.deleteAll(MfFixedBuyingChangeHistRow.TYPE);
            MfFixedBuyingChangeHistParams l_mfFixedBuyingChangeHistParams = 
                new MfFixedBuyingChangeHistParams();
            l_mfFixedBuyingChangeHistParams.setInstitutionCode("0D");
            l_mfFixedBuyingChangeHistParams.setBranchCode("381");
            l_mfFixedBuyingChangeHistParams.setAccountCode("2512246");
            l_mfFixedBuyingChangeHistParams.setProductCode("300100");
            l_mfFixedBuyingChangeHistParams.setValidStartDate(
                WEB3DateUtility.getDate("200802", "yyyyMM"));
            l_mfFixedBuyingChangeHistParams.setLastUpdater("111");
            l_mfFixedBuyingChangeHistParams.setCreatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            l_mfFixedBuyingChangeHistParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            l_mfFixedBuyingChangeHistParams.setSerialNo(1);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeHistParams);
            
            long l_lngSerialNo = l_serviceImpl.getLastNumber(
                    "0D",
                    "381",
                    "2512246",
                    "300100",
                    WEB3DateUtility.getDate("200802", "yyyyMM"));
                
            assertEquals(2L, l_lngSerialNo);            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);        
    }
    
    //3
    public void testGetLastNumber_C0003()
    {
        final String STR_METHOD_NAME = "testInsertMutualFixedBuyConditionChange_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {           
            WEB3MutualFixedBuyConditionServiceImpl l_serviceImpl =
                new WEB3MutualFixedBuyConditionServiceImpl();
            
            TestDBUtility.deleteAll(MfFixedBuyingChangeHistRow.TYPE);
            MfFixedBuyingChangeHistParams l_mfFixedBuyingChangeHistParams = 
                new MfFixedBuyingChangeHistParams();
            l_mfFixedBuyingChangeHistParams.setInstitutionCode("0D");
            l_mfFixedBuyingChangeHistParams.setBranchCode("381");
            l_mfFixedBuyingChangeHistParams.setAccountCode("2512246");
            l_mfFixedBuyingChangeHistParams.setProductCode("300100");
            l_mfFixedBuyingChangeHistParams.setValidStartDate(
                WEB3DateUtility.getDate("200802", "yyyyMM"));
            l_mfFixedBuyingChangeHistParams.setLastUpdater("111");
            l_mfFixedBuyingChangeHistParams.setCreatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            l_mfFixedBuyingChangeHistParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());

            l_mfFixedBuyingChangeHistParams.setSerialNo(1);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeHistParams);
            l_mfFixedBuyingChangeHistParams.setSerialNo(2);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeHistParams);
            l_mfFixedBuyingChangeHistParams.setSerialNo(3);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeHistParams);
            
            long l_lngSerialNo = l_serviceImpl.getLastNumber(
                    "0D",
                    "381",
                    "2512246",
                    "300100",
                    WEB3DateUtility.getDate("200802", "yyyyMM"));
                
            assertEquals(4L, l_lngSerialNo);            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);        
    }

    /**
     * 不滿足
     * リクエスト．投信定時定額買付積立登録内容．買付金額（積み増し） != nullの場合
     * 
     * 不滿足
     * <分岐処理> merge定時定額買付条件変更の件数が0件以外の場合
     * 
     * 正常終了
     */
    public void testValidateMutualFixedBuyConditionC0001()
    {
        final String STR_METHOD_NAME = "testvValidateMutualFixedBuyConditionC0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest();
        
        WEB3MutualFixedBuyConditionConfirmRequest l_request = new WEB3MutualFixedBuyConditionConfirmRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("200808", "yyyyMM"));
            
            WEB3MutualFixedBuyConditionConfirmResponse l_response =
                l_impl.validateMutualFixedBuyCondition(l_request);
            
            assertNull(l_response.conditionList);
            assertEquals(l_response.addConditionList[0].monthlyBuyAmount, "100");
            assertNull(l_response.acountInfo);
            assertNull(l_response.totalList);
            assertNull(l_response.categoryList);
            assertFalse(l_response.closingDateAlertFlag);
 
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    
    
    protected class WEB3MutualFixedBuyConditionServiceImplForTest extends WEB3MutualFixedBuyConditionServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getMainAccount()";
            log.entering(STR_METHOD_NAME);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_mainAccount;
            SubAccount l_subAccount;
            try
            {
                l_mainAccount = l_accMgr.getMainAccount(333812512246L);
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            }
            catch (NotFoundException e)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    e.getMessage(),
                    e);
            }
 
            log.exiting(STR_METHOD_NAME);
            return l_subAccount;
        }
        
        protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionChange(
            SubAccount l_subAccount, Date l_datValidStartDate) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "mergeMutualFixedBuyConditionChange(SubAccount, Date)";
            log.entering(STR_METHOD_NAME);
            
            WEB3MutualFixedBuyConditionUnit[] l_sortFixedBuyConditionLists = new WEB3MutualFixedBuyConditionUnit[1];
            WEB3MutualFixedBuyConditionUnit l_unit1 = new WEB3MutualFixedBuyConditionUnit();
            l_sortFixedBuyConditionLists[0] = l_unit1;
            
            return null;
        }
    }
    
    /**
     * リクエスト．投信定時定額買付積立登録内容．買付金額（積み増し） != nullの場合
     * 
     * <分岐処理> merge定時定額買付条件変更の件数が0件以外の場合
     * 
     * 正常終了
     */
    public void testValidateMutualFixedBuyConditionC0002()
    {
        final String STR_METHOD_NAME = "testvValidateMutualFixedBuyConditionC0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest2 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest2();
        
        WEB3MutualFixedBuyConditionConfirmRequest l_request = new WEB3MutualFixedBuyConditionConfirmRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "0";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080514", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "isPrizeAndMonth", new Class[]
                    {String.class, Date.class},
                    new Boolean(true));
            
            WEB3MutualFixedBuyConditionConfirmResponse l_response =
                l_impl.validateMutualFixedBuyCondition(l_request);
            
            assertFalse(l_response.conditionList[0].suspensionFlag);
            assertEquals(l_response.addConditionList[0].monthlyBuyAmount, "100");
            assertNull(l_response.acountInfo);
            assertNull(l_response.totalList);
            assertNull(l_response.categoryList);
            assertFalse(l_response.closingDateAlertFlag);
 
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    protected class WEB3MutualFixedBuyConditionServiceImplForTest2 extends WEB3MutualFixedBuyConditionServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getMainAccount()";
            log.entering(STR_METHOD_NAME);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_mainAccount;
            SubAccount l_subAccount;
            try
            {
                l_mainAccount = l_accMgr.getMainAccount(333812512246L);
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            }
            catch (NotFoundException e)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    e.getMessage(),
                    e);
            }
 
            log.exiting(STR_METHOD_NAME);
            return l_subAccount;
        }
        
        protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionChange(
            SubAccount l_subAccount, Date l_datValidStartDate) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "mergeMutualFixedBuyConditionChange(SubAccount, Date)";
            log.entering(STR_METHOD_NAME);
            
            WEB3MutualFixedBuyConditionUnit[] l_sortFixedBuyConditionLists = new WEB3MutualFixedBuyConditionUnit[1];
            WEB3MutualFixedBuyConditionUnit l_unit1 = new WEB3MutualFixedBuyConditionUnit();
            l_sortFixedBuyConditionLists[0] = l_unit1;
            
            return l_sortFixedBuyConditionLists;
        }
    }
    
    /**
     * BUSINESS_ERROR_00079
     *
     * リクエスト.validate()出現異常的場合
     */
    public void testValidateMutualFixedBuyConditionC0003()
    {
        final String STR_METHOD_NAME = "testvValidateMutualFixedBuyConditionC0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest2 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest2();
        
        WEB3MutualFixedBuyConditionConfirmRequest l_request = new WEB3MutualFixedBuyConditionConfirmRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = null;
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "0";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080514", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "isPrizeAndMonth", new Class[]
                    {String.class, Date.class},
                    new Boolean(true));
            
            l_impl.validateMutualFixedBuyCondition(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00079, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80006
     *
     * validate注文受付可能出現異常的場合
     */
    public void testValidateMutualFixedBuyConditionC0004()
    {
        final String STR_METHOD_NAME = "testvValidateMutualFixedBuyConditionC0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest2 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest2();
        
        WEB3MutualFixedBuyConditionConfirmRequest l_request = new WEB3MutualFixedBuyConditionConfirmRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "0";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080514", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "isPrizeAndMonth", new Class[]
                    {String.class, Date.class},
                    new Boolean(true));
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);
            
            l_impl.validateMutualFixedBuyCondition(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * SYSTEM_ERROR_80006
     *
     * validate取引可能顧客出現異常的場合
     */
    public void testValidateMutualFixedBuyConditionC0005()
    {
        final String STR_METHOD_NAME = "testvValidateMutualFixedBuyConditionC0005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest2 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest2();
        
        WEB3MutualFixedBuyConditionConfirmRequest l_request = new WEB3MutualFixedBuyConditionConfirmRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "0";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080514", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "isPrizeAndMonth", new Class[]
                    {String.class, Date.class},
                    new Boolean(true));
            
            
            
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(l_processingResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            l_impl.validateMutualFixedBuyCondition(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80007
     *
     * validate引落口座登録出現異常的場合
     */
    public void testValidateMutualFixedBuyConditionC0006()
    {
        final String STR_METHOD_NAME = "testvValidateMutualFixedBuyConditionC0006()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest2 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest2();
        
        WEB3MutualFixedBuyConditionConfirmRequest l_request = new WEB3MutualFixedBuyConditionConfirmRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "0";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080514", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "isPrizeAndMonth", new Class[]
                    {String.class, Date.class},
                    new Boolean(true));
            
            WEB3BaseException l_baseException = new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80007, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", new Class[]
                    {String.class, String.class, String.class},
                    l_baseException);

            l_impl.validateMutualFixedBuyCondition(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80007, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * BUSINESS_ERROR_01341
     * 
     * 外国投信か判定を行う
     * 外国証券口座開設済か判定を行う
     * 戻り値がfalseの場合、「外国証券口座未開設エラー」として例外をスローする
     */
    public void testValidateMutualFixedBuyConditionC0007()
    {
        final String STR_METHOD_NAME = "testvValidateMutualFixedBuyConditionC0007()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest2 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest2();
        
        WEB3MutualFixedBuyConditionConfirmRequest l_request = new WEB3MutualFixedBuyConditionConfirmRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "0";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080514", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "isPrizeAndMonth", new Class[]
                    {String.class, Date.class},
                    new Boolean(true));
            
            l_impl.validateMutualFixedBuyCondition(l_request);
 
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01341, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80007
     * 
     * validate定時定額買付金額出現異常的場合
     */
    public void testValidateMutualFixedBuyConditionC0008()
    {
        final String STR_METHOD_NAME = "testvValidateMutualFixedBuyConditionC0008()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest2 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest2();
        
        WEB3MutualFixedBuyConditionConfirmRequest l_request = new WEB3MutualFixedBuyConditionConfirmRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "0";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080514", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "isPrizeAndMonth", new Class[]
                    {String.class, Date.class},
                    new Boolean(true));
            
            WEB3BaseException l_baseException = new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80007, null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateFixedBuyAmount", new Class[]
                    {SubAccount.class, String.class, String.class},
                    l_baseException);
                    
            l_impl.validateMutualFixedBuyCondition(l_request);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80007, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * BUSINESS_ERROR_02480
     * 
     * ＜分岐処理＞is定時定額買付可能()の戻り値 == false の場合、例外をスローする
     */
    public void testValidateMutualFixedBuyConditionC0009()
    {
        final String STR_METHOD_NAME = "testvValidateMutualFixedBuyConditionC0009()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest2 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest2();
        
        WEB3MutualFixedBuyConditionConfirmRequest l_request = new WEB3MutualFixedBuyConditionConfirmRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "0";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080514", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "isPrizeAndMonth", new Class[]
                    {String.class, Date.class},
                    new Boolean(true));
            
            l_impl.validateMutualFixedBuyCondition(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02480, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * リクエスト．定時定額買付積立登録内容．買付金額（積み増し）== nullの場合
     * 
     * 正常終了
     */
    public void testSubmitMutualFixedBuyConditionC0001()
    {
        final String STR_METHOD_NAME = "testSubmitMutualFixedBuyConditionC0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest3 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest3();
        
        WEB3MutualFixedBuyConditionCompleteRequest l_request = new WEB3MutualFixedBuyConditionCompleteRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_traderParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("200808", "yyyyMM"));
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            
            WEB3MutualFixedBuyConditionCompleteResponse l_response =
                l_impl.submitMutualFixedBuyCondition(l_request);
            
            assertEquals(l_response.conditionList[0].mutualProductCode, "0001000");
            assertEquals(l_response.addConditionList[0].mutualProductCode, "0001000");
            assertNull(l_response.acountInfo);
            assertNull(l_response.totalList);
            assertNull(l_response.categoryList);
            assertFalse(l_response.closingDateAlertFlag);
 
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    
    /**
     * リクエスト．定時定額買付積立登録内容．買付金額（積み増し）!= nullの場合
     * 
     * 正常終了
     */
    public void testSubmitMutualFixedBuyConditionC0002()
    {
        final String STR_METHOD_NAME = "testSubmitMutualFixedBuyConditionC0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest4 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest4();
        
        WEB3MutualFixedBuyConditionCompleteRequest l_request = new WEB3MutualFixedBuyConditionCompleteRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "100";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_traderParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("200808", "yyyyMM"));
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            
            WEB3MutualFixedBuyConditionCompleteResponse l_response =
                l_impl.submitMutualFixedBuyCondition(l_request);
            
            assertEquals(l_response.conditionList[0].mutualProductCode, "0001000");
            assertEquals(l_response.addConditionList[0].mutualProductCode, "0001000");
            assertNull(l_response.acountInfo);
            assertNull(l_response.totalList);
            assertNull(l_response.categoryList);
            assertFalse(l_response.closingDateAlertFlag);
 
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    protected class WEB3MutualFixedBuyConditionServiceImplForTest3 extends WEB3MutualFixedBuyConditionServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getMainAccount()";
            log.entering(STR_METHOD_NAME);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_mainAccount;
            SubAccount l_subAccount;
            try
            {
                l_mainAccount = l_accMgr.getMainAccount(333812512246L);
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            }
            catch (NotFoundException e)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    e.getMessage(),
                    e);
            }
 
            log.exiting(STR_METHOD_NAME);
            return l_subAccount;
        }
        
        protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionChange(
            SubAccount l_subAccount, Date l_datValidStartDate) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "mergeMutualFixedBuyConditionChange(SubAccount, Date)";
            log.entering(STR_METHOD_NAME);
            
            WEB3MutualFixedBuyConditionUnit[] l_sortFixedBuyConditionLists = new WEB3MutualFixedBuyConditionUnit[1];
            WEB3MutualFixedBuyConditionUnit l_unit1 = new WEB3MutualFixedBuyConditionUnit();
            l_unit1.mutualProductCode = "0001000";
            l_sortFixedBuyConditionLists[0] = l_unit1;
            
            return l_sortFixedBuyConditionLists;
        }
        
        protected void updateMutualFixedBuyConditionChange(
            SubAccount l_subAccount,
            Trader l_trader,
            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit,
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit,
            Date l_datBizDate,
            Date l_datValidStartDate,
            Timestamp l_tsPrizeClosingDate,
            Timestamp l_tsCommonClosingDate) throws WEB3BaseException
        {
            
        }
        protected void insertMutualFixedBuyConditionChange(
            SubAccount l_subAccount,
            Trader l_trader,
            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit,
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit,
            Date l_datBizDate,
            Date l_datValidStartDate,
            Timestamp l_tsPrizeClosingDate,
            Timestamp l_tsCommonClosingDate)
            throws WEB3BaseException
        {
        
        }
    }
    
    
    protected class WEB3MutualFixedBuyConditionServiceImplForTest4 extends WEB3MutualFixedBuyConditionServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getMainAccount()";
            log.entering(STR_METHOD_NAME);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_mainAccount;
            SubAccount l_subAccount;
            try
            {
                l_mainAccount = l_accMgr.getMainAccount(333812512246L);
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            }
            catch (NotFoundException e)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    e.getMessage(),
                    e);
            }
 
            log.exiting(STR_METHOD_NAME);
            return l_subAccount;
        }
        
        protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionChange(
            SubAccount l_subAccount, Date l_datValidStartDate) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "mergeMutualFixedBuyConditionChange(SubAccount, Date)";
            log.entering(STR_METHOD_NAME);
            
            WEB3MutualFixedBuyConditionUnit[] l_sortFixedBuyConditionLists = new WEB3MutualFixedBuyConditionUnit[1];
            WEB3MutualFixedBuyConditionUnit l_unit1 = new WEB3MutualFixedBuyConditionUnit();
            l_unit1.mutualProductCode = "0001000";
            l_sortFixedBuyConditionLists[0] = l_unit1;
            
            return l_sortFixedBuyConditionLists;
        }
        
        protected void updateMutualFixedBuyConditionChange(
            SubAccount l_subAccount,
            Trader l_trader,
            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit,
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit,
            Date l_datBizDate,
            Date l_datValidStartDate,
            Timestamp l_tsPrizeClosingDate,
            Timestamp l_tsCommonClosingDate) throws WEB3BaseException
        {
            
        }
        protected void insertMutualFixedBuyConditionChange(
            SubAccount l_subAccount,
            Trader l_trader,
            WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit,
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit,
            Date l_datBizDate,
            Date l_datValidStartDate,
            Timestamp l_tsPrizeClosingDate,
            Timestamp l_tsCommonClosingDate)
            throws WEB3BaseException
        {
        
        }
    }
    
    /**
     * BUSINESS_ERROR_00079
     * 
     * リクエスト.validate()出現異常的場合
     */
    public void testSubmitMutualFixedBuyConditionC0003()
    {
        final String STR_METHOD_NAME = "testSubmitMutualFixedBuyConditionC0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest4 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest4();
        
        WEB3MutualFixedBuyConditionCompleteRequest l_request = new WEB3MutualFixedBuyConditionCompleteRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = null;
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "100";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_traderParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("200808", "yyyyMM"));
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            
            l_impl.submitMutualFixedBuyCondition(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00079, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80006
     *
     * validate注文受付可能出現異常的場合
     */
    public void testSubmitMutualFixedBuyConditionC0004()
    {
        final String STR_METHOD_NAME = "testSubmitMutualFixedBuyConditionC0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest4 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest4();
        
        WEB3MutualFixedBuyConditionCompleteRequest l_request = new WEB3MutualFixedBuyConditionCompleteRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "100";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("200808", "yyyyMM"));
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);
            
            l_impl.submitMutualFixedBuyCondition(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80006
     *
     * validate取引可能顧客出現異常的場合
     */
    public void testSubmitMutualFixedBuyConditionC0005()
    {
        final String STR_METHOD_NAME = "testSubmitMutualFixedBuyConditionC0005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest4 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest4();
        
        WEB3MutualFixedBuyConditionCompleteRequest l_request = new WEB3MutualFixedBuyConditionCompleteRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "100";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("200808", "yyyyMM"));
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(l_processingResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            l_impl.submitMutualFixedBuyCondition(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80006
     * 
     * validate取引パスワード出現異常的場合
     */
    public void testSubmitMutualFixedBuyConditionC0006()
    {
        final String STR_METHOD_NAME = "testSubmitMutualFixedBuyConditionC0006()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest4 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest4();
        
        WEB3MutualFixedBuyConditionCompleteRequest l_request = new WEB3MutualFixedBuyConditionCompleteRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "100";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_traderParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("200808", "yyyyMM"));
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(l_processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
                new Class[] { Trader.class, SubAccount.class, String.class },
                l_orderValidationResult);
                    
            l_impl.submitMutualFixedBuyCondition(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80007
     *
     * validate引落口座登録出現異常的場合
     */
    public void testSubmitMutualFixedBuyConditionC0007()
    {
        final String STR_METHOD_NAME = "testSubmitMutualFixedBuyConditionC0007()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest4 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest4();
        
        WEB3MutualFixedBuyConditionCompleteRequest l_request = new WEB3MutualFixedBuyConditionCompleteRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "100";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);
        
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_traderParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("200808", "yyyyMM"));
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            
            WEB3BaseException l_baseException = new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80007, null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", new Class[]
                    {String.class, String.class, String.class},
                    l_baseException);

            l_impl.submitMutualFixedBuyCondition(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80007, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * BUSINESS_ERROR_01341
     * 
     * 外国証券口座開設済か判定を行う
     * 戻り値がfalseの場合、「外国証券口座未開設エラー」として例外をスローする
     */
    public void testSubmitMutualFixedBuyConditionC0008()
    {
        final String STR_METHOD_NAME = "testSubmitMutualFixedBuyConditionC0008()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest4 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest4();
        
        WEB3MutualFixedBuyConditionCompleteRequest l_request = new WEB3MutualFixedBuyConditionCompleteRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "100";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_traderParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("200808", "yyyyMM"));
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            
            l_impl.submitMutualFixedBuyCondition(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01341, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80007
     * 
     * validate定時定額買付金額出現異常的場合
     */
    public void testSubmitMutualFixedBuyConditionC0009()
    {
        final String STR_METHOD_NAME = "testSubmitMutualFixedBuyConditionC0009()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest4 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest4();
        
        WEB3MutualFixedBuyConditionCompleteRequest l_request = new WEB3MutualFixedBuyConditionCompleteRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "100";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_traderParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("200808", "yyyyMM"));
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            
            WEB3BaseException l_baseException = new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80007, null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateFixedBuyAmount", new Class[]
                    {SubAccount.class, String.class, String.class},
                    l_baseException);
                    
            l_impl.submitMutualFixedBuyCondition(l_request);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80007, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * BUSINESS_ERROR_02480
     * 
     * ＜分岐処理＞is定時定額買付可能()の戻り値 == false の場合、例外をスローする
     */
    public void testSubmitMutualFixedBuyConditionC0010()
    {
        final String STR_METHOD_NAME = "testSubmitMutualFixedBuyConditionC0010()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImplForTest4 l_impl =
            new WEB3MutualFixedBuyConditionServiceImplForTest4();
        
        WEB3MutualFixedBuyConditionCompleteRequest l_request = new WEB3MutualFixedBuyConditionCompleteRequest();
        WEB3MutualFixedBuyCommonUnit l_conditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_conditionList1.mutualProductCode = "0001000";
        l_conditionList1.changeDiv = "1";
        l_conditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_conditionList1.increaseBuyAmount = "100";
        WEB3MutualFixedBuyCommonUnit[] l_conditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_conditionLists[0] = l_conditionList1;
        l_request.conditionList = l_conditionLists;
        
        WEB3MutualFixedBuyCommonUnit l_addConditionList1 = new WEB3MutualFixedBuyCommonUnit();
        l_addConditionList1.mutualProductCode = "0001000";
        l_addConditionList1.monthlyBuyAmount = "100";
        l_addConditionList1.changeDiv = "1";
        l_addConditionList1.validStartDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        WEB3MutualFixedBuyCommonUnit[] l_addConditionLists = new WEB3MutualFixedBuyCommonUnit[1];
        l_addConditionLists[0] = l_addConditionList1;
        l_request.addConditionList = l_addConditionLists;
        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setBranchId(33381);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams1 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams1.setName("0D_SBS_DRAW_DD");
            l_SystemPreferencesParams1.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams1);
            SystemPreferencesParams l_SystemPreferencesParams2 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams2.setName("0D_SBS_DAY_COUNT");
            l_SystemPreferencesParams2.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams2);
            SystemPreferencesParams l_SystemPreferencesParams3 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams3.setName("0D_SBS_DRAW_DD_BONUS");
            l_SystemPreferencesParams3.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams3);
            SystemPreferencesParams l_SystemPreferencesParams4 = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams4.setName("0D_SBS_DAY_COUNT_BONUS");
            l_SystemPreferencesParams4.setValue("06");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams4);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("080808");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            l_mutualFundProductParams.setProductId(0100000L);
            l_mutualFundProductParams.setProductCode("0001000");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            l_mutualFundProductParams.setFixedBuyPossibleDiv("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductId(0100000L);
            l_ProductParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("mf.fixed.buying.min.amount");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("mf.fixed.buying.unit.amount");
            l_branchPreferencesParams1.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_traderParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", 
                    new Class[] { String.class, String.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    { String.class, String.class}, 
                    WEB3DateUtility.getDate("200808", "yyyyMM"));
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            
            l_impl.submitMutualFixedBuyCondition(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02480, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testMergeMutualFixedBuyConditionChangeMonth_C0001()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl = new WEB3MutualFixedBuyConditionServiceImpl();

        Services.unregisterService(WEB3MutualFixedBuyCommonService.class);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(123L));

        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();

        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strBranchCode =
                l_subAccount1.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount1.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount1.getInstitution().getInstitutionCode();

            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionList(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    null, null);

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            l_impl.mergeMutualFixedBuyConditionChangeMonth(
                l_institution,
                "381",
                l_lisFixedBuyConditionList,
                l_lisFixedBuyConditionChangeList);

            fail();
        }
        catch (IllegalArgumentException l_ex)
        {
            assertEquals("Service not found: webbroker3.mf.WEB3MutualFixedBuyCommonService",
                l_ex.getMessage());
            Services.registerService(WEB3MutualFixedBuyCommonService.class,
                new WEB3MutualFixedBuyCommonServiceImpl());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0002()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            List l_lisFixedBuyConditionChangeList = null;

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionList(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    null, null);

            l_impl.mergeMutualFixedBuyConditionChangeMonth(
                l_institution,
                "381",
                l_lisFixedBuyConditionList,
                l_lisFixedBuyConditionChangeList);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            assertEquals("パラメータ値不正。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0003()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            List l_lisFixedBuyConditionList = null;

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            l_impl.mergeMutualFixedBuyConditionChangeMonth(
                l_institution,
                "381",
                l_lisFixedBuyConditionList,
                l_lisFixedBuyConditionChangeList);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            assertEquals("パラメータ値不正。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0004()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080611", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080611", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                    l_strSubAccountInstitutionCode,
                    l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                    WEB3MFStatusDef.SONAR_NOT_SEND,
                    WEB3MFStatusDef.SONAR_SENDED,
                    WEB3ChangeDivDef.ADD,
                    l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "1234",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                100);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 1);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 1);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0005()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080611", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080611", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                    l_strSubAccountInstitutionCode,
                    l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                    WEB3MFStatusDef.SONAR_NOT_SEND,
                    WEB3MFStatusDef.SONAR_SENDED,
                    WEB3ChangeDivDef.ADD,
                    l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "1234",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                100);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0006()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080611", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080611", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                    l_strSubAccountInstitutionCode,
                    l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                    WEB3MFStatusDef.SONAR_NOT_SEND,
                    WEB3MFStatusDef.SONAR_SENDED,
                    WEB3ChangeDivDef.ADD,
                    l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "1234",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                100);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 1);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 1);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0007()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080611", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080611", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                    l_strSubAccountInstitutionCode,
                    l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                    WEB3MFStatusDef.SONAR_NOT_SEND,
                    WEB3MFStatusDef.SONAR_SENDED,
                    WEB3ChangeDivDef.ADD,
                    l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "1234",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                100);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertTrue(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0008()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080611", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080611", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                    l_strSubAccountInstitutionCode,
                    l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.RELEASE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                    WEB3MFStatusDef.SONAR_NOT_SEND,
                    WEB3MFStatusDef.SONAR_SENDED,
                    WEB3ChangeDivDef.ADD,
                    l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "1234",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 0);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0009()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                100);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0010()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("15");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                100);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0011()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("15");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                100);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0012()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                100);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertTrue(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0013()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.RELEASE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0014()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            l_mfFixedBuyingCondParams.setMonthlyBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(010000L);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductId(010001L);
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(010000L);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams.setProductId(010001L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 2);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                200);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                200);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);

            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductCode, "1234");
            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[1].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[1].increaseBuyAmount),
                100);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].validStartDate,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].debitAccountYM,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[1].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[1].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[1].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0015()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("1234");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            l_mfFixedBuyingCondParams.setMonthlyBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(010000L);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductId(010001L);
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(010000L);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams.setProductId(010001L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 2);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "1234");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                200);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                200);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);

            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[1].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[1].increaseBuyAmount),
                100);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].validStartDate,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].debitAccountYM,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[1].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[1].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[1].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0016()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("1234");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            l_mfFixedBuyingCondParams.setMonthlyBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("07");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("20");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(010000L);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            MutualFundProductParams l_mutualFundProductParams1 =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams1.setProductCode("1234");
            l_mutualFundProductParams1.setProductId(010001L);
            l_mutualFundProductParams1.setProductIssueCode("0");
            l_mutualFundProductParams1.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(010000L);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams.setProductId(010001L);
            TestDBUtility.insertWithDel(l_productParams);

            MutualFundProduct l_mutualFundProduct =
                new MutualFundProductImpl(l_mutualFundProductParams1);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProductManager",
                    "getMutualFundProduct",
                    new Class[] {Institution.class, String.class, String.class},
                    l_mutualFundProduct);
              
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0017()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("1234");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            l_mfFixedBuyingCondParams.setMonthlyBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(010000L);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductId(010001L);
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(010000L);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams.setProductId(010001L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 2);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "1234");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                200);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                200);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);

            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[1].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[1].increaseBuyAmount),
                100);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].validStartDate,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].debitAccountYM,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[1].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[1].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[1].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0018()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0018()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("1234");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            l_mfFixedBuyingCondParams.setMonthlyBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(010000L);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductId(010001L);
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(010000L);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams.setProductId(010001L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 2);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "1234");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                200);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                200);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);

            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[1].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[1].increaseBuyAmount),
                100);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].validStartDate,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].debitAccountYM,
                WEB3DateUtility.getDate("20080801", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[1].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[1].checkResult);
            assertTrue(l_mutualFixedBuyConditionUnits[1].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeMonth_C0019()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0019()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("1234");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080610", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080610", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            l_mfFixedBuyingCondParams.setMonthlyBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(010000L);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductId(010001L);
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(010000L);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams.setProductId(010001L);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.RELEASE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeMonth(
                    l_institution,
                    "381",
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeFutureMonth_C0001()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionChangeFutureMonth_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl = new WEB3MutualFixedBuyConditionServiceImpl();

        Services.unregisterService(WEB3MutualFixedBuyCommonService.class);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(123L));

        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();

        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strBranchCode =
                l_subAccount1.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount1.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount1.getInstitution().getInstitutionCode();

            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionList(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    null, null);

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            l_impl.mergeMutualFixedBuyConditionChangeMonth(
                l_institution,
                "381",
                l_lisFixedBuyConditionList,
                l_lisFixedBuyConditionChangeList);

            fail();
        }
        catch (IllegalArgumentException l_ex)
        {
            assertEquals("Service not found: webbroker3.mf.WEB3MutualFixedBuyCommonService",
                l_ex.getMessage());
            Services.registerService(WEB3MutualFixedBuyCommonService.class,
                new WEB3MutualFixedBuyCommonServiceImpl());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeFutureMonth_C0002()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            List l_lisFixedBuyConditionChangeList = null;

            Date l_datValidStartDate = WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strWhere = " draw_date >= ? ";
            Object[] l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit[] l_mergeMutualFixedBuyConditionUnitLists =
                new WEB3MutualFixedBuyConditionUnit[l_lisFixedBuyConditionList.size()];
            l_lisFixedBuyConditionList.toArray(l_mergeMutualFixedBuyConditionUnitLists);
            l_impl.mergeMutualFixedBuyConditionChangeFutureMonth(
                l_institution,
                "381",
                l_mergeMutualFixedBuyConditionUnitLists,
                l_lisFixedBuyConditionChangeList);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            assertEquals("パラメータ値不正。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeFutureMonth_C0003()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionChangeFutureMonth_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            MutualFundProduct l_mutualFundProduct = new MutualFundProductImpl(
                l_mutualFundProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_mutualFundProduct);

            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(150);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            List l_lisFixedBuyConditionList = new ArrayList();
            WEB3MutualFixedBuyConditionUnit[] l_mergeMutualFixedBuyConditionUnitLists =
                new WEB3MutualFixedBuyConditionUnit[l_lisFixedBuyConditionList.size()];
            l_lisFixedBuyConditionList.toArray(l_mergeMutualFixedBuyConditionUnitLists);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeFutureMonth(
                    l_institution,
                    "381",
                    l_mergeMutualFixedBuyConditionUnitLists,
                    l_lisFixedBuyConditionChangeList);

//            assertEquals(l_mutualFixedBuyConditionUnits.length, 0);
//            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
//            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
//            assertEquals(
//                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
//                100);
//            assertEquals(
//                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
//                150);
//            int intCompreResult = WEB3DateUtility.compareToDay(
//                l_mutualFixedBuyConditionUnits[0].validStartDate, l_datValidStartDate);
//            assertEquals(intCompreResult, 0);
//            intCompreResult = WEB3DateUtility.compareToDay(
//                l_mutualFixedBuyConditionUnits[0].debitAccountYM, l_datValidStartDate);
//            assertEquals(intCompreResult, 0);
//            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
//            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
//            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeFutureMonth_C0004()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionChangeFutureMonth_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            MutualFundProduct l_mutualFundProduct = new MutualFundProductImpl(
                l_mutualFundProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_mutualFundProduct);


            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(150);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            List l_lisFixedBuyConditionList = new ArrayList();
            WEB3MutualFixedBuyConditionUnit[] l_mergeMutualFixedBuyConditionUnitLists =
                new WEB3MutualFixedBuyConditionUnit[l_lisFixedBuyConditionList.size()];
            l_lisFixedBuyConditionList.toArray(l_mergeMutualFixedBuyConditionUnitLists);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeFutureMonth(
                    l_institution,
                    "381",
                    l_mergeMutualFixedBuyConditionUnitLists,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                150);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate, l_datValidStartDate);
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM, l_datValidStartDate);
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeFutureMonth_C0005()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionChangeFutureMonth_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            MutualFundProduct l_mutualFundProduct = new MutualFundProductImpl(
                l_mutualFundProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_mutualFundProduct);


            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(150);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            List l_lisFixedBuyConditionList = new ArrayList();
            WEB3MutualFixedBuyConditionUnit[] l_mergeMutualFixedBuyConditionUnitLists =
                new WEB3MutualFixedBuyConditionUnit[l_lisFixedBuyConditionList.size()];
            l_lisFixedBuyConditionList.toArray(l_mergeMutualFixedBuyConditionUnitLists);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeFutureMonth(
                    l_institution,
                    "381",
                    l_mergeMutualFixedBuyConditionUnitLists,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                150);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate, l_datValidStartDate);
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM, l_datValidStartDate);
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertTrue(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeFutureMonth_C0006()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionChangeFutureMonth_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            MutualFundProduct l_mutualFundProduct = new MutualFundProductImpl(
                l_mutualFundProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_mutualFundProduct);


            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            Date l_datValidStartDate = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.RELEASE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(150);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            List l_lisFixedBuyConditionList = new ArrayList();
            WEB3MutualFixedBuyConditionUnit[] l_mergeMutualFixedBuyConditionUnitLists =
                new WEB3MutualFixedBuyConditionUnit[l_lisFixedBuyConditionList.size()];
            l_lisFixedBuyConditionList.toArray(l_mergeMutualFixedBuyConditionUnitLists);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeFutureMonth(
                    l_institution,
                    "381",
                    l_mergeMutualFixedBuyConditionUnitLists,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeFutureMonth_C0007()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionChangeFutureMonth_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            MutualFundProduct l_mutualFundProduct = new MutualFundProductImpl(
                l_mutualFundProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_mutualFundProduct);


            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            Date l_datValidStartDate = WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(150);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);
            WEB3MutualFixedBuyConditionUnit[] l_mergeMutualFixedBuyConditionUnitLists =
                new WEB3MutualFixedBuyConditionUnit[l_lisFixedBuyConditionList.size()];
            l_lisFixedBuyConditionList.toArray(l_mergeMutualFixedBuyConditionUnitLists);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeFutureMonth(
                    l_institution,
                    "381",
                    l_mergeMutualFixedBuyConditionUnitLists,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                150);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeFutureMonth_C0008()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionChangeFutureMonth_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            MutualFundProduct l_mutualFundProduct = new MutualFundProductImpl(
                l_mutualFundProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_mutualFundProduct);


            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            Date l_datValidStartDate = WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(150);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);
            WEB3MutualFixedBuyConditionUnit[] l_mergeMutualFixedBuyConditionUnitLists =
                new WEB3MutualFixedBuyConditionUnit[l_lisFixedBuyConditionList.size()];
            l_lisFixedBuyConditionList.toArray(l_mergeMutualFixedBuyConditionUnitLists);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeFutureMonth(
                    l_institution,
                    "381",
                    l_mergeMutualFixedBuyConditionUnitLists,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                150);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeFutureMonth_C0009()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionChangeFutureMonth_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            MutualFundProduct l_mutualFundProduct = new MutualFundProductImpl(
                l_mutualFundProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_mutualFundProduct);


            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            Date l_datValidStartDate = WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(150);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);
            WEB3MutualFixedBuyConditionUnit[] l_mergeMutualFixedBuyConditionUnitLists =
                new WEB3MutualFixedBuyConditionUnit[l_lisFixedBuyConditionList.size()];
            l_lisFixedBuyConditionList.toArray(l_mergeMutualFixedBuyConditionUnitLists);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeFutureMonth(
                    l_institution,
                    "381",
                    l_mergeMutualFixedBuyConditionUnitLists,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                150);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertTrue(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeFutureMonth_C0010()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionChangeFutureMonth_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            MutualFundProduct l_mutualFundProduct = new MutualFundProductImpl(
                l_mutualFundProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_mutualFundProduct);


            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            Date l_datValidStartDate = WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(150);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);
            WEB3MutualFixedBuyConditionUnit[] l_mergeMutualFixedBuyConditionUnitLists =
                new WEB3MutualFixedBuyConditionUnit[l_lisFixedBuyConditionList.size()];
            l_lisFixedBuyConditionList.toArray(l_mergeMutualFixedBuyConditionUnitLists);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeFutureMonth(
                    l_institution,
                    "381",
                    l_mergeMutualFixedBuyConditionUnitLists,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                150);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeFutureMonth_C0011()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionChangeFutureMonth_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            MutualFundProduct l_mutualFundProduct = new MutualFundProductImpl(
                l_mutualFundProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_mutualFundProduct);


            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            Date l_datValidStartDate = WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(150);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);
            WEB3MutualFixedBuyConditionUnit[] l_mergeMutualFixedBuyConditionUnitLists =
                new WEB3MutualFixedBuyConditionUnit[l_lisFixedBuyConditionList.size()];
            l_lisFixedBuyConditionList.toArray(l_mergeMutualFixedBuyConditionUnitLists);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeFutureMonth(
                    l_institution,
                    "381",
                    l_mergeMutualFixedBuyConditionUnitLists,
                    l_lisFixedBuyConditionChangeList);


            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                150);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeFutureMonth_C0012()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionChangeFutureMonth_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            MutualFundProduct l_mutualFundProduct = new MutualFundProductImpl(
                l_mutualFundProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_mutualFundProduct);


            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            Date l_datValidStartDate = WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(150);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            l_strWhere = " draw_date >= ? ";
            l_objValues = new Object[]{l_datValidStartDate};
            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    "123",
                    l_strWhere,
                    l_objValues);
            WEB3MutualFixedBuyConditionUnit[] l_mergeMutualFixedBuyConditionUnitLists =
                new WEB3MutualFixedBuyConditionUnit[l_lisFixedBuyConditionList.size()];
            l_lisFixedBuyConditionList.toArray(l_mergeMutualFixedBuyConditionUnitLists);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeFutureMonth(
                    l_institution,
                    "381",
                    l_mergeMutualFixedBuyConditionUnitLists,
                    l_lisFixedBuyConditionChangeList);


            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "シンセンテルス");
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount),
                100);
            assertEquals(
                (int)Double.parseDouble(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount),
                150);
            int intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081010",WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertTrue(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionChangeFutureMonth_C0013()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionChangeFutureMonth_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("1234");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080811", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            l_mfFixedBuyingCondParams.setMonthlyBuyAmount(250);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("28");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("051202");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            MutualFundProduct l_mutualFundProduct = new MutualFundProductImpl(
                l_mutualFundProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_mutualFundProduct);


            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            Date l_datValidStartDate =
                WEB3DateUtility.getDate("20080612", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(l_datValidStartDate);
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.RELEASE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(150);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            String l_strWhere = " (status in (?, ?) or change_div = ?) and valid_start_date = ? ";
            Object[] l_objValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED,
                WEB3ChangeDivDef.ADD,
                l_datValidStartDate};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    l_strWhere,
                    l_objValues);

            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.mutualProductCode = l_mfFixedBuyingCondParams.getProductCode();
            l_mutualFixedBuyConditionUnit.debitAccountYM = l_mfFixedBuyingCondParams.getDrawDate();
            l_mutualFixedBuyConditionUnit.mutualProductName = "シンセンテルス";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                String.valueOf(l_mfFixedBuyingCondParams.getMonthlyBuyAmount());
            l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                String.valueOf(l_mfFixedBuyingCondParams.getIncreaseBuyAmount());
            WEB3MutualFixedBuyConditionUnit[] l_mergeMutualFixedBuyConditionUnitLists =
                new WEB3MutualFixedBuyConditionUnit[1];
            l_mergeMutualFixedBuyConditionUnitLists[0] = l_mutualFixedBuyConditionUnit;

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionChangeFutureMonth(
                    l_institution,
                    "381",
                    l_mergeMutualFixedBuyConditionUnitLists,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * SYSTEM_ERROR_80017
     *
     * 投信定時定額買付条件行[] == null的場合
     */
    public void testGetFixedBuyTotalUnit_C0001()
    {
        final String STR_METHOD_NAME = "testGetFixedBuyTotalUnit_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();
        
        try
        {
            l_impl.getFixedBuyTotalUnit(null);
        
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    /**
     * 投信定時定額買付条件行.一時停止中フラグ　@==　@false の場合
     *
     * 投信定時定額買付金額合計.月々合計 =
     * 投信定時定額買付金額合計.月々合計 + 投信定時定額買付条件行.買付金額（月々）の場合
     *
     * 投信定時定額買付金額合計.積み増し合計 =
     * 投信定時定額買付金額合計.積み増し合計 + 投信定時定額買付条件行.買付金額（積み増し）の場合
     *
     * 投信定時定額買付金額合計.口座引落年月 = ｎｕｌｌの場合
     * 
     * 正常終了
     */
    public void testGetFixedBuyTotalUnit_C0002()
    {
        final String STR_METHOD_NAME = "testGetFixedBuyTotalUnit_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3MutualFixedBuyConditionServiceImpl l_impl =
            new WEB3MutualFixedBuyConditionServiceImpl();
        
        try
        {
            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                new WEB3MutualFixedBuyConditionUnit[1];
            l_mutualFixedBuyConditionUnits[0] = new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnits[0].suspensionFlag = false;
            Date l_datdebitAccountYM = WEB3DateUtility.getDate("200404", "yyyyMM");
            l_mutualFixedBuyConditionUnits[0].debitAccountYM = l_datdebitAccountYM;
            WEB3MutualFixedBuyTotalUnit[] l_mutualFixedBuyTotalUnit =
                l_impl.getFixedBuyTotalUnit(l_mutualFixedBuyConditionUnits);
        
            assertEquals(l_mutualFixedBuyTotalUnit[0].increaseBATotal, "0");

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
}
@
