head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3LogoutResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : ログアウト結果を返すレスポンスクラス(WEB3LogoutResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/26 菊地(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (ログアウトレスポンス)<BR>
 * ログアウト結果を返すレスポンスクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3LogoutResponse extends WEB3GenResponse
{
    /**
     * TAGNAME
     */
    public static final String TAGNAME = "response";

    /**
     * PTYPE
     */
    public static final String PTYPE = "web3_logout";

    /**
     * SerialVersionUID
     */
    public final static long serialVersionUID = 200402171348L;

    /**
     * デフォルトコンストラクタ。<BR>
     * @@roseuid 403EEF0800E1
     */
    public WEB3LogoutResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<BR>
     * @@param l_request
     * @@roseuid 403EF2F10045
     */
    public WEB3LogoutResponse(WEB3LogoutRequest l_request)
    {
        super(l_request);
    }
}
@
