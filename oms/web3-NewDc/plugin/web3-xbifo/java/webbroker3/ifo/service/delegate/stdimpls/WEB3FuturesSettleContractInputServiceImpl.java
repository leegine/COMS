head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesSettleContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ԍϓ��̓T�[�r�X����(WEB3FuturesSettleContractInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/23 羉s (���u) �V�K�쐬
                 : 2006/07/28 �юu�� (���u) �d�l�ύX�@@���f��486
Revesion History : 2007/06/21 �����F(���u) ���f��713�A717�A721�A722�A724�A729
Revesion History : 2007/11/20 ���n�m (���u) �d�l�ύX ���f��799
Revesion History : 2007/11/28 ���n�m (���u) �d�l�ύX Java�\�[�X�i��{�݌v�ƍ����Ă��Ȃ������jNo.010
Revesion History : 2008/03/12 �����F(���u) ���f��827 834 839 863
Revesion History : 2008/04/14 �����F(���u) ���f��872
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

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.define.WEB3IfoSettlementStateDef;
import webbroker3.ifo.message.WEB3FuturesCloseMarginGroup;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginListRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginListResponse;
import webbroker3.ifo.message.WEB3FuturesContractDivisionComparator;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOpenDateComparator;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3FuturesProductCodeComparator;
import webbroker3.ifo.message.WEB3FuturesProfitAndLossComparator;
import webbroker3.ifo.message.WEB3FuturesProfitAndLossCostComparator;
import webbroker3.ifo.message.WEB3FuturesSessionTypeComparator;
import webbroker3.ifo.message.WEB3FuturesSettlementStatusTypeComparator;
import webbroker3.ifo.message.WEB3FuturesContractPriceComparator;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractInputService;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�敨�ԍϓ��̓T�[�r�XImpl)<BR>
 * �����w���敨�ԍϓ��̓T�[�r�X�����N���X
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesSettleContractInputServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesSettleContractInputService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesSettleContractInputServiceImpl.class);

    /**
     * @@roseuid 40F7A2CD003E
     */
    public WEB3FuturesSettleContractInputServiceImpl()
    {

    }

    /**
     * �����w���敨�ԍϓ��̓T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Aget�ԍψꗗ()�܂��́A<BR>
     * get�ԍϓ��͉��()���\�b�h�̂����ꂩ���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A9699B0168
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3FuturesCloseMarginListRequest)
        {
            l_response = this.getCloseMarginList((WEB3FuturesCloseMarginListRequest) l_request);
        }
        else if (l_request instanceof WEB3FuturesCloseMarginInputRequest)
        {
            l_response = this.getColseMarginInput((WEB3FuturesCloseMarginInputRequest) l_request);
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get�ԍψꗗ)<BR>
     * �����w���敨�̕ԍψꗗ��ʕ\���T�[�r�X�����{����B<BR>
     * <BR>
     * �u�i�敨�ԍϓ��́jget�ԍψꗗ�v�Q�ƁB
     * ==========================================================<BR>
     * �V�[�P���X�}(�u�i�敨�ԍϓ��́jget�ԍψꗗ�v):<BR>
     *  6 get����(Institution, String, String, String, double, String, String)<BR>
     * (�����R�[�h�`�F�b�N)<BR>
     * get�����Ŏw��̖����R�[�h���擾�ł��Ȃ������ꍇ�́A<BR>
     * �u�����R�[�h�`�F�b�N�G���[�v�̗�O��throw����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00301<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���敨�ԍψꗗ��ʕ\�����N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginListResponse
     * @@roseuid 40A9699B0187
     */
    protected WEB3FuturesCloseMarginListResponse getCloseMarginList(WEB3FuturesCloseMarginListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCloseMarginList(WEB3FuturesCloseMarginListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        WEB3FuturesCloseMarginListResponse l_response = (WEB3FuturesCloseMarginListResponse) l_request.createResponse();

        //�⏕�������擾����B
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        //�ԍϒ�����t�R�������{����B
        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();
        l_orderMgr.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

        Institution l_institution = l_subAccount.getInstitution();
        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
        WEB3IfoProductImpl l_ifoProduct = null;

        //���N�G�X�g�f�[�^�ɖ����R�[�h���Z�b�g����Ă���ꍇ
        if (l_request.futProductCode != null)
        {
            try
            {
                //�����I�u�W�F�N�g���擾����B
                l_ifoProduct = l_productMgr.getIfoProduct(
                    l_institution,
                    l_request.futProductCode);
                    
                 if (l_ifoProduct == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (NotFoundException l_nfex)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //���N�G�X�g�f�[�^�ɖ������荀��(����s��A�w����ʁA����)���ݒ肳��Ă���ꍇ
        if (l_request.marketCode != null &&
            l_request.targetProductCode != null &&
            l_request.delivaryMonth != null)
        {
            IfoDerivativeTypeEnum l_ifoDerivativeTypeEnum = null;
            l_ifoDerivativeTypeEnum = IfoDerivativeTypeEnum.FUTURES;
            
            //�����I�u�W�F�N�g���擾����B
            try
            {
                l_ifoProduct = l_productMgr.getIfoProduct(
                    l_institution,
                    l_request.targetProductCode,
                    l_request.delivaryMonth,
                    l_ifoDerivativeTypeEnum,
                    0,
                    WEB3DivisionTypeDef.DIVISION_DEFAULT,
                    l_request.marketCode);
                    
                if (l_ifoProduct == null)
               {
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                       this.getClass().getName() + "." + STR_METHOD_NAME);
               }
           }
           catch (NotFoundException l_nfex)
           {
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                   this.getClass().getName() + "." + STR_METHOD_NAME);
           }
        }
        
        //�s��Ǌԋ߂̎w����z��Ŏ擾����B
        String[] l_closeSuspensions = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.FUTURES);

        //�w������̕ێ����関���ό��ʂ̖����R�[�h�Ɩ������̈ꗗ���擾����B
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl) l_tradingModule.getPositionManager();
        WEB3FuturesOptionsProductCodeNameUnit[] l_productCodeNameUnits = l_positionManager.createProductCodeNameFromContract(l_subAccount, true, WEB3FuturesOptionDivDef.FUTURES);

        //create�����R�[�h����from���ʂ̕Ԃ�l��NULL�̏ꍇ�A���X�|���X�쐬�A
        //�v���p�e�B�Z�b�g�������s���B
        if (l_productCodeNameUnits == null)
        {
            l_response.closeMarginGroups = null; //�����w���I�v�V�����ԍψꗗ�s
            l_response.totalPages = "0"; //���y�[�W��
            l_response.totalRecords = "0"; //�����R�[�h��
            l_response.pageIndex = "0"; //�\���y�[�W�ԍ�
            l_response.messageSuspension = l_closeSuspensions; //����I���x������
            //l_response.opProductCodeNames = null; //�����w���敨�I�v�V���������R�[�h����
            l_response.futOpProductCodeNames = null; //�����w���敨�I�v�V���������R�[�h����
            return l_response;
        }
        //��������(�����R�[�h�w��)�̍쐬
        String l_strQueryString = this.createSearchCondCharacter(l_ifoProduct);
        String[] l_strQueryContainer = this.createSearchCondDataContainer(l_ifoProduct);

        WEB3FuturesContractReferenceUnit[] l_referenceUnits =
            l_positionManager.createFuturesContractInquiryDetails(l_subAccount, WEB3FuturesOptionDivDef.FUTURES, null, l_strQueryString, l_strQueryContainer);

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
            boolean l_blnIsInstHandlingProduct = this.isPertinentInstDealtProduct(l_subAccount, l_referenceUnits[i].futProductCode);
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
        l_sortKeys[0].keyItem = WEB3IfoKeyItemDef.FUTPRODUCTCODE;
        l_sortKeys[0].ascDesc = WEB3AscDescDef.ASC;

        //�����w���敨�I�v�V�����\�[�g�L�[[1].�L�[���� = ���敪
        //�����w���敨�I�v�V�����\�[�g�L�[[1].�����^�~�� = "����"
        l_sortKeys[1] = new WEB3FuturesOptionsSortKey();
        l_sortKeys[1].keyItem = WEB3IfoKeyItemDef.CONTRACT_DIVISION;
        l_sortKeys[1].ascDesc = WEB3AscDescDef.ASC;

        //�����w���敨�I�v�V�����\�[�g�L�[[2].�L�[���� = ���Ϗ�ԋ敪
        //�����w���敨�I�v�V�����\�[�g�L�[[2].�����^�~�� = "����"
        l_sortKeys[2] = new WEB3FuturesOptionsSortKey();
        l_sortKeys[2].keyItem = WEB3IfoKeyItemDef.SETTLEMENT_STATUS_DIVISION;
        l_sortKeys[2].ascDesc = WEB3AscDescDef.ASC;

        //�����w���敨�I�v�V�����\�[�g�L�[[3].�L�[���� = ����
        //�����w���敨�I�v�V�����\�[�g�L�[[3].�����^�~�� = "����"
        l_sortKeys[3] = new WEB3FuturesOptionsSortKey();
        l_sortKeys[3].keyItem = WEB3IfoKeyItemDef.OPEN_DATE;
        l_sortKeys[3].ascDesc = WEB3AscDescDef.ASC;

        WEB3FuturesContractReferenceUnit[] l_returnReferenceUnits = new WEB3FuturesContractReferenceUnit[l_lstReferenceUnits.size()];
        l_lstReferenceUnits.toArray(l_returnReferenceUnits);
        //���ʏƉ�ׂ̃\�[�g�������s���B
        l_returnReferenceUnits = this.sortContractReferenceUnit(l_returnReferenceUnits, l_sortKeys);

        //�����w���敨�ԍψꗗ�s�̔z����쐬����B
        WEB3FuturesCloseMarginGroup[] l_closeMarginGroups = this.createCloseMarginList(l_returnReferenceUnits);

        //�ԍψꗗ�s�̃\�[�g�������s���B
        l_closeMarginGroups = this.sortCloseMarginListLine(l_closeMarginGroups, l_request.futOpSortKeys);

        //�v���y�[�W�ԍ��̕ԍψꗗ�s�z����쐬����B
        WEB3FuturesCloseMarginGroup[] l_closeMarginGroupsPage = createPage(l_request, l_closeMarginGroups);

        l_response.closeMarginGroups = l_closeMarginGroupsPage; //�����w���I�v�V�����ԍψꗗ�s
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

        l_response.totalPages = "" + l_intTotalPages; //���y�[�W��
        l_response.totalRecords = "" + l_intTotalRecords; //�����R�[�h��
        l_response.pageIndex = "" + l_intPageIndex; //�\���y�[�W�ԍ�
        l_response.messageSuspension = l_closeSuspensions; //����I���x������
        l_response.futOpProductCodeNames = l_productCodeNameUnits; //�����w���敨�I�v�V���������R�[�h����


        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get�ԍϓ���)<BR>
     * �����w���敨�̕ԍϓ��͉�ʕ\���T�[�r�X�����{����B<BR>
     * <BR>
     * �u�i�敨�ԍϓ��́jget�ԍϓ��͉�ʁv�Q�ƁB<BR>
     * ==========================================================<BR>
     * �V�[�P���X�}(�u�i�敨�ԍϓ��́jget�ԍϓ��͉�ʁv): 4 (*2)<BR>
     * (*2)����t���[<BR>
     * �������� = ���N�G�X�g�f�[�^.�������ʂƂ���<BR>
     * �������ʂ�NULL�łȂ��ꍇ�̂݉��L�ԍω\���ʃ`�F�b�N�����{����B<BR>
     *<BR>
     * �i�ԍω\���ʃ`�F�b�N)<BR>
     * �������� > �ԍω\���ʂ̏ꍇ�A<BR>
     * �u�ԍω\���ʕs���G���[�v�̗�O��throw����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00303<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3FuturesCloseMarginInputResponse
     * @@roseuid 40A9699B0197
     */
    protected WEB3FuturesCloseMarginInputResponse getColseMarginInput(WEB3FuturesCloseMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getColseMarginInput(WEB3FuturesCloseMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();

        WEB3FuturesCloseMarginInputResponse l_response = (WEB3FuturesCloseMarginInputResponse) l_request.createResponse();

        try
        {
            //get����(�����w���敨�ԍϓ��͉�ʃ��N�G�X�g)
            WEB3IfoContractImpl l_contract = getContract(l_request);
            //reset�����R�[�h
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl)l_contract.getProduct();
            WEB3GentradeTradingTimeManagement.resetProductCode(l_product.getUnderlyingProductCode());
            //�⏕�������擾����B
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

            //�ԍϒ�����t�R�������{����B
            WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();
            l_orderMgr.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);


            Market l_market = l_finObjMgr.getMarket(l_contract.getMarketId());
            Institution l_institution = l_subAccount.getInstitution();

            //�����A����s��`�F�b�N�����{����B
            WEB3IfoOrderManagerReusableValidations l_validations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();

            WEB3GentradeMarket l_gentradeMarket = (WEB3GentradeMarket)l_validations.validateMarket(l_market.getMarketCode(), l_institution.getInstitutionCode());

            //����A�\�����`�F�b�N�����{����B
            WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product0 = (WEB3IfoProductImpl) l_productMgr.getProduct(l_contract.getProduct().getProductId());
            l_validations.validateProductCode(l_product0.getProductCode(), //�����R�[�h
            l_institution.getInstitutionCode() //�،���ЃR�[�h
            );

            //��������̃`�F�b�N���s���A�敨OP��������I�u�W�F�N�g��ԋp����B
            WEB3IfoTradedProductImpl l_tradedProductImpl = l_validations.validateTradedProduct(l_product0,l_gentradeMarket,l_contract.isLong(),false);           
            Date l_datLastTradingDate = l_tradedProductImpl.getLastTradingDate();
            
            //�s��Ǌԋ߂̎w����z��Ŏ擾����B
            String[] l_closeSuspensions = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.FUTURES);

            //create���ʏƉ�׈ꗗ(�����w���敨�ԍϓ��͉�ʃ��N�G�X�g)
            WEB3FuturesContractReferenceUnit[] l_contractReferenceUnits =
                createContractReferenceUnitList(l_request);

            List l_lstContractUnits = new ArrayList();
            int l_intCloseMarginAvailableQuantity = 0;

            for (int i = 0; i < l_contractReferenceUnits.length; i++)
            {
                //�w��̌��ʏƉ�ׂ��猚�ʖ��ׂ��쐬����B
                WEB3FuturesOptionsContractUnit l_contractUnit = this.createContractUnit(l_contractReferenceUnits[i]);

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
                if (l_request.futOrderQuantity != null)
                {
                    int l_intOrderQuantity = Integer.parseInt(l_request.futOrderQuantity);

                    if (l_intOrderQuantity > l_intCloseMarginAvailableQuantity) //�������� > �ԍω\���ʂ̏ꍇ
                    {
                        throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00303, this.getClass().getName() + STR_METHOD_NAME);
                    }
                }

                //sort���ʖ��׈ꗗ(���ʖ���[], �����w���敨�ԍϓ��͉�ʃ��N�G�X�g)
                sortContractUnitList(l_contractUnits, l_request);
                
                double l_intOrderQuantity = 0D;
                if (WEB3StringTypeUtility.isNumber(l_request.futOrderQuantity))
                {
                    l_intOrderQuantity = Double.parseDouble(l_request.futOrderQuantity);
                }
            
                for (int i = 0; i < l_contractUnits.length; i++)
                {
                    //�ԍϐ��ʁA���Ϗ��ʂ̃v���p�e�B�ăZ�b�g����

                    double l_dblCloseMarginQuantity = 0;
                    double l_dblContractQuantity = Double.parseDouble(l_contractUnits[i].contractQuantity);

                    if (l_request.futOrderQuantity == null)
                    {
                        //�������ʂ�NULL�̏ꍇ
                        //�ԍϐ��� = ���ʖ���.���ʐ�
                        l_dblCloseMarginQuantity = l_dblContractQuantity;
                    }
                    else
                    {

                        if (l_intOrderQuantity >= l_dblContractQuantity)
                        {
                            //�ԍϐ��� = ���ʖ���.���ʐ�
                            l_dblCloseMarginQuantity = l_dblContractQuantity;
                        }
                        else
                        {
                            //�ԍϐ��� = ��������
                            l_dblCloseMarginQuantity = l_intOrderQuantity;
                        }

                        //�������� = �������� - �ԍϐ���
                        l_intOrderQuantity -= l_dblCloseMarginQuantity;

                    }

                    //���ʖ���.�ԍϐ��� = �ԍϐ���
                    l_contractUnits[i].contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblCloseMarginQuantity);

                    //���ʖ���.���Ϗ��� = ���ʖ��חv�f�̃C���f�b�N�X�ԍ� + 1
                    l_contractUnits[i].settlePriority = "" + (i + 1);
                }
            }

            //�戵�\���������I�u�W�F�N�g�𐶐�����B
            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(l_institution.getInstitutionCode(), //�،���ЃR�[�h
                ProductTypeEnum.IFO, //�����^�C�v
                WEB3FuturesOptionDivDef.FUTURES, //�敨�^�I�v�V�����敪
                WEB3MarginTradingDivDef.DEFAULT); //�M�p����敪

            //set����ŏI��(����ŏI�� : Date)
            l_handlingOrderCond.setTradingEndDate(l_datLastTradingDate);

            //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B

            //<�����w���I�v�V�������ʓ��̓��X�|���X����>

            //���X�|���X.�����P���敪�ꗗ = �戵�\�����P���敪�擾�̕Ԃ�l
            if(WEB3IfoContractTypeDef.OPEN_BUY.equals(l_contractReferenceUnits[0].contractType))
            {
                l_response.orderPriceDivList = l_handlingOrderCond.getHandlingPossibleOrderPriceDiv(false, true);
            }
            if(WEB3IfoContractTypeDef.OPEN_SELL.equals(l_contractReferenceUnits[0].contractType))
            {
                l_response.orderPriceDivList = l_handlingOrderCond.getHandlingPossibleOrderPriceDiv(false, false);
            }

            //���X�|���X.���s�����ꗗ = �戵�\���s�����擾�̕Ԃ�l
            String[] l_strPossibleExecCond = l_handlingOrderCond.getHandlingPossibleExecCond();
            l_strPossibleExecCond = l_orderMgr.getHandlingPossibleExecConds(l_response.orderPriceDivList,l_strPossibleExecCond);
            l_response.execCondList = l_strPossibleExecCond;

            //���X�|���X.���������ꗗ = �戵�\���������擾�̕Ԃ�l
            l_response.orderCondTypeList = l_handlingOrderCond.getHandlingPossibleOrderCond();

            //���X�|���X.���������敪�ꗗ = �戵�\���������敪�擾�̕Ԃ�l
            l_response.expirationDateTypeList = l_handlingOrderCond.getHandlingPossibleExpirationDateType();

            //is�o����܂Œ����戵�\<����ŏI���l��>�̕Ԃ�l��true�̏ꍇ
            if (l_handlingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering())
            {
                //���X�|���X.�L�������J�n�� = get�o����܂Œ����J�n��<����ŏI���l��>�̕Ԃ�l
                //get�o����܂Œ����J�n��<����ŏI���l��>(�o����܂Œ����J�n�� : Date)
                //�����ŏI�����l�������o����܂Œ����J�n�����擾����B 
                //[����]  
                //�o����܂Œ����J�n���F�@@null
                l_response.expirationStartDate =
                    l_handlingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(null);

                //���X�|���X.�L�������ŏI�� = get�o����܂Œ����ŏI��<����ŏI���l��>�̕Ԃ�l
                //get�o����܂Œ����ŏI��<����ŏI���l��>(������������ : Date)
                //�����ŏI�����l�������o����܂Œ����ŏI�����擾����B 
                //[����]  
                //�o����܂Œ����J�n���F�@@null
                l_response.expirationEndDate =
                    l_handlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(null);

                //���X�|���X.�L���������j���ꗗ = get�����������j���ꗗ�̕Ԃ�l
                Date[] l_datHoliday = l_handlingOrderCond.getExpirationDateHoliday();    
                l_response.holidayList = l_datHoliday;
            }
            else
            {
                //���X�|���X.�L�������J�n�� = null
                l_response.expirationStartDate = null;

                //���X�|���X.�L�������ŏI�� = null
                l_response.expirationEndDate = null;

                //���X�|���X.�L���������j���ꗗ = null
                l_response.holidayList = null;
            }
            
            //W�w�l�p�̎��s�����ꗗ���擾����B
            String[] l_strWLimitExecutionConditionTypeList = 
                WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(l_strPossibleExecCond,
                    l_handlingOrderCond.getHandlingPossibleOrderCond());
            
            //���X�|���X.W�w�l�p���s�����ꗗ = getW�w�l�p���s�����ꗗ()�̖߂�l
            l_response.wlimitExecCondList = l_strWLimitExecutionConditionTypeList;

            //���X�|���X.����敪 = ������ԊǗ�.get����敪�̖߂�l
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

            FinApp finApp = (FinApp) Services.getService(FinApp.class);
            WEB3QuoteDataSupplierService l_quoteDataSupplierService = (WEB3QuoteDataSupplierService) finApp.getTradingModule(ProductTypeEnum.IFO).getQuoteDataSupplierService();

            WEB3GentradeMainAccount l_gentradeMainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

            WEB3IfoQuoteData l_quoteData = null;
            if (l_gentradeMainAccount.isRealCustomer())
            {
                l_quoteData = l_quoteDataSupplierService.getIfoQuote(l_tradedProductImpl, RealType.REAL);
            }
            else
            {
                l_quoteData = l_quoteDataSupplierService.getIfoQuote(l_tradedProductImpl, RealType.DELAY);
            }

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
     * �������̏ڍׂ̓V�[�P���X�}�u�i�敨�ԍϓ��́jget�ԍϓ��͉�ʂP�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �敨�ԍϓ��̓��N�G�X�g�f�[�^�I�u�W�F�N�g�B<BR>
     * @@return WEB3IfoContractImpl
     * @@throws WEB3BaseException
     */
    protected WEB3IfoContractImpl getContract(WEB3FuturesCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContract(WEB3FuturesCloseMarginInputRequest)";
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
     * ���N�G�X�g�f�[�^�����ƂɁA���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * (1)�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�<BR>
     * <BR>
     * (2)�p�����[�^.�敨OP������NULL�i�����w��j�̏ꍇ�A<BR>
     * �@@�@@����ID�w��𕶎���C���X�^���X�ɒǉ��i����ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@" and product_id = ?"<BR>
     * <BR>
     * (3)������C���X�^���X��ԋp<BR>
     * �i�p�����[�^.�敨OP������NULL�̏ꍇ�ANULL��ԋp����j<BR>
     * @@param l_ifoProduct - �敨OP�����I�u�W�F�N�g
     * @@return String
     * @@roseuid 40A9699B01A7
     */
    protected String createSearchCondCharacter(WEB3IfoProductImpl l_ifoProduct)
    {
        final String STR_METHOD_NAME = "createSearchCondCharacter(String l_strProductCode)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�����R�[�h��NULL�̏ꍇ�ANULL��ԋp����
        if (l_ifoProduct == null)
        {
            return null;
        }

        String l_strQueryString = " and product_id = ? ";

        log.exiting(STR_METHOD_NAME);

        return l_strQueryString;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���N�G�X�g�f�[�^����A���������iwhere�ȉ��w��̕�����j�̃p�����[�^���X�g���쐬����B<BR>
     * <BR>
     * (1)������z����쐬<BR>
     * <BR>
     * (2)�p�����[�^.�敨OP������NULL�i�����w��j�̏ꍇ�A<BR>
     * �@@�@@����ID�𕶎���z��ɃZ�b�g�i����ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@����ID �� �p�����[�^.�敨OP����.����ID<BR>
     * <BR>
     * (3)������z���ԋp<BR>
     * �i�p�����[�^.�敨OP������NULL�̏ꍇ�ANULL��ԋp����j<BR>
     * @@param l_ifoProduct - �敨OP�����I�u�W�F�N�g
     * @@return String[]
     * @@roseuid 40A9699B01C6
     */
    protected String[] createSearchCondDataContainer(WEB3IfoProductImpl l_ifoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSearchCondDataContainer(String l_strProductCode)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�敨OP�����I�u�W�F�N�g��NULL�̏ꍇ�ANULL��ԋp����
        if (l_ifoProduct == null)
        {
            return null;
        }

        String[] l_strQueryContainer = new String[] { "" + l_ifoProduct.getProductId()};

        log.exiting(STR_METHOD_NAME);

        return l_strQueryContainer;

    }

    /**
     * (create�y�[�W)<BR>
     * �v���y�[�W�ԍ��ɊY�����銔���w���敨�ԍψꗗ�s�̔z���<BR>
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
     * (5)ArrayList.toArray�ŊY���y�[�W�ԍ��̕ԍψꗗ�s�̔z���ԋp����
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���敨�ԍψꗗ��ʕ\�����N�G�X�g�I�u�W�F�N�g
     * @@param l_closeMarginListLine - (�ԍψꗗ�s)<BR>
     * �����w���敨�ԍψꗗ�s�̔z��<BR>
     * (�����R�[�h�����̔z��)
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginGroup[]
     * @@roseuid 40A9699B01D6
     */
    protected WEB3FuturesCloseMarginGroup[] createPage(WEB3FuturesCloseMarginListRequest l_request, WEB3FuturesCloseMarginGroup[] l_closeMarginListLine)
    {
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_closeMarginListLine, 
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));
                       
         return (WEB3FuturesCloseMarginGroup[])l_pageIndexInfo.getArrayReturned(WEB3FuturesCloseMarginGroup.class);
    }

    /**
     * (create�ԍψꗗ)<BR>
     * �w��̌��ʏƉ�ׂ̔z�񂩂犔���w���敨�ԍψꗗ�s��<BR>
     * �z����쐬����B<BR>
     * <BR>
     * �u�i�敨�ԍϓ��́jcreate�ԍψꗗ�v�Q�ƁB<BR>
     * @@param l_contractReferenceUnit - (���ʏƉ��)<BR>
     * �����w���敨���ʏƉ�ׂ̔z��
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginGroup[]
     * @@roseuid 40A9699B0214
     */
    protected WEB3FuturesCloseMarginGroup[] createCloseMarginList(WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCloseMarginList(WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit)";
        log.entering(STR_METHOD_NAME);

        List l_lstCloseMarginGroups = new ArrayList();
        List l_lstContractUnits = new ArrayList();

        log.debug("l_contractReferenceUnit = " + l_contractReferenceUnit.length);
        //��ԍψꗗ�s�쐬
        WEB3FuturesCloseMarginGroup l_baseCloseMarginGroup = createCloseMarginListLine(l_contractReferenceUnit[0]);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
         
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
			//�S�j�@@�����R�[�h�A���敪�A���Ϗ�Ԃ���v�A���A���v��Ώۃt���O���s��v(*)
			//     �i�����v��ԍς̏ꍇ�j
			//�����ʏƉ�ׂ͖����R�[�h�E���敪�E���Ϗ�ԋ敪�E�����̏����Ń\�[�g���ꂽ��Ԃł���
            if (!l_baseCloseMarginGroup.futProductCode.equals(l_contractReferenceUnit[i].futProductCode)
                || !l_baseCloseMarginGroup.contractType.equals(l_contractReferenceUnit[i].contractType)
                || (l_baseCloseMarginGroup.futProductCode.equals(l_contractReferenceUnit[i].futProductCode)
                && l_baseCloseMarginGroup.contractType.equals(l_contractReferenceUnit[i].contractType)
                && !l_baseCloseMarginGroup.settlementState.equals(l_contractReferenceUnit[i].settlementState))
                || (l_baseCloseMarginGroup.futProductCode.equals(l_contractReferenceUnit[i].futProductCode)
                && l_baseCloseMarginGroup.contractType.equals(l_contractReferenceUnit[i].contractType)
                && l_baseCloseMarginGroup.settlementState.equals(l_contractReferenceUnit[i].settlementState)
                && l_blnBaseDayTradeFlag != l_blnDayTradeFlag))
            {
                WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[l_lstContractUnits.size()];
                l_lstContractUnits.toArray(l_contractUnits);

                //�w��̕ԍψꗗ�s�̌��ʖ��ׂ̃}�[�W�����ƃv���p�e�B�Z�b�g���s���B
                l_baseCloseMarginGroup = this.setCloseMarginListLine(l_baseCloseMarginGroup, l_contractUnits);

                l_lstCloseMarginGroups.add(l_baseCloseMarginGroup);

                //�V���ɍ쐬�����ԍψꗗ�s����ԍψꗗ�s�Ƃ��ăZ�b�g����
                l_baseCloseMarginGroup = this.createCloseMarginListLine(l_contractReferenceUnit[i]);

                //����v��Ώۃt���O�̐ݒ菈��
                l_blnBaseDayTradeFlag = l_orderMgr.isDayTrade(
                    l_contractReferenceUnit[i].openDate, 
                    l_contractReferenceUnit[i].sessionType);

                l_lstContractUnits = new ArrayList();
            }
            //�w��̌��ʏƉ�ׂ��猚�ʖ��ׂ��쐬����B
            WEB3FuturesOptionsContractUnit l_contractUnit = this.createContractUnit(l_contractReferenceUnit[i]);

            l_lstContractUnits.add(l_contractUnit);

        }

        WEB3FuturesOptionsContractUnit[] l_contractUnits1 = new WEB3FuturesOptionsContractUnit[l_lstContractUnits.size()];
        l_lstContractUnits.toArray(l_contractUnits1);
        l_baseCloseMarginGroup = this.setCloseMarginListLine(l_baseCloseMarginGroup, l_contractUnits1);
        l_lstCloseMarginGroups.add(l_baseCloseMarginGroup);
        log.debug("l_lstCloseMarginGroups = " + l_lstCloseMarginGroups.size());
        WEB3FuturesCloseMarginGroup[] l_closeMarginGroups = new WEB3FuturesCloseMarginGroup[l_lstCloseMarginGroups.size()];
        l_lstCloseMarginGroups.toArray(l_closeMarginGroups);

        log.exiting(STR_METHOD_NAME);

        return l_closeMarginGroups;
    }

    /**
     * (create�ԍψꗗ�s)<BR>
     * �w��̌��ʏƉ�ׂ��犔���w���敨�ԍψꗗ�s���쐬����B<BR>
     * <BR>
     * (1)�ԍψꗗ�s�̐���<BR>
     * �����w���敨�ԍψꗗ�s(�ȉ��A�ԍψꗗ�s)�I�u�W�F�N�g�𐶐�����<BR>
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
     * �ԍψꗗ�s.����s�� = �p�����[�^.���ʏƉ��.����s��<BR>
     * �ԍψꗗ�s.���敪 = �p�����[�^.���ʏƉ��.���敪<BR>
     * �ԍψꗗ�s.���Ϗ�ԋ敪 = �p�����[�^.���ʏƉ��.���Ϗ�ԋ敪<BR>
     * �ԍψꗗ�s.���ݒl = (2)�Ŏ擾��������<BR>
     * �ԍψꗗ�s.����敪 = �p�����[�^.���ʏƉ��.����敪<BR>
     * <BR>
     * (4)(3)�̕ԍψꗗ�s�I�u�W�F�N�g��ԋp����<BR>
     * @@param l_contractReferenceUnit - (���ʏƉ��)<BR>
     * �����w���敨���ʏƉ�׃I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginGroup
     * @@roseuid 40A9699B0243
     */
    protected WEB3FuturesCloseMarginGroup createCloseMarginListLine(WEB3FuturesContractReferenceUnit l_contractReferenceUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCloseMarginListLine(WEB3FuturesContractReferenceUnit l_contractReferenceUnit)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesCloseMarginGroup l_closeMarginGroup = new WEB3FuturesCloseMarginGroup();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl) l_tradingModule.getPositionManager();

        //�����̎擾
        try
        {
            WEB3IfoContractImpl l_contract = (WEB3IfoContractImpl) l_positionManager.getContract(Long.parseLong(l_contractReferenceUnit.id)); //throw NotFoundException
            double l_dblContractCurrentPrice = l_positionManager.getContractCurrentPrice(l_contract);

            //�ԍψꗗ�s.�����R�[�h = �p�����[�^.���ʏƉ��.�����R�[�h
            l_closeMarginGroup.futProductCode = l_contractReferenceUnit.futProductCode; //�����R�[�h
            //�ԍψꗗ�s.������ = �p�����[�^.���ʏƉ��.������
            l_closeMarginGroup.futProductName = l_contractReferenceUnit.futProductName; //������
            //�ԍψꗗ�s.�w����� = �p�����[�^.���ʏƉ��.�w�����
            l_closeMarginGroup.targetProductCode = l_contractReferenceUnit.targetProductCode; //�w�����
            //�ԍψꗗ�s.���� = �p�����[�^.���ʏƉ��.����
            l_closeMarginGroup.delivaryMonth = l_contractReferenceUnit.delivaryMonth; //����

            //�ԍψꗗ�s.����s�� = �p�����[�^.���ʏƉ��.����s��
            l_closeMarginGroup.marketCode = l_contractReferenceUnit.marketCode; //����s��
            //�ԍψꗗ�s.���敪 = �p�����[�^.���ʏƉ��.���敪
            l_closeMarginGroup.contractType = l_contractReferenceUnit.contractType; //���敪
            //�ԍψꗗ�s.���Ϗ�ԋ敪 = �p�����[�^.���ʏƉ��.���Ϗ�ԋ敪
            l_closeMarginGroup.settlementState = l_contractReferenceUnit.settlementState; //���Ϗ�ԋ敪
            //�ԍψꗗ�s.���ݒl = �擾��������
            l_closeMarginGroup.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblContractCurrentPrice); //����
            //�ԍψꗗ�s.����敪 = �p�����[�^.���ʏƉ��.����敪
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
     * @@param l_contractReferenceUnit - (���ʏƉ��)<BR>
     * �����w���敨���ʏƉ�׃I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsContractUnit
     * @@roseuid 40A9699B0253
     */
    protected WEB3FuturesOptionsContractUnit createContractUnit(WEB3FuturesContractReferenceUnit l_contractReferenceUnit)
    {
        final String STR_METHOD_NAME = "createContractDetails(WEB3FuturesContractReferenceUnit l_contractReferenceUnit)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesOptionsContractUnit l_contractUnit = new WEB3FuturesOptionsContractUnit();

        l_contractUnit.id = l_contractReferenceUnit.id; //ID
        l_contractUnit.openDate = WEB3DateUtility.toDay(l_contractReferenceUnit.openDate); //����
        l_contractUnit.contractPrice = l_contractReferenceUnit.contractPrice; //���P��
        l_contractUnit.contractQuantity = l_contractReferenceUnit.contractOrderQuantity; //���ʐ�
        l_contractUnit.contractExecPrice = l_contractReferenceUnit.contractExecPrice; //�������z
        l_contractUnit.contractCommission = l_contractReferenceUnit.contractCommission; //���萔��
        l_contractUnit.income = l_contractReferenceUnit.income; //���v
        l_contractUnit.incomeCost = l_contractReferenceUnit.incomeCost; //���v(���o�)
        l_contractUnit.contractOrderQuantity = null; //�ԍϐ���
        l_contractUnit.settlePriority = null; //���Ϗ���
        l_contractUnit.contractExecQuantity = null; //�ԍϖ�萔��
        l_contractUnit.sessionType = l_contractReferenceUnit.sessionType; //����敪
        log.exiting(STR_METHOD_NAME);

        return l_contractUnit;
    }
    
    /**
     * (create���ʏƉ�׈ꗗ)<BR>
     * ���N�G�X�g�f�[�^��茚�ʏƉ�ׂ̈ꗗ���쐬����B  <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�i�敨�ԍϓ��́jget�ԍϓ��͉�ʂP�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ԍϒ������̓��N�G�X�g�f�[�^�I�u�W�F�N�g�B
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesContractReferenceUnit[] createContractReferenceUnitList(
        WEB3FuturesCloseMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractReferenceUnitList(WEB3FuturesCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesContractReferenceUnit[] l_contractReferenceUnits = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoPositionManagerImpl l_positionManager =
            (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
        //ArrayList
        //���ʏƉ�ׂ��i�[���錚�ʏƉ�׃��X�g�𐶐�����
        ArrayList l_lisContractReferenceUnits = new ArrayList();

        try
        {
            for (int i = 0; i < l_request.id.length; i++)
            {
                //�w�茚��ID�ɊY������敨OP���ʃI�u�W�F�N�g���擾����B
                WEB3IfoContractImpl l_contract =
                    (WEB3IfoContractImpl)l_positionManager.getContract(Long.parseLong(l_request.id[i]));

                //�����ς̌��ʏƉ�ׂ�1���ׂ��쐬����B
                l_positionManager.createFuturesUnSettledContractInquiryDetails(
                    l_lisContractReferenceUnits,
                    l_contract);
            }

            l_contractReferenceUnits =
                new WEB3FuturesContractReferenceUnit[l_lisContractReferenceUnits.size()];
            l_lisContractReferenceUnits.toArray(l_contractReferenceUnits);
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
        return l_contractReferenceUnits;
    }

    /**
     * (sort���ʏƉ��)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Č��ʏƉ�ׂ̃\�[�g���s���B<BR>
     * <BR>
     * (1)ArrayList���쐬<BR>
     * <BR>
     * (2)�p�����[�^.�\�[�g�L�[�̔z�񐔕�Loop����<BR>
     * �@@(2-1)�p�����[�^.�\�[�g�L�[.�L�[���ڂ��擾<BR>
     * <BR>
     * �@@(2-2)�p�����[�^.�\�[�g�L�[.����/�~�����擾<BR>
     * <BR>
     * �@@(2-3)�L�[���ڂɂ�镪�򏈗�<BR>
     * �@@�@@�L�[���ڂ������R�[�h�ł������ꍇ�A�敨�����R�[�hComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����v�ł������ꍇ�A�敨���vComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ������ł������ꍇ�A
     * �@@�@@�敨����Comparator�A�敨����敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����敪�ł������ꍇ�A�敨���敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����Ϗ�ԋ敪�ł������ꍇ�A���Ϗ�ԋ敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@(2-4)(2-3)�ɂč쐬����Comparator��ArrayList�ɒǉ�<BR>
     * <BR>
     * (3)ArrayList����Comparator�̔z����쐬<BR>
     * <BR>
     * (4)Comparator�̔z�񏇂̃\�[�g����<BR>
     * (web3-common)WEB3ArraysUtility.sort(�p�����[�^.���ʏƉ�ׁA<BR>
     * Comparator[])<BR>
     * <BR>
     * (5)�\�[�g���ꂽ���ʏƉ�ׂ̔z���ԋp<BR>
     * @@param l_contractReferenceUnit - (���ʏƉ��)<BR>
     * �����w���敨���ʏƉ�ׂ̔z��
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * �����w���敨�I�v�V�����\�[�g�L�[�̔z��
     * @@return webbroker3.ifo.message.WEB3FuturesContractReferenceUnit[]
     * @@roseuid 40A9699B0291
     */
    protected WEB3FuturesContractReferenceUnit[] sortContractReferenceUnit(WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit, WEB3FuturesOptionsSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = "sortContractReferenceUnit(WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit, WEB3FuturesOptionsSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);

        List l_lstComparators = new ArrayList();

        for (int i = 0; i < l_sortKey.length; i++)
        {
            String l_strKeyItem = l_sortKey[i].keyItem;
            String l_strAscDesc = l_sortKey[i].ascDesc;

            Comparator l_com = null;
            Comparator l_sessionTypeComparator = null;

            if (WEB3IfoKeyItemDef.FUTPRODUCTCODE.equals(l_strKeyItem))
            {
                //�L�[���ڂ������R�[�h�ł������ꍇ�A�����R�[�hComparator�𐶐�
                l_com = new WEB3FuturesProductCodeComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.INCOME.equals(l_strKeyItem))
            {
                //�L�[���ڂ����v�ł������ꍇ�A���vComparator�𐶐�
                l_com = new WEB3FuturesProfitAndLossComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {
                //�L�[���ڂ������ł������ꍇ�A����Comparator�A�敨����敪Comparator�𐶐�
                l_com = new WEB3FuturesOpenDateComparator(l_strAscDesc);
                l_sessionTypeComparator = new WEB3FuturesSessionTypeComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(l_strKeyItem))
            {
                //�L�[���ڂ����敪�ł������ꍇ�A���敪Comparator�𐶐�
                l_com = new WEB3FuturesContractDivisionComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.SETTLEMENT_STATUS_DIVISION.equals(l_strKeyItem))
            {
                //�L�[���ڂ����Ϗ�ԋ敪�ł������ꍇ�A���Ϗ�ԋ敪Comparator�𐶐�
                l_com = new WEB3FuturesSettlementStatusTypeComparator(l_strAscDesc);
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

        WEB3ArraysUtility.sort(l_contractReferenceUnit, l_comparators);

        log.exiting(STR_METHOD_NAME);

        return l_contractReferenceUnit;
    }

    /**
     * (sort���ʖ���)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���Ɋ�Â��Č��ʖ��ׂ̃\�[�g���s���B<BR>
     * <BR>
     * (1)ArrayList���쐬<BR>
     * <BR>
     * (2)�p�����[�^.�\�[�g�L�[�̔z�񐔕�Loop����<BR>
     * �@@(2-1)�p�����[�^.�\�[�g�L�[.�L�[���ڂ��擾<BR>
     * <BR>
     * �@@(2-2)�p�����[�^.�\�[�g�L�[.����/�~�����擾<BR>
     * <BR>
     * �@@(2-3)�L�[���ڂɂ�镪�򏈗�<BR>
     * �@@�@@�L�[���ڂ������ł������ꍇ�A<BR>
     * �@@�@@�敨����Comparator�A�敨����敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����P���ł������ꍇ�A�敨���P��Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@(2-4)(2-3)�ɂč쐬����Comparator��ArrayList�ɒǉ�<BR>
     * <BR>
     * (3)ArrayList����Comparator�̔z����쐬<BR>
     * <BR>
     * (4)Comparator�̔z�񏇂̃\�[�g����<BR>
     * (web3-common)WEB3ArraysUtility.sort(�p�����[�^.���ʖ��ׁA<BR>Comparator[])<BR>
     * <BR>
     * (5)�\�[�g���ꂽ���ʖ��ׂ̔z���ԋp<BR>
     * @@param l_contractUnit - (���ʖ���)<BR>
     * ���ʖ��ׂ̔z��
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * �����w���敨�I�v�V�����\�[�g�L�[�̔z��
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsContractUnit[]
     * @@roseuid 40A9699B02EF
     */
    protected WEB3FuturesOptionsContractUnit[] sortContractUnit(WEB3FuturesOptionsContractUnit[] l_contractUnit, WEB3FuturesOptionsSortKey[] l_sortKey)
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
                //�L�[���ڂ������ł������ꍇ�A����Comparator�A�敨����敪Comparator�𐶐�
                l_com = new WEB3FuturesOpenDateComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
                l_com = new WEB3FuturesSessionTypeComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
            }
            else if (WEB3IfoKeyItemDef.CONTRACT_PRICE.equals(l_strKeyItem))
            {
                //�L�[���ڂ����P���ł������ꍇ�A���P��Comparator�𐶐�
                l_com = new WEB3FuturesContractPriceComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
            }
        }

        Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
        l_lstComparators.toArray(l_comparators);

        WEB3ArraysUtility.sort(l_contractUnit, l_comparators);

        log.exiting(STR_METHOD_NAME);

        return l_contractUnit;
    }

    /**
     * (sort�ԍψꗗ�s)<BR>
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
     * �@@�@@�L�[���ڂ������R�[�h�ł������ꍇ�A<BR>
     *     �敨�����R�[�hComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����v�ł������ꍇ�A�敨���vComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����v(���o�)�ł������ꍇ�A���v(���o�)Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����敪�ł������ꍇ�A�敨���敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@(2-4)(2-3)�ɂč쐬����Comparator��ArrayList�ɒǉ�<BR>
     * <BR>
     * (3)ArrayList����Comparator�̔z����쐬<BR>
     * <BR>
     * (4)Comparator�̔z�񏇂̃\�[�g����<BR>
     * (web3-common)WEB3ArraysUtility.sort(�p�����[�^.�ԍψꗗ�s�A<BR>Comparator[])<BR>
     * <BR>
     * (5)�\�[�g���ꂽ�ԍψꗗ�s�̔z���ԋp<BR>
     * @@param l_closeMarginListLine - (�ԍψꗗ�s)<BR>
     * �����w���敨�ԍψꗗ�s�̔z��
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * �����w���敨�I�v�V�����\�[�g�L�[�̔z��
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginGroup[]
     * @@roseuid 40A9699B033D
     */
    protected WEB3FuturesCloseMarginGroup[] sortCloseMarginListLine(WEB3FuturesCloseMarginGroup[] l_closeMarginListLine, WEB3FuturesOptionsSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = "sortCloseMarginListLine(WEB3FuturesCloseMarginGroup[] l_closeMarginListLine, WEB3FuturesOptionsSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);

        List l_lstComparators = new ArrayList();

        for (int i = 0; i < l_sortKey.length; i++)
        {
            String l_strKeyItem = l_sortKey[i].keyItem;
            String l_strAscDesc = l_sortKey[i].ascDesc;

            Comparator l_com = null;

            if (WEB3IfoKeyItemDef.FUTPRODUCTCODE.equals(l_strKeyItem))
            {
                //�L�[���ڂ������R�[�h�ł������ꍇ�A�敨�����R�[�hComparator�𐶐�
                l_com = new WEB3FuturesProductCodeComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.INCOME.equals(l_strKeyItem))
            {
                //�L�[���ڂ����v�ł������ꍇ�A�敨���vComparator�𐶐�
                l_com = new WEB3FuturesProfitAndLossComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.INCOME_COST.equals(l_strKeyItem))
            {
                //�L�[���ڂ����v(���o�)�ł������ꍇ�A�敨���v(���o�)Comparator�𐶐�
                l_com = new WEB3FuturesProfitAndLossCostComparator(l_strAscDesc);
            }
            else
            {
                //�L�[���ڂ����敪�ł������ꍇ�A�敨���敪Comparator�𐶐�
                l_com = new WEB3FuturesContractDivisionComparator(l_strAscDesc);
            }

            if (l_com != null)
            {
                l_lstComparators.add(l_com);
            }
        }

        Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
        l_lstComparators.toArray(l_comparators);

        WEB3ArraysUtility.sort(l_closeMarginListLine, l_comparators);

        log.exiting(STR_METHOD_NAME);

        return l_closeMarginListLine;
    }

    /**
     * (sort���ʖ��׈ꗗ)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Č��ʖ��ׂ̃\�[�g���s���B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�i�敨�ԍϓ��́jget�ԍϓ��͉�ʂQ�v�Q��<BR>
     * @@param l_contractUnits - (���ʖ��׈ꗗ)<BR>
     * ���ʖ��ׂ̔z��B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ԍϒ������̓��N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    protected void sortContractUnitList(
        WEB3FuturesOptionsContractUnit[] l_contractUnits, WEB3FuturesCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "sortContractUnitList(WEB3FuturesOptionsContractUnit[], WEB3FuturesCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        //�����w���敨�[�g�L�[�z����쐬����B
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
        l_contractUnits = this.sortContractUnit(l_contractUnits, l_sortKeys);

        log.exiting(STR_METHOD_NAME);
    }
    

    /**
     * (set�ԍψꗗ�s)<BR>
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
     * �ԍψꗗ�s.����� = ���v�������z<BR>
     * �ԍψꗗ�s.���萔�� = ���v���萔��<BR>
     * �ԍψꗗ�s.���v = ���v���v<BR>
     * �ԍψꗗ�s.���v�i���o��j = ���v���v�i���o��j<BR>
     * �ԍψꗗ�s.���ʖ��� = �p�����[�^.���ʖ���<BR>
     * <BR>
     * (3)(2)�Ńv���p�e�B�Z�b�g�����ԍψꗗ�s��ԋp����B<BR>
     * @@param l_closeMarginListLine - (�ԍψꗗ�s)<BR>
     * �����w���敨�ԍψꗗ�s�I�u�W�F�N�g
     * @@param l_contractUnit - (���ʖ���)<BR>
     * ���ʖ��ׂ̔z��
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginGroup
     * @@roseuid 40A9699B039B
     */
    protected WEB3FuturesCloseMarginGroup setCloseMarginListLine(WEB3FuturesCloseMarginGroup l_closeMarginListLine, WEB3FuturesOptionsContractUnit[] l_contractUnit)
    {
        final String STR_METHOD_NAME = "setCloseMarginListLine(WEB3FuturesCloseMarginGroup l_closeMarginListLine, WEB3FuturesOptionsContractUnit[] l_contractUnit)";
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

        for (int i = 0; i < l_contractUnit.length; i++)
        {
            //���v���ʐ�
            l_lngTotalQuantity += Long.parseLong(l_contractUnit[i].contractQuantity);
            //���v���P��
            l_bdTotalPrice = l_bdTotalPrice.add(
                new BigDecimal(l_contractUnit[i].contractPrice).multiply(
                    new BigDecimal(l_contractUnit[i].contractQuantity)));
            //���v�������z
            l_bdTotalExecPrice = l_bdTotalExecPrice.add(new BigDecimal(l_contractUnit[i].contractExecPrice));
            //���v���萔��
            l_bdContractCommission = l_bdContractCommission.add(new BigDecimal(l_contractUnit[i].contractCommission));
            //���v���v
            l_bdTotalIncome = l_bdTotalIncome.add(new BigDecimal(l_contractUnit[i].income));
            //���v���v�i���o��j
            l_bdTotalIncomeCost = l_bdTotalIncomeCost.add(new BigDecimal(l_contractUnit[i].incomeCost));
        }
        //�ԍψꗗ�s.���ʐ� = ���v���ʐ�
        l_closeMarginListLine.contractQuantity = WEB3StringTypeUtility.formatNumber(l_lngTotalQuantity);
        //�ԍψꗗ�s.���P�� = �ԍψꗗ�s.���P�� = ���v���P�� �� ���v���ʐ�(�~�����͎l�̌ܓ�)
        l_closeMarginListLine.contractPrice = WEB3StringTypeUtility.formatNumber(
            l_bdTotalPrice.divide(new BigDecimal(l_lngTotalQuantity + ""), 0, BigDecimal.ROUND_HALF_UP).doubleValue());
        //�ԍψꗗ�s.����� = ���v�������z
        l_closeMarginListLine.contractExecPrice =
            WEB3StringTypeUtility.formatNumber(l_bdTotalExecPrice.doubleValue());
        //�ԍψꗗ�s.���萔�� = ���v���萔��
		l_closeMarginListLine.contractCommission =
		    WEB3StringTypeUtility.formatNumber(l_bdContractCommission.doubleValue());
        //�ԍψꗗ�s.���v = ���v���v
        l_closeMarginListLine.income = WEB3StringTypeUtility.formatNumber(l_bdTotalIncome.doubleValue());
        //�ԍψꗗ�s.���v�i���o��j = ���v���v�i���o��j
		l_closeMarginListLine.incomeCost = WEB3StringTypeUtility.formatNumber(l_bdTotalIncomeCost.doubleValue());
        //�ԍψꗗ�s.���ʖ��� = �p�����[�^.���ʖ���
        l_closeMarginListLine.contractUnits = l_contractUnit;

        log.exiting(STR_METHOD_NAME);

        return l_closeMarginListLine;
    }

    /**
     * (is�Y����Ў戵����)<BR>
     * �w�肳�ꂽ�������Y���ڋq�̉�ЁE���X�Ŏ戵�\<BR>
     * �Ȗ����ł��邩�𔻒肷��B<BR>
     * �戵�\�����̏ꍇ��true���A�戵�s�\�����̏ꍇ��false��ԋp<BR>
     * ����B<BR>
     * <BR>
     * (1)�����Y�����R�[�h�̎擾<BR>
     * <BR>
     * �敨OP���� = �敨OP�v���_�N�g�}�l�[�W��.getProduct<BR>
     * (�p�����[�^.�����R�[�h)<BR>
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
     * �@@�戵�����z�� = (���X�w����)�戵����.get(���X�w����)<BR>
     * �戵����(�،���ЃR�[�h�A���X�R�[�h)<BR>
     * <BR>
     * �@@(2-3)�戵�����z��v�f����Loop����<BR>
     * �@@�戵����.get�����Y�����R�[�h == (2)<BR>
     * �̌����Y�����R�[�h�Ȃ�΁Abreak����true��ԋp����<BR>
     * �@@break����Loop���I��������false��ԋp����<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_strProductCode - �����R�[�h
     * 
     * @@return boolean
     * @@roseuid 40A9699B03CA
     */
    protected boolean isPertinentInstDealtProduct(WEB3GentradeSubAccount l_subAccount, String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPertinentInstDealtProduct(WEB3GentradeSubAccount l_subAccount, String l_strProductCode)";
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
            WEB3GentradeBranchIndexDealtCond[] l_condBranchs =
                WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
                    l_institutionCode, l_branchCode, WEB3FuturesOptionDivDef.FUTURES);

            for (int i = 0; i < l_condBranchs.length; i++)
            {
                if (l_strUnderlyingProductCode.equals(l_condBranchs[i].getUnderlyingProductCode()))
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
