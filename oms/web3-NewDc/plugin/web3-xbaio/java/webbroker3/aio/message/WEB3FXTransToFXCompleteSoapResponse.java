head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXCompleteSoapResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXへの振替完了レスポンス（SOAP接続）(WEB3FXTransToFXCompleteSoapResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/09 余新敏(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (FXへの振替完了レスポンス（SOAP接続）)<BR>
 * FXへの振替完了レスポンス（SOAP接続）クラス<BR>
 *
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3FXTransToFXCompleteSoapResponse extends WEB3FXTransToFXCompleteResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_to_fx_complete_soap";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200602091550L;
    
    /**
     * コンストラクタ<BR>
     */
    public WEB3FXTransToFXCompleteSoapResponse()
    {
        
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - リクエストオブジェクト
     */
    protected WEB3FXTransToFXCompleteSoapResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
