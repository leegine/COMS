head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.11.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLAccountOpenApplyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SL口座開設申込リクエスト(WEB3SLAccountOpenApplyRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 何文敏 (中訊) 新規作成 仕様変更・モデルNo.754
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (SL口座開設申込リクエスト)<BR>
 * SL口座開設申込リクエストクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3SLAccountOpenApplyRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_account_open_apply";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709071018L;

    /**
     * @@roseuid 46E0BE48003E
     */
    public WEB3SLAccountOpenApplyRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     * @@roseuid 46BBFB58039B
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SLAccountOpenApplyResponse(this);
    }
}
@
