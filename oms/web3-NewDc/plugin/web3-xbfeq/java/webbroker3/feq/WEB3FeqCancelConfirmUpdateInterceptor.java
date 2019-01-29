head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCancelConfirmUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式取消確定更新インタセプタ(WEB3FeqCancelConfirmUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 王亞洲 (中訊) 新規作成
                 : 2005/07/25 芦露(中訊) レビュー
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式取消確定更新インタセプタ)<BR>
 * 外国株式取消確定更新インタセプタクラス<BR>
 *
 * @@author 王亞洲
 * @@version 1.0
 */
public class WEB3FeqCancelConfirmUpdateInterceptor extends WEB3FeqUpdateInterceptor
{
    /**
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqCancelConfirmUpdateInterceptor.class);

    /**
     * (外国株式取消確定更新イベントインタセプタ)<BR>
     * コンストラクタ<BR>
     * @@roseuid 429D814202CE
     */
    public WEB3FeqCancelConfirmUpdateInterceptor()
    {

    }

    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     *  <BR>
     * １）　@注文（ﾍｯﾀﾞ）テーブル更新<BR>
     * 　@super.mutate(OrderManagerPersistenceType,<BR>
     * 　@OrderManagerPersistenceContext, FeqOrderUnitParams)<BR>
     * 　@をコールする。<BR>
     * <BR>
     * ２）　@注文単位テーブル更新<BR>
     * 　@注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * 項目設定内容は、<BR>
     * DB更新仕様「取消確定_外株注文単位テーブル.xls」参照。<BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * 更新タイプ<BR>
     * @@param l_context - (処理)<BR>
     * 処理<BR>
     * @@param l_feqOrderUnitParams - (注文単位行)<BR>
     * 注文単位行（：注文単位Params）<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams
     * @@roseuid 429D814202ED
     */
    public FeqOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderUnitParams l_feqOrderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if(l_feqOrderUnitParams == null)
        {
            log.debug(" 注文単位Params値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        // １）　@注文（ﾍｯﾀﾞ）テーブル更新
        l_feqOrderUnitParams = super.mutate(l_updateType, l_context, l_feqOrderUnitParams);
        // ２）　@注文単位テーブル更新
        // 注文訂正・取消区分
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        FeqOrderManager l_orderManager =
            (FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        FeqOrderUnit l_orderUnit =  (FeqOrderUnit)l_orderManager.toOrderUnit(l_feqOrderUnitParams);
        // this.isUnexecuted( ) == falseの場合は、”一部取消完了”
        // this.isUnexecuted( ) == trueの場合は、”全部取消完了”
        boolean l_blnIsUnExecuted = l_orderUnit.isUnexecuted();
        l_feqOrderUnitParams.setModifyCancelType(
            (!l_blnIsUnExecuted) ? WEB3ModifyCancelTypeDef.PART_CANCELED : WEB3ModifyCancelTypeDef.CANCELED);

        // 注文エラー理由コード
        l_feqOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnitParams;
    }
}
@
