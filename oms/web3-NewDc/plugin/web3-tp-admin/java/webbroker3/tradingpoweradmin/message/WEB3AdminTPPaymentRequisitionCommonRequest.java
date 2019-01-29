head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求顧客検索共通リクエスト(WEB3AdminTPPaymentRequisitionCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 孟亞南 (中訊) 新規作成 モデルNo.027
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * （入金請求顧客検索共通リクエスト）<BR>
 * （入金請求顧客検索共通リクエスト）<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionCommonRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tp_payment_requisition_common";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200810081735L;

    /**
     * (顧客属性)<BR>
     */
    public String customerAttribute;

    /**
     * (部店コード)<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     */
    public String accountCode = null;

    /**
     * (扱者コード)<BR>
     */
    public String traderCode = null;

    /**
     * (請求事由)<BR>
     */
    public String claimReason;

    /**
     * (日数)<BR>
     */
    public String days;

    /**
     * @@roseuid 48EC70340112
     */
    public WEB3AdminTPPaymentRequisitionCommonRequest()
    {

    }

    /**
     * レスポンスデータを作成する。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
