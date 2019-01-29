head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetTradingPowerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 資産余力情報画面表示サービスImplクラス(WEB3TPAssetTradingPowerServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 Revision History : 2007/08/07 トウ鋒鋼(中訊) モデルNo.120
 Revision History : 2007/08/07 トウ鋒鋼(中訊) モデルNo.153
 Revision History : 2008/01/28 崔遠鵬(中訊) モデルNo.250
 Revision History : 2008/10/09 劉剣(中訊) モデルNo.312、No.313
 Revision History : 2008/10/20 劉剣(中訊) モデルNo.336
 Revision History : 2008/10/20 張明鹿(中訊) モデルNo.330、No.331、No.332
 Revesion History : 2008/11/26 劉剣 (中訊) モデルNo.376
 Revesion History : 2010/01/29 武波 (中訊) モデルNo.453
 */
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AdditionalDepositStopDef;
import webbroker3.common.define.WEB3CurrencyCodeDef;
import webbroker3.common.define.WEB3TradingStopDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.ProcessManagementDao;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3ForeignPositionContract;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPAdddepositInfo;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.WEB3TPCalcResult;
import webbroker3.tradingpower.WEB3TPFirstAdddepositInfo;
import webbroker3.tradingpower.WEB3TPSecondAdddepositInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.WEB3TPTradingPowerSettlementService;
import webbroker3.tradingpower.data.TpAssetHistoryRow;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.define.WEB3TPClearUpDivDef;
import webbroker3.tradingpower.define.WEB3TPDepositDivDef;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketStateDivDef;
import webbroker3.tradingpower.define.WEB3TPOrderdMarketCodeListDef;
import webbroker3.tradingpower.define.WEB3TPSecuredLoanSecAccOpenDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.message.WEB3TPAdditionalGenerationRequest;
import webbroker3.tradingpower.message.WEB3TPAdditionalGenerationResponse;
import webbroker3.tradingpower.message.WEB3TPAssetHistoryRequest;
import webbroker3.tradingpower.message.WEB3TPAssetHistoryResponse;
import webbroker3.tradingpower.message.WEB3TPAssetHistoryUnit;
import webbroker3.tradingpower.message.WEB3TPAssetRequest;
import webbroker3.tradingpower.message.WEB3TPAssetResponse;
import webbroker3.tradingpower.message.WEB3TPAssetUnit;
import webbroker3.tradingpower.message.WEB3TPDayTradeTradingPowerRequest;
import webbroker3.tradingpower.message.WEB3TPDayTradeTradingPowerResponse;
import webbroker3.tradingpower.message.WEB3TPEquityTradingPowerDetailRequest;
import webbroker3.tradingpower.message.WEB3TPEquityTradingPowerDetailResponse;
import webbroker3.tradingpower.message.WEB3TPFirstAdditionalInfo;
import webbroker3.tradingpower.message.WEB3TPMarginMaintenanceTradingPowerUnit;
import webbroker3.tradingpower.message.WEB3TPMarginTradingPowerDetailRequest;
import webbroker3.tradingpower.message.WEB3TPMarginTradingPowerDetailResponse;
import webbroker3.tradingpower.message.WEB3TPPaymentAcceptRequest;
import webbroker3.tradingpower.message.WEB3TPPaymentAcceptResponse;
import webbroker3.tradingpower.message.WEB3TPPaymentAcceptUnit;
import webbroker3.tradingpower.message.WEB3TPSecondAdditionalInfo;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationInfo;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationRequest;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationResponse;
import webbroker3.tradingpower.message.WEB3TPTradingPowerRequest;
import webbroker3.tradingpower.message.WEB3TPTradingPowerResponse;
import webbroker3.tradingpower.message.WEB3TPTradingPowerUnit;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceRequest;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceResponse;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceUnit;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceUseQuoteRequest;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceUseQuoteResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.tradingpower.service.delegate.WEB3TPPaymentRequisitionManageService;
import webbroker3.tradingpower.service.delegate.WEB3TPTradingPowerClientRequestService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (資産余力情報画面表示サービスImpl)<BR>
 * 資産余力情報画面表示サービス実装クラス。<BR>
 * 
 * @@author asano(SCS)
 */

public class WEB3TPAssetTradingPowerServiceImpl extends WEB3TPTradingPowerClientRequestService
        implements
            WEB3TPAssetTradingPowerService
{

    /**
     * ログユーティリティ
     */
    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPAssetTradingPowerServiceImpl.class);

    /**
     * (デバッグison)
     */
    private static boolean DBG = log.ison();

    /**
     * @@roseuid 41B6817601E3
     */
    public WEB3TPAssetTradingPowerServiceImpl()
    {
    }

    /**
     * (execute) <BR>
     * <BR>
     * ※シーケンス図「（資産余力情報画面表示サービス）execute」参照<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(METHOD_NAME);

        //validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //預り資産画面
        if(l_request instanceof WEB3TPAssetRequest)
        {
            return createAssetResponse((WEB3TPAssetRequest)l_request);
        }
        //取引余力画面
        else if(l_request instanceof WEB3TPTradingPowerRequest)
        {
            return createTradingPowerResponse((WEB3TPTradingPowerRequest)l_request);
        }
        //日計り銘柄取引余力試算画面
        else if(l_request instanceof WEB3TPDayTradeTradingPowerRequest)
        {
            return createDayTradeTradingPowerResponse((WEB3TPDayTradeTradingPowerRequest)l_request);
        }
        //余力推移(時価計算)画面
        else if(l_request instanceof WEB3TPTransitionReferenceUseQuoteRequest)
        {
            return createTransitionReferenceUseQuoteResponse((WEB3TPTransitionReferenceUseQuoteRequest)l_request);
        }
        //余力推移画面
        else if(l_request instanceof WEB3TPTransitionReferenceRequest)
        {
            return createTransitionReferenceResponse((WEB3TPTransitionReferenceRequest)l_request);
        }
        //出金受付状況画面
        else if(l_request instanceof WEB3TPPaymentAcceptRequest)
        {
            return createPaymentStatusResponse((WEB3TPPaymentAcceptRequest)l_request);
        }
        //現物株買付余力詳細画面
        else if(l_request instanceof WEB3TPEquityTradingPowerDetailRequest)
        {
            return createEquityTradingPowerDetailResponse((WEB3TPEquityTradingPowerDetailRequest)l_request);
        }
        //信用新規建余力詳細画面
        else if(l_request instanceof WEB3TPMarginTradingPowerDetailRequest)
        {
            return createMarginTradingPowerDetailResponse((WEB3TPMarginTradingPowerDetailRequest)l_request);
        }
        //create資産評価額履歴画面
        else if(l_request instanceof WEB3TPAssetHistoryRequest)
        {
            return createAssertHistory((WEB3TPAssetHistoryRequest)l_request);
        }
        //create不足金発生画面
        else if (l_request instanceof WEB3TPShortfallGenerationRequest)
        {
            return createShortfallGenerationScreen((WEB3TPShortfallGenerationRequest)l_request);
        }
        //create追証発生画面
        else if (l_request instanceof WEB3TPAdditionalGenerationRequest)
        {
            return createAdditionalGenerationScreen((WEB3TPAdditionalGenerationRequest)l_request);
        }
        else
        {
            log.error("不正なリクエストです。");
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, "execute()");
        }
    }

    /**
     * (create預り資産画面)<BR>
     * 預り資産画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（資産余力情報画面表示サービス）create預り資産画面」参照。<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPAssetResponse
     * @@roseuid 41B66DB2009B
     */
    protected WEB3TPAssetResponse createAssetResponse(WEB3TPAssetRequest l_request)
            throws WEB3BaseException
    {

        //レスポンス取得
        WEB3TPAssetResponse l_response = (WEB3TPAssetResponse)l_request.createResponse();

        //補助口座取得
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //顧客取得
        WEB3GentradeMainAccount l_account = null;
        try
        {
            l_account = new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createAssetResponse",
                    dqe);
        }
        catch(DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createAssetResponse",
                    dne);
        }

        //取引余力サービス
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

        //外貨残高情報取得
        WEB3ForeignPositionContract l_foreignPositionContract = null;

        //外貨残高情報(ドル)取得
        l_foreignPositionContract =
            l_tradingPowerService.getForeignPositionContract(l_subAccount, WEB3CurrencyCodeDef.A0);

        //get外貨残高(T+0)
        Double l_accountBalanceDollarT0 =
            l_foreignPositionContract.getForeignPositionBalance(WEB3TPSpecifiedPointDef.T_0);
        //get外貨残高(T+5)
        Double l_accountBalanceDollarT5 =
            l_foreignPositionContract.getForeignPositionBalance(WEB3TPSpecifiedPointDef.T_5);

        //外貨残高情報(ユーロ)取得
        l_foreignPositionContract =
            l_tradingPowerService.getForeignPositionContract(l_subAccount, WEB3CurrencyCodeDef.Z4);

        //get外貨残高(T+0)
        Double l_accountBalanceEuroT0 =
            l_foreignPositionContract.getForeignPositionBalance(WEB3TPSpecifiedPointDef.T_0);
        //get外貨残高(T+5)
        Double l_accountBalanceEuroT5 =
            l_foreignPositionContract.getForeignPositionBalance(WEB3TPSpecifiedPointDef.T_5);

        //信用口座開設済みか
        boolean l_isMarginAccountEstablishd = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //余力計算条件
        WEB3TPCalcCondition l_condition = null;

        //預り資産明細(受残用)生成
        WEB3TPAssetUnit l_assetUnit1 = new WEB3TPAssetUnit();
        //預り資産明細(約残用)生成
        WEB3TPAssetUnit l_assetUnit2 = new WEB3TPAssetUnit();

        //余力計算結果ID
        long l_calcResultId;

        //現物顧客
        if(!l_isMarginAccountEstablishd)
        {
            //取引余力サービス
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //資産余力情報<現物顧客>取得
            WEB3TPTradingPowerCalcEquity l_calcEquity = l_service.getTradingPowerCalcEquity(l_subAccount);

            //余力計算条件取得
            l_condition = l_calcEquity.getCalcCondition();

            //余力計算結果詳細Params<現物顧客>取得
            TpCalcResultEquityDetailParams l_equityDetailParams = l_calcEquity.getCalcResultDetailEquity();

            //余力計算結果ID取得
            l_calcResultId = l_equityDetailParams.getCalcResultEquityId();

            //預り金残高(T+0)取得
            double l_dblAccountBalanceT_0 = l_calcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_0);
            //預り金残高(T+5)取得
            double l_dblAccountBalanceT_5 = l_calcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_5);
            //当日約定済代金(T+0)取得
            double l_dblTodayExecutedAmountT_0 = l_calcEquity.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_0);
            //当日約定済代金(T+5)取得
            double l_dblTodayExecutedAmountT_5 = l_calcEquity.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_5);
            //株式評価額<受残>取得
            double l_dblEquityAssetDelivered = l_equityDetailParams.getEquityAssetDelivered();
            //株式評価額<約残>取得
            double l_dblEquityAssetExecuted = l_equityDetailParams.getEquityAssetExecuted();
            //株式ミニ投資<受残>取得
            double l_dblMiniStockAssetDelivered = l_equityDetailParams.getMiniStockAssetDelivered();
            //株式ミニ投資<約残>取得
            double l_dblMiniStockAssetExecuted = l_equityDetailParams.getMiniStockAssetExecuted();
            //累積投資評価額取得<受残>
            double l_dblRuitoAssetDelivered = l_equityDetailParams.getRuitoAssetDelivered();
            //累積投資評価額取得<約残>
            double l_dblRuitoAssetExecuted = l_equityDetailParams.getRuitoAssetExecuted();
            //投資信託評価額<受残>取得
            double l_dblMutualFundAssetDelivered = l_equityDetailParams.getMutualFundAssetDelivered();
            //投資信託評価額<約残>取得
            double l_dblMutualFundAssetExecuted = l_equityDetailParams.getMutualFundAssetExecuted();
            //債券評価額<受残>取得
            double l_dblBondAssetDelivered = l_equityDetailParams.getBondAssetDelivered();
            //債券評価額<約残>取得
            double l_dblBondAssetExecuted = l_equityDetailParams.getBondAssetExecuted();
            //外国株式評価額<受残>取得
            double l_dblForeignEquityAssetDelivered = l_equityDetailParams.getForeignEquityAssetDelivered();
            //外国株式評価額<約残>取得
            double l_dblForeignEquityAssetExecuted = l_equityDetailParams.getForeignEquityAssetExecuted();

            //預り資産明細(受残用)のプロパティセット 
            l_assetUnit1.clearUpDiv = WEB3TPClearUpDivDef.DELIVERD;//清算済区分 ＝ 0：受渡済 
            l_assetUnit1.accountBalance = format(l_dblAccountBalanceT_0
                    - l_dblTodayExecutedAmountT_0);//預り金
            l_assetUnit1.equityAsset = format(l_dblEquityAssetDelivered);//株式評価額
            l_assetUnit1.mstkAsset = format(l_dblMiniStockAssetDelivered);//株式ミニ投資評価額
            l_assetUnit1.mutualAsset = format(l_dblMutualFundAssetDelivered);//投資信託評価額
            l_assetUnit1.ruitoAsset = format(l_dblRuitoAssetDelivered);//累積投資評価
            l_assetUnit1.bondAsset = format(l_dblBondAssetDelivered);//債券評価額
            l_assetUnit1.feqAsset = format(l_dblForeignEquityAssetDelivered);//外国株式評価額(受残)
            l_assetUnit1.totalAsset = format(l_dblEquityAssetDelivered
                    + l_dblMiniStockAssetDelivered
                    + l_dblRuitoAssetDelivered
                    + l_dblMutualFundAssetDelivered
                    + l_dblBondAssetDelivered
                    + l_dblForeignEquityAssetDelivered);//合額額

            //預り金<ドル>
            //get外貨残高<ドル>(T+0)がnullの場合
            if (l_accountBalanceDollarT0 == null)
            {
                l_assetUnit1.accountBalanceDollar = null;
            }
            //get外貨残高<ドル>(T+0)がnull以外の場合
            else
            {
                l_assetUnit1.accountBalanceDollar = format(l_accountBalanceDollarT0.doubleValue());
            }

            //預り金<ユーロ>
            //get外貨残高<ユーロ>(T+0)がnullの場合
            if (l_accountBalanceEuroT0 == null)
            {
                l_assetUnit1.accountBalanceEuro = null;
            }
            //get外貨残高<ユーロ>(T+0)がnull以外の場合
            else
            {
                l_assetUnit1.accountBalanceEuro = format(l_accountBalanceEuroT0.doubleValue());
            }

            //預り資産明細(受残用)のプロパティセット
            l_assetUnit2.clearUpDiv = WEB3TPClearUpDivDef.EXECUTED;//清算済区分 ＝ 1：未受渡分含む
            l_assetUnit2.accountBalance = format(l_dblAccountBalanceT_5
                    - l_dblTodayExecutedAmountT_0);//預り金
            l_assetUnit2.accountBalanceDay = format(l_dblAccountBalanceT_5
                    - l_dblTodayExecutedAmountT_5);//預り金<当日振込み分含む>
            l_assetUnit2.equityAsset = format(l_dblEquityAssetExecuted);//株式評価額設定
            l_assetUnit2.mstkAsset = format(l_dblMiniStockAssetExecuted);//株式ミニ投資評価額
            l_assetUnit2.mutualAsset = format(l_dblMutualFundAssetExecuted);//投資信託評価額
            l_assetUnit2.ruitoAsset = format(l_dblRuitoAssetExecuted);//累積投資評価
            l_assetUnit2.bondAsset = format(l_dblBondAssetExecuted);//債券評価額
            l_assetUnit2.feqAsset = format(l_dblForeignEquityAssetExecuted);//外国株式評価額(約残)
            l_assetUnit2.totalAsset = format(l_dblEquityAssetExecuted
                    + l_dblMiniStockAssetExecuted
                    + l_dblRuitoAssetExecuted
                    + l_dblMutualFundAssetExecuted
                    + l_dblBondAssetExecuted
                    + l_dblForeignEquityAssetExecuted);//合額額

            //預り金<ドル>
            //get外貨残高<ドル>(T+5)がnullの場合
            if (l_accountBalanceDollarT5 == null)
            {
                l_assetUnit2.accountBalanceDollar = null;
            }
            //get外貨残高<ドル>(T+5)がnull以外の場合
            else
            {
                l_assetUnit2.accountBalanceDollar = format(l_accountBalanceDollarT5.doubleValue());
            }

            //預り金<ユーロ>
            //get外貨残高<ユーロ>(T+5)がnullの場合
            if (l_accountBalanceEuroT5 == null)
            {
                l_assetUnit2.accountBalanceEuro = null;
            }
            //get外貨残高<ユーロ>(T+5)がnull以外の場合
            else
            {
                l_assetUnit2.accountBalanceEuro = format(l_accountBalanceEuroT5.doubleValue());
            }
        }
        //信用顧客
        else
        {

            //取引余力サービス
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //資産余力情報<信用顧客>取得
            WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMargin(l_subAccount);

            //余力計算条件取得
            l_condition = l_calcMargin.getCalcCondition();

            //余力計算結果詳細Params<信用顧客>取得
            TpCalcResultMarginDetailParams l_marginDetailParams = l_calcMargin.getCalcResultDetailMargin();

            //余力計算結果ID
            l_calcResultId = l_marginDetailParams.getCalcResultMarginId();

            //預り金残高(T+0)取得
            double l_dblAccountBalanceT_0 = l_calcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_0);
            //預り金残高(T+5)取得
            double l_dblAccountBalanceT_5 = l_calcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_5);
            //当日約定済代金(T+0)取得
            double l_dblTodayExecutedAmountT_0 = l_calcMargin.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_0);
            //当日約定済代金(T+5)取得
            double l_dblTodayExecutedAmountT_5 = l_calcMargin.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_5);
            //株式評価額<受残>取得
            double l_dblEquityAssetDelivered = l_marginDetailParams.getEquityAssetDelivered();
            //株式評価額<約残>取得
            double l_dblEquityAssetExecuted = l_marginDetailParams.getEquityAssetExecuted();
            //株式ミニ投資<受残>取得
            double l_dblMiniStockAssetDelivered = l_marginDetailParams.getMiniStockAssetDelivered();
            //株式ミニ投資<約残>取得
            double l_dblMiniStockAssetExecuted = l_marginDetailParams.getMiniStockAssetExecuted();
            //累積投資評価額取得<受残>
            double l_dblRuitoAssetDelivered = l_marginDetailParams.getRuitoAssetDelivered();
            //累積投資評価額取得<約残>
            double l_dblRuitoAssetExecuted = l_marginDetailParams.getRuitoAssetExecuted();
            //投資信託評価額<受残>取得
            double l_dblMutualFundAssetDelivered = l_marginDetailParams.getMutualFundAssetDelivered();
            //投資信託評価額<約残>取得
            double l_dblMutualFundAssetExecuted = l_marginDetailParams.getMutualFundAssetExecuted();
            //債券評価額<受残>取得
            double l_dblBondAssetDelivered = l_marginDetailParams.getBondAssetDelivered();
            //債券評価額<約残>取得
            double l_dblBondAssetExecuted = l_marginDetailParams.getBondAssetExecuted();
            //外国株式評価額<受残>取得
            double l_dblForeignEquityAssetDelivered = l_marginDetailParams.getForeignEquityAssetDelivered();
            //外国株式評価額<約残>取得
            double l_dblForeignEquityAssetExecuted = l_marginDetailParams.getForeignEquityAssetExecuted();

            //預り資産明細(受残用)のプロパティセット
            l_assetUnit1.clearUpDiv = WEB3TPClearUpDivDef.DELIVERD;//清算済区分 ＝ 0：受渡済 
            l_assetUnit1.accountBalance = format(l_dblAccountBalanceT_0
                    - l_dblTodayExecutedAmountT_0);//預り金
            l_assetUnit1.equityAsset = format(l_dblEquityAssetDelivered);//株式評価額
            l_assetUnit1.mstkAsset = format(l_dblMiniStockAssetDelivered);//株式ミニ投資評価額
            l_assetUnit1.mutualAsset = format(l_dblMutualFundAssetDelivered);//投資信託評価額
            l_assetUnit1.ruitoAsset = format(l_dblRuitoAssetDelivered);//累積投資評価
            l_assetUnit1.bondAsset = format(l_dblBondAssetDelivered);//債券評価額
            l_assetUnit1.feqAsset = format(l_dblForeignEquityAssetDelivered);//外国株式評価額(受残)
            l_assetUnit1.totalAsset = format(l_dblEquityAssetDelivered
                    + l_dblMiniStockAssetDelivered
                    + l_dblRuitoAssetDelivered
                    + l_dblMutualFundAssetDelivered
                    + l_dblBondAssetDelivered
                    + l_dblForeignEquityAssetDelivered);//合計額

            //預り金<ドル>
            //get外貨残高<ドル>(T+0)がnullの場合
            if (l_accountBalanceDollarT0 == null)
            {
                l_assetUnit1.accountBalanceDollar = null;
            }
            //get外貨残高<ドル>(T+0)がnull以外の場合
            else
            {
                l_assetUnit1.accountBalanceDollar = format(l_accountBalanceDollarT0.doubleValue());
            }

            //預り金<ユーロ>
            //get外貨残高<ユーロ>(T+0)がnullの場合
            if (l_accountBalanceEuroT0 == null)
            {
                l_assetUnit1.accountBalanceEuro = null;
            }
            //get外貨残高<ユーロ>(T+0)がnull以外の場合
            else
            {
                l_assetUnit1.accountBalanceEuro = format(l_accountBalanceEuroT0.doubleValue());
            }

            //預り資産明細(受残用)のプロパティセット
            l_assetUnit2.clearUpDiv = WEB3TPClearUpDivDef.EXECUTED;//清算済区分 ＝ 1：未受渡分含む
            l_assetUnit2.accountBalance = format(l_dblAccountBalanceT_5
                    - l_dblTodayExecutedAmountT_0);//預り金
            l_assetUnit2.accountBalanceDay = format(l_dblAccountBalanceT_5
                    - l_dblTodayExecutedAmountT_5);//預り金<当日振込み分含む>
            l_assetUnit2.accountBalance = format(l_dblAccountBalanceT_5
                    - l_dblTodayExecutedAmountT_0);//預り金
            l_assetUnit2.accountBalanceDay = format(l_dblAccountBalanceT_5
                    - l_dblTodayExecutedAmountT_5);//預り金<当日振込み分含む>
            l_assetUnit2.equityAsset = format(l_dblEquityAssetExecuted);//株式評価額設定
            l_assetUnit2.mstkAsset = format(l_dblMiniStockAssetExecuted);//株式ミニ投資評価額
            l_assetUnit2.mutualAsset = format(l_dblMutualFundAssetExecuted);//投資信託評価額
            l_assetUnit2.ruitoAsset = format(l_dblRuitoAssetExecuted);//累積投資評価
            l_assetUnit2.bondAsset = format(l_dblBondAssetExecuted);//債券評価額
            l_assetUnit2.feqAsset = format(l_dblForeignEquityAssetExecuted);//外国株式評価額(約残)
            l_assetUnit2.totalAsset = format(l_dblEquityAssetExecuted
                    + l_dblMiniStockAssetExecuted
                    + l_dblRuitoAssetExecuted
                    + l_dblMutualFundAssetExecuted
                    + l_dblBondAssetExecuted
                    + l_dblForeignEquityAssetExecuted);//合計額

            //預り金<ドル>
            //get外貨残高<ドル>(T+5)がnullの場合
            if (l_accountBalanceDollarT5 == null)
            {
                l_assetUnit2.accountBalanceDollar = null;
            }
            //get外貨残高<ドル>(T+5)がnull以外の場合
            else
            {
                l_assetUnit2.accountBalanceDollar = format(l_accountBalanceDollarT5.doubleValue());
            }

            //預り金<ユーロ>
            //get外貨残高<ユーロ>(T+5)がnullの場合
            if (l_accountBalanceEuroT5 == null)
            {
                l_assetUnit2.accountBalanceEuro = null;
            }
            //get外貨残高<ユーロ>(T+5)がnull以外の場合
            else
            {
                l_assetUnit2.accountBalanceEuro = format(l_accountBalanceEuroT5.doubleValue());
            }
        }

        //営業日(T+0)取得
        Date l_bizDate = l_condition.getBizDate(WEB3TPSpecifiedPointDef.T_0);

        //レスポンスにプロパティセット
        l_response.calcResultId = String.valueOf(l_calcResultId);//余力計算結果ID
        l_response.bizDate = l_bizDate;//日付
        l_response.assetUnits = new WEB3TPAssetUnit[2];//預り金明細一覧
        l_response.assetUnits[0] = l_assetUnit1;//預り金明細<受残>
        l_response.assetUnits[1] = l_assetUnit2;//預り金明細<約残>

        //レスポンス返却
        return l_response;
    }

    /**
     * (create取引余力画面)<BR>
     * 取引余力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（資産余力情報画面表示サービス）create取引余力画面」参照。<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPTradingPowerResponse
     * @@roseuid 41B67E1B0241
     */
    protected WEB3TPTradingPowerResponse createTradingPowerResponse(
            WEB3TPTradingPowerRequest l_request) throws WEB3SystemLayerException
    {

        //レスポンス取得
        WEB3TPTradingPowerResponse l_response = (WEB3TPTradingPowerResponse)l_request.createResponse();

        //補助口座取得
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //顧客取得
        WEB3GentradeMainAccount l_account = null;
        try
        {
            l_account = new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createTradingPowerResponse",
                    dqe);
        }
        catch(DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createTradingPowerResponse",
                    dne);
        }

        //信用口座開設済みか
        boolean l_isMarginAccountEstablishd = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //余力計算結果ID取得
        long l_lngCalcResultId;

        //取引余力明細一覧
        WEB3TPTradingPowerUnit[] l_units = new WEB3TPTradingPowerUnit[6];

        //余力計算条件
        WEB3TPCalcCondition l_condition = null;

        //現物顧客
        if(!l_isMarginAccountEstablishd)
        {

            //取引余力サービス
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //資産余力情報<現物顧客>取得
            WEB3TPTradingPowerCalcEquity l_calcEquity = l_service.getTradingPowerCalcEquity(l_subAccount);

            //余力計算結果<現物顧客>Params取得
            TpCalcResultEquityParams l_equityParams = l_calcEquity.getCalcResultEquity();

            //余力計算条件取得
            l_condition = l_calcEquity.getCalcCondition();

            //余力計算結果ID取得
            l_lngCalcResultId = l_equityParams.getCalcResultEquityId();

            for(int i = 0; i < 6; i++)
            {

                //T+n取得
                Date l_datBizDate = l_condition.getBizDate(i);

                //株式買付可能額取得
                double l_dblEquityTradingPower = Math.max(
                        0,
                        l_calcEquity.calcAppliedEquityTradingPower(i).tradingPower);
                //投信買付可能額取得
                double l_dblFundTradingPower = Math.max(
                        0,
                        l_calcEquity.calcAppliedOtherTradingPower(OrderTypeEnum.MF_BUY, i).tradingPower);
                //その他商品買付可能額取得
                double l_dblOtherTradingPower = Math.max(
                        0,
                        l_calcEquity.calcAppliedOtherTradingPower(null, i).tradingPower);
                //出金可能額
                double l_dblPaymentTradingPower = Math.max(
                        0,
                        l_calcEquity.calcAppliedOtherTradingPower(OrderTypeEnum.CASH_OUT, i).tradingPower);

                //営業日(株式買付可能額．適用日)を取得
                Date l_datEquityBuyApplyDate = l_condition.getBizDate(l_calcEquity.calcAppliedEquityTradingPower(i).appliedPoint);

                //取引余力明細ユニット
                WEB3TPTradingPowerUnit l_unit = new WEB3TPTradingPowerUnit();

                //ユニットにプロパティセット
                l_unit.bizDate = l_datBizDate;//日付
                l_unit.equityTradingPower = format(l_dblEquityTradingPower);//現物株買付余力
                l_unit.mutualTradingPower = format(l_dblFundTradingPower);//投信買付余力
                l_unit.otherTradingPower = format(l_dblOtherTradingPower);//その他商品買付余力
                l_unit.paymentTradingPower = format(l_dblPaymentTradingPower);//出金余力         
                l_unit.equityBuyApplyDate = l_datEquityBuyApplyDate;//余力適用日<現物株買付余力>

                //一覧に設定
                l_units[i] = l_unit;
            }

        }
        //信用顧客
        else
        {
            //取引余力サービス
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //資産余力情報<信用顧客>取得
            WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMargin(l_subAccount);

            //余力計算結果<信用顧客>Params取得
            TpCalcResultMarginParams l_marginParams = l_calcMargin.getCalcResultMargin();

            //余力計算条件取得
            l_condition = l_calcMargin.getCalcCondition();

            //余力計算結果ID取得
            l_lngCalcResultId = l_marginParams.getCalcResultMarginId();

            for(int i = 0; i < 6; i++)
            {

                //T+n取得
                Date l_datBizDate = l_condition.getBizDate(i);

                //株式買付可能額取得
                double l_dblEquityTradingPower = Math.max(
                        0,
                        l_calcMargin.calcAppliedEquityTradingPower(i).tradingPower);
                //信用新規建可能額
                double l_dblMarginTradingPower = Math.max(
                        0,
                        l_calcMargin.calcAppliedMarginTradingPower(i).tradingPower);
                //信用現引可能額
                double l_dblActualReceiptTradingPower = Math.max(
                        0,
                        l_calcMargin.calcAppliedActualReceiptTradingPower(i).tradingPower);
                //投信買付可能額取得
                double l_dblFundTradingPower = Math.max(
                        0,
                        l_calcMargin.calcAppliedOtherTradingPower(OrderTypeEnum.MF_BUY, i).tradingPower);
                //その他商品買付可能額取得
                double l_dblOtherTradingPower = Math.max(
                        0,
                        l_calcMargin.calcAppliedOtherTradingPower(null, i).tradingPower);
                //出金可能額取得
                double l_dblPaymentTradingPower = 0;

                if (i == 1)
                {
                    try
                    {
                        l_dblPaymentTradingPower =
                            l_service.getPaymentTradingPowerAioCashoutInput(l_subAccount, l_datBizDate);
                    }
                    catch (WEB3BaseException l_ex)
                    {
                        log.error(l_ex.getMessage(), l_ex);
                        throw new WEB3BaseRuntimeException(
                            l_ex.getErrorInfo(),
                            getClass().getName() + ".createTradingPowerResponse",
                            l_ex);
                    }
                }
                else
                {
                    l_dblPaymentTradingPower = Math.max(
                        0,
                        l_calcMargin.calcAppliedOtherTradingPower(OrderTypeEnum.CASH_OUT, i).tradingPower);
                }
                //保証金預託率取得
                double l_dblMarginDepositRate = l_calcMargin.calcAppliedMarginDepositRate(i).tradingPower;

                //営業日(株式買付可能額．適用日)を取得
                Date l_datEquityBuyApplyDate = l_condition.getBizDate(l_calcMargin.calcAppliedEquityTradingPower(i).appliedPoint);
                //営業日(信用新規建可能額．適用日)を取得
                Date l_datMarginApplyDate = l_condition.getBizDate(l_calcMargin.calcAppliedMarginTradingPower(i).appliedPoint);

                //取引余力明細ユニット
                WEB3TPTradingPowerUnit l_unit = new WEB3TPTradingPowerUnit();

                //レスポンスにプロパティセット
                l_unit.bizDate = l_datBizDate;//日付
                l_unit.equityTradingPower = format(l_dblEquityTradingPower);//現物株買付余力
                l_unit.marginTradingPower = format(l_dblMarginTradingPower);//信用新規建余力
                l_unit.swapLongTradingPower = format(l_dblActualReceiptTradingPower);//信用現引余力
                l_unit.mutualTradingPower = format(l_dblFundTradingPower);//投信買付余力
                l_unit.otherTradingPower = format(l_dblOtherTradingPower);//その他商品買付余力
                l_unit.paymentTradingPower = format(l_dblPaymentTradingPower);//出金余力

                //保証金預託率=無限大値
                if(l_dblMarginDepositRate == Double.POSITIVE_INFINITY)
                {
                    l_unit.marginCollateralRate = null;//保証金預託率                         
                }
                else
                {
                    l_unit.marginCollateralRate = format(l_dblMarginDepositRate);//保証金預託率                         
                }

                l_unit.equityBuyApplyDate = l_datEquityBuyApplyDate;//余力適用日<現物株買付余力>
                l_unit.marginApplyDate = l_datMarginApplyDate;//余力適用日<信用新規建余力>

                //一覧に設定
                l_units[i] = l_unit;

            }

        }

        /*
         * レスポンス設定
         */
        //余力計算結果ID
        l_response.calcResultId = String.valueOf(l_lngCalcResultId);
        //取引余力明細一覧
        l_response.tradingPowerUnits = l_units;

        /*
         * 現物株式受渡日
         */
        //余力計算基準日<株式買付／信用現引>
        int l_intBasePoint = l_condition.getEquityBasePoint();
        //余力計算基準日<株式買付／信用現引>をDate型に変換
        Date l_datBasePoint = l_condition.getBizDate(l_intBasePoint);
        //レスポンスに余力計算基準日<株式買付／信用現引>（Date型）をセット
        l_response.equityDeliveryDate = l_datBasePoint;

        /*
         * 信用新規建基準日
         */
        //余力計算基準日<信用新規建>
        int l_intMarginBasePoint = l_condition.getMarginBasePoint();
        //余力計算基準日<信用新規建>をDate型に変換
        Date l_datMarginBasePoint = l_condition.getBizDate(l_intMarginBasePoint);
        //レスポンスに余力計算基準日<信用新規建>（Date型）をセット
        l_response.marginBaseDate = l_datMarginBasePoint;

        /*
         * ミニ株受渡日
         */
        //余力計算基準日<ミニ株>
        int l_intMstkBasePoint = l_condition.getMstkBasePoint();
        //余力計算基準日<ミニ株>をDate型に変換
        Date l_datMstkBasePoint = l_condition.getBizDate(l_intMstkBasePoint);
        //レスポンスに余力計算基準日<ミニ株>（Date型）をセット
        l_response.mstkDeliveryDate = l_datMstkBasePoint;

        /*
         * オプション受渡日
         */
        //余力計算基準日<オプション新規建>
        int l_intOptionBasePoint = l_condition.getOptionBasePoint();
        //余力計算基準日<オプション新規建>をDate型に変換
        Date l_datOptionBasePoint = l_condition.getBizDate(l_intOptionBasePoint);
        //レスポンスに余力計算基準日<オプション新規建>（Date型）をセット
        l_response.optionsDeliveryDate = l_datOptionBasePoint;

        /*
         * 投信受渡日
         */
        //余力計算基準日<投信>
        int l_intFundBasePoint = l_condition.getFundBasePoint();
        //余力計算基準日<投信>をDate型に変換
        Date l_datFundBasePoint = l_condition.getBizDate(l_intFundBasePoint);
        //レスポンスに余力計算基準日<投信>（Date型）をセット
        l_response.mutualDeliveryDate = l_datFundBasePoint;

        /*
         * 累投受渡日
         */
        //余力計算基準日<累投>
        int l_intRuitoBasePoint = l_condition.getRuitoBasePoint();
        //余力計算基準日<累投>をDate型に変換
        Date l_datruitoBasePoint = l_condition.getBizDate(l_intRuitoBasePoint);
        //レスポンスに余力計算基準日<累投>（Date型）をセット
        l_response.ruitoDeliveryDate = l_datruitoBasePoint;

        /*
         * IPO基準日
         */
        //余力計算基準日<IPO>
        int l_intIpoBasePoint = l_condition.getIpoBasePoint();
        //余力計算基準日<IPO>をDate型に変換
        Date l_datIpoBasePoint = l_condition.getBizDate(l_intIpoBasePoint);
        //レスポンスに余力計算基準日<IPO>（Date型）をセット
        l_response.ipoBaseDate = l_datIpoBasePoint;

        /*
         * 中国株式受渡日
         */
        //余力計算基準日<中国株式>
        int l_intFeqBasePoint = l_condition.getFeqBasePoint();
        //余力計算基準日<中国株式>をDate型に変換
        Date l_datFeqBasePoint = l_condition.getBizDate(l_intFeqBasePoint);
        //レスポンスに余力計算基準日<中国株式>（Date型）をセット
        l_response.feqDeliveryDate = l_datFeqBasePoint;

        /*
         * 出金受渡日
         */
        //余力計算基準日<出金>
        int l_intPaymentBasePoint = l_condition.getPaymentBasePoint();
        //余力計算基準日<出金>をDate型に変換
        Date l_datPaymentBasePoint = l_condition.getBizDate(l_intPaymentBasePoint);
        //レスポンスに余力計算基準日<出金>（Date型）をセット
        l_response.aioDeliveryDate = l_datPaymentBasePoint;

        //レスポンス返却
        return l_response;
    }

    /**
     * (create日計り銘柄取引余力試算画面)<BR>
     * 日計り銘柄取引余力試算画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（資産余力情報画面表示サービス）create日計り銘柄取引余力試算画面」参照。<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPDayTradeTradingPowerResponse
     * @@roseuid 41B67EA40241
     */
    protected WEB3TPDayTradeTradingPowerResponse createDayTradeTradingPowerResponse(
            WEB3TPDayTradeTradingPowerRequest l_request) throws WEB3BaseException
    {

        //レスポンスを取得
        WEB3TPDayTradeTradingPowerResponse l_response = (WEB3TPDayTradeTradingPowerResponse)l_request.createResponse();

        //リクエストより銘柄コードを取得
        String l_strProductCode = l_request.productCode;

        //銘柄コードがNULLの場合、処理終了
        if(l_strProductCode == null)
        {
            return l_response;
        }

        //補助口座を取得
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //証券会社コード取得
        String l_institutionCode = l_subAccount.getInstitution().getInstitutionCode();

        //株式銘柄
        EqtypeProductRow l_eqtypeProduct = null;
        try
        {
            //株式銘柄取得
            l_eqtypeProduct = EqtypeProductDao.findRowByInstitutionCodeProductCode(
                    l_institutionCode,
                    l_strProductCode);
        }
        catch(DataFindException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }
        catch(DataNetworkException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }
        catch(DataQueryException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }

        if(l_eqtypeProduct == null)
        {
            if(DBG)
            {
                log.debug("株式銘柄マスタに該当するレコードがありません。");
            }
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }

        //銘柄
        ProductRow l_product = null;
        try
        {
            //銘柄取得
            l_product = ProductDao.findRowByPk(l_eqtypeProduct.getProductId());
        }
        catch(DataFindException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }
        catch(DataNetworkException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }
        catch(DataQueryException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }

        if(l_product == null)
        {
            if(DBG)
            {
                log.debug("銘柄マスタに該当するレコードがありません。");
            }
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }

        //銘柄名取得
        String l_strProductName = l_product.getStandardName();

        //発注日取得
        Date l_orderDate = l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        if(DBG)
        {
            log.debug("発注日 = " + l_orderDate);
        }

        //優先順市場リスト取得
        List l_lisMarket = getMarketList(l_subAccount.getInstitution());

        //取引銘柄ID
        long l_lngTradedProductID;

        //受渡日
        Date l_datDevidendRightDate = null;
        //市場ID
        long l_lngMarketId = 0;
        //売買単位取得
        double l_dblLotSize;

        //即日入金対象銘柄フラグ
        boolean l_isTodayDepFundReg;

        //get取引銘柄
        Object l_tradedProduct = getTradedProduct(l_product, l_orderDate, l_lisMarket);
        if(l_tradedProduct instanceof TradedProductRow)
        {
            //取引銘柄Row
            TradedProductRow l_tradedProductRow = (TradedProductRow)l_tradedProduct;
            //取引銘柄ID取得
            l_lngTradedProductID = l_tradedProductRow.getTradedProductId();
            //受渡日取得
            l_datDevidendRightDate = l_tradedProductRow.getDailyDeliveryDate();
            //市場ID取得
            l_lngMarketId = l_tradedProductRow.getMarketId();
        }
        else
        {
            //取引銘柄UpdqRow
            TradedProductUpdqRow l_tradedProductUpdqRow = (TradedProductUpdqRow)l_tradedProduct;
            //取引銘柄ID取得
            l_lngTradedProductID = l_tradedProductUpdqRow.getTradedProductId();
            //受渡日取得
            l_datDevidendRightDate = l_tradedProductUpdqRow.getDailyDeliveryDate();
            //市場ID取得
            l_lngMarketId = l_tradedProductUpdqRow.getMarketId();
        }

        //get株式銘柄
        Object l_eqtypeTradedProduct = getEqtypeTradedProduct(l_lngTradedProductID, l_orderDate);
        if(l_eqtypeTradedProduct instanceof EqtypeTradedProductRow)
        {
            //株式取引銘柄Row
            EqtypeTradedProductRow l_eqtypeTradedProductRow = (EqtypeTradedProductRow)l_eqtypeTradedProduct;
            //売買単位取得
            l_dblLotSize = l_eqtypeTradedProductRow.getLotSize();
            // 即日入金銘柄フラグ取得
            if(BooleanEnum.TRUE.equals(l_eqtypeTradedProductRow.getTodayDepFundReg()))
            {
                l_isTodayDepFundReg = true;
            }
            else
            {
                l_isTodayDepFundReg = false;
            }
        }
        else
        {
            //株式取引銘柄UpdqRow
            EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow = (EqtypeTradedProductUpdqRow)l_eqtypeTradedProduct;
            //売買単位取得
            l_dblLotSize = l_eqtypeTradedProductUpdqRow.getLotSize();
            //即日入金銘柄フラグ取得
            if(BooleanEnum.TRUE.equals(l_eqtypeTradedProductUpdqRow.getTodayDepFundReg()))
            {
                l_isTodayDepFundReg = true;
            }
            else
            {
                l_isTodayDepFundReg = false;
            }
        }

        //差金決済取引余力サービス取得
        WEB3TPTradingPowerSettlementService l_service = (WEB3TPTradingPowerSettlementService)Services.getService(WEB3TPTradingPowerSettlementService.class);

        //差金決済買付可能額取得
        if(DBG)
        {
            log.debug("CALL get差金決済買付可能額");
            log.debug("　@受渡日 = " + l_datDevidendRightDate);
            log.debug("　@銘柄ID = " + l_product.getProductId());
            log.debug("　@即日入金銘柄フラグ = " + l_isTodayDepFundReg);
        }
        double l_dblDayTradeEquityTradingPower = l_service.getBuyOrderPossibleAmount(
                l_subAccount,
                l_datDevidendRightDate,
                l_product.getProductId(),
                l_isTodayDepFundReg);

        //差金決済売付可能数量取得
        if(DBG)
        {
            log.debug("CALL get差金決済売付可能数量");
            log.debug("　@受渡日 = " + l_datDevidendRightDate);
            log.debug("　@銘柄ID = " + l_product.getProductId());
            log.debug("　@市場ID = " + l_lngMarketId);
            log.debug("　@売買単位 = " + l_dblLotSize);
        }
        double l_sellPossQtyResult = l_service.getSellOrderPossibleQuantity(
                l_subAccount,
                l_datDevidendRightDate,
                l_product.getProductId(),
                l_lngMarketId,
                0,
                l_dblLotSize);

        //レスポンスのプロパティセット
        l_response.productCode = l_strProductCode;//銘柄コード
        l_response.productName = l_strProductName;//銘柄名
        l_response.dayTradeEquityTradingPower = format(l_dblDayTradeEquityTradingPower);//指定銘柄買付余力
        l_response.dayTradeEquitySellPossQuantity = format(l_sellPossQtyResult);//指定銘柄売付可能数量

        //レスポンスを返却
        return l_response;
    }

    /**
     * (create余力推移画面)<BR>
     * 余力推移画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（資産余力情報画面表示サービス）create余力推移画面」参照。<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPTransitionReferenceResponse
     * @@roseuid 41B67FC802BE
     */
    protected WEB3TPTransitionReferenceResponse createTransitionReferenceResponse(
            WEB3TPTransitionReferenceRequest l_request) throws WEB3BaseException
    {

        //レスポンス取得
        WEB3TPTransitionReferenceResponse l_response = (WEB3TPTransitionReferenceResponse)l_request.createResponse();

        //補助口座取得
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //顧客取得
        WEB3GentradeMainAccount l_account = null;
        try
        {
            l_account = new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createTransitionReferenceResponse",
                    dqe);
        }
        catch(DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createTransitionReferenceResponse",
                    dne);
        }

        //信用口座開設済みか
        boolean l_isMarginAccountEstablishd = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //現物顧客
        if(!l_isMarginAccountEstablishd)
        {

            //取引余力サービス
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //資産余力情報<現物顧客>取得
            WEB3TPTradingPowerCalcEquity l_calcEquity = l_service.getTradingPowerCalcEquity(l_subAccount);

            //create余力推移明細<現物顧客>
            createTransitionReferenceEquity(l_calcEquity, l_response);

        }
        //信用顧客
        else
        {

            //取引余力サービス
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //資産余力情報<信用顧客>取得
            WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMargin(l_subAccount);

            //create余力推移明細<信用顧客>
            createTransitionReferenceMargin(l_calcMargin, l_response);

        }

        /*
         * 値洗い状態区分をセット
         */
        //会社コード
        String l_strInstCode = l_subAccount.getInstitution().getInstitutionCode();
        //部店コード
        String l_strBranCode = l_subAccount.getWeb3GenBranch().getBranchCode();

        //値洗い状態区分
        String l_strMarkToMarketStateDiv = this.getMarkToMarketStateDiv(
                l_strInstCode,
                l_strBranCode);
        //値洗い状態区分をレスポンスにセット
        l_response.markToMarketStateDiv = l_strMarkToMarketStateDiv;

        //レスポンス返却
        return l_response;
    }

    /**
     * (create余力推移(時価計算)画面)<BR>
     * <BR>
     * ※シーケンス図「（資産余力情報画面表示サービス）create余力推移(時価計算)画面」参照<BR>
     * <BR>
     * @@param l_request
     * @@return WEB3TPTransitionReferenceUseQuoteResponse
     */
    protected WEB3TPTransitionReferenceUseQuoteResponse createTransitionReferenceUseQuoteResponse(
            WEB3TPTransitionReferenceUseQuoteRequest l_request) throws WEB3BaseException
    {

        //レスポンス取得
        WEB3TPTransitionReferenceUseQuoteResponse l_response = (WEB3TPTransitionReferenceUseQuoteResponse)l_request.createResponse();

        //補助口座取得
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //顧客取得
        WEB3GentradeMainAccount l_account = null;
        try
        {
            l_account = new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createTransitionReferenceUseQuoteResponse",
                    dqe);
        }
        catch(DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createTransitionReferenceUseQuoteResponse",
                    dne);
        }

        //信用口座開設済みか
        boolean l_isMarginAccountEstablishd = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //現物顧客
        if(!l_isMarginAccountEstablishd)
        {

            //取引余力サービス
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //資産余力情報<現物顧客>取得
            WEB3TPTradingPowerCalcEquity l_calcEquity = l_service.getTradingPowerCalcEquityQuote(l_subAccount);

            //create余力推移明細<現物顧客>
            createTransitionReferenceEquity(l_calcEquity, l_response);

        }
        //信用顧客
        else
        {

            //取引余力サービス
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //資産余力情報<信用顧客>取得
            WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMarginQuote(l_subAccount);

            //create余力推移明細<信用顧客>
            createTransitionReferenceMargin(l_calcMargin, l_response);

        }

        //レスポンス返却
        return l_response;
    }

    /**
     * (create出金受付状況画面)<BR>
     * 出金受付状況画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（資産余力情報画面表示サービス）create出金受付状況画面」参照。<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPPaymentStatusResponse
     * @@roseuid 41B933E20110
     */
    protected WEB3TPPaymentAcceptResponse createPaymentStatusResponse(
            WEB3TPPaymentAcceptRequest l_request) throws WEB3BaseException
    {
        //レスポンス取得
        WEB3TPPaymentAcceptResponse l_response = (WEB3TPPaymentAcceptResponse)l_request.createResponse();

        //補助口座取得
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //顧客取得
        WEB3GentradeMainAccount l_account = null;
        try
        {
            l_account = new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createPaymentStatusResponse",
                    dqe);
        }
        catch(DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createPaymentStatusResponse",
                    dne);
        }

        //信用口座開設済みか
        boolean l_isMarginAccountEstablishd = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //出金受付状況明細一覧
        WEB3TPPaymentAcceptUnit[] l_paymentAcceptUnits = new WEB3TPPaymentAcceptUnit[6];

        //余力計算結果ID
        long l_lngCalcResultId;

        //現物顧客
        if(!l_isMarginAccountEstablishd)
        {

            //取引余力サービス
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //資産余力情報<現物顧客>取得
            WEB3TPTradingPowerCalcEquity l_calcEquity = l_service.getTradingPowerCalcEquity(l_subAccount);

            //余力計算結果Detail<現物顧客>Params取得
            TpCalcResultEquityDetailParams l_detailEquity = l_calcEquity.getCalcResultDetailEquity();

            //余力計算条件取得
            WEB3TPCalcCondition l_condition = l_calcEquity.getCalcCondition();

            //余力計算結果ID取得
            l_lngCalcResultId = l_detailEquity.getCalcResultEquityId();

            //T0～T+5
            for(int i = 0; i < 6; i++)
            {
                //営業日(T+n)取得
                Date l_bizDate = l_condition.getBizDate(i);

                //出金額<指定日>(T+n)取得
                double l_dblPaymentAmountDesignate = getPaymentAmountDesignate(l_detailEquity, i);

                //出金受付状況明細インスタンス生成
                WEB3TPPaymentAcceptUnit l_paymentAcceptUnit = new WEB3TPPaymentAcceptUnit();

                //ユニットのプロパティセット
                l_paymentAcceptUnit.bizDate = l_bizDate;//日付
                l_paymentAcceptUnit.paymentAmount = format(l_dblPaymentAmountDesignate);//出金額

                //一覧に追加
                l_paymentAcceptUnits[i] = l_paymentAcceptUnit;

            }

        }
        //信用顧客
        else
        {

            //取引余力サービス
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //資産余力情報<信用顧客>取得
            WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMargin(l_subAccount);

            //余力計算結果Detail<信用顧客>Params取得
            TpCalcResultMarginDetailParams l_detailMargin = l_calcMargin.getCalcResultDetailMargin();

            //余力計算条件取得
            WEB3TPCalcCondition l_condition = l_calcMargin.getCalcCondition();

            //余力計算結果ID取得
            l_lngCalcResultId = l_detailMargin.getCalcResultMarginId();

            //T0～T+5
            for(int i = 0; i < 6; i++)
            {
                //営業日(T+n)取得
                Date l_bizDate = l_condition.getBizDate(i);

                //出金額<指定日>(T+n)取得
                double l_dblPaymentAmountDesignate = getPaymentAmountDesignate(l_detailMargin, i);

                //出金受付状況明細インスタンス生成
                WEB3TPPaymentAcceptUnit l_paymentAcceptUnit = new WEB3TPPaymentAcceptUnit();

                //ユニットのプロパティセット
                l_paymentAcceptUnit.bizDate = l_bizDate;//日付
                l_paymentAcceptUnit.paymentAmount = format(l_dblPaymentAmountDesignate);//出金額

                //一覧に追加
                l_paymentAcceptUnits[i] = l_paymentAcceptUnit;

            }

        }

        //レスポンスのプロパティセット
        l_response.calcResultId = String.valueOf(l_lngCalcResultId);//余力計算結果ID
        l_response.paymentAcceptUnits = l_paymentAcceptUnits;//出金受付状況一覧

        //レスポンス返却
        return l_response;
    }

    /**
     * (create現物株買付余力詳細画面)<BR>
     * 現物株買付余力詳細画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（資産余力情報画面表示サービス）create現物株買付余力詳細画面」参照。<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPEquityTradingPowerDetailResponse
     * @@roseuid 41B680320128
     */
    protected WEB3TPEquityTradingPowerDetailResponse createEquityTradingPowerDetailResponse(
            WEB3TPEquityTradingPowerDetailRequest l_request) throws WEB3BaseException
    {

        //レスポンス取得
        WEB3TPEquityTradingPowerDetailResponse l_response = (WEB3TPEquityTradingPowerDetailResponse)l_request.createResponse();

        //補助口座取得
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //顧客取得
        WEB3GentradeMainAccount l_account = null;
        try
        {
            l_account = new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createEquityTradingPowerDetailResponse",
                    dqe);
        }
        catch(DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createEquityTradingPowerDetailResponse",
                    dne);
        }

        //信用口座開設済みか
        boolean l_isMarginAccountEstablishd = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //信用顧客でない場合、エラー
        if(!l_isMarginAccountEstablishd)
        {
            log.error("指定された口座IDに対応する顧客は、信用顧客ではありません。");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "createEquityTradingPowerDetailResponse");
        }

        //余力更新結果ID取得
        long l_lngResultId = Long.parseLong(l_request.calcResultId);

        //取引余力サービス
        WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

        //資産余力情報<信用顧客>取得
        WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMargin(l_lngResultId);

        //日付取得
        Date l_bizDate = l_request.bizDate;

        //余力計算条件取得
        WEB3TPCalcCondition l_condition = l_calcMargin.getCalcCondition();

        //指定日取得
        int l_intSpecifiedPoint = l_condition.calcSpecifiedPoint(l_bizDate);

        //預り金残高(指定日)取得
        double l_dblAccountBalance1 = l_calcMargin.getAccountBalance(l_intSpecifiedPoint);
        //預り金残高(指定日-1)取得
        double l_dblAccountBalance2 = 0d;
        if(l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0)
        {
            l_dblAccountBalance2 = l_calcMargin.getAccountBalance(l_intSpecifiedPoint - 1);
        }
        //当日約定済代金(指定日)取得
        double l_dblTodayExecutedAmount1 = l_calcMargin.getTodayExecutedAmount(l_intSpecifiedPoint);
        //当日約定済代金(指定日-1)取得
        double l_dblTodayExecutedAmount2 = 0d;
        if(l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0)
        {
            l_dblTodayExecutedAmount2 = l_calcMargin.getTodayExecutedAmount(l_intSpecifiedPoint - 1);
        }
        //当日未約定代金(指定日)取得
        double l_dblTodayUnexecutedAmount = l_calcMargin.getTodayUnexecutedAmount(l_intSpecifiedPoint);
        //日計り拘束金(指定日)取得
        double l_dblDayTradeRestraint = l_calcMargin.getDayTradeRestraint(l_intSpecifiedPoint);
        //その他拘束金(指定日)取得
        double l_dblOtherRestraint = l_calcMargin.getOtherRestraint(l_intSpecifiedPoint);
        //現金保証金(指定日)取得
        double l_dblMarginAccountBalance = l_calcMargin.calcMarginAccountBalance(l_intSpecifiedPoint);
        //代用証券評価額(指定日)取得
        double l_dblSubstituteSecurityAsset = l_calcMargin.getSubstituteSecurityAsset(l_intSpecifiedPoint);
        //未決済建玉評価損益(指定日)取得
        double l_dblContractAssetProfitLoss = l_calcMargin.getContractAssetProfitLoss(l_intSpecifiedPoint);
        //建玉諸経費取得
        double l_dblContractTotalCost = l_calcMargin.getContractTotalCost(l_intSpecifiedPoint);
        //未受渡建玉決済損(指定日)取得
        double l_dblUndeliContractLoss = l_calcMargin.getUndeliContractLoss(l_intSpecifiedPoint);
        //未受渡建玉決済益(指定日)取得
        double l_dblUndeliContractProfit = l_calcMargin.getUndeliContractProfit(l_intSpecifiedPoint);
        //受入保証金(指定日)取得
        double l_dblReceiptMarginDeposit = l_calcMargin.calcReceiptMarginDeposit(l_intSpecifiedPoint);
        //必要保証金(指定日)取得
        double l_dblMarginDeposit = l_calcMargin.getMarginDeposit(l_intSpecifiedPoint);
        //現金必要保証金(指定日)取得
        double l_dblCashMarginDeposit = l_calcMargin.getCashMarginDeposit(l_intSpecifiedPoint);
        //未受渡建玉現金必要保証金(指定日)取得
        double l_dblUndeliCashMarginDeposit = l_calcMargin.getUndeliCashMarginDeposit(l_intSpecifiedPoint);
        //保証金余力(指定日)取得
        double l_dblMarginPower = l_calcMargin.calcMarginPower(l_intSpecifiedPoint);
        //使用可能現金(指定日)取得
        double l_dblActualAccountBalance = l_calcMargin.calcActualAccountBalance(l_intSpecifiedPoint);
        //株式買付可能額(指定日)取得
        double l_dblEquityTradingPower = l_calcMargin.calcEquityTradingPower(l_intSpecifiedPoint);

        //レスポンスのプロパティセット
        l_response.calcResultId = l_request.calcResultId;//余力計算結果ID
        l_response.bizDate = l_bizDate;//日付
        l_response.accountBalance = format(l_dblAccountBalance1 - l_dblTodayExecutedAmount1);//預り金

        //前日比
        if(l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0)
        {
            l_response.comparedPreviousDay = format((l_dblAccountBalance1 - l_dblTodayExecutedAmount1)
                    - (l_dblAccountBalance2 - l_dblTodayExecutedAmount2));
        }

        l_response.unexecutedAmount = format(l_dblTodayUnexecutedAmount);//発注充当金
        l_response.dayTradeRestraint = format(l_dblDayTradeRestraint);//日計り拘束金
        l_response.otherRestraint = format(l_dblOtherRestraint);//その他拘束金
        l_response.cashDeposit = format(l_dblMarginAccountBalance);//現金保証金
        l_response.substituteSecurityAsset = format(l_dblSubstituteSecurityAsset);//代用証券評価額
        l_response.contractAssetProfitLoss = format(l_dblContractAssetProfitLoss);//建玉評価損益
        l_response.contractTotalCost = format(l_dblContractTotalCost);//建玉諸経費
        l_response.undeliContractLoss = format(l_dblUndeliContractLoss);//未受渡建玉決済損
        l_response.undeliContractProfit = format(l_dblUndeliContractProfit);//未受渡建玉決済益
        l_response.acceptDeposit = format(l_dblReceiptMarginDeposit);//受入保証金
        l_response.marginDeposit = format(l_dblMarginDeposit);//必要保証金
        l_response.cashMarginDeposit = format(l_dblCashMarginDeposit + l_dblUndeliCashMarginDeposit);//必要保証金
        l_response.depositTradingPower = format(l_dblMarginPower);//保証金余力
        l_response.actualAccountBalance = format(l_dblActualAccountBalance);//使用可能現金
        l_response.equityApplyTradingPower = format(l_dblEquityTradingPower);//現物株買付充当余力
        l_response.equityTradingPower = format(l_dblEquityTradingPower);//現物株買付余力

        //レスポンス返却
        return l_response;
    }

    /**
     * (create信用新規建余力詳細画面)<BR>
     * 信用新規建余力詳細画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（資産余力情報画面表示サービス）create信用新規建余力詳細画面」参照。<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPMarginTradingPowerDetailResponse
     * @@roseuid 41B680A3004D
     */
    protected WEB3TPMarginTradingPowerDetailResponse createMarginTradingPowerDetailResponse(
            WEB3TPMarginTradingPowerDetailRequest l_request) throws WEB3BaseException
    {

        //レスポンス取得
        WEB3TPMarginTradingPowerDetailResponse l_response = (WEB3TPMarginTradingPowerDetailResponse)l_request.createResponse();

        //補助口座取得
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //顧客取得
        WEB3GentradeMainAccount l_account = null;
        try
        {
            l_account = new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createMarginTradingPowerDetailResponse",
                    dqe);
        }
        catch(DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createMarginTradingPowerDetailResponse",
                    dne);
        }

        //信用口座開設済みか
        boolean l_isMarginAccountEstablishd = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //信用顧客でない場合、エラー
        if(!l_isMarginAccountEstablishd)
        {
            log.error("指定された口座IDに対応する顧客は、信用顧客ではありません。");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "createMarginTradingPowerDetailResponse");
        }

        //余力更新結果ID取得
        long l_lngResultId = Long.parseLong(l_request.calcResultId);

        //取引余力サービス
        WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

        //資産余力情報<信用顧客>取得
        WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMargin(l_lngResultId);

        //余力計算結果詳細Params<信用顧客>取得
        TpCalcResultMarginDetailParams l_detailMargin = l_calcMargin.getCalcResultDetailMargin();

        //日付取得
        Date l_bizDate = l_request.bizDate;

        //余力計算条件取得
        WEB3TPCalcCondition l_condition = l_calcMargin.getCalcCondition();

        //指定日取得
        int l_intSpecifiedPoint = l_condition.calcSpecifiedPoint(l_bizDate);

        //預り金残高(指定日)取得
        double l_dblAccountBalance1 = l_calcMargin.getAccountBalance(l_intSpecifiedPoint);
        //預り金算残高(指定日-1)取得
        double l_dblAccountBalance2 = 0d;
        if(l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0)
        {
            l_dblAccountBalance2 = l_calcMargin.getAccountBalance(l_intSpecifiedPoint - 1);
        }
        //当日約定済代金(指定日)取得
        double l_dblTodayExecutedAmount1 = l_calcMargin.getTodayExecutedAmount(l_intSpecifiedPoint);
        //当日約定済だ金(指定日-1)取得
        double l_dblTodayExecutedAmount2 = 0d;
        if(l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0)
        {
            l_dblTodayExecutedAmount2 = l_calcMargin.getTodayExecutedAmount(l_intSpecifiedPoint - 1);
        }
        //当日未約定代金(指定日)取得
        double l_dblTodayUnexecutedAmount = l_calcMargin.getTodayUnexecutedAmount(l_intSpecifiedPoint);
        //その他拘束金(指定日)取得
        double l_dblOtherRestraint = l_calcMargin.getOtherRestraint(l_intSpecifiedPoint);
        //現金保証金(指定日)取得
        double l_dblMarginAccountBalance = l_calcMargin.calcMarginAccountBalance(l_intSpecifiedPoint);
        //代用証券評価額(指定日)取得
        double l_dblSubstituteSecurityAsset = l_calcMargin.getSubstituteSecurityAsset(l_intSpecifiedPoint);
        //発注分代用証券評価額(指定日)取得
        double l_dblUnexecSubstiSecurityAsset = getUnexecSubstiSecurityAsset(
                l_detailMargin,
                l_intSpecifiedPoint);
        //差入保証金(指定日)取得
        double l_dblPaidMarginDeposit = l_calcMargin.calcPaidMarginDeposit(l_intSpecifiedPoint);

        //未決済建玉評価損(指定日)取得
        double l_dblContractAssetLoss = getContractAssetLoss(l_detailMargin, l_intSpecifiedPoint);
        //未決済建玉評価益(指定日)取得
        double l_dblContractAssetProfit = getContractAssetProfit(l_detailMargin, l_intSpecifiedPoint);
        
        //未決済建玉評価損益取得
        double l_dblContractAssetProfitLoss = l_calcMargin.getContractAssetProfitLoss(l_intSpecifiedPoint);

        //get建手数料(int)
        //[引数]
        //int = calc指定日()の戻り値
        double l_dblSetupFee = l_calcMargin.getSetupFee(l_intSpecifiedPoint);
        //get日歩・逆日歩損(int)
        //[引数]
        //int = calc指定日()の戻り値
        double l_dblContractInterestLoss = l_calcMargin.getContractInterestLoss(l_intSpecifiedPoint);
        //get日歩・逆日歩益(int)
        //[引数]
        //int = calc指定日()の戻り値
        double l_dblContractInterestProfit = l_calcMargin.getContractInterestProfit(l_intSpecifiedPoint);
        //getその他建玉諸経費(int)
        //[引数]
        //int = calc指定日()の戻り値
        double l_dblContractOtherCost = l_calcMargin.getContractOtherCost(l_intSpecifiedPoint);

        //見受渡建玉決済損(指定日)取得
        double l_dblUndeliContractLoss = l_calcMargin.getUndeliContractLoss(l_intSpecifiedPoint);
        //未受渡建玉決済益(指定日)取得
        double l_dblUndeliContractProfit = l_calcMargin.getUndeliContractProfit(l_intSpecifiedPoint);
        //建玉諸経費取得
        double l_dblContractTotalCost = l_calcMargin.getContractTotalCost(l_intSpecifiedPoint);
        //受入保証金(指定日)取得
        double l_dblReceiptMarginDeposit = l_calcMargin.calcReceiptMarginDeposit(l_intSpecifiedPoint);

        //未決済建玉代金(指定日)取得
        double l_dblContractAmount = l_calcMargin.getContractAmount(l_intSpecifiedPoint);
        //発注分建玉代金(指定日)取得
        double l_dblUnexecContractAmount = getUnexecContractAmount(
                l_detailMargin,
                l_intSpecifiedPoint);
        //日計り返済・現引現渡建玉代金(指定日)取得
        double l_dblDayRepayContractAmount = l_calcMargin.getDayRepayContractAmount(l_intSpecifiedPoint);

        //必要保証金(指定日)取得
        double l_dblMarginDeposit = l_calcMargin.getMarginDeposit(l_intSpecifiedPoint);
        //発注分必要保証金(指定日)取得
        double l_dblUnexecMarginDeposit = getUnexecMarginDeposit(
                l_detailMargin,
                l_intSpecifiedPoint);
        //日計り返済・現引現渡必要保証金(指定日)取得
        double l_dblDayRepayMarginDeposit = getDayRepayMarginDeposit(
                l_detailMargin,
                l_intSpecifiedPoint);

        //現金必要保証金(指定日)取得
        double l_dblCashMarginDeposit = l_calcMargin.getCashMarginDeposit(l_intSpecifiedPoint);
        //発注分現金必要保証金(指定日)取得
        double l_dblUnexecCashMarginDeposit = getUnexecCashMarginDeposit(
                l_detailMargin,
                l_intSpecifiedPoint);
        //日計り返済・現引現渡現金必要保証金(指定日)取得
        double l_dblDayRepayCashMarginDeposit = getDayRepayCashMarginDeposit(
                l_detailMargin,
                l_intSpecifiedPoint);

        //保証金余力(指定日)取得
        double l_dblMarginPower = l_calcMargin.calcMarginPower(l_intSpecifiedPoint);
        //信用新規建可能額(指定日)取得
        double l_dblMarginTradingPower = l_calcMargin.calcMarginTradingPower(l_intSpecifiedPoint);
        //保証金預託率(指定日)取得
        double l_dblMarginDepositRate1 = l_calcMargin.calcMarginDepositRate(l_intSpecifiedPoint);
        //建玉決済損<当日>
        double l_dblTodayRepayContractLoss = l_detailMargin.getTodayRepayContractLoss();
        //建玉決済益<当日>
        double l_dblTodayRepayContractProfit = l_detailMargin.getTodayRepayContractProfit();
        //建玉決済損<指定日>
        double l_dblContractLossDesignate = getContractLossDesignate(
                l_detailMargin,
                l_intSpecifiedPoint);
        //建玉決済益<指定日>
        double l_dblContractProfitDesignate = getContractProfitDesignate(
                l_detailMargin,
                l_intSpecifiedPoint);
        //決済建玉前日価格評価<当日>取得
        double l_dblTodayRepayContractPreAsset = l_detailMargin.getTodayRepayContractPreAsset();

        //保証金率取得
        int l_intMarginDepositRate = l_condition.getMarginDepositRate();
        //保証金維持率取得
        int l_intMarginMentenanceRate = l_condition.getMarginMentenanceRate();
        //法@定保証金率取得
        int l_intLegalMarginDepositRate = l_condition.getLegalMarginDepositRate();
        //最低必要保証金取得
        double l_dblMinMarginDeposit = l_condition.getMinMarginDeposit();

        //保証金維持余力(保証金率)取得
        double l_dblMarginMaintenancePower1 = l_calcMargin.calcMarginMaintenancePower(
                l_intSpecifiedPoint,
                l_intMarginDepositRate);
        //保証金維持余力(保証金維持率)取得
        double l_dblMarginMaintenancePower2 = l_calcMargin.calcMarginMaintenancePower(
                l_intSpecifiedPoint,
                l_intMarginMentenanceRate);
        //保証金維持余力(法@定保証金率)取得
        double l_dblMarginMaintenancePower3 = l_calcMargin.calcMarginMaintenancePower(
                l_intSpecifiedPoint,
                l_intLegalMarginDepositRate);

        //保証金維持余力ユニット(保証金率)
        WEB3TPMarginMaintenanceTradingPowerUnit l_unit1 = new WEB3TPMarginMaintenanceTradingPowerUnit();
        l_unit1.marginMaintenanceRate = String.valueOf(l_intMarginDepositRate);//保証金維持率
        l_unit1.marginMaintenanceTradingPower = format(l_dblMarginMaintenancePower1);//保証金維持余力

        //保証金維持余力ユニット(保証金維持率)
        WEB3TPMarginMaintenanceTradingPowerUnit l_unit2 = new WEB3TPMarginMaintenanceTradingPowerUnit();
        l_unit2.marginMaintenanceRate = String.valueOf(l_intMarginMentenanceRate);//保証金維持率
        l_unit2.marginMaintenanceTradingPower = format(l_dblMarginMaintenancePower2);//保証金維持余力

        //保証金維持余力ユニット(法@定保証金率)
        WEB3TPMarginMaintenanceTradingPowerUnit l_unit3 = new WEB3TPMarginMaintenanceTradingPowerUnit();
        l_unit3.marginMaintenanceRate = String.valueOf(l_intLegalMarginDepositRate);//保証金維持率
        l_unit3.marginMaintenanceTradingPower = format(l_dblMarginMaintenancePower3);//保証金維持余力

        WEB3TPMarginMaintenanceTradingPowerUnit[] l_units =
        {l_unit1, l_unit2, l_unit3};

        //レスポンスのプロパティセット
        l_response.calcResultId = l_request.calcResultId;//余力計算結果ID
        l_response.bizDate = l_bizDate;//日付
        l_response.accountBalance = format(l_dblAccountBalance1 - l_dblTodayExecutedAmount1);//預り金

        //前日比
        if(l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0)
        {
            l_response.comparedPreviousDay = format((l_dblAccountBalance1 - l_dblTodayExecutedAmount1)
                    - (l_dblAccountBalance2 - l_dblTodayExecutedAmount2));
        }

        l_response.unexecutedAmount = format(l_dblTodayUnexecutedAmount);//発注充当金
        l_response.otherRestraint = format(l_dblOtherRestraint);//その他拘束金
        l_response.cashDeposit = format(l_dblMarginAccountBalance);//現金保証金
        l_response.substituteSecurityAsset = format(l_dblSubstituteSecurityAsset
                - l_dblUnexecSubstiSecurityAsset);
        l_response.orderSubstituteSecurityAsset = format(l_dblUnexecSubstiSecurityAsset);
        l_response.guarantyDeposit = format(l_dblPaidMarginDeposit);//差入保証金
        l_response.contractAssetLoss = format(l_dblContractAssetLoss);//建玉評価損
        l_response.contractAssetProfit = format(l_dblContractAssetProfit);//建玉評価益
        l_response.contractAssetProfitLoss = format(l_dblContractAssetProfitLoss);//建玉評価損益
        l_response.contractCommission = format(l_dblSetupFee);//建手数料
        l_response.interestFeeLoss = format(l_dblContractInterestLoss);//日歩・逆日歩・貸株料
        l_response.interestFeeProfit = format(l_dblContractInterestProfit);//日歩・逆日歩益
        l_response.otherAccruedCost = format(l_dblContractOtherCost);//その他未収費用
        l_response.contractTotalCost = format(l_dblContractTotalCost);//建玉諸経費
        l_response.undeliContractLoss = format(l_dblUndeliContractLoss);//未受渡建玉決済損
        l_response.undeliContractProfit = format(l_dblUndeliContractProfit);//未受渡建玉決済益
        l_response.acceptDeposit = format(l_dblReceiptMarginDeposit);//受入保証金
        l_response.contractAmount = format(l_dblContractAmount - l_dblUnexecContractAmount);//純粋未決済建玉代金
        l_response.marginDeposit = format(l_dblMarginDeposit
                - l_dblUnexecMarginDeposit
                - l_dblDayRepayMarginDeposit);//純粋未決済建玉の必要保証金
        l_response.cashMarginDeposit = format(l_dblCashMarginDeposit
                - l_dblUnexecCashMarginDeposit
                - l_dblDayRepayCashMarginDeposit);//純粋未決済建玉の現金必要保証金

        l_response.orderContractAmount = format(l_dblUnexecContractAmount);//発注分建玉代金
        l_response.orderMarginDeposit = format(l_dblUnexecMarginDeposit);//発注分必要保証金
        l_response.orderCashMarginDeposit = format(l_dblUnexecCashMarginDeposit);//発注分現金必要保証金

        l_response.dayRepayContractAmount = format(l_dblDayRepayContractAmount);//日計り返済・現引現渡建玉代金
        l_response.dayRepayMarginDeposit = format(l_dblDayRepayMarginDeposit);//日計り返済・現引現渡必要保証金
        l_response.dayRepayCashMarginDeposit = format(l_dblDayRepayCashMarginDeposit);//日計り返済・現引現渡現金必要保証金

        l_response.marginDepositTotal = format(l_dblMarginDeposit);//必要保証金合計       
        l_response.cashMarginDepositTotal = format(l_dblCashMarginDeposit);//現金必要保証金合計       
        l_response.depositTradingPower = format(l_dblMarginPower);//保証金余力
        l_response.marginTradingPower = format(l_dblMarginTradingPower);//信用新規建余力
        l_response.marginMaintenanceTradingPowerUnits = l_units;//保証金維持余力
        //保証金預託率=無限大値
        if(l_dblMarginDepositRate1 == Double.POSITIVE_INFINITY)
        {
            l_response.marginCollateralRate = null;//保証金預託率
        }
        //以外の場合
        else
        {
            l_response.marginCollateralRate = format(l_dblMarginDepositRate1);//保証金預託率
        }
        l_response.marginDepositRate = format(l_intMarginDepositRate);//保証金率
        l_response.minMarginDeposit = format(l_dblMinMarginDeposit);//最低必要保証金
        l_response.settleContractLossToday = format(l_dblTodayRepayContractLoss);//建玉決済損<当日>
        l_response.settleContractLossDesignatedDate = format(l_dblContractLossDesignate);//建玉決済損<指定日>
        l_response.settleContractProfitToday = format(l_dblTodayRepayContractProfit);//建玉決済益<当日>
        l_response.settleContractProfitDesignatedDate = format(l_dblContractProfitDesignate);//建玉決済益<指定日>
        l_response.settleContractProfitLossToday = format(l_dblTodayRepayContractProfit
                - l_dblTodayRepayContractLoss);//決済損益合計<当日>
        l_response.settleContractPrevDay = format(l_dblTodayRepayContractPreAsset);//決済建玉<前日価格評価>
        l_response.settleContractProfitLossTotal = format(l_dblContractProfitDesignate
                - l_dblContractLossDesignate);//決済損益累計<概算>

        //レスポンス返却
        return l_response;
    }

    /**
     * (create余力推移<現物顧客>)<BR>
     * <BR>
     * 引数.資産余力情報<現物顧客>より、<BR>
     * 引数.余力推移画面表示レスポンスに値をセットする。
     * <BR>
     * シーケンス図「(資産余力情報画面表示サービス)create余力推移<現物顧客>」参照<BR>
     * <BR>
     * @@param l_calcEquity 資産余力情報<現物顧客>
     * @@param l_response 余力推移画面表示レスポンス
     */
    protected void createTransitionReferenceEquity(
            WEB3TPTradingPowerCalcEquity l_calcEquity,
            WEB3TPTransitionReferenceResponse l_response)
    {
        //create余力推移明細<現物顧客>
        WEB3TPTransitionReferenceUnit[] l_units = createTransitionReferenceUnitsEquity(l_calcEquity);

        //余力計算結果Params<現物顧客>
        TpCalcResultEquityParams l_equityParams = l_calcEquity.getCalcResultEquity();

        //余力計算条件
        WEB3TPCalcCondition l_condition = l_calcEquity.getCalcCondition();

        //余力計算結果ID取得
        long l_lngCalcResultId = l_equityParams.getCalcResultEquityId();

        //is預り証券評価制取得
        boolean l_isEquityEvalDiv = l_condition.isEquityEvalDiv();

        //is証券担保ローン区分
        boolean l_isSecuredLoanSecAccOpenDiv = l_condition.isSecuredLoanSecAccOpenDiv();

        //適用株式買付可能額．適用日取得
        WEB3TPCalcResult l_calcResultEquity = l_calcEquity.calcAppliedEquityTradingPower();
        //投信買付可能額．適用日取得
        WEB3TPCalcResult l_calcResultFund = l_calcEquity.calcFundTradingPower();
        //出金可能額．適用日取得
        WEB3TPCalcResult l_calcResultPayment = l_calcEquity.calcPaymentTradingPower();
        //適用その他商品買付可能額．適用日取得
        WEB3TPCalcResult l_calcResultOther = l_calcEquity.calcAppliedOtherTradingPower(null);

        //営業日(適用株式買付可能額．適用日)取得
        Date l_datEquityApplyDate = l_condition.getBizDate(l_calcResultEquity.appliedPoint);
        //営業日(投信買付可能額．適用日)取得
        Date l_datFundApplyDate = l_condition.getBizDate(l_calcResultFund.appliedPoint);
        //営業日(出金可能額．適用日)取得
        Date l_datPaymentApplyDate = l_condition.getBizDate(l_calcResultPayment.appliedPoint);
        //営業日(適用その他商品買付可能額．適用日)取得
        Date l_datOtherApplyDate = l_condition.getBizDate(l_calcResultOther.appliedPoint);

        /*
         * レスポンスのプロパティに設定
         */
        //余力計算結果ID
        l_response.calcResultId = String.valueOf(l_lngCalcResultId);

        //預り証券顧客区分
        if(l_isEquityEvalDiv)
        {
            //（預り証券顧客）
            l_response.depositDiv = WEB3TPDepositDivDef.DEPOSIT_CUSTOMER;
        }
        else
        {
            //（預り証券顧客でない）
            l_response.depositDiv = WEB3TPDepositDivDef.NOT_DEPOSIT_CUSTOMER;
        }

        //余力推移明細一覧
        l_response.transitionReferenceUnits = l_units;
        //余力適用日<現物株買付余力>
        l_response.equityBuyApplyDate = l_datEquityApplyDate;
        //余力適用日<投信買付余力>
        l_response.mutualBuyApplyDate = l_datFundApplyDate;
        //余力適用日<その他商品買付余力>
        l_response.otherApplyDate = l_datOtherApplyDate;
        //余力適用日<出金余力>
        l_response.paymentApplyDate = l_datPaymentApplyDate;

        //証券担保ローン区分
        if(l_isSecuredLoanSecAccOpenDiv)
        {
            //1：実施
            l_response.securedLoanSecAccOpenDiv = WEB3TPSecuredLoanSecAccOpenDivDef.ENFORCEMENT;
        }
        else
        {
            //0：未実施
            l_response.securedLoanSecAccOpenDiv = WEB3TPSecuredLoanSecAccOpenDivDef.NON_ENFORCEMENT;
        }
    }

    /**
     * (create余力推移<信用顧客>)<BR>
     * <BR>
     * 引数.資産余力情報<信用顧客>より、<BR>
     * 引数.余力推移画面表示レスポンスに値をセットする。
     * <BR>
     * シーケンス図「(資産余力情報画面表示サービス)create余力推移<信用顧客>」参照<BR>
     * <BR>
     * @@param l_calcMargin 資産余力情報<信用顧客>
     * @@param l_response 余力推移画面表示レスポンス
     */
    protected void createTransitionReferenceMargin(
            WEB3TPTradingPowerCalcMargin l_calcMargin,
            WEB3TPTransitionReferenceResponse l_response)
    {
        //create余力推移明細<信用顧客>
        WEB3TPTransitionReferenceUnit[] l_units = createTransitionReferenceUnitsMargin(l_calcMargin);

        //余力計算結果Params<信用顧客>
        TpCalcResultMarginParams l_marginParams = l_calcMargin.getCalcResultMargin();

        //余力計算条件
        WEB3TPCalcCondition l_condition = l_calcMargin.getCalcCondition();

        //余力計算結果ID取得
        long l_lngCalcResultId = l_marginParams.getCalcResultMarginId();

        //適用株式買付可能額．適用日取得
        WEB3TPCalcResult l_calcResultEquity = l_calcMargin.calcAppliedEquityTradingPower();
        //適用信用新規建可能額．適用日取得
        WEB3TPCalcResult l_calcResultMargin = l_calcMargin.calcAppliedMarginTradingPower();
        //適用信用現引可能額．適用日取得
        WEB3TPCalcResult l_calcResultActualReceipt = l_calcMargin.calcAppliedActualReceiptTradingPower();
        //投信買付可能額．適用日取得
        WEB3TPCalcResult l_calcResultFund = l_calcMargin.calcFundTradingPower();
        //出金可能額．適用日取得
        WEB3TPCalcResult l_calcResultPayment = l_calcMargin.calcPaymentTradingPower();
        //適用その他商品買付可能額．適用日取得
        WEB3TPCalcResult l_calcResultOther = l_calcMargin.calcAppliedOtherTradingPower(null);
        //適用保証金預託率．適用日取得
        WEB3TPCalcResult l_calcResultMarginDepositRate = l_calcMargin.calcAppliedMarginDepositRate();

        //営業日(適用株式買付可能額．適用日)取得
        Date l_datEquityApplyDate = l_condition.getBizDate(l_calcResultEquity.appliedPoint);
        //営業日(適用信用新規建可能額．適用日)取得
        Date l_datMarginApplyDate = l_condition.getBizDate(l_calcResultMargin.appliedPoint);
        //営業日(適用信用現引可能額．適用日)取得
        Date l_datActualReceiptApplyDate = l_condition.getBizDate(l_calcResultActualReceipt.appliedPoint);
        //営業日(投信買付可能額．適用日)取得
        Date l_datFundApplyDate = l_condition.getBizDate(l_calcResultFund.appliedPoint);
        //営業日(出金可能額．適用日)取得
        Date l_datPaymentApplyDate = l_condition.getBizDate(l_calcResultPayment.appliedPoint);
        //営業日(適用その他商品買付可能額．適用日)取得
        Date l_datOtherApplyDate = l_condition.getBizDate(l_calcResultOther.appliedPoint);
        //営業日(適用保証金預託率．適用日)取得
        Date l_datMarginDepositRateApplyDate = l_condition.getBizDate(l_calcResultMarginDepositRate.appliedPoint);

        /*
         * レスポンスのプロパティに設定
         */
        //余力計算結果ID
        l_response.calcResultId = String.valueOf(l_lngCalcResultId);
        //預り証券顧客区分（預り証券顧客でない）
        l_response.depositDiv = WEB3TPDepositDivDef.NOT_DEPOSIT_CUSTOMER;
        //余力推移明細一覧
        l_response.transitionReferenceUnits = l_units;
        //余力適用日<現物株買付余力>
        l_response.equityBuyApplyDate = l_datEquityApplyDate;
        //余力適用日<信用新規建余力>
        l_response.marginApplyDate = l_datMarginApplyDate;
        //余力適用日<信用現引余力>
        l_response.swapLongApplyDate = l_datActualReceiptApplyDate;
        //余力適用日<投信買付余力>
        l_response.mutualBuyApplyDate = l_datFundApplyDate;
        //余力適用日<その他商品買付余力>
        l_response.otherApplyDate = l_datOtherApplyDate;
        //余力適用日<出金余力>
        l_response.paymentApplyDate = l_datPaymentApplyDate;
        //余力適用日<保証金預託率>
        l_response.marginCollateralRateApplyDate = l_datMarginDepositRateApplyDate;
        //証券担保ローン区分（0：未実施）
        l_response.securedLoanSecAccOpenDiv = WEB3TPSecuredLoanSecAccOpenDivDef.NON_ENFORCEMENT;

    }

    /**
     * (create余力推移明細<現物顧客>)<BR>
     * create余力推移明細処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（資産余力情報画面表示サービス）create余力推移明細<現物顧客>」参照。<BR>
     * <BR>
     * @@param WEB3TPTradingPowerCalcEquity
     * @@return WEB3TPTransitionReferenceUnit
     */
    protected WEB3TPTransitionReferenceUnit[] createTransitionReferenceUnitsEquity(
            WEB3TPTradingPowerCalcEquity l_calcEquity)
    {

        //余力計算条件取得
        WEB3TPCalcCondition l_condition = l_calcEquity.getCalcCondition();

        //特別立替金実績取得
        double l_dblSpecialDebitAmount = l_condition.getSpecialDebitAmount();

        //余力推移明細一覧生成
        WEB3TPTransitionReferenceUnit[] l_units = new WEB3TPTransitionReferenceUnit[6];

        for(int i = 0; i < 6; i++)
        {

            //営業日取得
            Date l_bizDate = l_condition.getBizDate(i);

            //預り金残高(指定日)取得
            double l_dblAccountBalance1 = l_calcEquity.getAccountBalance(i);
            //預り金残高(指定日-1)取得
            double l_dblAccountBalance2 = 0.0d;
            if(i != WEB3TPSpecifiedPointDef.T_0)
            {
                l_dblAccountBalance2 = l_calcEquity.getAccountBalance(i - 1);
            }
            //当日約定済代金取得(指定日)
            double l_dblTodayExecutedAmount1 = l_calcEquity.getTodayExecutedAmount(i);
            //当日約定済代金取得(指定日-1)
            double l_dblTodayExecutedAmount2 = 0.0d;
            if(i != WEB3TPSpecifiedPointDef.T_0)
            {
                l_dblTodayExecutedAmount2 = l_calcEquity.getTodayExecutedAmount(i - 1);
            }
            //当日未約定代金取得
            double l_dblTodayUnexecutedAmount = l_calcEquity.getTodayUnexecutedAmount(i);
            //日計り拘束金取得
            double l_dblDayTradeRestraint = l_calcEquity.getDayTradeRestraint(i);
            //その他拘束金取得
            double l_dblOtherRestraint = l_calcEquity.getOtherRestraint(i);
            //預り金担保出金拘束金取得
            double l_dblCashDepositRestraint = l_calcEquity.getCashDepositRestraint(i);
            //預り証券評価額取得
            double l_dblTrustSecurityAsset = l_calcEquity.getTrustSecurityAsset(i);
            //引出可能現金取得
            double l_dblActualPaymentBalance = l_calcEquity.calcActualPaymentBalance(i);
            //入金請求額
            double l_dblDemandamount = l_calcEquity.calcDemandamount(i);
            //株式買付可能額取得
            double l_dblEquityTradingPower = l_calcEquity.calcEquityTradingPower(i);
            //株式買付可能額<日計り拘束金考慮>取得
            double l_dblEquityTradingPowerIncDayTrade = l_calcEquity.calcEquityTradingPowerIncDayTrade(i);
            //その他商品買付可能額<日計り拘束金考慮>取得
            double l_dblOtherTradingPower = l_calcEquity.calcOtherTradingPowerIncDayTrade(i);
            //出金可能額<日計り拘束金考慮>取得
            double l_dblPaymentTradingPowerIncDayTrade = l_calcEquity.calcPaymentTradingPowerIncDayTrade(i);
            
            
            //余力推移明細生成
            WEB3TPTransitionReferenceUnit l_unit = new WEB3TPTransitionReferenceUnit();

            /*
             * プロパティセット
             */
            //日付
            l_unit.bizDate = l_bizDate;
            //預り金
            l_unit.accountBalance = format(l_dblAccountBalance1 - l_dblTodayExecutedAmount1);

            //前日比
            //T+0でない場合
            if(i != WEB3TPSpecifiedPointDef.T_0)
            {
                l_unit.comparedPreviousDay = format((l_dblAccountBalance1 - l_dblTodayExecutedAmount1)
                        - (l_dblAccountBalance2 - l_dblTodayExecutedAmount2));
            }

            //発注充当金
            l_unit.unexecutedAmount = format(l_dblTodayUnexecutedAmount);

            //日計り拘束金
            //T+0の場合
            if(i == WEB3TPSpecifiedPointDef.T_0)
            {
                //日計り拘束金と特別立替金の大きい方
                l_unit.dayTradeRestraint = format(Math.max(
                        l_dblDayTradeRestraint,
                        l_dblSpecialDebitAmount));
            }
            else
            {
                l_unit.dayTradeRestraint = format(l_dblDayTradeRestraint);
            }

            //その他拘束金
            l_unit.otherRestraint = format(l_dblOtherRestraint);
            //預り金担保出金拘束金
            l_unit.cashDepositRestraint = format(l_dblCashDepositRestraint);
            //証券評価額
            l_unit.securityAsset = format(l_dblTrustSecurityAsset);
            //出金余力
            l_unit.paymentTradingPower = format(l_dblPaymentTradingPowerIncDayTrade);
            //入金請求額
            l_unit.payClaimAmount = format(l_dblDemandamount);
            //現物株買付余力
            l_unit.equityTradingPower = format(l_dblEquityTradingPower);
            //現物株買付余力<日計り拘束金考慮>
            l_unit.equityTradingPowerDayTrade = format(l_dblEquityTradingPowerIncDayTrade);
            //投信買付余力
            l_unit.mutualTradingPower = format(l_dblOtherTradingPower);
            //その他商品買付余力
            l_unit.otherTradingPower = format(l_dblOtherTradingPower);
            //預り金不足額=ABS(MIN(0,引出可能現金))
            l_unit.accountBalanceShortfall = format(Math.abs(Math.min(0, l_dblActualPaymentBalance)));

            l_units[i] = l_unit;

        }

        //余力推移一覧返却
        return l_units;
    }

    /**
     * (create余力推移明細<信用顧客>)<BR>
     * create余力推移明細処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（資産余力情報画面表示サービス）create余力推移明細<信用顧客>」参照。<BR>
     * <BR>
     * @@param WEB3TPTradingPowerCalcMargin
     * @@return WEB3TPTransitionReferenceUnit
     */
    protected WEB3TPTransitionReferenceUnit[] createTransitionReferenceUnitsMargin(
            WEB3TPTradingPowerCalcMargin l_calcMargin)
    {

        //余力計算条件取得
        WEB3TPCalcCondition l_condition = l_calcMargin.getCalcCondition();

        //結果詳細Params取得
        TpCalcResultMarginDetailParams l_detailMargin = l_calcMargin.getCalcResultDetailMargin();

        //特別立替金実績取得
        double l_dblSpecialDebitAmount = l_condition.getSpecialDebitAmount();

        //余力推移明細一覧生成
        WEB3TPTransitionReferenceUnit[] l_units = new WEB3TPTransitionReferenceUnit[6];

        for(int i = 0; i < 6; i++)
        {
            //一余力推移明細生成
            WEB3TPTransitionReferenceUnit l_unit = new WEB3TPTransitionReferenceUnit();

            //営業日取得
            Date l_bizDate = l_condition.getBizDate(i);

            //預り金残高(指定日)取得
            double l_dblAccountBalance1 = l_calcMargin.getAccountBalance(i);
            //預り金残高(指定日-1)取得
            double l_dblAccountBalance2 = 0d;
            if(i != WEB3TPSpecifiedPointDef.T_0)
            {
                l_dblAccountBalance2 = l_calcMargin.getAccountBalance(i - 1);
            }
            //当日約定済代金(指定日)取得
            double l_dblTodayExecutedAmount1 = l_calcMargin.getTodayExecutedAmount(i);
            //当日約定済代金(指定日-1)取得
            double l_dblTodayExecutedAmount2 = 0d;
            if(i != WEB3TPSpecifiedPointDef.T_0)
            {
                l_dblTodayExecutedAmount2 = l_calcMargin.getTodayExecutedAmount(i - 1);
            }
            //当日未約定代金取得
            double l_dblTodayUnexecutedAmount = l_calcMargin.getTodayUnexecutedAmount(i);
            //日計り拘束金取得
            double l_dblDayTradeRestraint = l_calcMargin.getDayTradeRestraint(i);
            //その他拘束金取得
            double l_dblOtherRestraint = l_calcMargin.getOtherRestraint(i);
            //現金保証金取得
            double l_dblMarginAccountBalance = l_calcMargin.calcMarginAccountBalance(i);
            //代用証券評価額取得
            double l_dblSubstituteSecurityAsset = l_calcMargin.getSubstituteSecurityAsset(i);
            //差入保証金取得
            double l_dblPaidMarginDeposit = l_calcMargin.calcPaidMarginDeposit(i);

            //建玉評価損取得
            double ldblContractAssetLoss = getContractAssetLoss(l_detailMargin, i);
            //建玉評価益取得
            double ldblContractAssetProfit =  getContractAssetProfit(l_detailMargin, i);

            //未決済建玉評価損益取得
            double l_dblContractAssetProfitLoss = l_calcMargin.getContractAssetProfitLoss(i);
            //建玉諸経費取得
            double l_dblContractTotalCost = l_calcMargin.getContractTotalCost(i);
            //見受渡建玉決済損取得
            double l_dblUndeliContractLoss = l_calcMargin.getUndeliContractLoss(i);
            //見受渡建玉決済益取得
            double l_dblUndeliContractProfit = l_calcMargin.getUndeliContractProfit(i);
            //受入保証金取得
            double l_dblReceiptMarginDeposit = l_calcMargin.calcReceiptMarginDeposit(i);
            //未決済建玉代金
            double l_dblContractAmount = l_calcMargin.getContractAmount(i);
            //日計り返済・現引現渡建玉代金
            double l_dblDayRepayContractAmount = l_calcMargin.getDayRepayContractAmount(i);
            //未受渡建玉代金
            double l_dblUndeliContractAmount = l_calcMargin.getUndeliContractAmount(i);
            //必要保証金
            double l_dblMarginDeposit = l_calcMargin.getMarginDeposit(i);
            //日計り返済・現引現渡必要保証金
            double l_dblDayRepayMarginDeposit = getDayRepayMarginDeposit(l_detailMargin, i);
            //未受渡建玉必要保証金
            double l_dblUndeliMarginDeposit = l_calcMargin.getUndeliMarginDeposit(i);
            //現金必要保証金
            double l_dblCashMarginDeposit = l_calcMargin.getCashMarginDeposit(i);
            //日計り返済・現引現渡必要保証金
            double l_dblDayRepayCashMarginDeposit = getDayRepayCashMarginDeposit(l_detailMargin, i);
            //未受渡建玉必要保証金
            double l_dblUndeliCashMarginDeposit = l_calcMargin.getUndeliCashMarginDeposit(i);
            //保証金余力
            double l_dblMarginPower = l_calcMargin.calcMarginPower(i);
            //保証金引出拘束金
            double l_dblMarginDrawDeposit = l_calcMargin.calcMarginDrawDeposit(i);
            //保証金引出余力
            double l_dblMarginDrawPower = l_calcMargin.calcMarginDrawPower(i);
            //引出可能現金
            double l_dblActualPaymentBalance = l_calcMargin.calcActualPaymentBalance(i);
            //預り金請求余力
            double l_dblAccountBalanceDemandPower = l_calcMargin.calcAccountBalanceDemandPower(i);
            //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>
            double l_dblContractAmountDayRepay = l_calcMargin.getContractAmountDayRepay(i);
            //保証金預託率
            double l_dblMarginDepositRate = l_calcMargin.calcMarginDepositRate(i);
            //追証必要保証金
            double l_dblMarginCallDeposit = l_calcMargin.calcMarginCallDeposit(i);
            //追証余力
            double l_dblMarginCallPower = l_calcMargin.calcMarginCallPower(i);
            //保証金維持余力＜保証金率＞
            double l_dblMarginDepositPower = l_calcMargin.calcMarginMaintenancePower(
                    i,
                    l_condition.getMarginDepositRate());
            //保証金維持余力＜保証金維持率＞
            double l_dblMarginMaintenancePower = l_calcMargin.calcMarginMaintenancePower(
                    i,
                    l_condition.getMarginMentenanceRate());
            //保証金維持余力＜法@定保証金維持率＞
            double l_dblLegalMarginDepositPower = l_calcMargin.calcMarginMaintenancePower(
                    i,
                    l_condition.getLegalMarginDepositRate());
            //入金請求額
            double l_dblDemandAmount = l_calcMargin.calcDemandamount(i);
            //信用新規建可能額
            double l_dblMarginTradingPower = l_calcMargin.calcMarginTradingPower(i);
            //信用現引可能額
            double l_dblActualReceiptTradingPower = l_calcMargin.calcActualReceiptTradingPower(i);
            //信用現引可能額<日計り拘束金考慮>取得
            double l_dblActualReceiptTradingPowerIncDayTrade = l_calcMargin.calcActualReceiptTradingPowerIncDayTrade(i);
            //株式買付可能額
            double l_dblEquityTradingPower = l_calcMargin.calcEquityTradingPower(i);
            //株式買付可能額<日計り拘束金考慮>取得
            double l_dblEquityTradingPowerIncDayTrade = l_calcMargin.calcEquityTradingPowerIncDayTrade(i);
            //その他商品買付可能額<日計り拘束金考慮>取得
            double l_dblOtherTradingPower = l_calcMargin.calcOtherTradingPowerIncDayTrade(i);

            //プロパティセット
            l_unit.bizDate = l_bizDate;//日付
            l_unit.accountBalance = format(l_dblAccountBalance1 - l_dblTodayExecutedAmount1);//預り金

            //前日比
            if(i != WEB3TPSpecifiedPointDef.T_0)//T+0でない場合
            {
                l_unit.comparedPreviousDay = format((l_dblAccountBalance1 - l_dblTodayExecutedAmount1)
                        - (l_dblAccountBalance2 - l_dblTodayExecutedAmount2));//前日比                                  
            }

            l_unit.unexecutedAmount = format(l_dblTodayUnexecutedAmount);//発注充当金

            //日計り拘束金
            if(i == WEB3TPSpecifiedPointDef.T_0)//T+0の場合
            {
                //日計り拘束金と特別立替金の大きい方
                l_unit.dayTradeRestraint = format(Math.max(
                        l_dblDayTradeRestraint,
                        l_dblSpecialDebitAmount));
            }
            else
            {
                l_unit.dayTradeRestraint = format(l_dblDayTradeRestraint);
            }

            //保証金預託率=無限大値
            if(l_dblMarginDepositRate == Double.POSITIVE_INFINITY)
            {
                l_unit.marginCollateralRate = null;
            }
            //以外の場合
            else
            {
                l_unit.marginCollateralRate = format(l_dblMarginDepositRate);
            }

            l_unit.otherRestraint = format(l_dblOtherRestraint);//その他拘束金
            l_unit.cashDeposit = format(l_dblMarginAccountBalance);//現金保証金
            l_unit.securityAsset = format(l_dblSubstituteSecurityAsset);//代用証券評価額
            l_unit.guarantyDeposit = format(l_dblPaidMarginDeposit);//差入保証金
            l_unit.contractAssetProfit = format(ldblContractAssetProfit);//建玉評価益
            l_unit.contractAssetLoss = format(ldblContractAssetLoss);//建玉評価損
            l_unit.contractAssetProfitLoss = format(l_dblContractAssetProfitLoss);//建玉評価損益
            l_unit.contractTotalCost = format(l_dblContractTotalCost);//建玉諸経費
            l_unit.undeliContractLoss = format(l_dblUndeliContractLoss);//未受渡建玉決済損
            l_unit.undeliContractProfit = format(l_dblUndeliContractProfit);//未受渡建玉決済益
            l_unit.acceptDeposit = format(l_dblReceiptMarginDeposit);//受入保証金
            l_unit.contractAmount = format(l_dblContractAmount);//建玉代金
            l_unit.dayRepayContractAmount = format(l_dblDayRepayContractAmount);//日計り返済・現引現渡建玉代金
            l_unit.undeliContractAmount = format(l_dblUndeliContractAmount);//未受渡建玉代金
            l_unit.marginDeposit = format(l_dblMarginDeposit);//必要保証金
            l_unit.dayRepayMarginDeposit = format(l_dblDayRepayMarginDeposit);//日計り返済・現引現渡必要保証金
            l_unit.undeliMarginDeposit = format(l_dblUndeliMarginDeposit);//未受渡建玉必要保証金
            l_unit.cashMarginDeposit = format(l_dblCashMarginDeposit);//現金必要保証金
            l_unit.dayRepayCashMarginDeposit = format(l_dblDayRepayCashMarginDeposit);//日計り返済・現引現渡必要保証金
            l_unit.undeliCashMarginDeposit = format(l_dblUndeliCashMarginDeposit);//未受渡建玉必要保証金

            l_unit.depositTradingPower = format(l_dblMarginPower);//保証金余力
            l_unit.depositWithdrawRestraint = format(l_dblMarginDrawDeposit);//保証金引出拘束金
            l_unit.depositWithdrawTradingPower = format(l_dblMarginDrawPower);//保証金引出余力
            l_unit.paymentTradingPower = format(Math.min(
                    l_dblMarginDrawPower,
                    l_dblActualPaymentBalance));//出金余力           
            l_unit.additionalMarginDeposit = format(l_dblMarginCallDeposit);//追証必要保証金
            l_unit.additionalDepositTradingPower = format(l_dblMarginCallPower);//追証余力            
            l_unit.marginDepositPower = format(l_dblMarginDepositPower);//保証金維持余力＜保証金率＞
            l_unit.marginMaintenancePower = format(l_dblMarginMaintenancePower);//保証金維持余力＜保証金維持率＞
            l_unit.legalMarginDepositPower = format(l_dblLegalMarginDepositPower);//保証金維持余力＜法@定保証金維持率＞   
            //預り金不足額=ABS(MIN(0,預り金請求余力))
            l_unit.accountBalanceShortfall = format(Math.abs(Math.min(
                    0,
                    l_dblAccountBalanceDemandPower)));
            l_unit.payClaimAmount = format(l_dblDemandAmount);//入金請求額
            l_unit.marginTradingPower = format(l_dblMarginTradingPower);//信用新規建余力
            l_unit.swapLongTradingPower = format(l_dblActualReceiptTradingPower);//信用現引余力
            l_unit.swapLongTradingPowerDayTrade = format(l_dblActualReceiptTradingPowerIncDayTrade);//信用現引余力<日計り拘束金考慮>            
            l_unit.equityTradingPower = format(l_dblEquityTradingPower);//現物株買付余力
            l_unit.equityTradingPowerDayTrade = format(l_dblEquityTradingPowerIncDayTrade);//現物株買付余力<日計り拘束金考慮>
            l_unit.mutualTradingPower = format(l_dblOtherTradingPower);//投信買付余力
            l_unit.otherTradingPower = format(l_dblOtherTradingPower);//その他商品買付余力

            //一覧に追加
            l_units[i] = l_unit;

        }

        //余力推移一覧返却
        return l_units;
    }

    /**
     * (get取引銘柄)<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（資産余力情報画面表示サービス）get取引銘柄」参照。<BR>
     * <BR>
     * @@param 銘柄Row
     * @@params 発注日
     * @@params 優先順市場リスト（要素の型：WEB3GentradeMarket）
     * @@return Object
     */
    protected Object getTradedProduct(ProductRow l_product, Date l_orderDate, List l_lisMarket)
            throws WEB3BaseException
    {

        //取引銘柄マスタ検索
        List l_lisTradedProduct = null;
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            String l_strWhere = " product_id = ? AND valid_until_biz_date = ? ";
            Object[] l_bindVars =
            {
                    new Long(l_product.getProductId()),
                    WEB3DateUtility.formatDate(l_orderDate, "yyyyMMdd")};
            l_lisTradedProduct = l_qp.doFindAllQuery(
                    TradedProductRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);
        }
        catch(DataNetworkException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getTradedProduct");
        }
        catch(DataQueryException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getTradedProduct");
        }

        //取引銘柄マスタに該当データがある場合
        if(l_lisTradedProduct != null && l_lisTradedProduct.size() != 0)
        {
            //優先市場IDがNULLでない場合
            if(!l_product.getPrimaryMarketIdIsNull())
            {
                for(int i = 0; i < l_lisTradedProduct.size(); i++)
                {
                    //取引銘柄Row
                    TradedProductRow l_tradedProdut = (TradedProductRow)l_lisTradedProduct.get(i);
                    //優先市場ID=取引銘柄．市場IDの場合
                    if(l_product.getPrimaryMarketId() == l_tradedProdut.getMarketId())
                    {
                        //取引銘柄Rowを返却
                        return l_tradedProdut;
                    }
                }
            }

            for(int i = 0; i < l_lisMarket.size(); i++)
            {
                //市場
                WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_lisMarket.get(i);
                for(int j = 0; j < l_lisTradedProduct.size(); j++)
                {
                    //取引銘柄Row
                    TradedProductRow l_tradedProdut = (TradedProductRow)l_lisTradedProduct.get(j);
                    //市場．市場ID = 取引銘柄．市場IDの場合  
                    if(l_market.getMarketId() == l_tradedProdut.getMarketId())
                    {
                        //取引銘柄Rowを返却
                        return l_tradedProdut;
                    }
                }
            }

        }

        //取引銘柄UPDQ検索
        List l_lisTradedProductUpdq = null;
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            String l_strWhere = " product_id = ? AND valid_until_biz_date = ? ";
            Object[] l_bindVars =
            {
                    new Long(l_product.getProductId()),
                    WEB3DateUtility.formatDate(l_orderDate, "yyyyMMdd")};
            l_lisTradedProductUpdq = l_qp.doFindAllQuery(
                    TradedProductUpdqRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);
        }
        catch(DataNetworkException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getTradedProduct");
        }
        catch(DataQueryException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getTradedProduct");
        }

        //該当なし
        if(l_lisTradedProductUpdq == null || l_lisTradedProductUpdq.size() == 0)
        {
            if(DBG)
            {
                log.debug("取引銘柄Updqに該当するレコードがありません。");
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        getClass().getName() + "getTradedProduct");
            }
        }

        //優先市場IDがNULLでない場合
        if(!l_product.getPrimaryMarketIdIsNull())
        {
            for(int i = 0; i < l_lisTradedProductUpdq.size(); i++)
            {
                //取引銘柄Updq
                TradedProductUpdqRow l_tradedProdutUpdqRow = (TradedProductUpdqRow)l_lisTradedProductUpdq.get(i);
                //優先市場ID=取引銘柄UPDQ．市場IDの場合
                if(l_tradedProdutUpdqRow.getMarketId() == l_product.getPrimaryMarketId())
                {
                    //取引銘柄UPDQRowを返却
                    return l_tradedProdutUpdqRow;
                }
            }
        }

        for(int i = 0; i < l_lisMarket.size(); i++)
        {
            //市場
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_lisMarket.get(i);
            for(int j = 0; j < l_lisTradedProductUpdq.size(); j++)
            {
                //取引銘柄Updq
                TradedProductUpdqRow l_tradedProdutUpdq = (TradedProductUpdqRow)l_lisTradedProductUpdq.get(j);
                //市場．市場ID = 取引銘柄．市場ID の場合
                if(l_market.getMarketId() == l_tradedProdutUpdq.getMarketId())
                {
                    //取引銘柄UpdqRowを返却
                    return l_tradedProdutUpdq;
                }
            }
        }

        if(DBG)
        {
            log.debug("取引銘柄、取引銘柄UPDQに該当するレコードがありません。");
        }
        throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                getClass().getName() + "getTradedProduct");

    }

    /**
     * (get株式取引銘柄)<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（資産余力情報画面表示サービス）get株式取引銘柄」参照。<BR>
     * <BR>
     * @@param l_lngTradedProductID　@取引銘柄ID
     * @@params l_orderDate　@発注日
     * @@return Object 株式取引銘柄Row もしくは 株式取引銘柄UpdqRow
     */
    protected Object getEqtypeTradedProduct(long l_lngTradedProductID, Date l_orderDate)
            throws WEB3BaseException
    {

        //株式取引銘柄マスタ検索
        List l_lisEqtypeTradedProduct = null;
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            String l_strWhere = " traded_product_id = ? AND valid_until_biz_date = ? ";
            Object[] l_bindVars =
            {new Long(l_lngTradedProductID), WEB3DateUtility.formatDate(l_orderDate, "yyyyMMdd")};
            l_lisEqtypeTradedProduct = l_qp.doFindAllQuery(
                    EqtypeTradedProductRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);
        }
        catch(DataQueryException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getEqtypeTradedProduct");
        }
        catch(DataNetworkException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getEqtypeTradedProduct");
        }

        //株式取引銘柄マスタに該当データが存在する場合
        if(l_lisEqtypeTradedProduct != null && l_lisEqtypeTradedProduct.size() != 0)
        {
            //株式取引銘柄Rowを返却
            return (EqtypeTradedProductRow)l_lisEqtypeTradedProduct.get(0);
        }

        //取引銘柄UPDQ検索
        EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow = null;
        try
        {
            l_eqtypeTradedProductUpdqRow = EqtypeTradedProductUpdqDao.findRowByPk(
                    l_lngTradedProductID,
                    WEB3DateUtility.formatDate(l_orderDate, "yyyyMMdd"));
        }
        catch(DataFindException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getEqtypeTradedProduct");
        }
        catch(DataNetworkException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getEqtypeTradedProduct");
        }
        catch(DataQueryException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getEqtypeTradedProduct");
        }

        if(l_eqtypeTradedProductUpdqRow != null)
        {
            //株式取引銘柄UpqqRowを返却
            return l_eqtypeTradedProductUpdqRow;
        }

        log.error("株式取引銘柄、株式取引銘柄Updqに該当するレコードがありません。");
        throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + ".getEqtypeTradedProduct");

    }

    /**
     * (get優先順市場リスト)<BR>
     * <BR>
     * 指定された証券会社の優先順市場リストを取得する。<BR>
     * <BR>
     *[優先順]<BR>
     *　@※市場コードが下記の順
     *　@東京　@大阪　@名古屋　@JASDAC　@NNM　@福岡　@札幌<BR>
     * <BR>
     * @@param l_instituion 証券会社
     * @@return List 優先順市場リスト(要素の型：WEB3GentradeMarket)
     */
    protected List getMarketList(Institution l_institution) throws WEB3BaseException
    {
        //優先順マーケットコードリスト
        String[] l_orderdMarketCodeList = WEB3TPOrderdMarketCodeListDef.ORDERD_MARKET_CODE_LIST;

        //優先順マーケットリスト
        List l_lisMarket = new ArrayList();

        for(int i = 0; i < l_orderdMarketCodeList.length; i++)
        {
            //市場
            WEB3GentradeMarket l_market;
            try
            {
                l_market = new WEB3GentradeMarket(l_institution, l_orderdMarketCodeList[i]);
            }
            catch(DataFindException e)
            {
                if(DBG)
                {
                    log.debug("該当する市場が存在しません。");
                }
                continue;
            }
            catch(DataQueryException e)
            {
                throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        e.getMessage(),
                        getClass().getName() + "getMarketList",
                        e);
            }
            catch(DataNetworkException e)
            {
                throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        e.getMessage(),
                        getClass().getName() + "getMarketList",
                        e);
            }

            if(l_market == null)
            {
                if(DBG)
                {
                    log.debug("該当する市場が存在しません。");
                }
                continue;
            }

            //優先順市場リストに追加
            l_lisMarket.add(l_market);

        }

        if(l_lisMarket.size() == 0)
        {
            log.error("該当する市場が一件もありません。");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "getMarketList");
        }

        //優先順市場リストを返却
        return l_lisMarket;
    }

    /**
     * (get値洗い状態区分) <BR>
     * 現在の値洗い状態区分を返却する。<BR>
     * <BR>
     * １）プロセス管理テーブルより、「大引け値洗い終了」レコードを取得する。<BR>
     * <BR>
     * 　@[検索対象テーブル]<BR>
     * 　@　@プロセス管理テーブル<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@プロセスID：”0002”<BR>
     * 　@　@証券会社コード：引数.証券会社コード<BR>
     * 　@　@部店コード：引数.部店コード　@<BR>
     * <BR>
     * ２）プロセス管理テーブルより、「大引け速報値洗い終了」レコードを取得する。<BR>
     * <BR>
     * 　@[検索対象テーブル]<BR>
     * 　@　@プロセス管理テーブル<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@プロセスID：”0004”<BR>
     * 　@　@証券会社コード：引数.証券会社コード<BR>
     * 　@　@部店コード：引数.部店コード　@<BR>
     * <BR>
     * ３）取得したレコードより、値洗い状態区分を返却する。<BR>
     * <BR>
     * 　@[大引け値洗い終了の場合]<BR>
     * 　@（「大引け値洗い終了」レコード != null かつ 「大引け値洗い終了」レコード.処理区分 == 1：処理済）<BR>
     * <BR>
     * 　@　@返却値：2（：値洗い終了）<BR>
     * <BR>
     * 　@[大引け速報値洗い終了の場合]<BR>
     * 　@（「大引け速報値洗い終了」レコード != null かつ 「大引け速報値洗い終了」レコード.処理区分 == 1：処理済）<BR>
     * <BR>
     * 　@　@返却値：1（：値洗い中）<BR>
     * <BR>
     * 　@[以外の場合]<BR>
     * <BR>
     * 　@　@返却値：null<BR>
     * <BR>
     * @@param l_strInstCode 証券会社コード
     * @@param l_strBranCode 部店コード
     * @@return String 値洗い状態区分
     */
    protected String getMarkToMarketStateDiv(String l_strInstCode, String l_strBranCode)
    {
        try
        {
            //プロセスID
            String l_strProcessId = null;

            //大引け値洗い
            l_strProcessId = "0002";
            ProcessManagementParams l_params1 = (ProcessManagementParams)ProcessManagementDao.findRowByProcessIdInstitutionCodeBranchCode(
                    l_strProcessId,
                    l_strInstCode,
                    l_strBranCode);

            //大引け速報値洗い
            l_strProcessId = "0004";
            ProcessManagementParams l_params2 = (ProcessManagementParams)ProcessManagementDao.findRowByProcessIdInstitutionCodeBranchCode(
                    l_strProcessId,
                    l_strInstCode,
                    l_strBranCode);

            //大引け値洗いのレコードが存在するかつ、処理区分=1:処理済み場合
            if(l_params1 != null && l_params1.getStatus().equals("1"))
            {
                return WEB3TPMarkToMarketStateDivDef.FIXED;
            }
            //大引け速報値洗いのレコードが存在するかつ、処理区分=1:処理済み場合
            else if(l_params2 != null && l_params2.getStatus().equals("1"))
            {
                return WEB3TPMarkToMarketStateDivDef.PRELIMINARY;
            }
            //以外の場合
            else
            {
                return null;
            }
        }
        catch(DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    getClass().getName() + "getProcessManagementParams(String, String)",
                    dfe);
        }
        catch(DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "getProcessManagementParams(String, String)",
                    de);
        }
    }

    /**
     * （get発注分代用証券評価額） <BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「発注分代用証券評価額」を返却する。 <BR>
     * <BR>
     * ［返却値］ <BR>
     * 余力計算結果詳細Params <信用顧客>.get発注分代用証券評価額（T+n） <BR>
     * 
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getUnexecSubstiSecurityAsset(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //発注分代用証券評価額
        double l_dblUnexecSubstiSecurityAsset;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //発注分代用証券評価額( T + 0 )を取得する。
                l_dblUnexecSubstiSecurityAsset = l_detailMargin.getUnexecSubstiSecurityAsset0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //発注分代用証券評価額( T + 1 )を取得する。
                l_dblUnexecSubstiSecurityAsset = l_detailMargin.getUnexecSubstiSecurityAsset1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //発注分代用証券評価額( T + 2 )を取得する。
                l_dblUnexecSubstiSecurityAsset = l_detailMargin.getUnexecSubstiSecurityAsset2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //発注分代用証券評価額( T + 3 )を取得する。
                l_dblUnexecSubstiSecurityAsset = l_detailMargin.getUnexecSubstiSecurityAsset3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //発注分代用証券評価額( T + 4 )を取得する。
                l_dblUnexecSubstiSecurityAsset = l_detailMargin.getUnexecSubstiSecurityAsset4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //発注分代用証券評価額( T + 5 )を取得する。
                l_dblUnexecSubstiSecurityAsset = l_detailMargin.getUnexecSubstiSecurityAsset5();
                break;
            default:
                l_dblUnexecSubstiSecurityAsset = 0d;
        }

        //取得した発注分代用証券評価額を返却する。
        return l_dblUnexecSubstiSecurityAsset;
    }

    /**
     * （get発注分建玉代金）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「発注分建玉代金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * 余力計算結果詳細Params<信用顧客>.get発注分建玉代金（T+n）<BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getUnexecContractAmount(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //発注分建玉代金
        double l_dblUnexecContractAmount;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //発注分建玉代金( T + 0 )を取得する。
                l_dblUnexecContractAmount = l_detailMargin.getUnexecContractAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //発注分建玉代金( T + 1 )を取得する。
                l_dblUnexecContractAmount = l_detailMargin.getUnexecContractAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //発注分建玉代金( T + 2 )を取得する。
                l_dblUnexecContractAmount = l_detailMargin.getUnexecContractAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //発注分建玉代金( T + 3 )を取得する。
                l_dblUnexecContractAmount = l_detailMargin.getUnexecContractAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //発注分建玉代金( T + 4 )を取得する。
                l_dblUnexecContractAmount = l_detailMargin.getUnexecContractAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //発注分建玉代金( T + 5 )を取得する。
                l_dblUnexecContractAmount = l_detailMargin.getUnexecContractAmount5();
                break;
            default:
                l_dblUnexecContractAmount = 0d;
        }

        //取得した発注分建玉代金を返却する。
        return l_dblUnexecContractAmount;
    }

    /**
     * （get発注分必要保証金）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「発注分必要保証金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * 余力計算結果詳細Params<信用顧客>.get発注分必要保証金（T+n）<BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getUnexecMarginDeposit(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //発注分必要保証金
        double l_dblUnexecMarginDeposit;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //発注分必要保証金( T + 0 )を取得する。
                l_dblUnexecMarginDeposit = l_detailMargin.getUnexecMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //発注分必要保証金( T + 1 )を取得する。
                l_dblUnexecMarginDeposit = l_detailMargin.getUnexecMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //発注分必要保証金( T + 2 )を取得する。
                l_dblUnexecMarginDeposit = l_detailMargin.getUnexecMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //発注分必要保証金( T + 3 )を取得する。
                l_dblUnexecMarginDeposit = l_detailMargin.getUnexecMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //発注分必要保証金( T + 4 )を取得する。
                l_dblUnexecMarginDeposit = l_detailMargin.getUnexecMarginDeposit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //発注分必要保証金( T + 5 )を取得する。
                l_dblUnexecMarginDeposit = l_detailMargin.getUnexecMarginDeposit5();
                break;
            default:
                l_dblUnexecMarginDeposit = 0d;
        }

        //取得した発注分必要保証金を返却する。
        return l_dblUnexecMarginDeposit;
    }

    /**
     * （get発注分現金必要保証金）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「発注分現金必要保証金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * 余力計算結果詳細Params<信用顧客>.get発注分現金必要保証金（T+n）<BR>
     * <BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getUnexecCashMarginDeposit(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //発注分現金必要保証金
        double l_dblUnexecCashMarginDeposit;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //発注分必要保証金( T + 0 )を取得する。
                l_dblUnexecCashMarginDeposit = l_detailMargin.getUnexecCashMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //発注分必要保証金( T + 1 )を取得する。
                l_dblUnexecCashMarginDeposit = l_detailMargin.getUnexecCashMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //発注分必要保証金( T + 2 )を取得する。
                l_dblUnexecCashMarginDeposit = l_detailMargin.getUnexecCashMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //発注分必要保証金( T + 3 )を取得する。
                l_dblUnexecCashMarginDeposit = l_detailMargin.getUnexecCashMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //発注分必要保証金( T + 4 )を取得する。
                l_dblUnexecCashMarginDeposit = l_detailMargin.getUnexecCashMarginDeposit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //発注分必要保証金( T + 5 )を取得する。
                l_dblUnexecCashMarginDeposit = l_detailMargin.getUnexecCashMarginDeposit5();
                break;
            default:
                l_dblUnexecCashMarginDeposit = 0d;
        }

        //取得した発注分現金必要保証金を返却する。
        return l_dblUnexecCashMarginDeposit;
    }

    /**
     * （get日計り返済・現引現渡必要保証金）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「日計り返済・現引現渡必要保証金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * 余力計算結果詳細Params<信用顧客>.get日計り返済・現引現渡必要保証金（T+n）<BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getDayRepayMarginDeposit(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //日計り返済・現引現渡必要保証金
        double l_dblDayRepayMarginDeposit;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //日計り返済・現引現渡必要保証金( T + 0 )を取得する。
                l_dblDayRepayMarginDeposit = l_detailMargin.getDayRepayMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //日計り返済・現引現渡必要保証金( T + 1 )を取得する。
                l_dblDayRepayMarginDeposit = l_detailMargin.getDayRepayMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //日計り返済・現引現渡必要保証金( T + 2 )を取得する。
                l_dblDayRepayMarginDeposit = l_detailMargin.getDayRepayMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //日計り返済・現引現渡必要保証金( T + 3 )を取得する。
                l_dblDayRepayMarginDeposit = l_detailMargin.getDayRepayMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //日計り返済・現引現渡必要保証金( T + 4 )を取得する。
                l_dblDayRepayMarginDeposit = l_detailMargin.getDayRepayMarginDeposit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //日計り返済・現引現渡必要保証金( T + 5 )を取得する。
                l_dblDayRepayMarginDeposit = l_detailMargin.getDayRepayMarginDeposit5();
                break;
            default:
                l_dblDayRepayMarginDeposit = 0d;
        }

        //取得した日計り返済・現引現渡必要保証金を返却する。
        return l_dblDayRepayMarginDeposit;
    }

    /**
     * （get日計り返済・現引現渡現金必要保証金）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「日計り返済・現引現渡現金必要保証金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * 余力計算結果詳細Params<信用顧客>.get日計り返済・現引現渡現金必要保証金（T+n）<BR>
     * <BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getDayRepayCashMarginDeposit(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //日計り返済・現引現渡現金必要保証金
        double l_dblDayRepayCashMarginDeposit;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //日計り返済・現引現渡現金必要保証金( T + 0 )を取得する。
                l_dblDayRepayCashMarginDeposit = l_detailMargin.getDayRepayCashMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //日計り返済・現引現渡現金必要保証金( T + 1 )を取得する。
                l_dblDayRepayCashMarginDeposit = l_detailMargin.getDayRepayCashMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //日計り返済・現引現渡現金必要保証金( T + 2 )を取得する。
                l_dblDayRepayCashMarginDeposit = l_detailMargin.getDayRepayCashMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //日計り返済・現引現渡現金必要保証金( T + 3 )を取得する。
                l_dblDayRepayCashMarginDeposit = l_detailMargin.getDayRepayCashMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //日計り返済・現引現渡現金必要保証金( T + 4 )を取得する。
                l_dblDayRepayCashMarginDeposit = l_detailMargin.getDayRepayCashMarginDeposit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //日計り返済・現引現渡現金必要保証金( T + 5 )を取得する。
                l_dblDayRepayCashMarginDeposit = l_detailMargin.getDayRepayCashMarginDeposit5();
                break;
            default:
                l_dblDayRepayCashMarginDeposit = 0d;
        }

        //取得した日計り返済・現引現渡必要保証金を返却する。
        return l_dblDayRepayCashMarginDeposit;
    }

    /**
     * （get未決済建玉評価損）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「未決済建玉評価損」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * 余力計算結果詳細Params<信用顧客>.get未決済建玉評価損（T+n）<BR>
     * <BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getContractAssetLoss(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //未決済建玉評価損
        double l_dblContractAssetLoss;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //未決済建玉評価損( T + 0 )を取得する。
                l_dblContractAssetLoss = l_detailMargin.getContractAssetLoss();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //未決済建玉評価損( T + 1 )を取得する。
                l_dblContractAssetLoss = l_detailMargin.getContractAssetLoss1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //未決済建玉評価損( T + 2 )を取得する。
                l_dblContractAssetLoss = l_detailMargin.getContractAssetLoss2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //未決済建玉評価損( T + 3 )を取得する。
                l_dblContractAssetLoss = l_detailMargin.getContractAssetLoss3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //未決済建玉評価損( T + 4 )を取得する。
                l_dblContractAssetLoss = l_detailMargin.getContractAssetLoss4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //未決済建玉評価損( T + 5 )を取得する。
                l_dblContractAssetLoss = l_detailMargin.getContractAssetLoss5();
                break;
            default:
                l_dblContractAssetLoss = 0d;
        }

        //取得した未決済建玉評価損を返却する。
        return l_dblContractAssetLoss;
    }

    /**
     * （get未決済建玉評価益）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「未決済建玉評価益」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * 余力計算結果詳細Params<信用顧客>.get未決済建玉評価益（T+n）<BR>
     * <BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getContractAssetProfit(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //未決済建玉評価益
        double l_dblContractAssetProfit;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //未決済建玉評価益( T + 0 )を取得する。
                l_dblContractAssetProfit = l_detailMargin.getContractAssetProfit();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //未決済建玉評価益( T + 1 )を取得する。
                l_dblContractAssetProfit = l_detailMargin.getContractAssetProfit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //未決済建玉評価益( T + 2 )を取得する。
                l_dblContractAssetProfit = l_detailMargin.getContractAssetProfit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //未決済建玉評価益( T + 3 )を取得する。
                l_dblContractAssetProfit = l_detailMargin.getContractAssetProfit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //未決済建玉評価益( T + 4 )を取得する。
                l_dblContractAssetProfit = l_detailMargin.getContractAssetProfit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //未決済建玉評価益( T + 5 )を取得する。
                l_dblContractAssetProfit = l_detailMargin.getContractAssetProfit5();
                break;
            default:
                l_dblContractAssetProfit = 0d;
        }

        //取得した未決済建玉評価益を返却する。
        return l_dblContractAssetProfit;
    }
    
    /**
     * （get建玉決済損<指定日>）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「建玉決済損<指定日>」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * 余力計算結果詳細Params<信用顧客>.get建玉決済損<指定日>（T+n）<BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getContractLossDesignate(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //建玉決済損<指定日>
        double l_dblContractLossDesignate;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_1:
                //建玉決済損<指定日>( T + 1 )を取得する。
                l_dblContractLossDesignate = l_detailMargin.getContractLossDesignate1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //建玉決済損<指定日>( T + 2 )を取得する。
                l_dblContractLossDesignate = l_detailMargin.getContractLossDesignate2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //建玉決済損<指定日>( T + 3 )を取得する。
                l_dblContractLossDesignate = l_detailMargin.getContractLossDesignate3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //建玉決済損<指定日>( T + 4 )を取得する。
                l_dblContractLossDesignate = l_detailMargin.getContractLossDesignate4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //建玉決済損<指定日>( T + 5 )を取得する。
                l_dblContractLossDesignate = l_detailMargin.getContractLossDesignate5();
                break;
            default:
                l_dblContractLossDesignate = 0d;
        }

        //取得した建玉決済損<指定日>を返却する。
        return l_dblContractLossDesignate;
    }

    /**
     * （get建玉決済益<指定日>）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「建玉決済益<指定日>」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * 余力計算結果詳細Params<信用顧客>.get建玉決済益<指定日>（T+n）<BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getContractProfitDesignate(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //建玉決済益<指定日>
        double l_dblContractProfitDesignate;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_1:
                //建玉決済益<指定日>( T + 1 )を取得する。
                l_dblContractProfitDesignate = l_detailMargin.getContractProfitDesignate1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //建玉決済益<指定日>( T + 2 )を取得する。
                l_dblContractProfitDesignate = l_detailMargin.getContractProfitDesignate2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //建玉決済益<指定日>( T + 3 )を取得する。
                l_dblContractProfitDesignate = l_detailMargin.getContractProfitDesignate3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //建玉決済益<指定日>( T + 4 )を取得する。
                l_dblContractProfitDesignate = l_detailMargin.getContractProfitDesignate4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //建玉決済益<指定日>( T + 5 )を取得する。
                l_dblContractProfitDesignate = l_detailMargin.getContractProfitDesignate5();
                break;
            default:
                l_dblContractProfitDesignate = 0d;
        }

        //取得した建玉決済益<指定日>を返却する。
        return l_dblContractProfitDesignate;
    }

    /**
     * （get出金額<指定日>）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「出金額<指定日>」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * 余力計算結果詳細Params<信用顧客>.get出金額<指定日>（T+n）<BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getPaymentAmountDesignate(
            TpCalcResultEquityDetailParams l_detailEquity,
            int l_intSpecifiedPoint)
    {
        //出金額<指定日>
        double l_dblPaymentAmountDesignate;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //出金額<指定日>( T + 0 )を取得する。
                l_dblPaymentAmountDesignate = l_detailEquity.getPaymentAmountDesignate0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //出金額<指定日>( T + 1 )を取得する。
                l_dblPaymentAmountDesignate = l_detailEquity.getPaymentAmountDesignate1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //出金額<指定日>( T + 2 )を取得する。
                l_dblPaymentAmountDesignate = l_detailEquity.getPaymentAmountDesignate2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //出金額<指定日>( T + 3 )を取得する。
                l_dblPaymentAmountDesignate = l_detailEquity.getPaymentAmountDesignate3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //出金額<指定日>( T + 4 )を取得する。
                l_dblPaymentAmountDesignate = l_detailEquity.getPaymentAmountDesignate4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //出金額<指定日>( T + 5 )を取得する。
                l_dblPaymentAmountDesignate = l_detailEquity.getPaymentAmountDesignate5();
                break;
            default:
                l_dblPaymentAmountDesignate = 0d;
        }

        //取得した出金額<指定日>を返却する。
        return l_dblPaymentAmountDesignate;
    }

    /**
     * （get出金額<指定日>）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「出金額<指定日>」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * 余力計算結果詳細Params<信用顧客>.get出金額<指定日>（T+n）<BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getPaymentAmountDesignate(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //出金額<指定日>
        double l_dblPaymentAmountDesignate;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //出金額<指定日>( T + 0 )を取得する。
                l_dblPaymentAmountDesignate = l_detailMargin.getPaymentAmountDesignate0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //出金額<指定日>( T + 1 )を取得する。
                l_dblPaymentAmountDesignate = l_detailMargin.getPaymentAmountDesignate1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //出金額<指定日>( T + 2 )を取得する。
                l_dblPaymentAmountDesignate = l_detailMargin.getPaymentAmountDesignate2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //出金額<指定日>( T + 3 )を取得する。
                l_dblPaymentAmountDesignate = l_detailMargin.getPaymentAmountDesignate3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //出金額<指定日>( T + 4 )を取得する。
                l_dblPaymentAmountDesignate = l_detailMargin.getPaymentAmountDesignate4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //出金額<指定日>( T + 5 )を取得する。
                l_dblPaymentAmountDesignate = l_detailMargin.getPaymentAmountDesignate5();
                break;
            default:
                l_dblPaymentAmountDesignate = 0d;
        }

        //取得した出金額<指定日>を返却する。
        return l_dblPaymentAmountDesignate;
    }

    /**
     * （create資産評価額履歴画面）<BR>
     * <BR>
     * 資産評価額履歴画面の表示処理を行う。<BR> 
     * <BR>
     * ※シーケンス図<BR> 
     * 「（資産余力情報画面表示サービス）create資産評価額履歴画面」参照<BR> 
     * @@param l_request リクエストデータ
     * @@return WEB3TPAssetHistoryResponse
     */
    public WEB3TPAssetHistoryResponse createAssertHistory(WEB3TPAssetHistoryRequest l_reqest)
            throws WEB3BaseException
    {
        final String METHOD_NAME = "createAssertHistory(WEB3TPAssetHistoryRequest)";
        log.entering(METHOD_NAME);
        //1.1get補助口座( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        //1.2getMainAccount( )
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(
                    l_subAccount.getAccountId());
        }
        catch(NotFoundException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        //1.3is信用口座開設(弁済区分 : String)
        //信用客かどうかをチェックする。 

        //［引数］ 
        //弁済区分： ”指定なし” 
        boolean l_blnIsMarginAccountEstablished = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        //1.4ArrayList( )
        //空のリストを生成する。 
        ArrayList l_arrayList = new ArrayList();
        //1.5create資産評価額履歴明細（当日分）(補助口座, boolean)
        //当日分の資産評価額履歴明細を生成する。 

        //［引数］ 
        //補助口座： get補助口座()の戻り値 
        //is信用客： is信用口座開設()の戻り値
        WEB3TPAssetHistoryUnit l_tpAssetHistoryUnit = this.createAssertHistoryPerDay(
                l_subAccount,
                l_blnIsMarginAccountEstablished);
        //1.6add(arg0 : Object)
        //リストに当日分の資産評価額履歴明細を追加する。 

        //［引数］ 
        //arg0： create資産評価額履歴明細（当日分）()の戻り値
        l_arrayList.add(l_tpAssetHistoryUnit);
        //1.7getDefaultProcessor( )クエリプロセッサを取得する。 

        //1.8doFindAllQuery(Rowタイプ : RowType, Where : String, orderBy : String, Condition :
        //String, リスト : Object[])
        //以下の条件で、余力資産評価額履歴テーブルからレコードを取得する。 
        //（前日以前のデータを取得する。） 

        //［条件］ 
        //口座ID = 補助口座.getAccountId()の戻り値 

        //［引数］ 
        //Rowタイプ： 余力資産評価額履歴Row.TYPE 
        //Where： "account_id = ?" 
        //orderBy： "calc_date desc" 
        //Condition： null 
        //リスト： 以下の要素の配列 
        //   補助口座.getAccountId()の戻り値 
        String l_strWhere = "account_id = ?";
        String l_strSortBy = "calc_date desc";
        Object[] l_objValue =
        {new Long(l_subAccount.getAccountId())};
        List l_lstTpAssertHistory = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lstTpAssertHistory = l_processor.doFindAllQuery(
                    TpAssetHistoryRow.TYPE,
                    l_strWhere,
                    l_strSortBy,
                    null,
                    l_objValue);
        }
        catch(DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch(DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch(IllegalStateException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        //1.9取得したレコード毎にLoop処理
        int l_intCount = 0;
        if(l_lstTpAssertHistory != null)
        {
            l_intCount = l_lstTpAssertHistory.size();
        }
        for(int i = 0; i < l_intCount; i++)
        {
            //1.9.1資産評価額履歴明細( 
            WEB3TPAssetHistoryUnit l_historyUnit = new WEB3TPAssetHistoryUnit();
            //1.9.2プロパティセット
            //(*2) 以下のとおりに、プロパティをセットする。
            TpAssetHistoryRow l_assertHistoryRow = (TpAssetHistoryRow)l_lstTpAssertHistory.get(i);
            //日付： 余力資産評価額履歴Params.日付
            l_historyUnit.bizDate = l_assertHistoryRow.getCalcDate();

            //預り金評価額： 余力資産評価額履歴Params.預り金残高
            double l_dblAccountBalance = l_assertHistoryRow.getAccountBalance();
            l_historyUnit.accountBalance = WEB3StringTypeUtility.formatNumber(l_dblAccountBalance);
            //株式評価額： 余力資産評価額履歴Params.株式評価額
            double l_dblEquityAssetDelivered = l_assertHistoryRow.getEquityAssetDelivered();
            l_historyUnit.equityAsset = WEB3StringTypeUtility.formatNumber(l_dblEquityAssetDelivered);
            //株式ミニ投資評価額： 余力資産評価額履歴Params.株式ミニ投資評価額
            double l_dblMiniStockAssetDelivered = l_assertHistoryRow.getMiniStockAssetDelivered();
            l_historyUnit.mstkAsset = WEB3StringTypeUtility.formatNumber(l_dblMiniStockAssetDelivered);
            //累積投資評価額： 余力資産評価額履歴Params.累積投資評価額
            double l_dblRuitoAssetDelivered = l_assertHistoryRow.getRuitoAssetDelivered();
            l_historyUnit.ruitoAsset = WEB3StringTypeUtility.formatNumber(l_dblRuitoAssetDelivered);
            //投資信託評価額： 余力資産評価額履歴Params.投資信託評価額
            double l_dblMutualFundAssetDelivered = l_assertHistoryRow.getMutualFundAssetDelivered();
            l_historyUnit.mutualAsset = WEB3StringTypeUtility.formatNumber(l_dblMutualFundAssetDelivered);
            //債券評価額： 余力資産評価額履歴Params.債券評価額
            double l_dblBondAssetDelivered = l_assertHistoryRow.getBondAssetDelivered();
            l_historyUnit.bondAsset = WEB3StringTypeUtility.formatNumber(l_dblBondAssetDelivered);
            //合計評価額： 預り金評価額 + 株式評価額 + 株式ミニ投資評価額           
            //                    + 累積投資評価額 + 投資信託評価額 + 債券評価額
            l_historyUnit.totalAsset = WEB3StringTypeUtility.formatNumber(l_dblAccountBalance
                    + l_dblEquityAssetDelivered
                    + l_dblMiniStockAssetDelivered
                    + l_dblRuitoAssetDelivered
                    + l_dblMutualFundAssetDelivered
                    + l_dblBondAssetDelivered);

            //前日比： null
            l_historyUnit.comparedPreviousDay = null;
            //1.9.3add(arg0 : Object)
            //リストに資産評価額履歴明細を追加する。 

            //［引数］ 
            //arg0： 資産評価額履歴明細オブジェクト 
            l_arrayList.add(l_historyUnit);
        }

        //1.10 toArray( )   
        WEB3TPAssetHistoryUnit[] l_tpAssetHistoryUnits = new WEB3TPAssetHistoryUnit[l_arrayList.size()];
        l_arrayList.toArray(l_tpAssetHistoryUnits);
        //1.11配列の各要素についてLoop処理
        //1.11.1(*4) 前日比項目の値を算出する。

        //資産評価額履歴明細［index].前日比 =資産評価額履歴明細［index].合計評価額 - 資産評価額履歴明細[index+1].合計評価額
        //※ index == 資産評価額履歴明細[].length - 1 の場合は、0 をセットする。
        for(int i = 0; i < l_tpAssetHistoryUnits.length; i++)
        {
            WEB3TPAssetHistoryUnit l_unit = l_tpAssetHistoryUnits[i];
            if(i == (l_tpAssetHistoryUnits.length - 1))
            {
                l_unit.comparedPreviousDay = "0";
                continue;
            }
            double l_dblTotalAsset = Double.parseDouble(l_unit.totalAsset);
            double l_dblPreTotalAsset = Double.parseDouble(l_tpAssetHistoryUnits[i + 1].totalAsset);
            l_unit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblTotalAsset
                    - l_dblPreTotalAsset);
        }

        //1.12createResponse( )
        WEB3TPAssetHistoryResponse l_response = (WEB3TPAssetHistoryResponse)l_reqest.createResponse();
        //1.12.1プロパティセット
        //(*5) 以下のとおりに、プロパティにセットする。

        //資産評価額履歴一覧： 資産評価額履歴明細の配列
        l_response.assetHistoryList = l_tpAssetHistoryUnits;
        log.exiting(METHOD_NAME);
        return l_response;
    }

    /**
     * （reate資産評価額履歴明細（当日分））<BR>
     * <BR>
     * 当日分の資産評価額履歴明細を生成する。 <BR> 
     * <BR>
     * ※シーケンス図<BR> 
     * 「（資産余力情報画面表示サービス）create資産評価額履歴明細（当日分）」参照 <BR> 
     * @@param l_subAccount  - 補助口座
     * @@param l_blnIsMargin  - is信用客
     * @@return WEB3TPAssetHistoryUnit
     */
    public WEB3TPAssetHistoryUnit createAssertHistoryPerDay(
            WEB3GentradeSubAccount l_subAccount,
            boolean l_blnIsMargin) throws WEB3BaseException
    {
        final String METHOD_NAME = "createAssertHistoryPerDay(WEB3GentradeSubAccount,boolean)";
        log.entering(METHOD_NAME);
        WEB3TPTradingPowerService l_tradingPowerService = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblAccountBalance = 0;
        double l_dblTodayExecutedAmount = 0;
        double l_dblEquityAssetExecuted = 0;
        double l_dblRuitoAssetExecuted = 0;
        double l_dblMiniStockAssetExecuted = 0;
        double l_dblMutualFundAssetExecuted = 0;
        double l_dblBondAssetExecuted = 0;
        WEB3TPCalcCondition l_calcCondition = null;
        //1.1(*1)引数.is信用客 == false （現物客）の場合
        if(!l_blnIsMargin)
        {

            //1.1.1get資産余力情報<現物顧客>(補助口座)
            //資産余力情報<現物顧客>オブジェクトを取得する。 

            //［引数］ 
            //補助口座： 引数.補助口座 
            WEB3TPTradingPowerCalcEquity l_tradingPowerCalcEquity = l_tradingPowerService.getTradingPowerCalcEquity(l_subAccount);
            //1.1.2get預り金残高(int)
            //T+5の預り金残高を取得する。 

            //［引数］ 
            //指定日： 5 
            l_dblAccountBalance = l_tradingPowerCalcEquity.getAccountBalance(5);
            //1.1.3get当日約定済代金(int)
            //T+5の当日約定済代金を取得する。 

            //［引数］ 
            //指定日： 0
            l_dblTodayExecutedAmount = l_tradingPowerCalcEquity.getTodayExecutedAmount(0);
            //1.1.4get余力計算結果詳細Params<現物顧客>( )
            TpCalcResultEquityDetailParams l_calcResultEquityDetailParams = l_tradingPowerCalcEquity.getCalcResultDetailEquity();
            //1.1.5get株式評価額(約残)
            l_dblEquityAssetExecuted = l_calcResultEquityDetailParams.getEquityAssetExecuted();
            //1.1.6get株式ミニ投資評価額(約残)
            l_dblMiniStockAssetExecuted = l_calcResultEquityDetailParams.getMiniStockAssetExecuted();
            //1.1.7 get累積投資評価額(約残)
            l_dblRuitoAssetExecuted = l_calcResultEquityDetailParams.getRuitoAssetExecuted();
            //1.1.8get投資信託評価額(約残)
            l_dblMutualFundAssetExecuted = l_calcResultEquityDetailParams.getMutualFundAssetExecuted();
            //1.1.9get債券評価額(約残)
            l_dblBondAssetExecuted = l_calcResultEquityDetailParams.getBondAssetExecuted();
            //1.1.10get余力計算条件
            l_calcCondition = l_tradingPowerCalcEquity.getCalcCondition();
        }
        else
        {
            //1.2引数.is信用客 == true （信用客）の場合
            //1.2.1get資産余力情報<信用顧客>
            //資産余力情報<信用顧客>オブジェクトを取得する。 

            //［引数］ 
            //補助口座： 補助口座オブジェクト 
            WEB3TPTradingPowerCalcMargin l_tradingPowerCalcMargin = l_tradingPowerService.getTradingPowerCalcMargin(l_subAccount);
            //1.2.2get預り金残高(int)
            //T+5の預り金残高を取得する。 

            //［引数］ 
            //指定日： 5 
            l_dblAccountBalance = l_tradingPowerCalcMargin.getAccountBalance(5);
            //1.2.3get当日約定済代金(int)
            //T+5の当日約定済代金を取得する。 

            //［引数］ 
            //指定日： 0 
            l_dblTodayExecutedAmount = l_tradingPowerCalcMargin.getTodayExecutedAmount(0);
            //1.2.4get余力計算結果詳細Params<信用顧客>
            TpCalcResultMarginDetailParams l_marginDetailParams = l_tradingPowerCalcMargin.getCalcResultDetailMargin();
            //1.2.5get株式評価額(約残)
            l_dblEquityAssetExecuted = l_marginDetailParams.getEquityAssetExecuted();
            //1.2.6get株式ミニ投資評価額(約残)
            l_dblMiniStockAssetExecuted = l_marginDetailParams.getMiniStockAssetExecuted();
            //1.2.7get累積投資評価額(約残)
            l_dblRuitoAssetExecuted = l_marginDetailParams.getRuitoAssetExecuted();
            //1.2.8get投資信託評価額(約残)
            l_dblMutualFundAssetExecuted = l_marginDetailParams.getMutualFundAssetExecuted();
            //1.2.9get債券評価額(約残)
            l_dblBondAssetExecuted = l_marginDetailParams.getBondAssetExecuted();
            //1.2.10get余力計算条件( )
            l_calcCondition = l_tradingPowerCalcMargin.getCalcCondition();

        }

        //1.3 get営業日(int)
        Date l_datBizDate = l_calcCondition.getBizDate(0);
        //1.4資産評価額履歴明細( )
        WEB3TPAssetHistoryUnit l_historyUnit = new WEB3TPAssetHistoryUnit();
        //1.5 (*3)プロパティセット
        //(*3) 以下のとおりに、プロパティをセットする。

        //日付： get営業日()の戻り値
        l_historyUnit.bizDate = l_datBizDate;
        //預り金評価額： get預り金残高()の戻り値 - get当日約定済代金()の戻り値
        double l_dblAccountBalanceResult = l_dblAccountBalance - l_dblTodayExecutedAmount;
        l_historyUnit.accountBalance = WEB3StringTypeUtility.formatNumber(l_dblAccountBalanceResult);
        //株式評価額： get株式評価額（約残）()の戻り値
        l_historyUnit.equityAsset = WEB3StringTypeUtility.formatNumber(l_dblEquityAssetExecuted);
        //株式ミニ投資評価額： get株式ミニ投資評価額（約残）()の戻り値
        l_historyUnit.mstkAsset = WEB3StringTypeUtility.formatNumber(l_dblMiniStockAssetExecuted);
        //累積投資評価額： get累積投資評価額（約残）()の戻り値
        l_historyUnit.ruitoAsset = WEB3StringTypeUtility.formatNumber(l_dblRuitoAssetExecuted);
        //投資信託評価額： get投資信託評価額（約残）()の戻り値
        l_historyUnit.mutualAsset = WEB3StringTypeUtility.formatNumber(l_dblMutualFundAssetExecuted);
        //債券評価額： get債券評価額（約残）()の戻り値
        l_historyUnit.bondAsset = WEB3StringTypeUtility.formatNumber(l_dblBondAssetExecuted);
        //合計評価額： 預り金評価額 + 株式評価額 + 株式ミニ投資評価額
        //                    + 累積投資評価額 + 投資信託評価額 + 債券評価額
        l_historyUnit.totalAsset = WEB3StringTypeUtility.formatNumber(l_dblAccountBalanceResult
                + l_dblEquityAssetExecuted
                + l_dblMiniStockAssetExecuted
                + l_dblRuitoAssetExecuted
                + l_dblMutualFundAssetExecuted
                + l_dblBondAssetExecuted);
        //前日比： null
        l_historyUnit.comparedPreviousDay = null;

        //※該当顧客が現物客の場合は(*1)の分岐で取得したものを、
        //   信用客の場合は(*2)の分岐で取得したものをセットする。
        log.exiting(METHOD_NAME);
        return l_historyUnit;
    }

    /**
     * (get顧客余力条件Params)<BR>
     * 顧客余力条件テーブルより、以下の条件でレコードを検索し結果を返す。<BR>
     * <BR>
     * 条件：<BR>
     * 口座ID = 引数のアカウントID<BR>
     * <BR>
     * ※検索結果が0件の場合、エラーを返却する。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_01037<BR>
     * <BR>
     * @@param l_accountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@return TradingpowerCalcConditionParams
     * @@throws WEB3BaseException
     */
    private TradingpowerCalcConditionParams getTradingpowerCalcConditionParams(Long l_accountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradingpowerCalcConditionParams(Long)";
        log.entering(STR_METHOD_NAME);

        List l_lisTradingpowerCalcConditionRows = null;

        try
        {
            //条件：
            //口座ID = 引数のアカウントID
            l_lisTradingpowerCalcConditionRows =
                TradingpowerCalcConditionDao.findRowsByAccountId(l_accountId.longValue());
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //※検索結果が0件の場合、エラーを返却する。
        if (l_lisTradingpowerCalcConditionRows.isEmpty())
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            (TradingpowerCalcConditionParams)l_lisTradingpowerCalcConditionRows.get(0);

        log.exiting(STR_METHOD_NAME);
        return l_tradingpowerCalcConditionParams;
    }

    /**
     * （create不足金発生画面）<BR>
     * <BR>
     * ※シーケンス図<BR>
     * 「（資産余力情報画面表示サービス）create不足金発生画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 不足金発生状況画面表示リクエスト<BR>
     * @@return WEB3TPShortfallGenerationResponse
     * @@throws WEB3BaseException
     */
    protected WEB3TPShortfallGenerationResponse createShortfallGenerationScreen(
        WEB3TPShortfallGenerationRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createShortfallGenerationScreen(WEB3TPShortfallGenerationRequest)";
        log.entering(STR_METHOD_NAME);

        //createResponse()
        //不足金発生画面表示リクエストに対応するレスポンスオブジェクトを生成する。
        WEB3TPShortfallGenerationResponse l_response = (WEB3TPShortfallGenerationResponse)l_request.createResponse();

        //get補助口座()
        //補助口座オブジェクトを取得する。
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //get顧客()
        //顧客オブジェクトを取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_subAccount.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //getAccountId()
        //取得した顧客のアカウントIDを取得する。
        long l_lngAccountId = l_mainAccount.getAccountId();

        //get顧客余力条件Params(Long)
        //[引数]
        //・getAccountId()で取得したアカウントID
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            this.getTradingpowerCalcConditionParams(new Long(l_lngAccountId));

        //get不足金発生状況(顧客)
        //[引数]
        //get顧客()の戻り値
        WEB3TPPaymentRequisitionManageService l_paymentRequisitionManageService =
            (WEB3TPPaymentRequisitionManageService)Services.getService(WEB3TPPaymentRequisitionManageService.class);
        String l_strLackCashOccurSituation =
            l_paymentRequisitionManageService.getLackCashOccurSituation(l_mainAccount);

        //get不足金発生情報(顧客)
        //[引数]
        //get顧客()の戻り値
        WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
            l_paymentRequisitionManageService.getShortfallGenerationInfo(l_mainAccount);

        //不足金発生情報()
        //不足金発生情報を生成する。
        WEB3TPShortfallGenerationInfo l_newShortfallGenerationInfoInfo = new WEB3TPShortfallGenerationInfo();

        //プロパティセット
        //不足金発生情報のプロパティを以下のようにセットする。
        //・期日(T+0)　@=　@get不足金発生情報()の戻り値.期日(T+0)
        l_newShortfallGenerationInfoInfo.closeDate0 =
            WEB3DateUtility.toDay(l_shortfallGenerationInfo.closeDate0);
        //・期日(T+1)　@=　@get不足金発生情報()の戻り値.期日(T+1)
        l_newShortfallGenerationInfoInfo.closeDate1 =
            WEB3DateUtility.toDay(l_shortfallGenerationInfo.closeDate1);
        //・期日(T+2)　@=　@get不足金発生情報()の戻り値.期日(T+2)
        l_newShortfallGenerationInfoInfo.closeDate2 =
            WEB3DateUtility.toDay(l_shortfallGenerationInfo.closeDate2);
        //・期日(T+3)　@=　@get不足金発生情報()の戻り値.期日(T+3)
        l_newShortfallGenerationInfoInfo.closeDate3 =
            WEB3DateUtility.toDay(l_shortfallGenerationInfo.closeDate3);
        //・期日(T+4)　@=　@get不足金発生情報()の戻り値.期日(T+4)
        l_newShortfallGenerationInfoInfo.closeDate4 =
            WEB3DateUtility.toDay(l_shortfallGenerationInfo.closeDate4);
        //・期日(T+5)　@=　@get不足金発生情報()の戻り値.期日(T+5)
        l_newShortfallGenerationInfoInfo.closeDate5 =
            WEB3DateUtility.toDay(l_shortfallGenerationInfo.closeDate5);

        //・入金必要額(T+0)　@=　@get不足金発生情報()の戻り値.入金必要額(T+0)
        l_newShortfallGenerationInfoInfo.requiredPayAmt0 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.requiredPayAmt0);
        //・入金必要額(T+1)　@=　@get不足金発生情報()の戻り値.入金必要額(T+1)
        l_newShortfallGenerationInfoInfo.requiredPayAmt1 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.requiredPayAmt1);
        //・入金必要額(T+2)　@=　@get不足金発生情報()の戻り値.入金必要額(T+2)
        l_newShortfallGenerationInfoInfo.requiredPayAmt2 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.requiredPayAmt2);
        //・入金必要額(T+3)　@=　@get不足金発生情報()の戻り値.入金必要額(T+3)
        l_newShortfallGenerationInfoInfo.requiredPayAmt3 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.requiredPayAmt3);
        //・入金必要額(T+4)　@=　@get不足金発生情報()の戻り値.入金必要額(T+4)
        l_newShortfallGenerationInfoInfo.requiredPayAmt4 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.requiredPayAmt4);
        //・入金必要額(T+5)　@=　@get不足金発生情報()の戻り値.入金必要額(T+5)
        l_newShortfallGenerationInfoInfo.requiredPayAmt5 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.requiredPayAmt5);

        //・精算額(T+0)　@=　@get不足金発生情報()の戻り値.不足金発生情報.精算額(T+0)
        l_newShortfallGenerationInfoInfo.adjustedAmt0 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.adjustedAmt0);
        //・精算額(T+1)　@=　@get不足金発生情報()の戻り値.不足金発生情報.精算額(T+1)
        l_newShortfallGenerationInfoInfo.adjustedAmt1 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.adjustedAmt1);

        //・日計り拘束金(T+0)　@=　@get不足金発生情報()の戻り値.不足金発生情報.日計り拘束金(T+0)
        l_newShortfallGenerationInfoInfo.dayTradeRestraint0 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.dayTradeRestraint0);
        //・日計り拘束金(T+1)　@=　@get不足金発生情報()の戻り値.不足金発生情報.日計り拘束金(T+1)
        l_newShortfallGenerationInfoInfo.dayTradeRestraint1 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.dayTradeRestraint1);

        //・保証金からの振替額(T+0)　@=　@get不足金発生情報()の戻り値.不足金発生情報.保証金からの振替額(T+0)
        l_newShortfallGenerationInfoInfo.transferFromMarginDeposit0 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.transferFromMarginDeposit0);
        //・保証金からの振替額(T+1)　@=　@get不足金発生情報()の戻り値.不足金発生情報.保証金からの振替額(T+1)
        l_newShortfallGenerationInfoInfo.transferFromMarginDeposit1 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.transferFromMarginDeposit1);

        //プロパティセット
        //不足金発生画面表示レスポンスのプロパティを以下のようにセットする。
        //・不足金発生状況　@=　@get不足金発生状況()の戻り値
        l_response.shortfallGenerationStateDiv = l_strLackCashOccurSituation;
        //・保証金自動振替後判定フラグ　@=　@get不足金発生情報()の戻り値.保証金自動振替後判定フラグ
        l_response.autoTransferAfterJudgeFlag = l_shortfallGenerationInfo.depositAutoTransferDivFlag;
        //・入金遅延発生フラグ　@=　@get顧客余力条件Params()の戻り値.取引停止区分
        String l_strTradingStop = l_tradingpowerCalcConditionParams.getTradingStop();
        if (l_strTradingStop.equals(WEB3TradingStopDef.TRADING_ENABLE))
        {
            l_response.payDelayGenerationFlag = false;
        }
        else if (l_strTradingStop.equals(WEB3TradingStopDef.TRADING_STOP))
        {
            l_response.payDelayGenerationFlag = true;
        }
        //・不足金発生情報　@=　@プロパティセットした不足金発生情報
        l_response.shortfallGenerationInfo = l_newShortfallGenerationInfoInfo;

        log.exiting(STR_METHOD_NAME);

        //不足金発生画面表示レスポンスを返却
        return l_response;
    }

    /**
     * （create追証発生画面）<BR>
     * <BR>
     * ※シーケンス図<BR>
     * 「（資産余力情報画面表示サービス）create追証発生画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 追証発生画面表示リクエスト<BR>
     * @@return WEB3TPAdditionalGenerationResponse
     * @@throws WEB3BaseException
     */
    protected WEB3TPAdditionalGenerationResponse createAdditionalGenerationScreen(
        WEB3TPAdditionalGenerationRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAdditionalGenerationScreen(WEB3TPAdditionalGenerationRequest)";
        log.entering(STR_METHOD_NAME);

        //createResponse()
        //追証発生画面表示リクエストに対応するレスポンスオブジェクトを生成する。
        WEB3TPAdditionalGenerationResponse l_response = (WEB3TPAdditionalGenerationResponse)l_request.createResponse();

        //get補助口座()
        //補助口座オブジェクトを取得する。
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //get顧客()
        //顧客オブジェクトを取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_subAccount.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //getAccountId()
        //取得した顧客のアカウントIDを取得する。
        long l_lngAccountId = l_mainAccount.getAccountId();

        //get顧客余力条件Params(Long)
        //[引数]
        //・getAccountId()で取得したアカウントID
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            this.getTradingpowerCalcConditionParams(new Long(l_lngAccountId));

        //get追証発生状況(顧客, boolean)
        //[引数]
        //get顧客()の戻り値
        //当初追証発生考慮フラグ：　@true
        WEB3TPPaymentRequisitionManageService l_paymentRequisitionManageService =
            (WEB3TPPaymentRequisitionManageService)Services.getService(WEB3TPPaymentRequisitionManageService.class);
        String l_strAdditionalMarginSituation =
            l_paymentRequisitionManageService.getAdditionalMarginSituation(l_mainAccount, true);

        //get追証発生情報(顧客, boolean)
        //[引数]
        //get顧客()の戻り値
        //当初追証発生考慮フラグ：　@true
        WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
            l_paymentRequisitionManageService.getAdddepositGenerationInfo(l_mainAccount, true);

        //プロパティセット
        //追証発生画面表示レスポンスのプロパティを以下のようにセットする。
        //・追証発生状況　@=　@get追証発生状況()の戻り値
        l_response.additionalGenerationStateDiv = l_strAdditionalMarginSituation;
        //・保証金自動振替後判定フラグ　@=　@get追証発生情報().保証金自動振替後判定フラグ
        l_response.autoTransferAfterJudgeFlag = l_adddepositGenerationInfo.getDepositAutoTransferDivFlag();
        //・追証未入金発生フラグ　@=　@get顧客余力条件Params().追証未入金区分
        String l_srtAdditionalDepositStop = l_tradingpowerCalcConditionParams.getAdditionalDepositStop();
        if (l_srtAdditionalDepositStop.equals(WEB3AdditionalDepositStopDef.ADDITIONAL_DEPOSIT_NOT_STOP))
        {
            l_response.additionalNonPayAmtFlag = false;
        }
        else if (l_srtAdditionalDepositStop.equals(WEB3AdditionalDepositStopDef.ADDITIONAL_DEPOSIT_STOP))
        {
            l_response.additionalNonPayAmtFlag = true;
        }

        //get追証発生情報().追証情報を取得する。
        WEB3TPAdddepositInfo l_adddepositInfo = l_adddepositGenerationInfo.getAdddepositInfo();

        //(*)分岐フロ－
        //get追証発生情報().追証情報　@==　@NULL の場合
        if (l_adddepositInfo == null)
        {
            //プロパティセット
            //追証発生画面表示レスポンスのプロパティを以下のようにセットする。
            //・第一水準追証情報　@=　@null
            l_response.firstAdditionalInfo = null;
            //・第二水準追証情報　@=　@null
            l_response.secondAdditionalInfo = null;

            //追証発生画面表示レスポンスを返却
            return l_response;
        }

        //(*)分岐フロ－
        //get追証発生情報().追証情報 が第一水準追証情報のインスタンスである場合
        if (l_adddepositInfo instanceof WEB3TPFirstAdddepositInfo)
        {
            //第一水準追証情報()
            //第一水準追証情報を生成する。
            WEB3TPFirstAdditionalInfo l_firstAdditionalInfo = new WEB3TPFirstAdditionalInfo();

            WEB3TPFirstAdddepositInfo l_newFirstAdddepositInfo = (WEB3TPFirstAdddepositInfo)l_adddepositInfo;

            //プロパティセット
            //第一水準追証情報のプロパティを以下のようにセットする。
            //・第一水準追証情報.経過日数 = get追証発生情報().第一水準追証情報.経過日数
            l_firstAdditionalInfo.firstDepositPassDay = String.valueOf(l_newFirstAdddepositInfo.firstDepositPassDay);
            //・第一水準追証情報.有効経過日数 = get追証発生情報().第一水準追証情報.有効経過日数
            l_firstAdditionalInfo.firstDepositPassDayValid =
                String.valueOf(l_newFirstAdddepositInfo.firstDepositPassDayValid);
            //・第一水準追証情報.発生日 = get追証発生情報().第一水準追証情報.発生日
            l_firstAdditionalInfo.firstDepositOccurredDate =
                WEB3DateUtility.toDay(l_newFirstAdddepositInfo.firstDepositOccurredDate);
            //・第一水準追証情報.保証金率 = get追証発生情報().第一水準追証情報.保証金率
            l_firstAdditionalInfo.firstMarginDepositRate =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstMarginDepositRate);
            //・第一水準追証情報.保証金維持率 = get追証発生情報().第一水準追証情報.保証金維持率
            l_firstAdditionalInfo.firstDepositRate =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstDepositRate);
            //・第一水準追証情報.追証金額 = get追証発生情報().第一水準追証情報.追証金額
            l_firstAdditionalInfo.firstDepositAmount =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstDepositAmount);
            //・第一水準追証情報.追証決済必要額 = get追証発生情報().第一水準追証情報.追証決済必要額
            l_firstAdditionalInfo.firstSettlement =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstSettlement);
            //・第一水準追証情報.保証金増減 = get追証発生情報().第一水準追証情報.保証金増減
            l_firstAdditionalInfo.firstMarginDepositInDe =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstMarginDepositInDe);
            //・第一水準追証情報.保証金増減(見込金額) = get追証発生情報().第一水準追証情報.保証金増減(見込金額)
            l_firstAdditionalInfo.firstMarginDepositInDeExpect =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstMarginDepositInDeExpect);
            //・第一水準追証情報.決済済建玉 = get追証発生情報().第一水準追証情報.決済済建玉
            l_firstAdditionalInfo.firstSettledContract =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstSettledContract);
            //・第一水準追証情報.未解消金額 = get追証発生情報().第一水準追証情報.未解消金額
            l_firstAdditionalInfo.firstUncancelAmt =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstUncancelAmt);
            //・第一水準追証情報.未解消決済必要額 = get追証発生情報().第一水準追証情報.未解消決済必要額
            l_firstAdditionalInfo.firstUncancelSettleRequiredAmt =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstUncancelSettleRequiredAmt);

            //プロパティセット
            //追証発生画面表示レスポンスのプロパティを以下のようにセットする。
            //・第一水準追証情報　@=　@プロパティセットした第一水準追証情報
            l_response.firstAdditionalInfo = l_firstAdditionalInfo;
            //・第二水準追証情報　@=　@null
            l_response.secondAdditionalInfo = null;
        }
        //(*)分岐フロ－
        //get追証発生情報().追証情報 が第二水準追証情報のインスタンスである場合
        else if (l_adddepositInfo instanceof WEB3TPSecondAdddepositInfo)
        {
            //第二水準追証情報()
            //第二水準追証情報を生成する。
            WEB3TPSecondAdditionalInfo l_secondAdditionalInfo = new WEB3TPSecondAdditionalInfo();

            WEB3TPSecondAdddepositInfo l_newSecondAdddepositInfo = (WEB3TPSecondAdddepositInfo)l_adddepositInfo;

            //プロパティセット
            //第二水準追証情報のプロパティを以下のようにセットする。
            //・第二水準追証情報.期日(請求2) = get追証発生情報().第二水準追証情報.期日(請求2)
            String l_strSecondCloseDate2 =
                WEB3DateUtility.formatDate(
                    l_newSecondAdddepositInfo.secondCloseDate2,
                    WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
            l_secondAdditionalInfo.secondCloseDate2 =
                WEB3DateUtility.getDate(l_strSecondCloseDate2,
                    WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
            //・第二水準追証情報.期日(請求1) = get追証発生情報().第二水準追証情報.期日(請求1)
            String l_strSecondCloseDate1 =
                WEB3DateUtility.formatDate(
                    l_newSecondAdddepositInfo.secondCloseDate1,
                    WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
            l_secondAdditionalInfo.secondCloseDate1 =
                WEB3DateUtility.getDate(l_strSecondCloseDate1,
                    WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
            //・第二水準追証情報.期日(請求見込) = get追証発生情報().第二水準追証情報.期日(請求見込)
            String l_strSecondCloseDateExpect =
                WEB3DateUtility.formatDate(
                    l_newSecondAdddepositInfo.secondCloseDateExpect,
                    WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
            l_secondAdditionalInfo.secondCloseDateExpect =
                WEB3DateUtility.getDate(l_strSecondCloseDateExpect,
                    WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
            //・第二水準追証情報.発生日(請求2) = get追証発生情報().第二水準追証情報.発生日(請求2)
            l_secondAdditionalInfo.secondDepositOccurredDate2 =
                WEB3DateUtility.toDay(l_newSecondAdddepositInfo.secondDepositOccurredDate2);
            //・第二水準追証情報.発生日(請求1) = get追証発生情報().第二水準追証情報.発生日(請求1)
            l_secondAdditionalInfo.secondDepositOccurredDate1 =
                WEB3DateUtility.toDay(l_newSecondAdddepositInfo.secondDepositOccurredDate1);
            //・第二水準追証情報.発生日(請求見込) = get追証発生情報().第二水準追証情報.発生日(請求見込)
            l_secondAdditionalInfo.secondDepositOccurredDateExpect =
                WEB3DateUtility.toDay(l_newSecondAdddepositInfo.secondDepositOccurredDateExpect);
            //・第二水準追証情報.保証金維持率 = get追証発生情報().第二水準追証情報.保証金維持率
            l_secondAdditionalInfo.secondDepositRate =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondDepositRate);
            //・第二水準追証情報.保証金戻し維持率 = get追証発生情報().第二水準追証情報.保証金戻し維持率
            l_secondAdditionalInfo.secondDepositBackRate =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondDepositBackRate);
            //・第二水準追証情報.保証金率(請求2) = get追証発生情報().第二水準追証情報.保証金率(請求2)
            l_secondAdditionalInfo.secondMarginDepositRate2 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondMarginDepositRate2);
            //・第二水準追証情報.保証金率(請求1) = get追証発生情報().第二水準追証情報.保証金率(請求1)
            l_secondAdditionalInfo.secondMarginDepositRate1 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondMarginDepositRate1);
            //・第二水準追証情報.保証金率(請求見込) = get追証発生情報().第二水準追証情報.保証金率(請求見込)
            l_secondAdditionalInfo.secondMarginDepositRateExpect =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondMarginDepositRateExpect);
            //・第二水準追証情報.追証金額(未入金) = get追証発生情報().第二水準追証情報.追証金額(未入金)
            l_secondAdditionalInfo.secondDepositNonPay =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondDepositNonPay);
            //・第二水準追証情報.追証金額(請求2) = get追証発生情報().第二水準追証情報.追証金額(請求2)
            l_secondAdditionalInfo.secondDeposit2 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondDeposit2);
            //・第二水準追証情報.追証金額(請求1) = get追証発生情報().第二水準追証情報.追証金額(請求1)
            l_secondAdditionalInfo.secondDeposit1 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondDeposit1);
            //・第二水準追証情報.追証決済必要額(未入金) = get追証発生情報().第二水準追証情報.追証決済必要額(未入金)
            l_secondAdditionalInfo.secondSettlementNonPay =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondSettlementNonPay);
            //・第二水準追証情報.追証決済必要額(請求2) = get追証発生情報().第二水準追証情報.追証決済必要額(請求2)
            l_secondAdditionalInfo.secondSettlement2 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondSettlement2);
            //・第二水準追証情報.追証決済必要額(請求1) = get追証発生情報().第二水準追証情報.追証決済必要額(請求1)
            l_secondAdditionalInfo.secondSettlement1 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondSettlement1);
            //・第二水準追証情報.保証金増減 = get追証発生情報().第二水準追証情報.保証金増減
            l_secondAdditionalInfo.secondMarginDepositInDe =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondMarginDepositInDe);
            //・第二水準追証情報.保証金増減(見込金額) = get追証発生情報().第二水準追証情報.保証金増減(見込金額)
            l_secondAdditionalInfo.secondMarginDepositInDeExpect =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondMarginDepositInDeExpect);
            //・第二水準追証情報.決済済建玉 = get追証発生情報().第二水準追証情報.決済済建玉
            l_secondAdditionalInfo.secondSettledContract =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondSettledContract);
            //・第二水準追証情報.未解消金額(未入金) = get追証発生情報().第二水準追証情報.未解消金額(未入金)
            l_secondAdditionalInfo.secondUncancelAmtNonPay =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelAmtNonPay);
            //・第二水準追証情報.未解消金額(請求2) = get追証発生情報().第二水準追証情報.未解消金額(請求2)
            l_secondAdditionalInfo.secondUncancelAmt2 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelAmt2);
            //・第二水準追証情報.未解消金額(請求1) = get追証発生情報().第二水準追証情報.未解消金額(請求1)
            l_secondAdditionalInfo.secondUncancelAmt1 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelAmt1);
            //・第二水準追証情報.未解消金額(請求見込) = get追証発生情報().第二水準追証情報.未解消金額(請求見込)
            l_secondAdditionalInfo.secondUncancelAmtExpect =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelAmtExpect);
            //・第二水準追証情報.未解消決済必要額(未入金) = get追証発生情報().第二水準追証情報.未解消決済必要額(未入金)
            l_secondAdditionalInfo.secondUncancelSettleRequiredAmtNonPay =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay);
            //・第二水準追証情報.未解消決済必要額(請求2) = get追証発生情報().第二水準追証情報.未解消決済必要額(請求2)
            l_secondAdditionalInfo.secondUncancelSettleRequiredAmt2 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelSettleRequiredAmt2);
            //・第二水準追証情報.未解消決済必要額(請求1) = get追証発生情報().第二水準追証情報.未解消決済必要額(請求1)
            l_secondAdditionalInfo.secondUncancelSettleRequiredAmt1 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelSettleRequiredAmt1);
            //・第二水準追証情報.未解消決済必要額(請求見込) = get追証発生情報().第二水準追証情報.未解消決済必要(請求見込)
            l_secondAdditionalInfo.secondUncancelSettleRequiredAmtExpect =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelSettleRequiredAmtExpect);

            //プロパティセット
            //追証発生画面表示レスポンスのプロパティを以下のようにセットする。
            //・第一水準追証情報　@=　@null
            l_response.firstAdditionalInfo = null;
            //・第二水準追証情報　@=　@プロパティセットした第二水準追証情報
            l_response.secondAdditionalInfo = l_secondAdditionalInfo;
        }

        log.exiting(STR_METHOD_NAME);

        //追証発生画面表示レスポンスを返却
        return l_response;
    }

}@
