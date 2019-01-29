head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.41.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）株価指数先物取消注文確認リクエスト(WEB3SuccFuturesCancelConfirmRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 楊夫志(中訊) 新規作成モデル272,303
 */
package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmRequest;

/**
 * (（連続）株価指数先物取消注文確認リクエスト)<BR>
 * （連続）株価指数先物取消注文確認リクエストクラス<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3SuccFuturesCancelConfirmRequest extends WEB3FuturesCancelConfirmRequest
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121737L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_cancel_confirm";

    /**
     * @@roseuid 47D65936030D
     */
    public WEB3SuccFuturesCancelConfirmRequest()
    {

    }
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccFuturesCancelConfirmResponse(this);
    }
}
@
