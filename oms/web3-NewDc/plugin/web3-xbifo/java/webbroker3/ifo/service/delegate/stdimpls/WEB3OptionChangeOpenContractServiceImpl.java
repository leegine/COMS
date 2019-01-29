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
filename	WEB3OptionChangeOpenContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�����V�K���T�[�r�XImpl(WEB3OptionChangeOpenContractServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/17 ���o�� (���u) �V�K�쐬
              001: 2004/07/22 ���Ō� (���u) WEB3ExecutionConditionDef��WEB3IfoExecCondTypeDef�������ւ���
              002: 2004/07/22 ���Ō� (���u) WEB3OrderingConditionDef��WEB3IfoOrderCondTypeDef�������ւ���
              003: 2004/07/23 ���Ō� (���u) WEB3TransactionTypeSONARDef��WEB3IfoTransactionTypeDef�������ւ���
              004: 2004/07/26 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000018
              005: 2004/07/30 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000074
              006: 2004/07/30 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000078�A79
              007: 2004/07/30 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000080�A100              
              008: 2004/08/09 ���Ō� (Sinocom) �Ή�����:�yWEB3-XBIFO-A-CD-0082�z
              009: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
              010: 2004/08/14 ���Ō� �Ή� �y�����w���I�v�V�����z�\�[�X�R�[�h�`�F�b�N�w�E����(JP)20040802 No020
              011: 2004/08/15 ������@@(���u) STBUG(IFO_ST-000083)��Ή�
              012: 2006/7/13 ���G�� (���u) �d�l�ύX�@@���f��469�A474�A478
              013: 2006/08/10 �юu�� (���u) �d�l�ύX ���f��545
              014: 2006/08/30 ������  (���u) ���f��No.551�Ή�
              015: 2006/08/31 ������  (���u) ���f��No.552�Ή�
              016: 2006/11/28 �����  (���u) ���f��No.573�Ή�
              017: 2006/11/30 �����  (���u) ���f��No.584,585�Ή�
              018: 2006/12/06 �����  (���u) ���f��No.592,594�Ή�
Revesion History : 2007/06/11 �Ј��� (���u) �d�l�ύX���f��No.672
Revesion History : 2007/11/20 �����q (���u) �d�l�ύX���f��No.804, No.815, No.819
Revesion History : 2008/04/14 �����F(���u) ���f��856 869
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoOpenContractChangeUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3OptionChangeOpenContractService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (OP�����V�K���T�[�r�XImpl)<BR>
 * <BR>
 * �����w���I�v�V���������V�K���T�[�r�X�����N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3OptionChangeOpenContractServiceImpl extends WEB3OptionClientRequestService implements WEB3OptionChangeOpenContractService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionChangeOpenContractServiceImpl.class);
    
    /**
     * @@roseuid 40C0BF99037A
     */
    public WEB3OptionChangeOpenContractServiceImpl() 
    {
     
    }
    
    /**
     * �����w���I�v�V���������V�K���T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�܂��́A<BR>
     * submit����()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056BC690359
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            getClass().getName() + ".execute(WEB3GenRequest)";

        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3OptionsOpenMarginChangeConfirmRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^��
            //�u�����w���I�v�V���������V�K���m�F���N�G�X�g�v�̏ꍇ
            log.debug("validateOrder���\�b�h�����s����");
                l_response = validateOrder(
                    (WEB3OptionsOpenMarginChangeConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3OptionsOpenMarginChangeCompleteRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^��
            //�u�����w���I�v�V���������������ڍ׃��N�G�X�g�v�̏ꍇ
            log.debug("submitOrder���\�b�h�����s����");
            l_response = submitOrder(
                (WEB3OptionsOpenMarginChangeCompleteRequest)l_request);
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate����)
     * �����w���I�v�V�����̒����V�K�������R�����s���B<BR>
     * <BR>
     * �u�iOP�����V�K���T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * <BR>
     * =============================================== <BR>
     * �V�[�P���X�} : �iOP�����V�K���T�[�r�X�jvalidate�����Q <BR>
     * ��̈ʒu     : 1.15 �߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B <BR>
     * class        : WEB3BusinessLayerException <BR>
     * tag          : BUSINESS_ERROR_01306 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - �����w���I�v�V���������V�K���m�F���N�G�X�g
     * @@return webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056BC930116
     */
    protected WEB3OptionsOpenMarginChangeConfirmResponse validateOrder(WEB3OptionsOpenMarginChangeConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = getClass().getName() + ".validateOrder(WEB3OptionsCloseMarginChangeConfirmRequest)";
        
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
        WEB3OptionOrderManagerImpl l_orderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        try
        {
            //1.validate
            log.debug("l_request.validate()");
            l_request.validate();
            
            //2.������ԊǗ�����get������
            log.debug("������ԊǗ�����get������");
            Date l_dateOrderBizDate = null;
            l_dateOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            //3.IfoOrderImpl
            log.debug("IfoOrderImpl");
            log.debug("l_request.id =" + l_request.id);

            OrderUnit l_orderUnit = l_orderManagerImpl.getOrderUnits(Long.parseLong(l_request.id))[0];
            
            //4.create�V�K���������e(long, long, double, double, 
            //IfoOrderExecutionConditionType, Date, Date, String, String, String, 
            //double, double, IfoOrderExecutionConditionType, String, String)
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec = null;
            
            log.debug("�V�K���������e");
            
            // ����ID�F�@@�����P��.getOrderId() 
            long l_lngOrderId = l_orderUnit.getOrderId();
            log.debug("getOrderUnitId()"); 
            
            // �����P�ʂh�c�F�@@�����P��.getOrderUnitId() 
            long l_lngOrderUnitId = l_orderUnit.getOrderUnitId();
            log.debug("l_lngOrderUnitId = " + l_lngOrderUnitId);
          
            //�����㐔�ʁF�@@���N�G�X�g�f�[�^.���� 
            double l_dblOpOrderQuantity = 0.0D;
            if (l_request.opOrderQuantity != null)
            {
                l_dblOpOrderQuantity = Double.parseDouble(l_request.opOrderQuantity);
            }
            
            // ������w�l�F�@@���N�G�X�g�f�[�^.�����P�� 
            double l_dblLimitPrice = 0.0D;
            if (l_request.limitPrice != null)
            {
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
            }
            
            // �������s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����) 
            IfoOrderExecutionConditionType l_changeExecCondType = 
            	WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);

            // OP�����}�l�[�W��
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
                (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getOrderManager();

            // �����������F
            // ���N�G�X�g�f�[�^.�����L������ != null�̏ꍇ�AOP�����}�l�[�W��.get�����L
            // ������(���N�G�X�g�f�[�^.�����L������,�敨OP����(*1).�����R�[�h,�s��
            // .getMarketCode(),�h�I�v�V�����h)�̖߂�l
            // �@@���N�G�X�g�f�[�^.�����L������ == null�̏ꍇ�Aget������()�̖߂�l
            Date l_datchangeExpirationDate = null;
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
            if (l_request.expirationDate != null)
            {
                // �敨OP�v���_�N�g�}�l�[�W��.getProduct(�����P��.����ID)�ɂĎ擾
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3IfoProductManagerImpl l_productMgr =
                    (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
                Product l_product = null;
                try
                {
                    l_product = l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_product;
                // �敨OP����(*1).�����R�[�h
                String l_strProductCode = l_ifoProductImpl.getProductCode();
                // �s��.getMarketCode()
                Market l_market = l_ifoProductImpl.getPrimaryMarket(); 
                String l_strMarketCode = l_market.getMarketCode();

                l_datchangeExpirationDate = l_optionOrderManagerImpl.getExpirationDate(
                    l_request.expirationDate,
                    l_strProductCode,
                    l_strMarketCode,
                    WEB3FuturesOptionDivDef.OPTION);
            }
            else
            {
                log.debug("l_request.expirationDate <> null");
                l_datchangeExpirationDate = l_dateOrderBizDate;
            }

            // ���������F�@@���N�G�X�g�f�[�^.���������敪 
            String l_strOrderCondType = l_request.orderCondType;
            
            // �����������Z�q�F�@@ 
            // �@@���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p�����������Z�q 
            // �@@���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p�����������Z�q
            String l_strOrderCondOperator = null;
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
            	l_strOrderCondOperator = l_request.stopOrderCondOperator;
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
            	l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
            }
           
            //�t�w�l��l�^�C�v�F�@@ 
            //���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p�v���~�A���^�����Y���i 
            //���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p�v���~�A���^�����Y���i
            String l_strStopPriceType = null;
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {               
                l_strStopPriceType = l_request.stopPremium_underlyingAssets;      
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {

                    l_strStopPriceType = l_request.wlimitPremium_underlyingAssets;
            }
            
            // �t�w�l��l�F�@@ 
            // �@@���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p���������P�� 
            // �@@���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p���������P�� 
            double l_dblStopOrderPrice = 0.0D;
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                if (l_request.stopOrderCondPrice != null)
                {
                    l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
                }            	
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                if (l_request.wlimitOrderCondPrice != null)
                {
                    l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
                } 
            }
            
            // �iW�w�l�j�����w�l�F�@@���N�G�X�g�f�[�^.W�w�l�p�����P�� 
            double l_dblWLimitOrderChange = 0.0D;
            if (l_request.wLimitPrice != null)
            {
                l_dblWLimitOrderChange = Double.parseDouble(l_request.wLimitPrice);
            }
            
            // �iW�w�l�j���s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.�v�w�l�p���s����)   
            IfoOrderExecutionConditionType l_wlimitExecCondType = 
            	WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
            
            // �iW�w�l�j�L����ԋ敪�F�@@���N�G�X�g�f�[�^.�v�w�l�p�L����ԋ敪 
            String l_strWlimitEnableStatusDiv = l_request.wlimitEnableStatusDiv;
            
            // �����㒍�������敪�F�@@���N�G�X�g�f�[�^.���������敪
            String l_strExpirationDateType = l_request.expirationDateType;
            
            //�[��O�J�z�Ώۃt���O�F�@@�敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O
            //(���N�G�X�g�f�[�^.���������敪, �����P��.���XID)�̖߂�l
            boolean l_blnEveningSessionOrderFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_strExpirationDateType, l_orderUnit.getBranchId());

            l_ifoOpenContractChangeSpec = 
            	WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
		    		l_lngOrderId, 
		    		l_lngOrderUnitId, 
		    		l_dblOpOrderQuantity, 
		    		l_dblLimitPrice,
		    		l_changeExecCondType,
		    		l_datchangeExpirationDate,
                    l_dateOrderBizDate,
		    		l_strOrderCondType,
		    		l_strOrderCondOperator,
		    		l_strStopPriceType,
		    		l_dblStopOrderPrice,
		    		l_dblWLimitOrderChange,
		    		l_wlimitExecCondType,
                    l_strWlimitEnableStatusDiv,      		
		    		l_strExpirationDateType,
                    l_blnEveningSessionOrderFlag);

            //5.get�⏕����( )
            log.debug("get�⏕����( )");
            SubAccount l_subAccount = this.getSubAccount();
            WEB3GentradeSubAccount l_GentradeSubAccount = null;
            l_GentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
                
            //6.validate�V�K����������
            log.debug("l_subAccount = " + l_subAccount);
            log.debug("l_WEB3IfoOpenContractChangeSpec = " + l_ifoOpenContractChangeSpec);
            log.debug("validate�V�K����������");

            OrderValidationResult l_result = null;
            l_result = l_orderManagerImpl.validateChangeOrder(l_GentradeSubAccount,l_ifoOpenContractChangeSpec);
            
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_result.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    STR_METHOD_NAME);                
            }
			            
			//7.1�iOP�����V�K���T�[�r�X�jvalidate�����Q �i�Q�Ɓj
            //7.2.getTradedProduct
            log.debug("getTradedProduct");
            TradedProduct l_tradedProduct = null;
            l_tradedProduct = l_orderUnit.getTradedProduct();
                
            //7.3.getDataSourceObject
            log.debug("getDataSourceObject");
            IfoOrderUnitRow l_orderUnitRow = null;
            l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //7.4.getAfterChangeOriginalQuantity
            log.debug("getAfterChangeOriginalQuantity");
            double l_afterChangeOriginalQuantity = 0;
            l_afterChangeOriginalQuantity = l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity();

            if (Double.isNaN(l_afterChangeOriginalQuantity))
            {
                l_afterChangeOriginalQuantity = 0D;
            }

            //7.5.getSide
            log.debug("getSide");
            SideEnum l_side = null;
            l_side = l_orderUnit.getSide();
                
            //7.6.getExecutedQuantity
            log.debug("getExecutedQuantity");
            double l_dblExecutedQuantity = 0;
            l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();

            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0;        
            }
                            
            //7.7.getExecutedAmount
            log.debug("getExecutedAmount");
            double l_dblExecutedAmount = 0;
            l_dblExecutedAmount = l_orderUnit.getExecutedAmount();
                        
            if (Double.isNaN(l_dblExecutedAmount))
            {
                l_dblExecutedAmount = 0;        
            }
            
            //7.8.�萔��
            //�萔���I�u�W�F�N�g�𐶐�����B
            log.debug("�萔��");
            WEB3GentradeCommission l_gentradeCommission = null;
            l_gentradeCommission = new WEB3GentradeCommission();
                
            //7.9.(*1)�v���p�e�B�Z�b�g 
            //�����`���l�����Z�b�g����B
            log.debug("�����`���l�����Z�b�g����B");
            l_gentradeCommission.setOrderChannel(this.getLoginChannel());
            
            //�،����ID���Z�b�g����B
            log.debug("�،����ID���Z�b�g����B");
            l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            
            //���XID���Z�b�g����B
            log.debug("���XID���Z�b�g����B");
            log.debug("BranchId() =" + l_subAccount.getMainAccount().getBranch().getBranchId());
            l_gentradeCommission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
            
            //���������Z�b�g����B
            log.debug("���������Z�b�g����B");
            l_gentradeCommission.setOrderBizDate(new Timestamp(l_dateOrderBizDate.getTime()));
            
            //����R�[�h�iSONAR�j���Z�b�g����B
            log.debug("����R�[�h���Z�b�g����B");
            l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);            
          
            //�萔�����i�R�[�h���Z�b�g����B
            log.debug("�萔�����i�R�[�h���Z�b�g����B");
            log.debug("CommissionProductCode =" + WEB3CommisionProductCodeDef.INDEX_OP);
            l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
            
            //�����������`���l�����Z�b�g����B
            log.debug("�����������`���l�����Z�b�g����B");
            l_gentradeCommission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());
            
            //�������萔��No���Z�b�g����B
            log.debug("�������萔��No���Z�b�g����B");
            l_gentradeCommission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());
            
            //�������萔��No�}�Ԃ��Z�b�g����B
            log.debug("�������萔��No�}�Ԃ��Z�b�g����B");
            l_gentradeCommission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());
            
            //�ٍϋ敪���Z�b�g����B                 
            l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);   

            boolean l_blnIsLimitPrice = false;
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                //���s
                l_blnIsLimitPrice = false;
            }
            else
            {
                //�w�l
                l_blnIsLimitPrice = true;
            }
            l_gentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);
            
            //�萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
            l_gentradeCommission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_tradedProduct.getProduct()).getUnderlyingProductCode());
            
            //�萔��.���� = �V�K���������e.getAfterChangeOriginalQuantity()
            l_gentradeCommission.setQuantity(l_afterChangeOriginalQuantity);
            
            //7.10.calc�������T�Z��n���
            log.debug("calc�������T�Z��n���1");
            log.debug("l_gentradeCommission = " + l_gentradeCommission);
            log.debug("l_subAccount = " + l_subAccount);
            log.debug("l_tradedProduct = " + l_tradedProduct);
            log.debug("l_WEB3IfoOpenContractChangeSpec = " + l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity());
            log.debug("l_side = " + l_side);
            log.debug("l_dblExecutedQuantity = " + l_dblExecutedQuantity);
            log.debug("l_dblExecutedAmount = " + l_dblExecutedAmount);
                        
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = new WEB3IfoTradedProductImpl(l_tradedProduct.getTradedProductId());
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = null;
            WEB3IfoEstimateDeliveryAmountCalcResult l_wEstimateDeliveryAmountCalcResult = null;
            l_estimateDeliveryAmountCalcResult = 
                l_orderManagerImpl.calcChangeEstimateDeliveryAmount(
                    l_gentradeCommission,l_dblLimitPrice,
                    l_GentradeSubAccount,
                    l_ifoTradedProductImpl,
                    l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity(),
                    l_side,
                    false,
                    l_dblExecutedQuantity,
                    l_dblExecutedAmount,
                    false);
            
			//7.11.(*2)�v���p�e�B�Z�b�g
			//�����A���A���~�b�g�������L����W�w�l�����̏ꍇ
			//(�����P��.getSide() == "BUY" &&�@@���N�G�X�g�f�[�^.�v�w�l�p�L����ԋ敪 == "���~�b�g�����L��" 
            //���� �V�K�������������e.��������() == "W�w�l")
			//�������s���B
            WEB3GentradeCommission l_wGentradeCommission = new WEB3GentradeCommission();
            if (SideEnum.BUY.equals(l_side) 
                && WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(
            		l_request.wlimitEnableStatusDiv)
                && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
            		l_ifoOpenContractChangeSpec.getOrderCond()))
            {
				//7.11.1.[setIs�w�l()�Ɏw�肷�����] 
				// is�w�l�F�@@�V�K���������e.(W�w�l)�����w�l != 0�̏ꍇ�Atrue�B�ȊO�Afalse�B
                if (l_ifoOpenContractChangeSpec.getWLimitPriceChange() != 0)
                {
                    l_wGentradeCommission.setIsLimitPrice(true);
                }
                else
                {
                    l_wGentradeCommission.setIsLimitPrice(false);
                }

                l_wGentradeCommission.setOrderChannel(this.getLoginChannel());

                //�،����ID���Z�b�g����B
                log.debug("�،����ID���Z�b�g����B");
                l_wGentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

                //���XID���Z�b�g����B
                log.debug("���XID���Z�b�g����B");
                log.debug("BranchId() =" + l_subAccount.getMainAccount().getBranch().getBranchId());
                l_wGentradeCommission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());

                //���������Z�b�g����B
                log.debug("���������Z�b�g����B");
                l_wGentradeCommission.setOrderBizDate(new Timestamp(l_dateOrderBizDate.getTime()));

                //����R�[�h�iSONAR�j���Z�b�g����B
                log.debug("����R�[�h���Z�b�g����B");
                l_wGentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);            

                //�萔�����i�R�[�h���Z�b�g����B
                log.debug("�萔�����i�R�[�h���Z�b�g����B");
                log.debug("CommissionProductCode =" + WEB3CommisionProductCodeDef.INDEX_OP);
                l_wGentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);

                //�����������`���l�����Z�b�g����B
                log.debug("�����������`���l�����Z�b�g����B");
                l_wGentradeCommission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());

                //�������萔��No���Z�b�g����B
                log.debug("�������萔��No���Z�b�g����B");
                l_wGentradeCommission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());

                //�������萔��No�}�Ԃ��Z�b�g����B
                log.debug("�������萔��No�}�Ԃ��Z�b�g����B");
                l_wGentradeCommission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());

                //�ٍϋ敪���Z�b�g����B                 
                l_wGentradeCommission.setPayType(WEB3PayTypeDef.OTHER);   

                //�萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
                l_wGentradeCommission.setUnderlyingProductCode(
                    ((WEB3IfoProductImpl)l_tradedProduct.getProduct()).getUnderlyingProductCode());

                //�萔��.���� = �V�K���������e.getAfterChangeOriginalQuantity()
                l_wGentradeCommission.setQuantity(l_afterChangeOriginalQuantity);
                
				//7.11.2.calc�������T�Z��n���(�萔��, double, SubAccount, �敨OP�������,
				//double, SideEnum, boolean, double, double, boolean)
				//[calc�������T�Z��n���()�Ɏw�肷�����] 
				// �萔���F�@@W�w�l�p�萔���I�u�W�F�N�g 
				// �w�l�F�@@�V�K���������e.(W�w�l)�����w�l 
				// �⏕�����F�@@�⏕�����I�u�W�F�N�g 
				// �敨OP��������F�@@�敨OP��������I�u�W�F�N�g 
				// ���ʁF �V�K���������e.getAfterChangeOriginalQuantity() 
				// �����F �����P��.getSide() 
				// is�ԍϒ����F�@@false 
				// ��萔�ʁF�@@�����P��.getExecutedQuantity() 
				// ���v�����z�F�@@�����P��.getExecutedAmount() 
				// isSkip���z�`�F�b�N�F�@@false 
                l_wEstimateDeliveryAmountCalcResult = 
                    l_orderManagerImpl.calcChangeEstimateDeliveryAmount(
                        l_wGentradeCommission,
                        l_ifoOpenContractChangeSpec.getWLimitPriceChange(),
                        l_GentradeSubAccount,
                        l_ifoTradedProductImpl,
                        l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity(),
                        l_side,
                        false,
                        l_dblExecutedQuantity,
                        l_dblExecutedAmount,
                        false);                
            }
            
            //7.12.get�㗝���͎�( )
            //�㗝���͎҃I�u�W�F�N�g���擾����B 
            Trader l_trader = this.getTrader();

			WEB3IfoOpenContractChangeUpdateInterceptor l_openContractChangeUpdateInterceptor =
				new WEB3IfoOpenContractChangeUpdateInterceptor(
					l_ifoOpenContractChangeSpec);

            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResultForCheck = null;

            boolean l_blnIsWLimitCommission = false;
            log.debug("�v���p�e�B�Z�b�g");
            l_openContractChangeUpdateInterceptor.setOrderCond(l_request.orderCondType);
            if (SideEnum.BUY.equals(l_side) 
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOpenContractChangeSpec.getOrderCond()))
            {
                if (l_estimateDeliveryAmountCalcResult.getRestraintTurnover() >= l_wEstimateDeliveryAmountCalcResult.getRestraintTurnover())
                {
                    l_openContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult
                        (l_estimateDeliveryAmountCalcResult);

					l_calcResultForCheck = l_estimateDeliveryAmountCalcResult;
                    l_openContractChangeUpdateInterceptor.setCommision(l_gentradeCommission);
                }
                else
                {
                    l_blnIsWLimitCommission = true;
                    l_openContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult
                      (l_wEstimateDeliveryAmountCalcResult);

                    l_calcResultForCheck = l_wEstimateDeliveryAmountCalcResult;
                    l_openContractChangeUpdateInterceptor.setCommision(l_wGentradeCommission);
                }
            }
            else
            {
                l_openContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult
                    (l_estimateDeliveryAmountCalcResult);

                l_calcResultForCheck = l_estimateDeliveryAmountCalcResult;
                l_openContractChangeUpdateInterceptor.setCommision(l_gentradeCommission);
            }
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                log.debug("WEB3IfoOrderCondTypeDef.STOP_ORDER.equals(l_request.orderCondType)");
                l_openContractChangeUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePriceType(l_request.stopPremium_underlyingAssets);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                log.debug("WEB3IfoOrderCondTypeDef.W_LIMIT_PRICE.equals(l_request.orderCondType)");
                l_openContractChangeUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePriceType(l_request.wlimitPremium_underlyingAssets);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));
            }
            l_openContractChangeUpdateInterceptor.setWLimitPriceChange(l_ifoOpenContractChangeSpec.getWLimitPriceChange());
            long l_lngTraderId = 0;
            if (l_trader != null)
            {
                l_lngTraderId = l_trader.getTraderId();
            }
            l_openContractChangeUpdateInterceptor.setTraderId(l_lngTraderId);
                          
			OpLoginSecurityService l_opLoginSec =
				(OpLoginSecurityService) Services.getService(
					OpLoginSecurityService.class);
			String l_strOrderRootDiv =
				l_opLoginSec.getSessionProperty(
					WEB3SessionAttributeDef.ORDER_ROOT_DIV);

			l_openContractChangeUpdateInterceptor.setOrderRootDiv(l_strOrderRootDiv);
			
            //7.15.validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], 
			//�������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
			//[����] 
			// �⏕�����F �⏕�����I�u�W�F�N�g 
			// �������e�C���^�Z�v�^�F �敨OP�V�K�������X�V�C���^�Z�v�^��v�f�Ƃ����z�� 
			// �������e�F �V�K���������e��v�f�Ƃ����z�� 
			// ������ʁF �����P��.������� 
			// �]�͍X�V�t���O�F false

            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_interceptorObject = new Object[1];
            l_interceptorObject[0] = l_openContractChangeUpdateInterceptor;
            Object[] l_specObject = new Object[1];
            l_specObject[0] = l_ifoOpenContractChangeSpec;
            
            WEB3TPTradingPowerResult l_tpTradingPoweResult = 
                l_tradingPowerService.validateTradingPower
                (l_GentradeSubAccount,
                l_interceptorObject,
                l_specObject,
                l_orderUnit.getOrderType(),
                false);
            if (!l_tpTradingPoweResult.isResultFlg())
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    STR_METHOD_NAME);
            }
 
                
            //7.16.WEB3GenResponse
            WEB3GenResponse l_genResponse = l_request.createResponse();
            WEB3OptionsOpenMarginChangeConfirmResponse l_response = (WEB3OptionsOpenMarginChangeConfirmResponse)l_genResponse;

            //26.get�s��ǌx���w��(���X, String)
            log.debug("get�s��ǌx���w��");
            Branch l_branch = null;
            l_branch = l_subAccount.getMainAccount().getBranch();
            String[] l_strTradeCloseSuspension = null;
            
            l_strTradeCloseSuspension = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension((WEB3GentradeBranch)l_branch,WEB3FuturesOptionDivDef.OPTION);
            //7.23.(*4)�v���p�e�B�Z�b�g
            log.debug("�v���p�e�B�Z�b�g");

            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getEstimateDeliveryAmount());
            l_response.messageSuspension = l_strTradeCloseSuspension;

            //���X�|���X.�m�F���P�� = (*)�������擾���Ă���ꍇ�i�T�Z��n����̎萔���I�u�W�F�N�g.is�w�l() == false�̏ꍇ�j�A
            //�T�Z��n����v�Z����.�v�Z�P��
            //(*)�����A���AW�w�l�����̏ꍇ�́A�؋����E�]�̓`�F�b�N�Ɏg�p�����T�Z��n�v�Z���ʂ�p����
            if (l_blnIsWLimitCommission)
            {
                if (!l_wGentradeCommission.isLimitPrice())
                {
                    l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getCalcUnitPrice());
                }
            }
            else
            {
                if (!l_gentradeCommission.isLimitPrice())
                {
                    l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getCalcUnitPrice());
                }
            }

            l_response.checkDate = WEB3DateUtility.toDay(l_dateOrderBizDate);
            l_response.commission = WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getCommission());
            l_response.commissionCourse = l_calcResultForCheck.getCommissionCourse();
            l_response.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber
                (l_calcResultForCheck.getCommissionTax());

            l_response.partExecQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);

            //���X�|���X.�����L������ = �V�K���������e.����������
            l_response.expirationDate = l_ifoOpenContractChangeSpec.getChangeExpirationDate();

            log.debug("l_response.estimatedPrice = " + l_response.estimatedPrice);
            log.debug("l_response.messageSuspension = " + l_response.messageSuspension);
            log.debug("l_response.checkPrice = " + l_response.checkPrice);
            log.debug("l_response.checkDate = " + l_response.checkDate);
                    
            log.exiting(STR_METHOD_NAME);
        
            return l_response;
             
        }  
        catch (DataQueryException l_ex)
        {            
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B",l_ex);                                    
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);                           
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B",l_ex);            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }
       
    }
    
    /**
     * �����w���I�v�V�����̒����V�K��������o�^����B<BR>
     * <BR>
     * �u�iOP�����V�K���T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * <BR>
     * =============================================== <BR>
     * �V�[�P���X�} : �iOP�����V�K���T�[�r�X�jsubmit�����Q <BR>
     * ��̈ʒu     : 1.15 �߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B <BR>
     * class        : WEB3BusinessLayerException <BR>
     * tag          : BUSINESS_ERROR_01306 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - �����w���I�v�V���������V�K���������N�G�X�g
     * @@return WEB3OptionsOpenMarginChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056BC930136
     */
    protected WEB3OptionsOpenMarginChangeCompleteResponse submitOrder
        (WEB3OptionsOpenMarginChangeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = getClass().getName() + ".submitOrder(WEB3OptionsOpenMarginChangeCompleteRequest)";
        
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
        WEB3OptionOrderManagerImpl l_orderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        try
        {
            //1.1.validate
            log.debug("validate");
            l_request.validate();
        
            //1.2.������ԊǗ�����get������
            log.debug("������ԊǗ�����get������");
            Date l_datOrderBizDate = null;
            if (l_request.checkDate == null)
            {
                l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            else
            {
                l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
            }

            //1.3.IfoOrderImpl
            OrderUnit l_orderUnit = l_orderManagerImpl.getOrderUnits(Long.parseLong(l_request.id))[0];
        
            //1.4.create�V�K���������e(long, long, double, double, 
            //IfoOrderExecutionConditionType, Date, Date, String, String, String, 
            //double, double, IfoOrderExecutionConditionType, String, String)
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec = null;
            
            log.debug("�V�K���������e");
            
            // ����ID�F�@@�����P��.getOrderId() 
            long l_lngOrderId = l_orderUnit.getOrderId();
            log.debug("getOrderUnitId()"); 
            
            // �����P�ʂh�c�F�@@�����P��.getOrderUnitId() 
            long l_lngOrderUnitId = l_orderUnit.getOrderUnitId();
            log.debug("l_lngOrderUnitId = " + l_lngOrderUnitId);
            
            // �����㐔�ʁF�@@���N�G�X�g�f�[�^.���� 
            double l_dblOpOrderQuantity = 0.0D;
            if (l_request.opOrderQuantity != null)
            {
                l_dblOpOrderQuantity = Double.parseDouble(l_request.opOrderQuantity);
            }
          
            // ������w�l�F�@@���N�G�X�g�f�[�^.�����P�� 
            double l_dblLimitPrice = 0.0D;
            if (l_request.limitPrice != null)
            {
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
            }
            
            // �������s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����) 
            IfoOrderExecutionConditionType l_changeExecCondType = 
            	WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);

            // OP�����}�l�[�W��
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
                (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getOrderManager();

            // �����������F�@@ 
            Date l_datchangeExpirationDate = null;
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
            // ���N�G�X�g�f�[�^.�����L������ != null�̏ꍇ
            if (l_request.expirationDate != null)
            {
                // �敨OP�v���_�N�g�}�l�[�W��.getProduct(�����P��.����ID)�ɂĎ擾
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3IfoProductManagerImpl l_productMgr =
                    (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
                Product l_product = null;
                try
                {
                    l_product = l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_product;
                // �敨OP����(*1).�����R�[�h
                String l_strProductCode = l_ifoProductImpl.getProductCode();
                // �s��.getMarketCode()
                Market l_market = l_ifoProductImpl.getPrimaryMarket(); 
                String l_strMarketCode = l_market.getMarketCode();
                l_datchangeExpirationDate = l_optionOrderManagerImpl.getExpirationDate(
                    l_request.expirationDate,
                    l_strProductCode,
                    l_strMarketCode,
                    WEB3FuturesOptionDivDef.OPTION);
            }
            else
            {
                log.debug("l_request.expirationDate <> null");
                l_datchangeExpirationDate = l_datOrderBizDate;
            }
            
            // �������F�@@get������()�̖߂�l 
            
            // ���������F�@@���N�G�X�g�f�[�^.���������敪 
            String l_strOrderCondType = l_request.orderCondType;
            
            // �����������Z�q�F�@@ 
            // �@@���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p�����������Z�q 
            // �@@���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p�����������Z�q
            String l_strOrderCondOperator = null;
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
            	l_strOrderCondOperator = l_request.stopOrderCondOperator;
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
            	l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
            }
           
            // �t�w�l��l�^�C�v�F�@@ 
            // �@@���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p�v���~�A���^�����Y���i 
            // �@@���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p�v���~�A���^�����Y���i 
            String l_strStopPriceType = null;
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
            	l_strStopPriceType = l_request.stopPremium_underlyingAssets;
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
            	l_strStopPriceType = l_request.wlimitPremium_underlyingAssets;
            }
            
            // �t�w�l��l�F�@@ 
            // �@@���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p���������P�� 
            // �@@���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p���������P�� 
            double l_dblStopOrderPrice = 0.0D;
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                if (l_request.stopOrderCondPrice != null)
                {
                    l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
                }            	
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                if (l_request.wlimitOrderCondPrice != null)
                {
                    l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
                } 
            }
            
            // �iW�w�l�j�����w�l�F�@@���N�G�X�g�f�[�^.W�w�l�p�����P�� 
            double l_dblWLimitOrderChange = 0.0D;
            if (l_request.wLimitPrice != null)
            {
                l_dblWLimitOrderChange = Double.parseDouble(l_request.wLimitPrice);
            }
            
            // �iW�w�l�j���s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.�v�w�l�p���s����)   
            IfoOrderExecutionConditionType l_wlimitExecCondType = 
            	WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
            
            // �iW�w�l�j�L����ԋ敪�F�@@���N�G�X�g�f�[�^.�v�w�l�p�L����ԋ敪 
            String l_strWlimitEnableStatusDiv = l_request.wlimitEnableStatusDiv;
            
            // �����㒍�������敪�F�@@���N�G�X�g�f�[�^.���������敪
            String l_strExpirationDateType = l_request.expirationDateType;
            
            //�[��O�J�z�Ώۃt���O�F�@@�敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O
            //(���N�G�X�g�f�[�^.���������敪, �����P��.���XID)�̖߂�l
            boolean l_blnEveningSessionOrderFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_strExpirationDateType, l_orderUnit.getBranchId());

            l_ifoOpenContractChangeSpec = WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
        		l_lngOrderId, 
        		l_lngOrderUnitId, 
        		l_dblOpOrderQuantity, 
        		l_dblLimitPrice,
        		l_changeExecCondType,
        		l_datchangeExpirationDate,
                l_datOrderBizDate,
        		l_strOrderCondType,
        		l_strOrderCondOperator,
        		l_strStopPriceType,
        		l_dblStopOrderPrice,
        		l_dblWLimitOrderChange,
        		l_wlimitExecCondType,
        		l_strWlimitEnableStatusDiv,      		
        		l_strExpirationDateType,
                l_blnEveningSessionOrderFlag);

            //1.5.get�⏕����( )
            log.debug("get�⏕����( )");
            WEB3GentradeSubAccount l_subAccount = null;
            l_subAccount = (WEB3GentradeSubAccount)getSubAccount();
                
            //1.6.validate�V�K����������
            log.debug("l_subAccount = " + l_subAccount);
            log.debug("l_WEB3IfoOpenContractChangeSpec = " + l_ifoOpenContractChangeSpec);
            log.debug("validate�V�K����������");

            OrderValidationResult l_result = null;
            l_result = l_orderManagerImpl.validateChangeOrder(l_subAccount,l_ifoOpenContractChangeSpec);
            
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_result.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    STR_METHOD_NAME);                
            }
            //1.7.1�iOP�����V�K���T�[�r�X�jsubmit�����Q �i�Q�Ɓj
            //1.7.2.getTradedProduct
            log.debug("getTradedProduct");
            TradedProduct l_tradedProduct = null;
            l_tradedProduct = l_orderUnit.getTradedProduct();
            
            //1.7.3.getDataSourceObject
            log.debug("getDataSourceObject");
            IfoOrderUnitRow l_orderUnitRow = null;
            l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //1.7.4.getAfterChangeOriginalQuantity
            log.debug("getAfterChangeOriginalQuantity");
            double l_afterChangeOriginalQuantity = 0;
            l_afterChangeOriginalQuantity = l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity();

            if (Double.isNaN(l_afterChangeOriginalQuantity))
            {
                l_afterChangeOriginalQuantity = 0D;
            }
  
            //1.7.5.getSide
            log.debug("getSide");
            SideEnum l_side = null;
            l_side = l_orderUnit.getSide();
            
            //1.7.6.getExecutedQuantity
            log.debug("getExecutedQuantity");
            double l_dblExecutedQuantity = 0;
            l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;
            }

            //1.7.7.getExecutedAmount
            log.debug("getExecutedAmount");
            double l_dblExecutedAmount = 0;
            l_dblExecutedAmount = l_orderUnit.getExecutedAmount();
            if (Double.isNaN(l_dblExecutedAmount))
            {
                l_dblExecutedAmount = 0D;
            }
            
            //1.7.8.�萔��
            log.debug("�萔��");
            WEB3GentradeCommission l_gentradeCommission = null;
            l_gentradeCommission = new WEB3GentradeCommission();
            
            //1.7.9.(*1)�v���p�e�B�Z�b�g
            log.debug("�v���p�e�B�Z�b�g"); 
            //�����`���l�����Z�b�g����B
            l_gentradeCommission.setOrderChannel(this.getLoginChannel());
            
            //�،����ID���Z�b�g����B
            l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            
            //���XID���Z�b�g����B
            l_gentradeCommission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
            
            //���������Z�b�g����B
            l_gentradeCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));            
            
            //����R�[�h�iSONAR�j���Z�b�g����B            
             l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);            
             
            //�萔�����i�R�[�h���Z�b�g����B
            l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
            
            //�萔��.is�w�l = 
            //�@@1)���N�G�X�g�f�[�^.�m�F�������� == null�̏ꍇ�A
            //�@@ ���N�G�X�g.�����P��!=0�Ȃ�΁Atrue�B�ȊO�Afalse�B
            //�@@2)��L�ȊO�Athis.is�w�l(���N�G�X�g�f�[�^)�B
            boolean l_blnIsLimitPrice = false;
            
            if (l_request.checkDate == null)
            {
                if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
                {
                    l_blnIsLimitPrice = false;
                }
                else
                {
                    l_blnIsLimitPrice = true;
                }
            }
            else
            {
                l_blnIsLimitPrice = this.isLimitPrice(l_request);
            }
            
            l_gentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);
            
            //�����������`���l�����Z�b�g����B
            l_gentradeCommission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());
            
            //�������萔��No���Z�b�g����B
            l_gentradeCommission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());
            
            //�������萔��No�}�Ԃ��Z�b�g����B
            l_gentradeCommission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());
            
            //�ٍϋ敪���Z�b�g����B                  
            l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER); 
            
            //�萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
            l_gentradeCommission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_tradedProduct.getProduct()).getUnderlyingProductCode());
            
            //�萔��.���� = �V�K���������e.getAfterChangeOriginalQuantity()
            l_gentradeCommission.setQuantity(l_afterChangeOriginalQuantity);
            
            log.debug("calc�������T�Z��n���1");
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = new WEB3IfoTradedProductImpl(l_tradedProduct.getTradedProductId());
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = null;

            double l_dblPrice = 0D;
            double l_dblLimitPriceTemp = 0D;
            double l_dblWLimitPriceTemp = 0D;
            if (l_request.limitPrice != null)
            {
                l_dblLimitPriceTemp = Double.parseDouble(l_request.limitPrice);
            }
            if (l_request.wLimitPrice != null)
            {
                l_dblWLimitPriceTemp = Double.parseDouble(l_request.wLimitPrice);
            }
            if (l_request.checkDate != null)
            {
                if (l_request.checkPrice != null)
                {
                    l_dblPrice = Double.parseDouble(l_request.checkPrice); 
                }
                else
                {
                    if (SideEnum.BUY.equals(l_side) && l_dblLimitPriceTemp < l_dblWLimitPriceTemp)
                    {
                        l_dblPrice = l_dblWLimitPriceTemp;
                    }
                    else
                    {
                        l_dblPrice = l_dblLimitPriceTemp;
                    }
                }
            }
            else
            {
                l_dblPrice = l_dblLimitPriceTemp; 
            }

            //1.7.10.�T�Z��n������v�Z����B
            //[calc�������T�Z��n���()�Ɏw�肷�����]
            //�萔���F�@@�萔���I�u�W�F�N�g
            //�w�l�F
            //�@@[���N�G�X�g�f�[�^.�m�F�������� != null�̏ꍇ]
            //�@@�@@���N�G�X�g�f�[�^.�m�F���P�� != null�̏ꍇ�A���N�G�X�g�f�[�^.�m�F���P���B
            //�@@�@@���N�G�X�g�f�[�^.�m�F���P�� == null�̏ꍇ�A
            //�@@�@@�@@�����P��.getSide() == "��" and ���N�G�X�g�f�[�^.�����P��(*1) <
            //                           ���N�G�X�g�f�[�^.W�w�l�p�����P��(*2) �Ȃ�΁A
            //�@@�@@�@@�@@���N�G�X�g�f�[�^.W�w�l�p�����P��(*2)���Z�b�g�B
            //�@@�@@�@@�ȊO�A���N�G�X�g�f�[�^.�����P��(*1)���Z�b�g�B
            //�@@[��L�ȊO]�i���b�`�N���C�A���g�N���j
            //�@@�@@���N�G�X�g�f�[�^.�����P��(*1)���Z�b�g�B
            //�@@�@@(*1�@@�����P��==null�̏ꍇ�A�[�����Z�b�g�j
            //�@@�@@(*2�@@W�w�l�p�����P��==null�̏ꍇ�A�[�����Z�b�g�j
            //�⏕�����F�@@�⏕�����I�u�W�F�N�g
            //�敨OP��������F�@@�敨OP��������I�u�W�F�N�g
            //���ʁF �V�K���������e.getQuantity()
            //�����F �����P��.getSide()
            //is�ԍϒ����F�@@false
            //��萔�ʁF�@@�����P��.getExecutedQuantity()
            //���v�����z�F�@@�����P��.getExecutedAmount()
            //isSkip���z�`�F�b�N�F�@@false
            l_estimateDeliveryAmountCalcResult = 
                l_orderManagerImpl.calcChangeEstimateDeliveryAmount(
                    l_gentradeCommission,
                    l_dblPrice,
                    l_subAccount,
                    l_ifoTradedProductImpl,
                    l_afterChangeOriginalQuantity,
                    l_side,
                    false,
                    l_dblExecutedQuantity,
                    l_dblExecutedAmount,
                    false); 
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult = null;
            
			//1.7.11.(*2)����t���[
			//�����������ڋN���� ���� ���~�b�g�������L����W�w�l�����i�����j�̏ꍇ
			//�i���N�G�X�g�f�[�^.�m�F�������� == null && 
			// �����P��.getSide() == "BUY" && 
			// �V�K�������������e.��������() == "W�w�l" &&
			// ���N�G�X�g�f�[�^.�v�w�l�p�L����ԋ敪 == "���~�b�g�����L��"�j�A�������s���B
			//
			//�������������ڋN�����łȂ��ꍇ�́A�m�F�����ɂč������̊T�Z��n���
			//�@@���m�肵�Ă���i��r�ςł���j�ׁA�v�Z�s�v�B
            WEB3GentradeCommission l_wLimitPriceCommission = new WEB3GentradeCommission();
            if (l_request.checkDate == null 
        		&& SideEnum.BUY.equals(l_side) 
        		&& WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
    				l_ifoOpenContractChangeSpec.getOrderCond()) 
        		&& WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(
    				l_request.wlimitEnableStatusDiv))
            {
                //1.7.11.1.[setIs�w�l()�Ɏw�肷�����]
                //is�w�l�F�@@�V�K���������e.(W�w�l)�����w�l != 0�̏ꍇ�Atrue�B�ȊO�Afalse�B
                if (l_ifoOpenContractChangeSpec.getWLimitPriceChange() != 0)
                {
                    l_wLimitPriceCommission.setIsLimitPrice(true);
                }
                else
                {
                    l_wLimitPriceCommission.setIsLimitPrice(false);
                }

                l_wLimitPriceCommission.setOrderChannel(this.getLoginChannel());

                //�،����ID���Z�b�g����B
                l_wLimitPriceCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

                //���XID���Z�b�g����B
                l_wLimitPriceCommission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
 
                //���������Z�b�g����B
                l_wLimitPriceCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));            

                //����R�[�h�iSONAR�j���Z�b�g����B            
                l_wLimitPriceCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);            

                //�萔�����i�R�[�h���Z�b�g����B
                l_wLimitPriceCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);

                //�����������`���l�����Z�b�g����B
                l_wLimitPriceCommission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());

                //�������萔��No���Z�b�g����B
                l_wLimitPriceCommission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());

                //�������萔��No�}�Ԃ��Z�b�g����B
                l_wLimitPriceCommission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());

                //�ٍϋ敪���Z�b�g����B                  
                l_wLimitPriceCommission.setPayType(WEB3PayTypeDef.OTHER); 

                //�萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
                l_wLimitPriceCommission.setUnderlyingProductCode(
                    ((WEB3IfoProductImpl)l_tradedProduct.getProduct()).getUnderlyingProductCode());

                //�萔��.���� = �V�K���������e.getAfterChangeOriginalQuantity()
                l_wLimitPriceCommission.setQuantity(l_afterChangeOriginalQuantity);

                //1.7.11.2.W�w�l�������̊T�Z��n������v�Z����B
                //[calc�������T�Z��n���()�Ɏw�肷�����]
                //�萔���F�@@�萔���I�u�W�F�N�g
                //�w�l�F�@@�V�K���������e.(W�w�l)�����w�l
                //�⏕�����F�@@�⏕�����I�u�W�F�N�g
                //�敨OP��������F�@@�敨OP��������I�u�W�F�N�g
                //���ʁF �V�K���������e.getAfterChangeOriginalQuantity()
                //�����F �����P��.getSide()
                //is�ԍϒ����F�@@false
                //��萔�ʁF�@@�����P��.getExecutedQuantity()
                //���v�����z�F�@@�����P��.getExecutedAmount()
                //isSkip���z�`�F�b�N�F�@@false
                l_amountCalcResult = 
                    l_orderManagerImpl.calcChangeEstimateDeliveryAmount(
                        l_wLimitPriceCommission,
                        l_ifoOpenContractChangeSpec.getWLimitPriceChange(),
                        l_subAccount,
                        l_ifoTradedProductImpl,
                        l_afterChangeOriginalQuantity,
                        l_side,
                        false,
                        l_dblExecutedQuantity,
                        l_dblExecutedAmount,
                        false); 
            }
            
            //1.7.12.get�㗝���͎�( )
            Trader l_trader = this.getTrader();

            //1.7.13.�敨OP�V�K�������X�V�C���^�Z�v�^(�V�K���������e)
			WEB3IfoOpenContractChangeUpdateInterceptor l_openContractChangeUpdateInterceptor =
				new WEB3IfoOpenContractChangeUpdateInterceptor(
					l_ifoOpenContractChangeSpec);
            
            //1.7.14. (*2) �v���p�e�B�Z�b�g
            log.debug("�v���p�e�B�Z�b�g");
            //�m�T�Z��n����Ǝ萔���̐ݒ�n
            //(*)���N�G�X�g�f�[�^.�m�F��������==null�A���A�����A���AW�w�l�����̏ꍇ�A�ȉ�������s���B
            //�߂�l(*1)�Ɩ߂�l(*2)�̍S������������r���āA
            //��r���ʂ̍����ق��̖߂�l�̊T�Z��n����v�Z�I�u�W�F�N�g���g�p����B
            if (l_request.checkDate == null 
                    && SideEnum.BUY.equals(l_side) 
                    && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOpenContractChangeSpec.getOrderCond()))
            {
               if (l_amountCalcResult.getRestraintTurnover() > l_estimateDeliveryAmountCalcResult.getRestraintTurnover())
               {
                   l_openContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_amountCalcResult);
                   l_openContractChangeUpdateInterceptor.setCommision(l_wLimitPriceCommission);
               }
               else
               {
                   l_openContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmountCalcResult);
                   l_openContractChangeUpdateInterceptor.setCommision(l_gentradeCommission);
               }
            }
            else
            {
                l_openContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmountCalcResult);
                l_openContractChangeUpdateInterceptor.setCommision(l_gentradeCommission);
            }

            l_openContractChangeUpdateInterceptor.setOrderCond(l_request.orderCondType);
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                log.debug("WEB3IfoOrderCondTypeDef.STOP_ORDER.equals(l_request.orderCondType)");
                l_openContractChangeUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePriceType(l_request.stopPremium_underlyingAssets);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                log.debug("WEB3IfoOrderCondTypeDef.W_LIMIT_PRICE.equals(l_request.orderCondType)");
                l_openContractChangeUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePriceType(l_request.wlimitPremium_underlyingAssets);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));
            }
            l_openContractChangeUpdateInterceptor.setWLimitPriceChange(l_ifoOpenContractChangeSpec.getWLimitPriceChange());
            
            long l_lngTraderId = 0;
            if (l_trader != null)
            {
                l_lngTraderId = l_trader.getTraderId();
            }
            l_openContractChangeUpdateInterceptor.setTraderId(l_lngTraderId);
            
			OpLoginSecurityService l_opLoginSec =
				(OpLoginSecurityService) Services.getService(
					OpLoginSecurityService.class);
			String l_strOrderRootDiv =
				l_opLoginSec.getSessionProperty(
					WEB3SessionAttributeDef.ORDER_ROOT_DIV);

			l_openContractChangeUpdateInterceptor.setOrderRootDiv(l_strOrderRootDiv);

            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_interceptorObject = new Object[1];
            l_interceptorObject[0] = l_openContractChangeUpdateInterceptor;
            Object[] l_specObject = new Object[1];
            l_specObject[0] = l_ifoOpenContractChangeSpec;

			//1.7.15.validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[],
			//�������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
            //[����] 
			// �⏕�����F �⏕�����I�u�W�F�N�g 
			// �������e�C���^�Z�v�^�F �敨OP�V�K�������X�V�C���^�Z�v�^��v�f�Ƃ����z�� 
			// �������e�F �V�K���������e��v�f�Ƃ����z�� 
			// ������ʁF �����P��.������� 
			// �]�͍X�V�t���O�F true 
            WEB3TPTradingPowerResult l_tpTradingPowerResult = 
                l_tradingPowerService.validateTradingPower
                (l_subAccount,
                l_interceptorObject,
                l_specObject,
                l_orderUnit.getOrderType(),
                true);
            if (!l_tpTradingPowerResult.isResultFlg())
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + "." +STR_METHOD_NAME);
            }
            
            //1.7.16.setThreadLocalPersistenceEventInterceptor
            l_orderManagerImpl.setThreadLocalPersistenceEventInterceptor(l_openContractChangeUpdateInterceptor);
            
            //1.7.17.submitChangeOrder(�⏕���� : SubAccount, �V�K���������e : ChangeOrderSpec, ����p�X���[�h : String, isSkip�����R��(=true) : boolean)
            OrderSubmissionResult l_orderResult = null;
            l_orderResult = l_orderManagerImpl.submitChangeOrder((SubAccount)l_subAccount,(ChangeOrderSpec)l_ifoOpenContractChangeSpec,l_request.password,true);
                      
            if (l_orderResult.getProcessingResult().isSuccessfulResult())
            {
                //is�\�񒍕��m�F�v(IfoOrderUnit)
                boolean l_blnIsReserveOrderExist = l_orderManagerImpl.isReserveOrderExist(l_ifoOrderUnit);

                //�\�񒍕��m�F�v�iis�\�񒍕��m�F�v() == true�j�̏ꍇ
                List l_lisOpenReserveIfoOrderUnits = null;
                if (l_blnIsReserveOrderExist)
                {
                    //get�L���\�񒍕��P�ʈꗗ(�e�����̒���ID : long)
                    WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                        (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                            WEB3ToSuccReservationIfoOrderUpdateService.class);
                    l_lisOpenReserveIfoOrderUnits =
                        l_ifoOrderUpdateService.getOpenReserveIfoOrderUnits(l_ifoOrderUnit.getOrderId());
                }

                //1.7.18.WEB3GenResponse(WEB3GenRequest)
                WEB3OptionsOpenMarginChangeCompleteResponse l_response = (WEB3OptionsOpenMarginChangeCompleteResponse) l_request.createResponse();

                //1.7.19..(*3) �v���p�e�B�Z�b�g
                l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
                l_response.orderActionId = l_request.id;
                if (l_lisOpenReserveIfoOrderUnits != null)
                {
                    l_response.succSettingFlag = true;
                }
                else
                {
                    l_response.succSettingFlag = false;
                }
                log.debug("l_response.lastUpdatedTimestamp = " + l_response.lastUpdatedTimestamp);
                log.debug("l_response.orderActionId = " + l_response.orderActionId);                     

                log.exiting(STR_METHOD_NAME);
                return l_response;                
            }
            else
            {
                log.debug("ProcessingResult() = " + l_orderResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }                        
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);           
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (is�w�l)<BR>
     * �w�l�������ǂ������ʂ���B <BR>
     * �w�l�����̏ꍇ�́Atrue�B�ȊO�Afalse��ԋp����B <BR>
     * ���񃊃b�`�N���C�A���g�i���N�G�X�g�f�[�^.����ID != null�j�̏ꍇ <BR>
     * �@@�̂ݎg�p���邱�ƁB <BR>
     * <BR>
     * �ȉ��A����.���N�G�X�g�f�[�^�̒l���g�p���Ĕ��ʂ��s���B <BR>
     * <BR>
     * �m�F���P�� == null�̏ꍇ�Atrue��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V���������V�K�������������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    protected boolean isLimitPrice(
        WEB3OptionsOpenMarginChangeCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "isLimitPrice(WEB3OptionsOpenMarginChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("���N�G�X�g�f�[�^ = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "���N�G�X�g�f�[�^ = null�B");
        }

        if (l_request.checkPrice == null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

}
@
