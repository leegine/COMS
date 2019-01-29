head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqPositionManagerHelper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 外国株式ポジションヘルパー(WEB3FeqPositionManagerHelper.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/14 呉艶飛(中訊) 新規作成
                   2005/07/26 王亞洲(中訊) レビュー
Revesion History : 2007/11/07 何文敏(中訊) 仕様変更モデルNo.358
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
Revesion History : 20010/01/11 張騰宇(中訊) モデルNo.529 No.530 No.534 No.535 No.537 No.540 ＤＢ更新仕様 No.104-116
Revesion History : 2010/03/05 武波 (中訊)【外国株式】仕様変更管理台帳（モデル）No.543
Revesion History : 2010/09/08 趙天月 (中訊)【外国株式】仕様変更管理台帳（モデル）No.545
Revesion History : 2010/09/10 劉レイ(中訊) モデルNo.546
Revesion History : 2010/09/14 車進(中訊) モデルNo.551 No.552 No.554
*/
package webbroker3.feq;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GeneralizedFinTransaction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GeneralizedFinTransactionManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqAssetImpl;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqPositionManagerHelper;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;




/**
 * (外国株式ポジションヘルパー) <BR>
 * 外国株式ポジションヘルパー<BR>
 * <BR>
 * @@ author 呉艶飛 <BR>
 * @@ version 1.0<BR>
 */
public class WEB3FeqPositionManagerHelper extends FeqPositionManagerHelper 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FeqPositionManagerHelper.class);
    
    /**
     * @@roseuid 42CE39E80222
     */
    public WEB3FeqPositionManagerHelper(ProductTypeEnum tradingModuleType) 
    {
        super(tradingModuleType);
    }
    
    public PersistentDataManager getPersistenceManager()
    {
        return new WEB3FeqUpdateDataManager();
    }
    
    /**
     * (applyTo保有資産残高) <BR>
     * （applyToAssetPositionのオーバーライド） <BR>
     * 保有資産を更新する。 <BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（外株残高）applyTo保有資産残高」参照。 <BR>
     * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行) <BR>
     * トランザクション（取引勘定明細）行オブジェクト
     * @@return List
     * @@throws DataException
     * @@roseuid 4288262A00C6
     */
    protected List applyToAssetPosition(FeqFinTransactionParams l_feqFinTransactionParams) 
        throws DataException
    {
        final String STR_METHOD_NAME = "applyToAssetPosition(FeqFinTransactionParams)";    
        log.entering(STR_METHOD_NAME);
        if (l_feqFinTransactionParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //get顧客(口座ID : )
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMarket l_market = null;
        boolean l_blnIsDayTradeAdoption = false;
        boolean l_blnIsDayTradeMarket = false;
        try
        {
            l_market = (WEB3GentradeMarket)(l_finApp.getFinObjectManager().getMarket(l_feqFinTransactionParams.getMarketId()));
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_feqFinTransactionParams.getAccountId());
            WEB3GentradeInstitution l_institution =
                (WEB3GentradeInstitution)l_mainAccount.getInstitution();

            try
            {
                l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();
                l_blnIsDayTradeMarket = l_market.isDayTradeMarket();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.2トランザクションタイプを取得する。 
        FinTransactionType l_transactionType = l_feqFinTransactionParams.getFinTransactionType();

        // （is日計り取引採用() == false　@or　@is日計り市場() == false）　@且つ　@買の場合(getFinTransactionType() == 外株買)
        if ((!l_blnIsDayTradeAdoption || !l_blnIsDayTradeMarket) && FinTransactionType.EQTYPE_FEQ_BUY.equals(l_transactionType))
        {
            return null;
        }

        //買の場合（getFinTransactionType( ) == 外株買） 、且つ出来通知時（※）
        Object l_obj = ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG);
        if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_transactionType) && l_obj != null && l_obj.equals(BooleanEnum.TRUE))
        {
            boolean l_blnDayExchange = false;

            l_blnDayExchange = ((Boolean)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG)).booleanValue();
            if (!l_blnDayExchange)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }

        //1.1外国株式更新データマネージャを取得する。 
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();

        //1.4保有資産を取得する。 
        AssetParams l_asserParams = l_dataManager.getAsset(l_feqFinTransactionParams);
        
		//保有資産に該当するデータがない、且つ　@売付(getFinTransactionType() == 外株売)の場合、例外をスローする。
		if (l_asserParams == null && FinTransactionType.EQTYPE_FEQ_SELL.equals(l_transactionType))
        {
		  log.error("保有資産該当データなし。");
		  throw new WEB3BaseRuntimeException(
		  WEB3ErrorCatalog.BUSINESS_ERROR_00204,
		  this.getClass().getName() + "." + STR_METHOD_NAME);
		}

        //買の場合（getFinTransactionType() == 外株買）
        if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_transactionType))
        {
            //残高がない（get保有資産()の戻り値 == null）場合
            if (l_asserParams == null)
            {
                l_asserParams = new AssetParams();
                //（自動採番）
                l_asserParams.setAssetId(AssetDao.newPkValue());
                //トランザクション（取引勘定明細）行.口座ＩＤ
                l_asserParams.setAccountId(l_feqFinTransactionParams.getAccountId());
                //トランザクション（取引勘定明細）行.補助口座ＩＤ
                l_asserParams.setSubAccountId(l_feqFinTransactionParams.getSubAccountId());
                //トランザクション（取引勘定明細）行.銘柄タイプ
                l_asserParams.setProductType(l_feqFinTransactionParams.getProductType());
                //トランザクション（取引勘定明細）行.約定数量
                l_asserParams.setQuantity(l_feqFinTransactionParams.getQuantity());
                //0
                l_asserParams.setQuantityCannotSell(0);
                //トランザクション（取引勘定明細）行.約定数量
                l_asserParams.setQuantityForBookValue(l_feqFinTransactionParams.getQuantity());
                //トランザクション（取引勘定明細）行.受渡代金×（-1）
                l_asserParams.setBookValue(l_feqFinTransactionParams.getNetAmount() * (-1));
                //0
                l_asserParams.setSetupFee(0);
                //0
                l_asserParams.setSetupFeeTax(0);
                //0
                l_asserParams.setManagementFee(0);
                //0
                l_asserParams.setManagementFeeTax(0);
                //トランザクション（取引勘定明細）行.銘柄ＩＤ
                l_asserParams.setProductId(l_feqFinTransactionParams.getProductId());
                //トランザクション（取引勘定明細）行.税区分
                l_asserParams.setTaxType(l_feqFinTransactionParams.getTaxType());
                //0：DEFAULT（ミニ株でない）
                l_asserParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                //0
                l_asserParams.setProfitDistribution(0);
                //0
                l_asserParams.setCountBeforePenalty(0);
                //現在日時
                l_asserParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                //現在日時
                l_asserParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //saveNewAsset(arg0 : AssetParams)
                l_dataManager.saveNewAsset(l_asserParams);
            }
            //保有資産が既に存在する場合（get保有資産()の戻り値 != null）の場合
            else
            {
                BigDecimal l_bdQuantity = new BigDecimal(Double.toString(l_feqFinTransactionParams.getQuantity()));

                //数量   （既存値）＋トランザクション（取引勘定明細）行.約定数量
                BigDecimal l_bdAssetQuantity = new BigDecimal(Double.toString(l_asserParams.getQuantity()));
                l_asserParams.setQuantity(l_bdAssetQuantity.add(l_bdQuantity).doubleValue());

                //数量（簿価単価計算用）    （既存値）＋トランザクション（取引勘定明細）行.約定数量
                BigDecimal l_bdQuantityForBookValue = new BigDecimal(Double.toString(l_asserParams.getQuantityForBookValue()));
                l_asserParams.setQuantityForBookValue(l_bdQuantityForBookValue.add(l_bdQuantity).doubleValue());

                //簿価（簿価単価計算用）
                //（税区分 == "一般"） &&
                //　@（既存値.数量（簿価単価計算用） > 0） &&
                //　@（既存値.簿価（簿価単価計算用） == 0） の場合、（既存値）。
                //以外、（既存値）＋トランザクション（取引勘定明細）行.受渡代金×（-1）。
                //（※一般口座であっても、簿価が設定されている場合は更新対象とする）
                if (!(TaxTypeEnum.NORMAL.equals(l_asserParams.getTaxType())
                    && l_asserParams.getQuantityForBookValue() > 0
                    && GtlUtils.Double.isZero(l_asserParams.getBookValue())))
                {
                    //（既存値）＋トランザクション（取引勘定明細）行.受渡代金×（-1）
                    l_asserParams.setBookValue(l_asserParams.getBookValue() +
                        l_feqFinTransactionParams.getNetAmount() * -1);
                }
                l_asserParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_dataManager.updateAssetByTrans(l_asserParams); 
            }
        }
        
        //1.5売の場合（getFinTransactionType() == 外株売）
        if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_transactionType))
        {
            //1.5.1保有資産行（getAsset()の戻り値）に値をセットする。 
            //【ｘTrade】補足資料.DB更新\\11.管理者・出来入力 
            //「外株出来_保有資産テーブル仕様.xls#外株出来_保有資産 DB更新（③売Update）」 
            //参照。
        
			//assetの数量 >= 約定数量のチェック
			if(l_asserParams.getQuantity() >= l_feqFinTransactionParams.getQuantity()){      
            	//（既存値）- トランザクション（取引勘定明細）行.約定数量
            	l_asserParams.setQuantity(l_asserParams.getQuantity() - l_feqFinTransactionParams.getQuantity());
			}else{
				log.error("保有資産残数量チェックエラー。");
				throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01931,
				this.getClass().getName() + "." + STR_METHOD_NAME);
			}
            //更新日付 = 現在日時
            l_asserParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //1.5.2保有資産行を更新する 
            l_dataManager.updateAssetByTrans(l_asserParams); 
        }
        //1.6保有資産ＩＤを取得する。 
        long l_lngAssetId = l_asserParams.getAssetId();
        
        //1.7保有資産ＩＤをセットする。 
        l_feqFinTransactionParams.setAssetId(l_lngAssetId);
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (applyToAssetUnitPosition)<BR>
     * （未使用） <BR>
     * nullを返却する。 <BR>
     *  <BR>
     * ※簿価単価算出用テーブル（ASSET_UNIT：取得単位保有資産） <BR>
     * 更新プロセス <BR>
     * @@param l_feqFinTransactionParams
     * @@param l_assetParams
     * @@return ListResourceBundle
     * @@throws DataException
     * @@roseuid 4288402D0394
     */
    protected List applyToAssetUnitPosition(FeqFinTransactionParams l_feqFinTransactionParams, AssetParams l_assetParams) throws DataException
    {
     return null;
    }
    
    /**
     * (process約定残高更新) <BR>
     * （processCashBasedOrderExecutionのオーバーライド） <BR>
     * 外国株式残高更新処理を行う。 <BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（外株残高）process約定残高更新」参照。 <BR>
     * @@param l_feqOrderExecution - (約定オブジェクト)
     * @@throws DataException
     * @@roseuid 428826850385
     */
    protected void processCashBasedOrderExecution(FeqOrderExecution l_feqOrderExecution) throws DataException 
    {
        final String STR_METHOD_NAME = "processCashBasedOrderExecution(FeqOrderExecution)";    
        log.entering(STR_METHOD_NAME);
        if (l_feqOrderExecution == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1トランザクション（取引勘定明細）行オブジェクトを生成する。 
        FeqFinTransactionParams l_transactionParams = new FeqFinTransactionParams();
        
        //1.2トランザクション（取引勘定明細）行オブジェクトに初期値をセットする。 
        this.setMarketOrderedTransDefaultValues(l_transactionParams);
        
        //1.3約定情報をトランザクションにセットする。 
        this.setExecutionInfoToMarketOrderedTrans(l_transactionParams, l_feqOrderExecution);
        
        //1.4applyTo保有資産残高
        this.applyToAssetPosition(l_transactionParams);
        
        //1.5外国株式更新データマネージャを取得する。 
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
        
        //1.6トランザクション（取引勘定明細）行オブジェクトをDBに更新する。 
        l_dataManager.saveNewFinTransaction(l_transactionParams);
        
        //1.7顧客勘定明細，補助口座を更新する。 
        this.notifyGtl(l_transactionParams);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notify顧客勘定) <BR>
     * （notifyGtlのオーバーライド） <BR>
     * 顧客勘定明細，補助口座を更新する。 <BR>
     *  <BR>
     * １）　@顧客勘定明細（：GeneralizedFinTransaction）オブジェクトを生成する。 <BR>
     *  <BR>
     * 　@[コンストラクタの引数] <BR>
     * 　@トランザクションＩＤ：　@<BR>
     * 　@　@　@トランザクション（取引勘定明細）行.トランザクションＩＤ <BR>
     * 　@口座ＩＤ：　@トランザクション（取引勘定明細）行.口座ＩＤ <BR>
     * 　@補助口座ＩＤ：　@トランザクション（取引勘定明細）行.補助口座ＩＤ <BR>
     * 　@受渡日：　@トランザクション（取引勘定明細）行.受渡日 <BR>
     * 　@トランザクションタイプ：　@ <BR>
     * 　@　@　@トランザクション（取引勘定明細）行.トランザクションタイプ  <BR>
     * 　@受渡金額：　@トランザクション（取引勘定明細）行.トランザクションＩＤ <BR>
     * 　@description：　@“FeqFinTransaction.  <BR>
     * productId(product_id),marketId(market_id),quantity(quantity),price(price)”  <BR>
     * 　@トレーディングモジュール名：　@getTradingModule().getTradingModuleName() <BR>
     * 　@トランザクション発生日時：<BR>
     * 　@　@　@トランザクション（取引勘定明細）行.トランザクション発生日時 <BR>
     *  <BR>
     * ２）　@ＧＴＬへ通知する。 <BR>
     * 　@GeneralizedFinTransactionManager.notifyTransaction()をコールする。 <BR>
     *  <BR>
     * 　@[notifyTransaction()に指定する引数] <BR>
     * 　@顧客勘定明細：　@１）で生成した顧客勘定明細 <BR>
     * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行) <BR>
     * トランザクション（取引勘定明細）行オブジェクト
     * @@roseuid 42882B0B0104
     */
    protected void notifyGtl(FeqFinTransactionParams l_feqFinTransactionParams)
    {
        final String STR_METHOD_NAME = "notifyGtl(FeqFinTransactionParams)";    
        log.entering(STR_METHOD_NAME);
        if (l_feqFinTransactionParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１）　@顧客勘定明細（：GeneralizedFinTransaction）オブジェクトを生成する。
        //トランザクションＩＤ：トランザクション（取引勘定明細）行.トランザクションＩＤ
        long l_lngTransactionId = l_feqFinTransactionParams.getFinTransactionId();        
        log.debug("トランザクションＩＤ= " + l_lngTransactionId);
        
        //口座ＩＤ：　@トランザクション（取引勘定明細）行.口座ＩＤ
        long l_lngAccountId = l_feqFinTransactionParams.getAccountId();
        log.debug("口座ＩＤ= " + l_lngAccountId);
        
        //補助口座ＩＤ：　@トランザクション（取引勘定明細）行.補助口座ＩＤ
        long l_lngSubAccountId = l_feqFinTransactionParams.getSubAccountId();
        log.debug("補助口座ＩＤ= " + l_lngSubAccountId);
        
        //受渡金額：　@トランザクション（取引勘定明細）行.トランザクションＩＤ
        double l_dblNetAmount  = l_feqFinTransactionParams.getNetAmount();
        log.debug("受渡金額= " + l_dblNetAmount);
        
        //受渡日：　@トランザクション（取引勘定明細）行.受渡日
        Timestamp l_timDeliveryDate = l_feqFinTransactionParams.getDeliveryDate();
        
        //トランザクションタイプ：トランザクション（取引勘定明細）行.トランザクションタイプ
        FinTransactionType l_finTransactionType = l_feqFinTransactionParams.getFinTransactionType();
        
        //description：
        String l_strDescription = 
            "FeqFinTransaction. productId(" + l_feqFinTransactionParams.getProductId() + ")," +
                "marketId(" + l_feqFinTransactionParams.getMarketId() + ")," +
                "quantity(" + l_feqFinTransactionParams.getQuantity() + ")," +
                "price(" + l_feqFinTransactionParams.getPrice() + ")" ;
        
        //トレーディングモジュール名：　@getTradingModule().getTradingModuleName()
        String l_strTradingModule = getTradingModule().getTradingModuleName();
        log.debug("トレーディングモジュール名= " + l_strTradingModule);
        
        //トランザクション発生日時：トランザクション（取引勘定明細）行.トランザクション発生日時 
        Timestamp l_timTransactionDate = l_feqFinTransactionParams.getFinTransactionTimestamp();
        GeneralizedFinTransaction l_gFinTransacton = 
            new GeneralizedFinTransaction(
                l_lngTransactionId, 
                l_lngAccountId, 
                l_lngSubAccountId, 
                l_timDeliveryDate, 
                l_finTransactionType, 
                l_dblNetAmount, 
                l_strDescription, 
                l_strTradingModule, 
                l_timTransactionDate);
        
        //２）　@ＧＴＬへ通知する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        GeneralizedFinTransactionManager l_transactionManager = l_finApp.getGeneralizedFinTransactionManager(); 
        
        l_transactionManager.notifyTransaction(l_gFinTransacton);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set約定情報Toトランザクション) <BR>
     * （setExecutionInfoToMarketOrderedTransのオーバーライド）<BR>
     * 約定情報をトランザクションにセットする。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株残高）set約定情報Toトランザクション」参照。<BR>
     * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行)<BR>
     * トランザクション（取引勘定明細）行オブジェクト
     * @@param l_feqOrderExecution - (約定) <BR>
     * 約定オブジェクト
     * @@param l_feqOrderUnitRow - (注文単位行) <BR>
     * 注文単位行オブジェクト
     * @@roseuid 4288325A0172
     */
    protected void setExecutionInfoToMarketOrderedTrans(
        FeqFinTransactionParams l_feqFinTransactionParams, 
        FeqOrderExecution l_feqOrderExecution, 
        FeqOrderUnitRow l_feqOrderUnitRow)
    {
        final String STR_METHOD_NAME = 
            "setExecutionInfoToMarketOrderedTrans(FeqFinTransactionParams,FeqOrderExecution,FeqOrderUnitRow)";    
        log.entering(STR_METHOD_NAME);
        
        if (l_feqFinTransactionParams == null || l_feqOrderExecution == null || l_feqOrderUnitRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_bizlogicProvider = (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3FeqProductManager l_productManager = (WEB3FeqProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        try
        {
            //1.1superクラスの処理をコールし、約定情報をトランザクション行にセットする。 
            super.setExecutionInfoToMarketOrderedTrans(l_feqFinTransactionParams, l_feqOrderExecution, l_feqOrderUnitRow); 
            
            //1.2約定数量を取得する
            double l_dblExecutionQuantity = l_feqOrderExecution.getExecutionQuantity();
            if (Double.isNaN(l_dblExecutionQuantity))
            {
                l_dblExecutionQuantity = 0.0D;
            }
            log.debug("約定数量= " + l_dblExecutionQuantity);
            
            //1.3約定単価を取得する
            double l_dblExecutionPrice = l_feqOrderExecution.getExecutionPrice();
            if (Double.isNaN(l_dblExecutionPrice))
            {
                l_dblExecutionPrice = 0.0D;
            }
            log.debug("約定単価= " + l_dblExecutionPrice);
            
			//1.6銘柄ＩＤを取得する。
			long l_lngProductId = l_feqFinTransactionParams.getProductId();
			log.debug("銘柄ＩＤ= " + l_lngProductId);
			
			//　@getProductId()に該当する外国株式銘柄
			WEB3FeqProduct l_feqProduct = l_productManager.getFeqProduct(l_lngProductId);
			
			//  get通貨()
			WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
            
            //1.4売買代金を計算する。
            double l_dblExecutionAmount = 
                l_bizlogicProvider.calcExecutionAmount(l_dblExecutionQuantity, l_dblExecutionPrice);
			BigDecimal l_bdExecutionAmount = new BigDecimal(l_dblExecutionAmount);
			int l_intDecimalPlace = l_currency.getScale();
			l_bdExecutionAmount = l_bdExecutionAmount.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
			l_dblExecutionAmount = l_bdExecutionAmount.doubleValue();
            log.debug("売買代金の丸めた結果= " + l_dblExecutionAmount);
            
            //1.5補助口座ＩＤを取得する。
            long l_lngSubAccountId = l_feqFinTransactionParams.getSubAccountId();
            log.debug("補助口座ＩＤ= " + l_lngSubAccountId);
            
            //1.7市場ＩＤを取得する。
            long l_lngMarketId = l_feqFinTransactionParams.getMarketId();
            log.debug("市場ＩＤ= " + l_lngMarketId);
            
            //1.8トランザクションタイプを取得する。
            FinTransactionType l_transactionType = l_feqFinTransactionParams.getFinTransactionType();
            
            //1.9為替レートを取得する。
            double l_dblFxRate = l_feqFinTransactionParams.getFxRate();
            log.debug("為替レート= " + l_dblFxRate);
            
            //1.10約定日時を取得する。
            Date l_datExecutionTime = l_feqOrderExecution.getExecutionTimestamp();
            log.debug("約定日時= " + l_datExecutionTime);
            
            //getSubAccountId()に該当する補助口座
            WEB3GentradeSubAccount l_subAccount = 
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_feqFinTransactionParams.getAccountId(), 
                    l_lngSubAccountId);
            
            //市場：　@getMarketId()に該当する市場
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_lngMarketId);
            
            //基準日：注文単位．発注日
            Date l_datBaseDate = WEB3DateUtility.getDate(l_feqOrderUnitRow.getBizDate(), "yyyyMMdd");   
            
            //is買付：getFinTransactionType() == ”外株買い”の場合true。以外、false。
            boolean l_blnIsBuy = false;           
            if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_transactionType))
            {
                l_blnIsBuy = true;
            }
            
            //is指値：注文単位行.指値 == 0の場合false。以外、true。
            boolean l_blnIsPrice = false;
            if (l_feqOrderUnitRow.getLimitPrice() != 0)
            {
                l_blnIsPrice = true;
            }
            
            //1.11外国株式金額計算を行う。
            WEB3FeqAmountCalcResult l_amountCalcResult = l_bizlogicProvider.calcFeqAmount(
                l_subAccount, 
                l_feqProduct, 
                l_market, 
                l_datBaseDate, 
                l_datExecutionTime,
                l_dblExecutionAmount, 
                l_dblFxRate, 
                l_blnIsBuy, 
                true,
                l_blnIsPrice,
                l_feqOrderUnitRow.getOrderChanel());
            
            double l_dblprofitLoss = 0.0D;
            double l_dblcapitalGainTax = 0.0D;
            double l_dblProfitLossFc = 0.0D;
            double l_dblCapitalGainTaxFc = 0.0D;
            //1.12売の場合（ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ=="外株売り"）、譲渡損益／譲渡益税を計算する
            if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_transactionType))
            {
                //1.122税区分を取得する。
                TaxTypeEnum l_taxType = l_feqFinTransactionParams.getTaxType();
                
                //1.123受渡日を取得する。
                Date l_datDeliveryDate = l_feqFinTransactionParams.getDeliveryDate();
                log.debug("受渡日= " + l_datDeliveryDate);
                
                //1.124譲渡損益を計算する。
                l_dblprofitLoss = l_bizlogicProvider.calcCapitalProfitLoss(
                    l_amountCalcResult.getNetAmount(),
                    l_dblExecutionQuantity, 
                    l_lngProductId, 
                    l_subAccount, 
                    l_taxType);
                log.debug("譲渡損益= " + l_dblprofitLoss);
                
                //1.125譲渡益税を計算する。
                l_dblcapitalGainTax = l_bizlogicProvider.calcCapitalGainTax(
                    l_subAccount,
                    l_taxType, 
                    l_dblprofitLoss, 
                    l_datDeliveryDate);
                log.debug("譲渡益税= " + l_dblcapitalGainTax);
                
                l_dblProfitLossFc =
                    l_bizlogicProvider.calcForeignCCYAmount(
                        l_dblprofitLoss,
                        l_dblFxRate,
                        l_feqProduct.getProductId(),
                        false,
                        true);
                log.debug("譲渡益金額（外貨）= " + l_dblProfitLossFc);
                
                l_dblCapitalGainTaxFc =
                    l_bizlogicProvider.calcForeignCCYAmount(
                        l_dblcapitalGainTax,
                        l_dblFxRate,
                        l_feqProduct.getProductId(),
                        false,
                        true);
                log.debug("譲渡益税額（外貨）= " + l_dblCapitalGainTaxFc);
            }
            
            //1.13行オブジェクトプロパティに値をセットする。
            //(*) プロパティセット
            //【ｘTrade】補足資料.DB更新\\11.管理者・出来入力
            //「外株出来_トランザクション（取引勘定明細）仕様.xls#外株出来_取引勘定明細DB更新（今回約定）」
            //参照。
            FeqOrderExecutionRow l_executionRow = 
                (FeqOrderExecutionRow)l_feqOrderExecution.getDataSourceObject();
            
            //トランザクション発生日時 = 約定.約定日時
            l_feqFinTransactionParams.setFinTransactionTimestamp(l_executionRow.getExecTimestamp());
            
            //約定.決済区分
            l_feqFinTransactionParams.setSettleDiv(l_executionRow.getSettleDiv());
            
            //約定.発注日
            l_feqFinTransactionParams.setBizDate(l_executionRow.getBizDate());
            
            //買約定（ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ=”外株買い”）の場合、以下の計算結果*) 受渡代金×（-1）
            if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_transactionType))
            {
                l_feqFinTransactionParams.setNetAmount((l_amountCalcResult.getNetAmount() * -1));
                //買約定（ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ=”外株買い”）の場合、以下の計算結果*) 受渡代金（外貨）×（-1）
                l_feqFinTransactionParams.setNetAmountFc((l_amountCalcResult.getNetAmountFc() * -1));
                //買約定（ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ=”外株買い”）の場合、０。
                l_feqFinTransactionParams.setCapitalGain(0.0D);
                //買約定（ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ=”外株買い”）の場合、０。
                l_feqFinTransactionParams.setCapitalGainTax(0.0D);
                
                l_feqFinTransactionParams.setCapitalGainFc(0.0D);
                l_feqFinTransactionParams.setCapitalGainTaxFc(0.0D);
            }
            //売約定（ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ=”外株売り”）の場合、*)受渡代金
            else if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_transactionType))
            {
                l_feqFinTransactionParams.setNetAmount(l_amountCalcResult.getNetAmount());
                //売約定（ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ=”外株売り”）の場合、*) 受渡代金（外貨）
                l_feqFinTransactionParams.setNetAmountFc(l_amountCalcResult.getNetAmountFc());
                //売約定（ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ=”外株売り”）の場合、譲渡損益（calc譲渡損益()の戻り値）。
                l_feqFinTransactionParams.setCapitalGain(l_dblprofitLoss);
                //売約定（ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ=”外株売り”）の場合、譲渡益税（calc譲渡益税()の戻り値）。
                l_feqFinTransactionParams.setCapitalGainTax(l_dblcapitalGainTax);
                
                l_feqFinTransactionParams.setCapitalGainFc(l_dblProfitLossFc);
                l_feqFinTransactionParams.setCapitalGainTaxFc(l_dblCapitalGainTaxFc);
            }
            
            //*）　@委託手数料
            l_feqFinTransactionParams.setCommissionFee(l_amountCalcResult.getCommissionFee());
            //*）　@委託手数料消費税
            l_feqFinTransactionParams.setCommissionFeeTax(l_amountCalcResult.getCommisionFeeTax());
            //*）　@登録No
            l_feqFinTransactionParams.setRegNo(
                l_amountCalcResult.getCommissionNumber() + l_amountCalcResult.getCommissionBranchNumber());
            //*）　@徴収率
            l_feqFinTransactionParams.setChargeRatio(l_amountCalcResult.getChargeRatio());
            //*）　@現地清算代金（円貨）
            l_feqFinTransactionParams.setBalanceAmount(l_amountCalcResult.getBalanceAmount());
            //*）　@委託手数料（外貨）
            l_feqFinTransactionParams.setCommissionFeeFc(l_amountCalcResult.getCommissionFeeFc());
            //*）　@委託手数料消費税（外貨）
            l_feqFinTransactionParams.setCommissionFeeTaxFc(l_amountCalcResult.getCommisionFeeTaxFc());
            //*）　@現地清算代金（外貨）
            l_feqFinTransactionParams.setBalanceAmountFc(l_amountCalcResult.getBalanceAmountFc());
            //*）　@現地手数料
            l_feqFinTransactionParams.setForeignCommissionFee(l_amountCalcResult.getForignCommissionFee());
            //*）　@現地取引税
            l_feqFinTransactionParams.setForeignTax(l_amountCalcResult.getForeignTax());
            //*）　@その他コスト１
            l_feqFinTransactionParams.setForeignFeeExt1(l_amountCalcResult.getForeignFeeExt1());
            //*）　@その他コスト２
            l_feqFinTransactionParams.setForeignFeeExt2(l_amountCalcResult.getForeignFeeExt2());
            //約定.約定経路区分
            l_feqFinTransactionParams.setOrderExecRouteDiv(l_executionRow.getOrderExecRouteDiv());
            //約定.更新者コード
            l_feqFinTransactionParams.setLastUpdater(l_executionRow.getLastUpdater());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch (DataNetworkException l_ex)        
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName()+  "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

    }
    
    /**
     * (createトランザクション行) <BR>
     * 注文単位行／約定行より、トランザクション行オブジェクトを生成する。 <BR>
     *  <BR>
     * デフォルトコンストラクタにてトランザクション（取引勘定明細）行 <BR>
     * オブジェクトを生成し、 <BR>
     * this.set約定情報Toトランザクション() <BR>
     * にてトランザクション行オブジェクトに値をセットする。 <BR>
     *  <BR>
     * [set約定情報Toトランザクション()に指定する引数] <BR>
     * トランザクション（取引勘定明細）行：　@生成したオブジェクト <BR>
     * 約定：　@約定 <BR>
     * 注文単位行：　@注文単位行 <BR>
     * @@param l_feqOrderExecution - (約定) <BR>
     * 約定オブジェクト
     * @@param l_feqOrderUnitRow - (注文単位行) <BR>
     * 注文単位行オブジェクト
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams
     * @@throws WEB3BaseException
     * @@roseuid 42B25DBB01AF
     */
    public FeqFinTransactionParams createTransactionParams(
        FeqOrderExecution l_feqOrderExecution, 
        FeqOrderUnitRow l_feqOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createTransactionParams(FeqOrderExecution,FeqOrderUnitRow)";    
        log.entering(STR_METHOD_NAME);
        
        //注文単位行／約定行より、トランザクション行オブジェクトを生成する。
        FeqFinTransactionParams l_finTransactionParams = new FeqFinTransactionParams();
        this.setExecutionInfoToMarketOrderedTrans(l_finTransactionParams, l_feqOrderExecution, l_feqOrderUnitRow);
        
        log.exiting(STR_METHOD_NAME);
        return l_finTransactionParams;
    }
    
    /**
     * (updateトランザクション) <BR>
     * 手数料按分計算（一口約定）を実施し、トランザクションデータを更新する。  <BR>
     *  <BR>
     * シーケンス図  <BR>
     * 「（外株残高）updateトランザクション」参照。  <BR>
     * @@param l_lngOrderUnitId - (注文単位ＩＤ)
     * @@param l_blnIsCancel - (is取消) <BR>
     * 約定取消かの判定 <BR>
     *  <BR>
     * ture：約定取消 <BR>
     * false：約定 <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4288361E022D
     */
    public void updateTransaction(long l_lngOrderUnitId, boolean l_blnIsCancel) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateTransaction(long,boolean)";    
        log.entering(STR_METHOD_NAME);
        
        //外国株式更新データマネージャを取得する。 
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_bizlogicProvider = (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        try
        {
            //1.1同一注文に関連する既約定の取引勘定明細をリストで取得する。  
            List l_lisForOrderUnit = l_dataManager.getFinTransactionForOrderUnit(l_lngOrderUnitId);
            
            //1.2該当データなし、または新規約定（is取消 == false） && 初回約定の場合（get取引勘定明細ForOrderUnit().size() == 1）、
            //   処理を終了する。
            int l_intOrderUnitSize = 0;
            if (l_lisForOrderUnit!= null && !l_lisForOrderUnit.isEmpty())
            {
                l_intOrderUnitSize = l_lisForOrderUnit.size();
            }

            if (l_intOrderUnitSize == 0)
            {
                return;
            }
            if (!l_blnIsCancel && l_intOrderUnitSize == 1)
            {
                return;
            }
            
            if (l_lisForOrderUnit == null || l_lisForOrderUnit.isEmpty())
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //1.3外国株式金額按分計算を行う。
            FeqFinTransactionParams[] l_fransactionParams = new FeqFinTransactionParams[l_intOrderUnitSize];
            l_lisForOrderUnit.toArray(l_fransactionParams);
            
            WEB3FeqAmountCalcResultFactor l_calcResultFactor = l_bizlogicProvider.calcFeqAmountFactor(l_fransactionParams);
            //1.4外国株式計算結果（合計）を取得する。
            WEB3FeqAmountCalcResult l_calcResult = l_calcResultFactor.getFeqAmountCalcResultTotal();
            
            //1.5取引勘定明細List（get取引勘定明細ForOrderUnit()の戻り値）各要素毎のLOOP処理 
            for (int i = 0; i < l_intOrderUnitSize; i++)
            {
                //更新前のトランザクション（取引勘定明細）行.受渡代金を取得する。
                double l_dblNetAmountBeforeUpdate = l_fransactionParams[i].getNetAmount();

                //1.5.1委託手数料を取得する。 
                double l_dblCommisionFee = l_calcResultFactor.getCommisionFee(i);
                log.debug("委託手数料= " + l_dblCommisionFee);
                
                //1.5.2委託手数料消費税を取得する。 
                double l_dblCommisionFeeTax = l_calcResultFactor.getCommisionFeeTax(i);
                log.debug("委託手数料消費税= " + l_dblCommisionFeeTax);
                
                //1.5.3現地清算代金（円貨）を取得する。 
                double l_dblBalanceAmount = l_calcResultFactor.getBalanceAmount(i);
                log.debug("現地清算代金（円貨）= " + l_dblBalanceAmount);
                
                //1.5.4get委託手数料（外貨）(
                double l_dblCommisionFeeFc = l_calcResultFactor.getCommisionFeeFc(i);
                log.debug("委託手数料（外貨）= " + l_dblCommisionFeeFc);
                
                //1.5.5get委託手数料消費税（外貨）(
                double l_dblCommisionFeeTaxFc = l_calcResultFactor.getCommisionFeeTaxFc(i);
                log.debug("委託手数料消費税（外貨）= " + l_dblCommisionFeeTaxFc);
                
                //1.5.6現地清算代金を取得する。 
                double l_dblBalanceAmountFc = l_calcResultFactor.getBalanceAmountFc(i);
                log.debug("現地清算代金 = " + l_dblBalanceAmountFc);
                
                //1.5.7現地手数料を取得する。 
                double l_dblForeignCommissionFee = l_calcResultFactor.getForeignCommissionFee(i);
                log.debug("現地手数料 = " + l_dblForeignCommissionFee);
                
                //1.5.8現地取引税を取得する。
                double l_dblForeignTax = l_calcResultFactor.getForeignTax(i);
                log.debug("現地取引税 = " + l_dblForeignTax);
                
                //1.5.9その他コスト１を取得する。 
                double l_dblForeignFeeExt1 = l_calcResultFactor.getForeignFeeExt1(i);
                log.debug("その他コスト１ = " + l_dblForeignFeeExt1);
                
                //1.5.10その他コスト２を取得する。 
                double l_dblForeignFeeExt2 = l_calcResultFactor.getForeignFeeExt2(i);
                log.debug("その他コスト２ = " + l_dblForeignFeeExt2);
                
                double l_dblNetAmount = l_calcResultFactor.getNetAmount(i);
                log.debug("受渡代金 = " + l_dblNetAmount);
                double l_dblNetAmountFc = l_calcResultFactor.getNetAmountFc(i);
                log.debug("受渡代金（外貨） = " + l_dblNetAmountFc);
                
                //1.5.11トランザクションタイプを取得する。 
                FinTransactionType l_finTransactionType = l_fransactionParams[i].getFinTransactionType(); 
                
                //譲渡損益
                double l_dblProfitLoss = 0.0D;
                //譲渡益税
                double l_dblCapitalGainTax = 0.0D;
                //譲渡益金額（外貨）
                double l_dblProfitLossFc = 0.0D;
                //譲渡益税額（外貨）
                double l_dblCapitalGainTaxFc = 0.0D;
                
                //補助口座ＩＤを取得する。
                long l_lngSubAccountId = l_fransactionParams[i].getSubAccountId();
                log.debug("補助口座ＩＤ = " + l_lngSubAccountId);

                //getSubAccountId()に該当する補助口座
                WEB3GentradeSubAccount l_subAccount =
                    (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                        l_fransactionParams[i].getAccountId(), 
                        l_lngSubAccountId);

                //1.5.12売の場合（ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ=="外株売り"）、譲渡損益／譲渡益税を計算する
                if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_finTransactionType))
                {
                    //1.5.12.2約定数量を取得する。
                    double l_dblQuantity = l_fransactionParams[i].getQuantity();
                    log.debug("約定数量 = " + l_dblQuantity);
                    
                    //1.5.12.32銘柄ＩＤを取得する。
                    long l_lngProductId = l_fransactionParams[i].getProductId();
                    log.debug("銘柄ＩＤ = " + l_lngProductId);
                    

                    
                    //1.5.12.5税区分を取得する。
                    TaxTypeEnum l_taxTypeEnum = l_fransactionParams[i].getTaxType();
                    
                    //1.5.12.6譲渡損益を計算する。 
                    l_dblProfitLoss = 
                        l_bizlogicProvider.calcCapitalProfitLoss(
                            l_dblNetAmount, 
                            l_dblQuantity, 
                            l_lngProductId, 
                            l_subAccount, 
                            l_taxTypeEnum);
                    log.debug("譲渡損益 = " + l_dblProfitLoss);
                    
                    //1.5.12.7受渡日を取得する。
                    Date l_datDeliveryDate = l_fransactionParams[i].getDeliveryDate();
                    log.debug("受渡日 = " + l_datDeliveryDate);
                    
                    //1.5.12.8譲渡益税を計算する。 
                    l_dblCapitalGainTax = 
                        l_bizlogicProvider.calcCapitalGainTax(l_subAccount, l_taxTypeEnum, l_dblProfitLoss, l_datDeliveryDate);
                    log.debug("譲渡益税 = " + l_dblCapitalGainTax);

                    // calc外貨換算(double, double, long, boolean, boolean)
                    // 譲渡益金額（外貨）を計算する。
                    // 金額（円貨）：　@calc譲渡損益()
                    // 為替レート：　@取引勘定明細トランザクションより取得した為替レート
                    // 銘柄ID：　@getProductId()
                    // is買付：　@false
                    // is約定計算：　@true
                    double l_dbFxRate = l_fransactionParams[i].getFxRate();
                    l_dblProfitLossFc =
                        l_bizlogicProvider.calcForeignCCYAmount(
                            l_dblProfitLoss,
                            l_dbFxRate,
                            l_lngProductId,
                            false,
                            true);
                    log.debug("譲渡益金額（外貨）= " + l_dblProfitLossFc);

                    // calc外貨換算(double, double, long, boolean, boolean)
                    // 譲渡益金額（外貨）を計算する。
                    // 金額（円貨）：　@calc譲渡益税()
                    // 為替レート：　@取引勘定明細トランザクションより取得した為替レート
                    // 銘柄ID：　@getProductId()
                    // is買付：　@false
                    // is約定計算：　@true
                    l_dblCapitalGainTaxFc =
                        l_bizlogicProvider.calcForeignCCYAmount(
                            l_dblCapitalGainTax,
                            l_dbFxRate,
                            l_lngProductId,
                            false,
                            true);
                    log.debug("譲渡益税額（外貨）= " + l_dblCapitalGainTaxFc);
                }
                
                //1.5.13行オブジェクトプロパティに値をセットする。
                if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_finTransactionType))
                {
                    l_fransactionParams[i].setNetAmount(l_dblNetAmount * -1);
                    l_fransactionParams[i].setNetAmountFc(l_dblNetAmountFc * -1);
                    l_fransactionParams[i].setCapitalGain(0);
                    l_fransactionParams[i].setCapitalGainTax(0);
                    l_fransactionParams[i].setCapitalGainFc(0);
                    l_fransactionParams[i].setCapitalGainTaxFc(0);
                }

                else if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_finTransactionType))
                {
                    l_fransactionParams[i].setNetAmount(l_dblNetAmount);
                    l_fransactionParams[i].setNetAmountFc(l_dblNetAmountFc);
                    l_fransactionParams[i].setCapitalGain(l_dblProfitLoss);
                    l_fransactionParams[i].setCapitalGainTax(l_dblCapitalGainTax);
                    l_fransactionParams[i].setCapitalGainFc(l_dblProfitLossFc);
                    l_fransactionParams[i].setCapitalGainTaxFc(l_dblCapitalGainTaxFc);
                }
                
                //一口按分後の委託手数料
                l_fransactionParams[i].setCommissionFee(l_dblCommisionFee);
                //一口按分後の委託手数料消費税
                l_fransactionParams[i].setCommissionFeeTax(l_dblCommisionFeeTax);
                //一口按分後の登録No
                l_fransactionParams[i].setRegNo(
                    l_calcResult.getCommissionNumber() + l_calcResult.getCommissionBranchNumber());
                //一口按分後の徴収率
                l_fransactionParams[i].setChargeRatio(l_calcResult.getChargeRatio());
                //一口按分後の現地清算代金（円貨）
                l_fransactionParams[i].setBalanceAmount(l_dblBalanceAmount);
                //一口按分後の委託手数料（外貨）
                l_fransactionParams[i].setCommissionFeeFc(l_dblCommisionFeeFc);
                //一口按分後の委託手数料消費税（外貨）
                l_fransactionParams[i].setCommissionFeeTaxFc(l_dblCommisionFeeTaxFc);
                //一口按分後の現地清算代金（外貨）
                l_fransactionParams[i].setBalanceAmountFc(l_dblBalanceAmountFc);
                //一口按分後の現地手数料
                l_fransactionParams[i].setForeignCommissionFee(l_dblForeignCommissionFee);
                //一口按分後の現地取引税
                l_fransactionParams[i].setForeignTax(l_dblForeignTax);
                //一口按分後のその他コスト１
                l_fransactionParams[i].setForeignFeeExt1(l_dblForeignFeeExt1);
                //一口按分後のその他コスト２
                l_fransactionParams[i].setForeignFeeExt2(l_dblForeignFeeExt2);
                //注文単位.更新者コード
                OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitId);
                FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
                l_fransactionParams[i].setLastUpdater(l_orderUnitRow.getLastUpdater());
                //現在日時
                l_fransactionParams[i].setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //1.5.14トランザクション（取引勘定明細）を更新する。 
                Map l_mapValues = new HashMap();
                //受渡代金 
                l_mapValues.put("net_amount", new BigDecimal(l_fransactionParams[i].getNetAmount()));
                //受渡代金（外貨） 
                l_mapValues.put("net_amount_fc", new BigDecimal(l_fransactionParams[i].getNetAmountFc()));
                //委託手数料 
                l_mapValues.put("commission_fee", new BigDecimal(l_fransactionParams[i].getCommissionFee()));
                //委託手数料消費税 
                l_mapValues.put("commission_fee_tax", new BigDecimal(l_fransactionParams[i].getCommissionFeeTax()));
                //登録No
                l_mapValues.put("reg_no", l_fransactionParams[i].getRegNo());
                //徴収率
                l_mapValues.put("charge_ratio", new BigDecimal(l_calcResult.getChargeRatio()));
                //現地清算代金（円貨） 
                l_mapValues.put("balance_amount", new BigDecimal(l_fransactionParams[i].getBalanceAmount()));
                //委託手数料（外貨） 
                l_mapValues.put("commission_fee_fc", new BigDecimal(l_fransactionParams[i].getCommissionFeeFc()));
                //委託手数料消費税（外貨） 
                l_mapValues.put("commission_fee_tax_fc", new BigDecimal(l_fransactionParams[i].getCommissionFeeTaxFc()));
                //現地清算代金（外貨） 
                l_mapValues.put("balance_amount_fc", new BigDecimal(l_fransactionParams[i].getBalanceAmountFc()));
                //現地手数料 
                l_mapValues.put("foreign_commission_fee", new BigDecimal(l_fransactionParams[i].getForeignCommissionFee()));
                //現地取引税 
                l_mapValues.put("foreign_tax", new BigDecimal(l_fransactionParams[i].getForeignTax()));
                //その他コスト１ 
                l_mapValues.put("foreign_fee_ext1", new BigDecimal(l_fransactionParams[i].getForeignFeeExt1()));
                //その他コスト２ 
                l_mapValues.put("foreign_fee_ext2", new BigDecimal(l_fransactionParams[i].getForeignFeeExt2()));
                //譲渡益金額 
                l_mapValues.put("capital_gain", new BigDecimal(l_fransactionParams[i].getCapitalGain()));
                //譲渡益税額 
                l_mapValues.put("capital_gain_tax", new BigDecimal(l_fransactionParams[i].getCapitalGainTax()));
                //譲渡益金額（外貨）
                l_mapValues.put("capital_gain_fc", new BigDecimal(l_fransactionParams[i].getCapitalGainFc()));
                //譲渡益税額（外貨）
                l_mapValues.put("capital_gain_tax_fc", new BigDecimal(l_fransactionParams[i].getCapitalGainTaxFc()));
                //更新者コード 
                l_mapValues.put("last_updater", l_fransactionParams[i].getLastUpdater());
                //更新日付 
                l_mapValues.put("last_updated_timestamp", l_fransactionParams[i].getLastUpdatedTimestamp());
                
                l_dataManager.updateFinTransaction(l_fransactionParams[i], l_mapValues);
                //1.5.15顧客勘定明細，補助口座を更新する。 
                this.notifyGtl(l_fransactionParams[i]);

                boolean l_blnIsDayTradeAdoption = false;
                boolean l_blnIsDayTradeMarket = false;
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket)(l_finApp.getFinObjectManager().getMarket(l_fransactionParams[i].getMarketId()));
                WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
                l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();
                l_blnIsDayTradeMarket = l_market.isDayTradeMarket();

                Object l_obj1 = ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3ThreadLocalSystemAttributePathDef.NETTING_EXCHANGE_FLAG);

                //買付（トランザクション（取引勘定明細）行.ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ==”外株買い”）
                //且つis日計り取引採用()==true 且つ　@is日計り市場()==trueの場合
                if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_finTransactionType)
                    &&( (l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket) || (l_obj1 != null && l_obj1.equals(BooleanEnum.TRUE))))
                {
                    //出来通知時
                    Object l_obj = ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG);
                    if (l_obj != null && l_obj.equals(BooleanEnum.TRUE))
                    {
                        boolean l_blnDayExchange = false;

                        l_blnDayExchange = ((Boolean)ThreadLocalSystemAttributesRegistry.getAttribute(
                            WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG)).booleanValue();
                        if (!l_blnDayExchange)
                        {
                            log.exiting(STR_METHOD_NAME);
                            return;
                        }
                    }

                    //保有資産更新（簿価更新用）(long, double, double)
                    this.assetUpdateNettingExchangeRateAdoption(
                        l_fransactionParams[i].getAssetId(),
                        l_dblNetAmountBeforeUpdate,
                        l_fransactionParams[i].getNetAmount());
                }
            }
  
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (NotFoundException l_ex)
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
     * (updateトランザクション) <BR>
     * 一括対象となるトランザクションについて、 <BR>
     * 手数料按分計算（一口約定）を実施し、トランザクションデータを更新する。  <BR>
     *  <BR>
     * シーケンス図  <BR>
     * 「（外株残高）updateトランザクション（一括）」参照。  <BR>
     * @@param l_lngOrderUnitIds - (注文単位ＩＤの一覧)
     * @@param l_datBaseDate - (基準日)
     * @@param l_blnIsNetting - (isネッティング処理)
     * @@throws WEB3BaseException 
     * @@roseuid 42B672ED0219
     */
    public void updateTransaction(
        long[] l_lngOrderUnitIds,
        Date l_datBaseDate,
        boolean l_blnIsNetting) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateTransaction(long[], Date, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_lngOrderUnitIds == null || l_lngOrderUnitIds.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //外国株式更新データマネージャを取得する。 
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_bizlogicProvider = (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        
        try
        {
            //1.1手数料一括対象となる既約定の取引勘定明細をリストで取得する。
            List l_lisLumpObj = l_dataManager.getFinTransactionForLumpObj(l_lngOrderUnitIds);
            //1.2該当データなし または 初回約定の場合（get取引勘定明細For一括対象().size() == 1）、処理を終了する。
            if (l_lisLumpObj == null || l_lisLumpObj.isEmpty() || l_lisLumpObj.size() == 1 )
            {
                return;
            }
            //1.3一括対象となるトランザクションを格納するArrayListを生成する。
            List listObj = new ArrayList();
            //1.4get取引勘定明細For一括対象()の戻り値の要素数分Loop処理
            for (int i = 0; i < l_lisLumpObj.size(); i++)
            {
                //1.4.1add(arg0 : Object)
                FeqFinTransactionRow l_finTransactionRow = (FeqFinTransactionRow) l_lisLumpObj.get(i);
                log.debug("FinTransactionId = " + l_finTransactionRow.getFinTransactionId());
                log.debug("OrderUnitId = " + l_finTransactionRow.getOrderUnitId());
                listObj.add(l_finTransactionRow);
                
                //1.4.2(*)以下の条件のいずれかに該当する場合、手数料一括処理を実施する。
                //①@現在処理している要素が最後の要素の場合
                //②次の要素(index + 1)と、今回の要素について、一括対象キー(*1)が
                //異なった場合
                boolean l_blnIsNextFactor = false;
                if ((i + 1) < l_lisLumpObj.size())
                {
                    FeqFinTransactionRow l_finTransactionRowNext = (FeqFinTransactionRow) l_lisLumpObj.get(i + 1);
                    //(*1)一括対象キー・・・以下の値を文字列連結したもの。
                    //トランザクション.銘柄ID + トランザクション.トランザクションタイプ
                    //+  トランザクション.決済区分 + トランザクション.発注日
                    StringBuffer l_strLumpObj = new StringBuffer();
                    l_strLumpObj.append(l_finTransactionRow.getProductId());
                    l_strLumpObj.append(l_finTransactionRow.getFinTransactionType().toString());
                    l_strLumpObj.append(l_finTransactionRow.getSettleDiv());
                    l_strLumpObj.append(l_finTransactionRow.getBizDate());
    
                    StringBuffer l_strLumpObjNext = new StringBuffer();
                    l_strLumpObjNext.append(l_finTransactionRowNext.getProductId());
                    l_strLumpObjNext.append(l_finTransactionRowNext.getFinTransactionType().toString());
                    l_strLumpObjNext.append(l_finTransactionRowNext.getSettleDiv());
                    l_strLumpObjNext.append(l_finTransactionRowNext.getBizDate());
                    
                    if(!(l_strLumpObj.toString().equals(l_strLumpObjNext.toString())))
                    {
                        l_blnIsNextFactor = true;
                    }
                }
                boolean l_blnIsLastFactor = false; 
                if ((i + 1) == l_lisLumpObj.size())
                {
                    l_blnIsLastFactor = true;
                }
                
                if (l_blnIsLastFactor || l_blnIsNextFactor)
                {
                    //1.4.2.1同一注文チェック
                    boolean l_blnIsOrderUnit = false;
                    
                    Map l_mapOrderUnitId = new HashMap();
                    for (int k = 0; k < listObj.size(); k++)
                    {
                        FeqFinTransactionRow l_row = (FeqFinTransactionRow)listObj.get(k);
                        Long l_lngOrderUnitId = new Long(l_row.getOrderUnitId());
                        l_mapOrderUnitId.put(l_lngOrderUnitId, l_lngOrderUnitId);
                    }
                    if (l_mapOrderUnitId.size() == 1)
                    {
                        l_blnIsOrderUnit = true;
                    }
    
                    
                    if (l_blnIsOrderUnit)
                    {
                        listObj.clear();
                        continue;
                    }
                    //1.4.2.2一括対象となるトランザクション（取引勘定明細）の配列を生成する。
                    FeqFinTransactionParams[] l_fransactionParams = new FeqFinTransactionParams[listObj.size()];
                    listObj.toArray(l_fransactionParams);
                    //1.4.2.3外国株式金額按分計算を行う。 
                    WEB3FeqAmountCalcResultFactor l_resultFactor = l_bizlogicProvider.calcFeqAmountFactor(l_fransactionParams);
                    //1.4.2.4外国株式計算結果（合計）を取得する。
                    WEB3FeqAmountCalcResult l_calcResult = l_resultFactor.getFeqAmountCalcResultTotal();
                                        
                    //1.4.2.5toArray()の戻り値各要素毎のLOOP処理
                    for (int j = 0; j < l_fransactionParams.length; j++)
                    {
                        double l_dblNetAmountBeforeUpdate = l_fransactionParams[j].getNetAmount();
                        //1.4.2.5.1委託手数料を取得する。 
                        double l_dblCommisionFee = l_resultFactor.getCommisionFee(j);
                        log.debug("委託手数料 = " + l_dblCommisionFee);
                        //1.4.2.5.2委託手数料消費税を取得する。 
                        double l_dblCommisionFeeTax = l_resultFactor.getCommisionFeeTax(j);
                        log.debug("委託手数料消費税 = " + l_dblCommisionFeeTax);
                        //1.4.2.5.3現地清算代金（円貨）を取得する。 
                        double l_dblBalanceAmount = l_resultFactor.getBalanceAmount(j);
                        log.debug("現地清算代金（円貨） = " + l_dblBalanceAmount);
                        //1.4.2.5.4get委託手数料（外貨）(
                        double l_dblCommisionFeeFc = l_resultFactor.getCommisionFeeFc(j);
                        log.debug("委託手数料（外貨） = " + l_dblCommisionFeeFc);
                        //1.4.2.5.5get委託手数料消費税（外貨）(
                        double l_dblCommisionFeeTaxFc = l_resultFactor.getCommisionFeeTaxFc(j);
                        log.debug("委託手数料消費税（外貨） = " + l_dblCommisionFeeTaxFc);
                        //1.4.2.5.6現地清算代金を取得する。 
                        double l_dblBalanceAmountFc = l_resultFactor.getBalanceAmountFc(j);
                        log.debug("現地清算代金 = " + l_dblBalanceAmountFc);
                        //1.4.2.5.7現地手数料を取得する。 
                        double l_dblForeignCommissionFee = l_resultFactor.getForeignCommissionFee(j);
                        log.debug("現地手数料 = " + l_dblForeignCommissionFee);
                        //1.4.2.5.8現地取引税を取得する。
                        double l_dblForeignTax = l_resultFactor.getForeignTax(j);
                        log.debug("現地取引税 = " + l_dblForeignTax);
                        //1.4.2.5.9その他コスト１を取得する。 
                        double l_dblForeignFeeExt1 = l_resultFactor.getForeignFeeExt1(j);
                        log.debug("その他コスト１ = " + l_dblForeignFeeExt1);
                        //1.4.2.5.10その他コスト２を取得する。 
                        double l_dblForeignFeeExt2 = l_resultFactor.getForeignFeeExt2(j);
                        log.debug("その他コスト２ = " + l_dblForeignFeeExt2);
                        double l_dblNetAmount = l_resultFactor.getNetAmount(j);
                        log.debug("受渡代金 = " + l_dblNetAmount);
                        double l_dblNetAmountFc = l_resultFactor.getNetAmountFc(j);
                        log.debug("受渡代金（外貨） = " + l_dblNetAmountFc);
                        //1.4.2.5.11トランザクションタイプを取得する。 
                        FinTransactionType l_finTransactionType = l_fransactionParams[j].getFinTransactionType();
    
                        double l_dblProfitLoss = 0.0D;
                        double l_dblCapitalGainTax = 0.0D;
                        double l_dblProfitLossFc = 0.0D;
                        double l_dblCapitalGainTaxFc = 0.0D;
                        //補助口座ＩＤを取得する。
                        long l_lngSubAccountId = l_fransactionParams[j].getSubAccountId();
                        log.debug("補助口座ＩＤ = " + l_lngSubAccountId);
                        //getSubAccountId()に該当する補助口座
                        WEB3GentradeSubAccount l_subAccount = 
                            (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                                l_fransactionParams[j].getAccountId(), 
                                l_lngSubAccountId);
                        //1.4.2.5.12売の場合（ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ=="外株売り"）、譲渡損益／譲渡益税を計算する
                        if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_finTransactionType))
                        {
                            //1.4.2.5.12.2約定数量を取得する。
                            double l_dblQuantity = l_fransactionParams[j].getQuantity();
                            log.debug("約定数量 = " + l_dblQuantity);
                            //1.4.2.5.12.3銘柄ＩＤを取得する。
                            long l_lngProductId = l_fransactionParams[j].getProductId();
                            log.debug("銘柄ＩＤ = " + l_lngProductId);

                            //1.4.2.5.12.5税区分を取得する。
                            TaxTypeEnum l_taxTypeEnum = l_fransactionParams[j].getTaxType();
                            //1.4.2.5.12.6譲渡損益を計算する。 
                            l_dblProfitLoss = 
                                l_bizlogicProvider.calcCapitalProfitLoss(
                                    l_dblNetAmount, 
                                    l_dblQuantity, 
                                    l_lngProductId, 
                                    l_subAccount, 
                                    l_taxTypeEnum);
                            log.debug("譲渡損益 = " + l_dblProfitLoss);
                            //1.4.2.5.12.7受渡日を取得する。
                            Date l_datDeliveryDate = l_fransactionParams[j].getDeliveryDate();
                            log.debug("受渡日 = " + l_datDeliveryDate);
                            //1.4.2.5.12.8譲渡益税を計算する。 
                            l_dblCapitalGainTax = 
                                l_bizlogicProvider.calcCapitalGainTax(l_subAccount, l_taxTypeEnum, l_dblProfitLoss, l_datDeliveryDate);
                            log.debug("譲渡益税 = " + l_dblCapitalGainTax);
                            double l_dbFxRate = l_fransactionParams[j].getFxRate();
                            l_dblProfitLossFc =
                                l_bizlogicProvider.calcForeignCCYAmount(
                                    l_dblProfitLoss,
                                    l_dbFxRate,
                                    l_lngProductId,
                                    false,
                                    true);
                            log.debug("譲渡益金額（外貨）= " + l_dblProfitLossFc);
                
                            l_dblCapitalGainTaxFc =
                                l_bizlogicProvider.calcForeignCCYAmount(
                                    l_dblCapitalGainTax,
                                    l_dbFxRate,
                                    l_lngProductId,
                                    false,
                                    true);
                            log.debug("譲渡益税額（外貨）= " + l_dblCapitalGainTaxFc);
                        }
                        
                        //1.4.2.5.13(*) プロパティセット
                        //【ｘTrade】補足資料.DB更新\\20.（管）出来終了
                        //「外株出来終了_トランザクション（取引勘定明細）仕様.xls」参照。

                        if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_finTransactionType))
                        {
                            l_fransactionParams[j].setNetAmount(l_dblNetAmount * -1);
                            l_fransactionParams[j].setNetAmountFc(l_dblNetAmountFc * -1);
                            l_fransactionParams[j].setCapitalGain(0);
                            l_fransactionParams[j].setCapitalGainTax(0);
                            l_fransactionParams[j].setCapitalGainFc(0);
                            l_fransactionParams[j].setCapitalGainTaxFc(0);
                        }

                        else if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_finTransactionType))
                        {
                            l_fransactionParams[j].setNetAmount(l_dblNetAmount);
                            l_fransactionParams[j].setNetAmountFc(l_dblNetAmountFc);
                            l_fransactionParams[j].setCapitalGain(l_dblProfitLoss);
                            l_fransactionParams[j].setCapitalGainTax(l_dblCapitalGainTax);
                            l_fransactionParams[j].setCapitalGainFc(l_dblProfitLossFc);
                            l_fransactionParams[j].setCapitalGainTaxFc(l_dblCapitalGainTaxFc);
                        }
                        
                        //一口按分後の委託手数料
                        l_fransactionParams[j].setCommissionFee(l_dblCommisionFee);
                        //一口按分後の委託手数料消費税
                        l_fransactionParams[j].setCommissionFeeTax(l_dblCommisionFeeTax);
                        //一口按分後の登録No
                        l_fransactionParams[j].setRegNo(
                            l_calcResult.getCommissionNumber() + l_calcResult.getCommissionBranchNumber());
                        //一口按分後の徴収率
                        l_fransactionParams[j].setChargeRatio(l_calcResult.getChargeRatio());
                        //一口按分後の現地清算代金（円貨）
                        l_fransactionParams[j].setBalanceAmount(l_dblBalanceAmount);
                        //一口按分後の委託手数料（外貨）
                        l_fransactionParams[j].setCommissionFeeFc(l_dblCommisionFeeFc);
                        //一口按分後の委託手数料消費税（外貨）
                        l_fransactionParams[j].setCommissionFeeTaxFc(l_dblCommisionFeeTaxFc);
                        //一口按分後の現地清算代金（外貨）
                        l_fransactionParams[j].setBalanceAmountFc(l_dblBalanceAmountFc);
                        //一口按分後の現地手数料
                        l_fransactionParams[j].setForeignCommissionFee(l_dblForeignCommissionFee);
                        //一口按分後の現地取引税
                        l_fransactionParams[j].setForeignTax(l_dblForeignTax);
                        //一口按分後のその他コスト１
                        l_fransactionParams[j].setForeignFeeExt1(l_dblForeignFeeExt1);
                        //一口按分後のその他コスト２
                        l_fransactionParams[j].setForeignFeeExt2(l_dblForeignFeeExt2);
                        //約定経路区分
                        //セッションよりログインＩＤを取得、ログインＩＤに該当する管理者.管理者コード。
                        //ログインＩＤが取得できない場合は、null。.更新者コード                        
                        try
                        {
                            OpLoginSecurityService l_opLoginSecurityService =
                                (OpLoginSecurityService)Services.getService(
                                OpLoginSecurityService.class);
                            
                            l_opLoginSecurityService.getLoginId();
                                                        
                            WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
                            l_fransactionParams[j].setLastUpdater(l_administrator.getAdministratorCode());
                        }
                        catch (IllegalSessionStateException l_ex)
                        {
                            l_fransactionParams[j].setLastUpdater(null);
                        }
                        //現在日時
                        l_fransactionParams[j].setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        
                        //1.4.2.5.14トランザクション（取引勘定明細）を更新する。 
                        Map l_mapValues = new HashMap();
                        //受渡代金 
                        l_mapValues.put("net_amount", new BigDecimal(l_fransactionParams[j].getNetAmount()));
                        //受渡代金（外貨） 
                        l_mapValues.put("net_amount_fc", new BigDecimal(l_fransactionParams[j].getNetAmountFc()));
                        //委託手数料 
                        l_mapValues.put("commission_fee", new BigDecimal(l_fransactionParams[j].getCommissionFee()));
                        //委託手数料消費税 
                        l_mapValues.put("commission_fee_tax", new BigDecimal(l_fransactionParams[j].getCommissionFeeTax()));
                        //登録No
                        l_mapValues.put("reg_no", l_fransactionParams[j].getRegNo());
                        //徴収率
                        l_mapValues.put("charge_ratio", new BigDecimal(l_calcResult.getChargeRatio()));
                        //現地清算代金（円貨） 
                        l_mapValues.put("balance_amount", new BigDecimal(l_fransactionParams[j].getBalanceAmount()));
                        //委託手数料（外貨） 
                        l_mapValues.put("commission_fee_fc", new BigDecimal(l_fransactionParams[j].getCommissionFeeFc()));
                        //委託手数料消費税（外貨） 
                        l_mapValues.put("commission_fee_tax_fc", new BigDecimal(l_fransactionParams[j].getCommissionFeeTaxFc()));
                        //現地清算代金（外貨） 
                        l_mapValues.put("balance_amount_fc", new BigDecimal(l_fransactionParams[j].getBalanceAmountFc()));
                        //現地手数料 
                        l_mapValues.put("foreign_commission_fee", new BigDecimal(l_fransactionParams[j].getForeignCommissionFee()));
                        //現地取引税 
                        l_mapValues.put("foreign_tax", new BigDecimal(l_fransactionParams[j].getForeignTax()));
                        //その他コスト１ 
                        l_mapValues.put("foreign_fee_ext1", new BigDecimal(l_fransactionParams[j].getForeignFeeExt1()));
                        //その他コスト２ 
                        l_mapValues.put("foreign_fee_ext2", new BigDecimal(l_fransactionParams[j].getForeignFeeExt2()));
                        //譲渡益金額 
                        l_mapValues.put("capital_gain", new BigDecimal(l_fransactionParams[j].getCapitalGain()));
                        //譲渡益税額 
                        l_mapValues.put("capital_gain_tax", new BigDecimal(l_fransactionParams[j].getCapitalGainTax()));
                        //譲渡益金額（外貨）
                        l_mapValues.put("capital_gain_fc", new BigDecimal(l_fransactionParams[j].getCapitalGainFc()));
                        //譲渡益税額（外貨）
                        l_mapValues.put("capital_gain_tax_fc", new BigDecimal(l_fransactionParams[j].getCapitalGainTaxFc()));
                        //更新者コード 
                        l_mapValues.put("last_updater", l_fransactionParams[j].getLastUpdater());
                        //更新日付 
                        l_mapValues.put("last_updated_timestamp", l_fransactionParams[j].getLastUpdatedTimestamp());
                        
                        l_dataManager.updateFinTransaction(l_fransactionParams[j], l_mapValues);
                        //1.4.2.5.15顧客勘定明細，補助口座を更新する。 
                        this.notifyGtl(l_fransactionParams[j]);

                        boolean l_blnIsDayTradeAdoption = false;
                        boolean l_blnIsDayTradeMarket = false;
                        WEB3GentradeMarket l_market =
                            (WEB3GentradeMarket)(l_finApp.getFinObjectManager().getMarket(l_fransactionParams[j].getMarketId()));
                        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
                        l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();
                        l_blnIsDayTradeMarket = l_market.isDayTradeMarket();

                        // 買付（トランザクション（取引勘定明細）行.ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ==”外株買い”）の場合
                        if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_finTransactionType))
                        {
                            // isネッティング処理==true || (isネッティング処理==false 且つ
                            // is日計り取引採用()==true 且つ　@is日計り市場()==true)の場合
                            if (l_blnIsNetting || (l_blnIsNetting == false
                                && l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket))
                            {
                                //保有資産更新（簿価更新用）(long, double, double)
                                this.assetUpdateNettingExchangeRateAdoption(
                                    l_fransactionParams[j].getAssetId(),
                                    l_dblNetAmountBeforeUpdate,
                                    l_fransactionParams[j].getNetAmount());
                            }
                        }
                    }

                    //1.4.2.6toArray()の戻り値.注文単位IDのうち、ユニークな注文単位ID全てについてLoop処理
                    for (int k = 0; k < listObj.size(); k++)
                    {
                        FeqFinTransactionRow l_transactionRow = (FeqFinTransactionRow) listObj.get(k);
                        //1.4.2.6.1手数料一括再計算の結果を注文単位、注文履歴に更新する。 
                        WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(l_transactionRow.getOrderUnitId());
                        l_datBaseDate = l_transactionRow.getFinTransactionTimestamp();
                        l_orderManager.updateEstimatedPrice(l_orderUnit, l_datBaseDate);
                    }
                    //1.4.2.7ArrayListの要素をクリアする。
                    listObj.clear();
                }
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataException l_ex)
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
     * (adjust保有資産（一口）) <BR>
     * 一口約定計算後の誤差を、保有資産.簿価 <BR>
     * （簿価単価計算用）に補正する。 <BR>
     *  <BR>
     * １）　@資産ＩＤに該当する保有資産行オブジェクトを取得する。  <BR>
     *  <BR>
     * ２）　@保有資産行.簿価（簿価単価計算用） に以下の計算結果をセットする。  <BR>
     *  <BR>
     * 　@簿価（簿価単価計算用）：　@（既存値）－按分前合計＋按分後合計  <BR>
     *  <BR>
     * ３）　@拡張データマネージャー.updateAssetByTrans()をコールし、 <BR>
     * 保有資産テーブルに保存する。  <BR>
     *  <BR>
     * 　@[updateAssetByTrans()に指定する引数] <BR>
     * 　@保有資産行：　@保有資産行 <BR>
     * @@param l_lngAssetId - (保有資産ＩＤ) <BR>
     * 保有資産ＩＤ
     * @@param l_dblFactorBeforeTotal - (按分前合計) <BR>
     * 按分前合計
     * @@param l_dblFactorAfterCount - (按分後合計) <BR>
     * 按分後合計
     * @@throws WEB3BaseException 
     * @@roseuid 428ACD0B01D6
     */
    protected void adjustAsset(long l_lngAssetId, double l_dblFactorBeforeTotal, double l_dblFactorAfterCount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "adjustAsset(long,double,double)";    
        log.entering(STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqPositionManager l_positionManager = (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
            
            //１）　@資産ＩＤに該当する保有資産行オブジェクトを取得する。            
            FeqAssetImpl l_feqAssetImpl = (FeqAssetImpl)l_positionManager.getAsset(l_lngAssetId);
            
            if (l_feqAssetImpl == null)
            {
                log.error("テーブルに該当するデータがありません。: ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //２）　@保有資産行.簿価（簿価単価計算用） に以下の計算結果をセットする。
            AssetRow l_assetRow = (AssetRow)l_feqAssetImpl.getDataSourceObject();
            
            double l_dblBookValue = 
                l_assetRow.getBookValue() - l_dblFactorBeforeTotal + l_dblFactorAfterCount;
            
            AssetParams l_assetParams = new AssetParams(l_assetRow);
            
            l_assetParams.setBookValue(l_dblBookValue);
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
                (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
            
            //３）　@拡張データマネージャー.updateAssetByTrans()をコールし、保有資産テーブルに保存する。
            l_dataManager.updateAssetByTrans(l_assetParams);
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch (DataNetworkException l_ex)        
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }   
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
    }
    
    /**
     * (reverseAssetUnitPositionByTrans) <BR>
     * （未使用） <BR>
     * 何もせずにreturnする <BR>
     *  <BR>
     * ※簿価単価算出用テーブル（ASSET_UNIT：取得単位保有資産） <BR>
     * 更新プロセス <BR>
     * @@param l_feqFinTransactionParams
     * @@param l_sideEnum
     * @@param l_assetParams
     * @@throws DataException
     * @@roseuid 4292D36802F2
     */
    protected void reverseAssetUnitPositionByTrans(FeqFinTransactionParams l_feqFinTransactionParams, SideEnum l_sideEnum, AssetParams l_assetParams) throws DataException
    {
     
    }
    
    /**
     * (reverse保有資産残高) <BR>
     * （reverseAssetParamsByMarketTradedTransのオーバーライド） <BR>
     * トランザクション（取引勘定明細）行オブジェクトの約定取消内容を、 <BR>
     * 保有資産行に値をセットする。 <BR>
     *  <BR>
     * セットする内容は、以下を参照。 <BR>
     *  <BR>
     * (*) 売の場合（getFinTransactionType() == 外株売） <BR>
     * 　@【ｘTrade】補足資料.DB更新\\13.管理者・出来約定取消 <BR>
     * 　@「外株出来取消_保有資産テーブル仕様.xls＃ <BR>
     *   外株出来取消_保有資産 DB更新（②売Update）」 <BR>
     * @@param l_assetParams - (保有資産行) <BR>
     * 保有資産行オブジェクト
     * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行) <BR>
     * トランザクション（取引勘定明細）行オブジェクト
     * @@throws DataException
     * @@roseuid 4292D41000FE
     */
    protected void reverseAssetParamsByMarketTradedTrans(
        AssetParams l_assetParams, 
        FeqFinTransactionParams l_feqFinTransactionParams) throws DataException
    {
        final String STR_METHOD_NAME = "reverseAssetParamsByMarketTradedTrans(AssetParams, FeqFinTransactionParams)";    
        log.entering(STR_METHOD_NAME);
        
        if (l_feqFinTransactionParams == null || l_assetParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //トランザクション（取引勘定明細）行オブジェクトの約定取消内容を、保有資産行に値をセットする。
        //(*) 売の場合（getFinTransactionType() == 外株売）
        if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_feqFinTransactionParams.getFinTransactionType()))
        {
            //（既存値）＋トランザクション（取引勘定明細）行.約定数量
            l_assetParams.setQuantity(l_assetParams.getQuantity() + l_feqFinTransactionParams.getQuantity());
            //現在日時
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        }
        //※買付　@かつ日計り採用会社　@かつ日計り取引市場の場合（update）
        else if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_feqFinTransactionParams.getFinTransactionType()))
        {
            //（既存値）- トランザクション（取引勘定明細）行.約定数量
            l_assetParams.setQuantity(l_assetParams.getQuantity() - l_feqFinTransactionParams.getQuantity());
            //（既存値）- トランザクション（取引勘定明細）行.約定数量
            l_assetParams.setQuantityForBookValue(l_assetParams.getQuantityForBookValue() - l_feqFinTransactionParams.getQuantity());
            //簿価（簿価単価計算用）
            //（税区分 == "一般"） &&
            //　@（既存値.数量（簿価単価計算用） > 0） &&
            //　@（既存値.簿価（簿価単価計算用） == 0） の場合、（既存値）。
            //以外、（既存値） － 約定取消対象のトランザクション（取引勘定明細）行.受渡代金×（-1）。
            //（※一般口座であっても、簿価が設定されている場合は更新対象とする）
            if (!(TaxTypeEnum.NORMAL.equals(l_assetParams.getTaxType())
                && l_assetParams.getQuantityForBookValue() > 0
                && GtlUtils.Double.isZero(l_assetParams.getBookValue())))
            {
                //（既存値）- トランザクション（取引勘定明細）行.受渡代金×（-1）
                l_assetParams.setBookValue(l_assetParams.getBookValue() - l_feqFinTransactionParams.getNetAmount() * -1);
            }
            //現在日時
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        }
        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * (applyTo保有資産残高Deligate) <BR>
     * 保有資産を更新する。 <BR>
     *  <BR>
     * this.applyTo保有資産残高()に委譲（deligate）する。 <BR>
     * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行) <BR>
     * トランザクション（取引勘定明細）行オブジェクト
     * @@return List
     * @@throws DataException
     * @@roseuid 42B7B1EF001F
     */
    public List applyToAssetBalanceDeligate(FeqFinTransactionParams l_feqFinTransactionParams) 
        throws DataException
    {
        //this.applyTo保有資産残高()に委譲（deligate）する。
        final String STR_METHOD_NAME = "applyToAssetBalanceDeligate(FeqFinTransactionParams)";    
        log.entering(STR_METHOD_NAME);
        
        List l_lisAssetPosition = this.applyToAssetPosition(l_feqFinTransactionParams);
        
        log.exiting(STR_METHOD_NAME);
        return l_lisAssetPosition;
    }
    
    /**
     * (notify顧客勘定Deligate) <BR>
     * 顧客勘定明細，補助口座を更新する。 <BR>
     *  <BR>
     * this.notify顧客勘定()に委譲（deligate）する。 <BR>
     * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行) <BR>
     * トランザクション（取引勘定明細）行オブジェクト
     * @@roseuid 42B7B2F9007D
     */
    public void notifyCashDeligate(FeqFinTransactionParams l_feqFinTransactionParams)
    {
        //this.notify顧客勘定()に委譲（deligate）する。
        final String STR_METHOD_NAME = "notifyCashDeligate(FeqFinTransactionParams)";    
        log.entering(STR_METHOD_NAME);
        
        this.notifyGtl(l_feqFinTransactionParams);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update保有資産) <BR>
     * 保有資産を更新する。 <BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（外株残高）update保有資産」参照。 <BR>
     * @@param l_feqOrderUnit - (外国株式注文単位) <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4288262A00C6
     */
    protected void updateAsset(WEB3FeqOrderUnit l_feqOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateAsset(WEB3FeqOrderUnit)";    
        log.entering(STR_METHOD_NAME);
        
        //1.1is買付()
        boolean l_isBuy = l_feqOrderUnit.isBuy();
        
        //1.2(*)売付注文(is買付() == false)の場合
        if (!l_isBuy)
        {
            //1.2.1(*)return
            return;
        }
        
        //1.3isUnexecuted()
        boolean l_isUnexecuted = l_feqOrderUnit.isUnexecuted();
        
        //1.4.(*)未約定(isUnexecuted() == true)の場合
        if (l_isUnexecuted)
        {
            //1.4.1(*)return
            return;            
        }
        
        //1.5getトランザクション(注文単位 : 外国株式注文単位)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(
            ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqFinTransactionManager l_finTransactionManager = 
            (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
        
        List l_lisTransaction = 
            l_finTransactionManager.getTransactions(l_feqOrderUnit);
        
        log.debug("getトランザクション()の戻り値の要素数 = " + l_lisTransaction.size());
        
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
        
        FeqFinTransactionParams l_feqFinTransactionParams = null;
        AssetParams l_assetParams = null;
        try
        {
            //1.6(*)getトランザクション()の戻り値の要素分、Loop処理
            for(int i = 0; i < l_lisTransaction.size(); i++)
            {
                //1.6.1get保有資産(トランザクション（取引勘定明細）行 : FeqFinTransactionParams)
                l_feqFinTransactionParams = (FeqFinTransactionParams)l_lisTransaction.get(i);
                l_assetParams = l_dataManager.getAsset(l_feqFinTransactionParams);
                
                //1.6.2(*)残高が無い(get保有資産()の戻り値 == nullの場合
                if (l_assetParams == null)
                {
                    //1.6.2.1AssetPrams()
                    l_assetParams = new AssetParams();
                    
                    //1.6.2.2(*)プロパティセット
                    //【ｘTrade】補足資料.DB更新\\16.(管)出来終了 
                    //「外株出来終了_保有資産テーブル仕様.xls#外株出来終了_保有資産 DB更新（①@買Insert）」 
                    //参照。
                    //トランザクション（取引勘定明細）行.口座ＩＤ
                    l_assetParams.setAccountId(l_feqFinTransactionParams.getAccountId());
                    
                    //トランザクション（取引勘定明細）行.補助口座ＩＤ
                    l_assetParams.setSubAccountId(l_feqFinTransactionParams.getSubAccountId());
                    
                    //トランザクション（取引勘定明細）行.銘柄タイプ
                    l_assetParams.setProductType(l_feqFinTransactionParams.getProductType());
                    
                    //トランザクション（取引勘定明細）行.約定数量
                    l_assetParams.setQuantity(l_feqFinTransactionParams.getQuantity());
                    
                    //売付不能数量 = 0
                    l_assetParams.setQuantityCannotSell(0.0D);
                    
                    //トランザクション（取引勘定明細）行.約定数量
                    l_assetParams.setQuantityForBookValue(l_feqFinTransactionParams.getQuantity());
                    
                    //トランザクション（取引勘定明細）行.受渡代金×（-1）
                    l_assetParams.setBookValue((l_feqFinTransactionParams.getNetAmount() * -1));
                    
                    //入力簿価単価 = null
                    l_assetParams.setInputBookValue(null);
                    
                    //簿価単価入力日時 = null
                    l_assetParams.setInputTimestamp(null);
                    
                    //名義書換料 = 0
                    l_assetParams.setSetupFee(0.0D);
                    
                    //名義書換料消費税 = 0
                    l_assetParams.setSetupFeeTax(0.0D);
                    
                    //口座管理費 = 0
                    l_assetParams.setManagementFee(0.0D);
                    
                    //口座管理費消費税 = 0
                    l_assetParams.setManagementFeeTax(0.0D);
                    
                    //トランザクション（取引勘定明細）行.銘柄ＩＤ
                    l_assetParams.setProductId(l_feqFinTransactionParams.getProductId());
                    
                    //トランザクション（取引勘定明細）行.税区分
                    l_assetParams.setTaxType(l_feqFinTransactionParams.getTaxType());
                    
                    //0：DEFAULT（ミニ株でない）
                    l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                    
                    //分配金 = 0
                    l_assetParams.setProfitDistribution(0.0D);
                    
                    //30日未経過残高口数 = 0
                    l_assetParams.setCountBeforePenalty(0.0D);
                    
                    //作成日付 = 現在日時
                    l_assetParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                    //更新日付 = 現在日時
                    l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                    //1.6.2.3saveNewAsset(arg0 : AssetPrams)
                    l_dataManager.saveNewAsset(l_assetParams);
                }
                //1.6.3(*)保有資産が既に存在する場合(get保有資産 != null)の場合
                else
                {
                    //1.6.3.1(*)プロパティセット
                    //【ｘTrade】補足資料.DB更新\\16.(管)出来終了 
                    //「外株出来終了_保有資産テーブル仕様.xls#外株出来終了_保有資産 DB更新（②買Update）」 
                    //参照。
                    //（既存値）＋トランザクション（取引勘定明細）行.約定数量
                    l_assetParams.setQuantity(l_assetParams.getQuantity() +
                        l_feqFinTransactionParams.getQuantity());
                    
                    //（既存値）＋トランザクション（取引勘定明細）行.約定数量
                    l_assetParams.setQuantityForBookValue(l_assetParams.getQuantityForBookValue() +
                        l_feqFinTransactionParams.getQuantity());
                    
                    //税区分 == "一般"かつ数量（簿価単価計算用）> 0かつ簿価（簿価単価計算用）== 0の場合は何もしない
                    if (TaxTypeEnum.NORMAL.equals(l_assetParams.getTaxType()) &&
                        l_assetParams.getQuantityForBookValue() > 0 &&
                        l_assetParams.getBookValue() == 0)
                    {
                        ;
                    }
                    //該当しない場合は更新
                    else
                    {
                        //（既存値）＋トランザクション（取引勘定明細）行.受渡代金×（-1）
                        l_assetParams.setBookValue(l_assetParams.getBookValue() +
                            l_feqFinTransactionParams.getNetAmount() * -1);
                    }
                    
                    //更新日付 = 現在日時
                    l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                    //1.6.3.2updateAssetByTrans(arg0 : AssetPrams)
                    l_dataManager.updateAssetByTrans(l_assetParams);
                }
                
                //1.6.4getAssetId()
                long l_assetId = l_assetParams.getAssetId();
                
                //1.6.5updateFinTransaction(arg0 : FeqFinTransaction arg1 : Map)
                //【ｘTrade】補足資料.DB更新\\16.(管)出来終了 
                //「外株出来終了_トランザクション（取引勘定明細）仕様.xls#
                //外株出来終了_取引勘定明細 DB更新（保有資産作成後）」参照。
                Map l_mapValues = new HashMap();
                
                //保有資産Params.資産ID
                l_mapValues.put("asset_id", new Long(l_assetId));
                
                //更新日付 = 現在日時
                l_mapValues.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                          
                l_dataManager.updateFinTransaction(l_feqFinTransactionParams, l_mapValues);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
                
        } 
        catch (DataNetworkException l_ex)        
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return;
    }
    
    /**
     * (外国株式更新データマネージャ) <BR>
     * 外国株式更新データマネージャ
     */
    public class WEB3FeqUpdateDataManager extends FeqPositionManagerHelper.PersistentDataManager 
    {
        
        /**
         * @@roseuid 42D0DD5F02FD
         */
        public WEB3FeqUpdateDataManager() 
        {
         
        }
        
        /**
         * (get保有資産) <BR>
         * （getAsset()のオーバーライド） <BR>
         * 保有資産行オブジェクトを取得する。 <BR>
         *  <BR>
         * 以下の条件で保有資産テーブルを検索し、 <BR>
         * 該当行オブジェクトを返却する。 <BR>
         *  <BR>
         * 　@[条件] <BR>
         * 　@保有資産.口座ＩＤ = トランザクション（取引勘定明細）行.口座ＩＤ And <BR>
         * 　@保有資産.補助口座ＩＤ =  <BR>
         * 　@　@　@　@トランザクション（取引勘定明細）行.補助口座ＩＤ And <BR>
         * 　@保有資産.銘柄ＩＤ = トランザクション（取引勘定明細）行.銘柄ＩＤ And <BR>
         * 　@保有資産.税区分 = トランザクション（取引勘定明細）行.税区分 <BR>
         *  <BR>
         * 該当行がない場合は、nullを返却する。 <BR>
         * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行)<BR>
         * トランザクション（取引勘定明細）行オブジェクト
         * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams
         * @@throws DataQueryException
         * @@throws DataNetworkException
         * @@roseuid 428854D600B7
         */
        public AssetParams getAsset(FeqFinTransactionParams l_feqFinTransactionParams)throws DataQueryException, DataNetworkException 
        {
            final String STR_METHOD_NAME = "getAsset(FeqFinTransactionParams)";    
            log.entering(STR_METHOD_NAME);
            if (l_feqFinTransactionParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            List l_lisReturnAsset = null;
            
            //データ査詢
            QueryProcessor l_processor = null;
            l_processor = Processors.getDefaultProcessor();
            
            //１）　@トランザクションテーブル検索
            String l_strWhere = " account_id = ? and sub_account_id = ? and product_id = ? and tax_type = ? ";
            Object[] l_strBindValue = new Object[4];
            l_strBindValue[0] = new Long(l_feqFinTransactionParams.getAccountId());
            l_strBindValue[1] = new Long(l_feqFinTransactionParams.getSubAccountId());
            l_strBindValue[2] = new Long(l_feqFinTransactionParams.getProductId());
            l_strBindValue[3] = l_feqFinTransactionParams.getTaxType();            
            l_lisReturnAsset = l_processor.doFindAllQuery(AssetRow.TYPE,
                l_strWhere, l_strBindValue);

            AssetParams l_assetParams = null;
            if (l_lisReturnAsset != null && !l_lisReturnAsset.isEmpty())
            {
                l_assetParams = (AssetParams)l_lisReturnAsset.get(0);
            }
            log.exiting(STR_METHOD_NAME);  
            return l_assetParams;
        }
        
        /**
         * (get取引勘定明細ForOrderUnit) <BR>
         * （getFinTransactionForOrderUnit） <BR>
         * 同一注文に関連する既約定の取引勘定明細をリストで取得する。  <BR>
         *  <BR>
         * トランザクション（取引勘定明細）テーブルより以下の条件で行を検索し、 <BR>
         * トランザクション（取引勘定明細）行オブジェクトのリストを取得する。  <BR>
         * レコードが見つからなかった場合は、サイズ0のListを返却する。  <BR>
         *  <BR>
         * [検索条件]  <BR>
         * トランザクション（取引勘定明細）.注文単位ＩＤ =  <BR>
         * 　@　@　@引数の注文単位ＩＤ And <BR>
         * トランザクション.削除フラグ = BooleanEnum.FALSE And <BR>
         * @@param l_lngOrderUnitId - (注文単位ＩＤ)
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 428AAD4B0253
         */
        public List getFinTransactionForOrderUnit(long l_lngOrderUnitId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionForOrderUnit(long)";    
            log.entering(STR_METHOD_NAME);

            List l_lisReturnTransaction = null;
            
            //データ査詢
            QueryProcessor l_processor = null;
            try
            {
                l_processor = Processors.getDefaultProcessor();
                //１）　@トランザクションテーブル検索
                String l_strWhere = " order_unit_id = ? and delete_flag = ? ";
                Object[] l_strBindValue = new Object[2];
                l_strBindValue[0] = new Long(l_lngOrderUnitId);
                l_strBindValue[1] = BooleanEnum.FALSE;
                            
                l_lisReturnTransaction = l_processor.doFindAllQuery(FeqFinTransactionRow.TYPE,
                    l_strWhere, l_strBindValue);

            } 
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
                
            } 
            catch (DataNetworkException l_ex)        
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
                
            }        
            log.exiting(STR_METHOD_NAME);  
            return l_lisReturnTransaction;
        }
        
        /**
         * (get取引勘定明細ForOrderUnit（約定入力）) <BR>
         *  <BR>
         * 注文に関連する既約定の取引勘定明細で、 <BR>
         * 約定入力の行をリストで取得する。  <BR>
         *  <BR>
         * トランザクション（取引勘定明細）テーブルより以下の条件で行を検索し、 <BR>
         * トランザクション（取引勘定明細）行オブジェクトのリストを取得する。  <BR>
         * レコードが見つからなかった場合は、サイズ0のListを返却する。  <BR>
         *  <BR>
         * [検索条件]  <BR>
         * トランザクション（取引勘定明細）.注文単位ＩＤ = 引数の注文単位ＩＤ And <BR>
         * トランザクション.削除フラグ = BooleanEnum.FALSE And <BR>
         * トランザクション.約定経路区分 == 約定経路区分.”9：約定入力” <BR>
         * @@param l_lngOrderUnitId - (注文単位ＩＤ) <BR>
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 42B28CE303DC
         */
        public List getFinTransactionForOrderUnitExecInput(long l_lngOrderUnitId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionForOrderUnitExecInput(long)";    
            log.entering(STR_METHOD_NAME);

            List l_lisReturnTransaction = null;
            
            //データ査詢
            QueryProcessor l_processor = null;
            try
            {
                l_processor = Processors.getDefaultProcessor();
                //１）　@トランザクションテーブル検索
                String l_strWhere = " order_unit_id = ? and delete_flag = ? and order_exec_route_div = ? ";
                Object[] l_strBindValue = new Object[3];
                l_strBindValue[0] = new Long(l_lngOrderUnitId);
                l_strBindValue[1] = BooleanEnum.FALSE;
                l_strBindValue[2] = WEB3FeqOrderExecRouteDivDef.ORDER_AND_EXEC_INPUT;
                            
                l_lisReturnTransaction = l_processor.doFindAllQuery(FeqFinTransactionRow.TYPE,
                    l_strWhere, l_strBindValue);

            } 
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
                
            } 
            catch (DataNetworkException l_ex)        
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
                
            }        
            log.exiting(STR_METHOD_NAME);  
            return l_lisReturnTransaction;
        }
        
        /**
         * (get取引勘定明細For一括対象) <BR>
         *  <BR>
         * 手数料一括対象となる既約定の取引勘定明細を <BR>
         * リストで取得する。 <BR>
         *  <BR>
         * １）　@トランザクション（取引勘定明細）テーブルより以下 <BR>
         *       の条件で行を検索し、 <BR>
         * 　@トランザクション（取引勘定明細）行オブジェクトのリストを取得する。  <BR>
         * 　@レコードが見つからなかった場合は、サイズ0のListを返却する。  <BR>
         *  <BR>
         * [検索条件]  <BR>
         * 注文単位ＩＤ in (引数の注文単位ID一覧) And <BR>
         * 削除フラグ = BooleanEnum.FALSE <BR>
         *  <BR>
         * ※検索結果は、以下の項目で昇順ソートし、取得すること。 <BR>
         * 　@①@銘柄ID <BR>
         * 　@②発注日 <BR>
         * 　@③トランザクションタイプ <BR>
         * 　@④税区分 <BR>
         * 　@⑤決済区分 <BR>
         * @@param l_lngOrderUnitIds - (注文単位ID一覧) <BR>
         * 注文単位IDの一覧
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 42B673C60034
         */
        public List getFinTransactionForLumpObj(long[] l_lngOrderUnitIds) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionForLumpObj(long)";    
            log.entering(STR_METHOD_NAME);
            
            if (l_lngOrderUnitIds == null || l_lngOrderUnitIds.length == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            List l_lisReturnTransaction = null;
            
            //データ査詢
            QueryProcessor l_processor = null;
            try
            {
                l_processor = Processors.getDefaultProcessor();

                //空の文字列を生成する。<BR>
                StringBuffer l_sbQueryString = new StringBuffer();
                //１）　@トランザクションテーブル検索                
                String l_strSubCond = null;

                for (int i = 0; i < l_lngOrderUnitIds.length; i++)
                {
                    if (l_strSubCond == null)
                    {
                        l_strSubCond = "?";
                    }
                    else
                    {
                        l_strSubCond += ", ?";
                    }
                }
            
                l_sbQueryString.append("order_unit_id in(" + l_strSubCond + ")");
                
                l_sbQueryString.append("and delete_flag = ? ");
                List l_lisQueryContainer = new ArrayList();

                for (int i = 0; i < l_lngOrderUnitIds.length; i++)
                {
                    l_lisQueryContainer.add(new Long(l_lngOrderUnitIds[i]));
                }
               
                l_lisQueryContainer.add(BooleanEnum.FALSE);
                Object[] l_queryContainer = new Object[l_lisQueryContainer.size()];
                l_lisQueryContainer.toArray(l_queryContainer);
                String l_strOrderBy = "product_id,biz_date,fin_transaction_type,settle_div";
                
                //検索結果は、以下の項目で昇順ソートし、取得すること。
                l_lisReturnTransaction = 
                    l_processor.doFindAllQuery(
                        FeqFinTransactionRow.TYPE,
                        l_sbQueryString.toString(), 
                        l_strOrderBy,  
                        null, 
                        l_queryContainer);

            } 
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
                
            } 
            catch (DataNetworkException l_ex)        
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
                
            }        
            log.exiting(STR_METHOD_NAME);  
            return l_lisReturnTransaction;
        }
        
        /**
         * アイテムの定義<BR>
         * 更新項目をMapにセットする。 <BR>
         * （setUpdateAssetAttributesのオーバーライドメソッド） <BR>
         * <BR>
         * 引数.保有資産行の以下の項目をMapに追加する。 <BR>
         * <BR>
         * 数量  キー："quantity", 値：引数.保有資産行.数量 <BR>
         * 数量（簿価単価計算用）  キー："quantity_for_book_value", 値：引数.保有資産行.数量（簿価単価計算用） <BR>
         * 簿価（簿価単価計算用）  キー："book_value", 値：引数.保有資産行.簿価（簿価単価計算用） <BR>
         * 名義書換料  キー："setup_fee", 値：引数.保有資産行.名義書換料 <BR>
         * 名義書換料消費税  キー："setup_fee_tax", 値：引数.保有資産行.名義書換料消費税 <BR>
         * 口座管理費  キー："management_fee", 値：引数.保有資産行.口座管理費 <BR>
         * 口座管理費消費税  キー："management_fee_tax", 値：引数.保有資産行.口座管理費消費税 <BR>
         * 更新日付  キー："last_updated_timestamp", 値：引数.保有資産行.更新日付 <BR>
         *  @@param l_asserParams - (保有資産) <BR>
         */
        public void setUpdateAssetAttributes (AssetParams l_asserParams, Map l_mapValue)
        {
            final String STR_METHOD_NAME = "setUpdateAssetAttributes (AssetParams l_assetParamse, Map l_mapValue)";    
            log.entering(STR_METHOD_NAME);
            
            if (l_asserParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }                        
            
            //数量  キー："quantity", 値：引数.保有資産行.数量
            l_mapValue.put("quantity", new BigDecimal(l_asserParams.getQuantity()));
            
            //数量（簿価単価計算用）  キー："quantity_for_book_value", 値：引数.保有資産行.数量（簿価単価計算用）                
            l_mapValue.put("quantity_for_book_value", new BigDecimal(l_asserParams.getQuantityForBookValue()));
            
            //簿価（簿価単価計算用）  キー："book_value", 値：引数.保有資産行.簿価（簿価単価計算用）
            l_mapValue.put("book_value", new BigDecimal(l_asserParams.getBookValue()));
            
            //名義書換料  キー："setup_fee", 値：引数.保有資産行.名義書換料
            l_mapValue.put("setup_fee", new BigDecimal(l_asserParams.getSetupFee()));
            
            //名義書換料消費税  キー："setup_fee_tax", 値：引数.保有資産行.名義書換料消費税 
            l_mapValue.put("setup_fee_tax", new BigDecimal(l_asserParams.getSetupFeeTax()));
            
            //口座管理費  キー："management_fee", 値：引数.保有資産行.口座管理費
            l_mapValue.put("management_fee", new BigDecimal(l_asserParams.getManagementFee()));
            
            //口座管理費消費税  キー："management_fee_tax", 値：引数.保有資産行.口座管理費消費税
            l_mapValue.put("management_fee_tax", new BigDecimal(l_asserParams.getManagementFeeTax()));
            
            //更新日付  キー："last_updated_timestamp", 値：引数.保有資産行.更新日付
            l_mapValue.put("last_updated_timestamp", l_asserParams.getLastUpdatedTimestamp());
            
        }
    }
    
    /**
     * (reverse保有資産ByTrans) <BR>
     * （reverseAssetPositionByTransのオーバーライド）<BR>
     * トランザクション（取引勘定明細）行オブジェクトの約定取消内容を、保有資産行に値をセットする。<BR>
     * <BR>
     * １）　@約定取消対象の保有資産を取得する。<BR>
     * 　@getPersistenceManager().getAsset(trans.getAssetId())を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ２）　@パラメータ.side == SideEnum.BUY 且つ　@<BR>
     * （証券会社.is日計り取引採用()＝false　@or　@市場.is日計り市場()＝false）の場合 <BR>
     * 　@　@－何もしない。 <BR>
     * 　@　@※買付　@且つ（日計り未採用会社　@or　@<BR>
     * 日計り取引市場でない）の場合、保有資産は作成されない。 <BR>
     * <BR>
     * ３）　@パラメータ.side == SideEnum.SELL　@又は <BR>
     * (パラメータ.side == SideEnum.BUY　@且つ is日計り取引採用()＝true　@<BR>
     * 且つ　@is日計り市場()　@＝　@true)の場合 <BR>
     * 　@３－１）　@１）の戻り値 == nullの場合、例外をスローする。<BR>
     * <BR>
     * 　@３－２）　@約定取消の内容を保有資産にセットする。<BR>
     * 　@　@reverse保有資産残高(１）の戻り値, パラメータ.trans)を<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@３－３）　@保有資産を更新する。<BR>
     * 　@　@getPersistenceManager().updateAssetByTrans(１）の戻り値)を<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * ４）　@保有資産単位を更新する。<BR>
     * 　@reverseAssetUnitPositionByTrans(<BR>
     * 　@　@パラメータ.trans, パラメータ.side, １）の戻り値)を<BR>
     * 　@コールする。<BR>
     * 　@※上記メソッドは空実装してある為、何もしない。<BR>
     * <BR>
     * @@param trans - (トランザクション（取引勘定明細）行) <BR>
     * トランザクション（取引勘定明細）行オブジェクト
     * @@param side - (SideEnum) <BR>
     * SideEnum
     * @@throws DataException, RuntimeSystemException
     */
    protected void reverseAssetPositionByTrans(FeqFinTransactionParams trans, SideEnum side)
        throws DataException, RuntimeSystemException
    {
        final String STR_METHOD_NAME =  "reverseAssetPositionByTrans(FeqFinTransactionParams, SideEnum)";
        log.entering(STR_METHOD_NAME);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMarket l_market = null;

        WEB3GentradeMainAccount l_mainAccount = null;
        boolean l_blnIsDayTradeAdoption = false;
        boolean l_blnIsDayTradeMarket = false;
        try
        {
            l_market = (WEB3GentradeMarket)(l_finApp.getFinObjectManager().getMarket(trans.getMarketId()));
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(trans.getAccountId());
            WEB3GentradeInstitution l_institution = 
                (WEB3GentradeInstitution)l_mainAccount.getInstitution();

            try
            {
                l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();
                l_blnIsDayTradeMarket = l_market.isDayTradeMarket();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        AssetParams asset = getPersistenceManager().getAsset(trans.getAssetId());
        //パラメータ.side == SideEnum.BUY 且つ　@
        //（証券会社.is日計り取引採用()＝false　@or　@市場.is日計り市場()＝false）の場合
        
        if (side == SideEnum.BUY  && (!l_blnIsDayTradeAdoption || !l_blnIsDayTradeMarket))
        {
//            if(asset == null)
//            {
//                String msg = "reverseAssetPositionByTrans: acct(" + trans.getAccountId() + "), subAcct(" + trans.getSubAccountId() + "), transType(" + trans.getFinTransactionType() + "), product(" + trans.getProductId() + "), price(" + trans.getPrice() + "), quantity(" + trans.getQuantity() + ")";
//                log.error(msg);
//                throw new RuntimeSystemException(msg);
//            }
//            if(asset.getQuantity() < trans.getQuantity())
//            {
//                String msg = "reverseAssetPositionByTrans: Too little Asset for acct(" + trans.getAccountId() + "), subacct(" + trans.getSubAccountId() + "), product(" + trans.getProductId() + "), asset(" + asset.getQuantity() + ") < trans(" + trans.getQuantity() + ")";
//                log.error(msg);
//                throw new RuntimeSystemException(msg);
//            }
//            reverseAssetParamsByMarketTradedTrans(asset, trans);
//            getPersistenceManager().updateAssetByTrans(asset);
        } else
        //３）　@パラメータ.side == SideEnum.SELL　@又は 
        //(パラメータ.side == SideEnum.BUY　@且つ is日計り取引採用()＝true　@且つ　@is日計り市場()　@＝　@true)の場合
        if(side == SideEnum.SELL || (side == SideEnum.BUY && l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket))
        {
            if(asset == null)
            {
                String msg = "reverseAssetPositionByTrans: No Asset for acct(" + trans.getAccountId() + "), transType(" + trans.getFinTransactionType() + "), subacct(" + trans.getSubAccountId() + "), product(" + trans.getProductId() + ")";
                log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            reverseAssetParamsByMarketTradedTrans(asset, trans);
            getPersistenceManager().updateAssetByTrans(asset);
        }
        reverseAssetUnitPositionByTrans(trans, side, asset);
    }

    /**
     * (保有資産更新（簿価更新用）)<BR>
     * ［処理概要］ <BR>
     * １）資産IDより保有資産Paramを取得する。 <BR>
     * ※保有資産に該当するデータがない、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag 　@:   BUSINESS_ERROR_00204<BR>
     * <BR>
     * ２）保有資産Params.税区分＝"一般口座"かつ <BR>
     * 　@　@保有資産Param.数量（簿価単価計算用）＞0 かつ <BR>
     * 　@　@保有資産Param.簿価（簿価単価計算用）＝0の場合、 <BR>
     * 　@　@何もせず処理をreturnする。 <BR>
     * <BR>
     * ３）上記以外の場合 <BR>
     * <BR>
     * 　@３－１）保有資産Param簿価（簿価単価計算用） の値を更新する。 <BR>
     * 　@　@　@※更新後簿価 = 更新前簿価－（更新前）受渡代金×（-1）＋（更新後）受渡代金 ×（-1）<BR>
     * <BR>
     * 　@３－２）更新された保有資産Paramsを保有資産テーブルに保存する。 <BR>
     * 　@　@　@※外国株式更新データマネージャ.updateAssetByTrans()をコール <BR>
     * 　@３－３）更新時間に現在日時（GtlUtils.getSystemTimestamp()）が設定される。<BR>
     * @@param l_lngAssetID - (資産ID)<BR>
     * @@param l_dblBeforeDeliveryPrice - (更新前の受渡代金)<BR>
     * @@param l_dblAfterDeliveryPrice - (更新後の受渡代金)<BR>
     * @@throws WEB3BaseException
     */
    public void assetUpdateNettingExchangeRateAdoption(
        long l_lngAssetID, double l_dblBeforeDeliveryPrice, double l_dblAfterDeliveryPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =  "assetUpdateNettingExchangeRateAdoption(long, double, double)";
        log.entering(STR_METHOD_NAME);

        AssetParams l_assetParams = null;
        try
        {
            l_assetParams = new AssetParams(AssetDao.findRowByPk(l_lngAssetID));
        }
        catch (DataFindException l_ex)
        {
            log.error("保有資産該当データなし。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。 ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)        
        {
            log.error("DBへのアクセスに失敗しました。 ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ２）保有資産Params.税区分＝"一般口座"かつ
        //保有資産Param.数量（簿価単価計算用）＞0 かつ
        //保有資産Param.簿価（簿価単価計算用）＝0の場合
        if (TaxTypeEnum.NORMAL.equals(l_assetParams.getTaxType())
            && l_assetParams.getQuantityForBookValue() >0
            && GtlUtils.Double.isZero(l_assetParams.getBookValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            //更新後簿価 = 更新前簿価－（更新前）受渡代金×（-1）＋（更新後）受渡代金 ×（-1）
            BigDecimal l_bdBeforeDeliveryPrice = new BigDecimal(Double.toString(l_dblBeforeDeliveryPrice * -1));
            BigDecimal l_bdAfterDeliveryPrice = new BigDecimal(Double.toString(l_dblAfterDeliveryPrice * -1));
            BigDecimal l_bdBeforeBookValue = new BigDecimal(Double.toString(l_assetParams.getBookValue()));
            BigDecimal l_bdAfterBookValue =
                l_bdBeforeBookValue.subtract(l_bdBeforeDeliveryPrice).add(l_bdAfterDeliveryPrice);
            l_assetParams.setBookValue(l_bdAfterBookValue.doubleValue());
            //※外国株式更新データマネージャ.updateAssetByTrans()をコール
            WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
                (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
            //更新時間に現在日時（GtlUtils.getSystemTimestamp()）
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            try
            {
                l_dataManager.updateAssetByTrans(l_assetParams);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。 ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            catch (DataNetworkException l_ex)        
            {
                log.error("DBへのアクセスに失敗しました。 ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }  
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateトランザクション（ネッティング採用）)<BR>
     * ネッティング採用会社となるトランザクションについて、 <BR>
     * ネッティング為替レートを使用し、トランザクションデータを更新する。 <BR>
     *      <BR>
     * シーケンス図 <BR>
     * 「（外株残高）updateトランザクション（ネッティング採用）」参照。<BR>
     * @@param l_lngOrderUnitIDs - (注文単位ＩＤ一覧)<BR>
     * @@param l_datDate - (基準日)<BR>
     */
    public void updateTransactionNettingAdoption(long[] l_lngOrderUnitIDs, Date l_datDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =  "updateTransactionNettingAdoption(long[], Date)";
        log.entering(STR_METHOD_NAME);

        //get取引勘定明細For一括対象(long[])
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager =
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
        List l_lisFinTransactionForLumpObj = l_dataManager.getFinTransactionForLumpObj(l_lngOrderUnitIDs);

        if (l_lisFinTransactionForLumpObj == null || l_lisFinTransactionForLumpObj.size() <= 1)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //getネッティング為替レート(List)
        //[引数]
        //トランザクション（取引勘定明細）行List：　@get取引勘定明細For一括対象()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3FeqProductManager l_productManager = (WEB3FeqProductManager)l_tradingModule.getProductManager();
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();

        WEB3FeqBizLogicProvider l_bizlogicProvider = (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        HashMap l_hmNettingExchangeRate = l_bizlogicProvider.getNettingExchangeRate(l_lisFinTransactionForLumpObj);

        //getネッティング為替レート(）の戻り値、nullが返却された場合は、処理を終了する。
        if (l_hmNettingExchangeRate == null || l_hmNettingExchangeRate.keySet().size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //注文単位ＩＤのArrayListを生成する
        ArrayList l_lisOrderUnitIDs = new ArrayList();

        //get取引勘定明細For一括対象()の戻り値の要素数分Loop処理
        Iterator l_iterator = l_lisFinTransactionForLumpObj.iterator();
        while (l_iterator.hasNext())
        {
            FeqFinTransactionRow l_feqFinTransactionRow = (FeqFinTransactionRow)l_iterator.next();
            String l_strCurrencyCode = l_feqFinTransactionRow.getCurrencyCode();
            double l_dblFxRate = 0;
            if (l_hmNettingExchangeRate.containsKey(l_strCurrencyCode) && !l_feqFinTransactionRow.getOrderUnitIdIsNull())
            {
                long l_lngOrderUnitId = l_feqFinTransactionRow.getOrderUnitId();
                if (!l_lisOrderUnitIDs.contains(new Long(l_lngOrderUnitId)))
                {
                    l_lisOrderUnitIDs.add(new Long(l_lngOrderUnitId));
                }
                FeqOrderExecutionRow l_feqOrderExecutionRow = null;
                FeqOrderExecutionParams l_feqOrderExecutionParams = null;
                FeqFinTransactionParams l_feqFinTransactionParams = null;
                try
                {
                    l_dblFxRate = ((Double)l_hmNettingExchangeRate.get(l_strCurrencyCode)).doubleValue();
                    //ﾄﾗﾝｻﾞｸｼｮﾝ（取引勘定明細）テーブルに紐づく、約定テーブルを全て取得する
                    l_feqOrderExecutionRow =
                        FeqOrderExecutionDao.findRowByPk(l_feqFinTransactionRow.getOrderExecutionId());
                    l_feqOrderExecutionParams = new FeqOrderExecutionParams(l_feqOrderExecutionRow);
                    l_feqOrderExecutionParams.setFxRate(l_dblFxRate);
                    l_feqOrderExecutionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                    l_queryProcesser.doUpdateQuery(l_feqOrderExecutionParams);

                    //「ネッティング為替レート（通貨コード）」をﾄﾗﾝｻﾞｸｼｮﾝ（取引勘定明細）行の適用為替レートに設定する
                    l_feqFinTransactionParams = new FeqFinTransactionParams(l_feqFinTransactionRow);
                    l_feqFinTransactionParams.setFxRate(l_dblFxRate);
                }
                catch (DataFindException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                } 
                catch (DataQueryException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。 ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                } 
                catch (DataNetworkException l_ex)        
                {
                    log.error("DBへのアクセスに失敗しました。 ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //calc売買代金(double, double)
                double l_dblExecutionAmount =
                    l_bizlogicProvider.calcExecutionAmount(l_feqFinTransactionRow.getQuantity(), l_feqFinTransactionRow.getPrice());

                WEB3GentradeSubAccount l_subAccount = null;
                WEB3FeqProduct l_product = null;
                WEB3FeqOrderUnit l_orderUnit = null;
                WEB3GentradeMarket l_market = null;
                boolean l_blnIsLimitPrice = false;
                boolean l_blnIsBuy = false;
                try
                {
                    l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(l_feqFinTransactionRow.getAccountId(),
                        l_feqFinTransactionRow.getSubAccountId());
                    l_product = (WEB3FeqProduct)l_productManager.getProduct(l_feqFinTransactionRow.getProductId());
 
                    l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_feqFinTransactionRow.getMarketId());

                    l_orderUnit = (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
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
                if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_feqFinTransactionRow.getFinTransactionType()))
                {
                    l_blnIsBuy = true;
                }
                FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
                if (GtlUtils.Double.isZero(l_orderUnit.getLimitPrice()))
                {
                    l_blnIsLimitPrice = true;
                }
                //calc外国株式金額
                //補助口座：
                //　@getSubAccountId()に該当する補助口座
                //　@（※AccountManager.getSubAccount()にて取得）
                //外国株式銘柄：
                //　@getProductId()に該当する外国株式銘柄
                //　@（※FeqProductManager.getProduct()にて取得）
                //市場：　@getMarketId()に該当する市場
                //　@（※FinObjectManager.getMarket()にて取得）
                //基準日：　@基準日
                //約定日：　@getFinTransactionTimestamp()
                //売買代金：　@calc売買代金()
                //為替レート：　@外国株式計算サービス.getネッティング為替レート()の
                //戻り値.get(ﾄﾗﾝｻﾞｸｼｮﾝ（取引勘定明細）行の通貨コード)の戻り値
                //is買付：
                //　@getFinTransactionType() == ”外株買い”の場合true。以外、false。
                //is約定計算：　@false
                //is指値：
                //　@処理対象の注文単位IDに該当する注文単位行.指値 == 0の場合false。以外、true。
                //注文チャネル：　@処理対象の注文単位IDに該当する注文単位行.初回注文の注文チャネル
                WEB3FeqAmountCalcResult l_feqAmountCalcResult =l_bizlogicProvider.calcFeqAmount(
                    l_subAccount,
                    l_product,
                    l_market,
                    l_datDate,
                    l_feqFinTransactionRow.getFinTransactionTimestamp(),
                    l_dblExecutionAmount,
                    l_dblFxRate,
                    l_blnIsBuy,
                    false,
                    l_blnIsLimitPrice,
                    l_feqOrderUnitRow.getOrderChanel());

                //プロパティセット
                //外株出来終了_取引勘定明細DB更新（ネッティング為替採用）
                HashMap l_hmFeqFinTransaction = new HashMap();

                //買約定（ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ=”外株買い”）の場合、以下の計算結果*) 受渡代金×（-1）
                if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_feqFinTransactionRow.getFinTransactionType()))
                {
                    //受渡代金
                    l_feqFinTransactionParams.setNetAmount(l_feqAmountCalcResult.getNetAmount() * (-1));
                    l_hmFeqFinTransaction.put("net_amount", new BigDecimal(Double.toString(l_feqFinTransactionParams.getNetAmount())));
                    //受渡代金（外貨）
                    l_feqFinTransactionParams.setNetAmountFc(l_feqAmountCalcResult.getNetAmountFc() * (-1));
                    l_hmFeqFinTransaction.put("net_amount_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getNetAmountFc())));
                    //譲渡益金額
                    l_feqFinTransactionParams.setCapitalGain(0);
                    l_hmFeqFinTransaction.put("capital_gain", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGain())));
                    //譲渡益税額
                    l_feqFinTransactionParams.setCapitalGainTax(0);
                    l_hmFeqFinTransaction.put("capital_gain_tax", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGainTax())));
                    
                    //譲渡益金額（外貨）
                    l_feqFinTransactionParams.setCapitalGainFc(0);
                    l_hmFeqFinTransaction.put("capital_gain_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGainFc())));
                    //譲渡益税額（外貨）
                    l_feqFinTransactionParams.setCapitalGainTaxFc(0);
                    l_hmFeqFinTransaction.put("capital_gain_tax_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGainTaxFc())));
                }
                //売の場合（ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ=="外株売り"）、譲渡損益／譲渡益税を計算する
                else if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_feqFinTransactionRow.getFinTransactionType()))
                {
                        //calc譲渡損益(double, double, long, 補助口座, TaxTypeEnum)
                        //売買代金（円貨）：　@calc外国株式金額().get受渡代金()
                        // 売株数：　@約定数量（約定.getExecutedQuantity()）
                        //銘柄ＩＤ：　@getProductId()
                        //補助口座：　@getSubAccountId()に該当する補助口座
                        // 税区分：　@getTaxType()
                        double l_dblCapitalProfitLoss = l_bizlogicProvider.calcCapitalProfitLoss(
                            l_feqAmountCalcResult.getNetAmount(),
                            l_feqOrderExecutionParams.getExecQuantity(),
                            l_feqFinTransactionRow.getProductId(),
                            l_subAccount,
                            l_feqFinTransactionRow.getTaxType());
                        //calc譲渡益税(補助口座, TaxTypeEnum, double, Date)
                        //補助口座：　@getSubAccountId()に該当する補助口座
                        //税区分：　@getTaxType()
                        //売買代金（円貨）：　@calc譲渡損益()
                        //基準日：　@受渡日（getDeliveryDate()）
                        double l_dblCapitalGainTax = l_bizlogicProvider.calcCapitalGainTax(
                            l_subAccount,
                            l_feqFinTransactionRow.getTaxType(),
                            l_dblCapitalProfitLoss,
                            l_feqFinTransactionRow.getDeliveryDate());
                        //calc外貨換算
                        //[calc外貨換算()に指定する引数]
                        //金額（円貨）：　@calc譲渡損益()
                        //為替レート：　@getFxRate()
                        //銘柄ID：　@getProductId()
                        //is買付：　@false
                        //is約定計算：　@false
                        double l_dblForeignCCYAmountCapitalProfitLoss = l_bizlogicProvider.calcForeignCCYAmount(
                            l_dblCapitalProfitLoss,
                            l_feqFinTransactionParams.getFxRate(),
                            l_feqFinTransactionParams.getProductId(),
                            false,
                            false);
                        //calc外貨換算
                        //[calc外貨換算()に指定する引数]
                        //金額（円貨）：　@calc譲渡益税()
                        //為替レート：　@getFxRate()
                        //銘柄ID：　@getProductId()
                        //is買付：　@false
                        //is約定計算：　@false
                        double l_dblForeignCCYAmountCapitalGainTax = l_bizlogicProvider.calcForeignCCYAmount(
                            l_dblCapitalGainTax,
                            l_feqFinTransactionParams.getFxRate(),
                            l_feqFinTransactionParams.getProductId(),
                            false,
                            false);

                        //受渡代金
                        l_feqFinTransactionParams.setNetAmount(l_feqAmountCalcResult.getNetAmount());
                        l_hmFeqFinTransaction.put("net_amount", new BigDecimal(Double.toString(l_feqFinTransactionParams.getNetAmount())));
                        //受渡代金（外貨）
                        l_feqFinTransactionParams.setNetAmountFc(l_feqAmountCalcResult.getNetAmountFc());
                        l_hmFeqFinTransaction.put("net_amount_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getNetAmountFc())));
                        //譲渡益金額
                        l_feqFinTransactionParams.setCapitalGain(l_dblCapitalProfitLoss);
                        l_hmFeqFinTransaction.put("capital_gain", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGain())));
                        //譲渡益税額
                        l_feqFinTransactionParams.setCapitalGainTax(l_dblCapitalGainTax);
                        l_hmFeqFinTransaction.put("capital_gain_tax", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGainTax())));
                        
                        //譲渡益金額（外貨）
                        l_feqFinTransactionParams.setCapitalGainFc(l_dblForeignCCYAmountCapitalProfitLoss);
                        l_hmFeqFinTransaction.put("capital_gain_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGainFc())));
                        //譲渡益税額（外貨）
                        l_feqFinTransactionParams.setCapitalGainTaxFc(l_dblForeignCCYAmountCapitalGainTax);
                        l_hmFeqFinTransaction.put("capital_gain_tax_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGainTaxFc())));
                }

                //適用為替レート
                l_hmFeqFinTransaction.put("fx_rate", new BigDecimal(Double.toString(l_feqFinTransactionParams.getFxRate())));
                //委託手数料
                l_feqFinTransactionParams.setCommissionFee(l_feqAmountCalcResult.getCommissionFee());
                l_hmFeqFinTransaction.put("commission_fee", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCommissionFee())));
                //委託手数料消費税
                l_feqFinTransactionParams.setCommissionFeeTax(l_feqAmountCalcResult.getCommisionFeeTax());
                l_hmFeqFinTransaction.put("commission_fee_tax", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCommissionFeeTax())));
                //現地清算代金（円貨）
                l_feqFinTransactionParams.setBalanceAmount(l_feqAmountCalcResult.getBalanceAmount());
                l_hmFeqFinTransaction.put("balance_amount", new BigDecimal(Double.toString(l_feqFinTransactionParams.getBalanceAmount())));
                //委託手数料（外貨）
                l_feqFinTransactionParams.setCommissionFeeFc(l_feqAmountCalcResult.getCommissionFeeFc());
                l_hmFeqFinTransaction.put("commission_fee_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCommissionFeeFc())));
                //委託手数料消費税（外貨）
                l_feqFinTransactionParams.setCommissionFeeTaxFc(l_feqAmountCalcResult.getCommisionFeeTaxFc());
                l_hmFeqFinTransaction.put("commission_fee_tax_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCommissionFeeTaxFc())));
                //更新者コード
                try
                {
                    OpLoginSecurityService l_opLoginSecurityService =
                        (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                    l_opLoginSecurityService.getLoginId();
                    WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
                    l_feqFinTransactionParams.setLastUpdater(l_administrator.getAdministratorCode());
                }
                catch (IllegalSessionStateException l_ex)
                {
                    l_feqFinTransactionParams.setLastUpdater(null);
                }
                l_hmFeqFinTransaction.put("last_updater", l_feqFinTransactionParams.getLastUpdater());
                //更新日付
                l_feqFinTransactionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_hmFeqFinTransaction.put("last_updated_timestamp", l_feqFinTransactionParams.getLastUpdatedTimestamp());

                //updateFinTransaction
                try
                {
                    l_dataManager.updateFinTransaction(l_feqFinTransactionParams, l_hmFeqFinTransaction);
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

                //notify顧客勘定(トランザクション（取引勘定明細）行 : FeqFinTransactionParams)
                this.notifyGtl(l_feqFinTransactionParams);

                //買付（トランザクション（取引勘定明細）行.ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ==”外株買い”）の場合
                if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_feqFinTransactionRow.getFinTransactionType()))
                {
                    //保有資産更新（簿価更新用）(long, double, double)
                    //[保有資産更新（ネッティング為替採用）に指定する引数]
                    //資産ID：　@getAssetId( )の戻り値
                    //更新前の受渡代金：
                    //　@　@　@更新前のトランザクション（取引勘定明細）行.受渡代金を取得する。
                    //更新後の受渡代金：
                    //　@　@　@更新後のトランザクション（取引勘定明細）行.受渡代金を取得する。
                    this.assetUpdateNettingExchangeRateAdoption(
                        l_feqFinTransactionRow.getAssetId(),
                        l_feqFinTransactionRow.getNetAmount(),
                        l_feqFinTransactionParams.getNetAmount());
                }
            }
        }

        //（注文単位ID）の要素数分Loop処理
        int l_intSize = l_lisOrderUnitIDs.size();

        //ArrayList( )の戻り値（注文単位IDの一覧）
        long[] l_lngOrderUnitIdList = new long[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            long l_lngOrderUnitId = ((Long)l_lisOrderUnitIDs.get(i)).longValue();

            l_lngOrderUnitIdList[i] = l_lngOrderUnitId;

            //updateトランザクション(long, boolean)
            //　@注文単位ＩＤ：　@ 要素数分の注文単位ID
            //　@is取消：　@false
            this.updateTransaction(l_lngOrderUnitId, false);

            WEB3FeqOrderUnit l_feqOrderUnit = null;
            try
            {
                l_feqOrderUnit = (WEB3FeqOrderUnit) l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。",
                    l_ex);
            }
            WEB3FeqFinTransactionManager l_finTransaction =
                (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
            List l_lstTransaction = l_finTransaction.getTransactions(l_feqOrderUnit);
            // update概算受渡代金(注文単位 : 外国株式注文単位, 約定日 : Date)
            //[引数]
            //注文単位：　@処理対象の注文単位IDに該当する注文単位
            //約定日：　@外国株式トランザクションマネージャ.getトランザクション()の戻り値
            //であるトランザクション（取引勘定明細）行の[0]行目.getFinTransactionTimestamp
            l_orderManager.updateEstimatedPrice(
                l_feqOrderUnit, ((FeqFinTransactionRow)l_lstTransaction.get(0)).getFinTransactionTimestamp());

            //update合計約定金額（円）(注文単位ID : long)
            l_orderManager.updateExecutedAmountYen(l_lngOrderUnitId);

            //updateネッティング注文更新日付(注文単位ID : Long)
            //引数の設定仕様は以下の通り
            //　@注文単位ID：　@パラメータ.注文単位ID一覧.[i]
            l_orderManager.updateNettingOrderLastUpdatedTimestamp(
                new Long(l_lngOrderUnitId));//WEB3BaseException
        }

        //isネッティング処理
        boolean l_blnIsNetting = true;

        //updateトランザクション(long[], Date, boolean)
        //[引数]
        //注文単位ID一覧：　@ArrayList( )の戻り値（注文単位IDの一覧）
        //基準日：　@パラメータ.発注日
        //isネッティング処理：　@true
        this.updateTransaction(l_lngOrderUnitIdList, l_datDate, l_blnIsNetting);

        //ArrayList( )の戻り値（注文単位）で顧客の一覧を取得し、要素数分Loop処理を行う。
        for (int j = 0; j < l_intSize; j++)
        {
            long l_lngOrderUnitId = ((Long)l_lisOrderUnitIDs.get(j)).longValue();
            log.debug("注文単位ＩＤ = " + l_lngOrderUnitId);

            //顧客
            WEB3GentradeMainAccount l_mainAccount = null;

            //補助口座
            WEB3GentradeSubAccount l_subAccount = null;

            //外国株式注文単位
            WEB3FeqOrderUnit l_feqOrderUnit = null;

            try
            {
                //ArrayList( )の戻り値（注文単位）で顧客の一覧を取得し
                l_feqOrderUnit =
                    (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);

                l_mainAccount =
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                        l_feqOrderUnit.getAccountId());
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

            //is信用口座開設(弁済区分 : String)
            boolean l_blnIsMarginAccountEstablished =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
            log.debug("is信用口座開設 = " + l_blnIsMarginAccountEstablished);

            //getSubAccount(arg0 : SubAccountTypeEnum)
            try
            {
                //[is信用口座開設()の戻り値 == trueの場合]
                if (l_blnIsMarginAccountEstablished)
                {
                    //SubAccountTypeEnum.株式信用取引口座をセット。
                    l_subAccount = (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);//NotFoundException
                }
                //[上記以外の場合]
                else
                {
                    //SubAccountTypeEnum.株式取引口座をセット。
                    l_subAccount = (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);//NotFoundException
                }
            }
            catch (NotFoundException l_ex)
            {
                log.debug("補助口座がありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "補助口座がありません。",
                    l_ex);
            }

            WEB3TPTradingPowerReCalcService l_tPTradingPowerReCalcServiceImpl =
                (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);

            if (l_tPTradingPowerReCalcServiceImpl == null)
            {
                log.debug("余力再計算サービスImplが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "余力再計算サービスImplが存在しない。");
            }

            //余力再計算(補助口座 : 補助口座)
            //[引数]
            //補助口座：　@getSubAccount()の戻り値
            l_tPTradingPowerReCalcServiceImpl.reCalcTradingPower(l_subAccount);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
