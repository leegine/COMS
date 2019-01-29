head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株価指数オプション取消注文確認リクエスト(WEB3SuccOptionsCancelConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 金シュ (中訊) 新規作成 モデル No.263,305
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3OptionsCancelConfirmRequest;


/**
 * (（連続）株価指数オプション取消注文確認リクエスト)<BR>
 * （連続）株価指数オプション取消注文確認リクエストクラス<BR>
 * <BR>
 * @@author 金シュ
 * @@version 1.0
 */
public class WEB3SuccOptionsCancelConfirmRequest extends WEB3OptionsCancelConfirmRequest
{
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141424L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_cancel_confirm";

    /**
     * @@roseuid 47D9F2C90098
     */
    public WEB3SuccOptionsCancelConfirmRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccOptionsCancelConfirmResponse(this);
    }
}
@
