head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecNotifyMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式出来通知メインレスポンス(WEB3EquityExecNotifyMainResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 中尾寿彦 (SRA) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * （株式出来通知メインレスポンス）。<br>
 * <br>
 * 株式出来通知メインレスポンスクラス
 * @@author 中尾寿彦
 * @@version 1.0
 */
public class WEB3EquityExecNotifyMainResponse extends WEB3BackResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_execNotifyMain";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412010000L;

    /**
     * デフォルトコンストラクタ<BR>
     */
    public WEB3EquityExecNotifyMainResponse()
    {
    }

    /**
     * (コンストラクタ)<BR>
     * <BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3EquityExecNotifyMainResponse(WEB3EquityExecNotifyMainRequest l_request)
    {
        super(l_request);
    }
}
@
