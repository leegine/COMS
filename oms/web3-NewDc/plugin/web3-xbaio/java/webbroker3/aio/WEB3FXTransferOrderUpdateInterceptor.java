head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTransferOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替注文更新インタセプタ(WEB3FXTransferOrderUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 屈陽 (中訊) 新規作成 
                 : 2006/4/25 相馬　@和明(SCS) 障害票 U02830 対応
Revesion History : 2009/03/16 柴双紅 (中訊) ＤＢ更新仕様No.209、211、224、225
*/

package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
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
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX振替注文更新インタセプタ) <BR>
 * FX振替注文更新インタセプタクラス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTransferOrderUpdateInterceptor extends
    WEB3AioCashTransUpdateInterceptor
{
    /**
    * ログユーティリティ
    */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FXTransferOrderUpdateInterceptor.class);
    
    /**
     * (入出金注文内容) <BR>
     * 入出金注文内容
     */
    private WEB3AioNewOrderSpec cashTransOrderSpec;

    /**
     * (発注日) <BR>
     * 発注日
     */
    private Date bizDate;

    /**
     * (受渡日) <BR>
     * 受渡日
     */
    private Date deliveryDate;

    /**
     * (識別コード)<BR> 
     * 識別コード
     */
    private String orderRequestNumber;

    /**
     * (MQステータス) <BR>
     * MQステータス
     */
    private String mqStatus;

    /**
     * @@roseuid 41E7693503A9
     */
    public WEB3FXTransferOrderUpdateInterceptor()
    {
    }

    /**
     * (FX振替注文更新インタセプタ) <BR>
     * コンストラクタ <BR>
     * <BR>
     * インスタンスを生成し、 <BR>
     * 引数の入出金注文内容をプロパティにセットする。
     * 
     * @@param l_cashTransOrderSpec - 入出金注文内容オブジェクト
     * @@roseuid 41C11C6101F2
     */
    public WEB3FXTransferOrderUpdateInterceptor(
        WEB3AioNewOrderSpec l_cashTransOrderSpec)
    {
        //引数の入出金注文内容をプロパティにセットする
        this.cashTransOrderSpec = l_cashTransOrderSpec;        
    }

    /** [更新値設定]
     * （mutateメソッドの実装） 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 「FXから振替_注文単位テーブル.xls」参照。<BR>
     * <BR>
     * @@param l_updateType - INSERTまたは、UPDATE
     * （OrderManagerPersistenceTypeにて定数定義。）
     * @@param l_process - （OrderManagerPersistenceContextにて定数定義）
     * @@param l_orderUnitParams - 注文単位のパラメータクラス
     * @@return AioOrderUnitParams
     * @@roseuid 41BEE5C600D7
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process,
        AioOrderUnitParams l_orderUnitParams)
    {
        String l_strMethodName = 
            "mutate(" +
            "OrderManagerPersistenceType l_updateType, " +
            "OrderManagerPersistenceContext l_process, " +
            "AioOrderUnitParams l_orderUnitParams)";
        log.entering(l_strMethodName);
        
        if (l_orderUnitParams == null)
        {
            log.debug("注文単位ParamsがNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);            
        }
        
        //1) 注文カテゴリ(15：為替保証金振替)   ---------- >>  WEB3QuestionDivDef
        l_orderUnitParams.setOrderCateg(OrderCategEnum.FX);
        
        //2) 発注日(インタセプタ.発注日)
        l_orderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.bizDate, "yyyyMMdd"));

        //3) 受渡日(インタセプタ.受渡日)
        l_orderUnitParams.setDeliveryDate(this.deliveryDate);
        
        //4) 税区分(デフォルト：0)
        l_orderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
        
        //5) 受注日時
        //ThreadLocalSystemAttributesRegistry.getAttributes(”xblocks.gtl.attributes.systemtimestamp”)の戻り値
        l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
        //6) 扱者コード（SONAR）(顧客.扱者コード)
        
        //a> accountId
        long l_lngAccountId = l_orderUnitParams.getAccountId();
        
        //b> FinApp, AccountManager, MainAccount(Row)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();            
        MainAccount l_mainAccount;
        try
        {
            //throw NotFoundException
            l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            
            //c> MainAccountRow
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
            //d> 顧客.扱者コード
            String l_strTraderCode = l_mainAccountRow.getSonarTraderCode();
        
            //set扱者コード
            l_orderUnitParams.setSonarTraderCode(l_strTraderCode);        
        }
        catch (NotFoundException l_ex)
        {
            log.error("__該当する顧客オブジェクトがありません__", l_ex); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        
        //7) 識別コード(インタセプタ.識別コード)
        l_orderUnitParams.setOrderRequestNumber(this.orderRequestNumber);
        
        //8) .comデビット決済取引番号(null)
        l_orderUnitParams.setComDebitNumber(null);
        
        //9) 保証金区分(null)
        l_orderUnitParams.setGuaranteeDiv(null);
        
        //10) 出金申込区分(null)
        l_orderUnitParams.setPaymentApplicationDiv(null);
        
        //11) 注文取消区分(0：初期値)
        l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        
        //12) 注文経路区分(セッションより取得した同項目の値)
        OpLoginSecurityService l_opLoginSecurityService = 
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_strRootDiv = 
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        
        //注文経路区分が取得できない、かつ注文種別が為替保証金振替注文（為替保証金から預り金）の 場合は、管理者を設定する
        if (l_strRootDiv == null && OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.stringValue().equals(this.cashTransOrderSpec.getOrderTypeEnum().stringValue()))
        {
            l_strRootDiv = WEB3OrderRootDivDef.ADMIN;
        }
            
        l_orderUnitParams.setOrderRootDiv(l_strRootDiv); 
        
        //13) 注文エラー理由コード(0000：正常)
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        //14) MQステータス(インタセプタ.MQステータス)
        l_orderUnitParams.setMqStatus(this.mqStatus);

        //摘要コード(入出金注文内容.摘要コード)
        l_orderUnitParams.setRemarkCode(this.cashTransOrderSpec.getRemarkCode());

        //摘要名(入出金注文内容.摘要名)
        l_orderUnitParams.setRemarkName(this.cashTransOrderSpec.getRemarkName());

        log.exiting(l_strMethodName);
        
        return l_orderUnitParams;
    }

    /**
     * (set発注日) <BR>
     * 発注日をセットする。
     * 
     * @@param l_datOrderBizDate - 発注日
     * @@roseuid 41C266E7009E
     */
    public void setOrderBizDate(Date l_datOrderBizDate)
    {
        String l_strMethodName = "setOrderBizDate(Date l_datOrderBizDate)";
        log.entering(l_strMethodName);
       
        this.bizDate = l_datOrderBizDate; 
        
        log.exiting(l_strMethodName);     
    }

    /**
     * (get発注日) <BR>
     * 発注日を取得する。
     * 
     * @@return Date
     * @@roseuid 41C2684802F0
     */
    public Date getOrderBizDate()
    {
        return this.bizDate;
    }

    /**
     * (set受渡日) <BR>
     * 受渡日をセットする。
     * 
     * @@param l_datDeliveryDate - 受渡日
     * @@roseuid 41C7D64B02C2
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        String l_strMethodName = "setDeliveryDate(Date l_datDeliveryDate)";
        log.entering(l_strMethodName);
        
        this.deliveryDate = l_datDeliveryDate; 
        
        log.exiting(l_strMethodName);     
    }

    /**
     * (get受渡日) <BR>
     * 受渡日を取得する。
     * 
     * @@return Date
     * @@roseuid 41C7D64B02E2
     */
    public Date getDeliveryDate()
    {
        return this.deliveryDate;
    }

    /**
     * (set識別コード) <BR>
     * 識別コードをセットする。
     * 
     * @@param l_strOrderRequestNumber - 識別コード
     * @@roseuid 41C266E700B0
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber)
    {
        String l_strMethodName = "setOrderRequestNumber(String l_strOrderRequestNumber)";
        log.entering(l_strMethodName);
        
        this.orderRequestNumber = l_strOrderRequestNumber; 
        
        log.exiting(l_strMethodName);     
    }

    /**
     * (get識別コード) <BR>
     * 識別コードを取得する。
     * 
     * @@return String
     * @@roseuid 41C268600169
     */
    public String getOrderRequestNumber()
    {
        return this.orderRequestNumber;
    }

    /**
     * (setMQステータス) <BR>
     * MQステータスをセットする。
     * 
     * @@param l_strMQStatus - MQステータス
     * @@roseuid 41C26A9A010B
     */
    public void setMQStatus(String l_strMQStatus)
    {
        String l_strMethodName = "setMQStatus(String l_strMQStatus)";
        log.entering(l_strMethodName);
        
        this.mqStatus = l_strMQStatus; 
        
        log.exiting(l_strMethodName);     
    }

    /**
     * (getMQステータス) <BR>
     * MQステータスを取得する。
     * 
     * @@return String
     * @@roseuid 41C26A9A011B
     */
    public String getMQStatus()
    {
        return this.mqStatus;
    }
}@
