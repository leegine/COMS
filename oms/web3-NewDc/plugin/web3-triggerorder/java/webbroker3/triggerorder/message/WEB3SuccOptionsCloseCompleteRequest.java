head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsCloseCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����w���I�v�V�����ԍϒ����������N�G�X�g(WEB3SuccOptionsCloseCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 ���V�� (���u) �V�K�쐬 ���f�� No.263,305
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * (�i�A���j�����w���I�v�V�����ԍϒ����������N�G�X�g)<BR>
 * �i�A���j�����w���I�v�V�����ԍϒ����������N�G�X�g�N���X<BR>
 * <BR>
 * @@author ���V��
 * @@version 1.0
 */
public class WEB3SuccOptionsCloseCompleteRequest extends WEB3OptionsCloseMarginCompleteRequest
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccOptionsCloseCompleteRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141452L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_close_complete";

    /**
     * (�A���������ʏ��)<BR>
     * �A���������ʏ��B<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;

    /**
     * (�P�������l���)<BR>
     * �P�������l���B<BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;

    /**
     * (������P��)<BR>
     * ������P���B <BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 47D9F2C90338
     */
    public WEB3SuccOptionsCloseCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccOptionsCloseCompleteResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�A���������ʏ��`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�A���������ʏ��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A���������ʏ��w��Ȃ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@ tag: BUSINESS_ERROR_02251<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B<BR>
     * <BR>
     * �@@�P�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@"OP�ԍρi�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"OP�ԍρi�����c�j"<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@ tag: BUSINESS_ERROR_02252<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���R�[�h�l�́A�敨OP�\�񒍕��P�ʃe�[�u�����Q�ƁB<BR>
     * <BR>
     * �Q�j�@@this.�A���������ʏ��.�A����������敪=="OP�ԍρi�����c�j"�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@super.validate()���R�[������B<BR>
     * �@@�@@�@@�ȊO�Asuper.validateAT���Ύ��()���R�[������B<BR>
     * <BR>
     * �R�j�@@�A�������P�������l���`�F�b�N<BR>
     * �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�A���������ʏ��.�A����������敪��"OP�ԍρi�O�񒍕��j"�ł����<BR>
     * �@@�@@�@@�@@�@@�@@�u�A����������敪���A�A�������́}�w�l�w��s�̋敪�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@ tag: BUSINESS_ERROR_02253<BR>
     * <BR>
     * �@@�R�|�R�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��<BR>
     * �@@�@@�@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@ tag: BUSINESS_ERROR_02254<BR>
     * <BR>
     * �S�j�@@this.�A���������ʏ��.�A����������敪=="OP�ԍρi�O�񒍕��j"�̏ꍇ�A<BR>
     * �@@�@@�@@���N�G�X�g.���Ϗ���==�inull or "������"�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�u�A�������E���Ύ�����̌��Ϗ����w��s���B�v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@ tag: BUSINESS_ERROR_02306<BR>
     * <BR>
     * �T�j�@@�A�������E���������`�F�b�N<BR>
     * �@@super.validate�A������()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B0FAEC0364
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�A���������ʏ��`�F�b�N
        // �@@�P�|�P�j�@@this.�A���������ʏ��null�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@�u�A���������ʏ��w��Ȃ��v�̗�O���X���[����B
        if (this.succCommonInfo == null)
        {
            log.debug("�A���������ʏ�񂪖��w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02251,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�A���������ʏ�񂪖��w��ł��B");
        }

        //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
        this.succCommonInfo.validate();

        //�P�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
        // �@@�@@�@@�@@�@@"OP�ԍρi�O�񒍕��j"
        // �@@�@@�@@�@@�@@"OP�ԍρi�����c�j"
        // �@@�@@�@@�@@�@@���R�[�h�l�́A�敨OP�\�񒍕��P�ʃe�[�u�����Q�ƁB
        if (!WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(
                this.succCommonInfo.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(
                this.succCommonInfo.succTradingType))
        {
            log.debug("�A����������敪�̒l�������ΏۊO");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�A����������敪�̒l�������ΏۊO");
        }

        //�Q�j�@@this.�A���������ʏ��.�A����������敪=="OP�ԍρi�����c�j"�̏ꍇ�̂݁A
        // �@@�@@�@@super.validate()���R�[������B
        // �@@�@@�@@�ȊO�Asuper.validateAT���Ύ��()���R�[������B
        if (WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(
            this.succCommonInfo.succTradingType))
        {
            super.validate();
        }
        else
        {
            super.validateATReserveOrder();
        }

        //�R�j�@@�A�������P�������l���`�F�b�N
        // �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B
        if (this.priceAdjustmentValueInfo != null)
        {
            this.priceAdjustmentValueInfo.validate();

            //�R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A
            // �@@�@@�@@�@@�@@�@@�A���������ʏ��.�A����������敪��"OP�ԍρi�O�񒍕��j"�ł����
            // �@@�@@�@@�@@�@@�@@�u�A����������敪���A�A�������́}�w�l�w��s�̋敪�v�̗�O��throw����B
            if (!WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(
                succCommonInfo.succTradingType))
            {
                log.debug("�A����������敪���A�A�������́}�w�l�w��s�̋敪");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02253,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�A����������敪���A�A�������́}�w�l�w��s�̋敪");
            }

            //�R�|�R�j�@@�A�������P�������l���null�̏ꍇ�A
            // �@@�@@�@@�@@�@@�@@�����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
            // �@@�@@�@@�@@�@@�@@��O��throw����B
            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                log.debug("�P�������l�ƒ����P���敪�̎w�肪�s�����ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�������l�ƒ����P���敪�̎w�肪�s�����ł��B");
            }
        }

        //�S�j�@@this.�A���������ʏ��.�A����������敪=="OP�ԍρi�O�񒍕��j"�̏ꍇ�A
        //�@@�@@�@@���N�G�X�g.���Ϗ���==�inull or "������"�j�̏ꍇ�́A
        // �@@�@@�@@�u�A�������E���Ύ�����̌��Ϗ����w��s���B�v�̗�O��throw����B
        if (WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(
            this.succCommonInfo.succTradingType))
        {
            if (this.closingOrder == null
                || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
            {
                log.debug("�A�������̔��Ύ���w�莞�́A���Ϗ����敪�Ɂi�����_���^�P���v���^�P�������j�̂����ꂩ���w�肵�Ă��������B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02306,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�A�������̔��Ύ���w�莞�́A���Ϗ����敪�Ɂi�����_���^�P���v���^�P�������j�̂����ꂩ���w�肵�Ă��������B");
            }
        }

        //�T�j�@@�A�������E���������`�F�b�N
        // �@@super.validate�A������()���R�[������B
        super.validateSuccOrder();
        log.exiting(STR_METHOD_NAME);
    }
}

@
