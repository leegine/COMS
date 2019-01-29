head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoPositionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張累投ポジションマネージャ(WEB3RuitoPositionManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09  李志強 (中訊) 新規作成
                   2004/12/01  韋念瓊 (中訊) 残対応
*/
package webbroker3.xbruito;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoAsset;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.stdimpls.RuitoPositionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CashBalanceflagDef;
import webbroker3.util.WEB3LogUtility;
/**
 * 拡張累投ポジションマネージャ<BR>
 */
public class WEB3RuitoPositionManager extends RuitoPositionManagerImpl
{
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3RuitoPositionManager.class);
	/**
     * (get累投保有資産明細)<BR>
	 * 顧客の累投保有資産の情報を取得する。<BR>
	 * <BR>
	 * シーケンス図「get累投保有資産明細」参照<BR>
	 *
	 * １）累投解約注文取得<BR>
	 * 　@－以下の条件で累投注文単位テーブルを検索し、<BR>
	 * 　@　@　@累投注文単位ParamsのListを取得する。<BR>
	 * <BR>
	 * 　@　@[検索条件]<BR>
	 * 　@　@   口座ID = 引数.補助口座.getAccountId()の戻り値 <BR>
	 * 　@　@   銘柄ID = 引数.累投保有資産明細.get銘柄ID()の戻り値 <BR>
	 * 　@　@　@ 注文種別　@=　@OrderTypeEnum.RUITO_SELL<BR>
	 * 　@　@　@ 銘柄タイプ　@=　@7：累積投資 <BR>
	 * 　@　@　@ 残高計上フラグ = 0：残高計上未済 <BR>
	 * 　@　@　@ 注文状態　@=　@1：受付済（新規注文）　@or　@3：発注済（新規注文） <BR>
	 * <BR>
	 * 　@－累投注文単位Params.get注文数量()の合計を算出して、<BR>
	 * 　@　@　@引数.累投保有資産明細.解約注文合計に設定する。<BR>
	 *     該当する累投注文単位が１件も無い場合は、0 を設定する。<BR>
	 * <BR>
	 * ２） 解約可能残高計算<BR>
	 * <BR>
	 * 　@this.calc解約可能残高()をコールし、<BR>
	 *     引数.累投保有資産明細に信託財産留保額と<BR>　@
	 *      解約可能残高を設定する。<BR>
	 * 　@［calc解約可能残高に渡すパラメタ］<BR>
	 * 　@　@ 累投保有資産明細： 引数.累投保有資産明細<BR>
	 * <BR>
	 * ３） 解約注文明細設定 <BR>　@　@
	 *   １）で取得した累投注文単位の件数 ＞ 0 の場合、以下の処理を行う。<BR>
	 * <BR>
	 *     －累投解約注文明細オブジェクトの配列 <BR>
	 *        （要素数は取得した累投注文単位の件数) <BR>
	 *          を生成し、引数.累投保有資産明細.解約注文明細に設定する。<BR>
	 * 　@  －各累投解約注文明細オブジェクトに以下の設定を行う 。<BR>
	 * <BR>
	 *  累投解約注文明細.注文時間（注文受付日) <BR>
	 *      ：累投注文単位Params.get注文時間() <BR> 　@　@
	 *  累投解約注文明細.注文状態区分 <BR>
	 *      ：累投注文単位Params.get注文状態区分() <BR>
	 *  累投解約注文明細.注文数量タイプ <BR>
	 *      ：累投注文単位Params.get注文数量タイプ() <BR>　@　@
	 *  累投解約注文明細.注文数量 <BR>
	 *      ：累投注文単位Params.get注文数量() <BR>　@　@
	 * @@param l_subAccount - 補助口座<BR>
	 * @@param l_ruitoAssetDetail - 累投保有資産明細<BR>　@　@　@　@　@
     * @@throws WEB3BaseException
	 */
	public void getRuitoAssetGroup(
		SubAccount l_subAccount,
		WEB3RuitoAssetDetail l_ruitoAssetDetail)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
		    "getRuitoAssetGroup(SubAccount l_subAccount, "
		     + "WEB3RuitoAssetDetail l_ruitoAssetDetail)";
		
		log.entering(STR_METHOD_NAME);
		
		if (l_subAccount == null || l_ruitoAssetDetail == null)
		{
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");            
		}

		//１）累投解約注文取得
		List l_lisResults = null;
		long l_lngAccountId = 0; //口座ID
		long l_lngProductId = 0; //銘柄ID
		l_lngAccountId = l_subAccount.getAccountId();
		l_lngProductId = l_ruitoAssetDetail.getProductID();
        
		QueryProcessor processor = null;
		try
		{
			log.debug("口座ID = " + l_lngAccountId);
			log.debug("銘柄ID = " + l_lngProductId);

			processor = Processors.getDefaultProcessor();
			String l_strWhere =   " account_id = ? and product_id = ?         "
					            + " and order_type = ? and product_type=?     "
					            + " and balance_add_flag = ?                  "
					            + " and  (order_status =? or order_status =?) ";
			String l_strCondition = " nowait";
			Object[] l_strBindValues = new Object[7];
			l_strBindValues[0] = "" + l_lngAccountId;
			l_strBindValues[1] = "" + l_lngProductId;
			l_strBindValues[2] = OrderTypeEnum.RUITO_SELL;
			l_strBindValues[3] = ProductTypeEnum.RUITO;
			l_strBindValues[4] = WEB3CashBalanceflagDef.CASH_BALANCE_NO_CLEAR;
			l_strBindValues[5] = OrderStatusEnum.ACCEPTED;
			l_strBindValues[6] = OrderStatusEnum.ORDERED;
			//累投注文単位ParamsのListを取得する。
			//DataQueryException または　@DataNetworkExceptionをthrow

            l_lisResults = 
            	processor.doFindAllQuery(
            		RuitoOrderUnitParams.TYPE,
            		l_strWhere,
            		null,
            		l_strBindValues);
		}
		
        catch (DataQueryException l_ex)
        {
            log.debug("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
		//該当する累投注文単位が１件も無い場合は、0 を設定する。
		if (l_lisResults == null || l_lisResults.size() == 0)
		{
			log.debug("[getRuitoAssetGroup] l_results.size() = 0");
			l_ruitoAssetDetail.setSellOrderTotal(0);
		}
		else
		{ 
			log.debug("[getRuitoAssetGroup] l_results.size() = " + l_lisResults.size());
			
			//累投注文単位Params.get注文数量()の合計を算出
			double l_dblSum = 0;
			for (int i = 0; i < l_lisResults.size(); i++)
			{
				RuitoOrderUnitParams ruitoOrderUnitParams =
					(RuitoOrderUnitParams) l_lisResults.get(i);
				l_dblSum += ruitoOrderUnitParams.getQuantity();
			}
			
			log.debug("[getRuitoAssetGroup] l_dblSum = " + l_dblSum);

			//合計を累投保有資産明細.解約注文合計に設定する。
			l_ruitoAssetDetail.setSellOrderTotal(l_dblSum);
		}
		//２）解約可能残高計算
		this.calcSellPossibleBalance(l_ruitoAssetDetail);
				
		//３）解約注文明細設定
		//累投注文単位の件数 ＞ 0 の場合
		if (l_lisResults != null && l_lisResults.size() > 0)
		{
			WEB3RuitoSellOrderDetail[] l_sellOrderDetail =
				new WEB3RuitoSellOrderDetail[l_lisResults.size()];
			
			//解約注文明細に設定する
			for (int i = 0; i < l_lisResults.size(); i++)
			{
				RuitoOrderUnitParams ruitoOrderUnitParams =
					(RuitoOrderUnitParams) l_lisResults.get(i);
					
				log.debug("[getRuitoAssetGroup] i = " + i);
				log.debug("[getRuitoAssetGroup] OrderTime = " 
					+ ruitoOrderUnitParams.getReceivedDateTime());
				log.debug("[getRuitoAssetGroup] OrderStatusDiv = " 
					+ ruitoOrderUnitParams.getOrderStatus().intValue());
				log.debug("[getRuitoAssetGroup] OrderQuantityType = " 
					+ ruitoOrderUnitParams.getQuantityType().intValue());
				log.debug("[getRuitoAssetGroup] Quantity = " 
					+ ruitoOrderUnitParams.getQuantity());

				l_sellOrderDetail[i] = new WEB3RuitoSellOrderDetail();
				
				//注文時間
				l_sellOrderDetail[i].setOrderTime(
					ruitoOrderUnitParams.getReceivedDateTime());
				//注文状態区分
				l_sellOrderDetail[i].setOrderStatusDiv(
					ruitoOrderUnitParams.getOrderStatus().intValue() + "");
				//注文数量タイプ
				l_sellOrderDetail[i].setOrderQuantityType(
					ruitoOrderUnitParams.getQuantityType().intValue() + "");
				//注文数量
				l_sellOrderDetail[i].setOrderQuantity(
					ruitoOrderUnitParams.getQuantity());
			}
			//累投保有資産明細の解約注文明細に設定する
			l_ruitoAssetDetail.setSellOrderDetail(l_sellOrderDetail);
		}
		
		log.exiting(STR_METHOD_NAME);
	}
	/**
	 * 解約可能残高を計算して返却する<BR>
	 * <BR>
	 * 1) －信託財産留保額を算出する。<BR>
	 * <BR>
	 * 　@　@　@　@30日未経過残高が 0 の場合は、<BR>
	 * 　@　@　@　@　@　@信託財産留保額 = 0<BR>
	 * 　@　@　@　@30日未経過残高が 0 以外の場合は、<BR>
	 * 　@　@　@　@　@　@信託財産留保額　@=　@<BR>
	 * 　@　@　@　@　@　@　@　@　@　@累投保有資産明細.30日未経過残高　@×　@0.001 <BR>
	 * <BR>
	 * 　@　@　@　@算出した信託財産留保額を、累投保有資産明細.信託財産留保額<BR>
	 * 　@　@　@　@に設定する。<BR>
	 * <BR>
	 * 2)  －引数.累投保有資産明細の残高、解約中注文合計より、<BR>
	 *       解約可能残高を計算する. <BR>
	 * 　@  －解約可能残高を計算する。　@<BR>
	 * 　@ 　@　@ 解約可能残高　@=　@累投保有資産明細.残高 <BR>
	 * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@－ 累投保有資産明細.信託財産留保額 <BR>
	 * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@－ 累投保有資産明細.解約中注文合計<BR>
	 * <BR>
	 * 　@　@　@　@算出した解約可能残高を、<BR>
	 *         累投保有資産明細.解約可能残高に設定する。<BR>
	 * <BR>
	 * @@param l_ruitoAssetDetail - 累投保有資産明細<BR>
     * @@throws WEB3BaseException
	 * @@roseuid 40765742020D
	 */
	public void calcSellPossibleBalance(WEB3RuitoAssetDetail l_ruitoAssetDetail) 
        throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"calcSellPossibleBalance(WEB3RuitoAssetDetail l_ruitoAssetDetail)";				
		log.entering(STR_METHOD_NAME);
		
		if (l_ruitoAssetDetail == null)
		{			
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");            
		}
		
		log.debug("balance = " + l_ruitoAssetDetail.getBalance());
		log.debug("sellOrderTotal = " + l_ruitoAssetDetail.getSellOrderTotal());
		log.debug("countBeforePenalty = " + l_ruitoAssetDetail.getCountBeforePenalty());

		double l_dblPenaltyPrice = 0.0; //信託財産留保額
		//30日未経過残高が 0 の場合
		if (l_ruitoAssetDetail.getCountBeforePenalty() == 0.0)
		{
			l_ruitoAssetDetail.setTrustFortunePenaltyPrice(l_dblPenaltyPrice);
		}
		//30日未経過残高が 0 以外の場合
		else
		{
			l_dblPenaltyPrice =
				l_ruitoAssetDetail.getCountBeforePenalty() * 0.001;
            l_dblPenaltyPrice = (int)l_dblPenaltyPrice;
            l_ruitoAssetDetail.setTrustFortunePenaltyPrice(l_dblPenaltyPrice);
		}
		
		log.debug("trustFortunePenaltyPrice = " + l_ruitoAssetDetail.getTrustFortunePenaltyPrice());
		
		//解約可能残高を計算
        log.debug("残高 = " + l_ruitoAssetDetail.getBalance());
        log.debug("信託財産留保額 = " + l_ruitoAssetDetail.getTrustFortunePenaltyPrice());
        log.debug("解約注文合計 = " + l_ruitoAssetDetail.getSellOrderTotal());
        
		double l_dblBalance = l_ruitoAssetDetail.getBalance()
				              - l_ruitoAssetDetail.getTrustFortunePenaltyPrice()
				              - l_ruitoAssetDetail.getSellOrderTotal();
		//解約可能残高に設定する
		l_ruitoAssetDetail.setSellPossibleBalance(l_dblBalance);
		
		log.exiting(STR_METHOD_NAME);
	}
	/**
	 * （updateLockedQuantityのオーバーライド）<BR>
	 * <BR>
	 * 何も処理を行わない。<BR>
	 * @@param l_lngAccountID - 口座ID<BR>
	 * @@param l_lngSubAccountID - 補助口座ID<BR>
	 * @@param l_lngOrderUnitID - 注文単位ID<BR>
	 * @@param l_lngProductID - 銘柄ID<BR>
	 * @@param l_dblLockedQuantity - ロックしようとするプラスかマイナスの数量<BR>
	 * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
	 * @@roseuid 4092096E033B
	 */
	public void updateLockedQuantity(
		long l_lngAccountID,
		long l_lngSubAccountID,
		long l_lngOrderUnitID,
		long l_lngProductID,
		double l_dblLockedQuantity)
		throws RuntimeSystemException
	{
	}	
    
    /**
     * (get解約可能残高)
     * 解約可能残高を取得する。<BR>
     * <BR>
     * １．拡張累投ポジションマネージャー.getAseetをコールして、指定銘柄の保有資産<BR>
     * 　@　@オブジェクトを取得する。<BR>
     * 　@　@［getAssetに渡すパラメタ］<BR>
     * 　@　@　@補助口座： 引数.補助口座<BR>
     * 　@　@　@銘柄： 引数.拡張累投銘柄<BR>
     * 　@　@getAsset()が例外をスローした場合は、例外をスローする。<BR>
     * <BR>
     * ２．累投保有資産明細オブジェクトを生成し、保有資産の情報を設定する。<BR>
     * 　@　@［累投保有資産明細オブジェクトに設定する値］<BR>
     * 　@　@　@　@銘柄ID： 保有資産オブジェクト.getProduct().getProductId()の戻り値<BR>
     * 　@　@　@　@残高： 保有資産オブジェクト.getQuantity()の戻り値<BR>
     * 　@　@　@　@30日未経過残高口数： 保有資産オブジェクト.getDataSourceObject().<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@get30日未経過残高口数の戻り値<BR>
     *         累投タイプ：引数.拡張累投銘柄.get累投タイプ()の戻り値<BR>
     * <BR>
     * ３．累投拡張ポジションマネージャ.get累投保有資産明細()をコールし、<BR>
     * 　@　@　@銘柄単位の保有資産明細を取得する。<BR>
     * 　@　@［get累投保有資産明細に渡すパラメタ］<BR>
     * 　@　@　@補助口座： 引数.補助口座<BR>
     * 　@　@　@累投保有資産明細： 生成した累投保有資産明細<BR>
     * <BR>
     * ４．累投保有資産明細.解約可能残高を返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_ruitoProducts - (拡張累投銘柄)
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 41084E1D02C5
     */     
    public double getSellPossibleBalance(
        SubAccount l_subAccount,
        WEB3RuitoProduct l_ruitoProduct)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getSellPossibleBalance(" +
            "SubAccount l_subAccount,WEB3RuitoProduct l_ruitoProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_ruitoProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        //１．拡張累投ポジションマネージャー.getAseetをコールして、
        //指定銘柄の保有資産オブジェクトを取得する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3RuitoPositionManager l_web3RuitoPositionManager =
            (WEB3RuitoPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getPositionManager();

        RuitoAsset l_ruitoAsset = null;
        try
        {
            l_ruitoAsset = (RuitoAsset)l_web3RuitoPositionManager.getAsset(
                l_subAccount, l_ruitoProduct);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFoundException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //２．累投保有資産明細オブジェクトを生成し、保有資産の情報を設定する。
        AssetRow l_ruitoAssetRow =  
            (AssetRow) l_ruitoAsset.getDataSourceObject();
        //30日未経過残高口数
        double l_dblCountBeforePenalty = l_ruitoAssetRow.getCountBeforePenalty();
        RuitoTypeEnum l_ruitoTypeEnum = l_ruitoProduct.getRuitoType();

        WEB3RuitoAssetDetail l_web3RuitoAssetDetail =
            new WEB3RuitoAssetDetail(
                l_ruitoAsset.getProduct().getProductId(),
                l_ruitoAsset.getQuantity(),
                l_dblCountBeforePenalty,
                l_ruitoTypeEnum);
    
        //３．累投拡張ポジションマネージャ.get累投保有資産明細()をコールし、     
        //銘柄単位の保有資産明細を取得する。
        l_web3RuitoPositionManager.getRuitoAssetGroup(
                l_subAccount, l_web3RuitoAssetDetail);       
                
        l_web3RuitoPositionManager.calcSellPossibleBalance(l_web3RuitoAssetDetail);        
        
        log.exiting(STR_METHOD_NAME);    
        //４．累投保有資産明細.解約可能残高を返却する。
        return l_web3RuitoAssetDetail.getSellPossibleBalance();     
    }
}
@
