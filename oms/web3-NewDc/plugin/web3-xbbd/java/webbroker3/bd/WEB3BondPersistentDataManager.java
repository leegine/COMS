head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondPersistentDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 債券データマネージャ(WEB3BondPersistentDataManager.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17 周捷(中訊) 新規作成
 */

package webbroker3.bd;

import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondPositionManagerHelper.PersistentDataManager;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券データマネージャ)<BR>
 * 債券データマネージャクラス
 * 
 * @@author 周捷(中訊)
 * @@version 1.0
 */
public class WEB3BondPersistentDataManager extends PersistentDataManager
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondPersistentDataManager.class);
    
    /**
     * @@roseuid 44E3531B0167
     */
    public WEB3BondPersistentDataManager() 
    {
     
    }
    
    /**
     * (get保有資産Params)<BR>
     * getAssetのオーバーライド<BR>
     * <BR>
     * １）債券ポジションマネージャー.get保有資産を呼ぶ<BR>
     * 引数<BR>
     * BondFinTransactionParams.口座ID<BR>
     * BondFinTransactionParams.補助口座ID<BR>
     * BondFinTransactionParams.銘柄ID<BR>
     * BondFinTransactionParams.税区分<BR>
     * <BR>
     * ２）AssetParamsを返す<BR>
     * if(戻り値の保有資産オブジェクト != null)<BR>
     * {<BR>
     *   戻り値の保有資産オブジェクト.getDataSourceObject()を返す<BR>
     * }<BR>
     * else<BR>
     * {<BR>
     *   nullを返す<BR>
     * }<BR>
     * @@param l_bondFinTransactionParams - BondFinTransactionParams<BR>
     * @@return AssetParams
     * @@throws WEB3BaseException 
     * @@roseuid 44D0362B005D
     */
    public AssetParams getAsset(
        BondFinTransactionParams l_bondFinTransactionParams)
    {
        final String STR_METHOD_NAME = " getAsset(BondFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondFinTransactionParams == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "パラメータ値がNULL");
        }
        //１）債券ポジションマネージャー.get保有資産を呼ぶ 
        //引数 
        //BondFinTransactionParams.口座ID 
        //BondFinTransactionParams.補助口座ID 
        //BondFinTransactionParams.銘柄ID 
        //BondFinTransactionParams.税区分 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondPositionManager l_bondPositionManager = 
            (WEB3BondPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getPositionManager();
        Asset l_asset = null;
        try
        {
            l_asset = l_bondPositionManager.getAsset(
                l_bondFinTransactionParams.getAccountId(),
                l_bondFinTransactionParams.getSubAccountId(),
                l_bondFinTransactionParams.getProductId(),
                l_bondFinTransactionParams.getTaxType());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("__error in 債券保有資産オブジェクトの取得__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);   
        }
        
        //２）AssetParamsを返す 
        //if(戻り値の保有資産オブジェクト != null) 
        if (l_asset != null)
        {
            //戻り値の保有資産オブジェクト.getDataSourceObject()を返す 
            log.exiting(STR_METHOD_NAME);
            AssetRow l_assetRow = 
                (AssetRow)l_asset.getDataSourceObject();
            return new AssetParams(l_assetRow);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    /**
     * (set更新保有資産属性)<BR>
     * setUpdateAssetAttributesのオーバーライド。<BR>
     * <BR>
     * 保有資産Paramsの更新対象のプロパティに、更新値をセットする。 <BR>
     * （void setUpdateAssetAttributes(AssetParams asset, Map vals)のオーバーライド） <BR>
     * <BR>
     * 【保有資産テーブル】の更新対象の項目毎に、<BR>
     * 保有資産Paramsの同名プロパティをセットする。 <BR>
     * <BR>
     * １）　@super.setUpdateAssetAttributes(引数の保有資産Params, 引数の更新値Map)をコールし、 <BR>
     * 　@　@　@xTrade標準プロパティをセットする。 <BR>
     * <BR>
     * ２）　@拡張プロパティをセットする。 <BR>
     * <BR>
     * 　@　@　@-------------------------------------------------- <BR>
     * 　@　@　@＜セット対象項目＞ <BR>
     * <BR>
     * 　@　@　@売付不能数量 <BR>
     * 　@　@　@数量（簿価単価計算用） <BR>
     * 　@　@　@-------------------------------------------------- <BR>
     * @@param l_assetParams - (保有資産Params)<BR>
     * 保有資産Params<BR>
     * @@param l_mapUpdate - (更新値Map)<BR>
     * 更新値Map<BR>
     * @@roseuid 44D992BB02DE
     */
    public void setUpdateAssetAttributes(AssetParams l_assetParams, Map l_mapUpdate) 
    {
        final String STR_METHOD_NAME = " setUpdateAssetAttributes(AssetParams, Map)";
        log.entering(STR_METHOD_NAME);
        
        if (l_assetParams == null || l_mapUpdate == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "パラメータ値がNULL");
        }
        
        //１）　@super.setUpdateAssetAttributes(引数の保有資産Params, 引数の更新値Map)をコールし、  
        //  xTrade標準プロパティをセットする。  
        super.setUpdateAssetAttributes(l_assetParams, l_mapUpdate);
        
        //２）　@拡張プロパティをセットする。  
        //
        //  --------------------------------------------------  
        //  ＜セット対象項目＞  
        //
        //  売付不能数量  
        //  数量（簿価単価計算用）  
        //  --------------------------------------------------  
        l_mapUpdate.put("quantity_cannot_sell", new Double(l_assetParams.getQuantityCannotSell()));
        l_mapUpdate.put("quantity_for_book_value", new Double(l_assetParams.getQuantityForBookValue()));
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
