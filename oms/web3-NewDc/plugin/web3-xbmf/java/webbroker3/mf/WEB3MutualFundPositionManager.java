head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundPositionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張投信ポジションマネージャ(WEB3MutualFundPositionManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/06 王蘭芬(中訊) 新規作成
Revesion History : 2004/08/20 韋念瓊 (中訊) レビュー   
Revesion History : 2004/12/06 于美麗 (中訊) 残対応 
Revesion History : 2006/03/28 山下 (SRA) 仕様変更モデルNo.405対応
Revesion History : 2006/03/28 山下 (SRA) 仕様変更モデルNo.405対応 検索条件の不備修正
Revesion History : 2006/05/15 周捷 (中訊) 仕様変更（モデル）：413
Revesion History : 2007/02/05 唐性峰 (中訊) モデル532,539
Revesion History : 2007/02/16 柴双紅 (中訊) 仕様変更・モデル541
*/

package webbroker3.mf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundAsset;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundPositionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecStatusDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.data.ExchangeRateParams;
import webbroker3.mf.data.MfSubAssetRow;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (拡張投信ポジションマネージャ)<BR>
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundPositionManager extends MutualFundPositionManagerImpl 
{
    
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MutualFundPositionManager.class);

    /**
     * （updateLockedQuantityのオーバーライド）<BR>
     * <BR>
     * 何も処理を行わない。<BR>
     * @@param l_lngAccountId - 口座ID<BR>
     * @@param l_lngSubAccountId - 補助口座ID<BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@param l_lngProductId - 銘柄ID<BR>
     * @@param l_dblLockedQuantity - (ロック数量)<BR>
     * ロックしようとするプラスかマイナスの数量<BR>
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
     * @@roseuid 40A97684000F
     */
    public void updateLockedQuantity(
        long l_lngAccountId, 
        long l_lngSubAccountId, 
        long l_lngOrderUnitId, 
        long l_lngProductId, 
        double l_dblLockedQuantity) 
        throws RuntimeSystemException 
    {
        // 何も処理を行わない。
    }
    
    /**
     * (calc解約可能残高口数)<BR>
     * 当該顧客の保有する、現在解約可能な投資信託の解約可能残高口数を返す。<BR>
     * <BR>
     * １）　@保有資産テーブルより残高を取得する。<BR>
     * 　@－this.getAsset()をコールし、保有資産オブジェクトを取得する。<BR>
     * 　@　@［getAssetに渡すパラメタ］<BR>
     * 　@　@　@アセットID： 引数.資産ID <BR>
     * 　@　@－getAsset()がNotFoundExceptionをスローした場合は、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00204<BR>
     * 　@　@－取得した保有資産オブジェクト.getQuantity()をコールして残高を取得する。<BR>
     * <BR>
     * ２）　@this.calc解約中概算口数()をコールし、解約中概算口数を取得する。<BR>
     * 　@　@［calc解約中概算口数に渡すパラメタ］<BR>
     * 　@　@　@補助口座： 引数.補助口座オブジェクト<BR>
     * 　@　@　@銘柄：　@引数.拡張投信銘柄オブジェクト。<BR>
     *      税区分:：　@getAsset()で取得した資産の税区分<BR> 
     *      保有資産：　@getAsset()の戻り値 <BR> 
     * <BR>
     * ３）　@残高 - 解約中概算口数を返す。<BR>
     * 　@　@　@
     * @@param l_subAccount - 補助口座
     * @@param l_mutualFundProduct - 拡張投信銘柄
     * @@param l_strAssetId - 資産ID
     * @@return double
     * @@roseuid 40B2D8FF008C
     */
    public double calcSellPossiblePositionQty(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strAssetId) 
        throws WEB3BaseException
    {
        final String l_strMethodName = "calcSellPossiblePositionQty("
            + "SubAccount l_subAccount, "
            + "WEB3MutualFundProduct l_mutualFundProduct, String l_strAssetId))";
        log.entering(l_strMethodName);
        if (l_subAccount == null || l_mutualFundProduct == null || l_strAssetId == null)
        {
            log.debug("パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        try
        {
            // １）　@保有資産テーブルより残高を取得する。
            // ［getAssetに渡すパラメタ］
            // アセットID： 引数.資産ID 
            Asset l_asset = this.getAsset(Long.parseLong(l_strAssetId));
            BigDecimal l_bdQuantity =
            	new BigDecimal(Double.toString(l_asset.getQuantity()));
            log.debug("保有資産の残高 = " + l_bdQuantity);

            // ２）　@this.calc解約中概算口数()をコールし、解約中概算口数を取得する。
            // ［calc解約中概算口数に渡すパラメタ］ 
            // 補助口座： 引数.補助口座オブジェクト 
            // 銘柄：　@引数.拡張投信銘柄オブジェクト 
            // 税区分:：　@getAsset()で取得した資産の税区分
            // 保有資産：　@getAsset()の戻り値 <BR> 
            double l_dblSellingEstimatedQty = 
                this.calcSellingEstimatedQty(
                    l_subAccount, 
                    l_mutualFundProduct,
                    l_asset.getTaxType(), 
                    l_asset);
            
            BigDecimal l_bdSellingEstimatedQty =
            	new BigDecimal(Double.toString(l_dblSellingEstimatedQty));
            
			log.debug("解約中概算口数 = " + l_bdSellingEstimatedQty);

            // ３）　@残高 - 解約中概算口数を返す。
            log.exiting(l_strMethodName);
			return l_bdQuantity.subtract(l_bdSellingEstimatedQty).doubleValue();
        }
        catch (NotFoundException l_ex)
        {
            log.error("該当する保有資産がありません for SubAccountId = " 
                + l_subAccount.getSubAccountId() + " ProductId = " 
                + l_mutualFundProduct.getProductId());
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
    }
    
    /**
     * (calc解約中概算口数)<BR>
     * 当該顧客の、現在解約中の概算口数を返す。<BR>
     * <BR>
     * １）　@投信注文単位テーブルを検索し、解約注文口数の総計を計算する。<BR>
     * 　@－投信注文単位テーブルを検索し、投信注文単位ParamsのListを取得する。<BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@口座ID = 引数.補助口座.getAccountId()<BR>
     * 　@　@　@AND 補助口座ID = 引数.補助口座.getSubAccountId()<BR>
     * 　@　@　@AND 銘柄ID = 引数.拡張投信銘柄.getProductId()<BR> 　@　@　@
     * 　@　@　@AND (注文状態 = OrderStatusEnum.受付済（新規注文） OR 注文状態<BR>
     * = OrderStatusEnum.発注済（新規注文）)<BR>
     *       AND 税区分 = 引数.税区分 <BR>
     * 　@　@　@AND (  (注文種別 = OrderTypeEnum.投資信託売注文 <BR>
     * 　@　@　@                 AND 約定状態 != ”2：約定済”) <BR>
     *           OR (注文種別 = OrderTypeEnum.投資信託乗換注文　@AND
     *                   (約定状態 is null OR (約定状態 = ”1：約定中”AND 約定日 = 乗換元約定日) ) )  )
     *      
     * 　@－取得した投信注文単位Paramsの概算売買口数（get概算売買口数()にて取得）<BR>
     * の総計を計算する。<BR>
     * （*)(拡張投信銘柄.通貨コード != 円貨 かつget投信解約区分 = 全部) の場合、 <BR>
     *     総計 = get注文数量() をセットする。<BR>
     * <BR>
     * ２）　@取得した解約注文口数の総計を返す。
     * @@param l_subAccount - 補助口座
     * @@param l_mutualFundProduct - 拡張投信銘柄
     * @@param l_taxType - 税区分
     * @@param l_asset - 保有資産
     * @@return double
     * @@roseuid 40B2E37F033C
     */
    public double calcSellingEstimatedQty(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        TaxTypeEnum l_taxType, 
        Asset l_asset)
        throws WEB3BaseException
    {
        final String l_strMethodName = "calcSellingEstimatedQty(" +
            "SubAccount, WEB3MutualFundProduct, String, Asset)";
        log.entering(l_strMethodName);
        
        if (l_subAccount == null || l_mutualFundProduct == null)
        {
            log.debug("パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        try
        {
            // １）　@投信注文単位テーブルを検索し、解約注文口数の総計を計算する。
            List l_lisMFOrderUnits = new ArrayList();

            String l_strWhere = 
			    "account_id = ? and sub_account_id = ? "
			    + "and product_id = ? "
			    + "and (order_status = ? or order_status = ?) "
			    + "and tax_type = ? "
			    + "and (   (order_type = ? and (exec_status != ? or exec_status is null) ) "
			    + "     or (order_type = ? and (exec_status is null or (exec_status = ? and exec_date = swt_exec_date) ) ) ) ";

            Object[] l_objWhereValues = {
                new Long(l_subAccount.getAccountId()),
                new Long(l_subAccount.getSubAccountId()),
                new Long(l_mutualFundProduct.getProductId()),
                // OrderStatusEnum.ACCEPTED ---- 1：受付済（新規注文）
				OrderStatusEnum.ACCEPTED,
				// OrderStatusEnum.ORDERED --- 3：発注済（新規注文）
				OrderStatusEnum.ORDERED,
                // 税区分 = 引数.税区分 
				l_taxType,                                
                // OrderTypeEnum.MF_SELL --- 202：投資信託売注文
                OrderTypeEnum.MF_SELL,
                // 約定状態 != ”2：約定済”
				WEB3ExecStatusDef.EXECUTED_COMPLETE,
                // OrderTypeEnum.MF_SWITCHING --- 204：投資信託乗換注文
                OrderTypeEnum.MF_SWITCHING,
				// 約定状態 = ”1：約定中”
				WEB3ExecStatusDef.EXECUTED_IN_PROCESS,            
            };
            // -投信注文単位テーブルを検索し、投信注文単位ParamsのListを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisMFOrderUnits = l_queryProcessor.doFindAllQuery(
                MutualFundOrderUnitRow.TYPE,
                l_strWhere,
                l_objWhereValues);
            
            if (l_lisMFOrderUnits == null || l_lisMFOrderUnits.size() == 0)
            {
                return 0;
            }            
            
            // 概算売買口数(集計用)   
            BigDecimal l_bdEstimateDealingQty = new BigDecimal("0");
			// 概算売買口数(作業用)
			BigDecimal l_bdMfEstimateDealingQty = null;
			
            // 取得した投信注文単位Paramsの概算売買口数の総計を計算する。
            for (int i = 0; i < l_lisMFOrderUnits.size(); i ++)
            {
                //取得した投信注文単位Params
                MutualFundOrderUnitRow l_orderUnitRow = 
                    (MutualFundOrderUnitRow)l_lisMFOrderUnits.get(i);

                
                //（*)(拡張投信銘柄.通貨コード != 円貨 かつget投信解約区分 = 全部) の場合、
				//    総計 = get注文数量() をセットする。                 	
                if ( (!WEB3MFOrderQuantityType.EN.equals( l_mutualFundProduct.getCurrencyCode())) 
                  && ( WEB3SellDivDef.ALL_DESIGNATE.equals(l_orderUnitRow.getFundSellDiv()))       )
                {	
                	                	
					l_bdMfEstimateDealingQty =
                        new BigDecimal(Double.toString(l_orderUnitRow.getQuantity()));                                        
                }                
                else
                {
                	                    
	                //概算売買口数の総計を計算する。
	                l_bdMfEstimateDealingQty =
	                    new BigDecimal(Double.toString(l_orderUnitRow.getEstimateDealingQty()));
	                
                }
				l_bdEstimateDealingQty =
					l_bdEstimateDealingQty.add(l_bdMfEstimateDealingQty);
            }
            
            // ２）　@取得した解約注文口数の総計を返す。
            log.exiting(l_strMethodName);
            return l_bdEstimateDealingQty.doubleValue();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In 投信注文単位テーブルを検索し ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In 投信注文単位テーブルを検索し ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
    }
    
    /**
     * (calc評価額)<BR>
     * 当該顧客の保有する銘柄の評価額を算出する。<BR>
     * <BR>
     * １)　@解約可能残高口数取得<BR>
     * 　@this.calc解約可能残高口数()をコールし、解約可能残高口数を取得する。<BR>
     * 　@　@［calc解約可能残高口数に渡すパラメタ］<BR>
     * 　@　@　@補助口座： 引数.補助口座<BR>
     * 　@　@　@拡張投信銘柄： 引数.拡張投信銘柄<BR>
     *      資産ID：　@引数.資産ID <BR>
     * <BR>
     * ２)　@評価額算出<BR>
     * 　@(2-1) 拡張投信銘柄オブジェクトの通貨コードがT0の場合<BR> 
     *     ①@ 評価額を算出し、結果をリターンする。（小数点以下四捨五入）<BR> 
     * 　@        評価額 ＝ (引数.拡張投信銘柄.get解約価額() × 解約可能残高口数)　@／　@<BR> 
     *　@　@           引数.拡張投信銘柄.get基準価額計算単位() <BR>
     * <BR>
     *   (2-2) 拡張投信銘柄オブジェクトの通貨コードがT0以外場合 <BR>
     * <BR>
     * 　@　@　@(2-2-1) 引数.拡張投信銘柄.is外貨MMF=trueの場合<BR>
     * <BR>
     * 　@　@　@　@　@①@概算受渡代金オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@　@　@　@②計算サービスのcalc外貨MMF概算受渡代金()をコールする。<BR>
     * <BR>
     * 　@　@　@　@　@　@[calc外貨MMF概算受渡代金()の引数]<BR>
     * 　@　@　@　@　@　@処理区分           ：解約<BR>
     * 　@　@　@　@　@　@注文数量           ：解約可能残高口数 <BR>
     * 　@　@　@　@　@　@決済方法@           ：円貨 <BR>
     * 　@　@　@　@　@　@拡張投信銘柄     ：引数拡張投信銘柄オブジェクト <BR>
     * 　@　@　@　@　@　@概算受渡代金     ：①@で生成した概算受渡代金オブジェクト <BR>
     * <BR>
     * 　@　@　@　@　@　@評価額　@=　@概算受渡代金オブジェクト.概算売買代金 <BR>
     * <BR>
     * 　@　@(2-2-2) 引数.拡張投信銘柄.is外貨MMF=falseの場合 <BR>
     *     ①@ 為替レートテーブル(*)より、為替レート（TTB）と為替レート計算単位を取得する。<BR> 
     *        (*)引数.拡張投信銘柄.get為替レート()にて為替レートParams取得 <BR>
     * <BR>
     *     ② 外貨での評価額を算出する。<BR>
     *        　@評価額（外貨） ＝ (引数.拡張投信銘柄.get解約価額() × 解約可能残高口数)　@／<BR>　@ 
     *　@　@　@　@　@　@　@　@引数.拡張投信銘柄.get基準価額計算単位() <BR>
     *     ③ 円貨での評価額を算出し、結果をリターンする。（小数点以下四捨五入） <BR>
     *          評価額 ＝ 評価額（外貨） × TTB　@／　@為替レート計算単位<BR>
     * <BR>
     * @@param l_subAccount - 補助口座
     * @@param l_mutualFundProduct - 拡張投信銘柄
     * @@param l_strAssetId - 資産ID
     * @@return double
     * @@roseuid 40BB0DF60203
     */
    public double calcMarketValue(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strAssetId)
        throws WEB3BaseException 
    {
        final String l_strMethodName = "calcMarketValue("
            + "SubAccount l_subAccount, "
            + "WEB3MutualFundProduct l_mutualFundProduct, String l_strAssetId)";
        log.entering(l_strMethodName);
        if (l_subAccount == null || l_mutualFundProduct == null || l_strAssetId == null)
        {
            log.debug("パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // １)　@解約可能残高口数取得 
        //  - this.calc解約可能残高口数()をコールし、解約可能残高口数を取得する。
        // ［calc解約可能残高口数に渡すパラメタ］ 
        // 　@補助口座： 引数.補助口座
        // 　@拡張投信銘柄： 引数.拡張投信銘柄 
        // 　@資産ID：　@引数.資産ID
        BigDecimal l_bdSellPossiblePositionQty =
            new BigDecimal (Double.toString(
                this.calcSellPossiblePositionQty(l_subAccount, l_mutualFundProduct, l_strAssetId)));
        log.debug("解約可能残高口数 = " + l_bdSellPossiblePositionQty);
        
        // ２)　@評価額算出
        BigDecimal l_bdMarketValue = null;
        BigDecimal l_bdSellValue =
            new BigDecimal(Double.toString(l_mutualFundProduct.getSellValue()));
        
        BigDecimal l_bdConstantValueCalcUnit =
            new BigDecimal(Double.toString(l_mutualFundProduct.getConstantValueCalcUnit()));
        
        log.debug("引数.拡張投信銘柄.get解約価額() = " + l_bdSellValue);
        log.debug("引数.拡張投信銘柄.get基準価額計算単位() = " + l_bdConstantValueCalcUnit);
        
        // 　@(2-1) 拡張投信銘柄オブジェクトの通貨コードがT0の場合
        //     ①@ 評価額を算出し、結果をリターンする。（小数点以下四捨五入）
        // 　@        評価額 ＝ (引数.拡張投信銘柄.get解約価額() × 解約可能残高口数) ／
        //　@　@           引数.拡張投信銘柄.get基準価額計算単位()
        if(WEB3MFOrderQuantityType.EN.equals(
                l_mutualFundProduct.getCurrencyCode()))
        {
            l_bdMarketValue = 
                l_bdSellValue.multiply(l_bdSellPossiblePositionQty).divide(
                    l_bdConstantValueCalcUnit, 0, BigDecimal.ROUND_HALF_UP);
        }
        //  　@(2-2) 拡張投信銘柄オブジェクトの通貨コードがT0以外の場合 
        else
        {
            // (2-2-1) 引数.拡張投信銘柄.is外貨MMF=trueの場合
            if (l_mutualFundProduct.isFrgnMmf())
            {
                // ①@概算受渡代金オブジェクトを生成する。
                WEB3MutualFundEstimatedPrice l_mfEstimatedPrice =
                    new WEB3MutualFundEstimatedPrice();

                // ②計算サービスのcalc外貨MMF概算受渡代金()をコールする。
                //         [calc外貨MMF概算受渡代金()の引数]
                //         処理区分           ：解約
                //         注文数量           ：解約可能残高口数
                //         決済方法@           ：円貨
                //         拡張投信銘柄     ：引数拡張投信銘柄オブジェクト
                //         概算受渡代金     ：①@で生成した概算受渡代金オブジェクト
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
                    (WEB3MutualFundBizLogicProvider)l_finApp.getTradingModule(
                        ProductTypeEnum.MUTUAL_FUND).getBizLogicProvider();
                l_mfBizLogicProvider.calcFrgnMmfEstimatedTradeAmount(
                    WEB3MFProcessDivDef.SELL,
                    l_bdSellPossiblePositionQty.doubleValue(),
                    WEB3SettlementDivDef.JAPANESE_CURRENCY,
                    l_mutualFundProduct,
                    l_mfEstimatedPrice);

                //     評価額　@=　@概算受渡代金オブジェクト.概算売買代金
                l_bdMarketValue =
                    new BigDecimal(WEB3StringTypeUtility.formatNumber(
                        l_mfEstimatedPrice.getEstimatedTradeAmount()));
            }

            //(2-2-2) 引数.拡張投信銘柄.is外貨MMF=falseの場合
            else
            {
                //①@ 為替レートテーブルより、為替レート（TTB）と為替レート計算単位を取得する。
                //(*)引数.拡張投信銘柄.get為替レート()にて為替レートParams取得        
                ExchangeRateParams l_exchangeRateParams = 
                    l_mutualFundProduct.getExchangeRate();
                   
                // ② 外貨での評価額を算出する。
                //     評価額（外貨） ＝ (引数.拡張投信銘柄.get解約価額() × 解約可能残高口数) ／　@ 
                //　@　@　@　@　@引数.拡張投信銘柄.get基準価額計算単位()
                BigDecimal l_bdMarketValueForeign = 
                    l_bdSellValue.multiply(l_bdSellPossiblePositionQty).divide(
                        l_bdConstantValueCalcUnit, 6, BigDecimal.ROUND_HALF_UP);
                
                //③ 円貨での評価額を算出し、結果をリターンする。（小数点以下四捨五入）
                //     評価額 ＝ 評価額（外貨） × TTB　@／　@為替レート計算単位
                BigDecimal l_bdTtBuyRate =
                    new BigDecimal(Double.toString(l_exchangeRateParams.tt_buying_rate));
                BigDecimal l_bdExchangeCalcUnit =
                    new BigDecimal(Double.toString(l_exchangeRateParams.exchange_calc_unit));
                
                l_bdMarketValue = 
                    l_bdMarketValueForeign.multiply(l_bdTtBuyRate).divide(
                        l_bdExchangeCalcUnit, 0, BigDecimal.ROUND_HALF_UP);
            }
        }
        
        log.exiting(l_strMethodName);
        return l_bdMarketValue.doubleValue();
    }
    
    /**
     * (calc評価損益)<BR>
     * 当該顧客の保有する銘柄の評価損益を算出する。<BR>
     * <BR>
     * １）　@保有残高の取得<BR>
     * 　@this.getAsset()をコールして、保有残高オブジェクトを取得する。<BR>
     * 　@　@［getAssetに渡すパラメタ］<BR>
     * 　@　@　@資産ID： 引数.資産ID <BR>
     * <BR>
     * ２）　@取得した保有残高オブジェクト.getTaxType() == TaxTypeEnum.一般口座
     * 　@で、<BR>
     * 引数.拡張投信銘柄.getMutualFundType() == MutualFundTypeEnum.国外
     * 　@の場合、<BR>
     * Double.NaN をリターンする。<BR>
     * <BR>
     * ３）　@拡張投信銘柄オブジェクト.is外貨MMF＝trueの場合、Double.NaN をリターンする。<BR>
     * <BR>
     * ４）　@解約可能残高口数取得<BR>
     * 　@this.calc解約可能残高口数()をコールし、解約可能残高口数を取得する。<BR>
     * 　@　@［calc解約可能残高口数に渡すパラメタ］<BR>
     * 　@　@　@補助口座： 引数.補助口座<BR>
     * 　@　@　@拡張投信銘柄： 引数.拡張投信銘柄<BR>
     *      資産ID： 引数.資産ID <BR>
     * <BR>
     * ５）　@評価損益算出<BR>
     * 　@(5-1) 拡張投信銘柄オブジェクトの通貨コードがT0の場合 <BR>
     * 	 ①@ 評価損益計算を行い、結果をリターンする。（小数点以下四捨五入） <BR>
     *		((引数.拡張投信銘柄.get解約価額() － 保有資産.getBookValue()) × 解約可能残高口数) <BR>
     *　@　@　@　@　@　@／ 引数.拡張投信銘柄.get基準価額計算単位() <BR>
     * <BR>
     *  (5-2) 拡張投信銘柄オブジェクトの通貨コードがT0以外の場合 <BR>
     *    ①@ 為替レートテーブルより、為替レート（TTB）と為替レート計算単位を取得する。 <BR>
     *　@     （小数点以下四捨五入） <BR>
     *       (*)引数.拡張投信銘柄.get為替レート()にて為替レートParams取得 <BR>
     * <BR>
     *    ② 評価損益計算を行い、結果をリターンする。（小数点以下四捨五入） <BR>
     *      (((引数.拡張投信銘柄.get解約価額() × TTB ／ 為替レート計算単位) － 保有資産.getBookValue()) <BR>
     *　@　@　@　@　@ × 解約可能残高口数) ／ 引数.拡張投信銘柄.get基準価額計算単位() <BR>
     * <BR>
     * @@param l_subAccount - 補助口座
     * @@param l_mutualFundProduct - 拡張投信銘柄
     * @@param l_strAssetId - 資産ID
     * @@return double
     * @@roseuid 40BB0E12005D
     */
    public double calcAppraisalProfitLoss(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strAssetId)
        throws WEB3BaseException 
    {
        final String l_strMethodName = "calcAppraisalProfitLoss("
            + "SubAccount l_subAccount, "
            + "WEB3MutualFundProduct l_mutualFundProduct, String l_strAssetId)";
        log.entering(l_strMethodName);
        if (l_subAccount == null || l_mutualFundProduct == null || l_strAssetId == null)
        {
            log.debug("パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        try
        {
            // １）　@保有資産テーブルより残高を取得する。
            MutualFundAsset l_asset =
                (MutualFundAsset)this.getAsset(Long.parseLong(l_strAssetId));
            
            // ２）取得した保有残高オブジェクト.getTaxType() == TaxTypeEnum.一般口座
            // で、引数.拡張投信銘柄.getMutualFundType() == MutualFundTypeEnum.国外
            // の場合、Double.NaN をリターンする。
            TaxTypeEnum l_taxTypeEnum = l_asset.getTaxType();
            MutualFundTypeEnum l_mfTypeEnum = l_mutualFundProduct.getMutualFundType();
            // test log
            log.debug("取得した保有残高オブジェクト.getTaxType() = " + l_taxTypeEnum);
            log.debug("引数.拡張投信銘柄.getMutualFundType() = " + l_mfTypeEnum);
            if (TaxTypeEnum.NORMAL.equals(l_taxTypeEnum) 
                && MutualFundTypeEnum.FOREIGN.equals(l_mfTypeEnum))
            {
                log.exiting(l_strMethodName);
                return  Double.NaN;    
            }

            //３）　@拡張投信銘柄オブジェクト.is外貨MMF＝trueの場合、Double.NaN をリターンする。 
            if (l_mutualFundProduct.isFrgnMmf())
            {
                log.exiting(l_strMethodName);
                return  Double.NaN; 
            }

            // ３）　@解約可能残高口数取得
            //  - this.calc解約可能残高口数()をコールし、解約可能残高口数を取得する。
            // ［calc解約可能残高口数に渡すパラメタ］
            // 　@補助口座： 引数.補助口座 
            // 　@拡張投信銘柄： 引数.拡張投信銘柄 
            // 　@資産ID： 引数.資産ID 
            BigDecimal l_bdSellPossiblePositionQty = 
                new BigDecimal(Double.toString(
                    this.calcSellPossiblePositionQty(
                        l_subAccount, l_mutualFundProduct, l_strAssetId)));

            // ４）　@評価損益算出
            BigDecimal l_bdAppraisalProfitLoss = null;            
            BigDecimal l_bdSellValue =
                new BigDecimal(Double.toString(l_mutualFundProduct.getSellValue()));
            
            BigDecimal l_bdBookValue =
                new BigDecimal(Double.toString(l_asset.getBookValue()));
            BigDecimal l_bdConstantValue =
                new BigDecimal(Double.toString(l_mutualFundProduct.getConstantValueCalcUnit()));
            
            // 　@(4-1) 拡張投信銘柄オブジェクトの通貨コードがT0の場合
            // 	 ①@ 評価損益計算を行い、結果をリターンする。（小数点以下四捨五入） 
            //		((引数.拡張投信銘柄.get解約価額() － 保有資産.getBookValue()) × 解約可能残高口数)
            //　@　@　@　@　@　@／ 引数.拡張投信銘柄.get基準価額計算単位() 
            if(WEB3MFOrderQuantityType.EN.equals(l_mutualFundProduct.getCurrencyCode()))
            {   
                l_bdAppraisalProfitLoss = 
                    (l_bdSellValue.subtract(l_bdBookValue)).multiply(
                        l_bdSellPossiblePositionQty).divide(
                            l_bdConstantValue, 0, BigDecimal.ROUND_HALF_UP);       
            }
            
            //  (4-2) 拡張投信銘柄オブジェクトの通貨コードがT0以外の場合
            else
            {
                //    ①@ 為替レートテーブルより、為替レート（TTB）と為替レート計算単位を取得する。
                //    (*)引数.拡張投信銘柄.get為替レート()にて為替レートParams取得 
				ExchangeRateParams l_exchangeRateParams = 
					l_mutualFundProduct.getExchangeRate();
                
                //    ② 評価損益計算を行い、結果をリターンする。（小数点以下四捨五入） 
                //      (((引数.拡張投信銘柄.get解約価額() × TTB ／ 為替レート計算単位) － 保有資産.getBookValue())
                //　@　@　@　@　@ × 解約可能残高口数) ／ 引数.拡張投信銘柄.get基準価額計算単位()
                BigDecimal l_bdTtBuyRate =
                    new BigDecimal(Double.toString(l_exchangeRateParams.tt_buying_rate));
                BigDecimal l_bdExchangeCalcUnit =
                    new BigDecimal(Double.toString(l_exchangeRateParams.exchange_calc_unit));
                
                l_bdAppraisalProfitLoss = 
                    (l_bdSellValue.multiply(l_bdTtBuyRate).divide(
                        l_bdExchangeCalcUnit, 10, BigDecimal.ROUND_HALF_UP).subtract(
                            l_bdBookValue)).multiply(l_bdSellPossiblePositionQty).divide(
                                l_bdConstantValue, 0, BigDecimal.ROUND_HALF_UP);
            }

            log.exiting(l_strMethodName);
            return l_bdAppraisalProfitLoss.doubleValue();
        }
        catch (NotFoundException l_ex)
        {
            log.error("該当する保有資産が不存在 with SubAccountId = " 
                + l_subAccount.getSubAccountId() + " ProductId = " 
                + l_mutualFundProduct.getProductId());
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
    }
    
    /**
     * (calc合計概算受渡代金)<BR>
     * 引数.投信銘柄に対する取引で発生した注文の概算受渡代金を合算して返却する。<BR>
     * <BR>
     * １)　@銘柄IDの取得<BR>
     * 　@引数.投信銘柄.getProductId( )をコールし、銘柄IDを取得。<BR>
     * <BR>
     * ２)　@投信注文単位テーブルを検索し、概算受渡代金の総計を計算する。<BR>
     * 　@投信注文単位テーブルを検索し、投信注文単位ParamsのListを取得する。<BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@口座ID = 引数.補助口座.getAccountId()<BR>
     * 　@　@　@AND 補助口座ID = 引数.補助口座.getSubAccountId()<BR>
     * 　@　@　@AND 銘柄ID = 引数.拡張投信銘柄.getProductId()<BR>
     * 　@　@　@AND （注文種別 = OrderTypeEnum.投資信託売注文 <BR>
     *       OR 注文種別 = OrderTypeEnum.投資信託乗換注文）<BR>
     * 　@　@　@AND (注文状態 = OrderStatusEnum.受付済（新規注文） OR 注文状態<BR>
     *  = OrderStatusEnum.発注済（新規注文）)<BR>
     * 　@　@　@AND 約定状態 != ”2：約定済”<BR>
     * <BR>
     * ３)　@取得した概算受渡代金の総計を返す。<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_mutualFundProduct - 投信銘柄<BR>
     * @@return double
     * @@roseuid 40D9564E0150
     */
    public double calcTotalEstimateDeliveryAmount(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct)
        throws WEB3BaseException 
    {
        final String l_strMethodName = "calcTotalEstimateDeliveryAmount("
            + "SubAccount l_subAccount, "
            + "WEB3MutualFundProduct l_mutualFundProduct)";
        log.entering(l_strMethodName);
        if (l_subAccount == null || l_mutualFundProduct == null)
        {
            log.debug("パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        try
        {
            // １）　@投信注文単位テーブルを検索し、解約注文口数の総計を計算する。
            List l_lisMFOrderUnits = new ArrayList();
            
            String l_strWhere = 
                "account_id = ? and sub_account_id = ? "
                + "and product_id = ? and (order_type = ? or order_type = ?) "
                + "and (order_status = ? or order_status = ?) "
                + "and exec_status != ? ";
            Object[] l_objWhereValues = {
                new Long(l_subAccount.getAccountId()),
                new Long(l_subAccount.getSubAccountId()),
                new Long(l_mutualFundProduct.getProductId()),
                // OrderTypeEnum.MF_SELL --- 202：投資信託売注文
                OrderTypeEnum.MF_SELL,
                // OrderTypeEnum.MF_SWITCHING --- 204：投資信託乗換注文）
                OrderTypeEnum.MF_SWITCHING,
                // OrderStatusEnum.ACCEPTED ---- 1：受付済（新規注文）
                OrderStatusEnum.ACCEPTED,
                // OrderStatusEnum.ORDERED --- 3：発注済（新規注文）
                OrderStatusEnum.ORDERED,
                // WEB3ExecStatusDef.EXECUTED_COMPLETE -- 約定状態 != ”2：約定済”
                WEB3ExecStatusDef.EXECUTED_COMPLETE
            };
            // -投信注文単位テーブルを検索し、投信注文単位ParamsのListを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisMFOrderUnits = l_queryProcessor.doFindAllQuery(
                MutualFundOrderUnitRow.TYPE,
                l_strWhere,
                l_objWhereValues);
            // 概算受渡代金   
            double l_dblEstimatedPrice = 0D;
            // 取得した投信注文単位Paramsの概算受渡代金の総計を計算する。
            for (int i = 0; i < l_lisMFOrderUnits.size(); i ++)
            {
                //取得した投信注文単位Params
                MutualFundOrderUnitRow l_orderUnitRow = 
                    (MutualFundOrderUnitRow)l_lisMFOrderUnits.get(i);
                l_dblEstimatedPrice += l_orderUnitRow.getEstimatedPrice();
                
            }
            // ２）　@取得した概算受渡代金の総計を返す。
            log.exiting(l_strMethodName);
            return l_dblEstimatedPrice;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In 投信注文単位テーブルを検索し ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In 投信注文単位テーブルを検索し ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
    }

    /**
     * getAssetsのオーバーロード<BR>
     * <BR>
     * 1)． 保有資産銘柄一覧を取得する <BR>
     * 　@super.getAssets()をコールし、リストを取得する。<BR>
     * 　@[引数]<BR>
     * 　@補助口座： 引数.補助口座<BR>
     * 　@ソートキー：null<BR>
     * 　@銘柄タイプ：ProductTypeEnum.投資信託 <BR>
     * <BR>
     * 2)． 引数.投信･外貨MMF表示区分が"2：両方"の場合 <BR>
     * 　@１）で取得したListをリターン<BR>
     * <BR>
     * 3). 空のListを作成する<BR>
     * <BR>
     * 4)．1)で取得したgetAssets()の戻り値の要素(=保有資産Params)数分Loop処理<BR>
     * <BR>
     * 　@4)-1)．銘柄IDを取得する<BR>
     * 　@　@　@　@AssetParams.getProductId()をコール<BR>
     * <BR>
     * 　@4)-2)．銘柄オブジェクトを取得する<BR>
     * 　@　@　@　@拡張投信銘柄マネージャ.getProduct(銘柄ID)をコール <BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@銘柄ID: 4)-1)で取得した保有資産Params.getProductId( )<BR>
     * <BR>
     * 　@4)-3)．拡張投信銘柄を取得する。<BR>
     * 　@　@　@　@拡張投信銘柄マネージャ.to銘柄(Row)をコール<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@銘柄Row: 4)-2)で取得したgetProduct()の戻り値<BR>
     * <BR>
     * 　@4)-4). 4)-3)で取得した拡張投信銘柄.is外貨MMF()==falseかつ <BR>
     * 　@　@　@　@引数.投信･外貨MMF表示区分が"0:投信のみ"の場合 <BR>
     * <BR>
     * 　@4)-4)-1). 3)で用意したListへ現在行の要素を追加する。<BR>
     * <BR>
     * 　@4)-5). 4)-3)で取得した拡張投信銘柄.is外貨MMF()==trueかつ <BR>
     * 　@　@　@　@引数.投信･外貨MMF表示区分が"1:外貨MMFのみ"の場合 <BR>
     * <BR>
     * 　@4)-5)-1). 3)で用意したListへ現在行の要素を追加する。<BR>
     * <BR>
     * 5). 3)で用意したListをリターンする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_strMutualFrgnMmfDisplayDiv - (投信･外貨MMF表示区分)<BR>
     * 投信･外貨MMF表示区分 <BR>
     * <BR>
     * 0:投信のみ<BR>
     * 1:外貨MMFのみ <BR>
     * 2:両方 <BR>
     * <BR>
     * ※nullの場合、「0:投信のみ」とする<BR>
     * @@return List
     * @@throws WEB3BaseException 
     */
    public List getAssets(
        SubAccount l_subAccount,
        String l_strMutualFrgnMmfDisplayDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAssets(SubAccount, String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1)． 保有資産銘柄一覧を取得する
        //  super.getAssets()をコールし、リストを取得する。
        //  [引数]
        //  補助口座： 引数.補助口座
        //  ソートキー：null
        //  銘柄タイプ：ProductTypeEnum.投資信託
        List l_lisAssets = super.getAssets(
            l_subAccount,
            null,
            ProductTypeEnum.MUTUAL_FUND);

        //2)． 引数.投信･外貨MMF表示区分が"2：両方"の場合
        //    １）で取得したListをリターン
        if (WEB3MutualFrgnMmfDisplayDivDef.BOTH.equals(l_strMutualFrgnMmfDisplayDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return l_lisAssets;
        }

        //3). 空のListを作成する
        List l_lisReturnList = new ArrayList();

        //4)．1)で取得したgetAssets()の戻り値の要素(=保有資産Params)数分Loop処理
        int l_intListSize = 0;
        if (l_lisAssets != null || l_lisAssets.size() != 0)
        {
            l_intListSize = l_lisAssets.size();
        }
        for (int i = 0; i < l_intListSize; i++)
        {
            AssetRow l_assetRow =
                (AssetRow)(((Asset)l_lisAssets.get(i)).getDataSourceObject());
            //  4)-1)．銘柄IDを取得する
            //         AssetParams.getProductId()をコール
            long l_lngProductId = l_assetRow.getProductId();

            //  4)-2)．銘柄オブジェクトを取得する
            //         拡張投信銘柄マネージャ.getProduct(銘柄ID)をコール
            //         [引数]
            //         銘柄ID: 4)-1)で取得した保有資産Params.getProductId( )
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
            WEB3MutualFundProductManager l_mfProductManager =
                (WEB3MutualFundProductManager) l_tradingModule.getProductManager();
            Product l_product = null;
            try
            {
                l_product = l_mfProductManager.getProduct(l_lngProductId);
            }
            catch (NotFoundException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName()
                        + "."
                        + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
            }

            //  4)-3)．拡張投信銘柄を取得する。
            //         拡張投信銘柄マネージャ.to銘柄(Row)をコール
            //         [引数]
            //         銘柄Row: 4)-2)で取得したgetProduct()の戻り値
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct)l_mfProductManager.toProduct(
                    (MutualFundProductRow)l_product.getDataSourceObject());

            //  4)-4). 4)-3)で取得した拡張投信銘柄.is外貨MMF()==falseかつ
            //         引数.投信･外貨MMF表示区分が"0:投信のみ"の場合
            if (!l_mutualFundProduct.isFrgnMmf()
                && WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(l_strMutualFrgnMmfDisplayDiv))
            {
                //    4)-4)-1). 3)で用意したListへ現在行の要素を追加する。
                l_lisReturnList.add(this.toAsset(l_assetRow));
            }

            //  4)-5). 4)-3)で取得した拡張投信銘柄.is外貨MMF()==trueかつ
            //         引数.投信･外貨MMF表示区分が"1:外貨MMFのみ"の場合
            if (l_mutualFundProduct.isFrgnMmf()
                && WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(l_strMutualFrgnMmfDisplayDiv))
            {
                //    4)-5)-1). 3)で用意したListへ現在行の要素を追加する。
                l_lisReturnList.add(this.toAsset(l_assetRow));
            }
        }

        //5). 3)で用意したListをリターンする。
        log.exiting(STR_METHOD_NAME);
        return l_lisReturnList;
    }

    /**
     * (get未収分配金)<BR>
     * 未収分配金を返却する。<BR>
     * <BR>
     * 1). 以下の条件で投信保有資産補助テーブルより行オブジェクトを取得する。<BR>
     * 　@資産ID = 引数.資産ID <BR>
     * <BR>
     * 2). レコードを取得できない場合、0をリターン。<BR>
     * <BR>
     * 3). 概算受渡代金オブジェクトを生成する。<BR>
     * <BR>
     * 4). 円転した未収分配金を取得する。<BR>
     * 　@計算サービス.calc外貨MMF概算受渡代金()<BR>
     * 　@[引数]<BR>
     * 　@処理区分:解約<BR>
     * 　@注文数量:取得した行オブジェクトのget未収分配金残高<BR>
     * 　@　@　@　@　@　@(小数点以下切り捨て)<BR>
     * 　@決済方法@:円貨<BR>
     * 　@拡張投信銘柄:引数.拡張投信銘柄オブジェクト<BR>
     * 　@概算受渡代金:生成した概算受渡代金オブジェクト<BR>
     * <BR>
     * 5). 概算受渡代金オブジェクト.概算売買代金をリターンする。<BR>
     * <BR>
     * @@param l_strAssetId - (資産ID)<BR>
     * 資産ID<BR>
     * @@param l_mutualFundProduct- (拡張投信銘柄)<BR>
     * 拡張投信銘柄<BR>
     * @@return long
     * @@throws WEB3BaseException 
     */
    public long getUnreceiveDist(String l_strAssetId, WEB3MutualFundProduct l_mutualFundProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUnreceiveDist(String)";
        log.entering(STR_METHOD_NAME);

        if (l_strAssetId == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1). 以下の条件で投信保有資産補助テーブルより行オブジェクトを取得する
        List l_lisRow = new ArrayList();
        String l_strWhere = " asset_id = ? ";
        Object[] l_objWheres = new Object[]{l_strAssetId};
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRow =
                l_queryProcessor.doFindAllQuery(
                    MfSubAssetRow.TYPE,
                    l_strWhere,
                    l_objWheres);
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

        //レコードを取得できない場合、0をリターン。
        if (l_lisRow == null || l_lisRow.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //概算受渡代金オブジェクトを生成する。
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = new WEB3MutualFundEstimatedPrice();

        //取得した行オブジェクトのget未収分配金残高
        MfSubAssetRow l_mfSubAssetRow = (MfSubAssetRow)l_lisRow.get(0);
        double l_dblUnreceiveDist = l_mfSubAssetRow.getUnreceiveDist();
        BigDecimal l_bdUnreceiveDist =
            new BigDecimal(WEB3StringTypeUtility.formatNumber(l_dblUnreceiveDist));
        BigDecimal l_bdUnreceiveDistRDown =
            l_bdUnreceiveDist.setScale(0,BigDecimal.ROUND_DOWN);

        //計算サービス
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
            (WEB3MutualFundBizLogicProvider)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getBizLogicProvider();
        //円転した未収分配金を取得する。
        //計算サービス.calc外貨MMF概算受渡代金()
        //[引数]
        //処理区分:解約
        //注文数量:取得した行オブジェクトのget未収分配金残高
        //              (小数点以下切り捨て)
        //決済方法@:円貨
        //拡張投信銘柄:引数.拡張投信銘柄オブジェクト
        //概算受渡代金:生成した概算受渡代金オブジェクト
        l_mfBizLogicProvider.calcFrgnMmfEstimatedTradeAmount(
            WEB3ProcessDivDef.SELL,
            l_bdUnreceiveDistRDown.doubleValue(),
            WEB3SettlementDivDef.JAPANESE_CURRENCY,
            l_mutualFundProduct,
            l_mfEstimatedPrice);

        //概算受渡代金オブジェクト.概算売買代金をリターンする。
        double l_dblEstimatedTradeAmount =
            l_mfEstimatedPrice.getEstimatedTradeAmount();
        BigDecimal l_bdEstimatedTradeAmount =
            new BigDecimal(WEB3StringTypeUtility.formatNumber(l_dblEstimatedTradeAmount));

        log.exiting(STR_METHOD_NAME);
        return l_bdEstimatedTradeAmount.longValue();
    }
}
@
