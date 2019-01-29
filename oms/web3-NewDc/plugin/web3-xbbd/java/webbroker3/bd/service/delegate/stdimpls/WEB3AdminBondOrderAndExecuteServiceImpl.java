head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderAndExecuteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者新規約定入力サービスImpl(WEB3AdminBondOrderAndExecuteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
                 : 2006/10/12 張騰宇 (中訊) WEBⅢ開発標準の見直しの対応（newBigDecimal部分）
                 : 2006/10/16 趙林鵬 (中訊) モデル No.106.108.121.129.131 ＤＢ更新仕様No.019
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.bd.WEB3AdminBondNewExecuteUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondNewOrderUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondOrderAcceptUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondOrderAndExecuteCommonInterceptor;
import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondNewOrderSpec;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondTradingTypeDef;
import webbroker3.bd.define.WEB3BondWarningDivDef;
import webbroker3.bd.message.WEB3AdminBondAccountInfo;
import webbroker3.bd.message.WEB3AdminBondCustodianUnit;
import webbroker3.bd.message.WEB3AdminBondExecCalculateRequest;
import webbroker3.bd.message.WEB3AdminBondExecCalculateResponse;
import webbroker3.bd.message.WEB3AdminBondExecCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondExecInputRequest;
import webbroker3.bd.message.WEB3AdminBondExecInputResponse;
import webbroker3.bd.message.WEB3AdminBondOrderExecInfo;
import webbroker3.bd.message.WEB3AdminBondOrderInfo;
import webbroker3.bd.message.WEB3AdminBondProductInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteNotifyService;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderAndExecuteService;
import webbroker3.bd.service.delegate.WEB3BondDataManagerService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderSettleDivDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者新規約定入力サービスImpl)<BR>
 * 管理者新規約定入力サービスImplクラス
 *
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondOrderAndExecuteServiceImpl implements WEB3AdminBondOrderAndExecuteService
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderAndExecuteServiceImpl.class);

    /**
     * @@roseuid 44E3362C0148
     */
    public WEB3AdminBondOrderAndExecuteServiceImpl()
    {

    }

    /**
     * 管理者新規約定入力サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図「新規約定入力execute」を参照
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@roseuid 44B61BFD006D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3GenResponse l_response = null;
        //1.1.リクエストデータは管理者新規約定入力リクエストである場合
        if (l_request instanceof WEB3AdminBondExecInputRequest)
        {
            //1.1.1.get管理者新規約定入力画面(管理者新規約定入力リクエスト)
            l_response =
                getExecuteInputScreen((WEB3AdminBondExecInputRequest)l_request);
        }
        //1.2.リクエストデータは管理者新規約定確認リクエストである場合
        else if (l_request instanceof WEB3AdminBondExecConfirmRequest)
        {
            //1.2.1.validate管理者新規約定入力(管理者新規約定確認リクエスト)
            l_response =
                validateExecuteInput((WEB3AdminBondExecConfirmRequest)l_request);
        }
        //1.3.リクエストデータは管理者新規約定完了リクエストである場合
        else if (l_request instanceof WEB3AdminBondExecCompleteRequest)
        {
            //1.3.1.submit管理者新規約定入力(管理者新規約定完了リクエスト)
            l_response =
                submitExecuteInput((WEB3AdminBondExecCompleteRequest)l_request);
        }
        //1.4.リクエストデータは管理者新規約定計算リクエストである場合
        else if (l_request instanceof WEB3AdminBondExecCalculateRequest)
        {
            //1.4.1.calc受渡代金(受渡代金計算リクエスト)
            l_response =
                calcEstimatedPrice((WEB3AdminBondExecCalculateRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get管理者新規約定入力画面)<BR>
     * 新規約定入力画面取得処理を行う。<BR>
     * <BR>
     * シーケンス図「get管理者新規約定入力画面」を参照
     * @@param l_request - (管理者新規約定入力リクエスト)
     * @@return WEB3AdminBondExecInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B6224F02AF
     */
    protected WEB3AdminBondExecInputResponse getExecuteInputScreen(
        WEB3AdminBondExecInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecuteInputScreen(WEB3AdminBondExecInputRequest)";
        log.entering(STR_METHOD_NAME);
        //1.1.validate( )
        l_request.validate();
        //1.2.getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3.validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_INPUT, true);

        //1.4get証券会社( )
        Institution l_institution = l_admin.getInstitution();

        //1.5.getBranch(arg0 : Institution, arg1 : 論理ビュー::java::lang::String)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_gentradeAccountManager.getBranch(
                l_institution,
                l_admin.getBranchCode());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.6. 部店が存在しない場合、例外をスローする
        if (l_branch == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当部店データなし");
        }
        //1.7. get債券銘柄(Institution, String)
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();

        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct = (WEB3BondProduct)l_bondProductManager.getBondProduct(
                l_institution,
                l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.8.validate管理者取扱可能銘柄(債券銘柄)
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();

        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);

        //1.9.to銘柄情報(債券銘柄)
        WEB3AdminBondHelperService l_bondHelperService =
            (WEB3AdminBondHelperService)Services.getService(WEB3AdminBondHelperService.class);

        WEB3AdminBondProductInfo l_bondProductInfo =
            l_bondHelperService.toProductInfo(l_bondProduct);

        //1.10.債券注文種別判定(注文種別, String)
        //債券注文種別オブジェクトを生成
        //[債券注文種別判定()の引数]
        //注文種別:OrderTypeEnum.債券買付
        //取引：国内仕切取引
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
            new WEB3BondOrderTypeJudge(
                OrderTypeEnum.BOND_BUY,
                WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING);

        //1.11. 注文情報( )
        WEB3AdminBondOrderInfo l_bondOrderInfo = new WEB3AdminBondOrderInfo();

        //1.12.get買付単価( )
        String l_strPrice = WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyPrice());

        //1.13.is外貨建( )
        boolean l_foreignCurrency = l_bondProduct.isForeignCurrency();

        //1.14is外貨建( )＝＝trueの場合
        BigDecimal l_bdGetFxRate = null;

        String l_strSettlementDiv = null;
        if (l_foreignCurrency)
        {
            l_strSettlementDiv = WEB3BondOrderSettleDivDef.FOREIGN_CURRENCY;
        }
        else
        {
            l_strSettlementDiv = WEB3BondOrderSettleDivDef.YEN_CURRENCY;
        }

        if (l_foreignCurrency)
        {

            //1.14.1get為替レート(債券銘柄, 債券注文種別判定, String, BigDecimal, boolean)
            //    債券銘柄：get債券銘柄
            //   　@債券注文種別判定：債券注文種別判定オブジェクト
            //   　@債券銘柄.is外貨建()==trueの場合
            //   　@　@決済区分：外貨
            //   　@入力為替レート：　@0
            //   　@is約定計算：　@true
            l_bdGetFxRate = l_bondOrderManager.getFxRate(
                l_bondProduct,
                l_bondOrderTypeJudge,
                l_strSettlementDiv,
                new BigDecimal("0"),
                true);
        }

        //1.15.プロパティセット
        //生成した注文情報オブジェクトに以下のデフォルトプロパティ値をセットする.
        //・注文種別：債券買付
        //・取引：国内仕切取引
        //・債券銘柄.is外貨建()==trueの場合
        //　@　@決済区分：外貨
        //　@債券銘柄.is外貨建()==falseの場合
        //　@　@決済区分：円貨
        //・注文数量：null
        //・単価：債券銘柄.get買付単価()
        //・債券銘柄.is外貨建()==trueの場合
        //・為替レート：get為替レート
        //・税区分：一般
        l_bondOrderInfo.tradingType = WEB3BondTradingTypeDef.BOND_BUY;
        l_bondOrderInfo.dealType = WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING;
        if (l_foreignCurrency)
        {
            l_bondOrderInfo.settleDiv = WEB3BondOrderSettleDivDef.FOREIGN_CURRENCY;

            l_bondOrderInfo.fxRate = l_bdGetFxRate.toString();
        }
        else
        {
            l_bondOrderInfo.settleDiv = WEB3BondOrderSettleDivDef.YEN_CURRENCY;
        }
        l_bondOrderInfo.orderQuantity = null;
        l_bondOrderInfo.price = l_strPrice;

        l_bondOrderInfo.taxType = TaxTypeEnum.NORMAL.intValue() + "";

        //1.16.get発注日( )
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //1.17.create債券約定日情報(java.util.Date, 債券注文種別判定, 債券銘柄)
        //債券約定日情報を取得
        //[引数]
        //発注日：get発注日
        //債券注文種別判定：債券注文種別判定オブジェクト
        //債券銘柄：get債券銘柄
        //債券銘柄.is外貨建()==trueの場合
        //　@決済区分：外貨
        //債券銘柄.is外貨建()==falseの場合
        //　@決済区分：円貨
        //部店：getBranch
        WEB3BondExecuteDateInfo l_bondExecutionDateInfo =
            (WEB3BondExecuteDateInfo)l_bondOrderManager.createBondExecutionDateInfo(
            l_datBizDate,
            l_bondOrderTypeJudge,
            l_bondProduct,
            l_strSettlementDiv,
            l_branch);

        //1.18約定情報( )
        WEB3AdminBondOrderExecInfo l_bondOrderExecInfo = new WEB3AdminBondOrderExecInfo();

        //1.19プロパティセット
        //約定情報オブジェクトに以下のプロパティをセットする.
        //約定日=create債券約定日情報.get約定日()
        //現地約定日=create債券約定日情報.get現地約定日()
        //受渡日=create債券約定日情報.get受渡日()
        //現地受渡日= create債券約定日情報.get現地受渡日()
        //入金日= create債券約定日情報.get入金日()
        l_bondOrderExecInfo.domesticExecutionDate = l_bondExecutionDateInfo.getExecuteDate();
        l_bondOrderExecInfo.foreignExecutionDate = l_bondExecutionDateInfo.getForeignExecuteDate();
        l_bondOrderExecInfo.domesticDeliveryDate = l_bondExecutionDateInfo.getDeliveryDate();
        l_bondOrderExecInfo.foreignDeliveryDate = l_bondExecutionDateInfo.getForeignDeliveryDate();
        l_bondOrderExecInfo.paymentDate = l_bondExecutionDateInfo.getPaymentDate();

        //1.20getカストディアン一覧(証券会社)
        WEB3BondDataManagerService l_bondDataManagerService =
            (WEB3BondDataManagerService)Services.getService(WEB3BondDataManagerService.class);
        List l_list = l_bondDataManagerService.getCustodianList(l_institution);

        //1.21.toカストディアン一覧(List)

        List l_lisCustodianList = l_bondHelperService.toCustodianList(l_list);

        //1.22.createレスポンス( )
        WEB3AdminBondExecInputResponse l_response =
            (WEB3AdminBondExecInputResponse)l_request.createResponse();

        //1.23.createレスポンス()にて取得したレスポンスデータに以下のプロパティをセットする。
        //)銘柄情報=to銘柄情報
        l_response.productInfo = l_bondProductInfo;
        //２）注文情報=生成した注文情報オブジェクト
        l_response.orderInfo = l_bondOrderInfo;
        //３）約定情報=生成した約定情報オブジェクト
        l_response.execInfo = l_bondOrderExecInfo;
        //４）カストディアン一覧=toカストディアン一覧の戻り値を配列にする
        WEB3AdminBondCustodianUnit[] l_bondCustodianUnits = null;
        if (l_lisCustodianList != null && !l_lisCustodianList.isEmpty())
        {
            l_bondCustodianUnits = new WEB3AdminBondCustodianUnit[l_lisCustodianList.size()];
            l_lisCustodianList.toArray(l_bondCustodianUnits);
        }
        l_response.custodianList = l_bondCustodianUnits;
        //５）入力時発注日：get発注日
        l_response.inpOrderDate = l_datBizDate;
        //６）注文種別一覧={債券買い注文, 債券売り注文}
        String[] l_strBondTradings = {WEB3BondTradingTypeDef.BOND_BUY,
            WEB3BondTradingTypeDef.BOND_SELL};
        l_response.tradingTypeList = l_strBondTradings;
        //７）取引一覧={募集取引, 国内仕切取引}
        String[] l_strDealTypes = {WEB3DealTypeDef.RECRUIT_TRADING,
            WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING};
        l_response.dealTypeList = l_strDealTypes;
        //８）決済区分一覧={円貨, 外貨}
        String[] l_strSettlementDivs = {WEB3BondOrderSettleDivDef.YEN_CURRENCY,
            WEB3BondOrderSettleDivDef.FOREIGN_CURRENCY};
        l_response.settleDivList = l_strSettlementDivs;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate管理者新規約定入力)<BR>
     * 管理者新規約定入力確認処理を行う。<BR>
     * <BR>
     * シーケンス図「validate管理者新規約定入力」を参照。
     * ==========================================================
     * 1.6. 部店が存在しない場合、例外をスローする
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01386<BR>
     * <BR>
     * ==========================================================
     * <BR>
     * @@param l_request - (管理者新規約定確認リクエスト)
     * @@return WEB3AdminBondExecConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B622570195
     */
    protected WEB3AdminBondExecConfirmResponse validateExecuteInput(
            WEB3AdminBondExecConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateExecuteInput(WEB3AdminBondExecConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1.validate( )
        l_request.validate();

        //1.2. getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3.証券会社( )
        Institution l_institution = l_admin.getInstitution();

        //1.4.validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_INPUT,
            true);

        //1.5.getBranch(arg0 : Institution, arg1 : 論理ビュー::java::lang::String)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_gentradeAccountManager.getBranch(
                l_institution,
                l_request.accountInfo.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.6. 部店が存在しない場合、例外をスローする
        if (l_branch == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当部店データなし");
        }

        //1.7.validate部店権限(部店コード : String)
        l_admin.validateBranchPermission(l_request.accountInfo.branchCode);
        //1.8. get補助口座(String, String, String)
        //補助口座オブジェクトを取得
        //[get補助口座()の引数]
        //証券会社コード：取得した証券会社オブジェクト.証券会社コード()
        //部店コード：リクエストデータ.顧客情報.部店コード
        //顧客コード：リクエストデータ.顧客情報.顧客コード
        WEB3AdminBondHelperService l_bondHelperService =
            (WEB3AdminBondHelperService)Services.getService(WEB3AdminBondHelperService.class);
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount)l_bondHelperService.getSubAccount(
            l_institution.getInstitutionCode(),
            l_request.accountInfo.branchCode,
            l_request.accountInfo.accountCode);

        //1.9.get債券銘柄(Institution, String)

        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();

        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct = (WEB3BondProduct)l_bondProductManager.getBondProduct(
                l_institution, l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.10.validate管理者取扱可能銘柄(債券銘柄)
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();

        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);

        //1.11.get発注日(確認時発注日 : Date)
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(
            l_request.inpOrderDate);

        //1.12.債券注文種別判定(注文種別, String)
        //注文種別：リクエストデータ.注文情報.注文種別
        //取引：リクエストデータ.注文情報.取引
        OrderTypeEnum l_orderTypeEnum = null;
        String l_strBondBuy = OrderTypeEnum.BOND_BUY.intValue() + "";
        String l_strBondSell = OrderTypeEnum.BOND_SELL.intValue() + "";
        
        if (l_strBondBuy.equals(l_request.orderInfo.tradingType))
        {
            l_orderTypeEnum = OrderTypeEnum.BOND_BUY;
        }
        else if (l_strBondSell.equals(l_request.orderInfo.tradingType))
        {
            l_orderTypeEnum = OrderTypeEnum.BOND_SELL;
        }

        WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge(
            l_orderTypeEnum,
            l_request.orderInfo.dealType);

        //1.13create債券約定日情報(java.util.Date, 債券注文種別判定, 債券銘柄)
        //債券約定日情報を生成する
        //[引数]
        //発注日：get発注日
        //債券注文種別判定：生成した債券注文種別判定
        //債券銘柄：get債券銘柄
        //決済区分：リクエスト.注文情報.決済区分
        //部店：getBranch
        WEB3BondExecuteDateInfo l_bondExecutionDateInfo = 
            (WEB3BondExecuteDateInfo)l_bondOrderManager.createBondExecutionDateInfo(
            l_datBizDate,
            l_bondOrderTypeJudge,
            l_bondProduct,
            l_request.orderInfo.settleDiv,
            l_branch);

        // 1.14.reset約定日情報(約定情報, 債券約定日情報, 債券注文種別判定, 債券銘柄, 決済区分, 部店)
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo =
            l_bondHelperService.resetExecuteDateInfo(
                l_request.execInfo,
                l_bondExecutionDateInfo,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_request.orderInfo.settleDiv,
                l_branch);

        //1.15.validate約定日(債券銘柄)
        l_bondExecutionDateInfo.validateExecuteDate(l_bondProduct);

        //1.16.validate数量(double, 債券銘柄)

        l_bondOrderManager.validateQuantity(
            Double.parseDouble(l_request.orderInfo.orderQuantity),
            l_bondProduct);

        //1.17validate単価(債券銘柄, String)
        //債券銘柄：get債券銘柄
        //単価：リクエスト.注文情報.単価
        l_bondOrderManager.validateExecPrice(l_bondProduct, l_request.orderInfo.price);

        //1.18validate為替レート(債券銘柄, String)
        //[引数]
        // 債券銘柄：get債券銘柄
        // 為替レート：リクエスト.注文情報.為替レート
        l_bondOrderManager.validateFxRate(l_bondProduct, l_request.orderInfo.fxRate);

        //1.19is外貨建( )
        boolean l_foreignCurrency = l_bondProduct.isForeignCurrency();

        //1.20is外貨建( )＝＝trueの場合
        BigDecimal l_bdGetFxRate = null;
        if (l_foreignCurrency)
        {
            //1.20.1get為替レート(債券銘柄, 債券注文種別判定, String, BigDecimal, boolean)
            //   　@債券銘柄：get債券銘柄
            //   　@債券注文種別判定：債券注文種別判定オブジェクト
            //   　@決済区分：リクエスト.注文情報.決済区分
            //   　@入力為替レート：　@リクエスト.注文情報.為替レート
            //   　@is約定計算：　@true
            l_bdGetFxRate = l_bondOrderManager.getFxRate(
                l_bondProduct,
                l_bondOrderTypeJudge,
                l_request.orderInfo.settleDiv,
                new BigDecimal(l_request.orderInfo.fxRate),
                true);
        }

        // 1.21.calc受渡代金(債券注文種別判定, BigDecimal, BigDecimal, BigDecimal, 債券銘柄, 債券約定日情報)
        //債券受渡代金計算結果オブジェクトを生成する
        //[引数]
        //債券注文種別判定：生成した債券注文種別判定
        //数量：リクエスト.注文情報.注文数量
        //注文単価：リクエスト.注文情報.注文単価
        //為替レート：get為替レート（※is外貨建()の戻り値 == falseの場合、nullをセットする。）
        //債券銘柄：get債券銘柄
        //債券約定日情報：生成した約定日情報
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondBizLogicProvider l_bondBizLogicProvider =
            (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();

        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            l_bondBizLogicProvider.calcEstimatedPrice(
                l_bondOrderTypeJudge,
                new BigDecimal(l_request.orderInfo.orderQuantity),
                new BigDecimal(l_request.orderInfo.price),
                l_bdGetFxRate,
                l_bondProduct,
                l_bondExecuteDateInfo);

        //1.22.reset受渡代金(債券受渡代金計算結果, 約定情報, 債券銘柄)
        //[reset受渡代金()]
        // 債券受渡代金計算結果：calc受渡代金の戻り値
        // 約定情報：リクエスト.約定情報
        // 債券銘柄：get債券銘柄
        WEB3BondEstimatedPriceCalcResult l_calcResult =
            l_bondHelperService.resetEstimatedPrice(
                l_bondEstimatedPriceCalcResult,
                l_request.execInfo,
                l_bondProduct);

        //1.23is海外市場営業日(債券銘柄, Date)
        //   　@債券銘柄：get債券銘柄
        //   　@国内発注日：get発注日
        WEB3BondTradingTimeManagement l_bondTradingTimeManagement =
            new WEB3BondTradingTimeManagement();

        boolean l_soreignMarketBizDate =
            l_bondTradingTimeManagement.isForeignMarketBizDate(l_bondProduct, l_datBizDate);

        //1.24 is海外市場営業日＝＝falseの場合
        if (!l_soreignMarketBizDate)
        {
            //1.24.1add警告区分(String)
            l_calcResult.addWarningDiv(WEB3BondWarningDivDef.FRGN_ORDERBIZDATE_NOBIZDATE);
        }

        //1.25.create拡張債券新規注文内容(Trader, 債券注文種別判定, String, double, double, TaxTypeEnum, java.util.Date, String)
        //拡張債券新規注文内容オブジェクトを生成
        //[引数]
        //オペレータ：null
        //債券注文種別判定：生成した債券注文種別判定オブジェクト
        //銘柄コード（WEB3）：リクエスト.銘柄コード(WEB3)
        //数量：リクエスト.注文情報.注文数量
        //単価：リクエスト.注文情報.単価
        //税区分：リクエスト.注文情報.税区分
        //　@リクエスト.注文情報.税区分＝＝nullの場合、　@TaxTypeEnum.NORMAL(一般口座)とする
        //受渡日：create債券約定日情報.受渡日
        //決済区分：リクエスト.注文情報.決済区分
        String l_strNormal = TaxTypeEnum.NORMAL.intValue() + "";
        String l_strUndefined = TaxTypeEnum.UNDEFINED.intValue() + "";
        String l_strSpecial = TaxTypeEnum.SPECIAL.intValue() + "";
        String l_strSpecialWithhold = TaxTypeEnum.SPECIAL_WITHHOLD.intValue() + "";

        TaxTypeEnum l_taxTypeEnum = null;
        if ((l_request.orderInfo.taxType) == null ||
            l_strNormal.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;

        }
        else if (l_strUndefined.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.UNDEFINED;
        }
        else if (l_strSpecial.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        else if (l_strSpecialWithhold.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.SPECIAL_WITHHOLD;
        }

        WEB3BondNewOrderSpec l_bondNewOrderSpec = WEB3BondNewOrderSpec.createBondNewOrderSpec(
            null,
            l_bondOrderTypeJudge,
            l_request.productCode,
            Double.parseDouble(l_request.orderInfo.orderQuantity),
            Double.parseDouble(l_request.orderInfo.price),
            l_taxTypeEnum,
            l_bondExecutionDateInfo.getDeliveryDate(),
            l_request.orderInfo.settleDiv);

        //1.26. validate債券新規注文(SubAccount, BondNewOrderSpec, 債券約定日情報, 債券受渡代金計算結果)
        //債券新規注文をチェックする
        //[引数]
        //補助口座：get補助口座
        //拡張債券新規注文内容：create拡張債券新規注文内容
        //債券約定日情報：債券約定日情報オブジェクト
        //債券受渡代金計算結果：生成した債券受渡代金計算結果オブジェクト
        l_bondOrderManager.validateBondNewOrder(
            l_subAccount,
            l_bondNewOrderSpec,
            l_bondExecuteDateInfo);

        //1.27債券管理者新規注文更新インタセプタ( )
        WEB3AdminBondNewOrderUpdateInterceptor l_bondNewOrderUpdateInterceptor =
            new WEB3AdminBondNewOrderUpdateInterceptor();

        //1.28プロパティセット
        //生成したインタセプタに以下のプロパティをセットする.
        //債券約定日情報：reset約定日情報
        //債券受渡代金計算結果：reset受渡代金
        //カストディアンコード：リクエストデータ.約定情報.カストディアン.カストディアンコード()
        //債券銘柄：get債券銘柄
        //管理者：getInstanceFromログイン情報
        //債券新規注文内容：create債券新規注文
        l_bondNewOrderUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);
        l_bondNewOrderUpdateInterceptor.setBondEstimatedPriceCalcResult(l_calcResult);
        if (l_request.execInfo.custodianInfo != null)
        {
            l_bondNewOrderUpdateInterceptor.setCustodianCode(
                l_request.execInfo.custodianInfo.custodianCode);
        }
        l_bondNewOrderUpdateInterceptor.setBondProduct(l_bondProduct);
        l_bondNewOrderUpdateInterceptor.setAdministrator(l_admin);
        l_bondNewOrderUpdateInterceptor.setBondNewOrderSpec(l_bondNewOrderSpec);

        //1.29.債券管理者新規約定更新インタセプタ( )
        WEB3AdminBondNewExecuteUpdateInterceptor l_executeUpdateInterceptor =
            new WEB3AdminBondNewExecuteUpdateInterceptor();

        //1.30.プロパティセット
        //生成したインタセプタに以下のプロパティをセットする.
        //債券受渡代金計算結果：reset受渡代金
        //債券銘柄：get債券銘柄
        l_executeUpdateInterceptor.setBondEstimatedPriceCalcResult(l_calcResult);
        l_executeUpdateInterceptor.setBondProduct(l_bondProduct);

        //1.31.債券注文種別判定.is売却注文＝＝false　@かつ　@決済区分＝＝円貨の場合
        if ((!l_bondOrderTypeJudge.isSellOrder()) &&
            (WEB3BondOrderSettleDivDef.YEN_CURRENCY.equals(l_request.orderInfo.settleDiv)))
        {
            //1.31.1.債券管理者新規約定入力インタセプタ( )
            WEB3AdminBondOrderAndExecuteCommonInterceptor l_commonInterceptor = new WEB3AdminBondOrderAndExecuteCommonInterceptor();
            
            //1.31.2.生成したインタセプタに以下のプロパティをセットする。 
            //債券管理者新規注文更新インタセプタ：債券管理者新規注文更新インタセプタ
            //債券管理者新規約定更新インタセプタ：債券管理者新規約定更新インタセプタ
            l_commonInterceptor.setBondNewOrderUpdateInterceptor(l_bondNewOrderUpdateInterceptor);
            l_commonInterceptor.setBondNewExecuteUpdateInterceptor(l_executeUpdateInterceptor);

            //1.31.3.validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 注文内容 : Object[],
            //注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
            //余力チェック
            //[validate取引余力()の引数]
            //補助口座：get補助口座
            //注文内容インタセプタ：債券管理者新規注文インタセプタ
            //注文内容：拡張債券新規注文内容
            //注文種別：OrderTypeEnum.債券買注文
            //余力更新フラグ：false
            WEB3TPTradingPowerService l_service =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            Object[] l_objNewOrderUpdateInterceptor = {l_commonInterceptor};
            Object[] l_objNewOrderSpec = {l_bondNewOrderSpec};
    
            WEB3TPTradingPowerResult l_tPTradingPowerResult =
                l_service.validateTradingPower(
                l_subAccount,
                l_objNewOrderUpdateInterceptor,
                l_objNewOrderSpec,
                OrderTypeEnum.BOND_BUY,
                false);

            //1.31.4is判定フラグ( )
            //1.31.5.メッセージ is判定フラグ＝＝falseの場合
            if (!l_tPTradingPowerResult.isResultFlg())
            {
                //1.31.5.1.add警告区分(String)
                //警告区分を追加
                //[引数]
                //警告区分：余力チェックNG
                l_calcResult.addWarningDiv(WEB3BondWarningDivDef.TRADE_POWER_CHECK_NG);
            }

        }

        //1.32.to約定情報(債券約定日情報, 債券受渡代金計算結果, カストディアン, 拡張債券注文単位)
        //引数：
        //債券約定日情報=create債券約定日情報()の戻り値
        //（reset約定日情報()で再設定した後の債権約定日情報）
        //債券受渡代金計算結果=calc受渡代金()の戻り値
        //（reset受渡代金()で再設定した後の債券受渡代金計算結果）
        //カストディアン=リクエスト.約定情報.カストディアン
        //拡張債券注文単位＝null
        WEB3AdminBondOrderExecInfo l_bondOrderExecInfo = l_bondHelperService.toOrderExecInfo(
            l_bondExecuteDateInfo,
            l_calcResult,
            l_request.execInfo.custodianInfo,
            null);

        //1.33. to顧客情報(顧客)
        //顧客：get補助口座.get顧客()
        WEB3AdminBondAccountInfo l_bondAccountInfo =
            l_bondHelperService.toAccountInfo(l_subAccount.getMainAccount());

        //1.34.createNewOrderId( )
        long l_lngCreateNewOrderId = l_bondOrderManager.createNewOrderId();

        //1.35.createレスポンス( )
        WEB3AdminBondExecConfirmResponse l_response =
            (WEB3AdminBondExecConfirmResponse)l_request.createResponse();

        //1.36.プロパティセット
        //プロパティをセットする。
        //注文ID＝createNewOrderId
        //入力時発注日＝get発注日
        //約定情報=to約定情報
        //顧客情報=to顧客情報
        l_response.id = l_lngCreateNewOrderId + "";
        l_response.inpOrderDate = l_datBizDate;
        l_response.execInfo = l_bondOrderExecInfo;
        l_response.accountInfo = l_bondAccountInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit管理者新規約定入力)<BR>
     * 新規約定入力完了処理を行う。<BR>
     * <BR>
     * シーケンス図「submit管理者新規約定入力」を参照。
     * ==========================================================
     * 1.7. 部店が存在しない場合、例外をスローする
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01386<BR>
     * <BR>
     * ==========================================================
     * <BR>
     * @@param l_request - (管理者新規約定完了リクエスト)
     * @@return WEB3AdminBondExecCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B6229D0222
     */
    protected WEB3AdminBondExecCompleteResponse submitExecuteInput(
        WEB3AdminBondExecCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitExecuteInput(WEB3AdminBondExecCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        //1.1. validate( )
        l_request.validate();

        //1.2.getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3.get証券会社( )
        Institution l_institution = l_admin.getInstitution();

        //1.4.validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_INPUT, true);

        //1.5. validate取引パスワード(パスワード : String)
        //取引パスワードが正しいかチェックする
        //[validate取引パスワード()の引数]
        //パスワード：リクエストデータ.暗証番号
        l_admin.validateTradingPassword(l_request.password);

        //1.6.getBranch(arg0 : Institution, arg1 : 論理ビュー::java::lang::String)
        //部店オブジェクトを取得
        //[get部店()の引数]
        //証券会社コード：get証券会社
        //部店コード：リクエスト.顧客情報.get部店コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_gentradeAccountManager.getBranch(
                l_institution,
                l_request.accountInfo.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.7.部店が存在しない場合、例外をスローする
        if (l_branch == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当部店データなし");
        }

        //1.8.validate部店権限(部店コード : String)
        //当該管理者が、指定の部店を取り扱えるかをチェックする。
        //[validate部店権限()の引数]
        //部店コード：リクエストデータ.顧客情報.部店コード
        l_admin.validateBranchPermission(
            l_request.accountInfo.branchCode);

        //1.9.get補助口座(String, String, String)
        //顧客の補助口座を取得
        //[get補助口座()の引数]
        //証券会社コード:証券会社コード：取得した証券会社オブジェクト.証券会社コード()
        //部店コード:リクエスト.顧客情報.部店コード
        //顧客コード:リクエスト.顧客情報.顧客コード
        WEB3AdminBondHelperService l_bondHelperService =
            (WEB3AdminBondHelperService)Services.getService(
                WEB3AdminBondHelperService.class);

        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount)l_bondHelperService.getSubAccount(
                l_institution.getInstitutionCode(),
                l_request.accountInfo.branchCode,
                l_request.accountInfo.accountCode);

        //1.10.get債券銘柄(Institution, String)
        //債券銘柄オブジェクトを取得
        //[get債券銘柄()の引数]
        //証券会社：get証券会社
        //銘柄コード(WEB3)：リクエストデータ.銘柄コード(WEB3)
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();

        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct =
                (WEB3BondProduct)l_bondProductManager.getBondProduct(
                    l_institution,
                    l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //1.11.validate管理者取扱可能銘柄(債券銘柄)
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();

        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);

        //1.12.get発注日(確認時発注日 : Date)
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(
            l_request.inpOrderDate);

        //1.13 債券注文種別判定(注文種別, String)
        //債券注文種別判定オブジェクトを生成
        //[債券注文種別判定()の引数]
        //注文種別：リクエストデータ.注文情報.注文種別
        //取引：リクエストデータ.注文情報.取引
        OrderTypeEnum l_orderTypeEnum = null;
        String l_strBondBuy = OrderTypeEnum.BOND_BUY.intValue() + "";
        String l_strBondSell = OrderTypeEnum.BOND_SELL.intValue() + "";
        
        if (l_strBondBuy.equals(l_request.orderInfo.tradingType))
        {
            l_orderTypeEnum = OrderTypeEnum.BOND_BUY;
        }
        else if (l_strBondSell.equals(l_request.orderInfo.tradingType))
        {
            l_orderTypeEnum = OrderTypeEnum.BOND_SELL;
        }

        WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
            new WEB3BondOrderTypeJudge(
                l_orderTypeEnum,
                l_request.orderInfo.dealType);

        //1.14.create債券約定日情報(java.util.Date, 債券注文種別判定, 債券銘柄)
        //債券約定日情報を生成する
        //[引数]
        //発注日：get発注日
        //債券注文種別判定：生成した債券注文種別判定
        //債券銘柄：get債券銘柄
        //決済区分：リクエスト.注文情報.決済区分
        //部店：getBranch
        WEB3BondExecuteDateInfo l_bondExecutionDateInfo =
            (WEB3BondExecuteDateInfo)l_bondOrderManager.createBondExecutionDateInfo(
                l_datBizDate,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_request.orderInfo.settleDiv,
                l_branch);

        // 1.15.reset約定日情報(約定情報, 債券約定日情報, 債券注文種別判定, 債券銘柄, 決済区分, 部店)
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo =
            l_bondHelperService.resetExecuteDateInfo(
                l_request.execInfo,
                l_bondExecutionDateInfo,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_request.orderInfo.settleDiv,
                l_branch);

        //1.16.validate約定日(債券銘柄)
        l_bondExecutionDateInfo.validateExecuteDate(l_bondProduct);

        //1.17.validate数量(double, 債券銘柄)
        l_bondOrderManager.validateQuantity(Double.parseDouble(
            l_request.orderInfo.orderQuantity),
            l_bondProduct);

        //1.18validate単価(債券銘柄, String)
        //債券銘柄：get債券銘柄
        //単価：リクエスト.注文情報.単価
        l_bondOrderManager.validateExecPrice(l_bondProduct, l_request.orderInfo.price);

        //1.19validate為替レート(債券銘柄, BigDecimal)
        //[引数]
        // 債券銘柄：get債券銘柄
        // 為替レート：リクエスト.注文情報.為替レート
        l_bondOrderManager.validateFxRate(l_bondProduct, l_request.orderInfo.fxRate);

        //1.20is外貨建( )
        boolean l_foreignCurrency = l_bondProduct.isForeignCurrency();

        //1.21is外貨建( )＝＝trueの場合
        BigDecimal l_bdGetFxRate = null;
        if (l_foreignCurrency)
        {
            //1.21.1get為替レート(債券銘柄, 債券注文種別判定, String, BigDecimal, boolean)
            //   　@債券銘柄：get債券銘柄
            //   　@債券注文種別判定：債券注文種別判定オブジェクト
            //   　@決済区分：リクエスト.注文情報.決済区分
            //   　@入力為替レート：　@リクエスト.注文情報.約定為替レート
            //   　@is約定計算：　@true
            l_bdGetFxRate = l_bondOrderManager.getFxRate(
                l_bondProduct,
                l_bondOrderTypeJudge,
                l_request.orderInfo.settleDiv,
                new BigDecimal(l_request.orderInfo.fxRate),
                true);
        }

        //1.22.calc受渡代金(債券注文種別判定, BigDecimal,
        //BigDecimal, BigDecimal, 債券銘柄, 債券約定日情報)
        //債券受渡代金計算結果オブジェクトを生成する
        //[引数]
        //債券注文種別判定：生成した債券注文種別判定
        //数量：リクエスト.注文情報.注文数量
        //注文単価：リクエスト.注文情報.注文単価
        //為替レート：get為替レート（※is外貨建()の戻り値 == falseの場合、nullをセットする。）
        //債券銘柄：get債券銘柄
        //債券約定日情報：生成した約定日情報
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondBizLogicProvider l_bondBizLogicProvider =
            (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();

        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            l_bondBizLogicProvider.calcEstimatedPrice(
                l_bondOrderTypeJudge,
                new BigDecimal(l_request.orderInfo.orderQuantity),
                new BigDecimal(l_request.orderInfo.price),
                l_bdGetFxRate,
                l_bondProduct,
                l_bondExecuteDateInfo);


        //1.23.reset受渡代金(債券受渡代金計算結果, 約定情報, 債券銘柄)
        //[reset受渡代金()]
        // 債券受渡代金計算結果：calc受渡代金
        // 約定情報：リクエスト.約定情報
        // 債券銘柄：get債券銘柄
        WEB3BondEstimatedPriceCalcResult l_calcResult =
            l_bondHelperService.resetEstimatedPrice(
                l_bondEstimatedPriceCalcResult,
                l_request.execInfo,
                l_bondProduct);

        //1.24is海外市場営業日(債券銘柄, Date)
        //   　@債券銘柄：get債券銘柄
        //   　@国内発注日：get発注日
        WEB3BondTradingTimeManagement l_bondTradingTimeManagement =
            new WEB3BondTradingTimeManagement();

        boolean l_soreignMarketBizDate =
            l_bondTradingTimeManagement.isForeignMarketBizDate(l_bondProduct, l_datBizDate);

        //1.25 is海外市場営業日＝＝falseの場合
        if (!l_soreignMarketBizDate)
        {
            //1.25.1add警告区分(String)
            l_calcResult.addWarningDiv(WEB3BondWarningDivDef.FRGN_ORDERBIZDATE_NOBIZDATE);
        }

        //1.26.create拡張債券新規注文内容(Trader, 債券注文種別判定,
        //String, double, double, TaxTypeEnum, java.util.Date, String)
        //拡張債券新規注文内容オブジェクトを生成
        //[引数]
        //オペレータ：null
        //債券注文種別判定：生成した債券注文種別判定オブジェクト
        //銘柄コード（WEB3）：リクエスト.銘柄コード(WEB3)
        //数量：リクエスト.注文情報.注文数量
        //単価：リクエスト.注文情報.単価
        //税区分：リクエスト.注文情報.税区分
        //　@リクエスト.注文情報.税区分＝＝nullの場合、　@TaxTypeEnum.NORMAL(一般口座)とする
        //受渡日：create債券約定日情報.受渡日
        //決済区分：リクエスト.注文情報.決済区分
        String l_strNormal = TaxTypeEnum.NORMAL.intValue() + "";
        String l_strUndefined = TaxTypeEnum.UNDEFINED.intValue() + "";
        String l_strSpecial = TaxTypeEnum.SPECIAL.intValue() + "";
        String l_strSpecialWithhold = TaxTypeEnum.SPECIAL_WITHHOLD.intValue() + "";

        TaxTypeEnum l_taxTypeEnum = null;
        if ((l_request.orderInfo.taxType) == null ||
            l_strNormal.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;

        }
        else if (l_strUndefined.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.UNDEFINED;
        }
        else if (l_strSpecial.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        else if (l_strSpecialWithhold.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.SPECIAL_WITHHOLD;
        }

        WEB3BondNewOrderSpec l_bondNewOrderSpec =
            WEB3BondNewOrderSpec.createBondNewOrderSpec(
            null,
            l_bondOrderTypeJudge,
            l_request.productCode,
            Double.parseDouble(l_request.orderInfo.orderQuantity),
            Double.parseDouble(l_request.orderInfo.price),
            l_taxTypeEnum,
            l_bondExecutionDateInfo.getDeliveryDate(),
            l_request.orderInfo.settleDiv);

        //1.27.validate債券新規注文(SubAccount, BondNewOrderSpec,
        //債券約定日情報, 債券受渡代金計算結果)
        //債券新規注文をチェックする
        //[引数]
        //補助口座：get補助口座
        //拡張債券新規注文内容：create拡張債券新規注文内容
        //債券約定日情報：reset債券約定日情報
        //債券受渡代金計算結果：reset受渡代金
        l_bondOrderManager.validateBondNewOrder(
            l_subAccount,
            l_bondNewOrderSpec,
            l_bondExecuteDateInfo);

        //1.28.債券管理者新規注文更新インタセプタ( )
        WEB3AdminBondNewOrderUpdateInterceptor l_bondNewOrderUpdateInterceptor =
            new WEB3AdminBondNewOrderUpdateInterceptor();

        //1.29.プロパティセット
        //生成したインタセプタに以下のプロパティをセットする。
        //債券約定日情報：reset約定日情報
        //債券受渡代金計算結果：reset受渡代金
        //カストディアンコード：リクエストデータ.約定情報.カストディアン.カストディアンコード()
        //債券銘柄：get債券銘柄
        //管理者：getInstanceFromログイン情報
        //債券新規注文内容：create債券新規注文
        l_bondNewOrderUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);
        l_bondNewOrderUpdateInterceptor.setBondEstimatedPriceCalcResult(l_calcResult);
        if (l_request.execInfo.custodianInfo != null)
        {
            l_bondNewOrderUpdateInterceptor.setCustodianCode(
                l_request.execInfo.custodianInfo.custodianCode);
        }
        l_bondNewOrderUpdateInterceptor.setBondProduct(l_bondProduct);
        l_bondNewOrderUpdateInterceptor.setAdministrator(l_admin);
        l_bondNewOrderUpdateInterceptor.setBondNewOrderSpec(l_bondNewOrderSpec);

        //1.30.債券管理者新規約定更新インタセプタ( )
        WEB3AdminBondNewExecuteUpdateInterceptor l_executeUpdateInterceptor =
            new WEB3AdminBondNewExecuteUpdateInterceptor();

        //1.31.プロパティセット
        //生成したインタセプタに以下のプロパティをセットする.
        //債券受渡代金計算結果：reset受渡代金
        //債券銘柄：get債券銘柄
        l_executeUpdateInterceptor.setBondEstimatedPriceCalcResult(l_calcResult);
        l_executeUpdateInterceptor.setBondProduct(l_bondProduct);

        //1.32.債券注文種別判定.is売却注文＝＝false　@かつ　@決済区分＝＝円貨の場合
        WEB3TPTradingPowerResult l_tPTradingPowerResult = null;
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        if ((!l_bondOrderTypeJudge.isSellOrder())  &&
            (WEB3BondOrderSettleDivDef.YEN_CURRENCY.equals(l_request.orderInfo.settleDiv)))
        {
            //債券管理者新規約定入力インタセプタ( )
            WEB3AdminBondOrderAndExecuteCommonInterceptor l_commonInterceptor = new WEB3AdminBondOrderAndExecuteCommonInterceptor();
            
            //生成したインタセプタに以下のプロパティをセットする。 
            //債券管理者新規注文更新インタセプタ：債券管理者新規注文更新インタセプタ
            //債券管理者新規約定更新インタセプタ：債券管理者新規約定更新インタセプタ
            l_commonInterceptor.setBondNewOrderUpdateInterceptor(l_bondNewOrderUpdateInterceptor);
            l_commonInterceptor.setBondNewExecuteUpdateInterceptor(l_executeUpdateInterceptor);
            
            //validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[],
            //注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
            //余力チェック
            //[validate取引余力()の引数]
            //補助口座：get補助口座
            //注文内容インタセプタ：債券管理者新規約定入力インタセプタ
            //注文内容：拡張債券新規注文内容
            //注文種別：OrderTypeEnum.債券買注文
            //余力更新フラグ：true
            Object[] l_objNewOrderUpdateInterceptor = {l_commonInterceptor};
            Object[] l_objNewOrderSpec = {l_bondNewOrderSpec};

            l_tPTradingPowerResult =
                l_service.validateTradingPower(
                l_subAccount,
                l_objNewOrderUpdateInterceptor,
                l_objNewOrderSpec,
                OrderTypeEnum.BOND_BUY,
                true);
            //is判定フラグ( )
            //is判定フラグ＝＝falseの場合
            if (!l_tPTradingPowerResult.isResultFlg())
            {
                //1.30.3.1.add警告区分(String)
                //警告区分を追加
                //[引数]
                //警告区分：余力チェックNG
                l_calcResult.addWarningDiv(
                    WEB3BondWarningDivDef.TRADE_POWER_CHECK_NG);
            }
        }
        //1.33.setThreadLocalPersistenceEventInterceptor(arg0 :
        //BondOrderManagerPersistenceEventInterceptor)
        l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_bondNewOrderUpdateInterceptor);

        //1.34.submitNewOrder(arg0 : SubAccount, arg1 : ProductTypeEnum,
        //arg2 : NewOrderSpec, arg3 : long, a
        //rg4 : 論理ビュー::java::lang::String, arg5 : boolean)
        //[submitNewOrderの引数]
        //補助口座：　@取得した補助口座オブジェクト
        //銘柄タイプ： ProductTypeEnum.債券
        //新規注文内容： 生成した拡張債券新規注文内容
        //注文ID： リクエストデータ.注文ID
        //取引パスワード：
        //取引パスワード設定 == ’取引パスワード使用’の場合
        //取得した補助口座.get顧客().getTradingPassword（）の戻り値を
        //WEB3Crypt().decrypt()で復号したもの
        //取引パスワード設定 == ’DEFAULT’の場合、リクエストデータ.暗証番号
        //is発注審査省略： true
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        OpLoginAdminService l_adminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
        LoginInfo l_loginInfo = l_securityService.getLoginInfo();
        Map l_mapAttributes = l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
        String l_strAttribute = (String)l_mapAttributes.get(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);

        //取引パスワード設定 == ”DEFAULT”
        String l_strTradingPassword = null;
        if (WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
        {
            l_strTradingPassword = l_request.password;
        }
        //取引パスワード設定 == ”取引パスワード使用” の場合
        else if (WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
        {
            WEB3Crypt l_web3Crypt = new WEB3Crypt();
            l_strTradingPassword = l_web3Crypt.decrypt(
                l_subAccount.getMainAccount().getTradingPassword());
        }
        l_bondOrderManager.submitNewOrder(
            l_subAccount,
            ProductTypeEnum.BOND,
            l_bondNewOrderSpec,
            Long.parseLong(l_request.id),
            l_strTradingPassword,
            true);

        //1.35.債券管理者注文受付更新インタセプタ( )
        WEB3AdminBondOrderAcceptUpdateInterceptor l_acceptUpdateInterceptor =
            new WEB3AdminBondOrderAcceptUpdateInterceptor();

        //1.36.accept新規注文(long, 債券管理者デフォルトインタセプタ)(
        //新規注文受付をする
        //[引数]
        //注文ID： リクエストデータ.注文ID
        //債券管理者デフォルトインタセプタ： 債券管理者注文受付更新インタセプタ
        WEB3AdminBondExecuteNotifyService l_bondExecuteNotifyService =
            (WEB3AdminBondExecuteNotifyService)Services.getService(
                WEB3AdminBondExecuteNotifyService.class);
        l_bondExecuteNotifyService.acceptNewOrder(
            Long.parseLong(l_request.id),
            l_acceptUpdateInterceptor);

        //1.37.get債券注文単位By注文ID(long)
        //[引数]
        //注文ID： リクエストデータ.注文ID
        WEB3BondOrderUnit l_bondOrderUnit =
            l_bondOrderManager.getBondOrderUnitByOrderId(
                Long.parseLong(l_request.id));

        //1.38.notify約定(BondOrderUnit, 債券管理者デフォルトインタセプタ)
        //約定処理をする
        //[引数]
        //債券注文単位： get債券注文単位By注文ID
        //債券管理者デフォルトインタセプタ： 債券管理者新規約定更新インタセプタ
        l_bondExecuteNotifyService.notifyExecute(
            l_bondOrderUnit,
            l_executeUpdateInterceptor);

        //1.39.（is判定フラグ！＝null かつ　@is判定フラグ＝＝false） 又は　@（債券注文種別判定.is売却注文＝＝true　@かつ　@決済区分＝＝円貨）の場合
        if((l_tPTradingPowerResult !=null && !l_tPTradingPowerResult.isResultFlg())
            ||
            (l_bondOrderTypeJudge.isSellOrder()  &&
            WEB3BondOrderSettleDivDef.YEN_CURRENCY.equals(l_request.orderInfo.settleDiv)))
        {
            //1.39.1 余力再計算(補助口座 : 補助口座)
            l_service.reCalcTradingPower(l_subAccount);  
        }

        //1.40.createレスポンス( )
        WEB3AdminBondExecCompleteResponse l_response =
            (WEB3AdminBondExecCompleteResponse)l_request.createResponse();

        //1.41.プロパティセット
        //プロパティをセットする。
        //更新時間：現在日時
        //識別番号：リクエストデータ.注文ID
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        l_response.orderActionId = l_request.id;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (calc受渡代金)<BR>
     * 受渡代金計算処理を実行する。<BR>
     * <BR>
     * シーケンス「calc受渡代金」を参照。
     * @@param l_request - (管理者新規約定計算リクエスト)
     * @@return WEB3AdminBondExecCalculateResponse
     * @@throws WEB3BaseException
     * @@roseuid 44BB49210197
     */
    protected WEB3AdminBondExecCalculateResponse calcEstimatedPrice(
            WEB3AdminBondExecCalculateRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcEstimatedPrice(WEB3AdminBondExecCalculateRequest)";
        log.entering(STR_METHOD_NAME);
        //1.1. validate( )
        l_request.validate();

        //1.2.getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3.validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_INPUT, true);

        //1.4.validate部店権限(部店コード : String)
        String l_strBranchCode = null;
        if (l_request.accountInfo.branchCode != null)
        {
            l_strBranchCode = l_request.accountInfo.branchCode;
        }
        else
        {
            l_strBranchCode = l_admin.getBranchCode();
        }
        l_admin.validateBranchPermission(l_strBranchCode);

        //1.5.get証券会社( )
        Institution l_institution = l_admin.getInstitution();

        //1.6.getBranch(arg0 : Institution, arg1 : 論理ビュー::java::lang::String)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_gentradeAccountManager.getBranch(
                l_institution,
                l_strBranchCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.7. 部店が存在しない場合、例外をスローする
        if (l_branch == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当部店データなし");
        }

        WEB3AdminBondHelperService l_bondHelperService =
            (WEB3AdminBondHelperService)Services.getService(WEB3AdminBondHelperService.class);
        //1.8.get債券銘柄(Institution, String)
        //注文債券銘柄オブジェクトを取得する
        //[get債券銘柄()の引数]
        //証券会社：取得した証券会社オブジェクト
        //銘柄コード：リクエストデータ.銘柄コード(WEB3)
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();

        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct = (WEB3BondProduct)l_bondProductManager.getBondProduct(
                l_institution, l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.9.validate管理者取扱可能銘柄(債券銘柄)
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();

        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);

        //1.10.get発注日(確認時発注日 : Date)
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(
            l_request.inpOrderDate);

        //1.11.債券注文種別判定(注文種別, String)
        //債券注文種別判定オブジェクトを生成
        //[引数]
        //注文種別：リクエスト.注文情報.注文種別をOrderTypeEnumに変換
        //取引：リクエスト.注文情報.取引
        WEB3AdminBondOrderInfo l_bondOrderInfo = l_request.orderInfo;

        OrderTypeEnum l_orderTypeEnum = null;
        String l_strBondBuy = OrderTypeEnum.BOND_BUY.intValue() + "";
        String l_strBondSell = OrderTypeEnum.BOND_SELL.intValue() + "";
        
        if (l_strBondBuy.equals(l_request.orderInfo.tradingType))
        {
            l_orderTypeEnum = OrderTypeEnum.BOND_BUY;
        }
        else if (l_strBondSell.equals(l_request.orderInfo.tradingType))
        {
            l_orderTypeEnum = OrderTypeEnum.BOND_SELL;
        }
        
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge(
            l_orderTypeEnum,
            l_bondOrderInfo.dealType);

        //1.12.create債券約定日情報(java.util.Date, 債券注文種別判定, 債券銘柄)
        //債券約定日情報を生成する
        //[引数]
        //発注日：get発注日
        //債券注文種別判定：生成した債券注文種別判定
        //債券銘柄：get債券銘柄
        //決済区分：リクエスト.注文情報.決済区分
        //部店：getBranch
        WEB3BondExecuteDateInfo l_bondExecutionDateInfo = (
            WEB3BondExecuteDateInfo)l_bondOrderManager.createBondExecutionDateInfo(
                l_datBizDate,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_request.orderInfo.settleDiv,
                l_branch);

        //1.13.reset約定日情報(約定情報, 債券約定日情報, 債券注文種別判定, 債券銘柄, 決済区分, 部店)
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo = l_bondHelperService.resetExecuteDateInfo(
            l_request.execInfo,
            l_bondExecutionDateInfo,
            l_bondOrderTypeJudge,
            l_bondProduct,
            l_request.orderInfo.settleDiv,
            l_branch);

        //1.14 validate約定日(債券銘柄)
        l_bondExecuteDateInfo.validateExecuteDate(l_bondProduct);

        // 1.15.validate数量(double, 債券銘柄)
        //数量：リクエスト.約定情報.約定数量　@!= nullの場合
        //　@　@数量＝リクエスト.約定情報.約定数量
        //　@上記以外の場合
        //　@　@数量＝リクエスト.注文情報.注文数量
        String l_strQuantity = null;
        if (l_request.execInfo.execFaceAmount != null)
        {
            l_strQuantity = l_request.execInfo.execFaceAmount;
        }
        else
        {
            l_strQuantity = l_request.orderInfo.orderQuantity;
        }
        l_bondOrderManager.validateQuantity(
            Double.parseDouble(l_strQuantity),
            l_bondProduct);

        //1.16validate単価(債券銘柄, String)
        //債券銘柄：get債券銘柄
        //単価：リクエスト.約定情報.約定単価　@!= nullの場合
        //　@　@単価＝リクエスト.約定情報.約定単価
        //　@上記以外の場合
        //　@　@単価＝リクエスト.注文情報.単価
        String l_strPrice = null;
        if (l_request.execInfo.execPrice != null)
        {
            l_strPrice = l_request.execInfo.execPrice;
        }
        else
        {
            l_strPrice = l_request.orderInfo.price;
        }
        l_bondOrderManager.validateExecPrice(l_bondProduct, l_strPrice);

        //1.17.validate為替レート(債券銘柄, BigDecimal)
        //[引数]
        // 債券銘柄：get債券銘柄
        // 為替レート：
        // リクエスト.約定情報.約定為替レート　@!= nullの場合
        // 為替レート＝リクエスト.約定情報.約定為替レート
        // 上記以外の場合
        // 為替レート＝リクエスト.注文情報.為替レート
        String l_strFxRate = null;

        if (l_request.execInfo.execFxRate != null)
        {
            l_strFxRate = l_request.execInfo.execFxRate;
        }
        else
        {
            l_strFxRate = l_request.orderInfo.fxRate;
        }

        l_bondOrderManager.validateFxRate(
            l_bondProduct, l_strFxRate);

        //1.18.is外貨建( )
        boolean l_foreignCurrency = l_bondProduct.isForeignCurrency();

        //1.19.is外貨建( )＝＝true
        BigDecimal l_bdGetFxRate = null;
        if (l_foreignCurrency)
        {
            //1.19.1.get為替レート(債券銘柄, 債券注文種別判定, String, BigDecimal, boolean)
            //為替レートを取得する。
            //[引数]
            //　@債券銘柄：get債券銘柄
            //　@債券注文種別判定：債券注文種別判定オブジェクト
            //　@決済区分：リクエスト.注文情報.決済区分
            //　@入力為替レート：リクエスト.約定情報.約定為替レート　@!= nullの場合
            //　@　@為替レート＝リクエスト.約定情報.約定為替レート
            //　@上記以外の場合
            //　@　@為替レート＝リクエスト.注文情報.為替レート
            //　@is約定計算：　@true
            BigDecimal l_bdFxRate = null;

            if (l_request.execInfo.execFxRate != null)
            {
                l_bdFxRate = new BigDecimal(l_request.execInfo.execFxRate);
            }
            else
            {
                l_bdFxRate = new BigDecimal(l_request.orderInfo.fxRate);
            }

            l_bdGetFxRate = l_bondOrderManager.getFxRate(
                l_bondProduct,
                l_bondOrderTypeJudge,
                l_request.orderInfo.settleDiv,
                l_bdFxRate,
                true);
        }

        //1.20.calc受渡代金(債券注文種別判定, BigDecimal, BigDecimal, BigDecimal, 債券銘柄, 債券約定日情報)
        //債券受渡代金計算結果オブジェクトを生成する
        //[引数]
        //債券注文種別判定：生成した債券注文種別判定
        //数量：
        //　@リクエスト.約定情報.約定数量　@!= nullの場合
        //　@　@数量＝リクエスト.約定情報.約定数量
        //　@上記以外の場合
        //　@　@数量＝リクエスト.注文情報.注文数量
        //単価：
        //　@リクエスト.約定情報.約定単価　@!= nullの場合
        //　@　@単価＝リクエスト.約定情報.約定単価
        //　@上記以外の場合
        //　@　@単価＝リクエスト.注文情報.単価
        //為替レート：get為替レート（※is外貨建()の戻り値 == falseの場合、nullをセットする。）
        //債券銘柄：get債券銘柄
        //債券約定日情報：生成した約定日情報
        String l_dblQuantity = "0";
        String l_dblPrice = "0";

        if (l_request.execInfo.execFaceAmount != null)
        {
            l_dblQuantity = l_request.execInfo.execFaceAmount;
        }
        else if (l_bondOrderInfo.orderQuantity != null)
        {
            l_dblQuantity = l_bondOrderInfo.orderQuantity;
        }

        if (l_request.execInfo.execPrice != null)
        {
            l_dblPrice = l_request.execInfo.execPrice;
        }
        else if (l_bondOrderInfo.price != null)
        {
            l_dblPrice = l_bondOrderInfo.price;
        }

        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondBizLogicProvider l_bondBizLogicProvider =
            (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();

        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            l_bondBizLogicProvider.calcEstimatedPrice(
            l_bondOrderTypeJudge,
            new BigDecimal(l_dblQuantity),
            new BigDecimal(l_dblPrice),
            l_bdGetFxRate,
            l_bondProduct,
            l_bondExecuteDateInfo);

        //1.21.to約定情報(債券約定日情報, 債券受渡代金計算結果, カストディアン, 拡張債券注文単位)
        //引数：
        //債券約定日情報=create債券約定日情報()の戻り値
        //（reset約定日情報()で再設定した後の債権約定日情報）
        //債券受渡代金計算結果=calc受渡代金()の戻り値
        //カストディアン=リクエスト.約定情報.カストディアン
        //拡張債券注文単位＝null
        WEB3AdminBondOrderExecInfo l_bondOrderExecInfo = l_bondHelperService.toOrderExecInfo(
            l_bondExecuteDateInfo,
            l_bondEstimatedPriceCalcResult,
            l_request.execInfo.custodianInfo,
            null);

        //1.22.createレスポンス( )
        WEB3AdminBondExecCalculateResponse l_response =
            (WEB3AdminBondExecCalculateResponse)l_request.createResponse();
        //1.23.プロパティセット
        //約定情報=to約定情報
        l_response.execInfo = l_bondOrderExecInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
