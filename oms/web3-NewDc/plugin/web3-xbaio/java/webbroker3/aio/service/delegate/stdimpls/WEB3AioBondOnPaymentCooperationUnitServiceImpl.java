head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioBondOnPaymentCooperationUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���A�gUnitServiceImpl(WEB3AioBondOnPaymentCooperationUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 ���G�� (���u) �V�K�쐬
Revesion History : 2007/03/12 �����q (���u)  ���f��No.710
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

import webbroker3.aio.WEB3AioBondOnPaymentInfo;
import webbroker3.aio.WEB3AioForeignCashTransOrderUpdateInterceptor;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.service.delegate.WEB3AioBondOnPaymentCooperationUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchLockDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���o���A�gUnitServiceImpl)<BR>
 * ���o���A�gUnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor(<BR>
 * TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioBondOnPaymentCooperationUnitServiceImpl
    implements WEB3AioBondOnPaymentCooperationUnitService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioBondOnPaymentCooperationUnitServiceImpl.class);
    /**
     * (submit����)<BR>
     * ������̒����̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o���A�g�jsubmit�����v �Q��<BR>
     * <BR>
     * @@param l_bondOnPaymentInfo - ���o�����
     * @@throws WEB3BaseException
     */
    public void submitOrder(WEB3AioBondOnPaymentInfo l_bondOnPaymentInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3AioBondOnPaymentInfo l_bondOnPaymentInfo)";
        log.entering(STR_METHOD_NAME);

        if (l_bondOnPaymentInfo == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //1.1.get�ڋq(����ID : long)
            //�ڋq�I�u�W�F�N�g���擾����B
            //[����]
            //����ID�F ����.���o�����.����ID
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            MainAccount l_mainAccount =
                l_accountManager.getMainAccount(
                    l_bondOnPaymentInfo.getAccountId().longValue());

            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_mainAccount.getDataSourceObject();

            //1.2.�x�X���b�N�̏ꍇ
            //�ڋq.�x�X���b�N = "1"�i���b�N�j�̏ꍇ
            if (WEB3BranchLockDef.BRANCH_LOCK.equals(l_mainAccountRow.getBranchLock()))
            {
                //1.2.1.return
                log.debug("�ڋq.�x�X���b�N = 1�i���b�N�j�̏ꍇ return");
                return;
            }

            //1.3.getInstitution( )
            //�،���ЃI�u�W�F�N�g���擾����B
            String l_strInstitutionCode = l_mainAccountRow.getInstitutionCode();
            Institution l_institution =
                l_accountManager.getInstitution(l_strInstitutionCode);

            //1.4.get���iID(Institution)
            //���o���p�̏��iID���擾����B
            //[����]
            //�،���ЁF �،���ЃI�u�W�F�N�g
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.AIO);
            WEB3AioOrderManager l_orderManager =
                (WEB3AioOrderManager)l_tradingModule.getOrderManager();

            long l_lngProductId = l_orderManager.getProductId(l_institution);

            //1.5.createNewOrderId( )
            //�V�K����ID���擾����B
            long l_lngNewOrderId = l_orderManager.createNewOrderId();

            //1.6.���o���������e
            //���o���������e�C���X�^���X�𐶐�����B
            //[����]
            //���ҁF null
            Trader l_trader = null;

            //������ʁF 1001�i�o�������j
            OrderTypeEnum l_orderType = OrderTypeEnum.CASH_OUT;

            //�U�փ^�C�v�F 2�i�o���j
            AssetTransferTypeEnum l_assetTransferType = AssetTransferTypeEnum.CASH_OUT;

            //���iID�F get���iID()�̖߂�l
            //���z�F �~�݁i����.���o�����.���ϋ敪 = "1"�i�~�݁j�j�̏ꍇ�A����.���o�����.��n����i�~�݁j
            //�@@�@@�@@�@@�O�݁i����.���o�����.���ϋ敪 = "2"�i�O�݁j�j�̏ꍇ�A����.���o�����.��n����i�O�݁j
            Double l_amount = new Double(0);
            double l_dblAmount = 0.0D;
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(
                l_bondOnPaymentInfo.getSettlementDiv()))
            {
                log.debug("����.���o�����.���ϋ敪 = 1�i�~�݁j�j�̏ꍇ");
                l_amount = l_bondOnPaymentInfo.getEstimatedPrice();
            }
            else if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(
                l_bondOnPaymentInfo.getSettlementDiv()))
            {
                log.debug("�i����.���o�����.���ϋ敪 = 2�i�O�݁j�j�̏ꍇ");
                l_amount = l_bondOnPaymentInfo.getForeignEstimatedPrice();
            }
            if (l_amount != null)
            {
                l_dblAmount = l_amount.doubleValue();
            }
            //�L�q�F �~�݁i����.���o�����.���ϋ敪 = "1"�i�~�݁j�j�̏ꍇ�Anull
            //�O�݁i����.���o�����.���ϋ敪 = "2"�i�O�݁j�j�̏ꍇ�A����.���o�����.�������P��ID
            String l_strDescription = null;
            if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(
                    l_bondOnPaymentInfo.getSettlementDiv()))
            {
                l_strDescription = l_bondOnPaymentInfo.getBondOrderUnitId() + "";
            }
            //�U�֗\����F ����.���o�����.��n��
            //���ϋ@@��ID�F null
            //����ID�F createNewOrderId( )�̖߂�l
            WEB3AioNewOrderSpec l_aioNewOrderSpec = new WEB3AioNewOrderSpec(
                l_trader,
                l_orderType,
                l_assetTransferType,
                l_lngProductId,
                l_dblAmount,
                l_strDescription,
                l_bondOnPaymentInfo.getDeliveryDate(),
                null,
                new Long(l_lngNewOrderId));

            //1.7.get������( )
            //���������擾����B
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //1.8.getSubAccount(�⏕�����^�C�v : SubAccountTypeEnum)
            //�⏕�����I�u�W�F�N�g���擾����B
            //[����]
            //�⏕�����^�C�v�F 1�i�a��������j
            SubAccount l_subAccount =
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            //1.9.get�V�K���ʃR�[�h
            //�V�K�̎��ʃR�[�h���擾����B
            //[����]
            // �،���ЃR�[�h�F �⏕��������擾�����،���ЃR�[�h
            // ���X�R�[�h�F �⏕��������擾�������X�R�[�h
            // �����^�C�v�F 5�i�����j
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);
            String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();

            String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_strBranchCode,
                ProductTypeEnum.CASH);

            //1.10.�O�ݓ��o�������X�V�C���^�Z�v�^(���o���������e)
            //�O�ݓ��o�������X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B
            //[����]
            //���o���������e�F ���o���������e�I�u�W�F�N�g
            WEB3AioForeignCashTransOrderUpdateInterceptor
                l_foreignCashTransOrderUpdateInterceptor =
                    new WEB3AioForeignCashTransOrderUpdateInterceptor(l_aioNewOrderSpec);

            //1.11.�v���p�e�B�Z�b�g
            //(*) �ȉ��̂Ƃ���ɃC���^�Z�v�^�̃v���p�e�B���Z�b�g����B
            //�C���^�Z�v�^.������ = ������ԒP��.get������()�̖߂�l
            l_foreignCashTransOrderUpdateInterceptor.setBizDate(l_datBizDate);

            //�C���^�Z�v�^.��n�� = ����.���o�����.��n��
            l_foreignCashTransOrderUpdateInterceptor.setDeliveryDate(
                l_bondOnPaymentInfo.getDeliveryDate());

            //�C���^�Z�v�^.���ʃR�[�h = get�V�K���ʃR�[�h()�̖߂�l
            l_foreignCashTransOrderUpdateInterceptor.setOrderRequestNumber(
                l_strNewNumber);

            //�C���^�Z�v�^.�ʉ݃R�[�h = ����.���o�����.�ʉ݃R�[�h
            l_foreignCashTransOrderUpdateInterceptor.setCurrencyCode(
                l_bondOnPaymentInfo.getCurrencyCode());

            //�C���^�Z�v�^.���o�����z(�~���Z�z)
            //�@@= �~�݁i����.���o�����.���ϋ敪 = "1"�i�~�݁j�j�̏ꍇ�Anull
            //�@@�@@�O�݁i����.���o�����.���ϋ敪 = "2"�i�O�݁j�j�̏ꍇ�A����.���o�����.��n����i�~�݁j
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(
                l_bondOnPaymentInfo.getSettlementDiv()))
            {
                log.debug("�i����.���o�����.���ϋ敪 = 1�i�~�݁j�j�̏ꍇ");
                l_foreignCashTransOrderUpdateInterceptor.setConvertAmount(null);
            }
            else if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(
                l_bondOnPaymentInfo.getSettlementDiv()))
            {
                log.debug("�i����.���o�����.���ϋ敪 = 2�i�O�݁j�j�̏ꍇ");
                l_foreignCashTransOrderUpdateInterceptor.setConvertAmount(
                    l_bondOnPaymentInfo.getEstimatedPrice());
            }

            //1.12.setThreadLocalPersistenceEventInterceptor(
            //�O�ݓ��o�������X�V�C���^�Z�v�^ : AioOrderManagerPersistenceEventInterceptor)
            //�O�ݓ��o�������X�V�C���^�Z�v�^�F �O�ݓ��o�������X�V�C���^�Z�v�^�I�u�W�F�N�g
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_foreignCashTransOrderUpdateInterceptor);

            //1.13.submitNewOrder(�⏕���� : SubAccount, ���i�^�C�v : ProductTypeEnum,
            //�������e : NewOrderSpec, ����ID : long, �p�X���[�h : String, isSkip�����R�� : boolean)
            //�����o�^�������s���B
            //[����]
            //�⏕�����F �⏕�����I�u�W�F�N�g
            //���i�^�C�v�F 5�i�����j
            //�������e�F ���o���������e�I�u�W�F�N�g
            //����ID�F createNewOrderId()�̖߂�l
            //�p�X���[�h�F �ڋq.getTradingPassword()�̖߂�l��WEB3Crypt.decrypt()�ŕ�����������
            //isSkip�����R���F true
            WEB3Crypt l_webCrypt = new WEB3Crypt();
            OrderSubmissionResult l_submitNewOrderResult =
                l_orderManager.submitNewOrder(
                    l_subAccount,
                    ProductTypeEnum.CASH,
                    l_aioNewOrderSpec,
                    l_lngNewOrderId,
                    l_webCrypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);

            if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
            {
                log.debug("�����o�^�������s�� Error"
                    + l_submitNewOrderResult.getProcessingResult().getErrorInfo());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                    STR_METHOD_NAME);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�������擾����: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�~�ݒ���)<BR>
     * �~�ݗp�̍��o�����C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j���o�����C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�ȉ��̂Ƃ���ɍ��o�����̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �E���o�����.����ID = new Long(����.�������P��Params.����ID)<BR>
     * �E���o�����.���XID = new Long(����.�������P��Params.���XID)<BR>
     * �E���o�����.��n�� = new Timestamp(����.�������P��Params.��n��.getTime())<BR>
     * �E���o�����.���ϋ敪 = new String(����.�������P��Params.���ϋ敪)<BR>
     * �E���o�����.��n����i�~�݁j =<BR>
     * new Double(����.�������P��Params.��n����i�~�݁j.doubleValue())<BR>
     * <BR>
     * �R�j���o�����I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_bondOrderUnitParams - �������P��Params�I�u�W�F�N�g<BR>
     * @@return WEB3AioBondOnPaymentInfo
     */
    public WEB3AioBondOnPaymentInfo createJapaneseOrder(BondOrderUnitParams l_bondOrderUnitParams)
    {
        final String STR_METHOD_NAME = "createJapaneseOrder(BondOrderUnitParams l_bondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        //�P�j���o�����C���X�^���X�𐶐�����B
        WEB3AioBondOnPaymentInfo l_bondOnPaymentInfo = new WEB3AioBondOnPaymentInfo();

        //�Q�j�ȉ��̂Ƃ���ɍ��o�����̃v���p�e�B���Z�b�g����B
        // �E���o�����.����ID = new Long(����.�������P��Params.����ID)
        l_bondOnPaymentInfo.setAccountId(new Long(l_bondOrderUnitParams.getAccountId()));

        // �E���o�����.���XID = new Long(����.�������P��Params.���XID)
        l_bondOnPaymentInfo.setBranchId(new Long(l_bondOrderUnitParams.getBranchId()));

        // �E���o�����.��n�� = new Timestamp(����.�������P��Params.��n��.getTime())
        l_bondOnPaymentInfo.setDeliveryDate(new Timestamp(
            l_bondOrderUnitParams.getDeliveryDate().getTime()));

        // �E���o�����.���ϋ敪 = new String(����.�������P��Params.���ϋ敪)
        l_bondOnPaymentInfo.setSettlementDiv(l_bondOrderUnitParams.getSettlementDiv());

        // �E���o�����.��n����i�~�݁j=
        // new Double(����.�������P��Params.��n����i�~�݁j.doubleValue())
        Double l_estimatedPrice = null;
        if (!l_bondOrderUnitParams.getEstimatedPriceIsNull())
        {
            l_estimatedPrice = new Double(l_bondOrderUnitParams.getEstimatedPrice());
        }
        l_bondOnPaymentInfo.setEstimatedPrice(l_estimatedPrice);

        //�R�j���o�����I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_bondOnPaymentInfo;
    }

    /**
     * (create�O�ݒ���)<BR>
     * �O�ݗp�̍��o�����C���X�^���X�𐶐�����B
     * <BR>
     * �P�j���o�����C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j�ȉ��̂Ƃ���ɍ��o�����̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �E���o�����.����ID = new Long(����.�������P��Params.����ID) <BR>
     * �E���o�����.���XID = new Long(����.�������P��Params.���XID) <BR>
     * �E���o�����.��n�� = new Timestamp(����.�������P��Params.��n��.getTime()) <BR>
     * �E���o�����.�ʉ݃R�[�h = new String(����.�������P��Params.�ʉ݃R�[�h) <BR>
     * �E���o�����.���ϋ敪 = new String(����.�������P��Params.���ϋ敪) <BR>
     * �E���o�����.��n����i�~�݁j = <BR>
     * �@@�@@new Double(����.�������P��Params.��n����i�~�݁j.doubleValue()) <BR>
     * �E���o�����.��n����i�O�݁j = <BR>
     * �@@�@@new Double(����.�������P��Params.��n����i�O�݁j.doubleValue())<BR>
     * �E���o�����.�������P��ID = new Long(����.�������P��Params.�����P��ID)<BR>
     * <BR>
     *  �R�j���o�����I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_bondOrderUnitParams - �������P��Params�I�u�W�F�N�g<BR>
     * @@return WEB3AioBondOnPaymentInfo
     */
    public WEB3AioBondOnPaymentInfo createForeignOrder(BondOrderUnitParams l_bondOrderUnitParams)
    {
        final String STR_METHOD_NAME = "createForeignOrder(BondOrderUnitParams l_bondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        //�P�j���o�����C���X�^���X�𐶐�����B
        WEB3AioBondOnPaymentInfo l_bondOnPaymentInfo = new WEB3AioBondOnPaymentInfo();

        //�Q�j�ȉ��̂Ƃ���ɍ��o�����̃v���p�e�B���Z�b�g����B
        //* �E���o�����.����ID = new Long(����.�������P��Params.����ID)
        l_bondOnPaymentInfo.setAccountId(new Long(l_bondOrderUnitParams.getAccountId()));

        //* �E���o�����.���XID = new Long(����.�������P��Params.���XID)
        l_bondOnPaymentInfo.setBranchId(new Long(l_bondOrderUnitParams.getBranchId()));

        //* �E���o�����.��n�� = new Timestamp(����.�������P��Params.��n��.getTime())
        l_bondOnPaymentInfo.setDeliveryDate(new Timestamp(
            l_bondOrderUnitParams.getDeliveryDate().getTime()));

        //* �E���o�����.�ʉ݃R�[�h = new String(����.�������P��Params.�ʉ݃R�[�h)
        l_bondOnPaymentInfo.setCurrencyCode(l_bondOrderUnitParams.getCurrencyCode());

        //* �E���o�����.���ϋ敪 = new String(����.�������P��Params.���ϋ敪)
        l_bondOnPaymentInfo.setSettlementDiv(l_bondOrderUnitParams.getSettlementDiv());

        //* �E���o�����.��n����i�~�݁j =
        //* �@@�@@new Double(����.�������P��Params.��n����i�~�݁j.doubleValue())
        Double l_estimatedPrice = null;
        if (!l_bondOrderUnitParams.getEstimatedPriceIsNull())
        {
            l_estimatedPrice = new Double(l_bondOrderUnitParams.getEstimatedPrice());
        }
        l_bondOnPaymentInfo.setEstimatedPrice(l_estimatedPrice);

        //* �E���o�����.��n����i�O�݁j =
        //* �@@�@@new Double(����.�������P��Params.��n����i�O�݁j.doubleValue())
        Double l_foreignEstimatedPrice = null;
        if (!l_bondOrderUnitParams.getForeignEstimatedPriceIsNull())
        {
            l_foreignEstimatedPrice = new Double(l_bondOrderUnitParams.getForeignEstimatedPrice());
        }
        l_bondOnPaymentInfo.setForeignEstimatedPrice(l_foreignEstimatedPrice);
        
        //�E���o�����.�������P��ID = new Long(����.�������P��Params.�����P��ID)
        l_bondOnPaymentInfo.setBondOrderUnitId(new Long(l_bondOrderUnitParams.getOrderUnitId()));

        //�R�j���o�����I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_bondOnPaymentInfo;
    }

    /**
     * (calc�~�ݒ���)<BR>
     * �~�ݒ�����Gross����B<BR>
     * <BR>
     * �P�j�ȉ��̂Ƃ���ɁA����.�������P��Params�̃v���p�e�B�� <BR>
     * �@@ ����.���o�����̃v���p�e�B�ɉ��Z����B <BR>
     * <BR>
     * �E����.���o�����.��n����i�~�݁j += ����.�������P��Params.��n����i�~�݁j<BR>
     * <BR>
     * @@param l_bondOnPaymentInfo - (���o�����)<BR>
     * ���o�����I�u�W�F�N�g<BR>
     * @@param l_bondOrderUnitParams - (�������P��Params)<BR>
     * �������P��Params�I�u�W�F�N�g<BR>
     */
    public void calcJapaneseOrder(WEB3AioBondOnPaymentInfo l_bondOnPaymentInfo,
        BondOrderUnitParams l_bondOrderUnitParams)
    {
        final String STR_METHOD_NAME = "calcJapaneseOrder("
            + "WEB3AioBondOnPaymentInfo, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        //�E����.���o�����.��n����i�~�݁j += ����.�������P��Params.��n����i�~�݁j
        Double l_estimatedPrice = null;
        double l_dblEstimatedPrice = 0.0D;
        if (l_bondOnPaymentInfo.getEstimatedPrice() != null)
        {
            l_dblEstimatedPrice = l_bondOnPaymentInfo.getEstimatedPrice().doubleValue();
        }
        l_estimatedPrice = new Double(l_dblEstimatedPrice
            + l_bondOrderUnitParams.getEstimatedPrice());
        l_bondOnPaymentInfo.setEstimatedPrice(l_estimatedPrice);

        log.exiting(STR_METHOD_NAME);
    }
}
@
