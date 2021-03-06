head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXAskingResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXへの振替依頼レスポンス(WEB3FXTransToFXAskingResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 屈陽 (中訊) 新規作成
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (FXへの振替依頼レスポンス) <BR>
 * FXへの振替依頼レスポンスクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTransToFXAskingResponse extends WEB3FXAskingCommonResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_to_fx_asking";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (注文ID) <BR>
     * 注文ID <BR>
     */
    public String orderId;

    /**
     * (確認時発注日) <BR>
     * 確認処理時の発注日 <BR>
     */
    public Date checkDate;

    /**
     * @@roseuid 41E769350222
     */
    public WEB3FXTransToFXAskingResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FXTransToFXAskingResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}@
