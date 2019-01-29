head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayApplyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済申込入力レスポンス(WEB3SLRepayApplyInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 何文敏 (中訊) 新規作成 仕様変更・モデルNo.758
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (証券担保ローン返済申込入力レスポンス)<BR>
 * 担保ローン返済入力レスポンスクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3SLRepayApplyInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_apply_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121510L;

    /**
     * (返済予定日)<BR>
     * （T+1～T+4)の返済予定日のリスト<BR>
     */
    public Date[] repayScheduledDateList;

    /**
     * (返済可能額)<BR>
     * 顧客の（T+1～T+4)の返済可能額のリスト<BR>
     */
    public String[] repayableAmtList;

    /**
     * @@roseuid 46E89085029C
     */
    public WEB3SLRepayApplyInputResponse(WEB3SLRepayApplyInputRequest l_request)
    {
        super(l_request);
    }
}
@
