head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張累投新規注文内容クラス(WEB3RuitoNewOrderSpec)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito;

import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbruito.ordersubmitter.io.RuitoNewOrderSpec;

/**
 * 拡張累投新規注文内容。<BR>
 */
public class WEB3RuitoNewOrderSpec extends RuitoNewOrderSpec
{

    /**
     * コンストラクタ。<BR>
     * <BR>
     * １）　@コンストラクタをコールする。<BR>
     * 　@［コンストラクタに渡すパラメタ］<BR>
     * 　@　@代理入力者： 引数.代理入力者<BR>
     * 　@　@is買付： 引数.is買付<BR>
     * 　@　@回号コード： ”0：DEFAULT”<BR>
     * 　@　@銘柄コード： 引数.銘柄コード<BR>
     * 　@　@市場コード： ”0：DEFAULT”<BR>
     * 　@　@注文数量： 引数.注文数量<BR>
     * 　@　@注文数量タイプ： 引数.注文数量タイプ<BR>
     * 　@　@税区分： 引数.税区分<BR>
     * @@param l_trader - 扱者<BR>
     * @@param l_isBuy - 買付の場合は true を、解約の場合は false を設定する<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@param l_orderQuantity - 注文数量<BR>
     * @@param l_quantityTypeEnum - 注文数量タイプ<BR>
     * @@param l_taxTypeEnum - 税区分<BR>
     * @@roseuid 407C8DA00261
     */
    public WEB3RuitoNewOrderSpec(
        Trader l_trader,
        boolean l_isBuy,
        String l_strProductCode,
        double l_orderQuantity,
        QuantityTypeEnum l_quantityTypeEnum,
        TaxTypeEnum l_taxTypeEnum)
    {
        super(
            l_trader,
            l_isBuy,
            "0",
            l_strProductCode,
            "0",
            l_orderQuantity,
            l_quantityTypeEnum,
            l_taxTypeEnum);
    }

    /**
     * コンストラクタ。<BR>
     * １）　@super()をコールする。<BR>
     *  ［superに渡すパラメタ］ 
     *     代理入力者： 引数.代理入力者 <BR>
     *     is買付： 引数.is買付 <BR>
     *     回号コード： 引数.回号コード <BR>
     *     銘柄コード： 引数.銘柄コード <BR>
     *     市場コード： 引数.市場コード <BR>
     *     注文数量： 引数.注文数量 <BR>
     *     注文数量タイプ： 引数.注文数量タイプ <BR>
     *     税区分： 引数.税区分 <BR>
     * <BR>
     * @@param l_trader - 扱者<BR>
     * @@param l_isBuy - 買付の場合は true を、解約の場合は false を設定する<BR>
     * @@param l_strIssueCode - 回号コード<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@param l_strMarketCode - 市場コード<BR>
     * @@param l_dblOrderQuantity - 注文数量<BR>
     * @@param l_quantityTypeEnum - 注文数量タイプ<BR>
     * @@param l_taxTypeEnum - 税区分<BR>
     * @@roseuid 406CE9BE02BE
     */
    public WEB3RuitoNewOrderSpec(
        Trader l_trader,
        boolean l_isBuy,
        String l_strIssueCode,
        String l_strProductCode,
        String l_strMarketCode,
        double l_dblOrderQuantity,
        QuantityTypeEnum l_quantityTypeEnum,
        TaxTypeEnum l_taxTypeEnum)
    {
        //１）　@super()をコールする。
        super(
            l_trader,
            l_isBuy,
            l_strIssueCode,
            l_strProductCode,
            l_strMarketCode,
            l_dblOrderQuantity,
            l_quantityTypeEnum,
            l_taxTypeEnum);
    }

}
@
