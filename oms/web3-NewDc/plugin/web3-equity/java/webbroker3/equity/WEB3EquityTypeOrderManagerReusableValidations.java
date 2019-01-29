head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityTypeOrderManagerReusableValidations.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式発注審査個別チェック(WEB3EquityTypeOrderManagerReusableValidations.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/16 中尾　@寿彦(SRA) 新規作成
                 : 2006/08/29 張騰宇(中訊) モデル 970
                 : 2006/11/01 肖志偉 (中訊) 仕様変更モデル951,990,995,1007,1027,1034
                 : 2006/11/28 唐性峰 (中訊) 仕様変更モデル1073（※【共通】仕様変更管理（ＤＢレイアウト）No.447も参照）
                 : 2007/07/27 望月　@研志(SRA) 仕様変更モデル 1181,1182,1183,1185,1186,1187
Revesion History : 2007/04/27 謝旋(中訊) モデル 1142
Revesion History : 2007/06/04 何文敏 (中訊) 仕様変更モデルNo.1149,モデルNo.1155,モデルNo.1161
Revesion History : 2007/06/15 武波 (中訊) モデルNo.1175
Revesion History : 2007/07/26 何文敏 (中訊) 仕様変更モデルNo.1190
Revesion History : 2007/08/30 柴双紅 (中訊) 仕様変更モデルNo.1193
Revesion History : 2007/12/10 趙林鵬 (中訊) 仕様変更モデルNo.1239
Revesion History : 2009/09/10 孟亞南 (中訊) 仕様変更モデルNo.1331 計算式書No.022
Revesion History : 2009/09/21 車進 (中訊) モデル No.1333 No.1339 No.1343 No.1344 No.1348
                                          計算式書 No.023 No.024 No.025 No.026 No.028 No.029 No.030
                                                  No.031 No.032 No.036 No.037 No.038
Revesion History : 2009/10/14 張騰宇 (中訊) モデル No.1352 計算式書No.041
Revesion History : 2009/10/20 張騰宇 (中訊) モデル No.1353
*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSwapOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeQuoteData;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EquityProductTypeOrderManagerReusableValidations;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ChangeableTypeDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3InsiderRegistDivDef;
import webbroker3.common.define.WEB3ListTypeDef;
import webbroker3.common.define.WEB3MarginGenProductTypeDef;
import webbroker3.common.define.WEB3MarginSysProductTypeDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MaxMinFlagDef;
import webbroker3.common.define.WEB3MultiChangeabilityDef;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PremiumRestraintRateDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3PriceRangeIdDef;
import webbroker3.common.define.WEB3PriceRangeTypeDef;
import webbroker3.common.define.WEB3QualifiedInstInvestorDivDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3ShortMarginRestrainDivDef;
import webbroker3.common.define.WEB3ShortSellingCountMethodDivDef;
import webbroker3.common.define.WEB3SuspensionDef;
import webbroker3.common.define.WEB3TargetTypeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradeStopDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.define.WEB3TriggerorderWlimitorderCheckOrderCondPriceDef;
import webbroker3.equity.data.OffFloorOrderProductParams;
import webbroker3.equity.data.ShortSellingRestraintTimeRow;
import webbroker3.equity.define.WEB3EquityBackServiceOnlineDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchListmarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInsider;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.InsiderRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.define.WEB3GentradeEquityMarginDivDef;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.triggerorder.base.data.TriggerOrderStopRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * （株式発注審査個別チェック）。<BR>
 * <BR>
 * 発注審査の個々のチェックを実装。<BR>
 * （EquityTypeOrderManagerReusableValidationsの拡張クラス）
 * @@version 1.0
 */
public class WEB3EquityTypeOrderManagerReusableValidations
    extends EquityProductTypeOrderManagerReusableValidations
{
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityTypeOrderManagerReusableValidations.class);

    /**
     * （エラータグ）。
     */
    public static String ORDER_ERROR_TAG = "WEB3ErrorReasonCode";  
    
    /**
     * （株式発注審査個別チェック）。<BR>
     * <BR>
     * コンストラクタ
     */
    public WEB3EquityTypeOrderManagerReusableValidations()
    {

    }

    /**
     * （スーパークラスに自身のインスタンスを登録する。）。<BR>
     * <BR>
     * （プラグイン初期化時にコールされる）<BR>
     * <BR>
     * ---<BR>
     * super.setInstance(this);<BR>
     * ---
     */
    public void register()
    {
        super.setInstance(this);
    }

    /**
     * （validate市場コード）。<BR>
     * <BR>
     * 市場コードのチェックを実施する。<BR>
     * 存在する場合は市場オブジェクトを返却する。<BR>
     * （validateMarketのオーバーロード）<BR>
     * <BR>
     * １）　@拡張金融オブジェクトマネージャ.get市場（）にて該当する<BR>
     * 　@　@　@市場オブジェクトを生成する。<BR>
     * 　@　@　@市場が取得できない場合は、該当市場なしと判断し、例外を<BR>
     * 　@　@　@スローする。<BR>
     * 　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag   : BUSINESS_ERROR_00003<BR>
     * <BR>
     * ２）　@市場.取引停止 == ”停止中”の場合、例外をスローする。<BR>
     * 　@　@　@-停止中-<br>
     * 　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag   : BUSINESS_ERROR_00013<BR>
     * <BR>
     * ３）　@市場オブジェクトを返却する。
     * @@param l_strMarketCode 市場コード
     * @@param l_strInstitutionCode 証券会社コード
     * @@return Market 市場
     * @@throws WEB3BaseException
     */
    public Market validateMarket(
        String l_strMarketCode,
        String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMarket(String, String)";
        log.entering(STR_METHOD_NAME);

        Market l_market;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        //拡張金融オブジェクトマネージャ.get市場（）にて該当する
        //市場オブジェクトを生成する。
        //市場が取得できない場合は、該当市場なしと判断し、例外をスローする。
        try
        {
            l_market =
                (WEB3GentradeMarket) l_gentradeFinObjectManager.getMarket(
                    l_strInstitutionCode,
                    l_strMarketCode);
        }
        catch (NotFoundException nfe)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "not find : market_code = " + l_strMarketCode + " , institution_code = " + l_strInstitutionCode);
        }

        MarketParams l_params = (MarketParams) l_market.getDataSourceObject();

        if (l_params.getSuspension().equals(WEB3SuspensionDef.SUSPENSION))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01747,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_market;
    }

    /**
     * （validate取引銘柄）。<BR>
     * <BR>
     * 取引銘柄のチェックを行う。<BR>
     * （validateTradedProductのオーバーロード）<BR>
     * <BR>
     * １）　@this.validate取引銘柄(株式銘柄, 市場)をコールする。<BR>
     * <BR>
     * ２）　@外国証券口座開設チェック<BR>
     * 　@　@　@取引銘柄.上場区分＝”外国部上場”の場合のみ、<BR>
     * 　@　@　@当該顧客が外国証券口座を開設しているかどうかチェックする。<BR>
     * <BR>
     * 　@　@　@顧客.is外国証券口座開設( )==false（外国証券口座開設なし）の場合は<BR>
     * 　@　@　@「当該顧客は外国証券口座開設なし」の例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01341<BR>
     * <BR>
     * ３）　@取引銘柄is現物売買規制（is売注文）にて売買規制中かを判定する。<BR>
     * 　@　@trueが返却された場合、例外をスローする。<BR>
     * <BR>
     * 　@－停止中には”停止中（当社規制）”、”停止中”（取引所規制）の２種類あり、<BR>
     * 　@　@　@エラーメッセージを分ける。<BR>
     * 　@　@　@-停止中（当社規制）- <BR>
     * 　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag   : BUSINESS_ERROR_00014<BR>
     * 　@　@　@-停止中（取引所規制）- <BR>
     * 　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag   : BUSINESS_ERROR_00015
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@補助口座オブジェクト。
     * @@param l_eqTypeProduct (株式銘柄)<BR>
     * 　@　@　@株式銘柄オブジェクト。
     * @@param l_market (市場)<BR>
     * 　@　@　@市場オブジェクト。
     * @@param l_isSellOrder (is売注文)<BR>
     * 　@　@　@売注文、買注文のフラグ。<BR>
     * 　@　@　@売注文の場合true、買注文の場合falseを指定する。
     * @@return TradedProduct
     * @@throws WEB3BaseException
     */
    public TradedProduct validateTradedProduct(
        SubAccount l_subAccount,
        EqTypeProduct l_eqTypeProduct,
        Market l_market,
        boolean l_isSellOrder)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateTradedProduct(SubAccount, EqTypeProduct, Market, boolean)";
        log.entering(STR_METHOD_NAME);

        // １）　@this.validate取引銘柄(株式銘柄, 市場)をコールする。
        WEB3EquityTradedProduct l_eqTypeTradedProduct =
            (WEB3EquityTradedProduct)this.validateTradedProduct(l_eqTypeProduct, l_market);

        // ２）　@外国証券口座開設チェック
        // 　@　@　@取引銘柄.上場区分＝”外国部上場”の場合のみ、
        // 　@　@　@当該顧客が外国証券口座を開設しているかどうかチェックする。
        EqtypeTradedProductRow l_eqtypeTradedProductRow =
            (EqtypeTradedProductRow)l_eqTypeTradedProduct.getDataSourceObject();
        if (WEB3ListTypeDef.FOREIGN_SECITION.equals(l_eqtypeTradedProductRow.getListType()))
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_account = null;
            try
            {
                l_account =
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_subAccount.getAccountId());
            }
            catch (NotFoundException l_exp)
            {
                log.error("顧客データの取得に失敗しました。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exp.getMessage(),
                    l_exp);
            }
            if (l_account.isForeignAccountOpen() == false)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // ３）　@取引銘柄is現物売買規制（is売注文）にて売買規制中かを判定する。
        // 　@　@trueが返却された場合、例外をスローする。
        if (l_eqTypeTradedProduct.isSpotTradeControl(l_isSellOrder))
        {
            if (l_isSellOrder)
            {
                if (l_eqtypeTradedProductRow.getSellCashStop()
                    == Integer.parseInt(WEB3TradeStopDef.STOP_MARKET_DEREG))
                {
                    // 停止中（取引所規制）
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00015,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                else if (
                l_eqtypeTradedProductRow.getSellCashStop()
                        == Integer.parseInt(WEB3TradeStopDef.STOP_COMPANY_DEREG))
                {                    // 停止中（当社規制）
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00014,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            else
            {
                if (l_eqtypeTradedProductRow.getBuyCashStop()
                    == Integer.parseInt(WEB3TradeStopDef.STOP_MARKET_DEREG))
                {
                    // 停止中（取引所規制）
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00015,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                else if (
                l_eqtypeTradedProductRow.getBuyCashStop()
                        == Integer.parseInt(WEB3TradeStopDef.STOP_COMPANY_DEREG))
                {
                    // 停止中（当社規制）
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00014,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }

        }

        log.exiting(STR_METHOD_NAME);
        return l_eqTypeTradedProduct;
    }

    /**
     * （validate特定口座取扱規制）。<BR>
     * <BR>
     * 特定口座にて取扱可能な銘柄であるかチェックを行う。<BR>
     * <BR>
     * １）　@特定口座指定でない場合、処理終了<BR>
     * 　@　@　@指定された税種別が”特定”または、”特定口座かつ源泉徴収”<BR>
     * 　@　@　@でない場合は、メソッドを終了する。<BR>
     * <BR>
     * ２）　@特定口座規制中であるかの判定<BR>
     * 　@　@　@株式銘柄.特定口座取扱規制==”特定口座にて取扱不可”<BR>
     * 　@　@　@の場合、例外をスローする。<BR>
     * 　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag   : BUSINESS_ERROR_00005
     * @@param l_taxTypeEnum <BR>
     * 　@　@　@0:その他,　@1:一般,　@2:特定,　@3:特定口座かつ源泉徴収<BR>
     * 　@　@　@（TaxTypeEnumにて定義）
     * @@param l_eqTypeProduct 株式銘柄オブジェクト
     * @@param l_isBuyOrder 買注文かどうかのフラグ。<BR>
     * 　@　@　@買注文の場合true、売注文の場合はfalseを指定する。
     * @@throws WEB3BaseException
     */
    public void validateCapitalGainTaxDealingsReg(
        TaxTypeEnum l_taxTypeEnum,
        EqTypeProduct l_eqTypeProduct,
        boolean l_isBuyOrder)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateCapitalGainTaxDealingsReg(TaxTypeEnum , EqTypeProduct , boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_taxTypeEnum == null || l_eqTypeProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //特定口座指定でない場合、処理終了
        //指定された税区分が”特定”または、”特定口座かつ源泉徴収”<BR>
        // 　@　@　@でない場合<BR>
        // 　@　@　@（税区分 = 一般口座 or ストックオプション口座 or その他　@の場合）<BR>
        //　@　@　@は、メソッドを終了する。<BR>
        if (l_taxTypeEnum.equals(TaxTypeEnum.NORMAL)
            || l_taxTypeEnum.equals(TaxTypeEnum.UNDEFINED)
            || l_taxTypeEnum.equals(TaxTypeEnum.STOCK_OPTION))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        EqtypeProductParams l_params =
            (EqtypeProductParams) l_eqTypeProduct.getDataSourceObject();
        log.debug("l_params.getProductId()="+l_params.getProductId());
         
        //特定口座規制中であるかの判定<BR>
        //株式銘柄.特定口座取扱規制==”特定口座にて取扱不可”
        //の場合、例外をスローする
        if (WEB3DealtDef.CAN_NOT_DEALT.equals("" + l_params.getCapitalGainTaxDealingsReg()))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （validate株数）。<BR>
     * <BR>
     * 株数のチェックを行う。<BR>
     * 　@－株数が０またはマイナス値でないこと。<BR>
     * 　@－株数が市場の売買上限単位を超えていないこと。<BR>
     * 　@－株数がHOST側で受付可能な株数上限値を超えていないこと。<BR>
     * （validateQuantityのオーバーロード）<BR>
     * <BR>
     * １）　@スーパークラスの処理（validateQuantity(株数)）にて、<BR>
     *       株数が0またはマイナス値でないかチェックを行う。<BR>
     * <BR>
     * ２）　@スーパークラスの処理（checkLotSize(取引銘柄.売買単位, 株数)）にて、<BR>
     *       株数が売買単位の整数倍であるかチェックを行う。<BR>
     * <BR>
     * ３）　@this.validate株数（指定可能上限値）(引数の部店, 引数の株数))にて、<BR>
     * 　@　@　@注文株数が、HOST側で受付可能な株数上限値を超えていないかどうかのチェックを行う。<BR>
     * <BR>
     * ４）　@市場の売買上限株数を計算する。<BR>
     * <BR>
     * （計算式）<BR>
     * 取引銘柄.売買単位　@×　@売買限度単位(*1)　@＝　@市場の売買上限株数<BR>
     * <BR>
     * (*1) 売買限度単位：<BR>
     * 　@　@　@－取引銘柄.強制限度単位 != null の場合は、取引銘柄.強制限度単位 を使用する。<BR>
     * 　@　@　@－取引銘柄.強制限度単位 == null の場合は、<BR>
     * 　@　@　@　@　@部店.get売買限度単位（市場コード, 取引銘柄.上場区分）にて取得した値を使用する。<BR>
     * <BR>
     * ５）　@（市場の売買上限株数 ＜ 株数）の場合、例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_00160
     * @@param l_tradedProduct 取引銘柄
     * @@param l_branch 部店
     * @@param l_dblStockQuantity 株数
     * @@throws WEB3BaseException
     */
    public void validateQuantity(
        TradedProduct l_tradedProduct,
        WEB3GentradeBranch l_branch,
        double l_dblStockQuantity)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateQuantity(TradedProduct , WEB3GentradeBranch, double)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null || l_branch == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_dblStockQuantity < 0.0D)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);

        }

        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();

        try
        {
            // １）　@スーパークラスの処理（validateQuantity(株数)）にて、
            // 　@　@株数が0またはマイナス値でないかチェックを行う。
            super.validateQuantity(l_dblStockQuantity);
        }
        catch (OrderValidationException ove)
        {
                 throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {           
            // ２）　@スーパークラスの処理（checkLotSize(取引銘柄.売買単位, 株数)）にて、
            // 　@　@株数が売買単位の整数倍であるかチェックを行う。
            super.checkLotSize(
                l_tradedProductRow.getLotSize(),
                l_dblStockQuantity);       
        }
        catch (OrderValidationException ove)
        {
            throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_00708,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // ３）　@this.validate株数（指定可能上限値）(引数の部店, 引数の株数))にて、
        // 　@　@　@注文株数が、HOST側で受付可能な株数上限値を超えていないかどうかのチェックを行う。
        this.validateQuantity(l_branch, l_dblStockQuantity);

        //  ４）　@市場の売買上限株数を計算する。
        // 売買限度単位を取得
        double l_dblLimitedUnit = 0.0D;
        if (l_tradedProductRow.getCompulsiveLimitedUnitIsNull())
        {
            l_dblLimitedUnit =
                l_branch.getDealingLimitUnit(
                    l_tradedProduct.getMarket().getMarketCode(),
                    l_tradedProductRow.getListType());
        }
        else
        {
            l_dblLimitedUnit = l_tradedProductRow.getCompulsiveLimitedUnit();
        } 
        // 取引銘柄.売買単位　@×　@売買限度単位　@＝　@市場の売買上限株数 
        double l_dblResultHighQuantity = l_tradedProductRow.getLotSize() * l_dblLimitedUnit;

        //  ５）　@（市場の売買上限株数　@< 株数）の場合、例外をスローする。
        if (l_dblResultHighQuantity < l_dblStockQuantity)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00160,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （validate取扱可能市場）。<BR>
     * <BR>
     * 会社部店の取扱可能市場かをチェックする。<BR>
     * <BR>
     * １）　@部店.is取扱可能市場( )にて、株式の取扱市場かを判定する。<BR>
     * falseが返された場合は、部店の取扱可能市場でないと判定し、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00158<BR>
     * <BR>
     * ２）　@JASDAQ取扱チェック<BR>
     * 　@－取引銘柄.validateJASDAQ銘柄取扱可能( )をコールする。
     * @@param  l_branch 部店オブジェクト（顧客の取引店）
     * @@param  l_tradedProduct 株式取引銘柄オブジェクト
     * @@throws WEB3BaseException
     */
    public void validateHandlingMarket(
        WEB3GentradeBranch l_branch,
        TradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateHandlingMarket(WEB3GentradeBranch, TradedProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_branch == null || l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // １）　@部店.is取扱可能市場( )にて、株式の取扱市場かを判定する。
        String l_strMarketCode = l_tradedProduct.getMarket().getMarketCode();
        boolean l_isStockHandlingMarket =
            l_branch.isHandlingPossibleMarket(
                l_strMarketCode,
                ProductTypeEnum.EQUITY);
        // falseが返された場合は、部店の取扱可能市場でないと
        // 判定し、例外をスローする。
        if (!l_isStockHandlingMarket)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00158,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // ２）　@JASDAQ取扱チェック
        //　@－取引銘柄.validateJASDAQ銘柄取扱可能( )をコールする。
        WEB3EquityTradedProduct l_equityTradedProduct = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        try
        {
            l_equityTradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                l_tradedProduct.getTradedProductId());
        }
        catch (NotFoundException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_equityTradedProduct.validateJASDAQProductHandling(l_branch);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （validate取引可能上限金額）。<BR>
     * 概算金額値が、会社・部店で一度に取引可能な上限金額を超えていないかチェックを行う。<BR>
     * <BR>
     * １）引数の「口座タイプ」より、取引可能上限値を決定する。<BR>
     * 　@　@口座タイプ＝個人アカウント、共用アカウント の場合：部店.取引可能金額上限値（個人）<BR>
     * 　@　@口座タイプ＝法@人アカウント の場合：部店.取引可能金額上限値（法@人）<BR>
     * 　@　@口座タイプが上記以外の場合：例外をthrowする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_00149<BR>
     * <BR>
     * ２）　@（ １）で決定した取引可能金額上限値　@＜　@拘束売買代金）の場合、<BR>
     * 　@　@取引可能上限値を超過していると判断し、例外をスローする。 <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00161<BR>
     * @@param l_branch
     * @@param l_market
     * @@param l_dblRestraintTurnover 拘束売買代金を指定する。 
     * @@param l_mainAccountTypeEnum 口座タイプ
     * @@throws WEB3BaseException
     */
    public void validateMaxHandlingPrice(
        Branch l_branch,
        Market l_market,
        double l_dblRestraintTurnover,
        MainAccountTypeEnum l_mainAccountTypeEnum)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMaxHandlingPrice(Branch, Market, double, MainAccountTypeEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_branch == null
            || l_mainAccountTypeEnum == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_dblRestraintTurnover < 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //（１）引数の「口座タイプ」より、取引可能上限値を決定する
        double l_dblMaxHandlingPrice = 0;
        if (MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.equals(l_mainAccountTypeEnum) ||
            MainAccountTypeEnum.JOINT_OWNERSHIP.equals(l_mainAccountTypeEnum))
        {
            //口座タイプ＝個人アカウント、共用アカウント の場合：部店.取引可能金額上限値（個人） 
            l_dblMaxHandlingPrice =
                ((BranchParams) l_branch.getDataSourceObject()).getMaxHandlingPriceInd();
        }
        else if (MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountTypeEnum))
        {
            //口座タイプ＝法@人アカウント の場合：部店.取引可能金額上限値（法@人）
            l_dblMaxHandlingPrice =
                ((BranchParams) l_branch.getDataSourceObject()).getMaxHandlingPriceCorp();
        }
        else
        {
            //口座タイプが上記以外の場合：例外をthrowする。
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00149,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //（１）で決定した取引可能金額上限値　@＜　@拘束売買代金）の場合、
        //取引可能上限値を超過していると判断し、例外をスローする
        if (l_dblMaxHandlingPrice < l_dblRestraintTurnover)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00161,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （get概算金額計算方式）。<BR>
     * <BR>
     * 【会社部店商品テーブル】から、概算金額計算方式を取得する。<BR>
     * 取得した計算方式は、STOP高計算要否の判定に使用する。<BR>
     * <BR>
     * 当メソッドの詳細は<BR>
     * 「基本設計計算式書（エクイティ）.doc」の「[2]成行時計算単価算出」<BR>
     * を参照。
     * @@param l_strCommissionProductCode 【会社部店商品テーブル】検索時に使用する。
     * @@param l_subAccount 【会社部店商品テーブル】検索時の、部店ID指定に使用する。
     * @@return long
     * @@throws WEB3SystemLayerException
     */
    public long getEstimatePriceCalcForm(
        String l_strCommissionProductCode,
        SubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getEstimatePriceCalcForm(String, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_strCommissionProductCode == null || l_subAccount == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_strCommissionProductCode.length() == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        long l_lngEstimatePriceCalcForm;
        long l_lngBranchId;
        long l_lngAccountId = l_subAccount.getAccountId();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        try
        {

            l_lngBranchId =
                l_accountManager.getMainAccount(l_lngAccountId).getBranch().getBranchId();
        }
        catch (NotFoundException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        try
        {
            //会社部店商品テーブルより概算金額計算方式取得
            l_lngEstimatePriceCalcForm =
                InstBranchProductDao.findRowByBranchIdCommissionProductCode(
                        l_lngBranchId,
                        l_strCommissionProductCode).getEstimatePriceCalcForm();
        }
        catch (DataException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lngEstimatePriceCalcForm;
    }

    /**
     * （is権利落ち日）。<BR>
     * <BR>
     * 発注日が権利落ち日であるかどうかの判定を行う。<BR>
     * 発注日＝権利落ち日の場合はtrueを、<BR>
     * 上記以外の場合はfalseを返す。<BR>
     * <BR>
     * 当メソッドの詳細は<BR>
     * 「基本設計計算式書（エクイティ）.doc」の「[●共通4]権利落ち日チェック」を参照。
     * @@param l_tsOrderBizDate 発注日
     * @@param l_tsYearlyBooksClosingDate 権利確定日<BR>
     * 　@　@　@権利確定日を指定する。通常は、【株式銘柄テーブル】決算日が指定される。
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isDevidendRightDate(
        Timestamp l_tsOrderBizDate,
        Timestamp l_tsYearlyBooksClosingDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDevidendRightDate(String, Timestamp)";
        log.entering(STR_METHOD_NAME);

        if (l_tsOrderBizDate == null || l_tsYearlyBooksClosingDate == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        boolean l_boDevidendRightDate = false;

        // 権利落ち日算出処理
        WEB3GentradeBizDate l_bizDateCalcUtil =
            new WEB3GentradeBizDate(l_tsYearlyBooksClosingDate);

        Timestamp l_tsDevidendRightDate = l_bizDateCalcUtil.roll(-2);
      
        if(l_tsOrderBizDate.compareTo(l_tsDevidendRightDate)==0)
        {
            l_boDevidendRightDate = true;
        }

        log.exiting(STR_METHOD_NAME);
        return l_boDevidendRightDate;
    }

    /**
     * （calc値幅上限）。<BR>
     * <BR>
     * 非指値注文の場合：STOP高かつ該当銘柄が非権利落ち日の場合の STOP高<BR>
     * 金額算出を、指値注文の場合：値幅チェックに使用する値幅上限算出を、<BR>
     * それぞれ行う。<BR>
     * <BR>
     * 当メソッドの詳細は<BR>
     * 「基本設計計算式書（エクイティ）.doc」の「[●共通3]値幅上限算出」<BR>
     * を参照。<BR>
     * <BR>
     * Parameter 値幅の基準値<BR>
     * Parameter 基準値から許容される値幅<BR>
     * Parameter (基準値＋値幅)に対する刻み値を設定する。
     * @@param l_dblBasePrice
     * @@param l_dblPriceRange
     * @@param l_dblLimitPriceUnit
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcStopHighPrice(
        double l_dblBasePrice,
        double l_dblPriceRange,
        double l_dblLimitPriceUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcStopHighPrice(double, double, double)";
        log.entering(STR_METHOD_NAME);

        if (l_dblBasePrice < 0
            || l_dblPriceRange < 0
            || l_dblLimitPriceUnit < 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        BigDecimal l_bdStopHighPrice;

        BigDecimal l_bdBasePrice = new BigDecimal(l_dblBasePrice);
        BigDecimal l_bdPriceRange = new BigDecimal(l_dblPriceRange);
        BigDecimal l_bdLimitPriceUnit = new BigDecimal(l_dblLimitPriceUnit);

        BigDecimal l_bdPrice = l_bdBasePrice.add(l_bdPriceRange);

        if ((l_bdPrice.doubleValue() % l_bdLimitPriceUnit.doubleValue()) == 0)
        {
            l_bdStopHighPrice = l_bdPrice;
        }
        else
        {
            BigDecimal l_bdResult1 = l_bdPrice.add(l_bdLimitPriceUnit);
            BigDecimal l_bdResult2 = l_bdResult1.subtract(new BigDecimal(1));
            BigDecimal l_bdResult3 =
                l_bdResult2.divide(l_bdLimitPriceUnit, BigDecimal.ROUND_DOWN);

            l_bdStopHighPrice = l_bdResult3.multiply(l_bdLimitPriceUnit);
        }

        log.exiting(STR_METHOD_NAME);
        return l_bdStopHighPrice.doubleValue();
    }

    /**
     * （validate銘柄コード）。<BR>
     * <BR>
     * 銘柄コードの存在チェックを実施する。<BR>
     * 存在する場合は銘柄オブジェクトを返却する。<BR>
     * <BR>
     * １）　@引数の銘柄コード、証券会社コードに該当する、株式銘柄<BR>
     * 　@　@　@オブジェクトを生成する。<BR>
     * <BR>
     * ２）　@株式銘柄.売買停止 == ”停止中”の場合、例外をスローする。<BR>
     * 　@　@　@－停止中には”停止中（当社規制）”、”停止中”（取引所規制）の<BR>
     * 　@　@　@２種類あり、エラーメッセージを分ける。<BR>
     * 　@　@　@-停止中（当社規制）- <BR>
     * 　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag   : BUSINESS_ERROR_00014<BR>
     * <BR>
     * 　@　@　@-停止中（取引所規制）- <BR>
     * 　@　@　@　@class    : WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag      : BUSINESS_ERROR_00015<BR>
     * <BR>
     * ３）　@株式銘柄オブジェクトを返却する。
     * @@param l_strProductCode 銘柄コード
     * @@param l_strInstitutionCode 証券会社コード
     * @@return WEB3EquityProduct 銘柄オブジェクト
     * @@throws WEB3BaseException
     */
    public WEB3EquityProduct validateProductCode(
        String l_strProductCode,
        String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateProductCode(String, String)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityProduct l_equityProduct;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //１）　@引数の銘柄コード、証券会社コードに該当する、株式銘柄
        // オブジェクトを生成する。
        try
        {
            //get 証券会社
            Institution l_institution =
                l_finApp.getAccountManager().getInstitution(
                    l_strInstitutionCode);

            WEB3EquityProductManager l_eqTypeProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();

            //get 銘柄
            l_equityProduct =
                (WEB3EquityProduct) l_eqTypeProductManager.getProduct(
                    l_institution,
                    l_strProductCode);
        }
        catch (NotFoundException nfe)
        {
            WEB3BusinessLayerException l_businessException =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            throw l_businessException;
        }

        EqtypeProductParams l_eqProductParams =
            (EqtypeProductParams)l_equityProduct.getDataSourceObject();

        //２）　@株式銘柄.売買停止 == ”停止中”の場合、例外をスローする。
        int l_intTradeStop = l_eqProductParams.getTradeStop();
        if (l_intTradeStop
            == Integer.parseInt(WEB3TradeStopDef.STOP_MARKET_DEREG))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00015,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else if (
            l_intTradeStop
                == Integer.parseInt(WEB3TradeStopDef.STOP_COMPANY_DEREG))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00014,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_equityProduct;
    }

    /**
     * （validate注文単価）。<BR>
     * <BR>
     * 指値が適切であるかどうかのチェックを行う。<BR>
     * （validatePriceのオーバーロード）<BR>
     * <BR>
     * １）　@引数.指値のマイナス値チェックを行う。<BR>
     * <BR>
     * ２）　@引数.指値のチェックを行う。<BR>
     * 引数.指値が適切であるかのチェックを行い、適切であればtrueを返却する。<BR>
     * 適切でない場合は、例外をスローする。<BR>
     * 成行注文（引数.指値＝0）の場合は、trueを返却する。<BR>
     * <BR>
     * 当メソッド、及び当メソッドからコールされるメソッドの詳細は<BR>
     * 「基本設計計算式書（エクイティ）.doc」の「1.1.  注文単価チェック」を参照。<BR>
     * 手続きシーケンスについては「（注文）validate注文単価」を参照。
     * @@param l_dblLimitPrice 指値。
     * @@param l_tradedProduct (取引銘柄)<BR>
     * 　@　@　@商品・市場関連の各種エンティティからデータを取得する際に使用する。
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@【会社部店商品テーブル】から概算金額計算方式を取得する際に使用する。
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean validatePrice(
        double l_dblLimitPrice, 
        WEB3EquityTradedProduct l_tradedProduct, 
        SubAccount l_subAccount)
            throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME =
            "validatePrice(double, WEB3EquityTradedProduct, SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_tradedProduct == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        
        if (Double.isNaN(l_dblLimitPrice) || l_dblLimitPrice == 0)
        {
            return true;
        }
        
        //呼値チェック       
        this.isTickValueDef((WEB3EquityTradedProduct)l_tradedProduct, l_dblLimitPrice);
                
        //取引銘柄Row                
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        
        //株式銘柄Rowを取得
        EqtypeProductRow l_productRow =
            (EqtypeProductRow)l_tradedProduct.getProduct().getDataSourceObject();

        //is権利落ち日
        Timestamp l_tsOrderBizDate = 
              new Timestamp(WEB3DateUtility.getDate(
              l_tradedProductRow.getValidUntilBizDate(), "yyyyMMdd").getTime());

        boolean l_blnIsDevidendRightDate = 
              this.isDevidendRightDate(l_tsOrderBizDate, l_productRow.getYearlyBooksClosingDate());

        //Is値幅チェック対象外
        boolean l_blnIsPriceRangeCheck = false;
        if (WEB3PriceRangeTypeDef.CHECK.equals(l_tradedProductRow.getPriceRangeType()))
        {
            l_blnIsPriceRangeCheck = true;
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule =
            (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityProductManager l_equityProductManager =
            (WEB3EquityProductManager) l_tradingModule.getProductManager();

        //発注日（（入力）取引銘柄.有効日）＝受付日時の翌営業日であると判断する。
        boolean l_blnIsOrderNextDate = false;
        //発注日（（入力）取引銘柄.有効日）
        Date l_datOrderBizDate = WEB3DateUtility.getDate(
            l_tradedProductRow.getValidUntilBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        //受付日時の翌営業日
        Timestamp l_orderTime = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        Date l_datGentradeBizDate =
            new WEB3GentradeBizDate(l_orderTime).roll(1);
        String l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(l_orderTime);
        //発注日（（入力）取引銘柄.有効日）＝受付日時の翌営業日であると判断する
        //受付日時は営業日
        //拡張プロダクトマネージャ .is翌日基準値受信（取引銘柄.証券会社コード）
        if (l_blnIsDevidendRightDate && WEB3DateUtility.compare(WEB3DateUtility.toDay(l_datOrderBizDate),
            WEB3DateUtility.toDay(l_datGentradeBizDate)) == 0  &&
            !WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) &&
            !l_equityProductManager.isNextDayBasePriceMail(l_tradedProductRow.getInstitutionCode()))
        {
            l_blnIsOrderNextDate = true;
        }

        if (!l_blnIsOrderNextDate && l_blnIsPriceRangeCheck)
        {
            return this.isPriceRange((WEB3EquityTradedProduct)l_tradedProduct, l_dblLimitPrice);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * （呼値チェック）。<BR>
     * <BR>
     * 指値が刻み値の整数倍かどうか（指値が刻み値で割り切れるかどうか）を<BR>
     * チェックする。<BR>
     * 非整数倍の場合は例外をスローする<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@tag   : BUSINESS_ERROR_00030<BR>
     * <BR>
     * Parameter 【会社部店商品テーブル】検索時に使用する。<BR>
     * Parameter 商品・市場関連の各種エンティティからデータを取得する際に<BR>
     * 使用します。<BR>
     * Parameter 指値
     * @@param l_tradedProduct
     * @@param l_dblLimitOrder
     * @@throws WEB3BaseException
     */
    private void isTickValueDef(
        WEB3EquityTradedProduct l_tradedProduct,
        double l_dblLimitOrder)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTickValueDef(WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_dblLimitOrder < 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3EquityProductManager l_productManager = null;
        l_productManager =
            (WEB3EquityProductManager) GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();

        BigDecimal l_bdTickValue = null;
        try
        {
            //刻み値取得
            double l_dblTickValue =
                l_productManager.getTickValue(l_tradedProduct, l_dblLimitOrder);
            l_bdTickValue = new BigDecimal(l_dblTickValue);
        }
        catch (WEB3BaseException l_ble)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00030,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (l_dblLimitOrder % l_bdTickValue.doubleValue() != 0)
        {
            // 呼値エラー
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00030,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （値幅チェック）。<BR>
     * <BR>
     * 【値幅テーブル】から値幅を取得して値幅上限／値幅下限を算出し、<BR>
     * 指値が値幅の範囲内であるかどうかをチェックする。<BR>
     * 指値が値幅の範囲内である場合はtrueを、それ以外の場合はfalseを、<BR>
     * それぞれ返す。<BR>
     * <BR>
     * 当メソッドの詳細は<BR>
     * 「基本設計計算式書（エクイティ）.doc」の「[3]値幅チェック」を参照。<BR>
     * <BR>
     * 上記処理中、スルーする例外。<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@tag   : BUSINESS_ERROR_00031
     * @@param l_eqtypeTradedProduct 取引銘柄<BR>
     * 　@　@　@値幅区分及び強制値幅取得、及びget制限値幅()引数<BR>
     * 　@　@　@設定に使用する。
     * @@param l_dblOrderPrice 注文単価 <BR>
     * 　@　@　@チェック対象の注文単価（指値）をセットする。
     * @@return boolean
     * @@throws WEB3BaseException
     */
    private boolean isPriceRange(
        WEB3EquityTradedProduct l_eqtypeTradedProduct,
        double l_dblOrderPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isPriceRange(WEB3GentradeTradedProduct, double)";
        log.entering(STR_METHOD_NAME);
        
        BigDecimal l_bdBasePrice = null;
        BigDecimal l_bdResultPriceHigh = null;
        BigDecimal l_bdResultPriceLow = null;
        
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)
            GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();

        // 注文単価
        BigDecimal l_bdOrderPrice = new BigDecimal(l_dblOrderPrice);

        //(1) [●共通5]基準値算出　@を行う
        l_bdBasePrice =
            new BigDecimal(this.calcBasePrice(l_eqtypeTradedProduct));
        log.debug("*** 「基準値」 ：  " + l_bdBasePrice.doubleValue());

        //(2) (1)で算出した基準値を使用し、値幅上限計算時に
        // 使用する値幅を[●共通2]値幅算出 により計算する。
        BigDecimal l_bdPriceRangeHigh =
            new BigDecimal(
                this.calcPriceRange(
                    l_eqtypeTradedProduct,
                    l_bdBasePrice.doubleValue(),
                    WEB3MaxMinFlagDef.MAXIMUM));
        log.debug("*** 「値幅(上限計算時)」 ：  " + l_bdPriceRangeHigh.doubleValue());

        //(3) （(1)で取得した基準値＋(2)で取得した値幅）を「基準値」
        // として[●共通1]刻み値取得　@を行い、
        // 値幅上限に対する刻み値を取得する。
        BigDecimal l_bdTickValue =
            new BigDecimal(
                l_productManager.getTickValue(
                    l_eqtypeTradedProduct,
                    l_bdBasePrice.add(l_bdPriceRangeHigh).doubleValue()));
        log.debug("*** 「刻み値(上限計算時)」 ：  " + l_bdTickValue.doubleValue());
                    
        //(4) (3)で取得した刻み値を指値単位とし、(2)で取得した
        // 値幅上限計算時に使用する値幅を元に[●共通3]値幅上限算出 
        // を行い、値幅上限を算出する
        l_bdResultPriceHigh =
            new BigDecimal(
                this.calcStopHighPrice(
                    l_bdBasePrice.doubleValue(),
                    l_bdPriceRangeHigh.doubleValue(),
                    l_bdTickValue.doubleValue()));
        log.debug("*** 「値幅上限」 ：  " + l_bdResultPriceHigh.doubleValue());

        //(5) (1)で算出した基準値を使用し、値幅下限計算時に使用する値幅
        // を[●共通2]値幅算出 により計算する
        BigDecimal l_bdPriceRangeLow =
            new BigDecimal(
                this.calcPriceRange(
                    l_eqtypeTradedProduct,
                    l_bdBasePrice.doubleValue(),
                    WEB3MaxMinFlagDef.MINIMUM));
        log.debug("*** 「値幅(下限計算時)」 ：  " + l_bdPriceRangeLow.doubleValue());

        //(6) (4)で取得した値幅上限、及び(5)で取得した値幅下限計算時に
        // 使用する値幅を元に、値幅下限を算出する。
        //(基準値－値幅)≦0 の場合　@⇒　@値幅下限＝1とする。
        //上記以外の場合　@⇒　@値幅下限＝(基準値－値幅)　@
        // (小数点以下を切り捨て)とする。
        l_bdResultPriceLow = l_bdBasePrice.subtract(l_bdPriceRangeLow);
        if (l_bdResultPriceLow.compareTo(new BigDecimal(0)) <= 0)
        {
            l_bdResultPriceLow = new BigDecimal(1);
        }
        else
        {
            l_bdResultPriceLow = new BigDecimal(l_bdResultPriceLow.longValue());
        }
        log.debug("*** 「値幅下限」 ：  " + l_bdResultPriceLow.doubleValue());

        //値幅チェック
        if ((l_bdOrderPrice.compareTo(l_bdResultPriceHigh) > 0)
            || (l_bdOrderPrice.compareTo(l_bdResultPriceLow) < 0))
        {
            String l_strMessage = "(取引銘柄=" + l_eqtypeTradedProduct.getTradedProductId()
                + ")値幅上限： " + l_bdResultPriceHigh
                + "、値幅下限：" + l_bdResultPriceLow
                + "、注文単価：" + l_bdOrderPrice;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00031,
                this.getClass().getName() + "."
                + STR_METHOD_NAME,WEB3EquityTypeOrderManagerReusableValidations.ORDER_ERROR_TAG
                + WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR
                );
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * （calc値幅）。<BR>
     * <BR>
     * 値幅区分、強制値幅（上限値）または強制値幅（下限値）、<BR>
     * 制限値幅を使用し、値幅を算出して返す。 <BR>
     * 値幅区分、強制値幅（上限値）、強制値幅（下限値）は（入力）取引銘柄<BR> 
     * から取得する。 <BR>
     * 引数の基準値に対する制限値幅はget制限値幅()メソッドをコールして取得する。<BR> 
     *  <BR>
     * 当メソッドの詳細は <BR>
     * 「基本設計計算式書（エクイティ）.doc」の「[●共通2]値幅算出」を参照。
     * @@param l_tradedProduct 取引銘柄<BR>
     * 　@　@　@値幅区分、強制値幅（上限値）、強制値幅（下限値）の取得、<BR>
     * 　@　@　@及びget制限値幅()引数設定に使用する。
     * @@param l_dblBasePrice 基準値<BR>
     * 　@　@　@get制限値幅()の引数に設定する基準値を指定する。
     * @@param l_intHighLowDivision 上限／下限区分 <BR>
     * 　@　@　@計算する値幅を、値幅上限算出／値幅下限算出　@<BR>
     * 　@　@　@のどちらに使用するのかを設定する。
     * @@return double
     * @@throws WEB3BaseException
     */
    protected double calcPriceRange(
        WEB3EquityTradedProduct l_tradedProduct,
        double l_dblBasePrice,
        int l_intHighLowDivision)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcPriceRange(WEB3GentradeTradedProduct, double, int)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_dblBasePrice < 0 || l_intHighLowDivision < 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        BigDecimal l_dbRange;
        BigDecimal l_bdResult;

        WEB3EquityProductManager l_productManager = null;
        l_productManager =
            (WEB3EquityProductManager) GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        EqtypeTradedProductParams l_params =
            (EqtypeTradedProductParams) l_tradedProduct.getDataSourceObject();

        // 制限値幅 ： 当機@能内で【値幅テーブル】より取得する
        BigDecimal l_bdDeregPriceRange =
            new BigDecimal(
                l_productManager.getDeregPriceRange(
                    l_tradedProduct,
                    l_dblBasePrice));

        // 値幅区分 ： （入力）取引銘柄．値幅区分
        String l_strPriceRangeId = l_params.getPriceRangeUnitType();
        if (l_strPriceRangeId == null)
        {
            l_strPriceRangeId = WEB3PriceRangeIdDef.DEFAULT;
        }
        
        //強制値幅（上限値） ： （入力）取引銘柄．強制値幅（上限値）
        BigDecimal l_bdHighCompulsivePriceRange =
            new BigDecimal(l_params.getHighCompulsivePriceRange());
        
        //強制値幅（下限値） ： （入力）取引銘柄．強制値幅（下限値）
        BigDecimal l_bdLowCompulsivePriceRange =
            new BigDecimal(l_params.getLowCompulsivePriceRange());

        //[「値幅区分」＝円　@の場合]
        if (l_strPriceRangeId.equals(WEB3PriceRangeIdDef.YEN))
        {
            //＜「上限／下限区分」＝「1：上限」　@の場合＞
            if (l_intHighLowDivision == WEB3MaxMinFlagDef.MAXIMUM)
            {
                //値幅＝「強制値幅（上限値）」
                l_dbRange = l_bdHighCompulsivePriceRange;
                log.debug("***値幅算出 ***    値幅 = 強制値幅（上限値）：" + l_dbRange);
            }
            //＜「上限／下限区分」＝「2：下限」　@の場合＞
            else
            {
                //値幅＝「強制値幅（下限値）」
                l_dbRange = l_bdLowCompulsivePriceRange;
                log.debug("***値幅算出 ***    値幅 = 強制値幅（下限値）：" + l_dbRange);
            }

        }
        //[「値幅区分」＝％　@の場合]
        else if (l_strPriceRangeId.equals(WEB3PriceRangeIdDef.PERCENT))
        {
            //＜「上限／下限区分」＝「1：上限」　@の場合＞
            if (l_intHighLowDivision == WEB3MaxMinFlagDef.MAXIMUM)
            {
                //値幅＝「制限値幅」×「強制値幅（上限値）」÷100
                l_bdResult =
                    l_bdDeregPriceRange.multiply(l_bdHighCompulsivePriceRange);
                l_dbRange =
                    (l_bdResult.divide(new BigDecimal(100), BigDecimal.ROUND_UP));
                log.debug("***値幅算出 ***    値幅 = 「制限値幅」×「強制値幅（上限値）」÷100  ： "
                    + l_dbRange);
            }
            //＜「⑥上限／下限区分」＝「2：下限」　@の場合＞
            else
            {
                //値幅＝「制限値幅」×「強制値幅（下限値）」÷100
                l_bdResult =
                    l_bdDeregPriceRange.multiply(l_bdLowCompulsivePriceRange);
                l_dbRange =
                    (l_bdResult.divide(new BigDecimal(100), BigDecimal.ROUND_UP));
                log.debug("***値幅算出 ***    値幅 ＝「制限値幅」×「強制値幅（下限値）」÷100  ： "
                    + l_dbRange);         
            }
        }
        //[「値幅区分」＝1/XX　@の場合]
        else if (l_strPriceRangeId.equals(WEB3PriceRangeIdDef.FRACTION))
        {
            //＜「上限／下限区分」＝「1：上限」　@の場合＞
            if (l_intHighLowDivision == WEB3MaxMinFlagDef.MAXIMUM)
            {
                //値幅＝「制限値幅」×1÷「強制値幅（上限値）」 
                l_dbRange =
                    (l_bdDeregPriceRange.divide(
                        l_bdHighCompulsivePriceRange,
                        BigDecimal.ROUND_UP));
                log.debug("***値幅算出 ***    値幅 ＝「制限値幅」×1÷「強制値幅（上限値）」  ： "
                    + l_dbRange);  
            }
            //  ＜「上限／下限区分」＝「2：下限」　@の場合＞
            else
            {
                //値幅＝「制限値幅」×1÷「強制値幅（下限値）」 
                l_dbRange =
                    (l_bdDeregPriceRange.divide(
                        l_bdLowCompulsivePriceRange,
                        BigDecimal.ROUND_UP));
                log.debug("***値幅算出 ***    値幅 ＝「制限値幅」×1÷「強制値幅（下限値）」   ： "
                    + l_dbRange);  
            }
        }
        //[「値幅区分」＝設定なし　@の場合]
        else
        {
            //値幅＝「①@制限値幅」
            l_dbRange = l_bdDeregPriceRange;
            log.debug("***値幅算出 ***    値幅 ＝「制限値幅」  ： " + l_dbRange);  
        }

        log.exiting(STR_METHOD_NAME);
        return l_dbRange.doubleValue();
    }

    /**
     * （calc基準値）<BR>
     * <BR>
     * 制限値幅を取得する際に使用する基準値を算出する。 <BR>
     * 引け後の場合、または（入力）取引銘柄．基準値 が設定されていない場合は、 <BR>
     * 終値テーブルや時価サーバから必要な値を取得して算出を行う。 <BR>
     * <BR>
     * 時価サーバからの値の取得はEqTypeQuoteDataインタフェースを使用して行う。 <BR>
     * EqTypeQuoteDataインタフェースのgetterが"0"を返した際には、 <BR>
     * 当該項目は「値付かずである」と判定する。 <BR>
     * <BR>
     * SONAR通知を受けての下り処理（出来通知、訂正取消通知等）は<BR>
     * 引け後に到着するものも場中の扱いで基準値を算出する必要がある。<BR>
     * LocalThreadの設定キー：BACK_SERVICE_IN_ONLINEの値==1（下り処理で必ず場中扱い）の<BR>
     * 場合は、市場閉局時間帯であっても開局時間帯扱いで処理を行う。<BR>
     * <BR>
     * 当メソッドの詳細は <BR>
     * 「基本設計計算式書（エクイティ）.doc」の「●共通5]基準値算出（）」を参照。 <BR>
     * @@param l_tradedProduct <BR>
     * 　@　@　@【株式取引銘柄マスターテーブル】からの基準値取得、<BR>
     * 　@　@　@または時価サーバからの現在値、売気配値、買気配値取得に<BR>
     * 　@　@　@使用する。
     * @@return double
     * @@throws WEB3BaseException
     */
    protected double calcBasePrice(WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcBasePrice(WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        EqTypeQuoteData l_eqTypeQuoteData;
        double l_dblBasePrice;
        
        //時価サーバより取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        try
        {
            l_eqTypeQuoteData =
                (EqTypeQuoteData)l_tradingModule.getQuoteDataSupplierService().getQuote(l_tradedProduct);
        }
        catch (NotFoundException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        boolean l_isTradeOpenTimeZone = false;
        String l_strBackSeriveOnline =
            (String)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE);
        if (l_strBackSeriveOnline != null)
        {
            if (WEB3EquityBackServiceOnlineDef.ONLINE.equals(l_strBackSeriveOnline))
            {
                l_isTradeOpenTimeZone = true;
            }
        }
        else
        {
            if (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
            {
                l_isTradeOpenTimeZone = true;
            }
        }
        //場中判定
        if (l_isTradeOpenTimeZone)
        {
            //以下の優先度で値が付いている値を基準値とする。
            //基準値(終値) ＞ 現在値 ＞ 売気配値 ＞ 買気配値

            // 取引銘柄.基準値
            l_dblBasePrice = 
                ((EqtypeTradedProductParams) l_tradedProduct.getDataSourceObject()).getBasePrice();
            if(l_dblBasePrice == 0)
            {
                //現在値
                l_dblBasePrice = l_eqTypeQuoteData.getCurrentPrice();
            }
            if (l_dblBasePrice == 0)
            {
                // 売気配値
                l_dblBasePrice = l_eqTypeQuoteData.getBidPrice();
            }
            if (l_dblBasePrice == 0)
            {
                // 買気配値
                l_dblBasePrice = l_eqTypeQuoteData.getAskPrice();
            }
        }
        else
        {
            EqTypeProduct l_product = (EqTypeProduct) l_tradedProduct.getProduct();

            // 発注日を取得
            String l_strOrderBizDate =
                ((EqtypeTradedProductParams)l_tradedProduct.getDataSourceObject()).getValidUntilBizDate().trim();

            Timestamp l_tsOrderBizDate = new Timestamp(
                WEB3DateUtility.getDate(l_strOrderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());  

            // 権利確定日取得
            Timestamp l_tsYearlyBooksClosingDate =
                ((EqtypeProductParams) l_product.getDataSourceObject()).getYearlyBooksClosingDate();

            //is権利落ち日
            boolean l_blnDevidendRightDate =
                this.isDevidendRightDate(
                    l_tsOrderBizDate,
                    l_tsYearlyBooksClosingDate);

            //プロセス管理テーブルに「0012：株式終値速報受信」データが存在する場合
            //或は権利落ち日（権利落ち日チェック結果＝true）
            //基準値＝（入力）取引銘柄．基準値
            WEB3EquityProductManager l_equityProductManager =
                (WEB3EquityProductManager) l_tradingModule.getProductManager();
            if (l_equityProductManager.isBasePriceRecCompleted(l_tradedProduct.getInstitutionCode())
                || l_blnDevidendRightDate)
            {
                l_dblBasePrice = ((EqtypeTradedProductParams)l_tradedProduct.getDataSourceObject()).getBasePrice();
            }
            else
            {
                //以下の優先度で値が付いている値を基準値とする。
                //当日終値　@＞　@現在値　@＞　@売気配値　@＞　@買気配値　@＞　@基準値（終値）
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();

                WEB3GentradeBizDate l_genBizDate =
                    new WEB3GentradeBizDate(l_tsOrderBizDate);
                
                // 当日終値
                l_dblBasePrice = l_productManager.getLastClosingPrice(
                    l_tradedProduct.getProduct().getProductId(),
                    l_tradedProduct.getMarketCode(),
                    l_genBizDate.roll(-1));
                    
                //現在値
                if (l_dblBasePrice == 0)
                {
                    l_dblBasePrice = l_eqTypeQuoteData.getCurrentPrice();
                }
                if (l_dblBasePrice == 0)
                {
                    // 売気配値
                    l_dblBasePrice = l_eqTypeQuoteData.getBidPrice();
                }
                if (l_dblBasePrice == 0)
                {
                    // 買気配値
                    l_dblBasePrice = l_eqTypeQuoteData.getAskPrice();
                }
                if (l_dblBasePrice == 0)
                {
                    //取引銘柄.基準値
                    l_dblBasePrice = 
                        ((EqtypeTradedProductParams) l_tradedProduct.getDataSourceObject()).getBasePrice();
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblBasePrice;      
    }

    /**
     * (validate注文訂正可能状態)<BR>
     * 訂正が可能な注文状態かをチェックする。<BR> 
     * （validateOrderForChangeability( )のオーバーライド）<BR> 
     * <BR>
     * this.validate注文訂正可能状態()に処理を委譲（delegate）する。<BR>  
     * <BR>
     * [validate注文訂正可能状態()に指定する引数] <BR> 
     * 　@注文：　@パラメータ.注文  <BR>
     * 　@isSkip遅延状況チェック：　@false（固定）<BR>
     * @@param l_order - 注文<BR>
     * @@throws OrderValidationException 
     */
    public void validateOrderForChangeability(Order l_order) 
        throws OrderValidationException
    {
        validateOrderForChangeability(l_order, false);
    }
    
    /**
     * （validate注文訂正可能状態）。<BR>
     * <BR>
     * 訂正が可能な注文状態かをチェックする。<BR>
     * <BR>
     * １）　@引数の注文オブジェクトより、注文単位オブジェクトを取得する。<BR>
     * 　@　@　@以下、取得した注文単位オブジェクトの0番目の要素を使用する。<BR>
     * <BR>
     * ２）　@以下の条件のいずれか１つにでも該当する場合は訂正不可とし、<BR>
     * 　@　@　@例外をthrowする。<BR>
     * <BR>
     * 　@　@（１）注文単位.注文カテゴリ == "現引現渡注文"の場合<BR>
     * 　@　@　@class:OrderValidationException<BR>
     * 　@　@　@tag  :BUSINESS_ERROR_00821<BR>
     * <BR>
     * 　@　@（２）注文単位.注文種別 == "現物買注文" かつ 取引コード（SONAR） == "立会外分売"の場合<BR>
     * 　@　@　@class:OrderValidationException<BR>
     * 　@　@　@tag  :BUSINESS_ERROR_01933<BR>
     * <BR>
     * ３）　@注文有効状態（order_open_status）、及び注文状態（order_status）のチェックを行う。<BR>
     * <BR>
     * ３－１） 注文有効状態のチェック<BR>
     * 　@　@　@・注文有効状態がOPEN以外の場合は訂正不可とし、<BR>
     * 　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@class:OrderValidationException<BR>
     * 　@　@　@tag  :BUSINESS_ERROR_00032<BR>
     * <BR>
     * ３－２） 注文状態のチェック<BR>
     * 市場開局／閉局によって以下の通りチェックを行う。<BR>
     * <BR>
     * ==========================================================================<BR>
     * －[市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）<BR>
     * 　@　@かつ　@取引所が取引中（取引時間管理.is取引所休憩時間帯( ) == false）の場合]<BR>
     * <BR>
     * 　@　@・注文状態が以下のいずれかに該当する場合は訂正不可とし、<BR>
     * 　@　@　@例外をthrowする。<BR>
     * 　@　@　@class:OrderValidationException<BR>
     * 　@　@　@tag  :BUSINESS_ERROR_00032<BR>
     * 　@　@　@------------------------------------------------<BR>
     * 　@　@　@ACCEPTED(*1)<BR>
     * 　@　@　@CANCEL_ACCEPTED<BR>
     * 　@　@　@CANCELLING<BR>
     * 　@　@　@MODIFY_ACCEPTED<BR>
     * 　@　@　@MODIFYING<BR>
     * 　@　@　@ORDERING<BR>
     * 　@　@　@------------------------------------------------<BR>
     * 　@　@　@(*1)注文単位.発注条件＝"逆指値"の場合のみは、訂正可能とする。<BR>
     * <BR>
     * 　@　@・注文単位.市場から確認済みの数量 == NaN（＝市場受付未済）の場合は訂正不可とし、<BR>
     * 　@　@　@例外をthrowする。<BR>
     * 　@　@　@class:OrderValidationException<BR>
     * 　@　@　@tag  :BUSINESS_ERROR_00032<BR>
     * 　@　@　@※注文単位.発注条件≠"逆指値"の場合のみ。<BR>
     * <BR>
     * 　@　@・注文単位.値段条件 == （"現在値指値注文", "優先値指値注文"）のいずれかの場合のみ、<BR>
     * 　@　@　@注文単位.市場から確認済みの指値 == 0（＝値段未確定） の場合は訂正不可とし、<BR>
     * 　@　@　@「値段条件付き注文・値段未確定」の例外をthrowする。<BR>
     * 　@　@　@class:OrderValidationException<BR>
     * 　@　@　@tag  :BUSINESS_ERROR_01340<BR>
     * <BR>
     * ==========================================================================<BR>
     * －[市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）<BR>
     * 　@　@かつ　@取引所が休憩時間中（取引時間管理.is取引所休憩時間帯( ) == true）の場合]<BR>
     * <BR>
     * 　@　@・注文状態が以下のいずれかに該当する場合は訂正不可とし、<BR>
     * 　@　@　@例外をthrowする。<BR>
     * 　@　@　@class:OrderValidationException<BR>
     * 　@　@　@tag  :BUSINESS_ERROR_00032<BR>
     * 　@　@　@------------------------------------------------<BR>
     * 　@　@　@CANCEL_ACCEPTED<BR>
     * 　@　@　@CANCELLING<BR>
     * 　@　@　@MODIFYING<BR>
     * 　@　@　@------------------------------------------------<BR>
     * <BR>
     * 　@　@・注文状態がMODIFY_ACCEPTEDの場合、<BR>
     * 　@　@　@　@this.validate市場送信済注文複数回訂正可能（休憩時間中）()をコールする。<BR>
     * 　@　@　@　@引数設定：　@注文単位．部店ID<BR>
     * <BR>
     * 　@　@・株式発注サービス.is市場通知中注文IN休憩時間帯() == true（＝通知中）の場合は<BR>
     * 　@　@　@訂正不可とし、例外をthrowする。<BR>
     * 　@　@　@class:OrderValidationException<BR>
     * 　@　@　@tag  :BUSINESS_ERROR_00032<BR>
     * <BR>
     * ==========================================================================<BR>
     * －[市場閉局時間帯（取引時間管理.is市場開局時間帯( ) == false）の場合]<BR>
     * <BR>
     *  　@　@・注文状態が以下のいずれかに該当する場合は訂正不可とし、<BR>
     * 　@　@　@例外をthrowする。<BR>
     * 　@　@　@class:OrderValidationException<BR>
     * 　@　@　@tag  :BUSINESS_ERROR_00032<BR>
     * 　@　@　@------------------------------------------------<BR>
     * 　@　@　@CANCEL_ACCEPTED<BR>
     * 　@　@　@CANCELLING<BR>
     * 　@　@　@MODIFY_ACCEPTED<BR>
     * 　@　@　@MODIFYING<BR>
     * 　@　@　@ORDERING<BR>
     * 　@　@　@------------------------------------------------<BR>
     * <BR>
     * ==========================================================================<BR>
     * <BR>
     * ４）　@市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）<BR>
     * 　@　@　@かつ　@市場発注済の注文(*2)<BR>
     * 　@　@　@の場合のみ、<BR>
     * 　@　@　@訂正対象注文の発注経路が訂正可能かどうかチェックする。<BR>
     * <BR>
     * 　@　@(*2)市場発注済の注文<BR>
     * 　@　@　@　@注文単位.市場から確認済みの数量≠NaNの場合、<BR>
     * 　@　@　@　@市場発注済の注文と判定する。<BR>
     * <BR>
     * ４－１）　@株式発注サービス.get発注先切替()にて、発注先切替クラスを取得する。<BR>
     * <BR>
     * 　@　@　@------------------------------------------------<BR>
     * 　@　@　@＜get発注先切替()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@注文単位：　@１）で取得した注文単位<BR>
     * 　@　@　@------------------------------------------------<BR>
     * <BR>
     * ４－２）　@４－１）の戻り値==nullの場合(*3)は、何もせずにそのままreturnする。<BR>
     * 　@　@　@　@　@４－１）で取得したインスタンス.is訂正取消可能( )==falseの場合は訂正不可とし、<BR>
     * 　@　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@class:OrderValidationException<BR>
     * 　@　@　@tag  :BUSINESS_ERROR_00032<BR>
     * <BR>
     * 　@　@(*3)戻り値==nullの場合：以下が該当する。<BR>
     * 　@　@　@　@－訂正対象の注文単位.発注経路区分=="発注停止"の場合<BR>
     * 　@　@　@　@－訂正対象の注文単位が、フロント発注対応市場に対する注文で、<BR>
     * 　@　@　@　@　@かつ　@SONAR入力注文の場合<BR>
     * 　@　@　@　@　@（SONAR入力注文の場合、発注経路区分にはSONARの経路が設定されている）<BR>
     * <BR>
     * ５）　@遅延状況のチェック <BR>
     * 　@引数.isSkip遅延状況チェック == false の場合のみ、以下処理を行う。<BR> 
     * 　@（W指値切替処理からコールされた場合のみスキップする） <BR>
     * <BR>
     * 　@拡張株式注文マネージャ.is未発注遅延注文() == true の場合 <BR>
     * 　@訂正不可とし、例外をthrowする。 <BR>
     * 　@　@　@class:OrderValidationException<BR>
     * 　@　@　@tag  :BUSINESS_ERROR_00032<BR>
     * <BR>
     * 　@　@　@------------------------------------------------ <BR>
     * 　@　@　@＜is未発注遅延注文()：引数設定仕様＞ <BR>
     * <BR>
     * 　@　@　@注文単位：　@１）で取得した注文単位 <BR>
     * 　@　@　@------------------------------------------------<BR>
     * <BR>
     * ６）　@強制決済注文のチェック<BR>
     * 　@６－１）　@処理対象の判定<BR>
     * 　@　@拡張株式注文マネージャ.is強制決済注文() == trueの場合、以下処理を行う。<BR>
     * <BR>
     * 　@  [引数]<BR>
     * 　@　@　@注文単位：　@１）で取得した注文単位<BR>
     * <BR>
     * 　@６－２）　@代理入力者の取得<BR>
     * 　@　@OpLoginSecurityServiceを取得し、ログインＩＤを取得する。<BR>
     * 　@　@取得したログインIDから、代理入力者を取得する。<BR>
     * 　@　@　@※例外はcatchして処理を続けること。<BR>
     * <BR>
     * 　@６－３）　@訂正可能チェック<BR>
     * 　@　@６－３－１）　@扱者ログインの場合<BR>
     * 　@　@　@代理入力者が取得出来た場合、以下の処理を行う。<BR>
     * <BR>
     * 　@　@　@　@拡張株式注文マネージャ.is未承認強制決済注文() == trueの場合、<BR>
     * 　@　@　@　@「代理入力者は、未承認の強制決済注文は訂正できません」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@: BUSINESS_ERROR_02811<BR>
     * <BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@注文単位：　@１）で取得した注文単位<BR>
     * <BR>
     * 　@　@６－３－２）　@以外の場合<BR>
     * 　@　@　@「強制決済注文は訂正できません」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@: BUSINESS_ERROR_02811<BR>
     * <BR>
     * @@param l_order 注文<BR>
     * @@param l_blnIsSkipDelayCheck isSkip遅延状況チェック<BR>
     * @@throws OrderValidationException
     */
    public void validateOrderForChangeability(Order l_order, boolean l_blnIsSkipDelayCheck)
        throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(Order, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_order == null)
        { 
            throw new OrderValidationException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }

        // １）　@引数の注文オブジェクトより、注文単位オブジェクトを取得する。
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        OrderUnit l_orderUnit = l_orderUnits[0];

        // ２）　@以下の条件のいずれか１つにでも該当する場合は訂正不可とし、
        //        例外をthrowする。
        // 　@１）注文単位.注文カテゴリ == "現引現渡注文"の場合
        if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()))
        {
            throw new OrderValidationException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00821);           
        }
        
        // 　@２）注文単位.注文種別 == "現物買注文" かつ 取引コード（SONAR） == "立会外分売"の場合
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
        if (l_orderUnit.getOrderType().equals(OrderTypeEnum.EQUITY_BUY)
                && l_orderUnitRow.getSonarTradedCode().equals(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET))
        {
            throw new OrderValidationException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01933);           
        }

        // ３）　@注文有効状態（order_open_status）、及び注文状態（order_status）のチェックを行う。
        // ３－１） 注文有効状態のチェック
        // 　@　@　@・注文有効状態がOPEN以外の場合は訂正不可とし、例外をthrowする。
        if (!OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus()))
        {
            throw new OrderValidationException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00032);
        }

        // ３－２） 注文状態のチェック
        //  市場開局／閉局によって以下の通りチェックを行う。<BR>
        //場中判定
        boolean l_result;
        try
        {
            l_result = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        }
        catch (WEB3SystemLayerException l_exp)
        {
            throw new OrderValidationException(l_exp.getErrorInfo());
        }

        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        int l_intStatus = l_orderUnit.getOrderStatus().intValue();
        // －[市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）の場合]
        if (l_result)
        {
            try
            {
                if (!WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
                {
                    if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                       || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                       || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                       || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                       || (l_intStatus == OrderStatusEnum.IntValues.ORDERING)) 
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }
                    // 注文状態がACCEPTEDで、発注条件が"逆指値"の場合のみ、訂正可能。
                    if (l_intStatus == OrderStatusEnum.IntValues.ACCEPTED &&
                        !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }
                    if (l_orderUnitRow.getConfirmedQuantity() == 0.0D &&
                        !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }
                    //  　@　@・注文単位.値段条件 == （"現在値指値注文", "優先値指値注文"）のいずれかの場合のみ、
                    // 　@　@　@注文単位.市場から確認済みの指値 == 0（＝値段未確定） の場合は訂正不可とし、
                    // 　@　@　@「値段条件付き注文・値段未確定」の例外をthrowする。
                    if (WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(l_orderUnitRow.getPriceConditionType()) ||
                        WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(l_orderUnitRow.getPriceConditionType()))
                    {
                        if ((l_orderUnitRow.getConfirmedPriceIsNull() == true)
                        || (l_orderUnitRow.getConfirmedPrice() == 0))
                        {
                            throw new OrderValidationException(
                                 WEB3ErrorCatalog.BUSINESS_ERROR_01340);
                        }
                    }
                }
                else
                {
                    if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                       || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                       || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING))

                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }

                    if (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED) 
                    {
                        this.validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(l_orderUnit.getBranchId());
                    }

                    if (l_frontOrderService.isMarketNotifyingOrderInBreakTimeZone((EqTypeOrderUnit)l_orderUnit))
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }
                }
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new OrderValidationException(l_wbe.getErrorInfo());
            }
        }
        // －[市場閉局時間帯（取引時間管理.is市場開局時間帯( ) == false）の場合]
        else
        {
            if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
               || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
               || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
               || (l_intStatus == OrderStatusEnum.IntValues.ORDERING)
               || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)) 
            {
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }
        
        if (l_result && l_orderUnitRow.getConfirmedQuantity() != 0.0D)
        {
            WEB3GentradeOrderSwitching l_orderSwitching = null;
            try
            {
                l_orderSwitching =
                    l_frontOrderService.getOrderSwitching((EqTypeOrderUnit)l_orderUnit);
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new OrderValidationException(l_wbe.getErrorInfo());
            }
            if (l_orderSwitching != null && !l_orderSwitching.isChangeCancelEnable())
            {
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }

        //５）　@遅延状況のチェック
        //　@引数.isSkip遅延状況チェック == false の場合のみ、以下処理を行う。
        //　@（W指値切替処理からコールされた場合のみスキップする）
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

        if (!l_blnIsSkipDelayCheck)
        {
            //　@拡張株式注文マネージャ.is未発注遅延注文() == true の場合
            //　@訂正不可とし、例外をthrowする。
            boolean l_blnIsNotOrderedDelayOrder = false;
            try
            {
                l_blnIsNotOrderedDelayOrder =
                    l_orderManager.isNotOrderedDelayOrder((EqTypeOrderUnit)l_orderUnit);
            }
            catch (WEB3BaseException l_wbe)
            {
                log.debug("exception in l_orderManager.isNotOrderedDelayOrder()");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(l_wbe.getErrorInfo());
            }

            if (l_blnIsNotOrderedDelayOrder)
            {
                log.debug("拡張株式注文マネージャ.is未発注遅延注文() == true の場合"
                    + " 訂正不可とし、例外をthrowする。");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }

        // ６－１）　@処理対象の判定
        // 拡張株式注文マネージャ.is強制決済注文() == trueの場合、以下処理を行う。
        boolean l_blnIsForcedSettleOrder =
            l_orderManager.isForcedSettleOrder((EqTypeOrderUnit)l_orderUnit);
        if (l_blnIsForcedSettleOrder)
        {
            // ６－２）　@代理入力者の取得
            // OpLoginSecurityServiceを取得し、ログインＩＤを取得する。
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            long l_lngLoginId = l_opLoginSecurityService.getLoginId();
            Trader l_trader = null;
            try
            {
                // 取得したログインIDから、代理入力者を取得する。
                l_trader = l_finApp.getFinObjectManager().getTraderByLoginId(l_lngLoginId);
            }
            catch (NotFoundException l_ex)
            {
                // 例外はcatchして処理を続けること。
                log.debug("例外はcatchして処理を続けること。");
            }

            // ６－３－１）　@扱者ログインの場合
            // 代理入力者が取得出来た場合、以下の処理を行う。
            // 拡張株式注文マネージャ.is未承認強制決済注文()
            if (l_trader != null)
            {
                boolean l_blnIsApproveForcedSettleOrder =
                    l_orderManager.isApproveForcedSettleOrder((EqTypeOrderUnit)l_orderUnit);
                if (l_blnIsApproveForcedSettleOrder)
                {
                    log.debug("代理入力者は、未承認の強制決済注文は訂正できません");
                    log.exiting(STR_METHOD_NAME);
                    throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02810);
                }
            }
            else
            {
                // ６－３－２）　@以外の場合
                //「強制決済注文は訂正できません」の例外をthrowする。
                log.debug("強制決済注文は訂正できません");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02811);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （validate注文取消可能状態）。<BR>
     * <BR>
     * 注文の取消が可能か注文状態であるかどうかをチェックする。 <BR>
     * （validateOrderForCancellation( )のオーバーライド） <BR>
     * <BR>
     * １）　@引数の注文オブジェクトより、注文単位オブジェクトを取得する。 <BR>
     * 　@　@　@以下、取得した注文単位オブジェクトの0番目の要素を使用する。 <BR>
     * <BR>
     * ２）　@注文有効状態（order_open_status）、 <BR>
     * 及び注文状態（order_status）のチェックを行う。 <BR>
     * <BR>
     * ２－１） 注文有効状態のチェック <BR>
     * 　@　@　@・当日発注分の、全部約定によりクローズ済の現引現渡注文(*)以外の場合で、<BR> 
     * 　@　@　@　@注文有効状態がCLOSEDの場合は取消不可とし、例外をthrowする。<BR>
     * <BR>
     * 　@　@　@　@(*)注文単位.注文カテゴリ == ”現引・現渡注文”、かつ、 <BR>
     * 　@　@　@　@注文単位.isFullyExecuted == true、かつ、<BR>
     * 　@　@　@　@注文単位.発注日 == 取引時間管理.get発注日() の場合<BR>
     * 　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag   : BUSINESS_ERROR_00820<BR>
     * <BR> 
     * 　@　@　@　@※当日発注分の現引現渡の全部約定注文の場合、当該チェックは行わない。 <BR>
     * 　@　@　@　@(注文有効状態はCLOSEDでも取消可) <BR>
     * <BR>
     * ２－２） 注文状態のチェック<BR>
     * 市場開局／閉局によって以下の通りチェックを行う。<BR>
     * <BR>
     * ==========================================================================<BR>
     * －[市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）<BR>
     * 　@　@かつ　@取引所が取引中（取引時間管理.is取引所休憩時間帯( ) == false）の場合]<BR>
     * <BR>
     * 　@　@　@・注文状態が以下のいずれかに該当する場合は取消不可とし、<BR>
     * 　@　@　@例外をthrowする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00032<BR>
     * 　@　@　@------------------------------------------------<BR>
     * 　@　@　@ACCEPTED(*1)<BR>
     * 　@　@　@CANCEL_ACCEPTED<BR>
     * 　@　@　@CANCELLING<BR>
     * 　@　@　@MODIFY_ACCEPTED<BR>
     * 　@　@　@MODIFYING<BR>
     * 　@　@　@ORDERING<BR>
     * 　@　@　@------------------------------------------------<BR>
     * 　@　@　@(*1)以下のいずれかの条件に合致する場合は、取消可能とする。<BR>
     * 　@　@　@　@　@　@・注文単位.発注条件＝"逆指値"の場合<BR>
     * 　@　@　@　@　@　@・注文単位.取引コード（SONAR）＝"立会外分売"の場合<BR>
     * <BR>
     * 　@　@・注文単位.市場から確認済みの数量 == NaN（＝市場受付未済）の場合は取消不可とし、<BR>
     * 　@　@　@例外をthrowする。<BR>
     * 　@　@　@※(*1)の場合は取消可能とする。<BR>
     * <BR>
     * 　@　@・注文単位.値段条件 == （"現在値指値注文", "優先値指値注文"）のいずれかの場合のみ、<BR>
     * 　@　@　@注文単位.市場から確認済みの指値 == 0（＝値段未確定） の場合は取消不可とし、<BR>
     * 　@　@　@「値段条件付き注文・値段未確定」の例外をthrowする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01340<BR>
     * <BR>
     * ==========================================================================<BR>
     * －[市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）<BR>
     * 　@　@かつ　@取引所が休憩時間中（取引時間管理.is取引所休憩時間帯( ) == true）の場合]<BR>
     * <BR>
     * 　@　@・注文状態が以下のいずれかに該当する場合は取消不可とし、<BR>
     * 　@　@　@例外をthrowする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00032<BR>
     * 　@　@　@------------------------------------------------<BR>
     * 　@　@　@CANCEL_ACCEPTED<BR>
     * 　@　@　@CANCELLING<BR>
     * 　@　@　@MODIFYING<BR>
     * 　@　@　@------------------------------------------------<BR>
     * <BR>
     * 　@　@・注文状態がMODIFY_ACCEPTEDの場合、<BR>
     * 　@　@　@　@this.validate市場送信済注文複数回訂正可能（休憩時間中）()をコールする。<BR>
     * 　@　@　@　@引数設定：　@注文単位．部店ID<BR>
     * <BR>
     * 　@　@・株式発注サービス.is市場通知中注文IN休憩時間帯() == true（＝通知中）の場合は<BR>
     * 　@　@　@取消不可とし、例外をthrowする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00032<BR>
     * <BR>
     * ==========================================================================<BR>
     * －[市場閉局時間帯（取引時間管理.is市場開局時間帯( ) == false）の場合]<BR>
     * <BR>
     *  　@　@・注文状態が以下のいずれかに該当する場合は取消不可とし、<BR>
     * 　@　@　@例外をthrowする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00032<BR>
     * 　@　@　@------------------------------------------------<BR>
     * 　@　@　@CANCEL_ACCEPTED<BR>
     * 　@　@　@CANCELLING<BR>
     * 　@　@　@MODIFY_ACCEPTED<BR>
     * 　@　@　@MODIFYING<BR>
     * 　@　@　@ORDERING<BR>
     * 　@　@　@------------------------------------------------<BR>
     * <BR>
     * ３）　@市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）<BR>
     * 　@　@　@かつ　@市場発注済の注文(*2)<BR>
     * 　@　@　@の場合のみ、<BR>
     * 　@　@　@取消対象注文の発注経路が取消可能かどうかチェックする。<BR>
     * <BR>
     * 　@　@(*2)市場発注済の注文<BR>
     * 　@　@　@　@注文単位.市場から確認済みの数量≠NaNの場合、<BR>
     * 　@　@　@　@市場発注済の注文と判定する。<BR>
     * <BR>
     * ３－１）　@株式発注サービス.get発注先切替()にて、発注先切替クラスを取得する。<BR>
     * <BR>
     * 　@　@　@------------------------------------------------<BR>
     * 　@　@　@＜get発注先切替()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@注文単位：　@１）で取得した注文単位<BR>
     * 　@　@　@------------------------------------------------<BR>
     * <BR>
     * ３－２）　@３－１）の戻り値==nullの場合(*3)は、何もせずにそのままreturnする。<BR>
     * 　@　@　@　@　@３－１）で取得したインスタンス.is訂正取消可能( )==falseの場合は取消不可とし、<BR>
     * 　@　@　@　@　@例外をthrowする。<BR>
     * <BR>
     * 　@　@(*3)戻り値==nullの場合：以下が該当する。<BR>
     * 　@　@　@　@－取消対象の注文単位.発注経路区分=="発注停止"の場合<BR>
     * 　@　@　@　@－取消対象の注文単位が、フロント発注対応市場に対する注文で、<BR>
     * 　@　@　@　@かつ　@SONAR入力注文の場合<BR>
     * 　@　@　@　@（SONAR入力注文の場合、発注経路区分にはSONARの経路が設定されている）<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00032<BR>
     * <BR>
     * ４）　@強制決済注文のチェック<BR>
     * 　@４－１）　@処理対象の判定<BR>
     * 　@　@拡張株式注文マネージャ.is強制決済注文() == trueの場合、以下処理を行う。<BR>
     * <BR>
     * 　@  [引数]<BR>
     * 　@　@　@注文単位：　@１）で取得した注文単位<BR>
     * <BR>
     * 　@４－２）　@代理入力者の取得<BR>
     * 　@　@OpLoginSecurityServiceを取得し、ログインＩＤを取得する。<BR>
     * 　@　@取得したログインIDから、代理入力者を取得する。<BR>
     * 　@　@　@※例外はcatchして処理を続けること。<BR>
     * <BR>
     * 　@４－３）　@取消可能チェック<BR>
     * 　@　@４－３－１）　@扱者ログインの場合<BR>
     * 　@　@　@代理入力者が取得出来た場合、以下の処理を行う。<BR>
     * <BR>
     * 　@　@　@　@拡張株式注文マネージャ.is未承認強制決済注文() == trueの場合、<BR>
     * 　@　@　@　@「代理入力者は、未承認の強制決済注文は取消できません」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@: BUSINESS_ERROR_02840<BR>
     * <BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@注文単位：　@１）で取得した注文単位<BR>
     * <BR>
     * 　@　@４－３－２）　@以外の場合<BR>
     * 　@　@　@「強制決済注文は取消できません」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@: BUSINESS_ERROR_02841<BR>
     * <BR>
     * @@param l_order 注文
     * @@throws OrderValidationException
     */
    public void validateOrderForCancellation(Order l_order)
        throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validateOrderForCancellation(Order)";
        log.entering(STR_METHOD_NAME);
        if (l_order == null)
        {
            throw new OrderValidationException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        //引数の注文オブジェクトより、注文単位オブジェクトを取得する
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        if (l_orderUnits.length != 1)
        {
            // 注文単位オブジェクト数が１以外。
            log.error(
                STR_METHOD_NAME
                    + "ERROR：注文ID：["
                    + l_order.getOrderId()
                    + "] 注文単位オブジェクト数：["
                    + l_orderUnits.length
                    + "]");
            throw new OrderValidationException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        OrderUnit l_orderUnit = l_orderUnits[0];
        
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
     
        // 注文有効状態のチェック
        // 注文有効状態がCLOSEDの場合は取消不可とし、例外をthrowする。
        // （ただし、全部約定によりCLOSE済の現引現渡注文については、当日発注分に限り取消可とする）
        Date l_datOrderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
        try
        {
            if ((OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()) == true) &&
                (l_orderUnit.isFullyExecuted() == true) &&
                (l_datOrderBizDate.equals(WEB3GentradeTradingTimeManagement.getOrderBizDate()) == true))
            {
                ;
            }
            else
            {
                if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnitRow.getOrderOpenStatus()))
                {
                    throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00820);
                }
            }
        }
        catch (WEB3BaseException l_bex)
        {
            throw new OrderValidationException(
                l_bex.getErrorInfo());            
        }

        //注文状態のチェック
        //市場開局／閉局によって以下の通りチェックを行う
        //場中判定
        boolean l_result;
        try
        {
            l_result =
                (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone());
        }
        catch (WEB3SystemLayerException e)
        {
            throw new OrderValidationException(e.getErrorInfo());
        }
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        int l_intStatus = l_orderUnit.getOrderStatus().intValue();
        if (l_result) //取引時間管理.is市場開局時間帯( ) == true
        {
            try
            {
                if (!WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
                {
                    //注文単位.発注条件を取得
                    String l_orderConditionType =
                        l_orderUnitRow.getOrderConditionType();
                    String l_sonarTradedCode = l_orderUnitRow.getSonarTradedCode();
                    if (((l_intStatus == OrderStatusEnum.IntValues.ACCEPTED)
                        && (!l_orderConditionType.equals(WEB3OrderingConditionDef.STOP_LIMIT_PRICE))
                        && (!l_sonarTradedCode.equals(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET)))
                        || (l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                        || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                        || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                        || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                        || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }
                    if (l_orderUnitRow.getConfirmedQuantity() == 0.0D &&
                        !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()) &&
                        !WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_sonarTradedCode))
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }
                    if(WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(l_orderUnitRow.getPriceConditionType()) ||
                        WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(l_orderUnitRow.getPriceConditionType()))
                    {
                        if ((l_orderUnitRow.getConfirmedPriceIsNull() == true)
                        || (l_orderUnitRow.getConfirmedPrice() == 0))
                        {
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01340);
                        }
                    }
                }
                else
                {
                    if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                       || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                       || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)) 
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }

                    if (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED) 
                    {
                        this.validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(l_orderUnit.getBranchId());
                    }
                    
                    if (l_frontOrderService.isMarketNotifyingOrderInBreakTimeZone((EqTypeOrderUnit)l_orderUnit))
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }
                }
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new OrderValidationException(l_wbe.getErrorInfo());
            }
        }
        else //取引時間管理.is市場開局時間帯( ) == false
            {
            if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
            {
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }
        
        if (l_result && l_orderUnitRow.getConfirmedQuantity() != 0.0D)
        {
            WEB3GentradeOrderSwitching l_orderSwitching = null;
            try
            {
                l_orderSwitching =
                    l_frontOrderService.getOrderSwitching((EqTypeOrderUnit)l_orderUnit);
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new OrderValidationException(l_wbe.getErrorInfo());
            }
            if (l_orderSwitching != null && !l_orderSwitching.isChangeCancelEnable())
            {
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }

        // ４－１）　@処理対象の判定
        // 拡張株式注文マネージャ.is強制決済注文() == trueの場合、以下処理を行う。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        boolean l_blnIsForcedSettleOrder =
            l_orderManager.isForcedSettleOrder((EqTypeOrderUnit)l_orderUnit);
        if (l_blnIsForcedSettleOrder)
        {
            // ４－２）　@代理入力者の取得
            // OpLoginSecurityServiceを取得し、ログインＩＤを取得する。
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            long l_lngLoginId = l_opLoginSecurityService.getLoginId();
            Trader l_trader = null;
            try
            {
                // 取得したログインIDから、代理入力者を取得する。
                l_trader = l_finApp.getFinObjectManager().getTraderByLoginId(l_lngLoginId);
            }
            catch (NotFoundException l_ex)
            {
                // 例外はcatchして処理を続けること。
                log.debug("例外はcatchして処理を続けること。");
            }

            // ４－３－１）　@扱者ログインの場合
            // 代理入力者が取得出来た場合、以下の処理を行う。
            // 拡張株式注文マネージャ.is未承認強制決済注文()
            if (l_trader != null)
            {
                boolean l_blnIsApproveForcedSettleOrder =
                    l_orderManager.isApproveForcedSettleOrder((EqTypeOrderUnit)l_orderUnit);
                if (l_blnIsApproveForcedSettleOrder)
                {
                    log.debug("代理入力者は、未承認の強制決済注文は取消できません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new OrderValidationException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02840);
                }
            }
            else
            {
                // ４－３－２）　@以外の場合
                //「強制決済注文は訂正できません」の例外をthrowする。
                log.debug("強制決済注文は取消できません。");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02841);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （calc時価）。<BR>
     * <BR>
     * 時価を算出する。 <BR>
     * 算出した時価は計算単価として、 <BR>
     * calc概算受渡代金()等で使用する。 <BR>
     * <BR>
     * 当メソッドの詳細は <BR>
     * 「基本設計計算式書（エクイティ）.doc」の「[3]時価算出」を参照。 <BR>
     * 手続きシーケンスについては「（注文）calc時価」を参照。 <BR>
     * <BR>
     * isSTOP高考慮＝trueの場合、<BR>
     * 引数の手数料商品コード＝nullだったら例外をthrow<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00736
     * @@param l_strCommissionProductCode 手数料商品コード<BR>
     * 　@　@　@【会社部店商品テーブル】検索時に使用する。<BR> 
     * <BR>
     * 　@　@　@引数の「isSTOP高考慮」＝falseの場合は、【会社部店商品テーブル】を参照しないため、<BR> 
     * 　@　@　@null設定も可。
     * @@param l_tradedProduct 取引銘柄<BR>
     * 　@　@　@【株式取引銘柄テーブル】からの時価取得に使用する。
     * @@param l_subAccount 補助口座<BR>
     * 　@　@　@【会社部店商品テーブル】からの「概算金額計算方式」取得時の「部店ID」の指定に使用する。
     * @@param l_isStopPriceConsideration isSTOP高考慮<BR>
     * 　@　@　@【会社部店商品テーブル】概算金額計算方式＝STOP高拘束 の設定を<BR>
     * 　@　@　@考慮するかどうかのフラグ。<BR> 
     * 　@　@　@概算金額計算方式 の設定をそのまま使用する場合はtrueを、<BR> 
     * 　@　@　@無視する場合はfalseを、それぞれ設定する。
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcCurrentPrice(
        String l_strCommissionProductCode,
        WEB3EquityTradedProduct l_tradedProduct,
        WEB3GentradeSubAccount l_subAccount,
        boolean l_isStopPriceConsideration)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcCurrentPrice(String, WEB3EquityTradedProduct, SubAccount, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null
            || l_subAccount == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (l_isStopPriceConsideration && l_strCommissionProductCode == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
                     
        EqTypeQuoteData l_eqTypeQuoteData;
        double l_dblResultPrice = 0D;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule =
            (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityProductManager l_equityProductManager =
            (WEB3EquityProductManager) l_tradingModule.getProductManager();

        boolean l_priceFlg = true;
        
        //1.1 引数のisSTOP高考慮==true（考慮要）の場合のみ
        if (l_isStopPriceConsideration)
        {
            //1.1.1 get概算金額計算方式(String, 補助口座)
            long l_lngEstimatePriceCalcForm =
                this.getEstimatePriceCalcForm(
                    l_strCommissionProductCode,
                    l_subAccount);

            
            //1.1.2取得した概算受渡代金計算方式＝STOP高拘束方式の場合のみ
            if (WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT == l_lngEstimatePriceCalcForm)
            {
                //1.1.2.1 getProduct()
                EqTypeProduct l_product = (EqTypeProduct) l_tradedProduct.getProduct();            

                // 権利確定日取得
                Timestamp l_tsYearlyBooksClosingDate =
                    ((EqtypeProductParams) l_product.getDataSourceObject()).getYearlyBooksClosingDate();
        
                // 発注日取得
                String l_strOrderBizDate =
                    ((EqtypeTradedProductParams) l_tradedProduct.getDataSourceObject()).getValidUntilBizDate().trim();
                Timestamp l_tsOrderBizDate = new Timestamp(
                    WEB3DateUtility.getDate(l_strOrderBizDate, "yyyyMMdd").getTime());    
            
                //1.1.2.2 is権利落ち日
                boolean l_blnDevidendRightDate =
                    this.isDevidendRightDate(
                        l_tsOrderBizDate,
                        l_tsYearlyBooksClosingDate);
                        
                if (l_blnDevidendRightDate)
                {
                    //発注日（（入力）取引銘柄.有効日）
                    Date l_datOrderBizDate = WEB3DateUtility.getDate(
                        l_strOrderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);

                    //受付日時の翌営業日
                    Timestamp l_orderTime = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
                    Date l_datGentradeBizDate =
                        new WEB3GentradeBizDate(l_orderTime).roll(1);
                    String l_strBizDateType = 
                        WEB3GentradeTradingTimeManagement.getBizDateType(l_orderTime);

                    //発注日（（入力）取引銘柄.有効日）＝受付日時の翌営業日であると判断する
                    //受付日時は営業日である。
                    //拡張プロダクトマネージャ .is翌日基準値受信（取引銘柄.証券会社コード）
                    if (WEB3DateUtility.compare(WEB3DateUtility.toDay(l_datOrderBizDate),
                        WEB3DateUtility.toDay(l_datGentradeBizDate)) == 0 &&
                        !WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) &&
                        !l_equityProductManager.isNextDayBasePriceMail(l_tradedProduct.getInstitutionCode()))
                    {
                        l_priceFlg = true;
                    }
                    else
                    {
                        l_priceFlg = false;
                    }

                }
                else
                {
                    l_priceFlg = false;
                }
            }
            else
            {
                l_priceFlg = true;
            }
        }
        
        // 拡張プロダクトマネージャ取得
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager) GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();     
            
        boolean l_isMarketOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        // 市場閉局時間帯の場合
        double l_dblLastClosingPrice = 0D;
        if (l_isMarketOpenTimeZone == false)
        {
            // 発注日を取得
            String l_strOrderBizDate =
                ((EqtypeTradedProductParams) l_tradedProduct.getDataSourceObject()).getValidUntilBizDate().trim();
            WEB3GentradeBizDate l_genBizDate =
                new WEB3GentradeBizDate(
                    new Timestamp(WEB3DateUtility.getDate(l_strOrderBizDate, "yyyyMMdd").getTime()));
            
            // 当日終値を取得
            l_dblLastClosingPrice = l_productManager.getLastClosingPrice(
                l_tradedProduct.getProduct().getProductId(),
                l_tradedProduct.getMarketCode(),
                l_genBizDate.roll(-1));
        }
        
        if (l_priceFlg == true)
        {
            try
            {
                l_eqTypeQuoteData =
                    (EqTypeQuoteData) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getQuoteDataSupplierService().getQuote(
                        l_tradedProduct);
            }
            catch (NotFoundException nfe)
            {
                log.error(STR_METHOD_NAME,nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            }
            log.debug("*** 当日終値=" + l_dblLastClosingPrice);
            log.debug("*** 現在値=" + l_eqTypeQuoteData.getCurrentPrice());
            log.debug("*** 売気配値=" + l_eqTypeQuoteData.getBidPrice());
            log.debug("*** 買気配値=" + l_eqTypeQuoteData.getAskPrice());
            log.debug("*** 終値=" + l_tradedProduct.getLastClosingPrice());

            // 当日終値
            l_dblResultPrice = l_dblLastClosingPrice;
            if (l_dblResultPrice == 0)
            {
                // 現在値
                l_dblResultPrice = l_eqTypeQuoteData.getCurrentPrice();
            }
            
            if (l_dblResultPrice == 0)
            {
                // 売気配値
                l_dblResultPrice = l_eqTypeQuoteData.getBidPrice();
            }

            if (l_dblResultPrice == 0)
            {
                // 買気配値
                l_dblResultPrice = l_eqTypeQuoteData.getAskPrice();
            }

            if (l_dblResultPrice == 0)
            {
                // 基準値（終値）
                l_dblResultPrice = l_tradedProduct.getLastClosingPrice();
            }
        }
        else
        {
            //「概算金額計算方式」＝STOP高拘束方式、かつ非権利落ち日（権利落ち日チェック結果＝false）の場合
            //基準値算出
            double l_dblBasePrice = this.calcBasePrice(l_tradedProduct);

            //値幅算出
            double l_dblRangePrice =
                this.calcPriceRange(
                    l_tradedProduct,
                    l_dblBasePrice,
                    WEB3MaxMinFlagDef.MAXIMUM);

            //刻み値取得
            BigDecimal l_bdBasePrice = new BigDecimal(l_dblBasePrice);

            double l_dbltickValue =
                l_productManager.getTickValue(
                    l_tradedProduct,
                    (l_bdBasePrice.add(new BigDecimal(l_dblRangePrice))).doubleValue());

            //値幅上限算出
            l_dblResultPrice =
                this.calcStopHighPrice(
                    l_dblBasePrice,
                    l_dblRangePrice,
                    l_dbltickValue);
        }        
        return l_dblResultPrice;
    }
    
    /**
     * （validate売付可能数量）。<BR>
     * <BR>
     * 売付に十分な数量の資産を所有しているかをチェックする。 <BR>
     * （validateSellableAssetQuantity( )のオーバーロード） <BR>
     * <BR>
     * １）　@株式ポジションマネージャ.get保有資産(口座ID, 補助口座ID, 銘柄ID,
     * 税区分)により、 <BR>
     * 　@　@　@保有資産オブジェクトを取得する。 <BR>
     * <BR>
     * ２）　@保有資産.getLockedQuantity(資産ID)により、ロック中数量を取
     * 得する。 <BR>
     * <BR>
     * ３）　@（保有資産オブジェクト.数量－ロック中数量）＜引数.株数の場合は、<BR>
     * 例外をthrowする。  <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00167
     * @@param l_subAccount 補助口座
     * @@param l_tradeProduct 取引銘柄
     * @@param l_dblQuantity (株数) <BR>
     * 　@　@　@売り注文の数量
     * @@param l_taxTypeEnum 税区分
     * @@throws WEB3BaseException
     */
    public void validateSellableAssetQuantity(
        SubAccount l_subAccount,
        TradedProduct l_tradeProduct,
        double l_dblQuantity,
        TaxTypeEnum l_taxTypeEnum)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateSellableAssetQuantity(SubAccount, TradedProduct, double, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null
            || l_tradeProduct == null
            || l_taxTypeEnum == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_dblQuantity < 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //株式ポジションマネージャを取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        //１）　@株式ポジションマネージャ.get保有資産(口座ID, 補助口座ID, 銘柄ID,
        // 税区分)により、 保有資産オブジェクトを取得する。
        WEB3EquityAsset l_asset = null;

        l_asset =
            (WEB3EquityAsset)l_positionManager.getAsset(
                l_subAccount.getMainAccount().getAccountId(),
                l_subAccount.getSubAccountId(),
                l_tradeProduct.getProduct().getProductId(),
                l_taxTypeEnum);

        double l_dblLockedQuantity = 0D;
        double l_remainQuantity = 0D;
        //２）　@保有資産.getLockedQuantity(資産ID)により、ロック中数量を取得する。
        if (l_asset != null)
        {
            l_dblLockedQuantity = l_asset.getLockedQuantity();
            l_remainQuantity = l_asset.getQuantity() - l_dblLockedQuantity;
        }
        else
        {
            WEB3BusinessLayerException l_businessException =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00167,
                    this.getClass().getName() + "."
                    + STR_METHOD_NAME,
                    "条件に該当する保有資産なし");
            throw l_businessException;
        }

        //３）　@（保有資産オブジェクト.数量－ロック中数量）＜引数.株数の場合は、
        // 例外をthrowする。
        if (l_remainQuantity < l_dblQuantity)
        {
            WEB3BusinessLayerException l_businessException =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00167,
                    this.getClass().getName() + "."
                    + STR_METHOD_NAME,WEB3EquityTypeOrderManagerReusableValidations.ORDER_ERROR_TAG
                    + WEB3ErrorReasonCodeDef.POSITION_SHORT_ERROR);
            throw l_businessException;
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （isChangeIs出来るまで）。<BR>
     * <BR>
     * 出来るまで注文かどうか（「出来るまで注文」でない(＝当日限り)<BR>
     * ／「出来るまで注文」）が訂正されていないかどうかチェックする。<BR>
     * <BR>
     * １）　@原注文の判定<BR>
     * 株式注文訂正内容.get訂正元注文単位( )にて注文単位オブジェクトを取得し、<BR>
     * 原注文が「出来るまで注文」(*1)かどうかを判定する。<BR>
     * <BR>
     * (*1) 拡張株式注文マネージャ.is出来るまで注文単位<BR>
     * (注文単位オブジェクト)==trueの<BR>
     * 　@　@　@場合、「出来るまで注文」である。<BR>
     * 　@　@　@falseの場合、「出来るまで注文」でない。<BR>
     * <BR>
     * ２）　@訂正入力の判定<BR>
     * 株式注文訂正内容.get訂正値詳細( )にて、<BR>
     * 株式注文訂正値詳細オブジェクトを取得し、<BR>
     * 株式注文訂正値詳細.get訂正後is出来るまで注文( )の戻り値で判定する。<BR>
     * （戻り値==trueの場合は「出来るまで注文」、<BR>
     * falseの場合は「出来るまで注文」でない。）<BR>
     * <BR>
     * ３）　@「出来るまで注文」指定変更チェック<BR>
     * 　@　@－（原注文の指定） != （訂正入力の指定）の場合は、<BR>
     *   　@例外をthrowする。 <BR>
     * 　@　@　@class: WEB3BaseException<BR>
     * 　@　@　@tag:    BUSINESS_ERROR_00070
     * @@param l_changeOrderSpec (株式注文訂正内容)<BR>
     * 　@　@　@株式注文訂正内容オブジェクト
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangeIsOrderUntilDeadLine(WEB3EquityChangeOrderSpec l_changeOrderSpec)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "isChangeIsOrderUntilDeadLine(WEB3EquityChangeOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_changeOrderSpec == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //注文単位オブジェクトを取得する
        EqTypeOrderUnit l_orderUnit =
            (EqTypeOrderUnit) l_changeOrderSpec.getOrgChangeOrderUnit();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

        boolean l_isDeadLineOrderUnit = false;
        try
        {
            l_isDeadLineOrderUnit =
                l_orderManager.isCarriedOrderUnit(l_orderUnit);

        }
        catch (WEB3SystemLayerException l_sle)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00070,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 訂正入力の判定
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            (WEB3EquityChangeOrderUnitEntry) l_changeOrderSpec.getChangeOrderUnitEntry();
        boolean l_isChangeAfterIsOrderUntilDeadLine =
            l_changeOrderUnitEntry.getChangeAfterIsOrderUntilDeadLine();
        if (l_isDeadLineOrderUnit != l_isChangeAfterIsOrderUntilDeadLine)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00070,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * （validate出来るまで注文停止銘柄）。<BR>
     * <BR>
     * 注文繰越スキップ銘柄に対し、注文期限に「出来るまで注文」が<BR>
     * 指定されていないことのチェックを行う。<BR>
     * <BR>
     * １）　@注文繰越スキップ銘柄チェック <BR>
     * 出来るまで注文指定の場合(*1)、取引銘柄.is繰越スキップ銘柄( )で<BR>
     * スキップ銘柄かどうかを判定する。戻り値＝trueの場合、<BR>
     * 例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException <BR>
     * 　@tag:   BUSINESS_ERROR_00153 <BR>
     * (*1) 注文失効日と発注日が違う場合、出来るまで<BR>
     * 注文指定と判断する。
     * @@param l_exepireDate 注文失効日
     * @@param l_eqTradedProduct 取引銘柄
     * @@throws WEB3BaseException
     */
    protected void validateExecutedOrderStopProduct(
        Date l_exepireDate,
        WEB3EquityTradedProduct l_eqTradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateExecutedOrderStopProduct(Date, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        EqtypeTradedProductRow l_eqTradedProductRow =
            (EqtypeTradedProductRow)l_eqTradedProduct.getDataSourceObject();

        // 発注日取得
        String l_strOrderBizDate = l_eqTradedProductRow.getValidUntilBizDate().trim();
        String l_strExepireDate = WEB3DateUtility.formatDate(l_exepireDate, "yyyyMMdd");
        if (!l_strOrderBizDate.equals(l_strExepireDate))
        {
            boolean l_isSkipProduct = l_eqTradedProduct.isTransferSkipProduct();
            if (l_isSkipProduct)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00153,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （validate取引銘柄（信用））。<BR>
     * <BR>
     * 取扱可能チェック、及び弁済区分別の売買停止チェック（信用）を実施する。<BR>
     * チェック結果がOKの場合は、取引銘柄オブジェクトを返却する。<BR>
     * （validateTradedProductForMarginTrading( )のオーバーロード）<BR>
     * ----------------------------------------<BR>
     * ○制度信用銘柄の取扱<BR>
     * 　@－貸借銘柄であれば、買建／売建共に可。<BR>
     * 　@－非貸借銘柄の場合は、非貸借銘柄の取扱部店であれば、買建は可。<BR>
     * 　@－非貸借銘柄の場合は、売建は不可。<BR>
     * <BR>
     * ○一般信用銘柄の取扱<BR>
     * 　@－一般信用銘柄であっても、売建不可銘柄の場合は、売建は不可。（買建は可）<BR>
     * ----------------------------------------<BR>
     * <BR>
     * １）　@this.validate取引銘柄(株式銘柄, 市場)をコールする。<BR>
     * <BR>
     * ２）　@外国証券口座開設チェック<BR>
     * 　@　@　@取引銘柄.上場区分＝”外国部上場”の場合のみ、<BR>
     * 　@　@　@当該顧客が外国証券口座を開設しているかどうかチェックする。<BR>
     * <BR>
     * 　@　@　@顧客.is外国証券口座開設( )==false（外国証券口座開設なし）の場合は<BR>
     * 　@　@　@「当該顧客は外国証券口座開設なし」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01341<BR>
     * <BR>
     * ３）　@以下のチェックを行う。<BR>
     * 　@　@　@引数の弁済区分の値により、チェック内容を分ける。<BR>
     * <BR>
     * ３－１）　@取扱可能チェック<BR>
     * 　@　@　@　@　@※取扱可能チェックは、引数の注文カテゴリ＝"新規建"（OPEN_MARGIN）の場合のみ<BR>
     * 　@　@　@　@　@※実施する。<BR>
     * 　@----------------------------------------------------<BR>
     * 　@＜引数の弁済区分＝DEFAULT（指定なし） の場合＞<BR>
     * <BR>
     * 　@---------------<BR>
     * 　@[制度信用取扱可能チェック]<BR>
     * 　@　@以下のいずれかに当てはまる場合、制度信用取扱不能とする。(*1)<BR>
     * <BR>
     * 　@－取引銘柄.制度信用銘柄区分＝「制度信用銘柄でない」<BR>
     * 　@－取引銘柄.制度信用銘柄区分＝「制度信用銘柄（非貸借銘柄）」、かつ、<BR>
     * 　@　@引数の部店オブジェクト.非貸借銘柄取扱＝「取扱不可」<BR>
     * 　@－取引銘柄.制度信用銘柄区分＝「制度信用銘柄（非貸借銘柄）」、かつ、<BR>
     * 　@　@ 引数のis売建＝trueの場合<BR>
     * 　@---------------<BR>
     * 　@[一般信用取扱可能チェック]<BR>
     * 　@　@以下のいずれかに当てはまる場合、一般信用取扱不能とする。(*2)<BR>
     * <BR>
     * 　@－取引銘柄.一般信用銘柄区分＝「一般信用銘柄でない」の場合<BR>
     * 　@－取引銘柄.一般信用銘柄区分＝「一般信用銘柄（売建不可銘柄）」、かつ、<BR>
     * 　@　@ 引数のis売建＝trueの場合<BR>
     * 　@---------------<BR>
     * <BR>
     * 　@[取扱可能チェック]<BR>
     * 　@　@制度信用取扱不能(*1)、かつ、一般信用取扱不能(*2)の場合、<BR>
     * 　@　@「信用取引の取扱不可銘柄」の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00697<BR>
     * 　@----------------------------------------------------<BR>
     * 　@＜引数の弁済区分＝制度信用 の場合＞<BR>
     * <BR>
     * 　@－取引銘柄.制度信用銘柄区分＝「制度信用銘柄でない」の場合は、<BR>
     * 　@　@「制度信用の取扱不可銘柄」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00696<BR>
     * <BR>
     * 　@－取引銘柄.制度信用銘柄区分＝「制度信用銘柄（非貸借銘柄）」、<BR>
     * 　@　@かつ　@引数の部店オブジェクト.非貸借銘柄取扱＝「取扱不可」であれば、<BR>
     * 　@　@「非貸借銘柄の取扱不可」の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00695<BR>
     * <BR>
     * 　@－取引銘柄.制度信用銘柄区分＝「制度信用銘柄（非貸借銘柄）」、<BR>
     * 　@　@かつ　@引数のis売建＝trueの場合は、<BR>
     * 　@　@「売建不可(非貸借銘柄)」の例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00694<BR>
     * 　@----------------------------------------------------<BR>
     * 　@＜引数の弁済区分＝一般信用 の場合＞<BR>
     * <BR>
     * 　@－取引銘柄.一般信用銘柄区分＝「一般信用銘柄でない」の場合は、<BR>
     * 　@　@「一般信用の取扱不可銘柄」の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00693<BR>
     * <BR>
     * 　@－取引銘柄.一般信用銘柄区分＝「一般信用銘柄（売建不可銘柄）」、<BR>
     * 　@　@かつ　@引数のis売建＝trueの場合は、<BR>
     * 　@　@「売建不可(売建不可銘柄)」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00692<BR>
     * 　@----------------------------------------------------<BR>
     * <BR>
     * ３－２）　@引数のis売買停止チェック==true（売買停止チェックをする）の場合のみ、<BR>
     * 　@　@　@　@　@売買停止チェック：全ての注文カテゴリに対し実施する。<BR>
     * <BR>
     * 　@取引銘柄.is信用売買規制(引数の弁済区分, 引数の注文カテゴリ, 引数のis売建)を<BR>
     * 　@コールする。<BR>
     * 　@戻り値==trueの場合、取引銘柄.get信用売買規制停止(引数の弁済区分, 引数の注文カテゴリ, <BR>
     * 　@引数のis売建)により<BR>
     * 　@停止中の内容（当社規制／取引所規制）を取得し、規制の内容別に例外をスローする。<BR>
     * 　@（* 停止中には”停止中（当社規制）”、”停止中”（取引所規制）の２種類あり、<BR>
     * 　@　@　@エラーメッセージを分ける。）<BR>
     * 　@　@　@・”停止中(当社規制)”の場合　@　@ →　@「銘柄の売買停止中(当社規制)」の例外をスロー。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00014<BR>
     * 　@　@　@・”停止中(取引所規制)”の場合　@→　@「銘柄の売買停止中(取引所規制)」の例外をスロー。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00015<BR>
     * <BR>
     * ４）　@取引銘柄オブジェクトを返却する。
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@補助口座オブジェクト。
     * @@param l_product (株式銘柄)<BR>
     * 　@　@　@株式銘柄オブジェクト。
     * @@param l_market (市場)<BR>
     * 　@　@　@市場オブジェクト。
     * @@param l_branch (部店)<BR>
     * 　@　@　@部店オブジェクト。
     * @@param l_strRepaymentType (弁済区分)<BR>
     * 　@　@　@弁済区分。<BR>
     * 　@　@　@0：DEFAULT（指定なし）<BR>
     * 　@　@　@1：制度信用<BR>
     * 　@　@　@2：一般信用
     * @@param l_orderCateg (注文カテゴリ)<BR>
     * 　@　@　@注文カテゴリ。（xTradeのOrderCategEnumにて定義）
     * @@param l_isShort (is売建)<BR>
     * 　@　@　@売建／買建のフラグ。<BR>
     * 　@　@　@建株＝売建の場合true、買建の場合falseを指定する。
     * @@param l_isTradeStopCheck (is売買停止チェック)<BR>
     * 　@　@　@売買停止チェック実施有無フラグ。<BR>
     * 　@　@　@（true：チェックする、false：チェックしない）
     * @@return WEB3EquityTradedProduct
     * @@throws WEB3BaseException
     */
    public WEB3EquityTradedProduct validateTradedProductForMarginTrading(
        SubAccount l_subAccount,
        WEB3EquityProduct l_product,
        WEB3GentradeMarket l_market,
        WEB3GentradeBranch l_branch,
        String l_strRepaymentType,
        OrderCategEnum l_orderCateg,
        boolean l_isShort,
        boolean l_isTradeStopCheck)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradedProduct(SubAccount, WEB3EquityProduct, WEB3GentradeMarket, WEB3GentradeBranch, String, OrderCategEnum, boolean, boolean)";
        log.entering(STR_METHOD_NAME);

        // １）　@this.validate取引銘柄(株式銘柄, 市場)をコールする
        WEB3EquityTradedProduct l_tradedProduct = (WEB3EquityTradedProduct)this.validateTradedProduct(
            (EqTypeProduct)l_product, l_market);

        // ２）　@外国証券口座開設チェック
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        if (WEB3ListTypeDef.FOREIGN_SECITION.equals(l_tradedProductRow.getListType()))
        {
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_account = null;
            try
            {
                l_account = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_subAccount.getAccountId());
            }
            catch (NotFoundException l_exp)
            {
                log.error("顧客データの取得に失敗しました。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exp.getMessage(),
                    l_exp);
            }
            if (l_account.isForeignAccountOpen() == false)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();

        // ３）　@以下のチェックを行う。
        // 　@　@　@引数の弁済区分の値により、チェック内容を分ける。
        // ３－１）　@取扱可能チェック
        // 　@　@　@　@　@※取扱可能チェックは、引数の注文カテゴリ＝"新規建"（OPEN_MARGIN）の場合のみ
        // 　@　@　@　@　@※実施する。
        if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg))
        {
            // 　@＜引数の弁済区分＝DEFAULT（指定なし） の場合＞
            if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
            {
                // 　@[制度信用取扱可能チェック]
                // 　@　@以下のいずれかに当てはまる場合、制度信用取扱不能とする。(*1)
                boolean l_blnIsMarginSysEnable = true;
                if (WEB3MarginSysProductTypeDef.NOT_MARGIN_SYS_PRODUCT.equals(l_tradedProductRow.getMarginSysProductType()))
                {
                    // 　@－取引銘柄.制度信用銘柄区分＝「制度信用銘柄でない」
                    l_blnIsMarginSysEnable = false;
                }
                else if (WEB3MarginSysProductTypeDef.MARGIN_SYS_PRODUCT_NO_LOAN.equals(
                    l_tradedProductRow.getMarginSysProductType())
                    && WEB3DealtDef.CAN_NOT_DEALT.equals(Integer.toString(l_branchRow.getHandlingNotLoanTransStock())))
                {
                    // 　@－取引銘柄.制度信用銘柄区分＝「制度信用銘柄（非貸借銘柄）」、かつ、
                    // 　@　@引数の部店オブジェクト.非貸借銘柄取扱＝「取扱不可」
                    l_blnIsMarginSysEnable = false;
                }
                else if (WEB3MarginSysProductTypeDef.MARGIN_SYS_PRODUCT_NO_LOAN.equals(
                    l_tradedProductRow.getMarginSysProductType())
                    && l_isShort)
                {
                    // 　@－取引銘柄.制度信用銘柄区分＝「制度信用銘柄（非貸借銘柄）」、かつ、
                    // 　@　@ 引数のis売建＝trueの場合
                    l_blnIsMarginSysEnable = false;
                }

                // 　@[一般信用取扱可能チェック]
                // 　@　@以下のいずれかに当てはまる場合、一般信用取扱不能とする。(*2)
                boolean l_blnIsMarginGenEnable = true;
                if (WEB3MarginGenProductTypeDef.NOT_MARGIN_GEN_PRODUCT.equals(l_tradedProductRow.getMarginGenProductType()))
                {
                    // 　@－取引銘柄.一般信用銘柄区分＝「一般信用銘柄でない」の場合
                    l_blnIsMarginGenEnable = false;
                }
                else if (WEB3MarginGenProductTypeDef.MARGIN_GEN_PRODUCT_NO_OPEN_SELL.equals(
                    l_tradedProductRow.getMarginGenProductType())
                    && l_isShort)
                {
                    // 　@－取引銘柄.一般信用銘柄区分＝「一般信用銘柄（売建不可銘柄）」、かつ、
                    // 　@　@ 引数のis売建＝trueの場合
                    l_blnIsMarginGenEnable = false;
                }

                // 　@[取扱可能チェック]
                if (!l_blnIsMarginSysEnable && !l_blnIsMarginGenEnable)
                {
                    // 　@　@制度信用取扱不能(*1)、かつ、一般信用取扱不能(*2)の場合、
                    // 　@　@「信用取引の取扱不可銘柄」の例外をスローする。
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00697,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            else if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
            {
                // 　@＜引数の弁済区分＝制度信用 の場合＞
                if (WEB3MarginSysProductTypeDef.NOT_MARGIN_SYS_PRODUCT.equals(l_tradedProductRow.getMarginSysProductType()))
                {
                    // 　@－取引銘柄.制度信用銘柄区分＝「制度信用銘柄でない」の場合は、
                    // 　@　@「制度信用の取扱不可銘柄」の例外をスローする。
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00696,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                else if (WEB3MarginSysProductTypeDef.MARGIN_SYS_PRODUCT_NO_LOAN.equals(
                    l_tradedProductRow.getMarginSysProductType())
                    && WEB3DealtDef.CAN_NOT_DEALT.equals(Integer.toString(l_branchRow.getHandlingNotLoanTransStock())))
                {
                    // 　@－取引銘柄.制度信用銘柄区分＝「制度信用銘柄（非貸借銘柄）」、
                    // 　@　@かつ　@引数の部店オブジェクト.非貸借銘柄取扱＝「取扱不可」であれば、
                    // 　@　@「非貸借銘柄の取扱不可」の例外をスローする。
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00695,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                else if (WEB3MarginSysProductTypeDef.MARGIN_SYS_PRODUCT_NO_LOAN.equals(
                    l_tradedProductRow.getMarginSysProductType())
                    && l_isShort)
                {
                    // 　@－取引銘柄.制度信用銘柄区分＝「制度信用銘柄（非貸借銘柄）」、
                    // 　@　@かつ　@引数のis売建＝trueの場合は、
                    // 　@　@「売建不可(非貸借銘柄)」の例外をスローする。
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00694,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            else if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
            {
                // 　@＜引数の弁済区分＝一般信用 の場合＞
                if (WEB3MarginGenProductTypeDef.NOT_MARGIN_GEN_PRODUCT.equals(
                    l_tradedProductRow.getMarginGenProductType()))
                {
                    // 　@－取引銘柄.一般信用銘柄区分＝「一般信用銘柄でない」の場合は、
                    // 　@　@「一般信用の取扱不可銘柄」の例外をスローする。
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00693,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                else if (WEB3MarginGenProductTypeDef.MARGIN_GEN_PRODUCT_NO_OPEN_SELL.equals(
                    l_tradedProductRow.getMarginGenProductType())
                    && l_isShort)
                {
                    // 　@－取引銘柄.一般信用銘柄区分＝「一般信用銘柄（売建不可銘柄）」、
                    // 　@　@かつ　@引数のis売建＝trueの場合は、
                    // 　@　@「売建不可(売建不可銘柄)」の例外をスローする。
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00692,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        if (l_isTradeStopCheck)
        {
            // 　@取引銘柄.is信用売買規制()
            boolean l_blnIsMarginTradeControl = l_tradedProduct.isMarginTradeControl(
                l_strRepaymentType, l_orderCateg, l_isShort);

            if (l_blnIsMarginTradeControl)
            {
                //信用売買規制停止内容
                String l_strMarginTradeControlStop =
                    l_tradedProduct.getMarginTradeControlStop(l_strRepaymentType, l_orderCateg, l_isShort);

                if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(l_strMarginTradeControlStop))
                {
                    //　@　@　@・”停止中(当社規制)”の場合　@　@ →　@「銘柄の売買停止中(当社規制)」の例外をスロー。
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00014,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                else if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals(l_strMarginTradeControlStop))
                {
                    // 　@　@　@・”停止中(取引所規制)”の場合　@→　@「銘柄の売買停止中(取引所規制)」の例外をスロー。
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00015,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        log.exiting(STR_METHOD_NAME);

        return l_tradedProduct;
    }
    
    /**
     * （validate銘柄コード（信用））。<BR>
     * <BR>
     * 銘柄コードの存在チェック及び売買停止チェック（信用）を実施する。<BR>
     * チェック結果がOKの場合は、株式銘柄オブジェクトを返却する。<BR>
     * （validateProductCode( )のオーバーロード）<BR>
     * <BR>
     * １）　@引数の銘柄コード、証券会社コードに該当する、株式銘柄オブジェクトを生成する。<BR>
     * <BR>
     * ２）　@以下のチェックを行う。引数の弁済区分の値により、チェック内容を分ける。<BR>
     * 　@----------------------------------------------------<BR>
     * 　@＜引数の弁済区分＝DEFAULT（指定なし） の場合＞<BR>
     * <BR>
     * 　@－株式銘柄.制度信用取引売買停止 == ”停止中”、かつ、<BR>
     * 　@　@ 株式銘柄.一般信用取引売買停止 == ”停止中”の場合、<BR>
     * 　@　@　@(*)例外をスローする。<BR>
     * <BR>
     * 　@----------------------------------------------------<BR>
     * 　@＜引数の弁済区分＝制度信用 の場合＞<BR>
     * <BR>
     * 　@－株式銘柄.制度信用取引売買停止 == ”停止中”の場合、<BR>
     * 　@　@例外をスローする。<BR>
     * <BR>
     * 　@----------------------------------------------------<BR>
     * 　@＜引数の弁済区分＝一般信用 の場合＞<BR>
     * <BR>
     * 　@－株式銘柄.一般信用取引売買停止 == ”停止中”の場合、<BR>
     * 　@　@例外をスローする。<BR>
     * <BR>
     * 　@----------------------------------------------------<BR>
     * 　@（*)停止中には”停止中（当社規制）”、”停止中（取引所規制）”の２種類あり、<BR>
     * 　@　@　@エラーメッセージを分ける。<BR>
     * 　@　@　@・”停止中(当社規制)”の場合　@　@ →　@「銘柄の売買停止中(当社規制)」の例外をスロー。<BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00014<BR>
     * <BR>
     * 　@　@　@・”停止中(取引所規制)”の場合　@→　@「銘柄の売買停止中(取引所規制)」の例外をスロー。<BR>
     * <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00015<BR>
     *       ・”停止中(取引所規制)”かつ ”停止中(当社規制)”の場合　@→　@<BR>
     *        「銘柄の売買停止中(取引所規制/当社規制)」の例外をスロー。<BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00700<BR>
     * <BR>
     * ３）　@株式銘柄オブジェクトを返却する。
     * @@param l_strProductCode 銘柄コード
     * @@param l_strInstitutionCode 証券会社コード
     * @@param l_strRepaymentType 弁済区分。<BR>
     * 　@　@　@0：DEFAULT（指定なし）<BR>
     * 　@　@　@1：制度信用<BR>
     * 　@　@　@2：一般信用
     * @@return WEB3EquityProduct
     * @@throws WEB3BaseException
     */
    public WEB3EquityProduct validateProductCode(
        String l_strProductCode, 
        String l_strInstitutionCode, 
        String l_strRepaymentType) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateProductCode(l_strProductCode,l_strInstitutionCode,l_strRepaymentType)";
        log.entering(STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
            //拡張プロダクトマネージャ
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        
            Institution l_institution = l_finApp.getAccountManager().getInstitution(l_strInstitutionCode);
        
            //引数の銘柄コード、証券会社コードに該当する、株式銘柄オブジェクトを生成する。           
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_productManager.getProduct(l_institution, l_strProductCode);
            
            //銘柄Rowオブジェクト
            EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();
             
            if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
            {
                // ＜引数の弁済区分＝DEFAULT（指定なし） の場合＞
                if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals("" + l_productRow.getMarginSysTradeStop())
                    && WEB3TradeStopDef.STOP_MARKET_DEREG.equals("" + l_productRow.getMarginGenTradeStop()))
                {
                    //株式銘柄.制度信用取引売買停止  == ”停止中(取引所規制)”の場合
                    //株式銘柄.一般信用取引売買停止  == ”停止中(取引所規制)”の場合
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00015,STR_METHOD_NAME);
                }
                else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals("" + l_productRow.getMarginSysTradeStop())
                    && WEB3TradeStopDef.STOP_COMPANY_DEREG.equals("" + l_productRow.getMarginGenTradeStop()))
                {
                    //株式銘柄.制度信用取引売買停止 == ”停止中(当社規制)”の場合
                    //株式銘柄.一般信用取引売買停止 == ”停止中(当社規制)”の場合
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00014,STR_METHOD_NAME);
                }
                else if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals("" + l_productRow.getMarginSysTradeStop())
                    && WEB3TradeStopDef.STOP_COMPANY_DEREG.equals("" + l_productRow.getMarginGenTradeStop()))
                {
                    //株式銘柄.制度信用取引売買停止 == ”停止中(取引所規制)”の場合
                    //株式銘柄.一般信用取引売買停止 == ”停止中(当社規制)”の場合
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00700,STR_METHOD_NAME);
                }
                else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals("" + l_productRow.getMarginSysTradeStop())
                    && WEB3TradeStopDef.STOP_MARKET_DEREG.equals("" + l_productRow.getMarginGenTradeStop()))
                {
                    //株式銘柄.制度信用取引売買停止 == ”停止中(当社規制)”の場合
                    //株式銘柄.一般信用取引売買停止 == ”停止中(取引所規制)”の場合
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00700,STR_METHOD_NAME);
                }                                
                
            }
            else if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
            {
                //＜引数の弁済区分＝制度信用 の場合＞ 
                if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals("" + l_productRow.getMarginSysTradeStop()))
                {
                    //”停止中(取引所規制)”の場合
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00015,STR_METHOD_NAME);
                }
                else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals("" + l_productRow.getMarginSysTradeStop()))
                {
                    //”停止中(当社規制)”の場合
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00014,STR_METHOD_NAME);
                }
            }
            else if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
            {
                //＜引数の弁済区分＝一般信用 の場合＞ 
                if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals("" + l_productRow.getMarginGenTradeStop()))
                {
                    //”停止中(取引所規制)”の場合
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00015,STR_METHOD_NAME);
                }
                else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals("" + l_productRow.getMarginGenTradeStop()))
                {
                    //”停止中(当社規制)”の場合
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00014,STR_METHOD_NAME);
                }                
            }
            log.exiting(STR_METHOD_NAME);
            return l_product;            
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301,STR_METHOD_NAME);
        }       

    }
    
    /**
     * （validate取扱可能市場（信用））。<BR>
     * <BR>
     * 会社部店が、信用取引の指定弁済区分・弁済期限の<BR>
     * 取扱可能市場かをチェックする。<BR>
     * <BR>
     * １）　@（部店市場弁済別）取扱条件のインスタンスを生成する。<BR>
     * <BR>
     * [（部店市場弁済別）取扱条件・コンストラクタ引数]<BR>
     * 証券会社コード：　@引数の部店.証券会社コード<BR>
     * 部店コード：　@引数の部店.部店コード<BR>
     * 市場コード：　@引数の市場コード<BR>
     * 弁済区分：　@引数の弁済区分<BR>
     * 弁済期限値：　@引数の弁済期限値<BR>
     * <BR>
     * ２）　@生成したインスタンス.is取扱可能( )にて、取扱可能市場かを判定する。<BR>
     * 　@　@　@falseが返された場合は、取扱可能市場でないと判定し、<BR>
     * 　@　@　@「取扱可能市場チェックエラー(部店・弁済)」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00703<BR>
     * <BR>
     * ３）　@JASDAQ取扱チェック<BR>
     * 　@－引数の取引銘柄.validateJASDAQ銘柄取扱可能( )をコールする。
     * @@param l_branch (部店)<BR>
     * 　@　@　@部店オブジェクト（顧客の取引店）
     * @@param l_tradedProduct (取引銘柄)<BR>
     * 　@　@　@株式取引銘柄オブジェクト
     * @@param l_strMarketCode (市場コード)<BR>
     * 　@　@　@市場コード。
     * @@param l_strRepaymentType (弁済区分)<BR>
     * 　@　@　@弁済区分。<BR>
     * 　@　@　@　@0：DEFAULT(指定なし)<BR>
     * 　@　@　@　@1：制度信用<BR>
     * 　@　@　@　@2：一般信用
     * @@param l_dblRepaymentNum (弁済期限値)<BR>
     * 　@　@　@弁済期限値
     * @@throws WEB3BaseException
     */
    public void validateHandlingMarket(
        WEB3GentradeBranch l_branch, 
        WEB3EquityTradedProduct l_tradedProduct, 
        String l_strMarketCode, 
        String l_strRepaymentType, 
        double l_dblRepaymentNum) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateHandlingMarket(WEB3GentradeBranch, WEB3EquityTradedProduct, String, String, double)";
        log.entering(STR_METHOD_NAME);

        // １）　@（部店市場弁済別）取扱条件のインスタンスを生成する。
        WEB3GentradeBranchMarketRepayDealtCond l_branchMarketRepayDealtCond = 
            new WEB3GentradeBranchMarketRepayDealtCond(l_branch.getInstitution().getInstitutionCode(),
            l_branch.getBranchCode(),
            l_strMarketCode,
            l_strRepaymentType,
            l_dblRepaymentNum);

        //  ２）　@生成したインスタンス.is取扱可能( )にて、取扱可能市場かを判定する。
        if (!l_branchMarketRepayDealtCond.isHandlingPossible())
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00703,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // ３）　@JASDAQ取扱チェック
        l_tradedProduct.validateJASDAQProductHandling(l_branch);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （validate注文条件）。<BR>
     * <BR>
     * 注文条件のチェックを行う。<BR>
     * 　@－値段条件のチェック<BR>
     * 　@－執行条件のチェック<BR>
     * 　@－発注条件のチェック<BR>
     * 　@－出来るまで注文取扱のチェック<BR>
     * 　@－出来るまで注文有効期限のチェック<BR>
     * 　@－注文繰越スキップ銘柄のチェック<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（注文）validate注文条件」参照。<BR>
     * <BR>
     * １．いずれかのチェックでfalseが返された場合は、例外をthrowする。 <BR>
     * ＃チェックの種類毎に例外クラスを分ける。 <BR>
     * 　@・値段条件のチェック　@→　@「値段条件のチェックエラー」の例外 <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_01342
     * <BR>
     * 　@・執行条件のチェック　@→　@「執行条件のチェックエラー」の例外 <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00150<BR>
     * <BR>
     * 　@・発注条件のチェック　@→　@「発注条件のチェックエラー」の例外 <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00151<BR>
     * <BR>
     * 　@・出来るまで注文取扱のチェック　@→ <BR>
     * 　@　@　@「出来るまで注文取扱のチェックエラー」の例外 <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00704<BR>
     * <BR>
     * 　@・出来るまで注文有効期限のチェック　@→ <BR>
     * 　@　@　@「出来るまで注文注文有効期限のチェックエラー」の例外 <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00704<BR>
     * 　@・権利付き最終日後の失効日指定不可。　@→ <BR>
     * 　@　@　@「権利付き最終日後の失効日指定不可。」の例外 <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_02836<BR>
     * <BR>
     * ２．以下のチェックでtrueが返された場合は、、例外をthrowする。 <BR>
     * ＃チェックの種類毎に例外クラスを分ける。 <BR>
     * 　@・注文繰越スキップ銘柄のチェック　@→ <BR>
     * 　@　@　@「注文繰越不可の銘柄」の例外 <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00684<BR>
     * 　@　@※（is繰越スキップ銘柄() == true）の場合、<BR>
     * 　@　@　@　@出来るまで注文停止銘柄であるため。
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@補助口座オブジェクト（信用取引口座）。
     * @@param l_lngOrderUnitId (注文単位ID)<BR>
     * 　@　@　@注文単位ID<BR>
     * <BR>
     * 　@　@　@新規注文の場合は0をセット。
     * @@param l_tradedProduct (取引銘柄)<BR>
     * 　@　@　@取引銘柄オブジェクト。
     * @@param l_datOrderBizDate (原注文発注日)<BR>
     * 　@　@　@原注文の発注日<BR>
     * <BR>
     * 　@　@　@出来るまで注文の訂正の場合のみ設定。
     * @@param l_datExpirationDate (注文失効日)<BR>
     * 　@　@　@注文失効日。
     * @@param l_strOrderConditionType (発注条件)<BR>
     * 　@　@　@発注条件<BR>
     * 　@　@　@0：DEFAULT（条件指定なし）　@1：逆指値　@2：W指値
     * @@param l_executionCondition (執行条件)<BR>
     * 　@　@　@執行条件。
     * @@param l_isCarriedOrder (is出来るまで注文)<BR>
     * 　@　@　@出来るまで注文の場合true、以外false。
     * @@param l_strMarginTradeType (信用取引区分)<BR>
     * 　@　@　@0：DAFAULT（信用取引以外）<BR>
     * 　@　@　@1：制度信用<BR>
     * 　@　@　@2：一般信用
     * @@param l_strPriceConditionType (値段条件)<BR>
     * 　@　@　@WebⅢの値段条件。（取引所のコード体系と同一）<BR>
     * 　@　@　@0：　@DEFAULT(条件指定なし)<BR>
     * 　@　@　@1：　@現在値指値注文<BR>
     * 　@　@　@3：　@優先指値注文<BR>
     * 　@　@　@5：　@成行残数指値注文<BR>
     * 　@　@　@7：　@成行残数取消注文
     * @@param l_strMarketCode (市場コード)<BR>
     * 　@　@　@0：  DEFAULT<BR>
     * 　@　@　@1：  東京<BR>
     * 　@　@　@2：  大阪<BR>
     * 　@　@　@3：  名古屋<BR>
     * 　@　@　@6：  福岡<BR>
     * 　@　@　@8：  札幌<BR>
     * 　@　@　@9：  NNM<BR>
     * 　@　@　@10： JASDAQ
     * @@throws WEB3BaseException
     */
    public void validateOrderCondition(
        WEB3GentradeSubAccount l_subAccount, 
        long l_lngOrderUnitId, 
        WEB3EquityTradedProduct l_tradedProduct, 
        Date l_datOrderBizDate, 
        Date l_datExpirationDate, 
        String l_strOrderConditionType, 
        EqTypeExecutionConditionType l_executionCondition, 
        boolean l_isCarriedOrder, 
        String l_strMarginTradeType,
        String l_strPriceConditionType,
        String l_strMarketCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateOrderCondition(WEB3GentradeSubAccount, long, WEB3EquityTradedProduct, Date, Date, String, EqTypeExecutionConditionType, boolean, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        // 1.1. 取扱可能注文条件()
        WEB3GentradeHandlingOrderCond l_handlingOrderCond = 
            new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_strMarketCode);

        // 部店.is権利付き最終日チェック()==trueの場合
        // 部店を取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityProductManager l_equityProductManager
            = (WEB3EquityProductManager)l_tradingModule.getProductManager();

        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        if (l_branch.isEqtypeFinalDayWithRight())
        {
            Institution l_institution = l_tradedProduct.getInstitution();
            String l_strProductCode = l_tradedProduct.getProductCode();
            //get権利付き最終日()
            WEB3EquityProduct l_product = null;
            try
            {
                l_product =
                    (WEB3EquityProduct)l_equityProductManager.getProduct(
                        l_institution, l_strProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            Date l_datRightCondOrderLastDay = l_product.getRightCondOrderEndDay();

            // 発注日
            Date l_datOrderBizDat = null;
            if (l_datOrderBizDate != null)
            {
                // パラメータ原注文発注日
                l_datOrderBizDat = l_datOrderBizDate;
            }
            else
            {
                // 現在日時より算出した発注日
                l_datOrderBizDat = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            // 発注日(*) <= 株式銘柄.get権利付き最終日の場合
            if (WEB3DateUtility.compare(l_datOrderBizDat, l_datRightCondOrderLastDay) <= 0)
            {
                //set取引最終日
                l_handlingOrderCond.setTradingEndDate(l_datRightCondOrderLastDay);
            }
        }

        // 1.2. is取扱可能値段条件()
        if (!l_handlingOrderCond.isHandlingPriceCond(l_strPriceConditionType))
        {
            log.debug("!l_handlingOrderCond.isHandlingPossiblePriceConditionType(l_strPriceConditionType)");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01342,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // 1.3. is取扱可能執行条件()
        if (!l_handlingOrderCond.isHandlingPossibleExecCond(l_executionCondition))
        {
            log.debug("!l_handlingOrderCond.isHandlingPossibleExecCond(l_executionCondition)");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00150,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // 1.4. is取扱可能発注条件()
        if (!l_handlingOrderCond.isHandlingPossibleOrderCond(l_strOrderConditionType))
        {
            log.debug("!l_handlingOrderCond.isHandlingPossibleOrderCond(l_strOrderConditionType)");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00151,
                this.getClass().getName() + STR_METHOD_NAME);
        }
                    
        // validate特殊執行条件取扱停止()
        validateTriggerOrderStop(
            l_subAccount,
            l_lngOrderUnitId,
            l_tradedProduct,
            l_strOrderConditionType,
            l_strMarginTradeType,
            l_strMarketCode);

        // 1.5. 出来るまで注文の場合
        if (l_isCarriedOrder)
        {
            log.debug("l_isCarriedOrder=true");
            // 1.5.1. is出来るまで注文取扱可能()
            if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleHandling())
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00704,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            // 1.5.2. パラメータ.原注文発注日 != nullの場合
            if (l_datOrderBizDate != null)
            {
                log.debug("l_datOrderBizDate != null"); 
                // 1.5.2.1. is出来るまで注文可能日()
                log.debug("l_datExpirationDate="+l_datExpirationDate);
                log.debug("l_datOrderBizDate="+l_datOrderBizDate);
                if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(l_datExpirationDate, l_datOrderBizDate))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01815,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            // 1.5.3. パラメータ.原注文発注日 == nullの場合
            else
            {
                log.debug("l_datOrderBizDate == null"); 
                // 1.5.3.1. is出来るまで注文可能日()
                if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(l_datExpirationDate))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01815,
                        this.getClass().getName() + STR_METHOD_NAME);
                }            
            }

            // 1.5.4. is繰越スキップ銘柄()
            if (l_lngOrderUnitId == 0)
            {
                log.debug("l_lngOrderUnitId == 0");
                if (l_tradedProduct.isTransferSkipProduct())
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00684,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }

            // get出来るまで注文最終日<取引最終日考慮>(原注文発注日 : Date)
            Date l_datEndDateConsidering =
                l_handlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(l_datOrderBizDate);

            // 注文失効日とget出来るまで注文最終日<取引最終日考慮>の戻り値を比較する
            int l_intCompareToDay = WEB3DateUtility.compareToDay(l_datExpirationDate, l_datEndDateConsidering);

            // 注文失効日 > get出来るまで注文最終日<取引最終日考慮>の戻り値の場合
            if (l_intCompareToDay > 0)
            {
                log.debug("権利付き最終日後の失効日指定不可。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02836,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "権利付き最終日後の失効日指定不可。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （is空売り規制）。<BR>
     * <BR>
     * 空売り規制チェック、及び価格規制対象時のチェックを行う。<BR>
     * ※DB設定論理フォルダの参考資料「空売り規制チェック.xls」を合わせて参照のこと。<BR>
     * -------------------<BR>
     * ○空売り規制チェック<BR>
     * 　@－（注文不可の部店の場合）：注文する（銘柄＋市場）の空売り（売建）数量が<BR>
     * 　@　@　@空売り規制数量を超えている場合、エラーとし例外をthrowする。<BR>
     * 　@　@　@超えていない場合は、空売り規制チェックOKとしfalse（空売り規制対象外）を返す。<BR>
     * 　@－（注文可の部店の場合）：注文する（銘柄＋市場）の空売り（売建）数量が<BR>
     * 　@　@　@空売り規制数量を超えている場合、成行指定不可等の追加チェックを行う。<BR>
     * 　@　@　@追加チェックで一つでもエラーが発生した場合は、エラーとし例外をthrowする。<BR>
     * 　@　@　@追加チェック結果が全てOKの場合は、true（空売り規制対象）を返す。<BR>
     * 　@－適格機@関投資家の場合、常に空売り規制数量を超えている場合と同じ扱いとする。<BR>
     * -------------------<BR>
     * ○価格規制対象時チェック<BR>
     * 　@－価格規制対象の場合、<BR>
     * 　@　@　@成行指定不可等の追加チェックを行う。<BR>
     * 　@　@　@追加チェックで一つでもエラーが発生した場合は、エラーとし例外をthrowする。<BR>
     * 　@　@　@追加チェック結果が全てOKの場合は、true（空売り規制対象）を返す。<BR>
     * -------------------<BR>
     * <BR>
     * １）　@引数の注文種別≠”新規売建注文”（MARGIN_SHORT） の場合は、<BR>
     * 　@　@　@何もせずにそのままreturnする。<BR>
     * 　@　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@引数の補助口座.get取引店( )で、部店オブジェクトを取得し、<BR>
     * 　@　@　@部店オブジェクトの<BR>
     * 　@　@　@　@空売り規制実施形態区分<BR>
     * 　@　@　@　@空売り規制限度単位<BR>
     * 　@　@　@　@空売り規制適用範囲時間<BR>
     * 　@　@　@　@適格機@関投資家確認実施区分<BR>
     * 　@　@　@を取得する。<BR>
     * <BR>
     * 　@　@　@以下、空売り規制実施形態区分＝"注文不可"の場合は「注文不可の部店」、<BR>
     * 　@　@　@空売り規制実施形態区分＝"注文可"の場合は「注文可の部店」と記載する。<BR>
     * <BR>
     * ３）　@空売り規制チェックを行う。<BR>
     * 　@　@　@顧客が一般投資家の場合、<BR>
     * 　@　@　@空売り規制チェック対象となる注文の注文単位オブジェクトを取得する。<BR>
     * <BR>
     * <BR>
     * ３－１）　@空売り数量計上方法@を取得する<BR>
     * 　@　@　@拡張株式注文マネージャ.get空売り規制時間()をコールする。 <BR>
     * <BR>
     * ３－２）　@取得した空売り数量計上方法@＝"開局扱いで計上"の場合<BR>
     * 　@　@　@　@　@以下の条件で注文単位オブジェクトを取得する。 <BR>
     * 　@　@　@　@　@※銘柄、市場が一致する新規売建注文のうち、空売り規制適用範囲時間内の注文を取得する。<BR>
     * 　@　@　@　@　@※（ただし未発注の逆指値注文は除外する。）<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * 　@　@　@＜有効注文の注文単位オブジェクト取得（市場開局時間帯）＞<BR>
     * 　@　@　@拡張株式注文マネージャ.get注文単位一覧(<BR>
     * 　@　@　@　@　@　@　@　@引数の補助口座, <BR>
     * 　@　@　@　@　@　@　@　@銘柄タイプ（株式（EQUITY））,<BR>
     * 　@　@　@　@　@　@　@　@検索条件文字列（*1）,<BR>
     * 　@　@　@　@　@　@　@　@検索条件データコンテナ（*2）,<BR>
     * 　@　@　@　@　@　@　@　@null)<BR>
     * 　@　@　@　@　@　@　@　@により取得する。<BR>
     * <BR>
     * 　@　@　@（*1）銘柄ID（"product_id"）、市場ID（"market_id"）、注文種別（"order_type"）、<BR>
     * 　@　@　@　@　@　@発注日（"biz_date"）、発注条件("order_condition_type")、<BR>
     * 　@　@　@　@　@　@作成日付（"created_timestamp"）、逆指値発注日時("stop_order_ordered_timestamp")を以下のように指定する。 <BR>
     * <BR>
     * 　@　@　@　@　@　@※"product_id = ? and market_id = ? and order_type = ? and biz_date = ?"<BR>
     * <BR>
     * 　@　@　@　@　@　@２）で取得した部店.空売り規制適用範囲時間＝ALL9 の場合、 <BR>
     * 　@　@　@　@　@　@　@　@ + " and ( order_condition_type != ? or stop_order_ordered_timestamp is not null )"<BR>
     * 　@　@　@　@　@　@そうでない場合、 <BR>
     * 　@　@　@　@　@　@　@　@ + " and ( order_condition_type != ? and stop_order_ordered_timestamp is null and created_timestamp >= ?"
     * 　@　@　@　@　@　@　@　@ + " or stop_order_ordered_timestamp is not null and stop_order_ordered_timestamp >= ? )"
     * <BR>
     * 　@　@　@　@　@　@また、引数の注文単位オブジェクト≠null（＝注文の訂正）の場合は、<BR>
     * 　@　@　@　@　@　@注文単位ID（"order_unit_id"）のnot指定を追加指定し、訂正対象注文を除外する。<BR>
     * 　@　@　@　@　@　@※" and order_unit_id != ?"<BR>
     * <BR>
     * 　@　@　@（*2）銘柄ID：　@引数の取引銘柄.銘柄IDの値を、検索条件データコンテナにそのまま設定する。<BR>
     * 　@　@　@　@　@　@市場ID：　@引数の取引銘柄.市場IDの値を、検索条件データコンテナにそのまま設定する。<BR>
     * 　@　@　@　@　@　@注文種別：　@引数の注文種別のintValueを設定する。<BR>
     * 　@　@　@　@　@　@発注日：　@取引時間管理.get発注日(void) の戻り値を設定する。 <BR>
     * 　@　@　@　@　@　@発注条件1：　@逆指値注文<BR>
     * <BR>
     * 　@　@　@　@　@　@２）で取得した部店.空売り規制適用範囲時間≠ALL9 場合、以下も設定<BR>
     * 　@　@　@　@　@　@　@　@適用開始日時1：　@GtlUtils.getTradingSystem( ).getSystemTimestamp( )の日時から、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@１）で取得した部店.空売り規制適用範囲時間 の分（minute）を <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@減算した日時 を設定する。 <BR>
     * 　@　@　@　@　@　@　@　@適用開始日時2：　@適用開始日時1と同じ値<BR>
     * <BR>
     * <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合のみ、以下も設定。<BR>
     * 　@　@　@　@　@　@注文単位ID：　@引数の注文単位.注文単位ID<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * <BR>
     * ３－３）　@取得した空売り数量計上方法@＝"閉局扱いで計上"の場合<BR>
     * 　@　@　@　@　@開局後に市場に発注されることになる、新規売建注文の注文単位オブジェクトを全て取得する。 <BR>
     * 　@　@　@　@　@※銘柄、市場が一致する新規売建注文のうち、開局後に市場に発注される可能性のある注文を取得する。 <BR>
     * 　@　@　@　@　@※（ただし翌日逆指値注文として繰り越される注文は除外する。）<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * 　@　@　@＜有効注文の注文単位オブジェクト取得（市場閉局時間帯）＞<BR>
     * 　@　@　@拡張株式注文マネージャ.get注文単位一覧(<BR>
     * 　@　@　@　@　@　@　@　@引数の補助口座, <BR>
     * 　@　@　@　@　@　@　@　@銘柄タイプ（株式（EQUITY））,<BR>
     * 　@　@　@　@　@　@　@　@検索条件文字列（*1）,<BR>
     * 　@　@　@　@　@　@　@　@検索条件データコンテナ（*2）,<BR>
     * 　@　@　@　@　@　@　@　@null)<BR>
     * 　@　@　@　@　@　@　@　@により取得する。<BR>
     * <BR>
     * 　@　@　@（*1）銘柄ID（"product_id"）、市場ID（"market_id"）、注文種別（"order_type"）、<BR>
     * 　@　@　@　@　@　@注文有効状態（"order_open_status"）、注文失効日付（"expiration_date"）、発注条件（"order_condition_type"）、<BR>
     * 　@　@　@　@　@　@逆指値発注日時("stop_order_ordered_timestamp")をand条件で指定する。 <BR>
     * 　@　@　@　@　@　@※"product_id = ? and market_id = ? and order_type = ? and order_open_status = ? and expiration_date => ?"<BR>
     * 　@　@　@　@　@　@＋" and not (order_condition_type = ? and stop_order_ordered_timestamp is null)" <BR>
     * <BR>
     * 　@　@　@　@　@　@また、引数の注文単位オブジェクト≠null（＝注文の訂正／逆指値発注処理）の場合は、<BR>
     * 　@　@　@　@　@　@注文単位ID（"order_unit_id"）のnot指定を追加指定し、訂正対象注文を除外する。<BR>
     * 　@　@　@　@　@　@※" and order_unit_id != ?"<BR>
     * <BR>
     * 　@　@　@（*2）銘柄ID：　@引数の取引銘柄.銘柄IDの値を、検索条件データコンテナにそのまま設定する。<BR>
     * 　@　@　@　@　@　@市場ID：　@引数の取引銘柄.市場IDの値を、検索条件データコンテナにそのまま設定する。<BR>
     * 　@　@　@　@　@　@注文種別：　@OrderTypeEnum.”新規売建注文”（MARGIN_SHORT）のintValueを設定する。<BR>
     * 　@　@　@　@　@　@注文有効状態：　@OrderOpenStatusEnum.”オープン”（OPEN）のintValueを設定する。<BR>
     * 　@　@　@　@　@　@注文失効日付：　@取引時間管理.get発注日(void) の戻り値を設定する。<BR>
     * 　@　@　@　@　@　@発注条件：　@逆指値注文<BR>
     * <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合のみ、以下も設定。<BR>
     * 　@　@　@　@　@　@注文単位ID：　@引数の注文単位.注文単位ID<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * <BR>
     * ３－４）　@取得した空売り数量計上方法@＝"休憩時間帯扱いで計上"の場合<BR>
     * 　@　@　@　@　@後場に新たに市場に発注されることになる、新規売建注文の注文単位オブジェクトを全て取得する。<BR>
     * 　@　@　@　@　@※銘柄、市場が一致する新規売建注文のうち、開局後に新たに市場に発注される可能性のある注文を取得する。<BR>
     * 　@　@　@　@　@※（ただし未発注の逆指値注文は除外する。）<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * 　@　@　@＜有効注文の注文単位オブジェクト取得（休憩時間帯に登録した分）＞<BR>
     * 　@　@　@拡張株式注文マネージャ.get注文単位一覧(<BR>
     * 　@　@　@　@　@　@　@　@引数の補助口座,<BR>
     * 　@　@　@　@　@　@　@　@銘柄タイプ（株式（EQUITY））,<BR>
     * 　@　@　@　@　@　@　@　@検索条件文字列（*1）,<BR>
     * 　@　@　@　@　@　@　@　@検索条件データコンテナ（*2）,<BR>
     * 　@　@　@　@　@　@　@　@null)<BR>
     * 　@　@　@　@　@　@　@　@により取得する。<BR>
     * <BR>
     * 　@　@　@（*1）銘柄ID（"product_id"）、市場ID（"market_id"）、注文種別（"order_type"）、<BR>
     * 　@　@　@　@　@　@注文有効状態（"order_open_status"）、作成日付（"created_timestamp"）、発注条件("order_condition_type")、<BR>
     * 　@　@　@　@　@　@逆指値発注日時("stop_order_ordered_timestamp")をand条件で指定する。<BR>
     * 　@　@　@　@　@　@※"product_id = ? and market_id = ? and order_type = ? and order_open_status = ? and created_timestamp => ?"<BR>
     * 　@　@　@　@　@　@＋" and not (order_condition_type = ? and stop_order_ordered_timestamp is null)"<BR>
     * <BR>
     * 　@　@　@　@　@　@また、引数の注文単位オブジェクト≠null（＝注文の訂正／逆指値発注処理）の場合は、<BR>
     * 　@　@　@　@　@　@注文単位ID（"order_unit_id"）のnot指定を追加指定し、訂正対象注文を除外する。<BR>
     * 　@　@　@　@　@　@※" and order_unit_id != ?"<BR>
     * <BR>
     * 　@　@　@（*2）銘柄ID：　@引数の取引銘柄.銘柄IDの値を、検索条件データコンテナにそのまま設定する。<BR>
     * 　@　@　@　@　@　@市場ID：　@引数の取引銘柄.市場IDの値を、検索条件データコンテナにそのまま設定する。<BR>
     * 　@　@　@　@　@　@注文種別：　@OrderTypeEnum.”新規売建注文”（MARGIN_SHORT）のintValueを設定する。<BR>
     * 　@　@　@　@　@　@注文有効状態：　@OrderOpenStatusEnum.”オープン”（OPEN）のintValueを設定する。<BR>
     * 　@　@　@　@　@　@作成日付：　@取引時間管理.get発注日(void)のYYYYMMDD<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@＋３－１）で取得した空売り規制時間テーブル.開始時間（HHMMSS）を設定する。<BR>
     * 　@　@　@　@　@　@発注条件：　@逆指値注文<BR>
     * <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合のみ、以下も設定。<BR>
     * 　@　@　@　@　@　@注文単位ID：　@引数の注文単位.注文単位ID<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * <BR>
     * ４）　@３）で取得した全ての注文単位オブジェクトから空売り規制数量を集計する。 <BR>
     * <BR>
     * ４－１）　@　@注文単位が繰り越し元注文の場合（発注日≠取引時間管理.get発注日(void) ）<BR>
     * 　@　@１）　@市場未送信の場合<BR>
     * 　@　@　@　@　@　@注文数量を集計する。<BR>
     * 　@　@２）　@市場送信済みの場合<BR>
     * 　@　@　@　@　@　@未約定数量（市場からの確認済の数量－約定数量）を集計する。<BR>
     * <BR>
     * ４－２）　@取得した空売り数量計上方法@＝"休憩時間帯扱いで計上"の場合<BR>
     * 　@　@４－１）　@に同じ。<BR>
     * <BR>
     * ４－３）　@　@上記以外の場合（取引時間帯／翌日注文）<BR>
     * 　@　@　@　@　@　@元注文数量を集計する。<BR>
     * <BR>
     * ５）　@指定銘柄の規制数量を計算する。<BR>
     * <BR>
     * 　@　@　@規制数量 ＝ 引数の取引銘柄.売買単位 × ２）で取得した部店.空売り規制限度単位<BR>
     * <BR>
     * ６）　@顧客属性により、価格規制チェック対象かどうかの判定を行う。<BR>
     * <BR>
     * 　@　@　@引数の補助口座.getMainAccount( ).適格機@関投資家区分＝適格機@関投資家である<BR>
     * 　@　@　@　@　@：適格機@関投資家と判定する。<BR>
     * 　@　@　@上記以外の場合：<BR>
     * 　@　@　@　@　@：一般投資家と判定する。<BR>
     * <BR>
     * ６－１）　@顧客が一般投資家の場合<BR>
     * <BR>
     * 　@　@・（（４）で集計した未約定数量 ＋ 引数の株数） <= ５）で計算した規制数量）の場合は、<BR>
     * 　@　@　@空売り規制チェックOKとしfalse（空売り規制対象外）を返す。<BR>
     * 　@　@　@※一般投資家の場合、数量が限度内の場合は価格規制対象とならず、<BR>
     * 　@　@　@※価格規制時チェックも不要。<BR>
     * <BR>
     * 　@　@・（（４）で集計した未約定数量 ＋ 引数の株数） > ５）で計算した規制数量）に該当する場合、<BR>
     * 　@　@　@７）以降の処理を行う。<BR>
     * <BR>
     * ６－２）　@顧客が適格機@関投資家の場合<BR>
     * <BR>
     * 　@　@　@無条件で７）以降の処理を行う。<BR>
     * <BR>
     * ７）　@注文可／不可の判定、及び注文可の場合は価格規制対象時のチェックを行う。<BR>
     * <BR>
     * ７－１）　@注文不可の部店の場合は「指定銘柄の空売り規制数量を超過」の例外をthrowする。<BR>
     * <BR>
     * ７－２）　@注文可の部店の場合<BR>
     * <BR>
     * 　@　@・２）で取得した取得した部店.適格機@関投資家確認実施区分＝実施する の場合、<BR>
     * 　@　@　@以下のいずれか一つにでも該当した場合は、例外をthrowする。<BR>
     * <BR>
     * 　@　@　@　@（１）引数の値段条件＝"指定なし"、かつ 引数のis成行＝true（成行注文）<BR>
     * 　@　@　@　@（２）引数の執行条件＝"不出来引け成行"<BR>
     * 　@　@　@　@（３）引数の値段条件＝（"優先値指値"、"成行残数指値"、"成行残数取消"）の<BR>
     * 　@　@　@　@　@　@いずれかに該当する<BR>
     * <BR>
     * 　@　@　@　@エラーコードは以下の３通りに分ける。<BR>
     * 　@　@　@　@（１）　@「成行指定不可（空売り規制数量超過時）」<BR>
     * 　@　@　@　@（２）　@「執行条件に"不出来引け成行"指定不可（空売り規制数量超過時）」<BR>
     * 　@　@　@　@（３）　@「値段条件に"優先値指値"、"成行残数指値"、"成行残数取消"指定不可<BR>
     * 　@　@　@　@　@　@　@（空売り規制数量超過時）」<BR>
     * <BR>
     * 　@　@・true（空売り規制対象）を返す。<BR>
     * <BR>
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@補助口座オブジェクト（信用取引口座）。
     * @@param l_tradedProduct (取引銘柄)<BR>
     * 　@　@　@取引銘柄オブジェクト。
     * @@param l_dblQuantity (株数)<BR>
     * 　@　@　@注文株数。
     * @@param l_orderType (注文種別)<BR>
     * 　@　@　@注文種別。（xTradeのOrderTypeEnumにて定義）
     * @@param isMarketOrder (is成行)<BR>
     * 　@　@　@成行注文の場合、true。以外、false。
     * @@param l_execCondType (執行条件)<BR>
     * 　@　@　@執行条件。
     * @@param l_strPriceConditionType (値段条件)<BR>
     * 　@　@　@値段条件。
     * @@param l_changeOrderUnit<BR>
     *       訂正対象注文単位オブジェクト。
     * @@throws WEB3BaseException
     */
    public boolean isShortSellingRestraint(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityTradedProduct l_tradedProduct, 
        double l_dblQuantity, 
        OrderTypeEnum l_orderType,
        boolean isMarketOrder,
        EqTypeExecutionConditionType l_execCondType,
        String l_strPriceConditionType,
        EqtypeOrderUnitRow l_changeOrderUnit) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isShortSellingRestraint(WEB3GentradeSubAccount, WEB3EquityTradedProduct, double, OrderTypeEnum, boolean, EqTypeExecutionConditionType, String)";
        log.entering(STR_METHOD_NAME);

        //  １）　@引数の注文種別≠”新規売建注文”（MARGIN_SHORT） の場合は、
        // 　@　@　@何もせずにそのままreturnする。
        if (!OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            return false;
        }

        // ２）　@引数の補助口座.get取引店( )で、部店オブジェクトを取得
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();      
        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        // 空売り規制実施形態区分
        String l_strShortMarginRestrainDiv = l_branchRow.getShortMarginRestrainDiv();
        // 空売り規制限度単位 
        double l_dblShortMarginRestrainUnit = l_branchRow.getShortMarginRestrainUnit();
        // 空売り規制適用範囲時間 
        int l_intShortSellOrderValidMinute = l_branchRow.getShortSellOrderValidMinute();
        // 適格機@関投資家確認実施区分
        String l_strQualifiedInvestorConfirmDiv = l_branchRow.getQualifiedInvestorConfirmDiv();

        // ３）　@空売り規制チェックを行う。
        // 　@　@　@一般投資家の場合、空売り制限チェック対象となる注文単位オブジェクトを取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        MainAccountRow l_accountRow = (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();
        if (WEB3QualifiedInstInvestorDivDef.QUALIFIED_INSTITUTIONAL_INVESTOR.equals(
            l_accountRow.getQualifiedInstInvestorDiv()) == false)
        {
			// 空売り数量計上方法@を取得する
			ShortSellingRestraintTimeRow l_shortSellingResTimeRow =
				l_orderManager.getShortSellingRestraintTime();

			String l_strShortSelCountMethodDiv =
				l_shortSellingResTimeRow.getShortSellingCountMethodDiv();

			Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
                        
			StringBuffer l_sbWhere = new StringBuffer(
				" product_id = ? and market_id = ? and order_type = ?");
            Object[] l_objWheres = null;
            List l_lisWheres = new ArrayList();
            List l_lisOrderUnits;
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
    
            // 取得した空売り数量計上方法@ == "開局扱いで計上"の場合
            if (WEB3ShortSellingCountMethodDivDef.COUNT_AS_OPEN.equals(l_strShortSelCountMethodDiv))
            {
                l_sbWhere.append(" and biz_date = ?");
                
                l_lisWheres.add(String.valueOf(l_tradedProduct.getProduct().getProductId()));
                l_lisWheres.add(String.valueOf(l_tradedProduct.getMarket().getMarketId()));
                l_lisWheres.add(l_orderType);
                l_lisWheres.add(WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"));
                l_lisWheres.add(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
    
                // 取得した部店.空売り規制適用範囲時間＝ALL9の場合
                if (l_intShortSellOrderValidMinute == 99999)
                {
                    l_sbWhere.append(" and (order_condition_type != ? or stop_order_ordered_timestamp is not null)");
                }
                else
                {
                    l_sbWhere.append(" and (( order_condition_type != ? and stop_order_ordered_timestamp is null and created_timestamp >= ? )");
                    l_sbWhere.append(" or (stop_order_ordered_timestamp is not null and stop_order_ordered_timestamp >= ? ))");
                    
                    Date l_datFrom = WEB3DateUtility.addMinute(l_tsSystemTime, -l_intShortSellOrderValidMinute);
                    l_lisWheres.add(new Timestamp(l_datFrom.getTime()));
                    l_lisWheres.add(new Timestamp(l_datFrom.getTime()));
                }
                
                //注文の訂正の場合は訂正対象注文を除外する。
                if (l_changeOrderUnit != null)
                {
                    //and 注文単位ID <> ?
                    l_sbWhere.append(" and order_unit_id <> ? "); 
                    //注文単位ID：  引数の訂正対象注文単位オブジェクト.注文単位ID
                    l_lisWheres.add(Long.toString(l_changeOrderUnit.getOrderUnitId()));
                }
                
                log.debug("l_strWhere=" + l_sbWhere.toString());  
                l_objWheres = new Object[l_lisWheres.size()];
                log.debug("String.valueOf(l_tradedProduct.getProduct().getProductId())="
                    + String.valueOf(l_tradedProduct.getProduct().getProductId())); 
                log.debug("String.valueOf(l_tradedProduct.getMarket().getMarketId()"
                    + String.valueOf(l_tradedProduct.getMarket().getMarketId()));
                log.debug("String.valueOf(l_orderType.intValue())=" + String.valueOf(l_orderType.intValue())); 
                log.debug("String.valueOf(OrderOpenStatusEnum.OPEN.intValue())="
                    + String.valueOf(OrderOpenStatusEnum.OPEN.intValue())); 
                log.debug("ll_lisWheres.size()=" + l_lisWheres.size()); 
    
                l_lisWheres.toArray(l_objWheres);
    
                l_lisOrderUnits = l_orderManager.getOrderUnits(
                    l_subAccount,
                    ProductTypeEnum.EQUITY,
                    l_sbWhere.toString(),
                    l_objWheres,
                    null);
                log.debug("l_lisOrderUnits=" + l_lisOrderUnits);
            }
            // 取得した空売り数量計上方法@ == "閉局扱いで計上"の場合
			else if (WEB3ShortSellingCountMethodDivDef.COUNT_AS_CLOSE.equals(l_strShortSelCountMethodDiv))
            {
                l_sbWhere.append(" and order_open_status = ?");
                l_sbWhere.append(" and expiration_date >=? ");
                l_sbWhere.append(" and not (order_condition_type = ?");
                l_sbWhere.append(" and stop_order_ordered_timestamp is null)");
    
                l_lisWheres.add(String.valueOf(l_tradedProduct.getProduct().getProductId()));
                l_lisWheres.add(String.valueOf(l_tradedProduct.getMarket().getMarketId()));
                l_lisWheres.add(OrderTypeEnum.MARGIN_SHORT);
                l_lisWheres.add(OrderOpenStatusEnum.OPEN);
                l_lisWheres.add(new Timestamp(l_datBizDate.getTime()));
                l_lisWheres.add(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
    
                //注文の訂正の場合は訂正対象注文を除外する。
                if (l_changeOrderUnit != null)
                {
                    //and 注文単位ID <> ?
                    l_sbWhere.append(" and order_unit_id <> ? "); 
                    //注文単位ID：  引数の訂正対象注文単位オブジェクト.注文単位ID
                    l_lisWheres.add(Long.toString(l_changeOrderUnit.getOrderUnitId()));
                }
    
                l_objWheres = new Object[l_lisWheres.size()];
                l_lisWheres.toArray(l_objWheres);
    
                l_lisOrderUnits = l_orderManager.getOrderUnits(
                    l_subAccount,
                    ProductTypeEnum.EQUITY,
                    l_sbWhere.toString(),
                    l_objWheres,
                    null);
                log.debug("l_lisOrderUnits=" + l_lisOrderUnits);
            }
			//取得した空売り数量計上方法@=="休憩時間帯扱いで計上"の場合
			else
			{
				//後場に新たに市場に発注されることになる、新規売建注文の注文単位オブジェクトを全て取得する。
				l_sbWhere.append(" and order_open_status = ?");
				l_sbWhere.append(" and created_timestamp >= ?");
				l_sbWhere.append(" and not (order_condition_type = ?");
				l_sbWhere.append(" and stop_order_ordered_timestamp is null)");
	        	
				l_lisWheres.add(String.valueOf(l_tradedProduct.getProduct().getProductId()));
				l_lisWheres.add(String.valueOf(l_tradedProduct.getMarket().getMarketId()));
				l_lisWheres.add(String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue()));
				l_lisWheres.add(String.valueOf(OrderOpenStatusEnum.OPEN.intValue()));
				String l_strbizDate = 
					WEB3DateUtility.formatDate(
						l_datBizDate, 
						WEB3GentradeTimeDef.DATE_FORMAT_YMD) +
							l_shortSellingResTimeRow.getStartTime();
				Date l_dtBizDate =
					WEB3DateUtility.getDate(
						l_strbizDate,
						WEB3GentradeTimeDef.DATE_FORMAT_YMD + 
						WEB3GentradeTimeDef.TIME_FORMAT_HMS);
				l_lisWheres.add(new Timestamp(l_dtBizDate.getTime()));
				l_lisWheres.add(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
	        	
				//引数の注文単位オブジェクト≠null（＝注文の訂正）の場合のみ、以下も設定。
				if (l_changeOrderUnit != null)
				{
					l_sbWhere.append(" and order_unit_id != ?");
					l_lisWheres.add(Long.toString(l_changeOrderUnit.getOrderUnitId()));
				}
	        	
				l_objWheres = new Object[l_lisWheres.size()];
				l_lisWheres.toArray(l_objWheres);
	        	
				l_lisOrderUnits = l_orderManager.getOrderUnits(
					l_subAccount, 
					ProductTypeEnum.EQUITY, 
					l_sbWhere.toString(), 
					l_objWheres, 
					null);
				log.debug("l_lisOrderUnits=" + l_lisOrderUnits);
			}    

            // ４）　@３）で取得した全ての注文単位オブジェクトから空売り規制数量を集計する
            EqTypeOrderUnit l_orderUnit;
            EqtypeOrderUnitRow l_orderUnitRow;
            double l_dblTotalUnExecQuantity = 0.0D;
    
            int l_intLength = 0;
            if (l_lisOrderUnits != null)
            {
                l_intLength = l_lisOrderUnits.size();
            }
    
            for (int i = 0; i < l_intLength; i ++)
            {
                l_orderUnit = (EqTypeOrderUnit)l_lisOrderUnits.get(i);
                l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                
                // 注文単位が繰越元注文の場合（発注日≠取引時間管理.get発注日(void)）
                // 取得した空売り数量計上方法@＝"休憩時間帯扱いで計上"の場合
				if (!l_orderUnitRow.getBizDate().equals(WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd")) ||
                    WEB3ShortSellingCountMethodDivDef.COUNT_AS_REST_TIME_ZONE.equals(l_strShortSelCountMethodDiv))
                {
                    // 市場未送信の場合
                    if (l_orderUnitRow.getConfirmedQuantity() == 0)
                    {
                        // 注文数量を集計
                        l_dblTotalUnExecQuantity += l_orderUnitRow.getQuantity();
                        log.debug("注文数量を集計");
                        log.debug("l_orderUnitRow.getQuantity()=" + l_orderUnitRow.getQuantity());
                    }
                    // 市場送信済の場合
                    else
                    {
                        // 未約定数量（市場から確認済の数量 - 約定数量）を集計
                        l_dblTotalUnExecQuantity += l_orderUnitRow.getConfirmedQuantity()
                            - l_orderUnitRow.getExecutedQuantity();
                        log.debug("未約定数量（市場から確認済の数量 - 約定数量）を集計");
                        log.debug("l_orderUnitRow.getConfirmedQuantity()=" + l_orderUnitRow.getConfirmedQuantity());
                        log.debug("l_orderUnitRow.getExecutedQuantity()=" + l_orderUnitRow.getExecutedQuantity());
                    }
                }
                // 上記以外の場合
                else
                {
                    // 元注文数量を集計
                    l_dblTotalUnExecQuantity += l_orderUnitRow.getOriginalQuantity();
                    log.debug("元注文数量を集計");
                    log.debug("l_orderUnitRow.getOriginalQuantity()=" + l_orderUnitRow.getOriginalQuantity());
                }
                log.debug("l_dblTotalUnExecQuantity="+l_dblTotalUnExecQuantity);
            }
    
            // ５）　@指定銘柄の規制数量を計算する。
            // 　@　@　@規制数量 ＝ 引数の取引銘柄.売買単位 × ２）で取得した部店.空売り規制限度単位
            double l_dblValidateQuantity = ((EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject()).getLotSize()
                * l_dblShortMarginRestrainUnit;
            log.debug("指定銘柄の規制数量 = 取引銘柄.売買単位 × 部店.空売り規制限度単位");
            log.debug("取引銘柄.売買単位：" + ((EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject()).getLotSize());
            log.debug("部店.空売り規制限度単位：" + l_dblShortMarginRestrainUnit);
            log.debug("指定銘柄の規制数量：" + l_dblValidateQuantity);
            
            // ６）　@顧客属性により、空売り規制対象かどうかの判定を行う。
            // ６－１）　@顧客が一般投資家の場合
            // 　@　@・（（４）で集計した未約定数量 ＋ 引数の株数） <= ５）で計算した規制数量）の場合は、
            // 　@　@　@空売り規制チェックOKとしfalse（空売り規制対象外）を返す。
            //  　@　@※一般投資家の場合、数量が限度内の場合は価格規制対象とならず、
            //  　@　@※価格規制時チェックも不要。
            // 　@　@・（（４）で集計した未約定数量 ＋ 引数の株数） > ５）で計算した規制数量）に該当する場合
            // 　@　@　@７）以降の処理を行う。
            //
            // ６－２）　@顧客が適格機@関投資家の場合
            // 　@　@　@無条件で７）以降の処理を行う。
            if ((l_dblTotalUnExecQuantity + l_dblQuantity) <= l_dblValidateQuantity)
            {
                log.debug("(未約定数量 + 引数の株数) <= 規制数量の場合");
                log.debug("未約定数量" + l_dblTotalUnExecQuantity);
                log.debug("株数" + l_dblQuantity);
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        // ７）　@注文可／不可の判定、及び注文可の場合は価格規制対象時のチェックを行う。
        // ７－１）　@注文不可の部店の場合は「指定銘柄の空売り規制数量を超過」の例外をthrowする。
        if (WEB3ShortMarginRestrainDivDef.DISABLE_ORDER.equals(l_strShortMarginRestrainDiv))
        {
            log.debug("指定銘柄の空売り規制数量を超過");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00734,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        else
        {
            // ７－２）　@注文可の部店の場合
            //    部店.適格機@関投資家確認実施区分＝実施する の場合のみ、価格規制対象時のチェックを行う。
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strQualifiedInvestorConfirmDiv) == true)
            {
                // （１）引数の値段条件＝"指定なし"、かつ 引数のis成行＝true（成行注文）
                if ((WEB3PriceConditionDef.DEFAULT.equals(l_strPriceConditionType) == true) &&
                    (isMarketOrder == true))
                {
                    log.debug("成行指定不可（空売り規制数量超過時）");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01345,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                // （２）引数の執行条件＝"不出来引け成行"
                if (EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_execCondType))
                {
                    log.debug("執行条件に”不出来引け成行”指定不可（空売り規制数量超過時）");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01346,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                // （３）引数の値段条件＝（"優先値指値"、"成行残数指値"、"成行残数取消"）
                if (WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(l_strPriceConditionType) ||
                    WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(l_strPriceConditionType) ||
                    WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(l_strPriceConditionType))
                {
                    log.debug("値段条件に”優先値指値”、”成行残数指値”、”成行残数取消”指定不可（空売り規制数量超過時）");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01347,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * （validate新規建代金上限）。<BR>
     * <BR>
     * 新規建代金上限チェックを行う。<BR>
     * 　@－建代金上限を超えていないかチェックする。超えている場合はエラーとする。<BR>
     *   －建代金上限の種類は、総建上限／銘柄上限／市場銘柄別上限／１日上限 の４種類。<BR>
     * 　@　@４種類のいずれか１種類で上限を超えている場合、例外をthrowする。<BR>
     * 　@　@（上限の種類毎に例外クラスを分ける）<BR>
     * <BR>
     * １）　@引数の注文種別＝（”新規買建注文”（MARGIN_LONG）、”新規売建注文”（MARGIN_SHORT） の<BR> 
     * 　@　@　@場合は、以下の処理を行う。<BR>
     * 　@　@　@以外は、何もせずにそのままreturnする。<BR>
     * <BR>
     * ２）　@引数の補助口座より、顧客オブジェクトを取得し、<BR>
     * 　@　@　@顧客オブジェクトの<BR>
     * 　@　@　@　@口座タイプ<BR>
     * 　@　@　@を取得する。<BR>
     * <BR>
     * ３）　@引数の補助口座.get取引店( )で、部店オブジェクトを取得し、<BR>
     * 　@　@　@部店オブジェクトの<BR>
     * 　@　@　@　@建代金上限値（個人・総）　@　@　@　@　@：（*1*）<BR>
     * 　@　@　@　@建代金上限値（法@人・総）　@　@　@　@　@：（*2*）<BR>
     * 　@　@　@　@建代金上限値（個人・銘柄単位）：（*3*）<BR>
     * 　@　@　@　@建代金上限値（法@人・銘柄単位）：（*4*）<BR>
     * 　@　@　@　@建代金上限値（個人・一日）　@　@　@：（*5*）<BR>
     * 　@　@　@　@建代金上限値（法@人・一日）　@　@　@：（*6*）<BR>
     * 　@　@　@を取得する。<BR>
     * <BR>
     * 　@　@　@２）で取得した顧客.口座タイプ≠”法@人アカウント”の場合は、<BR>
     * 　@　@　@上記（*1*）（*3*）（*5*）の項目を以降のチェックで使用する。<BR>
     * 　@　@　@２）で取得した顧客.口座タイプ＝”法@人アカウント”の場合は、<BR> 
     * 　@　@　@上記（*2*）（*4*）（*6*）の項目を以降のチェックで使用する。<BR>
     * <BR>
     * ４）　@＜総建上限チェック＞<BR>
     * <BR>
     * 　@４－１）　@該当顧客の、全ての有効な建株オブジェクトを取得する。<BR>
     * <BR>　@　@　@------------------------------------------------------------------<BR>
     * 　@　@　@＜建株オブジェクト取得＞<BR>
     * 　@　@　@株式ポジションマネージャ.get建株一覧(<BR>
     * 　@　@　@　@　@　@　@　@引数の補助口座, <BR>
     * 　@　@　@　@　@　@　@　@銘柄タイプ（株式（EQUITY））,<BR>
     * 　@　@　@　@　@　@　@　@検索条件文字列（*1）,<BR>
     * 　@　@　@　@　@　@　@　@検索条件データコンテナ（*2）)<BR>
     * 　@　@　@　@　@　@　@　@により取得する。<BR>
     * <BR>
     * 　@　@　@（*1）建株数（"quantity"）を指定する。<BR>
     * 　@　@　@　@　@　@※"quantity > ?"<BR>
     * 　@　@　@（*2）建株数の検索条件指定値として、"0"を設定する。<BR>
     * <BR>　@　@　@------------------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@取得した建株オブジェクト全ての（建株数 × 建単価）の値を集計する。<BR>
     * <BR>
     * 　@４－２）　@該当顧客の、新規建注文中（約定成立待ち）の注文単位オブジェクトを全て取得する。<BR>
     * <BR>　@　@------------------------------------------------------------------<BR>
     * 　@　@　@＜注文単位オブジェクト取得＞<BR>
     * 　@　@　@拡張株式注文マネージャ.get注文単位一覧(<BR>
     * 　@　@　@　@　@　@　@　@引数の補助口座, <BR>
     * 　@　@　@　@　@　@　@　@銘柄タイプ（株式（EQUITY））,<BR>
     * 　@　@　@　@　@　@　@　@検索条件文字列（*1）,<BR>
     * 　@　@　@　@　@　@　@　@検索条件データコンテナ（*2）,<BR>
     * 　@　@　@　@　@　@　@　@null)<BR>
     * 　@　@　@　@　@　@　@　@により取得する。<BR>
     * <BR>
     * 　@　@　@（*1）注文カテゴリ（"order_categ"）、注文有効状態（"order_open_status"）を、<BR>
     * 　@　@　@　@　@　@and条件で指定する。<BR>
     * 　@　@　@　@　@　@※"order_categ = ? and order_open_status = ?"<BR>
     * <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合は、<BR>
     * 　@　@　@　@　@　@注文単位ID（"order_unit_id"）のnot指定を追加指定し、訂正対象注文を除外する。<BR>
     * 　@　@　@　@　@　@※" and order_unit_id != ?"<BR>
     * <BR>
     * 　@　@　@（*2）注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）のintValue<BR>
     * 　@　@　@　@　@　@注文有効状態：　@OrderOpenStatusEnum.”オープン”（OPEN）のintValue<BR>
     * <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合のみ、以下も設定。<BR>
     * 　@　@　@　@　@　@注文単位ID：　@引数の注文単位.注文単位ID<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@取得した注文単位オブジェクト全ての、以下の計算結果を集計する。<BR>
     * 　@　@　@--------------------------------<BR>
     * 　@　@　@　@各注文単位オブジェクトより、以下の通りに値を取得する。<BR>
     * <BR>
     * 　@　@　@　@・注文単位.市場から確認済みの数量＝nullの場合<BR>
     * 　@　@　@　@　@（注文単位.注文数量 × 注文単位.注文単価） を計算する。<BR>
     * <BR>
     * 　@　@　@　@・注文単位.市場から確認済みの数量≠nullの場合<BR>
     * 　@　@　@　@　@（（注文単位.市場から確認済みの数量 － 注文単位.約定数量）<BR>
     * 　@　@　@　@　@　@× 注文単位.市場から確認済の注文単価） を計算する。<BR>
     * <BR>
     * 　@　@　@　@※割増拘束方式の部店の場合は、<BR>
     * 　@　@　@　@※各注文単位の計算結果に、（成行注文の場合のみ）割増拘束率を掛け、<BR>
     * 　@　@　@　@※円未満を切り捨てした結果を集計する。<BR>
     * 　@　@　@--------------------------------<BR>
     * <BR>
     * 　@４－３）　@（引数の建代金(*1) ＋ ４－１）で計算した建代金 ＋ ４－２）で計算した代金）<BR>
     * 　@　@　@　@　@> 部店.建代金上限値（総）（*） の場合は、<BR>
     * 　@　@　@　@　@「建代金の上限値超過(総)」の例外をthrowする。<BR>
     * <BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00741<BR>
     * 　@　@　@（*）部店.建代金上限値（総）：３）で顧客.口座タイプにより判定した、（*1*）または（*2*）の値。<BR>
     * 　@　@　@(*1)引数の建代金： <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合は、<BR>
     * 　@　@　@　@　@　@引数の注文単位.合計約定金額を控除した結果を使用する。<BR>
     * <BR>
     * ５）　@＜銘柄上限チェック＞<BR>
     * <BR>
     * 　@５－１）　@該当顧客の、該当銘柄の全ての有効な建株オブジェクトを取得する。<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * 　@　@　@＜建株オブジェクト取得＞<BR>
     * 　@　@　@株式ポジションマネージャ.get建株一覧(<BR>
     * 　@　@　@　@　@　@　@　@引数の補助口座, <BR>
     * 　@　@　@　@　@　@　@　@銘柄タイプ（株式（EQUITY））,<BR>
     * 　@　@　@　@　@　@　@　@検索条件文字列（*1）,<BR>
     * 　@　@　@　@　@　@　@　@検索条件データコンテナ（*2）)<BR>
     * 　@　@　@　@　@　@　@　@により取得する。<BR>
     * <BR>
     * 　@　@　@（*1）銘柄ID（"product_id"）、建株数（"quantity"）を、<BR>
     * 　@　@　@　@　@　@and条件で指定する。<BR>
     * 　@　@　@　@　@　@※"product_id = ? and quantity > ?"<BR>
     * <BR>
     * 　@　@　@（*2）銘柄IDの検索条件指定値として、引数の取引銘柄.銘柄IDを設定する。<BR>
     * 　@　@　@　@　@　@建株数の検索条件指定値として、"0"を設定する。<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@取得した建株オブジェクト全ての（建株数 × 建単価）の値を集計する。<BR>
     * <BR>
     * 　@５－２）　@該当顧客の、新規建注文中（約定成立待ち）の該当銘柄の注文単位オブジェクトを全て<BR>
     * 　@　@　@　@　@取得する。<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * 　@　@　@＜注文単位オブジェクト取得＞<BR>
     * 　@　@　@拡張株式注文マネージャ.get注文単位一覧(<BR>
     * 　@　@　@　@　@　@　@　@引数の補助口座, <BR>
     * 　@　@　@　@　@　@　@　@銘柄タイプ（株式（EQUITY））,<BR>
     * 　@　@　@　@　@　@　@　@検索条件文字列（*1）,<BR>
     * 　@　@　@　@　@　@　@　@検索条件データコンテナ（*2）,<BR>
     * 　@　@　@　@　@　@　@　@null)<BR>
     * 　@　@　@　@　@　@　@　@により取得する。<BR>
     * <BR>
     * 　@　@　@（*1）銘柄ID（"product_id"）、注文カテゴリ（"order_categ"）、<BR>
     * 　@　@　@　@　@　@注文有効状態（"order_open_status"）を、<BR>
     * 　@　@　@　@　@　@and条件で指定する。<BR>
     * 　@　@　@　@　@　@※"product_id = ? and order_categ = ? and order_open_status = ?"<BR>
     * <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合は、<BR>
     * 　@　@　@　@　@　@注文単位ID（"order_unit_id"）のnot指定を追加指定し、訂正対象注文を除外する。<BR>
     * 　@　@　@　@　@　@※" and order_unit_id != ?"<BR>
     * <BR>
     * 　@　@　@（*2）銘柄ID：　@引数の取引銘柄.銘柄IDを設定<BR>
     * 　@　@　@　@　@　@注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）のintValue<BR>
     * 　@　@　@　@　@　@注文有効状態：　@OrderOpenStatusEnum.”オープン”（OPEN）のintValue<BR>
     * <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合のみ、以下も設定。<BR>
     * 　@　@　@　@　@　@注文単位ID：　@引数の注文単位.注文単位ID<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@取得した注文単位オブジェクト全ての、以下の計算結果を集計する。<BR>
     * 　@　@　@--------------------------------<BR>
     * 　@　@　@　@各注文単位オブジェクトより、以下の通りに値を取得する。<BR>
     * <BR>
     * 　@　@　@　@・注文単位.市場から確認済みの数量＝nullの場合<BR>
     * 　@　@　@　@　@（注文単位.注文数量 × 注文単位.注文単価） を計算する。<BR>
     * <BR>
     * 　@　@　@　@・注文単位.市場から確認済みの数量≠nullの場合<BR>
     * 　@　@　@　@　@（（注文単位.市場から確認済みの数量 － 注文単位.約定数量）<BR>
     * 　@　@　@　@　@　@× 注文単位.市場から確認済の注文単価） を計算する。<BR>
     * <BR>
     * 　@　@　@　@※割増拘束方式の部店の場合は、<BR>
     * 　@　@　@　@※各注文単位の計算結果に、（成行注文の場合のみ）割増拘束率を掛け、<BR>
     * 　@　@　@　@※円未満を切り捨てした結果を集計する。<BR>
     * 　@　@　@--------------------------------<BR>
     * <BR>
     * 　@５－３）　@（引数の建代金(*1) ＋ ５－１）で計算した建代金 ＋ ５－２）で計算した代金） > <BR>
     * 　@　@　@　@　@部店.建代金上限値（銘柄単位）（*） の場合は、<BR>
     * 　@　@　@　@　@「建代金の上限値超過(銘柄単位)」の例外をthrowする。<BR>
     * <BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00742<BR>
     * 　@　@　@（*）部店.建代金上限値（銘柄単位）：３）で顧客.口座タイプにより判定した、（*3*）または（*4*）の値。<BR>
     * 　@　@　@(*1)引数の建代金： <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合は、<BR>
     * 　@　@　@　@　@　@引数の注文単位.合計約定金額を控除した結果を使用する。<BR>
     * <BR>
     * ６）　@＜市場銘柄別上限チェック＞<BR>
     * <BR>
     * 　@６－１)　@市場銘柄別建代金の上限金額を取得する。<BR>
     * <BR>
     * 　@　@６－１－１)　@(部店市場上場区分別)取扱条件を生成する。<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@部店ＩＤ　@：　@３）で取得した部店オブジェクト.部店ID<BR>
     * 　@　@　@　@　@市場ＩＤ　@：　@引数の取引銘柄.市場ＩＤ<BR>
     * 　@　@　@　@　@上場区分　@：　@引数の取引銘柄.上場区分<BR>
     * 　@　@　@　@　@新市場区分　@：　@引数の取引銘柄.新市場区分<BR>
     * 　@　@　@　@　@店頭公開区分　@：　@引数の取引銘柄.店頭公開区分<BR>
     * <BR>
     * 　@　@６－１－２)　@(部店市場上場区分別)取扱条件.get建代金上限値()をコールする。<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@２）で取得した顧客オブジェクト<BR>
     * <BR>
     * 　@　@６－１－３)　@以下に該当する場合、市場銘柄別上限チェックをスキップする。<BR>
     * 　@　@　@　@６－１－１)でエラーがスローされた場合<BR>
     * 　@　@　@　@６－１－２)でget建代金上限値()の戻り値がnullの場合<BR>
     * <BR>
     * 　@６－２）　@該当顧客・市場・銘柄の全ての有効な建株オブジェクトを取得する。<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * 　@　@　@＜建株オブジェクト取得＞<BR>
     * 　@　@　@株式ポジションマネージャ.get建株一覧(<BR>
     * 　@　@　@　@　@　@　@　@引数の補助口座, <BR>
     * 　@　@　@　@　@　@　@　@銘柄タイプ（株式（EQUITY））,<BR>
     * 　@　@　@　@　@　@　@　@検索条件文字列（*1）,<BR>
     * 　@　@　@　@　@　@　@　@検索条件データコンテナ（*2）)<BR>
     * 　@　@　@　@　@　@　@　@により取得する。<BR>
     * <BR>
     * 　@　@　@（*1）市場ID("market_id")、銘柄ID（"product_id"）、建株数（"quantity"）を<BR>
     * 　@　@　@　@　@　@and条件で指定する。<BR>
     * 　@　@　@　@　@　@※"market_id = ? and product_id = ? and quantity > ?"<BR>
     * <BR>
     * 　@　@　@（*2）市場IDの検索条件指定値として、引数の取引銘柄.市場IDを設定する。<BR>
     * 　@　@　@　@　@　@銘柄IDの検索条件指定値として、引数の取引銘柄.銘柄IDを設定する。<BR>
     * 　@　@　@　@　@　@建株数の検索条件指定値として、"0"を設定する。<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@取得した建株オブジェクト全ての（建株数 × 建単価）の値を集計する。<BR>
     * <BR>
     * 　@６－３）　@該当顧客・市場・銘柄の新規建注文中（約定成立待ち）の注文単位オブジェクトを全て<BR>
     * 　@　@　@　@　@　@取得する。<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * 　@　@　@＜注文単位オブジェクト取得＞<BR>
     * 　@　@　@拡張株式注文マネージャ.get注文単位一覧(<BR>
     * 　@　@　@　@　@　@　@　@引数の補助口座, <BR>
     * 　@　@　@　@　@　@　@　@銘柄タイプ（株式（EQUITY））,<BR>
     * 　@　@　@　@　@　@　@　@検索条件文字列（*1）,<BR>
     * 　@　@　@　@　@　@　@　@検索条件データコンテナ（*2）,<BR>
     * 　@　@　@　@　@　@　@　@null)<BR>
     * 　@　@　@　@　@　@　@　@により取得する。<BR>
     * <BR>
     * 　@　@　@（*1）市場ID("market_id")、銘柄ID（"product_id"）、注文カテゴリ（"order_categ"）、<BR>
     * 　@　@　@　@　@　@注文有効状態（"order_open_status"）を、<BR>
     * 　@　@　@　@　@　@and条件で指定する。<BR>
     * 　@　@　@　@　@　@※"market_id = ? and product_id = ? and order_categ = ? and order_open_status = ?"<BR>
     * <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合は、<BR>
     * 　@　@　@　@　@　@注文単位ID（"order_unit_id"）のnot指定を追加指定し、訂正対象注文を除外する。<BR>
     * 　@　@　@　@　@　@※" and order_unit_id != ?"<BR>
     * <BR>
     * 　@　@　@（*2）市場ID：　@引数の取引銘柄.市場IDを設定<BR>
     * 　@　@　@　@　@　@銘柄ID：　@引数の取引銘柄.銘柄IDを設定<BR>
     * 　@　@　@　@　@　@注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）のintValue<BR>
     * 　@　@　@　@　@　@注文有効状態：　@OrderOpenStatusEnum.”オープン”（OPEN）のintValue<BR>
     * <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合のみ、以下も設定。<BR>
     * 　@　@　@　@　@　@注文単位ID：　@引数の注文単位.注文単位ID<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@取得した注文単位オブジェクト全ての、以下の計算結果を集計する。<BR>
     * 　@　@　@--------------------------------<BR>
     * 　@　@　@　@各注文単位オブジェクトより、以下の通りに値を取得する。<BR>
     * <BR>
     * 　@　@　@　@・注文単位.市場から確認済みの数量＝nullの場合<BR>
     * 　@　@　@　@　@（注文単位.注文数量 × 注文単位.注文単価） を計算する。<BR>
     * <BR>
     * 　@　@　@　@・注文単位.市場から確認済みの数量≠nullの場合<BR>
     * 　@　@　@　@　@（（注文単位.市場から確認済みの数量 － 注文単位.約定数量）<BR>
     * 　@　@　@　@　@　@× 注文単位.市場から確認済の注文単価） を計算する。<BR>
     * <BR>
     * 　@　@　@　@※割増拘束方式の部店の場合は、<BR>
     * 　@　@　@　@※各注文単位の計算結果に、（成行注文の場合のみ）割増拘束率を掛け、<BR>
     * 　@　@　@　@※円未満を切り捨てした結果を集計する。<BR>
     * 　@　@　@--------------------------------<BR>
     * <BR>
     * 　@６－４）　@（引数の建代金（*1） ＋ ６－２）で計算した建代金 ＋ ６－３）で計算した代金） ><BR>
     * 　@　@　@　@　@　@建代金上限値（*2） の場合は、<BR>
     * 　@　@　@　@　@　@「建代金の上限値超過(市場銘柄別)」の例外をthrowする。<BR>
     * <BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02870<BR>
     * 　@　@　@(*1)引数の建代金：<BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合は、<BR>
     * 　@　@　@　@　@　@引数の注文単位.合計約定金額を控除した結果を使用する。<BR>
     * 　@　@　@（*2）６－１－２）で取得した値<BR>
     * <BR>
     * ７）　@＜一日上限チェック＞<BR>
     * <BR>
     * 　@７－１）　@該当顧客の、一日上限チェックの対象の注文単位オブジェクトを全て取得し、<BR>
     * 　@　@　@　@　@建代金を集計する。<BR>
     * <BR>
     * 　@　@７－１－１）　@該当顧客の、指定日分の新規建注文の注文単位オブジェクトを全て取得する。<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * 　@　@　@＜注文単位オブジェクト取得＞<BR>
     * 　@　@　@拡張株式注文マネージャ.get注文単位一覧(<BR>
     * 　@　@　@　@　@　@　@　@引数の補助口座, <BR>
     * 　@　@　@　@　@　@　@　@銘柄タイプ（株式（EQUITY））,<BR>
     * 　@　@　@　@　@　@　@　@検索条件文字列（*1）,<BR>
     * 　@　@　@　@　@　@　@　@検索条件データコンテナ（*2）,<BR>
     * 　@　@　@　@　@　@　@　@null)<BR>
     * 　@　@　@　@　@　@　@　@により取得する。<BR>
     * <BR>
     * 　@　@　@（*1）注文カテゴリ（"order_categ"）、発注日（"biz_date"）、注文状態（"order_status"）を、<BR>
     * 　@　@　@　@　@　@and条件（ただし、注文状態はNOT EQUAL指定）で指定する。<BR>
     * 　@　@　@　@　@　@※"order_categ = ? and biz_date = ? and order_status != ?"<BR>
     * <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合は、<BR>
     * 　@　@　@　@　@　@注文単位ID（"order_unit_id"）のnot指定を追加指定し、訂正対象注文を除外する。<BR>
     * 　@　@　@　@　@　@※" and order_unit_id != ?"<BR>
     * <BR>
     * 　@　@　@（*2）注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）のintValue<BR>
     * 　@　@　@　@　@　@発注日：　@取引時間管理.get発注日(void)の戻り値<BR>
     * 　@　@　@　@　@　@注文状態：　@OrderStatusEnum.”発注失敗（新規注文）”（NOT_ORDERED）のintValue<BR>
     * <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合のみ、以下も設定。<BR>
     * 　@　@　@　@　@　@注文単位ID：　@引数の注文単位.注文単位ID<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@取得した注文単位オブジェクト全ての、以下の計算結果を集計する。<BR>
     * 　@　@　@--------------------------------<BR>
     * 　@　@　@　@各注文単位オブジェクトの注文有効状態、市場から確認済みの数量により、以下の通りに計算する。<BR>
     * <BR>
     * 　@　@　@　@・注文単位.注文有効状態＝OrderOpenStatusEnum.”クローズ”（CLOSED）の場合は、<BR>
     * 　@　@　@　@　@注文単位.合計約定金額 を使用する。<BR>
     * <BR>
     * 　@　@　@　@・注文単位.注文有効状態≠OrderOpenStatusEnum.”クローズ”（CLOSED）の場合は、<BR>
     * 　@　@　@　@　@未出来分は注文単価を掛けた値を、出来分は合計約定金額を使用する。<BR>
     * <BR>
     * 　@　@　@　@　@＜注文単位.市場から確認済みの数量＝nullの場合＞<BR>
     * 　@　@　@　@　@（注文単位.注文数量 × 注文単位.注文単価） を計算する。<BR>
     * <BR>
     * 　@　@　@　@　@＜注文単位.市場から確認済みの数量≠nullの場合＞<BR>
     * 　@　@　@　@　@　@（（（注文単位.市場から確認済の数量 － 注文単位.約定数量）<BR>
     * 　@　@　@　@　@　@　@× 注文単位.市場から確認済の注文単価）<BR>
     * 　@　@　@　@　@　@＋ 注文単位.合計約定金額） を計算する。<BR>
     * <BR>
     * 　@　@　@　@※割増拘束方式の部店の場合は、<BR>
     * 　@　@　@　@※各注文単位の未約定分の計算結果に、（成行注文の場合のみ）割増拘束率を掛け、<BR>
     * 　@　@　@　@※円未満を切り捨てした結果を集計する。<BR>
     * 　@　@　@--------------------------------<BR>
     * <BR>
     * 　@　@７－１－２）　@市場閉局時間帯（場引け後）の場合<BR>
     * 　@　@　@　@　@　@　@　@（取引時間管理.is市場開局時間帯( ) == falseの場合）のみ、<BR>
     * 　@　@　@　@　@　@　@　@翌日以降も有効な、新規建の「出来るまで注文」の注文単位オブジェクトを<BR>
     * 　@　@　@　@　@　@　@　@全て取得する。<BR>
     * 　@　@　@　@　@　@　@　@※市場開局時間帯の場合<BR>
     * 　@　@　@　@　@　@　@　@※（取引時間管理.is市場開局時間帯( ) == trueの場合）は、取得をスキップする。<BR>
     * <BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * 　@　@　@＜注文単位オブジェクト取得＞<BR>
     * 　@　@　@拡張株式注文マネージャ.get注文単位一覧(<BR>
     * 　@　@　@　@　@　@　@　@引数の補助口座, <BR>
     * 　@　@　@　@　@　@　@　@銘柄タイプ（株式（EQUITY））,<BR>
     * 　@　@　@　@　@　@　@　@検索条件文字列（*1）,<BR>
     * 　@　@　@　@　@　@　@　@検索条件データコンテナ（*2）,<BR>
     * 　@　@　@　@　@　@　@　@null)<BR>
     * 　@　@　@　@　@　@　@　@により取得する。<BR>
     * <BR>
     * 　@　@　@（*1）注文カテゴリ（"order_categ"）、注文有効状態（"order_open_status"）、<BR>
     * 　@　@　@　@　@　@発注日（"biz_date"）、注文失効日付（"expiration_date"）を、<BR>
     * 　@　@　@　@　@　@and条件で指定する。<BR>
     * 　@　@　@　@　@　@※"order_categ = ? and order_open_status = ? and biz_date = ?<BR>
     * 　@　@　@　@　@　@※and expiration_date >= ? and first_order_unit_id is not null"（*3）<BR>
     * <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合は、<BR>
     * 　@　@　@　@　@　@注文単位ID（"order_unit_id"）のnot指定を追加指定し、訂正対象注文を除外する。<BR>
     * 　@　@　@　@　@　@※" and order_unit_id != ?"<BR>
     * <BR>
     * 　@　@　@（*2）注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）のintValue<BR>
     * 　@　@　@　@　@　@注文有効状態：　@OrderOpenStatusEnum.”オープン”（OPEN）のintValue<BR>
     * 　@　@　@　@　@　@発注日：　@取引時間管理.get発注日(void)の前営業日<BR>
     * 　@　@　@　@　@　@注文失効日付：　@取引時間管理.get発注日(void) の戻り値<BR>
     * <BR>
     * 　@　@　@　@　@　@引数の注文単位オブジェクト≠null（＝注文の訂正）の場合のみ、以下も設定。<BR>
     * 　@　@　@　@　@　@注文単位ID：　@引数の注文単位.注文単位ID<BR>
     * <BR>
     * 　@　@　@（*3）出来るまで注文指定を、初回注文の注文単位ID is not null 指定により行う。<BR>
     * 　@　@　@------------------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@取得した注文単位オブジェクト全ての、<BR>
     * 　@　@　@（（注文単位.注文数量 － 注文単位.約定数量） × 注文単位.注文単価）の<BR>
     * 　@　@　@値を集計する。<BR>
     * <BR>
     * 　@　@　@※割増拘束方式の部店の場合は、<BR>
     * 　@　@　@※各注文単位の計算結果に、（成行注文の場合のみ）割増拘束率を掛け、<BR>
     * 　@　@　@※円未満を切り捨てした結果を集計する。<BR>
     * <BR>
     * 　@７－２）　@（引数の建代金 ＋ ７－１－１）で計算した新規建注文の建代金<BR>
     * 　@　@　@　@　@＋ ７－１－２）で計算した翌日以降も有効な出来るまで注文の建代金） ><BR>
     * 　@　@　@　@　@部店.建代金上限値（一日）（*） の場合は、<BR>
     * 　@　@　@　@　@「建代金の上限値超過(一日)」の例外をthrowする。<BR>
     * <BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00743<BR>
     * 　@　@　@（*）部店.建代金上限値（一日）：３）で顧客.口座タイプにより判定した、（*5*）または（*6*）の値。<BR>
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@補助口座オブジェクト（信用取引口座）。
     * @@param l_dblAmount 建代金。
     * @@param l_orderType 注文種別。（xTradeのOrderTypeEnumにて定義）<BR>
     * 　@　@　@（現物買注文／新規買建注文／現引注文）
     * @@param l_tradedProduct 取引銘柄。
     * @@param l_changeOrderUnit 訂正対象注文単位オブジェクト
     * @@throws WEB3BaseException
     */
    public void validateMaxOpenMarginAmount(
        WEB3GentradeSubAccount l_subAccount, 
        double l_dblAmount, 
        OrderTypeEnum l_orderType, 
        WEB3EquityTradedProduct l_tradedProduct,
        EqtypeOrderUnitRow l_changeOrderUnit) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMaxOpenMarginAmount(l_subAccount,l_dblAmount,l_orderType,l_tradedProduct,l_changeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        long l_lngProductId;
        l_lngProductId = l_tradedProduct.getProduct().getProductId();
        // 取引銘柄Row
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();

        if (!OrderTypeEnum.MARGIN_SHORT.equals(l_orderType) && !OrderTypeEnum.MARGIN_LONG.equals(l_orderType))
        {
            //引数の注文種別＝（”新規買建注文”（MARGIN_LONG）、”新規売建注文”（MARGIN_SHORT） の 
            //場合は、以下の処理を行う。 以外は、何もせずにそのままreturnする。 
            return;
        }
        
        double l_dblNotExecutedContractAmount = l_dblAmount;
        if (l_changeOrderUnit != null)
        {
            l_dblNotExecutedContractAmount -= l_changeOrderUnit.getExecutedAmount();
        }
        log.debug("l_dblNotExecutedContractAmount="+l_dblNotExecutedContractAmount);
        
        //顧客を取得
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //部店を取得
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();

        // 会社部店商品テーブルを読み込む
        InstBranchProductRow l_instBranchProductRow;
        try
        {
            l_instBranchProductRow =
                InstBranchProductDao.findRowByPk(
                    l_branch.getBranchId(),
                    WEB3CommisionProductCodeDef.LISTING_STOCK);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        if (l_instBranchProductRow == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "会社部店商品テーブルに該当するデータがありません。");
        }
        //割増拘束率を取得（STOP高拘束方式部店の場合は、"1"が設定されている）
        BigDecimal l_bdPremiumRestraintRate =
            new BigDecimal(l_instBranchProductRow.getPremiumRestraintRate());
        
        //建代金上限値・総
        double l_dblMaxContPriceAll = 0D;
        double l_dblMaxContPriceProduct = 0D;
        double l_dblMaxContPrice1day = 0D;
        
        if (MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountRow.getAccountType()))
        {
            //顧客.口座タイプ＝”法@人アカウント”の場合は、 
            //建代金上限値（法@人・総）　@
            //建代金上限値（法@人・銘柄単位）
            //建代金上限値（法@人・一日）
            //の項目を以降のチェックで使用する。 
            l_dblMaxContPriceAll = l_branchRow.getMaxContPriceAllCorp() ;
            l_dblMaxContPriceProduct = l_branchRow.getMaxContPriceProductCorp();
            l_dblMaxContPrice1day = l_branchRow.getMaxContPrice1dayCorp();
        }
        else
        {
            //顧客.口座タイプ≠”法@人アカウント”の場合は、 
            //建代金上限値（個人・総
            //建代金上限値（個人・銘柄単位）
            //建代金上限値（個人・一日）
            //の項目を以降のチェックで使用する。
            l_dblMaxContPriceAll = l_branchRow.getMaxContPriceAllInd() ;
            l_dblMaxContPriceProduct = l_branchRow.getMaxContPriceProductInd();
            l_dblMaxContPrice1day = l_branchRow.getMaxContPrice1dayInd();             
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //拡張プロダクトマネージャを取得
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();
        
        //該当顧客の、全ての有効な建株オブジェクトを取得する。
        
        //検索条件文字列 quantity > ?
        String l_strWhere = " and quantity > ?";
        //検索条件データコンテナ  0
        String[] l_objWhere = {"0"};
        
        List l_lisEffictiveContracts = l_positionManager.getContracts(
            l_subAccount, ProductTypeEnum.EQUITY, l_strWhere, l_objWhere);
        
        int l_intLen = 0;
        if (l_lisEffictiveContracts != null)
        {
            l_intLen = l_lisEffictiveContracts.size();    
        }
        
        //取得した建株オブジェクト全ての（建株数 × 建単価）の値を集計する。
        double l_dblTotalEffictiveAmount = 0D;
        for (int i = 0; i < l_intLen; i++)
        {
            EqtypeContractRow l_contractRow = (EqtypeContractRow)l_lisEffictiveContracts.get(i);
            
            l_dblTotalEffictiveAmount += Math.floor(l_contractRow.getQuantity() * l_contractRow.getContractPrice());
        }
        log.debug("l_dblTotalEffictiveAmount="+l_dblTotalEffictiveAmount);
                
        //該当顧客の、新規建注文中（約定成立待ち）の注文単位オブジェクトを全て取得する。
        //(注文の訂正の場合は訂正対象注文を除外する。)
        
        List l_lisWheres2 = new ArrayList();
        
        //検索条件文字列 注文カテゴリ = ? and 注文有効状態 = ?
        String l_strWhere2 = "order_categ = ? and order_open_status = ?";
        //検索条件データコンテナ:   
        //注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN） 
        //注文有効状態：　@OrderOpenStatusEnum.”オープン”（OPEN）
        
        l_lisWheres2.add(String.valueOf(OrderCategEnum.OPEN_MARGIN.intValue()));
        l_lisWheres2.add(String.valueOf(OrderOpenStatusEnum.OPEN.intValue()));        
        
        if (l_changeOrderUnit != null)
        {
           //and 注文単位ID <> ?
           l_strWhere2 += " and order_unit_id <> ?";             
           //注文単位ID：  引数の訂正対象注文単位オブジェクト.注文単位ID
           l_lisWheres2.add(Long.toString(l_changeOrderUnit.getOrderUnitId())); 
        }
        Object[] l_objWhere2 = new Object[l_lisWheres2.size()];
        l_lisWheres2.toArray(l_objWhere2);
        
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        
        List l_lisOpenOrderUnits = l_orderManager.getOrderUnits(
            l_subAccount, ProductTypeEnum.EQUITY, l_strWhere2, l_objWhere2,null);
        
        if (l_lisOpenOrderUnits == null)
        {
            l_intLen = 0;
        }
        else
        {
            l_intLen = l_lisOpenOrderUnits.size();
        }
        
        double l_dblTotalOpenQuantity = 0D;
        //取得した注文単位オブジェクト全ての、以下の計算結果を集計する。
        for (int i = 0; i < l_intLen; i ++)
        {
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOpenOrderUnits.get(i);
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            double l_dblQuantity;
            double l_dblPrice;
            if (l_orderUnitRow.getConfirmedQuantityIsNull() == true)
            {
                //注文単位.市場から確認済みの数量＝nullの場合
                //（注文単位.注文数量 × 注文単位.注文単価） を計算する。
                l_dblQuantity = l_orderUnitRow.getQuantity();
                l_dblPrice = l_orderUnitRow.getPrice();
            }
            else
            {
                //注文単位.市場から確認済みの数量≠nullの場合 <BR>
                //　@（（注文単位.市場から確認済の数量 － 注文単位.約定数量）
                //　@　@　@　@× 注文単位.市場から確認済の注文単価） を計算する。
                l_dblQuantity = l_orderUnitRow.getConfirmedQuantity() - l_orderUnitRow.getExecutedQuantity();
                l_dblPrice = l_orderUnitRow.getConfirmedOrderPrice();
            }
            double l_dblOpenAmount = l_dblQuantity * l_dblPrice;
            if (l_orderUnit.isMarketOrder())
            {
                l_dblOpenAmount = Math.floor(
                    new BigDecimal(l_dblOpenAmount)
                    .multiply(l_bdPremiumRestraintRate).doubleValue());
            }
            l_dblTotalOpenQuantity += l_dblOpenAmount;
        }
        log.debug("l_dblTotalOpenQuantity="+l_dblTotalOpenQuantity);
        log.debug("建代金（総建）l_dblNotExecutedContractAmount + l_dblTotalEffictiveAmount + l_dblTotalOpenQuantity="
            + (l_dblNotExecutedContractAmount + l_dblTotalEffictiveAmount + l_dblTotalOpenQuantity));

        if ((l_dblNotExecutedContractAmount + l_dblTotalEffictiveAmount + l_dblTotalOpenQuantity) > l_dblMaxContPriceAll)
        {
            //建代金の上限値超過(総)の場合
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00741,STR_METHOD_NAME);
        }
        
        //銘柄上限チェックを行う。
        
        //該当顧客の、該当銘柄の全ての有効な建株オブジェクトを取得する。
        
        //検索条件文字列: 銘柄ID（"product_id"） = ?、建株数（"quantity"）> ?
        String l_strWhere3 = " and product_id = ? and quantity > ?";
        //検索条件データコンテナ: 
        //銘柄ID = 引数の銘柄ID
        //建株数 = "0"
        String[] l_objWhere3 = {"" + l_lngProductId,"0"};
        
        List l_lisEffictiveContractsForProduct =
            l_positionManager.getContracts(l_subAccount, ProductTypeEnum.EQUITY, l_strWhere3, l_objWhere3);
        
        if (l_lisEffictiveContractsForProduct == null)
        {
            l_intLen = 0;
        }
        else
        {
            l_intLen = l_lisEffictiveContractsForProduct.size();
        }
        //取得した建株オブジェクト全ての（建株数 × 建単価）の値を集計する。         
        double l_dblTotalEffictiveAmountForProduct = 0D;        
        for (int i = 0; i < l_intLen; i ++)
        {
            EqtypeContractRow l_contractRow = (EqtypeContractRow)l_lisEffictiveContractsForProduct.get(i);
            
            l_dblTotalEffictiveAmountForProduct +=
                Math.floor(l_contractRow.getQuantity() * l_contractRow.getContractPrice());
        }
        log.debug("l_dblTotalEffictiveAmountForProduct="+l_dblTotalEffictiveAmountForProduct);
        
        //該当顧客の、新規建注文中（約定成立待ち）の該当銘柄の注文単位オブジェクトを全て取得する。
        //(注文の訂正の場合は訂正対象注文を除外する。)
        
        //検索条件文字列 銘柄ID = ? 注文カテゴリ = ? and 注文有効状態 = ?
        String l_strWhere4 = "product_id = ? and order_categ = ? and order_open_status = ?";
        //検索条件データコンテナ:   
        //銘柄ID：　@引数の銘柄ID
        //注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN） 
        //注文有効状態：　@OrderOpenStatusEnum.”オープン”（OPEN）
        
        List l_lisWheres4 = new ArrayList();
        l_lisWheres4.add(String.valueOf(String.valueOf(l_lngProductId)));
        l_lisWheres4.add(String.valueOf(OrderCategEnum.OPEN_MARGIN.intValue()));        
        l_lisWheres4.add(String.valueOf(String.valueOf(OrderOpenStatusEnum.OPEN.intValue())));
        
        if (l_changeOrderUnit != null)
        {
            //and 注文単位ID <> ?
            l_strWhere4 += " and order_unit_id <> ?";             
            //注文単位ID：  引数の訂正対象注文単位オブジェクト.注文単位ID
            l_lisWheres4.add(Long.toString(l_changeOrderUnit.getOrderUnitId()));
        }
        Object[] l_objWhere4 = new Object[l_lisWheres4.size()];
        l_lisWheres4.toArray(l_objWhere4);
               
        List l_lisOpenOrderUnitsForProduct = l_orderManager.getOrderUnits(
            l_subAccount, ProductTypeEnum.EQUITY, l_strWhere4, l_objWhere4,null);
        
        if (l_lisOpenOrderUnitsForProduct == null)
        {
            l_intLen = 0;
        }
        else
        {
            l_intLen = l_lisOpenOrderUnitsForProduct.size();
        }
        
        double l_dblTotalOpenQuantityForProdcut = 0D;
        //取得した注文単位オブジェクト全ての、以下の計算結果を集計する。
        for (int i = 0; i < l_intLen; i ++)
        {
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOpenOrderUnitsForProduct.get(i);
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            double l_dblQuantity;
            double l_dblPrice;
            if (l_orderUnitRow.getConfirmedQuantityIsNull() == true)
            {
                //注文単位.市場から確認済みの数量＝nullの場合
                //（注文単位.注文数量 × 注文単位.注文単価） を計算する。
                l_dblQuantity = l_orderUnitRow.getQuantity();
                l_dblPrice = l_orderUnitRow.getPrice();
            }
            else
            {
                //注文単位.市場から確認済みの数量≠nullの場合 <BR>
                //　@（（注文単位.市場から確認済の数量 － 注文単位.約定数量）
                //　@　@　@　@× 注文単位.市場から確認済の注文単価） を計算する。
                l_dblQuantity = l_orderUnitRow.getConfirmedQuantity() - l_orderUnitRow.getExecutedQuantity();
                l_dblPrice = l_orderUnitRow.getConfirmedOrderPrice();
            }
            double l_dblOpenAmount = l_dblQuantity * l_dblPrice;
            if (l_orderUnit.isMarketOrder())
            {
                l_dblOpenAmount = Math.floor(
                    new BigDecimal(l_dblOpenAmount)
                    .multiply(l_bdPremiumRestraintRate).doubleValue());
            }

            l_dblTotalOpenQuantityForProdcut += l_dblOpenAmount;
        }        
        log.debug("l_dblTotalOpenQuantityForProdcut="+l_dblTotalOpenQuantityForProdcut);
        log.debug("建代金（銘柄単位）l_dblNotExecutedContractAmount + l_dblTotalEffictiveAmountForProduct + l_dblTotalOpenQuantityForProdcut="
            + (l_dblNotExecutedContractAmount + l_dblTotalEffictiveAmountForProduct + l_dblTotalOpenQuantityForProdcut));

        if ((l_dblNotExecutedContractAmount + l_dblTotalEffictiveAmountForProduct + l_dblTotalOpenQuantityForProdcut)
             > l_dblMaxContPriceProduct)
        {
            //建代金の上限値超過(銘柄単位)の場合
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00742,STR_METHOD_NAME);
        }
        
        //市場銘柄別上限チェックを行う。        
        //市場銘柄別建代金の上限金額を取得する。
        Double l_dblMaxAmountByMarketProduct = null;
        try
        { 
            //(部店市場上場区分別)取扱条件を生成する
            WEB3GentradeBranchListmarketDealtCond l_BranchListmarketDealtCondwk
                = new WEB3GentradeBranchListmarketDealtCond(
                    l_branchRow.getBranchId(),
                    l_tradedProduct.getMarket().getMarketId(),
                    l_tradedProductRow.getListType(),
                    l_tradedProductRow.getNewListType(),
                    l_tradedProductRow.getOpenOtcDiv());

            l_dblMaxAmountByMarketProduct = l_BranchListmarketDealtCondwk.getMaxContPrice(l_mainAccount);
        }
        catch (WEB3SystemLayerException e) {}
        
        if (l_dblMaxAmountByMarketProduct != null)
        {
            //該当顧客の、該当銘柄の全ての有効な建株オブジェクトを取得する。        
            //検索条件文字列: 市場ID（"market_id"） = ?、銘柄ID（"product_id"） = ?、建株数（"quantity"）> ?
            String l_strWhere7 = " and market_id = ? and product_id = ? and quantity > ?";
            //検索条件データコンテナ: 
            //市場ID = 引数の市場ID
            //銘柄ID = 引数の銘柄ID
            //建株数 = "0"
            String[] l_objWhere7 = {"" + l_tradedProductRow.getMarketId(),"" + l_lngProductId,"0"};
            List l_lisEffictiveContractsForProduct2 =
                l_positionManager.getContracts(l_subAccount, ProductTypeEnum.EQUITY, l_strWhere7, l_objWhere7);
            if (l_lisEffictiveContractsForProduct2 == null)
            {
                l_intLen = 0;
            }
            else
            {
                l_intLen = l_lisEffictiveContractsForProduct2.size();
            }
            //取得した建株オブジェクト全ての（建株数 × 建単価）の値を集計する。
            double l_dblTotalEffictiveAmountForProduct2 = 0D;
            for (int i = 0; i < l_intLen; i ++)
            {
                EqtypeContractRow l_contractRow = (EqtypeContractRow)l_lisEffictiveContractsForProduct2.get(i);

                l_dblTotalEffictiveAmountForProduct2 +=
                    Math.floor(l_contractRow.getQuantity() * l_contractRow.getContractPrice());
            }
            log.debug("l_dblTotalEffictiveAmountForProduct2=" + l_dblTotalEffictiveAmountForProduct2);

            //該当顧客の、新規建注文中（約定成立待ち）の該当銘柄の注文単位オブジェクトを全て取得する。
            //(注文の訂正の場合は訂正対象注文を除外する。)
            //検索条件文字列 市場ID = ? 銘柄ID = ? 注文カテゴリ = ? and 注文有効状態 = ?
            String l_strWhere8 = "market_id = ? and product_id = ? and order_categ = ? and order_open_status = ?";
            //検索条件データコンテナ:   
            //市場ID：  引数の市場ID
            //銘柄ID：　@引数の銘柄ID
            //注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN） 
            //注文有効状態：　@OrderOpenStatusEnum.”オープン”（OPEN）

            List l_lisWheres8 = new ArrayList();
            l_lisWheres8.add(String.valueOf(l_tradedProductRow.getMarketId()));
            l_lisWheres8.add(String.valueOf(l_lngProductId));
            l_lisWheres8.add(String.valueOf(OrderCategEnum.OPEN_MARGIN.intValue()));
            l_lisWheres8.add(String.valueOf(OrderOpenStatusEnum.OPEN.intValue()));

            if (l_changeOrderUnit != null)
            {
                //and 注文単位ID <> ?
                l_strWhere8 += " and order_unit_id <> ?";             
                //注文単位ID：  引数の訂正対象注文単位オブジェクト.注文単位ID
                l_lisWheres8.add(Long.toString(l_changeOrderUnit.getOrderUnitId()));
            }
            Object[] l_objWhere8 = new Object[l_lisWheres8.size()];
            l_lisWheres8.toArray(l_objWhere8);

            List l_lisOpenOrderUnitsForProduct2 = l_orderManager.getOrderUnits(
                l_subAccount, ProductTypeEnum.EQUITY, l_strWhere8, l_objWhere8, null);

            if (l_lisOpenOrderUnitsForProduct2 == null)
            {
                l_intLen = 0;
            }
            else
            {
                l_intLen = l_lisOpenOrderUnitsForProduct2.size();
            }

            double l_dblTotalOpenQuantityForProdcut2 = 0D;
            //取得した注文単位オブジェクト全ての、以下の計算結果を集計する。
            for (int i = 0; i < l_intLen; i ++)
            {
                EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOpenOrderUnitsForProduct2.get(i);
                EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

                double l_dblQuantity;
                double l_dblPrice;
                if (l_orderUnitRow.getConfirmedQuantityIsNull() == true)
                {
                    //注文単位.市場から確認済みの数量＝nullの場合
                    //（注文単位.注文数量 × 注文単位.注文単価） を計算する。
                    l_dblQuantity = l_orderUnitRow.getQuantity();
                    l_dblPrice = l_orderUnitRow.getPrice();
                }
                else
                {
                    //注文単位.市場から確認済みの数量≠nullの場合 <BR>
                    //　@（（注文単位.市場から確認済の数量 － 注文単位.約定数量）
                    //　@　@　@　@× 注文単位.市場から確認済の注文単価） を計算する。
                    l_dblQuantity = l_orderUnitRow.getConfirmedQuantity() - l_orderUnitRow.getExecutedQuantity();
                    l_dblPrice = l_orderUnitRow.getConfirmedOrderPrice();
                }
                double l_dblOpenAmount = l_dblQuantity * l_dblPrice;
                if (l_orderUnit.isMarketOrder())
                {
                    l_dblOpenAmount = Math.floor(
                    new BigDecimal(l_dblOpenAmount)
                    .multiply(l_bdPremiumRestraintRate).doubleValue());
                }

                l_dblTotalOpenQuantityForProdcut2 += l_dblOpenAmount;
            }
            log.debug("l_dblTotalOpenQuantityForProdcut2=" + l_dblTotalOpenQuantityForProdcut2);
            log.debug("建代金（市場銘柄単位）l_dblNotExecutedContractAmount + l_dblTotalEffictiveAmountForProduct2 + l_dblTotalOpenQuantityForProdcut2="
                + (l_dblNotExecutedContractAmount +
                    l_dblTotalEffictiveAmountForProduct2 +
                    l_dblTotalOpenQuantityForProdcut2));
            double l_dblMaxAmountByMarketProductwk = l_dblMaxAmountByMarketProduct.doubleValue();
            if ((l_dblNotExecutedContractAmount + l_dblTotalEffictiveAmountForProduct2 + l_dblTotalOpenQuantityForProdcut2)
                > l_dblMaxAmountByMarketProductwk)
            {
                //建代金の上限値超過(市場銘柄単位)の場合
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02870,STR_METHOD_NAME);
            }
        }
        //一日上限チェックを行う。
        //該当顧客の、一日上限チェックの対象の注文単位オブジェクトを全て取得し、 
        //建代金を集計する。 
                
        //検索条件文字列:
        //注文カテゴリ（"order_categ"） = ? and 発注日（"biz_date"） = ? and 注文状態（"order_status"）!= ?
        //(注文の訂正の場合は訂正対象注文を除外する。)
        
        String l_strWhere5= "order_categ = ? and biz_date = ? and order_status <> ?";
        //検索条件データコンテナ:
        //注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）
        //発注日：　@取引時間管理.get発注日()
        //注文状態：　@OrderStatusEnum.”発注失敗（新規注文）”（NOT_ORDERED）
        String l_strdate = WEB3DateUtility.formatDate(WEB3GentradeTradingTimeManagement.getOrderBizDate(),
            "yyyyMMdd"); 
        List l_lisWheres5 = new ArrayList();
        
        l_lisWheres5.add(String.valueOf(OrderCategEnum.OPEN_MARGIN.intValue()));
        l_lisWheres5.add(l_strdate);        
        l_lisWheres5.add(String.valueOf(OrderStatusEnum.NOT_ORDERED.intValue()));
        
        if (l_changeOrderUnit != null)
        {
            //and 注文単位ID <> ?
            l_strWhere5 += " and order_unit_id <> ?";             
            //注文単位ID：  引数の訂正対象注文単位オブジェクト.注文単位ID
            l_lisWheres5.add(Long.toString(l_changeOrderUnit.getOrderUnitId())); 
        }
        Object[] l_objWhere5 = new Object[l_lisWheres5.size()];
        l_lisWheres5.toArray(l_objWhere5);
                
        //該当顧客の、指定日分の新規建注文の注文単位オブジェクトを全て取得する。
        List l_lisOrderUnitsFor1Day = l_orderManager.getOrderUnits(
            l_subAccount, ProductTypeEnum.EQUITY, l_strWhere5, l_objWhere5, null);
        
        if (l_lisOrderUnitsFor1Day == null)
        {
            l_intLen = 0;
        }
        else
        {
            l_intLen = l_lisOrderUnitsFor1Day.size();
        }
        
        //取得した注文単位オブジェクト全ての、以下の計算結果を集計する。 
        double l_dblTotalAmountFor1Day = 0D;
        for (int i = 0; i < l_intLen; i++)
        {
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOrderUnitsFor1Day.get(i);
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnitRow.getOrderOpenStatus()))
            {
                //注文単位.注文有効状態＝OrderOpenStatusEnum.”クローズ”（CLOSED）の場合は、 
                //注文単位.合計約定金額 を使用する。 
                l_dblTotalAmountFor1Day += l_orderUnitRow.getExecutedAmount();
            }
            else
            {
                double l_dblQuantity;
                double l_dblPrice;

                //注文単位.注文有効状態≠OrderOpenStatusEnum.”クローズ”（CLOSED）の場合は、  
                //未出来分は注文単価を掛けた値を、出来分は合計約定金額を使用する。 
                
                if (l_orderUnitRow.getConfirmedQuantityIsNull() == true)
                {
                    //注文単位.市場から確認済みの数量＝nullの場合
                    //（注文単位.注文数量 × 注文単位.注文単価） を計算する。
                    l_dblQuantity = l_orderUnitRow.getQuantity();
                    l_dblPrice = l_orderUnitRow.getPrice();
                }
                else
                {
                    //注文単位.市場から確認済みの数量≠nullの場合 <BR>
                    //　@（（注文単位.市場から確認済の数量 － 注文単位.約定数量）
                    //　@　@　@　@× 注文単位.市場から確認済の注文単価） 
                    //＋ 注文単位.合計約定金額）を計算する。
                    l_dblQuantity = l_orderUnitRow.getConfirmedQuantity() - l_orderUnitRow.getExecutedQuantity();
                    l_dblPrice = l_orderUnitRow.getConfirmedOrderPrice();
                }
                double l_dblOpenAmount = l_dblQuantity * l_dblPrice;
                if (l_orderUnit.isMarketOrder())
                {
                    l_dblOpenAmount = Math.floor(
                        new BigDecimal(l_dblOpenAmount)
                        .multiply(l_bdPremiumRestraintRate).doubleValue());
                }
                if (OrderStatusEnum.ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.ORDERING.equals(l_orderUnitRow.getOrderStatus()))
                {
                    ;
                }
                else
                {
                    l_dblOpenAmount += l_orderUnitRow.getExecutedAmount();
                }

                l_dblTotalAmountFor1Day += l_dblOpenAmount;
            }
        }
        log.debug("l_dblTotalAmountFor1Day（発注日分）="+l_dblTotalAmountFor1Day);
        
        double l_dblTotalAmountForNextDay = 0D;
        if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
        {
            //（取引時間管理.is市場開局時間帯( ) == falseの場合）のみ、 
            
            //検索条件文字列:
            //注文カテゴリ（"order_categ"） = ? and 注文有効状態（"order_open_status"） = ?
            //and 発注日（"biz_date"）= ?  
            //and 注文失効日付（"expiration_date"）>= ? and 初回注文の注文単位ＩＤ != null
            //出来るまで注文指定を、初回注文の注文単位ID is not null 指定により行う。 
            //(注文の訂正の場合は訂正対象注文を除外する。)
            
            String l_strWhere6 =
                "order_categ = ? and order_open_status = ? "
                + "and biz_date = ? "
                + "and to_char(expiration_date,'yyyyMMdd') >= ? and first_order_unit_id is not null";
            //検索条件データコンテナ:
            //注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）のintValue 
            //注文有効状態：　@OrderOpenStatusEnum.”オープン”（OPEN）のintValue
            //発注日：　@取引時間管理.get発注日(void)の前営業日 
            //注文失効日付：　@取引時間管理.get発注日(void) の戻り値
            Date l_orderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            WEB3GentradeBizDate l_dateCalc = new WEB3GentradeBizDate(new Timestamp(l_orderBizDate.getTime()));
            Date l_prevBizDate = l_dateCalc.roll(-1);
            String l_strPrevBizDate = WEB3DateUtility.formatDate(l_prevBizDate, "yyyyMMdd");
            String l_strdate2 = WEB3DateUtility.formatDate(l_orderBizDate,"yyyyMMdd");
              
            List l_lisWheres6 = new ArrayList();
                        
            l_lisWheres6.add(String.valueOf(OrderCategEnum.OPEN_MARGIN.intValue()));
            l_lisWheres6.add(String.valueOf(String.valueOf(OrderOpenStatusEnum.OPEN.intValue())));
            l_lisWheres6.add(l_strPrevBizDate);
            l_lisWheres6.add(l_strdate2);
            
            if (l_changeOrderUnit != null)
            {
                //and 注文単位ID <> ?
                l_strWhere6 += " and order_unit_id <> ?";             
                //注文単位ID：  引数の訂正対象注文単位オブジェクト.注文単位ID
                l_lisWheres6.add(Long.toString(l_changeOrderUnit.getOrderUnitId())); 
            }
            
            Object[] l_objWhere6 = new Object[l_lisWheres6.size()];
            l_lisWheres6.toArray(l_objWhere6);
                        
            //翌日以降も有効な、新規建の「出来るまで注文」の注文単位オブジェクトを全て取得する。
            List l_lisOrderUnitsForNextDay = l_orderManager.getOrderUnits(
                l_subAccount, ProductTypeEnum.EQUITY, l_strWhere6, l_objWhere6, null);
            
            if (l_lisOrderUnitsForNextDay == null)
            {
                l_intLen = 0;
            }
            else
            {
                l_intLen = l_lisOrderUnitsForNextDay.size();
            }
            
            //取得した注文単位オブジェクト全ての、 
            //（（注文単位.注文数量 － 注文単位.約定数量） × 注文単位.注文単価）の 値を集計する。 
            for (int i = 0; i < l_intLen; i++)
            {
                EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOrderUnitsForNextDay.get(i);
                EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                
                double l_dblOpenAmount = 
                    (l_orderUnitRow.getQuantity() - l_orderUnitRow.getExecutedQuantity())
                    * l_orderUnitRow.getPrice();
                if (l_orderUnit.isMarketOrder())
                {
                    l_dblOpenAmount = Math.floor(
                        new BigDecimal(l_dblOpenAmount)
                        .multiply(l_bdPremiumRestraintRate).doubleValue());
                }
                l_dblTotalAmountForNextDay += l_dblOpenAmount;
            }
        }
        log.debug("l_dblTotalAmountForNextDay（発注予定分（引け後））="+l_dblTotalAmountForNextDay);
        log.debug("建代金（一日単位）l_dblAmount + l_dblTotalAmountFor1Day + l_dblTotalAmountForNextDay="
            + (l_dblAmount + l_dblTotalAmountFor1Day + l_dblTotalAmountForNextDay));

        if ((l_dblAmount + l_dblTotalAmountFor1Day + l_dblTotalAmountForNextDay) > l_dblMaxContPrice1day)
        {
            //建代金の上限値超過(一日)の場合
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00743,STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （validate株数（信用））。<BR>
     * <BR>
     * 株数のチェックを行う。<BR>
     * 　@－株数が０またはマイナス値でないこと。<BR>
     * 　@－株数が売買単位の整数倍であること。<BR>
     * 　@－株数がHOST側で受付可能な株数上限値を超えていないこと。<BR>
     * 　@－株数が、市場＋弁済区分＋注文種別毎の売買上限単位を超えていないこと。<BR>
     * 　@　@（※新規建／返済の場合のみ）<BR>
     * （validateQuantityのオーバーロード）<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用注文）validate株数（信用）」参照。<BR>
     * <BR>
     * １）　@スーパークラスの処理（validateQuantity(引数の株数)）にて、<BR>
     * 　@　@　@株数が0またはマイナス値でないかチェックを行う。<BR>
     * 　@　@　@チェックNGの場合は、「株数が0またはマイナス」の例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00709<BR>
     * <BR>
     * ２）　@スーパークラスの処理（checkLotSize(取引銘柄.売買単位, 引数の株数)）にて、<BR>
     * 　@　@　@株数が売買単位の整数倍であるかチェックを行う。<BR>
     * 　@　@　@チェックNGの場合は、「株数が売買単位の整数倍でない」の例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00708<BR>
     * <BR>
     * ３）　@this.validate株数（指定可能上限値）(引数の部店, 引数の株数))にて、<BR>
     * 　@　@　@注文株数が、HOST側で受付可能な株数上限値を超えていないかどうかのチェックを行う。<BR>
     * 　@　@　@チェックNGの場合は、「HOSTが受付可能な株数上限値を超過」の例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00707<BR>
     * <BR>
     * ４）　@引数の注文種別が以下のいずれかに該当する場合のみ、５）以降の処理を続行する。<BR>
     * 　@　@　@--------------------------------------------------------------<BR>
     * 　@　@　@”新規買建注文”（MARGIN_LONG）<BR>
     * 　@　@　@”新規売建注文”（MARGIN_SHORT）<BR>
     * 　@　@　@”買建返済注文”（CLOSE_MARGIN_LONG）<BR>
     * 　@　@　@”売建返済注文”（CLOSE_MARGIN_SHORT）<BR>
     * 　@　@　@--------------------------------------------------------------<BR>
     * 　@　@　@引数の注文種別が上記のいずれでもない場合は、returnし処理を終了する。<BR>
     * <BR>
     * ５）　@売買上限株数を計算する。<BR>
     * <BR>
     * （計算式）<BR>
     * 取引銘柄.売買単位　@×　@売買限度単位(*1)　@＝　@売買上限株数<BR>
     * <BR>
     * (*1) 売買限度単位：<BR>
     * 　@　@　@－取引銘柄.強制限度単位 != null の場合は、取引銘柄.強制限度単位 を使用する。<BR>
     * 　@　@　@－取引銘柄.強制限度単位 == null の場合は、<BR>
     * 　@　@　@　@　@部店.get売買限度単位（市場コード, 上場区分, 弁済区分, 弁済期限値, is新規建, is買建）<BR>
     * 　@　@　@　@　@にて取得した値を使用する。<BR>
     * <BR>
     * [部店.get売買限度単位( )引数設定値]<BR>
     * 市場コード：　@取引銘柄.getMarket().市場コード<BR>
     * 上場区分：　@取引銘柄.上場区分<BR>
     * 弁済区分：　@引数の弁済区分<BR>
     * 弁済期限値：　@引数の弁済期限値<BR>
     * is新規建：　@引数の注文種別＝”新規買建注文”（MARGIN_LONG）<BR>
     * 　@　@　@　@　@　@　@　@　@または”新規売建注文”（MARGIN_SHORT）の場合はtrue。<BR>
     * 　@　@　@　@　@　@　@引数の注文種別＝”買建返済注文”（CLOSE_MARGIN_LONG）<BR>
     * 　@　@　@　@　@　@　@　@　@または”売建返済注文”（CLOSE_MARGIN_SHORT）の場合はfalse。<BR>
     * is買建：　@引数の注文種別＝”新規買建注文”（MARGIN_LONG）<BR>
     * 　@　@　@　@　@　@　@　@　@または”買建返済注文”（CLOSE_MARGIN_LONG）の場合はtrue。<BR>
     * 　@　@　@　@　@　@　@引数の注文種別＝”新規売建注文”（MARGIN_SHORT）<BR>
     * 　@　@　@　@　@　@　@　@　@または”売建返済注文”（CLOSE_MARGIN_SHORT）の場合はfalse。<BR>
     * <BR>
     * ６）　@（売買上限株数 ＜ 引数の株数）の場合、<BR>
     * 　@　@　@「上限株数超過エラー」の例外をthrowする。<BR>
     * <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_00706
     * @@param l_tradedProduct 取引銘柄オブジェクト
     * @@param l_branch 部店オブジェクト
     * @@param l_dblQuantity (株数)<BR>
     * 　@　@　@入力株数
     * @@param l_orderType 注文種別。（xTradeのOrderTypeEnumにて定義）
     * @@param l_strRepaymentType 弁済区分。<BR>
     * 　@　@　@0：DEFAULT(指定なし)<BR>
     * 　@　@　@1：制度信用<BR>
     * 　@　@　@2：一般信用
     * @@param l_dblRepaymentNum 弁済期限値
     * @@throws WEB3BaseException
     */
    public void validateQuantity(
        WEB3EquityTradedProduct l_tradedProduct, 
        WEB3GentradeBranch l_branch, 
        double l_dblQuantity, 
        OrderTypeEnum l_orderType, 
        String l_strRepaymentType, 
        double l_dblRepaymentNum) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateQuantity(l_tradedProduct,l_branch,l_dblQuantity,l_orderType,l_strRepaymentType,l_dblRepaymentNum)";
        log.entering(STR_METHOD_NAME);

        // １）　@スーパークラスの処理（validateQuantity(引数の株数)）にて、
        // 　@　@　@株数が0またはマイナス値でないかチェックを行う。
        // 　@　@　@チェックNGの場合は、「株数が0またはマイナス」の例外をスローする。
        try
        {
            super.validateQuantity(l_dblQuantity);                        
        }
        catch (OrderValidationException l_ovex)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00709,STR_METHOD_NAME);
        }

        // ２）　@スーパークラスの処理（checkLotSize(取引銘柄.売買単位, 引数の株数)）にて、
        // 　@　@　@株数が売買単位の整数倍であるかチェックを行う。
        // 　@　@　@チェックNGの場合は、「株数が売買単位の整数倍でない」の例外をスローする。
        EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        try
        {
            super.checkLotSize(l_tradedProductRow.getLotSize(), l_dblQuantity);                     
        }
        catch (OrderValidationException l_ovex)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00708,STR_METHOD_NAME);
        }        

        // ３）　@this.validate株数（指定可能上限値）(引数の部店, 引数の株数))にて、
        // 　@　@　@注文株数が、HOST側で受付可能な株数上限値を超えていないかどうかのチェックを行う。
        // 　@　@　@チェックNGの場合は、「HOSTが受付可能な株数上限値を超過」の例外をスローする。
        try
        {
            this.validateQuantity(l_branch,l_dblQuantity);                  
        }
        catch (WEB3BaseException l_wbex)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00707,STR_METHOD_NAME);
        }          

        // ４）　@引数の注文種別が以下のいずれかに該当する場合のみ、５）以降の処理を続行する。
        // 　@　@　@--------------------------------------------------------------
        // 　@　@　@”新規買建注文”（MARGIN_LONG）
        // 　@　@　@”新規売建注文”（MARGIN_SHORT）
        // 　@　@　@”買建返済注文”（CLOSE_MARGIN_LONG）
        // 　@　@　@”売建返済注文”（CLOSE_MARGIN_SHORT）
        // 　@　@　@--------------------------------------------------------------
        // 　@　@　@引数の注文種別が上記のいずれでもない場合は、returnし処理を終了する。
        if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType)
            || OrderTypeEnum.MARGIN_SHORT.equals(l_orderType)
            || OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType)
            || OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        { 
            // ５）　@売買上限株数を計算する。
            boolean l_isOpenContract = false;
            if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType) 
                || OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
            {
                // 引数の注文種別＝”新規買建注文”（MARGIN_LONG） 
                // または”新規売建注文”（MARGIN_SHORT）の場合はtrue。 
                l_isOpenContract = true;
            }

            boolean l_isBuy = false;
            if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType) 
                || OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType))
                
            {
                // 引数の注文種別＝”新規買建注文”（MARGIN_LONG） 
                // または”買建返済注文”（CLOSE_MARGIN_LONG）の場合はtrue。 
                l_isBuy = true;
            }

            double l_dblDealingLimitUnit = 0.0D;
            if (l_tradedProductRow.getCompulsiveLimitedUnitIsNull())
            {
                l_dblDealingLimitUnit = 
                    l_branch.getDealingLimitUnit(
                        l_tradedProduct.getMarketCode(), 
                        l_tradedProductRow.getListType(), 
                        l_strRepaymentType, 
                        l_dblRepaymentNum, 
                        l_isOpenContract, 
                        l_isBuy);
            }
            else
            {
                l_dblDealingLimitUnit = l_tradedProductRow.getCompulsiveLimitedUnit();
            }
         
            // ６）　@（売買上限株数　@< 引数の株数）の場合、
            // 　@　@　@「上限株数超過エラー」の例外をthrowする。
            double l_dblDealingLimitQuantity = l_tradedProductRow.getLotSize() * l_dblDealingLimitUnit;
            if (l_dblDealingLimitQuantity < l_dblQuantity)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00160,
                    this.getClass().getName() + STR_METHOD_NAME);
            }       
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （validate株数（指定可能上限値））。<BR>
     * <BR>
     * 注文株数が、HOST側で受付可能な株数上限値を超えていないかどうかのチェックを行う。<BR>
     * （validateQuantityのオーバーロード）<BR>
     * <BR>
     * １）　@証券会社オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@引数の部店.getInstitution( )<BR>
     * <BR>
     * ２）　@（証券会社.指定可能株数上限値 ＜ 引数の株数）の場合、<BR>
     * 　@　@「上限数量超過エラー」の例外をthrowする。<BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00682
     * @@param l_branch 部店オブジェクト
     * @@param l_dblQuantity 入力株数
     * @@throws WEB3BaseException
     */
    protected void validateQuantity(
        WEB3GentradeBranch l_branch, 
        double l_dblQuantity) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateQuantity(l_branch,l_dblQuantity)";
        log.entering(STR_METHOD_NAME);
        
        //証券会社オブジェクトを取得する。
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_branch.getInstitution();
        //証券会社Rowオブジェクトを取得する。
        InstitutionRow l_institutionRow = (InstitutionRow)l_institution.getDataSourceObject();
      
        if (l_institutionRow.getMaxOrderQuantity() < l_dblQuantity)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00682,STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （validate現渡可能株数）。<BR>
     * <BR>
     * 指定顧客が現渡可能な株数を保持しているかどうかのチェックを行う。<BR>
     * 　@－SubAccount＝信用取引口座 の、該当銘柄＋税区分の保有資産を取得する。<BR>
     * 　@－該当銘柄の保有なし／保有株数不足 の場合は、例外をthrowする。<BR>
     * （validateSwappableAssetQuantity( )のオーバーロード）<BR>
     * <BR>
     * １）　@信用取引口座より、該当銘柄＋税区分の保有資産オブジェクトを取得し、
     * 　@　@　@保有株数チェックを行う。<BR>
     * <BR>
     * １－１）　@保有資産オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@＜保有資産オブジェクトの取得＞<BR>
     * 　@　@　@株式ポジションマネージャ.get保有資産(引数の補助口座.口座ID,<BR>
     * 　@　@　@　@引数の補助口座.補助口座ID, 引数の取引銘柄.銘柄ID,<BR>
     * 　@　@　@　@引数の税区分（現引現渡）)<BR>
     * <BR>
     * １－２）　@現渡可能数量を計算する。<BR>
     * <BR>
     * 　@　@　@現渡可能数量 ＝ （１－１）で取得した保有資産.数量 － 保有資産.getLockedQuantity( )）<BR>
     * 　@　@　@※１－１）で該当データなしの場合は、現渡可能数量＝0とする。<BR>
     * <BR>
     * １－３）　@（（１－２）で計算した現渡可能数量） ≧ 引数の株数）の場合は<BR>
     * 　@　@　@　@　@現渡可能株数を保有していると判断し、returnする。<BR>
     * 　@　@　@　@　@上記以外の場合は、「現渡対象の保有資産の数量不足」の例外をthrowする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00744
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@補助口座オブジェクト（信用取引口座）。
     * @@param l_tradedProduct 取引銘柄オブジェクト。
     * @@param l_dblQuantity (株数)<BR>
     * 　@　@　@入力株数。
     * @@param l_swapTaxType (税区分（現引現渡）)<BR>
     * 　@　@　@現引現渡の税区分。
     * @@throws WEB3BaseException
     */
    public void validateSwappableAssetQuantity(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityTradedProduct l_tradedProduct, 
        double l_dblQuantity, 
        TaxTypeEnum l_swapTaxType) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateSwappableAssetQuantity(l_subAccount,l_tradedProduct,l_dblQuantity,l_swapTaxType)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblSwappableAssetQuantituy = 0D;
       
        //信用取引口座より、該当銘柄＋税区分の保有資産オブジェクトを取得し、保有株数チェックを行う。 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
    
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();
                   
        //保有資産オブジェクトを取得する。

        EqTypeAsset l_asset = l_positionManager.getAsset(
            l_subAccount.getAccountId(), l_subAccount.getSubAccountId(),
            l_tradedProduct.getProduct().getProductId(),l_swapTaxType);
        log.debug("l_dblSwappableAssetQuantituy="+l_dblSwappableAssetQuantituy);
        
        //現渡可能数量を計算する
        if (l_asset != null)
        {
            l_dblSwappableAssetQuantituy = l_asset.getQuantity() - l_asset.getLockedQuantity();
            log.debug("l_dblSwappableAssetQuantituy="+l_dblSwappableAssetQuantituy);
        }
        
        if (l_dblSwappableAssetQuantituy < l_dblQuantity)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00744,STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （validate税区分（現引現渡））。<BR>
     * <BR>
     * 指定された税区分、税区分（現引現渡）の妥当性チェックを行う。<BR>
     * 　@－現引の場合、一般口座の建株を特定口座に入れることは不可。（税区分の壁あり）<BR>
     * 　@－上記以外の場合は、いずれも可能。（税区分の壁なし）<BR>
     * <BR>
     * １）　@引数のis買建＝trueの場合は、以下の処理を行う。<BR>
     * 　@　@　@以外は、何もせずにreturnする。<BR>
     * <BR>
     * ２）　@引数の税区分＝TaxTypeEnum.”一般口座”、<BR>
     * 　@　@　@かつ　@引数の税区分（現引現渡）＝（TaxTypeEnum.”特定口座”または”<BR>
     *       特定口座かつ源泉徴収”）<BR>
     * 　@　@　@の場合、「一般口座の建株は特定口座への差し入れ不可」の例外をthrowする。<BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00710
     * @@param l_taxType 税区分。<BR>
     * 　@　@　@（* 建株の税区分）
     * @@param l_swapTaxType 税区分（現引現渡）。<BR>
     * 　@　@　@（* 現引先／現渡元の税区分）
     * @@param l_blnIsLong (is買建)<BR>
     * 　@　@　@現引現渡対象の建株の買建／売建を示すフラグ。<BR>
     * 　@　@　@買建の場合true、以外false。
     * @@throws WEB3BaseException
     */
    public void validateSwapTaxType(
        TaxTypeEnum l_taxType, 
        TaxTypeEnum l_swapTaxType, 
        boolean l_blnIsLong) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSwapTaxType(l_taxType,l_swapTaxType,l_blnIsLong)";
        log.entering(STR_METHOD_NAME);
        
        if (!l_blnIsLong)
        {
            //引数のis買建 ＝ falseの場合は、何もせずにreturnする。
            return;
        }
        
        if (TaxTypeEnum.NORMAL.equals(l_taxType) 
            && (TaxTypeEnum.SPECIAL.equals(l_swapTaxType) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_swapTaxType)))
        {
            //引数の税区分＝TaxTypeEnum.”一般口座”、 
            //かつ　@引数の税区分（現引現渡）＝（TaxTypeEnum.”特定口座”または”特定口座かつ源泉徴収”）の場合
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00710,STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （validate特定口座開設（信用））。<BR>
     * <BR>
     * 信用取引の特定口座を開設しているかどうかのチェックを行う。<BR>
     * <BR>
     * １）　@引数の税区分≠（TaxTypeEnum.”特定口座”または”特定口座かつ源泉徴収”）の場合は、<BR>
     * 　@　@　@何もせずにreturnする。<BR>
     * 　@　@　@上記以外の場合は、以下の処理を行う。<BR>
     * <BR>
     * ２）　@顧客.is特定口座開設(引数の受渡日, 引数の補助口座, "2：信用取引")をコールする。<BR>
     * 　@　@　@戻り値＝falseの場合は、「特定口座の開設なし」の例外をthrowする。<BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_01703
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@補助口座オブジェクト。
     * @@param l_taxType 税区分。<BR>
     * 　@　@　@（* 顧客マスタ.信用取引税区分）
     * @@param l_datDeliveryDate 受渡日。
     * @@throws WEB3BaseException
     * （取引銘柄.受渡日）
     */
    public void validateMarginSpecialAccountOpen(
        WEB3GentradeSubAccount l_subAccount, 
        TaxTypeEnum l_taxType, 
        Date l_datDeliveryDate) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMarginSpecialAccountOpen(l_subAccount,l_taxType,l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);
        
        if (!TaxTypeEnum.SPECIAL.equals(l_taxType) && !TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
        {
            //引数の税区分≠（TaxTypeEnum.”特定口座”または”特定口座かつ源泉徴収”）の場合は、何もせずにreturnする。
            return;
        }
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        if (!l_mainAccount.isSpecialAccountEstablished(
            l_datDeliveryDate,l_subAccount, WEB3GentradeEquityMarginDivDef.MARGIN))
        {
            //顧客.is特定口座開設(引数の受渡日, 引数の補助口座, "2：信用取引")をコールする。
            //戻り値＝falseの場合は
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01703,STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * （validate成行指定規制（信用））。<BR>
     * <BR>
     * 成行指定規制チェックを行う。<BR>
     * <BR>
     * １）　@以下のいずれの条件にも合致しない場合は、何もせずにreturnする。<BR>
     * <BR>
     * 　@　@　@引数のis成行＝true<BR>
     * 　@　@　@引数の執行条件＝不出来引け成行<BR>
     * <BR>
     * 　@　@　@上記のいずれかの条件に合致する場合は、以下の処理を行う。<BR>
     * <BR>
     * ２）　@成行指定規制チェック<BR>
     * 　@　@　@取引銘柄.is信用成行指定規制(引数の弁済区分, 引数の注文カテゴリ, 引数のis売建)をコールする。<BR>
     * 　@　@　@戻り値＝trueの場合は、「信用取引・成行指定規制中」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00712<BR>
     * <BR>
     * ３）　@MM銘柄成行指定チェック<BR>
     * 　@　@　@市場(*).getMarketCode( )により市場コードを取得する。<BR>
     * 　@　@　@取得した市場コード＝”JASDAQ”、かつ　@取引銘柄.店頭公開区分＝”マーケットメイク銘柄”<BR>
     * 　@　@　@の場合は、「MM銘柄は成行指定不可」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_01352<BR>
     * <BR>
     * 　@　@　@(*)市場オブジェクトは、取引銘柄.getMarket()により取得する。
     * @@param l_tradedProduct 取引銘柄オブジェクト。
     * @@param l_strRepaymentType 弁済区分。<BR>
     * 　@　@　@0：DEFAULT<BR>
     * 　@　@　@1：制度信用<BR>
     * 　@　@　@2：一般信用
     * @@param l_orderCateg 注文カテゴリ。（xTradeのOrderCategEnumにて定義）
     * @@param l_blnIsMarketOrder (is成行)<BR>
     * 　@　@　@成行注文の場合true、以外false。
     * @@param l_blnIsShort (is売建)<BR>
     * 　@　@　@売建／買建のフラグ。<BR>
     * 　@　@　@建株＝売建の場合true、買建の場合falseを指定する。<BR>
     * @@param l_executionCondition (執行条件)<BR>
     * 　@　@　@執行条件。<BR>
     * @@throws WEB3BaseException
     */
    public void validateMarketOrderRestraint(
        WEB3EquityTradedProduct l_tradedProduct, 
        String l_strRepaymentType, 
        OrderCategEnum l_orderCateg, 
        boolean l_blnIsMarketOrder, 
        boolean l_blnIsShort,
        EqTypeExecutionConditionType l_executionCondition) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMarketOrderRestraint(l_tradedProduct,l_strRepaymentType,l_orderCateg,l_isMarketOrder,l_isShort,l_executionCondition)";
        log.entering(STR_METHOD_NAME);

        if ((l_blnIsMarketOrder == true) ||
            (EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_executionCondition)))
        {
            ;
        }
        else
        {
            //１）　@（成行注文 または 不出来引け成行注文）以外の場合は、何もせずにreturnする。
            return;
        }

        // ２）　@成行指定規制チェック
        boolean l_blnIsMarketOrderDesinateCtrl =
            l_tradedProduct.isMarginMarketOrderDesignateCtrl(l_strRepaymentType, l_orderCateg, l_blnIsShort);     
        if (l_blnIsMarketOrderDesinateCtrl)
        {
            // 　@　@　@取引銘柄.is信用成行指定規制(引数の弁済区分, 引数の注文カテゴリ, 引数のis売建)をコールする。
            // 　@　@　@戻り値＝trueの場合は、「信用取引・成行指定規制中」の例外をthrowする。
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00712,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // ３）　@MM銘柄成行指定チェック
        if (WEB3MarketCodeDef.JASDAQ.equals(l_tradedProduct.getMarketCode()))
        {
            // 　@　@　@取得した市場コード＝”JASDAQ”、かつ　@取引銘柄.店頭公開区分＝”マーケットメイク銘柄”
            // 　@　@　@の場合は、「MM銘柄は成行指定不可」の例外をthrowする。
            EqtypeTradedProductRow l_tradedProductRow =
                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
            if (WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(l_tradedProductRow.getOpenOtcDiv()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01352,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （validate訂正項目）。<BR>
     * <BR>
     * 注文訂正にて、訂正された項目が正しいかをチェックする。<BR>
     * エラー内容は以下の通り。<BR>
     * 　@－訂正元注文の株数より訂正株数が多い場合。<BR>
     * 　@－訂正元注文が一部出来の状態で、訂正株数が約定数以下の場合。<BR>
     * 　@－同時訂正不可の市場で、株数と同時訂正対象項目が同時に訂正されている場合。<BR>
     * 　@－”当日限り”→”出来るまで注文”へ、または”出来るまで注文”→”当日限り”へ<BR>
     * 　@　@　@訂正されている場合。<BR>
     * 　@－発注条件が訂正されている場合。<BR>
     * 　@－市場発注済の注文の、逆指値注文の条件が訂正されている場合。<BR>
     * 　@－訂正元注文から何も訂正が入っていない場合。<BR>
     * <BR>
     * -------------------------------------------------------------------<BR>
     * １）　@株数訂正チェック<BR>
     * 　@isChange株数（注文単位、訂正後株数）をコールし、<BR>
     * 　@株数訂正値のチェックを行う。<BR>
     * <BR>
     * ２）　@単価チェック<BR>
     * 　@isChange単価（注文単位、訂正後指値）をコールし、<BR>
     * 　@単価訂正値のチェックを行う。<BR>
     * <BR>
     * ３）　@執行条件に訂正が入っているかのチェック<BR>
     * 　@isChange執行条件（注文単位、訂正後執行条件）をコールする。<BR>
     * <BR>
     * ４）　@株数と同時訂正対象項目(*1)を同時に訂正している場合、同時訂正可能な市場かどうかのチェック<BR>
     * <BR>
     * 　@(*1)同時訂正対象項目<BR>
     * 　@　@　@・単価（指値）<BR>
     * 　@　@　@・執行条件<BR>
     * <BR>
     * 　@=======================================================================<BR>
     * 　@４－１）　@取引所が休憩時間帯(*2)、かつ 前場発注済注文(*3)の場合<BR>
     * <BR>
     * 　@　@市場に通知済の値を使用し、「同時訂正」であるかどうかを判定する。<BR>
     * <BR>
     * 　@　@（注文単位.市場から確認済の数量 != 訂正後株数）の場合のみ、<BR>
     * 　@　@｛（注文単位.市場から確認済の指値 != 訂正後指値） または<BR>
     * 　@　@　@（注文単位.市場から確認済の執行条件 != 訂正後執行条件）｝のいずれかに該当する場合は<BR>
     * 　@　@「同時訂正」と判定する。<BR>
     * <BR>
     * 　@　@(*2)取引所が休憩時間帯の場合<BR>
     * 　@　@　@　@　@取引時間管理.is取引所休憩時間帯()==trueの場合は、休憩時間帯であると判定する。<BR>
     * <BR>
     * 　@　@(*3)前場発注済注文<BR>
     * 　@　@　@　@　@注文単位.市場から確認済の数量 != NaNの場合は、前場発注済注文であると判定する。<BR>
     * <BR>
     * 　@=======================================================================<BR>
     * 　@４－２）　@４－１）以外の場合<BR>
     * <BR>
     * 　@　@訂正元注文単位の値を使用し、「同時訂正」であるかどうかを判定する。<BR>
     * <BR>
     * 　@　@isChange株数( )がtrueを返却した場合のみ、<BR>
     * 　@　@{isChange単価( )、isChange執行条件( )}のいずれか一つでもtrueを返却していれば<BR>
     * 　@　@「同時訂正」と判定する。<BR>
     * 　@=======================================================================<BR>
     * <BR>
     * 　@「同時訂正」に該当する場合のみ、拡張プロダクトマネージャ.getMarket(注文単位.市場ID)にて<BR>
     * 　@市場オブジェクトを取得する。<BR>
     * 　@市場オブジェクト.同時訂正可能区分＝”複数項目同時訂正不可”の場合<BR>
     * 　@は、「複数項目同時訂正不可」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00687<BR>
     * <BR>
     * ５）　@出来るまで注文かどうか（注文期限区分）に訂正が入っているかのチェック<BR>
     * 　@isChangeIs出来るまで注文（注文単位、訂正後is出来るまで注文 )をコールする。<BR>
     * <BR>
     * ６）　@値段条件に訂正が入っているかのチェック<BR>
     * 　@isChange値段条件(注文単位、訂正後値段条件、訂正後指値)をコールする。<BR>
     * <BR>
     * ７）　@発注条件（”指定なし”／”逆指値”／”Ｗ指値”）に訂正が入っているかのチェック<BR>
     * 　@isChange発注条件（注文単位、訂正後発注条件)をコールする。<BR>
     * <BR>
     * ８）　@逆指値条件（発注条件演算子、逆指値基準値）に訂正が入っているかのチェック<BR>
     * 　@isChange逆指値条件（注文単位、訂正後発注条件、訂正後逆指値基準値、訂正後発注条件演算子)をコールする。<BR>
     * 　@<BR>
     * ９）　@Ｗ指値条件（発注条件演算子、逆指値基準値、（Ｗ指値）訂正指値）に訂正が入っているかのチェック<BR>
     * 　@isChangeＷ指値条件（注文単位、訂正後発注条件、訂正後発注条件演算子、<BR>
     * 　@訂正後逆指値基準値、訂正後（W指値）訂正指値、訂正後（W指値）執行条件 )をコールする。 <BR>
     * <BR>
     * １０）　@注文有効期限（訂正後注文失効日）に訂正が入っているかのチェック<BR>
     * 　@isChange注文失効日(注文単位、訂正後注文失効日)をコールする。<BR>
     * <BR>
     * １１）　@個々の建に対する返済指定数量（返済数量内訳）に訂正が入っているかのチェックを行う。<BR>
     * 　@isChange決済数量内訳(注文単位、訂正後決済指定エントリ)をコールする。<BR>
     * <BR>
     * １２）　@訂正が入っているかのチェック<BR>
     * 　@isChange株数( )、isChange単価( )、<BR>
     * 　@isChange執行条件( )、isChange値段条件( )、<BR>
     * 　@isChange逆指値条件( )、isChangeＷ指値条件( )、<BR>
     * 　@isChange注文失効日( )、isChange決済数量内訳( )のすべてがfalseを返却した場合、<BR>
     * 　@訂正元注文から何も訂正されていないと判断し、<BR>
     * 　@「訂正入力なし」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00039<BR>
     * @@param l_orderUnit (注文単位)<BR>
     * 　@　@　@訂正元注文（原注文）の注文単位オブジェクト。
     * @@param l_dblModifiedQuantity (訂正後株数)<BR>
     * 　@　@　@訂正後株数。
     * @@param l_dblModifiedLimitPrice (訂正後指値)<BR>
     * 　@　@　@訂正後指値。
     * @@param l_modifiedExecutionType (訂正後執行条件)<BR>
     * 　@　@　@訂正後執行条件。
     * @@param l_strModifiedPriceConditionType (訂正後値段条件)<BR>
     * 　@　@　@訂正後値段条件。
     * @@param l_strModifiedOrderConditionType (訂正後発注条件)<BR>
     * 　@　@　@訂正後発注条件。
     * @@param l_strModifiedOrderCondOperator (訂正後発注条件演算子)<BR>
     * 　@　@　@訂正後発注条件演算子。
     * @@param l_dblModifiedStopOrderPrice (訂正後逆指値基準値)<BR>
     * 　@　@　@訂正後逆指値基準値。
     * @@param l_dblModifiedWLimitPrice (訂正後（W指値）訂正指値)<BR>
     * 　@　@　@訂正後（W指値）訂正指値。
     * @@param l_modifiedWLimitExecCondType (訂正後（W指値）執行条件)<BR>
     * 　@　@　@訂正後（W指値）執行条件<BR>
     * @@param l_modifiedIsCarriedOrder (訂正後is出来るまで注文)<BR>
     * 　@　@　@訂正後の注文が出来るまで注文かどうかを判別するフラグ。<BR>
     * 　@　@　@true：　@出来るまで注文<BR>
     * 　@　@　@false：　@当日注文<BR>
     * @@param l_datModifiedExpirationDate (訂正後注文失効日)<BR>
     * 　@　@　@訂正後注文失効日。<BR>
     * @@param l_modifiedSettleContractEntries（訂正後決済指定エントリ）<BR>
     * 　@　@　@訂正後決済指定エントリの配列。決済注文の場合のみセットする。
     * @@throws WEB3BaseException
     */
    public void validateChangeItem(
        EqTypeOrderUnit l_orderUnit, 
        double l_dblModifiedQuantity, 
        double l_dblModifiedLimitPrice, 
        EqTypeExecutionConditionType l_modifiedExecutionType, 
        String l_strModifiedPriceConditionType,
        String l_strModifiedOrderConditionType, 
        String l_strModifiedOrderCondOperator, 
        double l_dblModifiedStopOrderPrice, 
        double l_dblModifiedWLimitPrice,
        EqTypeExecutionConditionType l_modifiedWLimitExecCondType,
        boolean l_modifiedIsCarriedOrder,
        Date l_datModifiedExpirationDate,
        EqTypeSettleContractOrderEntry[] l_modifiedSettleContractEntries) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateChangeItem(l_orderUnit,l_dblModifiedQuantity,l_dblModifiedLimitPrice,l_modifiedExecutionType"
            + "l_strModifiedOrderConditionType,l_strModifiedOrderCondOperator,"
            + "l_dblModifiedStopOrderPrice,l_dblModifiedWLimitPrice,l_wLimitExecCondType,l_modifiedIsCarriedOrder";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //1)株数訂正チェック
        boolean l_blnIsChangeQuantity = this.isChangeQuantity(l_orderUnit, l_dblModifiedQuantity);
        
        //2)単価チェック
        boolean l_blnIsChangePrice = this.isChangePrice(l_orderUnit, l_dblModifiedLimitPrice);
        
        //3)執行条件に訂正が入っているかのチェック 
        boolean l_blnIsChangeExecutionCondition =
            this.isChangeExecutionCondition(l_orderUnit, l_modifiedExecutionType);
        
        //4)株数と同時訂正対象項目を同時に訂正している場合、同時訂正可能な市場かどうかのチェック
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        boolean l_blnIsSynChange = false;
        boolean l_blnIsInBreakAndOrdered = false;
        if (WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone() &&
            l_orderUnitRow.getConfirmedQuantity() != 0.0D)
        {
            l_blnIsInBreakAndOrdered = true;
            if (l_orderUnitRow.getConfirmedQuantity() != l_dblModifiedQuantity)
            {
                if ((l_orderUnitRow.getConfirmedPrice() != l_dblModifiedLimitPrice) ||
                    !(l_orderUnitRow.getConfirmedExecConditionType().equals(l_modifiedExecutionType)))
                {
                    l_blnIsSynChange = true;
                }
            }
        }
        else
        {
            if (l_blnIsChangeQuantity)
            {
                if (l_blnIsChangePrice || l_blnIsChangeExecutionCondition)
                {
                    l_blnIsSynChange = true;
                }
            }
        }
        if (l_blnIsSynChange)
        {
            try
            {
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(l_orderUnitRow.getMarketId());
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                if (WEB3ChangeableTypeDef.CANNOT_CHANGE.equals(l_marketRow.getChangeableType()))
                {
                    if (l_blnIsInBreakAndOrdered)
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02197,
                            STR_METHOD_NAME);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00687,
                            STR_METHOD_NAME);
                    }
                }              
            }
            catch (NotFoundException l_nfex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME);
            }
        }
        
        //5)出来るまで注文かどうか（注文期限区分）に訂正が入っているかのチェック 
        boolean l_blnIsChangeCarrideOrder =
            this.isChangeIsCarriedOrder(l_orderUnit, l_modifiedIsCarriedOrder);
        
        //6)値段条件に訂正が入っているかのチェック
        boolean l_blnIsChangePriceConditionType =
            this.isChangePriceConditionType(l_orderUnit, l_strModifiedPriceConditionType,
            l_dblModifiedLimitPrice);
                
        //7)発注条件（”指定なし”／”逆指値”／”Ｗ指値”）に訂正が入っているかのチェック 
        boolean l_blnIsChangeOrderConditionType =
            this.isChangeOrderConditionType(l_orderUnit, l_strModifiedOrderConditionType);
        
        //8)逆指値条件（発注条件演算子、逆指値基準値）に訂正が入っているかのチェック 
        boolean l_blnIsChangeStopOrderCondition =
            this.isChangeStopOrderCondition(l_orderUnit, l_strModifiedOrderConditionType,
            l_dblModifiedStopOrderPrice, l_strModifiedOrderCondOperator);
        
        //9)isChangeＷ指値条件（注文単位、訂正後発注条件、訂正後発注条件演算子、
        //       訂正後逆指値基準値、訂正後（W指値）訂正指値、訂正後（W指値）執行条件)
        boolean l_blnIsChangeWLimitCondition =
            this.isChangeWLimitCondition(l_orderUnit, l_strModifiedOrderConditionType,
            l_strModifiedOrderCondOperator, l_dblModifiedStopOrderPrice, l_dblModifiedWLimitPrice, l_modifiedWLimitExecCondType);
        
        //10)注文有効期限（訂正後注文失効日）に訂正が入っているかのチェック
        boolean l_blnIsChangeExpirationDate =
            this.isChangeExpirationDate(l_orderUnit, l_datModifiedExpirationDate);
        
        //11)個々の建に対する返済指定数量（返済数量内訳）に訂正が入っているかのチェック
        boolean l_blnIsChangeEachQuantityOfCloseContract =
            this.isChangeEachQuantityOfCloseContract(l_orderUnit, l_modifiedSettleContractEntries);
        
        //11)訂正が入っているかのチェック 
        if (!l_blnIsChangeQuantity && !l_blnIsChangePrice 
            && !l_blnIsChangeExecutionCondition && !l_blnIsChangePriceConditionType
            && !l_blnIsChangeStopOrderCondition && !l_blnIsChangeWLimitCondition
            && !l_blnIsChangeExpirationDate && !l_blnIsChangeEachQuantityOfCloseContract)
        {
            // 　@isChange株数( )、isChange単価( )、
            // 　@isChange執行条件( )、isChange値段条件( )、
            // 　@isChange逆指値条件( )、isChangeＷ指値条件( )、
            // 　@isChange注文失効日( )、isChange決済数量内訳( )のすべてがfalseを返却した場合
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00039,
                STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （isChange株数）。<BR>
     * <BR>
     * 株数に訂正が入ったかをチェックする。株数に訂正がある場合はtrue、以外は<BR>falseを返却する。<BR>
     * また、株数の訂正値が不正な場合は例外をスローする。<BR>
     * 株数の訂正値が不正な場合は以下の通り。<BR>
     * 　@－訂正元注文の株数より訂正株数が多い場合。<BR>
     * 　@－訂正元注文が一部出来の状態で、訂正株数が約定数以下の場合。<BR>
     * <BR>
     * １）　@訂正前の株数を超えていないかチェックを行う。<BR>
     * （注文株数(*1)　@＜　@引数.訂正後株数）の場合、<BR>
     * 　@　@「訂正後株数が注文株数を超過」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00036
     * <BR>
     * ２）　@約定数より訂正株数が少なくないかチェックを行う。<BR>
     * （約定済株数*2)　@＞　@引数.訂正後株数）の場合、<BR>
     * 　@　@「訂正後株数が約定済株数未満」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00037<BR>
     * <BR>
     * ３）　@値の比較<BR>
     * 　@（注文株数 == 引数.訂正後株数）の場合、falseを返却する。以外はtrueを返却する。<BR>
     * <BR>
     * (*1) 注文株数<BR>
     * 引数.注文単位.getQuantity()<BR>
     * <BR>
     * (*2) 約定済株数<BR>
     * 引数.注文単位.getExecutedQuantity()
     * @@param l_orderUnit (注文単位)<BR>
     * 　@　@　@訂正元注文（原注文）の注文単位オブジェクト
     * @@param l_dblModifiedQuantity 訂正後株数
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangeQuantity(EqTypeOrderUnit l_orderUnit, double l_dblModifiedQuantity)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "isChangeQuantity(EqTypeOrderUnit l_orderUnit, double l_dblModifiedQuantity)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //訂正前の株数を超えていないかチェックを行う。 
        if (l_orderUnit.getQuantity() < l_dblModifiedQuantity)
        {
            //（注文株数(*1)　@＜　@引数.訂正後株数）の場合、
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00036,STR_METHOD_NAME);
        }
        
        //約定数より訂正株数が少なくないかチェックを行う。 
        if (l_orderUnitRow.getExecutedQuantity() > l_dblModifiedQuantity)
        {
            //（約定済株数*2)　@＞　@引数.訂正後株数）の場合、 
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00037,STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);        
        if (l_orderUnit.getQuantity() == l_dblModifiedQuantity)
        {
            //（注文株数 == 引数.訂正後株数）の場合、falseを返却する。
            return false;    
        }
        else
        {
            //以外はtrueを返却する。 
            return true;
        }
    }
    
    /**
     * （isChange単価）。<BR>
     * <BR>
     * 単価に訂正が入ったかをチェックする。<BR>
     * <BR>
     * （引数.注文単位.getLimitPrice == 引数.訂正後指値）の場合、<BR>
     * falseを返却する。以外、trueを返却する。<BR>
     * @@param l_orderUnit (注文単位)<BR>
     * 　@　@　@訂正元注文（原注文）の注文単位オブジェクト
     * @@param l_dblModifiedLimitPrice 訂正後指値
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangePrice(EqTypeOrderUnit l_orderUnit, double l_dblModifiedLimitPrice) 
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = "isChangePrice(EqTypeOrderUnit l_orderUnit, double l_dblModifiedLimitPrice) ";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
                
        if (l_orderUnit.getLimitPrice() == l_dblModifiedLimitPrice)
        {
            //引数.注文単位.getLimitPrice == 引数.訂正後指値）の場合 falseを返却する。
            return false;         
        }
        else
        {
            return true;
        }
    }
    
    /**
     * （isChange執行条件）。<BR>
     * <BR>
     * 執行条件に訂正が入ったかをチェックする。<BR>
     * <BR>
     * ・引数.注文単位.執行条件を(訂正前の執行条件)とする。<BR>
     * ・引数.訂正後執行条件を（訂正後の執行条件）とする。<BR>
     * <BR>
     * １）　@執行条件変更チェック<BR>
     * 　@－原注文が「出来るまで注文」(*1)であり、<BR>
     * 　@　@　@（訂正前の執行条件）と（訂正後の執行条件）が一致していない場合は、<BR>
     * 　@　@　@「出来るまで注文は執行条件の訂正不可」の例外をthrowする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_00806<BR>
     * <BR>
     * 　@－原注文が「出来るまで注文」(*1)でなければ、<BR>
     * 　@　@　@（当該顧客の証券会社で取扱可能な執行条件(*2)）の<BR>
     * 　@　@　@いずれかにのみ変更することができる。<BR>
     * 　@　@　@（訂正後の執行条件）が変更できない執行条件の場合は、<BR>
     * 　@　@　@「証券会社が取扱不可な執行条件」の例外をthrowする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_00807<BR>   
     * <BR>
     * 　@(*1) 拡張株式注文マネージャ.is出来るまで注文単位(引数.注文単位.注文単位ID) == trueの<BR>
     * 　@　@　@　@場合は、出来るまで注文。<BR>
     * 　@　@　@　@falseの場合は、出来るまで注文ではない。<BR>
     * 　@(*2)取扱可能注文条件.取扱可能執行条件取得( )で取得した値を、<BR>
     * 　@　@　@拡張株式注文マネージャ.get執行条件( )にて変換したEnum値を使用する。<BR>
     * <BR>
     * ２）　@処理終了<BR>
     * 　@（訂正前の執行条件） == （訂正後の執行条件） の場合false、<BR>
     * 　@以外はtrueを返却する。
     * @@param l_orderUnit (注文単位)<BR>
     * 　@　@　@訂正元注文（原注文）の注文単位オブジェクト。
     * @@param l_modifiedExecutionType (訂正後執行条件)<BR>
     * 　@　@　@訂正後執行条件。
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangeExecutionCondition(
        EqTypeOrderUnit l_orderUnit, 
        EqTypeExecutionConditionType l_modifiedExecutionType)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "isChangeExecutionCondition(EqTypeOrderUnit l_orderUnit, EqTypeExecutionConditionType l_modifiedExecutionType)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        // 　@－原注文が「出来るまで注文」
        if (l_orderManager.isCarriedOrderUnit(l_orderUnit))
        {
            //　@（訂正前の執行条件）と（訂正後の執行条件）が一致していない場合は、
            // 「出来るまで注文は執行条件の訂正不可」の例外をthrowする。
            if (l_modifiedExecutionType == null ||
                !l_modifiedExecutionType.equals(l_orderUnitRow.getExecutionConditionType()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00806,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        // 　@－原注文が「出来るまで注文」(*1)でなければ
        else
        {  
            boolean l_blnreturn = false;
            AccountManager l_accountManager = (AccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = l_accountManager.getMainAccount(l_orderUnitRow.getAccountId());
            }
            catch (NotFoundException l_exp)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            log.debug("InstitutionCode() = " + l_strInstitutionCode);

            ProductTypeEnum l_productType = ProductTypeEnum.EQUITY;
            String l_strFuturesOptionDiv = WEB3MarginTradingDivDef.DEFAULT;
            String l_strMarginTradingDiv = WEB3MarginTradingDivDef.DEFAULT;
            String l_strMarketCode = null;
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            try
            {
                Market l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
                l_strMarketCode = l_market.getMarketCode();
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            WEB3GentradeHandlingOrderCond l_tradeOrderCond =
                new WEB3GentradeHandlingOrderCond(
                    l_strInstitutionCode,
                    l_productType,
                    l_strFuturesOptionDiv,
                    l_strMarginTradingDiv,
                    l_strMarketCode);
            
            // 取扱可能注文条件.is取扱可能執行条件()
            // (仕様と同結果を得られる為、当該メソッド使用)
            l_blnreturn = l_tradeOrderCond.isHandlingPossibleExecCond(l_modifiedExecutionType);
            // （訂正後の執行条件）が変更できない執行条件の場合は、
            // 「証券会社が取扱不可な執行条件」の例外をthrowする。
            if (!l_blnreturn)
            {         
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00807,
                    this.getClass().getName() + STR_METHOD_NAME);                        
            }
        }

        // ２）　@処理終了
        // 　@（訂正前の執行条件） == （訂正後の執行条件） の場合false、
        // 　@以外はtrueを返却する。
        log.exiting(STR_METHOD_NAME);
        if (l_modifiedExecutionType.equals(l_orderUnitRow.getExecutionConditionType()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * （isChangeIs出来るまで注文）。<BR>
     * <BR>
     * 出来るまで注文かどうか（「出来るまで注文」でない(＝当日限り)／「出来るまで注文」）が訂正されていないかどうかチェック<BR>
     * する。<BR>
     * <BR>
     * １）　@訂正元注文の判定<BR>
     * 訂正元注文が「出来るまで注文」(*1)かどうかを判定する。<BR>
     * <BR>
     * (*1)拡張株式注文マネージャ.is出来るまで注文単位(引数.注文単位)==trueの場合、<BR>「出来るまで注文」である。<BR>
     * 　@　@falseの場合、「出来るまで注文」でない。<BR>
     * <BR>
     * ２）　@訂正入力の判定<BR>
     * 訂正注文が「出来るまで注文」(*2)かどうかを判定する。<BR>
     * <BR>
     * (*2)引数.訂正後is出来るまで注文がtrueの場合、「出来るまで注文」である。<BR>
     * falseの場合、「出来るまで注文」でない。<BR>
     * <BR>
     * ３）　@「出来るまで注文」指定変更チェック<BR>
     * 　@（訂正元注文の指定） != （訂正入力の指定）の場合は、<BR>
     * 　@　@「出来るまで注文の指定は訂正不可」の例外をthrowする。<BR>
     * 　@以外、falseを返却する。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00070
     * @@param l_orderUnit 注文単位<BR>
     * 　@　@　@訂正元注文（原注文）の注文単位オブジェクト
     * @@param l_modifiedIsCarriedOrder 訂正後is出来るまで注文<BR>
     * 　@　@　@訂正後の注文が出来るまで注文かどうかを判別するフラグ。<BR>
     * <BR>
     * 　@　@　@true：　@出来るまで注文<BR>
     * 　@　@　@false：　@当日注文
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangeIsCarriedOrder(
        EqTypeOrderUnit l_orderUnit, 
        boolean l_modifiedIsCarriedOrder)
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "isChangeIsCarriedOrder(EqTypeOrderUnit l_orderUnit, boolean l_modifiedIsCarriedOrder)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
                
        //訂正元注文の判定 
        //訂正元注文が「出来るまで注文」(*1)かどうかを判定する。 
               
        boolean l_blnOrginIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(l_orderUnit);
        if (l_blnOrginIsCarriedOrderUnit != l_modifiedIsCarriedOrder)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00070,STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * （isChange発注条件）。<BR>
     * <BR>
     * 発注条件（”指定なし”／”逆指値”／”Ｗ指値”）に訂正が入ったかをチェックする。<BR>
     * <BR>
     * １）　@訂正元注文の発注条件を取得する。<BR>
     * 引数.注文単位.発注条件を取得する。（訂正前の発注条件）<BR>
     * <BR>
     * ２）　@訂正入力の発注条件を取得する。<BR>
     * 引数.訂正後発注条件（訂正後の発注条件）<BR>
     * <BR>
     * ３）　@発注条件変更チェック<BR>
     * 　@（訂正前の発注条件） != （訂正後の発注条件）の場合は、<BR>
     * 　@　@「発注条件は訂正不可」の例外をthrowする。以外、falseを返却する。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00071<BR>
     * <BR>
     * @@param l_orderUnit (注文単位)<BR>
     * 　@　@　@訂正元注文（原注文）の注文単位オブジェクト
     * @@param l_strModifiedOrderConditionType 訂正後発注条件
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangeOrderConditionType(
        EqTypeOrderUnit l_orderUnit, 
        String l_strModifiedOrderConditionType)
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "isChangeOrderConditionType(EqTypeOrderUnit l_orderUnit, String l_strModifiedOrderConditionType) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //発注条件変更チェック
        if (l_strModifiedOrderConditionType == null ||
            !l_strModifiedOrderConditionType.equals(l_orderUnitRow.getOrderConditionType()))
        {
            //（訂正前の発注条件） != （訂正後の発注条件）の場合は、
            //「発注条件は訂正不可」の例外をthrowする。以外、falseを返却する。
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00071,STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);        
        
        return false;
    }
    
    /**
     * （isChange逆指値条件）。<BR>
     * <BR>
     * 逆指値の条件（逆指値基準値、発注条件演算子）に訂正が入ったかをチェックする。<BR>
     * <BR>
     * １）　@引数.訂正後発注条件 ≠”逆指値”の場合は、以降の処理は行わずfalseを返す。<BR>
     * <BR>
     * ２）　@訂正元注文の逆指値基準値、発注条件演算子を取得する。<BR>
     * 　@－引数.注文単位.逆指値基準値を取得。（訂正前の逆指値基準値）<BR>
     * 　@－引数.注文単位.発注条件演算子を取得。（訂正前の発注条件演算子）<BR>
     * <BR>
     * ３）　@訂正入力の逆指値基準値、発注条件演算子を取得する。<BR>
     * 　@－引数.訂正後逆指値基準値（訂正後の逆指値基準値）<BR>
     * 　@－引数.訂正後発注条件演算子（訂正後の発注条件演算子）<BR>
     * <BR>
     * ４）　@逆指値基準値、発注条件演算子変更チェック<BR>
     * 　@－訂正元注文が市場発注済(*1)の場合のみ、<BR>
     * 　@　@（訂正前の逆指値基準値） != （訂正後の逆指値基準値）または、<BR>
     * 　@　@（訂正前の発注条件演算子） != （訂正後の発注条件演算子）であれば、<BR>
     * 　@　@「市場発注済注文の逆指値基準値及び、発注条件演算子は訂正不可」の<BR>
     * 　@　@例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00690<BR>
     * <BR>
     * (*1)　@引数.注文単位.リクエストタイプ == "時価サーバ"の場合は、<BR>
     * 　@　@　@訂正元注文が市場発注済と判定する。<BR>
     * <BR>
     * ５）　@処理終了<BR>
     * 　@（訂正前の逆指値基準値） == （訂正後の逆指値基準値）かつ、<BR>
     * 　@（訂正前の発注条件演算子） == （訂正後の発注条件演算子）　@の場合<BR>
     * 　@falseを、以外はtrueを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 訂正元注文（原注文）の注文単位オブジェクト<BR>
     * @@param l_strModifiedOrderConditionType - (訂正後発注条件)<BR>
     * 訂正後発注条件<BR>
     * @@param l_dblModifiedStopOrderPrice - (訂正後逆指値基準値)<BR>
     * 訂正後逆指値基準値<BR>
     * @@param l_strModifiedOrderCondOperator - (訂正後発注条件演算子)<BR>
     * 訂正後発注条件演算子<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangeStopOrderCondition(
        EqTypeOrderUnit l_orderUnit, 
        String l_strModifiedOrderConditionType, 
        double l_dblModifiedStopOrderPrice, 
        String l_strModifiedOrderCondOperator) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isChangeStopOrderCondition(EqTypeOrderUnit l_orderUnit, String l_strModifiedOrderConditionType, double l_dblModifiedStopOrderPrice, String l_strModifiedOrderCondOperator)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                 
        if (!WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strModifiedOrderConditionType))
        {
            //引数.訂正後発注条件 ≠”逆指値”の場合は、以降の処理は行わずfalseを返す。    
            return false;
        }
        
        //逆指値基準値、発注条件演算子変更チェック         
        if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_orderUnitRow.getRequestType()))
        {
            //訂正元注文が市場発注済
            

            
            if ((l_strModifiedOrderCondOperator != null &&
                !l_strModifiedOrderCondOperator.equals(l_orderUnitRow.getOrderCondOperator()))
                || (l_strModifiedOrderCondOperator == null && l_orderUnitRow.getOrderCondOperator() != null)
                || l_dblModifiedStopOrderPrice != l_orderUnitRow.getStopOrderPrice())
            {
                //（訂正前の逆指値基準値） != （訂正後の逆指値基準値）または、 
                //（訂正前の発注条件演算子） != （訂正後の発注条件演算子）であれば、 
                //「市場発注済注文の逆指値基準値及び、発注条件演算子は訂正不可」の 例外をthrowする。 
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00690,STR_METHOD_NAME);
            }            
        }        
        //処理終了
        log.exiting(STR_METHOD_NAME);
        if (l_strModifiedOrderCondOperator.equals(l_orderUnitRow.getOrderCondOperator()) 
            && l_dblModifiedStopOrderPrice == l_orderUnitRow.getStopOrderPrice())
        {
            return false;            
        }
        else
        {
            return true;
        }
    }
    
    /**
     * （isChangeW指値条件）<BR>
     * <BR>
     * W指値の条件（発注条件演算子、逆指値基準値、（W指値）訂正指値）に<BR>
     * 訂正が入ったかをチェックする。<BR>
     * <BR>
     * １）　@引数.訂正後発注条件≠”W指値”の場合は、以降の処理は行わずfalseを返す。<BR>
     * <BR>
     * ２）　@訂正元注文の発注条件演算子、逆指値基準値、（W指値）訂正指値、<BR>
     * 　@　@（W指値）執行条件を取得する。 <BR>
     * 株式注文訂正内容.get訂正元注文単位( )にて注文単位オブジェクトを取得する。<BR>
     * 　@－引数.注文単位.発注条件演算子を取得。（訂正前の発注条件演算子）<BR>
     * 　@－引数.注文単位.逆指値基準値を取得。（訂正前の逆指値基準値）<BR>
     * 　@－引数.注文単位.（W指値）訂正指値を取得。（訂正前の（W指値）訂正指値）<BR>
     * 　@－引数.注文単位.（W指値）執行条件を取得。（訂正前の（W指値）執行条件） <BR>
     * <BR>
     * ３）　@訂正入力の発注条件演算子、訂正入力逆指値基準値、訂正入力（W指値）訂正指値、<BR>
     * 訂正入力（W指値）執行条件を取得する。<BR>
     * 　@－引数.訂正後発注条件演算子（訂正後の発注条件演算子）<BR>
     * 　@－引数.訂正後逆指値基準値（訂正後の逆指値基準値）<BR>
     * 　@－引数.訂正後（W指値）訂正指値（訂正後の（W指値）訂正指値）<BR>
     * 　@－引数.訂正後（W指値）執行条件（訂正後の（W指値）執行条件）<BR>
     * <BR>
     * ４）　@処理終了<BR>
     * 　@（訂正前の発注条件演算子） == （訂正後の発注条件演算子） かつ、<BR>
     * 　@（訂正前の逆指値基準値） == （訂正後の逆指値基準値）かつ、<BR>
     * 　@（訂正前の（W指値）訂正指値） == （訂正後の（W指値）訂正指値）かつ、<BR>
     * 　@（訂正前の（W指値）執行条件） == （訂正後の（W指値）執行条件）<BR> 
     * 　@の場合false、以外はtrueを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 訂正元注文（原注文）の注文単位オブジェクト<BR>
     * @@param l_strModifiedOrderConditionType - (訂正後発注条件)<BR>
     * 訂正後発注条件<BR>
     * @@param l_strModifiedOrderCondOperator - (訂正後発注条件演算子)<BR>
     * 訂正後発注条件演算子<BR>
     * @@param l_dblModifiedStopOrderPrice - (訂正後逆指値基準値)<BR>
     * 訂正後逆指値基準値<BR>
     * @@param l_dblModifiedWLimitPrice - (訂正後（W指値）訂正指値)<BR>
     * 訂正後（W指値）訂正指値<BR>
     * @@param l_wLimitExecCondType - (訂正後（W指値）執行条件)<BR>
     * 訂正後（W指値）執行条件<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangeWLimitCondition(
        EqTypeOrderUnit l_orderUnit, 
        String l_strModifiedOrderConditionType, 
        String l_strModifiedOrderCondOperator, 
        double l_dblModifiedStopOrderPrice, 
        double l_dblModifiedWLimitPrice,
        EqTypeExecutionConditionType l_wLimitExecCondType) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isChangeWLimitCondition(EqTypeOrderUnit l_orderUnit, String l_strModifiedOrderConditionType, "
            + "String l_strModifiedOrderCondOperator, double l_dblModifiedStopOrderPrice, double l_dblModifiedWLimitPrice"
            + "EqTypeExecutionConditionType l_wLimitExecCondType)";
        log.entering(STR_METHOD_NAME);
          
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
  
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strModifiedOrderConditionType))
        {
            //引数.訂正後発注条件≠”W指値”の場合は、以降の処理は行わずfalseを返す。 
            return false;
        }
        
        //処理終了
        log.exiting(STR_METHOD_NAME);
        
        if (((l_strModifiedOrderCondOperator != null &&
            l_strModifiedOrderCondOperator.equals(l_orderUnitRow.getOrderCondOperator()))
            || l_strModifiedOrderCondOperator== null && l_orderUnitRow.getOrderCondOperator()==null)
            && l_dblModifiedStopOrderPrice == l_orderUnitRow.getStopOrderPrice()
            && l_dblModifiedWLimitPrice == l_orderUnitRow.getWLimitPrice()
            && l_wLimitExecCondType.equals(l_orderUnitRow.getWLimitExecCondType()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * （validate特定口座開設）。<BR>
     * <BR>
     * 現物株式の特定口座を開設しているかどうかのチェックを行う。<BR>
     * <BR>
     * １）　@引数の税区分≠（TaxTypeEnum.”特定口座”または”特定口座かつ源泉徴収”）の場合は、<BR>
     * 　@　@　@何もせずにreturnする。<BR>
     * 　@　@　@上記以外の場合は、以下の処理を行う。<BR>
     * <BR>
     * ２）　@顧客.is特定口座開設(引数の受渡日, 引数の補助口座, "1：現物株式")をコールする。<BR>
     * 　@　@　@戻り値＝falseの場合は、「特定口座の開設なし」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00711
     * @@param l_subAccount 補助口座オブジェクト。
     * @@param l_taxType 税区分。<BR>
     * 　@　@　@（* 顧客マスタ.税区分）
     * @@param l_datDeliveryDate 受渡日。<BR>
     * 　@　@　@（取引銘柄.受渡日）
     * @@throws WEB3BaseException
     */
    public void validateSpecialAccountEstablish(
        WEB3GentradeSubAccount l_subAccount, 
        TaxTypeEnum l_taxType, 
        Date l_datDeliveryDate) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSpecialAccountEstablish(l_subAccount,l_taxType,l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
                
        if (!TaxTypeEnum.SPECIAL.equals(l_taxType) && !TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
        {
            //引数の税区分≠（TaxTypeEnum.”特定口座”または”特定口座かつ源泉徴収”）の場合は
            //何もせずにreturnする
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        boolean l_blnEstablished =
            l_mainAccount.isSpecialAccountEstablished(
            l_datDeliveryDate, l_subAccount, WEB3GentradeEquityMarginDivDef.EQUITY);
        if (!l_blnEstablished)
        {
            //顧客.is特定口座開設(引数の受渡日, 引数の補助口座, "1：現物株式")をコールする
            //戻り値＝falseの場合は、「特定口座の開設なし」の例外をthrowする。
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00026,
                STR_METHOD_NAME,WEB3EquityTypeOrderManagerReusableValidations.ORDER_ERROR_TAG
                + WEB3ErrorReasonCodeDef.SPEC_ACCOUNT_ERROR);
        }        
    }

    /**
     * （validate銘柄コード（ミニ株））。<BR>
     * <BR>
     * ミニ株銘柄コードのチェックを行う。<BR>
     * <BR>
     * 引数の銘柄コード，証券会社コードに該当する株式銘柄オブジェクトを取得し返却する。<BR>
     * 株式銘柄オブジェクトが取得できなかった場合は、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00717<BR>
     * @@param l_strProductCode 銘柄コード。
     * @@param l_strInstitutionCode 証券会社コード。
     * @@return WEB3EquityProduct 
     */
    public WEB3EquityProduct validateMiniStockProductCode(String l_strProductCode, String l_strInstitutionCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMiniStockProductCode(String, String)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityProduct l_equityProduct;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        try
      {
            //get 証券会社
            Institution l_institution =
                l_finApp.getAccountManager().getInstitution(
                    l_strInstitutionCode);

            WEB3EquityProductManager l_eqTypeProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();

            //get 銘柄
            l_equityProduct =
                (WEB3EquityProduct) l_eqTypeProductManager.getProduct(
                    l_institution,
                    l_strProductCode);
      }
        catch (NotFoundException l_ex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00717, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);  
        }
        log.exiting(STR_METHOD_NAME);
        return l_equityProduct;
    }
    
    /**
     * （validate取引銘柄（ミニ株））。<BR>
     * <BR>
     * ミニ株取引銘柄のチェックを行う。<BR>
     * 　@－銘柄がミニ株を取り扱っているかのチェック（ミニ株市場チェック）<BR>
     * 　@－ミニ株売買が停止されていないことのチェック<BR>
     * <BR>
     * １）　@ミニ株市場取得<BR>
     * 　@株式銘柄.getミニ株市場()にて、市場オブジェクトを取得する。<BR>
     * nullが返却された場合は、ミニ株を取り扱っていない銘柄であると判定し、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00718<BR>
     * <BR>
     * ２）　@取引銘柄取得<BR>
     * 　@プロダクトマネージャ.getTradedProduct()にて、取引銘柄オブジェクトを取得する。<BR>
     * <BR>
     * 　@[getTradedProduct()に指定する引数]<BR>
     * 　@product：　@株式銘柄<BR>
     * 　@market：　@市場<BR>
     * <BR>
     * ３）　@ミニ株売買が停止されていないことのチェック<BR>
     * 　@（取引銘柄.isミニ株売買規制() == true）の場合、ミニ売買停止中と判定し、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00719<BR>
     * <BR>
     * 　@[isミニ株売買規制()に指定する引数]<BR>
     * 　@is売注文：　@is売注文<BR>
     * <BR>
     * 　@取引銘柄オブジェクトを返却する。
     * @@param l_product 株式銘柄。
     * @@param l_blnIsSellOrder is売注文。<BR>
     * 　@　@　@売注文かどうかを判定するフラグ <BR>
     * <BR>
     * 　@　@　@true：　@売注文 <BR>
     * 　@　@　@false：　@買注文
     * @@return WEB3EquityTradedProduct 
     */
    public WEB3EquityTradedProduct validateMiniStockTradedProduct(
        WEB3EquityProduct l_product, boolean l_blnIsSellOrder)  
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMiniStockTradedProduct(WEB3EquityProduct, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_product == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);             
        }
        Market l_market;
        WEB3EquityTradedProduct l_equityTradedProduct;
        try 
        {
            l_market = l_product.getMiniStockMarket();
            
            if (l_market == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00718, 
                        this.getClass().getName() + STR_METHOD_NAME);
            }
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            
            WEB3EquityProductManager l_eqTypeProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();

            l_equityTradedProduct =
                (WEB3EquityTradedProduct)l_eqTypeProductManager.getTradedProduct(l_product, l_market);
        } 
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
        if ( l_equityTradedProduct.isMiniStockTradeControl(l_blnIsSellOrder) )
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00719,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_equityTradedProduct;
    }

    /**
     * （validateミニ株買付株数）。<BR>
     * <BR>
     * ミニ株買付注文株数のチェックを行う。<BR>
     * 　@－0またはマイナス値でないことのチェック<BR>
     * 　@－株式の売買単位を超えていないかのチェック（上限数量チェック）<BR>
     * 　@－ミニ株の売買単位の整数倍になっているかのチェック（売買単位チェック）<BR>
     * <BR>
     * １）　@0またはマイナス値でないことのチェック<BR>
     * 　@super.validateQuantity(株数：double)にて、<BR>
     * 株数が0またはマイナス値でないかチェックを行う。<BR>
     * <BR>
     * ２）　@上限数量チェック<BR>
     * 　@取引銘柄.get売買単位()にて、株式売買単位を取得する。<BR>
     * 　@（取引銘柄.get売買単位() <= 株数）の場合、<BR>
     * 　@上限株数を超えていると判定し例外をスローする。 <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00720<BR>
     * <BR>
     * ３）　@売買単位チェック  <BR>
     * 　@取引銘柄.getミニ株売買単位()にて、ミニ株の売買単位を取得する。<BR>
     * 　@数量が売買単位で割り切れない（株数 % 取引銘柄.getミニ株売買単位() != 0）場合、<BR>
     * 　@売買単位が不正であると判定し例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00721<BR>
     * @@param l_tradedProduct 取引銘柄
     * @@param l_dblQuantity 株数
     */
    public void validateMiniStockBuyQuantity(WEB3EquityTradedProduct l_tradedProduct, double l_dblQuantity) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMiniStockBuyQuantity(WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        try 
        {
            super.validateQuantity(l_dblQuantity);
        } 
        catch (OrderValidationException l_ovex)
        {
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00709,STR_METHOD_NAME);
        }    
        
        if (l_tradedProduct.getLotSize() <= l_dblQuantity)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00720,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if (l_dblQuantity % l_tradedProduct.getMiniStockLotSize() != 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00721,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * （validateミニ株売付株数）。<BR>
     * <BR>
     * ミニ株売付注文株数のチェックを行う。<BR>
     * 　@－0またはマイナス値でないことのチェック<BR>
     * 　@－売付可能株数チェック<BR>
     * 　@－売買単位チェック　@※数量が売買単位でない場合のみ。<BR>
     * <BR>
     * １）　@0またはマイナス値でないことのチェック<BR>
     * 　@super.validateQuantity(株数：double)にて、<BR>
     *   株数が0またはマイナス値でないかチェックを行う。<BR>
     *  <BR>
     * ２）　@約残株数合計（保有株数）取得<BR>
     * 　@ポジションマネージャ.getミニ株保有株数()にて、約残株数合計を取得する。<BR>
     * <BR>
     * 　@[getミニ株保有株数()に指定する引数]<BR>
     * 　@口座ＩＤ：　@補助口座.getAccountId()<BR>
     * 　@補助口座ＩＤ：　@補助口座.getSubAccountId()<BR>
     * 　@銘柄ＩＤ：　@株式銘柄.getProductId()<BR>
     * <BR>
     * ３）　@売付注文中株数取得<BR>
     * 　@注文マネージャ.getミニ株注文中株数()にて、売付注文中株数を取得する。<BR>
     *  <BR>
     * 　@[getミニ株注文中株数()に指定する引数]<BR>
     * 　@口座ＩＤ：　@補助口座.getAccountId()<BR>
     * 　@補助口座ＩＤ：　@補助口座.getSubAccountId()<BR>
     * 　@銘柄ＩＤ：　@株式銘柄.getProductId()<BR>
     * 　@is売注文：　@true<BR>
     * <BR>
     * ４）　@売付可能株数計算<BR>
     * 　@以下の計算式で、売付可能株数を取得する。<BR>
     * <BR>
     * 　@売付可能株数 = 約残株数合計（保有株数） - 売付注文中株数<BR>
     * <BR>
     * ５）　@売付可能株数チェック<BR>
     * 　@以下の条件に当てはまれば、売付可能数量を超過していると判定し、
     * 　@例外をスローする。<BR>
     *  <BR>
     * 　@[Error条件]<BR>
     * 　@（売付可能株数　@< 株数）<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00167<BR>
     * <BR>
     * ６）　@売買単位チェック<BR>
     * 　@※数量が売買単位でない（株数 % 取引銘柄.getミニ株売買単位() != 0）場合
     *   のみ当該チェックを行う。<BR>
     *   <BR>
     *  　@以下の条件に当てはまれば、売買単位が不正であると判定し例外をスローする。<BR>
     *   <BR>
     *  　@[Error条件]<BR>
     *  　@（売付可能株数 ％ 取引銘柄.getミニ株売買単位()） != <BR>
     * 　@（株数 ％ 取引銘柄.getミニ株売買単位()）  <BR> 
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00723<BR>
     * @@param l_subAccount (補助口座)>
     * @@param l_tradedProduct 取引銘柄
     * @@param l_dblQuantity 株数
     */
    public void validateMiniStockSellQuantity(
        WEB3GentradeSubAccount l_subAccount,WEB3EquityTradedProduct l_tradedProduct, double l_dblQuantity) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMiniStockBuyQuantity(WEB3GentradeSubAccount, WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        try 
        {
            super.validateQuantity(l_dblQuantity);
        } 
        catch (OrderValidationException l_ovex)
        {
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00709,STR_METHOD_NAME);
        }    
        if (l_tradedProduct.getLotSize() <= l_dblQuantity)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00720,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //株式ポジションマネージャを取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        // ２）　@約残株数合計（保有株数）取得
        WEB3EquityProduct l_equityProduct = (WEB3EquityProduct) l_tradedProduct.getProduct();
        double l_dblMiniStockQuantity = l_positionManager.getMiniStockQuantity(
            l_subAccount.getAccountId(),
            l_subAccount.getSubAccountId(),
            l_equityProduct.getProductId()
            );
        //３）　@売付注文中株数取得
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        double l_dblOrderingQuantity = l_orderManager.getMiniStockOrderingQuantity(
            l_subAccount.getAccountId(),
            l_subAccount.getSubAccountId(),
            l_equityProduct.getProductId(),
            true
            );
        //４）　@売付可能株数計算
        double l_dblSellpossibleQuantity = l_dblMiniStockQuantity - l_dblOrderingQuantity;
        
        if (l_dblSellpossibleQuantity < l_dblQuantity)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00167,STR_METHOD_NAME);
        }
        if (l_dblQuantity % l_tradedProduct.getMiniStockLotSize() != 0)
        {
            if ( (l_dblSellpossibleQuantity % l_tradedProduct.getMiniStockLotSize()) != 
                (l_dblQuantity % l_tradedProduct.getMiniStockLotSize()))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00723,STR_METHOD_NAME);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （validateミニ株重複注文）。<BR>
     * <BR>
     * 同一日に同一銘柄のミニ注文がされていないかチェックを行う。<BR>
     * ※ ミニ株は、同一日に同一銘柄を売買できない。<BR>
     * <BR>
     * 以下の条件で株式注文単位テーブルを検索する。<BR>
     *  <BR>
     * 　@[条件]<BR>
     * 　@株式注文単位.口座ＩＤ = 補助口座.getAccountId() And<BR>
     * 　@株式注文単位.補助口座ＩＤ = 補助口座.getSubAccountId() And<BR>
     * 　@株式注文単位.銘柄ＩＤ = 取引銘柄.getProduct().getProductId() And<BR>
     * 　@株式注文単位.受渡日 = 取引銘柄.getDailyDeliveryDate() And<BR>
     * 　@株式注文単位.注文状態 != OrderStatusEnum.CANCELLED And<BR>
     * 　@株式注文単位.失効区分 != INVALIDATED_BY_MKT And<BR>
     * 　@（株式注文単位.注文種別 = OrderTypeEnum.MINI_STOCK_BUY Or<BR>
     * 　@　@株式注文単位.注文種別 = OrderTypeEnum.MINI_STOCK_SELL）<BR>
     *  <BR>
     * 　@該当する行が存在する場合は、<BR>
     * 　@　@同一日に同一銘柄の注文がされていると判定し例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00728<BR>
     * @@param l_subAccount (補助口座)
     * @@param l_tradedProduct (取引銘柄)
     */
    public void validateMiniStockDuplicateOrder(
        WEB3GentradeSubAccount l_subAccount, WEB3EquityTradedProduct l_tradedProduct) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "validateMiniStockDuplicateOrder(WEB3GentradeSubAccount, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
        }

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");                                   //口座ＩＤ
        l_sbWhere.append(" and sub_account_id = ? ");                        //補助口座ＩＤ
        l_sbWhere.append(" and product_id = ? ");                              //銘柄ＩＤ
        l_sbWhere.append(" and delivery_date = ? ");                         //受渡日
        l_sbWhere.append(" and order_status <> ? ");                          //注文状態
        l_sbWhere.append(" and expiration_status <> ? ");                    //失効区分
        l_sbWhere.append(" and ( order_type = ? or order_type = ?)"); //注文種別

            
        String l_lngAccountId = String.valueOf(l_subAccount.getAccountId());
        String l_lngSubAccountId = String.valueOf(l_subAccount.getSubAccountId());
        String l_lngProductId = String.valueOf(l_tradedProduct.getProduct().getProductId());
        Date l_datDailyDeliveryDate = l_tradedProduct.getDailyDeliveryDate();
        String  l_orderStatus = String.valueOf(OrderStatusEnum.CANCELLED.intValue());
        String  l_orderExpirationStatus = String.valueOf(OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue());
        String l_orderTypeBuy =String.valueOf( OrderTypeEnum.MINI_STOCK_BUY.intValue());
        String  l_orderTypeSell = String.valueOf(OrderTypeEnum.MINI_STOCK_SELL.intValue());
        
        Object[] l_objEqtypeOrderUnitWhere = { 
            l_lngAccountId,            
            l_lngSubAccountId,        
            l_lngProductId,
            l_datDailyDeliveryDate,
            l_orderStatus,
            l_orderExpirationStatus,
            l_orderTypeBuy,
            l_orderTypeSell   
            }; 

        List l_lisRecords = null;
        QueryProcessor l_QueryProcessor = null;
        try 
        {
            l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                EqtypeOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_objEqtypeOrderUnitWhere);
        }
        catch (DataFindException l_e) 
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }  
        catch (DataQueryException l_e) 
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }

        if (l_lisRecords.size() > 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00728, STR_METHOD_NAME);
        }
            
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （validateミニ株買付制限期間注文株数）。<BR>
     * <BR>
     * 買付制限期間中の場合、単位株数以上の注文がされていないかチェックを行う。<BR>
     * <BR>
     * １）　@株式銘柄オブジェクト取得<BR>
     * 　@取引銘柄.getProduct()にて、株式銘柄オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@制限期間かの判定<BR>
     * 　@－株式銘柄.get権利落日()にて権利落日を取得する。<BR>
     * 　@－制限期間内※でなければ、処理を終了する（return）。<BR>
     *   <BR>
     * 　@※ 制限期間内<BR>
     * 　@権利落日の４営業日前 <= 取引銘柄.getBaseDate() <= 権利落日の１営業日前<BR>
     * <BR>
     * ３）　@売買単位を取得する。<BR>
     * 　@取引銘柄.get売買単位()にて、売買単位を取得する。<BR>
     * <BR>
     * ４）　@単位未満株数を取得する。<BR>
     * 　@this.get単位未満株数()にて、単位未満株数を取得する。<BR>
     * <BR>
     * 　@[get単位未満株数()に指定する引数] <BR
     * 　@補助口座：　@補助口座<BR>
     * 　@株式銘柄：　@株式銘柄<BR>
     * 　@売買単位：　@売買単位<BR>
     * <BR>
     * ５）　@株数をチェックする<BR>
     * 　@　@　@以下の条件にあてはまる場合、<BR>
     * 　@　@　@買付制限期間中に単位株数以上注文されたと判定し例外をスローする。<BR>
     * 　@　@　@[Error条件]<BR>
     * 　@　@　@（単位未満株数 + 株数） >= 取引銘柄.get売買単位()<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_00724
     * @@param l_subAccount (補助口座)
     * @@param l_tradedProduct (取引銘柄)
     * @@param l_dblQuantity (株数)<BR>
     * 注文株数
     */
    public void validateMiniStockBuyDeregTermQuantity(
        WEB3GentradeSubAccount l_subAccount, WEB3EquityTradedProduct l_tradedProduct, double l_dblQuantity) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMiniStockBuyDeregTermQuantity(WEB3GentradeSubAccount, WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //１）　@株式銘柄オブジェクト取得
        WEB3EquityProduct l_equityProduct = (WEB3EquityProduct)l_tradedProduct.getProduct();
        //２）　@制限期間かの判定
        Date l_datDevidendRightDate = l_equityProduct.getDevidendRightDate();
        Timestamp l_tsLast = new Timestamp(l_datDevidendRightDate.getTime());
        WEB3GentradeBizDate l_bizDateLast = new WEB3GentradeBizDate(l_tsLast);
        Date l_datLast = l_bizDateLast.roll(-4);
        Timestamp l_tsNext = new Timestamp(l_datDevidendRightDate.getTime());
        WEB3GentradeBizDate l_bizDateNext = new WEB3GentradeBizDate(l_tsNext);
        Date l_datNext = l_bizDateNext.roll(-1);
                
        int l_intResultsLast = WEB3DateUtility.compareToDay(
            l_datLast, 
            l_tradedProduct.getBaseDate()
            );
        int l_intResultsNext = WEB3DateUtility.compareToDay(
            l_datNext, 
            l_tradedProduct.getBaseDate()
            );    
        if (l_intResultsLast <= 0 && l_intResultsNext >= 0)
        {
            //４）　@単位未満株数を取得する
            double l_oddLotQuantity = this.getOddLotQuantity(
                l_subAccount,
                l_equityProduct,
                l_tradedProduct.getLotSize()
                );
            //５）　@株数をチェックする
            if (l_oddLotQuantity + l_dblQuantity >= l_tradedProduct.getLotSize())
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00724, STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （validateミニ株売付制限期間注文株数）。<BR>
     * <BR>
     * 売付制限期間中の場合、単位株の売注文がされていないかチェックを行う。<BR>
     *  <BR>
     * １）　@株式銘柄オブジェクト取得<BR>
     * 　@取引銘柄.getProduct()にて、株式銘柄オブジェクトを取得する。<BR>
     *  <BR>
     * ２）　@制限期間かの判定<BR>
     * 　@－株式銘柄.get権利落日()にて権利落日を取得する。<BR>
     * 　@－制限期間内※でなければ、処理を終了する（return）。<BR>
     *  <BR>
     * 　@※ 制限期間内<BR>
     * 　@権利落日 <= 取引銘柄.getBaseDate() <= 権利落日の３営業日後 <BR>
     *  <BR>
     * ３）　@売買単位を取得する。<BR>
     * 　@取引銘柄.get売買単位()にて、売買単位を取得する。<BR>
     *  <BR>
     * ４）　@単位未満株数を取得する。<BR>
     * 　@this.get単位未満株数()にて、単位未満株数を取得する。<BR>
     *  <BR>
     * 　@[get単位未満株数()に指定する引数]<BR>
     * 　@補助口座：　@補助口座<BR>
     * 　@株式銘柄：　@株式銘柄<BR>
     * 　@売買単位：　@売買単位<BR>
     *  <BR>
     * ５）　@株数をチェックする<BR>
     * 　@以下の条件にあてはまる場合、
     *   売付制限期間中に単位株の売注文がされたと判定し例外をスローする。 <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00725<BR>
     *   <BR>
     * 　@[Error条件]  <BR>
     * 　@単位未満株数 ＜ 株数 <BR>
     * @@param l_subAccount (補助口座)
     * @@param l_tradedProduct (取引銘柄)
     * @@param l_dblQuantity (株数)
     * 　@　@　@注文株数
     */
    public void validateMiniStockSellDeregTermQuantity(
        WEB3GentradeSubAccount l_subAccount, WEB3EquityTradedProduct l_tradedProduct, double l_dblQuantity) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMiniStockSellDeregTermQuantity(WEB3GentradeSubAccount, WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);             
        }
        //１）　@株式銘柄オブジェクト取得
        WEB3EquityProduct l_equityProduct = (WEB3EquityProduct)l_tradedProduct.getProduct();
        //２）　@制限期間かの判定
        Date l_datLast = l_equityProduct.getDevidendRightDate();
        Timestamp l_tsLast = new Timestamp(l_datLast.getTime());
        WEB3GentradeBizDate l_bizDateLast = new WEB3GentradeBizDate(l_tsLast);
        Date l_datNext = l_bizDateLast.roll(3);
        int l_intResultsLast = WEB3DateUtility.compareToDay(
            l_datLast, 
            l_tradedProduct.getBaseDate()
            );
        int l_intResultsNext = WEB3DateUtility.compareToDay(
            l_datNext, 
            l_tradedProduct.getBaseDate()
            );    
        if (l_intResultsLast <= 0 && l_intResultsNext >= 0)
        {
            //４）　@単位未満株数を取得する
            double l_oddLotQuantity = this.getOddLotQuantity(
                l_subAccount,
                l_equityProduct,
                l_tradedProduct.getLotSize()
                );
            //５）　@株数をチェックする

            if (l_oddLotQuantity < l_dblQuantity)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00725, STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME); 
    }

    /**
     * （get単位未満株数）。<BR>
     * <BR>
     * 単位未満株数を取得する。<BR>
     * <BR>
     * １）　@約残株数合計（保有株数）取得 <BR>
     * 　@ポジションマネージャ.getミニ株保有株数()にて、約残株数合計を取得する。<BR>
     * <BR>
     * 　@[getミニ株保有株数()に指定する引数]<BR>
     * 　@口座ＩＤ：　@補助口座.getAccountId()<BR>
     * 　@補助口座ＩＤ：　@補助口座.getSubAccountId()<BR>
     * 　@銘柄ＩＤ：　@株式銘柄.getProductId()<BR>
     * <BR>
     * ２）　@買付注文中株数取得<BR>
     * 　@注文マネージャ.getミニ株注文中株数()にて、買付注文中株数を取得する。<BR>
     * <BR> 
     * 　@[getミニ株注文中株数()に指定する引数]<BR>
     * 　@口座ＩＤ：　@補助口座.getAccountId()<BR>
     * 　@補助口座ＩＤ：　@補助口座.getSubAccountId()<BR>
     * 　@銘柄ＩＤ：　@株式銘柄.getProductId()<BR>
     * 　@is売注文：　@false<BR>
     * <BR>
     * ３）　@売付注文中株数取得<BR>
     * 　@注文マネージャ.getミニ株注文中株数()にて、買付注文中株数を取得する。<BR>
     * <BR>
     * 　@[getミニ株注文中株数()に指定する引数]<BR>
     * 　@口座ＩＤ：　@補助口座.getAccountId()<BR>
     * 　@補助口座ＩＤ：　@補助口座.getSubAccountId()<BR>
     * 　@銘柄ＩＤ：　@株式銘柄.getProductId()<BR>
     * 　@is売注文：　@true<BR>
     * <BR>
     * ４）　@単位未満株数計算<BR>
     * 　@以下の計算式にて、単位未満株数を計算し返却する。<BR>
     * <BR>
     * 　@[計算式]<BR>
     * 　@（約残株数合計 + 買付注文中株数 - 売付注文中株数） ％ 売買単位
     * @@param l_subAccount (補助口座)
     * @@param l_product (株式銘柄)
     * @@param l_dblLotSize (売買単位)
     * @@return double
     */
    public double getOddLotQuantity(
        WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product, double l_dblLotSize) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOddLotQuantity(WEB3GentradeSubAccount, WEB3EquityProduct, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_product == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);             
        }
        //株式ポジションマネージャを取得
         FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
         TradingModule l_tradingModule =
             l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
         WEB3EquityPositionManager l_positionManager =
             (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        double l_dblMiniStockQuantity = l_positionManager.getMiniStockQuantity(
            l_subAccount.getAccountId(),
            l_subAccount.getSubAccountId(),
            l_product.getProductId()
            );
            
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();    
        double l_orderingQuantityBuy = l_orderManager.getMiniStockOrderingQuantity(
            l_subAccount.getAccountId(),
            l_subAccount.getSubAccountId(),
            l_product.getProductId(),
            false        
            );
        double l_orderingQuantitySell = l_orderManager.getMiniStockOrderingQuantity(
            l_subAccount.getAccountId(),
            l_subAccount.getSubAccountId(),
            l_product.getProductId(),
            true        
            );
        double l_oddLotQuantity =
            (l_dblMiniStockQuantity + l_orderingQuantityBuy - l_orderingQuantitySell) % l_dblLotSize;
        
        log.exiting(STR_METHOD_NAME);     
        return l_oddLotQuantity;
    }

    /**
     * （isChange注文失効日）。<BR>
     * <BR>
     * 注文失効日に訂正が入ったかをチェックする。<BR>
     * <BR>
     * ・引数.注文単位.注文失効日付を(訂正前の注文失効日)とする。<BR>
     * ・引数.訂正後注文失効日を（訂正後の注文失効日）とする。<BR>
     * <BR>
     * （訂正前の注文失効日） == （訂正後の注文失効日） の場合false、<BR>
     * 以外はtrueを返却する。
     * @@param l_orderUnit (注文単位)<BR>
     * 　@　@　@訂正元注文（原注文）の注文単位オブジェクト。
     * @@param l_datExpirationDate (訂正後注文失効日)<BR>
     * 　@　@　@訂正後注文失効日。
     * @@return boolean
     */
    protected boolean isChangeExpirationDate(
        EqTypeOrderUnit l_orderUnit,
        Date l_datExpirationDate)
    {
        final String STR_METHOD_NAME = "isChangeExpirationDate(EqTypeOrderUnit, Date)";
        log.entering(STR_METHOD_NAME);
        Timestamp l_tsExpirationDate = new Timestamp(l_datExpirationDate.getTime());
        log.exiting(STR_METHOD_NAME);
        return (l_orderUnit.getExpirationTimestamp().equals(l_tsExpirationDate) == false);
    }

    /**
     * （isChange値段条件）。<BR>
     * <BR>
     * 値段条件に訂正が入ったかをチェックする。<BR>
     * <BR>
     * １）　@訂正元注文の値段条件、指値を取得する。<BR>
     * 引数.注文単位.値段条件を取得する。（訂正前の値段条件）<BR>
     * 引数.注文単位.指値を取得する。（訂正前の指値）<BR>
     * <BR>
     * ２）　@訂正入力の値段条件、指値を取得する。<BR>
     * 引数.訂正後値段条件（訂正後の値段条件）<BR>
     * 引数.訂正後指値（訂正後の指値）<BR>
     * <BR>
     * ３）　@値段条件変更チェック<BR>
     * <BR>
     * ３－１）　@（訂正前の値段条件） == （訂正後の値段条件）の場合は、falseを返却する。<BR>
     * <BR>
     * ３－２）　@（引数の注文単位.市場から確認済の数量 != Double.NaN）（＝場中の当日注文）<BR>
     * 　@　@　@　@　@の場合は、（訂正後の指値）≠0（＝指値注文）の場合のみ、以下のチェックを行う。<BR>
     * <BR>
     * 　@　@　@　@　@（訂正前の値段条件） != （訂正後の値段条件）であっても、<BR>
     * 　@　@　@　@　@（訂正前の指値） == （訂正後の指値）の場合は、falseを返却する。<BR>
     * <BR>
     * 　@　@　@　@　@※値条注文→指値注文への訂正時には、単価の訂正がない場合は「訂正なし」とする。<BR>
     * 　@　@　@　@　@※値段条件内の訂正（成行残数指値→成行残数取消、等）は「訂正あり」とする。<BR>
     * <BR>
     * ３－３）　@上記以外の場合は、trueを返却する。
     * @@param l_orderUnit (注文単位)<BR>
     * 　@　@　@訂正元注文（原注文）の注文単位オブジェクト。
     * @@param l_strPriceConditionType (訂正後値段条件)<BR>
     * 　@　@　@訂正後値段条件。
     * @@param l_dblLimitPrice (訂正後指値)<BR>
     * 　@　@　@訂正後指値。
     * @@return boolean
     */
    protected boolean isChangePriceConditionType(
        EqTypeOrderUnit l_orderUnit,
        String l_strPriceConditionType,
        double l_dblLimitPrice)
    {
        final String STR_METHOD_NAME = "isChangePriceConditionType(EqTypeOrderUnit, String, double)";
        log.entering(STR_METHOD_NAME);

        // ３）　@値段条件変更チェック
        // ３－１）　@（訂正前の値段条件） == （訂正後の値段条件）の場合は、falseを返却する。
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_strPriceConditionType == null ||
            l_strPriceConditionType.equals(l_orderUnitRow.getPriceConditionType()))
        {
            return false;
        }

        // ３－２）　@（引数の注文単位.市場から確認済の数量 != Double.NaN）（＝場中の当日注文）
        // 　@　@　@　@　@の場合は、（訂正後の指値）≠0（＝指値注文）の場合のみ、以下のチェックを行う。
        // 　@　@　@　@　@（訂正前の値段条件） != （訂正後の値段条件）であっても、
        // 　@　@　@　@　@（訂正前の指値） == （訂正後の指値）の場合は、falseを返却する。
        if ((l_orderUnitRow.getConfirmedQuantityIsNull() == false)
        && (l_dblLimitPrice != 0))
        {
            if (l_dblLimitPrice == l_orderUnitRow.getLimitPrice())
            {
                return false;
            }
        }
        log.exiting(STR_METHOD_NAME);
        // ３－３）　@上記以外の場合は、trueを返却する。  
        return true;
    }
    
    /**
     * （isChange決済数量内訳）。<BR>
     * <BR>
     * 決済数量内訳に訂正が入ったかをチェックする。<BR>
     * 内訳に訂正がある場合はtrue、以外はfalseを返却する。<BR>
     * <BR>
     * １）　@（返済注文、現引現渡注文）以外の場合はfalseを返却する。<BR>
     * <BR>
     * ２）　@訂正前の返済指定情報オブジェクトを取得する。<BR>
     * 　@引数の注文単位.getContractsToClose( )で取得。<BR>
     * <BR>
     * ３）　@値の比較<BR>
     * 　@引数の訂正後決済指定エントリの要素数分LOOPし、値の比較を行う。<BR>
     * 　@（訂正前の返済指定情報.返済注文数量 != 引数の訂正後決済指定エントリ.訂正後株数）の場合、<BR>
     * 　@trueを返却する。<BR>
     * <BR>
     * ４）　@falseを返却する。<BR>
     * @@param l_orderUnit（注文単位）<BR>
     * 　@　@　@訂正元注文（原注文）の注文単位オブジェクト<BR>
     * @@param l_modifiedSettleContractEntries（訂正後決済指定エントリ）<BR>
     * 　@　@　@訂正後決済指定エントリの配列。<BR>
     * @@return boolean
     * @@throws WEB3BusinessLayerException
     */
    protected boolean isChangeEachQuantityOfCloseContract(
        EqTypeOrderUnit l_orderUnit,
        EqTypeSettleContractOrderEntry[] l_modifiedSettleContractEntries)
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME =
            "isChangeEachQuantityOfCloseContract(EqTypeOrderUnit, EqTypeSettleContractOrderEntry[])";
        log.entering(STR_METHOD_NAME);

        OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
        if (!OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg) &&
            !OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        EqTypeClosingContractSpec[] l_closingContractSpecs = null;
        if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
        {
            EqTypeContractSettleOrderUnit l_closingOrderUnit =
                (EqTypeContractSettleOrderUnit)l_orderUnit;
            l_closingContractSpecs = l_closingOrderUnit.getContractsToClose();
        }
        else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
        {
            EqTypeContractSwapOrderUnit l_swapOrderUnit =
                (EqTypeContractSwapOrderUnit)l_orderUnit;
            l_closingContractSpecs = l_swapOrderUnit.getContractsToClose();
        }
        if (l_closingContractSpecs.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00659,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        for (int i = 0; i < l_closingContractSpecs.length; i++)
        {
            if (l_closingContractSpecs[i].getQuantity() !=
                l_modifiedSettleContractEntries[i].getQuantity())
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * （validate成行指定規制）。<BR>
     * <BR>
     * 成行指定規制チェックを行う。<BR>
     * <BR>
     * １）　@以下のいずれの条件にも合致しない場合は、何もせずにreturnする。<BR>
     * <BR>
     * 　@　@　@引数のis成行＝true<BR>
     * 　@　@　@引数の執行条件＝不出来引け成行<BR>
     * <BR>
     * 　@　@　@上記のいずれかの条件に合致する場合は、以下の処理を行う。<BR>
     * <BR>
     * ２）　@成行指定規制チェック<BR>
     * 　@　@　@取引銘柄.is現物成行指定規制(引数のis売注文)をコールする。<BR>
     * 　@　@　@戻り値＝trueの場合は、「現物株式・成行指定規制中」の例外をthrowする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01355<BR>
     * <BR>
     * ３）　@MM銘柄成行指定チェック<BR>
     * 　@　@　@市場(*).getMarketCode( )により市場コードを取得する。<BR>
     * 　@　@　@取得した市場コード＝”JASDAQ”、かつ　@取引銘柄.店頭公開区分＝”マーケットメイク銘柄”<BR>
     * 　@　@　@の場合は、「MM銘柄は成行指定不可」の例外をthrowする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01352<BR>
     * <BR>
     * 　@　@　@(*)市場オブジェクトは、取引銘柄.getMarket()により取得する。
     * @@param l_tradedProduct (取引銘柄)<BR>
     * 　@　@　@取引銘柄オブジェクト。
     * @@param isMarketOrder (is成行)<BR>
     * 　@　@　@成行注文の場合true、以外false。
     * @@param isSellOrder (is売注文)<BR>
     * 　@　@　@売り注文／買い注文のフラグ。<BR>
     * 　@　@　@売り注文の場合true、買い注文の場合falseを指定する。<BR>
     * @@param l_executionCondition (執行条件)<BR>
     * 　@　@　@執行条件。<BR>
     * @@throws WEB3BaseException
     */
    public void validateMarketOrderDesignateCtrl(
        WEB3EquityTradedProduct l_tradedProduct,
        boolean isMarketOrder,
        boolean isSellOrder,
        EqTypeExecutionConditionType l_executionCondition)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMarketOrderDesignateCtrl(EqTypeOrderUnit, boolean, boolean, EqTypeExecutionConditionType)";
        log.entering(STR_METHOD_NAME);

        if ((isMarketOrder == true) ||
            (EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_executionCondition)))
        {
            ;
        }
        else
        {
            //１）　@（成行注文 または 不出来引け成行注文）以外の場合は、何もせずにreturnする。
            return;
        }

        // ２）　@成行指定規制チェック
        if (l_tradedProduct.isSpotMarketOrderDesignateCtrl(isSellOrder))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01355,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // ３）　@MM銘柄成行指定チェック
        if (WEB3MarketCodeDef.JASDAQ.equals(l_tradedProduct.getMarketCode()))
        {
            // 取得した市場コード＝”JASDAQ”、かつ　@取引銘柄.店頭公開区分＝”マーケットメイク銘柄”
            // の場合は、「MM銘柄は成行指定不可」の例外をthrowする。
            EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
            if (WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(l_tradedProductRow.getOpenOtcDiv()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01352,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （validateインサイダー）。<BR>
     * <BR>
     * インサイダーチェックを行う。<BR>
     * <BR>
     * １）　@内部者.get内部者(顧客, 株式銘柄)により、内部者オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get内部者( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@顧客：　@引数の補助口座.getMainAccount( )<BR>
     * 　@　@　@株式銘柄：　@引数の株式銘柄<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２）　@取得した内部者オブジェクト.登録状況区分＝"注文停止"の場合は、<BR>
     * 　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01356<BR>
     * <BR>
     * 　@　@　@該当データなしの場合、<BR>
     * 　@　@　@または取得した内部者オブジェクト.登録状況区分≠"注文停止"の場合は、<BR>
     * 　@　@　@そのままreturnする。
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@補助口座
     * @@param l_eqtypeProduct (株式銘柄)<BR>
     * 　@　@　@株式銘柄オブジェクト
     * @@throws WEB3BaseException
     */
    public void validateInsider(
        SubAccount l_subAccount,
        EqTypeProduct l_eqtypeProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInsider(SubAccount, EqTypeProduct)";
        log.entering(STR_METHOD_NAME);

        // １）　@内部者.get内部者(顧客, 株式銘柄)により、内部者オブジェクトを取得する。
        WEB3GentradeInsider l_insider = null;
        try
        {
            l_insider =
                WEB3GentradeInsider.getInsider(
                    new WEB3GentradeMainAccount((MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject()),
                    l_eqtypeProduct);
        }
        catch (WEB3BaseException l_exp)
        {
            // 該当データ無しの場合は、そのままリターンする。
            return;
        }

        // ２）　@取得した内部者オブジェクト.登録状況区分＝"注文停止"の場合は、
        // 　@　@　@例外をthrowする。
        InsiderRow l_insiderRow = (InsiderRow)l_insider.getDataSourceObject();
        if (WEB3InsiderRegistDivDef.ORDER_STOP.equals(l_insiderRow.getRegistDiv()))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01356,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （validate顧客銘柄別取引停止）。<BR>
     * <BR>
     * 顧客銘柄別取引停止チェックを行う。<BR>
     * <BR>
     * １）　@引数の補助口座オブジェクトより、顧客オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@顧客.is取引停止銘柄( )をコールする。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜is取引停止銘柄( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@銘柄ID：　@引数の銘柄ID<BR>
     * 　@　@　@注文種別：　@引数の注文種別<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@戻り値 == trueの場合は「顧客は指定銘柄の該当取引停止中」の例外をthrowする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01357
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@補助口座
     * @@param l_lngProductId (銘柄ID)<BR>
     * 　@　@　@銘柄ID
     * @@param l_orderType (注文種別)<BR>
     * 　@　@　@注文種別
     * @@throws WEB3BaseException
     */
    public void validateAccountProductOrderStop(
        SubAccount l_subAccount,
        long l_lngProductId,
        OrderTypeEnum l_orderType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccountProductOrderStop(SubAccount, long, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        // １）　@引数の補助口座オブジェクトより、顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_account =
            new WEB3GentradeMainAccount((MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject());

        // ２）　@顧客.is取引停止銘柄( )をコールする。
        //　@　@　@戻り値 == trueの場合は「顧客は指定銘柄の該当取引停止中」の例外をthrowする。
        if (l_account.isTradeStopProduct(l_lngProductId, l_orderType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01357,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座ID：[" + l_account.getAccountId()
                + "] 銘柄ID：[" + l_lngProductId
                + "] 注文種別：[" + l_orderType + "]");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （validate取引銘柄）。<BR>
     * <BR>
     * 取引銘柄のチェックを行う。<BR>
     * （validateTradedProduct(Product, Market)のオーバーロード）<BR>
     * <BR>
     * １）　@スーパークラスの処理にて取引銘柄オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@上場チェック<BR>
     * 　@　@　@取引銘柄.上場区分＝”非上場”の場合、<BR>
     * 　@　@　@「当該銘柄は非上場」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01307<BR>
     * <BR>
     * ３）　@取得した取引銘柄オブジェクトを返却する。
     * @@param l_eqTypeProduct (株式銘柄)<BR>
     * 　@　@　@株式銘柄オブジェクト。
     * @@param l_market (市場)<BR>
     * 　@　@　@市場オブジェクト。
     * @@return TradedProduct
     * @@throws WEB3BaseException
     */
    public TradedProduct validateTradedProduct(
        EqTypeProduct l_eqTypeProduct,
        Market l_market)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateTradedProduct(EqTypeProduct , Market)";
        log.entering(STR_METHOD_NAME);

        // １）　@スーパークラスの処理にて取引銘柄オブジェクトを取得する。
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)super.validateTradedProduct(
                    l_eqTypeProduct,
                    l_market);
        }
        catch (OrderValidationException l_ove)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ove.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage()
                + "銘柄ID：[" + l_eqTypeProduct.getProductId() + "]" + " 市場ID：[" + l_market.getMarketId() + "]");
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            if ( l_bre.getErrorInfo().equals(WEB3ErrorCatalog.SYSTEM_ERROR_80005) )
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_bre.getErrorMessage(),
                    l_bre
                );
            }
            else
            {
                throw l_bre;
            }
        }

        //  ２）　@上場チェック
        // 　@　@　@取引銘柄.上場区分＝”非上場”の場合、「当該銘柄は非上場」の例外をスローする。
        EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        if (WEB3ListTypeDef.UNLISTING.equals(l_tradedProductRow.getListType()))
        {
            log.debug("当銘柄（銘柄ID：[" + l_tradedProductRow.getProductId() + "]）は、非上場です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01307,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄ID：[" + l_tradedProductRow.getProductId() + "]");
        }

        log.exiting(STR_METHOD_NAME);
        return l_tradedProduct;
    }

    /**
     * （validate立会外分売受付可能）。<BR>
     * <BR>
     * 指定された立会外分売銘柄が注文可能かチェックする。<BR>
     * 　@－指定分売が受付可能時間帯の場合は、取得した立会外分売銘柄オブジェクトを返す。<BR>
     * 　@－指定分売が受付可能時間帯でない場合は、例外をthrowする。<BR>
     * <BR>
     * １）　@該当する立会外分売銘柄オブジェクトを取得する。<BR>
     * 　@　@　@※拡張プロダクトマネージャ.get注文可能立会外分売銘柄( )コールにより取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get注文可能立会外分売銘柄( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@証券会社コード：　@引数の補助口座.証券会社コード<BR>
     * 　@　@　@銘柄コード：　@引数の銘柄コード<BR>
     * 　@　@　@市場コード：　@引数の市場コード<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@※get注文可能立会外分売銘柄( )から例外がthrowされた場合は、<BR>
     * 　@　@　@※例外をそのままraiseする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   SYSTEM_ERROR_80005<BR>
     * <BR>
     * ２）　@戻り値≠nullの場合は、受付可能と判定し戻り値の立会外分売銘柄オブジェクトを返す。<BR>
     * <BR>
     * 　@　@　@戻り値＝nullの場合は、<BR>
     * 　@　@　@「指定分売は受付可能時間外」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_01369
     * @@param l_lngProductId (銘柄ID)<BR>
     * 　@　@　@銘柄ID。
     * @@param l_lngMarketId (市場ID)<BR>
     * 　@　@　@市場ID。
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@補助口座。
     * @@throws WEB3BaseException
     * @@return OffFloorOrderProductParams
     */
    public OffFloorOrderProductParams validateOffFloorOrderPossible(
        long l_lngProductId,
        long l_lngMarketId,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOffFloorOrderPossible(long, long, SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        WEB3EquityProduct l_product = null;
        Market l_market = null;
        try
        {
            l_product = (WEB3EquityProduct)l_productManager.getProduct(l_lngProductId);
            l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
        }
        catch (NotFoundException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }
        OffFloorOrderProductParams l_params = l_productManager.getCanOrderOffFloorOrderProduct(
            l_subAccount.getInstitution().getInstitutionCode(),
            l_product.getProductCode(),
            l_market.getMarketCode());
        if (l_params == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01369,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }

    /**
     * （validate立会外分売複数注文）。<BR>
     * <BR>
     * 指定された立会外分売銘柄に対し、既に注文が登録済かどうかをチェックする。<BR>
     * 　@－注文登録済の場合は、例外をthrowする。<BR>
     * <BR>
     * １）　@該当する立会外分売の注文単位データを取得する。<BR>
     * 　@　@　@※拡張株式注文マネージャ.get立会外分売注文単位一覧( )コールにより取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get立会外分売注文単位一覧( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@補助口座：　@引数の補助口座<BR>
     * 　@　@　@銘柄ID：　@引数の銘柄ID<BR>
     * 　@　@　@市場ID：　@引数の市場ID<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２）　@該当データなしの場合は、注文未登録と判定しreturnする。<BR>
     * 　@　@　@該当データが存在する場合は、「指定の立会外分売に対する注文が登録済」の例外を<BR>
     * 　@　@　@throwする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_01370
     * @@param l_lngProductId (銘柄ID)<BR>
     * 　@　@　@銘柄ID
     * @@param l_lngMarketId (市場ID)<BR>
     * 　@　@　@市場ID
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@補助口座
     * @@throws WEB3BaseException
     * @@return OffFloorOrderProductParams
     */
    public void validateOffFloorDuplicateOrder(
        long l_lngProductId,
        long l_lngMarketId,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOffFloorDuplicateOrder(long, long, SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        List l_lisOrderUnits = l_orderManager.getOffFloorOrderUnits(
            l_subAccount,
            l_lngProductId,
            l_lngMarketId);
        if (l_lisOrderUnits != null && l_lisOrderUnits.isEmpty() == false)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01370,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （validate決済総建株数）<BR>
     * <BR>
     * 決済注文時の総建株数のチェックを行う。 <BR>
     * <BR>
     * １）　@総建株数チェックの対象となる建株オブジェクトを全て取得する。 <BR>
     * 　@　@　@（建株の業務キーのうち、建日、期日、建単価を外して検索する） <BR>
     * <BR>
     * 　@　@　@株式ポジションマネージャ.get建株一覧(補助口座, 銘柄タイプ, 検索条件文字列,  <BR>
     * 　@　@　@検索条件データコンテナ)コールにより取得する。 <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * 　@　@　@＜get建株一覧( )：引数設定仕様＞ <BR>
     * <BR>
     * 　@　@　@補助口座：　@引数の補助口座 <BR>
     * 　@　@　@銘柄タイプ：　@ProductTypeEnum."株式" <BR>
     * 　@　@　@検索条件文字列：　@市場ID、建区分、銘柄ID、税区分、弁済区分、弁済期限値 を指定する <BR>
     * 　@　@　@検索条件データコンテナ：　@引数の取引銘柄.市場ID、引数の建区分、引数の取引銘柄.銘柄ID、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@引数の税区分、引数の弁済区分、引数の弁済期限値 の値を指定する <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * <BR>
     * ２）　@総建株数チェック対象の建株に対する、注文中数量の合計値を算出する。 <BR>
     * <BR>
     * 　@　@　@１）で取得した建株オブジェクトの数分Loop： <BR>
     * 　@　@　@　@　@建株.getLockedQuantity( )をコールし、取得結果の合計を求める。 <BR>
     * <BR>
     * ３）　@決済可能総建株数を算出する。 <BR>
     * <BR>
     * 　@　@　@決済可能総建株数 <BR>
     * 　@　@　@　@　@＝ 1）で取得した全ての建株.建株数 の合計値 － ２）で取得した注文中数量の合計値 <BR>
     * 　@　@　@　@　@　@　@＋ 当注文の分の注文中数量(*1) <BR>
     * <BR>
     * 　@　@　@(*1)当注文の分の注文中数量 <BR>
     * 　@　@　@　@　@引数の注文単位ID == 0（＝新規注文）の場合は、0を指定。 <BR>
     * 　@　@　@　@　@引数の注文単位ID != 0（＝訂正）の場合は、 <BR>
     * 　@　@　@　@　@拡張株式注文マネージャ.get未約定数量(引数の注文単位IDに該当する注文単位)の <BR>
     * 　@　@　@　@　@戻り値を指定。 <BR>
     * 　@　@　@　@　@※訂正の場合、訂正対象注文で押さえている分の数量を決済可能総建株数に含める。 <BR>
     * <BR>
     * ４）　@チェック対象の株数を算出する。<BR>
     * <BR>
     * 　@　@　@チェック対象の株数：<BR>
     * 　@　@　@　@　@引数の注文単位ID == 0（＝新規注文）の場合は、引数の株数。<BR>
     * 　@　@　@　@　@引数の注文単位ID != 0（＝訂正）の場合は、<BR>
     * 　@　@　@　@　@（引数の株数 － 引数の注文単位IDに該当する注文単位.約定数量）。<BR>
     * <BR>
     * ５）　@総建株数チェックを行う。 <BR>
     * <BR>
     * 　@　@　@（チェック対象の株数 ＞ 決済可能総建株数） の場合、 <BR>
     * 　@　@　@「総建株数チェックエラー」の例外をthrowする。 <BR>
     * <BR>
     * ６）　@returnする。 <BR>
     * <BR>
     * @@param l_subAccount （補助口座）<BR>
     * 　@　@　@補助口座オブジェクト（信用取引口座）。<BR>
     * @@param l_lngOrderUnitId （注文単位ID）<BR>
     * 　@　@　@注文単位ID。 <BR>
     * <BR>
     * 　@　@　@新規注文の場合は0をセット。<BR>
     * @@param l_equityTradedProduct （取引銘柄）<BR>
     * 　@　@　@取引銘柄オブジェクト。<BR>
     * @@param l_taxTypeEnum （税区分）<BR>
     * 　@　@　@税区分。（xTradeのTaxTypeEnumにて定義）<BR>
     * @@param l_strRepaymentType （弁済区分）<BR>
     * 　@　@　@弁済区分。 <BR>
     * 　@　@　@0：DEFAULT(指定なし) <BR>
     * 　@　@　@1：制度信用 <BR>
     * 　@　@　@2：一般信用<BR>
     * @@param l_dblRepaymentNum （弁済期限値）<BR>
     * 　@　@　@弁済期限値。 <BR>
     * @@param l_dblQuantity （株数）<BR>
     * 　@　@　@入力株数。 <BR>
     * @@param l_contractType （建区分）<BR>
     * 　@　@　@建区分。 <BR>
     * @@throws WEB3BaseException<BR>
     */
     public void validateSettleContractTotalQuantity(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderUnitId,
        WEB3EquityTradedProduct l_equityTradedProduct,
        TaxTypeEnum l_taxTypeEnum,
        String l_strRepaymentType,
        double l_dblRepaymentNum,
        double l_dblQuantity,
        ContractTypeEnum l_contractType) throws WEB3BaseException
     {
        final String STR_METHOD_NAME =
            "validateSettleContractTotalQuantity(WEB3GentradeSubAccount, long, WEB3EquityTradedProduct, TaxTypeEnum, String, double, double, ContractTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        // １）建株オブジェクトの取得
        // 検索条件文字列
        String l_strQueryString = "and market_id = ? " +
            "and contract_type = ? " +
            "and product_id = ? " +
            "and tax_type = ? " +
            "and repayment_type = ? " +
            "and repayment_num = ? ";
            
        // 検索条件データコンテナ
        long l_lngMarketId = l_equityTradedProduct.getMarket().getMarketId();
        long l_lngProductId = l_equityTradedProduct.getProduct().getProductId();
        String[] l_strQueryDataContainer = {String.valueOf(l_lngMarketId),
            String.valueOf(l_contractType.intValue()),
            String.valueOf(l_lngProductId),
            String.valueOf(l_taxTypeEnum.intValue()),
            l_strRepaymentType,
            String.valueOf(l_dblRepaymentNum)};
        
        // 株式ポジションマネージャ取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        
        // get建株一覧()
        List l_lstContracts = l_positionManager.getContracts(l_subAccount,
            ProductTypeEnum.EQUITY,
            l_strQueryString,
            l_strQueryDataContainer);
        
        if (l_lstContracts == null || l_lstContracts.size() == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当する建株データを取得出来ませんでした。検索条件:[" +
                l_strQueryString + "] 検索条件データコンテナ:[" + l_strQueryDataContainer.toString() + "]");
        }
        
        // ２）注文中数量の合計値を算出
        double l_dblTotalLockedQuantity = 0.0D;
        double l_dblTotalQuantity = 0.0D;
        int l_intCount = l_lstContracts.size();
        for (int i = 0; i < l_intCount; i++)
        {
            EqtypeContractRow l_eqtypeContractRow =
                (EqtypeContractRow)l_lstContracts.get(i);
            WEB3EquityContract l_equityContract = new WEB3EquityContract(l_eqtypeContractRow);
            
            l_dblTotalLockedQuantity += l_equityContract.getLockedQuantity();
            l_dblTotalQuantity += l_equityContract.getQuantity();
        }
        
        // 当注文の分の注文中数量、及び約定数量を取得
        double l_dblLockedQuantityCurOrder = 0.0D;
        double l_dblExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0)
        {
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            // 注文単位IDに該当する注文単位を取得
            EqTypeOrderUnit l_eqtypeOrderUnit = null;
            try
            {
                l_eqtypeOrderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "条件に該当する注文単位データを取得出来ませんでした。注文単位ID:[" +
                    l_lngOrderUnitId + "]");
            }
            
            // get未約定数量
            l_dblLockedQuantityCurOrder = l_orderManager.getUnExecutedQuantity(l_eqtypeOrderUnit);

            l_dblExecutedQuantity = l_eqtypeOrderUnit.getExecutedQuantity();
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0.0D;
            }
        }
        
        // ３）決済可能総建株数を算出
        double l_dblSettlePosTotalQuantity =
            l_dblTotalQuantity - l_dblTotalLockedQuantity + l_dblLockedQuantityCurOrder;
            
        // ４）チェック対象の株数を算出
        double l_dblQuantityForCheck = l_dblQuantity - l_dblExecutedQuantity;
        
        // ５）総建株数チェック
        if (l_dblQuantityForCheck > l_dblSettlePosTotalQuantity)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01932,
                STR_METHOD_NAME,
                "注文株数が、決済可能総建株数を超えました。 注文株数:[" +
                l_dblQuantity + "] 決済可能総建株数:[" + l_dblSettlePosTotalQuantity + "]");
        }
        
        log.exiting(STR_METHOD_NAME);
        return;
     }
     
    /**
     * (validate訂正時注文Rev上限)<BR>
     * 訂正時の注文Revが上限を超えないかどうかをチェックする。<BR>
     * <BR>
     * １）　@引数の注文単位オブジェクトのcloneオブジェクトを生成し、<BR>
     * 　@　@　@引数の訂正後の値をそれぞれ該当する項目にセットする。<BR>
     * <BR>
     * 　@　@　@訂正後株数：　@　@　@　@注文数量<BR>
     * 　@　@　@訂正後指値：　@　@　@　@指値<BR>
     * 　@　@　@訂正後執行条件：　@執行条件<BR>
     * 　@　@　@訂正後値段条件：　@値段条件<BR>
     * <BR>
     * ２）　@株式発注サービス.get訂正時注文Rev()をコールする。<BR>
     * 　@　@　@引数には、１）で作成した注文単位オブジェクトをセットする。<BR>
     * 　@　@　@※例外（上限回数オーバー等）がthrowされた場合は、<BR>
     * 　@　@　@※その例外をそのままthrowする。<BR>
     * @@param l_orderUnit - (訂正前注文単位)<BR>
     * 訂正前注文単位オブジェクト
     * @@param l_dblQuantity - (訂正後指値)<BR>
     * 訂正後指値
     * @@param l_dblLimitPrice - (訂正後指値)<BR>
     * 訂正後指値
     * @@param l_executionConditionType - (訂正後執行条件)<BR>
     * 訂正後執行条件
     * @@param l_strPriceConditionType - (訂正後値段条件)<BR>
     * 訂正後値段条件
     * @@throws WEB3BaseException
     */
    public void validateChangeOrderRevUpperLimit(
        EqTypeOrderUnit l_orderUnit,
        double l_dblQuantity,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_executionConditionType,
        String l_strPriceConditionType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateChangeOrderRevUpperLimit(EqTypeOrderUnit, double, double, EqTypeExecutionConditionType, String)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqtypeOrderUnitParams l_orderUnitParams =
            new EqtypeOrderUnitParams(l_orderUnitRow);
        l_orderUnitParams.setQuantity(l_dblQuantity);
        l_orderUnitParams.setLimitPrice(l_dblLimitPrice);
        l_orderUnitParams.setExecutionConditionType(l_executionConditionType);
        l_orderUnitParams.setPriceConditionType(l_strPriceConditionType);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderUnit l_changeAfterOrderUnit =
            (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);
        
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        l_frontOrderService.getChangeOrderRev(l_changeAfterOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate取引銘柄（信用）)<BR>
     * 取扱可能チェックを実施する。<BR>
     * チェック結果がOKの場合は、取引銘柄オブジェクトを返却する。<BR>
     * （validateTradedProductForMarginTrading( )のオーバーロード）<BR>
     * <BR>
     * this.validate取引銘柄（信用）(引数の補助口座, 引数の株式銘柄, 引数の市場, 引数の部店,<BR>
     * 引数の弁済区分, 引数の注文カテゴリ, 引数のis売建, true（＝売買停止チェックをする）)に<BR>
     * delegateする。<BR>
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@補助口座オブジェクト。
     * @@param l_product (株式銘柄)<BR>
     * 　@　@　@株式銘柄オブジェクト。
     * @@param l_market (市場)<BR>
     * 　@　@　@市場オブジェクト。
     * @@param l_branch (部店)<BR>
     * 　@　@　@部店オブジェクト。
     * @@param l_strRepaymentType (弁済区分)<BR>
     * 　@　@　@弁済区分。<BR>
     * 　@　@　@0：DEFAULT（指定なし）<BR>
     * 　@　@　@1：制度信用<BR>
     * 　@　@　@2：一般信用
     * @@param l_orderCateg (注文カテゴリ)<BR>
     * 　@　@　@注文カテゴリ。（xTradeのOrderCategEnumにて定義）
     * @@param l_isShort (is売建)<BR>
     * 　@　@　@売建／買建のフラグ。<BR>
     * 　@　@　@建株＝売建の場合true、買建の場合falseを指定する。
     * @@return WEB3EquityTradedProduct
     * @@throws WEB3BaseException
     */
    public WEB3EquityTradedProduct validateTradedProductForMarginTrading(
        SubAccount l_subAccount,
        WEB3EquityProduct l_product,
        WEB3GentradeMarket l_market,
        WEB3GentradeBranch l_branch,
        String l_strRepaymentType,
        OrderCategEnum l_orderCateg,
        boolean l_isShort)
        throws WEB3BaseException
    {
        return this.validateTradedProductForMarginTrading(
            l_subAccount,
            l_product,
            l_market,
            l_branch,
            l_strRepaymentType,
            l_orderCateg,
            l_isShort,
            true);
    }
    
    /**
     * (validate特殊執行条件取扱停止)<BR>
     * 特殊執行条件注文の取扱停止が設定されているかどうかを<BR>
     * 商品、銘柄、市場単位でチェックする。<BR>
     * <BR>
     * １）　@発注条件指定なし<BR>
     * 　@（パラメータ.発注条件 == "DEFAULT"）の場合、<BR>
     * 　@処理を終了する。<BR>
     * <BR>
     * ２）　@訂正（パラメータ.注文単位ID != 0）の場合、<BR>
     * 　@以下の処理を行う。<BR>
     * 　@２－１）　@パラメータ.注文単位IDに該当する注文単位を取得する。<BR>
     * <BR>
     * 　@２－２）　@発注済の逆指値／切替済のW指値注文の場合、<BR> 
     * 　@　@　@　@　@（拡張株式注文マネージャ.is未発注注文（） == false）<BR> 
     * 　@　@　@　@　@処理を終了する。 <BR>
     * <BR>
     * 　@　@　@[is未発注注文()に指定する引数] <BR>
     * 　@　@　@　@注文単位：　@取得した注文単位 <BR>
     * <BR>
     * ３）　@起動サービスチェックを行う。 <BR>
     * 　@３－１）以下を取得する。 <BR>
     * 　@　@ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     * 　@　@　@WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP<BR> 
     * 　@　@　@　@　@） <BR>
     * 　@３－２）３－１で取得した値 != NULL の場合以下の処理を行う。 <BR>
     * 　@　@　@３－１で取得した値 = BooleanEnum.TRUE の場合、処理を終了する。<BR> 
     * <BR>
     * ４）　@DB検索<BR>
     * 　@以下の条件にて、特殊執行条件取扱停止テーブルを検索する。<BR>
     * 　@[条件]<BR>
     * 　@　@証券会社コード = パラメータ.補助口座.証券会社コード<BR>
     * 　@　@And (<BR>
     * 　@　@　@-- 商品別条件<BR>
     * 　@　@　@(部店コード = パラメータ.補助口座.get取引店().部店コード<BR>
     * 　@　@　@　@And 設定対象種別 = "商品"<BR>
     * 　@　@　@　@And キー情報 = <BR>
     * 　@　@　@　@　@[パラメータ.信用取引区分 == "DEFAULT"の場合]<BR>
     * 　@　@　@　@　@　@"現物株式"<BR>
     * 　@　@　@　@　@[上記以外]<BR>
     * 　@　@　@　@　@　@"信用取引"<BR>
     * 　@　@　@Or<BR>
     * 　@　@　@-- 市場別条件<BR>
     * 　@　@　@(部店コード = "000"<BR>
     * 　@　@　@　@And 設定対象種別 = "市場"<BR>
     * 　@　@　@　@And キー情報 = パラメータ.市場コード)<BR>
     * 　@　@　@Or<BR>
     * 　@　@　@-- 銘柄別条件<BR>
     * 　@　@　@(部店コード = "000"<BR>
     * 　@　@　@　@And 設定対象種別 = "銘柄"<BR>
     * 　@　@　@　@And キー情報 = パラメータ.取引銘柄.銘柄IDに該当する銘柄.銘柄コード<BR>
     * 　@　@　@　@And 有効期限From <= 発注日(*1)<BR>
     * 　@　@　@　@And 有効期限To >= 発注日)<BR>
     * 　@　@) And 削除フラグ = "DEFAULT"<BR>
     * <BR>
     * 　@[ソート条件]<BR>
     * 　@　@設定対象種別 昇順<BR>
     * <BR>
     * ５）　@４）の検索結果の内、パラメータ.発注条件に<BR>
     * 　@対応するテーブル項目のいずれかが"停止中"となっていた場合、<BR>
     * 　@取扱停止が設定されているレコード.設定対象種別に<BR>
     * 　@対応する例外をスローする。<BR>
     * 　@※検索結果が取得できなかった場合、処理を終了する。<BR>
     * <BR>
     * 　@[対応する項目]<BR>
     * 　@　@パラメータ.発注条件 == "逆指値" ⇒ 逆指値注文停止フラグ<BR>
     * 　@　@パラメータ.発注条件 == "W指値" ⇒ W指値注文停止フラグ<BR>
     * <BR>
     * 　@　@※検索結果の要素.設定対象種別 == "市場"　@かつ　@キー情報 == "JASDAQ"　@かつ<BR>
     * 　@　@　@追加情報 != nullの場合、<BR>
     * 　@　@　@追加情報 == パラメータ.取引銘柄.店頭公開区分となる<BR>
     * 　@　@　@要素の注文停止フラグのみを取得する。<BR>
     * 　@　@　@（JASDAQの取扱停止は、「オークション銘柄のみ」 or 「マーケットメイク銘柄のみ」 or<BR>
     * 　@　@　@　@「両方」のいずれかで登録することができる為。　@※追加情報 == nullの場合は「両方」）<BR>
     * 　@　@　@<BR>
     * 　@[設定対象種別に対応する例外]<BR>
     * 　@　@設定対象種別 == "商品"の場合<BR>
     * 　@　@　@⇒　@「指定された条件付注文での商品は取扱停止中」<BR>
     * <BR>
     * 　@　@設定対象種別 == "市場"の場合<BR>
     * 　@　@　@⇒　@「指定された条件付注文での市場は取扱停止中」<BR>
     * <BR>
     * 　@　@設定対象種別 == "銘柄"の場合<BR>
     * 　@　@　@⇒　@「指定された条件付注文での銘柄は取扱停止中」<BR>
     * <BR>
     * (*1)発注日<BR>
     * 　@取引時間管理.get発注日()にて取得する。<BR>
     * @@param l_subAccount - (補助口座)
     * 補助口座オブジェクト
     * @@param l_lngOrderUnitId - (注文単位ID)
     * 注文単位ID
     * 
     * 新規注文の場合は0をセット。
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト。<BR>
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 0：DEFAULT（条件指定なし）　@1：逆指値　@2：W指値<BR>
     * @@param l_strMarginTradedType - (信用取引区分)<BR>
     * 0：DAFAULT（信用取引以外）<BR>
     * 1：制度信用<BR>
     * 2：一般信用<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 0：  DEFAULT<BR>
     * 1：  東京<BR>
     * 2：  大阪<BR>
     * 3：  名古屋<BR>
     * 4：  京都<BR>
     * 5：  広島<BR>
     * 6：  福岡<BR>
     * 8：  札幌<BR>
     * 9：  NNM<BR>
     * 10： JASDAQ<BR>
     * @@throws WEB3BaseException
     */
    public void validateTriggerOrderStop(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderUnitId,
        WEB3EquityTradedProduct l_tradedProduct,
        String l_strOrderConditionType,
        String l_strMarginTradedType,
        String l_strMarketCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTriggerOrderStop(WEB3GentradeSubAccount, " +
            "long, WEB3EquityTradedProduct, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        // 発注条件指定なしの場合、処理を終了する。
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            log.debug("発注条件指定なしなので、処理終了");
	        log.exiting(STR_METHOD_NAME);
	        return;
        }
        
        // 訂正注文の場合
        if (l_lngOrderUnitId != 0)
        {
	        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
	        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_orderUnit = null;
            try
            {
	            l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文単位ID:[" + l_lngOrderUnitId + "]に該当するレコードがありません");
            }

            // 発注済の逆指値／切替済のW指値注文の場合
            //（拡張株式注文マネージャ.is未発注注文（） == false）
	        if (!l_orderManager.isNotOrderedOrder(l_orderUnit))
	        {
	            log.debug("発注済の逆指値／切替済のW指値注文の場合、処理終了");
	            log.exiting(STR_METHOD_NAME);
	            return;
	        }
	    }

        // 起動サービスチェックを行う。
        Object l_objSkipTriggerOrderStop = 
            ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP);
        
        if(l_objSkipTriggerOrderStop != null) 
        {
            if(l_objSkipTriggerOrderStop.equals(BooleanEnum.TRUE)){
                log.debug("スキップ対象サービスなので、処理終了");
                log.exiting(STR_METHOD_NAME);
                return;
            }

        }

        // DB検索
        // 証券会社コード
        String l_strWhere = "institution_code = ? and (";
        ArrayList l_lisBindVal = new ArrayList();
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        l_lisBindVal.add(l_branch.getInstitution().getInstitutionCode());
        
        // 商品別条件
        l_strWhere += "(branch_code = ? "
            + "and target_type = ? "
            + "and key = ?) ";
            
        l_lisBindVal.add(l_branch.getBranchCode());
        l_lisBindVal.add(WEB3TargetTypeDef.COMMODITY);
        if (WEB3MarginTradingDivDef.DEFAULT.equals(l_strMarginTradedType))
        {
            l_lisBindVal.add(WEB3CommodityDivDef.EQUITY);
        }
        else
        {
            l_lisBindVal.add(WEB3CommodityDivDef.MARGIN);
        }
        
        // 市場別条件
        l_strWhere += "or (branch_code = ? "
            + "and target_type = ? "
            + "and key = ?) ";
            
        l_lisBindVal.add(WEB3BranchCodeDef.DEFAULT);
        l_lisBindVal.add(WEB3TargetTypeDef.MARKET);
        l_lisBindVal.add(l_strMarketCode);
        
        // 銘柄別条件
        l_strWhere += "or (branch_code = ? "
            + "and target_type = ? "
            + "and key = ? "
            + "and valid_term_from <= ? "
            + "and valid_term_to >= ?) ";
            
        l_lisBindVal.add(WEB3BranchCodeDef.DEFAULT);
        l_lisBindVal.add(WEB3TargetTypeDef.PRODUCT);
        EqTypeProduct l_product = (EqTypeProduct)l_tradedProduct.getProduct();
        l_lisBindVal.add(l_product.getProductCode());
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        l_lisBindVal.add(l_datBizDate);
        l_lisBindVal.add(l_datBizDate);
        
        l_strWhere += ") and delete_flag = ? ";
        l_lisBindVal.add(BooleanEnum.FALSE);
        
        QueryProcessor l_queryProcessor = null;
        List l_lisTriggerOrderStop = null;
        try
        {
	        l_queryProcessor = Processors.getDefaultProcessor();
	        l_lisTriggerOrderStop = l_queryProcessor.doFindAllQuery(
                TriggerOrderStopRow.TYPE,
                l_strWhere,
                "target_type",
                null,
                l_lisBindVal.toArray());
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DBアクセスに失敗しました");
        }
        
        // 検索結果が取得できなかった場合、処理終了
        if (l_lisTriggerOrderStop == null ||
            l_lisTriggerOrderStop.size() == 0)
        {
            log.debug("該当データなしなので、処理終了");
	        log.exiting(STR_METHOD_NAME);
            return;
        }
        
        for (int i = 0; i < l_lisTriggerOrderStop.size(); i++)
        {
            TriggerOrderStopRow l_triggerOrderStopRow =
                (TriggerOrderStopRow)l_lisTriggerOrderStop.get(i);
            int l_intStopFlag = 0;
            // 発注条件に対応する取扱停止フラグを取得
            // 設定対象種別 == "市場" かつ キー情報 == "JASDAQ" かつ 追加情報 != nullの場合
            if (WEB3TargetTypeDef.MARKET.equals(l_triggerOrderStopRow.getTargetType()) &&
                WEB3MarketCodeDef.JASDAQ.equals(l_triggerOrderStopRow.getKey()) &&
                l_triggerOrderStopRow.getAddition() != null)
            {
                EqtypeTradedProductRow l_tradedProductRow =
                    (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                if (!l_triggerOrderStopRow.getAddition().equals(l_tradedProductRow.getOpenOtcDiv()))
                {
                    continue;
                }
            }
            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                l_intStopFlag = l_triggerOrderStopRow.getStopOrderStopFlag();
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                l_intStopFlag = l_triggerOrderStopRow.getWlimitOrderStopFlag();
            }
            
            if (BooleanEnum.TRUE.intValue() == l_intStopFlag)
            {
                // 設定対象種別に対応する例外をスロー
                String l_strTragetType = l_triggerOrderStopRow.getTargetType();
                if (WEB3TargetTypeDef.COMMODITY.equals(l_strTragetType))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02433,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                else if (WEB3TargetTypeDef.MARKET.equals(l_strTragetType))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02434,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                else if (WEB3TargetTypeDef.PRODUCT.equals(l_strTragetType))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02435,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate市場送信済注文複数回訂正可能（休憩時間中）)<BR>
     * 
     * 昼休み中に市場送信済注文の複数回訂正が可能かどうか判定する。<BR>
     * <BR>
     * 　@１）部店プリファ@レンステーブルから以下の条件でレコードを取得する。<BR>
     * 　@　@[条件] <BR>
     * 　@　@　@部店ID = パラメータ.部店ID And<BR>
     * 　@　@　@プリファ@レンス名 = プリファ@レンス名.市場送信済注文複数回訂正可能（休憩時間中） And<BR>
     * 　@　@　@プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * 　@２）該当レコードが存在する場合のみ以下を行う。<BR>
     * 　@　@取得レコード．プリファ@レンスの値＝訂正不可の場合、<BR>
     * 　@　@例外をthrowする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01352<BR>
     * <BR>
     * @@param l_lngBranchId - (部店ID)<BR>
     * 部店ID
     * @@throws OrderValidationException
     */
    public void validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(
        long l_lngBranchId)
        throws WEB3BaseException, OrderValidationException
    {
        final String STR_METHOD_NAME =
            "validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BranchPreferencesRow l_row = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId, 
                    WEB3BranchPreferencesNameDef.
                        MULTI_CHANGEABILITY_OF_MARKET_NOTIFIED_ORDER_IN_BREAK_TIME,
                    1);                 
            
            if (l_row != null)
            {
                if (WEB3MultiChangeabilityDef.NOT_CHANGEABLE.equals(l_row.getValue()))
                {
                    throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                }
            }
            
        }
        catch (DataNetworkException l_dne)
        {
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        catch (DataFindException l_dfe)
        {
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80004);
        }
        catch (DataQueryException l_dqe)
        {
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * (validate決済建株エントリ毎売買単位 )<BR>
     * 
     * validate決済建株エントリ毎売買単位
     * 
     * 決済建株エントリ一覧の株数が売買単位の整数倍であることをチェックする <BR>
     *  <BR>
     * 　@１）取引銘柄から売買単位を取得する <BR>
     *  <BR>
     * 　@２）決済建株エントリ一覧の全要素に対して、売買単位のチェックを行う <BR>
     * 　@　@　@スーパークラスの処理（checkLotSize(売買単位, 決済建株エントリ．getQuantity（））にて、 <BR>
     * 　@　@　@株数が売買単位の整数倍であるかチェックを行う。 <BR>
     * 　@　@　@チェックNGの場合は、「株数が売買単位の整数倍でない」の例外をスローする。 <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00708<BR>
     * <BR>
     * @@param l_equityTradedProduct - (取引銘柄)<BR>
     * @@param l_eqTypeSettleContractOrderEntrys - (決済建株エントリ一覧)<BR>
     * 
     * @@throws WEB3BaseException
     */
    public void validateEverySettleContractOrderEntryLotSize(
        WEB3EquityTradedProduct l_equityTradedProduct,
        EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntrys)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateEverySettleContractOrderEntryLotSize(WEB3EquityTradedProduct,EqTypeSettleContractOrderEntry)";
        log.entering(STR_METHOD_NAME);
        
        double l_lotSize = l_equityTradedProduct.getLotSize();
        
        try
        {
            for(int i = 0; i < l_eqTypeSettleContractOrderEntrys.length; i++)
            {
                super.checkLotSize(
                    l_lotSize,
                    l_eqTypeSettleContractOrderEntrys[i].getQuantity());
            }
        }
        catch (OrderValidationException ove)
        {
            throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_00708,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateW指値注文)<BR>
     * 発注条件としてW指値が指定された注文について、<BR>
     * 以下のチェックを行う。 <BR>
     * <BR>
     * －（W指値）有効状態チェック　@※訂正時のみ <BR>
     * －（W指値）訂正指値がマイナス値でないこと 。<BR>
     * －（W指値）訂正指値の呼値チェック  <BR>
     * －（W指値）訂正指値の値幅チェック <BR>
     * －（W指値）執行条件取扱可能チェック  <BR>
     * －（W指値）訂正指値注文単価区分チェック <BR>
     * －（W指値）成行注文可能チェック <BR>
     * －発注条件単価乖離チェック  <BR>
     * －発注条件単価入力チェック<BR>
     * －空売り規制チェック <BR>
     * <BR>
     * シーケンス図  <BR>
     * 「（注文）validateW指値注文」参照。 <BR>
     * =====================================================<BR>
     * シーケンス図 : 「（注文）validateW指値注文」 <BR>
     * 具体位置　@　@ : 1.2.5（W指値）有効状態チェック<BR>
     * 　@　@　@　@　@　@　@　@(*)入力画面表示時と現在の注文単位とでW指値注文有効状態に相違がある<BR>
     * 　@　@　@　@　@　@　@　@（getＷ指値用有効状態区分()の戻り値 != パラメータ.（W指値）有効状態区分である）<BR>
     * 　@　@　@　@　@　@　@　@場合、<BR>
     * 　@　@　@　@　@　@　@　@「訂正中にW指値注文有効状態が変更となった為、訂正不可」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@<BR>
     * 　@　@　@　@　@　@　@　@※入力⇒確認⇒完了の間でストップ注文が有効または、失効となってしまった<BR>
     * 　@　@　@　@　@　@　@　@　@（W指値切替処理実施済またはトリガー注文管理者手動失効済の）場合を考慮。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  : BUSINESS_ERROR_02494<BR>
     * =====================================================<BR>
     * =====================================================<BR>
     * シーケンス図 : 「（注文）validateW指値注文」 <BR>
     * 具体位置　@　@ : 1.3 (*)リミット注文・注文単価区分チェック<BR>
     * 　@　@　@　@　@　@　@　@　@リミット注文の注文単価が成行の場合<BR>
     * 　@　@　@　@　@　@　@　@　@（パラメータ.指値 == 0）<BR>
     * 　@　@　@　@　@　@　@　@　@「Ｗ指値注文のリミット注文は成行指定不可」の<BR>
     * 　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  : BUSINESS_ERROR_02496<BR>
     * =====================================================<BR>
     * =====================================================<BR>
     * シーケンス図 : 「（注文）validateW指値注文」 <BR>
     * 具体位置　@　@ : 1.6 is取扱可能執行条件()<BR>
     * 　@　@　@　@　@　@　@　@　@(*)（W指値）執行条件取扱可能チェック<BR>
     * 　@　@　@　@　@　@　@　@　@falseが返却された場合、<BR>
     * 　@　@　@　@　@　@　@　@　@「入力された（W指値）執行条件は取扱不可」の<BR>
     * 　@　@　@　@　@　@　@　@　@の例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  : BUSINESS_ERROR_02495<BR>
     * =====================================================<BR>
     * =====================================================<BR>
     * シーケンス図 : 「（注文）validateW指値注文」 <BR>
     * 具体位置　@　@ : 1.9.1 (*)指値と（W指値）訂正指値のチェック<BR>
     * 　@　@　@　@　@　@　@　@(*)パラメータ.指値 == パラメータ.（W指値）訂正指値の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@「注文単価と（W指値）訂正指値が同値」の<BR>
     * 　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  : BUSINESS_ERROR_02498<BR>
     * =====================================================<BR>
     * <BR>
     * ※引数の「is買注文」は、"建区分"ではなく、"取引"によりセットされる。<BR>
     * 　@現物買付、信用新規買建、信用買返済（売建）の場合、true <BR>
     * 　@現物売付、信用新規売建/信用売返済（買建）の場合、false <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_lngOrderUnitId - (注文単位ＩＤ)<BR>
     * 注文単位ＩＤ<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値<BR>
     * @@param l_strOrderCondition - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_dblOrderCondPrice - (発注条件単価)<BR>
     * 発注条件<BR>
     * @@param l_strWLimitPrice - (（W指値）訂正指値)<BR>
     * （W指値）訂正指値<BR>
     * @@param l_wLimitExecCondType - (（W指値）執行条件)<BR>
     * （W指値）執行条件<BR>
     * @@param l_strWlimitEnableStatusDiv - (（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分<BR>
     * ※新規注文登録時はnull。<BR>
     * @@param l_equityTradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト
     * @@param l_blnIsBuyToOpenOrder - (is買注文)<BR>
     * （isBuyToOpenOrder）<BR>
     * 買注文取引かどうかの判定。<BR>
     * 買注文の場合true、売注文の場合false。<BR>
     * @@param l_strRepaymentType - (弁済区分)<BR>
     * 弁済区分<BR>
     * @@param l_orderCateg - (注文カテゴリ)<BR>
     * 注文カテゴリ<BR>
     * @@param l_dblQuantity - (株数)<BR>
     * 株数<BR>
     * @@param l_strPriceConditionType - (値段条件)<BR>
     * 値段条件<BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別<BR>
     * @@throws WEB3BaseException
     */
    public void validateWLimitPriceOrder(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderUnitId,
        double l_dblLimitPrice,
        String l_strOrderCondition,
        double l_dblOrderCondPrice,
        String l_strWLimitPrice,
        EqTypeExecutionConditionType l_wLimitExecCondType,
        String l_strWlimitEnableStatusDiv,
        WEB3EquityTradedProduct l_equityTradedProduct,
        boolean l_blnIsBuyToOpenOrder,
        String l_strRepaymentType,
        OrderCategEnum l_orderCateg,
        double l_dblQuantity,
        String l_strPriceConditionType,
        OrderTypeEnum l_orderType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateWLimitPriceOrder(WEB3GentradeSubAccount, long, double, String, "
            + "double, String, EqTypeExecutionConditionType, String, "
            + "WEB3EquityTradedProduct, boolean, String, OrderCategEnum, double, String, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_equityTradedProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        // (*)パラメータ.発注条件 != "W指値"の場合
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondition))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderUnit l_eqTypeOrderUnit = null;

        //(*)訂正（パラメータ.注文単位ID != 0）の場合
        if (l_lngOrderUnitId != 0)
        {

            //(*)ストップ注文へ切替済のW指値注文（パラメータ.（W指値）有効状態区分 == "ストップ注文有効"）の場合
            if (WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE.equals(l_strWlimitEnableStatusDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }

            //(*)ストップ注文失効済のW指値注文（パラメータ.（W指値）有効状態区分 == "ストップ注文失効済"）の場合
            if (WEB3EquityWlimitEnableStatusDivDef.STOP_UN_ENABLE.equals(l_strWlimitEnableStatusDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }

            //getOrderUnit(arg0 : long)
            try
            {
                l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文単位ID:[" + l_lngOrderUnitId + "]に該当するレコードがありません");
            }

            //getＷ指値用有効状態区分(注文単位 : EqTypeOrderUnit)
            String l_strWlimitEnableStatusDiv2 =
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_eqTypeOrderUnit);

            //（W指値）有効状態チェック
            if (!WEB3Toolkit.isEquals(l_strWlimitEnableStatusDiv, l_strWlimitEnableStatusDiv2))
            {
                log.debug("訂正中にW指値注文有効状態が変更となった為、訂正不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02494,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "訂正中にW指値注文有効状態が変更となった為、訂正不可。");
            }
        }

        // (*)リミット注文・注文単価区分チェック
        if (l_dblLimitPrice == 0)
        {
            log.debug("Ｗ指値注文のリミット注文は成行指定不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02496,
                this.getClass().getName() + STR_METHOD_NAME,
                "Ｗ指値注文のリミット注文は成行指定不可。");
        }

        double l_dblWLimitPrice = Double.parseDouble(l_strWLimitPrice);
        // validate注文単価(double, 取引銘柄, 補助口座)
        this.validatePrice(l_dblWLimitPrice, l_equityTradedProduct, l_subAccount);

        //取扱可能注文条件(証券会社コード : String, 銘柄タイプ : ProductTypeEnum,
        //      先物／オプション区分 : String, 信用取引区分 : String, 市場コード : String)
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        EqtypeTradedProductRow l_equityTradedProductRow =
            (EqtypeTradedProductRow)l_equityTradedProduct.getDataSourceObject();
        WEB3GentradeMarket l_gentradeMarket = null;
        try
        {
            l_gentradeMarket =
                (WEB3GentradeMarket)l_gentradeFinObjectManager.getMarket(
                    l_equityTradedProductRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("error in l_gentradeFinObjectManager.getMarket(MarketId)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "該当するレコードがありません");
        }

        WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
            l_subAccount.getInstitution().getInstitutionCode(),
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            WEB3MarginTradingDivDef.DEFAULT,
            l_gentradeMarket.getMarketCode());

        // is取扱可能執行条件(執行条件 : EqTypeExecutionConditionType)
        if (!l_handlingOrderCond.isHandlingPossibleExecCond(l_wLimitExecCondType))
        {
            log.debug("入力された（W指値）執行条件は取扱不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02495,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力された（W指値）執行条件は取扱不可。");
        }

        //is成行
        boolean l_blnIsMarketPrice = false;

        //[パラメータ.（W指値）訂正指値 == 0 の場合]
        //trueをセット
        if (l_dblWLimitPrice == 0)
        {
            l_blnIsMarketPrice = true;
        }

        // (*)パラメータ.注文カテゴリ == "現物株式"の場合
        if (OrderCategEnum.ASSET.equals(l_orderCateg))
        {
            // validate成行指定規制
            //  (取引銘柄, is成行(boolean), is売注文(boolean), EqTypeExecutionConditionType)
            this.validateMarketOrderDesignateCtrl(
                l_equityTradedProduct,
                l_blnIsMarketPrice,
                !l_blnIsBuyToOpenOrder,
                l_wLimitExecCondType);
        }

        // (*)パラメータ.注文カテゴリ ！= "現物株式"の場合
        else
        {
            //is売建
            boolean l_blnIsOpenSell = true;

            //注文カテゴリ == "新規建注文"
            if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg))
            {
                //パラメータ.is買注文＝trueの場合
                if (l_blnIsBuyToOpenOrder)
                {
                    l_blnIsOpenSell = false;
                }
            }
            else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
            {
                l_blnIsOpenSell = l_blnIsBuyToOpenOrder;
            }

            this.validateMarketOrderRestraint(
                l_equityTradedProduct,
                l_strRepaymentType,
                l_orderCateg,
                l_blnIsMarketPrice,
                l_blnIsOpenSell,
                l_wLimitExecCondType);
        }

        // (*)（W指値）訂正指値が指値（パラメータ.（W指値）訂正指値 != 0）の場合
        if (l_dblWLimitPrice != 0)
        {
            // (*)指値と（W指値）訂正指値のチェック
            if (l_dblLimitPrice == l_dblWLimitPrice)
            {
                log.debug("注文単価と（W指値）訂正指値が同値。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02498,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "注文単価と（W指値）訂正指値が同値。");
            }
        }

        // validate発注条件単価(部店, double, double, 取引銘柄, boolean)
        this.validateOrderCondPrice(
            l_subAccount.getWeb3GenBranch(),
            l_dblLimitPrice,
            l_dblOrderCondPrice,
            l_equityTradedProduct,
            l_blnIsBuyToOpenOrder);

        // is空売り規制(補助口座, 取引銘柄, double, OrderTypeEnum,
        //       boolean, EqTypeExecutionConditionType, String, 注文単位)
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = null;
        if (l_lngOrderUnitId != 0)
        {
            l_eqtypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        }
        this.isShortSellingRestraint(
            l_subAccount,
            l_equityTradedProduct,
            l_dblQuantity,
            l_orderType,
            l_blnIsMarketPrice,
            l_wLimitExecCondType,
            l_strPriceConditionType,
            l_eqtypeOrderUnitRow);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate発注条件単価)<BR>
     * 発注条件が指定された注文について、<BR>
     * 発注条件単価のチェックを行う。  <BR>
     * <BR>
     * －発注条件単価乖離チェック<BR>
     * －発注条件単価入力チェック<BR>
     * ※計算処理は全てBigDecimal型で計算すること。<BR>
     * <BR>
     * １）　@発注条件単価チェック実施しない部店の場合、発注条件単価チェックを行わない。<BR>
     *　@１－１）　@部店プリファ@レンステーブルから、発注条件単価チェック実施区分を取得する。<BR>
     * <BR>
     *　@　@　@[条件]<BR>
     *　@　@　@　@部店ID = パラメータ.部店.部店ID <BR>
     *　@　@　@　@And　@プリファ@レンス名 = プリファ@レンス名.W指値注文・発注条件単価チェック実施区分<BR>
     *　@　@　@　@And　@プリファ@レンス名の連番 = 1（：株・信用）<BR>
     * <BR>
     *　@　@　@※上記条件でレコードが取得できなかった場合、"チェックしない"とする。<BR>
     * <BR>
     *　@１－２）発注条件単価チェック実施区分が"チェックしない"の場合リターンする。<BR>
     * <BR>
     * ２）　@発注条件単価乖離チェック<BR>
     * 　@パラメータ.指値の値と、パラメータ.発注条件単価との間に<BR>
     * 　@刻み値の整数倍以上の乖離があるかどうかチェックする。<BR>
     * 　@２－１）　@刻み値を取得する。  <BR>
     * 　@　@拡張プロダクトマネージャ.get刻み値()をコールする。<BR>
     * <BR>
     * 　@　@[get刻み値()に指定する引数]<BR>
     * 　@　@　@取引銘柄：　@パラメータ.取引銘柄 <BR>
     * 　@　@　@基準値：　@パラメータ.指値  <BR>
     *
     * 　@２－２）　@乖離値を取得する。<BR>
     * 　@　@乖離値 = (パラメータ.発注条件単価 - パラメータ.指値)の絶対値<BR>
     * <BR>
     * 　@２－３）　@乖離値が刻み値*倍数以上かどうかチェックする。<BR>
     * 　@　@乖離値が、以下の条件に該当しない場合、  <BR>
     * 　@　@「発注条件単価入力エラー（乖離値が指定の倍率未満）」の <BR>
     * 　@　@例外をスローする。  <BR>
     * 　@　@class:WEB3BusinessLayerException<BR>
     * 　@　@tag  :BUSINESS_ERROR_02492<BR>
     * <BR>
     * 　@　@　@乖離値 >= （刻み値 * 倍率(*1)）<BR>
     * <BR>
     * 　@　@(*1)倍率<BR>
     * 　@　@　@部店プリファ@レンステーブルを以下の条件で検索し、<BR>
     * 　@　@　@取得したレコード.プリファ@レンスの値を倍率とする。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@　@部店ID = パラメータ.部店.部店ID<BR>
     * 　@　@　@　@And　@プリファ@レンス名 = プリファ@レンス名.W指値注文・発注条件単価乖離倍率<BR>
     * 　@　@　@　@And　@プリファ@レンス名の連番 = 1（：株・信用）  <BR>
     * <BR>
     * 　@　@　@※検索結果が取得できなかった場合、倍率 = 1とする。<BR>
     * <BR>
     * ３）　@発注条件単価入力チェック<BR>
     * 　@パラメータ.指値の値と、パラメータ.発注条件単価とで <BR>
     * 　@時価を挟み込んでいるかどうかチェックする。  <BR>
     * 　@３－１）　@時価を取得する。  <BR>
     * 　@　@拡張プロダクトマネージャ.get時価()をコールする。 <BR>
     * <BR>
     * 　@　@[get時価()に指定する引数]  <BR>
     * 　@　@　@取引銘柄：　@パラメータ.取引銘柄  <BR>
     * <BR>
     * 　@３－２）　@パラメータ.指値の値と、パラメータ.発注条件単価とで<BR>
     * 　@　@時価を挟み込んでいるかどうかチェックする。  <BR>
     * 　@　@以下の条件を満たさない場合、  <BR>
     * 　@　@「発注条件単価／注文単価入力エラー（時価の挟み込み不正）」の <BR>
     * 　@　@例外をスローする。  <BR>
     * 　@　@class:WEB3BusinessLayerException<BR>
     * 　@　@tag  :BUSINESS_ERROR_02493<BR>
     * <BR>
     * 　@　@[買（パラメータ.is買注文 == true）の場合]<BR>
     * 　@　@　@パラメータ.指値 <= 取得した時価 < パラメータ.発注条件単価 <BR>
     * <BR>
     * 　@　@[売（上記以外）の場合]  <BR>
     * 　@　@　@パラメータ.指値 >= 取得した時価 > パラメータ.発注条件単価<BR>
     *
     * @@param l_branch - (部店)<BR>
     * 部店オブジェクト<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値<BR>
     * @@param l_dblOrderCondPrice - (発注条件単価)<BR>
     * 発注条件<BR>
     * @@param l_equityTradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト<BR>
     * @@param l_blnIsBuyOrder - (is買注文)<BR>
     * （isBuyToOpenOrder）<BR>
     * 買注文取引かどうかの判定。<BR>
     * 買注文の場合true、売注文の場合false。<BR>
     * @@throws WEB3BaseException
     */
    public void validateOrderCondPrice(
        WEB3GentradeBranch l_branch,
        double l_dblLimitPrice,
        double l_dblOrderCondPrice,
        WEB3EquityTradedProduct l_equityTradedProduct,
        boolean l_blnIsBuyOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateOrderCondPrice(WEB3GentradeBranch, double, double,"
            + " WEB3EquityTradedProduct, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_equityTradedProduct == null || l_branch == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //１）　@発注条件単価チェック実施しない部店の場合、発注条件単価チェックを行わない。
        //　@１－１）　@部店プリファ@レンステーブルから、発注条件単価チェック実施区分を取得する。
        //　@　@　@[条件]
        //　@　@　@　@部店ID = パラメータ.部店.部店ID
        //　@　@　@　@And　@プリファ@レンス名 = プリファ@レンス名.W指値注文・発注条件単価チェック実施区分
        //　@　@　@　@And　@プリファ@レンス名の連番 = 1（：株・信用）
        //
        //　@　@　@※上記条件でレコードが取得できなかった場合、"チェックしない"とする。
        BranchPreferencesRow l_brReferencesRow = null;
        try
        {
            l_brReferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                l_branch.getBranchId(),
                WEB3BranchPreferencesNameDef.TRIGGERORDER_WLIMITORDER_CHECK_ORDER_COND_PRICE,
                1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました:", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //　@１－２）発注条件単価チェック実施区分が"チェックしない"の場合リターンする。
        if (l_brReferencesRow == null
            || WEB3TriggerorderWlimitorderCheckOrderCondPriceDef.DEFAULT.equals(
                l_brReferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //１）　@発注条件単価乖離チェック
        //　@パラメータ.指値の値と、パラメータ.発注条件単価との間に
        //　@刻み値の整数倍以上の乖離があるかどうかチェックする。
        //　@１－１）　@刻み値を取得する。
        //　@　@拡張プロダクトマネージャ.get刻み値()をコールする。
        //　@　@[get刻み値()に指定する引数]
        //　@　@　@取引銘柄：　@パラメータ.取引銘柄
        //　@　@　@基準値：　@パラメータ.指値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productMgr =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();

        double l_dblTickValue =
            l_productMgr.getTickValue(l_equityTradedProduct, l_dblLimitPrice);

        //　@１－２）　@乖離値を取得する。
        //　@　@乖離値 = (パラメータ.発注条件単価 - パラメータ.指値)の絶対値
        BigDecimal l_bdOrderCondPrice = new BigDecimal(l_dblOrderCondPrice);
        BigDecimal l_bdLimitPrice = new BigDecimal(l_dblLimitPrice);

        //乖離値
        BigDecimal l_bdEstrangePrice =
            (l_bdOrderCondPrice.subtract(l_bdLimitPrice)).abs();

        //刻み値
        BigDecimal l_bdTickValue = new BigDecimal(l_dblTickValue);

        //　@　@(*1)倍率
        //　@　@　@部店プリファ@レンステーブルを以下の条件で検索し、
        //　@　@　@取得したレコード.プリファ@レンスの値を倍率とする。
        BranchPreferencesRow l_branchReferencesRow = null;
        try
        {
            l_branchReferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_branch.getBranchId(),
                    WEB3BranchPreferencesNameDef.TRIGGERORDER_WLIMITORDER_DIVERGENCERATE,
                    1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        BigDecimal l_bdRate = null;
        if (l_branchReferencesRow == null)
        {
            //※検索結果が取得できなかった場合、倍率 = 1とする。
            l_bdRate = new BigDecimal(1);
        }
        else
        {

            l_bdRate = new BigDecimal(l_branchReferencesRow.getValue());
        }

        //　@１－３）　@乖離値が刻み値*倍数以上かどうかチェックする。
        //　@　@乖離値が、以下の条件に該当しない場合、
        //　@　@「発注条件単価入力エラー（乖離値が指定の倍率未満）」の
        //　@　@例外をスローする。
        //
        //　@　@　@乖離値 >= （刻み値 * 倍率(*1)）
        if (l_bdEstrangePrice.compareTo(l_bdTickValue.multiply(l_bdRate)) < 0)
        {
            log.debug("発注条件単価入力エラー（乖離値が指定の倍率未満）。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02492,
                getClass().getName() + STR_METHOD_NAME,
                "発注条件単価入力エラー（乖離値が指定の倍率未満）。");
        }

        //２）　@発注条件単価入力チェック
        //　@パラメータ.指値の値と、パラメータ.発注条件単価とで
        //　@時価を挟み込んでいるかどうかチェックする。
        //　@２－１）　@時価を取得する。
        //　@　@拡張プロダクトマネージャ.get時価()をコールする。
        //　@　@[get時価()に指定する引数]
        //　@　@　@取引銘柄：　@パラメータ.取引銘柄
        double l_dblCurrentPrice = l_productMgr.getCurrentPrice(l_equityTradedProduct);
        BigDecimal l_bdCurrentPrice = new BigDecimal(l_dblCurrentPrice);

        //　@２－２）　@パラメータ.指値の値と、パラメータ.発注条件単価とで
        //　@　@時価を挟み込んでいるかどうかチェックする。
        //　@　@以下の条件を満たさない場合、
        //　@　@「発注条件単価／注文単価入力エラー（時価の挟み込み不正）」の
        //　@　@例外をスローする。
        //　@　@[買（パラメータ.is買注文 == true）の場合]
        //　@　@　@パラメータ.指値 <= 取得した時価 < パラメータ.発注条件単価
        if (l_blnIsBuyOrder)
        {
            if (!(l_bdLimitPrice.compareTo(l_bdCurrentPrice) <= 0
                && l_bdCurrentPrice.compareTo(l_bdOrderCondPrice) < 0))
            {
                log.debug("発注条件単価／注文単価入力エラー（時価の挟み込み不正）。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02493,
                    getClass().getName() + STR_METHOD_NAME,
                    "発注条件単価／注文単価入力エラー（時価の挟み込み不正）。");
            }
        }

        //　@　@[売（上記以外）の場合]
        //　@　@　@パラメータ.指値 >= 取得した時価 > パラメータ.発注条件単価
        else
        {
            if (!(l_bdLimitPrice.compareTo(l_bdCurrentPrice) >= 0
                && l_bdCurrentPrice.compareTo(l_bdOrderCondPrice) > 0))
            {
                log.debug("発注条件単価／注文単価入力エラー（時価の挟み込み不正）。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02493,
                    getClass().getName() + STR_METHOD_NAME,
                    "発注条件単価／注文単価入力エラー（時価の挟み込み不正）。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * <BR>
     * （validate新規建株数上限）<BR>
     * 市場銘柄別の建株数上限のチェックを行う。<BR>
     * <BR>
     * １）　@市場銘柄別建単元数の上限単元数を取得する。<BR>
     *  １－１）　@(部店市場上場区分別)取扱条件を生成する。<BR>
     *       [引数]<BR>
　@　@ *        部店ＩＤ　@：　@部店オブジェクト.部店ＩＤ　@(*1)<BR>
     *        市場ＩＤ　@：　@引数の取引銘柄.市場ＩＤ<BR>
     *        上場区分　@：　@引数の取引銘柄.上場区分<BR>
     *        新市場区分　@：　@引数の取引銘柄.新市場区分<BR>
     *        店頭公開区分　@：　@引数の取引銘柄.店頭公開区分<BR>
     * <BR>
     *        (*1)引数の補助口座.get取引店( )により部店オブジェクトを取得する。<BR>
     * <BR>
     * １－２）　@(部店市場上場区分別)取扱条件.get建単元数上限値()をコールする。<BR>
     * <BR>
     * １－３）　@以下に該当する場合、市場銘柄別のチェックをスキップする。<BR>
     *     １－１）でエラーがスローされた場合<BR>
     *     １－２）でget建単元数上限値の戻り値がnullの場合<BR>
     * <BR>
     * ２）　@市場銘柄別建株数上限チェックを行う。<BR>
     * <BR>
     * ２－１）　@該当顧客・市場・銘柄の全ての有効な建株オブジェクトを取得する。<BR>
     * ------------------------------------------------------------------<BR>
     * ＜建株オブジェクト取得＞<BR>
     *   株式ポジションマネージャ.get建株一覧(<BR>
     *   引数の補助口座, <BR>
     *   銘柄タイプ（株式（EQUITY））,<BR>
     *   検索条件文字列（*1）,<BR>
     *   検索条件データコンテナ（*2）)<BR>
     *   により取得する。<BR>
     * <BR>
     *  （*1）市場ID・銘柄ID・建株数をand条件で指定する。<BR>
     *   ※"market_id = ? and product_id = ? and quantity > ?"<BR>
     * <BR>
     *  （*2）市場IDの検索条件指定値として、引数の取引銘柄.市場IDを設定する。<BR>
     *   銘柄IDの検索条件指定値として、引数の取引銘柄.銘柄IDを設定する。<BR>
     *   建株数の検索条件指定値として、"0"を設定する。<BR>
     * ------------------------------------------------------------------<BR>
     * <BR>
     * 取得した建株オブジェクト全ての建株数を集計する。<BR>
     * <BR>
     * ２－２）　@該当顧客・市場・銘柄の新規建注文中（約定成立待ち）の注文単位オブジェクトを<BR>
     *           全て取得する。<BR>
     * ------------------------------------------------------------------<BR>
     * ＜注文単位オブジェクト取得＞<BR>
     * 拡張株式注文マネージャ.get注文単位一覧(<BR>
     *       引数の補助口座, <BR>
     *       銘柄タイプ（株式（EQUITY））,<BR>
     *       検索条件文字列（*1）,<BR>
     *       検索条件データコンテナ（*2）,<BR>
     *       null)<BR>
     *       により取得する。<BR>
     * <BR>
     *（*1）市場ID・銘柄ID・注文カテゴリ・注文有効状態をand条件で指定する。<BR>
     * ※"market_id = ? and product_id = ? and order_categ = ? and order_open_status = ?"<BR>
     * <BR>
     * 引数の注文単位オブジェクト≠null（＝注文の訂正）の場合は、<BR>
     * 注文単位IDのnot指定を追加指定し、訂正対象注文を除外する。<BR>
     * ※" and order_unit_id != ?"
     * <BR>
     *（*2）市場ID：　@引数の取引銘柄.市場IDを設定<BR>
     * 銘柄ID：　@引数の取引銘柄.銘柄IDを設定<BR>
     * 注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）のintValue<BR>
     * 注文有効状態：　@OrderOpenStatusEnum.”オープン”（OPEN）のintValue<BR>
     * <BR>
     * 引数の注文単位オブジェクト≠null（＝注文の訂正）の場合のみ、以下も設定。<BR>
     * 注文単位ID：　@引数の注文単位.注文単位ID<BR>
     * ------------------------------------------------------------------<BR>
     * <BR>
     * 取得した注文単位オブジェクト全ての、以下の計算結果を集計する。<BR>
     * --------------------------------<BR>
     * 各注文単位オブジェクトより、以下の通りに値を取得する。<BR>
     * <BR>
     * ・注文単位.市場から確認済みの数量＝nullの場合<BR>
     *       注文単位.注文数量を取得する。<BR>
     * <BR>
     * ・注文単位.市場から確認済みの数量≠nullの場合<BR>
     *       （注文単位.市場から確認済みの数量 － 注文単位.約定数量）を計算する。<BR>
     * <BR>
     * --------------------------------<BR>
     * <BR>
     * ２－３）　@（　@引数の注文数量(*) ＋ ２－１）で取得した建株数 ＋ ２－３）で取得した建株数　@） > <BR>
     * （　@１－２）で取得した建単元数上限値　@×　@引数の取引銘柄.売買単位　@）の場合は、<BR>
     * 「建株数の上限値超過(市場銘柄別)」の例外をthrowする。<BR>
     * <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:   BUSINESS_ERROR_02871<BR>
     * (*)引数の注文数量：<BR>
     * 引数の注文単位オブジェクト≠null（＝注文の訂正）の場合は、<BR>
     * 引数の注文単位.約定数量を控除した結果を使用する。<BR>
     * (*)引数の注文数量：<BR>
     * 引数の注文単位オブジェクト≠null（＝注文の訂正）の場合は、<BR>
     * 引数の注文単位.約定数量を控除した結果を使用する。<BR>
     * <BR>
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@補助口座オブジェクト（信用取引口座）。<BR>
     * @@param l_dblQuantity 注文数量。<BR>
     * @@param l_orderType 注文種別。（xTradeのOrderTypeEnumにて定義）<BR>
     * 　@　@　@（現物買注文／新規買建注文／現引注文）<BR>
     * @@param l_tradedProduct 取引銘柄。<BR>
     * @@param l_changeOrderUnit 訂正対象注文単位オブジェクト<BR>
     * @@throws WEB3BaseException<BR>
     */
    public void validateMaxOpenMarginQuantity(
        WEB3GentradeSubAccount l_subAccount, 
        double l_dblQuantity,
        OrderTypeEnum l_orderType,
        WEB3EquityTradedProduct l_tradedProduct,
        EqtypeOrderUnitRow l_changeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMaxOpenMarginQuantity(l_subAccount,l_dblQuantity,l_orderType,l_tradedProduct,l_changeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //--------------------------------------------------------------------------------
        // エンティティ／エンティティ項目値の取得
        //--------------------------------------------------------------------------------
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        Product l_product = l_tradedProduct.getProduct();
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
            
        long l_lngBranchId = l_branch.getBranchId();
        long l_lngMarketId = l_tradedProductRow.getMarketId();
        String l_strListType = l_tradedProductRow.getListType();
        String l_strNewListType = l_tradedProductRow.getNewListType();
        String l_strOpenOtcDiv = l_tradedProductRow.getOpenOtcDiv();

        long l_lngProductId = l_product.getProductId();
        double l_dblLotSize = l_tradedProductRow.getLotSize();

        //--------------------------------------------------------------------------------
        // 銘柄・市場別の建単元数上限値を取得する
        //--------------------------------------------------------------------------------
        Double l_dblMaxOpenMarginUnitsByMarketProduct = null;
        try
        {
            WEB3GentradeBranchListmarketDealtCond l_branchListmarketDealtCond
                = new WEB3GentradeBranchListmarketDealtCond(
                    l_lngBranchId, l_lngMarketId, l_strListType, l_strNewListType, l_strOpenOtcDiv);
            l_dblMaxOpenMarginUnitsByMarketProduct = l_branchListmarketDealtCond.getMaxContUnit(l_mainAccount);
        }
        catch (WEB3SystemLayerException l_e) {}
        
        if (l_dblMaxOpenMarginUnitsByMarketProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //--------------------------------------------------------------------------------
        // 集計対象建株を取得する
        //--------------------------------------------------------------------------------
        String l_strWhereForGetContract = " and market_id = ? and product_id = ? and quantity > ?";
        String[] l_containerListForGetContract = {
            String.valueOf(l_lngMarketId), String.valueOf(l_lngProductId), "0"};
            
        List l_contractListByMarketProduct = l_positionManager.getContracts(
            l_subAccount, ProductTypeEnum.EQUITY, l_strWhereForGetContract, l_containerListForGetContract);

        //--------------------------------------------------------------------------------
        // 建株から建株数を集計する
        //--------------------------------------------------------------------------------
        double l_dblTotalContractQuantityByMarketProduct = 0D;
        if (l_contractListByMarketProduct != null)
        {
            for (int i = 0; i < l_contractListByMarketProduct.size(); i++)
            {
                EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contractListByMarketProduct.get(i);
                l_dblTotalContractQuantityByMarketProduct += l_contractRow.getQuantity();
            }
        }
        
        //--------------------------------------------------------------------------------
        // 集計対象注文を取得する
        //--------------------------------------------------------------------------------
        String l_strWhereForGetOrder =
            "market_id = ? and product_id = ? and order_categ = ? and order_open_status = ?";
        String[] l_containerListForGetOrder = null;
        
        List l_list = new ArrayList();
        l_list.add(String.valueOf(l_lngMarketId));
        l_list.add(String.valueOf(l_lngProductId));
        l_list.add(String.valueOf(OrderCategEnum.OPEN_MARGIN.intValue()));
        l_list.add(String.valueOf(OrderOpenStatusEnum.OPEN.intValue()));
        if (l_changeOrderUnit != null)
        {
            l_strWhereForGetOrder += " and order_unit_id <> ?";
            l_list.add(Long.toString(l_changeOrderUnit.getOrderUnitId()));
        }
        l_containerListForGetOrder = new String[l_list.size()];
        l_list.toArray(l_containerListForGetOrder);

        List l_orderListByMarketProduct = l_orderManager.getOrderUnits(
            l_subAccount, ProductTypeEnum.EQUITY, l_strWhereForGetOrder, l_containerListForGetOrder, null);
        
        //--------------------------------------------------------------------------------
        // 注文から新規建注文数量を集計する
        //--------------------------------------------------------------------------------
        double l_dblTotalOrderQuantityByMarketProduct = 0D;
        if (l_orderListByMarketProduct != null)
        {
            for (int i = 0; i < l_orderListByMarketProduct.size(); i++)
            {
                OrderUnit l_orderUnit = (OrderUnit) l_orderListByMarketProduct.get(i);
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
                if (l_orderUnitRow.getConfirmedQuantityIsNull())
                {
                    l_dblTotalOrderQuantityByMarketProduct += l_orderUnitRow.getQuantity();
                }
                else
                {
                    l_dblTotalOrderQuantityByMarketProduct +=
                        l_orderUnitRow.getConfirmedQuantity() - l_orderUnitRow.getExecutedQuantity();
                }
                log.debug("l_dblTotalOrderQuantityByMarketProduct=" + l_dblTotalOrderQuantityByMarketProduct);
            }
        }

        //--------------------------------------------------------------------------------
        // 今回注文の未約定数量と建株数と新規建注文数量を合計し、市場銘柄別の建株数を求める
        //--------------------------------------------------------------------------------
        double l_dblUnExecutedQuantity = 0D;
        if (l_changeOrderUnit == null)
        {
            l_dblUnExecutedQuantity = l_dblQuantity;
        }
        else{
            l_dblUnExecutedQuantity = l_dblQuantity - l_changeOrderUnit.getExecutedQuantity();
        }
        
        double l_totalOpenMarginQuantityByMarketProduct = l_dblUnExecutedQuantity +
            l_dblTotalContractQuantityByMarketProduct + l_dblTotalOrderQuantityByMarketProduct;
        log.debug("l_totalOpenMarginQuantityByMarketProduct=" + l_totalOpenMarginQuantityByMarketProduct);
        
        //--------------------------------------------------------------------------------
        // 市場銘柄別の建株数上限値を算出する
        //--------------------------------------------------------------------------------
        double l_dblMaxOpenMarginQuantityByMarketProduct =
            l_dblMaxOpenMarginUnitsByMarketProduct.doubleValue() * l_dblLotSize;
        log.debug("l_dblMaxOpenMarginQuantityByMarketProduct=" + l_dblMaxOpenMarginQuantityByMarketProduct);
        
        //--------------------------------------------------------------------------------
        // 市場銘柄別の建株数　@＞　@市場銘柄別の建株数上限値の場合エラーとする
        //--------------------------------------------------------------------------------
        if (l_totalOpenMarginQuantityByMarketProduct > l_dblMaxOpenMarginQuantityByMarketProduct)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02871, STR_METHOD_NAME);
        } 
        log.debug("建株数（市場銘柄単位）l_dblUnExecutedQuantity + l_dblTotalContractQuantityByMarketProduct + l_dblTotalOrderQuantityByMarketProduct="
            + (l_dblUnExecutedQuantity +
            l_dblTotalContractQuantityByMarketProduct +
            l_dblTotalOrderQuantityByMarketProduct));
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate決済期日超過)<BR>
     * 引数の建株の決済期日超過をチェックする。<BR>
     * <BR>
     * １）　@決済期日超過のチェック<BR>
     * 　@引数.建株.期日 ＜ 発注日(*)の場合、<BR>
     * 　@「発注日が決済期日を超過」の例外をthrowする。<BR>
     * 　@class:WEB3BusinessLayerException<BR>
     * 　@tag　@:BUSINESS_ERROR_00748<BR>
     * <BR>
     * 　@(*)取引時間管理.発注日()<BR>
     * <BR>
     * @@param l_equityContract - (建株)<BR>
     * 建株
     * @@throws WEB3BaseException
     */
    public void validateCloseDateExcess(WEB3EquityContract l_equityContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateCloseDateExcess(WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);

        // 取引時間管理.発注日()
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        // 引数.建株.期日
        Date l_datCloseDate = l_equityContract.getCloseDate();

        // 引数.建株.期日 ＜ 発注日(*)の場合
        if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datCloseDate) > 0)
        {
            log.debug("発注日が決済期日を超過");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00748,
                this.getClass().getName() + STR_METHOD_NAME,
                "該当建株の発注日が決済期日を超えています。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate手動強制決済可能)<BR>
     * 手動強制決済注文の可/不可を判定する。<BR>
     * 手動強制決済は扱者のみ登録可能。<BR>
     * <BR>
     * １）　@処理対象の判定<BR>
     * 　@引数.手動強制決済フラグ == falseの場合、処理を終了する。<BR>
     * 　@　@※手動強制決済注文以外はチェックしない<BR>
     * <BR>
     * ２）　@手動強制決済可/不可のチェック<BR>
     * 　@２－１）　@扱者のチェック<BR>
     * 　@　@引数.扱者 == nullの場合、<BR>
     * 　@　@「扱者以外は手動強制決済不可」の例外をthrowする。<BR>
     * 　@　@class:WEB3BusinessLayerException<BR>
     * 　@　@tag　@:BUSINESS_ERROR_02809<BR>
     * <BR>
     * @@param l_blnManualForcedSettleFlag - (手動強制決済フラグ)<BR>
     * 手動強制決済フラグ
     * @@param l_trader - (扱者)<BR>
     * 扱者
     * @@throws WEB3BaseException
     */
    public void validateManualForcedSettlePossible(boolean l_blnManualForcedSettleFlag, Trader l_trader)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateManualForcedSettlePossible(boolean, Trader)";
        log.entering(STR_METHOD_NAME);

        // 引数.手動強制決済フラグ == falseの場合、処理を終了する。
        if (!l_blnManualForcedSettleFlag)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        // 　@２－１）　@扱者のチェック
        // 引数.扱者 == nullの場合、
        if (l_trader == null)
        {
            // 「扱者以外は手動強制決済不可」の例外をthrowする。
            log.debug("扱者以外は手動強制決済不可");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02809,
                this.getClass().getName() + STR_METHOD_NAME,
                "扱者以外は手動強制決済不可");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate機@構預託同意)<BR>
     * 機@構預託に同意している顧客のみ取引可能とするチェックを行う。<BR>
     * <BR>
     * １）　@保振（機@構預託）同意チェック<BR>
     * 　@引数.補助口座から顧客を取得する。<BR>
     * <BR>
     * 　@取得した顧客.is保振（機@構預託）同意() ＝ falseの場合、<BR>
     * 　@「機@構預託に同意されていないため、取引できません」の例外をthrowする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  :　@BUSINESS_ERROR_02964<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@throws WEB3BaseException
     */
    public void validateMechanismDepositAgree(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMechanismDepositAgree(SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //引数.補助口座から顧客を取得する。
        WEB3GentradeMainAccount l_gentradeMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //取得した顧客.is保振（機@構預託）同意() ＝ falseの場合
        //「機@構預託に同意されていないため、取引できません」の例外をthrowする。
        if (!l_gentradeMainAccount.isOrgDepositAgree())
        {
            log.debug("機@構預託に同意されていないため、取引できません");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02964,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "機@構預託に同意されていないため、取引できません");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
