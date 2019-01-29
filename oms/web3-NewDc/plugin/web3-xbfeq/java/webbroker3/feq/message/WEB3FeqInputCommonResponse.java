head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.36.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqInputCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式入力共通レスポンス(WEB3FeqInputCommonResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (外国株式入力共通レスポンス)<BR>
 * 外国株式入力共通レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqInputCommonResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_inputCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (注文単価区分一覧)<BR>
     * 注文単価区分一覧 <BR>
     * <BR>
     * 0：成行<BR>
     * 1：指値 <BR>
     */
    public String[] orderPriceDivList;
    
    
    /**
     * (執行条件一覧)<BR>
     * 執行条件一覧<BR>
     * <BR>
     * 1：条件なし<BR>
     * 3：寄付<BR>
     * 4：引け<BR>
     * 7：不出来引け成行<BR>
     */
    public String[] execCondList;
    
    /**
     * (注文期限区分一覧)<BR>
     * 注文期限区分一覧<BR>
     * <BR>
     * 1：当日限り<BR>
     * 2：出来るまで注文<BR>
     */
    public String[] expirationDateTypeList;
    
    /**
     * (有効期限開始日)<BR>
     * 有効期限開始日<BR>
     */
    public Date expirationStartDate;
    
    /**
     * (有効期限終了日)<BR>
     * 有効期限終了日<BR>
     */
    public Date expirationEndDate;
    
    /**
     * (有効期限内祝日一覧)<BR>
     * 有効期限内祝日一覧<BR>
     */
    public Date[] holidayList;
    
    /**
     * (発注条件一覧)<BR>
     * 発注条件一覧<BR>
     * <BR>
     * 0：指定なし<BR>
     * 1：逆指値<BR>
     * 2：W指値<BR>
     */
    public String[] orderCondTypeList;
    
    /**
     * (時価取得区分)<BR>
     * 時価取得区分<BR>
     * <BR>
     * 1：現在値<BR>
     * 2：売気配値<BR>
     * 3：買気配値<BR>
     * 4：前日終値<BR>
     * <BR>
     * ※値がついていないときは未設定<BR>
     */
    public String currentPriceGetDiv;
    
    /**
     * (時価)<BR>
     * 時価<BR>
     * <BR>
     * ※値がついていないときは未設定<BR>
     */
    public String currentPrice;
    
    /**
     * (前日比)<BR>
     * 前日比<BR>
     * <BR>
     * ※値がついていないときは未設定<BR>
     */
    public String comparedPreviousDay;
    
    /**
     * (取引時間)<BR>
     * 取引時間<BR>
     * <BR>
     * ※値がついていないときは未設定<BR>
     */
    public Date currentPriceTime;
    
    /**
     * (取引終了警告市場コード一覧)<BR>
     * 取引終了文言を表示する市場コードの一覧<BR>
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 42CE3A0900CB
     */
    public WEB3FeqInputCommonResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FeqInputCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
