head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済取消確認レスポンス(WEB3SLRepayCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 何文敏 (中訊) 新規作成 仕様変更・モデルNo.758
*/

package webbroker3.aio.message;


/**
 * (証券担保ローン返済取消確認レスポンス)<BR>
 * 担保ローン返済取消確認レスポンスクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3SLRepayCancelConfirmResponse extends WEB3SLRepayCancelCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_cancel_confirm";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121511L;

    /**
     * (返済可能額)<BR>
     * 顧客の現在の返済可能額<BR>
     */
    public String repayableAmt;

    /**
     * @@roseuid 46E890860106
     */
    public WEB3SLRepayCancelConfirmResponse(WEB3SLRepayCancelConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
