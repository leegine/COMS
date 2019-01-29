head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BusinessLayerException.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューション業務第二部
File Name        : 業務アプリケーション業務エラー例外基クラス(WEB3BusinessLayerException.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 中尾　@寿彦(SRA) 新規作成
*/
package webbroker3.common;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * 業務アプリケーションで使用する業務エラーの例外クラス。<BR>
 *<BR>
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 * @@see webbroker3.common.WEB3BaseException
 */
public class WEB3BusinessLayerException extends WEB3BaseException
{
    /**
     * コンストラクタ。<BR>
     *<BR>
     * @@param l_errorInfo 発生したエラーに対応したエラー情報オブジェクト
     * @@param l_strErrorMethod エラーが発生したメソッド名
     */
    public WEB3BusinessLayerException(ErrorInfo l_errorInfo, String l_strErrorMethod)
    {
        super(l_errorInfo, l_strErrorMethod, "");
    }

    /**
     * コンストラクタ。<BR>
     *<BR>
     * @@param l_errorInfo 発生したエラーに対応したエラー情報オブジェクト
     * @@param l_strErrorMethod エラーが発生したメソッド名
     * @@param l_strErrorMessage エラーの内容
     */
    public WEB3BusinessLayerException(ErrorInfo l_errorInfo, String l_strErrorMethod, String l_strErrorMessage)
    {
        super(l_errorInfo, l_strErrorMethod, l_strErrorMessage);
    }

    /**
     * コンストラクタ。<BR>
     *<BR>
     * @@param l_errorInfo 発生したエラーに対応したエラー情報オブジェクト
     * @@param l_strErrorMethod エラーが発生したメソッド名
     * @@param l_strErrorMessage エラーの内容
     * @@param l_exception 発生した例外オブジェクト
     */
    public WEB3BusinessLayerException(ErrorInfo l_errorInfo, String l_strErrorMethod, String l_strErrorMessage, Exception l_exception)
    {
        super(l_errorInfo, l_strErrorMethod, l_strErrorMessage, l_exception);
    }
}
@
