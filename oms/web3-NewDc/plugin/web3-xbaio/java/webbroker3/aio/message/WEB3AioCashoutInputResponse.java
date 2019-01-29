head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込入力レスポンス(WEB3AioCashoutInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (出金申込入力レスポンス)<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashoutInputResponse extends WEB3AioCashoutCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101145L; 
       
    /**
     * (出金余力)<BR>
     * 顧客の（T+1～T+5)の出金余力のリスト
     */
    public String[] paymentPowerList;
    
    /**
     * (振込予定日)<BR>
     * （T+1～T+5)の振込予定日のリスト
     */
    public Date[] transScheduledDateList;
    
    /**
     * @@roseuid 4158EB620273
     */
    public WEB3AioCashoutInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashoutInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
