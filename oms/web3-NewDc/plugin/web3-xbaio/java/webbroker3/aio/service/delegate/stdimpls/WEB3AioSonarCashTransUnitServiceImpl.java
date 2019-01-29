head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o��UnitServiceImpl(WEB3AioSonarCashTransUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���� (���u) �V�K�쐬
                   2004/10/26 ���z (���u) ���r���[
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
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
import webbroker3.aio.WEB3AioSonarCashTransUpdateInterceptor;
import webbroker3.aio.data.HostCashTransferParams;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (SONAR���o��UnitServiceImpl)<BR>
 * SONAR���o��UnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3AioSonarCashTransUnitServiceImpl 
    implements WEB3AioSonarCashTransUnitService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransUnitServiceImpl.class);  
    
    /**
     * (submit����)<BR>
     * SONAR����̒����̓o�^���s���A�V�K�����̒���ID��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iSONAR���o���jsubmit�����v �Q��<BR>
     * @@param l_hostCashTransferParams - (���o��Params)<BR>
     * ���o��Params�I�u�W�F�N�g<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@return long
     * throws WEB3BaseException
     * @@roseuid 41009F530148
     */
    public long submitOrder(HostCashTransferParams l_hostCashTransferParams)
        throws  WEB3BaseException
    {
         
        final String STR_METHOD_NAME = 
            "submitOrder(HostCashTransferParams l_hostCashTransferParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_hostCashTransferParams == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //========================FinApp=============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //�A�J�E���g�}�l�[�W���擾����
        AccountManager l_accMgr = l_finApp.getAccountManager();
        
        //�P.�P�jInstitutionImpl(�،���ЃR�[�h : String)
        //�،���ЃI�u�W�F�N�g���擾����B
        //[����] 
        //�،���ЃR�[�h�F ����.���o��Params.�،���ЃR�[�h 
        
        //�،���ЃR�[�h���擾����
        String l_strInstitutionCode = l_hostCashTransferParams.getInstitutionCode();
        log.debug("�،���ЃR�[�h���擾���� ===========" + l_strInstitutionCode);
        try
        {
            //===========================NotFoundException==================== 
            Institution l_institution = l_accMgr.getInstitution(l_strInstitutionCode);
            
            //�P.�Q�jTraderImpl(�،���� : Institution, ���҃R�[�h : String, ���X�R�[�h : String)
            //�㗝���͎҃I�u�W�F�N�g���擾����B 
            //[����] 
            //�،���ЁF�،���ЃI�u�W�F�N�g
            //���҃R�[�h�F ����.���o��Params.���҃R�[�h 
            //���X�R�[�h�F ����.���o��Params.���X�R�[�h 
            
            //���҃R�[�h���擾����
            String l_strTraderCode = l_hostCashTransferParams.getTraderCode();
            
            //���X�R�[�h���擾����
            String l_strBranchCode = l_hostCashTransferParams.getBranchCode();
            
            //===========================NotFoundException====================
            FinObjectManager l_finObjMgr = GtlUtils.getFinObjectManager();
            Trader l_trader = null;
            if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strTraderCode))
            {
                l_trader = l_finObjMgr.getTrader(
                    l_institution, 
                    l_strTraderCode, 
                    l_strBranchCode);
            }
                    
            log.debug("���҃R�[�h���擾���� ================" + l_strTraderCode);    
            
            //�P.�R�jget���iID(Institution)
            //���o���p�̏��iID���擾����B
            //[����] �،���ЁF �،���ЃI�u�W�F�N�g
            
            //AIO�����}�l�[�W�����擾���� 
            WEB3AioOrderManager l_AioOrderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();     
            
            //���o���p�̏��iID���擾����
            long l_lngProductId = l_AioOrderManager.getProductId(l_institution);
            log.debug("���o���p�̏��iID���擾����=============" + l_lngProductId);
            
            //�P.�S�j
            //���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum, 
            //long, double, String, Date, String, long)
            //���o���������e�C���X�^���X�𐶐�����B
               //[����]
               //�㗝���͎ҁF ���҃I�u�W�F�N�g
               //������ʁF �i�ȉ��̂Ƃ���j
               //����.���o��Params.���o���敪="1"�i�o���j�̏ꍇ�A1001�i�o�������j���Z�b�g����B
               //����.���o��Params.���o���敪="2"�i�����j�̏ꍇ�A1002�i���������j���Z�b�g����B
               //�U�փ^�C�v�F �i�ȉ��̂Ƃ���j
               //����.���o��Params.���o���敪="1"�i�o���j�̏ꍇ�A2�i�o���j���Z�b�g����B
               //����.���o��Params.���o���敪="2"�i�����j�̏ꍇ�A1�i�����j���Z�b�g����B
               //���iID�F get���iID()�̖߂�l
               //���z�F ����.���o��Params.���o�����z
               //�L�q�F null
               //�U�֗\����F ����.���o��Params.���o����
               //���ϋ@@��ID�F null
               //����ID�F null
            
            //������ʂ��擾����
            OrderTypeEnum l_orderType = null;
            
            //�U�փ^�C�v���擾����
            AssetTransferTypeEnum l_assetTransferType = null;
            
            //����.���o��Params.���o���敪="1"�i�o���j�̏ꍇ�A1001�i�o�������j���Z�b�g����B
            //����.���o��Params.���o���敪="1"�i�o���j�̏ꍇ�A2�i�o���j���Z�b�g����B
            if ((WEB3OrderDivDef.CASHOUT).equals(l_hostCashTransferParams.getOrderDiv()))
            {
                l_orderType = OrderTypeEnum.CASH_OUT;
                l_assetTransferType = AssetTransferTypeEnum.CASH_OUT;
            }
            else 
            {
                //����.���o��Params.���o���敪="2"�i�����j�̏ꍇ�A1002�i���������j���Z�b�g����B
                //����.���o��Params.���o���敪="2"�i�����j�̏ꍇ�A1�i�����j���Z�b�g����B
                if (((WEB3OrderDivDef.CASHIN).equals(l_hostCashTransferParams.getOrderDiv())))
                {
                    l_orderType = OrderTypeEnum.CASH_IN;
                    l_assetTransferType = AssetTransferTypeEnum.CASH_IN;
                }
            }
            
            //���o���������e�C���X�^���X�𐶐�����
            WEB3AioNewOrderSpec l_aioNewOrderSpec =
                new WEB3AioNewOrderSpec(
                    l_trader, 
                    l_orderType, 
                    l_assetTransferType, 
                    l_lngProductId, 
                    l_hostCashTransferParams.getAmount(), 
                    null,
                    l_hostCashTransferParams.getCashTransDate(),
                    null,
                    null);   
            
            //�P.�T�j MainAccountImpl(�،����ID : long, ���X�R�[�h : String, �ڋq�R�[�h : String)
            //�ڋq�I�u�W�F�N�g���擾����B
            //[����]<BR>
            //�،����ID�F �،����.getInstitutionId()�̖߂�l
            //���X�R�[�h�F ����.���o��Params.���X�R�[�h
            //�ڋq�R�[�h�F ����.���o��Params.�ڋq�R�[�h
            
            //�،����ID���擾����
            long l_lngInstitutionId = l_institution.getInstitutionId();
            
            //�ڋq�R�[�h���擾����
            String l_strMainAccountCode = l_hostCashTransferParams.getAccountCode();
            
            //========================NotFoundException======================
            MainAccount l_mainAccount = 
                l_accMgr.getMainAccount(
                    l_lngInstitutionId,
                    l_strBranchCode,
                    l_strMainAccountCode);     
          
            //�P.�U�jgetSubAccount(�⏕�����^�C�v : SubAccountTypeEnum)
            //�⏕�����I�u�W�F�N�g���擾����B
            //[����] �⏕�����^�C�v�F 1�i�a��������j
            //================NotFoundException====================  
            SubAccount l_subAccount = 
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            //�P.�V�jcreateNewOrderId( )            
            //�V�K����ID���擾����B
            long l_lngNewOrderId = l_AioOrderManager.createNewOrderId();
            log.debug("�V�K����ID���擾���� ======== " + l_lngNewOrderId);
            
            //�P.�W�jSONAR���o���X�V�C���^�Z�v�^(���o���������e)
            //SONAR���o���X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B
            //[����]���o���������e�F ���o���������e�I�u�W�F�N�g
            WEB3AioSonarCashTransUpdateInterceptor 
                l_aioSonarCashTransUpdateInterceptor = 
                    new WEB3AioSonarCashTransUpdateInterceptor(
                        l_aioNewOrderSpec);
            
            //�P.�X�j(*) �ȉ��̂Ƃ���ɃC���^�Z�v�^�̃v���p�e�B���Z�b�g����B
            //�C���^�Z�v�^.��n�� = ���o���������e.�U�֗\���        
            l_aioSonarCashTransUpdateInterceptor.setDeliveryDate(
                l_aioNewOrderSpec.getEstimatedTransferDate());  
            
            //�C���^�Z�v�^.�󒍓��� = ���o��Params.�^�C���X�^���v
            l_aioSonarCashTransUpdateInterceptor.setReceivedDateTime(
                WEB3DateUtility.getDate(
                    l_hostCashTransferParams.getCreatedTimestamp(), "yyyyMMddHHmmss"));     
            
            //�P.�P�O�jsetThreadLocalPersistenceEventInterceptor(
            //SONAR���o���X�V�C���^�Z�v�^ : AioOrderManagerPersistenceEventInterceptor)
            //[����] SONAR���o���X�V�C���^�Z�v�^�F 
             //SONAR���o���X�V�C���^�Z�v�^�I�u�W�F�N�g
            l_AioOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_aioSonarCashTransUpdateInterceptor);
            
            //�P.�P�P�jsubmitNewOrder(SubAccount, ProductTypeEnum, NewOrderSpec,
            // long, �_���r���[::java::lang::String, boolean)
            //�����o�^�������s���B
            //[����]
            //�⏕�����F �⏕�����I�u�W�F�N�g
            //���i�^�C�v�F 5�i�����j
            //�������e�F ���o���������e�I�u�W�F�N�g
            //����ID�F createNewOrderId()�̖߂�l
            //�p�X���[�h�F �ڋq.getTradingPassword()�̖߂�l��WEB3Crypt.decrypt()�ŕ�����������
            //isSkip�����R���F true
            
            //������̈Í����ƕ������s���N���X
            WEB3Crypt l_webCrypt = new WEB3Crypt();
            OrderSubmissionResult l_submitNewOrderResult =
                l_AioOrderManager.submitNewOrder(
                    l_subAccount,
                    ProductTypeEnum.CASH,
                    l_aioNewOrderSpec,
                    l_lngNewOrderId,
                    l_webCrypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);
                    
            if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
            {
                log.debug("�����o�^�������s�� Error" +
                    l_submitNewOrderResult.getProcessingResult().getErrorInfo());
                throw new WEB3SystemLayerException(
                    l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                    STR_METHOD_NAME);
            }           
            
            //�P.�P �Q�j return
            //����ID��ԋp��
            log.exiting(STR_METHOD_NAME);  
            return l_lngNewOrderId;
        }
        catch (NotFoundException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);  
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
    }
}
@
