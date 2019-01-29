head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文約定履歴レスポンス(WEB3EquityOrderHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 張坤芳 (中訊) 新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （現物株式注文約定履歴レスポンス）。<BR>
 * <BR>
 * 現物株式注文約定履歴応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquityOrderHistoryResponse extends WEB3GenResponse
{

    /**
     * (注文履歴一覧)<BR>
     * 現物株式注文約定履歴明細オブジェクト一覧<BR>
     */
    public webbroker3.equity.message.WEB3EquityChangeCancelHistoryGroup[] changeCancelHistoryGroups;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_order_history";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405211058L;

    /**
     * @@roseuid 40A288B4006D
     */
    public WEB3EquityOrderHistoryResponse(WEB3EquityOrderHistoryRequest l_request)
    {
        super(l_request);
    }
    
    /**
     * @@roseuid 40A288B4006D
     */
    public WEB3EquityOrderHistoryResponse()
    {
    }    
}
@
