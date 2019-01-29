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
filename	WEB3EquityChangeCancelAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式訂正取消受付レスポンス(WEB3EquityChangeCancelAcceptResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 周玲玲 (中訊) 新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * （株式訂正取消受付レスポンス）。<BR>
 * <BR>
 * 株式訂正取消受付レスポンスクラス。
 * @@version 1.0
 */
public class WEB3EquityChangeCancelAcceptResponse extends WEB3BackResponse
{
    /**
     * ポリモルフィックタイプ。<BR>
     */
    public static final String PTYPE = "equity_change_cancel_accept";

    /**
     * タグ名。<BR>
     */
    public static final String TAGNAME = "response";

    /**
     * シリアルバージョンUID。<BR>
     */
    public static final long serialVersionUID = 200402241330L;

    /**
     * コンストラクタ。<BR>
     */
    public WEB3EquityChangeCancelAcceptResponse()
    {
    }

    /**
     * コンストラクタ。<BR>
     *
     * @@param request リクエストクラス
     */
    public WEB3EquityChangeCancelAcceptResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
