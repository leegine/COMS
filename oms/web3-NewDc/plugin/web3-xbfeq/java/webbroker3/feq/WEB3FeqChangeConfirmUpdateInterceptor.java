head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeConfirmUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式訂正確定更新インタセプタ(WEB3FeqChangeConfirmUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 王煜 (中訊) 新規作成
                 : 2005/07/28 呉艶飛(中訊) レビュー
*/
package webbroker3.feq;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式訂正確定更新インタセプタ)<BR>
 * 外国株式訂正確定更新インタセプタクラス<BR>
 * 
 * @@author 王煜(中訊)
 * @@version 1.0 
 */
public class WEB3FeqChangeConfirmUpdateInterceptor 
        extends WEB3FeqUpdateInterceptor
{

    /**
     * (ログユーティリティ)<BR>
     */
    protected static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqChangeConfirmUpdateInterceptor.class);
    
    /**
     * (外国株式訂正確定更新イベントインタセプタ)<BR>
     * コンストラクタ<BR>
     * @@roseuid 429D71260251
     */
    public WEB3FeqChangeConfirmUpdateInterceptor()
    {

    }

    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * <BR>
     * １）　@注文（ﾍｯﾀﾞ）テーブル更新<BR>
     * 　@super.mutate(OrderManagerPersistenceType,<BR>
     * 　@OrderManagerPersistenceContext, FeqOrderUnitParams)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ２）　@注文単位テーブル更新<BR>
     * 　@注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * 項目設定内容は、<BR>
     * DB更新仕様「訂正確定_外株注文単位テーブル.xls」参照。<BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * 更新タイプ<BR>
     * @@param l_context - (処理)<BR>
     * 処理<BR>
     * @@param l_orderUnitParams - (注文単位行)<BR>
     * 注文単位行（：注文単位Params）<BR>
     * @@return FeqOrderUnitParams
     * @@roseuid 429D71260260
     */
    public FeqOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType,"
            + "OrderManagerPersistenceContext, " 
            + "FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitParams == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        

        //１）　@注文（ﾍｯﾀﾞ）テーブル更新
        super.mutate(
            l_updateType,
            l_context,
            l_orderUnitParams);

        //２）　@注文単位テーブル更新
        WEB3FeqOrderUnit l_orderUnit = new WEB3FeqOrderUnit(l_orderUnitParams);
        
        // 注文状態＝”発注済み（変更注文）”を設定
        l_orderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        
        // 注文訂正・取消区分
        // this.isUnexecuted( ) == trueの場合は、”全部訂正完了”
        if (l_orderUnit.isUnexecuted())
        {
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGED);
        }
        // this.isUnexecuted( ) == falseの場合は、”一部訂正完了”
        else
        {
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
        }
        
        //市場から確認済みの注文単価
        l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.price);
        
        //市場から確認済みの概算受渡代金
        l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.estimated_price);
        
        //市場から確認済みの概算受渡代金（外貨）
        l_orderUnitParams.setConfirmedFEstimatedPrice(l_orderUnitParams.f_estimated_price);
        
        //市場から確認済みの執行条件
        l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.execution_condition_type);
        
        //注文エラー理由コード
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
}
@
