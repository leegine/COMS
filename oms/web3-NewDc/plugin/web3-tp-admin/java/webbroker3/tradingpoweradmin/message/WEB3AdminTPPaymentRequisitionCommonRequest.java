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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ڋq�������ʃ��N�G�X�g(WEB3AdminTPPaymentRequisitionCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 �И��� (���u) �V�K�쐬 ���f��No.027
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �i���������ڋq�������ʃ��N�G�X�g�j<BR>
 * �i���������ڋq�������ʃ��N�G�X�g�j<BR>
 * <BR>
 * @@author �И���
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
     * (�ڋq����)<BR>
     */
    public String customerAttribute;

    /**
     * (���X�R�[�h)<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     */
    public String accountCode = null;

    /**
     * (���҃R�[�h)<BR>
     */
    public String traderCode = null;

    /**
     * (�������R)<BR>
     */
    public String claimReason;

    /**
     * (����)<BR>
     */
    public String days;

    /**
     * @@roseuid 48EC70340112
     */
    public WEB3AdminTPPaymentRequisitionCommonRequest()
    {

    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
