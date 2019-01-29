head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金通知訂正入力画面レスポンス(WEB3AdminAioCashinNoticeChangeInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/20 李俊 (中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (入金通知訂正入力画面レスポンス)<BR>
 * 入金通知訂正入力画面レスポンスクラス<BR>
 * 
 * @@author 李俊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinNoticeChangeInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashin_notice_change_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200601211119L;        
    
    /**
     * (入金通知明細一覧)<BR>
     */
    public WEB3AioCashinNoticeUnit2[] cashinNoticeList;
    
    
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3AdminAioCashinNoticeChangeInputResponse(WEB3AdminAioCashinNoticeChangeInputRequest l_request)
    {
        super(l_request);
    }  
}
@
