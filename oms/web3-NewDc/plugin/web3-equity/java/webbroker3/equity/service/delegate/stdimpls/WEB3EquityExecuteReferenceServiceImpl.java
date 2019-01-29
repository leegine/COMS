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
filename	WEB3EquityExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������Ɖ�T�[�r�XImpl(WEB3EquityExecuteReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 �������F(SRA) �V�K�쐬
Revesion History : 2006/07/05 �юu�� (���u) �d�l�ύX���f��942
Revesion History : 2006/07/15 �h�C �y�����z�d�l�ύX�Ǘ��䒠�E���f��952��Ή�
Revesion History : 2006/07/20 �h�C �y�����z�d�l�ύX�Ǘ��䒠�E���f��957��Ή�
Revesion History : 2006/08/02 �h�C (���u) �d�l�ύX ���f��No.959��Ή�
Revesion History : 2006/08/29 �ęԍg(���u) �d�l�ύX���f��970
Revesion History : 2006/11/21 ������(���u)�@@���f��No.989,No.997,No.1077
Revesion History : 2007/10/16 ����(���u) �d�l�ύX���f��1196
Revesion History : 2008/02/15 ��іQ(���u) �d�l�ύX���f��No.1300
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityFinTransactionManager;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.equity.define.WEB3EquityWlimitOrderPriceDivDef;
import webbroker3.equity.message.WEB3EquityChangeCancelHistoryGroup;
import webbroker3.equity.message.WEB3EquityCommissionInfoUnit;
import webbroker3.equity.message.WEB3EquityExecuteDetailsRequest;
import webbroker3.equity.message.WEB3EquityExecuteDetailsResponse;
import webbroker3.equity.message.WEB3EquityExecuteGroup;
import webbroker3.equity.message.WEB3EquityExecuteReferenceRequest;
import webbroker3.equity.message.WEB3EquityExecuteReferenceResponse;
import webbroker3.equity.message.WEB3EquityExecuteUnit;
import webbroker3.equity.message.WEB3EquityOrderHistoryRequest;
import webbroker3.equity.message.WEB3EquityOrderHistoryResponse;
import webbroker3.equity.message.WEB3EquitySortKey;
import webbroker3.equity.service.delegate.WEB3EquityExecuteReferenceService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;


/**
 * �i���������������Ɖ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �������������Ɖ�T�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3EquityExecuteReferenceServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityExecuteReferenceService
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityExecuteReferenceServiceImpl.class);
   

    /**
     * @@roseuid 40A299DF003E
     */
    public WEB3EquityExecuteReferenceServiceImpl()
    {

    }

    /**
     * ���������������Ɖ�T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�usearch�������Ɖ�v<BR>
     * �usearch�������ڍׁv�usearch������藚���v��<BR>
     * �����ꂩ�̃��\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4060DEB5012D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3EquityExecuteReferenceRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^��
            //�u���������������Ɖ�N�G�X�g�v�̏ꍇ
            l_response = this.searchExecuteReference(
                (WEB3EquityExecuteReferenceRequest) l_request);
        }
        else if (l_request instanceof WEB3EquityExecuteDetailsRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^��
            //�u���������������ڍ׃��N�G�X�g�v�̏ꍇ
            l_response = this.searchExecuteDetails(
                (WEB3EquityExecuteDetailsRequest) l_request);
        }
        else if (l_request instanceof WEB3EquityOrderHistoryRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^��
            //�u��������������藚�����N�G�X�g�v�̏ꍇ
            l_response = this.searchOrderHistory(
                (WEB3EquityOrderHistoryRequest) l_request);
        }
        else
        {
            log.error(" __Error[���͒l���s���ł�]__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (search�������Ɖ�j<BR>
     * ���������������Ɖ�A����������������ꗗ<BR>
     * �̏������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�������Ɖ�jsearch�������Ɖ�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���������������Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.equity.message.WEB3EquityExecuteReferenceResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406A8FF7033B
     */
    public WEB3EquityExecuteReferenceResponse searchExecuteReference(
        WEB3EquityExecuteReferenceRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "searchExecuteReference(WEB3EquityExecuteReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        
        //1.1. validate()
        l_request.validate();
        
        //1.2. get�⏕����()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        boolean l_blnOffFloorBranch = false;
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getOffFloorDiv()))
        {
            l_blnOffFloorBranch = true;
        }
        
        boolean l_isChangeOrderAccept = true;
        boolean l_isCancelOrderAccept = true;
        boolean l_isCancelOrderSalesOutsideMarketAccept = true;
        // ������t�X�e�C�^�X
        boolean l_isReferenceAcceptBatch = false;
        boolean l_isReferenceAcceptScram = false;
        boolean l_isReferenceSalesOutsideMarketAcceptBatch = false;
        boolean l_isReferenceSalesOutsideMarketAcceptScram = false;
        String l_strErrorMassage = null;
        //1.3. ���N�G�X�g.�Ɖ�敪��"�������Ɖ�"�̏ꍇ�̂ݎ��s
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(l_request.referenceType))
        {
            //1.3.1. validate������t�\()
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BaseException l_exp)
            {
                if (l_exp instanceof WEB3BusinessLayerException)
                {
                    WEB3BusinessLayerException l_ble = (WEB3BusinessLayerException) l_exp;
                    // ������t�X�e�C�^�X���o�b�`��
                    if (l_ble.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00011)
                    {
                        l_isReferenceAcceptBatch = true;
                    }
                    // ������t�X�e�C�^�X���ً}��~��
                    else if (l_ble.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00012)
                    {
                        l_isReferenceAcceptScram = true;
                    }                    
                }
            }
            if (l_blnOffFloorBranch == true)
            {
                //1.3.2. reset������t���i()
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
                //1.3.3. validate������t�\()
                try
                {
                    WEB3GentradeTradingTimeManagement.validateOrderAccept();
                }
                catch (WEB3BaseException l_exp)
                {
                    if (l_exp instanceof WEB3BusinessLayerException)
                    {
                        WEB3BusinessLayerException l_ble = (WEB3BusinessLayerException) l_exp;
                        // ������t�X�e�C�^�X���o�b�`��
                        if (l_ble.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00011)
                        {
                            l_isReferenceSalesOutsideMarketAcceptBatch = true;
                        }
                        // ������t�X�e�C�^�X���ً}��~��
                        else if (l_ble.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00012)
                        {
                            l_isReferenceSalesOutsideMarketAcceptScram = true;
                        }                    
                    }
                }
            }

            // �i����O�����E���{��Ђ̏ꍇ�̂݁j�����A����O�������ɁA������t�X�e�C�^�X���o�b�`������
            if (l_isReferenceAcceptBatch == true &&
                l_isReferenceSalesOutsideMarketAcceptBatch == true)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME);
            }
            // �i����O�����E���{��Ђ̏ꍇ�̂݁j�����A����O�������ɁA������t�X�e�C�^�X���ً}��~��
            else if (l_isReferenceAcceptScram == true &&
                     l_isReferenceSalesOutsideMarketAcceptScram == true)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME);
            }
            if (l_blnOffFloorBranch == true)
            {
                // �i����O�����E���{��Ђ̏ꍇ�̂݁j
                // �����A����O�����ǂ��炩���A������t�X�e�C�^�X���ً}��~�� or �o�b�`������
                if ((l_isReferenceAcceptBatch == true && 
                          l_isReferenceSalesOutsideMarketAcceptScram == true) ||
                         (l_isReferenceAcceptScram == true &&
                          l_isReferenceSalesOutsideMarketAcceptBatch == true))
                {
                    if (l_isReferenceAcceptScram)
                    {
                        l_strErrorMassage = "������[�@@�ً}��~���@@]�A����O������[�@@�o�b�`�������@@]�ƂȂ��Ă���܂��B";
                    }
                    else if (l_isReferenceSalesOutsideMarketAcceptScram)
                    {
                        l_strErrorMassage = "�����́m�@@�o�b�`�������@@]�A����O������[�@@�ً}��~���@@]�ƂȂ��Ă���܂��B";
                    }
                    // �ً}��~��
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                        WEB3GentradeTradingTimeManagement.class.getName()
                            + "." + STR_METHOD_NAME,
                            l_strErrorMassage);
                }
            }
            else
            {
                //�i����O�����E����{��Ђ̏ꍇ�̂݁j������������t�X�e�C�^�X���o�b�`������
                if (l_isReferenceAcceptBatch == true)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                        WEB3GentradeTradingTimeManagement.class.getName()
                            + "." + STR_METHOD_NAME);
                }
                //�i����O�����E����{��Ђ̏ꍇ�̂݁j������������t�X�e�C�^�X���ً}��~��
                else if (l_isReferenceAcceptScram == true)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                        WEB3GentradeTradingTimeManagement.class.getName()
                            + "." + STR_METHOD_NAME);
                }
            }

            if (l_request.productCode != null)
            {
                try
                {
                    EqTypeProduct l_product =
                        l_productManager.getProduct(l_subAccount.getInstitution(), l_request.productCode);
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        this.getClass().getName() + "." +STR_METHOD_NAME,
                        l_nfe.getMessage(), null);
                }
            }
        }
        //1.4. ���N�G�X�g.�Ɖ�敪��"��������ꗗ"�̏ꍇ�̂ݎ��s
        else
        {
            //1.4.1. reset������t���i()
            WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
            //1.4.2. reset������t�g�����U�N�V����()
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
            //1.4.3. validate������t�\()
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BaseException l_exp)
            {
                l_isChangeOrderAccept = false;
            }
            //1.4.4. reset������t�g�����U�N�V����()
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
            //1.4.5. validate������t�\()
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BaseException l_exp)
            {
                l_isCancelOrderAccept = false;
            }

            if (l_blnOffFloorBranch == true)
            {
                //1.4.6. reset��t���ԋ敪
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET);
                //1.4.7. reset������t���i()
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
                //1.4.8. validate������t�\()
                try
                {
                    WEB3GentradeTradingTimeManagement.validateOrderAccept();
                    l_isCancelOrderSalesOutsideMarketAccept = true;
                }
                catch (WEB3BaseException l_exp)
                {
                    l_isCancelOrderSalesOutsideMarketAccept = false;
                }
            }
            else
            {
                l_isCancelOrderSalesOutsideMarketAccept = false;
            }

            if (!l_isChangeOrderAccept &&
                !l_isCancelOrderAccept &&
                !l_isCancelOrderSalesOutsideMarketAccept)
            {
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00146,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //1.4.9. getOrderValidator()
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
            //1.4.10. validate����\�ڋq()
            OrderValidationResult l_orderValidationResult =
                l_orderValidator.validateSubAccountForTrading(l_subAccount);
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.error("__Error[validate����\�ڋq���`�F�b�N]__");
                throw new WEB3SystemLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //1.4.11. ���N�G�X�g.�����R�[�h�w�莞�̂ݎ��s
            if (l_request.productCode != null)
            {
                WEB3EquityProduct l_product = null;
                try
                {
                    l_product =
                        (WEB3EquityProduct)l_productManager.getProduct(
                            l_subAccount.getInstitution(),
                            l_request.productCode);
                }
                catch (NotFoundException l_nfe)
                {
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
						STR_METHOD_NAME,
						l_nfe.getMessage(), 
						null);
                }
                //1.4.12. ���N�G�X�g.�����R�[�h�y�юs��R�[�h�w�莞�̂ݎ��s
                if (l_request.marketCode != null)
                {
                    //1.4.12.1. get�s��()
                    Market l_market = null;
                    try
                    {
                        l_market = l_finObjectManager.getMarket(
                            l_subAccount.getInstitution().getInstitutionCode(),
                            l_request.marketCode);
                    }
                    catch (NotFoundException l_nfe)
                    {
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + "." +STR_METHOD_NAME,
                            l_nfe.getMessage(),
                            l_nfe);
                    }
                    //1.4.12.2. validate�������()
                    WEB3EquityTradedProduct l_tradedProduct =
                        (WEB3EquityTradedProduct)l_orderManager.validateTradedProduct(
                            l_product,
                            l_market);
                    //1.4.12.3. validate�戵�\�s��()
                    l_orderManager.validateHandlingMarket(
                        l_branch,
                        l_tradedProduct);
                }
            }
        }
        
        //1.5. createResponse()
        WEB3EquityExecuteReferenceResponse l_response =
            (WEB3EquityExecuteReferenceResponse)l_request.createResponse();
                
        //1.6. �����R�[�h�E�s��R�[�h�E�������v���_�E���쐬
        // ���葱���T�v�� �Q�j����
        //1.6.2. get�戵�\�s��()
        String[] l_strHandlingPossibleMarkets = null;
        try
        {
            l_strHandlingPossibleMarkets =
                WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
                    l_branch,
                    ProductTypeEnum.EQUITY);
        }
        catch (WEB3BaseException l_exp)
        {
            log.debug(this.getClass().getName() + "." + STR_METHOD_NAME, l_exp);
        }
        
        // ���葱���T�v�� �S�j����
        //1.6.25. �Q�j�ō쐬�����s��R�[�h�ꗗ���A���X�|���X.�s��R�[�h�ꗗ�ɃZ�b�g����B
        l_response.marketList = l_strHandlingPossibleMarkets;
        //1.6.26. �������ꗗ���擾���A���X�|���X.�������ꗗ�ɃZ�b�g����B
        TreeMap l_orderBizDateMap = new TreeMap();
        //1.6.26.1. �V�X�e�����t�̑O�c�Ɠ��A�y�уV�X�e�����t���A���X�|���X.�������ꗗ�ɃZ�b�g����B
        Date l_datBizdate = GtlUtils.getTradingSystem().getBizDate();
        Timestamp l_tsBizdate = new Timestamp(l_datBizdate.getTime());
        Date l_datSysdate = WEB3DateUtility.toDay(l_tsBizdate);
        l_orderBizDateMap.put(l_datSysdate, l_datSysdate);
        WEB3GentradeBizDate l_genBizDate =
            new WEB3GentradeBizDate(l_tsBizdate);
        Date l_datBizDate = WEB3DateUtility.toDay(l_genBizDate.roll(-1));
        l_orderBizDateMap.put(l_datBizDate, l_datBizDate);
        //1.6.26.2. �Q�j�Ŏ擾�����s��R�[�h�ꗗ�����ALOOP
        int l_intMarketSize = 0;
        if (l_strHandlingPossibleMarkets != null)
        {
            l_intMarketSize = l_strHandlingPossibleMarkets.length;
        }
        
        //reset��t���ԋ敪()
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        for (int i = 0;i < l_intMarketSize;i++)
        {
            //1.6.26.2.1. reset�s��R�[�h()
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strHandlingPossibleMarkets[i]);
            //1.6.26.2.2. get������()
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            if (!l_orderBizDateMap.containsKey(l_datBizDate))
            {
                l_orderBizDateMap.put(l_datBizDate, l_datBizDate);
            }
        }
        //1.6.26.3. �擾�����s�ꖈ�̔������ɁA���X�|���X.�������ꗗ�Ɋ܂܂�Ă��Ȃ����t������ꍇ�́A
        // �@@�@@�@@�@@�@@���X�|���X.�������ꗗ�ɒǉ�����B
        int l_intListSize = l_orderBizDateMap.size();
        Date[] l_orderBizDateList = new Date[l_intListSize];
        Collection l_collection = l_orderBizDateMap.values();
        l_collection.toArray(l_orderBizDateList);
        l_response.orderBizDateList = l_orderBizDateList;

        //1.7. �f�[�^�擾�y�у��X�|���X�ݒ�
        // ���葱���T�v�� �P�j����
        //1.7.1. get�����P�ʈꗗFrom���N�G�X�g()
        List l_lisOrderUnit = null;
        List l_lisOrderUnitFinal = null;
        boolean l_isMarketTrading = true;
        boolean l_isSalesOutsideMarket = true;
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(l_request.referenceType))
        {
            if (l_isReferenceAcceptBatch || l_isReferenceAcceptScram)
            {   
                l_isMarketTrading = false;
            }
            if (l_isReferenceSalesOutsideMarketAcceptBatch || 
                l_isReferenceSalesOutsideMarketAcceptScram)
            {
                l_isSalesOutsideMarket = false;
            }
        }
        l_lisOrderUnit =
            this.getOrderUnitsFromRequest(
                l_subAccount,
                l_request,
                l_isMarketTrading,
                l_isSalesOutsideMarket);
        
        // ���葱���T�v�� �Q�j����
        List l_lisOrderUnitTmp = null;
        int l_intOrderUnitsFromRequestSize = 0;
        if (l_lisOrderUnit != null)
        {
            l_lisOrderUnitTmp = new ArrayList();
            l_intOrderUnitsFromRequestSize = l_lisOrderUnit.size();
        }
        long l_lngProductId = 0L;
        HashMap l_orderChangeImpossibleMap = new HashMap();
        HashMap l_orderCancelImpossibleMap = new HashMap();
        boolean l_validateTradedProduct = true;
        for (int i = 0;i < l_intOrderUnitsFromRequestSize;i++)
        {
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOrderUnit.get(i);
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(l_request.referenceType))
            {
                l_lisOrderUnitTmp.add(l_orderUnit);
            }
            // �Q�|�P�j
            String l_strTradingTimeType;
            String l_strOrderAcceptProduct;
            if (!WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.STOCK;
            }
            else
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET;
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET;
            }
            // reset������t�g�����U�N�V����()
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
            //1.7.2. reset��t���ԋ敪()
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_strTradingTimeType);
            //1.7.3. reset������t���i()
            WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(l_strOrderAcceptProduct);
            //1.7.4. reset�s��R�[�h()
            Market l_market = null;
            try
            {
                l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." +STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());
            //1.7.5. validate������t�\()
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BaseException l_exp)
            {
                l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                continue;
            }
            
            // �Q�|�Q�j
            //1.7.6. getProduct()
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
            if (l_product.getProductId() != l_lngProductId)
            {
                l_lngProductId = l_product.getProductId();
                try
                {
                    //1.7.7. validate�������()
                    WEB3EquityTradedProduct l_tradedProduct =
                        (WEB3EquityTradedProduct)l_orderManager.validateTradedProduct(l_product, l_market);
                    //1.7.8. validate�戵�\�s��()
                    l_orderManager.validateHandlingMarket(l_branch, l_tradedProduct);
                    l_validateTradedProduct = true;
                }
                catch (WEB3BaseException l_exp)
                {
                    l_validateTradedProduct = false;
                    l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    continue;
                }
            }
            else
            {
                if (!l_validateTradedProduct)
                {
                    l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    continue;
                }
            }
            
            // �Q�|�R�j�@@
            //1.7.9. getCommonOrderValidator()
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
            //1.7.10. validate����\�ڋq()
            OrderValidationResult l_result = l_orderValidator.validateSubAccountForTrading(l_subAccount);
            if (l_result.getProcessingResult().isFailedResult())
            {
                l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                continue;
            }
            
            // �Q�|�R�j�C
            //1.7.13. getOrder()
            Order l_order = l_orderUnit.getOrder();
            //1.7.14. validate���������\���()
            boolean l_blnIsOrderChangePossibleStatus = true;
            WEB3EquityTradedProduct l_tradedProduct = null;
            try
            {
                l_tradedProduct = (WEB3EquityTradedProduct) l_orderUnit.getTradedProduct();
                l_orderManager.validateOrderForChangeability(l_order);
            }
            catch (WEB3BaseException l_exp)
            {
                l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                l_blnIsOrderChangePossibleStatus = false;
            }
            catch (RuntimeSystemException l_rse)
            {
                l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                l_blnIsOrderChangePossibleStatus = false;
            }
            
            //1.7.15. �����\���ǂ����̃`�F�b�N
            if (l_blnIsOrderChangePossibleStatus)
            {
                try
                {
                    //1.7.15.2. validate�������()
                    l_orderManager.validateTradedProduct(
                        l_product,
                        l_market);
                    //1.7.15.3. validate�C���T�C�_�[()
                    l_orderManager.validateInsider(l_subAccount, l_product);
                    //1.7.15.4. validate�ڋq�����ʎ����~()
                    l_orderManager.validateAccountProductOrderStop(
                        l_subAccount,
                        l_orderUnitRow.getProductId(),
                        l_orderUnit.getOrderType());
                }
                catch (WEB3BaseException l_exp)
                {
                    l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    l_blnIsOrderChangePossibleStatus = false;
                }
            }
            //1.7.16. validate��������\���()
            boolean l_blnIsOrderCancelPossibleStatus = true;
            try
            {
                if (l_tradedProduct != null)
                {
                    l_orderManager.validateOrderForCancellation(l_order);
                }
                else
                {
                    l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    l_blnIsOrderCancelPossibleStatus = false;
                }
            }
            catch (WEB3BaseException l_exp)
            {
                l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                l_blnIsOrderCancelPossibleStatus = false;
            }
            if (!l_blnIsOrderChangePossibleStatus &&
                !l_blnIsOrderCancelPossibleStatus)
            {
                continue;
            }
            
			// �Q�|�R�j�F
			//1.7.19. ������ԊǗ�.get������ �� �����P��.�������̏ꍇ�̂ݎ��{�B
			Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
			Date l_orderBizDate =
				WEB3DateUtility.getDate(
					l_orderUnitRow.getBizDate(),
					WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            
			if (WEB3DateUtility.compare(l_bizDate, l_orderBizDate) > 0)
			{
				//1.7.19.1. validate�ǌ���������t�\()
				try
				{
					WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(ProductTypeEnum.EQUITY);
				}
				catch (WEB3BaseException l_exp)
				{
					l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
					l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
					continue;
				}
			}
            
            // �Q�|�R�j�G�y�чH
            //1.7.20. ����O���������̏ꍇ�i�������P��.����R�[�h�iSONAR�j=="����O����"�̏ꍇ�j�̂ݎ��s
            if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                //1.7.20.1. get�����o�H�敪()
                if (WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
                {
                    l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    continue;
                }
                //1.7.20.2. validate����O������t�\()
                try
                {
                    l_orderManager.validateOffFloorOrderPossible(
                        l_orderUnitRow.getProductId(),
                        l_orderUnitRow.getMarketId(),
                        l_subAccount);
                }
                catch (WEB3BaseException l_exp)
                {
                    l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    continue;
                }
            }
            if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_request.referenceType))
            {
                l_lisOrderUnitTmp.add(l_orderUnit);
            }
        }
        
        // ���葱���T�v�� �R�j����
        //1.7.21. ���N�G�X�g�f�[�^.����ԋ敪�I��null�@@�̏ꍇ
        int l_intSize;
        if (l_request.execType != null)
        {
            l_lisOrderUnitFinal = new ArrayList();
            l_intSize = 0;
            if (l_lisOrderUnitFinal != null)
            {
                l_intSize = l_lisOrderUnitTmp.size();
            }
            for (int i = 0;i < l_intSize;i++)
            {
                EqTypeOrderUnit l_orderUnit =
                    (EqTypeOrderUnit)l_lisOrderUnitTmp.get(i);
                //1.7.21.1. is�w������()
                if (this.isExecType(l_request.execType, l_orderUnit))
                {
                    l_lisOrderUnitFinal.add(l_orderUnit);
                }
            }
        }
        else
        {
            l_lisOrderUnitFinal = l_lisOrderUnitTmp;
        }
        
        // ���葱���T�v�� �S�j����
        //1.7.22. remove�J�z�������P��()
        if (l_lisOrderUnitFinal != null)
        {
            l_intSize = l_lisOrderUnitFinal.size();
        }
        else
        {
            l_intSize = 0;
        }
        EqTypeOrderUnit[] l_orderUnits = new EqTypeOrderUnit[l_intSize];
        for (int i = 0;i < l_intSize;i++)
        {
            l_orderUnits[i] = (EqTypeOrderUnit)l_lisOrderUnitFinal.get(i);
        }
        l_orderUnits = l_orderManager.removeCarryOverOriginalOrderUnit(l_orderUnits);
        
        // ���葱���T�v�� �T�j����
        if (l_orderUnits != null)
        {
            l_intSize = l_orderUnits.length;
        }
        else
        {
            l_intSize = 0;
        }
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intTotalPages = l_intSize / l_intPageSize;
        if (l_intSize % l_intPageSize > 0)
        {
            l_intTotalPages++;
        }
        if (l_intTotalPages > 0)
        {
            //���X�|���X.���y�[�W��
            l_response.totalPages = Integer.toString(l_intTotalPages);
            //���X�|���X.�����R�[�h��
            l_response.totalRecords = Integer.toString(l_intSize);
            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            if (l_intSize <= (l_intPageSize * (l_intPageIndex - 1)))
            {
                l_intPageIndex = l_intTotalPages;
            }
            //���X�|���X.�\���y�[�W�ԍ�
            l_response.pageIndex = Integer.toString(l_intPageIndex);
            
            //���X�|���X.ID�ꗗ
            l_intSize = l_orderUnits.length;
            l_response.idList = new String[l_intSize];
            for (int i = 0;i < l_intSize;i++)
            {
                l_response.idList[i] = Long.toString(l_orderUnits[i].getOrderId());
            }
            // ���葱���T�v�� �T�j����
            int l_intStart = l_intPageSize * (l_intPageIndex - 1);
            l_intSize = l_orderUnits.length;
            int l_intRecordCount = l_intPageSize;
            if ((l_intStart + l_intPageSize) > l_intSize)
            {
                l_intRecordCount -= l_intStart + l_intPageSize - l_intSize;
            }
            WEB3EquityExecuteGroup[] l_executeGroups =
                new WEB3EquityExecuteGroup[l_intRecordCount];
            for (int i = l_intStart, j = 0;j < l_intRecordCount;i++,j++)
            {
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)l_orderUnits[i].getDataSourceObject();
                    
                Market l_market = null;
                try
                {
                    l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." +STR_METHOD_NAME,
                        l_nfe.getMessage(),
                        l_nfe);
                }
                // reset�s��R�[�h()
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());

                // reset��t���ԋ敪()
                // ��t���ԋ敪�F�����P��.����R�[�h�iSONAR�j=="����O����"�̏ꍇ�́A"����O����"
                //              �ȊO�A"�����E�M�p"
                String l_tradingTimeType = null;
                if(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
                {
                    l_tradingTimeType = WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET;
                }
                else
                {
                    l_tradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                }
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_tradingTimeType);
                
                //1.7.23. get���s�����iSONAR�j()
                String l_strExecCondType =
                    l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getExecutionConditionType());

                //get���s�����iSONAR�j(EqTypeExecutionConditionType)
                //PR�w�ɕԂ����s�������擾����B
                //�����͈ȉ��̒ʂ�ɐݒ肷��B
                //���s�����F�@@�����P��.(W�w�l)���s����
                String l_strExecutionConditionTypeSonar = null;
                if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
                {
                    l_strExecutionConditionTypeSonar =
                        l_orderManager.getExecutionConditionTypeSonar(
                            l_orderUnitRow.getWLimitExecCondType());
                }

                //get�v�w�l�p�L����ԋ敪(EqTypeOrderUnit)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B
                //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
                String l_strWLimitEnableStatusDiv =
                    WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnits[i]);

                // get�v�w�l�p�֑ؑO�����P��(EqTypeOrderUnit)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B
                //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
                String l_strWLimitBefSwitchPrice =
                    WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnits[i]);

                //get�v�w�l�p�֑ؑO���s����(EqTypeOrderUnit)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B
                //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
                String l_strWLimitBefSwitchExecCondType =
                    WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnits[i]);

                // get���v�w�l�p�����P���敪(EqTypeOrderUnit)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B
                //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
                String l_strOrgWLimitOrderPriceDiv =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnits[i]);

                // get���v�w�l�p�����P��(EqTypeOrderUnit)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B
                //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
                String l_strOrgWLimitOrderPrice =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnits[i]);

                //get���v�w�l�p���s����(EqTypeOrderUnit)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B
                //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
                String l_strOrgWLimitExecCondType =
                    WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnits[i]);

                //1.7.24. get�����󋵋敪()
                String l_strTransactionStateType = WEB3EquityDataAdapter.getTransactionStatusType(l_orderUnits[i]);

                // get�x���敪(EqTypeOrderUnit)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B
                //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
                String l_strDelayDiv =
                    WEB3EquityDataAdapter.getDelayDiv(l_orderUnits[i]);

                // is�蓮�����\(EqTypeOrderUnit)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B
                //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
                boolean l_blnIsManualOrderPossible =
                    l_orderManager.isManualOrderPossible(l_orderUnits[i]);

                //1.7.25. getExecutions()
                OrderExecution[] l_orderExecutions = null;
                if (!l_orderUnits[i].isUnexecuted())
                {
                    l_orderExecutions = l_orderUnits[i].getExecutions();
                }
                //���X�|���X.�������ꗗ.ID
                l_executeGroups[j] = new WEB3EquityExecuteGroup();
                l_executeGroups[j].id = Long.toString(l_orderUnits[i].getOrderId());
                //���X�|���X.�������ꗗ.�����R�[�h
                WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnits[i].getProduct();
                EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();
                l_executeGroups[j].productCode = l_product.getProductCode();
                //���X�|���X.�������ꗗ.������
                l_executeGroups[j].productName = l_productRow.getStandardName();
                //���X�|���X.�������ꗗ.�s��R�[�h
                l_executeGroups[j].marketCode = l_market.getMarketCode();
                //���X�|���X.�������ꗗ.�����敪
                l_executeGroups[j].taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnitRow.getTaxType());
                //���X�|���X.�������ꗗ.����敪
                if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnitRow.getOrderType()))
                {
                    if (!WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
                    {
                        l_executeGroups[j].tradingType = WEB3TradingTypeDef.BUY_ORDER;
                    }
                    else
                    {
                        l_executeGroups[j].tradingType = WEB3TradingTypeDef.PRESENCE_ORDER;
                    }
                }
                else
                {
                    l_executeGroups[j].tradingType = WEB3TradingTypeDef.SELL_ORDER;
                }
                //���X�|���X.�������ꗗ.�l�i����
                l_executeGroups[j].priceCondType = l_orderUnitRow.getPriceConditionType();
                //���X�|���X.�������ꗗ.���s����
                l_executeGroups[j].execCondType = l_strExecCondType;
                //���X�|���X.�������ꗗ.���������敪
                l_executeGroups[j].orderCondType = l_orderUnitRow.getOrderConditionType();
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
                {
					//���X�|���X.�������ꗗ.�t�w�l�p���������P��
					l_executeGroups[j].stopOrderCondPrice = 
						WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
					//���X�|���X.�������ꗗ.�t�w�l�p�����������Z�q
					l_executeGroups[j].stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
                }
                else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
                {
                	//���X�|���X.�������ꗗ.�v�w�l�p���������P��
                	l_executeGroups[j].wlimitOrderCondPrice = 
                		WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
                	//���X�|���X.�������ꗗ.�v�w�l�p�����������Z�q
                	l_executeGroups[j].wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
                	//���X�|���X.�������ꗗ.W�w�l�p�����P���敪
                	if (l_orderUnitRow.getWLimitPrice() == 0)
                	{
						l_executeGroups[j].wLimitOrderPriceDiv = 
							WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;	
                	}
                	else
                	{
						l_executeGroups[j].wLimitOrderPriceDiv = 
							WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;
						//���X�|���X.�������ꗗ.W�w�l�p�����P��
						l_executeGroups[j].wLimitPrice = 
							WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
                	}
                    //���X�|���X.�������ꗗ.W�w�l�p���s�����F
                    //�����P��.����������2�iW�w�l�j�̏ꍇ�̂݁A
                    //�g�����������}�l�[�W��.get���s�����iSONAR�j(�����P��.(W�w�l)���s����)�̖߂�l���Z�b�g�B
                    l_executeGroups[j].wlimitExecCondType = l_strExecutionConditionTypeSonar;
                }
                //���X�|���X.�������ꗗ.W�w�l�p�L����ԋ敪�F�@@
                //�����f�[�^�A�_�v�^.getW�w�l�p�L����ԋ敪�i�����P�ʁj�̖߂�l���Z�b�g�B
                l_executeGroups[j].wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;

                //���X�|���X.�������ꗗ.W�w�l�p�֑ؑO�����P���F�@@
                //�����f�[�^�A�_�v�^.getW�w�l�p�֑ؑO�����P���i�����P�ʁj�̖߂�l���Z�b�g�B
                l_executeGroups[j].wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;

                //���X�|���X.�������ꗗ.W�w�l�p�֑ؑO���s�����F�@@
                //�����f�[�^�A�_�v�^.getW�w�l�p�֑ؑO���s�����i�����P�ʁj�̖߂�l���Z�b�g�B
                l_executeGroups[j].wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

                //���X�|���X.�������ꗗ.�����������敪
                l_executeGroups[j].orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();
                //���X�|���X.�������ꗗ.�����������P��
                if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
                {
                    l_executeGroups[j].orgOrderCondPrice = null;
                }
                else
                {
                    l_executeGroups[j].orgOrderCondPrice = 
                        WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
                }

                //���X�|���X.�������ꗗ.�������������Z�q
                l_executeGroups[j].orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

                //���X�|���X.�������ꗗ.��W�w�l�p�����P���敪�F
                //�����f�[�^�A�_�v�^.get��W�w�l�p�����P���敪�i�����P�ʁj�̖߂�l���Z�b�g�B
                l_executeGroups[j].orgWlimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;

                //���X�|���X.�������ꗗ.��W�w�l�p�����P���F�@@
                //���X�|���X.��W�w�l�p�����P���敪��"�w�l"�̏ꍇ�A�����f�[�^�A�_�v�^.get��W�w�l�p�����P���i�����P�ʁj�̖߂�l���Z�b�g�B
                if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
                {
                    l_executeGroups[j].orgWlimitPrice = l_strOrgWLimitOrderPrice;
                }

                //���X�|���X.�������ꗗ.��W�w�l�p���s�����F�@@
                //�����f�[�^�A�_�v�^.get��W�w�l�p���s�����i�����P�ʁj�̖߂�l���Z�b�g�B
                l_executeGroups[j].orgWlimitExecCondType = l_strOrgWLimitExecCondType;
                
                //���X�|���X.�������ꗗ.��������
                l_executeGroups[j].orderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getQuantity());
                //���X�|���X.�������ꗗ.�����P���敪
                if (l_orderUnits[i].isMarketOrder())
                {
                    l_executeGroups[j].orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {
                    l_executeGroups[j].orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                }
                //���X�|���X.�������ꗗ.�����P��
                if (!l_orderUnits[i].isMarketOrder())
                {
                    l_executeGroups[j].limitPrice =
                        WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
                }
                if (!l_orderUnits[i].isUnexecuted())
                {
                    //���X�|���X.�������ꗗ.��芔��
                    l_executeGroups[j].execQuantity =
                        WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getExecutedQuantity());
                    //���X�|���X.�������ꗗ.���P��
                    double l_dblExecPrice =
                        Math.round(l_orderUnitRow.getExecutedAmount() / l_orderUnitRow.getExecutedQuantity());
                    l_executeGroups[j].execPrice =
                        WEB3StringTypeUtility.formatNumber(l_dblExecPrice);

                    WEB3EquityFinTransactionManager l_finTransactionManager =
                        (WEB3EquityFinTransactionManager)l_tradingModule.getFinTransactionManager();
                    //get�g�����U�N�V����
                    List l_lisTransactions = l_finTransactionManager.getTransactions(l_orderUnits[i]);
                    //���X�|���X.��n���
                    //get��������n���
                    l_executeGroups[j].deliveryPrice =
                        WEB3StringTypeUtility.formatNumber(
                            l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnits[i],l_lisTransactions));
                    //���X�|���X.�������ꗗ.�T�Z���v
                    l_executeGroups[j].estimatedProfitLoss = l_finTransactionManager.getEstimatedProfitLoss(l_lisTransactions);
                }
                //���X�|���X.�������ꗗ.�T�Z��n���
                l_executeGroups[j].estimatedPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());
                //���X�|���X.�������ꗗ.��������
                l_executeGroups[j].orderDate = l_orderUnitRow.getCreatedTimestamp();
                //���X�|���X.�������ꗗ.������
                l_executeGroups[j].orderBizDate =
                    WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
                //���X�|���X.�������ꗗ.�����L������
                if (l_orderManager.isCarriedOrderUnit(l_orderUnits[i]))
                {
                    l_executeGroups[j].expirationDate = l_orderUnitRow.getExpirationDate();
                }
                //���X�|���X.�������ꗗ.������
                l_executeGroups[j].transactionStateType = l_strTransactionStateType;
                //���X�|���X.�������ꗗ.�����\�t���O
                String l_strOrderUnitId = Long.toString(l_orderUnitRow.getOrderUnitId());
                if (l_orderChangeImpossibleMap.containsKey(l_strOrderUnitId))
                {
                    l_executeGroups[j].changeFlag = false;
                }
                else
                {
                    l_executeGroups[j].changeFlag = true;
                }
                //���X�|���X.�������ꗗ.����\�t���O
                if (l_orderCancelImpossibleMap.containsKey(l_strOrderUnitId))
                {
                    l_executeGroups[j].cancelFlag = false;
                }
                else
                {
                    l_executeGroups[j].cancelFlag = true;
                }
                if (this.getTrader() != null)
                {
                    //���X�|���X.�������ꗗ.�����`���l��
                    l_executeGroups[j].orderChannel = l_orderUnitRow.getOrderChanel();
                    //���X�|���X.�������ꗗ.�����o�H�敪
                    l_executeGroups[j].orderRootDiv = l_orderUnitRow.getOrderRootDiv();
                    //���X�|���X.�������ꗗ.�I�y���[�^�R�[�h
                    if (l_orderUnitRow.getTraderIdIsNull() == false)
                    {
                        try
                        {
                            l_executeGroups[j].operatorCode =
                                l_finObjectManager.getTrader(l_orderUnitRow.getTraderId()).getTraderCode();
                        }
                        catch (NotFoundException l_nfe)
                        {
                            l_executeGroups[j].operatorCode = null;
                        }
                    }
                }

                //���X�|���X.�������ꗗ.�x���敪�F�@@�����f�[�^�A�_�v�^.get�x���敪�i�����P�ʁj�̖߂�l���Z�b�g�B
                l_executeGroups[j].delayDiv = l_strDelayDiv;

                //���X�|���X.�������ꗗ.�蓮�����\�t���O�F�@@
                //�g�����������}�l�[�W��.is�蓮�����\�i�����P�ʁj�̖߂�l���Z�b�g�B
                l_executeGroups[j].manualFlag = l_blnIsManualOrderPossible;

                if (l_orderExecutions != null)
                {
                    WEB3EquityExecuteUnit[] l_execUnit =
                        new WEB3EquityExecuteUnit[l_orderExecutions.length];
                    for (int k = 0;k < l_orderExecutions.length;k++)
                    {
                        //���X�|���X.�������ꗗ.�������ꗗ.������
                        l_execUnit[k] = new WEB3EquityExecuteUnit();
                        l_execUnit[k].executionTimestamp = l_orderExecutions[k].getExecutionTimestamp();
                        //���X�|���X.�������ꗗ.�������ꗗ.��萔��
                        l_execUnit[k].execQuantity =
                            WEB3StringTypeUtility.formatNumber(l_orderExecutions[k].getExecutionQuantity());
                        //���X�|���X.�������ꗗ.�������ꗗ.���P��
                        l_execUnit[k].execPrice =
                             WEB3StringTypeUtility.formatNumber(l_orderExecutions[k].getExecutionPrice());

                    }
                    l_executeGroups[j].executeUnits = l_execUnit;
                }
            }
            l_response.executeGroups = l_executeGroups;
        }
        
        //1.9. get�s��ǌx���s��()
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        String[] l_strCloseMarkets =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_branch,
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);
        l_response.messageSuspension = l_strCloseMarkets;
        
        if (l_response.executeGroups == null ||
            l_response.marketList == null)
        {
            //���X�|���X.���y�[�W��
            l_response.totalPages = "0";
            //���X�|���X.�����R�[�h��
            l_response.totalRecords = "0";
            //���X�|���X.�\���y�[�W�ԍ�
            l_response.pageIndex = "0";
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (search�������ڍ�)<BR>
     * �w�肳�ꂽ�����P��ID���������P�ʃI�u�W�F�N�g�̓��e����ʕ\���p�ɕҏW���A<BR>
     * ���X�|���X�ɐݒ肵�ĕԂ��B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�������Ɖ�jsearch�������ڍׁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���������������ڍ׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.equity.message.WEB3EquityExecuteDetailsResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406A8FFA00BA
     */
    public WEB3EquityExecuteDetailsResponse searchExecuteDetails(WEB3EquityExecuteDetailsRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "searchExecuteDetails(WEB3EquityExecuteDetailsRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            
        //1.1. validate()
        l_request.validate();
        
        //1.2. getOrderUnits()
        OrderUnit[] l_orderUnits =
            l_orderManager.getOrderUnits(Long.parseLong(l_request.id));
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
        
        //reset��t���ԋ敪(��t���ԋ敪 : String)
        //�����P��.����R�[�h�iSONAR�j��"����O����"�̏ꍇ�A"�����E�M�p"�B
        //�����P��.����R�[�h�iSONAR�j��"����O����"�̏ꍇ�A"����O����"
        //
        //1.3. reset������t���i()
        if (!WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
        {
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        }
        else
        {
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
            WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
        }
        
        //1.4. validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5. get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.6. get�萔���R�[�X�R�[�h()
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        Date l_datBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
        String l_strCommissionCourseDiv = null;
        try
        {
            l_strCommissionCourseDiv =
                l_bizLogicProvider.getCommissionCourseDiv(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_orderUnitRow.getCommProductCode(),
                    l_orderUnitRow.getCommTblNo(),
                    l_orderUnitRow.getCommTblSubNo(),
                    l_datBizDate);
        }
        //�O�������ɂ��Ď萔���}�X�^���Ȃ��Ȃ��Ă��܂��\�������邽�߁A
        //�u�Y���f�[�^�����ɂ��Ắv�G���[�I�����Ȃ悤�ɂ���
        catch (WEB3SystemLayerException l_wse)
        {
            if (l_wse.getErrorInfo() != WEB3ErrorCatalog.SYSTEM_ERROR_80005)
            {
                throw l_wse;
            }
        }
        
        //1.7. createResponse()
        WEB3EquityExecuteDetailsResponse l_response =
            (WEB3EquityExecuteDetailsResponse)l_request.createResponse();
        
        //1.8. get���s�����iSONAR�j()
        String l_strExecutionConditionType =
            l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getExecutionConditionType());
        
        //1.9. get������ԋ敪()
        String l_orderStatus = WEB3EquityDataAdapter.getOrderState(l_orderUnit);
        
        //1.10. get����ԋ敪()
        String l_execType = WEB3EquityDataAdapter.getExecType(l_orderUnit);

        //W�w�l�̏ꍇ
        //get���s�����iSONAR�j(EqTypeExecutionConditionType)
        //PR�w�ɕԂ����s�������擾����B
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //���s�����F�@@�����P��.(�v�w�l�p)���s����
        String l_strExecutionConditionTypeSonar = null;
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_strExecutionConditionTypeSonar =
                l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getWLimitExecCondType());
        }

        //get�v�w�l�p�L����ԋ敪(EqTypeOrderUnit)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
        String l_strWLimitEnableStatusDiv =
            WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        //get�v�w�l�p�֑ؑO�����P��(EqTypeOrderUnit)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
        String l_strWLimitBefSwitchPrice =
            WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        // get�v�w�l�p�֑ؑO���s����(EqTypeOrderUnit)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
        String l_strWLimitBefSwitchExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        // get���v�w�l�p�����P���敪(EqTypeOrderUnit)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
        String l_strOrgWLimitOrderPriceDiv =
            WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);

        //get���v�w�l�p�����P��(EqTypeOrderUnit)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
        String l_strOrgWLimitOrderPrice =
            WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);

        //get���v�w�l�p���s����(EqTypeOrderUnit)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
        String l_strOrgWLimitExecCondType =
            WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //1.11. get�����󋵋敪()
        String l_transactionStateType = WEB3EquityDataAdapter.getTransactionStatusType(l_orderUnit);

        //get�x���敪(EqTypeOrderUnit)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
        String l_strDelayDiv = WEB3EquityDataAdapter.getDelayDiv(l_orderUnit);

        //is�蓮�����\(EqTypeOrderUnit)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�����P�ʁF�@@���X�|���X�ɐݒ肷�钍���P��
        boolean l_blnIsManualOrderPossible =
            l_orderManager.isManualOrderPossible(l_orderUnit);

        //1.12. get�g�����U�N�V����
        WEB3EquityFinTransactionManager l_finTransactionManager =
            (WEB3EquityFinTransactionManager)l_tradingModule.getFinTransactionManager();
        List l_lisTransactions = l_finTransactionManager.getTransactions(l_orderUnit);

        //1.13. get��������n���()�@@�@@���o�����̎�n������擾
        double l_dblNetAmountTotal = l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit,l_lisTransactions);

        //1.14. get�ϑ��萔�����v()
        double l_dblCommTotal = l_finTransactionManager.getCommTotal(l_orderUnit);
        
        //1.15. get�ϑ��萔������ō��v()
        double l_dblCommConsumptionTaxTotal =
            l_finTransactionManager.getCommConsumptionTaxTotal(l_orderUnit);
        
        //1.16. getExecutions()
        OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();

        //1.17. get�T�Z���v
        String l_estimatedProfitLoss = l_finTransactionManager.getEstimatedProfitLoss(l_lisTransactions);

        //1.18. �v���p�e�B�Z�b�g
        //���X�|���X.ID
        l_response.id = Long.toString(l_orderUnit.getOrderId());
        //���X�|���X.�����R�[�h
        WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
        EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();
        l_response.productCode = l_product.getProductCode();
        //���X�|���X.������
        l_response.productName = l_productRow.getStandardName();
        //���X�|���X.�s��R�[�h
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        Market l_market = null;
        try
        {
            l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        l_response.marketCode = l_market.getMarketCode();
        //���X�|���X.�����敪
        l_response.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnitRow.getTaxType());

        //���X�|���X.����敪
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnitRow.getOrderType()))
        {
            if (!WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                l_response.tradingType = WEB3TradingTypeDef.BUY_ORDER;
            }
            else
            {
                l_response.tradingType = WEB3TradingTypeDef.PRESENCE_ORDER;
            }
        }
        else
        {
            l_response.tradingType = WEB3TradingTypeDef.SELL_ORDER;
        }
        //���X�|���X.�l�i����
        l_response.priceCondType = l_orderUnitRow.getPriceConditionType();
        //���X�|���X.���s����
        l_response.execCondType = l_strExecutionConditionType;
        //���X�|���X.���������敪
        l_response.orderCondType = l_orderUnitRow.getOrderConditionType();
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            //���X�|���X.�t�w�l�p���������P��
            l_response.stopOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            //���X�|���X.�t�w�l�p�����������Z�q
            l_response.stopOrderCondOperator =
                l_orderUnitRow.getOrderCondOperator();
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            //���X�|���X.W�w�l�p���������P��
            l_response.wlimitOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            //���X�|���X.W�w�l�p�����������Z�q
            l_response.wlimitOrderCondOperator =
                l_orderUnitRow.getOrderCondOperator();
            //���X�|���X.W�w�l�p�����P���敪
            if ( l_orderUnitRow.getWLimitPrice() == 0)
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                //���X�|���X.W�w�l�p�����P��
                l_response.wLimitPrice =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
            }
            //W�w�l�p���s�����F�@@�����P��.����������2�iW�w�l�j�̏ꍇ�̂݁A
            //�g�����������}�l�[�W��.get���s�����iSONAR�j(�����P��.(W�w�l)���s����)�̖߂�l���Z�b�g�B
            l_response.wlimitExecCondType = l_strExecutionConditionTypeSonar;
        }
        //W�w�l�p�L����ԋ敪�F�@@�����f�[�^�A�_�v�^.getW�w�l�p�L����ԋ敪�i�����P�ʁj�̖߂�l���Z�b�g�B
        l_response.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;

        //W�w�l�p�֑ؑO�����P���F�@@�����f�[�^�A�_�v�^.getW�w�l�p�֑ؑO�����P���i�����P�ʁj�̖߂�l���Z�b�g�B
        l_response.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;

        //W�w�l�p�֑ؑO���s�����F�@@�����f�[�^�A�_�v�^.getW�w�l�p�֑ؑO���s�����i�����P�ʁj�̖߂�l���Z�b�g�B
        l_response.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

        //���X�|���X.�����������敪
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();
        
        //���X�|���X.�����������P��
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice = null;
        }
        else
        {
            l_response.orgOrderCondPrice = 
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }
        
        //���X�|���X.�������������Z�q
        l_response.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        //��W�w�l�p�����P���敪�F�@@�����f�[�^�A�_�v�^.get��W�w�l�p�����P���敪�i�����P�ʁj�̖߂�l���Z�b�g�B
        l_response.orgWlimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;

        //��W�w�l�p�����P���F�@@
        //�@@�@@�@@���X�|���X.��W�w�l�p�����P���敪��"�w�l"�̏ꍇ�A�����f�[�^�A�_�v�^.get��W�w�l�p�����P���i�����P�ʁj�̖߂�l���Z�b�g�B
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_response.orgWlimitOrderPriceDiv))
        {
            l_response.orgWlimitPrice = l_strOrgWLimitOrderPrice;
        }

        //��W�w�l�p���s�����F�@@�����f�[�^�A�_�v�^.get��W�w�l�p���s�����i�����P�ʁj�̖߂�l���Z�b�g�B
        l_response.orgWlimitExecCondType = l_strOrgWLimitExecCondType;

        //���X�|���X.��������
        l_response.orderQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        //���X�|���X.�����P���敪
        if (l_orderUnit.isMarketOrder())
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        //���X�|���X.�����P��
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_response.orderPriceDiv))
        {
            l_response.limitPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        }
        //���X�|���X.�T�Z��n���
        l_response.estimatedPrice =
            WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());
        //���X�|���X.�����L������
        if (l_orderManager.isCarriedOrderUnit(l_orderUnit))
        {
            l_response.expirationDate = l_orderUnit.getExpirationTimestamp();
        }
        //���X�|���X.������t��
        l_response.orderDate = l_orderUnitRow.getReceivedDateTime();
        //���X�|���X.������ԋ敪
        l_response.orderState = l_orderStatus;
        //���X�|���X.�J�z�G���[�R�[�h
        if (WEB3OrderStatusDef.NOT_TRANSFERED.equals(l_orderStatus))
        {
            l_response.transferErrCode = l_orderUnitRow.getErrorReasonCode();
        }
        //���X�|���X.������
        l_response.orderBizDate =
            WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
        //���X�|���X.����ԋ敪
        l_response.execType = l_execType;
        if (!l_orderUnit.isUnexecuted())
        {
            //���X�|���X.��n��
            Date l_deliveryDate = l_orderUnitRow.getDeliveryDate();
            l_response.deliveryDate = WEB3DateUtility.toDay(l_deliveryDate);
            //���X�|���X.��芔��
            double l_dblExecQuantity = l_orderUnitRow.getExecutedQuantity();
            l_response.execQuantity =
                WEB3StringTypeUtility.formatNumber(l_dblExecQuantity);
            //���X�|���X.���P��
            double l_dblExecutedAmount = l_orderUnitRow.getExecutedAmount();
            double l_dblExecPrice = Math.round(l_dblExecutedAmount / l_dblExecQuantity);
            l_response.execPrice = WEB3StringTypeUtility.formatNumber(l_dblExecPrice);
            //���X�|���X.�����
            l_response.execTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblExecutedAmount);
            //���X�|���X.��n���
            l_response.deliveryPrice = WEB3StringTypeUtility.formatNumber(l_dblNetAmountTotal);
            //���X�|���X.�T�Z���v
            l_response.estimatedProfitLoss = l_estimatedProfitLoss;
            //���X�|���X.�萔�����.�萔���R�[�X�R�[�h
            l_response.commissionInfo = new WEB3EquityCommissionInfoUnit();
            l_response.commissionInfo.commissionCourse = l_strCommissionCourseDiv;
            //���X�|���X.�萔�����.�萔��
            l_response.commissionInfo.commission = WEB3StringTypeUtility.formatNumber(l_dblCommTotal); 
            //���X�|���X.�萔�����.�����
            l_response.commissionInfo.commissionConsumptionTax =
                WEB3StringTypeUtility.formatNumber(l_dblCommConsumptionTaxTotal);
    
            if (l_orderExecutions != null)
            {
                WEB3EquityExecuteUnit[] l_executeUnits =
                    new WEB3EquityExecuteUnit[l_orderExecutions.length];
                for (int i = 0; i < l_orderExecutions.length; i++)
                { 
                    l_executeUnits[i] = new WEB3EquityExecuteUnit();
                    //�������ꗗ.������
                    l_executeUnits[i].executionTimestamp =
                        l_orderExecutions[i].getExecutionTimestamp();
                    //�������ꗗ.��芔��
                    l_executeUnits[i].execQuantity =
                        WEB3StringTypeUtility.formatNumber(l_orderExecutions[i].getExecutionQuantity());
                    //�������ꗗ.���P��
                    l_executeUnits[i].execPrice =
                        WEB3StringTypeUtility.formatNumber(l_orderExecutions[i].getExecutionPrice());
                }
                l_response.executeUnits = l_executeUnits;
            }
        }
        //���X�|���X.���X�R�[�h
        WEB3GentradeMainAccount l_account =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        l_response.branchCode = l_account.getBranch().getBranchCode();
        //���X�|���X.�ڋq�R�[�h
        l_response.accountCode = l_account.getDisplayAccountCode();
        //���X�|���X.�ڋq��
        l_response.accountName = l_account.getDisplayAccountName();
        //���X�|���X.��������敪
        l_response.changeCancelDiv = l_orderUnitRow.getModifyCancelType();
        //���X�|���X.�����o�H�敪
        l_response.orderRootDiv = l_orderUnitRow.getOrderRootDiv();
        //���X�|���X.������
        l_response.transactionStateType = l_transactionStateType;
        
        //�x���敪�F�@@�����f�[�^�A�_�v�^.get�x���敪�i�j�̖߂�l���Z�b�g
        l_response.delayDiv = l_strDelayDiv;

        //�蓮�����\�t���O�F�@@�g�����������}�l�[�W��.is�蓮�����\�i�j�̖߂�l���Z�b�g
        l_response.manualFlag = l_blnIsManualOrderPossible;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (search������藚��)<BR>
     * �w�肳�ꂽ�����P��ID���������P�ʃI�u�W�F�N�g�̒����������擾����<BR>
     * ��ʕ\���p�ɕҏW���A���X�|���X�ɐݒ肵�ĕԂ��B<BR>
     * �o����܂Œ����̏ꍇ�́A�������`�ŐV�̒����܂ł̒���������ΏۂƂ���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�������Ɖ�jsearch������藚���v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ��������������藚�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.equity.message.WEB3EquityOrderHistoryResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4079FDE100B5
     */
    public WEB3EquityOrderHistoryResponse searchOrderHistory(
        WEB3EquityOrderHistoryRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "searchOrderHistory(WEB3EquityOrderHistoryRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        
        //1.1. validate()
        l_request.validate();
        
        //1.2. getOrderUnits()
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.id));
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
        
        //1.3. reset������t���i()
        if (!WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
        {
            WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        }
        else
        {
            WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
        }
        
        //1.4. validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5. is�o����܂Œ����P��()
        boolean l_isOrderUntilDeadLineOrderUnit =
            l_orderManager.isCarriedOrderUnit(l_orderUnit);
        
        //1.6. is�o����܂Œ����P��( )==true�̏ꍇ�̂ݎ��s
        EqTypeOrderUnit[] l_eqtypeOrderUnits = null;
        if (l_isOrderUntilDeadLineOrderUnit)
        {
            //1.6.1. get�o����܂Œ����P��FromFirstToLast
            l_eqtypeOrderUnits = this.getExecutedOrderUnitFromFirstToLast(l_orderUnit);
        }
        else
        {
            l_eqtypeOrderUnits = new EqTypeOrderUnit[1];
            l_eqtypeOrderUnits[0] = l_orderUnit;
        }
        
        //1.7. �擾���������P�ʃI�u�W�F�N�g�����ALoop
        WEB3EquityChangeCancelHistoryGroup[] l_changeCancelHistoryGroups = null;
        if (l_eqtypeOrderUnits != null && l_eqtypeOrderUnits.length > 0)
        {
            List l_lisChangeCancelHistoryGroup = new ArrayList();
            for (int i = 0;i < l_eqtypeOrderUnits.length;i++)
            {
                l_orderUnit = l_eqtypeOrderUnits[i];
                //1.7.1. getOrderActions()
                OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
                
                //1.7.2. �擾�������������I�u�W�F�N�g�����ALoop
                for (int j = 0;j < l_orderActions.length;j++)
                {
                    EqTypeOrderAction l_orderAction = (EqTypeOrderAction)l_orderActions[j];
                    EqtypeOrderActionRow l_orderActionRow =
                        (EqtypeOrderActionRow)l_orderAction.getDataSourceObject();
                    //1.7.2.1. get���s�����iSONAR�j()
                    String l_strExecutionConditionType =
                        l_orderManager.getExecutionConditionTypeSonar(l_orderAction.getExecutionConditionType());
                    //1.7.2.2. get�������e�敪()
                    String l_strOrderType = WEB3EquityDataAdapter.getOrderType(l_orderAction, l_orderUnit);

                    //get���s�����iSONAR�j(EqTypeExecutionConditionType)
                    //PR�w�ɕԂ����s�������擾����B
                    //�����͈ȉ��̒ʂ�ɐݒ肷��B
                    //���s�����F�@@��������.(W�w�l)���s����
                    String l_strExecutionConditionTypeSonar = null;
                    if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderActionRow.getOrderConditionType()))
                    {
                        l_strExecutionConditionTypeSonar =
                            l_orderManager.getExecutionConditionTypeSonar(
                                l_orderActionRow.getWLimitExecCondType());
                    }
                    //get�v�w�l�p�L����ԋ敪(EqTypeOrderAction)
                    //�����͈ȉ��̒ʂ�ɐݒ肷��B
                    //���������F�@@���X�|���X�ɐݒ肷�钍������
                    String l_strWLimitEnableStatusDiv =
                        WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderAction);

                    //get�v�w�l�p�֑ؑO�����P��(EqTypeOrderAction)
                    //�����͈ȉ��̒ʂ�ɐݒ肷��B
                    //���������F�@@���X�|���X�ɐݒ肷�钍������
                    String l_strWLimitBefSwitchPrice =
                        WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderAction);

                    //get�v�w�l�p�֑ؑO���s����(EqTypeOrderAction)
                    //�����͈ȉ��̒ʂ�ɐݒ肷��B
                    //���������F�@@���X�|���X�ɐݒ肷�钍������
                    String l_strWLimitBefSwitchExecCondType =
                        WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderAction);

                    //get���v�w�l�p�����P���敪(EqTypeOrderAction)
                    //�����͈ȉ��̒ʂ�ɐݒ肷��B
                    //���������F�@@���X�|���X�ɐݒ肷�钍������
                    String l_strOrgWLimitOrderPriceDiv =
                        WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderAction);

                    //get���v�w�l�p�����P��(EqTypeOrderAction)
                    //�����͈ȉ��̒ʂ�ɐݒ肷��B
                    //���������F�@@���X�|���X�ɐݒ肷�钍������
                    String l_strOrgWLimitOrderPrice =
                        WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderAction);

                    // get���v�w�l�p���s����(EqTypeOrderAction)
                    //�����͈ȉ��̒ʂ�ɐݒ肷��B
                    //���������F�@@���X�|���X�ɐݒ肷�钍������
                    String l_strOrgWLimitExecCondType =
                        WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderAction);

                    //1.7.2.3. get��t���ʋ敪()
                    String l_strAcceptedResultDiv = WEB3EquityDataAdapter.getAcceptedResultDiv(l_orderAction);
                    
                    WEB3EquityChangeCancelHistoryGroup l_changeCancelHistoryGroup =
                        new WEB3EquityChangeCancelHistoryGroup();
                    //����NO
                    l_changeCancelHistoryGroup.orderActionId =
                        Long.toString(l_orderAction.getOrderActionId());
                    //��t����
                    l_changeCancelHistoryGroup.orderDate =
                        l_orderActionRow.getCreatedTimestamp();
                    //�������e�敪
                    l_changeCancelHistoryGroup.orderType = l_strOrderType;
                    
                    if (l_orderAction.isUnexecuted())
                    {
                        //�l�i����
                        l_changeCancelHistoryGroup.priceCondType =
                            l_orderActionRow.getPriceConditionType();
                        //���s����
                        l_changeCancelHistoryGroup.execCondType =
                            l_strExecutionConditionType;
                        //���������敪
                        l_changeCancelHistoryGroup.orderCondType =
                            l_orderActionRow.getOrderConditionType();
                        //��������
                        l_changeCancelHistoryGroup.orderQuantity =
                            WEB3StringTypeUtility.formatNumber(l_orderAction.getQuantity());
                        //�����P���敪
                        if (l_orderAction.isMarketOrder())
                        {
                            l_changeCancelHistoryGroup.orderPriceDiv =
                                WEB3OrderPriceDivDef.MARKET_PRICE;
                        }
                        else
                        {
                            l_changeCancelHistoryGroup.orderPriceDiv =
                                WEB3OrderPriceDivDef.LIMIT_PRICE;
                        }
                        //�����P��
                        if (!l_orderAction.isMarketOrder())
                        {
                            l_changeCancelHistoryGroup.limitPrice =
                                WEB3StringTypeUtility.formatNumber(l_orderAction.getPrice());
                        }
                        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderActionRow.getOrderConditionType()))
                        {
                            //�t�w�l�p���������P��
                            l_changeCancelHistoryGroup.stopOrderCondPrice =
                                WEB3StringTypeUtility.formatNumber(l_orderActionRow.getStopOrderPrice());
                            //�t�w�l�p�����������Z�q
                            l_changeCancelHistoryGroup.stopOrderCondOperator =
                                l_orderActionRow.getOrderCondOperator();
                        }
                        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderActionRow.getOrderConditionType()))
                        {
                            //W�w�l�p���������P��
                            l_changeCancelHistoryGroup.wlimitOrderCondPrice =
                                WEB3StringTypeUtility.formatNumber(l_orderActionRow.getStopOrderPrice());
                            //W�w�l�p�����������Z�q
                            l_changeCancelHistoryGroup.wlimitOrderCondOperator =
                                l_orderActionRow.getOrderCondOperator();
                            //W�w�l�p�����P���敪
                            if ( l_orderActionRow.getWLimitPrice() == 0)
                            {
                                l_changeCancelHistoryGroup.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                            }
                            else
                            {
                                l_changeCancelHistoryGroup.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                                //W�w�l�p�����P��
                                l_changeCancelHistoryGroup.wLimitPrice =
                                    WEB3StringTypeUtility.formatNumber(l_orderActionRow.getWLimitPrice());
                            }

                            //W�w�l�p���s�����F�@@(*1)��������.����������2�iW�w�l�j�̏ꍇ�̂݁A
                            //�g�����������}�l�[�W��.get���s�����iSONAR�j(��������.(W�w�l)���s����)�̖߂�l���Z�b�g�B
                            l_changeCancelHistoryGroup.wlimitExecCondType = l_strExecutionConditionTypeSonar;
                        }
                        //W�w�l�p�L����ԋ敪�F�@@(*1)�����f�[�^�A�_�v�^.getW�w�l�p�L����ԋ敪�i���������j�̖߂�l���Z�b�g�B
                        l_changeCancelHistoryGroup.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;

                        //W�w�l�p�֑ؑO�����P���F�@@(*1)�����f�[�^�A�_�v�^.getW�w�l�p�֑ؑO�����P���i���������j�̖߂�l���Z�b�g�B
                        l_changeCancelHistoryGroup.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;

                        //W�w�l�p�֑ؑO���s�����F�@@(*1)�����f�[�^�A�_�v�^.getW�w�l�p�֑ؑO���s�����i���������j�̖߂�l���Z�b�g�B
                        l_changeCancelHistoryGroup.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

                        //�����������敪�F�@@(*1)��������.����������
                        l_changeCancelHistoryGroup.orgOrderCondType =
                            l_orderActionRow.getOrgOrderConditionType();

                        //�����������P���F�@@(*1)��������.���t�w�l��l
                        if (l_orderActionRow.getOrgStopOrderPriceIsNull())
                        {
                            l_changeCancelHistoryGroup.orgOrderCondPrice = null;
                        }
                        else
                        {
                            l_changeCancelHistoryGroup.orgOrderCondPrice =
                                WEB3StringTypeUtility.formatNumber(l_orderActionRow.getOrgStopOrderPrice());
                        }

                        //�������������Z�q�F�@@(*1)��������.�������������Z�q
                        l_changeCancelHistoryGroup.orgOrderCondOperator =
                            l_orderActionRow.getOrgOrderCondOperator();

                        //��W�w�l�p�����P���敪�F�@@(*1)�����f�[�^�A�_�v�^.get��W�w�l�p�����P���敪�i���������j�̖߂�l���Z�b�g�B
                        l_changeCancelHistoryGroup.orgWlimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;

                        //��W�w�l�p�����P���F�@@(*1)
                        //���X�|���X.��W�w�l�p�����P���敪��"�w�l"�̏ꍇ�A�����f�[�^�A�_�v�^.get��W�w�l�p�����P���i���������j�̖߂�l���Z�b�g�B
                        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
                        {
                            l_changeCancelHistoryGroup.orgWlimitPrice = l_strOrgWLimitOrderPrice;
                        }

                        //��W�w�l�p���s�����F�@@(*1)�����f�[�^�A�_�v�^.get��W�w�l�p���s�����i���������j�̖߂�l���Z�b�g�B
                        l_changeCancelHistoryGroup.orgWlimitExecCondType = l_strOrgWLimitExecCondType;

                        //�����L������

                        if (l_orderManager.isCarriedOrderUnit(l_orderAction.getOrderUnitId()))
                        {
                            l_changeCancelHistoryGroup.expirationDate = l_orderActionRow.getExpirationDate();
                        }
                    }
                    else
                    {
                        //��芔��
                        l_changeCancelHistoryGroup.execQuantity =
                            WEB3StringTypeUtility.formatNumber(l_orderAction.getExecutionQuantity());
                        //���P��
                        l_changeCancelHistoryGroup.execPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderAction.getExecutionPrice());
                    }
                    
                    //��t���ʋ敪
                    l_changeCancelHistoryGroup.acceptedResultDiv = l_strAcceptedResultDiv;
                    
                    l_lisChangeCancelHistoryGroup.add(l_changeCancelHistoryGroup);
                }
            }
            l_changeCancelHistoryGroups =
                new WEB3EquityChangeCancelHistoryGroup[l_lisChangeCancelHistoryGroup.size()];
            l_lisChangeCancelHistoryGroup.toArray(l_changeCancelHistoryGroups);
        }
        
        //1.8. createResponse()
        WEB3EquityOrderHistoryResponse l_response =
            (WEB3EquityOrderHistoryResponse)l_request.createResponse();
        //���X�|���X.���������ꗗ
        l_response.changeCancelHistoryGroups = l_changeCancelHistoryGroups;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (getAll�����P�ʈꗗ)<BR>
     * �\���Ώۂ̔��������������P�ʃI�u�W�F�N�g��S�Ă��擾����B<BR>
     * <BR>
     * �P�j�@@this.create��������������(null�Anull�Anull�A������is�����擾�A������is����O�����擾�Anull)<BR>
     * �@@�@@�@@�ɂ��A����������������쐬����B<BR>
     * <BR>
     * �Q�j�@@this.create���������f�[�^�R���e�i(null�Anull�Anull�Anull)<BR>
     * �@@�@@�@@�ɂ��A���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.get�����P�ʈꗗ( )�ɂ��A<BR>
     * �@@�@@�@@���������ɍ��v���钍���P�ʃI�u�W�F�N�g��List���擾����B<BR>
     * �@@�@@�@@���擾�̍ہA����ID�����w����s���B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------<BR>
     * �@@�@@�@@��get�����P�ʈꗗ( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�@@�@@�����^�C�v�F�@@"����"<BR>
     * �@@�@@�@@��������������F�@@create��������������( )�̖߂�l<BR>
     * �@@�@@�@@���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i( )�̖߂�l<BR>
     * �@@�@@�@@�\�[�g�����F�@@����ID ����<BR>
     * �@@�@@�@@----------------------------------------------------<BR>
     * <BR>
     * �S�j�@@�擾����List��Ԃ��B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_isMarketTrading - (is�����擾)<BR>
     * @@param l_isSalesOutsideMarket - (is����O�����擾)<BR>
     * @@return List
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406D559000BB
     */
    protected List getAllOrderUnits(
        WEB3GentradeSubAccount l_subAccount,
        boolean l_isMarketTrading,
        boolean l_isSalesOutsideMarket)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAllOrderUnits(WEB3GentradeSubAccount, boolean, boolean)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisReturn = null;
        
        // �P�j�@@this.create��������������(null�Anull�Anull�A������is�����擾�A������is����O�����擾�Anull)
        // �@@�@@�@@�ɂ��A����������������쐬����B
        String l_strCond = this.createQueryCond(null, null, null, l_isMarketTrading, l_isSalesOutsideMarket, null);
        
        // �Q�j�@@this.create���������f�[�^�R���e�i(null�Anull�Anull�Anull)
        // �@@�@@�@@�ɂ��A���������f�[�^�R���e�i���쐬����B
        String[] l_strQueryCondDataContainer = this.createQueryCondDataContainer(null, null, null, null);
        
        // �R�j�@@�g�����������}�l�[�W��.get�����P�ʈꗗ( )�ɂ��A
        // �@@�@@�@@���������ɍ��v���钍���P�ʃI�u�W�F�N�g��List���擾����B
        // �@@�@@�@@���擾�̍ہA����ID�����w����s���B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        String l_strSort = "product_id ASC";
        l_lisReturn =
            l_orderManager.getOrderUnits(
                l_subAccount,
                ProductTypeEnum.EQUITY,
                l_strCond,
                l_strQueryCondDataContainer,
                l_strSort);
        
        log.exiting(STR_METHOD_NAME);
        return l_lisReturn;
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
     * (3)������ʎw���ǉ�<BR>
     * <BR>
     * �@@�@@�@@" and order_type in (*���P, *���Q)"<BR>
     * <BR>
     * �@@-----------------------------------------------------------------<BR>
     * �@@*���P�F�@@OrderTypeEnum.EQUITY_BUY�i�����������j�@@�𕶎���ɕϊ������l<BR>
     * �@@*���Q�F�@@OrderTypeEnum.EQUITY_SELL�i�����������j�@@�𕶎���ɕϊ������l<BR>
     * �@@-----------------------------------------------------------------<BR>
     * <BR>
     * (4)�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ�A����ID�w���ǉ�<BR>
     * �@@�@@�i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@" and product_id = ?"<BR>
     * <BR>
     * (5)�s��w�������ǉ�����<BR>
     * �@@(5-1)�p�����[�^.�s��R�[�h��NULL�i�s��R�[�h�w��j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�s��ID�w���ǉ�����B<BR>
     * �@@�@@�i�s��R�[�h�ɑΉ�����s��ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@" and market_id = ?"<BR>
     * <BR>
     * �@@(5-2)�p�����[�^.�s��R�[�h��NULL�i�s��R�[�h�w��Ȃ��j�̏ꍇ�A<BR>
     * �@@�@@�@@PTS�����������ΏۊO�Ƃ���B<BR>
     * <BR>
     * �@@�@@�@@(5-2-1)�@@�i���X�s��ʁEPTS�j�戵����.get�戵�\�s��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���X�F�@@�⏕����.get����X()�̖߂�l �i*1�j<BR>
     * �@@�@@�@@�@@�����^�C�v�F�@@"����"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�i*1�j�⏕������this.get�⏕����()�Ŏ擾<BR>
     * <BR>
     * �@@�@@�@@(5-2-2�j�@@(5-2-1)�Ŏ擾�����s��R�[�h�̔z��̗v�f�� > 0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�ȉ��̕�������P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@"and market_id not in (?, ?, �E�E�E�E�E�E) "�i*2�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�i*2�j�h?�h�̐��́A�擾�����z��̗v�f�����ݒ肷��B<BR>
     * <BR>
     * (6)�p�����[�^.is�����擾==false�̏ꍇ�A�ȉ��̎w���ǉ�<BR>
     * <BR>
     * �@@�@@�@@" and sonar_traded_code = "16�F����O����""<BR>
     * <BR>
     * (7)�p�����[�^.is����O�����擾==false�̏ꍇ�A�ȉ��̎w���ǉ�<BR>
     * <BR>
     * �@@�@@�@@" and sonar_traded_code != "16�F����O����""<BR>
     * <BR>
     * (8)�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ�A<BR>
     * ���������敪�w���ǉ�<BR>
     *  �i���������P�ʃe�[�u��.�����������ɒl���ݒ肳��Ă���ꍇ�́A<BR>
     * �@@���������������ƂɌ�������B<BR>
     * �@@�����������ɒl���ݒ肳��Ă��Ȃ��ꍇ�́A<BR>
     * �@@���������P�ʃe�[�u��.�������������ƂɌ�������B) <BR>
     * <BR>
     * �@@�@@" and nvl(org_order_condition_type,order_condition_type) = ?"<BR>
     * (9)������C���X�^���X��ԋp<BR>
     * <BR>
     * ��(6)�܂���(7)�́A�Е������s�����^�ǂ�������s����Ȃ��@@�̂����ꂩ�B<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * @@param l_datOrderBizDate - (������)<BR>
     * @@param l_isMarketTrading - (is�����擾)<BR>
     * @@param l_isSalesOutsideMarket - (is����O�����擾)<BR>
     * @@param l_strOrderConditionDiv - (���������敪)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 406403B2027F
     */
    protected String createQueryCond(
        String l_strProductCode,
        String l_strMarketCode,
        Date l_datOrderBizDate,
        boolean l_isMarketTrading,
        boolean l_isSalesOutsideMarket,
        String l_strOrderConditionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createQueryCond(String, String, Date, boolean, boolean, String)";
        log.entering(STR_METHOD_NAME);
        
        // (1)�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�
        StringBuffer l_sbSearchCond = new StringBuffer();
        
        // (2)�������w��𕶎���C���X�^���X�ɐݒ�
        // �@@(2-1)�p�����[�^.��������NULL�i�������w��j�̏ꍇ
        if (l_datOrderBizDate != null)
        {
            l_sbSearchCond.append("biz_date = ?");
        }
        //�@@(2-2)�p�����[�^.��������NULL�i�������w��Ȃ��j�̏ꍇ
        else
        {
            l_sbSearchCond.append("biz_date >= ?");
        }
        
        // (3)������ʎw���ǉ�
        l_sbSearchCond.append(" and order_type in (1, 2)");

        // (4)�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ�A����ID�w���ǉ�
        if (l_strProductCode != null)
        {
            l_sbSearchCond.append(" and product_id = ?");
        }

        //(5)�s��w�������ǉ�����
        //(5-1)�p�����[�^.�s��R�[�h��NULL�i�s��R�[�h�w��j�̏ꍇ�A�s��ID�w���ǉ�����B
        if (l_strMarketCode != null)
        {
            l_sbSearchCond.append(" and market_id = ?");
        }
        else
        {
            //(5-2)�p�����[�^.�s��R�[�h��NULL�i�s��R�[�h�w��Ȃ��j�̏ꍇ�A
            //PTS�����������ΏۊO�Ƃ���B
            //(5-2-1)�@@�i���X�s��ʁEPTS�j�戵����.get�戵�\�s��()���R�[������B
            //���X�F�@@�⏕����.get����X()�̖߂�l �i*1�j
            //�����^�C�v�F�@@"����"
            String[] l_handlingPossibleMarkets =
                WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                    this.getSubAccount().getWeb3GenBranch(),
                    ProductTypeEnum.EQUITY);

            //(5-2-2�j�@@(5-2-1)�Ŏ擾�����s��R�[�h�̔z��̗v�f�� > 0 �̏ꍇ�A
            //�ȉ��̕�������P�j�̕�����ɒǉ�����B
            //"and market_id not in (?, ?, �E�E�E�E�E�E) "
            if (l_handlingPossibleMarkets.length > 0)
            {
                int l_inthandlingPossibleMarketsLength = l_handlingPossibleMarkets.length;

                l_sbSearchCond.append(" and market_id not in (?");
                for (int i = 1; i < l_inthandlingPossibleMarketsLength; i++)
                {
                    l_sbSearchCond.append(", ?");
                }
                l_sbSearchCond.append(")");
            }
        }

        // (6)�p�����[�^.is�����擾==false�̏ꍇ�A�ȉ��̎w���ǉ�
        if (!l_isMarketTrading)
        {

            l_sbSearchCond.append(" and sonar_traded_code = '16'");
        }
        
        // (7)�p�����[�^.is����O�����擾==false�̏ꍇ�A�ȉ��̎w���ǉ�
        if (!l_isSalesOutsideMarket)
        {

            l_sbSearchCond.append(" and sonar_traded_code != '16'");
        }

        // (8)�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ
        if (l_strOrderConditionDiv != null)
        {
            l_sbSearchCond.append(" and nvl(org_order_condition_type,order_condition_type) = ?");
        }

        log.exiting(STR_METHOD_NAME);
        return l_sbSearchCond.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������iwhere�ȉ��w��̕�����j�̃p�����[�^�̕�����z����쐬����B<BR>
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
     * (3)�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ�A<BR>
     * �@@�@@����ID�𕶎���z��ɃZ�b�g�i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@����ID �� �g���v���_�N�g�}�l�[�W��.get����(�،���ЃI�u�W�F�N�g(*2), �p�����[�^.�����R�[�h).����ID<BR>
     * <BR>
     * (4)�s��w�������ǉ�����B<BR>
     * �@@(4-1)�p�����[�^.�s��R�[�h��NULL�i�s��R�[�h�w��j�̏ꍇ�A<BR>
     * �@@�@@�@@�s��ID�𕶎���z��ɃZ�b�g<BR>
     * �@@�@@�i�s��R�[�h�ɑΉ�����s��ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@(4-1-1)���Z�I�u�W�F�N�g�}�l�[�W��.getMarket().getMarketId()�̖߂�l<BR>
     * �@@�@@�@@�����X�g�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�،���ЁF �،���ЃI�u�W�F�N�g(*2)<BR>
     * �@@�@@�@@�@@�s��R�[�h�F �p�����[�^.�s��R�[�h<BR>
     * <BR>
     * <BR>
     * �@@(4-2)�p�����[�^.�s��R�[�h��NULL�i�s��R�[�h�w��Ȃ��j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@PTS�����������ΏۊO�Ƃ��������ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@(4-2-1)�@@�i���X�s��ʁEPTS�j�戵����.get�戵�\�s��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���X�F�@@�⏕����.get����X()�̖߂�l (*3)<BR>
     * �@@�@@�@@�@@�����^�C�v�F�@@"����"<BR>
     * <BR>
     * �@@�@@�@@(4-2-2)�@@�@@(4-2-1)�@@�Ŏ擾�����s��R�[�h�z��̗v�f�� > 0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�z��v�f�����A�ȉ����J��Ԃ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket().getMarketId()�̖߂�l<BR>
     * �@@�@@�@@�@@�����X�g�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�،���ЁF�@@�،���ЃI�u�W�F�N�g(*2)<BR>
     * �@@�@@�@@�@@�s��R�[�h�F�@@(4-2-1)�Ŏ擾�����s��R�[�h�z��̗v�f<BR>
     * <BR>
     * (5)�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ�A<BR>
     * �p�����[�^.���������敪�𕶎���z��ɃZ�b�g<BR>
     * <BR>
     * �@@�@@�@@���������敪 �� �p�����[�^.���������敪<BR>
     * <BR>
     * (6)(2)�A(3)�A(4)�A(5)�ɂăp�����[�^���Z�b�g����������z���ԋp<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem( ).getBizDate( )<BR>
     * (*2)�،���ЃI�u�W�F�N�g�́A�⏕����.getInstitution( )�Ŏ擾���ݒ�<BR>
     * (*3)�⏕������this.get�⏕����()�Ŏ擾<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * @@param l_datOrderBizDate - (������)<BR>
     * @@param l_strOrderConditionDiv - (���������敪)<BR>
     * @@return String[ ]
     * @@throws WEB3BaseException
     * @@roseuid 40640D2A008C
     */
    protected String[] createQueryCondDataContainer(
        String l_strProductCode,
        String l_strMarketCode,
        Date l_datOrderBizDate,
        String l_strOrderConditionDiv)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "createQueryCondDataContainer(String, String, Date, String)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        // (1)�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�
        List l_lisParams = new ArrayList();
        
        // (2)�������w��l�𕶎���z��ɃZ�b�g
        // �@@(2-1)�p�����[�^.��������NULL�i�������w��j�̏ꍇ
        // �@@�@@�@@�������w��l �� �p�����[�^.������
        String l_strBizDate;
        if (l_datOrderBizDate != null)
        {
            l_strBizDate =
                GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datOrderBizDate);
        }
        // �@@(2-2)�p�����[�^.��������NULL�i�������w��Ȃ��j�̏ꍇ
        // �@@�@@�@@�������w��l �� �Ɩ����t(*1)
        else
        {
            WEB3GentradeBizDate l_dateCalc =
                new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp());
            Timestamp l_tsbizDate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
            l_strBizDate = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_tsbizDate);
        }
        l_lisParams.add(l_strBizDate);
        
        try
        {
            // (3)�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ
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

            //(4)�s��w�������ǉ�����B
            //4-1)�p�����[�^.�s��R�[�h��NULL�i�s��R�[�h�w��j�̏ꍇ�A�s��ID�𕶎���z��ɃZ�b�g
            if (l_strMarketCode != null)
            {
                Market l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(
                        l_subAccount.getInstitution(),
                        l_strMarketCode);
                l_lisParams.add(Long.toString(l_market.getMarketId()));
            }
            else
            {
                //(4-2)�p�����[�^.�s��R�[�h��NULL�i�s��R�[�h�w��Ȃ��j�̏ꍇ�A
                //PTS�����������ΏۊO�Ƃ��������ǉ�����B
                //(4-2-1)�@@�i���X�s��ʁEPTS�j�戵����.get�戵�\�s��()���R�[������B
                //���X�F�@@�⏕����.get����X()�̖߂�l
                //�����^�C�v�F�@@"����"
                String[] l_handlingPossibleMarkets =
                    WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                        this.getSubAccount().getWeb3GenBranch(),
                        ProductTypeEnum.EQUITY);

                //(4-2-2)�@@�@@(4-2-1)�@@�Ŏ擾�����s��R�[�h�z��̗v�f�� > 0 �̏ꍇ�A
                //�z��v�f�����A�ȉ����J��Ԃ��B
                //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket().getMarketId()�̖߂�l�����X�g�ɒǉ�����B
                //�،���ЁF�@@�،���ЃI�u�W�F�N�g
                //�s��R�[�h�F�@@(4-2-1)�Ŏ擾�����s��R�[�h�z��̗v�f
                if (l_handlingPossibleMarkets.length > 0)
                {
                    int l_inthandlingPossibleMarketsLength = l_handlingPossibleMarkets.length;

                    for (int i = 0; i < l_inthandlingPossibleMarketsLength; i++)
                    {
                        Market l_market =
                            (WEB3GentradeMarket)l_finObjectManager.getMarket(
                                l_subAccount.getInstitution(),
                                l_handlingPossibleMarkets[i]);

                        l_lisParams.add(Long.toString(l_market.getMarketId()));
                    }
                }
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

        // (5)�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ
        if (l_strOrderConditionDiv != null)
        {
            // �p�����[�^.���������敪�𕶎���z��ɃZ�b�g 
            l_lisParams.add(l_strOrderConditionDiv);
        }
        
        // (6)(2)�A(3)�A(4)�A(5)�ɂăp�����[�^���Z�b�g����������z���ԋp
        String[] l_strParams = new String[l_lisParams.size()];
        l_lisParams.toArray(l_strParams);
        log.exiting(STR_METHOD_NAME);
        return l_strParams;
    }

    /**
     * (create�\�[�g����)<BR>
     * <BR>
     * �P�j�@@�����̃\�[�g�L�[.�L�[���ڂ̐����A�Ή�����e�[�u���̗񕨗����������^�~���w���t�����Z�b�g����B<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�B<BR>
     * �@@�@@�@@���L�[���ڕ�����i�V���{�����j�́A���b�Z�[�W��`�����Q�ƁB<BR>
     * �@@�@@�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q�ƁB<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�i�����j�R�[�h�@@�@@�@@�@@�F�����P�ʃe�[�u���D����ID<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�����@@�@@�@@�@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�ŋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�s��@@�@@�@@�@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�s��ID<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E����敪�@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D������ʁA����R�[�h�iSONAR�j(*1)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�l�i�����@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�l�i����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E���s�����@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D���s����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E���������@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D��������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�������ԁ@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�쐬����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E������   �@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E���������@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�����������t<BR>
     * <BR>
     * �@@�@@�E�����^�~���w��́A�\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ肷��B<BR>
     * <BR>
     * �@@�@@(*1)�����w��̏ꍇ�F�@@�ȉ��̇@@���B�̏��ƂȂ�B<BR>
     * �@@�@@�@@�@@�@@�@@�������=="����������"�{����R�[�h�iSONAR�j=="���ʊ���"<BR>
     * �@@�@@�@@�@@�@@�A�������=="����������"�{����R�[�h�iSONAR�j=="����O����"<BR>
     * �@@�@@�@@�@@�@@�B�������=="����������"�i�������́A����R�[�h�iSONAR�j=="���ʊ���"�Œ�j<BR>
     * �@@�@@�@@�@@�~���w��̏ꍇ�F�@@��L�̇B���@@�̏��ƂȂ�B<BR>
     * <BR>
     * �Q�j�@@�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt������B<BR>
     * <BR>
     * �R�j�@@�쐬�����\�[�g�����������Ԃ��B<BR>
     * <BR>
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * ���N�G�X�g.�\�[�g�L�[
     * @@return java.lang.String
     * @@roseuid 40752DE40150
     */
    protected String createSortCond(WEB3EquitySortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = "createSortCond(WEB3EquitySortKey[])";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbReturn = new StringBuffer();
        // �P�j�@@�����̃\�[�g�L�[.�L�[���ڂ̐����A�Ή�����e�[�u���̗񕨗����������^�~���w���t�����Z�b�g����B
        for (int i = 0; i < l_sortKey.length; i++)
        {
            if(WEB3EquityKeyItemDef.PRODUCTCODE.equals(l_sortKey[i].keyItem))
            {
                //�i�����j�R�[�h �F�����P�ʃe�[�u���D����ID
                l_sbReturn.append("eqtype_order_unit.product_id");
            }
            else if(WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(l_sortKey[i].keyItem))
            {
                //���� �F�����P�ʃe�[�u���D�ŋ敪
                l_sbReturn.append("eqtype_order_unit.tax_type");
            }
            else if(WEB3EquityKeyItemDef.TRADEMARKET.equals(l_sortKey[i].keyItem))
            {
                //�s�� �F�����P�ʃe�[�u���D�s��ID
                l_sbReturn.append("eqtype_order_unit.market_id");
            }
            else if(WEB3EquityKeyItemDef.TRADETYPE.equals(l_sortKey[i].keyItem))
            {
                //����敪�@@�F�����P�ʃe�[�u���D������ʁA����R�[�h�iSONAR�j
                if (WEB3AscDescDef.ASC.equals(l_sortKey[i].ascDesc))
                {
                    l_sbReturn.append("eqtype_order_unit.order_type ASC , eqtype_order_unit.sonar_traded_code�@@ASC , ");
                }
                else
                {
                    l_sbReturn.append("eqtype_order_unit.order_type DESC , eqtype_order_unit.sonar_traded_code�@@DESC , ");
                }
                continue;
            }
            else if(WEB3EquityKeyItemDef.PRICE_COND.equals(l_sortKey[i].keyItem))
            {
                //�l�i�����@@�F�����P�ʃe�[�u���D�l�i����
                l_sbReturn.append("eqtype_order_unit.price_condition_type");
            }
            else if(WEB3EquityKeyItemDef.EXECUTE_COND.equals(l_sortKey[i].keyItem))
            {
                //���s�����@@�F�����P�ʃe�[�u���D���s����
                l_sbReturn.append("eqtype_order_unit.execution_condition_type");
            }
            else if(WEB3EquityKeyItemDef.SEND_COND.equals(l_sortKey[i].keyItem))
            {
                //���������@@�F�����P�ʃe�[�u���D��������
                l_sbReturn.append("eqtype_order_unit.order_condition_type");
            }
            else if(WEB3EquityKeyItemDef.ORDER_TIME.equals(l_sortKey[i].keyItem))
            {
                //�������ԁ@@�F�����P�ʃe�[�u���D�쐬����
                l_sbReturn.append("eqtype_order_unit.created_timestamp");
            }
            else if(WEB3EquityKeyItemDef.SEND_DATE.equals(l_sortKey[i].keyItem))
            {
                //�������@@�F�����P�ʃe�[�u���D������
                l_sbReturn.append("eqtype_order_unit.biz_date");
            }
            else if(WEB3EquityKeyItemDef.ORDER_TIMELIMIT.equals(l_sortKey[i].keyItem))
            {
                //���������@@�F�����P�ʃe�[�u���D�����������t
                l_sbReturn.append("eqtype_order_unit.expiration_date");
            }
            else
            {
                continue;
            }
            if (WEB3AscDescDef.ASC.equals(l_sortKey[i].ascDesc))
            {
                l_sbReturn.append("�@@ASC , ");
            }
            else
            {
                l_sbReturn.append("�@@DESC , ");
            }
        }
        
        // �Q�j�@@�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt������B
        l_sbReturn.append("eqtype_order_unit.last_updated_timestamp ASC");
        
        // �R�j�@@�쐬�����\�[�g�����������Ԃ��B
        log.debug("�\�[�g���� = " + l_sbReturn.toString());
        log.exiting(STR_METHOD_NAME);
        return l_sbReturn.toString();
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
     * @@param l_strExecType (����ԋ敪)<BR>
     * ���N�G�X�g.����ԋ敪�B<BR>
     * null:�w��Ȃ��@@0:�����@@1:�ꕔ�����@@2:�S������<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return boolean
     * @@roseuid 407D3956016D
     */
    protected boolean isExecType(String l_strExecType, EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "isExecType(String, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_bReturn;
        if (l_strExecType == null)
        {
            //����.����ԋ敪��null�̏ꍇ
            log.debug("����.����ԋ敪��null�̏ꍇ");
            l_bReturn = true;
        }
        else
        {
            //����.����ԋ敪��null�̏ꍇ
            log.debug("����.����ԋ敪��null�̏ꍇ");
            String l_execType = WEB3EquityDataAdapter.getExecType(l_orderUnit);
            if (l_execType.equals(l_strExecType))
            {
                //�擾��������ԋ敪������.����ԋ敪�̏ꍇ
                log.debug("�擾��������ԋ敪������.����ԋ敪�̏ꍇ");
                l_bReturn = true;
            }
            else
            {
                //�擾��������ԋ敪������.����ԋ敪�̏ꍇ
                log.debug("�擾��������ԋ敪������.����ԋ敪�̏ꍇ");
                l_bReturn = false;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_bReturn;
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
     * @@return com.fitechlabs.xtrade.plugin.tc.equity.EqTypeOrderUnit[]
     * @@roseuid 407F49D000B6
     */
    protected EqTypeOrderUnit[] getExecutedOrderUnitFromFirstToLast(EqTypeOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutedOrderUnitFromFirstToLast(EqTypeOrderUnit)";
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
     * (get�����P�ʈꗗFrom���N�G�X�g)<BR>
     * �����P�ʃI�u�W�F�N�g���A���N�G�X�g���̌����������w�肵�擾����B<BR>
     * <BR>
     * �P�j�@@this.create��������������(�����̃��N�G�X�g.�����R�[�h, �����̃��N�G�X�g.�s��R�[�h,<BR>
     * �@@�@@�@@�����̃��N�G�X�g.������, ������is�����擾, ������is����O�����擾, �����̃��N�G�X�g.���������敪)�ɂ��A<BR>
     * �@@�@@�@@����������������쐬����B<BR>
     * <BR>
     * �Q�j�@@this.create���������f�[�^�R���e�i(�����̃��N�G�X�g.�����R�[�h, �����̃��N�G�X�g.�s��R�[�h,<BR>
     * �@@�@@�@@�����̃��N�G�X�g.������, �����̃��N�G�X�g.���������敪)�ɂ��A<BR>
     * �@@�@@�@@���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �R�j�@@this.create�\�[�g����(�����̃��N�G�X�g.�\�[�g�L�[)�ɂ��A�\�[�g������������쐬����B<BR>
     * <BR>
     * �S�j�@@�g�����������}�l�[�W��.get�����P�ʈꗗ( )�ɂ��A<BR>
     * �@@�@@�@@���������ɍ��v���钍���P�ʃI�u�W�F�N�g��List���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------<BR>
     * �@@�@@�@@��get�����P�ʈꗗ( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�@@�@@�����^�C�v�F�@@"����"<BR>
     * �@@�@@�@@��������������F�@@create��������������( )�̖߂�l<BR>
     * �@@�@@�@@���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i( )�̖߂�l<BR>
     * �@@�@@�@@�\�[�g�����F�@@create�\�[�g����( )�̖߂�l<BR>
     * �@@�@@�@@----------------------------------------------------<BR>
     * <BR>
     * �T�j�@@�擾����List��Ԃ��B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@param l_isMarketTrading - (is�����擾)<BR>
     * @@param l_isSalesOutsideMarket - (is����O�����擾)<BR>
     * @@return java.util.List
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407FAFBC022D
     */
    protected List getOrderUnitsFromRequest(
        WEB3GentradeSubAccount l_subAccount,
        WEB3EquityExecuteReferenceRequest l_request,
        boolean l_isMarketTrading,
        boolean l_isSalesOutsideMarket)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOrderUnitsFromRequest(WEB3GentradeSubAccount, WEB3EquityExecuteReferenceRequest, boolean, boolean)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisReturn = null;
        
        // �P�j�@@this.create��������������(�����̃��N�G�X�g.�����R�[�h, �����̃��N�G�X�g.�s��R�[�h,
        // �@@�@@�@@�����̃��N�G�X�g.������, ������is�����擾, ������is����O�����擾, �����̃��N�G�X�g.���������敪)�ɂ��A
        // �@@�@@�@@����������������쐬����B
        String l_strFindWhere =
            this.createQueryCond(
                l_request.productCode,
                l_request.marketCode,
                l_request.orderBizDate,
                l_isMarketTrading,
                l_isSalesOutsideMarket,
                l_request.orderCondType);
        log.debug("�������������� l_strFindWhere = " + l_strFindWhere);
        
        // �Q�j�@@this.create���������f�[�^�R���e�i(�����̃��N�G�X�g.�����R�[�h, �����̃��N�G�X�g.�s��R�[�h,
        // �@@�@@�@@�����̃��N�G�X�g.������, �����̃��N�G�X�g.���������敪)�ɂ��A
        // �@@�@@�@@���������f�[�^�R���e�i���쐬����B
        String[] l_strArrCond =
            this.createQueryCondDataContainer(
                l_request.productCode,
                l_request.marketCode,
                l_request.orderBizDate,
                l_request.orderCondType);
        log.debug("���������f�[�^�R���e�i l_strArrCond = " + l_strArrCond);
        
        // �R�j�@@this.create�\�[�g����(�����̃��N�G�X�g.�\�[�g�L�[)�ɂ��A�\�[�g������������쐬����B
        String l_strOrder = null;
        if (l_request.sortKeys != null) {
            l_strOrder = this.createSortCond(l_request.sortKeys);
        }
        
        // �S�j�@@�g�����������}�l�[�W��.get�����P�ʈꗗ( )�ɂ��A
        // �@@�@@�@@���������ɍ��v���钍���P�ʃI�u�W�F�N�g��List���擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        l_lisReturn =
            l_orderManager.getOrderUnits(
                (WEB3GentradeSubAccount)l_subAccount,
                ProductTypeEnum.EQUITY,
                l_strFindWhere,
                l_strArrCond,
                l_strOrder);
        
        log.exiting(STR_METHOD_NAME);
        return l_lisReturn;
    }
}
@
