head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.31.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	89c4d7d7dfd671c;
filename	TestDBUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3JUnit�e�X�g�p�ADB�֘A���[�e�B���e�B�N���X(TestDBUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/12/12  ���Ō� (���u) �V�K�쐬
*/
package test.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.dbind.DBindTransactions;
import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.dbind.gen.TableSpec;
import com.fitechlabs.xtrade.kernel.boot.MessageClassRegistry;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;

import webbroker3.accountinfo.data.CommCampaignAccHistoryParams;
import webbroker3.accountinfo.data.CommCampaignCondMstParams;
import webbroker3.accountinfo.data.CommCampaignProductMstParams;
import webbroker3.accountinfo.data.CommissionCourseMasterParams;
import webbroker3.accountinfo.data.CommissionCourseRegistParams;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountopen.data.AccOpenVoucherItemParams;
import webbroker3.accountopen.data.AccOpenVoucherMasterParams;
import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenTempParams;
import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.accountopen.data.HostAccRegVoucherParams;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherParams;
import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.aio.data.AmountRangeParams;
import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.data.HostCashTransferParams;
import webbroker3.aio.data.HostMrgsecTransNotifyParams;
import webbroker3.aio.data.HostPaymentOrderParams;
import webbroker3.aio.data.HostTransferAcceptParams;
import webbroker3.aio.data.HostTransferOrderParams;
import webbroker3.aio.data.HostTransferReceiptParams;
import webbroker3.aio.data.SecurityProductParams;
import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3CapitalGainTaxTypeDef;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3ChangeCancelEnableFlag;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3FinSaveDivDef;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3FrontOrderSystemCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MarketCodeSONARDef;
import webbroker3.common.define.WEB3OrderEngineDivDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3SubmitMqTriggerDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3SwapTradeTypeDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.define.WEB3TransferDivDef;
import webbroker3.common.define.WEB3ValidFlag;
//import webbroker3.compliance.audit.data.ComplianceAuditServiceMstParams;
import webbroker3.dirsec.data.ApManagementParams;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqParams;
import webbroker3.equity.data.EqtypeOrderExecSendMailParams;
import webbroker3.equity.data.EquityLimitPriceRangeMstParams;
import webbroker3.equity.data.EquityTickValuesMstParams;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.data.HostEqtypeSwapReceiptParams;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.feq.data.FeqOrderexecutionEndParams;
import webbroker3.gentrade.data.AccOpenDivParams;
import webbroker3.gentrade.data.AccountMailAddressParams;
import webbroker3.gentrade.data.AccountProductOrderStopParams;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadTempParams;
import webbroker3.gentrade.data.BatoBranchProductPrefParams;
import webbroker3.gentrade.data.BatoDoctypeManagementParams;
import webbroker3.gentrade.data.BatoFunctionPrefParams;
import webbroker3.gentrade.data.BatoInstBranchPrefParams;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.BranchMarketDealtCondParams;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondParams;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.CommCodeChgMstParams;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DirectDebitParams;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDivManagementParams;
//import webbroker3.gentrade.data.EleDeliveryManagementParams;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EquityCommAccountCondMstParams;
import webbroker3.gentrade.data.ExchangeRateParams;
import webbroker3.gentrade.data.FTransFinInstitutionParams;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.gentrade.data.FeqCalendarParams;
import webbroker3.gentrade.data.FinInstitutionBankParams;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.InsiderParams;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.LastClosingPriceParams;
import webbroker3.gentrade.data.LoginHistoryParams;
import webbroker3.gentrade.data.LoginHistoryPastParams;
import webbroker3.gentrade.data.MailAssortmentParams;
import webbroker3.gentrade.data.OnlineRunStatusParams;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.PtsOrderexecutionEndParams;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.RequestCodesForReadParams;
import webbroker3.gentrade.data.SecurityCashoutRestraintParams;
import webbroker3.gentrade.data.SecurityShortageAccountParams;
import webbroker3.gentrade.data.SoapConnectPrefMsgParams;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TransferedFinInstitutionParams;
import webbroker3.gentrade.data.VirtualServerChangeParams;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyParams;
import webbroker3.ifo.data.HostFotypeOrderReceiptParams;
import webbroker3.ifo.data.IfoDeliveryMonthMasterParams;
import webbroker3.ifo.data.IfoIndexMasterParams;
import webbroker3.ifo.data.IfoOrderExecSendMailParams;
//import webbroker3.ifoadmin.data.IfoCsvFileFormatParams;
import webbroker3.inform.data.InformCtrlItemMasterParams;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.mf.data.HostFrgnMmfOrderParams;
import webbroker3.mf.data.HostXbmfOrderParams;
import webbroker3.mf.data.MfFixedBuyingChangeParams;
import webbroker3.mf.data.MfFixedBuyingCondParams;
import webbroker3.mf.data.MfFixedBuyingDrawAccountParams;
import webbroker3.mf.data.MfSubAssetParams;
import webbroker3.mf.data.MutualFund2ndProductSonarParams;
import webbroker3.mf.data.MutualFundFrgncalParams;
import webbroker3.mf.data.MutualFundProductCategoryParams;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvRegiChargeParams;
import webbroker3.srvregi.data.SrvRegiCommCondParams;
import webbroker3.srvregi.data.SrvRegiKeyParams;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.tradingpower.data.FixedFinTransactionParams;
import webbroker3.tradingpower.data.PaymentRequisitMngParams;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.data.TpOtherTempRestraintParams;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpoweradmin.data.DepositAutotransferStopParams;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.data.HostRuitoCancelAcceptParams;

/**
 * (WEB3JUnit�e�X�g�p�ADB�֘A���[�e�B���e�B�N���X)<BR>
 * @@author ���Ō�
 * @@version 1.0
 */
public class TestDBUtility
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        TestDBUtility.class);   
    
    /**
     * insertWithDel���\�b�h�p�ADB�Ɋ������R�[�h��ۑ������̃��X�g<BR>
     */
    private static List rowBackupList = new ArrayList();
    
    /**
     * �R���X�g���N�^<BR>
     */
    public TestDBUtility()
    {
        super();
    }
    
    private class DeleteAllTransactionCallback implements TransactionCallback
    {
        private static final long serialVersionUID = 1L;
        
        private RowType rowType = null;
        
        public DeleteAllTransactionCallback(RowType l_rowType)
        {
            this.rowType = l_rowType;
        }
        
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            log.debug("delete all from table : " + rowType.getTableName());
            l_processor.doDeleteAllQuery(rowType);
            return null;
        }
    }
    
    private class InsertWithDelTransactionCallback implements TransactionCallback
    {
        private static final long serialVersionUID = 1L;
        
        private Row row = null;
        
        public InsertWithDelTransactionCallback(Row l_row)
        {
            this.row = l_row;
        }
        
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = ".process()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();

                try
                {
                    log.debug("insert row : " + "table : " + row.getRowType().getTableName() + 
                            " Data : " + row.toString());
                    l_processor.doInsertQuery(row);
                } 
                catch (DataQueryException l_ex)
                {
                    //insert�ł��Ȃ��ꍇ
                    List l_tempList = l_processor.doFindAllQuery(row.getRowType());
                    rowBackupList.addAll(l_tempList);        
                    
                    l_processor.doDeleteAllQuery(row.getRowType());

                    l_processor.doInsertQuery(row);

                    int l_intSize = rowBackupList.size();
                    
                    for (int i = 0; i < l_intSize; i++)
                    {
                        Row l_loopRow = (Row)rowBackupList.get(i);
                        try
                        {
                            l_processor.doInsertQuery(l_loopRow);   
                        } 
                        catch (DataQueryException l_ex2)
                        {
                            // �����Ȃ�
                        }
                    } 
                }
                //l_processor.doFindByPrimaryKeyQuery(l_row.getPrimaryKey(), " FOR UPDATE ");
                
            }
            finally
            {
                rowBackupList.clear();
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
    }
    
    private class LockTableTransactionCallback implements TransactionCallback
    {
        private static final long serialVersionUID = 1L;
        
        private RowType rowType = null;
        
        public LockTableTransactionCallback(RowType l_rowType)
        {
            this.rowType = l_rowType;
        }
        
        public Object process()
        {
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_processor.doFindAllQuery(rowType," FOR UPDATE NOWAIT ");
                return Boolean.FALSE;
            }
            catch (DataQueryException l_ex)
            {
                log.error("LockTableTransactionCallback.process", l_ex);
                if (l_ex.getMessage().indexOf("ORA-00054") > 0)
                {
                    return Boolean.TRUE;
                }
                
                return Boolean.FALSE;
            }
            catch (DataException l_ex)
            {
                log.error("LockTableTransactionCallback.process", l_ex);
                return Boolean.FALSE;
            }
        }
    }
    
    public static boolean isTableLockedSuccessful(RowType l_rowType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".isTableLockedSuccessful(RowType)";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility l_testDBUtility = new TestDBUtility();
            
            LockTableTransactionCallback l_transactionCallback = 
                l_testDBUtility.new LockTableTransactionCallback(l_rowType);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            
            // doTransaction()
            return ((Boolean)l_processor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_transactionCallback)).booleanValue();      
        }
        catch (DataException l_ex)
        {
            log.error("insertWithDel", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                TestDBUtility.class.getName()
                    + STR_METHOD_NAME);
        }
        finally
        {
            
            try
            {
                DBindTransactions.getTransactionManager().commit();
            }
            catch (Exception l_ex)
            {
                log.error("isTableLockedSuccessful", l_ex);
            }
            
        }
    }
    /**
     * �����_��Transaction��Commit�B<BR>
     * <BR>
     * 
     */
    public static void commit()
    {
        try
        {
            DBindTransactions.getTransactionManager().commit();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.toString());
        }
    }
    
    /**
     * �V����Transaction��Start���܂�<BR>
     * <BR>
     * 
     */
    public static void beginTransaction()
    {
        try
        {
            DBindTransactions.getTransactionManager().begin();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.toString());
        }
    }
    
    /**
     * �w�肳�ꂽRow�I�u�W�F�N�g��DB��insert���܂��B<BR>
     * <BR>
     * DB�ɁAPK��Unique Index�Ɠ�����Row������́A�폜���āA�}�����܂��B<BR>
     * �}���̌�ŁACommit�����{���܂��B<BR>
     * 
     * @@param l_row -
     *            insert��Row�I�u�W�F�N�g
     */
    public static void insertWithDelAndCommit(Row l_row)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".insertWithDelAndCommit(Row)";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility l_testDBUtility = new TestDBUtility();
            
            InsertWithDelTransactionCallback l_transactionCallback = 
                l_testDBUtility.new InsertWithDelTransactionCallback(l_row);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            
            // doTransaction()
            l_processor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_transactionCallback);
        }
        catch (DataException l_ex)
        {
            log.error("insertWithDel", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                TestDBUtility.class.getName()
                    + STR_METHOD_NAME);
        }
    }
    /**
     * �w�肳�ꂽRow�I�u�W�F�N�g��DB��insert���܂��B<BR>
     * <BR>
     * DB�ɁAPK��Unique Index�Ɠ�����Row������́A�폜���āA�}�����܂��B<BR>
     * @@param l_row - insert��Row�I�u�W�F�N�g
     */
    public static void insertWithDel(Row l_row)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".insertWithDel(Row)";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility l_testDBUtility = new TestDBUtility();
            
            InsertWithDelTransactionCallback l_transactionCallback = 
                l_testDBUtility.new InsertWithDelTransactionCallback(l_row);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            
            // doTransaction()
            l_processor.doTransaction(
                QueryProcessor.TX_JOIN_EXISTING,
                l_transactionCallback);
        }
        catch (DataException l_ex)
        {
            log.error("insertWithDel", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                TestDBUtility.class.getName()
                    + STR_METHOD_NAME);
        }
    }

    /**
     * �w�肳�ꂽ�e�[�u���̂��ׂă��R�[�h���폜<BR>
     * <BR>
     * @@param l_row - insert��Row�I�u�W�F�N�g
     */
    public static void deleteAllAndCommit(RowType l_rowType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".deleteAllAndCommit(Row)";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility l_testDBUtility = new TestDBUtility();
            
            DeleteAllTransactionCallback l_transactionCallback = 
                l_testDBUtility.new DeleteAllTransactionCallback(l_rowType);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            
            // doTransaction()
            l_processor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_transactionCallback);
        }
        catch (DataException l_ex)
        {
            log.error("deleteAllAndCommit", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                TestDBUtility.class.getName()
                    + STR_METHOD_NAME);
        }
    }
    /**
     * �w�肳�ꂽ�e�[�u���̂��ׂă��R�[�h���폜<BR>
     * <BR>
     * @@param l_row - insert��Row�I�u�W�F�N�g
     */
    public static void deleteAll(RowType l_rowType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".deleteAll(Row)";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility l_testDBUtility = new TestDBUtility();
            
            DeleteAllTransactionCallback l_transactionCallback = 
                l_testDBUtility.new DeleteAllTransactionCallback(l_rowType);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            
            // doTransaction()
            l_processor.doTransaction(
                QueryProcessor.TX_JOIN_EXISTING,
                l_transactionCallback);
        }
        catch (DataException l_ex)
        {
            log.error("deleteAll", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                TestDBUtility.class.getName()
                    + STR_METHOD_NAME);
        }
    }
    /**
     * @@deprecated
     * @@param l_insertData - insert��Row�I�u�W�F�N�g
     */
    public static void insertWithDel(RowType l_targetTable,String l_insertData)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertWithDel(Row)";

        String[] l_str = new String[] {
        "ACCOUNT_ID,SUB_ACCOUNT_ID,SUB_ACCOUNT_TYPE,INSTITUTION_CODE,INSTITUTION_ID,BRANCH_ID,SUB_ACCOUNT_STATUS,OPEN_DATE,CLOSE_DATE,CASH_BALANCE,CREATED_TIMESTAMP,LAST_UPDATED_TIMESTAMP",
        "333812512203,33381251220302,1,0D,33,33381,1,2006/12/11,2006/12/11,13456.000000,2006/12/11,2006/12/11"};

        String[]  l_strColumnNames = l_str[0].split(",");
        List l_valueList = new ArrayList();
        for (int i = 1; i < l_str.length; i++)
        {
            l_valueList.add(l_str[i].split(","));
        }

        try
        {
            Params l_params =
                (Params)MessageClassRegistry.getClass("row", l_targetTable.getTableName()).newInstance();
            TableSpec l_tableSpec =
                (TableSpec)MessageClassRegistry.getClass("row", l_targetTable.getTableName()).newInstance();

        }
        catch (InstantiationException e)
        {
            //log.error("")
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * �⏕�����i�ڋq����c���jRow���쐬.<BR>
     */
    public static  SubAccountParams getSubAccountRow()
    {
        SubAccountParams l_subAccountParams = new SubAccountParams();
        //�����h�c]
        l_subAccountParams.setAccountId(333812512203L);
        //�⏕�����h�c
        l_subAccountParams.setSubAccountId(33381251220301L);
        //�⏕�����^�C�v
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        //�،���ЃR�[�h
        l_subAccountParams.setInstitutionCode("0D");
        //�،����ID
        l_subAccountParams.setInstitutionId(33);
        //���X�h�c
        l_subAccountParams.setBranchId(33381L);
        //�⏕�����X�e�[�^�X
        l_subAccountParams.setSubAccountStatus(SubAccountStatusEnum.ACTIVE);
        //�����o�^��
        l_subAccountParams.setOpenDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        //��������
        l_subAccountParams.setCloseDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        //�c��(�����j
        l_subAccountParams.setCashBalance(13456.0);
        //�쐬���t
        l_subAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //�X�V���t
        l_subAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_subAccountParams;
    }

    /**
     * �،����Row���쐬.<BR>
     */
    public static InstitutionParams getInstitutionRow()
    {
        InstitutionParams l_institutionParams = new InstitutionParams();

        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(33);

        return l_institutionParams;
    }
    
    /**
     * �T�[�r�X���p�L�[�e�[�u��Row���쐬.<BR>
     */
    public static SrvRegiKeyParams getSrvRegiKeyRow()
    {
    	SrvRegiKeyParams l_srvRegiKeyParams = new SrvRegiKeyParams();

    	l_srvRegiKeyParams.setInstitutionCode("0D");
    	l_srvRegiKeyParams.setSrvDiv("1234");
    	l_srvRegiKeyParams.setSrvUseKeyType("3");
    	l_srvRegiKeyParams.setSrvUseId(1l);
    	l_srvRegiKeyParams.setSrvUseKey("SrvUserKey");
    	l_srvRegiKeyParams.setLastUpdater("scott");
    	l_srvRegiKeyParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090428","yyyyMMdd"));
    	l_srvRegiKeyParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090429","yyyyMMdd"));
        return l_srvRegiKeyParams;
    }

    /**
     * ���XRow���쐬.<BR>
     */
    public static BranchParams getBranchRow()
    {
        BranchParams l_branchParams = new BranchParams();

        l_branchParams.setBranchId(33381);
        l_branchParams.setInstitutionCode("0D");
        l_branchParams.setInstitutionId(33);
        l_branchParams.setBranchCode("381");
        l_branchParams.setBranchName("�����x�X");
        l_branchParams.setBranchNameAlt1("TOKYO BRANCH");
        l_branchParams.setBranchType(BranchTypeEnum.WEB_BRANCH);
        l_branchParams.setMaxHandlingPriceInd(150000000);
        l_branchParams.setMaxHandlingPriceCorp(600000000);
        l_branchParams.setMaxHandlingPriceIndOption(600000000);
        l_branchParams.setMaxHandlingPriceCorpOption(600000000);
        l_branchParams.setMaxHandlingPriceIndFuture(600000000);
        l_branchParams.setMaxHandlingPriceCorpFuture(600000000);
        l_branchParams.setMaxContPriceAllInd(600000000.000000);
        l_branchParams.setMaxContPriceAllCorp(150000000.000000);
        l_branchParams.setMaxContPriceProductInd(600000000.000000);
        l_branchParams.setMaxContPriceProductCorp(150000000.000000);
        l_branchParams.setMaxContPrice1dayInd(600000000.000000D);
        l_branchParams.setMaxContPrice1dayCorp(1.000000D);
        l_branchParams.setHandlingMarketMake(0);
        l_branchParams.setHandlingNotLoanTransStock(0);
        l_branchParams.setEmailAddress("info@@naitou-sec.co.jp");
        l_branchParams.setLoginStopDiv("1");
        l_branchParams.setAccountCodeMin(6);
        l_branchParams.setAccountCodeMax(20);
        l_branchParams.setAccountCodeCheckMode("2");
        l_branchParams.setInsiderDefaultRegistDiv("0");
        l_branchParams.setMarginSysDiv("1");
        l_branchParams.setMarginGenDiv("1");
        l_branchParams.setFstkDiv("1");
        l_branchParams.setMstkDiv("1");
        l_branchParams.setOptionDiv("1");
        l_branchParams.setFutureDiv("1");
        l_branchParams.setMfDiv("1");
        l_branchParams.setRuitoDiv("0");
        l_branchParams.setQualifiedInvestorConfirmDiv("0");
        l_branchParams.setMarginDepositRate(0.000000D);
        l_branchParams.setCashMarginDepositRate(0.000000D);
        l_branchParams.setMarginDepositRate(0.000000D);
        l_branchParams.setMinMarginDeposit(0.000000D);
        l_branchParams.setMinIfoDeposit(0.000000D);
        l_branchParams.setCalcSubstituteRate(0.000000D);
        l_branchParams.setMarginSecCheckRate(0.000000D);
        l_branchParams.setShortMarginRestrainDiv("0");
        l_branchParams.setShortMarginRestrainUnit(0.000000D);
        l_branchParams.setShortSellOrderValidMinute(0);
        l_branchParams.setMarginSecTransferMaxCount(5);
        l_branchParams.setCloseWorngEquityMargin(10);
        l_branchParams.setCloseWorngOption(5);
        l_branchParams.setCloseWorngFeq(0);
        l_branchParams.setLastUpdater("administrator");
        l_branchParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_branchParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_branchParams.setMaxHandlingPriceCloseDiv("1");
        l_branchParams.setOffFloorDiv("1");
        l_branchParams.setCloseWorngFeq(5);

        return l_branchParams;
    }

    /**
     * �ڋq�}�X�^�[Row���쐬.<BR>
     */
    public static MainAccountParams getMainAccountRow()
    {
        MainAccountParams l_mainAccountParams = new MainAccountParams();

        l_mainAccountParams.setAccountId(333812512246L);
        l_mainAccountParams.setInstitutionCode("0D");
        l_mainAccountParams.setInstitutionId(33L);
        l_mainAccountParams.setAccountCode("2512246");
        l_mainAccountParams.setBranchId(33381L);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setSonarTraderCode("11124");
        l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
        l_mainAccountParams.setFamilyName("�����@@�l�Y");
        l_mainAccountParams.setFamilyNameAlt1("Ųĳ �۳");
        l_mainAccountParams.setGivenNameAlt1("Siro");
        l_mainAccountParams.setZipCode("1001238");
        l_mainAccountParams.setSubZipCode("1001238");
        l_mainAccountParams.setAddressLine1("�����s");
        l_mainAccountParams.setAddressLine2("�]����");
        l_mainAccountParams.setAddressLine3("�[��T");
        l_mainAccountParams.setTelephone("38201115");
        l_mainAccountParams.setMobile("901115");
        l_mainAccountParams.setFax("38202226");
        l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setResident("0");
        l_mainAccountParams.setNewAccountDiv("0");
        l_mainAccountParams.setViaTrustBankDiv("0");
        l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
        l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
        l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
        l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
        l_mainAccountParams.setPersonIdentify("1");
        l_mainAccountParams.setEraBorn("3");
        l_mainAccountParams.setBornDate("101013");
        l_mainAccountParams.setSex("1");
        l_mainAccountParams.setYellowCustomer("0");
        l_mainAccountParams.setHtSettlementWay("0");
        l_mainAccountParams.setBankAccountRegi("0");
        l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
        l_mainAccountParams.setExaminLockFlag("0");
        l_mainAccountParams.setMngLockFlag("0");
        l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
        l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
        l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
        l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
        l_mainAccountParams.setBranchLock("0");
        l_mainAccountParams.setOrderPermission("0");
        l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
        l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
        l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
        l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
        l_mainAccountParams.setQualifiedInstInvestorDiv("0");
        l_mainAccountParams.setMarginSysAccOpenDiv("0");
        l_mainAccountParams.setMarginGenAccOpenDiv("0");
        l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
        l_mainAccountParams.setForeignSecAccOpenDiv("1");
        l_mainAccountParams.setIfoAccOpenDivTokyo("0");
        l_mainAccountParams.setIfoAccOpenDivOsaka("0");
        l_mainAccountParams.setIfoAccOpenDivNagoya("0");
        l_mainAccountParams.setRuitoAccOpenDiv("0");
        l_mainAccountParams.setMrfAccOpenDiv("0");
        l_mainAccountParams.setFxAccOpenDiv("0");
        l_mainAccountParams.setFeqConAccOpenDiv("0");
        l_mainAccountParams.setTopPageId("0");
        l_mainAccountParams.setQuotoType("0");
        l_mainAccountParams.setTradingReportDiv("1");
        l_mainAccountParams.setPositionReportDiv("9");
        l_mainAccountParams.setPositionReportCycleDiv("1");
        l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setEmailLastUpdater("2512246");
        l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setTradingPasswordUpdater("2512246");
        l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setMbOffLastUpdater("2512246");
        l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setEnableOrderLastUpdater("2512246");
        l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
        l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
        l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setMrfFundCode("1");
        l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setSpMngAccOpenDiv("0");
        l_mainAccountParams.setOnlyMblOpnDivTimestamp(Calendar.getInstance().getTime());

        return l_mainAccountParams;
    }

    /**
     * �������Row���쐬.<BR>
     */
    public static TradingTimeParams getTradingTimeRow()
    {
        TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setTradingTimeType("26");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("0");
        l_tradingTimeParams.setStartTime("000000");
        l_tradingTimeParams.setEndTime("235959");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        l_tradingTimeParams.setEnableOrder("0");
        l_tradingTimeParams.setBizdateCalcParameter("1");
        l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_tradingTimeParams;
    }

    /**
     * �s��Row���쐬.<BR>
     */
    public static MarketParams getMarketRow()
    {
        MarketParams l_marketParams = new MarketParams();

        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("0D");
        l_marketParams.setMarketCode("SP");
        l_marketParams.setSonarMarketCode("G");
        l_marketParams.setMarketName("�V���K�|�[��");
        l_marketParams.setOpenTime("09:00");
        l_marketParams.setCloseTime("15:00");
        l_marketParams.setSuspension("0");
        l_marketParams.setChangeableType("1");
        l_marketParams.setFeqOrderEmpDiv("7");
        l_marketParams.setFeqOrderRequestDiv("1");
        l_marketParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_marketParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_marketParams;
    }

    /**
     * �i���X�s���.�O���j�戵����Row���쐬.<BR>
     */
    public static FeqBranchMarketDealtCondParams getFeqBranchMarketDealtCondRow()
    {
        FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams = new FeqBranchMarketDealtCondParams();

        l_feqBranchMarketDealtCondParams.setInstitutionCode("0D");
        l_feqBranchMarketDealtCondParams.setBranchCode("381");
        l_feqBranchMarketDealtCondParams.setMarketCode("SP");
        l_feqBranchMarketDealtCondParams.setMartCanDealtEquity("1");
        l_feqBranchMarketDealtCondParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_feqBranchMarketDealtCondParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_feqBranchMarketDealtCondParams;
    }
    
    /**
     * �����҃}�X�^�[Row���쐬.<BR>
     */
    public static InsiderParams getInsiderRow()
    {
        InsiderParams l_insiderParams = new InsiderParams();
        l_insiderParams.setInstitutionCode("1D");
        l_insiderParams.setBranchId(33381);
        l_insiderParams.setAccountId(333812512246L);
        l_insiderParams.setProductId(3304148080000L);
        l_insiderParams.setRelationDiv("1");
        l_insiderParams.setRegistDiv("0");
        l_insiderParams.setLastUpdater("123456");
        l_insiderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_insiderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_insiderParams;
    }

    /**
     * ����Row���쐬.<BR>
     */
    public static ProductParams getProductRow()
    {
        ProductParams l_productParams = new ProductParams();

        l_productParams.setProductId(3304148080000L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_productParams.setStandardName("�V���Z���e���X");
        l_productParams.setLotSize(0.000000D);
        l_productParams.setCalcSize(1.000000D);
        l_productParams.setEstimationPrice(0.000000D);
        l_productParams.setMarginRatio(0.000000D);
        l_productParams.setSecuritiesEstimationRatio(0.000000D);
        l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_productParams;
    }

    /**
     * �O������Row���쐬.<BR>
     */
    public static FeqProductParams getFeqProductRow()
    {
        FeqProductParams l_feqProductParams = new FeqProductParams();

        l_feqProductParams.setProductId(3304148080000L);
        l_feqProductParams.setInstitutionCode("0D");
        l_feqProductParams.setProductCode("N8080");
        l_feqProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_feqProductParams.setStandardNameKana("�V���Z���e���X");
        l_feqProductParams.setStandardNameKanji("ST�[�Z�����͏W�c");
        l_feqProductParams.setOffshoreProductCode("200025");
        l_feqProductParams.setMarketCode("N2");
        l_feqProductParams.setCurrencyCode("001");
        l_feqProductParams.setBooksClosingYm1("1231");
        l_feqProductParams.setCapitalGainTaxDealingsReg(1);
        l_feqProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_feqProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_feqProductParams;
    }

    /**
     * �O������Row���쐬.<BR>
     */
    public static FeqTradedProductParams getFeqTradedProductRow()
    {
        FeqTradedProductParams l_feqTradedProductParams = new FeqTradedProductParams();

        l_feqTradedProductParams.setTradedProductId(1006160060005L);
        l_feqTradedProductParams.setInstitutionCode("0D");
        l_feqTradedProductParams.setProductId(3304148080000L);
        l_feqTradedProductParams.setMarketId(3303L);
        l_feqTradedProductParams.setListFlag(BooleanEnum.TRUE);
        l_feqTradedProductParams.setListedDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
        l_feqTradedProductParams.setUnlistedDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
        l_feqTradedProductParams.setLastClosingPrice(0.000000D);
        l_feqTradedProductParams.setTradeStop(1);
        l_feqTradedProductParams.setBuyStop(0);
        l_feqTradedProductParams.setSellStop(0);
        l_feqTradedProductParams.setBuyLotSize(100.000000D);
        l_feqTradedProductParams.setBuyMinQty(100.000000D);
        l_feqTradedProductParams.setSellLotSize(100.000000D);
        l_feqTradedProductParams.setSellMinQty(100.000000D);
        l_feqTradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_feqTradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_feqTradedProductParams;
    }

    /**
     * �ڋq�����ʎ����~Row���쐬.<BR>
     */
    public static AccountProductOrderStopParams getAccountProductOrderStopRow()
    {
        AccountProductOrderStopParams l_accountProductOrderStopParams = new AccountProductOrderStopParams();

        l_accountProductOrderStopParams.setInstitutionCode("0D");
        l_accountProductOrderStopParams.setBranchId(33381L);
        l_accountProductOrderStopParams.setAccountId(333812512203L);
        l_accountProductOrderStopParams.setProductId(3304148080000L);
        l_accountProductOrderStopParams.setApplyStartDate(WEB3DateUtility.getDate("20050701","yyyyMMdd"));
        l_accountProductOrderStopParams.setApplyEndDate(WEB3DateUtility.getDate("20050731","yyyyMMdd"));
        l_accountProductOrderStopParams.setStopTradeDivBuyCash("1");
        l_accountProductOrderStopParams.setStopTradeDivSellCash("0");
        l_accountProductOrderStopParams.setStopTradeDivLongMargin("0");
        l_accountProductOrderStopParams.setStopTradeDivShortMargin("0");
        l_accountProductOrderStopParams.setStopDivLongCloseMargin("0");
        l_accountProductOrderStopParams.setStopDivShortCloseMargin("0");
        l_accountProductOrderStopParams.setStopDivLongSwapMargin("0");
        l_accountProductOrderStopParams.setStopDivShortSwapMargin("0");
        l_accountProductOrderStopParams.setStopDivBuyMiniStock("0");
        l_accountProductOrderStopParams.setStopDivSellMiniStock("0");
        l_accountProductOrderStopParams.setLastUpdater("admin");
        l_accountProductOrderStopParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_accountProductOrderStopParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_accountProductOrderStopParams;
    }

    public static EqtypeTradedProductParams getEqtypeTradedProductRow()
    {
        EqtypeTradedProductParams l_eqtypeTradedProductParams = new EqtypeTradedProductParams();
        l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
        l_eqtypeTradedProductParams.setProductId(330304148080000L);
        l_eqtypeTradedProductParams.setInstitutionCode("0D");
        l_eqtypeTradedProductParams.setMarketId(3303L);
        l_eqtypeTradedProductParams.setListFlag(BooleanEnum.TRUE);
        l_eqtypeTradedProductParams.setListType("1");
        l_eqtypeTradedProductParams.setNewListType("1");
        l_eqtypeTradedProductParams.setListedDate(Calendar.getInstance().getTime());
        l_eqtypeTradedProductParams.setMarginableFlag(BooleanEnum.TRUE);
        l_eqtypeTradedProductParams.setShortableFlag(BooleanEnum.TRUE);
        l_eqtypeTradedProductParams.setMiniStockCanDealt(BooleanEnum.TRUE);
        l_eqtypeTradedProductParams.setLotSize(100.0D);
        l_eqtypeTradedProductParams.setLastClosingPrice(1000.0D);
        l_eqtypeTradedProductParams.setMiniStockFlag(BooleanEnum.TRUE);
        l_eqtypeTradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_eqtypeTradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_eqtypeTradedProductParams.setBasePrice(10.0D);
        return l_eqtypeTradedProductParams;
    }

    public static EqtypeTradedProductUpdqParams getEqtypeTradedProductUpdqRow()
    {
        EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams = new EqtypeTradedProductUpdqParams();
        l_eqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
        l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20080701");
        l_eqtypeTradedProductUpdqParams.setProductId(330304148080000L);
        l_eqtypeTradedProductUpdqParams.setInstitutionCode("0D");
        l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
        l_eqtypeTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
        l_eqtypeTradedProductUpdqParams.setListType("1");
        l_eqtypeTradedProductUpdqParams.setNewListType("1");
        l_eqtypeTradedProductUpdqParams.setListedDate(Calendar.getInstance().getTime());
        l_eqtypeTradedProductUpdqParams.setMarginableFlag(BooleanEnum.TRUE);
        l_eqtypeTradedProductUpdqParams.setShortableFlag(BooleanEnum.TRUE);
        l_eqtypeTradedProductUpdqParams.setMiniStockCanDealt(BooleanEnum.TRUE);
        l_eqtypeTradedProductUpdqParams.setLotSize(100.0D);
        l_eqtypeTradedProductUpdqParams.setLastClosingPrice(1000.0D);
        l_eqtypeTradedProductUpdqParams.setMiniStockFlag(BooleanEnum.TRUE);
        l_eqtypeTradedProductUpdqParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_eqtypeTradedProductUpdqParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_eqtypeTradedProductUpdqParams.setBasePrice(10.0D);
        return l_eqtypeTradedProductUpdqParams;
    }
    /**
     * ��������}�X�^�[Row���쐬.<BR>
     */
    public static TradedProductParams getTradedProductRow()
    {
    	TradedProductParams l_tradedProductParams = new TradedProductParams();
    	
    	l_tradedProductParams.setTradedProductId(1006160060005L);
    	l_tradedProductParams.setInstitutionCode("0D");
    	l_tradedProductParams.setProductId(3304148080000L);
    	l_tradedProductParams.setMarketId(3303L);
    	l_tradedProductParams.setMarginRatio(70.000000D);
    	l_tradedProductParams.setSuspensionFlag(BooleanEnum.FALSE);
    	l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("20040917","yyyyMMdd"));
    	l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
    	l_tradedProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
    	l_tradedProductParams.setCollateralFlag(BooleanEnum.FALSE);
    	l_tradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
    	l_tradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
    	
    	return l_tradedProductParams;
    }
    
    /**
     * ����Row���쐬.<BR>
     */
    public static TraderParams getTraderRow(){
        TraderParams l_traderParams = new TraderParams();
        l_traderParams.setTraderId(3338111123L);
        l_traderParams.setInstitutionCode("0D");
        l_traderParams.setBranchId(33381L);
        l_traderParams.setBranchCode("381");
        l_traderParams.setTraderCode("11123");
        l_traderParams.setTraderType(TraderTypeEnum.UNDEFINED);
        l_traderParams.setLoginId(3338111123L);
        l_traderParams.setFamilyName("��a");
        l_traderParams.setGivenName("���Y");
        l_traderParams.setFamilyNameAlt1("�_�C��");
        l_traderParams.setGivenNameAlt1("�^���E");
        l_traderParams.setTradingPassword("11123");
        l_traderParams.setAccountOrderFlag("1");
        l_traderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_traderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_traderParams;
    }
    
    /**
     * �����e�[�u���i�w�b�_�jRow���쐬.<BR>
     */
    public static FeqOrderParams getFeqOrderRow()
    {
        FeqOrderParams l_feqOrderParams = new FeqOrderParams();
        l_feqOrderParams.setOrderId(123456789L);
        l_feqOrderParams.setAccountId(333812512246L);
        l_feqOrderParams.setSubAccountId(33381251220301L);
        l_feqOrderParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_feqOrderParams.setLastUpdater("200000");
        l_feqOrderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_feqOrderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_feqOrderParams;
    }
    
    public static EquityCommAccountCondMstParams getEquityCommAccountCondMstRow()
    {
    	EquityCommAccountCondMstParams l_equityCommAccountCondMstParams = new EquityCommAccountCondMstParams();
    	
    	l_equityCommAccountCondMstParams.setInstitutionCode("0D");
    	l_equityCommAccountCondMstParams.setBranchId(33381L);
    	l_equityCommAccountCondMstParams.setAccountId(333812512246L);
    	l_equityCommAccountCondMstParams.setCommProductCode("40");
    	l_equityCommAccountCondMstParams.setValidUntilBizDate("20061221");
    	l_equityCommAccountCondMstParams.setCommissionNo("01");
    	l_equityCommAccountCondMstParams.setAccountChargeRatio(80.000000D);
    	l_equityCommAccountCondMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
    	l_equityCommAccountCondMstParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
    	
    	return l_equityCommAccountCondMstParams;
    }
    
    /**
     * ������t�X�e�C�^�X�e�[�u��Row���쐬.<BR>
     */
    public static OrderAcceptStatusParams getOrderAcceptStatusRow()
    {
        OrderAcceptStatusParams l_orderAcceptStatusParams = new OrderAcceptStatusParams();
        l_orderAcceptStatusParams.setInstitutionCode("0D");
        l_orderAcceptStatusParams.setBranchCode("381");
        l_orderAcceptStatusParams.setOrderAccProduct("01");
        l_orderAcceptStatusParams.setOrderAccTransaction("01");
        l_orderAcceptStatusParams.setOrderAcceptStatus("0");
        l_orderAcceptStatusParams.setOrderAcceptStatusBefore("0");
        l_orderAcceptStatusParams.setLastUpdater("");
        l_orderAcceptStatusParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_orderAcceptStatusParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_orderAcceptStatusParams;
    }
    
    /**
     * ���������P�ʃe�[�u���i�w�b�_�jRow���쐬.<BR>
     */
    public static EqtypeOrderUnitParams getEqtypeOrderUnitRow()
    {
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = new EqtypeOrderUnitParams();
        l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
        l_eqtypeOrderUnitParams.setOrderId(123456789L);
        l_eqtypeOrderUnitParams.setAccountId(333812512203L);
        l_eqtypeOrderUnitParams.setSubAccountId(33381251220301L);
        l_eqtypeOrderUnitParams.setBranchId(33381L);
        l_eqtypeOrderUnitParams.setTraderId(3338111123L);
        l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
        l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
        l_eqtypeOrderUnitParams.setLastOrderActionSerialNo(0126);
        l_eqtypeOrderUnitParams.setLastExecutionSerialNo(8126);
        l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
        l_eqtypeOrderUnitParams.setQuantity(1200);
        l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
        l_eqtypeOrderUnitParams.setDeliveryDate(Calendar.getInstance().getTime());
        l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
        l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.NORMAL);
        l_eqtypeOrderUnitParams.setBizDate("20070117");
        l_eqtypeOrderUnitParams.setProductId(3304148080001L);
        l_eqtypeOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
        l_eqtypeOrderUnitParams.setExecutedQuantity(200);
        l_eqtypeOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
        l_eqtypeOrderUnitParams.setMarketId(3303L);
        l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
        l_eqtypeOrderUnitParams.setExecutedAmount(2000);
        l_eqtypeOrderUnitParams.setExecutedQuantity(500);
        l_eqtypeOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_eqtypeOrderUnitParams;
    }
    
    /**
     * ���������P�ʃe�[�u���i�w�b�_�jRow���쐬.<BR>
     */
    public static BranchMarketRepayDealtCondParams getBranchMarketRepayDealtCondRow()
    {
        BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams
            = new BranchMarketRepayDealtCondParams();
        
        l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
        l_branchMarketRepayDealtCondParams.setBranchCode("123");
        l_branchMarketRepayDealtCondParams.setMarketCode("N1");
        l_branchMarketRepayDealtCondParams.setRepaymentDiv("1");
        l_branchMarketRepayDealtCondParams.setRepaymentNum(123);
        l_branchMarketRepayDealtCondParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_branchMarketRepayDealtCondParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_branchMarketRepayDealtCondParams;
    }
    
    /**
     * �����ԍώw����ð��� Row���쐬.<BR>
     */
    public static EqtypeClosingContractSpecParams getEqtypeClosingContractSpecRow()
    {
        EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams = new EqtypeClosingContractSpecParams();
        l_eqTypeClosingContractSpecParams.setClosingContractSpecId(33445566778899L);
        l_eqTypeClosingContractSpecParams.setAccountId(333812512203L);
        l_eqTypeClosingContractSpecParams.setSubAccountId(33381251220301L);
        l_eqTypeClosingContractSpecParams.setOrderId(33381L);
        l_eqTypeClosingContractSpecParams.setOrderUnitId(3304148080001L);
        l_eqTypeClosingContractSpecParams.setContractId(2134566345L);
        l_eqTypeClosingContractSpecParams.setClosingSerialNo(111);
        l_eqTypeClosingContractSpecParams.setQuantity(1200);
        l_eqTypeClosingContractSpecParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_eqTypeClosingContractSpecParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_eqTypeClosingContractSpecParams;
    }
    
    /**
     * �����ԍώw����ð��� Row���쐬.<BR>
     */
    public static EqtypeContractParams getEqtypeContractRow()
    {
        EqtypeContractParams l_eqTypeContractParams = new EqtypeContractParams();
        l_eqTypeContractParams.setContractId(2134566345L);
        l_eqTypeContractParams.setAccountId(333812512203L);
        l_eqTypeContractParams.setSubAccountId(33381251220301L);
        l_eqTypeContractParams.setMarketId(33381L);
        l_eqTypeContractParams.setOriginalQuantity(123456.123);
        l_eqTypeContractParams.setQuantity(1200);
        l_eqTypeContractParams.setOriginalContractPrice(654321.321);
        l_eqTypeContractParams.setContractPrice(54321.4321);
        l_eqTypeContractParams.setContractType(ContractTypeEnum.LONG);
        l_eqTypeContractParams.setOpenDate(Calendar.getInstance().getTime());
        l_eqTypeContractParams.setCloseDate(Calendar.getInstance().getTime());
        l_eqTypeContractParams.setOriginalSetupFee(1234567.123456);
        l_eqTypeContractParams.setSetupFee(123.3);
        l_eqTypeContractParams.setOriginalSetupFeeTax(65432.321);
        l_eqTypeContractParams.setSetupFeeTax(54321.4321);
        l_eqTypeContractParams.setManagementFee(54322.43212);
        l_eqTypeContractParams.setManagementFeeTax(59322.4312);
        l_eqTypeContractParams.setInterestFee(69322.4312);
        l_eqTypeContractParams.setInterestFeeTax(69111.4312);
        l_eqTypeContractParams.setProductId(12342);
        l_eqTypeContractParams.setProductType(ProductTypeEnum.EQUITY);
        l_eqTypeContractParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_eqTypeContractParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_eqTypeContractParams.setFirstOpenDate(Calendar.getInstance().getTime());
        return l_eqTypeContractParams;
    }

    public static EqtypeOrderExecutionParams getEqtypeOrderExecutionRow()
    {
        EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = new EqtypeOrderExecutionParams();
        l_eqtypeOrderExecutionParams.setOrderExecutionId(811217L);
        l_eqtypeOrderExecutionParams.setAccountId(333812512203L);
        l_eqtypeOrderExecutionParams.setSubAccountId(33381251220301L);
        l_eqtypeOrderExecutionParams.setBranchId(33381L);
        l_eqtypeOrderExecutionParams.setOrderId(123456789L);
        l_eqtypeOrderExecutionParams.setOrderUnitId(5566778899L);
        l_eqtypeOrderExecutionParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
        l_eqtypeOrderExecutionParams.setProductType(ProductTypeEnum.EQUITY);
        l_eqtypeOrderExecutionParams.setExecSerialNo(1);
        l_eqtypeOrderExecutionParams.setExecQuantity(0.0D);
        l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());
        l_eqtypeOrderExecutionParams.setDeleteFlag(BooleanEnum.FALSE);
        l_eqtypeOrderExecutionParams.setBizDate("20070117");
        l_eqtypeOrderExecutionParams.setQuantityType(QuantityTypeEnum.QUANTITY);
        l_eqtypeOrderExecutionParams.setProductId(3304148080000L);
        l_eqtypeOrderExecutionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_eqtypeOrderExecutionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_eqtypeOrderExecutionParams.setExecPrice(0.1D);
        l_eqtypeOrderExecutionParams.setDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
        return l_eqtypeOrderExecutionParams;
    }

    public static EqtypeProductParams getEqtypeProductRow()
    {
        EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
        l_eqtypeProductParams.setProductId(3304148080000L);
        l_eqtypeProductParams.setInstitutionCode("0D");
        l_eqtypeProductParams.setProductCode("N8080");
        l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
        l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
        l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_eqtypeProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_eqtypeProductParams;
    }

    public static BranchMarketDealtCondParams getBranchMarketDealCondRow()
    {
        BranchMarketDealtCondParams l_branchMarketDealCondParams = new BranchMarketDealtCondParams();
        l_branchMarketDealCondParams.setInstitutionCode("0D");
        l_branchMarketDealCondParams.setBranchCode("381");
        l_branchMarketDealCondParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
        l_branchMarketDealCondParams.setMartCanDealtEquity("1");
        return l_branchMarketDealCondParams;
    }

    public static BranchMarketRepayDealtCondParams getBranchMarketRepayDealCondRow()
    {
        BranchMarketRepayDealtCondParams l_branchMarketRepayDealCondParams =
            new BranchMarketRepayDealtCondParams();
        l_branchMarketRepayDealCondParams.setInstitutionCode("0D");
        l_branchMarketRepayDealCondParams.setBranchCode("381");
        l_branchMarketRepayDealCondParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
        l_branchMarketRepayDealCondParams.setMartCanDealt("0");
        l_branchMarketRepayDealCondParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_branchMarketRepayDealCondParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_branchMarketRepayDealCondParams.setRepaymentDiv(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
        l_branchMarketRepayDealCondParams.setRepaymentNum(99);
        return l_branchMarketRepayDealCondParams;
    }
    
    public static EqtypeOrderParams getEqtypeOrderRow()
    {
        EqtypeOrderParams l_eqtypeOrderParams = new EqtypeOrderParams();
        l_eqtypeOrderParams.setAccountId(1906);
        l_eqtypeOrderParams.setSubAccountId(1906);
        l_eqtypeOrderParams.setProductType(ProductTypeEnum.EQUITY);
        l_eqtypeOrderParams.setOrderId(2099);
        l_eqtypeOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_eqtypeOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_eqtypeOrderParams;
        
    }
    
    /**
     * �o���I���e�[�u��(orderexecution_end)
     */
    public static OrderexecutionEndParams getOrderexecutionEndRow()
    {
        OrderexecutionEndParams l_orderexecutionEndParams = new OrderexecutionEndParams();
        l_orderexecutionEndParams.setInstitutionCode("0D");
        l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
        l_orderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
        l_orderexecutionEndParams.setCarryoverEndType(WEB3CarryoverEndTypeDef.NOT_STARTED_PROCESS);
        l_orderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_orderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_orderexecutionEndParams.setOrderexecutionEndType("0");
        return l_orderexecutionEndParams;
    }
    
    /**
     * �I�����C�����s���ʃe�[�u��(online_run_status)
     * path: $/WEBBROKER3/09Requirement�Ǘ�/2.1.����/�c�a���C�A�E�g
     *        /00.����/00.��ЁE���X�E�ڋq�E�s��/�I�����C�����s���ʃe�[�u���d�l.xls
     */
    public static OnlineRunStatusParams getOnlineRunStatusRow()
    {
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
        l_runStatusParams.setProductType(ProductTypeEnum.IFO);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("8");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        return l_runStatusParams;
    }
    
    /**
     * �����e�[�u���i�w�b�_�j(ifo_order)
     */
    public static IfoOrderParams getIfoOrderRow()
    {
        IfoOrderParams l_ifoOrderParams = new IfoOrderParams();
        l_ifoOrderParams.setOrderId(33381L);
        l_ifoOrderParams.setAccountId(123333812512238L);
        l_ifoOrderParams.setSubAccountId(33381251223801L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.OTHER);
        l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_ifoOrderParams;
    }
    
    /**
     * �����P�ʃe�[�u��(ifo_order_unit)
     */
    public static IfoOrderUnitParams getIfoOrderUnitRow()
    {
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
        
        l_ifoOrderUnitParams.setOrderUnitId(1001);
        l_ifoOrderUnitParams.setAccountId(101001010010L);
        l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
        l_ifoOrderUnitParams.setBranchId(33381);
        l_ifoOrderUnitParams.setTraderId(null);
        l_ifoOrderUnitParams.setOrderId(1001);
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
        l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderUnitParams.setFutureOptionDiv("1");
        l_ifoOrderUnitParams.setMarketId(1002);
        l_ifoOrderUnitParams.setQuantity(100);
        l_ifoOrderUnitParams.setLimitPrice(0);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
        l_ifoOrderUnitParams.setOrderConditionType("0");
        l_ifoOrderUnitParams.setOrderCondOperator(null);
        l_ifoOrderUnitParams.setStopPriceType(null);
        l_ifoOrderUnitParams.setStopOrderPrice(null);
        l_ifoOrderUnitParams.setWLimitPrice(null);
        l_ifoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
        l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_ifoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_ifoOrderUnitParams.setBizDate("20040101");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_ifoOrderUnitParams.setOrderRequestNumber("000003006");
        l_ifoOrderUnitParams.setConfirmedOrderRev("2");
        l_ifoOrderUnitParams.setOrderRev("1");
        return l_ifoOrderUnitParams;

    }
    
    /*
     * �Ǘ��҃e�[�u��(administrator)
     */
    public static AdministratorParams getAdministratorRow()
    {
        AdministratorParams l_administratorParams = new AdministratorParams();
        l_administratorParams.setAdministratorId(33381330001L);
        l_administratorParams.setAdministratorCode("330001");
        l_administratorParams.setInstitutionId(33L);
        l_administratorParams.setInstitutionCode("0D");
        l_administratorParams.setLoginId(33381330003L);
        l_administratorParams.setPermissionLevel("331");
        return l_administratorParams;
    }

    /**
     * �萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
     */
    public static CommCampaignAccHistoryParams getCommCampaignAccHistoryRow()
    {
        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams = new CommCampaignAccHistoryParams();
        l_commCampaignAccHistoryParams.setAccountId(1000100018L);
        l_commCampaignAccHistoryParams.setCampaignId(10010010018L);
        l_commCampaignAccHistoryParams.setCommCampaignName("asdf100");
        l_commCampaignAccHistoryParams.setInstitutionCode("0D");
        l_commCampaignAccHistoryParams.setAccountCode("2450007");
        l_commCampaignAccHistoryParams.setAccountChargeRatio(123.41235D);
        l_commCampaignAccHistoryParams.setLastUpdater("admin");
        l_commCampaignAccHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_commCampaignAccHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        
        return l_commCampaignAccHistoryParams;
    }
    
    /**
     * �萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
     */
    public static CommCampaignProductMstParams getCommCampaignProductMstRow()
    {
        CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
        l_commCampaignProductMstParams.setCampaignId(2008000018L);
        l_commCampaignProductMstParams.setCommProductCode("10");
        l_commCampaignProductMstParams.setLastUpdater("admin");
        l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
       
        return l_commCampaignProductMstParams;
    }
    
    /**
     * �萔�������L�����y�[�������}�X�^(comm_campaign_cond_mst)
     */
    public static CommCampaignCondMstParams getCommCampaignCondMstRow()
    {
        CommCampaignCondMstParams l_commCampaignCondMstParams = new CommCampaignCondMstParams();
        l_commCampaignCondMstParams.setCampaignId(100200018L);
        l_commCampaignCondMstParams.setCommCampaignName("sino");
        l_commCampaignCondMstParams.setInstitutionCode("0D");
        l_commCampaignCondMstParams.setAccountChargeRatio(123.12345D);
        l_commCampaignCondMstParams.setRegistType("0");
        l_commCampaignCondMstParams.setLastUpdater("admin");
        l_commCampaignCondMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_commCampaignCondMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_commCampaignCondMstParams;
    }
    
    /**
     * �U������Z�@@�փe�[�u��(transfered_fin_institution)
     */
    public static TransferedFinInstitutionParams getTransferedFinInstitutionRow()
    {
        TransferedFinInstitutionParams l_transferedFinInstitutionParams = new TransferedFinInstitutionParams();
        l_transferedFinInstitutionParams.setInstitutionCode("0D");
        l_transferedFinInstitutionParams.setBranchCode("381");
        l_transferedFinInstitutionParams.setAccountCode("2512238");
        l_transferedFinInstitutionParams.setTransferDiv(WEB3TransferDivDef.BANK_TRANSFER);
        l_transferedFinInstitutionParams.setFinSaveDiv(WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE);
        l_transferedFinInstitutionParams.setDesignateDiv("1");
        return l_transferedFinInstitutionParams;
    }
    /**
     * ������ؑփe�[�u��
     * (OrderSwitchingRow)
     * @@return OrderSwitchingParams
     */
    public static OrderSwitchingParams getOrderSwitchingRow()
    {
        OrderSwitchingParams l_orderSwitchingParams = new OrderSwitchingParams();
        //1   �،���ЃR�[�h    institution_code    VARCHAR2   3   NotNull
        l_orderSwitchingParams.setInstitutionCode("0D");
        //2   �����^�C�v    product_type    NUMBER   6   NotNull
        l_orderSwitchingParams.setProductType(ProductTypeEnum.IFO);
        //3   �s��R�[�h    market_code    VARCHAR2   2   NotNull
        l_orderSwitchingParams.setMarketCode(WEB3MarketCodeDef.HONGKONG);
        //4   �s��R�[�h�iSONAR�j    sonar_market_code    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setSonarMarketCode("G");
        //5   �����o�H�敪    submit_order_route_div    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION);
        //6   �L���t���O    valid_flag    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setValidFlag(WEB3ValidFlag.OFF);
        //7   ��������\�t���O    change_cancel_enable_flag    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setChangeCancelEnableFlag(WEB3ChangeCancelEnableFlag.DISABLE);
        //8   �쐬���t    created_timestamp    DATE      NotNull
        l_orderSwitchingParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //9   �X�V���t    last_updated_timestamp    DATE      NotNull
        l_orderSwitchingParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        //10  �t�����g�����V�X�e���敪    front_order_system_code    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.OSAKA_EXCHANGE_STOCK);
        //11  MQ�g���K���s    submit_mq_trigger    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setSubmitMqTrigger(WEB3SubmitMqTriggerDef.NOT_TRIGGER);
        //12  �����G���W���敪    order_engine_div    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setOrderEngineDiv(WEB3OrderEngineDivDef.SONAR);
        return l_orderSwitchingParams;
    }
    
   /**
    * �敨OP��������L���[�e�[�u��(host_fotype_order_all)
    */
    public static HostFotypeOrderAllParams getHostFotypeOrderAllRow()
    {
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        l_hostFotypeOrderAllParams.setRequestCode("EI803");
        l_hostFotypeOrderAllParams.setAccountId(333812512203L);
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("9");
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setTraderCode("asfd");
        l_hostFotypeOrderAllParams.setAccountCode("2512246");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("00");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setCorpCode("wesd");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv(WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE);
        l_hostFotypeOrderAllParams.setSonarMarketCode(WEB3MarketCodeSONARDef.TOKYO);
        l_hostFotypeOrderAllParams.setTransactionType(WEB3TransactionTypeSONARDef.MARKET_TRADING);
        l_hostFotypeOrderAllParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_hostFotypeOrderAllParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_hostFotypeOrderAllParams;
    }
    
    /**
     * �敨OP�����e�[�u�� (ifo_product)
     */
    public static IfoProductParams getIfoProductRow()
    {
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setInstitutionCode("10");
        l_ifoProductParams.setProductCode("160030005");
        l_ifoProductParams.setUnderlyingProductCode("0005");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(0);
        l_ifoProductParams.setMonthOfDelivery("200503");
        l_ifoProductParams.setExerciseDate(WEB3DateUtility.getDate("20070112","yyyyMMdd"));
        l_ifoProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_ifoProductParams;
    }
    
    /**
     * ���ʃe�[�u�� (ifo_contract)
     * path:  $/WEBBROKER3/09Requirement�Ǘ�/6.1.��ؗ[��/1.0.�敨�I�v�V����
     *         /DB���C�A�E�g/04.�敨�I�v�V����/02.�c���֘A/���ʃe�[�u���d�l.xls 
     */
    public static IfoContractParams getIfoContractRow()
    {
        IfoContractParams l_ifoContractParams = new IfoContractParams();
        l_ifoContractParams.setContractId(1001);
        l_ifoContractParams.setAccountId(101001010000L);
        l_ifoContractParams.setSubAccountId(10100101000007L);
        l_ifoContractParams.setMarketId(1002);
        l_ifoContractParams.setUnitSize(1000.0);
        l_ifoContractParams.setOriginalQuantity(1.0);
        l_ifoContractParams.setQuantity(1.0);
        l_ifoContractParams.setOriginalContractPrice(3720.0);
        l_ifoContractParams.setContractType(ContractTypeEnum.LONG);
        l_ifoContractParams.setContractPrice(3720.0);
        l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040702","yyyyMMdd"));
        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040812","yyyyMMdd"));
        l_ifoContractParams.setSetupFee(2000.0);
        l_ifoContractParams.setSetupFeeTax(100.0);
        l_ifoContractParams.setManagementFee(.0);
        l_ifoContractParams.setManagementFeeTax(.0);
        l_ifoContractParams.setInterestFee(.0);
        l_ifoContractParams.setInterestFeeTax(.0);
        l_ifoContractParams.setProductId(1006149081018L);
        l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
        l_ifoContractParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_ifoContractParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_ifoContractParams.setDeliveryDate(Calendar.getInstance().getTime());
        l_ifoContractParams.setSessionType(null);
        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        
        return l_ifoContractParams;
    }
    
    /**
     * �敨OP��������}�X�^(ifo_traded_product)
     */
    public static IfoTradedProductParams getIfoTradedProductRow()
    {
        IfoTradedProductParams l_IfoTradedProductParams = new IfoTradedProductParams();
        l_IfoTradedProductParams.setTradedProductId(1006160060005L);
//        l_IfoTradedProductParams.setValidForBizDate("20040707");
        l_IfoTradedProductParams.setInstitutionCode("10");
        l_IfoTradedProductParams.setMarketId(1001L);
        l_IfoTradedProductParams.setProductId(1006139070605L);
        l_IfoTradedProductParams.setUnitSize(10000L);
        l_IfoTradedProductParams.setUnitMargin(0L);
        l_IfoTradedProductParams.setPerOrderMaxUnits(1000L);
        l_IfoTradedProductParams.setOrderCloseTime("");
        l_IfoTradedProductParams.setLastClosingPrice(8D);
        l_IfoTradedProductParams.setStartTradingDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
        l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20040718","yyyyMMdd")); 
        l_IfoTradedProductParams.setTradeStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
        l_IfoTradedProductParams.setListFlag(BooleanEnum.TRUE);
        l_IfoTradedProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_IfoTradedProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        return l_IfoTradedProductParams;
    }
    
    /**
     * �Ǘ��Ҍ���(admin_permission)
     */
    public static AdminPermissionParams getAdminPermissionRow()
    {
        AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
        l_adminPermissionParams.setInstitutionCode("77");
        l_adminPermissionParams.setPermissionLevel("770");
        l_adminPermissionParams.setTransactionCategory("D0102");
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
        l_adminPermissionParams.setLastUpdater("sra501");
        l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
        l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
        return l_adminPermissionParams;
    }
    
    
    /**
     * �敨OP�������Ͻ��i�ꎞð��فj(ifo_traded_product_updq)
     */
    public static IfoTradedProductUpdqParams getIfoTradedProductUpdqRow()
    {
        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = new IfoTradedProductUpdqParams();
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060005L);
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070628");
        l_ifoTradedProductUpdqParams.setInstitutionCode("10");
        l_ifoTradedProductUpdqParams.setMarketId(1001L);
        l_ifoTradedProductUpdqParams.setProductId(1006139070L);
        l_ifoTradedProductUpdqParams.setUnitSize(10000D);
        l_ifoTradedProductUpdqParams.setUnitMargin(0L);
        l_ifoTradedProductUpdqParams.setPerOrderMaxUnits(1000D);
        l_ifoTradedProductUpdqParams.setOrderCloseTime("0");
        l_ifoTradedProductUpdqParams.setLastClosingPrice(28);
        l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20070628","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20070628","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setTradeStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
        l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20070628","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20070628","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setExerciseStop(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setActualDelivaryStop(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setActualRecieveStop(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setReserveStop(BooleanEnum.FALSE);
        l_ifoTradedProductUpdqParams.setIndicationPrice(30.8D);
        l_ifoTradedProductUpdqParams.setLastLiquidationPrice(0D);
        l_ifoTradedProductUpdqParams.setTargetSpotPrice(1212D);
        l_ifoTradedProductUpdqParams.setLiquidationPrice(8.13D);
        l_ifoTradedProductUpdqParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070628","yyyyMMdd"));
        l_ifoTradedProductUpdqParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070628","yyyyMMdd"));
//        l_ifoTradedProductUpdqParams.setStopLowPrice(1.1);
//        l_ifoTradedProductUpdqParams.setStopHighPrice(10.10);
//        l_ifoTradedProductUpdqParams.setPriceRangeType("0");
        
        return l_ifoTradedProductUpdqParams;
    }
    
    /**
     * ��������ꎞ�e�[�u��(traded_product_updq)
     */
    public static TradedProductUpdqParams getTradedProductUpdqRow()
    {
        TradedProductUpdqParams l_tradedProductUpdqParams = new TradedProductUpdqParams();
        l_tradedProductUpdqParams.setTradedProductId(1006160060005L);
        l_tradedProductUpdqParams.setValidUntilBizDate("20040708");
        l_tradedProductUpdqParams.setProductId(1006139070605L);
        l_tradedProductUpdqParams.setMarketId(1001L);
        l_tradedProductUpdqParams.setInstitutionCode("10");
        l_tradedProductUpdqParams.setMarginRatio(0D);
        l_tradedProductUpdqParams.setSuspensionFlag(BooleanEnum.FALSE);
        l_tradedProductUpdqParams.setBaseDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
        l_tradedProductUpdqParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20040709","yyyyMMdd"));
        l_tradedProductUpdqParams.setProductType(ProductTypeEnum.IFO);
        l_tradedProductUpdqParams.setCollateralFlag(BooleanEnum.FALSE);
        l_tradedProductUpdqParams.setLastUpdater("");
        l_tradedProductUpdqParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        l_tradedProductUpdqParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        return l_tradedProductUpdqParams;
    }
    
    /**
     * �敨OP��������ʒm���(host_fotype_order_clmd_notify)
     */
    public static HostFotypeOrderClmdNotifyParams getHostFotypeOrderClmdNotifyRow()
    {
        HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams = new HostFotypeOrderClmdNotifyParams();
        l_hostFotypeOrderClmdNotifyParams.setRequestCode("EI816");
        l_hostFotypeOrderClmdNotifyParams.setInstitutionCode("10");
        l_hostFotypeOrderClmdNotifyParams.setBranchCode("100");
        l_hostFotypeOrderClmdNotifyParams.setAccountCode("1010050");
        l_hostFotypeOrderClmdNotifyParams.setTraderCode("");
        l_hostFotypeOrderClmdNotifyParams.setOrderRequestNumber("000079003");
        l_hostFotypeOrderClmdNotifyParams.setModifiedQuantity(2);
        l_hostFotypeOrderClmdNotifyParams.setModifiedLimitPrice(4200);
        l_hostFotypeOrderClmdNotifyParams.setModifiedExecutionType("1");
        l_hostFotypeOrderClmdNotifyParams.setModifiedResult("02");
        l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType("1");
        l_hostFotypeOrderClmdNotifyParams.setStatus("0");
        l_hostFotypeOrderClmdNotifyParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
        l_hostFotypeOrderClmdNotifyParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
        return l_hostFotypeOrderClmdNotifyParams;
    }

    /**
     * �Ǘ��҃^�C�v�e�[�u��(administrator_type) 
     */
    public static AdministratorTypeParams getAdministratorTypeRow()
    {
        AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
        l_administratorTypeParams.setInstitutionCode("0D");
        l_administratorTypeParams.setPermissionLevel("331");
        l_administratorTypeParams.setLastUpdater("");
        l_administratorTypeParams.setDirAdminFlag(0);
        l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
        l_administratorTypeParams.setPermissionLevelName("�X�V�\�Ǘ���");
        l_administratorTypeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_administratorTypeParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        return l_administratorTypeParams;
    }

    /**
     *  ���X�p�v���t�@@�����X�e�[�u���d�l (branch_preferences)
     */
    public static BranchPreferencesParams getBranchPreferencesRow()
    {
        BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
        l_branchPreferencesParams.setBranchId(87871);
        l_branchPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_branchPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_branchPreferencesParams.setName("triggerorder.wlimitorder.check.order.cond.price");
        l_branchPreferencesParams.setValue("1");
        l_branchPreferencesParams.setNameSerialNo(1002);
        return l_branchPreferencesParams;
    }
    
    /**
     * (traded_product_calendar)
     */
     public static TradedProductCalendarParams getTradedProductCalendarRow()
     {
         TradedProductCalendarParams l_tradedProductCalendarParams = new TradedProductCalendarParams();
         l_tradedProductCalendarParams.setTradedProductId(1006160060005L);
         l_tradedProductCalendarParams.setTradeDate(WEB3DateUtility.getDate("20040709","yyyyMMdd"));
         l_tradedProductCalendarParams.setProductId(1006139070605L);
         l_tradedProductCalendarParams.setMarketId(1002L);
         l_tradedProductCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
         l_tradedProductCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
         l_tradedProductCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
         return l_tradedProductCalendarParams;
     }
     
     /**
      * branch_index_dealt_cond
      */

     public static BranchIndexDealtCondParams getBranchIndexDealtCondRow()
     {
         BranchIndexDealtCondParams l_branchIndexDealtCondParams = new BranchIndexDealtCondParams();
         l_branchIndexDealtCondParams.setTargetProductCode("0005");
         l_branchIndexDealtCondParams.setInstitutionCode("77");
         l_branchIndexDealtCondParams.setBranchCode("381");
         l_branchIndexDealtCondParams.setMarketCode("33");

         l_branchIndexDealtCondParams.setFutureOptionDiv("1");
         l_branchIndexDealtCondParams.setOpenContLimit(1234556L);
         l_branchIndexDealtCondParams.setIfoDepositPerUnit0(14D);
         l_branchIndexDealtCondParams.setIfoDepositPerUnit0Red(11D);
         l_branchIndexDealtCondParams.setIfoDepositPerUnit1(11D);
         l_branchIndexDealtCondParams.setIfoDepositPerUnit1Red(11D);
         l_branchIndexDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
         l_branchIndexDealtCondParams.setLastUpdatedDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
         l_branchIndexDealtCondParams.setEnableOrder("1");
         return l_branchIndexDealtCondParams;
     }
    
     /**
      * bond_order_unit
      */
     public static BondOrderUnitParams getBondOrderUnitRow()
     {
         BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams();
         l_bondOrderUnitParams.setOrderUnitId(123456L);
         l_bondOrderUnitParams.setAccountId(333812512203L);
         l_bondOrderUnitParams.setSubAccountId(33381251220301L);
         l_bondOrderUnitParams.setBranchId(33381);
         l_bondOrderUnitParams.setOrderId(65421L);
         l_bondOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
         l_bondOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
         l_bondOrderUnitParams.setDealType("35");
         l_bondOrderUnitParams.setLastOrderActionSerialNo(123);
         l_bondOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
         l_bondOrderUnitParams.setLastExecutionSerialNo(321);
         l_bondOrderUnitParams.setProductType(ProductTypeEnum.BOND);
         l_bondOrderUnitParams.setMarketId(35);
         l_bondOrderUnitParams.setQuantity(2000L);
         l_bondOrderUnitParams.setAutoExecDiv("1");
         l_bondOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
         l_bondOrderUnitParams.setForeignDeliveryDate(WEB3DateUtility.getDate("2007/02/09", "yyyy/MM/dd"));
         l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
         l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
         l_bondOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("2007/02/09", "yyyy/MM/dd"));
         l_bondOrderUnitParams.setLockStatus("1");
         l_bondOrderUnitParams.setOrderExecStatus("1");
         l_bondOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
         l_bondOrderUnitParams.setBizDate("20070208");
         l_bondOrderUnitParams.setProductId(123456L);
         l_bondOrderUnitParams.setPrice(23.2);
         l_bondOrderUnitParams.setPaymentDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
         l_bondOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_bondOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         return l_bondOrderUnitParams;
     }
        
     /**
      * �����X�ʏ����e�[�u��(bond_branch_condition)
      */
     
     public static BondBranchConditionParams getBondBranchConditionRow()
     {
         BondBranchConditionParams l_bondBranchConditionParams = new BondBranchConditionParams();
         l_bondBranchConditionParams.setBranchId(33381);
         l_bondBranchConditionParams.setEnforceDiv("4");
         l_bondBranchConditionParams.setAssetCheckDiv("2");
         l_bondBranchConditionParams.setOrderLockUseDiv("4");
         l_bondBranchConditionParams.setPaymentDateSetDiv("1");
         l_bondBranchConditionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_bondBranchConditionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         return l_bondBranchConditionParams;
     }
     /**
      * �戵�\���������e�[�u��(enable_order_condition)
      * $/WEBBROKER3/09Requirement�Ǘ�/2.1.����/�c�a���C�A�E�g/00.����
      *  /00.��ЁE���X�E�ڋq�E�s��/�戵�\���������e�[�u���d�l.xls
      */
     public static EnableOrderConditionParams getEnableOrderConditionRow()
     {
         EnableOrderConditionParams l_enableOrderConditionParams = new EnableOrderConditionParams();
         l_enableOrderConditionParams.setInstitutionCode("0D");
         l_enableOrderConditionParams.setProductType(ProductTypeEnum.OTHER);
         l_enableOrderConditionParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
         l_enableOrderConditionParams.setMarginTradingDiv("0");
         l_enableOrderConditionParams.setMarketCode(WEB3MarketCodeDef.HONGKONG);
         l_enableOrderConditionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_enableOrderConditionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         
         return l_enableOrderConditionParams;
     }
     
     /**
      * �������}�X�^�e�[�u��(bond_product)
      */
 
     public static BondProductParams getBondProductRow()
     {
         BondProductParams l_bondProductParams = new BondProductParams();
         l_bondProductParams.setProductId(3304140763000L);
         l_bondProductParams.setInstitutionCode("0D");
         l_bondProductParams.setProductCode("A01010763");
         l_bondProductParams.setProductIssueCode("0");
         l_bondProductParams.setBondType(BondTypeEnum.FOREIGN_BOND);
         l_bondProductParams.setHostProductCode("0763");
         l_bondProductParams.setIssueAmount(100000000000D);
         l_bondProductParams.setParValue(100D);
         l_bondProductParams.setAutoExecDiv("0");
         l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2009/07/01", "yyyy/MM/dd"));
         l_bondProductParams.setRedemptionPrice(110D);
         l_bondProductParams.setCouponType(CouponTypeEnum.COUPON);
         l_bondProductParams.setCoupon(0.08D);
         l_bondProductParams.setYearlyInterestPayments(2);
         l_bondProductParams.setTradeHandleDiv("2");
         l_bondProductParams.setTradeUnit(100D);
         l_bondProductParams.setMinFaceAmount(100D);
         l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         l_bondProductParams.setAutoExecAmount(500000000000D);
         l_bondProductParams.setIssuePrice(98D);
         l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2004/01/01", "yyyy/MM/dd"));
         l_bondProductParams.setHostProductIssueCode("101");
         return l_bondProductParams;
     }
     
     /**
      * (���o���e�[�u��)<BR>
      * host_cash_transfer
      */
     public static HostCashTransferParams getHostCashTransferRow()
     {
         HostCashTransferParams l_hostCashTransferParams = new HostCashTransferParams();
        // 1  �f�[�^�R�[�h  request_code    VARCHAR2    5NotNullGI811�F��  FI811�F�{���ꊇ
         l_hostCashTransferParams.setRequestCode("GI811");
        // 2  �،���ЃR�[�h institution_code    VARCHAR2    3NotNull
         l_hostCashTransferParams.setInstitutionCode("0D");
        // 3  ���X�R�[�h   branch_code VARCHAR2    3NotNull
         l_hostCashTransferParams.setBranchCode("624");
        // 4  �ڋq�R�[�h   account_code    VARCHAR2    7NotNull
         l_hostCashTransferParams.setAccountCode("123456");
        // 5  ���҃R�[�h   trader_code VARCHAR2    5Null
         l_hostCashTransferParams.setTraderCode("1234");
        // 6  ���o���敪   order_div   VARCHAR2    1NotNull1�F�o��  2�F����  
         l_hostCashTransferParams.setOrderDiv("1");
        // 7  ���o�����z   amount  NUMBER  12Null����̏ꍇ�A�}�C�i�X�l   
         l_hostCashTransferParams.setAmount(100);
        // 8  ���o����    cash_trans_date DATENull
         l_hostCashTransferParams.setCashTransDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        // 9  �^�C���X�^���v created_timestamp   VARCHAR2    14NullYYYYMMDDHHMMSS
         l_hostCashTransferParams.setCreatedTimestamp("20040707");
        // 10     �����敪    status  VARCHAR2    1NotNull0�F������  1�F������
         l_hostCashTransferParams.setStatus("1");
         return l_hostCashTransferParams;
     }


     /**
      * ifo_closing_contract_spec
      */

     public static IfoClosingContractSpecParams getIfoClosingContractSpecRow()
     {
         IfoClosingContractSpecParams l_ifoClosingContractSpecParams = new IfoClosingContractSpecParams();

         l_ifoClosingContractSpecParams.setClosingContractSpecId(1001);
         l_ifoClosingContractSpecParams.setAccountId(101001010010L);
         l_ifoClosingContractSpecParams.setSubAccountId(10100101001007L);
         l_ifoClosingContractSpecParams.setOrderId(1001);
         l_ifoClosingContractSpecParams.setOrderUnitId(1001);
         l_ifoClosingContractSpecParams.setContractId(1001);
         l_ifoClosingContractSpecParams.setClosingSerialNo(111);
         l_ifoClosingContractSpecParams.setQuantity(1.0D);
         l_ifoClosingContractSpecParams.setConfirmedQuantity(200);
         l_ifoClosingContractSpecParams.setExecutedQuantity(100);
         l_ifoClosingContractSpecParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
         l_ifoClosingContractSpecParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));

         return l_ifoClosingContractSpecParams;
     }
     
     /**
      * �����P�ʃe�[�u��(aio_order_unit)
      */
     public static AioOrderUnitParams getAioOrderUnitRow()
     {
         AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
         l_aioOrderUnitParams.setOrderUnitId(2000011L);
         l_aioOrderUnitParams.setAccountId(111101111010L);
         l_aioOrderUnitParams.setSubAccountId(11110111101001L);
         l_aioOrderUnitParams.setBranchId(11110);
         l_aioOrderUnitParams.setTraderId(null);
         l_aioOrderUnitParams.setOrderId(2000011L);
         l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_IN);
         l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);
         l_aioOrderUnitParams.setLastOrderActionSerialNo(2);
         l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
         l_aioOrderUnitParams.setQuantity(350000D);
         l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
         l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
         l_aioOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
         l_aioOrderUnitParams.setBizDate("20040716");
         l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_aioOrderUnitParams.setProductId(2605000000001L);
         l_aioOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
         l_aioOrderUnitParams.setMiniStockDiv("0");
         l_aioOrderUnitParams.setEstTransferDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_aioOrderUnitParams.setTransferType(AssetTransferTypeEnum.CASH_IN);
         l_aioOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_aioOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         return l_aioOrderUnitParams;
     }
     /**
      * �O��MMF�בփ��[�g�e�[�u��(frgn_mmf_exchange_rate)
      */
     public static FrgnMmfExchangeRateParams getFrgnMmfExchangeRateRow()
     {
        FrgnMmfExchangeRateParams l_FrgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
        l_FrgnMmfExchangeRateParams.setInstitutionCode("0D");
        l_FrgnMmfExchangeRateParams.setCurrencyCode("01");
        l_FrgnMmfExchangeRateParams.setExecTimestamp(Calendar.getInstance().getTime()); 
        l_FrgnMmfExchangeRateParams.setTtSellingRate(1D);
        l_FrgnMmfExchangeRateParams.setTtBuyingRate(50D);
        l_FrgnMmfExchangeRateParams.setSubCurrencyRatio(50);
        l_FrgnMmfExchangeRateParams.setRestrictRate(10D);
        l_FrgnMmfExchangeRateParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_FrgnMmfExchangeRateParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_FrgnMmfExchangeRateParams;
     }

     /**
      * ���M�����}�X�^�e�[�u��(mutual_fund_product)
      */

     public static MutualFundProductParams getMutualFundProductRow()
     {
         MutualFundProductParams l_MutualFundProductParams = new MutualFundProductParams();
         l_MutualFundProductParams.setProductId(0100000L);
         l_MutualFundProductParams.setInstitutionCode("40");
         l_MutualFundProductParams.setProductCode("0001000");
         l_MutualFundProductParams.setProductIssueCode("0");
         l_MutualFundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
         l_MutualFundProductParams.setInitPurchaseMinQty(0L);
         l_MutualFundProductParams.setAddtlPurchaseMinQty(0L);
         l_MutualFundProductParams.setLastUpdater("1001");
         l_MutualFundProductParams.setIndicationRanking(1);
         l_MutualFundProductParams.setOnlineUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_MutualFundProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_MutualFundProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         return l_MutualFundProductParams;
     }
     
     /**
      * ����������t�L���[�e�[�u��(host_eqtype_order_accept)
      */
                         
     public static HostEqtypeOrderAcceptParams getHostEqtypeOrderAcceptRow()
     {
         HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams = new HostEqtypeOrderAcceptParams();
         l_hostEqtypeOrderAcceptParams.setRequestCode("AI80A");
         l_hostEqtypeOrderAcceptParams.setInstitutionCode("6D");
         l_hostEqtypeOrderAcceptParams.setBranchCode("101");
         l_hostEqtypeOrderAcceptParams.setAccountCode("1000010");
         l_hostEqtypeOrderAcceptParams.setTraderCode(null);
         l_hostEqtypeOrderAcceptParams.setOrderRequestNumber("000022001");
         l_hostEqtypeOrderAcceptParams.setAcceptStatus("1");
//         l_hostEqtypeOrderAcceptParams.setErrorMessage();
         l_hostEqtypeOrderAcceptParams.setSubmitOrderRouteDiv("0");
//         l_hostEqtypeOrderAcceptParams.setVirtualServerNumberMarket();
//         l_hostEqtypeOrderAcceptParams.setNoticeType();
//         l_hostEqtypeOrderAcceptParams.setNoticeNumber();
//         l_hostEqtypeOrderAcceptParams.setAcceptNumber();
         l_hostEqtypeOrderAcceptParams.setStatus("0");
         l_hostEqtypeOrderAcceptParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_hostEqtypeOrderAcceptParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         return l_hostEqtypeOrderAcceptParams;
     }
     
     /**
      *  ���M��������}�X�^�e�[�u��(mutual_fund_traded_product)
      */
     
     public static MutualFundTradedProductParams getMutualFundTradedProductRow()
     {
         MutualFundTradedProductParams l_MutualFundTradedProductParams = new MutualFundTradedProductParams();
         l_MutualFundTradedProductParams.setTradedProductId(1006160060005L);
         l_MutualFundTradedProductParams.setInstitutionCode("40");
         l_MutualFundTradedProductParams.setProductId(4003000100000000L);
         l_MutualFundTradedProductParams.setMarketId(4000L);
         l_MutualFundTradedProductParams.setLastUpdater("manager");
         l_MutualFundTradedProductParams.setOnlineUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_MutualFundTradedProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_MutualFundTradedProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         return l_MutualFundTradedProductParams;
     }
     
     /**
      * �ۗL���Y�e�[�u��(asset)
      */

     public static AssetParams getAssetRow()
     {
         AssetParams l_assetParams = new AssetParams();
         l_assetParams.setAssetId(1001);
         l_assetParams.setAccountId(101001010010L);
         l_assetParams.setSubAccountId(10100101001007L);
         l_assetParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
         l_assetParams.setQuantity(100.0);
         l_assetParams.setQuantityCannotSell(100);
         l_assetParams.setQuantityForBookValue(100);
         l_assetParams.setBookValue(100);
         l_assetParams.setInputBookValue(100);
         l_assetParams.setInputTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_assetParams.setSetupFee(0);
         l_assetParams.setSetupFeeTax(0);
         l_assetParams.setManagementFee(0);
         l_assetParams.setManagementFeeTax(0);
         l_assetParams.setProductId(1006169090018L);
         l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
         l_assetParams.setMiniStockDiv("1");
         l_assetParams.setProfitDistribution(200);
         l_assetParams.setCountBeforePenalty(200);
         l_assetParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_assetParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));

         return l_assetParams;
     }
     
     /**
      * ���M�ۗL���Y�⏕�e�[�u��(mf_sub_asset)
      */

     public static MfSubAssetParams getMfSubAssetRow()
     {
         MfSubAssetParams l_mfSubAssetParams = new MfSubAssetParams();
         l_mfSubAssetParams.setAssetId(1001);
         l_mfSubAssetParams.setAccountId(101001010010L);
         l_mfSubAssetParams.setUnreceiveDist(123.11);
         l_mfSubAssetParams.setProductId(1006169090018L);
         l_mfSubAssetParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_mfSubAssetParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));

         return l_mfSubAssetParams;
     }
     /**
      * ���������e�[�u��(eqtype_order_action)
      */
     public static EqtypeOrderActionParams getEqtypeOrderActionRow()
     {
         EqtypeOrderActionParams l_eqtypeOrderActionParams = new EqtypeOrderActionParams();
         l_eqtypeOrderActionParams.setOrderActionId(3019L);
         l_eqtypeOrderActionParams.setAccountId(101401014050L);
         l_eqtypeOrderActionParams.setSubAccountId(10140101405001L);
         l_eqtypeOrderActionParams.setOrderId(5010);
         l_eqtypeOrderActionParams.setProductId(1006149081018L);
         l_eqtypeOrderActionParams.setOrderUnitId(5010);
         l_eqtypeOrderActionParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
         l_eqtypeOrderActionParams.setOrderEventType(OrderEventTypeEnum.NEW);
         l_eqtypeOrderActionParams.setQuantity(2D);
         l_eqtypeOrderActionParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
         l_eqtypeOrderActionParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
         l_eqtypeOrderActionParams.setOrderActionSerialNo(1);
         l_eqtypeOrderActionParams.setProductType(ProductTypeEnum.IFO);
         l_eqtypeOrderActionParams.setQuantityType(QuantityTypeEnum.QUANTITY);
         l_eqtypeOrderActionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_eqtypeOrderActionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         return l_eqtypeOrderActionParams;
     }

     /**
      * (mobile_office_info_regist)
      */

     public static MobileOfficeInfoRegistParams getMobileOfficeInfoRegistRow()
     {
         MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = new MobileOfficeInfoRegistParams();
         l_mobileOfficeInfoRegistParams.setMobileOfficeInfoRegistId(1001L);
         l_mobileOfficeInfoRegistParams.setInstitutionCode("07");
         l_mobileOfficeInfoRegistParams.setBranchId(26624L);
         l_mobileOfficeInfoRegistParams.setAccountId(103266245384010L);
         l_mobileOfficeInfoRegistParams.setAccountCode("5384010");
         l_mobileOfficeInfoRegistParams.setRegistUpdater("DIR01");             
         l_mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.FALSE);
         l_mobileOfficeInfoRegistParams.setLastUpdater("DIR01");
         l_mobileOfficeInfoRegistParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         return l_mobileOfficeInfoRegistParams;
     }
     /**
      * (exchange_rate)
      */
     public static ExchangeRateParams getExchangeRateRow()
     {
         ExchangeRateParams l_exchangeRateParams = new ExchangeRateParams();
         l_exchangeRateParams.setInstitutionCode("0D");
         l_exchangeRateParams.setCurrencyCode("01");
         l_exchangeRateParams.setExecTimestamp(Calendar.getInstance().getTime());
         l_exchangeRateParams.setTtSellingRate(50D);
         l_exchangeRateParams.setTtBuyingRate(1D);
         l_exchangeRateParams.setExchangeCalcUnit(11D);
         l_exchangeRateParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_exchangeRateParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         return l_exchangeRateParams;
     }
     
     /**
      * ��胁�[�����M�T�[�r�X_������胁�[�����M�e�[�u��(eqtype_order_exec_send_mail)
      * @@return
      */
     public static EqtypeOrderExecSendMailParams getEqtypeOrderExecSendMailRow()
     {
         EqtypeOrderExecSendMailParams l_eqtypeOrderExecSendMailParams =
             new EqtypeOrderExecSendMailParams();
         //�،���ЃR�[�h,  institution_code,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setInstitutionCode("6D");
         //���X�R�[�h,  branch_code,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setBranchCode("381");
         //�����R�[�h,  account_code,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setAccountCode("2512246");
         //���ʃR�[�h,  order_request_number,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setOrderRequestNumber("000022001");
         //�����R�[�h,  product_code,  NULL,
         l_eqtypeOrderExecSendMailParams.setProductCode("1");
         //�s��R�[�h,  market_code,  NULL,
         l_eqtypeOrderExecSendMailParams.setMarketCode("33");
         //���������h�c,  order_action_id,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setOrderActionId(15051L);
         //�ŋ敪,  tax_type,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setTaxType(TaxTypeEnum.NORMAL);
         //���敪,  order_exec_status,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setOrderExecStatus("1");
         l_eqtypeOrderExecSendMailParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         //�X�V����,  last_updated_timestamp,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

         return l_eqtypeOrderExecSendMailParams;
     }
     
     /**
      * (last_closing_price)
      */
     public static LastClosingPriceParams getLastClosingPriceRow()
     {
         LastClosingPriceParams l_lastClosingPriceParams = new LastClosingPriceParams();
         l_lastClosingPriceParams.setProductId(1006169090018L);
         l_lastClosingPriceParams.setBaseDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_lastClosingPriceParams.setTokyoClosingPrice(1000);
         l_lastClosingPriceParams.setOosakaClosingPrice(1100);
         l_lastClosingPriceParams.setNagoyaClosingPrice(1200);
         l_lastClosingPriceParams.setOtherClosingPrice(900);
         l_lastClosingPriceParams.setPrimaryClosingPrice(500);
         l_lastClosingPriceParams.setErrorCode("0");
         l_lastClosingPriceParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_lastClosingPriceParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         return l_lastClosingPriceParams;
     }
     
     /**
      * ���M�����P�ʃe�[�u���d�l(mutual_fund_order_unit)
      */
        
     public static MutualFundOrderUnitParams getMutualFundOrderUnitRow()
     {
         MutualFundOrderUnitParams l_mutualFundOrderUnitParams = new MutualFundOrderUnitParams();
         l_mutualFundOrderUnitParams.setOrderUnitId(2050L);
         l_mutualFundOrderUnitParams.setAccountId(406255702224L);
         l_mutualFundOrderUnitParams.setSubAccountId(40625570222410L);
         l_mutualFundOrderUnitParams.setBranchId(40625L);
         l_mutualFundOrderUnitParams.setOrderId(50L);
         l_mutualFundOrderUnitParams.setOrderType(OrderTypeEnum.MF_BUY);
         l_mutualFundOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
         l_mutualFundOrderUnitParams.setLastOrderActionSerialNo(2);
         l_mutualFundOrderUnitParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
         l_mutualFundOrderUnitParams.setMarketId(4000L);
         l_mutualFundOrderUnitParams.setQuantity(100.0D);
         l_mutualFundOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040723","yyyyMMdd"));
         l_mutualFundOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
         l_mutualFundOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
         l_mutualFundOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
         l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
         l_mutualFundOrderUnitParams.setBizDate("20040720");
         l_mutualFundOrderUnitParams.setProductId(4003000900000000L);
         l_mutualFundOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
         l_mutualFundOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_mutualFundOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         return l_mutualFundOrderUnitParams;
     }
     
     /**
      * ���M�C�O�s��J�����_�[�d�l(mutual_fund_frgncal)
      */

     public static MutualFundFrgncalParams getMutualFundFrgncalRow()
     {
         MutualFundFrgncalParams l_mutualFundFrgncalParams = new MutualFundFrgncalParams();
         l_mutualFundFrgncalParams.setInstitutionCode("58");
         l_mutualFundFrgncalParams.setProductCode("1031000");
         l_mutualFundFrgncalParams.setHoliday(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_mutualFundFrgncalParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         l_mutualFundFrgncalParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
         return l_mutualFundFrgncalParams;
     }
     
     /**
      * ���M�����L���[�e�[�u�� (host_xbmf_order)
      */

     public static HostXbmfOrderParams getHostXbmfOrderRow()
     {
         HostXbmfOrderParams l_hostXbmfOrderParams = new HostXbmfOrderParams();
         l_hostXbmfOrderParams.setRequestCode("CI801");
         l_hostXbmfOrderParams.setInstitutionCode("40");
         l_hostXbmfOrderParams.setBranchCode("624");
         l_hostXbmfOrderParams.setAccountCode("5388155");
         l_hostXbmfOrderParams.setOrderRequestNumber("1980003");
         l_hostXbmfOrderParams.setTraderCode("11127");
         return l_hostXbmfOrderParams;
     }
     
     /**
      * �O��MMF�����L���[�e�[�u��(host_frgn_mmf_order)
      */
      public static HostFrgnMmfOrderParams getHostFrgnMmfOrderRow()
      {
          HostFrgnMmfOrderParams l_hostFrgnMmfOrderParams = new HostFrgnMmfOrderParams();
          l_hostFrgnMmfOrderParams.setRequestCode("CI801");
          l_hostFrgnMmfOrderParams.setInstitutionCode("40");
          l_hostFrgnMmfOrderParams.setBranchCode("624");
          l_hostFrgnMmfOrderParams.setAccountCode("5388155");
          l_hostFrgnMmfOrderParams.setOrderRequestNumber("1980003");
          return l_hostFrgnMmfOrderParams;
      }
      
      /**
       * �V�X�e���v���t�@@�����X(system_preferences)
       */

      public static SystemPreferencesParams getSystemPreferencesRow()
      {
          SystemPreferencesParams l_systemPreferencesParams = new SystemPreferencesParams();
          l_systemPreferencesParams.setName("system.bizdate");
          l_systemPreferencesParams.setValue("20070206");
          l_systemPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070210","yyyyMMdd"));
          l_systemPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070210","yyyyMMdd"));
          return l_systemPreferencesParams;
      }
      
      /**
       * �s��p�v���t�@@�����X(market_preferences)
       */
 
      public static MarketPreferencesParams getMarketPreferencesRow()
      {
          MarketPreferencesParams l_marketPreferencesParams = new MarketPreferencesParams();
          l_marketPreferencesParams.setMarketId(3303L);
          l_marketPreferencesParams.setName("feq.sle.broker");
          l_marketPreferencesParams.setNameSerialNo(1);
          l_marketPreferencesParams.setValue("true");
          l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
          l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
          return l_marketPreferencesParams;
      }
      
      /**
       * �\�����e�e�[�u���iDISPLAY_CONTENTS�j
       */
      public static DisplayContentsParams getDisplayContentsRow()
      {
          DisplayContentsParams l_displayContentsParams = new DisplayContentsParams();
          l_displayContentsParams.setDisplayContentsId(10001000L);
          l_displayContentsParams.setInstitutionCode("0D");
          l_displayContentsParams.setBranchCode("381");
          l_displayContentsParams.setConditionNo("1001");
          l_displayContentsParams.setDisplayTitle("100100100");
          l_displayContentsParams.setDisplayMessage("200020002000");
          l_displayContentsParams.setLastUpdateTimeDisplayFlag("0");
          l_displayContentsParams.setEffectiveFlag("0");
          l_displayContentsParams.setDisplayDevice("0");
          l_displayContentsParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
          l_displayContentsParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
          return l_displayContentsParams;
      }
      
      /**
       * (enable_order_condition)
       */
      public static EnableOrderConditionParams getEnableOrderConditionParamsRow()
      {
          EnableOrderConditionParams l_enableOrderConditionParams = new EnableOrderConditionParams();
          l_enableOrderConditionParams.setInstitutionCode("0D");
          l_enableOrderConditionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
          l_enableOrderConditionParams.setFutureOptionDiv("0");
          l_enableOrderConditionParams.setMarginTradingDiv("0");
          l_enableOrderConditionParams.setMarketCode("0");
          l_enableOrderConditionParams.setAtMarketOpen("1");
          l_enableOrderConditionParams.setStopOrder("1");
          l_enableOrderConditionParams.setCarriedOrder("1");
          l_enableOrderConditionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
          l_enableOrderConditionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
          return l_enableOrderConditionParams;
      }
      
      /**
       * �����\�񒍕��P�ʃe�[�u��(rsv_eq_order_unit)
       */
      public static RsvEqOrderUnitParams getRsvEqOrderUnitRow()
      {
          RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams();

          l_rsvEqOrderUnitParams.setOrderUnitId(1001);
          l_rsvEqOrderUnitParams.setAccountId(101001010010L);
          l_rsvEqOrderUnitParams.setSubAccountId(10100101001007L);
          l_rsvEqOrderUnitParams.setBranchId(33381);
          l_rsvEqOrderUnitParams.setTraderId(3338111123L);
          l_rsvEqOrderUnitParams.setOrderId(1001);
          l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
          l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
          l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(0);
          l_rsvEqOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
          l_rsvEqOrderUnitParams.setQuantity(1200);
          l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
          l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
          l_rsvEqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
          l_rsvEqOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
          l_rsvEqOrderUnitParams.setSwapTaxType(TaxTypeEnum.NORMAL);
          l_rsvEqOrderUnitParams.setBizDate("20070117");
          l_rsvEqOrderUnitParams.setProductId(1006169090018L);
          l_rsvEqOrderUnitParams.setParentOrderId(1002);
          l_rsvEqOrderUnitParams.setSerialNoInParent(1);
          l_rsvEqOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
          l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));

          return l_rsvEqOrderUnitParams;
      }
      
      /**
       * �O�������P�ʃe�[�u��(feq_order_unit)<BR>
       */
      public static FeqOrderUnitParams getFeqOrderUnitRow()
      {
          FeqOrderUnitParams l_feqOrderUnitParams = new FeqOrderUnitParams();
          l_feqOrderUnitParams.setOrderUnitId(1234L);
          l_feqOrderUnitParams.setInstitutionCode("0D");
          l_feqOrderUnitParams.setAccountId(333812512246L);
          l_feqOrderUnitParams.setSubAccountId(33381251220301L);
          l_feqOrderUnitParams.setBranchId(40625L);
          l_feqOrderUnitParams.setTraderId(3338111123L);
          l_feqOrderUnitParams.setOrderId(123456789L);
          l_feqOrderUnitParams.setOrderType(OrderTypeEnum.FEQ_BUY);
          l_feqOrderUnitParams.setOrderCateg(OrderCategEnum.FEQ_TRANSFER);
          l_feqOrderUnitParams.setLastOrderActionSerialNo(0);
          l_feqOrderUnitParams.setLastExecutionSerialNo(0);
          l_feqOrderUnitParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
          l_feqOrderUnitParams.setMarketId(3303L);
          l_feqOrderUnitParams.setQuantity(100D);
          l_feqOrderUnitParams.setLimitPrice(200D);
          l_feqOrderUnitParams.setExecutionConditionType(FeqExecutionConditionType.UNDEFINED);
          l_feqOrderUnitParams.setOrderConditionType("0");
          l_feqOrderUnitParams.setOrderCondOperator("0");
          l_feqOrderUnitParams.setStopOrderPrice(0D);
          l_feqOrderUnitParams.setWLimitPrice(0D);
          l_feqOrderUnitParams.setSettleDiv("0");
          l_feqOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
          l_feqOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
          l_feqOrderUnitParams.setConfirmedQuantity(0D);
          l_feqOrderUnitParams.setConfirmedPrice(0D);
          l_feqOrderUnitParams.setExecutedQuantity(0D);
          l_feqOrderUnitParams.setExecutedAmount(0D);
          l_feqOrderUnitParams.setExecutedAmountYen(0D);
          l_feqOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
          l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
          l_feqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
          l_feqOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
          l_feqOrderUnitParams.setBizDate("20070228");
          l_feqOrderUnitParams.setProductId(400300090000000000L);
          l_feqOrderUnitParams.setCurrencyCode("01");
          l_feqOrderUnitParams.setOrderChanel("0");
          l_feqOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
          l_feqOrderUnitParams.setVoucherNo("0000");
          l_feqOrderUnitParams.setCommTblNo("01");
          l_feqOrderUnitParams.setCommTblSubNo("1");
          l_feqOrderUnitParams.setSonarTradedCode("0");
          l_feqOrderUnitParams.setPrice(100D);
          l_feqOrderUnitParams.setOrderRequestNumber("1");
          l_feqOrderUnitParams.setEstimatedPrice(100D);
          l_feqOrderUnitParams.setFEstimatedPrice(100D);
          l_feqOrderUnitParams.setSonarTradedCode("11");
          l_feqOrderUnitParams.setSonarMarketCode("1");
          l_feqOrderUnitParams.setCommProductCode("40");
          l_feqOrderUnitParams.setCapitalGain(0D);
          l_feqOrderUnitParams.setCapitalGainTax(0D);
          l_feqOrderUnitParams.setModifyCancelType("0");
          l_feqOrderUnitParams.setOrderRootDiv("5");
          l_feqOrderUnitParams.setConfirmedOrderPrice(100D);
          l_feqOrderUnitParams.setConfirmedEstimatedPrice(200D);
          l_feqOrderUnitParams.setConfirmedFEstimatedPrice(300D);
          l_feqOrderUnitParams.setConfirmedExecConditionType(FeqExecutionConditionType.LIMIT_PRICE);
          l_feqOrderUnitParams.setErrorReasonCode("0");
          l_feqOrderUnitParams.setFactor("1");
          l_feqOrderUnitParams.setOrderEmpCode("NWxxxxx");
          l_feqOrderUnitParams.setAccountDiv("2");
          l_feqOrderUnitParams.setExecEndTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
          l_feqOrderUnitParams.setExecFileSendFlag(null);
          l_feqOrderUnitParams.setFirstOrderUnitId(1233L);
          l_feqOrderUnitParams.setLastUpdater("admin");
          l_feqOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
          l_feqOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
 
          return l_feqOrderUnitParams;
          
      }
      
      /**
       * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u��(VirtualServerChangeRow)
       */
      public  static VirtualServerChangeParams getVirtualServerChangeRow()
      {
          VirtualServerChangeParams l_virtualServerChangeParams = new VirtualServerChangeParams();
          l_virtualServerChangeParams.setVirtualServerNumberMarket("33381");
          l_virtualServerChangeParams.setChangeReqResDiv("01");
          l_virtualServerChangeParams.setNoticeType("01");
          l_virtualServerChangeParams.setFrontOrderExchangeCode("1");
          l_virtualServerChangeParams.setProductType(ProductTypeEnum.EQUITY);
          l_virtualServerChangeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
          l_virtualServerChangeParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
          return l_virtualServerChangeParams;
      }

      /**
       * ������������L���[�e�[�u��(host_eqtype_order_all)
       */

      public static HostEqtypeOrderAllParams getHostEqtypeOrderAllRow()
      {
          HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
          l_hostEqtypeOrderAllParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER);
          l_hostEqtypeOrderAllParams.setAccountId(333812512203L);
          l_hostEqtypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);
          l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("9");
          l_hostEqtypeOrderAllParams.setCorpCode("wesd");
          l_hostEqtypeOrderAllParams.setStatus(WEB3FrontOrderStatusDef.NOT_DEAL);
          l_hostEqtypeOrderAllParams.setAllOrderChangeDiv(WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE);
          l_hostEqtypeOrderAllParams.setOrderRequestNumber("000079003");
          return l_hostEqtypeOrderAllParams;
      }
      
      /**
       * �敨OP�w���}�X�^�e�[�u��(ifo_index_master)
       * path: $/WEBBROKER3/09Requirement�Ǘ�/2.4.�敨�I�v�V����/�c�a���C�A�E�g
       *        /04.�敨�I�v�V����/00.�����֘A/�敨OP�w���}�X�^�e�[�u���d�l.xls
       */
      public static IfoIndexMasterParams getIfoIndexMasterRow()
      {
          IfoIndexMasterParams l_ifoIndexMasterParams = new IfoIndexMasterParams();
          l_ifoIndexMasterParams.setIndexId(123L);
          l_ifoIndexMasterParams.setUnderlyingProductCode("0005");
          l_ifoIndexMasterParams.setFutureOptionDiv("1");
          l_ifoIndexMasterParams.setStandardName("���o300");
          
          return l_ifoIndexMasterParams;
      }
      
      /**
       * �O�ݐU������Z�@@�փe�[�u���if_trans_fin_institution�j
       */
      public static FTransFinInstitutionParams getFTransFinInstitutionRow()
      {
          FTransFinInstitutionParams l_fTransFinInstitutionParams = new FTransFinInstitutionParams();
          l_fTransFinInstitutionParams.setInstitutionCode("0D");
          l_fTransFinInstitutionParams.setBranchCode("381");
          l_fTransFinInstitutionParams.setAccountCode("2512246");
          l_fTransFinInstitutionParams.setCurrencyCode("0D");
          l_fTransFinInstitutionParams.setFinDelDiv("0");
          l_fTransFinInstitutionParams.setFinInstitutionCode("0D");
          l_fTransFinInstitutionParams.setFinInstitutionName("0D");
          l_fTransFinInstitutionParams.setFinBranchCode("0D");
          l_fTransFinInstitutionParams.setFinBranchName("0D");
          l_fTransFinInstitutionParams.setFinAccountNo("0D");
          l_fTransFinInstitutionParams.setFinAccountName("0D");
          l_fTransFinInstitutionParams.setFinSaveDiv("0");
          l_fTransFinInstitutionParams.setFinCommDiv("0");
          l_fTransFinInstitutionParams.setForeignFinAccountName("0D");
          l_fTransFinInstitutionParams.setLastUpdater("sonar");
          l_fTransFinInstitutionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
          l_fTransFinInstitutionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
          
          return l_fTransFinInstitutionParams;
      }

      /**
       * ��s�����ʒm�e�[�u��
       * @@return
       */
      public static BankDepositNotifyParams getBankDepositNotifyRow()
      {
          BankDepositNotifyParams lparams = new BankDepositNotifyParams();
        //  ��s�����ʒm�e�[�u��ID      bankdepositnotifyid   NUMBER     18   NotNull    
          lparams.setBankDepositNotifyId(123);
        //  �،���ЃR�[�h       institutioncode   VARCHAR2     3   NotNull
          lparams.setInstitutionCode("123");
        //  ��s�R�[�h     bankcode   VARCHAR2    4   NotNull
          lparams.setBankCode("12");
          lparams.setBranchCode("123");
        //  ��s�x�X�R�[�h       bankbranchcode   VARCHAR2     3   NotNull
          lparams.setBankBranchCode("123");
        //  �����ԍ�      bankaccountno   VARCHAR2      10   NotNull
          lparams.setBankAccountNo("123");
        //  �Ɖ�ԍ�      depositdatareferenceno   VARCHAR2    8   NotNull
          lparams.setDepositDataReferenceNo("123");
        //  �����       depositdataaccountdate   VARCHAR2    6   NotNull
          lparams.setDepositDataAccountDate("123");
        //  ��s���J�i     banknamekana   VARCHAR2       30      
        //  �x�X���J�i     bankbranchnamekana   VARCHAR2    30      
        //  �a�����      depositdatabankaccounttype   VARCHAR2       1       
        //  �����N�Z��     depositdatabasedate   VARCHAR2       6       
        //  ������z      depositdatadepositamount   VARCHAR2      12      
        //  �U���˗��l�R�[�h      depositdatatranspersoncode   VARCHAR2       10      
        //  �U���˗��l��    depositdatatranspersonname   VARCHAR2       96      
        //  �����敪      cashtransferdiv   VARCHAR2    1   NotNull
          lparams.setCashTransferDiv("1");
        //  ����敪      tradediv   VARCHAR2    2       
        //  �d����s��     deliveredbankname   VARCHAR2      30      
        //  �d���X��      deliveredbankbranchname   VARCHAR2       30      
        //  �E�v���e      summary   VARCHAR2      40      
        //  EDI���     ediinfo   VARCHAR2     40      
        //  �����敪      status   VARCHAR2       1       
        //  �����G���[�R�����g     depositerrorcomment   VARCHAR2    100     
        //  ���X�R�[�h     branchcode   VARCHAR2      20      
        //  �ڋq�R�[�h     accountcode   VARCHAR2     7       
        //  ���l    remark   VARCHAR2       100     
        //  �G���[�����ŏI�ʔ�     lasterrorhistoryserialno   NUMBER       8   NotNull
          lparams.setLastErrorHistorySerialNo(12345678);
          lparams.setDataLoadDiv("1");
          return lparams; 
      }

      /**
       * �ʉ݃R�[�h
       * @@return
       */
      public static GenCurrencyParams getGenCurrencyRow()
      {
          GenCurrencyParams l_params = new GenCurrencyParams();
          //�،���ЃR�[�h   institution_code   VARCHAR2   3   Notnull        
          l_params.setInstitutionCode("123");
          //  �ʉ݃R�[�h   currency_code   VARCHAR2   3   Notnull   ��ʉ݃R�[�h�sheet�Q��       
          l_params.setCurrencyCode("132");
          //  �ʉݖ�   currency_name   VARCHAR2   20   NULL        
          //  �ʉݗ���   currency_short_name   VARCHAR2   3   NULL          
          //  ���񔄕t�בփ��[�g   current_sell_rate   NUMBER   7    3   4   NULL   �i�T�Z����v�Z�Ɏg�p�j         
          //  �O�񔄕t�בփ��[�g   prev_sell_rate   NUMBER   7   3   4   NULL   �i�T�Z����v�Z�Ɏg�p�j         
          //  ���񔃕t�בփ��[�g   current_buy_rate   NUMBER   7 3   4   NULL   �i�T�Z����v�Z�Ɏg�p�j         
          //  �O�񔃕t�בփ��[�g   prev_buy_rate   NUMBER   7    3   4   NULL   �i�T�Z����v�Z�Ɏg�p�j         
          //  ���񔄕t���בփ��[�g   current_sell_exec_rate   NUMBER   7 3   4   NULL   �i�T�Z����v�Z�Ɏg�p�j         
          //  �O�񔄕t���בփ��[�g   prev_sell_exec_rate   NUMBER   7    3   4   NULL   �i�T�Z����v�Z�Ɏg�p�j         
          //  ���񔃕t���בփ��[�g   current_buy_exec_rate   NUMBER   7  3   4   NULL   �i�T�Z����v�Z�Ɏg�p�j         
          //  �O�񔃕t���בփ��[�g   prev_buy_exec_rate   NUMBER   7 3   4   NULL   �i�T�Z����v�Z�Ɏg�p�j         
          //  ����������   scale   NUMBER   6   Notnull   "���Y�ʉ݂������ꍇ�̏����_�ȉ������B
          l_params.setScale(1234);
          //��100.51�̂悤�ɕ\������ꍇ�A2��o�^�B"         
          //  �~�݊��Z�ۂߕ���   change_jpy_round_div   VARCHAR2   1   Notnull   "0�F�l�̌ܓ��@@1�F�؎́@@2�F�؏�
          l_params.setChangeJpyRoundDiv("1");
          // ��Default�@@1�F�؎�"         
          //  �O�݊��Z�ۂߕ���   change_f_ccy_round_div   VARCHAR2   1   Notnull   "0�F�l�̌ܓ��@@1�F�؎́@@2�F�؏�
          l_params.setChangeFCcyRoundDiv("1");
          //    ��Default�@@0�F�l�̌ܓ�"         
          //  ��ב֍X�V�҃R�[�h   rate_last_updater   VARCHAR2   20   NULL         
          //  ���ב֍X�V�҃R�[�h   exec_rate_last_updater   VARCHAR2   20   NULL        
          //  ��ב֍X�V���t   rate_update_timestamp   DATE      Notnull   DEFAULT sysdate  
          l_params.setRateUpdateTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
          //  ���ב֍X�V���t   exec_rate_update_timestamp   DATE      Notnull   DEFAULT sysdate  
          l_params.setExecRateUpdateTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
          //  �X�V�҃R�[�h   last_updater   VARCHAR2   20   NULL          
          //  �쐬���t   created_timestamp   DATE      Notnull   DEFAULT sysdate    
          l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
          //  �X�V���t   last_updated_timestamp   DATE      Notnull   DEFAULT sysdate 
          l_params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
          return l_params;
      }
      
      /**
       *  amount_range
       */
      public static AmountRangeParams getAmountRangeRow()
      {
          AmountRangeParams l_amoutRangeParams = new AmountRangeParams();
          l_amoutRangeParams.setFundType("5");
          l_amoutRangeParams.setInstitutionCode("0D");
          l_amoutRangeParams.setTransactionType("5");
          l_amoutRangeParams.setMaxAmount(1235678);
          l_amoutRangeParams.setMinAmount(21);
          l_amoutRangeParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
          l_amoutRangeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
          return l_amoutRangeParams;
      }
      
      /**
       * �]�͌v�Z����Row(tradingpower_calc_condition)
       */
      public static TradingpowerCalcConditionParams getTradingpowerCalcConditionRow()
      {
          TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
              new TradingpowerCalcConditionParams();
          
          l_tradingpowerCalcConditionParam.setAccountId(55464654654L);
          l_tradingpowerCalcConditionParam.setAssetEvaluationDiv("1");
          l_tradingpowerCalcConditionParam.setBranchId(1235562L);
          l_tradingpowerCalcConditionParam.setCalcConditionId(4521365L);
          l_tradingpowerCalcConditionParam.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
          l_tradingpowerCalcConditionParam.setIfoOpenPositionStop("1");
          l_tradingpowerCalcConditionParam.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
          l_tradingpowerCalcConditionParam.setMarginOpenPositionStop("1");
          l_tradingpowerCalcConditionParam.setOtherTradingStop("1");
          l_tradingpowerCalcConditionParam.setPaymentStop("1");
          l_tradingpowerCalcConditionParam.setSpecialDebitAmount(20);
          l_tradingpowerCalcConditionParam.setTradingStop("1");
          l_tradingpowerCalcConditionParam.setAdditionalDepositStop("1");
          
          return l_tradingpowerCalcConditionParam;
      }
 
      /**
       * �ʉ݃e�[�u���e�[�u��
       */
