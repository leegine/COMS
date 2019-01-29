head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3MarginTransferServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۏ؋��ւ̐U�փT�[�r�XImpl(WEB3MarginTransferServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/12 �����F (���u) �V�K�쐬 �d�l�ύX���f��736 739 DB�X�V�d�lNo.140�A141�A142�A143
Revision History : 2007/07/28 �Ј��� (���u) �d�l�ύX���f��741
Revision History : 2007/08/01 �����F (���u) �d�l�ύX���f��748
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioTransferOrderUpdateInterceptor;
import webbroker3.aio.service.delegate.WEB3MarginTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ۏ؋��ւ̐U�փT�[�r�XImpl)<BR>
 * �ۏ؋��ւ̐U�փT�[�r�X�����N���X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3MarginTransferServiceImpl implements WEB3MarginTransferService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginTransferServiceImpl.class);

    /**
     * (submit�ۏ؋��U��)<BR>
     * �ۏ؋��ւ̐U�փT�[�r�X�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�ۏ؋��ւ̐U�փT�[�r�X�v�Q�ƁB<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@param l_dblCashinAmt - (�����z)<BR>
     * �����z<BR>
     * @@param l_strPassword - (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * �㗝���͎�
     * @@throws WEB3BaseException
     */
    public void submitMarginTransfer(
        WEB3GentradeMainAccount l_mainAccount,
        Date l_datDeliveryDate,
        double l_dblCashinAmt,
        String l_strPassword,
        Trader l_trader) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitMarginTransfer(WEB3GentradeMainAccount, Date, double, String, Trader)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GentradeSubAccount l_subAccount1 = null;
        WEB3GentradeSubAccount l_subAccount2 = null;
        try
        {
            //getSubAccount(arg0 : SubAccountTypeEnum)
            //[����] arg0�F 1�i������������i�a����j�j
            l_subAccount1 =
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            //getSubAccount(arg0 : SubAccountTypeEnum)
            //[����] arg0�F 2�i�����M�p��������i�ۏ؋��j�j
            l_subAccount2 =
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get�ۏ؋��ւ̐U�֊z(�⏕���� : �⏕����, ��n�� : Date, �����z : double)
        //�⏕�����F getSubAccount()�̖߂�l�i�⏕�����@@[�a�������]�j
        //��n���F�@@����.��n��
        //�����z�F�@@����.�����z
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblMarginPowerTransferAmount =
            l_tpTradingPowerService.getTransferAmountToDeposit(
                l_subAccount1,
                l_datDeliveryDate,
                l_dblCashinAmt);

        log.debug("get�ۏ؋��ւ̐U�֊z = " + l_dblMarginPowerTransferAmount);

        if (l_dblMarginPowerTransferAmount > 0)
        {
            //get���iID(Institution)
            //[����]
            //�،���ЁF �⏕�����@@[�a�������].get����X().getInstitution()�̖߂�l
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3AioOrderManager l_aioOrderManager =
                (WEB3AioOrderManager)l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();
            long l_lngProductId =
                l_aioOrderManager.getProductId(l_subAccount1.getWeb3GenBranch().getInstitution());

            //get�V�K���ʃR�[�h(�،���ЃR�[�h:String, ���X�R�[�h:String, �����^�C�v:ProductTypeEnum)
            //[����]
            //�،���ЃR�[�h�F �⏕�����@@[�a�������].getInstitution().�،���ЃR�[�h
            //���X�R�[�h�F�@@�⏕�����@@[�a�������].get����X().getBranchCode()
            //�����^�C�v�F 5�i�����j
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);
            String l_strNewNumber =
                l_hostReqOrderNumberManageService.getNewNumber(
                    l_subAccount1.getInstitution().getInstitutionCode(),
                    l_subAccount1.getWeb3GenBranch().getBranchCode(),
                    ProductTypeEnum.CASH);

            //���o���������e
            //[����]
            //�㗝���͎ҁF ����.�㗝���͎�
            //������ʁF 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j
            //�U�փ^�C�v�F 2�i�o���j
            //���iID�F get���iID()�̖߂�l
            //���z�F get�ۏ؋��U�։\�z()�̖߂�l
            //�L�q�F null
            //�U�֗\����F ����.��n��
            //���ϋ@@��ID�F null
            //����ID�F null
            WEB3AioNewOrderSpec l_aioNewOrderSpec1 = new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                AssetTransferTypeEnum.CASH_OUT,
                l_lngProductId,
                l_dblMarginPowerTransferAmount,
                null,
                l_datDeliveryDate,
                null,
                null);

            //�U�֒����X�V�C���^�Z�v�^(���o���������e)
            //[�����̐ݒ�]
            //���o���������e�F�@@���o���������e�i�U�֒����@@�j
            WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor1 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec1);
            l_transferOrderUpdateInterceptor1.setBizDate(l_datDeliveryDate);
            l_transferOrderUpdateInterceptor1.setDeliveryDate(l_datDeliveryDate);
            l_transferOrderUpdateInterceptor1.setOrderRequestNumber(l_strNewNumber);
            l_transferOrderUpdateInterceptor1.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);

            //���o���������e
            //[����]
            //�㗝���͎ҁF ����.�㗝���͎�
            //������ʁF 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j
            //�U�փ^�C�v�F 1�i�����j
            //���iID�F get���iID()�̖߂�l
            //���z�F get�ۏ؋��U�։\�z()�̖߂�l
            //�L�q�F null
            //�U�֗\����F ����.��n��
            //���ϋ@@��ID�F null
            //����ID�F null
            WEB3AioNewOrderSpec l_aioNewOrderSpec2 = new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                AssetTransferTypeEnum.CASH_IN,
                l_lngProductId,
                l_dblMarginPowerTransferAmount,
                null,
                l_datDeliveryDate,
                null,
                null);

            //�U�֒����X�V�C���^�Z�v�^(���o���������e)
            //[�����̐ݒ�]
            //���o���������e�F�@@���o���������e�i�U�֒����A�j
            WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor2 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec2);
            l_transferOrderUpdateInterceptor2.setBizDate(l_datDeliveryDate);
            l_transferOrderUpdateInterceptor2.setDeliveryDate(l_datDeliveryDate);
            l_transferOrderUpdateInterceptor2.setOrderRequestNumber(l_strNewNumber);
            l_transferOrderUpdateInterceptor2.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);

            //createNewOrderId
            //�V�K����ID���̔Ԃ���B�i����ID�@@�j
            long l_lngOrderId1 = l_aioOrderManager.createNewOrderId();

            //submit�U�֒���
            //�M�p�U�֒����i�U�֌��j��o�^����
            //[����]
            //�⏕�����F getSubAccount()�̖߂�l�i�⏕�����@@[�a�������]�j
            //�����^�C�v�F 5�i�����j
            //������ʁF 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j
            //�������e�F ���o���������e�i�U�֒����@@�j
            //�C���^�Z�v�^�F �U�֒����X�V�C���^�Z�v�^�i�U�֒����@@�j
            //����ID�F createNewOrderId()�̖߂�l�i����ID�@@�j
            //�p�X���[�h�F ����.�Ïؔԍ�
            l_aioOrderManager.submitTransferOrder(
                l_subAccount1,
                ProductTypeEnum.CASH,
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                l_aioNewOrderSpec1,
                l_transferOrderUpdateInterceptor1,
                l_lngOrderId1,
                l_strPassword);

            //createNewOrderId
            //�V�K����ID���̔Ԃ���B�i����ID�A�j
            long l_lngOrderId2 = l_aioOrderManager.createNewOrderId();

            //submit�U�֒���
            //�M�p�U�ւ̔��Β����i�U�֐�j��o�^����
            //[����]
            //�⏕�����F getSubAccount()�̖߂�l�i�⏕�����A[�ۏ؋�����]�j
            //�����^�C�v�F 5�i�����j
            //������ʁF 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j
            //�������e�F ���o���������e�i�U�֒����A�j
            //�C���^�Z�v�^�F �U�֒����X�V�C���^�Z�v�^�i�U�֒����A�j
            //����ID�F createNewOrderId()�̖߂�l�i����ID�A�j
            //�p�X���[�h�F ����.�Ïؔԍ�
            l_aioOrderManager.submitTransferOrder(
                l_subAccount2,
                ProductTypeEnum.CASH,
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                l_aioNewOrderSpec2,
                l_transferOrderUpdateInterceptor2,
                l_lngOrderId2,
                l_strPassword);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
