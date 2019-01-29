head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.45.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesCloseInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）株価指数先物返済入力画面レスポンス(WEB3SuccFuturesCloseInputResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 楊夫志(中訊) 新規作成モデル272,303
 */
package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputResponse;

/**
 * (（連続）株価指数先物返済入力画面レスポンス)<BR>
 * （連続）株価指数先物返済入力画面レスポンスクラス<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseInputResponse extends WEB3FuturesCloseMarginInputResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121829L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_close_input";

    /**
     * (注文数量)<BR>
     * 注文数量<BR>
     * （反対取引の場合のみセット）<BR>
     */
    public String orderQuantity;

    /**
     * @@roseuid 47D6593801C5
     */
    public WEB3SuccFuturesCloseInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * <BR>
     * @@param l_request -リクエストオブジェクト
     */
    public WEB3SuccFuturesCloseInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
