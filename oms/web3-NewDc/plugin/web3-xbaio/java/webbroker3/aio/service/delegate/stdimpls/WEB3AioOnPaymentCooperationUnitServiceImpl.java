head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOnPaymentCooperationUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���A�gUnitServiceImpl (WEB3AioOnPaymentCooperationUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 ���r (���u) �V�K�쐬   
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioProductTypeOrderManagerReusableValidations;

import webbroker3.aio.WEB3AioCashTransOrderUpdateInterceptor;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOnPaymentCooperationCancelUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioProductTypeOrderManagerReusableValidations;
import webbroker3.aio.define.WEB3AioDescriptionDef;
import webbroker3.aio.service.delegate.WEB3AioOnPaymentCooperationUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o���A�gUnitServiceImpl) <BR>
 * �o���A�gUnitService�����N���X <BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor( <BR>
 *      TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 * <BR>
 * @@author ���r(���u)
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationUnitServiceImpl implements WEB3AioOnPaymentCooperationUnitService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOnPaymentCooperationUnitServiceImpl.class);  
    
    /**
     * @@roseuid 41E77F4A01D4
     */
    public WEB3AioOnPaymentCooperationUnitServiceImpl()
    {
    }

    /**
     * �o���A�g�������s���A�o�����O���X��DB�X�V���s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�o���A�g�j�o���A�g�v�Q�� <BR>
     * <BR>
     * @@param l_aioOrderUnits - �����P�ʃI�u�W�F�N�g[ ]
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C7B2080071
     */
    public void execute(AioOrderUnit[] l_aioOrderUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(AioOrderUnit[] l_aioOrderUnits)";
        log.entering(STR_METHOD_NAME);
        
        if (l_aioOrderUnits == null || l_aioOrderUnits.length == 0)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 get��������(AioOrderUnit[ ])
        //�����P�ʂ̃O���X���ʂ��v�Z����B
        Double l_dblOrderQuantity = getOrderQuantity(l_aioOrderUnits); 
        
        // ����ID
        long l_lngAccountId =  l_aioOrderUnits[0].getAccountId();
        // �⏕����ID 
        long l_lngSubAccountId = l_aioOrderUnits[0].getSubAccountId();
        
        //1.6 �⏕�������擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);       
        AccountManager l_accountManager = l_finApp.getAccountManager();        
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accountManager.getSubAccount(l_lngAccountId, l_lngSubAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.2 �o�����z�͈̔͂̃`�F�b�N���s���B
        //���o�������R���ʃ`�F�b�N�̃I�u�W�F�N�g���擾����B
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();    
        try
        {
			l_reusableValidations.validatePaymentAmount(
                l_subAccount, 
		        l_dblOrderQuantity.doubleValue());
		}
        catch (WEB3BaseException l_ex)
        {
            log.debug("�o�����z�͈̔͂̃`�F�b�N throw Exception", l_ex);
            return;
		} 
        
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)GtlUtils.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        //1.3 �V�K����ID���擾����B
        long l_lngNewOrderId = l_orderManager.createNewOrderId();
        
        //1.4 �ڋq�I�u�W�F�N�g���擾����B 
        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("error in l_accountManager.getMainAccount", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        
        // 1.5  ���o���������e�C���X�^���X�𐶐�����B 
        //[����]  
        // �㗝���͎ҁF null 
        // ������ʁF 1001�i�o�������j 
        // �U�փ^�C�v�F 2�i�o���j  
        // ���iID�F ����.�����P��[0].getProduct( ).getProductId( ) 
        // ���z�F get��������( )�̖߂�l  
        // �L�q�F null  
        // �U�֗\����F ����.�����P��[0].getEstimatedTransferDate( ) 
        // ���ϋ@@��ID�F null  
        // ����ID�F createNewOrderId( )�̖߂�l  

        long l_lngProductId = l_aioOrderUnits[0].getProduct().getProductId();
    
        WEB3AioNewOrderSpec l_aioNewOrderSpec = 
            new WEB3AioNewOrderSpec(
                null, 
                OrderTypeEnum.CASH_OUT, 
                AssetTransferTypeEnum.CASH_OUT, 
                l_lngProductId, 
                l_dblOrderQuantity.doubleValue(), 
                null, 
                l_aioOrderUnits[0].getEstimatedTransferDate(), 
                null, 
                new Long(l_lngNewOrderId));
        
		//1.7 get�V�K���ʃR�[�h(String, String, ProductTypeEnum)
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode() 
        //�����^�C�v�F 5�i�����j
        //(1)get �������ʃR�[�h�̔ԃT�[�r�X 
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        //(2)
        String l_strNewNumber = 
            l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.CASH);
        log.debug("get�V�K���ʃR�[�h() = " + l_strNewNumber);
        
        //1.8 ���o�������X�V�C���^�Z�v�^(���o���������e)
        WEB3AioCashTransOrderUpdateInterceptor l_orderUpdateInterceptor = 
            new WEB3AioCashTransOrderUpdateInterceptor(l_aioNewOrderSpec);
        
        AioOrderUnitRow l_aioOrderUnitRow = 
            (AioOrderUnitRow)(l_aioOrderUnits[0].getDataSourceObject());
        
        String l_strBizDate =  l_aioOrderUnitRow.getBizDate(); 
        Date l_datBizDate = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");  
        
        log.debug("�����P��.������ = " + 
            WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd")); 
        
        //1.9 set������(Date) 
        l_orderUpdateInterceptor.setBizDate(l_datBizDate);
        
        //1.10 set��n��(Date)
        l_orderUpdateInterceptor.setDeliveryDate(l_aioOrderUnits[0].getDeliveryDate());
        
        //1.11 set���ʃR�[�h(String)
        l_orderUpdateInterceptor.setOrderRequestNumber(l_strNewNumber);
        
        //1.12 setThreadLocalPersistenceEventInterceptor( ���o�������X�V�C���^�Z�v�^  )
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_orderUpdateInterceptor);
        
        // 1.13 �����o�^�������s���B 
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
                l_aioNewOrderSpec,
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
    
        //1.14 ����.�����P��[ ]�̗v�f����Loop�������s��
        for (int i = 0; i < l_aioOrderUnits.length; i++)
        {
            //1.14.1 ����������e�C���X�^���X�𐶐�����B 
            //[����] 
            //����ID�F �����P��[index].getOrderId 
            CancelOrderSpec l_cancelOrderSpec = 
                new CancelOrderSpec(l_aioOrderUnits[i].getOrderId());
           
            //1.14.2 �o���A�g����X�V�C���^�Z�v�^( )
            WEB3AioOnPaymentCooperationCancelUpdateInterceptor
                l_aioOnPaymentCooperationCancelUpdateInterceptor = 
                    new WEB3AioOnPaymentCooperationCancelUpdateInterceptor();
            
            //1.14.3 set�U�֋L�q(String)
            l_aioOnPaymentCooperationCancelUpdateInterceptor.setDescription(
                WEB3AioDescriptionDef.CASHOUT_GROSS);
            
            //1.14.4 setThreadLocalPersistenceEventInterceptor(�o���A�g����X�V�C���^�Z�v�^)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_aioOnPaymentCooperationCancelUpdateInterceptor);
            
            //1.14.5 submitCancelOrder(�⏕���� : SubAccount, ����������e 
            //: CancelOrderSpec, �p�X���[�h : String, isSkip�����R�� : boolean)
            //[����]  
            // �⏕�����F �⏕�����I�u�W�F�N�g 
            // ����������e�F�@@����������e�I�u�W�F�N�g 
            // �p�X���[�h�F�@@ �p�X���[�h�F �ڋq.getTradingPassword( )�̖߂�l��WEB3Crypt.decrypt( )�ŕ����������� 
            // isSkip�����R���F�@@true
            OrderSubmissionResult l_submitCancelOrderResult =            
            l_orderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_web3Crypt.decrypt(l_mainAccount.getTradingPassword()),
                true); 
            if (l_submitCancelOrderResult.getProcessingResult().isFailedResult())
            {
                log.debug("�������e����������s�ł���");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_submissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
                    
      }
      //1.15 �]�͍Čv�Z
      //[����]  
      // �⏕�����F �⏕�����I�u�W�F�N�g
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        WEB3GentradeSubAccount l_gentradeSubAccount =
            (WEB3GentradeSubAccount)l_subAccount;

        l_service.reCalcTradingPower(l_gentradeSubAccount);   
                
        log.exiting(STR_METHOD_NAME);        
  } 

    /**
     * (get��������) <BR>
     * �����P�ʂ̐��ʂ��O���X����B <BR>
     * <BR>
     * �P�j�����P�ʂ��Ƃ�Loop�����B  <BR>
     * �@@�@@����.�����P�ʂ��Ƃ�Loop�����ɂāA�ȉ��̒l���擾����B <BR>
     * <BR>
     * �@@�@@���v���� = ���v���� + �����P��[�C���f�b�N�X].��������  <BR>
     * <BR>
     * �Q)���v���ʂ�ԋp����B<BR>
     * 
     * @@param l_aioOrderUnits - �����P�ʃI�u�W�F�N�g[ ]
     * @@return Double
     * @@roseuid 41C7B2080090
     */
    protected Double getOrderQuantity(AioOrderUnit[] l_aioOrderUnits)        
    {
        final String STR_METHOD_NAME = 
            "getOrderQuantity(AioOrderUnit[ ] l_aioOrderUnits)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblSumQuantity = 0;
        
        //�P�j�����P�ʂ��Ƃ�Loop����
        // ����.�����P�ʂ��Ƃ�Loop�����ɂāA�ȉ��̒l���擾����
        for (int i = 0; i < l_aioOrderUnits.length; i++)
        {
            l_dblSumQuantity = l_dblSumQuantity + l_aioOrderUnits[i].getQuantity();            
        }
        log.exiting(STR_METHOD_NAME);        
        return new Double(l_dblSumQuantity);
        
    }    
}@
