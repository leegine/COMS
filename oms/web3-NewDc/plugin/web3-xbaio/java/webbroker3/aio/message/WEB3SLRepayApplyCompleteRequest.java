head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayApplyCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済申込完了リクエスト(WEB3SLRepayApplyCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 何文敏 (中訊) 新規作成 仕様変更・モデルNo.758
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (証券担保ローン返済申込完了リクエスト)<BR>
 * 担保ローン返済完了リクエストクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3SLRepayApplyCompleteRequest extends WEB3SLRepayApplyCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_apply_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121549L;

    /**
     * (暗証番号)<BR>
     * 画面にて入力された暗証番号<BR>
     */
    public String password;

    /**
     * @@roseuid 46E8908503B5
     */
    public WEB3SLRepayApplyCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト<BR>
     * @@roseuid 46BC080901A7
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SLRepayApplyCompleteResponse(this);
    }
}
@
