head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEqTypeSwitchUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : W�w�l���������ؑֈꌏ�T�[�r�XImpl(WEB3ToWLimitEqTypeSwitchUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/17 ���G�� (���u) �V�K�쐬 �i���f���jNo.176 �iDB�X�V�d�l�jNo.025
Revesion History : 2006/11/30 ���G�� (���u)�i���f���jNo.195 �iDB�X�V�d�l�jNo.032�@@No.033
Revesion History : 2006/12/11 ���G�� (���u)�i���f���jNo.207
Revesion History : 2007/01/29 �đo�g (���u)�i���f���jNo.227
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEqTypeSwitchUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (W�w�l���������ؑֈꌏ�T�[�r�XImpl)<BR>
 * ���ۃN���X�iabstract�j<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public abstract class WEB3ToWLimitEqTypeSwitchUnitServiceImpl
    implements WEB3ToWLimitEqTypeSwitchUnitService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3ToWLimitEqTypeSwitchUnitServiceImpl.class);

    /**
     * W�w�l�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �w�iW�w�l���������ؑֈꌏ�T�[�r�X�jsubmit�x<BR>
     * �Q�ƁB<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)
     * @@throws WEB3BaseException
     */
    public void submit(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submit(EqTypeOrderUnit l_eqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // getOrderUnit
        //�����P�ʂ��Ď擾����B
        //�����͈ȉ��̒ʂ�ɃZ�b�g����B
        //�����P��ID�F�@@�p�����[�^.�����P��.�����P��ID
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        try
        {
            l_eqTypeOrderUnit =
                (EqTypeOrderUnit)l_equityOrderManager.getOrderUnit(
                    l_eqTypeOrderUnit.getOrderUnitId());

        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();

        //is�����Ώ�(EqTypeOrderUnit)
        //�w�肳�ꂽ������W�w�l�ؑւ̑Ώۂł��邩�𔻒肷��
        //�����͈ȉ��̒ʂ�ɃZ�b�g����B
        //�����P�ʁF�@@�����P��
        boolean l_blnIsProcessObject = this.isProcessObject(l_eqTypeOrderUnit);

        //�i����t���[�F�@@�����ΏۊO�iis�����Ώ� == false�j�̏ꍇ�j
        if (!l_blnIsProcessObject)
        {
            //�����ΏۊO�̏ꍇ�A
            //�����������̂܂�return����B
            //�i����X�e�[�^�X�ŏI���j
            log.debug("�����ΏۊO�iis�����Ώ� == false�j�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //validate�ؑ֏����\(EqTypeOrderUnit)
        //W�w�l�ؑ֏������\�Ȓ�����Ԃ����肷��B
        //�����͈ȉ��̒ʂ�ɃZ�b�g����B
        //�����P�ʁF�@@�����P��
        //validate�ؑ֏����\(EqTypeOrderUnit)
        //��ʂɗ�O���X���[����
        this.validateSwitchPossible(l_eqTypeOrderUnit);
        boolean l_blnFailed = false;
        ChangeOrderSpec l_changeOrderSpec = null;
        WEB3EquityEstimatedPrice l_equityEstimatedPrice = null;

        try
        {
            // get�⏕����(����ID : long, �⏕����ID : long)
            //�⏕�������擾����B
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //����ID�F�@@�����P��.getAccountId()
            //�⏕����ID�F�@@�����P��.getSubAccountId()
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            l_subAccount = l_accountManager.getSubAccount(
                l_eqTypeOrderUnit.getAccountId(),
                l_eqTypeOrderUnit.getSubAccountId());

            //get������
            //�����`�F�b�N���s���B
            //�i�����o�^���̔������ƁA���ݓ������狁�߂����������قȂ�ꍇ�͔����G���[�Ƃ���j
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //�m�F���������F�@@�����P��.������
            Date l_datBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate);

            //create�����������e(EqTypeOrderUnit, �⏕����)
            //�����������e���쐬����B
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //�����P�ʁF�@@�����P��
            //�⏕�����F�@@get�⏕����()�̖߂�l
            l_changeOrderSpec =
                this.createChangeOrderSpec(l_eqTypeOrderUnit, l_subAccount);

            //get�T�Z����v�Z����(ChangeOrderSpec, EqTypeOrderUnit, �⏕����)
            //���������������e���Z�b�g����B
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //�����������e�F�@@create�����������e()�̖߂�l
            //�����P�ʁF�@@�����P��
            //�⏕�����F�@@get�⏕����()�̖߂�l
            l_equityEstimatedPrice =
                this.getEstimatedPrice(l_changeOrderSpec, l_eqTypeOrderUnit, l_subAccount);

            //validate(ChangeOrderSpec, �⏕����)
            //���������������e���Z�b�g����B
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //�����������e�F�@@create�����������e()�̖߂�l
            //�⏕�����F�@@get�⏕����()�̖߂�l
            EqTypeOrderValidationResult l_validationResult =
                this.validate(l_changeOrderSpec, l_subAccount);

            //validate����]��(ChangeOrderSpec, EqTypeOrderValidationResult,
            //�T�Z����v�Z����, EqTypeOrderUnit, �⏕����)
            //�]�̓`�F�b�N�y�сA�]�͎c�����X�V����B
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //�����������e�F�@@create�����������e()�̖߂�l
            //�����R�����ʁF�@@validate()�̖߂�l
            //�T�Z����v�Z���ʁF�@@get�T�Z����v�Z����()�̖߂�l
            //�����P�ʁF�@@�����P��
            //�⏕�����F�@@get�⏕����()�̖߂�l
            this.validateTradingPower(
                l_changeOrderSpec,
                l_validationResult,
                l_equityEstimatedPrice,
                l_eqTypeOrderUnit,
                l_subAccount);

        }
        catch (Exception l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            l_blnFailed = true;
        }
        if (!l_blnFailed)
        {
            //�i���s���ʂɉ����Ē����f�[�^��UPDATE����j
            //����I�������ꍇ
            //submit�ؑ�(ChangeOrderSpec, �T�Z����v�Z����, �⏕����)
            //����I�������ꍇ�A�����̐ؑւ��s���B
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //�����������e�F�@@create�����������e()�̖߂�l
            //�T�Z����v�Z���ʁF�@@get�T�Z����v�Z����()�̖߂�l
            //�⏕�����F�@@get�⏕����()�̖߂�l
            log.debug("����I�������ꍇ�A�����̐ؑւ��s���B");
            this.submitSwitch(l_changeOrderSpec, l_equityEstimatedPrice, l_subAccount);
        }
        else
        {
            //��O���X���[���ꂽ�ꍇ�A
            //submit�ؑ֎��s()���R�[������O�ɁA
            //�G���[���O���o�͂���B
            //submit�ؑ֎��s(EqTypeOrderUnit, �⏕����, ChangeOrderSpec)
            //�������ɗ�O���X���[���ꂽ�ꍇ�A�����̐ؑւ��s���B
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //�����P�ʁF�@@�����P��
            //�⏕�����F�@@get�⏕����()�̖߂�l
            //�����������e�F�@@create�����������e()�̖߂�l
            log.debug("�������ɗ�O���X���[���ꂽ�ꍇ�A�����̐ؑւ��s���B");
            this.submitSwitchFail(l_eqTypeOrderUnit, l_subAccount, l_changeOrderSpec);
        }

        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (is�����Ώ�)<BR>
     * �w��̒�����W�w�l�ؑւ̏����Ώۂł��邩�𔻒肷��B<BR>
     * �����Ώۂ̏ꍇ�Atrue���A�����ΏۊO�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �ȉ��̏����A�S�ĂɊY������ꍇ <BR>
     * �����ΏۂƂ�true��ԋp����B <BR>
     * �ȊO�Afalse��ԋp����B <BR>
     * <BR>
     * �@@�E�p�����[�^.�����P��.�����L����� == "�I�[�v��"<BR>
     * �@@�E�p�����[�^.�����P��.�������� == "W�w�l" <BR>
     * �@@�E�ؑ֏������ρi�g�����������}�l�[�W��.is����������(�����P��) == true�j<BR>
     * @@param l_orderUnit - (���������P��)
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isProcessObject(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isProcessObject(EqTypeOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�w��̒�����W�w�l�ؑւ̏����Ώۂł��邩�𔻒肷��B
        //�����Ώۂ̏ꍇ�Atrue���A�����ΏۊO�̏ꍇ�Afalse��ԋp����B
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderingCondition = l_eqtypeOrderUnitRow.getOrderConditionType();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsNotOrderedOrder = l_orderManager.isNotOrderedOrder(l_orderUnit);

        //�ȉ��̏����A�S�ĂɊY������ꍇ
        //�����ΏۂƂ�true��ԋp����B
        //�ȊO�Afalse��ԋp����B
        //
        //�@@�E�p�����[�^.�����P��.�����L����� == "�I�[�v��"
        //�@@�E�p�����[�^.�����P��.�������� == "W�w�l"
        //�@@�E�ؑ֏������ρi�g�����������}�l�[�W��.is����������(�����P��) == true�j
        if (OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus())
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderingCondition)
            && l_blnIsNotOrderedOrder)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (validate�ؑ֏����\)<BR>
     * �ؑ֏������{��҂K�v�̂��钍���̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �P�j�@@������ԃ`�F�b�N<BR>
     * �@@�������s�ꖢ���M�i*�j�̏ꍇ�A<BR>
     * �@@�܂��́A�p�����[�^.�����P��.������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ<BR>
     * �@@�u��t���^�������^������̒����͐ؑ֏����s�v�� <BR>
     * �@@��O���X���[����B  <BR>
     * <BR>
     * �@@�@@�E"��t�ρi�ύX�����j" <BR>
     * �@@�@@�E"�������i�ύX�����j" <BR>
     * �@@�@@�E"��t�ρi��������j" <BR>
     * �@@�@@�E"�������i��������j" <BR>
     * <BR>
     * �@@�i*�j�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�NaN�̏ꍇ�A<BR>
     * �@@�@@�@@�s�ꖢ���M�̒����Ɣ��肷��B<BR>
     * @@param l_orderUnit - (���������P��)
     * @@throws WEB3BaseException
     */
    public void validateSwitchPossible(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSwitchPossible(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("���������P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�ؑ֏������{��҂K�v�̂��钍���̏ꍇ�A��O���X���[����B
        //�P�j�@@������ԃ`�F�b�N
        //�@@�������s�ꖢ���M�i*�j�̏ꍇ
        //�@@�܂��́A�p�����[�^.�����P��.������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ
        //�@@�u��t���^�������^������̒����͐ؑ֏����s�v��
        //�@@��O���X���[����B
        //�@@�@@�E"��t�ρi�ύX�����j"
        //�@@�@@�E"�������i�ύX�����j"
        //�@@�@@�E"��t�ρi��������j"
        //�@@�@@�E"�������i��������j"
        //�@@�i*�j�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�NaN�̏ꍇ�A
        //�@@�@@�@@�s�ꖢ���M�̒����Ɣ��肷��B
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if (Double.isNaN(l_orderUnit.getConfirmedQuantity())
            || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.MODIFYING.equals(l_orderStatus)
            || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.CANCELLING.equals(l_orderStatus))
        {
            log.debug("�������s�ꖢ���M�܂��́A��t���^�������^������̒����͐ؑ֏����s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02521,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�����������e)<BR>
     * ���ۃ��\�b�h�iabstract�j<BR>
     * <BR>
     * @@param l_eqTypeOrderUnit - (���������P��)
     * @@param l_subAccount - (�⏕����)
     * @@return ChangeOrderSpec
     * @@throws WEB3BaseException
     */
    public abstract ChangeOrderSpec createChangeOrderSpec(
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException;

    /**
     * ���ۃ��\�b�h�iabstract�j<BR>
     * <BR>
     * @@param l_changeOrderSpec - (�����������e)
     * @@param l_subAccount - (�⏕����)
     * @@return EqTypeOrderValidationResult
     * @@throws WEB3BaseException
     */
    public abstract EqTypeOrderValidationResult validate(
        ChangeOrderSpec l_changeOrderSpec,
        SubAccount l_subAccount) throws WEB3BaseException;

    /**
     * (get�T�Z����v�Z����)<BR>
     * ���ۃ��\�b�h�iabstract�j<BR>
     * <BR>
     * @@param l_changeOrderSpec - (�����������e)
     * @@param l_eqTypeOrderUnit - (���������P��)
     * @@param l_subAccount - (�⏕����)
     * @@return WEB3EquityEstimatedPrice
     * @@throws WEB3BaseException
     */
    public abstract WEB3EquityEstimatedPrice getEstimatedPrice(
        ChangeOrderSpec l_changeOrderSpec,
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException;

    /**
     * (validate����]��)<BR>
     * ���ۃ��\�b�h�iabstract�j<BR>
     * <BR>
     * @@param l_changeOrderSpec - (�����������e)
     * @@param l_eqTypeOrderValidationResult - (�����R������)
     * @@param l_equityEstimatedPrice - (�T�Z����v�Z����)
     * @@param l_eqTypeOrderUnit - (���������P��)
     * @@param l_subAccount - (�⏕����)
     * @@throws WEB3BaseException
     */
    public abstract void validateTradingPower(
        ChangeOrderSpec l_changeOrderSpec,
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult,
        WEB3EquityEstimatedPrice l_equityEstimatedPrice,
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException;

    /**
     * (submit�ؑ�)<BR>
     * ���ۃ��\�b�h�iabstract�j<BR>
     * <BR>
     * @@param l_changeOrderSpec - (�����������e)
     * @@param l_equityEstimatedPrice - (�T�Z����v�Z����)
     * @@param l_subAccount - (�⏕����)
     * @@throws WEB3BaseException
     */
    public abstract void submitSwitch(
        ChangeOrderSpec l_changeOrderSpec,
        WEB3EquityEstimatedPrice l_equityEstimatedPrice,
        SubAccount l_subAccount) throws WEB3BaseException;

    /**
     * (submit�ؑ֎��s)<BR>
     * �������ɗ�O���X���[���ꂽ�ꍇ�A�ؑ֎��s�̓��e�Œ������X�V����B <BR>
     * <BR>
     * �P�j�@@�g�����������}�l�[�W��.get�X�g�b�v�����������T�Z����v�Z����()���R�[������B <BR>
     * <BR>
     * �@@�@@���T�Z����̍Čv�Z���ʂ��擾����B <BR>
     * �@@�@@���i�X�g�b�v�����̒����P���ŗ]�͂��S������Ă���\��������ׁA  <BR>
     * �@@�@@���@@���~�b�g�����̒����P���ōČv�Z���s���B�j  <BR>
     * <BR>
     * �@@�@@�@@[get�X�g�b�v�����������T�Z����v�Z����()���R�[������B�̈���] <BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * �@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�����P�ʂ�clone���쐬����B  <BR>
     * <BR>
     * �R�j�@@ �Q�j�ɂč쐬����clone�ɑ΂��ADB�X�V�d�l�ɂ����čX�V�l���Z�b�g����B <BR>
     * <BR>
     * �@@�@@���X�V�l�̐ݒ�d�l <BR>
     * �@@�@@�@@DB�X�V�d�l  <BR>
     * �@@�@@�@@�uW�w�l�����ؑցiNG�j_�����P�ʃe�[�u���d�l�v�Q�ƁB  <BR>
     * <BR>
     * �S�j�@@�g�����������}�l�[�W��.create��������()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[create��������()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�����P�ʁF�@@�R�j�ō쐬���������P�� <BR>
     * �@@�@@�@@�C�x���g�^�C�v�F�@@�}�[�P�b�g���M���� <BR>
     * <BR>
     * �@@�@@���X�V�l�̐ݒ�d�l�i�Q�l�j <BR>
     * �@@�@@�@@DB�X�V�d�l  <BR>
     * �@@�@@�@@�uW�w�l�����ؑցiNG�j_���������e�[�u���d�l�v�Q�ƁB  <BR>
     * <BR>
     * �T�j�@@�g�����������}�l�[�W��.update�����f�[�^()���R�[������B  <BR>
     * <BR>
     * �@@�@@�@@[update�����f�[�^()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�����P�ʁF�@@�R�j�ō쐬���������P�� <BR>
     * �@@�@@�@@���������F�@@create��������()�̖߂�l <BR>
     * <BR>
     * �U�j�@@�p�����[�^.�����P��.������ʂ�"����������"�̏ꍇ�A�܂��́A <BR>
     * �@@�@@�@@�p�����[�^.�����P��.�����J�e�S����"�V�K������"�̏ꍇ�A <BR>
     * �@@�@@�@@����]�̓T�[�r�X.�]�͍Čv�Z()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[�]�͍Čv�Z()�̈���] <BR>
     * �@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * <BR>
     * @@param l_eqTypeOrderUnit - (���������P��)
     * @@param l_subAccount - (�⏕����)
     * @@param l_changeOrderSpec - (�����������e)
     * @@throws WEB3BaseException
     */
    public void submitSwitchFail(
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount,
        ChangeOrderSpec l_changeOrderSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitSwitchFail(EqTypeOrderUnit, SubAccount, ChangeOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("���������P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�@@�g�����������}�l�[�W��.get�X�g�b�v�����������T�Z����v�Z����()���R�[������B
        //�@@�@@���T�Z����̍Čv�Z���ʂ��擾����B
        //�@@�@@���i�X�g�b�v�����̒����P���ŗ]�͂��S������Ă���\���������
        //�@@�@@���@@���~�b�g�����̒����P���ōČv�Z���s���B�j
        //�@@�@@�@@[get�X�g�b�v�����������T�Z����v�Z����()���R�[������B�̈���]
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        WEB3EquityEstimatedPrice l_estimatedPrice =
            l_orderManager.getStopOrderExpireEstimatedPrice(l_eqTypeOrderUnit, l_subAccount);

        //�Q�j�@@�p�����[�^.�����P�ʂ�clone���쐬����B
        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
            new EqtypeOrderUnitParams(l_eqTypeOrderUnitRow);

        //�R�j�@@ �Q�j�ɂč쐬����clone�ɑ΂��ADB�X�V�d�l�ɂ����čX�V�l���Z�b�g����B
        //�@@�@@���X�V�l�̐ݒ�d�l
        //�@@�@@�@@DB�X�V�d�l
        //�@@�@@�@@�uW�w�l�����ؑցiNG�j_�����P�ʃe�[�u���d�l�v�Q�ƁB
        // ���������ŏI�ʔ� = �i�����l�j + 1
        l_eqTypeOrderUnitParams.setLastOrderActionSerialNo(
            l_eqTypeOrderUnitParams.getLastOrderActionSerialNo() + 1);

        // �������� =  0�FDEFAULT
        l_eqTypeOrderUnitParams.setOrderConditionType(
            WEB3OrderingConditionDef.DEFAULT);

        //�������� = null
        l_eqTypeOrderUnitParams.setOrderCondOperator(null);

        //�t�w�l��l = null
        l_eqTypeOrderUnitParams.setStopOrderPrice(null);

        //�iW�w�l�j�����w�l = null
        l_eqTypeOrderUnitParams.setWLimitPrice(null);

        //������� = 11�F�������s�i�ύX�����j
        l_eqTypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);

        //�����P�� = "�C���^�Z�v�^.�T�Z����v�Z����.get�v�Z�P��()
        //���C���^�Z�v�^.�T�Z����v�Z���ʂ�nul���̏ꍇ�A�i�����l�j"
        WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor l_updateServiceInterceptor =
            new WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor(l_estimatedPrice);

        WEB3EquityEstimatedPrice l_equityEstimatedPrice =
            l_updateServiceInterceptor.l_equityEstimatedPrice;

        if (l_equityEstimatedPrice != null)
        {
            l_eqTypeOrderUnitParams.setPrice(
                l_equityEstimatedPrice.getCalcUnitPrice());
        }

        //this.�w�l�@@�����~�b�g�����̒����P���B
        if (l_eqTypeOrderUnitParams.getLimitPriceIsNull())
        {
            //�s�ꂩ��m�F�ς݂̒����P��
            l_eqTypeOrderUnitParams.setConfirmedOrderPrice(null);
        }
        else
        {
            //�s�ꂩ��m�F�ς݂̒����P��
            l_eqTypeOrderUnitParams.setConfirmedOrderPrice(
                l_eqTypeOrderUnitParams.getLimitPrice());
        }

        //�T�Z��n��� = �C���^�Z�v�^.�T�Z����v�Z����.get�T�Z��n���()
        //���C���^�Z�v�^.�T�Z����v�Z���ʂ�nul���̏ꍇ�A�i�����l�j
        if (l_equityEstimatedPrice != null)
        {
            l_eqTypeOrderUnitParams.setEstimatedPrice(
                l_equityEstimatedPrice.getEstimateDeliveryAmount());
        }

        //���������E����敪 = D:W�w�l�����ؑ֎��s
        l_eqTypeOrderUnitParams.setModifyCancelType(
            WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR);

        //�s�ꂩ��m�F�ς݂̊T�Z��n��� = �C���^�Z�v�^.�T�Z����v�Z����.get�T�Z��n���()
        //���C���^�Z�v�^.�T�Z����v�Z���ʂ�nul���̏ꍇ�A�i�����l�j
        if (l_equityEstimatedPrice != null)
        {
            l_eqTypeOrderUnitParams.setConfirmedEstimatedPrice(
                l_equityEstimatedPrice.getEstimateDeliveryAmount());
        }

        //���N�G�X�g�^�C�v = 5�F����
        l_eqTypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);

        //�X�V���t = ���ݎ���
        l_eqTypeOrderUnitParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());

        //���������� = �X�V�O�̔�������
        l_eqTypeOrderUnitParams.setOrgOrderConditionType(
            l_eqTypeOrderUnitRow.getOrderConditionType());

        //�������������Z�q = �X�V�O�̔����������Z�q
        l_eqTypeOrderUnitParams.setOrgOrderCondOperator(
            l_eqTypeOrderUnitRow.getOrderCondOperator());

        //���t�w�l��l = �X�V�O�̋t�w�l��l
        if (l_eqTypeOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_eqTypeOrderUnitParams.setOrgStopOrderPrice(null);
        }
        else
        {
            l_eqTypeOrderUnitParams.setOrgStopOrderPrice(
                l_eqTypeOrderUnitRow.getStopOrderPrice());
        }

        //���iW�w�l�j�����w�l = �X�V�O�́iW�w�l�j�����w�l
        if (l_eqTypeOrderUnitRow.getWLimitPriceIsNull())
        {
            l_eqTypeOrderUnitParams.setOrgWLimitPrice(null);
        }
        else
        {
            l_eqTypeOrderUnitParams.setOrgWLimitPrice(
                l_eqTypeOrderUnitRow.getWLimitPrice());
        }

        //���iW�w�l�j���s���� = �X�V�O�́iW�w�l�j���s����
        l_eqTypeOrderUnitParams.setOrgWLimitExecCondType(
            l_eqTypeOrderUnitRow.getWLimitExecCondType());

        //�iW�w�l�j���s���� = null
        l_eqTypeOrderUnitParams.setWLimitExecCondType(null);

        //�S�j�@@�g�����������}�l�[�W��.create��������()���R�[������B
        //�@@�@@�@@[create��������()�Ɏw�肷�����]
        //�@@�@@�@@�����P�ʁF�@@�R�j�ō쐬���������P��
        //�@@�@@�@@�C�x���g�^�C�v�F�@@�}�[�P�b�g���M����
        //�@@�@@���X�V�l�̐ݒ�d�l�i�Q�l�j
        //�@@�@@�@@DB�X�V�d�l
        //�@@�@@�@@�uW�w�l�����ؑցiNG�j_���������e�[�u���d�l�v�Q�ƁB
        EqTypeOrderUnit l_orderUnitClone =
            (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_eqTypeOrderUnitParams);
        EqTypeOrderAction l_typeOrderAction =
            (EqTypeOrderAction)l_orderManager.createOrderAction(
                l_orderUnitClone, OrderEventTypeEnum.MARKER_REFUSAL);

        //�T�j�@@�g�����������}�l�[�W��.update�����f�[�^()���R�[������B
        //�@@�@@�@@[update�����f�[�^()�Ɏw�肷�����]
        //�@@�@@�@@�����P�ʁF�@@�R�j�ō쐬���������P��
        //�@@�@@�@@���������F�@@create��������()�̖߂�l
        l_orderManager.updateOrderData(l_orderUnitClone, l_typeOrderAction);

        //�U�j�@@�p�����[�^.�����P��.������ʂ�"����������"�̏ꍇ�A�܂��́A
        //�@@�@@�@@�p�����[�^.�����P��.�����J�e�S����"�V�K������"�̏ꍇ�A
        //�@@�@@�@@����]�̓T�[�r�X.�]�͍Čv�Z()���R�[������B
        //�@@�@@�@@[�]�͍Čv�Z()�̈���]
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����

        if (OrderTypeEnum.EQUITY_BUY.equals(l_eqTypeOrderUnit.getOrderType())
            || OrderCategEnum.OPEN_MARGIN.equals(l_eqTypeOrderUnit.getOrderCateg()))
        {
            WEB3GentradeSubAccount l_gentradeSubAccount = null;
            if (l_subAccount != null)
            {
                l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
            }

            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
                l_tpTradingPowerService.reCalcTradingPower(l_gentradeSubAccount);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
