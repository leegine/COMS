head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡注文入力レスポンス(WEB3MarginSwapMarginInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
Revesion History : 2004/12/13 桑原 (SRA) 修正
                 : 2007/01/11 趙林鵬 (中訊) モデル No.1082
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （信用取引現引現渡注文入力レスポンス）。<br>
 * <br>
 * 信用取引現引現渡注文入力レスポンスクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginSwapMarginInputResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_swapMarginInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (現引可能額)<BR>
     * 取引区分が「7：現引注文」の時設定
     */
    public String swapLongTradingPower;
    
    /**
     * (銘柄コード)
     */
    public String productCode;
    
    /**
     * (銘柄名)
     */
    public String productName;
    
    /**
     * (市場コード)
     */
    public String marketCode;
    
    /**
     * (口座区分)<BR>
     * 0：一般　@1：特定
     */
    public String taxType;
    
    /**
     * (取引区分)<BR>
     * 7：現引注文　@8：現渡注文<BR>
     * （OrderTypeEnumにて定義）<BR>
     */
    public String tradingType;
    
    /**
     * 弁済<BR>
     * （信用取引弁済）<BR>
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (現引先現渡元口座区分一覧)<BR>
     * 0：一般　@1：特定<BR>
     * <BR>
     * 現引の時は現引先口座区分<BR>
     * 現渡の時は現渡元口座区分<BR>
     */
    public String[] swapTaxTypeList;
    
    /**
     * (時価区分)<BR>
     * 時価区分 <BR>
     * （1：現在値 <BR>
     * 　@2：売気配値 <BR>
     * 　@3：買気配値 <BR>
     * 　@4：前日終値）<BR>
     */
    public String currentPriceDiv;
    
    /**
     * (時価(現在値))<BR>
     * 値がついていないときは未設定。<BR>
     * <BR>
     * 銘柄指定の場合使用。<BR>
     */
    public String currentPrice;
    
    /**
     * (前日比)<BR>
     * 値がついていないときは未設定。<BR>
     * <BR>
     * 銘柄指定の場合使用。<BR>
     */
    public String comparedPreviousDay;
    
    /**
     * (取引時間(時価発表時間))<BR>
     * 値がついていないときは未設定。<BR>
     * <BR>
     * 銘柄指定の場合使用。<BR>
     */
    public Date currentPriceTime;

    /**
     * (現在値)<BR>
     */
    public String boardCurrentPrice;

    /**
     * (現在値時刻)<BR>
     */
    public Date boardCurrentPriceTime;

    /**
     * (現在値区分)<BR>
     */
    public String boardCurrentPriceDiv;

    /**
     * (現在値前日比)<BR>
     */
    public String boardChange;

    /**
     * (出来高)<BR>
     */
    public String volume;

    /**
     * (出来高時刻)<BR>
     */
    public Date volumeTime;

    /**
     * (買気配値タイトル区分)<BR>
     */
    public String askPriceTitle;

    /**
     * (買気配値)<BR>
     */
    public String askPrice;

    /**
     * (買気配値時刻)<BR>
     */
    public Date askPriceTime;

    /**
     * (売気配値タイトル区分)<BR>
     */
    public String bidPriceTitle;

    /**
     * (売気配値)<BR>
     */
    public String bidPrice;

    /**
     * (売気配値時刻)<BR>
     */
    public Date bidPriceTime;

    /**
     * (基準値段)<BR>
     */
    public String basePrice;

    /**
     * (建株明細一覧)
     */
    public WEB3MarginContractUnit[] contractUnits;
    
    /**
     * (取引終了警告市場コード一覧)<BR>
     * 取引終了警告文言を表示する市場コードの一覧
     */
    public String[] messageSuspension;
    
	/**
	 * (インサイダー警告表示フラグ)<BR>
	 * true：警告表示要　@　@　@false：警告表示不要
	 */
	public boolean insiderWarningFlag;
	
    /**
     * @@roseuid 414044F002C2
     */
    public WEB3MarginSwapMarginInputResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginSwapMarginInputResponse(WEB3MarginSwapMarginInputRequest l_request)
    {
        super(l_request);
    }
}
@
