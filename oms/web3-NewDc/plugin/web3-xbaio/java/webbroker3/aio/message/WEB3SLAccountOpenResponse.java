head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLAccountOpenResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SL口座開設レスポンス (WEB3SLAccountOpenResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 何文敏 (中訊) 新規作成 仕様変更・モデルNo.754
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (SL口座開設レスポンス)<BR>
 * SL口座開設レスポンスクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3SLAccountOpenResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_account_open";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709071047L;

    /**
     * (ストックローン口座番号)<BR>
     * ストックローン口座番号<BR>
     */
    public String stockLoanAccount;

    /**
     * (外部接続URL)<BR>
     * 外部接続URL<BR>
     */
    public String url;

    /**
     * (顧客基本情報)<BR>
     * 顧客基本情報<BR>
     */
    public WEB3SLAccountBaseInfoUnit accountBaseInfo;

    /**
     * @@param l_request - SL口座開設リクエスト
     * @@roseuid 46E0BE47031C
     */
    public WEB3SLAccountOpenResponse(WEB3SLAccountOpenRequest l_request)
    {
        super(l_request);
    }
}
@
