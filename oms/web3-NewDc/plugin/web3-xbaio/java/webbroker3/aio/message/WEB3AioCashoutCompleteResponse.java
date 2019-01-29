head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込完了レスポンス(WEB3AioCashoutCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (出金申込完了レスポンス)<BR>
 * 出金申込完了レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashoutCompleteResponse extends WEB3AioCashoutCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101325L; 
       
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
     * (DBの更新時間)
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (発注した注文の注文ID)
     */
    public String orderId;
    
    /**
     * @@roseuid 4158EB61038A
     */
    public WEB3AioCashoutCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashoutCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
