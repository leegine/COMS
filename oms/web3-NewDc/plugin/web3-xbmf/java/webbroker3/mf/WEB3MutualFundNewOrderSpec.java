head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  拡張投信新規注文内容(WEB3MutualFundNewOrderSpec)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/06 黄建 (中訊) 新規作成
*/
package webbroker3.mf;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbmf.ordersubmitter.io.MutualFundNewOrderSpec;

/**
 * 拡張投信新規注文内容<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualFundNewOrderSpec extends MutualFundNewOrderSpec 
{
    /**
     * (拡張投信新規注文内容)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * １）　@this()をコールする。<BR>
     * 　@［thisに渡すパラメタ］<BR>
     * 　@　@代理入力者： 引数.代理入力者<BR>
     * 　@　@is買付： 引数.is買付<BR>
     * 　@　@回号コード： ”0：DEFAULT”<BR>
     * 　@　@銘柄コード： 引数.銘柄コード<BR>
     * 　@　@市場コード： ”0：DEFAULT”<BR>
     * 　@　@注文数量： 引数.注文数量<BR>
     * 　@　@注文数量タイプ： 引数.注文数量タイプ<BR>
     * 　@　@税区分： 引数.税区分<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 扱者<BR>
     * @@param l_blnIsAcquired - (is買付)<BR>
     * 買付の場合は true を、解約の場合は false を設定する<BR>
     * @@param l_strMutualProductCode - 銘柄コード<BR>
     * @@param l_dblOrderQuantity - 注文数量<BR>
     * @@param l_orderQuantityType - 注文数量タイプ<BR>
     * @@param l_taxType - 税区分<BR>
     * @@roseuid 40ADAD9003B4
     */
    public WEB3MutualFundNewOrderSpec(
        Trader l_trader,
        boolean l_blnIsAcquired,
        String l_strMutualProductCode,
        double l_dblOrderQuantity,
        QuantityTypeEnum l_orderQuantityType,
        TaxTypeEnum l_taxType) 
    {
        this(
            l_trader,
            l_blnIsAcquired, 
            "0", 
            l_strMutualProductCode,
            "0",
            l_dblOrderQuantity,
            l_orderQuantityType,
            l_taxType);
    }
    /**
     * (拡張投信新規注文内容)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * １）　@super()をコールする。<BR>
     * 　@［superに渡すパラメタ］<BR>
     * 　@　@代理入力者： 引数.代理入力者<BR>
     * 　@　@is買付： 引数.is買付<BR>
     * 　@　@回号コード： 引数.回号コード<BR>
     * 　@　@銘柄コード： 引数.銘柄コード<BR>
     * 　@　@市場コード： 引数.市場コード<BR>
     * 　@　@注文数量： 引数.注文数量<BR>
     * 　@　@注文数量タイプ： 引数.注文数量タイプ<BR>
     * 　@　@税区分： 引数.税区分<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 扱者<BR>
     * @@param l_blnIsAcquired - 買付の場合は true を、解約の場合は false を設定する<BR>
     * @@param l_strProductIssueCode - 回号コード<BR>
     * @@param l_strMutualProductCode - 銘柄コード<BR>
     * @@param l_strMarketCode - 市場コード<BR>
     * @@param l_dblOrderQuantity - 注文数量<BR>
     * @@param l_orderQuantityType - 注文数量タイプ<BR>
     * @@param l_taxType - 税区分<BR>
     * @@roseuid 40ADAD9003A4
     */
    public WEB3MutualFundNewOrderSpec(
        Trader l_trader,
        boolean l_blnIsAcquired,
        String l_strProductIssueCode,
        String l_strMutualProductCode,
        String l_strMarketCode,
        double l_dblOrderQuantity,
        QuantityTypeEnum l_orderQuantityType,
        TaxTypeEnum l_taxType) 
    {
        super(
            l_trader,
            l_blnIsAcquired,
            l_strProductIssueCode,
            l_strMutualProductCode,
            l_strMarketCode,
            l_dblOrderQuantity,
            l_orderQuantityType,
            l_taxType);
    }
}
@
