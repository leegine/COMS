head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3OptionOrderCarryOverServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�����J�z�T�[�r�XImpl(WEB3OptionOrderCarryOverServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/17 ���� (���u) �V�K�쐬
Revesion History : 2006/10/19 ������(���u)�@@���f��No.568
Revesion History : 2007/03/26 ꎉ�  (���u)�@@���f��No.632
Revesion History : 2007/06/21 ����  (���u)  ���f��670
Revesion History : 2007/07/12 ��іQ(���u) ���f��No.775
Revesion History : 2008/04/11 ��іQ (���u) ���f��No.277,278
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.service.delegate.WEB3OptionOrderCarryOverService;
import webbroker3.triggerorder.service.delegate.WEB3OptionOrderCarryOverUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP�����J�z�T�[�r�XImpl)<BR>
 * OP�����J�z�T�[�r�X�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V�����̎w������Ȃ��B<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3OptionOrderCarryOverServiceImpl extends WEB3IfoOrderCarryOverMainServiceImpl
    implements WEB3OptionOrderCarryOverService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionOrderCarryOverServiceImpl.class);

    /**
     * @@roseuid 40C0AFC40222
     */
    public WEB3OptionOrderCarryOverServiceImpl()
    {

    }

    /**
     * OP�����J�z�T�[�r�X���������{����B<BR>
     * <BR>
     * �X�[�p�[�N���X�̓����\�b�h�ɏ������Ϗ�����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     *
     * @@return webbroker3.ifo.message.WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 409B126A0188
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        return super.execute(l_request);
    }

    /**
     * (create��������)<BR>
     * �����������쐬����B<BR>
     * <BR>
     * �X�[�p�[�N���X�̓����\�b�h�ɏ������Ϗ�����B<BR>
     * @@param l_mainAccount - (�ڋq) <BR>
     * �ڋq�I�u�W�F�N�g <BR>
     * @@param l_strFutureOptionDiv - (�敨�^�I�v�V�����敪)<BR>
     * �敨�^�I�v�V�����敪<BR>
     * @@param l_strCarryoverProcessType - (�����J�z�����敪)<BR>
     * �����J�z�����敪<BR>
     * @@throws WEB3BaseException
     */
    public void createNextOrder(
        MainAccount l_mainAccount,
        String l_strFutureOptionDiv,
        String l_strCarryoverProcessType)
        throws WEB3BaseException
    {
        super.createNextOrder(l_mainAccount, l_strFutureOptionDiv, l_strCarryoverProcessType);
    }

    /**
     *�iget�敨�^�I�v�V�����敪�j<BR>
     * �敨�^�I�v�V�����敪���擾����B<BR>
     * <BR>
     * "�I�v�V����"��ԋp����B
     * @@return String
     */
    protected String getFutureOptionDiv()
    {
        return WEB3FuturesOptionDivDef.OPTION;
    }

    /**
     * �iexpire�J�z�������j<BR>
     * �J�z�������̎����������s���B<BR>
     * <BR>
     * OP�����J�zUnitServiceImpl.expire�J�z������()�� <BR>
     * �������Ϗ�����B<BR>
     * <BR>
     * �������́A�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * @@param l_orderUnit - �����P�� <BR>
     * �i�J�z���j�����P�ʃI�u�W�F�N�g
     * @@throws WEB3BaseException
     */
    protected void expireCarryOverOriginOrder(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "expireCarryOverOriginOrder(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        // OP�����J�z�ꌏ�T�[�r�X���擾����
        WEB3OptionOrderCarryOverUnitService l_optionOrderCarryOverUnitService =
            (WEB3OptionOrderCarryOverUnitService)Services.getService(
                WEB3OptionOrderCarryOverUnitService.class);
        l_optionOrderCarryOverUnitService.expireCarryOverOriginOrder(l_orderUnit);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     *�i�]�͍Čv�Z�j<BR>
     * �]�͍Čv�Z���s���B<BR>
     * <BR>
     * �P�j�@@OP��������^�C�v���擾����B <BR>
     * �p�����[�^.�ڋq.getOP��������^�C�v()���R�[������B<BR>
     * �Q�j�@@�P�j�̖߂�l != "�؋�������"�̏ꍇ�A<BR>
     * ����]�̓T�[�r�XImpl.�]�͍Čv�Z()���R�[������B<BR>
     * <BR>
     * [�]�͍Čv�Z()�Ɏw�肷�����] <BR>
     *    �⏕�����F�@@�擾�����⏕����(*1) <BR>
     * (*1)�⏕���� <BR>
     * �p�����[�^.�ڋq.getSubAccount(�P�j�̖߂�l)�ɂĎ擾�B<BR>
     * @@param l_mainAccount - �ڋq <BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    protected void reCalcTradingPower(MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "reCalcTradingPower(MainAccount)";
        log.entering(STR_METHOD_NAME);
        SubAccountTypeEnum l_subAccountType = null;
        SubAccount l_subAccount = null;
        
        if (l_mainAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            // �P�j�@@OP��������^�C�v���擾����B
            l_subAccountType = ((WEB3GentradeMainAccount)l_mainAccount).getOpSubAccountType();
            l_subAccount = l_mainAccount.getSubAccount(l_subAccountType);
        }
        catch (NotFoundException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
            // �P�j�̖߂�l != "�؋�������"�̏ꍇ
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccountType))
        {
            // ����]�̓T�[�r�XImpl���擾����
            WEB3TPTradingPowerService l_tpTradingPowerService = (WEB3TPTradingPowerService)
                Services.getService(WEB3TPTradingPowerService.class);

            // �]�͍Čv�Z
            l_tpTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �isubmit���������j<BR>
     * ����������o�^����B<BR>
     * �p�����[�^.�����P��.�����J�e�S���ɂ��  <BR>
     *  <BR>
     * �����𕪊򂷂�B<BR>
     * �����J�e�S�����A<BR>
     *  <BR>
     * ["OP�V�K������"�̏ꍇ] <BR>
     * OP�����J�zUnitServiceImpl.create�V�K����������()���R�[������B<BR>
     * <BR>
     * ["OP�ԍϒ���"�̏ꍇ] <BR>
     * OP�����J�zUnitServiceImpl.create�ԍϗ�������()���R�[������B <BR>
     *  <BR>
     * ���e���\�b�h�̈����ɂ́A�{���\�b�h�̈��������̂܂ܐݒ肷��B<BR>
     * @@param l_ifoOrderUnit  - �����P�� <BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_lisRsvIfoOrderUnits - (�\�񒍕��P�ʈꗗ)<BR>
     * �\�񒍕��P�ʈꗗ<BR>
     * @@throws WEB3BaseException
     */
    protected void submitNextOrder(IfoOrderUnit l_ifoOrderUnit, List l_lisRsvIfoOrderUnits)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitNextOrder(IfoOrderUnit, List)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // OP�����J�z�ꌏ�T�[�r�X���擾����
        WEB3OptionOrderCarryOverUnitService l_optionOrderCarryOverUnitService =
            (WEB3OptionOrderCarryOverUnitService)Services.getService(
                WEB3OptionOrderCarryOverUnitService.class);

        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        // OP�V�K������"�̏ꍇ
        if (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_ifoOrderUnitRow.getOrderCateg()))
        {
            // OP�����J�zUnitServiceImpl.create�V�K����������()���R�[������B
            l_optionOrderCarryOverUnitService.createOpenContractNextOrder(l_ifoOrderUnit, l_lisRsvIfoOrderUnits);
        }
        // "OP�ԍϒ���"�̏ꍇ
        if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_ifoOrderUnitRow.getOrderCateg()))
        {
            // OP�����J�zUnitServiceImpl.create�ԍϗ�������()���R�[������B
            l_optionOrderCarryOverUnitService.createSettleContractNextOrder(l_ifoOrderUnit, l_lisRsvIfoOrderUnits);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     *�iupdate�J�z�������j<BR>
     * �J�z�������̒����G���[���R�R�[�h���X�V����B<BR>
     * OP�����J�zUnitServiceImpl.update�J�z������()��<BR>
     * �������Ϗ�����B<BR>
     * <BR>
     * ���Ϗ��惁�\�b�h�̈����́A�{���\�b�h�̈��������̂܂ܐݒ肷��B<BR>
     *  <BR>
     * @@param l_orderUnit - �����P�� <BR>
     * �i�J�z���j�����P�ʃI�u�W�F�N�g <BR>
     * @@param l_strOrderErrorReasonCode - �����G���[���R�R�[�h <BR>
     * �����G���[���R�R�[�h <BR>
     * <BR>
     * DB���C�A�E�g <BR>
     * �����P�ʃe�[�u���d�l.xls <BR>
     * �u�i�����P�ʃe�[�u���⑫�j<BR>
     * �����G���[���R�R�[�h�v�V�[�g�Q�ƁB<BR>
     * @@throws WEB3BaseException
     */
    protected void updateCarryOverOriginOrder(OrderUnit l_orderUnit, String l_strOrderErrorReasonCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateCarryOverOriginOrder(OrderUnit, String)";
        log.entering(STR_METHOD_NAME);
        WEB3OptionOrderCarryOverUnitService l_optionOrderCarryOverUnitService =
            (WEB3OptionOrderCarryOverUnitService)Services.getService(
                WEB3OptionOrderCarryOverUnitService.class);
        try
        {
            //OP�����J�zUnitServiceImpl.update�J�z������()�ɏ������Ϗ�����B
            l_optionOrderCarryOverUnitService.updateCarryOverOriginOrder(l_orderUnit, l_strOrderErrorReasonCode);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@