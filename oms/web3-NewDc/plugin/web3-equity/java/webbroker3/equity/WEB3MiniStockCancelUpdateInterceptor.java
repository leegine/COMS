head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MiniStockCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資取消更新インタセプタ(WEB3MiniStockCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12  彭巍(中訊) 新規作成
                   2004/12/29  岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式ミニ投資取消更新インタセプタ）。<BR>
 * <BR>
 * 株式ミニ投資取消更新インタセプタクラス
 * @@author 彭巍
 * @@version 1.0 
 */
public class WEB3MiniStockCancelUpdateInterceptor extends WEB3MarginUpdateEventInterceptor 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MiniStockCancelUpdateInterceptor.class);
    
    /**
     * (注文経路区分)<BR>
     */
    private String orderRootDiv = null;
    
    /**
     * (代理入力者)<BR>
     */
    private WEB3GentradeTrader trader = null;
    
    /**
     * （株式ミニ投資取消更新インタセプタ）。<BR>
     * <BR>
     * コンストラクタ。
     * @@param    l_orderRootdiv - 注文経路区分<BR>
     * @@param    l_trader       - 扱者<BR>
     */
    public WEB3MiniStockCancelUpdateInterceptor(
        String l_strOrderRootdiv, WEB3GentradeTrader l_trader) 
    {
        this.orderRootDiv = l_strOrderRootdiv;
        this.trader = l_trader;
    }
    
    /**
     * （更新値設定）。<BR>
     * <BR>
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * １） 拡張項目セット<BR>
     * 　@プロパティから、パラメータ.注文単位Paramsの拡張項目に値をセットし、<BR>
     * 返却する。<BR>
     * <BR>
     * 更新内容はDB設定論理「ミニ株取消_株式注文単位テーブル.xls」参照。
     * @@param l_mutate (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * EqTypeOrderManagerPersistenceTypeにて定数定義。
     * @@param l_context (処理)<BR>
     * （EqTypeOrderManagerPersistenceContextにて定数定義）
     * @@param l_eqtypeOrderUnitParams (注文単位Params)<BR>
     * 株式注文単位が保持する項目のパラメータクラス。
     * @@return EqtypeOrderUnitParams
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_mutate, 
        OrderManagerPersistenceContext l_context, 
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(" +
            "OrderManagerPersistenceType l_mutate," +
            "OrderManagerPersistenceContext l_context," + 
            "EqtypeOrderUnitParams l_eqtypeOrderUnitParams) ";
        log.entering(STR_METHOD_NAME);

        //扱者 = インタセプタのプロパティ.代理入力者.取引者ID
        //  ※インタセプタのプロパティ.代理入力者==nullの場合は、nullをセット。
        if (this.trader == null)
        {
            l_eqtypeOrderUnitParams.setTraderId(null);
        }
        else
        {
            l_eqtypeOrderUnitParams.setTraderId(this.trader.getTraderId());
        }
        //全部取消完了
        l_eqtypeOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELED);

        //注文経路区分 = インタセプタのプロパティ.注文経路区分
        l_eqtypeOrderUnitParams.setOrderRootDiv(this.orderRootDiv);

        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
    }
     
}
@
