head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求顧客検索入力レスポンス(WEB3AdminTPPaymentRequisitionInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 孟亞南 (中訊) 新規作成 モデルNo.027
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (入金請求顧客検索入力レスポンス)<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tp_payment_requisition_input";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200810081726L;

    /**
     * @@roseuid 48EC703301FD
     */
    public WEB3AdminTPPaymentRequisitionInputResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminTPPaymentRequisitionInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
