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
filename	WEB3NewMiniStockOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文内容(WEB3NewMiniStockOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 劉江涛 (中訊) 新規作成
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewMiniStockOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

/**
 * （株式ミニ投資注文内容）。<BR>
 * <BR>
 * 株式ミニ投資注文内容クラス
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3NewMiniStockOrderSpec extends EqTypeNewMiniStockOrderSpec 
{
    
    /**
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     * @@param arg4
     * @@param arg5
     */
    public WEB3NewMiniStockOrderSpec(Trader arg0, boolean arg1, String arg2, String arg3, double arg4, TaxTypeEnum arg5)
    {
        super(arg0, arg1, arg2, arg3, arg4, arg5);
    }

    /**
     * （create株式ミニ投資注文内容）。<BR>
     * <BR>
     * （staticメソッド）<BR>
     * 株式ミニ投資注文内容を生成する。<BR>
     * <BR>
     * superクラスのコンストラクタをコールし、ミニ株注文内容を生成する。<BR>
     * <BR>
     * [コンストラクタの引数]<BR>
     * trader：　@扱者<BR>
     * isBuyOrder：　@is買注文<BR>
     * productCode：　@銘柄コード<BR>
     * marketCode：　@市場コード<BR>
     * quantity：　@株数<BR>
     * taxType：　@TaxTypeEnum.UNDEFINED（その他）<BR>
     * <BR>
     * 生成したミニ株注文内容オブジェクトを返却する。<BR>
     * <BR>
     * @@param l_trader - (扱者)<BR>
     * 扱者オブジェクト（代理入力者）
     * 
     * @@param l_blnIsBuyOrder - (is買注文)<BR>
     * 買注文かを判定するフラグ。<BR>
     * <BR>
     * true：　@買注文<BR>
     * false：　@売注文<BR>
     * <BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード
     * @@param l_dblQuantity - (株数)<BR>
     * 株数
     * @@return WEB3MstkOrderSpec
     */
    public static WEB3NewMiniStockOrderSpec createNewMiniStockOrderSpec(Trader l_trader, boolean l_blnIsBuyOrder, String l_strProductCode, String l_strMarketCode, double l_dblQuantity) 
    {
        WEB3NewMiniStockOrderSpec l_spec = new WEB3NewMiniStockOrderSpec(
            l_trader, l_blnIsBuyOrder, l_strProductCode, l_strMarketCode, l_dblQuantity, TaxTypeEnum.UNDEFINED);
            
        return l_spec;
    }
}
@
