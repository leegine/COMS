head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済取消完了レスポンス(WEB3SLRepayCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 何文敏 (中訊) 新規作成 仕様変更・モデルNo.758
*/

package webbroker3.aio.message;

import java.util.Date;

/**
 * (証券担保ローン返済取消完了レスポンス)<BR>
 * 担保ローン返済取消完了レスポンスクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3SLRepayCancelCompleteResponse extends WEB3SLRepayCancelCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_cancel_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121513L;

    /**
     * (更新時間)<BR>
     * DBの更新時間<BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * @@roseuid 46E890860173
     */
    public WEB3SLRepayCancelCompleteResponse(WEB3SLRepayCancelCompleteRequest l_request)
    {
        super(l_request);
    }
}
@
