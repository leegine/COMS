head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.39.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ڋq�������̓��N�G�X�g(WEB3AdminTPPaymentRequisitionInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 �И��� (���u) �V�K�쐬 ���f��No.027
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���������ڋq�������̓��N�G�X�g)<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tp_payment_requisition_input";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200810081725L;

    /**
     * @@roseuid 48EC7033021C
     */
    public WEB3AdminTPPaymentRequisitionInputRequest()
    {

    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTPPaymentRequisitionInputResponse();
    }
}
@
