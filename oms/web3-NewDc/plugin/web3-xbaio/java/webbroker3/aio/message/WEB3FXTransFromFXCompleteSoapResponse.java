head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXCompleteSoapResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
Author Name         : Daiwa Institute of Research
File Name           : FXから振替完了レスポンス（SOAP接続）(WEB3FXTransFromFXCompleteSoapResponse.java)
Revision History    : 2008/04/08 王志葵(中訊) 新規作成 モデルNo.837
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (FXから振替完了レスポンス（SOAP接続）)<BR>
 * FXから振替完了レスポンス（SOAP接続）クラス<BR>
 * <BR>
 * @@author 王志葵
 * @@version 1.0
 */
public class WEB3FXTransFromFXCompleteSoapResponse extends WEB3FXTransFromFXCompleteResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "fx_trans_from_fx_complete_soap";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200804081428L;

    /**
     * (FXから振替完了レスポンス（SOAP接続）)<BR>
     * デフォルトコンストラクタ<BR>
     */
    public WEB3FXTransFromFXCompleteSoapResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FXTransFromFXCompleteSoapResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
