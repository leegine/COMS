head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCancelOrderConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文取消確定インタセプタ(WEB3EquityCancelOrderConfirmInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/26 孟 東 (中訊) mutateメソッドの実装完成
                   2004/9/22 盧法@旭 (中訊) mutate メソッドの
                   2004/12/08 水落（SRA） 残案件対応　@更新値設定(OrderUnit)メソッド・・「注文数量」カスタマイズの削除
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文取消確定インタセプタ）。<BR>
 * <BR>
 * 以下のケースで使用する、注文データ更新仕様カスタマイズ用のクラス。<BR>
 * 市場閉局後～注文繰越までの間の、市場送信済注文に対する取消を、<BR>
 * 取消が市場に受け入れられたものとして確定する。<BR>
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityCancelOrderConfirmInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{

    /**
     * (ログユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityCancelOrderConfirmInterceptor.class);

    /**
     * (株式注文取消確定インタセプタ)<BR>
     * コンストラクタ。<BR>
     * @@return webbroker3.equity.WEB3EquityOrderCancelConfirmInterceptor
     * @@roseuid 407A17C10140
     */
    public WEB3EquityCancelOrderConfirmInterceptor()
    {

    }

    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * １） 拡張項目セット<BR>
     * （注文状態が”発注済み（取消注文）：CANCELLED”に該当する場合のみ、<BR>
     * 処理を実施する。<BR>
     * 　@以外は、引数をそのまま返却し処理を終了する）<BR>
     * 　@注文単位Paramsの値を「発注済（取消注文）」の状態に設定し返却する。<BR>
     * <BR>
     * 更新内容は、「注文取消_株式注文単位テーブル.xls」の<BR>
     * 「（株式注文取消 DB更新内容[取消確定]）株式注文単位テーブル」シート参照。<BR>
     * <BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。 <BR>
     * EqTypeOrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR> 
     * @@param l_manage - (処理)<BR>
     * （EqTypeOrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 4143CD7B0154
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_manage,
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 発注状態が”発注済み（取消注文）：CANCELLED”に該当する場合のみ処理を実施。
        if (OrderStatusEnum.CANCELLED.equals(l_orderUnitParams.getOrderStatus()))
        {

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            OrderManager l_orderMgr = l_tradingMod.getOrderManager();

            OrderUnit l_orderUnit = null;
            try 
            {
                l_orderUnit =
                    l_orderMgr.getOrderUnit(l_orderUnitParams.getOrderUnitId());
            } 
            catch (NotFoundException l_nfe) 
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            //注文訂正・取消区分をセット        
            if (l_orderUnit.isUnexecuted())
            {
                //全部取消完了3
                log.debug("isUnexecuted true: executed_quantityがNullの場合");
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.CANCELED);
            }
            else
            {
                //取消中
                log.debug("false: それ以外の場合");
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.PART_CANCELED);
            }
            
            //市場から確認済みの概算受渡代金をセット
            l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
            
            //注文エラー理由コードをセット0000
            l_orderUnitParams.setErrorReasonCode(
                WEB3ErrorReasonCodeDef.NORMAL);
            log.debug("注文エラー理由コード" +l_orderUnitParams.getErrorReasonCode());
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
}
@
