head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionDownLoadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ڋq�����_�E�����[�h���X�|���X(WEB3AdminTPPaymentRequisitionDownLoadResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 �И��� (���u) �V�K�쐬 ���f��No.027
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���������ڋq�����_�E�����[�h���X�|���X)<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionDownLoadResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tp_payment_requisition_download";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200810081733L;

    /**
     * (�_�E�����[�h�t�@@�C��)<BR>
     */
    public String[] downloadFile;

    /**
     * (���ݓ���)<BR>
     */
    public Date currentDate;

    /**
     * @@roseuid 48EC703302A9
     */
    public WEB3AdminTPPaymentRequisitionDownLoadResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminTPPaymentRequisitionDownLoadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
