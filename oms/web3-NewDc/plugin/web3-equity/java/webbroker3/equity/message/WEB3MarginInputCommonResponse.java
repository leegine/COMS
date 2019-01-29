head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginInputCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引共通入力レスポンス(WEB3MarginInputCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 凌建平 (中訊) 新規作成
Revesion History : 2004/12/10 桑原 (SRA) 残案件対応
                   2006/11/02 張騰宇(中訊) モデル 948
                   2006/12/14 唐性峰　@(中訊)　@モデルNo.1082
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * （信用取引共通入力レスポンス）。<br>
 * <br>
 * 信用取引共通入力レスポンスクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginInputCommonResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_inputCommon";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (注文単価区分一覧)<BR>
     * 0：成行　@　@　@1：指値
     */    
    public String[] orderPriceDivList;
    
    /**
     * (値段条件一覧)<BR>
     * 0:指定なし　@1:現在値指値　@3:優先指値　@5:成行残数指値　@7:成行残数取消
     */
    public String[] priceCondList;
    
    
    /**
     * (執行条件一覧)<BR>
     * <BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行
     */
    public String[] execCondList;

    /**
     * (Ｗ指値用執行条件一覧)<BR>
     * 1：無条件 3:寄付　@4:引け　@7:不出来引け成行<BR>
     */
    public String[] wlimitExecCondList;

    /**
     * (有効期限開始日)<BR>
     * 出来るまで注文に指定できる最初の日
     */
    public Date expirationStartDate;
    
    /**
     * (有効期限最終日)<BR>
     * 出来るまで注文に指定できる最後の日
     */
    public Date expirationEndDate;
    
    /**
     * (有効期限内祝日一覧)<BR>
     * 出来るまで注文に指定できる期間中の祝日一覧
     */
    public Date[] holidayList;
    
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
     * (時価区分)<BR>
     * 時価区分 <BR>
     * （1：現在値 <BR>
     * 　@2：売気配値 <BR>
     * 　@3：買気配値 <BR>
     * 　@4：前日終値）<BR>
     */
    public String currentPriceDiv;
    
    /**
     * (時価（現在値）<BR>
     * 時価（現在値）
     */
    public String currentPrice;
    
    /**
     * (前日比)<BR>
     * 前日比
     */
    public String comparedPreviousDay;
    
    /**
     * (取引時間(時価発表時間）)<BR>
     * 取引時間(時価発表時間）
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
     * @@roseuid 41403F700264
     */
    public WEB3MarginInputCommonResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginInputCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
