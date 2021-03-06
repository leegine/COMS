head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferCancelCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX振替取消共通レスポンス(WEB3AdminFXTransferCancelCommonResponse)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX振替取消共通レスポンス) <BR>
 * 管理者・FX振替取消共通レスポンスクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXTransferCancelCommonResponse extends WEB3GenResponse
{
    /**
     * (FX振替明細一覧) <BR>
     * FX振替明細の一覧
     */
    public WEB3FXTransferDetailUnit[] fxTransferDetailList;

    /**
     * @@roseuid 41E7902002DE
     */
    public WEB3AdminFXTransferCancelCommonResponse()
    {
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminFXTransferCancelCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
