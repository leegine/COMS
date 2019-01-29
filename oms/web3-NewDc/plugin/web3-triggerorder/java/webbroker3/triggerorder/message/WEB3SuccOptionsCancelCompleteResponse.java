head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株価指数オプション取消注文完了レスポンス(WEB3SuccOptionsCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 金シュ (中訊) 新規作成 モデル No.263,305
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3OptionsCancelCompleteResponse;


/**
 * (（連続）株価指数オプション取消注文完了レスポンス)<BR>
 * （連続）株価指数オプション取消注文完了レスポンスクラス<BR>
 * <BR>
 * @@author 金シュ
 * @@version 1.0
 */
public class WEB3SuccOptionsCancelCompleteResponse extends WEB3OptionsCancelCompleteResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141418L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_cancel_complete";

    /**
     * @@roseuid 47D9F2C9004A
     */
    public WEB3SuccOptionsCancelCompleteResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する<BR>
     * @@param l_request -リクエストオブジェクト
     */
    public WEB3SuccOptionsCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
