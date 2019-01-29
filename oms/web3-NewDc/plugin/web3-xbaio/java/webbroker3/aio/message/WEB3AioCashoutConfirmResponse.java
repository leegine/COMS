head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込確認レスポンス(WEB3AioCashoutConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (出金申込確認レスポンス)<BR>
 * 出金申込確認レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashoutConfirmResponse extends WEB3AioCashoutCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101030L;  
      
    /**
     * (出金金額)<BR>
     * 画面にて入力された出金額
     */
    public String cashoutAmt;
    
    /**
     * (振込予定日)<BR>
     * 画面にて選択された振込予定日
     */
    public Date transScheduledDate;
    
    /**
     * (確認時発注日)<BR>
     * 確認処理時の発注日<BR>
     * （画面表示なし）
     */
    public Date checkDate;
    
    /**
     * (確認時注文ID)<BR>
     * 確認処理時の注文ID<BR>
     * （画面表示なし）
     */
    public long checkOrderID;
    
    /**
     * @@roseuid 4158EB6200E3
     */
    public WEB3AioCashoutConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashoutConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
