head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p������ψꗗ�T�[�r�XImpl(WEB3MarginCloseMarginListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/28 ������ (���u) �V�K�쐬              
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginAccountTypeComparator;
import webbroker3.equity.WEB3MarginAppraisalProfitOrLossComparator;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginCloseStatus;
import webbroker3.equity.WEB3MarginCloseStatusTypeComparator;
import webbroker3.equity.WEB3MarginContractInfo;
import webbroker3.equity.WEB3MarginContractTypeComparator;
import webbroker3.equity.WEB3MarginContractUnitOpenDateComparator;
import webbroker3.equity.WEB3MarginMarketCodeComparator;
import webbroker3.equity.WEB3MarginOpenDateComparator;
import webbroker3.equity.WEB3MarginProductCodeComparator;
import webbroker3.equity.WEB3MarginRepaymentNumComparator;
import webbroker3.equity.WEB3MarginRepaymentTypeComparator;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3MarginContractTypeDef;
import webbroker3.equity.message.WEB3MarginCloseMarginGroup;
import webbroker3.equity.message.WEB3MarginCloseMarginListRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginListResponse;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginIndividualCloseMarginListRequest;
import webbroker3.equity.message.WEB3MarginIndividualCloseMarginListResponse;
//import webbroker3.equity.message.WEB3MarginProductCodeNameUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.message.WEB3MarginSortKey;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginListService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3PageIndexInfo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

/**
 * �i�M�p������ψꗗ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p������ψꗗ�T�[�r�X�����N���X�B
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCloseMarginListServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginCloseMarginListService
{

    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginCloseMarginListServiceImpl.class);

    /**
     * (execute)�B<BR>
     * <BR>
     * �M�p������ψꗗ�T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * get���ψꗗ()�܂��́Aget�ʌ��ψꗗ()���\�b�h�̂����ꂩ���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g
     * @@return message.WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407CB53F025D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request == null)
        {
            log.error(STR_METHOD_NAME + "�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        if (l_request instanceof WEB3MarginCloseMarginListRequest) //validate����
        {
            l_response = getCloseMarginList((WEB3MarginCloseMarginListRequest) l_request);
        }
        else if (l_request instanceof WEB3MarginIndividualCloseMarginListRequest)
        {
            l_response = getIndividualCloseMarginList((WEB3MarginIndividualCloseMarginListRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get���ψꗗ)�B<BR>
     * <BR>
     * �M�p����̌��ψꗗ�\���T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p������ψꗗ�T�[�r�X�jget���ψꗗ�v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     *   �V�[�P���X�}(�u�i�M�p������ψꗗ�T�[�r�X)get���ψꗗ�v) : <BR>   
     *   (6*)getProduct(�،���� : Institution, �����R�[�h : �_���r���[::java::lang::String)<BR>   
     *   (�����R�[�h�`�F�b�N)<BR>
     *   getProduct( )�Ŋ����������擾�ł��Ȃ������ꍇ�́A�u�����R�[�h�G���[�v�̗�O��throw���A�������I������B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00301<BR>
     *   ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3MarginCloseMarginListResponse
     * @@roseuid 40E402970206
     */
    protected WEB3MarginCloseMarginListResponse getCloseMarginList(WEB3MarginCloseMarginListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCloseMarginList(WEB3MarginCloseMarginListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //������t�\���̔�����s���B
        boolean l_blnIsCloseMarginOrderAcceptPossible = true;
        boolean l_blnIsSwapMarginOrderAcceptPossible = true;
        WEB3BaseException l_wbeReturn = null;
        
        //�ԍϒ�����t�\�`�F�b�N
        try
        {
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            l_wbeReturn = l_wbe;
            l_blnIsCloseMarginOrderAcceptPossible = false;
        }
        
        //�������n������t�\�`�F�b�N
        try
        {
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.SWAP_MARGIN);
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsSwapMarginOrderAcceptPossible = false;
        }
        
        //�ԍϒ����A�������n�������Ɏ�t�s�̏ꍇ�̓G���[�𓊂���
        if ( !l_blnIsCloseMarginOrderAcceptPossible && !l_blnIsSwapMarginOrderAcceptPossible)
        {
            throw new WEB3BusinessLayerException(
                l_wbeReturn.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //get�⏕����
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            AccountManager l_accountManager = l_finApp.getAccountManager();

            Institution l_institution = l_accountManager.getInstitution(l_subAccount.getInstitution().getInstitutionCode());
            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();

            WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

            WEB3GentradeFinObjectManager l_finTransactionManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            WEB3EquityProduct l_product = null;

            //(����t���[)�����R�[�h�w�莞(���N�G�X�g�f�[�^.�����R�[�h != null)�̂݁A���L���������{����B
            if (l_request.productCode != null)
            {
                try
                {
                    //�����������擾����B
                    l_product = (WEB3EquityProduct) l_productManager.getProduct(l_institution, l_request.productCode);
                }
                catch (NotFoundException l_nfex)
                {
                    log.error("�����R�[�h�`�F�b�N)getProduct( )�Ŋ����������擾�ł��Ȃ������ꍇ�́A�B", l_nfex);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, STR_METHOD_NAME);
                }
                //(�����R�[�h�`�F�b�N)getProduct( )�Ŋ����������擾�ł��Ȃ������ꍇ�́A�u�����R�[�h�G���[�v�̗�O��throw���A�������I������B
                if (l_product == null)
                {
                    log.error("�����R�[�h�`�F�b�N)getProduct( )�Ŋ����������擾�ł��Ȃ������ꍇ�́A�B");
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, STR_METHOD_NAME);
                }
            }

            WEB3MarginCloseMarginListResponse l_response = (WEB3MarginCloseMarginListResponse)l_request.createResponse();

            //get�M�p�����\�z
            double l_dblSwapLongTradingPower = 0.0;
            WEB3TPTradingPowerService l_tradingPowerService
                = (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
            if (l_request.succFlag == false)
            {
                l_dblSwapLongTradingPower
                    = l_tradingPowerService.getActualReceiptTradingPower(l_subAccount);
            }
            else
            {
                l_dblSwapLongTradingPower
                    = l_tradingPowerService.getSuccActualReceiptTradingPower(l_subAccount, null);
            }

            //���ڋq�I�u�W�F�N�g�͕⏕����.getMainAccount( )�ɂĎ擾����B
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager.getMainAccount(l_subAccount.getAccountId());

            //�ٍϋ敪�F�@@�h���x�M�p�h
            boolean l_isMarginSys = l_mainAccount.isMarginAccountEstablished(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            //�ٍϋ敪�F�@@�h��ʐM�p�h
            boolean l_isMarginGen = l_mainAccount.isMarginAccountEstablished(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
            String l_strMarginTradingDiv = null;
            if (l_isMarginSys && l_isMarginGen)
            {
                l_strMarginTradingDiv = WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN;
            }
            else if (l_isMarginSys && !l_isMarginGen)
            {
                l_strMarginTradingDiv = WEB3MarginTradingDivDef.MARKET_MARGIN;
            }
            else if (!l_isMarginSys && l_isMarginGen)
            {
                l_strMarginTradingDiv = WEB3MarginTradingDivDef.INSTITUTION_MARGIN;
            }
            //�Ǌԋ߂̎s��R�[�h���擾����B
			WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            String[] l_strCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY, l_strMarginTradingDiv);

            //�����ꗗ���擾����B
            List l_lstContract = l_positionManager.getContracts(l_subAccount, ProductTypeEnum.EQUITY, null, "product_id asc", null);

            //(�Y���f�[�^�`�F�b�N)get�����ꗗ( )�̖߂�l��null(���Y���f�[�^�Ȃ�)�̏ꍇ�́A���L�v���p�e�B�Z�b�g���s���A�߂�l��ԋp
            //�M�p������ψꗗ���X�|���X�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
            if (l_lstContract == null || l_lstContract.size() == 0)
            {
                //�����ꗗ�F null
                l_response.productCodeNames = null;
                //�s��R�[�h�ꗗ�F null
                l_response.marketList = null;
                //���ψꗗ�F�@@null
                l_response.closeMarginGroups = null;
                //���y�[�W���F 0
                l_response.totalPages = "0";
                //�����R�[�h���F 0
                l_response.totalRecords = "0";
                //�\���y�[�W�ԍ��F 0
                l_response.pageIndex = "0";
                //�����\�z�F�@@�����\�z�擾����
                l_response.swapLongTradingPower = WEB3StringTypeUtility.formatNumber(l_dblSwapLongTradingPower);
                //����I���x���s��R�[�h�ꗗ�F�@@������ԊǗ�.get�s��ǌx���s��( )�̖߂�l
                l_response.messageSuspension = l_strCloseMarket;

                //�v���p�e�B�Z�b�g�����M�p��������Ɖ�X�|���X��ԋp���A�������I������B
                return l_response;
            }
            //�s��R�[�h�ꗗ���i�[����s��R�[�h�ꗗHashMap�𐶐�����B
            Map l_mapMarket = new TreeMap();

            int l_intContract = l_lstContract.size();

            //�����̗v�f����Loop����
            for (int i = 0; i < l_intContract; i++)
            {
                //���Ϗ�Ԃ��擾����B
                EqtypeContractRow l_row = (EqtypeContractRow) l_lstContract.get(i);
                WEB3EquityContract l_contract = new WEB3EquityContract(l_row);

                //��get���Ϗ��( )�̖߂�l�̐M�p������Ϗ�Ԃ����L��Ԃ̏ꍇ�i�\���ΏۊO�j�A
                //�ȍ~��Loop�������͍s��Ȃ��icontinue;)
                WEB3MarginCloseStatus l_closeStatus = l_positionManager.getMarginCloseStatus(l_contract);
                if (l_closeStatus.closedMarginFlag == false && l_closeStatus.closeMarginFlag == false && l_closeStatus.closingMarginFlag == false)
                {
                    continue;
                }
                
                //�s��ID���擾����B
                long l_lngMarketId = l_row.getMarketId();
                //�s��I�u�W�F�N�g���擾����B
                Market l_market = l_finTransactionManager.getMarket(l_lngMarketId);
                //�s��R�[�h���擾����B
                String l_strMarketCode = l_market.getMarketCode();

                try
                {
                    //validate�s��R�[�h�i�s��R�[�h, �،���ЃR�[�h�j
                    WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
                    l_orderManager.validateMarket(l_strMarketCode, l_institution.getInstitutionCode());
                    
                    //validate�s��R�[�h�ŗ�O��throw����Ȃ������ꍇ�̂�
                    //�s��R�[�h�ꗗHashMap�Ɏs��R�[�h��ǉ�����B
                    l_mapMarket.put((new Long(l_lngMarketId)), l_strMarketCode);
                }
                catch (WEB3BaseException l_wbe)
                {
                    //��O��throw���ꂽ�ꍇ�͒ǉ����Ȃ��B
                }
            }

            String[] l_strMarketCode = new String[l_mapMarket.size()];
            Collection l_cllMarket = new ArrayList();
            l_cllMarket = l_mapMarket.values();
            l_cllMarket.toArray(l_strMarketCode);

            //�M�p������ψꗗ���X�|���X�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
            //l_response.productCodeNames = l_productCodeNames;
            l_response.marketList = l_strMarketCode;

            //create�������ꗗ�̈������쐬����B
            String l_strSearchCond = this.createSearchCondCharacter(l_request.productCode, l_request.marketCode);
            String[] l_strSearchData = this.createSearchCondDataContainers(l_request.productCode, l_request.marketCode);
            boolean l_blnOIsProduct = l_request.productCode != null ? true : false;
            
            WEB3MarginContractInfo[] l_contractInfo = this.createContractInfoList(
                l_subAccount, l_strSearchCond, l_strSearchData, l_blnOIsProduct);

            if (l_contractInfo == null)
            {
                //���ψꗗ�F�@@null
                l_response.closeMarginGroups = null;
                //���y�[�W���F 0
                l_response.totalPages = "0";
                //�����R�[�h���F 0
                l_response.totalRecords = "0";
                //�\���y�[�W�ԍ��F 0
                l_response.pageIndex = "0";
                //�����\�z�F�@@�����\�z�擾����
                l_response.swapLongTradingPower = WEB3StringTypeUtility.formatNumber(l_dblSwapLongTradingPower);
                //����I���x���s��R�[�h�ꗗ�F�@@������ԊǗ�.get�s��ǌx���s��( )�̖߂�l
                l_response.messageSuspension = l_strCloseMarket;

                //�v���p�e�B�Z�b�g�����M�p��������Ɖ�X�|���X��ԋp���A�������I������B
                return l_response;
            }
            //���ψꗗ�s���쐬���邽�߂̈ꊇ�����ɂ�錚�����ꗗ�̃\�[�g���s���B
            this.sortContractInfoListForColseMarginGroupList(l_contractInfo);

            //�������ꗗ���猈�ψꗗ���쐬����B
            WEB3MarginCloseMarginGroup[] l_closeMarginGroup = this.createCloseMarginListFromContractInfoList(l_contractInfo);
            //���ψꗗ���w�肳�ꂽ�\�[�g�L�[�ɏ]���ă\�[�g����B
            this.sortCloseMarginList(l_closeMarginGroup, l_request.sortKeys);

            //�v���y�[�W�ԍ��̌��ψꗗ���쐬����B
			int l_intPageSize = Integer.parseInt(l_request.pageSize); //�y�[�W���\���s��
			int l_intPageIndex = Integer.parseInt(l_request.pageIndex); //�v���y�[�W�ԍ�
			
			WEB3PageIndexInfo l_pageIndexInfo =
			new WEB3PageIndexInfo(l_closeMarginGroup, l_intPageIndex, l_intPageSize);
			
			l_response.closeMarginGroups
			= (WEB3MarginCloseMarginGroup[])l_pageIndexInfo.getArrayReturned(WEB3MarginCloseMarginGroup.class);
			
			l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
			l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
			l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());         
            
            //�����\�z�F�@@�����\�z�擾����
            l_response.swapLongTradingPower = WEB3StringTypeUtility.formatNumber(l_dblSwapLongTradingPower);
            //����I���x���s��R�[�h�ꗗ�F�@@������ԊǗ�.get�s��ǌx���s��( )�̖߂�l
            l_response.messageSuspension = l_strCloseMarket;

            log.exiting(STR_METHOD_NAME);
            return l_response;

        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_nfe);

            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
    }

    /**
     * (get�ʌ��ψꗗ)�B<BR>
     * <BR>
     * �M�p����̌ʌ��ψꗗ�\���T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p������ψꗗ�T�[�r�X�jget�ʌ��ψꗗ�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3MarginIndividualCloseMarginListResponse
     * @@roseuid 40E403180285
     */
    protected WEB3MarginIndividualCloseMarginListResponse getIndividualCloseMarginList(WEB3MarginIndividualCloseMarginListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getIndividualCloseMarginList(WEB3MarginIndividualCloseMarginListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
            l_request.validate();
            //�����h�c�F�@@���N�G�X�g�f�[�^.�h�c[0]
            long l_lngContractId = Long.parseLong(l_request.id[0]);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

            //�����I�u�W�F�N�g���擾����B
            WEB3EquityContract l_contract = (WEB3EquityContract) l_positionManager.getContract(l_lngContractId);
            EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contract.getDataSourceObject();
            //�s��R�[�h��ThreadLocal�ɃZ�b�g�������B
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(l_contract.getMarketId());
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());

            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();


            //������t�\���̔�����s���B
            boolean l_blnIsCloseMarginOrderAcceptPossible = true;
            boolean l_blnIsSwapMarginOrderAcceptPossible = true;
            WEB3BaseException l_wbeReturn = null;
        
            //�ԍϒ�����t�\�`�F�b�N
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BaseException l_wbe)
            {
                l_wbeReturn = l_wbe;
                l_blnIsCloseMarginOrderAcceptPossible = false;
            }
        
            //�������n������t�\�`�F�b�N
            try
            {
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.SWAP_MARGIN);
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BaseException l_wbe)
            {
                l_blnIsSwapMarginOrderAcceptPossible = false;
            }
        
            //�ԍϒ����A�������n�������Ɏ�t�s�̏ꍇ�̓G���[�𓊂���
            if ( !l_blnIsCloseMarginOrderAcceptPossible && !l_blnIsSwapMarginOrderAcceptPossible)
            {
                throw new WEB3BusinessLayerException(
                    l_wbeReturn.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //get�⏕����
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
            //���ڋq�I�u�W�F�N�g�͕⏕����.getMainAccount( )�ɂĎ擾����B
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            //�M�p�������J�݂��Ă��邩�𔻒肷��B
            l_mainAccount.isMarginAccountEstablished(l_contractRow.getRepaymentType());
            //��������I�u�W�F�N�g���擾����B
            WEB3EquityTradedProduct l_product = null;
            try
            {
                l_product = (WEB3EquityTradedProduct) l_contract.getTradedProduct();
            }
            catch (RuntimeSystemException l_rse)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����ID=[" + Long.toString(l_contract.getContractId()) + "]�̌����ɕR�t�������������",
                    l_rse);
            }
            
            //�������擾����B
            double l_dblCurrentPrice = l_productManager.getCurrentPrice(l_product);
            //��������List�i�FArrayList�j�𐶐�����B
            List l_lstContract = new ArrayList();
            //���N�G�X�g�f�[�^.�h�c[]�̗v�f����LOOP����
            for (int i = 0; i < l_request.id.length; i++)
            {
                //�����I�u�W�F�N�g���擾����B
                WEB3EquityContract l_equityContract = (WEB3EquityContract) l_positionManager.getContract(Long.parseLong(l_request.id[i]));

                //�M�p����������ׂ𐶐�����B
                WEB3MarginContractUnit l_contractUnit = new WEB3MarginContractUnit();
                //�h�c�F�@@getContractId()�̖߂�l
                l_contractUnit.id = "" + l_equityContract.getContractId();
                //�����F�@@getOpenDate()�̖߂�l
                l_contractUnit.openDate = WEB3DateUtility.toDay(l_equityContract.getOpenDate());
                //���P���F�@@getContractPrice()�̖߂�l
                l_contractUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_equityContract.getContractPrice());
                //�������F�@@�igetQuantity() - getLockedQuantity()�j�̌v�Z����
                double l_dblQuantity = l_equityContract.getQuantity() - l_equityContract.getLockedQuantity();
                l_contractUnit.contractQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
                //������F�@@get�����()�̖߂�l
                l_contractUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_equityContract.getContractAmount(l_dblQuantity));
                //�]�����v�F�@@get�]�����v()�̖߂�l
                l_contractUnit.appraisalProfitLoss = WEB3StringTypeUtility.formatNumber(
                    l_equityContract.getAppraisalProfitOrLossExpenses(l_dblCurrentPrice, l_dblQuantity));
                //���������F�@@null ���o�������F�@@null ���Ϗ��ʁF�@@nul
                l_contractUnit.orderQuantity = null;
                l_contractUnit.partContQuantity = null;
                l_contractUnit.settlePriority = null;
                //��������List�i�FArrayList�j�ɐM�p����������׃I�u�W�F�N�g��ǉ�����B
                l_lstContract.add(l_contractUnit);
            }

            WEB3MarginContractUnit[] l_Unit = new WEB3MarginContractUnit[l_lstContract.size()];
            l_lstContract.toArray(l_Unit);
            WEB3MarginContractUnitOpenDateComparator[] l_Comparator = new WEB3MarginContractUnitOpenDateComparator[1];
            l_Comparator[0] = new WEB3MarginContractUnitOpenDateComparator(WEB3AscDescDef.ASC);
            //���N�G�X�g�f�[�^.�\�[�g�L�[[]�̎w�菇�ɑΉ�����Comparator�𐶐����A�z��Ŏw�肷��B
            //WEB3EquityKeyItemDef
            //�������ׂ̔z����\�[�g����B
            WEB3ArraysUtility.sort(l_Unit, l_Comparator);
            
            //get�M�p�����\�z
            double l_dblSwapLongTradingPower = 0.0;
            WEB3TPTradingPowerService l_tradingPowerService
                = (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
            l_dblSwapLongTradingPower = l_tradingPowerService.getActualReceiptTradingPower(l_subAccount);
            
            //�����͈ȉ��̒ʂ�ݒ肷��B
            //�Ǌԋ߂̎s����擾����B
			WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            String[] l_strCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY, l_contractRow.getRepaymentType());
            //
            WEB3MarginIndividualCloseMarginListResponse l_reponse = (WEB3MarginIndividualCloseMarginListResponse)l_request.createResponse();
            //EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow) l_product.getDataSourceObject();
            //�����R�[�h�F�@@�������.getProduct().�����R�[�h
            l_reponse.productCode = l_product.getProductCode();
            //�������F�@@�������.getProduct().�������@@
            EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getProduct().getDataSourceObject();
            l_reponse.productName = l_productRow.getStandardName();
            //�s��R�[�h�F�@@�������.getMarket().�s��R�[�h
            l_reponse.marketCode = l_product.getMarketCode();
            //�����敪�F�@@
            if (TaxTypeEnum.NORMAL.equals(l_contractRow.getTaxType()))
            {
                l_reponse.taxType = WEB3TaxTypeDef.NORMAL;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_contractRow.getTaxType()) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_contractRow.getTaxType()))
            {
                l_reponse.taxType = WEB3TaxTypeDef.SPECIAL;
            }
            //���敪�F�@@
            if (l_contract.isLong() == true)
            {
                l_reponse.contractType = WEB3MarginContractTypeDef.OPEN_BUY;
            }
            else
            {
                l_reponse.contractType = WEB3MarginContractTypeDef.OPEN_SELL;
            }
            //�ٍρF
            //�M�p����ٍ�.�ٍϋ敪�F�@@����.�ٍϋ敪
            //�M�p����ٍ�.�ٍϊ����l�F�@@����.�ٍϊ����l
            WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
            l_repaymentUnit.repaymentDiv = l_contractRow.getRepaymentType();
            l_repaymentUnit.repaymentTimeLimit = "" + l_contractRow.getRepaymentNum();
            l_reponse.repayment = l_repaymentUnit;

            //�����\�z�F�@@calc�M�p�����\�z()�̖߂�l
            l_reponse.swapLongTradingPower = WEB3StringTypeUtility.formatNumber(l_dblSwapLongTradingPower);
            //���ݒl�Fget����()�̖߂�l
            l_reponse.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
            //�������׈ꗗ�F�@@��������[]�i��������List.toArray()�̖߂�l�j
            l_reponse.contractUnits = l_Unit;
            //����I���x���s��R�[�h�ꗗ�F�@@������ԊǗ�.get�s��ǌx���s��( )�̖߂�l�z��
            l_reponse.messageSuspension = l_strCloseMarket;

            log.exiting(STR_METHOD_NAME);
            return l_reponse;
        }
        catch (NotFoundException l_nfe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfe);

            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        catch (DataNetworkException l_dnw)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dnw);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_dnw.getMessage(), l_dnw);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqe);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }

    }

    /**
     * (create��������������)�B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�����ƂɁA���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * �P�j�@@�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����.�����R�[�h��null�i�����R�[�h�w��j�̏ꍇ�A<BR>
     * ����ID�w��𕶎���C���X�^���X�ɒǉ�����B<BR>
     * �@@�@@�i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@" and product_id = ?"<BR>
     * <BR>
     * �R�j�@@����.�s��R�[�h��null�i�s��R�[�h�w��j�̏ꍇ�A<BR>
     * �s��ID�w��𕶎���C���X�^���X�ɒǉ�����B<BR>
     * �@@�@@�i�s��R�[�h�ɑΉ�����s��ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@" and market_id = ?"<BR>
     * <BR>
     * �S�j�@@������C���X�^���X��ԋp����B<BR>
     * �i����.�����R�[�h�A����.�s��R�[�h���ǂ����null�̏ꍇ�Anull��ԋp����j<BR>
     * <BR>
     * @@param l_strProductCode - �����R�[�h
     * @@param l_strMarketCode - �s��R�[�h
     * @@return String
     * @@roseuid 40F4B61A009A
     */
    protected String createSearchCondCharacter(String l_strProductCode, String l_strMarketCode)
    {
        final String STR_METHOD_NAME = " createSearchCondCharacter(String l_strProductCode, String l_strMarketCode)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�����B
        StringBuffer l_strSearchCondCharacter = new StringBuffer();
        
        //l_strSearchCondCharacter = null;

        if (l_strProductCode == null && l_strMarketCode == null)
        {
            return null;
        }
        //����.�����R�[�h��null�i�����R�[�h�w��j�̏ꍇ�A����ID�w��𕶎���C���X�^���X�ɒǉ�����B
        if (l_strProductCode != null)
        {
            l_strSearchCondCharacter.append(" and product_id = ?");
        }
        //����.�s��R�[�h��null�i�s��R�[�h�w��j�̏ꍇ�A�s��ID�w��𕶎���C���X�^���X�ɒǉ�����B
        if (l_strMarketCode != null)
        {
            l_strSearchCondCharacter.append(" and market_id = ?");
        }

        //�S�j�@@������C���X�^���X��ԋp����B
        return l_strSearchCondCharacter.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)�B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^����A���������iwhere�ȉ��w��̕�����j��<BR>
     * �p�����[�^���X�g���쐬����B<BR>
     * <BR>
     * �P�j�@@������z����쐬����B<BR>
     * <BR>
     * �Q�j�@@����.�����R�[�h��null�i�����R�[�h�w��j�̏ꍇ�A����ID��<BR>
     * �@@�@@������z��ɃZ�b�g����B�i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@����ID �� �g���v���_�N�g�}�l�[�W��.get����(�،���ЃI�u�W�F�N�g(*1), <BR>
     * �p�����[�^.�����R�[�h).����ID<BR>
     * <BR>
     * �R�j�@@����.�s��R�[�h��null�i�s��R�[�h�w��j�̏ꍇ�A�s��ID��<BR>
     * �@@�@@������z��ɃZ�b�g����B�i�s��R�[�h�ɑΉ�����s��ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@�s��ID �� �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket<BR>
     * (�،���ЃI�u�W�F�N�g(*1), ����.�s��R�[�h).�s��ID<BR>
     * <BR>
     * (*1)�،���ЃI�u�W�F�N�g�́A�⏕����.getInstitution( )�Ŏ擾���ݒ�<BR>
     * <BR>
     * �S�j�@@������z���ԋp����B<BR>
     * �i����.�����R�[�h�A����.�s��R�[�h���ǂ����null�̏ꍇ�Anull��ԋp����j<BR>
     * <BR>
     * @@param l_strProductCode - �����R�[�h
     * @@param l_strMarketCode - �s��R�[�h
     * @@return String[]
     * @@roseuid 40F4B61A009D
     */
    protected String[] createSearchCondDataContainers(String l_strProductCode, String l_strMarketCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createQueryContainer(String l_strProductCode)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�����R�[�h��NULL�̏ꍇ�ANULL��ԋp����
        if (l_strProductCode == null && l_strMarketCode == null)
        {
            return null;
        }
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3GentradeFinObjectManager l_finTransactionManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            //�⏕�������擾����B
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

            WEB3EquityProductManager l_productMgr = (WEB3EquityProductManager) l_tradingModule.getProductManager();
            WEB3EquityProduct l_product = null;
            if (l_strProductCode != null)
            {
                l_product = (WEB3EquityProduct) l_productMgr.getProduct(l_subAccount.getInstitution(), l_strProductCode);
            }
            WEB3GentradeMarket l_market = null;
            if (l_strMarketCode != null)
            {
                l_market = (WEB3GentradeMarket) l_finTransactionManager.getMarket(l_subAccount.getInstitution(), l_strMarketCode);
            }
            String[] l_strQueryContainer = null;
            if (l_strProductCode != null && l_strMarketCode == null)
            {
                l_strQueryContainer = new String[] { "" + l_product.getProductId()};
            }
            else if (l_strProductCode == null && l_strMarketCode != null)
            {
                l_strQueryContainer = new String[] { "" + l_market.getMarketId()};
            }
            else if (l_strProductCode != null && l_strMarketCode != null)
            {
                l_strQueryContainer = new String[] { "" + l_product.getProductId(), "" + l_market.getMarketId()};
            }

            log.exiting(STR_METHOD_NAME);

            return l_strQueryContainer;
        }
        catch (NotFoundException l_nfe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfe);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
    }

    /**
     * (create�������ꗗ)�B<BR>
     * <BR>
     * ���ψꗗ��ʂɕ\�����閾�ׂ̌��ƂȂ錚�����̈ꗗ���쐬����B<BR>
     * <BR>
     * �ȉ��̂����ꂩ�̌��Ϗ�Ԃɓ��Ă͂܂錚�����𒊏o����B<BR>
     * �E������<BR>
     * �E���ϒ�<BR>
     * <BR>
     * ���Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�null��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p������ψꗗ�T�[�r�X�jcreate�������ꗗ�v�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount - �⏕����
     * @@param l_strSearchCondCharacter - �������� ������
     * @@param l_strSearchCondDataContainers - (���������f�[�^�R���e�i)<BR>
     * @@param l_blnIsProductDesignate - (is�����w��)<BR>
     * �����R�[�h���w�肳��Ă���ꍇ�Atrue�B�ȊO�Afalse�B<BR>
     * @@return WEB3MarginContractInfo[]
     * @@roseuid 40F4B63800F8
     */
    protected WEB3MarginContractInfo[] createContractInfoList(
        WEB3GentradeSubAccount l_subAccount,
        String l_strSearchCondCharacter,
        String[] l_strSearchCondDataContainers,
        boolean l_blnIsProductDesignate)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " createQueryContainer(String l_strProductCode)";
        log.entering(STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
            WEB3GentradeFinObjectManager l_finTransactionManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            //�����̈ꗗ���擾����B
            List l_lstContracts = l_positionManager.getContracts(l_subAccount, ProductTypeEnum.EQUITY, l_strSearchCondCharacter, l_strSearchCondDataContainers);
            if (l_lstContracts == null)
            {
				log.exiting(STR_METHOD_NAME);
                return null;
            }
            //���������i�[���錚����񃊃X�g�𐶐�����B
            List l_lstContractInfo = new ArrayList();
            int l_intContractsLength = l_lstContracts.size();
            for (int i = 0; i < l_intContractsLength; i++)
            {
                EqtypeContractRow l_contractRow = (EqtypeContractRow) l_lstContracts.get(i);
                WEB3EquityContract l_contract = (WEB3EquityContract) l_positionManager.getContract(l_contractRow.getContractId());
                //�s��R�[�h���擾����B            
                WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_finTransactionManager.getMarket(l_contractRow.getMarketId());
                //�s��R�[�h���擾����B
                String l_strMarketCode = l_market.getMarketCode();
                //������ԃR���e�L�X�g�Ɏs��R�[�h���Z�b�g����B
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
                //�����̌��Ϗ�Ԃ𔻒肷��B
                WEB3MarginCloseStatus l_closeStatus = l_positionManager.getMarginCloseStatus(l_contract);
				if (l_closeStatus.closeMarginFlag == false
					&& l_closeStatus.closingMarginFlag == false)										
                {
                	continue;
                }
                //get���Ϗ��( )�̖߂�l�̐M�p������Ϗ�Ԃ����L���(������)�̏ꍇ�̂݁A���������{����B
                if (l_closeStatus.closeMarginFlag == true
                    && l_closeStatus.closedMarginFlag == false
                    && l_closeStatus.closingMarginFlag == false)
                {
                    //�����ς̌��������쐬����B
                    WEB3MarginContractInfo l_contractInfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);
                    //�ԍω\�t���O�A�������n�\�t���O�̔�����s���B
                    this.setCloseMarginPossibleFlagToContractInfo(l_contractInfo, l_blnIsProductDesignate);
                    //�쐬�����M�p�����������������񃊃X�g�ɒǉ�����B
					l_lstContractInfo.add(l_contractInfo);
                }
                //get���Ϗ��( )�̖߂�l�̐M�p������Ϗ�Ԃ����L���(���ϒ�)�̏ꍇ�̂݁A���������{����B
                else if (l_closeStatus.closeMarginFlag == false
                    && l_closeStatus.closedMarginFlag == false
                    && l_closeStatus.closingMarginFlag == true)
                {
                    //���ϒ��̌��������쐬����B
                    WEB3MarginContractInfo l_contractInfo = l_positionManager.createClosingMarginContractInfo(l_contract);
                    //�쐬�����M�p�����������������񃊃X�g�ɒǉ�����B
                    l_lstContractInfo.add(l_contractInfo);
                }
                else
                {
                    this.createMultipleContractInfo(
                        l_lstContractInfo, l_contract,l_closeStatus, l_blnIsProductDesignate);
                }
            }
            if (l_lstContractInfo.size() == 0)
            {
				log.exiting(STR_METHOD_NAME);
            	return null;
            }
            else
            {
				WEB3MarginContractInfo[] l_marginContractInfo = new WEB3MarginContractInfo[l_lstContractInfo.size()];
				l_lstContractInfo.toArray(l_marginContractInfo);
				log.exiting(STR_METHOD_NAME);
				return l_marginContractInfo;
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfe);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
    }

    /**
     * (create�����������)�B<BR>
     * <BR>
     * 1�̌����ŕ����̌��������쐬����ꍇ�̏������s���B <BR>
     * <BR>
     * �����̌��Ϗ�Ԃɂ��ƂÂ��A  <BR>
     * �����|�W�V�����}�l�[�W��.create�����ό������( ) <BR>
     * �����|�W�V�����}�l�[�W��.create���ϒ��������( ) <BR>
     * ���\�b�h�̂����ꂩ���R�[������B  <BR>
     * <BR>
     * �P�j�@@���Ϗ�ԁF���L�̏ꍇ(�����ςƌ��ϒ�)�A�����ςƌ��ϒ���2���ׂ��쐬����B  <BR>
     * �@@����.���Ϗ��.���ύσt���O��false  <BR>
     * �@@����.���Ϗ��.�����σt���O��true   <BR>
     * �@@����.���Ϗ��.���ϒ��t���O��true <BR>
     * <BR>
     * �P-�P�j�@@�����|�W�V�����}�l�[�W��.create�����ό������(����.����)  <BR>
     * �P-�Q�j�@@this.set���ω\�t���Oto�������(�P?�P�̖߂�l�̐M�p����������) <BR>
     * �P-�R�j�@@����.������񃊃X�g.add(�P?�Q�Ńv���p�e�B�Z�b�g�����M�p����������) <BR>
     * �P-�S�j�@@�����|�W�V�����}�l�[�W��.create���ϒ��������(����.����)  <BR>
     * �P-�T�j�@@����.������񃊃X�g.add(�P?�S�̖߂�l�̐M�p����������) <BR>
     * <BR>
     * �Q�j�@@���Ϗ�ԁF���L�̏ꍇ(���ύςƖ�����)�A�����ς�1���ׂ��쐬����B <BR>
     * �@@�@@�@@(���ύς̖��ׂ͕K�v�Ȃ�)  <BR>
     * �@@����.���Ϗ��.���ύσt���O��true <BR>
     * �@@����.���Ϗ��.�����σt���O��true   <BR>
     * �@@����.���Ϗ��.���ϒ��t���O��false <BR>
     * <BR>
     * �Q-�P�j�@@�����|�W�V�����}�l�[�W��.create�����ό������(����.����)  <BR>
     * �Q-�Q�j�@@this.set���ω\�t���Oto�������(�Q?�P�̖߂�l�̐M�p����������) <BR>
     * �Q-�R�j�@@����.������񃊃X�g.add(�Q?�Q�Ńv���p�e�B�Z�b�g�����M�p����������) <BR>
     * <BR>
     * �R�j�@@���Ϗ�ԁF���L�̏ꍇ(���ύςƌ��ϒ�)�A���ϒ���1���ׂ��쐬����B <BR>
     * �@@�@@�@@(���ύς̖��ׂ͕K�v�Ȃ�)  <BR>
     * �@@����.���Ϗ��.���ύσt���O��true <BR>
     * �@@����.���Ϗ��.�����σt���O��false   <BR>
     * �@@����.���Ϗ��.���ϒ��t���O��true <BR>
     * <BR>
     * �R-�P�j�@@�����|�W�V�����}�l�[�W��.create���ϒ��������(����.����)  <BR>
     * �R-�Q�j�@@����.������񃊃X�g.add(�R?�P�̖߂�l�̐M�p����������) <BR>
     * <BR>
     * �S�j�@@���Ϗ�ԁF���L�̏ꍇ(���ύςƖ����ςƌ��ϒ�)�A�����ςƌ��ϒ���2���ׂ��쐬����B <BR>
     * �@@�@@�@@(���ύς̖��ׂ͕K�v�Ȃ�)  <BR>
     * �@@����.���Ϗ��.���ύσt���O��true <BR>
     * �@@����.���Ϗ��.�����σt���O��true   <BR>
     * �@@����.���Ϗ��.���ϒ��t���O��true <BR>
     * <BR>
     * �S-�P�j�@@�����|�W�V�����}�l�[�W��.create�����ό������(����.����)  <BR>
     * �S-�Q�j�@@this.set���ω\�t���Oto�������(�S?�Q�̖߂�l�̐M�p����������) <BR>
     * �S-�R�j�@@����.������񃊃X�g.add(�S?�Q�Ńv���p�e�B�Z�b�g�����M�p����������) <BR>
     * �S-�S�j�@@�����|�W�V�����}�l�[�W��.create���ϒ��������(����.����)  <BR>
     * �S-�T�j�@@����.������񃊃X�g.add(�S?�S�̖߂�l�̐M�p����������) <BR>
     * <BR>
     * @@param l_lisContractInfoList - (������񃊃X�g)<BR>
     * �쐬�������������i�[���郊�X�g
     * @@param l_contract - (����)<BR>
     * ���������쐬����Ώۂ̌���
     * @@param l_closeMarginStatus - (���Ϗ��)<BR>
     * �M�p������Ϗ��<BR>
     * @@param l_blnIsProductDesignate - is�����w��<BR>
     * �����w��L��̏ꍇ�Atrue�B�ȊO�Afalse�B<BR>
     * @@roseuid 40F4B63E0221
     */
    protected void createMultipleContractInfo(
        List l_lisContractInfoList,
        WEB3EquityContract l_contract,
        WEB3MarginCloseStatus l_closeMarginStatus,
        boolean l_blnIsProductDesignate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMultipleContractInfo";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        WEB3MarginContractInfo l_contractInfo = null;
        //�P�j�@@���Ϗ�ԁF���L�̏ꍇ(�����ςƌ��ϒ�)�A�����ςƌ��ϒ��� 2���ׂ��쐬����B
        if (l_closeMarginStatus.closedMarginFlag == false
            && l_closeMarginStatus.closeMarginFlag == true
            && l_closeMarginStatus.closingMarginFlag == true)
        {
            //�P�|�P�j�@@�����|�W�V�����}�l�[�W��.create�����ό������(����.����)
            l_contractInfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);

            //�P�|�Q�j�@@this.set���ω\�t���Oto�������(
            this.setCloseMarginPossibleFlagToContractInfo(l_contractInfo, l_blnIsProductDesignate);
            //�P�|�R�j�@@����.������񃊃X�g.add(�P�|�Q�Ńv���p�e�B�Z�b�g�����M�p����������)
            l_lisContractInfoList.add(l_contractInfo);
            //�P�|�S�j�@@�����|�W�V�����}�l�[�W��.create���ϒ��������(����.����) 
            //�P�|�T�j�@@����.������񃊃X�g.add(�P�|�S�̖߂�l�̐M�p����������)
            l_lisContractInfoList.add(l_positionManager.createClosingMarginContractInfo(l_contract));
        }

        //�Q�j�@@���Ϗ�ԁF���L�̏ꍇ(���ύςƖ�����)�A�����ς�1���ׂ��쐬����B
        if (l_closeMarginStatus.closedMarginFlag == true && l_closeMarginStatus.closeMarginFlag == true && l_closeMarginStatus.closingMarginFlag == false)
        {
            //�Q�|�P�j�@@�����|�W�V�����}�l�[�W��.create�����ό������(����.����) 
            l_contractInfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);
            // �Q�|�Q�j�@@this.set���ω\�t���Oto�������
            this.setCloseMarginPossibleFlagToContractInfo(l_contractInfo, l_blnIsProductDesignate);
            //�Q�|�R�j�@@����.������񃊃X�g.add(�Q�|�Q�Ńv���p�e�B�Z�b�g�����M�p����������)
            l_lisContractInfoList.add(l_contractInfo);
        }

        //�R�j�@@���Ϗ�ԁF���L�̏ꍇ(���ύςƌ��ϒ�)�A���ϒ���1���ׂ��쐬����B
        if (l_closeMarginStatus.closedMarginFlag == true && l_closeMarginStatus.closeMarginFlag == false && l_closeMarginStatus.closingMarginFlag == true)
        {
            // �R�|�P�j�@@�����|�W�V�����}�l�[�W��.create���ϒ��������(����.����)             
            //�R�|�Q�j�@@����.������񃊃X�g.add(�R�|�P�̖߂�l�̐M�p����������)
            l_lisContractInfoList.add(l_positionManager.createClosingMarginContractInfo(l_contract));
        }

        if (l_closeMarginStatus.closedMarginFlag == true && l_closeMarginStatus.closeMarginFlag == true && l_closeMarginStatus.closingMarginFlag == true)
        {
            //�S�|�P�j�@@�����|�W�V�����}�l�[�W��.create�����ό������(����.����) 
            l_contractInfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);
            //�S�|�Q�j�@@this.set���ω\�t���Oto�������(�S�|�Q�̖߂�l�̐M�p����������A
            //����.is�����s��w��)
            this.setCloseMarginPossibleFlagToContractInfo(l_contractInfo, l_blnIsProductDesignate);
            // �S�|�R�j�@@����.������񃊃X�g.add(�S�|�Q�Ńv���p�e�B�Z�b�g�����M�p����������)
            l_lisContractInfoList.add(l_contractInfo);

            // �S�|�S�j�@@�����|�W�V�����}�l�[�W��.create���ϒ��������(����.����) 
            // �S�|�T�j�@@����.������񃊃X�g.add(�S�|�S�̖߂�l�̐M�p����������)
            l_lisContractInfoList.add(l_positionManager.createClosingMarginContractInfo(l_contract));
        }
    }

    /**
     * (create���ψꗗfrom�������ꗗ)�B<BR>
     * <BR>
     * �������̈ꗗ����M�p������ψꗗ�s�̈ꗗ���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p������ψꗗ�T�[�r�X�jcreate���ψꗗfrom�������ꗗ�v�Q�ƁB<BR>
     * <BR>
     * @@param l_contractInfoList - (�������ꗗ)<BR>
     * �M�p����������̔z��
     * @@return WEB3MarginCloseMarginGroup[]
     * @@roseuid 40F4CBDA00E8
     */
    protected WEB3MarginCloseMarginGroup[] createCloseMarginListFromContractInfoList(WEB3MarginContractInfo[] l_contractInfoList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createCloseMarginListFromContractInfoList";
        log.entering(STR_METHOD_NAME);

        //�������ׂ��i�[���錚�����׃��X�g�𐶐�����B
        List l_lstContractUnits = new ArrayList();
        //���ψꗗ�s���i�[���錈�ψꗗ�s���X�g�𐶐�����B
        List l_closeMarginGroups = new ArrayList();
        //create���ψꗗ�s(�M�p����������)
        WEB3MarginContractUnit[] l_contractUnit = null;
        WEB3MarginCloseMarginGroup l_marginGroup = this.createCloseMarginGroup(l_contractInfoList[0]);

        for (int i = 0; i < l_contractInfoList.length; i++)
        {
            if (!l_marginGroup.productCode.equals(l_contractInfoList[i].productCode)
                || (l_marginGroup.productCode.equals(l_contractInfoList[i].productCode) && !l_marginGroup.taxType.equals(l_contractInfoList[i].accountType))
                || (l_marginGroup.productCode.equals(l_contractInfoList[i].productCode)
                    && l_marginGroup.taxType.equals(l_contractInfoList[i].accountType)
                    && !l_marginGroup.marketCode.equals(l_contractInfoList[i].marketCode))
                || (l_marginGroup.productCode.equals(l_contractInfoList[i].productCode)
                    && l_marginGroup.taxType.equals(l_contractInfoList[i].accountType)
                    && l_marginGroup.marketCode.equals(l_contractInfoList[i].marketCode)
                    && !l_marginGroup.contractType.equals(l_contractInfoList[i].contractType))
                || (l_marginGroup.productCode.equals(l_contractInfoList[i].productCode)
                    && l_marginGroup.taxType.equals(l_contractInfoList[i].accountType)
                    && l_marginGroup.marketCode.equals(l_contractInfoList[i].marketCode)
                    && l_marginGroup.contractType.equals(l_contractInfoList[i].contractType)
                    && !l_marginGroup.repayment.repaymentDiv.equals(l_contractInfoList[i].repaymentType))
				|| (l_marginGroup.productCode.equals(l_contractInfoList[i].productCode)
					&& l_marginGroup.taxType.equals(l_contractInfoList[i].accountType)
					&& l_marginGroup.marketCode.equals(l_contractInfoList[i].marketCode)
					&& l_marginGroup.contractType.equals(l_contractInfoList[i].contractType)
					&& l_marginGroup.repayment.repaymentDiv.equals(l_contractInfoList[i].repaymentType)
                    && !l_marginGroup.repayment.repaymentTimeLimit.equals(l_contractInfoList[i].repaymentNum))
                || (l_marginGroup.productCode.equals(l_contractInfoList[i].productCode)
                    && l_marginGroup.taxType.equals(l_contractInfoList[i].accountType)
                    && l_marginGroup.marketCode.equals(l_contractInfoList[i].marketCode)
                    && l_marginGroup.contractType.equals(l_contractInfoList[i].contractType)
                    && l_marginGroup.repayment.repaymentDiv.equals(l_contractInfoList[i].repaymentType)
					&& l_marginGroup.repayment.repaymentTimeLimit.equals(l_contractInfoList[i].repaymentNum)
                    && !l_marginGroup.settlementState.equals(l_contractInfoList[i].closingStatusType)))
            {
                //�������ׂ̔z����擾����B
                l_contractUnit = new WEB3MarginContractUnit[l_lstContractUnits.size()];
                l_lstContractUnits.toArray(l_contractUnit);
                //�w��̌��ψꗗ�s�̌������ׂ̃}�[�W�����ƃv���p�e�B�Z�b�g���s���B
                this.setCloseMarginGroup(l_marginGroup, l_contractUnit);
                //���ψꗗ�s���X�g�Ɋ���ψꗗ�s��ǉ�����B
                l_closeMarginGroups.add(l_marginGroup);

                //�V���ɍ쐬�����ԍψꗗ�s����ԍψꗗ�s�Ƃ��ăZ�b�g����
                l_marginGroup = this.createCloseMarginGroup(l_contractInfoList[i]);
                l_lstContractUnits = new ArrayList();
            }
            //�w��̌��ʏƉ�ׂ��猚�ʖ��ׂ��쐬����B
            WEB3MarginContractUnit l_marginContractUnit = this.createContractUnit(l_contractInfoList[i]);
            l_lstContractUnits.add(l_marginContractUnit);
        }

        //�������ׂ̔z����擾����B
        WEB3MarginContractUnit[] l_marginContractUnit1 = new WEB3MarginContractUnit[l_lstContractUnits.size()];
        l_lstContractUnits.toArray(l_marginContractUnit1);
        //�w��̌��ψꗗ�s�̌������ׂ̃}�[�W�����ƃv���p�e�B�Z�b�g���s���B
        this.setCloseMarginGroup(l_marginGroup, l_marginContractUnit1);
        l_closeMarginGroups.add(l_marginGroup);
        WEB3MarginCloseMarginGroup[] l_marginGroups = new WEB3MarginCloseMarginGroup[l_closeMarginGroups.size()];
        l_closeMarginGroups.toArray(l_marginGroups);

        log.exiting(STR_METHOD_NAME);
        return l_marginGroups;
    }

    /**
     * (create���ψꗗ�s)�B<BR>
     * <BR>
     * �w��̌�����񂩂猈�ψꗗ�s���쐬����B<BR>
     * <BR>
     * �P�j�@@�M�p������ψꗗ�s�̐����B<BR>
     * �@@�M�p������ψꗗ�s�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�M�p����ٍς̐����ƃv���p�e�B�Z�b�g�B<BR>
     * �@@�M�p����ٍσI�u�W�F�N�g�𐶐�����B<BR>
     * �@@�M�p����ٍ�.�ٍϋ敪 = ����.�������.�ٍϋ敪<BR>
     * �@@�M�p����ٍ�.�ٍϊ����l = ����.�������.�ٍϊ����l<BR>
     * <BR>
     * �R�j�@@�����̎擾�B<BR>
     * �@@���� = �����|�W�V�����}�l�[�W��.getContract(����.�������.ID)<BR>
     * �@@���� = �����|�W�V�����}�l�[�W��.get��������(����)<BR>
     * <BR>
     * �S�j�@@�v���p�e�B�̃Z�b�g�B<BR>
     * �@@�P�j�Ő����������ψꗗ�s�I�u�W�F�N�g�Ɉȉ��̃v���p�e�B�Z�b�g���s���B<BR>
     * <BR>
     * �@@���ψꗗ�s.�����R�[�h = ����.�������.�����R�[�h<BR>
     * �@@���ψꗗ�s.������ = ����.�������.������<BR>
     * �@@���ψꗗ�s.�s��R�[�h = ����.�������.�s��R�[�h<BR>
     * �@@���ψꗗ�s.�����敪 = ����.�������.�����敪<BR>
     * �@@���ψꗗ�s.���敪 = ����.�������.���敪<BR>
     * �@@���ψꗗ�s.�ٍ� =�@@�Q�j�ɂăv���p�e�B�Z�b�g�����M�p����ٍ� <BR>
     * �@@���ψꗗ�s.���ݒl = �R�j�Ŏ擾��������<BR>
     * �@@���ψꗗ�s.���Ϗ�ԋ敪 = ����.�������.���Ϗ�ԋ敪<BR>
     * �@@���ψꗗ�s.�ԍω\�t���O = ����.�������.�ԍω\�t���O<BR>
     * �@@���ψꗗ�s.�������n�\�t���O = ����.�������.�������n�\�t���O<BR>
     * <BR>
     * �@@���ȉ��̃v���p�e�B�ɂ��Ă͐ݒ���s��Ȃ�<BR>
     * �@@���ψꗗ�s.������<BR>
     * �@@���ψꗗ�s.���ό��P��<BR>
     * �@@���ψꗗ�s.�]�����v<BR>
     * �@@���ψꗗ�s.�������׈ꗗ<BR>
     * <BR>
     * �S�j�@@�R�j�Ńv���p�e�B���Z�b�g�������ψꗗ�s�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_contractInfo - (�������)<BR>
     * �M�p����������
     * @@return WEB3MarginCloseMarginGroup
     * @@roseuid 40F4D9220038
     */
    protected WEB3MarginCloseMarginGroup createCloseMarginGroup(WEB3MarginContractInfo l_contractInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createSettleContractListLine(WEB3OptionsContractReferenceUnit l_contractReferenceUnit)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginGroup l_closeMarginGroup = new WEB3MarginCloseMarginGroup();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        //�����̎擾
        try
        {
            WEB3EquityContract l_contract = (WEB3EquityContract) l_positionManager.getContract(Long.parseLong(l_contractInfo.id)); //throw NotFoundException

            double l_dblContractCurrentPrice;
            try {
                l_dblContractCurrentPrice = l_positionManager.getContractCurrentPrice(l_contract);
            }
            catch (WEB3BaseException l_be) {
                l_dblContractCurrentPrice = 0D;
            }
            if (Double.isNaN(l_dblContractCurrentPrice))
            {
                l_dblContractCurrentPrice = 0D;
            }
            WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
            l_repaymentUnit.repaymentDiv = l_contractInfo.repaymentType; //�ٍϋ敪
            l_repaymentUnit.repaymentTimeLimit = l_contractInfo.repaymentNum; //�ٍϊ����l
            l_closeMarginGroup.productCode = l_contractInfo.productCode; //�����R�[�h
            l_closeMarginGroup.productName = l_contractInfo.standardName; //������
            l_closeMarginGroup.marketCode = l_contractInfo.marketCode; //�s��R�[�h
            l_closeMarginGroup.taxType = l_contractInfo.accountType; //�����敪
            l_closeMarginGroup.contractType = l_contractInfo.contractType; //���敪
            l_closeMarginGroup.repayment = l_repaymentUnit; //�M�p����ٍ�
            if (l_dblContractCurrentPrice == 0D)
            {
				l_closeMarginGroup.currentPrice = null;
            }
            else
            {
				l_closeMarginGroup.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblContractCurrentPrice); //�擾��������
            }            
            l_closeMarginGroup.settlementState = l_contractInfo.closingStatusType; //���Ϗ�ԋ敪
            l_closeMarginGroup.closeMarginFlag = l_contractInfo.closingPossibleFlag; //�ԍω\�t���O
            l_closeMarginGroup.swapFlag = l_contractInfo.swapPossibleFlag; //�������n�\�t���O

        }
        catch (NotFoundException l_ex)
        {
            log.error("�f�[�^�s�����G���[�B", l_ex);

            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_closeMarginGroup;
    }

    /**
     * (create��������)�B<BR>
     * <BR>
     * �w��̌�����񂩂猚�����ׂ��쐬����B<BR>
     * <BR>
     * �P�j�@@�������ׂ̐����B<BR>
     * �@@�M�p����������׃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�v���p�e�B�̃Z�b�g<BR>
     * �@@�P�j�Ő��������������׃I�u�W�F�N�g�Ɉȉ��̃v���p�e�B�Z�b�g���s���B<BR>
     * <BR>
     * �@@��������.ID = ����.�������.ID<BR>
     * �@@��������.���� = ����.�������.����<BR>
     * �@@��������.���P�� = ����.�������.���P��<BR>
     * �@@��������.������ = ����.�������.������<BR>
     * �@@��������.����� = ����.�������.�����<BR>
     * �@@��������.�]�����v = ����.�������.�]�����v(���o��l��)(*)<BR>
     * �@@��������.�������� = NULL<BR>
     * �@@��������.���o������ = NULL<BR>
     * �@@��������.���Ϗ��� = NULL<BR>
     * <BR>
     * �@@(*)���ψꗗ�ɕ\������]�����v�͏��o����l���������̂��g�p����<BR>
     * <BR>
     * �R�j�@@�Q�j�Ńv���p�e�B���Z�b�g�����������׃I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_contractInfo - (�������)<BR>
     * �M�p����������
     * @@return webbroker3.margin.message.WEB3MarginContractUnit
     * @@roseuid 40F4D92D022C
     */
    protected WEB3MarginContractUnit createContractUnit(WEB3MarginContractInfo l_contractInfo)
    {
        WEB3MarginContractUnit l_contractUnit = new WEB3MarginContractUnit();
        l_contractUnit.id = l_contractInfo.id; //ID
        l_contractUnit.openDate = WEB3DateUtility.toDay(l_contractInfo.openDate); //����
        l_contractUnit.contractPrice = l_contractInfo.contractPrice; //���P��
        l_contractUnit.contractQuantity = l_contractInfo.quantity; //������
        l_contractUnit.contractExecPrice = l_contractInfo.contractExecPrice; //�����
        l_contractUnit.appraisalProfitLoss = l_contractInfo.takeExpensesOffEvaluationIncome; //�]�����v
        l_contractUnit.orderQuantity = null; //��������
        l_contractUnit.partContQuantity = null; //���o������
        l_contractUnit.settlePriority = null; //���Ϗ���

        return l_contractUnit;
    }

    /**
     * (set���ω\�t���Oto�������)�B<BR>
     * <BR>
     * �w��̌������̕ԍω\�t���O�A����ь������n�\�t���O��ݒ���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p������ψꗗ�T�[�r�X�jset���ω\�t���Oto�������v�Q�ƁB<BR>
     * <BR>
     * @@param l_contractInfo - (�������)<BR>
     * �ԍω\�t���O�A�������n�\�t���O���Z�b�g����Ώۂ̌������
     * @@param l_blnIsProductDesignate - (is�����w��)<BR>
     * �����R�[�h���w�肳��Ă���ꍇ�Atrue�B�ȊO�Afalse�B<BR>
     * @@roseuid 40F4B653004C
     */
    protected void setCloseMarginPossibleFlagToContractInfo(
        WEB3MarginContractInfo l_contractInfo,
        boolean l_blnIsProductDesignate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setCloseMarginPossibleFlagToContractInfo()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeOrderValidator l_orderValidator = (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();
        WEB3GentradeFinObjectManager l_finObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();


        //�evalidate�̌��ʊi�[�p�ϐ�
        boolean l_blnIsValidTradingSubAccount  = true;     //isValid����\�ڋq
        boolean l_blnIsClsMgnOdrAptPossible    = true;     //validate������t�\�i�ԍρj
        boolean l_blnIsSwpMgnOdrAptPossible    = true;     //validate������t�\�i�����E���n�j
        boolean l_blnIsValidMarketCode         = true;     //isValid�s��R�[�h
        boolean l_blnIsValidProductCode        = true;     //isValid�����R�[�h
        boolean l_blnIsNotInsider              = true;     //isNot�C���T�C�_�[
        boolean l_blnIsNotStpdPdctForCls       = true;     //isNot�ڋq���������~�i�ԍρj
        boolean l_blnIsNotStpdPdctForSwp       = true;     //isNot�ڋq���������~�i�����E���n�j
        boolean l_blnIsValidTrddPdctForCls     = true;     //isValid��������i�M�p�^�ԍρj
        boolean l_blnIsValidTrddPdctForSwp     = true;     //isValid��������i�M�p�^�����E���n�j
        boolean l_blnIsValidHandlingMarket     = true;     //isValid�戵�\�s��i�M�p�j

        String l_strInstitutionCode             = null;     //�⏕�����ɕR�Â��،���ЃR�[�h
        WEB3GentradeBranch  l_branch            = null;     //�⏕�����ɕR�Â����X
        WEB3GentradeMarket  l_market            = null;     //validate�s��R�[�h���̎s��
        WEB3EquityProduct   l_product           = null;     //validate�������̖���
        WEB3EquityTradedProduct l_tradeProduct  = null;     //validate����������̎������
        
        //�M�p���������񂩂猚�敪�A�ԍώ��̒�����ʁA�����E���n���̒�����ʂ��擾
        boolean l_blnIsShortContract
            = (l_contractInfo.contractType.equals(String.valueOf(ContractTypeEnum.SHORT.intValue())));
        OrderTypeEnum l_OrderTypeEnumForCls
            = l_blnIsShortContract ? OrderTypeEnum.CLOSE_MARGIN_SHORT : OrderTypeEnum.CLOSE_MARGIN_LONG;
        OrderTypeEnum l_OrderTypeEnumForSwp
            = l_blnIsShortContract ? OrderTypeEnum.SWAP_MARGIN_SHORT : OrderTypeEnum.SWAP_MARGIN_LONG;
        
        
        //get�⏕�����i����сA�⏕��������،���ЃR�[�h�A���X���擾�j
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
        l_strInstitutionCode                = l_subAccount.getInstitution().getInstitutionCode();
        l_branch                            = l_subAccount.getWeb3GenBranch();        


        //validate����\�ڋq
        OrderValidationResult l_orderValidationResult = l_orderValidator.validateSubAccountForTrading(l_subAccount);
        l_blnIsValidTradingSubAccount = l_orderValidationResult.getProcessingResult().isSuccessfulResult();
        if (l_blnIsValidTradingSubAccount == false)
        {
            log.debug("validate����\�ڋq()����O���X���[ ���ԍρ^�������n�s��");
            l_contractInfo.closingPossibleFlag = false;
            l_contractInfo.swapPossibleFlag = false;
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        
        //reset�s��R�[�h
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_contractInfo.marketCode);
        
        
        //�ԍϒ�����t�\�`�F�b�N
        try
        {
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsClsMgnOdrAptPossible = false;
        }
        
        
        //�������n������t�\�`�F�b�N
        try
        {
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.SWAP_MARGIN);
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsSwpMgnOdrAptPossible = false;
        }
        
        
        //validate�s��R�[�h
        try
        {
            l_market = (WEB3GentradeMarket) l_orderManager.validateMarket(
                l_contractInfo.marketCode, l_strInstitutionCode);
            l_blnIsValidMarketCode = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsValidMarketCode = false;
            try
            {
                //(*)validate�s��R�[�h����O��throw�����ꍇ�ɂ́A
                //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket(�s��ID)�ɂĎ擾����B
                log.debug("validate�s��R�[�h�����s�����̂Ŋg�����Z�I�u�W�F�N�g�}�l�[�W������ȉ������Ŏs����擾");
                log.debug("(�s��R�[�h, �،���ЃR�[�h)=(["+l_contractInfo.marketCode+"],["+l_strInstitutionCode+"])");
                l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(
                    l_strInstitutionCode, l_contractInfo.marketCode);
            }
            catch (NotFoundException l_nfe)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(), l_nfe);
            }
        }
        
        
        //validate�����R�[�h
        try
        {
            l_product = (WEB3EquityProduct) l_orderManager.validateProductCode(
                l_contractInfo.productCode, l_strInstitutionCode, l_contractInfo.repaymentType);
            l_blnIsValidProductCode = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            log.debug("validate�����R�[�h�i�M�p�j()����O���X���[ ���ԍρ^�������n�s��");
            l_contractInfo.closingPossibleFlag = false;
            l_contractInfo.swapPossibleFlag = false;
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        
        //validate�C���T�C�_�[
        try
        {
            l_orderManager.validateInsider(l_subAccount, l_product);
            l_blnIsNotInsider = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            log.debug("validate�C���T�C�_�[()����O���X���[ ���ԍρ^�������n�s��");
            l_contractInfo.closingPossibleFlag = false;
            l_contractInfo.swapPossibleFlag = false;
            log.exiting(STR_METHOD_NAME);
            return;
        } 
        
        
        //validate�ڋq�����ʎ����~�i�ԍρj
        try
        {
            l_orderManager.validateAccountProductOrderStop(
                l_subAccount, l_product.getProductId(), l_OrderTypeEnumForCls);
            l_blnIsNotStpdPdctForCls = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsNotStpdPdctForCls = false;
        }
        
        
        //validate�ڋq�����ʎ����~�i�����E���n�j
        try
        {
            l_orderManager.validateAccountProductOrderStop(
                l_subAccount, l_product.getProductId(), l_OrderTypeEnumForSwp);
            l_blnIsNotStpdPdctForSwp = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsNotStpdPdctForSwp = false;
        }
        
        
        //validate��������i�ԍρj
        try
        {
            l_tradeProduct = (WEB3EquityTradedProduct) l_orderManager.validateTradedProductForMarginTrading(
                l_subAccount, l_product, l_market, l_branch,
                l_contractInfo.repaymentType, OrderCategEnum.CLOSE_MARGIN, l_blnIsShortContract);
            l_blnIsValidTrddPdctForCls = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsValidTrddPdctForCls = false;
        }
        
        
        //validate��������i�����E���n�j
        try
        {
            l_tradeProduct = (WEB3EquityTradedProduct) l_orderManager.validateTradedProductForMarginTrading(
                l_subAccount, l_product, l_market, l_branch,
                l_contractInfo.repaymentType, OrderCategEnum.SWAP_MARGIN, l_blnIsShortContract);
            l_blnIsValidTrddPdctForSwp = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsValidTrddPdctForSwp = false;
        }
        
        
        //validate�戵�\�s��
        if (l_blnIsValidTrddPdctForCls || l_blnIsValidTrddPdctForSwp)
        {
            try
            {
                l_orderManager.validateHandlingMarket(
                    l_branch, l_tradeProduct, l_contractInfo.marketCode,
                    l_contractInfo.repaymentType, Double.parseDouble(l_contractInfo.repaymentNum));
                l_blnIsValidHandlingMarket = true;
            }
            catch (WEB3BaseException l_wbe)
            {
                log.debug("validate�戵�\�s��i�M�p�j()����O���X���[ ���ԍρ^�������n�s��");
                l_contractInfo.closingPossibleFlag = false;
                l_contractInfo.swapPossibleFlag = false;
                log.exiting(STR_METHOD_NAME);
                return;
            }
            catch (NumberFormatException l_nfe)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(), l_nfe);
            }
        }
        else
        {
            //validate��������ɂ����ĕԍώ��A�����E���n���̗�����false�������ꍇ
            //validate�戵�\�s����R�[������K�v�Ȃ��B
        }
        
        //�f�o�b�O�p���O�o��
        log.debug("validate����\�ڋq                -> [" + String.valueOf(l_blnIsValidTradingSubAccount) + "]");
        log.debug("validate�s��R�[�h                  -> [" + String.valueOf(l_blnIsValidMarketCode)        + "]");
        log.debug("validate�����R�[�h                  -> [" + String.valueOf(l_blnIsValidProductCode)       + "]");
        log.debug("validate�C���T�C�_�[                -> [" + String.valueOf(l_blnIsNotInsider)             + "]");
        log.debug("validate�ڋq���������~/�ԍ�       -> [" + String.valueOf(l_blnIsNotStpdPdctForCls)      + "]");
        log.debug("validate�ڋq���������~/�����E���n -> [" + String.valueOf(l_blnIsNotStpdPdctForSwp)      + "]");
        log.debug("validate��������i�M�p�j/�ԍ�       -> [" + String.valueOf(l_blnIsValidTrddPdctForCls)    + "]");
        log.debug("validate��������i�M�p�j/�����E���n -> [" + String.valueOf(l_blnIsValidTrddPdctForSwp)    + "]");
        log.debug("validate�戵�\�s��                -> [" + String.valueOf(l_blnIsValidHandlingMarket)    + "]");
        
        //�ԍω\�t���O�̐ݒ�
        l_contractInfo.closingPossibleFlag =
            l_blnIsValidTradingSubAccount &&
            l_blnIsClsMgnOdrAptPossible &&
            l_blnIsValidMarketCode &&
            l_blnIsValidProductCode &&
            l_blnIsNotInsider &&
            l_blnIsNotStpdPdctForCls &&
            l_blnIsValidTrddPdctForCls &&
            l_blnIsValidHandlingMarket;
        
        //�����E���n�\�t���O�̐ݒ�
        l_contractInfo.swapPossibleFlag =
            l_blnIsValidTradingSubAccount &&
            l_blnIsSwpMgnOdrAptPossible &&
            l_blnIsValidProductCode &&
            l_blnIsNotInsider &&
            l_blnIsNotStpdPdctForSwp &&
            l_blnIsValidTrddPdctForSwp &&
            l_blnIsValidHandlingMarket;
            
        log.debug("�ԍω\�t���O          -> [" + String.valueOf(l_contractInfo.closingPossibleFlag) + "]");
        log.debug("�����E���n�\�t���O    -> [" + String.valueOf(l_contractInfo.swapPossibleFlag) + "]");

        log.exiting(STR_METHOD_NAME);
    }
    

    /**
     * (set���ψꗗ�s)�B<BR>
     * <BR>
     * �w��̌��ψꗗ�s�̌������ׂ̃}�[�W�����ƃv���p�e�B�Z�b�g���s���B <BR>
     * �P�j�@@�������ׂ��Ƃ�Loop�����B <BR>
     * �@@����.�������ׂ̗v�f���Ƃ�Loop�����ɂāA�ȉ��̒l���擾����B <BR>
     * <BR>
     * �@@���v������ = ���v������ + ��������[�C���f�b�N�X].������ <BR>
     * �@@���v���P�� = ���v���P�� + �i��������[�C���f�b�N�X].���P�� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�~ ��������[�C���f�b�N�X].�������j <BR>
     * �@@���v�]�����v = ���v�]�����v + ��������[�C���f�b�N�X].�]�����v <BR>
     * <BR>
     * �Q�j�@@�v���p�e�B�Z�b�g�B <BR>
     * �@@����.���ψꗗ�s�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@���ψꗗ�s.������ = ���v������ <BR>
     * �@@���ψꗗ�s.���ό��P�� = ���v���P�� �� ���v������(�~�����͎l�̌ܓ�) <BR>
     * �@@���ψꗗ�s.�]�����v = ���v�]�����v <BR>
     *     ���������A�����̌������ׂɁA�]�����v==null�̖��ׂ��܂܂��ꍇ�́A�Z�b�g���Ȃ��B<BR>
     * �@@���ψꗗ�s.�������׈ꗗ = ����.�������׈ꗗ <BR>
     * <BR>
     * @@param l_closeMarginGroup - (���ψꗗ�s)<BR>
     * �������ׂ̃}�[�W�����ƃv���p�e�B�Z�b�g���s���Ώۂ̐M�p������ψꗗ�s
     * @@param l_contractUnitList - (�������׈ꗗ)<BR>
     * �M�p����������ׂ̔z��
     * @@roseuid 40F4D9400038
     */
    protected void setCloseMarginGroup(WEB3MarginCloseMarginGroup l_closeMarginGroup, WEB3MarginContractUnit[] l_contractUnitList)
    {
        final String STR_METHOD_NAME = " setCloseMarginGroup()";
        log.entering(STR_METHOD_NAME);

        double l_dblDetailContractQuantity = 0; //1���ׂ̌�����
        double l_dblDetailContractPrice = 0;    //1���ׂ̌�����
        double l_dblDetailIncome = 0;           //1���ׂ̌�����
        double l_dblTotalContractQuantity = 0;  //���v������
        double l_dblTotalContractPrice = 0;     //���v���P��
        double l_dblTotalIncome = 0;            //���v�]�����v
        boolean l_blnNullIncome = false;

        for (int i = 0; i < l_contractUnitList.length; i++)
        {
            l_dblDetailContractQuantity =   Double.parseDouble(l_contractUnitList[i].contractQuantity);
            l_dblDetailContractPrice    =   Double.parseDouble(l_contractUnitList[i].contractPrice);
            if (l_contractUnitList[i].appraisalProfitLoss == null)
            {
				l_dblDetailIncome = 0D;
				l_blnNullIncome = true;
            }
            else
            {
				l_dblDetailIncome       =   Double.parseDouble(l_contractUnitList[i].appraisalProfitLoss);
            }
            
            l_dblTotalContractQuantity  +=  l_dblDetailContractQuantity;
            l_dblTotalContractPrice     +=  l_dblDetailContractPrice * l_dblDetailContractQuantity;
            l_dblTotalIncome            +=  l_dblDetailIncome;
        }

        l_closeMarginGroup.contractQuantity = WEB3StringTypeUtility.formatNumber(l_dblTotalContractQuantity);
        l_closeMarginGroup.averageContractPrice
            = WEB3StringTypeUtility.formatNumber(Math.round(l_dblTotalContractPrice / l_dblTotalContractQuantity));
        if (l_blnNullIncome == true)
        {
			l_closeMarginGroup.appraisalProfitLoss = null;
        }
        else
        {
			l_closeMarginGroup.appraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblTotalIncome);
        }
        l_closeMarginGroup.contractUnits = l_contractUnitList;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (sort�������ꗗfor���ψꗗ�s�쐬)�B<BR>
     * <BR>
     * ���ψꗗ�s���쐬���邽�߂̈ꊇ����(*1)�ɂ�錚�����ꗗ�̃\�[�g���s���B<BR>
     * <BR>
     * (*1)�����R�[�h�A�����敪�A�s��R�[�h�A���敪�A�ٍϋ敪�A<BR>
     * �@@�@@���Ϗ�ԋ敪(���Ϗ�Ԃ��قȂ閾�ׂ͕ʍs�Ƃ��ĕ\�����邽��)<BR>
     * <BR>
     * �P�j�@@ArrayList���쐬<BR>
     * <BR>
     * �Q�j�@@Compartor�̍쐬<BR>
     * �@@�@@���L���ԂɂĊeComparator�𐶐����AArrayList�ɒǉ�����B<BR>
     * �@@�@@<BR>
     * �@@�@@�Q�|�P�j�@@�M�p�����R�[�hComparator�i�h�����h�j�𐶐����AArrayList�ɒǉ�<BR>
     * <BR>
     * �@@�@@�Q�|�Q�j�@@�M�p�����敪Comparator�i�h�����h�j�𐶐����AArrayList�ɒǉ�<BR>
     * <BR>
     * �@@�@@�Q�|�R�j�@@�M�p�s��R�[�hComparator�i�h�����h�j�𐶐����AArrayList�ɒǉ�<BR>
     * <BR>
     * �@@�@@�Q�|�S�j�@@�M�p���敪Comparator�i�h�����h�j�𐶐����AArrayList�ɒǉ�<BR>
     * <BR>
     * �@@�@@�Q�|�T�j�@@�M�p�ٍϋ敪Comparator�i�h�����h�j�𐶐����AArrayList�ɒǉ�<BR>
     * <BR>
     * �@@�@@�Q�|�U�j�@@�M�p���Ϗ�ԋ敪Comparator�i�h�����h�j�𐶐����AArrayList�ɒǉ�<BR>
     * <BR>
     * �@@�@@�Q�|�V�j�@@�M�p����Comparator�i�h�����h�j�𐶐����AArrayList�ɒǉ�<BR>
     * �@@�@@<BR>
     * �R�j�@@ArrayList.toArray( )�ɂ�Comparator�̔z����쐬<BR>
     * <BR>
     * �S�j�@@Comparator�̔z�񏇂Ɉ���.���������\�[�g<BR>
     * (web3-common)WEB3ArraysUtility.sort(����.�������ꗗ�A<BR>
     * �R�j�ō쐬����Comparator[])<BR>
     * <BR>
     * @@param l_contractInfoList - (�������ꗗ)<BR>
     * �M�p����������̈ꗗ
     * @@roseuid 40F4C6AC001D
     */
    protected void sortContractInfoListForColseMarginGroupList(WEB3MarginContractInfo[] l_contractInfoList)
    {
        final String STR_METHOD_NAME = " sortContractInfoListForColseMarginGroupList()";
        log.entering(STR_METHOD_NAME);
        String l_strAscDesc = WEB3AscDescDef.ASC;
        //�P�j�@@ArrayList���쐬
        List l_lstComparator = new ArrayList();
        //�Q�|�P�j�@@�M�p�����R�[�hComparator�i�h�����h�j�𐶐����AArrayList�ɒǉ�
        WEB3MarginProductCodeComparator l_productCodeComparator = new WEB3MarginProductCodeComparator(l_strAscDesc);
        l_lstComparator.add(l_productCodeComparator);
        //�Q�|�Q�j�@@�M�p�����敪Comparator�i�h�����h�j�𐶐����AArrayList�ɒǉ�
        WEB3MarginAccountTypeComparator l_accountTypeComparator = new WEB3MarginAccountTypeComparator(l_strAscDesc);
        l_lstComparator.add(l_accountTypeComparator);

        //�Q�|�R�j�@@�M�p�s��R�[�hComparator�i�h�����h�j�𐶐����AArrayList�ɒǉ�
        WEB3MarginMarketCodeComparator l_marketCodeComparator = new WEB3MarginMarketCodeComparator(l_strAscDesc);
        l_lstComparator.add(l_marketCodeComparator);

        //�Q�|�S�j�@@�M�p���敪Comparator�i�h�����h�j�𐶐����AArrayList�ɒǉ�
        WEB3MarginContractTypeComparator l_contractTypeComparator = new WEB3MarginContractTypeComparator(l_strAscDesc);
        l_lstComparator.add(l_contractTypeComparator);

        //�Q�|�T�j�@@�M�p�ٍϋ敪Comparator�i�h�����h�j�𐶐����AArrayList�ɒǉ�
        WEB3MarginRepaymentTypeComparator l_reparmentTypeComparator = new WEB3MarginRepaymentTypeComparator(l_strAscDesc);
        l_lstComparator.add(l_reparmentTypeComparator);
        
		WEB3MarginRepaymentNumComparator l_reparmentNumComparator = new WEB3MarginRepaymentNumComparator(l_strAscDesc);
		l_lstComparator.add(l_reparmentNumComparator);

        //�Q�|�U�j�@@�M�p���Ϗ�ԋ敪Comparator�i�h�����h�j�𐶐����AArrayList�ɒǉ�
        WEB3MarginCloseStatusTypeComparator l_statusTypeComparator = new WEB3MarginCloseStatusTypeComparator(l_strAscDesc);
        l_lstComparator.add(l_statusTypeComparator);

        //�Q�|�V�j�@@�M�p����Comparator�i�h�����h�j�𐶐����AArrayList�ɒǉ�
        WEB3MarginOpenDateComparator l_openDateComparator = new WEB3MarginOpenDateComparator(l_strAscDesc);
        l_lstComparator.add(l_openDateComparator);

        //�R�j�@@ArrayList.toArray( )�ɂ�Comparator�̔z����쐬
        Comparator[] l_comparator = new Comparator[l_lstComparator.size()];
        l_lstComparator.toArray(l_comparator);

        // �S�j�@@Comparator�̔z�񏇂Ɉ���.���������\�[�g
        WEB3ArraysUtility.sort(l_contractInfoList, l_comparator);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (sort���ψꗗ)�B<BR>
     * <BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Č��ψꗗ�s�̔z��̃\�[�g���s���B<BR>
     * <BR>
     * �P�j�@@ArrayList���쐬<BR>
     * <BR>
     * �Q�j�@@����.�\�[�g�L�[�̔z�񐔕�Loop����<BR>
     * �@@�Q�|�P�j�@@����.�\�[�g�L�[.�L�[���ڂ��擾<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@����.�\�[�g�L�[.����/�~�����擾<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�L�[���ڂɂ�镪�򏈗�<BR>
     * �@@�@@�E�L�[���ڂ������R�[�h�ł������ꍇ�A�M�p�����R�[�hComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ��s��R�[�h�ł������ꍇ�A�M�p�s��R�[�hComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ������敪�ł������ꍇ�A�M�p�����敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * 
     * �@@�@@�E�L�[���ڂ����敪�ł������ꍇ�A�M�p���敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ��ٍϋ敪�ł������ꍇ�A�M�p�ٍϋ敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ��ٍϊ����l�ł������ꍇ�A�M�p�ٍϊ����lComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈���=�Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ��]�����v�ł������ꍇ�A�M�p�]�����vComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�Q�|�R�j�ɂč쐬����Comparator��ArrayList�ɒǉ�<BR>
     * <BR>
     * �R�j�@@ArrayList����Comparator�̔z����쐬<BR>
     * <BR>
     * �S�j�@@Comparator�̔z�񏇂Ɉ���.���ψꗗ���\�[�g<BR>
     * (web3-common)WEB3ArraysUtility.sort(����.���ψꗗ�A�R�j�ō쐬����<BR>
     * Comparator[])<BR>
     * <BR>
     * @@param l_closeMarginGroupList - (���ψꗗ)<BR>
     * �M�p������ψꗗ�s�̔z��
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * �M�p����\�[�g�L�[�̈ꗗ
     * @@roseuid 40F4B657005B
     */
    protected void sortCloseMarginList(WEB3MarginCloseMarginGroup[] l_closeMarginGroupList, WEB3MarginSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = " sortCloseMarginList()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@ArrayList���쐬
        List l_lstComparators = new ArrayList();

        //�Q�j�@@����.�\�[�g�L�[�̔z�񐔕�Loop����
        for (int i = 0; i < l_sortKey.length; i++)
        {
            //�Q�|�P�j�@@����.�\�[�g�L�[.�L�[���ڂ��擾
            String l_strKeyItem = l_sortKey[i].keyItem;

            //�Q�|�Q�j�@@����.�\�[�g�L�[.����/�~�����擾
            String l_strAscDesc = l_sortKey[i].ascDesc;

            Comparator l_com = null;

            if (WEB3EquityKeyItemDef.PRODUCTCODE.equals(l_strKeyItem))
            {
                //�L�[���ڂ������R�[�h�ł������ꍇ�A�M�p�����R�[�hComparator�𐶐�
                l_com = new WEB3MarginProductCodeComparator(l_strAscDesc);
            }
            else if (WEB3EquityKeyItemDef.TRADEMARKET.equals(l_strKeyItem))
            {
                //�L�[���ڂ��s��R�[�h�ł������ꍇ�A�M�p�s��R�[�hComparator�𐶐�
                l_com = new WEB3MarginMarketCodeComparator(l_strAscDesc);
            }
            else if (WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(l_strKeyItem))
            {
                //�L�[���ڂ������敪�ł������ꍇ�A�M�p�����敪Comparator�𐶐�
                l_com = new WEB3MarginAccountTypeComparator(l_strAscDesc);
            }
            else if (WEB3EquityKeyItemDef.CONTRACT_DIVISION.equals(l_strKeyItem))
            {
                //�L�[���ڂ����敪�ł������ꍇ�A�M�p���敪Comparator�𐶐�
                l_com = new WEB3MarginContractTypeComparator(l_strAscDesc);
            }
            else if (WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(l_strKeyItem))
            {
                //�L�[���ڂ��ٍϋ敪�ł������ꍇ�A�M�p�ٍϋ敪Comparator�𐶐�
                l_com = new WEB3MarginRepaymentTypeComparator(l_strAscDesc);
            }
            else if (WEB3EquityKeyItemDef.REPAYMENTNUM.equals(l_strKeyItem))
            {
                //�L�[���ڂ��ٍϊ����l�ł������ꍇ�A�M�p�ٍϊ����lComparator�𐶐�
                l_com = new WEB3MarginRepaymentNumComparator(l_strAscDesc);
            }
            else if (WEB3EquityKeyItemDef.INCOME.equals(l_strKeyItem))
            {
                //�L�[���ڂ��]�����v�ł������ꍇ�A�M�p�]�����vComparator�𐶐�
                l_com = new WEB3MarginAppraisalProfitOrLossComparator(l_strAscDesc);
            }
            //�Q�|�R�j�ɂč쐬����Comparator��ArrayList�ɒǉ�
            if (l_com != null)
            {
                l_lstComparators.add(l_com);
            }
        }
        //�R�j�@@ArrayList����Comparator�̔z����쐬
        Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
        l_lstComparators.toArray(l_comparators);

        //�S�j�@@Comparator�̔z�񏇂Ɉ���.���ψꗗ���\�[�g
        //(web3-common)WEB3ArraysUtility.sort(����.���ψꗗ�A�R�j�ō쐬����
        WEB3ArraysUtility.sort(l_closeMarginGroupList, l_comparators);

        log.exiting(STR_METHOD_NAME);
    }
}
@
