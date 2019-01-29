head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitSwitchServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : W�w�l�����ؑփT�[�r�XImpl(WEB3ToWLimitSwitchServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 ������(���u) �V�K�쐬
                   2006/11/20 ���G�� (���u) ���f�� No.176, 192
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEqTypeSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquitySwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitIfoSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitMarginCloseMarginSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitMarginOpenMarginSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitSwitchService;
import webbroker3.util.WEB3LogUtility;

/**
 * (W�w�l�����ؑփT�[�r�XImpl)<BR>
 * W�w�l�����ؑփT�[�r�X�����N���X<BR>
 *
 * @@author ������
 * @@version 1.0
 */
public class WEB3ToWLimitSwitchServiceImpl implements WEB3ToWLimitSwitchService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitSwitchServiceImpl.class);

    /**
     * @@roseuid 44E9077B0271
     */
    public WEB3ToWLimitSwitchServiceImpl()
    {

    }

    /**
     * (executeW�w�l�����ؑ�)<BR>
     * W�w�l�����ؑ֏������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iW�w�l�����ؑփT�[�r�X�jexecuteW�w�l�����ؑցv�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B<BR>
     * �i�֑ؑΏۂ̒����̒���ID�j<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923CF803D2
     */
    public void executeWLimitSwitch(
        SubAccount l_subAccount,
        long l_lngOrderId,
        ProductTypeEnum l_productType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "executeWLimitSwitch(SubAccount l_subAccount, long l_lngOrderId, ProductTypeEnum l_productType)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_productType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1 �i����t���[�F�@@�����̖����^�C�v=="����"�̏ꍇ�j
        //1.1.1 execute����W�w�l�����ؑ�
        //[����]
        // �⏕�����F�@@�p�����[�^.�⏕����
        // ����ID�F�@@�p�����[�^.����ID 
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            this.executeEquityWLimitSwitch(l_subAccount, l_lngOrderId);
        }

        //1.2 �i����t���[�F�@@�����̖����^�C�v=="�敨�I�v�V����"�̏ꍇ�j
        //1.2.1  execute�敨OPW�w�l�����ؑ�
        //[����]
        // �⏕�����F�@@�p�����[�^.�⏕����
        // ����ID�F�@@�p�����[�^.����ID
        else if (ProductTypeEnum.IFO.equals(l_productType))
        {
            this.executeIfoWLimitSwitch(l_subAccount, l_lngOrderId);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (execute�敨OPW�w�l�����ؑ�)<BR>
     * �敨OPW�w�l�����ؑ֏������s���B<BR>
     * <BR>
     * �P�j�@@�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@OP�����}�l�[�W��.getOrderUnits()���R�[������B<BR>
     * <BR>
     * �@@[getOrderUnits()�Ɏw�肷�����] <BR>
     *�@@ arg0�F�@@�p�����[�^.����ID <BR>
     * <BR>
     * �@@�����P�ʂ��擾�ł��Ȃ������ꍇ�A<BR>
     * �@@log.error()�ɂāA�����P�ʂ��擾�ł��Ȃ�����<BR>
     * �@@���b�Z�[�W�ƁA����ID�A�⏕����ID�A����ID�A�����^�C�v��<BR>
     * �@@�o�͂��A�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B<BR>
     * �@@class: WEB3SystemLayerException<BR>
     * �@@tag:   SYSTEM_ERROR_80005<BR>
     * <BR>
     * �Q�j�@@W�w�l�����敨OP�ؑֈꌏ�T�[�r�XImpl���擾����B<BR>
     * <BR>
     * �R�j�@@�擾���������P�ʂ̒����J�e�S���ɂ��A<BR>
     * �@@�ꌏ�T�[�r�X�̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * �@@�p�����[�^.�����P��.�����J�e�S�����A<BR>
     * �@@["OP�V�K������"�̏ꍇ]<BR>
     * �@@�@@�Q�j�ɂĎ擾�����T�[�r�X.submitOP�V�K��W�w�l����()��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@["OP�ԍϒ���"�̏ꍇ]<BR>
     * �@@�@@�Q�j�ɂĎ擾�����T�[�r�X.submitOP�ԍ�W�w�l����()��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@["�敨�V�K������"�̏ꍇ]<BR>
     * �@@�@@�Q�j�ɂĎ擾�����T�[�r�X.submit�敨�V�K��W�w�l����()��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@["�敨�ԍϒ���"�̏ꍇ]<BR>
     * �@@�@@�Q�j�ɂĎ擾�����T�[�r�X.submit�敨�ԍ�W�w�l����()��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@����Lsubmit���\�b�h�̈����́A�S�ĂP�j�̃��\�b�h�̖߂�l��<BR>
     * �@@0�Ԗڂ̗v�f���Z�b�g���邱�ƁB<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_orderId - (����ID)<BR>
     * ����ID�B<BR>
     * �i�֑ؑΏۂ̒����̒���ID�j<BR>
     * @@throws WEB3BaseException
     */
    protected void executeIfoWLimitSwitch(
        SubAccount l_subAccount,
        long l_orderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "executeIfoWLimitSwitch(SubAccount, long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����P�ʃI�u�W�F�N�g���擾����B
        //�@@OP�����}�l�[�W��.getOrderUnits()���R�[������B
        //
        //�@@[getOrderUnits()�Ɏw�肷�����]
        //�@@�@@arg0�F�@@�p�����[�^.����ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_orderId);

        //�@@�����P�ʂ��擾�ł��Ȃ������ꍇ�A
        //�@@log.error()�ɂāA�����P�ʂ��擾�ł��Ȃ�����
        //�@@���b�Z�[�W�ƁA����ID�A�⏕����ID�A����ID�A�����^�C�v��
        //�@@�o�͂��A�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            log.error("error in WEB3OptionOrderManagerImpl.getOrderUnits()"
                + " ����ID:[" + l_subAccount.getAccountId()
                + "] �⏕����ID:[" + l_subAccount.getSubAccountId()
                + "] ����ID:[" + l_orderId
                + "] �����^�C�v:[" + ProductTypeEnum.IFO + "]");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Y���f�[�^�Ȃ�");
        }

        //�Q�j�@@W�w�l�����敨OP�ؑֈꌏ�T�[�r�XImpl���擾����B
        WEB3ToWLimitIfoSwitchUnitService l_toWLimitIfoSwitchUnitService =
            (WEB3ToWLimitIfoSwitchUnitService)Services.getService(WEB3ToWLimitIfoSwitchUnitService.class);

        //�R�j�@@�擾���������P�ʂ̒����J�e�S���ɂ��A
        //�@@�ꌏ�T�[�r�X�̃��\�b�h���Ăѕ�����B
        //�@@�����́A�S�ĂP�j�̃��\�b�h�̖߂�l�� 0�Ԗڂ̗v�f���Z�b�g���邱�ƁB
        //�@@�p�����[�^.�����P��.�����J�e�S�����A
        //�@@["OP�V�K������"�̏ꍇ]
        //�@@�@@�Q�j�ɂĎ擾�����T�[�r�X.submitOP�V�K��W�w�l����()��
        //�@@�@@�R�[������B
        OrderCategEnum l_orderCategEnum = l_orderUnits[0].getOrderCateg();
        if (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderCategEnum))
        {
            l_toWLimitIfoSwitchUnitService.submitOptionOpenContractWLimitOrder((IfoOrderUnit)l_orderUnits[0]);
        }

        //�@@["OP�ԍϒ���"�̏ꍇ]
        //�@@�@@�Q�j�ɂĎ擾�����T�[�r�X.submitOP�ԍ�W�w�l����()��
        //�@@�@@�R�[������B
        else if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderCategEnum))
        {
            l_toWLimitIfoSwitchUnitService.submitOptionSettleContractWLimitOrder((IfoOrderUnit)l_orderUnits[0]);
        }

        //�@@["�敨�V�K������"�̏ꍇ]
        //�@@�@@�Q�j�ɂĎ擾�����T�[�r�X.submit�敨�V�K��W�w�l����()��
        //�@@�@@�R�[������B
        else if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderCategEnum))
        {
            l_toWLimitIfoSwitchUnitService.submitFuturesOpenContractWLimitOrder((IfoOrderUnit)l_orderUnits[0]);
        }

        //�@@["�敨�ԍϒ���"�̏ꍇ]
        //�@@�@@�Q�j�ɂĎ擾�����T�[�r�X.submit�敨�ԍ�W�w�l����()��
        //�@@�@@�R�[������B
        else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderCategEnum))
        {
            l_toWLimitIfoSwitchUnitService.submitFuturesSettleContractWLimitOrder((IfoOrderUnit)l_orderUnits[0]);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (execute����W�w�l�����ؑ�)<BR>
     * ����W�w�l�����ؑ֏������s���B<BR> 
     * <BR>
     * �P�j�@@this.get���������f�[�^()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[get���������f�[�^()�̈���] <BR>
     * �@@�@@�@@����ID�F�@@�p�����[�^.����ID <BR>
     * �@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * <BR>
     * �Q�j�@@this.get�����ؑֈꌏ�T�[�r�X()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[get�����ؑֈꌏ�T�[�r�X()�̈���] <BR>
     * �@@�@@�@@�����P�ʁF�@@get���������f�[�^()�̖߂�l <BR>
     * <BR>
     * �R�j�@@get�����ؑֈꌏ�T�[�r�X()�̖߂�l.submit()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[submit()�̈���] <BR>
     * �@@�@@�@@�����P�ʁF�@@get���������f�[�^()�̖߂�l<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_orderId - (����ID)<BR>
     * ����ID�B<BR>
     * �i�֑ؑΏۂ̒����̒���ID�j<BR>
     * @@throws WEB3BaseException
     */
    protected void executeEquityWLimitSwitch(
        SubAccount l_subAccount,
        long l_orderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "executeEquityWLimitSwitch(SubAccount, long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@this.get���������f�[�^()���R�[������B
        //�@@�@@�@@[get���������f�[�^()�̈���]
        //�@@�@@�@@����ID�F�@@�p�����[�^.����ID
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        EqTypeOrderUnit l_eqTypeOrderUnit =
            this.getEqTypeOrderData(l_orderId, l_subAccount);

        //�Q�j�@@this.get�����ؑֈꌏ�T�[�r�X()���R�[������B
        //�@@�@@�@@[get�����ؑֈꌏ�T�[�r�X()�̈���]
        //�@@�@@�@@�����P�ʁF�@@get���������f�[�^()�̖߂�l
        WEB3ToWLimitEqTypeSwitchUnitService l_eqTypeSwitchUnitService =
            this.getEqTypeSwitchUnitService(l_eqTypeOrderUnit);

        //�R�j�@@get�����ؑֈꌏ�T�[�r�X()�̖߂�l.submit()���R�[������B
        //�@@�@@�@@[submit()�̈���]
        //�@@�@@�@@�����P�ʁF�@@get���������f�[�^()�̖߂�l
        l_eqTypeSwitchUnitService.submit(l_eqTypeOrderUnit);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�����ؑֈꌏ�T�[�r�X)<BR>
     * �p�����[�^.�����P�ʂɊY������ؑֈꌏ�T�[�r�X��Ԃ��B<BR>
     * <BR>
     * �p�����[�^.�����P��.�����J�e�S�����A<BR>
     * �@@�m "��������"�̏ꍇ �n<BR>
     * �@@�@@�@@�@@W�w�l�������������ؑֈꌏ�T�[�r�X��Ԃ��B<BR>
     * <BR>
     * �@@�m "�V�K������"�̏ꍇ �n<BR>
     * �@@�@@�@@�@@W�w�l�����M�p����V�K���ؑֈꌏ�T�[�r�X��Ԃ��B<BR>
     * <BR>
     * �@@�m "�ԍϒ���"�̏ꍇ �n<BR>
     * �@@�@@�@@�@@W�w�l�����M�p����ԍϐؑֈꌏ�T�[�r�X��Ԃ��B<BR>
     * <BR>
     * @@param l_eqTypeOrderUnit - (�����P��)
     * @@return WEB3ToWLimitEqTypeSwitchUnitService
     * @@throws WEB3BaseException
     */
    public WEB3ToWLimitEqTypeSwitchUnitService getEqTypeSwitchUnitService(
        EqTypeOrderUnit l_eqTypeOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEqTypeSwitchUnitService(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        OrderCategEnum l_orderCategEnum = l_eqTypeOrderUnit.getOrderCateg();
        //�p�����[�^.�����P�ʂɊY������ؑֈꌏ�T�[�r�X��Ԃ��B
        //
        //�p�����[�^.�����P��.�����J�e�S�����A
        //�@@�m "��������"�̏ꍇ �n
        //�@@�@@�@@�@@W�w�l�������������ؑֈꌏ�T�[�r�X��Ԃ��B
        if (OrderCategEnum.ASSET.equals(l_orderCategEnum))
        {
            WEB3ToWLimitEquitySwitchUnitService l_equitySwitchUnitService =
                (WEB3ToWLimitEquitySwitchUnitService)Services.getService(
                    WEB3ToWLimitEquitySwitchUnitService.class);
            log.exiting(STR_METHOD_NAME);
            return l_equitySwitchUnitService;
        }
        //�@@�m "�V�K������"�̏ꍇ �n
        //�@@�@@�@@�@@W�w�l�����M�p����V�K���ؑֈꌏ�T�[�r�X��Ԃ��B
        else if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCategEnum))
        {
            WEB3ToWLimitMarginOpenMarginSwitchUnitService l_marginOpenMarginSwitchUnitService =
                (WEB3ToWLimitMarginOpenMarginSwitchUnitService)Services.getService(
                    WEB3ToWLimitMarginOpenMarginSwitchUnitService.class);
            log.exiting(STR_METHOD_NAME);
            return l_marginOpenMarginSwitchUnitService;
        }
        //�@@�m "�ԍϒ���"�̏ꍇ �n
        //�@@�@@�@@�@@W�w�l�����M�p����ԍϐؑֈꌏ�T�[�r�X��Ԃ��B
        else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCategEnum))
        {
            WEB3ToWLimitMarginCloseMarginSwitchUnitService l_marginCloseMarginSwitchUnitService =
                (WEB3ToWLimitMarginCloseMarginSwitchUnitService)Services.getService(
                    WEB3ToWLimitMarginCloseMarginSwitchUnitService.class);
            log.exiting(STR_METHOD_NAME);
            return l_marginCloseMarginSwitchUnitService;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get���������f�[�^)<BR>
     * �p�����[�^.����ID�ɊY�����钍���P�ʂ��擾����B<BR>
     * <BR>
     * �P�j�@@�g�����������}�l�[�W��.getOrderUnits()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[getOrderUnits()�Ɏw�肷�����]<BR>
     * �@@�@@�@@arg0�F�@@�p�����[�^.����ID  <BR>
     * <BR>
     * �Q�j�@@�����P�ʂ��擾�ł��Ȃ������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@log.error()�ɂāA�����P�ʂ��擾�ł��Ȃ��������b�Z�[�W�ƁA<BR>
     * �@@�@@�@@�@@�@@����ID�A�⏕����ID�A����ID�A�����^�C�v���o�͂��A <BR>
     * �@@�@@�@@�@@�@@�u�Y���f�[�^�Ȃ��v�̋Ɩ��G���[���X���[����B  <BR>
     * <BR>
     * �@@�@@�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�P�j�Ŏ擾���������P�ʂ�0�Ԗڂ̗v�f��Ԃ��B<BR>
     * <BR>
     * @@param l_eqTypeOrderId - (����ID)
     * @@param l_subAccount - (�⏕����)
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     */
    public EqTypeOrderUnit getEqTypeOrderData(
        long l_eqTypeOrderId,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEqTypeOrderData(long, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�p�����[�^.����ID�ɊY�����钍���P�ʂ��擾����B
        //�P�j�@@�g�����������}�l�[�W��.getOrderUnits()���R�[������B
        //�@@�@@�@@[getOrderUnits()�Ɏw�肷�����]
        //�@@�@@�@@arg0�F�@@�p�����[�^.����ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_eqTypeOrderId);

        //�Q�j�@@�����P�ʂ��擾�ł��Ȃ������ꍇ
        //�@@�@@�@@�@@�@@log.error()�ɂāA�����P�ʂ��擾�ł��Ȃ��������b�Z�[�W��
        //�@@�@@�@@�@@�@@����ID�A�⏕����ID�A����ID�A�����^�C�v���o�͂�
        //�@@�@@�@@�@@�@@�u�Y���f�[�^�Ȃ��v�̋Ɩ��G���[���X���[����B
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            log.error("error in WEB3EquityOrderManager.getOrderUnits()"
                + " ����ID:[" + l_subAccount.getAccountId()
                + "] �⏕����ID:[" + l_subAccount.getSubAccountId()
                + "] ����ID:[" + l_eqTypeOrderId
                + "] �����^�C�v:[" + ProductTypeEnum.EQUITY + "]");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Y���f�[�^�Ȃ�");
        }

        //�@@�@@�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�P�j�Ŏ擾���������P�ʂ�0�Ԗڂ̗v�f��Ԃ��B
        EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        log.exiting(STR_METHOD_NAME);
        return l_eqTypeOrderUnit;
    }
}
@
