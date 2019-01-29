head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DuplicationMailAddressCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール重複チェック定数定義インタフェイス(WEB3DuplicationMailAddressCheckDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/19 凌建平(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * メール重複チェック 定数定義インタフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3DuplicationMailAddressCheckDef
{

	/**
     * 0：重複メールアドレスチェックを行わない。<BR>
     */
    public final static String DEFAULT = "0";

    /**
     * 1：重複メールアドレスチェックを行なう。<BR>
     * （重複アドレス存在時、例外生成無し）<BR>
     */
    public final static String NO_EXCEPTION = "1";

    /**
     * 2：重複メールアドレスチェックを行なう。<BR>
     * （重複アドレス存在時、例外を生成）<BR>
     */
    public final static String CREATE_EXCEPTION = "2";

    /**
     * 3：重複メールアドレスチェックを行なう。<BR>
     * （重複アドレス存在時、「ログインユーザが管理者以外の場合」は例外を生成）<BR>
     */
    public final static String CREATE_EXCEPTION_CUST = "3";

    /**
     * 4：重複メールアドレスチェックを行なう。<BR>
     * （重複アドレス存在時、「ログインユーザが管理者の場合」は例外を生成）<BR>
     */
    public final static String CREATE_EXCEPTION_ADMIN = "4";
} @
