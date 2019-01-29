head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.27.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecurityTransferForceUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替強制更新インタセプタ(WEB3AioSecurityTransferForceUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 屈陽 (中訊) 新規作成 
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
 * (証券振替強制更新インタセプタ)<BR>
 * 証券振替強制更新インタセプタクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferForceUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{
    /**
    * ログユーティリティ
    */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferForceUpdateInterceptor.class);
    
    /**
     * (入出金注文内容)<BR>
     * 入出金注文内容
     */
    protected WEB3AioNewOrderSpec cashTransOrderSpec;
    
    /**
     * (発注日)<BR>
     * 発注日
     */
    protected Date bizDate;
    
    /**
     * (受渡日)<BR>
     * 受渡日
     */
    protected Date deliveryDate;
    
    /**
     * (税区分)<BR>
     * 税区分
     */
    protected TaxTypeEnum taxType;
    
    /**
     * (識別コード)<BR>
     * 識別コード
     */
    protected String orderRequestNumber;
    
    /**
     * @@roseuid 41B0458B01F4
     */
    public WEB3AioSecurityTransferForceUpdateInterceptor() 
    {
     
    }
    
    /**
     * (証券振替強制更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、引数の入出金注文内容をプロパティにセットする。
     * @@param l_securityTransferForceUpdateInterceptor - 入出金注文内容オブジェクト
     * @@return webbroker3.aio.WEB3AioSecurityTransferForceUpdateInterceptor
     * @@roseuid 416CAF1A02D0
     */
    public WEB3AioSecurityTransferForceUpdateInterceptor(WEB3AioNewOrderSpec l_aioNewOrderSpec) 
    {      
        //インスタンスを生成し、引数の入出金注文内容をプロパティにセットする。
        this.cashTransOrderSpec = l_aioNewOrderSpec; 
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
     * 「証券振替強制_注文単位テーブル仕様.xls」参照。
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE<BR>
     * <BR>
     * （OrderManagerPersistenceTypeにて定数定義。）
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）
     * @@param l_aioOrderUnitParams - (注文単位Params)<BR>
     * 注文単位のパラメータクラス
     * @@return AioOrderUnitParams
     * @@roseuid 416CAF1A02D2
     */
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType, 
        OrderManagerPersistenceContext l_orderManagerPersistenceContext, 
        AioOrderUnitParams l_aioOrderUnitParams) 
    {
        String l_strMethodName = 
            "mutate(" +
            "OrderManagerPersistenceType l_orderManagerPersistenceType, " +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "AioOrderUnitParams l_aioOrderUnitParams)";
        log.entering(l_strMethodName);
        
        if (l_aioOrderUnitParams == null)
        {
            log.debug("注文単位ParamsがNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);            
        }
        
        //1) 注文カテゴリ(14：証券振替)
        l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET_TRANSFER);
        
        //2) 発注日(インタセプタ.発注日)
        l_aioOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.bizDate, "yyyyMMdd"));
                    
        //3) 受渡日(インタセプタ.受渡日)
        l_aioOrderUnitParams.setDeliveryDate(this.deliveryDate);
        
        //4) 税区分(インタセプタ.税区分)
        l_aioOrderUnitParams.setTaxType(this.taxType);
        
        //5) 受注日時
        //ThreadLocalSystemAttributesRegistry.getAttributes(”xblocks.gtl.attributes.systemtimestamp”)の戻り値
        l_aioOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
        //6) 扱者コード（SONAR）(顧客.扱者コード)
        
        //a> accountId
        long l_lngAccountId = l_aioOrderUnitParams.getAccountId();
        
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
            String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
        
            //set扱者コード
            l_aioOrderUnitParams.setSonarTraderCode(l_strSonarTraderCode);        
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
        l_aioOrderUnitParams.setOrderRequestNumber(orderRequestNumber);
        
        //8) .comデビット決済取引番号(null)
        l_aioOrderUnitParams.setComDebitNumber(null);
        
        //9) 保証金区分(null)
        l_aioOrderUnitParams.setGuaranteeDiv(null);
        
        //10) 出金申込区分(null)
        l_aioOrderUnitParams.setPaymentApplicationDiv(null);
        
        //11) 注文取消区分(0：初期値)
        l_aioOrderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        
        //12) 注文経路区分(9：HOST)
        l_aioOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST); 
        
        //13) 注文エラー理由コード(0000：正常)
        l_aioOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        //14) MQステータス(1:送信済)
        l_aioOrderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
        
        log.exiting(l_strMethodName);
        
        return l_aioOrderUnitParams;
    }
    
    /**
     * (set発注日)<BR>
     * 発注日をセットする。
     * @@param l_datOrderBizDate - 発注日
     * @@roseuid 416CAF1A02D6
     */
    public void setOrderBizDate(Date l_datOrderBizDate) 
    {
        String l_strMethodName = "setOrderBizDate(Date l_datOrderBizDate)";
        log.entering(l_strMethodName);
       
        this.bizDate = l_datOrderBizDate; 
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set受渡日)<BR>
     * 受渡日をセットする。
     * @@param l_datDeliveryDate - 受渡日
     * @@roseuid 416CAF1A02E0
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        String l_strMethodName = "setDeliveryDate(Date l_datDeliveryDate)";
        log.entering(l_strMethodName);
        
        this.deliveryDate = l_datDeliveryDate; 
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set識別コード)<BR>
     * 識別コードをセットする。
     * @@param l_strOrderRequestNumber - 識別コード
     * @@roseuid 416CAF1A02E2
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber) 
    {
        String l_strMethodName = "setOrderRequestNumber(String l_strOrderRequestNumber)";
        log.entering(l_strMethodName);
        
        this.orderRequestNumber = l_strOrderRequestNumber; 
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set税区分)<BR>
     * 税区分をセットする。
     * @@param l_taxType - 税区分
     * @@roseuid 416CB9CB02C0
     */
    public void setTaxType(TaxTypeEnum l_taxType) 
    {
        String l_strMethodName = "setTaxType(TaxTypeEnum l_taxType)";
        log.entering(l_strMethodName);        
        
        this.taxType = l_taxType;
        
        log.exiting(l_strMethodName);
    }
}
@
