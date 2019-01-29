head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.11.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductRegistCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録完了レスポンス(WEB3AdminSLProductRegistCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 孟亞南 (中訊) 新規作成 モデルNo.760
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (担保銘柄登録完了レスポンス)<BR>
 * 担保銘柄登録完了レスポンスクラス<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminSLProductRegistCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_regist_complete";

    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709141338L;

    /**
     * @@roseuid 46E89084023E
     */
    public WEB3AdminSLProductRegistCompleteResponse()
    {
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - （リクエストオブジェクト）<BR>
     * リクエストオブジェクト
     */
    protected WEB3AdminSLProductRegistCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
