head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.45.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesCloseChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）株価指数先物訂正返済入力画面リクエスト(WEB3SuccFuturesCloseChangeInputRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 楊夫志(中訊) 新規作成モデル272,303
 */
package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeInputRequest;

/**
 * (（連続）株価指数先物訂正返済入力画面リクエスト)<BR>
 * （連続）株価指数先物訂正返済入力画面リクエストクラス<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseChangeInputRequest extends WEB3FuturesCloseMarginChangeInputRequest
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121804L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_close_change_input";

    /**
     * @@roseuid 47D65939007D
     */
    public WEB3SuccFuturesCloseChangeInputRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccFuturesCloseChangeInputResponse(this);
    }
}
@
