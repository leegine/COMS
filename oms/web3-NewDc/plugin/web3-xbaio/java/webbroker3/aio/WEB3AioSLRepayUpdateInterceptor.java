head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSLRepayUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍύX�V�C���^�Z�v�^(WEB3AioSLRepayUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/12 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.759�CNo.784
*/

package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�،��S�ۃ��[���ԍύX�V�C���^�Z�v�^)<BR>
 * �،��S�ۃ��[���ԍύX�V�C���^�Z�v�^<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AioSLRepayUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayUpdateInterceptor.class);

    /**
     * (���o���������e)<BR>
     * ���o���������e<BR>
     */
    private WEB3AioNewOrderSpec aioOrderSpec;

    /**
     * (������)<BR>
     * ������<BR>
     */
    private Date orderBizDate;

    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    private Date deliveryDate;

    /**
     * (�،��S�ۃ��[���ԍύX�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �C���X�^���X�𐶐����A�����̓��o���������e���v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_aioOrderSpec - (���o���������e)<BR>
     * ���o���������e�I�u�W�F�N�g<BR>
     * <BR>
     * @@roseuid 46D7C3A6035E
     */
    public WEB3AioSLRepayUpdateInterceptor(WEB3AioNewOrderSpec l_aioOrderSpec)
    {
        this.aioOrderSpec = l_aioOrderSpec;
    }

    /**
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)��ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl<BR>
     * �@@���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     *  �u�،��S�ۃ��[���ԍϐ\��_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE<BR>
     * <BR>
     * �iOrderManagerPersistenceType�ɂĒ萔��`�B�j<BR>
     * <BR>
     * @@param l_persistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_aioOrderUnitParams - (�����P��Params)<BR>
     * �����P�ʂ̃p�����[�^�N���X<BR>
     * <BR>
     * @@return AioOrderUnitParams
     * @@roseuid 46D7C194025C
     */
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_persistenceContext,
        AioOrderUnitParams l_aioOrderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType,"
            + "OrderManagerPersistenceContext, AioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_aioOrderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�u�،��S�ۃ��[���ԍϐ\��_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB
        // �����J�e�S��
        // 9�F���o��
        l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);
        // ������
        // �،��S�ۃ��[���X�V�C���^�Z�v�^.������
        l_aioOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
            this.orderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
        // ��n��
        // �،��S�ۃ��[���X�V�C���^�Z�v�^.��n��
        l_aioOrderUnitParams.setDeliveryDate(this.deliveryDate);
        // �ŋ敪
        // �f�t�H���g�F0
        l_aioOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
        // �~�j���敪
        // 0�FFALSE�i�~�j���łȂ��j
        l_aioOrderUnitParams.setMiniStockDiv(BooleanEnum.FALSE.intValue() + "");
        // �󒍓���
        // ThreadLocalSystemAttributesRegistry.getAttributes(�hxblocks.gtl.attributes.systemtimestamp�h)�̖߂�l
        l_aioOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        // ���҃R�[�h�iSONAR�j
        l_aioOrderUnitParams.setSonarTraderCode(null);
        // ���ʃR�[�h
        l_aioOrderUnitParams.setOrderRequestNumber(null);
        // .com�f�r�b�g���ώ���ԍ�
        l_aioOrderUnitParams.setComDebitNumber(null);
        // �ۏ؋��敪
        l_aioOrderUnitParams.setGuaranteeDiv(null);
        // �o���\���敪
        l_aioOrderUnitParams.setPaymentApplicationDiv(null);
        // ��������敪
        // 0�F�����l
        l_aioOrderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        // �����o�H�敪
        // �Z�b�V�������擾���������ڂ̒l
        OpLoginSecurityService l_opLoginService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_opLoginService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        l_aioOrderUnitParams.setOrderRootDiv(l_strOrderRootDiv);
        // �����G���[���R�R�[�h
        // 0000�F����
        l_aioOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        // MQ�X�e�[�^�X
        // 0:�����M
        l_aioOrderUnitParams.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
        // �ʉ݃R�[�h
        l_aioOrderUnitParams.setCurrencyCode(null);
        // ���o�����z(�~���Z�z)
        l_aioOrderUnitParams.setConvertAmount(null);

        log.exiting(STR_METHOD_NAME);
        return l_aioOrderUnitParams;
    }

    /**
     * (set������)<BR>
     * ���������Z�b�g����B<BR>
     * @@param l_datOrderBizDate - (������)<BR>
     * ������<BR>
     * @@roseuid 46D7C17802D5
     */
    public void setOrderBizDate(Date l_datOrderBizDate)
    {
        this.orderBizDate = l_datOrderBizDate;
    }

    /**
     * (set��n��)<BR>
     * ��n�����Z�b�g����B<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@roseuid 46D7C1800354
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        this.deliveryDate = l_datDeliveryDate;
    }
}
@
