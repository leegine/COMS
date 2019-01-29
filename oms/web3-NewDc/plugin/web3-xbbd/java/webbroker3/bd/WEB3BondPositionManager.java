head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondPositionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 債券ポジションマネージャ (WEB3BondPositionManager.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17 周捷(中訊) 新規作成
                    2006/10/08 周捷 (中訊) 仕様変更・モデル111
                    2006/10/12 徐大方(中訊)WEBⅢ開発標準の見直しの対応（newBigDecimal部分）
 */

package webbroker3.bd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondAssetImpl;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondPositionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondAssetCheckDef;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券ポジションマネージャ)<BR>
 * 債券ポジションマネージャ
 *
 * @@author 周捷(中訊)
 * @@version 1.0
 */
public class WEB3BondPositionManager extends BondPositionManagerImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondPositionManager.class);

    /**
     * PositionManagerHelperをOverride
     */
    public WEB3BondPositionManager()
    {
        super();
        super.m_helper = new WEB3BondPositionManagerHelper(ProductTypeEnum.BOND );
    }
    /**
     * (get債券保有資産一覧)
     * （getAssetsのオーバーロード） <BR>
     * <BR>
     * 指定条件に一致する債券保有資産オブジェクトの一覧を返却する。<BR>
     * <BR>
     * １）　@検索条件を追加する。 <BR>
     * <BR>
     * １－１）　@引数.検索条件文字列の先頭に、 <BR>
     * "口座ID = ? " + <BR>
     * "and　@補助口座ID = ? " + <BR>
     * "and 銘柄タイプ = ProductTypeEnum.債券 " +<BR>
     * "and ミニ株区分 = 0：DEFAULT（ミニ株でない） " + <BR>
     * "and (数量+売付不能数量) > 0 "<BR>
     * を付加する。<BR>
     * <BR>
     * １－２）　@引数.検索条件データコンテナの先頭に、<BR>
     * 　@　@　@　@　@検索条件文字列先頭に付加したパラメータリストを追加する。<BR>
     * 　@　@　@　@　@※口座ID、補助口座IDは、引数の補助口座オブジェクトより設定する。<BR>
     * <BR>
     * ３）　@QueryProcessor.doFindAllQuery( )により、保有資産オブジェクトのListを取得する。<BR>
     * <BR>
     * doFindAllQuery(,保有資産Row.TYPE<BR>
     *                               ２－１）の検索条件文字列, <BR>
     *                               引数のソート条件,<BR>
     *                               null,<BR>
     *                               ２－２）の検索条件データコンテナ)<BR>
     *<BR>
     * ４）　@検索結果より保有資産オブジェクトを生成し、<BR>
     * 　@Listとして返却する。 <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_objQueryContainers - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44D0354A0000
     */
    public List getAssets(SubAccount l_subAccount,
        String l_strQueryString,
        Object[] l_objQueryContainers,
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getAssets(SubAccount, String, Object[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）　@検索条件を追加する。
        StringBuffer l_sbQueryString = new StringBuffer();
        //　@"口座ID = ? " +
        l_sbQueryString.append(" account_id = ?");
        //"and　@補助口座ID = ? " +
        l_sbQueryString.append(" and sub_account_id = ?");
        //"and 銘柄タイプ = ProductTypeEnum.債券 " +
        l_sbQueryString.append(" and product_type = ?");
        //"and ミニ株区分 = 0：DEFAULT（ミニ株でない） " +
        l_sbQueryString.append(" and mini_stock_div = ?");
        //"and (数量+売付不能数量) > 0 "
        l_sbQueryString.append(" and (quantity + quantity_cannot_sell) > ?  ");
        //を付加する。
        if (l_strQueryString != null)
        {
            l_sbQueryString.append(l_strQueryString);
        }

        //１－２）　@引数.検索条件データコンテナの先頭に、
        //検索条件文字列先頭に付加したパラメータリストを追加する。
        //※口座ID、補助口座IDは、引数の補助口座オブジェクトより設定する。
        List l_lisArrayList = new ArrayList();
        l_lisArrayList.add(new Long(l_subAccount.getAccountId()));
        l_lisArrayList.add(new Long(l_subAccount.getSubAccountId()));
        l_lisArrayList.add(new Long(ProductTypeEnum.BOND.intValue()));
        l_lisArrayList.add(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        l_lisArrayList.add(new Long(0));

        Object[] l_objNewQueryContainers = null;
        if (l_objQueryContainers != null && l_objQueryContainers.length > 0)
        {
            for (int i = 0; i < l_objQueryContainers.length; i++)
            {
                l_lisArrayList.add(l_objQueryContainers[i]);
            }
            l_objNewQueryContainers = new Object[5 + l_objQueryContainers.length];
            l_lisArrayList.toArray(l_objNewQueryContainers);
        }
        else
        {
            l_objNewQueryContainers = new Object[l_lisArrayList.size()];
            l_lisArrayList.toArray(l_objNewQueryContainers);
        }

        //３）　@QueryProcessor.doFindAllQuery( )により、保有資産オブジェクトのListを取得する。
        //　@　@　@doFindAllQuery(,保有資産Row.TYPE
        //          ２－１）の検索条件文字列,
        //          引数のソート条件,
        //          null,
        //          ２－２）の検索条件データコンテナ)
        List l_lisAssets = null;
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_lisAssets = l_qp.doFindAllQuery(
                AssetRow.TYPE,
                l_sbQueryString.toString(),
                l_strSortCond,
                null,
                l_objNewQueryContainers);
        }
        catch (DataException l_ex)
        {
            log.error("__error in 債券保有資産オブジェクトの取得__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //４）　@検索結果より保有資産オブジェクトを生成し、
        //Listとして返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisAssets;
    }

    /**
     * (get保有資産)<BR>
     * get保有資産 <BR>
     * <BR>
     * １）assetテーブルから以下の条件に該当する保有資産を取得する <BR>
     * 口座ID=引数.口座ID <BR>
     * 補助口座ID=引数.補助口座ID<BR>
     * 銘柄ID=引数.銘柄ID  <BR>
     * 税区分=引数.税区分 <BR>
     * ミニ株区分=DEFAULT（ミニ株以外）
     * <BR>
     * ２）　@assetテーブルの検索結果を以下のように戻す <BR>
     * <BR>
     * 保有資産=null  <BR>
     * (検索結果List != null && ! 検索結果List.isEmpty())の場合、 <BR>
     * { <BR>
     *  保有資産 = new BondAssetImpl((AssetRow)検索結果List.get(0)) <BR>
     * } <BR>
     * <BR>
     * return 保有資産 <BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_lngSubAccountId - (補助口座ID)<BR>
     * 補助口座ID<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_taxType - (税区分)<BR>
     * 税区分<BR>
     * @@return Asset
     * @@throws WEB3BaseException
     * @@roseuid 44C44AF103CC
     */
    public Asset getAsset(long l_lngAccountId,
        long l_lngSubAccountId, long l_lngProductId,
        TaxTypeEnum l_taxType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAsset(long, long, long, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        List l_lisAssets = null;

        //１）assetテーブルから以下の条件に該当する保有資産を取得する
        //口座ID=引数.口座ID
        //補助口座ID=引数.補助口座ID
        //銘柄ID=引数.銘柄ID
        //税区分=引数.税区分
        //ミニ株区分=DEFAULT（ミニ株以外）
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" account_id = ?");
        l_sbQueryString.append(" and sub_account_id = ?");
        l_sbQueryString.append(" and product_id = ?");
        l_sbQueryString.append(" and tax_type = ?");
        l_sbQueryString.append(" and mini_stock_div = ? ");

        Object[] l_objQueries =
            new Object[]{
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                new Long(l_lngProductId),
                l_taxType,
                WEB3MiniStockDivDef.DEFAULT_MINI_STOCK};
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_lisAssets = l_qp.doFindAllQuery(
                AssetRow.TYPE,
                l_sbQueryString.toString(),
                null,
                l_objQueries);
        }
        catch (DataException l_ex)
        {
            log.error("__error in 債券保有資産オブジェクトの取得__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //２）　@assetテーブルの検索結果を以下のように戻す
        //
        //保有資産=null
        //(検索結果List != null && ! 検索結果List.isEmpty())の場合、
        //{
        // 保有資産 = new BondAssetImpl((AssetRow)検索結果List.get(0))
        //}
        Asset l_asset = null;
        if (l_lisAssets != null && !l_lisAssets.isEmpty())
        {
            if (l_lisAssets.size() > 1)
            {
                log.debug("データ不整合エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            l_asset = new BondAssetImpl((AssetRow)l_lisAssets.get(0));
        }
        log.exiting(STR_METHOD_NAME);
        return l_asset;
    }

    /**
     * (updateロック中数量)<BR>
     * <BR>
     * 【ロック中の資産詳細テーブル】のロック中数量の調整を行う。   <BR>
     *（updateLockedQuantity(long accountId,   <BR>
     * long subAccountId,  <BR>
     * long orderUnitId,  <BR>
     * long productId,   <BR>
     * double lockedQtyToBeAdjusted)   <BR>
     * のオーバーライド） <BR>
     *   <BR>
     * １）　@保有資産チェック区分をチェック<BR>
     * 　@１－１）債券部店別条件を生成する。<BR>
     *     　@　@　@　@[引数] <BR>
     *     　@　@ 　@　@部店ID：拡張アカウントマネージャ.get顧客(引数.口座ID).get部店().get部店ID() <BR>
     * 　@１－２）債券部店別条件.get保有資産チェック区分 == 'チェックしない'の場合、処理をせずにreturnする。<BR>
     * <BR>
     * ２）　@調整数量の更新　@　@<BR>
     * 　@２－１）拡張債券注文単位を取得する。　@　@<BR>
     *     　@　@　@　@[引数]　@　@<BR>
     *     　@　@ 　@　@注文単位ID：拡張債券注文マネージャ.get債券注文単位（引数.注文単位ID）　@　@<BR>
     * 　@２－２）調整数量を更新する。　@　@<BR>
     * 　@　@　@　@　@・拡張債券注文単位.注文約定区分＝約定済　@　@<BR>
     * 　@　@　@　@　@　@　@調整数量＝拡張債券注文単位.注文数量　@× -1　@　@<BR>
     * 　@　@　@　@　@・拡張債券注文単位.注文約定区分＝未約定　@かつ　@拡張債券注文単位.注文状態＝発注済（新規注文）　@かつ　@拡張債券注文単位.約定最終通番＞0　@　@<BR>
     * 　@　@　@　@　@　@　@調整数量＝拡張債券注文単位.注文数量　@　@<BR>
     * 　@　@　@　@　@・拡張債券注文単位.注文約定区分＝取消済　@かつ　@拡張債券注文単位.約定最終通番＞0　@　@<BR>
     * 　@　@　@　@　@　@　@調整数量＝拡張債券注文単位.注文数量　@× -1　@　@<BR>
     *    <BR>
     *    ３）引数.調整数量が0と等しい場合は、処理をせずにreturnする。   <BR>
     *    <BR>
     *    ４）this.get保有資産(口座ID, 補助口座ID, 銘柄ID, 税区分)をコールし、 <BR>
     *        保有資産オブジェクトを取得する。 <BR>
     *　@[引数] <BR>
     *　@　@口座ID　@　@　@：引数.口座ID <BR>
     *　@　@補助口座ID：引数.補助口座ID <BR>
     *　@　@銘柄ID　@　@　@：引数.銘柄ID <BR>
     *　@　@税区分　@　@　@：債券注文単位オブジェクト.get税区分 <BR>
     *     <BR>
     *    ５)保有資産オブジェクト.資産IDをキーにして、Locked_Asset_Detailsテーブルを検索する。 <BR>
     *        LockedAssetDetailsRow lockedAssetRow = LockedAssetDetailsDao.findRowByPk(資産Id);  <BR>
     *     <BR>
     *    　@５-１)　@ 該当資産IDのLockedAssetDetailsRowが存在しない場合、Insertを行う。   <BR>
     *        －新規ロック中数量オブジェクト（LockedAssetDetailsParams）のプロパティを設定する。   <BR>
     *        　@　@新規ロック中数量オブジェクト.setAssetId(資産Id);   <BR>
     *        　@　@新規ロック中数量オブジェクト.setAccountId(引数.口座ID);   <BR>
     *        　@　@新規ロック中数量オブジェクト.setSubAccountId(引数.補助口座ID);   <BR>
     *        　@　@新規ロック中数量オブジェクト.setCreatedTimestamp(GtlUtils.getSystemTimestamp());   <BR>
     *        　@　@新規ロック中数量オブジェクト.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());   <BR>
     *        　@　@新規ロック中数量オブジェクト.setLockedQuantity（引数.調整数量）;   <BR>
     *        －DB更新QueryProcessorでデータベースに新規行を追加する。   <BR>
     *        　@　@QueryProcessor qp = Processors.getDefaultProcessor();   <BR>
     *        　@　@qp.doInsertQuery(新規ロック中数量オブジェクト);   <BR>
     *     <BR>
     *        ５-２)　@ 該当資産IDのLockedAssetDetailsRowがすでに存在した場合、Updateを行う。  <BR>
     *        －新ロック中数量を算出する。   <BR>
     *        　@　@新ロック中数量=lockedAssetRow.ロック中数量 + 引数.調整数量  <BR>
     *        －算出した結果の新ロック中数量をゼロ調整する。（0と等しい場合、0.0Dをセットする）   <BR>
     *        　@　@(GtlUtils.Double.isZero(新ロック中数量))の場合、  <BR>
     *        　@　@新ロック中数量=0.0D;   <BR>
     *     <BR>
     *    　@－更新ロック中数量オブジェクト（LockedAssetDetailsParams）のプロパティを設定する。   <BR>
     *    　@　@　@更新ロック中数量オブジェクト.setAssetId(資産Id);   <BR>
     *    　@　@　@更新ロック中数量オブジェクト.setAccountId(引数.口座ID);   <BR>
     *    　@　@　@更新ロック中数量オブジェクト.setSubAccountId(引数.補助口座ID);   <BR>
     *    　@　@　@更新ロック中数量オブジェクト.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());   <BR>
     *    　@　@　@更新ロック中数量オブジェクト.setLockedQuantity（新ロック中数量）;   <BR>
     *    　@－DB更新QueryProcessorでデータベースに該当行を更新する。   <BR>
     *        　@　@QueryProcessor qp = Processors.getDefaultProcessor();　@   <BR>
     *        　@　@qp.doUpdateQuery(更新ロック中数量オブジェクト);     <BR>
     *　@<BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_lngSubAccountId - (補助口座ID)<BR>
     * 補助口座ID<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_dblLockedQtyToBeAdjusted - (調整数量)<BR>
     * 調整数量<BR>
     * @@roseuid 44D992BB02DE
     */
    public void updateLockedQuantity(long l_lngAccountId,
        long l_lngSubAccountId,
        long l_lngOrderUnitId,
        long l_lngProductId,
        double l_dblLockedQtyToBeAdjusted)
    {
        final String STR_METHOD_NAME = " updateLockedQuantity(long, long, long, long, double)";
        log.entering(STR_METHOD_NAME);

        //１）　@保有資産チェック区分をチェック
        //拡張アカウントマネージャ取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            // １－１）債券部店別条件を生成する。
            //    [引数]
            //    部店ID：拡張アカウントマネージャ.get顧客(引数.口座ID).get部店().get部店ID()
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
            log.error("__error in 部店用プリファ@レンステーブルからレコードを取得__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //２）  調整数量の更新
        //  ２－１）拡張債券注文単位を取得する。
        //            [引数]
        //             注文単位ID：拡張債券注文マネージャ.get債券注文単位（引数.注文単位ID）
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        BondOrderUnit l_orderUnit =
            (BondOrderUnit) l_bondOrderManager.getOrderUnit(l_lngOrderUnitId);
        
        //  ２－２）調整数量を更新する。
        //          ・拡張債券注文単位.注文約定区分＝約定済
        //              調整数量＝拡張債券注文単位.注文数量  × -1
        //          ・拡張債券注文単位.注文約定区分＝未約定  かつ  拡張債券注文単位.注文状態＝発注済（新規注文）　@かつ　@拡張債券注文単位.約定最終通番＞0
        //              調整数量＝拡張債券注文単位.注文数量
        //          ・拡張債券注文単位.注文約定区分＝取消済  かつ  拡張債券注文単位.約定最終通番＞0
        //              調整数量＝拡張債券注文単位.注文数量  × -1
        BondOrderUnitRow l_row = (BondOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderExecStatus = l_row.getOrderExecStatus();
        int l_intLastExecutionSerialNo = l_row.getLastExecutionSerialNo();
        
        //約定
        if(WEB3BondOrderExecStatusDef.EXECUTED.equals(l_strOrderExecStatus))
        {
            l_dblLockedQtyToBeAdjusted = l_row.getQuantity() * -1D;
        }
        //約定後の約定取消
        else if((WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_strOrderExecStatus))
                && (OrderStatusEnum.ORDERED.equals(l_row.getOrderStatus()))
                && (l_intLastExecutionSerialNo > 0))
        {
            l_dblLockedQtyToBeAdjusted = l_row.getQuantity();
        }
        //約定後の取消受付
        else if((WEB3BondOrderExecStatusDef.CANCELED.equals(l_strOrderExecStatus))
                && (l_intLastExecutionSerialNo > 0))
        {
            l_dblLockedQtyToBeAdjusted = l_row.getQuantity() * -1D;
        }
        
        //３）　@引数.調整数量が0と等しい場合は、処理をせずにreturnする。
        if (l_dblLockedQtyToBeAdjusted == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        try
        {
            //４）　@this.get保有資産(口座ID, 補助口座ID, 銘柄ID, 税区分)をコールし、
            //保有資産オブジェクトを取得する。
            //[引数]
            //  口座ID　@　@　@：引数.口座ID
            //  補助口座ID：引数.補助口座ID
            //  銘柄ID　@　@　@：引数.銘柄ID
            //  税区分　@　@　@：債券注文単位オブジェクト.get税区分
            Asset l_asset = null;
            if (l_orderUnit != null)
            {
                l_asset = this.getAsset(
                    l_lngAccountId, l_lngSubAccountId, l_lngProductId, l_orderUnit.getTaxType());
            }

            //５)　@保有資産オブジェクト.資産IDをキーにして、Locked_Asset_Detailsテーブルを検索する。
            //LockedAssetDetailsRow lockedAssetRow = LockedAssetDetailsDao.findRowByPk(資産Id);
            LockedAssetDetailsRow l_lockedAssetRow = null;
            LockedAssetDetailsParams l_lockedAssetParams = null;
            QueryProcessor l_qp = Processors.getDefaultProcessor();

            if (l_asset != null)
            {
                l_lockedAssetRow = LockedAssetDetailsDao.findRowByAssetId(l_asset.getAssetId());

                //５-１)　@ 該当資産IDのLockedAssetDetailsRowが存在しない場合、Insertを行う。
                if (l_lockedAssetRow == null)
                {
                    l_lockedAssetParams = new LockedAssetDetailsParams();

                    // －新規ロック中数量オブジェクト（LockedAssetDetailsParams）のプロパティを設定する。               
                    //    新規ロック中数量オブジェクト.setAssetId(資産Id);  
                    l_lockedAssetParams.setAssetId(l_asset.getAssetId());
                    //    新規ロック中数量オブジェクト.setAccountId(引数.口座ID);
                    l_lockedAssetParams.setAccountId(l_lngAccountId);
                    //    新規ロック中数量オブジェクト.setSubAccountId(引数.補助口座ID);
                    l_lockedAssetParams.setSubAccountId(l_lngSubAccountId);
                    //    新規ロック中数量オブジェクト.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_lockedAssetParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                    //    新規ロック中数量オブジェクト.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_lockedAssetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    //    新規ロック中数量オブジェクト.setLockedQuantity（引数.調整数量）;
                    l_lockedAssetParams.setLockedQuantity(l_dblLockedQtyToBeAdjusted);

                    //   　@－DB更新QueryProcessorでデータベースに新規行を追加する。
                    //   　@　@　@QueryProcessor qp = Processors.getDefaultProcessor();
                    //   　@　@　@qp.doInsertQuery(新規ロック中数量オブジェクト);
                    l_qp.doInsertQuery(l_lockedAssetParams);
                }
                else
                {
                    l_lockedAssetParams = new LockedAssetDetailsParams(l_lockedAssetRow);

                    //５-２)　@ 該当資産IDのLockedAssetDetailsRowがすでに存在した場合、Updateを行う。
                    //   －新ロック中数量を算出する。
                    //   　@ 新ロック中数量=lockedAssetRow.ロック中数量 + 引数.調整数量
                    BigDecimal l_bdLockedQuantity = new BigDecimal(String.valueOf(l_lockedAssetRow.getLockedQuantity()));
                    BigDecimal l_bdLockedQtyToBeAdjusted = new BigDecimal(String.valueOf(l_dblLockedQtyToBeAdjusted));
                    double l_dblNewQuantity =
                        l_bdLockedQuantity.add(l_bdLockedQtyToBeAdjusted).doubleValue() ;

                    //－算出した結果の新ロック中数量をゼロ調整する。（0と等しい場合、0.0Dをセットする）
                    // (GtlUtils.Double.isZero(新ロック中数量))の場合、
                    if (GtlUtils.Double.isZero(l_dblNewQuantity))
                    {
                        //新ロック中数量=0.0D;
                        l_dblNewQuantity = 0.0D;
                    }
                    //－更新ロック中数量オブジェクト（LockedAssetDetailsParams）のプロパティを設定する。

                    //更新ロック中数量オブジェクト.setAccountId(引数.口座ID);
                    l_lockedAssetParams.setAccountId(l_lngAccountId);

                    //更新ロック中数量オブジェクト.setSubAccountId(引数.補助口座ID);
                    l_lockedAssetParams.setSubAccountId(l_lngSubAccountId);

                    //更新ロック中数量オブジェクト.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_lockedAssetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                    //更新ロック中数量オブジェクト.setLockedQuantity（新ロック中数量）;
                    l_lockedAssetParams.setLockedQuantity(l_dblNewQuantity);

                    //－DB更新QueryProcessorでデータベースに該当行を更新する。
                    //   　@　@　@QueryProcessor qp = Processors.getDefaultProcessor();　@
                    //   　@　@　@qp.doUpdateQuery(更新ロック中数量オブジェクト);
                    l_qp.doUpdateQuery(l_lockedAssetParams);
                }
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__error in DB conncetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataException l_ex)
        {
            log.error("__error in DB conncetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
