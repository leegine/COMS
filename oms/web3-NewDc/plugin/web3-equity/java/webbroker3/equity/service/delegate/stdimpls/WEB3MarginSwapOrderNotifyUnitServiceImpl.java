head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapOrderNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�����ʒm�ꌏ�T�[�r�XImpl(WEB3MarginSwapOrderNotifyUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/28 �X��   (SRA)  �c�Č��Ή�
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3DealedTypeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.WEB3MarginSwapContractOrderSpec;
import webbroker3.equity.WEB3MarginSwapMarginUpdateInterceptor;
import webbroker3.equity.data.HostEqtypeSwapReceiptParams;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginOrderExecNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyDataAdapter;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;


/**
 * �i�M�p����������n�����ʒm�ꌏ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p����������n�����ʒm�ꌏ�T�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3MarginSwapOrderNotifyUnitServiceImpl
    implements WEB3MarginSwapOrderNotifyUnitService
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapOrderNotifyUnitServiceImpl.class);


    /**
     * (�R���X�g���N�^)�B<BR>
     */
    public WEB3MarginSwapOrderNotifyUnitServiceImpl()
    {
    }

    /**
     * (notify�������n����)�B<BR>
     * <BR>
     * �������n�����ʒm���������{����B <BR>
     * <BR>
     * �V�[�P���X�}�u�i�M�p����������n�����ʒm�ꌏ�T�[�r�X�jnotify�������n�����v�Q�ƁB <BR>
     * <BR>
     * @@param l_params - �������n���͒ʒm�L���[Params�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    public void notifySwapOrder(WEB3MarginSwapOrderNotifyDataAdapter l_adapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifySwapOrder(HostEqtypeSwapReceiptParams)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule
            = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeAccountManager l_accountManager
            = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3EquityBizLogicProvider l_provider
            = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
        WEB3EquityProductManager l_productManager
            = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        WEB3EquityOrderManager l_orderManager
            = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        WEB3EquityPositionManager l_positionManager
            = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        //--------------------
        //param����̒l�̎��o��
        //--------------------
        HostEqtypeSwapReceiptParams l_params = l_adapter.getDataSourseObject();
        log.debug(l_params.toString());
        String l_strInstitutionCode     = l_params.getInstitutionCode();
        String l_strBranchCode          = l_params.getBranchCode();
        String l_strAccountCode         = l_params.getAccountCode();
        String l_strProductCode         = l_params.getProductCode();
        String l_strSonnarRepaymentType = l_params.getSonarRepaymentType();
        String l_strOrderRequestNumber  = l_params.getOrderRequestNumber();
        String l_strTraderCode          = l_params.getTraderCode();
        Timestamp l_tsBizDatetime       = l_params.getBizDatetime();
        double l_dblQuantity            = l_params.getQuantity();


        WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
            l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

        WEB3GentradeSubAccount l_subAccount;
        try {
            l_subAccount = (WEB3GentradeSubAccount)
                l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y���⏕���������B", l_nfe);
        }

        boolean l_blnIsLong = l_adapter.isLong();

        WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_adapter.getMarket();
        long l_lngMarketId = l_market.getMarketId();

        TaxTypeEnum l_taxtype = l_adapter.getTaxType();
        String l_strRepeymentType = l_adapter.getRepaymentType();
        double l_dblRepaymentNum = l_adapter.getRepaymentNum();

        WEB3GentradeInstitution l_institution;
        try
        {
            l_institution = (WEB3GentradeInstitution)
                l_accountManager.getInstitution(l_strInstitutionCode);
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y���،���Ж����B", l_nfe);
        }

        WEB3EquityProduct l_product;
        try
        {
            l_product = (WEB3EquityProduct)
                l_productManager.getProduct(l_institution, l_strProductCode);
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y�����������B", l_nfe);
        }
        long l_lngProductId = l_product.getProductId();


        //--------------------
        //create���ό������׈ꗗ
        //--------------------
        WEB3MarginCloseMarginContractUnit[] l_clsMrgContractUnitList
            = l_positionManager.createCloseMarginContracts(
                l_subAccount, l_blnIsLong, l_lngMarketId, l_lngProductId,
                l_taxtype, l_strRepeymentType, l_dblRepaymentNum);


        //--------------------
        //create���ό����G���g��
        //--------------------
        final long l_lngOrderUnitIdCreateNew = 0;               //�V�K�������Ɏw�肷�钍���P��ID
        EqTypeSettleContractOrderEntry[] l_contractOrderEntry
            = l_orderManager.createClosingContractEntry(
                l_lngOrderUnitIdCreateNew, l_dblQuantity, l_clsMrgContractUnitList, true);


        TaxTypeEnum l_swapTaxType = l_adapter.getSwapTaxType();

        //--------------------
        //create�������n�������e
        //--------------------
        WEB3MarginSwapContractOrderSpec l_swapContractOrderSpec
            = WEB3MarginSwapContractOrderSpec.createSwapMarginOrderSpec(
                null, l_contractOrderEntry, WEB3ClosingOrderDef.OPEN_DATE,
                l_taxtype, l_swapTaxType);

        boolean l_blnIsShort = l_adapter.isShort();


        //--------------------
        //calc�T�Z��n���
        //--------------------
        double l_dblEstimatedSwapPrice = l_orderManager
            .calcEstimatedSwapPrice(l_contractOrderEntry, l_dblQuantity, null);


        //--------------------
        //�y����t���[�z�����̏ꍇ�̂�
        //--------------------
        double l_dblCapitalGain = 0.0;
        double l_dblCapitalGainTax = 0.0;

        if (l_blnIsShort)
        {
            double l_dblTotalQuantity = l_swapContractOrderSpec.getTotalQuantity();
            TaxTypeEnum l_strOdrSpcSwapTaxType = l_swapContractOrderSpec.getSwapTaxType();

            //--------------------
            //calc���n���v
            //--------------------
            l_dblCapitalGain = l_provider.calcCapitaGain(
                l_dblEstimatedSwapPrice, l_dblTotalQuantity,
                l_lngProductId, l_subAccount, l_strOdrSpcSwapTaxType);

            WEB3EquityTradedProduct l_tradedProduct;
            try
            {
                l_tradedProduct = (WEB3EquityTradedProduct)
                    l_productManager.getTradedProduct(l_product, l_market);
            }
            catch (NotFoundException l_nfe)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Y��������������B", l_nfe);
            }
            Timestamp l_tsDailyDeliveryDate = new Timestamp(l_tradedProduct.getDailyDeliveryDate().getTime());

            //--------------------
            //calc���n�v��
            //--------------------
            TaxTypeEnum l_deliveryDateTaxType = l_mainAccount.getDeliveryDateTaxType(l_tsDailyDeliveryDate);
            l_dblCapitalGainTax = l_provider.calcCapitalGainTax(
                l_subAccount, l_swapTaxType, l_dblCapitalGain, l_tsDailyDeliveryDate, l_deliveryDateTaxType);

        }

        //--------------------
        //�M�p�������n�X�V�C���^�Z�v�^()
        //--------------------
        WEB3MarginSwapMarginUpdateInterceptor l_interceptor = new WEB3MarginSwapMarginUpdateInterceptor(
            l_swapContractOrderSpec, l_strSonnarRepaymentType, l_dblEstimatedSwapPrice, l_strRepeymentType,
            l_dblRepaymentNum, l_dblCapitalGain, l_dblCapitalGainTax, null, WEB3OrderRootDivDef.HOST
        );

        l_interceptor.setOrderRequestNumber(l_strOrderRequestNumber);
        l_interceptor.setReceivedDateTime(l_tsBizDatetime);
		l_interceptor.setDeliveryDate(WEB3DateUtility.getDate(l_params.getDeliveryDate(), "yyyyMMdd"));
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        long l_lngNewOrderId = l_orderManager.createNewOrderId();
        String l_strTradingPassword =  l_mainAccount.getTradingPassword();
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

        //--------------------
        //submitSwapContractOrder
        //--------------------
        EqTypeOrderSubmissionResult l_result = l_orderManager
            .submitSwapContractOrder(l_subAccount, l_swapContractOrderSpec,
            l_lngNewOrderId, l_strTradingPassword, true);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        OrderUnit[] l_orderUnitList
            = l_orderManager.getOrderUnits(l_lngNewOrderId);


        //--------------------
        //�����o���ʒm�L���[params�̃C���X�^���X��������уv���p�e�B�Z�b�g
        //--------------------
        HostEquityOrderExecNotifyParams l_execNotifyParams
            = new HostEquityOrderExecNotifyParams();
        l_execNotifyParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_EXEC_NOTICE);
        l_execNotifyParams.setInstitutionCode(l_strInstitutionCode);
        l_execNotifyParams.setBranchCode(l_strBranchCode);
        l_execNotifyParams.setAccountCode(l_strAccountCode);
        l_execNotifyParams.setTraderCode(l_strTraderCode);
        l_execNotifyParams.setOrderRequestNumber(l_strOrderRequestNumber);
        l_execNotifyParams.setExecQuantity(l_dblQuantity);
        l_execNotifyParams.setExecPrice(0.0);
        l_execNotifyParams.setExecTimestamp(l_tsBizDatetime);
        l_execNotifyParams.setDealedType(WEB3DealedTypeDef.FULLY_EXECUTED);
        l_execNotifyParams.setStatus(WEB3StatusDef.NOT_DEAL);
        log.debug(l_execNotifyParams.toString());


        //--------------------
        //notify���
        //--------------------
        WEB3MarginOrderExecNotifyUnitService l_orderExecNotifyUnitService
            = (WEB3MarginOrderExecNotifyUnitService) Services
                .getService(WEB3MarginOrderExecNotifyUnitService.class);

        l_orderExecNotifyUnitService.notifyExecute((EqTypeOrderUnit) l_orderUnitList[0], l_execNotifyParams);

        //--------------------
        //�����ΏۃL���[UPDATE�@@(=>������)
        //--------------------
        //�ꌏ���������ֈڂ�.start
        try{
            l_params.setStatus(WEB3StatusDef.DEALT);
            l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            Processors.getDefaultProcessor().doUpdateQuery(l_params);
        }
        catch (DataNetworkException l_dne)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dne)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        //.end

        log.exiting(STR_METHOD_NAME);
    }


    /**
     * (notify�������n�������)�B<BR>
     * <BR>
     * �������n����������������{����B <BR>
     * <BR>
     * �V�[�P���X�}�u�i�M�p����������n�����ʒm�ꌏ�T�[�r�X�jnotify�������n��������v�Q�ƁB <BR>
     * <BR>
     * @@param l_params - �������n���͒ʒm�L���[Params�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    public void notifyCancelSwapOrder(WEB3MarginSwapOrderNotifyDataAdapter l_adapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyCancelSwapOrder(HostEqtypeSwapReceiptParams)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule
            = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager
            = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();


        //--------------------
        //param����̒l�̎��o��
        //--------------------
        HostEqtypeSwapReceiptParams l_params = l_adapter.getDataSourseObject();
        log.debug(l_params.toString());
        String l_strInstitutionCode     = l_params.getInstitutionCode();
        String l_strBranchCode          = l_params.getBranchCode();
        String l_strAccountCode         = l_params.getAccountCode();
        String l_strOrderRequestNumber  = l_params.getOrderRequestNumber();
        String l_strTraderCode          = l_params.getTraderCode();
        Timestamp l_tsBizDatetime       = l_params.getBizDatetime();
        double l_dblQuantity            = l_params.getQuantity();


        EqTypeOrderUnit l_orderUnit = l_orderManager.getOrderUnit(
            l_strInstitutionCode, l_strBranchCode, ProductTypeEnum.EQUITY, l_strOrderRequestNumber);


        //--------------------
        //�����o���ʒm�L���[params�̃C���X�^���X��������уv���p�e�B�Z�b�g
        //--------------------
        HostEquityOrderExecNotifyParams l_execNotifyParams
            = new HostEquityOrderExecNotifyParams();
        l_execNotifyParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_EXEC_NOTICE);
        l_execNotifyParams.setInstitutionCode(l_strInstitutionCode);
        l_execNotifyParams.setBranchCode(l_strBranchCode);
        l_execNotifyParams.setAccountCode(l_strAccountCode);
        l_execNotifyParams.setTraderCode(l_strTraderCode);
        l_execNotifyParams.setOrderRequestNumber(l_strOrderRequestNumber);
        l_execNotifyParams.setExecQuantity(l_dblQuantity);
        l_execNotifyParams.setExecPrice(0.0);
        l_execNotifyParams.setExecTimestamp(l_tsBizDatetime);
        l_execNotifyParams.setDealedType(WEB3DealedTypeDef.CANCEL);
        l_execNotifyParams.setStatus(WEB3StatusDef.NOT_DEAL);
        log.debug(l_execNotifyParams.toString());


        //--------------------
        //notify�����
        //--------------------
        WEB3MarginOrderExecNotifyUnitService l_orderExecNotifyUnitService
            = (WEB3MarginOrderExecNotifyUnitService) Services
                .getService(WEB3MarginOrderExecNotifyUnitService.class);

        l_orderExecNotifyUnitService.notifyExecuteCancel(l_orderUnit, l_execNotifyParams);


        //--------------------
        //�����ΏۃL���[UPDATE�@@(=>������)
        //--------------------
        //�ꌏ���������ֈڂ�.start
        try{
            l_params.setStatus(WEB3StatusDef.DEALT);
            l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            Processors.getDefaultProcessor().doUpdateQuery(l_params);
        }
        catch (DataNetworkException l_dne)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dne)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        //.end

        log.exiting(STR_METHOD_NAME);
    }

}
@
