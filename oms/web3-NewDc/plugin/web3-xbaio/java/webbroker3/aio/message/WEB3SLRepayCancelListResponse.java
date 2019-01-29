head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayCancelListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済一覧レスポンス(WEB3SLRepayCancelListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 何文敏 (中訊) 新規作成 仕様変更・モデルNo.758
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (証券担保ローン返済一覧レスポンス)<BR>
 * 担保ローン返済一覧レスポンスクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3SLRepayCancelListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_cancel_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121512L;

    /**
     * (証券担保ローン返済明細)<BR>
     * 証券担保ローン返済対象となる注文のリスト<BR>
     */
    public WEB3SLRepayUnit[] stockLoanRepayUnits;

    /**
     * @@roseuid 46E890860079
     */
    public WEB3SLRepayCancelListResponse(WEB3SLRepayCancelListRequest l_request)
    {
        super(l_request);
    }
}
@
