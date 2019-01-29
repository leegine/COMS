head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashTransOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金注文更新インタセプタ(WEB3AioCashTransOrderUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 周勇 (中訊) 新規作成
                   2004/10/23 于美麗 (中訊) レビュー
                   2004/11/28 韋念瓊 (中訊) フィデリティ対応
                   2006/04/13 肖志偉 (中訊) 仕様変更・モデル528
                   2006/04/14 肖志偉 (中訊) 仕様変更・モデル539
                   2006/10/17 徐宏偉 (中訊) 仕様変更・モデル626
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (入出金注文更新インタセプタ)<BR>
 * 入出金注文更新インタセプタクラス
 *
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashTransOrderUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor
{

    /**
     * (入出金注文内容)
     */
    protected WEB3AioNewOrderSpec cashTransOrderSpec;

    /**
     * (発注日)
     */
    protected Date bizDate;

    /**
     * (受渡日)
     */
    protected Date deliveryDate;

    /**
     * (.comデビット決済取引番号)
     */
    protected String comDebitNumber;

    /**
     * (保証金区分)
     */
    protected String guaranteeDiv;

    /**
     * (出金申込区分)
     */
    protected String paymentApplicationDiv;

    /**
     * (識別コード)
     */
    protected String orderRequestNumber;

    /**
     * (注文経路区分)<BR>
     * 1：call <BR>
     * 2：PC <BR>
     * 3：スリングショット <BR>
     * 4：i-mode <BR>
     * 5：vodafone <BR>
     * 6；au <BR>
     * 7：スリングショット（無料） <BR>
     * 9：HOST <BR>
     * A：管理者 <BR>
     * B：保証金自動振替バッチ<BR>
     */
    protected String orderRootDiv;

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransOrderUpdateInterceptor.class);
    
    /**
     * (入出金注文更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、引数の入出金注文内容をプロパティにセットする。
     * @@param l_cashTransOrderSpec - (入出金注文内容オブジェクト)
     * @@return WEB3AioCashTransOrderUpdateInterceptor
     * @@roseuid 40E2A42B0272
     */
    public WEB3AioCashTransOrderUpdateInterceptor(WEB3AioNewOrderSpec l_cashTransOrderSpec)
    {
        final String STR_METHOD_NAME = "WEB3AioCashTransOrderUpdateInterceptor" +
            "(WEB3AioNewOrderSpec l_cashTransOrderSpec)";
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
     * 　@プロパティの内容より、<BR>
     * パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。 <BR>
     * <BR>
     * １）注文経路区分について<BR>
     *    以下のように設定する。<Br>
     * <BR>
     *    注文単位.注文種別 = 1001(出金注文) の場合、<BR>
     *    セッションより取得した同項目の値をセットする。<BR>
     *    セッションより取得できない場合は、2（PC）をセットする。<BR>
     *<BR>
     * ２）MQステータスについて<BR> 
     *   以下のように設定する。 <BR>
     *<BR>
     *   注文単位.注文種別 = 1001(出金注文) の場合、0（未送信）をセットする。 <BR>
     *   注文単位.注文種別 = 1002(入金注文) の場合、1（送信済み）をセットする。<BR> 
     *<BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「出金申込_注文単位テーブル仕様.xls」参照。<BR>
     * 「注文結果通知_注文単位テーブル仕様.xls」参照。 <BR>
     * @@param l_updateType - ((更新タイプ)<BR>)<BR>
     * INSERTまたは、UPDATE<BR>
     * <BR>
     * （OrderManagerPersistenceTypeにて定数定義。）
     * @@param l_process - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 注文単位のパラメータクラス
     * @@return AioOrderUnitParams
     * @@roseuid 40E2A4350204
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType l_updateType," +
                "OrderManagerPersistenceContext l_process, AioOrderUnitParams l_orderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            log.debug("注文単位ParamsがNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }     

        //注文カテゴリの設定を行う。
        l_orderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);  
        
        //発注日
        l_orderUnitParams.setBizDate(WEB3DateUtility.formatDate(
            this.bizDate, "yyyyMMdd"));
        
        //受渡日
        l_orderUnitParams.setDeliveryDate(this.deliveryDate);
        
        // 1)受注日時
        l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
        // 2)扱者コード(SONAR)の設定を行う。
        long l_lngAccountId = l_orderUnitParams.getAccountId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();            
        MainAccount l_acc = null;
        try
        {
            l_acc = l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("_____not MainAccount", l_ex); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_acc.getDataSourceObject();
        log.debug("MainAccountRow" + l_mainAccountRow);
        
        if(l_mainAccountRow == null)
        {
            log.error("___not MainAccountRow___");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
        l_orderUnitParams.setSonarTraderCode(l_strSonarTraderCode);
        
        
        l_orderUnitParams.setOrderRequestNumber(this.orderRequestNumber);

        // 4).comデビット決済取引番号の設定を行う。
        l_orderUnitParams.setComDebitNumber(this.comDebitNumber);
        
        // 5)保証金区分の設定を行う。
        l_orderUnitParams.setGuaranteeDiv(this.guaranteeDiv);
        
        // 6)出金申込区分の設定を行う。
        l_orderUnitParams.setPaymentApplicationDiv(this.paymentApplicationDiv);
        
        // 7)注文取消区分の設定を行う。
        l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        
        // 8)注文経路区分の設定を行う。
        //※注文経路区分について
        //以下のように設定する。
        //注文単位.注文種別 = 1001(出金注文) の場合、セッションより取得した同項目の値をセットする。
        //セッションより取得できない場合は、2（PC）をセットする。 
        if (OrderTypeEnum.CASH_OUT.equals(l_orderUnitParams.getOrderType()))
        {
            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            
            String l_strRootDiv = null;            
            boolean l_blnIsLoginRootDiv = true;
            try
            {
                l_strRootDiv = l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            }
            catch (IllegalSessionStateException l_ex)
            {
                l_blnIsLoginRootDiv = false;
            }

            if(l_blnIsLoginRootDiv && l_strRootDiv != null)
            {
                l_orderUnitParams.setOrderRootDiv(l_strRootDiv);
            }
            else
            {
                l_orderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.PC);
            }            
            
            // 10)MQステータスの設定を行う。
            //注文単位.注文種別 = 1001(出金注文) の場合、0（未送信）をセットする。
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
        }
        
        // 10)MQステータスの設定を行う。
        //注文単位.注文種別 = 1002(入金注文) の場合、1（送信済み）をセットする
        //セッションより注文経路区分を設定する
        else if (OrderTypeEnum.CASH_IN.equals(l_orderUnitParams.getOrderType()))
        {            
            l_orderUnitParams.setOrderRootDiv(this.orderRootDiv);
            
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
        }
        
        // 9)注文エラー理由コードの設定を行う。
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
   
        log.exiting(STR_METHOD_NAME); 
    
        return l_orderUnitParams;
    }
    
    /**
     * (set.comデビット決済取引番号)<BR>
     * .comデビット決済取引番号をプロパティにセットする。
     * @@param l_strComDebitNumber - (.comデビット決済取引番号)
     * @@roseuid 40F511520091
     */
    public void setComDebitNumber(String l_strComDebitNumber) 
    {
        final String STR_METHOD_NAME = "setComDebitNumber(String l_strComDebitNumber)";
        log.entering(STR_METHOD_NAME);
  
        this.comDebitNumber = l_strComDebitNumber;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set発注日)<BR>
     * 発注日をセットする。
     * @@param l_datBizDate - (発注日)
     * @@roseuid 40F631EE03B0
     */
    public void setBizDate(Date l_datBizDate) 
    {
        final String STR_METHOD_NAME = "setBizDate(Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
   
        this.bizDate = l_datBizDate;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (setDeliveryDate)<BR>
     * 受渡日をセットする。
     * @@param l_strDeliveryDate - (受渡日)
     * @@roseuid 4125B3600055
     */
    public void setDeliveryDate(Date l_strDeliveryDate) 
    {
        final String STR_METHOD_NAME = "setDeliveryDate(Date l_strDeliveryDate)";
        log.entering(STR_METHOD_NAME);
  
        this.deliveryDate = l_strDeliveryDate;
        
        log.exiting(STR_METHOD_NAME);          
    }

    /**
     * (setGuaranteeDiv)<BR>
     * 保証金区分をセットする。
     * @@param l_strGuaranteeDiv - (保証金区分)
     * @@roseuid 4125B3600055
     */
    public void setGuaranteeDiv(String l_strGuaranteeDiv) 
    {
        final String STR_METHOD_NAME = "setGuaranteeDiv(String l_strGuaranteeDiv)";
        log.entering(STR_METHOD_NAME);
        
        this.guaranteeDiv = l_strGuaranteeDiv;
        
        log.exiting(STR_METHOD_NAME);          
    }

    /**
     * (set出金申込区分)<BR>
     * 保証金区分をセットする。
     * @@param l_strPaymentApplicationDiv - (出金申込区分)
     * @@roseuid 4125B3600055
     */
    public void setPaymentApplicationDiv(String l_strPaymentApplicationDiv) 
    {
        final String STR_METHOD_NAME = "setPaymentApplicationDiv(String l_strPaymentApplicationDiv)";
        log.entering(STR_METHOD_NAME);
        
        this.paymentApplicationDiv = l_strPaymentApplicationDiv;
        
        log.exiting(STR_METHOD_NAME);          
    }

    /**
     * (set識別コード)<BR>
     * 識別コードをセットする。
     * @@param l_strOrderRequestNumber - (識別コード)
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber) 
    {
        final String STR_METHOD_NAME = "setOrderRequestNumber(String l_strOrderRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        this.orderRequestNumber = l_strOrderRequestNumber;
        
        log.exiting(STR_METHOD_NAME);          
    }
    
    /**
     * (set注文経路区分)<BR>
     * 注文経路区分をセットする。<BR>
     * @@param l_strOrderRootDiv - (注文経路区分)
     */
    public void setOrderRootDiv(String l_strOrderRootDiv)
    {
        final String STR_METHOD_NAME = "setOrderRootDiv(String l_strOrderRootDiv)";
        log.entering(STR_METHOD_NAME);
        
        this.orderRootDiv = l_strOrderRootDiv;
        
        log.exiting(STR_METHOD_NAME); 

    }
}@
