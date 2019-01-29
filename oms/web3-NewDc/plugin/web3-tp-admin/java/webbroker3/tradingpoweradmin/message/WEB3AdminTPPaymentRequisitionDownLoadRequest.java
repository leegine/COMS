head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionDownLoadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ڋq�����_�E�����[�h���N�G�X�g(WEB3AdminTPPaymentRequisitionDownLoadRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 �И��� (���u) �V�K�쐬 ���f��No.027
Revision History : 2008/10/15 �I�O (���u) ���f��No.034 ���f��No.039
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPClaimReasonDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPDaysDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���������ڋq�����_�E�����[�h���N�G�X�g)<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionDownLoadRequest extends WEB3AdminTPPaymentRequisitionCommonRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionDownLoadRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tp_payment_requisition_download";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200810081731L;

    /**
     * @@roseuid 48EC70B8002A
     */
    public WEB3AdminTPPaymentRequisitionDownLoadRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�ڋq�����̃`�F�b�N<BR>
     * �@@�ڋq������null�̏ꍇ<BR>
     * �@@��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_03140<BR>
     * <BR>
     * �Q�j�������R�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�������R��null�̏ꍇ<BR>
     * �@@��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_03136<BR>
     * <BR>
     * �@@�Q�|�Q�j���֋�/���ʗ��֋��A�s�����i�����j�܂��͎w��Ȃ����I������Ă���ꍇ<BR>
     * �@@������ 0 �łȂ���΁A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_03138<BR>
     * <BR>
     * �R�j���X�R�[�h�̃`�F�b�N<BR>
     * �@@���X�R�[�h��null�@@or�@@���X�R�[�h.length��3�ȊO or ���X�R�[�h�����p�����ȊO�̏ꍇ<BR>
     * �@@��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_00779<BR>
     * <BR>
     * �S�j�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@�ڋq�R�[�h��null�ȊO�@@and�@@�i�ڋq�R�[�h.length��6�ȊO or �ڋq�R�[�h�����p�����ȊO�j�̏ꍇ<BR>
     * �@@��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_00780<BR>
     * <BR>
     * �T�j���҃R�[�h�̃`�F�b�N<BR>
     * �@@���҃R�[�h��null�ȊO�@@and�@@���҃R�[�h.length��5�ȊO�̏ꍇ<BR>
     * �@@��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_01912<BR>
     * <BR>
     * @@roseuid 48D1F805008E
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�ڋq�����̃`�F�b�N
        //�@@�ڋq������null�̏ꍇ
        //�@@��O���X���[����B 
        if (this.customerAttribute == null)
        {
            log.debug("�ڋq������null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03140,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq������null�ł��B");
        }

        //�Q�j�������R�̃`�F�b�N
        //�@@�Q�|�P�j�������R��null�̏ꍇ
        //�@@��O���X���[����B
        if (this.claimReason == null)
        {
            log.debug("�������R��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03136,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������R��null�ł��B");
        }

        //�Q�|�Q�j���֋�/���ʗ��֋��A�s�����i�����j�܂��͎w��Ȃ����I������Ă���ꍇ
        //������ 0 �łȂ���΁A��O���X���[����B
        if (WEB3AdminTPClaimReasonDef.DEBIT_AMOUNT_SPECIAL.equals(this.claimReason)
            || WEB3AdminTPClaimReasonDef.SHORT_FALL_GENERATION_TODAY.equals(this.claimReason)
            || WEB3AdminTPClaimReasonDef.DEFAULT.equals(this.claimReason))
        {
            if (!WEB3AdminTPDaysDef.DAYS_0.equals(this.days))
            {
                log.debug("���֋�/���ʗ��֋��A�s�����i�����j�܂��͎w��Ȃ����I������Ă���ꍇ�A" +
                    "������0�ł͂Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03138,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���֋�/���ʗ��֋��A�s�����i�����j�܂��͎w��Ȃ����I������Ă���ꍇ�A������0�ł͂Ȃ��B");
            }
        }

        //�R�j���X�R�[�h�̃`�F�b�N
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

        //�S�j�ڋq�R�[�h�̃`�F�b�N
        //�@@�ڋq�R�[�h��null�ȊO�@@and�@@�i�ڋq�R�[�h.length��6�ȊO or �ڋq�R�[�h�����p�����ȊO�j�̏ꍇ
        //�@@��O���X���[����B
        if (this.accountCode != null
            && (this.accountCode.length() != 6
                || !WEB3StringTypeUtility.isDigit(this.accountCode)))
        {
            log.debug("�ڋq�R�[�h�̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̓��͂��s���ł��B");
        }

        //�T�j���҃R�[�h�̃`�F�b�N
        //�@@���҃R�[�h��null�ȊO�@@and�@@���҃R�[�h.length��5�ȊO�̏ꍇ
        //�@@��O���X���[����B
        if (this.traderCode != null && this.traderCode.length() != 5)
        {
            log.debug("���҃R�[�h�i������j�̒������s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01912,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���҃R�[�h�i������j�̒������s���ł��B");
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
        return new WEB3AdminTPPaymentRequisitionDownLoadResponse();
    }
}
@
