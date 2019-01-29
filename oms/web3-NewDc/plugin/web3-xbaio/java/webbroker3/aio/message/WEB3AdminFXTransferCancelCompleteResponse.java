head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX振替取消完了レスポンス(WEB3AdminFXTransferCancelCompleteResponse)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (管理者・FX振替取消完了レスポンス) <BR>
 * 管理者・FX振替取消完了レスポンスクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXTransferCancelCompleteResponse extends
    WEB3AdminFXTransferCancelCommonResponse
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
     * @@roseuid 41E790210148
     */
    public WEB3AdminFXTransferCancelCompleteResponse()
    {
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminFXTransferCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
