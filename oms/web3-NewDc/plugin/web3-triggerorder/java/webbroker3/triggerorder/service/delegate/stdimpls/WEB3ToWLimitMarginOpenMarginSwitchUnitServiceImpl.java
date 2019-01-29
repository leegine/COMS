head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitMarginOpenMarginSwitchUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : W�w�l�����M�p����V�K���ؑֈꌏ�T�[�r�XImpl(WEB3ToWLimitMarginOpenMarginSwitchUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/20 ���r (���u) �V�K�쐬 ���f�� No.176 No.195
Revesion History : 2006/12/11 ���G�� (���u)�i���f���jNo.207 No.210
Revesion History : 2007/01/16 ���G�� (���u)�i���f���jNo.220
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderValidationResult;
import webbroker3.equity.WEB3MarginChangeOpenMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginChangeOrderSpec;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitMarginOpenMarginSwitchUnitService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;

/**
 * (W�w�l�����M�p����V�K���ؑֈꌏ�T�[�r�XImpl)
 *
 * @@author ���r(���u)
 * @@version 1.0
 */
public class WEB3ToWLimitMarginOpenMarginSwitchUnitServiceImpl
    extends WEB3ToWLimitEqTypeSwitchUnitServiceImpl
    implements WEB3ToWLimitMarginOpenMarginSwitchUnitService
{

   /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3ToWLimitMarginOpenMarginSwitchUnitServiceImpl.class);

    /**
     * (create�����������e)<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �M�p�V�K�������������e���쐬����B<BR>
     *<BR>
     *�P�j�@@�p�����[�^.�����P��.getDataSourceObject()���R�[������B<BR>
     *<BR>
     *�Q�j�@@�����P��.�����ID��null�̏ꍇ�A <BR>
     *�@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrader()���R�[������B<BR>
     *<BR>
     *�@@�@@�@@[getTrader()�̈���] <BR>
     *�@@�@@�@@�����ID�F�@@�p�����[�^.�����P��.�����ID <BR>
     *<BR>
     *�R�j�@@�g�����������}�l�[�W��.is�o����܂Œ����P��()���R�[������B <BR>
     *<BR>
     *�@@�@@�@@[is�o����܂Œ����P��()�̈���]<BR>
     *�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     *<BR>
     *�S�j�@@�M�p�V�K�������������e�I�u�W�F�N�g�𐶐�����B <BR>
     *<BR>
     *�@@�@@�@@[create�V�K�������������e()�̈���] <BR>
     *�@@�@@�@@����ID�F�@@�p�����[�^.�����P��.����ID <BR>
     *�@@�@@�@@�����P��ID�F�@@�p�����[�^.�����P��.�����P��ID<BR>
     *�@@�@@�@@�����㒍�������F�@@�p�����[�^.�����P��.�������� <BR>
     *�@@�@@�@@������w�l�F�@@�����P��Row.�iW�w�l�j�����w�l <BR>
     *�@@�@@�@@�����㎷�s�����F�@@�����P��Row.�iW�w�l�j���s���� <BR>
     *�@@�@@�@@�����㒍���������F�@@�����P��Row.�����������t <BR>
     *�@@�@@�@@������l�i�����F�@@�����P��Row.�l�i���� <BR>
     *�@@�@@�@@���������F�@@�����P��Row.��������<BR>
     *�@@�@@�@@�����㔭���������Z�q�F�@@�����P��Row.�����������Z�q<BR>
     *�@@�@@�@@������t�w�l��l�F�@@�����P��Row.�t�w�l��l <BR>
     *�@@�@@�@@������iW�w�l�j�����w�l�F�@@�����P��Row.�iW�w�l�j�����w�l <BR>
     *�@@�@@�@@������iW�w�l�j���s�����F�@@�����P��Row.�iW�w�l�j���s���� <BR>
     *�@@�@@�@@������is�o����܂Œ����F�@@is�o����܂Œ���()�̖߂�l <BR>
     *�@@�@@�@@�㗝���͎ҁF�@@�����P��.�����ID��null�̏ꍇ�AgetTrader()�̖߂�l <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��L�ȊO�̏ꍇ�Anull���Z�b�g�B <BR>
     *�@@�@@�@@�iW�w�l�j�L����ԋ敪�F�@@"�X�g�b�v�����L��"�i�Œ�j<BR>
     *<BR>
     *�T�j�@@�V�K�������������e��ԋp����B <BR>
     * <BR>
     * @@param l_eqTypeOrderUnit - (���������P��)
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

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�M�p�V�K�������������e���쐬����B
        //�P�j�@@�p�����[�^.�����P��.getDataSourceObject()���R�[������B
        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();

        //�Q�j�@@�����P��.�����ID��null�̏ꍇ�A
        //�@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrader()���R�[������B
        //�@@�@@�@@[getTrader()�̈���]
        //�@@�@@�@@�����ID�F�@@�p�����[�^.�����P��.�����ID
        Trader l_trader = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        try
        {
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
        //�R�j�@@�g�����������}�l�[�W��.is�o����܂Œ����P��()���R�[������B
        //�@@�@@�@@[is�o����܂Œ����P��()�̈���]
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();

        boolean l_blnIsCarryOrder = l_orderMgr.isCarriedOrderUnit(l_eqTypeOrderUnit);

        //�S�j�@@�M�p�V�K�������������e�I�u�W�F�N�g�𐶐�����B
        //�@@�@@�@@[create�V�K�������������e()�̈���]
        //�@@�@@�@@����ID�F�@@�p�����[�^.�����P��.����ID
        //�@@�@@�@@�����P��ID�F�@@�p�����[�^.�����P��.�����P��ID
        //�@@�@@�@@�����㒍�������F�@@�p�����[�^.�����P��.��������
        //�@@�@@�@@������w�l�F�@@�����P��Row.�iW�w�l�j�����w�l
        //�@@�@@�@@�����㎷�s�����F�@@�����P��Row.�iW�w�l�j���s����
        //�@@�@@�@@�����㒍���������F�@@�����P��Row.�����������t
        //�@@�@@�@@������l�i�����F�@@�����P��Row.�l�i����
        //�@@�@@�@@���������F�@@�����P��Row.��������
        //�@@�@@�@@�����㔭���������Z�q�F�@@�����P��Row.�����������Z�q
        //�@@�@@�@@������t�w�l��l�F�@@�����P��Row.�t�w�l��l
        //�@@�@@�@@������iW�w�l�j�����w�l�F�@@�����P��Row.�iW�w�l�j�����w�l
        //�@@�@@�@@������iW�w�l�j���s�����F�@@�����P��Row.�iW�w�l�j���s����
        //�@@�@@�@@������is�o����܂Œ����F�@@is�o����܂Œ���()�̖߂�l
        //�@@�@@�@@�㗝���͎ҁF�@@�����P��.�����ID��null�̏ꍇ�AgetTrader()�̖߂�l
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��L�ȊO�̏ꍇ�Anull���Z�b�g�B
        //�@@�@@�@@�iW�w�l�j�L����ԋ敪�F�@@"�X�g�b�v�����L��"�i�Œ�j

        WEB3MarginChangeOrderSpec l_marginChangeOrderSpec =
            WEB3MarginChangeOrderSpec.createOpenMarginChangeOrderSpec(
                l_eqTypeOrderUnit.getOrderId(),
                l_eqTypeOrderUnit.getOrderUnitId(),
                l_eqTypeOrderUnit.getQuantity(),
                l_eqTypeOrderUnitRow.getWLimitPrice(),
                l_eqTypeOrderUnitRow.getWLimitExecCondType(),
                l_eqTypeOrderUnitRow.getExpirationDate(),
                l_eqTypeOrderUnitRow.getPriceConditionType(),
                l_eqTypeOrderUnitRow.getOrderConditionType(),
                l_eqTypeOrderUnitRow.getOrderCondOperator(),
                l_eqTypeOrderUnitRow.getStopOrderPrice(),
                l_eqTypeOrderUnitRow.getWLimitPrice(),
                l_eqTypeOrderUnitRow.getWLimitExecCondType(),
                l_blnIsCarryOrder,
                l_trader,
                WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE);

        log.exiting(STR_METHOD_NAME);
        //�T�j�@@�V�K�������������e��ԋp����B
        return l_marginChangeOrderSpec;
    }

    /**
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �ؑ֒��������R�����s���B<BR>
     *<BR>
     *�P�j�@@�p�����[�^.�����������e��"�V�K�������������e"�^�ɃL���X�g����B  <BR>
     *<BR>
     *�Q�j�@@�g�����������}�l�[�W��.validate����������������()���R�[������B  <BR>
     *<BR>
     *�@@�@@�@@[validate����������������()�̈���]  <BR>
     *�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����  <BR>
     *�@@�@@�@@���������������e�F�@@�V�K�������������e <BR>
     *�@@�@@�@@isSkip�x���󋵃`�F�b�N�F�@@true�i*1�j  <BR>
     *<BR>
     *�@@�@@�@@�i*1�jisSkip�x���󋵃`�F�b�N�ɂ���  <BR>
     *�@@�@@�@@�@@�ؑ֏����͒x�������̐ؑ֏������s�����߁A  <BR>
     *�@@�@@�@@�@@validate���������\���()�̒x���󋵃`�F�b�N���s��Ȃ��B  <BR>
     *�@@�@@�@@�@@�����true��ݒ肷��B  <BR>
     *<BR>
     *�R�j�@@�Q�j�Ŏ擾�������������R������.getProcessingResult().isFailedResult() == true �̏ꍇ <BR>
     *�@@�@@�@@���������R�����ʂ���G���[�����擾���A��O���X���[����B <BR>
     *<BR>
     *�S�j�@@validate����������������()�̖߂�l��ԋp����B<BR>
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
        //�P�j�@@�p�����[�^.�����������e��"�V�K�������������e"�^�ɃL���X�g����B
        WEB3MarginChangeOrderSpec l_marginChangeOrderSpec =
            (WEB3MarginChangeOrderSpec)l_changeOrderSpec;

        //�Q�j�@@�g�����������}�l�[�W��.validate����������������()���R�[������B
        //�@@�@@�@@[validate����������������()�̈���]
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�@@���������������e�F�@@�V�K�������������e
        //�@@�@@�@@isSkip�x���󋵃`�F�b�N�F�@@true�i*1�j
        //�@@�@@�@@�i*1�jisSkip�x���󋵃`�F�b�N�ɂ���
        //�@@�@@�@@�@@�ؑ֏����͒x�������̐ؑ֏������s�����߁A
        //�@@�@@�@@�@@validate���������\���()�̒x���󋵃`�F�b�N���s��Ȃ��B
        //�@@�@@�@@�@@�����true��ݒ肷��B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();

        EqTypeOrderValidationResult l_validationResult =
            l_orderMgr.validateChangeOrder(
                l_subAccount,
                l_marginChangeOrderSpec,
                true);

        //�R�j�@@�Q�j�Ŏ擾�������������R������.getProcessingResult().isFailedResult() == true �̏ꍇ
        //�@@�@@�@@���������R�����ʂ���G���[�����擾���A��O���X���[����B
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�S�j�@@validate����������������()�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_validationResult;
    }

    /**
     * (get�T�Z����v�Z����)<BR>
     *�i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     *<BR>
     *�T�Z����v�Z���ʂ��擾����B <BR>
     *<BR>
     *�P�j�@@�p�����[�^.�����������e��"�M�p�V�K�������������e"�^�ɃL���X�g����B <BR>
     *<BR>
     *�Q�j�@@�M�p�V�K�������������e.get�V�K�������������e�ڍ�()���R�[������B <BR>
     *<BR>
     *�R�j�@@�����v�Z�T�[�r�X.create�萔��()���R�[������B <BR>
     *<BR>
     *�@@�@@�@@[create�萔��()�̈���] <BR>
     *�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     *<BR>
     *�S�j�@@�g�����������}�l�[�W��.is������()���R�[������B <BR>
     *<BR>
     *�@@�@@�@@[is������()�̈���] <BR>
     *�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     *<BR>
     *�T�j�@@�g�����������}�l�[�W��.calc�����������()���R�[������B <BR>
     *<BR>
     *�@@�@@�@@[calc�����������()�̈���] <BR>
     *�@@�@@�@@�萔���F�@@create�萔��() <BR>
     *�@@�@@�@@�w�l�F�@@�V�K�������������e�ڍ�.getAfterChangePrice() (*1) <BR>
     *�@@�@@�@@�iW�w�l�j�����w�l�F�@@�M�p�V�K�������������e.get������iW�w�l�j�����w�l() <BR>
     *�@@�@@�@@�t�w�l��l�F�@@�M�p�V�K�������������e.get������t�w�l��l() <BR>
     *�@@�@@�@@���s�����F�@@�M�p�V�K�������������e.get�����㎷�s����() (*1) <BR>
     *�@@�@@�@@�iW�w�l�j���s�����F�@@�M�p�V�K�������������e.get������iW�w�l�j���s����() <BR>
     *�@@�@@�@@�l�i�����F�@@�M�p�V�K�������������e.get������l�i����() <BR>
     *�@@�@@�@@���������F�@@�M�p�V�K�������������e.get�����㔭������() <BR>
     *�@@�@@�@@�m�F���擾�����F�@@0�i�Œ�j <BR>
     *�@@�@@�@@is�X�g�b�v�����L���F�@@true�i�Œ�j (*2) <BR>
     *�@@�@@�@@is�����F�@@is������()�̖߂�l <BR>
     *�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     *�@@�@@�@@��������F�@@�p�����[�^.�����P��.getTradedProduct() <BR>
     *�@@�@@�@@�����F�@@�M�p�V�K���������e.get�����㒍������() <BR>
     *�@@�@@�@@��萔�ʁF�@@�p�����[�^.�����P��.��萔�� <BR>
     *�@@�@@�@@���v�����z�F�@@�p�����[�^.�����P��.���v�����z  <BR>
     *�@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�Œ�j <BR>
     *<BR>
     *�@@�@@(*1)�e���ځA������i�X�g�b�v�����j�̒l���Z�b�g����B <BR>
     *�@@�@@(*2)�X�g�b�v�����L�����̊T�Z������v�Z����B <BR>
     *<BR>
     *�U�j�@@�M�p�V�K�������������e.set������v�Z�P��()���R�[������B<BR>
     *<BR>
     *�@@�@@�@@[set������v�Z�P��()�̈���]<BR>
     *�@@�@@�@@������v�Z�P���F�@@calc�����������()�̖߂�l.get�v�Z�P��()<BR>
     *<BR>
     *�V�j�@@�M�p�V�K�������������e.set�����㌚���()���R�[������B<BR>
     *<BR>
     *�@@�@@�@@[set�����㌚���()�̈���]<BR>
     *�@@�@@�@@������F�@@calc�����������()�̖߂�l.get�T�Z�����()<BR>
     *�W�j�@@�T�Z������v�Z���ʃI�u�W�F�N�g��ԋp����B<BR>
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
        
        if (l_eqTypeOrderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�@@�p�����[�^.�����������e��"�M�p�V�K�������������e"�^�ɃL���X�g����B
        WEB3MarginChangeOrderSpec l_marginChangeOrderSpec =
            (WEB3MarginChangeOrderSpec)l_changeOrderSpec;

        //�Q�j�@@�M�p�V�K�������������e.get�V�K�������������e�ڍ�()���R�[������B
        EqTypeChangeOrderUnitEntry l_changeOrderUnitEntry =
            l_marginChangeOrderSpec.getChangeOrderUnitEntry();

        //�R�j�@@�����v�Z�T�[�r�X.create�萔��()���R�[������B
        //
        //�@@�@@�@@[create�萔��()�̈���]
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_eqBizLogicProvider =
           (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();

        WEB3GentradeCommission l_commission =
            l_eqBizLogicProvider.createCommission((EqTypeOrderUnitImpl)l_eqTypeOrderUnit);

        //�S�j�@@�g�����������}�l�[�W��.is������()���R�[������B
        //�@@�@@�@@[is������()�̈���]
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        boolean l_blnIsSellOrder = l_orderMgr.isSellOrder(l_eqTypeOrderUnit);

        //�T�j�@@�g�����������}�l�[�W��.calc�����������()���R�[������B
        //
        //�@@�@@�@@[calc�����������()�̈���]
        //�@@�@@�@@�萔���F�@@create�萔��()
        //�@@�@@�@@�w�l�F�@@�V�K�������������e�ڍ�.getAfterChangePrice() (*1)
        //�@@�@@�@@�iW�w�l�j�����w�l�F�@@�M�p�V�K�������������e.get������iW�w�l�j�����w�l()
        //�@@�@@�@@�t�w�l��l�F�@@�M�p�V�K�������������e.get������t�w�l��l()
        //�@@�@@�@@���s�����F�@@�M�p�V�K�������������e.get�����㎷�s����() (*1)
        //�@@�@@�@@�iW�w�l�j���s�����F�@@�M�p�V�K�������������e.get������iW�w�l�j���s����()
        //�@@�@@�@@�l�i�����F�@@�M�p�V�K�������������e.get������l�i����()
        //�@@�@@�@@���������F�@@�M�p�V�K�������������e.get�����㔭������()
        //�@@�@@�@@�m�F���擾�����F�@@0�i�Œ�j
        //�@@�@@�@@is�X�g�b�v�����L���F�@@true�i�Œ�j (*2)
        //�@@�@@�@@is�����F�@@is������()�̖߂�l
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�@@��������F�@@�p�����[�^.�����P��.getTradedProduct()
        //�@@�@@�@@�����F�@@�M�p�V�K���������e.get�����㒍������()
        //�@@�@@�@@��萔�ʁF�@@�p�����[�^.�����P��.��萔��
        //�@@�@@�@@���v�����z�F�@@�p�����[�^.�����P��.���v�����z
        //�@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�Œ�j
        //
        //�@@�@@(*1)�e���ځA������i�X�g�b�v�����j�̒l���Z�b�g����B
        //�@@�@@(*2)�X�g�b�v�����L�����̊T�Z������v�Z����B

        EqTypeTradedProduct l_eqTypeTradedProduct = null;
        if (l_eqTypeOrderUnit.getTradedProduct() != null)
        {
            l_eqTypeTradedProduct = (EqTypeTradedProduct)l_eqTypeOrderUnit.getTradedProduct();
        }
        WEB3EquityEstimatedContractPrice l_estimatedContractPrice =
            l_orderMgr.calcContractAmountAtOrder(
                l_commission,
                l_changeOrderUnitEntry.getAfterChangePrice(),
                l_marginChangeOrderSpec.getModifiedWLimitPrice(),
                l_marginChangeOrderSpec.getModifiedStopOrderPrice(),
                l_marginChangeOrderSpec.getModifiedExecutionType(),
                l_marginChangeOrderSpec.getModifiedWlimitExecCondType(),
                l_marginChangeOrderSpec.getModifiedPriceConditionType(),
                l_marginChangeOrderSpec.getModifiedOrderConditionType(),
                "0",
                true,
                l_blnIsSellOrder,
                l_subAccount,
                l_eqTypeTradedProduct,
                l_marginChangeOrderSpec.getModifiedOrderQuantity(),
                l_eqTypeOrderUnit.getExecutedQuantity(),
                l_eqTypeOrderUnit.getExecutedAmount(),
                false);

        // �U�j�@@�M�p�V�K�������������e.set������v�Z�P��()���R�[������
        // �@@�@@�@@[set������v�Z�P��()�̈���]
        // �@@�@@�@@������v�Z�P���F�@@calc�����������()�̖߂�l.get�v�Z�P��()
        l_marginChangeOrderSpec.setModifiedCalcUnitPrice(
            l_estimatedContractPrice.getCalcUnitPrice());

        // �V�j�@@�M�p�V�K�������������e.set�����㌚���()���R�[������B
        // �@@�@@�@@[set�����㌚���()�̈���]
        // �@@�@@�@@������F�@@calc�����������()�̖߂�l.get�T�Z�����()
        l_marginChangeOrderSpec.setModifiedContractAmount(
            l_estimatedContractPrice.getEstimatedContractPrice());
        
        log.exiting(STR_METHOD_NAME);
        //8�j�@@�T�Z������v�Z���ʃI�u�W�F�N�g��ԋp����B
        return l_estimatedContractPrice;


    }

    /**
     * (validate����]��)<BR>
     *�]�̓`�F�b�N�ƁA�]�͎c���X�V�������s���B  <BR>
     *<BR>
     *�P�j�@@�p�����[�^.�����������e��"�M�p�V�K�������������e"�^�ɃL���X�g����B <BR>
     *<BR>
     *�Q�j�@@�M�p�V�K�������X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B <BR>
     *<BR>
     *�@@�@@�@@[�M�p�V�K�������X�V�C���^�Z�v�^()�̈���] <BR>
     *�@@�@@�@@�M�p�V�K�������������e�F�@@�M�p�V�K�������������e <BR>
     *�@@�@@�@@�󔄂�K���Ώۃt���O�F�@@�p�����[�^.�����R������.is�󔄂�K���Ώۃt���O()�̖߂�l <BR>
     *�@@�@@�@@�����o�H�敪�F�@@�p�����[�^.�����P��.�����o�H�敪 <BR>
     *�@@�@@�@@�㗝���͎ҁF�@@�M�p�V�K�������������e.get����()�̖߂�l <BR>
     *<BR>
     *�R�j�@@����]�̓T�[�r�X.validate����]��()���R�[������B <BR>
     *<BR>
     *�@@�@@�@@[validate����]��()�̈���] <BR>
     *�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     *�@@�@@�@@�������e�C���^�Z�v�^�F�@@���������M�p�V�K�������X�V�C���^�Z�v�^ <BR>
     *�@@�@@�@@�������e�F�@@�M�p�V�K�������������e <BR>
     *�@@�@@�@@������ʁF�@@�p�����[�^.�����P��.������� <BR>
     *�@@�@@�@@�]�͍X�V�t���O�F�@@true <BR>
     *<BR>
     *�S�j�@@����]�͌��ʂ̓��e�ɊY�������O�N���X���X���[���邽�߁A <BR>
     *�@@�@@�g�����������}�l�[�W��.throw�]�̓G���[�ڍ׏��()���R�[������B <BR>
     *<BR>
     *�@@�@@�@@[throw�]�̓G���[�ڍ׏��()�̈���] <BR>
     *�@@�@@�@@����]�͌��ʁF�@@validate����]��()�̖߂�l <BR>
     *�@@�@@�@@������ʁF�@@�p�����[�^.�����P��.������� <BR>
     * @@param l_changeOrderSpec - (�����������e)
     * @@param l_eqTypeOrderValidationResult - (�����R������)
     * @@param l_equityEstimatedPrice - (�T�Z����v�Z����)
     * @@param l_eqTypeOrderUnit - (�����P��)
     * @@param l_subAccount - (�⏕����)
     */
    public void validateTradingPower(ChangeOrderSpec l_changeOrderSpec,
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult,
        WEB3EquityEstimatedPrice l_equityEstimatedPrice,
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradingPower("
            + "EqTypeOrderValidationResult,"
            + "WEB3EquityEstimatedPrice,"
            + "EqTypeOrderUnit,"
            + "SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null || l_changeOrderSpec == null
            || l_eqTypeOrderValidationResult ==null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@�p�����[�^.�����������e��"�M�p�V�K�������������e"�^�ɃL���X�g����B
        WEB3MarginChangeOrderSpec l_marginChangeOrderSpec =
            (WEB3MarginChangeOrderSpec)l_changeOrderSpec;
        //�Q�j�@@�M�p�V�K�������X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B
        //
        //�@@�@@�@@[�M�p�V�K�������X�V�C���^�Z�v�^()�̈���]
        //�@@�@@�@@�M�p�V�K�������������e�F�@@�M�p�V�K�������������e
        //�@@�@@�@@�󔄂�K���Ώۃt���O�F�@@�p�����[�^.�����R������.is�󔄂�K���Ώۃt���O()�̖߂�l
        //�@@�@@�@@�����o�H�敪�F�@@�p�����[�^.�����P��.�����o�H�敪
        //�@@�@@�@@�㗝���͎ҁF�@@�M�p�V�K�������������e.get����()�̖߂�l
        boolean l_blnIsShortSellRegulationTarget =
            ((WEB3EquityOrderValidationResult)l_eqTypeOrderValidationResult).getShortSellingRestraint();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strOrderRootDiv = l_orderUnitRow.getOrderRootDiv();

        WEB3MarginChangeOpenMarginUpdateInterceptor l_intercepter =
            new WEB3MarginChangeOpenMarginUpdateInterceptor(
                l_marginChangeOrderSpec,
                l_blnIsShortSellRegulationTarget,
                l_strOrderRootDiv,
                (WEB3GentradeTrader)l_marginChangeOrderSpec.getTrader());

        //�R�j�@@����]�̓T�[�r�X.validate����]��()���R�[������B
        //
        //�@@�@@�@@[validate����]��()�̈���]
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�@@�������e�C���^�Z�v�^�F�@@���������M�p�V�K�������X�V�C���^�Z�v�^
        //�@@�@@�@@�������e�F�@@�M�p�V�K�������������e
        //�@@�@@�@@������ʁF�@@�p�����[�^.�����P��.�������
        //�@@�@@�@@�]�͍X�V�t���O�F�@@true
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);

        WEB3MarginChangeOpenMarginUpdateInterceptor[] l_interceptors =
            {l_intercepter};

        WEB3MarginChangeOrderSpec[] l_orderSpecs = {l_marginChangeOrderSpec};

        WEB3GentradeSubAccount l_gentradeSubAccount = null;
        if (l_subAccount != null)
        {
            l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        }
        WEB3TPTradingPowerResult l_tradingPowerResult =
            l_tradingPowerService.validateTradingPower(
            l_gentradeSubAccount,
            l_interceptors,
            l_orderSpecs,
            l_eqTypeOrderUnit.getOrderType(),
            true);

        //�S�j�@@����]�͌��ʂ̓��e�ɊY�������O�N���X���X���[���邽�߁A
        //�@@�@@�g�����������}�l�[�W��.throw�]�̓G���[�ڍ׏��()���R�[������B
        //
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
     *<BR>
     *�����̐ؑւ��s���B <BR>
     *<BR>
     *�P�j�@@�p�����[�^.�����������e��"�M�p�V�K�������������e"�^�ɃL���X�g����B <BR>
     *<BR>
     *�Q�j�@@�p�����[�^.�⏕����.getMainAccount()���R�[������B <BR>
     *<BR>
     *�R�j�@@W�w�l���������ؑ֍X�V�C���^�Z�v�^()���R�[������B <BR>
     *<BR>
     *�@@�@@�@@[W�w�l���������ؑ֍X�V�C���^�Z�v�^()�̈���] <BR>
     *�@@�@@�@@�T�Z����v�Z���ʁF�@@�p�����[�^.�T�Z����v�Z���� <BR>
     *<BR>
     *�S�j�@@�g�����������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()���R�[������B <BR>
     *<BR>
     *�@@�@@�@@[setThreadLocalPersistenceEventInterceptor()�̈���] <BR>
     *�@@�@@�@@arg0�F�@@W�w�l���������ؑ֍X�V�C���^�Z�v�^ <BR>
     *<BR>
     *�T�j�@@�g�����������}�l�[�W��.submitChangeOrder()���R�[������B <BR>
     *<BR>
     *�@@�@@�@@[submitChangeOrder()�̈���] <BR>
     *�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     *�@@�@@�@@���������������e�F�@@�M�p�V�K�������������e <BR>
     *�@@�@@�@@����p�X���[�h�F�@@getMainAccount().getTradingPassword()�̖߂�l��decrypt�����l  <BR>
     *�@@�@@�@@isSkip�����R���F�@@true<BR>
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
        final String STR_METHOD_NAME = "submitSwitch(ChangeOrderSpec, " +
            "WEB3EquityEstimatedPrice, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P�j�@@�p�����[�^.�����������e��"�M�p�V�K�������������e"�^�ɃL���X�g����B
        WEB3MarginChangeOrderSpec l_marginChangeOrderSpec =
            (WEB3MarginChangeOrderSpec)l_changeOrderSpec;

        //�Q�j�@@�p�����[�^.�⏕����.getMainAccount()���R�[������B
        MainAccount l_mainAccount  = l_subAccount.getMainAccount();

        //�R�j�@@W�w�l���������ؑ֍X�V�C���^�Z�v�^()���R�[������B
        //
        //�@@�@@�@@[W�w�l���������ؑ֍X�V�C���^�Z�v�^()�̈���]
        //�@@�@@�@@�T�Z����v�Z���ʁF�@@�p�����[�^.�T�Z����v�Z����
        WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor l_interceptor =
            new WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor(l_equityEstimatedPrice);

        //�S�j�@@�g�����������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()���R�[������B
        //
        //�@@�@@�@@[setThreadLocalPersistenceEventInterceptor()�̈���]
        //�@@�@@�@@arg0�F�@@W�w�l���������ؑ֍X�V�C���^�Z�v�^
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        //�T�j�@@�g�����������}�l�[�W��.submitChangeOrder()���R�[������B
        //
        //�@@�@@�@@[submitChangeOrder()�̈���]
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�@@���������������e�F�@@�M�p�V�K�������������e
        //�@@�@@�@@����p�X���[�h�F�@@getMainAccount().getTradingPassword()�̖߂�l��decrypt�����l
        //�@@�@@�@@isSkip�����R���F�@@true

        WEB3Crypt l_crypt = new WEB3Crypt();
        OrderSubmissionResult l_orderSubmissionResult =
            l_orderManager.submitChangeOrder(
	            l_subAccount,
	            l_marginChangeOrderSpec,
	            l_crypt.decrypt(l_mainAccount.getTradingPassword()),
	            true);

        if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
