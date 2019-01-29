head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物新規建注文入力画面レスポンスクラス(WEB3FuturesOpenMarginInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 呉艶飛 新規作成
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (株価指数先物新規建注文入力画面レスポンス)<BR>
 * 株価指数先物新規建注文入力画面レスポンスクラス<BR>
 * 
 * @@author 呉艶飛
 * @@version 1.0 
 */
public class WEB3FuturesOpenMarginInputResponse extends WEB3FuturesCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_OpenMarginInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201655L;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄選択画面より遷移時に設定される。<BR>
     */
    public String futProductCode;
    
    /**
     * (新規建可能額)<BR>
     */
    public String futTradingPower;
    
    /**
     * (取引市場一覧)<BR>
     */
    public String[] marketList;
    
    /**
     * (取引市場)<BR>
     * 1：東京　@2：大阪<BR>
     */
    public String marketCode;
    
    /**
     * (指数種別一覧)<BR>
     */
    public String[] targetProductList;
    
    /**
     * (指数種別)<BR>
     * 0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225<BR>
     */
    public String targetProductCode;
    
    /**
     * (限月一覧)<BR>
     */
    public String[] delivaryMonthList;
    
    /**
     * (限月)<BR>
     */
    public String delivaryMonth;
    
    /**
     * (現在値)<BR>
     * 値がついていないときは基準値を設定。<BR>
     */
    public String currentPrice;
    
    /**
     * (前日比)<BR>
     * 値がついていないときは未設定。<BR>
     */
    public String comparedPreviousDay;
    
    /**
     * (取引時間)<BR>
     * 値がついていないときは未設定。<BR>
     */
    public Date currentPriceTime;
    
    /**
     * (取引終了警告文言)<BR>
     * 閉局間近の市場があれば、そのコードを格納<BR>
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 40F7AE0D01E4
     */
    public WEB3FuturesOpenMarginInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
