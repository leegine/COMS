head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.05.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡確認レスポンス(WEB3AioCashinNoticeConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (入金連絡確認レスポンス)<BR>
 * 入金連絡確認レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashinNoticeConfirmResponse extends WEB3AioCashinNoticeCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_notice_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101545L; 
            
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashinNoticeConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
