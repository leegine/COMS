head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.36.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashoutTradingPowerCheckUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金余力チェック更新インタセプタ(WEB3AioCashoutTradingPowerCheckUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 周勇 (中訊) 新規作成     
                   2004/10/23 于美麗 (中訊) レビュー              
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (出金余力チェック更新インタセプタ)<BR>
 * 出金余力チェック更新インタセプタクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashoutTradingPowerCheckUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutTradingPowerCheckUpdateInterceptor.class); 
    
    /**
     * (出金余力チェック更新インタセプタ)<BR>
     * コンストラクタ
     * @@return WEB3AioCashoutTradingPowerCheckUpdateInterceptor
     * @@roseuid 412DAE3E02AD
     */
    public WEB3AioCashoutTradingPowerCheckUpdateInterceptor() 
    {

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
     * 「出金申込問合せ_注文単位テーブル仕様.xls」参照。 <BR>
     * @@param l_updateType - ((更新タイプ)<BR>)<BR>
     * INSERTまたは、UPDATE<BR>
     * <BR>
     * （OrderManagerPersistenceTypeにて定数定義。）<BR>
     * @@param l_process - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 注文単位のパラメータクラス
     * @@return AioOrderUnitParams
     * @@roseuid 412DAE3E02AE
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
        // 1)注文状態の設定を行う。
        l_orderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
        
        // 2)注文取消区分の設定を行う。
        l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.CANCELED);
        
        // 3)注文エラー理由コードの設定を行う。
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
}
@
