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
filename	WEB3OptionSettleContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�ԍϓ��̓T�[�r�XImpl(WEB3OptionSettleContractInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/21 ����� (���u) �V�K�쐬
                   2006/07/13 鰁@@(���u) �d�l�ύX ���f�� NO.460��Ή�
Revesion History : 2007/06/08 �����F(���u) ���f��647�A652�A690
Revesion History : 2007/06/21 �Ј���(���u) ���f��712�A725
Revesion History : 2007/11/20 �g�E�N�|(���u) ���f��794
Revesion History : 2008/04/10 �����F(���u) ���f��862 872
Revesion History : 2008/08/18 ���� (���u) IFO�����_�Ή�
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3IfoSettlementStateDef;
import webbroker3.ifo.message.WEB3OptionsContractDivisionComparator;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3OptionsOpenDateComparator;
import webbroker3.ifo.message.WEB3OptionsCloseMarginGroup;
import webbroker3.ifo.message.WEB3OptionsCloseMarginInputRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginInputResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginListRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginListResponse;
import webbroker3.ifo.message.WEB3OptionsContractReferenceUnit;
import webbroker3.ifo.message.WEB3OptionsProductCodeComparator;
import webbroker3.ifo.message.WEB3OptionsProfitAndLossComparator;
import webbroker3.ifo.message.WEB3OptionsProfitAndLossCostComparator;
import webbroker3.ifo.message.WEB3OptionsSessionTypeComparator;
import webbroker3.ifo.message.WEB3OptionsSettlementStatusTypeComparator;
import webbroker3.ifo.message.WEB3OptionsContractPriceComparator;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractInputService;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (OP�ԍϓ��̓T�[�r�XImpl)<BR>
 * <BR>
 * �����w���I�v�V�����ԍϓ��̓T�[�r�X�����N���X<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3OptionSettleContractInputServiceImpl extends WEB3OptionClientRequestService implements WEB3OptionSettleContractInputService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionSettleContractInputServiceImpl.class);

    /**
     * �����w���I�v�V�����ԍϓ��̓T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Aget�ԍψꗗ()�܂��́A<BR>
     * get�ԍϓ��͉��()���\�b�h�̂����ꂩ���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407A60AA00CB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3OptionsCloseMarginListRequest)
        {
            l_response = getSettleContractList((WEB3OptionsCloseMarginListRequest) l_request);
        }
        else if (l_request instanceof WEB3OptionsCloseMarginInputRequest)
        {
            l_response = getSettleContractInputScreen((WEB3OptionsCloseMarginInputRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s��.");
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get�ԍψꗗ)<BR>
     * <BR>
     * �����w���I�v�V�����̕ԍψꗗ��ʕ\���T�[�r�X�����{����B<BR>
     * <BR>
     * �u�iOP�ԍϓ��́jget�ԍψꗗ�v�Q�ƁB<BR>
     * @@param l_request - �����w���I�v�V�����ԍψꗗ��ʕ\�����N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginListResponse
     * @@roseuid 4083A5BD02F5
     */
    protected WEB3OptionsCloseMarginListResponse getSettleContractList(WEB3OptionsCloseMarginListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSettleContractList(WEB3OptionsCloseMarginListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        WEB3OptionsCloseMarginListResponse l_response = (WEB3OptionsCloseMarginListResponse) l_request.createResponse();

        //�⏕�������擾����B
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        //�ԍϒ�����t�R�������{����B
        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
        l_orderMgr.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);
        

        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
        WEB3IfoProductImpl l_ifoProduct = null;

        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        if (l_request.opProductCode != null) 
        {
            try
            {
				l_ifoProduct = l_productMgr.getIfoProduct
                    (l_subAccount.getInstitution(), l_request.opProductCode);

                if (l_ifoProduct == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            catch (NotFoundException l_nfex)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
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
                
			double l_dblStrikePrice = 0;      
			if (l_request.strikePrice != null && !"".equals(l_request.strikePrice))       
			{     
				l_dblStrikePrice = Double.parseDouble(l_request.strikePrice); 
			}     
                
			try       
			{     
				l_ifoProduct = l_productMgr.getIfoProduct(        
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
		}

        //�s��Ǌԋ߂̎w����z��Ŏ擾����B
        String[] l_closeSuspensions = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.OPTION);

        //�w������̕ێ����関���ό��ʂ̖����R�[�h�Ɩ������̈ꗗ���擾����B
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl) l_tradingModule.getPositionManager();
        WEB3FuturesOptionsProductCodeNameUnit[] l_productCodeNameUnits = l_positionManager.createProductCodeNameFromContract(l_subAccount, true, WEB3FuturesOptionDivDef.OPTION);

        //create�����R�[�h����from���ʂ̕Ԃ�l��NULL�̏ꍇ�A���X�|���X�쐬�A
        //�v���p�e�B�Z�b�g�������s���B
        if (l_productCodeNameUnits == null)
        {
            l_response.closeMarginGroups = null; //�����w���I�v�V�����ԍψꗗ�s
            l_response.totalPages = "0"; //���y�[�W��
            l_response.totalRecords = "0"; //�����R�[�h��
            l_response.pageIndex = "0"; //�\���y�[�W�ԍ�
            l_response.messageSuspension = l_closeSuspensions; //����I���x������
            l_response.futOpProductCodeNames = null; //�����w���敨�I�v�V���������R�[�h����
            return l_response;
        }

        //���������̍쐬
        String l_strQueryString = createQueryString(l_ifoProduct);
        String[] l_strQueryContainer = createQueryContainer(l_ifoProduct);

        WEB3OptionsContractReferenceUnit[] l_referenceUnits = l_positionManager.createOptionsContractReferenceUnits(l_subAccount, //�⏕����
            WEB3FuturesOptionDivDef.OPTION,
            null, 
            l_strQueryString, 
            l_strQueryContainer); 
    

        //create���ʏƉ�ׂ̕Ԃ�l��NULL�̏ꍇ�A
        //���X�|���X�쐬�A�v���p�e�B�Z�b�g�������s���B
        if (l_referenceUnits == null)
        {
            l_response.closeMarginGroups = null; //�����w���I�v�V�����ԍψꗗ�s
            l_response.totalPages = "0"; //���y�[�W��
            l_response.totalRecords = "0"; //�����R�[�h��
            l_response.pageIndex = "0"; //�\���y�[�W�ԍ�
            l_response.messageSuspension = l_closeSuspensions; //����I���x������
            l_response.futOpProductCodeNames = l_productCodeNameUnits; //�����w���敨�I�v�V���������R�[�h����
            return l_response;
        }

        List l_lstReferenceUnits = new ArrayList();

        //���ʏƉ�חv�f���Ƃ�Loop����, (�ԍψꗗ�쐬�Ώۂ̌��ʏƉ�׍쐬����)
        log.debug("l_referenceUnits.length = " + l_referenceUnits.length);
        for (int i = 0; i < l_referenceUnits.length; i++)
        {
            //�w�肳�ꂽ�������Y���ڋq�̉�ЁE���X�Ŏ戵�\�Ȗ����ł��邩�𔻒肷��B
            boolean l_blnIsInstHandlingProduct = isInstitutionHandlingProduct(l_subAccount, l_referenceUnits[i].opProductCode);
            log.debug("l_blnIsInstHandlingProduct = " + l_blnIsInstHandlingProduct);
            if (l_blnIsInstHandlingProduct && !WEB3IfoSettlementStateDef.SETTLEMENT_END.equals(l_referenceUnits[i].settlementState))
            {
                log.debug("l_referenceUnits[i] = " + l_referenceUnits[i]);

                l_lstReferenceUnits.add(l_referenceUnits[i]);
            }
        }

        //�ԍψꗗ�쐬�Ώۂ̌��ʏƉ�׍쐬�����ɂ���Č��ʏƉ�חv�f����0�ɂȂ����ꍇ
        if (l_lstReferenceUnits.size() == 0)
        {
            l_response.closeMarginGroups = null; //�����w���I�v�V�����ԍψꗗ�s
            l_response.totalPages = "0"; //���y�[�W��
            l_response.totalRecords = "0"; //�����R�[�h��
            l_response.pageIndex = "0"; //�\���y�[�W�ԍ�
            l_response.messageSuspension = l_closeSuspensions; //����I���x������
            l_response.futOpProductCodeNames = l_productCodeNameUnits; //�����w���敨�I�v�V���������R�[�h����
            return l_response;
        }

        //�����w���敨�I�v�V�����\�[�g�L�[�z����쐬����B
        WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[4];

        //�����w���敨�I�v�V�����\�[�g�L�[[0].�L�[���� = �����R�[�h
        //�����w���敨�I�v�V�����\�[�g�L�[[0].�����^�~�� = "����"
        l_sortKeys[0] = new WEB3FuturesOptionsSortKey();
        l_sortKeys[0].keyItem = WEB3IfoKeyItemDef.PRODUCTCODE;
        l_sortKeys[0].ascDesc = WEB3AscDescDef.ASC;

        //�����w���敨�I�v�V�����\�[�g�L�[[1].�L�[���� = ���敪
        //�����w���敨�I�v�V�����\�[�g�L�[[1].�����^�~�� = "����"
        l_sortKeys[1] = new WEB3FuturesOptionsSortKey();
        l_sortKeys[1].keyItem = WEB3IfoKeyItemDef.CONTRACT_DIVISION;
        l_sortKeys[1].ascDesc = WEB3AscDescDef.ASC;

        //�����w���敨�I�v�V�����\�[�g�L�[[2].�L�[���� = ���Ϗ�ԋ敪"
        //�����w���敨�I�v�V�����\�[�g�L�[[2].�����^�~�� = "����"
        l_sortKeys[2] = new WEB3FuturesOptionsSortKey();
        l_sortKeys[2].keyItem = WEB3IfoKeyItemDef.SETTLEMENT_STATUS_DIVISION;
        l_sortKeys[2].ascDesc = WEB3AscDescDef.ASC;

        //�����w���敨�I�v�V�����\�[�g�L�[[3].�L�[���� = ����
        //�����w���敨�I�v�V�����\�[�g�L�[[3].�����^�~�� = "����"
        l_sortKeys[3] = new WEB3FuturesOptionsSortKey();
        l_sortKeys[3].keyItem = WEB3IfoKeyItemDef.OPEN_DATE;
        l_sortKeys[3].ascDesc = WEB3AscDescDef.ASC;

        WEB3OptionsContractReferenceUnit[] l_returnReferenceUnits = new WEB3OptionsContractReferenceUnit[l_lstReferenceUnits.size()];
        l_lstReferenceUnits.toArray(l_returnReferenceUnits);
        //���ʏƉ�ׂ̃\�[�g�������s���B
        l_returnReferenceUnits = sortContractReferenceDetails(l_returnReferenceUnits, l_sortKeys);

        //�����w���I�v�V�����ԍψꗗ�s�̔z����쐬����B
        //log.debug("l_returnReferenceUnits =" + l_returnReferenceUnits.length);
        WEB3OptionsCloseMarginGroup[] l_closeMarginGroups = createSettleContractList(l_returnReferenceUnits);

        //�ԍψꗗ�s�̃\�[�g�������s���B
        l_closeMarginGroups = sortSettleContractListLine(l_closeMarginGroups, l_request.futOpSortKeys);

        //�v���y�[�W�ԍ��̕ԍψꗗ�s�z����쐬����B
        WEB3OptionsCloseMarginGroup[] l_closeMarginGroupsPage = createPage(l_request, l_closeMarginGroups);

        int l_intPageSize = Integer.parseInt(l_request.pageSize); //�y�[�W���\���s��
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex); //�v���y�[�W�ԍ�
        int l_intTotalRecords = l_closeMarginGroups.length; //�����R�[�h��

        //���y�[�W�� = �����R�[�h�� �� ���N�G�X�g�f�[�^.�y�[�W���\���s�� ���v�Z���ʂ�1�����A�܂��͗]�肪���݂���ꍇ�ɂ͍X��1�����Z����
        int l_intTotalPages = (int) Math.ceil(l_intTotalRecords / l_intPageSize);
        if (l_intTotalPages < 1 || l_intTotalRecords % l_intPageSize > 0)
        {
            l_intTotalPages = l_intTotalPages + 1;
        }
        //�����X�|���X.�����R�[�h�� <= (���N�G�X�g�f�[�^.�y�[�W���\���s�� * (���N�G�X�g�f�[�^.�v���y�[�W�ԍ� - 1))�̏ꍇ�́A���X�|���X.���y�[�W�����Z�b�g(�ŏI�y�[�W�ԍ����Z�b�g)
        if (l_intTotalRecords <= l_intPageSize * (l_intPageIndex - 1))
        {
            l_intPageIndex = l_intTotalPages;
        }

        l_response.closeMarginGroups = l_closeMarginGroupsPage; //�����w���I�v�V�����ԍψꗗ�s
        log.debug(">>>>>>>> l_response.closeMarginGroups = " + l_closeMarginGroupsPage[0].contractExecPrice);
        log.debug(">>>>>>>> l_response.closeMarginGroups = " + l_closeMarginGroupsPage[0].contractPrice);
        log.debug(">>>>>>>> l_response.closeMarginGroups = " + l_closeMarginGroupsPage[0].contractQuantity);
        log.debug(">>>>>>>> l_response.closeMarginGroups = " + l_closeMarginGroupsPage[0].contractType);
        l_response.totalPages = "" + l_intTotalPages; //���y�[�W��
        log.debug(">>>>>>>> l_response.totalPages = " + l_response.totalPages);
        l_response.totalRecords = "" + l_intTotalRecords; //�����R�[�h��
        log.debug(">>>>>>>> l_response.totalRecords = " + l_intTotalRecords);
        l_response.pageIndex = "" + l_intPageIndex; //�\���y�[�W�ԍ�
        log.debug(">>>>>>>> l_response.pageIndex = " + l_intPageIndex);
        l_response.messageSuspension = l_closeSuspensions; //����I���x������
        log.debug(">>>>>>>> l_response.messageSuspension = " + l_closeSuspensions);
        l_response.futOpProductCodeNames = l_productCodeNameUnits; //�����w���敨�I�v�V���������R�[�h����
        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get�ԍϓ��͉��)<BR>
     * <BR>
     * �����w���I�v�V�����̕ԍϓ��͉�ʕ\���T�[�r�X�����{����B<BR>
     * <BR>
     * �u�iOP�ԍϓ��́jget�ԍϓ��͉�ʁv�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginInputResponse
     * @@roseuid 4083A5C90362
     */
    protected WEB3OptionsCloseMarginInputResponse getSettleContractInputScreen(WEB3OptionsCloseMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSettleContractInputScreen(WEB3OptionsCloseMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        WEB3OptionsCloseMarginInputResponse l_response = (WEB3OptionsCloseMarginInputResponse) l_request.createResponse();

        //get����(�����w���I�v�V�����ԍϓ��͉�ʃ��N�G�X�g)
        WEB3IfoContractImpl l_contractImpl = this.getContract(l_request);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        //�ԍϒ�����t�R�������{����B
        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();

        FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();

        try
        {
            IfoProduct l_product0 = (IfoProduct)l_contractImpl.getProduct();
            WEB3GentradeTradingTimeManagement.resetProductCode(l_product0.getUnderlyingProductCode());
            //�⏕�������擾����B
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
            l_orderMgr.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);
            Market l_market = l_finObjMgr.getMarket(l_contractImpl.getMarketId());
            Institution l_institution = l_subAccount.getInstitution();

            //�����A����s��`�F�b�N�����{����B
            WEB3IfoOrderManagerReusableValidations l_validations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();
            WEB3GentradeMarket l_gentradeMarket = (WEB3GentradeMarket)l_validations.validateMarket(l_market.getMarketCode(), //�s��R�[�h
                l_institution.getInstitutionCode()); //�،���ЃR�[�h

            //����A�\�����`�F�b�N�����{����B
            WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_productImpl = (WEB3IfoProductImpl) l_productMgr.getProduct(l_contractImpl.getProduct().getProductId());
            l_validations.validateProductCode(l_productImpl.getProductCode(), //�����R�[�h
                l_institution.getInstitutionCode()); //�،���ЃR�[�h
           
            
            //��������̃`�F�b�N���s���A�敨OP��������I�u�W�F�N�g��ԋp����B
            WEB3IfoTradedProductImpl l_tradedProductImpl = l_validations.validateTradedProduct(l_productImpl,l_gentradeMarket,l_contractImpl.isLong(),false);           
            Date l_datLastTradingDate = l_tradedProductImpl.getLastTradingDate();
            
            //�s��Ǌԋ߂̎w����z��Ŏ擾����B
            String[] l_closeSuspensions = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.OPTION);

            //create���ʏƉ�׈ꗗ(�����w���I�v�V�����ԍϓ��͉�ʃ��N�G�X�g)
            WEB3OptionsContractReferenceUnit[] l_contractReferenceUnits =
                this.createContractReferenceUnitList(l_request);

            List l_lstContractUnits = new ArrayList();
            int l_intCloseMarginAvailableQuantity = 0;

            for (int i = 0; i < l_contractReferenceUnits.length; i++)
            {
                //�w��̌��ʏƉ�ׂ��猚�ʖ��ׂ��쐬����B
                WEB3FuturesOptionsContractUnit l_contractUnit = this.createContractDetails(l_contractReferenceUnits[i]);

                //�ԍω\���ʂ̃Z�b�g
                l_intCloseMarginAvailableQuantity += Double.parseDouble(l_contractUnit.contractQuantity);

                l_lstContractUnits.add(l_contractUnit);
            }

            WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[l_lstContractUnits.size()];
            l_lstContractUnits.toArray(l_contractUnits);
            log.debug("l_contractUnits = " + l_contractUnits);
            if (l_contractUnits.length > 1) //�ꊇ�ԍς̏ꍇ
            {
                //�������ʂ�NULL�łȂ��ꍇ�̂݉��L�ԍω\���ʃ`�F�b�N�����{����B
                if (l_request.opOrderQuantity != null)
                {
                    int l_intOrderQuantity = Integer.parseInt(l_request.opOrderQuantity);

                    if (l_intOrderQuantity > l_intCloseMarginAvailableQuantity) //�������� > �ԍω\���ʂ̏ꍇ
                    {
                        throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00303, this.getClass().getName() + STR_METHOD_NAME);
                    }
                }

                //sort���ʖ��׈ꗗ(���ʖ���[], �����w���I�v�V�����ԍϓ��͉�ʃ��N�G�X�g)
                this.sortContractUnitList(l_contractUnits, l_request);

                double l_intOrderQuantity = 0D;
                if (WEB3StringTypeUtility.isNumber(l_request.opOrderQuantity))
                {
                    l_intOrderQuantity = Double.parseDouble(l_request.opOrderQuantity);
                }

                for (int i = 0; i < l_contractUnits.length; i++)
                {
                    //�ԍϐ��ʁA���Ϗ��ʂ̃v���p�e�B�ăZ�b�g����

                    double l_intCloseMarginQuantity = 0;

                    double l_intContractQuantity = 0D;
                    if (WEB3StringTypeUtility.isNumber(l_contractUnits[i].contractQuantity))
                    {
                        l_intContractQuantity = Double.parseDouble(l_contractUnits[i].contractQuantity);
                    }

                    if (Double.isNaN(l_intContractQuantity))
                    {
                        l_intContractQuantity = 0D;
                    }

                    if (l_request.opOrderQuantity == null)
                    {
                        //�������ʂ�NULL�̏ꍇ
                        //�ԍϐ��� = ���ʖ���.���ʐ�
                        l_intCloseMarginQuantity = l_intContractQuantity;
                    }
                    else
                    {

                        if (l_intOrderQuantity >= l_intContractQuantity)
                        {
                            //�ԍϐ��� = ���ʖ���.���ʐ�
                            l_intCloseMarginQuantity = l_intContractQuantity;
                        }
                        else
                        {
                            //�ԍϐ��� = ��������
                            l_intCloseMarginQuantity = l_intOrderQuantity;
                        }

                        //�������� = �������� - �ԍϐ���
                        l_intOrderQuantity -= l_intCloseMarginQuantity;

                    }

                    //���ʖ���.�ԍϐ��� = �ԍϐ���
                    l_contractUnits[i].contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_intCloseMarginQuantity);

                    //���ʖ���.���Ϗ��� = ���ʖ��חv�f�̃C���f�b�N�X�ԍ� + 1
                    l_contractUnits[i].settlePriority = "" + (i + 1);

                } 
            }
            //�戵�\���������I�u�W�F�N�g�𐶐�����B
        WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(l_institution.getInstitutionCode(), //�،���ЃR�[�h
            ProductTypeEnum.IFO, //�����^�C�v
            WEB3FuturesOptionDivDef.OPTION, //�敨�^�I�v�V�����敪
            WEB3MarginTradingDivDef.DEFAULT); //�M�p����敪
    
            // set����ŏI��(����ŏI�� : Date)
            l_handlingOrderCond.setTradingEndDate(l_datLastTradingDate);

            //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B

            //<�����w���I�v�V�������ʓ��̓��X�|���X����>

            //���X�|���X.�����P���敪�ꗗ = �戵�\�����P���敪�擾�̕Ԃ�l
            if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_contractReferenceUnits[0].contractType))
            {
                l_response.orderPriceDivList = l_handlingOrderCond.getHandlingPossibleOrderPriceDiv(false,true);
            }
            if (WEB3IfoContractTypeDef.OPEN_SELL.equals(l_contractReferenceUnits[0].contractType))
            {
                l_response.orderPriceDivList = l_handlingOrderCond.getHandlingPossibleOrderPriceDiv(false,false);
            }
              //���X�|���X.���s�����ꗗ = �戵�\���s�����擾�̕Ԃ�l
			String[] l_strPossibleExecCond =l_handlingOrderCond.getHandlingPossibleExecCond();
			l_strPossibleExecCond = l_orderMgr.getHandlingPossibleExecConds(l_response.orderPriceDivList,l_strPossibleExecCond);
			l_response.execCondList = l_strPossibleExecCond;

            //���X�|���X.���������敪�ꗗ = �戵�\���������敪�擾�̕Ԃ�l
            l_response.expirationDateTypeList = l_handlingOrderCond.getHandlingPossibleExpirationDateType();

            //is�o����܂Œ����戵�\<����ŏI���l��>�̕Ԃ�l��true�̏ꍇ
            if (l_handlingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering())
            {
                //get�o����܂Œ����J�n��<����ŏI���l��>(Date)
                Date l_datStartDate = l_handlingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(null);

                //���X�|���X.�L�������J�n�� = get�o����܂Œ����J�n��<����ŏI���l��>�̕Ԃ�l
                l_response.expirationStartDate = WEB3DateUtility.toDay(l_datStartDate);

                //get�o����܂Œ����ŏI��<����ŏI���l��>(Date)
                Date l_datEndDate = l_handlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(null);

                //���X�|���X.�L�������ŏI�� = get�o����܂Œ����ŏI��<����ŏI���l��>�̕Ԃ�l
                l_response.expirationEndDate = WEB3DateUtility.toDay(l_datEndDate);

                //���X�|���X.�L���������j���ꗗ = get�����������j���ꗗ�̕Ԃ�l
                l_response.holidayList = l_handlingOrderCond.getExpirationDateHoliday();
            }

            //���X�|���X.���������ꗗ = �戵�\���������擾�̕Ԃ�l
            l_response.orderCondTypeList = l_handlingOrderCond.getHandlingPossibleOrderCond();

            //���X�|���X.�v�w�l�p���s�����ꗗ = �敨OP�f�[�^�A�_�v�^.get�v�w�l�p���s�����ꗗ(get���s�����ꗗ()�̕Ԃ�l)
            l_response.wlimitExecCondList = WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(l_strPossibleExecCond,
                l_handlingOrderCond.getHandlingPossibleOrderCond());

            //���X�|���X.����敪 = ������ԊǗ�.get����敪()
            l_response.sessionType = WEB3GentradeTradingTimeManagement.getSessionType();

            //<�����w���I�v�V�����ԍϓ��͉�ʃ��X�|���X����>

            //���X�|���X.����敪 = ���ʏƉ��[0].���敪��"����"�Ȃ��"�����ԍϒ���(���ԍρj"���A"����"�Ȃ��"�����ԍϒ���(���ԍρj"��ݒ�
            if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_contractReferenceUnits[0].contractType))
            {
                l_response.tradingType = "" + OrderTypeEnum.CLOSE_MARGIN_LONG.intValue();
            }
            else
            {
                l_response.tradingType = "" + OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue();
            }
            //���X�|���X.����s�� = ���ʏƉ��[0].����s��
            l_response.marketCode = l_contractReferenceUnits[0].marketCode;

            //���X�|���X.�w����� = ���ʏƉ��[0].�w�����
            l_response.targetProductCode = l_contractReferenceUnits[0].targetProductCode;

            //���X�|���X.���� = ���ʏƉ��[0].����
            l_response.delivaryMonth = l_contractReferenceUnits[0].delivaryMonth;

            //���X�|���X.�I�v�V�������i�敪 = ���ʏƉ��[0].�I�v�V�������i�敪
            l_response.opProductType = l_contractReferenceUnits[0].opProductType;

            //���X�|���X.�s�g���i = ���ʏƉ��[0].�s�g���i

            l_response.strikePrice = l_contractReferenceUnits[0].strikePrice;

            FinApp finApp = (FinApp) Services.getService(FinApp.class);
            WEB3QuoteDataSupplierService l_quoteDataSupplierService = (WEB3QuoteDataSupplierService) finApp.getTradingModule(ProductTypeEnum.IFO).getQuoteDataSupplierService();

            WEB3IfoQuoteData l_quoteData = l_quoteDataSupplierService.getIfoQuote(l_tradedProductImpl, RealType.REAL);

            //���X�|���X.���ݒl = getCurrentPrice�̕Ԃ�l
            double l_dblCurrentPrice = l_quoteData.getCurrentPrice();

            //getCurrentPrice�̕Ԃ�l                                   
            //������0�̏ꍇ�Anull��ݒ肷��                                 
            if (l_dblCurrentPrice == 0D)                                    
            {                                   
                l_response.currentPrice = null;                                       
            }                                   
            else                                    
            {                                   
                //������0�łȂ��ꍇ�A�擾�������ݒl��ݒ肷��                                     
                l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);                                      
            }                                   

            log.debug("���ݒl  = " + l_quoteData.getCurrentPrice());
            //���X�|���X.������� = getCurrentPriceTime�̕Ԃ�l(�Ԃ�l��NULL�̏ꍇ�́ANULL��ݒ�)
            l_response.currentPriceTime = l_quoteData.getCurrentPriceTime();

            log.debug("�������  = " + l_quoteData.getCurrentPriceTime());
            //���X�|���X.�O���� = getChange�̕Ԃ�l
            double l_dblChange = l_quoteData.getChange();
            
            l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);

            log.debug("�O����  = " + l_dblChange);
            //���X�|���X.���ʖ��� =�i*��L�ŕҏW�������ʖ��ׂ̔z��j
            l_response.contractUnits = l_contractUnits;

            log.debug("���ʖ���  = " + l_contractUnits[0].contractExecPrice);
            //���X�|���X.����I���x������ = get�s��ǌx���w���̕Ԃ�l
            l_response.messageSuspension = l_closeSuspensions;

            log.debug("����I���x������  = " + l_closeSuspensions);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�f�[�^�s�����G���[�B", l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME); 
        return l_response;
    }

    /**
     * (get����)<BR>
     * ���ʂ��擾���ԋp����B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�iOP�ԍϓ��́jget�ԍϓ��͉�ʂP�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �I�v�V�����ԍϓ��̓��N�G�X�g�f�[�^�I�u�W�F�N�g�B<BR>
     * @@return WEB3IfoContractImpl
     * @@throws WEB3BaseException
     */
    protected WEB3IfoContractImpl getContract(WEB3OptionsCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContract(WEB3OptionsCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoPositionManagerImpl l_positionManager =
            (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
        WEB3IfoContractImpl l_ifoContract = null;
        try
        {
            //���N�G�X�g.�ԍό���[0].ID�ɊY�����錚��
            l_ifoContract = (WEB3IfoContractImpl)l_positionManager.getContract(Long.parseLong(l_request.id[0]));
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_ifoContract;
    }

    /**
     * (create��������������)<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�����ƂɁA���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * (1)�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�<BR>
     * <BR>
     * (2)�p�����[�^.�敨OP������NULL�i�����w��j�̏ꍇ�A<BR>
     * ����ID�w��𕶎���C���X�^���X�ɒǉ��i����ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@" and product_id = ?"<BR>
     * <BR>
     * (3)������C���X�^���X��ԋp<BR>
     * �i�p�����[�^.�����R�[�h��NULL�̏ꍇ�ANULL��ԋp����j<BR>
     * @@param l_ifoProduct - �敨OP����
     * @@return String
     * @@roseuid 40AAE4A6009B
     */
    protected String createQueryString(WEB3IfoProductImpl l_ifoProduct)
    {
        final String STR_METHOD_NAME = " createQueryString()";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�敨OP������NULL�̏ꍇ�ANULL��ԋp����
        if (l_ifoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        String l_strQueryString = " and product_id = ? ";

        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * <BR>
     * ���N�G�X�g�f�[�^����A���������iwhere�ȉ��w��̕�����j��<BR>
     * �p�����[�^���X�g���쐬����B<BR>
     * <BR>
     * (1)������z����쐬<BR>
     * <BR>
     * (2)�p�����[�^.�敨OP������NULL�i�����w��j�̏ꍇ�A<BR>
     * �@@�@@����ID�𕶎���z��ɃZ�b�g�i����ID�Ō������s��)<BR>
     *
     * �@@�@@�@@����ID �� �p�����[�^.�敨OP����.����ID<BR>
     * <BR>
     * (3)������z���ԋp<BR>
     * �i�p�����[�^.�敨OP������NULL�̏ꍇ�ANULL��ԋp����j<BR>
     * @@param l_ifoProduct - �敨OP����
     * @@return String[]
     * @@roseuid 40AAE4A600AB
     */
    protected String[] createQueryContainer(WEB3IfoProductImpl l_ifoProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createQueryContainer()";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�敨OP������NULL�̏ꍇ�ANULL��ԋp����
        if (l_ifoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        String[] l_strQueryContainer = new String[] {String.valueOf(l_ifoProduct.getProductId())};

        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainer;
    }

    /**
     * (create�y�[�W)<BR>
     * <BR>
     * �v���y�[�W�ԍ��ɊY�����銔���w���I�v�V�����ԍψꗗ�s�̔z���<BR>
     * �쐬����B<BR>
     * <BR>
     * (1)�y�[�W���\���s���A�v���y�[�W�ԍ��̎擾<BR>
     * �y�[�W���\���s�� = �p�����[�^.���N�G�X�g�f�[�^.�y�[�W���\���s��<BR>
     * �v���y�[�W�ԍ� = �p�����[�^.���N�G�X�g�f�[�^.�v���y�[�W�ԍ�<BR>
     * <BR>
     * (1)�v���y�[�W�J�n�ʒu������<BR>
     * fromIndex = �y�[�W���\���s�� * (�v���y�[�W�ԍ� - 1)<BR>
     * <BR>
     * (2)�v���y�[�W�I���ʒu������<BR>
     * toIndex = (�y�[�W���\���s�� * �v���y�[�W�ԍ�) - 1<BR>
     * <BR>
     * ���p�����[�^.�ԍψꗗ�s�̗v�f�� <= fromIndex�̏ꍇ�A<BR>
     * (�����R�[�h�����v���y�[�W�ԍ��ɖ����Ȃ��ꍇ)<BR>
     * fromIndex =�@@�p�����[�^.�ԍψꗗ�s�̗v�f�� - �y�[�W���\���s��<BR>
     * toIndex = �p�����[�^.�ԍψꗗ�s�̗v�f�� - 1<BR>
     * <BR>
     * (3)ArrayList���쐬<BR>
     * <BR>
     * (4)�p�����[�^.�ԍψꗗ�s����Loop����<BR>
     * <BR>
     * �ԍψꗗ�s�̃C���f�b�N�X��<BR>
     * fromIndex��toIndex�͈͓̔�(fromIndex�ȏ�AtoIndex�ȉ�)<BR>
     * �ł���ꍇ�́A�쐬����ArrayList�ɕԍψꗗ�s�I�u�W�F�N�g��ǉ�<BR>
     * <BR>
     * (5)ArrayList.toArray�ŊY���y�[�W�ԍ��̕ԍψꗗ�s�̔z���ԋp����<BR>
     * @@param l_request - �����w���I�v�V�����ԍψꗗ��ʕ\�����N�G�X�g�I�u�W�F�N�g
     * @@param l_settleContractListLine - �����w���I�v�V�����ԍψꗗ�s�̔z��
     * (�����R�[�h�����̔z��)
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginGroup[]
     * @@roseuid 40848D230225
     */
    protected WEB3OptionsCloseMarginGroup[] createPage(WEB3OptionsCloseMarginListRequest l_request, WEB3OptionsCloseMarginGroup[] l_settleContractListLine)
    {
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_settleContractListLine, 
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));
                       
         return (WEB3OptionsCloseMarginGroup[])l_pageIndexInfo.getArrayReturned(WEB3OptionsCloseMarginGroup.class);
    }

    /**
     * (create�ԍψꗗ)<BR>
     * <BR>
     * �w��̌��ʏƉ�ׂ̔z�񂩂犔���w���I�v�V�����ԍψꗗ�s��<BR>
     * �z����쐬����B<BR>
     * <BR>
     * �u�iOP�ԍϓ��́jcreate�ԍψꗗ�v�Q�ƁB<BR>
     * @@param l_contractReferenceUnits - �����w���I�v�V�������ʏƉ�ׂ̔z��
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginGroup[]
     * @@roseuid 4084B03F02FD
     */
    protected WEB3OptionsCloseMarginGroup[] createSettleContractList(WEB3OptionsContractReferenceUnit[] l_contractReferenceUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createSettleContractList(WEB3OptionsContractReferenceUnit[] l_contractReferenceUnit)";
        log.entering(STR_METHOD_NAME);

        List l_lstCloseMarginGroups = new ArrayList();
        List l_lstContractUnits = new ArrayList();
        WEB3FuturesOptionsContractUnit[] l_contractUnits = null;
        log.debug(">>>>>>>>l_contractReferenceUnit = " + l_contractReferenceUnit.length);
        //��ԍψꗗ�s�쐬
        WEB3OptionsCloseMarginGroup l_baseCloseMarginGroup = createSettleContractListLine(l_contractReferenceUnit[0]);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();

        //����v��Ώۃt���O�̐ݒ菈��        
        boolean l_blnBaseDayTradeFlag = l_orderMgr.isDayTrade(
            l_contractReferenceUnit[0].openDate, 
            l_contractReferenceUnit[0].sessionType);

        for (int i = 0; i < l_contractReferenceUnit.length; i++)
        {
            //���v��Ώۃt���O
            boolean l_blnDayTradeFlag = l_orderMgr.isDayTrade(
                l_contractReferenceUnit[i].openDate, 
                l_contractReferenceUnit[i].sessionType);

            //�ȉ��̏����̂����ꂩ�ɓ��Ă͂܂�ꍇ�̂݁A���������{����B
            //��Loop�����̏���͂��̕���ɓ��邱�Ƃ͂Ȃ�
            //��ԍψꗗ�s�ƌ��ʏƉ��[�C���f�b�N�X]�ɂ���
            //�P�j�@@�����R�[�h���s��v�B
            //�Q�j�@@���敪���s��v�B
            //�R�j�@@�����R�[�h�A���敪�͈�v���邪�A���Ϗ�Ԃ��s��v�B
            //�S�j�@@�����R�[�h�A���敪�A���Ϗ�Ԃ͈�v�A���A���v��Ώۃt���O���s��v(*)
            //�@@�@@�@@�i�����v��ԍς̏ꍇ�j
            //�����ʏƉ�ׂ͖����R�[�h�E���敪�E���Ϗ�ԋ敪�E�����E����敪�̏����Ń\�[�g���ꂽ��Ԃł���
            if (!l_baseCloseMarginGroup.opProductCode.equals(l_contractReferenceUnit[i].opProductCode)
                || !l_baseCloseMarginGroup.contractType.equals(l_contractReferenceUnit[i].contractType)
                || (l_baseCloseMarginGroup.opProductCode.equals(l_contractReferenceUnit[i].opProductCode)
                    && l_baseCloseMarginGroup.contractType.equals(l_contractReferenceUnit[i].contractType)
                    && !l_baseCloseMarginGroup.settlementState.equals(l_contractReferenceUnit[i].settlementState))
                || (l_baseCloseMarginGroup.opProductCode.equals(l_contractReferenceUnit[i].opProductCode)
                    && l_baseCloseMarginGroup.contractType.equals(l_contractReferenceUnit[i].contractType)
                    && l_baseCloseMarginGroup.settlementState.equals(l_contractReferenceUnit[i].settlementState)
                    && l_blnBaseDayTradeFlag != l_blnDayTradeFlag))
            {
                l_contractUnits = new WEB3FuturesOptionsContractUnit[l_lstContractUnits.size()];
                l_lstContractUnits.toArray(l_contractUnits);

                //�w��̕ԍψꗗ�s�̌��ʖ��ׂ̃}�[�W�����ƃv���p�e�B�Z�b�g���s���B
                l_baseCloseMarginGroup = setSettleContractListLine(l_baseCloseMarginGroup, l_contractUnits);

                l_lstCloseMarginGroups.add(l_baseCloseMarginGroup);

                //�V���ɍ쐬�����ԍψꗗ�s����ԍψꗗ�s�Ƃ��ăZ�b�g����
                l_baseCloseMarginGroup = createSettleContractListLine(l_contractReferenceUnit[i]);

                //����v��Ώۃt���O�̐ݒ菈��
                l_blnBaseDayTradeFlag = l_orderMgr.isDayTrade(
                    l_contractReferenceUnit[i].openDate, 
                    l_contractReferenceUnit[i].sessionType);

                l_lstContractUnits = new ArrayList();

            }
            //�w��̌��ʏƉ�ׂ��猚�ʖ��ׂ��쐬����B
            WEB3FuturesOptionsContractUnit l_contractUnit = createContractDetails(l_contractReferenceUnit[i]);

            l_lstContractUnits.add(l_contractUnit);
        }

        //WEB3OptionsCloseMarginGroup[] l_closeMarginGroups = new WEB3OptionsCloseMarginGroup[l_lstCloseMarginGroups.size()];
        WEB3FuturesOptionsContractUnit[] l_contractUnits1 = new WEB3FuturesOptionsContractUnit[l_lstContractUnits.size()];
        l_lstContractUnits.toArray(l_contractUnits1);
        l_baseCloseMarginGroup = this.setSettleContractListLine(l_baseCloseMarginGroup, l_contractUnits1);
        l_lstCloseMarginGroups.add(l_baseCloseMarginGroup);
        log.debug("l_lstCloseMarginGroups = " + l_lstCloseMarginGroups.size());
        WEB3OptionsCloseMarginGroup[] l_closeMarginGroups = new WEB3OptionsCloseMarginGroup[l_lstCloseMarginGroups.size()];
        l_lstCloseMarginGroups.toArray(l_closeMarginGroups);

        log.exiting(STR_METHOD_NAME);

        return l_closeMarginGroups;
    }

    /**
     * (create�ԍψꗗ�s)<BR>
     * <BR>
     * �w��̌��ʏƉ�ׂ��犔���w���I�v�V�����ԍψꗗ�s���쐬����B<BR>
     * <BR>
     * (1)�ԍψꗗ�s�̐���<BR>
     * �����w���I�v�V�����ԍψꗗ�s(�ȉ��A�ԍψꗗ�s)�I�u�W�F�N�g�𐶐�����<BR>
     * <BR>
     * (2)�����̎擾<BR>
     * �@@�敨OP�|�W�V�����}�l�[�W��.getContract(�p�����[�^.���ʏƉ��.ID)<BR>
     * �@@�敨OP�|�W�V�����}�l�[�W��.get���ʎ���(����)<BR>
     * <BR>
     * (3)�v���p�e�B�̃Z�b�g<BR>
     * �ȉ��̃v���p�e�B�Z�b�g���s��<BR>
     * <BR>
     * �ԍψꗗ�s.�����R�[�h = �p�����[�^.���ʏƉ��.�����R�[�h<BR>
     * �ԍψꗗ�s.������ = �p�����[�^.���ʏƉ��.������<BR>
     * �ԍψꗗ�s.�w����� = �p�����[�^.���ʏƉ��.�w�����<BR>
     * �ԍψꗗ�s.���� = �p�����[�^.���ʏƉ��.����<BR>
     * �ԍψꗗ�s.�I�v�V�������i�敪 = �p�����[�^.���ʏƉ��.�I�v�V�������i�敪<BR>
     * �ԍψꗗ�s.�s�g���i = �p�����[�^.���ʏƉ��.�s�g���i<BR>
     * �ԍψꗗ�s.����s�� = �p�����[�^.���ʏƉ��.����s��<BR>
     * �ԍψꗗ�s.���敪 = �p�����[�^.���ʏƉ��.���敪<BR>
     * �ԍψꗗ�s.���Ϗ�ԋ敪 = �p�����[�^.���ʏƉ��.���Ϗ�ԋ敪<BR>
     * �ԍψꗗ�s.���ݒl = (2)�Ŏ擾��������<BR>
     * �ԍψꗗ�s.����敪 = �p�����[�^.���ʏƉ��.����敪<BR>
     * <BR>
     * (4)(3)�̕ԍψꗗ�s�I�u�W�F�N�g��ԋp����<BR>
     * @@param l_contractReferenceUnit - �����w���I�v�V�������ʏƉ�׃I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginGroup
     * @@roseuid 4084C0EC005D
     */
    protected WEB3OptionsCloseMarginGroup createSettleContractListLine(WEB3OptionsContractReferenceUnit l_contractReferenceUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createSettleContractListLine(WEB3OptionsContractReferenceUnit l_contractReferenceUnit)";
        log.entering(STR_METHOD_NAME);

        WEB3OptionsCloseMarginGroup l_closeMarginGroup = new WEB3OptionsCloseMarginGroup();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl) l_tradingModule.getPositionManager();

        //�����̎擾
        try
        {
            WEB3IfoContractImpl l_contract = (WEB3IfoContractImpl) l_positionManager.getContract(Long.parseLong(l_contractReferenceUnit.id)); //throw NotFoundException
            double l_dblContractCurrentPrice = l_positionManager.getContractCurrentPrice(l_contract);

            if (Double.isNaN(l_dblContractCurrentPrice))
            {
                l_dblContractCurrentPrice = 0D;
            }
            l_closeMarginGroup.opProductCode = l_contractReferenceUnit.opProductCode; //�����R�[�h
            l_closeMarginGroup.opProductName = l_contractReferenceUnit.opProductName; //������
            l_closeMarginGroup.targetProductCode = l_contractReferenceUnit.targetProductCode; //�w�����
            l_closeMarginGroup.targetProductCode = l_contractReferenceUnit.targetProductCode; //������
            l_closeMarginGroup.delivaryMonth = l_contractReferenceUnit.delivaryMonth; //����
            l_closeMarginGroup.opProductType = l_contractReferenceUnit.opProductType; //�I�v�V�������i�敪
            l_closeMarginGroup.strikePrice = l_contractReferenceUnit.strikePrice; //�s�g���i
            l_closeMarginGroup.marketCode = l_contractReferenceUnit.marketCode; //����s��
            l_closeMarginGroup.contractType = l_contractReferenceUnit.contractType; //���敪
            l_closeMarginGroup.settlementState = l_contractReferenceUnit.settlementState; //���Ϗ�ԋ敪
            l_closeMarginGroup.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblContractCurrentPrice);
            l_closeMarginGroup.sessionType = l_contractReferenceUnit.sessionType;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�f�[�^�s�����G���[�B", l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_closeMarginGroup;
    }

    /**
     * (create���ʖ���)<BR>
     * <BR>
     * �w��̌��ʏƉ�ׂ��猚�ʖ��ׂ��쐬����B<BR>
     * <BR>
     * (1)���ʖ��ׂ̐���<BR>
     * ���ʖ��׃I�u�W�F�N�g�𐶐�����<BR>
     * <BR>
     * (2)�v���p�e�B�̃Z�b�g<BR>
     * �ȉ��̃v���p�e�B�Z�b�g���s��<BR>
     * <BR>
     * ���ʖ���.ID = �p�����[�^.���ʏƉ��.ID<BR>
     * ���ʖ���.���� = �p�����[�^.���ʏƉ��.����<BR>
     * ���ʖ���.���ʐ� = �p�����[�^.���ʏƉ��.������<BR>
     * ���ʖ���.���P�� = �p�����[�^.���ʏƉ��.���P��<BR>
     * ���ʖ���.�������z = �p�����[�^.���ʏƉ��.�������z<BR>
     * ���ʖ���.���萔�� = �p�����[�^.���ʏƉ��.���萔��<BR>
     * ���ʖ���.���v = �p�����[�^.���ʏƉ��.���v<BR>
     * ���ʖ���.���v(���o�) = �p�����[�^.���ʏƉ��.���v(���o�)<BR>
     * ���ʖ���.�ԍϐ��� = NULL<BR>
     * ���ʖ���.���Ϗ��� = NULL<BR>
     * ���ʖ���.�ԍϖ�萔�� = NULL<BR>
     * ���ʖ���.����敪 = �p�����[�^.���ʏƉ��.����敪<BR>
     * <BR>
     * (3)(2)�̌��ʖ��׃I�u�W�F�N�g��ԋp����<BR>
     * @@param l_contractReferenceUnit - �����w���I�v�V�������ʏƉ�׃I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsContractUnit
     * @@roseuid 4084C118031C
     */
    protected WEB3FuturesOptionsContractUnit createContractDetails(WEB3OptionsContractReferenceUnit l_contractReferenceUnit)
    {
        final String STR_METHOD_NAME = "createContractDetails(WEB3OptionsContractReferenceUnit l_contractReferenceUnit)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesOptionsContractUnit l_contractUnit = new WEB3FuturesOptionsContractUnit();

        l_contractUnit.id = l_contractReferenceUnit.id; //ID
        l_contractUnit.contractPrice = l_contractReferenceUnit.contractPrice; //���P��
        l_contractUnit.openDate = WEB3DateUtility.toDay(l_contractReferenceUnit.openDate); //����
        l_contractUnit.contractQuantity = l_contractReferenceUnit.contractOrderQuantity; //���ʐ�
        l_contractUnit.contractExecPrice = l_contractReferenceUnit.contractExecPrice; //�������z
        l_contractUnit.contractCommission = l_contractReferenceUnit.contractCommission; //���萔��
        l_contractUnit.income = l_contractReferenceUnit.income; //���v
        l_contractUnit.incomeCost = l_contractReferenceUnit.incomeCost; //���v(���o�)
        l_contractUnit.contractOrderQuantity = null; //�ԍϐ���
        l_contractUnit.settlePriority = null; //���Ϗ���
        l_contractUnit.contractExecQuantity = null; //�ԍϖ�萔��
        l_contractUnit.sessionType = l_contractReferenceUnit.sessionType;//����敪
        log.exiting(STR_METHOD_NAME);

        return l_contractUnit;
    }

    /**
     * (create���ʏƉ�׈ꗗ)<BR>
     * ���N�G�X�g�f�[�^��茚�ʏƉ�ׂ̈ꗗ���쐬����B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�iOP�ԍϓ��́jget�ԍϓ��͉�ʂP�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ԍϒ������̓��N�G�X�g�f�[�^�I�u�W�F�N�g�B<BR>
     * @@return WEB3OptionsContractReferenceUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3OptionsContractReferenceUnit[] createContractReferenceUnitList(
        WEB3OptionsCloseMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractReferenceUnitList(WEB3OptionsCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoPositionManagerImpl l_positionManager =
            (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();

        ArrayList l_listContractReferenceUnits = new ArrayList();

        try
        {
            for (int i = 0; i < l_request.id.length; i++)
            {
                //getContract(���N�G�X�g�f�[�^.ID : long)
                WEB3IfoContractImpl l_contract =
                    (WEB3IfoContractImpl)l_positionManager.getContract(Long.parseLong(l_request.id[i]));
                //createOP�����ό��ʏƉ��(ArrayList, �敨OP����)
                l_positionManager.createOptionUnSettledContractInquiryDetails(
                    l_listContractReferenceUnits,
                    l_contract);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        WEB3OptionsContractReferenceUnit[] l_contractReferenceUnits =
            new WEB3OptionsContractReferenceUnit[l_listContractReferenceUnits.size()];
        l_listContractReferenceUnits.toArray(l_contractReferenceUnits);

        log.exiting(STR_METHOD_NAME);
        return l_contractReferenceUnits;
    }

    /**
     * (sort���ʏƉ��)<BR>
     * <BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���Ɋ�Â��Č��ʏƉ�ׂ̃\�[�g���s���B<BR>
     * <BR>
     * (1)ArrayList���쐬<BR>
     * <BR>
     * (2)�p�����[�^.�\�[�g�L�[�̔z�񐔕�Loop����<BR>
     * �@@(2-1)�p�����[�^.�\�[�g�L�[.�L�[���ڂ��擾<BR>
     * <BR>
     * �@@(2-2)�p�����[�^.�\�[�g�L�[.����/�~�����擾<BR>
     * <BR>
     * �@@(2-3)�L�[���ڂɂ�镪�򏈗�<BR>
     * �@@�@@�L�[���ڂ������R�[�h�ł������ꍇ�A�����R�[�hComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����v�ł������ꍇ�A���vComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ������ł������ꍇ�A<BR>
     * �@@�@@�@@����Comparator�A����敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^(*)=(2-2)�Ŏ擾��������/�~��] <BR>
     * �@@�@@(*)�p�����[�^�Ɍ����̃L�[���ڂŎ擾�����l��ݒ肷��B<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����Ϗ�ԋ敪�ł������ꍇ�A���Ϗ�ԋ敪Comparator�𐶐�<BR>
     *     [�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>�@@ 
     * <BR>
     * �@@�@@�L�[���ڂ����敪�ł������ꍇ�A���敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@(2-4)(2-3)�ɂč쐬����Comparator��ArrayList�ɒǉ�<BR>
     * <BR>
     * (3)ArrayList����Comparator�̔z����쐬<BR>
     * <BR>
     * (4)Comparator�̔z�񏇂̃\�[�g����<BR>
     * (web3-common)WEB3ArraysUtility.sort(�p�����[�^.���ʏƉ�ׁAComparator[])<BR>
     * <BR>
     * (5)�\�[�g���ꂽ���ʏƉ�ׂ̔z���ԋp<BR>
     * @@param l_contractReferenceDetails - �����w���I�v�V�������ʏƉ�ׂ̔z��
     * @@param l_sortKey - �����w���I�v�V�����\�[�g�L�[�̔z��
     * @@return webbroker3.ifo.message.WEB3OptionsContractReferenceUnit[]
     * @@roseuid 408F2F00031D
     */
    protected WEB3OptionsContractReferenceUnit[] sortContractReferenceDetails(WEB3OptionsContractReferenceUnit[] l_contractReferenceDetails, WEB3FuturesOptionsSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = " sortContractReferenceDetails(WEB3OptionsContractReferenceUnit[] l_contractReferenceDetails, WEB3FuturesOptionsSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);

        List l_lstComparators = new ArrayList();

        for (int i = 0; i < l_sortKey.length; i++)
        {
            String l_strKeyItem = l_sortKey[i].keyItem;
            String l_strAscDesc = l_sortKey[i].ascDesc;

            Comparator l_com = null;
            Comparator l_sessionTypeComparator = null;

            if (WEB3IfoKeyItemDef.PRODUCTCODE.equals(l_strKeyItem))
            {
                //�L�[���ڂ������R�[�h�ł������ꍇ�A�����R�[�hComparator�𐶐�
                l_com = new WEB3OptionsProductCodeComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.INCOME.equals(l_strKeyItem))
            {
                //�L�[���ڂ����v�ł������ꍇ�A���vComparator�𐶐�
                l_com = new WEB3OptionsProfitAndLossComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {
                //�L�[���ڂ������ł������ꍇ�A����Comparator�𐶐�
                l_com = new WEB3OptionsOpenDateComparator(l_strAscDesc);
                //����敪Comparator�𐶐�
                l_sessionTypeComparator = new WEB3OptionsSessionTypeComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.SETTLEMENT_STATUS_DIVISION.equals(l_strKeyItem))
            {
                //�L�[���ڂ����Ϗ�ԋ敪�ł������ꍇ�A���Ϗ�ԋ敪Comparator�𐶐�
                l_com = new WEB3OptionsSettlementStatusTypeComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(l_strKeyItem))
            {
                //�L�[���ڂ����敪�ł������ꍇ�A���敪Comparator�𐶐�
                l_com = new WEB3OptionsContractDivisionComparator(l_strAscDesc);
            }

            if (l_com != null)
            {
                l_lstComparators.add(l_com);
            }
            if (l_sessionTypeComparator != null)
            {
                l_lstComparators.add(l_sessionTypeComparator);
            }

        }

        Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
        l_lstComparators.toArray(l_comparators);

        WEB3ArraysUtility.sort(l_contractReferenceDetails, l_comparators);

        log.exiting(STR_METHOD_NAME);

        return l_contractReferenceDetails;
    }

    /**
     * (sort���ʖ���)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Č��ʖ��ׂ̃\�[�g���s���B<BR>
     * <BR>
     * (1)ArrayList���쐬<BR>
     * <BR>
     * (2)����.�\�[�g�L�[�̔z�񐔕�Loop����<BR>
     * <BR>
     * �@@(2-1)����.�\�[�g�L�[.�L�[���ڂ��擾<BR>
     * <BR>
     * �@@(2-2)�L�[���ڂɂ�镪�򏈗�<BR>
     * <BR>
     * �@@�@@�L�[���ڂ������ł������ꍇ�A<BR>
     * �@@�@@�@@����Comparator�A����敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈���(*) = ����.�\�[�g�L�[.����/�~��]<BR>
     * �@@�@@(*)�p�����[�^�Ɍ����̃L�[���ڂŎ擾�����l��ݒ肷��B<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����P���ł������ꍇ�A���P��Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈��� = ����.�\�[�g�L�[.����/�~��]<BR>
     * <BR>
     * �@@(2-3)(2-2)�ɂč쐬����Comparator��ArrayList�ɒǉ�<BR>
     * <BR>
     * (3)ArrayList����Comparator�̔z����쐬<BR>
     * <BR>
     * (4)Comparator�̔z�񏇂̃\�[�g����<BR>
     * �@@WEB3ArraysUtility.sort(����.���ʖ��ׁAComparator[])<BR>
     * <BR>
     * (5)�\�[�g���ꂽ���ʖ��ׂ̔z���ԋp<BR>
     * @@param l_contractUnits - ���ʖ��ׂ̔z��
     * @@param l_sortKey - �����w���I�v�V�����\�[�g�L�[�̔z��
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsContractUnit
     * @@roseuid 408CAF1D0232
     */
    protected WEB3FuturesOptionsContractUnit[] sortContractDetails(WEB3FuturesOptionsContractUnit[] l_contractUnits, WEB3FuturesOptionsSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = " sortContractDetails(WEB3FuturesOptionsContractUnit[] l_contractUnits, WEB3FuturesOptionsSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);

        List l_lstComparators = new ArrayList();

        for (int i = 0; i < l_sortKey.length; i++)
        {
            String l_strKeyItem = l_sortKey[i].keyItem;
            String l_strAscDesc = l_sortKey[i].ascDesc;

            Comparator l_com = null;


            if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {
                //�L�[���ڂ������ł������ꍇ�A����Comparator�𐶐�
                l_com = new WEB3OptionsOpenDateComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
                //����敪Comparator�𐶐�
                l_com = new WEB3OptionsSessionTypeComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
            }
            else if (WEB3IfoKeyItemDef.CONTRACT_PRICE.equals(l_strKeyItem))
            {
                //�L�[���ڂ����P���ł������ꍇ�A���P��Comparator�𐶐�
                l_com = new WEB3OptionsContractPriceComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
            }
        }

        Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
        l_lstComparators.toArray(l_comparators);

        WEB3ArraysUtility.sort(l_contractUnits, l_comparators);

        log.exiting(STR_METHOD_NAME);

        return l_contractUnits;
    }

    /**
     * (sort���ʖ��׈ꗗ)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Č��ʖ��ׂ̃\�[�g���s���B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�iOP�ԍϓ��́jget�ԍϓ��͉�ʂQ�v�Q��<BR>
     * @@param l_contractUnits - (���ʖ��׈ꗗ)<BR>
     * ���ʖ��ׂ̔z��B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ԍϒ������̓��N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    protected void sortContractUnitList(
        WEB3FuturesOptionsContractUnit[] l_contractUnits, WEB3OptionsCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "sortContractUnitList(WEB3FuturesOptionsContractUnit[], WEB3OptionsCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        //�����w���敨�I�v�V�����\�[�g�L�[�z����쐬����B
        //���N�G�X�g�f�[�^.�\�[�g�L�[�̍��ڂɉ����Ĉȉ���ǉ������\�[�g�L�[�̔z���ݒ肷��B
        WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[2];
        //1. ���N�G�X�g�f�[�^.�\�[�g�L�[[0].�L�[���ڂ������̏ꍇ
        if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_request.futOpSortKeys[0].keyItem))
        {
            l_sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_sortKeys[0].keyItem = WEB3IfoKeyItemDef.OPEN_DATE;
            l_sortKeys[0].ascDesc = l_request.futOpSortKeys[0].ascDesc;
            //�\�[�g�L�[[1].�L�[���� = ���P��
            //�\�[�g�L�[[1].�����^�~�� = "�~��"
            l_sortKeys[1] = new WEB3FuturesOptionsSortKey();
            l_sortKeys[1].keyItem = WEB3IfoKeyItemDef.CONTRACT_PRICE;
            l_sortKeys[1].ascDesc = WEB3AscDescDef.DESC;
        }
        //2. ���N�G�X�g�f�[�^.�\�[�g�L�[[0].�L�[���ڂ����P���̏ꍇ
        else if (WEB3IfoKeyItemDef.CONTRACT_PRICE.equals(l_request.futOpSortKeys[0].keyItem))
        {
            l_sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_sortKeys[0].keyItem = WEB3IfoKeyItemDef.CONTRACT_PRICE;
            l_sortKeys[0].ascDesc = l_request.futOpSortKeys[0].ascDesc;
            //�\�[�g�L�[[1].�L�[���� = ����
            //�\�[�g�L�[[1].�����^�~�� = "����"
            l_sortKeys[1] = new WEB3FuturesOptionsSortKey();
            l_sortKeys[1].keyItem = WEB3IfoKeyItemDef.OPEN_DATE;
            l_sortKeys[1].ascDesc = WEB3AscDescDef.ASC;
        }

        //�w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Č��ʖ��ׂ̃\�[�g���s���B
        l_contractUnits = sortContractDetails(l_contractUnits, l_sortKeys);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (sort�ԍψꗗ�s)<BR>
     * <BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���Ɋ�Â��ĕԍψꗗ�s�̃\�[�g���s���B<BR>
     * <BR>
     * (1)ArrayList���쐬<BR>
     * <BR>
     * (2)�p�����[�^.�\�[�g�L�[�̔z�񐔕�Loop����<BR>
     * �@@(2-1)�p�����[�^.�\�[�g�L�[.�L�[���ڂ��擾<BR>
     * <BR>
     * �@@(2-2)�p�����[�^.�\�[�g�L�[.����/�~�����擾<BR>
     * <BR>
     * �@@(2-3)�L�[���ڂɂ�镪�򏈗�<BR>
     * �@@�@@�L�[���ڂ������R�[�h�ł������ꍇ�A�����R�[�hComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����v�ł������ꍇ�A���vComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����v(���o�)�ł������ꍇ�A���v(���o�)Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����敪�ł������ꍇ�A���敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@(2-4)(2-3)�ɂč쐬����Comparator��ArrayList�ɒǉ�<BR>
     * <BR>
     * (3)ArrayList����Comparator�̔z����쐬<BR>
     * <BR>
     * (4)Comparator�̔z�񏇂̃\�[�g����<BR>
     * (web3-common)WEB3ArraysUtility.sort(�p�����[�^.�ԍψꗗ�s�AComparator[])<BR>
     * <BR>
     * (5)�\�[�g���ꂽ�ԍψꗗ�s�̔z���ԋp<BR>
     * @@param l_settleContractListLine - �����w���I�v�V�����ԍψꗗ�s�̔z��
     * @@param l_sortKey - �����w���I�v�V�����\�[�g�L�[�̔z��
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginGroup[]
     * @@roseuid 4084F9D801A5
     */
    protected WEB3OptionsCloseMarginGroup[] sortSettleContractListLine(WEB3OptionsCloseMarginGroup[] l_closeMarginGroups, WEB3FuturesOptionsSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = " sortSettleContractListLine(WEB3OptionsCloseMarginGroup[] l_closeMarginGroups, WEB3FuturesOptionsSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);

        List l_lstComparators = new ArrayList();

        for (int i = 0; i < l_sortKey.length; i++)
        {
            String l_strKeyItem = l_sortKey[i].keyItem;
            String l_strAscDesc = l_sortKey[i].ascDesc;

            Comparator l_com = null;

            if (WEB3IfoKeyItemDef.PRODUCTCODE.equals(l_strKeyItem))
            {
                //�L�[���ڂ������R�[�h�ł������ꍇ�A�����R�[�hComparator�𐶐�
                l_com = new WEB3OptionsProductCodeComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.INCOME.equals(l_strKeyItem))
            {
                //�L�[���ڂ����v�ł������ꍇ�A���vComparator�𐶐�
                l_com = new WEB3OptionsProfitAndLossComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.INCOME_COST.equals(l_strKeyItem))
            {
                //�L�[���ڂ����v(���o�)�ł������ꍇ�A���v(���o�)Comparator�𐶐�
                l_com = new WEB3OptionsProfitAndLossCostComparator(l_strAscDesc);
            }
            else
            {
                //�L�[���ڂ����敪�ł������ꍇ�A���敪Comparator�𐶐�
                l_com = new WEB3OptionsContractDivisionComparator(l_strAscDesc);
            }

            if (l_com != null)
            {
                l_lstComparators.add(l_com);
            }
        }

        Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
        l_lstComparators.toArray(l_comparators);

        WEB3ArraysUtility.sort(l_closeMarginGroups, l_comparators);

        log.exiting(STR_METHOD_NAME);

        return l_closeMarginGroups;
    }

    /**
     * (set�ԍψꗗ�s)<BR>
     * <BR>
     * �w��̕ԍψꗗ�s�̌��ʖ��ׂ̃}�[�W�����ƃv���p�e�B�Z�b�g���s���B<BR>
     * <BR>
     * (1)���ʖ��ׂ��Ƃ�Loop�����B<BR>
     * �p�����[�^.���ʖ��ׂ̗v�f���Ƃ�Loop�����ɂāA�ȉ��̒l���擾����<BR>
     * <BR>
     * ���v���ʐ� += ���ʖ���[�C���f�b�N�X].���ʐ�<BR>
     * ���v���P�� += (���ʖ���[�C���f�b�N�X].���P�� �~ ���ʖ���[�C���f�b�N�X].���ʐ�)<BR>
     * ���v�������z += ���ʖ���[�C���f�b�N�X].�������z<BR>
     * ���v�����萔�� += ���ʖ���[�C���f�b�N�X].���萔��<BR>
     * ���v���v += ���ʖ���[�C���f�b�N�X].���v<BR>
     * ���v���v(���o��j += ���ʖ���[�C���f�b�N�X].���v�i���o��j <BR>
     * <BR>
     * (2)�v���p�e�B�Z�b�g�B<BR>
     * �p�����[�^.�ԍψꗗ�s�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����<BR>
     * <BR>
     * �ԍψꗗ�s.���ʐ� = ���v���ʐ�<BR>
     * �ԍψꗗ�s.���P�� = �ԍψꗗ�s.���P�� = ���v���P�� �� ���v���ʐ�(�~�����͎l�̌ܓ�)<BR>
     * �ԍψꗗ�s.�������z = ���v�������z<BR>
     * �ԍψꗗ�s.���萔�� = ���v���萔��<BR>
     * �ԍψꗗ�s.���v = ���v���v<BR>
     * �ԍψꗗ�s.���v�i���o��j = ���v���v�i���o��j<BR>
     * �ԍψꗗ�s.���ʖ��� = �p�����[�^.���ʖ���<BR>
     * <BR>
     * (3)(2)�Ńv���p�e�B�Z�b�g�����ԍψꗗ�s��ԋp����B<BR>
     * @@param l_closeMarginGroup - �����w���I�v�V�����ԍψꗗ�s�I�u�W�F�N�g
     * @@param l_contractUnits - ���ʖ��ׂ̔z��
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginGroup
     * @@roseuid 408617C80147
     */
    protected WEB3OptionsCloseMarginGroup setSettleContractListLine(WEB3OptionsCloseMarginGroup l_closeMarginGroup, WEB3FuturesOptionsContractUnit[] l_contractUnits)
    {
        final String STR_METHOD_NAME = " setSettleContractListLine(WEB3OptionsCloseMarginGroup l_closeMarginGroup, WEB3FuturesOptionsContractUnit[] l_contractUnits)";
        log.entering(STR_METHOD_NAME);

        long l_lngTotalQuantity = 0; //���v���ʐ�
        double l_dblTotalPrice = 0; //���v���P��
        double l_dblTotalExecPrice = 0; //���v�������z
        double l_dblcontractCommission = 0; //���v���萔��
        double l_dblTotalIncome = 0; //���v���v
        double l_dblTotalIncomeCost = 0; //���v���v�i���o��j

        BigDecimal l_bdTotalPrice = new BigDecimal(l_dblTotalPrice + "");
        BigDecimal l_bdTotalExecPrice = new BigDecimal(l_dblTotalExecPrice + "");
        BigDecimal l_bdContractCommission = new BigDecimal(l_dblcontractCommission + "");
        BigDecimal l_bdTotalIncome = new BigDecimal(l_dblTotalIncome + "");
        BigDecimal l_bdTotalIncomeCost = new BigDecimal(l_dblTotalIncomeCost + "");

        for (int i = 0; i < l_contractUnits.length; i++)
        {
            //���v���ʐ�
            l_lngTotalQuantity += Long.parseLong(l_contractUnits[i].contractQuantity);
            //���v���P��
            l_bdTotalPrice = l_bdTotalPrice.add(
                new BigDecimal(l_contractUnits[i].contractPrice).multiply(
                    new BigDecimal(l_contractUnits[i].contractQuantity)));
            //���v�������z
            l_bdTotalExecPrice = l_bdTotalExecPrice.add(new BigDecimal(l_contractUnits[i].contractExecPrice));
            //���v���萔��
            l_bdContractCommission = l_bdContractCommission.add(new BigDecimal(l_contractUnits[i].contractCommission));
            //���v���v
            l_bdTotalIncome = l_bdTotalIncome.add(new BigDecimal(l_contractUnits[i].income));
            //���v���v�i���o��j
            l_bdTotalIncomeCost = l_bdTotalIncomeCost.add(new BigDecimal(l_contractUnits[i].incomeCost));
        }
        //�ԍψꗗ�s.���ʐ� = ���v���ʐ�
        l_closeMarginGroup.contractQuantity = WEB3StringTypeUtility.formatNumber(l_lngTotalQuantity);
        //�ԍψꗗ�s.���P�� = �ԍψꗗ�s.���P�� = ���v���P�� �� ���v���ʐ�(�~�����͎l�̌ܓ�)
        l_closeMarginGroup.contractPrice = WEB3StringTypeUtility.formatNumber(l_bdTotalPrice.divide(
            new BigDecimal(l_lngTotalQuantity + ""), 0, BigDecimal.ROUND_HALF_UP).doubleValue());
        //�ԍψꗗ�s.�������z = ���v�������z
        l_closeMarginGroup.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_bdTotalExecPrice.doubleValue());
        //�ԍψꗗ�s.���萔�� = ���v���萔��
        l_closeMarginGroup.contractCommission =
            WEB3StringTypeUtility.formatNumber(l_bdContractCommission.doubleValue());
        //�ԍψꗗ�s.���v = ���v���v
        l_closeMarginGroup.income = WEB3StringTypeUtility.formatNumber(l_bdTotalIncome.doubleValue());
        //�ԍψꗗ�s.���v�i���o��j = ���v���v�i���o��j
        l_closeMarginGroup.incomeCost = WEB3StringTypeUtility.formatNumber(l_bdTotalIncomeCost.doubleValue());
        //�ԍψꗗ�s.���ʖ��� = �p�����[�^.���ʖ���
        l_closeMarginGroup.contractUnits = l_contractUnits;

        log.exiting(STR_METHOD_NAME);

        return l_closeMarginGroup;
    }

    /**
     * (is�Y����Ў戵����)<BR>
     * <BR>
     * �w�肳�ꂽ�������Y���ڋq�̉�ЁE���X�Ŏ戵�\�Ȗ����ł��邩�𔻒肷��B<BR>
     * �戵�\�����̏ꍇ��true���A�戵�s�\�����̏ꍇ��false��ԋp����B<BR>
     * <BR>
     * (1)�����Y�����R�[�h�̎擾<BR>
     * <BR>
     * �敨OP���� = �敨OP�v���_�N�g�}�l�[�W��.getProduct(�p�����[�^.�����R�[�h)<BR>
     * �����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()<BR>
     * <BR>
     * (2)�戵�\�����̔���<BR>
     * <BR>
     * �@@(2-1)�،���ЃR�[�h�A���X�R�[�h�̎擾<BR>
     * �@@���� = �p�����[�^.�⏕����.getMainAccount()<BR>
     * �@@�،���ЃR�[�h = ����.get�،���ЃR�[�h()<BR>
     * �@@���X�R�[�h = ����.get���X�R�[�h()<BR>
     * <BR>
     * �@@(2-2)(���X�w����)�戵�����z��̎擾<BR>
     * �@@�戵�����z�� = (���X�w����)�戵����.get(���X�w����)�戵����(�،���ЃR�[�h�A���X�R�[�h�A�敨�^�I�v�V�����敪)�i*1�j<BR>
     * �@@�i*1�j�敨�^�I�v�V�����敪 = 2:�I�v�V�����i�Œ�j<BR>
     * <BR>
     * �@@(2-3)�戵�����z��v�f����Loop����<BR>
     * �@@�戵����.get�����Y�����R�[�h == (2)�̌����Y�����R�[�h�Ȃ�΁A<BR>
     * break����true��ԋp����<BR>
     * �@@break����Loop���I��������false��ԋp����<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@param l_strProductCode - �����R�[�h
     *
     * @@return boolean
     * @@roseuid 408774F200F9
     */
    protected boolean isInstitutionHandlingProduct(WEB3GentradeSubAccount l_subAccount, String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isInstitutionHandlingProduct(WEB3GentradeSubAccount l_subAccount, String l_strProductCode)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        try
        {
            WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productMgr.getIfoProduct(l_subAccount.getInstitution(), l_strProductCode);

            //�����Y�����R�[�h�̎擾
            String l_strUnderlyingProductCode = l_product.getUnderlyingProductCode();

            MainAccount l_account = l_subAccount.getMainAccount();
            String l_institutionCode = l_account.getInstitution().getInstitutionCode(); //�،���ЃR�[�h
            String l_branchCode = l_account.getBranch().getBranchCode(); //���X�R�[�h

            WEB3GentradeBranchIndexDealtCond[] l_condBranchIndexes =
                WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
                    l_institutionCode, l_branchCode, WEB3FuturesOptionDivDef.OPTION);

            for (int i = 0; i < l_condBranchIndexes.length; i++)
            {
                if (l_strUnderlyingProductCode.endsWith(l_condBranchIndexes[i].getUnderlyingProductCode()))
                {
                    return true;
                }
            }

        }
        catch (Exception l_ex)
        {
            log.error("�f�[�^�s�����G���[�B", l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return false;
    }
}
@
