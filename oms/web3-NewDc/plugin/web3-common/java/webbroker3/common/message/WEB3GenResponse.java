head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GenResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 上り処理用レスポンスの基底クラス(WEB3GenResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 中尾　@寿彦(SRA) 新規作成
Revesion History : 2004/07/29 鄒 政(中訊) errorMessageを追加
Revesion History : 2006/10/27 栄イ (中訊) 仕様変更 モデル212を対応
*/
package webbroker3.common.message;

import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * 上り処理用レスポンスの基底クラス。<BR>
 *<BR>
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.kernel.message.Response
 */
public abstract class WEB3GenResponse extends Response
{
    /**
     * エラー情報。
     */
    public ErrorInfo errorInfo = null;
    
    /**
     * エラーメッセージ。<BR>
     * エラー情報に付加するメッセージを指定する。
     */
    public String errorMessage = null;

    /**
     * コンプライアンス情報
     */
    public WEB3ComplianceInfoUnit complianceInfo = null;

    /**
     * コンストラクタ。
     */
    public WEB3GenResponse()
    {
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3GenResponse(WEB3GenRequest l_request)
    {
    }
}
@
