head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録取消確認レスポンス(WEB3AdminSLProductCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 孫洪江 (中訊) 新規作成 仕様変更モデル760
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (担保銘柄登録取消確認レスポンス)<BR>
 * 担保銘柄登録取消確認レスポンスクラス<BR>
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3AdminSLProductCancelConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_sl_product_cancel_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709141409L;

    /**
     * (銘柄登録情報)<BR>
     * 担保銘柄登録情報オブジェクト<BR>
     */
    public WEB3SLProductInfoUnit stockLoanProductInfo;

    /**
     * @@roseuid 46E890850183
     */
    public WEB3AdminSLProductCancelConfirmResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminSLProductCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
