head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderNotifyInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文通知インタセプタ(WEB3EquityOrderNotifyIntercepter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 欒学峰(中訊) 作成
Revesion History : 2006/04/26 謝旋 (中訊) ＤＢ更新仕様No.198
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ForcedExpireType;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.define.WEB3EquityVoucherNoDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

/**
 * （株式注文通知インタセプタ）。
 * @@version 1.0
 */
public class WEB3EquityOrderNotifyInterceptor extends WEB3EquityOrderManagerPersistenceEventInterceptor
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityOrderNotifyInterceptor.class);


    /**
     * (株式注文入力通知キューParams) <BR>
     * 【株式注文入力通知キューテーブル】の１レコード。 <BR>
     */
    private HostEqtypeOrderReceiptParams hostEqtypeOrderReceiptParams;

    /**
     * @@roseuid 40B5A0FA0148
     */
    public WEB3EquityOrderNotifyInterceptor()
    {

    }

    /**
     * (株式注文通知インタセプタ) <BR>
     * コンストラクタ。 <BR>
     * 引数を、クラスのプロパティにセットする。 <BR>
     * @@param l_hostEqtypeOrderReceiptParams - (株式注文入力通知キューParams) <BR>
     * 【株式注文入力通知キューテーブル】の１レコード。 <BR>
     * @@return WEB3EquityOrderNotifyIntercepter
     * @@roseuid 403EF16602DD
     */
    public WEB3EquityOrderNotifyInterceptor(HostEqtypeOrderReceiptParams l_hostEqtypeOrderReceiptParams)
    {
        this.hostEqtypeOrderReceiptParams = l_hostEqtypeOrderReceiptParams;
    }

    /**
     * (更新値設定) <BR>
     * （mutateメソッドの実装） <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。 <BR>
     * <BR>
     * １） プロパティ設定判定 <BR>
     * 　@this.株式注文入力通知キューParamsプロパティがnullの場合は <BR>
     * パラメータ.注文単位Paramsを返却し <BR>
     * 処理を終了する。 <BR>
     * <BR>
     * ２） 拡張項目セット <BR>
     * 　@this.株式注文入力通知キューParamsプロパティから、 <BR>
     * パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。<BR>
     * 更新内容は、 <BR>
     * 「現物株式注文通知_株式注文単位テーブル.xls」参照。 <BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * OrderManagerPersistenceTypeにて定数定義。 <BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義） <BR>
     * <BR>
     * @@param l_eqtypeOrderUnitParams - (注文単位Params)<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 403EF16602D9
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType, 
        OrderManagerPersistenceContext l_orderManagerPersistenceContext, 
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams)
    {
        String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType OrderManagerPersistenceContext, EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (this.hostEqtypeOrderReceiptParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_eqtypeOrderUnitParams;
        }

        WEB3EquityNewCashBasedOrderSpec l_cashBaseOrderSpec = getEquityOrderSpec();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        AccountManager l_accMgr = l_finApp.getAccountManager();
       
        try
        {            
            // 値段条件            
            l_eqtypeOrderUnitParams.setPriceConditionType(
                l_orderManager.getPriceConditionType(
                    this.hostEqtypeOrderReceiptParams.getPriceConditionType()));
            
            //発注条件
            l_eqtypeOrderUnitParams.setOrderConditionType(l_cashBaseOrderSpec.getOrderCond());
            
            if (l_eqtypeOrderUnitParams.getOrderConditionType().equals(WEB3OrderingConditionDef.DEFAULT))
            {
                //発注条件演算子
                l_eqtypeOrderUnitParams.setOrderCondOperator(null);
                //逆指値基準値
                l_eqtypeOrderUnitParams.setStopOrderPrice(null);
                //（W指値）訂正指値
                l_eqtypeOrderUnitParams.setWLimitPrice(null);
            }
            else
            {
                // 発注条件演算子
                l_eqtypeOrderUnitParams.setOrderCondOperator(
                    l_cashBaseOrderSpec.getOrderCondOperator());   
                // 逆指値基準値  
                l_eqtypeOrderUnitParams.setStopOrderPrice(
                    l_cashBaseOrderSpec.getStopLimitPriceBasePrice());       
                //（W指値）訂正指値
                if (l_eqtypeOrderUnitParams.getOrderConditionType().equals(WEB3OrderingConditionDef.STOP_LIMIT_PRICE))
                {
                    l_eqtypeOrderUnitParams.setWLimitPrice(null);
                }
                else
                {
                    l_eqtypeOrderUnitParams.setWLimitPrice(
                        l_cashBaseOrderSpec.getWLimitPriceChange());     
                }
            }
            
            //受渡日
            if (this.hostEqtypeOrderReceiptParams.getDeliveryDate() != null)
            {
				l_eqtypeOrderUnitParams.setDeliveryDate(
                    WEB3DateUtility.getDate(
                        this.hostEqtypeOrderReceiptParams.getDeliveryDate(), "yyyyMMdd"));
            }

            //税区分（現引現渡）
            l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);
            
            //弁済区分
            l_eqtypeOrderUnitParams.setRepaymentType(null);
            
            //弁済期限値
            l_eqtypeOrderUnitParams.setRepaymentNum(null);
            
            //弁済区分（SONAR)
            l_eqtypeOrderUnitParams.setSonarRepaymentType(null);

            //発注日
            l_eqtypeOrderUnitParams.setBizDate(
                GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat()
                    .format(GtlUtils.getSystemTimestamp()));
        
            //初回注文の注文チャネル
            l_eqtypeOrderUnitParams.setOrderChanel(
                this.hostEqtypeOrderReceiptParams.getChannel());
        
            //受注日時
            l_eqtypeOrderUnitParams.setReceivedDateTime(
                this.hostEqtypeOrderReceiptParams.getCreateDatetime());
        
            //伝票No
            String l_strOrderRequestNumber =
                this.hostEqtypeOrderReceiptParams.getOrderRequestNumber();
            String l_strOrderRequestNumber_3 = "";
            String l_strVoucherNo = "";
            if(l_strOrderRequestNumber.length() >= 3)
            {
                l_strOrderRequestNumber_3 = 
                l_strOrderRequestNumber.substring(l_strOrderRequestNumber.length() - 3,
                l_strOrderRequestNumber.length());   
                l_strVoucherNo = WEB3EquityVoucherNoDef.VoucherNo + l_strOrderRequestNumber_3;
            }
            l_eqtypeOrderUnitParams.setVoucherNo(l_strVoucherNo);

            //初回注文の手数料No
            l_eqtypeOrderUnitParams.setCommTblNo(
                l_cashBaseOrderSpec.getCommission().getCommissionNo());
        
            //初回注文の手数料No枝番
            l_eqtypeOrderUnitParams.setCommTblSubNo(
                l_cashBaseOrderSpec.getCommission().getCommissionRevNo());

            //扱者コード（SONAR）
            MainAccount l_mainAccount = null;
            try
            {
                l_mainAccount =
                    l_accMgr.getMainAccount(l_eqtypeOrderUnitParams.account_id);
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(), l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), l_nfe);
            }
            MainAccountRow l_accRow =
                (MainAccountRow) l_mainAccount.getDataSourceObject();
            l_eqtypeOrderUnitParams.setSonarTraderCode(
                l_accRow.getSonarTraderCode());

            //注文単価
            l_eqtypeOrderUnitParams.setPrice(l_cashBaseOrderSpec.getOrderUnitPrice()); 
        
            //識別コード
            l_eqtypeOrderUnitParams.setOrderRequestNumber(
                this.hostEqtypeOrderReceiptParams.getOrderRequestNumber());
        
            //概算受渡代金
            l_eqtypeOrderUnitParams.setEstimatedPrice(
                l_cashBaseOrderSpec.getEstimateDeliveryAmount());

            //譲渡益金額
            l_eqtypeOrderUnitParams.setCapitalGain(0.0);
            
            //譲渡益税額
            l_eqtypeOrderUnitParams.setCapitalGainTax(0.0);
        
            //取引コード（SONAR）
            l_eqtypeOrderUnitParams.setSonarTradedCode(
                this.hostEqtypeOrderReceiptParams.getSonarTradedCode());
        
            //市場コード（SONAR）
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_eqtypeOrderUnitParams);
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            Market l_market = null;
            try
            {
                l_market = l_finApp.getFinObjectManager().getMarket(l_orderUnitRow.getMarketId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
            String l_strMaketCode = l_marketRow.getSonarMarketCode();
            l_eqtypeOrderUnitParams.setSonarMarketCode(l_strMaketCode);
        
            //手数料商品コード
            l_eqtypeOrderUnitParams.setCommProductCode(
                l_cashBaseOrderSpec.getCommissionProductCode());
            
            //空売フラグ
            l_eqtypeOrderUnitParams.setShortSellOrderFlag(
                WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);
        
            //注文訂正・取消区分
            l_eqtypeOrderUnitParams.setModifyCancelType(
                WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        
            //注文経路区分
            l_eqtypeOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
            
            //発注経路区分
            l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv(
                hostEqtypeOrderReceiptParams.getSubmitOrderRouteDiv());
        
            //決済順序
            l_eqtypeOrderUnitParams.setClosingOrderType(null);
        
            //注文エラー理由コード
            l_eqtypeOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
            //リクエストタイプ
            l_eqtypeOrderUnitParams.setRequestType(null);
        
            //注文Rev.
            l_eqtypeOrderUnitParams.setOrderRev("00");
            
            //市場から確認済みの注文Rev.
            l_eqtypeOrderUnitParams.setConfirmedOrderRev("00");
            
            //初回注文の注文単位ＩＤ
            l_eqtypeOrderUnitParams.setFirstOrderUnitId(null);
            
            // 強制決済理由区分
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
        catch (WEB3BaseException l_wbe)
        {
            throw new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbe.getMessage(),
                l_wbe);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
    }
}
@
