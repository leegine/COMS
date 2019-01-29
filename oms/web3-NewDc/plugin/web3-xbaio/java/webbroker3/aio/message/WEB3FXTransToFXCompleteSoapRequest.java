head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXCompleteSoapRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXへの振替完了リクエスト（SOAP接続）(WEB3FXTransToFXCompleteSoapRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/09 余新敏(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (FXへの振替完了リクエスト（SOAP接続）)<BR>
 * FXへの振替完了リクエスト（SOAP接続）クラス<BR>
 *
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3FXTransToFXCompleteSoapRequest extends WEB3FXTransToFXAskingRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_to_fx_complete_soap";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200602091650L;

    /**
     * コンストラクタ<BR>
     */
    public WEB3FXTransToFXCompleteSoapRequest()
    {
        
    }
    
    /**
     * (createResponseの実装)<BR>
     * <BR>
     * FXへの振替完了レスポンス（SOAP接続）オブジェクトを返却する。
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXTransToFXCompleteSoapResponse(this);
    }
}
@
