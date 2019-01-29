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
filename	WEB3MarginBalanceReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引残高照会サービスImpl(WEB3MarginBalanceReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
                 : 2005/03/31 劉(FLJ)　@性能チューニング修正
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
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
import webbroker3.equity.WEB3MarginBalanceReferenceComparator;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginCloseStatus;
import webbroker3.equity.define.WEB3EquitySettlementStateDef;
import webbroker3.equity.define.WEB3MarginContractTypeDef;
import webbroker3.equity.message.WEB3MarginBalanceReferenceDetailUnit;
import webbroker3.equity.message.WEB3MarginBalanceReferenceRequest;
import webbroker3.equity.message.WEB3MarginBalanceReferenceResponse;
import webbroker3.equity.message.WEB3MarginBalanceReferenceTotalRequest;
import webbroker3.equity.message.WEB3MarginBalanceReferenceTotalResponse;
import webbroker3.equity.message.WEB3MarginProductCodeNameUnit;
import webbroker3.equity.message.WEB3MarginSortKey;
import webbroker3.equity.service.delegate.WEB3MarginBalanceReferenceService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;


/**
 * （信用取引残高照会サービスImpl）。<BR>
 * <BR>
 * 信用取引残高照会サービス実装クラス<BR>
 */
public class WEB3MarginBalanceReferenceServiceImpl
extends WEB3MarginClientRequestService
implements WEB3MarginBalanceReferenceService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginBalanceReferenceServiceImpl.class);

    /**
     * @@roseuid 4206CDD500A8<BR>
     */
    public WEB3MarginBalanceReferenceServiceImpl()
    {

    }

    /**
     * 信用取引残高照会処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○信用取引残高照会残高合計リクエストの場合<BR>
     * 　@this.get残高合計()メソッドをコールする。<BR>
     * <BR>
     * ○信用取引残高照会リクエストの場合<BR>
     * 　@this.get残高照会()メソッドをコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41BFCFC30159<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "WEB3MarginBalanceReferenceServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("パラメータ.リクエストデータがnullです。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3MarginBalanceReferenceRequest)
        {
            l_response =
                this.getBalanceReference((WEB3MarginBalanceReferenceRequest)l_request);
        }
        else if (l_request instanceof WEB3MarginBalanceReferenceTotalRequest)
        {
            l_response =
                this.getBalanceTotal((WEB3MarginBalanceReferenceTotalRequest)l_request);
        }
        else
        {
            log.error("パラメータ.リクエストデータの型が不正です。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get残高照会)<BR>
     * <BR>
     * 信用取引残高照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(信用取引残高照会サービス)get残高照会」<BR>
     * 参照<BR>
     * @@param l_request - (リクエストデータ) 信用取引残高照会リクエストオブジェクト<BR>
     * @@return WEB3MarginBalanceReferenceResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BFCFBA00CD<BR>
     */
    protected WEB3MarginBalanceReferenceResponse getBalanceReference(WEB3MarginBalanceReferenceRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3MarginBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        // validate()
        l_request.validate();

        // validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // get補助口座()
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount)this.getSubAccount();

        // 証券会社オブジェクトを取得
        WEB3GentradeInstitution l_institution =
            (WEB3GentradeInstitution)l_subAccount.getInstitution();

        // 銘柄コード指定の場合
        String l_strProductCode = l_request.productCode;
        if (l_strProductCode != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();

            try {
                // 銘柄コードの取得チェックを行う
                l_productManager.getProduct(l_institution, l_strProductCode);
            } catch (NotFoundException l_ex) {
                // 取得できなかった場合は、例外をスロー
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage());
            }
        }

        // レスポンスデータを生成
        WEB3MarginBalanceReferenceResponse l_response =
            (WEB3MarginBalanceReferenceResponse)l_request.createResponse();

        // create銘柄・市場プルダウン()
        //性能チューニング修正 劉(FLJ)
        //銘柄コード名称の一覧作成機@能削除
        //市場コード=取引中取扱可能市場
        //delete begin
