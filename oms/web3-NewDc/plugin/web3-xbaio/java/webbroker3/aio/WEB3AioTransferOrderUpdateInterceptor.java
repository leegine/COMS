head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.35.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioTransferOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替注文更新インタセプタ(WEB3AioTransferOrderUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 周勇 (中訊) 新規作成       
                   2004/10/23 于美麗 (中訊) レビュー    
                   2005/01/07 周勇 (中訊) 残対応
Revesion History : 2007/08/02 張騰宇(中訊)仕様変更モデル749 ＤＢ更新仕様144 147
Revesion History : 2009/03/31 王志葵(中訊)ＤＢ更新仕様226,227
*/
package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (振替注文更新インタセプタ)<BR>
 * 振替注文更新インタセプタクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioTransferOrderUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
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
     * (MQステータス)
     */
    private String mqStatus;
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioTransferOrderUpdateInterceptor.class); 
    
    /**
     * (振替注文更新インタセプタ)<BR>
     * コンストラクタ <BR>
     * <BR>
     * インスタンスを生成し、引数の入出金注文内容をプロパティにセットする。
     * @@param cashTransOrderSpec - (入出金注文内容オブジェクト)
     * @@return WEB3AioTransferOrderUpdateInterceptor
     * @@roseuid 413583820016
     */
    public WEB3AioTransferOrderUpdateInterceptor(WEB3AioNewOrderSpec cashTransOrderSpec) 
    {
        final String STR_METHOD_NAME = "WEB3AioTransferOrderUpdateInterceptor" +
            "(WEB3AioNewOrderSpec cashTransOrderSpec)";
        log.entering(STR_METHOD_NAME);
   
        this.cashTransOrderSpec = cashTransOrderSpec;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）  <BR>
     * 注文単位Paramsに拡張項目(*)を設定し返却する。  <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。  <BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。  <BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。 <BR>
     * <BR>
     * １）注文経路区分について  <BR>
     * 以下のように設定する。  <BR>
     * <BR>
     * 注文単位.注文種別 = 1005（振替注文（預り金から信用保証金）） の場合、 <BR>
     * セッションより取得した同項目の値をセットする。 セッションより取得できない場合は、<BR>
     * 2（PC）をセットする。<BR>
     * 注文単位.注文種別 = 1005以外の場合、セッションより取得した同項目の値をセットする。  <BR>
     * <BR>
     * <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新  <BR>
     *  「証拠金から振替_注文単位テーブル仕様.xls」参照。  <BR>
     *  「信用保証金への振替_注文単位テーブル.xls」参照。<BR>
     * @@param l_updateType - ((更新タイプ)<BR>)<BR>
     * INSERTまたは、UPDATE<BR>
     * <BR>
     * （OrderManagerPersistenceTypeにて定数定義。）
     * 
     * @@param l_process - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）
     * 
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 注文単位のパラメータクラス
     * 
     * @@return AioOrderUnitParams
     * @@roseuid 413583820025
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_updateType,
         OrderManagerPersistenceContext l_process, AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType l_updateType," +
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
            l_orderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);  

            //発注日
            l_orderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                this.bizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD));

            //受渡日
            l_orderUnitParams.setDeliveryDate(this.deliveryDate);

            //税区分
            //デフォルト：0
            l_orderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            
            //ミニ株区分
            //デフォルト：0
            l_orderUnitParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
            
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
            
            // 注文経路区分について以下のように設定する。
            //注文単位.注文種別 = 1005（振替注文（預り金から信用保証金）） の場合、
            //セッションより取得した同項目の値をセットする。
            //    セッションより取得できない場合は、2（PC）をセットする。
            //注文単位.注文種別 = 1005以外の場合、セッションより取得した同項目の値をセットする。
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            String l_strRootDiv = null;
            if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_orderUnitParams.getOrderType()))
            {
                try
                {
                    l_strRootDiv = l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
                    // WEB3-AIO-A-FT-0245により
                    if (WEB3StringTypeUtility.isEmpty(l_strRootDiv))
                    {
                        l_strRootDiv = WEB3OrderRootDivDef.PC;
                    }
                }
                catch (IllegalSessionStateException l_ex)
                {
                    l_strRootDiv = WEB3OrderRootDivDef.PC;
                }
            }
            else
            {
                l_strRootDiv = l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            }
            l_orderUnitParams.setOrderRootDiv(l_strRootDiv);
            
            // 9)注文エラー理由コードの設定を行う。
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            // 10)MQステータスの設定を行う。
            l_orderUnitParams.setMqStatus(this.mqStatus);

            //通貨コード
            l_orderUnitParams.setCurrencyCode(null);

            //入出金金額(円換算額)
            l_orderUnitParams.setConvertAmount(null);

            //摘要コード
            l_orderUnitParams.setRemarkCode(null);

            //摘要名
            l_orderUnitParams.setRemarkName(null);
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
     * @@roseuid 413D514B0140
     */
    public void setBizDate(Date l_datBizDate) 
    {
        final String STR_METHOD_NAME = "setBizDate(Date l_datBizDate) ";
        log.entering(STR_METHOD_NAME);
         
        this.bizDate = l_datBizDate;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set受渡日)<BR>
     * 受渡日をセットする。
     * @@param l_datDeliveryDate - (受渡日)
     * @@roseuid 413D514B0314
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        final String STR_METHOD_NAME = "setDeliveryDate(Date l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);
  
        this.deliveryDate  = l_datDeliveryDate;
        
        log.exiting(STR_METHOD_NAME);     
     
    }
    
    /**
     * (set識別コード)<BR>
     * 識別コードをセットする。
     * @@param l_orderRequestNumber - (識別コード)
     * @@roseuid 413FF8900136
     */
    public void setOrderRequestNumber(String l_orderRequestNumber) 
    {
        final String STR_METHOD_NAME = "setOrderRequestNumber(" +
            "String l_orderRequestNumber)";
        log.entering(STR_METHOD_NAME);
   
        this.orderRequestNumber  = l_orderRequestNumber;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    //===========remain zhou-yong NO.1 begin ========

    /**
     * (setMQステータス)<BR>
     * MQステータスをセットする。
     * @@param l_mqStatus - (MQステータス)
     * @@roseuid 413FF8900136
     */
    public void setMqStatus(String l_mqStatus) 
    {
        final String STR_METHOD_NAME = "setMqStatus(String l_mqStatus)";
        log.entering(STR_METHOD_NAME);
   
        this.mqStatus  = l_mqStatus;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    
    
    //===========remain zhou-yong NO.1 end ========
}
@
