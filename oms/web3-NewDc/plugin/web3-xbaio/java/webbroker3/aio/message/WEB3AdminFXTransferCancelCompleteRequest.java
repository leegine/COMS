head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX振替取消完了リクエスト(WEB3AdminFXTransferCancelCompleteRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX振替取消完了リクエスト) <BR>
 * 管理者・FX振替取消完了リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXTransferCancelCompleteRequest extends
    WEB3AdminFXTransferCancelCommonRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_transfer_cancel_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (暗証番号) <BR>
     * 画面から入力された暗証番号
     */
    public String password;

    /**
     * @@roseuid 41E7902001C5
     */
    public WEB3AdminFXTransferCancelCompleteRequest()
    {
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 管理者・FX振替取消完了レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904B029F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXTransferCancelCompleteResponse(this);
    }
}@