//>>>>>
//        this.createProductMarketPullDown(l_subAccount, l_response);
//
//        if (l_response.productCodeNames == null)
//        {
//            return l_response;
//        }
//>>>>>
        String[] l_strHandlingMarketCodes =
            WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(
            l_subAccount.getWeb3GenBranch(), WEB3GentradeRepaymentDivDef.DEFAULT, 0D);

        l_response.marketList = l_strHandlingMarketCodes;
        //delete end


        // create検索条件文字列
        String l_strMarketCode = l_request.marketCode;
        String l_strQueryString = this.createQueryString(l_strProductCode, l_strMarketCode);

        // create検索条件データコンテナ
        String[] l_strQueryDataContainer =
            this.createQueryContainer(l_institution, l_strProductCode, l_strMarketCode);

        // create残高照会明細一覧
        WEB3MarginBalanceReferenceDetailUnit[] l_balanceReferenceUnits =
            this.createBalanceReferenceDetailUnitList(l_subAccount, l_request.settlementState, l_strQueryString, l_strQueryDataContainer);

        // create残高照会明細一覧の戻り値 == nullの場合
        if (l_balanceReferenceUnits == null || l_balanceReferenceUnits.length == 0)
        {
            l_response.balanceReference = null;
            return l_response;
        }

        // sort残高照会明細
        this.sortBalanceReferenceDetailUnit(l_balanceReferenceUnits, l_request.sortKeys);

        // 表示対象行の残高照会明細の抽出
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_balanceReferenceUnits, l_intPageIndex, l_intPageSize);

        l_balanceReferenceUnits =
            (WEB3MarginBalanceReferenceDetailUnit[])l_pageIndexInfo.getArrayReturned(WEB3MarginBalanceReferenceDetailUnit.class);

        // set取引可能フラグ
        this.setTradingFlag(l_subAccount, l_balanceReferenceUnits);

        // レスポンスデータにプロパティをセットする。
        // 残高照会明細
        l_response.balanceReference = l_balanceReferenceUnits;
        // 表示ページ番号
        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
        // 総ページ数
        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
        // 総レコード数
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());

        return l_response;
    }

    /**
     * (get残高合計)<BR>
     * <BR>
     * 信用取引残高合計処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(信用取引残高照会サービス)get残高合計」<BR>
     * 参照<BR>
     * @@param l_request - (リクエストデータ) 信用取引残高照会残高合計リクエストオブジェクト<BR>
     * @@return WEB3MarginBalanceReferenceTotalResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BFCFBA00EC<BR>
     */
    protected WEB3MarginBalanceReferenceTotalResponse getBalanceTotal(WEB3MarginBalanceReferenceTotalRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceTotal(WEB3MarginBalanceReferenceTotalRequest) ";
        log.entering(STR_METHOD_NAME);

        // validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        // create残高照会明細一覧()
        WEB3MarginBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnits =
            this.createBalanceReferenceDetailUnitList(l_subAccount, null, null, null);

        // レスポンスデータを生成
        WEB3MarginBalanceReferenceTotalResponse l_response =
            (WEB3MarginBalanceReferenceTotalResponse)l_request.createResponse();

        // 該当データなしの場合
        if (l_balanceReferenceDetailUnits == null || l_balanceReferenceDetailUnits.length == 0)
        {
            // レスポンスに初期値をセットして終了
			l_response.buyTotalPrice = "0";
			l_response.sellTotalPrice = "0";
			l_response.capitalGainTotalPrice = "0";
			l_response.normalAccountTotalPrice = "0";
			l_response.totalPrice = "0";
			l_response.buyTotalAssetProfitLoss = "0";
			l_response.sellTotalAssetProfitLoss = "0";
			l_response.capitalGainTotalAssetProfitLoss = "0";
			l_response.normalAccountTotalAssetProfitLoss = "0";
			l_response.totalAssetProfitLoss = "0";
			l_response.buyTotalAssetProfitLossCost = "0";
			l_response.sellTotalAssetProfitLossCost = "0";
			l_response.capitalGainTotalAssetProfitLossCost = "0";
			l_response.normalAccountTotalAssetProfitLossCost = "0";
			l_response.totalAssetProfitLossCost = "0";

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        double l_dblContractAmountLong = 0.0D;
        double l_dblContractAmountShort = 0.0D;
        double l_dblContractAmountNormal = 0.0D;
        double l_dblContractAmountSpecial = 0.0D;
        double l_dblContractAmountTotal = 0.0D;
        double l_dblProfitLossLong = 0.0D;
        double l_dblProfitLossShort = 0.0D;
        double l_dblProfitLossNormal = 0.0D;
        double l_dblProfitLossSpecial = 0.0D;
        double l_dblProfitLossTotal = 0.0D;
        double l_dblProfitLossCostLong = 0.0D;
        double l_dblProfitLossCostShort = 0.0D;
        double l_dblProfitLossCostNormal = 0.0D;
        double l_dblProfitLossCostSpecial = 0.0D;
        double l_dblProfitLossCostTotal = 0.0D;

        // create残高照会明細一覧()の戻り値の要素数分Loop処理
        for (int i =0; i < l_balanceReferenceDetailUnits.length; i++)
        {
            // 建代金、評価損益、評価損益（諸経費考慮）を取得
            double l_dblContractAmount = Double.parseDouble(l_balanceReferenceDetailUnits[i].contractExecPrice);
            double l_dblProfitLoss = 0.0D;
            if (l_balanceReferenceDetailUnits[i].appraisalProfitLoss != null)
            {
                l_dblProfitLoss = Double.parseDouble(l_balanceReferenceDetailUnits[i].appraisalProfitLoss);
            }
            double l_dblProfitLossCost = 0.0D;
            if (l_balanceReferenceDetailUnits[i].appraisalProfitLossCost != null)
            {
                l_dblProfitLossCost = Double.parseDouble(l_balanceReferenceDetailUnits[i].appraisalProfitLossCost);
            }

            // 信用取引残高照会明細.建区分 == "買建"の場合
            if (l_balanceReferenceDetailUnits[i].contractType.equals(WEB3MarginContractTypeDef.OPEN_BUY))
            {
                // 買建株総額、買建株評価損益合計、買建株評価損益合計（諸経費考慮）に加算する
                l_dblContractAmountLong += l_dblContractAmount;
                l_dblProfitLossLong += l_dblProfitLoss;
                l_dblProfitLossCostLong += l_dblProfitLossCost;
            }
            // 信用取引残高照会明細.建区分 == "売建"の場合
            else if (l_balanceReferenceDetailUnits[i].contractType.equals(WEB3MarginContractTypeDef.OPEN_SELL))
            {
                // 売建株総額、売建株評価損益合計、売建株評価損益合計（諸経費考慮）に加算する
                l_dblContractAmountShort += l_dblContractAmount;
                l_dblProfitLossShort += l_dblProfitLoss;
                l_dblProfitLossCostShort += l_dblProfitLossCost;
            }

            // 信用取引残高照会明細.口座区分 == "一般"の場合
            if (l_balanceReferenceDetailUnits[i].taxType.equals(WEB3TaxTypeDef.NORMAL))
            {
                // 一般口座建株総額、一般口座評価損益合計、一般口座評価損益合計（諸経費考慮）
                l_dblContractAmountNormal += l_dblContractAmount;
                l_dblProfitLossNormal += l_dblProfitLoss;
                l_dblProfitLossCostNormal += l_dblProfitLossCost;
            }
            // 信用取引残高照会明細.口座区分 == "特定"の場合
            else if (l_balanceReferenceDetailUnits[i].taxType.equals(WEB3TaxTypeDef.SPECIAL))
            {
                // 特定口座建株総額、特定口座評価損益合計、特定口座評価損益合計（諸経費考慮）
                l_dblContractAmountSpecial += l_dblContractAmount;
                l_dblProfitLossSpecial += l_dblProfitLoss;
                l_dblProfitLossCostSpecial += l_dblProfitLossCost;
            }
        }

        // 建株総額合計、建株評価損益合計、建株評価損益合計（諸経費考慮）をセットする。
        l_dblContractAmountTotal = l_dblContractAmountLong + l_dblContractAmountShort;
        l_dblProfitLossTotal = l_dblProfitLossLong + l_dblProfitLossShort;
        l_dblProfitLossCostTotal = l_dblProfitLossCostLong + l_dblProfitLossCostShort;

        log.debug("建株総額合計:" + l_dblContractAmountTotal);
        log.debug("建株評価損益合計:" + l_dblProfitLossTotal);
        log.debug("建株評価損益合計（諸経費考慮）:" + l_dblProfitLossCostTotal);

        // レスポンスデータにプロパティをセットする。
        // 買建株総額
        l_response.buyTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmountLong);
        // 売建株総額
        l_response.sellTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmountShort);
        // 特定口座建株総額
        l_response.capitalGainTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmountSpecial);
        // 一般口座建株総額
        l_response.normalAccountTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmountNormal);
        // 建株総額合計
        l_response.totalPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmountTotal);

        // 買建株評価損益合計
        l_response.buyTotalAssetProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblProfitLossLong);
        // 売建株評価損益合計
        l_response.sellTotalAssetProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblProfitLossShort);
        // 特定口座評価損益合計
        l_response.capitalGainTotalAssetProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblProfitLossSpecial);
        // 一般口座評価損益合計
        l_response.normalAccountTotalAssetProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblProfitLossNormal);
        // 建株評価損益合計
        l_response.totalAssetProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblProfitLossTotal);

        // 買建株評価損益合計（諸経費考慮）
        l_response.buyTotalAssetProfitLossCost = WEB3StringTypeUtility.formatNumber(l_dblProfitLossCostLong);
        // 売建株評価損益合計（諸経費考慮）
        l_response.sellTotalAssetProfitLossCost = WEB3StringTypeUtility.formatNumber(l_dblProfitLossCostShort);
        // 特定口座評価損益合計（諸経費考慮）
        l_response.capitalGainTotalAssetProfitLossCost = WEB3StringTypeUtility.formatNumber(l_dblProfitLossCostSpecial);
        // 一般口座評価損益合計（諸経費考慮）
        l_response.normalAccountTotalAssetProfitLossCost = WEB3StringTypeUtility.formatNumber(l_dblProfitLossCostNormal);
        // 建株評価損益合計（諸経費考慮）
        l_response.totalAssetProfitLossCost = WEB3StringTypeUtility.formatNumber(l_dblProfitLossCostTotal);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create銘柄・市場プルダウン)<BR>
     * <BR>
     * 顧客の保持する建株より、銘柄・市場の一覧を作成し、<BR>
     * レスポンスデータに設定して返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(信用取引残高照会サービス)create銘柄・市場プルダウン」<BR>
     * 参照<BR>
     * @@param l_subAccount - (補助口座) 補助口座オブジェクト<BR>
     * @@param l_response - (レスポンスデータ) 信用取引残高照会レスポンスオブジェクト<BR>
     * @@return WEB3MarginBalanceReferenceResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C11B7601D8<BR>
     */
    protected WEB3MarginBalanceReferenceResponse createProductMarketPullDown(WEB3GentradeSubAccount l_subAccount, WEB3MarginBalanceReferenceResponse l_response)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createProductMarketPullDown(WEB3GentradeSubAccount, WEB3MarginBalanceReferenceResponse)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        // 拡張金融オブジェクトマネージャ取得
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // ポジションマネージャ取得
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();

        // get建株一覧()
        List l_lstContract =
            l_positionManager.getContracts(l_subAccount, ProductTypeEnum.EQUITY, null, "product_id asc", null);

        // 該当の建株が取得できなかった場合
        if (l_lstContract == null || l_lstContract.size() == 0)
        {
            // レスポンスデータに初期値をセットして終了する。
            l_response.productCodeNames = null;
            l_response.marketList = null;
            l_response.balanceReference = null;

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // 銘柄コード名称、市場コード格納リストを生成する。
        TreeMap l_tmProcutCode = new TreeMap();
        TreeMap l_tmMarketCode = new TreeMap();

        WEB3EquityContract l_contract = null;
        // get建株一覧()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_lstContract.size(); i++)
        {
            l_contract =
                new WEB3EquityContract((EqtypeContractRow)l_lstContract.get(i));

            // get決済状態()
            WEB3MarginCloseStatus l_closeStatus = l_positionManager.getMarginCloseStatus(l_contract);

            // (処理対象チェック)
            // get決済状態()の戻り値.決済中フラグ == false かつ 未決済フラグ == falseの場合、
            // 次の要素へ処理を移行する。
            if (l_closeStatus.closingMarginFlag == false
                && l_closeStatus.closeMarginFlag == false)
            {
                continue;
            }

            // getProduct()
            WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_contract.getProduct();

            // 銘柄一覧リストに未追加の場合
            if (l_tmProcutCode.get(l_eqtypeProduct.getProductCode()) == null)
            {
                // 信用取引銘柄コード名称インスタンスを生成
                WEB3MarginProductCodeNameUnit l_productCodeNameUnit = new WEB3MarginProductCodeNameUnit();

                // プロパティセット
                // 銘柄コード
                l_productCodeNameUnit.productCode = l_eqtypeProduct.getProductCode();
                // 銘柄名
                EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_eqtypeProduct.getDataSourceObject();
                l_productCodeNameUnit.productName = l_eqtypeProductRow.getStandardName();

                // 銘柄コード名称格納リストにプロパティセットしたインスタンスを追加
                l_tmProcutCode.put(l_eqtypeProduct.getProductCode(), l_productCodeNameUnit);
            }

            // 市場オブジェクト取得
            Market l_market = null;
            try
            {
	            l_market = l_finObjectManager.getMarket(l_contract.getMarketId());
            } catch (NotFoundException l_ex) {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "市場オブジェクトの取得に失敗しました");
            }

            // 市場コード格納リストに市場コードを追加
            l_tmMarketCode.put(new Long(l_market.getMarketId()), l_market.getMarketCode());
        }

        // プロパティセット
        // 銘柄一覧
        l_response.productCodeNames =
            (WEB3MarginProductCodeNameUnit[])l_tmProcutCode.values().toArray(new WEB3MarginProductCodeNameUnit[0]);
        // 銘柄一覧
        l_response.marketList =
            (String[])l_tmMarketCode.values().toArray(new String[0]);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件文字列)<BR>
     * <BR>
     * リクエストデータをもとに、検索条件（where以下指定の文字列）を作成する。<BR>
     * <BR>
     * １）　@パラメータ.銘柄コード == null　@かつ<BR>
     * 　@　@パラメータ.市場コード == nullの場合、nullを返却して終了する。<BR>
     * <BR>
     * ２）　@戻り値となる文字列のインスタンスを生成する。<BR>
     * <BR>
     * ３）　@パラメータ.銘柄コード != null（銘柄コード指定）の場合、<BR>
     * 　@銘柄ID指定を文字列インスタンスに追加する。<BR>
     * <BR>
     * 　@　@　@" and product_id = ?"<BR>
     * <BR>
     * ４）　@パラメータ.市場コード != null（市場コード指定）の場合、<BR>
     * 　@市場ID指定を文字列インスタンスに追加する。<BR>
     * <BR>
     * 　@　@　@" and market_id = ?"<BR>
     * <BR>
     * ５）　@文字列インスタンスを返却する。<BR>
     * <BR>
     * @@param l_strProductCode - (銘柄コード) 銘柄コード<BR>
     * @@param l_strMarketCode - (市場コード) 市場コード<BR>
     * @@return String<BR>
     * @@roseuid 41C12BEE00FE<BR>
     */
    protected String createQueryString(String l_strProductCode, String l_strMarketCode)
    {
        final String STR_METHOD_NAME = "createQueryString(String, String)";
        log.entering(STR_METHOD_NAME);

        // パラメータ.銘柄コード == null かつ パラメータ.市場コード == nullの場合
        if (l_strProductCode == null
            && l_strMarketCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        String l_strQueryString = "";

        // 銘柄コードに該当する銘柄IDを検索条件文字列に追加
        if (l_strProductCode != null)
        {
            l_strQueryString = " and product_id = ? ";
        }

        // 市場コードに該当する市場IDを条件を検索条件文字列に追加
        if (l_strMarketCode != null)
        {
            l_strQueryString += " and market_id = ? ";
        }

        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * <BR>
     * リクエストデータから、検索条件（where以下指定の文字列）のパラメータリストを作成?
<BR>
     * る。<BR>
     * <BR>
     * １）　@パラメータ.銘柄コード == null　@かつ<BR>
     * 　@　@パラメータ.市場コード == nullの場合、nullを返却して終了する。<BR>
     * <BR>
     * ２）　@文字列配列を作成する。<BR>
     * <BR>
     * ３）　@パラメータ.銘柄コード != null（銘柄コード指定）の場合、銘柄IDを<BR>
     * 　@　@文字列配列にセットする。<BR>
     * <BR>
     * 　@　@　@銘柄ID = 拡張プロダクトマネージャ.get銘柄(パラメータ.証券会社, <BR>
     * パラメータ.銘柄コード).銘柄ID<BR>
     * <BR>
     * ４）　@パラメータ.市場コード != null（市場コード指定）の場合、市場IDを<BR>
     * 　@　@文字列配列にセットする。<BR>
     * <BR>
     * 　@　@　@市場ID ＝ 拡張金融オブジェクトマネージャ.getMarket(パラメータ.証券会社, <BR>
     * パラメータ.市場コード).市場ID<BR>
     * <BR>
     * ５）　@文字列配列を返却する。<BR>
     * <BR>
     * @@param l_institution - (証券会社) 証券会社オブジェクト<BR>
     * @@param l_strProductCode - (銘柄コード) 銘柄コード<BR>
     * @@param l_strMarketCode - (市場コード) 市場コード<BR>
     * @@return String[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C12BEE0101<BR>
     */
    protected String[] createQueryContainer(WEB3GentradeInstitution l_institution, String l_strProductCode, String l_strMarketCode)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryContainer(WEB3GentradeInstitution, String, String)";
        log.entering(STR_METHOD_NAME);

        // パラメータ.銘柄コード == null かつ パラメータ.市場コード == nullの場合
        if (l_strProductCode == null
            && l_strMarketCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // 拡張プロダクトマネージャ、拡張金融オブジェクトマネージャの取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        // 株式銘柄、市場オブジェクトを取得
        EqTypeProduct l_eqtypeProduct = null;
        Market l_market = null;
        ArrayList l_lstQueryDataContainer = new ArrayList();
        try
        {
            if (l_strProductCode != null)
            {
		        l_eqtypeProduct =
		            l_productManager.getProduct(l_institution, l_strProductCode);
		        l_lstQueryDataContainer.add(String.valueOf(l_eqtypeProduct.getProductId()));
            }
            if (l_strMarketCode != null)
            {
                l_market =
	                l_finObjectManager.getMarket(l_institution, l_strMarketCode);
		        l_lstQueryDataContainer.add(String.valueOf(l_market.getMarketId()));
            }
        } catch (NotFoundException l_ex) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "株式銘柄または、市場オブジェクトの取得に失敗しました");
        }

        log.exiting(STR_METHOD_NAME);
        return (String[])l_lstQueryDataContainer.toArray(new String[0]);
    }

    /**
     * (create残高照会明細一覧)<BR>
     * <BR>
     * 信用取引残高照会明細の一覧を作成する。<BR>
     * 以下のいずれかの決済状態に当てはまる建株情報を抽出する。<BR>
     * (決済状態の指定がある場合には、指定決済状態のみの建株情報とする)<BR>
     * ・未決済<BR>
     * ・決済中<BR>
     * <BR>
     * ※該当データが存在しない場合にはnullを返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(信用取引残高照会サービス)create残高照会明細一覧」<BR>
     * 参照<BR>
     * @@param l_subAccount - (補助口座) 補助口座オブジェクト<BR>
     * @@param l_strSpecifiedSettleDiv - (指定決済状態) 下記のいずれか。<BR>
     * <BR>
     * null：指定なし <BR>
     * 1：未決済<BR>
     * 2：決済中<BR>
     * <BR>
     * @@param l_strQueryString - (検索条件文字列) 検索条件文字列<BR>
     * @@param l_strQueryDataContainer - (検索条件データコンテナ) 検索条件データコンテナ<BR>
     * @@return WEB3MarginBalanceReferenceDetailUnit[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C12E080275<BR>
     */
    protected WEB3MarginBalanceReferenceDetailUnit[] createBalanceReferenceDetailUnitList(WEB3GentradeSubAccount l_subAccount, String l_strSpecifiedSettleDiv, String l_strQueryString, String[] l_strQueryDataContainer)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createBalanceReferenceDetailUnitList(WEB3GentradeSubAccount, String, String, String[])";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // 拡張金融オブジェクトマネージャ
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        // 拡張ポジションマネージャ取得
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();

        // get建株一覧()
        List l_lstContract =
            l_positionManager.getContracts(l_subAccount, ProductTypeEnum.EQUITY, l_strQueryString, "product_id asc", l_strQueryDataContainer);

        // get建株一覧にてnullが返却された場合
        if (l_lstContract == null || l_lstContract.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // 残高照会明細格納リストを生成する。
        ArrayList l_lstBalanceReferenceUnit = new ArrayList();

        WEB3EquityContract l_contract = null;
        WEB3MarginCloseStatus l_closeStatus = null;
        // get建株一覧()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_lstContract.size(); i++)
        {
            l_contract =
                new WEB3EquityContract((EqtypeContractRow)l_lstContract.get(i));

            // 市場オブジェクトを取得
            Market l_market = null;
            try
            {
	            l_market = l_finObjectManager.getMarket(l_contract.getMarketId());
            } catch (NotFoundException l_ex) {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "市場オブジェクトの取得に失敗しました");
            }

            // reset市場コード()
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());

            // get決済状態()
            l_closeStatus = l_positionManager.getMarginCloseStatus(l_contract);

            // (処理対象チェック)
            // 以下の条件のいづれかに該当する場合は、次の要素へ処理を移行する。
            // ①@get決済状態()の戻り値.決済中フラグ == false かつ 未決済フラグ == falseの場合
            if (l_closeStatus.closingMarginFlag == false
                && l_closeStatus.closeMarginFlag == false)
            {
                continue;
            }
            // ②パラメータ.指定決済状態が
            // 　@・"未決済" かつ get決済状態()の戻り値.未決済フラグ == falseの場合
            // 　@・"決済中" かつ get決済状態()の戻り値.決済中フラグ == falseの場合
            if (l_strSpecifiedSettleDiv != null)
            {
	            if ((l_strSpecifiedSettleDiv.equals(WEB3EquitySettlementStateDef.HAVE_NOT_SETTLED)
	                    && l_closeStatus.closeMarginFlag == false)
	                 || l_strSpecifiedSettleDiv.equals(WEB3EquitySettlementStateDef.SETTLING)
	                    && l_closeStatus.closingMarginFlag == false)
	            {
	                continue;
	            }
            }

            // 未決済建株の場合
            // (パラメータ.指定決済状態 == null or パラメータ.指定決済状態 == "未決済")
            // かつ
            // get決済状態()の戻り値.未決済フラグ == true
            if ((l_strSpecifiedSettleDiv == null
                    || l_strSpecifiedSettleDiv.equals(WEB3EquitySettlementStateDef.HAVE_NOT_SETTLED))
                && l_closeStatus.closeMarginFlag == true)
            {
                // create未決済残高照会明細
                l_lstBalanceReferenceUnit.add(
                    l_positionManager.createUnCloseMarginBalanceReferenceDetailUnit(l_subAccount, l_contract));
            }

            // 決済中建株の場合
            // (パラメータ.指定決済状態 == null or パラメータ.指定決済状態 == "決済中")
            // かつ
            // get決済状態()の戻り値.決済中フラグ == true
            if ((l_strSpecifiedSettleDiv == null
                    || l_strSpecifiedSettleDiv.equals(WEB3EquitySettlementStateDef.SETTLING))
                && l_closeStatus.closingMarginFlag == true)
            {
                // create決済中残高照会明細
                l_lstBalanceReferenceUnit.add(
                    l_positionManager.createClosingMarginBalanceReferenceDetailUnit(l_subAccount, l_contract));
            }
        }

        // 残高照会明細の配列を生成し、返却
        log.exiting(STR_METHOD_NAME);
        return (WEB3MarginBalanceReferenceDetailUnit[])l_lstBalanceReferenceUnit.toArray(new WEB3MarginBalanceReferenceDetailUnit[0]);
    }

    /**
     * (sort残高照会明細)<BR>
     * <BR>
     * 指定されたソートキー、昇降順にもどついて残高照会明細のソートを行う。<BR>
     * <BR>
     * １）ArrayListを生成する。 <BR>
     * <BR>
     * ２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@２－１）ソートキー.キー項目の値に対応する比較項目のComparatorを生成し、<BR>
     * 　@　@　@ArrayListに追加する。 <BR>
     * <BR>
     * 　@　@　@①@信用取引残高照会Comparatorを生成する。<BR>
     * <BR>
     * 　@　@　@　@[コンストラクタにセットするパラメータ] <BR>
     * 　@　@　@　@　@orderBy： ソートキー.昇順／降順<BR>
     * 　@　@　@　@　@比較項目：　@ソートキー.キー項目<BR>
     * <BR>
     * 　@　@　@②ArrayListに生成したComparatorを追加する。<BR>
     * <BR>
     * ３）WEB3ArraysUtility.sort()メソッドをコールする。 <BR>
     * <BR>
     * 　@[sort()にセットするパラメータ] <BR>
     * 　@　@obj：　@パラメータ.残高照会明細<BR>
     * 　@　@com：　@生成したArrayList.toArray()の戻り値<BR>
     * <BR>
     * @@param l_balanceReferenceDetailUnit - (残高照会明細) 信用取引残高照会明細の配列<BR>
     * @@param l_sortKey - (ソートキー) 信用取引ソートキーの配列<BR>
     * @@roseuid 41C298710100<BR>
     */
    protected void sortBalanceReferenceDetailUnit(WEB3MarginBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnit, WEB3MarginSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = "sortBalanceReferenceDetailUnit(WEB3MarginBalanceReferenceDetailUnit, WEB3MarginSortKey)";
        log.entering(STR_METHOD_NAME);

        // パラメータ.残高照会明細 == nullの場合、処理終了
        if (l_balanceReferenceDetailUnit == null || l_balanceReferenceDetailUnit.length == 0)
        {
            return;
        }

        ArrayList l_lstComparators = new ArrayList();

        // パラメータ.ソートキーの要素数分Loop処理
        WEB3MarginBalanceReferenceComparator l_comparator = null;
        String l_strOrderBy = null;
        String l_strKeyItem = null;
        for (int i = 0; i < l_sortKey.length; i++)
        {
            l_strOrderBy = l_sortKey[i].ascDesc;
            l_strKeyItem = l_sortKey[i].keyItem;
            log.debug("第" + (i + 1) + "ソートキー：" + l_strKeyItem + " " + l_strOrderBy);

            // 現物株式残高照会Comparatorを生成
            l_comparator = new WEB3MarginBalanceReferenceComparator(l_strOrderBy, l_strKeyItem);
            // ArrayListにComparatorを追加
            l_lstComparators.add(l_comparator);
        }

        // ソート
        WEB3ArraysUtility.sort(l_balanceReferenceDetailUnit,
            (WEB3MarginBalanceReferenceComparator[])l_lstComparators.toArray(new WEB3MarginBalanceReferenceComparator[0]));
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set取引可能フラグ)<BR>
     * <BR>
     * 引数の残高照会明細の<BR>
     * 　@・新規建可能フラグ<BR>
     * 　@・返済可能フラグ<BR>
     * 　@・現引現渡可能フラグ<BR>
     * の設定を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(信用取引残高照会サービス)set取引可能フラグ」<BR>
     * 参照<BR>
     * <BR>
     * <BR>
     * @@param l_subAccount - (補助口座) 補助口座オブジェクト<BR>
     * @@param l_balanceReferenceDetailUnitList - (残高照会明細一覧) 信用取引残高照会明細の配列<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C29B690357<BR>
     */
    protected void setTradingFlag(WEB3GentradeSubAccount l_subAccount, WEB3MarginBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnitList)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setTradingFlag(WEB3GentradeSubAccount, WEB3MarginBalanceReferenceDetailUnit[]) ";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        // 注文チェックサービス取得　@
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // 株式注文マネージャ取得
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        // 拡張プロダクトマネージャ取得
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();

        // 顧客情報を取得
        WEB3GentradeInstitution l_institution =
            (WEB3GentradeInstitution)l_subAccount.getInstitution();
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();

        // create銘柄・市場プルダウン()
        //性能チューニング修正 劉(FLJ)
        //取引可能顧客チェックLOOP外へ
        //begin
        // 新規建、返済、現引現渡共通チェック
        // 取引可能顧客チェック
        OrderValidationResult l_orderValidationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        //end
        // パラメータ.残高照会明細一覧の要素数分Loop処理
        for (int i = 0; i < l_balanceReferenceDetailUnitList.length; i++)
        {
            // reset市場コード
            String l_strMarketCode = l_balanceReferenceDetailUnitList[i].marketCode;
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);


            // チェックOKでない場合
            if (!l_orderValidationResult.equals(OrderValidationResult.VALIDATION_OK_RESULT))
            {
                log.debug("validate取引可能顧客()が例外をスローした為、新規建・返済・現引現渡フラグ == false");
                // 新規建可能フラグ、返済可能フラグ、現引現渡可能フラグにfalseをセット
                l_balanceReferenceDetailUnitList[i].tradingFlag = false;
                l_balanceReferenceDetailUnitList[i].closeMarginFlag = false;
                l_balanceReferenceDetailUnitList[i].swapFlag = false;
                continue;
            }

            String l_strProductCode = l_balanceReferenceDetailUnitList[i].productCode;
            String l_strRepaymentType = l_balanceReferenceDetailUnitList[i].repayment.repaymentDiv;
            double l_dblRepaymentNum = Double.parseDouble(l_balanceReferenceDetailUnitList[i].repayment.repaymentTimeLimit);
            WEB3EquityProduct l_eqtypeProduct = null;
            WEB3EquityTradedProduct l_tradedProduct = null;

            try
            {
                // validate銘柄コード(信用)
                l_eqtypeProduct =
                    (WEB3EquityProduct)l_orderManager.validateProductCode(l_strProductCode, l_strInstitutionCode, l_strRepaymentType);

                // get取引銘柄
                try {
	                l_tradedProduct =
	                    (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_institution, l_strProductCode, l_strMarketCode);
                } catch (NotFoundException l_ex) {
                    log.debug("取引銘柄なし 証券会社コード：[" +
                        l_institution.getInstitutionCode() +
                        "] 銘柄コード:[" + l_strProductCode +
                        "] 市場コード:[" + l_strMarketCode + "]");
                    // 新規建可能フラグ、返済可能フラグ、現引現渡可能フラグにfalseをセット
                    l_balanceReferenceDetailUnitList[i].tradingFlag = false;
                    l_balanceReferenceDetailUnitList[i].closeMarginFlag = false;
                    l_balanceReferenceDetailUnitList[i].swapFlag = false;
                    continue;
                }
                // validate取扱可能市場()
                l_orderManager.validateHandlingMarket(l_branch, l_tradedProduct, l_strMarketCode, l_strRepaymentType, l_dblRepaymentNum);
                // validateインサイダー()
                l_orderManager.validateInsider(l_subAccount, l_eqtypeProduct);

            } catch (WEB3BusinessLayerException l_wbex) {
                log.debug("新規建・返済・現引現渡共通チェックNGの為、新規建・返済・現引現渡フラグ = falseをセット");
                // 新規建可能フラグ、返済可能フラグ、現引現渡可能フラグにfalseをセット
                l_balanceReferenceDetailUnitList[i].tradingFlag = false;
                l_balanceReferenceDetailUnitList[i].closeMarginFlag = false;
                l_balanceReferenceDetailUnitList[i].swapFlag = false;
                continue;
            }

            // 売建かどうかのフラグ
            boolean l_blnIsShort;
            if (l_balanceReferenceDetailUnitList[i].contractType.equals(WEB3MarginContractTypeDef.OPEN_SELL))
            {
                l_blnIsShort = true;
            }
            else
            {
                l_blnIsShort = false;
            }

            WEB3GentradeMarket l_market = null;
            OrderTypeEnum l_orderTypeEnum = null;
            // 未決済残高照会明細の場合
            if (l_balanceReferenceDetailUnitList[i].settlementState.equals(WEB3EquitySettlementStateDef.HAVE_NOT_SETTLED))
            {
                // 返済可能チェックを行う
                // 注文受付トランザクション = "返済"をセット
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);
                try
                {
	                // validate注文受付可能
	                WEB3GentradeTradingTimeManagement.validateOrderAccept();
	                // validate市場コード
	                l_market = (WEB3GentradeMarket)l_orderManager.validateMarket(l_strMarketCode, l_strInstitutionCode);
	                // validate取引銘柄（信用）
                    l_orderManager.validateTradedProductForMarginTrading(l_subAccount, l_eqtypeProduct, l_market, l_branch, l_strRepaymentType, OrderCategEnum.CLOSE_MARGIN, l_blnIsShort);

                    // 注文種別の判別
                    if (l_balanceReferenceDetailUnitList[i].contractType.equals(WEB3MarginContractTypeDef.OPEN_BUY))
                    {
                        // 買建売返済をセット
                        l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_LONG;
                    }
                    else
                    {
                        // 売建買返済をセット
                        l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_SHORT;
                    }
                    // validate顧客銘柄別取引停止
                    l_orderManager.validateAccountProductOrderStop(l_subAccount, l_eqtypeProduct.getProductId(), l_orderTypeEnum);

                } catch (WEB3BusinessLayerException l_wbex) {
	                log.debug("返済可能チェックNGの為、返済フラグ = false");
                    // 返済可能フラグにfalseをセット
	                l_balanceReferenceDetailUnitList[i].closeMarginFlag = false;
                }

                // 現引現渡可能チェック
                // 注文受付トランザクション = "現引・現渡"をセット
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.SWAP_MARGIN);
                // 受付時間区分 = "現引・現渡"をセット
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);

                try
                {
	                // validate注文受付可能
	                WEB3GentradeTradingTimeManagement.validateOrderAccept();
	                
					// 拡張金融オブジェクトマネージャ
					WEB3GentradeFinObjectManager l_finObjectManager =
						(WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
						
					try
					{
						l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(l_strInstitutionCode, l_strMarketCode);
					}
					catch (NotFoundException l_nfe)
					{
						throw new WEB3SystemLayerException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80005,
						this.getClass().getName() + "." + STR_METHOD_NAME,
						"市場オブジェクトの取得に失敗しました");
					}
	                // validate取引銘柄（信用）
                    l_orderManager.validateTradedProductForMarginTrading(
                        l_subAccount, l_eqtypeProduct, l_market, l_branch, l_strRepaymentType, OrderCategEnum.SWAP_MARGIN, l_blnIsShort);

                    // 注文種別の判別
                    if (l_balanceReferenceDetailUnitList[i].contractType.equals(WEB3MarginContractTypeDef.OPEN_BUY))
                    {
                        // 現引をセット
                        l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_LONG;
                    }
                    else
                    {
                        // 現渡をセット
                        l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_SHORT;
                    }
                    // validate顧客銘柄別取引停止
                    l_orderManager.validateAccountProductOrderStop(l_subAccount, l_eqtypeProduct.getProductId(), l_orderTypeEnum);

                } catch (WEB3BusinessLayerException l_wbex) {
                    log.debug("現引現渡可能チェックNGの為、現引現渡フラグ = falseをセット");
                    // 現引現渡可能フラグにfalseをセット
                    l_balanceReferenceDetailUnitList[i].swapFlag = false;
                }
            }

            // 新規建可能チェック
            // 注文受付トランザクション = "買付(新規建買)" or "売付(新規建売)"をセット
            if (l_balanceReferenceDetailUnitList[i].contractType.equals(WEB3MarginContractTypeDef.OPEN_BUY))
            {
                // 買付(新規建買)をセット
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
            }
            else
            {
                // 売付(新規建売)をセット
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
            }
            // 受付時間区分 = "株式・信用"をセット
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

            try
            {
	            // validate注文受付可能
	            WEB3GentradeTradingTimeManagement.validateOrderAccept();
	            // validate市場コード
                l_market =
                    (WEB3GentradeMarket)l_orderManager.validateMarket(l_strMarketCode, l_strInstitutionCode);
                // validate取引銘柄（信用）
                l_orderManager.validateTradedProductForMarginTrading(
                    l_subAccount, l_eqtypeProduct, l_market, l_branch, l_strRepaymentType, OrderCategEnum.OPEN_MARGIN, l_blnIsShort);

                // 注文種別の判別
                if (l_balanceReferenceDetailUnitList[i].contractType.equals(WEB3MarginContractTypeDef.OPEN_BUY))
                {
                    // 新規買建をセット
                    l_orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
                }
                else
                {
                    // 新規売建をセット
                    l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
                }
                // validate顧客銘柄別取引停止
                l_orderManager.validateAccountProductOrderStop(l_subAccount, l_eqtypeProduct.getProductId(), l_orderTypeEnum);

            } catch (WEB3BusinessLayerException l_wbex) {
                log.debug("新規建可能チェックNGの為、新規建可能フラグ = falseをセット");
                // 新規建可能フラグにfalseをセット
                l_balanceReferenceDetailUnitList[i].tradingFlag = false;
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
