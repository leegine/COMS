head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������Ɖ�T�[�r�XImpl(WEB3MarginExecuteReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/21 �������F(SRA) �V�K�쐬
Revesion History : 2006/07/05 �юu�� (���u) �d�l�ύX���f��942
Revesion History : 2006/11/27 �����F(���u) �i���f���jNo.998 No.1084
Revesion History : 2007/06/05 �����q (���u) �d�l�ύX�E���f��1164
Revesion History : 2007/10/16 ����(���u) �d�l�ύX���f��1198�A�d�l�ύX���f��1199
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSwapOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityFinTransactionManager;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.equity.define.WEB3EquityWlimitOrderPriceDivDef;
import webbroker3.equity.message.WEB3MarginChangeCancelHistoryGroup;
import webbroker3.equity.message.WEB3MarginCloseMarginContractGroup;
import webbroker3.equity.message.WEB3MarginCloseMarginContractListRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginContractListResponse;
import webbroker3.equity.message.WEB3MarginCommissionInfoUnit;
import webbroker3.equity.message.WEB3MarginExecuteDetailsRequest;
import webbroker3.equity.message.WEB3MarginExecuteDetailsResponse;
import webbroker3.equity.message.WEB3MarginExecuteGroup;
import webbroker3.equity.message.WEB3MarginExecuteReferenceRequest;
import webbroker3.equity.message.WEB3MarginExecuteReferenceResponse;
import webbroker3.equity.message.WEB3MarginExecuteUnit;
import webbroker3.equity.message.WEB3MarginOrderHistoryRequest;
import webbroker3.equity.message.WEB3MarginOrderHistoryResponse;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.message.WEB3MarginSortKey;
import webbroker3.equity.service.delegate.WEB3MarginExecuteReferenceService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p����������Ɖ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p����������Ɖ�T�[�r�X�����N���X
 * @@author �������F
 * @@version 1.0
 */
public class WEB3MarginExecuteReferenceServiceImpl
    extends WEB3MarginClientRequestService
    implements WEB3MarginExecuteReferenceService
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginExecuteReferenceServiceImpl.class);

    /**
     * �M�p����������Ɖ�T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̏������Ăѕ�����B<BR>
     * <BR>
     * �Esearch�������Ɖ�<BR>
     * �Esearch�������ڍ�<BR>
     * �Esearch���������Ɖ�<BR>
     * �Esearch���ό����ꗗ<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4058302000E7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("���N�G�X�g==null");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3GenResponse l_response = null;

        //search�������Ɖ�
        if (l_request instanceof WEB3MarginExecuteReferenceRequest)
        {
            l_response =
                searchOrderExecuteReference(
                    (WEB3MarginExecuteReferenceRequest)l_request);
        }

        //search�������ڍ�
        else if (l_request instanceof WEB3MarginExecuteDetailsRequest)
        {
            l_response =
                searchOrderExecuteDetails(
                    (WEB3MarginExecuteDetailsRequest)l_request);
        }

        //search���������Ɖ�        
        else if (l_request instanceof WEB3MarginOrderHistoryRequest)
        {
            l_response =
                searchOrderHistoryReference(
                    (WEB3MarginOrderHistoryRequest)l_request);
        }

        //���ό����ꗗ
        else if (l_request instanceof WEB3MarginCloseMarginContractListRequest)
        {
            l_response =
                searchCloseMarginContractList(
                    (WEB3MarginCloseMarginContractListRequest)l_request);
        }
        else
        {
            log.error(" __Error[���͒l���s���ł�]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (search�������Ɖ�)<BR>
     * �M�p����������Ɖ�������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�M�p����������Ɖ�T�[�r�X)search�������Ɖ�v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     *   �V�[�P���X�}(�u(�M�p����������Ɖ�T�[�r�X)search�������Ɖ�v) : <BR>   
     *   getProduct(�،���� : Institution, �����R�[�h : �_���r���[::java::lang::String)<BR>   
     *   �擾�ł��Ȃ������ꍇ�́A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00301<BR>
     *   ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *   �V�[�P���X�}(�u(�M�p����������Ɖ�T�[�r�X)search�������Ɖ�v) : <BR>   
     *   (17*)get�������(WEB3EquityProduct, �s��)<BR>   
     *   ���̒i�K�ł͎w�肳�ꂽ�����R�[�h�̖���/������������݂��邩�ǂ����̃`�F�b�N�݂̂����{����B<BR>
     *   �擾�ł��Ȃ������ꍇ�́A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00638<BR>
     *   ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3MarginExecuteReferenceResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40FC85DC01F0
     */
    protected WEB3MarginExecuteReferenceResponse searchOrderExecuteReference(
        WEB3MarginExecuteReferenceRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "searchOrderExecuteReference(WEB3MarginExecuteReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginExecuteReferenceResponse l_response = null;
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        l_request.validate();
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)this.getMainAccount();
        WEB3GentradeBranch l_branch =
            (WEB3GentradeBranch)l_mainAccount.getBranch();
        if (l_branch.isMarginTradeEnforcement(WEB3GentradeRepaymentDivDef.DEFAULT) == false)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00746,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) == false)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //�������Ɖ�̏ꍇ
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(l_request.referenceType))
        {
            //validate������t�\()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //�����R�[�h�w�莞
            if (l_request.productCode != null && l_request.productCode.length() > 0)
            {
                Institution l_institution = l_subAccount.getInstitution();
                EqTypeProduct l_eqtypeProduct;
                try
                {
                    l_eqtypeProduct = l_productManager.getProduct(
                            l_institution,
                            l_request.productCode);
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301, STR_METHOD_NAME,
                        l_nfe.getMessage(), null);
                }
            }
        }
        //��������ꗗ�̏ꍇ
        else if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_request.referenceType))
        {
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CHANGE);
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BusinessLayerException e1)
            {
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                    WEB3OrderAccTransactionDef.CANCEL);
                try
                {
                    WEB3GentradeTradingTimeManagement.validateOrderAccept();
                }
                catch (WEB3BusinessLayerException e2)
                {
                    WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                        WEB3TradingTimeTypeDef.SWAP);
                    WEB3GentradeTradingTimeManagement.validateOrderAccept();
                }

            }

            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
            OrderValidationResult l_validationResult =
                l_orderValidator.validateSubAccountForTrading(l_subAccount);

            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.error("!OrderValidationResult.VALIDATION_OK_RESULT");
                throw new WEB3BusinessLayerException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    l_validationResult
                        .getProcessingResult()
                        .getErrorInfo()
                        .getErrorMessage());
            }

            //�����R�[�h�w�莞
            if (l_request.productCode != null && l_request.productCode.length() > 0)
            {
                Institution l_institution = l_subAccount.getInstitution();
                EqTypeProduct l_eqtypeProduct;
                try
                {
                    l_eqtypeProduct = l_productManager.getProduct(
                            l_institution,
                            l_request.productCode);
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301, STR_METHOD_NAME,
                        l_nfe.getMessage(), null);
                }

                //�s��R�[�h�w�莞
                if (l_request.marketCode != null && l_request.marketCode.length() > 0)
                {
                    Market l_market;
                    try
                    {
                        l_market = l_finObjectManager.getMarket(
                                l_institution.getInstitutionCode(),
                                l_request.marketCode);
                    }
                    catch (NotFoundException l_nfe)
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00003, STR_METHOD_NAME,
                            l_nfe.getMessage(), l_nfe);
                    }
                    try
                    {
                        l_productManager.getTradedProduct(
                            l_eqtypeProduct,
                            l_market);
                    }
                    catch (NotFoundException l_nfe)
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00638, STR_METHOD_NAME,
                            l_nfe.getMessage(), null);
                    }
                }
            }
        }
        
        l_response = (WEB3MarginExecuteReferenceResponse)l_request.createResponse();
        
        WEB3MarginExecuteGroup[] l_group = null;
        //create�������Ɖ�()
        l_group = this.createExecuteReference(l_subAccount, l_request, l_response);
        
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //get�s��ǌx���s��()
        String l_marginAccOpenDiv = null;
        if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv())
            && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv()))
        {
            l_marginAccOpenDiv = WEB3MarginTradingDivDef.MARKET_MARGIN;
        }
        if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv()) 
            && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv()))
        {
            l_marginAccOpenDiv = WEB3MarginTradingDivDef.INSTITUTION_MARGIN;
        }
        if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv())
            && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv()))
        {
            l_marginAccOpenDiv = WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN;
        }
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        String[] l_closeMarket =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_subAccount.getWeb3GenBranch(),
                ProductTypeEnum.EQUITY,
                l_marginAccOpenDiv);
        
        //get�戵�\�s��()
        String l_marginAccOpenDivMarket = null;
        if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv())
           && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv()))
        {
            l_marginAccOpenDivMarket = WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS;
        }
        if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv()) 
            && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv()))
        {
            l_marginAccOpenDivMarket = WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN;
        }
        if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv())
            && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv()))
        {
            l_marginAccOpenDivMarket = WEB3GentradeRepaymentDivDef.DEFAULT;
        }
        String[] l_possibleMarkets =
            WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(
                l_subAccount.getWeb3GenBranch(),
                l_marginAccOpenDivMarket,
                0.0D);
        
        //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����
        TreeMap l_orderBizDateMap = new TreeMap();
        Date l_datBizdate = GtlUtils.getTradingSystem().getBizDate();
        Timestamp l_tsBizdate = new Timestamp(l_datBizdate.getTime());
        
        WEB3GentradeBizDate l_genBizDate =
            new WEB3GentradeBizDate(l_tsBizdate);
        Date l_datBizDate = WEB3DateUtility.toDay(l_genBizDate.roll(-1));
        l_orderBizDateMap.put(l_datBizDate, l_datBizDate);
        
        Date l_datSysdate = WEB3DateUtility.toDay(l_tsBizdate);
        l_orderBizDateMap.put(l_datSysdate, l_datSysdate);
        
        int l_intMarketSize = 0;
        if (l_possibleMarkets != null)
        {
            l_intMarketSize = l_possibleMarkets.length;
        }
        for (int i = 0;i < l_intMarketSize;i++)
        {
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_possibleMarkets[i]);
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            if (!l_orderBizDateMap.containsKey(l_datBizDate))
            {
                l_orderBizDateMap.put(l_datBizDate, l_datBizDate);
            }
        }
        int l_intListSize = l_orderBizDateMap.size();
        Date[] l_orderBizDateList = new Date[l_intListSize];
        Collection l_collection = l_orderBizDateMap.values();
        l_collection.toArray(l_orderBizDateList);
        l_response.orderBizDateList = l_orderBizDateList;
        
        if (l_group == null)
        {
            l_response.productCodeNames = null;
            l_response.marketList = l_possibleMarkets;
            l_response.executeGroups = null;
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";
            l_response.idList = null;
            l_response.messageSuspension = l_closeMarket;
            log.debug("****** ���X�|���X�D�������ꗗ�̗v�f���F[0]");
            log.debug("****** ���X�|���X�DID�ꗗ�̗v�f���F[0]");
        }
        else
        {
            l_response.productCodeNames = null;
            l_response.marketList = l_possibleMarkets;
            l_response.messageSuspension = l_closeMarket;
            log.debug("****** ���X�|���X�D�������ꗗ�̗v�f���F[" + l_response.executeGroups.length + "]");
            log.debug("****** ���X�|���X�DID�ꗗ�̗v�f���F[" + l_response.idList.length + "]");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (search�������ڍ�)<BR>
     * �M�p����������ڍ׏��������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�M�p����������Ɖ�T�[�r�X)search�������ڍׁv�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MarginExecuteDetailsResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40FC86B8029C
     */
    protected WEB3MarginExecuteDetailsResponse searchOrderExecuteDetails(
        WEB3MarginExecuteDetailsRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "searchOrderExecuteDetails(WEB3MarginExecuteDetailsRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginExecuteDetailsResponse l_response = null;
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        l_request.validate();

        long l_lngOrderId = Long.parseLong(l_request.id);
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        if (l_orderUnits.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w�肵������ID�ɊY������f�[�^�����݂��܂���B");
        }
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //get�萔���R�[�X�R�[�h()
        WEB3EquityBizLogicProvider l_bizLogicProvider =
        (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();

        String l_strCommissionCourseDiv = null;
        try
        {
            l_strCommissionCourseDiv = l_bizLogicProvider.getCommissionCourseDiv(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_orderUnitRow.getCommProductCode(),
                l_orderUnitRow.getCommTblNo(),
                l_orderUnitRow.getCommTblSubNo(),
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd"));
        }
        catch (WEB3SystemLayerException l_wse)
        {
            if (l_wse.getErrorInfo() != WEB3ErrorCatalog.SYSTEM_ERROR_80005)
            {
                throw l_wse;
            }
        }
        
        String l_strOrderState = WEB3EquityDataAdapter.getOrderState(l_orderUnit);
        String l_strExecType = WEB3EquityDataAdapter.getExecType(l_orderUnit);
        //get�����󋵋敪()
        String l_transactionStateType =
            WEB3EquityDataAdapter.getTransactionStatusType(l_orderUnit);
        String l_strExcCondTypeSonar =
            l_orderManager.getExecutionConditionTypeSonar(
                l_orderUnitRow.getExecutionConditionType());

        //get���s�����iSONAR�j�j(EqTypeExecutionConditionType)
        //���s�����F�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f.�iW�w�l�j���s����
        String l_strWlimitExecCondType = null;
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_strWlimitExecCondType =
                l_orderManager.getExecutionConditionTypeSonar(
                    l_orderUnitRow.getWLimitExecCondType());
        }

        //get�v�w�l�p�L����ԋ敪(EqTypeOrderUnit)
        //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
        String l_strWlimitEnableStatusDiv =
            WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        // get�v�w�l�p�֑ؑO�����P��(EqTypeOrderUnit)
        //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
        String l_strWlimitBefChgLimitPrice =
            WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        // get�v�w�l�p�֑ؑO���s����(EqTypeOrderUnit)
        //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
        String l_strWLimitBefSwitchExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        //get���v�w�l�p�����P���敪(EqTypeOrderUnit)
        //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
        String l_strOrgWlimitOrderPriceDiv =
            WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);

        //get���v�w�l�p�����P��(EqTypeOrderUnit)
        //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
        String l_strOrgWlimitPrice =
            WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);

        //get���v�w�l�p���s����(EqTypeOrderUnit)
        //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
        String l_strOrgWlimitExecCondType =
            WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //get�x���敪(EqTypeOrderUnit)
        //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
        String l_strDelayDiv =
            WEB3EquityDataAdapter.getDelayDiv(l_orderUnit);

        //is�蓮�����\(EqTypeOrderUnit)
        //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
        boolean l_blnIsManualOrderPossible =
            l_orderManager.isManualOrderPossible(l_orderUnit);

        OrderExecution[] l_executions = l_orderUnit.getExecutions();
        WEB3EquityFinTransactionManager l_finManager =
            (WEB3EquityFinTransactionManager)l_tradingModule.getFinTransactionManager();
        List l_transactionList =
            l_finManager.getTransactions(l_orderUnit);

        //�g�����U�N�V����(�����ڋq���薾��)���Ƃ�Loop����
        int l_intSize = 0;
        if(l_transactionList != null)
        {
            l_intSize = l_transactionList.size();
        }
        //��n���
        double l_dblDeliveryAmt = 0.0D;
        if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()))
        {
            l_dblDeliveryAmt = l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit);
        }
        //�ϑ��萔��
        double l_dblCommissionFee = 0.0D;
        //�ϑ��萔�������
        double l_dblCommissionFeeTax = 0.0D;
        for (int i = 0;i < l_intSize;i++)
        {
            EqtypeFinTransactionRow l_transactionRow =
                (EqtypeFinTransactionRow)l_transactionList.get(i);
            //�ԍϒ����A�������n�����i���V�K�������j�̏ꍇ�A�ȉ��̏��������{����B
            if (!OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                double l_dblNetAmount = l_transactionRow.getNetAmount();
                l_dblDeliveryAmt += l_dblNetAmount;
            }
            double l_dblCommissionFeeUnit =
                l_transactionRow.getCommissionFee();
            l_dblCommissionFee += l_dblCommissionFeeUnit;

            double l_dblCommissionFeeTaxUnit =
                l_transactionRow.getCommissionFeeTax();
            l_dblCommissionFeeTax += l_dblCommissionFeeTaxUnit;
        }
        
        l_response =
            (WEB3MarginExecuteDetailsResponse)l_request.createResponse();
        
        //�v���p�e�B�Z�b�g
        //<�����n���>
        l_response.orderActionId =
            Long.toString(l_orderUnitRow.getOrderId());
            
        WEB3EquityProduct l_product =
            (WEB3EquityProduct)l_orderUnit.getProduct();
        l_response.productCode = l_product.getProductCode();
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        
        Market l_market;
        try
        {
            l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003, STR_METHOD_NAME,
                l_nfe.getMessage(), l_nfe);
        }
        l_response.marketCode = l_market.getMarketCode();

        TaxTypeEnum l_taxType = l_orderUnit.getTaxType();
        if (TaxTypeEnum.NORMAL.equals(l_taxType))
        {
            l_response.taxType = WEB3TaxTypeDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_taxType) ||
            TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
        {
            l_response.taxType = WEB3TaxTypeDef.SPECIAL;
        }
        
        l_response.tradingType =
            Integer.toString(l_orderUnitRow.getOrderType().intValue());
            
        WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
        l_repaymentUnit.repaymentDiv = l_orderUnitRow.getRepaymentType();
        l_repaymentUnit.repaymentTimeLimit = Integer.toString(l_orderUnitRow.getRepaymentNum());
        l_response.repayment = l_repaymentUnit;
        
        l_response.priceCondType = l_orderUnitRow.getPriceConditionType();
        l_response.execCondType = l_strExcCondTypeSonar;
        
        l_response.orderCondType = l_orderUnitRow.getOrderConditionType();
        String l_strOrderConditionType = l_orderUnitRow.getOrderConditionType();
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            l_response.stopOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            l_response.stopOrderCondOperator =
                l_orderUnitRow.getOrderCondOperator();
        }
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            l_response.wlimitOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            l_response.wlimitOrderCondOperator =
                l_orderUnitRow.getOrderCondOperator();
           if (l_orderUnitRow.getWLimitPrice() == 0)
           {
               l_response.wLimitOrderPriceDiv =
                   WEB3EquityWlimitOrderPriceDivDef
                       .WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
           }
           else
           {
               l_response.wLimitOrderPriceDiv =
                   WEB3EquityWlimitOrderPriceDivDef
                       .WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;    
               l_response.wLimitPrice =
                   WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
           }

           //�v�w�l�p���s����
           l_response.wlimitExecCondType = l_strWlimitExecCondType;
        }

        //�v�w�l�p�L����ԋ敪
        l_response.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;

        //�v�w�l�p�֑ؑO�����P��
        l_response.wlimitBefChgLimitPrice = l_strWlimitBefChgLimitPrice;

        //�v�w�l�p�֑ؑO���s����
        l_response.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

        //�����������敪
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();

        //�����������P��
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice = null;
        }
        else
        {
            l_response.orgOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }

        //�������������Z�q
        l_response.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        //���v�w�l�p�����P���敪
        l_response.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;

        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
        {
            //���v�w�l�p�����P��
            l_response.orgWlimitPrice = l_strOrgWlimitPrice;
        }

        //���v�w�l�p���s����
        l_response.orgWlimitExecCondType = l_strOrgWlimitExecCondType;

        l_response.orderQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getQuantity());
        if (l_orderUnit.isMarketOrder())
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            l_response.limitPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
        }
        l_response.estimatedPrice =
            WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());

        if (l_orderManager.isCarriedOrderUnit(l_orderUnit))
        {
            l_response.expirationDate = l_orderUnitRow.getExpirationDate();
        }

        l_response.orderDate = l_orderUnitRow.getReceivedDateTime();
        l_response.orderState = l_strOrderState;
        if (WEB3OrderStatusDef.NOT_TRANSFERED.equals(l_strOrderState))
        {
            l_response.transferErrCode =
                l_orderUnitRow.getErrorReasonCode();
        }
        l_response.orderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
        l_response.execType = l_strExecType;
        
        //<���n���>
        if (l_orderUnit.isUnexecuted() == false)
        {
            l_response.deliveryDate = l_orderUnit.getDeliveryDate();
            l_response.execQuantity =
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedQuantity());
            l_response.execPrice =
                WEB3StringTypeUtility.formatNumber(
                    Math.round(
                        l_orderUnitRow.getExecutedAmount()
                            / l_orderUnit.getExecutedQuantity()));   
            l_response.execTotalPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_orderUnitRow.getExecutedAmount());
            l_response.deliveryPrice = WEB3StringTypeUtility.formatNumber(l_dblDeliveryAmt);

            WEB3MarginCommissionInfoUnit l_commissionInfoUnit =
                new WEB3MarginCommissionInfoUnit();
            l_commissionInfoUnit.commissionCourse = l_strCommissionCourseDiv;
            l_commissionInfoUnit.commission =
                WEB3StringTypeUtility.formatNumber(l_dblCommissionFee);
            l_commissionInfoUnit.commissionConsumptionTax =
                WEB3StringTypeUtility.formatNumber(l_dblCommissionFeeTax);
            l_response.commissionInfo = l_commissionInfoUnit;

            int l_intExeLength = 0;
            if (l_executions != null)
            {
                l_intExeLength = l_executions.length;
            }
            l_response.executeUnits = new WEB3MarginExecuteUnit[l_intExeLength];
            for (int i = 0;i < l_intExeLength;i++)
            {
                WEB3MarginExecuteUnit l_executeUnit = new WEB3MarginExecuteUnit();
                l_executeUnit.executionTimestamp =
                    l_executions[i].getExecutionTimestamp();
                l_executeUnit.execQuantity =
                    WEB3StringTypeUtility.formatNumber(
                        l_executions[i].getExecutionQuantity());
                l_executeUnit.execPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_executions[i].getExecutionPrice());
                l_response.executeUnits[i] = l_executeUnit;
            }
        }
        WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        l_response.branchCode = l_account.getBranch().getBranchCode();
        l_response.accountCode = l_account.getDisplayAccountCode();
        l_response.accountName = l_account.getDisplayAccountName();
        l_response.changeCancelDiv = l_orderUnitRow.getModifyCancelType();
        l_response.orderRootDiv = l_orderUnitRow.getOrderRootDiv();
        l_response.transactionStateType = l_transactionStateType;
        //�x���敪
        l_response.delayDiv = l_strDelayDiv;
        //�蓮�����\�t���O
        l_response.manualFlag = l_blnIsManualOrderPossible;
        //�������ϗ��R
        l_response.forcedSettleReason = l_orderUnitRow.getForcedSettleReasonType();
        //���������敪
        l_response.forcedLapseDiv = l_orderUnitRow.getForcedExpireType();

        return l_response;
    }

    /**
     * (search���������Ɖ�)<BR>
     * �M�p������������Ɖ�������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�M�p����������Ɖ�T�[�r�X)search���������Ɖ�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MarginOrderHistoryResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40FC86DC0387
     */
    protected WEB3MarginOrderHistoryResponse searchOrderHistoryReference(
        WEB3MarginOrderHistoryRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "searchOrderHistoryReference(WEB3MarginOrderHistoryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginOrderHistoryResponse l_response = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        l_request.validate();
        
        long l_lngOrderId = Long.parseLong(l_request.id);
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        if (l_orderUnits.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w�肵������ID�ɊY������f�[�^�����݂��܂���B");
        }
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqTypeOrderUnit[] l_firstOrderUnits = null;
        
        //�o����܂Œ����̏ꍇ�̂�
        if (l_orderManager.isCarriedOrderUnit(l_orderUnit) == true)
        {
            l_firstOrderUnits =
                this.getCarriedOrderUnitFromFirstToLast(l_orderUnit);
        }
        // ���o����܂Œ����łȂ��ꍇ�͎擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f�̂�
        else
        {
            l_firstOrderUnits = new EqTypeOrderUnit[1];
            l_firstOrderUnits[0] = l_orderUnit;
        }
        
        ArrayList l_lisOrderHistory = new ArrayList();
        for (int i = 0; i < l_firstOrderUnits.length;i++)
        {
            EqTypeOrderUnit l_orderUnitNew = l_firstOrderUnits[i];
            EqtypeOrderUnitRow l_orderUnitRowNew =
                (EqtypeOrderUnitRow)l_orderUnitNew.getDataSourceObject();
            //1.5.1. getOrderActions()
            OrderAction[] l_orderActions = l_orderUnitNew.getOrderActions();               
            int l_intOrderActionLen = 0;
            if (l_orderActions != null)
            {
                l_intOrderActionLen = l_orderActions.length;
            }
            //��������v�f���Ƃ�Loop����
            for (int j = 0; j < l_intOrderActionLen; j++)
            {
                WEB3MarginChangeCancelHistoryGroup l_historyGroup =
                    new WEB3MarginChangeCancelHistoryGroup();
                EqTypeOrderAction l_orderActionUnit =
                    (EqTypeOrderAction)l_orderActions[j];
                EqtypeOrderActionRow l_orderActionRow =
                    (EqtypeOrderActionRow)l_orderActionUnit.getDataSourceObject();
                    
                String l_strExecCondType =
                    l_orderManager.getExecutionConditionTypeSonar(
                    l_orderActionUnit.getExecutionConditionType());
                String l_orderSpecType =
					WEB3EquityDataAdapter.getOrderType(l_orderActionUnit, l_orderUnitNew);

                // get���s�����iSONAR�j(EqTypeExecutionConditionType)
                String l_strWlimitExecCondType = null;
                if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderActionRow.getOrderConditionType()))
                {
                    l_strWlimitExecCondType =
                        l_orderManager.getExecutionConditionTypeSonar(
                            l_orderActionRow.getWLimitExecCondType());
                }

                // get�v�w�l�p�L����ԋ敪(EqTypeOrderAction)
                String l_strWlimitEnableStatusDiv =
                    WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderActionUnit);

                // get�v�w�l�p�֑ؑO�����P��(EqTypeOrderAction)
                String l_strWlimitBefChgLimitPrice =
                    WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderActionUnit);

                //get�v�w�l�p�֑ؑO���s����(EqTypeOrderAction)
                String l_strWLimitBefSwitchExecCondType =
                    WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderActionUnit);

                //get���v�w�l�p�����P���敪(EqTypeOrderAction)
                String l_strOrgWlimitOrderPriceDiv =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderActionUnit);

                //get���v�w�l�p�����P��(EqTypeOrderAction)
                String l_strOrgWlimitPrice =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderActionUnit);

                //get���v�w�l�p���s����(EqTypeOrderAction)
                String l_strOrgWlimitExecCondType =
                    WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderActionUnit);

                String l_acceptedResult =
					WEB3EquityDataAdapter.getAcceptedResultDiv(l_orderActionUnit);
                l_historyGroup.orderActionId =
                    Long.toString(l_orderActionUnit.getOrderActionId());
                l_historyGroup.orderDate =
                    l_orderActionRow.getCreatedTimestamp();
                l_historyGroup.orderType = l_orderSpecType;
                l_historyGroup.acceptedResultDiv = l_acceptedResult;
                //��莞�ɍ쐬���ꂽ�����łȂ��ꍇ(��������.isUnexecuted() == true)�̂݃Z�b�g
                if (l_orderActionUnit.isUnexecuted() == true)
                {
                    l_historyGroup.priceCondType = l_orderActionRow.getPriceConditionType();
                    l_historyGroup.execCondType = l_strExecCondType;
                    l_historyGroup.orderCondType =
                        l_orderActionRow.getOrderConditionType();
                    String l_strOrderConditionType = l_orderActionRow.getOrderConditionType();
                    if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
                    {
                        l_historyGroup.stopOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderActionRow.getStopOrderPrice());
                        l_historyGroup.stopOrderCondOperator =
                            l_orderActionRow.getOrderCondOperator();
                    }
                    else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
                    {
                        l_historyGroup.wlimitOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderActionRow.getStopOrderPrice());
                        l_historyGroup.wlimitOrderCondOperator =
                            l_orderActionRow.getOrderCondOperator();
                        if (l_orderActionRow.getWLimitPrice() == 0)
                        {
                            l_historyGroup.wLimitOrderPriceDiv =
                                WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
                        }
                        else
                        {
                            l_historyGroup.wLimitOrderPriceDiv =
                                WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;
                            l_historyGroup.wLimitPrice =
                                WEB3StringTypeUtility.formatNumber(l_orderActionRow.getWLimitPrice());
                        }
                        //�v�w�l�p���s����
                        l_historyGroup.wlimitExecCondType = l_strWlimitExecCondType;
                    }

                    //�v�w�l�p�L����ԋ敪
                    l_historyGroup.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;

                    //�v�w�l�p�֑ؑO�����P��
                    l_historyGroup.wlimitBefChgLimitPrice = l_strWlimitBefChgLimitPrice;

                    //�v�w�l�p�֑ؑO���s����
                    l_historyGroup.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

                    //�����������敪
                    l_historyGroup.orgOrderCondType = l_orderActionRow.getOrgOrderConditionType();

                    if (!l_orderActionRow.getOrgStopOrderPriceIsNull())
                    {
                        //�����������P��
                        l_historyGroup.orgOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderActionRow.getOrgStopOrderPrice());
                    }

                    //�������������Z�q
                    l_historyGroup.orgOrderCondOperator = l_orderActionRow.getOrgOrderCondOperator();

                    //���v�w�l�p�����P���敪
                    l_historyGroup.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;

                    if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
                    {
                        //���v�w�l�p�����P��
                        l_historyGroup.orgWlimitPrice = l_strOrgWlimitPrice;
                    }

                    //���v�w�l�p���s����
                    l_historyGroup.orgWlimitExecCondType = l_strOrgWlimitExecCondType;

                    l_historyGroup.orderQuantity =
                        WEB3StringTypeUtility.formatNumber(l_orderActionRow.getQuantity());
                    if (l_orderActionUnit.isMarketOrder() == true)
                    {
                        l_historyGroup.orderPriceDiv =
                            WEB3OrderPriceDivDef.MARKET_PRICE;
                    }
                    else
                    {
                        l_historyGroup.orderPriceDiv =
                            WEB3OrderPriceDivDef.LIMIT_PRICE;
                        l_historyGroup.limitPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderActionRow.getPrice());
                    }
                    if (l_orderManager.isCarriedOrderUnit(l_orderUnit) == true)
                    {
                        l_historyGroup.expirationDate =
                            WEB3DateUtility.toDay(l_orderActionRow.getExpirationDate());
                    }
                }
                else
                {
                    l_historyGroup.execQuantity =
                        WEB3StringTypeUtility.formatNumber(
                            l_orderActionUnit.getExecutionQuantity());
                    l_historyGroup.execPrice =
                        WEB3StringTypeUtility.formatNumber(
                            l_orderActionUnit.getExecutionPrice());
                }
                l_lisOrderHistory.add(l_historyGroup);
            }
        }
        
        WEB3MarginChangeCancelHistoryGroup[] l_historyGroups =
            new WEB3MarginChangeCancelHistoryGroup[l_lisOrderHistory.size()];
        l_lisOrderHistory.toArray(l_historyGroups);
        
        l_response =
            (WEB3MarginOrderHistoryResponse)l_request.createResponse();
        l_response.changeCancelHistoryGroups = l_historyGroups;

        return l_response;
    }

    /**
     * (search���ό����ꗗ)<BR>
     * �M�p������ό����ꗗ���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�M�p����������Ɖ�T�[�r�X)search���ό����ꗗ�v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     *  �V�[�P���X�}(�u(�M�p����������Ɖ�T�[�r�X)search���ό����ꗗ�v) : <BR>   
     *  (4*) (*)�����J�e�S���`�F�b�N<BR>   
     *  (*)�����J�e�S���`�F�b�N<BR>
     *  �ԍϒ����A�������n���� ,(�����P��.�����J�e�S�� != �h�ԍϒ����h�@@�܂��́@@�h�������n�����h)<BR>
     *  �ȊO�́A�u�����ΏۊO�v�̗�O���X���[����B<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00653<BR>
     *  ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(�M�p����������Ɖ�T�[�r�X)search���ό����ꗗ�v) : <BR>   
     * (5*)getContractsToClose( )<BR>   
     * �����P��ID�ɊY�����錚���ԍώw����I�u�W�F�N�g���擾����B<BR>
     * �擾�ł��Ȃ������ꍇ�́A�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B<BR>
     * ���擾���������P�ʂ̔z���0�Ԗڂ̗v�f���ȉ��̏�����,�@@���ꂼ��̌^�ɃL���X�g���ăR�[������B<BR>
     * [�����P��.�����J�e�S�� == �h�ԍϒ����h�̏ꍇ�@@�@@EqTypeContractSettleOrderUnit�ɃL���X�g�B<BR>
     * [�����P��.�����J�e�S�� == �h�������n�����h�̏ꍇ]�@@�@@EqTypeContractSwapOrderUnit�ɃL���X�g�B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:   BUSINESS_ERROR_00659<BR>
     * ==========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MarginCloseMarginContractListResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40FC870C02EA
     */
    protected WEB3MarginCloseMarginContractListResponse searchCloseMarginContractList(
        WEB3MarginCloseMarginContractListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "searchCloseMarginContractList(WEB3MarginCloseMarginContractListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginCloseMarginContractListResponse l_response = null;
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        try
        {
            l_request.validate();
        
            long l_lngOrderId = Long.parseLong(l_request.id);
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
            if (l_orderUnits.length == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w�肵������ID�ɊY������f�[�^�����݂��܂���B");
            }
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
            OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
            if (!OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg) &&
                !OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            EqTypeClosingContractSpec[] l_closingContractSpecs = null;
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
            {
                EqTypeContractSettleOrderUnit l_closingOrderUnit =
                    (EqTypeContractSettleOrderUnit)l_orderUnit;
                l_closingContractSpecs =
                    l_closingOrderUnit.getContractsToClose();
            }
            else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
            {
                EqTypeContractSwapOrderUnit l_swapOrderUnit =
                    (EqTypeContractSwapOrderUnit)l_orderUnit;
                l_closingContractSpecs =
                    l_swapOrderUnit.getContractsToClose();
            }
            if (l_closingContractSpecs.length == 0)
            {
                log.error("�Y���f�[�^�Ȃ�");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00659,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
        
            ArrayList l_lisClosingContract = new ArrayList();

            int l_intClosingContractSpecsLen = l_closingContractSpecs.length;
            int l_intCnt = 0;
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            for (int i = 0;i < l_intClosingContractSpecsLen; i++)
            {
                EqTypeClosingContractSpec l_closeContractSpec =
                    l_closingContractSpecs[i];
                double l_dblQuantity = l_closeContractSpec.getQuantity();
                if (l_dblQuantity == 0.0D)
                {
                    continue;
                }
                long l_lngOrderUnitId = l_closeContractSpec.getOrderUnitId();
                long l_lngContractId = l_closeContractSpec.getContractId();
                WEB3EquityContract l_contract =
                    (WEB3EquityContract) l_positionManager.getContract(l_lngContractId);
                Date l_contractOpenDate = l_contract.getOpenDate();
                double l_dblContractPrice = l_contract.getContractPrice();
                double l_dblSetupFee = l_contract.getSetupFee(l_dblQuantity, l_lngOrderUnitId);
                double l_dblSetupFeeTax = l_contract.getSetupFeeTax(l_dblQuantity, l_lngOrderUnitId);
                double l_dblInterestFee = l_contract.getInterestFee(l_dblQuantity, l_lngOrderUnitId);
                double l_dblPayInterestFee = l_contract.getPayInterestFee(l_dblQuantity, l_lngOrderUnitId);
                double l_dblLoanEquityFee = l_contract.getLoanEquityFee(l_dblQuantity, l_lngOrderUnitId);
                double l_dblNameTransferFee = l_contract.getNameTransferFee(l_dblQuantity, l_lngOrderUnitId);
                double l_dblNameTransferFeeTax = l_contract.getNameTransferFeeTax(l_dblQuantity, l_lngOrderUnitId);
                double l_dblManagementFee = l_contract.getManagementFee(l_dblQuantity, l_lngOrderUnitId);
                double l_dblManagementFeeTax = l_contract.getManagementFeeTax(l_dblQuantity, l_lngOrderUnitId);
                double l_dblOther = l_contract.getOther(l_dblQuantity, l_lngOrderUnitId);
                double l_dblContractQuantity =
                    l_closeContractSpec.getQuantity();
                double l_dblContractExecutedQuantity =
                    l_closeContractSpec.getExecutedQuantity();
                
                List l_finTransactionList =
                    l_positionManager.getFinTransactionListByOrderUnitPlusContract(
                        l_lngOrderUnitId,
                        l_lngContractId);
                //�擾�����g�����U�N�V����(�����ڋq���薾��)���Ƃ�Loop����
                int l_intSize = l_finTransactionList.size();
                double l_dblNetAmount = 0.0D;
                double l_dblExecutedAmount = 0.0D;
                for (int j = 0;j < l_intSize;j++)
                {
                    EqtypeFinTransactionRow l_transactionRow =
                        (EqtypeFinTransactionRow)l_finTransactionList.get(j);
                    l_dblNetAmount += l_transactionRow.getNetAmount();
                    l_dblExecutedAmount += l_transactionRow.getPrice() * l_transactionRow.getQuantity();
                }
            
                WEB3MarginCloseMarginContractGroup l_group =
                    new WEB3MarginCloseMarginContractGroup();

                //�v���p�e�B�Z�b�g
                l_group.openDate = WEB3DateUtility.toDay(l_contractOpenDate);
                l_group.contractPrice = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
                l_group.orderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_dblContractQuantity);
                if (l_orderUnit.isMarketOrder())
                {
                    l_group.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {
                    l_group.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_group.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
                }

                if (l_closeContractSpec.isUnexecuted() == false)
                {
                    l_group.execQuantity = WEB3StringTypeUtility.formatNumber(l_dblContractExecutedQuantity);    
                    l_group.execPrice =
                        WEB3StringTypeUtility.formatNumber(
                            Math.round(l_dblExecutedAmount / l_dblContractExecutedQuantity));
                }

                l_group.contractCommission =
                    WEB3StringTypeUtility.formatNumber(l_dblSetupFee + l_dblSetupFeeTax);
                l_group.interestFee =
                    WEB3StringTypeUtility.formatNumber(l_dblInterestFee);
                l_group.payInterestFee =
                    WEB3StringTypeUtility.formatNumber(l_dblPayInterestFee);
                l_group.loanEquityFee =
                    WEB3StringTypeUtility.formatNumber(l_dblLoanEquityFee);
                l_group.setupFee =
                    WEB3StringTypeUtility.formatNumber(l_dblNameTransferFee + l_dblNameTransferFeeTax);
                l_group.managementFee =
                    WEB3StringTypeUtility.formatNumber(l_dblManagementFee + l_dblManagementFeeTax);
                l_group.otherwise =
                    WEB3StringTypeUtility.formatNumber(l_dblOther);
                if (l_closeContractSpec.isUnexecuted() == false)
                {
                    l_group.settleProfitLoss =
                        WEB3StringTypeUtility.formatNumber(l_dblNetAmount);
                }
                if (l_orderUnitRow.getClosingOrderType() != null)
                {
                    l_group.settlePriority =
                        WEB3StringTypeUtility.formatNumber(++l_intCnt);
                }
                l_lisClosingContract.add(l_group);
            }
        
            WEB3MarginCloseMarginContractGroup[] l_closeMaginGroup =
                new WEB3MarginCloseMarginContractGroup[l_lisClosingContract.size()];
            l_lisClosingContract.toArray(l_closeMaginGroup);
        
            l_response =
                (WEB3MarginCloseMarginContractListResponse)l_request.createResponse();

            //�v���p�e�B�Z�b�g
            WEB3EquityContract l_contract1 =
                (WEB3EquityContract)l_positionManager.getContract(
                    l_closingContractSpecs[0].getContractId());
            EqtypeContractRow l_eqtypeContractRow =
                (EqtypeContractRow)l_contract1.getDataSourceObject();
            WEB3EquityProduct l_product =
                (WEB3EquityProduct)l_contract1.getProduct();
            EqtypeProductRow l_productRow =
                (EqtypeProductRow)l_product.getDataSourceObject();
            l_response.productCode = l_productRow.getProductCode();
            l_response.productName = l_productRow.getStandardName();

            Market l_market;
            try
            {
                l_market =
                    l_finObjectManager.getMarket(
                        l_eqtypeContractRow.getMarketId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00003, STR_METHOD_NAME,
                    l_nfe.getMessage(), l_nfe);
            }
            l_response.marketCode = l_market.getMarketCode();
            if (TaxTypeEnum.NORMAL.equals(l_eqtypeContractRow.getTaxType()))
            {
                l_response.taxType = WEB3TaxTypeDef.NORMAL;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_eqtypeContractRow.getTaxType()) ||
                      TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_eqtypeContractRow.getTaxType()))
            {
                l_response.taxType = WEB3TaxTypeDef.SPECIAL;
            }

            l_response.contractType =
                Integer.toString(l_eqtypeContractRow.getContractType().intValue());
            WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
            l_repaymentUnit.repaymentDiv =
                l_eqtypeContractRow.getRepaymentType();
            l_repaymentUnit.repaymentTimeLimit =
                Integer.toString(l_eqtypeContractRow.getRepaymentNum());
            l_response.repayment = l_repaymentUnit;
            l_response.closingOrder = l_orderUnitRow.getClosingOrderType();
            l_response.closeMarginContractGroups = l_closeMaginGroup;
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�������Ɖ�)<BR>
     * �������Ɖ��ʁ^��������ꗗ��ʂɕ\������<BR>
     * �������Ɖ���P�ʂ̈ꗗ���쐬���A���X�|���X�ɐݒ肷��B<BR>
     * <BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�null��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����������Ɖ�T�[�r�X�jcreate�������Ɖ�v�Q�ƁB<BR>
     * @@param L_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p����������Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@param l_response - (���X�|���X�f�[�^)<BR>
     * �M�p����������Ɖ�X�|���X�I�u�W�F�N�g<BR>
     * @@return WEB3MarginExecuteGroup[]
     * @@roseuid 40FCA49A0193
     */
    protected WEB3MarginExecuteGroup[] createExecuteReference(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginExecuteReferenceRequest l_request,
        WEB3MarginExecuteReferenceResponse l_response)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createExecuteReference(WEB3GentradeSubAccount, WEB3MarginExecuteReferenceRequest, "
            + "WEB3MarginExecuteReferenceResponse)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginExecuteGroup[] l_group = null;
        Long[] l_orderUnitIdList = null;
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        
        try
        {
            String l_strSearchCondCharacter =
                createSearchCondCharacter(
                    l_request.productCode,
                    l_request.marketCode,
                    l_request.orderBizDate,
                    l_request.orderCondType);
            
            String[] l_searchCondDataContains =
                createSearchCondDataContainers(
                    l_request.productCode,
                    l_request.marketCode,
                    l_request.orderBizDate,
                    l_request.orderCondType);
            
            String l_strSortCond = createSortCond(l_request.sortKeys);
            
            List l_orderUnitsList =
                l_orderManager.getOrderUnits(
                    l_subAccount,
                    ProductTypeEnum.EQUITY,
                    l_strSearchCondCharacter,
                    l_searchCondDataContains,
                    l_strSortCond);
            
            //���������ɖ���Ԃ��w�肳�ꂽ�ꍇ
            List l_orderUnitTmpList;
            if (l_request.execType != null)
            {
                l_orderUnitTmpList = new ArrayList();
                int l_intSize = 0;
                if (l_orderUnitsList != null)
                {
                    l_intSize = l_orderUnitsList.size();
                }
                for (int i = 0;i < l_intSize;i++)
                {
                    EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnitsList.get(i);
                    boolean l_blnIsDesignateExecutedStatus =
                        this.isDesignateExecutedStatus(l_request.execType, l_orderUnit);
                    if (l_blnIsDesignateExecutedStatus)
                    {
                        l_orderUnitTmpList.add(l_orderUnit);
                    }
                }
            }
            else
            {
                l_orderUnitTmpList = l_orderUnitsList;
            }
            if (l_orderUnitTmpList == null || l_orderUnitTmpList.size() == 0)
            {
                return null;
            }
            
            //remove�J�z�������P��()
            EqTypeOrderUnit[] l_orderUnits = null;
            int l_intSize = l_orderUnitTmpList.size();
            l_orderUnits = new EqTypeOrderUnit[l_intSize];
            for (int i = 0;i < l_intSize;i++)
            {
                l_orderUnits[i] = (EqTypeOrderUnit)l_orderUnitTmpList.get(i);
            }
			EqTypeOrderUnit[] l_leftOrderUnits = l_orderManager.removeCarryOverOriginalOrderUnit(l_orderUnits);
            
            List l_orderUnitLastList = new ArrayList();
            List l_selectedOrderUnitList = new ArrayList();
            //�\���Ώے����P�ʂ̗v�f������Loop����
            for (int i = 0;i < l_leftOrderUnits.length;i++)
            {
                EqTypeOrderUnit l_orderUnit = l_leftOrderUnits[i];
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(
                        l_orderUnitRow.getMarketId());
                WEB3GentradeTradingTimeManagement.resetMarketCode(
                    l_market.getMarketCode());
                WEB3EquityProduct l_product =
                    (WEB3EquityProduct)l_orderUnit.getProduct();
                boolean l_isChangeCancelEnableOrderUnit =
                    this.isChangeCancelEnableOrderUnit(
                        l_subAccount,
                        l_orderUnit,
                        l_product,
                        l_market);
                if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_request.referenceType))
                {
                    if (l_isChangeCancelEnableOrderUnit == false)
                    {
                        continue;
                    }
                }
                Order l_order = l_orderManager.getOrder(l_orderUnit.getOrderId());
                //validate���������\���()
                boolean l_isOrderChangePossibleStatus = true;
                WEB3EquityTradedProduct l_tradedProduct = null;
                try
                {
                    l_tradedProduct = (WEB3EquityTradedProduct) l_orderUnit.getTradedProduct();
                    l_orderManager.validateOrderForChangeability(l_order);
                }
                catch (WEB3BaseException l_wbe)
                {
                    l_isOrderChangePossibleStatus = false;
                }
                catch (RuntimeSystemException l_rse)
                {
                    l_isOrderChangePossibleStatus = false;
                }
                //�����\���ǂ����̃`�F�b�N
                if (l_isOrderChangePossibleStatus)
                {
                    try
                    {
                        //validate��������i�M�p�j()
                        boolean l_blnIsShort = true;
                        OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
                        SideEnum l_side = l_orderUnit.getSide();
                        if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg))
                        {
                            if (SideEnum.BUY.equals(l_side))
                            {
                                l_blnIsShort = false;
                            }
                            else
                            {
                                l_blnIsShort = true;
                            }
                        }
                        else
                        {
                            if (SideEnum.BUY.equals(l_side))
                            {
                                l_blnIsShort = true;
                            }
                            else
                            {
                                l_blnIsShort = false;
                            }
                        }
                        l_orderManager.validateTradedProductForMarginTrading(
                            l_subAccount,
                            l_product,
                            l_market,
                            l_subAccount.getWeb3GenBranch(),
                            l_orderUnitRow.getRepaymentType(),
                            l_orderCateg,
                            l_blnIsShort,
                            false);
                        //validate�C���T�C�_�[()
                        l_orderManager.validateInsider(l_subAccount, l_product);
                        //validate�ڋq�����ʎ����~()
                        l_orderManager.validateAccountProductOrderStop(
                            l_subAccount,
                            l_orderUnitRow.getProductId(),
                            l_orderUnit.getOrderType());
                    }
                    catch (WEB3BaseException l_exp)
                    {
                        l_isOrderChangePossibleStatus = false;
                    }
                }
                //validate��������\���()
                boolean l_isOrderCancelPossibleStatus = true;
                try
                {
                    if (l_tradedProduct != null)
                    {
                        l_orderManager.validateOrderForCancellation(l_order);    
                    }
                    else
                    {
                        l_isOrderCancelPossibleStatus = false;
                    }
                }
                catch (WEB3BaseException l_exp)
                {
                    l_isOrderCancelPossibleStatus = false;
                }
                if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_request.referenceType))
                {
                    if (!l_isOrderChangePossibleStatus &&
                        !l_isOrderCancelPossibleStatus)
                    {
                        continue;
                    }
                }

                WEB3MarginExecuteGroup l_marginExecuteGroup =
                    new WEB3MarginExecuteGroup();
                    
                //�v���p�e�B�Z�b�g
                l_marginExecuteGroup.id = Long.toString(l_orderUnitRow.getOrderId());
                l_marginExecuteGroup.productCode = l_product.getProductCode();
                EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
                l_marginExecuteGroup.productName = l_productRow.getStandardName();
                l_marginExecuteGroup.marketCode = l_market.getMarketCode();

                //�����\�t���O
                if (l_isChangeCancelEnableOrderUnit == false ||
                    l_isOrderChangePossibleStatus == false)
                {
                    l_marginExecuteGroup.changeFlag = false;
                }
                else
                {
                    l_marginExecuteGroup.changeFlag = true;                     
                }
                //����\�t���O
                if (l_isChangeCancelEnableOrderUnit == false || 
                    l_isOrderCancelPossibleStatus == false)
                {
                    l_marginExecuteGroup.cancelFlag = false;
                }
                else
                {
                    l_marginExecuteGroup.cancelFlag = true;
                }

                l_orderUnitLastList.add(l_marginExecuteGroup);
                l_selectedOrderUnitList.add(l_orderUnit);
            }

            if (l_orderUnitLastList.size() == 0)
            {
                return null;
            }
            l_group = new WEB3MarginExecuteGroup[l_orderUnitLastList.size()];
            l_orderUnitLastList.toArray(l_group);
            
            //���X�|���X�̃y�[�W�֘A���ڐݒ�
            //���X�|���X.ID�ꗗ�F�@@���������ɊY�����钍��ID��S�ăZ�b�g
            // �i�v�f���́A�����R�[�h���ɓ������j
            if (l_group != null)
            {
                int l_intOrderSize = l_group.length;
                l_response.idList = new String[l_intOrderSize];
                for (int i = 0;i < l_intOrderSize; i++)
                {
                    l_response.idList[i] = l_group[i].id;
                }
            }
            //���X�|���X.���y�[�W��
            //���X�|���X.�����R�[�h��
            //���X�|���X.�\���y�[�W�ԍ�
            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            int l_intPageSize = Integer.parseInt(l_request.pageSize);
            WEB3PageIndexInfo l_pageIndexInfo =
                new WEB3PageIndexInfo(l_group, l_intPageIndex, l_intPageSize);
            l_response.executeGroups
                = (WEB3MarginExecuteGroup[])l_pageIndexInfo.getArrayReturned(WEB3MarginExecuteGroup.class);
            l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
            l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
            l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());

            WEB3PageIndexInfo l_ordersAtPage =
                new WEB3PageIndexInfo(l_selectedOrderUnitList, l_intPageIndex, l_intPageSize);
            Object[] l_objReturned = l_ordersAtPage.getArrayReturned(EqTypeOrderUnit.class);

            for (int i = 0; i < l_response.executeGroups.length; i++)
            {
                WEB3MarginExecuteGroup l_marginExecuteGroup = l_response.executeGroups[i];
                EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_objReturned[i];
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                Market l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());

                // reset��t���ԋ敪()
                // ��t���ԋ敪�F�����P��.�����J�e�S��=="�������n"�̏ꍇ�́A"�����E���n"
                //              �ȊO�A"�����E�M�p"
                String l_tradingTimeType = null;
                if(OrderCategEnum.SWAP_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
                {
                    l_tradingTimeType = WEB3TradingTimeTypeDef.SWAP;
                }
                else
                {
                    l_tradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                }
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_tradingTimeType);

                //get���s�����iSONAR�j(EqTypeExecutionConditionType)
                //���s�����F�@@�����P��.(W�w�l)���s����
                String l_strWlimitExecCondType = null;
                if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
                {
                    l_strWlimitExecCondType =
                        l_orderManager.getExecutionConditionTypeSonar(
                            l_orderUnitRow.getWLimitExecCondType());
                }

                //get�v�w�l�p�L����ԋ敪(EqTypeOrderUnit)
                String l_strWlimitEnableStatusDiv =
                    WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

                // get�v�w�l�p�֑ؑO�����P��(EqTypeOrderUnit)
                //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
                String l_strWlimitBefChgLimitPrice =
                    WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

                // get�v�w�l�p�֑ؑO���s����(EqTypeOrderUnit)
                //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
                String l_strWLimitBefSwitchExecCondType =
                    WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

                //get���v�w�l�p�����P���敪(EqTypeOrderUnit)
                //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
                String l_strOrgWlimitOrderPriceDiv =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);

                //get���v�w�l�p�����P��(EqTypeOrderUnit)
                //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
                String l_strOrgWlimitPrice =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);

                //get���v�w�l�p���s����(EqTypeOrderUnit)
                //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
                String l_strOrgWlimitExecCondType =
                    WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

                //get�x���敪(EqTypeOrderUnit)
                //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
                String l_strDelayDiv =
                    WEB3EquityDataAdapter.getDelayDiv(l_orderUnit);

                //is�蓮�����\(EqTypeOrderUnit)
                //�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f
                boolean l_blnIsManualOrderPossible =
                    l_orderManager.isManualOrderPossible(l_orderUnit);

                if (TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getTaxType()))
                {
                    l_marginExecuteGroup.taxType = WEB3TaxTypeDef.NORMAL;
                }
                if (TaxTypeEnum.SPECIAL.equals(l_orderUnitRow.getTaxType()) ||
                    TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_orderUnitRow.getTaxType()))
                {
                    l_marginExecuteGroup.taxType = WEB3TaxTypeDef.SPECIAL;
                }

                l_marginExecuteGroup.tradingType =
                    Integer.toString(l_orderUnitRow.getOrderType().intValue());

                WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
                l_repaymentUnit.repaymentDiv = l_orderUnitRow.getRepaymentType();
                l_repaymentUnit.repaymentTimeLimit = Integer.toString(l_orderUnitRow.getRepaymentNum());
                l_marginExecuteGroup.repayment = l_repaymentUnit;

                EqTypeClosingContractSpec[] l_contractSpec = null;
                if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
                {
                    EqTypeContractSettleOrderUnit l_orderUnitClose =
                        (EqTypeContractSettleOrderUnit)l_orderUnit;
                    l_contractSpec = l_orderUnitClose.getContractsToClose();
                }
                if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()))
                {
                    EqTypeContractSwapOrderUnit l_orderSwap =
                        (EqTypeContractSwapOrderUnit)l_orderUnit;
                    l_contractSpec = l_orderSwap.getContractsToClose();
                }

                if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
                {
                    l_marginExecuteGroup.openDate = null;
                    l_marginExecuteGroup.contractPrice = null;
                }
                else
                {
                    WEB3EquityContract l_contract;
                    if (l_orderUnitRow.getClosingOrderType() == null)
                    {
                        if (l_contractSpec.length > 0)
                        {
                            l_contract =
                                (WEB3EquityContract)l_positionManager.getContract(l_contractSpec[0].getContractId());
                            l_marginExecuteGroup.openDate =
                                WEB3DateUtility.toDay(l_contract.getOpenDate());
                        }
                    }
                    double l_dblTotalContractAmount = 0.0D;
                    double l_dblTotalQuantity = 0.0D;
                    for (int j = 0;j < l_contractSpec.length;j++)
                    {
                        l_contract =
                            (WEB3EquityContract)l_positionManager.getContract(l_contractSpec[j].getContractId());
                        double l_dblQuantity = l_contractSpec[j].getQuantity();
                        l_dblTotalContractAmount += l_contract.getContractAmount(l_dblQuantity);
                        l_dblTotalQuantity += l_dblQuantity;
                    }
                    l_marginExecuteGroup.contractPrice =
                        WEB3StringTypeUtility.formatNumber(
                            Math.round(l_dblTotalContractAmount / l_dblTotalQuantity));                          
                }
                l_marginExecuteGroup.priceCondType = l_orderUnitRow.getPriceConditionType();
                l_marginExecuteGroup.execCondType = 
                    l_orderManager.getExecutionConditionTypeSonar(
                    l_orderUnitRow.getExecutionConditionType());
                l_marginExecuteGroup.orderCondType = l_orderUnitRow.getOrderConditionType();
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                	l_orderUnitRow.getOrderConditionType()))
                {
					l_marginExecuteGroup.stopOrderCondPrice = 
						WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
					l_marginExecuteGroup.stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
                }
				else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
					l_orderUnitRow.getOrderConditionType()))
				{
					l_marginExecuteGroup.wlimitOrderCondPrice = 
						WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
					l_marginExecuteGroup.wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
					if (l_orderUnitRow.getWLimitPrice() == 0)
					{
						l_marginExecuteGroup.wLimitOrderPriceDiv = 
							WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
					}
					else
					{
						l_marginExecuteGroup.wLimitOrderPriceDiv = 
							WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;
						l_marginExecuteGroup.wLimitPrice = 
							WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
					}
                    //�v�w�l�p���s����
                    l_marginExecuteGroup.wlimitExecCondType = l_strWlimitExecCondType;
				}

                //�v�w�l�p�L����ԋ敪
                l_marginExecuteGroup.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;

                //�v�w�l�p�֑ؑO�����P��
                l_marginExecuteGroup.wlimitBefChgLimitPrice = l_strWlimitBefChgLimitPrice;

                //�v�w�l�p�֑ؑO���s����
                l_marginExecuteGroup.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

                //�����������敪
                l_marginExecuteGroup.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();
                
                //�����������P��
                if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
                {
                    l_marginExecuteGroup.orgOrderCondPrice = null;
                }
                else
                {
                    l_marginExecuteGroup.orgOrderCondPrice = 
                        WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
                }

                //�������������Z�q
                l_marginExecuteGroup.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

                //���v�w�l�p�����P���敪
                l_marginExecuteGroup.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;

                if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
                {
                    //���v�w�l�p�����P��
                    l_marginExecuteGroup.orgWlimitPrice = l_strOrgWlimitPrice;
                }

                //���v�w�l�p���s����
                l_marginExecuteGroup.orgWlimitExecCondType = l_strOrgWlimitExecCondType;

                l_marginExecuteGroup.orderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getQuantity());
                if (l_orderUnit.isMarketOrder())
                {
                    l_marginExecuteGroup.orderPriceDiv =
                        WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {
                    l_marginExecuteGroup.orderPriceDiv =
                        WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_marginExecuteGroup.limitPrice =
                        WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
                }

                if (l_orderUnit.isUnexecuted() == false)
                {
                    l_marginExecuteGroup.execQuantity =
                        WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getExecutedQuantity());
                    l_marginExecuteGroup.execPrice =
                        WEB3StringTypeUtility.formatNumber(
                            Math.round(l_orderUnitRow.getExecutedAmount() / l_orderUnitRow.getExecutedQuantity()));
                    OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();
                    int l_intExeLength = l_orderExecutions.length;
                    l_marginExecuteGroup.executeUnits = new WEB3MarginExecuteUnit[l_intExeLength];
                    for (int j = 0;j < l_intExeLength;j++)
                    {
                        WEB3MarginExecuteUnit l_executeUnit = new WEB3MarginExecuteUnit();
                        l_executeUnit.executionTimestamp = l_orderExecutions[j].getExecutionTimestamp();
                        l_executeUnit.execQuantity =
                            WEB3StringTypeUtility.formatNumber(l_orderExecutions[j].getExecutionQuantity());
                        l_executeUnit.execPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderExecutions[j].getExecutionPrice());                             
                        l_marginExecuteGroup.executeUnits[j] = l_executeUnit;
                    }
                    double l_dblDeliveryAmt = l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit);
                    l_marginExecuteGroup.deliveryPrice =
                        WEB3StringTypeUtility.formatNumber(l_dblDeliveryAmt);
                }
                l_marginExecuteGroup.estimatedPrice =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());
                l_marginExecuteGroup.orderDate =
                    l_orderUnitRow.getCreatedTimestamp();
                l_marginExecuteGroup.orderBizDate =
                    WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
                boolean l_isCarriedOrderUnit =
                    l_orderManager.isCarriedOrderUnit(l_orderUnit);
                if (l_isCarriedOrderUnit)
                {
                    l_marginExecuteGroup.expirationDate =
                        WEB3DateUtility.toDay(l_orderUnitRow.getExpirationDate());
                }
                l_marginExecuteGroup.transactionStateType =
					WEB3EquityDataAdapter.getTransactionStatusType(l_orderUnit);
                    
                l_marginExecuteGroup.orderChannel = l_orderUnitRow.getOrderChanel();
                l_marginExecuteGroup.orderRootDiv = l_orderUnitRow.getOrderRootDiv();
                if (l_orderUnitRow.getTraderIdIsNull() == false)
                {
                    Trader l_trader;
                    if ((l_trader = l_finObjectManager.getTrader(l_orderUnitRow.getTraderId())) != null)
                    {
                        l_marginExecuteGroup.operatorCode = l_trader.getTraderCode();
                    }
                }

                //�x���敪
                l_marginExecuteGroup.delayDiv = l_strDelayDiv;
                //�蓮�����\�t���O
                l_marginExecuteGroup.manualFlag = l_blnIsManualOrderPossible;
                //�������ϗ��R
                l_marginExecuteGroup.forcedSettleReason = l_orderUnitRow.getForcedSettleReasonType();
                //���������敪
                l_marginExecuteGroup.forcedLapseDiv = l_orderUnitRow.getForcedExpireType();
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME,l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        return l_group;
    }

    /**
     * (create��������������)<BR>
     * ���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * (1)�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�<BR>
     * <BR>
     * (2)�������w��𕶎���C���X�^���X�ɐݒ�<BR>
     * �@@(2-1)�p�����[�^.��������NULL�i�������w��j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@"biz_date = ?"<BR>
     * <BR>
     * �@@(2-2)�p�����[�^.��������NULL�i�������w��Ȃ��j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@"biz_date >= ?"<BR>
     * <BR>
     * (3)�����J�e�S���w���ǉ�<BR>
     * <BR>
     * �@@�@@�@@" and order_categ IN (?, ?, ?)"<BR>
     * <BR>
     * (4)�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ�A����ID�w���ǉ�<BR>
     * �@@�@@�i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@" and product_id = ?"<BR>
     * <BR>
     * (5)�p�����[�^.�s��R�[�h��NULL�i�s��R�[�h�w��j�̏ꍇ�A�s��ID�w���ǉ�<BR>
     * �@@�@@�i�s��R�[�h�ɑΉ�����s��ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@" and market_id = ?"<BR>
     * <BR>
     * (6)�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ�A���������敪�w���ǉ�<BR>
     *�@@�i���������P�ʃe�[�u��.�����������ɒl���ݒ肳��Ă���ꍇ�́A<BR>
     * �@@���������������ƂɌ�������B<BR>
     * �@@�����������ɒl���ݒ肳��Ă��Ȃ��ꍇ�́A<BR>
     * �@@���������P�ʃe�[�u��.�������������ƂɌ�������B) <BR>
     * <BR>
     * �@@" and nvl(org_order_condition_type,order_condition_type) = ?" <BR>
     * <BR>
     * (7)������C���X�^���X��ԋp<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * @@param l_datOrderBizDate - (������)<BR>
     * @@param l_strOrderConditionDiv - (���������敪)<BR>
     * @@return String<BR>
     * @@roseuid 40FCA49A0196
     */
    protected String createSearchCondCharacter(
        String l_strProductCode,
        String l_strMarketCode,
        Date l_datOrderBizDate,
        String l_strOrderConditionDiv)
    {
        final String STR_METHOD_NAME =
            "createSearchCondCharacter(String, String, Date, String)";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbSearchCond = new StringBuffer();

        if (l_datOrderBizDate != null)
        {
            l_sbSearchCond.append("biz_date = ?");
        }
        else
        {
            l_sbSearchCond.append("biz_date >= ?");
        }
        
        l_sbSearchCond.append(" and order_categ in (?, ?, ?)");

        if (l_strProductCode != null)
        {
            l_sbSearchCond.append(" and product_id = ?");
        }

        if (l_strMarketCode != null)
        {

            l_sbSearchCond.append(" and market_id = ?");
        }
        
        // �p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ
        if (l_strOrderConditionDiv != null)
        {
            l_sbSearchCond.append(" and nvl(org_order_condition_type,order_condition_type) = ?");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_sbSearchCond.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * �������iwhere�ȉ��w��̕�����j�̃p�����[�^�̕�����z����쐬����B<BR>
     * <BR>
     * (1)������z��𐶐�<BR>
     * <BR>
     * (2)�������w��l�𕶎���z��ɃZ�b�g<BR>
     * �@@(2-1)�p�����[�^.��������NULL�i�������w��j�̏ꍇ<BR>
     * �@@�@@�@@�������w��l �� �p�����[�^.������<BR>
     * <BR>
     * �@@(2-2)�p�����[�^.��������NULL�i�������w��Ȃ��j�̏ꍇ<BR>
     * �@@�@@�@@�������w��l �� �Ɩ����t(*1)<BR>
     * <BR>
     * (3)�ȉ��̒����J�e�S���𕶎���z��ɃZ�b�g<BR>
     * �@@�@@�E�h�V�K�������h<BR>
     * �@@�@@�E�h�ԍϒ����h<BR>
     * �@@�@@�E�h�������n�����h<BR>
     * �@@�@@(OrderCategEnum�ɂĒ�`)<BR>
     * <BR>
     * (4)�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ�A<BR>
     * �@@�@@����ID�𕶎���z��ɃZ�b�g�i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@����ID �� �g���v���_�N�g�}�l�[�W��.get����(�،���ЃI�u�W�F�N�g(*2), �p�����[�^.�����R�[�h).����ID<BR>
     * <BR>
     * (5)�p�����[�^.�s��R�[�h��NULL�i�s��R�[�h�w��j�̏ꍇ�A�s��ID�𕶎���z��ɃZ�b�g<BR>
     * �@@�@@�i�s��R�[�h�ɑΉ�����s��ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@�s��ID �� ���Z�I�u�W�F�N�g�}�l�[�W��.get�s��(�،���ЃI�u�W�F�N�g.�،���ЃR�[�h, �p�����[�^.�s��R�[�h).�s��ID<BR>
     * <BR>
     * (6)�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ�A<BR>
     * �p�����[�^.���������敪�𕶎���z��ɃZ�b�g <BR>
     * <BR>
     * �@@���������敪 �� �p�����[�^.���������敪<BR>
     * <BR>
     * (7)(2)�A(3)�A(4)�A(5)�A(6)�ɂăp�����[�^���Z�b�g����������z���ԋp<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem( ).getBizDate( ) <BR>
     * (*2)�،���ЃI�u�W�F�N�g�́A�⏕����.getInstitution( )�Ŏ擾���ݒ�<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * @@param l_datOrderBizDate - (������)<BR>
     * @@param l_strOrderConditionDiv - (���������敪)<BR>
     * @@return String[]<BR>
     * @@roseuid 40FCA49A01A2
     */
    protected String[] createSearchCondDataContainers(
        String l_strProductCode,
        String l_strMarketCode,
        Date l_datOrderBizDate,
        String l_strOrderConditionDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createSearchCondDataContainers(String ,String ,Date, String)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        List l_lisParams = new ArrayList();

        if (l_datOrderBizDate != null)
        {
            l_lisParams.add(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datOrderBizDate));
        }
        else
        {
            Timestamp l_tsbizDate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
            l_lisParams.add(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_tsbizDate));
        }
        l_lisParams.add(Integer.toString(OrderCategEnum.OPEN_MARGIN.intValue()));
        l_lisParams.add(Integer.toString(OrderCategEnum.CLOSE_MARGIN.intValue()));
        l_lisParams.add(Integer.toString(OrderCategEnum.SWAP_MARGIN.intValue()));

        try
        {
            // (4)�i�����R�[�h�w��j�̏ꍇ
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)this.getSubAccount();
            if (l_strProductCode != null)
            {
                WEB3EquityProduct l_product =
                    (WEB3EquityProduct)l_productManager.getProduct(
                            l_subAccount.getInstitution(),
                            l_strProductCode);
                l_lisParams.add(Long.toString(l_product.getProductId()));
            }
            
            // (5)�i�s��R�[�h�w��j�̏ꍇ�A�s��ID�𕶎���z��ɃZ�b�g
            if (l_strMarketCode != null)
            {
                Market l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(
                        l_subAccount.getInstitution(),
                        l_strMarketCode);
                l_lisParams.add(Long.toString(l_market.getMarketId()));
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        // 6)�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ
        if (l_strOrderConditionDiv != null)
        {
            // �p�����[�^.���������敪�𕶎���z��ɃZ�b�g 
            l_lisParams.add(l_strOrderConditionDiv);
        }
        
        String[] l_strParams = new String[l_lisParams.size()];
        l_lisParams.toArray(l_strParams);
        log.exiting(STR_METHOD_NAME);
        return l_strParams;
    }

    /**
     * (create�\�[�g����)<BR>
     * �\�[�g������������쐬���Ԃ��B<BR>
     * <BR>
     * (1)�p�����[�^.�M�p����\�[�g�L�[ �� null�̏ꍇ�́A<BR>
     * �@@"����ID ����"�̃\�[�g�����������ԋp����B<BR>
     * <BR>
     * (2)�p�����[�^.�M�p����\�[�g�L�[.�L�[���ڂ̐����A�Ή�����e�[�u���̗񕨗�����<BR>
     * �@@�@@�����^�~���w���t�����Z�b�g<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�<BR>
     * �@@�@@�@@���e�[�u�����F�����P�ʃe�[�u��(eqtype_order_unit)<BR>
     * �@@�@@�@@���L�[���ڕ�����i�V���{�����j�́A���b�Z�[�W��`�����Q��<BR>
     * �@@�@@�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q��<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ϊ��O�@@�@@�@@�@@�@@�@@�@@�@@�@@�ϊ���<BR>
     *              -------------�@@�@@�@@�@@-----------------------------<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�����R�[�h  �@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D����ID<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�����敪 �@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�ŋ敪<BR>
     *                �E�s��R�[�h  �@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�s��ID<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E����敪 �@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�l�i���� �@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�l�i����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E���s���� �@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D���s����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�������� �@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D��������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�������� �@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�쐬���t<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E������    �@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�������� �@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�����������t<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�ٍϋ敪 �@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�ٍϋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�ٍϊ��� �@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�ٍϊ���<BR>
     * <BR>
     * �@@�@@�E�����^�~���w��́A�M�p����\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ�<BR>
     * <BR>
     * (3)�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt��<BR>
     * <BR>
     * (4)�쐬�����\�[�g�����������ԋp<BR>
     * <BR>
     * @@param l_marginSortKey - �M�p����\�[�g�L�[<BR>
     * ���N�G�X�g�f�[�^.�M�p����\�[�g�L�[<BR>
     * @@return String
     * @@roseuid 40FCA49A01F0
     */
    protected String createSortCond(WEB3MarginSortKey[] l_marginSortKey)
    {
        final String STR_METHOD_NAME = "createSortCond(WEB3MarginSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_marginSortKey == null)
        {
            return "product_id ASC";
        }
        
        StringBuffer l_sbReturn = new StringBuffer();
        for (int i = 0;i < l_marginSortKey.length;i++)
        {      
            if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.PRODUCTCODE))
            {
                l_sbReturn.append("eqtype_order_unit.product_id");
            }                
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.ACCOUNTTYPE))
            {
                l_sbReturn.append("eqtype_order_unit.tax_type");
            }                       
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.TRADEMARKET))
            {
                l_sbReturn.append("eqtype_order_unit.market_id");
            }                                    
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.TRADETYPE))
            {
                l_sbReturn.append("eqtype_order_unit.order_type");
            }                                             
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.PRICE_COND))
            {
                l_sbReturn.append("eqtype_order_unit.price_condition_type");
            }
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.EXECUTE_COND))
            {
                l_sbReturn.append("eqtype_order_unit.execution_condition_type");
            }
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.SEND_COND))
            {
                l_sbReturn.append("eqtype_order_unit.order_condition_type");
            }
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.ORDER_TIME))
            {
                l_sbReturn.append("eqtype_order_unit.created_timestamp");
            }
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.SEND_DATE))
            {
                l_sbReturn.append("eqtype_order_unit.biz_date");
            }
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.ORDER_TIMELIMIT))
            {
                l_sbReturn.append("eqtype_order_unit.expiration_date");
            }
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.REPAYMENT_DIV))
            {
                l_sbReturn.append("eqtype_order_unit.repayment_type");
            }
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.REPAYMENTNUM))
            {
                l_sbReturn.append("eqtype_order_unit.repayment_num");
            }
            else
            {
                continue;
            }
            if (WEB3AscDescDef.ASC.equals(l_marginSortKey[i].ascDesc))
            {
                l_sbReturn.append("�@@ASC , ");
            }
            else
            {
                l_sbReturn.append("�@@DESC , ");
            }
        }
        
        l_sbReturn.append("eqtype_order_unit.last_updated_timestamp ASC");
        log.debug("�\�[�g���� = " + l_sbReturn.toString());
        
        log.exiting(STR_METHOD_NAME);
        return l_sbReturn.toString();
    }

    /**
     * (get�o����܂Œ����P��FromFirstToLast)<BR>
     * �w�肳�ꂽ�u�o����܂Œ����v�̒����P�ʃI�u�W�F�N�g�ɑ΂���A<BR>
     * �������`�ŐV�̒����܂ł̒����P�ʃI�u�W�F�N�g�̈ꗗ���擾����B<BR>
     * <BR>
     * �������`�ŐV�̒����܂ł̒����P�ʃI�u�W�F�N�g�����L���o�����ɂĎ擾����B<BR>
     * <BR>
     * �@@�@@�����o������<BR>
     * <BR>
     * �@@�@@�@@[�p�����[�^.�����P��.���񒍕��̒����P��ID == 0�i���o����܂Œ����̌������j�̏ꍇ]<BR>
     * �@@�@@�@@�@@�p�����[�^.�����P�ʁi���������g�j�A<BR>
     * �@@�@@�@@�@@�y�с@@���񒍕��̒����P��ID == �p�����[�^.�����P��.�����P��ID<BR>
     * <BR>
     * �@@�@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�@@���񒍕��̒����P��ID == �p�����[�^.�����P��.���񒍕��̒����P��ID�@@�܂���<BR>
     * �@@�@@�@@�@@�����P��ID == �p�����[�^.�����P��.���񒍕��̒����P��ID<BR>
     * <BR>
     * �@@�@@�@@�@@��������.���񒍕��̒����P��ID�ɂ́A0���Z�b�g����Ă���ׁB<BR>
     * <BR>
     * �@@�@@�@@�@@�擾���������P�ʃI�u�W�F�N�g���쐬�������ɏ����Ń\�[�g���A�z��ɂ��ĕԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return EqTypeOrderUnit[]
     * @@roseuid 40FCA49A023F
     */
    protected EqTypeOrderUnit[] getCarriedOrderUnitFromFirstToLast(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCarriedOrderUnitFromFirstToLast(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        EqTypeOrderUnit[] l_orderUnits = null;
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
        List l_lisRecords = null;
        String l_strWhere = "order_unit_id=? or first_order_unit_id=?";
        Object l_objWhere[];
        if (l_orderUnitRow.getFirstOrderUnitId() == 0L)
        {
            l_objWhere =
                new Object[] {
                    new Long(l_orderUnitRow.getOrderUnitId()),
                    new Long(l_orderUnitRow.getOrderUnitId())};
        }
        else
        {
            l_objWhere =
                new Object[] {
                    new Long(l_orderUnitRow.getFirstOrderUnitId()),
                    new Long(l_orderUnitRow.getFirstOrderUnitId())};
        }
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_strWhere,
                    "created_timestamp asc",
                    null,
                    l_objWhere);

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
        
        int l_intSize = l_lisRecords.size();
        l_orderUnits = new EqTypeOrderUnit[l_intSize];
        for (int i = 0;i < l_intSize;i++)
        {
            EqtypeOrderUnitRow l_orderUnitRowNew =
                (EqtypeOrderUnitRow)l_lisRecords.get(i);
            l_orderUnits[i] =
                (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitRowNew);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnits;
    }

    /**
     * (is��������\�����P��)<BR>
     * �w�肳�ꂽ�����P�ʃI�u�W�F�N�g���A��������\�ł��邩�ǂ������肷��B<BR>
     * ��������\�̏ꍇ��true���A�ȊO��false��Ԃ��B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�M�p����������Ɖ�T�[�r�X)is��������\�����P�ʁv�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * @@param l_eqtypeProduct - (��������)<BR>
     * @@param l_market - (�s��)<BR>
     * @@return boolean
     * @@roseuid 40FCF9D00200
     */
    protected boolean isChangeCancelEnableOrderUnit(
        WEB3GentradeSubAccount l_subAccount,
        EqTypeOrderUnit l_orderUnit,
        WEB3EquityProduct l_eqtypeProduct,
        WEB3GentradeMarket l_market)
    {
        final String STR_METHOD_NAME =
            "isChangeCancelEnableOrderUnit(WEB3GentradeSubAccount, EqTypeOrderUnit, WEB3EquityProduct, WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        
        if (!l_branch.isMarginTradeEnforcement(l_orderUnitRow.getRepaymentType()))
        {
            return false;
        }
        
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        if (!l_mainAccount.isMarginAccountEstablished(l_orderUnitRow.getRepaymentType()))
        {
            return false;
        }
        
        WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
        try
        {
            OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
            if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg) ||
                OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
            {
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
            {
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            
            //validate�������()
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
            WEB3EquityTradedProduct l_tradedProduct =
                (WEB3EquityTradedProduct)l_orderManager.validateTradedProduct(
                    l_product,
                    l_market);

            //validate�戵�\�s��i�M�p�j()
            l_orderManager.validateHandlingMarket(
                l_branch,
                l_tradedProduct,
                l_market.getMarketCode(),
                l_orderUnitRow.getRepaymentType(),
                l_orderUnitRow.getRepaymentNum());

            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
            
            //validate����\�ڋq()
            OrderValidationResult l_validationResult =
                l_orderValidator.validateSubAccountForTrading(l_subAccount);
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                return false;
            }
            
			//�����P�ʂ̔����������ݓ������Z�o�������������O�̏ꍇ
			Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
			Date l_orderBizDate = 
				WEB3DateUtility.getDate(
					l_orderUnitRow.getBizDate(), 
					WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                    
			if (WEB3DateUtility.compare(l_bizDate, l_orderBizDate) > 0)
			{
				//validate�ǌ���������t�\()
				WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
					ProductTypeEnum.EQUITY);
			}
        }
        catch (WEB3BaseException l_exp)
        {
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (is�w������)<BR>
     * �w�肳�ꂽ����Ԃɍ��v���Ă��邩�ǂ����𔻒肵�A<BR>
     * ���v���Ă���ꍇ��true���A���v���Ă��Ȃ��ꍇ��false���A���ꂼ��Ԃ��B<BR>
     * <BR>
     * this.get����ԋ敪(�p�����[�^.�����P��)���R�[������B<BR>
     * �@@�擾��������ԋ敪 == �p�����[�^.����ԋ敪�̏ꍇ�́Atrue��Ԃ��B<BR>
     * �@@�ȊO�Afalse��Ԃ��B<BR>
     * <BR>
     * @@param l_strExecType - (����ԋ敪)<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * @@return boolean
     * @@roseuid 40FCA49A0221
     */
    protected boolean isDesignateExecutedStatus(
        String l_strExecType,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDesignateExecutedStatus(String, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_result = false;

        if (l_strExecType.equals(WEB3EquityDataAdapter.getExecType(l_orderUnit)))
        {
            l_result = true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
}
@
