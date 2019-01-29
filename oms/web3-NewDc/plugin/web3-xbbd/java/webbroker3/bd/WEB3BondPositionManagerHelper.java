head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondPositionManagerHelper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 債券ポジションマネージャヘルパー(WEB3BondPositionManagerHelper.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17 周捷(中訊) 新規作成
                    2006/10/09 周捷 (中訊) 仕様変更・モデル111
                    2006/10/12 徐大方(中訊)WEBⅢ開発標準の見直しの対応（newBigDecimal部分）
                    2006/10/16 趙林鵬(中訊) モデルNo.127
 */

package webbroker3.bd;

import java.math.BigDecimal;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondPositionManagerHelper;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondAssetCheckDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券ポジションマネージャヘルパー)<BR>
 * 債券ポジションマネージャヘルパークラス
 *
 * @@author 周捷(中訊)
 * @@version 1.0
 */
public class WEB3BondPositionManagerHelper extends BondPositionManagerHelper
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondPositionManagerHelper.class);

    public WEB3BondPositionManagerHelper(ProductTypeEnum l_typeEnum)
    {
        super(l_typeEnum);
    }


    public PersistentDataManager getPersistentDataManager()
    {
        return new WEB3BondPersistentDataManager();
    }
    /**
     * (applyTo保有資産ポジション)applyToAssetPosition<BR>
     * のオーバーライド<BR>
     * <BR>
     * シーケンス図「applyTo保有資産ポジション」を参照<BR>
     * <BR>
     * =============================================== <BR>
     *　@　@　@　@　@シーケンス図 : 「applyTo保有資産ポジション」<BR>
     *　@　@　@　@　@具体位置     : 1.6.1. get保有資産Params == nullの場合<BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@例外。 <BR>
     *　@　@　@　@　@class        : WEB3BusinessLayerException <BR>
     *　@　@　@　@　@tag          : BUSINESS_ERROR_00204 <BR>
     *=============================================== <BR>
     * <BR>
     * @@param l_bondFinTransactionParams - (BondFinTransactionParams)<BR>
     * BondFinTransactionParams
     * @@return List
     * @@throws DataException
     * @@roseuid 44D0354A0000
     */
    public List applyToAssetPosition(
        BondFinTransactionParams l_bondFinTransactionParams)
        throws DataException
    {
        final String STR_METHOD_NAME =
            " applyToAssetPosition(BondFinTransactionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_bondFinTransactionParams == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "パラメータ値がNULL");
        }

        //1.1.債券部店別条件(long)
        //債券部店別条件を生成する。
        //[引数]
        //部店ID： 拡張アカウントマネージャ.get顧客(
        //引数.BondFinTransactionParams.get口座ID()).get部店().get部店ID()

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        WEB3BondBranchCondition l_branchCondition = null;
        try
        {
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_web3GentradeAccountManager.getMainAccount(
                    l_bondFinTransactionParams.getAccountId());

            l_branchCondition =
                new WEB3BondBranchCondition(l_mainAccount.getBranch().getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("__error in DBへのアクセスに失敗しました。__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.2.get保有資産チェック区分( )
        //1.3.get保有資産チェック区分＝＝　@'チェックしない' の場合
        if (WEB3BondAssetCheckDef.EXCEPT.equals(l_branchCondition.getAssetCheckDiv()))
        {
            //1.3.1.nullを返す
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //1.4.get保有資産Params(BondFinTransactionParams)
        WEB3BondPersistentDataManager l_persistentDataManager =
            (WEB3BondPersistentDataManager) getPersistentDataManager();

        //保有資産Paramsを取得する。
        //「メソッドに渡す引数」
        //BondFinTransactionParams＝引数.BondFinTransactionParams
        AssetParams l_assetParams = null;
        l_assetParams =
            l_persistentDataManager.getAsset(l_bondFinTransactionParams);

        //1.5.getFinTransactionType( )
        FinTransactionType l_transactionType =
            l_bondFinTransactionParams.getFinTransactionType();

        //1.6.getSide(arg0 : FinTransactionType)
        SideEnum l_sideEnum = getSide(l_transactionType);

        //1.7.getSide()==SideEnum.Buyの場合
        if (l_sideEnum == SideEnum.BUY)
        {
            //1.7.1.get保有資産Params == nullの場合
            if (l_assetParams == null)
            {
                //1.7.1.1.AssetParams( )
                l_assetParams = new AssetParams();

                //1.7.1.2.setAssetDefaultValues(arg0 : AssetParams)
                setAssetDefaultValues(l_assetParams);

                //1.7.1.3.set保有資産Params(AssetParams, BondFinTransactionParams)
                this.setNewAssetParamsFromMarketTradedTrans(
                    l_assetParams, l_bondFinTransactionParams);

                //1.7.1.4.saveNewAsset(arg0 : AssetParams)
                l_persistentDataManager.saveNewAsset(l_assetParams);
            }
            else
            {
                //1.7.2.get保有資産Params != nullの場合
                //1.7.2.1.update保有資産Params(AssetParams, BondFinTransactionParams)
                this.updateAssetParamsFromMarketTradedTrans(
                    l_assetParams, l_bondFinTransactionParams);

                //1.7.2.2.updateAssetByTrans(arg0 : AssetParams)
                l_persistentDataManager.updateAssetByTrans(l_assetParams);
            }
        }
        else if (l_sideEnum == SideEnum.SELL)
        {
            //1.8.getSide()==SideEnum.SELLの場合
            //1.8.1.get保有資産Params == nullの場合
            if (l_assetParams == null)
            {
                log.debug("保有資産該当データなし。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "保有資産該当データなし。");
            }
            else
            {
                //1.8.2.get保有資産Params != nullの場合
                //1.8.2.1.update保有資産Params(AssetParams, BondFinTransactionParams)
                updateAssetParamsFromMarketTradedTrans(
                    l_assetParams, l_bondFinTransactionParams);

                //1.8.2.2.updateAssetByTrans(arg0 : AssetParams)
                l_persistentDataManager.updateAssetByTrans(l_assetParams);
            }
        }

        //1.9. setAssetId(arg0 : long)
        l_bondFinTransactionParams.setAssetId(l_assetParams.getAssetId());

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (set保有資産Params)<BR>
     * setNewAssetParamsFromMarketTradedTrans(AssetParams, <BR>
     * BondFinTransactionParams)のオーバーライド<BR>
     * <BR>
     * <BR>
     * １）以下のように値をセットする。<BR>
     * 保有資産Params.口座ID＝BondFinTransactionParams.口座ID<BR>
     * 保有資産Params.補助口座ID＝BondFinTransactionParams.補助口座ID<BR>
     * 保有資産Params.銘柄ID＝BondFinTransactionParams.銘柄ID<BR>
     * 保有資産Params.銘柄タイプ＝BondFinTransactionParams.銘柄タイプ<BR>
     * 保有資産Params.数量＝BondFinTransactionParams.約定数量<BR>
     * 保有資産Params.税区分＝BondFinTransactionParams.税区分<BR>
     * 保有資産Params.売付不能数量＝0<BR>
     * 保有資産Params.数量（簿価単価計算用）＝BondFinTransactionParams.約定数量<BR>
     * 保有資産Params.入力簿価単価＝null<BR>
     * 保有資産Params.簿価単価入力日時＝null<BR>
     * 保有資産Params.ミニ株区分＝WEB3MiniStockDivDef.DEFAULT_MINI_STOCK<BR>
     * 保有資産Params.分配金＝0<BR>
     * 保有資産Params.30日未経過残高口数＝0<BR>
     * 保有資産Params.作成日付＝GtlUtils.getSystemTimestamp()<BR>
     * 保有資産Params.更新日付＝GtlUtils.getSystemTimestamp()<BR>
     * 保有資産Params.簿価（簿価単価計算用）＝債券計算サービス.calc売買代金（数量, 単価, 債券銘柄）<BR>
     * 　@[引数]<BR>
     * 　@　@　@数量＝BondFinTransactionParams.約定数量<BR>
     * 　@　@　@単価＝BondFinTransactionParams.約定単価<BR>
     * 　@　@　@債券銘柄＝債券プロダクトマネージャ.get債券銘柄(BondFinTransactionParams.銘柄ID)<BR>
     * @@param l_assetParams - (保有資産Params)<BR>
     * 保有資産Params<BR>
     * @@param l_bondFinTransactionParams - BondFinTransactionParams<BR>
     * @@roseuid 44D04B210128
     */
    protected void setNewAssetParamsFromMarketTradedTrans(
        AssetParams l_assetParams,
        BondFinTransactionParams l_bondFinTransactionParams)
    {
        final String STR_METHOD_NAME =
            " setNewAssetParamsFromMarketTradedTrans(" +
            "AssetParams, BondFinTransactionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_assetParams == null || l_bondFinTransactionParams == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "パラメータ値がNULL");
        }

        //１）以下のように値をセットする。
        //保有資産Params.口座ID＝BondFinTransactionParams.口座ID
        l_assetParams.setAccountId(l_bondFinTransactionParams.getAccountId());
        //保有資産Params.補助口座ID＝BondFinTransactionParams.補助口座ID
        l_assetParams.setSubAccountId(l_bondFinTransactionParams.getSubAccountId());
        //保有資産Params.銘柄ID＝BondFinTransactionParams.銘柄ID
        l_assetParams.setProductId(l_bondFinTransactionParams.getProductId());
        //保有資産Params.銘柄タイプ＝BondFinTransactionParams.銘柄タイプ
        l_assetParams.setProductType(l_bondFinTransactionParams.getProductType());
        //保有資産Params.数量＝BondFinTransactionParams.約定数量
        l_assetParams.setQuantity(l_bondFinTransactionParams.getQuantity());
        //保有資産Params.税区分＝BondFinTransactionParams.税区分
        l_assetParams.setTaxType(l_bondFinTransactionParams.getTaxType());
        //保有資産Params.売付不能数量＝0
        l_assetParams.setQuantityCannotSell(0);
        //保有資産Params.数量（簿価単価計算用）＝BondFinTransactionParams.約定数量
        l_assetParams.setQuantityForBookValue(l_bondFinTransactionParams.getQuantity());
        //保有資産Params.入力簿価単価＝null
        l_assetParams.setInputBookValue(null);
        //保有資産Params.簿価単価入力日時＝null
        l_assetParams.setInputTimestamp(null);
        //保有資産Params.ミニ株区分＝WEB3MiniStockDivDef.DEFAULT_MINI_STOCK
        l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        //保有資産Params.分配金＝0
        l_assetParams.setProfitDistribution(0);
        //保有資産Params.30日未経過残高口数＝0
        l_assetParams.setCountBeforePenalty(0);
        //保有資産Params.作成日付＝GtlUtils.getSystemTimestamp()
        l_assetParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //保有資産Params.更新日付＝GtlUtils.getSystemTimestamp()
        l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //保有資産Params.簿価（簿価単価計算用）＝債券計算サービス.calc売買代金（数量, 単価, 債券銘柄）
        // [引数]
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getBizLogicProvider();
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();

        //  数量＝BondFinTransactionParams.約定数量
        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getQuantity()));
        //  単価＝BondFinTransactionParams.約定単価
        BigDecimal l_bdPrice = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getPrice()));
        //  債券銘柄＝債券プロダクトマネージャ.get債券銘柄(BondFinTransactionParams.銘柄ID)
        BigDecimal l_bdTradePrice = null ;

        WEB3BondProduct l_bondProduct = null;

        try
        {
            l_bondProduct =
                (WEB3BondProduct) l_productManager.getBondProduct(l_bondFinTransactionParams.getProductId());
            l_bdTradePrice = l_bizLogicProvider.calcTradingPrice(l_bdQuantity, l_bdPrice, l_bondProduct);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        l_assetParams.setBookValue(l_bdTradePrice.doubleValue());
    }

    /**
     * (update保有資産Params)<BR>
     * updateAssetParamsFromMarketTradedTrans(AssetParams, <BR>
     * BondFinTransactionParams)のオーバーライド<BR>
     * <BR>
     * (this.getSide(BondFinTransactionParams.トランザクションタイプ)==SideEnum.買)の場合<BR>
     * {<BR>
     * 　@保有資産Params.数量＝保有資産Params.数量　@＋　@BondFinTransactionParams.約定数量<BR>
     * 　@保有資産Params.数量（簿価単価計算用）＝保有資産Params.数量（簿価単価計算用）　@<BR>
     * ＋　@BondFinTransactionParams.約定数量<BR>
     * <BR>
     * 　@if(保有資産Params.税区分 == "一般"　@かつ　@<BR>
     * 　@　@更新前の保有資産Params.数量（簿価単価計算用）> 0　@かつ<BR>
     * 　@　@保有資産Params.簿価（簿価単価計算用）== 0）<BR>
     * 　@{<BR>
     * 　@//no operation<BR>
     * 　@}<BR>
     * 　@else<BR>
     * 　@{<BR>
     * 　@保有資産Params.簿価（簿価単価計算用）＝保有資産Params.簿価（簿価単価計算用）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@債券計算サービス.get売買代金（数量, 単価, 債券銘柄）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ [引数]<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ 数量＝BondFinTransactionParams.約定数量<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ 単価＝BondFinTransactionParams.約定単価<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@
     * 債券銘柄＝債券プロダクトマネージャ.get債券銘柄(BondFinTransactionParams.銘柄ID)<BR>
     * 　@}<BR>
     * }<BR>
     * (this.getSide(BondFinTransactionParams.トランザクションタイプ)==SideEnum.売)の場合<BR>
     * {<BR>
     * 　@残数量＝保有資産Params.売付不能数量 + 保有資産Params.数量（簿価単価計算用) -<BR>
     *  BondFinTransactionParams.約定数量<BR>
     * 　@if(残数量 < 0)<BR>
     * 　@{<BR>
     * 　@　@「保有資産残数量チェックエラー」をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     * 　@   tag:   BUSINESS_ERROR_01931<BR>
     * 　@}<BR>
     * 　@if(保有資産Params.数量　@＞＝　@BondFinTransactionParams.約定数量)<BR>
     * 　@{<BR>
     * 　@　@保有資産Params.数量＝保有資産Params.数量　@－　@<BR>
     * BondFinTransactionParams.約定数量<BR>
     * 　@}<BR>
     * 　@else<BR>
     * 　@{<BR>
     * 　@　@保有資産Params.数量＝0<BR>
     * 　@　@保有資産Params.売付不能数量＝保有資産Params.売付不能数量<BR>
     * 　@－　@(BondFinTransactionParams.約定数量　@－　@保有資産Params
     * .数量)<BR>
     * 　@}<BR>
     * }<BR>
     * 保有資産Params.更新日付＝GtlUtils.getSystemTimestamp()<BR>
     * @@param l_assetParams - AssetParams<BR>
     * @@param l_bondFinTransactionParams - BondFinTransactionParams<BR>
     * @@roseuid 44D07288037A
     */
    protected void updateAssetParamsFromMarketTradedTrans(
        AssetParams l_assetParams,
        BondFinTransactionParams l_bondFinTransactionParams)
    {
        final String STR_METHOD_NAME =
            " updateAssetParamsFromMarketTradedTrans(AssetParams, " +
            "BondFinTransactionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_assetParams == null || l_bondFinTransactionParams == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "パラメータ値がNULL");
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //(this.getSide(BondFinTransactionParams.トランザクションタイプ)==SideEnum.買)の場合
        SideEnum l_sideEnum = getSide(l_bondFinTransactionParams.getFinTransactionType());
        BigDecimal l_bdQuantity= new BigDecimal(String.valueOf(l_assetParams.getQuantity()));
        BigDecimal l_bdFinQuantity = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getQuantity()));
        BigDecimal l_bdPrice = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getPrice()));
        BigDecimal l_bdForBookValue = new BigDecimal(String.valueOf(l_assetParams.getQuantityForBookValue()));
        BigDecimal l_bdTradePrice = null ;
        BigDecimal l_bdBookValue = new BigDecimal(String.valueOf(l_assetParams.getBookValue()));
        BigDecimal l_bdQuantityCannotSell = new BigDecimal(String.valueOf(l_assetParams.getQuantityCannotSell()));

        if (l_sideEnum == SideEnum.BUY)
        {
            //保有資産Params.数量＝保有資産Params.数量　@
            //＋　@BondFinTransactionParams.約定数量
            l_assetParams.setQuantity(
                l_bdQuantity.add(l_bdFinQuantity).doubleValue());

            //保有資産Params.数量（簿価単価計算用）＝保有資産Params.数量（簿価単価計算用）　@
            //＋　@BondFinTransactionParams.約定数量
            l_assetParams.setQuantityForBookValue(
                l_bdForBookValue.add(l_bdFinQuantity).doubleValue());

            //　@if(保有資産Params.税区分 == "一般"　@かつ　@
            //　@　@更新前の保有資産Params.数量（簿価単価計算用）> 0　@かつ
            //　@　@保有資産Params.簿価（簿価単価計算用）== 0）
            if (TaxTypeEnum.NORMAL.equals(l_assetParams.getTaxType())
                && l_bdForBookValue.doubleValue() > 0.0D
                && l_assetParams.getBookValue() == 0.0D)
            {
                //no operation
            }
            else
            {
                //保有資産Params.簿価（簿価単価計算用）＝保有資産Params.簿価（簿価単価計算用）
                //  ＋　@債券計算サービス.get売買代金（数量, 単価, 債券銘柄）
                //　@　@[引数]
                //　@　@　@数量＝BondFinTransactionParams.約定数量
                //　@　@　@単価＝BondFinTransactionParams.約定単価
                //　@　@　@債券銘柄＝債券プロダクトマネージャ.get債券銘柄(BondFinTransactionParams.銘柄ID)
                WEB3BondBizLogicProvider l_bizLogicProvider =
                    (WEB3BondBizLogicProvider) l_finApp.getTradingModule(
                        ProductTypeEnum.BOND).getBizLogicProvider();
                WEB3BondProductManager l_productManager =
                    (WEB3BondProductManager) l_finApp.getTradingModule(
                        ProductTypeEnum.BOND).getProductManager();

                WEB3BondProduct l_bondProduct = null;
                try
                {
                    l_bondProduct =
                        (WEB3BondProduct) l_productManager.getBondProduct(
                            l_bondFinTransactionParams.getProductId());
                    l_bdTradePrice = l_bizLogicProvider.calcTradingPrice(
                        l_bdFinQuantity, l_bdPrice, l_bondProduct);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                if (l_bdTradePrice != null)
                {
                    l_assetParams.setBookValue(
                        l_bdBookValue.add(l_bdTradePrice).doubleValue());
                }
            }
        }
        //(this.getSide(BondFinTransactionParams.トランザクションタイプ)==SideEnum.売)の場合
        if (l_sideEnum == SideEnum.SELL)
        {
            //残数量＝保有資産Params.売付不能数量 + 保有資産Params.数量
            //- BondFinTransactionParams.約定数量
            double l_dblQuantity = 0.0D;
            l_dblQuantity = l_bdQuantityCannotSell.add(l_bdQuantity) .subtract(l_bdFinQuantity).doubleValue();
            //　@if(残数量 < 0)
            if (l_dblQuantity < 0)
            {
                //「保有資産残数量チェックエラー」をスローする。
                log.debug("保有資産残数量チェックエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01931,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "保有資産残数量チェックエラー。");
            }
            //if(保有資産Params.数量　@＞＝　@BondFinTransactionParams.約定数量)
            if (l_assetParams.getQuantity() >= l_bondFinTransactionParams.getQuantity())
            {
                //保有資産Params.数量＝保有資産Params.数量　@
                //－　@BondFinTransactionParams.約定数量
                l_assetParams.setQuantity(l_bdQuantity.subtract(l_bdFinQuantity).doubleValue());
            }
            else
            {
                //保有資産Params.数量＝0
                l_assetParams.setQuantity(0);

                //保有資産Params.売付不能数量＝保有資産Params.売付不能数量　@
                //－　@(BondFinTransactionParams.約定数量　@－　@保有資産Params.数量)
                l_assetParams.setQuantityCannotSell(
                    l_bdQuantityCannotSell.subtract(l_bdFinQuantity.subtract(l_bdQuantity)).doubleValue());
            }
        }

        //保有資産Params.更新日付＝GtlUtils.getSystemTimestamp()
        l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set保有資産NetAmount)<BR>
     * setAssetNetAmount(BondFinTransactionParams)のオーバーライド<BR>
     * <BR>
     * 何もしない<BR>
     * @@param l_bondFinTransactionParams - BondFinTransactionParams<BR>
     * @@roseuid 44D075450128
     */
    protected void setAssetNetAmount(
        BondFinTransactionParams l_bondFinTransactionParams)
    {
        final String STR_METHOD_NAME =
            " updateAssetParamsFromMarketTradedTrans(" +
            "AssetParams, BondFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        //setAssetNetAmount(BondFinTransactionParams)のオーバーライド
        //何もしない

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set約定情報To債券顧客勘定明細)<BR>
     * setExecutionInfoToMarketOrderedTrans(BondFinTransactionParams, <BR>
     * BondOrderExecution, BondOrderUnitRow)のオーバーライド<BR>
     * <BR>
     * BondFinTransactionParams.口座ID＝BondOrderUnitRow.口座ID<BR>
     * BondFinTransactionParams.補助口座ID＝BondOrderUnitRow.補助口座ID<BR>
     * BondFinTransactionParams.銘柄ID＝BondOrderUnitRow.銘柄ID<BR>
     * BondFinTransactionParams.トランザクションタイプ＝<BR>
     * BondOrderUnitRow.注文種別.toFinTransactionType()<BR>
     * BondFinTransactionParams.トランザクションカテゴリ＝<BR>
     * BondOrderUnitRow.注文種別.toFinTransactionType().toFinTransactionCateg()<BR>
     * BondFinTransactionParams.税区分＝BondOrderUnitRow.税区分<BR>
     * BondFinTransactionParams.受渡日＝BondOrderUnitRow.受渡日<BR>
     * BondFinTransactionParams.銘柄タイプ＝BondOrderUnitRow.銘柄タイプ<BR>
     * BondFinTransactionParams.市場ID＝BondOrderUnitRow.市場ID<BR>
     * BondFinTransactionParams.約定単価＝BondOrderUnitRow.約定単価<BR>
     * BondFinTransactionParams.約定数量＝BondOrderUnitRow.約定数量<BR>
     * BondFinTransactionParams.注文ID＝BondOrderUnitRow.注文ID<BR>
     * BondFinTransactionParams.注文単位ID＝BondOrderUnitRow.注文単位ID<BR>
     * BondFinTransactionParams.経過利子＝0<BR>
     * int l_sign = this.getSide(BondFinTransactionParams.トランザクションタイプ).cashFlowDir();<BR>
     * BondFinTransactionParams.受渡代金＝BondOrderUnitRow.受渡代金（円貨）* l_sign<BR>
     * BondFinTransactionParams.約定ID＝BondOrderExecutionParams.約定ID<BR>
     * @@param l_bondFinTransactionParams - BondFinTransactionParams<BR>
     * @@param l_bondOrderExecution - BondOrderExecution<BR>
     * @@param l_bondOrderUnitRow - BondOrderUnitRow<BR>
     * @@roseuid 44D075FA0128
     */
    protected void setExecutionInfoToMarketOrderedTrans(
        BondFinTransactionParams l_bondFinTransactionParams,
        BondOrderExecution l_bondOrderExecution,
        BondOrderUnitRow l_bondOrderUnitRow)
    {
        final String STR_METHOD_NAME =
            " setExecutionInfoToMarketOrderedTrans(BondFinTransactionParams,"
            + "BondOrderExecution, BondOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_bondFinTransactionParams == null
            || l_bondOrderExecution == null || l_bondOrderUnitRow == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "パラメータ値がNULL");
        }

        //BondFinTransactionParams.口座ID＝BondOrderUnitRow.口座ID
        l_bondFinTransactionParams.setAccountId(l_bondOrderUnitRow.getAccountId());

        //BondFinTransactionParams.補助口座ID＝BondOrderUnitRow.補助口座ID
        l_bondFinTransactionParams.setSubAccountId(l_bondOrderUnitRow.getSubAccountId());

        //BondFinTransactionParams.銘柄ID＝BondOrderUnitRow.銘柄ID
        l_bondFinTransactionParams.setProductId(l_bondOrderUnitRow.getProductId());
        //BondFinTransactionParams.トランザクションタイプ＝
        //BondOrderUnitRow.注文種別.toFinTransactionType()
        l_bondFinTransactionParams.setFinTransactionType(
            l_bondOrderUnitRow.getOrderType().toFinTransactionType());

        //BondFinTransactionParams.トランザクションカテゴリ＝
        //BondOrderUnitRow.注文種別.toFinTransactionType().toFinTransactionCateg()
        l_bondFinTransactionParams.setFinTransactionCateg(
            l_bondOrderUnitRow.getOrderType().toFinTransactionType().toFinTransactionCateg());

        //BondFinTransactionParams.税区分＝BondOrderUnitRow.税区分
        l_bondFinTransactionParams.setTaxType(l_bondOrderUnitRow.getTaxType());

        //BondFinTransactionParams.受渡日＝BondOrderUnitRow.受渡日
        l_bondFinTransactionParams.setDeliveryDate(l_bondOrderUnitRow.getDeliveryDate());

        //BondFinTransactionParams.銘柄タイプ＝BondOrderUnitRow.銘柄タイプ
        l_bondFinTransactionParams.setProductType(l_bondOrderUnitRow.getProductType());

        //BondFinTransactionParams.市場ID＝BondOrderUnitRow.市場ID
        l_bondFinTransactionParams.setMarketId(l_bondOrderUnitRow.getMarketId());

        //BondFinTransactionParams.約定単価＝BondOrderUnitRow.約定単価
        l_bondFinTransactionParams.setPrice(l_bondOrderUnitRow.getExecutedPrice());

        //BondFinTransactionParams.約定数量＝BondOrderUnitRow.約定数量
        l_bondFinTransactionParams.setQuantity(l_bondOrderUnitRow.getExecutedQuantity());

        //BondFinTransactionParams.注文ID＝BondOrderUnitRow.注文ID
        l_bondFinTransactionParams.setOrderId(l_bondOrderUnitRow.getOrderId());

        //BondFinTransactionParams.注文単位ID＝BondOrderUnitRow.注文単位ID
        l_bondFinTransactionParams.setOrderUnitId(l_bondOrderUnitRow.getOrderUnitId());

        //BondFinTransactionParams.経過利子＝0
        l_bondFinTransactionParams.setAccruedInterest(0);

        //int l_sign = this.getSide(BondFinTransactionParams.トランザクションタイプ).cashFlowDir();
        int l_intSign = getSide(l_bondFinTransactionParams.getFinTransactionType()).cashFlowDir();

        //BondFinTransactionParams.受渡代金＝BondOrderUnitRow.受渡代金（円貨）* l_sign
        l_bondFinTransactionParams.setNetAmount(
            new BigDecimal(
                String.valueOf(l_bondOrderUnitRow.getEstimatedPrice())).multiply(
                    new BigDecimal(String.valueOf(l_intSign))).doubleValue());

        //BondFinTransactionParams.約定ID＝BondOrderExecutionParams.約定ID
        l_bondFinTransactionParams.setOrderExecutionId(
            l_bondOrderExecution.getOrderExecutionId());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (reverse保有資産By債券顧客勘定明細)<BR>
     * reverseAssetPositionByTrans(BondFinTransactionParams, SideEnum)のオーバーライド<BR>
     * <BR>
     * １）　@保有資産チェック区分をチェック <BR>
     * 　@１－１）債券部店別条件を生成する。 <BR>
     *　@　@　@　@　@[引数] <BR>
     *　@　@　@　@　@　@部店ID=拡張アカウントマネージャ.get顧客(<BR>
     *　@　@　@　@　@　@引数.BondFinTransactionParams.get口座ID()).get部店().get部店ID()<BR>
     * 　@１－２）債券部店別条件.get保有資産チェック区分 == 'チェックしない'の場合、処理をせずにreturnする。<BR>
     * <BR>
     * ２）保有資産Paramsを取得する<BR>
     * 　@債券データマネージャ.getAsset(BondFinTransactionParams.資産ID)<BR>
     * 　@注）保有資産Params＝＝nullの場合<BR>
     * 　@　@　@例外をスローする<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00204<BR>
     * <BR>
     * ３）SideEnumが買の場合<BR>
     * 　@３－１）保有資産Params.数量＝保有資産Params.数量　@-<BR>
     * 　@BondFinTransactionParams.約定数量<BR>
     * 　@　@　@if(保有資産Params.数量 < 0) <BR>
     * 　@　@　@{<BR>
     * 　@　@　@　@「保有資産残数量チェックエラー」をスローする。<BR>
     * 　@　@　@} <BR>
     *      class: WEB3BusinessLayerException<BR>
     * 　@   tag:   BUSINESS_ERROR_01931<BR>
     * <BR>
     * 　@３－２）保有資産Params.数量（簿価単価計算用）＝<BR>
     *     　@保有資産Params.数量（簿価単価計算用）　@-　@BondFinTransactionParams.約定数量<BR>
     * 　@３－３）次の場合(保有資産Params.税区分 == "一般"　@かつ　@<BR>
     * 　@　@　@　@　@保有資産Params.簿価（簿価単価計算用）== 0）<BR>
     * 　@　@　@　@　@{<BR>
     * 　@　@　@　@　@　@　@//no operation<BR>
     * 　@　@　@　@　@}<BR>
     * 　@　@　@　@　@else<BR>
     * 　@　@　@　@　@{<BR>
     * 　@　@　@　@　@　@　@保有資産Params.簿価（簿価単価計算用）＝保有資産Params.簿価（簿価単価計算用）　@<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@-債券計算サービス.get売買代金（数量, 単価, 債券銘柄）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@数量＝BondFinTransactionParams.約定数量<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@単価＝BondFinTransactionParams.約定単価<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@債券銘柄＝債券プロダクトマネージャ.get債券銘柄(<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@BondFinTransactionParams.銘柄ID)<BR>
     * 　@　@　@　@　@　@}<BR>
     * <BR>
     * ４）SideEnumが売の場合<BR>
     * 　@　@・保有資産Params.数量＝保有資産Params.数量　@+　@<BR>
     * 　@　@BondFinTransactionParams.約定数量<BR>
     * <BR>
     * ５）保有資産Params.更新日付＝GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * ６）保有資産Paramsを更新する。<BR>
     * 　@債券データマネージャ.updateAssetByTrans(保有資産Params)<BR>
     * @@param l_bondFinTransactionParams - BondFinTransactionParams<BR>
     * @@param l_sideEnum - SideEnum<BR>
     * @@throws DataException
     * @@roseuid 44D096020128
     */
    protected void reverseAssetPositionByTrans(
        BondFinTransactionParams l_bondFinTransactionParams,
        SideEnum l_sideEnum) throws DataException
    {
        final String STR_METHOD_NAME =
            " reverseAssetPositionByTrans(BondFinTransactionParams,"
            + "SideEnum, BondOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_bondFinTransactionParams == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        //１）　@保有資産チェック区分をチェック
        //   １－１）債券部店別条件を生成する。
        //     [引数]
        //       部店ID=拡張アカウントマネージャ.get顧客(
        //       引数.BondFinTransactionParams.get口座ID()).get部店().get部店ID()
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        long l_lngAccountId = l_bondFinTransactionParams.getAccountId();
        try
        {
            long l_lngBranch =
                l_web3GentradeAccountManager.getMainAccount(l_lngAccountId).getBranch().getBranchId();
            WEB3BondBranchCondition l_branchCondition = new WEB3BondBranchCondition(l_lngBranch);

            // １－２）債券部店別条件.get保有資産チェック区分 == 'チェックしない'の場合、処理をせずにreturnする。
            if (WEB3BondAssetCheckDef.EXCEPT.equals(l_branchCondition.getAssetCheckDiv()))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in 拡張アカウントマネージャから顧客を取得__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__error in DBへのアクセスに失敗しました。__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）保有資産Paramsを取得する
        //  債券データマネージャ.getAsset(BondFinTransactionParams.資産ID)
        //  注）保有資産Params＝＝nullの場合
        //  例外をスローする
        WEB3BondPersistentDataManager l_bondPersistentManager =
            (WEB3BondPersistentDataManager) getPersistentDataManager();
        AssetParams l_assetParams = null;

        l_assetParams =
            l_bondPersistentManager.getAsset(l_bondFinTransactionParams.getAssetId());

        if (l_assetParams == null)
        {
            log.debug("保有資産該当データなし。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "保有資産該当データなし。");
        }

        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_assetParams.getQuantity()));
        BigDecimal l_bdFinQuantity = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getQuantity()));
        BigDecimal l_bdQuantityForBookValue = new BigDecimal(String.valueOf(l_assetParams.getQuantityForBookValue()));
        //３）SideEnumが買の場合
        if (l_sideEnum == SideEnum.BUY)
        {
            //３－１）保有資産Params.数量＝保有資産Params.数量　@
            //-　@BondFinTransactionParams.約定数量
            l_assetParams.setQuantity(
                l_bdQuantity.subtract(l_bdFinQuantity).doubleValue());
            
            //if(保有資産Params.数量 < 0)  
            if (l_assetParams.getQuantity() < 0)
            {
                //「保有資産残数量チェックエラー」をスローする。
                log.debug("保有資産残数量チェックエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01931,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "保有資産残数量チェックエラー。");
            }

            //３－２）保有資産Params.数量（簿価単価用）＝
            //保有資産Params.数量（簿価単価用）　@-　@BondFinTransactionParams.約定数量
            l_assetParams.setQuantityForBookValue(
                l_bdQuantityForBookValue.subtract(l_bdFinQuantity).doubleValue());

            //３－３）次の場合(保有資産Params.税区分 == "一般"　@かつ　@
            //    保有資産Params.簿価（簿価単価計算用）== 0）
            if (TaxTypeEnum.NORMAL.equals(l_assetParams.getTaxType())
                && l_assetParams.getBookValue() == 0.0D)
            {
                //no operation
            }
            else
            {
                //保有資産Params.簿価（簿価単価計算用）＝保有資産Params.簿価（簿価単価計算用）
                //  -　@債券計算サービス.get売買代金（数量, 単価, 債券銘柄）
                //　@　@[引数]
                //　@　@　@数量＝BondFinTransactionParams.約定数量
                //　@　@　@単価＝BondFinTransactionParams.約定単価
                //　@　@　@債券銘柄＝債券プロダクトマネージャ.get債券銘柄(BondFinTransactionParams.銘柄ID)
                WEB3BondBizLogicProvider l_bizLogicProvider =
                    (WEB3BondBizLogicProvider) l_finApp.getTradingModule(
                        ProductTypeEnum.BOND).getBizLogicProvider();
                WEB3BondProductManager l_productManager =
                    (WEB3BondProductManager) l_finApp.getTradingModule(
                        ProductTypeEnum.BOND).getProductManager();

                BigDecimal l_bdPrice = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getPrice()));
                BigDecimal l_bdTradePrice = null ;

                WEB3BondProduct l_bondProduct = null;
                try
                {
                    l_bondProduct =
                        (WEB3BondProduct) l_productManager.getBondProduct(
                            l_bondFinTransactionParams.getProductId());
                    l_bdTradePrice = l_bizLogicProvider.calcTradingPrice(l_bdFinQuantity, l_bdPrice,
                        l_bondProduct);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                if (l_bdTradePrice != null)
                {
                    BigDecimal l_bdBookValue = new BigDecimal(String.valueOf(l_assetParams.getBookValue()));
                    l_assetParams.setBookValue(
                        l_bdBookValue.subtract(l_bdTradePrice).doubleValue());
                    log.debug(">>>>>>>>>" + l_bdBookValue.subtract(l_bdTradePrice).doubleValue());
                }

            }
        }

        //４）SideEnumが売の場合
        //　@・保有資産Params.数量＝保有資産Params.数量　@+
        //　@BondFinTransactionParams.約定数量
        if (l_sideEnum == SideEnum.SELL)
        {
            l_assetParams.setQuantity(
                l_bdQuantity.add(l_bdFinQuantity).doubleValue());
        }

        //５）保有資産Params.更新日付＝GtlUtils.getSystemTimestamp()
        l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //６）保有資産Paramsを更新する。
        // 債券データマネージャ.updateAssetByTrans(保有資産Params)
        l_bondPersistentManager.updateAssetByTrans(l_assetParams);
        log.exiting(STR_METHOD_NAME);
    }
}@
