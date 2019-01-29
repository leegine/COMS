head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSonarCashTransUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金更新インタセプタ(WEB3AioSonarCashTransUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 周勇 (中訊) 新規作成  
                   2004/10/23 于美麗 (中訊) レビュー   
                   2004/12/20 周勇 (中訊) 残対応 
                   2006/08/28 車進 (中訊) 仕様変更・モデルNo.620             
*/
package webbroker3.aio;

import java.util.Date;
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
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (SONAR入出金更新インタセプタ)<BR>
 * SONAR入出金更新インタセプタクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{
    
    /**
     * (入出金注文内容)
     */
    protected WEB3AioNewOrderSpec cashTransOrderSpec;
    
    /**
     * (受渡日)
     */
    protected Date deliveryDate;
    
    /**
     * (受注日時)
     */
    protected Date receivedDateTime;
    

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSonarCashTransUpdateInterceptor.class); 
    
    /**
     * (SONAR入出金更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、引数の入出金注文内容をプロパティにセットする。
     * @@param l_cashTransOrderSpec - (入出金注文内容)<BR>
     * 入出金注文内容オブジェクト
     * @@return WEB3AioSonarCashTransUpdateInterceptor
     * @@roseuid 4105B7B902FF
     */
    public WEB3AioSonarCashTransUpdateInterceptor(WEB3AioNewOrderSpec l_cashTransOrderSpec) 
    {
        final String STR_METHOD_NAME = "WEB3AioSonarCashTransUpdateInterceptor(" +
            "WEB3AioNewOrderSpec l_cashTransOrderSpec)";
        log.entering(STR_METHOD_NAME);

        this.cashTransOrderSpec = l_cashTransOrderSpec;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。 <BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、<BR>返却する。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「SONAR入出金_注文単位テーブル仕様.xls」参照。 <BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE<BR>
     * （OrderManagerPersistenceTypeにて定数定義。）<BR>
     * @@param l_process - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 注文単位のパラメータクラス
     * @@return AioOrderUnitParams
     * @@roseuid 4105B7B9030F
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_updateType,
         OrderManagerPersistenceContext l_process, AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType l_updateType, " +
            "OrderManagerPersistenceContext l_process, " +
            "AioOrderUnitParams l_orderUnitParams)";
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
            //注文カテゴリ
            l_orderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);
            
            //受渡日
            l_orderUnitParams.setDeliveryDate(this.deliveryDate);

            //=========remain zhou-yong NO.1 begin ============
            
            //税区分
            l_orderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            
            //=========remain zhou-yong NO.1 end ============            
            
            // 1)受注日時
            l_orderUnitParams.setReceivedDateTime(this.receivedDateTime);
            
            // 2)扱者コード(SONAR)の設定を行う。
            long l_lngAccountId = l_orderUnitParams.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();            
            MainAccount l_acc = l_accountManager.getMainAccount(l_lngAccountId);
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_acc.getDataSourceObject();
            String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
            l_orderUnitParams.setSonarTraderCode(l_strSonarTraderCode);
            
            // 3)識別コードの設定を行う。
            l_orderUnitParams.setOrderRequestNumber(null);
            
            // 4).comデビット決済取引番号の設定を行う。
            l_orderUnitParams.setComDebitNumber(null);
            
            // 5)保証金区分の設定を行う。
            l_orderUnitParams.setGuaranteeDiv(null);
            
            // 6)出金申込区分の設定を行う。
            l_orderUnitParams.setPaymentApplicationDiv(null);
            
            // 7)注文取消区分の設定を行う。
            l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            
            // 8)注文経路区分の設定を行う。
            l_orderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
            
            // 9)注文エラー理由コードの設定を行う。
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            // 10)MQステータスの設定を行う。
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
       
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
     * (set受渡日)<BR>
     * 受渡日をセットする。
     * @@param l_datDeliveryDate - (受渡日)
     * @@roseuid 4105B7B9033E
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        final String STR_METHOD_NAME = "setDeliveryDate(Date l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);
        
        this.deliveryDate = l_datDeliveryDate;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set受注日時)<BR>
     * 受注日時をセットする。
     * @@param l_dateReceivedDateTime - (受注日時)
     * @@roseuid 4105B7B9033E
     */
    public void setReceivedDateTime(Date l_dateReceivedDateTime) 
    {
        final String STR_METHOD_NAME = "setReceivedDateTime(Date l_dateReceivedDateTime)";
        log.entering(STR_METHOD_NAME);
          
        this.receivedDateTime = l_dateReceivedDateTime;
        
        log.exiting(STR_METHOD_NAME);
    }
    
}
@
