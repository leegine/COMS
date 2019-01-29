head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.05.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashTransferListInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金一覧入力リクエスト(WEB3AdminAioCashTransferListInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/03 何文敏 (中訊) 新規作成　@仕様変更モデル NO.693
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (入出金一覧入力リクエスト)<BR>
 * 入出金一覧入力リクエストクラス<BR>
 *
 * @@author 何文敏 (中訊)
 * @@version 1.0
 */
public class WEB3AdminAioCashTransferListInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cash_transfer_list_input";

    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200702051000L;

    /**
     * @@roseuid 45C3F15702AF
     */
    public WEB3AdminAioCashTransferListInputRequest()
    {

    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB660189
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAioCashTransferListInputResponse(this);
    }
}
@
