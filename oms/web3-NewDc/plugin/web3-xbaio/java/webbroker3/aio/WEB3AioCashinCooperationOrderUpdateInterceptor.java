head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.34.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinCooperationOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連携注文更新インタセプタ クラス(WEB3AioCashinCooperationOrderUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/12 韋念瓊 (中訊) 新規作成
                   2006/08/30 車進　@(中訊)モデルNo.621対応
                   2006/09/18 徐宏偉　@(中訊)モデルNo.647対応
*/

package webbroker3.aio;

import java.util.Date;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

/**
 * (入金連携注文更新インタセプタ )
 * 入金連携注文更新インタセプタクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinCooperationOrderUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinCooperationOrderUpdateInterceptor.class);  
    
    /**
     * (入出金注文内容)
     */
    private WEB3AioNewOrderSpec cashTransOrderSpec;
    
    /**
     * (発注日)
     */
    private Date bizDate;
    
    /**
     * (受渡日)
     */
    private Date deliveryDate;
    
    /**
     * (識別コード)
     */
    private String orderRequestNumber;
    
    /**
     * (発注経路)
     */
    private String bizChannel;
    
    /**
     * (振替記述)
     */
    private String description;
    
    /**
     * (通貨コード)<BR>
     */
    private String currencyCode;
    
    /**
     * 入出金金額(円換算額)<BR>
     */
    private Double convertAmount;
    
    /**
     * (入金連携注文更新インタセプタ)<BR>
     * デフォルトコンストラクタ。 <BR>
     * <BR>
     * @@param l_cashTransOrderSpec  - (入出金注文内容オブジェクト)
     * @@roseuid 41E4FF0100DC
     */
    public WEB3AioCashinCooperationOrderUpdateInterceptor(WEB3AioNewOrderSpec l_cashTransOrderSpec) 
    {
        this.cashTransOrderSpec = l_cashTransOrderSpec;
    }
    
    /**
     * （mutateメソッドの実装）  
     * 注文単位Paramsに拡張項目(*)設定し返却する。  <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。  <BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。  <BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、<BR>
     * 返却する。 <BR> 
     * <BR>
     * １）MQステータスについて <BR>
     *    1（送信済み）をセットする。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新  <BR>
     * 「銀行入金通知_注文単位テーブル仕様.xls」 <BR>
     * 「銀行入金通知_注文単位テーブル仕様.xls」参照。  <BR>
     * 「入金連携_注文単位テーブル仕様.xls」を参照。 <BR>
     * 
     * @@param l_updateType - 更新タイプ
     * @@param l_process - 処理
     * @@param l_orderUnitParams - 注文単位のパラメータクラス
     * 
     * @@return AioOrderUnitParams
     * @@roseuid 41E4FF0100EB
     */
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(" +
            "OrderManagerPersistenceType l_updateType, " +
            "OrderManagerPersistenceContext l_process, " +
            "AioOrderUnitParams l_orderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            log.debug("注文単位ParamsがNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }         
        //【ｘTrade】補足資料.DB更新  
        //「銀行入金通知_注文単位テーブル仕様.xls」 

        try
        {
            // 1)注文種別 = 1002：入金注文
            l_orderUnitParams.setOrderType(OrderTypeEnum.CASH_IN);
            
            // 2)銘柄タイプ = 5：現金
            l_orderUnitParams.setProductType(ProductTypeEnum.CASH);
            
            // 3)注文数量 = 入出金注文内容.getQuantity()の戻り値
            l_orderUnitParams.setQuantity(this.cashTransOrderSpec.getQuantity());
            
            // 4)発注日 = インタセプタ.発注日
            //振込日が当日及び当日以前でSONARへの送信時間内に発生した伝票の場合、当日発注。
            //振込日が当日及び当日以前でSONARへの送信時間外に発生した伝票の場合、翌営業日発注。
            //振込日が当日以降で振込日が営業日の場合、振込日発注。
            //振込日が当日以降で振込日が非営業日の場合、振込日翌営業日発注。
            
            l_orderUnitParams.setBizDate(
                WEB3DateUtility.formatDate(this.bizDate, "yyyyMMdd"));
            
            // 5)受渡日 = インタセプタ.受渡日発注日と同じ
            l_orderUnitParams.setDeliveryDate(this.deliveryDate);
        
            // 6)扱者コード（SONAR）= 顧客.扱者コード
            long l_lngAccountId = l_orderUnitParams.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();            
            MainAccount l_acc = l_accountManager.getMainAccount(l_lngAccountId);
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_acc.getDataSourceObject();
            String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
            
            l_orderUnitParams.setSonarTraderCode(l_strSonarTraderCode);
            
            // 7)識別コード = インタセプタ.識別コード
            l_orderUnitParams.setOrderRequestNumber(this.orderRequestNumber);
            
            // 8).comデビット決済取引番号 = null
            l_orderUnitParams.setComDebitNumber(null);
            
            // 9)保証金区分 = null
            l_orderUnitParams.setGuaranteeDiv(null);
            
            // 10)出金申込区分 = null
            l_orderUnitParams.setPaymentApplicationDiv(null);
            
            // 11)注文取消区分 = 0:初期値
            l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            
            // 12)注文経路区分
            l_orderUnitParams.setOrderRootDiv(this.bizChannel);
            
            // 13)注文エラー理由コード = 0000：正常
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            // 14)MQステータス = 1:送信済み
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            
            // 15)注文カテゴリ = 9：入出金
            l_orderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);
            
            // 16)振替記述
            l_orderUnitParams.setDescription(this.description);
            
            //17)受注日時 
            l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            
            //18 通貨コード
            l_orderUnitParams.setCurrencyCode(this.currencyCode);
            
            //19 入出金金額(円換算額)
            l_orderUnitParams.setConvertAmount(this.convertAmount);
        }
        catch(NotFoundException l_ex)
        {
            log.error("該当する顧客オブジェクトがありません", l_ex); 
            log.exiting(STR_METHOD_NAME);
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
     * (set発注日)<BR>
     * 発注日をセットする。<BR>
     * @@param l_datOrderBizDate - 発注日
     * @@roseuid 41E4FF01010A
     */
    public void setOrderBizDate(Date l_datOrderBizDate) 
    {
        bizDate = l_datOrderBizDate;
    }
    
    /**
     * (set受渡日)<BR>
     * 受渡日をセットする。<BR>
     * @@param l_datDeliveryDate - 受渡日
     * @@roseuid 41E4FF010139
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        deliveryDate = l_datDeliveryDate;
    }
    
    /**
     * (set識別コード)<BR>
     * 識別コードをセットする。<BR>
     * @@param l_strOrderRequestNumber - 識別コード
     * @@roseuid 41E4FF010178
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber) 
    {
        orderRequestNumber = l_strOrderRequestNumber;
    }
    
    /**
     * (set発注経路)<BR>
     * 発注経路をセットする。<BR>
     * @@param l_strBizChannel - 発注経路
     * @@roseuid 41E4FF010178
     */
    public void setBizChannel(String l_strBizChannel) 
    {
        bizChannel = l_strBizChannel;
    }
    
    /**
     * (set振替記述)<BR>
     * 振替記述をセットする。 <BR>
     * (*入金連携では、金融機@関コードがセットされる）<BR>
     * @@param l_strDescription - 振替記述
     * @@roseuid 41E4FF010178
     */
    public void setDescription(String l_strDescription) 
    {
        description = l_strDescription;
    }
    
    /**
     * (set通貨コード )<BR>
     * 通貨コードをセットする。 <BR>
     * @@param l_strCurrencyCode - 通貨コード
     * @@roseuid 41E4FF010178
     */
    public void setCurrencyCode(String l_strCurrencyCode) 
    {
        this.currencyCode = l_strCurrencyCode;
    }
    
    /**
     * (set入出金金額（円換算額）)<BR>
     * 入出金金額（円換算額）をセットする。 <BR>
     * @@param l_dblConvertAmount - 入出金金額（円換算額）
     * @@roseuid 41E4FF010178
     */
    public void setConvertAmount(Double l_dblConvertAmount) 
    {
        this.convertAmount = l_dblConvertAmount;
    }
}
@
