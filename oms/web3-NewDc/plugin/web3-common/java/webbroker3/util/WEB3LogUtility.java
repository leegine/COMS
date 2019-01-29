head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LogUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログ出力ユーティリティクラス(WEB3LogUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 中尾　@寿彦(SRA) 新規作成
                   2004/07/26 張宝楠(中訊) WEB3BaseException -> WEB3Exception
*/
package webbroker3.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import webbroker3.common.WEB3Exception;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3GenRequest;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * ログ出力ユーティリティクラス。<BR>
 *<BR>
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.kernel.util.log.Logit
 */
public final class WEB3LogUtility
{
    /**
     * ログ出力文字列：メソッド名。<BR>
     */
    private final static String METHOD_NAME = "メソッド名：";
    /**
     * ログ出力文字列：ENTER。<BR>
     */
    private final static String ENTER = " ： ENTER";
    /**
     * ログ出力文字列：EXIT。<BR>
     */
    private final static String EXIT = " ： EXIT";
    /**
     * ログ出力文字列：エラーコード。<BR>
     */
    private final static String ERROR_CODE = "\tエラーコード ： ";
    /**
     * ログ出力文字列：タグ名。<BR>
     */
    private final static String ERROR_TAG = "\tタグ名 ： ";
    /**
     * ログ出力文字列：エラーメッセージ。<BR>
     */
    private final static String ERROR_INFO_MESSAGE = "\tエラーメッセージ ： ";
    /**
     * ログ出力文字列：エラー発生メソッド。<BR>
     */
    private final static String ERROR_METHOD = "\tエラー発生メソッド ： ";
    /**
     * ログ出力文字列：エラー内容。<BR>
     */
    private final static String ERROR_MESSAGE = "\tエラー内容 ： ";
    /**
     * ログ出力文字列：スタックトレース。<BR>
     */
    private final static String STACK_TRACE = "\tスタックトレース ： ";
    /**
     * ログ出力文字列：発生した例外のスタックトレース。<BR>
     */
    private final static String EXP_STACK_TRACE = "\t発生した例外のスタックトレース ： ";
    /**
     * 復帰改行文字。<BR>
     */
    private static String newLine = "\n";
    /**
     * 各クラス用の Logit オブジェクトを保持する。<BR>
     */
    private static HashMap logs;
    /**
     * ログ出力を行う Logit オブジェクト。<BR>
     */
    private Logit log;

    static
    {
        logs = new HashMap();
        try
        {
            newLine = System.getProperty("line.separator");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * コンストラクタ。<BR>
     *<BR>
     * @@param l_clazz ログ出力対象のクラス
     */
    private WEB3LogUtility(Class l_clazz)
    {
        log = Logit.getInstance(l_clazz);
    }

    /**
     * WEB3LogUtility クラスのインスタンスを生成して返す。<BR>
     *<BR>
     * @@param l_clazz ログ出力対象のクラス
     * @@return WEB3LogUtility クラスのインスタンス
     */
    public static WEB3LogUtility getInstance(Class l_clazz)
    {
        WEB3LogUtility l_logUtil = null;

        if ((l_logUtil = (WEB3LogUtility)logs.get(l_clazz.getName())) == null)
        {
            l_logUtil = new WEB3LogUtility(l_clazz);
            logs.put(l_clazz.getName(), l_logUtil);
        }
        return l_logUtil;
    }

    /**
     * メソッドに入ったことをログに出力する。<BR>
     *<BR>
     * @@param l_sourceMethod ログ出力対象のメソッド名
     */
    public void entering(String l_sourceMethod)
    {
        if (log.ison())
        {
            log.debug(METHOD_NAME + l_sourceMethod + ENTER);
        }
    }

    /**
     * メソッドから退出したことをログに出力する。<BR>
     *<BR>
     * @@param l_sourceMethod ログ出力対象のメソッド名
     */
    public void exiting(String l_sourceMethod)
    {
        if (log.ison())
        {
            log.debug(METHOD_NAME + l_sourceMethod + EXIT);
        }
    }

    /**
     * デバッグ文をログに出力する。<BR>
     *<BR>
     * @@param l_message デバッグ文
     */
    public void debug(String l_message)
    {
        if (log.ison())
        {
            log.debug(l_message);
        }
    }

    /**
     * 情報をログに出力する。<BR>
     *<BR>
     * @@param l_message 情報
     */
    public void info(String l_message)
    {
        log.info(l_message);
    }

    /**
     * 警告をログに出力する。<BR>
     *<BR>
     * @@param l_message 警告
     */
    public void warn(String l_message)
    {
        log.warn(l_message);
    }

    /**
     * エラーをログに出力する。<BR>
     *<BR>
     * @@param l_message エラー
     */
    public void error(String l_message)
    {
        log.error(l_message);
    }

    /**
     * 業務ロジックでエラーが発生した場合、WEB層にレスポンスを返す前にエラー情報を<BR>
     * ログに出力する（上り処理用）。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     * @@param l_message エラーメッセージ
     * @@param l_exception 例外オブジェクト
     */
    public void error(WEB3GenRequest l_request, String l_message, WEB3Exception l_exception)
    {
        log.error(l_message + exceptionInfo(l_exception));
    }

    /**
     * 業務ロジックでエラーが発生した場合、WEB層にレスポンスを返す前にエラー情報を<BR>
     * ログに出力する（下り処理用）。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     * @@param l_message エラーメッセージ
     * @@param l_exception 例外オブジェクト
     */
    public void error(WEB3BackRequest l_request, String l_message, WEB3Exception l_exception)
    {
        log.error(l_message + exceptionInfo(l_exception));
    }

    /**
     * 業務ロジックでエラーが発生した場合、WEB層にレスポンスを返す前にエラー情報を<BR>
     * ログに出力する（上り処理用）。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     * @@param l_message エラーメッセージ
     * @@param l_errorInfo エラー情報
     * @@param l_exception 例外オブジェクト
     */
    public void error(WEB3GenRequest l_request, String l_message, ErrorInfo l_errorInfo, Exception l_exception)
    {
        log.error(l_message + errorInfo(l_errorInfo, l_exception));
    }

    /**
     * 業務ロジックでエラーが発生した場合、WEB層にレスポンスを返す前にエラー情報を<BR>
     * ログに出力する（下り処理用）。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     * @@param l_message エラーメッセージ
     * @@param l_errorInfo エラー情報
     * @@param l_exception 例外オブジェクト
     */
    public void error(WEB3BackRequest l_request, String l_message, ErrorInfo l_errorInfo, Exception l_exception)
    {
        log.error(l_message + errorInfo(l_errorInfo, l_exception));
    }

    /**
     * デバッグ情報をログに出力する（Debug用）。<BR>
     *<BR>
     * @@param l_message デバッグメッセージ
     * @@param l_throwable 例外オブジェクト
     */
    public void debug(String l_message, Throwable l_throwable)
    {
        if (l_throwable instanceof WEB3Exception)
        {
            if (log.ison())
            {
                log.debug(l_message + exceptionInfo((WEB3Exception)l_throwable));
            }
        }
        else
        {
            log.debug(l_message, l_throwable);
        }
        
    }

    /**
     * 警告をログに出力する（Debug用）。<BR>
     *<BR>
     * @@param l_message 警告メッセージ
     * @@param l_throwable 例外オブジェクト
     */
    public void warn(String l_message, Throwable l_throwable)
    {
        log.warn(l_message, l_throwable);
    }

    /**
     * エラーをログに出力する（Debug用）。<BR>
     *<BR>
     * @@param l_message エラーメッセージ
     * @@param l_throwable 例外オブジェクト
     */
    public void error(String l_message, Throwable l_throwable)
    {
        if (l_throwable instanceof WEB3Exception)
        {
            log.error(l_message + exceptionInfo((WEB3Exception)l_throwable));
        }
        else
        {
            log.error(l_message, l_throwable);
        }       
        
    }
    
    /**
     * Debugモードのオン／オフ判定を行う。<BR>
     * log.ison()の戻り値を返す。<BR>
     * <BR>
     * @@return Debugモードがオンの場合はtrueを、オフの場合はfalseを返す。<BR>
     */
    public boolean ison()
    {
        return log.ison();
    }

    /**
     * 例外オブジェクト情報の文字列を返す。<BR>
     *<BR>
     * @@param l_exception 例外オブジェクト
     * @@return 例外オブジェクト情報の文字列
     */
    private String exceptionInfo(WEB3Exception l_exception)
    {
        StringBuffer l_sbInfo = new StringBuffer("");

        if (l_exception != null)
        {
            ErrorInfo l_errorInfo = l_exception.getErrorInfo();
            l_sbInfo.append(newLine + ERROR_CODE + l_errorInfo.error_code);
            l_sbInfo.append(newLine + ERROR_TAG + l_errorInfo.error_tag);
            l_sbInfo.append(newLine + ERROR_INFO_MESSAGE + l_errorInfo.error_message);
            l_sbInfo.append(newLine + ERROR_METHOD + l_exception.getErrorMethod());
            l_sbInfo.append(newLine + ERROR_MESSAGE + l_exception.getErrorMessage());
            ByteArrayOutputStream l_baos = new ByteArrayOutputStream();
            PrintStream l_ps = new PrintStream(l_baos);
            ((Exception)l_exception).printStackTrace(l_ps);
            l_sbInfo.append(newLine + STACK_TRACE + new String(l_baos.toByteArray()));
            l_ps.close();
            try
            {
                l_baos.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            Exception exp = l_exception.getException();
            if (exp != null)
            {
                l_baos = new ByteArrayOutputStream();
                l_ps = new PrintStream(l_baos);
                exp.printStackTrace(l_ps);
                l_sbInfo.append(newLine + EXP_STACK_TRACE + new String(l_baos.toByteArray()));
                l_ps.close();
                try
                {
                    l_baos.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        return l_sbInfo.toString();
    }

    /**
     * エラー情報の文字列を返す。<BR>
     *<BR>
     * @@param l_errorInfo エラー情報
     * @@param l_exp 例外オブジェクト
     * @@return エラー情報の文字列
     */
    private String errorInfo(ErrorInfo l_errorInfo, Exception l_exp)
    {
        StringBuffer l_sbInfo = new StringBuffer("");

        if (l_errorInfo != null)
        {
            l_sbInfo.append(newLine + ERROR_CODE + l_errorInfo.error_code);
            l_sbInfo.append(newLine + ERROR_TAG + l_errorInfo.error_tag);
            l_sbInfo.append(newLine + ERROR_INFO_MESSAGE + l_errorInfo.error_message);
            if (l_exp != null)
            {
                ByteArrayOutputStream l_baos = new ByteArrayOutputStream();
                PrintStream l_ps = new PrintStream(l_baos);
                l_exp.printStackTrace(l_ps);
                l_sbInfo.append(newLine + STACK_TRACE + new String(l_baos.toByteArray()));
                l_ps.close();
                try
                {
                    l_baos.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        return l_sbInfo.toString();
    }
}
@
