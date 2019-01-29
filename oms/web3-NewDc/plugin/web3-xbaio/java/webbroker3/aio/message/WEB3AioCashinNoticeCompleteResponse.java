head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡完了レスポンスクラス(WEB3AioCashinNoticeCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 屈陽 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー    
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (入金連絡完了レスポンス)<BR>
 * 入金連絡完了レスポンスクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinNoticeCompleteResponse extends WEB3AioCashinNoticeCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_notice_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410111111L;       
    
    /**
     * (更新時間)<BR>
     * DB更新時間
     */
    public Date lastUpdatedTimestamp;       
    
    /**
    * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
    * レスポンスオブジェクトを生成する。<BR>
    *<BR>
    * @@param l_request リクエストオブジェクト
    */
    protected WEB3AioCashinNoticeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
