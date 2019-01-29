head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMarketRequestSenderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �s�ꃊ�N�G�X�g���M�T�[�r�X(WEB3EquityMarketRequestSenderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/06 �{���@@�瑐(SRA) �V�K�쐬
Revesion History : 2006/07/06 �юu�� (���u) �d�l�ύX���f��949
Revesion History : 2006/11/01 ��іQ (���u) ���f�� No.1023
Revesion History : 2007/04/25 �Ӑ�(���u) ���f��1138
Revesion History : 2007/12/17 ����(���u) ���f��1219�A1230�A1244
Revesion History : 2007/12/26 ����(���u) ���f��1273
*/

package webbroker3.equity.marketadaptor;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketRequestSenderService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeSwapContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeNewCashBasedOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeOpenContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeSwapContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeProductImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3CapitalGainTaxTypeDef;
import webbroker3.common.define.WEB3CheckTypeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3FrontOrderTradeCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderDateDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.equity.WEB3EquityCancelOrderConfirmInterceptor;
import webbroker3.equity.WEB3EquityChangeConfirmInterceptor;
import webbroker3.equity.WEB3EquityOrderAcceptPersistenceInterceptor;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3MarginSwapMarginAcceptInterceptor;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.HostEqtypeSwapParams;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * �i�s�ꃊ�N�G�X�g���M�T�[�r�X�j�B<BR>
 * <BR>
 * SONAR�֔������N�G�X�g���s���T�[�r�X�B<BR>
 * �iEqTypeMarketRequestSenderService�̎����j
 * @@version 1.0
 */
public class WEB3EquityMarketRequestSenderServiceImpl
    implements EqTypeMarketRequestSenderService
{

    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityMarketRequestSenderServiceImpl.class);

    /**
     * (�f�[�^�R�[�h�t������)�B
     */
    private static final String DATA_CODE = "T";
    
    /**
     * �i�V�K�������M�j�B<BR>
     * <BR>
     * �V�K�����̑��M���s���B<BR>
     * �isend(EqTypeNewCashBasedOrderMarketRequestMessage)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����~�j�������t�����T�[�r�X�A�����~�j�������t�����T�[�r�X�A<BR>
     * ���������T�[�r�X�A�����J�z�T�[�r�X�A�����ʒm�T�[�r�X���<BR>
     * �R�[�������B<BR>
     * -------------------------------------------------------------------<BR>
     * ���~�j���̏ꍇ<BR>
     * �@@�|�����Ȃ��B<BR>
     * <BR>
     * ���~�j���ȊO�A����Web�V���͒����̏ꍇ�i�����o�H�敪��HOST�j<BR>
     * �@@�|�y���������L���[�e�[�u���z�ɑ΂��A�����f�[�^��Insert������B<BR>
     * �@@�|MQ�g���K�𔭍s����B<BR>
     * <BR>
     * ���~�j���ȊO�A����SONAR���͒����̏ꍇ�i�����o�H�敪��HOST�j<BR>
     * �@@�|�u�V�K������tOK�v�̏������s���A�����X�e�[�^�X��"������"�ɂ���B<BR>
     * -------------------------------------------------------------------<BR>
     * <BR>
     * �P�j�@@�����擾<BR>
     * �@@�������N�G�X�g���b�Z�[�W.getOrderId() �ɂĒ���ID���擾����B<BR>
     * �@@�����}�l�[�W���ɂāA����ID�ɊY�����钍���I�u�W�F�N�g���擾����B<BR>
     * �@@�����I�u�W�F�N�g.getOrderUnits( )��<BR>
     * �@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f�ɂ��āA�ȉ��̏������s���B<BR>
     * <BR>
     * �P�|�P�j�@@�~�j���̏ꍇ<BR>
     * �@@�~�j���̏ꍇ(*1)�A�ȍ~�̏������s�킸�I������ireturn�j�B<BR>
     * �@@�@@(*1) �~�j���̔���<BR>
     * �@@�@@�����P��.������� = OrderTypeEnum.MINI_STOCK_SELL�i�����~�j�����������j Or<BR>
     * �@@�@@�����P��.������� = OrderTypeEnum.MINI_STOCK_BUY�i�����~�j�����������j<BR>
     * <BR>
     * �P�|�Q�j�@@�~�j���łȂ��ꍇ<BR>
     * �@@�擾���������P�ʁD�����o�H�敪��"HOST"�̏ꍇ�i��Web�V���͒����j�̏�����<BR>
     * �V�[�P���X�}�u�i���������s��ظ��āj�V�K�������M�v���Q�ƁB<BR>
     * �@@�擾���������P�ʁD�����o�H�敪��"HOST"�̏ꍇ�i��SONAR���͒����j�̏�����<BR>
     * �V�[�P���X�}�u�i���������s��ظ��āj�V�K�������M�i�����ʒm�j�v���Q�ƁB
     * @@param l_requestMessage �i�����������N�G�X�g���b�Z�[�W�j<BR>
     * �����������N�G�X�g���b�Z�[�W
     * @@return MarketRequestSendResult
     */
    public MarketRequestSendResult send(EqTypeNewCashBasedOrderMarketRequestMessage l_requestMessage)
    {
        final String STR_METHOD_NAME =
            "send(EqTypeNewCashBasedOrderMarketRequestMessage)";
        log.entering(STR_METHOD_NAME);
        long l_lngMsgTokenId = 0;

        try
        {
            long l_lngOrderId = l_requestMessage.getOrderId();
    
            String l_strInstitutionCode = null;
            boolean l_isTriggerIssue = false;
            String l_strOrderConditionType = null;
            String l_strOrderRootDiv = null;
    
            EqtypeOrderUnitRow l_eqtypeOderUnitRow = null;
    
            WEB3MQMessageSpec l_web3MQMessageSpec = null;
            WEB3MQGatewayService l_web3MQGatewayService;
            WEB3MQSendResult l_web3MQSendResult;
    
            DefaultNewOrderAcceptedMarketResponseMessage l_newOrderResponseMessage =
                null;
    
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
    
            AccountManager l_accountManager = l_finApp.getAccountManager();
    
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

            //�g�������}�l�[�W�����擾
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            if (l_requestMessage == null)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //�����擾
            OrderUnit[] l_orderUnits = l_equityOrderManager.getOrderUnits(l_lngOrderId);

            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];

            l_eqtypeOderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            // �P�|�P�j�@@�~�j���̏ꍇ <BR>                                                       
            // �@@�@@�����P��.������� = OrderTypeEnum.MINI_STOCK_SELL�i�����~�j�����������jOr
            // �@@�@@�����P��.������� = OrderTypeEnum.MINI_STOCK_BUY�i�����~�j����������)
            if(l_orderUnit.getOrderType().equals(OrderTypeEnum.MINI_STOCK_SELL) 
            || l_orderUnit.getOrderType().equals(OrderTypeEnum.MINI_STOCK_BUY))
            {
                
                log.debug("�~�j���̏ꍇ");
                return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                   
            }
            //�P�|�Q�j�@@�~�j���łȂ��ꍇ 
            else
            {
                //�����o�H�敪
                l_strOrderRootDiv = l_eqtypeOderUnitRow.getOrderRootDiv();

                log.debug(
                    "�����o�H�敪" + WEB3OrderRootDivDef.HOST + "?=" + l_strOrderRootDiv);
                if (!WEB3OrderRootDivDef.HOST.equals(l_strOrderRootDiv))
                {
                    log.debug("�����o�H�敪��'HOST'�̏ꍇ�i��Web�V���͒����j");
                    //�擾���������P�ʁD�����o�H�敪��"HOST"�̏ꍇ�i��Web�V���͒����j

                    l_equityOrderManager.notifyRLS(
                        l_orderUnit,
                        OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER);

                    if (WEB3OrderingConditionDef
                        .STOP_LIMIT_PRICE
                        .equals(l_eqtypeOderUnitRow.getOrderConditionType()))
                    {
                        log.debug("�����P��.�����������h�t�w�l�h�̏ꍇ�́A�����L���[�ɓo�^���Ȃ��B");
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
                    }

                    //���X���擾����
                    Branch l_banch =
                        l_accountManager.getBranch(
                            l_eqtypeOderUnitRow.getBranchId());
                    //�،���ЃR�[�h���擾����
                    l_strInstitutionCode =
                        l_banch.getInstitution().getInstitutionCode();
                    
                    l_equityOrderManager.insertEquityHostOrder(l_lngOrderId);

                    // �����o�H�敪
                    String l_strSubmitOrderRouteDiv =
                        l_eqtypeOderUnitRow.getSubmitOrderRouteDiv();
                    
                    // �s��I�u�W�F�N�g����s��R�[�h�����
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                    WEB3GentradeFinObjectManager l_finObjectManager =
                        (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                    Market l_market =
                        l_finObjectManager.getMarket(l_eqtypeOderUnitRow.getMarketId());
                    String l_strMarketCode = l_market.getMarketCode();

                    // getMarket()�̖߂�l.getMarketCode() == "PTS�s��"�̏ꍇ
                    if (WEB3MarketCodeDef.JNX_PTS.equals(l_strMarketCode))
                    {
                        log.exiting(STR_METHOD_NAME);
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                    }

                    // �t�����g�����V�X�e���敪
                    EqTypeTradedProduct l_tradedProduct =
                        (EqTypeTradedProduct)l_orderUnit.getTradedProduct();
                    EqtypeTradedProductRow l_tradedProductRow =
                        (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                    String l_strFrontOrderSystemCode =
                        l_frontOrderService.getFrontOrderSystemCode(
                            l_strMarketCode,
                            l_tradedProductRow.getOpenOtcDiv());

                    //�����������擾
                    l_strOrderConditionType =
                        l_eqtypeOderUnitRow.getOrderConditionType();

                    l_isTriggerIssue =
                        WEB3GentradeTradingTimeManagement
                            .isSubmitMarketTrigger(
                            l_strOrderConditionType);

                    boolean l_isSubmitMqTrigger =
                        WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                            l_strInstitutionCode,
                            l_eqtypeOderUnitRow.getProductType(),
                            l_strMarketCode,
                            l_strSubmitOrderRouteDiv,
                            l_strFrontOrderSystemCode);
                    
                    if (l_isTriggerIssue && l_isSubmitMqTrigger)
                    {
                        String l_strMQDataCode =
                            l_frontOrderService.getOrderMQDataCode(l_strSubmitOrderRouteDiv);
                        if (l_strMQDataCode == null)
                        {
                            log.debug("�V�K�������M �����i������~���j�I�I�I");
                            return DefaultMarketRequestSendResult
                                .newSuccessResultInstance(
                                l_lngMsgTokenId);
                        }

                        l_web3MQGatewayService =
                            (WEB3MQGatewayService)Services.getService(
                                WEB3MQGatewayService.class);

                        l_web3MQMessageSpec =
                            new WEB3MQMessageSpec(
                                l_strInstitutionCode,
                                l_strMQDataCode);

                        l_web3MQSendResult =
                            l_web3MQGatewayService.send(l_web3MQMessageSpec);

                        if (l_web3MQSendResult.isSuccessResult())
                        {
                            log.debug("�V�K�������M �����I�I�I");
                            return DefaultMarketRequestSendResult
                                .newSuccessResultInstance(
                                l_lngMsgTokenId);
                        }
                        else
                        {
                            log.debug("�V�K�������M ���s �I�I�I");
                            ProcessingResult l_processingResult =
                                ProcessingResult.newFailedResultInstance(
                                    l_web3MQSendResult.getErrorInfo());
                            return DefaultMarketRequestSendResult
                                .newFailedResultInstance(
                                l_processingResult);
                        }
                    }
                }
                else
                {
                    log.debug("�����o�H�敪��'HOST'�̏ꍇ�i��SONAR���͒����j");
                    //�擾���������P��.�����o�H�敪��"HOST"�̏ꍇ�i��SONAR���͒����j
                    WEB3EquityOrderAcceptPersistenceInterceptor l_orderAccepterInterceptor =
                        new WEB3EquityOrderAcceptPersistenceInterceptor(null);
                    l_equityOrderManager.setThreadLocalPersistenceEventInterceptor(l_orderAccepterInterceptor);
                    EqTypeMarketResponseReceiverCallbackService l_callBackService =
                        (EqTypeMarketResponseReceiverCallbackService)l_tradingModule
                            .getMarketAdapter()
                            .getMarketResponseReceiverCallbackService();
                    l_newOrderResponseMessage =
                        new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
                    ProcessingResult l_processingResult =
                        l_callBackService.process(
                            (MarketResponseMessage)l_newOrderResponseMessage);
                    if (l_processingResult.isFailedResult())
                    {
                        return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                            l_processingResult);
                    }
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(
                        l_lngMsgTokenId);
                }
            }
        }
        catch (NotFoundException nfe)
        {
            WEB3SystemLayerException l_sysException =
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            log.error("__an unexpected error__", l_sysException);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                    l_sysException.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);

        }
        catch (WEB3BaseException wbe)
        {
            log.error("__an unexpected error__", wbe);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(wbe.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }

        log.exiting(STR_METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(
            l_lngMsgTokenId);
    }

    /**
     * �i����������M�j�B<BR>
     * <BR>
     * ��������̑��M���s���B<BR>
     * �isend(CancelOrderMarketRequestMessage, boolean)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ��������T�[�r�X�A�M�p�������T�[�r�X���<BR>
     * �R�[�������B<BR>
     * -------------------------------------------------------------------<BR>
     *   �|����Ώے������~�j�������̏ꍇ�́A����������return����B<BR>
     * <BR>
     * �@@�|����Ώے������s�ꖢ���M�̏ꍇ<BR>
     * �@@�@@�@@�E�������n�����Ȃ�΁A�y�������n�L���[�e�[�u���z����Ώۃf�[�^��Delete����B<BR>
     * �@@�@@�@@�E�������n�����ȊO�Ȃ�΁A�y���������L���[�e�[�u���z����Ώۃf�[�^��Delete����B<BR>
     * <BR>
     * �@@�|����Ώے������s�ꑗ�M�ς̏ꍇ<BR>
     * �@@�@@�@@�E�s��J�ǎ��ԑ�(�z�X�g���M���ԑ�)�̏ꍇ�A�ΏۃL���[�e�[�u��(*)�Ɏ���f�[�^��Insert���A<BR>
     * �@@�@@�@@�@@MQ�g���K�𔭍s����B<BR>
     * �@@�@@�@@�@@(*)�ΏۃL���[�e�[�u��<BR>
     * �@@�@@�@@�@@�@@�������n�����Ȃ�΁A�y�������n�L���[�e�[�u���z<BR>
     * �@@�@@�@@�@@�@@�������n�����ȊO�Ȃ�΁A�y����������������L���[�e�[�u���z<BR>
     * �@@�@@�@@�E�s��ǎ��ԑ�(�z�X�g���M���ԑъO)�̏ꍇ�A������m�肳����B<BR>
     * -------------------------------------------------------------------<BR>
     * <BR>
     * �P�j�@@�����擾<BR>
     * �@@��������������N�G�X�g���b�Z�[�W.getOrderId() �ɂĒ���ID���擾����B<BR>
     * �@@�����}�l�[�W���ɂāA����ID�ɊY�����钍���I�u�W�F�N�g���擾����B<BR>
     * �@@�����I�u�W�F�N�g.getOrderUnits( )��<BR>
     * �@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f�ɂ��āA�~�j���ȊO�̏ꍇ�͈ȉ��̏������s���B<BR>
     * <BR>
     * �ȉ��A�������s�ꖢ���M�����M�ς��ɂ��A�������������B<BR>
     * <BR>
     * �������s�ꖢ���M�̏ꍇ�i����.is�s�ꖢ���M == true�j�́A<BR>
     * �V�[�P���X�}�u�i��������_�M�p����s��ظ��āj����������M�i�s�ꖢ���M�����j�v���Q�ƁB<BR>
     * �������s�ꑗ�M�ς̏ꍇ�i����.is�s�ꖢ���M == false�j�́A<BR>
     * �V�[�P���X�}�u�i��������_�M�p����s��ظ��āj����������M�i�s�ꑗ�M�ϒ����j�v���Q�ƁB
     * @@param l_requestMessage �i��������������N�G�X�g���b�Z�[�W�j<BR>
     * ��������������N�G�X�g���b�Z�[�W
     * @@param l_isUnSend �iis�s�ꖢ���M�j<BR>
     * ���������s�ꖢ���M�̏ꍇ��true�A<BR>
     * ���������s�ꑗ�M�ς̏ꍇ��false���w�肷��B<BR>
     * false�̏ꍇ�ASONAR�֎����ʒm����B<BR>
     * @@return MarketRequestSendResult
     * @@throws TooLateException
     */
    public MarketRequestSendResult send(
        CancelOrderMarketRequestMessage l_requestMessage,
        boolean l_isUnSend)
        throws TooLateException
    {
        final String STR_METHOD_NAME =
            "send(CancelOrderMarketRequestMessage,boolean)";
        log.entering(STR_METHOD_NAME);

        long l_lngMsgTokenId = 0L;
        boolean l_isTriggerIssue = false;

        try
        {
            long l_lngOrderId = l_requestMessage.getOrderId();
        
            if (l_requestMessage == null)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            AccountManager l_accountManager = l_finApp.getAccountManager();

            //�����擾
            Order l_order = new EqTypeOrderImpl(l_lngOrderId);
            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];
            EqtypeOrderUnitRow l_eqtypeOderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                
            // �~�j���̏ꍇ <BR>                                                       
            // �@@�@@�����P��.������� = OrderTypeEnum.MINI_STOCK_SELL�i�����~�j�����������jOr
            // �@@�@@�����P��.������� = OrderTypeEnum.MINI_STOCK_BUY�i�����~�j����������)
            if(l_orderUnit.getOrderType().equals(OrderTypeEnum.MINI_STOCK_SELL) 
            || l_orderUnit.getOrderType().equals(OrderTypeEnum.MINI_STOCK_BUY))
            {
                log.debug("�~�j���̏ꍇ");
                return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
            }

            //�ڋq�R�[�h���擾����
            MainAccount l_mainAccount = l_mainAccount = l_accountManager
                .getMainAccount(l_eqtypeOderUnitRow.getAccountId());
            MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
            String l_strAccountCode = l_mainAccount.getAccountCode();
            
            EqTypeProduct l_eqtypeProduct = (EqTypeProduct)l_tradingModule.getProductManager().getProduct(l_eqtypeOderUnitRow.getProductId());
            String l_strPoductCode = l_eqtypeProduct.getProductCode();
            //���ʃR�[�h���擾����
            String l_strOrderRequestNumber =
                l_eqtypeOderUnitRow.getOrderRequestNumber();
            //���X���擾����
            Branch l_banch =
                l_accountManager.getBranch(l_eqtypeOderUnitRow.getBranchId());
            //�،���ЃR�[�h���擾����
            String l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            //���X�R�[�h���擾����
            String l_strBranchCode = l_banch.getBranchCode();

            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            // �s��R�[�h���擾
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_eqtypeOderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();

            if (l_isUnSend)
            {
                try 
                {
                    l_orderManager.notifyRLS(
                        (EqTypeOrderUnit)l_orderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_CONFIRMED_BY_MKT);
                }
                catch (WEB3BusinessLayerException l_ble)
                {
                    log.debug("error in  l_orderMgr.notifyRLS", l_ble);
                }

                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_eqtypeOderUnitRow.getOrderConditionType())
                    && !(WEB3RequestTypeDef.QUOTE_SERVER.equals(l_eqtypeOderUnitRow.getRequestType())))
                {
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(
                        l_lngMsgTokenId);
                }

                QueryProcessor processor = Processors.getDefaultProcessor();
                log.debug("==> �������n�����̏ꍇ�̂݁A���{����B");
                if(OrderCategEnum.SWAP_MARGIN.equals(l_eqtypeOderUnitRow.getOrderCateg()))
                {
                    String l_strWhere =" request_code=? and institution_code=? and branch_code=? and order_request_number=? and status=?";
                    String[] l_strBindValues = new String[5];
                    l_strBindValues[0] = WEB3HostRequestCodeDef.EQUITY_SPOT_ORDER;
                    l_strBindValues[1] = l_strInstitutionCode;
                    l_strBindValues[2] = l_strBranchCode;
                    l_strBindValues[3] = l_strOrderRequestNumber;
                    l_strBindValues[4] = WEB3HostStatusDef.NOT_STARTED_PROCESS;

                    //�Ώۃf�[�^�폜
                    int l_intResults = processor.doDeleteAllQuery(
                                                HostEqtypeSwapParams.TYPE,
                                                l_strWhere,
                                                l_strBindValues);
                    if (l_intResults == 0)
                    {
                        return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                                ProcessingResult.newFailedResultInstance(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00010));
                    }
                }
                else
                {
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                    int l_intIndex = l_frontOrderService.getIndexOfOrderRevInCorpCode();
                    int l_intFigure = l_frontOrderService.getFigureOfOrderRev();
                    String l_strWhere =" request_code=? and institution_code=? and branch_code=? and order_request_number=? and substr(corp_code," + l_intIndex + "," + l_intFigure + ")=? and status=?";
                    String[] l_strBindValues = new String[6];
                    l_strBindValues[0] = WEB3HostRequestCodeDef.EQUITY_ORDER;
                    l_strBindValues[1] = l_strInstitutionCode;
                    l_strBindValues[2] = l_strBranchCode;
                    l_strBindValues[3] = l_strOrderRequestNumber;
                    l_strBindValues[4] = l_eqtypeOderUnitRow.getOrderRev();
                    l_strBindValues[5] = WEB3FrontOrderStatusDef.NOT_DEAL;

                    //�Ώۃf�[�^�폜
                    int l_intResults = processor.doDeleteAllQuery(
                            HostEqtypeOrderAllParams.TYPE,
                            l_strWhere,
                            l_strBindValues);
                    if (l_intResults == 0)
                    {
                        return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                                ProcessingResult.newFailedResultInstance(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00010));
                    }
                }
            }
            else
            {
                log.debug("==> �s�ꑗ�M�ρiis�s�ꖢ���M == false�j�̏ꍇ�̏���");

                // getMarket()�̖߂�l.�s��R�[�h == "PTS�s��"�̏ꍇ
                if (WEB3MarketCodeDef.JNX_PTS.equals(l_strMarketCode))
                {
                    // ������������L���[�e�[�u���֓o�^����B
                    // �o�^���e�́ADB�X�V�d�l
                    //�u(PTS)�����������_������������L���[�e�[�u��.xls�v �Q�ƁB

                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);

                    HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();

                    // �f�[�^�R�[�h
                    l_hostEqtypeOrderAllParams.setRequestCode(
                        WEB3HostRequestCodeDef.EQUITY_ORDER_CHANGE);
                    // ����ID
                    l_hostEqtypeOrderAllParams.setAccountId(
                        l_eqtypeOderUnitRow.getAccountId());
                    // �،���ЃR�[�h
                    l_hostEqtypeOrderAllParams.setInstitutionCode(
                        l_strInstitutionCode);
                    // ���X�R�[�h
                    l_hostEqtypeOrderAllParams.setBranchCode(
                        l_strBranchCode);
                    // �����R�[�h
                    l_hostEqtypeOrderAllParams.setAccountCode(
                        l_strAccountCode);
                    // ���҃R�[�h�iSONAR�j
                    l_hostEqtypeOrderAllParams.setSonarTraderCode(
                        l_eqtypeOderUnitRow.getSonarTraderCode());
                    // ���ʃR�[�h
                    l_hostEqtypeOrderAllParams.setOrderRequestNumber(
                        l_strOrderRequestNumber);
                    // ��������ԍ�
                    l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                        l_eqtypeOderUnitRow.getLastOrderActionSerialNo());
                    // �����R�[�h
                    l_hostEqtypeOrderAllParams.setProductCode(
                        l_strPoductCode);
                    // �󒍓���
                    l_hostEqtypeOrderAllParams.setReceivedDateTime(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    // �����o�H�敪
                    l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                        l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                    if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                    {
                        // ���t����
                        l_hostEqtypeOrderAllParams.setSellOrderQuantity(
                            l_eqtypeOderUnitRow.getConfirmedQuantity());
                        // ���t����
                        l_hostEqtypeOrderAllParams.setBuyOrderQuantity(0D);
                    }
                    else
                    {
                        // ���t����
                        l_hostEqtypeOrderAllParams.setSellOrderQuantity(0D);
                        // ���t����
                        l_hostEqtypeOrderAllParams.setBuyOrderQuantity(
                            l_eqtypeOderUnitRow.getConfirmedQuantity());
                    }
                    // �w�l
                    l_hostEqtypeOrderAllParams.setLimitPrice(
                        l_eqtypeOderUnitRow.getConfirmedPrice());
                    // ���s�����iSONAR�j
                    String l_strConfirmedExecConditionType = null;
                    if (l_orderManager.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
                    {
                        l_strConfirmedExecConditionType =
                            WEB3ExecutionConditionDef.COME_TO_TERMS;
                    }
                    else
                    {
                        l_strConfirmedExecConditionType =
                        l_orderManager.getExecutionConditionTypeSonar(
                                l_eqtypeOderUnitRow.getConfirmedExecConditionType());
                    }
                    l_hostEqtypeOrderAllParams.setExecutionCondition(
                        l_strConfirmedExecConditionType);
                    // �l�i�����iSONAR�j
                    l_hostEqtypeOrderAllParams.setPriceConditionType(
                        l_orderManager.getPriceConditionTypeSonar(
                            l_eqtypeOderUnitRow.getConfirmedPriceConditionType()));
                    // ����敪
                    l_hostEqtypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.CANCEL);
                    // �������
                    String l_strLastUpdatedDate =
                        WEB3DateUtility.formatDate(
                            l_eqtypeOderUnitRow.getLastUpdatedTimestamp(), WEB3GentradeTimeDef.TIME_FORMAT_HM);
                    l_hostEqtypeOrderAllParams.setCancelOrderTime(l_strLastUpdatedDate);
                    // ����敪�i�O���^�����j
                    Timestamp l_tsCurTime = GtlUtils.getTradingSystem().getSystemTimestamp();

                    String l_strReceivedDateTime = WEB3DateUtility.formatDate(
                        l_tsCurTime,
                        WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                    String l_strCancelOrderDateDiv = null;

                    if (WEB3Toolkit.isEquals(l_strReceivedDateTime, l_eqtypeOderUnitRow.getBizDate()))
                    {
                        l_strCancelOrderDateDiv = WEB3OrderDateDivDef.TODAY;
                    }
                    else
                    {
                        l_strCancelOrderDateDiv = WEB3OrderDateDivDef.YESTERDAY;
                    }

                    l_hostEqtypeOrderAllParams.setCancelOrderDateDiv(l_strCancelOrderDateDiv);
                    // �t�����g����������敪�R�[�h
                    l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode(
                        l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
                    // �t�����g�����V�X�e���敪
                    EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
                    EqTypeTradedProduct l_tradedProduct =
                        (EqTypeTradedProduct)l_eqtypeOrderUnit.getTradedProduct();
                    EqtypeTradedProductRow l_tradedProductRow =
                        (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                    String l_strFrontOrderSystemCode =
                        l_frontOrderService.getFrontOrderSystemCode(
                            l_strMarketCode,
                            l_tradedProductRow.getOpenOtcDiv());
                    l_hostEqtypeOrderAllParams.setFrontOrderSystemCode(
                        l_strFrontOrderSystemCode);
                    // �t�����g��������敪�R�[�h
                    l_hostEqtypeOrderAllParams.setFrontOrderTradeCode(
                        WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                    // �Г���������
                    l_hostEqtypeOrderAllParams.setCorpCode(
                        l_frontOrderService.getOrgCorpCode(l_eqtypeOrderUnit));
                    // �i������j�Г���������
                    l_hostEqtypeOrderAllParams.setOrgCorpCode(
                        l_frontOrderService.getOrgCorpCode(l_eqtypeOrderUnit));
                    // �S���������敪
                    l_hostEqtypeOrderAllParams.setAllOrderChangeDiv(
                        WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                    // �����敪�i�X�e�[�^�X�j
                    l_hostEqtypeOrderAllParams.setStatus(
                        WEB3FrontOrderStatusDef.NOT_DEAL);

                    // �f�[�^�}������
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    l_processor.doInsertQuery(l_hostEqtypeOrderAllParams);

                    log.exiting(STR_METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }

                try 
                {
                    l_orderManager.notifyRLS(
                        (EqTypeOrderUnit)l_orderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED);
                }
                catch (WEB3BusinessLayerException l_ble)
                {
                    log.error("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ");
                }
                
                ProcessingResult l_processingResult;

                double l_quantity = l_orderUnit.getQuantity();
                String l_strTraderCode = l_eqtypeOderUnitRow.getSonarTraderCode();
				Timestamp l_tsCurTime = GtlUtils.getTradingSystem().getSystemTimestamp();
                String l_strReceivedDateTime = WEB3DateUtility.formatDate(l_tsCurTime,"yyyyMMdd");
                String l_strCancelOrderDateDiv;
                if (l_strReceivedDateTime.equals(l_eqtypeOderUnitRow.getBizDate()))
                {
                    l_strCancelOrderDateDiv = WEB3OrderDateDivDef.TODAY;
                }
                else
                {
                    l_strCancelOrderDateDiv = WEB3OrderDateDivDef.YESTERDAY;
                }      
     
                String l_strLastUpdatedDate =
                    WEB3DateUtility.formatDate(
                        l_eqtypeOderUnitRow.getLastUpdatedTimestamp(), "HHmm");

                if (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
                {
                    log.debug("==> �s��J�ǎ��ԑт̏ꍇ");
                    
                    //�s��J�ǎ��ԑт̏ꍇ
                    if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()))
                    {
                        //�������n�����̏ꍇ
                        HostEqtypeSwapParams l_hostEqtypeSwapParams =
                            new HostEqtypeSwapParams();

                        //set�f�[�^�R�[�h
                        l_hostEqtypeSwapParams.setRequestCode(
                            WEB3HostRequestCodeDef.EQUITY_SPOT_ORDER_CHANGE_CANCEL);                            
                        //�ڋqID
                        l_hostEqtypeSwapParams.setAccountId(l_eqtypeOderUnitRow.getAccountId());
                        //set�،���ЃR�[�h
                        l_hostEqtypeSwapParams.setInstitutionCode(
                            l_strInstitutionCode);
                        //set���X�R�[�h
                        l_hostEqtypeSwapParams.setBranchCode(
                            l_strBranchCode);
                        //set�ڋq�R�[�h
                        l_hostEqtypeSwapParams.setAccountCode(
                            l_strAccountCode);
                        //set���҃R�[�h
                        l_hostEqtypeSwapParams.setSonarTraderCode(
                            l_strTraderCode);
                        //set�����R�[�h
                        l_hostEqtypeSwapParams.setProductCode(
                            l_strPoductCode);
                        //set���n����
                        if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderUnit.getOrderType()))
                        {
                            l_hostEqtypeSwapParams.setSellOrderQuantity(l_quantity);
                        }
                        else
                        {
                            l_hostEqtypeSwapParams.setSellOrderQuantity(0);
                        }
                        //set��������
                        if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderUnit.getOrderType()))
                        {
                            l_hostEqtypeSwapParams.setBuyOrderQuantity(l_quantity);
                        }
                        else
                        {
                            l_hostEqtypeSwapParams.setBuyOrderQuantity(0);
                        }
                        //set�ٍϋ敪�iSONAR�j
                        l_hostEqtypeSwapParams.setSonarRepaymentType(l_eqtypeOderUnitRow.getSonarRepaymentType());
                        //set�s��R�[�h�iSONAR�j
                        l_hostEqtypeSwapParams.setSonarMarketCode(l_eqtypeOderUnitRow.getSonarMarketCode());
                        //set�`�[��
                        l_hostEqtypeSwapParams.setTicketNumber(l_eqtypeOderUnitRow.getVoucherNo());
                        //set���n�v�ŋ敪
                        // ���������̏ꍇ�F�@@0�F�Ȃ�
                        // ���n�����̏ꍇ�F
						// �@@�l�q�i�ڋq.�����^�C�v==("�l�A�J�E���g", "���p�A�J�E���g")�j�ł��A
						//�@@�@@�@@�@@���Z�ҁA���ʔ񋏏Z�҂̏ꍇ�F�@@1�F�\��
						//�@@�@@�@@�@@�񋏏Z�҂̏ꍇ�F�@@�@@�@@�@@�@@�@@�@@0�F�Ȃ�
						// �@@�@@�l�q�i�ڋq.�����^�C�v=="�@@�l�A�J�E���g"�j�̏ꍇ�F�@@0�F�Ȃ�
						if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderUnit.getOrderType()))
						{
							l_hostEqtypeSwapParams.setCapitalGainTaxType(
								WEB3CapitalGainTaxTypeDef.NOTHING);
						}
						else if (
							MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.equals(l_mainAccountRow.getAccountType()) ||
							MainAccountTypeEnum.JOINT_OWNERSHIP.equals(l_mainAccountRow.getAccountType()))
						{
							if (WEB3ResidentDef.RESIDENT.equals(l_mainAccountRow.getResident()) ||
								WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_mainAccountRow.getResident()))
							{
								l_hostEqtypeSwapParams.setCapitalGainTaxType(
									WEB3CapitalGainTaxTypeDef.REPORT);
							}
							else if (WEB3ResidentDef.NON_RESIDENT.equals(l_mainAccountRow.getResident()))
							{
								l_hostEqtypeSwapParams.setCapitalGainTaxType(
									WEB3CapitalGainTaxTypeDef.NOTHING);
							}
						}
                        else if(
                            MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountRow.getAccountType()))
                        {
                            l_hostEqtypeSwapParams.setCapitalGainTaxType(
                                WEB3CapitalGainTaxTypeDef.NOTHING);
                        }
                        
                        //set����
                        l_hostEqtypeSwapParams.setCheckType(WEB3CheckTypeDef.PRE_CHECK);
                        //set���
                        l_hostEqtypeSwapParams.setCancelDiv(WEB3CancelDivDef.CANCEL);
                        //set���ʃR�[�h
                        l_hostEqtypeSwapParams.setOrderRequestNumber(l_strOrderRequestNumber);
                        //set��������ԍ� �F ���������P��.���������ŏI�ʔ�
                        l_hostEqtypeSwapParams.setOrderActionSerialNo(
                            l_eqtypeOderUnitRow.getLastOrderActionSerialNo());
                        //set�󒍓���
                        l_hostEqtypeSwapParams.setCreateDatetime(l_tsCurTime);
                        //set�ŋ敪�i��������敪�j
                        if (TaxTypeEnum.NORMAL.equals(l_eqtypeOderUnitRow.getTaxType()))
                        {
                            l_hostEqtypeSwapParams.setTaxType(WEB3TaxTypeDef.NORMAL);
                        }
                        else
                        {
                            l_hostEqtypeSwapParams.setTaxType(WEB3TaxTypeDef.SPECIAL);
                        }
                        //set�ŋ敪�i�������n������������敪�j
                        if (TaxTypeEnum.NORMAL.equals(l_eqtypeOderUnitRow.getSwapTaxType()))
                        {
                            l_hostEqtypeSwapParams.setSwapTaxType(WEB3TaxTypeDef.NORMAL);
                        }
                        else
                        {
                            l_hostEqtypeSwapParams.setSwapTaxType(WEB3TaxTypeDef.SPECIAL);
                        }
                        //set�����敪(0�F������)
                        l_hostEqtypeSwapParams.setStatus(WEB3StatusDef.NOT_DEAL);

                        //�f�[�^�}������
                        QueryProcessor processor = Processors.getDefaultProcessor();
                        processor.doInsertQuery(l_hostEqtypeSwapParams);
                        
                        l_isTriggerIssue =
                            WEB3GentradeTradingTimeManagement
                                .isSubmitMarketTrigger(
                                null);
                    
                        if (l_isTriggerIssue)
                        {
                            log.debug("==> is�g���K���s()���R�[��==true");
                            WEB3MQMessageSpec l_web3MQMessageSpec =
                                new WEB3MQMessageSpec(
                                    l_strInstitutionCode,
                                    WEB3HostRequestCodeDef.EQUITY_SPOT_ORDER_CHANGE_CANCEL + DATA_CODE);
                             WEB3MQGatewayService l_web3MQGatewayService =
                                 (WEB3MQGatewayService)Services.getService(
                                     WEB3MQGatewayService.class);
                             WEB3MQSendResult l_web3MQSendResult =
                                 l_web3MQGatewayService.send(l_web3MQMessageSpec); 
                    
                            if (l_web3MQSendResult.isSuccessResult())
                            {
                                log.debug("==> ����������M �����I�I�I");
                                return DefaultMarketRequestSendResult
                                        .newSuccessResultInstance(l_lngMsgTokenId);
                            }
                            else
                            {
                                log.debug("==> ����������M ���s �I�I�I");
                                l_processingResult =
                                    ProcessingResult.newFailedResultInstance(l_web3MQSendResult.getErrorInfo());
                                    return DefaultMarketRequestSendResult
                                        .newFailedResultInstance(
                                        l_processingResult);
                            }
                        }
                    }
                    else
                    {
                        //�������n�����ȊO�̏ꍇ
                        WEB3EquityFrontOrderService l_frontOrderService =
                            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = null;
                        if (WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
                        {
                            l_hostEqtypeOrderAllParams =
                                l_frontOrderService.getHostEqtypeOrderAll(
                                    (EqTypeOrderUnit)l_orderUnit);
                        }
                        
                        if (l_hostEqtypeOrderAllParams == null)
                        {
                            l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
                            
                            // �f�[�^�R�[�h
                            l_hostEqtypeOrderAllParams.setRequestCode(
                                WEB3HostRequestCodeDef.EQUITY_ORDER_CHANGE);
                            // ����ID
                            l_hostEqtypeOrderAllParams.setAccountId(
                                l_eqtypeOderUnitRow.getAccountId());
                            // �،���ЃR�[�h
                            l_hostEqtypeOrderAllParams.setInstitutionCode(
                                l_strInstitutionCode);
                            // ���X�R�[�h
                            l_hostEqtypeOrderAllParams.setBranchCode(
                                l_strBranchCode);
                            // �����R�[�h
                            l_hostEqtypeOrderAllParams.setAccountCode(
                                l_strAccountCode);
                            // ���҃R�[�h�iSONAR�j
                            l_hostEqtypeOrderAllParams.setSonarTraderCode(
                                l_strTraderCode);
                            // ���ʃR�[�h
                            l_hostEqtypeOrderAllParams.setOrderRequestNumber(
                                l_strOrderRequestNumber);
                            // ��������ԍ�
                            l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                                l_eqtypeOderUnitRow.getLastOrderActionSerialNo());
                            // �����R�[�h
                            l_hostEqtypeOrderAllParams.setProductCode(
                                l_strPoductCode);
                            // �󒍓���
                            l_hostEqtypeOrderAllParams.setReceivedDateTime(l_tsCurTime);
                            // �����o�H�敪
                            l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                                l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                            if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                            {
                                // ���t����
                                l_hostEqtypeOrderAllParams.setSellOrderQuantity(
                                    l_eqtypeOderUnitRow.getConfirmedQuantity());
                                // ���t����
                                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(0D);
                            }
                            else
                            {
                                // ���t����
                                l_hostEqtypeOrderAllParams.setSellOrderQuantity(0D);
                                // ���t����
                                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(
                                    l_eqtypeOderUnitRow.getConfirmedQuantity());
                            }
                            // �w�l
                            l_hostEqtypeOrderAllParams.setLimitPrice(
                                l_eqtypeOderUnitRow.getConfirmedPrice());
                            // ���s�����iSONAR�j
                            String l_strConfirmedExecConditionType = null;
                            if (l_orderManager.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
                            {
                                l_strConfirmedExecConditionType =
                                    WEB3ExecutionConditionDef.COME_TO_TERMS;
                            }
                            else
                            {
                                l_strConfirmedExecConditionType = 
                                l_orderManager.getExecutionConditionTypeSonar(
                                        l_eqtypeOderUnitRow.getConfirmedExecConditionType());
                            }
                            l_hostEqtypeOrderAllParams.setExecutionCondition(
                                l_strConfirmedExecConditionType);
                            // �l�i�����iSONAR�j
                            l_hostEqtypeOrderAllParams.setPriceConditionType(
                            	l_orderManager.getPriceConditionTypeSonar(
                                	l_eqtypeOderUnitRow.getConfirmedPriceConditionType()));
                            // ����敪
                            l_hostEqtypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.CANCEL);
                            // �������                      
                            l_hostEqtypeOrderAllParams.setCancelOrderTime(l_strLastUpdatedDate);
                            // ����敪�i�O���^�����j
                            l_hostEqtypeOrderAllParams.setCancelOrderDateDiv(l_strCancelOrderDateDiv);
                            // �t�����g����������敪�R�[�h
                            l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode(
                                l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
                            // �t�����g�����V�X�e���敪
                            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
                            EqTypeTradedProduct l_tradedProduct =
                                (EqTypeTradedProduct)l_eqtypeOrderUnit.getTradedProduct();
                            EqtypeTradedProductRow l_tradedProductRow =
                                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                            String l_strFrontOrderSystemCode =
                                l_frontOrderService.getFrontOrderSystemCode(
                                    l_strMarketCode,
                                    l_tradedProductRow.getOpenOtcDiv());
                            l_hostEqtypeOrderAllParams.setFrontOrderSystemCode(
                                l_strFrontOrderSystemCode);
                            // �t�����g��������敪�R�[�h
                            l_hostEqtypeOrderAllParams.setFrontOrderTradeCode(
                                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                            // �Г���������
                            l_hostEqtypeOrderAllParams.setCorpCode(
                                l_frontOrderService.getOrgCorpCode(l_eqtypeOrderUnit));
                            // �i������j�Г���������
                            l_hostEqtypeOrderAllParams.setOrgCorpCode(
                                l_frontOrderService.getOrgCorpCode(l_eqtypeOrderUnit));
                            // �S���������敪
                            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv(
                                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                            // �����敪�i�X�e�[�^�X�j
                            l_hostEqtypeOrderAllParams.setStatus(
                                WEB3FrontOrderStatusDef.NOT_DEAL);
    
                            // �f�[�^�}������
                            QueryProcessor processor = Processors.getDefaultProcessor();
                            processor.doInsertQuery(l_hostEqtypeOrderAllParams);
                        }
                        else
                        {
                            // ��������ԍ�
                            l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                                l_eqtypeOderUnitRow.getLastOrderActionSerialNo());
                            // �󒍓���
                            l_hostEqtypeOrderAllParams.setReceivedDateTime(l_tsCurTime);
                            // �����o�H�敪
                            l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                                l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                            // ����R�[�h�iSONAR�j
                            l_hostEqtypeOrderAllParams.setSonarTradedCode(null);
                            // �ٍϋ敪�iSONAR�j
                            l_hostEqtypeOrderAllParams.setSonarRepaymentType(null);
                            // �s��R�[�h�iSONAR�j
                            l_hostEqtypeOrderAllParams.setSonarMarketCode(null);
                            // �`�[��
                            l_hostEqtypeOrderAllParams.setTicketNumber(null);
                            // �󒍓��敪
                            l_hostEqtypeOrderAllParams.setReceivedDateTimeDiv(null);
                            // �ŋ敪�i��������敪�j
                            l_hostEqtypeOrderAllParams.setTaxType(null);
                            // ���n�v�ŋ敪
                            l_hostEqtypeOrderAllParams.setCapitalGainTaxType(null);
                            // ����
                            l_hostEqtypeOrderAllParams.setCheckType(null);
                            // �����`���l��
                            l_hostEqtypeOrderAllParams.setOrderChanel(null);
                            // �t�@@�N�^�[
                            l_hostEqtypeOrderAllParams.setFactor(null);
                            // �萔����
                            l_hostEqtypeOrderAllParams.setCommisionNumber(null);
                            // �萔�����}��
                            l_hostEqtypeOrderAllParams.setCommisionBranchNumber(null);
                            // �萔�����i�R�[�h
                            l_hostEqtypeOrderAllParams.setCommisionProductCode(null);
                            // �󔄃t���O
                            l_hostEqtypeOrderAllParams.setShortSellOrderFlag(null);
                            // ��������
                            l_hostEqtypeOrderAllParams.setChangeQuantity(null);
                            // �����w�l
                            l_hostEqtypeOrderAllParams.setChangeLimitPrice(null);
                            // �������s�����iSONAR�j
                            l_hostEqtypeOrderAllParams.setChangeExecutionCondition(null);
                            // �����l�i�����iSONAR�j
                            l_hostEqtypeOrderAllParams.setChangePriceConditionType(null);
                            // ��������
                            l_hostEqtypeOrderAllParams.setChangeOrderTime(null);
                            // �����敪�i�O���^�����j
                            l_hostEqtypeOrderAllParams.setChangeOrderDateDiv(null);
                            // ����敪
                            l_hostEqtypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.CANCEL);
                            // �������                      
                            l_hostEqtypeOrderAllParams.setCancelOrderTime(l_strLastUpdatedDate);
                            // ����敪�i�O���^�����j
                            l_hostEqtypeOrderAllParams.setCancelOrderDateDiv(l_strCancelOrderDateDiv);
                            // �Г���������
                            l_hostEqtypeOrderAllParams.setCorpCode(
                                l_frontOrderService.getOrgCorpCode((EqTypeOrderUnit)l_orderUnit));
                            // �i������j�Г���������
                            l_hostEqtypeOrderAllParams.setOrgCorpCode(
                                l_frontOrderService.getOrgCorpCode((EqTypeOrderUnit)l_orderUnit));
                            // �X�V���t
                            l_hostEqtypeOrderAllParams.setLastUpdatedTimestamp(l_tsCurTime);
                            // �f�[�^���X�V����
                            QueryProcessor processor = Processors.getDefaultProcessor();
                            processor.doUpdateQuery(l_hostEqtypeOrderAllParams);
                        }
                        
                        //is�g���K���s()���R�[��
                        l_isTriggerIssue =
                            WEB3GentradeTradingTimeManagement
                                .isSubmitMarketTrigger(
                                null);

                        boolean l_isSubmitMqTrigger =
                            WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                                l_strInstitutionCode,
                                l_eqtypeOderUnitRow.getProductType(),
                                l_strMarketCode,
                                l_eqtypeOderUnitRow.getSubmitOrderRouteDiv(),
                                l_hostEqtypeOrderAllParams.getFrontOrderSystemCode());
                        
                        if (l_isTriggerIssue && l_isSubmitMqTrigger)
                        {
                            String l_strMQDataCode =
                                l_frontOrderService.getChangeCancelMQDataCode(
                                    l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                            if (l_strMQDataCode == null)
                            {
                                log.debug("�V�K�������M �����i������~���j�I�I�I");
                                return DefaultMarketRequestSendResult
                                    .newSuccessResultInstance(
                                    l_lngMsgTokenId);
                            }
                            WEB3MQMessageSpec l_web3MQMessageSpec =
                                new WEB3MQMessageSpec(
                                    l_strInstitutionCode,
                                    l_strMQDataCode);
                            WEB3MQGatewayService l_web3MQGatewayService =
                                (WEB3MQGatewayService)Services.getService(
                                    WEB3MQGatewayService.class);
                            WEB3MQSendResult l_web3MQSendResult =
                                l_web3MQGatewayService.send(l_web3MQMessageSpec); 
                        
                            if (l_web3MQSendResult.isSuccessResult())
                            {
                                log.debug("==> ����������M �����I�I�I");
                                return DefaultMarketRequestSendResult
                                        .newSuccessResultInstance(l_lngMsgTokenId);
                            }
                            else
                            {
                                log.debug("==> ����������M ���s �I�I�I");
                                l_processingResult =
                                    ProcessingResult.newFailedResultInstance(l_web3MQSendResult.getErrorInfo());
                                    return DefaultMarketRequestSendResult
                                        .newFailedResultInstance(
                                        l_processingResult);
                            }
                        }
                    }
                }
                else
                {
                    log.debug("==> �s��ǎ��ԑт̏ꍇ");
                        //�s��ǎ��ԑт̏ꍇ
                    WEB3EquityCancelOrderConfirmInterceptor l_cancelConfirmInterceptor =
                        new WEB3EquityCancelOrderConfirmInterceptor();
                    l_orderManager.setThreadLocalPersistenceEventInterceptor(
                        l_cancelConfirmInterceptor);
                    MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
                    l_marketAdapter.getMarketResponseReceiverCallbackService();
                    DefaultCancelOrderAcceptedMarketResponseMessage l_cancelResponseMessage =
                        new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);
                    EqTypeMarketResponseReceiverCallbackService l_callBackService =
                        (EqTypeMarketResponseReceiverCallbackService)l_tradingModule
                            .getMarketAdapter()
                            .getMarketResponseReceiverCallbackService();
                    l_processingResult = l_callBackService.process(
                        (MarketResponseMessage)l_cancelResponseMessage);
                    if (l_processingResult.isFailedResult())
                    {
                        return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                            l_processingResult);
                    }    
                }
            }
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__", l_wbe);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataFindException e)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    e.toString(),
                    e));
            throw new TooLateException(e.getMessage());
        }
        catch (DataQueryException e)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    e.toString(),
                    e));
            throw new TooLateException(e.getMessage());
        }
        catch (DataNetworkException e)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    e.toString(),
                    e));
            throw new TooLateException(e.getMessage());
        }
        catch (NotFoundException nfe)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.toString(),
                    nfe));
            throw new TooLateException(nfe.getMessage());
        }

        log.exiting(STR_METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(
            l_lngMsgTokenId);
    }

    /**
     * �i�����������M�j�B<BR>
     * <BR>
     * �����̒��������A�V�K�����������̑��M���s���B<BR>
     * �isend(EqTypeChangeOrderMarketRequestMessage, boolean)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * Web3����̒��������i�����A�V�K���j��SONAR�֑��M����B<BR>
     * ���������T�[�r�X�A�M�p��������V�K���T�[�r�X����R�[�������B<BR>
     * -------------------------------------------------------------------<BR>
     * ���������e���A�s��ɒʒm����K�v��������e�̏ꍇ<BR>
     * �@@�|�������������s�ꖢ���M�̏ꍇ�A�y���������L���[�e�[�u���z�̑Ώۃf�[�^��Update����B<BR>
     * �@@�|�������������s�ꑗ�M�ς̏ꍇ<BR>
     * �@@�@@�@@�E�s��J�ǎ��ԑсi�z�X�g���M���ԑ�)�̏ꍇ�A�y����������������L���[�e�[�u���z�ɑ΂�<BR>
     * �@@�@@�@@�@@�s��Insert���A�g���K�𔭍s���鎞�ԑт̏ꍇ�̂�MQ�g���K�𔭍s����B<BR>
     * �@@�@@�@@�E�s��ǎ��ԑ�(�z�X�g���M���ԑъO)�̏ꍇ�A�������m�肳����B<BR>
     * <BR>
     * ���������e���A�s��ɒʒm�s�v�ł���ꍇ<BR>
     * �@@�|�������m�肳����B<BR>
     * -------------------------------------------------------------------<BR>
     * <BR>
     * �P�j�@@�����擾<BR>
     * �@@���������������N�G�X�g���b�Z�[�W.getOrderId() �ɂĒ���ID���擾����B<BR>
     * �@@�����}�l�[�W���ɂāA����ID�ɊY�����钍���I�u�W�F�N�g���擾����B<BR>
     * �@@�����I�u�W�F�N�g.getOrderUnits( )��<BR>
     * �@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f�ɂ��āA�ȉ��̏������s���B<BR>
     * <BR>
     * �ȉ��A�������s�ꖢ���M�����M�ς��ɂ��A�������������B<BR>
     * <BR>
     * �������s�ꖢ���M�̏ꍇ�i����.is�s�ꖢ���M == true�j�́A<BR>
     * �V�[�P���X�}�u�i��������_�M�p����s��ظ��āj�����������M�i�s�ꖢ���M�����j�v���Q�ƁB<BR>
     * �������s�ꑗ�M�ς̏ꍇ�i����.is�s�ꖢ���M == false�j�́A<BR>
     * �V�[�P���X�}�u�i��������_�M�p����s��ظ��āj�����������M�i�s�ꑗ�M�ϒ����j�v���Q�ƁB
     * @@param l_requestMessage �i���������������N�G�X�g���b�Z�[�W�j<BR>
     * ���������������N�G�X�g���b�Z�[�W
     * @@param l_isUnSend �iis�s�ꖢ���M�j<BR>
     * ���������s�ꖢ���M�̏ꍇ��true�A<BR>
     * ���������s�ꑗ�M�ς̏ꍇ��false���w�肷��B<BR> 
     * false�̏ꍇ�ASONAR�֒�����ʒm����B
     * @@return MarketRequestSendResult<BR>
     * @@throws TooLateException
     */
    public MarketRequestSendResult send(
        EqTypeChangeOrderMarketRequestMessage l_requestMessage,
        boolean l_isUnSend)
        throws TooLateException
    {
        final String STR_METHOD_NAME =
            "send(EqTypeChangeOrderMarketRequestMessage,boolean)";
        log.entering(STR_METHOD_NAME);

        String ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.EQUITY_ORDER;
        String CHANGE_CANCEL_REQUEST_CODE =
            WEB3HostRequestCodeDef.EQUITY_ORDER_CHANGE;

        boolean l_isTriggerIssue = false;
        long l_lngMsgTokenId = 0;
        ProcessingResult l_processingResult;
        WEB3MQMessageSpec l_web3MQMessageSpec = null;
        WEB3MQGatewayService l_web3MQGatewayService;
        WEB3MQSendResult l_web3MQSendResult;
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strExecutionCondition = null;
        String l_strPriceConditionType = null;
        String l_strOrderRequestNumber = null;
        String l_strTraderCode = null;
        String l_strPoductCode = null;
        String l_strLastUpdatedDate = null;
        String l_strChangeOrderDateDiv = null;
        Timestamp l_tsReceivedDateTime = null;
        double l_dblOrderQuantity = 0;
        double l_dblLimitPrice = 0;
        String l_strOrderConditionType = null;

        WEB3EquityChangeConfirmInterceptor l_changeConfirmInterceptor = null;
        try
        {
            if (l_requestMessage == null)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            long l_lngOrderId = l_requestMessage.getOrderId();
            OrderUnit[] l_orderUnits = null;
            OrderUnit l_orderUnit = null;
            EqtypeOrderUnitRow l_eqtypeOderUnitRow = null;
    
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            EqTypeProductManager l_eqTypeProductManager =
                (EqTypeProductManager)l_tradingModule.getProductManager();
            EqTypeMarketResponseReceiverCallbackService l_callBackService =
                (EqTypeMarketResponseReceiverCallbackService)l_tradingModule
                    .getMarketAdapter()
                    .getMarketResponseReceiverCallbackService();
    
            WEB3EquityOrderManager l_orderMgr =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            //�����擾
            Order l_order = null;
            l_order = new EqTypeOrderImpl(l_lngOrderId);
            l_orderUnits = l_order.getOrderUnits();
            l_orderUnit = l_orderUnits[0];
            l_eqtypeOderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            // �s��R�[�h���擾
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_eqtypeOderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();

            //�����������擾
            l_strOrderConditionType =
                l_eqtypeOderUnitRow.getOrderConditionType();

            //���ʃR�[�h���擾����
            l_strOrderRequestNumber =
                l_eqtypeOderUnitRow.getOrderRequestNumber();
            //���X���擾����
            Branch l_banch =
                l_accountManager.getBranch(l_eqtypeOderUnitRow.getBranchId());
            //�،���ЃR�[�h���擾����
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            //���X�R�[�h���擾����
            l_strBranchCode = l_banch.getBranchCode();
            //�ڋq�R�[�h���擾����
            l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_eqtypeOderUnitRow.getAccountId())
                    .getAccountCode();
            //���҃R�[�h���擾����
            l_strTraderCode = l_eqtypeOderUnitRow.getSonarTraderCode();

            //�����R�[�h���擾����
            l_strPoductCode =
                ((EqTypeProductImpl)l_eqTypeProductManager
                    .getProduct(l_eqtypeOderUnitRow.getProductId()))
                    .getProductCode();
            //�������ʂ��擾����
            l_dblOrderQuantity = l_eqtypeOderUnitRow.getQuantity();
            //�����w�l���擾����
            l_dblLimitPrice = l_eqtypeOderUnitRow.getLimitPrice();

            //���s�����iSONAR�j
            if (l_orderMgr.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
            {
                l_strExecutionCondition =
                    WEB3ExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                l_strExecutionCondition = 
                    l_orderMgr.getExecutionConditionTypeSonar(
                        l_eqtypeOderUnitRow.getExecutionConditionType());
            }

            //�l�i�����iSONAR�j
            l_strPriceConditionType = l_orderMgr.getPriceConditionTypeSonar(
                l_eqtypeOderUnitRow.getPriceConditionType());
            
            Timestamp l_tsLastUpdateDateTime = GtlUtils.getSystemTimestamp();
            //�󒍓���
            l_tsReceivedDateTime = l_tsLastUpdateDateTime;

            //��������
            l_strLastUpdatedDate =
                WEB3DateUtility.formatDate(l_tsReceivedDateTime, "HHmm");

            //�����敪�i�O���^�����j
            String l_strDate =
                WEB3DateUtility.formatDate(l_tsReceivedDateTime, "yyyyMMdd");
            if (l_eqtypeOderUnitRow.getBizDate().equals(l_strDate))
            {
                l_strChangeOrderDateDiv = WEB3OrderDateDivDef.TODAY;
            }
            else
            {
                l_strChangeOrderDateDiv = WEB3OrderDateDivDef.YESTERDAY;
            }

            if (l_isUnSend)
            {
                l_orderMgr.notifyRLS(
                    (EqTypeOrderUnit)l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_CONFIRMED_BY_MKT);

                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType)
                    && !(WEB3RequestTypeDef.QUOTE_SERVER.equals(l_eqtypeOderUnitRow.getRequestType())))
                {
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }

                log.debug("==> �s�ꖢ���M�iis�s�ꖢ���M == true�j�̏ꍇ�̏���");

                QueryProcessor processor = Processors.getDefaultProcessor();
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                int l_intIndex = l_frontOrderService.getIndexOfOrderRevInCorpCode();
                int l_intFigure = l_frontOrderService.getFigureOfOrderRev();
                String l_strWhere =
                    " request_code=? and institution_code=? and branch_code=? and order_request_number=? and substr(corp_code," + l_intIndex + "," + l_intFigure + ")=? and status=?";
                String[] l_strBindValues = new String[6];
                l_strBindValues[0] = ORDER_REQUEST_CODE;
                l_strBindValues[1] = l_strInstitutionCode;
                l_strBindValues[2] = l_strBranchCode;
                l_strBindValues[3] = l_strOrderRequestNumber;
                l_strBindValues[4] = l_eqtypeOderUnitRow.getOrderRev();
                l_strBindValues[5] = WEB3FrontOrderStatusDef.NOT_DEAL;

                //�������e���X�V����
                Map l_mapChanges = new HashMap();
                
				//set��������ԍ� �F ���������P��.���������ŏI�ʔ�
				l_mapChanges.put(
                    "order_action_serial_no",
				    new Integer(l_eqtypeOderUnitRow.getLastOrderActionSerialNo()));
                
                if (OrderCategEnum.ASSET.equals(l_eqtypeOderUnitRow.getOrderCateg()))
                {
                    //���������̏ꍇ
                    if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                    {
                        //set���t����
                        l_mapChanges.put("buy_order_quantity", new Double("0"));
                        //set���t����
                        l_mapChanges.put("sell_order_quantity", new Double(l_dblOrderQuantity));
                    }
                    else
                    {
                        //set���t����
                        l_mapChanges.put("buy_order_quantity", new Double(l_dblOrderQuantity));
                        //set���t����
                        l_mapChanges.put("sell_order_quantity", new Double("0"));
                    }
                }
                else
                {
                    //�M�p����̏ꍇ
                    if (OrderTypeEnum.MARGIN_LONG.equals(l_orderUnit.getOrderType()))
                    {
                        //set���t����
                        l_mapChanges.put("buy_order_quantity", new Double(l_dblOrderQuantity));
                        //set���t����
                        l_mapChanges.put("sell_order_quantity", new Double("0"));
                    }
                    else if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderUnit.getOrderType()))
                    {
                        //set���t����
                        l_mapChanges.put("buy_order_quantity", new Double("0"));
                        //set���t����
                        l_mapChanges.put("sell_order_quantity", new Double(l_dblOrderQuantity));
                    }
                    //set�󔄃t���O
                    l_mapChanges.put("short_sell_order_flag",l_eqtypeOderUnitRow.getShortSellOrderFlag());
                }

                //set�w�l
                l_mapChanges.put(
                    "limit_price",
                    new Double(l_dblLimitPrice));
                //set���s�����iSONAR�j
                l_mapChanges.put(
                    "execution_condition",
                    l_strExecutionCondition);
                //set�l�i�����iSONAR�j
                l_mapChanges.put(
                    "price_condition_type",
                    l_strPriceConditionType);
                //set�����o�H�敪
                l_mapChanges.put(
                    "submit_order_route_div",
                    l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                //set�X�V���t
                l_mapChanges.put(
                    "last_updated_timestamp",
                    l_tsLastUpdateDateTime);
                int l_intResults = processor.doUpdateAllQuery(
                    (RowType)HostEqtypeOrderAllParams.TYPE,
                    l_strWhere,
                    l_strBindValues,
                    l_mapChanges);
                if (l_intResults == 0)
                {
                    return DefaultMarketRequestSendResult
                        .newFailedResultInstance(
                            ProcessingResult.newFailedResultInstance(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00010));
                }
            }
            else
            {
                log.debug("==> �s�ꑗ�M�ρiis�s�ꖢ���M == false�j�̏ꍇ�̏���");

                // getMarket()�̖߂�l.�s��R�[�h == "PTS�s��"�̏ꍇ
                if (WEB3MarketCodeDef.JNX_PTS.equals(l_strMarketCode))
                {
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);

                    // ������������L���[�e�[�u���֓o�^����B
                    //�o�^���e�́ADB�X�V�d�l
                    //�u(PTS)������������_������������L���[�e�[�u��.xls�v �Q�ƁB

                    HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();

                    // �f�[�^�R�[�h
                    l_hostEqtypeOrderAllParams.setRequestCode(
                        CHANGE_CANCEL_REQUEST_CODE);
                    // ����ID
                    l_hostEqtypeOrderAllParams.setAccountId(l_eqtypeOderUnitRow.getAccountId());
                    // �،���ЃR�[�h
                    l_hostEqtypeOrderAllParams.setInstitutionCode(
                        l_strInstitutionCode);
                    // ���X�R�[�h
                    l_hostEqtypeOrderAllParams.setBranchCode(
                        l_strBranchCode);
                    // �����R�[�h
                    l_hostEqtypeOrderAllParams.setAccountCode(
                        l_strAccountCode);
                    // ���҃R�[�h�iSONAR�j
                    l_hostEqtypeOrderAllParams.setSonarTraderCode(
                        l_strTraderCode);
                    // ���ʃR�[�h
                    l_hostEqtypeOrderAllParams.setOrderRequestNumber(
                        l_strOrderRequestNumber);
                    // ��������ԍ�
                    l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                        l_eqtypeOderUnitRow.getLastOrderActionSerialNo());
                    // �����R�[�h
                    l_hostEqtypeOrderAllParams.setProductCode(
                        l_strPoductCode);
                    // �󒍓���
                    l_hostEqtypeOrderAllParams.setReceivedDateTime(
                        l_tsReceivedDateTime);
                    // �����o�H�敪
                    l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                        l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                    if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                    {
                        // ���t����
                        l_hostEqtypeOrderAllParams.setSellOrderQuantity(
                            l_eqtypeOderUnitRow.getConfirmedQuantity());
                        // ���t����
                        l_hostEqtypeOrderAllParams.setBuyOrderQuantity(0D);
                    }
                    else
                    {
                        // ���t����
                        l_hostEqtypeOrderAllParams.setSellOrderQuantity(0D);
                        // ���t����
                        l_hostEqtypeOrderAllParams.setBuyOrderQuantity(
                            l_eqtypeOderUnitRow.getConfirmedQuantity());
                    }
                    // �w�l
                    l_hostEqtypeOrderAllParams.setLimitPrice(
                        l_eqtypeOderUnitRow.getConfirmedPrice());
                    // ���s�����iSONAR�j
                    String l_strConfirmedExecConditionType = null;
                    if (l_orderMgr.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
                    {
                        l_strConfirmedExecConditionType =
                            WEB3ExecutionConditionDef.COME_TO_TERMS;
                    }
                    else
                    {
                        l_strConfirmedExecConditionType =
                            l_orderMgr.getExecutionConditionTypeSonar(
                                l_eqtypeOderUnitRow.getConfirmedExecConditionType());
                    }
                    l_hostEqtypeOrderAllParams.setExecutionCondition(
                        l_strConfirmedExecConditionType);
                    // �l�i�����iSONAR�j
                    l_hostEqtypeOrderAllParams.setPriceConditionType(
                        l_orderMgr.getPriceConditionTypeSonar(
                            l_eqtypeOderUnitRow.getConfirmedPriceConditionType()));
                    // ��������
                    l_hostEqtypeOrderAllParams.setChangeQuantity(
                        l_dblOrderQuantity);
                    // �����w�l
                    l_hostEqtypeOrderAllParams.setChangeLimitPrice(
                        l_dblLimitPrice);
                    // �������s�����iSONAR�j
                    l_hostEqtypeOrderAllParams.setChangeExecutionCondition(
                        l_strExecutionCondition);
                    // �����l�i�����iSONAR�j
                    l_hostEqtypeOrderAllParams.setChangePriceConditionType(
                        l_strPriceConditionType);
                    // ��������
                    l_hostEqtypeOrderAllParams.setChangeOrderTime(
                        l_strLastUpdatedDate);
                    // �����敪�i�O���^�����j
                    l_hostEqtypeOrderAllParams.setChangeOrderDateDiv(
                        l_strChangeOrderDateDiv);
                    // ����敪
                    l_hostEqtypeOrderAllParams.setCancelDiv(
                        WEB3CancelDivDef.EXCEPT_CANCEL);
                    // �t�����g����������敪�R�[�h
                    l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode(
                        l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
                    // �t�����g�����V�X�e���敪
                    EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
                    EqTypeTradedProduct l_tradedProduct =
                        (EqTypeTradedProduct)l_eqtypeOrderUnit.getTradedProduct();
                    EqtypeTradedProductRow l_tradedProductRow =
                        (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                    String l_strFrontOrderSystemCode =
                        l_frontOrderService.getFrontOrderSystemCode(
                            l_strMarketCode,
                            l_tradedProductRow.getOpenOtcDiv());
                    l_hostEqtypeOrderAllParams.setFrontOrderSystemCode(
                        l_strFrontOrderSystemCode);
                    // �t�����g��������敪�R�[�h
                    l_hostEqtypeOrderAllParams.setFrontOrderTradeCode(
                        WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                    // �Г���������
                    l_hostEqtypeOrderAllParams.setCorpCode(
                        l_frontOrderService.getCorpCode(l_eqtypeOrderUnit));
                    // �i������j�Г���������
                    l_hostEqtypeOrderAllParams.setOrgCorpCode(
                        l_frontOrderService.getOrgCorpCode(l_eqtypeOrderUnit));
                    // �S���������敪
                    l_hostEqtypeOrderAllParams.setAllOrderChangeDiv(
                        WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                    // �����敪
                    l_hostEqtypeOrderAllParams.setStatus(
                        WEB3FrontOrderStatusDef.NOT_DEAL);

                    //�f�[�^�}������
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    l_processor.doInsertQuery(
                        l_hostEqtypeOrderAllParams);
                    log.exiting(STR_METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }

                boolean l_blnQueueInsUpdFlg = true;

                l_orderMgr.notifyRLS(
                    (EqTypeOrderUnit)l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED);

                // is�����s��ʒm
                boolean l_blnIsChangeMarketNotify =
                    l_orderMgr.isChangeMarketNotify(l_orderUnit);
                // is�s��J�ǎ��ԑ�
                boolean l_blnIsTradeOpenTimeZone =
                    WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
                // is������x�e���ԑ�
                boolean l_blnIsTradeRestTimeZone =
                    WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone();
                    
                if (l_blnIsTradeRestTimeZone ||
                    (l_blnIsChangeMarketNotify && l_blnIsTradeOpenTimeZone))
                {
                    log.debug("is�����s��ʒm==" + l_blnIsChangeMarketNotify);
                    log.debug("is�s��J�ǎ��ԑ�==" + l_blnIsTradeOpenTimeZone);
                    log.debug("is������x�e���ԑ�==" + l_blnIsTradeRestTimeZone);
                    
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                    HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = null;
                    if (l_blnIsTradeRestTimeZone)
                    {
                        l_hostEqtypeOrderAllParams =
                            l_frontOrderService.getHostEqtypeOrderAll(
                                (EqTypeOrderUnit)l_orderUnit);
                    }
                    
                    if (l_hostEqtypeOrderAllParams == null)
                    {
                        //is�����s��ʒm==false(�����ʒm�s�v�̏ꍇ)�̓L���[�ɓo�^�E�X�V���s��Ȃ�
                        if(!l_blnIsChangeMarketNotify)
                        {
                            log.debug("�����ʒm�s�v�̂��߁A�L���[�ɓo�^�E�X�V���s��Ȃ��i��������������j");
                            l_blnQueueInsUpdFlg = false;
                        }
                        else
                        {
                            l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();

                            // �f�[�^�R�[�h
                            l_hostEqtypeOrderAllParams.setRequestCode(
                                CHANGE_CANCEL_REQUEST_CODE);
                            // ����ID
                            l_hostEqtypeOrderAllParams.setAccountId(l_eqtypeOderUnitRow.getAccountId());
                            // �،���ЃR�[�h
                            l_hostEqtypeOrderAllParams.setInstitutionCode(
                                l_strInstitutionCode);
                            // ���X�R�[�h
                            l_hostEqtypeOrderAllParams.setBranchCode(
                                l_strBranchCode);
                            // �����R�[�h
                            l_hostEqtypeOrderAllParams.setAccountCode(
                                l_strAccountCode);
                            // ���҃R�[�h�iSONAR�j
                            l_hostEqtypeOrderAllParams.setSonarTraderCode(
                                l_strTraderCode);
                            // ���ʃR�[�h
                            l_hostEqtypeOrderAllParams.setOrderRequestNumber(
                                l_strOrderRequestNumber);
                            // ��������ԍ�
                            l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                                l_eqtypeOderUnitRow.getLastOrderActionSerialNo());
                            // �����R�[�h
                            l_hostEqtypeOrderAllParams.setProductCode(
                                l_strPoductCode);
                            // �󒍓���
                            l_hostEqtypeOrderAllParams.setReceivedDateTime(
                                l_tsReceivedDateTime);
                            // �����o�H�敪
                            l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                                l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                            if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                            {
                                // ���t����
                                l_hostEqtypeOrderAllParams.setSellOrderQuantity(
                                    l_eqtypeOderUnitRow.getConfirmedQuantity());
                                // ���t����
                                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(0D);
                            }
                            else
                            {
                                // ���t����
                                l_hostEqtypeOrderAllParams.setSellOrderQuantity(0D);
                                // ���t����
                                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(
                                    l_eqtypeOderUnitRow.getConfirmedQuantity());
                            }
                            // �w�l
                            l_hostEqtypeOrderAllParams.setLimitPrice(
                                l_eqtypeOderUnitRow.getConfirmedPrice());
                            // ���s�����iSONAR�j
                            String l_strConfirmedExecConditionType = null;
                            if (l_orderMgr.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
                            {
                                l_strConfirmedExecConditionType =
                                    WEB3ExecutionConditionDef.COME_TO_TERMS;
                            }
                            else
                            {
                                l_strConfirmedExecConditionType = 
                                    l_orderMgr.getExecutionConditionTypeSonar(
                                        l_eqtypeOderUnitRow.getConfirmedExecConditionType());
                            }
                            l_hostEqtypeOrderAllParams.setExecutionCondition(
                                l_strConfirmedExecConditionType);
                            // �l�i�����iSONAR�j
                            l_hostEqtypeOrderAllParams.setPriceConditionType(
                                l_orderMgr.getPriceConditionTypeSonar(
                                    l_eqtypeOderUnitRow.getConfirmedPriceConditionType()));
                            // ��������
                            l_hostEqtypeOrderAllParams.setChangeQuantity(
                                l_dblOrderQuantity);
                            // �����w�l
                            l_hostEqtypeOrderAllParams.setChangeLimitPrice(
                                l_dblLimitPrice);
                            // �������s�����iSONAR�j
                            l_hostEqtypeOrderAllParams.setChangeExecutionCondition(
                                l_strExecutionCondition);
                            // �����l�i�����iSONAR�j
                            l_hostEqtypeOrderAllParams.setChangePriceConditionType(
                                l_strPriceConditionType);
                            // ��������
                            l_hostEqtypeOrderAllParams.setChangeOrderTime(
                                l_strLastUpdatedDate);
                            // �����敪�i�O���^�����j
                            l_hostEqtypeOrderAllParams.setChangeOrderDateDiv(
                                l_strChangeOrderDateDiv);
                            // ����敪
                            l_hostEqtypeOrderAllParams.setCancelDiv(
                                WEB3CancelDivDef.EXCEPT_CANCEL);
                            // �t�����g����������敪�R�[�h
                            l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode(
                                l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
                            // �t�����g�����V�X�e���敪
                            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
                            EqTypeTradedProduct l_tradedProduct =
                                (EqTypeTradedProduct)l_eqtypeOrderUnit.getTradedProduct();
                            EqtypeTradedProductRow l_tradedProductRow =
                                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                            String l_strFrontOrderSystemCode =
                                l_frontOrderService.getFrontOrderSystemCode(
                                    l_strMarketCode,
                                    l_tradedProductRow.getOpenOtcDiv());
                            l_hostEqtypeOrderAllParams.setFrontOrderSystemCode(
                                l_strFrontOrderSystemCode);
                            // �t�����g��������敪�R�[�h
                            l_hostEqtypeOrderAllParams.setFrontOrderTradeCode(
                                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                            // �Г���������
                            l_hostEqtypeOrderAllParams.setCorpCode(
                                l_frontOrderService.getCorpCode(l_eqtypeOrderUnit));
                            // �i������j�Г���������
                            l_hostEqtypeOrderAllParams.setOrgCorpCode(
                                l_frontOrderService.getOrgCorpCode(l_eqtypeOrderUnit));
                            // �S���������敪
                            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv(
                                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                            // �����敪
                            l_hostEqtypeOrderAllParams.setStatus(
                                WEB3FrontOrderStatusDef.NOT_DEAL);
                        
                            //�f�[�^�}������
                            QueryProcessor processor = Processors.getDefaultProcessor();
                            processor.doInsertQuery(
                                l_hostEqtypeOrderAllParams);
                        }
                    }
                    else
                    {
                        // ��������ԍ�
                        l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                            l_eqtypeOderUnitRow.getLastOrderActionSerialNo());
                        // �󒍓���
                        l_hostEqtypeOrderAllParams.setReceivedDateTime(
                            l_tsReceivedDateTime);
                        // �����o�H�敪
                        l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                            l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                        // ��������
                        l_hostEqtypeOrderAllParams.setChangeQuantity(
                            l_dblOrderQuantity);
                        // �����w�l
                        l_hostEqtypeOrderAllParams.setChangeLimitPrice(
                            l_dblLimitPrice);
                        // �������s�����iSONAR�j
                        l_hostEqtypeOrderAllParams.setChangeExecutionCondition(
                            l_strExecutionCondition);
                        // �����l�i�����iSONAR�j
                        l_hostEqtypeOrderAllParams.setChangePriceConditionType(
                            l_strPriceConditionType);
                        // ��������
                        l_hostEqtypeOrderAllParams.setChangeOrderTime(
                            l_strLastUpdatedDate);
                        // �����敪�i�O���^�����j
                        l_hostEqtypeOrderAllParams.setChangeOrderDateDiv(
                            l_strChangeOrderDateDiv);
                        //�X�V���t
                        l_hostEqtypeOrderAllParams.setLastUpdatedTimestamp(
                            l_tsLastUpdateDateTime);
                        // �f�[�^�X�V����
                        QueryProcessor processor = Processors.getDefaultProcessor();
                        processor.doUpdateQuery(
                            l_hostEqtypeOrderAllParams);
                    }
                    
                    if (l_blnQueueInsUpdFlg == true)
                    {
                        //is�g���K���s()���R�[��
                        l_isTriggerIssue =
                            WEB3GentradeTradingTimeManagement
                                .isSubmitMarketTrigger(
                                null);

                        boolean l_isSubmitMqTrigger =
                            WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                                l_strInstitutionCode,
                                l_eqtypeOderUnitRow.getProductType(),
                                l_strMarketCode,
                                l_eqtypeOderUnitRow.getSubmitOrderRouteDiv(),
                                l_hostEqtypeOrderAllParams.getFrontOrderSystemCode());
                        
                        if (l_isTriggerIssue && l_isSubmitMqTrigger)
                        {
                            String l_strMQDataCode =
                                l_frontOrderService.getChangeCancelMQDataCode(l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                            if (l_strMQDataCode == null)
                            {
                                log.debug("�V�K�������M �����i������~���j�I�I�I");
                                return DefaultMarketRequestSendResult
                                    .newSuccessResultInstance(
                                    l_lngMsgTokenId);
                            }
                        
                            l_web3MQGatewayService =
                                (WEB3MQGatewayService)Services.getService(
                                    WEB3MQGatewayService.class);

                            l_web3MQMessageSpec =
                                new WEB3MQMessageSpec(
                                    l_strInstitutionCode,
                                    l_strMQDataCode);

                            l_web3MQSendResult =
                                l_web3MQGatewayService.send(
                                    l_web3MQMessageSpec);

                            if (l_web3MQSendResult.isSuccessResult())
                            {
                                log.debug("==> �����������M �����I�I�I");
                                return DefaultMarketRequestSendResult
                                    .newSuccessResultInstance(
                                    l_lngMsgTokenId);
                            }
                            else
                            {
                                log.debug("==> �����������M ���s �I�I�I");
                                l_processingResult =
                                    ProcessingResult.newFailedResultInstance(
                                        l_web3MQSendResult.getErrorInfo());
                                return DefaultMarketRequestSendResult
                                    .newFailedResultInstance(
                                    l_processingResult);
                            }
                        }
                    }
                }
                else
                {
                    l_blnQueueInsUpdFlg = false;
                }

                if (l_blnQueueInsUpdFlg == false)
                {
                    l_changeConfirmInterceptor = 
                        new WEB3EquityChangeConfirmInterceptor();                                                                                                                                                                                                                                          
                    l_orderMgr.setThreadLocalPersistenceEventInterceptor(
                        l_changeConfirmInterceptor);                                                                                                                        
                    l_processingResult = l_callBackService.process(
                        new DefaultChangeOrderAcceptedMarketResponseMessage(l_lngOrderId));    
                    if (l_processingResult.isFailedResult())
                    {
                        return DefaultMarketRequestSendResult.newFailedResultInstance(
                            l_processingResult);
                    }                                                                                                                    
                }
            }
        }
        catch (DataNetworkException dne)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    dne.toString(),
                    dne));
            throw new TooLateException(dne.getMessage());
        }
        catch (DataQueryException dqe)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    dqe.toString(),
                    dqe));
            throw new TooLateException(dqe.getMessage());
        }
        catch (NotFoundException nfe)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.toString(),
                    nfe));
            throw new TooLateException(nfe.getMessage());
        }
        catch (WEB3SystemLayerException wse)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    wse.toString(),
                    wse));
            throw new TooLateException(wse.getMessage());
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__", l_wbe);
            l_processingResult =
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }

        log.exiting(STR_METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(
            l_lngMsgTokenId);
    }

    /**
     * �i�V�K���������M�j�B<BR>
     * <BR>
     * �V�K�������̑��M���s���B<BR>
     * �isend(EqTypeOpenContractOrderMarketRequestMessage)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �M�p����V�K���T�[�r�X�A�����J�z�T�[�r�X�A�M�p��������ʒm�T�[�r�X���<BR>
     * �R�[�������B<BR>
     * -------------------------------------------------------------------<BR>
     * ��Web�V���͒����̏ꍇ�i�����o�H�敪��HOST�j<BR>
     * �@@�|�y���������L���[�e�[�u���z�ɑ΂��A�����f�[�^��Insert������B<BR>
     * �@@�|MQ�g���K�𔭍s����B<BR>
     * <BR>
     * ��SONAR���͒����̏ꍇ�i�����o�H�敪��HOST�j<BR>
     * �@@�|�u�V�K��������tOK�v�̏������s���A�����X�e�[�^�X��"������"�ɂ���B<BR>
     * -------------------------------------------------------------------<BR>
     * <BR>
     * �������e�́A<BR>
     * �V�[�P���X�}�u�i�M�p����s��ظ��āj�V�K���������M�v���Q�ƁB<BR>
     * <BR>
     * @@param l_requestMessage �i�M�p�V�K�������s�ꃊ�N�G�X�g���b�Z�[�W�j<BR>
     * �M�p�V�K�������s�ꃊ�N�G�X�g���b�Z�[�W
     * @@return MarketRequestSendResult
     * @@roseuid 414544E701F1
     */
    public MarketRequestSendResult send(EqTypeOpenContractOrderMarketRequestMessage l_requestMessage)
    {
        final String STR_METHOD_NAME =
            "send(EqTypeOpenContractOrderMarketRequestMessage)";
        log.entering(STR_METHOD_NAME);
        long l_lngMsgTokenId = 0;
        try
        {
            long l_orderId = l_requestMessage.getOrderId();
            //3.getOrderUnits(����ID : long)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            //�����}�l�[�W���ɂāA����ID�ɊY�����钍���I�u�W�F�N�g���擾����B<BR>
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
            AccountManager l_accountManager = l_finApp.getAccountManager();

            //�����I�u�W�F�N�g.getOrderUnits( )��<BR>
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_orderId);
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            Branch l_branch =
                l_accountManager.getBranch(l_orderUnitRow.getBranchId());
        
            //4.�����P��.�����o�H�敪�@@���@@"HOST"
            if (WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
            {
                //5.����������t�C���^�Z�v�^
                WEB3EquityOrderAcceptPersistenceInterceptor l_orderAccepterInterceptor =
                    new WEB3EquityOrderAcceptPersistenceInterceptor(null);
                //6.setThreadLocalPersistenceEventInterceptor
                l_orderManager.setThreadLocalPersistenceEventInterceptor(
                    l_orderAccepterInterceptor);
                //7.getMarketResponseReceiverCallbackService( )
                MarketResponseReceiverCallbackService l_marketRequestSenderService =
                    l_marketAdapter.getMarketResponseReceiverCallbackService();
                //8.DefaultNewOrderAcceptedMarketResponseMessage
                DefaultNewOrderAcceptedMarketResponseMessage l_responseMessage =
                    new DefaultNewOrderAcceptedMarketResponseMessage(l_orderId);
                //
                ProcessingResult l_processingResult = l_marketRequestSenderService.process(l_responseMessage);
                
                if (l_processingResult.isFailedResult())
                {
                    return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                            l_processingResult);
                }
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                    l_lngMsgTokenId);
            }
            
            l_orderManager.notifyRLS(
                l_orderUnit,
                OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER);
            
            //12�����P��.�����������h�t�w�l�h
            if (WEB3OrderingConditionDef
                .STOP_LIMIT_PRICE
                .equals(l_orderUnitRow.getOrderConditionType()))
            {
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                    l_lngMsgTokenId);
            }

            l_orderManager.insertMarginOpenHostOrder(l_orderId);
            
            // �����o�H�敪
            String l_strSubmitOrderRouteDiv =
                l_orderUnitRow.getSubmitOrderRouteDiv();
            // �t�����g����������敪�R�[�h
            WEB3EquityFrontOrderService l_frontOrderService =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            // �t�����g�����V�X�e���敪
            EqTypeTradedProduct l_tradedProduct =
                (EqTypeTradedProduct)l_orderUnit.getTradedProduct();
            EqtypeTradedProductRow l_tradedProductRow =
                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
            String l_strFrontOrderSystemCode =
                l_frontOrderService.getFrontOrderSystemCode(
                    l_strMarketCode,
                    l_tradedProductRow.getOpenOtcDiv());
            
            boolean l_isTriggerIssue =
                WEB3GentradeTradingTimeManagement
                    .isSubmitMarketTrigger(l_orderUnitRow.getOrderConditionType());
            boolean l_isSubmitMqTrigger =
                WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                    l_branch.getInstitution().getInstitutionCode(),
                    l_orderUnitRow.getProductType(),
                    l_strMarketCode,
                    l_strSubmitOrderRouteDiv,
                    l_strFrontOrderSystemCode);
            
            if (l_isTriggerIssue && l_isSubmitMqTrigger)
            {
                String l_strMQDataCode =
                    l_frontOrderService.getOrderMQDataCode(l_strSubmitOrderRouteDiv);
                if (l_strMQDataCode == null)
                {
                    log.debug("�V�K�������M �����i������~���j�I�I�I");
                    return DefaultMarketRequestSendResult
                        .newSuccessResultInstance(
                        l_lngMsgTokenId);
                }
                WEB3MQMessageSpec l_mqMessageSpec =
                    new WEB3MQMessageSpec(
                        l_branch.getInstitution().getInstitutionCode(),
                        l_strMQDataCode);
                //18.send(WEB3MQMessageSpec)(
                WEB3MQGatewayService l_mqGatewayService =
                    (WEB3MQGatewayService)Services.getService(
                        WEB3MQGatewayService.class);
                WEB3MQSendResult l_web3MQSendResult= l_mqGatewayService.send(l_mqMessageSpec);
                ProcessingResult l_processingResult = null;
                if (l_web3MQSendResult.isSuccessResult())
                {
                    log.debug("==> �V�K���������M �����I�I�I");
                    return DefaultMarketRequestSendResult
                        .newSuccessResultInstance(l_lngMsgTokenId);
                }
                else
                {
                    log.debug("==> �V�K���������M ���s �I�I�I");
                    l_processingResult =ProcessingResult.newFailedResultInstance(
                                                       l_web3MQSendResult.getErrorInfo());
                                               return DefaultMarketRequestSendResult
                                                   .newFailedResultInstance(
                                                   l_processingResult);
                }
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__", l_wbe);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        log.exiting(STR_METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                   l_lngMsgTokenId);
    }

    /**
     * �i�ԍϒ������M�j�B<BR>
     * <BR>
     * �ԍϒ����̑��M���s���B<BR>
     * �isend(EqTypeSettleContractOrderMarketRequestMessage)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �M�p����ԍσT�[�r�X�A�����J�z�T�[�r�X�A�M�p��������ʒm�T�[�r�X���
     * �R�[�������B<BR>
     * -------------------------------------------------------------------<BR>
     * ��Web�V���͒����̏ꍇ�i�����o�H�敪��HOST�j<BR>
     * �@@�|�y���������L���[�e�[�u���z�ɑ΂��A�����f�[�^��Insert������B<BR>
     * �@@�|MQ�g���K�𔭍s����B<BR>
     * <BR>
     * ��SONAR���͒����̏ꍇ�i�����o�H�敪��HOST�j<BR>
     * �@@�|�u�ԍϒ�����tOK�v�̏������s���A�����X�e�[�^�X��"������"�ɂ���B<BR>
     * -------------------------------------------------------------------<BR>
     * <BR>
     * �������e�́A<BR>
     * �V�[�P���X�}�u�i�M�p����s��ظ��āj�ԍϒ������M�v���Q�ƁB
     * @@param l_requestMessage �i�M�p�ԍϒ����s�ꃊ�N�G�X�g���b�Z�[�W�j<BR>
     * �M�p�ԍϒ����s�ꃊ�N�G�X�g���b�Z�[�W
     * @@return MarketRequestSendResult
     */
    public MarketRequestSendResult send(EqTypeSettleContractOrderMarketRequestMessage l_requestMessage)
    {
        final String STR_METHOD_NAME =
            "send(EqTypeNewCashBasedOrderMarketRequestMessage)";
        log.entering(STR_METHOD_NAME);
        long l_lngMsgTokenId = 0;
        try
        {
            //2.getOrderId( )(
            long l_orderId = l_requestMessage.getOrderId();
            //3.getOrderUnits(����ID : long)
            //�����}�l�[�W���ɂāA����ID�ɊY�����钍���I�u�W�F�N�g���擾����B<BR>
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
            //�����I�u�W�F�N�g.getOrderUnits( )��<BR>
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_orderId);
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];

            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            if (l_orderManager.isApproveForcedSettleOrder(l_orderUnit))
            {
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                    l_lngMsgTokenId);
            }

            if (WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
            {
                //5.����������t�C���^�Z�v�^
                WEB3EquityOrderAcceptPersistenceInterceptor l_orderAccepterInterceptor =
                    new WEB3EquityOrderAcceptPersistenceInterceptor(null);
                //6.setThreadLocalPersistenceEventInterceptor
                l_orderManager.setThreadLocalPersistenceEventInterceptor(
                    l_orderAccepterInterceptor);
                //7.getMarketResponseReceiverCallbackService( )
                MarketResponseReceiverCallbackService l_marketRequestSenderService =
                    l_marketAdapter.getMarketResponseReceiverCallbackService();
                //8.DefaultNewOrderAcceptedMarketResponseMessage
                DefaultNewOrderAcceptedMarketResponseMessage l_responseMessage =
                    new DefaultNewOrderAcceptedMarketResponseMessage(l_orderId);
                //
                ProcessingResult l_processingResult = l_marketRequestSenderService.process(l_responseMessage);
                if (l_processingResult.isFailedResult())
                {
                    return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                            l_processingResult);
                }
                
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                    l_lngMsgTokenId);
            
            }
            
            l_orderManager.notifyRLS(
                l_orderUnit,
                OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER);
            
            //12�����P��.�����������h�t�w�l�h
            if (WEB3OrderingConditionDef
                .STOP_LIMIT_PRICE
                .equals(l_orderUnitRow.getOrderConditionType()))
            {
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                    l_lngMsgTokenId);
            }
            
            l_orderManager.insertMarginCloseHostOrder(l_orderId);

            AccountManager l_accountManager = l_finApp.getAccountManager();
            Branch l_branch =
                l_accountManager.getBranch(l_orderUnitRow.getBranchId());

            // �����o�H�敪
            String l_strSubmitOrderRouteDiv =
                l_orderUnitRow.getSubmitOrderRouteDiv();
            // �t�����g����������敪�R�[�h
            WEB3EquityFrontOrderService l_frontOrderService =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            // �t�����g�����V�X�e���敪
            EqTypeTradedProduct l_tradedProduct =
                (EqTypeTradedProduct)l_orderUnit.getTradedProduct();
            EqtypeTradedProductRow l_tradedProductRow =
                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
            String l_strFrontOrderSystemCode =
                l_frontOrderService.getFrontOrderSystemCode(
                    l_strMarketCode,
                    l_tradedProductRow.getOpenOtcDiv());
            
            //15. is�g���K���s(String)(
            boolean l_isTriggerIssue =
                WEB3GentradeTradingTimeManagement
                    .isSubmitMarketTrigger(l_orderUnitRow.getOrderConditionType());
            boolean l_isSubmitMqTrigger =
                WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                    l_branch.getInstitution().getInstitutionCode(),
                    l_orderUnitRow.getProductType(),
                    l_strMarketCode,
                    l_strSubmitOrderRouteDiv,
                    l_strFrontOrderSystemCode);
            
            if (l_isTriggerIssue && l_isSubmitMqTrigger)
            {
                String l_strMQDataCode =
                    l_frontOrderService.getOrderMQDataCode(l_strSubmitOrderRouteDiv);
                if (l_strMQDataCode == null)
                {
                    log.debug("�V�K�������M �����i������~���j�I�I�I");
                    return DefaultMarketRequestSendResult
                        .newSuccessResultInstance(
                        l_lngMsgTokenId);
                }
                //16.WEB3MQMessageSpec(String, String)(
                WEB3MQMessageSpec l_mqMessageSpec =
                    new WEB3MQMessageSpec(
                        l_branch.getInstitution().getInstitutionCode(),
                        l_strMQDataCode);
                //18.send(WEB3MQMessageSpec)(
                WEB3MQGatewayService l_mqGatewayService =
                    (WEB3MQGatewayService)Services.getService(
                        WEB3MQGatewayService.class);
                WEB3MQSendResult l_web3MQSendResult = l_mqGatewayService.send(l_mqMessageSpec);
                log.exiting(STR_METHOD_NAME);
                ProcessingResult l_processingResult = null;
                if (l_web3MQSendResult.isSuccessResult())
                {
                    log.debug("==> �ԍϒ������M �����I�I�I");
                    return DefaultMarketRequestSendResult
                        .newSuccessResultInstance(l_lngMsgTokenId);
                }
                else
                {
                    log.debug("==> �ԍϒ������M ���s �I�I�I");
                    l_processingResult =ProcessingResult.newFailedResultInstance(
                    l_web3MQSendResult.getErrorInfo());
                    return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                }
            }           
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__", l_wbe);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                  l_lngMsgTokenId);
    }

    /**
     * �i�������n�������M�j�B<BR>
     * <BR>
     * �������n�����̑��M���s���B<BR>
     * �isend(EqTypeSwapContractOrderMarketRequestMessage)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �M�p����������n�T�[�r�X����R�[�������B<BR>
     * -------------------------------------------------------------------<BR>
     * �@@�|�y�������n�L���[�e�[�u���z�ɑ΂��A�����f�[�^��Insert����B<BR>
     * �@@�|MQ�g���K�𔭍s����B<BR>
     * -------------------------------------------------------------------<BR>
     * <BR>
     * �������e�́A<BR>
     * �V�[�P���X�}�u�i�M�p����s��ظ��āj�������n�������M�v���Q�ƁB
     * @@param l_requestMessage �i�M�p�������n�����s�ꃊ�N�G�X�g���b�Z�[�W�j<BR>
     * �M�p�������n�����s�ꃊ�N�G�X�g���b�Z�[�W
     * @@return MarketRequestSendResult
     * @@roseuid 414544E70255
     */
    public MarketRequestSendResult send(EqTypeSwapContractOrderMarketRequestMessage l_requestMessage)
    {
        final String STR_METHOD_NAME =
            "send(EqTypeNewCashBasedOrderMarketRequestMessage)";
        log.entering(STR_METHOD_NAME);
        //2.getOrderId( )(
        long l_orderId = l_requestMessage.getOrderId();
        //3.getOrderUnits(����ID : long)
        //�����}�l�[�W���ɂāA����ID�ɊY�����钍���I�u�W�F�N�g���擾����B<BR>
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        //�����I�u�W�F�N�g.getOrderUnits( )��<BR>
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_orderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];

        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        long l_lngMsgTokenId = 0;
        HostEqtypeSwapParams l_params = new HostEqtypeSwapParams();
        AccountManager l_accountManager = l_finApp.getAccountManager();
        ProductManager l_productManager = l_tradingModule.getProductManager();
        try
        {
            //3.�����P��.�����o�H�敪�@@���@@"HOST"
            if (WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
            {
                //3.1.�M�p�������n������t�C���^�Z�v�^
                WEB3MarginSwapMarginAcceptInterceptor l_swapMarginAccepterInterceptor =
                    new WEB3MarginSwapMarginAcceptInterceptor(null);
                //3.2.setThreadLocalPersistenceEventInterceptor
                l_orderManager.setThreadLocalPersistenceEventInterceptor(
                    l_swapMarginAccepterInterceptor);
                //3.3.getMarketResponseReceiverCallbackService( )
                MarketResponseReceiverCallbackService l_marketRequestSenderService =
                    l_marketAdapter.getMarketResponseReceiverCallbackService();
                //3.4.DefaultNewOrderAcceptedMarketResponseMessage
                DefaultNewOrderAcceptedMarketResponseMessage l_responseMessage =
                    new DefaultNewOrderAcceptedMarketResponseMessage(l_orderId);
                //3.5.process
                ProcessingResult l_processingResult = l_marketRequestSenderService.process(l_responseMessage);
                
                if (l_processingResult.isFailedResult())
                {
                    return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                            l_processingResult);
                }
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                    l_lngMsgTokenId);
            }
            
            Branch l_branch =
                l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            Institution l_institution = l_branch.getInstitution();
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

            //(*1) �L���[�e�[�u���ɍs��}������B
            //�}������s�̓��e�́ADB�X�V�d�l
            //�u�M�p�������n_�������n�L���[�e�[�u��.xls�v�Q�ƁB
            //1   �f�[�^�R�[�h  request_code   �hAI805�h             
            l_params.setRequestCode(WEB3HostRequestCodeDef.EQUITY_SPOT_ORDER);
            //����ID
            l_params.setAccountId(l_orderUnitRow.getAccountId());
            //2   �،���ЃR�[�h  institution_code     ���������P��.���X�h�c�ɊY�����镔�X.�،���ЃR�[�h  
            l_params.setInstitutionCode(l_institution.getInstitutionCode());
            //3   ���X�R�[�h                                                  
            l_params.setBranchCode(l_branch.getBranchCode());
            //4   �ڋq�R�[�h  
            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_orderUnit.getAccountId());
            MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
            l_params.setAccountCode(l_mainAccount.getAccountCode());
            //5   ���҃R�[�h�iSONAR�j   
            l_params.setSonarTraderCode(l_orderUnitRow.getSonarTraderCode());
            
            //6   �����R�[�h 
            WEB3EquityProduct l_product =
                (WEB3EquityProduct)l_productManager.getProduct(
                    l_orderUnitRow.getProductId());
            l_params.setProductCode(l_product.getProductCode());
            //7   ���n����   
            if (OrderTypeEnum
                .SWAP_MARGIN_SHORT
                .equals(l_orderUnitRow.getOrderType()))
            {
                l_params.setSellOrderQuantity(l_orderUnitRow.getQuantity());
            }
            else
            {
                l_params.setSellOrderQuantity(0);
            }
            //8   ��������  
            if (OrderTypeEnum
                .SWAP_MARGIN_LONG
                .equals(l_orderUnitRow.getOrderType()))
            {
                l_params.setBuyOrderQuantity(l_orderUnitRow.getQuantity());
            }
            else
            {
                l_params.setBuyOrderQuantity(0);
            }
            //9   �ٍϋ敪�iSONAR�j
            l_params.setSonarRepaymentType(
                l_orderUnitRow.getSonarRepaymentType());
            //10  �s��R�[�h�iSONAR�j
            l_params.setSonarMarketCode(l_orderUnitRow.getSonarMarketCode());
            //11  �`�[�� 
            l_params.setTicketNumber(l_orderUnitRow.getVoucherNo());
            //12  ���n�v�ŋ敪
            // ���������̏ꍇ�F�@@0�F�Ȃ�
            // ���n�����̏ꍇ�F
			// �@@�l�q�i�ڋq.�����^�C�v==("�l�A�J�E���g", "���p�A�J�E���g")�j�ł��A
			//�@@�@@�@@�@@���Z�ҁA���ʔ񋏏Z�҂̏ꍇ�F�@@1�F�\��
			//�@@�@@�@@�@@�񋏏Z�҂̏ꍇ�F�@@�@@�@@�@@�@@�@@�@@0�F�Ȃ�
			// �@@�@@�l�q�i�ڋq.�����^�C�v=="�@@�l�A�J�E���g"�j�̏ꍇ�F�@@0�F�Ȃ�
			if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderUnit.getOrderType()))
			{
				l_params.setCapitalGainTaxType(
					WEB3CapitalGainTaxTypeDef.NOTHING);
			}
			else if (
				MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.equals(l_mainAccountRow.getAccountType()) ||
				MainAccountTypeEnum.JOINT_OWNERSHIP.equals(l_mainAccountRow.getAccountType()))
			{
				if (WEB3ResidentDef.RESIDENT.equals(l_mainAccountRow.getResident()) ||
					WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_mainAccountRow.getResident()))
				{
					l_params.setCapitalGainTaxType(
						WEB3CapitalGainTaxTypeDef.REPORT);
				}
				else if (WEB3ResidentDef.NON_RESIDENT.equals(l_mainAccountRow.getResident()))
				{
					l_params.setCapitalGainTaxType(
						WEB3CapitalGainTaxTypeDef.NOTHING);
				}
			}
            else if(
                MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountRow.getAccountType()))
            {
                l_params.setCapitalGainTaxType(
                    WEB3CapitalGainTaxTypeDef.NOTHING);
            }
            
            //13  ���� 
            l_params.setCheckType(WEB3CheckTypeDef.PRE_CHECK);
            //14  ���  
            l_params.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);
            //15  ���ʃR�[�h 
            l_params.setOrderRequestNumber(
                l_orderUnitRow.getOrderRequestNumber());
            //��������ԍ� �F ���������P��.���������ŏI�ʔ�
            l_params.setOrderActionSerialNo(l_orderUnitRow.getLastOrderActionSerialNo());
            //16  �󒍓���
            Timestamp l_tsCurTime =
                GtlUtils.getTradingSystem().getSystemTimestamp();
            l_params.setCreateDatetime(l_tsCurTime);
            //17  �ŋ敪�i��������敪�j 
            if (TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getTaxType()))
            {
                l_params.setTaxType(WEB3TaxTypeDef.NORMAL);
            }
            else
            {
                l_params.setTaxType(WEB3TaxTypeDef.SPECIAL);
            }

            //18  �ŋ敪�i�������n������������敪�j  
            if (TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getSwapTaxType()))
            {
                l_params.setSwapTaxType(WEB3TaxTypeDef.NORMAL);
            }
            else
            {
                l_params.setSwapTaxType(WEB3TaxTypeDef.SPECIAL);
            }

            //19  �����敪 status    
            l_params.setStatus(WEB3StatusDef.NOT_DEAL);
            l_queryProcesser.doInsertQuery(l_params);
            //7.is�g���K���s(String)(    
            if (WEB3GentradeTradingTimeManagement
                .isSubmitMarketTrigger(l_orderUnitRow.getOrderConditionType()))
            {
                //8.WEB3MQMessageSpec(String, String)(
                WEB3MQMessageSpec l_mqMessageSpec =
                    new WEB3MQMessageSpec(
                        l_branch.getInstitution().getInstitutionCode(),
                        WEB3HostRequestCodeDef.EQUITY_SPOT_ORDER + DATA_CODE);
                //9.send(WEB3MQMessageSpec)(
                WEB3MQGatewayService l_mqGatewayService =
                    (WEB3MQGatewayService)Services.getService(
                        WEB3MQGatewayService.class);
                WEB3MQSendResult l_web3MQSendResult = l_mqGatewayService.send(l_mqMessageSpec);
                ProcessingResult l_processingResult = null;
                if (l_web3MQSendResult.isSuccessResult())
                {
                    log.debug("==> �������n�������M �����I�I�I");
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }
                else
                {
                    log.debug("==> �������n�������M ���s �I�I�I");
                        l_processingResult =ProcessingResult.newFailedResultInstance(
                            l_web3MQSendResult.getErrorInfo());
                            return DefaultMarketRequestSendResult
                            .newFailedResultInstance(l_processingResult);
                }
            }           
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME,l_dfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME,l_dne);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME,l_dqe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_wsle)
        {
            log.error(STR_METHOD_NAME,l_wsle);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                          l_lngMsgTokenId);
    }

    /**
     * �i�ԍϒ����������M�j�B<BR>
     * <BR>
     * �ԍϒ��������̑��M���s���B<BR>
     * �isend(EqTypeChangeSettleContractOrderMarketRequestMessage, boolean)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * Web3����̕ԍϒ���������SONAR�֑��M����B<BR>
     * �M�p��������ԍσT�[�r�X����R�[�������B<BR>
     * -------------------------------------------------------------------<BR>
     * ���������e���A�s��ɒʒm����K�v��������e�̏ꍇ<BR>
     * �@@�|�������������s�ꖢ���M�̏ꍇ�A�y���������L���[�e�[�u���z�̑Ώۃf�[�^��Update����B<BR>
     * �@@�|�������������s�ꑗ�M�ς̏ꍇ<BR>
     * �@@�@@�@@�E�s��J�ǎ��ԑсi�z�X�g���M���ԑ�)�̏ꍇ�A�y����������������L���[�e�[�u���z�ɑ΂�<BR>
     * �@@�@@�@@�@@�s��Insert���A�g���K�𔭍s���鎞�ԑт̏ꍇ�̂�MQ�g���K�𔭍s����B<BR>
     * �@@�@@�@@�E�s��ǎ��ԑ�(�z�X�g���M���ԑъO)�̏ꍇ�A�������m�肳����B<BR>
     * <BR>
     * ���������e���A�s��ɒʒm�s�v�ł���ꍇ<BR>
     * �@@�|�������m�肳����B<BR>
     * -------------------------------------------------------------------<BR>
     * <BR>
     * �P�j�@@�����擾<BR>
     * �@@�M�p�ԍϒ����������N�G�X�g���b�Z�[�W.getOrderId( ) �ɂĒ���ID���擾����B<BR>
     * �@@�����}�l�[�W���ɂāA����ID�ɊY�����钍���I�u�W�F�N�g���擾����B<BR>
     * �@@�����I�u�W�F�N�g.getOrderUnits( )��<BR>
     * �@@�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f�ɂ��āA�ȉ��̏������s���B<BR>
     * <BR>
     * �ȉ��A�������s�ꖢ���M�����M�ς��ɂ��A�������������B<BR>
     * <BR>
     * �������s�ꖢ���M�̏ꍇ�i����.is�s�ꖢ���M == true�j�́A<BR>
     * �V�[�P���X�}�u�i�M�p����s��ظ��āj�ԍϒ����������M�i�s�ꖢ���M�����j�v���Q�ƁB<BR>
     * �������s�ꑗ�M�ς̏ꍇ�i����.is�s�ꖢ���M == false�j�́A<BR>
     * �V�[�P���X�}�u�i�M�p����s��ظ��āj�ԍϒ����������M�i�s�ꑗ�M�ϒ����j�v���Q�ƁB
     * @@param l_marginCloseMarginOrderChangeRequestMessage �i�M�p�ԍϒ����������N�G�X�g���b�Z�[�W�j<BR>
     * �M�p�ԍϒ����������N�G�X�g���b�Z�[�W
     * @@param l_isMarketNoRequestMessage �iis�s�ꖢ���M�j<BR>
     * �������������s�ꖢ���M�̏ꍇ��true�A<BR>
     * ���������s�ꑗ�M�ς̏ꍇ��false���w�肷��B<BR>
     * false�̏ꍇ�ASONAR�֒�����ʒm����B
     * @@return MarketRequestSendResult
     * @@roseuid 414544E70291
     */
    public MarketRequestSendResult send(
        EqTypeChangeSettleContractOrderMarketRequestMessage l_requestMessage,
        boolean l_isUnSend)
    {
        final String STR_METHOD_NAME =
            "EqTypeChangeSettleContractOrderMarketRequestMessage l_requestMessage, boolean l_isUnSend)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����擾
        long l_orderId = l_requestMessage.getOrderId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //�����}�l�[�W���ɂāA����ID�ɊY�����钍���I�u�W�F�N�g���擾����B<BR>
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp
                .getTradingModule(ProductTypeEnum.EQUITY)
                .getOrderManager();
        //�����I�u�W�F�N�g.getOrderUnits( )��<BR>
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_orderId);
        //�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f�ɂ��āA�ȉ��̏������s���B
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        AccountManager l_accountManager = l_finApp.getAccountManager();
        long l_lngMsgTokenId = 0;
          
        try
        {
            //���ʃR�[�h���擾����
            String l_strOrderRequestNumber =
                l_orderUnitRow.getOrderRequestNumber();
            //���X���擾����
            Branch l_banch =
                l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            //�،���ЃR�[�h���擾����
            String l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            //���X�R�[�h���擾����
            String l_strBranchCode = l_banch.getBranchCode();
            //�ڋq�R�[�h���擾����
            String l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_orderUnitRow.getAccountId())
                    .getAccountCode();
            //���҃R�[�h���擾����
            String l_strTraderCode = l_orderUnitRow.getSonarTraderCode();

            //�����R�[�h���擾����
            String l_strPoductCode =
                ((EqTypeProduct)l_orderUnit.getProduct()).getProductCode();
            //�������ʂ��擾����
            double l_dblOrderQuantity = l_orderUnitRow.getQuantity();
            //�����w�l���擾����
            double l_dblLimitPrice = l_orderUnitRow.getLimitPrice();

            //���s����
            String l_strExecutionCondition = null;
            if (l_orderManager.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
            {
                l_strExecutionCondition =
                    WEB3ExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                l_strExecutionCondition = 
                    l_orderManager.getExecutionConditionTypeSonar(
                        l_orderUnitRow.getExecutionConditionType());
            }

            //�l�i����
            String l_strPriceConditionType = l_orderManager.getPriceConditionTypeSonar(
                l_orderUnitRow.getPriceConditionType());
            
            Timestamp l_tsLastUpdateDateTime = GtlUtils.getSystemTimestamp();
            //�󒍓���
            Timestamp l_tsReceivedDateTime = l_tsLastUpdateDateTime;
            //��������
            String l_strLastUpdatedDate =
                WEB3DateUtility.formatDate(l_tsReceivedDateTime, "HHmm");

            //�����敪�i�O���^�����j
            String l_strDate =
                WEB3DateUtility.formatDate(l_tsReceivedDateTime, "yyyyMMdd");
            String l_strChangeOrderDateDiv;
            if (l_orderUnitRow.getBizDate().equals(l_strDate))
            {
                l_strChangeOrderDateDiv = WEB3OrderDateDivDef.TODAY;
            }
            else
            {
                l_strChangeOrderDateDiv = WEB3OrderDateDivDef.YESTERDAY;
            }
            
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            //�������s�ꖢ���M�̏ꍇ�i����.is�s�ꖢ���M == true�j��
            if (l_isUnSend)
            {
                l_orderManager.notifyRLS(
                    l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_CONFIRMED_BY_MKT);

                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType())
                    && !(WEB3RequestTypeDef.QUOTE_SERVER.equals(l_orderUnitRow.getRequestType())))
                {
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }

                log.debug("==> �s�ꖢ���M�iis�s�ꖢ���M == true�j�̏ꍇ�̏���");
                //�s�ꖢ���M�iis�s�ꖢ���M == true�j�̏ꍇ�̏���
                //���������L���[�e�[�u���̑Ώۃf�[�^����
                // ���������L���[�e�[�u�����A�ȉ��̏����Ɉ�v����s��
                //Select for update�ɂČ������A�������e���X�V����
                //���������L���[�e�[�u��.�f�[�^�R�[�h = �h������������h
                //���������L���[�e�[�u��.�،���ЃR�[�h = �����P��.�،����ID�ɊY������،���ЃR�[�h
                //���������L���[�e�[�u��.���X�R�[�h = �����P��.���XID�ɊY�����镔�X�R�[�h
                //���������L���[�e�[�u��.���ʃR�[�h = �����P��.���ʃR�[�h
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                int l_intIndex = l_frontOrderService.getIndexOfOrderRevInCorpCode();
                int l_intFigure = l_frontOrderService.getFigureOfOrderRev();
                String l_strWhere =
                    " request_code=? and institution_code=? and branch_code=? and order_request_number=? and substr(corp_code," + l_intIndex + "," + l_intFigure + ")=? and status=?";
                String[] l_strBindValues = new String[6];
                l_strBindValues[0] = WEB3HostRequestCodeDef.EQUITY_ORDER;
                l_strBindValues[1] = l_strInstitutionCode;
                l_strBindValues[2] = l_strBranchCode;
                l_strBindValues[3] = l_strOrderRequestNumber;
                l_strBindValues[4] = l_orderUnitRow.getOrderRev();
                l_strBindValues[5] = WEB3FrontOrderStatusDef.NOT_DEAL;

                //�������e���X�V����
                Map l_mapChanges = new HashMap();
                
				//set��������ԍ� �F ���������P��.���������ŏI�ʔ�
				l_mapChanges.put(
				    "order_action_serial_no",
				    new Integer(l_orderUnitRow.getLastOrderActionSerialNo()));
                
                if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderUnit.getOrderType()))
                {
					//set���t����
					l_mapChanges.put("buy_order_quantity", new Double("0"));
					//set���t����
					l_mapChanges.put("sell_order_quantity", new Double(l_dblOrderQuantity));
                }
                else if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderUnit.getOrderType()))
                {
					//set���t����
					l_mapChanges.put("buy_order_quantity", new Double(l_dblOrderQuantity));
					 //set���t����
					l_mapChanges.put("sell_order_quantity", new Double("0"));
                }

                //set�w�l
                l_mapChanges.put(
                    "limit_price",
                    new Double(l_dblLimitPrice));
                //set���s�����iSONAR�j
                l_mapChanges.put(
                    "execution_condition",
                    l_strExecutionCondition);
                //set�l�i�����iSONAR�j
                l_mapChanges.put(
                    "price_condition_type",
                    l_strPriceConditionType);
                //set�����o�H�敪
                l_mapChanges.put(
                    "submit_order_route_div",
                    l_orderUnitRow.getSubmitOrderRouteDiv());
                //set�X�V���t
                l_mapChanges.put(
                    "last_updated_timestamp",
                    l_tsLastUpdateDateTime);
                int l_intResults = l_queryProcesser.doUpdateAllQuery(
                    (RowType)HostEqtypeOrderAllParams.TYPE,
                    l_strWhere,
                    l_strBindValues,
                    l_mapChanges);
                if (l_intResults == 0)
                {
                    return DefaultMarketRequestSendResult
                        .newFailedResultInstance(
                            ProcessingResult.newFailedResultInstance(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00010));
                }
            }
            else
            {
                boolean l_blnQueueInsUpdFlg = true;

                l_orderManager.notifyRLS(
                    l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED);
                
                //6. is�����s��ʒm�v(�����P��)(
                boolean l_isChangeMarketNotify =
                    l_orderManager.isChangeMarketNotify(l_orderUnit);
                //7. is�s��J�ǎ��ԑ�()
                boolean l_isTradeOpenTimeZone =
                    WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
                // is������x�e���ԑ�
                boolean l_isTradeRestTimeZone =
                    WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone();

                if (l_isTradeRestTimeZone ||
                    (l_isChangeMarketNotify && l_isTradeOpenTimeZone))
                {
                    log.debug("is�����s��ʒm==" + l_isChangeMarketNotify);
                    log.debug("is�s��J�ǎ��ԑ�==" + l_isTradeOpenTimeZone);
                    log.debug("is������x�e���ԑ�==" + l_isTradeRestTimeZone);
                    
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                    HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = null;
                    if (l_isTradeRestTimeZone)
                    {
                        l_hostEqtypeOrderAllParams =
                            l_frontOrderService.getHostEqtypeOrderAll(
                                (EqTypeOrderUnit)l_orderUnit);
                    }
                    
                    if (l_hostEqtypeOrderAllParams == null)
                    {
                        //is�����s��ʒm==false(�����ʒm�s�v�̏ꍇ)�̓L���[�ɓo�^�E�X�V���s��Ȃ�
                        if(!l_isChangeMarketNotify)
                        {
                            log.debug("�����ʒm�s�v�̂��߁A�L���[�ɓo�^�E�X�V���s��Ȃ��i��������������j");
                            l_blnQueueInsUpdFlg = false;
                        }
                        else
                        {
                            l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
                        
                            // �f�[�^�R�[�h
                            l_hostEqtypeOrderAllParams.setRequestCode(
                                WEB3HostRequestCodeDef.EQUITY_ORDER_CHANGE);     
                            // ����ID
                            l_hostEqtypeOrderAllParams.setAccountId(l_orderUnitRow.getAccountId());
                            // �،���ЃR�[�h
                            l_hostEqtypeOrderAllParams.setInstitutionCode(
                                l_strInstitutionCode);
                            // ���X�R�[�h
                            l_hostEqtypeOrderAllParams.setBranchCode(
                                l_strBranchCode);
                            // �����R�[�h
                            l_hostEqtypeOrderAllParams.setAccountCode(
                                l_strAccountCode);
                            // ���҃R�[�h�iSONAR�j
                            l_hostEqtypeOrderAllParams.setSonarTraderCode(
                                l_strTraderCode);
                            // ���ʃR�[�h
                            l_hostEqtypeOrderAllParams.setOrderRequestNumber(
                                l_strOrderRequestNumber);
                            // ��������ԍ�
                            l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                                l_orderUnitRow.getLastOrderActionSerialNo());
                            // �����R�[�h
                            l_hostEqtypeOrderAllParams.setProductCode(
                                l_strPoductCode);
                            // �󒍓���
                            l_hostEqtypeOrderAllParams.setReceivedDateTime(
                                l_tsReceivedDateTime);
                            // �����o�H�敪
                            l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                                l_orderUnitRow.getSubmitOrderRouteDiv());
                            if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                            {
                                // ���t����
                                l_hostEqtypeOrderAllParams.setSellOrderQuantity(
                                    l_orderUnitRow.getConfirmedQuantity());
                                // ���t����
                                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(0D);
                            }
                            else
                            {
                                // ���t����
                                l_hostEqtypeOrderAllParams.setSellOrderQuantity(0D);
                                // ���t����
                                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(
                                    l_orderUnitRow.getConfirmedQuantity());
                            }
                            // �w�l
                            l_hostEqtypeOrderAllParams.setLimitPrice(
                                l_orderUnitRow.getConfirmedPrice());
                            // ���s�����iSONAR�j
                            String l_strConfirmedExecConditionType = null;
                            if (l_orderManager.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
                            {
                                l_strConfirmedExecConditionType =
                                    WEB3ExecutionConditionDef.COME_TO_TERMS;
                            }
                            else
                            {
                                l_strConfirmedExecConditionType = 
                                    l_orderManager.getExecutionConditionTypeSonar(
                                        l_orderUnitRow.getConfirmedExecConditionType());
                            }
                            l_hostEqtypeOrderAllParams.setExecutionCondition(
                                l_strConfirmedExecConditionType);
                            // �l�i�����iSONAR�j
                            l_hostEqtypeOrderAllParams.setPriceConditionType(
                                l_orderManager.getPriceConditionTypeSonar(
                                    l_orderUnitRow.getConfirmedPriceConditionType()));
                            // ��������
                            l_hostEqtypeOrderAllParams.setChangeQuantity(
                                l_dblOrderQuantity);
                            // �����w�l
                            l_hostEqtypeOrderAllParams.setChangeLimitPrice(
                                l_dblLimitPrice);
                            // �������s�����iSONAR�j
                            l_hostEqtypeOrderAllParams.setChangeExecutionCondition(
                                l_strExecutionCondition);
                            // �����l�i�����iSONAR�j
                            l_hostEqtypeOrderAllParams.setChangePriceConditionType(
                                l_strPriceConditionType);
                            // ��������
                            l_hostEqtypeOrderAllParams.setChangeOrderTime(
                                l_strLastUpdatedDate);
                            // �����敪�i�O���^�����j
                            l_hostEqtypeOrderAllParams.setChangeOrderDateDiv(
                                l_strChangeOrderDateDiv);
                            // ����敪
                            l_hostEqtypeOrderAllParams.setCancelDiv(
                                WEB3CancelDivDef.EXCEPT_CANCEL);
                            // �t�����g����������敪�R�[�h
                            WEB3GentradeFinObjectManager l_finObjectManager =
                                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                            Market l_market =
                                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
                            String l_strMarketCode = l_market.getMarketCode();
                            l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode(
                                l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
                            // �t�����g�����V�X�e���敪
                            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
                            EqTypeTradedProduct l_tradedProduct =
                                (EqTypeTradedProduct)l_eqtypeOrderUnit.getTradedProduct();
                            EqtypeTradedProductRow l_tradedProductRow =
                                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                            String l_strFrontOrderSystemCode =
                                l_frontOrderService.getFrontOrderSystemCode(
                                    l_strMarketCode,
                                    l_tradedProductRow.getOpenOtcDiv());
                            l_hostEqtypeOrderAllParams.setFrontOrderSystemCode(
                                l_strFrontOrderSystemCode);
                            // �t�����g��������敪�R�[�h
                            l_hostEqtypeOrderAllParams.setFrontOrderTradeCode(
                                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                            // �Г���������
                            l_hostEqtypeOrderAllParams.setCorpCode(
                                l_frontOrderService.getCorpCode(l_eqtypeOrderUnit));
                            // �i������j�Г���������
                            l_hostEqtypeOrderAllParams.setOrgCorpCode(
                                l_frontOrderService.getOrgCorpCode(l_eqtypeOrderUnit));
                            // �S���������敪
                            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv(
                                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                            // �����敪
                            l_hostEqtypeOrderAllParams.setStatus(
                                WEB3FrontOrderStatusDef.NOT_DEAL);
                        
                            l_queryProcesser.doInsertQuery(l_hostEqtypeOrderAllParams);
                        }
                    }
                    else
                    {
                        // ��������ԍ�
                        l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                            l_orderUnitRow.getLastOrderActionSerialNo());
                        // �󒍓���
                        l_hostEqtypeOrderAllParams.setReceivedDateTime(
                            l_tsReceivedDateTime);
                        // �����o�H�敪
                        l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                            l_orderUnitRow.getSubmitOrderRouteDiv());
                        // ��������
                        l_hostEqtypeOrderAllParams.setChangeQuantity(
                            l_dblOrderQuantity);
                        // �����w�l
                        l_hostEqtypeOrderAllParams.setChangeLimitPrice(
                            l_dblLimitPrice);
                        // �������s�����iSONAR�j
                        l_hostEqtypeOrderAllParams.setChangeExecutionCondition(
                            l_strExecutionCondition);
                        // �����l�i�����iSONAR�j
                        l_hostEqtypeOrderAllParams.setChangePriceConditionType(
                            l_strPriceConditionType);
                        // ��������
                        l_hostEqtypeOrderAllParams.setChangeOrderTime(
                            l_strLastUpdatedDate);
                        // �����敪�i�O���^�����j
                        l_hostEqtypeOrderAllParams.setChangeOrderDateDiv(
                            l_strChangeOrderDateDiv);
                        //set�X�V���t
                        l_hostEqtypeOrderAllParams.setLastUpdatedTimestamp(
                            l_tsLastUpdateDateTime);
                        // �f�[�^�X�V����
                        l_queryProcesser.doUpdateQuery(
                            l_hostEqtypeOrderAllParams);
                    }
                    
                    if (l_blnQueueInsUpdFlg == true)
                    {
                        boolean l_isTriggerIssue =
                            WEB3GentradeTradingTimeManagement
                                .isSubmitMarketTrigger(null);
                        WEB3GentradeFinObjectManager l_finObjectManager =
                            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                        Market l_market =
                            l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
                        boolean l_isSubmitMqTrigger =
                            WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                                l_strInstitutionCode,
                                l_orderUnitRow.getProductType(),
                                l_market.getMarketCode(),
                                l_orderUnitRow.getSubmitOrderRouteDiv(),
                                l_hostEqtypeOrderAllParams.getFrontOrderSystemCode());
                    
                        if (l_isTriggerIssue && l_isSubmitMqTrigger)
                        {
                            String l_strMQDataCode =
                                l_frontOrderService.getChangeCancelMQDataCode(
                                    l_orderUnitRow.getSubmitOrderRouteDiv());
                            if (l_strMQDataCode == null)
                            {
                                log.debug("�V�K�������M �����i������~���j�I�I�I");
                                return DefaultMarketRequestSendResult
                                    .newSuccessResultInstance(
                                    l_lngMsgTokenId);
                            }
                            //12.WEB3MQMessageSpec(String, String)(
                            WEB3MQMessageSpec l_mqMessageSpec =
                                new WEB3MQMessageSpec(
                                l_strInstitutionCode,
                                l_strMQDataCode);
                            //13.send(WEB3MQMessageSpec)(
                            WEB3MQGatewayService l_mqGatewayService =
                                (WEB3MQGatewayService)Services.getService(
                                    WEB3MQGatewayService.class);
                            WEB3MQSendResult l_web3MQSendResult = l_mqGatewayService.send(l_mqMessageSpec);
                            ProcessingResult l_processingResult = null;
                            if (l_web3MQSendResult.isSuccessResult())
                            {
                                log.debug("==> �ԍϒ����������M �����I�I�I");
                                return DefaultMarketRequestSendResult
                                    .newSuccessResultInstance(l_lngMsgTokenId);
                            }
                            else
                            {
                                log.debug("==> �ԍϒ����������M ���s �I�I�I");
                                l_processingResult =ProcessingResult.newFailedResultInstance(
                                            l_web3MQSendResult.getErrorInfo());
                                return DefaultMarketRequestSendResult
                                            .newFailedResultInstance(l_processingResult);
                            }
                        }
                    }
                }
                else
                {
                    l_blnQueueInsUpdFlg = false;
                }

                if (l_blnQueueInsUpdFlg == false)
                {
                    //15.�������������m��C���^�Z�v�^( )(
                    WEB3EquityChangeConfirmInterceptor l_changeConfirmInterceptor =
                        new WEB3EquityChangeConfirmInterceptor();
           
                    //16. setThreadLocalPersistenceEventInterceptor(�������������m��C���^�Z�v�^ : EqTypeOrderManagerPersistenceEventInterceptor)
                    l_orderManager.setThreadLocalPersistenceEventInterceptor(
                        l_changeConfirmInterceptor);
                    //17.getMarketResponseReceiverCallbackService( )
                    MarketAdapter l_marketAdapter =
                        l_finApp
                            .getTradingModule(ProductTypeEnum.EQUITY)
                            .getMarketAdapter();
                    EqTypeMarketResponseReceiverCallbackService l_marketResponseReciverCallbackService =
                        (EqTypeMarketResponseReceiverCallbackService)l_marketAdapter
                            .getMarketResponseReceiverCallbackService();
                    //18.DefaultChangeOrderAcceptedMarketResponseMessage
                    DefaultChangeOrderAcceptedMarketResponseMessage l_acceptedMarketResponseMessage =
                        new DefaultChangeOrderAcceptedMarketResponseMessage(
                            l_orderUnitRow.getOrderId());
                    //19.process
                    ProcessingResult l_processingResult = l_marketResponseReciverCallbackService.process(
                        l_acceptedMarketResponseMessage);
                
                    if (l_processingResult.isFailedResult())
                    {
                        return DefaultMarketRequestSendResult
                             .newFailedResultInstance(
                             l_processingResult);
                    }
                }
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME,l_dfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME,l_dne);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME,l_dqe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__", l_wbe);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        log.exiting(STR_METHOD_NAME);

        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                        l_lngMsgTokenId);

    }

    /**
     * (non-Javadoc)
     * @@see
     * com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketRequestSenderService#send(com
     * .fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeSwapContractOrde
     * rMarketRequestMessage, boolean)
     * @@param arg0
     * @@param arg1
     * @@return MarketRequestSendResult
     * @@throws TooLateException
     * @@roseuid 40349AC3016F
     */
    public MarketRequestSendResult send(
        EqTypeChangeSwapContractOrderMarketRequestMessage arg0,
        boolean arg1)
        throws TooLateException
    {
        return null;
    }

    /**
     * (non-Javadoc)
     * @@see
     * com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSenderService#send(com.fit
     * echlabs.xtrade.plugin.tc.gentrade.MarketRequestMessage)
     * @@param arg0
     * @@return MarketRequestSendResult
     * @@roseuid 40349AC302B7
     */
    public MarketRequestSendResult send(MarketRequestMessage arg0)
    {
        return null;
    }
}
@
