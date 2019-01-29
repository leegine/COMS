head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderExecutedInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�������Ɖ�T�[�r�XImpl(WEB3OptionOrderExecutedInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 �n�U�c (���u) �V�K�쐬
              001: 2006/07/27 �ęԍg (���u)�@@ �d�l�ύX�@@���f��471�A495�A497�A518
Revesion History : 2007/06/15 �юu�� (���u)�d�l�ύX ���f��685,686,687,688
Revesion History : 2007/06/21 �����F (���u)�d�l�ύX ���f��741�A746�A747
Revesion History : 2007/10/16 �����F (���u)�d�l�ύX ���f��786 787
Revesion History : 2008/08/18 ���� (���u) IFO�����_�Ή�
**/

package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Contract;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContract;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderAction;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoFinTransactionManagerImpl;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3IfoReferenceTypeDef;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.define.WEB3IfoTradeTypeDef;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3OptionsChangeCancelHistoryGroup;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractGroup;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractListRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractListResponse;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsResponse;
import webbroker3.ifo.message.WEB3OptionsExecuteGroup;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceRequest;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceResponse;
import webbroker3.ifo.message.WEB3OptionsExecuteUnit;
import webbroker3.ifo.message.WEB3OptionsOrderHistoryRequest;
import webbroker3.ifo.message.WEB3OptionsOrderHistoryResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecutedInquiryService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (OP�������Ɖ�T�[�r�XImpl)<BR>
 * �����w���I�v�V�����������Ɖ�T�[�r�X�����N���X<BR>
 * @@author  : �n�U�c
 * @@version : 1.0
 */
public class WEB3OptionOrderExecutedInquiryServiceImpl extends WEB3OptionClientRequestService implements WEB3OptionOrderExecutedInquiryService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionOrderExecutedInquiryServiceImpl.class);

    /**
     * FinApp
     */
    private FinApp finApp = (FinApp)Services.getService(FinApp.class);

    /**
     * TradingModule
     */
    private TradingModule tradingModule =
        finApp.getTradingModule(ProductTypeEnum.IFO);

    /**
     * OrderManager
     */
    private WEB3OptionOrderManagerImpl opOrderManager =
        (WEB3OptionOrderManagerImpl)tradingModule.getOrderManager();

    /**
     * @@roseuid 40C0BAF302EE
     */
    public WEB3OptionOrderExecutedInquiryServiceImpl()
    {

    }

    /**
     * �����w���I�v�V�����������Ɖ�T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Aget�������Ɖ�()�Aget�������ڍ�()�A<BR>
     * get���������Ɖ�()�Aget�ԍό��ʈꗗ()�̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057FB7D0263
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3OptionsExecuteReferenceRequest)
        {
            //�u�����w���I�v�V�����������Ɖ�N�G�X�g�v�̏ꍇ
            l_response = getOrderExecutedInquiry(
                (WEB3OptionsExecuteReferenceRequest)l_request);
        }
        else if (l_request instanceof WEB3OptionsExecuteDetailsRequest)
        {
            //�u�����w���I�v�V���������������ڍ׃��N�G�X�g�v�̏ꍇ
            l_response = getOrderExecutedDetail(
                (WEB3OptionsExecuteDetailsRequest)l_request);
        }
        else if (l_request instanceof WEB3OptionsOrderHistoryRequest)
        {
            //�u�����w���I�v�V�������������Ɖ�N�G�X�g�v�̏ꍇ
            l_response = getOrderActionInquiry(
                (WEB3OptionsOrderHistoryRequest)l_request);
        }
        else if (l_request instanceof WEB3OptionsCloseMarginContractListRequest)
        {
            //�u�����w���I�v�V�����ԍό��ʈꗗ���N�G�X�g�v�̏ꍇ
            l_response = getSettleContractList(
                (WEB3OptionsCloseMarginContractListRequest)l_request);
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�������Ɖ�)<BR>
     * �����w���I�v�V�����������Ɖ�A�����w���I�v�V������������ꗗ�̏������s���B
     * <BR>
     * <BR>
     * �V�[�P���X�}�u�iOP�������Ɖ�T�[�r�X�jget�������Ɖ�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V�����������Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsExecuteReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 406A8790001E
     */
    protected WEB3OptionsExecuteReferenceResponse getOrderExecutedInquiry(WEB3OptionsExecuteReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOrderExecutedInquiry(WEB3OptionsExecuteReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //get�⏕����()
        SubAccount l_subAccount = getSubAccount();
        
        WEB3IfoProductImpl l_ifoProduct = null;
        WEB3IfoProductManagerImpl l_ProductManager = 
            (WEB3IfoProductManagerImpl)tradingModule.getProductManager();

        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        if (l_request.opProductCode != null
            && l_request.opProductCode.length() != 0)
        {
            try
            {
                l_ifoProduct = l_ProductManager.getIfoProduct(
					l_subAccount.getInstitution(), 
                	l_request.opProductCode);
                	
                if (l_ifoProduct == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (NotFoundException l_nfex)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //�����Y�����R�[�h���擾
            String l_strUnderlyingProductCode = l_ifoProduct.getUnderlyingProductCode();
            //reset�����R�[�h
            WEB3GentradeTradingTimeManagement.resetProductCode(l_strUnderlyingProductCode);

        }
        
        //���N�G�X�g�f�[�^�ɖ������荀��(����s��A�w����ʁA�����A�I�v�V�������i�敪�A�s�g���i)���ݒ肳��Ă���ꍇ
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
            else
            {
                //�I�v�V�������i�敪�����݂��Ȃ��l
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00270,
                    getClass().getName() + "." + STR_METHOD_NAME);     
            }
                
			double l_dblStrikePrice = 0;      
			if (l_request.strikePrice != null && !"".equals(l_request.strikePrice))       
			{     
				l_dblStrikePrice = Double.parseDouble(l_request.strikePrice); 
			}     
                
			try       
			{     
				l_ifoProduct = l_ProductManager.getIfoProduct(        
					l_subAccount.getInstitution(),    
					l_request.targetProductCode,  
					l_request.delivaryMonth,  
					l_ifoDerivativeTypeEnum,  
					l_dblStrikePrice, 
					WEB3DivisionTypeDef.DIVISION_DEFAULT, 
					l_request.marketCode);    
					
				if (l_ifoProduct == null)
				{
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00301,
						getClass().getName() + "." + STR_METHOD_NAME);
				}
			}     
			catch (NotFoundException l_nfex)      
			{     
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
					STR_METHOD_NAME);   
			}
			
			//�����Y�����R�[�h���擾
			String l_strUnderlyingProductCode = l_ifoProduct.getUnderlyingProductCode();
			//reset�����R�[�h
			WEB3GentradeTradingTimeManagement.resetProductCode(l_strUnderlyingProductCode);
		}
        
		//is����\�ڋq(�⏕����)
		//���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"
		//���Ais����\�ڋq��false�̏ꍇ�A��O��throw����
		boolean l_blnIsTradedPossibleAccount =
			opOrderManager.isTradedPossibleCustomer(
				(WEB3GentradeSubAccount)l_subAccount);
		if (WEB3IfoReferenceTypeDef.CORRECTION_LIST.equals(l_request.referenceType) &&
			l_blnIsTradedPossibleAccount == false)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00275,
				getClass().getName() + "." + STR_METHOD_NAME);
		}
        
		//validate������t�\()
		//���N�G�X�g�f�[�^.�Ɖ�敪="�������Ɖ�"�̏ꍇ�̂݁A
		//��t���ԃ`�F�b�N�A�V�X�e��������~�`�F�b�N
		if (WEB3IfoReferenceTypeDef.ORDER_PROMISE.equals(l_request.referenceType))
		{
			//��t���ԃ`�F�b�N�A�V�X�e��������~�`�F�b�N
			WEB3GentradeTradingTimeManagement.validateOrderAccept();
		}

        //get����X()
        WEB3GentradeBranch l_branch = ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch();
        //������ԊǗ�.get�s��ǌx���w��()
        String[] l_TradeCloseMarket =
            WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                l_branch,
                WEB3FuturesOptionDivDef.OPTION);

        //���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"�̏ꍇ�̂݁A���������{����
        if (WEB3IfoReferenceTypeDef.CORRECTION_LIST.equals(l_request.referenceType))
        {
            //reset��t���ԋ敪(��t���ԋ敪�F"12�F�����w���敨OP�i��������j)
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.INDEX_FUTURE_OP_CHANGE_CANCEL);

            boolean l_blnChangeAcceptFail = false;
            boolean l_blnCancelAcceptFail = false;            
            //�G���[�t���O
            boolean l_blnCancelErrBATCH = false;
            boolean l_blnCancelErrSCRAM = false;
            boolean l_blnCancelErrTRADINGTIME = false;

            //reset������t�g�����U�N�V����(������t�g�����U�N�V���� : "05�F����")
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CHANGE);

            //validate������t�\()
            //������t�g�����U�N�V����="05�F����"
            //��t���ԋ敪="�����w���敨OP�i��������j"��
            //������t�\�`�F�b�N�i��t���ԃ`�F�b�N�A
            //�V�X�e��������~�`�F�b�N�j���s���B
            try
            {   
                WEB3GentradeTradingTimeManagement.validateOrderAccept();                  
            }
            catch (WEB3BusinessLayerException l_ex)
            {
                log.debug("�����F������t�s��", l_ex);
                l_blnChangeAcceptFail= true;
            }

            //reset������t�g�����U�N�V����(������t�g�����U�N�V���� : "06:���")
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CANCEL);

            //validate������t�\()
            //������t�g�����U�N�V����="06�F���"
            //��t���ԋ敪="�����w���敨OP�i��������j"��
            //������t�\�`�F�b�N�i��t���ԃ`�F�b�N�A
            //�V�X�e��������~�`�F�b�N�j���s���B
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BusinessLayerException l_ex)
            {
				log.debug("����F������t�s��", l_ex);
				l_blnCancelAcceptFail = true;
				if (l_ex.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00011)
				{
					l_blnCancelErrBATCH = true;
				}
				else if (l_ex.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00012)
				{
					l_blnCancelErrSCRAM = true;
				}
				else if (l_ex.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00013)
				{
					l_blnCancelErrTRADINGTIME = true;
				}
				else
				{
					throw new WEB3SystemLayerException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
						this.getClass().getName() + STR_METHOD_NAME,
						l_ex.getMessage(),
						l_ex);
				}
			}

			//�����A����Ƃ��Ɏ�t�s�̏ꍇ�A��O��throw����B
			if (l_blnChangeAcceptFail && l_blnCancelAcceptFail)
			{
				if (l_blnCancelErrBATCH)
				{
					//�o�b�`������
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00011,
						this.getClass().getName() + "." + STR_METHOD_NAME,
						"�����Ǝ�������Ɏ�t�s��");
				}
				else if (l_blnCancelErrSCRAM)
				{
					//�ً}��~��
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00012,
						this.getClass().getName() + "." + STR_METHOD_NAME,
						"�����Ǝ�������Ɏ�t�s��");
				}
				else
				{
					//��t�\���ԊO
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00013,
						this.getClass().getName() + "." + STR_METHOD_NAME,
						"�����Ǝ�������Ɏ�t�s��");            		
				}
			}
		}

        //�������ꗗ�̍쐬
        TreeMap l_orderBizDateMap = new TreeMap();
        Date l_datBizdate = GtlUtils.getTradingSystem().getBizDate();
        Timestamp l_tsBizdate = new Timestamp(l_datBizdate.getTime());
        
        //�Ɩ����t���Z�b�g
        Date l_datSysdate = WEB3DateUtility.toDay(l_tsBizdate);
        l_orderBizDateMap.put(l_datSysdate, l_datSysdate);
        
        //�Ɩ����t�̑O�c�Ɠ����Z�b�g
        WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsBizdate);
        Date l_datBizDate = WEB3DateUtility.toDay(l_genBizDate.roll(-1));
        l_orderBizDateMap.put(l_datBizDate, l_datBizDate);
        
        //(���X�w����)�戵�����I�u�W�F�N�g���擾����
        String l_instCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_branchCode = l_branch.getBranchCode();
        
		WEB3GentradeBranchIndexDealtCond[] l_branchIndexDealtCond =
			WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
				l_instCode, l_branchCode, WEB3FuturesOptionDivDef.OPTION);
            
        //�擾����(���X�w����)�戵�����̗v�f�����ALOOP����
        int l_intIndexSize = 0;
        if (l_branchIndexDealtCond != null)
        {
            l_intIndexSize = l_branchIndexDealtCond.length;
        }
        for (int i = 0; i < l_intIndexSize; i++)
        {
            //reset�����R�[�h
            WEB3GentradeTradingTimeManagement.resetProductCode(l_branchIndexDealtCond[i].getUnderlyingProductCode());
            //���������擾
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            //�擾�������������������ꗗ�Ɋ܂܂�Ă��Ȃ����t�̏ꍇ�A�������ꗗ�ɒǉ�
            if (!l_orderBizDateMap.containsKey(l_datBizDate))
            {                                      
                l_orderBizDateMap.put(l_datBizDate, l_datBizDate);
            }
        }
        
        //�擾�����������ꗗ���Z�b�g
        int l_intListSize = l_orderBizDateMap.size();
        Date[] l_bizDateList = new Date[l_intListSize];
        Collection l_collection = l_orderBizDateMap.values();
        l_collection.toArray(l_bizDateList); 
        
        //createResponse()
        WEB3OptionsExecuteReferenceResponse l_response = null;
        l_response = (WEB3OptionsExecuteReferenceResponse)
            l_request.createResponse();
 
        //create�����R�[�h����
        WEB3FuturesOptionsProductCodeNameUnit[] l_productCodeNameUnits;
        l_productCodeNameUnits = createProductCodeName(
            (WEB3GentradeSubAccount)l_subAccount,
            l_request);
   
        //create�����R�[�h���̂̕Ԃ�l��NULL�̏ꍇ�A
        //���X�|���X�쐬�A�v���p�e�B�Z�b�g�������s���B
        //�i�������Ɖ�쐬�A�y�[�W���O�����͍s��Ȃ��j
        if (l_productCodeNameUnits == null)
        {
            log.debug("create�����R�[�h���̂̕Ԃ�l��NULL");
            //���X�|���X.�������Ɖ���P�� = NULL
            l_response.opExecuteGroups = null;
            //���X�|���X.�������ꗗ
            l_response.orderBizDateList = l_bizDateList;
            //���X�|���X.�����R�[�h�� = 0
            l_response.totalRecords = "0";
            //���X�|���X.���y�[�W�� = 0
            l_response.totalPages = "0";
            //���X�|���X.�\���y�[�W�ԍ� = 0
            l_response.pageIndex = "0";
            //���X�|���X.ID�ꗗ = NULL
            l_response.idList = null;
            //���X�|���X.����I���x������ = get�s��ǌx���w���̕Ԃ�l
            l_response.messageSuspension = l_TradeCloseMarket;
            //���X�|���X.�����w���敨�I�v�V���������R�[�h���� = NULL
            l_response.futOpProductCodeNames = null;
            
            //���X�|���X.�ڋq���b�N�敪 =  is����\�ڋq�̕Ԃ�l��false�Ȃ�true
            //�����b�N�ڋq�̏ꍇ�Ftrue�A���b�N�ڋq�łȂ��ꍇ�Ffalse�ƂȂ�
            if (l_blnIsTradedPossibleAccount == false)
            {
                l_response.accountLock = true;
            }
            else
            {
                l_response.accountLock = false;
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //create�������Ɖ�
        WEB3OptionsExecuteGroup[] l_executeGroups;
        l_executeGroups = createOrderExecutedInquiry(
            (WEB3GentradeSubAccount)l_subAccount,
            l_request,
            l_response,
			l_ifoProduct);
                                          
        //create�������Ɖ�̕Ԃ�l��NULL�̏ꍇ�A
        //���X�|���X�쐬�A�v���p�e�B�Z�b�g�������s���B
        //(�y�[�W���O�����͍s��Ȃ�)
        if (l_executeGroups == null)
        {
            log.debug("create�������Ɖ�̕Ԃ�l��NULL");
            //���X�|���X.�������Ɖ���P�� = NULL
            l_response.opExecuteGroups = null;
            //���X�|���X.�������ꗗ
            l_response.orderBizDateList = l_bizDateList;
            //���X�|���X.�����R�[�h�� = 0
            l_response.totalRecords = "0";
            //���X�|���X.���y�[�W�� = 0
            l_response.totalPages = "0";
            //���X�|���X.�\���y�[�W�ԍ� = 0
            l_response.pageIndex = "0";
            //���X�|���X.ID�ꗗ = NULL
            l_response.idList = null;
            //���X�|���X.����I���x������ = get�s��ǌx���w���̕Ԃ�l
            l_response.messageSuspension = l_TradeCloseMarket;
            //���X�|���X.�����w���敨�I�v�V���������R�[�h����
            l_response.futOpProductCodeNames = l_productCodeNameUnits;
            //���X�|���X.�ڋq���b�N�敪 =  is����\�ڋq�̕Ԃ�l��false�Ȃ�true
            //�����b�N�ڋq�̏ꍇ�Ftrue�A���b�N�ڋq�łȂ��ꍇ�Ffalse�ƂȂ�
            if (l_blnIsTradedPossibleAccount == false)
            {
                l_response.accountLock = true;
            }
            else
            {
                l_response.accountLock = false;
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.debug("total record count: " + l_executeGroups.length);
        log.debug("****** ���X�|���X�D�������ꗗ�̗v�f���F[" + l_response.opExecuteGroups.length + "]");
        log.debug("****** ���X�|���X�DID�ꗗ�̗v�f���F[" + l_response.idList.length + "]");

        //���X�|���X.����I���x������ = get�s��ǌx���w���̕Ԃ�l
        l_response.messageSuspension = l_TradeCloseMarket;

        //���X�|���X.�����w���敨�I�v�V���������R�[�h���� = create�����R�[�h���̂̕Ԃ�l
        l_response.futOpProductCodeNames = l_productCodeNameUnits;

        //���X�|���X.�ڋq���b�N�敪 = is����\�ڋq�̕Ԃ�l��false�Ȃ�true
        //�����b�N�ڋq�̏ꍇ�Ftrue�A���b�N�ڋq�łȂ��ꍇ�Ffalse�ƂȂ�
        if (l_blnIsTradedPossibleAccount == false)
        {
            l_response.accountLock = true;
        }
        else
        {
            l_response.accountLock = false;
        }
        
        // ���X�|���X.�������ꗗ
        l_response.orderBizDateList = l_bizDateList;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�������ڍ�)<BR>
     * �w�肳�ꂽ����ID���������P�ʃI�u�W�F�N�g�̓��e����ʕ\���p�ɕҏW���A<BR>
     * ���X�|���X�ɐݒ肵�ĕԂ��B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�������Ɖ�T�[�r�X�jget�������ڍׁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V���������������ڍ׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3OptionsExecuteDetailsResponse
     * @@throws WEB3BaseException
     * @@roseuid 406A879202FD
     */
    protected WEB3OptionsExecuteDetailsResponse getOrderExecutedDetail(WEB3OptionsExecuteDetailsRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOrderExecutedDetail(WEB3OptionsExecuteDetailsRequest)";
        log.entering(STR_METHOD_NAME);

        //1.���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //2.�⏕�������擾����B
        SubAccount l_subAccount = getSubAccount();

        //3.��t���ԃ`�F�b�N�A�V�X�e��������~�`�F�b�N�B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //4.����ID�ɑΉ����钍���P�ʃI�u�W�F�N�g���擾����B
        OrderUnit[] l_orderUnits =
            opOrderManager.getOrderUnits(Long.parseLong(l_request.id));

		if (l_orderUnits.length == 0)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00398,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"�w�肵������ID�ɊY������f�[�^�����݂��܂���B");
		}

        IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderUnits[0];
        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        //�������擾
        WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_orderUnit.getProduct();
        IfoProductRow l_productRow = (IfoProductRow)l_ifoProduct.getDataSourceObject();

        //5.reset�����R�[�h(�����R�[�h)
        WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProduct.getUnderlyingProductCode());

        //6.�����P�ʂ̒�����ԋ敪���擾����B
        String l_strOrderStatus = WEB3IfoDataAdapter.getOrderStatusType(l_orderUnit);

        //7.�����P�ʂ̖���ԋ敪���擾����B
        String l_strExecType = WEB3IfoDataAdapter.getExecStatusType(l_orderUnit);

        //8.PR�w�p�̎��s�����̃R�[�h���擾����B
        String l_strPRExcCondType = WEB3IfoDataAdapter.getExecutionCondByPr(l_orderUnit.getExecutionConditionType());

        //9.get�����󋵋敪(�����P��)
        String l_strTransStatusType = WEB3IfoDataAdapter.getTransactionStatusType(l_orderUnit);
        
        //10  get�v�w�l�p�L����ԋ敪(�����P�� : IfoOrderUnit)
        String l_strWLimitEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);
        
        //11 get�v�w�l�p�֑ؑO�����P��(�����P�� : IfoOrderUnit)        
        String l_strWLimitBefSwitchPrice = WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);
        
        //12 get�v�w�l�p�֑ؑO���s����(�����P�� : IfoOrderUnit)
        String l_strWLimitBefSwitchExecCondType = WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);
        
        //13 is�蓮�����\(IfoOrderUnit)
        boolean l_blnManualOrderPossible = opOrderManager.isManualOrderPossible(l_orderUnit);
        
        //14 get�x���敪(IfoOrderUnit)
        String l_strDelayDiv = WEB3IfoDataAdapter.getDelayDiv(l_orderUnit);        

        //15.�����P�ʂɂЂ��Â����̈ꗗ���擾����B
        OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();

        //16.�����P�ʂɂЂ��Â��g�����U�N�V�����̈ꗗ���擾����B
        WEB3IfoFinTransactionManagerImpl l_finTransactionManager =
            (WEB3IfoFinTransactionManagerImpl)tradingModule.getFinTransactionManager();
        List l_transactionList = l_finTransactionManager.getTransactions(l_orderUnit);

        double l_dblDeliveryPrice = 0.0;    //��n���z
        double l_dblCommission = 0.0;       //�萔��
        double l_dblConsumptionTax = 0.0;   //�����

        //17.��n���z���擾����
        l_dblDeliveryPrice = opOrderManager.getNetAmount(l_orderUnit);

        BigDecimal l_bdCommission = new BigDecimal(l_dblCommission + "");
        BigDecimal l_bdConsumptionTax = new BigDecimal(l_dblConsumptionTax + "");
        int l_intListLength = l_transactionList.size();
        for (int i = 0; i < l_intListLength; i++)
        {
            IfoFinTransactionParams l_FinTransactionParams
                 = new IfoFinTransactionParams((IfoFinTransactionRow)l_transactionList.get(i));
            l_bdCommission = l_bdCommission.add(
                new BigDecimal(l_FinTransactionParams.getCommissionFee() + ""));
            l_bdConsumptionTax = l_bdConsumptionTax.add(
                new BigDecimal(l_FinTransactionParams.getCommissionFeeTax() + ""));
        }

        //16.���X�|���X�f�[�^�𐶐�����B
        WEB3OptionsExecuteDetailsResponse l_response = null;
        l_response = (WEB3OptionsExecuteDetailsResponse)l_request.createResponse();
           
        //���X�|���X.������
        l_response.opProductName = l_productRow.getStandardName();
        //���X�|���X.�w�����
        l_response.targetProductCode = l_ifoProduct.getUnderlyingProductCode();
        //���X�|���X.����
        l_response.delivaryMonth = l_ifoProduct.getMonthOfDelivery();
        //���X�|���X.�I�v�V�������i�敪
        if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProduct.getDerivativeType()))
        {
            l_response.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;                           
        }
        else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProduct.getDerivativeType()))
        {
            l_response.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;            
        }
        //���X�|���X.�s�g���i
        l_response.strikePrice = WEB3StringTypeUtility.formatNumber(l_ifoProduct.getStrikePrice()); 
        //���X�|���X.������
        l_response.orderBizDate = WEB3DateUtility.toDay(WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd"));

        WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //���X�|���X.���X�R�[�h
        l_response.branchCode = l_account.getBranch().getBranchCode();
        //���X�|���X.�ڋq�R�[�h(�\���p)
        l_response.accountCode = l_account.getDisplayAccountCode();
        //���X�|���X.�ڋq��(�\���p)
        l_response.accountName = l_account.getDisplayAccountName();            
        //���X�|���X.��������敪
        l_response.changeCancelDiv = l_orderUnitRow.getModifyCancelType();
        //���X�|���X.�����o�H�敪
        l_response.orderRootDiv = l_orderUnitRow.getOrderRootDiv();
        //���X�|���X.������
        l_response.transactionStateType = l_strTransStatusType;

        //�g�����Z�I�u�W�F�N�g�}�l�[�W��
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)finApp.getFinObjectManager();
        //�s��̎擾
        Market l_market;
        try
        {
            l_market = l_gentradeFinObjectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003, STR_METHOD_NAME,
                l_nfe.getMessage(), l_nfe); 
        }
        //���X�|���X.����s�� = �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket(�����P��.�s��ID).�s��R�[�h
        l_response.marketCode = l_market.getMarketCode();
        //���X�|���X.����敪 = �����P��.�������     
        l_response.tradingType = getPRLevelTradeTypeDiv(l_orderUnit.getOrderType());
        //���X�|���X.���� = null���Z�b�g
        l_response.openDate = null;
        //���X�|���X.���P�� = null���Z�b�g
        l_response.contractPrice = null;
        //���X�|���X.�������� = �����P��.��������
        l_response.opOrderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        //���X�|���X.�����P���敪 = �����P��.isMarketOrder�̕Ԃ�l��true�̏ꍇ�́A"���s"���Afalse�̏ꍇ�́A"�w�l"
        if (l_orderUnit.isMarketOrder())
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_response.limitPrice = null;
        }
        else
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        }
        //���X�|���X.�T�Z��n��� = �����P��.�T�Z��n���
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());

        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(
            WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit)))
        {
            //���X�|���X.�����L������ = �����P��.�����������t
            l_response.expirationDate = WEB3DateUtility.toDay(l_orderUnitRow.getExpirationDate());                
        }
        //���X�|���X.���s���� = get���s����(PR�w)�̕Ԃ�l
        l_response.execCondType = l_strPRExcCondType;
        //���X�|���X.���������敪 = �����P��.��������
        l_response.orderCondType = l_orderUnitRow.getOrderConditionType();
        //�����P��.����������"�t�w�l"�̏ꍇ
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_response.orderCondType))
        {
            //���X�|���X.�t�w�l�p�v���~�A���^�����Y���i = �����P��.�t�w�l��l�^�C�v���Z�b�g
            l_response.stopPremium_underlyingAssets = l_orderUnitRow.getStopPriceType();
            //���X�|���X.�t�w�l�p���������P�� = �����P��.�t�w�l��l���Z�b�g
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            //���X�|���X.�t�w�l�p�����������Z�q = �����P��.�����������Z�q���Z�b�g
            l_response.stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
        }
        //�����P��.����������"W�w�l"�̏ꍇ
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_response.orderCondType))
        {
            //���X�|���X.W�w�l�p�v���~�A���^�����Y���i = �����P��.�t�w�l��l�^�C�v���Z�b�g
            l_response.wlimitPremium_underlyingAssets = l_orderUnitRow.getStopPriceType();
            //���X�|���X.W�w�l�p���������P�� = �����P��.�t�w�l��l���Z�b�g
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            //���X�|���X.W�w�l�p�����������Z�q = �����P��.�����������Z�q���Z�b�g
            l_response.wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
            //���X�|���X.W�w�l�p�����P���敪 
            //���X�|���X.W�w�l�p�����P��
            if (l_orderUnitRow.getWLimitPrice() == 0)
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                l_response.wLimitPrice = null;
            }
            else
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
            }
            
            //���X�|���X.W�w�l�p���s����
            l_response.wlimitExecCondType = 
                WEB3IfoDataAdapter.getExecutionCondByPr(l_orderUnitRow.getWLimitExecCondType()); 
        }
        
        //���X�|���X.W�w�l�p�L����ԋ敪
        l_response.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
               
        //���X�|���X.W�w�l�p�֑ؑO�����P��
        l_response.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;
               
        //���X�|���X.W�w�l�p�֑ؑO���s����
        l_response.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;
          
        //���X�|���X.������t�� = �����P��.�󒍓���
        l_response.orderDate = l_orderUnitRow.getReceivedDateTime();
        //���X�|���X.������ԋ敪 = get������ԋ敪()�̕Ԃ�l
        l_response.orderState = l_strOrderStatus;
        //���X�|���X.�J�z�G���[�R�[�h = get������ԋ敪()�̕Ԃ�l��"�J�z���s"�̏ꍇ�̂݁A�����P��.�����G���[���R�R�[�h���Z�b�g
        if (WEB3OrderStatusDef.NOT_TRANSFERED.equals(l_strOrderStatus))
        {
            l_response.transferErrCode = l_orderUnitRow.getErrorReasonCode();
        }
        //���X�|���X.����ԋ敪 = get����ԋ敪()�̕Ԃ�l
        l_response.execType = l_strExecType;
               
        //���X�|���X.�x���敪
        l_response.delayDiv = l_strDelayDiv;    
               
        //���X�|���X.�蓮�����\�t���O
        l_response.manualFlag = l_blnManualOrderPossible;

        //���X�|���X.�����������敪 = �����P��.����������
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();
                          
        //���X�|���X.���v���~�A��/�����Y���i = �����P��.���t�w�l��l�^�C�v
        l_response.orgPremium_underlyingAssets = l_orderUnitRow.getOrgStopPriceType();
          
        if(!l_orderUnitRow.getOrgStopOrderPriceIsNull())      
        {
            //���X�|���X.�����������P�� = �����P��.���t�w�l��l
            l_response.orgOrderCondPrice = 
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }      
                
        //���X�|���X.�������������Z�q = �����P��.�������������Z�q
        l_response.orgCondOperator = l_orderUnitRow.getOrgOrderCondOperator();
                
        //���X�|���X.��W�w�l�p�����P���敪
        String l_strOrgWLimitOrderPriceDiv = WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);
        l_response.orgWLimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;
               
        //���X�|���X.��W�w�l�p�����P��
        if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))    
        {
            l_response.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }
               
        //���X�|���X.��W�w�l�p���s����
        l_response.orgWlimitExecCondType = WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //���X�|���X.�[��O�J�z�Ώۃt���O
        l_response.eveningSessionCarryoverFlag =
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

        //���X�|���X.����敪
        l_response.sessionType = l_orderUnitRow.getSessionType();

        //<���n���>(*2)
        //��肪����ꍇ(�����P��.isUnexecuted��false)�̂݃Z�b�g
        if (!l_orderUnit.isUnexecuted())
        {
            //���X�|���X.��n�� = �����P��.��n��
            l_response.deliveryDate = WEB3DateUtility.toDay(l_orderUnit.getDeliveryDate());
                
            WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProduct.getUnderlyingProductCode());
            WEB3IfoTradedProductImpl l_tradedProduct = (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct();
            double l_dblExecutedAmount = l_orderUnit.getExecutedAmount();
            double l_ExecutedQuantity = l_orderUnit.getExecutedQuantity();
            double l_dblUnitsize = l_tradedProduct.getUnitSize();

            BigDecimal l_bdExecutedAmount = new BigDecimal(l_dblExecutedAmount + "");
            BigDecimal l_bdExecutedQuantity = new BigDecimal(l_ExecutedQuantity + "");
            BigDecimal l_bdUnitsize = new BigDecimal(l_dblUnitsize + "");
            //���X�|���X.���P�� = �����P��.���v�����z �� �����P��.��萔�� �� �w���搔(�~�����͎l�̌ܓ�)
            double l_dblExecPrice = l_bdExecutedAmount.divide(
                l_bdExecutedQuantity, 10, BigDecimal.ROUND_HALF_UP).divide(
                    l_bdUnitsize, 0, BigDecimal.ROUND_HALF_UP).doubleValue();
            l_response.execPrice = WEB3StringTypeUtility.formatNumber(l_dblExecPrice);
            //���X�|���X.��萔�� = �����P��.��萔��
            l_response.execQuantity = WEB3StringTypeUtility.formatNumber(l_ExecutedQuantity);
            //���X�|���X.�����z = �����P��.���v�����z
            l_response.execTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblExecutedAmount);
            //���X�|���X.��n���z = �g�����U�N�V����.getNetAmount�̍��v�l
            l_response.deliveryPrice = WEB3StringTypeUtility.formatNumber(l_dblDeliveryPrice);
            //���X�|���X.�萔�� = �g�����U�N�V����.getCommissionFee�̍��v�l
            l_response.commission = WEB3StringTypeUtility.formatNumber(l_bdCommission.doubleValue());
            //���X�|���X.����� = �g�����U�N�V����.getCommissionFeeTax�̍��v�l
            l_response.consumptionTax = WEB3StringTypeUtility.formatNumber(l_bdConsumptionTax.doubleValue());             
            //���X�|���X.�������ڍז��
            WEB3OptionsExecuteUnit[] l_executeDetailUnit =
                new WEB3OptionsExecuteUnit[l_orderExecutions.length];
            for (int i = 0; i < l_orderExecutions.length; i++)
            {
                l_executeDetailUnit[i] = new WEB3OptionsExecuteUnit();
     
                //�����w���I�v�V�����������ڍו������.�������̐ݒ�
                l_executeDetailUnit[i].executionTimestamp =
                    l_orderExecutions[i].getExecutionTimestamp();
                //�����w���I�v�V�����������ڍו������.��芔���̐ݒ�
                l_executeDetailUnit[i].execQuantity =
                    WEB3StringTypeUtility.formatNumber(l_orderExecutions[i].getExecutionQuantity());
                //�����w���I�v�V�����������ڍו������.���P���̐ݒ�
                l_executeDetailUnit[i].execPrice =
                    WEB3StringTypeUtility.formatNumber(l_orderExecutions[i].getExecutionPrice());
            }
            l_response.opExecuteUnits = l_executeDetailUnit;
        }
    log.exiting(STR_METHOD_NAME);
    return l_response;
    }

    /**
     * (get���������Ɖ�)<BR>
     * �w�肳�ꂽ����ID���������P�ʃI�u�W�F�N�g�̒����������擾����<BR>
     * ��ʕ\���p�ɕҏW���A���X�|���X�ɐݒ肵�ĕԂ��B<BR>
     * �o����܂Œ����̏ꍇ�́A�������`�ŐV�̒����܂ł̒���������ΏۂƂ���B<BR>
     * <BR>
     * �V�[�P���X�}�u�iOP�������Ɖ�T�[�r�X�jget���������Ɖ�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V�������������Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsOrderHistoryResponse
     * @@throws WEB3BaseException
     * @@roseuid 409EE13103DB
     */
    protected WEB3OptionsOrderHistoryResponse getOrderActionInquiry(WEB3OptionsOrderHistoryRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderActionInquiry(WEB3OptionsOrderHistoryRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //��t���ԃ`�F�b�N�A�V�X�e��������~�`�F�b�N�B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.4 ����ID�ɑΉ����钍���P�ʃI�u�W�F�N�g���擾����B
        OrderUnit[] l_orderUnits =
            opOrderManager.getOrderUnits(Long.parseLong(l_request.id));

		if (l_orderUnits.length == 0)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00398,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"�w�肵������ID�ɊY������f�[�^�����݂��܂���B");
		}

        OrderUnit l_orderUnit = l_orderUnits[0];

        IfoOrderUnit[] l_executedOrderUnits = null;
        if (!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(
            WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit)))
        {
            //get�o����܂Œ����P��FromFirstToLast(IfoOrderUnit)
            l_executedOrderUnits = getCarriedOrderUnitFromFirstToLast(
                (IfoOrderUnit)l_orderUnit);
        }
        //1.6 ArrayList()
        ArrayList l_orderHistoryList = new ArrayList();

        //��������v�f
        IfoOrderUnit[] l_targetOrderUnits = null;
        if (!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(
            WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit)))
        {
            l_targetOrderUnits = l_executedOrderUnits;
        }
        else
        {
            //��������
            l_targetOrderUnits = new IfoOrderUnit[1];
            l_targetOrderUnits[0] = (IfoOrderUnit)l_orderUnits[0];
        }

        OrderAction[] l_orderAction = null;
        
        //��������v�f���Ƃ�Loop�����B
        for (int i = 0; i < l_targetOrderUnits.length; i++)
        {
            //1.7 get���������ꗗ(�����P�� : IfoOrderUnit)
            l_orderAction = l_targetOrderUnits[i].getOrderActions();

            IfoOrderUnit l_curOrderUnit = (IfoOrderUnit)l_targetOrderUnits[i];
            for (int j = 0; j < l_orderAction.length; j++)
            {
                IfoOrderAction l_curAction = (IfoOrderAction)l_orderAction[j];
                IfoOrderActionRow l_curOrderActionRow = 
                    (IfoOrderActionRow)l_curAction.getDataSourceObject();

                //1.8 �����I�v�V�������������ꗗ�s()
                WEB3OptionsChangeCancelHistoryGroup l_action =
                    new WEB3OptionsChangeCancelHistoryGroup();

                //1.9 get���s����(PR�w)
                String l_strPRExecCond = WEB3IfoDataAdapter.getExecutionCondByPr(
                    l_curAction.getExecutionConditionType());

                //1.10 get�������e�敪()
                String l_strOrderType = WEB3IfoDataAdapter.getOrderSpecType(
                    l_curAction,
                    l_curOrderUnit);
                    
                // get��t���ʋ敪()
                String l_strAcceptedResultDiv = WEB3IfoDataAdapter.getAcceptResultType(l_curAction);                     
                //1.11 (*2)�v���p�e�B�Z�b�g
                // ����NO = ��������.��������ID
                l_action.orderActionId = String.valueOf(l_curAction.getOrderActionId());

                // ������t�� = ��������.�쐬���t
                l_action.orderDate = l_curAction.getOrderActionTimestamp();
                
                // (*3)��莞�ɍ쐬���ꂽ�����łȂ��ꍇ(��������.isUnexecuted() == true)�̂݃Z�b�g
                if (l_curAction.isUnexecuted())
                {                 
                    //1.11.1  get�v�w�l�p�L����ԋ敪(�����P�� : IfoOrderUnit)
                    String l_strWLimitEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_curAction);               
        
                    //1.11.2 get�v�w�l�p�֑ؑO�����P��(�����P�� : IfoOrderUnit)        
                    String l_strWLimitBefSwitchPrice = WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_curAction);
                    
                    //1.11.3 get�v�w�l�p�֑ؑO���s����(�����P�� : IfoOrderUnit)
                    String l_strWLimitBefSwitchExecCondType = WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_curAction);                                                    

                    // �������� = (*3)��������.��������
                    l_action.opOrderQuantity = WEB3StringTypeUtility.formatNumber(l_curAction.getQuantity());

                    // �����P���敪 = (*3)��������.isMarketOrder�̕Ԃ�l��
                    // true�̏ꍇ�́A"���s"���Afalse�̏ꍇ�́A"�w�l"���Z�b�g
                    if (l_curAction.isMarketOrder())
                    {
                        l_action.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                        l_action.limitPrice = null;
                    }
                    else
                    {
                        l_action.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                        // �����P�� = (*3)�����P���敪��"�w�l"�̏ꍇ�̂݁A
                        // ��������.�����P�� ���Z�b�g
                        l_action.limitPrice = WEB3StringTypeUtility.formatNumber(l_curAction.getPrice());
                    }

                    // ���s���� =�@@(*3)get���s����(PR�w)�̖߂�l
                    l_action.execCondType = l_strPRExecCond;

                    // ���������敪 = (*3)��������.��������
                    l_action.orderCondType = l_curOrderActionRow.getOrderConditionType();

                    // �t�w�l�p�v���~�A���^�����Y���i = (*3)��������.�������� == "�t�w�l"�̏ꍇ�̂݁A��������.�t�w�l��l�^�C�v
                    // �t�w�l�p���������P�� = (*3)��������.�������� == "�t�w�l"�̏ꍇ�̂݁A��������.�t�w�l��l
                    // �t�w�l�p�����������Z�q = (*3)��������.�������� == "�t�w�l"�̏ꍇ�̂݁A��������.�����������Z�q
                    if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                        l_curOrderActionRow.getOrderConditionType()))
                    {
                        l_action.stopPremium_underlyingAssets = l_curOrderActionRow.getStopPriceType();

                        l_action.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                            l_curOrderActionRow.getStopOrderPrice());

                        l_action.stopOrderCondOperator = l_curOrderActionRow.getOrderCondOperator();
                    }
                    // W�w�l�p�v���~�A���^�����Y���i = (*3)��������.�������� == "W�w�l"�̏ꍇ�̂݁A��������.�t�w�l��l�^�C�v
                    // W�w�l�p���������P�� = (*3)��������.�������� == "W�w�l"�̏ꍇ�̂݁A��������.�t�w�l��l
                    // W�w�l�p�����������Z�q = (*3)��������.�������� == "W�w�l"�̏ꍇ�̂݁A��������.�����������Z�q
                    // W�w�l�p�����P���敪 = (*3)��������.�������� == "W�w�l"�̏ꍇ�̂݁A(W�w�l)�����w�l��0�̏ꍇ��"���s"�A0�ȊO�̏ꍇ��"�w�l"
                    // W�w�l�p�����P�� = (*3)��������.�������� == "W�w�l"&& ��������.(W�w�l)�����w�l != 0(�w�l)�̏ꍇ�̂݁A��������.(W�w�l)�����w�l
                    else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                        l_curOrderActionRow.getOrderConditionType()))
                    {
                        l_action.wlimitPremium_underlyingAssets = l_curOrderActionRow.getStopPriceType();

                        l_action.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                            l_curOrderActionRow.getStopOrderPrice());

                        l_action.wlimitOrderCondOperator = l_curOrderActionRow.getOrderCondOperator();
                        
                        if (l_curOrderActionRow.getWLimitPrice() == 0)
                        {
                            l_action.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                            l_action.wLimitPrice = null;
                        }
                        else
                        {
                            l_action.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

                            l_action.wLimitPrice = WEB3StringTypeUtility.formatNumber(
                                l_curOrderActionRow.getWLimitPrice());
                        }
                        
                        //W�w�l�p���s����
                        l_action.wlimitExecCondType = 
                            WEB3IfoDataAdapter.getExecutionCondByPr(l_curOrderActionRow.getWLimitExecCondType()); 
                        
                    }

                    //W�w�l�p�L����ԋ敪
                    l_action.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;                    
        
                    //W�w�l�p�֑ؑO�����P��
                    l_action.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;
        
                    //W�w�l�p�֑ؑO���s����
                    l_action.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

                    //�����������敪 = �����P��.����������
                    l_action.orgOrderCondType = l_curOrderActionRow.getOrgOrderConditionType();
                
                    //���v���~�A��/�����Y���i = �����P��.���t�w�l��l�^�C�v
                    l_action.orgPremium_underlyingAssets = l_curOrderActionRow.getOrgStopPriceType();
                
                    if(!l_curOrderActionRow.getOrgStopOrderPriceIsNull())      
                    {
                        //�����������P�� = �����P��.���t�w�l��l
                        l_action.orgOrderCondPrice = 
                            WEB3StringTypeUtility.formatNumber(l_curOrderActionRow.getOrgStopOrderPrice());                
                    } 

                    //�������������Z�q = �����P��.�������������Z�q
                    l_action.orgCondOperator = l_curOrderActionRow.getOrgOrderCondOperator();
                
                    //��W�w�l�p�����P���敪
                    String l_strOrgWLimitOrderPriceDiv = WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_curAction);
                    l_action.orgWLimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;
               
                    //��W�w�l�p�����P��
                    if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))    
                    {
                        l_action.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_curAction);
                    }
               
                    //��W�w�l�p���s����
                    l_action.orgWlimitExecCondType = WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_curAction);
                    
                    // �����L������  = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l��"�o����܂Œ���"�̏ꍇ�̂�
                    // ��������.�����������t
                    if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(
                        WEB3IfoDataAdapter.getExpirationDateType(l_curOrderUnit)))
                    {
                        l_action.expirationDate = WEB3DateUtility.toDay(l_curOrderActionRow.getExpirationDate());
                    }                   
                    
                    //�[��O�J�z�Ώۃt���O
                    l_action.eveningSessionCarryoverFlag =
                        WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_curOrderUnit);

                    //����敪
                    l_action.sessionType =
                        ((IfoOrderUnitRow)l_curOrderUnit.getDataSourceObject()).getSessionType();
                }

                //(*4)��肪����ꍇ(��������.isUnexecuted��false)�̂݃Z�b�g
                if (l_curAction.isUnexecuted() == false)
                {
                    //��萔�� = (*4)��������.��萔��
                    l_action.execQuantity = WEB3StringTypeUtility.formatNumber(
                        l_curAction.getExecutionQuantity());

                    //���P�� = (*4)��������.���P��
                    l_action.execPrice = WEB3StringTypeUtility.formatNumber(
                        l_curAction.getExecutionPrice());
                }

                //�������e�敪 = get�������e�敪()�̖߂�l
                l_action.orderType = l_strOrderType;

                //���l(���Ϗ���) = ��������.���Ϗ����iNULL�̏ꍇ��NULL���Z�b�g)
                l_action.closingOrder = l_curOrderActionRow.getClosingOrder();

                //��t���ʋ敪 = get��t���ʋ敪()�̖߂�l
                l_action.acceptedResultDiv = l_strAcceptedResultDiv;

                //1.12 add(�����w���I�v�V�������������ꗗ�s : Object)
                l_orderHistoryList.add(l_action);                
            }
        }

        //1.13 toArray()
        WEB3OptionsChangeCancelHistoryGroup[] l_changeCancelHistoryGroup = 
            new WEB3OptionsChangeCancelHistoryGroup[l_orderHistoryList.size()];
        l_orderHistoryList.toArray(l_changeCancelHistoryGroup);

        //1.14 createResponse()
        WEB3OptionsOrderHistoryResponse l_response = 
            (WEB3OptionsOrderHistoryResponse)l_request.createResponse();

        //1.15 ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g���A�ԋp����B
        l_response.opChangeCancelHistoryGroups = l_changeCancelHistoryGroup;        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ԍό��ʈꗗ)<BR>
     * �w�肳�ꂽ����ID���������P�ʃI�u�W�F�N�g�̕ԍό��ʏ����擾����<BR>
     * ��ʕ\���p�ɕҏW���A���X�|���X�ɐݒ肵�ĕԂ��B<BR>
     * �ԍϒ����̏ꍇ�̂݁A�R�[�������B<BR>
     * <BR>
     * �V�[�P���X�}�u�iOP�������Ɖ�T�[�r�X�jget�ԍό��ʈꗗ�v�Q�ƁB<BR>
     * <BR>
     * =============================================== <BR>
     *    �V�[�P���X�} : (�敨�������Ɖ�T�[�r�X)get�ԍό��ʈꗗ <BR>
     *    ��̈ʒu     : 1.5 (*)�����J�e�S���`�F�b�N�ԍϒ����ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(�����P��.�����J�e�S�� != �hOP�ԍϒ���)�h)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u�����ΏۊO�v�̗�O��throw����B<BR>
     *          class�@@�@@�@@�@@: WEB3BusinessLayerException <BR>
     *          tag          �@@: SYSTEM_ERROR_80025 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V�����ԍό��ʈꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginContractListResponse
     * @@throws WEB3BaseException
     * @@roseuid 409EE1CD0060
     */
    protected WEB3OptionsCloseMarginContractListResponse getSettleContractList(WEB3OptionsCloseMarginContractListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSettleContractList(WEB3OptionsCloseMarginContractListRequest)";

        log.entering(STR_METHOD_NAME);
        
        WEB3IfoPositionManagerImpl l_positionManager = 
            (WEB3IfoPositionManagerImpl)tradingModule.getPositionManager();
        WEB3IfoFinTransactionManagerImpl l_finTransactionManager =
            (WEB3IfoFinTransactionManagerImpl)tradingModule.getFinTransactionManager();
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)finApp.getFinObjectManager();
        
        WEB3OptionsCloseMarginContractListResponse l_response = null;
        
        try
        {
            //���N�G�X�g�f�[�^�̐��������`�F�b�N����
            l_request.validate();

            //��t���ԃ`�F�b�N�A�V�X�e��������~�`�F�b�N
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            long l_lngOrderid = Long.parseLong(l_request.id);
            OrderUnit[] l_orderUnits = (OrderUnit[])
                opOrderManager.getOrderUnits(l_lngOrderid);

		    if (l_orderUnits.length == 0)
		    {
			    throw new WEB3BusinessLayerException(
				    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
				    this.getClass().getName() + "." + STR_METHOD_NAME,
				    "�w�肵������ID�ɊY������f�[�^�����݂��܂���B");
		    }

            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject();
        
            //�ԍϒ����ȊO�̏ꍇ
            if (!OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnits[0].getOrderCateg()))
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80025,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }

            IfoContractSettleOrderUnit l_ifoContractSettleOrderUnit = 
                (IfoContractSettleOrderUnit)l_orderUnits[0];
            IfoClosingContractSpec[] l_arrIfoClosingContractSpec =
                l_ifoContractSettleOrderUnit.getContractsToClose();
        
            //�����P�ʂɊY������ԍώw���񂪎擾�ł��Ȃ������ꍇ    
            if (l_arrIfoClosingContractSpec.length == 0)
            {
			    throw new WEB3BusinessLayerException(
				    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
				    getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //reset�����R�[�h
            IfoContract l_ifoContract1 = (IfoContract)l_positionManager.getContract(l_arrIfoClosingContractSpec[0].getContractId());
            IfoProduct l_ifoProduct1 = (IfoProduct)l_ifoContract1.getProduct();
            WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProduct1.getUnderlyingProductCode());        
        
            //�����w���I�v�V�����ԍό��ʈꗗ���׍s�I�u�W�F�N�g���i�[����ԍό��ʖ��׍s���X�g�𐶐�
            ArrayList l_closingContractList = new ArrayList();
            
            //�����ʃJ�E���^�[
            int l_intCloseMarginOrderNumber = 0; 

            //���ʕԍώw����v�f���Ƃ�Loop����
            for (int i = 0; i < l_arrIfoClosingContractSpec.length; i++)
            {
                //�����ʃJ�E���^�[�̃J�E���g�A�b�v
                ++ l_intCloseMarginOrderNumber;
                
                IfoClosingContractSpec l_curIfoClosingContractSpec = l_arrIfoClosingContractSpec[i];

                //�ԍϒ������ʂ��擾����
                double l_dblQuantity = l_curIfoClosingContractSpec.getQuantity();
            
                // �ԍϒ������ʂ�0�̏ꍇ�A���X�g�ւ̒ǉ����s��Ȃ�                 
                if (l_dblQuantity == 0.0D)                  
                {                   
                    //�����ʃJ�E���^�[�̃J�E���g�_�E��
                    -- l_intCloseMarginOrderNumber;
                    continue;               
                }                   

                //�ԍϖ�萔�ʂ��擾����
                double l_dblExecutedQuantity = l_curIfoClosingContractSpec.getExecutedQuantity();
                //����ID���擾����
                long l_lngContractId = l_curIfoClosingContractSpec.getContractId();
                //�ԍώw����ɂЂ��Â����ʃI�u�W�F�N�g���擾����
                WEB3IfoContractImpl l_ifoContract = 
                    (WEB3IfoContractImpl)l_positionManager.getContract(l_lngContractId);
                //���P�����擾����
                double l_dblContractPrice = l_ifoContract.getContractPrice();
                //�������擾����
                Date l_datOpenDate = l_ifoContract.getOpenDate();
                //����������擾����
                double l_dblExecTotalPrice = l_ifoContract.getContractExecutedAmount(l_dblQuantity);
                //���萔�������
                double l_dblCommissionConsumptionTax = 0;
                //���萔��
                double l_dblCommission = 0;
                //�����z
                BigDecimal l_bdExecuteAmount = new BigDecimal("0");
                //���v      
                BigDecimal l_bdLostProfit = new BigDecimal("0");
            
                //�ԍϖ����A�ꕔ���̏ꍇ
                if (l_dblQuantity > l_dblExecutedQuantity)
                {
                    //���萔�����擾����
                    l_dblCommission =
                        l_ifoContract.getContractCommission(l_dblQuantity - l_dblExecutedQuantity);
              
                    //���萔������ł��擾����
                    l_dblCommissionConsumptionTax =
                        l_ifoContract.getContractCommissionConsumptionTax(l_dblQuantity - l_dblExecutedQuantity);
                }
            
                //�ԍψꕔ���A�S�����̏ꍇ
                if (l_dblExecutedQuantity > 0)
                {
                    //�����P��ID�ƌ���ID�ɊY������g�����U�N�V����(������薾��)���擾����
                    List l_transacions = l_finTransactionManager.getTransactions(l_ifoContractSettleOrderUnit.getOrderUnitId(),l_ifoContract.getContractId());
                    for (int j = 0; j < l_transacions.size(); j++)
                    {
                        IfoFinTransactionRow l_transacionRow = (IfoFinTransactionRow)l_transacions.get(j); 
                        BigDecimal l_bdQuantity = new BigDecimal("" + l_transacionRow.getQuantity());
                        BigDecimal l_bdPrice = new BigDecimal("" + l_transacionRow.getPrice());
                        BigDecimal l_bdCapitalGain = new BigDecimal("" + l_transacionRow.getCapitalGain());
                        BigDecimal l_bdImportedSetupFee = new BigDecimal("" + l_transacionRow.getImportedSetupFee());
                        BigDecimal l_bdCommission = new BigDecimal("" + l_dblCommission);
                        BigDecimal l_bdImportedSetupFeeTax = new BigDecimal("" + l_transacionRow.getImportedSetupFeeTax());
                        BigDecimal l_bdCommissionConsumptionTax = new BigDecimal("" + l_dblCommissionConsumptionTax);
                        l_bdExecuteAmount = l_bdQuantity.multiply(l_bdPrice).add(l_bdExecuteAmount);
                        l_bdLostProfit = l_bdCapitalGain.add(l_bdLostProfit);
                        l_bdCommission = l_bdImportedSetupFee.add(l_bdCommission);
                        l_dblCommission = l_bdCommission.doubleValue();
                        l_bdCommissionConsumptionTax = l_bdImportedSetupFeeTax.add(l_bdCommissionConsumptionTax);
                        l_dblCommissionConsumptionTax = l_bdCommissionConsumptionTax.doubleValue();
                    }
                }

                //�����w���I�v�V�����ԍό��ʈꗗ���׍s�I�u�W�F�N�g�𐶐�����
                WEB3OptionsCloseMarginContractGroup l_opCloseMarginContractGroup;
                l_opCloseMarginContractGroup = new WEB3OptionsCloseMarginContractGroup();
                l_opCloseMarginContractGroup.contractPrice = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
                l_opCloseMarginContractGroup.openDate = l_datOpenDate;
                l_opCloseMarginContractGroup.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
                if (l_orderUnitRow.getLimitPrice() == 0)
                {
                    l_opCloseMarginContractGroup.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    l_opCloseMarginContractGroup.limitPrice = null;
                }
                else
                {
                    l_opCloseMarginContractGroup.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_opCloseMarginContractGroup.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
                }
            
                if(l_dblExecutedQuantity > 0)
                {
                    BigDecimal l_bdExecutedQuantity = new BigDecimal("" + l_dblExecutedQuantity);
                    //��萔�� = �敨OP�ԍώw����.�ԍϖ�萔��
                    l_opCloseMarginContractGroup.execQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
                    l_opCloseMarginContractGroup.execPrice = WEB3StringTypeUtility.formatNumber(
                        l_bdExecuteAmount.divide(l_bdExecutedQuantity,
                        0,
                        BigDecimal.ROUND_HALF_UP).doubleValue());
                    l_opCloseMarginContractGroup.income = WEB3StringTypeUtility.formatNumber(l_bdLostProfit.doubleValue());
                }
                l_opCloseMarginContractGroup.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblCommission);
                l_opCloseMarginContractGroup.contractCommissionConsumptionTax =
                    WEB3StringTypeUtility.formatNumber(l_dblCommissionConsumptionTax);
                l_opCloseMarginContractGroup.contractExecTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblExecTotalPrice);
                l_opCloseMarginContractGroup.closeMarginOrderNumber = String.valueOf(l_intCloseMarginOrderNumber);

                //����敪
                l_opCloseMarginContractGroup.sessionType =
                    ((IfoContractRow)l_ifoContract.getDataSourceObject()).getSessionType();

                //�v���p�e�B���Z�b�g���������w���I�v�V�����ԍό��ʈꗗ���׍s�I�u�W�F�N�g��ԍό��ʈꗗ���׍s���X�g�ɒǉ�
                l_closingContractList.add(l_opCloseMarginContractGroup);
            }

            //createResponse()
            l_response = (WEB3OptionsCloseMarginContractListResponse)l_request.createResponse();
			
            l_response.marketCode =
                l_gentradeFinObjectManager.getMarket(l_ifoContract1.getMarketId()).getMarketCode();
            l_response.productName = ((IfoProductRow)l_ifoProduct1.getDataSourceObject()).getStandardName();
            if (l_ifoContract1.isLong())
            {
                l_response.contractType = WEB3IfoContractTypeDef.OPEN_BUY;
            }
            else
            {
                l_response.contractType = WEB3IfoContractTypeDef.OPEN_SELL;
            }
            l_response.closingOrder = l_orderUnitRow.getClosingOrder();
            l_response.closeMarginContractGroups = 
                (WEB3OptionsCloseMarginContractGroup[])l_closingContractList.toArray
                (new WEB3OptionsCloseMarginContractGroup[l_closingContractList.size()]);
        }
        catch(NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
    
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�����R�[�h����)<BR>
     * �w������̕ێ����銔���w���I�v�V���������̖����R�[�h�Ɩ������̈ꗗ���擾����B<BR>
     * <BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�NULL��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u�iOP�������Ɖ�T�[�r�X�jcreate�����R�[�h���́v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V�����������Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3FuturesOptionsProductCodeNameUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 409F610B01BD
     */
    protected WEB3FuturesOptionsProductCodeNameUnit[] createProductCodeName
        (WEB3GentradeSubAccount l_subAccount, 
        WEB3OptionsExecuteReferenceRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createProductCodeName(WEB3GentradeSubAccount,WEB3OptionsExecuteReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //����������������쐬����
        String l_strSerachCond = createQueryString();

        //���������f�[�^�R���e�i���쐬����
        String[] l_SearchCondDataContainer = createQueryContainer();

        //���N�G�X�g�f�[�^.�����w���敨�I�v�V�����\�[�g�L�[
        String l_strSort = createSortCond(null);
        
        //�w������Ɉ�v���钍���̒����P�ʃI�u�W�F�N�g�̈ꗗ��ԋp����B
        List l_orderUnitList = opOrderManager.getOrderUnits(
            l_subAccount,
            ProductTypeEnum.IFO,
            l_strSerachCond,
            l_SearchCondDataContainer,
            l_strSort
            );

        //get�����P�ʈꗗ�̕Ԃ�l��NULL�̏ꍇ�ɂ́ANULL��ԋp���I������B
        if (l_orderUnitList == null || l_orderUnitList.size() == 0)
        {
            return null;
        }

        TreeMap l_productList = new TreeMap();
   
        //�����P�ʗv�f���Ƃ�Loop�����B
        int l_intListLength = l_orderUnitList.size();
        for (int i = 0; i < l_intListLength; i++)
        {
            //�����P�ʂ̎擾
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderUnitList.get(i);
            //�����̎擾
            WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_orderUnit.getProduct();
            IfoProductRow l_productRow = (IfoProductRow)l_ifoProduct.getDataSourceObject();
            //reset�����R�[�h
            WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProduct.getUnderlyingProductCode());

            //���N�G�X�g.�Ɖ�敪 = "��������ꗗ"�̏ꍇ�A��������\�`�F�b�N���s��
            if (WEB3IfoReferenceTypeDef.CORRECTION_LIST.equals(l_request.referenceType))
            {
                //��������s�̏ꍇ�A�ȍ~�̏������s��Ȃ�
                if (isChangeCancelEnable(l_subAccount, l_orderUnit) == false)
                {
                    continue;
                }
            }
            
            String l_strProductCode = l_productRow.getProductCode();
            String l_strProductName = l_productRow.getStandardName();

            if (!l_productList.containsKey(l_strProductCode))
            {
                WEB3FuturesOptionsProductCodeNameUnit l_productCodeName
                     = new WEB3FuturesOptionsProductCodeNameUnit();
                l_productCodeName.productCode = l_strProductCode;
                l_productCodeName.productName = l_strProductName;
                l_productList.put(
                    l_productCodeName.productCode,
                    l_productCodeName
                    );
            }
        }
        int l_size = l_productList.size();
        if(l_size == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        WEB3FuturesOptionsProductCodeNameUnit[] l_retrunValues = new WEB3FuturesOptionsProductCodeNameUnit[l_size];
        l_productList.values().toArray(l_retrunValues);
        
        log.exiting(STR_METHOD_NAME);
        return l_retrunValues;
    }

    /**
     * (create�������Ɖ�)<BR>
     * �������Ɖ��ʁ^��������ꗗ��ʂɕ\�����钍�����Ɖ���P�ʂ̈ꗗ��<BR>
     * �쐬����B<BR>
     * <BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�NULL��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u�iOP�������Ɖ�T�[�r�X�jcreate�������Ɖ�v�Q�ƁB<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@param l_request - �����w���I�v�V�����������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@param l_response - �����w���I�v�V�����������Ɖ�X�|���X�I�u�W�F�N�g
     * @@param l_ifoProduct - �敨OP�����I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3OptionsExecuteGroup[]
     * @@throws WEB3BaseException
     * @@roseuid 409F8859009C
     */
    protected WEB3OptionsExecuteGroup[] createOrderExecutedInquiry
        (WEB3GentradeSubAccount l_subAccount, 
         WEB3OptionsExecuteReferenceRequest l_request,
         WEB3OptionsExecuteReferenceResponse l_response,
         WEB3IfoProductImpl l_ifoProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOrderExecutedInquiry()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionsExecuteGroup[] l_ret = null;

        //1.1 ����������������쐬����
        String l_strSerachCond =
            createQueryString(l_ifoProduct, l_request.orderBizDate, l_request.orderCondType);

        //1.2 ���������f�[�^�R���e�i���쐬����
        String[] l_SearchCondDataContainer =
            createQueryContainer(l_ifoProduct,l_request.orderBizDate, l_request.orderCondType);

        //1.3 �\�[�g����
        String l_strSort = createSortCond(l_request.futOpSortKeys);

        //�w������Ɉ�v���钍���̒����P�ʃI�u�W�F�N�g�̈ꗗ��ԋp����B
        List l_orderUnitList = opOrderManager.getOrderUnits(
            l_subAccount,
            ProductTypeEnum.IFO,
            l_strSerachCond,
            l_SearchCondDataContainer,
            l_strSort
            );

        //1.4 get�����P�ʈꗗ�̕Ԃ�l��NULL�̏ꍇ�ɂ́ANULL��ԋp���I������B
        if (l_orderUnitList == null || l_orderUnitList.size() == 0)
        {
            return null;
        }

        //1.5 ���N�G�X�g�f�[�^.����ԋ敪��NULL�̏ꍇ�̂݁A�ȉ��̏��������{����B
        if (l_request.execType != null)
        {
            ListIterator l_iterator = l_orderUnitList.listIterator();
            while (l_iterator.hasNext())
            {
                IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_iterator.next();
                boolean l_excuteStatusDivIs = isDesignateExecutedStatus(l_request.execType, l_orderUnit);
                //�w��̖���ԂƈقȂ�ꍇ�A�����P�ʈꗗ���珜��
                if (l_excuteStatusDivIs == false)
                {
                    l_iterator.remove();
                }
            }
        }
        //�����P�ʈꗗ�v�f����0�ɂȂ����ꍇ��NULL��ԋp���I������B
        if (l_orderUnitList.size() == 0)
        {
            return null;
        }

        //1.6 �J�z�������P�ʂ̏���
        IfoOrderUnit[] l_arrOrderUnitBeforeRemove =
            (IfoOrderUnit[])l_orderUnitList.toArray(new IfoOrderUnit[l_orderUnitList.size()]);
        IfoOrderUnit[] l_arrOrderUnitAfterRemove =
            opOrderManager.removeCarryOverOriginalOrderUnit(l_arrOrderUnitBeforeRemove);

        //1.7 remove�J�z�������P�ʂ̕Ԃ�l��NULL�̏ꍇ��NULL��ԋp���I������B
        if (l_arrOrderUnitAfterRemove == null ||
            l_arrOrderUnitAfterRemove.length == 0)
        {
            return null;
        }

        //�������Ɖ���P�ʃ��X�g�𐶐�
        ArrayList l_OrderUnitList = new ArrayList(l_arrOrderUnitAfterRemove.length);
        //�����P�ʃ��X�g�𐶐�
        ArrayList l_SelectedOrderUnitList = new ArrayList(l_arrOrderUnitAfterRemove.length);
        
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)finApp.getFinObjectManager();
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)
            tradingModule.getPositionManager();

        try
        {

            for (int i = 0; i < l_arrOrderUnitAfterRemove.length; i++)
            {
                //�����P�ʂ̎擾
                IfoOrderUnit l_curOrderUnit = l_arrOrderUnitAfterRemove[i];
                IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_curOrderUnit.getDataSourceObject();

                //�s��̎擾
                Market l_market = l_finObjectManager.getMarket(l_ifoOrderUnitRow.getMarketId());

                //�����̎擾
                WEB3IfoProductImpl l_curIfoProduct = (WEB3IfoProductImpl)l_curOrderUnit.getProduct();
                IfoProductRow l_curProductRow = (IfoProductRow)l_curIfoProduct.getDataSourceObject();

                //1.8 reset�����R�[�h
                WEB3GentradeTradingTimeManagement.resetProductCode(l_curIfoProduct.getUnderlyingProductCode());

                //1.9 ��������̎擾 
                WEB3IfoTradedProductImpl l_ifoTradedProduct = (WEB3IfoTradedProductImpl)l_curOrderUnit.getTradedProduct();

                //1.10 ������ʂ̎擾
                OrderTypeEnum l_orderType = l_curOrderUnit.getOrderType();

                //is���� 
                boolean l_blnIsCallOptions = false;
                if(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType) ||
                    OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType))
                {
                    l_blnIsCallOptions = true;
                }
                //is�V�K��
                boolean l_blnIsOpenContractOrder = false;
                if(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType) ||
                  OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType))
                {
                    l_blnIsOpenContractOrder = true;
                }
                //1.11 is����K��
                boolean l_blnTradingSuspended = false;
                l_blnTradingSuspended = l_ifoTradedProduct.isTradingSuspended(
                    l_blnIsCallOptions,
                    l_blnIsOpenContractOrder);

                //1.12 ���N�G�X�g.�Ɖ�敪 = "��������ꗗ"�̏ꍇ�A��������s�Ȃ�΁A�ȍ~�̏����͍s��Ȃ�
                boolean l_blnCanChangeCancel = isChangeCancelEnable(l_subAccount, l_curOrderUnit);
                if (WEB3IfoReferenceTypeDef.CORRECTION_LIST.equals(l_request.referenceType))
                {
                    if (l_blnCanChangeCancel == false)
                    {
                        continue;
                    }
                }

                //1.13 �����w���I�v�V�����������Ɖ���P��()
                WEB3OptionsExecuteGroup l_referenceOrderUnit = new WEB3OptionsExecuteGroup();

                //1.14 �v���p�e�B�Z�b�g
                //ID =  �����P��.����ID
                l_referenceOrderUnit.id = WEB3StringTypeUtility.formatNumber(l_curOrderUnit.getOrderId());
                //������
                l_referenceOrderUnit.opProductName = l_curProductRow.getStandardName();
                //����s��
                l_referenceOrderUnit.marketCode = l_market.getMarketCode();
                // �����R�[�h
                l_referenceOrderUnit.opProductCode = l_curIfoProduct.getProductCode();
                // �w�����
                l_referenceOrderUnit.targetProductCode = l_curIfoProduct.getUnderlyingProductCode();
                // ����
                l_referenceOrderUnit.delivaryMonth = l_curIfoProduct.getMonthOfDelivery();
                // �I�v�V�������i�敪
                if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_curIfoProduct.getDerivativeType()))
                {
                    l_referenceOrderUnit.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
                }
                else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_curIfoProduct.getDerivativeType()))
                {
                    l_referenceOrderUnit.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
                }
                // �s�g���i
                l_referenceOrderUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_curIfoProduct.getStrikePrice());

                //�����\�t���O
                //�ȉ��̂����ꂩ�ɂ��Ă͂܂�ꍇ��false���Z�b�g�B�ȊO��true���Z�b�g�B
                //�E�����K���`�F�b�N���`�F�b�NNG(is����K����true)
                //�E��������\�`�F�b�N���`�F�b�NNG
                //�Evalidate���������\��Ԃ���O��throw
                WEB3IfoOrderManagerReusableValidations l_ifoOrderValidator =
                    (WEB3IfoOrderManagerReusableValidations)
                        WEB3IfoOrderManagerReusableValidations.getInstance();

                Order l_order = l_curOrderUnit.getOrder();
                
                if (l_blnTradingSuspended == true || l_blnCanChangeCancel == false)
                {
                    l_referenceOrderUnit.changeFlag = false;
                }
                else
                {
                    try
                    {
                        l_ifoOrderValidator.validateOrderForChangeability(l_order);
                        l_referenceOrderUnit.changeFlag = true;
                    }
                    catch (OrderValidationException l_ovE) 
                    {
                        l_referenceOrderUnit.changeFlag = false;
                    }
                }

                //����\�t���O
                //�ȉ��̂����ꂩ�ɂ��Ă͂܂�ꍇ��false���Z�b�g�B�ȊO��true���Z�b�g�B
                //�E��������\�`�F�b�N���`�F�b�NNG
                //�Evalidate��������\��Ԃ���O��throw                
                if (l_blnCanChangeCancel == false)
                {
                    l_referenceOrderUnit.cancelFlag = false;
                }
                else
                {
                    try
                    {
                        l_ifoOrderValidator.validateOrderForCancellation(l_order);
                        l_referenceOrderUnit.cancelFlag = true;
                    }
                    catch (OrderValidationException l_ovE)
                    {
                        l_referenceOrderUnit.cancelFlag = false;
                    }
                }

                //1.15 �������Ɖ���P�ʃ��X�g�ɒǉ�
                l_OrderUnitList.add(l_referenceOrderUnit);

                //1.16 �����P�ʃ��X�g�ɒǉ�
                l_SelectedOrderUnitList.add(l_curOrderUnit);
            }

            if(l_OrderUnitList.size() == 0)
            {
                return null;
            }

            //1.17 toArray
            l_ret = new WEB3OptionsExecuteGroup[l_OrderUnitList.size()];
            l_OrderUnitList.toArray(l_ret);

            //���X�|���X�̃y�[�W�֘A���ڐݒ�
            //1.18 ���X�|���X.ID�ꗗ�F�@@���������ɊY�����钍��ID��S�ăZ�b�g
            // �i�v�f���́A�����R�[�h���ɓ������j
            if (l_OrderUnitList != null)
            {
                int l_intOrderSize = l_ret.length;
                l_response.idList = new String[l_intOrderSize];
                for (int i = 0;i < l_intOrderSize; i++)
                {
                    l_response.idList[i] = l_ret[i].id;
                }
            }

            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            int l_intPageSize = Integer.parseInt(l_request.pageSize);

            //1.19 �\���ΏۂƂȂ钍�����Ɖ���P�ʃ��X�g�̍쐬
            WEB3PageIndexInfo l_pageIndexInfo =
                new WEB3PageIndexInfo(l_ret, l_intPageIndex, l_intPageSize);
            l_response.opExecuteGroups
                = (WEB3OptionsExecuteGroup[])l_pageIndexInfo.getArrayReturned(WEB3OptionsExecuteGroup.class);

            //���X�|���X.�\���y�[�W�ԍ�
            l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
            //���X�|���X.���y�[�W��
            l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
            //���X�|���X.�����R�[�h��
            l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());

            //�\���ΏۂƂȂ钍���P�ʃ��X�g�̍쐬
            WEB3PageIndexInfo l_ordersAtPage =
                new WEB3PageIndexInfo(l_SelectedOrderUnitList, l_intPageIndex, l_intPageSize);
            Object[] l_objReturned = l_ordersAtPage.getArrayReturned(IfoOrderUnit.class);

            for (int i = 0; i < l_response.opExecuteGroups.length; i++)
            {
                WEB3OptionsExecuteGroup l_referenceOrderUnit = l_response.opExecuteGroups[i];
                IfoOrderUnit l_curOrderUnit = (IfoOrderUnit)l_objReturned[i];
                IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_curOrderUnit.getDataSourceObject();

                //�����̎擾
                WEB3IfoProductImpl l_curIfoProduct = (WEB3IfoProductImpl)l_curOrderUnit.getProduct();

                //1.20 reset�����R�[�h
                WEB3GentradeTradingTimeManagement.resetProductCode(l_curIfoProduct.getUnderlyingProductCode());

                //��������̎擾 
                WEB3IfoTradedProductImpl l_ifoTradedProduct = (WEB3IfoTradedProductImpl)l_curOrderUnit.getTradedProduct();

                //1.21 get���s����(PR�w)
                String l_PRExcCondType = WEB3IfoDataAdapter.getExecutionCondByPr(
                    l_ifoOrderUnitRow.getExecutionConditionType());

                //1.23 get�����󋵋敪()
                String l_transactionStateType =
                    WEB3IfoDataAdapter.getTransactionStatusType(l_curOrderUnit);
                 
                //1.24 get�v�w�l�p�L����ԋ敪(�����P�� : IfoOrderUnit)
                String l_strWLimitEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_curOrderUnit);           
                
                //1.25  get�v�w�l�p�֑ؑO�����P��(�����P�� : IfoOrderUnit)
                String l_strWLimitBefSwitchPrice = WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_curOrderUnit);
                
                //1.26 get�v�w�l�p�֑ؑO���s����(�����P�� : IfoOrderUnit)
                String l_strWLimitBefSwitchExecCondType = WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_curOrderUnit);
                
                //1.27 is�蓮�����\(IfoOrderUnit)
                boolean l_blnManualOrderPossible = opOrderManager.isManualOrderPossible(l_curOrderUnit);
                
                //1.28 get�x���敪(IfoOrderUnit)
                String l_strDelayDiv = WEB3IfoDataAdapter.getDelayDiv(l_curOrderUnit);
                
                //�����A���P��
                // [�����P��.�����J�e�S�� == �hOP�V�K�������h�̏ꍇ]
                if(OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_ifoOrderUnitRow.getOrderCateg()))
                {
                    l_referenceOrderUnit.openDate = null;
                    l_referenceOrderUnit.contractPrice = null;    
                }
                // [�����P��.�����J�e�S�� == �hOP�ԍϒ����h�̏ꍇ]
                else if(OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_ifoOrderUnitRow.getOrderCateg())) 
                {
                    //���ʕԍώw���� = �ԍϒ����P��.getContractsToClose()�Ŏ擾
                    IfoContractSettleOrderUnit l_contractSettleOrderUnit = (IfoContractSettleOrderUnit)l_curOrderUnit;
                    IfoClosingContractSpec[] l_spec = l_contractSettleOrderUnit.getContractsToClose();
                        
                    if(l_ifoOrderUnitRow.getClosingOrder() == null)
                    {
                        l_referenceOrderUnit.openDate = l_positionManager.getContract(l_spec[0].getContractId()).getOpenDate();
                        l_referenceOrderUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_positionManager.getContract(l_spec[0].getContractId()).getContractPrice());
                    }
                    else
                    {
                        l_referenceOrderUnit.openDate = null;
                        double l_dblTotalContractAmount = 0.0D;
                        double l_dblTotalQuantity = 0.0D;

                        BigDecimal l_bdTotalContractAmount = new BigDecimal(l_dblTotalContractAmount + "");
                        //���ʕԍώw����̗v�f���ƂɌ�������Z�o���A�W�v����SUM�l���擾����
                        for (int j = 0;j < l_spec.length;j++)
                        {
                            //����� += (���ʕԍώw����.����ID����擾��������.���P�� * ���ʕԍώw����.�ԍϒ�������)
                            Contract l_contract = l_positionManager.getContract(l_spec[j].getContractId());
                            double l_dblQuantity = l_spec[j].getQuantity();
                            l_bdTotalContractAmount = l_bdTotalContractAmount.add(
                                new BigDecimal(l_contract.getContractPrice() + "").multiply(
                                    new BigDecimal(l_dblQuantity + "")));
                            l_dblTotalQuantity += l_dblQuantity;
                        }
                        //���P���̕��ϒl = ����� /�@@���ʕԍώw����.�ԍϒ������ʂ�SUM�l(�~�����͎l�̌ܓ�)
                        l_referenceOrderUnit.contractPrice =
                            WEB3StringTypeUtility.formatNumber(l_bdTotalContractAmount.divide(
                                new BigDecimal(l_dblTotalQuantity + ""), 0, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                }
                //����敪 = �����P��.�������
                l_referenceOrderUnit.tradingType = getPRLevelTradeTypeDiv(l_curOrderUnit.getOrderType());
                //�������� = �����P��.�󒍓���
                l_referenceOrderUnit.orderDate = l_ifoOrderUnitRow.getReceivedDateTime();
                //������ = �����P��.������
                l_referenceOrderUnit.orderBizDate = WEB3DateUtility.toDay(WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd"));
                //�������� = �����P��.��������
                l_referenceOrderUnit.opOrderQuantity = WEB3StringTypeUtility.formatNumber(l_curOrderUnit.getQuantity());
                //�����P���敪
                //�����P��
                if (l_curOrderUnit.isMarketOrder())
                {
                    l_referenceOrderUnit.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    l_referenceOrderUnit.limitPrice = null;
                }
                else
                {
                    l_referenceOrderUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_referenceOrderUnit.limitPrice = WEB3StringTypeUtility.formatNumber(l_curOrderUnit.getLimitPrice());
                }
                //���s���� = get���s����(PR�w)�̕Ԃ�l
                l_referenceOrderUnit.execCondType = l_PRExcCondType;
                //���������敪 = �����P��.��������
                l_referenceOrderUnit.orderCondType = l_ifoOrderUnitRow.getOrderConditionType();

                //�����P��.����������"�t�w�l"�̏ꍇ
                if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_referenceOrderUnit.orderCondType))
                {
                    l_referenceOrderUnit.stopPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType();
                    l_referenceOrderUnit.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());
                    l_referenceOrderUnit.stopOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
                }
                //�����P��.����������"W�w�l"�̏ꍇ
                else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_referenceOrderUnit.orderCondType))
                {
                    l_referenceOrderUnit.wlimitPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType();
                    l_referenceOrderUnit.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());
                    l_referenceOrderUnit.wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
                    if (l_ifoOrderUnitRow.getWLimitPrice() == 0D)
                    {
                        l_referenceOrderUnit.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    }
                    else
                    {
                        l_referenceOrderUnit.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                        l_referenceOrderUnit.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getWLimitPrice());
                    }
                    
                    //W�w�l�p���s����
                    l_referenceOrderUnit.wlimitExecCondType = 
                        WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getWLimitExecCondType()); 
                 }
                
                //W�w�l�p�L����ԋ敪
                l_referenceOrderUnit.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
               
                //W�w�l�p�֑ؑO�����P��
                l_referenceOrderUnit.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;
               
                //W�w�l�p�֑ؑO���s����
                l_referenceOrderUnit.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;
                           
                //(*7)��萔�ʁA���P���A�������ڍז��
                //1.29 ��肪����ꍇ(�����P��.isUnexecuted��false)�̂݃Z�b�g
                if (!l_curOrderUnit.isUnexecuted())
                {
                    //1.29.1 getExecutions()
                    OrderExecution[] l_orderExecution = l_curOrderUnit.getExecutions();

                    //��������̎擾 
                    l_ifoTradedProduct = (WEB3IfoTradedProductImpl)l_curOrderUnit.getTradedProduct();
                    //�����P��.���v�����z�̎擾
                    double l_dblExecutedAmount = l_curOrderUnit.getExecutedAmount();
                    //�����P��.��萔�ʂ̎擾
                    double l_dblExecutedQuantity = l_curOrderUnit.getExecutedQuantity();
                    //�w���搔�̎擾
                    double l_dblUnitSize = l_ifoTradedProduct.getUnitSize();
                    //���P�� = �����P��.���v�����z �� �����P��.��萔�� ���w���搔(�~�����͎l�̌ܓ�)
                    BigDecimal l_bdExecutedAmount = new BigDecimal(l_dblExecutedAmount + "");
                    BigDecimal l_bdExecutedQuantity = new BigDecimal(l_dblExecutedQuantity + "");
                    BigDecimal l_bdUnitSize = new BigDecimal(l_dblUnitSize + "");
                    double l_execPrice = l_bdExecutedAmount.divide(
                        l_bdExecutedQuantity, 10, BigDecimal.ROUND_HALF_UP).divide(
                            l_bdUnitSize, 0, BigDecimal.ROUND_HALF_UP).doubleValue();
                    l_referenceOrderUnit.execPrice = WEB3StringTypeUtility.formatNumber(l_execPrice);
                    //��萔��
                    l_referenceOrderUnit.execQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
                    //�������ڍז��
                    WEB3OptionsExecuteUnit[] l_executeUnit =
                        new WEB3OptionsExecuteUnit[l_orderExecution.length];
                    for (int j = 0; j < l_orderExecution.length; j++)
                    {
                        l_executeUnit[j] = new WEB3OptionsExecuteUnit();
                        //OP�����������Ɖ���.���P���̐ݒ�
                        l_executeUnit[j].execPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderExecution[j].getExecutionPrice());
                        //OP�����������Ɖ���.��芔���̐ݒ�
                        l_executeUnit[j].execQuantity =
                            WEB3StringTypeUtility.formatNumber(l_orderExecution[j].getExecutionQuantity());
                        //OP�����������Ɖ���.�������̐ݒ�
                        l_executeUnit[j].executionTimestamp =
                            l_orderExecution[j].getExecutionTimestamp();
                    }
                    l_referenceOrderUnit.opExecuteUnits = l_executeUnit;

                    //1.29.2 ��n���z�̎擾
                    double l_dblDeliveryAmt = opOrderManager.getNetAmount(l_curOrderUnit);
                    l_referenceOrderUnit.deliveryPrice = WEB3StringTypeUtility.formatNumber(l_dblDeliveryAmt);
                }
                         
                //�T�Z��n��� = �����P��.�T�Z��n���
                l_referenceOrderUnit.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getEstimatedPrice());
                //������ = get�����󋵋敪()�̕Ԃ�l
                l_referenceOrderUnit.transactionStateType =
                    l_transactionStateType;
                //�����L������ = get���������敪���o����܂ł̏ꍇ�̂݁A
                //�����P��.�����������t���Z�b�g�B�ȊO�ANULL���Z�b�g
                if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(
                    WEB3IfoDataAdapter.getExpirationDateType(l_curOrderUnit)))
                { 
                    l_referenceOrderUnit.expirationDate =
                                        l_curOrderUnit.getExpirationTimestamp();              
                }
                else
                {
                    l_referenceOrderUnit.expirationDate = null;          
                }
                
                if (this.getTrader() != null)
                {
                    //�����`���l�� = �����P��.���񒍕��̒����`���l�����Z�b�g
                    l_referenceOrderUnit.orderChannel = l_ifoOrderUnitRow.getOrderChanel();
                    //�����o�H�敪 = �����P��.�����o�H�敪���Z�b�g
                    l_referenceOrderUnit.orderRootDiv = l_ifoOrderUnitRow.getOrderRootDiv();
                    //�����P��.�����ID��null�̏ꍇ�̂݁A���҃R�[�h���Z�b�g
                    if (!l_ifoOrderUnitRow.getTraderIdIsNull())
                    {
                        try
                        {
                                //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrader()
                                Trader l_trader = null;
                                l_trader = l_finObjectManager.getTrader(l_curOrderUnit.getTraderId());
                                l_referenceOrderUnit.operatorCode = l_trader.getTraderCode();
                        }
                        catch (NotFoundException l_nfe)
                        {
                                //�����P��.�����ID�ɊY������f�[�^�����҃e�[�u���ɑ��݂��Ȃ��ꍇ
                                l_referenceOrderUnit.operatorCode = null;
                        }
                    }
                }
               
                //�x���敪
                l_referenceOrderUnit.delayDiv = l_strDelayDiv;
                
                //�蓮�����\�t���O
                l_referenceOrderUnit.manualFlag = l_blnManualOrderPossible;    

                //�����������敪 = �����P��.����������
                l_referenceOrderUnit.orgOrderCondType = l_ifoOrderUnitRow.getOrgOrderConditionType();
                
                //���v���~�A��/�����Y���i = �����P��.���t�w�l��l�^�C�v
                l_referenceOrderUnit.orgPremium_underlyingAssets = l_ifoOrderUnitRow.getOrgStopPriceType();
                
                if(!l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull())      
                {
                    //�����������P�� = �����P��.���t�w�l��l
                    l_referenceOrderUnit.orgOrderCondPrice = 
                        WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getOrgStopOrderPrice());                
                } 
                
                //�������������Z�q = �����P��.�������������Z�q
                l_referenceOrderUnit.orgCondOperator = l_ifoOrderUnitRow.getOrgOrderCondOperator();
                
                //��W�w�l�p�����P���敪
                String l_strOrgWLimitOrderPriceDiv = WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_curOrderUnit);
                l_referenceOrderUnit.orgWLimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;
               
                //��W�w�l�p�����P��
                if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))    
                {
                    l_referenceOrderUnit.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_curOrderUnit);
                }
               
                //��W�w�l�p���s����
                l_referenceOrderUnit.orgWlimitExecCondType = WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_curOrderUnit);

                //�[��O�J�z�Ώۃt���O
                l_referenceOrderUnit.eveningSessionCarryoverFlag =
                    WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_curOrderUnit);

                //����敪
                l_referenceOrderUnit.sessionType = l_ifoOrderUnitRow.getSessionType();
            }
        }
        catch (NotFoundException l_nfE)
        {
            String l_strMsg = "�f�[�^�擾�G���[";
            log.error(l_strMsg, l_nfE);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_ret;
    }

    /**
     * (create��������������)<BR>
     * ���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * (1)�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�<BR>
     * <BR>
     * (2)�敨�^�I�v�V�����敪�𕶎���C���X�^���X�ɐݒ�<BR>
     * <BR>
     *     " future_option_div = ?"<BR>
     * <BR>
     * (3)�������w��𕶎���C���X�^���X�ɐݒ�<BR>
     * <BR>
     * (3-1)�p�����[�^.��������NULL�i�������w��j�̏ꍇ<BR>
     * <BR>
     *     " and biz_date = ?" <BR>
     * <BR>
     * (3-2)�p�����[�^.��������NULL�i�������w��Ȃ��j�̏ꍇ<BR>
     * <BR>
     *     " and biz_date >= ?" <BR>
     * <BR>
     * (4)�p�����[�^.�敨OP������NULL�i�����w��j�̏ꍇ�A<BR>
     * ����ID�w���ǉ��i����ID�Ō������s��)<BR>
     * <BR> 
     *     " and product_id=?" <BR>
     * <BR>
     * (5)�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ�A<BR>
     * �@@�@@���������敪�w���ǉ� <BR>
     * �@@�@@�i�敨OP�����P�ʃe�[�u��.�����������ɒl���ݒ肳��Ă���ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���������������ƂɌ�������B  <BR>
     * �@@�@@�@@�����������ɒl���ݒ肳��Ă��Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�敨OP�����P�ʃe�[�u��.�������������ƂɌ�������B) <BR>
     *   <BR>
     * �@@�@@�@@" and nvl(org_order_condition_type,order_condition_type) = ?"  <BR>
     * <BR>
     * (6)������C���X�^���X��ԋp <BR>
     * @@param l_ifoProduct - �敨OP�����I�u�W�F�N�g<BR>
     * @@param l_datBizDate - ������<BR>
     * @@param l_strOrderCondType - (���������敪)<BR>
     * ���������敪<BR>
     * @@return String
     * @@roseuid 40A07C680399
     */
    protected String createQueryString(
        WEB3IfoProductImpl l_ifoProduct,Date l_datBizDate, String l_strOrderCondType)
    {
        final String STR_METHOD_NAME =
            "createQueryString(WEB3IfoProductImpl,Date,String)";

        log.entering(STR_METHOD_NAME);

        //�敨�^�I�v�V�����敪�A����є������͈̔͂𕶎���C���X�^���X�ɐݒ�
        String l_strSearchCond = " and future_option_div = ? ";

        //�p�����[�^.��������NULL�̏ꍇ
        if (l_datBizDate != null && !l_datBizDate.equals(""))
        {
            l_strSearchCond += "and biz_date = ? ";
        }
        else
        {
            l_strSearchCond += "and biz_date >= ? ";
        }
        
        //�p�����[�^.�敨OP������NULL
        if(l_ifoProduct != null)
        {
            l_strSearchCond += "and product_id = ? ";            
        }
        
        //�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ
        if (l_strOrderCondType != null)
        {
            l_strSearchCond += "and nvl(org_order_condition_type,order_condition_type) = ? "; 
        }
        log.debug("�������������� = " + l_strSearchCond);
        log.exiting(STR_METHOD_NAME);

        return l_strSearchCond;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������iwhere�ȉ��w��̕�����j�̃p�����[�^�̕�����z����쐬����B<BR>
     * <BR>
     * (1)������z��𐶐�<BR>
     * <BR>
     * (2)�敨�^�I�v�V�����敪�𕶎���z��ɃZ�b�g<BR>
     * �@@�@@�@@�敨�^�I�v�V�����敪 �� "�I�v�V����"<BR>
     * �@@�@@�@@���敨�^�I�v�V�����敪 1�F�敨�@@2�F�I�v�V����<BR>
     * <BR>
     * (3)�������w��l�𕶎���z��ɃZ�b�g <BR>
     * (3-1)�p�����[�^.��������NULL�i�������w��j�̏ꍇ <BR>
     * �������w��l �� �p�����[�^.������ <BR>
     * <BR>
     * (3-2)�p�����[�^.��������NULL�i�������w��Ȃ��j�̏ꍇ <BR>
     * �@@�@@�@@�������w��l �� �Ɩ����t(*1) <BR>
     * <BR>
     * (4)�p�����[�^.�敨OP������NULL�i�����w��j�̏ꍇ�A<BR>
     * ����ID�𕶎���z��ɃZ�b�g�i����ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@����ID �� �p�����[�^.�敨OP����.����ID,<BR>
     * <BR>
     * (5)�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ�A<BR>
     * �@@�@@�p�����[�^.���������敪�𕶎���z��ɃZ�b�g <BR>
     * <BR>
     * �@@�@@�@@���������敪 �� �p�����[�^.���������敪 <BR>
     * <BR>
     * (6)(2)�A(3)�A(4)�A(5)�ɂăp�����[�^���Z�b�g����������z���ԋp<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_ifoProduct - �敨OP�����I�u�W�F�N�g<BR>
     * @@param l_datBizDate - ������<BR>
     * @@param l_strOrderCondType - (���������敪)<BR>
     * ���������敪<BR>
     * @@return String[]
     * @@roseuid 40A07C6803C8
     */
    protected String[] createQueryContainer(
        WEB3IfoProductImpl l_ifoProduct,Date l_datBizDate, String l_strOrderCondType)
    {
        final String STR_METHOD_NAME =
            "createQueryContainer(WEB3IfoProductImpl,Date,String)";

        log.entering(STR_METHOD_NAME);

        //���������̐��ʂ�ݒ肷��B
        int l_intCondCount = 2;

        if (l_ifoProduct != null)
        {
            l_intCondCount++;
        }
        if (l_strOrderCondType != null)
        {
            l_intCondCount++;
        }

        //������z��𐶐�
        String[] l_strParam = new String[l_intCondCount];

        //�敨�^�I�v�V�����敪: 2 "�I�v�V����"
        l_strParam[0] = WEB3FuturesOptionDivDef.OPTION;

        //�p�����[�^.��������NULL
        if (l_datBizDate != null && !"".equals(l_datBizDate))
        {
            l_strParam[1] = WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd");
        }
        else
        {
            //�Ɩ����t���擾                 
            Date l_datDate = GtlUtils.getTradingSystem().getBizDate();
            l_strParam[1] = WEB3DateUtility.formatDate(l_datDate,"yyyyMMdd");
        }

        //�p�����[�^.�敨OP������NULL
        if (l_ifoProduct != null)
        {
            //����ID���Z�b�g����
            l_strParam[2] = String.valueOf(l_ifoProduct.getProductId());
            //�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ
            if (l_strOrderCondType != null)
            {
                l_strParam[3] = l_strOrderCondType;
            }
        }
        else
        {
            //�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ
            if (l_strOrderCondType != null)
            {
                l_strParam[2] = l_strOrderCondType;
            }
        }
        log.debug("���������f�[�^�R���e�i�z�� = " + l_strParam.length);
        log.exiting(STR_METHOD_NAME);

        return l_strParam;
    }


    /**
     * (create��������������)<BR>
     * ���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * (1)�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�<BR>
     * <BR>
     * (2)�敨�^�I�v�V�����敪�𕶎���C���X�^���X�ɐݒ�<BR>
     * <BR>
     *     " future_option_div = ?"<BR>
     * <BR>
     * (3)�������w��𕶎���C���X�^���X�ɐݒ�<BR>
     * <BR>
     *     " and biz_date >= ?" <BR>
     * <BR>
     * (4)������C���X�^���X��ԋp <BR>
     */
    protected String createQueryString()
    {
        final String STR_METHOD_NAME = "createQueryString()";
        log.entering(STR_METHOD_NAME);

        //�敨�^�I�v�V�����敪�A����є������͈̔͂𕶎���C���X�^���X�ɐݒ�
        String l_strSearchCond = " and future_option_div = ? ";

        //�������w��𕶎���C���X�^���X�ɐݒ�
        l_strSearchCond += "and biz_date >= ? ";

        log.exiting(STR_METHOD_NAME);
        return l_strSearchCond;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������iwhere�ȉ��w��̕�����j�̃p�����[�^�̕�����z����쐬����B<BR>
     * <BR>
     * (1)������z��𐶐�<BR>
     * <BR>
     * (2)�敨�^�I�v�V�����敪�𕶎���z��ɃZ�b�g<BR>
     * �@@�@@�@@�敨�^�I�v�V�����敪 �� "�I�v�V����"<BR>
     * �@@�@@�@@���敨�^�I�v�V�����敪 1�F�敨�@@2�F�I�v�V����<BR>
     * <BR>
     * (3)�������w��l�𕶎���z��ɃZ�b�g <BR>
     * �@@�@@�@@�������w��l �� �Ɩ����t(*1)�̑O�c�Ɠ�<BR>
     * <BR>
     * (4)(2)�A(3)�ɂăp�����[�^���Z�b�g����������z���ԋp<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@return String[]
     */
    protected String[] createQueryContainer() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryContainer()";
        log.entering(STR_METHOD_NAME);

        //������z��𐶐�
        String[] l_strParam = new String[2];

        //�敨�^�I�v�V�����敪: 2 "�I�v�V����"
        l_strParam[0] = WEB3FuturesOptionDivDef.OPTION;

        //�Ɩ����t�̑O�c�Ɠ����擾
        Date l_datDate = GtlUtils.getTradingSystem().getBizDate();
		Date l_datFormerDate = (Date)WEB3GentradeUtils.getBizDate(l_datDate, -1);

        //�������w��l�𕶎���z��ɃZ�b�g
        l_strParam[1] = WEB3DateUtility.formatDate(l_datFormerDate,"yyyyMMdd");

        log.exiting(STR_METHOD_NAME);
        return l_strParam;
    }

    /**
     * (create�\�[�g����)<BR>
     *�\�[�g������������쐬���Ԃ��B<BR>
     *<BR>
     *(1)�p�����[�^.�\�[�g�L�[ == null�̏ꍇ�́A <BR>
     *�@@"����ID(*) ����"�̃\�[�g�����������ԋp����B <BR>
     *<BR>
     *�@@(*)�����P�ʃe�[�u��.����ID<BR>
     *<BR>
     *(2)�p�����[�^.�\�[�g�L�[.�L�[���ڂ̐����A<BR>
     *�Ή�����e�[�u���̗񕨗����������^�~���w���t�����\�[�g������������Z�b�g���Ă����B<BR>
     *<BR>
     *�@@�@@  �E�L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�<BR>
     *�@@�@@�@@�@@�@@�@@�E�����R�[�h�@@�@@ �@@�@@  �F�����P�ʃe�[�u���D����ID<BR>
     *�@@�@@�@@�@@�@@�@@�E����s��@@�@@ �@@�@@  �@@�F�����P�ʃe�[�u���D�s��ID<BR>
     *�@@�@@�@@�@@�@@�@@�E����敪�@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�������<BR>
     *�@@�@@�@@�@@�@@�@@�E�������ԁ@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�󒍓���<BR>
     *�@@�@@�@@�@@�@@�@@�E�������@@�@@�@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D������<BR>
     *�@@�@@�@@�@@�@@�@@�E�����L�������@@�@@�@@�F�����P�ʃe�[�u���D�����������t<BR>
     *<BR>
     *�@@�@@�E�����^�~���w��́A�\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ�<BR>
     *<BR>
     *(3)�\�[�g���������񖖔��ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt��<BR>
     *<BR>
     *(4)�쐬�����\�[�g�����������ԋp<BR>
     *<BR>
     *���L�[���ڕ�����i�V���{�����j�́A���b�Z�[�W��`�����Q��<BR>
     *���e�[�u�����F�����P�ʃe�[�u��(ifo_order_unit)<BR>
     *���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q��<BR>
     * @@param l_futuresOptionsSortKey - (�����w���敨�I�v�V�����\�[�g�L�[)<BR>
     * �����w���敨�I�v�V�����\�[�g�L�[<BR>
     * @@return String
     * @@roseuid 40A07C69002E
     */
    protected String createSortCond(WEB3FuturesOptionsSortKey[] l_futuresOptionsSortKey)
    {
        final String STR_METHOD_NAME =
            "createSortCond(WEB3FuturesOptionsSortKey)";

        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_strReturn = new StringBuffer();
        if (l_futuresOptionsSortKey == null)
        {
            l_strReturn.append("product_id");
            l_strReturn.append(" ");
            l_strReturn.append("ASC");
            return l_strReturn.toString();
        }

        //�Ԋ҂̒l�̐ݒ�        
        for (int i = 0; i < l_futuresOptionsSortKey.length; i++)
        {
            log.debug(" �L�[����" + i + " = "+ l_futuresOptionsSortKey[i].keyItem);
            log.debug(" �����^�~��" + i + " = "+ l_futuresOptionsSortKey[i].keyItem);
            //�E�����R�[�h�@@�@@ �@@�@@�@@�F�����P�ʃe�[�u���D����ID
            if(l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.PRODUCTCODE))
            {
                l_strReturn.append("PRODUCT_ID");
            }
            //�E����s��@@�@@�@@�@@�@@  �F�����P�ʃe�[�u���D�s��ID
            else if(l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.TRADE_MARKET))
            {
                l_strReturn.append("MARKET_ID");
            }
            //�E����敪            �F�����P�ʃe�[�u���D�������
            else if(l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.TRADE_DIVISION))
            {
                l_strReturn.append("ORDER_TYPE");
            }
            //�E�������ԁ@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�󒍓���
            else if(l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.ORDER_TIME))
            {
                l_strReturn.append("RECEIVED_DATE_TIME");
            }
            //�E������ �@@�@@�@@�@@�@@ �F�����P�ʃe�[�u���D������
            else if (l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.BIZ_DATE)) 
            {
                l_strReturn.append("BIZ_DATE");
            }
            //�E���������@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�����������t
            else if(l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.ORDER_EXPIRATION_DATE))
            {
                l_strReturn.append("EXPIRATION_DATE");
            }
            else
            {
                continue;
            }
            l_strReturn.append(" ");
            if(WEB3AscDescDef.ASC.equals(l_futuresOptionsSortKey[i].ascDesc))
            {
                l_strReturn.append("ASC");
            }
            else
            {
                l_strReturn.append("DESC");
            }
            if(i < l_futuresOptionsSortKey.length)
            {
                l_strReturn.append(" , ");
            }
        }
        l_strReturn.append("last_updated_timestamp");
        l_strReturn.append(" ");
        l_strReturn.append("ASC");
        log.exiting(STR_METHOD_NAME);
        return l_strReturn.toString();
    }

    /**
     * (getPR�w����敪)<BR>
     * ������ʂ��烌�X�|���X�ɕҏW����PR�w�p�̎���敪�̃R�[�h�𔻒肵�A�ԋp����B <BR>
     *<BR>
     * �߂�l��PR�w�p�̎���敪�R�[�h�F <BR>
     * 3�F�V�K���������@@4�F�V�K���������@@5�F�����ԍϒ���(���ԍ�)�@@6�F�����ԍϒ���(���ԍ�) <BR>
     *<BR>
     *�E�p�����[�^.������ʁ�"605"�iOP�V�K���������j�̏ꍇ�A"3"(�V�K��������)��Ԃ��B<BR> 
     *<BR>
     *�E�p�����[�^.������ʁ�"606"�iOP�V�K���������j�̏ꍇ�A"4"(�V�K��������)��Ԃ��B<BR> 
     *<BR>
     *�E�p�����[�^.������ʁ�"608"�iOP�����ԍϒ����j�̏ꍇ�A"5"(�����ԍϒ���(���ԍ�))��Ԃ��B <BR>
     *<BR>
     *�E�p�����[�^.������ʁ�"607"�iOP�����ԍϒ����j�̏ꍇ�A"6"(�����ԍϒ���(���ԍ�))��Ԃ��B<BR> 
     *<BR>
     *�E�p�����[�^.������ʂ���L�ȊO�̏ꍇ�́A��O��throw����B<BR>
     * @@return String
     * @@roseuid 40A36C160227
     */

    protected String getPRLevelTradeTypeDiv(OrderTypeEnum l_orderTypeEnum) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPRLevelTradeTypeDiv(OrderTypeEnum l_orderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        String l_prTradeTypeDiv = "";
        if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderTypeEnum))
        {
            l_prTradeTypeDiv = WEB3IfoTradeTypeDef.OPEN_LONG_CONTRACT;
        }
        else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderTypeEnum))
        {
            l_prTradeTypeDiv = WEB3IfoTradeTypeDef.OPEN_SHORT_CONTRACT;
        }
        else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderTypeEnum))
        {
            l_prTradeTypeDiv = WEB3IfoTradeTypeDef.CLOSE_SELL_CONTRACT;
        }
        else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderTypeEnum))
        {
            l_prTradeTypeDiv = WEB3IfoTradeTypeDef.CLOSE_BUY_CONTRACT;
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01439,
                STR_METHOD_NAME);            
        }
        log.exiting(STR_METHOD_NAME);
        return l_prTradeTypeDiv;
    }

    /**
     * (is�w������)<BR>
     * �w�肳�ꂽ����Ԃɍ��v���Ă��邩�ǂ����𔻒肵�A<BR>
     * ���v���Ă���ꍇ��true���A���v���Ă��Ȃ��ꍇ��false���A���ꂼ��Ԃ��B<BR>
     * <BR>
     * �敨OP�f�[�^�A�_�v�^.get����ԋ敪()���R�[������B<BR>
     * <BR>
     * [����]<BR>
     *   �����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �@@�߂�l�̖���ԋ敪���p�����[�^.����ԋ敪�̏ꍇ�́Atrue��Ԃ��B<BR>
     * �@@�ȊO�Afalse��Ԃ��B<BR>
     * @@param l_strExecutedStatus - ����ԋ敪�B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return boolean
     * @@roseuid 40A36C160227
     */
    protected boolean isDesignateExecutedStatus(String l_strExecutedStatusDivision, IfoOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "isDesignateExecutedStatus(String, IfoOrderUnit)";

        log.entering(STR_METHOD_NAME);

        boolean l_bReturn = false; //�ԋp�l

        if (l_strExecutedStatusDivision == null)
        {
            //����.����ԋ敪��null�̏ꍇ
            l_bReturn = true;
        }
        else
        {
            //����.����ԋ敪��null�̏ꍇ
            String l_execType = WEB3IfoDataAdapter.getExecStatusType(l_orderUnit);
            l_bReturn = l_execType.equals(l_strExecutedStatusDivision);
        }

        log.exiting(STR_METHOD_NAME);

        return l_bReturn;

    }
    
    /**
     * (get�o����܂Œ����P��FromFirstToLast)<BR>
     *�w�肳�ꂽ�u�[��܂Œ����v��u�o����܂Œ����v�̒����P�ʃI�u�W�F�N�g�ɑ΂���A<BR>
     *�������`�ŐV�̒����܂ł̒����P�ʃI�u�W�F�N�g�̈ꗗ���擾����B<BR>
     *<BR>
     *(1)�p�����[�^.�����P��.���񒍕��̒����P��ID == 0�@@<BR>
     *|| �p�����[�^.�����P��.���񒍕��̒����P��ID == null�j�̏ꍇ<BR>
     *�@@�i�������Ŗ��J�z��Ԃ̏ꍇ�j<BR>
     *<BR>
     *�@@�p�����[�^.�����P�ʂ�z��ɃZ�b�g���ĕԋp����B<BR>
     *<BR>
     *(2)�������`�ŐV�̒����܂ł̒����P�ʃI�u�W�F�N�g�����L���o�����ɂĎ擾����B <BR>
     *<BR>
     *�@@�@@�@@�����o������<BR>
     *�@@�@@      ���񒍕��̒����P��ID == �p�����[�^.�����P��.���񒍕��̒����P��ID�@@<BR>
     *�@@�@@�@@�@@�@@�܂��́A�����P��ID == �p�����[�^.�����P��.���񒍕��̒����P��ID(*) <BR>
     *<BR>
     *�@@�@@�@@    (*)������.���񒍕��̒����P��ID�ɂ́A0���Z�b�g����Ă���ׁB <BR>
     *<BR>
     *(3)�擾���������P�ʃI�u�W�F�N�g���쐬�������ɏ����Ń\�[�g���A�z��ɂ��ĕԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return IfoOrderUnit[]
     * @@roseuid 40A45DF500AB
     */
    protected IfoOrderUnit[] getCarriedOrderUnitFromFirstToLast(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getCarriedOrderUnitFromFirstToLast(IfoOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        // �������`�ŐV�̒����܂ł̒����P�ʃI�u�W�F�N�g���擾����B
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        List l_lisRecords = null;
        IfoOrderUnit[] l_orderUnits = null;
        
        if (l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull()
            || l_ifoOrderUnitRow.getFirstOrderUnitId() == 0)
        {
            l_orderUnits = new IfoOrderUnit[1];
            l_orderUnits[0] = l_orderUnit;
            return l_orderUnits;
        }
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strWhere = "order_unit_id=? or first_order_unit_id=?";
            Object l_objWhere[] =
                new Object[] {
                    new Long(l_ifoOrderUnitRow.getFirstOrderUnitId()),
                    new Long(l_ifoOrderUnitRow.getFirstOrderUnitId())};
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    l_strWhere,
                    "created_timestamp asc",
                    null,
                    l_objWhere);
            l_orderUnits = new IfoOrderUnit[l_lisRecords.size()];

           for(int i = 0;i<l_lisRecords.size();i++)
           {
               IfoOrderUnitRow l_orderUnitRowNew = (IfoOrderUnitRow)l_lisRecords.get(i);
               l_orderUnits[i] = (IfoOrderUnit)opOrderManager.getOrderUnit(l_orderUnitRowNew.getOrderUnitId());
           }
        }
        catch(NotFoundException l_nfe)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME, l_nfe);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);            
        }
        catch (DataException l_de)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME, l_de);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_de.getMessage(),
                    l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnits;
    }

    /** 
     * (is��������\)<BR>
     * ����������\�ł��邩�𔻒肷��B<BR>
     * ��������\�ł���ꍇtrue���A�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u�iOP�������Ɖ�T�[�r�X�jis��������\�v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@return boolean
     * @@throws WEB3BaseException
     */
    private boolean isChangeCancelEnable(WEB3GentradeSubAccount l_subAccount, IfoOrderUnit l_orderUnit)                              
        throws WEB3BaseException                             
    {                                
        final String STR_METHOD_NAME =                               
            "isChangeCancelEnable(WEB3GentradeSubAccount, IfoOrderUnit)";                                
                                
        log.entering(STR_METHOD_NAME);                               
                                
        IfoOrderUnitRow l_orderUnitRow =                             
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();                              
                                        
        //�����}�l�[�W��.getOrder(�����P��.����ID)                                
        Order l_order = null;                                
        try                              
        {                                
            l_order = opOrderManager.getOrder(l_orderUnit.getOrderId());                             
        }                                
        catch (NotFoundException l_nfE)                              
        {                                
            log.error(getClass().getName() + "." + STR_METHOD_NAME,l_nfE);                               
            throw new WEB3SystemLayerException(                              
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,                             
                STR_METHOD_NAME,                             
                l_nfE.getMessage(),                              
                l_nfE);                              
        }                                
                                
        //�����}�l�[�W��.validate���������\���(���� : ����)                              
        boolean l_blnOrderChange = true;                             
        try                              
        {                                
            opOrderManager.validateOrderChangePossibleStatus(l_order);                               
        }                                
        catch (WEB3BaseException l_wbe)                              
        {                                
            l_blnOrderChange = false;                                
        }                                
                                
        //�����}�l�[�W��.validate��������\���(���� : ����)                              
        boolean l_blnOrderCancel = true;                             
        try                              
        {                                
            opOrderManager.validateOrderCancelPossibleStatus(l_order);                               
        }                                
        catch (WEB3BaseException l_wbe)                              
        {                                
            l_blnOrderCancel = false;                                
        }                                
                                
        //�@@�����P��.������Ԃ���������\�ȏ�Ԃł��邱��                              
        if (l_blnOrderChange == false && l_blnOrderCancel == false)                              
        {                                
            log.debug("��������\�`�F�b�N�F������������s��");                                
            log.exiting(STR_METHOD_NAME);                                
            return false;                                
        }                                

        //������
        Date l_datBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");

        //validate�ǌ���������t�\
        // [����]
        //  �����^�C�v�F�@@�h�敨�I�v�V�����h 
        //  �敨�^�I�v�V�����敪�F�@@�h�I�v�V�����h 
        //  ���X�F�@@�⏕����.get�戵�X() 
        //  ����敪�F�@@�����P��.����敪 
        //  �������F�@@�����P��.������
        try
        {
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                ProductTypeEnum.IFO, 
                WEB3FuturesOptionDivDef.OPTION,
                (WEB3GentradeBranch)l_subAccount.getWeb3GenBranch(),
                l_orderUnitRow.getSessionType(),
                l_datBizDate);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.debug("��������\�`�F�b�N�F�s��ǌ� ��������s����");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
                     
        //�B�����~�ڋq�ł͂Ȃ����Ɓi�x�q�A�l�����b�N�A�x�X���b�N�A�Ǘ����b�N�̃`�F�b�N�j                                
        WEB3GentradeOrderValidator l_gentradeOrderValidator = null;                              
        l_gentradeOrderValidator = (WEB3GentradeOrderValidator)this.finApp.getCommonOrderValidator();                                
                                        
        OrderValidationResult l_validationResult =                               
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);                             
                                
        if(l_validationResult.getProcessingResult().isFailedResult())                                
        {                                
            log.debug("��������\�`�F�b�N�F����s�ڋq");                              
            log.exiting(STR_METHOD_NAME);                                
            return false;                                
        }                                
                                
        //�S�`�F�b�NOK�̏ꍇ                             
        log.exiting(STR_METHOD_NAME);                                
        return true;                             
    }
}@
