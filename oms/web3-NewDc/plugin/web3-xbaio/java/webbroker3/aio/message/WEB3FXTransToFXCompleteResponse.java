head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXへの振替完了レスポンス(WEB3FXTransToFXCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 屈陽 (中訊) 新規作成
                 : 2006/04/26 李小健 (中訊) 仕様変更・モデル537
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FXへの振替完了レスポンス) <BR>
 * FXへの振替完了レスポンスクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTransToFXCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_to_fx_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;
    
    /**
     * (受渡日) <BR>
     * 受渡日 <BR>
     */
    public Date deliveryDate;
    
    /**
     * (更新時間) <BR>
     * DBの更新時間 <BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * (識別番号) <BR>
     * 注文ID <BR>
     */
    public String orderActionId;

    /**
     * @@roseuid 41E76935000F
     */
    public WEB3FXTransToFXCompleteResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FXTransToFXCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}@
