head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionDetailRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ڋq�����ڍ׃��N�G�X�g(WEB3AdminTPPaymentRequisitionDetailRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 �И��� (���u) �V�K�쐬 ���f��No.027
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���������ڋq�����ڍ׃��N�G�X�g)<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionDetailRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionDetailRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tp_payment_requisition_detail";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200810081729L;

    /**
     * (���X�R�[�h)<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     */
    public String accountCode;

    /**
     * @@roseuid 48EC70330132
     */
    public WEB3AdminTPPaymentRequisitionDetailRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j���X�R�[�h�̃`�F�b�N<BR>
     * �@@���X�R�[�h��null�@@or�@@���X�R�[�h.length��3�ȊO or ���X�R�[�h�����p�����ȊO�̏ꍇ<BR>
     * �@@��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_00779<BR>
     * <BR>
     * �Q�j�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@�ڋq�R�[�h��null�@@or�@@�ڋq�R�[�h.length��6�ȊO or �ڋq�R�[�h�����p�����ȊO�̏ꍇ<BR>
     * �@@��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_00780<BR>
     * <BR>
     * @@roseuid 48C768D600E5
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j���X�R�[�h�̃`�F�b�N
        //�@@���X�R�[�h��null�@@or�@@���X�R�[�h.length��3�ȊO or ���X�R�[�h�����p�����ȊO�̏ꍇ
        //�@@��O���X���[����B
        if (this.branchCode == null
            || this.branchCode.length() != 3
            || !WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("���X�R�[�h�̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̓��͂��s���ł��B");
        }

        // �Q�j�ڋq�R�[�h�̃`�F�b�N
        //�@@�ڋq�R�[�h��null�@@or�@@�ڋq�R�[�h.length��6�ȊO or �ڋq�R�[�h�����p�����ȊO�̏ꍇ��
        //�@@��O���X���[����B
        if (this.accountCode == null
            || this.accountCode.length() != 6
            || !WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("�ڋq�R�[�h�̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̓��͂��s���ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTPPaymentRequisitionDetailResponse();
    }
}
@
