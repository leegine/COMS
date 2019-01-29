head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsOpenInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株価指数オプション新規建注文入力画面レスポンス(WEB3SuccOptionsOpenInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 金シュ (中訊) 新規作成 モデル No.263,304
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputResponse;


/**
 * (（連続）株価指数オプション新規建注文入力画面レスポンス)<BR>
 * （連続）株価指数オプション新規建注文入力画面レスポンスクラス<BR>
 * <BR>
 * @@author 金シュ
 * @@version 1.0
 */
public class WEB3SuccOptionsOpenInputResponse extends WEB3OptionsOpenMarginInputResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141610L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_open_input";

    /**
     * (注文数量)<BR>
     * 注文数量。<BR>
     * （反対取引の場合のみセット）<BR>
     */
    public String orderQuantity;

    /**
     * @@roseuid 47D9F2CB0079
     */
    public WEB3SuccOptionsOpenInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する<BR>
     * @@param l_request -リクエストオブジェクト
     */
    public WEB3SuccOptionsOpenInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
