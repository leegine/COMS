head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesOpenInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�����w���敨�V�K���������͉�ʃ��N�G�X�g(WEB3SuccFuturesOpenInputRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 �k�v�u(���u) �V�K�쐬���f��272,302
 */
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3FuturesOpenMarginInputRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�����w���敨�V�K���������͉�ʃ��N�G�X�g)<BR>
 * �i�A���j�����w���敨�V�K���������͉�ʃ��N�G�X�g�N���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3SuccFuturesOpenInputRequest extends WEB3FuturesOpenMarginInputRequest
{

    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    public static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesOpenInputRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121900L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_open_input";

    /**
     * (�A���������ʏ��)<BR>
     * �A���������ʏ��B<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;

    /**
     * @@roseuid 47D6593603D8
     */
    public WEB3SuccFuturesOpenInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccFuturesOpenInputResponse(this);
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
     * �@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02251 <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@"�敨�V�K���i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"�敨�V�K��"<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02252 <BR>
     * <BR>
     * �@@�@@�@@�@@�@@���R�[�h�l�́A�敨OP�\�񒍕��P�ʃe�[�u�����Q�ƁB<BR>
     * <BR>
     * �R�j�@@�A����������敪�`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.�A���������ʏ��.�A����������敪=="�敨�V�K���i�O�񒍕��j"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�����R�[�h�v���ݒ肳��Ă��Ȃ���΁A<BR>
     * �@@�@@�@@�@@�@@�@@�u���̓p�����[�^�`�F�b�N�G���[�B�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00830 <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47A6670402AD
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //  �P�j�@@super.validate()���R�[������B
        super.validate();

        //  �Q�j�@@�A���������ʏ��`�F�b�N
        //  �Q�|�P�j�@@this.�A���������ʏ��null�̏ꍇ�A
        //  �u�A���������ʏ�񂪖��w��v�̗�O���X���[����B
        if (this.succCommonInfo == null)
        {
            log.debug("�A���������ʏ�񂪖��w��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02251,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A���������ʏ�񂪖��w��ł��B");
        }

        //  �Q�|�Q�j�@@�A���������ʏ��.validate()���R�[������B
        this.succCommonInfo.validate();

        //  �Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
        //  �u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
        if (!WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE_ASSUMPTION_ORDER.equals(
                this.succCommonInfo.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE.equals(
                this.succCommonInfo.succTradingType))
        {
            log.debug("�A����������敪�̒l�������ΏۊO");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A����������敪�̒l�������ΏۊO�ł��B");
        }

        //  �R�j�@@�A����������敪�`�F�b�N
        //  �R�|�P�j�@@this.�A���������ʏ��.�A����������敪=="�敨�V�K���i�O�񒍕��j"�̏ꍇ�A
        // �u�����R�[�h�v���ݒ肳��Ă��Ȃ����
        // �u���̓p�����[�^�`�F�b�N�G���[�B�v�̗�O��throw����B
        if (WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType)
            && this.futProductCode == null)
        {
            log.debug("���̓p�����[�^�`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00830,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���̓p�����[�^�`�F�b�N�G���[�B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
