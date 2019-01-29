head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderManagerPersistenceEventInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文インタセプタ(WEB3EquityOrderManagerPersistenceEventInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/04 石炎 (中訊) 修正
Revesion History : 2004/09/27 法@旭 (中訊) 修正
Revesion History : 2004/11/30 SRA水落　@　@ 修正
Revesion History : 2005/01/05 岡村和明(SRA) JavaDoc修正
Revesion History : 2006/07/19 肖志偉 (中訊) ＤＢ更新仕様156
Revesion History : 2006/08/01 李俊 (中訊) ＤＢ更新仕様163
Revesion History : 2006/11/02 柴双紅 (中訊) ＤＢ更新仕様No.168,No.174、No.180
Revesion History : 2007/04/26 謝旋 (中訊) ＤＢ更新仕様No.198
*/
package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ForcedExpireType;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.equity.define.WEB3EquityVoucherNoDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文インタセプタ）。<BR>
 * <BR>
 * 株式注文、株式注文単位、株式注文履歴テーブル<BR>
 * のカスタマイズ項目に値をセットする。<BR>
 *（EqTypeOrderManagerPersistenceEventInterceptorの実装）
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityOrderManagerPersistenceEventInterceptor
    implements EqTypeOrderManagerPersistenceEventInterceptor
{

    /**
     * (株式注文内容) <BR>
     * 株式注文内容オブジェクト <BR>
     */
    private WEB3EquityNewCashBasedOrderSpec equityNewCashBasedOrderSpec;

    /**
     * (繰越元注文単位)
     */
    private EqTypeOrderUnit l_eqtypeOrderUnit;
    
    /**
     * (注文経路区分)<BR>
     * 注文経路区分。
     */
    private String orderRootDiv;
    
    /**
     * (受注日時)<BR>
     * 受注日時。
     */
    private Date receivedDateTime;
    
    /**
     * (株式注文インタセプタ) <BR>
     * コンストラクタ。 <BR>
     * @@roseuid 4010C4A700BC
     */
    public WEB3EquityOrderManagerPersistenceEventInterceptor()
    {
    }

    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderManagerPersistenceEventInterceptor.class);

    /**
     * (更新値設定) <BR>
     * （mutateメソッドの実装） <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。 <BR>
     * <BR>
     * １） 注文内容判定 <BR>
     * 　@this.株式注文内容プロパティがnullの場合は <BR>
     *   パラメータ.注文単位Paramsを返却し処理を終了する。 <BR>
     * <BR>
     * ２） 拡張項目セット <BR>
     * 　@this.株式注文内容プロパティから、 <BR>
     *   パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。 <BR>
     * 更新内容は、 <BR>
     * 「現物株式買付_株式注文単位テーブル.xls」、 <BR>
     * 「現物株式売付_株式注文単位テーブル.xls」
     * 「（注文繰越[繰越]）注文単位テーブル」、参照。 <BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ) <BR>
     * INSERTまたは、UPDATE。 <BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。 <BR>
     * 
     * @@param l_orderManagerPersistenceContext - (処理) <BR>
     * （OrderManagerPersistenceContextにて定数定義） <BR>
     * @@param l_eqtypeOrderUnitParams - (注文単位Params) <BR>
     * 株式注文単位が保持する項目のパラメータクラス。 <BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 4010C4A700B0
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType,OrderManagerPersistenceContext,EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        if(l_eqtypeOrderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 約定時は処理を行わない
        if (this.equityNewCashBasedOrderSpec == null)
        {
            return l_eqtypeOrderUnitParams;
        }

        // 株式注文内容
        WEB3EquityNewCashBasedOrderSpec l_orderSpec =
            this.getEquityOrderSpec();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // 処理日時取得
        Timestamp l_processTime =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        try
        {
            //買付、売付の場合
            if ((l_orderSpec.getFirstOrderUnitId() == null)
              ||(l_orderSpec.getFirstOrderUnitId().longValue() <= 0))
            {

                // 部店オブジェクト取得             
                AccountManager l_accMgr = l_finApp.getAccountManager();
                Branch l_branch =
                    l_accMgr.getBranch(l_eqtypeOrderUnitParams.branch_id);

                //識別コード（MMDDXXXXX）採番
                WEB3HostReqOrderNumberManageService l_numberService =
                    (WEB3HostReqOrderNumberManageService) Services.getService(
                        WEB3HostReqOrderNumberManageService.class);
                String l_newOrderRequestNumber =
                    l_numberService.getNewNumber(
                        l_orderSpec.getInstitutionCode(),
                        l_branch.getBranchCode(),
                        ProductTypeEnum.EQUITY);

                //譲渡益金額
                double l_dblCapitalGain = 0;
                l_eqtypeOrderUnitParams.setCapitalGain(l_dblCapitalGain);
                
                //譲渡益税額
                double l_dblCapitalGainTax = 0;
                l_eqtypeOrderUnitParams.setCapitalGainTax(l_dblCapitalGainTax);
                
                //値段条件
                l_eqtypeOrderUnitParams.setPriceConditionType(l_orderSpec.getPriceConditionType());

                //（W指値）執行条件
                //株式注文内容.get（W指値）執行条件
                //ただし、発注条件＝（0：DEFAULT（条件指定なし）、1：逆指値）の場合は、null。
                if (WEB3OrderingConditionDef.DEFAULT.equals(l_eqtypeOrderUnitParams.getOrderConditionType())
                    || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_eqtypeOrderUnitParams.getOrderConditionType()))
                {
                    l_eqtypeOrderUnitParams.setWLimitExecCondType(null);
                }
                else
                {
                    l_eqtypeOrderUnitParams.setWLimitExecCondType(l_orderSpec.getWlimitExecCondType());
                }

                //発注条件
                l_eqtypeOrderUnitParams.setOrderConditionType(
                    l_orderSpec.getOrderCond());                    
                
                //（発注条件＝"DEFAULT（条件指定なし）"の場合はnullセット
                if (l_orderSpec.getOrderCond().equals(WEB3OrderingConditionDef.DEFAULT))
                {                    
                    l_eqtypeOrderUnitParams.setOrderCondOperator(null); // 発注条件演算子                    
                    l_eqtypeOrderUnitParams.setStopOrderPrice(null);    // 逆指値基準値                    
                    l_eqtypeOrderUnitParams.setWLimitPrice(null);       // （W指値）訂正指値
                }
                else
                {
                    // 発注条件演算子
                    l_eqtypeOrderUnitParams.setOrderCondOperator(
                        l_orderSpec.getOrderCondOperator());
                    //逆指値基準値
                    l_eqtypeOrderUnitParams.setStopOrderPrice(
                        l_orderSpec.getStopLimitPriceBasePrice());
                    // （W指値）訂正指値    （（0：DEFAULT、1：逆指値）の場合はnullセット）
                    if (l_orderSpec.getOrderCond().equals(WEB3OrderingConditionDef.STOP_LIMIT_PRICE))
                    {
                        l_eqtypeOrderUnitParams.setWLimitPrice(null);
                    }
                    else
                    {
                        l_eqtypeOrderUnitParams.setWLimitPrice(
                            l_orderSpec.getWLimitPriceChange());                        
                    }
                } 

                // 税区分（現引現渡）
                l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);
                
                //弁済区分
                l_eqtypeOrderUnitParams.setRepaymentType(null);
                
                //弁済期限値
                l_eqtypeOrderUnitParams.setRepaymentNum(null);
                
                //弁済区分（SONAR）
                l_eqtypeOrderUnitParams.setSonarRepaymentType(null);
                
                //初回注文の注文チャネル
                l_eqtypeOrderUnitParams.setOrderChanel(
                    l_orderSpec.getOrderChannel());

                //受注日時
                if (this.receivedDateTime != null)
                {
                    l_eqtypeOrderUnitParams.setReceivedDateTime(this.receivedDateTime);
                }
                else
                {
                    l_eqtypeOrderUnitParams.setReceivedDateTime(l_processTime);
                }

                //伝票No ("9" + 識別コードの下３桁)
                String l_strNewOrderRequestNumber_sub =
                    l_newOrderRequestNumber.substring(
                        l_newOrderRequestNumber.length() - 3,
                        l_newOrderRequestNumber.length());
                String l_strVoucherNo =
                    WEB3EquityVoucherNoDef.VoucherNo
                        + l_strNewOrderRequestNumber_sub;
                l_eqtypeOrderUnitParams.setVoucherNo(l_strVoucherNo);

                //初回注文の手数料No
                l_eqtypeOrderUnitParams.setCommTblNo(
                    l_orderSpec.getCommission().getCommissionNo());

                //初回注文の手数料No枝番
                l_eqtypeOrderUnitParams.setCommTblSubNo(
                    l_orderSpec.getCommission().getCommissionRevNo());

                //扱者コード（SONAR）
                MainAccount l_mainAccount =
                    l_accMgr.getMainAccount(l_eqtypeOrderUnitParams.account_id);
                MainAccountRow l_accRow =
                    (MainAccountParams) l_mainAccount.getDataSourceObject();
                String l_strSonarTraderCode = l_accRow.getSonarTraderCode();
                l_eqtypeOrderUnitParams.setSonarTraderCode(
                    l_strSonarTraderCode);

                // 注文単価
                l_eqtypeOrderUnitParams.setPrice(
                    l_orderSpec.getOrderUnitPrice());

                // 識別コード
                l_eqtypeOrderUnitParams.setOrderRequestNumber(
                    l_newOrderRequestNumber);

                // 概算受渡代金
                l_eqtypeOrderUnitParams.setEstimatedPrice(
                    l_orderSpec.getEstimateDeliveryAmount());

                //譲渡益金額
                l_eqtypeOrderUnitParams.setCapitalGain(0D);
                
                //譲渡益税額
                l_eqtypeOrderUnitParams.setCapitalGainTax(0D);
                
                // 取引コード（SONAR）
                WEB3GentradeCommission l_comm =
                    equityNewCashBasedOrderSpec.getCommission();
                l_eqtypeOrderUnitParams.setSonarTradedCode(
                    l_comm.getSonarTradedCode());

                // 市場コード（SONAR）
                FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
                Market l_market =
                    l_finObjMgr.getMarket(l_eqtypeOrderUnitParams.market_id.longValue());
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                l_eqtypeOrderUnitParams.setSonarMarketCode(
                    l_marketRow.getSonarMarketCode());

                // 手数料商品コード(10：上場株式 or 11：店頭株式)
                l_eqtypeOrderUnitParams.setCommProductCode(
                    l_orderSpec.getCommissionProductCode());

                //空売フラグ
                l_eqtypeOrderUnitParams.setShortSellOrderFlag(
                    WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);
                
                //注文訂正・取消区分
                l_eqtypeOrderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.INITIAL_VALUE);

                //注文経路区分
                String l_strOrderRootDiv = null;
                try
                {
                    OpLoginSecurityService l_securityService =
                        (OpLoginSecurityService)Services.getService(
                            OpLoginSecurityService.class);
                    l_strOrderRootDiv =
                        l_securityService.getSessionProperty(
                            WEB3SessionAttributeDef.ORDER_ROOT_DIV);
                }
                catch (Exception l_exp)
                {
                }
                if (l_strOrderRootDiv != null)
                {
                    l_eqtypeOrderUnitParams.setOrderRootDiv(l_strOrderRootDiv);
                }
                else
                {
                    l_eqtypeOrderUnitParams.setOrderRootDiv(this.orderRootDiv);
                }

                //発注経路区分
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingMod.getProductManager();
                String l_strSubmitOrderRouteDiv = null;
                try
                {
                    EqTypeTradedProduct l_tradedProduct =
                        (EqTypeTradedProduct)l_productManager.getTradedProduct(
                            l_eqtypeOrderUnitParams.getProductId(),
                            l_eqtypeOrderUnitParams.getMarketId());
                    l_strSubmitOrderRouteDiv =
                        l_frontOrderService.getSubmitOrderRouteDiv(
                            l_tradedProduct,
                            l_branch.getInstitution().getInstitutionCode(),
                            l_eqtypeOrderUnitParams.getSonarTradedCode());
                }
                catch (NotFoundException l_nfe)
                {
                    log.error(l_nfe.getMessage(), l_nfe);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_nfe.getMessage(), l_nfe);
                }
                catch (WEB3BaseException l_wbe)
                {
                    log.error(l_wbe.getMessage(), l_wbe);
                    throw new WEB3BaseRuntimeException(
                        l_wbe.getErrorInfo(), 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_wbe.getMessage(), l_wbe);
                }
                l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
                
                //市場から確認済みの注文単価
                l_eqtypeOrderUnitParams.setConfirmedOrderPrice(null);

                //市場から確認済みの概算受渡代金
                l_eqtypeOrderUnitParams.setConfirmedEstimatedPrice(null);

                //市場から確認済みの執行条件
                l_eqtypeOrderUnitParams.setConfirmedExecConditionType(null);
                
                //市場から確認済みの値段条件
                l_eqtypeOrderUnitParams.setConfirmedPriceConditionType(null);
                
                //決済順序区分
                l_eqtypeOrderUnitParams.setClosingOrderType(null);

                //注文エラー理由コード
                l_eqtypeOrderUnitParams.setErrorReasonCode(
                    WEB3ErrorReasonCodeDef.NORMAL);

                //リクエストタイプ
                //株式注文内容.get発注条件( )＝"逆指値"または"W指値"の場合：　@0：DEFAULT
                //上記以外の場合：null
                if ((WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderSpec.getOrderCond()))
                    || (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderSpec.getOrderCond())))
                {
                    l_eqtypeOrderUnitParams.setRequestType(
                        WEB3RequestTypeDef.DEFAULT);
                }
                else
                {
                    l_eqtypeOrderUnitParams.setRequestType(null);
                }

                //注文Rev.
                l_eqtypeOrderUnitParams.setOrderRev("00");
                
                //市場から確認済みの注文Rev.
                l_eqtypeOrderUnitParams.setConfirmedOrderRev("00");
                
                //初回注文の注文単位ＩＤ
                l_eqtypeOrderUnitParams.setFirstOrderUnitId(
                    l_orderSpec.getFirstOrderUnitId());

                //発注遅延フラグ
                //0：遅延なし（0：DEFAULT）
                l_eqtypeOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.FALSE);
                
                //強制決済理由区分
                l_eqtypeOrderUnitParams.setForcedSettleReasonType(null);
                
                //承認状態区分
                l_eqtypeOrderUnitParams.setApproveStatusType(null);
                
                //承認者コード
                l_eqtypeOrderUnitParams.setApproverCode(null);
                
                //承認／非承認日時
                l_eqtypeOrderUnitParams.setApproveTimestamp(null);
                
                //保証金維持率
                l_eqtypeOrderUnitParams.setMarginMaintenanceRate(null);
                
                //追証発生日
                l_eqtypeOrderUnitParams.setAdditionalMarginDate(null);
                
                //追証経過日数
                l_eqtypeOrderUnitParams.setAdditionalMarginAccruedDays(null);
                
                //強制失効区分
                //0：オープン（0：DEFAULT）
                l_eqtypeOrderUnitParams.setForcedExpireType(WEB3ForcedExpireType.OPENING);
            }
            else
            {
                //注文繰越の場合

                //get初回注文の注文単位ＩＤ
                long l_lngCarryoverOrderUnitId =
                    l_orderSpec.getFirstOrderUnitId().longValue();
                //get繰越元注文単位
                TradingModule l_tradingMod =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                EqtypeOrderUnitRow l_carryoverOrderUnitRow =
                    (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();

                //部店オブジェクト取得             
                AccountManager l_accMgr = l_finApp.getAccountManager();
                Branch l_branch =
                    l_accMgr.getBranch(l_carryoverOrderUnitRow.getBranchId());

                //識別コード（MMDDXXXXX）採番
                WEB3HostReqOrderNumberManageService l_numberService =
                    (WEB3HostReqOrderNumberManageService) Services.getService(
                        WEB3HostReqOrderNumberManageService.class);
                String l_newOrderRequestNumber =
                    l_numberService.getNewNumber(
                        l_branch.getInstitution().getInstitutionCode(),
                        l_branch.getBranchCode(),
                        ProductTypeEnum.EQUITY);
                
                //取引者ＩＤ
                if (!l_carryoverOrderUnitRow.getTraderIdIsNull())
                {
                    l_eqtypeOrderUnitParams.setTraderId(l_carryoverOrderUnitRow.getTraderId());
                }
                
                //値段条件
                l_eqtypeOrderUnitParams.setPriceConditionType(
                    l_orderSpec.getPriceConditionType());
                
                //発注条件
                l_eqtypeOrderUnitParams.setOrderConditionType(
                    l_carryoverOrderUnitRow.getOrderConditionType());
                //発注条件演算子
                l_eqtypeOrderUnitParams.setOrderCondOperator(
                    l_carryoverOrderUnitRow.getOrderCondOperator());
                //逆指値基準値
	            if (l_carryoverOrderUnitRow.getStopOrderPriceIsNull())
	            {
	                l_eqtypeOrderUnitParams.setStopOrderPrice(null);
	            }
	            else
	            {
	                l_eqtypeOrderUnitParams.setStopOrderPrice(
                        l_carryoverOrderUnitRow.getStopOrderPrice());
	            }
                //（W指値）訂正指値
	            if (l_carryoverOrderUnitRow.getWLimitPriceIsNull())
	            {
	                l_eqtypeOrderUnitParams.setWLimitPrice(null);
	            }
	            else
	            {
	                l_eqtypeOrderUnitParams.setWLimitPrice(
	                    l_carryoverOrderUnitRow.getWLimitPrice());
	            }

                //税区分（現引現渡）
                l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);
                
                //弁済区分
                l_eqtypeOrderUnitParams.setRepaymentType(null);
                
                //弁済期限値
                l_eqtypeOrderUnitParams.setRepaymentNum(null);
                
                //弁済区分（SONAR）
                l_eqtypeOrderUnitParams.setSonarRepaymentType(null);
                
                //初回注文の注文チャネル
                l_eqtypeOrderUnitParams.setOrderChanel(
                    l_orderSpec.getOrderChannel());

                //受注日時
                l_eqtypeOrderUnitParams.setReceivedDateTime(
                    l_carryoverOrderUnitRow.getReceivedDateTime());

                //伝票No
                String l_strNewOrderRequestNumber_sub =
                    l_newOrderRequestNumber.substring(
                        l_newOrderRequestNumber.length() - 3,
                        l_newOrderRequestNumber.length());
                String l_strVoucherNo =
                    WEB3EquityVoucherNoDef.VoucherNo
                        + l_strNewOrderRequestNumber_sub;
                l_eqtypeOrderUnitParams.setVoucherNo(l_strVoucherNo);

                //初回注文の手数料No
                l_eqtypeOrderUnitParams.setCommTblNo(
                    l_orderSpec.getCommission().getCommissionNo());

                //初回注文の手数料No枝番
                l_eqtypeOrderUnitParams.setCommTblSubNo(
                    l_orderSpec.getCommission().getCommissionRevNo());

                //扱者コード（SONAR）
                MainAccount l_mainAccount =
                    l_accMgr.getMainAccount(l_eqtypeOrderUnitParams.account_id);
                MainAccountRow l_accRow =
                    (MainAccountParams) l_mainAccount.getDataSourceObject();
                String l_strSonarTraderCode = l_accRow.getSonarTraderCode();
                l_eqtypeOrderUnitParams.setSonarTraderCode(
                    l_strSonarTraderCode);

                //注文単価
                l_eqtypeOrderUnitParams.setPrice(
                    l_orderSpec.getOrderUnitPrice());

                //識別コード
                l_eqtypeOrderUnitParams.setOrderRequestNumber(
                    l_newOrderRequestNumber);

                //概算受渡代金
                l_eqtypeOrderUnitParams.setEstimatedPrice(
                    l_orderSpec.getEstimateDeliveryAmount());
                
                //譲渡益金額
                l_eqtypeOrderUnitParams.setCapitalGain(0D);
                
                //譲渡益税額
                l_eqtypeOrderUnitParams.setCapitalGainTax(0D);
                
                // 取引コード（SONAR）
                WEB3GentradeCommission l_comm =
                    equityNewCashBasedOrderSpec.getCommission();
                l_eqtypeOrderUnitParams.setSonarTradedCode(
                    l_comm.getSonarTradedCode());

                // 市場コード（SONAR）
                FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
                Market l_market =
                    l_finObjMgr.getMarket(l_eqtypeOrderUnitParams.market_id.longValue());
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                l_eqtypeOrderUnitParams.setSonarMarketCode(
                    l_marketRow.getSonarMarketCode());

                // 手数料商品コード
                l_eqtypeOrderUnitParams.setCommProductCode(
                    l_orderSpec.getCommissionProductCode());
                
                //空売フラグ
                l_eqtypeOrderUnitParams.setShortSellOrderFlag(
                    WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);
                
                //注文訂正・取消区分
                l_eqtypeOrderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.INITIAL_VALUE);

                //注文経路区分
                l_eqtypeOrderUnitParams.setOrderRootDiv(
                    l_carryoverOrderUnitRow.getOrderRootDiv());

                //発注経路区分
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingMod.getProductManager();
                String l_strSubmitOrderRouteDiv = null;
                try
                {
                    EqTypeTradedProduct l_tradedProduct =
                        (EqTypeTradedProduct)l_productManager.getTradedProduct(
                            l_eqtypeOrderUnitParams.getProductId(),
                            l_eqtypeOrderUnitParams.getMarketId());
                    l_strSubmitOrderRouteDiv =
                        l_frontOrderService.getSubmitOrderRouteDiv(
                            l_tradedProduct,
                            l_branch.getInstitution().getInstitutionCode(),
                            l_eqtypeOrderUnitParams.getSonarTradedCode());
                }
                catch (NotFoundException l_nfe)
                {
                    log.error(l_nfe.getMessage(), l_nfe);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_nfe.getMessage(), l_nfe);
                }
                catch (WEB3BaseException l_wbe)
                {
                    log.error(l_wbe.getMessage(), l_wbe);
                    throw new WEB3BaseRuntimeException(
                        l_wbe.getErrorInfo(), 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_wbe.getMessage(), l_wbe);
                }
                l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
                
                //市場から確認済みの注文単価
                l_eqtypeOrderUnitParams.setConfirmedOrderPrice(null);

                //市場から確認済みの概算受渡代金
                l_eqtypeOrderUnitParams.setConfirmedEstimatedPrice(null);

                //市場から確認済みの執行条件
                l_eqtypeOrderUnitParams.setConfirmedExecConditionType(null);

                //市場から確認済みの値段条件
                l_eqtypeOrderUnitParams.setConfirmedPriceConditionType(null);
                
                //決済順序区分
                l_eqtypeOrderUnitParams.setClosingOrderType(null);

                //注文エラー理由コード
                l_eqtypeOrderUnitParams.setErrorReasonCode(
                    WEB3ErrorReasonCodeDef.NORMAL);

                //リクエストタイプ
                if (WEB3OrderingConditionDef.DEFAULT.equals(
                    l_eqtypeOrderUnitParams.getOrderConditionType()))
                {
                    l_eqtypeOrderUnitParams.setRequestType(
                        l_carryoverOrderUnitRow.getRequestType());
                }
                else
                {
                    l_eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);
                }

                //注文Rev.
                l_eqtypeOrderUnitParams.setOrderRev("00");
                
                //市場から確認済みの注文Rev.
                l_eqtypeOrderUnitParams.setConfirmedOrderRev("00");
                
                //初回注文の注文単位ＩＤ
                l_eqtypeOrderUnitParams.setFirstOrderUnitId(
                    l_lngCarryoverOrderUnitId);
                
                //元発注条件
                l_eqtypeOrderUnitParams.setOrgOrderConditionType(
                    l_carryoverOrderUnitRow.getOrgOrderConditionType());
	            
	            //元発注条件演算子
                l_eqtypeOrderUnitParams.setOrgOrderCondOperator(
                    l_carryoverOrderUnitRow.getOrgOrderCondOperator());
	            
	            //元逆指値基準値
	            if (l_carryoverOrderUnitRow.getOrgStopOrderPriceIsNull())
	            {
	                l_eqtypeOrderUnitParams.setOrgStopOrderPrice(null);
	            }
	            else 
	            {
	                l_eqtypeOrderUnitParams.setOrgStopOrderPrice(
	                    l_carryoverOrderUnitRow.getOrgStopOrderPrice());
	            }
	            //元（W指値）訂正指値
	            if (l_carryoverOrderUnitRow.getOrgWLimitPriceIsNull())
	            {
	                l_eqtypeOrderUnitParams.setOrgWLimitPrice(null);
	            }
	            else
	            {
	                l_eqtypeOrderUnitParams.setOrgWLimitPrice(
	                    l_carryoverOrderUnitRow.getOrgWLimitPrice());
	            }
	            //元（W指値）執行条件
	            l_eqtypeOrderUnitParams.setOrgWLimitExecCondType(
	                l_carryoverOrderUnitRow.getOrgWLimitExecCondType());
	            //（W指値）執行条件
	            l_eqtypeOrderUnitParams.setWLimitExecCondType(
	                l_carryoverOrderUnitRow.getWLimitExecCondType());
	            //（W指値）切替前指値
	            if (l_carryoverOrderUnitRow.getWLimitBeforeLimitPriceIsNull())
	            {
	                l_eqtypeOrderUnitParams.setWLimitBeforeLimitPrice(null);
	            }
	            else
	            {
	                l_eqtypeOrderUnitParams.setWLimitBeforeLimitPrice(
	                    l_carryoverOrderUnitRow.getWLimitBeforeLimitPrice());
	            }
	            //（W指値）切替前執行条件
	            l_eqtypeOrderUnitParams.setWLimitBeforeExecCondType(
	                l_carryoverOrderUnitRow.getWLimitBeforeExecCondType());

                //発注遅延フラグ
                l_eqtypeOrderUnitParams.setSubmitOrderDelayFlag(
                    l_carryoverOrderUnitRow.getSubmitOrderDelayFlag());
            }
        }
        catch (NotFoundException l_nfe)
        {
            WEB3BaseRuntimeException l_runtimeException = new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
            log.error(STR_METHOD_NAME,l_runtimeException);
            throw l_runtimeException;
        }
        catch (WEB3BaseException l_wbe)
        {
            WEB3BaseRuntimeException l_runtimeException = new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbe.getMessage(),
                l_wbe);
            log.error(STR_METHOD_NAME,l_runtimeException);
            throw l_runtimeException;
        }

        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
    }

    /**
     * (更新値設定) <BR>
     * （mutateメソッドの実装）<BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * １）　@注文単位オブジェクト取得<BR>
     * <BR>
     * 引数の注文単位Params.注文ID、<BR>
     * 注文単位ＩＤに該当する注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@拡張項目セット<BR>
     * 　@○注文エラー理由コード<BR>
     * 　@　@パラメータ.注文履歴Params.注文エラー理由コードに”0000:正常”をセットする。<BR>
     * 　@<BR>
     * 　@○その他の項目<BR>
     * 　@　@パラメータ.注文履歴Paramsの拡張項目に、<BR>
     * 　@　@注文単位オブジェクトの同名項目から値をセットし、返却する。<BR>
     * @@param l_orderManagerPersistenceType
     * @@param l_orderManagerPersistenceContext
     * @@param l_eqtypeOrderActionParams
     * @@return EqtypeOrderActionParams
     * @@roseuid 4143DB7D0042
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderActionParams l_eqtypeOrderActionParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType,OrderManagerPersistenceContext,EqtypeOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        // 注文単位取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        OrderManager l_orderMgr = l_tradingMod.getOrderManager();
        EqtypeOrderUnitParams l_orderUnitParams = null;

        try
        {
            OrderUnit l_orderUnit = l_orderMgr.getOrderUnit(l_eqtypeOrderActionParams.order_unit_id);
            l_orderUnitParams = (EqtypeOrderUnitParams) l_orderUnit.getDataSourceObject();
        }
        catch (NotFoundException nfe)
        {
            WEB3BaseRuntimeException l_runtimeException = new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
            log.error(STR_METHOD_NAME,l_runtimeException);
            throw l_runtimeException;
        }
        
        // 取引者ＩＤ（注文単位テーブル．取引者ＩＤ）
        if (l_orderUnitParams.getTraderIdIsNull())
        {
            l_eqtypeOrderActionParams.setTraderId(null);
        }
        else
        {
            l_eqtypeOrderActionParams.setTraderId(l_orderUnitParams.getTraderId());
        }
        
        // 値段条件（注文単位テーブル．値段条件）
        l_eqtypeOrderActionParams.setPriceConditionType(
            l_orderUnitParams.getPriceConditionType());

        // 発注条件 (注文単位テーブル.発注条件)
        l_eqtypeOrderActionParams.setOrderConditionType(
            l_orderUnitParams.getOrderConditionType()
            );

        // 発注条件演算子 (注文単位テーブル.発注条件演算子)
        l_eqtypeOrderActionParams.setOrderCondOperator(
            l_orderUnitParams.getOrderCondOperator()
            );

        //逆指値基準値
        if (l_orderUnitParams.getStopOrderPriceIsNull())
        {
            l_eqtypeOrderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_eqtypeOrderActionParams.setStopOrderPrice(
                l_orderUnitParams.getStopOrderPrice());
        }

        //（W指値）訂正指値
        if (l_orderUnitParams.getWLimitPriceIsNull())
        {
            l_eqtypeOrderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_eqtypeOrderActionParams.setWLimitPrice(
            l_orderUnitParams.getWLimitPrice());
        }

        // 注文失効日付 (注文単位テーブル.注文失効日付)
        l_eqtypeOrderActionParams.setExpirationDate(
            l_orderUnitParams.getExpirationDate()
            );

        // 概算受渡代金 (株式注文単位テーブル.概算受渡代金)
        if(l_orderUnitParams.getEstimatedPriceIsNull())
        {
            l_eqtypeOrderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_eqtypeOrderActionParams.setEstimatedPrice(
                l_orderUnitParams.getEstimatedPrice()
                );
        }


        // 注文訂正・取消区分 (株式注文単位テーブル.注文訂正・取消区分)
        l_eqtypeOrderActionParams.setModifyCancelType(
            l_orderUnitParams.getModifyCancelType()
            );

        // 注文経路区分（注文単位テーブル．注文経路区分）
        l_eqtypeOrderActionParams.setOrderRootDiv(l_orderUnitParams.getOrderRootDiv());

        // 決済順序 (株式注文単位テーブル.決済順序)
        l_eqtypeOrderActionParams.setClosingOrderType(
            l_orderUnitParams.getClosingOrderType()
            );

        // 注文エラー理由コード (株式注文単位テーブル.注文エラー理由コード)
        l_eqtypeOrderActionParams.setErrorReasonCode(
            WEB3ErrorReasonCodeDef.NORMAL
            );

        // リクエストタイプ (株式注文単位テーブル.リクエストタイプ)
        l_eqtypeOrderActionParams.setRequestType(
            l_orderUnitParams.getRequestType()
            );
        
        // IPアドレス
		OpLoginSecurityService l_securityService = 
			(OpLoginSecurityService) Services.getService(
				OpLoginSecurityService.class);
		try 
		{
			String l_strIpAddress = 
				l_securityService.getSessionProperty(
					WEB3SessionAttributeDef.IP_ADDRESS);
			l_eqtypeOrderActionParams.setIpAddress(l_strIpAddress);
		} 
		catch (IllegalSessionStateException e) 
		{
			l_eqtypeOrderActionParams.setIpAddress(null);
		}
        
           //元発注条件
        l_eqtypeOrderActionParams.setOrgOrderConditionType(
            l_orderUnitParams.getOrgOrderConditionType());
        
        //元発注条件演算子
        l_eqtypeOrderActionParams.setOrgOrderCondOperator(
            l_orderUnitParams.getOrgOrderCondOperator());
        
        //元逆指値基準値
        if (l_orderUnitParams.getOrgStopOrderPriceIsNull())
        {
            l_eqtypeOrderActionParams.setOrgStopOrderPrice(null);
        }
        else 
        {
            l_eqtypeOrderActionParams.setOrgStopOrderPrice(
                l_orderUnitParams.getOrgStopOrderPrice());
        }
        
        //元（W指値）訂正指値
        if (l_orderUnitParams.getOrgWLimitPriceIsNull())
        {
            l_eqtypeOrderActionParams.setOrgWLimitPrice(null);
        }
        else 
        {
            l_eqtypeOrderActionParams.setOrgWLimitPrice(
                l_orderUnitParams.getOrgWLimitPrice());
        }
        
        //元（W指値）執行条件
        l_eqtypeOrderActionParams.setOrgWLimitExecCondType(
            l_orderUnitParams.getOrgWLimitExecCondType());
        
        //（W指値）執行条件
        l_eqtypeOrderActionParams.setWLimitExecCondType(
            l_orderUnitParams.getWLimitExecCondType());
        
        //（W指値）切替前指値
        if (l_orderUnitParams.getWLimitBeforeLimitPriceIsNull())
        {
            l_eqtypeOrderActionParams.setWLimitBeforeLimitPrice(null);
        }
        else 
        {
            l_eqtypeOrderActionParams.setWLimitBeforeLimitPrice(
                l_orderUnitParams.getWLimitBeforeLimitPrice());
        }
        
        //（W指値）切替前執行条件
        l_eqtypeOrderActionParams.setWLimitBeforeExecCondType(
            l_orderUnitParams.getWLimitBeforeExecCondType());

        //市場から確認済みの執行条件
        //注文単位テーブル.市場から確認済みの執行条件より編集
        l_eqtypeOrderActionParams.setConfirmedExecConditionType(
            l_orderUnitParams.getConfirmedExecConditionType());

        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderActionParams;
    }

    /**
     * (get株式注文内容) <BR>
     * 株式注文内容オブジェクトを取得する。<BR>
     * <BR>
     * @@return  - 株式注文内容 <BR>
     */
    public WEB3EquityNewCashBasedOrderSpec getEquityOrderSpec() 
    {
        return this.equityNewCashBasedOrderSpec;
    }
    
    /**
     * (set株式注文内容) <BR>
     * 株式注文内容オブジェクトをセットする。 <BR>
     * <BR>
     * @@param l_equityOrderSpec - 株式注文内容 <BR>
     * @@roseuid 4010C4A700BD
     */
    public void setEquityOrderSpec(WEB3EquityNewCashBasedOrderSpec l_equityOrderSpec)
    {
        this.equityNewCashBasedOrderSpec = l_equityOrderSpec;
    }


    /**
     *（mutateメソッドの実装）<BR>
     *株式約定Paramsに拡張項目(*)設定し返却する。<BR>
     *(*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     *１） 拡張項目セット<BR>
     *　@this.識別コードからパラメータ.株式約定Paramsの拡張項目に値をセットする。<BR>
     *　@this.約定日時からパラメータ.株式約定Paramsの拡張項目に値をセットする。<BR>
     *　@this.受渡日からパラメータ.株式約定Paramsの拡張項目に値をセットする。<BR>
     *　@this.発注日からパラメータ.株式約定Paramsの拡張項目に値をセットする。<BR>
     *  ２） パラメータ.株式約定Paramsを返却する。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType
     * @@param l_orderManagerPersistenceContext
     * @@param l_eqtypeOrderExecutionParams
     * @@return EqtypeOrderExecutionParams
     * @@roseuid 4143DB7D01D2
     */
    public EqtypeOrderExecutionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams)
    {
        return null;
    }

    /**
     * @@param arg0
     * @@param arg1
     * @@return com.fitechlabs.xtrade.kernel.data.BatchedQuery
     * @@roseuid 40A02C9200AB
     */
    public BatchedQuery getQueryToExecute(
        OrderManagerPersistenceType arg0, 
        Class arg1)
    {
        return null;
    }
    
    /**
     * (get繰越元注文単位)<BR>
     * プロパティ「繰越元注文単位」を取得する。
     * @@return EqTypeOrderUnit
     * @@roseuid 4112F5220352
     */
    public EqTypeOrderUnit getCarryoverOrderUnit() 
    {
       return this.l_eqtypeOrderUnit;
    }
    
    /**
     * (set繰越元注文単位)<BR>
     * 引数を、プロパティ「繰越元注文単位」にセットする。<BR>
     * @@param 繰越元注文単位 - 繰越元注文単位。
     * @@roseuid 4112F5220371
     */
    public void setCarryoverOrderUnit(EqTypeOrderUnit l_eqTypeOrderUnit) 
    {
        this.l_eqtypeOrderUnit = l_eqTypeOrderUnit;    
    }
    
    /**
     * (set注文経路区分)<BR>
     * 注文経路区分をセットする。
     * @@param l_strOrderRootDiv - (注文経路区分)<BR>
     * 注文経路区分。
     */
    public void setOrderRootDiv(String l_strOrderRootDiv)
    {
        this.orderRootDiv = l_strOrderRootDiv;
    }
    
    /**
     * (get注文経路区分)<BR>
     * 注文経路区分を取得する。
     * @@return String
     */
    public String getOrderRootDiv()
    {
        return this.orderRootDiv;
    }
    
    /**
     * (set受注日時)<BR>
     * 注日時をセットする。
     * @@param l_datReceivedDateTime - (受注日時)<BR>
     * 受注日時。
     */
    public void setReceivedDateTime(Date l_datReceivedDateTime)
    {
        this.receivedDateTime = l_datReceivedDateTime;
    }
    
    /**
     * (get受注日時)<BR>
     * 受注日時を取得する。
     * @@return Date
     */
    public Date getReceivedDateTime()
    {
        return this.receivedDateTime;
    }
}
@
