head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOpenContractOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP新規建注文リクエストアダプタ(WEB3OptionOpenContractOrderRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 張騰宇 (中訊) 新規作成 モデル 848
*/
package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP新規建注文リクエストアダプタ)<BR>
 * 株価指数オプション新規建注文リクエストアダプタクラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3OptionOpenContractOrderRequestAdapter
{
    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionOpenContractOrderRequestAdapter.class);

    /**
     * (リクエストデータ)<BR>
     * リクエストオブジェクト。<BR>
     */
    public WEB3GenRequest request;

    /**
     * (単価)<BR>
     * 単価。<BR>
     * <BR>
     * （指値／0（＝成行）／執行単価（±指値））<BR>
     */
    public double price;

    /**
     * (コンストラクタ)。<BR>
     */
    protected WEB3OptionOpenContractOrderRequestAdapter()
    {

    }

    /**
     * オプション新規建注文リクエストアダプタのインスタンスを生成する。 <BR>
     * <BR>
     * 　@１）　@本インスタンスを生成する。 <BR>
     * <BR>
     * 　@２）　@生成したインスタンスに、以下の項目を設定する。 <BR>
     * <BR>
     * 　@　@　@　@リクエストデータ　@：　@引数リクエスト <BR>
     * <BR>
     * 　@３）　@インスタンスを返却する。 <BR>
     * <BR>
     * （本メソッドによってインスタンスかするように制限する）<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3OptionOpenContractOrderRequestAdapter
     * @@throws WEB3BaseException
     */
    public static WEB3OptionOpenContractOrderRequestAdapter create(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //１） 本インスタンスを生成する。
        WEB3OptionOpenContractOrderRequestAdapter l_openContractRequestAdapter =
            new WEB3OptionOpenContractOrderRequestAdapter();

        //２） 生成したインスタンスに、以下の項目を設定する。
        //リクエストデータ ： 引数.リクエスト
        l_openContractRequestAdapter.request = l_request;

        //３） インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_openContractRequestAdapter;
    }

    /**
     * (get単価)<BR>
     * 単価を返却する。 <BR>
     * <BR>
     * 　@リクエストデータ.注文単価区分 == "指値"の場合 <BR>
     * 　@　@リクエストデータ.注文単価を返却する。 <BR>
     * <BR>
     * 　@リクエストデータ.注文単価区分 == "成行"の場合 <BR>
     * 　@　@0を返却する。<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPrice()";
        log.entering(STR_METHOD_NAME);

        String l_strOrderPriceDiv = null;
        double l_dblPrice = 0.0D;
        String l_strLimitPrice = null;
        if (this.request instanceof WEB3OptionsOpenMarginConfirmRequest)
        {
            l_strOrderPriceDiv = ((WEB3OptionsOpenMarginConfirmRequest)this.request).orderPriceDiv;
            l_strLimitPrice = ((WEB3OptionsOpenMarginConfirmRequest)this.request).limitPrice;
        }
        else if (this.request instanceof WEB3OptionsOpenMarginCompleteRequest)
        {
            l_strOrderPriceDiv = ((WEB3OptionsOpenMarginCompleteRequest)this.request).orderPriceDiv;
            l_strLimitPrice = ((WEB3OptionsOpenMarginCompleteRequest)this.request).limitPrice;
        }

        //リクエストデータ.注文単価区分 == "指値"の場合リクエストデータ.注文単価を返却する。
        //リクエストデータ.注文単価区分 == "成行"の場合0を返却する。
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrderPriceDiv))
        {
            if (l_strLimitPrice != null)
            {
                l_dblPrice = Double.parseDouble(l_strLimitPrice);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblPrice;
    }
}
@
