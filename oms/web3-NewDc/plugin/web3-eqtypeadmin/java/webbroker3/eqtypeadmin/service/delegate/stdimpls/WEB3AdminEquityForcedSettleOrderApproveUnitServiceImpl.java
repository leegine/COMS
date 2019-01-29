head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済仮注文承認／非承認一件サービスImpl(WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 張騰宇 (中訊) 新規作成 仕様変更モデルNo.129
Revision History : 2007/05/16 張騰宇 (中訊) モデル152
Revision History : 2007/05/28 張騰宇 (中訊) モデル155
Revision History : 2008/12/10 張少傑 (中訊) モデル215
Revision History : 2009/11/19 車進    (中訊) 【トリガー注文・株管理者】逆指値注文発注障害対応
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.DefaultEqTypeSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ApproveStatusType;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExpirationReasonCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveUnitService;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.marketadaptor.WEB3EquityMarketRequestSenderServiceImpl;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・強制決済仮注文承認／非承認一件サービスImpl)<BR>
 * 管理者・強制決済仮注文承認／非承認一件サービス実装クラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl
    implements WEB3AdminEquityForcedSettleOrderApproveUnitService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl.class);

    /**
     * @@roseuid 462CA4240321
     */
    public WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl()
    {

    }

    /**
     * (exec承認)<BR>
     * 強制決済仮注文承認処理を行う。<BR>
     * （戻り値　@false：エラーなし　@true：エラーあり）<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者強制決済仮注文承認／非承認一件サービス）exec承認」参照。<BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :1.15.1.4　@send強制決済注文<BR>
     * 　@　@　@　@再取得した注文単位の注文単位Rowを使用すること。<BR>
     * 　@　@　@　@また、市場送信処理に失敗した場合、<BR>
     * 　@　@　@　@「新規注文市場メッセージ送信失敗。」の例外をスローする。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException       <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_00237         <BR>
     * =============================================== <BR>
     * @@param l_forcedSettleOrderRow - (強制決済注文Row)<BR>
     * 強制決済注文Rowオブジェクト<BR>
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 46076E8302A9
     */
    public boolean execApprove(
        AdminEqForcedSettleOrderRow l_forcedSettleOrderRow,
        WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execApprove(AdminEqForcedSettleOrderRow, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        if (l_forcedSettleOrderRow == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        EqtypeOrderUnitRow l_eqtypeOrderUnitRow  = null;
        EqTypeOrderUnit l_eqOrderUnit = null;
        WEB3GentradeSubAccount l_subAccount = null;
        WEB3MarginSettleContractOrderSpec l_settleContractOrderSpec = null;
        WEB3EquityContract l_contract = null;
        EqTypeSettleContractOrderEntry[] l_contractOrderEntryList = null;
        WEB3EquityRealizedProfitAndLossPrice l_realizedProfitAndLossPrice = null;
        WEB3GentradeMainAccount l_account = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        try
        {
            try
            {
                // get顧客(口座ID : )
                l_account = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                    l_forcedSettleOrderRow.getAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }


            //lock口座(証券会社コード : String, 部店コード : String, 口座コード : String)
            l_accountManager.lockAccount(
                l_account.getInstitution().getInstitutionCode(),
                l_account.getBranch().getBranchCode(),
                l_account.getAccountCode());

            try
            {
                //getOrderUnit(arg0 : long)
                l_eqOrderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_forcedSettleOrderRow.getOrderUnitId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }


            //is承認／非承認処理対象注文(EqtypeOrderUnitRow)
            l_eqtypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();
            boolean l_blnIsApproveDealObjectOrder =
                WEB3AdminPMEquityDataManager.isApproveProcessTargetOrder(l_eqtypeOrderUnitRow);

            //is承認／非承認処理対象注文()の戻り値 == falseの場合
            if (!l_blnIsApproveDealObjectOrder)
            {
                //処理対象外である為、何もせずreturnする。
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            //create決済建株エントリ(注文単位ID : long)
            l_contractOrderEntryList =
                l_positionManager.createCloseMarginContractEntry(l_eqtypeOrderUnitRow.getOrderUnitId());

            try
            {
                //create返済注文内容
                //(*)引数設定仕様
                //扱者：　@注文単位Row.取引者IDに該当する扱者　@※nullの場合はnullをセット。
                //決済建株エントリ：　@create決済建株エントリ()の戻り値
                //指値：　@注文単位Row.指値
                //執行条件：　@注文単位Row.執行条件
                //注文失効日：　@注文単位Row.注文失効日付
                //税区分：　@注文単位Row.税区分
                //値段条件：　@注文単位Row.値段条件
                //発注条件：　@注文単位Row.発注条件
                //発注条件演算子：　@注文単位Row.発注条件演算子
                //逆指値基準値：　@注文単位Row.逆指値基準値
                //（W指値）訂正指値：　@注文単位Row.（W指値）訂正指値
                //決済順序区分：　@注文単位Row.決済順序区分
                //初回注文の注文単位ID：　@注文単位Row.初回注文の注文単位ID
                //（W指値）執行条件：　@注文単位Row.（W指値）執行条件
                WEB3GentradeTrader l_trader = null;
                if (!l_eqtypeOrderUnitRow.getTraderIdIsNull())
                {
                    l_trader = (WEB3GentradeTrader)l_finObjectManager.getTrader(l_eqtypeOrderUnitRow.getTraderId());
                }

                Long l_firstOrderUnitId = null;
                if (!l_eqtypeOrderUnitRow.getFirstOrderUnitIdIsNull())
                {
                    l_firstOrderUnitId = new Long(l_eqtypeOrderUnitRow.getFirstOrderUnitId());
                }

                l_settleContractOrderSpec =
                    WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                        l_trader,
                        l_contractOrderEntryList,
                        l_eqtypeOrderUnitRow.getLimitPrice(),
                        l_eqtypeOrderUnitRow.getExecutionConditionType(),
                        l_eqtypeOrderUnitRow.getExpirationDate(),
                        l_eqtypeOrderUnitRow.getTaxType(),
                        l_eqtypeOrderUnitRow.getPriceConditionType(),
                        l_eqtypeOrderUnitRow.getOrderConditionType(),
                        l_eqtypeOrderUnitRow.getOrderCondOperator(),
                        l_eqtypeOrderUnitRow.getStopOrderPrice(),
                        l_eqtypeOrderUnitRow.getWLimitPrice(),
                        l_eqtypeOrderUnitRow.getClosingOrderType(),
                        l_firstOrderUnitId,
                        l_eqtypeOrderUnitRow.getWLimitExecCondType());

                //get建株(建株ID : long)
                //建株ID：　@create決済建株エントリ()の戻り値の0番目の要素.建株ID
                l_contract =
                    (WEB3EquityContract)l_positionManager.getContract(l_contractOrderEntryList[0].getContractId());

                //get補助口座(口座ID : long, 補助口座ID : long)
                l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_eqtypeOrderUnitRow.getAccountId(),
                    l_eqtypeOrderUnitRow.getSubAccountId());

            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            try
            {
                //validate返済注文(補助口座 : SubAccount, 信用返済注文内容
                //[引数]
                //補助口座：　@get補助口座()の戻り値
                //信用返済注文内容：　@create返済注文内容()の戻り値
                //建株：　@get建株()の戻り値
                EqTypeNewOrderValidationResult l_validationResult =
                    l_orderManager.validateSettleContractOrder(l_subAccount, l_settleContractOrderSpec, l_contract);
                if (l_validationResult.getProcessingResult().isFailedResult())
                {
                    throw new WEB3BusinessLayerException(
                        l_validationResult.getProcessingResult().getErrorInfo(),
                        STR_METHOD_NAME);
                }

                // getTradedProduct( )
                WEB3EquityTradedProduct l_tradedProduct =
                    (WEB3EquityTradedProduct)l_contract.getTradedProduct();

                //validate決済総建株数
                //[引数]
                //補助口座：　@get補助口座()の戻り値
                //注文単位ID：　@注文単位Row.注文単位ID（二重拘束されないよう、訂正時と同様の処理を行う。）
                //取引銘柄：　@getTradedProduct()の戻り値
                //税区分：　@信用返済注文内容.getTaxType( )
                //弁済区分：　@建株.弁済区分
                //弁済期限値：　@建株.弁済期限値
                //株数：　@信用返済注文内容.getTotalQuantity( )
                //建区分：　@建株.建区分
                EqtypeContractRow l_contractRow =
                    (EqtypeContractRow)l_contract.getDataSourceObject();
                WEB3EquityTypeOrderManagerReusableValidations l_reusableValidations =
                    (WEB3EquityTypeOrderManagerReusableValidations)
                        WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                l_reusableValidations.validateSettleContractTotalQuantity(
                    l_subAccount,
                    l_eqtypeOrderUnitRow.getOrderUnitId(),
                    l_tradedProduct,
                    l_settleContractOrderSpec.getTaxType(),
                    l_contractRow.getRepaymentType(),
                    l_contractRow.getRepaymentNum(),
                    l_settleContractOrderSpec.getTotalQuantity(),
                    l_contractRow.getContractType());

                //create手数料(注文単位 : 注文単位)
                //注文単位：　@注文単位Rowより生成した注文単位オブジェクト
                WEB3GentradeCommission l_commission =
                    l_bizLogicProvider.createCommission((EqTypeOrderUnitImpl)l_eqOrderUnit);

                //calc概算決済損益代金
                //(*)引数設定仕様
                //手数料：　@create手数料()の戻り値
                //指値：　@注文単位Row.指値
                //補助口座：　@get補助口座()の戻り値
                //取引銘柄：　@getTradedProduct()の戻り値
                //決済建株エントリ：　@create決済建株エントリ()の戻り値
                //数量：　@返済注文内容.getTotalQuantity()
                //注文単位：　@null（固定）
                //今回約定数量：　@0
                //今回約定単価：　@0
                //isSkip金額チェック：　@false（スキップしない）
                l_realizedProfitAndLossPrice = l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    l_eqtypeOrderUnitRow.getLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_contractOrderEntryList,
                    l_settleContractOrderSpec.getTotalQuantity(),
                    null,
                    0,
                    0,
                    false);

                //正常終了した場合
                try
                {
                    //get発注経路区分(株式取引銘柄 : 取引銘柄, 証券会社コード : String, 取引コード（SONAR） : String)
                    //[引数]
                    //株式取引銘柄：　@getTradedProduct()の戻り値
                    //証券会社コード：　@顧客.証券会社コード
                    //取引コード（SONAR）：　@注文単位Rowの同名項目
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                    String l_strSubmitOrderRouteDiv = l_frontOrderService.getSubmitOrderRouteDiv(
                        l_tradedProduct,
                        l_account.getInstitution().getInstitutionCode(),
                        l_eqtypeOrderUnitRow.getSonarTradedCode());

                    //update注文データ(管理者, EqtypeOrderUnitRow, 概算決済損益代金計算結果, String, boolean)
                    //[引数]
                    //管理者：　@取得した管理者オブジェクト
                    //注文単位Row：　@注文単位Row
                    //概算代金計算結果：　@calc概算決済損益代金()の戻り値
                    //発注経路区分：　@get発注経路区分()の戻り値
                    //is承認：　@true
                    this.updateOrderData(
                        l_admin,
                        l_eqtypeOrderUnitRow,
                        l_realizedProfitAndLossPrice,
                        l_strSubmitOrderRouteDiv, true);
                    try
                    {
                        //getOrderUnit(arg0 : long)
                        //更新後の注文単位を再取得する。
                        //[引数]
                        //arg0：　@注文単位Row.注文単位ID
                        l_eqOrderUnit =
                            (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_eqtypeOrderUnitRow.getOrderUnitId());
                    }
                    catch (NotFoundException l_ex)
                    {
                        log.error(l_ex.getMessage(), l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }

                    //send強制決済注文(補助口座, 取引銘柄, EqtypeOrderUnitRow)
                    //強制決済注文を市場に送信する。
                    //[引数]
                    //補助口座：　@get補助口座()の戻り値
                    //取引銘柄：　@getTradedProduct()の戻り値
                    //注文単位Row：　@注文単位Row
                    EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();
                    MarketRequestSendResult l_result =
                        this.sendForcedSettleOrder(l_subAccount, l_tradedProduct, l_orderUnitRow);

                    //再取得した注文単位の注文単位Rowを使用すること。
                    //また、市場送信処理に失敗した場合、
                    //「新規注文市場メッセージ送信失敗。」の例外をスローする。
                    if (l_result.getProcessingResult().isFailedResult())
                    {
                        log.error(STR_METHOD_NAME + " 新規注文市場メッセージ送信失敗。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00237,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "新規注文市場メッセージ送信失敗。");
                    }
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
            catch (WEB3BaseException l_ex)
            {
                //処理中に例外がスローされた場合
                //update注文失効(管理者, EqtypeOrderUnitRow, String, boolean)
                //[引数]
                //管理者：　@取得した管理者オブジェクト
                //注文単位Row：　@注文単位Row
                //エラーコード：　@スローされた例外クラスより取得したエラーコード
                //is承認：　@true（承認）
                this.updateOrderExpire(
                    l_admin,
                    l_eqtypeOrderUnitRow,
                    l_ex.getErrorInfo().getErrorCode(),
                    true);
            }
            //余力再計算(補助口座 : 補助口座)
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
            l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (exec非承認)<BR>
     * 強制決済仮注文非承認処理を行う。<BR>
     * （戻り値　@false：エラーなし　@true：エラーあり）<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者強制決済仮注文承認／非承認一件サービス）exec非承認」参照。<BR>
     * @@param l_forcedSettleOrderRow - (強制決済注文Row)<BR>
     * 強制決済注文Rowオブジェクト<BR>
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 46076E8302C8
     */
    public boolean execDisapprove(
        AdminEqForcedSettleOrderRow l_forcedSettleOrderRow,
        WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execDisapprove(AdminEqForcedSettleOrderRow, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        if (l_forcedSettleOrderRow == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        WEB3GentradeMainAccount l_account = null;
        WEB3GentradeSubAccount l_subAccount = null;
        EqTypeOrderUnit l_eqOrderUnit = null;
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = null;
        try
        {
            //get顧客(口座ID : )
            //口座ID：　@パラメータ.強制決済注文Row.口座ID
            l_account = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                l_forcedSettleOrderRow.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //lock口座(証券会社コード : String, 部店コード : String, 口座コード : String)
        l_accountManager.lockAccount(
            l_account.getInstitution().getInstitutionCode(),
            l_account.getBranch().getBranchCode(),
            l_account.getAccountCode());
        try
        {
            //getOrderUnit(arg0 : long)
            l_eqOrderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_forcedSettleOrderRow.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //is承認／非承認処理対象注文(EqtypeOrderUnitRow)
        l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();
        boolean l_blnIsApproveDealObjectOrder =
            WEB3AdminPMEquityDataManager.isApproveProcessTargetOrder(l_eqtypeOrderUnitRow);

        //is承認／非承認処理対象注文()の戻り値 == falseの場合
        if (!l_blnIsApproveDealObjectOrder)
        {
            //処理対象外である為、何もせずreturnする。
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //update注文データ(管理者, EqtypeOrderUnitRow, 概算決済損益代金計算結果, String, boolean)
        //[引数]
        //管理者：　@取得した管理者オブジェクト
        //注文単位Row：　@注文単位Row
        //概算代金計算結果：　@null
        //発注経路区分：　@null
        //is承認：　@false（非承認）
        this.updateOrderData(l_admin, l_eqtypeOrderUnitRow, null, null, false);
        try
        {
            //get補助口座(口座ID : long, 補助口座ID : long)
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                l_eqtypeOrderUnitRow.getAccountId(),
                l_eqtypeOrderUnitRow.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //余力再計算(補助口座 : 補助口座)
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (update注文データ)<BR>
     * 注文データを承認／非承認状態にupdateする。<BR>
     * <BR>
     * １）　@承認処理（パラメータ.is承認 == true）の場合、<BR>
     * 　@以下の処理を実施する。<BR>
     * 　@１－１）　@更新後の注文単位Paramsの作成<BR>
     * 　@　@パラメータ.注文単位Rowの内容をコピーした<BR>
     * 　@　@注文単位Paramsを生成し、更新値をセットする。<BR>
     * 　@　@更新内容は、DB更新仕様<BR>
     * 　@　@「仮注文承認_株式注文単位テーブル仕様.xls」参照。<BR>
     * <BR>
     * 　@１－２）　@注文履歴Paramsの生成<BR>
     * 　@　@注文履歴Paramsを生成し、更新値をセットする。<BR>
     * 　@　@更新内容は、DB更新仕様<BR>
     * 　@　@「仮注文承認_株式注文履歴テーブル.xls」参照。<BR>
     * <BR>
     * 　@１－３）　@拡張株式注文マネージャ.update注文データ()<BR>
     * 　@　@メソッドをコールする。<BR>
     * <BR>
     * 　@　@[update注文データ()に指定する引数]<BR>
     * 　@　@　@注文単位：　@更新値をセットした注文単位Paramsより<BR>
     * 　@　@　@　@生成した注文単位オブジェクト<BR>
     * 　@　@　@注文履歴：　@更新値をセットした注文履歴Paramsより<BR>
     * 　@　@　@　@生成した注文履歴オブジェクト<BR>
     * <BR>
     * ２）　@非承認処理（１）以外）の場合、<BR>
     * 　@以下の処理を実施する。<BR>
     * 　@２－１）　@this.update注文失効()に<BR>
     * 　@　@処理を委譲する。<BR>
     * 　@　@[update注文失効()に指定する引数]<BR>
     * 　@　@　@管理者：　@パラメータ.管理者<BR>
     * 　@　@　@注文単位Row：　@パラメータ.注文単位Row<BR>
     * 　@　@　@エラーコード：　@null<BR>
     * 　@　@　@is承認：　@パラメータ.is承認<BR>
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@param l_eqtypeOrderUnitRow - (注文単位Row)<BR>
     * 注文単位Rowオブジェクト<BR>
     * @@param l_equityRealizedProfitAndLossPrice - (概算代金計算結果)<BR>
     * 概算決済損益代金計算結果オブジェクト<BR>
     * @@param l_strSubmitOrderRouteDiv - (発注経路区分)<BR>
     * 発注経路区分<BR>
     * @@param l_blnIsApprove - (is承認)<BR>
     * 承認処理かどうかのフラグ<BR>
     * <BR>
     * false：　@非承認<BR>
     * true：　@承認<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4608AD940143
     */
    protected void updateOrderData(
        WEB3Administrator l_admin,
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow,
        WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice,
        String l_strSubmitOrderRouteDiv,
        boolean l_blnIsApprove) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderData(WEB3Administrator, EqtypeOrderUnitRow" +
            "WEB3EquityRealizedProfitAndLossPrice, String, boolean)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //１）　@承認処理（パラメータ.is承認 == true）の場合、
        //　@以下の処理を実施する。
        //　@１－１）　@更新後の注文単位Paramsの作成
        //　@　@パラメータ.注文単位Rowの内容をコピーした
        //　@　@注文単位Paramsを生成し、更新値をセットする。
        //　@　@更新内容は、DB更新仕様
        //　@　@「仮注文承認_株式注文単位テーブル仕様.xls」参照。
        if (l_blnIsApprove)
        {
            EqtypeOrderUnitParams l_orderUnitParams =
                new EqtypeOrderUnitParams(l_eqtypeOrderUnitRow);
            //注文履歴最終通番  last_order_action_serial_no  （既存値） + 1
            l_orderUnitParams.setLastOrderActionSerialNo(
                l_eqtypeOrderUnitRow.getLastOrderActionSerialNo() + 1);

            //注文単価    price パラメータ.概算決済損益代金計算結果.計算単価
            l_orderUnitParams.setPrice(l_equityRealizedProfitAndLossPrice.getCalcUnitPrice());

            //概算受渡代金   estimated_price   パラメータ.概算決済損益代金計算結果.概算決済損益代金
            l_orderUnitParams.setEstimatedPrice(
                l_equityRealizedProfitAndLossPrice.getEstimatedRealizedProfitAndLossAmount());

            //発注経路区分   submit_order_route_div  パラメータ.発注経路区分
            l_orderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);

            //注文エラー理由コード  error_reason_code  0000：正常
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

            //更新日付  現在時刻
            l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            // 承認状態区分  approve_status_type  1：承認済
            l_orderUnitParams.setApproveStatusType(WEB3ApproveStatusType.APPROVED);

            //承認者コード  approver_code  パラメータ.管理者.管理者コード
            l_orderUnitParams.setApproverCode(l_admin.getAdministratorCode());

            //承認／非承認日時  approve_timestamp 現在時刻
            l_orderUnitParams.setApproveTimestamp(GtlUtils.getSystemTimestamp());

            //　@１－２）　@注文履歴Paramsの生成
            //　@　@注文履歴Paramsを生成し、更新値をセットする。
            //　@　@更新内容は、DB更新仕様
            //　@　@「仮注文承認_株式注文履歴テーブル.xls」参照。
            EqtypeOrderActionParams l_orderActionParams = new EqtypeOrderActionParams();
            try
            {
                long l_lngOrderActionId = EqtypeOrderActionDao.newPkValue();
                l_orderActionParams.setOrderActionId(l_lngOrderActionId);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            l_orderActionParams.setAccountId(l_orderUnitParams.getAccountId());
            l_orderActionParams.setSubAccountId(l_orderUnitParams.getSubAccountId());
            if (l_orderUnitParams.getTraderIdIsNull())
            {
                l_orderActionParams.setTraderId(null);
            }
            else
            {
                l_orderActionParams.setTraderId(l_orderUnitParams.getTraderId());
            }
            l_orderActionParams.setOrderId(l_orderUnitParams.getOrderId());
            l_orderActionParams.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
            if (l_orderUnitParams.getMarketIdIsNull())
            {
                l_orderActionParams.setMarketId(null);
            }
            else
            {
                l_orderActionParams.setMarketId(l_orderUnitParams.getMarketId());
            }
            l_orderActionParams.setOrderType(l_orderUnitParams.getOrderType());
            l_orderActionParams.setOrderEventType(OrderEventTypeEnum.APPROVED);
            if (l_orderUnitParams.getLimitPriceIsNull())
            {
                l_orderActionParams.setPrice(null);
            }
            else
            {
                l_orderActionParams.setPrice(l_orderUnitParams.getLimitPrice());
            }
            l_orderActionParams.setExecutionConditionType(l_orderUnitParams.getExecutionConditionType());
            l_orderActionParams.setPriceConditionType(l_orderUnitParams.getPriceConditionType());
            l_orderActionParams.setOrderConditionType(l_orderUnitParams.getOrderConditionType());
            l_orderActionParams.setOrderCondOperator(l_orderUnitParams.getOrderCondOperator());
            if (l_orderUnitParams.getStopOrderPriceIsNull())
            {
                l_orderActionParams.setStopOrderPrice(null);
            }
            else
            {
                l_orderActionParams.setStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
            }
            if (l_orderUnitParams.getWLimitPriceIsNull())
            {
                l_orderActionParams.setWLimitPrice(null);
            }
            else
            {
                l_orderActionParams.setWLimitPrice(l_orderUnitParams.getWLimitPrice());
            }
            l_orderActionParams.setExpirationDate(l_orderUnitParams.getExpirationDate());
            l_orderActionParams.setQuantity(l_orderUnitParams.getQuantity());
            if (l_orderUnitParams.getConfirmedPriceIsNull())
            {
                l_orderActionParams.setConfirmedPrice(null);
            }
            else
            {
                l_orderActionParams.setConfirmedPrice(l_orderUnitParams.getConfirmedPrice());
            }
            if (l_orderUnitParams.getConfirmedQuantityIsNull())
            {
                l_orderActionParams.setConfirmedQuantity(null);
            }
            else
            {
                l_orderActionParams.setConfirmedQuantity(l_orderUnitParams.getConfirmedQuantity());
            }
            l_orderActionParams.setExecutedQuantity(null);
            l_orderActionParams.setOrderStatus(l_orderUnitParams.getOrderStatus());
            l_orderActionParams.setExpirationStatus(l_orderUnitParams.getExpirationStatus());
            l_orderActionParams.setOrderActionSerialNo(l_orderUnitParams.getLastOrderActionSerialNo());
            l_orderActionParams.setExecutedPrice(null);
            l_orderActionParams.setProductType(l_orderUnitParams.getProductType());
            l_orderActionParams.setProductId(l_orderUnitParams.getProductId());
            l_orderActionParams.setQuantityType(l_orderUnitParams.getQuantityType());
            if (l_orderUnitParams.getEstimatedPriceIsNull())
            {
                l_orderActionParams.setEstimatedPrice(null);
            }
            else
            {
                l_orderActionParams.setEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
            }
            l_orderActionParams.setModifyCancelType(l_orderUnitParams.getModifyCancelType());
            l_orderActionParams.setOrderRootDiv(l_orderUnitParams.getOrderRootDiv());
            l_orderActionParams.setClosingOrderType(l_orderUnitParams.getClosingOrderType());
            l_orderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            l_orderActionParams.setRequestType(l_orderUnitParams.getRequestType());
            l_orderActionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_orderActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_orderActionParams.setIpAddress(null);
            l_orderActionParams.setOrgOrderConditionType(l_orderUnitParams.getOrgOrderConditionType());
            l_orderActionParams.setOrgOrderCondOperator(l_orderUnitParams.getOrgOrderCondOperator());
            if (l_orderUnitParams.getOrgStopOrderPriceIsNull())
            {
                l_orderActionParams.setOrgStopOrderPrice(null);
            }
            else
            {
                l_orderActionParams.setOrgStopOrderPrice(l_orderUnitParams.getOrgStopOrderPrice());
            }
            if (l_orderUnitParams.getOrgWLimitPriceIsNull())
            {
                l_orderActionParams.setOrgWLimitPrice(null);
            }
            else
            {
                l_orderActionParams.setOrgWLimitPrice(l_orderUnitParams.getOrgWLimitPrice());
            }
            l_orderActionParams.setOrgWLimitExecCondType(l_orderUnitParams.getOrgWLimitExecCondType());
            l_orderActionParams.setWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());
            if (l_orderUnitParams.getWLimitBeforeLimitPriceIsNull())
            {
                l_orderActionParams.setWLimitBeforeLimitPrice(null);
            }
            else
            {
                l_orderActionParams.setWLimitBeforeLimitPrice(l_orderUnitParams.getWLimitBeforeLimitPrice());
            }
            l_orderActionParams.setWLimitBeforeExecCondType(l_orderUnitParams.getWLimitBeforeExecCondType());
            l_orderActionParams.setConfirmedExecConditionType(l_orderUnitParams.getConfirmedExecConditionType());

            //　@１－３）　@拡張株式注文マネージャ.update注文データ()メソッドをコールする。
            EqTypeOrderUnit l_eqTypeOrderUnit =
               (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);
            EqTypeOrderAction l_eqTypeOrderAction =
                (EqTypeOrderAction)l_orderManager.toOrderAction(l_orderActionParams);

            l_orderManager.updateOrderData(l_eqTypeOrderUnit, l_eqTypeOrderAction);
        }
        else
        {
            //２）　@非承認処理（１）以外）の場合、
            //　@以下の処理を実施する。
            //　@２－１）　@this.update注文失効()に
            //　@　@処理を委譲する。
            //　@　@[update注文失効()に指定する引数]
            //　@　@　@管理者：　@パラメータ.管理者
            //　@　@　@注文単位Row：　@パラメータ.注文単位Row
            //　@　@　@エラーコード：　@null
            //　@　@　@is承認：　@パラメータ.is承認
            this.updateOrderExpire(l_admin, l_eqtypeOrderUnitRow, null, l_blnIsApprove);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (send強制決済注文)<BR>
     * 強制決済注文を市場に送信する。<BR>
     * <BR>
     * １）　@DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec<BR>
     * 　@インスタンスを生成する。<BR>
     * <BR>
     * 　@[コンストラクタに指定する引数]<BR>
     * 　@　@注文単位ID：　@パラメータ.注文単位Row.注文単位ID<BR>
     * 　@　@数量：　@パラメータ.注文単位Row.注文数量<BR>
     * 　@　@指値：　@　@パラメータ.注文単位Row.指値<BR>
     * 　@　@取引銘柄：　@パラメータ.取引銘柄<BR>
     * 　@　@is買建：<BR>
     * 　@　@　@パラメータ.注文単位Row.注文種別 == "買建売返済"の場合、true。<BR>
     * 　@　@　@以外、false。<BR>
     * 　@　@執行条件：　@パラメータ.注文単位Row.執行条件<BR>
     * 　@　@注文失効日付：　@パラメータ.注文単位Row.注文失効日付<BR>
     * 　@　@注文単位Params：　@パラメータ.注文単位Row<BR>
     * <BR>
     * ２）　@DefaultEqTypeSettleContractOrderMarketRequestMessageを生成する。<BR>
     * <BR>
     * 　@[コンストラクタに指定する引数]<BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * 　@　@注文ID：　@パラメータ.注文単位Row.注文ID<BR>
     * 　@　@注文内容：　@１）にて生成したインスタンス<BR>
     * <BR>
     * ３）　@EqTypeMarketRequestSenderServiceを取得する。<BR>
     * 　@※株式市場リクエスト送信サービス<BR>
     * <BR>
     * ４）　@３）にて取得したサービス.send()メソッドをコールする。<BR>
     * <BR>
     * 　@[send()に指定する引数]<BR>
     * 　@　@arg0：　@２）にて生成したMessageインスタンス<BR>
     * <BR>
     * ５）　@４）のメソッドの戻り値を返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト<BR>
     * @@param l_eqtypeOrderUnitRow - (注文単位Row)<BR>
     * 注文単位Rowオブジェクト<BR>
     * @@return MarketRequestSendResult
     * @@roseuid 4608B6E00058
     */
    protected MarketRequestSendResult sendForcedSettleOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3EquityTradedProduct l_tradedProduct,
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow)
    {
        final String STR_METHOD_NAME = "sendForcedSettleOrder(WEB3GentradeSubAccount," +
            "EqtypeTradedProduct, EqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        //１）　@DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec
        //　@インスタンスを生成する。
        //　@[コンストラクタに指定する引数]
        //　@　@注文単位ID：　@パラメータ.注文単位Row.注文単位ID
        //　@　@数量：　@パラメータ.注文単位Row.注文数量
        //　@　@指値：　@　@パラメータ.注文単位Row.指値
        //　@　@取引銘柄：　@パラメータ.取引銘柄
        //　@　@is買建：
        //　@　@　@パラメータ.注文単位Row.注文種別 == "買建売返済"の場合、true。
        //　@　@　@以外、false。
        //　@　@執行条件：　@パラメータ.注文単位Row.執行条件
        //　@　@注文失効日付：　@パラメータ.注文単位Row.注文失効日付
        //　@　@注文単位Params：　@パラメータ.注文単位Row
        boolean l_blnIsBuy = false;
        if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_eqtypeOrderUnitRow.getOrderType()))
        {
            l_blnIsBuy = true;
        }
        DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec l_settleOrderUnitSpec =
            new DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec(
                l_eqtypeOrderUnitRow.getOrderUnitId(),
                l_eqtypeOrderUnitRow.getQuantity(),
                l_eqtypeOrderUnitRow.getLimitPrice(),
                l_tradedProduct,
                l_blnIsBuy,
                l_eqtypeOrderUnitRow.getExecutionConditionType(),
                l_eqtypeOrderUnitRow.getExpirationDate(),
                l_eqtypeOrderUnitRow);

        //２）　@DefaultEqTypeSettleContractOrderMarketRequestMessageを生成する。
        //　@[コンストラクタに指定する引数]
        //　@　@補助口座：　@パラメータ.補助口座
        //　@　@注文ID：　@パラメータ.注文単位Row.注文ID
        //　@　@注文内容：　@１）にて生成したインスタンス
        DefaultEqTypeSettleContractOrderMarketRequestMessage l_orderMarketRequestMessage =
            new DefaultEqTypeSettleContractOrderMarketRequestMessage(
                l_subAccount,
                l_eqtypeOrderUnitRow.getOrderId(),
                l_settleOrderUnitSpec);

        //３）　@EqTypeMarketRequestSenderServiceを取得する。
        //　@※株式市場リクエスト送信サービス
        WEB3EquityMarketRequestSenderServiceImpl l_marketRequestSenderService = 
            (WEB3EquityMarketRequestSenderServiceImpl) GtlUtils.getTradingModule(
                ProductTypeEnum.EQUITY).getMarketAdapter().getMarketRequestSenderServce();

        //４）　@３）にて取得したサービス.send()メソッドをコールする。
        //　@[send()に指定する引数]
        //　@　@arg0：　@２）にて生成したMessageインスタンス
        MarketRequestSendResult l_sendResult =
            l_marketRequestSenderService.send(l_orderMarketRequestMessage);

        //５）　@４）のメソッドの戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sendResult;
    }

    /**
     * (update注文失効)<BR>
     * 注文データを失効状態に更新する。<BR>
     * <BR>
     * １）　@注文エラー理由コードの取得<BR>
     * 　@パラメータ.エラーコード != nullの場合、<BR>
     * 　@拡張株式注文マネージャ.get注文エラー理由コード()を<BR>
     * 　@コールする。<BR>
     * <BR>
     * 　@[get注文エラー理由コード()に指定する引数]<BR>
     * 　@　@エラーコード：　@パラメータ.エラーコード<BR>
     * <BR>
     * 　@※パラメータ.エラーコード == nullであれば、<BR>
     * 　@　@注文エラー理由コード = "0000：正常"とする。<BR>
     * <BR>
     * ２）　@失効処理の実施<BR>
     * 　@２－１）　@失効通知キューParamsを生成する。<BR>
     * <BR>
     * 　@２－２）　@生成したParamsに、以下のプロパティをセットする。<BR>
     * <BR>
     * 　@　@データコード	＝　@"AI813"（株式失効通知）<BR>
     * 　@　@証券会社コード	＝　@顧客(*1).証券会社コード<BR>
     * 　@　@部店コード	＝　@顧客(*1).部店コード<BR>
     * 　@　@口座コード	＝　@顧客(*1).口座コード<BR>
     * 　@　@扱者コード	＝<BR>
     * 　@　@　@パラメータ.注文単位Row.取引者IDに該当する扱者.扱者コード<BR>
     * 　@　@　@※nullであれば、nullをセット。<BR>
     * 　@　@識別コード	＝　@パラメータ.注文単位Row.識別コード<BR>
     * 　@　@約定数量	＝　@パラメータ.注文単位Row.約定数量<BR>
     * 　@　@失効理由コード	＝　@"原注文破棄"<BR>
     * 　@　@失効通知区分	＝　@"失効"<BR>
     * 　@　@エラーメッセージ	＝　@取得した注文エラー理由コード<BR>
     * 　@　@　@※エラーメッセージは、注文単位.注文エラー理由コードに記録される。<BR>
     * 　@　@処理区分	＝　@"未処理"<BR>
     * <BR>
     * 　@　@(*1)パラメータ.注文単位Row.口座IDに該当する顧客を使用する。<BR>
     * <BR>
     * 　@２－３）　@株式失効通知一件サービスImpl.exec失効()をコールする。<BR>
     * 　@　@※本処理は新規トランザクションで処理を行い、<BR>
     * 　@　@処理完了時に更新が反映されるようにすること。<BR>
     * 　@　@（参考：WEB3GentradeDaemonTriggerManager.startThread()）<BR>
     * <BR>
     * 　@　@[exec失効()に指定する引数]<BR>
     * 　@　@　@株式失効通知キューParams：　@プロパティセットしたParams<BR>
     * 　@　@　@注文単位：　@パラメータ.注文単位Rowより生成した注文単位オブジェクト<BR>
     * <BR>
     * ３）　@RACコンテキストに値を再セットする。<BR>
     * 　@※失効通知一件サービスと紐付く株式下り処理RACコンテキストインタセプタにて<BR>
     * 　@※RACのクリアをしているため、再設定が必要。<BR>
     * <BR>
     * 　@３－１）　@RACコンテキストに口座IDをセットする。<BR>
     * 　@　@WEB3DescendRacCtxService.setAccountIdCtx()をcallする。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@引数.注文単位Row.口座ID<BR>
     * <BR>
     * ４）　@非承認・処理失敗情報の記録<BR>
     * 　@拡張株式注文マネージャ.update注文データ()をコールする。<BR>
     * <BR>
     * 　@[update注文データ()に指定する引数]<BR>
     * 　@　@注文単位：　@更新値をセットした注文単位Paramsより生成した<BR>
     * 　@　@　@注文単位オブジェクト<BR>
     * 　@　@注文履歴：　@null（固定）<BR>
     * <BR>
     * 　@※更新値設定内容については、以下のDB更新仕様を参照。<BR>
     * 　@　@[非承認処理（パラメータ.is承認 == false かつ 取得した<BR>
     * 　@　@　@注文エラー理由コード == "0000：正常"）の場合]<BR>
     * 　@　@　@「仮注文非承認_株式注文単位テーブル仕様.xls」参照。<BR>
     * <BR>
     * 　@　@[処理失敗（上記以外）の場合]<BR>
     * 　@　@　@「承認／非承認処理失敗_株式注文単位テーブル仕様.xls」参照。<BR>
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@param l_eqtypeOrderUnitRow - (注文単位Row)<BR>
     * 注文単位Rowオブジェクト<BR>
     * @@param l_strErrorCode - (エラーコード)<BR>
     * WEB3ErrorCatalogに定義されているエラーコード。<BR>
     * @@param l_blnIsApprove - (is承認)<BR>
     * 承認処理かどうかのフラグ<BR>
     * <BR>
     * false：　@非承認<BR>
     * true：　@承認<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4609EAFF00E2
     */
    protected void updateOrderExpire(
        WEB3Administrator l_admin,
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow,
        String l_strErrorCode,
        boolean l_blnIsApprove) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderExpire(WEB3Administrator," +
            "EqtypeOrderUnitRow, String, boolean)";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeMainAccount l_account = null;
        String l_strErrorReasonCode = null;
        String l_strTraderCode = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        final WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        final EqtypeOrderUnitRow l_finalOrderUnitRow = l_eqtypeOrderUnitRow;

        try
        {
            l_account = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                l_finalOrderUnitRow.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //１）　@注文エラー理由コードの取得
        //　@パラメータ.エラーコード != nullの場合、
        //　@拡張株式注文マネージャ.get注文エラー理由コード()を
        //　@コールする。
        //　@[get注文エラー理由コード()に指定する引数]
        //　@　@エラーコード：　@パラメータ.エラーコード
        //　@※パラメータ.エラーコード == nullであれば、
        //　@　@注文エラー理由コード = "0000：正常"とする。
        if (l_strErrorCode != null)
        {
            l_strErrorReasonCode = l_orderManager.getErrorReasonCode(l_strErrorCode);
        }
        else
        {
            l_strErrorReasonCode = WEB3ErrorReasonCodeDef.NORMAL;
        }

        //２）　@失効処理の実施
        //　@２－１）　@失効通知キューParamsを生成する。
        final HostEqtypeCloseOrderNotifyParams l_closeOrderNotityParams =
            new HostEqtypeCloseOrderNotifyParams();

        //　@２－２）　@生成したParamsに、以下のプロパティをセットする。
        //　@　@データコード ＝　@"AI813"（株式失効通知）
        l_closeOrderNotityParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);
        //　@　@証券会社コード ＝　@顧客(*1).証券会社コード
        l_closeOrderNotityParams.setInstitutionCode(l_account.getInstitution().getInstitutionCode());
        //　@　@部店コード ＝　@顧客(*1).部店コード
        l_closeOrderNotityParams.setBranchCode(l_account.getBranch().getBranchCode());
        //　@　@口座コード ＝　@顧客(*1).口座コード
        l_closeOrderNotityParams.setAccountCode(l_account.getAccountCode());
        //　@　@扱者コード ＝
        //　@　@　@パラメータ.注文単位Row.取引者IDに該当する扱者.扱者コード
        //　@　@　@※nullであれば、nullをセット。
        if (l_finalOrderUnitRow.getTraderIdIsNull())
        {
            l_strTraderCode = null;
        }
        else
        {
            l_strTraderCode = l_finalOrderUnitRow.getTraderId() + "";
        }
        l_closeOrderNotityParams.setTraderCode(l_strTraderCode);
        //　@　@識別コード ＝　@パラメータ.注文単位Row.識別コード
        l_closeOrderNotityParams.setOrderRequestNumber(l_finalOrderUnitRow.getOrderRequestNumber());
        //　@　@約定数量 ＝　@パラメータ.注文単位Row.約定数量
        l_closeOrderNotityParams.setExecutedQuantity(l_finalOrderUnitRow.getExecutedQuantity());
        //　@　@失効理由コード ＝　@"原注文破棄"
        l_closeOrderNotityParams.setReasonCode(WEB3ExpirationReasonCodeDef.ORDER_CANCEL);
        //　@　@失効通知区分 ＝　@"失効"
        l_closeOrderNotityParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
        //　@　@エラーメッセージ ＝　@取得した注文エラー理由コード
        //　@　@　@※エラーメッセージは、注文単位.注文エラー理由コードに記録される。
        l_closeOrderNotityParams.setErrorMessage(l_strErrorReasonCode);
        //　@　@処理区分 ＝　@"未処理"
        l_closeOrderNotityParams.setStatus(WEB3StatusDef.NOT_DEAL);

        //　@２－３）　@株式失効通知一件サービスImpl.exec失効()をコールする。
        //　@　@※本処理は新規トランザクションで処理を行い、
        //　@　@処理完了時に更新が反映されるようにすること。
        //　@　@（参考：WEB3GentradeDaemonTriggerManager.startThread()）
        //　@　@[exec失効()に指定する引数]
        //　@　@　@株式失効通知キューParams：　@プロパティセットしたParams
        //　@　@　@注文単位：　@パラメータ.注文単位Rowより生成した注文単位オブジェクト
       boolean l_blnFlag = false;
       try
       {
           final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

           l_queryProcesser.doTransaction(
               QueryProcessor.TX_CREATE_NEW,
               new TransactionCallback()
                   {
                       public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
                       {
                           WEB3EquityReceiveCloseOrderUnitService l_service =
                               (WEB3EquityReceiveCloseOrderUnitService)Services.getService(
                                   WEB3EquityReceiveCloseOrderUnitService.class);

                           EqTypeOrderUnit l_orderUnit =
                               (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_finalOrderUnitRow);
                           try
                           {
                               l_service.execCloseOrder(l_closeOrderNotityParams, l_orderUnit);
                           }
                           catch (WEB3BaseException l_ex)
                           {
                               throw new DataCallbackException(l_ex.getErrorMessage(), l_ex);
                           }
                           return null;
                       }
                   }
           );
       }
       catch (DataCallbackException l_ex)
       {
           log.error(l_ex.getMessage(), l_ex);
           l_blnFlag = true;
       }
       catch (DataException l_ex)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(), l_ex);
       }

       //３）　@RACコンテキストに値を再セットする。
       //  ※失効通知一件サービスと紐付く株式下り処理RACコンテキストインタセプタにて
       //  ※RACのクリアをしているため、再設定が必要。
       //  ３－１）　@RACコンテキストに口座IDをセットする。
       //      WEB3DescendRacCtxService.setAccountIdCtx()をcallする。
       //      [引数]
       //         引数.注文単位Row.口座ID
       WEB3DescendRacCtxService l_descendRacCtxService =
           (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
       if (l_descendRacCtxService != null)
       {
           l_descendRacCtxService.setAccountIdCtx(l_eqtypeOrderUnitRow.getAccountId());
       }

       //３）　@非承認・処理失敗情報の記録
       //　@拡張株式注文マネージャ.update注文データ()をコールする。
       //　@[update注文データ()に指定する引数]
       //　@　@注文単位：　@更新値をセットした注文単位Paramsより生成した
       //　@　@　@注文単位オブジェクト
       //　@　@注文履歴：　@null（固定）
       //　@※更新値設定内容については、以下のDB更新仕様を参照。
       //　@　@[非承認処理（パラメータ.is承認 == false かつ 取得した
       //　@　@　@注文エラー理由コード == "0000：正常"）の場合]
       //　@　@　@「仮注文非承認_株式注文単位テーブル仕様.xls」参照。
       //　@　@[処理失敗（上記以外）の場合]
       //　@　@　@「承認／非承認処理失敗_株式注文単位テーブル仕様.xls」参照。
       EqTypeOrderUnit l_orderUnit = null;
       try
       {
           l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_eqtypeOrderUnitRow.getOrderUnitId());
       }
       catch (NotFoundException l_ex)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }
       EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
           new EqtypeOrderUnitParams((EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject());
       if (l_blnFlag)
       {
           //「承認／非承認処理失敗_株式注文単位テーブル仕様.xls」とWEB3-EQTYPEADMIN-A-FT-0033参照。
           //更新日付
           l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
           //承認状態区分 9：エラー
           l_eqtypeOrderUnitParams.setApproveStatusType(WEB3ApproveStatusType.ERROR);
           //承認者コード   パラメータ.管理者.管理者コード
           l_eqtypeOrderUnitParams.setApproverCode(l_admin.getAdministratorCode());
           //承認／非承認日時
           l_eqtypeOrderUnitParams.setApproveTimestamp(GtlUtils.getSystemTimestamp());
       }
       else if (!l_blnIsApprove && WEB3ErrorReasonCodeDef.NORMAL.equals(l_strErrorReasonCode))
       {
           //「仮注文非承認_株式注文単位テーブル仕様.xls」参照。
           //承認状態区分 2：非承認
           l_eqtypeOrderUnitParams.setApproveStatusType(WEB3ApproveStatusType.NON_APPROVED);
           //承認者コード   パラメータ.管理者.管理者コード
           l_eqtypeOrderUnitParams.setApproverCode(l_admin.getAdministratorCode());
           //承認／非承認日時
           l_eqtypeOrderUnitParams.setApproveTimestamp(GtlUtils.getSystemTimestamp());
       }
       else
       {
           //「承認／非承認処理失敗_株式注文単位テーブル仕様.xls」参照。
           //承認状態区分 9：エラー
           l_eqtypeOrderUnitParams.setApproveStatusType(WEB3ApproveStatusType.ERROR);
           //承認者コード   パラメータ.管理者.管理者コード
           l_eqtypeOrderUnitParams.setApproverCode(l_admin.getAdministratorCode());
           //承認／非承認日時
           l_eqtypeOrderUnitParams.setApproveTimestamp(GtlUtils.getSystemTimestamp());

       }
       EqTypeOrderUnit l_eqTypeOrderUnit =
           (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_eqtypeOrderUnitParams);
       l_orderManager.updateOrderData(l_eqTypeOrderUnit, null);

       log.exiting(STR_METHOD_NAME);
    }
}
@
