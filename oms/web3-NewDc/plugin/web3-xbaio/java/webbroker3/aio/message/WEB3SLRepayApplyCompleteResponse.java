head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayApplyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済申込完了レスポンス(WEB3SLRepayApplyCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 何文敏 (中訊) 新規作成 仕様変更・モデルNo.758
*/

package webbroker3.aio.message;

import java.util.Date;


/**
 * (証券担保ローン返済申込完了レスポンス)<BR>
 * 担保ローン返済完了レスポンスクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3SLRepayApplyCompleteResponse extends WEB3SLRepayApplyCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_apply_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121505L;

    /**
     * (更新時間)<BR>
     * DBの更新時間<BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * (注文ID)<BR>
     * 発注した注文の注文ID<BR>
     */
    public String orderId;

    /**
     * @@roseuid 46E890850377
     */
    public WEB3SLRepayApplyCompleteResponse(WEB3SLRepayApplyCompleteRequest l_request)
    {
        super(l_request);
    }
}
@
