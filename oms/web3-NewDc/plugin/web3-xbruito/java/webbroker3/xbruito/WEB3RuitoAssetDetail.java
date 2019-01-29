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
filename	WEB3RuitoAssetDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投保有資産明細オブジェクト(WEB3RuitoAssetDetail)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/08  李志強 (中訊) 新規作成
*/
package webbroker3.xbruito;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
/**
 * 累投保有資産明細オブジェクト<BR>
 */
public class WEB3RuitoAssetDetail
{
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3RuitoAssetDetail.class);	
		
	/**
	 * 銘柄ID<BR>
	 */
	private long productID;
	/**
	 * 累投残高口数<BR>
	 */
	private double balance;
	/**
	 * 累投にうち30日を経過した残高<BR>
	 */
	private double countAfterPenalty;
	/**
	 * 累投で30日を経過していない解約ペナルティ対象残高<BR>
	 */
	private double countBeforePenalty;
	/**
	 * 信託財産留保額　@＝　@30日未経過残高 × 0.001<BR>
	 */
	private double trustFortunePenaltyPrice;
	/**
	 * 解約可能残高 ＝ 残高 － 信託財産留保額 － 解約中注文数量<BR>
	 */
	private double sellPossibleBalance;
	/**
	 * 解約注文明細<BR>
	 */
	private WEB3RuitoSellOrderDetail[] sellOrderDetail;
	/**
	 * 累投タイプ<BR>
	 * （RuitoTypeEnumにて定義）<BR>
	 * <BR>
	 * ３：中期国債ファ@ンド<BR>
	 * ４：MMF<BR>
	 */
	private RuitoTypeEnum ruitoType;
	/**
	 * 解約注文の合計を設定する<BR>
	 */
	private double sellOrderTotal;
	/**
	 * 累投保有資産明細オブジェクト<BR>
	 */
	public WEB3RuitoSellOrderDetail theWEB3RuitoSellOrderDetail[];

	/**
	 * コンストラクタ<BR>
	 * １）　@引数の値を各属性に設定する。<BR> 
	　@  *     －引数.銘柄IDをthis.銘柄IDに設定する。<BR> 
	　@  *     －引数.残高をthis.残高に設定する。 <BR>
	　@  *     －引数.30日未経過残高をthis.30日未経過残高に設定する。<BR> 
	　@  *     －引数.累投タイプをthis.累投タイプに設定する。 <BR>
	　@  *     －以下の計算結果をthis.30日経過残高に設定する。 <BR>
	　@　@*　@     引数.残高 - 引数.30日未経過残高<BR>
	 * @@roseuid 40BC3716036C<BR>
	 */
	public WEB3RuitoAssetDetail(
		long l_lngProductId,
		double l_dblBalance,
		double l_countBeforePenalty,
		RuitoTypeEnum l_ruitoType)
	{
		final String STR_METHOD_NAME = "WEB3RuitoAssetDetail(long l_lngProductId," 
		    + " double l_dblBalance, double l_countBeforePenalty, RuitoTypeEnum l_ruitoType)";
		log.entering(STR_METHOD_NAME);

		productID = l_lngProductId;
		balance = l_dblBalance;
		countBeforePenalty = l_countBeforePenalty;
		ruitoType = l_ruitoType;
        countAfterPenalty = l_dblBalance - l_countBeforePenalty;
        
        log.debug("productID = " + productID);
        log.debug("balance = " + balance);
        log.debug("countBeforePenalty = " + countBeforePenalty);
        log.debug("ruitoType = " + ruitoType);
        log.debug("countAfterPenalty = " + countAfterPenalty);
		log.exiting(STR_METHOD_NAME);
	}
	/**
	 * 残高の設定を行う。<BR>
	 * @@param l_dblBalance
	 * @@roseuid 40765A2102E7
	 */
	public void setBalance(double l_dblBalance)
	{
		log.debug("l_dblBalance = " + l_dblBalance);
		balance = l_dblBalance;
	}
	/**
	 * this.残高を返却する<BR>
	 * @@return double
	 * @@roseuid 40765A8A0307
	 */
	public double getBalance()
	{
		log.debug("balance = " + balance);
		return balance;
	}
	/**
	 * 30日経過残高の設定を行う。<BR>
	 * @@param l_dblCount
	 * @@roseuid 40765A2B026A
	 */
	public void setCountAfterPenalty(double l_dblCount)
	{
		log.debug("l_dblCount = " + l_dblCount);
		countAfterPenalty = l_dblCount;
	}
	/**
	 * this.30日経過残高を返却する。<BR>
	 * @@return double
	 * @@roseuid 40765A970190
	 */
	public double getCountAfterPenalty()
	{
		log.debug("countAfterPenalty = " + countAfterPenalty);
		return countAfterPenalty;
	}
	/**
	 * 30日未経過残高の設定を行う。<BR>
	 * @@param l_dblCount
	 * @@roseuid 40765A3B03D2
	 */
	public void setCountBeforePenalty(double l_dblCount)
	{
		log.debug("l_dblCount = " + l_dblCount);
		countBeforePenalty = l_dblCount;
	}
	/**
	 * this.30日未経過残高を返却する<BR>
	 * @@return double
	 * @@roseuid 40765AA80019
	 */
	public double getCountBeforePenalty()
	{
		log.debug("countBeforePenalty = " + countBeforePenalty);
		return countBeforePenalty;
	}
	/**
	 * 信託財産留保額の設定を行う。<BR>
	 * @@param l_dblPrice
	 * @@roseuid 40765A4C0067
	 */
	public void setTrustFortunePenaltyPrice(double l_dblPrice)
	{
		log.debug("l_dblPrice = " + l_dblPrice);
		trustFortunePenaltyPrice = l_dblPrice;
	}
	/**
	 * this.信託財産留保額を返却する。<BR>
	 * @@return double
	 * @@roseuid 40765AB50384
	 */
	public double getTrustFortunePenaltyPrice()
	{
		log.debug("trustFortunePenaltyPrice = " + trustFortunePenaltyPrice);
		return trustFortunePenaltyPrice;
	}
	/**
	 * 解約可能残高の設定を行う。<BR>
	 * @@param l_dblBalance
	 * @@roseuid 40765A6200C4
	 */
	public void setSellPossibleBalance(double l_dblBalance)
	{
		log.debug("l_dblBalance = " + l_dblBalance);
		sellPossibleBalance = l_dblBalance;
	}
	/**
	 * this.解約可能残高を返却する。<BR>
	 * @@return double
	 * @@roseuid 40765AC40086
	 */
	public double getSellPossibleBalance()
	{
		log.debug("sellPossibleBalance = " + sellPossibleBalance);
		return sellPossibleBalance;
	}
	/**
	 * 累投タイプを設定する。<BR>
	 * @@param l_ruitoType
	 * @@roseuid 407671A30190
	 */
	public void setRuitoType(RuitoTypeEnum l_ruitoType)
	{
		log.debug("l_ruitoType = " + l_ruitoType);
		ruitoType = l_ruitoType;
	}
	/**
	 * this.累投タイプを取得する<BR>
	 * @@return RuitoTypeEnum
	 * @@roseuid 407671C50384
	 */
	public RuitoTypeEnum getRuitoType()
	{
		log.debug("ruitoType = " + ruitoType);
		return ruitoType;
	}
	/**
	 * 銘柄IDを設定する。<BR>
	 * @@param l_lngProductID
	 * @@roseuid 40767D9D0364
	 */
	public void setProductID(long l_lngProductID)
	{
		log.debug("l_lngProductID = " + l_lngProductID);
		productID = l_lngProductID;
	}	
	/**
	 * this.銘柄IDを取得する<BR>
	 * @@return long
	 * @@roseuid 407671C50384
	 */
	public long getProductID()
	{
		log.debug("productID = " + productID);
		return productID;
	}
	/**
	 * 解約注文合計を設定する。<BR>
	 * @@param l_dblSellOrderTatal
	 * @@roseuid 4079F8A90236
	 */
	public void setSellOrderTotal(double l_dblSellOrderTatal)
	{
		log.debug("l_dblSellOrderTatal = " + l_dblSellOrderTatal);
		sellOrderTotal = l_dblSellOrderTatal;
	}
	/**
	 * this.解約注文合計を返却する<BR>
	 * @@return double
	 * @@roseuid 4079F8C00294
	 */
	public double getSellOrderTotal()
	{
		log.debug("sellOrderTotal = " + sellOrderTotal);
		return sellOrderTotal;
	}
	/**
	 * 解約注文明細オブジェクトを設定する。<BR>
	 * @@param l_sellOrderDetail
	 * @@roseuid 407E3705029E
	 */
	public void setSellOrderDetail(WEB3RuitoSellOrderDetail[] l_sellOrderDetail)
	{
		log.debug("l_sellOrderDetail = " + l_sellOrderDetail);
		sellOrderDetail = l_sellOrderDetail;
	}
	/**
	 * this.解約注文明細オブジェクトを取得する<BR>
	 * @@return webbroker3.xbruito.WEB3RuitoSellOrderDetail
	 * @@roseuid 407E36F10389
	 */
	public WEB3RuitoSellOrderDetail[] getSellOrderDetail()
	{
		log.debug("sellOrderDetail = " + sellOrderDetail);
		return sellOrderDetail;
	}
}
@
