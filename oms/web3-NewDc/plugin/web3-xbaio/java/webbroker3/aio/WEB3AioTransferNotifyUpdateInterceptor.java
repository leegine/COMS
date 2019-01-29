head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioTransferNotifyUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替通知更新インタセプタ(WEB3AioTransferNotifyUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 周勇 (中訊) 新規作成       
                   2004/10/23 于美麗 (中訊) レビュー            
*/
package webbroker3.aio;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
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
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (振替通知更新インタセプタ)<BR>
 * 振替通知更新インタセプタクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioTransferNotifyUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{
    
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
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioTransferNotifyUpdateInterceptor.class);    
    
    /**
     * (振替通知更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、引数の入出金注文内容をプロパティにセットする。
     * @@param l_cashTransOrderSpec - (入出金注文内容)<BR>
     * 入出金注文内容オブジェクト
     * @@roseuid 413D53A101DC
     */
    public WEB3AioTransferNotifyUpdateInterceptor(WEB3AioNewOrderSpec l_cashTransOrderSpec) 
    {
        final String STR_METHOD_NAME = "WEB3AioTransferNotifyUpdateInterceptor" +
        "(WEB3AioNewOrderSpec l_cashTransOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        if (l_cashTransOrderSpec == null)
        {
            log.debug("入出金注文内容がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }     
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
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に<BR>
     * 値をセットし、返却する。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「振替請求通知_注文単位テーブル仕様.xls」参照。 <BR>
     * @@param l_updateType - ((更新タイプ)<BR>)<BR>
     * INSERTまたは、UPDATE<BR>
     * <BR>
     * （OrderManagerPersistenceTypeにて定数定義。）<BR>
     * @@param l_process - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 注文単位のパラメータクラス
     * @@return AioOrderUnitParams
     * @@roseuid 413D53A10314
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
            // 1)注文カテゴリの設定を行う。
            l_orderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            
            //発注日
            l_orderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.bizDate,
                "yyyyMMdd"));
            
            //受渡日
            l_orderUnitParams.setDeliveryDate(this.deliveryDate);
            
            // 2)受注日時の設定を行う。
            l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
            // 3)扱者コード(SONAR)の設定を行う。
            long l_lngAccountId = l_orderUnitParams.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();            
            MainAccount l_acc = l_accountManager.getMainAccount(l_lngAccountId);
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_acc.getDataSourceObject();
            String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
            l_orderUnitParams.setSonarTraderCode(l_strSonarTraderCode);
            
            //識別コード
            l_orderUnitParams.setOrderRequestNumber(this.orderRequestNumber);
            
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
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED );
            
            //=========remian zhou-yong NO.1 begin =========
            
            //11) 税区分
            l_orderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            
            //=========remian zhou-yong NO.1 end =========
        
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
     * (set発注日)<BR>
     * 発注日をセットする。
     * @@param l_datBizDate - (発注日)
     * @@roseuid 4141376A03DA
     */
    public void setBizDate(Date l_datBizDate) 
    {
        final String STR_METHOD_NAME = "setBizDate(Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);

        this.bizDate = l_datBizDate;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set受渡日)<BR>
     * 受渡日をセットする。
     * @@param l_datDeliveryDate - (受渡日)
     * @@roseuid 413D53A200A3
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        final String STR_METHOD_NAME = "setDeliveryDate(Date l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);

        this.deliveryDate = l_datDeliveryDate;
        
        log.exiting(STR_METHOD_NAME);  
     
    }
    
    /**
     * (set識別コード)<BR>
     * 識別コードをセットする。
     * @@param l_orderRequestNumber - (識別コード)
     * @@roseuid 413FF9A7024F
     */
    public void setOrderRequestNumber(String l_orderRequestNumber) 
    {
        final String STR_METHOD_NAME = "setOrderRequestNumber(String l_orderRequestNumber)";
        log.entering(STR_METHOD_NAME);
   
        this.orderRequestNumber = l_orderRequestNumber;
        
        log.exiting(STR_METHOD_NAME);       
    }
}
@
