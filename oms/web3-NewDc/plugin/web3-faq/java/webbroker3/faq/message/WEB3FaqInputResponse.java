head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 問合せ管理お問合せ入力レスポンス(WEB3FaqInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (問合せ管理お問合せ入力レスポンス)<BR>
 * 問合せ管理お問合せ入力レスポンス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3FaqInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "faq_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412171301L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     * <BR>
     * ※ログイン済の場合、セットする。<BR>
     * ※ログインしていない場合、null。<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     * <BR>
     * <BR>
     * ※ログイン済の場合、セットする。<BR>
     * ※ログインしていない場合、null。<BR>
     */
    public String accountCode;

    /**
     * (顧客名)<BR>
     * 顧客名<BR>
     * <BR>
     * ※ログイン済の場合、セットする。<BR>
     * ※ログインしていない場合、null。<BR>
     */
    public String accountName;

    /**
     * (メールアドレス)<BR>
     * メールアドレス<BR>
     * <BR>
     * ※ログイン済の場合、セットする。<BR>
     * ※ログインしていない場合、null。<BR>
     */
    public String mailAddress;

    /**
     * @@roseuid 41C25C060119
     */
    public WEB3FaqInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FaqInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
