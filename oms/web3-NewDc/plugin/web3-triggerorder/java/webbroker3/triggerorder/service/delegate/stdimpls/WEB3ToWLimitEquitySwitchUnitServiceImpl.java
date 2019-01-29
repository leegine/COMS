head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEquitySwitchUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : W�w�l�������������ؑֈꌏ�T�[�r�XImpl(WEB3ToWLimitEquitySwitchUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/17 ���G�� (���u) �V�K�쐬 �i���f���jNo.176
Revesion History : 2006/11/30 ���G�� (���u)�i���f���j No.193 No.194�@@No.195
Revesion History : 2006/12/11 ���G�� (���u)�i���f���jNo.207
Revesion History : 2007/01/15 ���G�� (���u)�i���f���jNo.220
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.WEB3EquityChangeOrderSpec;
import webbroker3.equity.WEB3EquityChangeOrderUnitEntry;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderManagerChangeOrderEventInterceptor;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquitySwitchUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (W�w�l�������������ؑֈꌏ�T�[�r�XImpl)
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3ToWLimitEquitySwitchUnitServiceImpl
    extends WEB3ToWLimitEqTypeSwitchUnitServiceImpl
    implements WEB3ToWLimitEquitySwitchUnitService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3ToWLimitEquitySwitchUnitServiceImpl.class);

    /**
     * (create�����������e)<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����������e���쐬����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����P��.getDataSourceObject()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����P��.�����ID��null�̏ꍇ�A<BR>
     * �@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrader()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[getTrader()�̈���] <BR>
     * �@@�@@�@@�����ID�F�@@�p�����[�^.�����P��.�����ID <BR>
     * <BR>
     * �R�j�@@�p�����[�^.�⏕����.getInstitution()���R�[������B<BR>
     * <BR>
     * �S�j�@@this.create�������������l�ڍ�()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[create�������������l�ڍ�()�̈���] <BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �T�j�@@���������������e�I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * �@@�@@�@@[���������������e()�̈���] <BR>
     * �@@�@@�@@����ID�F�@@�p�����[�^.�����P��.����ID <BR>
     * �@@�@@�@@�������������l�ڍׁF�@@create�������������l�ڍ�()�̖߂�l <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@getInstitution().getInstitutionCode()�̖߂�l <BR>
     * �@@�@@�@@�����`���l���F�@@�����P��Row.���񒍕��̒����`���l�� <BR>
     * �@@�@@�@@���ҁF�@@�����P��.�����ID��null�̏ꍇ�AgetTrader()�̖߂�l <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��L�ȊO�Anull <BR>
     * <BR>
     * �U�j�@@���������������e��ԋp����B<BR>
     * <BR>
     * @@param l_eqTypeOrderUnit - (�����P��)
     * @@param l_subAccount - (�⏕����)
     * @@return ChangeOrderSpec
     * @@throws WEB3BaseException
     */
    public ChangeOrderSpec createChangeOrderSpec(
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createChangeOrderSpec(EqTypeOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null || l_subAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j
        //�����������e���쐬����B
        //�P�j�@@�p�����[�^.�����P��.getDataSourceObject()���R�[������B
        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();

        //����
        Trader l_trader = null;
        try
        {
            //�Q�j�@@�����P��.�����ID��null�̏ꍇ�A
            //�@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrader()���R�[������B
            //�@@�@@�@@[getTrader()�̈���]
            //�@@�@@�@@�����ID�F�@@�p�����[�^.�����P��.�����ID
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            if (!l_eqTypeOrderUnitRow.getTraderIdIsNull())
            {
                l_trader = l_finObjManager.getTrader(l_eqTypeOrderUnitRow.getTraderId());
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�R�j�@@�p�����[�^.�⏕����.getInstitution()���R�[������B
        Institution l_institution = l_subAccount.getInstitution();

        //�S�j�@@this.create�������������l�ڍ�()���R�[������B
        //�@@�@@�@@[create�������������l�ڍ�()�̈���]
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            this.createChangeOrderUnitEntry(l_eqTypeOrderUnit);

        //�T�j�@@���������������e�I�u�W�F�N�g�𐶐�����B
        //�@@�@@�@@[���������������e()�̈���]
        //�@@�@@�@@����ID�F�@@�p�����[�^.�����P��.����ID
        //�@@�@@�@@�������������l�ڍׁF�@@create�������������l�ڍ�()�̖߂�l
        //�@@�@@�@@�،���ЃR�[�h�F�@@getInstitution().getInstitutionCode()�̖߂�l
        //�@@�@@�@@�����`���l���F�@@�����P��Row.���񒍕��̒����`���l��
        //�@@�@@�@@���ҁF�@@�����P��.�����ID��null�̏ꍇ�AgetTrader()�̖߂�l
        //�@@�@@�@@�@@�@@�@@�@@�@@��L�ȊO�Anull
        WEB3EquityChangeOrderSpec l_changeOrderSpec =
            new WEB3EquityChangeOrderSpec(
                l_eqTypeOrderUnitRow.getOrderId(),
                l_changeOrderUnitEntry,
                l_institution.getInstitutionCode(),
                l_eqTypeOrderUnitRow.getOrderChanel(),
                l_trader);

        //�U�j�@@���������������e��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_changeOrderSpec;
    }

    /**
     * (create�������������l�ڍ�)<BR>
     * ���������l�ڍ׃I�u�W�F�N�g�𐶐����A <BR>
     * �p�����[�^.�����P�ʂ̓��e���v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�����P��.getDataSourceObject()���R�[������B  <BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�o����܂Œ����P��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[is�o����܂Œ����P��()�̈���] <BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �R�j�@@�������������l�ڍ׃I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * �@@�@@�@@[�������������l�ڍ�()�̈���] <BR>
     * �@@�@@�@@�����㎷�s�����F�@@�����P��Row.�iW�w�l�j���s���� <BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * �@@�@@�@@������is�o����܂Œ����F�@@is�o����܂Œ����P��()�̖߂�l <BR>
     * �@@�@@�@@������l�i�����F�@@�����P��Row.�l�i���� <BR>
     * �@@�@@�@@�����㔭�������F�@@�����P��Row.�������� <BR>
     * �@@�@@�@@�����㔭���������Z�q�F�@@�����P��Row.�����������Z�q  <BR>
     * �@@�@@�@@������t�w�l��l�F�@@�����P��Row.�t�w�l��l <BR>
     * �@@�@@�@@������iW�w�l�j�����w�l�F�@@�����P��Row.�iW�w�l�j�����w�l <BR>
     * �@@�@@�@@�����㒍���������F�@@�����P��Row.�����������t <BR>
     * �@@�@@�@@������iW�w�l�j���s�����F�@@�����P��Row.�iW�w�l�j���s���� <BR>
     * �@@�@@�@@�iW�w�l�j�L����ԋ敪�F�@@"�X�g�b�v�����L��"�i�Œ�j <BR>
     * <BR>
     * @@param l_eqTypeOrderUnit - (�����P��)
     * @@return WEB3EquityChangeOrderUnitEntry
     * @@throws WEB3BaseException
     */
    protected WEB3EquityChangeOrderUnitEntry createChangeOrderUnitEntry(
        EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createChangeOrderUnitEntry(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //���������l�ڍ׃I�u�W�F�N�g�𐶐����A
        //�p�����[�^.�����P�ʂ̓��e���v���p�e�B���Z�b�g����B
        //�P�j�@@�����P��.getDataSourceObject()���R�[������B
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();

        //�Q�j�@@�g�����������}�l�[�W��.is�o����܂Œ����P��()���R�[������B
        //�@@�@@�@@[is�o����܂Œ����P��()�̈���]
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnCarriedOrderUnit =
            l_orderManager.isCarriedOrderUnit(l_eqTypeOrderUnit);

        //�R�j�@@�������������l�ڍ׃I�u�W�F�N�g�𐶐�����B
        //�@@�@@�@@[�������������l�ڍ�()�̈���]
        //�@@�@@�@@�����㎷�s�����F�@@�����P��Row.�iW�w�l�j���s����
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        //�@@�@@�@@������is�o����܂Œ����F�@@is�o����܂Œ����P��()�̖߂�l
        //�@@�@@�@@������l�i�����F�@@�����P��Row.�l�i����
        //�@@�@@�@@�����㔭�������F�@@�����P��Row.��������
        //�@@�@@�@@�����㔭���������Z�q�F�@@�����P��Row.�����������Z�q
        //�@@�@@�@@������t�w�l��l�F�@@�����P��Row.�t�w�l��l
        //�@@�@@�@@������iW�w�l�j�����w�l�F�@@�����P��Row.�iW�w�l�j�����w�l
        //�@@�@@�@@�����㒍���������F�@@�����P��Row.�����������t
        //�@@�@@�@@������iW�w�l�j���s�����F�@@�����P��Row.�iW�w�l�j���s����
        //�@@�@@�@@�iW�w�l�j�L����ԋ敪�F�@@"�X�g�b�v�����L��"�i�Œ�j
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            new WEB3EquityChangeOrderUnitEntry(
                l_eqtypeOrderUnitRow.getWLimitExecCondType(),
                l_eqTypeOrderUnit,
                l_blnCarriedOrderUnit,
                l_eqtypeOrderUnitRow.getPriceConditionType(),
                l_eqtypeOrderUnitRow.getOrderConditionType(),
                l_eqtypeOrderUnitRow.getOrderCondOperator(),
                l_eqtypeOrderUnitRow.getStopOrderPrice(),
                l_eqtypeOrderUnitRow.getWLimitPrice(),
                l_eqtypeOrderUnitRow.getExpirationDate(),
                l_eqtypeOrderUnitRow.getWLimitExecCondType(),
                WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE
                );

        log.exiting(STR_METHOD_NAME);
        return l_changeOrderUnitEntry;
    }

    /**
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �ؑ֒��������R�����s���B <BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����������e��"���������������e"�^�ɃL���X�g����B <BR>
     * <BR>
     * �Q�j�@@������ԊǗ�.validate������t�\()���R�[������B <BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.validate����������������()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[validate����������������()�̈���] <BR>
     * �@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * �@@�@@�@@���������������e�F�@@���������������e <BR>
     * �@@�@@�@@isSkip�x���󋵃`�F�b�N�F�@@true�i*1�j <BR>
     * <BR>
     * �@@�@@�@@�i*1�jisSkip�x���󋵃`�F�b�N�ɂ��� <BR>
     * �@@�@@�@@�@@�ؑ֏����͒x�������̐ؑ֏������s�����߁A <BR>
     * �@@�@@�@@�@@validate���������\���()�̒x���󋵃`�F�b�N���s��Ȃ��B <BR>
     * �@@�@@�@@�@@�����true��ݒ肷��B <BR>
     * <BR>
     * �S�j�@@�Q�j�Ŏ擾�������������R������.getProcessingResult().isFailedResult() == true �̏ꍇ <BR>
     * �@@�@@�@@validate����������������()�̖߂�l����G���[�����擾���A��O���X���[����B <BR>
     * <BR>
     * �T�j�@@validate����������������()�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_changeOrderSpec - (�����������e)
     * @@param l_subAccount - (�⏕����)
     * @@return EqTypeOrderValidationResult
     * @@throws WEB3BaseException
     */
    public EqTypeOrderValidationResult validate(
        ChangeOrderSpec l_changeOrderSpec,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate(ChangeOrderSpec, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_changeOrderSpec == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j
        //�ؑ֒��������R�����s���B
        //�P�j�@@�p�����[�^.�����������e��"���������������e"�^�ɃL���X�g����B
        WEB3EquityChangeOrderSpec l_equityChangeOrderSpec =
            (WEB3EquityChangeOrderSpec)l_changeOrderSpec;

        //�Q�j�@@������ԊǗ�.validate������t�\()���R�[������
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //�R�j�@@�g�����������}�l�[�W��.validate����������������()���R�[������B
        //�@@�@@�@@[validate����������������()�̈���]
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�@@���������������e�F�@@���������������e
        //�@@�@@�@@isSkip�x���󋵃`�F�b�N�F�@@true�i*1�j
        //�@@�@@�@@�i*1�jisSkip�x���󋵃`�F�b�N�ɂ���
        //�@@�@@�@@�@@�ؑ֏����͒x�������̐ؑ֏������s�����߁A
        //�@@�@@�@@�@@validate���������\���()�̒x���󋵃`�F�b�N���s��Ȃ��B
        //�@@�@@�@@�@@�����true��ݒ肷��B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderValidationResult l_orderValidationResult =
            l_orderManager.validateChangeOrder(
                l_subAccount, l_equityChangeOrderSpec, true);

        //�S�j�@@�Q�j�Ŏ擾�������������R������.getProcessingResult().isFailedResult() == true �̏ꍇ
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("validate����������������Error"
                + l_orderValidationResult.getProcessingResult().getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�T�j�@@validate����������������()�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }

    /**
     * (get�T�Z����v�Z����)<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �T�Z����v�Z���ʂ��擾����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����������e��"���������������e"�^�ɃL���X�g����B<BR>
     * <BR>
     * �Q�j�@@���������������e.create�����������e()���R�[������B<BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.calc�T�Z��n���()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[calc�T�Z��n���()�̈���]<BR>
     * �@@�@@�@@�萔���F�@@�����������e.get�萔��()<BR>
     * �@@�@@�@@�w�l�F�@@�����������e.getLimitPrice() (*1)<BR>
     * �@@�@@�@@�iW�w�l)�����w�l�F�@@�����������e.get�iW�w�l�j�����w�l()<BR>
     * �@@�@@�@@�t�w�l��l�F�@@�����������e.get�t�w�l��l()<BR>
     * �@@�@@�@@���s�����F�@@�����������e.getExecConditionType() (*1)<BR>
     * �@@�@@�@@�iW�w�l�j���s�����F�@@�����������e.get�iW�w�l�j���s����()<BR>
     * �@@�@@�@@�l�i�����F�@@�����������e.get�l�i����()<BR>
     * �@@�@@�@@���������F�@@�����������e.get��������()<BR>
     * �@@�@@�@@�m�F���擾�����F�@@0�i�Œ�j<BR>
     * �@@�@@�@@is�X�g�b�v�����L���F�@@true�i�Œ�j (*2)<BR>
     * �@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�@@�@@��������F�@@�p�����[�^.�����P��.getTradedProduct()<BR>
     * �@@�@@�@@�����F�@@�����������e.getQuantity()<BR>
     * �@@�@@�@@is�������F�@@�����������e.isSellOrder()<BR>
     * �@@�@@�@@��萔�ʁF�@@�p�����[�^.�����P��.��萔��<BR>
     * �@@�@@�@@���v�����z�F�@@�p�����[�^.�����P��.���v�����z <BR>
     * �@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�Œ�j <BR>
     * <BR>
     * �@@�@@(*1)�e���ځA������i�X�g�b�v�����j�̒l���Z�b�g����B<BR>
     * �@@�@@(*2)�X�g�b�v�����L�����̊T�Z������v�Z����B<BR>
     * <BR>
     * �S�j�@@�����������e.set�����P��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[set�����P��()�̈���]<BR>
     * �@@�@@�@@�����P���F�@@calc�T�Z��n���()�̖߂�l.get�v�Z�P��()<BR>
     * <BR>
     * �T�j�@@�����������e.set�T�Z��n���()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[set�T�Z��n���()�̈���]<BR>
     * �@@�@@�@@�T�Z���z�F�@@calc�T�Z��n���()�̖߂�l.get�T�Z��n���()<BR>
     * <BR>
     * �U�j�@@�T�Z��n����v�Z���ʃI�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_changeOrderSpec - (�����������e)
     * @@param l_eqTypeOrderUnit - (�����P��)
     * @@param l_subAccount - (�⏕����)
     * @@return WEB3EquityEstimatedPrice
     * @@throws WEB3BaseException
     */
    public WEB3EquityEstimatedPrice getEstimatedPrice(
        ChangeOrderSpec l_changeOrderSpec,
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getEstimatedPrice(ChangeOrderSpec, EqTypeOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_changeOrderSpec == null || l_eqTypeOrderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j
        //�T�Z����v�Z���ʂ��擾����B
        //�P�j�@@�p�����[�^.�����������e��"���������������e"�^�ɃL���X�g����B
        WEB3EquityChangeOrderSpec l_equityChangeOrderSpec =
            (WEB3EquityChangeOrderSpec)l_changeOrderSpec;

        //�Q�j�@@���������������e.create�����������e()���R�[������B
        WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec =
            l_equityChangeOrderSpec.createOrderSpec();

        //�R�j�@@�g�����������}�l�[�W��.calc�T�Z��n���()���R�[������B
        //�@@�@@�@@[calc�T�Z��n���()�̈���]
        //�@@�@@�@@�萔���F�@@�����������e.get�萔��()
        //�@@�@@�@@�w�l�F�@@�����������e.getLimitPrice() (*1)
        //�@@�@@�@@�iW�w�l)�����w�l�F�@@�����������e.get�iW�w�l�j�����w�l()
        //�@@�@@�@@�t�w�l��l�F�@@�����������e.get�t�w�l��l()
        //�@@�@@�@@���s�����F�@@�����������e.getExecConditionType() (*1)
        //�@@�@@�@@�iW�w�l�j���s�����F�@@�����������e.get�iW�w�l�j���s����()
        //�@@�@@�@@�l�i�����F�@@�����������e.get�l�i����()
        //�@@�@@�@@���������F�@@�����������e.get��������()
        //�@@�@@�@@�m�F���擾�����F�@@0�i�Œ�j
        //�@@�@@�@@is�X�g�b�v�����L���F�@@true�i�Œ�j (*2)
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�@@��������F�@@�p�����[�^.�����P��.getTradedProduct()
        //�@@�@@�@@�����F�@@�����������e.getQuantity()
        //�@@�@@�@@is�������F�@@�����������e.isSellOrder()
        //�@@�@@�@@��萔�ʁF�@@�p�����[�^.�����P��.��萔��
        //�@@�@@�@@���v�����z�F�@@�p�����[�^.�����P��.���v�����z
        //�@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�Œ�j
        //�@@�@@(*1)�e���ځA������i�X�g�b�v�����j�̒l���Z�b�g����B
        //�@@�@@(*2)�X�g�b�v�����L�����̊T�Z������v�Z����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3EquityTradedProduct l_tradedProduct = null;
        if (l_eqTypeOrderUnit.getTradedProduct() != null)
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_eqTypeOrderUnit.getTradedProduct();
        }

        WEB3EquityEstimatedDeliveryPrice l_equityEstimatedDeliveryPrice =
            l_orderManager.calcEstimateDeliveryAmount(
            l_equityNewCashBasedOrderSpec.getCommission(),
            l_equityNewCashBasedOrderSpec.getLimitPrice(),
            l_equityNewCashBasedOrderSpec.getWLimitPriceChange(),
            l_equityNewCashBasedOrderSpec.getStopLimitPriceBasePrice(),
            l_equityNewCashBasedOrderSpec.getExecConditionType(),
            l_equityNewCashBasedOrderSpec.getWlimitExecCondType(),
            l_equityNewCashBasedOrderSpec.getPriceConditionType(),
            l_equityNewCashBasedOrderSpec.getOrderCond(),
            "0",
            true,
            l_subAccount,
            l_tradedProduct,
            l_equityNewCashBasedOrderSpec.getQuantity(),
            l_equityNewCashBasedOrderSpec.isSellOrder(),
            l_eqTypeOrderUnit.getExecutedQuantity(),
            l_eqTypeOrderUnit.getExecutedAmount(),
            false);

        // �S�j�@@�����������e.set�����P��()���R�[������
        // �@@�@@�@@[set�����P��()�̈���]
        // �@@�@@�@@�����P���F�@@calc�T�Z��n���()�̖߂�l.get�v�Z�P��()
        l_equityNewCashBasedOrderSpec.setOrderUnitPrice(
            l_equityEstimatedDeliveryPrice.getCalcUnitPrice());

        // �T�j�@@�����������e.set�T�Z��n���()���R�[������B
        // �@@�@@�@@[set�T�Z��n���()�̈���]
        // �@@�@@�@@�T�Z���z�F�@@calc�T�Z��n���()�̖߂�l.get�T�Z��n���()
        l_equityNewCashBasedOrderSpec.setEstimateDeliveryAmount(
            l_equityEstimatedDeliveryPrice.getEstimateDeliveryAmount());

        //6�j�@@�T�Z��n����v�Z���ʃI�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_equityEstimatedDeliveryPrice;
    }

    /**
     * (validate����]��)<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �]�̓`�F�b�N�ƁA�]�͎c���X�V�������s���B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����������e��"���������������e"�^�ɃL���X�g����B<BR>
     * <BR>
     * �Q�j�@@���������������e.get�����������e()���R�[������B<BR>
     * <BR>
     * �R�j�@@�������������C���^�Z�v�^�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@[�������������C���^�Z�v�^()�̈���]<BR>
     * �@@�@@�@@�����o�H�敪�F�@@�p�����[�^.�����P��.�����o�H�敪<BR>
     * �@@�@@�@@�㗝���͎ҁF�@@���������������e.getTrader()�̖߂�l<BR>
     * <BR>
     * �S�j�@@�������������C���^�Z�v�^.set�����������e()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[set�����������e()�̈���]<BR>
     * �@@�@@�@@�����������e�F�@@�����������e<BR>
     * <BR>
     * �T�j�@@����]�̓T�[�r�X.validate����]��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[validate����]��()�̈���]<BR>
     * �@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�@@�@@�������e�C���^�Z�v�^�F�@@�������������C���^�Z�v�^<BR>
     * �@@�@@�@@�������e�F�@@���������������e<BR>
     * �@@�@@�@@������ʁF�@@�p�����[�^.�����P��.�������<BR>
     * �@@�@@�@@�]�͍X�V�t���O�F�@@true<BR>
     * <BR>
     * �U�j�@@����]�͌��ʂ̓��e�ɊY�������O�I�u�W�F�N�g���X���[���邽�߁A<BR>
     * �@@�@@�g�����������}�l�[�W��.throw�]�̓G���[�ڍ׏��()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[throw�]�̓G���[�ڍ׏��()�̈���]<BR>
     * �@@�@@�@@����]�͌��ʁF�@@validate����]��()�̖߂�l<BR>
     * �@@�@@�@@������ʁF�@@�p�����[�^.�����P��.�������<BR>
     * <BR>
     * @@param l_changeOrderSpec - (�����������e)
     * @@param l_eqTypeOrderValidationResult - (�����R������)
     * @@param l_equityEstimatedPrice - (�T�Z����v�Z����)
     * @@param l_eqTypeOrderUnit - (�����P��)
     * @@param l_subAccount - (�⏕����)
     * @@throws WEB3BaseException
     */
    public void validateTradingPower(
        ChangeOrderSpec l_changeOrderSpec,
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult,
        WEB3EquityEstimatedPrice l_equityEstimatedPrice,
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradingPower("
            + "ChangeOrderSpec,"
            + "EqTypeOrderValidationResult,"
            + "WEB3EquityEstimatedPrice,"
            + "EqTypeOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null || l_changeOrderSpec == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j
        //�]�̓`�F�b�N�ƁA�]�͎c���X�V�������s���B
        //�P�j�@@�p�����[�^.�����������e��"���������������e"�^�ɃL���X�g����B
        WEB3EquityChangeOrderSpec l_equityChangeOrderSpec =
            (WEB3EquityChangeOrderSpec)l_changeOrderSpec;

        //�Q�j�@@���������������e.get�����������e()���R�[������B
        WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec =
            l_equityChangeOrderSpec.getNewCachBasedOrderSpec();

        //�R�j�@@�������������C���^�Z�v�^�̃C���X�^���X�𐶐�����B
        //�@@�@@�@@[�������������C���^�Z�v�^()�̈���]
        //�@@�@@�@@�����o�H�敪�F�@@�p�����[�^.�����P��.�����o�H�敪
        //�@@�@@�@@�㗝���͎ҁF�@@���������������e.getTrader()�̖߂�l
        WEB3GentradeTrader l_trader = null;
        if (l_equityNewCashBasedOrderSpec.getTrader() != null)
        {
            l_trader = (WEB3GentradeTrader)l_equityNewCashBasedOrderSpec.getTrader();
        }
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        WEB3EquityOrderManagerChangeOrderEventInterceptor l_changeOrderEventInterceptor =
            new WEB3EquityOrderManagerChangeOrderEventInterceptor(
                l_eqtypeOrderUnitRow.getOrderRootDiv(), l_trader);

        //�S�j�@@�������������C���^�Z�v�^.set�����������e()���R�[������B
        //�@@�@@�@@[set�����������e()�̈���]
        //�@@�@@�@@�����������e�F�@@�����������e
        l_changeOrderEventInterceptor.setEquityOrderSpec(l_equityNewCashBasedOrderSpec);

        //�T�j�@@����]�̓T�[�r�X.validate����]��()���R�[������B
        //�@@�@@�@@[validate����]��()�̈���]
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�@@�������e�C���^�Z�v�^�F�@@�������������C���^�Z�v�^
        //�@@�@@�@@�������e�F�@@���������������e
        //�@@�@@�@@������ʁF�@@�p�����[�^.�����P��.�������
        //�@@�@@�@@�]�͍X�V�t���O�F�@@true
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);

        WEB3EquityOrderManagerChangeOrderEventInterceptor[] l_interceptor =
            {l_changeOrderEventInterceptor};

        WEB3EquityChangeOrderSpec[] l_orderSpec = {l_equityChangeOrderSpec};

        WEB3GentradeSubAccount l_gentradeSubAccount = null;
        if (l_subAccount != null)
        {
            l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        }
        WEB3TPTradingPowerResult l_tradingPowerResult =
            l_tradingPowerService.validateTradingPower(
            l_gentradeSubAccount,
            l_interceptor,
            l_orderSpec,
            l_eqTypeOrderUnit.getOrderType(),
            true);

        //�U�j�@@����]�͌��ʂ̓��e�ɊY�������O�I�u�W�F�N�g���X���[���邽�߁A
        //�@@�@@�g�����������}�l�[�W��.throw�]�̓G���[�ڍ׏��()���R�[������B
        //�@@�@@�@@[throw�]�̓G���[�ڍ׏��()�̈���]
        //�@@�@@�@@����]�͌��ʁF�@@validate����]��()�̖߂�l
        //�@@�@@�@@������ʁF�@@�p�����[�^.�����P��.�������
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        l_orderManager.throwTpErrorInfo(
            l_tradingPowerResult,
            l_eqTypeOrderUnit.getOrderType());
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit�ؑ�)<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����̐ؑւ��s���B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����������e��"���������������e"�^�ɃL���X�g����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�⏕����.getMainAccount()���R�[������B<BR>
     * <BR>
     * �R�j�@@W�w�l���������ؑ֍X�V�C���^�Z�v�^�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@[W�w�l���������ؑ֍X�V�C���^�Z�v�^()�̈���]<BR>
     * �@@�@@�@@�T�Z����v�Z���ʁF�@@�p�����[�^.�T�Z����v�Z����<BR>
     * <BR>
     * �S�j�@@�g�����������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[setThreadLocalPersistenceEventInterceptor()�̈���]<BR>
     * �@@�@@�@@arg0�F�@@W�w�l���������ؑ֍X�V�C���^�Z�v�^<BR>
     * <BR>
     * �T�j�@@�g�����������}�l�[�W��.submitChangeOrder()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[submitChangeOrder()�̈���]<BR>
     * �@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�@@�@@���������������e�F�@@���������������e<BR>
     * �@@�@@�@@����p�X���[�h�F�@@getMainAccount().getTradingPassword()�̖߂�l��decrypt�����l<BR>
     * �@@�@@�@@isSkip�����R���F�@@true<BR>
     * <BR>
     * @@param l_changeOrderSpec - (�����������e)
     * @@param l_equityEstimatedPrice - (�T�Z����v�Z����)
     * @@param l_subAccount - (�⏕����)
     * @@throws WEB3BaseException
     */
    public void submitSwitch(
        ChangeOrderSpec l_changeOrderSpec,
        WEB3EquityEstimatedPrice l_equityEstimatedPrice,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getEstimatedPrice(ChangeOrderSpec, WEB3EquityEstimatedPrice, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_changeOrderSpec == null || l_subAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j
        //�����̐ؑւ��s���B
        //�P�j�@@�p�����[�^.�����������e��"���������������e"�^�ɃL���X�g����B
        WEB3EquityChangeOrderSpec l_equityChangeOrderSpec =
            (WEB3EquityChangeOrderSpec)l_changeOrderSpec;

        //�Q�j�@@�p�����[�^.�⏕����.getMainAccount()���R�[������B
        MainAccount l_mainAccount = l_subAccount.getMainAccount();

        //�R�j�@@W�w�l���������ؑ֍X�V�C���^�Z�v�^�𐶐�����B
        //�@@�@@�@@[W�w�l���������ؑ֍X�V�C���^�Z�v�^()�̈���]
        //�@@�@@�@@�T�Z����v�Z���ʁF�@@�p�����[�^.�T�Z����v�Z����
        WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor l_updateServiceInterceptor =
            new WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor(l_equityEstimatedPrice);

        //�S�j�@@�g�����������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()���R�[������B
        //�@@�@@�@@[setThreadLocalPersistenceEventInterceptor()�̈���]
        //�@@�@@�@@arg0�F�@@W�w�l���������ؑ֍X�V�C���^�Z�v�^
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateServiceInterceptor);

        //�T�j�@@�g�����������}�l�[�W��.submitChangeOrder()���R�[������B
        //�@@�@@�@@[submitChangeOrder()�̈���]
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�@@���������������e�F�@@���������������e
        //�@@�@@�@@����p�X���[�h�F�@@getMainAccount().getTradingPassword()�̖߂�l��decrypt�����l
        //�@@�@@�@@isSkip�����R���F�@@true
        WEB3Crypt l_webCrypt = new WEB3Crypt();
        EqTypeOrderSubmissionResult l_submitRes =
            l_orderManager.submitChangeOrder(
                l_subAccount,
                l_equityChangeOrderSpec,
                l_webCrypt.decrypt(l_mainAccount.getTradingPassword()),
                true);

        if (l_submitRes.getProcessingResult().isFailedResult())
        {
            log.debug(" __Error[���������X�V]__"
                + l_submitRes.getProcessingResult().getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_submitRes.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
