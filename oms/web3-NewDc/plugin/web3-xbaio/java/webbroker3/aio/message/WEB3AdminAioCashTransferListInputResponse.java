head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashTransferListInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金一覧入力レスポンス(WEB3AdminAioCashTransferListInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/03 何文敏 (中訊) 新規作成　@仕様変更モデル NO.693
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (入出金一覧入力レスポンス)<BR>
 * 入出金一覧入力レスポンスクラス<BR>
 *
 * @@author 何文敏 (中訊)
 * @@version 1.0
 */
public class WEB3AdminAioCashTransferListInputResponse extends WEB3GenResponse
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
     * (受渡日)<BR>
     * デフォルト表示となる受渡日<BR>
     */
    public Date deliveryDate;

    /**
     * @@roseuid 45C3F1570232
     */
    public WEB3AdminAioCashTransferListInputResponse() 
    {

    }

    /**
     * @@roseuid 4158EB66008E
     */
    public WEB3AdminAioCashTransferListInputResponse(
        WEB3AdminAioCashTransferListInputRequest l_request)
    {
        super(l_request);
    }
}
@
