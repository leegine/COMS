head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLAccountOpenApplyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SL口座開設申込レスポンス(WEB3SLAccountOpenApplyResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 何文敏 (中訊) 新規作成 仕様変更・モデルNo.754
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (SL口座開設申込レスポンス)<BR>
 * SL口座開設申込レスポンスクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */

public class WEB3SLAccountOpenApplyResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_account_open_apply";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709071029L;

    /**
     * (顧客基本情報)<BR>
     * 顧客基本情報<BR>
     */
    public WEB3SLAccountBaseInfoUnit accountBaseInfo;

    /**
     * @@roseuid 46E0BE47031C
     */
    public WEB3SLAccountOpenApplyResponse(WEB3SLAccountOpenApplyRequest l_request)
    {
        super(l_request);
    }
}
@
