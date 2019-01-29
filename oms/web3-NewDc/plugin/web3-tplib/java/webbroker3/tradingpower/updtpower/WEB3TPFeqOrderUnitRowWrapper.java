head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPFeqOrderUnitRowWrapper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力外株注文単位Wrapper(WEB3TPFeqOrderUnitRowWrapper.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/09/28 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpower.updtpower;

import java.sql.Timestamp;
import java.util.Map;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

/**
 * ( 余力外株注文単位Wrapper) <BR>
 * 余力計算時市場確認済値を使用するにあたり
 * 外株注文単位行クラスのラッパーとして機@能する。
 */
public class WEB3TPFeqOrderUnitRowWrapper implements FeqOrderUnitRow
{
	/**
	 * (外株注文単位)
	 */
	private FeqOrderUnitRow source;

	/**
	 * (割増拘束率)
	 */
	private double premiumRestraintRate;
	
	/*
	 * デフォルトコンストラクタ（使用しないようにする）
	 */
	private WEB3TPFeqOrderUnitRowWrapper(){};
	
	/**
	 * コンストラクタ
	 * 
	 * @@param l_row　@外株注文単位
	 */
	public WEB3TPFeqOrderUnitRowWrapper(FeqOrderUnitRow l_row)
	{
		this(l_row, 1.0);
	}

	/**
	 * コンストラクタ
	 * 
	 * @@param l_row　@外株注文単位
	 * @@param l_dblPreRestRate　@割増拘束率
	 */
	public WEB3TPFeqOrderUnitRowWrapper(FeqOrderUnitRow l_row, double l_dblPreRestRate)
	{
		source = l_row;
		premiumRestraintRate = l_dblPreRestRate;
	}

	/**
	 * FeqOrderUnitRow.get注文単価()を実装する。<BR>
	 * <BR>
	 * １）this.外株注文単位.注文種別が<BR>
	 * 現物買、新規買建、新規売建の場合<BR>
	 * this.外株注文単位.概算受渡代金, this.外株注文単位.市場確認済概算受渡代金)を比較する<BR>
	 * [概算受渡代金 > 市場確認済概算受渡代金の場合]<BR>
	 * 注文単価を返却。<BR>
	 * [上記以外]<BR>
	 * 市場確認済注文単価を返却。<BR>
	 * ２）　@１）以外<BR>
	 * this.外株注文単位.get注文単価()を返却する。<BR>
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getPrice()
	 */
	public double getPrice() {
		
		double l_dblPrice = 0.0d;
		
		if(OrderTypeEnum.FEQ_BUY.equals(source.getOrderType())) 
		{			
			//概算受渡代金が大きいほうと整合する注文数量あるいは市場確認済注文数量を返す
			if(source.getEstimatedPrice() >= source.getConfirmedEstimatedPrice())
			{
			    
                //Y00125：割増拘束率対応
                //成行注文の場合
                if(source.getLimitPrice() == 0)
                {
                    //注文単価 = 外株注文単位テーブル.注文単価 * 割増拘束率
                    l_dblPrice = source.getPrice() * premiumRestraintRate;
                }
                //指値注文の場合
                else
                {
                    l_dblPrice = source.getPrice();
                }
            }
            else
            {
                //Y00125：割増拘束率対応
                //成行注文の場合
                if(source.getConfirmedPrice() == 0)
                {
                    //注文単価 = 外株注文単位テーブル.注文単価 * 割増拘束率
                    l_dblPrice = source.getConfirmedOrderPrice() * premiumRestraintRate;
                }
                //指値注文の場合
                else
                {
                    l_dblPrice = source.getConfirmedOrderPrice();
                }
            }				
		}
		else
		{			
			l_dblPrice = source.getPrice();				
		}

		return l_dblPrice;

	}

	/**
	 * FeqOrderUnitRow.get注文数量()を実装する。<BR>
	 * １）this.外株注文単位.注文種別が<BR>
	 * 現物買、新規買建、新規売建の場合<BR>
	 * this.外株注文単位.概算受渡代金, this.外株注文単位.市場確認済概算受渡代金)を比較する<BR>
	 * [概算受渡代金 > 市場確認済概算受渡代金の場合]<BR>
	 * 注文数量を返却。<BR>
	 * [上記以外]<BR>
	 * 市場確認済注文数量を返却。<BR>
	 * ２）　@１）以外<BR>
	 * this.外株注文単位.get注文数量()を返却する。<BR>
	 * 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getQuantity()
	 */
	public double getQuantity() {
		
		double l_dblQty = 0.0d;

		if(OrderTypeEnum.FEQ_BUY.equals(source.getOrderType())) 
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
	 * FeqOrderUnitRow.get概算受渡代金()を実装する。<BR>
	 * １）this.外株注文単位.注文種別が<BR>
	 * 現物買、新規買建、新規売建の場合<BR>
	 * Max(外株注文単位.概算受渡代金, this.外株注文単位.市場確認済概算受渡代金) を返却する。<BR>
	 * ２）　@１）以外<BR>
	 * this.外株注文単位.get概算受渡代金()を返却する。<BR>
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getEstimatedPrice()
	 */
	public double getEstimatedPrice() {
		
		double l_dblEstimatePrice = 0.0d;
		
		if(OrderTypeEnum.FEQ_BUY.equals(source.getOrderType())) 
		{
			//概算受渡代金が大きいほうを返す
			l_dblEstimatePrice = Math.max(source.getEstimatedPrice(), source.getConfirmedEstimatedPrice());				
		}				
		else
		{
			l_dblEstimatePrice = source.getEstimatedPrice();			
		}
		return l_dblEstimatePrice;
	}

	
	/*
	 * 以降のメソッドはthis.外株注文単位に処理を委譲する。
	 */
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderUnitId()
	 */
	public long getOrderUnitId() {
		return source.getOrderUnitId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderUnitIdIsSet()
	 */
	public boolean getOrderUnitIdIsSet() {
		return source.getOrderUnitIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getAccountId()
	 */
	public long getAccountId() {
		return source.getAccountId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getAccountIdIsSet()
	 */
	public boolean getAccountIdIsSet() {
		return source.getAccountIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getSubAccountId()
	 */
	public long getSubAccountId() {
		return source.getSubAccountId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getSubAccountIdIsSet()
	 */
	public boolean getSubAccountIdIsSet() {
		return source.getSubAccountIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getBranchId()
	 */
	public long getBranchId() {
		return source.getBranchId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getBranchIdIsSet()
	 */
	public boolean getBranchIdIsSet() {
		return source.getBranchIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getTraderId()
	 */
	public long getTraderId() {
		return source.getTraderId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getTraderIdIsNull()
	 */
	public boolean getTraderIdIsNull() {
		return source.getTraderIdIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderId()
	 */
	public long getOrderId() {
		return source.getOrderId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderIdIsSet()
	 */
	public boolean getOrderIdIsSet() {
		return source.getOrderIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderType()
	 */
	public OrderTypeEnum getOrderType() {
		return source.getOrderType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderTypeIsSet()
	 */
	public boolean getOrderTypeIsSet() {
		return source.getOrderTypeIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderCateg()
	 */
	public OrderCategEnum getOrderCateg() {
		return source.getOrderCateg();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderCategIsSet()
	 */
	public boolean getOrderCategIsSet() {
		return source.getOrderCategIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getLastOrderActionSerialNo()
	 */
	public int getLastOrderActionSerialNo() {
		return source.getLastOrderActionSerialNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getLastOrderActionSerialNoIsSet()
	 */
	public boolean getLastOrderActionSerialNoIsSet() {
		return source.getLastOrderActionSerialNoIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getLastExecutionSerialNo()
	 */
	public int getLastExecutionSerialNo() {
		return source.getLastExecutionSerialNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getLastExecutionSerialNoIsSet()
	 */
	public boolean getLastExecutionSerialNoIsSet() {
		return source.getLastExecutionSerialNoIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getProductType()
	 */
	public ProductTypeEnum getProductType() {
		return source.getProductType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getProductTypeIsSet()
	 */
	public boolean getProductTypeIsSet() {
		return source.getProductTypeIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getMarketId()
	 */
	public long getMarketId() {
		return source.getMarketId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getMarketIdIsNull()
	 */
	public boolean getMarketIdIsNull() {
		return source.getMarketIdIsNull();
	}
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getQuantityIsSet()
	 */
	public boolean getQuantityIsSet() {
		return source.getQuantityIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getLimitPrice()
	 */
	public double getLimitPrice() {
		return source.getLimitPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getLimitPriceIsNull()
	 */
	public boolean getLimitPriceIsNull() {
		return source.getLimitPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderConditionType()
	 */
	public String getOrderConditionType() {
		return source.getOrderConditionType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderCondOperator()
	 */
	public String getOrderCondOperator() {
		return source.getOrderCondOperator();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getStopOrderPrice()
	 */
	public double getStopOrderPrice() {
		return source.getStopOrderPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getStopOrderPriceIsNull()
	 */
	public boolean getStopOrderPriceIsNull() {
		return source.getStopOrderPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getWLimitPrice()
	 */
	public double getWLimitPrice() {
		return source.getWLimitPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getWLimitPriceIsNull()
	 */
	public boolean getWLimitPriceIsNull() {
		return source.getWLimitPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getDeliveryDate()
	 */
	public Timestamp getDeliveryDate() {
		return source.getDeliveryDate();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getDeliveryDateIsSet()
	 */
	public boolean getDeliveryDateIsSet() {
		return source.getDeliveryDateIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getExpirationDate()
	 */
	public Timestamp getExpirationDate() {
		return source.getExpirationDate();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getConfirmedQuantity()
	 */
	public double getConfirmedQuantity() {
		return source.getConfirmedQuantity();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getConfirmedQuantityIsNull()
	 */
	public boolean getConfirmedQuantityIsNull() {
		return source.getConfirmedQuantityIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getConfirmedPrice()
	 */
	public double getConfirmedPrice() {
		return source.getConfirmedPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getConfirmedPriceIsNull()
	 */
	public boolean getConfirmedPriceIsNull() {
		return source.getConfirmedPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getExecutedQuantity()
	 */
	public double getExecutedQuantity() {
		return source.getExecutedQuantity();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getExecutedQuantityIsNull()
	 */
	public boolean getExecutedQuantityIsNull() {
		return source.getExecutedQuantityIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getExecutedAmount()
	 */
	public double getExecutedAmount() {
		return source.getExecutedAmount();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getExecutedAmountIsNull()
	 */
	public boolean getExecutedAmountIsNull() {
		return source.getExecutedAmountIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderStatus()
	 */
	public OrderStatusEnum getOrderStatus() {
		return source.getOrderStatus();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderStatusIsSet()
	 */
	public boolean getOrderStatusIsSet() {
		return source.getOrderStatusIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderOpenStatus()
	 */
	public OrderOpenStatusEnum getOrderOpenStatus() {
		return source.getOrderOpenStatus();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderOpenStatusIsSet()
	 */
	public boolean getOrderOpenStatusIsSet() {
		return source.getOrderOpenStatusIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getExpirationStatus()
	 */
	public OrderExpirationStatusEnum getExpirationStatus() {
		return source.getExpirationStatus();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getExpirationStatusIsSet()
	 */
	public boolean getExpirationStatusIsSet() {
		return source.getExpirationStatusIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getTaxType()
	 */
	public TaxTypeEnum getTaxType() {
		return source.getTaxType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getTaxTypeIsSet()
	 */
	public boolean getTaxTypeIsSet() {
		return source.getTaxTypeIsSet();
	}
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getBizDate()
	 */
	public String getBizDate() {
		return source.getBizDate();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getBizDateIsSet()
	 */
	public boolean getBizDateIsSet() {
		return source.getBizDateIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getProductId()
	 */
	public long getProductId() {
		return source.getProductId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getProductIdIsSet()
	 */
	public boolean getProductIdIsSet() {
		return source.getProductIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderChanel()
	 */
	public String getOrderChanel() {
		return source.getOrderChanel();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getReceivedDateTime()
	 */
	public Timestamp getReceivedDateTime() {
		return source.getReceivedDateTime();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getVoucherNo()
	 */
	public String getVoucherNo() {
		return source.getVoucherNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getCommTblNo()
	 */
	public String getCommTblNo() {
		return source.getCommTblNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getCommTblSubNo()
	 */
	public String getCommTblSubNo() {
		return source.getCommTblSubNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getSonarTraderCode()
	 */
	public String getSonarTraderCode() {
		return source.getSonarTradedCode();
	}


	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getPriceIsNull()
	 */
	public boolean getPriceIsNull() {
		return source.getPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderRequestNumber()
	 */
	public String getOrderRequestNumber() {
		return source.getOrderRequestNumber();
	}


	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getEstimatedPriceIsNull()
	 */
	public boolean getEstimatedPriceIsNull() {
		return source.getEstimatedPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getCapitalGain()
	 */
	public double getCapitalGain() {
		return source.getCapitalGain();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getCapitalGainIsNull()
	 */
	public boolean getCapitalGainIsNull() {
		return source.getCapitalGainIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getCapitalGainTax()
	 */
	public double getCapitalGainTax() {
		return source.getCapitalGainTax();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getCapitalGainTaxIsNull()
	 */
	public boolean getCapitalGainTaxIsNull() {
		return source.getCapitalGainTaxIsNull();
	}
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getSonarTradedCode()
	 */
	public String getSonarTradedCode() {
		return source.getSonarTradedCode();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getSonarMarketCode()
	 */
	public String getSonarMarketCode() {
		return source.getSonarMarketCode();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getCommProductCode()
	 */
	public String getCommProductCode() {
		return source.getCommProductCode();
	}	
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getModifyCancelType()
	 */
	public String getModifyCancelType() {
		return source.getModifyCancelType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getOrderRootDiv()
	 */
	public String getOrderRootDiv() {
		return source.getOrderRootDiv();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getConfirmedOrderPrice()
	 */
	public double getConfirmedOrderPrice() {
		return source.getConfirmedOrderPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getConfirmedOrderPriceIsNull()
	 */
	public boolean getConfirmedOrderPriceIsNull() {
		return source.getConfirmedOrderPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getConfirmedEstimatedPrice()
	 */
	public double getConfirmedEstimatedPrice() {
		return source.getConfirmedEstimatedPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getConfirmedEstimatedPriceIsNull()
	 */
	public boolean getConfirmedEstimatedPriceIsNull() {
		return source.getConfirmedEstimatedPriceIsNull();
	}
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getErrorReasonCode()
	 */
	public String getErrorReasonCode() {
		return source.getErrorReasonCode();
	}
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getFirstOrderUnitId()
	 */
	public long getFirstOrderUnitId() {
		return source.getFirstOrderUnitId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getFirstOrderUnitIdIsNull()
	 */
	public boolean getFirstOrderUnitIdIsNull() {
		return source.getFirstOrderUnitIdIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getCreatedTimestamp()
	 */
	public Timestamp getCreatedTimestamp() {
		return source.getCreatedTimestamp();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getCreatedTimestampIsSet()
	 */
	public boolean getCreatedTimestampIsSet() {
		return source.getCreatedTimestampIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getLastUpdatedTimestamp()
	 */
	public Timestamp getLastUpdatedTimestamp() {
		return source.getLastUpdatedTimestamp();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.FeqOrderUnitRow#getLastUpdatedTimestampIsSet()
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
	
    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getInstitutionCode()
     */
    public String getInstitutionCode() {
        return null;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getInstitutionCodeIsSet()
     */
    public boolean getInstitutionCodeIsSet() {
        
        return false;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getSettleDiv()
     */
    public String getSettleDiv() {
        
        return null;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getExecutedAmountYen()
     */
    public double getExecutedAmountYen() {
        
        return 0;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getExecutedAmountYenIsNull()
     */
    public boolean getExecutedAmountYenIsNull() {
        
        return false;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getCurrencyCode()
     */
    public String getCurrencyCode() {
        
        return null;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getCurrencyCodeIsSet()
     */
    public boolean getCurrencyCodeIsSet() {
        
        return false;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getFEstimatedPrice()
     */
    public double getFEstimatedPrice() {
        
        return 0;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getFEstimatedPriceIsNull()
     */
    public boolean getFEstimatedPriceIsNull() {
        
        return false;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getConfirmedFEstimatedPrice()
     */
    public double getConfirmedFEstimatedPrice() {
        
        return 0;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getConfirmedFEstimatedPriceIsNull()
     */
    public boolean getConfirmedFEstimatedPriceIsNull() {
        
        return false;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getFactor()
     */
    public String getFactor() {
        
        return null;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getOrderEmpCode()
     */
    public String getOrderEmpCode() {        
        return null;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getAccountDiv()
     */
    public String getAccountDiv() {        
        return null;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getExecEndTimestamp()
     */
    public Timestamp getExecEndTimestamp() {        
        return null;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getExecFileSendFlag()
     */
    public String getExecFileSendFlag() {        
        return null;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getLastUpdater()
     */
    public String getLastUpdater() {        
        return null;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getExecutionConditionType()
     */
    public FeqExecutionConditionType getExecutionConditionType() {
        return null;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getConfirmedExecConditionType()
     */
    
    public FeqExecutionConditionType getConfirmedExecConditionType() {
        return null;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getExecutionSeqNo()
     */
    public int getExecutionSeqNo()
    {
        return 0;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getExecutionSeqNoIsNull()
     */
    public boolean getExecutionSeqNoIsNull()
    {
        return false;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getExecutionSeqNoIsSet()
     */
    public boolean getExecutionSeqNoIsSet()
    {
        return false;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getTemporaryExecutionFlag()
     */
	public String getTemporaryExecutionFlag() 
	{
		return null;
	}

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow#getTemporaryExecutionFlagIsSet()
     */
	public boolean getTemporaryExecutionFlagIsSet() 
	{
		return false;
	}

    public boolean getOrderUnitIdIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderUnitIdIsModified();
    }

    public boolean getInstitutionCodeIsModified() {
        // TODO Auto-generated method stub
        return source.getInstitutionCodeIsModified();
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

    public boolean getSettleDivIsSet() {
        // TODO Auto-generated method stub
        return source.getSettleDivIsSet();
    }

    public boolean getSettleDivIsModified() {
        // TODO Auto-generated method stub
        return source.getSettleDivIsModified();
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

    public boolean getExecutedAmountYenIsSet() {
        // TODO Auto-generated method stub
        return source.getExecutedAmountYenIsSet();
    }

    public boolean getExecutedAmountYenIsModified() {
        // TODO Auto-generated method stub
        return source.getExecutedAmountYenIsModified();
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

    public boolean getCurrencyCodeIsModified() {
        // TODO Auto-generated method stub
        return source.getCurrencyCodeIsModified();
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

    public boolean getFEstimatedPriceIsSet() {
        // TODO Auto-generated method stub
        return source.getFEstimatedPriceIsSet();
    }

    public boolean getFEstimatedPriceIsModified() {
        // TODO Auto-generated method stub
        return source.getFEstimatedPriceIsModified();
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

    public boolean getCapitalGainIsSet() {
        // TODO Auto-generated method stub
        return source.getCapitalGainIsSet();
    }

    public boolean getCapitalGainIsModified() {
        // TODO Auto-generated method stub
        return source.getCapitalGainIsModified();
    }

    public boolean getCapitalGainTaxIsSet() {
        // TODO Auto-generated method stub
        return source.getCapitalGainTaxIsSet();
    }

    public boolean getCapitalGainTaxIsModified() {
        // TODO Auto-generated method stub
        return source.getCapitalGainTaxIsModified();
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

    public boolean getConfirmedFEstimatedPriceIsSet() {
        // TODO Auto-generated method stub
        return source.getConfirmedFEstimatedPriceIsSet();
    }

    public boolean getConfirmedFEstimatedPriceIsModified() {
        // TODO Auto-generated method stub
        return source.getConfirmedFEstimatedPriceIsModified();
    }

    public boolean getConfirmedExecConditionTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getConfirmedExecConditionTypeIsSet();
    }

    public boolean getConfirmedExecConditionTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getConfirmedExecConditionTypeIsModified();
    }

    public boolean getErrorReasonCodeIsSet() {
        // TODO Auto-generated method stub
        return source.getErrorReasonCodeIsSet();
    }

    public boolean getErrorReasonCodeIsModified() {
        // TODO Auto-generated method stub
        return source.getErrorReasonCodeIsModified();
    }

    public boolean getFactorIsSet() {
        // TODO Auto-generated method stub
        return source.getFactorIsSet();
    }

    public boolean getFactorIsModified() {
        // TODO Auto-generated method stub
        return source.getFactorIsModified();
    }

    public boolean getOrderEmpCodeIsSet() {
        // TODO Auto-generated method stub
        return source.getOrderEmpCodeIsSet();
    }

    public boolean getOrderEmpCodeIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderEmpCodeIsModified();
    }

    public boolean getAccountDivIsSet() {
        // TODO Auto-generated method stub
        return source.getAccountDivIsSet();
    }

    public boolean getAccountDivIsModified() {
        // TODO Auto-generated method stub
        return source.getAccountDivIsModified();
    }

    public boolean getExecEndTimestampIsSet() {
        // TODO Auto-generated method stub
        return source.getExecEndTimestampIsSet();
    }

    public boolean getExecEndTimestampIsModified() {
        // TODO Auto-generated method stub
        return source.getExecEndTimestampIsModified();
    }

    public boolean getExecFileSendFlagIsSet() {
        // TODO Auto-generated method stub
        return source.getExecFileSendFlagIsSet();
    }

    public boolean getExecFileSendFlagIsModified() {
        // TODO Auto-generated method stub
        return source.getExecFileSendFlagIsModified();
    }

    public boolean getFirstOrderUnitIdIsSet() {
        // TODO Auto-generated method stub
        return source.getFirstOrderUnitIdIsSet();
    }

    public boolean getFirstOrderUnitIdIsModified() {
        // TODO Auto-generated method stub
        return source.getFirstOrderUnitIdIsModified();
    }

    public boolean getLastUpdaterIsSet() {
        // TODO Auto-generated method stub
        return source.getLastUpdaterIsSet();
    }

    public boolean getLastUpdaterIsModified() {
        // TODO Auto-generated method stub
        return source.getLastUpdaterIsModified();
    }

    public boolean getCreatedTimestampIsModified() {
        // TODO Auto-generated method stub
        return source.getCreatedTimestampIsModified();
    }

    public boolean getLastUpdatedTimestampIsModified() {
        // TODO Auto-generated method stub
        return source.getLastUpdatedTimestampIsModified();
    }

    public boolean getExecutionSeqNoIsModified() {
        // TODO Auto-generated method stub
        return source.getExecutionSeqNoIsModified();
    }

    public boolean getTemporaryExecutionFlagIsModified() {
        // TODO Auto-generated method stub
        return source.getTemporaryExecutionFlagIsModified();
    }

}
@
