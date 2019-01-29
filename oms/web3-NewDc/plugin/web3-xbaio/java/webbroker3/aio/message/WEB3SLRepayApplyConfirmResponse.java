head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayApplyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済申込確認レスポンス(WEB3SLRepayApplyConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 何文敏 (中訊) 新規作成 仕様変更・モデルNo.758
*/

package webbroker3.aio.message;

/**
 * (証券担保ローン返済申込確認レスポンス)<BR>
 * 担保ローン返済確認レスポンスクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3SLRepayApplyConfirmResponse extends WEB3SLRepayApplyCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_apply_confirm";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121547L;

    /**
     * @@roseuid 46E89085030A
     */
    public WEB3SLRepayApplyConfirmResponse(WEB3SLRepayApplyConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
