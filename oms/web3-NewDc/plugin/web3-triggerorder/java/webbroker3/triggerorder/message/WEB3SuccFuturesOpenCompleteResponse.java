head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.40.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesOpenCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）株価指数先物新規建注文完了レスポンス(WEB3SuccFuturesOpenCompleteResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 楊夫志(中訊) 新規作成モデル272,302
 */
package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteResponse;

/**
 * (（連続）株価指数先物新規建注文完了レスポンス)<BR>
 * （連続）株価指数先物新規建注文完了レスポンスクラス<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3SuccFuturesOpenCompleteResponse extends WEB3FuturesOpenMarginCompleteResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121851L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_open_complete";

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request -リクエストオブジェクト
     */
    public WEB3SuccFuturesOpenCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
