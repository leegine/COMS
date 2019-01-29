head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済入力画面レスポンス(WEB3FuturesCloseMarginInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (株価指数先物返済入力画面レスポンス)<BR>
 * 株価指数先物返済入力画面レスポンスクラス
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginInputResponse extends WEB3FuturesCommonResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_closeMarginInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191543L;

    /**
     * (取引区分)<BR>
     * 5：買建返済注文（売返済）　@6：売建返済注文（買返済）
     */
    public String tradingType;

    /**
     * (取引市場)<BR>
     * 1：東京　@2：大阪
     */
    public String marketCode;

    /**
     * (指数種別)<BR>
     * 0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225
     */
    public String targetProductCode;

    /**
     * (限月)<BR>
     * YYYYMM形式
     */
    public String delivaryMonth;

    /**
     * (現在値)<BR>
     * 値がついていないときは基準値を設定。
     */
    public String currentPrice;

    /**
     * (前日比)<BR>
     * 値がついていないときは未設定。
     */
    public String comparedPreviousDay;

    /**
     * (取引時間)<BR>
     * 値がついていないときは未設定。
     */
    public Date currentPriceTime;

    /**
     * (建玉明細)<BR>
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits;

    /**
     * (取引終了警告文言)<BR>
     * 閉局間近の市場があれば、そのコードを格納
     */
    public String[] messageSuspension;

    /**
     * @@roseuid 40F7AE160280
     */
    public WEB3FuturesCloseMarginInputResponse()
    {

    }

    /**
    * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
    * レスポンスオブジェクトを生成する。<BR>
    *<BR>
    * @@param l_request リクエストオブジェクト
    */
    protected WEB3FuturesCloseMarginInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
