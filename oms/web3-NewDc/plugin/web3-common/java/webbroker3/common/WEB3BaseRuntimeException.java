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
filename	WEB3BaseRuntimeException.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : システムRuntimeException例外基底クラス(WEB3BaseRuntimeException.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 張宝楠(中訊) 新規作成
*/
package webbroker3.common;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * 業務アプリケーションで使用するRuntimeException例外クラスの基底クラス。<BR>
 *<BR>
 * @@author 張宝楠(中訊)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
 */
public class WEB3BaseRuntimeException extends RuntimeException implements WEB3Exception
{
    /**
     * エラー情報。<BR>
     */
    private ErrorInfo errorInfo;
    /**
     * エラーが発生したメソッド名。<BR>
     */
    private String errorMethod;
    /**
     * 追加メッセージ。<BR>
     */
    private String errorMessage;
    /**
     * 発生した例外オブジェクト。<BR>
     */
    private Exception exception;

    /**
     * コンストラクタ。<BR>
     *<BR>
     * @@param l_errorInfo 発生したエラーに対応したエラー情報オブジェクト
     * @@param l_strErrorMethod エラーが発生したメソッド名
     */
    public WEB3BaseRuntimeException(ErrorInfo l_errorInfo, String l_strErrorMethod)
    {
        errorInfo = l_errorInfo;
        errorMethod = l_strErrorMethod;
    }

    /**
     * コンストラクタ。<BR>
     *<BR>
     * @@param l_errorInfo 発生したエラーに対応したエラー情報オブジェクト
     * @@param l_strErrorMethod エラーが発生したメソッド名
     * @@param l_exception 発生した例外オブジェクト
     */
    public WEB3BaseRuntimeException(ErrorInfo l_errorInfo, String l_strErrorMethod, Exception l_exception)
    {

        errorInfo = l_errorInfo;
        errorMethod = l_strErrorMethod;
        exception = l_exception;
    }

    /**
     * コンストラクタ。<BR>
     *<BR>
     * @@param l_errorInfo 発生したエラーに対応したエラー情報オブジェクト
     * @@param l_strErrorMethod エラーが発生したメソッド名
     * @@param l_strErrorMessage エラーの内容
     */
    public WEB3BaseRuntimeException(ErrorInfo l_errorInfo, String l_strErrorMethod, String l_strErrorMessage)
    {

        errorInfo = l_errorInfo;
        errorMethod = l_strErrorMethod;
        errorMessage = l_strErrorMessage;
    }

    /**
     * コンストラクタ。<BR>
     *<BR>
     * @@param l_errorInfo 発生したエラーに対応したエラー情報オブジェクト
     * @@param l_strErrorMethod エラーが発生したメソッド名
     * @@param l_strErrorMessage エラーの内容
     * @@param l_exception 発生した例外オブジェクト
     */
    public WEB3BaseRuntimeException(ErrorInfo l_errorInfo, String l_strErrorMethod, String l_strErrorMessage, Exception l_exception)
    {

        errorInfo = l_errorInfo;
        errorMethod = l_strErrorMethod;
        errorMessage = l_strErrorMessage;
        exception = l_exception;
    }

    /**
     * エラー情報を返す。<BR>
     *<BR>
     * @@return エラー情報
     */
    public ErrorInfo getErrorInfo()
    {
        return errorInfo;
    }

    /**
     * エラーが発生したメソッド名を返す。<BR>
     *<BR>
     * @@return エラーが発生したメソッド名
     */
    public String getErrorMethod()
    {
        return errorMethod;
    }

    /**
     * エラー内容を返す。<BR>
     *<BR>
     * @@return エラー内容
     */
    public String getErrorMessage()
    {
        return errorMessage;
    }

    /**
     * 例外オブジェクトを返す。<BR>
     *<BR>
     * @@return 例外オブジェクト
     */
    public Exception getException()
    {
        return exception;
    }
}
@
