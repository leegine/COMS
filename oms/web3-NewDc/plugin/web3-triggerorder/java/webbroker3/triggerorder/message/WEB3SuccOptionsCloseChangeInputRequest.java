head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.45.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsCloseChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株価指数オプション訂正返済入力画面リクエスト(WEB3SuccOptionsCloseChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 金シュ (中訊) 新規作成 モデル No.263,305
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeInputRequest;


/**
 * (（連続）株価指数オプション訂正返済入力画面リクエスト)<BR>
 * （連続）株価指数オプション訂正返済入力画面リクエストクラス<BR>
 * <BR>
 * @@author 金シュ
 * @@version 1.0
 */
public class WEB3SuccOptionsCloseChangeInputRequest extends WEB3OptionsCloseMarginChangeInputRequest
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141445L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_close_change_input";

    /**
     * @@roseuid 47D9F2C9028C
     */
    public WEB3SuccOptionsCloseChangeInputRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccOptionsCloseChangeInputResponse(this);
    }
}
@
