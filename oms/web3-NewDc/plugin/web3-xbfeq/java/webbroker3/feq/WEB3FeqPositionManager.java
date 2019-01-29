head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqPositionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 外国株式ポジションマネージャ(WEB3FeqPositionManager.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/14 呉艶飛(中訊) 新規作成
                   2005/07/27 魏馨(中訊) レビュー
                   2010/09/08 趙天月(中訊) モデル545
*/
package webbroker3.feq;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqAssetImpl;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqPositionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式ポジションマネージャ) <BR>
 * 外国株式ポジションマネージャ<BR>
 * <BR>
 * @@ author 呉艶飛 <BR>
 * @@ version 1.0<BR>
 */
public class WEB3FeqPositionManager extends FeqPositionManagerImpl 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FeqPositionManager.class);
    
    protected WEB3FeqPositionManagerHelper m_helper;
    protected ProductTypeEnum m_tradingType;
    /**
     * getPersistenceManager
     * 外国株式更新データマネージャを取得する。 
     * ポジションヘルパー.getPersistenceManager()の戻り値を返却する。
     */
    public WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager getPersistenceManager()
    {
        WEB3FeqPositionManagerHelper l_positionManagerHelper = 
            new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)l_positionManagerHelper.getPersistenceManager();
        
        return l_dataManager;
    }

    /**
     * @@roseuid 42CE39E8009C
     */
    public WEB3FeqPositionManager() 
    {
        m_helper = null;
        m_tradingType = null;
        m_tradingType = ProductTypeEnum.FOREIGN_EQUITY;
        m_helper = new WEB3FeqPositionManagerHelper(m_tradingType);
        super.m_helper = m_helper; 
    }
    
    /**
     * (get保有資産) <BR>
     * 保有資産テーブルから、 <BR>
     * 以下の条件に該当する保有資産を取得し返却する。 <BR>
     *  <BR>
     * 口座ＩＤ： 引数.口座ＩＤ  <BR>
     * 補助口座ＩＤ： 引数.補助口座ＩＤ  <BR>
     * 銘柄ＩＤ： 引数.銘柄ＩＤ  <BR>
     * 税区分： 引数.税区分  <BR>
     * ミニ株区分： 0：DEFAULT（ミニ株以外） <BR>
     * @@param l_lngAccountId - (口座ID) <BR>
     * 口座ID
     * 
     * @@param l_lngSubAccountId - (補助口座ID)
     * 
     * @@param l_lngProductId - (銘柄ID)
     * 
     * @@param l_taxType - (税区分)
     * 
     * @@return Asset
     * @@throws WEB3BaseException
     * @@roseuid 429466E70186
     */
    public Asset getAsset(
        long l_lngAccountId, 
        long l_lngSubAccountId, 
        long l_lngProductId, 
        TaxTypeEnum l_taxType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAsset(long,long,long)";
        log.entering(STR_METHOD_NAME);
        
        FeqAssetImpl l_asset = null;
        try
        {
            QueryProcessor l_qp = null;
            List l_lisRows = null;
            //以下の条件に該当する保有資産を取得し返却する。
            
            l_qp = Processors.getDefaultProcessor();
            String l_strWhere = "account_id = ? and sub_account_id = ? and product_id = ? and tax_type = ? and mini_stock_div = ?";
            Object[] l_objBinds = new Object[5];
            
            //口座ＩＤ： 引数.口座ＩＤ
            l_objBinds[0] = new Long(l_lngAccountId);
            
            //補助口座ＩＤ： 引数.補助口座ＩＤ
            l_objBinds[1] = new Long(l_lngSubAccountId);
            
            //銘柄ＩＤ： 引数.銘柄ＩＤ
            l_objBinds[2] = new Long(l_lngProductId);
            
            //税区分： 引数.税区分
            l_objBinds[3] = l_taxType;
            
            //ミニ株区分： 0：DEFAULT（ミニ株以外）
            l_objBinds[4] = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;
            l_lisRows = l_qp.doFindAllQuery(AssetRow.TYPE, l_strWhere, l_objBinds);

            int l_intSize = 0;
            if (l_lisRows != null && !l_lisRows.isEmpty())
            {
                l_intSize = l_lisRows.size();
            }
            if (l_intSize > 0)
            {
                l_asset = new FeqAssetImpl((AssetRow)l_lisRows.get(0));
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_asset;
    }
    
    /**
     * (getロック中の資産詳細) <BR>
     * ロック中の資産詳細行（：LockedAssetDetailsParams）を取得する。 <BR>
     *  <BR>
     * １）　@保有資産取得 <BR>
     * 　@this.get保有資産()にて保有資産を取得する。 <BR>
     *  <BR>
     * 　@[get保有資産()に指定する引数] <BR>
     * 　@口座ＩＤ：　@口座ＩＤ <BR>
     * 　@補助口座ＩＤ：　@補助口座ＩＤ  <BR>
     * 　@銘柄ＩＤ：　@銘柄ＩＤ  <BR>
     * 　@税区分：　@税区分  <BR>
     *  <BR>
     * ２）　@ロック中の資産詳細行（：LockedAssetDetailsParams）取得 <BR>
     * 　@１）で取得した保有資産.getAssetId()に該当する行を <BR>
     * 　@　@　@ロック中の資産詳細を取得し返却する。 <BR>
     * @@param l_lngAccountId - (口座ID)
     * 
     * @@param l_lngSubAccountId - (補助口座ID)
     * 
     * @@param l_lngProductId - (銘柄ID)
     * 
     * @@param l_taxType - (税区分)
     * 
     * @@return LockedAssetDetailsParams
     * @@throws WEB3BaseException
     * @@roseuid 42A9477F00DC
     */
    public LockedAssetDetailsParams getLockedAssetDetails(
        long l_lngAccountId, 
        long l_lngSubAccountId, 
        long l_lngProductId, 
        TaxTypeEnum l_taxType)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLockedAssetDetails(long,long,long,TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@保有資産取得
        FeqAssetImpl l_asset = null;
        l_asset = (FeqAssetImpl)this.getAsset(l_lngAccountId, l_lngSubAccountId,l_lngProductId, l_taxType);
        
        if (l_asset == null)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //２）　@ロック中の資産詳細行（：LockedAssetDetailsParams）取得
        //）で取得した保有資産.getAssetId()に該当する行をロック中の資産詳細を取得し返却する。
        long l_assetId = l_asset.getAssetId();
        
        LockedAssetDetailsRow l_assetDetailsRow = null;
        try
        {
            l_assetDetailsRow = LockedAssetDetailsDao.findRowByAssetId(l_assetId);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        if (l_assetDetailsRow != null)
        {
            log.exiting(STR_METHOD_NAME);
            return new LockedAssetDetailsParams (l_assetDetailsRow);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    
    }
    
    /**
     * (updateトランザクション) <BR>
     * 手数料按分計算（一口約定）を実施し、 <BR>
     * トランザクションデータを更新する。 <BR>
     * <BR>
     * 外国株式ポジションヘルパー.updateトランザクション()に <BR>
     * 委譲（deligate）する。<BR>
     * @@param l_lngOrderUnitId - (注文単位ＩＤ)
     * @@param l_blnIsCancel - (is取消) <BR>
     * 約定取消かの判定 <BR>
     *  <BR>
     * ture：約定取消 <BR>
     * false：約定 <BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A808210269
     */
    public void updateTransaction(long l_lngOrderUnitId, boolean l_blnIsCancel) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateTransaction(long,boolean)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式ポジションヘルパー.updateトランザクション()に委譲（deligate）する。
        m_helper.updateTransaction(l_lngOrderUnitId, l_blnIsCancel);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (updateトランザクション) <BR>
     * 一括対象となるトランザクションについて、 <BR>
     * 手数料按分計算（一口約定）を実施し、トランザクションデータを更新する。  <BR>
     *  <BR>
     * 手数料按分計算は、最初外貨の手数料按分計算を行い、その後、円貨の手数料按分計算を行う。<BR>
     *  <BR>
     * 外国株式ポジションヘルパー.updateトランザクション()に委譲（deligate）する。 <BR>
     * @@param l_lngOrderUnitIds - (注文単位ＩＤの一覧)
     * @@param l_datBaseDate - (基準日)
     * @@param l_blnIsNetting - (isネッティング処理)
     * @@throws WEB3BaseException
     * @@roseuid 42B672630370
     */
    public void updateTransaction(
        long[] l_lngOrderUnitIds,
        Date l_datBaseDate,
        boolean l_blnIsNetting) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateTransaction(long[],Date,boolean)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式ポジションヘルパー.updateトランザクション()に委譲（deligate）する。
        m_helper.updateTransaction(l_lngOrderUnitIds, l_datBaseDate, l_blnIsNetting);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get売付可能保有資産一覧) <BR>
     * 指定条件に一致する保有資産オブジェクトの一覧を返却する。 <BR>
     *  <BR>
     * １）　@検索条件を追加する。 <BR>
     *  <BR>
     * １－１）　@パラメータ.検索条件文字列の先頭に、 <BR>
     * 　@"口座ID = ?　@and　@補助口座ID = ? and 銘柄タイプ = ?  <BR>
     * 　@and ミニ株区分 = ? and 数量 > ? " <BR>
     * 　@を付加する。 <BR>
     *  <BR>
     * １－２）　@パラメータ.検索条件データコンテナの先頭に、 <BR>
     * 　@　@　@　@　@以下のパラメータリストを追加する。 <BR>
     * 　@　@　@　@　@　@・パラメータ.補助口座.口座ID <BR>
     * 　@　@　@　@　@　@・パラメータ.補助口座.補助口座ID <BR>
     * 　@　@　@　@　@　@・パラメータ.銘柄タイプ <BR>
     * 　@　@　@　@　@　@・"0：DEFAULT(ミニ株でない)" <BR>
     * 　@　@　@　@　@　@・0 <BR>
     *  <BR>
     * ３）　@QueryProcessor.doFindAllQuery( )により、 <BR>
     *      保有資産オブジェクトのListを取得する。 <BR>
     *  <BR>
     * 　@　@　@doFindAllQuery(保有資産Row.TYPE <BR>
     *                      １－１）の検索条件文字列, <BR>
     *                      パラメータ.ソート条件, <BR>
     *                      null, <BR>
     *                      １－２）の検索条件データコンテナ) <BR>
     *  <BR>
     * 　@　@　@※検索結果が取得できなかった場合、nullを返却する。 <BR>
     *  <BR>
     * ４）　@検索結果を返却する。 <BR>
     * @@param l_subAccount - (補助口座) <BR>
     * 補助口座オブジェクト
     * @@param l_productType - (銘柄タイプ)
     * @@param l_strQueryString - (検索条件文字列)
     * @@param l_queryContainer - (検索条件データコンテナ)
     * @@param l_strSortCond - (ソート条件)
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 42A81D2C03E5
     */
    public List getSellPossibleAssetList(
        WEB3GentradeSubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        String l_strQueryString, 
        String[] l_queryContainer, 
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSellPossibleAssetList(WEB3GentradeSubAccount,ProductTypeEnum,String,String,String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        try
        {
            QueryProcessor l_qp = null;
            List l_lisRows = null;
            
            //以下の条件に該当する保有資産を取得し返却する。
            l_qp = Processors.getDefaultProcessor();
            
            //１）　@検索条件を追加する。
            String l_strWhere = "account_id = ? and sub_account_id = ? and product_type = ? and mini_stock_div = ? and quantity > ?";
            if (l_strQueryString != null && l_strQueryString.length() > 0)
            {
                l_strWhere += l_strQueryString;
            }
            int l_intCondParamLen = 0;
            Object[] l_objBinds = null;
            if (l_queryContainer != null && l_queryContainer.length != 0)
            {
                l_intCondParamLen = l_queryContainer.length;
                l_objBinds = new Object[5 + l_intCondParamLen];
            }
            else
            {
                l_objBinds = new Object[5];
            }
            //１－２）　@パラメータ.検索条件データコンテナの先頭に、以下のパラメータリストを追加する。
            
            
            //口座ＩＤ： 引数.口座ＩＤ
            l_objBinds[0] = new Long(l_subAccount.getAccountId());
            
            //補助口座ＩＤ： 引数.補助口座ＩＤ
            l_objBinds[1] = new Long(l_subAccount.getSubAccountId());
            
            //銘柄ＩＤ： 引数.銘柄ＩＤ
            l_objBinds[2] = l_productType;
            
            //"0：DEFAULT(ミニ株でない)"
            l_objBinds[3] = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;
            l_objBinds[4] = new Double(0.0D);
            for (int i = 0; i < l_intCondParamLen; i++)
            {
                l_objBinds[5 + i] = l_queryContainer[i];
            }
            
            //３）保有資産オブジェクトのListを取得する。
            l_lisRows = l_qp.doFindAllQuery(AssetRow.TYPE, l_strWhere, l_strSortCond, null, l_objBinds);
            
            //４）　@検索結果を返却する。
            if (l_lisRows == null || l_lisRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            log.exiting(STR_METHOD_NAME);
            return l_lisRows;

        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }

    }
    
    /**
     * (get保有資産一覧) <BR>
     * 指定条件に一致する保有資産オブジェクトの一覧を返却する。 <BR>
     *  <BR>
     * １）　@検索条件を追加する。 <BR>
     *  <BR>
     * １－１）　@パラメータ.検索条件文字列の先頭に、 <BR>
     * 　@"口座ID = ?　@and　@補助口座ID = ? and 銘柄タイプ = ?  <BR>
     * 　@and ミニ株区分 = ? and (数量 + 売付不能数量) > ? " <BR>
     * 　@を付加する。 <BR>
     *  <BR>
     * １－２）　@パラメータ.検索条件データコンテナの先頭に、 <BR>
     * 　@　@　@　@　@以下のパラメータリストを追加する。 <BR>
     * 　@　@　@　@　@　@・パラメータ.補助口座.口座ID <BR>
     * 　@　@　@　@　@　@・パラメータ.補助口座.補助口座ID <BR>
     * 　@　@　@　@　@　@・パラメータ.銘柄タイプ <BR>
     * 　@　@　@　@　@　@・"0：DEFAULT(ミニ株でない)" <BR>
     * 　@　@　@　@　@　@・0 <BR>
     *  <BR>
     * ３）　@QueryProcessor.doFindAllQuery( )により、 <BR>
     * 　@　@　@保有資産オブジェクトのListを取得する。 <BR>
     *  <BR>
     * 　@　@　@doFindAllQuery(保有資産Row.TYPE <BR>
     *                      １－１）の検索条件文字列, <BR>
     *                      パラメータ.ソート条件, <BR>
     *                      null, <BR>
     *                      １－２）の検索条件データコンテナ) <BR>
     *  <BR>
     * 　@　@　@※検索結果が取得できなかった場合、nullを返却する。 <BR>
     *  <BR>
     * ４）　@検索結果を返却する。 <BR>
     * @@param l_subAccount - (補助口座) <BR>
     * 補助口座オブジェクト
     * @@param l_productType - (銘柄タイプ)
     * @@param l_strQueryString - (検索条件文字列)
     * @@param l_queryContainer - (検索条件データコンテナ)
     * @@param l_strSortCond - (ソート条件)
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 42A81D950348
     */
    public List getAssetList(WEB3GentradeSubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        String l_strQueryString, 
        String[] l_queryContainer, 
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAssetList(WEB3GentradeSubAccount,ProductTypeEnum,String,String,String)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        try
        {
            QueryProcessor l_qp = null;
            List l_lisRows = null;
            //以下の条件に該当する保有資産を取得し返却する。
            l_qp = Processors.getDefaultProcessor();
            String l_strWhere = 
                "account_id = ? and sub_account_id = ? and product_type = ? and mini_stock_div = ? and (quantity + quantity_cannot_sell) > ?";
            if (l_strQueryString != null && l_strQueryString.length() > 0)
            {
                l_strWhere += l_strQueryString;
            }
            
            Object[] l_objBinds = null;
            
            int l_intCondParamLen = 0;
            if (l_queryContainer != null)
            {
                l_intCondParamLen = l_queryContainer.length;
                l_objBinds = new Object[5 + l_intCondParamLen];
            }
            else
            {
                l_objBinds = new Object[5];
            }

            //口座ＩＤ： 引数.口座ＩＤ
            l_objBinds[0] = new Long(l_subAccount.getAccountId());
            //補助口座ＩＤ： 引数.補助口座ＩＤ
            l_objBinds[1] = new Long(l_subAccount.getSubAccountId());
            //銘柄ＩＤ： 引数.銘柄ＩＤ
            l_objBinds[2] = l_productType;
            //"0：DEFAULT(ミニ株でない)"
            l_objBinds[3] = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;
            l_objBinds[4] = new Double(0.0D);
            for (int i = 0; i < l_intCondParamLen; i++)
            {
                l_objBinds[5 + i] = l_queryContainer[i];
            }
            l_lisRows = l_qp.doFindAllQuery(AssetRow.TYPE, l_strWhere, l_strSortCond, null, l_objBinds);
            
            if (l_lisRows == null || l_lisRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            log.exiting(STR_METHOD_NAME);
            return l_lisRows;

        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
    }
    
    /**
     * (createトランザクション行) <BR>
     * 注文単位行／約定行より、トランザクション行オブジェクトを生成する。 <BR>
     *  <BR>
     * 外国株式ポジションヘルパー.createトランザクション()に委譲（deligate）する。 <BR>
     * @@param l_feqOrderExecution - (約定) <BR>
     * 約定オブジェクト
     * @@param l_feqOrderUnitRow - (注文単位行)
     * 注文単位行オブジェクト
     * 
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams
     * @@throws WEB3BaseException
     * @@roseuid 42B25F6C0394
     */
    public FeqFinTransactionParams createTransactionParams(
        FeqOrderExecution l_feqOrderExecution, 
        FeqOrderUnitRow l_feqOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createTransactionParams(FeqOrderExecution,FeqOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        //外国株式ポジションヘルパー.createトランザクション()に委譲（deligate）する。
        FeqFinTransactionParams l_params = m_helper.createTransactionParams(l_feqOrderExecution, l_feqOrderUnitRow);
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (get取引勘定明細ForOrderUnit) <BR>
     * （getFinTransactionForOrderUnit） <BR>
     * 同一注文に関連する既約定の取引勘定明細をリストで取得する。  <BR>
     *  <BR>
     * 外国株式ポジションヘルパー.getPersistenceManager().get取引勘定明細 <BR>
     * ForOrderUnit()に委譲（deligate）する。 <BR>
     *  <BR>
     * [get取引勘定明細ForOrderUnit()に指定する引数] <BR>
     * 注文単位ＩＤ：　@注文単位ＩＤ <BR>
     * @@param l_lngOrderUnitId - (注文単位ＩＤ)
     * @@return List
     * @@throws DataException
     * @@throws RuntimeSystemException
     * @@roseuid 42B2696C03D2
     */
    public List getFinTransactionForOrderUnit(long l_lngOrderUnitId) throws DataException, RuntimeSystemException 
    {
        final String STR_METHOD_NAME = "getFinTransactionForOrderUnit(long)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式ポジションヘルパー.getPersistenceManager().get取引勘定明細ForOrderUnit
        //（約定入力）()に委譲（deligate）する。
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
        
        try
        {
            List l_lisOrderUnit = l_dataManager.getFinTransactionForOrderUnit(l_lngOrderUnitId);
            log.exiting(STR_METHOD_NAME);
            return l_lisOrderUnit;  
        }
        catch (WEB3BaseException l_ex)        
        {
            log.error(l_ex.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }
    }
    
    /**
     * (get取引勘定明細ForOrderUnit（約定入力）) <BR>
     * 注文に関連する既約定の取引勘定明細で、 <BR>
     * 約定入力の行をリストで取得する。  <BR>
     *  <BR>
     * 外国株式ポジションヘルパー.getPersistenceManager().get取引勘定明細 <BR>
     * ForOrderUnit（約定入力）()に委譲（deligate）する。 <BR>
     *  <BR>
     * [get取引勘定明細ForOrderUnit()に指定する引数] <BR>
     * 注文単位ＩＤ：　@注文単位ＩＤ <BR>
     * @@param l_lngOrderUnitId - 注文単位ＩＤ
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 42B28D310255
     */
    public List getTradeDetailsForOrderUnit(long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradeDetailsForOrderUnit(long)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式ポジションヘルパー.getPersistenceManager().get取引勘定明細ForOrderUnit
        //（約定入力）()に委譲（deligate）する。
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
        List l_lisOrderUnit = l_dataManager.getFinTransactionForOrderUnitExecInput(l_lngOrderUnitId);
        
        log.exiting(STR_METHOD_NAME);
        return l_lisOrderUnit;
    }
    
    /**
     * (applyTo保有資産残高) <BR>
     * 保有資産を更新する。 <BR>
     *  <BR>
     * ポジションヘルパー.applyTo保有資産残高Deligate() <BR>
     * に委譲（deligate）する。 <BR>
     * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行) <BR>
     * トランザクション（取引勘定明細）行オブジェクト
     * @@return List
     * @@throws DataException
     * @@throws RuntimeSystemException
     * @@roseuid 42B7B1D600FA
     */
    public List applyToAssetBalance(FeqFinTransactionParams l_feqFinTransactionParams) 
        throws DataException, RuntimeSystemException 
    {
        final String STR_METHOD_NAME = "applyToAssetBalance(FeqFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        
        //ポジションヘルパー.applyTo保有資産残高Deligate()に委譲（deligate）する。
        List l_lstAssetBalance = m_helper.applyToAssetBalanceDeligate(l_feqFinTransactionParams);
        
        log.exiting(STR_METHOD_NAME);
        return l_lstAssetBalance;
    }
    
    /**
     * (notify顧客勘定) <BR>
     * 顧客勘定明細，補助口座を更新する。 <BR>
     *  <BR>
     * ポジションヘルパー.notify顧客勘定Deligate()に委譲（deligate）する。 <BR>
     * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行) <BR>
     * トランザクション（取引勘定明細）行オブジェクト
     * @@roseuid 42B7B33A0196
     */
    public void notifyAccountCash(FeqFinTransactionParams l_feqFinTransactionParams)
    {
        final String STR_METHOD_NAME = "notifyAccountCash(FeqFinTransactionParams)";
        log.entering(STR_METHOD_NAME);        
        
        //ポジションヘルパー.notify顧客勘定Deligate()に委譲（deligate）する。
        m_helper.notifyCashDeligate(l_feqFinTransactionParams);
        
        log.exiting(STR_METHOD_NAME);
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
     * <BR>
     * ３）　@this.get保有資産(口座ID, 補助口座ID, 銘柄ID, 税区分)をコールし、<BR>
     *         保有資産オブジェクトを取得する。<BR>
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
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager) l_tradingModule.getOrderManager();
            
            //注文単位オブジェクトを取得する
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitId);
            
            //取得保有資産
            TaxTypeEnum l_taxTypeEnum = null;
            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
            //税区分として注文単位.税区分を指定する。
            l_taxTypeEnum = l_orderUnitRow.getTaxType();
        
            Asset l_asset = this.getAsset(
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
            
            LockedAssetDetailsParams l_feqLockedAssetDetailsParams = (LockedAssetDetailsParams) LockedAssetDetailsDao.findRowByAssetId(l_lngAssetID);
        
            if (l_feqLockedAssetDetailsParams == null)
            {
                l_feqLockedAssetDetailsParams = new LockedAssetDetailsParams();
                l_feqLockedAssetDetailsParams.setAssetId(l_lngAssetID);
                l_feqLockedAssetDetailsParams.setAccountId(l_lngAccountId);
                l_feqLockedAssetDetailsParams.setSubAccountId(l_lngSubAccountId);
                l_feqLockedAssetDetailsParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_feqLockedAssetDetailsParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_feqLockedAssetDetailsParams.setLockedQuantity(l_dblAdjustQuantity);
        
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doInsertQuery(l_feqLockedAssetDetailsParams);
            }
            else
            {
                double l_dblNewLockedQuantity = l_dblAdjustQuantity + l_feqLockedAssetDetailsParams.getLockedQuantity();
                LockedAssetDetailsParams l_lockedAssetDetailsParams = new LockedAssetDetailsParams();
                GtlUtils.copyRow2Params(l_feqLockedAssetDetailsParams, l_lockedAssetDetailsParams);
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
     * (update保有資産) <BR>
     * 保有資産を更新する。 <BR>
     *  <BR>
     * ポジションヘルパー.update保有資産() <BR>
     * に委譲（deligate）する。 <BR>
     * @@param l_feqOrderUnit - (外国株式注文単位) <BR> 
     * @@throws WEB3BaseException
     * @@roseuid 42B7B1D600FA
     */
    public void updateAsset(WEB3FeqOrderUnit l_feqOrderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "updateAsset(WEB3FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //ポジションヘルパー.update保有資産()に委譲（deligate）する。
        m_helper.updateAsset(l_feqOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
        return;
    }

}
@
