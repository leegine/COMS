head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3EquityWLimitManualCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式W指値注文手動切替完了リクエスト(WEB3EquityWLimitManualCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/11/20　@齊珂(中訊) 新規作成 （モデル）No.180
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (株式W指値注文手動切替完了リクエスト)<BR>
 *
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3EquityWLimitManualCompleteRequest extends WEB3EquityManualCompleteRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_w_limit_manual_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200611221347L;

    /**
     * コンストラクタ<BR>
     */
    public WEB3EquityWLimitManualCompleteRequest()
    {

    }

    /**
     * (createResponseの実装)<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityManualCompleteResponse();
    }
}
@
