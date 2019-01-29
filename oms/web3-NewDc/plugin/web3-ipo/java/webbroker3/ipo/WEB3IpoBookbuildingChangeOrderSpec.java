head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingChangeOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ブックビルディング訂正内容クラス(WEB3IpoBookbuildingChangeSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;

/**
 * ブックビルディング訂正内容クラス。<BR>
 * <BR>
 * ※ 以下のメソッドに指定する引数クラス<BR>
 * 　@IPO申告マネージャ.validateブックビルディング訂正()<BR>
 * 　@IPO申告マネージャsubmitブックビルディング訂正()
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingChangeOrderSpec extends ChangeOrderSpec 
{
    
    /**
     * 数量
     */
    private double quantity;
    
    /**
     * 指値<BR>
     * <BR>
     * ※ 成行の場合0。
     */
    private double limitPrice;
    
    /**
     * 取引者
     */
    private Trader trader;
    
    /**
     * 基準値（時価）
     */
    private double currentPrice = 0;
        
    /**
     * (ブックビルディング訂正内容)<BR>
     * コンストラクタ。<BR>
     * ブックビルディング訂正内容を生成する。<BR>
     * <BR>
     * １）　@スーパークラスのコンストラクタ（super）をコール、インスタンスを生成する。<BR>
     * <BR>
     * [superに指定する引数]<BR>
     * 注文ＩＤ：　@IPO申告ＩＤ<BR>
     * <BR>
     * ２）　@プロパティに引数の数量、指値、扱者をセットする。
     * @@param l_trader - (取引者)<BR>
     * 取引者（扱者）オブジェクト
     * 
     * @@param l_lngIpoOrderId - IPO申告ＩＤ
     * @@param l_dblQuantity - 数量
     * @@param l_dblLimitPrice - 指値
     * @@param l_dblCurrentPrice - 基準値（時価）
     * @@return webbroker3.ipo.WEB3IPOBookBuildingChangeSpec
     * @@roseuid 40C039C90162
     */
    public WEB3IpoBookbuildingChangeOrderSpec(Trader l_trader, long l_lngIpoOrderId, double l_dblQuantity, double l_dblLimitPrice, double l_dblCurrentPrice) 
    {
        super(l_lngIpoOrderId);
        this.quantity = l_dblQuantity;
        this.limitPrice = l_dblLimitPrice;
        this.trader = l_trader;
        this.currentPrice = l_dblCurrentPrice;
    }
    
    /**
     * (is指値)<BR>
     * （isLimitOrder）<BR>
     * <BR>
     * （this.指値 == 0）の場合falseを返却する。<BR>
     * 以外、trueを返却する。
     * @@return boolean
     * @@roseuid 40C038CB0191
     */
    public boolean isLimitOrder() 
    {
        return this.limitPrice != 0;
    }
    
    /**
     * (get数量)<BR>
     * 数量を取得する。
     * @@return double
     * @@roseuid 40C03947001A
     */
    public double getQuantity() 
    {
        return this.quantity;
    }
    
    /**
     * (get指値)<BR>
     * 指値を取得する。
     * @@return double
     * @@roseuid 40C039500394
     */
    public double getLimitPrice() 
    {
        return this.limitPrice;
    }
    
    /**
     * (get取引者)<BR>
     * （getTrader）<BR>
     * <BR>
     * this.取引者を返却する。
     * @@return Trader
     * @@roseuid 40D95A340094
     */
    public Trader getTrader() 
    {
        return this.trader;
    }
    
    /**
     * (get基準値（時価）)<BR>
     * 基準値（時価）を取得する。<BR>
     * <BR>
     * this.基準値（時価）を返却する。
     * @@return double
     * @@roseuid 40D96F9902ED
     */
    public double getCurrentPrice() 
    {
        return this.currentPrice;
    }
}
@
