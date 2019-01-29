head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3Exception.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3例外インタフェース(WEB3Exception.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 張宝楠(中訊) 新規作成
*/
package webbroker3.common;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * 業務アプリケーションで使用する例外クラスのインタフェース。<BR>
 *<BR>
 * @@author 張宝楠(中訊)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
 */
public interface WEB3Exception
{
    
    /**
     * エラー情報を返す。<BR>
     *<BR>
     * @@return エラー情報
     */
    public ErrorInfo getErrorInfo();

    /**
     * エラーが発生したメソッド名を返す。<BR>
     *<BR>
     * @@return エラーが発生したメソッド名
     */
    public String getErrorMethod();

    /**
     * エラー内容を返す。<BR>
     *<BR>
     * @@return エラー内容
     */
    public String getErrorMessage();

    /**
     * 例外オブジェクトを返す。<BR>
     *<BR>
     * @@return 例外オブジェクト
     */
    public Exception getException();
}
@
