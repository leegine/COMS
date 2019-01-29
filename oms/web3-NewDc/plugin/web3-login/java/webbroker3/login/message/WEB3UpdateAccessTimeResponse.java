head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3UpdateAccessTimeResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : セッションアクセス時刻更新結果を返すレスポンスクラス(WEB3UpdateAccessTimeResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/26 菊地(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (セッションアクセス時刻更新レスポンス)<BR>
 * セッションアクセス時刻更新結果を返すレスポンスクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3UpdateAccessTimeResponse extends WEB3GenResponse
{
    /**
     * TAGNAME
     */
    public static final String TAGNAME = "response";

    /**
     * PTYPE
     */
    public static final String PTYPE = "web3_update_access_time";

    /**
     * SerialVersionUID
     */
    public final static long serialVersionUID = 200402261800L;

    /**
     * デフォルトコンストラクタ。<BR>
     * @@roseuid 403EF12C017D
     */
    public WEB3UpdateAccessTimeResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<BR>
     * @@param l_request
     * @@roseuid 403EF19C015E
     */
    public WEB3UpdateAccessTimeResponse(WEB3UpdateAccessTimeRequest l_request)
    {
        super(l_request);
    }
}
@
