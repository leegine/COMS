head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingCancelOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ブックビルディング取消内容クラス(WEB3IpoBookbuildingCancelOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;

/**
 * ブックビルディング取消内容クラス。<BR>
 * <BR>
 * ※ 以下のメソッドに指定する引数クラス<BR>
 * 　@IPO申告マネージャ.validateブックビルディング取消()<BR>
 * 　@IPO申告マネージャsubmitブックビルディング取消()<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingCancelOrderSpec extends CancelOrderSpec 
{
    
    /**
     * 取引者
     */
    private Trader trader;
        
    /**
     * (ブックビルディング取消内容)<BR>
     * コンストラクタ。<BR>
     * ブックビルディング取消内容を生成する。<BR>
     * <BR>
     * １）　@スーパークラスのコンストラクタ（super）をコール、インスタンスを生成する。<BR>
     * <BR>
     * [superに指定する引数]<BR>
     * orderId：　@IPO申告ＩＤ<BR>
     * <BR>
     * ２）　@プロパティに引数の代理入力者をセットする。<BR>
     * @@param l_trader - (取引者)<BR>
     * 取引者（扱者）オブジェクト
     * 
     * @@param l_lngIpoOrderId - IPO申告ＩＤ
     * @@return webbroker3.ipo.WEB3IPOBookBuildingCancelOrderSpec
     * @@roseuid 40D958FB0055
     */
    public WEB3IpoBookbuildingCancelOrderSpec(Trader l_trader, long l_lngIpoOrderId) 
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
     * @@roseuid 40D958930026
     */
    public Trader getTrader() 
    {
        return this.trader;
    }
}
@
