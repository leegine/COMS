head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.36.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioMarketRequestSenderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金・入出庫リクエスト送信サービスクラス(WEB3AioMarketRequestSenderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 王蘭芬(中訊) 新規作成
Revesion History : 2004/10/27 屈陽 (中訊) レビュー
Revesion History : 2004/12/06 周勇 (中訊) 残対応
Revesion History : 2005/01/18 玉岡 (SRA) 受入テスト障害U02724対応
Revesion History : 2005/01/18 玉岡 (SRA) 受入テスト障害U02728対応
Revesion History : 2006/01/12 韋念瓊 (中訊) 仕様変更・モデル433
Revesion History : 2006/05/08 韋念瓊 (中訊) 受入テスト障害U02836対応
Revesion History : 2006/07/12 韋念瓊 (中訊) 仕様変更 モデル598
Revesion History : 2006/08/01 鈴木(SCS)　@モデルNo.609・DB更新仕様 096,097対応
Revesion History : 2006/08/28 鈴木(SCS)　@モデルNo.632・DB更新仕様 108対応
Revesion History : 2006/08/31 車進 (中訊) モデルNo.618
Revesion History : 2006/09/27 韋念瓊 (中訊) DB更新仕様 No.120,121対応
Revesion History : 2006/10/18 車進 (中訊) モデルNo.624
Revesion History : 2006/10/19 車進 (中訊) モデルNo.660
Revesion History : 2006/10/19 車進 (中訊) DB更新仕様 No.124対応
Revesion History : 2006/10/24 徐宏偉 (中訊) モデルNo.676　@DB更新仕様 No.128対応
Revesion History : 2006/10/24 徐宏偉 (中訊) モデルNo.674
Revesion History : 2006/10/26 車進 (中訊) DB更新仕様 No.126対応
Revesion History : 2006/11/06 鈴木(SCS)　@DB更新仕様 No.133対応
Revesion History : 2007/02/10 徐宏偉(中訊)　@モデルNo.706,DB更新仕様 No.135対応
Revesion History : 2007/03/12 何文敏(中訊)　@モデルNo.711,No.712,No.714  DB更新仕様 No.136,No.137対応
Revesion History : 2007/03/12 何文敏(中訊)　@DB更新仕様 No.138
Revesion History : 2007/03/28 何文敏(中訊)　@実装の問題  No.010
Revesion History : 2007/03/29 何文敏(中訊)　@DB更新仕様 No.139
Revesion History : 2007/07/12 孫洪江(中訊) 仕様変更モデルNo.737 No.738
Revesion History : 2007/07/28 孟亜南(中訊) 仕様変更モデルNo.747
Revesion History : 2008/09/24 王志葵(中訊) 仕様変更モデルNo.991,1074 ＤＢ更新仕様 No.176,177
Revesion History : 2008/11/12 大嶋(SCS) DB更新仕様 No.203
Revesion History : 2008/12/03 大嶋(SCS) DB更新仕様 No.205
Revesion History : 2008/12/23 王志葵(中訊) DB更新仕様 No.206
Revesion History : 2009/03/12 王志葵(中訊) 仕様変更モデルNo.1114 ＤＢ更新仕様 No.209,210,212,222
*/

package webbroker3.aio.marketadaptor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioMarketRequestSenderService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.market.messages.AioNewOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.aio.WEB3AioProductManager;
import webbroker3.aio.data.BankCashTransferStatusDao;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.data.CompBankConditionDao;
import webbroker3.aio.data.CompBankConditionRow;
import webbroker3.aio.data.HostCashTransOrderParams;
import webbroker3.aio.data.HostForeignCashTransOrderParams;
import webbroker3.aio.data.HostMrgsecTransferParams;
import webbroker3.aio.data.HostPaymentOrderDao;
import webbroker3.aio.data.HostPaymentOrderPK;
import webbroker3.aio.data.HostPaymentOrderParams;
import webbroker3.aio.data.HostPaymentOrderRow;
import webbroker3.aio.data.HostTransferOrderDao;
import webbroker3.aio.data.HostTransferOrderPK;
import webbroker3.aio.data.HostTransferOrderParams;
import webbroker3.aio.data.HostTransferOrderRow;
import webbroker3.aio.define.WEB3AioDescriptionDef;
import webbroker3.aio.define.WEB3AioOriginalTransferDateBlankDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioDeliveryTypeDef;
import webbroker3.common.define.WEB3AioHostCommodityDef;
import webbroker3.common.define.WEB3AioHostStatusDef;
import webbroker3.common.define.WEB3AioPaymentApplicationDivDef;
import webbroker3.common.define.WEB3AioTransferFlagDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3CurrencyCodeDef;
import webbroker3.common.define.WEB3DepositDef;
import webbroker3.common.define.WEB3ForceTransferDef;
import webbroker3.common.define.WEB3ForeignCashRemarkCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3InputUnitDef;
import webbroker3.common.define.WEB3InterLockDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PaymentApplyTriggerDef;
import webbroker3.common.define.WEB3RemarkCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.FinInstitutionDao;
import webbroker3.gentrade.data.FinInstitutionPK;
import webbroker3.gentrade.data.FinInstitutionRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (入出金・入出庫リクエスト送信サービス)<BR>
 * 入出金・入出庫リクエスト送信サービスクラス<BR>
 * （AioMarketRequestSenderServiceの実装クラス）<BR>
 *
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AioMarketRequestSenderServiceImpl 
    implements AioMarketRequestSenderService 
{

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioMarketRequestSenderServiceImpl.class);

    /**
     * @@roseuid 415A471501A3
     */
    public WEB3AioMarketRequestSenderServiceImpl() 
    {
     
    }
    
    /**
     * (新規注文送信)<BR>
     * （send(AioNewOrderMarketRequestMessage)の実装） <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（リクエスト送信）新規注文送信」参照。 <BR>
     * @@param l_cashTransOrderRequest - (入出金注文リクエスト)<BR>
     * 
     * @@return MarketRequestSendResult
     * @@roseuid 40E2436001BD
     */
    public MarketRequestSendResult send(AioNewOrderMarketRequestMessage l_cashTransOrderRequest) 
    {
        
        final String l_strMethodName = "send("
            + "AioNewOrderMarketRequestMessage l_cashTransOrderRequest)";
        log.entering(l_strMethodName);
        if (l_cashTransOrderRequest == null)
        {
            log.debug("パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // 1.1. getOrderId( )
        long l_lngOrderId = l_cashTransOrderRequest.getOrderId();
        // 1.2. 注文インスタンスを生成する
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.AIO);
        AioOrderManager l_orderManager = 
            (AioOrderManager)l_tradingModule.getOrderManager();
        //1.3. getOrderUnits( ) ---注文単位オブジェクトを取得する。
        // ※リストの1番目の要素を取得する。
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            log.debug("Error In  getOrderUnits() with Order_Id = " + l_lngOrderId);
            WEB3BaseException l_baseEx = new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + l_strMethodName);
            ProcessingResult l_sendResult =
                ProcessingResult.newFailedResultInstance(l_baseEx.getErrorInfo());
            MarketRequestSendResult l_returnResult = 
                DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
            return l_returnResult;
        }
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];
        try
        {
            //1.4 注文単位.注文種別 == 1001：出金注文 and 出金申込区分 == null or mf（投信）
            //     and 注文経路区分 != 9：HOST の場合
            AioOrderUnitRow l_orderUnitRow = 
                (AioOrderUnitRow)l_aioOrderUnit.getDataSourceObject();
            if (OrderTypeEnum.CASH_OUT.equals(l_orderUnitRow.getOrderType()) 
                && (l_orderUnitRow.getPaymentApplicationDiv() == null
                || WEB3AioPaymentApplicationDivDef.MF.equals(l_orderUnitRow.getPaymentApplicationDiv()))
                && !WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
            {
                //1.4.1 新規出金注文送信(AioOrderUnit)---- 出金注文の送信処理を行う。
                this.openCashoutOrderSend(l_aioOrderUnit);
            }
            //1.5 注文単位.注文種別 == 1002：入金注文 
            //     and 注文経路区分 != 9：HOST の場合
            if (OrderTypeEnum.CASH_IN.equals(l_orderUnitRow.getOrderType()) 
                    && !WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
            {
                //1.5.1 新規入金注文送信(AioOrderUnit)---- 入金注文の送信処理を行う。 。
                this.openCashinOrderSend(l_aioOrderUnit);
            }
            //（注文単位.注文種別 == 1005（振替注文（預り金から信用保証金））
            //                or == 1006：振替注文(信用保証金から預り金)
            //                or == 1007：振替注文(預り金から株先証拠金)
            //                or == 1008：振替注文(株先証拠金から預り金)
            //                or == 1011（為替保証金振替注文（預り金から為替保証金））
            //                or == 1012（為替保証金振替注文（為替保証金から預り金））
            //                or == 1013（外国株式振替注文（預り金から外国株式口座）））
            //                or == 1021（CFD振替注文（預り金からCFD口座））
            //                or == 1022（CFD振替注文（CFD口座から預り金））
            //     and 注文経路区分 != 9：HOST の場合
            if ((OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD.equals(l_orderUnitRow.getOrderType()))
                && !WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
            {
                //1.6.1 新規振替注文送信(AioOrderUnit)---- 振替注文の送信処理を行う。
                this.openTransferOrderSend(l_aioOrderUnit);
            } 
            
            //============remain zhou-yong No.2 begin ===========
            
            //1.7) （ 注文単位.注文種別 = 1009（証券振替注文（保護預りから代用有価証券）） or
            //        注文単位.注文種別 = 1010（証券振替注文（代用有価証券から保護預り）） ） and
            //        注文単位.注文経路区分 != 9（HOST)
            //        の場合
            if((OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES.equals(l_orderUnitRow.getOrderType())
                || OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT.equals(l_orderUnitRow.getOrderType()))
                && !WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()) )
            {
                //新規証券振替注文送信(AioOrderUnit)
                //アイテムの定義
                //証券振替注文の送信処理を行う。
                //[引数] 
                //注文単位： 注文単位オブジェクト 
                this.openSecurityTransferOrderSend(l_aioOrderUnit);

            }
            
            //============remain zhou-yong No.2 end ===========            
            
            // 新規注文市場リクエストメッセージ送信の各メソッドが正常終了した場合、
            //  DefaultMarketRequestSendResult.newSuccessResultInstance()の戻り値を返す。
            MarketRequestSendResult l_sendResult = 
                DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
            return l_sendResult;
        }
        catch (WEB3BaseException l_ex)
        {
            // 新規注文市場リクエストメッセージ送信の各メソッドが例外をスローした場合
            // －ProcessingResult.newFailedResultInstance()をコールしてProcessingResultオブジェクトを生成する。
            // －DefaultMarketRequestSendResult.newFailedResultInstance()の戻り値を返す。
            ProcessingResult l_sendResult =
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo());
            MarketRequestSendResult l_returnResult = 
                DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
            return l_returnResult;
        }
        finally
        {
            log.exiting(l_strMethodName);
        }
    }
    
    /**
     * (取消注文送信)<BR>
     * （send(CancelOrderMarketRequestMessage, boolean)の実装） <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（リクエスト送信）取消注文送信」参照。 <BR>
     * @@param l_orderCancelRequest - (注文取消リクエスト)<BR>
     * 
     * @@param l_isMarketNoSend - (is市場未送信)<BR>
     * 市場未送信かどうかのフラグ<BR>
     * 
     * @@return MarketRequestSendResult
     * @@roseuid 40E2436800A4
     */
    public MarketRequestSendResult send(
            CancelOrderMarketRequestMessage l_orderCancelRequest, 
            boolean l_isMarketNoSend) 
    {
        final String l_strMethodName = "send("
            + "CancelOrderMarketRequestMessage l_orderCancelRequest, "
            + "boolean l_isMarketNoSend)";
        log.entering(l_strMethodName);
        if (l_orderCancelRequest == null)
        {
            log.debug("パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // 1.1 getSubAccount( ) ---- 補助口座オブジェクトを取得する。
        SubAccount l_subAccount = l_orderCancelRequest.getSubAccount();
        // 1.2. getOrderId( )
        long l_lngOrderId = l_orderCancelRequest.getOrderId();
        // 1.3. 注文インスタンスを生成する
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.AIO);
        AioOrderManager l_orderManager = 
            (AioOrderManager)l_tradingModule.getOrderManager();
        //1.4. getOrderUnits( ) ---注文単位オブジェクトを取得する。
        // ※リストの1番目の要素を取得する。
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            log.debug("Error In  getOrderUnits() with Order_Id = " + l_lngOrderId);
            WEB3BaseException l_baseEx = new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + l_strMethodName);
            ProcessingResult l_sendResult =
                ProcessingResult.newFailedResultInstance(l_baseEx.getErrorInfo());
            MarketRequestSendResult l_returnResult = 
                DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
            return l_returnResult;
        }
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];
        //1.5 getDataSourceObject( ) ---- 注文単位行オブジェクトを取得する。
        AioOrderUnitRow l_orderUnitRow = 
            (AioOrderUnitRow)l_aioOrderUnit.getDataSourceObject();
        //1.6 注文単位.注文カテゴリ == 9（入出金） の場合
        if (OrderCategEnum.CASH_IN_OUT.equals(l_orderUnitRow.getOrderCateg()))
        {
            //１）出金請求注文キューParamsインスタンスを生成する。 
            HostPaymentOrderParams l_params = new HostPaymentOrderParams();
            //２）出金請求注文キューParamsにプロパティをセットする。 
            //データコード request_code        出金注文：GI801
            l_params.setRequestCode(WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER);
            l_params.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            l_params.setBranchCode(l_subAccount.getMainAccount().getBranch().getBranchCode());
            l_params.setAccountCode(l_subAccount.getMainAccount().getAccountCode());
            l_params.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());
            // deleteRow(Row() --- 取消された注文に該当する出金請求注文キューテーブルのレコードを削除する。
            try
            {
                // test log
                log.debug("取消注文送信#出金請求注文キューParams");
                
                HostPaymentOrderDao.findRowByPk(
                    (HostPaymentOrderPK) l_params.getPrimaryKey());

                WEB3DataAccessUtility.deleteRow(l_params);
            }
            catch (DataFindException l_ex)
            {
                //障害対応 NO_U01828
                //HostPaymentOrderDao.findRowByPk()により該当データが存在しない場合
                //取消を行わずに次の処理を行う（サービス利用対応）
                log.debug("HostPaymentOrderDao.findRowByPk() - DataFindException -");
            }
            catch (DataQueryException l_ex)
            {
                // 新規注文市場リクエストメッセージ送信の各メソッドが例外をスローした場合
                // －ProcessingResult.newFailedResultInstance()を
                //      コールしてProcessingResultオブジェクトを生成する。
                // －DefaultMarketRequestSendResult.newFailedResultInstance()の戻り値を返す。
                log.error("Error In 取消された注文に" +
                        "該当する出金請求注文キューテーブルのレコードを削除する。", l_ex);
                
                WEB3BaseException l_baseEx = new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + l_strMethodName,
                        l_ex.getMessage(),
                        l_ex);
                ProcessingResult l_sendResult =
                    ProcessingResult.newFailedResultInstance(l_baseEx.getErrorInfo());
                MarketRequestSendResult l_returnResult = 
                    DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
                return l_returnResult;
            }
            catch (DataNetworkException l_ex)
            {
                log.error("Error In 取消された注文に" +
                        "該当する出金請求注文キューテーブルのレコードを削除する。", l_ex);
                WEB3BaseException l_baseEx = new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + l_strMethodName,
                        l_ex.getMessage(),
                        l_ex);
                ProcessingResult l_sendResult =
                    ProcessingResult.newFailedResultInstance(l_baseEx.getErrorInfo());
                MarketRequestSendResult l_returnResult = 
                    DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
                return l_returnResult;
            }
        }
        
        //1.7 注文単位.注文カテゴリ == 13（振替）or 
        //    注文単位.注文カテゴリ == 15（為替保証金振替）
        if (OrderCategEnum.CASH_TRANSFER.equals(l_orderUnitRow.getOrderCateg()) ||
            OrderCategEnum.FX.equals(l_orderUnitRow.getOrderCateg()))
        {
            //１）振替請求注文キューParamsのインスタンスを生成する。
            HostTransferOrderParams l_hostTransferOrderParams = new HostTransferOrderParams();
            //２）振替請求注文キューParamsに以下のプロパティをセットする。 
            //データコード = "GI806"(保証金振替請求)
            l_hostTransferOrderParams.setRequestCode(WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ORDER);
            //証券会社コード = 補助口座.getInstitution().getInstitutionCode()の戻り値
            l_hostTransferOrderParams.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            //部店コード = 補助口座.get取引店().getBranchCode()の戻り値
            l_hostTransferOrderParams.setBranchCode(l_subAccount.getMainAccount().getBranch().getBranchCode());
            //顧客コード = 補助口座.getMainAccount().getAccountCode()の戻り値
            l_hostTransferOrderParams.setAccountCode(l_subAccount.getMainAccount().getAccountCode());
            //識別コード = 注文単位Params.識別コード
            l_hostTransferOrderParams.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());

            log.debug("証券会社コード = " + l_subAccount.getInstitution().getInstitutionCode());
            log.debug("部店コード = " + l_subAccount.getMainAccount().getBranch().getBranchCode());
            log.debug("顧客コード = " + l_subAccount.getMainAccount().getAccountCode());
            log.debug("識別コード = " + l_orderUnitRow.getOrderRequestNumber());
            
            // deleteRow(Row() --- 取消された注文に該当する出金請求注文キューテーブルのレコードを削除する。
            try
            {
                // test log
                //log.debug("取消注文送信#振替請求注文キューParams = " + 
                //        HostTransferOrderDao.findRowByPk(
                //        (HostTransferOrderPK)l_hostTransferOrderParams.getPrimaryKey()));
                
				HostTransferOrderDao.findRowByPk(
					(HostTransferOrderPK)l_hostTransferOrderParams.getPrimaryKey());
                   
                //1.7.1 取消された注文に該当する振替請求注文キューテーブルのレコードを削除する。
                WEB3DataAccessUtility.deleteRow(l_hostTransferOrderParams);
            }
			catch (DataFindException l_ex)
			{
				//HostTransferOrderDao.findRowByPk()により該当データが存在しない場合
				//取消を行わずに次の処理を行う
				log.debug("HostTransferOrderDao.findRowByPk() - DataFindException -");
			}
            catch (DataQueryException l_ex)
            {
                // 新規注文市場リクエストメッセージ送信の各メソッドが例外をスローした場合
                // －ProcessingResult.newFailedResultInstance()をコールしてProcessingResultオブジェクトを生成する。
                // －DefaultMarketRequestSendResult.newFailedResultInstance()の戻り値を返す。
                log.error("Error In 取消された注文に" +
                        "該当する振替請求注文キューテーブルのレコードを削除する。", l_ex);
                WEB3BaseException l_baseEx = new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + l_strMethodName,
                        l_ex.getMessage(),
                        l_ex);
                ProcessingResult l_sendResult =
                    ProcessingResult.newFailedResultInstance(l_baseEx.getErrorInfo());
                MarketRequestSendResult l_returnResult = 
                    DefaultMarketRequestSendResult.newFailedResultInstance(
                        l_sendResult);
                return l_returnResult;
            }
            catch (DataNetworkException l_ex)
            {
                log.error("Error In 取消された注文に" +
                        "該当する出金請求注文キューテーブルのレコードを削除する。", l_ex);
                WEB3BaseException l_baseEx = new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + l_strMethodName,
                        l_ex.getMessage(),
                        l_ex);
                ProcessingResult l_sendResult =
                    ProcessingResult.newFailedResultInstance(l_baseEx.getErrorInfo());
                MarketRequestSendResult l_returnResult = 
                    DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
                return l_returnResult;
            }
        }
        // 新規注文市場リクエストメッセージ送信の各メソッドが正常終了した場合、
        //  DefaultMarketRequestSendResult.newSuccessResultInstance()の戻り値を返す。
        MarketRequestSendResult l_sendResult = 
            DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
        return l_sendResult;
    }
    
    /**
     * (create入金注文キューデータ)<BR>
     * 入金注文のキューデータの生成（DB登録）を行う。<BR>
     * <BR>
     * １）入出金伝票キューParamsインスタンスを生成する。<BR>
     * <BR>
     * ２）入出金伝票キューParamsにプロパティをセットする。<BR>
     * <BR>
     *   ※詳細は、DB更新仕様「入出金伝票キューテーブル.xls」参照<BR>
     * <BR>
     * ※※※<BR>
     * 入出金注文単位.注文経路が”入金連携”の場合 <BR>
     * <BR>
     * ・金融機@関テーブルを以下の条件で検索。 <BR>
     * [条件] <BR>
     * 証券会社コード = 証券会社.証券会社コード <BR>
     * 金融機@関コード = 入出金注文単位.振替記述 <BR>
     * <BR>
     * 以下のプロパティをセット <BR>
     * ・入出金伝票キューParams.入出金方法@ <BR>
     * = 金融機@関テーブル.入出金伝票キュー用入出金方法@ <BR>
     * ・入金伝票キューParams.相手コード <BR>
     * = 金融機@関テーブル.入出金伝票キュー用相手コード <BR>
     * <BR>
     * ※詳細は、DB更新仕様「銀行入金通知_入出金伝票キューテーブル.xls」参照 <BR>
     * ３）入出金伝票キューParamsの内容をDBに登録する。<BR>
     * <BR>
     *   WEB3DataAccessUtility.insertRow()<BR>
     * <BR>
     *   [引数]<BR>
     *   入出金伝票キューParams<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40F647780100
     */
    protected void createCashinOrderQueueData(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String l_strMethodName = "createCashinOrderQueueData"
            + "(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);
        //１）入出金伝票キューParamsインスタンスを生成する。
        HostCashTransOrderParams l_params = new HostCashTransOrderParams();
        //２）入出金伝票キューParamsにプロパティをセットする。 
        //※詳細は、DB更新仕様「入出金伝票キューテーブル.xls」参照 
        //データコード:  入金注文：GI803
        l_params.setRequestCode(WEB3HostRequestCodeDef.AIO_SLIP_SERVE);
        try 
        {
            AccountManager l_accMgr = (AccountManager) GtlUtils.getAccountManager();
            log.debug(">>>>>>>>>WEB3GentradeAccountManager");
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_orderUnit.getAccountId());
            log.debug(">>>>>>>>>MainAccount");
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode =" + l_strInstitutionCode);
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            log.debug("l_strBranchCode =" + l_strBranchCode);
            String l_strAccountCode = l_mainAccount.getAccountCode();
            log.debug("l_strAccountCode =" + l_strAccountCode);
            
            //証券会社コード:注文単位.部店ＩＤに該当する部店.証券会社コード
            l_params.setInstitutionCode(l_strInstitutionCode);
            //部店コード:   注文単位.部店ＩＤに該当する部店.部店コード
            l_params.setBranchCode(l_strBranchCode);
            //顧客コード:   注文単位.顧客ＩＤに該当する顧客.顧客コード
            l_params.setAccountCode(l_strAccountCode);
            //扱者コード:   ブランク
            l_params.setTraderCode("     ");
            //識別コード:   注文単位.識別コード
            AioOrderUnitRow l_aioOrderUnitRow = 
                (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
            l_params.setOrderRequestNumber(l_aioOrderUnitRow.getOrderRequestNumber());
            
            //出金額:       0
            l_params.setDebitAmount(0);
            
            //入金額:       注文単位.数量
            l_params.setCreditAmount((long)l_aioOrderUnitRow.getQuantity());
            
            if (!WEB3OrderRootDivDef.CASH_IN_COOPERATION.equals(l_aioOrderUnitRow.getOrderRootDiv()))
            {
                //入出金方法@:   会社別決済機@関条件テーブル.入出金方法@
                // 金融機@関連携入出金状況テーブル
                BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                    BankCashTransferStatusDao.findRowByInstitutionCodeBranchCodeOrderRequestNumber(
                            l_strInstitutionCode, l_strBranchCode, l_aioOrderUnitRow.getOrderRequestNumber());
                // test log
                log.debug("金融機@関連携入出金状況テーブル BankCashTransferStatusRow = " + l_bankCashTransferStatusRow);
                // 会社別決済機@関条件テーブル
                CompBankConditionRow l_compBankConditionRow = 
                    CompBankConditionDao.findRowByInstitutionCodeBranchCodePaySchemeId(
                            l_strInstitutionCode, l_strBranchCode, l_bankCashTransferStatusRow.getPaySchemeId());
                // test log
                log.debug("会社別決済機@関条件テーブル CompBankConditionRow = " + l_compBankConditionRow);
                l_params.setCashTransferType(l_compBankConditionRow.getPaymentMethod());
                //相手:         会社別決済機@関条件テーブル.相手 
                l_params.setSonarCode(l_compBankConditionRow.getSonarCode());
            }
            
            //送金料:       0
            l_params.setRemittanceFee(0);
            
            //受渡方法@:     1'：店頭
            l_params.setDeliveryType(WEB3AioDeliveryTypeDef.OTC);
            //先発精算日:   ブランク
            l_params.setAdvanceSettlementDate("    ");
            //立替区分:     ブランク
            l_params.setPayTemporarilyDiv(" ");
            //原入出金:     "注文単位.振替予定日 < 注文単位.受渡日の場合、振替予定日(MMDD)
            //              ※振替予定日が非営業日の場合は、その翌営業日と比較
            //              それ以外の場合、ブランク"
            Timestamp l_dteEstTransferDate = l_aioOrderUnitRow.getEstTransferDate();
            String l_strDateType = 
                WEB3GentradeTradingTimeManagement.getBizDateType(l_dteEstTransferDate);
            // test log
            log.debug("振替予定日l_dteEstTransferDateの営業日区分　@=　@" + l_strDateType);
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strDateType))
            {
                l_dteEstTransferDate = new WEB3GentradeBizDate(l_dteEstTransferDate).roll(1);
            }
            log.debug("new 振替予定日l_dteEstTransferDate　@=　@" + l_dteEstTransferDate);
            if (WEB3DateUtility.compareToDay(l_dteEstTransferDate, l_aioOrderUnitRow.getDeliveryDate()) < 0)
            {
                l_params.setOrderOriginalDate(
                        WEB3DateUtility.formatDate(l_dteEstTransferDate, "yyyyMMdd").substring(4));
            }
            else
            {
                l_params.setOrderOriginalDate("    ");
            }
            //取消区分:     ランク  
            l_params.setCancelDiv(" ");
            //証書発行区分:  ブランク                                                                     
            l_params.setRecieptDiv(" ");
            //保証金区分:   ブブランク                                                                        
            l_params.setGuaranteeDiv(" ");
            //受注日時: ThreadLocalSystemAttributesRegistry.getAttributes(”xblocks.gtl.attributes.systemtimestamp”)の戻り値                                                                     
            l_params.setOrderedTimestamp(GtlUtils.getSystemTimestamp());
            //処理区分:     0：未処理 
            l_params.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);
            
            //※※※ 
            //入出金注文単位.注文経路が”入金連携”の場合 

            if (WEB3OrderRootDivDef.CASH_IN_COOPERATION.equals(l_aioOrderUnitRow.getOrderRootDiv()))
            {                
                //・金融機@関テーブルを以下の条件で検索。 
                //[条件] 
                //証券会社コード = 証券会社.証券会社コード 
                //金融機@関コード = 入出金注文単位.振替記述 
                FinInstitutionRow l_finInstitutionRow = FinInstitutionDao.findRowByPk(
                    l_strInstitutionCode,
                    l_aioOrderUnitRow.getDescription());
    
                //※詳細は、DB更新仕様「銀行入金通知_入出金伝票キューテーブル.xls」参照 
                
                //入出金方法@ = 金融機@関テーブル.入出金伝票キュー用入出金方法@
                l_params.setCashTransferType(l_finInstitutionRow.getCashTransferType());
                
                //原入出金 = 注文単位.振替予定日 < 注文単位.受渡日の場合、振替予定日(MMDD)
                //          ※振替予定日が非営業日の場合は、その翌営業日と比較
                //          それ以外の場合、ブランク
                
                Date l_datEstTransferDate = l_aioOrderUnitRow.getEstTransferDate();
                Date l_datDeliveryDate = l_aioOrderUnitRow.getDeliveryDate();
                
                String l_strEstTransferDateType = 
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        new Timestamp(l_datEstTransferDate.getTime()));
    
                Date l_datCompareDate = null;
                    
                if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strEstTransferDateType))
                {
                    l_datCompareDate = new WEB3GentradeBizDate(
                        new Timestamp(l_datEstTransferDate.getTime())).roll(1);               
                }
                else
                {
                    l_datCompareDate = l_datEstTransferDate;
                }
                
                if (WEB3DateUtility.compareToDay(l_datCompareDate, l_datDeliveryDate) < 0)
                {
                    String l_strTransferDateMD = 
                        WEB3DateUtility.formatDate(l_datCompareDate, "MMdd");
                    
                    l_params.setOrderOriginalDate(l_strTransferDateMD);
                }
                else
                {
                    l_params.setOrderOriginalDate("    ");
                }
                
                //相手 = 金融機@関テーブル.入出金伝票キュー用入金連携相手コード
                l_params.setSonarCode(l_finInstitutionRow.getCashTransferSonarCode());                
                          
            }
            
            //３）入出金伝票キューParamsの内容をDBに登録する。 
            //WEB3DataAccessUtility.insertRow() 
            //[引数] 
            //入出金伝票キューParams 
            WEB3DataAccessUtility.insertRow(l_params);
        } 
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMainAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In 入出金伝票キューParamsの内容をDBに登録する", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In 入出金伝票キューParamsの内容をDBに登録する", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        log.exiting(l_strMethodName);
    }
    
    /**
     * (create出金注文キューデータ)<BR>
     * 出金注文のキューデータの生成（DB登録）を行う。<BR>
     * <BR>
     * １）出金請求注文キューParamsインスタンスを生成する。<BR>
     * <BR>
     * ２）出金請求注文キューParamsにプロパティをセットする。<BR>
     * <BR>
     *   ※詳細は、DB更新仕様「出金請求注文キューテーブル.xls」参照<BR>
     * <BR>
     * ３）出金請求注文キューParamsの内容をDBに登録する。<BR>
     * <BR>
     *   WEB3DataAccessUtility.insertRow()<BR>
     * <BR>
     *   [引数]<BR>
     *   出金請求注文キューParams<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)注文単位オブジェクト<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40F6493C0093
     */
    protected void createCashoutOrderQueueData(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String l_strMethodName = "createCashoutOrderQueueData"
            + "(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);
        try
        {
            //１）出金請求注文キューParamsインスタンスを生成する。 
            HostPaymentOrderParams l_params = new HostPaymentOrderParams();
            //２）出金請求注文キューParamsにプロパティをセットする。 
            //※詳細は、DB更新仕様「出金請求注文キューテーブル.xls」参照 (出金申込_出金請求注文キューテーブル.xls)
            //データコード request_code        出金注文：GI801
            l_params.setRequestCode(WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER);
    
            AccountManager l_accMgr = (AccountManager) GtlUtils.getAccountManager();
            log.debug(">>>>>>>>>WEB3GentradeAccountManager");
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_orderUnit.getAccountId());
            log.debug(">>>>>>>>>MainAccount");
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode =" + l_strInstitutionCode);
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            log.debug("l_strBranchCode =" + l_strBranchCode);
            String l_strAccountCode = l_mainAccount.getAccountCode();
            log.debug("l_strAccountCode =" + l_strAccountCode);
            
            //証券会社コードinstitution_code    注文単位.部店ＩＤに該当する部店.証券会社コード                                                                       
            l_params.setInstitutionCode(l_strInstitutionCode);
            //部店コード   branch_code         注文単位.部店ＩＤに該当する部店.部店コード                                                                        
            l_params.setBranchCode(l_strBranchCode);
            //顧客コード   account_code        注文単位.顧客ＩＤに該当する顧客.顧客コード                                                                        
            l_params.setAccountCode(l_strAccountCode);
            //扱者コード   trader_code         ブランク
			l_params.setTraderCode("     ");                                                                        
            //識別コード   order_request_number注文単位.識別コード                                                                        
            AioOrderUnitRow l_aioOrderUnitRow = 
                (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
            l_params.setOrderRequestNumber(l_aioOrderUnitRow.getOrderRequestNumber());
            //出金額      payment_amount      注文単位.数量                                                                      
            l_params.setPaymentAmount((int)l_aioOrderUnitRow.getQuantity());
            //振込予定日   est_transfer_date   注文単位.振替予定日                                                                        
            l_params.setEstTransferDate(l_aioOrderUnitRow.getEstTransferDate());
            //受注日時     ordered_timestamp   現在時刻（システムタイムスタンプ）                                                                        
            l_params.setOrderedTimestamp(GtlUtils.getSystemTimestamp());
            //処理区分     status              3:未処理    
            l_params.setStatus(WEB3AioHostStatusDef.NOT_DEAL);

            //Test log
            log.debug("出金請求注文キューParamsの内容 = " + l_params);
            //３）出金請求注文キューParamsの内容をDBに登録する。 
            //
            //WEB3DataAccessUtility.insertRow() 
            //
            //[引数] 
            //出金請求注文キューParams 
            WEB3DataAccessUtility.insertRow(l_params);
        } 
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMainAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In 出金請求注文キューParamsの内容をDBに登録する", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In 出金請求注文キューParamsの内容をDBに登録する", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        log.exiting(l_strMethodName);
    }
    
    /**
     * (isリアルタイムトリガ発行)<BR>
     * 該当する証券会社がリアルタイムでトリガ発行をするという設定<BR>
     * になっているかどうかとチェックする。<BR>
     * <BR>
     * １）証券会社テーブルのレコードを取得する。<BR>
     * <BR>
     *   部店（ 引数.部店ID ）.getInstitution().getDataSourceObject()<BR>
     * <BR>
     * ２）証券会社Params.出金申込トリガ発行 = "2"（リアルタイム実施）<BR>
     * の場合は、trueを返す。<BR>
     *    それ以外の場合は、falseを返す。<BR>
     * @@param l_lngBranchId - (部店ID)<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@return boolean
     * @@roseuid 4121BCA800A4
     */
    protected boolean isSubmitRealTimeTrigger(long l_lngBranchId)
        throws WEB3BaseException
    {
        final String l_strMethodName = "isSubmitRealTimeTrigger"
            + "(String l_strBranchId) ";
        log.entering(l_strMethodName);
        try
        {
            //１）証券会社テーブルのレコードを取得する。
            AccountManager l_accountManager = GtlUtils.getAccountManager();            
            Branch l_branch = l_accountManager.getBranch(l_lngBranchId);
            InstitutionRow l_institutionRow = 
                (InstitutionRow)l_branch.getInstitution().getDataSourceObject();
            //２）証券会社Params.出金申込トリガ発行 = "2"（リアルタイム実施）の場合は、trueを返す。
            if (WEB3PaymentApplyTriggerDef.REAL_ENFORCEMENT.equals(
                    l_institutionRow.getPaymentApplyTrigger()))
            {
                log.exiting(l_strMethodName);
                return true;
            }
            // それ以外の場合は、falseを返す。
            log.exiting(l_strMethodName);
            return false;
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In 証券会社テーブルのレコードを取得する ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (update処理区分)<BR>
     * 出金請求注文キューテーブルの処理区分を'0'（処理中）に、<BR>
     * 注文単位テーブルのMQステータスを'1'（送信済み）に更新する。<BR>
     * <BR>
     * １）出金請求注文キューテーブルの更新<BR>
     * <BR>
     * １－１）以下の条件で、出金請求注文キューテーブルからレコードを取得する。<BR>
     * <BR>
     *   [検索条件]<BR>
     *   データコード： "GI801"<BR>
     *   証券会社コード： 引数.証券会社コード<BR>
     *   部店コード： 引数.部店コード<BR>
     *   顧客コード： 引数.顧客コード<BR>
     *   識別コード： 引数.識別コード<BR>
     *   処理区分： '3'（未処理）<BR>
     * <BR>
     * １－２）処理区分に'0'をセットする。<BR>
     * <BR>
     * １－３）該当レコードを更新する。<BR>
     * <BR>
     * ２）注文単位テーブルの更新<BR>
     * <BR>
     * ２－１）以下の条件で、注文単位テーブルからレコードを取得する。<BR>
     * <BR>
     *   [検索条件]<BR>
     *   注文単位ID： 引数.注文単位ID<BR>
     * <BR>
     * ２－２）MQステータスに'1'を、更新日付にシステムタイムスタンプをセットする。<BR>
     * <BR>
     * ２－３）該当レコードを更新する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * @@param l_strOrderRequestNumber - (識別コード)<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4129E25302ED
     */
    public void updateStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strOrderRequestNumber, 
            long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String l_strMethodName = "updateStatus(String l_strInstitutionCode,"
            + "String l_strBranchCode, String l_strAccountCode, "
            + "String l_strOrderRequestNumber, long l_lngOrderUnitId)";
        log.entering(l_strMethodName);
        try
        {
            // １）出金請求注文キューテーブルの更新
            // １－１）以下の条件で、出金請求注文キューテーブルからレコードを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisHostPaymentOrderRows = l_queryProcessor.doFindAllQuery(
                HostPaymentOrderRow.TYPE,
                "request_code=? and institution_code=? and branch_code=? "
                + "and account_code=? and order_request_number=? and status =? ",
                null,
                new Object[] { WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER,
                        l_strInstitutionCode, l_strBranchCode, 
                        l_strAccountCode, l_strOrderRequestNumber, 
                        WEB3AioHostStatusDef.NOT_DEAL});
            if (l_lisHostPaymentOrderRows.size() == 1)
            {
                HostPaymentOrderRow l_hostPaymentOrderRow = 
                    (HostPaymentOrderRow) l_lisHostPaymentOrderRows.get(0);
                HostPaymentOrderParams l_hostPaymentOrderParams = 
                    new HostPaymentOrderParams(l_hostPaymentOrderRow);
                // １－２）処理区分に'0'をセットする。
                l_hostPaymentOrderParams.setStatus("0");
                // １－３）該当レコードを更新する。
                l_queryProcessor.doUpdateQuery(l_hostPaymentOrderParams);
            }
            // ２）注文単位テーブルの更新
            // ２－１）以下の条件で、注文単位テーブルからレコードを取得する。
            //[検索条件] 
            //注文単位ID： 引数.注文単位ID 
            AioOrderUnitRow l_orderUnitRow = 
                AioOrderUnitDao.findRowByPk(l_lngOrderUnitId);
            log.debug("注文単位テーブルRow := " + l_orderUnitRow);
            
            AioOrderUnitParams l_orderUnitParams = new AioOrderUnitParams(l_orderUnitRow);
            //２－２）MQステータスに'1'を、更新日付にシステムタイムスタンプをセットする。
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //２－３）該当レコードを更新する。
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);
            log.debug("updated 注文単位テーブルRow := " + l_orderUnitParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException Error ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException Error In", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(l_strMethodName);
    }
    
    /**
     * (トリガ発行)<BR>
     * トリガを発行する。<BR>
     * <BR>
     * １）WEB3MQMessageSpecを生成する。 <BR>
     * <BR>
     *    WEB3MQMessageSpec(証券会社コード, データコード)<BR>
     * <BR>
     *    [引数] <BR>
     *    証券会社コード：　@引数.証券会社コード<BR>
     *    データコード：　@引数.データコード<BR>
     * <BR>
     * ２）MQトリガを発行する。<BR>
     * <BR>
     *    WEB3MQGatewayServiceImpl.send(MQメッセージ内容)<BR>
     * <BR>
     *    [引数]<BR>
     *    MQメッセージ内容： 生成したWEB3MQMessageSpec<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strRequestCode - (データコード)<BR>
     * @@roseuid 412B11A0015A
     */
    public void submitTrigger(String l_strInstitutionCode, 
            String l_strRequestCode) 
    {
        final String l_strMethodName = "submitTrigger(String l_strInstitutionCode, "
            + "String l_strRequestCode)";
        log.entering(l_strMethodName);
        // １）WEB3MQMessageSpecを生成する。
        WEB3MQMessageSpec l_mqMessageSpec = 
            new WEB3MQMessageSpec(l_strInstitutionCode, l_strRequestCode);
        // ２）MQトリガを発行する。
        WEB3MQGatewayService l_mqGatewayService =
            (WEB3MQGatewayService) Services.getService(WEB3MQGatewayService.class);
        log.debug("トリガを発行する........");
        l_mqGatewayService.send(l_mqMessageSpec);
        log.debug("トリガを発行する........OK!");
        log.exiting(l_strMethodName);
    }

    /**
     * (トリガ発行)<BR>
     * トリガを発行する。<BR>
     * <BR>
     * １）WEB3MQMessageSpecを生成する。<BR>
     * <BR>
     *    WEB3MQMessageSpec(証券会社コード, データコード, ユーザデータ)<BR>
     * <BR>
     *    [引数]<BR>
     *    証券会社コード：　@引数.証券会社コード<BR>
     *    データコード：　@引数.データコード<BR>
     *    ユーザデータ：　@引数.ユーザデータ<BR>
     * <BR>
     * ２）MQトリガを発行する。<BR>
     * <BR>
     *    WEB3MQGatewayServiceImpl.send(MQメッセージ内容)<BR>
     * <BR>
     *    [引数]<BR>
     *    MQメッセージ内容： 生成したWEB3MQMessageSpec<BR>
     * <BR>
     */
    public void submitTrigger(
        String l_strInstitutionCode, String l_strRequestCode, String l_strUser)
    {
        final String STR_METHOD_NAME =
            "submitTrigger(String l_strInstitutionCode, String l_strRequestCode, String l_strUser)";
        log.entering(STR_METHOD_NAME);

        // １）WEB3MQMessageSpecを生成する。
        WEB3MQMessageSpec l_mqMessageSpec =
            new WEB3MQMessageSpec(l_strInstitutionCode, l_strRequestCode, l_strUser);

        // ２）MQトリガを発行する。
        WEB3MQGatewayService l_mqGatewayService =
            (WEB3MQGatewayService) Services.getService(WEB3MQGatewayService.class);
        l_mqGatewayService.send(l_mqMessageSpec);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (新規入金注文送信)<BR>
     * 新規の入金注文の送信を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（リクエスト送信）新規入金注文送信」 参照<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413D5A78018E
     */
    protected void openCashinOrderSend(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String l_strMethodName = "openCashinOrderSend(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);

        AccountManager l_accountManager = GtlUtils.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accountManager.getSubAccount(
                    l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMSubAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.1 注文単位.通貨コード != null（外貨） の場合、
        //以下の処理を実行。
        AioOrderUnitRow l_aioOrderUnitRow = 
            (AioOrderUnitRow)l_orderUnit.getDataSourceObject();        
        if (l_aioOrderUnitRow.getCurrencyCode() != null)
        {
            //1.1.1  create外貨入金注文キューデータ
            //外貨入金注文のキューデータをDBに登録する。 
            //[引数] 
            //注文単位： 引数.注文単位 
            log.debug(" 注文単位.通貨コード != null（外貨） の場合" + l_aioOrderUnitRow.getCurrencyCode());
            this.createHostForeignCashinOrderData(l_orderUnit);
        } 
        else
        {
            //1.2 注文単位.通貨コード == null（円貨） の場合、
            //以下の処理を実行。
            //1.2.1 create入金注文キューデータ
            //入金注文のキューデータをDBに登録する。 
            //[引数] 
            //注文単位： 引数.注文単位 
            log.debug(" 注文単位.通貨コード == null（外貨） の場合" + l_aioOrderUnitRow.getCurrencyCode());
            this.createCashinOrderQueueData(l_orderUnit);
        }

        //1.3 isトリガ発行(String)
        boolean l_blnIsSubmitTrigger = 
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                    WEB3OrderingConditionDef.DEFAULT);
        
        // Test log
        log.debug("isトリガ発行(String) = " + l_blnIsSubmitTrigger);
        
        //1.4 isトリガ発行(String) = true の場合
        if (l_blnIsSubmitTrigger)
        {
            String l_strDataCode = null;
            // 注文単位.通貨コード == null(円貨)の場合
            if (l_aioOrderUnitRow.getCurrencyCode() == null)
            {
                //  トリガ発行(String, String)
                // [引数]
                // 証券会社コード：　@引数.注文単位.証券会社IDに該当する証券会社コード
                // データコード：”GI803T”（入金）
                l_strDataCode = WEB3HostRequestCodeDef.AIO_SLIP_SERVE + "T";
                this.submitTrigger(
                    l_subAccount.getInstitution().getInstitutionCode(), l_strDataCode);
            }
            // 注文単位.通貨コード != null(円貨)の場合
            else
            {
                // トリガ発行(String, String, String)
                // [引数]
                // 証券会社コード：引数:注文単位.証券会社IDに該当する証券会社コード
                // データコード：”GI804T”（外貨入金）
                // ユーザデータ："2"（WEB3OrderDivDef.CASHIN(入金)）
                l_strDataCode = WEB3HostRequestCodeDef.FOREIGN_CASH_TRANSFER_ORDER + "T";
                this.submitTrigger(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_strDataCode,
                    WEB3OrderDivDef.CASHIN);
            }
        }
        log.exiting(l_strMethodName);
    }
    
    /**
     * (新規出金注文送信)<BR>
     * 新規の出金注文の送信を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（リクエスト送信）新規出金注文送信」 参照<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413D5AD70130
     */
    protected void openCashoutOrderSend(AioOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String l_strMethodName = "openCashoutOrderSend(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accountManager.getSubAccount(
                l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMSubAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);
        }

        AioOrderUnitRow l_orderUnitRow = (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strCurrencyCode = l_orderUnitRow.getCurrencyCode();

        // 1.1  円貨の場合
        if (l_strCurrencyCode == null)
        {
            // 1.1.1 create出金注文キューデータ
            // 出金注文のキューデータをDBに登録する。
            // [引数]
            // 注文単位： 引数.注文単位
            this.createCashoutOrderQueueData(l_orderUnit);
        }
        // 1.2 外貨の場合
        else
        {
           // 1.2.1  create外貨出金注文キューデータ
           // 外貨出金注文のキューデータをDBに登録する。
           // [引数]
           // 注文単位： 引数.注文単位
            this.createHostForeignCashoutOrderData(l_orderUnit);
        }
        //1.3.isリアルタイムトリガ発行(String)
        // 該当の証券会社がリアルタイムでトリガ発行を実施するかをチェックする。
        //[引数]
        // 部店ID： 引数.注文単位.部店ID
        boolean l_blnIsRealSubmitTrigger = 
            this.isSubmitRealTimeTrigger(
                l_subAccount.getMainAccount().getBranch().getBranchId());
        //1.4.isリアルタイムトリガ発行 == false の場合
        if (!l_blnIsRealSubmitTrigger)
        {
            return;
        }
        //1.5. isトリガ発行(String)
        boolean l_blnIsSubmitTrigger = 
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                    WEB3OrderingConditionDef.DEFAULT);
        // test log
        log.debug("isトリガ発行(String) = " + l_blnIsSubmitTrigger);

        // 1.6 isトリガ発行(String) = false の場合
        if (!l_blnIsSubmitTrigger)
        {
            // 1.6.1
            return;
        }

        // 1.7 円貨の場合
        if (l_strCurrencyCode == null)
        {
            // 出金請求注文キューテーブルの該当レコードの処理区分を処理中に更新する。
            // [引数]
            // 証券会社コード： 部店.getInstitution().getInstitutionCode()の戻り値
            // 部店コード： 部店（引数.注文単位.部店ID）.getBranchCode()の戻り値
            // 顧客コード： 口座（引数.注文単位.口座ID）.getAccountCode()の戻り値
            // 識別コード： 引数.注文単位.識別コード
            // 注文単位ID： 引数.注文単位.注文単位ID

          this.updateStatus(l_subAccount.getInstitution().getInstitutionCode(), 
                  l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                  l_subAccount.getMainAccount().getAccountCode(), 
                  l_orderUnitRow.getOrderRequestNumber(), 
                  l_orderUnitRow.getOrderUnitId());
          
          // トリガを発行する。
          // [引数]
          // 証券会社コード： 引数.注文単位.証券会社IDに該当する証券会社コード
          // データコード： ”GI801T”
          String l_strDateCode = WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER + "T";
          this.submitTrigger(l_subAccount.getInstitution().getInstitutionCode(), l_strDateCode);
        }

        // 1.8 外貨の場合
        else
        {
            //注文単位テーブルのMQステータスを'1'（送信済み）に更新する。
            //[引数]
            //注文単位ID： 引数.注文単位.注文単位ID
            this.updateMQStatus(l_orderUnitRow.getOrderUnitId());

            // トリガを発行する。
            // [引数]
            // 証券会社コード： 引数.注文単位.証券会社IDに該当する証券会社コード
            // データコード： ”GI804T”
            // ユーザデータ： 1（WEB3OrderDivDef.CASHOUT（出金））
            String l_strDateCode = WEB3HostRequestCodeDef.FOREIGN_CASH_TRANSFER_ORDER + "T";

            this.submitTrigger(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_strDateCode,
                WEB3OrderDivDef.CASHOUT);
        }
        log.exiting(l_strMethodName);
    }
    
    /**
     * (新規振替注文送信)<BR>
     * 新規の振替注文の送信を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（リクエスト送信）新規振替注文送信」 参照<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413D70BB020B
     */
    protected void openTransferOrderSend(AioOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String l_strMethodName = "openTransferOrderSend(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);
        //1.1 補助口座オブジェクトを取得する。
        //[引数] 
        // 口座ID： 引数.注文単位.口座ID 
        // 補助口座ID： 引数.注文単位.補助口座ID
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accountManager.getSubAccount(
                l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMSubAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        //1.2. 補助口座.getSubAccountType() != 1（預り金口座） OR
        //AioOrderUnit.getDescription() == "feq_transfer" の場合        
        if (!SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(
                l_subAccount.getSubAccountType()) || 
            WEB3AioDescriptionDef.FEQ_TRANSFER.equals(
                l_orderUnit.getDescription()))
        {
            //1.2.1.return 
            log.exiting(l_strMethodName);
            return;
        }
        //1.3.create振替注文キューデータ(AioOrderUnit)
        this.createTransferOrderQueueData(l_orderUnit);
        //1.4. isトリガ発行(String)
        boolean l_blnIsSubmitTrigger = 
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);
        // test log
        log.debug("新規振替注文送信#isトリガ発行(String) = " + l_blnIsSubmitTrigger);
        log.debug("新規振替注文送信# system time = " + GtlUtils.getSystemTimestamp());
        //1.5. isトリガ発行(String) = true の場合
        if (l_blnIsSubmitTrigger)
        {
            //1.5.1  トリガ発行(String, String)
            //[引数] 
            // 証券会社コード：　@引数.注文単位.証券会社IDに該当する証券会社コード 
            // データコード： ”GI806T”（保証金振替請求） 
            this.submitTrigger(l_subAccount.getInstitution().getInstitutionCode(),
                WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ORDER + "T");
        }
        log.exiting(l_strMethodName);
    }
    
    /**
     * (create振替注文キューデータ)<BR>
     * 振替注文のキューデータの生成（DB登録）を行う。<BR>
     * <BR>
     * １）振替請求注文キューParamsインスタンスを生成する。<BR> 
     * <BR>
     * ２）振替請求注文キューParamsにプロパティをセットする。 <BR>
     *<BR> 
     * ２－１）振替出金額（借方）、振替入金額（貸方）、摘要コード、処理区分の値の設定方法@ <BR>
     *<BR> 
     * ２－１－１）引数.注文単位.注文種別 = 1006（振替注文（信用保証金から預り金））の場合 <BR>
     *<BR> 
     *  振替出金額（借方） = 0 <BR>
     *  振替入金額（貸方） = 注文単位.数量 <BR>
     *  摘要コード = 01（信用保証金） <BR>
     *<BR> 
     * ２－１－２）引数.注文単位.注文種別 = 1007（振替注文（預り金から株先証拠金））の場合 <BR>
     *<BR> 
     *  振替出金額（借方） = 注文単位.数量 <BR>
     *  振替入金額（貸方） = 0 <BR>
     * 　@　@　@・注文単位.摘要コード　@!=　@null　@の場合、<BR>
     * 　@　@　@　@摘要コード = 注文単位.摘要コード<BR>
     * 　@　@　@・注文単位.摘要コード　@==　@nullの場合、<BR>
     *　@      　@　@摘要コード = 72（株先証拠金） <BR>
     *<BR> 
     * ２－１－３）引数.注文単位.注文種別 = 1008（振替注文（株先証拠金から預り金））の場合 <BR>
     * <BR>
     *  振替出金額（借方） = 0 <BR>
     *  振替入金額（貸方） = 注文単位.数量 <BR>
     * 　@　@　@・注文単位.摘要コード　@!=　@null　@の場合、<BR>
     * 　@　@　@　@摘要コード = 注文単位.摘要コード<BR>
     * 　@　@　@・注文単位.摘要コード　@==　@null　@の場合、<BR>
     *      　@　@　@摘要コード = 72（株先証拠金）<BR>
     *<BR> 
     * ２－１－４）引数.注文単位.注文種別 = 1011（為替保証金振替注文（預り金から為替保証金））<BR>
     * 　@or 1021（CFD振替注文（預り金からCFD口座））の場合<BR>
     *<BR> 
     *  振替出金額（借方） = 注文単位.数量 <BR>
     *  振替入金額（貸方） = 0 <BR>
     * <BR>
     * ２－１－５）引数.注文単位.注文種別 = 1012（為替保証金振替注文（為替保証金から預り金））<BR>
     * 　@or 1022（CFD振替注文（CFD口座から預り金））の場合<BR>
     * <BR>
     *  振替出金額（借方） = 0 <BR>
     *  振替入金額（貸方） = 注文単位.数量 <BR>
     * <BR>
     * ２－１－６）引数.注文単位.注文種別 = <BR> 
     *      1013（外国株式振替注文（預り金から外国株式口座））の場合<BR> 
     * <BR>
     * 振替出金額（借方） = 注文単位.数量 <BR>
     * 振替入金額（貸方） = 0 <BR>
     * 摘要コード 　@ = 99（その他） <BR>
     * <BR>
     * ２－１－７）引数.注文単位.注文種別 = 1005（振替注文（預り金から信用保証金））の場合<BR>
     * <BR>
     * 振替出金額（借方） = 注文単位.数量<BR>
     * 振替入金額（貸方） = 0<BR>
     * 摘要コード         　@ = 01（信用保証金）<BR>
     * <BR>
     * ２－２）その他の項目については、DB更新仕様「振替請求注文キューテーブル.xls」参照 <BR>
     * <BR>
     * ３）振替請求注文キューParamsの内容をDBに登録する。 <BR>
     * <BR>
     *  WEB3DataAccessUtility.insertRow() <BR>
     * <BR>
     *  [引数] <BR>
     *   振替請求注文キューParams <BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413D749D019D
     */
    protected void createTransferOrderQueueData(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String l_strMethodName = "" +
            "createTransferOrderQueueData(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);

        //１）振替請求注文キューParamsインスタンスを生成する。
        HostTransferOrderParams l_params = new HostTransferOrderParams();
        //２）振替請求注文キューParamsにプロパティをセットする。 
        //摘要名:ブランク
        l_params.setRemarkName("          ");
        //強制振替:9（強制）
        l_params.setInvoluntaryTransfer(WEB3ForceTransferDef.FORCE_TRANSFER);
        //原振替月日:ブランク
        l_params.setOriginalTransferDate(WEB3AioOriginalTransferDateBlankDef.BLANK);

        AioOrderUnitRow l_aioOrderUnitRow =
            (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strRemarkCode = l_aioOrderUnitRow.getRemarkCode();

        //２－１）振替出金額（借方）、振替入金額（貸方）、摘要コード、強制振替項目の値の設定方法@
        //２－１－１）引数.注文単位.注文種別 = 1006（振替注文（信用保証金から預り金））の場合
        if (OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderUnit.getOrderType()))
        {
            //振替出金額（借方） = 0 
            l_params.setTransferAmountDebitor(0);
            //振替入金額（貸方） = 注文単位.数量
            l_params.setTransferAmountCreditor((int)l_orderUnit.getQuantity());
            //摘要コード = 01（信用保証金）
            l_params.setRemarkCode(WEB3RemarkCodeDef.MARGIN_GUARANTEE);
        }
        //２－１－２）引数.注文単位.注文種別 = 1007（振替注文（預り金から株先証拠金））の場合 
        if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderUnit.getOrderType()))
        {
            //振替出金額（借方） = 注文単位.数量 
            l_params.setTransferAmountDebitor((int)l_orderUnit.getQuantity());
            //振替入金額（貸方） = 0
            l_params.setTransferAmountCreditor(0);
            
            //・注文単位.摘要コード　@!=　@null　@の場合、
            if (l_strRemarkCode != null)
            {
                //摘要コード = 注文単位.摘要コード
                l_params.setRemarkCode(l_strRemarkCode);
            }
            //・注文単位.摘要コード　@==　@nullの場合、
            else
            {
                //    摘要コード = 72（株先証拠金） 
                l_params.setRemarkCode(WEB3RemarkCodeDef.STOCK_FUTURES_MARGIN);
            }
        }
        //２－１－３）引数.注文単位.注文種別 = 1008（振替注文（株先証拠金から預り金））の場合 
        if (OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(l_orderUnit.getOrderType()))
        {
            //振替出金額（借方） = 0 
            l_params.setTransferAmountDebitor(0);
            //振替入金額（貸方） = 注文単位.数量
            l_params.setTransferAmountCreditor((int)l_orderUnit.getQuantity());
            
            //・注文単位.摘要コード　@!=　@null　@の場合、
            if (l_strRemarkCode != null)
            {
                //摘要コード = 注文単位.摘要コード
                l_params.setRemarkCode(l_strRemarkCode);
            }
            //・注文単位.摘要コード　@==　@null　@の場合、
            else
            {
                //    摘要コード = 72（株先証拠金） 
                l_params.setRemarkCode(WEB3RemarkCodeDef.STOCK_FUTURES_MARGIN);
            }
        }
        
        //２－１－４）引数.注文単位.注文種別 = 1011（為替保証金振替注文（預り金から為替保証金））
        //or 1021（CFD振替注文（預り金からCFD口座））の場合
        if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
            l_orderUnit.getOrderType())
            || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(
                l_orderUnit.getOrderType()))
        {
            //振替出金額（借方） = 注文単位.数量
            l_params.setTransferAmountDebitor((int)l_orderUnit.getQuantity());
            //振替入金額（貸方） = 0
            l_params.setTransferAmountCreditor(0);
            //摘要コード = 注文単位.摘要コード
            l_params.setRemarkCode(l_strRemarkCode);
            //摘要名 = 注文単位.摘要名
            if (!WEB3StringTypeUtility.isEmpty(l_aioOrderUnitRow.getRemarkName()))
            {
                l_params.setRemarkName(l_aioOrderUnitRow.getRemarkName());
            }
            //強制振替
            //注文単位.摘要コード = 99：その他 and 注文単位.振替タイプ = １：入金の場合、ブランク
            //上記以外の場合、9：強制
            if (WEB3RemarkCodeDef.BLANK.equals(
                l_aioOrderUnitRow.getRemarkCode())
                && AssetTransferTypeEnum.CASH_IN.equals(
                    l_aioOrderUnitRow.getTransferType()))
            {
        	    //強制振替 = ブランク
        	    l_params.setInvoluntaryTransfer(" ");
            }
            //原振替月日
            //取引時間.発注日 = 注文単位.受渡日(MMDD)の場合、ブランクをセット
            //上記以外の場合、注文単位.受渡日（MMDD）をセット
            if (WEB3DateUtility.compareToDay(
                WEB3GentradeTradingTimeManagement.getOrderBizDate(),
                l_aioOrderUnitRow.getDeliveryDate()) != 0)
            {
                String l_strDeliveryMD = WEB3DateUtility.formatDate(
                    l_aioOrderUnitRow.getDeliveryDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_MD);
                l_params.setOriginalTransferDate(l_strDeliveryMD);
            }
        }
        
        //２－１－５）引数.注文単位.注文種別 = 1012（為替保証金振替注文（為替保証金から預り金））
        //or 1022（CFD振替注文（CFD口座から預り金））の場合
        if (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(
            l_orderUnit.getOrderType())
            || OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD.equals(
                l_orderUnit.getOrderType()))
        {
            //振替出金額（借方） = 0 
            l_params.setTransferAmountDebitor(0);
            //振替入金額（貸方） = 注文単位.数量
            l_params.setTransferAmountCreditor((int)l_orderUnit.getQuantity());
            //摘要コード = 注文単位.摘要コード
            l_params.setRemarkCode(l_strRemarkCode);
            //摘要名 = 注文単位.摘要名
            if (!WEB3StringTypeUtility.isEmpty(l_aioOrderUnitRow.getRemarkName()))
            {
                l_params.setRemarkName(l_aioOrderUnitRow.getRemarkName());
            }
            //強制振替
            //注文単位.摘要コード = 99：その他 and 注文単位.振替タイプ = １：入金の場合、ブランク
            //上記以外の場合、9：強制
            if (WEB3RemarkCodeDef.BLANK.equals(
                l_aioOrderUnitRow.getRemarkCode())
                && AssetTransferTypeEnum.CASH_IN.equals(
                    l_aioOrderUnitRow.getTransferType()))
            {
        	    //強制振替 = ブランク
        	    l_params.setInvoluntaryTransfer(" ");
            }
            //原振替月日
            //取引時間.発注日 = 注文単位.受渡日(MMDD)の場合、ブランクをセット
            //上記以外の場合、注文単位.受渡日（MMDD）をセット
            if (WEB3DateUtility.compareToDay(
                WEB3GentradeTradingTimeManagement.getOrderBizDate(),
                l_aioOrderUnitRow.getDeliveryDate()) != 0)
            {
                String l_strDeliveryMD = WEB3DateUtility.formatDate(
                    l_aioOrderUnitRow.getDeliveryDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_MD);
                l_params.setOriginalTransferDate(l_strDeliveryMD);
            }
        }
        
        //２－１－６）引数.注文単位.注文種別 = 1013（外国株式振替注文（預り金から外国株式口座））の場合
        if (OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderUnit.getOrderType()))
        {
            //振替出金額（借方） = 注文単位.数量
            l_params.setTransferAmountDebitor((int)l_orderUnit.getQuantity());
            //振替入金額（貸方） = 0
            l_params.setTransferAmountCreditor(0);
            //摘要コード 　@ = 99（その他）
            l_params.setRemarkCode(WEB3RemarkCodeDef.BLANK);
        }

        //２－１－７）引数.注文単位.注文種別 = 1005（振替注文（預り金から信用保証金））の場合
        if(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_orderUnit.getOrderType()))
        {
            //振替出金額（借方） = 注文単位.数量
            l_params.setTransferAmountDebitor((int)l_orderUnit.getQuantity());
            //振替入金額（貸方） = 0
            l_params.setTransferAmountCreditor(0);
            //摘要コード         　@ = 01（信用保証金）
            l_params.setRemarkCode(WEB3RemarkCodeDef.MARGIN_GUARANTEE);
        }

        //２－２）その他の項目については、DB更新仕様「振替請求注文キューテーブル.xls」参照
        l_params.setRequestCode(WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ORDER);
        
        try 
        {
            AccountManager l_accMgr = (AccountManager) GtlUtils.getAccountManager();
            log.debug(">>>>>>>>>WEB3GentradeAccountManager");
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_orderUnit.getAccountId());
            log.debug(">>>>>>>>>MainAccount");
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode =" + l_strInstitutionCode);
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            log.debug("l_strBranchCode =" + l_strBranchCode);
            String l_strAccountCode = l_mainAccount.getAccountCode();
            log.debug("l_strAccountCode =" + l_strAccountCode);
            
            l_params.setInstitutionCode(l_strInstitutionCode);
            l_params.setBranchCode(l_strBranchCode);
            l_params.setAccountCode(l_strAccountCode);
			l_params.setTraderCode("     ");

            l_params.setCancelDiv(" ");

            l_params.setOrderRequestNumber(l_aioOrderUnitRow.getOrderRequestNumber());
            
            //振替指示日
            //注文単位.発注日
            //※時刻部分は、00:00  
            l_params.setTransferDate(
                WEB3DateUtility.toDay(
                    WEB3DateUtility.getDate(l_aioOrderUnitRow.getBizDate(), "yyyyMMdd")));
            
            //振替指示時間
            //現在時刻がトリガ発行を実施する時間帯の場合、00:00をセット
            //現在時刻がトリガ発行を実施しない時間帯の場合、06:00をセット
            //※日付部分は、注文単位.発注日
            
            String l_strTransferTime;        
            if (WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT) == true)
            {
                log.debug("トリガ発行を実施する時間帯の場合、isトリガ発行 = true");
                l_strTransferTime = l_aioOrderUnitRow.getBizDate() + "000000";
            }
            else
            {
                log.debug("トリガ発行を実施しない時間帯の場合、isトリガ発行 = false");
                l_strTransferTime = l_aioOrderUnitRow.getBizDate() + "060000";    
            }
            
            l_params.setTransferTime(
                WEB3DateUtility.getDate(l_strTransferTime, "yyyyMMddHHmmss"));
            
            //注文単位.MQステータス＝0：未送信の場合、3：未処理
            //以外、0:処理中
            if (WEB3MqStatusDef.NOT_SEND_MAIL.equals(l_aioOrderUnitRow.getMqStatus()))
            {
                l_params.setStatus(WEB3AioHostStatusDef.NOT_DEAL);
            }
            else
            {
                l_params.setStatus(WEB3AioHostStatusDef.DEALING);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMainAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
       
        //３）振替請求注文キューParamsの内容をDBに登録する。 
        //WEB3DataAccessUtility.insertRow() 
        //[引数] 
        //振替請求注文キューParams 
        try 
        {
            WEB3DataAccessUtility.insertRow(l_params);
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In 振替請求注文キューParamsの内容をDBに登録する ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In 振替請求注文キューParamsの内容をDBに登録する ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        log.exiting(l_strMethodName);
    }
    
    /**
     * @@param arg0
     * @@return MarketRequestSendResult
     * @@roseuid 415A47150211
     */
    public MarketRequestSendResult send(MarketRequestMessage arg0) 
    {
        final String l_strMethodName = "send(MarketRequestMessage arg0)";
        log.entering(l_strMethodName);
        log.exiting(l_strMethodName);
        return null;
    }
    
    //=============remain zhou-yong 2004/12/6 NO.1 begin ===============
    /**
     * (新規証券振替注文送信)<BR>
     * 新規の証券振替注文の送信を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（リクエスト送信）新規証券振替注文送信」 参照 <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413D70BB020B
     */
    protected void openSecurityTransferOrderSend(AioOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String l_strMethodName = "openSecurityTransferOrderSend(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);


        //1.1) get補助口座(,)
        //アイテムの定義
        //補助口座オブジェクトを取得する。
        //[引数] 
        //口座ID： 引数.注文単位.口座ID 
        //補助口座ID： 引数.注文単位.補助口座ID 
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);           
        WEB3GentradeAccountManager l_gentradeAccountManager =  //拡張アカウントマネージャ取得する 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
        try
        {
            SubAccount l_subAccount = l_gentradeAccountManager.getSubAccount(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId());
            log.debug("l_subAccount.getAccountId() = " + 
                l_subAccount.getAccountId());
            
            //1.2) 補助口座.getSubAccountType() != 1（預り金口座）
            //の場合
            //1.2.1 return
            if(!SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                return;
            }
            
            //1.3) create証券振替注文キューデータ(AioOrderUnit)
            //アイテムの定義
            //振替注文のキューデータをDBに登録する。 
            //[引数] 
            //注文単位： 引数.注文単位 
            this.createSecurityTransferOrderQueueData(l_orderUnit);
            
            //1.4) isトリガ発行(String)
            //アイテムの定義
            //SONARへトリガを発行できる時間帯かを判定する。
            //[引数] 
            //発注条件： 0（DEFAULT） 
            boolean l_blnIsSubmitTrigger = 
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                    WEB3OrderingConditionDef.DEFAULT);
            
            //1.5) isトリガ発行() = true
            if(l_blnIsSubmitTrigger)
            {
                //1.5.1) トリガ発行(String, String)
                //アイテムの定義
                //トリガを発行する。
                //[引数] 
                //証券会社コード：　@引数.注文単位.証券会社IDに該当する証券会社コード 
                //データコード： ”GI807T”（代用振替請求） 
                this.submitTrigger(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3HostRequestCodeDef.AIO_MRGSEC_TRANSFER + "T");
            }
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (create証券振替注文キューデータ )<BR>
     * 証券振替注文のキューデータの生成（DB登録）を行う。<BR>
     * <BR>
     *１）代用振替請求キューParamsインスタンスを生成する。<BR>
     *<BR>
     *２）代用振替請求キューParamsにプロパティをセットする。 <BR>
     *  DB更新仕様「証券振替_代用振替請求キューテーブル.xls」参照<BR>
     *<BR>
     *３）代用振替請求キューParamsの内容をDBに登録する。 <BR>
     *  WEB3DataAccessUtility.insertRow() <BR>
     *  [引数] <BR>
     *  代用振替請求キューParams<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@roseuid 413D70BB020B
     */
    protected void createSecurityTransferOrderQueueData(AioOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String l_strMethodName = "createSecurityTransferOrderQueueData(AioOrderUnit l_orderUnit)";
        log.entering(l_strMethodName);

        try
        {
            AioOrderUnitRow l_orderUnitRow = (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //１）代用振替請求キューParamsインスタンスを生成する
            HostMrgsecTransferParams l_hostMrgsecTransferParams = new HostMrgsecTransferParams();
            
            //２）代用振替請求キューParamsにプロパティをセットする。
            //DB更新仕様「証券振替_代用振替請求キューテーブル.xls」参照
            
            //データコード
            //GI807(代用振替請求)
            l_hostMrgsecTransferParams.setRequestCode(WEB3HostRequestCodeDef.AIO_MRGSEC_TRANSFER);
            
            //証券会社コード
            //注文単位.部店ＩＤに該当する部店.証券会社コード
            AccountManager l_accMgr = (AccountManager) GtlUtils.getAccountManager();
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_orderUnit.getAccountId());
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            
            l_hostMrgsecTransferParams.setInstitutionCode(l_strInstitutionCode);
            
            //部店コード
            //注文単位.部店ＩＤに該当する部店.部店コード
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            l_hostMrgsecTransferParams.setBranchCode(l_strBranchCode);
            
            //顧客コード
            //注文単位.顧客ＩＤに該当する顧客.顧客コード
            String l_strAccountCode = l_mainAccount.getAccountCode();
            l_hostMrgsecTransferParams.setAccountCode(l_strAccountCode); 
            
            //扱者コード
            //ブランク
            l_hostMrgsecTransferParams.setTraderCode("     ");
            
            //振替区分
            //注文単位.注文種別 = 1009（保護預りから代用有価証券）の場合、
            //  02：保護→代用
            //注文単位.注文種別 = 1010（代用有価証券から保護預り）の場合、
            //  01：代用→保護
            if(OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES.equals(
                l_orderUnit.getOrderType()))
            {
                l_hostMrgsecTransferParams.setTransferFlag(WEB3AioTransferFlagDef.SAFE_DEPOSIT);
            }
            else if(OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT.equals(
                l_orderUnit.getOrderType()))
            {
                l_hostMrgsecTransferParams.setTransferFlag(WEB3AioTransferFlagDef.COLLATERAL_SECURITY);
            }
            
            //原月日
            //ブランク
            l_hostMrgsecTransferParams.setOriginalTransferDate("    ");

            //取消フラグ
            //ブランク
            l_hostMrgsecTransferParams.setCancelFlag(" ");

            //商品区分
            //注文単位.銘柄タイプ = 1（株式）の場合、1：株
            //注文単位.銘柄タイプ = 2（債券）の場合、3：債券
            //注文単位.銘柄タイプ = 3（投信）の場合、2：投信
            //注文単位.銘柄タイプ = 4（外株）の場合、4：外株
            if(ProductTypeEnum.EQUITY.equals(l_orderUnit.getProductType()))
            {
                l_hostMrgsecTransferParams.setCommodity(WEB3AioHostCommodityDef.EQUITY);
            }
            else if(ProductTypeEnum.BOND.equals(l_orderUnit.getProductType()))
            {
                l_hostMrgsecTransferParams.setCommodity(WEB3AioHostCommodityDef.BOND);
            }
            else if(ProductTypeEnum.MUTUAL_FUND.equals(l_orderUnit.getProductType()))
            {
                l_hostMrgsecTransferParams.setCommodity(WEB3AioHostCommodityDef.MUTUAL_FUND);
            }        
            
            //銘柄コード
            //注文単位.銘柄タイプ = 2（債券）の場合、
            //  注文単位.銘柄IDに該当する銘柄.回号コード(SONAR)+銘柄.銘柄コード(SONAR)の上4桁
            //注文単位.銘柄タイプ != 2（債券）の場合、
            //  注文単位.銘柄IDに該当する銘柄.銘柄コード
            
            AioOrderUnitRow l_aioOrderUnitRow = 
                (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            String l_strProductCode = null;
            
            ProductTypeEnum l_productTypeEnum = l_aioOrderUnitRow.getProductType();

            WEB3AioProductManager l_aioProductManager = 
                (WEB3AioProductManager)GtlUtils.getTradingModule(ProductTypeEnum.AIO).getProductManager();
            
            Product l_product = l_aioProductManager.getProduct(l_productTypeEnum, l_aioOrderUnitRow.getProductId());
            
            log.debug("l_productTypeEnum = " + l_productTypeEnum);
            if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
            {   
              l_strProductCode = 
                  ((EqtypeProductRow)l_product.getDataSourceObject()).getProductCode();    
            }
            else if (ProductTypeEnum.BOND.equals(l_productTypeEnum))
            {
                String l_strHostIssueCode = 
                    ((BondProductRow)l_product.getDataSourceObject()).getHostProductIssueCode();
                String l_strHostBondProductCode = 
                    ((BondProductRow)l_product.getDataSourceObject()).getHostProductCode().substring(0, 4);
                l_strProductCode = l_strHostIssueCode + l_strHostBondProductCode;
            }
            else if (ProductTypeEnum.MUTUAL_FUND.equals(l_productTypeEnum))
            {
                l_strProductCode = 
                    ((MutualFundProductRow)l_product.getDataSourceObject()).getProductCode();    
            }
            else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_productTypeEnum))
            {
                l_strProductCode = 
                    ((FeqProductRow)l_product.getDataSourceObject()).getProductCode();    
            }
            
            //test log
            log.debug("l_strProductCode = " + l_strProductCode);
            
            l_hostMrgsecTransferParams.setProductCode(l_strProductCode);
          
            //数量
            double l_dbQuantity =  Math.abs(l_orderUnit.getQuantity());
            // 注文単位.銘柄タイプ == 3（投信）の場合
            if (ProductTypeEnum.MUTUAL_FUND.equals(l_orderUnit.getProductType()))
            {
                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow)l_product.getDataSourceObject();
                // 投信銘柄マスタ.投信銘柄タイプ == 2（国外） or （投信銘柄マスタ.投信銘柄タイプ == 0（その他）
                // and 投信銘柄マスタ.通貨コード != T0(円)）の場合
                if (MutualFundTypeEnum.FOREIGN.equals(l_mutualFundProductRow.getFundType()) ||
                    (MutualFundTypeEnum.OTHER.equals(l_mutualFundProductRow.getFundType()) &&
                    !WEB3CurrencyCodeDef.T0.equals(l_mutualFundProductRow.getCurrencyCode())))
                {
                    // 投信銘柄マスタ.入力単位が”1：1”の場合 注文単位.数量の絶対値　@×　@1000
                    if (WEB3InputUnitDef.ONE.equals(l_mutualFundProductRow.getInputUnit()))
                    {
                        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dbQuantity));
                        BigDecimal l_bdResult = l_bdQuantity.multiply(new BigDecimal(1000));
                        l_hostMrgsecTransferParams.setQuantity(l_bdResult.longValue());
                    }
                    // 投信銘柄マスタ.入力単位が”2：1万”の場合 注文単位.数量の絶対値 / 10000　@×　@1000
                    else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_mutualFundProductRow.getInputUnit()))
                    {
                        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dbQuantity));
                        BigDecimal l_bdResult =
                            l_bdQuantity.divide(new BigDecimal(10000), 0).multiply(new BigDecimal(1000));
                        l_hostMrgsecTransferParams.setQuantity(l_bdResult.longValue());
                    }
                }
                // それ以外の場合
                else
                {
                    // 投信銘柄マスタ.入力単位が”1：1”の場合 注文単位.数量の絶対値
                    if (WEB3InputUnitDef.ONE.equals(l_mutualFundProductRow.getInputUnit()))
                    {
                        l_hostMrgsecTransferParams.setQuantity((long)l_dbQuantity);
                    }
                    // 投信銘柄マスタ.入力単位が”2：1万”の場合 注文単位.数量の絶対値 / 10000
                    else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_mutualFundProductRow.getInputUnit()))
                    {
                        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dbQuantity));
                        BigDecimal l_bdResult =
                            l_bdQuantity.divide(new BigDecimal(10000), 0);
                        l_hostMrgsecTransferParams.setQuantity(l_bdResult.longValue());
                    }
                }
            }
            // 注文単位.銘柄タイプ != 3（投信以外）の場合
            // 　@注文単位.数量の絶対値
            else
            {
                l_hostMrgsecTransferParams.setQuantity((long)l_dbQuantity);
            }
            //単価
            //ブランク
            l_hostMrgsecTransferParams.setPrice(null);

            //仕法@区分
            //注文単位.銘柄タイプ = 1（株式） or 4（外株）の場合、ブランク
            //注文単位.銘柄タイプ = 2（債券） or 3（投信）の場合、1
            if(ProductTypeEnum.EQUITY.equals(l_orderUnit.getProductType())
                || ProductTypeEnum.FOREIGN_EQUITY.equals(l_orderUnit.getProductType()))
            {
                l_hostMrgsecTransferParams.setDelivType(" ");
            }
            else if(ProductTypeEnum.BOND.equals(l_orderUnit.getProductType())
                      || ProductTypeEnum.MUTUAL_FUND.equals(l_orderUnit.getProductType()))
            {
                l_hostMrgsecTransferParams.setDelivType("1");
            }
            
            //識別コード
            //注文単位.識別コード
            l_hostMrgsecTransferParams.setOrderRequestNumber(l_aioOrderUnitRow.getOrderRequestNumber());
            
            //振替指示日
            //注文単位.発注日
            //※時刻部分は、00:00  
            l_hostMrgsecTransferParams.setTransferDate(
                WEB3DateUtility.toDay(
                    WEB3DateUtility.getDate(l_aioOrderUnitRow.getBizDate(), "yyyyMMdd")));

            //振替指示時間
            //00:00:00  ※日付部分は、注文単位.発注日  
            l_hostMrgsecTransferParams.setTransferTime(
                WEB3DateUtility.toDay(
                    WEB3DateUtility.getDate(l_aioOrderUnitRow.getBizDate(), "yyyyMMdd")));                           
            
            //処理区分
            //0：未処理        
            l_hostMrgsecTransferParams.setStatus(WEB3StatusDef.NOT_DEAL);
            
            //特定口座区分
            //注文単位.税区分 = 1（一般）の場合、0：一般口座
            //注文単位.税区分 = 2（特定） or 3（特定かつ源泉）の場合、1：特定口座
            log.debug("l_orderUnit.getTaxType()= " + l_orderUnitRow.getTaxType());
            
            if(TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getTaxType()))
            {
                l_hostMrgsecTransferParams.setSpecificAccountDiv(WEB3TaxTypeSpecialDef.NORMAL);
            }
            else if(TaxTypeEnum.SPECIAL.equals(l_orderUnitRow.getTaxType())
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_orderUnit.getTaxType()))
            {
                l_hostMrgsecTransferParams.setSpecificAccountDiv(WEB3TaxTypeSpecialDef.SPECIAL);
            }  

            //連動
            //注文単位.銘柄タイプ = 1（株式）の場合、0：機@構連動
            //注文単位.銘柄タイプ != 1（株式）の場合、 ：ブランク
            if (ProductTypeEnum.EQUITY.equals(l_orderUnit.getProductType()))
            {
                l_hostMrgsecTransferParams.setInterlock(
                    WEB3InterLockDef.INSTITUTE_INTERLOCKING_MOVEMENT);
            }
            else
            {
                l_hostMrgsecTransferParams.setInterlock(WEB3InterLockDef.DEFAULT);
            }

            //預託
            //：ブランク
            l_hostMrgsecTransferParams.setDeposit(WEB3DepositDef.DEFAULT);

            //３）代用振替請求キューParamsの内容をDBに登録する
            //WEB3DataAccessUtility.insertRow()>
            //  [引数] <BR>
            //  代用振替請求キューParams<BR>
            WEB3DataAccessUtility.insertRow(l_hostMrgsecTransferParams);

        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMainAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataException l_ex)
        {
            log.error("Error In 代用振替請求キューParamsの内容をDBに登録する ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(l_strMethodName);
        
    }
    //=============remain zhou-yong 2004/12/6 NO.1 end ===============
    
    //=============remain wei-nianqiong 2005/01/21 NO.125 start ======
    /**
     * (update振替処理区分) <BR>
     * <BR>
     * 振替請求注文キューテーブルの処理区分を0（処理中）に、 <BR>
     * 注文単位テーブルのMQステータスを1（送信済み）に更新する。 <BR>
     * 更新後発注日、更新後受渡日が設定されている場合、対応する項目の更新を行う。 <BR>
     * <BR>
     * １）注文単位テーブルの更新 <BR>
     * <BR>
     * １－１）以下の条件で、注文単位テーブルからレコードを取得する。 <BR>
     * <BR>
     * [検索条件] <BR>
     * 注文単位ID： 引数.注文単位ID <BR>
     * <BR>
     * １－２）更新内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「FXへの振替_注文単位テーブル.xls」 <BR>
     * 「FXへの振替_注文単位テーブル_DB更新仕様[結果通知]」シート <BR>
     * <BR>
     * １－３）該当レコードを更新する。 <BR>
     * <BR>
     * ２）注文履歴テーブルの更新 <BR>
     * <BR>
     * ２－１）以下の条件で、注文履歴テーブルからレコードを取得する。 <BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@注文単位ID：　@引数.注文単位ID <BR>
     * 　@注文履歴番号：　@１）で取得した注文単位.注文履歴最終通番 <BR>
     * <BR>
     * ２－２）更新内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「FXへの振替_注文履歴テーブル.xls」 <BR>
     * 「FXへの振替_注文履歴テーブル_DB更新仕様[結果通知]」シート <BR>
     * <BR>
     * ２－３）該当レコードを更新する。 <BR>
     * <BR>
     * ３）注文テーブル（ヘッダ）の更新 <BR>
     * <BR>
     * ３－１）以下の条件で、注文テーブル（ヘッダ）からレコードを取得する。 <BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@注文ID　@＝　@１）で取得した注文単位.注文ID <BR>
     * <BR>
     * ３－２）更新内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「FXへの振替_注文テーブル(ヘッダ).xls」 <BR>
     * 「FXへの振替_注文テーブル(ヘッダ)_DB更新仕様[結果通知]」シート <BR>
     * <BR>
     * ３－３）該当レコードを更新する。 <BR>
     * <BR>
     * ４）振替請求注文キューテーブルの更新 <BR>
     * <BR>
     * ４－１）以下の条件で、振替請求注文キューテーブルからレコードを取得する。 <BR>
     * <BR>
     * [検索条件] <BR>
     * データコード： GI806（保証金振替請求） <BR>
     * 証券会社コード： 引数.証券会社コード <BR>
     * 部店コード： 引数.部店コード <BR>
     * 顧客コード： 引数.顧客コード <BR>
     * 識別コード： 引数.識別コード <BR>
     * 処理区分： 3（未処理） <BR>
     * <BR>
     * ４－２）更新内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「FXへの振替_振替請求注文キューテーブル.xls」 <BR>
     * 「FXへの振替_振替請求注文キューテーブル_DB更新[結果通知]」シート <BR>
     * <BR>
     * ４－３）該当レコードを更新する。 <BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strAccountCode - (顧客コード)
     * @@param l_strOrderRequestNumber  - (識別コード)
     * @@param l_lngOrderUnitId - (注文単位ID)
     * @@param l_strUpdatedBizDate - (更新後発注日)
     * @@param l_strUpdatedDeliveryDate - (更新後受渡日)
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 416D749D019D
     */   
    public void updateTransferProcessDiv(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strOrderRequestNumber , 
            long l_lngOrderUnitId, 
            String l_strUpdatedBizDate, 
            String l_strUpdatedDeliveryDate)
    throws WEB3BaseException
    {
        final String l_strMethodName = "updateTransferProcessDiv(" +
            "String, String, String, String, long, String, String)";
        log.entering(l_strMethodName);
        
        try
        {
            //１）注文単位テーブルの更新 
            //１－１）以下の条件で、注文単位テーブルからレコードを取得する。 
            //[検索条件] 
            //注文単位ID： 引数.注文単位ID 
            AioOrderUnitRow l_orderUnitRow = 
                AioOrderUnitDao.findRowByPk(l_lngOrderUnitId);
            log.debug("注文単位テーブルRow := " + l_orderUnitRow);
            
            AioOrderUnitParams l_orderUnitParams = new AioOrderUnitParams(l_orderUnitRow);

            //引数.更新後発注日 != nullの場合、引数.更新後発注日
            //以外、（既存値）
            if (l_strUpdatedBizDate != null)
            {
                log.debug("引数.更新後発注日 != nullの場合");
                l_orderUnitParams.setBizDate(l_strUpdatedBizDate);
            }
            //引数.更新後受渡日 != nullの場合、引数.更新後受渡日
            //以外、（既存値）
            if (l_strUpdatedDeliveryDate != null)
            {
                log.debug("引数.更新後受渡日 != nullの場合");
                Date l_datDeliveryDate = 
                    WEB3DateUtility.getDate(l_strUpdatedDeliveryDate,"yyyyMMdd");
                l_orderUnitParams.setDeliveryDate(l_datDeliveryDate);
            }
            
            //１－２）更新内容            
            //MQステータスに1：送信済み
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            //更新日付にシステムタイムスタンプをセットする。
            l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //１－３）該当レコードを更新する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);
            log.debug("updated 注文単位テーブルRow := " + l_orderUnitParams);
            
            //２）注文履歴テーブルの更新 
            //２－１）以下の条件で、注文履歴テーブルからレコードを取得する。 
            //[検索条件] 
            //注文単位ID：　@引数.注文単位ID 
            //注文履歴番号：　@１）で取得した注文単位.注文履歴最終通番 

            List l_lisOrderActionRows = l_queryProcessor.doFindAllQuery(
                AioOrderActionRow.TYPE,
                "order_unit_id = ? and order_action_serial_no = ?",
                null,
                new Object[] {
                    new Long(l_lngOrderUnitId), 
                    new Long(l_orderUnitRow.getLastOrderActionSerialNo())
                    });
            if (l_lisOrderActionRows.size() == 1)
            {
                AioOrderActionRow l_orderActionRow = 
                    (AioOrderActionRow)l_lisOrderActionRows.get(0);
                AioOrderActionParams l_orderActionParams = 
                    new AioOrderActionParams(l_orderActionRow);
                l_orderActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //２－３）該当レコードを更新する。
                l_queryProcessor.doUpdateQuery(l_orderActionParams);
                log.debug("updated 注文履歴テーブルRow := " + l_orderActionParams);
                
                //３）注文テーブル（ヘッダ）の更新 
                //３－１）以下の条件で、注文テーブル（ヘッダ）からレコードを取得する。 
                //[検索条件] 
                //注文ID　@＝　@１）で取得した注文単位.注文ID 
                AioOrderRow l_orderRow = 
                    AioOrderDao.findRowByPk(l_orderUnitRow.getOrderId());
                log.debug("注文テーブルRow := " + l_orderRow);
                
                AioOrderParams l_orderParams = 
                    new AioOrderParams(l_orderRow);
                l_orderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //３－３）該当レコードを更新する。
                l_queryProcessor.doUpdateQuery(l_orderParams);
                log.debug("updated 注文テーブルRow := " + l_orderParams);
            }
            
            //４）振替請求注文キューテーブルの更新 
            //４－１）以下の条件で、振替請求注文キューテーブルからレコードを取得する。 
            //[検索条件] 
            //データコード： GI806（保証金振替請求） 
            //証券会社コード： 引数.証券会社コード 
            //部店コード： 引数.部店コード 
            //顧客コード： 引数.顧客コード 
            //識別コード： 引数.識別コード 
            //処理区分： 3（未処理） 
            List l_lisHostTransferOrderRows = l_queryProcessor.doFindAllQuery(
                HostTransferOrderRow.TYPE,
                "request_code = ? and institution_code = ? and " +
                "branch_code = ? and account_code = ? and " +
                "order_request_number = ? and status = ?",
                null,
                new Object[] {
                        WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ORDER, 
                        l_strInstitutionCode, 
                        l_strBranchCode, 
                        l_strAccountCode, 
                        l_strOrderRequestNumber,
                        WEB3AioHostStatusDef.NOT_DEAL
                        });
            
            if (l_lisHostTransferOrderRows.size() == 1)
            {
                HostTransferOrderRow l_hostTransferOrderRow = 
                    (HostTransferOrderRow)l_lisHostTransferOrderRows.get(0);
                HostTransferOrderParams l_hostTransferOrderParams = 
                    new HostTransferOrderParams(l_hostTransferOrderRow);
                
                log.debug("before 振替請求注文キューテーブルRow := " + 
                        l_hostTransferOrderRow);
                
                AccountManager l_accMgr = (AccountManager) GtlUtils.getAccountManager();
                MainAccount l_mainAccount =
                	l_accMgr.getMainAccount(l_orderUnitParams.getAccountId());

                WEB3GentradeBranch l_branch =
                	(WEB3GentradeBranch) l_mainAccount.getBranch();

                //原振替月日:
                //取引時間.発注日 = 注文単位.受渡日(MMDD)の場合、ブランクをセット
                //上記以外の場合、注文単位.受渡日（MMDD）をセット
                if (WEB3DateUtility.compareToDay(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(),
                    l_orderUnitParams.getDeliveryDate()) == 0)
                {
                    l_hostTransferOrderParams.setOriginalTransferDate(
                        WEB3AioOriginalTransferDateBlankDef.BLANK);
                }
                else
                {
                    String l_strDeliveryMD = WEB3DateUtility.formatDate(
                        l_orderUnitParams.getDeliveryDate(),
                        WEB3GentradeTimeDef.DATE_FORMAT_MD);
                    l_hostTransferOrderParams.setOriginalTransferDate(l_strDeliveryMD);
                }

                //注文単位.発注日 ※時刻部分は、00:00
                l_hostTransferOrderParams.setTransferDate(
                        WEB3DateUtility.getDate(
                            l_orderUnitRow.getBizDate() + "00:00", 
                            "yyyyMMddHH:mm"));
                
                //現在時刻がトリガ発行を実施する時間帯の場合、00:00をセット            
                //※日付部分は、注文単位.発注日
                Date l_datTransferTime = null;
                if (WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
                {
                    log.debug("現在時刻がトリガ発行を実施する時間帯の場合");
                    l_datTransferTime = WEB3DateUtility.getDate(
                        l_orderUnitRow.getBizDate() + "00:00",
                        "yyyyMMddHH:mm");
                }
                //現在時刻がトリガ発行を実施しない時間帯の場合、06:00をセット
                //※日付部分は、注文単位.発注日
                else
                {
                    l_datTransferTime = WEB3DateUtility.getDate(
                        l_orderUnitRow.getBizDate() + "06:00",
                        "yyyyMMddHH:mm");
                }
                l_hostTransferOrderParams.setTransferTime(l_datTransferTime);
                
                //処理区分 = 0:処理中
                l_hostTransferOrderParams.setStatus(
                    WEB3AioHostStatusDef.DEALING); //0:処理中
                
                //４－３）該当レコードを更新する。
                l_queryProcessor.doUpdateQuery(l_hostTransferOrderParams);
                log.debug("updated 振替請求注文キューテーブルRow := " + 
                        l_hostTransferOrderParams);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException Error ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException Error In", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_ex) {
            log.error("NotFoundException Error In", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
		}
        
        log.exiting(l_strMethodName);
    }
    //=============remain wei-nianqiong 2005/01/21 NO.125 end ========
    
    /**
     * (update振替処理区分) <BR>
     * <BR>
     * 振替請求注文キューテーブルの処理区分を0（処理中）に、 <BR>
     * 注文単位テーブルのMQステータスを1（送信済み）に更新する。 <BR>
     * 更新後発注日、更新後受渡日が設定されている場合、対応する項目の更新を行う。 <BR>
     * <BR>
     * １）注文単位テーブルの更新 <BR>
     * <BR>
     * １－１）以下の条件で、注文単位テーブルからレコードを取得する。 <BR>
     * <BR>
     *   [検索条件] <BR>
     *   注文単位ID： 引数.注文単位ID <BR>
     * <BR>
     * １－２）更新内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新  <BR>
     * 「FXへの振替_注文単位テーブル.xls」 <BR>
     * 「FXへの振替_注文単位テーブル_DB更新仕様(結果通知2)」シート <BR>
     * <BR>
     * １－３）該当レコードを更新する。 <BR>
     * <BR>
     * ２）注文履歴テーブルの更新 <BR>
     * <BR>
     * ２－１）以下の条件で、注文履歴テーブルからレコードを取得する。 <BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@注文単位ID：　@引数.注文単位ID <BR>
     * 　@注文履歴番号：　@１）で取得した注文単位.注文履歴最終通番  <BR>
     * 
     * ２－２）更新内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新  <BR>
     * 「FXへの振替_注文履歴テーブル.xls」 <BR>
     * 「FXへの振替_注文履歴テーブル_DB更新仕様(結果通知2)」シート <BR>
     * <BR>
     * ２－３）該当レコードを更新する。 <BR>
     * <BR>
     * ３）注文テーブル（ヘッダ）の更新 <BR>
     * <BR>
     * ３－１）以下の条件で、注文テーブル（ヘッダ）からレコードを取得する。 <BR>
     * <BR>
     * 　@[検索条件]  <BR>
     * 　@注文ID　@＝　@１）で取得した注文単位.注文ID  <BR>
     * <BR>
     * ３－２）更新内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新  <BR>
     * 「FXへの振替_注文テーブル(ヘッダ).xls」 <BR>
     * 「FXへの振替_注文テーブル(ヘッダ)_DB更新仕様[結果通知]」シート <BR>
     * <BR>
     * ３－３）該当レコードを更新する。 <BR>
     * <BR>
     * ４）振替請求注文キューテーブルの更新  <BR>
     * <BR>
     * ４－１）以下の条件で、振替請求注文キューテーブルからレコードを取得する。  <BR>
     * <BR>
     * [検索条件]  <BR>
     * データコード： GI806（保証金振替請求）  <BR>
     * 証券会社コード： 引数.証券会社コード  <BR>
     * 部店コード： 引数.部店コード  <BR>
     * 顧客コード： 引数.顧客コード  <BR>
     * 識別コード： 引数.識別コード  <BR>
     * 処理区分： 3（未処理）  <BR>
     * <BR>
     * ４－２）更新内容は下記を参照。  <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新  <BR>
     * 「FXへの振替_振替請求注文キューテーブル.xls」  <BR>
     * 「_振替請求注文キューテーブルDB更新(結果通知2)」シート  <BR>
     * <BR>
     * ４－３）該当レコードを更新する。 <BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード
     * @@param l_strOrderRequestNumber  - (識別コード)<BR>
     * 識別コード
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID
     * @@param l_strUpdatedBizDate - (更新後発注日)<BR>
     * 更新後発注日 <BR>
     * YYYYMMDD <BR>
     * ※更新しない場合、null<BR>
     * @@param l_strUpdatedDeliveryDate - (更新後受渡日)<BR>
     * 更新後受渡日 <BR>
     * YYYYMMDD<BR>
     * ※更新しない場合、null
     * @@param l_strCashInOutAmount - (入出金額)<BR>
     * 入出金額<BR>
     * @@param l_strRemarkCode - (摘要コード)<BR>
     * 摘要コード
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 416D749D019D
     */   
    public void updateTransferProcessDiv(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strOrderRequestNumber , 
        long l_lngOrderUnitId, 
        String l_strUpdatedBizDate, 
        String l_strUpdatedDeliveryDate, 
        String l_strCashInOutAmount, 
        String l_strRemarkCode) throws WEB3BaseException
    {
        final String l_strMethodName = "updateTransferProcessDiv(" +
            "String, String, String, String, long, String, String, String, String)";
        log.entering(l_strMethodName);
        
        try
        {
            //１）注文単位テーブルの更新 
            //１－１）以下の条件で、注文単位テーブルからレコードを取得する。 
            //[検索条件] 
            //注文単位ID： 引数.注文単位ID 
            AioOrderUnitRow l_orderUnitRow = 
                AioOrderUnitDao.findRowByPk(l_lngOrderUnitId);

            AioOrderUnitParams l_orderUnitParams = 
                new AioOrderUnitParams(l_orderUnitRow);

            //１－２）更新内容
            //【ｘTrade】補足資料.DB更新  
            //「FXへの振替_注文単位テーブル.xls」 
            //「FXへの振替_注文単位テーブル_DB更新仕様(結果通知2)」シート 

            //引数.入出金額
            log.debug("引数.入出金額をセット");
            l_orderUnitParams.setQuantity(Double.parseDouble(l_strCashInOutAmount));

            //引数.更新後発注日 != nullの場合、引数.更新後発注日
            //以外、（既存値）
            if (l_strUpdatedBizDate != null)
            {
                log.debug("引数.更新後発注日 != nullの場合");
                l_orderUnitParams.setBizDate(l_strUpdatedBizDate);
            }
            //引数.更新後受渡日 != nullの場合、引数.更新後受渡日
            //以外、（既存値）
            if (l_strUpdatedDeliveryDate != null)
            {
                log.debug("引数.更新後受渡日 != nullの場合");
                Date l_datDeliveryDate = 
                    WEB3DateUtility.getDate(l_strUpdatedDeliveryDate,"yyyyMMdd");
                l_orderUnitParams.setDeliveryDate(l_datDeliveryDate);
            }

            //MQステータスに1：送信済み
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            //更新日付にシステムタイムスタンプをセットする。
            l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            //摘要コードに引数.摘要コードをセットする。
            l_orderUnitParams.setRemarkCode(l_strRemarkCode);
            
            //１－３）該当レコードを更新する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);
            
            //２）注文履歴テーブルの更新 
            //２－１）以下の条件で、注文履歴テーブルからレコードを取得する。 
            //[検索条件] 
            //注文単位ID：　@引数.注文単位ID 
            //注文履歴番号：　@１）で取得した注文単位.注文履歴最終通番 

            List l_lisOrderActionRows = l_queryProcessor.doFindAllQuery(
                AioOrderActionRow.TYPE,
                "order_unit_id = ? and order_action_serial_no = ?",
                null,
                new Object[] {
                    new Long(l_lngOrderUnitId), 
                    new Long(l_orderUnitRow.getLastOrderActionSerialNo())
                    });
            if (l_lisOrderActionRows.size() == 1)
            {
                AioOrderActionRow l_orderActionRow = 
                    (AioOrderActionRow)l_lisOrderActionRows.get(0);
                AioOrderActionParams l_orderActionParams = 
                    new AioOrderActionParams(l_orderActionRow);
                
                //２－２）更新内容は下記を参照。 

                //【ｘTrade】補足資料.DB更新  
                //「FXへの振替_注文履歴テーブル.xls」 
                //「FXへの振替_注文履歴テーブル_DB更新仕様(結果通知2)」シート 

                //引数.入出金額
                log.debug("引数.入出金額をセット");
                l_orderActionParams.setQuantity(Double.parseDouble(l_strCashInOutAmount));

                l_orderActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //２－３）該当レコードを更新する。
                l_queryProcessor.doUpdateQuery(l_orderActionParams);
                
                //３）注文テーブル（ヘッダ）の更新 
                //３－１）以下の条件で、注文テーブル（ヘッダ）からレコードを取得する。 
                //[検索条件] 
                //注文ID　@＝　@１）で取得した注文単位.注文ID 
                AioOrderRow l_orderRow = 
                    AioOrderDao.findRowByPk(l_orderUnitRow.getOrderId());
                
                AioOrderParams l_orderParams = 
                    new AioOrderParams(l_orderRow);
                
                //３－２）更新内容は下記を参照。 

                //【ｘTrade】補足資料.DB更新  
                //「FXへの振替_注文テーブル(ヘッダ).xls」 
                //「FXへの振替_注文テーブル(ヘッダ)_DB更新仕様[結果通知]」シート 

                l_orderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //３－３）該当レコードを更新する。
                l_queryProcessor.doUpdateQuery(l_orderParams);
            }
            
            //４）振替請求注文キューテーブルの更新 
            //４－１）以下の条件で、振替請求注文キューテーブルからレコードを取得する。 
            //[検索条件] 
            //データコード： GI806（保証金振替請求） 
            //証券会社コード： 引数.証券会社コード 
            //部店コード： 引数.部店コード 
            //顧客コード： 引数.顧客コード 
            //識別コード： 引数.識別コード 
            //処理区分： 3（未処理）
            
            String l_strWhere = 
                "request_code = ? and institution_code = ? " +
                "and branch_code = ? and account_code = ? " +
                "and order_request_number = ? and status = ?";   
            
            Object[] l_objBind = 
                new Object[] {
                    WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ORDER, 
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    l_strOrderRequestNumber,
                    WEB3AioHostStatusDef.NOT_DEAL};
            
            List l_lisHostTransferOrderRows = 
                l_queryProcessor.doFindAllQuery(
                    HostTransferOrderRow.TYPE,
                    l_strWhere,
                    null,
                    l_objBind);
            
            //４－２）更新内容は下記を参照。  
            //
            //【ｘTrade】補足資料.DB更新  
            //「FXへの振替_振替請求注文キューテーブル.xls」  
            //「_振替請求注文キューテーブルDB更新(結果通知2)」シート 
            if (l_lisHostTransferOrderRows.size() == 1)
            {
                HostTransferOrderRow l_hostTransferOrderRow = 
                    (HostTransferOrderRow)l_lisHostTransferOrderRows.get(0);
                
                HostTransferOrderParams l_hostTransferOrderParams = 
                    new HostTransferOrderParams(l_hostTransferOrderRow);

                AccountManager l_accMgr = 
                    (AccountManager) GtlUtils.getAccountManager();
                
                MainAccount l_mainAccount =
                    l_accMgr.getMainAccount(l_orderUnitParams.getAccountId());

                WEB3GentradeBranch l_branch =
                    (WEB3GentradeBranch) l_mainAccount.getBranch();

                //原振替月日:
                //取引時間.発注日 = 注文単位.受渡日(MMDD)の場合、ブランクをセット
                //上記以外の場合、注文単位.受渡日（MMDD）をセット
                if (WEB3DateUtility.compareToDay(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(),
                    l_orderUnitParams.getDeliveryDate()) == 0)
                {
                    l_hostTransferOrderParams.setOriginalTransferDate(
                        WEB3AioOriginalTransferDateBlankDef.BLANK);
                }
                else
                {
                    String l_strDeliveryMD = WEB3DateUtility.formatDate(
                        l_orderUnitParams.getDeliveryDate(),
                        WEB3GentradeTimeDef.DATE_FORMAT_MD);
                    l_hostTransferOrderParams.setOriginalTransferDate(l_strDeliveryMD);
                }

                //振替出金額（借方）: 引数.入出金額
                if (l_strCashInOutAmount != null)
                {
                    l_hostTransferOrderParams.setTransferAmountDebitor(
                        Integer.parseInt(l_strCashInOutAmount)); 
                }

                //摘要コード : 引数.摘要コード
                l_hostTransferOrderParams.setRemarkCode(l_strRemarkCode);

                //振替指示日
                //注文単位.発注日 ※時刻部分は、00:00
                l_hostTransferOrderParams.setTransferDate(
                    WEB3DateUtility.getDate(
                        l_orderUnitRow.getBizDate() + "00:00", 
                        "yyyyMMddHH:mm"));
                
                //振替指示時間
                //現在時刻がトリガ発行を実施する時間帯の場合、00:00をセット            
                //※日付部分は、注文単位.発注日
                Date l_datTransferTime = null;
                if (WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
                {
                    log.debug("現在時刻がトリガ発行を実施する時間帯の場合");
                    l_datTransferTime = WEB3DateUtility.getDate(
                        l_orderUnitRow.getBizDate() + "00:00",
                        "yyyyMMddHH:mm");
                }
                //現在時刻がトリガ発行を実施しない時間帯の場合、06:00をセット
                //※日付部分は、注文単位.発注日
                else
                {
                    l_datTransferTime = WEB3DateUtility.getDate(
                        l_orderUnitRow.getBizDate() + "06:00",
                        "yyyyMMddHH:mm");
                }
                l_hostTransferOrderParams.setTransferTime(l_datTransferTime);
                
                //処理区分 = 0:処理中
                l_hostTransferOrderParams.setStatus(
                    WEB3AioHostStatusDef.DEALING); //0:処理中
                
                //４－３）該当レコードを更新する。
                l_queryProcessor.doUpdateQuery(l_hostTransferOrderParams);
                log.debug("updated 振替請求注文キューテーブルRow := " + 
                        l_hostTransferOrderParams);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_ex) {
            log.error("Error In getMainAccount()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(l_strMethodName);

    }
    
    /**
     * (create外貨入金注文キューデータ )<BR>
     * 外貨入金注文のキューデータの生成（DB登録）を行う。<BR>
     * <BR>
     * １）外貨入出金伝票キューParamsインスタンスを生成する。<BR>
     * <BR>
     * ２）外貨入出金伝票キューParamsにプロパティをセットする。<BR>
     *  <BR>
     * ※詳細は、DB更新仕様「外貨入出金伝票キューテーブル.xls」参照<BR>
     * <BR>
     * <BR>
     * ※※※※※※※※※※※※※※※※※※※※※※※※<BR>
     * [入出金注文単位.注文経路が”入金連携”の場合]<BR>
     * 詳細は更新仕様「入金通知_外貨入出金伝票キューテーブル.xls」参照<BR>
     * 入出金方法@、相手方コードのみ以下に特記。<BR>
     * <BR>
     * ・外貨入出金伝票キューParams.入出金方法@<BR>
     * = 金融機@関テーブル.入出金伝票キュー用入出金方法@<BR>
     * ・外貨入金伝票キューParams.相手コード<BR>
     * = 金融機@関テーブル.入出金伝票キュー用相手コード<BR>
     * <BR>
     * ↓<BR>
     * 金融機@関テーブルレコードの取得<BR>
     * 金融機@関テーブルを以下の条件で検索。<BR>
     * [条件]<BR>
     * 証券会社コード = 証券会社.証券会社コード<BR>
     * 金融機@関コード = 入出金注文単位.振替記述<BR>
     * <BR>
     * （入出金注文単位.振替記述はnull可ですが、業務的には値は必ず入れるように実装されます。<BR>
     * しかしシステム的にnullが入る可能性があるので、<BR>
     * 金融機@関テーブル検索時は、以下のようにしてください。）<BR>
     * <BR>
     * １）金融機@関テーブルPKを作成<BR>
     * （コンストラクタ引数：証券会社コード=証券会社.証券会社コード, 金融機@関コード = 入出金注文単位.振替記述）<BR>
     * ２）金融機@関テーブルDaoでPK検索<BR>
     * ３）レコードがない場合、DataFindException発生するため<BR>
     * DataFindExceptionをcatchする句を追加。<BR>
     * WEB3SystemLayerExceptionをスローする。<BR>
     * <BR>
     * <BR>
     * ※※※※※※※※※※※※※※※※※※※※※※※※<BR>
     * <BR>
     * <BR>
     * ３）外貨入出金伝票キューParamsの内容をDBに登録する。<BR>
     * <BR>
     *　@ WEB3DataAccessUtility.insertRow()<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@外貨入出金伝票キューParams<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F647780100
     */
    protected void createHostForeignCashinOrderData(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createHostForeignCashinOrderData"
            + "(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        //１）外貨入出金伝票キューParamsインスタンスを生成する。
        HostForeignCashTransOrderParams l_params = new HostForeignCashTransOrderParams();

        //２）外貨入出金伝票キューParamsにプロパティをセットする
        //詳細は、DB更新仕様「外貨入出金伝票キューテーブル.xls」参照
        //データコード:  入金注文：GI804
        l_params.setRequestCode(WEB3HostRequestCodeDef.FOREIGN_CASH_TRANSFER_ORDER);
        // 入出金注文単位.注文経路が”入金連携”の場合
        AioOrderUnitRow l_aioOrderUnitRow =
            (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (WEB3OrderRootDivDef.CASH_IN_COOPERATION.equals(
            l_aioOrderUnitRow.getOrderRootDiv()))
        {
            try
            {
                AccountManager l_accMgr = (AccountManager)GtlUtils.getAccountManager();
                MainAccount l_mainAccount = l_accMgr.getMainAccount(l_orderUnit.getAccountId());
                String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
                String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
                String l_strAccountCode = l_mainAccount.getAccountCode();

                //証券会社コード:注文単位.部店ＩＤに該当する部店.証券会社コード
                l_params.setInstitutionCode(l_strInstitutionCode);

                //部店コード:   注文単位.部店ＩＤに該当する部店.部店コード
                l_params.setBranchCode(l_strBranchCode);

                //顧客コード:   注文単位.顧客ＩＤに該当する顧客.顧客コード
                l_params.setAccountCode(l_strAccountCode);

                //扱者コード:   ブランク
                l_params.setTraderCode("     ");

                l_params.setOrderRequestNumber(l_aioOrderUnitRow.getOrderRequestNumber());

                // 通貨コード 注文単位.通貨コード
                l_params.setCurrencyCode(l_aioOrderUnitRow.getCurrencyCode());

                // 入出金区分:  2：入金
                l_params.setOrderDiv(WEB3OrderDivDef.CASHIN);

                //出金額（外貨）:       0
                l_params.setDebitAmount(0);

                //入金額（外貨）:       注文単位.数量
                l_params.setCreditAmount(l_aioOrderUnitRow.getQuantity());

                // 摘要コード:   01：銀行送金
                l_params.setRemarkCode(WEB3ForeignCashRemarkCodeDef.BANK_REMITTANCE);

                // 出金額（円換算額）:   0
                l_params.setDebitConvertAmount(0);

                // 入金額(円換算額）:  注文単位.入出金金額(円換算額）
                l_params.setCreditConvertAmount(new Double(l_aioOrderUnitRow.getConvertAmount()).longValue());

                //摘要名（カナ) : ブランク
                l_params.setRemarkName("                       ");

                //入出金日:  注文単位.振替予定日
                String l_strCashTransDate =
                    WEB3DateUtility.formatDate(l_aioOrderUnitRow.getEstTransferDate(),"MMdd");
                l_params.setCashTransDate(l_strCashTransDate);

                //入金区分  :    ブランク
                l_params.setCreditDiv(" ");

                //強制区分  :    ブランク
                l_params.setForceDiv(" ");

                //取消区分  :    ブランク
                l_params.setCancelDiv(" ");

                // １）金融機@関テーブルPKを作成
                //（コンストラクタ引数：証券会社コード=証券会社.証券会社コード,
                // 金融機@関コード = 入出金注文単位.振替記述）
                FinInstitutionPK l_finInstitutionPK = new FinInstitutionPK(
                    l_strInstitutionCode, l_orderUnit.getDescription());
                //金融機@関テーブルDaoでPK検索
                FinInstitutionRow l_finInsRow = FinInstitutionDao.findRowByPk(l_finInstitutionPK);

                // 入出金方法@ = 金融機@関テーブル.入出金伝票キュー用入出金方法@
                l_params.setCashTransferType(l_finInsRow.getCashTransferType());

                // 相手コード = 金融機@関テーブル.入出金伝票キュー用相手コード
                l_params.setSonarCode(l_finInsRow.getCashTransferSonarCode());

                // 為替レート :    ブランク
                l_params.setRate("         ");

                //受注日時  : ThreadLocalSystemAttributesRegistry.getAttribute
                //(”xblocks.gtl.attributes.systemtimestamp”)の戻り値
                l_params.setOrderedTimestamp(GtlUtils.getSystemTimestamp());

                //処理区分:     0：未処理
                l_params.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

                //３）外貨入出金伝票キューParamsの内容をDBに登録する。
                //WEB3DataAccessUtility.insertRow()
                //[引数]
                //外貨入出金伝票キューParams
                WEB3DataAccessUtility.insertRow(l_params);
            }
            catch (NotFoundException l_ex)
            {
                log.error("Error In  getMainAccount()", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataFindException l_ex)
            {
                log.error("Error In  金融機@関テーブルDaoでPK検索", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("Error In 外貨入出金伝票キューParamsの内容をDBに登録する", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex
                );
            }
            catch (DataNetworkException l_ex)
            {
                log.error("Error In 外貨入出金伝票キューParamsの内容をDBに登録する", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex
                );
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create外貨出金注文キューデータ)<BR>
     * 外貨出金注文のキューデータの生成（DB登録）を行う。 <BR>
     * <BR>
     * １）外貨入出金伝票キューParamsインスタンスを生成する。 <BR>
     * <BR>
     * ２）金融機@関テーブルレコードの取得  <BR>
     * 金融機@関テーブルを以下の条件で検索。  <BR>
     * [条件]  <BR>
     * 証券会社コード = 引数.注文単位.部店IDに該当する証券会社コード <BR>
     * 金融機@関コード = ALL’0’ <BR>
     * <BR>
     * <BR>
     * ３）外貨入出金伝票キューParamsにプロパティをセットする。 <BR>
     * <BR>
     * ※詳細は更新仕様「外貨出金申込_外貨入出金伝票キューテーブル.xls」参照  <BR>
     * <BR>
     * ４）外貨入出金伝票キューParamsの内容をDBに登録する。 <BR>
     * <BR>
     *   WEB3DataAccessUtility.insertRow() <BR>
     * <BR>
     *   [引数] <BR>
     *   外貨入出金伝票キューParams<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@throws WEB3BaseException
     */
    protected void createHostForeignCashoutOrderData(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createHostForeignCashoutOrderData"
            + "(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        //１）外貨入出金伝票キューParamsインスタンスを生成する。
        HostForeignCashTransOrderParams l_params = new HostForeignCashTransOrderParams();

        //3）外貨入出金伝票キューParamsにプロパティをセットする。
        //データコード:  入金注文：GI804
        l_params.setRequestCode(WEB3HostRequestCodeDef.FOREIGN_CASH_TRANSFER_ORDER);

        try
        {
            AccountManager l_accMgr = (AccountManager)GtlUtils.getAccountManager();
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_orderUnit.getAccountId());
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            String l_strAccountCode = l_mainAccount.getAccountCode();

            // ２）金融機@関テーブルレコードの取得
            // 金融機@関テーブルを以下の条件で検索。
            // [条件]
            // 証券会社コード = 引数.注文単位.部店IDに該当する証券会社コード
            // 金融機@関コード = ALL’0'
            //金融機@関テーブルDaoでPK検索
            String l_strFinInstitutionCode = "000000000000000";
            FinInstitutionRow l_finInsRow =
                FinInstitutionDao.findRowByPk(l_strInstitutionCode, l_strFinInstitutionCode);

            //証券会社コード:注文単位.部店ＩＤに該当する部店.証券会社コード
            l_params.setInstitutionCode(l_strInstitutionCode);

            //部店コード:   注文単位.部店ＩＤに該当する部店.部店コード
            l_params.setBranchCode(l_strBranchCode);

            //顧客コード:   注文単位.顧客ＩＤに該当する顧客.顧客コード
            l_params.setAccountCode(l_strAccountCode);

            //扱者コード:   ブランク
            l_params.setTraderCode("     ");

            //識別コード:   注文単位.識別コード
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
            l_params.setOrderRequestNumber(l_aioOrderUnitRow.getOrderRequestNumber());

            // 通貨コード 注文単位.通貨コード
            l_params.setCurrencyCode(l_aioOrderUnitRow.getCurrencyCode());
            
            // 入出金区分 1：出金
            l_params.setOrderDiv(WEB3OrderDivDef.CASHOUT);

            //出金額（外貨）:      注文単位.注文数量
            l_params.setDebitAmount(l_aioOrderUnitRow.getQuantity());

            //入金額（外貨）:       0
            l_params.setCreditAmount(0);

            // 摘要コード:   01:銀行送金
            l_params.setRemarkCode(WEB3ForeignCashRemarkCodeDef.BANK_REMITTANCE);

            // 出金額（円換算額）:   注文単位.入出金金額(円換算額）
            l_params.setDebitConvertAmount(new Double(l_aioOrderUnitRow.getConvertAmount()).longValue());

            // 入金額(円換算額）:  0
            l_params.setCreditConvertAmount(0);

            //摘要名（カナ) : ブランク
            l_params.setRemarkName("                       ");

            //入出金日:  注文単位.振替予定日
            Timestamp l_tsEstTransferDate = l_aioOrderUnitRow.getEstTransferDate();
            l_params.setCashTransDate(WEB3DateUtility.formatDate(l_tsEstTransferDate, "MMdd"));

            //入金区分  :    ブランク
            l_params.setCreditDiv(" ");

            //強制区分  :    ブランク
            l_params.setForceDiv(" ");

            //取消区分  :    ブランク
            l_params.setCancelDiv(" ");

            //入出金方法@ cash_transfer_type  金融機@関テーブル.入出金伝票キュー用入出金方法@                                                                     
            l_params.setCashTransferType(l_finInsRow.getCashTransferType());

            //相手 sonar_code 金融機@関テーブル.入出金伝票キュー用入金連携相手コード                                                                     
            l_params.setSonarCode(l_finInsRow.getCashTransferSonarCode());

            // 為替レート :    ブランク
            l_params.setRate("         ");

            //受注日時  : 現在時刻（システムタイムスタンプ）
            l_params.setOrderedTimestamp(GtlUtils.getSystemTimestamp());

            //処理区分:     0：未処理
            l_params.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

            //4）外貨入出金伝票キューParamsの内容をDBに登録する。
            //WEB3DataAccessUtility.insertRow()
            //[引数]
            //外貨入出金伝票キューParams
            WEB3DataAccessUtility.insertRow(l_params);
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In  getMainAccount()", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("Error In  金融機@関テーブルDaoでPK検索", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In 外貨入出金伝票キューParamsの内容をDBに登録する", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In 外貨入出金伝票キューParamsの内容をDBに登録する", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * (updateMQステータス)<BR>    
     * 注文単位テーブルのMQステータスを'1'（送信済み）に更新する。<BR>
     * <BR>
     * １）注文単位テーブルの更新<BR> 
     * <BR>
     * １－１）以下の条件で、注文単位テーブルからレコードを取得する。<BR> 
     * <BR>
     * 　@[検索条件]<BR> 
     * 　@注文単位ID： 引数.注文単位ID<BR>
     * <BR>
     * １－２）MQステータスに'1'を、更新日付にシステムタイムスタンプをセットする。<BR> 
     * <BR>
     * １－３）該当レコードを更新する。<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (注文単位ID)
     * @@throws WEB3BaseException
     */
    public void updateMQStatus(long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateMQStatus(long l_lngOrderUnitId)";
        log.entering(STR_METHOD_NAME);
        
        try{
            // １－１）以下の条件で、注文単位テーブルからレコードを取得する。 
            // [検索条件] 
            // 注文単位ID： 引数.注文単位ID 
            AioOrderUnitRow l_orderUnitRow = 
                AioOrderUnitDao.findRowByPk(l_lngOrderUnitId);
            log.debug("注文単位テーブルRow := " + l_orderUnitRow);
            
            AioOrderUnitParams l_orderUnitParams = new AioOrderUnitParams(l_orderUnitRow);
            //１－２）MQステータスに'1'を、更新日付にシステムタイムスタンプをセットする。 
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //１－３）該当レコードを更新する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);
            log.debug("updated 注文単位テーブルRow := " + l_orderUnitParams);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException Error ", l_ex);
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException Error In", l_ex);
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

}@