//      public static CurrencyParams getCurrencyRow()
//      {
//          CurrencyParams l_currencyParams = new CurrencyParams();
//          
//          l_currencyParams.setInstitutionCode("0D");
//          l_currencyParams.setCurrencyCode("01");
//          l_currencyParams.setCurrencyName("abc");
//          l_currencyParams.setCurrencyShortName("a");
//          l_currencyParams.setCurrentSellRate(1D);
//          l_currencyParams.setPrevSellRate(2D);
//          l_currencyParams.setCurrentBuyRate(3D);
//          l_currencyParams.setPrevBuyRate(4D);
//          l_currencyParams.setCurrentSellExecRate(5D);
//          l_currencyParams.setPrevSellExecRate(6D);
//          l_currencyParams.setCurrentBuyExecRate(7D);
//          l_currencyParams.setPrevBuyExecRate(8D);
//          l_currencyParams.setScale(3);
//          l_currencyParams.setChangeJpyRoundDiv("0");
//          l_currencyParams.setChangeFCcyRoundDiv("0");
//          l_currencyParams.setRateLastUpdater("01");
//          l_currencyParams.setExecRateLastUpdater("02");
//          l_currencyParams.setRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
//          l_currencyParams.setExecRateUpdateTimestamp(GtlUtils.getSystemTimestamp());
//          l_currencyParams.setLastUpdater("03");
//          l_currencyParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//          l_currencyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//          
//          return l_currencyParams;
//      }

      /**
       * �T�[�r�X�\���o�^�e�[�u��
       */
      public static SrvRegiApplicationParams getSrvRegiApplicationParams()
      {
          SrvRegiApplicationParams l_srvRegiApplicationParams = new SrvRegiApplicationParams();
          
          l_srvRegiApplicationParams.setInstitutionCode("0D");
          l_srvRegiApplicationParams.setBranchCode("381");
          l_srvRegiApplicationParams.setSrvDiv("1");
          l_srvRegiApplicationParams.setAccountCode("123");
          l_srvRegiApplicationParams.setRegistId(1000L);
          l_srvRegiApplicationParams.setAppliStartDate(GtlUtils.getSystemTimestamp());
          l_srvRegiApplicationParams.setAppliEndDate(GtlUtils.getSystemTimestamp());
          l_srvRegiApplicationParams.setOrderId(1111L);
          l_srvRegiApplicationParams.setAppliDate(GtlUtils.getSystemTimestamp());
          l_srvRegiApplicationParams.setPaymentDiv("0");
          l_srvRegiApplicationParams.setAppliLotDiv("0");
          l_srvRegiApplicationParams.setEffectiveDiv("0");
          l_srvRegiApplicationParams.setCancelDiv("0");
          l_srvRegiApplicationParams.setUseAmt(100);
          l_srvRegiApplicationParams.setPaymentDate(GtlUtils.getSystemTimestamp());
          l_srvRegiApplicationParams.setCancelLimitDate(GtlUtils.getSystemTimestamp());
          l_srvRegiApplicationParams.setLastUpdater("web3");
          l_srvRegiApplicationParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
          l_srvRegiApplicationParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
          
          return l_srvRegiApplicationParams;
      }    

      /**
       * ���M��2�����}�X�^�iSONAR�j�e�[�u��
       */
      public static MutualFund2ndProductSonarParams getMutualFund2ndProductSonarParams()
      {
          MutualFund2ndProductSonarParams l_mutualFund2ndProductSonarParams = new MutualFund2ndProductSonarParams();
          
          l_mutualFund2ndProductSonarParams.setInstitutionCode("0D");
          l_mutualFund2ndProductSonarParams.setProductCode("0001000");
          
          return l_mutualFund2ndProductSonarParams;
      }
      
      /**
       * ��p�U�֋����L���[�e�[�u��
       */
      public static HostMrgsecTransNotifyParams getHostMrgsecTransNotifyRow()
      {
          HostMrgsecTransNotifyParams l_hostmrgsecTransNotifyParams =
              new HostMrgsecTransNotifyParams();
          l_hostmrgsecTransNotifyParams.setRequestCode("GI813");
          l_hostmrgsecTransNotifyParams.setInstitutionCode("07");
          l_hostmrgsecTransNotifyParams.setBranchCode("6");
          l_hostmrgsecTransNotifyParams.setAccountCode("6");
          l_hostmrgsecTransNotifyParams.setTraderCode("00001");
          l_hostmrgsecTransNotifyParams.setTransferFlag("01");
          l_hostmrgsecTransNotifyParams.setOriginalTransferDate("");
          l_hostmrgsecTransNotifyParams.setCancelFlag("-");
          l_hostmrgsecTransNotifyParams.setCommodityDiv("1");
          l_hostmrgsecTransNotifyParams.setProductCode("1");
          l_hostmrgsecTransNotifyParams.setQuantity(3000);
          l_hostmrgsecTransNotifyParams.setPrice(3900);
          l_hostmrgsecTransNotifyParams.setDelivType("1");
          l_hostmrgsecTransNotifyParams.setTransferTime(WEB3DateUtility.getDate("101010","HHMMSS"));
          l_hostmrgsecTransNotifyParams.setOrderRequestNumber("1");
          l_hostmrgsecTransNotifyParams.setStatus("0");
          l_hostmrgsecTransNotifyParams.setSpecificAccountDiv("0");
          return l_hostmrgsecTransNotifyParams;
      }

      /**
       * �������n���͒ʒm�L���[�e�[�u�� (host_eqtype_swap_receipt)
       */
      public static HostEqtypeSwapReceiptParams getHostEqtypeSwapReceiptRow()
      {
          HostEqtypeSwapReceiptParams l_hostEqtypeSwapReceiptParams = new HostEqtypeSwapReceiptParams();
          l_hostEqtypeSwapReceiptParams.setRequestCode("AI826");
          l_hostEqtypeSwapReceiptParams.setInstitutionCode("0D");
          l_hostEqtypeSwapReceiptParams.setBranchCode("381");
          l_hostEqtypeSwapReceiptParams.setAccountCode("2512246");
          l_hostEqtypeSwapReceiptParams.setTraderCode("1234");
          l_hostEqtypeSwapReceiptParams.setProductCode("0001000");
          l_hostEqtypeSwapReceiptParams.setOrderRequestNumber("1");
          l_hostEqtypeSwapReceiptParams.setTradeType(WEB3SwapTradeTypeDef.DELIVERY);
          l_hostEqtypeSwapReceiptParams.setSonarRepaymentType("1");
          l_hostEqtypeSwapReceiptParams.setSonarMarketCode(WEB3MarketCodeSONARDef.TOKYO);
          l_hostEqtypeSwapReceiptParams.setQuantity(100);
          l_hostEqtypeSwapReceiptParams.setCapitalGainTaxType(WEB3CapitalGainTaxTypeDef.NOTHING);
          l_hostEqtypeSwapReceiptParams.setSwapTaxType(WEB3TaxTypeDef.NORMAL);
          l_hostEqtypeSwapReceiptParams.setTaxType(WEB3TaxTypeDef.NORMAL);
          l_hostEqtypeSwapReceiptParams.setBizDatetime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
          l_hostEqtypeSwapReceiptParams.setDeliveryDate("20070412");
          l_hostEqtypeSwapReceiptParams.setCancelDiv("0");
          l_hostEqtypeSwapReceiptParams.setStatus("0");
          l_hostEqtypeSwapReceiptParams.setCreatedTimestamp(Calendar.getInstance().getTime());
          l_hostEqtypeSwapReceiptParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
          return l_hostEqtypeSwapReceiptParams;
      }

      /**
       * �����Ώۃf�[�^�R�[�h�e�[�u��(request_codes_for_read)
       */
      public static RequestCodesForReadParams getRequestCodesForReadRow()
      {
          RequestCodesForReadParams l_requestCodesForReadParams = new RequestCodesForReadParams();
          l_requestCodesForReadParams.setRequestCode("AI826");
          l_requestCodesForReadParams.setPtype("margin_swapOrderNotify");
          l_requestCodesForReadParams.setSubmitOrderRouteDiv("0");
          l_requestCodesForReadParams.setCreatedTimestamp(Calendar.getInstance().getTime());
          l_requestCodesForReadParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
          return l_requestCodesForReadParams;
      }

      /**
       * �����������͒ʒm�L���[�e�[�u�� (host_eqtype_order_receipt)
       */
      public static HostEqtypeOrderReceiptParams getHostEqtypeOrderReceiptRow()
      {
          HostEqtypeOrderReceiptParams l_hostEqtypeOrderReceiptParams = new HostEqtypeOrderReceiptParams();
          l_hostEqtypeOrderReceiptParams.setRequestCode("AI821");
          l_hostEqtypeOrderReceiptParams.setInstitutionCode("0D");
          l_hostEqtypeOrderReceiptParams.setBranchCode("381");
          l_hostEqtypeOrderReceiptParams.setAccountCode("2512246");
          l_hostEqtypeOrderReceiptParams.setTraderCode("1234");
          l_hostEqtypeOrderReceiptParams.setProductCode("0001000");
          l_hostEqtypeOrderReceiptParams.setOrderRequestNumber("1");
          l_hostEqtypeOrderReceiptParams.setTradeType(WEB3SwapTradeTypeDef.DELIVERY);
          l_hostEqtypeOrderReceiptParams.setSonarRepaymentType("1");
          l_hostEqtypeOrderReceiptParams.setSonarMarketCode(WEB3MarketCodeSONARDef.TOKYO);
          l_hostEqtypeOrderReceiptParams.setQuantity(100);
          l_hostEqtypeOrderReceiptParams.setCapitalGainTaxType(WEB3CapitalGainTaxTypeDef.NOTHING);
          l_hostEqtypeOrderReceiptParams.setBizDatetime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
          l_hostEqtypeOrderReceiptParams.setDeliveryDate("20070412");
          l_hostEqtypeOrderReceiptParams.setStatus("0");
          l_hostEqtypeOrderReceiptParams.setCreatedTimestamp(Calendar.getInstance().getTime());
          l_hostEqtypeOrderReceiptParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
          return l_hostEqtypeOrderReceiptParams;
      }

      /**
       * ����������������ʒm�L���[�e�[�u��(host_eqtype_order_clmd_receipt)
       */
      public static HostEqtypeOrderClmdReceiptParams getHostEqtypeOrderClmdReceiptRow()
      {
          HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams = new HostEqtypeOrderClmdReceiptParams();
          l_hostEqtypeOrderClmdReceiptParams.setRequestCode("AI817");
          l_hostEqtypeOrderClmdReceiptParams.setInstitutionCode("0D");
          l_hostEqtypeOrderClmdReceiptParams.setBranchCode("381");
          l_hostEqtypeOrderClmdReceiptParams.setAccountCode("2512246");
          l_hostEqtypeOrderClmdReceiptParams.setTraderCode("1234");
          l_hostEqtypeOrderClmdReceiptParams.setOrderRequestNumber("0");
          l_hostEqtypeOrderClmdReceiptParams.setModifiedQuantity(1000);
          l_hostEqtypeOrderClmdReceiptParams.setModifiedLimitPrice(100);
          l_hostEqtypeOrderClmdReceiptParams.setModifiedPriceConditionType("A");
          l_hostEqtypeOrderClmdReceiptParams.setModifiedResult("02");
          l_hostEqtypeOrderClmdReceiptParams.setCanmodReceiptType("1");
          l_hostEqtypeOrderClmdReceiptParams.setModifiedExecutionType("1");
          l_hostEqtypeOrderClmdReceiptParams.setStatus("0");
          l_hostEqtypeOrderClmdReceiptParams.setCreatedTimestamp(Calendar.getInstance().getTime());
          l_hostEqtypeOrderClmdReceiptParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
          l_hostEqtypeOrderClmdReceiptParams.setModifiedOrderRev("00");
          l_hostEqtypeOrderClmdReceiptParams.setVirtualServerNumberMarket("1");
          l_hostEqtypeOrderClmdReceiptParams.setNoticeType("02");
          l_hostEqtypeOrderClmdReceiptParams.setNoticeNumber(100);
          l_hostEqtypeOrderClmdReceiptParams.setModSubmitOrderRouteDiv("0");
          
          return l_hostEqtypeOrderClmdReceiptParams;
      }

      /**
       * �f�[�����g���K�[�e�[�u��(daemon_trigger)
       */
     public static DaemonTriggerParams getDaemonTriggerRow()
     {
         DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
         l_daemonTriggerParams.setTriggerType("1");
         l_daemonTriggerParams.setThreadNo(1);
         l_daemonTriggerParams.setOrderRequestNumber("11");
         l_daemonTriggerParams.setRangeFrom(0);
         l_daemonTriggerParams.setRangeTo(999999999);
         l_daemonTriggerParams.setTriggerStatus("0");
         l_daemonTriggerParams.setTriggerDate(Calendar.getInstance().getTime());
         return l_daemonTriggerParams;
     }

     /**
      * �Ēl�e�[�u��
      * @@return
      */
     public static EquityLimitPriceRangeMstParams getEquityLimitPriceRangeMstRow()
     {
         //  �s��R�[�h  market_code  VARCHAR2 2            NOT NULL        
         //  �����l  low_price  DECIMAL 18    12  6   NOT NULL        
         //  ����l  cap_price  DECIMAL 18    12  6   NULL        
         //  �l��  range  DECIMAL 18 12  6   NULL        
         //  �쐬���t  created_timestamp  DATE             NOT NULL        
         //  �X�V���t  last_updated_timestamp  DATE            NOT NULL
         EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
             new EquityLimitPriceRangeMstParams();
         l_equityLimitPriceRangeMstParams.setMarketCode("1");
         l_equityLimitPriceRangeMstParams.setLowPrice(200);
         l_equityLimitPriceRangeMstParams.setCapPrice(350);
         l_equityLimitPriceRangeMstParams.setRange(100);
         l_equityLimitPriceRangeMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("101010","HHMMSS"));
         l_equityLimitPriceRangeMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("101010","HHMMSS"));
         return l_equityLimitPriceRangeMstParams;
 
     }
 
     /**
      * �l���e�[�u��
      * @@return
      */
     public static EquityTickValuesMstParams getEquityTickValuesMstRow()
     {
         //  �s��R�[�h   market_code   VARCHAR2              2           NOT NULL        
         // �����l    low_price   DECIMAL                18  12  6   NOT NULL        
         // ����l  cap_price            DECIMAL             18  12  6   NULL        
         //  ���ݒl   tick_value          DECIMAL             18  12  6   NULL        
         //  �쐬���t   created_timestamp          DATE  NOT NULL      
         // �X�V���t   last_updated_timestamp 
         EquityTickValuesMstParams l_equityTickValuesMstParams =
             new EquityTickValuesMstParams();
         l_equityTickValuesMstParams.setMarketCode("1");
         l_equityTickValuesMstParams.setLowPrice(200);
         l_equityTickValuesMstParams.setCapPrice(350);
         l_equityTickValuesMstParams.setTickValue(100);
         l_equityTickValuesMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("101010","HHMMSS"));
         l_equityTickValuesMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("101010","HHMMSS"));
         return l_equityTickValuesMstParams;
 
     }
     
     /**
      * �����\�񌚊��ԍώw����e�[�u��
      */
     public static RsvEqClosingContractSpecParams getRsvEqClosingContractSpecRow()
     {
        //     �����h�c          account_id                      
        //     �⏕�����h�c        sub_account_id                      
        //     �����h�c          order_id                        
        //     �����h�c          contract_id                     
        //     �ԍϘA��          closing_serial_no                       
        //     �ԍϒ�������        quantity                        
        //     �쐬���t          created_timestamp                       
        //     �X�V���t          last_updated_timestamp                      
         RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams = new RsvEqClosingContractSpecParams();
         l_rsvEqClosingContractSpecParams.setAccountId(1002L);
         l_rsvEqClosingContractSpecParams.setSubAccountId(1002L);
         l_rsvEqClosingContractSpecParams.setOrderId(1002L);
         l_rsvEqClosingContractSpecParams.setContractId(12347);
         l_rsvEqClosingContractSpecParams.setClosingSerialNo(123);
         l_rsvEqClosingContractSpecParams.setQuantity(1002D);
         l_rsvEqClosingContractSpecParams.setCreatedTimestamp(WEB3DateUtility.getDate("101010","HHMMSS"));
         l_rsvEqClosingContractSpecParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("101010","HHMMSS"));
         return l_rsvEqClosingContractSpecParams;
     }
     
     /**
      * ���������ʒm�L���[�e�[�u��
      * @@return
      */
     public static HostEqtypeCloseOrderNotifyParams getHostEqtypeCloseOrderNotifyRow()
     {
         HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
             new HostEqtypeCloseOrderNotifyParams();
         
         l_hostEqtypeCloseOrderNotifyParams.setAccountCode("2512246");
         l_hostEqtypeCloseOrderNotifyParams.setBranchCode("624");
         l_hostEqtypeCloseOrderNotifyParams.setCloseNotifyType("1");
         l_hostEqtypeCloseOrderNotifyParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_hostEqtypeCloseOrderNotifyParams.setExecutedQuantity(2);
         l_hostEqtypeCloseOrderNotifyParams.setInstitutionCode("0D");
         l_hostEqtypeCloseOrderNotifyParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         l_hostEqtypeCloseOrderNotifyParams.setNoticeType("0");
         l_hostEqtypeCloseOrderNotifyParams.setOrderRequestNumber("1");
         l_hostEqtypeCloseOrderNotifyParams.setReasonCode("1");
         l_hostEqtypeCloseOrderNotifyParams.setNoticeNumber(1);
         l_hostEqtypeCloseOrderNotifyParams.setRequestCode("1");
         l_hostEqtypeCloseOrderNotifyParams.setStatus("1");
         l_hostEqtypeCloseOrderNotifyParams.setTraderCode("1");
         l_hostEqtypeCloseOrderNotifyParams.setVirtualServerNumberMarket("TOKYO");
         
         return l_hostEqtypeCloseOrderNotifyParams;
     }
     /*
      * �T�[�r�X�\�������e�[�u��(srv_appli_attribute)
      * 
      */
     public static SrvAppliAttributeParams getSrvAppliAttributeRow()
     {
         SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
         l_srvAppliAttributeParams.setInstitutionCode("338");
         l_srvAppliAttributeParams.setBranchCode("A01");
         l_srvAppliAttributeParams.setAccountCode("1234567");
         l_srvAppliAttributeParams.setSrvDiv("1234");
         l_srvAppliAttributeParams.setAppliAttribute("1");
         l_srvAppliAttributeParams.setAppliStartDate(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
         l_srvAppliAttributeParams.setAppliEndDate(WEB3DateUtility.getDate("20070616","yyyyMMdd"));
         l_srvAppliAttributeParams.setLastUpdater("0213");
         l_srvAppliAttributeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_srvAppliAttributeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

         return l_srvAppliAttributeParams;
     }
     /*
      * �T�[�r�X���p�萔�������e�[�u��(srv_regi_comm_cond)
      * path:  $/WEBBROKER3/09Requirement�Ǘ�/3.0.�T�[�r�X���p/DB���C�A�E�g
      *         /00.�T�[�r�X���p/�T�[�r�X���p�萔�������e�[�u���d�l.xls
      */
     public static SrvRegiCommCondParams getSrvRegiCommCondRow()
     {
         SrvRegiCommCondParams l_srvRegiCommCondParams = new SrvRegiCommCondParams();
         l_srvRegiCommCondParams.setInstitutionCode("123");
         l_srvRegiCommCondParams.setSrvDiv("1234");
         l_srvRegiCommCondParams.setOrderAccProduct("01");
         l_srvRegiCommCondParams.setLastUpdater("updater");
         l_srvRegiCommCondParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_srvRegiCommCondParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         
         return l_srvRegiCommCondParams;
     }
     
     /*
      * �T�[�r�X�\���o�^�e�[�u��(srv_regi_application)
      * path:  $/WEBBROKER3/09Requirement�Ǘ�/2.1.����/�c�a���C�A�E�g/00.����
      *         /00.��ЁE���X�E�ڋq�E�s��/�T�[�r�X�\���o�^�e�[�u���d�l.xls
      */
     public static SrvRegiApplicationParams getSrvRegiApplicationRow()
     {
         SrvRegiApplicationParams l_srvRegiApplicationParams = new SrvRegiApplicationParams();
         l_srvRegiApplicationParams.setInstitutionCode("123");
         l_srvRegiApplicationParams.setBranchCode("123");
         l_srvRegiApplicationParams.setSrvDiv("1234");
         l_srvRegiApplicationParams.setAccountCode("1234567");
         l_srvRegiApplicationParams.setRegistId(123);
         l_srvRegiApplicationParams.setAppliStartDate(Calendar.getInstance().getTime());
         l_srvRegiApplicationParams.setAppliEndDate(Calendar.getInstance().getTime());
         l_srvRegiApplicationParams.setOrderId(1002L);
         l_srvRegiApplicationParams.setAppliDate(Calendar.getInstance().getTime());
         l_srvRegiApplicationParams.setPaymentDiv("0");
         l_srvRegiApplicationParams.setAppliLotDiv("0");
         l_srvRegiApplicationParams.setEffectiveDiv("0");
         l_srvRegiApplicationParams.setCancelDiv("0");
         l_srvRegiApplicationParams.setUseAmt(1234);
         l_srvRegiApplicationParams.setPaymentDate(Calendar.getInstance().getTime());
         l_srvRegiApplicationParams.setCancelLimitDate(Calendar.getInstance().getTime());
         l_srvRegiApplicationParams.setLastUpdater("123");
         l_srvRegiApplicationParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_srvRegiApplicationParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         l_srvRegiApplicationParams.setFreeSrvDiv("0");
         
         return l_srvRegiApplicationParams;
     }
     
     
     /*
      * �T�[�r�X���p���ԗ����e�[�u��(srv_regi_charge)
      * path: ���O:  $/WEBBROKER3/09Requirement�Ǘ�/3.0.�T�[�r�X���p/DB���C�A�E�g
      *              /00.�T�[�r�X���p/�T�[�r�X���p���ԗ����e�[�u���d�l.xls
      * 
      */
     public static SrvRegiChargeParams getSrvRegiChargeRow()
     {
         SrvRegiChargeParams l_srvRegiChargeParams = new SrvRegiChargeParams();
         l_srvRegiChargeParams.setInstitutionCode("123");
         l_srvRegiChargeParams.setSrvDiv("1234");
         l_srvRegiChargeParams.setConsecutiveNumbers(123);
         l_srvRegiChargeParams.setSrvUsePeriodDiv("1");
         l_srvRegiChargeParams.setSrvUsePeriod(10);
         l_srvRegiChargeParams.setUseAmt(100);
         l_srvRegiChargeParams.setLastUpdater("updater");
         l_srvRegiChargeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_srvRegiChargeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         
         return l_srvRegiChargeParams;
     }
     
     /*
      * �R���T�[�r�X�}�X�^�e�[�u��(compliance_audit_service_mst)
      * path: $/WEBBROKER3/09Requirement�Ǘ�/5.0.�R���v���C�A���X/DB���C�A�E�g
      *        /�R���T�[�r�X�}�X�^�e�[�u���d�l.xls
      * 
      * */
//     public static ComplianceAuditServiceMstParams getComplianceAuditServiceMstRow()
//     {
//         ComplianceAuditServiceMstParams l_complianceAuditServiceMstParams= 
//             new ComplianceAuditServiceMstParams();
//         
//         l_complianceAuditServiceMstParams.setServiceId(123451L);
//         l_complianceAuditServiceMstParams.setInstitutionCode("0D");
//         l_complianceAuditServiceMstParams.setTransactionCategory("D0102");
//         l_complianceAuditServiceMstParams.setCancelServiceFlag("0");
//         l_complianceAuditServiceMstParams.setPullDownShowDiv("1");
//         l_complianceAuditServiceMstParams.setSortNo(001);
//         l_complianceAuditServiceMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//         l_complianceAuditServiceMstParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//         
//         return l_complianceAuditServiceMstParams;
//     }
     
     /*
      * �T�[�r�X�}�X�^�[�e�[�u��(srv_regi_master)
      * path: $/WEBBROKER3/09Requirement�Ǘ�/3.0�T�[�r�X���p/DB���C�A�E�g 
      *        /�T�[�r�X�}�X�^�[�e�[�u���d�l.xls
      */
     public static SrvRegiMasterParams getSrvRegiMasterRow()
     {
         SrvRegiMasterParams l_srvRegiMasterParams =
             new SrvRegiMasterParams();
         
         l_srvRegiMasterParams.setInstitutionCode("0D");
         l_srvRegiMasterParams.setSrvDiv("1234");
         l_srvRegiMasterParams.setSrvName("SrvName");
         l_srvRegiMasterParams.setSrvStatus("0");
         l_srvRegiMasterParams.setOfferingDiv("0");
         l_srvRegiMasterParams.setSrvUrl("SrvUrl");
         l_srvRegiMasterParams.setLastUpdater("0213");
         l_srvRegiMasterParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_srvRegiMasterParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         
         return l_srvRegiMasterParams;
     }
     
     /*
      * �\���v�T�[�r�X�e�[�u��(srv_regi_setup)
      * path: $/WEBBROKER3/09Requirement�Ǘ�/3.0�T�[�r�X���p/DB���C�A�E�g
      *        /�\���v�T�[�r�X�e�[�u���d�l.xls
      */
     public static SrvRegiSetupParams getSrvRegiSetupRow()
     {
         SrvRegiSetupParams l_srvRegiSetupParams =
             new SrvRegiSetupParams();
         
         l_srvRegiSetupParams.setInstitutionCode("0D");
         l_srvRegiSetupParams.setSrvDiv("1234");
         l_srvRegiSetupParams.setSummary("summary");
         l_srvRegiSetupParams.setLotDiv("0");
         l_srvRegiSetupParams.setTrialPeriodDiv("1");
         l_srvRegiSetupParams.setSrvContents("Contents");
         l_srvRegiSetupParams.setSrvExplanUrl("url");
         l_srvRegiSetupParams.setStartMailDiv("0");
         l_srvRegiSetupParams.setEndMailDiv("0");
         l_srvRegiSetupParams.setElectricIssueDiv("0");
         l_srvRegiSetupParams.setMinCommAmt(0);
         l_srvRegiSetupParams.setSupplyDiv("0");
         l_srvRegiSetupParams.setLastUpdater("updater");
         l_srvRegiSetupParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_srvRegiSetupParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         
         return l_srvRegiSetupParams;
     }
     
     /*
      * ���[���G���W������̒ʒm�e�[�u��(rls_con_order_hit_notify)
      * path: $/WEBBROKER3/09Requirement�Ǘ�/4.4.�g���K�[����/DB���C�A�E�g
      *       /�Q�l�����i�k�����őΉ������j/���[���G���W������̒ʒm�e�[�u���d�l
      */
     public static RlsConOrderHitNotifyParams getRlsConOrderHitNotifyRow()
     {
         RlsConOrderHitNotifyParams l_rlsConOrderHitNotifyParams = new RlsConOrderHitNotifyParams();
         l_rlsConOrderHitNotifyParams.setAccountId(333812512203L);
         l_rlsConOrderHitNotifyParams.setSubAccountId(333812512203L);
         l_rlsConOrderHitNotifyParams.setOrderType(1);
         l_rlsConOrderHitNotifyParams.setOrderId(1002L);
         l_rlsConOrderHitNotifyParams.setProductType(ProductTypeEnum.OTHER);
         l_rlsConOrderHitNotifyParams.setSerialNoInParent(0);
         l_rlsConOrderHitNotifyParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_rlsConOrderHitNotifyParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         
         return l_rlsConOrderHitNotifyParams;
     }
     
     /*
      * (�Ǘ��ҋ��ʁj����۰��ð���(administrator_upload)
      * path: $/WEBBROKER3\09Requirement�Ǘ�\2.1.����\�c�a���C�A�E�g
      *       \00.����\04.�Ǘ��ҋ���\�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���d�l.xls
      */
     public static AdministratorUploadParams getAdministratorUploadRow()
     {
         AdministratorUploadParams l_administratorUploadParams =
             new AdministratorUploadParams();

         l_administratorUploadParams.setAdministratorUploadId(10L);
         l_administratorUploadParams.setInstitutionCode("0D");
         l_administratorUploadParams.setBranchCode("A01");
         l_administratorUploadParams.setProductType(ProductTypeEnum.IFO);
         l_administratorUploadParams.setUploadFileId("UploadFileId");
         l_administratorUploadParams.setUploadStartTimestamp(Calendar.getInstance().getTime());
         l_administratorUploadParams.setUploadEndTimestamp(Calendar.getInstance().getTime());
         l_administratorUploadParams.setUploadCount(123);
         l_administratorUploadParams.setUploadKey(12345);
         l_administratorUploadParams.setNote1("note1");
         l_administratorUploadParams.setNote2("note2");
         l_administratorUploadParams.setNote3("note3");
         
         return l_administratorUploadParams;
     }
     
     /*
      * (�Ǘ��ҋ���)����۰��������(administrator_upload_temp)
      * path: $/WEBBROKER3\09Requirement�Ǘ�\2.1.����\�c�a���C�A�E�g
      *       \00.����\04.�Ǘ��ҋ���\�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����e�[�u���d�l.xls
      */
     public static AdministratorUploadTempParams getAdministratorUploadTempRow()
     {
         AdministratorUploadTempParams l_administratorUploadTempParams =
             new AdministratorUploadTempParams();
         
         l_administratorUploadTempParams.setAdministratorUploadId(10L);
         l_administratorUploadTempParams.setLineNumber(12345);
         l_administratorUploadTempParams.setCsvLineValue("CsvLineValue");
         l_administratorUploadTempParams.setUpdateTimestamp(Calendar.getInstance().getTime());
         
         return l_administratorUploadTempParams;
     }

     /*
      * �����U�֓o�^�}�X�^�e�[�u��(direct_debit)
      * path: $/WEBBROKER3/09Requirement�Ǘ�/2.1/DB���C�A�E�g/00/00
      *        /�����U�֓o�^�}�X�^�e�[�u���d�l.xls
      */
     public static DirectDebitParams getDirectDebitRow()
     {
         DirectDebitParams l_directDebitParams = new DirectDebitParams();
         l_directDebitParams.setInstitutionCode("KK");
         l_directDebitParams.setBranchCode("BBB");
         l_directDebitParams.setAccountCode("KKKKKKC");
         l_directDebitParams.setDesignateDiv("1");
         l_directDebitParams.setTransferDiv("1");
         l_directDebitParams.setFinSaveDiv("1");
         return l_directDebitParams;
     }

     /*
      * ���Z�@@�ցi��s�j�}�X�^�e�[�u��(fin_institution_bank)
      * path: $/WEBBROKER3/09Requirement�Ǘ�/2.1/DB���C�A�E�g/00/00
      *        /���Z�@@�ցi��s�j�}�X�^�e�[�u���d�l.xls
      */
     public static FinInstitutionBankParams getFinInstitutionBankRow()
     {
         FinInstitutionBankParams l_finInstitutionBankParams = new FinInstitutionBankParams();
         l_finInstitutionBankParams.setFinInstitutionCode("1234");
         l_finInstitutionBankParams.setFinBranchCode("123");
         l_finInstitutionBankParams.setFinInstitutionNameKana("��s���i�J�i�j");         
         l_finInstitutionBankParams.setFinBranchNameKana("�x�X���i�J�i�j");
         l_finInstitutionBankParams.setFinInstitutionName("��s���i�����j");
         l_finInstitutionBankParams.setFinBranchName("�x�X���i�����j");
         l_finInstitutionBankParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
         l_finInstitutionBankParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());         
         return l_finInstitutionBankParams;
     }
     
     /*
      * �g�����U�N�V�����i�����ڋq���薾�ׁj�e�[�u��(eqtype_fin_transaction)
      * path: $/WEBBROKER3/09Requirement�Ǘ�/2.3.����\�c�a���C�A�E�g
      *       \01.����\01.�����E���\�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�d�l.xls
      * 
      * */
     public static EqtypeFinTransactionParams getEqtypeFinTransactionParams()
     {
         EqtypeFinTransactionParams l_finTransactionParams = new EqtypeFinTransactionParams();
         //�g�����U�N�V�����h�c   fin_transaction_id      NOT NULL
         l_finTransactionParams.setFinTransactionId(111L);
         
         //�����h�c              account_id              NOT NULL
         l_finTransactionParams.setAccountId(333812512203L);
         
         //�⏕�����h�c           sub_account_id         NOT NULL
         l_finTransactionParams.setSubAccountId(33381251220301L);
         
         //�����h�c               product_id            NOT NULL
         l_finTransactionParams.setProductId(123456);
         //�g�����U�N�V�����^�C�v  fin_transaction_type  NOT NULL

         l_finTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_EQUITY_BUY);
         
         //�g�����U�N�V�����J�e�S��  fin_transaction_categ       NOT NULL
         l_finTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);
         
         //�g�����U�N�V������������  fin_transaction_timestamp   NOT NULL
         l_finTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
         
         //�ŋ敪               tax_type            NOT NULL
         l_finTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
         
         //��n��               delivery_date           NOT NULL
         l_finTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
         
         //��n���          net_amount          NOT NULL
         l_finTransactionParams.setNetAmount(1.0);
         
         //�����^�C�v         product_type            NOT NULL
         l_finTransactionParams.setProductType(ProductTypeEnum.EQUITY);
         
         //�s��h�c          market_id           NULL
         //���P��          price               NULL
         //��萔��          quantity            NOT NULL
         l_finTransactionParams.setQuantity(1000);
         
         //�����h�c          order_id            NULL
         l_finTransactionParams.setOrderId(11);
         
         //�����P�ʂh�c            order_unit_id           NULL
         l_finTransactionParams.setOrderUnitId(1001);
         
         //���h�c          order_execution_id      NULL
         //�ϑ��萔��         commission_fee          NOT NULL
         l_finTransactionParams.setCommissionFee(1000);
         
         //�ϑ��萔�������      commission_fee_tax          NOT NULL
         l_finTransactionParams.setCommissionFeeTax(1000);
         
         //���Y�h�c          asset_id            NULL
         //�����̎�n���z           imported_paid_value     NOT NULL
         l_finTransactionParams.setImportedPaidValue(1000);
         
         //���萔��          imported_setup_fee      NOT NULL
         l_finTransactionParams.setImportedSetupFee(1000);
         
         //���萔�������           imported_setup_fee_tax      NOT NULL
         l_finTransactionParams.setImportedPayInterestFeeTax(1000);
         
         //���`������         imported_name_transfer_fee  NULL
         //���`�����������      imported_name_transfer_fee_tax  NULL
         //���n�v���z         capital_gain            NOT NULL
         l_finTransactionParams.setCapitalGain(1000);
         
         //���n�v�Ŋz         capital_gain_tax        NOT NULL
         l_finTransactionParams.setCapitalGainTax(1000);
         
         //�����h�c          contract_id         NULL
         l_finTransactionParams.setContractId(12347);
         
         //�Ǘ���               imported_management_fee     NOT NULL
         l_finTransactionParams.setImportedManagementFee(422);
         
         //�Ǘ�������            imported_management_fee_tax NOT NULL
         l_finTransactionParams.setImportedManagementFeeTax(533);
         
         //������               imported_interest_fee       NOT NULL
         l_finTransactionParams.setImportedInterestFee(1000);
         
         //�����������            imported_interest_fee_tax   NOT NULL
         l_finTransactionParams.setImportedInterestFeeTax(1000);
         
         //�t����               imported_pay_interest_fee   NULL
         //�t���������            imported_pay_interest_fee_tax   NULL
         //�݊���               imported_loan_equity_fee    NULL
         //���̑�               imported_other          NULL
         //���p�ۗL���Y�̊Ǘ���        transfered_asset_mng_fee    NULL
         //���p�ۗL���Y�̊Ǘ������� transfered_asset_mng_fee_tax    NULL
         //���p�ۗL���Y�̎萔��        transfered_asset_setup_fee  NULL
         //���p�ۗL���Y�̎萔������� transfered_asset_setup_fee_tax  NULL
         //���Y�̕뉿         transfered_asset_book_value NULL
         //�폜�t���O         delete_flag         NOT NULL
         l_finTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
         
         //�������o�ߗ��q      accrued_interest        NULL
         //�쐬���t          created_timestamp       NOT NULL
         l_finTransactionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
         
         //�X�V���t          last_updated_timestamp      NOT NULL
         l_finTransactionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
         //���n�v�L�����           capital_gain_status     NOT NULL
         l_finTransactionParams.setCapitalGainStatus("0");
         
         return l_finTransactionParams;
     }
     
     
     /**
      * FX�ڋq�e�[�u��(fx_account)
      * path:  $/WEBBROKER3/09Requirement�Ǘ�/2.9.���o��/�c�a���C�A�E�g
      *         /23.�ב֕ۏ؋�/FX�ڋq�e�[�u��.xls
      */
     public static FxAccountParams getFxAccountRow()
     {
         FxAccountParams l_fxAccountParams = new FxAccountParams();
         l_fxAccountParams.setFxAccountId(333812512246L);
         l_fxAccountParams.setInstitutionCode("0D");
         l_fxAccountParams.setBranchCode("624");
         l_fxAccountParams.setFxSystemCode("02");
         l_fxAccountParams.setAccountCode("2512246");
         l_fxAccountParams.setFxAccountDiv("1");
         l_fxAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_fxAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         return l_fxAccountParams;
     }

    /**
      * FX�����ԍ��e�[�u��(fx_account_code)
      * path: $/WEBBROKER3/09Requirement�Ǘ�/2.9.���o��/�c�a���C�A�E�g
      *        /23.�ב֕ۏ؋�/FX�����ԍ��e�[�u��.xls
      */
     public static FxAccountCodeParams getFxAccountCodeRow()
     {
         FxAccountCodeParams l_fxAccountCodeParams = new FxAccountCodeParams();
         l_fxAccountCodeParams.setInstitutionCode("0D");
         l_fxAccountCodeParams.setBranchCode("624");
         l_fxAccountCodeParams.setFxSystemCode("02");
         l_fxAccountCodeParams.setAccountCode("2512246");
         l_fxAccountCodeParams.setFxCourseDiv("1");
         l_fxAccountCodeParams.setFxAccountCode("25");
         l_fxAccountCodeParams.setLastUpdater("123456");
         l_fxAccountCodeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_fxAccountCodeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         
         return l_fxAccountCodeParams;
     }
     
     
    /*
     * ��Е�FX�V�X�e�������e�[�u��(comp_fx_condition)
     * path: $/WEBBROKER3/09Requirement�Ǘ�/2.9.���o��/�c�a���C�A�E�g/23.�ב֕ۏ؋�
     *        /��Е�FX�V�X�e�������e�[�u��.xls
     */
    public static CompFxConditionParams getCompFxConditionRow()
    {
        CompFxConditionParams l_compFxConditionParams = new CompFxConditionParams();
        l_compFxConditionParams.setInstitutionCode("0D");
        l_compFxConditionParams.setBranchCode("624");
        l_compFxConditionParams.setFxSystemCode("02");
        l_compFxConditionParams.setGroupName("name");
        l_compFxConditionParams.setUrl("url");
        l_compFxConditionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
        l_compFxConditionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
        l_compFxConditionParams.setFxHeadOfLoginId("159");
        l_compFxConditionParams.setSingleSignOnUrl("url");
        l_compFxConditionParams.setValidTerm("12");
        l_compFxConditionParams.setFxSystemDiv("1");
        //��t���ԋ敪    trading_time_type    VARCHAR2       2    NotNull
        l_compFxConditionParams.setTradingTimeType("01");
        //�I�����C�������J�ݎ��{�敪    online_acc_open    VARCHAR2      2    NotNull
        l_compFxConditionParams.setOnlineAccOpen("0");
        //SOAP�ڑ����{�敪    soap_connect_div    VARCHAR2        2    NotNull
        l_compFxConditionParams.setSoapConnectDiv("0");
        //�������    acc_type    VARCHAR2      2    NotNull
        l_compFxConditionParams.setAccType("1");
        //MRF�������敪    mrf_allow_div    VARCHAR2        2    NotNull
        l_compFxConditionParams.setMrfAllowDiv("0");
        //�����敪���A���X�V    acc_open_real_update    VARCHAR2     2    NotNull
        l_compFxConditionParams.setAccOpenRealUpdate("0");
        //���⓯�Ӄ`�F�b�N���{�敪    question_check_div    VARCHAR2        2    NotNull
        l_compFxConditionParams.setQuestionCheckDiv("0");
        //FX�V�X�e������ID    fx_system_id    NUMBER      18    NotNull
        l_compFxConditionParams.setFxSystemId(6241001);
        l_compFxConditionParams.setGetTransferableAmtDiv("1");
//        l_compFxConditionParams.setMultiCfdAccDiv("0");
//        l_compFxConditionParams.setCommonTransferPageDiv("0");
        return l_compFxConditionParams;
    }

    /**
     * GFT�U�֏󋵃e�[�u��Row���쐬.<BR>
     */
    public static GftTransferStatusParams getGftTransferStatusRow()
    {

        GftTransferStatusParams l_gftTransferStatusParams = new GftTransferStatusParams();

        l_gftTransferStatusParams.setInstitutionCode("0D");
        l_gftTransferStatusParams.setBranchCode("381");
        l_gftTransferStatusParams.setAccountCode("2512246");
        l_gftTransferStatusParams.setOrderRequestNumber("1980003");
        l_gftTransferStatusParams.setOperationDiv("1");
        l_gftTransferStatusParams.setCourseDiv("0");
        l_gftTransferStatusParams.setFxAccountCode("100401");
        l_gftTransferStatusParams.setTransferStatusDiv("1");
        l_gftTransferStatusParams.setSendRcvDiv("1");

        l_gftTransferStatusParams.setCreatedTimestamp(Calendar.getInstance().getTime());

        l_gftTransferStatusParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_gftTransferStatusParams;
    }
    
    /**
     * �����e�[�u���i�w�b�_�j<BR>
     */
    public static AioOrderParams getAioOrderRow()
    {
        AioOrderParams l_aioOrderParams = new AioOrderParams();
        l_aioOrderParams.setAccountId(101001010010L);
        l_aioOrderParams.setSubAccountId(10100101001007L);
        l_aioOrderParams.setProductType(ProductTypeEnum.AIO);
        l_aioOrderParams.setOrderId(1111);

        l_aioOrderParams.setCreatedTimestamp(Calendar.getInstance().getTime());

        l_aioOrderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_aioOrderParams;
    }
    
    /*
     * �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j(tp_calc_result_margin)
     * path: $/WEBBROKER3/09Requirement�Ǘ�/3.6.�]��/�c�a���C�A�E�g/10.�]��
     *        /�]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j�d�l.xls
     */
    public static TpCalcResultMarginParams getTpCalcResultMarginRow()
    {
        
        TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();

        l_tpCalcResultMarginParams.setCalcResultMarginId(1001);
        l_tpCalcResultMarginParams.setAccountId(333812512246L);
        l_tpCalcResultMarginParams.setAccountBalance0(1);
        l_tpCalcResultMarginParams.setAccountBalance1(1);
        l_tpCalcResultMarginParams.setAccountBalance2(1);
        l_tpCalcResultMarginParams.setAccountBalance3(1);
        l_tpCalcResultMarginParams.setAccountBalance4(1);
        l_tpCalcResultMarginParams.setAccountBalance5(1);
        l_tpCalcResultMarginParams.setTodayExecutedAmount0(1);
        l_tpCalcResultMarginParams.setTodayExecutedAmount1(1);
        l_tpCalcResultMarginParams.setTodayExecutedAmount2(1);
        l_tpCalcResultMarginParams.setTodayExecutedAmount3(1);
        l_tpCalcResultMarginParams.setTodayExecutedAmount4(1);
        l_tpCalcResultMarginParams.setTodayExecutedAmount5(1);
        l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(1);
        l_tpCalcResultMarginParams.setTodayUnexecutedAmount1(1);
        l_tpCalcResultMarginParams.setTodayUnexecutedAmount2(1);
        l_tpCalcResultMarginParams.setTodayUnexecutedAmount3(1);
        l_tpCalcResultMarginParams.setTodayUnexecutedAmount4(1);

        l_tpCalcResultMarginParams.setTodayUnexecutedAmount5(1);
        l_tpCalcResultMarginParams.setDayTradeRestraint0(1);
        l_tpCalcResultMarginParams.setDayTradeRestraint1(1);
        l_tpCalcResultMarginParams.setDayTradeRestraint2(1);
        l_tpCalcResultMarginParams.setDayTradeRestraint3(1);
        l_tpCalcResultMarginParams.setDayTradeRestraint4(1);
        l_tpCalcResultMarginParams.setOtherRestraint0(1);
        l_tpCalcResultMarginParams.setOtherRestraint1(1);
        l_tpCalcResultMarginParams.setOtherRestraint2(1);
        l_tpCalcResultMarginParams.setOtherRestraint3(1);
        l_tpCalcResultMarginParams.setOtherRestraint4(1);
        l_tpCalcResultMarginParams.setOtherRestraint5(1);
        l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(1);
        l_tpCalcResultMarginParams.setSubstituteSecurityAsset1(1);
        l_tpCalcResultMarginParams.setSubstituteSecurityAsset2(1);
        l_tpCalcResultMarginParams.setSubstituteSecurityAsset3(1);
        l_tpCalcResultMarginParams.setSubstituteSecurityAsset4(1);
        l_tpCalcResultMarginParams.setSubstituteSecurityAsset5(1);
        l_tpCalcResultMarginParams.setContractAmount0(1);
        l_tpCalcResultMarginParams.setContractAmount1(1);
        l_tpCalcResultMarginParams.setContractAmount2(1);
        l_tpCalcResultMarginParams.setContractAmount3(1);
        l_tpCalcResultMarginParams.setContractAmount4(1);
        l_tpCalcResultMarginParams.setContractAmount5(1);
        l_tpCalcResultMarginParams.setDayRepayContractAmount0(1);
        l_tpCalcResultMarginParams.setDayRepayContractAmount1(1);
        l_tpCalcResultMarginParams.setDayRepayContractAmount2(1);
        l_tpCalcResultMarginParams.setDayRepayContractAmount3(1);
        l_tpCalcResultMarginParams.setDayRepayContractAmount4(1);
        l_tpCalcResultMarginParams.setDayRepayContractAmount5(1);
        l_tpCalcResultMarginParams.setMarginDeposit0(1);
        l_tpCalcResultMarginParams.setMarginDeposit1(1);
        l_tpCalcResultMarginParams.setMarginDeposit2(1);
        l_tpCalcResultMarginParams.setMarginDeposit3(1);
        l_tpCalcResultMarginParams.setMarginDeposit4(1);
        l_tpCalcResultMarginParams.setMarginDeposit5(1);
        l_tpCalcResultMarginParams.setCashMarginDeposit0(1);
        l_tpCalcResultMarginParams.setCashMarginDeposit1(1);
        l_tpCalcResultMarginParams.setCashMarginDeposit2(1);
        l_tpCalcResultMarginParams.setCashMarginDeposit3(1);
        l_tpCalcResultMarginParams.setCashMarginDeposit4(1);
        l_tpCalcResultMarginParams.setCashMarginDeposit5(1);
        l_tpCalcResultMarginParams.setContractAssetProfitLoss(1);
        l_tpCalcResultMarginParams.setUndeliContractAmount0(1);
        l_tpCalcResultMarginParams.setUndeliContractAmount1(1);
        l_tpCalcResultMarginParams.setUndeliContractAmount2(1);
        l_tpCalcResultMarginParams.setUndeliContractAmount3(1);
        l_tpCalcResultMarginParams.setUndeliMarginDeposit0(1);
        l_tpCalcResultMarginParams.setUndeliMarginDeposit1(1);
        l_tpCalcResultMarginParams.setUndeliMarginDeposit2(1);
        l_tpCalcResultMarginParams.setUndeliMarginDeposit3(1);
        l_tpCalcResultMarginParams.setUndeliCashMarginDeposit0(1);
        l_tpCalcResultMarginParams.setUndeliCashMarginDeposit1(1);
        l_tpCalcResultMarginParams.setUndeliCashMarginDeposit2(1);
        l_tpCalcResultMarginParams.setUndeliCashMarginDeposit3(1);
        l_tpCalcResultMarginParams.setUndeliContractLoss0(1);
        l_tpCalcResultMarginParams.setUndeliContractLoss1(1);
        l_tpCalcResultMarginParams.setUndeliContractLoss2(1);
        l_tpCalcResultMarginParams.setUndeliContractLoss3(1);
        l_tpCalcResultMarginParams.setUndeliContractProfit0(1);
        l_tpCalcResultMarginParams.setUndeliContractProfit1(1);
        l_tpCalcResultMarginParams.setUndeliContractProfit2(1);
        l_tpCalcResultMarginParams.setUndeliContractProfit3(1);
        l_tpCalcResultMarginParams.setContractTotalCost(1);
        l_tpCalcResultMarginParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tpCalcResultMarginParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_tpCalcResultMarginParams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
        return l_tpCalcResultMarginParams;
    }

    /**
     * �]�͌v�Z���ʏڍ׃e�[�u���i�M�p�ڋq�j(tp_calc_result_margin_detail)
     * path: $/WEBBROKER3/09Requirement�Ǘ�/3.6.�]��/�c�a���C�A�E�g/10.�]��
     *        /�]�͌v�Z���ʏڍ׃e�[�u���i�M�p�ڋq�j�d�l.xls
     */
    public static TpCalcResultMarginDetailParams getTpCalcResultMarginDetailRow()
    {
        TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams =
            new TpCalcResultMarginDetailParams();

        l_tpCalcResultMarginDetailParams.setAccountId(101001010010L);
        l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1001);
        l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_tpCalcResultMarginDetailParams;
    }
    
    /**
     * �]�͌v�Z���ʃe�[�u���i�����ڋq�j(tp_calc_result_equity)
     * 
     */
    public static TpCalcResultEquityParams getTpCalcResultEquityRow()
    {
        TpCalcResultEquityParams l_tpCalcResultEquityParams = new TpCalcResultEquityParams();

        l_tpCalcResultEquityParams.setCalcResultEquityId(1001);
        l_tpCalcResultEquityParams.setAccountId(101001010010L);
        l_tpCalcResultEquityParams.setAccountBalance0(1);
        l_tpCalcResultEquityParams.setAccountBalance1(1);
        l_tpCalcResultEquityParams.setAccountBalance2(1);
        l_tpCalcResultEquityParams.setAccountBalance3(1);
        l_tpCalcResultEquityParams.setAccountBalance4(1);
        l_tpCalcResultEquityParams.setAccountBalance5(1);
        l_tpCalcResultEquityParams.setTodayExecutedAmount0(1);
        l_tpCalcResultEquityParams.setTodayExecutedAmount1(1);
        l_tpCalcResultEquityParams.setTodayExecutedAmount2(1);
        l_tpCalcResultEquityParams.setTodayExecutedAmount3(1);
        l_tpCalcResultEquityParams.setTodayExecutedAmount4(1);
        l_tpCalcResultEquityParams.setTodayExecutedAmount5(1);
        l_tpCalcResultEquityParams.setTodayUnexecutedAmount0(1);
        l_tpCalcResultEquityParams.setTodayUnexecutedAmount1(1);
        l_tpCalcResultEquityParams.setTodayUnexecutedAmount2(1);
        l_tpCalcResultEquityParams.setTodayUnexecutedAmount3(1);
        l_tpCalcResultEquityParams.setTodayUnexecutedAmount4(1);
        l_tpCalcResultEquityParams.setTodayUnexecutedAmount5(1);
        l_tpCalcResultEquityParams.setDayTradeRestraint0(1);
        l_tpCalcResultEquityParams.setDayTradeRestraint1(1);
        l_tpCalcResultEquityParams.setDayTradeRestraint2(1);
        l_tpCalcResultEquityParams.setDayTradeRestraint3(1);
        l_tpCalcResultEquityParams.setDayTradeRestraint4(1);
        l_tpCalcResultEquityParams.setOtherRestraint0(1);
        l_tpCalcResultEquityParams.setOtherRestraint1(1);
        l_tpCalcResultEquityParams.setOtherRestraint2(1);
        l_tpCalcResultEquityParams.setOtherRestraint3(1);
        l_tpCalcResultEquityParams.setOtherRestraint4(1);
        l_tpCalcResultEquityParams.setOtherRestraint5(1);
        l_tpCalcResultEquityParams.setTrustSecurityAsset0(1);
        l_tpCalcResultEquityParams.setTrustSecurityAsset1(1);
        l_tpCalcResultEquityParams.setTrustSecurityAsset2(1);
        l_tpCalcResultEquityParams.setTrustSecurityAsset3(1);
        l_tpCalcResultEquityParams.setTrustSecurityAsset4(1);
        l_tpCalcResultEquityParams.setTrustSecurityAsset5(1);
        l_tpCalcResultEquityParams.setCashDepositRestraint0(1);
        l_tpCalcResultEquityParams.setCashDepositRestraint2(1);
        l_tpCalcResultEquityParams.setCashDepositRestraint3(1);
        l_tpCalcResultEquityParams.setCashDepositRestraint4(1);
        l_tpCalcResultEquityParams.setCashDepositRestraint5(1);
        l_tpCalcResultEquityParams.setCashDepositRestraint1(1);

        l_tpCalcResultEquityParams.setCreatedTimestamp(Calendar.getInstance().getTime());

        l_tpCalcResultEquityParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_tpCalcResultEquityParams;
    }

    /**
     * �]�͌v�Z���ʏڍ׃e�[�u���i�M�p�ڋq�j(tp_calc_result_margin_detail)
     * 
     */
    public static TpCalcResultEquityDetailParams getTpCalcResultEquityDetailRow()
    {
        TpCalcResultEquityDetailParams l_tpCalcResultEquityDetailParams = new TpCalcResultEquityDetailParams();
        l_tpCalcResultEquityDetailParams.setCalcResultEquityId(1001);
        l_tpCalcResultEquityDetailParams.setAccountId(101001010010L);

        l_tpCalcResultEquityDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());

        l_tpCalcResultEquityDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_tpCalcResultEquityDetailParams;
    }
    
    /**
     * �莞��z���t���������e�[�u��
     */
    public static MfFixedBuyingDrawAccountParams getMfFixedBuyingDrawAccountRow()
    {
        MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams = new MfFixedBuyingDrawAccountParams();
        l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
        l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
        l_mfFixedBuyingDrawAccountParams.setAccountCode("2512246");
        l_mfFixedBuyingDrawAccountParams.setFinInstitutionDiv("2");
        l_mfFixedBuyingDrawAccountParams.setFinInstitutionCode("0D");
        l_mfFixedBuyingDrawAccountParams.setFinBranchCode("381");
        l_mfFixedBuyingDrawAccountParams.setDepositDiv("1");
        l_mfFixedBuyingDrawAccountParams.setDrawAccountNo("1001");
        l_mfFixedBuyingDrawAccountParams.setDrawAccountNameKana("2");
        l_mfFixedBuyingDrawAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_mfFixedBuyingDrawAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_mfFixedBuyingDrawAccountParams;
    }
    
    /**
     * �莞��z���t�����e�[�u��
     */
    public static MfFixedBuyingCondParams getMfFixedBuyingCondRow()
    {
        MfFixedBuyingCondParams l_mfFixedBuyingCondParams = new MfFixedBuyingCondParams();
        l_mfFixedBuyingCondParams.setInstitutionCode("0D");
        l_mfFixedBuyingCondParams.setBranchCode("381");
        l_mfFixedBuyingCondParams.setAccountCode("2512246");
        l_mfFixedBuyingCondParams.setProductCode("0001000");
        l_mfFixedBuyingCondParams.setDrawDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        l_mfFixedBuyingCondParams.setValidStartDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        l_mfFixedBuyingCondParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_mfFixedBuyingCondParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_mfFixedBuyingCondParams;
    }
    
    /**
     * �����S�ۃ��[�������e�[�u��(stock_secured_loan)
     */
    public static StockSecuredLoanParams getStockSecuredLoanRow()
    {
        StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();
        l_stockSecuredLoanParams.setStockLoanAccountCode("abc");
        l_stockSecuredLoanParams.setAccountId(3325);
        l_stockSecuredLoanParams.setInstitutionCode("0D");
        l_stockSecuredLoanParams.setBranchCode("hh");
        l_stockSecuredLoanParams.setAccountCode("rr");
        l_stockSecuredLoanParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_stockSecuredLoanParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_stockSecuredLoanParams;
    }
    
    /**
     * �S�ۃ��[���o���S�����e�[�u��(security_cashout_restraint)
     */
    public static SecurityCashoutRestraintParams getSecurityCashoutRestraintRow()
    {
        SecurityCashoutRestraintParams l_securityCashoutRestraintParams = new SecurityCashoutRestraintParams();
        l_securityCashoutRestraintParams.setAccountId(333812512246L);
        l_securityCashoutRestraintParams.setInstitutionCode("0D");
        l_securityCashoutRestraintParams.setBranchCode("381");
        l_securityCashoutRestraintParams.setAccountCode("2512246");
        l_securityCashoutRestraintParams.setUseEnableLimit(560000L);
        l_securityCashoutRestraintParams.setCashoutRestraint(10000L);
        l_securityCashoutRestraintParams.setCashoutEnablieAmount(400000L);
        l_securityCashoutRestraintParams.setAgreeCancelFlg("0");
        l_securityCashoutRestraintParams.setAmountLockFlg("0");
        l_securityCashoutRestraintParams.setLastUpdater("330001");
        return l_securityCashoutRestraintParams;
    }
    
    /**
     * �����J�ݓ`�[�o�^��t�L���[(host_acc_open_accept)
     */
    public static HostAccOpenAcceptParams getHostAccOpenAcceptRow()
    {
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams = new HostAccOpenAcceptParams();
        
        l_hostAccOpenAcceptParams.setRequestCode("GI843");
        l_hostAccOpenAcceptParams.setInstitutionCode("0D");
        l_hostAccOpenAcceptParams.setBranchCode("381");
        l_hostAccOpenAcceptParams.setAccountCode("2512246");
        l_hostAccOpenAcceptParams.setTraderCode("1234");
        l_hostAccOpenAcceptParams.setOrderRequestNumber("1");
        l_hostAccOpenAcceptParams.setAcceptStatus("1");
        l_hostAccOpenAcceptParams.setErrorCode("0");
        l_hostAccOpenAcceptParams.setStatus("0");
        return l_hostAccOpenAcceptParams;
    }
    
    /**
     * ���E��c�d�q��t�E��������o�^�iGI311�j�L���[(host_condition_reg_voucher)
     */
    public static HostConditionRegVoucherParams getHostConditionRegVoucherRow()
    {
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams = new HostConditionRegVoucherParams();
        
        l_hostConditionRegVoucherParams.setOrderRequestNumber("1");
        l_hostConditionRegVoucherParams.setRequestCode("GI843");
        l_hostConditionRegVoucherParams.setInstitutionCode("0D");
        l_hostConditionRegVoucherParams.setBranchCode("381");
        l_hostConditionRegVoucherParams.setAccountCode("2512246");
        l_hostConditionRegVoucherParams.setAccOpenRequestNumber("1");
        l_hostConditionRegVoucherParams.setSerialNo("0");
        l_hostConditionRegVoucherParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_hostConditionRegVoucherParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_hostConditionRegVoucherParams;
    }
    
    /**
     * �S�ۖ����e�[�u��(security_product)
     * path:  $/WEBBROKER3/09Requirement�Ǘ�/2.9.���o��/�c�a���C�A�E�g
     *         /05.���o��/06.�،��S�ۃ��[��/�S�ۖ����e�[�u��.xls
     */
    public static SecurityProductParams getSecurityProductRow()
    {
        SecurityProductParams l_securityProductParams = new SecurityProductParams();

        l_securityProductParams.setProductId(1006169090018L);
        l_securityProductParams.setInstitutionCode("0D");
        l_securityProductParams.setProductCode("1");
        l_securityProductParams.setProductType(ProductTypeEnum.AIO);
        l_securityProductParams.setEstimationRatio(263.3);
        l_securityProductParams.setFitFlg("0");
        l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
        l_securityProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_securityProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_securityProductParams;
    }
    
    /**
     * �����J�݌����q
     */
    public static ExpAccountOpenParams getExpAccountOpenRow()
    {
        ExpAccountOpenParams l_expAccountOpenParams = new ExpAccountOpenParams();

        l_expAccountOpenParams.setInstitutionCode("0D");
        l_expAccountOpenParams.setAccOpenRequestNumber("2007092899999");
        l_expAccountOpenParams.setInstitutionId(33);
        l_expAccountOpenParams.setBranchId(33381);
        l_expAccountOpenParams.setBranchCode("381");
        l_expAccountOpenParams.setAccountCode("2512246");
        l_expAccountOpenParams.setExAccountFlag(BooleanEnum.TRUE);
        l_expAccountOpenParams.setAccountDiv("0");
        l_expAccountOpenParams.setOrderDiv("0");
        l_expAccountOpenParams.setFamilyName("�����@@�l�Y");
        l_expAccountOpenParams.setGivenName("���Y");
        l_expAccountOpenParams.setFamilyNameAlt1("Ųĳ �۳");
        l_expAccountOpenParams.setGivenNameAlt1("Siro");
        l_expAccountOpenParams.setSex("1");
        l_expAccountOpenParams.setZipCode("100081");
        l_expAccountOpenParams.setAddressLine1("�����s");
        l_expAccountOpenParams.setAddressLine1Kana("SiroSiroSiroSiroSiro");
        l_expAccountOpenParams.setExperienceDivEquity("0");
        l_expAccountOpenParams.setExperienceDivMargin("0");
        l_expAccountOpenParams.setExperienceDivBond("0");
        l_expAccountOpenParams.setExperienceDivWt("0");
        l_expAccountOpenParams.setExperienceDivFundSk("0");
        l_expAccountOpenParams.setExperienceDivFundBd("0");
        l_expAccountOpenParams.setExperienceDivFo("0");
        l_expAccountOpenParams.setExperienceDivFEquity("0");
        l_expAccountOpenParams.setExperienceDivEtc("0");
        l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagEquity(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagMinistock(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagMargin(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagBond(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagFund(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagFo(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagFEquity(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagEtc(BooleanEnum.TRUE);
        l_expAccountOpenParams.setIdConfirmFlag(BooleanEnum.TRUE);
        l_expAccountOpenParams.setInsiderFlag(BooleanEnum.TRUE);
        Timestamp l_tsSystemTimestamp =
        GtlUtils.getTradingSystem().getSystemTimestamp();
        l_expAccountOpenParams.setCreatedTimestamp(l_tsSystemTimestamp);
        l_expAccountOpenParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

        return l_expAccountOpenParams;
    }
    
    /**
     * �S�ەs���ڋq�f�[�^�e�[�u��
     * security_shortage_account
     */
    public static SecurityShortageAccountParams getSecurityShortageAccountRow()
    {
        SecurityShortageAccountParams l_securityShortageAccountParams = new SecurityShortageAccountParams();

        l_securityShortageAccountParams.setAccountId(1006169090018L);
        l_securityShortageAccountParams.setProcDate("20071010");
        l_securityShortageAccountParams.setInstitutionCode("0D");
        l_securityShortageAccountParams.setBranchCode("381");
        l_securityShortageAccountParams.setAccountCode("2512246");
        l_securityShortageAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_securityShortageAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_securityShortageAccountParams;
    }
    
    /**
     * �ݓ������P�ʃe�[�u��
     *
     */
    public static RuitoOrderUnitParams getRuitoOrderUnitRow()
    {
        RuitoOrderUnitParams l_ruitoOrderUnitParams = new RuitoOrderUnitParams();
        l_ruitoOrderUnitParams.setOrderUnitId(3304148080001L);
        l_ruitoOrderUnitParams.setAccountId(333812512203L);
        l_ruitoOrderUnitParams.setSubAccountId(33381251220301L);
        l_ruitoOrderUnitParams.setBranchId(33381L);
        l_ruitoOrderUnitParams.setOrderId(123456789L);
        l_ruitoOrderUnitParams.setOrderType(OrderTypeEnum.RUITO_SELL);
        l_ruitoOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
        l_ruitoOrderUnitParams.setLastOrderActionSerialNo(123456);
        l_ruitoOrderUnitParams.setProductType(ProductTypeEnum.RUITO);
        l_ruitoOrderUnitParams.setQuantity(2632);
        l_ruitoOrderUnitParams.setDeliveryDate(Calendar.getInstance().getTime());
        l_ruitoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_ruitoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_ruitoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
        l_ruitoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_ruitoOrderUnitParams.setBizDate("20071015");
        l_ruitoOrderUnitParams.setProductId(123456);
        l_ruitoOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
        l_ruitoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ruitoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_ruitoOrderUnitParams;
    }
    
    /**
     * �ݓ������e�[�u��
     * ruito_product
     */
    public static RuitoProductParams getRuitoProductRow()
    {
        RuitoProductParams l_ruitoProductParams = new RuitoProductParams();

        l_ruitoProductParams.setProductId(3304148080000L);
        l_ruitoProductParams.setInstitutionCode("0D");
        l_ruitoProductParams.setProductCode("N8080");
        l_ruitoProductParams.setProductIssueCode("0");
        l_ruitoProductParams.setRuitoType(RuitoTypeEnum.MRF);
        l_ruitoProductParams.setInitPurchaseMinQty(100);
        l_ruitoProductParams.setAddtlPurchaseMinQty(100);
        l_ruitoProductParams.setLastUpdater("1001");
        l_ruitoProductParams.setOnlineUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        l_ruitoProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ruitoProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_ruitoProductParams;
    }
    
    /**
     * ���̑��S�����i���S���j�e�[�u��
     * tp_other_temp_restraint
     */
    public static TpOtherTempRestraintParams getTpOtherTempRestraintRow()
    {
        TpOtherTempRestraintParams l_tpOtherTempRestraintParams = new TpOtherTempRestraintParams();
        
        l_tpOtherTempRestraintParams.setAccountId(333812512203L);
        l_tpOtherTempRestraintParams.setRestraintDiv("1");
        l_tpOtherTempRestraintParams.setAmount(100);
        l_tpOtherTempRestraintParams.setDeliveryDate(Calendar.getInstance().getTime());
        l_tpOtherTempRestraintParams.setDeleteFlag(BooleanEnum.FALSE);
        l_tpOtherTempRestraintParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tpOtherTempRestraintParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_tpOtherTempRestraintParams;
    }

    public static TpCashBalanceParams getTpCashBalanceRow()
    {
    	TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
        
    	l_tpCashBalanceParams.setAccountId(333812512203L);
    	l_tpCashBalanceParams.setSubAccountId(33381251220301L);

        return l_tpCashBalanceParams;
    }
    
    public static DocDeliveryManagementParams getDocDeliveryManagementRow()
    {
    	DocDeliveryManagementParams l_docDeliveryManagementParams = new DocDeliveryManagementParams();

    	l_docDeliveryManagementParams.setAccountId(333812512246L);
    	l_docDeliveryManagementParams.setInstitutionCode("0D");
    	l_docDeliveryManagementParams.setBranchCode("381");
    	l_docDeliveryManagementParams.setAccountCode("2512246");
    	l_docDeliveryManagementParams.setDocumentDiv("100");
    	l_docDeliveryManagementParams.setProductCode("0D");
    	l_docDeliveryManagementParams.setDeliveryDate(Calendar.getInstance().getTime());
    	l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.FALSE);
    	l_docDeliveryManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
    	l_docDeliveryManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

    	return l_docDeliveryManagementParams;
    }

    public static BranchMarketPtsDealtCondParams getBranchMarketPtsDealtCondRow()
    {
        BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
            new BranchMarketPtsDealtCondParams();
        l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
        l_branchMarketPtsDealtCondParams.setBranchCode("381");
        l_branchMarketPtsDealtCondParams.setMarketCode("11");

        return l_branchMarketPtsDealtCondParams;
    }
    
    /**
     * �����o���ʒm�L���[�e�[�u��(host_equity_order_exec_notify)
     */
    public static HostEquityOrderExecNotifyParams getHostEquityOrderExecNotifyRow()
    {
        HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();
        l_hostEquityOrderExecNotifyParams.setRequestCode("AI811");
        l_hostEquityOrderExecNotifyParams.setInstitutionCode("0D");
        l_hostEquityOrderExecNotifyParams.setBranchCode("381");
        l_hostEquityOrderExecNotifyParams.setAccountCode("2512246");
        l_hostEquityOrderExecNotifyParams.setTraderCode("1234");
        l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("1");
        l_hostEquityOrderExecNotifyParams.setExecQuantity(1000);
        l_hostEquityOrderExecNotifyParams.setExecPrice(100);
        l_hostEquityOrderExecNotifyParams.setExecTimestamp(Calendar.getInstance().getTime());
        l_hostEquityOrderExecNotifyParams.setDealedType("0");
        l_hostEquityOrderExecNotifyParams.setStatus("0");
        l_hostEquityOrderExecNotifyParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_hostEquityOrderExecNotifyParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_hostEquityOrderExecNotifyParams.setVirtualServerNumberMarket("1");
        l_hostEquityOrderExecNotifyParams.setNoticeType("02");
        l_hostEquityOrderExecNotifyParams.setNoticeNumber(100);
        l_hostEquityOrderExecNotifyParams.setExecNumber(100);
        l_hostEquityOrderExecNotifyParams.setLastUpdater("A110");

        return l_hostEquityOrderExecNotifyParams;
    }

    /**
     * �iPTS�j�o���I���e�[�u��(pts_orderexecution_end)
     */
    public static PtsOrderexecutionEndParams getPtsOrderexecutionEndRow()
    {
        PtsOrderexecutionEndParams l_ptsOrderexecutionEndParams = new PtsOrderexecutionEndParams();
        l_ptsOrderexecutionEndParams.setInstitutionCode("0D");
        l_ptsOrderexecutionEndParams.setMarketCode("1");
        l_ptsOrderexecutionEndParams.setStatus("1");
        l_ptsOrderexecutionEndParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ptsOrderexecutionEndParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_ptsOrderexecutionEndParams;
    }
    
    /**
     * �y�敨OP�\�񒍕��P�ʃe�[�u���z
     */
    public static RsvIfoOrderUnitParams getRsvIfoOrderUnitRow()
    {
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.setOrderUnitId(1001);
        l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
        l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
        l_rsvIfoOrderUnitParams.setBranchId(33381L);
        l_rsvIfoOrderUnitParams.setOrderId(1001);
        l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
        l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
        l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
        l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
        l_rsvIfoOrderUnitParams.setQuantity(60000);
        l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
        l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_rsvIfoOrderUnitParams.setBizDate("20080326");
        l_rsvIfoOrderUnitParams.setProductId(1006149081018L);
        l_rsvIfoOrderUnitParams.setParentOrderId(1001);
        l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
        l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_rsvIfoOrderUnitParams;
    }
    
    /**
     * �m��g�����U�N�V�����i�����ڋq���薾�ׁj
     * fixed_fin_transaction
     */
    public static FixedFinTransactionParams getFixedFinTransactionRow()
    {
        FixedFinTransactionParams l_fixedFinTransactionParams = new FixedFinTransactionParams();
        //fixed_fin_transaction_id
        l_fixedFinTransactionParams.setFixedFinTransactionId(111L);
        
        //�����h�c              account_id              NOT NULL
        l_fixedFinTransactionParams.setAccountId(333812512203L);
        
        //�⏕�����h�c           sub_account_id         NOT NULL
        l_fixedFinTransactionParams.setSubAccountId(33381251220301L);
        
        //�����h�c               product_id            NOT NULL
        l_fixedFinTransactionParams.setProductId(123456);
        //�g�����U�N�V�����^�C�v  fin_transaction_type  NOT NULL

        l_fixedFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_EQUITY_BUY);
        
        //�g�����U�N�V�����J�e�S��  fin_transaction_categ       NOT NULL
        l_fixedFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
        
        //�g�����U�N�V������������  fin_transaction_timestamp   NOT NULL
        l_fixedFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
        
        //�ŋ敪               tax_type            NOT NULL
        l_fixedFinTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
        
        //��n��               delivery_date           NOT NULL
        l_fixedFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
        
        //��n���          net_amount          NOT NULL
        l_fixedFinTransactionParams.setNetAmount(1.0);
        
        //�����^�C�v         product_type            NOT NULL
        l_fixedFinTransactionParams.setProductType(ProductTypeEnum.EQUITY);

        //���P��          price               NULL
        //��萔��          quantity            NOT NULL
        l_fixedFinTransactionParams.setQuantity(1000);

        //�ϑ��萔��         commission_fee          NOT NULL
        l_fixedFinTransactionParams.setCommissionFee(1000);
        
        //�ϑ��萔�������      commission_fee_tax          NOT NULL
        l_fixedFinTransactionParams.setCommissionFeeTax(1000);
        
        //�����̎�n���z           imported_paid_value     NOT NULL
        l_fixedFinTransactionParams.setImportedPaidValue(1000);
        
        //���萔��          imported_setup_fee      NOT NULL
        l_fixedFinTransactionParams.setImportedSetupFee(1000);
        
        //���萔�������           imported_setup_fee_tax      NOT NULL
        l_fixedFinTransactionParams.setImportedSetupFeeTax(1000);
        
        //���`������         imported_name_transfer_fee 
        l_fixedFinTransactionParams.setImportedNameTransferFee(100);
        
        //���`�����������      imported_name_transfer_fee_tax
        l_fixedFinTransactionParams.setImportedNameTransferFeeTax(100);
        
        //���n�v���z         capital_gain            NOT NULL
        l_fixedFinTransactionParams.setCapitalGain(1000);
        
        //���n�v�Ŋz         capital_gain_tax        NOT NULL
        l_fixedFinTransactionParams.setCapitalGainTax(1000);
        
        //�����h�c          contract_id         NULL
        l_fixedFinTransactionParams.setFixedContractId(12347);
        
        //�Ǘ���               imported_management_fee     NOT NULL
        l_fixedFinTransactionParams.setImportedManagementFee(422);
        
        //�Ǘ�������            imported_management_fee_tax NOT NULL
        l_fixedFinTransactionParams.setImportedManagementFeeTax(533);
        
        //������               imported_interest_fee       NOT NULL
        l_fixedFinTransactionParams.setImportedInterestFee(1000);
        
        //�����������            imported_interest_fee_tax   NOT NULL
        l_fixedFinTransactionParams.setImportedInterestFeeTax(1000);
        
        //�t����               imported_pay_interest_fee  
        l_fixedFinTransactionParams.setImportedPayInterestFee(100);
        
        //�t���������            imported_pay_interest_fee_tax  
        l_fixedFinTransactionParams.setImportedPayInterestFeeTax(100);
        
        //�݊���               imported_loan_equity_fee   
        l_fixedFinTransactionParams.setImportedLoanEquityFee(100);
        //���̑�               imported_other         
        l_fixedFinTransactionParams.setImportedOther(100);        
        
        //�폜�t���O         delete_flag         NOT NULL
        l_fixedFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
        
        //�쐬���t          created_timestamp       NOT NULL
        l_fixedFinTransactionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
        
        //�X�V���t          last_updated_timestamp      NOT NULL
        l_fixedFinTransactionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
        
        return l_fixedFinTransactionParams;
    }
    
    public static ForcedSettleOrderInqParams getForcedSettleOrderInqRow()
    {
        ForcedSettleOrderInqParams l_forcedSettleOrderInqParams = new ForcedSettleOrderInqParams();
        l_forcedSettleOrderInqParams.setForcedSettleOrderInqId(1234L);
        l_forcedSettleOrderInqParams.setAccountId(333812512246L);
        l_forcedSettleOrderInqParams.setSubAccountId(33381251220301L);
        l_forcedSettleOrderInqParams.setBranchId(33381L);
        l_forcedSettleOrderInqParams.setOrderType(OrderTypeEnum.BOND_BUY);
        l_forcedSettleOrderInqParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
        l_forcedSettleOrderInqParams.setQuantity(20.0);
        l_forcedSettleOrderInqParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
        l_forcedSettleOrderInqParams.setBizDate("20080215");
        l_forcedSettleOrderInqParams.setProductId(3304148080000L);
        l_forcedSettleOrderInqParams.setContractId(2134566345L);
        l_forcedSettleOrderInqParams.setOrgContractQuantity(123456.123);
        l_forcedSettleOrderInqParams.setContractQuantity(1200);
        l_forcedSettleOrderInqParams.setOriginalContractPrice(654321.321);
        l_forcedSettleOrderInqParams.setContractPrice(54321.4321);
        l_forcedSettleOrderInqParams.setContractType(1);
        l_forcedSettleOrderInqParams.setOpenDate(GtlUtils.getSystemTimestamp());
        l_forcedSettleOrderInqParams.setCloseDate(GtlUtils.getSystemTimestamp());
        l_forcedSettleOrderInqParams.setAccountCode("123456");
        l_forcedSettleOrderInqParams.setProductCode("0");
        
        return l_forcedSettleOrderInqParams;
    }
    
    /**
     * ���b�N���̌��ʏڍ�
     * ifo_locked_contract_details
     */
    public static IfoLockedContractDetailsParams getIfoLockedContractDetailsRow()
    {
        IfoLockedContractDetailsParams l_ifoLockedContractDetailsParams =
            new IfoLockedContractDetailsParams();
        
        l_ifoLockedContractDetailsParams.setContractId(1001);
        l_ifoLockedContractDetailsParams.setAccountId(333812512203L);
        l_ifoLockedContractDetailsParams.setSubAccountId(33381251220301L);
        l_ifoLockedContractDetailsParams.setLockedQuantity(100);
        l_ifoLockedContractDetailsParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoLockedContractDetailsParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        return l_ifoLockedContractDetailsParams;
    }

    public static OtherOrgInfoAdminParams getOtherOrgInfoAdminRow()
    {
        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
        l_otherOrgInfoAdminParams.setSrvDiv("1");
        l_otherOrgInfoAdminParams.setInstitutionCode("0D");
        l_otherOrgInfoAdminParams.setBranchCode("456");
        l_otherOrgInfoAdminParams.setAccountCode("111");
        l_otherOrgInfoAdminParams.setStatus("0");
        l_otherOrgInfoAdminParams.setSequenceNumber(1);
        l_otherOrgInfoAdminParams.setId("1001");
        l_otherOrgInfoAdminParams.setPassword("123");
        l_otherOrgInfoAdminParams.setLastUpdater("1");
        l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_otherOrgInfoAdminParams;
    }
    
    /**
     * ifo_order_execution
     */
    public static IfoOrderExecutionParams getIfoOrderExecutionRow()
    {
        IfoOrderExecutionParams l_ifoOrderExecutionParams = new IfoOrderExecutionParams();
        l_ifoOrderExecutionParams.setOrderExecutionId(123456);
        l_ifoOrderExecutionParams.setAccountId(333812512203L);
        l_ifoOrderExecutionParams.setSubAccountId(33381251220301L);
        l_ifoOrderExecutionParams.setBranchId(33381L);
        l_ifoOrderExecutionParams.setOrderId(123456789L);
        l_ifoOrderExecutionParams.setOrderUnitId(1001);
        l_ifoOrderExecutionParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
        l_ifoOrderExecutionParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderExecutionParams.setExecSerialNo(1);
        l_ifoOrderExecutionParams.setExecQuantity(0.0D);
        l_ifoOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());
        l_ifoOrderExecutionParams.setDeleteFlag(BooleanEnum.FALSE);
        l_ifoOrderExecutionParams.setBizDate("20070117");
        l_ifoOrderExecutionParams.setProductId(3304148080000L);
        l_ifoOrderExecutionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ifoOrderExecutionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_ifoOrderExecutionParams.setExecPrice(0.1D);
        l_ifoOrderExecutionParams.setDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
        return l_ifoOrderExecutionParams;
    }
    
    /**
     * rsv_ifo_closing_contract_spec
     */
    public static RsvIfoClosingContractSpecParams getRsvIfoClosingContractSpecRow()
    {
        RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams = new RsvIfoClosingContractSpecParams();
        l_rsvIfoClosingContractSpecParams.setAccountId(333812512203L);
        l_rsvIfoClosingContractSpecParams.setSubAccountId(33381251220301L);
        l_rsvIfoClosingContractSpecParams.setOrderId(123456789L);
        l_rsvIfoClosingContractSpecParams.setContractId(1001);
        l_rsvIfoClosingContractSpecParams.setClosingSerialNo(1);
        l_rsvIfoClosingContractSpecParams.setQuantity(10.0D);
        l_rsvIfoClosingContractSpecParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_rsvIfoClosingContractSpecParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_rsvIfoClosingContractSpecParams;
    } 

    /**
     * rsv_ifo_order_action
     */
    public static RsvIfoOrderActionParams getRsvIfoOrderActionRow()
    {
        RsvIfoOrderActionParams l_rsvIfoOrderActionParams = new RsvIfoOrderActionParams();
        l_rsvIfoOrderActionParams.setAccountId(333812512203L);
        l_rsvIfoOrderActionParams.setSubAccountId(33381251220301L);
        l_rsvIfoOrderActionParams.setOrderId(123456789L);
        l_rsvIfoOrderActionParams.setQuantity(1000L);
        l_rsvIfoOrderActionParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_rsvIfoOrderActionParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        l_rsvIfoOrderActionParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_rsvIfoOrderActionParams.setOrderActionSerialNo(1);
        l_rsvIfoOrderActionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_rsvIfoOrderActionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_rsvIfoOrderActionParams;
    }

    /**
     * ifo_fin_transaction
     */
    public static IfoFinTransactionParams getIfoFinTransactionRow()
    {
        IfoFinTransactionParams l_ifoFinTransactionParams = new IfoFinTransactionParams();
        l_ifoFinTransactionParams.setFinTransactionId(1L);
        l_ifoFinTransactionParams.setSubAccountId(33381251220301L);
        l_ifoFinTransactionParams.setAccountId(333812512203L);
        l_ifoFinTransactionParams.setProductId(1006160060005L);
        l_ifoFinTransactionParams.setFinTransactionType(FinTransactionType.ASSET_IN);
        l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.ASSET_IN_OUT);
        l_ifoFinTransactionParams.setFinTransactionTimestamp(Calendar.getInstance().getTime());
        l_ifoFinTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
        l_ifoFinTransactionParams.setDeliveryDate(Calendar.getInstance().getTime());
        l_ifoFinTransactionParams.setNetAmount(5.0d);
        l_ifoFinTransactionParams.setProductType(ProductTypeEnum.IFO);
        l_ifoFinTransactionParams.setQuantity(6.0d);
        l_ifoFinTransactionParams.setCommissionFee(7.0d);
        l_ifoFinTransactionParams.setImportedPaidValue(8.0d);
        l_ifoFinTransactionParams.setImportedSetupFee(9.0d);
        l_ifoFinTransactionParams.setImportedSetupFeeTax(10.0d);
        l_ifoFinTransactionParams.setCapitalGain(11.0d);
        l_ifoFinTransactionParams.setCapitalGainTax(12.0d);
        l_ifoFinTransactionParams.setImportedManagementFee(13.0d);
        l_ifoFinTransactionParams.setImportedManagementFeeTax(14.0d);
        l_ifoFinTransactionParams.setImportedInterestFee(15.0d);
        l_ifoFinTransactionParams.setImportedInterestFeeTax(16.0d);
        l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
        l_ifoFinTransactionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ifoFinTransactionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_ifoFinTransactionParams;
    }

    public static MutualFundProductCategoryParams getMutualFundProductCategoryRow()
    {
        MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
            new MutualFundProductCategoryParams();
        l_mutualFundProductCategoryParams.setCategoryCode("0");
        l_mutualFundProductCategoryParams.setCategoryName("012");
        l_mutualFundProductCategoryParams.setInstitutionCode("0D");
        l_mutualFundProductCategoryParams.setParentCategoryCode("01");
        l_mutualFundProductCategoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        l_mutualFundProductCategoryParams.setLastUpdater("132");
        l_mutualFundProductCategoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_mutualFundProductCategoryParams;
    }

    /**
     * �d�q���V�X�e����Е��X�ʃv���t�@@�����X
     * bato_inst_branch_pref
     */
    public static BatoInstBranchPrefParams getBatoInstBranchPrefRow()
    {
        BatoInstBranchPrefParams l_batoInstBranchPrefParams =
            new BatoInstBranchPrefParams();
        
        l_batoInstBranchPrefParams.setInstitutionCode("0D");
        l_batoInstBranchPrefParams.setBranchCode("381");
        l_batoInstBranchPrefParams.setUrl("https//www.sinocom.jp/denshibato");
        l_batoInstBranchPrefParams.setSoapUrl("https//www.com.jp/denshibato");
        l_batoInstBranchPrefParams.setHashField1("a1b1c1");
        l_batoInstBranchPrefParams.setHashField2("d1f1g1");
        l_batoInstBranchPrefParams.setSystemFailureFlag("0");
        l_batoInstBranchPrefParams.setLastUpdater("bixian");
        l_batoInstBranchPrefParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_batoInstBranchPrefParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_batoInstBranchPrefParams;
    }
    /**
     * SLE_RCVD_Q
     */
    public static SleRcvdQParams getSleRcvdQRow()
    {
        SleRcvdQParams l_sleRcvdQParams =
            new SleRcvdQParams();

        l_sleRcvdQParams.setQueueId(1000);
        l_sleRcvdQParams.setXblocksProductType(ProductTypeEnum.CASH);
        l_sleRcvdQParams.setAccountId(10000);
        l_sleRcvdQParams.setOpType(SleSendqOpTypeEnum.NEW_ORDER);
        l_sleRcvdQParams.setStatus(SleRcvdqProcStatusEnum.NO_DEFINE_2);

        return l_sleRcvdQParams;
    }

    public static InformCtrlItemMasterParams getInformCtrlItemMasterRow()
    {
        InformCtrlItemMasterParams informCtrlItemMasterParams = new InformCtrlItemMasterParams();
        informCtrlItemMasterParams.setBranchCode("000");
        informCtrlItemMasterParams.setInstitutionCode("123");
        informCtrlItemMasterParams.setInformDiv("12");
        informCtrlItemMasterParams.setItemSymbolName("123");
        informCtrlItemMasterParams.setNecessaryFlag(1);
        return informCtrlItemMasterParams;
    }

    public static VariousInformParams getVariousInformRow()
    {
        VariousInformParams variousInformParams = new VariousInformParams();
        variousInformParams.setBranchCode("000");
        variousInformParams.setInstitutionCode("123");
        variousInformParams.setInformDiv("12");
        variousInformParams.setRequestNumber("123");
        variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        return variousInformParams;
    }
    
    /**
     * �d�q���V�X�e�����X���i�ʃv���t�@@�����X
     * bato_branch_product_pref
     */
    public static BatoBranchProductPrefParams getBatoBranchProductPrefRow()
    {
        BatoBranchProductPrefParams l_batoBranchProductPrefParams =
            new BatoBranchProductPrefParams();
        
        l_batoBranchProductPrefParams.setInstitutionCode("0D");
        l_batoBranchProductPrefParams.setBranchCode("381");
        l_batoBranchProductPrefParams.setProductCode("123");
        l_batoBranchProductPrefParams.setOrderAtSystemFailureFlag("1");
        l_batoBranchProductPrefParams.setOperatorInputFlag("1");
        l_batoBranchProductPrefParams.setLastUpdater("bixian");
        l_batoBranchProductPrefParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_batoBranchProductPrefParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_batoBranchProductPrefParams;
    } 
    
    /**
     * �d�q���V�X�e���h�L�������g��ʊǗ��e�[�u��
     * bato_doctype_management
     */
    public static BatoDoctypeManagementParams getBatoDoctypeManagementRow()
    {
        BatoDoctypeManagementParams l_batoDoctypeManagementParams =
            new BatoDoctypeManagementParams();
        
        l_batoDoctypeManagementParams.setInstitutionCode("0D");
        l_batoDoctypeManagementParams.setTypeCode("111");
        l_batoDoctypeManagementParams.setProductCode("123");
        l_batoDoctypeManagementParams.setLastUpdater("bixian");
        l_batoDoctypeManagementParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_batoDoctypeManagementParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_batoDoctypeManagementParams;
    }
    
    /**
     * �d�q���V�X�e���@@�\�ʃv���t�@@�����X
     * bato_function_pref
     */
    public static BatoFunctionPrefParams getBatoFunctionPrefRow()
    {
        BatoFunctionPrefParams l_batoFunctionPrefParams =
            new BatoFunctionPrefParams();

        l_batoFunctionPrefParams.setFunctionDiv("03");
        l_batoFunctionPrefParams.setTargetNamespaceName("soso");
        l_batoFunctionPrefParams.setServiceName("sinocom");
        l_batoFunctionPrefParams.setPortTypeName("port");
        l_batoFunctionPrefParams.setOperationName("aaaa");
        l_batoFunctionPrefParams.setParameterList("comp_code:br_code:cust_code:type_code:fund_code:hashString");
        l_batoFunctionPrefParams.setLastUpdater("bixian");
        l_batoFunctionPrefParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_batoFunctionPrefParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_batoFunctionPrefParams;
    }
    
    public static MfFixedBuyingChangeParams getMfFixedBuyingChangeRow()
    {
        MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
            new MfFixedBuyingChangeParams();
        l_mfFixedBuyingChangeParams.setInstitutionCode("0D");
        l_mfFixedBuyingChangeParams.setBranchCode("381");
        l_mfFixedBuyingChangeParams.setAccountCode("111111");
        l_mfFixedBuyingChangeParams.setProductCode("1");
        l_mfFixedBuyingChangeParams.setValidStartDate(GtlUtils.getSystemTimestamp());
        l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(0);
        l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(0);
        l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(0);
        l_mfFixedBuyingChangeParams.setChangeDiv("1");
        l_mfFixedBuyingChangeParams.setStatus("1");
        l_mfFixedBuyingChangeParams.setBizDate(GtlUtils.getTradingSystem().getBizDate());
        l_mfFixedBuyingChangeParams.setOrderTimestamp(GtlUtils.getSystemTimestamp());
        l_mfFixedBuyingChangeParams.setOrderChanel("1");
        l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.TRUE);
        l_mfFixedBuyingChangeParams.setLastUpdater("1");
        l_mfFixedBuyingChangeParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_mfFixedBuyingChangeParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_mfFixedBuyingChangeParams;
    }
    /**
     * ���ʋ敪�Ǘ��e�[�u��
     * doc_div_management
     */
    
    public static DocDivManagementParams getDocDivManagementRow()
    {
        DocDivManagementParams l_docDivManagementParams =
            new DocDivManagementParams();

        l_docDivManagementParams.setInstitutionCode("0D");
        l_docDivManagementParams.setBranchCode("381");
        l_docDivManagementParams.setDocumentDiv("100");
        l_docDivManagementParams.setDocumentCheckDiv("4");
        l_docDivManagementParams.setDocumentNumber("0");
        l_docDivManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_docDivManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_docDivManagementParams.setDocumentCategory("1");
        return l_docDivManagementParams;
    }
    /**
     * �d�q�������R�[�h�Ǘ��e�[�u��
     * bato_product_management
     */
    public static BatoProductManagementParams getBatoProductManagementRow()
    {
        BatoProductManagementParams l_batoProductManagementParams =
            new BatoProductManagementParams();
        l_batoProductManagementParams.setInstitutionCode("0D");
        l_batoProductManagementParams.setBranchCode("381");
        l_batoProductManagementParams.setDocumentDiv("100");
        l_batoProductManagementParams.setBatoProductCode("0000000");
        l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_batoProductManagementParams.setValidFlag("0");
        return l_batoProductManagementParams;
    }
    /**
     * �O���V�X�e��SOAP�ڑ��v���t�@@�����X�i���b�Z�[�W�`���j
     * soap_connect_pref_msg
     */
    public static SoapConnectPrefMsgParams getSoapConnectPrefMsgRow()
    {
        SoapConnectPrefMsgParams l_soapConnectPrefMsgParams =
            new SoapConnectPrefMsgParams();
        l_soapConnectPrefMsgParams.setBranchId(33381);
        l_soapConnectPrefMsgParams.setConnectDiv("01");
        l_soapConnectPrefMsgParams.setEndpointName("url");
        l_soapConnectPrefMsgParams.setTargetNamespaceName("0");
        l_soapConnectPrefMsgParams.setPrefix("0");
        l_soapConnectPrefMsgParams.setOperationName("0");
        l_soapConnectPrefMsgParams.setCharset("0");
        l_soapConnectPrefMsgParams.setParameterList("1");
        l_soapConnectPrefMsgParams.setResponseName("0");
        l_soapConnectPrefMsgParams.setResponseParamList("1");
        l_soapConnectPrefMsgParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_soapConnectPrefMsgParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_soapConnectPrefMsgParams;
    }
    
    /**
     * AP���菈���Ǘ�Row���쐬.
     */
    public static ApManagementParams getApManagementRow()
    {

        ApManagementParams l_apManagementParams = new ApManagementParams();

        l_apManagementParams.setPtype("1");
        l_apManagementParams.setRequestCode("1");
        l_apManagementParams.setApName("0");
        l_apManagementParams.setOrderRequestNumberDiv("0");
        l_apManagementParams.setThreadNumberDiv("0");
        l_apManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_apManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_apManagementParams;
    }
    /**
     * �敨OP�����}�X�^�e�[�u��
     * ifo_delivery_month_master.
     */
    public static IfoDeliveryMonthMasterParams getIfoDeliveryMonthMasterRow()
    {
        IfoDeliveryMonthMasterParams l_IfoDeliveryMonthMasterParams =
            new IfoDeliveryMonthMasterParams();
        l_IfoDeliveryMonthMasterParams.setUnderlyingProductCode("0005");
        l_IfoDeliveryMonthMasterParams.setFutureOptionDiv("1");
        l_IfoDeliveryMonthMasterParams.setDeliveryMonth("200807");
        l_IfoDeliveryMonthMasterParams.setIndexId(123L);

        return l_IfoDeliveryMonthMasterParams;
    }
    /**
     * �敨OP��胁�[�����M�e�[�u��
     */
    public static IfoOrderExecSendMailParams getIfoOrderExecSendMailRow()
    {

        IfoOrderExecSendMailParams l_ifoOrderExecSendMailParams = new IfoOrderExecSendMailParams();

        l_ifoOrderExecSendMailParams.setInstitutionCode("0D");
        l_ifoOrderExecSendMailParams.setBranchCode("381");
        l_ifoOrderExecSendMailParams.setAccountCode("2512246");
        l_ifoOrderExecSendMailParams.setFutureOptionDiv("0");
        l_ifoOrderExecSendMailParams.setOrderRequestNumber("000003006");
        l_ifoOrderExecSendMailParams.setOrderExecStatus("0");
        l_ifoOrderExecSendMailParams.setOrderActionId(123456);

        return l_ifoOrderExecSendMailParams;
    }
    /**
     * �敨OP�����ʒm�L���[�e�[�u��
     */
    public static HostFotypeOrderReceiptParams getHostFotypeOrderReceiptRow()
    {

        HostFotypeOrderReceiptParams l_hostFotypeOrderReceiptParams = new HostFotypeOrderReceiptParams();

        l_hostFotypeOrderReceiptParams.setRequestCode("EI821");
        l_hostFotypeOrderReceiptParams.setInstitutionCode("0D");
        l_hostFotypeOrderReceiptParams.setBranchCode("381");
        l_hostFotypeOrderReceiptParams.setAccountCode("2512246");
        l_hostFotypeOrderReceiptParams.setOrderRequestNumber("000003006");

        return l_hostFotypeOrderReceiptParams;
    }
    /**
     * �O���C�O�s��J�����_�[
     */
    public static FeqCalendarParams getFeqCalendarRow()
    {
        FeqCalendarParams l_feqCalendarParams = new FeqCalendarParams();
        l_feqCalendarParams.setInstitutionCode("0D");
        l_feqCalendarParams.setMarketCode("SP");
        l_feqCalendarParams.setBizDate(Calendar.getInstance().getTime());
        l_feqCalendarParams.setBizDateType("0");
        l_feqCalendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_feqCalendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_feqCalendarParams;
    }

    /**
     * ���M���������e�[�u��
     */
    public static MutualFundOrderActionParams getMutualFundOrderActionRow()
    {
        MutualFundOrderActionParams l_mutualFundOrderActionParams =
            new MutualFundOrderActionParams();
        l_mutualFundOrderActionParams.setOrderActionId(123456789);
        l_mutualFundOrderActionParams.setAccountId(12345);
        l_mutualFundOrderActionParams.setSubAccountId(121212);
        l_mutualFundOrderActionParams.setOrderId(120000);
        l_mutualFundOrderActionParams.setOrderUnitId(1234);
        l_mutualFundOrderActionParams.setMarketId(123456);
        l_mutualFundOrderActionParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
        l_mutualFundOrderActionParams.setOrderEventType(OrderEventTypeEnum.NEW);
        l_mutualFundOrderActionParams.setQuantity(10002);
        l_mutualFundOrderActionParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_mutualFundOrderActionParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_mutualFundOrderActionParams.setOrderActionSerialNo(1250);
        l_mutualFundOrderActionParams.setProductId(123);
        l_mutualFundOrderActionParams.setQuantityType(QuantityTypeEnum.QUANTITY);
        l_mutualFundOrderActionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_mutualFundOrderActionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_mutualFundOrderActionParams;
    }
    /**
     * �萔���R�[�X�R�[�h�ϊ��}�X�^�e�[�u��
     */
    public static CommCodeChgMstParams getCommCodeChgMstRow()
    {
        CommCodeChgMstParams l_commCodeChgMstParams = new CommCodeChgMstParams();
        l_commCodeChgMstParams.setBranchId(3301);
        l_commCodeChgMstParams.setCommProductCode("10");
        l_commCodeChgMstParams.setCommissionNo("22");
        l_commCodeChgMstParams.setAppliStartDate("20040716");
        l_commCodeChgMstParams.setCommissionCourseDiv("02");
        l_commCodeChgMstParams.setInitialSetDiv("0");
        l_commCodeChgMstParams.setAccountChargeRatio(0.01);
        l_commCodeChgMstParams.setLastUpdater("1001");
        l_commCodeChgMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_commCodeChgMstParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_commCodeChgMstParams;
    }
    /**
     * �ϑ��萔���R�[�X�ύX�\���e�[�u��
     */
    public static CommissionCourseRegistParams getCommissionCourseRegistRow()
    {
        CommissionCourseRegistParams l_commissionCourseRegistParams =
            new CommissionCourseRegistParams();
        l_commissionCourseRegistParams.setCommissionCourseRegistId(1001);
        l_commissionCourseRegistParams.setInstitutionCode("0D");
        l_commissionCourseRegistParams.setBranchId(63101);
        l_commissionCourseRegistParams.setAccountId(631012512203L);
        l_commissionCourseRegistParams.setCommProductCode("10");
        l_commissionCourseRegistParams.setAppliStartDatetime(
            WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        l_commissionCourseRegistParams.setAppliEndDatetime(
            WEB3DateUtility.getDate("20040725","yyyyMMdd"));
        l_commissionCourseRegistParams.setCommissionCourseDiv("02");
        l_commissionCourseRegistParams.setRegistTimestamp(Calendar.getInstance().getTime());
        l_commissionCourseRegistParams.setRegistEndTimestamp(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
        l_commissionCourseRegistParams.setDownloadFlag(BooleanEnum.FALSE);
        l_commissionCourseRegistParams.setDeleteFlag(BooleanEnum.FALSE);
        l_commissionCourseRegistParams.setLastUpdater("1001");
        l_commissionCourseRegistParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_commissionCourseRegistParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_commissionCourseRegistParams;
    }

    /**
     * �ϑ��萔���R�[�X�}�X�^
     * commission_course_master
     */
    public static CommissionCourseMasterParams getCommissionCourseMasterRow()
    {
        CommissionCourseMasterParams l_commissionCourseMasterParams = new CommissionCourseMasterParams();
        l_commissionCourseMasterParams.setInstitutionCode("0D");
        l_commissionCourseMasterParams.setCommProductCode("10");
        l_commissionCourseMasterParams.setCommissionCourseDiv("02");
        l_commissionCourseMasterParams.setStandardName("1");
        l_commissionCourseMasterParams.setRegistEndDaySpec("00");
        l_commissionCourseMasterParams.setRegistEndTime("000000");
        l_commissionCourseMasterParams.setAppliStartDateDiv("1");
        l_commissionCourseMasterParams.setAppliStartEndTime("000000");
        l_commissionCourseMasterParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_commissionCourseMasterParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_commissionCourseMasterParams.setCommissionDiv("0");
        return l_commissionCourseMasterParams;
    }

    /**
     * �،���Ѓv���t�@@�����X�e�[�u��
     * institution_preferences
     */
    public static InstitutionPreferencesParams getInstitutionPreferencesRow()
    {
        InstitutionPreferencesParams l_institutionPreferencesParams = new InstitutionPreferencesParams();
        l_institutionPreferencesParams.setInstitutionId(33);
        l_institutionPreferencesParams.setName("accountinfo.commision.div.check");
        l_institutionPreferencesParams.setNameSerialNo(104533);
        l_institutionPreferencesParams.setValue("1");
        l_institutionPreferencesParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_institutionPreferencesParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_institutionPreferencesParams;
    }

    /**
     * ��Е��X���i�e�[�u��
     * inst_branch_product
     */
    public static InstBranchProductParams getInstBranchProductRow()
    {
        InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
        l_instBranchProductParams.setBranchId(33381L);
        l_instBranchProductParams.setCommissionProductCode("50");
        l_instBranchProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_instBranchProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_instBranchProductParams;
    }
    
    /**
     * ���O�C������
     * login_history
     */
    public static LoginHistoryParams getLoginHistoryRow()
    {
        LoginHistoryParams l_loginHistoryParams = new LoginHistoryParams();
        l_loginHistoryParams.setLoginHistoryId(1001);
        l_loginHistoryParams.setInstitutionCode("0D");
        l_loginHistoryParams.setBranchCode("102");
        l_loginHistoryParams.setAccountCode("102050");
        l_loginHistoryParams.setDiscriminationCode("0");
        l_loginHistoryParams.setAccountId(31102102050L);
        l_loginHistoryParams.setIpAddress("192.168.255.21");
        l_loginHistoryParams.setOrderRootDiv("2");
        l_loginHistoryParams.setOrderChannel("1");
        l_loginHistoryParams.setLoginTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        l_loginHistoryParams.setLoginFailure("1");
        l_loginHistoryParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_loginHistoryParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_loginHistoryParams;
    }

    /**
     * ���O�C���ߋ�����
     * login_history_past
     */
    public static LoginHistoryPastParams getLoginHistoryPastRow()
    {
        LoginHistoryPastParams l_loginHistoryPastParams = new LoginHistoryPastParams();
        l_loginHistoryPastParams.setLoginHistoryId(1001);
        l_loginHistoryPastParams.setInstitutionCode("0D");
        l_loginHistoryPastParams.setBranchCode("102");
        l_loginHistoryPastParams.setAccountCode("102050");
        l_loginHistoryPastParams.setDiscriminationCode("0");
        l_loginHistoryPastParams.setAccountId(31102102050L);
        l_loginHistoryPastParams.setIpAddress("192.168.255.21");
        l_loginHistoryPastParams.setOrderRootDiv("2");
        l_loginHistoryPastParams.setOrderChannel("1");
        l_loginHistoryPastParams.setLoginTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        l_loginHistoryPastParams.setLoginFailure("1");
        l_loginHistoryPastParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_loginHistoryPastParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_loginHistoryPastParams;
    }

    public static GftAccountOpenStatusParams getGftAccountOpenStatusRow()
    {
        GftAccountOpenStatusParams l_params = new GftAccountOpenStatusParams();
        //�،���ЃR�[�h    institution_code    VARCHAR2    3    NotNull
        l_params.setInstitutionCode("0D");
        //���X�R�[�h    branch_code    VARCHAR2    3    NotNull
        l_params.setBranchCode("624");
        //�ڋq�R�[�h    account_code    VARCHAR2    7    NotNull
        l_params.setAccountCode("123456");
        //���ʃR�[�h    order_request_number    VARCHAR2    9    NotNull
        l_params.setOrderRequestNumber("101");
        //���O�i���j    last_name    VARCHAR2    50    Null
        //���O�i���j    first_name    VARCHAR2    50    Null
        //���[���A�h���X    mail_address    VARCHAR2    50    Null
        //���O�C��ID    login_id    VARCHAR2    18    Null
        //�����p�X���[�h    password    VARCHAR2    8    Null
        //�����ԍ��i1���ʉ݃R�[�X�j    fx_account_code_01    VARCHAR2    6    Null
        //�����ԍ��i10���ʉ݃R�[�X�j    fx_account_code_10    VARCHAR2    6    Null
        //�����J�ݏ󋵋敪    account_open_status_div    VARCHAR2    2    NotNull
        l_params.setAccountOpenStatusDiv("1");
        //����M�敪    send_rcv_div    VARCHAR2    2    NotNull
        l_params.setSendRcvDiv("0");
        //��t���ʃR�[�h    result_code    VARCHAR2    8    Null
        //�G���[���R�R�[�h    error_reason_code    VARCHAR2    4    Null
        //�X�V�҃R�[�h    last_updater    VARCHAR2    20    Null
        //�쐬���t    created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //�X�V���t    last_updated_timestamp    DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //������敪    agreement_div    VARCHAR2    1    NotNull
        l_params.setAgreementDiv("1");
        //��t���ʃR�[�h�iSOAP�j    result_code_soap    VARCHAR2    4    Null
        //�e�w�V�X�e���R�[�h    fx_system_code    VARCHAR2    2    Null
        return l_params;
    }
    
    /*
     * �O���o���I���e�[�u��
     */
    public static FeqOrderexecutionEndParams getFeqOrderexecutionEndRow()
    {
        FeqOrderexecutionEndParams l_params = new FeqOrderexecutionEndParams();
        
        //�،���ЃR�[�h
        l_params.setInstitutionCode("0D");
        
        //�s��R�[�h
        l_params.setMarketCode("SP");
        
        //�O����{��
        l_params.setLastExecuteDate((WEB3DateUtility.getDate("20100908","yyyyMMdd")));

        //�����敪
        l_params.setStatus("0");
        
        //�X�V�҃R�[�h
        l_params.setLastUpdater("01001");
        
        //�쐬���t
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //�X�V���t
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }
    
    
    public static FeqOrderExecutionParams getFeqOrderExecutionParams()
    {
        FeqOrderExecutionParams l_params = new FeqOrderExecutionParams();
        //���h�c      order_execution_id    NUMBER    18    NotNull
        l_params.setOrderExecutionId(123456789);
        //�����h�c      account_id    NUMBER    18    NotNull
        l_params.setAccountId(31102102050L);
        //�⏕�����h�c        sub_account_id    NUMBER    18    NotNull
        l_params.setSubAccountId(33381251220301L);
        //���X�h�c      branch_id    NUMBER    18    NotNull
        l_params.setBranchId(33381L);
        //����҂h�c     trader_id    NUMBER    18    NULL
        l_params.setTraderId(123456);
        //�����h�c      order_id    NUMBER    18    NotNull
        l_params.setOrderId(123456);
        //�����P�ʂh�c        order_unit_id    NUMBER    18    NotNull
        l_params.setOrderUnitId(456789123);
        //�������      order_type    NUMBER    6    NotNull
        l_params.setOrderType(OrderTypeEnum.FEQ_BUY);
        //�����^�C�v     product_type    NUMBER    6    NotNull
        l_params.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        //�s��h�c      market_id    NUMBER    18    NULL
        //��n��       delivery_date    DATE        NULL
        //���n��n��     f_delivery_date    DATE        NULL
        //���ʔ�      exec_serial_no    NUMBER    8    NotNull
        l_params.setExecSerialNo(1001);
        //���P��      exec_price    DECIMAL    18 12  6   NULL
        //�בփ��[�g     fx_rate    DECIMAL    18    12  6   NotNull
        l_params.setFxRate(100);
        //��萔��      exec_quantity    DECIMAL    18  12  6   NotNull
        l_params.setExecQuantity(200);
        //������      exec_timestamp    DATE        NotNull
        l_params.setExecTimestamp(GtlUtils.getSystemTimestamp());
        //�폜�t���O     delete_flag    NUMBER    1    NotNull
        l_params.setDeleteFlag(BooleanEnum.FALSE);
        //������       biz_date    VARCHAR2    8    NotNull
        l_params.setBizDate("20080201");
        //�����h�c      product_id    NUMBER    18    NotNull
        l_params.setProductId(45678913212L);
        //���ϋ敪      settle_div    VARCHAR2    1    NULL
        //���ʃR�[�h     order_request_number    VARCHAR2    9    NULL
        //�^�p�R�[�h     order_emp_code    VARCHAR2    7    NULL
        //���o�H�敪        order_exec_route_div    VARCHAR2    1    NULL
        //�X�V�҃R�[�h        last_updater    VARCHAR2    20    NULL
        //�쐬���t      created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //�X�V���t      last_updated_timestamp    DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //�������      foreign_trade_price    DECIMAL    18    12  6   NULL
        return l_params;
    }
    
    public static FeqFinTransactionParams getFeqFinTransactionParams()
    {
        FeqFinTransactionParams l_params = new FeqFinTransactionParams();
        //�g�����U�N�V�����h�c    fin_transaction_id    NUMBER    18    NotNull
        l_params.setFinTransactionId(123456789);
        //�����h�c    account_id    NUMBER    18    NotNull
        l_params.setAccountId(31102102050L);
        //�⏕�����h�c    sub_account_id    NUMBER    18    NotNull
        l_params.setSubAccountId(33381251220301L);
        //�����h�c    product_id    NUMBER    18    NotNull
        l_params.setProductId(456789132);
        //�g�����U�N�V�����^�C�v    fin_transaction_type    NUMBER    6    NotNull
        l_params.setFinTransactionType(FinTransactionType.FROM_DEPOSIT_AMOUNT_DSK);
        //�g�����U�N�V�����J�e�S��    fin_transaction_categ    NUMBER    6    NotNull
        l_params.setFinTransactionCateg(FinTransactionCateg.FEQ_TRANSFER);
        //�g�����U�N�V������������    fin_transaction_timestamp    DATE        NotNull
        l_params.setFinTransactionTimestamp(GtlUtils.getSystemTimestamp());
        //�ŋ敪    tax_type    NUMBER    6    NotNull
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        //���ϋ敪    settle_div    VARCHAR2    1    NULL
        //������    biz_date    VARCHAR2    8    NotNull
        l_params.setBizDate("20080201");
        //��n��    delivery_date    DATE        NotNull
        l_params.setDeliveryDate(GtlUtils.getSystemTimestamp());
        //�ʉ݃R�[�h    currency_code    VARCHAR2    8    NotNull
        l_params.setCurrencyCode("1002");
        //��n���    net_amount    DECIMAL    18   12  6   NotNull
        l_params.setNetAmount(45871);
        //��n����i�O�݁j    net_amount_fc    DECIMAL    18    12  6   NotNull
        l_params.setNetAmountFc(78945);
        //�K�p�בփ��[�g    fx_rate    DECIMAL    18   12  6   NotNull
        l_params.setFxRate(120);
        //�����^�C�v    product_type    NUMBER    6    NotNull
        l_params.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        //�s��h�c    market_id    NUMBER    18    NULL
        //���P��    price    DECIMAL    18    12  6   NULL
        //��萔��    quantity    DECIMAL    18 12  6   NotNull
        l_params.setQuantity(1230);
        //�����h�c    order_id    NUMBER    18    NULL
        //�����P�ʂh�c    order_unit_id    NUMBER    18    NULL
        //���h�c    order_execution_id    NUMBER    18    NULL
        //�ϑ��萔��    commission_fee    DECIMAL    18  12  6   NotNull
        l_params.setCommissionFee(100);
        //�ϑ��萔�������    commission_fee_tax    DECIMAL    18   12  6   NotNull
        l_params.setCommissionFeeTax(100);
        //�o�^No    reg_no    VARCHAR2    3    Null
        //������    charge_ratio    DECIMAL    18  12  6   Null
        //���n���Z����i�~�݁j    balance_amount    DECIMAL    18 12  6   NotNull
        l_params.setBalanceAmount(123);
        //�ϑ��萔���i�O�݁j    commission_fee_fc    DECIMAL    18   12  6   NotNull
        l_params.setCommissionFeeFc(123);
        //�ϑ��萔������Łi�O�݁j    commission_fee_tax_fc    DECIMAL    18    12  6   NotNull
        l_params.setCommissionFeeTaxFc(123);
        //���n���Z���    balance_amount_fc    DECIMAL    18  12  6   NotNull
        l_params.setBalanceAmountFc(123);
        //���n�萔��    foreign_commission_fee    DECIMAL    18  12  6   NotNull
        l_params.setForeignCommissionFee(100);
        //���n�����    foreign_tax    DECIMAL    18 12  6   NotNull
        l_params.setForeignTax(100);
        //���̑��R�X�g�P    foreign_fee_ext1    DECIMAL    18  12  6   NotNull
        l_params.setForeignFeeExt1(100);
        //���̑��R�X�g�Q    foreign_fee_ext2    DECIMAL    18  12  6   NotNull
        l_params.setForeignFeeExt2(100);
        //���Y�h�c    asset_id    NUMBER    18    NULL
        //���n�v���z    capital_gain    DECIMAL    18    12  6   NotNull
        l_params.setCapitalGain(100);
        //���n�v�Ŋz    capital_gain_tax    DECIMAL    18    12  6   NotNull
        l_params.setCapitalGainTax(100);
        //���n�v���z�i�O�݁j    capital_gain_fc    DECIMAL    18 12  6   NotNull
        l_params.setCapitalGainFc(100);
        //���n�v�Ŋz�i�O�݁j    capital_gain_tax_fc    DECIMAL    18 12  6   NotNull
        l_params.setCapitalGainTaxFc(100);
        //���p�ۗL���Y�̊Ǘ���    transfered_asset_mng_fee    DECIMAL    18   12  6   NULL
        //���p�ۗL���Y�̊Ǘ�������    transfered_asset_mng_fee_tax    DECIMAL    18    12  6   NULL
        //���p�ۗL���Y�̎萔��    transfered_asset_setup_fee    DECIMAL    18 12  6   NULL
        //���p�ۗL���Y�̎萔�������    transfered_asset_setup_fee_tax    DECIMAL    18  12  6   NULL
        //���Y�̕뉿    transfered_asset_book_value    DECIMAL    18 12  6   NULL
        //�폜�t���O    delete_flag    NUMBER    1    NotNull
        l_params.setDeleteFlag(BooleanEnum.FALSE);
        //���o�H�敪    order_exec_route_div    VARCHAR2    1    NULL
        //�X�V�҃R�[�h    last_updater    VARCHAR2    20    NULL
        //�쐬���t    created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //�X�V���t    last_updated_timestamp    DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }

    /**
     * ����񓚃e�[�u��
     * question_answer
     */
    public static QuestionAnswerParams getQuestionAnswerRow()
    {
    	QuestionAnswerParams l_questionAnswerParams =
    		new QuestionAnswerParams();
    	l_questionAnswerParams.setInstitutionCode("0D");
    	l_questionAnswerParams.setBranchCode("381");
    	l_questionAnswerParams.setQuestionDiv("0001");
    	l_questionAnswerParams.setOrderRequestNumber("000003006");
    	l_questionAnswerParams.setQuestionNo("100");
    	l_questionAnswerParams.setQuestionAnswer("A");
    	l_questionAnswerParams.setCreatedTimestamp(
			WEB3DateUtility.getDate("20080205", "yyyymmdd"));
    	l_questionAnswerParams.setLastUpdatedTimestamp(
			WEB3DateUtility.getDate("20080506", "yyyymmdd"));
    	return l_questionAnswerParams;
    }

    /**
     * ���������Ǘ��e�[�u��
     * payment_requisit_mng
     */
    public static PaymentRequisitMngParams getPaymentRequisitMngParams()
    {
        PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
        l_paymentRequisitMngParams.setAccountId(102050);
        l_paymentRequisitMngParams.setInstitutionCode("0D");
        l_paymentRequisitMngParams.setBranchCode("102");
        l_paymentRequisitMngParams.setAccountCode("10010");
        l_paymentRequisitMngParams.setTraderCode("0025");
        l_paymentRequisitMngParams.setFamilyName("jack");
        l_paymentRequisitMngParams.setAccountAttribute("0");
        l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        l_paymentRequisitMngParams.setCreatedTimestamp(Calendar.getInstance().getTime());  
        l_paymentRequisitMngParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_paymentRequisitMngParams;
    }

    /**
     * ���������e�[�u��
     * feq_order_action
     */
    public static FeqOrderActionParams getFeqOrderActionParams()
    {
        FeqOrderActionParams l_params = new FeqOrderActionParams();
        //���������h�c    order_action_id     NUMBER    18    NotNull
        l_params.setOrderActionId(123456789);
        //�����h�c    account_id     NUMBER    18    NotNull
        l_params.setAccountId(31102102050L);        
        //�⏕�����h�c    sub_account_id     NUMBER    18    NotNull
        l_params.setSubAccountId(33381251220301L);
        //�����h�c    order_id     NUMBER    18    NotNull
        l_params.setOrderId(123456789);
        //�����P�ʂh�c    order_unit_id     NUMBER    18    NotNull
        l_params.setOrderUnitId(123456789);
        //�s��h�c    market_id     NUMBER    18    NULL
        l_params.setMarketId(123456);
        //�������    order_type     NUMBER    6    NotNull
        l_params.setOrderType(OrderTypeEnum.FEQ_BUY);
        //�����C�x���g�^�C�v    order_event_type     NUMBER    6    NotNull
        l_params.setOrderEventType(OrderEventTypeEnum.APPROVED);
        //�����P��    price     DECIMAL    18   12  6   NULL
        //���s����    execution_condition_type     NUMBER    6    NULL
        //��������    order_condition_type     VARCHAR2    1    NULL
        //�����������Z�q    order_cond_operator     VARCHAR2    1    NULL
        //�t�w�l��l    stop_order_price     DECIMAL    18  12  6   NULL
        //�iW�w�l�j�����w�l    w_limit_price     DECIMAL    18  12  6   NULL
        //�����������t    expiration_date     DATE        NULL
        //��������    quantity     DECIMAL    18    12  6   NotNull
        l_params.setQuantity(100);
        //�s��Ɗm�F�ς݂̎w�l    confirmed_price     DECIMAL    18   12  6   NULL
        //�s��Ɗm�F�ς݂̐���    confirmed_quantity     DECIMAL    18    12  6   NULL
        //��萔��    executed_quantity     DECIMAL    18   12  6   NULL
        //�������    order_status     NUMBER    6    NotNull
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        //���������X�e�[�^�X    expiration_status     NUMBER    6    NotNull
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        //��������ԍ�    order_action_serial_no     NUMBER    8    NotNull
        l_params.setOrderActionSerialNo(1001);
        //���P��    executed_price     DECIMAL    18  12  6   NULL
        //������    exec_timestamp     DATE        NULL
        //�����h�c    product_id     NUMBER    18    NotNull
        l_params.setProductId(123456789);
        //�T�Z��n���    estimated_price     DECIMAL    18   12  6   NULL
        //�T�Z��n����i�O�݁j    f_estimated_price     DECIMAL    18 12  6   NULL
        //���������E����敪    modify_cancel_type     VARCHAR2    1    NULL
        //�����o�H�敪    order_root_div     VARCHAR2    1    NULL
        //�s�ꂩ��m�F�ς݂̒����P��    confirmed_order_price     DECIMAL    18  12  6   NULL
        //�s�ꂩ��m�F�ς݂̊T�Z��n���    confirmed_estimated_price     DECIMAL    18    12  6   NULL
        //�s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j    confirmed_f_estimated_price     DECIMAL    18  12  6   NULL
        //�s�ꂩ��m�F�ς݂̎��s����    confirmed_exec_condition_type     NUMBER    6    NULL
        //�����G���[���R�R�[�h    error_reason_code     VARCHAR2    4    NULL
        //�X�V�҃R�[�h    last_updater     VARCHAR2    20    NULL
        //�쐬���t    created_timestamp     DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //�X�V���t    last_updated_timestamp     DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }

    /**
     * �U�֐�����t�L���[�e�[�u��
     * @@return HostTransferAcceptParams
     */
    public static HostTransferAcceptParams getHostTransferAcceptRow()
    {
        HostTransferAcceptParams l_params =
            new HostTransferAcceptParams();
        l_params.setRequestCode("1");
        l_params.setInstitutionCode("0D");
        l_params.setBranchCode("381");
        l_params.setAccountCode("1111111");
        l_params.setOrderRequestNumber("1");
        l_params.setAcceptDiv("1");
        l_params.setErrorMessage("1");
        l_params.setStatus("1");
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }

    /**
     * �U�֓��͒ʒm�L���[�e�[�u��
     * host_transfer_receipt
     */
    public static HostTransferReceiptParams getHostTransferReceiptRow()
    {
        HostTransferReceiptParams l_hostTransferReceiptParams =
            new HostTransferReceiptParams();
        l_hostTransferReceiptParams.setRequestCode("1");
        l_hostTransferReceiptParams.setInstitutionCode("0D");
        l_hostTransferReceiptParams.setBranchCode("381");
        l_hostTransferReceiptParams.setAccountCode("1111111");
        l_hostTransferReceiptParams.setDepositTypeDiv("01");
        l_hostTransferReceiptParams.setOrderRequestNumber("000000001");
        l_hostTransferReceiptParams.setStatus("1");
        l_hostTransferReceiptParams.setRemarkCode("03");
        return l_hostTransferReceiptParams;
    }

    /**
     * �ݓ������t�L���[�e�[�u��
     * host_ruito_cancel_accept
     */
    public static HostRuitoCancelAcceptParams getHostRuitoCancelAcceptRow()
    {
        HostRuitoCancelAcceptParams l_hostRuitoCancelAcceptParams =
            new HostRuitoCancelAcceptParams();
        l_hostRuitoCancelAcceptParams.setRequestCode("1");
        l_hostRuitoCancelAcceptParams.setInstitutionCode("0D");
        l_hostRuitoCancelAcceptParams.setBranchCode("381");
        l_hostRuitoCancelAcceptParams.setAccountCode("1111111");
        l_hostRuitoCancelAcceptParams.setOrderRequestNumber("000000001");
        l_hostRuitoCancelAcceptParams.setStatus("0");
        return l_hostRuitoCancelAcceptParams;
    }

    /**
     * IPO�\���e�[�u��
     * ipo_order
     */
    public static IpoOrderParams getIpoOrderRow()
    {
        IpoOrderParams l_ipoOrderParams = new IpoOrderParams();
        l_ipoOrderParams.setIpoOrderId(123L);
        l_ipoOrderParams.setIpoProductId(123456L);
        l_ipoOrderParams.setBranchId(33381L);
        l_ipoOrderParams.setAccountId(31102102050L);
        l_ipoOrderParams.setSubAccountId(33381251220301L);
        l_ipoOrderParams.setLastOrderActionSerialNo(1000);
        l_ipoOrderParams.setQuantity(120);
        l_ipoOrderParams.setLimitPrice(10000);
        l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCELLED);
        l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        return l_ipoOrderParams;
    }
    
    /**
     * FX�U�֏����}�X�^
     * fx_transfer_master
     */
    public static FxTransferMasterParams getFxTransferMasterParams()
    {
        FxTransferMasterParams l_params =
            new FxTransferMasterParams();
        //FX�V�X�e������ID    fx_system_id    NUMBER    18    NotNull
        l_params.setFxSystemId(123456789);
        //�U�֋敪    transfer_div    VARCHAR2    1    NotNull
        l_params.setTransferDiv("1");
        //��n���ݒ�敪    delivery_date_div    VARCHAR2    2    NotNull
        l_params.setDeliveryDateDiv("1");
        //�������    order_type    NUMBER    6    NotNull
        l_params.setOrderType(OrderTypeEnum.ASSET_IN);
        //�E�v�R�[�h    remark_code    VARCHAR2    4    NotNull
        l_params.setRemarkCode("71");
        //�E�v��    remark_name    VARCHAR2    10    null
        l_params.setRemarkName("CFD��ض�");
        //���o���ꗗ����敪    io_list_trade_div    VARCHAR2    4    null
        l_params.setIoListTradeDiv("5");
        //�쐬���t    created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //�X�V���t    last_updated_timestamp    DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }

    /**
     * �����J�݋敪�e�[�u��
     * acc_open_div
     */
    public static AccOpenDivParams getAccOpenDivRow()
    {
        AccOpenDivParams l_accOpenDivParams = new AccOpenDivParams();
        l_accOpenDivParams.setAccountId(31102102050L);
        l_accOpenDivParams.setAccType("01");
        l_accOpenDivParams.setAccOpenDiv("0");
        l_accOpenDivParams.setAccOpenDate(GtlUtils.getSystemTimestamp());
        l_accOpenDivParams.setLastUpdater("0001");
        l_accOpenDivParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_accOpenDivParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_accOpenDivParams;
    }

    /**
     * ���������e�[�u��
     * aio_order_action
     */
    public static AioOrderActionParams getAioOrderActionRow()
    {
        AioOrderActionParams l_aioOrderActionParams = new AioOrderActionParams();
        l_aioOrderActionParams.setOrderActionId(123);
        l_aioOrderActionParams.setAccountId(100);
        l_aioOrderActionParams.setSubAccountId(1002);
        l_aioOrderActionParams.setOrderId(1003);
        l_aioOrderActionParams.setOrderUnitId(1004);
        l_aioOrderActionParams.setOrderType(OrderTypeEnum.CASH_OUT);
        l_aioOrderActionParams.setOrderEventType(OrderEventTypeEnum.CHANGE);
        l_aioOrderActionParams.setQuantity(1000);
        l_aioOrderActionParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
        l_aioOrderActionParams.setOrderActionSerialNo(2);
        l_aioOrderActionParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_aioOrderActionParams.setProductId(1005);
        l_aioOrderActionParams.setCancelType("5");
        l_aioOrderActionParams.setErrorReasonCode("001");
        l_aioOrderActionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_aioOrderActionParams.setLastUpdatedUser("1009");
        l_aioOrderActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_aioOrderActionParams;
    }

    /**
     * �U�֐��������L���[�e�[�u��
     * host_transfer_order
     */
    public static HostTransferOrderParams getHostTransferOrderRow()
    {
        HostTransferOrderParams l_hostTransferOrderParams =
            new HostTransferOrderParams();
        l_hostTransferOrderParams.setRequestCode("1001");
        l_hostTransferOrderParams.setInstitutionCode("0D");
        l_hostTransferOrderParams.setBranchCode("381");
        l_hostTransferOrderParams.setAccountCode("1002");
        l_hostTransferOrderParams.setRemarkCode("72");
        l_hostTransferOrderParams.setOrderRequestNumber("1003");
        l_hostTransferOrderParams.setStatus("3");
        return l_hostTransferOrderParams;
    }

    /**
     * 
     */
    public static ProcessManagementParams getProcessManagementParams()
    {
        ProcessManagementParams l_processManagementParams =
            new ProcessManagementParams();
        //�v���Z�X�h�c  process_id  VARCHAR2  4
        l_processManagementParams.setProcessId("0006");
        //�،���ЃR�[�h  institution_code  VARCHAR2  3
        l_processManagementParams.setInstitutionCode("0D");
        //���X�R�[�h  branch_code  VARCHAR2  3
        l_processManagementParams.setBranchCode("624");
        //�����敪  status  VARCHAR2  1
        l_processManagementParams.setStatus("1");
        //�ŏI�X�V��  last_updater  VARCHAR2  20
        l_processManagementParams.setLastUpdater("jiddk");
        //�ŏI�X�V����  last_updated_timestamp  DATE
        l_processManagementParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());
        return l_processManagementParams;
        
    }

    /**
     * 
     */
    public static AccOpenVoucherMasterParams getAccOpenVoucherMasterRow()
    {
        AccOpenVoucherMasterParams l_accOpenVoucherMasterParams =
            new AccOpenVoucherMasterParams();
        l_accOpenVoucherMasterParams.setInstitutionCode("0D");
        l_accOpenVoucherMasterParams.setBranchCode("381");
        l_accOpenVoucherMasterParams.setAccountDiv("4");
        l_accOpenVoucherMasterParams.setRequestCode("GI858");
        l_accOpenVoucherMasterParams.setSerialNo("1");
        return l_accOpenVoucherMasterParams;
    }

    /**
     * 
     */
    public static AccOpenVoucherStatusParams getAccOpenVoucherStatusRow()
    {
        AccOpenVoucherStatusParams l_accOpenVoucherStatusRow =
            new AccOpenVoucherStatusParams();
        l_accOpenVoucherStatusRow.setInstitutionCode("0D");
        l_accOpenVoucherStatusRow.setAccOpenRequestNumber("2007092899999");
        l_accOpenVoucherStatusRow.setRequestCode("GI858");
        l_accOpenVoucherStatusRow.setSerialNo("1");
        l_accOpenVoucherStatusRow.setVoucherStatus("1");
        l_accOpenVoucherStatusRow.setSendTimestamp(GtlUtils.getSystemTimestamp());
        l_accOpenVoucherStatusRow.setRecvTimestamp(GtlUtils.getSystemTimestamp());
        l_accOpenVoucherStatusRow.setErrorCode(null);
        l_accOpenVoucherStatusRow.setLastUpdater(null);
        l_accOpenVoucherStatusRow.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_accOpenVoucherStatusRow.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_accOpenVoucherStatusRow;
    }

    /**
     * 
     */
    public static AccOpenVoucherItemParams getAccOpenVoucherItemRow()
    {
        AccOpenVoucherItemParams l_accOpenVoucherItemParams =
            new AccOpenVoucherItemParams();
        l_accOpenVoucherItemParams.setInstitutionCode("0D");
        l_accOpenVoucherItemParams.setBranchCode("381");
        l_accOpenVoucherItemParams.setAccountDiv("4");
        l_accOpenVoucherItemParams.setRequestCode("GI858");
        l_accOpenVoucherItemParams.setOutputItemSymbolName(
            WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE);
        l_accOpenVoucherItemParams.setEditWayDiv("0");
        l_accOpenVoucherItemParams.setSerialNo("1");
        l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM);
        l_accOpenVoucherItemParams.setInputItemSymbolName1(WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE);
        return l_accOpenVoucherItemParams;
    }

    /**
     * 
     */
    public static HostAgencyNotifyVoucherParams getHostAgencyNotifyVoucherRow()
    {
        HostAgencyNotifyVoucherParams l_hostAgencyNotifyVoucherParams =
            new HostAgencyNotifyVoucherParams();
        l_hostAgencyNotifyVoucherParams.setOrderRequestNumber("1");
        l_hostAgencyNotifyVoucherParams.setRequestCode("GI858");
        l_hostAgencyNotifyVoucherParams.setInstitutionCode("0D");
        l_hostAgencyNotifyVoucherParams.setBranchCode("381");
        l_hostAgencyNotifyVoucherParams.setAccountCode("1234567");
        l_hostAgencyNotifyVoucherParams.setAccOpenRequestNumber("1");
        l_hostAgencyNotifyVoucherParams.setSerialNo("1");
        l_hostAgencyNotifyVoucherParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_hostAgencyNotifyVoucherParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_hostAgencyNotifyVoucherParams;
    }

    /**
     * �����J�݌����q�ꎞ�e�[�u��
     * @@return
     */
    public static ExpAccountOpenTempParams getExpAccountOpenTempRow()
    {
        ExpAccountOpenTempParams l_expAccountOpenTempParams = new ExpAccountOpenTempParams();

        l_expAccountOpenTempParams.setInstitutionCode("0D");
        l_expAccountOpenTempParams.setStatus("1");
        l_expAccountOpenTempParams.setAccOpenRequestNumber("2007092899999");
        l_expAccountOpenTempParams.setInstitutionId(33);
        l_expAccountOpenTempParams.setBranchId(33381);
        l_expAccountOpenTempParams.setBranchCode("381");
        l_expAccountOpenTempParams.setAccountCode("2512246");
        l_expAccountOpenTempParams.setExAccountFlag(BooleanEnum.TRUE);
        l_expAccountOpenTempParams.setAccountDiv("0");
        l_expAccountOpenTempParams.setOrderDiv("0");
        l_expAccountOpenTempParams.setFamilyName("�����@@�l�Y");
        l_expAccountOpenTempParams.setGivenName("���Y");
        l_expAccountOpenTempParams.setFamilyNameAlt1("Ųĳ �۳");
        l_expAccountOpenTempParams.setGivenNameAlt1("Siro");
        l_expAccountOpenTempParams.setSex("1");
        l_expAccountOpenTempParams.setZipCode("100081");
        l_expAccountOpenTempParams.setAddressLine1("�����s");
        l_expAccountOpenTempParams.setAddressLine1Kana("SiroSiroSiroSiroSiro");
        l_expAccountOpenTempParams.setExperienceDivEquity("0");
        l_expAccountOpenTempParams.setExperienceDivMargin("0");
        l_expAccountOpenTempParams.setExperienceDivBond("0");
        l_expAccountOpenTempParams.setExperienceDivWt("0");
        l_expAccountOpenTempParams.setExperienceDivFundSk("0");
        l_expAccountOpenTempParams.setExperienceDivFundBd("0");
        l_expAccountOpenTempParams.setExperienceDivFo("0");
        l_expAccountOpenTempParams.setExperienceDivFEquity("0");
        l_expAccountOpenTempParams.setExperienceDivEtc("0");
        l_expAccountOpenTempParams.setExperienceFlagEquity(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setExperienceFlagMargin(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setExperienceFlagBond(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setExperienceFlagWt(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setExperienceFlagFundSk(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setExperienceFlagFundBd(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setExperienceFlagFo(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setExperienceFlagFEquity(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setExperienceFlagEtc(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setInterestFlagEquity(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setInterestFlagMinistock(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setInterestFlagMargin(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setInterestFlagBond(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setInterestFlagFund(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setInterestFlagFo(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setInterestFlagFEquity(BooleanEnum.FALSE);
        l_expAccountOpenTempParams.setInterestFlagEtc(BooleanEnum.TRUE);
        l_expAccountOpenTempParams.setIdConfirmFlag(BooleanEnum.TRUE);
        l_expAccountOpenTempParams.setInsiderFlag(BooleanEnum.TRUE);
        Timestamp l_tsSystemTimestamp =
        GtlUtils.getTradingSystem().getSystemTimestamp();
        l_expAccountOpenTempParams.setCreatedTimestamp(l_tsSystemTimestamp);
        l_expAccountOpenTempParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

        return l_expAccountOpenTempParams;
    }

    public static SoapConnectPrefRpcParams getSoapConnectPrefRpcRow()
    {
        SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
        l_soapConnectPrefRpcParams.setBranchId(33381);
        l_soapConnectPrefRpcParams.setConnectDiv("01");
        l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.85:8080/axis2/services/AdministrativeAPI");
        l_soapConnectPrefRpcParams.setTargetNamespaceName("mm");
        l_soapConnectPrefRpcParams.setServiceName("TEST_URL_HTTP");
        l_soapConnectPrefRpcParams.setPortTypeName("ORIX");
        l_soapConnectPrefRpcParams.setOperationName("wP9Gl713r6+2EKVPv10G0i+Uqxmp8keBjKrOjm37pbyukvLerx4DJHeNtDZbEgUU103fD9objXdePRl3Ur7CLw==");
        l_soapConnectPrefRpcParams.setResponseParamType("#");
        l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_soapConnectPrefRpcParams.setResponseTimeout("50");
        return l_soapConnectPrefRpcParams;
    }

    public static HostPaymentOrderParams getHostPaymentOrderRow()
    {
        HostPaymentOrderParams l_hostPaymentOrderParams = new HostPaymentOrderParams();
        l_hostPaymentOrderParams.setRequestCode("GI801");
        l_hostPaymentOrderParams.setInstitutionCode("0D");
        l_hostPaymentOrderParams.setBranchCode("381");
        l_hostPaymentOrderParams.setAccountCode("123456");
        l_hostPaymentOrderParams.setOrderRequestNumber("1");
        l_hostPaymentOrderParams.setStatus("3");
        return l_hostPaymentOrderParams;
    }
    /**
     * �ۏ؋������U�֒�~�e�[�u��(deposit_autotransfer_stop)
     * 
     */
    public static DepositAutotransferStopParams getDepositAutotransferStopRow()
    {
        DepositAutotransferStopParams l_depositAutotransferStopParams = new DepositAutotransferStopParams();

        l_depositAutotransferStopParams.setDepositAutotransferStopId(123456789123456789l);
        l_depositAutotransferStopParams.setAccountId(333812512246L);
        l_depositAutotransferStopParams.setBranchId(33381L);
        l_depositAutotransferStopParams.setAutotransferStopStart(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
        l_depositAutotransferStopParams.setAutotransferStopEnd(WEB3DateUtility.getDate("20150924","yyyyMMdd"));
        l_depositAutotransferStopParams.setRegistDiv("1");
        l_depositAutotransferStopParams.setLastUpdater("scottche");
        l_depositAutotransferStopParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_depositAutotransferStopParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_depositAutotransferStopParams;
    }

    /**
     * �ڋq���[���A�h���X
     */
    public static AccountMailAddressParams getAccountMailAddressRow()
    {
        AccountMailAddressParams l_accountMailAddressParams = new AccountMailAddressParams();
        l_accountMailAddressParams.setInstitutionCode("0D");
        l_accountMailAddressParams.setBranchCode("381");
        l_accountMailAddressParams.setAccountCode("190600");
        l_accountMailAddressParams.setEmailAddressNumber(1);
        l_accountMailAddressParams.setAddressDiv("1");
        l_accountMailAddressParams.setEmailAddress("test@@sinocom.cn");
        l_accountMailAddressParams.setEmailLastUpdater("190600");
        l_accountMailAddressParams.setEmailLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        l_accountMailAddressParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_accountMailAddressParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_accountMailAddressParams;
    }

    /**
     * ���[�����
     */
    public static MailAssortmentParams getMailAssortmentRow()
    {
        MailAssortmentParams l_mailAssortmentParams = new MailAssortmentParams();
        l_mailAssortmentParams.setInstitutionCode("0D");
        l_mailAssortmentParams.setBranchCode("381");
        l_mailAssortmentParams.setAccountCode("190600");
        l_mailAssortmentParams.setEmailAddressNumber(1);
        l_mailAssortmentParams.setMailAssortmentDiv("1");
        l_mailAssortmentParams.setLastUpdater("190600");
        l_mailAssortmentParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_mailAssortmentParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_mailAssortmentParams;
    }

    /**
     * 
     */
    public static HostAccRegVoucherParams getHostAccRegVoucherRow()
    {
        HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
        l_hostAccRegVoucherParams.setOrderRequestNumber("123456789");
        l_hostAccRegVoucherParams.setRequestCode("43210");
        l_hostAccRegVoucherParams.setInstitutionCode("0D");
        l_hostAccRegVoucherParams.setBranchCode("381");
        l_hostAccRegVoucherParams.setAccountCode("2512246");
        l_hostAccRegVoucherParams.setAccOpenRequestNumber("1234567890123");
        l_hostAccRegVoucherParams.setSerialNo("333");
        l_hostAccRegVoucherParams.setAccountName("��");
        l_hostAccRegVoucherParams.setAccountNameKana("��");
        l_hostAccRegVoucherParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
        l_hostAccRegVoucherParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
        
        //T0807311AIAO
        l_hostAccRegVoucherParams.setProamDiv("1");
        l_hostAccRegVoucherParams.setForeignerDivBroadcast("1");
        l_hostAccRegVoucherParams.setForeignerDivAviation("1");
        l_hostAccRegVoucherParams.setForeignerDivNtt("1");
        l_hostAccRegVoucherParams.setDividendTransferDiv("1");
        l_hostAccRegVoucherParams.setAgentDivPermanent("0");
        l_hostAccRegVoucherParams.setAgentDivLegal("0");
        return l_hostAccRegVoucherParams;
    }

    /**
     * �敨OPCSV�t�@@�C���t�H�[�}�b�g�e�[�u��(ifo_csv_file_format)
     */
//    public static IfoCsvFileFormatParams getIfoCsvFileFormatRow()
//    {
//        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
//            new IfoCsvFileFormatParams();
//
//        //�،���ЃR�[�h
//        l_ifoCsvFileFormatParams.setInstitutionCode("0D");
//        //�J�����ԍ�
//        l_ifoCsvFileFormatParams.setColumnNumber(0);
//        //�J�������x��
//        l_ifoCsvFileFormatParams.setColumnLabel("central_module_c");
//        //�쐬���t
//        l_ifoCsvFileFormatParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//        //�X�V���t
//        l_ifoCsvFileFormatParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//
//        return l_ifoCsvFileFormatParams;
//    }
    
    public static LoginUsernameParams getLoginUsernameRow()
    {
        LoginUsernameParams l_loginUserNameParams = new LoginUsernameParams();
        l_loginUserNameParams.setUsername("Scottche");
        l_loginUserNameParams.setLoginId(12);
        return l_loginUserNameParams;
    }
    
    /**
     * �d�q��t�Ǘ��e�[�u��(ele_delivery_management)
     */
//    public static EleDeliveryManagementParams getEleDeliveryManagementRow()
//    {
//        EleDeliveryManagementParams l_params = new EleDeliveryManagementParams();
//        
//        l_params.setAccountId(333812512246L);
//        l_params.setInstitutionCode("0D");
//        l_params.setBranchCode("381");
//        l_params.setAccountCode("2512246");
//        l_params.setEleDelRegiFlag(0);//0�F���ς݁@@1�F�ς�
//        l_params.setCreatedTimestamp(
//            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
//        l_params.setLastUpdatedTimestamp(
//            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
//        
//        return l_params;
//    }
}@
