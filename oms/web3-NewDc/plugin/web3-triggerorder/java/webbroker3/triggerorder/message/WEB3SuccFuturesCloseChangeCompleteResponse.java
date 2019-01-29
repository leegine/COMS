head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesCloseChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）株価指数先物訂正返済完了レスポンス(WEB3SuccFuturesCloseChangeCompleteResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 楊夫志(中訊) 新規作成モデル272,303
 */
package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeCompleteResponse;

/**
 * (（連続）株価指数先物訂正返済完了レスポンス)<BR>
 * （連続）株価指数先物訂正返済完了レスポンスクラス<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseChangeCompleteResponse extends WEB3FuturesCloseMarginChangeCompleteResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121753L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_close_change_complete";

    /**
     * @@roseuid 47D659380158
     */
    public WEB3SuccFuturesCloseChangeCompleteResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request -リクエストオブジェクト
     */
    public WEB3SuccFuturesCloseChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
