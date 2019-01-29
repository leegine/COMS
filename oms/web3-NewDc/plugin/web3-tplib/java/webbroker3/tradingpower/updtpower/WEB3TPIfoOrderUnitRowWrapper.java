head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPIfoOrderUnitRowWrapper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力先物オプション注文単位Wrapper(WEB3TPIfoOrderUnitRowWrapper.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/02/04 堀野 和美(FLJ) 新規作成
Revision History : 2007/02/15 王暁傑(中訊) 【IFO】DEOS対応
Revision History : 2007/04/07 肖志偉(中訊) 【IFO】連続注文対応
*/
package webbroker3.tradingpower.updtpower;

import java.sql.Timestamp;
import java.util.Map;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

/**
 * ( 余力先物オプション注文単位Wrapper) <BR>
 * 余力計算時市場確認済値を使用するにあたり
 * 先物オプション注文単位行クラスのラッパーとして機@能する。
 */
public class WEB3TPIfoOrderUnitRowWrapper implements IfoOrderUnitRow
{
	/**
	 * (先物オプション注文単位)
	 */
	private IfoOrderUnitRow source;
	
	/*
	 * デフォルトコンストラクタ（使用しないようにする）
	 */
	private WEB3TPIfoOrderUnitRowWrapper(){};
	
	/**
	 * コンストラクタ
	 * 
	 * @@param l_row　@先物オプション注文単位
	 */
	public WEB3TPIfoOrderUnitRowWrapper(IfoOrderUnitRow l_row)
	{
		source = l_row;
		
	}

	/**
	 * IfoOrderUnitRow.get注文単価()を実装する。<BR>
	 * <BR>
	 * １）this.先物オプション注文単位.注文種別が<BR>
	 * 現物買、新規買建、新規売建の場合<BR>
	 * this.先物オプション注文単位.概算受渡代金, this.先物オプション注文単位.市場確認済概算受渡代金)を比較する<BR>
	 * [概算受渡代金 > 市場確認済概算受渡代金の場合]<BR>
	 * 注文単価を返却。<BR>
	 * [上記以外]<BR>
	 * 市場確認済注文単価を返却。<BR>
	 * ２）　@１）以外<BR>
	 * this.先物オプション注文単位.get注文単価()を返却する。<BR>
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getPrice()
	 */
	public double getPrice() {
		
		double l_dblPrice = 0.0d;
		
		if(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(source.getOrderType()))
		{			
			//概算受渡代金が大きいほうと整合する注文単価あるいは市場確認済注文単価を返す
			if(source.getEstimatedPrice() >= source.getConfirmedEstimatedPrice())
			{
				l_dblPrice = source.getPrice();
			}
			else
			{
				l_dblPrice = source.getConfirmedOrderPrice();				
			}				
		}
		else
		{			
			l_dblPrice = source.getPrice();				
		}
		return l_dblPrice;

	}

	/**
	 * IfoOrderUnitRow.get注文数量()を実装する。<BR>
	 * １）this.先物オプション注文単位.注文種別が<BR>
	 * 現物買、新規買建、新規売建の場合<BR>
	 * this.先物オプション注文単位.概算受渡代金, this.先物オプション注文単位.市場確認済概算受渡代金)を比較する<BR>
	 * [概算受渡代金 > 市場確認済概算受渡代金の場合]<BR>
	 * 注文数量を返却。<BR>
	 * [上記以外]<BR>
	 * 市場確認済注文数量を返却。<BR>
	 * ２）　@１）以外<BR>
	 * this.先物オプション注文単位.get注文数量()を返却する。<BR>
	 * 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getQuantity()
	 */
	public double getQuantity() {
		
		double l_dblQty = 0.0d;
		if(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(source.getOrderType()))
		{
			//概算受渡代金が大きいほうと整合する注文数量あるいは市場確認済注文数量を返す
			if(source.getEstimatedPrice() >= source.getConfirmedEstimatedPrice())
			{
				l_dblQty = source.getQuantity();
			}
			else
			{
				l_dblQty = source.getConfirmedQuantity();
				
			}				
		}
		else
		{			
			l_dblQty = source.getQuantity();
		}
		return l_dblQty;
		
	}
	
	
	/**
	 * IfoOrderUnitRow.get概算受渡代金()を実装する。<BR>
	 * １）this.先物オプション注文単位.注文種別が<BR>
	 * 現物買、新規買建、新規売建の場合<BR>
	 * Max(先物オプション注文単位.概算受渡代金, this.先物オプション注文単位.市場確認済概算受渡代金) を返却する。<BR>
	 * ２）　@１）以外<BR>
	 * this.先物オプション注文単位.get概算受渡代金()を返却する。<BR>
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getEstimatedPrice()
	 */
	public double getEstimatedPrice() {
		
		double l_dblEstimatePrice = 0.0d;
		
		if(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(source.getOrderType()))
		{
			//概算受渡代金が大きいほうと整合する注文単価あるいは市場確認済注文単価を返す
			l_dblEstimatePrice = Math.max(source.getEstimatedPrice(), source.getConfirmedEstimatedPrice());				
		}
		else
		{
			l_dblEstimatePrice = source.getEstimatedPrice();			
		}
		return l_dblEstimatePrice;
	}

	
	/*
	 * 以降のメソッドはthis.先物オプション注文単位に処理を委譲する。
	 */
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderUnitId()
	 */
	public long getOrderUnitId() {
		return source.getOrderUnitId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderUnitIdIsSet()
	 */
	public boolean getOrderUnitIdIsSet() {
		return source.getOrderUnitIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getAccountId()
	 */
	public long getAccountId() {
		return source.getAccountId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getAccountIdIsSet()
	 */
	public boolean getAccountIdIsSet() {
		return source.getAccountIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getSubAccountId()
	 */
	public long getSubAccountId() {
		return source.getSubAccountId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getSubAccountIdIsSet()
	 */
	public boolean getSubAccountIdIsSet() {
		return source.getSubAccountIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getBranchId()
	 */
	public long getBranchId() {
		return source.getBranchId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getBranchIdIsSet()
	 */
	public boolean getBranchIdIsSet() {
		return source.getBranchIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getTraderId()
	 */
	public long getTraderId() {
		return source.getTraderId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getTraderIdIsNull()
	 */
	public boolean getTraderIdIsNull() {
		return source.getTraderIdIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderId()
	 */
	public long getOrderId() {
		return source.getOrderId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderIdIsSet()
	 */
	public boolean getOrderIdIsSet() {
		return source.getOrderIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderType()
	 */
	public OrderTypeEnum getOrderType() {
		return source.getOrderType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderTypeIsSet()
	 */
	public boolean getOrderTypeIsSet() {
		return source.getOrderTypeIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderCateg()
	 */
	public OrderCategEnum getOrderCateg() {
		return source.getOrderCateg();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderCategIsSet()
	 */
	public boolean getOrderCategIsSet() {
		return source.getOrderCategIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getLastOrderActionSerialNo()
	 */
	public int getLastOrderActionSerialNo() {
		return source.getLastOrderActionSerialNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getLastOrderActionSerialNoIsSet()
	 */
	public boolean getLastOrderActionSerialNoIsSet() {
		return source.getLastOrderActionSerialNoIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getLastExecutionSerialNo()
	 */
	public int getLastExecutionSerialNo() {
		return source.getLastExecutionSerialNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getLastExecutionSerialNoIsSet()
	 */
	public boolean getLastExecutionSerialNoIsSet() {
		return source.getLastExecutionSerialNoIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getProductType()
	 */
	public ProductTypeEnum getProductType() {
		return source.getProductType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getProductTypeIsSet()
	 */
	public boolean getProductTypeIsSet() {
		return source.getProductTypeIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getMarketId()
	 */
	public long getMarketId() {
		return source.getMarketId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getMarketIdIsNull()
	 */
	public boolean getMarketIdIsNull() {
		return source.getMarketIdIsNull();
	}
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getQuantityIsSet()
	 */
	public boolean getQuantityIsSet() {
		return source.getQuantityIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getLimitPrice()
	 */
	public double getLimitPrice() {
		return source.getLimitPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getLimitPriceIsNull()
	 */
	public boolean getLimitPriceIsNull() {
		return source.getLimitPriceIsNull();
	}
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderConditionType()
	 */
	public String getOrderConditionType() {
		return source.getOrderConditionType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderCondOperator()
	 */
	public String getOrderCondOperator() {
		return source.getOrderCondOperator();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getStopOrderPrice()
	 */
	public double getStopOrderPrice() {
		return source.getStopOrderPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getStopOrderPriceIsNull()
	 */
	public boolean getStopOrderPriceIsNull() {
		return source.getStopOrderPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getWLimitPrice()
	 */
	public double getWLimitPrice() {
		return source.getWLimitPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getWLimitPriceIsNull()
	 */
	public boolean getWLimitPriceIsNull() {
		return source.getWLimitPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getDeliveryDate()
	 */
	public Timestamp getDeliveryDate() {
		return source.getDeliveryDate();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getDeliveryDateIsSet()
	 */
	public boolean getDeliveryDateIsSet() {
		return source.getDeliveryDateIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getExpirationDate()
	 */
	public Timestamp getExpirationDate() {
		return source.getExpirationDate();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getConfirmedQuantity()
	 */
	public double getConfirmedQuantity() {
		return source.getConfirmedQuantity();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getConfirmedQuantityIsNull()
	 */
	public boolean getConfirmedQuantityIsNull() {
		return source.getConfirmedQuantityIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getConfirmedPrice()
	 */
	public double getConfirmedPrice() {
		return source.getConfirmedPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getConfirmedPriceIsNull()
	 */
	public boolean getConfirmedPriceIsNull() {
		return source.getConfirmedPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getExecutedQuantity()
	 */
	public double getExecutedQuantity() {
		return source.getExecutedQuantity();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getExecutedQuantityIsNull()
	 */
	public boolean getExecutedQuantityIsNull() {
		return source.getExecutedQuantityIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getExecutedAmount()
	 */
	public double getExecutedAmount() {
		return source.getExecutedAmount();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getExecutedAmountIsNull()
	 */
	public boolean getExecutedAmountIsNull() {
		return source.getExecutedAmountIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderStatus()
	 */
	public OrderStatusEnum getOrderStatus() {
		return source.getOrderStatus();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderStatusIsSet()
	 */
	public boolean getOrderStatusIsSet() {
		return source.getOrderStatusIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderOpenStatus()
	 */
	public OrderOpenStatusEnum getOrderOpenStatus() {
		return source.getOrderOpenStatus();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderOpenStatusIsSet()
	 */
	public boolean getOrderOpenStatusIsSet() {
		return source.getOrderOpenStatusIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getExpirationStatus()
	 */
	public OrderExpirationStatusEnum getExpirationStatus() {
		return source.getExpirationStatus();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getExpirationStatusIsSet()
	 */
	public boolean getExpirationStatusIsSet() {
		return source.getExpirationStatusIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getTaxType()
	 */
	public TaxTypeEnum getTaxType() {
		return source.getTaxType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getTaxTypeIsSet()
	 */
	public boolean getTaxTypeIsSet() {
		return source.getTaxTypeIsSet();
	}
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getBizDate()
	 */
	public String getBizDate() {
		return source.getBizDate();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getBizDateIsSet()
	 */
	public boolean getBizDateIsSet() {
		return source.getBizDateIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getProductId()
	 */
	public long getProductId() {
		return source.getProductId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getProductIdIsSet()
	 */
	public boolean getProductIdIsSet() {
		return source.getProductIdIsSet();
	}
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderChanel()
	 */
	public String getOrderChanel() {
		return source.getOrderChanel();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getReceivedDateTime()
	 */
	public Timestamp getReceivedDateTime() {
		return source.getReceivedDateTime();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getVoucherNo()
	 */
	public String getVoucherNo() {
		return source.getVoucherNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getCommTblNo()
	 */
	public String getCommTblNo() {
		return source.getCommTblNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getCommTblSubNo()
	 */
	public String getCommTblSubNo() {
		return source.getCommTblSubNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getSonarTraderCode()
	 */
	public String getSonarTraderCode() {
		return source.getSonarTradedCode();
	}


	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getPriceIsNull()
	 */
	public boolean getPriceIsNull() {
		return source.getPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderRequestNumber()
	 */
	public String getOrderRequestNumber() {
		return source.getOrderRequestNumber();
	}


	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getEstimatedPriceIsNull()
	 */
	public boolean getEstimatedPriceIsNull() {
		return source.getEstimatedPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getSonarTradedCode()
	 */
	public String getSonarTradedCode() {
		return source.getSonarTradedCode();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getSonarMarketCode()
	 */
	public String getSonarMarketCode() {
		return source.getSonarMarketCode();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getCommProductCode()
	 */
	public String getCommProductCode() {
		return source.getCommProductCode();
	}
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getModifyCancelType()
	 */
	public String getModifyCancelType() {
		return source.getModifyCancelType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getOrderRootDiv()
	 */
	public String getOrderRootDiv() {
		return source.getOrderRootDiv();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getConfirmedOrderPrice()
	 */
	public double getConfirmedOrderPrice() {
		return source.getConfirmedOrderPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getConfirmedOrderPriceIsNull()
	 */
	public boolean getConfirmedOrderPriceIsNull() {
		return source.getConfirmedOrderPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getConfirmedEstimatedPrice()
	 */
	public double getConfirmedEstimatedPrice() {
		return source.getConfirmedEstimatedPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getConfirmedEstimatedPriceIsNull()
	 */
	public boolean getConfirmedEstimatedPriceIsNull() {
		return source.getConfirmedEstimatedPriceIsNull();
	}
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getErrorReasonCode()
	 */
	public String getErrorReasonCode() {
		return source.getErrorReasonCode();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getRequestType()
	 */
	public String getRequestType() {
		return source.getRequestType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getFirstOrderUnitId()
	 */
	public long getFirstOrderUnitId() {
		return source.getFirstOrderUnitId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getFirstOrderUnitIdIsNull()
	 */
	public boolean getFirstOrderUnitIdIsNull() {
		return source.getFirstOrderUnitIdIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getExecutionConditionType()
	 */
	public IfoOrderExecutionConditionType getExecutionConditionType() {
		return source.getExecutionConditionType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getConfirmedExecConditionType()
	 */
	public IfoOrderExecutionConditionType getConfirmedExecConditionType() {
		return source.getConfirmedExecConditionType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getFutureOptionDiv()
	 */
	public String getFutureOptionDiv() {
		return source.getFutureOptionDiv();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getStopPriceType()
	 */
	public String getStopPriceType() {
		return source.getStopPriceType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getClosingOrder()
	 */
	public String getClosingOrder() {
		return source.getClosingOrder();
	}
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getCreatedTimestamp()
	 */
	public Timestamp getCreatedTimestamp() {
		return source.getCreatedTimestamp();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getCreatedTimestampIsSet()
	 */
	public boolean getCreatedTimestampIsSet() {
		return source.getCreatedTimestampIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getLastUpdatedTimestamp()
	 */
	public Timestamp getLastUpdatedTimestamp() {
		return source.getLastUpdatedTimestamp();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.ifo.data.IfoOrderUnitRow#getLastUpdatedTimestampIsSet()
	 */
	public boolean getLastUpdatedTimestampIsSet() {
		return source.getLastUpdatedTimestampIsSet();
	}

	/* 
	 * @@see com.fitechlabs.dbind.Row#getPrimaryKey()
	 */
	public PrimaryKey getPrimaryKey() {
		return source.getPrimaryKey();
	}

	/* 
	 * @@see com.fitechlabs.dbind.Row#getColumn(java.lang.String)
	 */
	public Object getColumn(String arg0) {
		return source.getColumn(arg0);
	}

	/* 
	 * @@see com.fitechlabs.dbind.Row#getRowType()
	 */
	public RowType getRowType() {
		return source.getRowType();		
	}

	/* 
	 * @@see com.fitechlabs.dbind.Row#toInsertMap()
	 */
	public Map toInsertMap() {
		return source.toInsertMap();
	}

	/* 
	 * @@see com.fitechlabs.dbind.Row#toUpdateMap()
	 */
	public Map toUpdateMap() {
		return source.toUpdateMap();
	}

	/* 
	 * @@see com.fitechlabs.dbind.Row#toMap()
	 */
	public Map toMap() {
		return source.toMap();
	}
	
    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getOrgOrderConditionType()
     */
    public String getOrgOrderConditionType()
    {
        return source.getOrgOrderConditionType();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getOrgOrderCondOperator()
     */
    public String getOrgOrderCondOperator()
    {
        return source.getOrgOrderCondOperator();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getOrgStopOrderPrice()
     */
    public double getOrgStopOrderPrice()
    {
        return source.getOrgStopOrderPrice();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getOrgStopOrderPriceIsNull()
     */
    public boolean getOrgStopOrderPriceIsNull()
    {
        return source.getOrgStopOrderPriceIsNull();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getOrgStopPriceType()
     */
    public String getOrgStopPriceType()
    {
        return source.getOrgStopPriceType();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getOrgWLimitExecCondType()
     */
    public IfoOrderExecutionConditionType getOrgWLimitExecCondType()
    {
        return source.getOrgWLimitExecCondType();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getOrgWLimitPrice()
     */
    public double getOrgWLimitPrice()
    {
        return source.getOrgWLimitPrice();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getOrgWLimitPriceIsNull()
     */
    public boolean getOrgWLimitPriceIsNull()
    {
        return source.getOrgWLimitPriceIsNull();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getWLimitBeforeExecCondType()
     */
    public IfoOrderExecutionConditionType getWLimitBeforeExecCondType()
    {
        return source.getWLimitBeforeExecCondType();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getWLimitBeforeLimitPrice()
     */
    public double getWLimitBeforeLimitPrice()
    {
        return source.getWLimitBeforeLimitPrice();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getWLimitBeforeLimitPriceIsNull()
     */
    public boolean getWLimitBeforeLimitPriceIsNull()
    {
        return source.getWLimitBeforeLimitPriceIsNull();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getWLimitExecCondType()
     */
    public IfoOrderExecutionConditionType getWLimitExecCondType()
    {
        return source.getWLimitExecCondType();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getSubmitOrderDelayFlag()
     */
    public BooleanEnum getSubmitOrderDelayFlag()
    {
        return source.getSubmitOrderDelayFlag();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getSubmitOrderDelayFlagIsSet()
     */
    public boolean getSubmitOrderDelayFlagIsSet()
    {
        return source.getSubmitOrderDelayFlagIsSet();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getSubmitOrderRouteDiv()
     */    
    public String getSubmitOrderRouteDiv()
    {
        return source.getSubmitOrderRouteDiv();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getConfirmedOrderRev()
     */
    public String getConfirmedOrderRev()
    {
        return source.getConfirmedOrderRev();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getConfirmedOrderRevIsSet()
     */
    public boolean getConfirmedOrderRevIsSet()
    {
        return source.getConfirmedOrderRevIsSet();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getOrderRev()
     */
    public String getOrderRev()
    {
        return source.getOrderRev();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getOrderRevIsSet()
     */
    public boolean getOrderRevIsSet()
    {
        return source.getOrderRevIsSet();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getEveningSessionCarryoverFlag()
     */
    public BooleanEnum getEveningSessionCarryoverFlag()
    {
        return source.getEveningSessionCarryoverFlag();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getEveningSessionCarryoverFlagIsSet()
     */
    public boolean getEveningSessionCarryoverFlagIsSet()
    {
        return source.getEveningSessionCarryoverFlagIsSet();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getSessionType()
     */
    public String getSessionType()
    {
        return source.getSessionType();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getDayTradeType()
     */
    public String getDayTradeType()
    {
        return source.getDayTradeType();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getReserveOrderExistFlag()
     */
    public String getReserveOrderExistFlag()
    {
        return source.getReserveOrderExistFlag();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getReserveOrderExistFlagIsSet()
     */
    public boolean getReserveOrderExistFlagIsSet()
    {
        return source.getReserveOrderExistFlagIsSet();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow#getExpirationDateType()
     */
    public String getExpirationDateType()
    {
        return source.getExpirationDateType();
    }

    public boolean getOrderUnitIdIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderUnitIdIsModified();
    }

    public boolean getAccountIdIsModified() {
        // TODO Auto-generated method stub
        return source.getAccountIdIsModified();
    }

    public boolean getSubAccountIdIsModified() {
        // TODO Auto-generated method stub
        return source.getSubAccountIdIsModified();
    }

    public boolean getBranchIdIsModified() {
        // TODO Auto-generated method stub
        return source.getBranchIdIsModified();
    }

    public boolean getTraderIdIsSet() {
        // TODO Auto-generated method stub
        return source.getTraderIdIsSet();
    }

    public boolean getTraderIdIsModified() {
        // TODO Auto-generated method stub
        return source.getTraderIdIsModified();
    }

    public boolean getOrderIdIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderIdIsModified();
    }

    public boolean getOrderTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderTypeIsModified();
    }

    public boolean getOrderCategIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderCategIsModified();
    }

    public boolean getLastOrderActionSerialNoIsModified() {
        // TODO Auto-generated method stub
        return source.getLastOrderActionSerialNoIsModified();
    }

    public boolean getLastExecutionSerialNoIsModified() {
        // TODO Auto-generated method stub
        return source.getLastExecutionSerialNoIsModified();
    }

    public boolean getProductTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getProductTypeIsModified();
    }

    public boolean getFutureOptionDivIsSet() {
        // TODO Auto-generated method stub
        return source.getFutureOptionDivIsSet();
    }

    public boolean getFutureOptionDivIsModified() {
        // TODO Auto-generated method stub
        return source.getFutureOptionDivIsModified();
    }

    public boolean getMarketIdIsSet() {
        // TODO Auto-generated method stub
        return source.getMarketIdIsSet();
    }

    public boolean getMarketIdIsModified() {
        // TODO Auto-generated method stub
        return source.getMarketIdIsModified();
    }

    public boolean getQuantityIsModified() {
        // TODO Auto-generated method stub
        return source.getQuantityIsModified();
    }

    public boolean getLimitPriceIsSet() {
        // TODO Auto-generated method stub
        return source.getLimitPriceIsSet();
    }

    public boolean getLimitPriceIsModified() {
        // TODO Auto-generated method stub
        return source.getLimitPriceIsModified();
    }

    public boolean getExecutionConditionTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getExecutionConditionTypeIsSet();
    }

    public boolean getExecutionConditionTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getExecutionConditionTypeIsModified();
    }

    public boolean getOrderConditionTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getOrderConditionTypeIsSet();
    }

    public boolean getOrderConditionTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderConditionTypeIsModified();
    }

    public boolean getOrderCondOperatorIsSet() {
        // TODO Auto-generated method stub
        return source.getOrderCondOperatorIsSet();
    }

    public boolean getOrderCondOperatorIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderCondOperatorIsModified();
    }

    public boolean getStopPriceTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getStopPriceTypeIsSet();
    }

    public boolean getStopPriceTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getStopPriceTypeIsModified();
    }

    public boolean getStopOrderPriceIsSet() {
        // TODO Auto-generated method stub
        return source.getStopOrderPriceIsSet();
    }

    public boolean getStopOrderPriceIsModified() {
        // TODO Auto-generated method stub
        return source.getStopOrderPriceIsModified();
    }

    public boolean getWLimitPriceIsSet() {
        // TODO Auto-generated method stub
        return source.getWLimitPriceIsSet();
    }

    public boolean getWLimitPriceIsModified() {
        // TODO Auto-generated method stub
        return source.getWLimitPriceIsModified();
    }

    public boolean getDeliveryDateIsModified() {
        // TODO Auto-generated method stub
        return source.getDeliveryDateIsModified();
    }

    public boolean getExpirationDateIsSet() {
        // TODO Auto-generated method stub
        return source.getExpirationDateIsSet();
    }

    public boolean getExpirationDateIsModified() {
        // TODO Auto-generated method stub
        return source.getExpirationDateIsModified();
    }

    public boolean getConfirmedQuantityIsSet() {
        // TODO Auto-generated method stub
        return source.getConfirmedQuantityIsSet();
    }

    public boolean getConfirmedQuantityIsModified() {
        // TODO Auto-generated method stub
        return source.getConfirmedQuantityIsModified();
    }

    public boolean getConfirmedPriceIsSet() {
        // TODO Auto-generated method stub
        return source.getConfirmedPriceIsSet();
    }

    public boolean getConfirmedPriceIsModified() {
        // TODO Auto-generated method stub
        return source.getConfirmedPriceIsModified();
    }

    public boolean getExecutedQuantityIsSet() {
        // TODO Auto-generated method stub
        return source.getExecutedQuantityIsSet();
    }

    public boolean getExecutedQuantityIsModified() {
        // TODO Auto-generated method stub
        return source.getExecutedQuantityIsModified();
    }

    public boolean getExecutedAmountIsSet() {
        // TODO Auto-generated method stub
        return source.getExecutedAmountIsSet();
    }

    public boolean getExecutedAmountIsModified() {
        // TODO Auto-generated method stub
        return source.getExecutedAmountIsModified();
    }

    public boolean getOrderStatusIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderStatusIsModified();
    }

    public boolean getOrderOpenStatusIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderOpenStatusIsModified();
    }

    public boolean getExpirationStatusIsModified() {
        // TODO Auto-generated method stub
        return source.getExpirationStatusIsModified();
    }

    public boolean getTaxTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getTaxTypeIsModified();
    }

    public boolean getBizDateIsModified() {
        // TODO Auto-generated method stub
        return source.getBizDateIsModified();
    }

    public boolean getProductIdIsModified() {
        // TODO Auto-generated method stub
        return source.getProductIdIsModified();
    }

    public boolean getOrderChanelIsSet() {
        // TODO Auto-generated method stub
        return source.getOrderChanelIsSet();
    }

    public boolean getOrderChanelIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderChanelIsModified();
    }

    public boolean getReceivedDateTimeIsSet() {
        // TODO Auto-generated method stub
        return source.getReceivedDateTimeIsSet();
    }

    public boolean getReceivedDateTimeIsModified() {
        // TODO Auto-generated method stub
        return source.getReceivedDateTimeIsModified();
    }

    public boolean getVoucherNoIsSet() {
        // TODO Auto-generated method stub
        return source.getVoucherNoIsSet();
    }

    public boolean getVoucherNoIsModified() {
        // TODO Auto-generated method stub
        return source.getVoucherNoIsModified();
    }

    public boolean getCommTblNoIsSet() {
        // TODO Auto-generated method stub
        return source.getCommTblNoIsSet();
    }

    public boolean getCommTblNoIsModified() {
        // TODO Auto-generated method stub
        return source.getCommTblNoIsModified();
    }

    public boolean getCommTblSubNoIsSet() {
        // TODO Auto-generated method stub
        return source.getCommTblSubNoIsSet();
    }

    public boolean getCommTblSubNoIsModified() {
        // TODO Auto-generated method stub
        return source.getCommTblSubNoIsModified();
    }

    public boolean getSonarTraderCodeIsSet() {
        // TODO Auto-generated method stub
        return source.getSonarTraderCodeIsSet();
    }

    public boolean getSonarTraderCodeIsModified() {
        // TODO Auto-generated method stub
        return source.getSonarTraderCodeIsModified();
    }

    public boolean getPriceIsSet() {
        // TODO Auto-generated method stub
        return source.getPriceIsSet();
    }

    public boolean getPriceIsModified() {
        // TODO Auto-generated method stub
        return source.getPriceIsModified();
    }

    public boolean getOrderRequestNumberIsSet() {
        // TODO Auto-generated method stub
        return source.getOrderRequestNumberIsSet();
    }

    public boolean getOrderRequestNumberIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderRequestNumberIsModified();
    }

    public boolean getEstimatedPriceIsSet() {
        // TODO Auto-generated method stub
        return source.getEstimatedPriceIsSet();
    }

    public boolean getEstimatedPriceIsModified() {
        // TODO Auto-generated method stub
        return source.getEstimatedPriceIsModified();
    }

    public boolean getSonarTradedCodeIsSet() {
        // TODO Auto-generated method stub
        return source.getSonarTradedCodeIsSet();
    }

    public boolean getSonarTradedCodeIsModified() {
        // TODO Auto-generated method stub
        return source.getSonarTradedCodeIsModified();
    }

    public boolean getSonarMarketCodeIsSet() {
        // TODO Auto-generated method stub
        return source.getSonarMarketCodeIsSet();
    }

    public boolean getSonarMarketCodeIsModified() {
        // TODO Auto-generated method stub
        return source.getSonarMarketCodeIsModified();
    }

    public boolean getCommProductCodeIsSet() {
        // TODO Auto-generated method stub
        return source.getCommProductCodeIsSet();
    }

    public boolean getCommProductCodeIsModified() {
        // TODO Auto-generated method stub
        return source.getCommProductCodeIsModified();
    }

    public boolean getModifyCancelTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getModifyCancelTypeIsSet();
    }

    public boolean getModifyCancelTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getModifyCancelTypeIsModified();
    }

    public boolean getOrderRootDivIsSet() {
        // TODO Auto-generated method stub
        return source.getOrderRootDivIsSet();
    }

    public boolean getOrderRootDivIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderRootDivIsModified();
    }

    public boolean getConfirmedOrderPriceIsSet() {
        // TODO Auto-generated method stub
        return source.getConfirmedOrderPriceIsSet();
    }

    public boolean getConfirmedOrderPriceIsModified() {
        // TODO Auto-generated method stub
        return source.getConfirmedOrderPriceIsModified();
    }

    public boolean getConfirmedEstimatedPriceIsSet() {
        // TODO Auto-generated method stub
        return source.getConfirmedEstimatedPriceIsSet();
    }

    public boolean getConfirmedEstimatedPriceIsModified() {
        // TODO Auto-generated method stub
        return source.getConfirmedEstimatedPriceIsModified();
    }

    public boolean getConfirmedExecConditionTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getConfirmedExecConditionTypeIsSet();
    }

    public boolean getConfirmedExecConditionTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getConfirmedExecConditionTypeIsModified();
    }

    public boolean getClosingOrderIsSet() {
        // TODO Auto-generated method stub
        return source.getClosingOrderIsSet();
    }

    public boolean getClosingOrderIsModified() {
        // TODO Auto-generated method stub
        return source.getClosingOrderIsModified();
    }

    public boolean getErrorReasonCodeIsSet() {
        // TODO Auto-generated method stub
        return source.getErrorReasonCodeIsSet();
    }

    public boolean getErrorReasonCodeIsModified() {
        // TODO Auto-generated method stub
        return source.getErrorReasonCodeIsModified();
    }

    public boolean getRequestTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getRequestTypeIsSet();
    }

    public boolean getRequestTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getRequestTypeIsModified();
    }

    public boolean getFirstOrderUnitIdIsSet() {
        // TODO Auto-generated method stub
        return source.getFirstOrderUnitIdIsSet();
    }

    public boolean getFirstOrderUnitIdIsModified() {
        // TODO Auto-generated method stub
        return source.getFirstOrderUnitIdIsModified();
    }

    public boolean getCreatedTimestampIsModified() {
        // TODO Auto-generated method stub
        return source.getCreatedTimestampIsModified();
    }

    public boolean getLastUpdatedTimestampIsModified() {
        // TODO Auto-generated method stub
        return source.getLastUpdatedTimestampIsModified();
    }

    public boolean getOrgOrderConditionTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getOrgOrderConditionTypeIsSet();
    }

    public boolean getOrgOrderConditionTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getOrgOrderConditionTypeIsModified();
    }

    public boolean getOrgOrderCondOperatorIsSet() {
        // TODO Auto-generated method stub
        return source.getOrgOrderCondOperatorIsSet();
    }

    public boolean getOrgOrderCondOperatorIsModified() {
        // TODO Auto-generated method stub
        return source.getOrgOrderCondOperatorIsModified();
    }

    public boolean getOrgStopPriceTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getOrgStopPriceTypeIsSet();
    }

    public boolean getOrgStopPriceTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getOrgStopPriceTypeIsModified();
    }

    public boolean getOrgStopOrderPriceIsSet() {
        // TODO Auto-generated method stub
        return source.getOrgStopOrderPriceIsSet();
    }

    public boolean getOrgStopOrderPriceIsModified() {
        // TODO Auto-generated method stub
        return source.getOrgStopOrderPriceIsModified();
    }

    public boolean getOrgWLimitPriceIsSet() {
        // TODO Auto-generated method stub
        return source.getOrgWLimitPriceIsSet();
    }

    public boolean getOrgWLimitPriceIsModified() {
        // TODO Auto-generated method stub
        return source.getOrgWLimitPriceIsModified();
    }

    public boolean getOrgWLimitExecCondTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getOrgWLimitExecCondTypeIsSet();
    }

    public boolean getOrgWLimitExecCondTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getOrgWLimitExecCondTypeIsModified();
    }

    public boolean getWLimitExecCondTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getWLimitExecCondTypeIsSet();
    }

    public boolean getWLimitExecCondTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getWLimitExecCondTypeIsModified();
    }

    public boolean getWLimitBeforeLimitPriceIsSet() {
        // TODO Auto-generated method stub
        return source.getWLimitBeforeLimitPriceIsSet();
    }

    public boolean getWLimitBeforeLimitPriceIsModified() {
        // TODO Auto-generated method stub
        return source.getWLimitBeforeLimitPriceIsModified();
    }

    public boolean getWLimitBeforeExecCondTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getWLimitBeforeExecCondTypeIsSet();
    }

    public boolean getWLimitBeforeExecCondTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getWLimitBeforeExecCondTypeIsModified();
    }

    public boolean getSubmitOrderDelayFlagIsModified() {
        // TODO Auto-generated method stub
        return source.getSubmitOrderDelayFlagIsModified();
    }

    public boolean getSubmitOrderRouteDivIsSet() {
        // TODO Auto-generated method stub
        return source.getSubmitOrderRouteDivIsSet();
    }

    public boolean getSubmitOrderRouteDivIsModified() {
        // TODO Auto-generated method stub
        return source.getSubmitOrderRouteDivIsModified();
    }

    public boolean getConfirmedOrderRevIsModified() {
        // TODO Auto-generated method stub
        return source.getConfirmedOrderRevIsModified();
    }

    public boolean getOrderRevIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderRevIsModified();
    }

    public boolean getEveningSessionCarryoverFlagIsModified() {
        // TODO Auto-generated method stub
        return source.getEveningSessionCarryoverFlagIsModified();
    }

    public boolean getSessionTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getSessionTypeIsSet();
    }

    public boolean getSessionTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getSessionTypeIsModified();
    }

    public boolean getDayTradeTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getDayTradeTypeIsSet();
    }

    public boolean getDayTradeTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getDayTradeTypeIsModified();
    }

    public boolean getReserveOrderExistFlagIsModified() {
        // TODO Auto-generated method stub
        return source.getReserveOrderExistFlagIsModified();
    }

    public boolean getExpirationDateTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getExpirationDateTypeIsSet();
    }

    public boolean getExpirationDateTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getExpirationDateTypeIsModified();
    }

}
@
