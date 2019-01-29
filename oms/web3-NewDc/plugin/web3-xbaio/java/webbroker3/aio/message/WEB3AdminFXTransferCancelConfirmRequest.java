head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX振替取消確認リクエスト(WEB3AdminFXTransferCancelConfirmRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX振替取消確認リクエスト) <BR>
 * 管理者・FX振替取消確認リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXTransferCancelConfirmRequest extends
    WEB3AdminFXTransferCancelCommonRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_transfer_cancel_confirm";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * @@roseuid 41E7902000CB
     */
    public WEB3AdminFXTransferCancelConfirmRequest()
    {
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 管理者・FX振替取消確認レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904B029F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXTransferCancelConfirmResponse(this);
    }
}@
