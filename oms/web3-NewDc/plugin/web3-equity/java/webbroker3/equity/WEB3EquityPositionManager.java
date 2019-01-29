head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPositionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ポジションマネージャ(WEB3EquityPositionManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23 中尾寿彦(SRA) 新規作成
Revesion History : 2007/04/25 謝旋(中訊) モデル1138,1141
*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSwapOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypePositionManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Contract;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.equity.define.WEB3EquitySettlementStateDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.equity.define.WEB3MarginClosingStatusTypeDef;
import webbroker3.equity.message.WEB3MarginBalanceReferenceDetailUnit;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.quoteadaptor.RealType;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （株式ポジションマネージャ）。<BR>
 * <BR>
 * 株式用のポジションマネージャ。 <BR>
 * （EqTypePositionManagerImplのサブクラス）
 * @@ author 呉艶飛 
 * @@ version 1.0
 */
public class WEB3EquityPositionManager extends EqTypePositionManagerImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityPositionManager.class);

    /**
     * @@roseuid 409CBF120148
     */
    public WEB3EquityPositionManager()
    {
        super.m_tradingType = ProductTypeEnum.EQUITY;
        super.m_helper = new WEB3EquityPositionManagerHelper(super.m_tradingType);
    }

    /**
     * （一口約定処理）<BR>
     * <BR>
     * 一口約定処理を行う。<BR>
     * （* 拡張ポジションヘルパー.一口約定処理( )に委譲する。)<BR>
     * <BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@param l_lngAssetId - 資産ID<BR>
     * @@param l_isExecuteCancel - is約定取消<BR>
     * 約定取消かどうかのフラグ。
     * @@throws WEB3BaseException
     */
    public void shareContractExecution(
        long l_lngOrderUnitId,
        long l_lngAssetId,
        boolean l_isExecuteCancel)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "shareContractExecution(long, long, boolean)";
        log.entering(STR_METHOD_NAME);
        ((WEB3EquityPositionManagerHelper)m_helper).shareContractExecution(l_lngOrderUnitId, l_lngAssetId, l_isExecuteCancel);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （get保有資産）<BR>
     * <BR>
     * （getAsset(口座ID, 補助口座ID, 銘柄ID)のオーバーロード）<BR>
     * <BR>
     * 【保有資産テーブル】から、以下の条件を指定し該当する保有資産を取得し返却する。<BR>
     * <BR>
     * 口座ＩＤ：　@引数の口座ＩＤ<BR>
     * 補助口座ＩＤ：　@引数の補助口座ＩＤ<BR>
     * 銘柄ＩＤ：　@引数の銘柄ＩＤ<BR>
     * 税区分：　@引数の税区分<BR>
     * ミニ株区分：　@0：DEFAULT（ミニ株以外）<BR>
     * <BR>
     * @@param l_lngAccountId - 口座ID。 <BR>
     * @@param l_lngSubAccountId - 補助口座ID。 <BR>
     * @@param l_lngProductId - 銘柄ID。 <BR>
     * @@param l_taxType - 税区分。 <BR>
     * @@return EqTypeAsset
     * @@throws WEB3BaseException
     * @@roseuid 413BED51035D
     */
    public EqTypeAsset getAsset(long l_lngAccountId, long l_lngSubAccountId, long l_lngProductId, TaxTypeEnum l_taxType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAsset(long ,long ,long ,TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityAsset l_asset = null;
        try
        {
            QueryProcessor l_qp = null;
            List l_lisRows = null;
            l_qp = Processors.getDefaultProcessor();
            String l_strWhere = "account_id=? and sub_account_id=? and product_id=? and tax_type=? and mini_stock_div = ?";
            Object[] l_objBinds = new Object[5];
            l_objBinds[0] = new Long(l_lngAccountId);
            l_objBinds[1] = new Long(l_lngSubAccountId);
            l_objBinds[2] = new Long(l_lngProductId);
            l_objBinds[3] = l_taxType;
            l_objBinds[4] = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;
            l_lisRows = l_qp.doFindAllQuery(AssetRow.TYPE, l_strWhere, l_objBinds);

            int l_intSize = l_lisRows.size();
            if (l_intSize > 0)
            {
                l_asset = new WEB3EquityAsset((AssetRow)l_lisRows.get(0));
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error("データ不整合エラー。", l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_dfe.getMessage(), l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("データ不整合エラー。", l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_dne.getMessage(), l_dne);
        }
        log.exiting(STR_METHOD_NAME);
        return l_asset;
    }

    /**
     * (updateロック中数量)<BR>
     * <BR>
     * 【ロック中の資産詳細テーブル】のロック中数量の調整を行う。 <BR>
     * （updateLockedQuantity(long accountId, <BR>
     *                        long subAccountId, <BR>
     *                        long orderUnitId, <BR>
     *                        long productId, <BR>
     *                        double lockedQtyToBeAdjusted)<BR>
     * のオーバーライド） <BR>
     * １）　@引数.lockedQtyToBeAdjustedが0と等しい場合は、処理をせずにそのままreturnする。<BR>
     * if(Utils.Double.isZero(lockedQtyToBeAdjusted))<BR>
     * 　@　@　@　@　@return;<BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.getOrderUnit(引数.注文単位ID)により、<BR>
     * 注文単位オブジェクトを取得する。<BR>
     * 　@２－１）　@ミニ株の場合(*1)、処理を行わず処理を終了する（return）。 <BR>
     *      (*1) ミニ株の判定 <BR>
     *      注文単位.注文種別 = OrderTypeEnum.MINI_STOCK_SELL（株式ミニ投資売注文） Or <BR>
     *      注文単位.注文種別 = OrderTypeEnum.MINI_STOCK_BUY（株式ミニ投資買注文）<BR>
     * <BR>
     * ３）　@this.get保有資産(口座ID, 補助口座ID, 銘柄ID, 税区分)をコールし、<BR>
　@   *         保有資産オブジェクトを取得する。<BR>
　@   * ３－１）　@現引現渡注文の場合(*2)、税区分として注文単位.税区分（現引現渡）を指定する。<BR>
     * 　@現引現渡注文でない場合、税区分として注文単位.税区分を指定する。<BR>
     * 　@(*2)現引現渡注文の判定<BR>
     * 　@注文単位.注文カテゴリ = OrderCategEnum.SWAP_MARGIN（現引現渡注文)<BR>
     * <BR>
     * ４)　@保有資産オブジェクト.assetidをキーにして、Locked_Asset_Detailsテーブルを検索する。<BR>
     * LockedAssetDetailsRow lockedAssetRow = LockedAssetDetailsDao.findRowByPk(assetId);<BR>
     * <BR>
     * ４-１)　@ 該当assetidキーのLockedAssetDetailsRowが存在しない場合、Insertを行う。<BR>
     * －新規ロック中数量オブジェクト（LockedAssetDetailsParams）のプロパティを設定する。<BR>
     * 　@　@ロック中数量オブジェクト.setAssetId(assetId);<BR>
     * 　@　@ロック中数量オブジェクト.setAccountId(引数.accountId);<BR>
     * 　@　@ロック中数量オブジェクト.setSubAccountId(引数.subAccountId);<BR>
     * 　@　@ロック中数量オブジェクト.setCreatedTimestamp(Utils.getSystemTimestamp());<BR>
     * 　@　@ロック中数量オブジェクト.setLastUpdatedTimestamp(Utils.getSystemTimestamp());<BR>
     * 　@　@ロック中数量オブジェクト.setLockedQuantity（引数.lockedQtyToBeAdjusted）;<BR>
     * －DB更新QueryProcessorでデータベースに新規行を追加する。<BR>
     * 　@　@QueryProcessor qp = Processors.getDefaultProcessor();<BR>
     * 　@　@qp.doInsertQuery(新規ロック中数量オブジェクト);<BR>
     * <BR>
     * ４-２)　@ 該当assetidキーのLockedAssetDetailsRowがすでに存在した場合、Updateを行う。<BR>
     * －新ロック中数量を算出する。<BR>
     * 　@　@  新ロック中数量=lockedAssetRow.getLockedQuantity()+引数.lockedQtyToBeAdjusted;<BR>
     * －算出した結果の新ロック中数量をゼロ調整する。（0と等しい場合、0.0Dをセットする）<BR>
     * 　@　@　@if(Utils.Double.isZero(newLockedQty))　@新ロック中数量=0.0D;<BR>
     * <BR>
     * －更新ロック中数量オブジェクト（LockedAssetDetailsParams）のプロパティを設定する。<BR>
     * 　@　@ロック中数量オブジェクト.setAssetId(assetId);<BR>
     * 　@　@ロック中数量オブジェクト.setAccountId(引数.accountId);<BR>
     * 　@　@ロック中数量オブジェクト.setSubAccountId(引数.subAccountId);<BR>
     * 　@　@ロック中数量オブジェクト.setLastUpdatedTimestamp(Utils.getSystemTimestamp());<BR>
     * 　@　@ロック中数量オブジェクト.setLockedQuantity（新ロック中数量）;<BR>
     * －DB更新QueryProcessorでデータベースに該当行を更新する。<BR>
     * 　@　@QueryProcessor qp = Processors.getDefaultProcessor();　@<BR>
     * 　@　@qp.doUpdateQuery(更新ロック中数量オブジェクト);<BR>
     * @@param l_lngAccountId
     * @@param l_lngSubAccountId
     * @@param l_lngOrderUnitId
     * @@param l_lngProductId
     * @@param l_dblAdjustQuantity
     * @@roseuid 413BED5200DD
     */
    public void updateLockedQuantity(long l_lngAccountId, long l_lngSubAccountId, long l_lngOrderUnitId, long l_lngProductId, double l_dblAdjustQuantity)
    {
        final String STR_METHOD_NAME = "updateLockedQuantity(long, long, long, long, double)";
        log.entering(STR_METHOD_NAME);

        //　@引数.lockedQtyToBeAdjustedが0と等しい場合は、処理をせずにそのままreturnする。
        if (GtlUtils.Double.isZero(l_dblAdjustQuantity))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        try
        {
            //取得拡張株式注文マネージャ
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();

            //注文単位オブジェクトを取得する
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitId);
            //　@２－１）　@ミニ株の場合(*1)、処理を行わず処理を終了する（return）。 
            if (OrderTypeEnum.MINI_STOCK_SELL.equals(l_orderUnit.getOrderType()) || OrderTypeEnum.MINI_STOCK_BUY.equals(l_orderUnit.getOrderType()))
            {
                return;
            }
            //取得保有資産
            TaxTypeEnum l_taxTypeEnum = null;
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            //現引現渡注文の場合(*2)、税区分として注文単位.税区分（現引現渡）を指定する。
            if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                l_taxTypeEnum = l_orderUnitRow.getSwapTaxType();
            }
            //現引現渡注文でない場合、税区分として注文単位.税区分を指定する。
            else
            {
                l_taxTypeEnum = l_orderUnitRow.getTaxType();
            }
            WEB3EquityAsset l_asset =
                (WEB3EquityAsset) this.getAsset(
                    l_lngAccountId, l_lngSubAccountId, l_lngProductId, l_taxTypeEnum);
            if (l_asset == null)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "該当する保有資産がありません:["
                    + l_lngAccountId + ","
                    + l_lngSubAccountId + ","
                    + l_taxTypeEnum + "]");
            }
            long l_lngAssetID = l_asset.getAssetId();

            LockedAssetDetailsParams l_eqtypeLockedAssetDetailsParams = (LockedAssetDetailsParams) LockedAssetDetailsDao.findRowByAssetId(l_lngAssetID);

            if (l_eqtypeLockedAssetDetailsParams == null)
            {
                l_eqtypeLockedAssetDetailsParams = new LockedAssetDetailsParams();
                l_eqtypeLockedAssetDetailsParams.setAssetId(l_lngAssetID);
                l_eqtypeLockedAssetDetailsParams.setAccountId(l_lngAccountId);
                l_eqtypeLockedAssetDetailsParams.setSubAccountId(l_lngSubAccountId);
                l_eqtypeLockedAssetDetailsParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_eqtypeLockedAssetDetailsParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_eqtypeLockedAssetDetailsParams.setLockedQuantity(l_dblAdjustQuantity);

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doInsertQuery(l_eqtypeLockedAssetDetailsParams);
            }
            else
            {
                double l_dblNewLockedQuantity = l_dblAdjustQuantity + l_eqtypeLockedAssetDetailsParams.getLockedQuantity();
                LockedAssetDetailsParams l_lockedAssetDetailsParams = new LockedAssetDetailsParams();
                GtlUtils.copyRow2Params(l_eqtypeLockedAssetDetailsParams, l_lockedAssetDetailsParams);
                l_lockedAssetDetailsParams.setAssetId(l_lngAssetID);
                l_lockedAssetDetailsParams.setAccountId(l_lngAccountId);
                l_lockedAssetDetailsParams.setSubAccountId(l_lngSubAccountId);
                l_lockedAssetDetailsParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_lockedAssetDetailsParams.setLockedQuantity(l_dblNewLockedQuantity);
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_lockedAssetDetailsParams);
            }

        }
        catch (NotFoundException l_nfe)
        {
            String l_strMessage = "ロック中数量をupdateできません";
            log.error(l_strMessage, l_nfe);
            throw new RuntimeSystemException(l_strMessage, l_nfe);
        }
        catch (DataNetworkException l_dne)
        {
            String l_strMessage = "ロック中数量をupdateできません";
            log.error(l_strMessage, l_dne);
            throw new RuntimeSystemException(l_strMessage, l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            String l_strMessage = "ロック中数量をupdateできません";
            log.error(l_strMessage, l_dqe);
            throw new RuntimeSystemException(l_strMessage, l_dqe);
        }
        catch (WEB3BaseException l_be)
        {
            String l_strMessage = "ロック中数量をupdateできません";
            log.error(l_strMessage, l_be);
            throw new RuntimeSystemException(l_strMessage, l_be);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get売付可能保有資産一覧)<BR>
     * <BR>
     * 指定条件に一致する売付可能な保有資産オブジェクトの一覧を返却する。<BR>
     * 保有資産一覧に利用する。<BR>
     * <BR>
     * １）　@戻り値オブジェクトのインスタンスを生成する。<BR>
     * <BR>
     * ２）　@検索条件を追加する。<BR>
     * <BR>
     * ２－１）　@引数.検索条件文字列の先頭に、<BR>
     * 　@"口座ID = ?　@and　@補助口座ID = ? and 銘柄タイプ = ? and ミニ株区分 = 0：DEFAULT（ミニ株でない）  and 数量 > 0"<BR>
     * 　@を付加する。<BR>
     * <BR>
     * ２－２）　@引数.検索条件データコンテナの先頭に、<BR>
     * 　@　@　@　@　@検索条件文字列先頭に付加したパラメータリストを追加する。<BR>
     * 　@　@　@　@　@※口座ID、補助口座IDは、引数の補助口座オブジェクトより設定する。<BR>
     * 　@　@　@　@　@※銘柄タイプは、引数の銘柄タイプより設定する。<BR>
     * <BR>
     * ３）　@QueryProcessor.doFindAllQuery( )により、保有資産オブジェクトのListを取得する。<BR>
     * <BR>
     * 　@　@　@doFindAllQuery(,保有資産Row.TYPE<BR>
     *                                      ２－１）の検索条件文字列,<BR>
     *                                      引数のソート条件,<BR>
     *                                      null,<BR>
     *                                      ２－２）の検索条件データコンテナ)<BR>
     * <BR>
     * ４）　@検索結果を返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ（ProductTypeEnumオブジェクト）<BR>
     * @@param l_strSearchString - (検索条件文字列)<BR>
     * 検索条件文字列オブジェクト<BR>
     * @@param l_searchCondContainers - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナオブジェクト<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 413BED52010F
     */
    public List getSellableAssets(
        SubAccount l_subAccount,
        ProductTypeEnum l_productType,
        String l_strSearchString,
        String[] l_searchCondContainers,
        String l_strSortCond)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCanSellAssets(SubAccount, ProductTypeEnum,String, String[] ,String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        //検索条件文字列
        String l_strWhere = "account_id = ? and sub_account_id = ? and product_type = ? and mini_stock_div = ? and quantity > 0";
        List l_lisResults = null;
        if (l_strSearchString != null && l_strSearchString.length() > 0)
        {
            l_strWhere += " and " + l_strSearchString;
        }
        try
        {
            int l_intCondParamLen = 0;
            if (l_searchCondContainers != null)
            {
                l_intCondParamLen = l_searchCondContainers.length;
            }

            Object[] l_objBinds = new Object[4 + l_intCondParamLen];
            l_objBinds[0] = new Long(l_subAccount.getAccountId());
            l_objBinds[1] = new Long(l_subAccount.getSubAccountId());
            l_objBinds[2] = l_productType;
            l_objBinds[3] = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;
            for (int i = 0; i < l_intCondParamLen; i++)
            {
                l_objBinds[4 + i] = l_searchCondContainers[i];
            }

            QueryProcessor l_qp = null;

            l_qp = Processors.getDefaultProcessor();
            List l_lisAssets = l_qp.doFindAllQuery(AssetRow.TYPE, l_strWhere, l_strSortCond, null, l_objBinds);
            int l_assetCount = l_lisAssets.size();
            l_lisResults = new ArrayList(l_assetCount);
            for (int i = 0; i < l_assetCount; i++)
            {
                l_lisResults.add(new WEB3EquityAsset((AssetRow)l_lisAssets.get(i)));
            }
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, e.getMessage(), e);
        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, e.getMessage(), e);
        }
        catch (IllegalStateException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, e.getMessage(), e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisResults;
    }

    /**
     * EqtypeAssetRowオブジェクトから資産クラスオブジェクトを作成する。<BR>
     * <BR>
     * @@param l_row EqtypeAssetRowオブジェクト
     * @@return Asset
     * @@roseuid 4042EC7C01AC
     */
    public Asset toAsset(Row l_row)
    {
        final String STR_METHOD_NAME = "toAsset";
        log.entering(STR_METHOD_NAME);
        if (l_row == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return new WEB3EquityAsset((AssetRow) l_row);
    }

    /**
     * (get建株)<BR>
     * 【建株テーブル】から、引数の建株IDに該当する建株オブジェクト（*）<BR>
     * を取得し返却する。<BR>
     * （getContract(建株ID)のオーバーライド）<BR>
     * <BR>
     * （*）返却値：xTradeのEqTypeContractImplを拡張して定義した、<BR>
     * 建株オブジェクト。<BR>
     * @@param l_lngContractId - 建株ID。
     * @@return Contract
     * @@throws NotFoundException
     * @@roseuid 40BBEBC1030B
     */
    public Contract getContract(long l_lngContractId) throws NotFoundException
    {
        final String STR_METHOD_NAME = "getContract";
        log.entering(STR_METHOD_NAME);
        WEB3EquityContract l_contract = null;
        try
        {
            l_contract = new WEB3EquityContract(l_lngContractId);
        }
        catch (DataQueryException l_dqe)
        {
            String l_strMessage = "建株テーブルを検索 error";
            log.error(l_strMessage, l_dqe);
            throw new RuntimeSystemException("建株テーブルを検索 error", l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            String l_strMessage = "建株テーブルを検索 error";
            log.error(l_strMessage, l_dne);
            throw new RuntimeSystemException("建株テーブルを検索 error", l_dne);
        }

        log.exiting(STR_METHOD_NAME);
        return l_contract;
    }

    /**
     * (adjust返済指定情報)<BR>
     * 返済指定情報の数量を調整する。 <BR>
     * <BR>
     * 下の様に返済指定情報の返済数量を調整する。 <BR>
     * －数量減の場合、返済連番：大→小の順に、調整数量分を <BR>
     * 　@返済割当数量のうちの未約定数量から減算していく。 <BR>
     * －数量増の場合、返済連番：小→大の順に、調整数量分を <BR>
     * 　@訂正前の返済割当数量（市場確認済返済数量）を上限値として <BR>
     * 　@訂正時の返済割当数量（返済注文数量）に加算していく。 <BR>
     * <BR>
     * １）　@調整数量を計算する。 <BR>
     * <BR>
     * 調整数量 = （パラメータ.訂正前数量　@－　@パラメータ.訂正後数量） <BR>
     * <BR>
     * ２）　@返済指定情報読込 <BR>
     * 　@建株返済指定情報テーブルを読み、<BR>
     * 建株返済指定情報行オブジェクトを取得する。 <BR>
     * <BR>
     * [検索条件] <BR>
     * 建株返済指定情報.口座ＩＤ = パラメータ.口座ＩＤ <BR>
     * 建株返済指定情報.補助口座ＩＤ = パラメータ.補助口座ＩＤ <BR>
     * 建株返済指定情報.注文ＩＤ = パラメータ.注文ＩＤ <BR>
     * 建株返済指定情報.注文単位ＩＤ = パラメータ.注文単位ＩＤ <BR>
     * <BR>
     * ※取得順 ･･･ 返済連番の昇順（asc） <BR>
     * <BR>
     * ３）　@取得した建株返済指定情報の返済注文数量を調整する。 <BR>
     * <BR>
     * ３－１）　@調整数量≧０の場合（返済数量の割当てを減らす場合） <BR>
     * 　@調整数量を建株返済指定情報の未約定数量に割り当てる。 <BR>
     * 　@取得した建株返済指定情報行オブジェクトに全て対し返済連番の降順に以下処理を行う。 <BR>
     * 　@（※配列の後から） <BR>
     * <BR>
     * 　@３－１－１）　@未約定数量算出 <BR>
     * 　@　@未約定数量 = 建株返済指定情報.返済注文数量 － 建株返済指定情報.返済約定数量 <BR>
     * <BR>
     * 　@３－１－２）　@調整数量割り当て <BR>
     * 　@　@[（未約定数量 >= 調整数量）の場合] <BR>
     * 　@　@　@・（建株返済指定情報.返済注文数量 － 調整数量）の値を建株返済指定情報.返済注文数量にセットする。 <BR>
     * 　@　@　@※LOOP処理を終了する。 （調整数量の割当て完了） <BR>
     * <BR>
     * 　@　@[（未約定数量 < 調整数量）の場合] <BR>
     * 　@　@　@・建株返済指定情報.返済約定数量を建株返済指定情報.返済注文数量にセットする。 <BR>
     * 　@　@　@・調整数量 = 調整数量 - 未約定数量 <BR>
     * <BR>
     * ３－２）そうでない場合（返済数量の割当てを増やす場合） <BR>
     * 　@市場確認済返済数量を上限として返済注文数量に調整数量を割り当てる。 <BR>
     * <BR>
     * 　@３－２－１）調整数量の符号を反転させる。 <BR>
     * <BR>
     * 　@取得した建株返済指定情報行オブジェクトに全て対し返済連番の昇順に以下処理を行う。 <BR>
     * 　@（※配列の前から） <BR>
     * <BR>
     * 　@　@３－２－２－１）　@調整可能数量を算出する <BR>
     * 　@　@　@調整可能数量＝建株返済指定情報.市場確認済返済数量 － 建株返済指定情報.返済注文数量 <BR>
     * <BR>
     * 　@　@３－２－２－２）調整不可能な場合（調整可能数量≦０の場合） <BR>
     * 　@　@　@次の返済指定情報の処理へ <BR>
     * <BR>
     * 　@　@３－２－２－３）　@返済注文数量に調整数量を割当てる <BR>
     * 　@　@　@[（調整可能数量 ≧ 調整数量）の場合] <BR>
     * 　@　@　@　@・（建株返済指定情報.返済注文数量 ＋ 調整数量）を建株返済指定情報.返済注文数量にセットする。 <BR>
     * 　@　@　@　@※LOOP処理を終了する。 （調整数量の割当て完了） <BR>
     * <BR>
     * 　@　@　@[（調整可能数量 ＜ 調整数量）の場合] <BR>
     * 　@　@　@　@・（建株返済指定情報.返済注文数量 ＋ 調整可能数量）を建株返済指定情報.返済注文数量にセットする。 <BR>
     * 　@　@　@　@・調整数量 = 調整数量 - 調整可能数量 <BR>
     * <BR>
     * ４）　@建株返済指定情報更新 <BR>
     * 　@調整後の建株返済指定情報行オブジェクトの内容でDBを更新（update）する。 <BR>
     * <BR>
     * @@param l_lngAccountId - 口座ＩＤ
     * @@param l_lngSubAccountId - 補助口座ID
     * @@param l_lngOrderId - 注文ID
     * @@param l_lngOrderUnitId - 注文単位ID
     * @@param l_dblBeforeChangingQuantity - 訂正前数量
     * @@param l_dblChangedQuantity - 訂正後数量
     * @@throws WEB3BaseException
     * @@roseuid 40F7B7F200A3
     */
    public void adjustClosingContractSpecInfo(long l_lngAccountId, long l_lngSubAccountId, long l_lngOrderId, long l_lngOrderUnitId, double l_dblBeforeChangingQuantity, double l_dblChangedQuantity)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "adjustCloseContractSpecInfo";
        log.entering(STR_METHOD_NAME);

        try
        {
            // 調整数量を計算する。
            double l_dblAdjustQuantity = l_dblBeforeChangingQuantity - l_dblChangedQuantity;
            
            // 返済指定情報の一覧を取得する。
            String l_strWhere = " account_id = ? and sub_account_id = ? and order_id = ? and order_unit_id = ? ";
            String l_strOrderBy = "closing_serial_no asc";
            Object[] l_objWhereValues = { new Long(l_lngAccountId), new Long(l_lngSubAccountId), new Long(l_lngOrderId), new Long(l_lngOrderUnitId)};
            List l_returnList = new ArrayList();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_returnList = l_processor.doFindAllQuery(EqtypeClosingContractSpecParams.TYPE, l_strWhere, l_strOrderBy, null, l_objWhereValues);

            EqtypeClosingContractSpecParams l_contractSpecParams = null;
            
            // 返済数量の割当てを減らす場合
            if (l_dblAdjustQuantity >= 0)
            {
                for (int i = l_returnList.size() - 1; i >= 0  ; i--)
                {
                    l_contractSpecParams = new EqtypeClosingContractSpecParams(
                        (EqtypeClosingContractSpecRow) l_returnList.get(i));
                        
                    double l_dblExecuteQuantity = l_contractSpecParams.getExecutedQuantity();
                    double l_dblQuantity = l_contractSpecParams.getQuantity();
                    double l_dblUnExecutedQuantity = l_dblQuantity - l_dblExecuteQuantity;
     
                    if (l_dblUnExecutedQuantity >= l_dblAdjustQuantity)
                    {
                        l_contractSpecParams.setQuantity(l_dblQuantity - l_dblAdjustQuantity);
                        l_processor.doUpdateQuery(l_contractSpecParams);
                        break;
                    }
                    else
                    {
                        l_dblAdjustQuantity = l_dblAdjustQuantity - l_dblUnExecutedQuantity;
                        l_contractSpecParams.setQuantity(l_dblExecuteQuantity);
                    }
                    l_processor.doUpdateQuery(l_contractSpecParams);
                }
            }
            // 返済数量の割当てを増やす場合
            else
            {
                l_dblAdjustQuantity = - l_dblAdjustQuantity;
                for (int i = 0; i < l_returnList.size(); i++)
                {
                    l_contractSpecParams = new EqtypeClosingContractSpecParams(
                        (EqtypeClosingContractSpecRow) l_returnList.get(i));
                        
                    double l_dblConfirmedQuantity = l_contractSpecParams.getConfirmedQuantity();
                    double l_dblQuantity = l_contractSpecParams.getQuantity();
                    double l_dblAssignableQuantity = l_dblConfirmedQuantity - l_dblQuantity;
                    
                    if (l_dblAssignableQuantity <= 0) continue;
                    
                    if (l_dblAssignableQuantity >= l_dblAdjustQuantity)
                    {
                        l_contractSpecParams.setQuantity(l_dblQuantity + l_dblAdjustQuantity);
                        l_processor.doUpdateQuery(l_contractSpecParams);
                        break;
                    }
                    else
                    {
                        l_contractSpecParams.setQuantity(l_dblQuantity + l_dblAssignableQuantity);
                        l_dblAdjustQuantity = l_dblAdjustQuantity - l_dblAssignableQuantity;
                        l_processor.doUpdateQuery(l_contractSpecParams);
                    }
                }
            }

        }
        catch (DataQueryException l_dqe)
        {
            String l_strMessage = "建株返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_dqe);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            String l_strMessage = "建株返済指定情報テーブルを検索  error";
            log.error(l_strMessage, l_dne);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_dne.getMessage(), l_dne);
        }
    }

    /**
     * (create決済中建株情報)<BR>
     * 決済中の建株情報を1明細作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用残高）create決済中建株情報」参照。<BR>
     * @@param l_contract - (建株)<BR>
     * 建株情報を作成する対象の建株
     * @@return WEB3MarginContractInfo
     * @@throws WEB3BaseException
     * @@roseuid 40EB4E960071
     */
    public WEB3MarginContractInfo createClosingMarginContractInfo(WEB3EquityContract l_contract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createClosingMarginContractInfo";
        log.entering(STR_METHOD_NAME);

        //信用取引建株情報オブジェクトを生成する。
        if (l_contract == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        WEB3MarginContractInfo l_marginContractInfo = new WEB3MarginContractInfo();

        //決済注文中数量を取得する。
        double l_dblLockedQuantity = l_contract.getLockedQuantity();

        //建株IDを取得する。
        long l_lngContractId = l_contract.getContractId();

        //決済注文中の注文単位の一覧を取得する。
		EqTypeClosingContractSpec[] l_constractSpec = this.getContractOrderingClosingContractSpecInfo(l_lngContractId);
        
        double l_dblEvaluateIncome = 0;
        double l_dblEvaluateIncomeWithCost = 0;
        //注文単位要素ごとのLoop処理
        int l_intOrderUnitLength = 0;
        if (l_constractSpec != null)
        {
            l_intOrderUnitLength = l_constractSpec.length;
        }
        
		//該当建株銘柄の時価を取得する。
		double l_dblCurrentPrice = 0D;
        try
        {
			l_dblCurrentPrice = this.getContractCurrentPrice(l_contract);
        }
        catch (WEB3BaseException l_be)
        {
			log.error("時価取得エラー: " + STR_METHOD_NAME, l_be);
        }
		
		if (l_dblCurrentPrice != 0D)
		{
	        for (int i = 0; i < l_intOrderUnitLength; i++)
	        {
	            //注文数量を取得する。
	            double l_dblQuantity = l_constractSpec[i].getConfirmedQuantity();
	            if (Double.isNaN(l_dblQuantity))
	            {
	                l_dblQuantity = l_constractSpec[i].getQuantity();
	            }
	            
	            //返済約定数量を取得する。
	            double l_dblExecQuantity = l_constractSpec[i].getExecutedQuantity();
				if (Double.isNaN(l_dblExecQuantity))
	            {
					l_dblExecQuantity = 0d;
	            }
	            
				// 該当返済指定情報分の決済中数量を取得する。
				double l_dblOrderingQuantity = l_dblQuantity - l_dblExecQuantity;
                
				//評価損益を取得する。
				l_dblEvaluateIncome += l_contract.getAppraisalProfitOrLoss(
                    l_dblCurrentPrice, l_dblOrderingQuantity);
	        }
			//(*)信用取引建株情報オブジェクトに以下の通り、プロパティをセットする。
			//評価損益：　@(建株.get評価損益( )の戻り値の合計値)
			l_marginContractInfo.evaluationIncome = WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncome);
		}
		else
		{
			//時価が取得できなかった場合はnullをセット
			l_marginContractInfo.evaluationIncome = null;
			l_marginContractInfo.takeExpensesOffEvaluationIncome = null;
		}

        EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) l_contract.getDataSourceObject();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //(*)信用取引建株情報オブジェクトに以下の通り、プロパティをセットする。
        //ID：　@建株.建株ID
        l_marginContractInfo.id = "" + l_contract.getContractId();

        //市場コード：　@拡張金融オブジェクトマネージャ.getMarket(建株.銘柄ID).市場コード
        WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        try
        {
            WEB3EquityProduct l_equityProduct = (WEB3EquityProduct) l_equityProductManager.getProduct(l_eqtypeContractRow.getProductId());

            //銘柄コード：　@株式銘柄(**).銘柄コード
            //銘柄名：　@株式銘柄(**).銘柄名
            l_marginContractInfo.productCode = l_equityProduct.getProductCode();
            EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_equityProduct.getDataSourceObject(); 
            l_marginContractInfo.standardName = l_eqtypeProductRow.getStandardName();
            //市場コード：　@拡張金融オブジェクトマネージャ.getMarket(
            l_marginContractInfo.marketCode = l_finApp.getFinObjectManager().getMarket(l_contract.getMarketId()).getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        //口座区分：　@
        if (TaxTypeEnum.NORMAL.equals(l_eqtypeContractRow.getTaxType()))
        {
            //　@建株.税区分==”一般”の場合、”一般”をセット。
            l_marginContractInfo.accountType = WEB3TaxTypeDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_eqtypeContractRow.getTaxType()) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_eqtypeContractRow.getTaxType()))
        {
            //建株.税区分==（”特定”、または、”特定口座かつ源泉徴収”）の場合、”特定”をセット。
            l_marginContractInfo.accountType = WEB3TaxTypeDef.SPECIAL;
        }
        //建区分：　@建株.建区分
        if (ContractTypeEnum.LONG.equals(l_eqtypeContractRow.getContractType()))
        {
            l_marginContractInfo.contractType = "" + ContractTypeEnum.LONG.intValue();
        }
        else if (ContractTypeEnum.SHORT.equals(l_eqtypeContractRow.getContractType()))
        {
            l_marginContractInfo.contractType = "" + ContractTypeEnum.SHORT.intValue();
        }
        //弁済区分：　@建株.弁済区分
        l_marginContractInfo.repaymentType = l_eqtypeContractRow.getRepaymentType();
        //弁済期限値：　@建株.弁済期限値
        l_marginContractInfo.repaymentNum = "" + l_eqtypeContractRow.getRepaymentNum();
        //建日：　@建株.建日
        l_marginContractInfo.openDate = WEB3DateUtility.toDay(l_contract.getOpenDate());
        //建単価：　@建株.建単価
        l_marginContractInfo.contractPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
        //建株数：　@建株.getLockedQuantity( )
        l_marginContractInfo.quantity = WEB3StringTypeUtility.formatNumber(l_contract.getLockedQuantity());
        //期日：　@建株.期日
        l_marginContractInfo.closeDate = WEB3DateUtility.toDay(l_contract.getCloseDate());
        //建代金：　@建株.get建代金( )の戻り値
        l_marginContractInfo.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractAmount(l_dblLockedQuantity));
        //決済状態区分：　@”決済中”　@※決済中＝”2”
        //返済可能フラグ：　@false(固定)
        //現引現渡可能フラグ： false(固定)
        l_marginContractInfo.closingStatusType = WEB3MarginClosingStatusTypeDef.SETTLING;
        l_marginContractInfo.closingPossibleFlag = false;
        l_marginContractInfo.swapPossibleFlag = false;
        //建手数料：　@(建株.建手数料 + 建株.建手数料消費税)
        double l_dblSetupFee = l_contract.getSetupFee(l_dblLockedQuantity);
        double l_dblSetupFeeTax = l_contract.getSetupFeeTax(l_dblLockedQuantity);
        l_marginContractInfo.setupFee = WEB3StringTypeUtility.formatNumber(
            l_dblSetupFee
            + l_dblSetupFeeTax);
        //順日歩：　@建株.順日歩
        double l_dblInterestFee = l_contract.getInterestFee(l_dblLockedQuantity);
        l_marginContractInfo.interestFee = WEB3StringTypeUtility.formatNumber(
            l_dblInterestFee);
        //逆日歩：　@建株.逆日歩
        double l_dblPayInterestFee = l_contract.getPayInterestFee(l_dblLockedQuantity);
        l_marginContractInfo.payInterestFee = WEB3StringTypeUtility.formatNumber(
            l_dblPayInterestFee);
        //貸株料：　@建株.貸株料
        double l_dblLoanEquityFee = l_contract.getLoanEquityFee(l_dblLockedQuantity);
        l_marginContractInfo.loanEquityFee = WEB3StringTypeUtility.formatNumber(
            l_dblLoanEquityFee);
        //書換料：　@(建株.名義書換料 + 建株.名義書換料消費税)
        double l_dblNameTransferFee = l_contract.getNameTransferFee(l_dblLockedQuantity);
        double l_dblNameTransferFeeTax = l_contract.getNameTransferFeeTax(l_dblLockedQuantity);
        l_marginContractInfo.transferFee = WEB3StringTypeUtility.formatNumber(
            l_dblNameTransferFee
            + l_dblNameTransferFeeTax);
        //管理費：　@(建株.管理費 + 建株.管理費消費税)
        double l_dblManagementFee = l_contract.getManagementFee(l_dblLockedQuantity);
        double l_dblManagementFeeTax = l_contract.getManagementFeeTax(l_dblLockedQuantity);
        l_marginContractInfo.managementFee = WEB3StringTypeUtility.formatNumber(
            l_dblManagementFee
            + l_dblManagementFeeTax);
        //その他：　@建株.その他
        double l_dblOther = l_contract.getOther(l_dblLockedQuantity);
        l_marginContractInfo.other = WEB3StringTypeUtility.formatNumber(
            l_dblOther);

        if (l_dblCurrentPrice != 0D)
        {
	        WEB3EquityBizLogicProvider l_bizLogic =
	            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
	        // 諸経費合計
	        double l_dblTotalCost = l_bizLogic.calcExpenses(0,
	            0,
	            l_dblSetupFee,
	            l_dblSetupFeeTax,
	            l_dblNameTransferFee,
	            l_dblNameTransferFeeTax,
	            l_dblManagementFee,
	            l_dblManagementFeeTax,
	            l_dblInterestFee,
	            l_dblPayInterestFee,
	            l_dblLoanEquityFee,
	            l_dblOther,
	            l_eqtypeContractRow.getContractType());
	        l_dblEvaluateIncomeWithCost = l_dblEvaluateIncome - l_dblTotalCost;
	        // 評価損益（諸経費考慮）
	        l_marginContractInfo.takeExpensesOffEvaluationIncome =
	            WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncomeWithCost);
        }
        
        log.exiting(STR_METHOD_NAME);
        //プロパティセットした信用取引建株情オブジェクトを返却する。
        return l_marginContractInfo;
    }

    /**
     * (create未決済建株情報)<BR>
     * 未決済の建株情報を1明細作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用残高）create未決済建株情報」参照。<BR>
     * @@param l_contract - (建株)<BR>
     * 建株情報を作成する対象の建株
     * @@return WEB3MarginContractInfo
     * @@throws WEB3BaseException
     * @@roseuid 40EB4E880217
     */
    public WEB3MarginContractInfo createUnCloseMarginContractInfo(WEB3EquityContract l_contract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createUnCloseMarginContractInfo";
        log.entering(STR_METHOD_NAME);
        if (l_contract == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }

        //信用取引建株情報オブジェクトを生成する。
        WEB3MarginContractInfo l_marginContractInfo = new WEB3MarginContractInfo();

        //建株数を取得する。
        double l_dblContractQuantity = l_contract.getQuantity();

        //決済注文中数量を取得する。
        double l_dblLockedQuantity = l_contract.getLockedQuantity();
        
        //当該建の未決済数量（決済中の数量を除いた数量）を取得する。
		double l_dblUnCloseContractQuantity = l_dblContractQuantity - l_dblLockedQuantity;

        //建株時価を取得する。
		double l_dblCurrentPrice = 0D;
        try
        {
			l_dblCurrentPrice = this.getContractCurrentPrice(l_contract);
        }
        catch (WEB3BaseException l_be)
        {
			log.error("時価取得エラー: " + STR_METHOD_NAME, l_be);
        }

        EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) l_contract.getDataSourceObject();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //建代金を取得する。
        double l_dblContractPrice = l_contract.getContractAmount(l_dblUnCloseContractQuantity);

        //(*)信用取引建株情報オブジェクトに以下の通り、プロパティをセットする。
        //ID：　@建株.建株ID
        l_marginContractInfo.id = "" + l_contract.getContractId();

        //市場コード：　@拡張金融オブジェクトマネージャ.getMarket(建株.銘柄ID).市場コード
        WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        try
        {
            WEB3EquityProduct l_equityProduct = (WEB3EquityProduct) l_equityProductManager.getProduct(l_eqtypeContractRow.getProductId());

            //銘柄コード：　@株式銘柄(**).銘柄コード
            //銘柄名：　@株式銘柄(**).銘柄名
            l_marginContractInfo.productCode = l_equityProduct.getProductCode();
            EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_equityProduct.getDataSourceObject(); 
            l_marginContractInfo.standardName = l_eqtypeProductRow.getStandardName();
            //市場コード：　@拡張金融オブジェクトマネージャ.getMarket(
            l_marginContractInfo.marketCode = l_finApp.getFinObjectManager().getMarket(l_contract.getMarketId()).getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        //口座区分：　@
        if (TaxTypeEnum.NORMAL.equals(l_eqtypeContractRow.getTaxType()))
        {
            //　@建株.税区分==”一般”の場合、”一般”をセット。
            l_marginContractInfo.accountType = WEB3TaxTypeDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_eqtypeContractRow.getTaxType()) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_eqtypeContractRow.getTaxType()))
        {
            //建株.税区分==（”特定”、または、”特定口座かつ源泉徴収”）の場合、”特定”をセット。
            l_marginContractInfo.accountType = WEB3TaxTypeDef.SPECIAL;
        }
        //建区分：　@建株.建区分
        if (ContractTypeEnum.LONG.equals(l_eqtypeContractRow.getContractType()))
        {
            l_marginContractInfo.contractType = "" + ContractTypeEnum.LONG.intValue();
        }
        else if (ContractTypeEnum.SHORT.equals(l_eqtypeContractRow.getContractType()))
        {
            l_marginContractInfo.contractType = "" + ContractTypeEnum.SHORT.intValue();
        }

        //弁済区分：　@建株.弁済区分
        l_marginContractInfo.repaymentType = l_eqtypeContractRow.getRepaymentType();
        //弁済期限値：　@建株.弁済期限値
        l_marginContractInfo.repaymentNum = "" + l_eqtypeContractRow.getRepaymentNum();
        //建日：　@建株.建日
        l_marginContractInfo.openDate = WEB3DateUtility.toDay(l_contract.getOpenDate());
        //建単価：　@建株.建単価
        l_marginContractInfo.contractPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
        //建株数：　@当該建の未決済数量（== (建株.getQuantity( ) - 建株.getLockedQuantity( ))）
        l_marginContractInfo.quantity = WEB3StringTypeUtility.formatNumber(l_dblUnCloseContractQuantity);
        //期日：　@建株.期日
        l_marginContractInfo.closeDate = WEB3DateUtility.toDay(l_contract.getCloseDate());
        //建代金：　@建株.get建代金( )の戻り値
        l_marginContractInfo.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
        //決済状態区分：　@”未決済”　@※未決済＝”1”
        l_marginContractInfo.closingStatusType = WEB3MarginClosingStatusTypeDef.HAVE_NOT_SETTLED;
        //建手数料：　@(建株.建手数料 + 建株.建手数料消費税)
        double l_dblSetupFee = l_contract.getSetupFee(l_dblContractQuantity) - l_contract.getSetupFee(l_dblLockedQuantity);
        double l_dblSetupFeeTax = l_contract.getSetupFeeTax(l_dblContractQuantity) - l_contract.getSetupFeeTax(l_dblLockedQuantity);
        l_marginContractInfo.setupFee = WEB3StringTypeUtility.formatNumber(
            l_dblSetupFee
            + l_dblSetupFeeTax);
        //順日歩：　@建株.順日歩
        double l_dblInterestFee = l_contract.getInterestFee(l_dblContractQuantity) - l_contract.getInterestFee(l_dblLockedQuantity);
        l_marginContractInfo.interestFee = WEB3StringTypeUtility.formatNumber(
            l_dblInterestFee);
        //逆日歩：　@建株.逆日歩
        double l_dblPayInterestFee = l_contract.getPayInterestFee(l_dblContractQuantity) - l_contract.getPayInterestFee(l_dblLockedQuantity);
        l_marginContractInfo.payInterestFee = WEB3StringTypeUtility.formatNumber(
            l_dblPayInterestFee);
        //貸株料：　@建株.貸株料
        double l_dblLoanEquityFee = l_contract.getLoanEquityFee(l_dblContractQuantity) - l_contract.getLoanEquityFee(l_dblLockedQuantity);
        l_marginContractInfo.loanEquityFee = WEB3StringTypeUtility.formatNumber(
            l_dblLoanEquityFee);
        //書換料：　@(建株.名義書換料 + 建株.名義書換料消費税)
        double l_dblNameTransferFee = l_contract.getNameTransferFee(l_dblContractQuantity) - l_contract.getNameTransferFee(l_dblLockedQuantity);
        double l_dblNameTransferFeeTax = l_contract.getNameTransferFeeTax(l_dblContractQuantity) - l_contract.getNameTransferFeeTax(l_dblLockedQuantity);
        l_marginContractInfo.transferFee = WEB3StringTypeUtility.formatNumber(
            l_dblNameTransferFee
            + l_dblNameTransferFeeTax);
        //管理費：　@(建株.管理費 + 建株.管理費消費税)
        double l_dblManagementFee = l_contract.getManagementFee(l_dblContractQuantity) - l_contract.getManagementFee(l_dblLockedQuantity);
        double l_dblManagementFeeTax = l_contract.getManagementFeeTax(l_dblContractQuantity) - l_contract.getManagementFeeTax(l_dblLockedQuantity);
        l_marginContractInfo.managementFee = WEB3StringTypeUtility.formatNumber(
            l_dblManagementFee
            + l_dblManagementFeeTax);
        //その他：　@建株.その他
        double l_dblOther = l_contract.getOther(l_dblContractQuantity) - l_contract.getOther(l_dblLockedQuantity);
        l_marginContractInfo.other = WEB3StringTypeUtility.formatNumber(
            l_dblOther);
        
        if (l_dblCurrentPrice != 0D)
        {
            //評価損益を取得する。
            double l_dblEvaluateIncome = l_contract.getAppraisalProfitOrLoss(
                l_dblCurrentPrice, l_dblUnCloseContractQuantity);
            
            WEB3EquityBizLogicProvider l_bizLogic =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            // 諸経費合計
            double l_dblTotalCost = l_bizLogic.calcExpenses(0,
                0,
                l_dblSetupFee,
                l_dblSetupFeeTax,
                l_dblNameTransferFee,
                l_dblNameTransferFeeTax,
                l_dblManagementFee,
                l_dblManagementFeeTax,
                l_dblInterestFee,
                l_dblPayInterestFee,
                l_dblLoanEquityFee,
                l_dblOther,
                l_eqtypeContractRow.getContractType());
            //評価損益（諸経費考慮）を取得する。
            double l_dblEvaluateIncomeWithCost = l_dblEvaluateIncome - l_dblTotalCost;

            //(*)信用取引建株情報オブジェクトに以下の通り、プロパティをセットする。
            //評価損益：　@建株.get評価損益( )の戻り値
            l_marginContractInfo.evaluationIncome =
                WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncome);
            //評価損益(諸経費考慮)：　@建株.get評価損益( )の戻り値 - calc諸経費()の戻り値
            l_marginContractInfo.takeExpensesOffEvaluationIncome =
                WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncomeWithCost);
        }
        else
        {
            //時価が取得できなかった場合はnullをセット
            l_marginContractInfo.evaluationIncome = null;
            l_marginContractInfo.takeExpensesOffEvaluationIncome = null;
        }

        log.exiting(STR_METHOD_NAME);
        //プロパティセットした信用取引建株情報オブジェクトを返却する。
        return l_marginContractInfo;
    }

    /**
     * (create決済済建株情報)<BR>
     * 当日決済済の建株情報を1明細作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用残高）create決済済建株情報」参照。<BR>
     * @@param l_contract - (建株)<BR>
     * 建株情報を作成する対象の建株
     * @@return WEB3MarginContractInfo
     * @@throws WEB3BaseException
     * @@roseuid 40EB4E65013C
     */
    public WEB3MarginContractInfo createClosedMarginContractInfo(WEB3EquityContract l_contract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createClosedMarginContractInfo";
        log.entering(STR_METHOD_NAME);

        if (l_contract == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        //信用取引建株情報オブジェクトを生成する。
        WEB3MarginContractInfo l_marginContractInfo = new WEB3MarginContractInfo();

        //建株IDを取得する。
        long l_lngContractId = l_contract.getContractId();

        //発注日を取得する。
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //当日決済済の約定数量を取得する。
        double l_dblCloseExecQuantity = this.getDayCloseContractExecutedQuantity(l_lngContractId, GtlUtils.getSystemTimestamp());

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityFinTransactionManager l_finTransactionManager = (WEB3EquityFinTransactionManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getFinTransactionManager();
        //株式顧客勘定明細の一覧を取得する。
        List l_lstTransaction = l_finTransactionManager.getTransactions(l_lngContractId, GtlUtils.getSystemTimestamp());

        EqtypeFinTransactionRow[] l_eqtyFinTransactionRow = new EqtypeFinTransactionRow[l_lstTransaction.size()];
        l_lstTransaction.toArray(l_eqtyFinTransactionRow);

        //受渡代金
        double l_dblNetAmount = 0D;

        //建手数料
        double l_dblSetupFee = 0D;

        //順日歩
        double l_dblInterestFee = 0D;

        //逆日歩
        double l_dblPayInterestFee = 0D;

        //貸株料
        double l_dblLoanEquityFee = 0D;

        //書換料
        double l_dblTransferFee = 0D;

        //管理費
        double l_dblManagementFee = 0D;

        //その他
        double l_dblOther = 0D;

        //顧客勘定明細(getトランザクションの戻り値)要素ごとのLoop処理
        int l_tracsactionRowLength = 0;
        if (l_eqtyFinTransactionRow != null)
        {
            l_tracsactionRowLength = l_eqtyFinTransactionRow.length;
        }
        for (int i = 0; i < l_tracsactionRowLength; i++)
        {
            //トランザクションカテゴリを取得する。
            FinTransactionCateg l_finTransactionCateg = l_eqtyFinTransactionRow[i].getFinTransactionCateg();

            //（対象カテゴリ判定)
            //返済、現引現渡以外の場合は、以降のLoop内処理は実施しない。(continue;)
            //(株式顧客勘定明細.getFinTransactionCateg( ) != (”返済取引”、または”現引・現渡取引”))
            if (FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_finTransactionCateg) || FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_finTransactionCateg))
            {
                //評価損益＝評価損益 + 株式顧客勘定明細.受
                l_dblNetAmount += l_eqtyFinTransactionRow[i].getNetAmount();

                //建手数料＝建手数料 + 株式顧客勘定明細.建手数料 + 株式顧客勘定明細.建手数料消費税
                l_dblSetupFee += (l_eqtyFinTransactionRow[i].getImportedSetupFee() + l_eqtyFinTransactionRow[i].getImportedSetupFeeTax());

                //順日歩＝順日歩 + 株式顧客勘定明細.順日歩
                l_dblInterestFee += l_eqtyFinTransactionRow[i].getImportedInterestFee();

                //逆日歩＝逆日歩 + 株式顧客勘定明細.逆日歩
                l_dblPayInterestFee += l_eqtyFinTransactionRow[i].getImportedPayInterestFee();

                //貸株料＝貸株料 + 株式顧客勘定明細
                l_dblLoanEquityFee += l_eqtyFinTransactionRow[i].getImportedLoanEquityFee();

                //書換料＝書換料 + 株式顧客勘定明細.名義書換料 + 株式顧客勘定明細.名義書換料消費税
                l_dblTransferFee += (l_eqtyFinTransactionRow[i].getImportedNameTransferFee() + l_eqtyFinTransactionRow[i].getImportedNameTransferFeeTax());

                //管理費＝管理費 + 株式顧客勘定明細.管理費 + 株式顧客勘定明細.管理費消費税
                l_dblManagementFee += (l_eqtyFinTransactionRow[i].getImportedManagementFee() + l_eqtyFinTransactionRow[i].getImportedManagementFeeTax());

                //その他＝その他 + 株式顧客勘定明細.その他
                l_dblOther += l_eqtyFinTransactionRow[i].getImportedOther();
            }
        }

        EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) l_contract.getDataSourceObject();

        //(*)信用取引建株情報オブジェクトに以下の通り、プロパティをセットする。
        //ID：　@建株.建株ID
        l_marginContractInfo.id = "" + l_contract.getContractId();

        //(**)拡張プロダクトマネージャ.getProduct(建株.銘柄ID)にて取得
        WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();

        try
        {

            WEB3EquityProduct l_equityProduct = (WEB3EquityProduct) l_equityProductManager.getProduct(l_eqtypeContractRow.getProductId());

            //銘柄コード：　@株式銘柄(**).銘柄コード
            //銘柄名：　@株式銘柄(**).銘柄名
            l_marginContractInfo.productCode = l_equityProduct.getProductCode();
            EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_equityProduct.getDataSourceObject();
            l_marginContractInfo.standardName = l_eqtypeProductRow.getStandardName();

            l_marginContractInfo.marketCode = l_finApp.getFinObjectManager().getMarket(l_contract.getMarketId()).getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        //口座区分：　@
        if (TaxTypeEnum.NORMAL.equals(l_eqtypeContractRow.getTaxType()))
        {
            //建株.税区分==”一般”の場合、”一
            l_marginContractInfo.accountType = WEB3TaxTypeDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_eqtypeContractRow.getTaxType()) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_eqtypeContractRow.getTaxType()))
        {
            //建株.税区分==（”特定”、または、”特定口座かつ源泉徴収”
            l_marginContractInfo.accountType = WEB3TaxTypeDef.SPECIAL;
        }
        //建区分：　@建株.建区
        if (ContractTypeEnum.LONG.equals(l_eqtypeContractRow.getContractType()))
        {
            l_marginContractInfo.contractType = "" + ContractTypeEnum.LONG.intValue();
        }
        else if (ContractTypeEnum.SHORT.equals(l_eqtypeContractRow.getContractType()))
        {
            l_marginContractInfo.contractType = "" + ContractTypeEnum.SHORT.intValue();
        }

        //弁済区分：　@建株.弁済区分
        l_marginContractInfo.repaymentType = l_eqtypeContractRow.getRepaymentType();
        //弁済期限値：　@建株.弁済期限値
        l_marginContractInfo.repaymentNum = "" + l_eqtypeContractRow.getRepaymentNum();
        //建日：　@建株.建日
        l_marginContractInfo.openDate = WEB3DateUtility.toDay(l_contract.getOpenDate());
        //建単価：　@建株.建単価
        l_marginContractInfo.contractPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
        //建株数：　@this.当日決済約定株数( )の
        l_marginContractInfo.quantity = WEB3StringTypeUtility.formatNumber(l_dblCloseExecQuantity);
        //期日：　@建株.期
        l_marginContractInfo.closeDate = WEB3DateUtility.toDay(l_contract.getCloseDate());
        //建代金：　@建株.get建代金(this.当日決済約定株数( )の戻
        l_marginContractInfo.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractAmount(l_dblCloseExecQuantity));
        //評価損益：　@(受渡代金の合計値)
        l_marginContractInfo.evaluationIncome = WEB3StringTypeUtility.formatNumber(l_dblNetAmount);
        //評価損益(諸経費考慮)：　@(受渡代金の
        l_marginContractInfo.takeExpensesOffEvaluationIncome = WEB3StringTypeUtility.formatNumber(l_dblNetAmount);
        //決済状態区分：　@”決済済”　@※決済済＝”0”
        //返済可能フラグ：　@false(固定)
        //現引現渡可能フラグ： false(固
        l_marginContractInfo.closingStatusType = WEB3MarginClosingStatusTypeDef.SETTLEMENT_END;
        l_marginContractInfo.closingPossibleFlag = false;
        l_marginContractInfo.swapPossibleFlag = false;
        //建手数料：　@(建手数料 + 建手数料消費税の合計値)
        l_marginContractInfo.setupFee = WEB3StringTypeUtility.formatNumber(l_dblSetupFee);
        //順日歩：　@(順日歩の合計値)
        l_marginContractInfo.interestFee = WEB3StringTypeUtility.formatNumber(l_dblInterestFee);
        //逆日歩：　@(逆日歩の合計値)
        l_marginContractInfo.payInterestFee = WEB3StringTypeUtility.formatNumber(l_dblPayInterestFee);
        //貸株料：　@(貸株料の合計値)
        l_marginContractInfo.loanEquityFee = WEB3StringTypeUtility.formatNumber(l_dblLoanEquityFee);
        //(名義書換料の合計値)
        l_marginContractInfo.transferFee = WEB3StringTypeUtility.formatNumber(l_dblTransferFee);
        //管理費：　@(管理費の合計値)
        l_marginContractInfo.managementFee = WEB3StringTypeUtility.formatNumber(l_dblManagementFee);
        //その他：　@(その他の合計値)
        l_marginContractInfo.other = WEB3StringTypeUtility.formatNumber(l_dblOther);

        log.exiting(STR_METHOD_NAME);

        //プロパティセットした信用取引建株情報オブジェクトを返却する。
        return l_marginContractInfo;
    }

    /**
     * (get返済指定情報)<BR>
     * 指定した建株IDに該当する返済指定情報の一覧を取得する<BR>
     * <BR>
     * １）　@建株返済指定情報テーブル検索<BR>
     * 　@以下の条件で建株返済指定情報テーブルを検索する<BR>
     * <BR>
     * [検索条件]<BR>
     * 建株ID = 引数.建株ID<BR>
     * <BR>
     * ２）　@１）の取得結果の一覧を返却する<BR>
     * ※該当するデータが存在しない場合にはnullを返却する<BR>
     * @@param l_lngContractId - 建株ID
     * @@return EqTypeClosingContractSpec[]
     * @@throws WEB3BaseException
     * @@roseuid 40E514E90212
     */
    public EqTypeClosingContractSpec[] getClosingContractSpecs(long l_lngContractId) throws WEB3BaseException
    {
        final String l_strMethodName = "getClosingContractSpecs";
        log.entering(l_strMethodName);

        // (1)建株I返済指定情報テーブル検索
        List l_lisReturnValue = new ArrayList();
        // [検索条件]:
        String l_strWhere = " contract_id = ? ";

        Object[] l_objWhereValue = { new Long(l_lngContractId)};

        // QueryProcessor.doFindAllQuery()
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(EqtypeClosingContractSpecRow.TYPE, l_strWhere, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "建株返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "建株返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + l_strMethodName, l_ex.getMessage(), l_ex);
        }

        // (2)(1)の取得結果の一覧を返却する
        if (l_lisReturnValue.size() == 0)
        {
            // --- Test Log
            log.debug("建株返済指定情報テーブル検索の結果がない、nullを 返却する！！！");

            log.exiting(l_strMethodName);
            return null;
        }
        WEB3EquityClosingContractSpec[] l_closingcontractspec = new WEB3EquityClosingContractSpec[l_lisReturnValue.size()];

        ArrayList l_lisReturnList = new ArrayList();
        for (int i = 0; i < l_lisReturnValue.size(); i++)
        {
            EqtypeClosingContractSpecRow l_row = (EqtypeClosingContractSpecRow) l_lisReturnValue.get(i);
            WEB3EquityClosingContractSpec l_impl = new WEB3EquityClosingContractSpec(l_row);
            l_lisReturnList.add(i, l_impl);
        }

        l_lisReturnList.toArray(l_closingcontractspec);

        log.exiting(l_strMethodName);
        return l_closingcontractspec;
    }

    /**
     * (get返済指定情報)<BR>
     * 指定した建株IDと更新日付に該当する返済指定情報の一覧を取得する。<BR>
     * <BR>
     * １）　@建株返済指定情報テーブル検索<BR>
     * 以下の条件で建株返済指定情報テーブルを検索する<BR>
     * <BR>
     * [検索条件]<BR>
     * 建株ID＝引数.建株ID<BR>
     * 更新日付＝引数.更新日付と同じ日付<BR>
     * <BR>
     * ２）　@１）の取得結果の一覧を返却する<BR>
     * ※該当するデータが存在しない場合にはnullを返却する<BR>
     * @@param l_lngContractId - 建株ID
     * @@param l_datLastUpdatedTimestamp - (更新日付)<BR>
     * 当日の日付<BR>
     * <BR>
     * YYYYMMDD<BR>
     * @@return EqTypeClosingContractSpec[]
     * @@throws WEB3BaseException
     * @@roseuid 40E5092302ED
     */
    public EqTypeClosingContractSpec[] getClosingContractSpecs(long l_lngContractId, Date l_datLastUpdatedTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getClosingContractSpecs";
        log.entering(STR_METHOD_NAME);
        WEB3EquityClosingContractSpec[] l_closingContractSpec = null;
        try
        {
            // １）建株返済指定情報読込

            //建株ＩＤ = this.getContractId()
            String l_strWhere = " contract_id = ? and to_char(last_updated_timestamp,'yyyyMMdd') = ? ";

            // [条件1]:建株返済指定情報.建株ＩＤ = 引数.建株ＩＤ

            Object[] l_objWhereValues = { new Long(l_lngContractId), WEB3DateUtility.formatDate(l_datLastUpdatedTimestamp, "yyyyMMdd")};

            List l_returnList = new ArrayList();

            // データ検索   
            QueryProcessor processor = Processors.getDefaultProcessor();
            l_returnList = processor.doFindAllQuery(EqtypeClosingContractSpecRow.TYPE, l_strWhere, l_objWhereValues);

            // ２）取得結果の一覧を返却
            if (l_returnList == null ||l_returnList.size() == 0)
            {
                return null;
            }

            l_closingContractSpec = new WEB3EquityClosingContractSpec[l_returnList.size()];
            ArrayList l_lisReturnList = new ArrayList();
            for (int i = 0; i < l_returnList.size(); i++)
            {
                EqtypeClosingContractSpecRow l_row = (EqtypeClosingContractSpecRow) l_returnList.get(i);
                WEB3EquityClosingContractSpec l_impl = new WEB3EquityClosingContractSpec(l_row);
                l_lisReturnList.add(i, l_impl);
            }
            l_lisReturnList.toArray(l_closingContractSpec);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "建株返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "建株返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_closingContractSpec;
    }

    /**
     * (get当日決済約定株数)<BR>
     * 指定建株の当日決済約定株数を取得する。<BR>
     * <BR>
     * １）　@当日の返済指定情報一覧を取得<BR>
     * 　@this.get返済指定情報(引数.建株ID、引数.更新日付)をコール<BR>
     * 　@※get返済指定情報の戻り値がnullの場合、0を返却する<BR>
     * <BR>
     * ２）　@返済指定情報要素数ごとのLoop処理<BR>
     * 　@返済指定情報.getExecutedQuantity( )の値を加算していく<BR>
     * <BR>
     * ３）　@ 返済約定数量の合計値を返却する<BR>
     * @@param l_lngContractId - 建株ID
     * @@param l_datLastUpdatedTimestamp - (更新日付)<BR>
     * 当日の日付<BR>
     * <BR>
     * YYYYMMDD<BR>
     * @@return double<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40E504EF0212
     */
    public double getDayCloseContractExecutedQuantity(long l_lngContractId, Date l_datLastUpdatedTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDayCloseContractExecutedQuantity";
        log.entering(STR_METHOD_NAME);
        WEB3EquityClosingContractSpec[] l_closingContractSpec = (WEB3EquityClosingContractSpec[]) this.getClosingContractSpecs(l_lngContractId, l_datLastUpdatedTimestamp);
        if (l_closingContractSpec == null)
        {
            return 0;
        }
        double l_dblexecutedQuantity = 0D;
        int l_intContractSpecLength = 0;
        if (l_closingContractSpec != null)
        {
            l_intContractSpecLength = l_closingContractSpec.length;
        }
        for (int i = 0; i < l_intContractSpecLength; i++)
        {
            l_dblexecutedQuantity += l_closingContractSpec[i].getExecutedQuantity();
        }

        return l_dblexecutedQuantity;
    }

    /**
     * (get建株時価)<BR>
     * 指定建株の銘柄の時価を取得する。<BR>
     * <BR>
     * 下記手順で取得した時価を返却する。<BR>
     * <BR>
     * １）　@市場ID、銘柄IDの取得<BR>
     * 市場ID = 引数.建株.get市場ID()<BR>
     * 銘柄ID = 引数.建株.get銘柄ID()<BR>
     * <BR>
     * ２）　@市場コード、銘柄コードの取得<BR>
     * 市場 = 拡張金融オブジェクトマネージャ.getMarket(市場ID)<BR>
     * 市場コード = 市場.get市場コード()<BR>
     * <BR>
     * 株式銘柄 = 拡張プロダクトマネージャ.getProduct(銘柄ID)<BR>
     * 銘柄コード = 株式銘柄.get銘柄コード()<BR>
     * <BR>
     * ３）　@時価セット判定および時価の取得<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(CURRENT_PRICE)でHashtableを取得<BR>
     * <BR>
     * 　@３－１）　@該当銘柄の時価がセットされている場合(Hashtable.get(銘柄コード + 市場コード)がNULLでない場合)<BR>
     * <BR>
     *         時価 = Hashtable.get(銘柄コード + 市場コード)<BR>
     * <BR>
     * 　@３－２）　@該当銘柄の時価がセットされていない場合(Hashtable.get(銘柄コード + 市場コード)がNULLの場合)<BR>
     * <BR>
     * 　@　@３－２－１）　@時価の取得<BR>
     * 　@　@　@　@　@　@　@取引銘柄 = 拡張プロダクトマネージャ.getTradedProduct(銘柄ID、市場ID）<BR>
     * 　@　@　@　@　@　@　@時価 = 拡張プロダクトマネージャ.get時価(取引銘柄)<BR>
     * 　@　@<BR>
     * 　@　@３－２－２）　@時価の追加<BR>
     * 　@　@　@　@　@　@　@取得したHashtableに銘柄コード + 市場コードをkeyとし、時価を追加<BR>
     * 　@　@　@　@　@　@　@Hashtable.put（銘柄コード + 市場コード, 時価)<BR>
     * <BR>
     * 　@　@３－２－３）　@時価のセット<BR>
     * 　@　@　@　@　@　@　@ThreadLocalSystemAttributesRegistry.setAttribute()にて時価をセットする<BR>
     * 　@　@　@　@　@　@　@設定キー：　@CURRENT_PRICE<BR>
     * 　@　@　@　@　@　@　@値：　@３－２－２）で時価を追加したHashtable<BR>
     * <BR>
     * ４）　@時価を返却<BR>
     * <BR>
     * ※当該メソッドを使用する場合は、各サービスインタセプタのonCallにて時価のセット処理(*)、
     * 　@onReturnおよびonThrowableメソッド内にて時価のクリア処理を行うこと<BR>
     * <BR>
     * (*)ThreadLocalSystemAttributesRegistry.setAttribute()にてThreadLocalに時価の変数をセットする<BR>
     * 　@　@　@　@　@　@　@設定キー：　@CURRENT_PRICE<BR>
     * 　@　@　@　@　@　@　@値：　@Hashtable(新規に作成したHashtable)<BR>
     * <BR>
     * @@param l_contract - 建株
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40E4F2FC03D7
     */
    public double getContractCurrentPrice(WEB3EquityContract l_contract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractCurrentPrice";
        log.entering(STR_METHOD_NAME);

        if (l_contract == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        double l_dblCurrentPrice = 0.0D;
        
        // １）　@市場ID、銘柄IDの取得
        long l_lngMarketId = l_contract.getMarketId();
        long l_lngProductId = l_contract.getProduct().getProductId();

        // ２）　@市場コード、銘柄コードの取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        Market l_market = null;
        WEB3EquityProduct l_product = null;
        try {
            l_market = l_finObjectManager.getMarket(l_lngMarketId);
            l_product = (WEB3EquityProduct)l_productManager.getProduct(l_lngProductId);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        String l_strMarketCode = l_market.getMarketCode();
        String l_strProductCode = l_product.getProductCode();

        //  ３）　@時価セット判定および時価の取得
        Hashtable l_htCurrentPrices =
            (Hashtable)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3MarginAttributeNameDef.CURRENT_PRICE);
        String l_strKey = l_strProductCode + l_strMarketCode;
        // 　@３－１）　@該当銘柄の時価がセットされている場合(Hashtable.get(銘柄コード + 市場コード)がNULLでない場合)
        if (l_htCurrentPrices != null && l_htCurrentPrices.containsKey(l_strKey))
        {
            Double l_currentPrice =
                (Double)l_htCurrentPrices.get(l_strKey);
            l_dblCurrentPrice = l_currentPrice.doubleValue();
        }
        // 　@３－２）　@該当銘柄の時価がセットされていない場合(Hashtable.get(銘柄コード + 市場コード)がNULLの場合)
        else
        {
            // 　@　@３－２－１）　@時価の取得
            WEB3EquityTradedProduct l_tradeProduct = null;
            try
            {
                l_tradeProduct = (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_lngProductId, l_lngMarketId);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            l_dblCurrentPrice = l_productManager.getCurrentPrice(l_tradeProduct);

            // 　@　@３－２－２）　@時価の追加
            if (l_htCurrentPrices == null)
            {
                l_htCurrentPrices = new Hashtable();
            }
            l_htCurrentPrices.put(l_strKey, new Double(l_dblCurrentPrice));

            // 　@　@３－２－３）　@時価のセット
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MarginAttributeNameDef.CURRENT_PRICE,
                l_htCurrentPrices);
        }
        
        // ４）　@時価を返却
        log.exiting(STR_METHOD_NAME);
        return l_dblCurrentPrice;
    }

    /**
     * (get決済状態)<BR>
     * 建株の決済状態を判定する。<BR>
     * <BR>
     * １）　@信用取引決済状態の生成<BR>
     * 　@戻り値となる信用取引決済状態オブジェクトを生成する。<BR>
     * 　@信用取引決済状態のプロパティのデフォルト設定は下記とする。<BR>
     * 　@・決済済フラグ＝false<BR>
     * 　@・未決済フラグ＝false<BR>
     * 　@・決済中フラグ＝false<BR>
     * <BR>
     * ２）　@決済状態判定項目の取得<BR>
     * <BR>
     * 　@・建株ID＝引数.建株.getContractId( )<BR>
     * 　@・更新日付＝引数.建株.getLastUpdatedTimestamp( )<BR>
     * 　@・元建株数＝引数.建株.getOriginalQuantity( )<BR>
     * 　@・建株数＝引数.建株.getQuantity( )<BR>
     * <BR>
     * ３）　@決済状態の判定１<BR>
     * <BR>
     * 　@３－１）　@元建株数==0、かつ建株数==0の場合(新規建約定取消)、<BR>
     * 　@　@　@　@　@　@デフォルト設定のままで信用取引決済状態を返却する。<BR>
     * <BR>
     * 　@３－２）　@更新日付(yyyymmdd)＜システム日付(yyyymmdd)で、かつ建株数<BR>
     * ==0の場合(前日以前決済済)、<BR>
     * 　@　@　@　@　@　@デフォルト設定のままで信用取引決済状態を返却する。<BR>
     * <BR>
     * 　@３－３）　@更新日付(yyyymmdd)≧システム日付（yyyymmdd）、<BR>
     * かつ建株数==0の場合(決済済)、<BR>
     * 　@　@　@　@　@　@決済済フラグ＝trueに設定し信用取引決済状態を返却する。<BR>
     * <BR>
     * 　@３－４）　@上記(３－１）、３－２）、３－３)以外の場合、４）の処理を行う。<BR>
     * <BR>
     * ４）　@ロック中数量、および、当日決済約定株数の取得<BR>
     * <BR>
     * 　@　@・ロック中数量＝引数.建株.getLockedQuantity( )<BR>
     * 　@　@・当日決済約定株数＝this.当日決済約定株数(建株ID、システム日付<BR>
     * (yyyymmdd))<BR>
     * <BR>
     * ５）　@決済状態の判定２<BR>
     * <BR>
     * 　@５－１）　@ロック中数量==0の場合<BR>
     * 　@　@　@・当日決済約定株数==0の場合(未決済)、<BR>
     * 　@　@　@　@未決済フラグ＝trueに設定し信用取引決済状態を返却する。<BR>
     * <BR>
     * 　@　@　@・当日決済約定株数≧1の場合(決済済と未決済)、<BR>
     * 　@　@　@　@決済済フラグ＝true、未決済フラグ＝trueに設定し信用取引決済状態を返却する
     * 。<BR>
     * <BR>
     * 　@５－２）　@ロック中数量≧1の場合<BR>
     * 　@　@　@５－２－１）　@当日決済約定株数==0の場合<BR>
     * 　@　@　@　@・ロック中数量==建株数の場合(決済中)、<BR>
     * 　@　@　@　@　@決済中フラグ＝trueに設定し信用取引決済状態を返却する。<BR>
     * <BR>
     * 　@　@　@　@・ロック中数量!=建株数の場合(未決済と決済中)、<BR>
     * 　@　@　@　@　@未決済フラグ＝true、決済中フラグ＝trueに設定し信用取引決済状態を返却す
     * る。<BR>
     * <BR>
     * 　@　@　@５－２－２）　@当日決済約定株数≧1の場合<BR>
     * 　@　@　@　@・ロック中数量==建株数の場合(決済済と決済中)、<BR>
     * 　@　@　@　@　@決済済フラグ＝true、決済中フラグ＝trueに設定し信用取引決済状態を返却す
     * る。<BR>
     * <BR>
     * 　@　@　@　@・ロック中数量!=建株数の場合(決済済と未決済と決済中)、<BR>
     * 　@　@　@　@　@決済済フラグ＝true、未決済フラグ＝ture、<BR>
     * 決済中フラグ＝tureに設定し信用取引決済状態を返却する。<BR>
     * @@param l_contract - 建株
     * @@return WEB3MarginCloseStatus
     * @@throws WEB3BaseException
     * @@roseuid 40E4F23E01B4
     */
    public WEB3MarginCloseStatus getMarginCloseStatus(WEB3EquityContract l_contract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarginCloseStatus";
        log.entering(STR_METHOD_NAME);
        if (l_contract == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        WEB3MarginCloseStatus l_marginCloseStatus = new WEB3MarginCloseStatus();
        //決済済フラグ
        l_marginCloseStatus.closedMarginFlag = false;

        //未決済フラグ
        l_marginCloseStatus.closeMarginFlag = false;

        //決済中フラグ
        l_marginCloseStatus.closingMarginFlag = false;

        // 建株を取得する
        EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) l_contract.getDataSourceObject();
        // 1.建株IDを取得する。
        long l_lngContractId = l_contract.getContractId();
        String l_timestamp = WEB3DateUtility.formatDate(l_eqtypeContractRow.getLastUpdatedTimestamp(), "yyyyMMdd");

        // 3.getOriginalQuantity( ) --- 元建株数量を取得する。
        double l_dblOriginalQuantity = l_contract.getOriginalQuantity();
        if (Double.isNaN(l_dblOriginalQuantity))
        {
            l_dblOriginalQuantity = 0;
        }

        Timestamp l_dteCurrentDate = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        String l_CurrentDate = WEB3DateUtility.formatDate(l_dteCurrentDate, "yyyyMMdd");
        // 4.getQuantity( ) --- 建株数量を取得する。
        double l_dblQuantity = l_contract.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0;
        }
        // 元建株数 == 0 かつ 建株数 == 0の場合(約定取消)
        if (l_dblOriginalQuantity == 0 && l_dblQuantity == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return l_marginCloseStatus;
        }
        // 更新日付(yyyymmdd) < システム日付(yyyymmdd)で、かつ建株数 == 0の場合(前日以前決済済)
        else if (l_timestamp.compareTo(l_CurrentDate) < 0 && l_dblQuantity == 0)
        {
            return l_marginCloseStatus;
        }
        // 更新日付(yyyymmdd) >= システム日付(yyyymmdd)、かつ建株数 == 0の場合(決済済)
        else if (l_timestamp.compareTo(l_CurrentDate) >= 0 && l_dblQuantity == 0)
        {
            l_marginCloseStatus.closedMarginFlag = true;

            log.exiting(STR_METHOD_NAME);
            return l_marginCloseStatus;
        }
        else
        {
            // 5.getLockedQuantity( ) --- 決済注文中数量を取得する。
            double l_dblLockedQuantity = l_contract.getLockedQuantity();
            //当日決済約定株数＝this.当日決済約定株数(建株ID、システム日付
            double l_dblContractExecutionCnt = getDayCloseContractExecutedQuantity(l_lngContractId, l_dteCurrentDate);
            
            // ロック中数量 == 0の場合
            if (l_dblLockedQuantity == 0)
            {
                // 当日決済約定株数 == 0の場合(未決済)
                if (l_dblContractExecutionCnt == 0)
                {
                    // 未決済フラグ == true
                    l_marginCloseStatus.closeMarginFlag = true;
                }
                // 当日決済約定株数 ≧ 1の場合(決済済と未決済)
                else if (l_dblContractExecutionCnt >= 1)
                {
                    // 決済済・未決済フラグ == true
                    l_marginCloseStatus.closedMarginFlag = true;
                    l_marginCloseStatus.closeMarginFlag = true;

                }
                return l_marginCloseStatus;
            }
            // ロック中数量 ≧ 1の場合
            else if (l_dblLockedQuantity >= 1)
            {
                // 当日決済約定株数 == 0の場合
                if (l_dblContractExecutionCnt == 0)
                {
                    // ロック中数量 == 建株数の場合(決済中)
                    if (l_dblLockedQuantity == l_dblQuantity)
                    {
                        // 決済中フラグ == true
                        l_marginCloseStatus.closingMarginFlag = true;
                    }
                    else    // ロック中数量 != 建株数の場合(未決済と決済中)
                    {
                        // 未決済・決済中フラグ == true
                        l_marginCloseStatus.closeMarginFlag = true;
                        l_marginCloseStatus.closingMarginFlag = true;
                    }
                }
                // 当日決済約定株数 ≧ 1の場合
                else if (l_dblContractExecutionCnt >= 1)
                {
                    // ロック中数量 == 建株数の場合(決済済と決済中)
                    if (l_dblLockedQuantity == l_dblQuantity)
                    {
                        // 決済済・決済中フラグ == true
                        l_marginCloseStatus.closedMarginFlag = true;
                        l_marginCloseStatus.closingMarginFlag = true;
                    }
                    else    // ロック中数量 != 建株数の場合(決済済と未決済と決済中)
                    {
                        // 決済済・未決済・決済中フラグ == true
                        l_marginCloseStatus.closedMarginFlag = true;
                        l_marginCloseStatus.closeMarginFlag = true;
                        l_marginCloseStatus.closingMarginFlag = true;
                    }
                }
                
                log.exiting(STR_METHOD_NAME);
                return l_marginCloseStatus;
            }
        }
        log.error("Error In Method: " + STR_METHOD_NAME);
        throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, this.getClass().getName() + STR_METHOD_NAME);
    }

    /**
     * (is無期限)<BR>
     * 指定された弁済期限値が「無期限」であるかどうかを判定する。<BR>
     * <BR>
     * 引数の弁済期限値＝9999999(ALL9)である場合は、<BR>
     * 「無期限」と判定しtrueを返す。<BR>
     * 以外はfalseを返す。<BR>
     * @@param l_dblRepaymentNum - 弁済期限値。
     * 
     * @@return boolean
     * @@roseuid 40D2F02700FB
     */
    public boolean isIndefinite(double l_dblRepaymentNum)
    {
        final String STR_METHOD_NAME = "isIndefinite";
        log.entering(STR_METHOD_NAME);

        if (Double.compare(l_dblRepaymentNum, 9999999) == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (update既約定データ)<BR>
     * 約定取消時に、以下のエンティティの更新を行う。<BR>
     * <BR>
     * －株式顧客勘定（EqTypeFinTransaction）<BR>
     * －顧客勘定（GenFinTransaction）<BR>
     * －建株（EqTypeContract）<BR>
     * <BR>
     * 拡張ポジションヘルパー.update既約定データ(約定)にdelegateする。<BR>
     * @@param l_orderExecution - 約定。
     * @@throws WEB3BaseException
     * @@roseuid 40D0EAC20208
     */
    public void updateExecutedData(EqTypeOrderExecution l_orderExecution) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateExecutedData(EqTypeOrderExecution)";
        log.entering(STR_METHOD_NAME);
        ((WEB3EquityPositionManagerHelper)m_helper).updateExecutedData(l_orderExecution);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get建株一覧)<BR>
     * 指定条件に一致する建株オブジェクト（EqTypeContract）の一覧をソートして返却する。<BR>
     * 該当データが存在しない場合はNULLを返却する。<BR>
     * （getContractsのオーバーロード）<BR>
     * <BR>
     * １）　@戻り値オブジェクトのインスタンスを生成する。<BR>
     * <BR>
     * ２）　@検索条件を追加する。<BR>
     * <BR>
     * ２－１）　@引数.検索条件文字列の先頭に、<BR>
     * 　@　@　@　@　@"口座ID = ?　@and　@補助口座ID = ? and 銘柄タイプ = ?"<BR>
     * 　@　@　@　@　@を付加する。<BR>
     * <BR>
     * ２－２）　@引数.検索条件データコンテナの先頭に、<BR>
     * 　@　@　@　@　@検索条件文字列先頭に付加したパラメータリストを追加する。<BR>
     * 　@　@　@　@　@※引数の補助口座オブジェクト、及び引数の銘柄タイプより設定する。<BR>
     * <BR>
     * ３）　@QueryProcessor.doFindAllQuery( )により、建株オブジェクトのListを取得する。<BR>
     * <BR>
     * 　@　@　@doFindAllQuery(,建株Row.TYPE<BR>
     *                                      ２－１）の検索条件文字列,<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@パラメータ.ソート条件文字列,<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@null,<BR>
     *                                      ２－２）の検索条件データコンテナ)<BR>
     * <BR>
     * ４）　@検索結果を返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ（ProductTypeEnumオブジェクト）<BR>
     * <BR>
     * 0：その他<BR>
     * 1：株式<BR>
     * 2：債券<BR>
     * 3：投資信託<BR>
     * 4：外国株<BR>
     * 5：現金<BR>
     * 6：先物オプション<BR>
     * @@param l_strSearchCondCharacterString - 検索条件 文字列
     * @@param l_strSortCondCharacterString - ソート条件文字列
     * @@param l_strSearchCondDataContainers - (検索条件データコンテナ)<BR>
     * 検索条件
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 40F2536202BC
     */
    public List getContracts(
        WEB3GentradeSubAccount l_subAccount,
        ProductTypeEnum l_productType,
        String l_strSearchCondCharacterString,
        String l_strSortCondCharacterString,
        String[] l_strSearchCondDataContainers)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContracts";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        // (1)戻り値オブジェクトのインスタンスを生成する
        List l_lisReturnValue = new ArrayList();

        // (2)検索条件追加
        // (2-1）検索条件文字列を作成
        String l_strWhere = "account_id = ? and sub_account_id = ? and product_type = ?";

        Object[] l_objWhereValue = null;
        // (2-2)文字列配列を生成し
        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        Object[] l_objWhereValue1 = { new Long(l_lngAccountId), new Long(l_lngSubAccountId), new Long(l_productType.intValue())};

        if ((l_strSearchCondCharacterString != null && !l_strSearchCondCharacterString.equals("")) && l_strSearchCondDataContainers != null)
        {

            l_strWhere += l_strSearchCondCharacterString;

            l_objWhereValue = new Object[l_strSearchCondDataContainers.length + 3];
            System.arraycopy(l_objWhereValue1, 0, l_objWhereValue, 0, 3);

            System.arraycopy(l_strSearchCondDataContainers, 0, l_objWhereValue, 3, l_strSearchCondDataContainers.length);

        }
        else
        {
            l_objWhereValue = new Object[3];
            l_objWhereValue[0] = new Long(l_lngAccountId);
            l_objWhereValue[1] = new Long(l_lngSubAccountId);
            l_objWhereValue[2] = new Long(l_productType.intValue());
        }

        // (3)QueryProcessor.doFindAllQuery( )により、
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(EqtypeContractRow.TYPE, l_strWhere, l_strSortCondCharacterString, null, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "建株テーブルを検索  error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "建株テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisReturnValue;
    }

    /**
     * (get建株一覧)<BR>
     * 指定条件に一致する建株オブジェクト（EqTypeContract）の一覧を返却する。<BR>
     * 該当データが存在しない場合はNULLを返却する。<BR>
     * （getContractsのオーバーロード）<BR>
     * <BR>
     * １）　@戻り値オブジェクトのインスタンスを生成する。<BR>
     * <BR>
     * ２）　@検索条件を追加する。<BR>
     * <BR>
     * ２－１）　@引数.検索条件文字列の先頭に、<BR>
     * 　@　@　@　@　@"口座ID = ?　@and　@補助口座ID = ? and 銘柄タイプ = ?"<BR>
     * 　@　@　@　@　@を付加する。<BR>
     * <BR>
     * ２－２）　@引数.検索条件データコンテナの先頭に、<BR>
     * 　@　@　@　@　@検索条件文字列先頭に付加したパラメータリストを追加する。<BR>
     * 　@　@　@　@　@※引数の補助口座オブジェクト、及び引数の銘柄タイプより設定する。<BR>
     * <BR>
     * ３）　@QueryProcessor.doFindAllQuery( )により、建株オブジェクトのListを取得する。<BR>
     * <BR>
     * 　@　@　@doFindAllQuery(,建株Row.TYPE<BR>
     *                                      ２－１）の検索条件文字列,<BR>
     *                                      ２－２）の検索条件データコンテナ)<BR>
     * 
     * ４）　@検索結果を返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ（ProductTypeEnumオブジェクト）<BR>
     * <BR>
     * 0：その他<BR>
     * 1：株式<BR>
     * 2：債券<BR>
     * 3：投資信託<BR>
     * 4：外国株<BR>
     * 5：現金<BR>
     * 6：先物オプション<BR>
     * @@param l_strSearchCondCharacterString - 検索条件 文字列
     * @@param l_strSearchCondDataContainers - (検索条件データコンテナ)<BR>
     * 検索条件
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 40ABFE5E03C4
     */
    public List getContracts(
        WEB3GentradeSubAccount l_subAccount,
        ProductTypeEnum l_productType,
        String l_strSearchCondCharacterString,
        String[] l_strSearchCondDataContainers)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContracts()";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        // (1)戻り値オブジェクトのインスタンスを生成する
        List l_lisReturnValue = new ArrayList();

        // (2)検索条件追加
        // (2-1）検索条件文字列を作成
        String l_strWhere = "account_id = ? and sub_account_id = ? and product_type = ?";

        Object[] l_objWhereValue = null;
        // (2-2)文字列配列を生成し
        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        Object[] l_objWhereValue1 = {
            new Long(l_lngAccountId),
            new Long(l_lngSubAccountId),
            new Long(l_productType.intValue())};

        if ((l_strSearchCondCharacterString != null
            && !l_strSearchCondCharacterString.equals(""))
            && l_strSearchCondDataContainers != null)
        {

            //l_strWhere += "and "  +l_strSearchConditionString;
            l_strWhere += l_strSearchCondCharacterString;

            l_objWhereValue = new Object[l_strSearchCondDataContainers.length + 3];
            System.arraycopy(l_objWhereValue1, 0, l_objWhereValue, 0, 3);

            System.arraycopy(l_strSearchCondDataContainers, 0, l_objWhereValue, 3, l_strSearchCondDataContainers.length);

        }
        else
        {
            l_objWhereValue = new Object[3];
            l_objWhereValue[0] = new Long(l_lngAccountId);
            l_objWhereValue[1] = new Long(l_lngSubAccountId);
            l_objWhereValue[2] = new Long(l_productType.intValue());
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(EqtypeContractRow.TYPE, l_strWhere, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "建株テーブルを検索  error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "建株テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        if (l_lisReturnValue == null || l_lisReturnValue.size() == 0)
        {
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisReturnValue;
    }
    /**
     * (getミニ株保有株数)<BR>
     * （getMiniStockQuantity）<BR>
     *  １）　@保有資産取得 <BR>
     * 【保有資産テーブル】から、以下の条件を指定し該当する保有資産を取得する。<BR> 
     * [条件] <BR>
     * 口座ＩＤ = 口座ＩＤ And <BR>
     * 補助口座ＩＤ = 補助口座ＩＤ And <BR>
     * 銘柄ＩＤ = 銘柄ＩＤ And <BR>
     * ミニ株区分 = 1：ミニ株 <BR>
     * <BR>
     * ２）　@株数合計 <BR>
     * 取得したすべての保有資産株数（保有資産.getQuantity()）の合計値を返却する<BR>
     * <BR>
     * @@param l_lngMainAccountId - (口座ID)<BR>
     * @@param l_lngSubAccountId - (補助口座ID)<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * @@return double
     */
    public double getMiniStockQuantity(long l_lngMainAccountId, long l_lngSubAccountId, long l_lngProductId)
    {
        final String STR_METHOD_NAME = " getMiniStockQuantity(long, long, long, boolean)";
        log.entering(STR_METHOD_NAME);
        List l_list = null;
        String l_strWhere = "(account_id = ?) and (sub_account_id = ?) and (product_id = ?) and (mini_stock_div = ?)";
        Object[] l_objWhere = new Object[4];
        l_objWhere[0] = new Long(l_lngMainAccountId);
        l_objWhere[1] = new Long(l_lngSubAccountId);
        l_objWhere[2] = new Long(l_lngProductId);
        l_objWhere[3] = new String("1");
        try
        {

            l_list = Processors.getDefaultProcessor().doFindAllQuery(AssetRow.TYPE, l_strWhere, l_objWhere);

            int l_intLength = l_list.size();
            double l_dblTotal = 0;
            for (int i = 0; i < l_intLength; i++)
            {
                AssetParams l_assetParams = new AssetParams((AssetRow) l_list.get(i));
                l_dblTotal = l_dblTotal + l_assetParams.getQuantity();
            }
            return l_dblTotal;
        }
        catch (DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new RuntimeSystemException("System exception while searching product with market id :", l_ex);
        }
    }
    
    /**
     * (getミニ株保有資産一覧)<BR>
     * （getMiniStockAssets）<BR>
     *  指定条件に一致する保有資産オブジェクトの一覧を銘柄ＩＤ順に返却する。<BR> 
     * <BR>
     * １）　@検索条件編集 <BR>
     * １－１）　@検索条件文字列編集 <BR>
     * " 口座ＩＤ = ?　@and　@補助口座ＩＤ = ? and 銘柄タイプ = ? and ミニ株区分 = 1：ミニ株 "を編集する。<BR> 
     * <BR>
     * １－２）　@データコンテナ作成 <BR>
     * 検索条件文字列の ? に該当するbind値を配列に編集する。 <BR>
     * ※ 補助口座オブジェクト、及び引数の銘柄タイプより設定する。<BR>
     *  <BR>
     * ２）　@クエリ実行 <BR>
     * QueryProcessor.doFindAllQuery()にて、保有資産オブジェクトのListを取得、戻り値を返却する。 <BR>
     * <BR>
     * [doFindAllQuery()に指定する引数] <BR>
     * rowType：　@保有資産Row.TYPE <BR>
     * where：　@１－１）の検索条件文字列 <BR>
     * orderBy：　@(* 銘柄IDの項目名） <BR>
     * conditions：　@null <BR>
     * bindVars：　@１－２）の検索条件データコンテナ<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ（ProductTypeEnumオブジェクト）
     * @@return List
     */
    public List getMiniStockAssets(WEB3GentradeSubAccount l_subAccount, ProductTypeEnum l_productType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMiniStockAssets(WEB3GentradeSubAccount l_subAccount, ProductTypeEnum l_productType)";
        log.entering(STR_METHOD_NAME);

        //検索条件文字列
        String l_strWhere = " (account_id = ?) and (sub_account_id) = ? and (product_type = ?) and (mini_stock_div = ?)";
        Object[] l_objWhere = new Object[4];
        l_objWhere[0] = new Long(l_subAccount.getAccountId());
        l_objWhere[1] = new Long(l_subAccount.getSubAccountId());
        l_objWhere[2] = new Long(l_productType.intValue());
        l_objWhere[3] = new String("1");

        List l_lisReturnValue = new ArrayList();
        QueryProcessor l_queryProcessor = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(AssetRow.TYPE, l_strWhere, "product_id", null, l_objWhere);
        }
        catch (DataFindException e)
        {

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00670, this.getClass().getName() + STR_METHOD_NAME, e.getMessage(), e);
        }
        catch (DataNetworkException e)
        {
            String l_strMessage = "建株テーブルを検索 error";
            log.error(l_strMessage, e);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, e.getMessage(), e);
        }

        catch (DataQueryException e)
        {
            String l_strMessage = "建株テーブルを検索  error";
            log.error(l_strMessage, e);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, e.getMessage(), e);
        }
        List l_list2 = new ArrayList();
        for(int i = 0; i < l_lisReturnValue.size(); i++)
        {
            Asset l_asset = new WEB3EquityAsset((AssetRow)l_lisReturnValue.get(i));
            l_list2.add(l_asset);
        }
        
        return l_list2;
    }

    /**
     * （create決済建株明細一覧）<BR>
     * <BR>
     * 建株テーブルのレコードから決済建株明細の配列を作成し、返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用残高）create決済建株明細一覧」参照。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param isLong - (is買建)<BR>
     * 買建かどうかのフラグ<BR>
     * true：買建<BR>
     * false：売建<BR>
     * @@param l_lngMarketId - (市場ID)<BR>
     * 市場ID。<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID。<BR>
     * @@param l_taxType - (税区分)<BR>
     * 税区分<BR>
     * 0：その他<BR>
     * 1：一般<BR>
     * 2：特定<BR>
     * 3：特定口座かつ源泉徴収<BR>
     * (TaxTypeEnumにて定義)<BR>
     * @@param l_strRepaymentType - (弁済区分)<BR>
     * 弁済区分<BR>
     * 1：制度信用<BR>
     * 2：一般信用<BR>
     * @@param l_dblRepaymentNum - (弁済期限値)<BR>
     * 弁済期限値。<BR>
     * @@throws WEB3BaseException
     * @@return WEB3MarginCloseMarginContractUnit[]
     */
    public WEB3MarginCloseMarginContractUnit[] createCloseMarginContracts(
        WEB3GentradeSubAccount l_subAccount,
        boolean isLong,
        long l_lngMarketId,
        long l_lngProductId,
        TaxTypeEnum l_taxType,
        String l_strRepaymentType,
        double l_dblRepaymentNum)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCloseMarginContracts(WEB3GentradeSubAccount, boolean, long, long, TaxTypeEnum, String, double)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. 検索条件文字列の作成
        String l_strWhere = " and market_id = ?"
                          + " and contract_type = ?"
					      + " and product_id = ?"
					      + " and quantity != ?"
					      + " and tax_type = ?"
					      + " and repayment_type = ?"
                          + " and repayment_num = ?";
        
        //1.2. 検索条件データコンテナの作成
        String[] l_strData = new String[7];
        l_strData[0] = Long.toString(l_lngMarketId);
        if (isLong)
        {
            l_strData[1] = Integer.toString(ContractTypeEnum.LONG.intValue());
        }
        else
        {
            l_strData[1] = Integer.toString(ContractTypeEnum.SHORT.intValue());
        }
        l_strData[2] = Long.toString(l_lngProductId);
        l_strData[3] = Integer.toString(0);
        l_strData[4] = Integer.toString(l_taxType.intValue());
        l_strData[5] = l_strRepaymentType;
        l_strData[6] = Double.toString(l_dblRepaymentNum);
        
        //1.3. ソート条件(order by句)文字列の作成
        String l_strSort;
        if (isLong)
        {
            l_strSort = "open_date asc, first_open_date asc, contract_price asc";
        }
        else
        {
            l_strSort = "open_date asc, first_open_date asc, contract_price desc";
        }
        
        //1.4. get建株一覧()
        List l_lisContracts = this.getContracts(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            l_strWhere,
            l_strSort,
            l_strData);
        
        if(l_lisContracts == null || l_lisContracts.size() == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits = null;
        
        //1.6. 取得した建株オブジェクトのﾚｺｰﾄﾞ数分だけLoop処理
        if (l_lisContracts != null)
        {
            int l_intSize = l_lisContracts.size();
            l_closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[l_intSize];
            for (int i = 0;i < l_intSize;i++)
            {
                EqtypeContractRow l_contractRow = (EqtypeContractRow)l_lisContracts.get(i);
                l_closeMarginContractUnits[i] = new WEB3MarginCloseMarginContractUnit();
                l_closeMarginContractUnits[i].id = Long.toString(l_contractRow.getContractId());
                l_closeMarginContractUnits[i].orderQuantity = null;
                l_closeMarginContractUnits[i].settlePriority = null;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_closeMarginContractUnits;
    }

    /**
     * （get決済注文中返済指定情報）<BR>
     * <BR>
     * 指定建株に該当する決済注文中の建株返済指定情報の一覧を取得する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@返済指定情報一覧を取得。<BR>
     * 　@this.get返済指定情報(引数.建株ID）で、<BR>
     * 　@当該建株に対する返済指定情報オブジェクトの一覧を取得する。<BR>
     * <BR>
     * ３）　@２）で取得した返済指定情報要素数分のLoop処理。<BR>
     * 　@　@　@※返済割り当てなし要素、クローズ注文の要素を除外する。<BR>
     * <BR>
     * 　@３－１）　@返済注文数量を取得　@<BR>
     * 　@　@返済指定情報.市場確認済返済数量 == NaN（受付未済）の場合は、<BR>
     * 　@　@返済指定情報.返済注文数量を使用する。<BR>
     * 　@　@返済指定情報.市場確認済返済数量 != NaN（受付済）の場合は、<BR>
     * 　@　@返済指定情報.市場確認済返済数量を使用する。<BR>
     *     ※返済注文数量==0の場合は、以降のLoop内処理を行わない(continue;)<BR>
     * <BR>
     * 　@３－２）　@注文単位IDを取得　@<BR>
     * 　@　@注文単位ID＝返済指定情報.get注文単位ID()<BR>
     * <BR>
     * 　@３－３）　@注文単位オブジェクトを取得<BR>
     * 　@　@注文単位＝拡張株式注文マネージャ.getOrderUnit(注文単位ID)<BR>
     * <BR>
     * 　@３－４）　@注文有効状態の判定<BR>
     * 　@　@注文単位.注文有効状態==”オープン”の場合のみ、<BR>
     * 　@　@ArrayListに返済指定情報オブジェクトを追加<BR>
     * 　@<BR>
     * ４）　@ArrayList.toArray( )で返済指定情報オブジェクトの配列を返却する<BR>
     * <BR>
     * @@param l_lngContractId - (建株ID)<BR>
     * 建株ID。<BR>
     * @@throws WEB3BaseException
     * @@return EqTypeClosingContractSpec[]
     */
    public EqTypeClosingContractSpec[] getContractOrderingClosingContractSpecInfo(
        long l_lngContractId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractOrderingClosingContractSpecInfo(long)";
        log.entering(STR_METHOD_NAME);
        
        EqTypeClosingContractSpec[] l_closingContractSpecs = null;
        
        // １）　@ArrayListを生成する。
        List l_lisClosingContractSpec = new ArrayList();
        
        // ２）　@返済指定情報一覧を取得。
        EqTypeClosingContractSpec[] l_specs = this.getClosingContractSpecs(l_lngContractId);
        
        // ３）　@２）で取得した返済指定情報要素数分のLoop処理。
        if (l_specs != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
			TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
			WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();

            for (int i = 0;i < l_specs.length;i++)
            {
                // 　@３－１）　@返済注文数量を取得
                double l_dblQuantity;
				EqtypeClosingContractSpecRow l_specsRow = (EqtypeClosingContractSpecRow)l_specs[i].getDataSourceObject();
				if (l_specsRow.getConfirmedQuantityIsNull() == true)
				{
					l_dblQuantity = l_specsRow.getQuantity();
				}
				else
				{
					l_dblQuantity = l_specsRow.getConfirmedQuantity();
				}
                if (l_dblQuantity == 0.0D)
                {
                	continue;
                }
                // 　@３－２）　@注文単位IDを取得
                long l_lngOrderUnitId = l_specs[i].getOrderUnitId();
                // 　@３－３）　@注文単位オブジェクトを取得
                EqTypeOrderUnit l_orderUnit = null;
                try
                {
                    l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_nfe.getMessage(),
                        l_nfe);
                }
                // 　@３－４）　@注文有効状態の判定
                if (OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus()))
                {
                    l_lisClosingContractSpec.add(l_specs[i]);
                }
            }
            
            // ４）　@ArrayList.toArray( )で返済指定情報オブジェクトの配列を返却する
            int l_intSize = l_lisClosingContractSpec.size();            
            l_closingContractSpecs = new EqTypeClosingContractSpec[l_intSize];
            l_lisClosingContractSpec.toArray(l_closingContractSpecs);
        }
        log.exiting(STR_METHOD_NAME);
        return l_closingContractSpecs;
    }

    /**
     * （calc諸経費按分比率）<BR>
     * <BR>
     * 引数の建株、及び株式顧客勘定明細より、諸経費計算時に使用する按分比率を計算し<BR>
     * 返却する。<BR>
     * （* 拡張ポジションヘルパー.calc諸経費按分比率( )へdelegateする）<BR>
     * <BR>
     * @@param l_dblBalance - (建株残数)<BR>
     * 建株残数。
     * @@param l_dblClosingExecutedQuantity - (決済約定数量)<BR>
     * 決済約定数量（今回約定の、該当建株への割り当て分数量）。
     * @@return double
     */
    public double calcExpensesFactorRatio(double l_dblBalance, double l_dblClosingExecutedQuantity)
    {
        final String STR_METHOD_NAME = "calcExpensesFactorRatio(double, double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return ((WEB3EquityPositionManagerHelper)m_helper).calcExpensesFactorRatio(l_dblBalance, l_dblClosingExecutedQuantity);
    }

    /**
     * （get株式顧客勘定明細ListBy注文単位Plus建株）<BR>
     * <BR>
     * 指定された注文データ＋建株データに対する、<BR>
     * 一口約定計算対象となる株式顧客勘定明細ParamsのListを取得する。<BR>
     * （* 拡張ポジションヘルパー.拡張データマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )へ<BR>
     * 　@　@delegateする）<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * @@param l_lngContractId - (建株ID)<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public List getFinTransactionListByOrderUnitPlusContract(
        long l_lngOrderUnitId,
        long l_lngContractId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFinTransactionListByOrderUnitPlusContract(long, long)";
        log.entering(STR_METHOD_NAME);
        WEB3EquityPositionManagerHelper.WEB3EquityPersistentDataManager l_dataManager =
            (WEB3EquityPositionManagerHelper.WEB3EquityPersistentDataManager)((WEB3EquityPositionManagerHelper)m_helper).getPersistenceManager();
        log.exiting(STR_METHOD_NAME);
        return l_dataManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, l_lngContractId);
    }

    /**
     * （create決済建株エントリ）<BR>
     * <BR>
     * 注文単位IDに該当する決済建株のエントリを作成する。 <BR>
     * <BR>
     * （シーケンス図） <BR>
     * 「（信用残高）create決済建株エントリ」参照<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * @@throws WEB3BaseException
     * @@return EqTypeSettleContractOrderEntry[]
     */
    public EqTypeSettleContractOrderEntry[] createCloseMarginContractEntry(long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createCloseMarginContractEntry(long)";
        log.entering(STR_METHOD_NAME);

        EqTypeSettleContractOrderEntry[] l_contractOrderEntry = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        try
        {
            //1.1. getOrderUnit(注文単位ID : long)
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
            //1.2. getContractsToClose()
            EqTypeClosingContractSpec[] l_specs = null;
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                EqTypeContractSettleOrderUnit l_newOrderUnit = (EqTypeContractSettleOrderUnit)l_orderUnit;
                l_specs = l_newOrderUnit.getContractsToClose();
            }
            else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                EqTypeContractSwapOrderUnit l_newOrderUnit = (EqTypeContractSwapOrderUnit)l_orderUnit;
                l_specs = l_newOrderUnit.getContractsToClose();
            }
            //1.3. ArrayList()
            ArrayList l_array = new ArrayList();
            int l_size = 0;
            if (l_specs != null)
            {
                l_size = l_specs.length;
            }
            //1.4. 返済指定情報の要素数分Loop処理
            for (int i = 0;i < l_size;i++)
            {
                EqTypeClosingContractSpec l_spec = l_specs[i];
                //1.4.1. getContractId()
                long l_lngContractId = l_spec.getContractId();
                //1.4.2. getQuantity()
                double l_dblQuantity = l_spec.getQuantity();
                //1.4.3. EqTypeSettleContractOrderEntry()
                EqTypeSettleContractOrderEntry l_entry = new EqTypeSettleContractOrderEntry(l_lngContractId, l_dblQuantity);
                //1.4.4. add()
                l_array.add(l_entry);
            }
            //1.5. toArray()
            l_contractOrderEntry = new EqTypeSettleContractOrderEntry[l_array.size()];
            l_array.toArray(l_contractOrderEntry);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Not Found Exception");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.entering(STR_METHOD_NAME);
        return l_contractOrderEntry;
    }

    /**
     * （get保有資産一覧）<BR>
     * <BR>
     * （getAssetsのオーバーロード）<BR>
     * 指定条件に一致する保有資産オブジェクトの一覧を返却する。<BR>
     * <BR>
     * １）　@戻り値オブジェクトのインスタンスを生成する。<BR>
     * <BR>
     * ２）　@検索条件を追加する。<BR>
     * <BR>
     * ２－１）　@引数.検索条件文字列の先頭に、<BR>
     * 　@"口座ID = ?　@and　@補助口座ID = ? and 銘柄タイプ = ? and ミニ株区分 = 0：DEFAULT（ミニ株でない）  and (数量+売付不能数量) > 0"<BR>
     * 　@を付加する。<BR>
     * <BR>
     * ２－２）　@引数.検索条件データコンテナの先頭に、<BR>
     * 　@　@　@　@　@検索条件文字列先頭に付加したパラメータリストを追加する。<BR>
     * 　@　@　@　@　@※口座ID、補助口座IDは、引数の補助口座オブジェクトより設定する。<BR>
     * 　@　@　@　@　@※銘柄タイプは、引数の銘柄タイプより設定する。<BR>
     * <BR>
     * ３）　@QueryProcessor.doFindAllQuery( )により、保有資産オブジェクトのListを取得する。<BR>
     * <BR>
     * 　@　@　@doFindAllQuery(,保有資産Row.TYPE<BR>
     *                                      ２－１）の検索条件文字列,<BR>
     *                                      引数のソート条件,<BR>
     *                                      null,<BR>
     *                                      ２－２）の検索条件データコンテナ)<BR>
     * <BR>
     * ４）　@検索結果を返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ（ProductTypeEnumオブジェクト）<BR>
     * @@param l_strSearchString - (検索条件文字列)<BR>
     * 検索条件文字列オブジェクト<BR>
     * @@param l_searchCondContainers - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナオブジェクト<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getAssets(
        SubAccount l_subAccount,
        ProductTypeEnum l_productType,
        String l_strSearchString,
        String[] l_searchCondContainers,
        String l_strSortCond)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAssets(SubAccount, ProductTypeEnum,String, String[] ,String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        //検索条件文字列
        String l_strWhere = "account_id = ? and sub_account_id = ? and product_type = ? and mini_stock_div = ? and (quantity + quantity_cannot_sell) > 0";
        List l_lisResults = null;
        if (l_strSearchString != null && l_strSearchString.length() > 0)
        {
            l_strWhere += l_strSearchString;
        }
        try
        {
            int l_intCondParamLen = 0;
            if (l_searchCondContainers != null)
            {
                l_intCondParamLen = l_searchCondContainers.length;
            }

            Object[] l_objBinds = new Object[4 + l_intCondParamLen];
            l_objBinds[0] = new Long(l_subAccount.getAccountId());
            l_objBinds[1] = new Long(l_subAccount.getSubAccountId());
            l_objBinds[2] = l_productType;
            l_objBinds[3] = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;
            for (int i = 0; i < l_intCondParamLen; i++)
            {
                l_objBinds[4 + i] = l_searchCondContainers[i];
            }

            QueryProcessor l_qp = null;

            l_qp = Processors.getDefaultProcessor();
            List l_lisAssets = l_qp.doFindAllQuery(AssetRow.TYPE, l_strWhere, l_strSortCond, null, l_objBinds);
            int l_assetCount = l_lisAssets.size();
            l_lisResults = new ArrayList(l_assetCount);
            for (int i = 0; i < l_assetCount; i++)
            {
                l_lisResults.add(new WEB3EquityAsset((AssetRow)l_lisAssets.get(i)));
            }
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, e.getMessage(), e);
        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, e.getMessage(), e);
        }
        catch (IllegalStateException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, e.getMessage(), e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisResults;
    }
    
    /**
     * (create未決済残高明細)<BR>
     * <BR>
     * 未決済の残高照会明細を1明細作成する。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（信用残高）create未決済残高照会明細」参照。<BR>
     * <BR>
     * @@params l_subAccount - (補助口座)<BR><BR>
     * @@params l_contract - (建株)<BR>
     * @@return WEB3MarginBalanceReferenceDetailUnit - (信用取引残高照会明細)<BR>
     * @@throws WEB3BaseException<BR>
     */
    public WEB3MarginBalanceReferenceDetailUnit createUnCloseMarginBalanceReferenceDetailUnit(WEB3GentradeSubAccount l_subAccount, WEB3EquityContract l_contract)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createUnCloseMarginBalanceReferenceUnit(WEB3GentradeSubAccount, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);
        
        // 信用取引残高照会明細インスタンスを生成
        WEB3MarginBalanceReferenceDetailUnit l_balanceReferenceUnit =
            new WEB3MarginBalanceReferenceDetailUnit();
        
        // 株式銘柄を取得
        WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_contract.getProduct();
        
        // 建株数量、ロック中数量を取得
        double l_dblQuantity = l_contract.getQuantity();
        double l_dblLockedQuantity = l_contract.getLockedQuantity();
        
        // 建株時価情報の取得
        WEB3EquityProductQuote l_productQuote = this.getContractCurrentPriceInfo(l_subAccount, l_contract);
        
        // 未約定数量の算出
        double l_dblUnExecQuantity = l_dblQuantity - l_dblLockedQuantity;
        
        double l_dblCurrentPrice = 0.0D;
        // 時価
        if (l_productQuote != null)
        {
	        l_dblCurrentPrice = l_productQuote.getQuote();
        }
        
        // 建代金
        double l_dblContractAmount = l_contract.getContractAmount(l_dblUnExecQuantity);
        EqtypeContractRow l_contractRow =
            (EqtypeContractRow)l_contract.getDataSourceObject();
        // 建手数料
        double l_dblSetupFee = l_contract.getSetupFee(l_dblQuantity) - l_contract.getSetupFee(l_dblLockedQuantity);
        // 建手数料消費税
        double l_dblSetupFeeTax = l_contract.getSetupFeeTax(l_dblQuantity) - l_contract.getSetupFeeTax(l_dblLockedQuantity);
        // 名義書換料
        double l_dblNameTransferFee = l_contract.getNameTransferFee(l_dblQuantity) - l_contract.getNameTransferFee(l_dblLockedQuantity);
        // 名義書換料消費税
        double l_dblNameTransferFeeTax = l_contract.getNameTransferFeeTax(l_dblQuantity) - l_contract.getNameTransferFeeTax(l_dblLockedQuantity);
        // 管理費
        double l_dblManagementFee = l_contract.getManagementFee(l_dblQuantity) - l_contract.getManagementFee(l_dblLockedQuantity);
        // 管理費消費税
        double l_dblManagementFeeTax = l_contract.getManagementFeeTax(l_dblQuantity) - l_contract.getManagementFeeTax(l_dblLockedQuantity);
        // 順日歩
        double l_dblInterestFee = l_contract.getInterestFee(l_dblQuantity) - l_contract.getInterestFee(l_dblLockedQuantity);
        // 逆日歩
        double l_dblPayInterestFee = l_contract.getPayInterestFee(l_dblQuantity) - l_contract.getPayInterestFee(l_dblLockedQuantity);
        // 貸株料
        double l_dblLoanEquityFee = l_contract.getLoanEquityFee(l_dblQuantity) - l_contract.getLoanEquityFee(l_dblLockedQuantity);
        // その他
        double l_dblOther = l_contract.getOther(l_dblQuantity) - l_contract.getOther(l_dblLockedQuantity);
        
        // 残高照会明細にプロパティセット
        // ID
        l_balanceReferenceUnit.id = String.valueOf(l_contract.getContractId());
        // 銘柄コード
        l_balanceReferenceUnit.productCode = l_eqtypeProduct.getProductCode();
        // 銘柄名
        EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_eqtypeProduct.getDataSourceObject();
        l_balanceReferenceUnit.productName = l_eqtypeProductRow.getStandardName();
        
        // 拡張金融オブジェクトマネージャを取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        Market l_market = null;
        try
        {
	        l_market = l_finObjectManager.getMarket(l_contract.getMarketId());
        } catch (NotFoundException l_ex) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "市場オブジェクトの取得に失敗しました");
        }
        // 市場コード
        l_balanceReferenceUnit.marketCode = l_market.getMarketCode();
        // 口座区分
        // 建株.税区分 == "一般"の場合
        if (l_contractRow.getTaxType().equals(TaxTypeEnum.NORMAL))
        {
            // "一般"をセット
            l_balanceReferenceUnit.taxType = WEB3TaxTypeDef.NORMAL;
        }
        // 建株.税区分 == "特定" or "特定口座かつ源泉徴収"の場合
        else if (l_contractRow.getTaxType().equals(TaxTypeEnum.SPECIAL)
            || l_contractRow.getTaxType().equals(TaxTypeEnum.SPECIAL_WITHHOLD))
        {
            // "特定"をセット
            l_balanceReferenceUnit.taxType = WEB3TaxTypeDef.SPECIAL;
        }
        // 建区分
        if (ContractTypeEnum.LONG.equals(l_contractRow.getContractType()))
        {
            l_balanceReferenceUnit.contractType = "" + ContractTypeEnum.LONG.intValue();
        }
        else if (ContractTypeEnum.SHORT.equals(l_contractRow.getContractType()))
        {
            l_balanceReferenceUnit.contractType = "" + ContractTypeEnum.SHORT.intValue();
        }
        // 弁済区分、弁済期限値
        WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
        l_repaymentUnit.repaymentDiv = l_contractRow.getRepaymentType();
        l_repaymentUnit.repaymentTimeLimit = String.valueOf(l_contractRow.getRepaymentNum());
        l_balanceReferenceUnit.repayment = l_repaymentUnit;
        
        // 建日
        l_balanceReferenceUnit.openDate = l_contract.getOpenDate();
        // 建単価
        l_balanceReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
        // 建株数
        l_balanceReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblUnExecQuantity);
        // 期日
        l_balanceReferenceUnit.closeDate = l_contract.getCloseDate();
        // 建代金
        l_balanceReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmount);
        // 決済状態区分
        l_balanceReferenceUnit.settlementState = WEB3EquitySettlementStateDef.HAVE_NOT_SETTLED;
        // 返済可能フラグ
        l_balanceReferenceUnit.closeMarginFlag = true;
        // 現引現渡可能フラグ
        l_balanceReferenceUnit.swapFlag = true;
        // 建手数料
        l_balanceReferenceUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblSetupFee + l_dblSetupFeeTax);
        // 順日歩
        l_balanceReferenceUnit.interestFee = WEB3StringTypeUtility.formatNumber(l_dblInterestFee);
        // 逆日歩
        l_balanceReferenceUnit.payInterestFee = WEB3StringTypeUtility.formatNumber(l_dblPayInterestFee);
        // 貸株料
        l_balanceReferenceUnit.loanEquityFee = WEB3StringTypeUtility.formatNumber(l_dblLoanEquityFee);
        // 書換料
        l_balanceReferenceUnit.setupFee = WEB3StringTypeUtility.formatNumber(l_dblNameTransferFee + l_dblNameTransferFeeTax);
        // 管理費
        l_balanceReferenceUnit.managementFee = WEB3StringTypeUtility.formatNumber(l_dblManagementFee + l_dblManagementFeeTax);
        // その他
        l_balanceReferenceUnit.otherwise = WEB3StringTypeUtility.formatNumber(l_dblOther);
        
        // get建株時価情報()の戻り値 != nullの場合
        if (l_productQuote != null)
        {
	        // 評価損益
	        double l_dblProfitLoss = l_contract.getAppraisalProfitOrLoss(l_dblCurrentPrice, l_dblUnExecQuantity);
	        // 評価損益
	        l_balanceReferenceUnit.appraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblProfitLoss);
	        
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityBizLogicProvider l_bizLogic =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            // 諸経費合計
            double l_dblTotalCost = l_bizLogic.calcExpenses(0,
                0,
                l_dblSetupFee,
                l_dblSetupFeeTax,
                l_dblNameTransferFee,
                l_dblNameTransferFeeTax,
                l_dblManagementFee,
                l_dblManagementFeeTax,
                l_dblInterestFee,
                l_dblPayInterestFee,
                l_dblLoanEquityFee,
                l_dblOther,
                l_contractRow.getContractType());
	        double l_dblProfitLossCost = l_dblProfitLoss - l_dblTotalCost;
	        // 評価損益（諸経費考慮）
	        l_balanceReferenceUnit.appraisalProfitLossCost = WEB3StringTypeUtility.formatNumber(l_dblProfitLossCost);
	        // 時価
	        l_balanceReferenceUnit.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
	        // 前日比
            l_balanceReferenceUnit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_productQuote.getComparedPreviousDay());
            // 時価取得時間(HH:MM)
            log.debug("時価取得区分:[" + l_productQuote.getQuoteFromDiv() + "]");
            Timestamp l_quoteTime = l_productQuote.getQuoteTime();
            if (l_quoteTime != null)
            {
                l_balanceReferenceUnit.currentPriceTime = WEB3DateUtility.formatDate(l_quoteTime, "HH:mm");
            }
        }
        else
        {
            // 時価が取得できなかった場合はnullをセット
            l_balanceReferenceUnit.appraisalProfitLoss = null;
            l_balanceReferenceUnit.appraisalProfitLossCost = null;
            l_balanceReferenceUnit.currentPrice = null;
            l_balanceReferenceUnit.comparedPreviousDay = null;
            l_balanceReferenceUnit.currentPriceTime = null;
        }
        
        // 新規建可能フラグ
        l_balanceReferenceUnit.tradingFlag = true;
        
        log.exiting(STR_METHOD_NAME);
        return l_balanceReferenceUnit;
    }
    
    /**
     * (create決済中残高明細)<BR>
     * <BR>
     * 決済中の残高照会明細を1明細作成する。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（信用残高）create決済中残高照会明細」参照。<BR>
     * <BR>
     * @@params l_subAccount - (補助口座)<BR><BR>
     * @@params l_contract - (建株)<BR>
     * @@return WEB3MarginBalanceReferenceDetailUnit - (信用取引残高照会明細)<BR>
     * @@throws WEB3BaseException<BR>
     */
    public WEB3MarginBalanceReferenceDetailUnit createClosingMarginBalanceReferenceDetailUnit(WEB3GentradeSubAccount l_subAccount, WEB3EquityContract l_contract)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createClosingMarginBalanceReferenceDetailUnit(WEB3GentradeSubAccount, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);
        
        // 信用取引残高照会明細インスタンスを生成
        WEB3MarginBalanceReferenceDetailUnit l_balanceReferenceUnit =
            new WEB3MarginBalanceReferenceDetailUnit();
        
        // 株式銘柄を取得
        WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_contract.getProduct();
        
        // ロック中数量を取得
        double l_dblLockedQuantity = l_contract.getLockedQuantity();
        
        // 決済注文中返済指定情報を取得
        EqTypeClosingContractSpec[] l_eqtypeClosingContractSpecs = 
            this.getContractOrderingClosingContractSpecInfo(l_contract.getContractId());
            
        // 建株時価情報の取得
        WEB3EquityProductQuote l_productQuote = this.getContractCurrentPriceInfo(l_subAccount, l_contract);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        double l_dblProfitLossTotal = 0.0D;
        
        // get決済注文中返済指定情報()の戻り値の要素数分Loop処理
        if (l_productQuote != null)
        {
            for (int i = 0; i < l_eqtypeClosingContractSpecs.length; i++)
            {
                EqtypeClosingContractSpecRow l_specRow =
                    (EqtypeClosingContractSpecRow)l_eqtypeClosingContractSpecs[i].getDataSourceObject();
                    
                double l_dblConfirmedQuantity = 0;
                // 市場確認済返済注文数量 != nullの場合
                if (!l_specRow.getConfirmedQuantityIsNull())
                {
                    // 市場確認済返済注文数量の取得
                    l_dblConfirmedQuantity = l_specRow.getConfirmedQuantity();
                }
                else
                {
                    // 返済注文数量の取得
                    l_dblConfirmedQuantity = l_specRow.getQuantity();
                }
                
                // 返済約定数量の取得
                double l_dblExecutedQuantity = l_eqtypeClosingContractSpecs[i].getExecutedQuantity();
                
                // 計算単価
                double l_dblCalcPrice = l_productQuote.getQuote();
                
                // 返済注文中数量の算出
                double l_dblCloseOrderingQuantity = l_dblConfirmedQuantity - l_dblExecutedQuantity;
                
                // 評価損益を算出する
                double l_dblProfitLoss = l_contract.getAppraisalProfitOrLoss(l_dblCalcPrice, l_dblCloseOrderingQuantity);
                
                // 評価損益を集計する
                l_dblProfitLossTotal += l_dblProfitLoss;
            }
        }
        
        // 建代金
        double l_dblContractAmount = l_contract.getContractAmount(l_dblLockedQuantity);
        // 建手数料
        double l_dblSetupFee = l_contract.getSetupFee(l_dblLockedQuantity);
        // 建手数料消費税
        double l_dblSetupFeeTax = l_contract.getSetupFeeTax(l_dblLockedQuantity);
        // 名義書換料
        double l_dblNameTransferFee = l_contract.getNameTransferFee(l_dblLockedQuantity);
        // 名義書換料消費税
        double l_dblNameTransferFeeTax = l_contract.getNameTransferFeeTax(l_dblLockedQuantity);
        // 管理費
        double l_dblManagementFee = l_contract.getManagementFee(l_dblLockedQuantity);
        // 管理費消費税
        double l_dblManagementFeeTax = l_contract.getManagementFeeTax(l_dblLockedQuantity);
        // 順日歩
        double l_dblInterestFee = l_contract.getInterestFee(l_dblLockedQuantity);
        // 逆日歩
        double l_dblPayInterestFee = l_contract.getPayInterestFee(l_dblLockedQuantity);
        // 貸株料
        double l_dblLoanEquityFee = l_contract.getLoanEquityFee(l_dblLockedQuantity);
        // その他
        double l_dblOther = l_contract.getOther(l_dblLockedQuantity);
        
        // 残高照会明細にプロパティセット
        // ID
        l_balanceReferenceUnit.id = String.valueOf(l_contract.getContractId());
        // 銘柄コード
        l_balanceReferenceUnit.productCode = l_eqtypeProduct.getProductCode();
        // 銘柄名
        EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_eqtypeProduct.getDataSourceObject();
        l_balanceReferenceUnit.productName = l_eqtypeProductRow.getStandardName();
        
        // 拡張金融オブジェクトマネージャを取得
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        Market l_market = null;
        try
        {
            l_market = l_finObjectManager.getMarket(l_contract.getMarketId());
        } catch (NotFoundException l_ex) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "市場オブジェクトの取得に失敗しました");
        }
        // 市場コード
        l_balanceReferenceUnit.marketCode = l_market.getMarketCode();
        // 口座区分
        EqtypeContractRow l_contractRow =
            (EqtypeContractRow)l_contract.getDataSourceObject();
        // 建株.税区分 == "一般"の場合
        if (l_contractRow.getTaxType().equals(TaxTypeEnum.NORMAL))
        {
            // "一般"をセット
            l_balanceReferenceUnit.taxType = WEB3TaxTypeDef.NORMAL;
        }
        // 建株.税区分 == "特定" or "特定口座かつ源泉徴収"の場合
        else if (l_contractRow.getTaxType().equals(TaxTypeEnum.SPECIAL)
            || l_contractRow.getTaxType().equals(TaxTypeEnum.SPECIAL_WITHHOLD))
        {
            // "特定"をセット
            l_balanceReferenceUnit.taxType = WEB3TaxTypeDef.SPECIAL;
        }
        // 建区分
        if (ContractTypeEnum.LONG.equals(l_contractRow.getContractType()))
        {
            l_balanceReferenceUnit.contractType = "" + ContractTypeEnum.LONG.intValue();
        }
        else if (ContractTypeEnum.SHORT.equals(l_contractRow.getContractType()))
        {
            l_balanceReferenceUnit.contractType = "" + ContractTypeEnum.SHORT.intValue();
        }
        // 弁済区分、弁済期限値
        WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
        l_repaymentUnit.repaymentDiv = l_contractRow.getRepaymentType();
        l_repaymentUnit.repaymentTimeLimit = String.valueOf(l_contractRow.getRepaymentNum());
        l_balanceReferenceUnit.repayment = l_repaymentUnit;
        
        // 建日
        l_balanceReferenceUnit.openDate = l_contract.getOpenDate();
        // 建単価
        l_balanceReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
        // 建株数
        l_balanceReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblLockedQuantity);
        // 期日
        l_balanceReferenceUnit.closeDate = l_contract.getCloseDate();
        // 建代金
        l_balanceReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmount);
        // 決済状態区分
        l_balanceReferenceUnit.settlementState = WEB3EquitySettlementStateDef.SETTLING;
        // 返済可能フラグ
        l_balanceReferenceUnit.closeMarginFlag = false;
        // 現引現渡可能フラグ
        l_balanceReferenceUnit.swapFlag = false;
        // 建手数料
        l_balanceReferenceUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblSetupFee + l_dblSetupFeeTax);
        // 順日歩
        l_balanceReferenceUnit.interestFee = WEB3StringTypeUtility.formatNumber(l_dblInterestFee);
        // 逆日歩
        l_balanceReferenceUnit.payInterestFee = WEB3StringTypeUtility.formatNumber(l_dblPayInterestFee);
        // 貸株料
        l_balanceReferenceUnit.loanEquityFee = WEB3StringTypeUtility.formatNumber(l_dblLoanEquityFee);
        // 書換料
        l_balanceReferenceUnit.setupFee = WEB3StringTypeUtility.formatNumber(l_dblNameTransferFee + l_dblNameTransferFeeTax);
        // 管理費
        l_balanceReferenceUnit.managementFee = WEB3StringTypeUtility.formatNumber(l_dblManagementFee + l_dblManagementFeeTax);
        // その他
        l_balanceReferenceUnit.otherwise = WEB3StringTypeUtility.formatNumber(l_dblOther);
        
        // get建株時価情報()の戻り値 != nullの場合
        if (l_productQuote != null)
        {
	        // 評価損益
	        l_balanceReferenceUnit.appraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblProfitLossTotal);
	        
            WEB3EquityBizLogicProvider l_bizLogic =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            // 諸経費合計
            double l_dblTotalCost = l_bizLogic.calcExpenses(0,
                0,
                l_dblSetupFee,
                l_dblSetupFeeTax,
                l_dblNameTransferFee,
                l_dblNameTransferFeeTax,
                l_dblManagementFee,
                l_dblManagementFeeTax,
                l_dblInterestFee,
                l_dblPayInterestFee,
                l_dblLoanEquityFee,
                l_dblOther,
                l_contractRow.getContractType());
            double l_dblProfitLossCost = l_dblProfitLossTotal - l_dblTotalCost;
	        // 評価損益（諸経費考慮）
	        l_balanceReferenceUnit.appraisalProfitLossCost = WEB3StringTypeUtility.formatNumber(l_dblProfitLossCost);
            // 時価
            l_balanceReferenceUnit.currentPrice = WEB3StringTypeUtility.formatNumber(l_productQuote.getQuote());
            // 前日比
            l_balanceReferenceUnit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_productQuote.getComparedPreviousDay());
            // 時価取得時間(HH:MM)
            log.debug("時価取得区分:[" + l_productQuote.getQuoteFromDiv() + "]");
            Timestamp l_quoteTime = l_productQuote.getQuoteTime();
            if (l_quoteTime != null)
            {
                l_balanceReferenceUnit.currentPriceTime = WEB3DateUtility.formatDate(l_quoteTime, "HH:mm");
            }
        }
        else
        {
            // 時価が取得できなかった場合はnullをセット
            l_balanceReferenceUnit.appraisalProfitLoss = null;
            l_balanceReferenceUnit.appraisalProfitLossCost = null;
            l_balanceReferenceUnit.currentPrice = null;
            l_balanceReferenceUnit.comparedPreviousDay = null;
            l_balanceReferenceUnit.currentPriceTime = null;
        }
        
        // 新規建可能フラグ
        l_balanceReferenceUnit.tradingFlag = true;
        
        log.exiting(STR_METHOD_NAME);
        return l_balanceReferenceUnit;
    }
    
    /**
     * (get建株時価情報)<BR>
     * <BR>
     * 指定建株の銘柄の時価情報(時価、時価取得時間など)を取得する。 <BR>
     * <BR>
     * 下記手順で取得した時価情報を返却する。 <BR>
     * <BR>
     * １）銘柄コードの取得 <BR>
     * 　@株式銘柄 = パラメータ.建株.getProduct() <BR>
     * 　@銘柄コード = 株式銘柄.get銘柄コード() <BR>
     * <BR>
     * ２）市場コードの取得 <BR>
     * 　@市場 = 拡張金融オブジェクトマネージャ.getMarket(建株.市場ID) <BR>
     * 　@市場コード = 市場.市場コード <BR>
     * <BR>
     * ３）時価情報セット判定および時価情報の取得 <BR>
     * 　@ThreadLocalSystemAttributesRegistry. <BR>
     * 　@getAttribute(CURRENT_PRICE_INFO)でHashtableを取得 <BR>
     * <BR>
     * 　@３－１）該当銘柄の時価情報がセットされている場合 <BR>
     * 　@　@　@(Hashtable.get(銘柄コード + 市場コード) != nullの場合) <BR>
     * <BR>
     *         時価情報 = Hashtable.get(銘柄コード + 市場コード) <BR>
     * <BR>
     * 　@３－２）該当銘柄の時価情報がセットされていない場合 <BR>
     * 　@　@　@(Hashtable.get(銘柄コード + 市場コード) == nullの場合) <BR>
     * <BR>
     * 　@　@３－２－１）時価情報の取得 <BR>
     * 　@　@　@　@株式取引銘柄 = パラメータ.建株.getTradedProduct() <BR>
     * 　@　@　@　@時価情報 = 拡張プロダクトマネージャ.get時価情報(株式取引銘柄, "リアル") <BR>
     * 　@　@ <BR>
     * 　@　@３－２－２）時価情報の追加 <BR>
     * 　@　@　@　@取得したHashtableに銘柄コード + 市場コードをkeyとして時価情報を追加 <BR>
     * 　@　@　@　@Hashtable.put（銘柄コード + 市場コード, ３－２－１）にて取得した時価情報) <BR>
     * <BR>
     * 　@　@３－２－３）時価のセット <BR>
     * 　@　@　@　@ThreadLocalSystemAttributesRegistry.setAttribute()にて時価情報をセットする <BR>
     * 　@　@　@　@設定キー：　@CURRENT_PRICE_INFO <BR>
     * 　@　@　@　@値：　@３－２－２）にて時価情報を追加したHashtable <BR>
     * <BR>
     * ４）取得した時価情報を返却 <BR>
     * <BR>
     * ※当該メソッドを使用する場合は、各サービスインタセプタのonCallにて時価情報のセット処理(*)、 <BR>
     * 　@onReturn()およびonThrowable()メソッド内にて時価情報のクリア処理を行うこと <BR>
     * <BR>
     * (*)ThreadLocalSystemAttributesRegistry.setAttribute()にて <BR>
     * 　@ThreadLocalに時価情報の変数をセットする <BR>
     * 　@設定キー：　@CURRENT_PRICE_INFO <BR>
     * 　@値：　@Hashtable(新規に作成したHashtable) <BR>
     * <BR>
     * @@params l_subAccount - (補助口座)<BR>
     * @@params l_contract - (建株)<BR>
     * @@return WEB3EquityProductQuote - (株式銘柄時価情報)<BR>
     */
    public WEB3EquityProductQuote getContractCurrentPriceInfo(WEB3GentradeSubAccount l_subAccount, WEB3EquityContract l_contract)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractCurrentPriceInfo(WEB3GentradeSubAccount, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);
        
        // 銘柄コードの取得
        WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_contract.getProduct();
        String l_strProductCode = l_eqtypeProduct.getProductCode();
        
        // 市場コードの取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        Market l_market = null;
        try
        {
	        l_market = l_finObjectManager.getMarket(l_contract.getMarketId());
        } catch (NotFoundException l_ex) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "市場オブジェクトの取得に失敗しました");
        }
        String l_strMarketCode = l_market.getMarketCode();
        
        // 時価情報格納リストの取得
        String l_strKey = l_strProductCode + l_strMarketCode;
        Hashtable l_htProductQuote = 
            (Hashtable)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3MarginAttributeNameDef.CURRENT_PRICE_INFO);
        
        // 時価情報の取得
        WEB3EquityProductQuote l_productQuote =
            (WEB3EquityProductQuote)l_htProductQuote.get(l_strKey);
        
        // 該当銘柄の時価情報がセットされていない場合
        if (l_productQuote == null)
        {
            // 取引銘柄を取得
            WEB3EquityTradedProduct l_eqtypeTradedProduct = null;
            try
            {
	            l_eqtypeTradedProduct =
	                (WEB3EquityTradedProduct)l_contract.getTradedProduct();
            }
            catch (Exception l_ex)
            {
                return null;
            }
            
            // 時価情報を取得
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
                
            l_productQuote = l_productManager.getProductQuote(l_eqtypeTradedProduct, RealType.REAL);

			if (l_productQuote != null)
			{
            // 時価情報の追加
            l_htProductQuote.put(l_strKey, l_productQuote);
            
            // ThreadLocalに時価情報を追加したHashtableをセット
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MarginAttributeNameDef.CURRENT_PRICE_INFO,
                l_htProductQuote);
			}
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_productQuote;
    }
    
    /**
     * (getミニ株保有資産一覧)<BR>
     * （getMiniStockAssetsのオーバーロード）<BR>
     * <BR>
     * 指定条件に一致するミニ株保有資産オブジェクトの一覧を返却する。 <BR>
     * <BR>
     * １）　@戻り値オブジェクトのインスタンスを生成する。 <BR>
     * <BR>
     * ２）　@検索条件を追加する。 <BR>
     * <BR>
     * ２－１）　@引数.検索条件文字列の先頭に、 <BR>
     * 　@"口座ID = ?　@and　@補助口座ID = ? and 銘柄タイプ = ? and ミニ株区分 = "1：ミニ株"  and (数量+売付不能数量) > 0" <BR>
     * 　@を付加する。 <BR>
     * <BR>
     * ２－２）　@引数.検索条件データコンテナの先頭に、 <BR>
     * 　@　@　@　@　@検索条件文字列先頭に付加したパラメータリストを追加する。 <BR>
     * 　@　@　@　@　@※口座ID、補助口座IDは、引数の補助口座オブジェクトより設定する。 <BR>
     * 　@　@　@　@　@※銘柄タイプは、引数の銘柄タイプより設定する。 <BR>
     * <BR>
     * ３）　@QueryProcessor.doFindAllQuery( )により、保有資産オブジェクトのListを取得する。 <BR>
     * <BR>
     * 　@　@　@doFindAllQuery(,保有資産Row.TYPE <BR>
     *                                      ２－１）の検索条件文字列, <BR>
     *                                      引数のソート条件, <BR>
     *                                      null, <BR>
     *                                      ２－２）の検索条件データコンテナ) <BR>
     * <BR>
     * ４）　@検索結果を返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ（ProductTypeEnumオブジェクト）
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列
     * @@param l_strQueryDataContainerString - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getMiniStockAssets(WEB3GentradeSubAccount l_subAccount, ProductTypeEnum l_productType, String l_strQueryString, String[] l_strQueryDataContainer, String l_strSortCond)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMiniStockAssets(WEB3GentradeSubAccount, WEB3GentradeSubAccount, String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        // 追加する検索条件の作成
        String l_strWhere = "account_id = ? "
                            + "and sub_account_id = ? "
                            + "and product_type = ? "
                            + "and mini_stock_div = ? "
                            + "and (quantity + quantity_cannot_sell) > 0 ";
        // 引数の検索条件の先頭に追加
        if (l_strQueryString != null)
        {
	        l_strWhere += l_strQueryString;
        }
        
        // 追加する検索条件データコンテナの作成
        ArrayList l_lstBinds = new ArrayList();
        // 口座ID
        l_lstBinds.add(String.valueOf(l_subAccount.getAccountId()));
        // 補助口座ID
        l_lstBinds.add(String.valueOf(l_subAccount.getSubAccountId()));
        // 銘柄タイプ
        l_lstBinds.add(String.valueOf(l_productType.intValue()));
        // ミニ株区分
        l_lstBinds.add(WEB3MiniStockDivDef.MINI_STOCK);
        
        if (l_strQueryDataContainer != null)
        {
	        // 引数の検索条件データコンテナを追加
	        for (int i = 0; i < l_strQueryDataContainer.length; i++)
	        {
	            l_lstBinds.add(l_strQueryDataContainer[i]);
	        }
        }
        
        String[] l_strBinds = (String[])l_lstBinds.toArray(new String[0]);
        List l_lstAssets = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstAssets = l_queryProcessor.doFindAllQuery(AssetRow.TYPE,
                                                        l_strWhere,
                                                        l_strSortCond,
                                                        null,
                                                        l_strBinds);
        } catch (DataException e) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                e.getMessage());
        }
        
        if (l_lstAssets == null || l_lstAssets.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        ArrayList l_lstReturnValue = new ArrayList();
        for(int i = 0; i < l_lstAssets.size(); i++)
        {
            Asset l_asset = new WEB3EquityAsset((AssetRow)l_lstAssets.get(i));
            l_lstReturnValue.add(l_asset);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lstReturnValue;
    }
    
    /**
     * (get建株期日)<BR>
     * 建株.建日に設定する期日（closeDate）を計算し返す。<BR>
     * <BR>
     * 拡張ポジションヘルパー.get建株期日(建日, 弁済期限値)に<BR>
     * 処理を委譲（delegate）する。<BR>
     * @@param l_datOpenDate - (建日)<BR>
     * 建日。
     * @@param l_dblRepaymentNum - (弁済期限値)<BR>
     * 弁済期限値。
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getContractCloseDate(
        Date l_datOpenDate,
        double l_dblRepaymentNum)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractCloseDate(Date, double)";
        log.entering(STR_METHOD_NAME);
        
        Date l_datContractCloseDate =
            ((WEB3EquityPositionManagerHelper)m_helper).getContractCloseDate(
                l_datOpenDate, l_dblRepaymentNum);
        
        log.exiting(STR_METHOD_NAME);
        return l_datContractCloseDate;
    }
    
    /**
     * (get建株ListBy注文単位)<BR>
     * 指定された注文データに対する、建株データを全て取得し、<BR>
     * 建株ParamsのListを作成して返す。<BR>
     * <BR>
     * 拡張データマネージャ.get建株ListBy注文単位(注文単位ID)にdelegateする。<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID。
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getContractListByOrderUnit(
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractListByOrderUnit(long)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityPositionManagerHelper.WEB3EquityPersistentDataManager l_dataManager =
            (WEB3EquityPositionManagerHelper.WEB3EquityPersistentDataManager)m_helper.getPersistenceManager();
        List l_lisContractListByOrderUnit =
            l_dataManager.getContractListByOrderUnit(l_lngOrderUnitId);
        
        log.exiting(STR_METHOD_NAME);
        return l_lisContractListByOrderUnit;
    }

    /**
     * (get建株一覧)<BR>
     * 引数の顧客レンジで、有効な建株を全て取得し、一覧を返却する。 <BR>
     * <BR>
     * １）　@建株テーブルの検索を実行する。 <BR>
     * 　@QueryProcessor.doFindAllQuery()により、 <BR>
     * 　@以下検索条件にて、建株テーブル（eqtype_contract）を検索する。 <BR>
     * <BR>
     * 　@　@　@[条件] <BR>
     * 　@　@引数.From口座ID ≦ 建株.口座ID　@かつ、 <BR>
     * 　@　@建株.口座ID ≦ 引数.To口座ID　@かつ、 <BR>
     * 　@　@建株.建株数 ≠ 0　@かつ、 <BR>
     * 　@　@建株.銘柄タイプ ＝ ”株式” <BR>
     * <BR>
     * 　@　@※口座IDで昇順（ASC）にsortすること。 <BR>
     * <BR>
     * ２）　@検索結果を返却する。 <BR>
     * @@param l_rangeFrom - From口座ID
     * @@param l_rangeTo - To口座ID
     * @@return List
     * @@throws WEB3BaseException
     */
    public static List getContracts(long l_lngRangeFrom, long l_lngRangeTo) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = " getContracts(long, long)";
        log.entering(STR_METHOD_NAME);

        List l_returnValue = new ArrayList();

        // 建株テーブルの検索を実行する。
        String l_strWhere = "account_id >= ? and account_id <= ? and quantity != ? and product_type = ?";
        String l_strOrderBy = "account_id ASC";

        Object[] l_objWhereValue = new Object[4];
        l_objWhereValue[0] = new Long(l_lngRangeFrom);
        l_objWhereValue[1] = new Long(l_lngRangeTo);
        l_objWhereValue[2] = new BigDecimal(0);
        l_objWhereValue[3] = ProductTypeEnum.EQUITY;

        QueryProcessor l_queryProcessor;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();

            l_returnValue = l_queryProcessor.doFindAllQuery(EqtypeContractRow.TYPE,
                l_strWhere, l_strOrderBy, null, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPositionManager.class.getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPositionManager.class.getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_returnValue;
    }

    /**
     * (get強制決済期日到来建株一覧)<BR>
     * 引数の顧客レンジで、決済期日が到来した建株を全て取得し、一覧を返却する。<BR>
     * ※決済期日は、設定した基準日を加味して算出する。<BR>
     * <BR>
     * １）　@業務日付を取得する。<BR>
     * <BR>
     * 　@GtlUtils.getTradingSystem().getBizDate()をCallする。<BR>
     * <BR>
     * ２）　@建株テーブルの検索を実行する。<BR>
     * 　@QueryProcessor.doFindAllQuery()により、<BR>
     * 　@以下検索条件にて、建株テーブル（eqtype_contract）を検索する。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@引数.From口座ID ≦ 建株.口座ID　@かつ、<BR>
     * 　@　@建株.口座ID ≦ 引数.To口座ID　@かつ、<BR>
     * 　@　@建株.期日 ≦ 強制決済期日（*1）　@かつ、<BR>
     * 　@　@建株.建株数 ≠ 0　@かつ、<BR>
     * 　@　@建株.銘柄タイプ ＝ ”株式”<BR>
     * <BR>
     * 　@　@※口座IDで昇順（ASC）にsortすること。<BR>
     * <BR>
     * 　@　@（*1）強制決済期日は以下計算式により算出する。<BR>
     * 　@　@　@強制決済期日 ＝ 業務日付 ＋ 引数.証券会社.信用強制決済期日算出日数<BR>
     * <BR>
     * ３）　@２）の検索結果を返却する。<BR>
     * @@param l_institution - 証券会社
     * @@param l_rangeFrom - From口座ID
     * @@param l_rangeTo - To口座ID
     * @@return List
     * @@throws WEB3BaseException
     */
    public static List getForcedSettleCloseDateContractList(
        Institution l_institution,
        long l_lngRangeFrom,
        long l_lngRangeTo)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = " getForcedSettleCloseDateContractList(Institution, long, long)";
        log.entering(STR_METHOD_NAME);

        //１）　@業務日付を取得する。
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        //２）　@建株テーブルの検索を実行する。
        List l_lisReturnValue = new ArrayList();

        String l_strWhere = "account_id >= ? and account_id <= ? and to_char(close_date,'yyyyMMdd') <= ? "
            + "and quantity != ? and product_type = ?";

        String l_strOrderBy = "account_id ASC";

        //（*1）強制決済期日は以下計算式により算出する。
        InstitutionRow l_InstitutionRow = (InstitutionRow)l_institution.getDataSourceObject();
        Date l_datForcedSettleCloseDate = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(l_InstitutionRow.getForcedsettleorderClosedayCnt());

        Object[] l_objWhereValues = new Object[5];
        l_objWhereValues[0] = new Long(l_lngRangeFrom);
        l_objWhereValues[1] = new Long(l_lngRangeTo);
        l_objWhereValues[2] = WEB3DateUtility.formatDate(l_datForcedSettleCloseDate, "yyyyMMdd");
        l_objWhereValues[3] = new BigDecimal(0);
        l_objWhereValues[4] = ProductTypeEnum.EQUITY;

        QueryProcessor l_queryProcessor;

        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();

            l_lisReturnValue = l_queryProcessor.doFindAllQuery(EqtypeContractRow.TYPE,
                l_strWhere, l_strOrderBy, null, l_objWhereValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPositionManager.class.getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPositionManager.class.getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisReturnValue;
    }
}
@
