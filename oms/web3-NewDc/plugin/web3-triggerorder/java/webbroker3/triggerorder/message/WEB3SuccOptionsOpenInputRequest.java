head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsOpenInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����w���I�v�V�����V�K���������͉�ʃ��N�G�X�g(WEB3SuccOptionsOpenInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 ���V�� (���u) �V�K�쐬 ���f�� No.263,304
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * (�i�A���j�����w���I�v�V�����V�K���������͉�ʃ��N�G�X�g)<BR>
 * �i�A���j�����w���I�v�V�����V�K���������͉�ʃ��N�G�X�g�N���X<BR>
 * <BR>
 * @@author ���V��
 * @@version 1.0
 */
public class WEB3SuccOptionsOpenInputRequest extends WEB3OptionsOpenMarginInputRequest
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccOptionsOpenInputRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141609L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_open_input";

    /**
     * (�A���������ʏ��)<BR>
     * �A���������ʏ��B<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;

    /**
     * @@roseuid 47D9F2CB002A
     */
    public WEB3SuccOptionsOpenInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccOptionsOpenInputResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@super.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�A���������ʏ��`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�A���������ʏ��==null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A���������ʏ�񂪖��w��v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@ tag: BUSINESS_ERROR_02251<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@"OP�V�K���i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"OP�V�K��"<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@ tag: BUSINESS_ERROR_02252<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���R�[�h�l�́A�敨OP�\�񒍕��P�ʃe�[�u�����Q�ƁB<BR>
     * <BR>
     * �R�j�@@�A����������敪�`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.�A���������ʏ��.�A����������敪=="OP�V�K���i�O�񒍕��j"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�����R�[�h�v���ݒ肳��Ă��Ȃ���΁A<BR>
     * �@@�@@�@@�@@�@@�@@�u���̓p�����[�^�`�F�b�N�G���[�B�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@ tag: BUSINESS_ERROR_00830<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47ABAD5F03D5
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@super.validate()���R�[������B
        super.validate();

        //�Q�j�@@�A���������ʏ��`�F�b�N
        // �@@�Q�|�P�j�@@this.�A���������ʏ��==null�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@�u�A���������ʏ�񂪖��w��v�̗�O���X���[����B
        if (this.succCommonInfo == null)
        {
            log.debug("�A���������ʏ�񂪖��w��");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02251,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�A���������ʏ�񂪖��w��");
        }

        //�Q�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
        this.succCommonInfo.validate();

        //�Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
        // �@@�@@�@@�@@�@@"OP�V�K���i�O�񒍕��j"
        // �@@�@@�@@�@@�@@"OP�V�K��"
        //�@@�@@�@@�@@�@@���R�[�h�l�́A�敨OP�\�񒍕��P�ʃe�[�u�����Q�ƁB
        if (!WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER.equals(
                this.succCommonInfo.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_OP.equals(
                this.succCommonInfo.succTradingType))
        {
            log.debug("�A����������敪�̒l�������ΏۊO");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�A����������敪�̒l�������ΏۊO");
        }

        //�R�j�@@�A����������敪�`�F�b�N
        // �@@�R�|�P�j�@@this.�A���������ʏ��.�A����������敪=="OP�V�K���i�O�񒍕��j"�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@�u�����R�[�h�v���ݒ肳��Ă��Ȃ���΁A
        // �@@�@@�@@�@@�@@�@@�u���̓p�����[�^�`�F�b�N�G���[�B�v�̗�O��throw����B
        if (WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType)
            && this.opProductCode == null)
        {
            log.debug("���̓p�����[�^�`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00830,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���̓p�����[�^�`�F�b�N�G���[�B");
        }

    }
}
@
