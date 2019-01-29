head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOpenContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : OP�V�K�����̓T�[�r�XImpl(WEB3OptionOpenContractInputServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/06/15 ������ (���u) �V�K�쐬
                            :2004/07/23  ������ (���u) �V�K�C��
                 001: 2004/07/31 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000095�A96
                 002: 2004/08/13 ���Ō� �Ή��o�O BUG94
                 003: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�                 
                 004: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000083)��Ή�
                 005: 2006/07/14 ������  (���u) ���f��No.466��Ή�
Revesion History    : 2007/06/11 �����F�@@(���u) ���f�� 661
Revesion History    : 2007/06/21 �Ј���@@(���u) ���f�� 712
Revesion History    : 2007/06/27 �����F�@@(���u) ���f�� 767
Revesion History    : 2007/06/28 �����F�@@(���u) ���f�� 769
Revesion History    : 2007/11/20 �g�E�N�|�@@(���u) ���f�� 792
Revesion History    : 2008/07/25 �����Á@@(���u) ���f�� No.889�C890�C894
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;

import webbroker3.ifo.message.WEB3OptionsProductSelectRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputResponse;
import webbroker3.ifo.message.WEB3OptionsProductSelectResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractInputService;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoIndexMaster;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.IfoIndexMasterRow;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.WEB3OptionClientRequestService;

/**
 * (OP�V�K�����̓T�[�r�XImpl)<BR>
 * <BR>
 * �����w���I�v�V�����V�K�����̓T�[�r�X�����N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionOpenContractInputServiceImpl extends WEB3OptionClientRequestService implements WEB3OptionOpenContractInputService
{
    /**
      * Logger
      */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3OptionOpenContractInputServiceImpl.class);

    /**
     * @@roseuid 40C0C0A9029F
     */
    public WEB3OptionOpenContractInputServiceImpl()
    {

    }

    /**
     * �����w���I�v�V�����V�K�����̓T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Acreate���͉�ʂ܂��́Acreate�����I����ʂ��R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407A521802AF
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3OptionsOpenMarginInputRequest)
        {        
            l_response = this.createInputScreen((WEB3OptionsOpenMarginInputRequest) l_request);
        }
        else if (l_request instanceof WEB3OptionsProductSelectRequest)
        {         
            l_response = this.createProductSelectScreen((WEB3OptionsProductSelectRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s��.");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }

    /**
     * (create���͉��)<BR>
     * <BR>
     * �����w���I�v�V�����V�K�����͉�ʂ�\������B<BR>
     * <BR>
     * �u(OP�V�K������)���͉�ʕ\���f�[�^�擾�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3OptionsOpenMarginInputResponse
     * @@roseuid 409B5298028A
     */
    public WEB3OptionsOpenMarginInputResponse createInputScreen(WEB3OptionsOpenMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createInputScreen(WEB3OptionsOpenMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.validate();
            
            //�⏕�������擾����B
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
            WEB3GentradeMainAccount l_genTradeMainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
                (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
           
            //validate�������Ăяo���B
            WEB3OptionOrderManagerImpl l_ifoOrderManagerImpl = 
                (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            l_ifoOrderManagerImpl.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);
            
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = 
                (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();  
                                        
            //validate�戵�\�V�K������
            //�⏕�����F get�⏕����()�̕Ԃ�l 
            //is�����F�@@���N�G�X�g�D���敪���h�����h�̏ꍇ��true�A�h�����h�̏ꍇ��false            
            boolean l_blnIsBuy = WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType) ? true : false;
            l_ifoOrderManagerReusableValidations.validateHandlingOpenContractOrder(l_subAccount, l_blnIsBuy);
            
            Institution l_institution = l_subAccount.getInstitution();

            //���X
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
                        
            WEB3IfoProductImpl l_ifoProductImpl = null;
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl =null; 
            Market l_market = null;
            
            //�����R�[�h��null�łȂ��ꍇ�́A�ȉ��̏��������{����
            if (l_request.opProductCode != null)
            {
                //get����(�،���� : Institution, �����R�[�h : String)
                //[����] 
                //�،���ЁF�@@�⏕�����Dget�،����()�̕Ԃ�l 
                //�����R�[�h�F�@@���N�G�X�g�D�����R�[�h
                try
                {
                    l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(
                        l_institution, 
                        l_request.opProductCode);
                }
                catch (NotFoundException l_nfex)    
                {   
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                        STR_METHOD_NAME);
                }   

                if (l_ifoProductImpl == null)   
                {   
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                        STR_METHOD_NAME);
                }   

                l_market = l_ifoProductImpl.getPrimaryMarket(); 

                //get�������
                //[����] 
                //�،���ЁF�@@�⏕�����Dget�،����()�̕Ԃ�l 
                //�����R�[�h�F�@@���N�G�X�g�D�����R�[�h 
                //�s��R�[�h�F �s��DgetMarketCode()�̕Ԃ�l
                try
                {
                    l_ifoTradedProductImpl = l_ifoProductManagerImpl.getIfoTradedProduct(
                        l_institution, 
                        l_request.opProductCode, 
                        l_market.getMarketCode()); 
                }
                catch (NotFoundException l_nfex)    
                {   
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00735, 
                        STR_METHOD_NAME);
                }   
                if (l_ifoTradedProductImpl == null) 
                {   
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00735, 
                        STR_METHOD_NAME);
                }   

                //validate�戵�\�w��
                //[����] 
                //���X�R�[�h�F �⏕�����Dget����X()�DgetBranchCode()�̕Ԃ�l 
                //�敨OP��������F �敨OP��������I�u�W�F�N�g 
                l_ifoOrderManagerReusableValidations.validateHandlingIndex(
                    l_branch.getBranchCode(), 
                    l_ifoTradedProductImpl);                
            }
            
            //������           
            Date l_datBizDate = null;           
            Date l_datTmpBizDate = null;            

            // �ȉ��̃��N�G�X�g�f�[�^��null�łȂ��ꍇ�͈ȉ��̏��������{����            
            if (l_request.marketCode != null 
                && l_request.targetProductCode != null 
                && l_request.strikePrice != null 
                && l_request.delivaryMonth != null 
                && l_request.opProductType != null)           
            {         
                IfoDerivativeTypeEnum l_ifoDerivativeTypeEnum = null;     
                if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_request.opProductType))       
                {     
                    l_ifoDerivativeTypeEnum = IfoDerivativeTypeEnum.CALL_OPTIONS; 
                }     
                else if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_request.opProductType))       
                {     
                    l_ifoDerivativeTypeEnum = IfoDerivativeTypeEnum.PUT_OPTIONS;  
                }     
                
                double l_dblStrikePrice = 0;      
                if (l_request.strikePrice != null && !"".equals(l_request.strikePrice))       
                {     
                    l_dblStrikePrice = Double.parseDouble(l_request.strikePrice); 
                }     
                
                try       
                {     
                //�敨OP�����I�u�W�F�N�g���擾����B      
                l_ifoProductImpl=     
                l_ifoProductManagerImpl.getIfoProduct(        
                    l_institution,    
                    l_request.targetProductCode,  
                    l_request.delivaryMonth,  
                    l_ifoDerivativeTypeEnum,  
                    l_dblStrikePrice, 
                    WEB3DivisionTypeDef.DIVISION_DEFAULT, 
                    l_request.marketCode);    
                }     
                catch (NotFoundException l_nfex)      
                {     
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                        STR_METHOD_NAME);   
                }     
                
                if (l_ifoProductImpl == null)     
                {     
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                        STR_METHOD_NAME);   
                }     
                
                //������ԊǗ�.reset�����R�[�h(�敨OP�w���}�X�^�I�u�W�F�N�g.�����Y�����R�[�h)���R�[������B      
                WEB3GentradeTradingTimeManagement.resetProductCode(l_request.targetProductCode);      
                
                l_datTmpBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();        
                //���������擾      
                if (l_datBizDate == null || l_datTmpBizDate.before(l_datBizDate))     
                {     
                    l_datBizDate = l_datTmpBizDate;   
                }     
                try       
                {     
                //�敨OP��������I�u�W�F�N�g���擾����B        
                l_ifoTradedProductImpl =      
                l_ifoProductManagerImpl.getIfoTradedProduct(      
                    l_institution,    
                    l_request.marketCode, 
                    l_request.targetProductCode,  
                    l_request.delivaryMonth,  
                    l_ifoDerivativeTypeEnum,  
                    l_dblStrikePrice, 
                    WEB3DivisionTypeDef.DIVISION_DEFAULT, 
                    l_request.marketCode);    
                }     
                catch (NotFoundException l_nfex)      
                {     
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00735, 
                        STR_METHOD_NAME);   
                }     
                
                if (l_ifoTradedProductImpl == null)       
                {     
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00735, 
                        STR_METHOD_NAME);   
                }     
                
                // validate�戵�\�w��  
                //[����]        
                //���X�R�[�h�F �⏕�����Dget����X()�DgetBranchCode()�̕Ԃ�l        
                //�敨OP��������F �敨OP��������I�u�W�F�N�g        
                l_ifoOrderManagerReusableValidations.validateHandlingIndex(       
                    l_branch.getBranchCode(),   
                    l_ifoTradedProductImpl);                    
            }         

            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            
            Double  l_dblTradingPower = null;
            //�����N�G�X�g.���敪 == �h�����h�̏ꍇ�̂ݎ��{����
            if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType))
            {
				if ((l_request.opProductCode !=null)||  
				(l_request.marketCode != null 
				&& l_request.targetProductCode != null 
				&& l_request.strikePrice != null 
				&& l_request.delivaryMonth != null 
				&& l_request.opProductType != null))

				{
				    l_dblTradingPower = l_tradingPowerService.getOptionBuyTradingPower(l_subAccount, l_ifoProductImpl);  
				}else
				{
					l_dblTradingPower = l_tradingPowerService.getOptionBuyTradingPower(l_subAccount, null);  
				}
                              
            }
            //�����N�G�X�g.���敪 == �h�����h�̏ꍇ�̂ݎ��{����B
            else
            {
				if ((l_request.opProductCode !=null)||  
				(l_request.marketCode != null 
				&& l_request.targetProductCode != null 
				&& l_request.strikePrice != null 
				&& l_request.delivaryMonth != null 
				&& l_request.opProductType != null))

				{
                    l_dblTradingPower = l_tradingPowerService.getFuturesOptionTradingPower(
                        l_subAccount, 
                        false, 
                        l_ifoProductImpl);
				}
				else
				{
					l_dblTradingPower = l_tradingPowerService.getFuturesOptionTradingPower(
						l_subAccount, 
						false, 
						null);
				}
            }

            //�Ǌԋ߂̎s��R�[�h���擾����B
            //[����] 
            //���X�F�@@�⏕�����Dget�戵�X() 
            //�����^�C�v�F�@@�敨/�I�v�V����                   
            String[] l_tradeCloseMarkets = 
                WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                    l_branch, 
                    WEB3FuturesOptionDivDef.OPTION);
            
            //[�戵�\��������()�Ɏw�肷�����] 
            //�،���ЃR�[�h�F 
            //�،���� = �⏕����.get�،����() 
            //�،���ЃR�[�h = �،����.get�،���ЃR�[�h() 
            //�����^�C�v(="�敨�I�v�V����")�F�@@ProductTypeEnum 
            //�敨�^�I�v�V�����敪(="�I�v�V����")�F�@@String
            WEB3GentradeHandlingOrderCond l_handingOrderCond = new WEB3GentradeHandlingOrderCond(
                l_institution.getInstitutionCode(), 
                ProductTypeEnum.IFO, 
                WEB3FuturesOptionDivDef.OPTION, 
                WEB3MarginTradingDivDef.DEFAULT);

            if (l_request.opProductCode != null
                || (l_request.marketCode != null
                    && l_request.targetProductCode != null
                    && l_request.strikePrice != null
                    && l_request.delivaryMonth != null
                    && l_request.opProductType != null))
            {
                //set����ŏI��(����ŏI�� : Date)
                l_handingOrderCond.setTradingEndDate(l_ifoTradedProductImpl.getLastTradingDate());
            }

            //���b�Z�[�W �戵�\�����P���敪�擾
            //[����] 
            //is�V�K���F�@@true(:�V�K��) 
            //is�����F�@@���N�G�X�g�D���敪���h�����h�̏ꍇ��true�A�h�����h�̏ꍇ��false�B
            String[] l_orderUnitDivision = null;
            if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType))
            {
                l_orderUnitDivision = l_handingOrderCond.getHandlingPossibleOrderPriceDiv(true, true);
            }
            else
            {
                l_orderUnitDivision = l_handingOrderCond.getHandlingPossibleOrderPriceDiv(true, false);
            }
            
            //�戵�\���s�����擾( )
            String[] l_strPossibleExecCond = l_handingOrderCond.getHandlingPossibleExecCond();
			l_strPossibleExecCond=l_ifoOrderManagerImpl.getHandlingPossibleExecConds(l_orderUnitDivision,l_strPossibleExecCond);

            //�戵�\���������敪���擾����
            String[] l_strHandlingPossible = l_handingOrderCond.getHandlingPossibleExpirationDateType();

            //�戵�\�����������擾����            
            String[] l_strHandingPossibleOrderCond = l_handingOrderCond.getHandlingPossibleOrderCond();

            //(���X�w����)�戵�����I�u�W�F�N�g���擾����
            WEB3GentradeBranchIndexDealtCond[] l_handLingCondBranchIndex;
            l_handLingCondBranchIndex =
                WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
                    l_institution.getInstitutionCode(), l_branch.getBranchCode(), WEB3FuturesOptionDivDef.OPTION);

            int l_length = 0;
            if (l_handLingCondBranchIndex != null)
            {
                l_length = l_handLingCondBranchIndex.length;
            }

            //�s��ꗗ
            ArrayList l_lisMarkets = new ArrayList();
            HashMap l_hsmMarkets = new HashMap();

            //�w����ʈꗗ
            ArrayList l_lisUnderlyingProducts = new ArrayList();
            HashMap l_hsmUnderlyingProducts = new HashMap();

            String l_strMatketCode = null;
            String l_strUnderlyingProductCode = null;

            for (int i = 0; i < l_length; i++)
            {
                //�敨OP�w���}�X�^�I�u�W�F�N�g���擾���� 
                WEB3IfoIndexMaster l_indexMaster = new WEB3IfoIndexMaster(l_handLingCondBranchIndex[i].getUnderlyingProductCode(), WEB3FuturesOptionDivDef.OPTION); //DateFindException

                //������ԊǗ�.reset�����R�[�h(�敨OP�w���}�X�^�I�u�W�F�N�g.�����Y�����R�[�h)���R�[������B
                WEB3GentradeTradingTimeManagement.resetProductCode(((IfoIndexMasterRow) l_indexMaster.getDataSourceObject()).getUnderlyingProductCode());

                l_datTmpBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                //���������擾
                if (l_datBizDate == null || l_datTmpBizDate.before(l_datBizDate))
                {
                    l_datBizDate = l_datTmpBizDate;
                }

                l_strMatketCode = ((BranchIndexDealtCondRow) l_handLingCondBranchIndex[i].getDataSourceObject()).getMarketCode();
                l_strUnderlyingProductCode = l_handLingCondBranchIndex[i].getUnderlyingProductCode();

                if (!l_hsmMarkets.containsKey(l_strMatketCode))
                {
                    //�d�����Ȃ��s����擾
                    l_hsmMarkets.put(l_strMatketCode, l_strMatketCode);
                    l_lisMarkets.add(l_strMatketCode);
                }

                if (!l_hsmUnderlyingProducts.containsKey(l_strUnderlyingProductCode))
                {
                    //�d�����Ȃ��w����ʂ��擾
                    l_hsmUnderlyingProducts.put(l_strUnderlyingProductCode, l_strUnderlyingProductCode);
                    l_lisUnderlyingProducts.add(l_strUnderlyingProductCode);
                }

            }

            //�����ꗗ
            List l_lisMonthOfDeliverys = new ArrayList();

            //�����̎戵�\�Ȍ����Y�����R�[�h�A�敨/�I�v�V�����敪�ɊY������A�������ڂ��擾����B
            l_lisMonthOfDeliverys = l_ifoProductManagerImpl.getDeliveryMonthList(
                l_handLingCondBranchIndex,
                WEB3FuturesOptionDivDef.OPTION);

            //�����J�n��
            Date l_datStartDay = null;

            //�����ŏI��
            Date l_datEndDay = null;

            //�����������j���ꗗ
            Date[] l_datHoliday = null;

            //is�o����܂Œ����戵�\<����ŏI���l��>( )��true�̏ꍇ�͈ȉ��̏��������{����
            if (l_handingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering())
            {
                if (l_request.opProductCode == null)
                {
                    //get�o����܂Œ����J�n��<����ŏI���l��>(Date)
                    l_datStartDay =
                        l_handingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(l_datBizDate);

                    //get�o����܂Œ����ŏI��<����ŏI���l��>(Date)
                    l_datEndDay =
                        l_handingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(l_datBizDate);

                    // get�����������j���ꗗ(Date)
                    l_datHoliday = l_handingOrderCond.getExpirationDateHoliday(l_datBizDate);
                }
                else
                {
                    //get�o����܂Œ����J�n��<����ŏI���l��>(Date)
                    l_datStartDay = l_handingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(null);

                    //get�o����܂Œ����ŏI��<����ŏI���l��>(Date)
                    l_datEndDay = l_handingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(null);

                    // get�����������j���ꗗ(Date)
                    l_datHoliday = l_handingOrderCond.getExpirationDateHoliday();
                }
            }

            //�s��ꗗ
            String[] l_strMarkertsList = new String[l_lisMarkets.size()];
            l_lisMarkets.toArray(l_strMarkertsList);

            //�����ꗗ
            String[] l_strMonthOfDeliverys = new String[l_lisMonthOfDeliverys.size()];
            l_lisMonthOfDeliverys.toArray(l_strMonthOfDeliverys);

            //�w����ʈꗗ
            String[] l_strUnderlyingProductsList = new String[l_lisUnderlyingProducts.size()];
            l_lisUnderlyingProducts.toArray(l_strUnderlyingProductsList);

            //����
            double l_dblCurrentPrice = 0;

            //����(���ݒl)�@@
            Timestamp l_currentPriceTime = null;

            //�O����
            double l_dblChange = 0;
            
            // �ȉ��̂����ꂩ�ɂ��Ă͂܂�ꍇ���{����B    
            // �P�j���N�G�X�g.�����R�[�h!=null 
            // �Q�j�ȉ��̃��N�G�X�g�f�[�^!=null 
            // ���N�G�X�g.����s��    
            // ���N�G�X�g.�w�����    
            // ���N�G�X�g.����  
            // ���N�G�X�g.�I�v�V�����敪 
            // ���N�G�X�g.�s�g���i    
            if ((l_request.opProductCode !=null)||  
                (l_request.marketCode != null 
                && l_request.targetProductCode != null 
                && l_request.strikePrice != null 
                && l_request.delivaryMonth != null 
                && l_request.opProductType != null))
            {       
                FinApp finApp = (FinApp) Services.getService(FinApp.class);
                WEB3QuoteDataSupplierService l_quoteDataSupplierService = (WEB3QuoteDataSupplierService) finApp.getTradingModule(ProductTypeEnum.IFO).getQuoteDataSupplierService();

                l_quoteDataSupplierService.getIfoQuote(l_ifoTradedProductImpl, RealType.DELAY);
                WEB3IfoQuoteData l_quoteDataImpl = null;

                if (l_genTradeMainAccount.isRealCustomer())
                {
                    l_quoteDataImpl = l_quoteDataSupplierService.getIfoQuote(l_ifoTradedProductImpl, RealType.REAL);
                }
                else
                {
                    l_quoteDataImpl = l_quoteDataSupplierService.getIfoQuote(l_ifoTradedProductImpl, RealType.DELAY);
                }

                //����
                l_dblCurrentPrice = l_quoteDataImpl.getCurrentPrice();
                log.debug("l_dblCurrentPrice = " + l_quoteDataImpl.getCurrentPrice());
                
                //����(���ݒl)�������擾����
                l_currentPriceTime = l_quoteDataImpl.getCurrentPriceTime();

                //�O������擾����
                l_dblChange = l_quoteDataImpl.getChange();
            }

            WEB3OptionsOpenMarginInputResponse l_marginInputResponse = (WEB3OptionsOpenMarginInputResponse) l_request.createResponse();
            //�戵�\�����P���敪�擾
            l_marginInputResponse.orderPriceDivList = l_orderUnitDivision;

            //�戵�\���s�����擾
            l_marginInputResponse.execCondList = l_strPossibleExecCond;

            //�戵�\���������敪�擾
            l_marginInputResponse.expirationDateTypeList = l_strHandlingPossible;

            //���X�|���X.�L�������J�n����get�o����܂Œ����J�n��<����ŏI���l��>�̕Ԃ�l
            l_marginInputResponse.expirationStartDate = WEB3DateUtility.toDay(l_datStartDay);

            //���X�|���X.�L�������ŏI����get�o����܂Œ����ŏI��<����ŏI���l��>�̕Ԃ�l
            l_marginInputResponse.expirationEndDate = WEB3DateUtility.toDay(l_datEndDay);

            //get�����������j���ꗗ
            l_marginInputResponse.holidayList = l_datHoliday;

            //�戵�\���������擾�̕Ԃ�l
            l_marginInputResponse.orderCondTypeList = l_strHandingPossibleOrderCond;
            
            //���X�|���X.�v�w�l�p���s�����ꗗ = �敨OP�f�[�^�A�_�v�^.get�v�w�l�p���s�����ꗗ()�̕Ԃ�l
            l_marginInputResponse.wlimitExecCondList = 
                WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(l_strPossibleExecCond, l_strHandingPossibleOrderCond);

            //���X�|���X.����敪 = ������ԊǗ�.get����敪()
            l_marginInputResponse.sessionType = WEB3GentradeTradingTimeManagement.getSessionType();

            //���X�|���X�D�V�K���\�z�@@���@@
            //�E���N�G�X�g.���敪�������̏ꍇ�Aget�I�v�V�����V�K�����\�z�̕Ԃ�l�@@���߂�l���}�C�i�X�l�̏ꍇ��0�ɐݒ�
            //�E���N�G�X�g.���敪�������̏ꍇ�Aget�敨�I�v�V�����V�K���\�z�̕Ԃ�l�@@���߂�l���}�C�i�X�l�̏ꍇ��0�ɐݒ�
            double l_dblPower = 0;
            if (l_dblTradingPower != null)
            {
                l_dblPower = l_dblTradingPower.doubleValue();

                if (l_dblPower < 0)
                {
                    l_dblPower = 0;
                }

                //���X�|���X�D�V�K���\�z�@@���@@get�I�v�V�����V�K���\�z�̕Ԃ�l ���߂�l���}�C�i�X�l�̏ꍇ��0�ɐݒ�
                l_marginInputResponse.opTradingPower = WEB3StringTypeUtility.formatNumber(l_dblPower);
            }
            else
            {
                l_marginInputResponse.opTradingPower = null;
            }
            
            //get�s��ǌx���w���̕Ԃ�l
            l_marginInputResponse.messageSuspension = l_tradeCloseMarkets;

            //���X�|���X�D����s��ꗗ�@@���@@(���X�w����)�戵�����I�u�W�F�N�g�D�s��R�[�h�@@
            l_marginInputResponse.marketList = l_strMarkertsList;

            //���X�|���X�D�w����ʈꗗ�@@���@@(���X�w����)�戵�����I�u�W�F�N�g�D�����Y�����R�[�h
            l_marginInputResponse.targetProductList = l_strUnderlyingProductsList;

            //���X�|���X�D�����ꗗ�@@���@@�敨OP�v���_�N�g�}�l�[�W���Dget�����ꗗ�i�j�̕Ԃ�l
            l_marginInputResponse.delivaryMonthList = l_strMonthOfDeliverys;
            
            //)����t���[�ȉ��̃��N�G�X�g�f�[�^��null�łȂ��ꍇ�͈ȉ��̏��������{����
            if (l_request.marketCode != null 
                && l_request.targetProductCode != null 
                && l_request.strikePrice != null 
                && l_request.delivaryMonth != null 
                && l_request.opProductType != null)
            {
                //�������                
                l_marginInputResponse.opProductCode = ((IfoProductRow) l_ifoTradedProductImpl.getProduct().getDataSourceObject()).getProductCode();

                //����s��
                l_marginInputResponse.marketCode = l_request.marketCode;

                //�w�����    
                l_marginInputResponse.targetProductCode = l_request.targetProductCode;

                //����
                l_marginInputResponse.delivaryMonth = l_request.delivaryMonth;

                //�I�v�V�������i�敪
                l_marginInputResponse.opProductType =  l_request.opProductType;

                //�s�g���i
                l_marginInputResponse.strikePrice = l_request.strikePrice;

                //getCurrentPrice�̕Ԃ�l                                   
                //������0�̏ꍇ�Anull��ݒ肷��                                 
                if (l_dblCurrentPrice == 0D)                                    
                {                                   
                    l_marginInputResponse.currentPrice = null;                                       
                }                                   
                else                                    
                {                                   
                    //������0�łȂ��ꍇ�A�擾�������ݒl��ݒ肷��                                     
                    l_marginInputResponse.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);                                      
                }                                   

                //���X�|���X�D������ԁ@@���@@getCurrentPriceTime�̕Ԃ�l
                l_marginInputResponse.currentPriceTime = l_currentPriceTime;

                //���X�|���X�D�O����@@���@@getChange�̕Ԃ�l
                l_marginInputResponse.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
            }
            else
            {
                if (l_request.opProductCode == null)
                {
                    //�������R�[�h��null�̏ꍇ��null���Z�b�g
                    //���X�|���X�DgetProductCode()
                    l_marginInputResponse.opProductCode = null;
                    
                    //���X�|���X�D�����Y�����R�[�h    
                    l_marginInputResponse.targetProductCode = null;
                    
                    //���X�|���X�D�s��R�[�h
                    l_marginInputResponse.marketCode = null;
                    
                    //�敨OP�����I�u�W�F�N�g�D�����@@
                    l_marginInputResponse.delivaryMonth = null;
                    
                    //�敨OP�����I�u�W�F�N�g�D�s�g���i
                    l_marginInputResponse.strikePrice = null;
                    
                    //���X�|���X�D�I�v�V�������i�敪   ���@@null
                    l_marginInputResponse.opProductType = null;
                    
                    //���X�|���X�D���ݒl�@@���@@null
                    l_marginInputResponse.currentPrice = null;

                    //���X�|���X�D������� = ���@@null
                    l_marginInputResponse.currentPriceTime = null;

                    //���X�|���X�D�O����@@���@@null
                    l_marginInputResponse.comparedPreviousDay = null;
                }
                else
                {
                    //���X�|���X�DgetProductCode()
                    l_marginInputResponse.opProductCode = l_ifoProductImpl.getProductCode();

                    //���X�|���X�D�w�����   
                    l_marginInputResponse.targetProductCode = l_ifoProductImpl.getUnderlyingProductCode();

                    //���X�|���X�D�s��R�[�h
                    l_marginInputResponse.marketCode = l_market.getMarketCode();

                    //���X�|���X�D�����@@
                    l_marginInputResponse.delivaryMonth = l_ifoProductImpl.getMonthOfDelivery();

                    //���X�|���X�D�s�g���i
                    l_marginInputResponse.strikePrice = WEB3StringTypeUtility.formatNumber(l_ifoProductImpl.getStrikePrice());
                    
                    //���X�|���X�D�I�v�V�������i�敪 = 
                    //�敨OP�����I�u�W�F�N�g.�敨�I�v�V�������i���h�R�[���I�v�V�����h�̏ꍇ�́A�hC�h���Z�b�g�B�h�v�b�g�I�v�V�����h�̏ꍇ�́A�hP�h���Z�b�g�B
                    if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(
                        l_ifoProductImpl.getDerivativeType()))
                    {
                        l_marginInputResponse.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
                    }
                    else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductImpl.getDerivativeType()))
                    {
                        l_marginInputResponse.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
                    } 
                    
                    //getCurrentPrice�̕Ԃ�l                                   
                    //������0�̏ꍇ�Anull��ݒ肷��                                 
                    if (l_dblCurrentPrice == 0D)                                    
                    {                                   
                        l_marginInputResponse.currentPrice = null;                                       
                    }                                   
                    else                                    
                    {                                   
                        //������0�łȂ��ꍇ�A�擾�������ݒl��ݒ肷��                                     
                        l_marginInputResponse.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);                                      
                    }                                   

                    //���X�|���X�D������ԁ@@���@@getCurrentPriceTime�̕Ԃ�l
                    l_marginInputResponse.currentPriceTime = l_currentPriceTime;
                    
                    //���X�|���X�D�O����@@���@@getChange�̕Ԃ�l
                    l_marginInputResponse.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return l_marginInputResponse;
        }
        catch (DataQueryException l_edqx)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_edqx);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, STR_METHOD_NAME, 
                l_edqx.toString(), 
                l_edqx);
        }
        catch (DataNetworkException l_edw)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_edw);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                STR_METHOD_NAME, 
                l_edw.toString(), 
                l_edw);
        }
        catch (NotFoundException l_enf)
        {
            log.error("�f�[�^�s�����G���[", l_enf);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                STR_METHOD_NAME, 
                l_enf.toString(), 
                l_enf);
        }
    }

    /**
     * (create�����I�����)<BR>
     * <BR>
     * �����w���I�v�V�����V�K�������I����ʂ�\������B<BR>
     * <BR>
     * �u(OP�V�K������)�����I����ʕ\���f�[�^�擾�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3OptionsOrderCarryOverResponse.WEB3OptionsProductSelectResponse
     * @@roseuid 409B52CB01B0
     */
    protected WEB3OptionsProductSelectResponse createProductSelectScreen(WEB3OptionsProductSelectRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createProductSelectScreen(WEB3OptionsProductSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //���b�Z�[�W validate( )
        l_request.validate();

        //�⏕�������擾����B
        SubAccount l_subAccount = getSubAccount();    

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //validate�������Ăяo���B
        WEB3OptionOrderManagerImpl l_ifoOrderManagerImpl = (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        l_ifoOrderManagerImpl.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);

        WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();

        //�Ǌԋ߂̎s��R�[�h���擾����B(���X, ProductTypeEnum, String)
        String[] l_tradeCloseMarkets = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_branch, WEB3FuturesOptionDivDef.OPTION);

        //�擾�،����
        Institution l_institution = l_subAccount.getInstitution();

        //(���X�w����)�戵�����I�u�W�F�N�g���擾����     
        WEB3GentradeBranchIndexDealtCond[] l_handLingCondBranchIndex;

        l_handLingCondBranchIndex =
            WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
                l_institution.getInstitutionCode(), l_branch.getBranchCode(), WEB3FuturesOptionDivDef.OPTION);

        log.debug("l_institution.getInstitutionCode() = " + l_institution.getInstitutionCode());
        log.debug("l_branch.getBranchCode() = " + l_branch.getBranchCode());

        int l_intLength = 0;

        if (l_handLingCondBranchIndex != null)
        {
            l_intLength = l_handLingCondBranchIndex.length;
        }

        //�s��ꗗ
        ArrayList l_lisMarkets = new ArrayList();
        HashMap l_hsmMarkets = new HashMap();

        //�w����ʈꗗ
        ArrayList l_lisUnderlyingProducts = new ArrayList();
        HashMap l_hsmUnderlyingProducts = new HashMap();

        String l_strMatketCode = null;
        String l_strUnderlyingProductCode = null;

        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
            (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        for (int i = 0; i < l_intLength; i++)
        {
            l_strMatketCode = ((BranchIndexDealtCondRow) l_handLingCondBranchIndex[i].getDataSourceObject()).getMarketCode();
            l_strUnderlyingProductCode = l_handLingCondBranchIndex[i].getUnderlyingProductCode();
            if (!l_hsmMarkets.containsKey(l_strMatketCode))
            {
                //�d�����Ȃ��s����擾
                l_hsmMarkets.put(l_strMatketCode, l_strMatketCode);
                l_lisMarkets.add(l_strMatketCode);
            }

            if (!l_hsmUnderlyingProducts.containsKey(l_strUnderlyingProductCode))
            {
                //�d�����Ȃ��w����ʂ��擾
                l_hsmUnderlyingProducts.put(l_strUnderlyingProductCode, l_strUnderlyingProductCode);
                l_lisUnderlyingProducts.add(l_strUnderlyingProductCode);
            }

        }

        //�����̎戵�\�Ȍ����Y�����R�[�h�A�敨/�I�v�V�����敪�ɊY������A�������ڂ��擾����B
        List l_lisDeliveryMonths = l_ifoProductManagerImpl.getDeliveryMonthList(
            l_handLingCondBranchIndex,
            WEB3FuturesOptionDivDef.OPTION);
        //���X�|���X�f�[�^�𐶐�����B
        WEB3OptionsProductSelectResponse l_marginSelectResponse = (WEB3OptionsProductSelectResponse) l_request.createResponse();

        //�s��ꗗ
        String[] l_strMarkertsList = new String[l_lisMarkets.size()];
        l_lisMarkets.toArray(l_strMarkertsList);

        //�����ꗗ
        String[] l_strMonthOfDeliverys = new String[l_lisDeliveryMonths.size()];
        l_lisDeliveryMonths.toArray(l_strMonthOfDeliverys);

        //�w����ʈꗗ
        String[] l_strUnderlyingProductsList = new String[l_lisUnderlyingProducts.size()];
        l_lisUnderlyingProducts.toArray(l_strUnderlyingProductsList);

        //���X�|���X�D�V�K���\�z�@@���@@null
        l_marginSelectResponse.opTradingPower = null;
        //���X�|���X�D����s��ꗗ�@@���@@(���X�w����)�戵�����I�u�W�F�N�g�D�s��R�[�h
        l_marginSelectResponse.marketList = l_strMarkertsList;
        //���X�|���X�D�w����ʈꗗ�@@���@@(���X�w����)�戵�����I�u�W�F�N�g�D�����Y�����R�[�h
        l_marginSelectResponse.targetProductList = l_strUnderlyingProductsList;
        //���X�|���X�D�����ꗗ�@@���@@�敨OP�v���_�N�g�}�l�[�W���Dget�����ꗗ�i�j�̕Ԃ�l
        l_marginSelectResponse.delivaryMonthList = l_strMonthOfDeliverys;
        //���X�|���X�D����I���x�������@@���@@get�s��ǌx���w���̕Ԃ�l
        l_marginSelectResponse.messageSuspension = l_tradeCloseMarkets;

        return l_marginSelectResponse;

    }
}
@
