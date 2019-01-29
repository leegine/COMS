head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSExecEndNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)株式出来終了通知レスポンス(WEB3EquityPTSExecEndNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 趙林鵬(中訊) 新規作成 モデルNo.1286
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * ((PTS)株式出来終了通知レスポンス)<BR>
 * (PTS)株式出来終了通知レスポンスクラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3EquityPTSExecEndNotifyResponse extends WEB3BackResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_pts_exec_end_notify";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20080123100000L;

    /**
     * @@roseuid 462CA42601AA
     */
    public WEB3EquityPTSExecEndNotifyResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3EquityPTSExecEndNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
