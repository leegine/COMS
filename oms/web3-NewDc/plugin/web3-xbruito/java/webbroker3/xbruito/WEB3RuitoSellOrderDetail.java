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
filename	WEB3RuitoSellOrderDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資解約注文明細オブジェクト(WEB3RuitoSellOrderDetail)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/08  李志強 (中訊) 新規作成
*/
package webbroker3.xbruito;
import java.sql.Timestamp;

import webbroker3.util.WEB3LogUtility;
/**
 * 累積投資解約注文明細オブジェクト<BR>　@
 */
public class WEB3RuitoSellOrderDetail
{
	private static WEB3LogUtility log =
	    WEB3LogUtility.getInstance(WEB3RuitoSellOrderDetail.class);

	/**
	 * 注文時間<BR>
	 */
	private Timestamp orderTime;
	/**
	 *  1:受付済(新規注文)　@ 3:発注済(新規注文)　@　@<BR>　@
	 *   6:発注失敗(新規注文) 12:受付済(取消注文)　@<BR>　@
	 * 14:発注済(取消注文)　@　@　@ 15:発注失敗(取消注文) <BR>　@
	 * 30:MRF取消エラー　@　@　@31:受付済(MRF解約有り)　@<BR>　@
	 *  32:注文済(MRF解約有り) <BR>　@
	 */
	private String orderStatusDiv;
	/**
	 * 注文数量タイプ<BR>
	 * 1:口数　@2:金額<BR>
	 */
	private String orderQuantityType;
	/**
	 * 注文数量<BR>
	 */
	private double orderQuantity;
	/**
	 * this.受注日時を返す。<BR>
	 * @@return Timestamp
	 * @@roseuid 40766C38021C
	 */
	public Timestamp getOrderTime()
	{
		log.debug("[getOrderTime] orderTime = " + orderTime);
		return orderTime;
	}
	/**
	 * 注文時間の設定を行う<BR>
	 * @@param l_orderTime - 注文時間<BR>
	 * @@roseuid 40766C47021C
	 */
	public void setOrderTime(Timestamp l_orderTime)
	{
		log.debug("[setOrderTime] l_orderTime = " + l_orderTime);
		orderTime = l_orderTime;
	}
	/**
	 * this.注文状態区分を返す。<BR>
	 * @@return String
	 * @@roseuid 40766C560076
	 */
	public String getOrderStatusDiv()
	{
		log.debug("[getOrderStatusDiv] orderStatusDiv = " + orderStatusDiv);
		return orderStatusDiv;
	}
	/**
	 * 注文状態区分の設定を行う<BR>
	 * @@param l_strOrderStatusDiv - 注文状態区分<BR>
	 * @@roseuid 40766C6F0374
	 */
	public void setOrderStatusDiv(String l_strOrderStatusDiv)
	{
		log.debug("[setOrderStatusDiv] l_strOrderStatusDiv = " + l_strOrderStatusDiv);
		orderStatusDiv = l_strOrderStatusDiv;
	}
	/**
	 * this.注文数量タイプを返す。<BR>
	 * @@return String
	 * @@roseuid 40A330F703A8
	 */
	public String getOrderQuantityType()
	{
		log.debug("[getOrderQuantityType] orderQuantityType = " + orderQuantityType);
		return orderQuantityType;
	}
	/**
	 * 注文数量タイプの設定を行う<BR>
	 * @@param l_strOrderQuantityType
	 * @@roseuid 40A33103035A
	 */
	public void setOrderQuantityType(String l_strOrderQuantityType)
	{
		log.debug("[setOrderQuantityType] l_strOrderQuantityType = " + l_strOrderQuantityType);
		orderQuantityType = l_strOrderQuantityType;
	}
	/**
	 * this.注文数量を返す。<BR>
	 * @@return double
	 * @@roseuid 40766C8E02A9
	 */
	public double getOrderQuantity()
	{
		log.debug("[getOrderQuantity] orderQuantity = " + orderQuantity);
		return orderQuantity;
	}
	/**
	 * 注文数量の設定を行う<BR>
	 * @@param l_dblOrderQuantity - 注文数量<BR>.
	 * @@roseuid 40766C830345
	 */
	public void setOrderQuantity(double l_dblOrderQuantity)
	{
		log.debug("[setOrderQuantity] l_dblOrderQuantity = " + l_dblOrderQuantity);
		orderQuantity = l_dblOrderQuantity;
	}
}
@
