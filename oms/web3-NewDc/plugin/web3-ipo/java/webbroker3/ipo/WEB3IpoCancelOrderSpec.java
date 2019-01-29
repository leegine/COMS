head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoCancelOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 辞退内容(WEB3IpoCancelOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 劉江涛(中訊) 新規作成
*/

package webbroker3.ipo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;


/**
 * 辞退内容クラス<BR>
 * <BR>
 * ※ 以下のメソッドに指定する引数クラス<BR>
 * 　@IPO申告マネージャ.validate辞退()<BR>
 * 　@IPO申告マネージャsubmit辞退()
 * @@author 劉江涛(中訊)
 * @@version 1.0
 */

public class WEB3IpoCancelOrderSpec extends CancelOrderSpec 
{
    
    /**
     * 取引者
     */
    private Trader trader;
    
    /**
     * (辞退内容)<BR>
     * コンストラクタ。<BR>
     * 辞退内容を生成する。<BR>
     * <BR>
     * １）　@スーパークラスのコンストラクタ（super）をコール、インスタンスを生成する。<BR>
     * <BR>
     * [superに指定する引数]<BR>
     * orderId：　@IPO申告ＩＤ<BR>
     * <BR>
     * ２）　@プロパティに引数の代理入力者をセットする。
     * @@param l_trader - 取引者（扱者）オブジェクト
     * 
     * @@param l_lngIpoOrderId - IPO申告ＩＤ
     * @@return webbroker3.ipo.WEB3IpoCancelOrderSpec
     * @@roseuid 40DBC3D703CA
     */
    public WEB3IpoCancelOrderSpec(Trader l_trader, long l_lngIpoOrderId) 
    {
        super(l_lngIpoOrderId);
        this.trader = l_trader;
    }
    
    /**
     * (get取引者)<BR>
     * （getTrader）<BR>
     * <BR>
     * this.取引者を返却する。
     * @@return Trader
     * @@roseuid 40DBC3D703C9
     */
    public Trader getTrader() 
    {
        return this.trader;
    }
}
@
