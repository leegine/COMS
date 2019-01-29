head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradeOrderNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������������ʒm�T�[�r�X�����N���X(WEB3RuitoTradeOrderNotifyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 �m �X (���u) �V�K�쐬
                   2004/12/08 ��O�� (���u) �c�Ή�
Revesion History : 2007/12/21 �g�C�� (���u) �d�l�ύX No095
Revesion History : 2007/12/26 ���g (���u) �d�l�ύX No096
Revesion History : 2009/01/23 ���u�� (���u) �����̖�� No.002
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CourseDef;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoNewOrderSpec;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.WEB3RuitoPositionManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.WEB3RuitoTradedOrderNotifyDecisionInterceptor;
import webbroker3.xbruito.data.HostRuitoOrderNotifyParams;
import webbroker3.xbruito.marketadaptor.WEB3RuitoMarketRequestSubmitServiceImpl;
import webbroker3.xbruito.service.delegate.WEB3RuitoTradeOrderNotifyUnitService;
import webbroker3.xbruito.define.WEB3RuitoOrderDivTypeDef;


/**
 * �ݓ������ʒm�P���T�[�r�X�����N���X <BR>
 * <BR>
 * �����P�����Ƃ̗ݓ������ʒm���������{����B<BR>
 */
public class WEB3RuitoTradeOrderNotifyUnitServiceImpl 
    implements WEB3RuitoTradeOrderNotifyUnitService 
{

    final String STR_METHOD_NAME = "submitBuyOrder(WEB3RuitoBuyCompleteRequest l_request)";
    /**
    * ���O�o�̓��[�e�B���e�B�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoTradeOrderNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 40C41C390157
     */
    public WEB3RuitoTradeOrderNotifyUnitServiceImpl()
    {

    }

    /**
     * �ݓ����������ʒm�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�ݓ����������ʒm�jnotify���������ʒm�v�Q�ƁB<BR>
     * <BR>
     * �@@�|�g���ݓ������}�l�[�W��.submitNewOrder()�̖߂�l���� <BR>
     * �@@�@@�@@�g���ݓ������}�l�[�W��.submitNewOrder()�̖߂�l. <BR>
     *             getProcessingResult().isSuccessfulResult()==false <BR>
     * �@@        �@@�̏ꍇ�A��O���X���[����B <BR>
     *             class    : WEB3BusinessLayerException <BR>
     *             tag      : BUSINESS_ERROR_00191 <BR>
     * @@param l_orderNotifyCueParams - �ݓ������ʒm�L���[Params <BR>
     * @@param l_tradeOrderNotifyDecisionInterceptor - �ݓ����������ʒm�m��C���^�Z�v�^ 
     * <BR>
     * @@roseuid 408F4C930023
     */
    public void notifyTradeOrderNotify(
        HostRuitoOrderNotifyParams l_hostOrderNotifyParams,
        WEB3RuitoTradedOrderNotifyDecisionInterceptor 
        l_tradeOrderNotifyDecisionInterceptor)
        throws WEB3BaseException
    {        
        String STR_METHOD_NAME = 
            "HostRuitoOrderNotifyParams l_hostOrderNotifyParams," + 
            "WEB3RuitoTradedOrderNotifyDecisionInterceptor " + 
            "l_tradeOrderNotifyDecisionInterceptor)";
        
        if (l_hostOrderNotifyParams == null)
        { 
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "�Y���p�����[�^��Null�l�͐ݒ�ł��܂���B");
        }
        if (l_tradeOrderNotifyDecisionInterceptor == null)
        { 
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "�Y���p�����[�^��Null�l�͐ݒ�ł��܂���B");
        }
        log.entering(STR_METHOD_NAME);
        Institution l_institution = null;
        Branch l_branch = null;
         
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accManager = l_finApp.getAccountManager();


        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        //�ڋq�R�[�h�̎擾
        String l_lngAccountId = l_hostOrderNotifyParams.getAccountCode();
        log.debug("�ڋq�R�[�h�̎擾=" + l_lngAccountId);
        
        //NotFoundException
        try
        {       
            //1.1 �،���ЃI�u�W�F�N�g���擾����B
            l_institution = l_accMgr.getInstitution
                (l_hostOrderNotifyParams.getInstitutionCode());
            log.debug("�،���ЃI�u�W�F�N�g���擾=" + 
                l_institution.getInstitutionCode());
            
            //1.2 ���X�I�u�W�F�N�g���擾����B
            l_branch =  l_accMgr.getBranch(
                l_institution,l_hostOrderNotifyParams.getBranchCode());
            log.debug("���X�I�u�W�F�N�g���擾=" + 
                l_branch.getBranchCode());    
             
            //1.3 �ڋq�I�u�W�F�N�g���擾����B
            MainAccount l_mainAccount;
            l_mainAccount = l_accManager.getMainAccount
                (l_institution,l_branch,l_lngAccountId);
            log.debug("�ڋq�I�u�W�F�N�g=" + 
                l_mainAccount.getAccountId());     
            
            //1.4 �⏕�����I�u�W�F�N�g���擾����B 
            SubAccount l_subAccount;
            l_subAccount = l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            log.debug("�⏕�����I�u�W�F�N�g���擾=" + 
                l_subAccount.getSubAccountId());        
           
            //1.5 �ݓ��^�C�v���擾����B 
            RuitoTypeEnum l_ruitoTypeEnum = null;
            l_ruitoTypeEnum = this.getRuitoType(l_hostOrderNotifyParams);
            log.debug("�ݓ��^�C�v���擾����=" + 
                l_ruitoTypeEnum.toString());             
            
            //1.6 �ݓ����敪���擾����B
            String l_strRuitoSellDiv = this.getRuitoSellDivision(
                    l_hostOrderNotifyParams);
            log.debug("�ݓ����敪���擾����=" + 
                l_strRuitoSellDiv);            

            WEB3RuitoProductManager l_ruitoProductManager =
               (WEB3RuitoProductManager) l_finApp.getTradingModule(
                   ProductTypeEnum.RUITO).getProductManager();
            
            WEB3RuitoPositionManager l_ruitoPositionManager =
                (WEB3RuitoPositionManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getPositionManager();
            
            //1.7 �g���ݓ������I�u�W�F�N�g���擾����B 
            WEB3RuitoProduct l_ruitoProduct = (WEB3RuitoProduct)
                l_ruitoProductManager.getRuitoProductWithCoursePlan(
                    l_institution,
                    l_hostOrderNotifyParams.getCourse(),
                    l_hostOrderNotifyParams.getPlan());
            log.debug("�g���ݓ������I�u�W�F�N�g���擾����=" + 
                l_ruitoProduct.getProductCode());
            
            //1.8 �S����񎞁A�������ʂ��Z�o����B
            //�ݓ������ʒm�L���[Params.get�������()�̖߂�l���u1�F�S�����v�̏ꍇ�ɁA
            //get���\�c��()���R�[�����ĉ��\�c�����Z�o����B  
            double l_dblOrderQuantity = 0;
            if (WEB3RuitoOrderDivTypeDef.ALL_SELL.equals(
                    l_hostOrderNotifyParams.getOrderDiv()))
            {

                l_dblOrderQuantity = 
                    l_ruitoPositionManager.getSellPossibleBalance(
                        l_subAccount, l_ruitoProduct);
            }
            else
            {
                l_dblOrderQuantity = l_hostOrderNotifyParams.getAmount();
            }   
            
            //1.9 �������� 
            //����.�ݓ����������ʒm�m��C���^�Z�v�^��ݓ������}�l�[�W���ɐݒ肷��B       
            WEB3RuitoOrderManager l_ruitoOrderManager =   
                (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getOrderManager();   
                       
            l_ruitoOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_tradeOrderNotifyDecisionInterceptor);
                
            log.debug("����.�ݓ����������ʒm�m��C��" +
                 "�^�Z�v�^��ݓ������}�l�[�W���ɐݒ肷��B");    
            
            //1.10 �ݓ����������ʒm�m��C���^�Z�v�^�Ɏ�n����ݒ肷��B
            Timestamp l_deliveryDate = l_hostOrderNotifyParams.getDeliveryDate();
            l_tradeOrderNotifyDecisionInterceptor.setDeliveryDate(l_deliveryDate);
            log.debug("��n��="+
                l_tradeOrderNotifyDecisionInterceptor.getDeliveryDate());
            
            //1.11 �ݓ����������ʒm�m��C���^�Z�v�^�Ɏ󒍓�����ݒ肷��B
            Timestamp l_acceptedDate = l_hostOrderNotifyParams.getOrderDate();
            l_tradeOrderNotifyDecisionInterceptor.setAcceptedDate(l_acceptedDate);
            log.debug("�󒍓���="+
                l_tradeOrderNotifyDecisionInterceptor.getAcceptedDate());
            
            //1.12 �ݓ����������ʒm�m��C���^�Z�v�^�Ɏ��ʃR�[�h��ݒ肷��B
            String l_strRequestNumber = l_hostOrderNotifyParams.getOrderRequestNumber();
            l_tradeOrderNotifyDecisionInterceptor.setRequestNumber(
                l_strRequestNumber);
            log.debug("���ʃR�[�h="+
                l_tradeOrderNotifyDecisionInterceptor.getRequestNumber());
            
            //1.13 �ݓ����������ʒm�m��C���^�Z�v�^�Ɏ�n���@@��ݒ肷��
            String l_strPaymentMethod = 
                l_hostOrderNotifyParams.getPaymentMethod();
            l_tradeOrderNotifyDecisionInterceptor.setPaymentMethod(
                l_strPaymentMethod);
            log.debug("��n���@@"+
                l_tradeOrderNotifyDecisionInterceptor.getPaymentMethod());
            
            //1.14 �ݓ����������ʒm�m��C���^�Z�v�^�ɗݓ��^�C�v��ݒ肷��B
            l_tradeOrderNotifyDecisionInterceptor.setRuitoTypeEnum(
                l_ruitoTypeEnum);
            log.debug("�ݓ��^�C�v"+
                l_tradeOrderNotifyDecisionInterceptor.getRuitoTypeEnum());
            
            //1.15 �ݓ����������ʒm�m��C���^�Z�v�^�ɗݓ����敪��ݒ肷��B
            l_tradeOrderNotifyDecisionInterceptor.setRuitoSellDiv(
                l_strRuitoSellDiv);
            log.debug("���敪"+
                l_tradeOrderNotifyDecisionInterceptor.getRuitoSellDiv());
            
            //1.16 �ݓ����������ʒm�m��C���^�Z�v�^�ɒ����`���l����ݒ肷��B
            String l_strOrderChannel = l_hostOrderNotifyParams.getChannel();
            l_tradeOrderNotifyDecisionInterceptor.setOrderChannel(
                 l_strOrderChannel);
            log.debug("�����`���l��"+
                l_tradeOrderNotifyDecisionInterceptor.getOrderChannel());
            
            //1.17 �ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X�ɁA�s�ꑗ�M���������{����Ƃ����ݒ���s��
            TradingModule l_tradingModule = l_finApp.getTradingModule(
                ProductTypeEnum.RUITO);
              MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
              WEB3RuitoMarketRequestSubmitServiceImpl l_ruitoMarReq =
                  (WEB3RuitoMarketRequestSubmitServiceImpl)
                  l_marketAdapter.getMarketRequestSenderServce();        
            boolean isMarketSubmit = true;
            
            l_ruitoMarReq.setMarketSubmit(isMarketSubmit);                       
    
            // �������ʁF ����.�ݓ������ʒm�L���[Params.get�������()�̖߂�l <BR>
            
            //�������ʃ^�C�v
            QuantityTypeEnum l_quantityType = this.getOrderQuantityType(
                    l_hostOrderNotifyParams,l_ruitoProduct);
            // �ŋ敪
            TaxTypeEnum l_taxType = TaxTypeEnum.UNDEFINED;     
           
            //1.18 �g���ݓ��V�K�������e�𐶐�����B 
            WEB3RuitoNewOrderSpec l_ruitoNewOrderSpec;
            if(WEB3RuitoOrderDivTypeDef.BUY.equals(
                    l_hostOrderNotifyParams.getOrderDiv()))
            {
                l_ruitoNewOrderSpec =  
                    new WEB3RuitoNewOrderSpec(
                        null, //����
                        true, //is���t                                          
                        l_ruitoProduct.getProductCode(), //�����R�[�h                                          
                        l_dblOrderQuantity, //��������
                        l_quantityType,//�������ʃ^�C�v 
                        l_taxType); //�ŋ敪

                 log.debug("�g���ݓ��V�K�������e�𐶐�����B ");
                 log.debug("�������=" + l_hostOrderNotifyParams.getOrderDiv());
                 log.debug("����=null");
                 log.debug("is���t=true");
                 log.debug("�����R�[�h=" + l_ruitoProduct.getProductCode());
                 log.debug("��������=" + l_dblOrderQuantity);
                 log.debug("�������ʃ^�C�v=" + l_quantityType);
                 log.debug("�ŋ敪=" + l_taxType);                                                          
            }
            else
            {   
                l_ruitoNewOrderSpec = new WEB3RuitoNewOrderSpec(
                    null, //����
                    false, //is���t                                          
                    l_ruitoProduct.getProductCode(), //�����R�[�h                                          
                    l_dblOrderQuantity, //��������
                    l_quantityType,//�������ʃ^�C�v 
                    l_taxType); //�ŋ敪
                
                log.debug("�V�K�������e�̐���");
                log.debug("�������=" + l_hostOrderNotifyParams.getOrderDiv());
                log.debug("����=null");
                log.debug("is���t=false");
                log.debug("�����R�[�h=" + l_ruitoProduct.getProductCode());
                log.debug("��������=" + l_dblOrderQuantity);
                log.debug("�������ʃ^�C�v=" + l_quantityType);
                log.debug("�ŋ敪=" + l_taxType);                          
            }

            
            //1.19 �g���ݓ������}�l�[�W��.createNewOrderId()���R�[�����Ē���ID���擾����B  
            
            long l_lngOrderId = 0;
            l_lngOrderId = l_ruitoOrderManager.createNewOrderId();
            log.debug("����ID = " + l_lngOrderId);
            
            //1.20 ����p�X���[�h
            String l_strPassword = l_mainAccount.getTradingPassword();
            log.debug("����p�X���[�h = " + l_strPassword);            
            
            log.debug("subAccount.getInstitution()" + 
                l_subAccount.getInstitution().getInstitutionCode());
            log.debug("spec.getProductCode()" +
                l_ruitoNewOrderSpec.getProductCode());
            log.debug("spec.getIssueCode() = " +
                l_ruitoNewOrderSpec.getIssueCode());
            log.debug("spec.getMarketCode() = " +
                l_ruitoNewOrderSpec.getMarketCode());
            
            //1.21 �|������ԊǗ�.setTimestamp()���R�[������B 
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //1.22 �����f�[�^�쐬   
            OrderSubmissionResult l_orderSubmissionResult = null;
            WEB3Crypt l_crypt = new WEB3Crypt();
            l_orderSubmissionResult = l_ruitoOrderManager.submitNewOrder(
                        l_subAccount,
                        ProductTypeEnum.RUITO,
                        l_ruitoNewOrderSpec,
                        l_lngOrderId,
                        l_crypt.decrypt(l_strPassword), 
                        true);
                                                               
            //�g���ݓ������}�l�[�W��.submitNewOrder()�̖߂�l���� 
            boolean l_blnResult;             
            l_blnResult = 
                l_orderSubmissionResult.getProcessingResult().isSuccessfulResult();
            if(!l_blnResult)
            {
                log.debug("__�V�K�������s__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�V�K�������s");
            }
            
			// �|�]�͍Čv�Z�T�[�r�X���擾���A�]�͍Čv�Z()���R�[������B 
			// [�]�͍Čv�Z�ɓn���p�����^] 
			// �@@�⏕�����F�擾�����⏕�����I�u�W�F�N�g
			WEB3GentradeSubAccount l_genSubAccount =(WEB3GentradeSubAccount) l_subAccount;
			WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService = 
				(WEB3TPTradingPowerReCalcService) Services.getService(
					WEB3TPTradingPowerReCalcService.class);
			l_tpTradingPowerReCalcService.reCalcTradingPower(l_genSubAccount);     
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�g���ݓ�����������Ȃ�");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00250,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get�ݓ��^�C�v) <BR>
     * �ݓ������ʒm�L���[Params.get�R�[�X()�̖߂�l���A<BR>
     * �ݓ��^�C�v�iRuitoTypeEnum�j��p����B<BR>
     * <BR>
     * �P�j ����F�̃Z�b�g<BR>
     * �@@�R�[�X���fG�f�̏ꍇ�ARuitoTypeEnum.�h����F�h��ԋp����B<BR>
     * <BR>
     * �Q�j MMF�̃Z�b�g<BR>
     * �@@�R�[�X���fT�f�̏ꍇ�ARuitoTypeEnum.�hMMF�h��ԋp����B<BR>
     * @@param l_ruitoOrderNotifyCueParams - �ݓ������ʒm�L���[Params <BR>
     * @@return RuitoTypeEnum
     * @@roseuid 408F53D601A2
     */
    public RuitoTypeEnum getRuitoType(HostRuitoOrderNotifyParams l_ruitoOrderNotifyCueParams) throws WEB3BaseException
    {        
        String STR_METHOD_NAME = 
            "getRuitoSellDivision(HostRuitoOrderNotifyParams)";
        if (l_ruitoOrderNotifyCueParams == null)
        { 
            log.debug("����=null");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        String l_strRuitoCourse = l_ruitoOrderNotifyCueParams.getCourse();
        RuitoTypeEnum l_ruitoTypeEnum = null;
        
        if(WEB3CourseDef.MEDIUM_TERM_NATIONAL_BONDS_FUNDS.equals(
            l_strRuitoCourse))
        {
            l_ruitoTypeEnum = RuitoTypeEnum.CHUUKOKU_FUND;//����F�̃Z�b�g
        }
        
        if(WEB3CourseDef.MMF.equals(l_strRuitoCourse))
        {
            l_ruitoTypeEnum = RuitoTypeEnum.MMF;//MMF�̃Z�b�g
        }
        log.exiting(STR_METHOD_NAME); 
        return l_ruitoTypeEnum;
    }

    /**
     * (get�ݓ����敪) <BR>
     * �ݓ������ʒm�L���[Params.get�������()�̖߂�l���h���t�h�ȊO�̏ꍇ�A<BR>
     * �ݓ������ʒm�L���[Params�Dget�������()�̖߂�l�A<BR>
     * �ݓ������ʒm�L���[Params.get�w��敪()�̖߂�l���A<BR>
     * �ݓ����敪��ԋp����B<BR>
     * <BR>
     * <BR>
     * �P�j �S�����̃Z�b�g<BR>
     * <BR>
     * �@@�@@������ʁ��f�P�f�̏ꍇ�A �f�Q�f�i�S���w��j��ԋp����B<BR>
     * <BR>
     * �Q�j �����w��̃Z�b�g <BR>
     * <BR>
     * �@@�@@������ʁ��f�R�f�̏ꍇ�A<BR>
     *           �w��敪���f�P�f�i�����j�Ȃ�A�f�S�f�i�����w��j��ԋp����B<BR>
     * <BR>
     * �R�j ���z�w��̃Z�b�g<BR>
     * <BR>
     * �@@�@@������ʁ��f�R�f�̏ꍇ�A<BR>
     *           �w��敪���f�P�f�Ȃ�A�f�R�f�i���z�w��j��ԋp����B<BR>
     * @@param l_ruitoOrderNotifyCueParams - �ݓ������ʒm�L���[Params <BR>
     * @@return java.lang.String
     * @@roseuid 408F53D601A4
     */
    public String getRuitoSellDivision(HostRuitoOrderNotifyParams l_ruitoOrderNotifyCueParams) throws WEB3BaseException
    {        
       
        String STR_METHOD_NAME = 
            "getRuitoSellDivision(HostRuitoOrderNotifyParams)";
        if (l_ruitoOrderNotifyCueParams == null)
        { 
            log.debug("����=null");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        log.entering(STR_METHOD_NAME);
        String l_orderType = l_ruitoOrderNotifyCueParams.getOrderDiv();
        String l_orderDivision = l_ruitoOrderNotifyCueParams.getSpecifyDiv();
        String l_sellDiv = null;
        
        
        log.debug("l_orderType="+l_orderType);
        if(WEB3RuitoOrderDivTypeDef.ALL_SELL.equals
            (l_orderType))
        {
            
            l_sellDiv = WEB3SellDivDef.ALL_DESIGNATE;

        }

        if(WEB3RuitoOrderDivTypeDef.PARTIALLY_SELL.equals(
           l_orderType) &&  "1".equals(l_orderDivision))
        {
            l_sellDiv = WEB3SellDivDef.COUNT_DESIGNATE;
        }
        if(WEB3RuitoOrderDivTypeDef.PARTIALLY_SELL.equals(
           l_orderType) &&  !"1".equals(l_orderDivision))
        {
            l_sellDiv = WEB3SellDivDef.MONEY_DESIGNATE;
        } 
        log.exiting(STR_METHOD_NAME); 
        return l_sellDiv;        
    }

    /**
     * (get�������ʃ^�C�v) <BR>
     * �������ʃ^�C�v��Ԃ��B<BR>
     * <BR>
     * �P�j�@@����.�ݓ������ʒm�L���[Params.get�������()�̖߂�l���h1�F<BR>
     *             �S�����h�̏ꍇ�́A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�|����.�g���ݓ�����.get�w����@@�i���j()�̖߂�l���h0�F<BR>
     *             �I���w��h�̏ꍇ��QuantityTypeEnum.���z��Ԃ��B<BR>
     * �@@�|����.�g���ݓ�����.get�w����@@�i���j()�̖߂�l���h3�F<BR>
     *             ���z�w��h�̏ꍇ��QuantityTypeEnum.���z��Ԃ��B<BR>
     * �@@�|����.�g���ݓ�����.get�w����@@�i���j()�̖߂�l���h4�F<BR>
     *             �����w��h�̏ꍇ��QuantityTypeEnum.���ʂ�Ԃ��B<BR>
     * <BR>
     * �Q�j�@@����.�ݓ������ʒm�L���[Params.get�������()�̖߂�l���h1�F<BR>
     *             �S�����h�ł͂Ȃ��ꍇ�́A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�|����.�ݓ������ʒm�L���[Params.get�w����@@()�̖߂�l���h1�F<BR>
     *             �����h�̏ꍇ��QuantityTypeEnum.���ʂ��A<BR>
     *             ����ȊO��QuantityTypeEnum.���z��Ԃ��B<BR>
     * @@param l_ruitoOrderNotifyCueParams - �ݓ������ʒm�L���[Params <BR>
     * @@param l_expansionRuitoProduct - �g���ݓ����� <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum
     * @@roseuid 40A3366E00AF
     */
    public QuantityTypeEnum getOrderQuantityType(
        HostRuitoOrderNotifyParams l_ruitoOrderNotifyCueParams,
        WEB3RuitoProduct l_ruitoProduct) throws WEB3BaseException
    {        
        String STR_METHOD_NAME = 
            "getOrderQuantityType(HostRuitoOrderNotifyParams," +
            "WEB3RuitoProduct)";
        if (l_ruitoOrderNotifyCueParams == null)
        { 
            log.debug("����=null");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if (l_ruitoProduct == null)
        { 
            log.debug("����=null");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        log.entering(STR_METHOD_NAME);       
        String l_orType = l_ruitoOrderNotifyCueParams.getOrderDiv();
        String l_strPaymentMethod = l_ruitoProduct.getPaymentMethodSell();
        QuantityTypeEnum l_quantityType = null;
        
        //�P�j�@@����.�ݓ������ʒm�L���[Params.get�������()�̖߂�l���h
        //1�F�S�����h�̏ꍇ
        if (WEB3RuitoOrderDivTypeDef.ALL_SELL.equals(l_orType))
        {                     
            log.debug("l_orTypeA=" + l_orType);
            log.debug("l_strPaymentMethod=" + l_strPaymentMethod);
             if (WEB3DesignateMethodDef.SELECT.equals(l_strPaymentMethod))
             {
                 l_quantityType = QuantityTypeEnum.AMOUNT;
                 //return QuantityTypeEnum.AMOUNT;
             }
             else if (WEB3DesignateMethodDef.AMOUNT.equals(
                        l_strPaymentMethod))
             {
                 l_quantityType = QuantityTypeEnum.AMOUNT;
                 //return QuantityTypeEnum.AMOUNT;
             }
             else if (WEB3DesignateMethodDef.NUMBER.equals(
                        l_strPaymentMethod))
             {
                 l_quantityType = QuantityTypeEnum.QUANTITY;
                 //return QuantityTypeEnum.QUANTITY;
             }
         }
         //�Q�j�@@����.�ݓ������ʒm�L���[Params.get�������()�̖߂�l���h1�F
         //�S�����h�ł͂Ȃ��ꍇ
         else 
         {             
             String l_Method = l_ruitoOrderNotifyCueParams.getSpecifyDiv();
             if ("1".equals(l_Method))
             {
                 l_quantityType = QuantityTypeEnum.QUANTITY;
                 //return QuantityTypeEnum.QUANTITY;
             }
             else
             {
                 l_quantityType = QuantityTypeEnum.AMOUNT;      
             }
         }
         log.exiting(STR_METHOD_NAME);     
         return l_quantityType;   
    }

}

@
