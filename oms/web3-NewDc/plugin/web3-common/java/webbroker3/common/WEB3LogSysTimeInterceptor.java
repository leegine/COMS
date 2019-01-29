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
filename	WEB3LogSysTimeInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理時間ログ出力インタセプタクラス(WEB3LogSysTimeInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 中尾　@寿彦(SRA) 新規作成
*/
package webbroker3.common;

import java.lang.reflect.Method;
import java.util.Date;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * 業務ロジックの開始時間と終了時間をログに出力するクラス。<BR>
 *<BR>
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.kernel.interceptor.Interceptor
 */
public class WEB3LogSysTimeInterceptor implements Interceptor
{
    /**
     * ログ出力文字列（メソッド名 ： ）。<BR>
     */
    private final static String METHOD_NAME = "メソッド名 ： ";
    /**
     * ログ出力文字列（.）。<BR>
     */
    private final static String DOT = ".";
    /**
     * ログ出力文字列（開始時刻 ： ）。<BR>
     */
    private final static String START_DATE = "業務処理開始時刻 ： ";
    /**
     * ログ出力文字列（終了時刻 ： ）。<BR>
     */
    private final static String END_DATE = "業務処理終了時刻 ： ";
    /**
     * インタセプタされたメソッド名。<BR>
     */
    private String methodName;
    /**
     * インタセプタされたメソッドが定義されているクラス。<BR>
     */
    private Class clazz;
    /**
     * ログユーティリティオブジェクト。<BR>
     */
    private WEB3LogUtility log;

    /**
     * メソッドが呼び出され、メソッドの処理が実行される前に呼び出されるメソッド。<BR>
     *<BR>
     * @@param l_method 呼び出されたメソッドオブジェクト
     * @@param l_args メソッドの引数の配列
     * @@return nullが返される
     */
    public Object onCall(Method l_method, Object[] l_args)
    {
        methodName = l_method.getName();
        clazz = l_method.getDeclaringClass();

        log = WEB3LogUtility.getInstance(clazz);
        log.info(startDate());

        return null;
    }

    /**
     * メソッドから復帰した後に呼び出されるメソッド。<BR>
     *<BR>
     * @@param l_context null
     * @@param l_returnValue メソッドの戻り値
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        log.info(endDate());
    }

    /**
     * メソッドが例外をスローした時に呼び出されるメソッド。<BR>
     *<BR>
     * @@param l_context null
     * @@param l_thrownObject スローされたオブジェクト
     */
    public void onThrowable(Object l_context, Throwable l_thrownObject)
    {
        log.info(endDate());
    }

    /**
     * 開始時刻の文字列を返す。<BR>
     *<BR>
     * @@return 開始時刻の文字列
     */
    private String startDate()
    {
        StringBuffer l_sbMessage = new StringBuffer();

        l_sbMessage.append(METHOD_NAME);
        l_sbMessage.append(clazz.getName() + DOT + methodName);
        l_sbMessage.append(START_DATE + new Date().toString());

        return l_sbMessage.toString();
    }

    /**
     * 終了時刻の文字列を返す。<BR>
     *<BR>
     * @@return 終了時刻の文字列
     */
    private String endDate()
    {
        StringBuffer l_sbMessage = new StringBuffer();

        l_sbMessage.append(METHOD_NAME);
        l_sbMessage.append(clazz.getName() + DOT + methodName);
        l_sbMessage.append(END_DATE + new Date().toString());

        return l_sbMessage.toString();
    }
}
@
