head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenMarginInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション新規建注文入力画面レスポンス(WEB3OptionsOpenMarginInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 李頴淵 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (株価指数オプション新規建注文入力画面レスポンス)<BR>
 * 株価指数オプション新規建注文入力画面レスポンスクラス<BR>
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginInputResponse extends WEB3OptionsCommonResponse
{

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406141512L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_openMarginInput";

    /**
     * 新規建可能額
     */
    public String opTradingPower;

    /**
     * 銘柄コード<BR>
     * 銘柄選択画面より遷移時に設定される。<BR>
     */
    public String opProductCode;

    /**
     * 取引市場一覧
     */
    public String[] marketList;

    /**
     * 取引市場
     */
    public String marketCode;

    /**
     * 指数種別一覧<BR>
     * 0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225<BR>
     */
    public String[] targetProductList;

    /**
     * 指数種別
     */
    public String targetProductCode;

    /**
     * 限月一覧
     */
    public String[] delivaryMonthList;

    /**
     * 限月
     */
    public String delivaryMonth;

    /**
     * オプション商品区分
     */
    public String opProductType;

    /**
     * 行使価格
     */
    public String strikePrice;

    /**
     * 値がついていないときは基準値を設定。<BR>
     */
    public String currentPrice;

    /**
     * 値がついていないときは未設定。<BR>
     */
    public String comparedPreviousDay;

    /**
     * 取引時間<BR>
     * 値がついていないときは未設定。<BR>
     */
    public Date currentPriceTime;

    /**
     * 取引終了警告文言<BR>
     * 閉局間近の市場があれば、そのコードを格納<BR>
     */
    public String[] messageSuspension;
    
    /**
     * デフォルトコンストラクタ
     */
    public WEB3OptionsOpenMarginInputResponse()
    {
        
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3OptionsOpenMarginInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
