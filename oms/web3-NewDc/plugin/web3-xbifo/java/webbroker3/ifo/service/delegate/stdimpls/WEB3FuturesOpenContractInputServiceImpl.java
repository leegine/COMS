head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�V�K�����̓T�[�r�X�����N���X(WEB3FuturesOpenContractInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 ������ (���u) �V�K�쐬
                 : 2006/07/28 �юu�� (���u) �d�l�ύX�@@���f��483
Revesion History : 2007/06/21 ���^�] (���u) �d�l�ύX���f��No.679 702
Revesion History : 2007/06/27 �����F (���u) ���f�� 767
Revesion History : 2007/06/28 �����F (���u) ���f�� 769
Revesion History : 2007/11/20 ���n�m (���u) �d�l�ύX ���f��797
Revesion History : 2007/11/28 ���n�m (���u) �d�l�ύX Java�\�[�X�i��{�݌v�ƍ����Ă��Ȃ������jNo.010
Revesion History : 2008/07/25 ���z (���u) �d�l�ύX ���f��889,890,894
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;

import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoIndexMaster;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.data.IfoIndexMasterRow;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.message.WEB3FuturesOpenMarginInputRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginInputResponse;
import webbroker3.ifo.message.WEB3FuturesProductSelectRequest;
import webbroker3.ifo.message.WEB3FuturesProductSelectResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractInputService;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����w���敨�V�K�����̓T�[�r�XImpl)<BR>
 * �����w���敨�V�K�����̓T�[�r�X�����N���X<BR>
 * 
 * @@author ������
 * @@version 1.0 
 */
public class WEB3FuturesOpenContractInputServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesOpenContractInputService
{
    /**
      * Logger
      */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3FuturesOpenContractInputServiceImpl.class);

    /**
     * @@roseuid 40F7A2C50213
     */
    public WEB3FuturesOpenContractInputServiceImpl()
    {

    }

    /**
     * �����w���敨�V�K�����̓T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Acreate���͉�ʂ܂��́A<BR>
     * create�����I����ʂ��R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A854F5011B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = getClass().getName() + "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3FuturesOpenMarginInputRequest)
        {
            l_response = this.createInput((WEB3FuturesOpenMarginInputRequest) l_request);
        }
        else if (l_request instanceof WEB3FuturesProductSelectRequest)
        {
            l_response = this.createProductSelect((WEB3FuturesProductSelectRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s��.");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create���͉��)<BR>
     * �����w���敨�V�K�����͉�ʂ�\������B<BR>
     * <BR>
     * �u(�敨�V�K������)���͉�ʕ\���f�[�^�擾�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3FuturesOpenMarginInputResponse
     * @@roseuid 40A854F5013B
     */
    protected WEB3FuturesOpenMarginInputResponse createInput(WEB3FuturesOpenMarginInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createInput(WEB3FuturesOpenMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        Map l_hmMonthOfDeliverys = new Hashtable();
        Map l_hmMarkertList = new Hashtable();
        Vector l_vecMonthOfDeliverys = new Vector();
        try
        {
            l_request.validate();
            //�⏕�������擾����B
            SubAccount l_subAccount = this.getSubAccount();

            WEB3GentradeMainAccount l_genTradeMainAccount = 
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
                
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
                (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
            //validate�������Ăяo���B 
            WEB3FuturesOrderManagerImpl l_futuresOrderManagerImpl = 
                (WEB3FuturesOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            l_futuresOrderManagerImpl.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);
            
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = 
                (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();  
            //�擾�،����    
            Institution l_institution = l_subAccount.getInstitution();
            
            //����
            WEB3IfoProductImpl l_ifoProductImpl = null;
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
            Market l_market = null;

            if (l_request.futProductCode != null)
            {
                //�����R�[�h��null�łȂ��ꍇ�́A�ȉ��̏��������{����
                //get����(�،���� : Institution, �����R�[�h : String)
                //[����] 
                //�،���ЁF�@@�⏕�����Dget�،����()�̕Ԃ�l 
                //�����R�[�h�F�@@���N�G�X�g�D�����R�[�h
                try
                {
                    l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(
                        l_institution, 
                        l_request.futProductCode);
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
                        l_request.futProductCode, 
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
                    l_genTradeMainAccount.getBranch().getBranchCode(), 
                    l_ifoTradedProductImpl);                
            }                                  
            
            //������       
            Date l_datBizDate = null;       
            Date l_datTmpBizDate = null;        
            double l_dblStrikePrice = 0;        
            if (l_request.marketCode != null        
                && l_request.targetProductCode != null      
                && l_request.delivaryMonth != null)     
            {       
                // �敨OP�����I�u�W�F�N�g���擾����B      
                try     
                {       
                    l_ifoProductImpl=         
                    l_ifoProductManagerImpl.getIfoProduct(        
                        l_institution,  
                        l_request.targetProductCode,    
                        l_request.delivaryMonth,    
                        IfoDerivativeTypeEnum.FUTURES,  
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
            
                // ������ԊǗ�.reset�����R�[�h(�敨OP�w���}�X�^�I�u�W�F�N�g.�����Y�����R�[�h)���R�[������B        
                WEB3GentradeTradingTimeManagement.resetProductCode(l_request.targetProductCode);        
                l_datTmpBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();      
    
                // ���������擾      
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
                            IfoDerivativeTypeEnum.FUTURES,
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
            
                //validate�戵�\�w��        
                //[����]      
                //���X�R�[�h�F �⏕�����Dget����X()�DgetBranchCode()�̕Ԃ�l      
                //�敨OP��������F �敨OP��������I�u�W�F�N�g      
                l_ifoOrderManagerReusableValidations.validateHandlingIndex(     
                        l_genTradeMainAccount.getBranch().getBranchCode(), 
                        l_ifoTradedProductImpl);                
            }   

            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            
            //get�敨�I�v�V�����V�K���\�z
            //[����]
            //�⏕�����F get�⏕����()�̖߂�l
            //is�����F�@@���N�G�X�g.���敪���h�����h�̏ꍇ��true�A�h�����h�̏ꍇ��false�B
            //�����F�@@���N�G�X�g.�����R�[�h != null�̏ꍇ�Aget����()�̖߂�l�B�ȊO�Anull�B
            boolean l_blnLongFlg = 
                WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType) ? true : false;
                
            Double  l_dblTradingPower = null;
            
            // <����>�@@
            //�P�j���N�G�X�g.�����R�[�h�@@!��null
            //�Q�j�ȉ��̃��N�G�X�g�f�[�^ != null ���N�G�X�g.����s��  ���N�G�X�g.�w�����  ���N�G�X�g.����
            if (l_request.futProductCode != null 
                    || (l_request.marketCode !=null && l_request.targetProductCode != null && l_request.delivaryMonth != null))
            {
                l_dblTradingPower = 
                    l_tradingPowerService.getFuturesOptionTradingPower(
                        (WEB3GentradeSubAccount)l_subAccount, 
                        l_blnLongFlg, 
                        (IfoProduct)l_ifoProductImpl);
            }
            else
            {
                l_dblTradingPower = 
                    l_tradingPowerService.getFuturesOptionTradingPower(
                        (WEB3GentradeSubAccount)l_subAccount, 
                        l_blnLongFlg, null);
            }            
            
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
            
            //�Ǌԋ߂̎s��R�[�h���擾����B
            String[] l_tradeCloseMarkets = 
                WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_branch, WEB3FuturesOptionDivDef.FUTURES);

            //�戵�\��������(String, ProductTypeEnum, String)
            WEB3GentradeHandlingOrderCond l_handingOrderCond =
                new WEB3GentradeHandlingOrderCond(l_institution.getInstitutionCode(), ProductTypeEnum.IFO, WEB3FuturesOptionDivDef.FUTURES, WEB3MarginTradingDivDef.DEFAULT);

            if (l_request.futProductCode != null
                || (l_request.marketCode != null
                    && l_request.targetProductCode != null
                    && l_request.delivaryMonth != null))
            {
                //set����ŏI��(����ŏI�� : Date)
                //�m�����n�������.getLastTradingDate()�̖߂�l
                //����ŏI�����Z�b�g����.
                l_handingOrderCond.setTradingEndDate(l_ifoTradedProductImpl.getLastTradingDate());
            }

            //            //���b�Z�[�W �戵�\�����P���敪�擾
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
			l_strPossibleExecCond = l_futuresOrderManagerImpl.getHandlingPossibleExecConds(l_orderUnitDivision,l_strPossibleExecCond);

            //�戵�\���������敪���擾����
            String[] l_strHandlingPossible = l_handingOrderCond.getHandlingPossibleExpirationDateType();
            //�戵�\�����������擾����            
            String[] l_strHandingPossibleOrderCond = l_handingOrderCond.getHandlingPossibleOrderCond();

            //(���X�w����)�戵�����I�u�W�F�N�g���擾����
            int l_intLength = 0;
            WEB3GentradeBranchIndexDealtCond[] l_handLingCondBranchIndex;
            l_handLingCondBranchIndex =
                WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
                    l_institution.getInstitutionCode(), l_branch.getBranchCode(), WEB3FuturesOptionDivDef.FUTURES);
            
            if (l_handLingCondBranchIndex == null || l_handLingCondBranchIndex.length == 0)
            {
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,"(���X�w����)�戵�����I�u�W�F�N�g���擾�ł��܂���");
            }
            
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

            //�擾�����S�Ă�(���X�w����)�戵�����I�u�W�F�N�g�ɑ΂����[�v����
            for (int i = 0; i < l_intLength; i++)
            {
                //�敨OP�w���}�X�^�I�u�W�F�N�g���擾���� 
                WEB3IfoIndexMaster l_indexMaster = new WEB3IfoIndexMaster(l_handLingCondBranchIndex[i].getUnderlyingProductCode(), WEB3FuturesOptionDivDef.FUTURES); //DateFindException

                //������ԊǗ�.reset�����R�[�h(�敨OP�w���}�X�^�I�u�W�F�N�g.�����Y�����R�[�h)���R�[������B
                WEB3GentradeTradingTimeManagement.resetProductCode(((IfoIndexMasterRow) l_indexMaster.getDataSourceObject()).getUnderlyingProductCode());

                l_datTmpBizDate = WEB3DateUtility.toDay( WEB3GentradeTradingTimeManagement.getOrderBizDate());
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

            List l_lisMonthOfDeliverys =
                l_ifoProductManagerImpl.getDeliveryMonthList(
                    l_handLingCondBranchIndex, WEB3FuturesOptionDivDef.FUTURES);

            //�����J�n��
            Date l_datStartDay = null;

            //�����ŏI��
            Date l_datEndDay = null;

            //�����������j���ꗗ
            Date[] l_datHoliday = null;

            //(*3)����t���[
            //is�o����܂Œ����戵�\<����ŏI���l��>( )��true�̏ꍇ�͈ȉ��̏��������{����
            if (l_handingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering())
            {
                if (l_request.futProductCode == null)
                {
                    //get�o����܂Œ����J�n��<����ŏI���l��>(Date)
                    //�����ŏI�����l�������o����܂Œ����J�n�����擾����B
                    //[����]
                    //�o����܂Œ����J�n���F�@@�V�[�P���X�}�̃m�[�g�Q��
                    l_datStartDay = l_handingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(l_datBizDate);

                    //get�o����܂Œ����ŏI��<����ŏI���l��>(Date)
                    //�����ŏI�����l�������o����܂Œ����ŏI�����擾����B
                    //[����]
                    //�o����܂Œ����ŏI���F�@@�V�[�P���X�}�̃m�[�g�Q��
                    l_datEndDay = l_handingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(l_datBizDate);

                    // get�����������j���ꗗ(Date)
                    l_datHoliday = l_handingOrderCond.getExpirationDateHoliday(l_datBizDate);
                }
                else
                {
                    //get�o����܂Œ����J�n��<����ŏI���l��>(Date)
                    //�����ŏI�����l�������o����܂Œ����J�n�����擾����B
                    //[����]
                    //�o����܂Œ����J�n���F�@@null
                    l_datStartDay = l_handingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(null);

                    //get�o����܂Œ����ŏI��<����ŏI���l��>(Date)
                    //�����ŏI�����l�������o����܂Œ����ŏI�����擾����B
                    //[����]
                    //�o����܂Œ����ŏI���F�@@null
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
            if ((l_request.futProductCode !=null)||   
                ((l_request.marketCode != null 
                && l_request.targetProductCode != null 
                && l_request.delivaryMonth != null))) 
            { 
                //���������擾����
                FinApp finApp = (FinApp) Services.getService(FinApp.class);
                WEB3QuoteDataSupplierService l_quoteDataSupplierService = 
                    (WEB3QuoteDataSupplierService) finApp.getTradingModule(ProductTypeEnum.IFO).getQuoteDataSupplierService();
                l_quoteDataSupplierService.getIfoQuote(l_ifoTradedProductImpl, RealType.DELAY);

                WEB3IfoQuoteData l_quoteDataImpl = null;
                if (l_genTradeMainAccount.isRealCustomer())
                {
                    l_quoteDataImpl = 
                        l_quoteDataSupplierService.getIfoQuote(l_ifoTradedProductImpl, RealType.REAL);
                }
                else
                {
                    l_quoteDataImpl = 
                        l_quoteDataSupplierService.getIfoQuote(l_ifoTradedProductImpl, RealType.DELAY);                    
                }

                //����
                l_dblCurrentPrice = l_quoteDataImpl.getCurrentPrice();

                log.debug("l_dblCurrentPrice = " + l_quoteDataImpl.getCurrentPrice());
                
                //����(���ݒl)�������擾����
                l_currentPriceTime = l_quoteDataImpl.getCurrentPriceTime();

                //�O������擾����
                l_dblChange = l_quoteDataImpl.getChange();
            }
            
            //W�w�l�p�̎��s�����ꗗ���擾����B
            String[] l_strWLimitExecutionConditionTypeList = 
                WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(l_strPossibleExecCond, l_strHandingPossibleOrderCond);
            
            WEB3FuturesOpenMarginInputResponse l_marginInputResponse = 
                (WEB3FuturesOpenMarginInputResponse) l_request.createResponse();
                
            //���X�|���X�D�����P���敪�ꗗ�@@���@@�戵�\�����P���敪�擾�̕Ԃ�l
            l_marginInputResponse.orderPriceDivList = l_orderUnitDivision;

            //���X�|���X�D���s�����ꗗ�@@���@@�戵�\���s�����擾�̕Ԃ�l
            l_marginInputResponse.execCondList = l_strPossibleExecCond;

            //���X�|���X�D���������敪�ꗗ�@@���@@�戵�\���������敪�擾�̕Ԃ�l
            l_marginInputResponse.expirationDateTypeList = l_strHandlingPossible;

            //���X�|���X�D�L�������J�n���@@���@@get�o����܂Œ����J�n���̕Ԃ�l�@@
            //��is�o����܂Œ����戵�\<����ŏI���l��>( )��false�̏ꍇ��NULL�ɐݒ�
            l_marginInputResponse.expirationStartDate = WEB3DateUtility.toDay(l_datStartDay);

            //���X�|���X�D�L�������ŏI���@@���@@get�o����܂Œ����ŏI��<����ŏI���l��>�̕Ԃ�l
            //��is�o����܂Œ����戵�\<����ŏI���l��>( )��false�̏ꍇ��NULL�ɐݒ�
            l_marginInputResponse.expirationEndDate = WEB3DateUtility.toDay(l_datEndDay);

            //���X�|���X�D�L���������j���ꗗ�@@���@@get�����������j���ꗗ�̕Ԃ�l�@@
            //��is�o����܂Œ����戵�\<����ŏI���l��>( )��false�̏ꍇ��NULL�ɐݒ�
            l_marginInputResponse.holidayList = l_datHoliday;

            //���X�|���X�D���������敪�ꗗ�@@���@@�戵�\���������擾�̕Ԃ�l
            l_marginInputResponse.orderCondTypeList = l_strHandingPossibleOrderCond;
            
            //���X�|���X�D�v�w�l�p���s�����ꗗ�@@���@@�敨OP�f�[�^�A�_�v�^.get�v�w�l�p���s�����ꗗ()�̕Ԃ�l
            l_marginInputResponse.wlimitExecCondList = l_strWLimitExecutionConditionTypeList;
            
            //���X�|���X�D����敪 = ������ԊǗ�.get����敪()
            l_marginInputResponse.sessionType = WEB3GentradeTradingTimeManagement.getSessionType();

            //�V�K���\�z�͖߂�l���}�C�i�X�l�̏ꍇ�̂�0�ɕϊ����Đݒ肵�Ă��������B
            //�ȊO�̏ꍇ�́A�߂�l�����̂܂ܐݒ肷��悤�ɂ��Ă��������B
            if (l_dblTradingPower != null)
            {
                double l_dblPower = 0;
                l_dblPower = l_dblTradingPower.doubleValue();

                if (l_dblPower < 0)
                {
                    l_dblPower = 0;
                }

                //���X�|���X�D�V�K���\�z�@@���@@get�敨�I�v�V�����V�K���\�z�̕Ԃ�l ���߂�l���}�C�i�X�l�̏ꍇ��0�ɐݒ�
                l_marginInputResponse.futTradingPower = WEB3StringTypeUtility.formatNumber(l_dblPower);
            }
            else
            {
                l_marginInputResponse.futTradingPower = null;
            }
            
            //get�s��ǌx���w���̕Ԃ�l
            l_marginInputResponse.messageSuspension = l_tradeCloseMarkets;
            
            //���X�|���X�D����s��ꗗ�@@���@@(���X�w����)�戵�����I�u�W�F�N�g�D�s��R�[�h�@@
            l_marginInputResponse.marketList = l_strMarkertsList;

            //���X�|���X�D�w����ʈꗗ�@@���@@(���X�w����)�戵�����I�u�W�F�N�g�D�����Y�����R�[�h
            l_marginInputResponse.targetProductList = l_strUnderlyingProductsList;

            //���X�|���X�D�����ꗗ�@@���@@�敨OP�v���_�N�g�}�l�[�W���Dget�����ꗗ�i�j�̕Ԃ�l
            l_marginInputResponse.delivaryMonthList = l_strMonthOfDeliverys;
            
            if (l_request.marketCode != null 
                && l_request.targetProductCode != null 
                && l_request.delivaryMonth != null)
            {
                //�������
                l_marginInputResponse.futProductCode = 
                    ((IfoProductRow)l_ifoTradedProductImpl.getProduct().getDataSourceObject()).getProductCode();

                //����s��
                l_marginInputResponse.marketCode = l_request.marketCode;

                //�w�����    
                l_marginInputResponse.targetProductCode = l_request.targetProductCode;

                //����
                l_marginInputResponse.delivaryMonth = l_request.delivaryMonth;

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
                if (l_request.futProductCode == null)
                {
                    //�������R�[�h��null�̏ꍇ��null���Z�b�g
                    //���X�|���X�DgetProductCode()
                    l_marginInputResponse.futProductCode = null;

                    //���X�|���X�D�����Y�����R�[�h    
                    l_marginInputResponse.targetProductCode = null;

                    //���X�|���X�D�s��R�[�h
                    l_marginInputResponse.marketCode = null;

                    //�敨�����I�u�W�F�N�g�D�����@@
                    l_marginInputResponse.delivaryMonth = null;

                    //���X�|���X�D���ݒl�@@���@@null
                    l_marginInputResponse.currentPrice = null;

                    //���X�|���X�D������ԁ@@���@@null
                    l_marginInputResponse.currentPriceTime = null;

                    //���X�|���X�D�O����@@���@@null 
                    l_marginInputResponse.comparedPreviousDay = null;
                }
                else
                {
                    //���X�|���X�DgetProductCode()
                    l_marginInputResponse.futProductCode = l_ifoProductImpl.getProductCode();

                    //���X�|���X�D�w�����   
                    l_marginInputResponse.targetProductCode = l_ifoProductImpl.getUnderlyingProductCode();

                    //���X�|���X�D�s��R�[�h
                    l_marginInputResponse.marketCode = l_market.getMarketCode();

                    //���X�|���X�D�����@@
                    l_marginInputResponse.delivaryMonth = l_ifoProductImpl.getMonthOfDelivery();

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

            l_hmMonthOfDeliverys.clear();
            l_hmMarkertList.clear();
            l_vecMonthOfDeliverys.clear();

            log.exiting(STR_METHOD_NAME);
            return l_marginInputResponse;
        }
        catch (DataQueryException l_edqx)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_edqx);
            log.exiting(STR_METHOD_NAME);

            l_hmMonthOfDeliverys.clear();
            l_vecMonthOfDeliverys.clear();
            l_hmMarkertList.clear();

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                STR_METHOD_NAME, 
                l_edqx.toString(), 
                l_edqx);
        }
        catch (DataNetworkException l_edw)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_edw);
            log.exiting(STR_METHOD_NAME);

            l_hmMonthOfDeliverys.clear();
            l_hmMarkertList.clear();
            l_vecMonthOfDeliverys.clear();

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

            l_hmMonthOfDeliverys.clear();
            l_hmMarkertList.clear();
            l_vecMonthOfDeliverys.clear();

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                STR_METHOD_NAME, 
                l_enf.toString(), 
                l_enf);
        }
    }

    /**
     * (create�����I�����)<BR>
     * �����w���敨�V�K�������I����ʂ�\������B<BR>
     * <BR>
     * �u(�敨�V�K������)�����I����ʕ\���f�[�^�擾�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3FuturesProductSelectResponse
     * @@roseuid 40A854F5014A
     */
    protected WEB3FuturesProductSelectResponse createProductSelect(WEB3FuturesProductSelectRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createProductSelectScreen(WEB3FuturesProductSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //���b�Z�[�W validate( )
        l_request.validate();

        //�⏕�������擾����B
        SubAccount l_subAccount = getSubAccount();

        //validate�������Ăяo���B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3FuturesOrderManagerImpl l_futuresOrderManagerImpl =
            (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        l_futuresOrderManagerImpl.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

        WEB3IfoProductManagerImpl l_ifoProductManagerImpl =
            (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        //FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        //�Ǌԋ߂̎s��R�[�h���擾����B(���X, ProductTypeEnum, String)
        String[] l_tradeCloseMarkets = 
            WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_branch, WEB3FuturesOptionDivDef.FUTURES);

        //�擾�،����
        Institution l_institution = l_subAccount.getInstitution();

        //(���X�w����)�戵�����I�u�W�F�N�g���擾����            
        WEB3GentradeBranchIndexDealtCond[] l_handLingCondBranchIndex;

        l_handLingCondBranchIndex = 
            WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
                l_institution.getInstitutionCode(), l_branch.getBranchCode(), WEB3FuturesOptionDivDef.FUTURES);

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

        for (int i = 0; i < l_intLength; i++)
        {
            l_strMatketCode = 
                ((BranchIndexDealtCondRow) l_handLingCondBranchIndex[i].getDataSourceObject()).getMarketCode();
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

        List l_lisMonthOfDeliverys =
            l_ifoProductManagerImpl.getDeliveryMonthList(
                l_handLingCondBranchIndex,WEB3FuturesOptionDivDef.FUTURES);

        //���X�|���X�f�[�^�𐶐�����B
        WEB3FuturesProductSelectResponse l_marginSelectResponse = 
            (WEB3FuturesProductSelectResponse) l_request.createResponse();

        //�s��ꗗ
        String[] l_strMarkertsList = new String[l_lisMarkets.size()];
        l_lisMarkets.toArray(l_strMarkertsList);

        //�����ꗗ
        String[] l_strMonthOfDeliverys = new String[l_lisMonthOfDeliverys.size()];
        l_lisMonthOfDeliverys.toArray(l_strMonthOfDeliverys);

        //�w����ʈꗗ
        String[] l_strUnderlyingProductsList = new String[l_lisUnderlyingProducts.size()];
        l_lisUnderlyingProducts.toArray(l_strUnderlyingProductsList);
        //���X�|���X�D�V�K���\�z�@@���@@null
        l_marginSelectResponse.futTradingPower = null;
        l_marginSelectResponse.delivaryMonthList = l_strMonthOfDeliverys;
        l_marginSelectResponse.marketList = l_strMarkertsList;
        l_marginSelectResponse.targetProductList = l_strUnderlyingProductsList;
        l_marginSelectResponse.messageSuspension = l_tradeCloseMarkets;

        log.exiting(STR_METHOD_NAME);
        return l_marginSelectResponse;
    }

}
@
