head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.03.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録入力レスポンス(WEB3AdminSLProductRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 孟亞南 (中訊) 新規作成 モデルNo.760
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (担保銘柄登録入力レスポンス)<BR>
 * 担保銘柄登録入力レスポンスクラス<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminSLProductRegistInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_regist_input";

    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709141335L;

    /**
     * (翌営業日日付)<BR>
     * 翌営業日日付<BR>
     */
    public Date nextBizDate;

    /**
     * @@roseuid 46E890840183
     */
    public WEB3AdminSLProductRegistInputResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - （リクエストオブジェクト）<BR>
     * リクエストオブジェクト
     */
    protected WEB3AdminSLProductRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
