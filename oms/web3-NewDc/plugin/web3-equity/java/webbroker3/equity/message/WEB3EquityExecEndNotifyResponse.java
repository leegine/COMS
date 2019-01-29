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
filename	WEB3EquityExecEndNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出来終了通知レスポンスクラス(WEB3EquityExecEndNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 鄭海良(中訊) 新規作成
                   2004/12/06 岡村和明(SRA) 残案件対応 Ｎｏ.３３５
                   2004/12/21 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * 株式出来終了通知レスポンス）。<br>
 * <br>
 * 株式出来終了通知レスポンスクラス
 * @@version 1.0
 */
public class WEB3EquityExecEndNotifyResponse extends WEB3BackResponse
{

    /**
     * <p>（serialVersionUID）。</p>
     */
    public static final long serialVersionUID = 200405211030L;

    /**
     * <p>（PTYPE）。</p>
     */
    public static final String PTYPE = "equity_exec_end_notify";

    /**
     * <p>（株式出来終了通知レスポンス）。</p>
     * <p>コンストラクタ。</p>
     */
    public WEB3EquityExecEndNotifyResponse()
    {

    }

    /**
     * <p>（株式出来終了通知レスポンス）。</p>
     * <p>コンストラクタ。</p>
     * @@param l_request 株式出来終了通知リクエスト
      */
    public WEB3EquityExecEndNotifyResponse(WEB3EquityExecEndNotifyRequest l_request)
    {
        super(l_request);
    }

}
@
