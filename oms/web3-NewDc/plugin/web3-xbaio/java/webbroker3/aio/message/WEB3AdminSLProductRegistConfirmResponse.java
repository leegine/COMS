head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductRegistConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録確認レスポンス(WEB3AdminSLProductRegistConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 孟亞南 (中訊) 新規作成 モデルNo.760
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (担保銘柄登録確認レスポンス)<BR>
 * 担保銘柄登録確認レスポンスクラス<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminSLProductRegistConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_regist_confirm";

    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709141333L;

    /**
     * (銘柄登録情報)<BR>
     * 担保銘柄登録情報オブジェクト<BR>
     */
    public WEB3SLProductInfoUnit stockLoanProductInfo;

    /**
     * @@roseuid 46E8908401E1
     */
    public WEB3AdminSLProductRegistConfirmResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - （リクエストオブジェクト）<BR>
     * リクエストオブジェクト
     */
    protected WEB3AdminSLProductRegistConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
