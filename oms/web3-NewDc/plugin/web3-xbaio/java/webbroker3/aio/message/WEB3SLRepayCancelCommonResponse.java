head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayCancelCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済取消共通レスポンス(WEB3SLRepayCancelCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 何文敏 (中訊) 新規作成 仕様変更・モデルNo.758
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (証券担保ローン返済取消共通レスポンス)<BR>
 * 担保ローン返済取消共通レスポンスクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3SLRepayCancelCommonResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_cancel_common";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121540L;

    /**
     * (注文ID)<BR>
     * 取消対象となっている注文の注文ID<BR>
     */
    public String orderId;

    /**
     * (返済予定日)<BR>
     * 取消対象となっている注文の返済予定日<BR>
     */
    public Date repayScheduledDate;

    /**
     * (返済額)<BR>
     * 取消対象となっている注文の返済額<BR>
     */
    public String repayAmt;

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3SLRepayCancelCommonResponse(WEB3SLRepayCancelCommonRequest l_request)
    {
        super(l_request);
    }
}
@
