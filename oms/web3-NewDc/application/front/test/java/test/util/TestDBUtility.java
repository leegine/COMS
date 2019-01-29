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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3JUnitテスト用、DB関連ユーティリティクラス(TestDBUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/12/12  王暁傑 (中訊) 新規作成
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
 * (WEB3JUnitテスト用、DB関連ユーティリティクラス)<BR>
 * @@author 王暁傑
 * @@version 1.0
 */
public class TestDBUtility
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        TestDBUtility.class);   
    
    /**
     * insertWithDelメソッド用、DBに既存レコードを保存したのリスト<BR>
     */
    private static List rowBackupList = new ArrayList();
    
    /**
     * コンストラクタ<BR>
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
                    //insertできない場合
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
                            // 処理なし
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
     * 現時点のTransactionをCommit。<BR>
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
     * 新しいTransactionをStartします<BR>
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
     * 指定されたRowオブジェクトをDBにinsertします。<BR>
     * <BR>
     * DBに、PKとUnique Indexと同じのRowがあれは、削除して、挿入します。<BR>
     * 挿入の後で、Commitを実施します。<BR>
     * 
     * @@param l_row -
     *            insertのRowオブジェクト
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
     * 指定されたRowオブジェクトをDBにinsertします。<BR>
     * <BR>
     * DBに、PKとUnique Indexと同じのRowがあれは、削除して、挿入します。<BR>
     * @@param l_row - insertのRowオブジェクト
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
     * 指定されたテーブルのすべてレコードを削除<BR>
     * <BR>
     * @@param l_row - insertのRowオブジェクト
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
     * 指定されたテーブルのすべてレコードを削除<BR>
     * <BR>
     * @@param l_row - insertのRowオブジェクト
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
     * @@param l_insertData - insertのRowオブジェクト
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
     * 補助口座（顧客勘定残高）Rowを作成.<BR>
     */
    public static  SubAccountParams getSubAccountRow()
    {
        SubAccountParams l_subAccountParams = new SubAccountParams();
        //口座ＩＤ]
        l_subAccountParams.setAccountId(333812512203L);
        //補助口座ＩＤ
        l_subAccountParams.setSubAccountId(33381251220301L);
        //補助口座タイプ
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        //証券会社コード
        l_subAccountParams.setInstitutionCode("0D");
        //証券会社ID
        l_subAccountParams.setInstitutionId(33);
        //部店ＩＤ
        l_subAccountParams.setBranchId(33381L);
        //補助口座ステータス
        l_subAccountParams.setSubAccountStatus(SubAccountStatusEnum.ACTIVE);
        //口座登録日
        l_subAccountParams.setOpenDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        //口座閉鎖日
        l_subAccountParams.setCloseDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        //残高(当日）
        l_subAccountParams.setCashBalance(13456.0);
        //作成日付
        l_subAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日付
        l_subAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_subAccountParams;
    }

    /**
     * 証券会社Rowを作成.<BR>
     */
    public static InstitutionParams getInstitutionRow()
    {
        InstitutionParams l_institutionParams = new InstitutionParams();

        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(33);

        return l_institutionParams;
    }
    
    /**
     * サービス利用キーテーブルRowを作成.<BR>
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
     * 部店Rowを作成.<BR>
     */
    public static BranchParams getBranchRow()
    {
        BranchParams l_branchParams = new BranchParams();

        l_branchParams.setBranchId(33381);
        l_branchParams.setInstitutionCode("0D");
        l_branchParams.setInstitutionId(33);
        l_branchParams.setBranchCode("381");
        l_branchParams.setBranchName("東京支店");
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
     * 顧客マスターRowを作成.<BR>
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
        l_mainAccountParams.setFamilyName("内藤　@四郎");
        l_mainAccountParams.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
        l_mainAccountParams.setGivenNameAlt1("Siro");
        l_mainAccountParams.setZipCode("1001238");
        l_mainAccountParams.setSubZipCode("1001238");
        l_mainAccountParams.setAddressLine1("東京都");
        l_mainAccountParams.setAddressLine2("江東区");
        l_mainAccountParams.setAddressLine3("深川５");
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
     * 取引時間Rowを作成.<BR>
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
     * 市場Rowを作成.<BR>
     */
    public static MarketParams getMarketRow()
    {
        MarketParams l_marketParams = new MarketParams();

        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("0D");
        l_marketParams.setMarketCode("SP");
        l_marketParams.setSonarMarketCode("G");
        l_marketParams.setMarketName("シンガポール");
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
     * （部店市場別.外株）取扱条件Rowを作成.<BR>
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
     * 内部者マスターRowを作成.<BR>
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
     * 銘柄Rowを作成.<BR>
     */
    public static ProductParams getProductRow()
    {
        ProductParams l_productParams = new ProductParams();

        l_productParams.setProductId(3304148080000L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_productParams.setStandardName("シンセンテルス");
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
     * 外株銘柄Rowを作成.<BR>
     */
    public static FeqProductParams getFeqProductRow()
    {
        FeqProductParams l_feqProductParams = new FeqProductParams();

        l_feqProductParams.setProductId(3304148080000L);
        l_feqProductParams.setInstitutionCode("0D");
        l_feqProductParams.setProductCode("N8080");
        l_feqProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_feqProductParams.setStandardNameKana("シンセンテルス");
        l_feqProductParams.setStandardNameKanji("ST深セン特力集団");
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
     * 外株銘柄Rowを作成.<BR>
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
     * 顧客銘柄別取引停止Rowを作成.<BR>
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
     * 取引銘柄マスターRowを作成.<BR>
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
     * 扱者Rowを作成.<BR>
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
        l_traderParams.setFamilyName("大和");
        l_traderParams.setGivenName("太郎");
        l_traderParams.setFamilyNameAlt1("ダイワ");
        l_traderParams.setGivenNameAlt1("タロウ");
        l_traderParams.setTradingPassword("11123");
        l_traderParams.setAccountOrderFlag("1");
        l_traderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_traderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_traderParams;
    }
    
    /**
     * 注文テーブル（ヘッダ）Rowを作成.<BR>
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
     * 注文受付ステイタステーブルRowを作成.<BR>
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
     * 株式注文単位テーブル（ヘッダ）Rowを作成.<BR>
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
     * 株式注文単位テーブル（ヘッダ）Rowを作成.<BR>
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
     * 建株返済指定情報ﾃｰﾌﾞﾙ Rowを作成.<BR>
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
     * 建株返済指定情報ﾃｰﾌﾞﾙ Rowを作成.<BR>
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
     * 出来終了テーブル(orderexecution_end)
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
     * オンライン実行結果テーブル(online_run_status)
     * path: $/WEBBROKER3/09Requirement管理/2.1.共通/ＤＢレイアウト
     *        /00.共通/00.会社・部店・顧客・市場/オンライン実行結果テーブル仕様.xls
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
     * 注文テーブル（ヘッダ）(ifo_order)
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
     * 注文単位テーブル(ifo_order_unit)
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
     * 管理者テーブル(administrator)
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
     * 手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
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
     * 手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
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
     * 手数料割引キャンペーン条件マスタ(comm_campaign_cond_mst)
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
     * 振込先金融機@関テーブル(transfered_fin_institution)
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
     * 発注先切替テーブル
     * (OrderSwitchingRow)
     * @@return OrderSwitchingParams
     */
    public static OrderSwitchingParams getOrderSwitchingRow()
    {
        OrderSwitchingParams l_orderSwitchingParams = new OrderSwitchingParams();
        //1   証券会社コード    institution_code    VARCHAR2   3   NotNull
        l_orderSwitchingParams.setInstitutionCode("0D");
        //2   銘柄タイプ    product_type    NUMBER   6   NotNull
        l_orderSwitchingParams.setProductType(ProductTypeEnum.IFO);
        //3   市場コード    market_code    VARCHAR2   2   NotNull
        l_orderSwitchingParams.setMarketCode(WEB3MarketCodeDef.HONGKONG);
        //4   市場コード（SONAR）    sonar_market_code    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setSonarMarketCode("G");
        //5   発注経路区分    submit_order_route_div    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION);
        //6   有効フラグ    valid_flag    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setValidFlag(WEB3ValidFlag.OFF);
        //7   訂正取消可能フラグ    change_cancel_enable_flag    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setChangeCancelEnableFlag(WEB3ChangeCancelEnableFlag.DISABLE);
        //8   作成日付    created_timestamp    DATE      NotNull
        l_orderSwitchingParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //9   更新日付    last_updated_timestamp    DATE      NotNull
        l_orderSwitchingParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        //10  フロント発注システム区分    front_order_system_code    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.OSAKA_EXCHANGE_STOCK);
        //11  MQトリガ発行    submit_mq_trigger    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setSubmitMqTrigger(WEB3SubmitMqTriggerDef.NOT_TRIGGER);
        //12  発注エンジン区分    order_engine_div    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setOrderEngineDiv(WEB3OrderEngineDivDef.SONAR);
        return l_orderSwitchingParams;
    }
    
   /**
    * 先物OP注文取引キューテーブル(host_fotype_order_all)
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
     * 先物OP銘柄テーブル (ifo_product)
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
     * 建玉テーブル (ifo_contract)
     * path:  $/WEBBROKER3/09Requirement管理/6.1.大証夕場/1.0.先物オプション
     *         /DBレイアウト/04.先物オプション/02.残高関連/建玉テーブル仕様.xls 
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
     * 先物OP取引銘柄マスタ(ifo_traded_product)
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
     * 管理者権限(admin_permission)
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
     * 先物OP取引銘柄ﾏｽﾀ（一時ﾃｰﾌﾞﾙ）(ifo_traded_product_updq)
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
     * 取引銘柄一時テーブル(traded_product_updq)
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
     * 先物OP訂正取消通知ｷｭｰ(host_fotype_order_clmd_notify)
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
     * 管理者タイプテーブル(administrator_type) 
     */
    public static AdministratorTypeParams getAdministratorTypeRow()
    {
        AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
        l_administratorTypeParams.setInstitutionCode("0D");
        l_administratorTypeParams.setPermissionLevel("331");
        l_administratorTypeParams.setLastUpdater("");
        l_administratorTypeParams.setDirAdminFlag(0);
        l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
        l_administratorTypeParams.setPermissionLevelName("更新可能管理者");
        l_administratorTypeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_administratorTypeParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        return l_administratorTypeParams;
    }

    /**
     *  部店用プリファ@レンステーブル仕様 (branch_preferences)
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
      * 債券部店別条件テーブル(bond_branch_condition)
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
      * 取扱可能注文条件テーブル(enable_order_condition)
      * $/WEBBROKER3/09Requirement管理/2.1.共通/ＤＢレイアウト/00.共通
      *  /00.会社・部店・顧客・市場/取扱可能注文条件テーブル仕様.xls
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
      * 債券銘柄マスタテーブル(bond_product)
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
      * (入出金テーブル)<BR>
      * host_cash_transfer
      */
     public static HostCashTransferParams getHostCashTransferRow()
     {
         HostCashTransferParams l_hostCashTransferParams = new HostCashTransferParams();
        // 1  データコード  request_code    VARCHAR2    5NotNullGI811：個別  FI811：本部一括
         l_hostCashTransferParams.setRequestCode("GI811");
        // 2  証券会社コード institution_code    VARCHAR2    3NotNull
         l_hostCashTransferParams.setInstitutionCode("0D");
        // 3  部店コード   branch_code VARCHAR2    3NotNull
         l_hostCashTransferParams.setBranchCode("624");
        // 4  顧客コード   account_code    VARCHAR2    7NotNull
         l_hostCashTransferParams.setAccountCode("123456");
        // 5  扱者コード   trader_code VARCHAR2    5Null
         l_hostCashTransferParams.setTraderCode("1234");
        // 6  入出金区分   order_div   VARCHAR2    1NotNull1：出金  2：入金  
         l_hostCashTransferParams.setOrderDiv("1");
        // 7  入出金金額   amount  NUMBER  12Null取消の場合、マイナス値   
         l_hostCashTransferParams.setAmount(100);
        // 8  入出金日    cash_trans_date DATENull
         l_hostCashTransferParams.setCashTransDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
        // 9  タイムスタンプ created_timestamp   VARCHAR2    14NullYYYYMMDDHHMMSS
         l_hostCashTransferParams.setCreatedTimestamp("20040707");
        // 10     処理区分    status  VARCHAR2    1NotNull0：未処理  1：処理済
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
      * 注文単位テーブル(aio_order_unit)
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
      * 外貨MMF為替レートテーブル(frgn_mmf_exchange_rate)
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
      * 投信銘柄マスタテーブル(mutual_fund_product)
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
      * 株式注文受付キューテーブル(host_eqtype_order_accept)
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
      *  投信取引銘柄マスタテーブル(mutual_fund_traded_product)
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
      * 保有資産テーブル(asset)
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
      * 投信保有資産補助テーブル(mf_sub_asset)
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
      * 注文履歴テーブル(eqtype_order_action)
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
      * 約定メール送信サービス_株式約定メール送信テーブル(eqtype_order_exec_send_mail)
      * @@return
      */
     public static EqtypeOrderExecSendMailParams getEqtypeOrderExecSendMailRow()
     {
         EqtypeOrderExecSendMailParams l_eqtypeOrderExecSendMailParams =
             new EqtypeOrderExecSendMailParams();
         //証券会社コード,  institution_code,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setInstitutionCode("6D");
         //部店コード,  branch_code,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setBranchCode("381");
         //口座コード,  account_code,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setAccountCode("2512246");
         //識別コード,  order_request_number,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setOrderRequestNumber("000022001");
         //銘柄コード,  product_code,  NULL,
         l_eqtypeOrderExecSendMailParams.setProductCode("1");
         //市場コード,  market_code,  NULL,
         l_eqtypeOrderExecSendMailParams.setMarketCode("33");
         //注文履歴ＩＤ,  order_action_id,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setOrderActionId(15051L);
         //税区分,  tax_type,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setTaxType(TaxTypeEnum.NORMAL);
         //約定区分,  order_exec_status,  NOT NULL,
         l_eqtypeOrderExecSendMailParams.setOrderExecStatus("1");
         l_eqtypeOrderExecSendMailParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         //更新日時,  last_updated_timestamp,  NOT NULL,
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
      * 投信注文単位テーブル仕様(mutual_fund_order_unit)
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
      * 投信海外市場カレンダー仕様(mutual_fund_frgncal)
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
      * 投信注文キューテーブル (host_xbmf_order)
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
      * 外貨MMF注文キューテーブル(host_frgn_mmf_order)
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
       * システムプリファ@レンス(system_preferences)
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
       * 市場用プリファ@レンス(market_preferences)
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
       * 表示内容テーブル（DISPLAY_CONTENTS）
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
       * 株式予約注文単位テーブル(rsv_eq_order_unit)
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
       * 外株注文単位テーブル(feq_order_unit)<BR>
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
       * 障害仮想サーバ切替管理テーブル(VirtualServerChangeRow)
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
       * 株式注文取引キューテーブル(host_eqtype_order_all)
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
       * 先物OP指数マスタテーブル(ifo_index_master)
       * path: $/WEBBROKER3/09Requirement管理/2.4.先物オプション/ＤＢレイアウト
       *        /04.先物オプション/00.銘柄関連/先物OP指数マスタテーブル仕様.xls
       */
      public static IfoIndexMasterParams getIfoIndexMasterRow()
      {
          IfoIndexMasterParams l_ifoIndexMasterParams = new IfoIndexMasterParams();
          l_ifoIndexMasterParams.setIndexId(123L);
          l_ifoIndexMasterParams.setUnderlyingProductCode("0005");
          l_ifoIndexMasterParams.setFutureOptionDiv("1");
          l_ifoIndexMasterParams.setStandardName("日経300");
          
          return l_ifoIndexMasterParams;
      }
      
      /**
       * 外貨振込先金融機@関テーブル（f_trans_fin_institution）
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
       * 銀行入金通知テーブル
       * @@return
       */
      public static BankDepositNotifyParams getBankDepositNotifyRow()
      {
          BankDepositNotifyParams lparams = new BankDepositNotifyParams();
        //  銀行入金通知テーブルID      bankdepositnotifyid   NUMBER     18   NotNull    
          lparams.setBankDepositNotifyId(123);
        //  証券会社コード       institutioncode   VARCHAR2     3   NotNull
          lparams.setInstitutionCode("123");
        //  銀行コード     bankcode   VARCHAR2    4   NotNull
          lparams.setBankCode("12");
          lparams.setBranchCode("123");
        //  銀行支店コード       bankbranchcode   VARCHAR2     3   NotNull
          lparams.setBankBranchCode("123");
        //  口座番号      bankaccountno   VARCHAR2      10   NotNull
          lparams.setBankAccountNo("123");
        //  照会番号      depositdatareferenceno   VARCHAR2    8   NotNull
          lparams.setDepositDataReferenceNo("123");
        //  勘定日       depositdataaccountdate   VARCHAR2    6   NotNull
          lparams.setDepositDataAccountDate("123");
        //  銀行名カナ     banknamekana   VARCHAR2       30      
        //  支店名カナ     bankbranchnamekana   VARCHAR2    30      
        //  預金種目      depositdatabankaccounttype   VARCHAR2       1       
        //  入金起算日     depositdatabasedate   VARCHAR2       6       
        //  取引金額      depositdatadepositamount   VARCHAR2      12      
        //  振込依頼人コード      depositdatatranspersoncode   VARCHAR2       10      
        //  振込依頼人名    depositdatatranspersonname   VARCHAR2       96      
        //  入払区分      cashtransferdiv   VARCHAR2    1   NotNull
          lparams.setCashTransferDiv("1");
        //  取引区分      tradediv   VARCHAR2    2       
        //  仕向銀行名     deliveredbankname   VARCHAR2      30      
        //  仕向店名      deliveredbankbranchname   VARCHAR2       30      
        //  摘要内容      summary   VARCHAR2      40      
        //  EDI情報     ediinfo   VARCHAR2     40      
        //  処理区分      status   VARCHAR2       1       
        //  入金エラーコメント     depositerrorcomment   VARCHAR2    100     
        //  部店コード     branchcode   VARCHAR2      20      
        //  顧客コード     accountcode   VARCHAR2     7       
        //  備考    remark   VARCHAR2       100     
        //  エラー履歴最終通番     lasterrorhistoryserialno   NUMBER       8   NotNull
          lparams.setLastErrorHistorySerialNo(12345678);
          lparams.setDataLoadDiv("1");
          return lparams; 
      }

      /**
       * 通貨コード
       * @@return
       */
      public static GenCurrencyParams getGenCurrencyRow()
      {
          GenCurrencyParams l_params = new GenCurrencyParams();
          //証券会社コード   institution_code   VARCHAR2   3   Notnull        
          l_params.setInstitutionCode("123");
          //  通貨コード   currency_code   VARCHAR2   3   Notnull   ｢通貨コード｣sheet参照       
          l_params.setCurrencyCode("132");
          //  通貨名   currency_name   VARCHAR2   20   NULL        
          //  通貨略称   currency_short_name   VARCHAR2   3   NULL          
          //  今回売付為替レート   current_sell_rate   NUMBER   7    3   4   NULL   （概算代金計算に使用）         
          //  前回売付為替レート   prev_sell_rate   NUMBER   7   3   4   NULL   （概算代金計算に使用）         
          //  今回買付為替レート   current_buy_rate   NUMBER   7 3   4   NULL   （概算代金計算に使用）         
          //  前回買付為替レート   prev_buy_rate   NUMBER   7    3   4   NULL   （概算代金計算に使用）         
          //  今回売付約定為替レート   current_sell_exec_rate   NUMBER   7 3   4   NULL   （概算代金計算に使用）         
          //  前回売付約定為替レート   prev_sell_exec_rate   NUMBER   7    3   4   NULL   （概算代金計算に使用）         
          //  今回買付約定為替レート   current_buy_exec_rate   NUMBER   7  3   4   NULL   （概算代金計算に使用）         
          //  前回買付約定為替レート   prev_buy_exec_rate   NUMBER   7 3   4   NULL   （概算代金計算に使用）         
          //  小数部桁数   scale   NUMBER   6   Notnull   "当該通貨を扱う場合の小数点以下桁数。
          l_params.setScale(1234);
          //※100.51のように表示する場合、2を登録。"         
          //  円貨換算丸め方式   change_jpy_round_div   VARCHAR2   1   Notnull   "0：四捨五入　@1：切捨　@2：切上
          l_params.setChangeJpyRoundDiv("1");
          // ※Default　@1：切捨"         
          //  外貨換算丸め方式   change_f_ccy_round_div   VARCHAR2   1   Notnull   "0：四捨五入　@1：切捨　@2：切上
          l_params.setChangeFCcyRoundDiv("1");
          //    ※Default　@0：四捨五入"         
          //  基準為替更新者コード   rate_last_updater   VARCHAR2   20   NULL         
          //  約定為替更新者コード   exec_rate_last_updater   VARCHAR2   20   NULL        
          //  基準為替更新日付   rate_update_timestamp   DATE      Notnull   DEFAULT sysdate  
          l_params.setRateUpdateTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
          //  約定為替更新日付   exec_rate_update_timestamp   DATE      Notnull   DEFAULT sysdate  
          l_params.setExecRateUpdateTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
          //  更新者コード   last_updater   VARCHAR2   20   NULL          
          //  作成日付   created_timestamp   DATE      Notnull   DEFAULT sysdate    
          l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
          //  更新日付   last_updated_timestamp   DATE      Notnull   DEFAULT sysdate 
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
       * 余力計算条件Row(tradingpower_calc_condition)
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
       * 通貨テーブルテーブル
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
       * サービス申込登録テーブル
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
       * 投信第2銘柄マスタ（SONAR）テーブル
       */
      public static MutualFund2ndProductSonarParams getMutualFund2ndProductSonarParams()
      {
          MutualFund2ndProductSonarParams l_mutualFund2ndProductSonarParams = new MutualFund2ndProductSonarParams();
          
          l_mutualFund2ndProductSonarParams.setInstitutionCode("0D");
          l_mutualFund2ndProductSonarParams.setProductCode("0001000");
          
          return l_mutualFund2ndProductSonarParams;
      }
      
      /**
       * 代用振替強制キューテーブル
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
       * 現引現渡入力通知キューテーブル (host_eqtype_swap_receipt)
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
       * 処理対象データコードテーブル(request_codes_for_read)
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
       * 株式注文入力通知キューテーブル (host_eqtype_order_receipt)
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
       * 株式注文訂正取消通知キューテーブル(host_eqtype_order_clmd_receipt)
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
       * デーモントリガーテーブル(daemon_trigger)
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
      * 呼値テーブル
      * @@return
      */
     public static EquityLimitPriceRangeMstParams getEquityLimitPriceRangeMstRow()
     {
         //  市場コード  market_code  VARCHAR2 2            NOT NULL        
         //  下限値  low_price  DECIMAL 18    12  6   NOT NULL        
         //  上限値  cap_price  DECIMAL 18    12  6   NULL        
         //  値幅  range  DECIMAL 18 12  6   NULL        
         //  作成日付  created_timestamp  DATE             NOT NULL        
         //  更新日付  last_updated_timestamp  DATE            NOT NULL
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
      * 値幅テーブル
      * @@return
      */
     public static EquityTickValuesMstParams getEquityTickValuesMstRow()
     {
         //  市場コード   market_code   VARCHAR2              2           NOT NULL        
         // 下限値    low_price   DECIMAL                18  12  6   NOT NULL        
         // 上限値  cap_price            DECIMAL             18  12  6   NULL        
         //  刻み値   tick_value          DECIMAL             18  12  6   NULL        
         //  作成日付   created_timestamp          DATE  NOT NULL      
         // 更新日付   last_updated_timestamp 
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
      * 株式予約建株返済指定情報テーブル
      */
     public static RsvEqClosingContractSpecParams getRsvEqClosingContractSpecRow()
     {
        //     口座ＩＤ          account_id                      
        //     補助口座ＩＤ        sub_account_id                      
        //     注文ＩＤ          order_id                        
        //     建株ＩＤ          contract_id                     
        //     返済連番          closing_serial_no                       
        //     返済注文数量        quantity                        
        //     作成日付          created_timestamp                       
        //     更新日付          last_updated_timestamp                      
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
      * 株式失効通知キューテーブル
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
      * サービス申込属性テーブル(srv_appli_attribute)
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
      * サービス利用手数料条件テーブル(srv_regi_comm_cond)
      * path:  $/WEBBROKER3/09Requirement管理/3.0.サービス利用/DBレイアウト
      *         /00.サービス利用/サービス利用手数料条件テーブル仕様.xls
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
      * サービス申込登録テーブル(srv_regi_application)
      * path:  $/WEBBROKER3/09Requirement管理/2.1.共通/ＤＢレイアウト/00.共通
      *         /00.会社・部店・顧客・市場/サービス申込登録テーブル仕様.xls
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
      * サービス利用期間料金テーブル(srv_regi_charge)
      * path: 名前:  $/WEBBROKER3/09Requirement管理/3.0.サービス利用/DBレイアウト
      *              /00.サービス利用/サービス利用期間料金テーブル仕様.xls
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
      * 審査サービスマスタテーブル(compliance_audit_service_mst)
      * path: $/WEBBROKER3/09Requirement管理/5.0.コンプライアンス/DBレイアウト
      *        /審査サービスマスタテーブル仕様.xls
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
      * サービスマスターテーブル(srv_regi_master)
      * path: $/WEBBROKER3/09Requirement管理/3.0サービス利用/DBレイアウト 
      *        /サービスマスターテーブル仕様.xls
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
      * 申込要サービステーブル(srv_regi_setup)
      * path: $/WEBBROKER3/09Requirement管理/3.0サービス利用/DBレイアウト
      *        /申込要サービステーブル仕様.xls
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
      * ルールエンジンからの通知テーブル(rls_con_order_hit_notify)
      * path: $/WEBBROKER3/09Requirement管理/4.4.トリガー注文/DBレイアウト
      *       /参考資料（北京側で対応無し）/ルールエンジンからの通知テーブル仕様
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
      * (管理者共通）ｱｯﾌﾟﾛｰﾄﾞﾃｰﾌﾞﾙ(administrator_upload)
      * path: $/WEBBROKER3\09Requirement管理\2.1.共通\ＤＢレイアウト
      *       \00.共通\04.管理者共通\（管理者共通）アップロードテーブル仕様.xls
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
      * (管理者共通)ｱｯﾌﾟﾛｰﾄﾞﾃﾝﾎﾟﾗﾘ(administrator_upload_temp)
      * path: $/WEBBROKER3\09Requirement管理\2.1.共通\ＤＢレイアウト
      *       \00.共通\04.管理者共通\（管理者共通）アップロードテンポラリテーブル仕様.xls
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
      * 自動振替登録マスタテーブル(direct_debit)
      * path: $/WEBBROKER3/09Requirement管理/2.1/DBレイアウト/00/00
      *        /自動振替登録マスタテーブル仕様.xls
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
      * 金融機@関（銀行）マスタテーブル(fin_institution_bank)
      * path: $/WEBBROKER3/09Requirement管理/2.1/DBレイアウト/00/00
      *        /金融機@関（銀行）マスタテーブル仕様.xls
      */
     public static FinInstitutionBankParams getFinInstitutionBankRow()
     {
         FinInstitutionBankParams l_finInstitutionBankParams = new FinInstitutionBankParams();
         l_finInstitutionBankParams.setFinInstitutionCode("1234");
         l_finInstitutionBankParams.setFinBranchCode("123");
         l_finInstitutionBankParams.setFinInstitutionNameKana("銀行名（カナ）");         
         l_finInstitutionBankParams.setFinBranchNameKana("支店名（カナ）");
         l_finInstitutionBankParams.setFinInstitutionName("銀行名（漢字）");
         l_finInstitutionBankParams.setFinBranchName("支店名（漢字）");
         l_finInstitutionBankParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
         l_finInstitutionBankParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());         
         return l_finInstitutionBankParams;
     }
     
     /*
      * トランザクション（株式顧客勘定明細）テーブル(eqtype_fin_transaction)
      * path: $/WEBBROKER3/09Requirement管理/2.3.株式\ＤＢレイアウト
      *       \01.株式\01.注文・約定\トランザクションテーブル（株式顧客勘定明細）仕様.xls
      * 
      * */
     public static EqtypeFinTransactionParams getEqtypeFinTransactionParams()
     {
         EqtypeFinTransactionParams l_finTransactionParams = new EqtypeFinTransactionParams();
         //トランザクションＩＤ   fin_transaction_id      NOT NULL
         l_finTransactionParams.setFinTransactionId(111L);
         
         //口座ＩＤ              account_id              NOT NULL
         l_finTransactionParams.setAccountId(333812512203L);
         
         //補助口座ＩＤ           sub_account_id         NOT NULL
         l_finTransactionParams.setSubAccountId(33381251220301L);
         
         //銘柄ＩＤ               product_id            NOT NULL
         l_finTransactionParams.setProductId(123456);
         //トランザクションタイプ  fin_transaction_type  NOT NULL

         l_finTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_EQUITY_BUY);
         
         //トランザクションカテゴリ  fin_transaction_categ       NOT NULL
         l_finTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);
         
         //トランザクション発生日時  fin_transaction_timestamp   NOT NULL
         l_finTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
         
         //税区分               tax_type            NOT NULL
         l_finTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
         
         //受渡日               delivery_date           NOT NULL
         l_finTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
         
         //受渡代金          net_amount          NOT NULL
         l_finTransactionParams.setNetAmount(1.0);
         
         //銘柄タイプ         product_type            NOT NULL
         l_finTransactionParams.setProductType(ProductTypeEnum.EQUITY);
         
         //市場ＩＤ          market_id           NULL
         //約定単価          price               NULL
         //約定数量          quantity            NOT NULL
         l_finTransactionParams.setQuantity(1000);
         
         //注文ＩＤ          order_id            NULL
         l_finTransactionParams.setOrderId(11);
         
         //注文単位ＩＤ            order_unit_id           NULL
         l_finTransactionParams.setOrderUnitId(1001);
         
         //約定ＩＤ          order_execution_id      NULL
         //委託手数料         commission_fee          NOT NULL
         l_finTransactionParams.setCommissionFee(1000);
         
         //委託手数料消費税      commission_fee_tax          NOT NULL
         l_finTransactionParams.setCommissionFeeTax(1000);
         
         //資産ＩＤ          asset_id            NULL
         //建株の受渡金額           imported_paid_value     NOT NULL
         l_finTransactionParams.setImportedPaidValue(1000);
         
         //建手数料          imported_setup_fee      NOT NULL
         l_finTransactionParams.setImportedSetupFee(1000);
         
         //建手数料消費税           imported_setup_fee_tax      NOT NULL
         l_finTransactionParams.setImportedPayInterestFeeTax(1000);
         
         //名義書換料         imported_name_transfer_fee  NULL
         //名義書換料消費税      imported_name_transfer_fee_tax  NULL
         //譲渡益金額         capital_gain            NOT NULL
         l_finTransactionParams.setCapitalGain(1000);
         
         //譲渡益税額         capital_gain_tax        NOT NULL
         l_finTransactionParams.setCapitalGainTax(1000);
         
         //建株ＩＤ          contract_id         NULL
         l_finTransactionParams.setContractId(12347);
         
         //管理費               imported_management_fee     NOT NULL
         l_finTransactionParams.setImportedManagementFee(422);
         
         //管理費消費税            imported_management_fee_tax NOT NULL
         l_finTransactionParams.setImportedManagementFeeTax(533);
         
         //順日歩               imported_interest_fee       NOT NULL
         l_finTransactionParams.setImportedInterestFee(1000);
         
         //順日歩消費税            imported_interest_fee_tax   NOT NULL
         l_finTransactionParams.setImportedInterestFeeTax(1000);
         
         //逆日歩               imported_pay_interest_fee   NULL
         //逆日歩消費税            imported_pay_interest_fee_tax   NULL
         //貸株料               imported_loan_equity_fee    NULL
         //その他               imported_other          NULL
         //売却保有資産の管理費        transfered_asset_mng_fee    NULL
         //売却保有資産の管理費消費税 transfered_asset_mng_fee_tax    NULL
         //売却保有資産の手数料        transfered_asset_setup_fee  NULL
         //売却保有資産の手数料消費税 transfered_asset_setup_fee_tax  NULL
         //資産の簿価         transfered_asset_book_value NULL
         //削除フラグ         delete_flag         NOT NULL
         l_finTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
         
         //債券所得経過利子      accrued_interest        NULL
         //作成日付          created_timestamp       NOT NULL
         l_finTransactionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
         
         //更新日付          last_updated_timestamp      NOT NULL
         l_finTransactionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
         //譲渡益有効状態           capital_gain_status     NOT NULL
         l_finTransactionParams.setCapitalGainStatus("0");
         
         return l_finTransactionParams;
     }
     
     
     /**
      * FX顧客テーブル(fx_account)
      * path:  $/WEBBROKER3/09Requirement管理/2.9.入出金/ＤＢレイアウト
      *         /23.為替保証金/FX顧客テーブル.xls
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
      * FX口座番号テーブル(fx_account_code)
      * path: $/WEBBROKER3/09Requirement管理/2.9.入出金/ＤＢレイアウト
      *        /23.為替保証金/FX口座番号テーブル.xls
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
     * 会社別FXシステム条件テーブル(comp_fx_condition)
     * path: $/WEBBROKER3/09Requirement管理/2.9.入出金/ＤＢレイアウト/23.為替保証金
     *        /会社別FXシステム条件テーブル.xls
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
        //受付時間区分    trading_time_type    VARCHAR2       2    NotNull
        l_compFxConditionParams.setTradingTimeType("01");
        //オンライン口座開設実施区分    online_acc_open    VARCHAR2      2    NotNull
        l_compFxConditionParams.setOnlineAccOpen("0");
        //SOAP接続実施区分    soap_connect_div    VARCHAR2        2    NotNull
        l_compFxConditionParams.setSoapConnectDiv("0");
        //口座種別    acc_type    VARCHAR2      2    NotNull
        l_compFxConditionParams.setAccType("1");
        //MRF口座許可区分    mrf_allow_div    VARCHAR2        2    NotNull
        l_compFxConditionParams.setMrfAllowDiv("0");
        //口座区分リアル更新    acc_open_real_update    VARCHAR2     2    NotNull
        l_compFxConditionParams.setAccOpenRealUpdate("0");
        //質問同意チェック実施区分    question_check_div    VARCHAR2        2    NotNull
        l_compFxConditionParams.setQuestionCheckDiv("0");
        //FXシステム条件ID    fx_system_id    NUMBER      18    NotNull
        l_compFxConditionParams.setFxSystemId(6241001);
        l_compFxConditionParams.setGetTransferableAmtDiv("1");
//        l_compFxConditionParams.setMultiCfdAccDiv("0");
//        l_compFxConditionParams.setCommonTransferPageDiv("0");
        return l_compFxConditionParams;
    }

    /**
     * GFT振替状況テーブルRowを作成.<BR>
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
     * 注文テーブル（ヘッダ）<BR>
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
     * 余力計算結果テーブル（信用顧客）(tp_calc_result_margin)
     * path: $/WEBBROKER3/09Requirement管理/3.6.余力/ＤＢレイアウト/10.余力
     *        /余力計算結果テーブル（信用顧客）仕様.xls
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
     * 余力計算結果詳細テーブル（信用顧客）(tp_calc_result_margin_detail)
     * path: $/WEBBROKER3/09Requirement管理/3.6.余力/ＤＢレイアウト/10.余力
     *        /余力計算結果詳細テーブル（信用顧客）仕様.xls
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
     * 余力計算結果テーブル（現物顧客）(tp_calc_result_equity)
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
     * 余力計算結果詳細テーブル（信用顧客）(tp_calc_result_margin_detail)
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
     * 定時定額買付引落口座テーブル
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
     * 定時定額買付条件テーブル
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
     * 株券担保ローン口座テーブル(stock_secured_loan)
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
     * 担保ローン出金拘束金テーブル(security_cashout_restraint)
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
     * 口座開設伝票登録受付キュー(host_acc_open_accept)
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
     * 取報・取残電子交付・特定口座登録（GI311）キュー(host_condition_reg_voucher)
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
     * 担保銘柄テーブル(security_product)
     * path:  $/WEBBROKER3/09Requirement管理/2.9.入出金/ＤＢレイアウト
     *         /05.入出金/06.証券担保ローン/担保銘柄テーブル.xls
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
     * 口座開設見込客
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
        l_expAccountOpenParams.setFamilyName("内藤　@四郎");
        l_expAccountOpenParams.setGivenName("太郎");
        l_expAccountOpenParams.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
        l_expAccountOpenParams.setGivenNameAlt1("Siro");
        l_expAccountOpenParams.setSex("1");
        l_expAccountOpenParams.setZipCode("100081");
        l_expAccountOpenParams.setAddressLine1("東京都");
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
     * 担保不足顧客データテーブル
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
     * 累投注文単位テーブル
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
     * 累投銘柄テーブル
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
     * その他拘束金（仮拘束）テーブル
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
     * 株式出来通知キューテーブル(host_equity_order_exec_notify)
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
     * （PTS）出来終了テーブル(pts_orderexecution_end)
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
     * 【先物OP予約注文単位テーブル】
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
     * 確定トランザクション（株式顧客勘定明細）
     * fixed_fin_transaction
     */
    public static FixedFinTransactionParams getFixedFinTransactionRow()
    {
        FixedFinTransactionParams l_fixedFinTransactionParams = new FixedFinTransactionParams();
        //fixed_fin_transaction_id
        l_fixedFinTransactionParams.setFixedFinTransactionId(111L);
        
        //口座ＩＤ              account_id              NOT NULL
        l_fixedFinTransactionParams.setAccountId(333812512203L);
        
        //補助口座ＩＤ           sub_account_id         NOT NULL
        l_fixedFinTransactionParams.setSubAccountId(33381251220301L);
        
        //銘柄ＩＤ               product_id            NOT NULL
        l_fixedFinTransactionParams.setProductId(123456);
        //トランザクションタイプ  fin_transaction_type  NOT NULL

        l_fixedFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_EQUITY_BUY);
        
        //トランザクションカテゴリ  fin_transaction_categ       NOT NULL
        l_fixedFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
        
        //トランザクション発生日時  fin_transaction_timestamp   NOT NULL
        l_fixedFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
        
        //税区分               tax_type            NOT NULL
        l_fixedFinTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
        
        //受渡日               delivery_date           NOT NULL
        l_fixedFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
        
        //受渡代金          net_amount          NOT NULL
        l_fixedFinTransactionParams.setNetAmount(1.0);
        
        //銘柄タイプ         product_type            NOT NULL
        l_fixedFinTransactionParams.setProductType(ProductTypeEnum.EQUITY);

        //約定単価          price               NULL
        //約定数量          quantity            NOT NULL
        l_fixedFinTransactionParams.setQuantity(1000);

        //委託手数料         commission_fee          NOT NULL
        l_fixedFinTransactionParams.setCommissionFee(1000);
        
        //委託手数料消費税      commission_fee_tax          NOT NULL
        l_fixedFinTransactionParams.setCommissionFeeTax(1000);
        
        //建株の受渡金額           imported_paid_value     NOT NULL
        l_fixedFinTransactionParams.setImportedPaidValue(1000);
        
        //建手数料          imported_setup_fee      NOT NULL
        l_fixedFinTransactionParams.setImportedSetupFee(1000);
        
        //建手数料消費税           imported_setup_fee_tax      NOT NULL
        l_fixedFinTransactionParams.setImportedSetupFeeTax(1000);
        
        //名義書換料         imported_name_transfer_fee 
        l_fixedFinTransactionParams.setImportedNameTransferFee(100);
        
        //名義書換料消費税      imported_name_transfer_fee_tax
        l_fixedFinTransactionParams.setImportedNameTransferFeeTax(100);
        
        //譲渡益金額         capital_gain            NOT NULL
        l_fixedFinTransactionParams.setCapitalGain(1000);
        
        //譲渡益税額         capital_gain_tax        NOT NULL
        l_fixedFinTransactionParams.setCapitalGainTax(1000);
        
        //建株ＩＤ          contract_id         NULL
        l_fixedFinTransactionParams.setFixedContractId(12347);
        
        //管理費               imported_management_fee     NOT NULL
        l_fixedFinTransactionParams.setImportedManagementFee(422);
        
        //管理費消費税            imported_management_fee_tax NOT NULL
        l_fixedFinTransactionParams.setImportedManagementFeeTax(533);
        
        //順日歩               imported_interest_fee       NOT NULL
        l_fixedFinTransactionParams.setImportedInterestFee(1000);
        
        //順日歩消費税            imported_interest_fee_tax   NOT NULL
        l_fixedFinTransactionParams.setImportedInterestFeeTax(1000);
        
        //逆日歩               imported_pay_interest_fee  
        l_fixedFinTransactionParams.setImportedPayInterestFee(100);
        
        //逆日歩消費税            imported_pay_interest_fee_tax  
        l_fixedFinTransactionParams.setImportedPayInterestFeeTax(100);
        
        //貸株料               imported_loan_equity_fee   
        l_fixedFinTransactionParams.setImportedLoanEquityFee(100);
        //その他               imported_other         
        l_fixedFinTransactionParams.setImportedOther(100);        
        
        //削除フラグ         delete_flag         NOT NULL
        l_fixedFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
        
        //作成日付          created_timestamp       NOT NULL
        l_fixedFinTransactionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
        
        //更新日付          last_updated_timestamp      NOT NULL
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
     * ロック中の建玉詳細
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
     * 電子鳩システム会社部店別プリファ@レンス
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
     * 電子鳩システム部店商品別プリファ@レンス
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
     * 電子鳩システムドキュメント種別管理テーブル
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
     * 電子鳩システム機@能別プリファ@レンス
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
     * 書面区分管理テーブル
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
     * 電子鳩銘柄コード管理テーブル
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
     * 外部システムSOAP接続プリファ@レンス（メッセージ形式）
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
     * AP下り処理管理Rowを作成.
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
     * 先物OP限月マスタテーブル
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
     * 先物OP約定メール送信テーブル
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
     * 先物OP注文通知キューテーブル
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
     * 外株海外市場カレンダー
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
     * 投信注文履歴テーブル
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
     * 手数料コースコード変換マスタテーブル
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
     * 委託手数料コース変更申込テーブル
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
     * 委託手数料コースマスタ
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
     * 証券会社プリファ@レンステーブル
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
     * 会社部店商品テーブル
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
     * ログイン履歴
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
     * ログイン過去履歴
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
        //証券会社コード    institution_code    VARCHAR2    3    NotNull
        l_params.setInstitutionCode("0D");
        //部店コード    branch_code    VARCHAR2    3    NotNull
        l_params.setBranchCode("624");
        //顧客コード    account_code    VARCHAR2    7    NotNull
        l_params.setAccountCode("123456");
        //識別コード    order_request_number    VARCHAR2    9    NotNull
        l_params.setOrderRequestNumber("101");
        //名前（姓）    last_name    VARCHAR2    50    Null
        //名前（名）    first_name    VARCHAR2    50    Null
        //メールアドレス    mail_address    VARCHAR2    50    Null
        //ログインID    login_id    VARCHAR2    18    Null
        //初期パスワード    password    VARCHAR2    8    Null
        //口座番号（1万通貨コース）    fx_account_code_01    VARCHAR2    6    Null
        //口座番号（10万通貨コース）    fx_account_code_10    VARCHAR2    6    Null
        //口座開設状況区分    account_open_status_div    VARCHAR2    2    NotNull
        l_params.setAccountOpenStatusDiv("1");
        //送受信区分    send_rcv_div    VARCHAR2    2    NotNull
        l_params.setSendRcvDiv("0");
        //受付結果コード    result_code    VARCHAR2    8    Null
        //エラー理由コード    error_reason_code    VARCHAR2    4    Null
        //更新者コード    last_updater    VARCHAR2    20    Null
        //作成日付    created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp    DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //約諾書区分    agreement_div    VARCHAR2    1    NotNull
        l_params.setAgreementDiv("1");
        //受付結果コード（SOAP）    result_code_soap    VARCHAR2    4    Null
        //ＦＸシステムコード    fx_system_code    VARCHAR2    2    Null
        return l_params;
    }
    
    /*
     * 外株出来終了テーブル
     */
    public static FeqOrderexecutionEndParams getFeqOrderexecutionEndRow()
    {
        FeqOrderexecutionEndParams l_params = new FeqOrderexecutionEndParams();
        
        //証券会社コード
        l_params.setInstitutionCode("0D");
        
        //市場コード
        l_params.setMarketCode("SP");
        
        //前回実施日
        l_params.setLastExecuteDate((WEB3DateUtility.getDate("20100908","yyyyMMdd")));

        //処理区分
        l_params.setStatus("0");
        
        //更新者コード
        l_params.setLastUpdater("01001");
        
        //作成日付
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //更新日付
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }
    
    
    public static FeqOrderExecutionParams getFeqOrderExecutionParams()
    {
        FeqOrderExecutionParams l_params = new FeqOrderExecutionParams();
        //約定ＩＤ      order_execution_id    NUMBER    18    NotNull
        l_params.setOrderExecutionId(123456789);
        //口座ＩＤ      account_id    NUMBER    18    NotNull
        l_params.setAccountId(31102102050L);
        //補助口座ＩＤ        sub_account_id    NUMBER    18    NotNull
        l_params.setSubAccountId(33381251220301L);
        //部店ＩＤ      branch_id    NUMBER    18    NotNull
        l_params.setBranchId(33381L);
        //取引者ＩＤ     trader_id    NUMBER    18    NULL
        l_params.setTraderId(123456);
        //注文ＩＤ      order_id    NUMBER    18    NotNull
        l_params.setOrderId(123456);
        //注文単位ＩＤ        order_unit_id    NUMBER    18    NotNull
        l_params.setOrderUnitId(456789123);
        //注文種別      order_type    NUMBER    6    NotNull
        l_params.setOrderType(OrderTypeEnum.FEQ_BUY);
        //銘柄タイプ     product_type    NUMBER    6    NotNull
        l_params.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        //市場ＩＤ      market_id    NUMBER    18    NULL
        //受渡日       delivery_date    DATE        NULL
        //現地受渡日     f_delivery_date    DATE        NULL
        //約定通番      exec_serial_no    NUMBER    8    NotNull
        l_params.setExecSerialNo(1001);
        //約定単価      exec_price    DECIMAL    18 12  6   NULL
        //為替レート     fx_rate    DECIMAL    18    12  6   NotNull
        l_params.setFxRate(100);
        //約定数量      exec_quantity    DECIMAL    18  12  6   NotNull
        l_params.setExecQuantity(200);
        //約定日時      exec_timestamp    DATE        NotNull
        l_params.setExecTimestamp(GtlUtils.getSystemTimestamp());
        //削除フラグ     delete_flag    NUMBER    1    NotNull
        l_params.setDeleteFlag(BooleanEnum.FALSE);
        //発注日       biz_date    VARCHAR2    8    NotNull
        l_params.setBizDate("20080201");
        //銘柄ＩＤ      product_id    NUMBER    18    NotNull
        l_params.setProductId(45678913212L);
        //決済区分      settle_div    VARCHAR2    1    NULL
        //識別コード     order_request_number    VARCHAR2    9    NULL
        //運用コード     order_emp_code    VARCHAR2    7    NULL
        //約定経路区分        order_exec_route_div    VARCHAR2    1    NULL
        //更新者コード        last_updater    VARCHAR2    20    NULL
        //作成日付      created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付      last_updated_timestamp    DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //売買代金      foreign_trade_price    DECIMAL    18    12  6   NULL
        return l_params;
    }
    
    public static FeqFinTransactionParams getFeqFinTransactionParams()
    {
        FeqFinTransactionParams l_params = new FeqFinTransactionParams();
        //トランザクションＩＤ    fin_transaction_id    NUMBER    18    NotNull
        l_params.setFinTransactionId(123456789);
        //口座ＩＤ    account_id    NUMBER    18    NotNull
        l_params.setAccountId(31102102050L);
        //補助口座ＩＤ    sub_account_id    NUMBER    18    NotNull
        l_params.setSubAccountId(33381251220301L);
        //銘柄ＩＤ    product_id    NUMBER    18    NotNull
        l_params.setProductId(456789132);
        //トランザクションタイプ    fin_transaction_type    NUMBER    6    NotNull
        l_params.setFinTransactionType(FinTransactionType.FROM_DEPOSIT_AMOUNT_DSK);
        //トランザクションカテゴリ    fin_transaction_categ    NUMBER    6    NotNull
        l_params.setFinTransactionCateg(FinTransactionCateg.FEQ_TRANSFER);
        //トランザクション発生日時    fin_transaction_timestamp    DATE        NotNull
        l_params.setFinTransactionTimestamp(GtlUtils.getSystemTimestamp());
        //税区分    tax_type    NUMBER    6    NotNull
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        //決済区分    settle_div    VARCHAR2    1    NULL
        //発注日    biz_date    VARCHAR2    8    NotNull
        l_params.setBizDate("20080201");
        //受渡日    delivery_date    DATE        NotNull
        l_params.setDeliveryDate(GtlUtils.getSystemTimestamp());
        //通貨コード    currency_code    VARCHAR2    8    NotNull
        l_params.setCurrencyCode("1002");
        //受渡代金    net_amount    DECIMAL    18   12  6   NotNull
        l_params.setNetAmount(45871);
        //受渡代金（外貨）    net_amount_fc    DECIMAL    18    12  6   NotNull
        l_params.setNetAmountFc(78945);
        //適用為替レート    fx_rate    DECIMAL    18   12  6   NotNull
        l_params.setFxRate(120);
        //銘柄タイプ    product_type    NUMBER    6    NotNull
        l_params.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        //市場ＩＤ    market_id    NUMBER    18    NULL
        //約定単価    price    DECIMAL    18    12  6   NULL
        //約定数量    quantity    DECIMAL    18 12  6   NotNull
        l_params.setQuantity(1230);
        //注文ＩＤ    order_id    NUMBER    18    NULL
        //注文単位ＩＤ    order_unit_id    NUMBER    18    NULL
        //約定ＩＤ    order_execution_id    NUMBER    18    NULL
        //委託手数料    commission_fee    DECIMAL    18  12  6   NotNull
        l_params.setCommissionFee(100);
        //委託手数料消費税    commission_fee_tax    DECIMAL    18   12  6   NotNull
        l_params.setCommissionFeeTax(100);
        //登録No    reg_no    VARCHAR2    3    Null
        //徴収率    charge_ratio    DECIMAL    18  12  6   Null
        //現地清算代金（円貨）    balance_amount    DECIMAL    18 12  6   NotNull
        l_params.setBalanceAmount(123);
        //委託手数料（外貨）    commission_fee_fc    DECIMAL    18   12  6   NotNull
        l_params.setCommissionFeeFc(123);
        //委託手数料消費税（外貨）    commission_fee_tax_fc    DECIMAL    18    12  6   NotNull
        l_params.setCommissionFeeTaxFc(123);
        //現地清算代金    balance_amount_fc    DECIMAL    18  12  6   NotNull
        l_params.setBalanceAmountFc(123);
        //現地手数料    foreign_commission_fee    DECIMAL    18  12  6   NotNull
        l_params.setForeignCommissionFee(100);
        //現地取引税    foreign_tax    DECIMAL    18 12  6   NotNull
        l_params.setForeignTax(100);
        //その他コスト１    foreign_fee_ext1    DECIMAL    18  12  6   NotNull
        l_params.setForeignFeeExt1(100);
        //その他コスト２    foreign_fee_ext2    DECIMAL    18  12  6   NotNull
        l_params.setForeignFeeExt2(100);
        //資産ＩＤ    asset_id    NUMBER    18    NULL
        //譲渡益金額    capital_gain    DECIMAL    18    12  6   NotNull
        l_params.setCapitalGain(100);
        //譲渡益税額    capital_gain_tax    DECIMAL    18    12  6   NotNull
        l_params.setCapitalGainTax(100);
        //譲渡益金額（外貨）    capital_gain_fc    DECIMAL    18 12  6   NotNull
        l_params.setCapitalGainFc(100);
        //譲渡益税額（外貨）    capital_gain_tax_fc    DECIMAL    18 12  6   NotNull
        l_params.setCapitalGainTaxFc(100);
        //売却保有資産の管理費    transfered_asset_mng_fee    DECIMAL    18   12  6   NULL
        //売却保有資産の管理費消費税    transfered_asset_mng_fee_tax    DECIMAL    18    12  6   NULL
        //売却保有資産の手数料    transfered_asset_setup_fee    DECIMAL    18 12  6   NULL
        //売却保有資産の手数料消費税    transfered_asset_setup_fee_tax    DECIMAL    18  12  6   NULL
        //資産の簿価    transfered_asset_book_value    DECIMAL    18 12  6   NULL
        //削除フラグ    delete_flag    NUMBER    1    NotNull
        l_params.setDeleteFlag(BooleanEnum.FALSE);
        //約定経路区分    order_exec_route_div    VARCHAR2    1    NULL
        //更新者コード    last_updater    VARCHAR2    20    NULL
        //作成日付    created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp    DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }

    /**
     * 質問回答テーブル
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
     * 入金請求管理テーブル
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
     * 注文履歴テーブル
     * feq_order_action
     */
    public static FeqOrderActionParams getFeqOrderActionParams()
    {
        FeqOrderActionParams l_params = new FeqOrderActionParams();
        //注文履歴ＩＤ    order_action_id     NUMBER    18    NotNull
        l_params.setOrderActionId(123456789);
        //口座ＩＤ    account_id     NUMBER    18    NotNull
        l_params.setAccountId(31102102050L);        
        //補助口座ＩＤ    sub_account_id     NUMBER    18    NotNull
        l_params.setSubAccountId(33381251220301L);
        //注文ＩＤ    order_id     NUMBER    18    NotNull
        l_params.setOrderId(123456789);
        //注文単位ＩＤ    order_unit_id     NUMBER    18    NotNull
        l_params.setOrderUnitId(123456789);
        //市場ＩＤ    market_id     NUMBER    18    NULL
        l_params.setMarketId(123456);
        //注文種別    order_type     NUMBER    6    NotNull
        l_params.setOrderType(OrderTypeEnum.FEQ_BUY);
        //注文イベントタイプ    order_event_type     NUMBER    6    NotNull
        l_params.setOrderEventType(OrderEventTypeEnum.APPROVED);
        //注文単価    price     DECIMAL    18   12  6   NULL
        //執行条件    execution_condition_type     NUMBER    6    NULL
        //発注条件    order_condition_type     VARCHAR2    1    NULL
        //発注条件演算子    order_cond_operator     VARCHAR2    1    NULL
        //逆指値基準値    stop_order_price     DECIMAL    18  12  6   NULL
        //（W指値）訂正指値    w_limit_price     DECIMAL    18  12  6   NULL
        //注文失効日付    expiration_date     DATE        NULL
        //注文数量    quantity     DECIMAL    18    12  6   NotNull
        l_params.setQuantity(100);
        //市場と確認済みの指値    confirmed_price     DECIMAL    18   12  6   NULL
        //市場と確認済みの数量    confirmed_quantity     DECIMAL    18    12  6   NULL
        //約定数量    executed_quantity     DECIMAL    18   12  6   NULL
        //注文状態    order_status     NUMBER    6    NotNull
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        //注文失効ステータス    expiration_status     NUMBER    6    NotNull
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        //注文履歴番号    order_action_serial_no     NUMBER    8    NotNull
        l_params.setOrderActionSerialNo(1001);
        //約定単価    executed_price     DECIMAL    18  12  6   NULL
        //約定日時    exec_timestamp     DATE        NULL
        //銘柄ＩＤ    product_id     NUMBER    18    NotNull
        l_params.setProductId(123456789);
        //概算受渡代金    estimated_price     DECIMAL    18   12  6   NULL
        //概算受渡代金（外貨）    f_estimated_price     DECIMAL    18 12  6   NULL
        //注文訂正・取消区分    modify_cancel_type     VARCHAR2    1    NULL
        //注文経路区分    order_root_div     VARCHAR2    1    NULL
        //市場から確認済みの注文単価    confirmed_order_price     DECIMAL    18  12  6   NULL
        //市場から確認済みの概算受渡代金    confirmed_estimated_price     DECIMAL    18    12  6   NULL
        //市場から確認済みの概算受渡代金（外貨）    confirmed_f_estimated_price     DECIMAL    18  12  6   NULL
        //市場から確認済みの執行条件    confirmed_exec_condition_type     NUMBER    6    NULL
        //注文エラー理由コード    error_reason_code     VARCHAR2    4    NULL
        //更新者コード    last_updater     VARCHAR2    20    NULL
        //作成日付    created_timestamp     DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp     DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }

    /**
     * 振替請求受付キューテーブル
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
     * 振替入力通知キューテーブル
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
     * 累投取消受付キューテーブル
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
     * IPO申告テーブル
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
     * FX振替条件マスタ
     * fx_transfer_master
     */
    public static FxTransferMasterParams getFxTransferMasterParams()
    {
        FxTransferMasterParams l_params =
            new FxTransferMasterParams();
        //FXシステム条件ID    fx_system_id    NUMBER    18    NotNull
        l_params.setFxSystemId(123456789);
        //振替区分    transfer_div    VARCHAR2    1    NotNull
        l_params.setTransferDiv("1");
        //受渡日設定区分    delivery_date_div    VARCHAR2    2    NotNull
        l_params.setDeliveryDateDiv("1");
        //注文種別    order_type    NUMBER    6    NotNull
        l_params.setOrderType(OrderTypeEnum.ASSET_IN);
        //摘要コード    remark_code    VARCHAR2    4    NotNull
        l_params.setRemarkCode("71");
        //摘要名    remark_name    VARCHAR2    10    null
        l_params.setRemarkName("CFDﾍﾌﾘｶｴ");
        //入出金一覧取引区分    io_list_trade_div    VARCHAR2    4    null
        l_params.setIoListTradeDiv("5");
        //作成日付    created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp    DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }

    /**
     * 口座開設区分テーブル
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
     * 注文履歴テーブル
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
     * 振替請求注文キューテーブル
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
        //プロセスＩＤ  process_id  VARCHAR2  4
        l_processManagementParams.setProcessId("0006");
        //証券会社コード  institution_code  VARCHAR2  3
        l_processManagementParams.setInstitutionCode("0D");
        //部店コード  branch_code  VARCHAR2  3
        l_processManagementParams.setBranchCode("624");
        //処理区分  status  VARCHAR2  1
        l_processManagementParams.setStatus("1");
        //最終更新者  last_updater  VARCHAR2  20
        l_processManagementParams.setLastUpdater("jiddk");
        //最終更新時刻  last_updated_timestamp  DATE
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
     * 口座開設見込客一時テーブル
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
        l_expAccountOpenTempParams.setFamilyName("内藤　@四郎");
        l_expAccountOpenTempParams.setGivenName("太郎");
        l_expAccountOpenTempParams.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
        l_expAccountOpenTempParams.setGivenNameAlt1("Siro");
        l_expAccountOpenTempParams.setSex("1");
        l_expAccountOpenTempParams.setZipCode("100081");
        l_expAccountOpenTempParams.setAddressLine1("東京都");
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
     * 保証金自動振替停止テーブル(deposit_autotransfer_stop)
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
     * 顧客メールアドレス
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
     * メール種別
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
        l_hostAccRegVoucherParams.setAccountName("亜");
        l_hostAccRegVoucherParams.setAccountNameKana("あ");
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
     * 先物OPCSVファ@イルフォーマットテーブル(ifo_csv_file_format)
     */
//    public static IfoCsvFileFormatParams getIfoCsvFileFormatRow()
//    {
//        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
//            new IfoCsvFileFormatParams();
//
//        //証券会社コード
//        l_ifoCsvFileFormatParams.setInstitutionCode("0D");
//        //カラム番号
//        l_ifoCsvFileFormatParams.setColumnNumber(0);
//        //カラムラベル
//        l_ifoCsvFileFormatParams.setColumnLabel("central_module_c");
//        //作成日付
//        l_ifoCsvFileFormatParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//        //更新日付
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
     * 電子交付管理テーブル(ele_delivery_management)
     */
//    public static EleDeliveryManagementParams getEleDeliveryManagementRow()
//    {
//        EleDeliveryManagementParams l_params = new EleDeliveryManagementParams();
//        
//        l_params.setAccountId(333812512246L);
//        l_params.setInstitutionCode("0D");
//        l_params.setBranchCode("381");
//        l_params.setAccountCode("2512246");
//        l_params.setEleDelRegiFlag(0);//0：未済み　@1：済み
//        l_params.setCreatedTimestamp(
//            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
//        l_params.setLastUpdatedTimestamp(
//            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
//        
//        return l_params;
//    }
}@
