head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPersistentDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 余力更新サービス(WEB3TPPersistentDataManager.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/8/03 劉 (fitechlabs) 新規作成
                    2006/09/11 徐宏偉 (中訊) モデルNo.017  
                    2006/09/25 徐宏偉 (中訊) モデルNo.063～066
 Revesion History : 2007/09/20 崔遠鵬(中訊) モデルNo.175
 Revesion History : 2007/11/05 孟亞南 (中訊) モデルNo.224、No.227
 Revesion History : 2008/01/22 張騰宇 (中訊) 仕様変更　@モデルNo.240
 Revesion History : 2008/05/26 楊夫志 (中訊) 仕様変更　@モデルNo.282、ＤＢ更新仕様002，003
 Revesion History : 2008/09/10 劉剣 (中訊) 仕様変更　@モデルNo.296、No.297、No.298
 Revesion History : 2009/10/02 肖志偉 (中訊) 仕様変更　@モデルNo.397
 Revesion History : 2010/01/11 武波 (中訊) 仕様変更　@モデルNo.421、No.443
 Revesion History : 2010/02/22 武波 (中訊) 仕様変更モデルNo.455
 */

package webbroker3.tradingpower.updtpower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3BondOrderSettleDivDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3HostReflectDivDef;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.gentrade.data.DailyAssetParams;
import webbroker3.gentrade.data.DailyAssetRow;
import webbroker3.gentrade.data.ExchangeRateDao;
import webbroker3.gentrade.data.ExchangeRateRow;
import webbroker3.gentrade.data.GenCurrencyDao;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.data.TaxRateTableRow;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductDao;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.data.EqtypeFixedContractDao;
import webbroker3.tradingpower.data.FixedFinTransactionRow;
import webbroker3.tradingpower.data.TpCalcResultEquityDao;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDao;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCashBalanceDao;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.data.TpOtherTempRestraintDao;
import webbroker3.tradingpower.data.TpOtherTempRestraintParams;
import webbroker3.tradingpower.data.TpOtherTempRestraintRow;
import webbroker3.tradingpower.define.WEB3TPAccountTaxTypeKeyDef;
import webbroker3.tradingpower.define.WEB3TPFeqExecFileSendStatusDivDef;
import webbroker3.tradingpower.define.WEB3TPFeqSettlementDivDef;
import webbroker3.tradingpower.define.WEB3TPIPOLotResultTypeDef;
import webbroker3.tradingpower.define.WEB3TPIPOOfferingDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (データベースアクセス管理) <BR>
 * 余力計算データベースアクセス管理用クラス
 */
public class WEB3TPPersistentDataManager
{
    
    private static WEB3TPPersistentDataManager THIS_INSTANCE = new
    WEB3TPPersistentDataManager();
    
    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3TPPersistentDataManager.class);
    
    /**
     * @@roseuid 4106191F00BB
     */
    public WEB3TPPersistentDataManager()
    {
        
    }
    
    /**
     * (get既存受渡日別保有資産レコード)<BR>
     * 既存受渡日別保有資産レコード<確定>を取得する。<BR>
     * 検索条件：<BR>
     *  受渡日別残高テーブル.口座ID = 引数.顧客属性.口座ID<BR>
     *  受渡日別残高テーブル.補助口座ID = 引数.顧客属性.補助口座ID<BR>
     * <BR>
     * @@param l_accountInfo - (顧客属性)
     * @@return List
     * @@roseuid 4104D2DE02CE
     */
    public List getDayAssets(WEB3TPAccountInfo l_accountInfo)
    {
        final String STR_METHOD_NAME = "getDayAssets(WEB3TPAccountInfo l_accountInfo)";
        try
        {
            String l_strWhere = "account_id=?";
            Object[] l_bindVars = { new Long(l_accountInfo.getAccountId())};
            
            log.debug("WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            
            List l_rows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        DailyAssetRow.TYPE,
                        l_strWhere,
                        l_bindVars);
            
            ArrayList l_dayAssets = new ArrayList();
            for (int i = 0; i < l_rows.size(); i++)
            {
                DailyAssetRow l_row = (DailyAssetRow)l_rows.get(i);
                DailyAssetParams l_params = new DailyAssetParams();
                l_params.setAccountId(l_row.getAccountId());
                l_params.setSubAccountId(l_row.getSubAccountId());
                l_params.setProductId(l_row.getProductId());
                l_params.setProductType(l_row.getProductType());
                l_params.setDeliveryDate(l_row.getDeliveryDate());
                l_params.setQuantity(l_row.getQuantity());
                l_params.setTaxType(l_row.getTaxType());
                //ミニ株区分の追加
                l_params.setMiniStockDiv(l_row.getMiniStockDiv());
                //分割新株式区分の追加
                l_params.setSplitNewStockDiv(l_row.getSplitNewStockDiv());
                //原約定日の追加
                l_params.setOriginalExecDate(l_row.getOriginalExecDate());                
                l_dayAssets.add(l_params);
            }
            
            return l_dayAssets;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
        }
    }
    
    /**
     * (get既存保有資産レコード)<BR>
     * 既存保有残高変動レコード<確定>を取得する。<BR>
     * 検索条件：<BR>
     *  保有資産テーブル.口座ID = 引数.顧客属性.口座ID<BR>
     *  保有資産テーブル.補助口座ID = 引数.顧客属性.補助口座ID<BR>
     *  保有資産テーブル.銘柄タイプ = 累投<BR>
     * <BR>
     * @@param l_accountInfo - (顧客属性)
     * @@return List
     * @@roseuid 4104D2DE02EE
     */
    public List getAssets(WEB3TPAccountInfo l_accountInfo)
    {
        final String STR_METHOD_NAME = "getAssets(WEB3TPAccountInfo l_accountInfo)";
        try
        {
            String l_strWhere = "account_id=? and product_type=?";
            Object[] l_bindVars = { new Long(l_accountInfo.getAccountId()), ProductTypeEnum.RUITO };
            
            return Processors.getDefaultProcessor().doFindAllQuery(
                    AssetRow.TYPE,
                    l_strWhere,
                    l_bindVars);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
        }
    }
    
    /**
     * (get資産移動レコード<出庫>)<BR>
     * 資産移動レコード<当日以降>を取得する。<BR>
     * 検索条件：<BR>
     *   資産移動テーブル.口座ID = 引数.顧客属性.口座ID<BR>
     *   資産移動テーブル.注文カテゴリ = 入出庫<BR>
     *   資産移動テーブル.注文カテゴリタイプ = 出庫<BR>
     *   資産移動テーブル.注文状態 NOT IN (発注失敗(新規注文)、発注済(取消注文))<BR>
     *   資産移動テーブル.発注日 >= 引数.計算条件.get営業日(T+0)<BR>
     * <BR>
     * @@param l_assetValuation - 資産評価
     * @@return List
     */
    public List getAssetOutAioOrderUnits(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getAssetOutAioOrderUnits(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = null;
        long l_accountId;
        List l_lisRows = null;
        
        try
        {
            l_accountInfo = l_assetValuation.getAccountInfo();
            l_accountId = l_accountInfo.getAccountId();
            
            StringBuffer l_strWhereBuf = new StringBuffer(" ");
            l_strWhereBuf.append("account_id = ? AND ");
            l_strWhereBuf.append("order_categ = ? AND ");
            l_strWhereBuf.append("order_type = ? AND ");
            l_strWhereBuf.append("order_status not in (?, ?) AND ");
            l_strWhereBuf.append("to_date(biz_date, 'YYYYMMDD') >= ? ");
            
            Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(0);
            Object[] l_bindVars =
            {
                    new Long(l_accountId),
                    OrderCategEnum.ASSET_IN_OUT,
                    OrderTypeEnum.ASSET_OUT,
                    OrderStatusEnum.NOT_ORDERED,
                    OrderStatusEnum.CANCELLED,
                    l_datT0
            };
            
            String l_strWhere = l_strWhereBuf.toString();
            log.debug("l_strWhere=" + l_strWhere);
            for(int i = 0 ; i < l_bindVars.length; i++)
            {
                log.debug("l_bindVars=[" + i + "]");
                log.debug(l_bindVars[i].toString());                
            }
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_lisRows = l_qp.doFindAllQuery(AioOrderUnitRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);
            
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
        }
    }
    
    /**
     * (get資産移動レコード<振替>)<BR>
     * 資産移動レコード<当日以降>を取得する。<BR>
     *   資産移動テーブル.口座ID = 引数.顧客属性.口座ID<BR>
     *   資産移動テーブル.注文カテゴリ = 証券振替<BR>
     *   資産移動テーブル.注文タイプ IN (保護から代用、代用から保護)<BR>
     *   資産移動テーブル.注文状態 NOT IN (発注失敗(新規注文)、発注済(取消注文))<BR>
     *   資産移動テーブル.発注日 >= 引数.計算条件.get営業日(T+0)<BR>
     * <BR>
     * @@param l_assetValuation - 資産評価
     * @@return List
     */
    public List getAssetTransferAioOrderUnits(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getAssetTransferAioOrderUnits(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = null;
        long l_accountId;
        List l_lisRows = null;
        
        try
        {
            l_accountInfo = l_assetValuation.getAccountInfo();
            l_accountId = l_accountInfo.getAccountId();
            Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(0);
            
            StringBuffer l_strWhereBuf = new StringBuffer(" ");
            l_strWhereBuf.append("account_id = ? AND ");
            l_strWhereBuf.append("order_categ = ? AND ");
            l_strWhereBuf.append("order_type in (?,?) AND ");
            l_strWhereBuf.append("order_status not in (?, ?) AND ");
            l_strWhereBuf.append("to_date(biz_date, 'YYYYMMDD') >= ? ");
            
            Object[] l_bindVars =
            {
                    new Long(l_accountId),
                    OrderCategEnum.ASSET_TRANSFER,
                    OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT,
                    OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES,
                    OrderStatusEnum.NOT_ORDERED,
                    OrderStatusEnum.CANCELLED,
                    l_datT0 };
            
            String l_strWhere = l_strWhereBuf.toString();
            log.debug("l_strWhere=" + l_strWhere);
            for(int i = 0 ; i < l_bindVars.length; i++)
            {
                log.debug("l_bindVars=[" + i + "]");
                log.debug(l_bindVars[i].toString());                
            }
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_lisRows = l_qp.doFindAllQuery(AioOrderUnitRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
        }
        
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        if (l_newOrderSpecs == null)
        {
            return l_lisRows;
        }
        
        //新規注文内容を取り込む
        TreeMap l_ht = new TreeMap();
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows.get(i);
            l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
        }
        
        long l_intNewAioOrderUnitId = WEB3TPNewOrderSpec.DEFAULT_NEW_ID;
        for (int i = 0; i < l_newOrderSpecs.length; i++)
        {
            //現注文内容.注文カテゴリ != 証券振替 の場合
            if (!OrderCategEnum.ASSET_TRANSFER.equals(l_newOrderSpecs[i].getOrderCategory()))
            {
                continue;
            }
            
            if (l_newOrderSpecs[i].getOrderUnitId() == WEB3TPNewOrderSpec.DEFAULT_NEW_ID)
            {
                l_newOrderSpecs[i].setOrderUnitId(l_intNewAioOrderUnitId);
                l_intNewAioOrderUnitId = l_intNewAioOrderUnitId - 1;
            }
            
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            l_params.setAccountId(l_accountId);
            l_params.setSubAccountId(l_newOrderSpecs[i].getSubAccountId());
            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
            l_params.setOrderCateg(l_newOrderSpecs[i].getOrderCategory());
            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
            l_params.setProductType(l_newOrderSpecs[i].getProductType());
            l_params.setProductId(l_newOrderSpecs[i].getProductId());
            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
            
            AioOrderUnitParams l_oldParams =
                (AioOrderUnitParams)l_ht.get(new Long(l_params.getOrderUnitId()));
            if (l_oldParams != null)
            {
                l_ht.remove(new Long(l_params.getOrderUnitId()));
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
            else
            {
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
        }
        
        List l_lisRet = new ArrayList();
        Iterator l_iterator = l_ht.values().iterator();
        while (l_iterator.hasNext())
        {
            AioOrderUnitParams l_aioOrderUnitParams = (AioOrderUnitParams)
            l_iterator.next();
            l_lisRet.add(l_aioOrderUnitParams);
        }
        
        return l_lisRet;
    }
    
    /**
     * (get株式取引レコード)<BR>
     * 当日注文レコード<当日以降>を取得する。<BR>
     * 検索条件：<BR>
     * 　@株式注文単位テーブル.口座ID = 引数.顧客属性.get口座ID()<BR>
     * 　@株式注文単位テーブル.補助口座ID = 引数.顧客属性.get補助口座ID()<BR>
     * 　@株式注文単位テーブル.発注日 >= 引数.計算条件.get営業日(T+0)<BR>
     * 　@株式注文単位テーブル.注文カテゴリ in (現物注文, 現引・現渡注文, 立会外分売)<BR>
     * 　@(　@株式注文単位テーブル.注文オープン = オープン<BR>
     * 　@　@　@OR<BR>
     * 　@　@（株式注文単位テーブル.注文オープン = クローズ<BR>
     * 　@　@　@　@AND<BR>
     * 　@　@　@株式注文単位テーブル.注文数量 - 株式注文単位テーブル.約定数量 >= 0 <BR>
     * 　@　@　@AND 株式注文単位テーブル.約定数量 > 0)<BR>
     * 　@)<BR>
     * <BR>
     * @@param l_accountInfo - (顧客属性)
     * @@param l_calcCondition - (余力計算条件)
     * @@return List
     * @@roseuid 4104D2DE031C
     */
    public List getEqTypeOrderUnits(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getEqTypeOrderUnits(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        long l_accountId = l_accountInfo.getAccountId();
        long l_subAccountId = l_accountInfo.getSubAccountId(l_accountInfo.
                isMarginCustomer());
        
        //Y00125：割増拘束率対応
        //割増拘束率を取得
        double l_dblPreRestRate = this.getPremiumRestraintRate(
                WEB3CommisionProductCodeDef.LISTING_STOCK,
                l_assetValuation);
        
        List l_lisRows=l_assetValuation.getTodaysEquityOrders();
        
        TreeMap l_ht = new TreeMap();
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow) l_lisRows.get(i);
            
            boolean isTarget=
                l_row.getSubAccountId()==l_subAccountId
                &&
                (OrderCategEnum.ASSET.equals(l_row.getOrderCateg())
                        || OrderCategEnum.SWAP_MARGIN.equals(l_row.getOrderCateg())
                )
                && (  (OrderOpenStatusEnum.OPEN.equals(l_row.getOrderOpenStatus()))
                        || (OrderOpenStatusEnum.CLOSED.equals(l_row.getOrderOpenStatus())
                                &&
                                ( (l_row.getExecutedQuantity() > 0) &&
                                        (l_row.getQuantity() - l_row.getExecutedQuantity()) >= 0)
                        )
                )
                &&(!OrderTypeEnum.MINI_STOCK_BUY.equals(l_row.getOrderType()))
                &&(!OrderTypeEnum.MINI_STOCK_SELL.equals(l_row.getOrderType()));
            if(!isTarget)continue;                
            
            EqtypeOrderUnitParams l_params = new EqtypeOrderUnitParams(l_row);
            //内出来＋失効された(或は取消済み)注文の場合、注文数量=約定数量　@に変換する
            if (OrderOpenStatusEnum.CLOSED.equals(l_params.getOrderOpenStatus()) &&
                    l_params.getExecutedQuantity() > 0)
            {
                l_params.setQuantity(l_params.getExecutedQuantity());
            }
            
            //Y00125：割増拘束率対応
            l_ht.put(
                    new Long(l_params.getOrderUnitId()),
                    new WEB3TPEqtypeOrderUnitRowWrapper(l_params, l_dblPreRestRate));
        }
        
        //新規注文内容を取り込む
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        if (l_newOrderSpecs != null)
        {
            for (int i = 0; i < l_newOrderSpecs.length; i++)
            {
                //現注文内容[i].銘柄タイプ!=株式の場合
                if(!ProductTypeEnum.EQUITY.equals(l_newOrderSpecs[i].getProductType()))
                {
                    continue;
                }
                
                //Y00126:返済後余力の不正
                //現注文内容.注文カテゴリ==信用新規建 又は 信用返済の場合
                if(OrderCategEnum.OPEN_MARGIN.equals(l_newOrderSpecs[i].getOrderCategory())
                        || OrderCategEnum.CLOSE_MARGIN.equals(l_newOrderSpecs[i].getOrderCategory()))
                {
                    continue;
                }
                
                //現注文内容.注文タイプ==ミニ株買 又は ミニ株売の場合
                if(OrderTypeEnum.MINI_STOCK_BUY.equals(l_newOrderSpecs[i].getOrderType())
                        || OrderTypeEnum.MINI_STOCK_SELL.equals(l_newOrderSpecs[i].getOrderType()))
                {
                    continue;
                }
                
                //新規注文内容と同一注文単位ＩＤのParamsがあるかチェック
                EqtypeOrderUnitRow l_oldParams = 
                    (EqtypeOrderUnitRow)l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                
                EqtypeOrderUnitParams l_params = null;
                
                //古いParamsに新しい情報を上書きする                  
                if (l_oldParams != null)
                {   
                    //旧ParamsからコピーParams作成
                    l_params = new EqtypeOrderUnitParams(l_oldParams);
                    
                    //旧Paramsを削除
                    l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                }
                else
                {
                    l_params = new EqtypeOrderUnitParams();
                }
                
                l_params.setAccountId(l_accountId);
                l_params.setSubAccountId(l_subAccountId);
                l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                l_params.setProductType(l_newOrderSpecs[i].getProductType());
                l_params.setProductId(l_newOrderSpecs[i].getProductId());
                l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                l_params.setOrderCateg(l_newOrderSpecs[i].getOrderCategory());
                l_params.setEstimatedPrice(l_newOrderSpecs[i].getEstimatedPrice());
                l_params.setBizDate(WEB3DateUtility.formatDate(
                        l_newOrderSpecs[i].
                        getOrderBizDate(), "yyyyMMdd"));
                l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                l_params.setMarketId(l_newOrderSpecs[i].getMarketId());
                l_params.setPrice(l_newOrderSpecs[i].getPrice());
                
                //Y00125：割増拘束率対応
                l_params.setLimitPrice(l_newOrderSpecs[i].getLimitPrice());
                
                l_ht.put(new Long(l_params.getOrderUnitId()),
                        new WEB3TPEqtypeOrderUnitRowWrapper(l_params, l_dblPreRestRate));
            }
            
        }
        
        return new ArrayList(l_ht.values());
    }
    
    /**
     * (getGP取引レコード)<BR>
     * 当日注文レコード<当日以降>を取得する。<BR>
     * 検索条件：<BR>
     *   累投注文単位テーブル.口座ID = 引数.顧客属性.get口座ID()<BR>
     *   累投注文単位テーブル.補助口座ID = 引数.顧客属性.get補助口座ID()<BR>
     *   累投注文単位テーブル.発注日 >= 引数.計算条件.get営業日(T+0)<BR>
     *   累投注文単位テーブル.累投タイプ in (中期国債ファ@ンド, MMF)<BR>
     *   累投注文単位テーブル.注文状態 not in <BR>
     *   　@(受付済(取消注文), 発注中(取消注文), 発注済(取消注文)))<BR>
     *   累投注文単位テーブル.注文オープン = オープン<BR>
     * <BR>
     * @@param l_accountInfo - (顧客属性)
     * @@param l_calcCondition - (余力計算条件)
     * @@return List
     * @@roseuid 4104D2DE032C
     */
    public List getRuitoOrderUnits(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getRuitoOrderUnits(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            long l_accountId = l_accountInfo.getAccountId();
            long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                    EQUITY_SUB_ACCOUNT);
            
            StringBuffer l_strWhereBuf = new StringBuffer(" ");
            l_strWhereBuf.append("account_id = ? AND ");
            l_strWhereBuf.append("to_date(biz_date, 'YYYYMMDD') >= ? AND ");
            l_strWhereBuf.append("ruito_type in (?, ?) AND ");
            l_strWhereBuf.append("order_status not in( ? , ? ,? ) AND ");
            l_strWhereBuf.append("order_open_status = ?  ");
            String l_strWhere = l_strWhereBuf.toString();
            
            Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(0);
            Object[] l_bindVars =
            {
                    new Long(l_accountId),
                    l_datT0,
                    RuitoTypeEnum.CHUUKOKU_FUND,
                    RuitoTypeEnum.MMF,
                    OrderStatusEnum.CANCEL_ACCEPTED,
                    OrderStatusEnum.CANCELLING,
                    OrderStatusEnum.CANCELLED,
                    OrderOpenStatusEnum.OPEN
                    
            };
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(RuitoOrderUnitRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);
            
            //新規注文内容を取り込む
            WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
            if (l_newOrderSpecs == null)
            {
                return l_lisRows;
            }
            TreeMap l_ht = new TreeMap();
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                RuitoOrderUnitParams l_params = (RuitoOrderUnitParams) l_lisRows.get(i);
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
            for (int i = 0; i < l_newOrderSpecs.length; i++)
            {
                if(!ProductTypeEnum.RUITO.equals(l_newOrderSpecs[i].getProductType()))
                {
                    continue;
                }
                RuitoOrderUnitParams l_params = new RuitoOrderUnitParams();
                l_params.setAccountId(l_accountId);
                l_params.setSubAccountId(l_subAccountId);
                l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                l_params.setOrderCateg(l_newOrderSpecs[i].getOrderCategory());
                l_params.setProductType(l_newOrderSpecs[i].getProductType());
                l_params.setProductId(l_newOrderSpecs[i].getProductId());
                l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                
                RuitoOrderUnitParams l_oldParams = (RuitoOrderUnitParams) l_ht.get(new
                        Long(l_params.getOrderUnitId()));
                if (l_oldParams != null)
                {
                    l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                    l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                }
                else
                {
                    l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                }
            }
            List l_lisRet = new ArrayList();
            Iterator l_iterator = l_ht.values().iterator();
            while (l_iterator.hasNext())
            {
                RuitoOrderUnitParams l_ruitoOrderUnitParams = (RuitoOrderUnitParams)
                l_iterator.next();
                l_lisRet.add(l_ruitoOrderUnitParams);
                
            }
            return l_lisRet;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get銘柄)<BR>
     * 銘柄テーブルから銘柄情報を検索する<BR>
     * 検索条件：<BR>
     * 　@銘柄テーブル.銘柄ID = 引数.銘柄ID<BR>
     * <BR>
     * @@param l_lngProductId - (銘柄ＩＤ)
     * @@return ProductParams
     * @@roseuid 4104D2DE034B
     */
    public ProductParams getProduct(long l_lngProductId)
    {
        final String STR_METHOD_NAME =
            "getProduct(long l_lngProductId)";
        try
        {
            return (ProductParams) ProductDao.findRowByPk(l_lngProductId);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get株式取引銘柄)<BR>
     *
     * 会社コード=引数.資産評価.顧客属性.会社コード
     * 引数.資産評価.余力計算条件.翌日注文受付開始区分<現物株式>=true <BR>
     * の場合、有効日=引数.余力計算条件.get営業日[T＋1] <BR>
     *
     * 引数.資産評価.余力計算条件.翌日注文受付開始区分<現物株式>=false <BR>
     * の場合、有効日=引数.余力計算条件.get営業日[T＋0] <BR>
     *
     * 株式取引銘柄テーブル（eqtype_traded_product）を検索する。<BR>
     * 検索条件：<BR>
     * 　@株式取引銘柄テーブル.銘柄ID = 引数.銘柄ID<BR>
     * 　@株式取引銘柄テーブル.有効日 = 有効日<BR>
     * 　@株式取引銘柄テーブル.会社コード = 会社コード<BR>
     *
     * 株式取引銘柄に該当データなければ、<BR>
     *
     * 株式取引銘柄テーブル_updq(eqtype_traded_product_updq)を検索する。
     * 検索条件：<BR>
     *   株式取引銘柄テーブル_updq.銘柄ID = 引数.銘柄ID<BR>
     *   株式取引銘柄テーブル_updq.有効日 = 有効日<BR>
     * 　@株式取引銘柄テーブル_updq.会社コード = 会社コード<BR>
     * @@param l_lngProductId - (銘柄ＩＤ)
     * @@param l_assetValuation -(資産評価)
     * @@return List
     * @@roseuid 4104D2DE035B
     */
    public List getEqTypeTradedProduct(
            long l_lngProductId,
            WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getEqTypeTradedProduct(long l_lngProductId,WEB3TPAssetValuation l_assetValuation)";
        try
        {
            List l_ret = Collections.EMPTY_LIST;
            String baseDate = null;
            WEB3TPCalcCondition l_calcCondition =
                l_assetValuation.getCalcCondition();
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            
            if (l_calcCondition.isEquityNextDayOrderStartDiv())
            {
                baseDate =
                    GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
                            l_calcCondition.getBizDate(1));
                //T+1
            }
            else
            {
                baseDate =
                    GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
                            l_calcCondition.getBizDate(0));
                //T+0
            }
            l_ret =
                EqtypeTradedProductDao
                .findRowsByInstitutionCodeProductIdValidUntilBizDate(
                        l_accountInfo.getInstitutionCode(),
                        l_lngProductId,
                        baseDate);
            if (l_ret.size() <= 0)
            {
                l_ret =
                    EqtypeTradedProductUpdqDao
                    .findRowsByInstitutionCodeProductIdValidUntilBizDate(
                            l_accountInfo.getInstitutionCode(),
                            l_lngProductId,
                            baseDate);
            }
            return l_ret;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get株式約定レコード)<BR>
     * 株式注文約定テーブルから株式注文約定情報を検索する。<BR>
     * 検索条件：<BR>
     * 　@株式注文約定テーブル.注文単位ID = 引数.注文単位ID<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)
     * @@return List
     * @@roseuid 4104D2DE036B
     */
    public List getEqTypeOrderExecutions(EqtypeOrderUnitRow l_orderUnit)
    {
        
        final String STR_METHOD_NAME =
            "getEqTypeOrderExecutions(long l_lngOrderUnitId)";
        
        try
        {
            return EqtypeOrderExecutionDao.findRowsByOrderUnitId(l_orderUnit.
                    getOrderUnitId());
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get建玉情報<確定>)<BR>
     * 確定の建玉情報を取得する。<BR>
     * <BR>
     * １）確定の建玉情報を取得する。<BR>
     * 　@　@確定建株テーブルを以下の条件で検索する。<BR>
     * 　@　@－検索条件<BR>
     * 　@　@　@・確定建株テーブル.口座ID　@　@　@＝引数.顧客属性.口座ID<BR>
     * 　@　@　@・確定建株テーブル.補助口座ID　@＝引数.顧客属性.補助口座ID<BR>
     * <BR>
     * ２）検索結果を返す。<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D3360274
     */
    public List getFixedContracts(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getFixedContracts(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        
        try
        {
            List l_lisRows = EqtypeFixedContractDao
            .findRowsByAccountIdSubAccountId(l_accountInfo.getAccountId(),
                    l_accountInfo.getSubAccountId(true));
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get建玉情報<当日>)<BR>
     * 当日の建玉情報を取得する。<BR>
     * <BR>
     * １）当日の建玉情報を取得する。<BR>
     * 　@　@建株テーブルを以下の条件で検索する。<BR>
     * 　@　@－検索条件<BR>
     * 　@　@　@・建株テーブル.口座ID　@　@　@＝引数.顧客属性.口座ID<BR>
     * 　@　@　@・建株テーブル.補助口座ID　@＝引数.顧客属性.補助口座ID<BR>
     * 　@　@　@・建株テーブル.建日　@　@　@　@＝引数.余力計算条件.営業日(T+0)<BR>
     * <BR>
     * ２）検索結果を返す。<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D3360293
     */
    public List getTodaysContracts(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysContracts(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
        
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("TRUNC(open_date) = ? ");
            String l_strWhere = l_strWhereBuf.toString();
            Object[] l_bindVars =
            {
                    new Long(l_accountInfo.getAccountId()),
                    new Long(l_accountInfo.getSubAccountId(true)),
                    l_calcCondition.getBizDate(0)
            };
            log.debug(STR_METHOD_NAME + " WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);
            
            List l_lisRows = l_qp.doFindAllQuery(EqtypeContractRow.TYPE, l_strWhere, null,
                    l_bindVars);
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get変動情報<確定>)><BR>
     * 確定の変動情報を取得する。<BR>
     * <BR>
     * １）確定の変動情報を取得する。<BR>
     * 　@　@確定トランザクションテーブルを以下の条件で検索する。<BR>
     * 　@　@－検索条件<BR>
     * 　@　@　@・確定トランザクションテーブル.口座ID　@　@　@　@　@　@　@　@　@　@＝引数.顧客属性.口座ID<BR>
     * 　@　@　@・確定トランザクションテーブル.補助口座ID　@　@　@　@　@　@　@　@＝引数.顧客属性.補助口座ID<BR>
     * 　@　@　@・確定トランザクションテーブル.トランザクションカテゴリ　@in　@( 返済取引 , 現引現渡取引 )
     * 　@　@　@・確定トランザクションテーブル.受渡日　@　@　@　@　@　@　@　@　@　@＞引数.余力計算条件.営業日(T+0)<BR>
     * 　@　@　@・確定トランザクションテーブル.削除フラグ        　@　@　@　@＝FALSE<BR>
     * <BR>
     * ２）検索結果を返す。<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D33602B2
     */
    public List getFixedHistories(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getFixedHistories(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
        
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("fin_transaction_categ IN (?, ?) AND ");
            l_strWhereBuf.append("delivery_date > ? AND ");
            l_strWhereBuf.append("delete_flag = ? ");
            String l_strWhere = l_strWhereBuf.toString();
            
            Object[] l_bindVars =
            {
                    new Long(l_accountInfo.getAccountId()),
                    new Long(l_accountInfo.getSubAccountId(true)),
                    FinTransactionCateg.EQTYPE_CLOSE_MARGIN,
                    FinTransactionCateg.EQTYPE_SWAP_MARGIN,
                    l_calcCondition.getBizDate(0),
                    BooleanEnum.FALSE
            };
            log.debug(STR_METHOD_NAME + " WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);
            log.debug("BindVars[3]=" + l_bindVars[3]);
            log.debug("BindVars[4]=" + l_bindVars[4]);
            log.debug("BindVars[5]=" + l_bindVars[5]);
            
            List l_lisRows = l_qp.doFindAllQuery(FixedFinTransactionRow.TYPE, l_strWhere, null,
                    l_bindVars);
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get変動情報<当日>)<BR>
     * 当日の変動情報を取得する。<BR>
     * <BR>
     * １）当日の変動情報を取得する。<BR>
     * 　@　@トランザクションテーブルを以下の条件で検索する。<BR>
     * 　@　@－検索条件<BR>
     * 　@　@　@・トランザクションテーブル.口座ID　@　@　@　@　@　@　@　@　@　@＝引数.顧客属性.口座ID<BR>
     * 　@　@　@・トランザクションテーブル.補助口座ID　@　@　@　@　@　@　@　@＝引数.顧客属性.補助口座ID<BR>
     * 　@　@　@・トランザクションテーブル.トランザクションカテゴリ　@in　@( 返済取引 , 現引現渡取引 )<BR>
     * 　@　@　@・トランザクションテーブル.発生日時　@　@　@　@　@　@　@　@　@＝引数.余力計算条件.営業日(T+0)<BR>
     * 　@　@　@・トランザクションテーブル.削除フラグ       　@　@　@　@＝FALSE<BR>
     * <BR>
     * ２）検索結果を返す。<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D33602C2
     */
    public List getTodaysHistories(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysHistories(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
        
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("fin_transaction_categ IN (?, ?) AND ");
            l_strWhereBuf.append("TRUNC(fin_transaction_timestamp) = ? AND ");
            l_strWhereBuf.append("delete_flag = ? ");
            String l_strWhere = l_strWhereBuf.toString();
            
            Object[] l_bindVars =
            {
                    new Long(l_accountInfo.getAccountId()),
                    new Long(l_accountInfo.getSubAccountId(true)),
                    FinTransactionCateg.EQTYPE_CLOSE_MARGIN,
                    FinTransactionCateg.EQTYPE_SWAP_MARGIN,
                    l_calcCondition.getBizDate(0),
                    BooleanEnum.FALSE
            };
            log.debug(STR_METHOD_NAME + " WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);
            log.debug("BindVars[3]=" + l_bindVars[3]);
            log.debug("BindVars[4]=" + l_bindVars[4]);
            log.debug("BindVars[5]=" + l_bindVars[5]);
            
            List l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, null,
                    l_bindVars);
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get未約定変動情報)<BR>
     * 未約定変動情報を取得する。<BR>
     * <BR>
     * １）未約定変動情報を取得する。<BR>
     * 　@　@株式注文単位テーブルを以下の条件で検索する。<BR>
     * 　@　@－検索条件<BR>
     * 　@　@　@・株式注文単位テーブル.口座ID　@　@　@　@＝引数.顧客属性.口座ID<BR>
     * 　@　@　@・株式注文単位テーブル.補助口座ID　@　@＝引数.顧客属性.補助口座ID<BR>
     * 　@　@　@・株式注文単位テーブル.カテゴリ　@　@　@in　@( 新規建注文 , 現引現渡注文 )
     * 　@　@　@・株式注文単位テーブル.発注日　@　@　@　@＞＝引数.余力計算条件.営業日(T+0)<BR>
     * 　@　@　@・株式注文単位テーブル.注文状態 　@　@　@not in ( 発注済（取消注文）)<BR>
     * 　@　@　@・株式注文単位テーブル.注文有効状態　@＝オープン
     * <BR>
     * ２）検索結果を返す。<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D33602D2
     */
    public List getUnExecutedOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getUnExecutedOrders(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
        
        long l_accountId = l_accountInfo.getAccountId();
        long l_subAccountId = l_accountInfo.getSubAccountId(l_accountInfo.
                isMarginCustomer());
        
        //Y00125：割増拘束率対応
        //割増拘束率を取得
        double l_dblPreRestRate = this.getPremiumRestraintRate(
                WEB3CommisionProductCodeDef.LISTING_STOCK,
                l_assetValuation);
        
        //発注日が当日以降の注文List(getTodaysEquityOrdersで取得したList)
        List l_lisRows=l_assetValuation.getTodaysEquityOrders();
        
        TreeMap l_ht = new TreeMap();
        for(int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow) l_lisRows.get(i);
            
            //サブアカウントID
            if(l_subAccountId == l_row.getSubAccountId())
            {
                //注文有効状態＝オープン
                if(OrderOpenStatusEnum.OPEN.equals(l_row.getOrderOpenStatus()))
                {
                    //カテゴリ　@in　@( 新規建注文 , 現引現渡注文 )
                    if(OrderCategEnum.OPEN_MARGIN.equals(l_row.getOrderCateg())
                            || OrderCategEnum.SWAP_MARGIN.equals(l_row.getOrderCateg()))
                    {
                        //注文状態 not in ( 発注済（取消注文）)
                        if(! OrderStatusEnum.CANCELLED.equals(l_row.getOrderStatus()))
                        {
                            //市場確認済値対応
                            //Y00125：割増拘束率対応
                            l_ht.put(
                                    new Long(l_row.getOrderUnitId()),
                                    new WEB3TPEqtypeOrderUnitRowWrapper(l_row, l_dblPreRestRate));
                        }
                    }
                }
            }
        }
        
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        
        //現注文内容がnullでない場合、反映させる
        if(l_newOrderSpecs != null)
        {
            int l_intNewSize = l_newOrderSpecs.length;
            for (int i = 0; i < l_intNewSize; i++)
            {
                //カテゴリが新規建 又は 現引現渡
                OrderCategEnum l_newSpecOrderCateg = l_newOrderSpecs[i].getOrderCategory();
                if (OrderCategEnum.SWAP_MARGIN.equals(l_newSpecOrderCateg)
                        || OrderCategEnum.OPEN_MARGIN.equals(l_newSpecOrderCateg))
                {
                    //新規注文内容と同一注文単位ＩＤのParamsがあるかチェック
                    EqtypeOrderUnitRow l_oldParams = 
                        (EqtypeOrderUnitRow)l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                    
                    EqtypeOrderUnitParams l_params = null;
                    
                    //古いParamsに新しい情報を上書きする                  
                    if (l_oldParams != null)
                    {   
                        //旧ParamsからコピーParams作成
                        l_params = new EqtypeOrderUnitParams(l_oldParams);
                        
                        //旧Paramsを削除
                        l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                    }
                    else
                    {
                        l_params = new EqtypeOrderUnitParams();
                    }
                    
                    l_params.setAccountId(l_accountId);
                    l_params.setSubAccountId(l_subAccountId);
                    l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                    l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                    l_params.setProductType(l_newOrderSpecs[i].getProductType());
                    l_params.setProductId(l_newOrderSpecs[i].getProductId());
                    l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                    l_params.setOrderCateg(l_newOrderSpecs[i].getOrderCategory());
                    l_params.setEstimatedPrice(l_newOrderSpecs[i].getEstimatedPrice());
                    l_params.setBizDate(WEB3DateUtility.formatDate(
                            l_newOrderSpecs[i].
                            getOrderBizDate(), "yyyyMMdd"));
                    l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                    l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                    l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                    l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                    l_params.setMarketId(l_newOrderSpecs[i].getMarketId());
                    l_params.setPrice(l_newOrderSpecs[i].getPrice());
                    
                    //Y00125：割増拘束率対応
                    l_params.setLimitPrice(l_newOrderSpecs[i].getLimitPrice());
                    
                    //Y00125：割増拘束率対応
                    l_ht.put(
                            new Long(l_params.getOrderUnitId()),
                            new WEB3TPEqtypeOrderUnitRowWrapper(
                                    l_params, l_dblPreRestRate));
                }
            }
        }
        return new ArrayList(l_ht.values());
    }
    
    /**
     * (get建玉返済指定情報)<BR>
     * 建玉返済指定情報を取得する。<BR>
     * <BR>
     * １）建玉返済指定情報を取得する。<BR>
     * 　@　@建玉返済指定情報テーブルを以下の条件で検索する。<BR>
     * 　@　@－検索条件<BR>
     * 　@　@　@・建玉返済指定情報テーブル.口座ID　@　@　@＝引数.顧客属性.口座ID<BR>
     * 　@　@　@・建玉返済指定情報テーブル.補助口座ID　@＝引数.顧客属性.補助口座ID<BR>
     * <BR>
     * ２）検索結果を返す。<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D33602F1
     */
    public List getClosingContractSpecs(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getClosingContractSpecs(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        List l_lisRows = null;
        
        try
        {
            l_lisRows = EqtypeClosingContractSpecDao
            .findRowsByAccountIdSubAccountId(l_accountInfo.getAccountId(),
                    l_accountInfo.getSubAccountId(true));
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        if (l_newOrderSpecs == null)
        {
            return l_lisRows;
        }
        
        //現注文内容をListに反映
        TreeMap l_ht = new TreeMap();
        int l_lisRowsSize = l_lisRows.size();
        for (int i = 0; i < l_lisRowsSize; i++)
        {
            EqtypeClosingContractSpecParams l_params = (EqtypeClosingContractSpecParams) l_lisRows.get(i);
            String l_strKey= String.valueOf(l_params.getOrderUnitId()) + String.valueOf(l_params.getContractId());
            l_ht.put(l_strKey, l_params);
        }
        
        int l_intNewSize = l_newOrderSpecs.length;
        for (int i = 0; i < l_intNewSize; i++)
        {
            //カテゴリが現引現渡　@または、　@返済の場合
            OrderCategEnum l_newSpecOrderCateg = l_newOrderSpecs[i].getOrderCategory();
            if (OrderCategEnum.SWAP_MARGIN.equals(l_newSpecOrderCateg)
                    || OrderCategEnum.CLOSE_MARGIN.equals(l_newSpecOrderCateg))
            {
                //現注文内容の返済指定情報を取得
                WEB3TPContractSettleSpecify[] l_contractSettleSpecs = l_newOrderSpecs[i].getContractSettleSpecify();
                
                //新規 又は 訂正注文の場合
                if(l_contractSettleSpecs != null)
                {
                    int l_intSpecsSize = l_contractSettleSpecs.length;
                    for (int j = 0; j < l_intSpecsSize; j++)
                    {
                        //現注文内容の返済指定情報を元にオブジェクト生成
                        EqtypeClosingContractSpecParams l_eqClosingContractSpec = new EqtypeClosingContractSpecParams();
                        l_eqClosingContractSpec.setClosingContractSpecId(l_newOrderSpecs[i].getOrderUnitId());
                        l_eqClosingContractSpec.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_eqClosingContractSpec.setContractId(l_contractSettleSpecs[j].getContractId());
                        l_eqClosingContractSpec.setQuantity(l_contractSettleSpecs[j].getQuantity());
                        
                        String l_strNewKey = String.valueOf(l_eqClosingContractSpec.getOrderUnitId()) + String.valueOf(l_eqClosingContractSpec.getContractId());
                        EqtypeClosingContractSpecParams l_oldParams = (EqtypeClosingContractSpecParams) l_ht.get(l_strNewKey);
                        if (l_oldParams != null)
                        {
                            l_eqClosingContractSpec.setExecutedQuantity(l_oldParams.getExecutedQuantity());
                            String l_strOldKey = String.valueOf(l_oldParams.getOrderUnitId()) + String.valueOf(l_oldParams.getContractId());
                            l_ht.remove(l_strOldKey);
                            l_ht.put(l_strNewKey, l_eqClosingContractSpec);
                        }
                        else
                        {
                            l_ht.put(l_strNewKey, l_eqClosingContractSpec);
                        }
                    }
                }
                //取消注文の場合
                else
                {
                    //DB検索で取得した返済指定情報Params一覧でループ
                    for (int j = 0; j < l_lisRowsSize; j++)
                    {
                        //登録した返済指定情報Paramsを取得
                        EqtypeClosingContractSpecParams l_params = (EqtypeClosingContractSpecParams) l_lisRows.get(j);
                        
                        long l_lngCancelOrderUnitId = l_newOrderSpecs[i].getOrderUnitId();
                        
                        long l_lngCancelClosingContractOrderUnitId = l_params.getOrderUnitId();
                        
                        //注文単位IDが同じ場合
                        if(l_lngCancelOrderUnitId == l_lngCancelClosingContractOrderUnitId)
                        {
                            //DB検索で取得した返済指定情報Paramsを削除
                            String l_strRegisterdKey = String.valueOf(l_params.getOrderUnitId()) + String.valueOf(l_params.getContractId());
                            l_ht.remove(l_strRegisterdKey);
                        }
                    }
                }
            }
        }
        return new ArrayList(l_ht.values());
    }
    
    /**
     * (get株式取引銘柄)<BR>
     * 株式取引銘柄情報を取得する。<BR>
     * <BR>
     * １）株式取引銘柄を取得する。<BR>
     * 　@　@株式取引銘柄マスターテーブルを以下の条件で検索する。<BR>
     * 　@　@－検索条件<BR>
     * 　@　@　@・株式取引銘柄マスターテーブル.証券会社コード　@＝引数.資産評価.get証券会社コード<BR>
     * 　@　@　@・株式取引銘柄マスターテーブル.銘柄ID　@　@　@　@　@＝引数.銘柄ID<BR>
     * 　@　@　@・株式取引銘柄マスターテーブル.市場ID　@　@　@　@　@＝引数.市場ID<BR>
     * 　@　@　@・株式取引銘柄マスターテーブル.有効日　@　@　@　@　@＝引数.発注日<BR>
     * <BR>
     * ２）検索結果を返す。<BR>
     * @@param l_assetValuation - (資産評価)
     * @@param l_lngProductId - (銘柄ID)
     * @@param l_lngMarketId - (市場ID)
     * @@param l_strValidUntilBizDate - (発注日)
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow
     * @@roseuid 4104D3360300
     */
    public EqtypeTradedProductRow getEqtypeTradedProduct(WEB3TPAssetValuation
            l_assetValuation, long l_lngProductId, long l_lngMarketId, String l_strValidUntilBizDate)
    {
        final String STR_METHOD_NAME =
            "getEqtypeTradedProduct(WEB3TPAssetValuation l_assetValuation, long l_lngProductId, long l_lngMarketId, String l_strValidUntilBizDate)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
        
        try
        {
            EqtypeTradedProductRow l_row
            = EqtypeTradedProductDao.
            findRowByInstitutionCodeProductIdMarketIdValidUntilBizDate
            (
                    l_accountInfo.getInstitutionCode(),
                    l_lngProductId,
                    l_lngMarketId,
                    l_strValidUntilBizDate
            );
            return l_row;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get株式取引銘柄)<BR>
     * 株式取引銘柄情報を取得する。<BR>
     * <BR>
     * １）株式取引銘柄を取得する。<BR>
     * 株式取引銘柄マスターテーブルを以下の条件で検索する。<BR>
     * －検索条件<BR>
     * ・株式取引銘柄マスターテーブル.証券会社コード ＝引数.証券会社コード<BR>
     * ・株式取引銘柄マスターテーブル.銘柄ID ＝引数.銘柄ID<BR>
     * ・株式取引銘柄マスターテーブル.市場ID ＝引数.市場ID<BR>
     * ・株式取引銘柄マスターテーブル.有効日 ＝引数.発注日<BR>
     * <BR>
     * ２）検索結果を返す。<BR>
     * 
     * @@param l_strInstCode - (証券会社コード)
     * @@param l_lngProductId - (銘柄ID)
     * @@param l_lngMarketId - (市場ID)
     * @@param l_strValidUntilBizDate - (発注日)
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow
     */
    public EqtypeTradedProductRow getEqtypeTradedProduct(String l_strInstCode, long l_lngProductId,
            long l_lngMarketId, String l_strValidUntilBizDate)
    {
        final String STR_METHOD_NAME = "getEqtypeTradedProduct(String l_strInstCode, long l_lngProductId, long l_lngMarketId, String l_strValidUntilBizDate)";

        try
        {
            EqtypeTradedProductRow l_row = EqtypeTradedProductDao
                    .findRowByInstitutionCodeProductIdMarketIdValidUntilBizDate(l_strInstCode,
                            l_lngProductId, l_lngMarketId, l_strValidUntilBizDate);
            return l_row;
        }
        catch(DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass()
                    .getName()
                    + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }

    /**
     * (get株式取引銘柄Updq)<BR>
     * 株式取引銘柄Updq情報を取得する。<BR>
     * <BR>
     * １）株式取引銘柄Updqを取得する。<BR>
     * 株式取引銘柄マスターUpdqテーブルを以下の条件で検索する。<BR>
     * －検索条件<BR>
     * ・株式取引銘柄マスターUpdqテーブル.銘柄ID ＝引数.銘柄ID<BR>
     * ・株式取引銘柄マスターUpdqテーブル.市場ID ＝引数.市場ID<BR>
     * ・株式取引銘柄マスターUpdqテーブル.有効日 ＝引数.発注日<BR>
     * <BR>
     * ２）検索結果を返す。<BR>
     * @@param l_lngProductId - (銘柄ID)
     * @@param l_lngMarketId - (市場ID)
     * @@param l_strValidUntilBizDate - (発注日)
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow
     */
    public EqtypeTradedProductUpdqRow getEqtypeTradedProductUpdq(long l_lngProductId, long l_lngMarketId, String l_strValidUntilBizDate)
    {
        final String STR_METHOD_NAME =
            "getEqtypeTradedProductUpdq(long l_lngProductId, long l_lngMarketId, String l_strValidUntilBizDate)";
        
        try
        {
            EqtypeTradedProductUpdqRow l_row
            = EqtypeTradedProductUpdqDao.
            findRowByProductIdMarketIdValidUntilBizDate
            (
                    l_lngProductId,
                    l_lngMarketId,
                    l_strValidUntilBizDate
            );
            return l_row;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get当日以降現物株注文)<BR>
     * 当日以降現物株注文（株式注文単位のリスト）を取得する。<BR>
     * <BR>
     * １） 株式注文単位テーブルより注文検索<BR>
     * <BR>
     * 当日約定済株式（買・売）<BR>
     * 当日未約定株式（買）<BR>
     * 翌日未約定株式（買）<BR>
     * を検索。<BR>
     * <BR>
     * 条件：<BR>
     * 口座ID ＝ 顧客属性.get口座ID()<BR>
     * 補助口座ID ＝ 顧客属性.get補助口座ID（補助口座タイプ）<BR>
     * 補助口座タイプ・・・現物客：補助口座タイプ.現物<BR>
     * ・・・信用客：補助口座タイプ.現物あるいは補助口座タイプ.信用（実際のデータは補助口座タイプ.信用のみ）<BR>
     * 銘柄タイプ ＝ 株<BR>
     * 注文種別 IN 現物株買、現物株売<BR>
     * 発注日 ＞＝ 当日<BR>
     * 注文状態 NOT IN 取消済<BR>
     * 注文有効状態 ＝ オープン<BR>
     * OR<BR>
     * 約定数量 ＞0<BR>
     * <BR>
     * ２） 検索された株式注文のリストを返す<BR>
     * 
     * @@param l_assetValuation -
     *            (資産評価)
     * @@return List
     * @@roseuid 4104D3750355
     */
    public List getTodaysCashBasedEquityOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysCashBasedEquityOrders(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(0);
        
        long l_accountId = l_accountInfo.getAccountId();
        long l_subAccountId;
        //現物客の場合
        if (!l_accountInfo.isMarginCustomer())
        {
            l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                    EQUITY_SUB_ACCOUNT);
        }
        //信用客の場合
        else
        {
            l_subAccountId = l_accountInfo.getSubAccountId(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        }
        
        List l_lisRows=l_assetValuation.getTodaysEquityOrders();
        
        //フィルタする
        TreeMap l_ht = new TreeMap();
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitParams l_params = (EqtypeOrderUnitParams) l_lisRows.
            get(i);
            if ((l_subAccountId == l_params.getSubAccountId())
                    &&
                    (OrderTypeEnum.EQUITY_BUY.equals(l_params.getOrderType())
                            || OrderTypeEnum.EQUITY_SELL.equals(l_params.getOrderType()))
                            && 
                            ((OrderOpenStatusEnum.OPEN.equals(l_params.getOrderOpenStatus()))
                                    || (OrderOpenStatusEnum.CLOSED.equals(l_params.getOrderOpenStatus())
                                            &&
                                            ( (l_params.getExecutedQuantity() > 0) &&
                                                    (l_params.getQuantity() - l_params.getExecutedQuantity()) >= 0)))
            )
            {
                //市場確認済値対応
                l_ht.put(new Long(l_params.getOrderUnitId()),
                        new WEB3TPEqtypeOrderUnitRowWrapper(l_params));
            }
        }
        
        //新規注文内容を取り込む
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        
        if (l_newOrderSpecs != null)
        {
            for (int i = 0; i < l_newOrderSpecs.length; i++)
            {
                if (OrderTypeEnum.EQUITY_BUY.equals(l_newOrderSpecs[i].getOrderType()) 
                        ||			            
                        OrderTypeEnum.EQUITY_SELL.equals(l_newOrderSpecs[i].getOrderType()))
                {
                    //新規注文内容と同一注文単位ＩＤのParamsがあるかチェック
                    EqtypeOrderUnitRow l_oldParams = (EqtypeOrderUnitRow)
                    l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                    
                    EqtypeOrderUnitParams l_params = null;
                    
                    //古いParamsに新しい情報を上書きする		        	
                    if (l_oldParams != null)
                    {	
                        //旧ParamsからコピーParams作成
                        l_params = new EqtypeOrderUnitParams(l_oldParams);
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setPrice(l_newOrderSpecs[i].getPrice());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                        
                        //旧Paramsを削除
                        l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                        
                    }
                    else
                    {
                        l_params = new EqtypeOrderUnitParams();
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setPrice(l_newOrderSpecs[i].getPrice());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());				        			
                    }
                    
                    //書換えor新しいParamsを追加
                    if(l_params.getEstimatedPrice() != 0.0d)
                    {
                        l_ht.put(new Long(l_params.getOrderUnitId()),
                                new WEB3TPEqtypeOrderUnitRowWrapper(l_params));
                    }
                    else
                    {
                        log.debug("概算受渡金額が0の新規注文内容=" + l_newOrderSpecs[i]);
                    }
                }
            }
        }
        
        return new ArrayList(l_ht.values());
    }
    
    /**
     * (get当日以降信用返済注文)<BR>
     *　@当日以降信用返済注文（株式注文単位のリスト）を取得する。<BR>
     *<BR>
     * １）　@注文検索<BR>
     * <BR>
     * 株式注文単位テーブルより注文検索。<BR>
     * <BR>
     * 当日約定済決済損<BR>
     * 当日約定済決済益<BR>
     * を検索。<BR>
     * <BR>
     * 条件：<BR>
     * 口座ID     ＝   顧客属性.get口座ID()<BR>
     * 補助口座ID       ＝   顧客属性.get補助口座ID（補助口座タイプ）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@補助口座タイプ・・・補助口座タイプ.現物あるいは補助口座タイプ.信用（実際のデータは補助口座タイプ.信用のみ）<BR>
     * 注文カテゴリ   ＝   信用返済<BR>
     * 発注日      ＝   当日<BR>
     * 約定数量 ＞   0<BR>
     * <BR>
     * <BR>
     * ２）　@株式注文のリストを返す<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D3750375
     */
    public List getTodaysCloseMarginOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysCloseMarginOrders(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        //信用客でない場合要素0のリストを返す
        if (!l_accountInfo.isMarginCustomer())
        {
            return Collections.EMPTY_LIST;
        }
        long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                EQUITY_MARGIN_SUB_ACCOUNT);
        
        List l_lisRows = l_assetValuation.getTodaysEquityOrders();
        
        //新規注文内容は未約定のため取り込まない
        
        TreeMap l_ht = new TreeMap();
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitParams l_params = (EqtypeOrderUnitParams) l_lisRows.
            get(i);
            if((l_subAccountId == l_params.getSubAccountId())
                    &&
                    (OrderCategEnum.CLOSE_MARGIN.equals(l_params.getOrderCateg()))
                    && 
                    (l_params.getExecutedQuantity() > 0)
            )
            {
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
        }
        
        List l_lisRet = new ArrayList();
        Iterator l_iterator = l_ht.values().iterator();
        while (l_iterator.hasNext())
        {
            EqtypeOrderUnitParams l_eqOrderUnitParams = (EqtypeOrderUnitParams)
            l_iterator.next();
            l_lisRet.add(l_eqOrderUnitParams);
            
        }
        return l_lisRet;
    }
    
    /**
     * (get当日以降現引現渡注文)<BR>
     *　@当日以降現引現渡注文（株式注文単位のリスト）を取得する。<BR>
     *<BR>
     * １）　@注文検索<BR>
     * <BR>
     * 株式注文単位テーブルより注文検索。<BR>
     * <BR>
     * 当日約定済決済（現引・現渡）<BR>
     * 当日未約定決済（現引・現渡）<BR>
     * 翌日未約定決済（現引・現渡）<BR>
     * を検索。<BR>
     * <BR>
     * 条件：<BR>
     * 口座ID     ＝   顧客属性.get口座ID()<BR>
     * 補助口座ID       ＝   顧客属性.get補助口座ID（補助口座タイプ）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@補助口座タイプ・・・補助口座タイプ.現物あるいは補助口座タイプ.信用（実際のデータは補助口座タイプ.信用のみ）<BR>
     * 注文カテゴリ   ＝   現引現渡<BR>
     * 発注日      ＞＝  当日<BR>
     * 注文状態     NOT IN  取消済<BR>
     * (<BR>
     * 注文有効状態   ＝   オープン<BR>
     * OR<BR>
     * (<BR>
     * 注文有効状態   ＝   クローズ<BR>
     * AND<BR>
     * 約定数量     ＞   0<BR>
     * ))<BR>
     * <BR>
     * ２）　@検索された株式注文のリストを返す<BR>
     * <BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D3750384
     */
    public List getTodaysSwapMarginOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysSwapMarginOrders(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        
        log.debug("l_accountInfo.isMarginCustomer=" + l_accountInfo.isMarginCustomer());
        
        //信用客でない場合要素0のリストを返す
        if (!l_accountInfo.isMarginCustomer())
        {
            return Collections.EMPTY_LIST;
        }
        
        long l_accountId = l_accountInfo.getAccountId();
        long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                EQUITY_MARGIN_SUB_ACCOUNT);
        
        List l_lisRows = l_assetValuation.getTodaysEquityOrders();
        
        log.debug("getTodaysEquityOrders　@size=" + l_lisRows.size());
        
        //フィルタする
        TreeMap l_ht = new TreeMap();
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitParams l_params = (EqtypeOrderUnitParams) l_lisRows.
            get(i);
            if((l_subAccountId == l_params.getSubAccountId())
                    &&
                    (OrderCategEnum.SWAP_MARGIN.equals(l_params.getOrderCateg())) 
                    &&
                    (!OrderStatusEnum.CANCELLED.equals(l_params.getOrderStatus())) 
                    &&
                    ((OrderOpenStatusEnum.OPEN.equals(l_params.getOrderOpenStatus())) 
                            ||
                            (l_params.getExecutedQuantity() > 0)
                    )
            )
                
            {            	
                log.debug("追加した既存現引現渡注文=" + l_params);
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
        }
        
        
        //新規注文内容を取り込む
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        
        if (l_newOrderSpecs != null)
        {
            for (int i = 0; i < l_newOrderSpecs.length; i++)
            {
                if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_newOrderSpecs[i].
                        getOrderType()) ||
                        OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_newOrderSpecs[i].
                                getOrderType()))
                {
                    
                    //新規注文内容と同一注文単位ＩＤのParamsがあるかチェック
                    EqtypeOrderUnitRow l_oldParams = (EqtypeOrderUnitRow)
                    l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                    
                    EqtypeOrderUnitParams l_params = null;
                    
                    //古いParamsに新しい情報を上書きする		        	
                    if (l_oldParams != null)
                    {	
                        //旧ParamsからコピーParams作成
                        l_params = new EqtypeOrderUnitParams(l_oldParams);
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                        
                        //旧Paramsを削除
                        l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                        
                    }
                    else
                    {
                        l_params = new EqtypeOrderUnitParams();
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());				        
                        
                    }
                    
                    //書換えor新しいParamsを追加
                    //現渡取消注文は、取消済として約定済代金に計上しない
                    //このときの概算受渡代金は0円                        
                    if(l_params.getEstimatedPrice() != 0.0d)
                    {
                        l_ht.put(new Long(l_params.getOrderUnitId()),
                                new WEB3TPEqtypeOrderUnitRowWrapper(l_params));
                    }
                    else
                    {
                        log.debug("概算受渡金額が0の新規注文内容=" + l_newOrderSpecs[i]);
                    }
                    
                }
            }
        }
        
        return new ArrayList(l_ht.values());
    }
    
    /**
     * (get投資信託注文一覧)<BR>
     *　@投資信託注文一覧（投信注文単位のリスト）を取得する。<BR>
     *<BR>
     * １）　@注文検索<BR>
     * <BR>
     * 投信注文単位テーブルより注文検索。<BR>
     * <BR>
     * 未約定投信（買付、募集、乗換、売付<出金予約付き>）<BR>
     * を検索。<BR>
     * <BR>
     * 条件：<BR>
     * 口座ID      ＝   顧客属性.get口座ID()<BR>
     * 補助口座ID   ＝   顧客属性.get補助口座ID（補助口座タイプ）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@補助口座タイプ・・・現物客：補助口座タイプ.現物<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@・・・信用客：補助口座タイプ.現物あるいは補助口座タイプ.信用（実際のデータは補助口座タイプ.信用のみ）<BR>
     * 銘柄タイプ   ＝   投信<BR>
     * 取引種別     ＝   投信買付　@OR　@投信募集　@OR　@投信乗換　@OR　@(投信売付 AND 出金注文識別コード IS NOT NULL)<BR>
     * 注文有効状態　@＝　@オープン<BR>
     * 決済区分     ＝円価<BR>
     * <BR>
     * <BR>
     * <BR>
     * ２）　@検索された投信注文のリストを返す<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D37503A3
     */
    public List getMutualFundOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getMutualFundOrders(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            //投信約定は夜間バッチで反映されるので（客観反映済）
            //未約定のみ抽出すればよい
            //ただし約定中のものが存在するので
            //注文有効状態＝オープンのものはすべて抽出する。
            //外貨建て投信は円価のもののみ抽出。
            
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(0);
            
            List l_lisBindVars = new ArrayList();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            long l_accountId = l_accountInfo.getAccountId();
            l_lisBindVars.add(new Long(l_accountId));
            
            //抽出条件　@補助口座IDの取得
            //補助口座タイプ・・・現物客：補助口座タイプ.現物
            //                   ・・・信用客：補助口座タイプ.現物あるいは補助口座タイプ.信用（実際のデータは補助口座タイプ.信用のみ）
            long l_defaultSubAccountId;
            //現物客の場合
            if (!l_accountInfo.isMarginCustomer())
            {
                l_strWhereBuf.append("sub_account_id = ? AND ");
                long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                        EQUITY_SUB_ACCOUNT);
                l_defaultSubAccountId = l_subAccountId;
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            //信用客の場合
            else
            {
                l_strWhereBuf.append("sub_account_id in (?, ?) AND ");
                long l_equitySubAccountId = l_accountInfo.getSubAccountId(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_equitySubAccountId));
                long l_marginSubAccountId = l_accountInfo.getSubAccountId(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                l_defaultSubAccountId = l_marginSubAccountId;
                l_lisBindVars.add(new Long(l_marginSubAccountId));
                
            }
            
            l_strWhereBuf.append("product_type = ? AND ");
            l_lisBindVars.add(ProductTypeEnum.MUTUAL_FUND);
            
            l_strWhereBuf.append("(order_type = ? OR ");
            l_lisBindVars.add(OrderTypeEnum.MF_BUY);
            
            l_strWhereBuf.append("order_type = ? OR ");
            l_lisBindVars.add(OrderTypeEnum.MF_RECRUIT);
            
            l_strWhereBuf.append("order_type = ? OR ");
            l_lisBindVars.add(OrderTypeEnum.MF_SWITCHING);
            
            l_strWhereBuf.append("(order_type = ? AND payment_order_req_number IS NOT NULL )) AND ");
            l_lisBindVars.add(OrderTypeEnum.MF_SELL);
            
            l_strWhereBuf.append("order_open_status = ? AND ");
            l_lisBindVars.add(OrderOpenStatusEnum.OPEN);
            
            l_strWhereBuf.append("settlement_div = ? ");
            l_lisBindVars.add(WEB3SettlementDivDef.JAPANESE_CURRENCY);
            
            String l_strWhere = l_strWhereBuf.toString();
            Object[] l_bindVars = l_lisBindVars.toArray();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(MutualFundOrderUnitRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            TreeMap l_ht = new TreeMap();
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                MutualFundOrderUnitParams l_params = (MutualFundOrderUnitParams)
                l_lisRows.get(i);
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
            
            //新規注文内容を取り込む
            WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
            
            if (l_newOrderSpecs != null)
            {
                for (int i = 0; i < l_newOrderSpecs.length; i++)
                {
                    if (OrderTypeEnum.MF_BUY.equals(l_newOrderSpecs[i].getOrderType())
                            || OrderTypeEnum.MF_RECRUIT.equals(l_newOrderSpecs[i].getOrderType())
                            || OrderTypeEnum.MF_SWITCHING.equals(l_newOrderSpecs[i].getOrderType()))
                    {
                        //新規注文内容と同一注文単位ＩＤのParamsがあるかチェック
                        MutualFundOrderUnitRow l_oldParams = (MutualFundOrderUnitRow)
                        l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                        
                        MutualFundOrderUnitParams l_params = null;
                        
                        //古いParamsに新しい情報を上書きする		        	
                        if (l_oldParams != null)
                        {	
                            //旧ParamsからコピーParams作成
                            l_params = new MutualFundOrderUnitParams(l_oldParams);
                            
                            //旧Paramsを削除
                            l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                        }
                        else
                        {
                            l_params = new MutualFundOrderUnitParams();
                        }
                        
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_defaultSubAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setWithholdingTaxRestriction(l_newOrderSpecs[i].getWithholdingTaxRestriction());
                        l_params.setPaymentDate(l_newOrderSpecs[i].getPaymentDate());
                        
                        l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                    }	                        
                }
            }
            
            return new ArrayList(l_ht.values());
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get投資信託注文<取引代金>)<BR>
     *　@引数.投信注文一覧から取引代金の計上対象となる注文だけ抽出する。<BR>
     *<BR>
     * １）以下の条件でフィルタリングする。<BR>
     * <BR>
     * 　@・注文種別＝投信買付　@OR　@投信募集　@OR　@投信売付<BR>
     * <BR>
     * ２）条件に適合した注文のリストを返す。<BR>
     * <BR>
     * @@param l_mutualFundOrders - (投資信託注文一覧)
     * @@param l_assetValuation - (資産評価)
     * @@return List
     */
    public List getMutualFundOrdersAmount(List l_mutualFundOrders, WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getMutualFundOrdersAmount(List l_mutualFundOrders, WEB3TPAssetValuation l_assetValuation)";
        
        List l_list = new ArrayList();
        for(int i = 0; i < l_mutualFundOrders.size(); i++)
        {
            MutualFundOrderUnitRow l_row = (MutualFundOrderUnitRow) l_mutualFundOrders.get(i);
            OrderTypeEnum l_orderType = l_row.getOrderType();
            
            //投信買付、投信募集、投信売付注文のみ取得する
            if(OrderTypeEnum.MF_BUY.equals(l_orderType)
                    || OrderTypeEnum.MF_RECRUIT.equals(l_orderType)
                    || OrderTypeEnum.MF_SELL.equals(l_orderType))
            {
                l_list.add(l_row);
            }
        }
        return l_list;
    }
    
    /**
     * (get投資信託注文<譲渡益税>)<BR>
     *　@引数.投信注文一覧から投資信託譲渡益税拘束金の計上対象となる注文だけ抽出する。<BR>
     *<BR>
     * １）以下の条件でフィルタリングする。<BR>
     * <BR>
     * 　@・注文種別＝投信乗換<BR>
     * <BR>
     * ２）条件に適合した注文のリストを返す。<BR>
     * <BR>
     * @@param l_mutualFundOrders - (投資信託注文一覧)
     * @@param l_assetValuation - (資産評価)
     * @@return List
     */
    public List getMutualFundOrdersCapitalGainTax(List l_mutualFundOrders, WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getMutualFundOrdersCapitalGainTax(List l_mutualFundOrders, WEB3TPAssetValuation l_assetValuation)";
        
        List l_list = new ArrayList();
        for(int i = 0; i < l_mutualFundOrders.size(); i++)
        {
            MutualFundOrderUnitRow l_row = (MutualFundOrderUnitRow) l_mutualFundOrders.get(i);
            OrderTypeEnum l_orderType = l_row.getOrderType();
            
            //投信乗換注文のみ取得する
            if(OrderTypeEnum.MF_SWITCHING.equals(l_orderType))
            {
                l_list.add(l_row);
            }
        }
        return l_list;
    }
    
    /**
     * (get投資信託前受拘束<未約定>)<BR>
     *　@引数.投信注文一覧から投資信託前受拘束金の計上対象となる注文だけ抽出する。<BR>
     *<BR>
     * １）以下の条件でフィルタリングする。<BR>
     * <BR>
     * 　@・注文種別＝投信買付　@OR　@投信募集　@OR　@投信乗換<BR>
     * <BR>
     * ２）条件に適合した注文のリストを返す。<BR>
     * <BR>
     * @@param l_mutualFundOrders - (投資信託注文一覧)
     * @@param l_assetValuation - (資産評価)
     * @@return List
     */
    public List getMutualFundAdvanceRestraintUnExecuted(List l_mutualFundOrders, WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getMutualFundAdvanceRestraintUnExecuted(List l_mutualFundOrders, WEB3TPAssetValuation l_assetValuation)";
        
        List l_list = new ArrayList();
        for(int i = 0; i < l_mutualFundOrders.size(); i++)
        {
            MutualFundOrderUnitRow l_row = (MutualFundOrderUnitRow) l_mutualFundOrders.get(i);
            OrderTypeEnum l_orderType = l_row.getOrderType();
            
            //投信買付、投信募集、投信乗換注文のみ取得する
            if(OrderTypeEnum.MF_BUY.equals(l_orderType)
                    || OrderTypeEnum.MF_RECRUIT.equals(l_orderType)
                    || OrderTypeEnum.MF_SWITCHING.equals(l_orderType))
            {
                l_list.add(l_row);
            }
        }
        return l_list;
    }
    
    /**
     * (get投資信託前受拘束<約定済>)<BR>
     *　@確定取引明細テーブルから投資信託前受拘束金の計上対象となる注文を取得する。<BR>
     *<BR>
     * １） 確定取引明細テーブルより以下の条件で検索。<BR>
     * <BR>
     * 条件：<BR>
     * ・顧客ID ＝ 顧客属性.get顧客ID()<BR>
     * ・補助口座ID IN (顧客属性.get補助口座ID（補助口座タイプ(*)）)<BR>
     * ・トランザクションタイプ IN (投資信託買付、投資信託募集)<BR>
     * ・営業日[0]＜受渡日<BR>
     * ・削除フラグ＝false
     * <BR>
     * (*)補助口座タイプ⇒現物客：補助口座タイプ.現物<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@⇒信用客：補助口座タイプ.現物あるいは補助口座タイプ.信用<BR>
     * <BR>
     * ２）　@検索された確定取引明細のリストを返す<BR>
     * <BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     */
    public List getMutualFundAdvanceRestraintFixed(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getMutualFundAdvanceRestraintFixed(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
            Date l_datT0 = l_calcCondition.getBizDate(0);
            
            List l_lisBindVars = new ArrayList();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_lisBindVars.add(new Long(l_accountInfo.getAccountId()));
            
            //抽出条件　@補助口座IDの取得
            //補助口座タイプ・・・現物客：補助口座タイプ.現物
            //                   ・・・信用客：補助口座タイプ.現物あるいは補助口座タイプ.信用（実際のデータは補助口座タイプ.信用のみ）
            //現物客の場合
            if (!l_accountInfo.isMarginCustomer())
            {
                l_strWhereBuf.append("sub_account_id = ? AND ");
                long l_subAccountId =
                    l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            //信用客の場合
            else
            {
                l_strWhereBuf.append("sub_account_id in (?, ?) AND ");
                long l_equitySubAccountId =
                    l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_equitySubAccountId));
                long l_marginSubAccountId =
                    l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_marginSubAccountId));
                
            }
            
            l_strWhereBuf.append("fin_transaction_type IN(?,?) AND ");
            l_lisBindVars.add(FinTransactionType.EQTYPE_MF_BUY);
            l_lisBindVars.add(FinTransactionType.EQTYPE_MF_RECRUIT);
            
            l_strWhereBuf.append("delivery_date > ? AND ");
            l_lisBindVars.add(l_datT0);
            
            l_strWhereBuf.append("delete_flag = ?");
            l_lisBindVars.add(BooleanEnum.FALSE);
            
            String l_strWhere = l_strWhereBuf.toString();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows =
                l_qp.doFindAllQuery(
                        FixedFinTransactionRow.TYPE,
                        l_strWhere,
                        null,
                        l_lisBindVars.toArray());
            
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
        }
    }
    
    /**
     * (get投信銘柄)<BR>
     *　@投信銘柄を取得する。<BR>
     *<BR>
     * １）銘柄検索<BR>
     * <BR>
     * 投信銘柄マスターテーブルより銘柄検索。<BR>
     * <BR>
     * 条件：<BR>
     * 銘柄ID ＝ 引数.銘柄ID<BR>
     * <BR>
     * ２）検索された投信銘柄を返す<BR>
     * @@param l_lngProductId - (銘柄ID)
     * @@return MutualFundProductRow
     */
    public MutualFundProductRow getMutualFundProduct(long l_lngProductId)
    {
        final String STR_METHOD_NAME =
            "getMutualFundProduct(long l_lngProductId)";
        
        MutualFundProductRow l_row = null;
        
        try
        {
            log.debug("mutual_fund_product table : product_id=" + l_lngProductId);
            
            l_row = MutualFundProductDao.findRowByPk(l_lngProductId);
        }
        catch (DataFindException dfe)
        {
            l_row = null;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
        return l_row;
    }
    
    /**
     * (get為替レート)<BR>
     *　@為替レートを取得する。<BR>
     *<BR>
     * １）為替レート検索<BR>
     * <BR>
     * 為替レートテーブルより検索。<BR>
     * <BR>
     * 条件：<BR>
     * 証券会社コード ＝ 引数.証券会社コード<BR>
     * 通貨コード 　@　@＝ 引数.通貨コード<BR>
     * <BR>
     * ２）検索された為替レートを返す<BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strCurrencyCode - (通貨コード)
     * @@return ExchangeRateRow
     */
    public ExchangeRateRow getExchangeRate(String l_strInstitutionCode, String l_strCurrencyCode)
    {
        final String STR_METHOD_NAME =
            "getExchangeRate(String l_strInstitutionCode, String l_strCurrencyCode)";
        
        ExchangeRateRow l_row = null;
        
        try
        {
            log.debug("exchange_rate table : institution_code=" + l_strInstitutionCode + " currency_code=" + l_strCurrencyCode);
            
            l_row = ExchangeRateDao.findRowByPk(l_strInstitutionCode, l_strCurrencyCode);
        }
        catch (DataFindException dfe)
        {
            l_row = null;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
        return l_row;
    }
    
    /**
     * (get当日以降オプション注文)<BR>
     *　@当日以降オプション注文（オプション注文単位のリスト）を取得する。<BR>
     *<BR>
     * １）　@注文検索<BR>
     * <BR>
     * 先物オプション注文単位テーブルより<BR>
     * <BR>
     * 当日約定済新規買建<BR>
     * 当日約定済買建返済<BR>
     * 当日未約定新規買建<BR>
     * 翌日未約定新規買建<BR>
     * <BR>
     * を検索。<BR>
     * <BR>
     * 条件：<BR>
     * 口座ID     ＝   顧客属性.get口座ID()<BR>
     * 補助口座ID   ＝   顧客属性.get補助口座ID（補助口座タイプ.現物）<BR>
     * 銘柄タイプ    ＝   先物オプション<BR>
     * 取引種別     IN  オプション新規買建、オプション買返済<BR>
     * 発注日      ＞＝  当日<BR>
     * 注文状態     NOT IN  取消済<BR>
     * (<BR>
     * 取引種別 ＝   オプション新規買建<BR>
     * AND<BR>
     * (注文有効状態  ＝   オープン<BR>
     * OR<BR>
     * 約定数量 ＞０<BR>
     * ))<BR>
     * AND<BR>
     * (<BR>
     * 取引種別 ＝   オプション買返済<BR>
     *      AND<BR>
     * 約定数量 ＞０<BR>
     * )<BR>
     * <BR>
     * ２）　@検索されたオプション注文のリストを返す<BR>
     * <BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D37503B3
     */
    public List getTodaysOptionOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysOptionOrders(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            
            long l_accountId = l_accountInfo.getAccountId();
            
            long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                    EQUITY_SUB_ACCOUNT);
            
            Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(0);
            
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("product_type = ? AND ");
            l_strWhereBuf.append("((order_type = ?  AND ");
            l_strWhereBuf.append("(order_open_status = ? OR ");
            l_strWhereBuf.append("executed_quantity > ?)) OR ");
            l_strWhereBuf.append("(order_type = ?  AND ");
            l_strWhereBuf.append("executed_quantity > ?)) AND ");
            l_strWhereBuf.append("to_date(biz_date, 'YYYYMMDD') >= ?");
            
            String l_strWhere = l_strWhereBuf.toString();
            
            Object[] l_bindVars =
            {
                    new Long(l_accountId),
                    new Long(l_subAccountId),
                    ProductTypeEnum.IFO,
                    OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN,
                    OrderOpenStatusEnum.OPEN,
                    new Double(0),
                    //OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE,
                    OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE,
                    new Double(0),
                    l_datT0
            };
            
            log.debug(l_strWhere + l_bindVars);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(IfoOrderUnitRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            TreeMap l_ht = new TreeMap();
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                //市場確認済値使用対応
                IfoOrderUnitRow l_params = (IfoOrderUnitRow) l_lisRows.get(i);
                l_ht.put(new Long(l_params.getOrderUnitId()), new WEB3TPIfoOrderUnitRowWrapper(l_params));
            }
            
            //新規注文内容を取り込む
            WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
            if (l_newOrderSpecs != null)
            {
                for (int i = 0; i < l_newOrderSpecs.length; i++)
                {
                    //オプション買建の場合のみ（オプション買返済は約定済のみ取り込むため新規注文内容はとりこまない）
                    if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_newOrderSpecs[i].
                            getOrderType()))
                    {
                        //新規注文内容と同一注文単位ＩＤのParamsがあるかチェック
                        IfoOrderUnitRow l_oldParams = (IfoOrderUnitRow)
                        l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                        
                        IfoOrderUnitParams l_params = null;
                        
                        //古いParamsに新しい情報を上書きする		        	
                        if (l_oldParams != null)
                        {	
                            //旧ParamsからコピーParams作成
                            l_params = new IfoOrderUnitParams(l_oldParams);
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                    getEstimatedPrice());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            
                            //旧Paramsを削除
                            l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                            
                        }
                        else
                        {
                            l_params = new IfoOrderUnitParams();
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                    getEstimatedPrice());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            
                        }
                        
                        //書換えor新しいParamsを追加
                        if(l_params.getEstimatedPrice() != 0.0d)
                        {
                            l_ht.put(new Long(l_params.getOrderUnitId()),
                                    new WEB3TPIfoOrderUnitRowWrapper(l_params));
                        }
                        else
                        {
                            log.debug("概算受渡金額が0の新規注文内容=" + l_newOrderSpecs[i]);
                        }
                    }
                }
            }
            
            return new ArrayList(l_ht.values());
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get当日以降GP注文)<BR>
     *　@当日以降GP注文（累投注文単位のリスト）を取得する。<BR>
     *<BR>
     * １）　@注文検索<BR>
     * <BR>
     * 累投注文単位テーブルより注文検索。<BR>
     * <BR>
     * 当日約定済累投（買、売（解約））<BR>
     * を検索。<BR>
     * <BR>
     * 条件：<BR>
     * 口座D      ＝   顧客属性.get口座ID()<BR>
     * 補助口座ID   ＝   顧客属性.get補助口座ID（補助口座タイプ.現物）<BR>
     * 銘柄タイプ    ＝   累投<BR>
     * 取引種別     ＝   累投買、累投売（累投解約）<BR>
     * コース      ＝   中ファ@ン、MMF<BR>
     * 注文状態　@        NOT IN  取消中、取消済<BR>
     * (受渡方法@　@　@　@＝   証券口座入力<BR>
     * OR<BR>
     * 受渡方法@　@　@　@＝   NULL)<BR>
     * 発注日      ＞＝　@当日<BR>
     * (<BR>
     * 取引種別     ＝   累投買<BR>
     * AND<BR>
     * 受渡日      ＞＝  当日<BR>
     * )<BR>
     * OR<BR>
     * (<BR>
     * 取引種別     ＝   累投売<BR>
     * AND<BR>
     * 受渡日      ＞＝  翌日<BR>
     * )<BR>
     * <BR>
     * ２）　@検索されたGP注文のリストを返す<BR>
     * <BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D37503D2
     */
    public List getTodaysGPOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysGPOrders(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            
            long l_accountId = l_accountInfo.getAccountId();
            long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                    EQUITY_SUB_ACCOUNT);
            
            Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(0);
            Date l_datT1 = l_assetValuation.getCalcCondition().getBizDate(1);
            
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("product_type = ? AND ");
            l_strWhereBuf.append("ruito_type in (?, ?) AND ");
            l_strWhereBuf.append("order_open_status = ? AND ");
            l_strWhereBuf.append("order_status not in (?, ?) AND ");
            l_strWhereBuf.append(
            "((payment_method = ?) OR (payment_method IS NULL)) AND ");
            l_strWhereBuf.append("to_date(biz_date, 'YYYYMMDD') >= ?");
            
            String l_strWhere = l_strWhereBuf.toString();
            Object[] l_bindVars =
            {
                    new Long(l_accountId),
                    new Long(l_subAccountId),
                    ProductTypeEnum.RUITO,
                    RuitoTypeEnum.CHUUKOKU_FUND,
                    RuitoTypeEnum.MMF,
                    OrderOpenStatusEnum.OPEN,
                    OrderStatusEnum.CANCELLING,
                    OrderStatusEnum.CANCELLED,
                    WEB3PaymentMethodDef.SECURITIES_ACCOUNT_INPUT,
                    l_datT0,
            };
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(RuitoOrderUnitRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            TreeMap l_ht = new TreeMap();
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                RuitoOrderUnitParams l_params = (RuitoOrderUnitParams) l_lisRows.get(
                        i);
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
            
            //新規注文内容を取り込む
            WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
            if (l_newOrderSpecs != null)
            {
                for (int i = 0; i < l_newOrderSpecs.length; i++)
                {
                    //買の場合は受渡日が当日以降、売りの場合は受渡日が翌日以降
                    if ( ( (OrderTypeEnum.RUITO_BUY.equals(l_newOrderSpecs[i].
                            getOrderType()) &&
                            (l_datT0.compareTo(l_newOrderSpecs[i].getDeliveryDate()) <= 0))) ||
                            (OrderTypeEnum.RUITO_SELL.equals(l_newOrderSpecs[i].getOrderType()) &&
                                    (l_datT1.compareTo(l_newOrderSpecs[i].getDeliveryDate()) <= 0)))
                    {
                        //新規注文内容と同一注文単位ＩＤのParamsがあるかチェック
                        RuitoOrderUnitRow l_oldParams = (RuitoOrderUnitRow)
                        l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                        
                        RuitoOrderUnitParams l_params = null;
                        
                        //古いParamsに新しい情報を上書きする		        	
                        if (l_oldParams != null)
                        {	
                            //旧ParamsからコピーParams作成
                            l_params = new RuitoOrderUnitParams(l_oldParams);
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            
                            //旧Paramsを削除
                            l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                            
                        }
                        else
                        {
                            l_params = new RuitoOrderUnitParams();
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());    			
                        }
                        
                        //書換えor新しいParamsを追加
                        if(l_params.getQuantity() != 0.0d)
                        {
                            l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                        }
                        else
                        {
                            log.debug("概算受渡金額が0の新規注文内容=" + l_newOrderSpecs[i]);
                        }
                    }
                }
            }
            
            return new ArrayList(l_ht.values());
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get当日以降ミニ株注文)<BR>
     *　@当日以降ミニ株注文（株式注文単位のリスト）を取得する。<BR>
     *<BR>
     * １）注文検索<BR>
     * <BR>
     * 株式注文単位テーブルより注文検索<BR>
     * <BR>
     * 前日未約定ミニ株（買）<BR>
     * 当日未約定ミニ株（買）<BR>
     * 翌日未約定ミニ株（買）<BR>
     * を検索。<BR>
     * <BR>
     * 条件：<BR>
     * 口座ID     ＝   顧客属性.get口座ID()<BR>
     * 補助口座ID   ＝   顧客属性.get補助口座ID（補助口座タイプ.現物）<BR>
     * 注文状態     NOT IN  取消済<BR>
     * ((取引種別     ＝   ミニ株買 AND<BR>
     *　@(注文有効状態　@＝　@オープン OR　@
     * (注文有効状態　@＝クローズ AND 注文数量＝約定数量)))
     *  OR
     * (取引種別     ＝   ミニ株売 AND<BR>
     * (注文有効状態　@＝クローズ AND 注文数量＝約定数量)))     *
     * 発注日      ＞＝  当日<BR>
     * <BR>
     * ２）検索されたミニ株注文（株式注文と同じ型）の配列を返す<BR>
     * <BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D37503E2
     */
    public List getTodaysMiniStockOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysMiniStockOrders(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
        
        long l_accountId = l_accountInfo.getAccountId();
        long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                EQUITY_SUB_ACCOUNT);
        
        List l_lisRows = l_assetValuation.getTodaysEquityOrders();
        
        //フィルタする
        TreeMap l_ht = new TreeMap();
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitParams l_params = (EqtypeOrderUnitParams) l_lisRows.get(i);
            if ((l_subAccountId == l_params.getSubAccountId())
                    &&
                    (!(OrderStatusEnum.CANCELLED.equals(l_params.getOrderStatus())))
                    &&
                    (((OrderTypeEnum.MINI_STOCK_BUY.equals(l_params.getOrderType()))
                            &&
                            ((OrderOpenStatusEnum.OPEN.equals(l_params.getOrderOpenStatus()))
                                    || 
                                    (OrderOpenStatusEnum.CLOSED.equals(l_params.getOrderOpenStatus())
                                            &&
                                            ((l_params.getQuantity() == l_params.getExecutedQuantity()) 
                                                    &&
                                                    (l_params.getExecutedQuantity() > 0))
                                    )
                            )
                    )                     
                    || 
                    ((OrderTypeEnum.MINI_STOCK_SELL.equals(l_params.getOrderType())) 
                            && 
                            ((OrderOpenStatusEnum.CLOSED.equals(l_params.getOrderOpenStatus()))
                                    &&
                                    ((l_params.getQuantity() == l_params.getExecutedQuantity()) 
                                            &&
                                            (l_params.getExecutedQuantity() > 0))
                            )
                    )
                    )
            )
            {
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
        }
        
        //新規注文内容を取り込む
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        if (l_newOrderSpecs != null)
        {
            for (int i = 0; i < l_newOrderSpecs.length; i++)
            {
                if (OrderTypeEnum.MINI_STOCK_BUY.equals(l_newOrderSpecs[i].
                        getOrderType()))
                {
                    
                    //新規注文内容と同一注文単位ＩＤのParamsがあるかチェック
                    EqtypeOrderUnitRow l_oldParams = (EqtypeOrderUnitRow)
                    l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                    
                    EqtypeOrderUnitParams l_params = null;
                    
                    //古いParamsに新しい情報を上書きする		        	
                    if (l_oldParams != null)
                    {	
                        //旧ParamsからコピーParams作成
                        l_params = new EqtypeOrderUnitParams(l_oldParams);
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                        
                        //旧Paramsを削除
                        l_ht.remove(new Long(l_oldParams.getOrderUnitId()));			        	
                        
                    }
                    else
                    {
                        l_params = new EqtypeOrderUnitParams();
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());				        			
                    }
                    
                    //書換えor新しいParamsを追加
                    if(l_params.getEstimatedPrice() != 0.0d)
                    {
                        l_ht.put(new Long(l_params.getOrderUnitId()),
                                new WEB3TPEqtypeOrderUnitRowWrapper(l_params));
                    }
                    else
                    {
                        log.debug("概算受渡金額が0の新規注文内容=" + l_newOrderSpecs[i]);
                    }
                }
            }
        }
        
        return new ArrayList(l_ht.values());
    }
    
    /**
     * (get当日以降外株注文)<BR>
     * 当日以降外株注文（外株注文単位のリスト）を取得する。<BR>
     * <BR>
     * 条件：<BR>
     * 口座ID 　@　@　@　@　@= 顧客属性.get口座ID()<BR>
     * 補助口座ID 　@　@　@　@　@= 顧客属性.get補助口座ID(補助口座タイプ.現物)<BR>
     * 決済区分 　@　@　@　@　@= 円価<BR>
     * 約定データファ@イル送信フラグ　@!= 　@　@客勘反映済("2：バッチ更新済")<BR>
     * (取引種別 　@　@　@　@　@= 外株買<BR>
     * AND<BR>
     * (注文有効状態 　@　@　@　@　@ = オープン<BR>
     * OR<BR>
     * 約定数量 　@　@　@　@　@ > 0))<BR>
     * OR<BR>
     * 検索条件が以下分岐を追加－－－<BR>
     * 　@　@『外株日計り取引採用(※１)の場合』<BR>
     * 　@　@ ( 取引種別 = 外株売 AND<BR>
     * 　@　@　@(注文有効状態 = オープン OR 約定数量 > 0）AND<BR>
     * 　@　@　@仮約定フラグ <> 1：仮約定)<BR>
     * <BR>
     * 　@　@『（※１以外）外株日計り取引未採用の場合』<BR>
     * 　@　@(　@取引種別 = 外株売 AND<BR>
     * 　@　@　@約定数量 > 0 AND<BR>
     * 　@　@　@出来終了処理日時 IS NOT NULL)<BR>
     * <BR>
     * (※１)引数.資産評価.get余力計算条件().is外株日計り取引採用()が,trueの場合<BR>
     * <BR>
     * ２．引数.資産評価.現注文内容 !=nullの場合,<BR>
     * 現注文内容の中、外株注文分(注文種別=外株買 <BR>
     * 　@或いは 注文種別=外株売)を外株注文単位テーブル行オブジェクトへ変換する。<BR>
     * <BR>
     * ３．　@１．と２．で取得した株式注文単位テーブル行オブジェクトを引数として<BR>
     * 余力外株注文単位Wrapperを生成しリストに追加する。<BR>
     * （外株注文単位Wrapper※get受渡代金()で市場確認済の値と比較し妥当な値を返却する)<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D3760019
     */
    public List getTodaysFeqOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysFeqOrders(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
            
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("settle_div = ? AND ");
            l_strWhereBuf.append("((exec_file_send_flag IS NULL) OR (exec_file_send_flag != ?)) AND ");
            l_strWhereBuf.append("((order_type = ? AND ");
            l_strWhereBuf.append("(order_open_status = ? OR executed_quantity > ?)) OR");
            //引数.資産評価.get余力計算条件().is外株日計り取引採用()が,trueの場合
            boolean l_blnIsFeqDayTradeAdoption =
                l_assetValuation.getCalcCondition().isFeqDayTradeAdoption();
            if (l_blnIsFeqDayTradeAdoption)
            {
                //( 取引種別 = 外株売 AND
                //(注文有効状態 = オープン OR 約定数量 > 0）AND
                //仮約定フラグ <> 1：仮約定)
                l_strWhereBuf.append(" (order_type = ? AND ");
                l_strWhereBuf.append(
                    "(order_open_status = ? OR executed_quantity > ?) and temporary_execution_flag <> ?))");
            }
            else
            {
                //(　@取引種別 = 外株売 AND
                //約定数量 > 0 AND
                //出来終了処理日時 IS NOT NULL)
                l_strWhereBuf.append(
                    "(order_type = ? AND (executed_quantity > ?) AND exec_end_timestamp IS NOT NULL))");
            }
            String l_strWhere = l_strWhereBuf.toString();
            
            long l_accountId = l_accountInfo.getAccountId();
            long l_subAccountId;
            //現物客の場合
            if (!l_accountInfo.isMarginCustomer())
            {
                l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                        EQUITY_SUB_ACCOUNT);
            }
            //信用客の場合
            else
            {
                l_subAccountId = l_accountInfo.getSubAccountId(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            
            Object[] l_bindVars = null;
            if (l_blnIsFeqDayTradeAdoption)
            {
                l_bindVars =new Object[]{
                    new Long(l_accountId),
                    new Long(l_subAccountId),
                    WEB3TPFeqSettlementDivDef.JAPANESE_CURRENCY,
                    WEB3TPFeqExecFileSendStatusDivDef.PROCESSED,
                    OrderTypeEnum.FEQ_BUY,
                    OrderOpenStatusEnum.OPEN,
                    new Double(0),
                    OrderTypeEnum.FEQ_SELL,
                    OrderOpenStatusEnum.OPEN,
                    new Double(0),
                    WEB3TemporaryExecutionFlagDef.TEMPORARY_EXEC};
            }
            else
            {
                l_bindVars =new Object[]{
                    new Long(l_accountId),
                    new Long(l_subAccountId),
                    WEB3TPFeqSettlementDivDef.JAPANESE_CURRENCY,
                    WEB3TPFeqExecFileSendStatusDivDef.PROCESSED,
                    OrderTypeEnum.FEQ_BUY,
                    OrderOpenStatusEnum.OPEN,
                    new Double(0),
                    OrderTypeEnum.FEQ_SELL,
                    new Double(0)};
            }
            
            
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(FeqOrderUnitRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            double l_dblPreRestRate = this.getPremiumRestraintRate(
                    WEB3CommisionProductCodeDef.FOREIGN_EQITY,
                    l_assetValuation);                            
            
            if(log.ison())
            {
                log.debug("l_strWhere" + l_strWhere);
                for(int i = 0; i < l_bindVars.length; i++)
                {
                    log.debug("l_bindVars[" + i + "]=" + l_bindVars[i]);                    
                }
                log.debug("selectされた外株注文単位数=" + l_lisRows.size());
            }
            
            
            
            TreeMap l_ht = new TreeMap();
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                FeqOrderUnitRow l_params = (FeqOrderUnitRow)l_lisRows.get(i);
                FeqOrderUnitRow l_wrapper = new WEB3TPFeqOrderUnitRowWrapper(l_params);
                l_ht.put(new Long(l_wrapper.getOrderUnitId()), l_wrapper);
            }
            
            //新規注文内容を取り込む
            WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
            if (l_newOrderSpecs != null)
            {
                for (int i = 0; i < l_newOrderSpecs.length; i++)
                {                    
                    //注文種別=外株買 或いは 注文種別=外株売のみ新規注文内容を取り込む
                    if (OrderTypeEnum.FEQ_BUY.equals(l_newOrderSpecs[i].getOrderType())
                        || OrderTypeEnum.FEQ_SELL.equals(l_newOrderSpecs[i].getOrderType()))
                    {
                        FeqOrderUnitRow l_oldParams = (FeqOrderUnitRow)l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));                    
                        FeqOrderUnitParams l_params = null;
                        
                        //注文が存在した場合
                        if(l_oldParams != null)
                        {
                            l_params = new FeqOrderUnitParams(l_oldParams);
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                    getEstimatedPrice());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            
                            //旧Paramsを削除
                            l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                            
                            log.debug("訂正注文内容を取り込み=" + l_params);
                            
                        }                    
                        else
                        {
                            l_params = new FeqOrderUnitParams();
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setEstimatedPrice(l_newOrderSpecs[i].getEstimatedPrice());
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            l_params.setBizDate(WEB3DateUtility.formatDate(l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            
                            l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
                            l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
                            
                        }                        
                        
                        //書換えor新しいParamsを追加
                        if(l_params.getEstimatedPrice() != 0.0d)
                        {
                            l_ht.put(new Long(l_params.getOrderUnitId()),
                                    new WEB3TPFeqOrderUnitRowWrapper(l_params));
                            
                            log.debug("新規注文内容を取り込み=" + l_params);
                            
                        }
                        else
                        {
                            log.debug("概算受渡金額が0の新規注文内容=" + l_newOrderSpecs[i]);
                        }	                    	                    
                    }                    
                }
            }
            
            return new ArrayList(l_ht.values());
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get当日以降入出金注文)<BR>
     *　@当日以降入出金注文（入出金振替注文単位のリスト）を取得する。<BR>
     *<BR>
     * １）　@注文検索<BR>
     * <BR>
     * 入出金振替注文単位テーブルより注文検索<BR>
     * <BR>
     * 条件：<BR>
     * 口座ID     ＝   顧客属性.get口座ID()<BR>
     * 補助口座ID＝顧客属性.get補助口座ID（補助口座タイプ.現物）<BR>
     * 取引種別      IN     (入金注文,  出金注文)<BR>
     * 注文状態 NOT IN   (発注失敗, 取消済)<BR>
     * 発注日      ＞＝  当日<BR>
     * 
     * ２）　@検索された入出金注文のリストを返す<BR>
     * <BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D3760029
     */
    public List getTodaysCashInOutOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysCashInOutOrders(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {        	
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
            
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("order_type in (?,?) AND ");
            l_strWhereBuf.append("order_status not in (?, ?) AND ");
            l_strWhereBuf.append("to_date(biz_date, 'YYYYMMDD') >= ?");
            
            long l_accountId = l_accountInfo.getAccountId();
            //入出金は預り金口座のみ
            long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                    EQUITY_SUB_ACCOUNT);
            
            Date l_datT0 = l_calcCondition.getBizDate(0);
            
            List l_lisBindVars = new ArrayList();
            l_lisBindVars.add(0, new Long(l_accountId));
            l_lisBindVars.add(1, new Long(l_subAccountId));
            l_lisBindVars.add(2, OrderTypeEnum.CASH_IN);
            l_lisBindVars.add(3, OrderTypeEnum.CASH_OUT);            
            l_lisBindVars.add(4, OrderStatusEnum.NOT_ORDERED);
            l_lisBindVars.add(5, OrderStatusEnum.CANCELLED);
            l_lisBindVars.add(6, l_datT0);            
                        
            String l_strWhere = l_strWhereBuf.toString();
            Object[] l_bindVars = l_lisBindVars.toArray();            
            
            log.debug("l_strWhere=" + l_strWhere);
            for(int i = 0 ; i < l_bindVars.length; i++)
            {
                log.debug("l_bindVars=[" + i + "]");
                log.debug(l_bindVars[i].toString());                
            }
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(AioOrderUnitRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            TreeMap l_ht = new TreeMap();
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows.get(
                        i);
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
            
            //新規注文内容を取り込む
            WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
            if (l_newOrderSpecs != null)
            {
                for (int i = 0; i < l_newOrderSpecs.length; i++)
                {
                    if (OrderTypeEnum.CASH_IN.equals(l_newOrderSpecs[i].getOrderType()) ||
                            (OrderTypeEnum.CASH_OUT.equals(l_newOrderSpecs[i].getOrderType())))
                    {
                        //新規注文内容と同一注文単位ＩＤのParamsがあるかチェック
                        AioOrderUnitRow l_oldParams = (AioOrderUnitRow)
                        l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                        
                        AioOrderUnitParams l_params = null;
                        
                        //古いParamsに新しい情報を上書きする		        	
                        if (l_oldParams != null)
                        {	
                            //旧ParamsからコピーParams作成
                            l_params = new AioOrderUnitParams(l_oldParams);
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            //有料情報区分
                            l_params.setPaymentApplicationDiv(l_newOrderSpecs[i].getPaymentApplicationDiv());
                            //受注日時
                            l_params.setReceivedDateTime(l_newOrderSpecs[i].getReceivedDateTime());
                            
                            //旧Paramsを削除
                            l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                            
                            log.debug("削除した古い注文単位：" + l_oldParams);
                            
                        }
                        else
                        {
                            l_params = new AioOrderUnitParams();
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            //有料情報区分
                            l_params.setPaymentApplicationDiv(l_newOrderSpecs[i].getPaymentApplicationDiv());
                            //受注日時
                            l_params.setReceivedDateTime(l_newOrderSpecs[i].getReceivedDateTime());
                            
                        }
                        
                        //書換えor新しいParamsを追加
                        if(l_params.getQuantity() != 0.0d)
                        {
                            l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                            log.debug("追加した注文単位：" + l_params);
                        }
                        else
                        {
                            log.debug("概算受渡金額が0の新規注文内容=" + l_newOrderSpecs[i]);
                        }
                    }
                }
            }
            
            return new ArrayList(l_ht.values());
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get当日以降振替注文) <BR>
     * <BR>
     * ロード対象となる振替注文を、AIO注文単位テーブルより検索し、 <BR>
     * 行オブジェクトのリストを返却する。 <BR>
     * <BR>
     * ※シーケンス図「(余力データソースアクセス管理)get当日以降振替注文」参照 <BR>
     * <BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D3760029
     */
    public List getTodaysCashTransferOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysCashTransferOrders(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
            Date l_datT0 = l_calcCondition.getBizDate(0);
            
            List l_lisBindVars = new ArrayList();
            //口座ＩＤ
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            long l_accountId = l_accountInfo.getAccountId();
            l_lisBindVars.add(new Long(l_accountId));
            
            //抽出条件　@補助口座IDの取得
            //補助口座タイプ・・・現物客：補助口座タイプ.現物
            //                   ・・・信用客：補助口座タイプ.現物あるいは補助口座タイプ.信用
            long l_defaultSubAccountId;
            //現物客の場合
            if (!l_accountInfo.isMarginCustomer())
            {
                l_strWhereBuf.append("sub_account_id = ? AND ");
                long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                        EQUITY_SUB_ACCOUNT);
                l_defaultSubAccountId = l_subAccountId;
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            //信用客の場合
            else
            {
                l_strWhereBuf.append("sub_account_id in (?, ?) AND ");
                long l_equitySubAccountId = l_accountInfo.getSubAccountId(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_equitySubAccountId));
                long l_marginSubAccountId = l_accountInfo.getSubAccountId(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                l_defaultSubAccountId = l_marginSubAccountId;
                l_lisBindVars.add(new Long(l_marginSubAccountId));
            }
            //注文状態 not in (発注失敗, 取消済) 
            l_strWhereBuf.append("order_status not in (?, ?) AND ");
            l_lisBindVars.add(OrderStatusEnum.NOT_ORDERED);
            l_lisBindVars.add(OrderStatusEnum.CANCELLED);
            
            //発注日　@T+0以降
            l_strWhereBuf.append("to_date(biz_date, 'YYYYMMDD') >= ?");
            l_lisBindVars.add(l_datT0);
            
            String l_strWhere = l_strWhereBuf.toString();            
            Object[] l_bindVars = l_lisBindVars.toArray();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(AioOrderUnitRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            //抽出対象となる
            //            注文種別：
            //			1005：振替注文(預り金から信用保証金)　@　@　@
            //			1006：振替注文(信用保証金から預り金)　@　@　@
            //			1007：振替注文(預り金から株先証拠金)
            //			1008：振替注文(株先証拠金から預り金)
            //			1011：為替保証金振替注文（預り金から為替保証金）　@←追加
            //			1012：為替保証金振替注文（為替保証金から預り金）　@←追加
            //			1013：外国株式振替注文（預り金から外国株式口座）　@←追加
            //			1014：外国株式振替注文（外国株式口座から預り金）　@←追加
            //			1017：その他振替注文（預り金からX）　@　@　@　@　@　@　@ ←追加
            //			1018：その他振替注文（Xから預り金）　@　@　@　@　@　@　@ ←追加
            //        　@1019：振替注文（預り金から大証金）　@             ←追加
            //        　@1020: 振替注文（預り金からオリックスクレジット）  ←追加
            //          1021：CFD振替注文（預り金からCFD口座）
            //          1022：CFD振替注文（CFD口座から預り金）
            List orderTypeList = new ArrayList();
            orderTypeList.add(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            orderTypeList.add(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            orderTypeList.add(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            orderTypeList.add(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT);
            orderTypeList.add(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            orderTypeList.add(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            orderTypeList.add(OrderTypeEnum.TRANSFER_TO_FEQ);
            orderTypeList.add(OrderTypeEnum.TRANSFER_FROM_FEQ);
            orderTypeList.add(OrderTypeEnum.TO_OTHER_TRANSFER);
            orderTypeList.add(OrderTypeEnum.FROM_OTHER_TRANSFER);
            orderTypeList.add(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK);
            orderTypeList.add(OrderTypeEnum.TO_ORIX_CREDIT);
            orderTypeList.add(OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT);
            orderTypeList.add(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD);
            
            TreeMap l_ht = new TreeMap();
            for(int i = 0; i < l_lisRows.size(); i++)
            {
                AioOrderUnitRow l_params = (AioOrderUnitRow)l_lisRows.get(i);
                log.debug("ＤＢから検索した入出金注文[" + i + "]" + l_params );
                if(orderTypeList.contains(l_params.getOrderType()))
                {            	    
                    l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                    log.debug("抽出した当日振替注文=" + l_params);
                }
                
                
            }
            log.debug("振替入出金注文数=" + l_lisRows.size() + "　@：　@フィルタした入出金注文件数=" + l_ht.size());            	
            
            //新規注文内容を取り込む
            //対象が預り金のサブアカウントの場合だけ新規注文内容が渡される前提
            WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
            if (l_newOrderSpecs != null)
            {
                for (int i = 0; i < l_newOrderSpecs.length; i++)
                {                	
                    if(orderTypeList.contains(l_newOrderSpecs[i].getOrderType()))
                    {                    	
                        //新規注文内容と同一注文単位ＩＤのParamsがあるかチェック
                        AioOrderUnitRow l_oldParams = (AioOrderUnitRow)
                        l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                        
                        AioOrderUnitParams l_params = null;
                        
                        //古いParamsに新しい情報を上書きする		        	
                        if (l_oldParams != null)
                        {	
                            //旧ParamsからコピーParams作成
                            l_params = new AioOrderUnitParams(l_oldParams);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            l_params.setPaymentApplicationDiv(l_newOrderSpecs[i].getPaymentApplicationDiv());
                            l_params.setReceivedDateTime(l_newOrderSpecs[i].getReceivedDateTime());
                            
                            //旧Paramsを削除
                            l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                            
                            log.debug("現注文内容[訂正/取消] 旧=" + l_oldParams + " 新=" + l_params);
                            
                        }
                        else
                        {
                            l_params = new AioOrderUnitParams();
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_newOrderSpecs[i].getSubAccountId());
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            l_params.setPaymentApplicationDiv(l_newOrderSpecs[i].getPaymentApplicationDiv());
                            l_params.setReceivedDateTime(l_newOrderSpecs[i].getReceivedDateTime());
                            
                            
                            AssetTransferTypeEnum l_transferType;
                            if(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_newOrderSpecs[i].getOrderType()) ||
                                    OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_newOrderSpecs[i].getOrderType()) ||    
                                    OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_newOrderSpecs[i].getOrderType()) ||	                               
                                    OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_newOrderSpecs[i].getOrderType()) ||    
                                    OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_newOrderSpecs[i].getOrderType()) ||
                                    OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK.equals(l_newOrderSpecs[i].getOrderType()) ||
                                    OrderTypeEnum.TO_ORIX_CREDIT.equals(l_newOrderSpecs[i].getOrderType()) ||
                                    OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_newOrderSpecs[i].getOrderType())
                            )
                            {
                                l_transferType = AssetTransferTypeEnum.CASH_OUT;
                            }
                            else if(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_newOrderSpecs[i].getOrderType()) ||    
                                    OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(l_newOrderSpecs[i].getOrderType()) ||
                                    OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_newOrderSpecs[i].getOrderType()) ||    
                                    OrderTypeEnum.TRANSFER_FROM_FEQ.equals(l_newOrderSpecs[i].getOrderType()) ||	                               
                                    OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_newOrderSpecs[i].getOrderType()) ||
                                    OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD.equals(l_newOrderSpecs[i].getOrderType())
                            )	                               
                            {
                                l_transferType = AssetTransferTypeEnum.CASH_IN;		                            
                            }
                            else
                            {
                                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);        		                            
                            }
                            
                            //実際は新規注文内容で取り込むのは出金だけ
                            l_params.setTransferType(l_transferType);		                        
                            
                            log.debug("現注文内容[新規]=" + l_params);
                        }
                        
                        //書換えor新しいParamsを追加
                        if(l_params.getQuantity() != 0.0d)
                        {
                            l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                        }
                        else
                        {
                            log.debug("概算受渡金額が0の新規注文内容=" + l_newOrderSpecs[i]);
                        }
                    }
                }
            }
            
            return new ArrayList(l_ht.values());
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get当日以降IPO注文)<BR>
     *　@当日以降IPO注文（IPO注文単位のリスト）を取得する。<BR>
     * <BR>
     * １）　@IPO申告テーブルより以下の条件で注文検索。<BR>
     * 条件：<BR>
     * 口座ID             ＝   　@顧客属性.get顧客ID()<BR>
     * 補助顧客ID       ＝　@顧客属性.get補助口座ID（補助口座タイプ.現物）<BR>
     * IPO申告有効状態        ＝   オープン<BR>
     * IPO購入申込区分        !＝   辞退<BR>
     * (抽選結果         IN  当選)<BR>
     * OR<BR>
     * (抽選結果         IN  補欠<BR>
     * 抽選結果（繰り上げ）　@!＝　@落選)<BR>
     * <BR>
     * ２）　@検索されたIPO注文のリストを返す。<BR>
     * <BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D3760048
     */
    public List getTodaysIPOOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysIPOOrders(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            long l_lngAccountId = l_accountInfo.getAccountId();
            long l_lngSubAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                    EQUITY_SUB_ACCOUNT);
            
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("order_open_status = ? AND ");
            l_strWhereBuf.append("offering_div != ? AND ");
            l_strWhereBuf.append("(lot_result = ? OR ");
            l_strWhereBuf.append("(lot_result = ? AND  ");
            l_strWhereBuf.append("lot_result_retry != ?))");
            
            String l_strWhere = l_strWhereBuf.toString();
            
            Object[] l_bindVars =
            {
                    new Long(l_lngAccountId),
                    new Long(l_lngSubAccountId),
                    OrderOpenStatusEnum.OPEN,
                    WEB3TPIPOOfferingDivDef.CANCELLED,
                    WEB3TPIPOLotResultTypeDef.WIN,
                    WEB3TPIPOLotResultTypeDef.SUBSTITUTE,
                    WEB3TPIPOLotResultTypeDef.LOSE
            };
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            return l_qp.doFindAllQuery(IpoOrderRow.TYPE, l_strWhere, null, l_bindVars);
            
            //IPOは既にあるレコードに対して余力拘束をかけるのみ
            //発注審査に相当する新規注文内容の取り込みは考慮しなくてよい
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get当日以降株式注文<特定口座源泉あり>)<BR>
     *　@当日以降株式注文<特定口座源泉あり>（株式注文単位のリスト）を取得する。<BR>
     * <BR>
     * １）　@注文検索<BR>
     * <BR>
     * 株式注文単位テーブルより注文検索<BR>
     * <BR>
     * 条件：<BR>
     * 口座ID     ＝   顧客属性.口座ID<BR>
     * 補助顧客ID    IN     顧客属性.get補助口座ID（補助口座タイプ.信用）<BR>
     * 銘柄タイプ    ＝   株式<BR>
     * 注文種別     IN  現渡<BR>
     * 税区分      ＝   特定口座源泉徴収あり<BR>
     * 発注日      ＞＝  当日<BR>
     * <BR>
     * ２）　@検索された株式注文のリストを返す<BR>
     * <BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D3760067
     */
    public List getTodaysSpecialWithHoldEquityOrders(WEB3TPAssetValuation
            l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysSpecialWithHoldEquityOrders(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        //信用客でない場合要素0のリストを返す
        if (!l_accountInfo.isMarginCustomer())
        {
            return Collections.EMPTY_LIST;
        }
        
        long l_accountId = l_accountInfo.getAccountId();
        long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                EQUITY_MARGIN_SUB_ACCOUNT);
        
        List l_lisRows = l_assetValuation.getTodaysEquityOrders();
        //フィルタする
        TreeMap l_ht = new TreeMap();
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitParams l_params = (EqtypeOrderUnitParams) l_lisRows.
            get(i);
            
            if((l_subAccountId == l_params.getSubAccountId())
                    &&
                    (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_params.getOrderType())) 
                    &&
                    (TaxTypeEnum.SPECIAL.equals(l_params.getTaxType())))
            {
                
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
        }
        
        //新規注文内容を取り込む
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        
        if (l_newOrderSpecs != null)
        {
            for (int i = 0; i < l_newOrderSpecs.length; i++)
            {
                if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_newOrderSpecs[i].
                        getOrderType()) &&
                        TaxTypeEnum.SPECIAL.equals(l_newOrderSpecs[i].getTaxType()))
                {
                    //新規注文内容と同一注文単位ＩＤのParamsがあるかチェック
                    EqtypeOrderUnitRow l_oldParams = (EqtypeOrderUnitRow)
                    l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                    
                    EqtypeOrderUnitParams l_params = null;
                    
                    //古いParamsに新しい情報を上書きする		        	
                    if (l_oldParams != null)
                    {	
                        //旧ParamsからコピーParams作成
                        l_params = new EqtypeOrderUnitParams(l_oldParams);
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                        
                        //旧Paramsを削除
                        l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                        
                    }
                    else
                    {
                        l_params = new EqtypeOrderUnitParams();
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                        //注文状態追加
                        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
                        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
                        
                    }
                    
                    //書換えor新しいParamsを追加
                    if(l_params.getEstimatedPrice() != 0.0d)
                    {
                        l_ht.put(new Long(l_params.getOrderUnitId()),
                                new WEB3TPEqtypeOrderUnitRowWrapper(l_params));
                        log.debug("追加される新規注文" + l_params);
                    }
                    else
                    {
                        log.debug("概算受渡金額が0の新規注文内容=" + l_newOrderSpecs[i]);
                    }                	
                }
            }
        }
        
        return new ArrayList(l_ht.values());
        
    }
    
    /**
     * (get株式トランザクション<特定口座源泉あり>)<BR>
     *　@株式トランザクション<特定口座源泉あり>（株式トランザクションのリスト）を取得する。<BR>
     * <BR>
     * １）　@注文検索<BR>
     * <BR>
     * 株式トランザクションテーブルより注文検索<BR>
     * <BR>
     * 条件：<BR>
     * 口座ID         ＝   顧客属性.get口座ID()<BR>
     * 補助口座ID       ＝   顧客属性.get補助口座ID（補助口座タイプ）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@補助口座タイプ・・・現物客：補助口座タイプ.現物<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@・・・信用客：補助口座タイプ.現物あるいは補助口座タイプ.信用<BR>
     * 銘柄タイプ        ＝   株式<BR>
     * 税区分          ＝   特定口座源泉徴収あり<BR>
     * 削除フラグ        ＝   FALSE<BR>
     * トランザクションタイプ      IN  現物売取引、買建返済取引、売建返済取引、現渡取引<BR>
     * トランザクション発生日  ＞＝  当日<BR>
     * <BR>
     * ２）　@検索された株式トランザクションのリストを返す<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D3760077
     */
    public List getTodaysSpecialWithHoldEquityFinTransactions(WEB3TPAssetValuation
            l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysSpecialWithHoldEquityFinTransactions(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
            Date l_datT0 = l_calcCondition.getBizDate(0);
            
            List l_lisBindVars = new ArrayList();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_lisBindVars.add(new Long(l_accountInfo.getAccountId()));
            
            //抽出条件　@補助口座IDの取得
            //補助口座タイプ・・・現物客：補助口座タイプ.現物
            //                   ・・・信用客：補助口座タイプ.信用（実際のデータは補助口座タイプ.信用のみ）
            //現物客の場合
            l_strWhereBuf.append("sub_account_id = ? AND ");
            if (!l_accountInfo.isMarginCustomer())
            {
                long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                        EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            //信用客の場合
            else
            {
                long l_marginSubAccountId = l_accountInfo.getSubAccountId(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_marginSubAccountId));
            }
            
            l_strWhereBuf.append("delete_flag = ? AND ");
            l_lisBindVars.add(BooleanEnum.FALSE);
            
            l_strWhereBuf.append("fin_transaction_timestamp >= ? AND ");
            l_lisBindVars.add(l_datT0);
            
            l_strWhereBuf.append("tax_type = ? AND ");
            l_lisBindVars.add(TaxTypeEnum.SPECIAL);
            
            
            l_strWhereBuf.append("fin_transaction_type in (?");
            l_lisBindVars.add(FinTransactionType.EQTYPE_EQUITY_SELL);
            l_strWhereBuf.append(", ?");
            l_lisBindVars.add(FinTransactionType.EQTYPE_CLOSE_MARGIN_LONG);
            l_strWhereBuf.append(", ?");
            l_lisBindVars.add(FinTransactionType.EQTYPE_CLOSE_MARGIN_SHORT);
            l_strWhereBuf.append(", ?");
            l_lisBindVars.add(FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT);
            l_strWhereBuf.append(")");
            
            String l_strWhere = l_strWhereBuf.toString();
            Object[] l_bindVars = l_lisBindVars.toArray();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get外株トランザクション<特定口座源泉あり>)<BR>
     *　@外株トランザクション<特定口座源泉あり>（外株トランザクションのリスト）を取得する。<BR>
     * <BR>
     * １）　@注文検索<BR>
     * <BR>
     * 外株トランザクションテーブルより注文検索<BR>
     * <BR>
     * 条件：<BR>
     * 口座ID         ＝   顧客属性.get口座ID()<BR>
     * 補助顧客ID        IN     顧客属性.get補助口座ID（補助口座タイプ.現物）<BR>
     * 銘柄タイプ        ＝   外株<BR>
     * トランザクションタイプ      IN  外株売取引(★未定義)<BR>
     * 税区分          ＝   特定口座源泉徴収あり<BR>
     * 削除フラグ              ＝   FALSE<BR>
     * トランザクション発生日  ＞＝  当日<BR>
     * <BR>
     * ２）　@検索された外株トランザクションのリストを返す<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D3760096
     */
    public List getTodaysSpecialWithHoldFeqFinTranasctions(WEB3TPAssetValuation
            l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysSpecialWithHoldFeqFinTranasctions(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
            Date l_datT0 = l_calcCondition.getBizDate(0);
            
            List l_lisBindVars = new ArrayList();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_lisBindVars.add(new Long(l_accountInfo.getAccountId()));
            
            //抽出条件　@補助口座IDの取得
            //補助口座タイプ・・・現物客：補助口座タイプ.現物
            //                   ・・・信用客：補助口座タイプ.信用（実際のデータは補助口座タイプ.信用のみ）
            //現物客の場合
            l_strWhereBuf.append("sub_account_id = ? AND ");
            if (!l_accountInfo.isMarginCustomer())
            {
                long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                        EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            //信用客の場合
            else
            {
                long l_marginSubAccountId = l_accountInfo.getSubAccountId(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_marginSubAccountId));
            }
            
            l_strWhereBuf.append("delete_flag = ? AND ");
            l_lisBindVars.add(BooleanEnum.FALSE);
                        
            l_strWhereBuf.append("tax_type = ? AND ");
            l_lisBindVars.add(TaxTypeEnum.SPECIAL);
            
            
            l_strWhereBuf.append("fin_transaction_type = ?");
            l_lisBindVars.add(FinTransactionType.EQTYPE_FEQ_SELL);
            
            String l_strWhere = l_strWhereBuf.toString();
            Object[] l_bindVars = l_lisBindVars.toArray();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(FeqFinTransactionRow.TYPE, l_strWhere, null,
                    l_bindVars);
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get確定取引明細)<BR>
     *　@確定取引明細のリストを取得する。<BR>
     * <BR>
     * １） 確定取引明細テーブルより確定取引明細を検索。<BR>
     * <BR>
     * 条件：<BR>
     * 顧客ID ＝ 顧客属性.get顧客ID() AND <BR>
     * 補助口座ID IN (顧客属性.get補助口座ID（補助口座タイプ(*)）) AND <BR>
     * 営業日[0]＜＝受渡日＜＝営業日[5]<BR>
     * <BR>
     * (*)補助口座タイプ⇒現物客：補助口座タイプ.現物<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@⇒信用客：補助口座タイプ.現物あるいは補助口座タイプ.信用<BR>
     * <BR>
     * ２）　@検索された確定取引明細のリストを返す<BR>
     * <BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D37600B5
     */
    public List getFixedFinTransactions(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getFixedFinTransactions(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
            Date l_datT0 = l_calcCondition.getBizDate(0);
            Date l_datT5 = l_calcCondition.getBizDate(5);
            
            List l_lisBindVars = new ArrayList();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_lisBindVars.add(new Long(l_accountInfo.getAccountId()));
            
            //抽出条件　@補助口座IDの取得
            //補助口座タイプ・・・現物客：補助口座タイプ.現物
            //                   ・・・信用客：補助口座タイプ.現物あるいは補助口座タイプ.信用（実際のデータは補助口座タイプ.信用のみ）
            //現物客の場合
            if (!l_accountInfo.isMarginCustomer())
            {
                l_strWhereBuf.append("sub_account_id = ? AND ");
                long l_subAccountId =
                    l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            //信用客の場合
            else
            {
                l_strWhereBuf.append("sub_account_id in (?, ?) AND ");
                long l_equitySubAccountId =
                    l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_equitySubAccountId));
                long l_marginSubAccountId =
                    l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_marginSubAccountId));
                
            }
            
            l_strWhereBuf.append("(delivery_date >= ? AND ");
            l_strWhereBuf.append("delivery_date <= ?) AND ");
            l_strWhereBuf.append("delete_flag = ?");
            l_lisBindVars.add(l_datT0);
            l_lisBindVars.add(l_datT5);
            l_lisBindVars.add(BooleanEnum.FALSE);
            
            String l_strWhere = l_strWhereBuf.toString();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows =
                l_qp.doFindAllQuery(
                        FixedFinTransactionRow.TYPE,
                        l_strWhere,
                        null,
                        l_lisBindVars.toArray());
            
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
        }
    }
    
    /**
     * (get株式トランザクション)<BR>
     *　@株式トランザクションのリストを取得する。<BR>
     * <BR>
     * １）　@注文検索<BR>
     * <BR>
     * 株式トランザクションテーブルより注文検索<BR>
     * <BR>
     * 条件：<BR>
     * 注文単位ID           ＝   注文単位ID<BR>
     * 削除フラグ              ＝   FALSE<BR>
     * <BR>
     * ２）　@検索された株式トランザクションを返す<BR>
     * <BR>
     * @@param orderUnitId - (注文単位ID)
     * @@return List
     * @@roseuid 4104D37600C5
     */
    public List getEqtypeFinTransactions(EqtypeOrderUnitRow l_row)
    {
        final String STR_METHOD_NAME =
            "getEqtypeFinTransactions(EqtypeOrderUnitRow l_row)";
        
        try
        {
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("order_unit_id = ? AND ");
            l_strWhereBuf.append("delete_flag = ?");
            
            Object[] l_bindVars =
            {
                    new Long(l_row.getAccountId()),
                    new Long(l_row.getSubAccountId()),
                    new Long(l_row.getOrderUnitId()),
                    BooleanEnum.FALSE
            };
            
            String l_strWhere = l_strWhereBuf.toString();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, null,
                    l_bindVars);
            return l_lisRows;
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (getオプショントランザクション)<BR>
     *　@オプショントランザクションのリストを取得する。<BR>
     *<BR>
     * １）　@注文検索<BR>
     * <BR>
     * （先物）オプショントランザクションテーブルより注文検索<BR>
     * <BR>
     * 条件：<BR>
     * 注文単位ID           ＝   注文単位ID<BR>
     * 削除フラグ              ＝   FALSE<BR>
     * <BR>
     * ２）　@検索されたオプショントランザクションを返す<BR>
     * @@param orderUnitId - (注文単位ID)
     * @@return Lits
     * @@roseuid 4104D37600E4
     */
    public List getIfoFinTransactions(IfoOrderUnitRow l_row)
    {
        final String STR_METHOD_NAME =
            "getIfoFinTransactions(IfoOrderUnitRow l_row)";
        
        try
        {
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("order_unit_id = ? AND ");
            l_strWhereBuf.append("delete_flag = ?");
            
            Object[] l_bindVars =
            {
                    new Long(l_row.getAccountId()),
                    new Long(l_row.getSubAccountId()),
                    new Long(l_row.getOrderUnitId()),
                    BooleanEnum.FALSE
            };
            
            String l_strWhere = l_strWhereBuf.toString();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(IfoFinTransactionRow.TYPE, l_strWhere, null,
                    l_bindVars);
            return l_lisRows;
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get外株トランザクション)<BR>
     *　@外株トランザクションのリストを取得する。<BR>
     *<BR>
     * １）　@注文検索<BR>
     * <BR>
     * 外株トランザクションテーブルより注文検索<BR>
     * <BR>
     * 条件：<BR>
     * 注文単位ID           ＝   注文単位ID<BR>
     * 削除フラグ              ＝   FALSE<BR>
     * <BR>
     * ２）　@検索された外株トランザクションを返す<BR>
     * @@param orderUnitId - (注文単位ID)
     * @@return List
     * @@roseuid 4104D37600F4
     */
    public List getFeqFinTransactions(FeqOrderUnitRow l_row)
    {
        final String STR_METHOD_NAME =
            "getFeqFinTransactions(FeqOrderUnitRow l_row)";
        
        try
        {
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("order_unit_id = ? AND ");
            l_strWhereBuf.append("delete_flag = ?");
            
            Object[] l_bindVars =
            {
                    new Long(l_row.getAccountId()),
                    new Long(l_row.getSubAccountId()),
                    new Long(l_row.getOrderUnitId()),
                    BooleanEnum.FALSE
            };
            
            String l_strWhere = l_strWhereBuf.toString();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(FeqFinTransactionRow.TYPE, l_strWhere, null,
                    l_bindVars);
            return l_lisRows;
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get即日入金銘柄)<BR>
     * 即日入金銘柄を取得する。<BR>
     *  <BR>
     * １）　@即日入金銘柄検索<BR>
     * <BR>
     * [a. 引数.トランザクション発生日が当日の場合]<BR>
     *  株式取引銘柄テーブルより検索<BR>
     * [a. 上記以外の場合]<BR>
     *  株式取引銘柄Updqテーブルより検索<BR>
     * <BR>
     * 条件：<BR>
     * 銘柄ID   ＝   引数.銘柄ID<BR>
     * 取引執行日   ＝   引数.トランザクション発生日<BR>
     * 即日入金銘柄フラグ＝正<BR>
     * <BR>
     * ２）検索結果のリストを返却する。<BR>
     *<BR>
     * @@return List
     * @@roseuid 4104D3760123
     */
    public List getTodayDepFundProducts(long l_lngProductId,
            Date l_transactionDate,
            WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodayDepRegProduct(long l_productId, Date l_transactionDate)";
        
        try
        {
            Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(
                    WEB3TPSpecifiedPointDef.T_0);
            String l_strYYYYMMDD = "yyyyMMdd";
            String l_strTransactionDate = WEB3DateUtility.formatDate(l_transactionDate,
                    l_strYYYYMMDD);
            
            final String l_strWhere =
                "product_id = ? AND valid_until_biz_date = ? AND today_dep_fund_reg = ?";
            
            final Object[] l_bindVars =
            {
                    new Long(l_lngProductId),
                    l_strTransactionDate,
                    BooleanEnum.TRUE
            };
            
            //当日だったらEqtypeTradedProductテーブルから検索、
            //当日以外はEqtypeTradedProductUqdqテーブルから検索
            final RowType l_rowType = WEB3DateUtility.compareToDay(l_datT0,
                    l_transactionDate) == 0 ?
                            EqtypeTradedProductRow.TYPE : EqtypeTradedProductUpdqRow.TYPE;
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            return l_qp.doFindAllQuery(l_rowType, l_strWhere, null, l_bindVars);
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (getIPO銘柄)<BR>
     * IPO銘柄を取得する。<BR>
     * <BR>
     * １）　@IPO銘柄検索<BR>
     * <BR>
     * IPO銘柄テーブルより銘柄検索<BR>
     * <BR>
     * 条件：<BR>
     * 銘柄ID         ＝   銘柄ID<BR>
     * <BR>
     * ２）　@検索されたIPO銘柄を返す<BR>
     *
     * @@param productId - (銘柄ID)
     * @@return IpoProductRow
     * @@roseuid 4104D3760132
     */
    public IpoProductRow getIPOProduct(long l_productId)
    {
        final String STR_METHOD_NAME =
            "getIPOProduct(long l_productId)";
        
        try
        {
            return IpoProductDao.findRowByIpoProductId(l_productId);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get税率)<BR>
     * 税率を取得する。<BR>
     * <BR>
     * 税率テーブルより<BR>
     * ・税種類＝引数.税種類<BR>
     * ・適用開始日＝＜引数.適用日<BR>
     * ・適用終了日＞＝引数.適用日<BR>
     * で検索した結果レコード.税率を返す。<BR>
     * <BR>
     * @@param taxType - (税種類)
     * @@param applicationDate - (適用日)
     * @@return double
     * @@roseuid 4104D3760152
     */
    public double getTaxRate(WEB3TPAssetValuation l_assetValuation, String l_strTaxType,
            Date l_datApplicationDate)
    {
        final String STR_METHOD_NAME =
            "getTaxRate(String taxType, Date applicationDate)";
        
        try
        {
            final String l_strWhere =
                "institution_code=? and tax_type=? and (appli_start_date<=? and appli_end_date>=?)";
            //          修正 齋藤 2005/02/01
            //                          "institution_code = ? AND tax_type = ? AND appli_start_date <= ? AND appli_end_date >= ?";
            
            Object[] l_bindVars =
            {
                    l_assetValuation.getAccountInfo().getInstitutionCode(),
                    l_strTaxType,
                    l_datApplicationDate,
                    l_datApplicationDate
            };
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(TaxRateTableRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            int l_intSize = l_lisRows.size();
            
            if (l_intSize == 0)
            {
                return 0.0d;
            }
            
            TaxRateTableRow l_row = (TaxRateTableRow) l_lisRows.get(0);
            //修正劉
            //            BigDecimal l_oneHandred = new BigDecimal(WEB3GentradeNumberConstDef.HYAKU);
            //            int l_intScale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);
            //
            //            BigDecimal l_bdTaxRate =
            //                new BigDecimal(l_row.getTaxRate()).divide(l_oneHandred, l_intScale,
            //                BigDecimal.ROUND_HALF_EVEN);
            //            return l_bdTaxRate.doubleValue();
            return l_row.getTaxRate()/100;
            //end
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get日歩調整額)<BR>
     * 日歩調整額を取得する。<BR>
     *<BR>
     * １）　@部店テーブルより以下の条件で検索。<BR>
     * <BR>
     * 条件：<BR>
     * 部店ID＝顧客属性.部店ID<BR>
     * <BR>
     * ２）　@検索結果.日歩調整額を返す。<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return double
     * @@roseuid 4104D3760171
     */
    public double getDayInterestAdjustment(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getDayInterestAdjustment(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            BranchRow l_row = BranchDao.findRowByBranchId(l_assetValuation.getAccountInfo().
                    getBranchId());
            
            return (l_row == null) ? 0.0d : l_row.getDailyInterestAdjustAmount();
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get手数料一口処理調整額)<BR>
     * 手数料一口処理調整額を取得する。<BR>
     *<BR>
     * １）　@会社部店商品テーブルより以下の条件で検索。<BR>
     * <BR>
     * 条件：<BR>
     * 部店ID＝顧客属性.部店ID<BR>
     * 手数料商品コード＝手数料商品コード<BR>
     * <BR>
     * ２）　@検索結果.手数料一口調整額を返す。<BR>
     * @@param commissionProductCode - (手数料商品コード)
     * @@param l_assetValuation - (資産評価)
     * @@return double
     * @@roseuid 4104D3760181
     */
    public double getConsolidatedCommissionAdjustment(String l_strCommProductCode,
            WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getConsolidatedCommissionAdjustment(String l_commProductCode, WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            InstBranchProductRow l_row =
                InstBranchProductDao.findRowByBranchIdCommissionProductCode(
                        l_assetValuation.getAccountInfo().getBranchId(), l_strCommProductCode);
            
            return (l_row == null) ? 0.0d : l_row.getCommissionLumpAdjustAmount();
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get割増拘束率) <BR>
     * 割増拘束率を取得する。 <BR>
     * <BR>
     * １） 会社部店商品テーブルより以下の条件で検索。 <BR>
     * <BR>
     * 条件： <BR>
     * 部店ID＝顧客属性.部店ID <BR>
     * 手数料商品コード＝手数料商品コード <BR>
     * <BR>
     * ２） 検索結果.割増拘束率を返す。 <BR>
     * 
     * @@param commissionProductCode - (手数料商品コード)
     * @@param l_assetValuation - (資産評価)
     * @@return double
     */
    public double getPremiumRestraintRate(String l_strCommProductCode,
            WEB3TPAssetValuation l_assetValuation)
    {
        //Y00125：割増拘束率対応
        final String STR_METHOD_NAME =
            "getPremiumRestraintRate(String l_commProductCode, WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            InstBranchProductRow l_row =
                InstBranchProductDao.findRowByBranchIdCommissionProductCode(
                        l_assetValuation.getAccountInfo().getBranchId(), l_strCommProductCode);
            
            return (l_row == null) ? 1.0d : l_row.getPremiumRestraintRate();
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get補助口座残高マスター)<BR>
     * 補助口座残高マスターのリストを取得する。<BR>
     *<BR>
     * １）　@補助口座残高マスター情報（仮）より以下の条件で検索。<BR>
     * <BR>
     * 条件：<BR>
     * 口座ID＝顧客属性.口座ID<BR>
     * 補助口座ID＝顧客属性.補助口座ID<BR>
     * <BR>
     * ２）　@検索結果を返す。<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     * @@roseuid 4104D37601A0
     */
    public List getTpCashBalances(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTpCashBalances(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            long l_lngAccId = l_accountInfo.getAccountId();
            long l_lngEqSubAccId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                    EQUITY_SUB_ACCOUNT);
            
            List l_lisBindVars = new ArrayList();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_lisBindVars.add(new Long(l_lngAccId));
            l_strWhereBuf.append("sub_account_id in (?");
            l_lisBindVars.add(new Long(l_lngEqSubAccId));
            
            if (l_accountInfo.isMarginCustomer())
            {
                long l_lngMgSubAccId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                        EQUITY_MARGIN_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_lngMgSubAccId));
                l_strWhereBuf.append(", ?");
            }
            l_strWhereBuf.append(")");
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            return l_qp.doFindAllQuery(TpCashBalanceRow.TYPE, l_strWhereBuf.toString(), null,
                    l_lisBindVars.toArray());
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * @@return webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager
     * @@roseuid 4104D3A700AF
     */
    public static WEB3TPPersistentDataManager getInstance()
    {
        
        if (THIS_INSTANCE == null)
        {
            THIS_INSTANCE = new WEB3TPPersistentDataManager();
        }
        return THIS_INSTANCE;
        
    }
    
    /**
     * (save余力更新内容<現物顧客>)
     *
     * １）Primary_key(calc_result_equity_id)を採番する<BR>
     * ２）現物顧客の余力更新内容をDBへ保存する<BR>
     *
     * @@param l_list　@- 現物顧客の余力更新内容<BR>
     * @@roseuid 4110854102B3
     */
    public void saveTradingpowerUpdResultEquity(List l_list)
    {
        
        final String STR_METHOD_NAME =
            "saveTradingpowerUpdResultEquity(List)";
        
        TpCalcResultEquityParams l_params = null;
        TpCalcResultEquityDetailParams l_paramsDetail = null;
        
        Iterator l_iterator = l_list.iterator();
        while (l_iterator.hasNext())
        {
            Object l_obj = l_iterator.next();
            if(l_obj instanceof TpCalcResultEquityParams)
            {
                l_params = (TpCalcResultEquityParams)l_obj;
            }
            else if(l_obj instanceof TpCalcResultEquityDetailParams)
            {
                l_paramsDetail = (TpCalcResultEquityDetailParams)l_obj;
            }
        }
        
        try
        {
            if(l_params != null && l_paramsDetail != null)
            {
                long l_lngCalcResultEquityId = TpCalcResultEquityDao.
                newPkValue();
                l_params.setCalcResultEquityId(l_lngCalcResultEquityId);
                l_paramsDetail.setCalcResultEquityId(l_lngCalcResultEquityId);
                BatchedQuery[] l_batchedQueries = new BatchedQuery[]
                                                                   {
                        BatchedQuery.createInsertQuery(l_params),
                        BatchedQuery.createInsertQuery(l_paramsDetail)
                                                                   };
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                l_qp.doQueries(l_batchedQueries);
            }
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            // start 20090622障害発生の障害対応としてログ出力を追加
            log.error(l_list.toString(),de);
            // end
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (save余力更新内容<信用顧客>)
     *
     * １）Primary_key(calc_result_margin_id)を採番する<BR>
     * ２）信用顧客の余力更新内容をDBへ保存する<BR>
     *
     * @@param l_list　@- 信用顧客の余力更新内容<BR>
     * @@roseuid 41108593020E
     */
    public void saveTradingpowerUpdResultMargin(List l_list)
    {
        final String STR_METHOD_NAME =
            "saveTradingpowerUpdResultMargin(List)";
        
        TpCalcResultMarginParams l_params = null;
        TpCalcResultMarginDetailParams l_paramsDetail = null;
        
        Iterator l_iterator = l_list.iterator();
        while (l_iterator.hasNext())
        {
            Object l_obj = l_iterator.next();
            if(l_obj instanceof TpCalcResultMarginParams)
            {
                l_params = (TpCalcResultMarginParams)l_obj;
            }
            else if(l_obj instanceof TpCalcResultMarginDetailParams)
            {
                l_paramsDetail = (TpCalcResultMarginDetailParams)l_obj;
            }
        }
        
        try
        {
            if(l_params != null && l_paramsDetail != null)
            {
                long l_lngCalcResultMarginId = TpCalcResultMarginDao.
                newPkValue();
                l_params.setCalcResultMarginId(l_lngCalcResultMarginId);
                l_paramsDetail.setCalcResultMarginId(l_lngCalcResultMarginId);
                BatchedQuery[] l_batchedQueries = new BatchedQuery[]
                                                                   {
                        BatchedQuery.createInsertQuery(l_params),
                        BatchedQuery.createInsertQuery(l_paramsDetail)
                                                                   };
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                l_qp.doQueries(l_batchedQueries);
            }
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            // start 20090622障害発生の障害対応としてログ出力を追加
            log.error(l_list.toString(),de);
            // end
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (顧客属性取得())
     * 顧客属性を取得する。<BR>
     * @@param l_lngAccountId - 口座ID
     * @@param l_blnMarginFlag- 信用顧客フラグ
     *
     * @@param l_blnMarginFlag
     * @@return WEB3TPAccountInfo
     * @@roseuid 4110900102B1
     */
    public WEB3TPAccountInfo getAccountInfo(long l_lngAccountId,
            boolean l_blnMarginFlag)
    {
        
        final String STR_METHOD_NAME =
            "getAccountInfo(long l_lngAccountId, boolean l_blnMarginFlag)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(l_lngAccountId);
            l_accountInfo.setMarginCustFlag(l_blnMarginFlag);
            
            MainAccountRow l_mainAccount = MainAccountDao.findRowByAccountId(
                    l_lngAccountId);
            if (l_mainAccount == null)
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            l_accountInfo.setBranchId(l_mainAccount.getBranchId());
            l_accountInfo.setBranchCode(l_mainAccount.getBranchCode());
            l_accountInfo.setInstitutionId(l_mainAccount.getInstitutionId());
            l_accountInfo.setInstitutionCode(l_mainAccount.getInstitutionCode());
            
            //税区分をロードする。
            Hashtable l_taxTypes = new Hashtable();
            l_taxTypes.put(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE, l_mainAccount.getTaxType());
            l_taxTypes.put(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE_NEXT, l_mainAccount.getTaxTypeNext());
            
            if(l_blnMarginFlag)
            {
                l_taxTypes.put(WEB3TPAccountTaxTypeKeyDef.MARGIN_TAX_TYPE, l_mainAccount.getMarginTaxType());
                l_taxTypes.put(WEB3TPAccountTaxTypeKeyDef.MARGIN_TAX_TYPE_NEXT, l_mainAccount.getMarginTaxTypeNext());            	
            }
            l_accountInfo.setTaxTypes(l_taxTypes);
            
            List l_rows = SubAccountDao.findRowsByAccountId(l_lngAccountId);
            Hashtable l_subAccountIds = new Hashtable();
            for (int i = 0; i < l_rows.size(); i++)
            {
                SubAccountRow l_subAccountRow = (SubAccountRow) l_rows.get(i);
                l_subAccountIds.put(new Long(l_subAccountRow.getSubAccountId()),
                        l_subAccountRow.getSubAccountType());
            }
            l_accountInfo.setSubAccountIds(l_subAccountIds);
            
            
            return l_accountInfo;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get未約定分返済決済損益)<BR>
     * <BR>
     * 未出来分を含む、当日以降信用返済注文（株式注文単位のリスト）を取得する。<BR>
     * <BR>
     * １）　@株式注文単位テーブルより注文検索する<BR>
     * <BR>
     * 条件：<BR>
     * 　@口座ID ＝ 顧客属性.get口座ID()<BR>
     * 　@補助口座ID ＝ 顧客属性.get補助口座ID（補助口座タイプ）<BR>
     * 　@銘柄タイプ ＝ 株<BR>
     * 　@注文カテゴリ ＝ 信用返済<BR>
     * 　@注文有効状態 ＝ オープン OR 約定数量 ＞ 0<BR>
     * 　@発注日 ＞＝ 当日<BR>
     * <BR>
     * （※）補助口座タイプ・・・補助口座タイプ.現物あるいは補助口座タイプ.信用（実際のデータは補助口座タイプ.信用のみ）<BR>
     * <BR>
     * ２）　@現注文内容を株式注文単位Paramsに変換しリストに追加する<BR>
     * <BR>
     * ３）　@株式注文のリストを返す<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     */
    public List getTodaysCloseMarginOrdersAfterRepay(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysCloseMarginOrdersAfterRepay(WEB3TPAssetValuation l_assetValuation)";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        
        //信用客でない場合要素0のリストを返す
        if (!l_accountInfo.isMarginCustomer())
        {
            log.exiting(STR_METHOD_NAME);
            return Collections.EMPTY_LIST;
        }
        
        long l_accountId = l_accountInfo.getAccountId();
        long l_equitySubAccountId =
            l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        long l_marginSubAccountId =
            l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        
        List l_lisRows = new ArrayList();
        
        //発注日が当日以降の注文List(getTodaysEquityOrdersで取得したList)
        List l_lisTempRows=l_assetValuation.getTodaysEquityOrders();
        for(int i = 0; i < l_lisTempRows.size(); i++)
        {
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow) l_lisTempRows.get(i);
            
            //サブアカウントID
            if(l_marginSubAccountId == l_row.getSubAccountId())
            {
                //銘柄タイプ ＝ 株
                if(ProductTypeEnum.EQUITY.equals(l_row.getProductType()))
                {
                    //カテゴリ ＝ 信用返済
                    if(OrderCategEnum.CLOSE_MARGIN.equals(l_row.getOrderCateg()))
                    {
                        //注文有効状態 ＝ オープン OR 約定数量 ＞ 0
                        if(OrderOpenStatusEnum.OPEN.equals(l_row.getOrderOpenStatus())
                                || (!l_row.getExecutedQuantityIsNull() && l_row.getExecutedQuantity() > 0))
                        {
                            l_lisRows.add(l_row);
                        }
                    }
                }
            }
        }
        
        /*
         * 現注文内容を株式注文単位Paramsに変換しリストに追加する
         */
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        
        if (l_newOrderSpecs != null)
        {
            TreeMap l_ht = new TreeMap();
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                EqtypeOrderUnitParams l_params = (EqtypeOrderUnitParams)l_lisRows.get(i);
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
            
            for (int i = 0; i < l_newOrderSpecs.length; i++)
            {
                if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_newOrderSpecs[i].getOrderType())
                        || OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_newOrderSpecs[i].getOrderType()))
                {
                    EqtypeOrderUnitParams l_params = new EqtypeOrderUnitParams();
                    l_params.setAccountId(l_accountId);
                    l_params.setSubAccountId(l_marginSubAccountId);
                    l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                    l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                    l_params.setProductType(l_newOrderSpecs[i].getProductType());
                    l_params.setProductId(l_newOrderSpecs[i].getProductId());
                    l_params.setOrderCateg(l_newOrderSpecs[i].getOrderCategory());
                    l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                    l_params.setEstimatedPrice(l_newOrderSpecs[i].getEstimatedPrice());
                    l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                    l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                    l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                    l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                    l_params.setBizDate(
                            WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].getOrderBizDate(),
                            "yyyyMMdd"));
                    
                    EqtypeOrderUnitParams l_oldParams =
                        (EqtypeOrderUnitParams)l_ht.get(new Long(l_params.getOrderUnitId()));
                    
                    if (l_oldParams != null)
                    {
                        l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                        l_ht.put(new Long(l_oldParams.getOrderUnitId()), l_params);
                    }
                    else
                    {
                        l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                    }
                }
            }
            
            log.exiting(STR_METHOD_NAME);
            return new ArrayList(l_ht.values());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisRows;
    }
    
    /**
     * (get未約定返済変動情報)<BR>
     * 未約定変動情報を取得する。<BR>
     * <BR>
     * １）未約定変動情報を取得する。<BR>
     * 　@　@株式注文単位テーブルを以下の条件で検索する。<BR>
     * 　@　@－検索条件<BR>
     * 　@　@　@・株式注文単位テーブル.口座ID ＝ 引数.顧客属性.口座ID<BR>
     * 　@　@　@・株式注文単位テーブル.補助口座ID ＝ 引数.顧客属性.補助口座ID<BR>
     * 　@　@　@・株式注文単位テーブル.カテゴリ ＝ 返済注文
     * 　@　@　@・株式注文単位テーブル.発注日 ＞＝ 引数.余力計算条件.営業日(T+0)<BR>
     * 　@　@　@・株式注文単位テーブル.注文状態 not in ( 発注済（取消注文）)<BR>
     * 　@　@　@・株式注文単位テーブル.注文有効状態　@＝オープン
     * <BR>
     * ２）検索結果を返す。<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     */
    public List getUnExecutedOrdersAfterRepay(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getUnExecutedOrdersAfterRepay(WEB3TPAssetValuation l_assetValuation)";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
        List l_lisRows = new ArrayList();
        
        //信用客でない場合要素0のリストを返す
        if (!l_accountInfo.isMarginCustomer())
        {
            log.exiting(STR_METHOD_NAME);
            return Collections.EMPTY_LIST;
        }
        
        long l_marginSubAccountId = l_accountInfo.getSubAccountId(true);
        
        //発注日が当日以降の注文List(getTodaysEquityOrdersで取得したList)
        List l_lisTempRows=l_assetValuation.getTodaysEquityOrders();
        for(int i = 0; i < l_lisTempRows.size(); i++)
        {
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow) l_lisTempRows.get(i);
            
            //サブアカウントID
            if(l_marginSubAccountId == l_row.getSubAccountId())
            {
                //カテゴリ ＝ 信用返済
                if(OrderCategEnum.CLOSE_MARGIN.equals(l_row.getOrderCateg()))
                {
                    //注文有効状態 ＝ オープン
                    if(OrderOpenStatusEnum.OPEN.equals(l_row.getOrderOpenStatus()))
                    {
                        //注文状態 not in ( 発注済（取消注文）)
                        if(! OrderStatusEnum.CANCELLED.equals(l_row.getOrderStatus()))
                        {
                            l_lisRows.add(l_row);
                        }
                    }
                }
            }
        }
        
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        if (l_newOrderSpecs == null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_lisRows;
        }
        
        //現注文内容をListに反映
        TreeMap l_ht = new TreeMap();
        int l_lisRowsSize = l_lisRows.size();
        for (int i = 0; i < l_lisRowsSize; i++)
        {
            EqtypeOrderUnitParams l_params = (EqtypeOrderUnitParams)l_lisRows.get(i);
            l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
        }
        
        int l_intNewSize = l_newOrderSpecs.length;
        for (int i = 0; i < l_intNewSize; i++)
        {
            //カテゴリが返済の場合
            OrderCategEnum l_newSpecOrderCateg = l_newOrderSpecs[i].getOrderCategory();
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_newSpecOrderCateg))
            {
                //現注文内容を元にオブジェクト生成
                EqtypeOrderUnitParams l_eqOrderUnit = new EqtypeOrderUnitParams();
                l_eqOrderUnit.setOrderId(l_newOrderSpecs[i].getOrderId());
                l_eqOrderUnit.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                l_eqOrderUnit.setOrderCateg(l_newOrderSpecs[i].getOrderCategory());
                l_eqOrderUnit.setOrderType(l_newOrderSpecs[i].getOrderType());
                l_eqOrderUnit.setEstimatedPrice(l_newOrderSpecs[i].getEstimatedPrice());
                l_eqOrderUnit.setPrice(l_newOrderSpecs[i].getPrice());
                l_eqOrderUnit.setQuantity(l_newOrderSpecs[i].getQuantity());
                l_eqOrderUnit.setBizDate(
                        GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
                                l_newOrderSpecs[i].getOrderBizDate()));
                l_eqOrderUnit.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                l_eqOrderUnit.setMarketId(l_newOrderSpecs[i].getMarketId());
                l_eqOrderUnit.setProductId(l_newOrderSpecs[i].getProductId());
                l_eqOrderUnit.setTaxType(l_newOrderSpecs[i].getTaxType());
                
                EqtypeOrderUnitParams l_oldParams =
                    (EqtypeOrderUnitParams)l_ht.get(new Long(l_eqOrderUnit.getOrderUnitId()));
                if (l_oldParams != null)
                {
                    l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                    l_ht.put(new Long(l_eqOrderUnit.getOrderUnitId()), l_eqOrderUnit);
                }
                else
                {
                    l_ht.put(new Long(l_eqOrderUnit.getOrderUnitId()), l_eqOrderUnit);
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return new ArrayList(l_ht.values());
    }
    
    /**
     * (get当日以降現物株注文)<BR>
     *　@当日以降現物株注文（株式注文単位のリスト）を取得する。<BR>
     *<BR>
     * １）　@株式注文単位テーブルより当日注文検索<BR>
     * 条件：<BR>
     * 口座ID         ＝   顧客属性.get口座ID()<BR>
     * 発注日          ＞＝  当日<BR>
     * ２）　@検索された株式注文のリストを返す<BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     */
    public List getTodaysEquityOrders(WEB3TPAccountInfo l_accountInfo,
            WEB3TPCalcCondition l_calcCondition)
    {
        final String STR_METHOD_NAME =
            "getTodaysEquityOrders(WEB3TPAssetValuation l_assetValuation)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            long l_accountId = l_accountInfo.getAccountId();
            
            StringBuffer l_strWhereBuf = new StringBuffer(" ");
            l_strWhereBuf.append("account_id=? and ");
            l_strWhereBuf.append("TO_DATE(biz_Date,'YYYYMMDD') >=?");
            String l_strWhere = l_strWhereBuf.toString();
            Date l_datT0 = l_calcCondition.getBizDate(0);
            Object[] l_bindVars =
            {
                    new Long(l_accountId),
                    l_datT0
            };
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(EqtypeOrderUnitRow.TYPE, l_strWhere, null,
                    l_bindVars);
            log.exiting(STR_METHOD_NAME);
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get債券注文<取引代金>)<BR>
     * <BR>
     * 取引代金においてロード対象となる債券注文を、<BR>
     * 債券注文単位テーブルより検索し、行オブジェクトのリストを返却する。<BR> 
     * <BR>
     * ※シーケンス図「(余力データソースアクセス管理)get債券注文<取引代金>」参照 <BR>
     * <BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     */
    public List getBondOrdersAmount(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getBondOrdersAmount(WEB3TPAssetValuation l_assetValuation)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. get顧客属性( )
        //顧客属性オブジェクトを取得する。
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        
        //1.2.get余力計算条件( )
        //余力計算条件オブジェクトを取得する。
        WEB3TPCalcCondition l_tpCalcCondition = l_assetValuation.getCalcCondition();
        
        //1.3.get営業日(int)
        //当日営業日を取得する。 
        //[引数] 
        //int = 0
        Date l_datBizDate = l_tpCalcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();
        //1.4.(*)債券注文単位テーブルを検索する。
        //    検索条件：
        //   　@口座ID = "顧客属性".get口座ID()
        l_sbWhere.append("account_id = ? ");
        long l_lngAccountId = l_accountInfo.getAccountId();
        l_lisBindVars.add(new Long(l_lngAccountId));
        
        //   　@[a.現物顧客の場合]
        //   　@("顧客属性".is信用顧客() == false)
        //   　@　@補助口座ID = "顧客属性".get補助口座ID(:SubAccountTypeEnum = '1:株式取引口座（預り金）')
        long l_lngSubAccountId = 0;
        if (!l_accountInfo.isMarginCustomer())
        {
            l_sbWhere.append("and sub_account_id = ? ");
            l_lngSubAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_lisBindVars.add(new Long(l_lngSubAccountId));
        }
        
        //   　@[a.信用顧客の場合]
        //   　@("顧客属性".is信用顧客() == true)
        //   　@　@補助口座ID IN 
        //   　@　@　@(　@"顧客属性".get補助口座ID(:SubAccountTypeEnum = 1：株式取引口座（預り金）),  
        //   　@　@　@　@　@"顧客属性".get補助口座ID(:SubAccountTypeEnum = 2：株式信用取引口座（保証金）) )
        else if (l_accountInfo.isMarginCustomer())
        {
            l_sbWhere.append("and sub_account_id in (?, ?) ");
            long l_lngEquitySubAccountId =
                l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_lisBindVars.add(new Long(l_lngEquitySubAccountId));
            long l_lngMarginSubAccountId =
                l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_lisBindVars.add(new Long(l_lngMarginSubAccountId));
        }
            
        //   　@注文種別 IN (401：債券買い注文, 注文種別 = 402：債券売い注文)
        l_sbWhere.append("and order_type in (?, ?) ");
        l_lisBindVars.add(OrderTypeEnum.BOND_BUY);
        l_lisBindVars.add(OrderTypeEnum.BOND_SELL);
        
        //   　@注文約定区分 IN (0：未約定, 1：約定済))
        l_sbWhere.append("and order_exec_status in (?, ?) ");
        l_lisBindVars.add(WEB3BondOrderExecStatusDef.UNEXECUTED);
        l_lisBindVars.add(WEB3BondOrderExecStatusDef.EXECUTED);
        
        //   　@約定分host反映区分 ≠ 2：客勘反映済
        l_sbWhere.append("and exec_host_reflect_div != ? ");
        l_lisBindVars.add(WEB3HostReflectDivDef.CUSTOMER_CALCULATION_REFLECTED);
        
        //   　@受渡日 >= "T+0"
        l_sbWhere.append("and delivery_date >= ? ");
        l_lisBindVars.add(l_datBizDate);
        
        //   　@決済区分 = 1：円貨
        l_sbWhere.append("and settlement_div = ? ");
        l_lisBindVars.add(WEB3BondOrderSettleDivDef.YEN_CURRENCY);
        
        String l_strWhere = l_sbWhere.toString();
        List l_lisBondOrderUnitRows = null;
        try
        {
            QueryProcessor l_qProcessor = Processors.getDefaultProcessor();
            l_lisBondOrderUnitRows =
                l_qProcessor.doFindAllQuery(
                    BondOrderUnitRow.TYPE,
                    l_strWhere,
                    null,
                    l_lisBindVars.toArray());
        }
        catch (DataException l_dex)
        {
            log.error("DBへのアクセスに失敗しました。", l_dex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        //1.5.(*)LOOP処理：債券注文単位テーブルの検索結果の行数回
        TreeMap l_treeMap = new TreeMap();
        BondOrderUnitRow l_bondOrderUnitRow = null;
        for (Iterator l_iter = l_lisBondOrderUnitRows.iterator(); l_iter.hasNext(); )
        {
            //1.5.1. put(Object, Object)
            //債券注文単位マップに、行オブジェクトを追加する。 
            //[引数] 
            //Object = new Long(:long = 行オブジェクト.注文単位ID) 
            //Object = 行オブジェクト 
            l_bondOrderUnitRow = (BondOrderUnitRow)l_iter.next();
            l_treeMap.put(new Long(l_bondOrderUnitRow.getOrderUnitId()), l_bondOrderUnitRow);
        }
        
        //1.6.get現注文内容( )
        //現注文内容オブジェクトの配列を取得する。 
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        
        //1.7.(*)分岐フロー
        //現注文内容が存在する場合
        //(get現注文内容() != null)
        if (l_newOrderSpecs != null)
        {
            //1.7.1.1.(*)LOOP処理：get現注文内容()の戻り値の配列要素数回
            for (int i = 0 ; i < l_newOrderSpecs.length; i ++)
            {
                //1.7.1.1.get注文タイプ( )
                //注文タイプ（=注文種別）を取得する。
                OrderTypeEnum l_orderTypeEnum = l_newOrderSpecs[i].getOrderType();
                
                //1.7.1.2.(*)分岐フロー
                //債券注文である場合
                //(get注文タイプ()の戻り値 IN (401：債券買い注文　@402：債券売り注文))
                if (OrderTypeEnum.BOND_BUY.equals(l_orderTypeEnum) 
                    || OrderTypeEnum.BOND_SELL.equals(l_orderTypeEnum))
                {
                    //1.7.1.2.1.get注文単位ID( )
                    //注文単位IDを取得する。
                    long l_lngOrderUnitId = l_newOrderSpecs[i].getOrderUnitId();
                    
                    //1.7.1.2.2. get(Object)
                    //新規注文内容と同一注文単位ＩＤである行オブジェクトを取得する。
                    //[引数]
                    //Object = new Long(:long = get注文単位ID()の戻り値)  
                    Long l_orderUnitId = new Long(l_lngOrderUnitId);
                    
                    BondOrderUnitRow l_oldBondOrderUnitRow = 
                        (BondOrderUnitRow)l_treeMap.get(l_orderUnitId);
                    
                    BondOrderUnitParams l_bondOrderUnitParams = null;
                    
                    //1.7.1.2.3.(*)分岐フロー
                    //訂正注文 または 取消注文である場合
                    //("債券注文単位マップ".get()の戻り値 != null)
                    if (l_oldBondOrderUnitRow != null)
                    {
                        //1.7.1.2.3.1.BondOrderUnitParams(BondOrderUnitRow)
                        //"債券注文単位Paramas"を生成する。
                        //[引数]
                        //BondOrderUnitRow = "債券注文単位マップ".get()の戻り値
                        //旧ParamsからコピーParams作成
                        l_bondOrderUnitParams = new BondOrderUnitParams(
                            l_oldBondOrderUnitRow);
                        
                        //1.7.1.2.3.2. (*)"債券注文単位Params"に、値をセットする。
                        //[設定値]
                        //　@"債券注文単位Params".口座ID = "顧客属性".get口座ID()
                        l_bondOrderUnitParams.setAccountId(l_accountInfo.getAccountId());
                        
                        //　@"債券注文単位Params".補助口座ID = 現注文内容.get補助口座ID()
                        l_bondOrderUnitParams.setSubAccountId(
                            l_newOrderSpecs[i].getSubAccountId());
                        
                        //　@"債券注文単位Params".注文ID = 現注文内容.get注文ID()
                        l_bondOrderUnitParams.setOrderId(
                            l_newOrderSpecs[i].getOrderId());
                        
                        //　@"債券注文単位Params".注文単位ID = 現注文内容.get注文単位ID()
                        l_bondOrderUnitParams.setOrderUnitId(
                            l_newOrderSpecs[i].getOrderUnitId());
                        
                        //　@"債券注文単位Params".銘柄ID = 現注文内容.get銘柄ID()
                        l_bondOrderUnitParams.setProductId(
                            l_newOrderSpecs[i].getProductId());
                        
                        //　@"債券注文単位Params".銘柄タイプ = 現注文内容.get銘柄タイプ()
                        l_bondOrderUnitParams.setProductType(
                            l_newOrderSpecs[i].getProductType());
                        
                        //　@"債券注文単位Params".市場ID = 現注文内容.get市場ID()
                        l_bondOrderUnitParams.setMarketId(
                            l_newOrderSpecs[i].getMarketId());
                        
                        //　@"債券注文単位Params".注文カテゴリ = 現注文内容.get注文カテゴリ()
                        l_bondOrderUnitParams.setOrderCateg(
                            l_newOrderSpecs[i].getOrderCategory());
                        
                        //　@"債券注文単位Params".注文種別 = 現注文内容.get注文タイプ()
                        l_bondOrderUnitParams.setOrderType(
                            l_newOrderSpecs[i].getOrderType());
                        
                        //　@"債券注文単位Params".注文数量 = 現注文内容.get数量()
                        l_bondOrderUnitParams.setQuantity(
                            l_newOrderSpecs[i].getQuantity());
                        
                        //　@"債券注文単位Params".注文単価 = 現注文内容.get単価()
                        l_bondOrderUnitParams.setPrice(l_newOrderSpecs[i].getPrice());
                        
                        //　@"債券注文単位Params".指値 = 現注文内容.get指値()
                        l_bondOrderUnitParams.setLimitPrice(
                            l_newOrderSpecs[i].getLimitPrice());
                        
                        //　@"債券注文単位Params".受渡代金(円貨) = 現注文内容.get概算代金()
                        l_bondOrderUnitParams.setEstimatedPrice(
                            l_newOrderSpecs[i].getEstimatedPrice());
                        
                        //　@"債券注文単位Params".発注日 = 現注文内容.get発注日()
                        l_bondOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                            l_newOrderSpecs[i].getOrderBizDate(), "yyyyMMdd"));
                        
                        //　@"債券注文単位Params".受渡日 = 現注文内容.get受渡日()
                        l_bondOrderUnitParams.setDeliveryDate(
                            l_newOrderSpecs[i].getDeliveryDate());
                        
                        //　@"債券注文単位Params".税区分 = 現注文内容.get税区分()
                        l_bondOrderUnitParams.setTaxType(l_newOrderSpecs[i].getTaxType());
                        
                        //　@"債券注文単位Params".受注日時 = 現注文内容.get受注日時()
                        l_bondOrderUnitParams.setReceivedDateTime(
                            l_newOrderSpecs[i].getReceivedDateTime());
                        
                        //　@"債券注文単位Params".入金日 = 現注文内容.get入金日()
                        l_bondOrderUnitParams.setPaymentDate(
                            l_newOrderSpecs[i].getPaymentDate());
                        
                        //　@"債券注文単位Params".約定数量 = 現注文内容.get約定数量()
                        l_bondOrderUnitParams.setExecutedQuantity(
                            l_newOrderSpecs[i].getExecutedQuantity());
                        
                        //　@"債券注文単位Params".注文約定区分 = 現注文内容.get注文約定区分()
                        l_bondOrderUnitParams.setOrderExecStatus(
                            l_newOrderSpecs[i].getOrderExecStatus());
                        
                        //　@"債券注文単位Params".取引 = 現注文内容.get取引()
                        l_bondOrderUnitParams.setDealType(l_newOrderSpecs[i].getDealType());
                        
                        //※"顧客属性" = get顧客属性()の戻り値
                        
                        //1.7.1.2.3.3.remove(Object)
                        //"債券注文単位マップ"より、指定の要素を削除する。
                        //[引数]
                        //Object = new Long(:long = get注文単位ID()の戻り値)
                        l_treeMap.remove(new Long(l_lngOrderUnitId));
                    }
                    
                    //1.7.1.2.4.(*)分岐フロー
                    //以外（新規注文）の場合
                    else
                    {
                        //1.7.1.2.4.1.BondOrderUnitParams( )
                        //"債券注文単位Paramas"を生成する。
                        l_bondOrderUnitParams = new BondOrderUnitParams();
                        
                        //[設定値]
                        //　@"債券注文単位Params".口座ID = "顧客属性".get口座ID()
                        l_bondOrderUnitParams.setAccountId(l_accountInfo.getAccountId());
                        
                        //　@"債券注文単位Params".補助口座ID = 現注文内容.get補助口座ID()
                        l_bondOrderUnitParams.setSubAccountId(
                            l_newOrderSpecs[i].getSubAccountId());
                        
                        //　@"債券注文単位Params".注文ID = 現注文内容.get注文ID()
                        l_bondOrderUnitParams.setOrderId(l_newOrderSpecs[i].getOrderId());
                        
                        //　@"債券注文単位Params".注文単位ID = 現注文内容.get注文単位ID()
                        l_bondOrderUnitParams.setOrderUnitId(
                            l_newOrderSpecs[i].getOrderUnitId());
                        
                        //　@"債券注文単位Params".銘柄ID = 現注文内容.get銘柄ID()
                        l_bondOrderUnitParams.setProductId(l_newOrderSpecs[i].getProductId());
                        
                        //　@"債券注文単位Params".銘柄タイプ = 現注文内容.get銘柄タイプ()
                        l_bondOrderUnitParams.setProductType(l_newOrderSpecs[i].getProductType());
                        
                        //　@"債券注文単位Params".市場ID = 現注文内容.get市場ID()
                        l_bondOrderUnitParams.setMarketId(l_newOrderSpecs[i].getMarketId());
                        
                        //　@"債券注文単位Params".注文カテゴリ = 現注文内容.get注文カテゴリ()
                        l_bondOrderUnitParams.setOrderCateg(l_newOrderSpecs[i].getOrderCategory());
                        
                        //　@"債券注文単位Params".注文種別 = 現注文内容.get注文タイプ()
                        l_bondOrderUnitParams.setOrderType(l_newOrderSpecs[i].getOrderType());
                        
                        //　@"債券注文単位Params".注文数量 = 現注文内容.get数量()
                        l_bondOrderUnitParams.setQuantity(l_newOrderSpecs[i].getQuantity());
                        
                        //　@"債券注文単位Params".注文単価 = 現注文内容.get単価()
                        l_bondOrderUnitParams.setPrice(l_newOrderSpecs[i].getPrice());
                        
                        //　@"債券注文単位Params".指値 = 現注文内容.get指値()
                        l_bondOrderUnitParams.setLimitPrice(l_newOrderSpecs[i].getLimitPrice());
                        
                        //　@"債券注文単位Params".受渡代金(円貨) = 現注文内容.get概算代金()
                        l_bondOrderUnitParams.setEstimatedPrice(
                            l_newOrderSpecs[i].getEstimatedPrice());
                        
                        //　@"債券注文単位Params".発注日 = 現注文内容.get発注日()
                        l_bondOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                            l_newOrderSpecs[i].getOrderBizDate(), "yyyyMMdd"));
                        
                        //　@"債券注文単位Params".受渡日 = 現注文内容.get受渡日()
                        l_bondOrderUnitParams.setDeliveryDate(
                            l_newOrderSpecs[i].getDeliveryDate());
                        
                        //　@"債券注文単位Params".税区分 = 現注文内容.get税区分()
                        l_bondOrderUnitParams.setTaxType(l_newOrderSpecs[i].getTaxType());
                        
                        //　@"債券注文単位Params".受注日時 = 現注文内容.get受注日時()
                        l_bondOrderUnitParams.setReceivedDateTime(
                            l_newOrderSpecs[i].getReceivedDateTime());
                        
                        //　@"債券注文単位Params".入金日 = 現注文内容.get入金日()
                        l_bondOrderUnitParams.setPaymentDate(
                            l_newOrderSpecs[i].getPaymentDate());
                        
                        //　@"債券注文単位Params".約定数量 = 現注文内容.get約定数量()
                        l_bondOrderUnitParams.setExecutedQuantity(
                            l_newOrderSpecs[i].getExecutedQuantity());
                        
                        //　@"債券注文単位Params".注文約定区分 = 現注文内容.get注文約定区分()
                        l_bondOrderUnitParams.setOrderExecStatus(
                            l_newOrderSpecs[i].getOrderExecStatus());
                        
                        //　@"債券注文単位Params".取引 = 現注文内容.get取引()
                        l_bondOrderUnitParams.setDealType(l_newOrderSpecs[i].getDealType());
                    }
                    
                    //1.7.1.2.5.(*)分岐フロー
                    //(*)分岐フロー
                    //取消注文でない場合
                    //("債券注文単位Params".get注文数量()の戻り値 != 0)
                    if (l_bondOrderUnitParams.getQuantity() != 0)
                    {
                        //1.7.1.2.5.1.put(Object, Object)
                        //"債券注文単位マップ"に、"債券注文単位Params"を追加する。
                        //[引数]
                        //Object = new Long(:long = "債券注文単位Params".注文単位ID)
                        //Object = "債券注文単位Params"
                        l_treeMap.put(new Long(
                            l_bondOrderUnitParams.getOrderUnitId()), 
                            l_bondOrderUnitParams);
                    }
                }
            }
        }

        //1.8.(*)"債券注文単位マップ"を、リストに変換して返却する。
        
        log.exiting(STR_METHOD_NAME);
        return new ArrayList(l_treeMap.values());
    }
    
    /**
     * (get債券注文<預り金>)<BR> 
     * <BR>
     * 預り金においてロード対象となる債券過日約定取消注文を、<BR> 
     * 債券注文単位テーブルより検索し、行オブジェクトのリストを返却する。<BR> 
     * <BR>
     * ※シーケンス図「(余力データソースアクセス管理)get債券注文<預り金>」参照 <BR>
     * <BR>
     * @@param l_assetValuation - (資産評価)
     * @@return List
     */
    public List getBondOrdersCash(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getBondOrdersCash(WEB3TPAssetValuation l_assetValuation)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get顧客属性( )
        //顧客属性オブジェクトを取得する。
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        
        //1.2.get余力計算条件( )
        //余力計算条件オブジェクトを取得する。
        WEB3TPCalcCondition l_caclCondition = l_assetValuation.getCalcCondition();
        
        //1.3.get営業日(int)
        //当日営業日を取得する。
        //[引数]
        //int = 0
        Date l_datBizDate = l_caclCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();
        
        //1.4.(*)債券注文単位テーブルを検索する。
        //    検索条件：
        //   　@口座ID = "顧客属性".get口座ID()
        l_sbWhere.append("account_id = ? ");
        long l_lngAccountId = l_accountInfo.getAccountId();
        l_lisBindVars.add(new Long(l_lngAccountId));
        
        //   　@AND
        //   　@[a.現物顧客の場合]
        //   　@("顧客属性".is信用顧客() == false)
        //   　@　@補助口座ID = "顧客属性".get補助口座ID(:SubAccountTypeEnum = '1:株式取引口座（預り金）')
        long l_lngSubAccountId = 0;
        if (!l_accountInfo.isMarginCustomer())
        {
            l_sbWhere.append("and sub_account_id = ? ");
            l_lngSubAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_lisBindVars.add(new Long(l_lngSubAccountId));
        }
        
        //   　@[a.信用顧客の場合]
        //   　@("顧客属性".is信用顧客() == true)
        //   　@　@補助口座ID IN 
        //   　@　@　@(　@"顧客属性".get補助口座ID(:SubAccountTypeEnum = 1：株式取引口座（預り金）),  
        //   　@　@　@　@　@"顧客属性".get補助口座ID(:SubAccountTypeEnum = 2：株式信用取引口座（保証金）) )
        else if (l_accountInfo.isMarginCustomer())
        {
            l_sbWhere.append("and sub_account_id in (?, ?) ");
            long l_lngEquitySubAccountId =
                l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_lisBindVars.add(new Long(l_lngEquitySubAccountId));
            long l_lngMarginSubAccountId =
                l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_lisBindVars.add(new Long(l_lngMarginSubAccountId));
        }
            
        //   　@AND
        //   　@注文種別 IN (401：債券買い注文, 注文種別 = 402：債券売い注文)
        l_sbWhere.append("and order_type in (?, ?) ");
        l_lisBindVars.add(OrderTypeEnum.BOND_BUY);
        l_lisBindVars.add(OrderTypeEnum.BOND_SELL);
        
        //   　@AND 
        //   　@注文約定区分 ＝ 2：取消済
        l_sbWhere.append("and order_exec_status = ? ");
        l_lisBindVars.add(WEB3BondOrderExecStatusDef.CANCELED);
        
        //   　@AND 
        //   　@約定分host反映区分 ＝ 2：客勘反映済
        l_sbWhere.append("and exec_host_reflect_div = ? ");
        l_lisBindVars.add(WEB3HostReflectDivDef.CUSTOMER_CALCULATION_REFLECTED);
        
        //   　@AND
        //   　@取消分host反映区分 IN(0：未反映, 1：送信済) 
        l_sbWhere.append("and cancel_host_reflect_div in (?, ?) ");
        l_lisBindVars.add(WEB3HostReflectDivDef.NOT_REFLECT); 
        l_lisBindVars.add(WEB3HostReflectDivDef.SENDED); 
        
        //   　@AND
        //   　@受渡日 >= "T+0"
        l_sbWhere.append("and delivery_date >= ? ");
        l_lisBindVars.add(l_datBizDate);
        
        //   　@AND
        //   　@決済区分 = 1：円貨
        l_sbWhere.append("and settlement_div = ? ");
        l_lisBindVars.add(WEB3BondOrderSettleDivDef.YEN_CURRENCY);
        
        String l_strWhere = l_sbWhere.toString();
        List l_lisBondOrderUnitRows = null;
        
        try
        {
            QueryProcessor l_qProcessor = Processors.getDefaultProcessor();
            l_lisBondOrderUnitRows =
                l_qProcessor.doFindAllQuery(
                    BondOrderUnitRow.TYPE,
                    l_strWhere,
                    null,
                    l_lisBindVars.toArray());
        }
        catch (DataException l_dex)
        {
            log.error("DBへのアクセスに失敗しました。", l_dex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        //1.5.(*)検索結果行リストを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisBondOrderUnitRows;
    }

    /**
     * (getその他拘束金(仮拘束))<BR>
     * <BR>
     * その他拘束金(仮拘束金)情報を取得する。<BR>
     * <BR>
     * １）その他拘束金(仮拘束金)テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@　@口座ID = 引数.口座ID<BR>
     * 　@　@　@AND 削除フラグ = 0：false<BR>
     * <BR>
     * ２）検索結果を返す。<BR>
     * @@param l_accountId - (口座ID)<BR>
     * @@return List
     */
    public List getTempRestraint(Long l_accountId)
    {
        final String STR_METHOD_NAME = "getTempRestraint(Long)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();

        //口座ID = 引数.口座ID
        l_sbWhere.append(" account_id = ? ");
        l_lisBindVars.add(l_accountId);
        //削除フラグ = 0：false
        l_sbWhere.append(" and delete_flag = ? ");
        l_lisBindVars.add(BooleanEnum.FALSE);

        List l_lisTpOtherTempRestraintRows = null;
        try
        {
            QueryProcessor l_qProcessor = Processors.getDefaultProcessor();
            l_lisTpOtherTempRestraintRows =
                l_qProcessor.doFindAllQuery(
                    TpOtherTempRestraintRow.TYPE,
                    l_sbWhere.toString(),
                    l_lisBindVars.toArray());
        }
        catch (DataException l_dex)
        {
            log.error("DBへのアクセスに失敗しました。", l_dex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        //検索結果を返す。
        log.exiting(STR_METHOD_NAME);
        return l_lisTpOtherTempRestraintRows;
    }

    /**
     * (get外株銘柄)<BR>
     * 外株銘柄テーブルをPK検索する。 <BR>
     * <BR>
     * 検索条件 <BR>
     * 　@銘柄ID = 引数.銘柄ID <BR>
     * <BR>
     * ※検索できなかった場合、例外をスローする。<BR>
     * @@param l_lngProductId - (銘柄ＩＤ)<BR>
     * (銘柄ＩＤ)<BR>
     * @@return FeqProductRow
     */
    public FeqProductRow getFeqProduct(long l_lngProductId)
    {
        final String STR_METHOD_NAME = "getFeqProduct(long)";
        log.entering(STR_METHOD_NAME);

        FeqProductRow l_feqProductRow = null;
        //外株銘柄テーブルをPK検索する
        //検索条件
        //　@銘柄ID = 引数.銘柄ID
        try
        {
            l_feqProductRow = FeqProductDao.findRowByPk(l_lngProductId);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_feqProductRow;
    }

    /**
     * (get（共通）通貨)<BR>
     * （共通）通貨テーブルをPK検索する。 <BR>
     * <BR>
     * 検索条件： <BR>
     * 　@証券会社コード = 引数.証券会社コード <BR>
     * 　@通貨コード = 引数.通貨コード <BR>
     * <BR>
     * ※検索できなかった場合、例外をスローする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strCurrencyCode - (通貨コード)<BR>
     * @@return GenCurrencyRow
     */
    public GenCurrencyRow getGenCurrency(String l_strInstitutionCode, String l_strCurrencyCode)
    {
        final String STR_METHOD_NAME = "getGenCurrency(String, String)";
        log.entering(STR_METHOD_NAME);

        GenCurrencyRow l_genCurrencyRow = null;
        //（共通）通貨テーブルをPK検索する
        //検索条件
        //　@証券会社コード = 引数.証券会社コード
        //　@通貨コード = 引数.通貨コード
        try
        {
            l_genCurrencyRow = GenCurrencyDao.findRowByPk(l_strInstitutionCode, l_strCurrencyCode);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_genCurrencyRow;
    }

    /**
     * (saveその他拘束金（仮拘束）)<BR>
     * その他拘束金（仮拘束）テーブルにレコードを挿入する。<BR>
     * <BR>
     * [設定値]<BR>
     * 　@「挿入_その他拘束金(仮拘束)」DB更新仕様参照<BR>
     * <BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_dblTradeRestraint - (拘束金)<BR>
     * 拘束金<BR>
     * @@param l_datTransaction - (トランザクション発生日)<BR>
     * トランザクション発生日<BR>
     * @@param l_datDelivery - (受渡日)<BR>
     * 受渡日<BR>
     * @@param l_strDeletekey1 - (削除キー１)<BR>
     * 削除キー１<BR>
     * @@param l_strRestraintDiv - (拘束金種別)<BR>
     * 拘束金種別<BR>
     * @@throws WEB3BaseException
     */
    public void saveOtherRestraint(
		long l_lngAccountId,
		double l_dblTradeRestraint,
		Date l_datTransaction,
		Date l_datDelivery,
		String l_strDeletekey1,
		String l_strRestraintDiv) throws WEB3BaseException
    {
    	final String STR_METHOD_NAME =
            "saveOtherRestraint(long, double, Date, Date, String, String)";
    	log.entering(STR_METHOD_NAME);

        try
        {
        	TpOtherTempRestraintParams l_tpOtherTempRestraintParams = new TpOtherTempRestraintParams();
        	//口座ID=引数.口座ＩＤ
        	l_tpOtherTempRestraintParams.setAccountId(l_lngAccountId);
        	//拘束金種別=引数.拘束金種別
        	l_tpOtherTempRestraintParams.setRestraintDiv(l_strRestraintDiv);
        	//拘束金=引数.拘束金
        	l_tpOtherTempRestraintParams.setAmount(l_dblTradeRestraint);
        	//トランザクション発生日=引数.トランザクション発生日
        	l_tpOtherTempRestraintParams.setTransactionDate(l_datTransaction);
        	//受渡日=引数.受渡日
        	l_tpOtherTempRestraintParams.setDeliveryDate(l_datDelivery);
        	//削除キー1=引数.削除キー１
        	l_tpOtherTempRestraintParams.setDeleteKey1(l_strDeletekey1);
        	//削除キー2=NULL
        	l_tpOtherTempRestraintParams.setDeleteKey2(null);
        	//削除フラグ=0：FALSE
        	l_tpOtherTempRestraintParams.setDeleteFlag(BooleanEnum.FALSE);
        	//作成日付=現在時刻（システムタイムスタンプ）
        	l_tpOtherTempRestraintParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        	//更新日付=現在時刻（システムタイムスタンプ）
        	l_tpOtherTempRestraintParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        	QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        	l_queryProcessor.doInsertQuery(l_tpOtherTempRestraintParams);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (deleteその他拘束金（仮拘束）)<BR>
     * その他拘束金（仮拘束）テーブルを更新（論理削除）する。<BR>
     * <BR>
     * １）その他拘束金（仮拘束）テーブルを検索し、項目を更新する。<BR>
     * [検索条件]<BR>
     * 　@拘束金種別 = 引数.拘束金種別<BR>
     * 　@削除キー１ = 引数.削除キー１<BR>
     * <BR>
     * [設定値] <BR>
     * 　@「更新_その他拘束金（仮拘束）」DB更新仕様参照<BR>
     * @@param l_strRestraintDiv - (拘束金種別)<BR>
     * 拘束金種別<BR>
     * @@param l_strDeletekey1 - (削除キー１)<BR>
     * 削除キー１<BR>
     * @@throws WEB3BaseException
     */
    public void deleteOtherRestraint(String l_strRestraintDiv, String l_strDeletekey1) throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "deleteOtherRestraint(String, String)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" restraint_div = ? ");
        l_sbWhere.append(" and delete_key1 = ? ");

        Object[] l_objWheres =
            new Object[]{l_strRestraintDiv, l_strDeletekey1};

        Map l_map = new HashMap();
        l_map.put("delete_flag", BooleanEnum.TRUE);
        l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                TpOtherTempRestraintRow.TYPE,
                l_sbWhere.toString(),
                l_objWheres,
                l_map);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (isExistその他拘束金（仮拘束）)<BR>
     * <BR>
     * その他拘束金（仮拘束）テーブルに引数.削除キー１が存在するかどうか判断する。<BR>
     * 　@※レコードが存在する場合は、true、レコードが存在しない場合は、false を返却する。<BR>
     * <BR>
     * [検索条件]<BR>
     * 　@拘束金種別 = 引数.拘束金種別<BR>
     * 　@削除キー１ = 引数.削除キー１<BR>
     * @@param l_strRestraintDiv - (拘束金種別)<BR>
     * 拘束金種別<BR>
     * @@param l_strDeletekey1 - (削除キー１)<BR>
     * 削除キー１<BR>
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isExistOtherRestraint(String l_strRestraintDiv, String l_strDeletekey1) throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "isExistOtherRestraint(String, String)";
    	log.entering(STR_METHOD_NAME);

    	List l_lisTpOtherTempRestraintRows = new ArrayList();
        try
        {
        	//[検索条件]
        	//拘束金種別 = 引数.拘束金種別
        	//削除キー１ = 引数.削除キー１
        	l_lisTpOtherTempRestraintRows =
        		TpOtherTempRestraintDao.findRowsByRestraintDivDeleteKey1(l_strRestraintDiv, l_strDeletekey1);
        	if (!l_lisTpOtherTempRestraintRows.isEmpty())
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
    }

    /**
     * (get顧客勘定残高(マスタ情報))<BR>
     * <BR>
     * 顧客勘定残高(マスタ情報)Rowを取得する。<BR>
     * ※取得できない場合は、nullを返却する。<BR>
     * <BR>
     * [検索条件]<BR>
     * 　@・口座ID = 引数.口座ID<BR>
     * 　@・補助口座ID = 引数.補助口座ID<BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_lngSubAccountId - (補助口座ID)<BR>
     * 補助口座ID<BR>
     * @@return TpCashBalanceRow
     * @@throws WEB3BaseException
     */
    public TpCashBalanceRow getTpCashBalanceRow(long l_lngAccountId, long l_lngSubAccountId)
        throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getTpCashBalanceRow(long, long)";
        log.entering(STR_METHOD_NAME);

        TpCashBalanceRow l_tpCashBalanceRow = null;
        //顧客勘定残高(マスタ情報)Rowを取得する。
        //[検索条件]
        //　@口座ID = 引数.口座ID
        //　@補助口座ID = 引数.補助口座ID
        try
        {
        	l_tpCashBalanceRow = TpCashBalanceDao.findRowByAccountIdSubAccountId(l_lngAccountId, l_lngSubAccountId);
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

        log.exiting(STR_METHOD_NAME);
        return l_tpCashBalanceRow;
    }

    /**
     * (get株式銘柄)<BR>
     * 株式銘柄テーブルをPK検索する。 <BR>
     * <BR>
     * 検索条件<BR> 
     * 　@銘柄ID = 引数.銘柄ID<BR> 
     * <BR>
     * @@param l_lngProductId -(銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@return EqtypeProductRow
     * @@throws WEB3BaseException
     */
    public EqtypeProductRow getEqtypeProduct(long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEqtypeProduct(long)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeProductRow l_eqtypeProductRow = null;
        
        try
        {
        	l_eqtypeProductRow = EqtypeProductDao.findRowByPk(l_lngProductId);
        }
        catch (DataFindException l_dfe)
        {
        	l_eqtypeProductRow = null;
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
        
        log.exiting(STR_METHOD_NAME);
        return l_eqtypeProductRow;
        
    }
    		
}
@
