head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.29.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FEqConTransferOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株振替注文更新インタセプタクラス(WEB3FEqConTransferOrderUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/17 韋念瓊 (中訊) 新規作成       
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3AioPaymentApplicationDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外株振替注文更新インタセプタ)
 * 外株振替注文更新インタセプタクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FEqConTransferOrderUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
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
     * @@roseuid 42363FCD008C
     */
    public WEB3FEqConTransferOrderUpdateInterceptor() 
    {
     
    }
    
    /**
     * (外株振替注文更新インタセプタ)<BR>
     * コンストラクタ <BR>
     * <BR>
     * インスタンスを生成し、引数の入出金注文内容をプロパティにセットする。
     * @@param l_cashTransOrderSpec - 入出金注文内容オブジェクト
     * @@roseuid 41E4FF0100DC
     */
    public WEB3FEqConTransferOrderUpdateInterceptor(WEB3AioNewOrderSpec l_cashTransOrderSpec) 
    {
        final String STR_METHOD_NAME = "WEB3FEqConTransferOrderUpdateInterceptor(" +
                "WEB3AioNewOrderSpec l_cashTransOrderSpec)";
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
     * （mutateメソッドの実装） <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。 <BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「外株口座への振替_注文単位テーブル.xls」参照。<BR>
     * @@param l_updateType - INSERTまたは、UPDATE<BR>
     * <BR>
     * （OrderManagerPersistenceTypeにて定数定義。）<BR>
     * 
     * @@param l_process - （OrderManagerPersistenceContextにて定数定義）
     * @@param 注文単位Params - 注文単位のパラメータクラス
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
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }         
        try
        {
            // 1)注文カテゴリ = 16：外国株式振替
            l_orderUnitParams.setOrderCateg(OrderCategEnum.FEQ_TRANSFER);
            
            // 2)発注日 = インタセプタ.発注日
            l_orderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.bizDate,
                "yyyyMMdd"));
            
            // 3)受渡日 = インタセプタ.受渡日
            l_orderUnitParams.setDeliveryDate(this.deliveryDate);
            
            // 4)税区分 = デフォルト：0
            l_orderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            
            // 5)受注日時 = ThreadLocalSystemAttributesRegistry.getAttributes(
            //  ”xblocks.gtl.attributes.systemtimestamp”)の戻り値
            l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
            // 6)扱者コード(SONAR) = 顧客.扱者コード
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
            
            // 10)出金申込区分 = "FT"
            l_orderUnitParams.setPaymentApplicationDiv(WEB3AioPaymentApplicationDivDef.FEQ_TRANSFER);
            
            // 11)注文取消区分 = 0:初期値
            l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            
            // 12)注文経路区分 = セッションより取得した同項目の値
            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) 
                Services.getService(OpLoginSecurityService.class);
            
            String l_strRootDiv = l_opLoginSec.getSessionProperty(
                    WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            
            l_orderUnitParams.setOrderRootDiv(l_strRootDiv);
            
            // 13)注文エラー理由コード = 0000：正常
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            // 14)MQステータス = 0：未送信
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
        
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
     * @@param l_datOrderBizDate - 発注日
     * @@roseuid 41E4FF01010A
     */
    public void setOrderBizDate(Date l_datOrderBizDate) 
    {
        bizDate = l_datOrderBizDate;
    }
    
    /**
     * (get発注日)<BR>
     * 発注日を取得する。
     * @@return Date
     * @@roseuid 41E4FF01011A
     */
    public Date getOrderBizDate() 
    {
        return bizDate;
    }
    
    /**
     * (set受渡日)<BR>
     * 受渡日をセットする。
     * @@param l_datDeliveryDate - 受渡日
     * @@roseuid 41E4FF010139
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        deliveryDate = l_datDeliveryDate;
    }
    
    /**
     * (get受渡日)<BR>
     * 受渡日を取得する。
     * @@return Date
     * @@roseuid 41E4FF010159
     */
    public Date getDeliveryDate() 
    {
        return deliveryDate;
    }
    
    /**
     * (set識別コード)<BR>
     * 識別コードをセットする。
     * @@param l_strOrderRequestNumber - 識別コード
     * @@roseuid 41E4FF010178
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber) 
    {
        orderRequestNumber = l_strOrderRequestNumber;
    }
    
    /**
     * (get識別コード)<BR>
     * 識別コードを取得する。
     * @@return String
     * @@roseuid 41E4FF010197
     */
    public String getOrderRequestNumber() 
    {
        return orderRequestNumber;
    }
}
@
