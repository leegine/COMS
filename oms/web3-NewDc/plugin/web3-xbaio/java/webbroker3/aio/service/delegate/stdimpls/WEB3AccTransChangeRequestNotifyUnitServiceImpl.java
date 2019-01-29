head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֐����ʒmUnitServiceImpl(WEB3AccTransChangeRequestNotifyUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/26 ���E(���u) ���r���[
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioTransferNotifyUpdateInterceptor;
import webbroker3.aio.data.HostTransferReceiptParams;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�U�֐����ʒmUnitServiceImpl)<BR>
 * �U�֐����ʒmUnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestNotifyUnitServiceImpl
    implements WEB3AccTransChangeRequestNotifyUnitService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeRequestNotifyUnitServiceImpl.class);

    /**
     * (submit����)<BR>
     * SONAR����̐U�֒����̓o�^���s���A�V�K�����̒���ID��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�U�֐����ʒm�jsubmit�����v �Q��<BR>
     * @@param l_hostTransferReceiptParams - (�U�֓��͒ʒm�L���[Params�I�u�W�F�N�g)
     * @@param l_orderType - (�������)
     * @@param l_changeType - (�U�փ^�C�v)
     * @@return long
     * @@throws WEB3BaseException
     * @@roseuid 413C2D4300FB
     */
    public long submitOrder(
        HostTransferReceiptParams l_hostTransferReceiptParams,
        OrderTypeEnum l_orderType,
        AssetTransferTypeEnum l_changeType)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitOrder(" +
                "HostTransferReceiptParams l_hostTransferReceiptParams," +
                "OrderTypeEnum l_orderType," +
                "AssetTransferTypeEnum l_changeType)";
                
        log.entering(STR_METHOD_NAME);

        if (l_hostTransferReceiptParams == null 
                || l_orderType == null
                || l_changeType == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1) �،���ЃI�u�W�F�N�g���擾����B 
        // [����] 
        // �،���ЃR�[�h�F ����.�U�֓��͒ʒm�L���[Params.�،���ЃR�[�h
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)GtlUtils.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
                
        long l_lngNewOrderId = 0;
        try
        {                
            Institution l_institution = 
                l_accountManager.getInstitution(
                    l_hostTransferReceiptParams.getInstitutionCode());
                    
            // 1.2) �㗝���͎҃I�u�W�F�N�g���擾����B 
            // [����] 
            // �،���ЁF�،���ЃI�u�W�F�N�g 
            // ���҃R�[�h�F ����.�U�֓��͒ʒm�L���[Params.���҃R�[�h 
            String l_strTraderCode = l_hostTransferReceiptParams.getTraderCode();
            // ���X�R�[�h�F ����.�U�֓��͒ʒm�L���[Params.���X�R�[�h
            Trader l_trader = null;
            if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strTraderCode))
            {
                FinObjectManager l_finObjectManager = GtlUtils.getFinObjectManager();
                l_trader = l_finObjectManager.getTrader(
                    l_institution,
                    l_hostTransferReceiptParams.getTraderCode(),
                    l_hostTransferReceiptParams.getBranchCode());
            }
                            
            // 1.3) ���o���p�̏��iID���擾����B 
            // [����] 
            // �،���ЁF �،���ЃI�u�W�F�N�g
            long l_lngProductId = l_orderManager.getProductId(l_institution);
            
            // 1.4) ���o���������e�C���X�^���X�𐶐�����B 
            // [����] 
            // �㗝���͎ҁF�i�ȉ��̂Ƃ���j 
            // �U�֓��͒ʒm�L���[.���҃R�[�h != �u�����N �̏ꍇ�A���҃I�u�W�F�N�g 
            // �U�֓��͒ʒm�L���[.���҃R�[�h == �u�����N �̏ꍇ�Anull
            // ������ʁF ����.������� 
            // �U�փ^�C�v�F ����.�U�փ^�C�v 
            // ���iID�F get���iID()�̖߂�l 
            // ���z�F ����.�U�֓��͒ʒm�L���[Params.�U�֋��z 
            // �L�q�F �i�ȉ��̂Ƃ���j 
            //����.������� == ���̑��U�֒����i1017 or 1018�j �̏ꍇ�A����.�U�֓��͒ʒm�L���[Params.�E�v�R�[�h 
            //����.������� != ���̑��U�֒����i1017 or 1018�j �̏ꍇ�Anull 
            // �U�֗\����F �U�֓��͒ʒm�L���[Params.�U�֎w�����t�i���P�j 
            // ���ϋ@@��ID�F null 
            // ����ID�F null 
            
            //�i���P�j 
            //�U�֓��͒ʒm�L���[Params.�U�֎w�����t��NULL�̏ꍇ�́A���ݓ��t���Z�b�g����B

            String l_strDescription = null;
            if (OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderType) || 
                OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderType))
            {
                l_strDescription = l_hostTransferReceiptParams.getRemarkCode();
            }
            else
            {
                l_strDescription = null;
            }
            
            Date l_datSysTime = 
                WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
            
            Date l_datEstTransferDate = null;
            
            //�U�֓��͒ʒm�L���[Params.�U�֎w�����t��NULL�̏ꍇ�́A���ݓ��t���Z�b�g����B
            if (l_hostTransferReceiptParams.getTransferDate() == null)
            {
                l_datEstTransferDate = l_datSysTime;
            }
            else
            {
                l_datEstTransferDate = WEB3DateUtility.getDate(
                    l_hostTransferReceiptParams.getTransferDate(), "yyyyMMdd");
            }
            
            if (l_datEstTransferDate == null)
            {
				log.error("�U�֐����ʒm�L���[.�U�֎w�����t���s�� ");
				throw new WEB3BaseRuntimeException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80017,
					this.getClass().getName() + "." + STR_METHOD_NAME);
          	}
			
            WEB3AioNewOrderSpec l_AioNewOrderSpec = 
                new WEB3AioNewOrderSpec(
                    l_trader, 
                    l_orderType, 
                    l_changeType, 
                    l_lngProductId, 
                    l_hostTransferReceiptParams.getTransferAmount(), 
                    l_strDescription, 
                    WEB3DateUtility.toDay(l_datEstTransferDate), 
                    null, 
                    null);
                
            // 1.5) �ڋq�I�u�W�F�N�g���擾����B 
            // [����] 
            // �،����ID�F �،����.getInstitutionId()�̖߂�l 
            // ���X�R�[�h�F ����.�U�֓��͒ʒm�L���[Params.���X�R�[�h 
            // �ڋq�R�[�h�F ����.�U�֓��͒ʒm�L���[Params.�ڋq�R�[�h 
            MainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_institution.getInstitutionId(),
                l_hostTransferReceiptParams.getBranchCode(),
                l_hostTransferReceiptParams.getAccountCode());
                
            // 1.6) �⏕�����^�C�v���擾����B 
            // [����] 
            // ������ʁF ����.������� 
            // �U�փ^�C�v�F ����.�U�փ^�C�v
            SubAccountTypeEnum l_subAccountType = 
                this.getSubAccountType(l_orderType,l_changeType);
            log.debug("�⏕�����^�C�v = " + l_subAccountType);
            
            // 1.7) �⏕�����I�u�W�F�N�g���擾����B 
            // [����] 
            // �⏕�����^�C�v�F get�⏕�����^�C�v()�̖߂�l
            SubAccount l_subAccount = l_mainAccount.getSubAccount(l_subAccountType);
            
            // 1.8) �V�K����ID���擾����B
            l_lngNewOrderId = l_orderManager.createNewOrderId();
            
            // 1.9) �U�֒ʒm�X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
            // [����] 
            // ���o���������e�F ���o���������e�I�u�W�F�N�g
            WEB3AioTransferNotifyUpdateInterceptor l_updateInterceptor = 
                new WEB3AioTransferNotifyUpdateInterceptor(l_AioNewOrderSpec);
            
            // 1.10) �v���p�e�B�Z�b�g   
            //�C���^�Z�v�^.������ = ���ݓ��t
            l_updateInterceptor.setBizDate(l_datSysTime);
            
            l_updateInterceptor.setDeliveryDate(
                l_AioNewOrderSpec.getEstimatedTransferDate());
            
            l_updateInterceptor.setOrderRequestNumber(
                l_hostTransferReceiptParams.getOrderRequestNumber());
                
            // 1.11) �C���^�Z�v�^���Z�b�g����B 
            // [����] 
            // �U�֒ʒm�X�V�C���^�Z�v�^�F �U�֒ʒm�X�V�C���^�Z�v�^�I�u�W�F�N�g
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            // 1.12) �����o�^�������s���B 
            // [����] 
            // �⏕�����F �⏕�����I�u�W�F�N�g 
            // ���i�^�C�v�F 5�i�����j 
            // �������e�F ���o���������e�I�u�W�F�N�g 
            // ����ID�F createNewOrderId()�̖߂�l 
            // �p�X���[�h�F �ڋq.getTradingPassword()�̖߂�l��WEB3Crypt.decrypt()�ŕ����������� 
            // isSkip�����R���F true 
            WEB3Crypt l_web3Crypt = new WEB3Crypt();
            OrderSubmissionResult l_submissionResult =
                l_orderManager.submitNewOrder(
                    l_subAccount,
                    ProductTypeEnum.CASH,
                    l_AioNewOrderSpec,
                    l_lngNewOrderId,
                    l_web3Crypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);
            if (l_submissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("�����o�^�������s�ł���");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_submissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error(" �e�[�u���ɊY������f�[�^������܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lngNewOrderId;
    }

    /**
     * (get�⏕�����^�C�v)<BR>
     * �������猈�肳���⏕�����^�C�v��ԋp����B<BR>
     * <BR>
     * �P�j����.������� = 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j�̏ꍇ<BR>
     * <BR>
     * �P�|�P�j����.�U�փ^�C�v = 1�i�����j�̏ꍇ<BR>
     * <BR>
     *    2�i�ۏ؋������j��ԋp����B<BR>
     * <BR>
     * �P�|�Q�j����.�U�փ^�C�v = 2�i�o���j�̏ꍇ<BR>
     * <BR>
     *    1�i�a��������j��ԋp����B<BR>
     * <BR>
     * �Q�j����.������� = 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j�̏ꍇ<BR>
     * <BR>
     * �Q�|�P�j����.�U�փ^�C�v = 1�i�����j�̏ꍇ<BR>
     * <BR>
     *    1�i�a��������j��ԋp����B<BR>
     * <BR>
     * �Q�|�Q�j����.�U�փ^�C�v = 2�i�o���j�̏ꍇ<BR>
     * <BR>
     *    2�i�ۏ؋������j��ԋp����B<BR>
     * <BR>
     * �R�j����.������� = 1007�i�U�֒����i�a������犔��؋����j�j�̏ꍇ<BR>
     * <BR>
     * �R�|�P�j����.�U�փ^�C�v = 1�i�����j�̏ꍇ<BR>
     * <BR>
     *    7�i�؋��������j��ԋp����B<BR>
     * <BR>
     * �R�|�Q�j����.�U�փ^�C�v = 2�i�o���j�̏ꍇ<BR>
     * <BR>
     *    1�i�a��������j��ԋp����B<BR>
     * <BR>
     * �S�j����.������� = 1008�i�U�֒����i����؋�������a����j�j�̏ꍇ<BR>
     * <BR>
     * �S�|�P�j����.�U�փ^�C�v = 1�i�����j�̏ꍇ<BR>
     * <BR>
     *    1�i�a��������j��ԋp����B<BR>
     * <BR>
     * �S�|�Q�j����.�U�փ^�C�v = 2�i�o���j�̏ꍇ<BR>
     * <BR>
     *    7�i�؋��������j��ԋp����B<BR>
     * <BR>
     * �T�j����.������ʂ��ȉ��̂����ꂩ�̏ꍇ <BR>
     * �@@�@@�@@1011�i�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�j <BR>
     * �@@�@@�@@1012�i�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�j <BR>
     *      1017�i���̑��U�֒����i�a�������X�j�j<BR>
     *      1018�i���̑��U�֒����iX����a����j�j<BR>
     * <BR>
     * 1�i�a��������j��ԋp����B<BR>
     * <BR>
     * @@param l_orderType - (�������)
     * @@param l_changeType - (�U�փ^�C�v)
     * @@return SubAccountTypeEnum
     * @@throws WEB3BaseException
     * @@roseuid 413D44D7020B
     */
    protected SubAccountTypeEnum getSubAccountType(
        OrderTypeEnum l_orderType,
        AssetTransferTypeEnum l_changeType)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getSubAccountType(" 
                + "OrderTypeEnum l_orderType," 
                + "AssetTransferTypeEnum l_changeType)";
        log.entering(STR_METHOD_NAME);

        if (l_orderType == null || l_changeType == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j����.������� = 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j�̏ꍇ 
        if(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_orderType))
        {
            //�P�|�P�j����.�U�փ^�C�v = 1�i�����j�̏ꍇ 
            //2�i�ۏ؋������j��ԋp����B 
            if(AssetTransferTypeEnum.CASH_IN.equals(l_changeType))
            {
                log.exiting(STR_METHOD_NAME);
                return SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
            }
            else
            {
                //�P�|�Q�j����.�U�փ^�C�v = 2�i�o���j�̏ꍇ 
                //1�i�a��������j��ԋp����B
                if(AssetTransferTypeEnum.CASH_OUT.equals(l_changeType))
                {
                    log.exiting(STR_METHOD_NAME);
                    return SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
                }
            }
        }

        //�Q�j����.������� = 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j�̏ꍇ 
        if(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType))
        {
            //�Q�|�P�j����.�U�փ^�C�v = 1�i�����j�̏ꍇ 
            //1�i�a��������j��ԋp����B 
            if(AssetTransferTypeEnum.CASH_IN.equals(l_changeType))
            {
                log.exiting(STR_METHOD_NAME);
                return SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }
            else
            {
                //�Q�|�Q�j����.�U�փ^�C�v = 2�i�o���j�̏ꍇ 
                //2�i�ۏ؋������j��ԋp����B
                if(AssetTransferTypeEnum.CASH_OUT.equals(l_changeType))
                {
                    log.exiting(STR_METHOD_NAME);
                    return SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
                }
            }
        }
        
        //�R�j����.������� = 1007�i�U�֒����i�a������犔��؋����j�j�̏ꍇ 
        if(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderType))
        {
            //�R�|�P�j����.�U�փ^�C�v = 1�i�����j�̏ꍇ 
            //7�i�؋��������j��ԋp����B 
            if(AssetTransferTypeEnum.CASH_IN.equals(l_changeType))
            {
                log.exiting(STR_METHOD_NAME);
                return SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT;
            }
            else
            {
                //�R�|�Q�j����.�U�փ^�C�v = 2�i�o���j�̏ꍇ 
                //1�i�a��������j��ԋp����B 
                if(AssetTransferTypeEnum.CASH_OUT.equals(l_changeType))
                {
                    log.exiting(STR_METHOD_NAME);
                    return SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
                }
            }
        }
        
        //�S�j����.������� = 1008�i�U�֒����i����؋�������a����j�j�̏ꍇ 
        if(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(l_orderType))
        {
            //�S�|�P�j����.�U�փ^�C�v = 1�i�����j�̏ꍇ 
            //1�i�a��������j��ԋp����B
            if(AssetTransferTypeEnum.CASH_IN.equals(l_changeType))
            {
                log.exiting(STR_METHOD_NAME);
                return SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }
            else
            {
                //�S�|�Q�j����.�U�փ^�C�v = 2�i�o���j�̏ꍇ 
                //7�i�؋��������j��ԋp����B 
                if(AssetTransferTypeEnum.CASH_OUT.equals(l_changeType))
                {
                    log.exiting(STR_METHOD_NAME);
                    return SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT;
                }
            }
        }
        
        //============= remain wei-nianqiong No.138 start ==================
        //�T�j����.������ʂ��ȉ��̂����ꂩ�̏ꍇ 
        //�@@1011�i�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�j 
        //�@@1012�i�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�j 
        //  1017�i���̑��U�֒����i�a�������X�j�j
        //  1018�i���̑��U�֒����iX����a����j�j
        //1�i�a��������j��ԋp����B
        if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType) ||
            OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderType) ||
            OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderType) ||
            OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderType))
        {
            log.exiting(STR_METHOD_NAME);
            return SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
        }
        //============= remain wei-nianqiong No.138 end ==================
        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
