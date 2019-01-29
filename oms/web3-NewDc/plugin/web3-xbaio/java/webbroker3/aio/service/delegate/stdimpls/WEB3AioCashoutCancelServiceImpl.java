head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o������T�[�r�XImpl(WEB3AioCashoutCancelServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 ���� (���u) �V�K�쐬
                   2004/10/25 ���z (���u) ���r���[
                   2004/12/09 ���E (���u) �c�Ή�
Revesion History : 2010/02/02 ���g (���u)���f��No.1261
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import webbroker3.aio.WEB3AioCashoutCancelUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AioCashoutCancelCompleteRequest;
import webbroker3.aio.message.WEB3AioCashoutCancelCompleteResponse;
import webbroker3.aio.message.WEB3AioCashoutCancelConfirmRequest;
import webbroker3.aio.message.WEB3AioCashoutCancelConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�o������T�[�r�XImpl)<BR>
 * �o������T�[�r�X�����N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3AioCashoutCancelServiceImpl extends WEB3ClientRequestService 
    implements WEB3AioCashoutCancelService 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutCancelServiceImpl.class);
    
    /**
     * �o������T�[�r�X���������{����B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��validate����()�A<BR>
     * �܂���submit����()���\�b�h���R�[������B <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F73EFC00DC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AioCashoutCancelConfirmRequest)
        {
            //validate����()���\�b�h
            l_response =
                this.validateOrder(
                    (WEB3AioCashoutCancelConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AioCashoutCancelCompleteRequest)
        {
            //submit����()���\�b�h
            l_response =
                this.submitOrder(
                    (WEB3AioCashoutCancelCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                    "���N�G�X�g�f�[�^��"
                    + " WEB3AioCashoutCancelConfirmRequest "
                    + " �� WEB3AioCashoutCancelCompleteRequest�ȊO�ł���, but is " + l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * �o������̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�o������jvalidate�����v�Q�ƁB
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AioCashoutCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F73EFC00DE
     */
    protected WEB3AioCashoutCancelConfirmResponse validateOrder(
        WEB3AioCashoutCancelConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3AioCashoutCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�P.�P�j validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //�P.�Q�jget������( )
        //���������擾����B
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("���������擾���� ========" + l_datOrderBizDate);     
        
        //�P.�R�jget�⏕����(SubAccountTypeEnum)
        //�⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //�P.�S�j����������e�C���X�^���X�𐶐�����B
        //[����]
        //����ID�F ���N�G�X�g�f�[�^.����ID
        CancelOrderSpec l_cancelOrderSpec = 
            new CancelOrderSpec(Long.parseLong(l_request.orderId));
        
        //�P.�T�j��������`�F�b�N�����{����B 
        //�ȉ��̃`�F�b�N���s���B 
        //�@@�|��t���ԃ`�F�b�N 
        //�@@�|�V�X�e����~���`�F�b�N 
        //�@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j 
        //  �|�Y������������\���ǂ����̃`�F�b�N
        //[����]
        //�⏕�����F get�⏕����()�̖߂�l
        //����������e�F ����������e�I�u�W�F�N�g
        
        //============================FinApp==============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
        //���o�������}�l�[�W���N���X���擾����B
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        //�`�F�b�N���s��
        l_aioOrderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);

        //1.6�jgetOrderUnits(����ID : long)
        //�����P�ʃI�u�W�F�N�g���擾����B 
        //�����X�g��1�Ԗڂ̗v�f���擾����B 
        //[����] 
        //����ID�F ���N�G�X�g�f�[�^.����ID 
        //===================NotFoundException=================
        OrderUnit[] l_orderUnits = 
            l_aioOrderManager.getOrderUnits(
                Long.parseLong(l_request.orderId));
        if (l_orderUnits.length <= 0)
        {
          log.debug("�����P�ʃI�u�W�F�N�g���擾����");
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80005,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnits[0];         
        
        //1.7�jgetQuantity( )
        //���z���擾����B 
        String l_strQuantity = 
            WEB3StringTypeUtility.formatNumber(l_aioOrderUnit.getQuantity());
        log.debug("���z���擾����:" + l_strQuantity);    
        
        //1.8�jgetEstimatedTransferDate( )
        //�U���\������擾����B
        Date l_datEstimatedTransfer = l_aioOrderUnit.getEstimatedTransferDate();
        log.debug("�U���\������擾����:" + l_datEstimatedTransfer);    
        
        //1.9�jget�o���\�z�`�o�����͉�ʕ\���p�`(�⏕���� : �⏕����, ��n�� : Date) 
        //�U���\����̗]�͂��擾����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //��n���F getEstimatedTransferDate()�̖߂�l 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
     
        double l_dblCashoutPossiblePrice =  
            l_tPTradingPowerService.getPaymentTradingPowerAioCashoutInput(
                (WEB3GentradeSubAccount)l_subAccount, l_datEstimatedTransfer);

        //1.10�j createResponse( ) ���X�|���X�f�[�^�𐶐�����B
        WEB3AioCashoutCancelConfirmResponse 
            l_aioCashoutCancelConfirmResponse =
                (WEB3AioCashoutCancelConfirmResponse) l_request.createResponse();      
        
        //1.11�j(*) �v���p�e�B�Z�b�g
        //(*) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B

        //���X�|���X.����ID = ���N�G�X�g�f�[�^.����ID
        l_aioCashoutCancelConfirmResponse.orderId = l_request.orderId;  
        
        //���X�|���X.�o���]�� = ����]�̓T�[�r�X.get�o���\�z�̖߂�l 
        l_aioCashoutCancelConfirmResponse.paymentPower = 
            WEB3StringTypeUtility.formatNumber(l_dblCashoutPossiblePrice);
            
        //���X�|���X.�o�����z = �����P��.getQuantity()�̖߂�l
        l_aioCashoutCancelConfirmResponse.cashoutAmt = l_strQuantity;
        
        //���X�|���X.�U���\��� = �����P��.getEstimatedTransferDate()�̖߂�l
        l_aioCashoutCancelConfirmResponse.transScheduledDate = l_datEstimatedTransfer;
        
        //���X�|���X.�m�F�������� = ������ԊǗ�.get������()�̖߂�l
        l_aioCashoutCancelConfirmResponse.checkDate = l_datOrderBizDate;
                
        //���������o������m�F���X�|���X��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_aioCashoutCancelConfirmResponse;                
    }
    
    /**
     * (submit����)<BR>
     * �o������̓o�^���s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�o������jsubmit�����v�Q�ƁB
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AioCashoutCancelCompleteResponse
     * @@roseuid 40F73EFC00E0
     */
    protected WEB3AioCashoutCancelCompleteResponse submitOrder(
        WEB3AioCashoutCancelCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3AioCashoutCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�P.�P�jvalidate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //�P.�Q�jget������(Date)
        //�������̎擾�Ɗm�F���̔������Ƃ̃`�F�b�N���s���B 
        //[����] 
        //�m�F���������F ���N�G�X�g�f�[�^.�m�F�������� 
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                l_request.checkDate); 
        log.debug("�������̎擾" + l_datOrderBizDate);       
                
        //�P.�R�jget�⏕����(SubAccountTypeEnum)
        //�⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //�P.�S�jCancelOrderSpec(long)
        //����������e�C���X�^���X�𐶐�����B 
        //[����] 
        //����ID�F ���N�G�X�g�f�[�^.����ID     
        CancelOrderSpec l_cancelOrderSpec = 
            new CancelOrderSpec(Long.parseLong(l_request.orderId));
        
        //�P.�T�j validate�������(SubAccount, CancelOrderSpec)
        //��������`�F�b�N�����{����B 
        //�ȉ��̃`�F�b�N���s���B 
        //  �|��t���ԃ`�F�b�N 
        //  �|�V�X�e����~���`�F�b�N 
        //  �|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j 
        //  �|�Y������������\���ǂ����̃`�F�b�N 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //����������e�F ����������e�I�u�W�F�N�g 
        
        //============================FinApp==============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
        //���o�������}�l�[�W���N���X���擾����B
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
        
        //��������`�F�b�N
        OrderValidationResult l_orderValidationResult =
            l_aioOrderManager.validateCancelOrder(
                l_subAccount,
                l_cancelOrderSpec);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("validate������� Error " +
                l_orderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }      
            
        //�P.�U�j�o������X�V�C���^�Z�v�^( )
        //�C���^�Z�v�^�𐶐�����B 
        WEB3AioCashoutCancelUpdateInterceptor 
            l_aioCashoutCancelUpdateInterceptor =
                new WEB3AioCashoutCancelUpdateInterceptor();

        //�P.7�j getOrderUnits(����ID : long)
        //�����X�g��1�Ԗڂ̗v�f���擾����B 
        //[����] 
        //����ID�F ���N�G�X�g�f�[�^.����ID 
        OrderUnit[] l_orderUnits = 
            l_aioOrderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        if (l_orderUnits.length <= 0)
        {
          log.debug("Error in �����P�ʃI�u�W�F�N�g���擾����");
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80005,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnits[0]; 
        
        //�P.8 �jsetThreadLocalPersistenceEventInterceptor(
        //�o������X�V�C���^�Z�v�^ : AioOrderManagerPersistenceEventInterceptor)
        //�C���^�Z�v�^���Z�b�g����B 
        //[����] 
        //�o������X�V�C���^�Z�v�^�F�@@���������o������X�V�C���^�Z�v�^ 
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_aioCashoutCancelUpdateInterceptor);

        //�P.9�jsubmitCancelOrder(�⏕���� : SubAccount, 
        //����������e : CancelOrderSpec, �p�X���[�h : String, isSkip�����R�� : boolean)
        //��������s����B 
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //����������e�F�@@����������e�I�u�W�F�N�g 
        //�p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ� 
        //isSkip�����R���F�@@true 
        //OrderSubmissionResult
        OrderSubmissionResult l_submitCancelOrderResult =
            l_aioOrderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                true);
            
        if (l_submitCancelOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitCancelOrder" +
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  

        //1.10)���b�Z�[�W �]�͍Čv�Z(�⏕���� : �⏕����)
        //�A�C�e���̒�`
        //�]�͂̍X�V������B
        //[����] 
        //�⏕�����F�@@�⏕�����I�u�W�F�N�g 
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        
        l_service.reCalcTradingPower(l_gentradeSubAccount);
        
        // ReGet OrderUnit
        AioOrderUnit l_aioOrderUnitCanceled = null;
        try
        {
            l_aioOrderUnitCanceled = 
                (AioOrderUnit)l_aioOrderManager.getOrderUnit(l_aioOrderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("Error in �����P�ʃI�u�W�F�N�g���擾����", l_ex);
            throw new WEB3SystemLayerException( 
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage());
        }
        
        
       //�P.�P1�jgetQuantity( )
       //���z���擾����B   
       String l_strQuantity = 
           WEB3StringTypeUtility.formatNumber(l_aioOrderUnitCanceled.getQuantity()); 
       log.debug("���z���擾����" + l_strQuantity);         
      
       //�P.�P2�jgetEstimatedTransferDate( )
       //�U���\������擾����@@ 
       Date l_datEstimatedTransfer =
           l_aioOrderUnitCanceled.getEstimatedTransferDate();
       log.debug("�U���\������擾���� " + l_datEstimatedTransfer);              
       
       //�P.�P3�jgetDataSourceObject( ) 
       //�����P�ʂ̍s�I�u�W�F�N�g���擾����B  
       AioOrderUnitRow l_aioOrderUnitRow = 
           (AioOrderUnitRow) l_aioOrderUnitCanceled.getDataSourceObject();  
       
       //�P.�P4�jgetLastUpdatedTimestamp( )
       //�X�V���t���擾����
       Date l_datLastUpdatedTimestamp = l_aioOrderUnitRow.getLastUpdatedTimestamp();     
       log.debug("�X�V���t���擾���� " + l_datLastUpdatedTimestamp); 
       
       //�P.�P5�jcreateResponse( ) ���X�|���X�f�[�^�𐶐�����B       
       WEB3AioCashoutCancelCompleteResponse 
           l_aioCashoutCancelCompleteResponse = 
               (WEB3AioCashoutCancelCompleteResponse) l_request.createResponse();   
      
      //�j�P.�P6�j(*) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
      
      //���X�|���X.�o�����z = �����P��.getQuantity()�̖߂�l
      l_aioCashoutCancelCompleteResponse.cashoutAmt = l_strQuantity;
      
      //���X�|���X.�U���\��� = �����P��.getEstimatedTransferDate()�̖߂�l
      l_aioCashoutCancelCompleteResponse.transScheduledDate = 
          l_datEstimatedTransfer;
           
      //���X�|���X.�X�V���� = �����P��Params.getLastUpdateedTimestamp()�̖߂�l
      l_aioCashoutCancelCompleteResponse.lastUpdatedTimestamp = 
          l_datLastUpdatedTimestamp;
           
      //���X�|���X.����ID = ���N�G�X�g�f�[�^.����ID     
      l_aioCashoutCancelCompleteResponse.orderId = l_request.orderId;
      log.debug("���N�G�X�g�f�[�^.����ID " + l_aioCashoutCancelCompleteResponse.orderId );
      
      //���������o������������X�|���X��Ԃ��B
      log.exiting(STR_METHOD_NAME);
      return l_aioCashoutCancelCompleteResponse;
    }
}@
