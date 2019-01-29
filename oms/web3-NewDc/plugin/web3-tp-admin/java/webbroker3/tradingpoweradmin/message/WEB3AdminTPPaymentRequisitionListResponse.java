head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ڋq�����ꗗ���X�|���X(WEB3AdminTPPaymentRequisitionListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 �И��� (���u) �V�K�쐬 ���f��No.027
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���������ڋq�����ꗗ���X�|���X)<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tp_payment_requisition_list";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200810081728L;

    /**
     * (�v�Z��)<BR>
     */
    public Date calcDate;

    /**
     * (�������R)<BR>
     */
    public String claimReason;

    /**
     * (����)<BR>
     */
    public String days = null;

    /**
     * (���y�[�W��)<BR>
     */
    public String totalPages;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     */
    public String pageIndex;

    /**
     * (�����R�[�h��)<BR>
     */
    public String totalRecords;

    /**
     * (���������ڋq�������ꗗ)<BR>
     */
    public WEB3AdminTPPaymentRequisitionListUnit[] paymentRequisitionListUnit = null;

    /**
     * @@roseuid 48EC7033025A
     */
    public WEB3AdminTPPaymentRequisitionListResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminTPPaymentRequisitionListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
