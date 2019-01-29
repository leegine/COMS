head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ブックビルディング申告内容クラス(WEB3IpoBookbuildingNewOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;

/**
 * ブックビルディング申告内容クラス。<BR>
 * <BR>
 * ※ 以下のメソッドに指定する引数クラス<BR>
 * 　@IPO申告マネージャ.validateブックビルディング申告()<BR>
 * 　@IPO申告マネージャsubmitブックビルディング申告()
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingNewOrderSpec extends NewOrderSpec 
{

    /**
     * 銘柄ＩＤ
     */
    private long productId;
    
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
     * 基準値（時価）
     */
    private double currentPrice = 0;
    
    /**
     * (ブックビルディング申告内容)<BR>
     * コンストラクタ。<BR>
     * ブックビルディング訂正内容を生成する。<BR>
     * <BR>
     * １）　@スーパークラスのコンストラクタ（super）をコール、インスタンスを生成する。<BR>
     * <BR>
     * [superに指定する引数]<BR>
     * 扱者：　@引数.扱者<BR>
     * 注文有効期限：　@null<BR>
     * <BR>
     * ２）　@プロパティに引数の銘柄ＩＤ、数量、指値をセットする。<BR>
     * <BR>
     * @@param l_trader - (扱者)<BR>
     * 扱者オブジェクト
     * @@param l_lngProductId - 銘柄ＩＤ
     * @@param l_dblQuantity - 数量
     * @@param l_dblLimitPrice - 指値
     * @@param l_dblCurrentPrice - 基準値（時価）
     * @@return webbroker3.ipo.WEB3IpoBookbuildingNewOrderSpec
     * @@roseuid 40C03AEA001A
     */
    public WEB3IpoBookbuildingNewOrderSpec(Trader l_trader, long l_lngProductId, double l_dblQuantity, double l_dblLimitPrice, double l_dblCurrentPrice) 
    {
        super(l_trader, null);
        this.productId = l_lngProductId;
        this.quantity = l_dblQuantity;
        this.limitPrice = l_dblLimitPrice;
        this.currentPrice = l_dblCurrentPrice;
    }
    
    /**
     * (is指値)<BR>
     * （isLimitOrder）<BR>
     * <BR>
     * （this.指値 == 0）の場合falseを返却する。<BR>
     * 以外、trueを返却する。
     * @@return boolean
     * @@roseuid 40C03AE903A4
     */
    public boolean isLimitOrder() 
    {
        return this.limitPrice != 0;
    }
    
    /**
     * (get銘柄ＩＤ)<BR>
     * 銘柄ＩＤ
     * @@return long
     * @@roseuid 40C03B2B021D
     */
    public long getProductId() 
    {
        return this.productId;
    }
    
    /**
     * (get数量)<BR>
     * 数量を取得する。
     * @@return double
     * @@roseuid 40C03AE903C3
     */
    public double getQuantity() 
    {
        return this.quantity;
    }
    
    /**
     * (get指値)<BR>
     * 指値を取得する。
     * @@return double
     * @@roseuid 40C03AE903E3
     */
    public double getLimitPrice() 
    {
        return this.limitPrice;
    }
    
    /**
     * (get基準値（時価）)<BR>
     * 基準値（時価）を取得する。<BR>
     * <BR>
     * this.基準値（時価）を返却する。
     * @@return double
     * @@roseuid 40D8F5D30017
     */
    public double getCurrentPrice() 
    {
        return this.currentPrice;
    }
}
@
