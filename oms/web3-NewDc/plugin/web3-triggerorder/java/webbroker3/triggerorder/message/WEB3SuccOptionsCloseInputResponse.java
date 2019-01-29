head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsCloseInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株価指数オプション返済入力画面レスポンス(WEB3SuccOptionsCloseInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 金シュ (中訊) 新規作成 モデル No.263,305
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginInputResponse;


/**
 * (（連続）株価指数オプション返済入力画面レスポンス)<BR>
 * （連続）株価指数オプション返済入力画面レスポンスクラス<BR>
 * <BR>
 * @@author 金シュ
 * @@version 1.0
 */
public class WEB3SuccOptionsCloseInputResponse extends WEB3OptionsCloseMarginInputResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141529L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_close_input";

    /**
     * (注文数量)<BR>
     * 注文数量<BR>
     * （反対取引の場合のみセット）<BR>
     */
    public String orderQuantity;

    /**
     * @@roseuid 47D9F2CA00B7
     */
    public WEB3SuccOptionsCloseInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成するBR>
     * @@param l_request -リクエストオブジェクト
     */
    public WEB3SuccOptionsCloseInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
