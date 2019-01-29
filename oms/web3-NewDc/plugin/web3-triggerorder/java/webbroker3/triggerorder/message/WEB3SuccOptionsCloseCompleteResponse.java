head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsCloseCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株価指数オプション返済注文完了レスポンス(WEB3SuccOptionsCloseCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 金シュ (中訊) 新規作成 モデル No.263,305
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteResponse;


/**
 * (（連続）株価指数オプション返済注文完了レスポンス)<BR>
 * （連続）株価指数オプション返済注文完了レスポンスクラス<BR>
 * <BR>
 * @@author 金シュ
 * @@version 1.0
 */
public class WEB3SuccOptionsCloseCompleteResponse extends WEB3OptionsCloseMarginCompleteResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141455L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_close_complete";

    /**
     * @@roseuid 47D9F2C90395
     */
    public WEB3SuccOptionsCloseCompleteResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する<BR>
     * @@param l_request -リクエストオブジェクト
     */
    public WEB3SuccOptionsCloseCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
