head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���T�[�r�XImpl(WEB3AioCashoutServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 ���� (���u) �V�K�쐬
Revesion History : 2004/10/25 ���E(���u) ���r���[
Revesion History : 2004/12/09 ���E (���u) �c�Ή�
Revesion History : 2007/03/16 �����q (���u) ���f��No.716
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import webbroker3.aio.WEB3AioCashTransOrderUpdateInterceptor;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AioCashoutCompleteRequest;
import webbroker3.aio.message.WEB3AioCashoutCompleteResponse;
import webbroker3.aio.message.WEB3AioCashoutConfirmRequest;
import webbroker3.aio.message.WEB3AioCashoutConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BankAccountRegiDef;
import webbroker3.common.define.WEB3BankCodeDef;
import webbroker3.common.define.WEB3FinTransferDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTransferedFinInstitution;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.gentrade.data.TransferedFinInstitutionDao;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o���\���T�[�r�XImpl)<BR>
 * �o���\���T�[�r�X�����N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3AioCashoutServiceImpl extends WEB3ClientRequestService 
    implements WEB3AioCashoutService 
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutServiceImpl.class);  
    
    /**
     * �o���\���T�[�r�X���������{����B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��validate����()�A<BR>
     * �܂���submit����()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40EBEF0F0148
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AioCashoutConfirmRequest)
        {
            //validate����()���\�b�h
            l_response =
                this.validateOrder(
                    (WEB3AioCashoutConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AioCashoutCompleteRequest)
        {
            //submit����()���\�b�h
            l_response =
                this.submitOrder(
                    (WEB3AioCashoutCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                    "���N�G�X�g�f�[�^��"
                    + " WEB3AioCashoutConfirmRequest "
                    + " �� WEB3AioCashoutCompleteRequest�ȊO�ł���, but is " + l_request.getClass().getName());
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
     * �o���\���̔����R�����s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�o���\���jvalidate�����v�Q�ƁB <BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(���o���T�[�r�X���f��) / �o���\�� �v(�o���\���jvalidate���� )<BR>
     * �@@�@@:  1.2.get������<BR>   
     *     ������ > ���N�G�X�g�f�[�^.�U���\��� �̏ꍇ�A��O���X���[����B<BR><BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00773<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u (���o���T�[�r�X���f��) / �o���\���v (�o���\���jvalidate����)<BR>
     * �@@�@@:  1.5.get�U����o�^�敪( )<BR>   
     *     �߂�l = 0�i���o�^�j�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00774<BR>
     * <BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AioCashoutConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40EBEF5D0010
     */
    protected WEB3AioCashoutConfirmResponse validateOrder(
        WEB3AioCashoutConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3AioCashoutConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1�jvalidate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //1.2�jget������( )
        //���������擾����B
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate(); 

        //1.3�jget�⏕����(SubAccountTypeEnum)
        //�⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);            
        
		//==================FinApp==============================
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
		//���o�������}�l�[�W���N���X���擾����B
		WEB3AioOrderManager l_aioOrderManager = 
			(WEB3AioOrderManager) l_finApp.getTradingModule(
				ProductTypeEnum.AIO).getOrderManager();

		//1.4�jget���ߐU����(SubAccount, Date)
		//[����]
		//�⏕�����F get�⏕����()�̖߂�l
		//�������F get������()�̖߂�l
		//���ߐU���� > ���N�G�X�g�f�[�^.�U���\��� �̏ꍇ�A��O���X���[����B
		Date l_datClosestTransfer = 
			l_aioOrderManager.getClosestTransferDate(
				l_subAccount, l_datOrderBizDate);
		log.debug("���߂̐U�������擾���� = " + l_datClosestTransfer);        

		if (WEB3DateUtility.compareToDay(l_datClosestTransfer, l_request.transScheduledDate) > 0)
		{
			log.debug("���ߐU���� > ���N�G�X�g�f�[�^.�U���\����G���[�B");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00773,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"���ߐU����[" + l_datClosestTransfer + "] > ���N�G�X�g�f�[�^.�U���\���[" 
				+ l_request.transScheduledDate + "]");        
		}            

        //1.5�jgetMainAccount( )
        //�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_gentradeMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();          
        
        //is�~�ݐU����i��s�����j�o�^( )
        //���Y�ڋq���A�U����i��s�����j�ɉ~�ݓo�^���Ă��邩�ǂ����𔻒肷��B
        //�߂�l = false(���o�^)�̏ꍇ���O���X���[����B
        boolean l_blnIsJapaneseCurrencyBankAccountRegi =
            l_gentradeMainAccount.isJapaneseCurrencyBankAccountRegi();

        if (!l_blnIsJapaneseCurrencyBankAccountRegi)
        {
            log.debug("�o���������J�݃G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00774,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�U����o�^�敪= 0�i���o�^�j");            
        }           
        
        //1.7�j�㗝���͎҃I�u�W�F�N�g���擾����B
        //=================WEB3SystemLayerException=======================
        Trader l_trader =  this.getTrader();
       
        //1.8�j���o���p�̏��iID���擾����B 
        //[����] 
        // �،���ЁF �⏕����.getInstitution()�̖߂�l 
        
        //���o���p�̏��iID���擾����
        long l_lngProductId = 
            l_aioOrderManager.getProductId(l_subAccount.getInstitution());
        log.debug("���o���p�̏��iID���擾����:" + l_lngProductId);        
        
        //1.9�j���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum, 
        //long, double, String, Date, String, long)(���o��������
        //�o���������e�C���X�^���X�𐶐�����B 
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        //������ʁF 1001�i�o�������j 
        //�U�փ^�C�v�F 2�i�o���j 
        //���iID�F get���iID()�̖߂�l 
        //���z�F ���N�G�X�g�f�[�^.�o�����z 
        //�L�q�F null 
        //�U�֗\����F ���N�G�X�g�f�[�^.�U�֗\��� 
        //���ϋ@@��ID�F null 
        //����ID�F null 
        
        //���o���������e�C���X�^���X�𐶐�����
        WEB3AioNewOrderSpec l_aioNewOrderSpec =
            new WEB3AioNewOrderSpec(
                l_trader, 
                OrderTypeEnum.CASH_OUT,
                AssetTransferTypeEnum.CASH_OUT, 
                l_lngProductId, 
                Double.parseDouble(l_request.cashoutAmt), 
                null,
                l_request.transScheduledDate,
                null,
                null);   
                        
        //1.10�jvalidate�V�K����(SubAccount, ProductTypeEnum, ���o���������e)
        //�������e�̃`�F�b�N���s���B 
        //- �����n���̒����`�F�b�N 
        //- �o�����z�͈̓`�F�b�N 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //���i�^�C�v�F 5�i�����j 
        //���o���������e�F ���o���������e�I�u�W�F�N�g 
        NewOrderValidationResult l_newOrderValidationResult =
            l_aioOrderManager.validateNewOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_aioNewOrderSpec);
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�������e�̃`�F�b�N���s�� Error " +
                l_newOrderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        //�V�K���������h�c        
        long l_lngNewOrderId = l_newOrderValidationResult.getNewOrderId(); 
        log.debug("�V�K���������h�c" + l_lngNewOrderId); 
            
        //1.11 ���o�������X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
        //[����] 
        //���o���������e�F ���o���������e 
        WEB3AioCashTransOrderUpdateInterceptor l_orderUpdateInterceptor =
            new WEB3AioCashTransOrderUpdateInterceptor(l_aioNewOrderSpec);
        
        //1.12 (*1)�v���p�e�B�Z�b�g 
        Date l_datOrderBizTomDate = new WEB3GentradeBizDate(
            new Timestamp(l_datOrderBizDate.getTime())).roll(1);
        
        if (WEB3DateUtility.compareToDay(
                l_datOrderBizDate, l_aioNewOrderSpec.getEstimatedTransferDate()) == 0 
            || WEB3DateUtility.compareToDay(
                l_datOrderBizTomDate, l_aioNewOrderSpec.getEstimatedTransferDate()) == 0 )
        {
            l_orderUpdateInterceptor.setBizDate(l_datOrderBizDate);
        }
        else
        {
            Date l_datTransferTomDate = new WEB3GentradeBizDate(
                new Timestamp(l_aioNewOrderSpec.getEstimatedTransferDate().getTime())).roll(1);
            l_orderUpdateInterceptor.setBizDate(l_datTransferTomDate);
        }
        l_orderUpdateInterceptor.setDeliveryDate(l_aioNewOrderSpec.getEstimatedTransferDate());
        
        WEB3AioCashTransOrderUpdateInterceptor[] l_orderUpdateInterceptors = 
            {l_orderUpdateInterceptor};
    
        //�������e�̔z��
        WEB3AioNewOrderSpec[] l_aioNewOrderSpecs = {l_aioNewOrderSpec}; 
        
        //1.13 �]�͂̃`�F�b�N���s���B 
        //[����] 
        //�⏕�����F�@@�⏕�����I�u�W�F�N�g 
        //�������e�C���^�Z�v�^�F ���o�������X�V�C���^�Z�v�^�̔z�� 
        //�������e�F ���o���������e�̔z�� 
        //������ʁF 1001�i�o�������j 
        //�]�͍X�V�t���O�F false 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_powerResult =      
            l_tPTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount, 
                l_orderUpdateInterceptors, 
                l_aioNewOrderSpecs, 
                OrderTypeEnum.CASH_OUT, 
                false);

        //�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[��
        if (l_powerResult.isResultFlg() == false)
        {
            log.debug("����]�̓`�F�b�N�G���[�B"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����]�̓`�F�b�N�G���[�B");
        }
        
        //1.14�jget�U������Z�@@��( )
        //�ڋq�̐U������Z�@@�փI�u�W�F�N�g���擾����B 
        WEB3GentradeTransferedFinInstitution 
            l_gentradeTransferedFinInstitution =
                l_gentradeMainAccount.getTransferedFinInstitution();
        
        //1.15�jget��s�R�[�h( )
        //��s�R�[�h���擾����B
        String l_strFinInstitutionCode = 
            l_gentradeTransferedFinInstitution.getFinInstitutionCode();
            
        //1.16�jget��s��( )
        //��s�����擾����B 
        String l_strFinInstitutionName =
            l_gentradeTransferedFinInstitution.getFinInstitutionName();
        
        //1.17) get��s��() == null�̏ꍇ
		if ( l_strFinInstitutionName == null)
		{
			TransferedFinInstitutionRow l_transferedFinInstitutionRow = null;
			try
			{
				//�U������Z�@@�փe�[�u�����ȉ��̏����Ō�������
				//[�擾����] 
				// �،���ЃR�[�h
				// ���X�R�[�h
				// �ڋq�R�[�h
				// �w��敪
				l_transferedFinInstitutionRow = TransferedFinInstitutionDao.findRowByPk(
					l_subAccount.getInstitution().getInstitutionCode(),
					l_gentradeMainAccount.getBranch().getBranchCode(),
					l_gentradeMainAccount.getAccountCode(),
					"A" );
			}
			catch (DataFindException l_ex)
			{
				//��O���X���[
				log.debug("�Y������U������Z�@@�ւ�����܂���B");
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01937,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			catch (DataException l_ex)
			{
				//��O���X���[
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			
			if (WEB3FinTransferDivDef.BANK_TRANSFER.equals(l_transferedFinInstitutionRow.getTransferDiv()))
			{
                //�U������Z�@@��.�U�֋敪="1(��s�j"�̏ꍇ�A��O���X���[����B
				log.debug("�U�֋敪=1(��s)�̏ꍇ�A��s���ɒl������܂���B");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01937,
					this.getClass().getName() + "." + STR_METHOD_NAME);   
				
			}
		}
		        
        //==========remain zhou-yong NO.1 begin ===========
        
        //1.18) get�x�X�R�[�h( )(�U������Z�@@��::get�x�X�R�[�h)
        //�A�C�e���̒�`
        //�x�X�R�[�h���擾����B 
        String l_strFinBranchCode =
            l_gentradeTransferedFinInstitution.getFinBranchCode();   

        //==========remain zhou-yong NO.1 end ===========
        
        //1.19�jget�x�X��( )
        //�x�X�����擾����B 
        String l_strFinBranchName =
            l_gentradeTransferedFinInstitution.getFinBranchName();        
 
        //1.20�j get�a���敪( )
        //�a���敪���擾����B   
        String l_strFinSaveDiv =
            l_gentradeTransferedFinInstitution.getFinSaveDiv();     
 
        //1.21�jget�����ԍ�( )
        //�����ԍ����擾����B  
        String l_strFinAccountNo =
            l_gentradeTransferedFinInstitution.getFinAccountNo();
        
        //1.22�j createResponse( ) ���X�|���X�f�[�^�𐶐�����B 
        WEB3AioCashoutConfirmResponse l_aioCashoutConfirmResponse = 
            (WEB3AioCashoutConfirmResponse) l_request.createResponse();            
        
        //1.23�j(*) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B

        //���X�|���X.�U����o�^�敪 = "1"�i�o�^�ρj
        l_aioCashoutConfirmResponse.transRegistDiv = WEB3BankAccountRegiDef.ALREADY_REGISTER;
        
        //���X�|���X.��s�R�[�h = �U������Z�@@��.get��s�R�[�h()�̖߂�l
        l_aioCashoutConfirmResponse.bankCode = l_strFinInstitutionCode;
        
        //���X�|���X.��s�� = �U������Z�@@��.get��s��()�̖߂�l
        l_aioCashoutConfirmResponse.bankName = l_strFinInstitutionName;   
        
        //���X�|���X.�x�X�� = �U������Z�@@��.get�x�X��()�̖߂�l
        l_aioCashoutConfirmResponse.branchBankName = l_strFinBranchName;
        
        //���X�|���X.�a���敪 = �U������Z�@@��.get�a���敪()�̖߂�l
        l_aioCashoutConfirmResponse.depositDiv = l_strFinSaveDiv;
        
        //==========remain zhou-yong NO.2 begin ===========
        
        //���X�|���X.�����ԍ� = �i�ȉ��̂Ƃ���j
        //�P�j�E��s�R�[�h="9900"�i�X���j�̏ꍇ�A�U������Z�@@��.get�x�X�R�[�h()�̖߂�l + "-" + get�����ԍ�( )�̖߂�l  ���Z�b�g
        //    �E��s�R�[�h!="9900"�i�X���j�̏ꍇ�Aget�����ԍ�( )�̖߂�l  ���Z�b�g
		if(WEB3BankCodeDef.POSTAL_SAVINGS.equals(l_strFinInstitutionCode))
		{
			l_aioCashoutConfirmResponse.accountID = l_strFinBranchCode + "-" + l_strFinAccountNo;
		}
		else 
		{
			l_aioCashoutConfirmResponse.accountID = l_strFinAccountNo;
		}
        
        //==========remain zhou-yong NO.2 end ===========
        
        //���X�|���X.�o�����z = ���N�G�X�g�f�[�^.�o�����z
        l_aioCashoutConfirmResponse.cashoutAmt = l_request.cashoutAmt;
        
        //���X�|���X.�U���\��� = ���N�G�X�g�f�[�^.�U���\���
        l_aioCashoutConfirmResponse.transScheduledDate = 
            l_request.transScheduledDate;
        
        //���X�|���X.�m�F�������� = ������ԊǗ�.get������()�̖߂�l
        l_aioCashoutConfirmResponse.checkDate = l_datOrderBizDate;
        
        //���X�|���X.����ID = �ivalidate�V�K�����̖߂�l�j.getNewOrderId()�̖߂�l 
        l_aioCashoutConfirmResponse.checkOrderID = l_lngNewOrderId;
        
        
        log.exiting(STR_METHOD_NAME); 
        return l_aioCashoutConfirmResponse;
    }
    
    /**
     * (submit����)<BR>
     * �o���\���̓o�^���s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�o���\���jsubmit�����v�Q�ƁB <BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(���o���T�[�r�X���f��) / �o���\�� �v<BR>
     * �i�o���\���jsubmit���� )<BR>
     * �@@�@@:  1.5.get�U����o�^�敪( )<BR>   
     *     �߂�l = 0�i���o�^�j�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00774<BR>
     * <BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AioCashoutCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40EBEF6B0177
     */
    protected WEB3AioCashoutCompleteResponse submitOrder(
        WEB3AioCashoutCompleteRequest l_request)  throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3AioCashoutCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1�jvalidate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //1.2�jget������(Date)
        //���������擾����B 
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate(); 
        log.debug("�������̎擾 " + l_datOrderBizDate);    
        
        //1.3�jget�⏕����(SubAccountTypeEnum)
        //�⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);       

		//==================FinApp==============================
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
		//���o�������}�l�[�W���N���X���擾����B
		WEB3AioOrderManager l_aioOrderManager = 
			(WEB3AioOrderManager) l_finApp.getTradingModule(
				ProductTypeEnum.AIO).getOrderManager();

		//1.4�jget���ߐU����(SubAccount, Date)
		//[����]
		//�⏕�����F get�⏕����()�̖߂�l
		//�������F get������()�̖߂�l
		//���ߐU���� > ���N�G�X�g�f�[�^.�U���\��� �̏ꍇ�A��O���X���[����B
		Date l_datClosestTransfer = 
			l_aioOrderManager.getClosestTransferDate(
				l_subAccount, l_datOrderBizDate);
		log.debug("���߂̐U�������擾���� = " + l_datClosestTransfer);        

		if (WEB3DateUtility.compareToDay(l_datClosestTransfer, l_request.transScheduledDate) > 0)
		{
			log.debug("���ߐU���� > ���N�G�X�g�f�[�^.�U���\����G���[�B");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00773,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"���ߐU����[" + l_datClosestTransfer + "] > ���N�G�X�g�f�[�^.�U���\���[" 
				+ l_request.transScheduledDate + "]");        
		}            

        //1.5�jgetMainAccount( )
        //�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_gentradeMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();  

        //is�~�ݐU����i��s�����j�o�^( )
        //���Y�ڋq���A�U����i��s�����j�ɉ~�ݓo�^���Ă��邩�ǂ����𔻒肷��B
        boolean l_blnIsJapaneseCurrencyBankAccountRegi =
            l_gentradeMainAccount.isJapaneseCurrencyBankAccountRegi();
       
        if (!l_blnIsJapaneseCurrencyBankAccountRegi)
        {
            log.debug("�o���������J�݃G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00774,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�U����o�^�敪= 0�i���o�^�j");            
        }           

        //1.7�j�㗝���͎҃I�u�W�F�N�g���擾����B
        //=================WEB3SystemLayerException=======================
        Trader l_trader =  this.getTrader();
       
        //1.8�j���o���p�̏��iID���擾����B 
        //[����] 
        // �،���ЁF �⏕����.getInstitution()�̖߂�l 
        long l_lngProductId = 
            l_aioOrderManager.getProductId(l_subAccount.getInstitution());
        log.debug("���o���p�̏��iID���擾����" + l_lngProductId);
        
        //1.9�j���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum, 
        //long, double, String, Date, String, long)(���o���������e::���o���������e)
        //�o���������e�C���X�^���X�𐶐�����B 
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        //������ʁF 1001�i�o�������j 
        //�U�փ^�C�v�F 2�i�o���j 
        //���iID�F get���iID()�̖߂�l 
        //���z�F ���N�G�X�g�f�[�^.�o�����z 
        //�L�q�F null 
        //�U�֗\����F ���N�G�X�g�f�[�^.�U�֗\��� 
        //���ϋ@@��ID�F null 
        //����ID�F ���N�G�X�g�f�[�^.�m�F������ID 
        //���o���������e�C���X�^���X�𐶐�����
        WEB3AioNewOrderSpec l_aioNewOrderSpec =
            new WEB3AioNewOrderSpec(
                l_trader, 
                OrderTypeEnum.CASH_OUT,
                AssetTransferTypeEnum.CASH_OUT, 
                l_lngProductId, 
                Double.parseDouble(l_request.cashoutAmt), 
                null,
                l_request.transScheduledDate,
                null,
                new Long(l_request.checkOrderID));
        
        //1.10�jvalidate�V�K����(SubAccount, ProductTypeEnum, ���o���������e)
        //�������e�̃`�F�b�N���s���B 
        //- �����n���̒����`�F�b�N 
        //- �o�����z�͈̓`�F�b�N 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //���i�^�C�v�F 5�i�����j 
        //���o���������e�F ���o���������e�I�u�W�F�N�g 
        NewOrderValidationResult l_newOrderValidationResult =
            l_aioOrderManager.validateNewOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_aioNewOrderSpec);
                
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�������e�̃`�F�b�N���s�� Error " + 
                l_newOrderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        //�V�K���������h�c        
        long l_lngNewOrderId = l_newOrderValidationResult.getNewOrderId(); 
        log.debug("�V�K���������h�c" + l_lngNewOrderId);      

        //1.11�jget�V�K���ʃR�[�h
        // �V�K�̎��ʃR�[�h���擾����B
        WEB3HostReqOrderNumberManageService l_numberService =
            (WEB3HostReqOrderNumberManageService) Services.getService(
            WEB3HostReqOrderNumberManageService.class);
        //[����]
        //�،���ЃR�[�h�F �⏕��������擾�����،���ЃR�[�h
        //���X�R�[�h�F �⏕��������擾�������X�R�[�h
        //�����^�C�v�F 5�i�����j

      String l_strOrderRequestNumber = 
          l_numberService.getNewNumber(
              l_subAccount.getInstitution().getInstitutionCode(),
              l_subAccount.getMainAccount().getBranch().getBranchCode(),
              ProductTypeEnum.CASH);
          
      log.debug("�������ʔԍ�:" + l_strOrderRequestNumber);

        //1.12�j���b�Z�[�W ���o�������X�V�C���^�Z�v�^(���o���������e)
        //���o�������X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
        WEB3AioCashTransOrderUpdateInterceptor 
            l_aioCashTransOrderUpdateInterceptor = 
                new WEB3AioCashTransOrderUpdateInterceptor(l_aioNewOrderSpec);
        
        //1.13�j(*) �ȉ��̂Ƃ���ɁA�C���^�Z�v�^�̃v���p�e�B���Z�b�g����B
        //�C���^�Z�v�^.������ = �i�ȉ��̂Ƃ���j
        //get������()�̖߂�l = ���o���������e.�U�֗\��� or
        //get������()�̖߂�l�̗��c�Ɠ� = ���o���������e.�U�֗\��� �̏ꍇ�Aget������()�̖߂�l
        Date l_datEstimatedTransferDate = 
            l_aioNewOrderSpec.getEstimatedTransferDate();  //�U�֗\���
        
        Date l_datNextOrderBizDate = 
            new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime())).roll(1); // get������()�̖߂�l�̗��c�Ɠ�
        
        if ((WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datEstimatedTransferDate) == 0)
            ||(WEB3DateUtility.compareToDay(l_datNextOrderBizDate, l_datEstimatedTransferDate) == 0))
        {
            l_aioCashTransOrderUpdateInterceptor.setBizDate(l_datOrderBizDate);
        }
        //����ȊO�̏ꍇ�A���o���������e.�U�֗\����̑O�c�Ɠ�
        else
        {
            Date l_datretBizDate = 
                new WEB3GentradeBizDate(
                    new Timestamp(
                        l_datEstimatedTransferDate.getTime())).roll(-1);
            
            l_aioCashTransOrderUpdateInterceptor.setBizDate(l_datretBizDate);
        }
        
        //�C���^�Z�v�^.��n�� = ���o���������e.�U�֗\���
        l_aioCashTransOrderUpdateInterceptor.setDeliveryDate(
            l_datEstimatedTransferDate);
        
        //�C���^�Z�v�^.���ʃR�[�h = get�V�K���ʃR�[�h()�̖߂�l
        l_aioCashTransOrderUpdateInterceptor.setOrderRequestNumber(l_strOrderRequestNumber);
        
        //===========remain zhou-yong NO.3 begin========
        //1.14) validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[],
        //�������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
        //�A�C�e���̒�`
        //�]�͂̍X�V������B
        //[����] 
        //�⏕�����F�@@�⏕�����I�u�W�F�N�g 
        //�������e�C���^�Z�v�^�F ���o�������X�V�C���^�Z�v�^�̔z�� 
        //�������e�F ���o���������e�̔z�� 
        //������ʁF 1001�i�o�������j 
        //�]�͍X�V�t���O�F true
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;

        Object[] l_objCashoutCancelUpdate= {l_aioCashTransOrderUpdateInterceptor};
        Object[] l_objNewOrderSpec= {l_aioNewOrderSpec};
        
        WEB3TPTradingPowerResult l_tPTradingPowerResult = 
            l_service.validateTradingPower(l_gentradeSubAccount, l_objCashoutCancelUpdate, 
                l_objNewOrderSpec, OrderTypeEnum.CASH_OUT, true);
        
        //�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[����B
        if(!l_tPTradingPowerResult.isResultFlg())
        {
            log.debug("����]�̓`�F�b�N�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����]�͌���.����t���O == false");
            
        }
        
        //===========remain zhou-yong NO.3 end========
        
        //1.15�jsetThreadLocalPersistenceEventInterceptor(
        //���o�������X�V�C���^�Z�v�^ : AioOrderManagerPersistenceEventInterceptor)
        //�C���^�Z�v�^���Z�b�g����B 
        //[����] 
        //���o�������X�V�C���^�Z�v�^�F�@@�����������o�������X�V�C���^�Z�v�^ 
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_aioCashTransOrderUpdateInterceptor);
         
        //1.16�j�����o�^�������s���B 
        //[����] 
        //�⏕�����F�@@�⏕�����I�u�W�F�N�g 
        //���i�^�C�v�F�@@5�i�����j 
        //���o���������e�F�@@���o���������e�I�u�W�F�N�g 
        //�����h�c�F�@@�ivalidate�V�K�����̖߂�l�j.getNewOrderId()�̖߂�l 
        //�p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ� 
        //isSkip�����R���F�@@true 
        OrderSubmissionResult l_submitNewOrderResult =
            l_aioOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_aioNewOrderSpec,
                l_lngNewOrderId,
                l_request.password,
                true);
                
        if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����o�^�������s�� Error" +  
                l_submitNewOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }        
        
        //1.17�jgetOrderUnits(����ID : long)
        //�����P�ʃI�u�W�F�N�g���擾����B 
        //�����X�g��1�Ԗڂ̗v�f�̂��̂��擾����B 
        //[����] 
        //�����h�c�F�@@�ivalidate�V�K�����̖߂�l�j.getNewOrderId()�̖߂�l 
        OrderUnit[] l_orderUnits = l_aioOrderManager.getOrderUnits(l_lngNewOrderId);
        if (l_orderUnits.length <= 0)
        {
          log.debug("Error in �����P�ʃI�u�W�F�N�g���擾����");
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80005,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnits[0];
        AioOrderUnitRow l_aioOrderUnitRow = 
            (AioOrderUnitRow) l_aioOrderUnit.getDataSourceObject();
                
        //1.18�jget�U������Z�@@��( )
        //�ڋq�̐U������Z�@@�փI�u�W�F�N�g���擾����B 
        WEB3GentradeTransferedFinInstitution 
            l_gentradeTransferedFinInstitution =
                l_gentradeMainAccount.getTransferedFinInstitution();      
                   
        //1.19�jget��s�R�[�h( )
        //��s�R�[�h���擾����B 
        String l_strFinInstitutionCode = 
            l_gentradeTransferedFinInstitution.getFinInstitutionCode();        
         
        //1.20�jget��s��( )
        //��s�����擾����B 
        String l_strFinInstitutionName =
            l_gentradeTransferedFinInstitution.getFinInstitutionName();
            
        //1.21) get��s��() == null�̏ꍇ
		if ( l_strFinInstitutionName == null)
		{
			TransferedFinInstitutionRow l_transferedFinInstitutionRow = null;
			try
			{
				//�U������Z�@@�փe�[�u�����ȉ��̏����Ō�������
				//[�擾����] 
				// �،���ЃR�[�h
				// ���X�R�[�h
				// �ڋq�R�[�h
				// �w��敪
				l_transferedFinInstitutionRow = TransferedFinInstitutionDao.findRowByPk(
					l_subAccount.getInstitution().getInstitutionCode(),
					l_gentradeMainAccount.getBranch().getBranchCode(),
					l_gentradeMainAccount.getAccountCode(),
					"A" );
			}
			catch (DataFindException l_ex)
			{
				//��O���X���[
				log.debug("�Y������U������Z�@@�ւ�����܂���B");
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01937,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			catch (DataException l_ex)
			{
				//��O���X���[
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			
			if (WEB3FinTransferDivDef.BANK_TRANSFER.equals(l_transferedFinInstitutionRow.getTransferDiv()))
			{
				//�U������Z�@@��.�U�֋敪="1(��s�j"�̏ꍇ�A��O���X���[����B
				log.debug("�U�֋敪=1(��s)�̏ꍇ�A��s���ɒl������܂���B");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01937,
					this.getClass().getName() + "." + STR_METHOD_NAME);   
			}
		}    
        
        //=========remain zhou-yong NO.4 begin =============
        
        //1.22�jget�x�X�R�[�h( )(�U������Z�@@��::get�x�X�R�[�h)
        //�A�C�e���̒�`
        //�x�X�R�[�h���擾����B
        String l_strFinBranchCode =
            l_gentradeTransferedFinInstitution.getFinBranchCode();         
        
        //=========remain zhou-yong NO.4 end =============
        
        //1.23�jget�x�X��( )
        //�x�X�����擾����B 
        String l_strFinBranchName =
            l_gentradeTransferedFinInstitution.getFinBranchName();  
        
        //1.24�j get�a���敪( )
        //�a���敪���擾����B   
        String l_strFinSaveDiv =
            l_gentradeTransferedFinInstitution.getFinSaveDiv();            
        
        //1.25�jget�����ԍ�( )
        //�����ԍ����擾����B    
        String l_strFinAccountNo =
            l_gentradeTransferedFinInstitution.getFinAccountNo();
        
        //1.26�j���X�|���X�f�[�^�𐶐�����B
        WEB3AioCashoutCompleteResponse l_aioCashoutCompleteResponse =
            (WEB3AioCashoutCompleteResponse) l_request.createResponse();
         
        //1.27�j(*) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
         
        //���X�|���X.�U����o�^�敪 = "1"�i�o�^�ρj
        l_aioCashoutCompleteResponse.transRegistDiv = WEB3BankAccountRegiDef.ALREADY_REGISTER;

        //���X�|���X.��s�R�[�h = �U������Z�@@��.get��s�R�[�h()�̖߂�l
        l_aioCashoutCompleteResponse.bankCode = l_strFinInstitutionCode;
        
        //���X�|���X.��s�� = �U������Z�@@��.get��s��()�̖߂�l
        l_aioCashoutCompleteResponse.bankName = l_strFinInstitutionName;   
        
        //���X�|���X.�x�X�� = �U������Z�@@��.get�x�X��()�̖߂�l
        l_aioCashoutCompleteResponse.branchBankName = l_strFinBranchName;
        
        //���X�|���X.�a���敪 = �U������Z�@@��.get�a���敪()�̖߂�l
        l_aioCashoutCompleteResponse.depositDiv = l_strFinSaveDiv;
        
        //==========remain zhou-yong NO.5 begin ===========
        
        //���X�|���X.�����ԍ� = �i�ȉ��̂Ƃ���j
        //�P�j�E��s�R�[�h="9900"�i�X���j�̏ꍇ�A�U������Z�@@��.get�x�X�R�[�h()�̖߂�l + "-" + get�����ԍ�( )�̖߂�l  ���Z�b�g
        //    �E��s�R�[�h!="9900"�i�X���j�̏ꍇ�Aget�����ԍ�( )�̖߂�l ���Z�b�g
		if(WEB3BankCodeDef.POSTAL_SAVINGS.equals(l_strFinInstitutionCode))
		{
			l_aioCashoutCompleteResponse.accountID = l_strFinBranchCode + "-" + l_strFinAccountNo;
		}
		else 
		{
			l_aioCashoutCompleteResponse.accountID = l_strFinAccountNo;
		}
        
        //==========remain zhou-yong NO.5 end ===========
        
        //���X�|���X.�o�����z = ���N�G�X�g�f�[�^.�o�����z
        l_aioCashoutCompleteResponse.cashoutAmt = l_request.cashoutAmt;
        
        //���X�|���X.�U���\��� = ���N�G�X�g�f�[�^.�U���\���
        l_aioCashoutCompleteResponse.transScheduledDate = 
            l_request.transScheduledDate;
        
        //���X�|���X.�X�V���� = �����P��.�X�V���t
        l_aioCashoutCompleteResponse.lastUpdatedTimestamp = 
            l_aioOrderUnitRow.getLastUpdatedTimestamp();
        
        //���X�|���X.����ID = �ivalidate�V�K�����̖߂�l�j.getNewOrderId()�̖߂�l 
        //l_aioCashoutCompleteResponse.orderId = String.valueOf(l_lngNewOrderId);
        l_aioCashoutCompleteResponse.orderId =l_lngNewOrderId + "";
                
        log.exiting(STR_METHOD_NAME);
        return l_aioCashoutCompleteResponse;

    }
}
@
