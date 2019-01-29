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
filename	WEB3EquityCancelOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文取消内容(WEB3EquityCancelOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 李雲峰 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2006/11/01 趙林鵬 (中訊) モデル No.1046
*/

package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeCancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

/**
 * （株式注文取消内容）。<BR>
 * <BR>
 * 注文取消内容の入力を表現する。<BR>
 * 注文マネージャに渡すパラメタ。<BR>
 * <BR>
 * xTradeのEqTypeCancelOrderSpecを拡張したクラス。
 * @@version 1.0
 */
public class WEB3EquityCancelOrderSpec extends EqTypeCancelOrderSpec
{

    /**
     * (扱者)<BR>
     * 代理入力扱者。<BR>
     */
    private Trader trader;

    /**
     * (株式注文取消内容)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * 　@スーパークラスのコンストラクタ（EqTypeCancelOrderSpec）をコールする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@orderId：　@（引数より編集）<BR>
     * <BR>
     * ２）　@拡張プロパティをセットする<BR>
     * <BR>
     * 　@－扱者：　@（引数より編集）<BR>
     * <BR>
     * @@param l_orderID - (注文ＩＤ)<BR>
     * 注文ＩＤ<BR>
     * @@param l_trader - (扱者)<BR>
     * 扱者<BR>
     * @@return WEB3EquityOrderCancelSpec<BR>
     * @@roseuid 4031B81301B5<BR>
     */
    public WEB3EquityCancelOrderSpec(long l_orderID, Trader l_trader)
    {
        //インスタンス生成
        super(l_orderID);

        //拡張プロパティをセットする
        this.trader = l_trader;
    }

    /**
     * (get扱者)<BR>
     * 扱者を取得する。<BR>
     * @@return Trader<BR>
     * @@roseuid 4031B7F10261<BR>
     */
    public Trader getTrader()
    {
        return this.trader;
    }

}
@
