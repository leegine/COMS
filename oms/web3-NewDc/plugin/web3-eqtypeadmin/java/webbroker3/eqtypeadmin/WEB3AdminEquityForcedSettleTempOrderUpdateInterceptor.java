head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ω������X�V�C���^�Z�v�^(WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 �����q(���u) �V�K�쐬 �d�l�ύX���f��No.131�A���f��No.147�A���f��No.154
*/
package webbroker3.eqtypeadmin;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ForcedExpireType;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3MarginCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.util.WEB3LogUtility;


/**
 * (�������ω������X�V�C���^�Z�v�^)<BR>
 * �������ω������o�^���́ADB�X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR>
 * �iEqTypeOrderManagerPersistenceEventInterceptor�̎����j<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor extends
    WEB3MarginCloseMarginUpdateInterceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor.class);

    /**
     * (�������ϗ��R�敪)<BR>
     * �������ϗ��R�敪<BR>
     */
    private String forcedSettleReasonType;

    /**
     * (���F��ԋ敪)<BR>
     * ���F��ԋ敪<BR>
     */
    private String approveStatusType;

    /**
     * (�ۏ؋��ێ���)<BR>
     * �ۏ؋��ێ���<BR>
     */
    private Double marginMaintenanceRate;

    /**
     * (�Ǐؔ�����)<BR>
     * �Ǐؔ�����<BR>
     */
    private Date additionalMarginDate;

    /**
     * (�Ǐ،o�ߓ���)<BR>
     * �Ǐ،o�ߓ���<BR>
     */
    private Integer additionalMarginAccruedDays;

    /**
     * (�������ω������X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �P�j�@@�p���������ڂ̒l�Z�b�g<BR>
     * �@@�X�[�p�[�N���X�̃R���X�g���N�^��call����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�M�p�ԍϒ������e�F�@@����.�M�p�ԍϒ������e<BR>
     * �@@�@@�@@�萔���F�@@����.�萔��<BR>
     * �@@�@@�@@�T�Z���ϑ��v����v�Z���ʁF�@@����.�T�Z���ϑ��v����v�Z����<BR>
     * �@@�@@�@@�ٍϋ敪�F�@@����.�ٍϋ敪<BR>
     * �@@�@@�@@�ٍϊ����l�F�@@����.�ٍϊ����l<BR>
     * �@@�@@�@@���񒍕��̒����`���l���F�@@����.���񒍕��̒����`���l��<BR>
     * �@@�@@�@@�����o�H�敪�F�@@����.�����o�H�敪<BR>
     * <BR>
     * �Q�j�@@�g�����ڂ̒l�Z�b�g<BR>
     * �@@�����Ɏw�肳�ꂽ���̑��̃I�u�W�F�N�g�^�l���A�����̃v���p�e�B�ɐݒ肷��B<BR>
     * <BR>
     * @@param l_eqTypeSettleContractOrderSpec - (�M�p�ԍϒ������e)<BR>
     * �M�p�ԍϒ������e<BR>
     * @@param l_commission - (�萔��)<BR>
     * �萔��<BR>
     * @@param l_equityRealizedProfitAndLossPrice - (�T�Z���ϑ��v����v�Z����)<BR>
     * �T�Z���ϑ��v����v�Z����<BR>
     * @@param l_strRepaymentDiv - (�ٍϋ敪)<BR>
     * �ٍϋ敪<BR>
     * @@param l_dbRepaymentNum - (�ٍϊ����l)<BR>
     * �ٍϊ����l<BR>
     * @@param l_strOrderChanel - (���񒍕��̒����`���l��)<BR>
     * ���񒍕��̒����`���l��<BR>
     * @@param l_strOrderRootDiv - (�����o�H�敪)<BR>
     * �����o�H�敪<BR>
     * @@param l_strForcedSettleReasonDiv - (�������ϗ��R�敪)<BR>
     * �������ϗ��R�敪<BR>
     * @@param l_strComondebiStatusDiv - (���F��ԋ敪)<BR>
     * ���F��ԋ敪<BR>
     * @@param l_dbMarginMaintenanceRate - (�ۏ؋��ێ���)<BR>
     * �ۏ؋��ێ���<BR>
     * @@param l_datAdditionalBizDate - (�Ǐؔ�����)<BR>
     * �Ǐؔ�����<BR>
     * @@param l_dbAdditionalElapsedDays - (�Ǐ،o�ߓ���)<BR>
     * �Ǐ،o�ߓ���<BR>
     * @@roseuid 460B99A30399
     */
    public WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor(
        WEB3MarginSettleContractOrderSpec l_eqTypeSettleContractOrderSpec,
        WEB3GentradeCommission l_commission,
        WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice,
        String l_strRepaymentDiv,
        double l_dbRepaymentNum,
        String l_strOrderChanel,
        String l_strOrderRootDiv,
        String l_strForcedSettleReasonDiv,
        String l_strComondebiStatusDiv,
        Double l_dbMarginMaintenanceRate,
        Date l_datAdditionalBizDate,
        Integer l_dbAdditionalElapsedDays)
    {
        // �P�j�@@�p���������ڂ̒l�Z�b�g<BR>
        // �X�[�p�[�N���X�̃R���X�g���N�^��call����B
        super(l_eqTypeSettleContractOrderSpec,
                l_commission,
                l_equityRealizedProfitAndLossPrice,
                l_strRepaymentDiv,
                l_dbRepaymentNum,
                l_strOrderChanel,
                l_strOrderRootDiv);

        // �g�����ڂ̒l�Z�b�g
        // �����Ɏw�肳�ꂽ���̑��̃I�u�W�F�N�g�^�l���A�����̃v���p�e�B�ɐݒ肷��B
        this.additionalMarginAccruedDays = l_dbAdditionalElapsedDays;
        this.additionalMarginDate = l_datAdditionalBizDate;
        this.approveStatusType = l_strComondebiStatusDiv;
        this.forcedSettleReasonType = l_strForcedSettleReasonDiv;
        this.marginMaintenanceRate = l_dbMarginMaintenanceRate;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * ���������ω������̍X�V���s���B<BR>
     * <BR>
     * �P�j�@@�������ϊ֘A���ڈȊO�̒l�Z�b�g<BR>
     * �@@super.�X�V�l�ݒ��call����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�X�V�^�C�v�F�@@����.�X�V�^�C�v<BR>
     * �@@�@@�@@�����F�@@����.����<BR>
     * �@@�@@�@@�����P��Params�F�@@����.�����P��Params<BR>
     * <BR>
     * �Q�j�@@�������ϊ֘A���ڂ̒l�Z�b�g<BR>
     * �@@�P�j�̖߂�l�ɁA���N���X���ێ�����v���p�e�B�l���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �@@�X�V���e��DB�ݒ�_���u�������o�^_���������P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * EqTypeOrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_context - (����)<BR>
     * �iEqTypeOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_eqtypeOrderUnitParams - (�����P��Params)<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 460B8DC200C7
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext,EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�������ϊ֘A���ڈȊO�̒l�Z�b�g
        // super.�X�V�l�ݒ��call����B
        super.mutate(l_updateType, l_context, l_eqtypeOrderUnitParams);

        // �Q�j�@@�������ϊ֘A���ڂ̒l�Z�b�g
        if (l_eqtypeOrderUnitParams == null)
        {
            log.error("�p�����[�^�l�s���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // ���F�҃R�[�h
        l_eqtypeOrderUnitParams.setApproverCode(null);

        // ���F�^�񏳔F����
        l_eqtypeOrderUnitParams.setApproveTimestamp(null);

        // �������ϗ��R�敪
        // �������ω������X�V�C���^�Z�v�^�̓������ڒl
        l_eqtypeOrderUnitParams.setForcedSettleReasonType(this.forcedSettleReasonType);

        // ���F��ԋ敪
        // �������ω������X�V�C���^�Z�v�^�̓������ڒl
        l_eqtypeOrderUnitParams.setApproveStatusType(this.approveStatusType);

        // �ۏ؋��ێ���
        // �������ω������X�V�C���^�Z�v�^�̓������ڒl
        l_eqtypeOrderUnitParams.setMarginMaintenanceRate(this.marginMaintenanceRate);

        // �Ǐؔ�����
        // �������ω������X�V�C���^�Z�v�^�̓������ڒl
        l_eqtypeOrderUnitParams.setAdditionalMarginDate(this.additionalMarginDate);

        // �Ǐ،o�ߓ���
        // �������ω������X�V�C���^�Z�v�^�̓������ڒl
        if (additionalMarginAccruedDays != null)
        {
            l_eqtypeOrderUnitParams.setAdditionalMarginAccruedDays(
                this.additionalMarginAccruedDays.longValue());
        }

        // ���������敪
        // 0�F�I�[�v��
        l_eqtypeOrderUnitParams.setForcedExpireType(WEB3ForcedExpireType.OPENING);

        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
   }
}
@
