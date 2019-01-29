head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録取消完了レスポンス(WEB3AdminSLProductCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 孫洪江 (中訊) 新規作成 仕様変更モデル760
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (担保銘柄登録取消完了レスポンス)<BR>
 * 担保銘柄登録取消完了レスポンスクラス<BR>
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3AdminSLProductCancelCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_sl_product_cancel_complete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709141438L;

    /**
     * @@roseuid 46E8908501F0
     */
    public WEB3AdminSLProductCancelCompleteResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminSLProductCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
