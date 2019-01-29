head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSonarCashTransForeignUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金（外貨）更新インタセプタ(WEB3AioSonarCashTransForeignUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 車進 (中訊) 新規作成              
*/

package webbroker3.aio;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;


/**
 * (SONAR入出金（外貨）更新インタセプタ)<BR>
 * SONAR入出金（外貨）更新インタセプタクラス<BR>
 * 
 * @@author 車進(中訊)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignUpdateInterceptor extends 
    WEB3AioSonarCashTransUpdateInterceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSonarCashTransForeignUpdateInterceptor.class); 
    
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    private String currencyCode;
    
    /**
     * (入出金金額(円換算額))<BR>
     * 入出金金額(円換算額)<BR>
     */
    private double convertAmount;
    
    /**
     * (SONAR入出金（外貨）更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、引数の入出金注文内容をプロパティにセットする。<BR>
     * @@param l_cashTransOrderSpec - (入出金注文内容)<BR>
     * 入出金注文内容オブジェクト<BR>
     * @@return WEB3AioSonarCashTransForeignUpdateInterceptor
     * @@roseuid 44D85B3402C1
     */
    public WEB3AioSonarCashTransForeignUpdateInterceptor(WEB3AioNewOrderSpec l_cashTransOrderSpec) 
    {
        super(l_cashTransOrderSpec);
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。 <BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「SONAR入出金（外貨）_注文単位テーブル仕様.xls」参照。 <BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE<BR>
     * <BR>
     * （OrderManagerPersistenceTypeにて定数定義。）<BR>
     * @@param l_process - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 注文単位のパラメータクラス<BR>
     * @@return AioOrderUnitParams
     * @@roseuid 44D85BA80315
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, " +
            "OrderManagerPersistenceContext, " +
            "AioOrderUnitParams)";
        
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            log.debug("注文単位ParamsがNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            // 1)注文カテゴリ
            l_orderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);
            
            // 2)受渡日
            l_orderUnitParams.setDeliveryDate(this.deliveryDate);
            
            // 3)税区分
            l_orderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            
            // 4)ミニ株区分
            l_orderUnitParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
            
            // 5)受注日時
            l_orderUnitParams.setReceivedDateTime(this.receivedDateTime);
            
            // 6)扱者コード(SONAR)の設定を行う。
            long l_lngAccountId = l_orderUnitParams.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();   
            MainAccount l_acc = l_accountManager.getMainAccount(l_lngAccountId);
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_acc.getDataSourceObject();
            String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
            l_orderUnitParams.setSonarTraderCode(l_strSonarTraderCode);
            
            // 7)識別コードの設定を行う。
            l_orderUnitParams.setOrderRequestNumber(null);
            
            // 8).comデビット決済取引番号の設定を行う。
            l_orderUnitParams.setComDebitNumber(null);
            
            // 9)保証金区分の設定を行う。
            l_orderUnitParams.setGuaranteeDiv(null);
            
            // 10)出金申込区分の設定を行う。
            l_orderUnitParams.setPaymentApplicationDiv(null);
            
            // 11)注文取消区分の設定を行う。
            l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            
            // 12)注文経路区分の設定を行う。
            l_orderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
            
            // 13)注文エラー理由コードの設定を行う。
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            // 14)MQステータスの設定を行う。
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            
            // 15)通貨コードの設定を行う。
            l_orderUnitParams.setCurrencyCode(this.currencyCode);
            
            //16)入出金金額(円換算額)の設定を行う。
            l_orderUnitParams.setConvertAmount(this.convertAmount);
        }
        catch(NotFoundException l_ex)
        {
            log.error("該当する顧客オブジェクトがありません", l_ex); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
           
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (set通貨コード)<BR>
     * 通貨コードをセットする。<BR>
     * @@param l_strCurrencyCode - (通貨コード)<BR>
     * 通貨コード<BR>
     * @@roseuid 44D85EEF0347
     */
    public void setCurrencyCode(String l_strCurrencyCode) 
    {
        final String STR_METHOD_NAME = "setCurrencyCode(String)";
        log.entering(STR_METHOD_NAME);
        
        this.currencyCode = l_strCurrencyCode;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set入出金金額(円換算額))<BR>
     * 入出金金額(円換算額)をセットする。<BR>
     * @@param l_dblConvertAmount - (入出金金額(円換算額))<BR>
     * 入出金金額(円換算額)<BR>
     * @@roseuid 44E45E4C0219
     */
    public void setConvertAmount(double l_dblConvertAmount) 
    {
        final String STR_METHOD_NAME = "setConvertAmount(double)";
        log.entering(STR_METHOD_NAME);
        
        this.convertAmount = l_dblConvertAmount;
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
