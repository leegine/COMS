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
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : üà¿Úqõ¤ÊNGXg(WEB3AdminTPPaymentRequisitionCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 Ð±ì (u) VKì¬ fNo.027
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * iüà¿Úqõ¤ÊNGXgj<BR>
 * iüà¿Úqõ¤ÊNGXgj<BR>
 * <BR>
 * @@author Ð±ì
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
     * (Úq®«)<BR>
     */
    public String customerAttribute;

    /**
     * (XR[h)<BR>
     */
    public String branchCode;

    /**
     * (ÚqR[h)<BR>
     */
    public String accountCode = null;

    /**
     * (µÒR[h)<BR>
     */
    public String traderCode = null;

    /**
     * (¿R)<BR>
     */
    public String claimReason;

    /**
     * (ú)<BR>
     */
    public String days;

    /**
     * @@roseuid 48EC70340112
     */
    public WEB3AdminTPPaymentRequisitionCommonRequest()
    {

    }

    /**
     * X|Xf[^ðì¬·éB<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
