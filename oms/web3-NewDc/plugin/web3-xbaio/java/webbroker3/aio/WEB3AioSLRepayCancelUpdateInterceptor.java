head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.21.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSLRepayCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済取消更新インタセプタ(WEB3AioSLRepayCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/12 何文敏 (中訊) 新規作成 仕様変更・モデルNo.759,DB更新仕様 150
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券担保ローン返済取消更新インタセプタ)<BR>
 * 証券担保ローン返済取消更新インタセプタクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AioSLRepayCancelUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayCancelUpdateInterceptor.class);

    /**
     * (証券担保ローン返済取消更新インタセプタ)<BR>
     * コンストラクタ <BR>
     * <BR>
     * @@roseuid 46DE559F014C
     */
    public WEB3AioSLRepayCancelUpdateInterceptor()
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
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値<BR>
     * をセットし、返却する。<BR>
     * <BR>
     * 【ｘTrae】補足資料.DB更新<BR>
     * 「証券担保ローン返済取消_注文単位テーブル.xls」参照。<BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE<BR>
     * <BR>
     * （OrderManagerPersistenceTypeにて定数定義。）<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_aioOrderUnitParams - (注文単位Params)<BR>
     * 注文単位のパラメータクラス<BR>
     * <BR>
     * @@return AioOrderUnitParams
     * @@roseuid 46DE55A300CC
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        AioOrderUnitParams l_aioOrderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, AioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_aioOrderUnitParams == null)
        {
            log.debug("注文単位ParamsがNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // 証券担保ローン返済取消_注文単位テーブル.xls
        // 注文状態
        // 14:発注済（取消注文）
        l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
        // 注文有効状態
        // 2:クローズ
        l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        // 注文取消区分
        // 3：全部取消完了
        l_aioOrderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.CANCELED);

        log.exiting(STR_METHOD_NAME);
        return l_aioOrderUnitParams;
    }
}
@
