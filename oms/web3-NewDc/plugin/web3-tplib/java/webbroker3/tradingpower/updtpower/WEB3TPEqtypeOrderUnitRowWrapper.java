head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPEqtypeOrderUnitRowWrapper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : ó]óÕäîéÆíçï∂íPà Wrapper(WEB3TPEqtypeOrderUnitRowWrapper.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/02/04 ñxñÏ òaî¸(FLJ) êVãKçÏê¨
Revision History : 2007/05/08 è—éuàÃ(íÜêu) êMópã≠êßåàçœëŒâû [äîéÆ]DBLayout145
*/
package webbroker3.tradingpower.updtpower;

import java.sql.Timestamp;
import java.util.Map;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

/**
 * ( ó]óÕäîéÆíçï∂íPà Wrapper) <BR>
 * ó]óÕåvéZéûésèÍämîFçœílÇégópÇ∑ÇÈÇ…Ç†ÇΩÇË
 * äîéÆíçï∂íPà çsÉNÉâÉXÇÃÉâÉbÉpÅ[Ç∆ÇµÇƒã@@î\Ç∑ÇÈÅB
 */
public class WEB3TPEqtypeOrderUnitRowWrapper implements EqtypeOrderUnitRow
{
	/**
	 * (äîéÆíçï∂íPà )
	 */
	private EqtypeOrderUnitRow source;

	/**
	 * (äÑëùçSë©ó¶)
	 */
	private double premiumRestraintRate;
	
	/*
	 * ÉfÉtÉHÉãÉgÉRÉìÉXÉgÉâÉNÉ^ÅiégópÇµÇ»Ç¢ÇÊÇ§Ç…Ç∑ÇÈÅj
	 */
	private WEB3TPEqtypeOrderUnitRowWrapper(){};
	
	/**
	 * ÉRÉìÉXÉgÉâÉNÉ^
	 * 
	 * @@param l_rowÅ@@äîéÆíçï∂íPà 
	 */
	public WEB3TPEqtypeOrderUnitRowWrapper(EqtypeOrderUnitRow l_row)
	{
		this(l_row, 1.0);
	}

	/**
	 * ÉRÉìÉXÉgÉâÉNÉ^
	 * 
	 * @@param l_rowÅ@@äîéÆíçï∂íPà 
	 * @@param l_dblPreRestRateÅ@@äÑëùçSë©ó¶
	 */
	public WEB3TPEqtypeOrderUnitRowWrapper(EqtypeOrderUnitRow l_row, double l_dblPreRestRate)
	{
		source = l_row;
		premiumRestraintRate = l_dblPreRestRate;
	}

	/**
	 * EqtypeOrderUnitRow.getíçï∂íPâø()Çé¿ëïÇ∑ÇÈÅB<BR>
	 * <BR>
	 * ÇPÅjthis.äîéÆíçï∂íPà .íçï∂éÌï Ç™<BR>
	 * åªï®îÉÅAêVãKîÉåöÅAêVãKîÑåöÇÃèÍçá<BR>
	 * this.äîéÆíçï∂íPà .äTéZéÛìnë„ã‡, this.äîéÆíçï∂íPà .ésèÍämîFçœäTéZéÛìnë„ã‡)Çî‰ärÇ∑ÇÈ<BR>
	 * [äTéZéÛìnë„ã‡ > ésèÍämîFçœäTéZéÛìnë„ã‡ÇÃèÍçá]<BR>
	 * íçï∂íPâøÇï‘ãpÅB<BR>
	 * [è„ãLà»äO]<BR>
	 * ésèÍämîFçœíçï∂íPâøÇï‘ãpÅB<BR>
	 * ÇQÅjÅ@@ÇPÅjà»äO<BR>
	 * this.äîéÆíçï∂íPà .getíçï∂íPâø()Çï‘ãpÇ∑ÇÈÅB<BR>
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getPrice()
	 */
	public double getPrice() {
		
		double l_dblPrice = 0.0d;
		
		if((OrderTypeEnum.EQUITY_BUY.equals(source.getOrderType())) 
				||
			(OrderTypeEnum.MARGIN_LONG.equals(source.getOrderType()))
				||
			(OrderTypeEnum.MARGIN_SHORT.equals(source.getOrderType())))
		{			
			//äTéZéÛìnë„ã‡Ç™ëÂÇ´Ç¢ÇŸÇ§Ç∆êÆçáÇ∑ÇÈíçï∂íPâøÇ†ÇÈÇ¢ÇÕésèÍämîFçœíçï∂íPâøÇï‘Ç∑
            if(source.getEstimatedPrice() >= source
                .getConfirmedEstimatedPrice())
            {
                //Y00125ÅFäÑëùçSë©ó¶ëŒâû
                //ê¨çsíçï∂ÇÃèÍçá
                if(source.getLimitPrice() == 0)
                {
                    //íçï∂íPâø = äîéÆíçï∂íPà ÉeÅ[ÉuÉã.íçï∂íPâø * äÑëùçSë©ó¶
                    l_dblPrice = source.getPrice() * premiumRestraintRate;
                }
                //éwílíçï∂ÇÃèÍçá
                else
                {
                    l_dblPrice = source.getPrice();
                }
            }
            else
            {
                //Y00125ÅFäÑëùçSë©ó¶ëŒâû
                //ê¨çsíçï∂ÇÃèÍçá
                if(source.getConfirmedPrice() == 0)
                {
                    //íçï∂íPâø = äîéÆíçï∂íPà ÉeÅ[ÉuÉã.íçï∂íPâø * äÑëùçSë©ó¶
                    l_dblPrice = source.getConfirmedOrderPrice() * premiumRestraintRate;
                }
                //éwílíçï∂ÇÃèÍçá
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
	 * EqtypeOrderUnitRow.getíçï∂êîó ()Çé¿ëïÇ∑ÇÈÅB<BR>
	 * ÇPÅjthis.äîéÆíçï∂íPà .íçï∂éÌï Ç™<BR>
	 * åªï®îÉÅAêVãKîÉåöÅAêVãKîÑåöÇÃèÍçá<BR>
	 * this.äîéÆíçï∂íPà .äTéZéÛìnë„ã‡, this.äîéÆíçï∂íPà .ésèÍämîFçœäTéZéÛìnë„ã‡)Çî‰ärÇ∑ÇÈ<BR>
	 * [äTéZéÛìnë„ã‡ > ésèÍämîFçœäTéZéÛìnë„ã‡ÇÃèÍçá]<BR>
	 * íçï∂êîó Çï‘ãpÅB<BR>
	 * [è„ãLà»äO]<BR>
	 * ésèÍämîFçœíçï∂êîó Çï‘ãpÅB<BR>
	 * ÇQÅjÅ@@ÇPÅjà»äO<BR>
	 * this.äîéÆíçï∂íPà .getíçï∂êîó ()Çï‘ãpÇ∑ÇÈÅB<BR>
	 * 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getQuantity()
	 */
	public double getQuantity() {
		
		double l_dblQty = 0.0d;

		if((OrderTypeEnum.EQUITY_BUY.equals(source.getOrderType())) 
				||
			(OrderTypeEnum.MARGIN_LONG.equals(source.getOrderType()))
				||
			(OrderTypeEnum.MARGIN_SHORT.equals(source.getOrderType())))
		{
			//äTéZéÛìnë„ã‡Ç™ëÂÇ´Ç¢ÇŸÇ§Ç∆êÆçáÇ∑ÇÈíçï∂êîó Ç†ÇÈÇ¢ÇÕésèÍämîFçœíçï∂êîó Çï‘Ç∑
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
	 * EqtypeOrderUnitRow.getäTéZéÛìnë„ã‡()Çé¿ëïÇ∑ÇÈÅB<BR>
	 * ÇPÅjthis.äîéÆíçï∂íPà .íçï∂éÌï Ç™<BR>
	 * åªï®îÉÅAêVãKîÉåöÅAêVãKîÑåöÇÃèÍçá<BR>
	 * Max(äîéÆíçï∂íPà .äTéZéÛìnë„ã‡, this.äîéÆíçï∂íPà .ésèÍämîFçœäTéZéÛìnë„ã‡) Çï‘ãpÇ∑ÇÈÅB<BR>
	 * ÇQÅjÅ@@ÇPÅjà»äO<BR>
	 * this.äîéÆíçï∂íPà .getäTéZéÛìnë„ã‡()Çï‘ãpÇ∑ÇÈÅB<BR>
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getEstimatedPrice()
	 */
	public double getEstimatedPrice() {
		
		double l_dblEstimatePrice = 0.0d;
		
		if((OrderTypeEnum.EQUITY_BUY.equals(source.getOrderType())) 
				||
			(OrderTypeEnum.MARGIN_LONG.equals(source.getOrderType()))
				||
			(OrderTypeEnum.MARGIN_SHORT.equals(source.getOrderType())))
		{
			//äTéZéÛìnë„ã‡Ç™ëÂÇ´Ç¢ÇŸÇ§Ç∆êÆçáÇ∑ÇÈíçï∂íPâøÇ†ÇÈÇ¢ÇÕésèÍämîFçœíçï∂íPâøÇï‘Ç∑
			l_dblEstimatePrice = Math.max(source.getEstimatedPrice(), source.getConfirmedEstimatedPrice());				
		}
		else
		{
			l_dblEstimatePrice = source.getEstimatedPrice();			
		}
		return l_dblEstimatePrice;
	}

	
	/*
	 * à»ç~ÇÃÉÅÉ\ÉbÉhÇÕthis.äîéÆíçï∂íPà Ç…èàóùÇàœè˜Ç∑ÇÈÅB
	 */
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderUnitId()
	 */
	public long getOrderUnitId() {
		return source.getOrderUnitId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderUnitIdIsSet()
	 */
	public boolean getOrderUnitIdIsSet() {
		return source.getOrderUnitIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getAccountId()
	 */
	public long getAccountId() {
		return source.getAccountId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getAccountIdIsSet()
	 */
	public boolean getAccountIdIsSet() {
		return source.getAccountIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getSubAccountId()
	 */
	public long getSubAccountId() {
		return source.getSubAccountId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getSubAccountIdIsSet()
	 */
	public boolean getSubAccountIdIsSet() {
		return source.getSubAccountIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getBranchId()
	 */
	public long getBranchId() {
		return source.getBranchId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getBranchIdIsSet()
	 */
	public boolean getBranchIdIsSet() {
		return source.getBranchIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getTraderId()
	 */
	public long getTraderId() {
		return source.getTraderId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getTraderIdIsNull()
	 */
	public boolean getTraderIdIsNull() {
		return source.getTraderIdIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderId()
	 */
	public long getOrderId() {
		return source.getOrderId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderIdIsSet()
	 */
	public boolean getOrderIdIsSet() {
		return source.getOrderIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderType()
	 */
	public OrderTypeEnum getOrderType() {
		return source.getOrderType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderTypeIsSet()
	 */
	public boolean getOrderTypeIsSet() {
		return source.getOrderTypeIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderCateg()
	 */
	public OrderCategEnum getOrderCateg() {
		return source.getOrderCateg();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderCategIsSet()
	 */
	public boolean getOrderCategIsSet() {
		return source.getOrderCategIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getLastOrderActionSerialNo()
	 */
	public int getLastOrderActionSerialNo() {
		return source.getLastOrderActionSerialNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getLastOrderActionSerialNoIsSet()
	 */
	public boolean getLastOrderActionSerialNoIsSet() {
		return source.getLastOrderActionSerialNoIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getLastExecutionSerialNo()
	 */
	public int getLastExecutionSerialNo() {
		return source.getLastExecutionSerialNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getLastExecutionSerialNoIsSet()
	 */
	public boolean getLastExecutionSerialNoIsSet() {
		return source.getLastExecutionSerialNoIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getProductType()
	 */
	public ProductTypeEnum getProductType() {
		return source.getProductType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getProductTypeIsSet()
	 */
	public boolean getProductTypeIsSet() {
		return source.getProductTypeIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getMarketId()
	 */
	public long getMarketId() {
		return source.getMarketId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getMarketIdIsNull()
	 */
	public boolean getMarketIdIsNull() {
		return source.getMarketIdIsNull();
	}
	
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getQuantityIsSet()
	 */
	public boolean getQuantityIsSet() {
		return source.getQuantityIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getLimitPrice()
	 */
	public double getLimitPrice() {
		return source.getLimitPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getLimitPriceIsNull()
	 */
	public boolean getLimitPriceIsNull() {
		return source.getLimitPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getExecutionConditionType()
	 */
	public EqTypeExecutionConditionType getExecutionConditionType() {
		return source.getExecutionConditionType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getPriceConditionType()
	 */
	public String getPriceConditionType() {
		return source.getPriceConditionType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderConditionType()
	 */
	public String getOrderConditionType() {
		return source.getOrderConditionType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderCondOperator()
	 */
	public String getOrderCondOperator() {
		return source.getOrderCondOperator();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getStopOrderPrice()
	 */
	public double getStopOrderPrice() {
		return source.getStopOrderPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getStopOrderPriceIsNull()
	 */
	public boolean getStopOrderPriceIsNull() {
		return source.getStopOrderPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getWLimitPrice()
	 */
	public double getWLimitPrice() {
		return source.getWLimitPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getWLimitPriceIsNull()
	 */
	public boolean getWLimitPriceIsNull() {
		return source.getWLimitPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getDeliveryDate()
	 */
	public Timestamp getDeliveryDate() {
		return source.getDeliveryDate();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getDeliveryDateIsSet()
	 */
	public boolean getDeliveryDateIsSet() {
		return source.getDeliveryDateIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getExpirationDate()
	 */
	public Timestamp getExpirationDate() {
		return source.getExpirationDate();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getConfirmedQuantity()
	 */
	public double getConfirmedQuantity() {
		return source.getConfirmedQuantity();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getConfirmedQuantityIsNull()
	 */
	public boolean getConfirmedQuantityIsNull() {
		return source.getConfirmedQuantityIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getConfirmedPrice()
	 */
	public double getConfirmedPrice() {
		return source.getConfirmedPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getConfirmedPriceIsNull()
	 */
	public boolean getConfirmedPriceIsNull() {
		return source.getConfirmedPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getExecutedQuantity()
	 */
	public double getExecutedQuantity() {
		return source.getExecutedQuantity();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getExecutedQuantityIsNull()
	 */
	public boolean getExecutedQuantityIsNull() {
		return source.getExecutedQuantityIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getExecutedAmount()
	 */
	public double getExecutedAmount() {
		return source.getExecutedAmount();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getExecutedAmountIsNull()
	 */
	public boolean getExecutedAmountIsNull() {
		return source.getExecutedAmountIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderStatus()
	 */
	public OrderStatusEnum getOrderStatus() {
		return source.getOrderStatus();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderStatusIsSet()
	 */
	public boolean getOrderStatusIsSet() {
		return source.getOrderStatusIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderOpenStatus()
	 */
	public OrderOpenStatusEnum getOrderOpenStatus() {
		return source.getOrderOpenStatus();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderOpenStatusIsSet()
	 */
	public boolean getOrderOpenStatusIsSet() {
		return source.getOrderOpenStatusIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getExpirationStatus()
	 */
	public OrderExpirationStatusEnum getExpirationStatus() {
		return source.getExpirationStatus();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getExpirationStatusIsSet()
	 */
	public boolean getExpirationStatusIsSet() {
		return source.getExpirationStatusIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getTaxType()
	 */
	public TaxTypeEnum getTaxType() {
		return source.getTaxType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getTaxTypeIsSet()
	 */
	public boolean getTaxTypeIsSet() {
		return source.getTaxTypeIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getSwapTaxType()
	 */
	public TaxTypeEnum getSwapTaxType() {
		return source.getSwapTaxType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getSwapTaxTypeIsSet()
	 */
	public boolean getSwapTaxTypeIsSet() {
		return source.getSwapTaxTypeIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getRepaymentType()
	 */
	public String getRepaymentType() {
		return source.getRepaymentType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getRepaymentNum()
	 */
	public int getRepaymentNum() {
		return source.getRepaymentNum();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getRepaymentNumIsNull()
	 */
	public boolean getRepaymentNumIsNull() {
		return source.getRepaymentNumIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getSonarRepaymentType()
	 */
	public String getSonarRepaymentType() {
		return source.getSonarRepaymentType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getBizDate()
	 */
	public String getBizDate() {
		return source.getBizDate();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getBizDateIsSet()
	 */
	public boolean getBizDateIsSet() {
		return source.getBizDateIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getProductId()
	 */
	public long getProductId() {
		return source.getProductId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getProductIdIsSet()
	 */
	public boolean getProductIdIsSet() {
		return source.getProductIdIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getQuantityType()
	 */
	public QuantityTypeEnum getQuantityType() {
		return source.getQuantityType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getQuantityTypeIsSet()
	 */
	public boolean getQuantityTypeIsSet() {
		return source.getQuantityTypeIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderChanel()
	 */
	public String getOrderChanel() {
		return source.getOrderChanel();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getReceivedDateTime()
	 */
	public Timestamp getReceivedDateTime() {
		return source.getReceivedDateTime();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getVoucherNo()
	 */
	public String getVoucherNo() {
		return source.getVoucherNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getCommTblNo()
	 */
	public String getCommTblNo() {
		return source.getCommTblNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getCommTblSubNo()
	 */
	public String getCommTblSubNo() {
		return source.getCommTblSubNo();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getSonarTraderCode()
	 */
	public String getSonarTraderCode() {
		return source.getSonarTradedCode();
	}


	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getPriceIsNull()
	 */
	public boolean getPriceIsNull() {
		return source.getPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderRequestNumber()
	 */
	public String getOrderRequestNumber() {
		return source.getOrderRequestNumber();
	}


	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getEstimatedPriceIsNull()
	 */
	public boolean getEstimatedPriceIsNull() {
		return source.getEstimatedPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getCapitalGain()
	 */
	public double getCapitalGain() {
		return source.getCapitalGain();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getCapitalGainIsNull()
	 */
	public boolean getCapitalGainIsNull() {
		return source.getCapitalGainIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getCapitalGainIsSet()
	 */
	public boolean getCapitalGainIsSet() {
		return source.getCapitalGainIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getCapitalGainTax()
	 */
	public double getCapitalGainTax() {
		return source.getCapitalGainTax();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getCapitalGainTaxIsNull()
	 */
	public boolean getCapitalGainTaxIsNull() {
		return source.getCapitalGainTaxIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getCapitalGainTaxIsSet()
	 */
	public boolean getCapitalGainTaxIsSet() {
		return source.getCapitalGainTaxIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getSonarTradedCode()
	 */
	public String getSonarTradedCode() {
		return source.getSonarTradedCode();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getSonarMarketCode()
	 */
	public String getSonarMarketCode() {
		return source.getSonarMarketCode();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getCommProductCode()
	 */
	public String getCommProductCode() {
		return source.getCommProductCode();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getShortSellOrderFlag()
	 */
	public String getShortSellOrderFlag() {
		return source.getShortSellOrderFlag();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getModifyCancelType()
	 */
	public String getModifyCancelType() {
		return source.getModifyCancelType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderRootDiv()
	 */
	public String getOrderRootDiv() {
		return source.getOrderRootDiv();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getConfirmedOrderPrice()
	 */
	public double getConfirmedOrderPrice() {
		return source.getConfirmedOrderPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getConfirmedOrderPriceIsNull()
	 */
	public boolean getConfirmedOrderPriceIsNull() {
		return source.getConfirmedOrderPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getConfirmedEstimatedPrice()
	 */
	public double getConfirmedEstimatedPrice() {
		return source.getConfirmedEstimatedPrice();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getConfirmedEstimatedPriceIsNull()
	 */
	public boolean getConfirmedEstimatedPriceIsNull() {
		return source.getConfirmedEstimatedPriceIsNull();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getConfirmedExecConditionType()
	 */
	public EqTypeExecutionConditionType getConfirmedExecConditionType() {
		return source.getConfirmedExecConditionType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getConfirmedPriceConditionType()
	 */
	public String getConfirmedPriceConditionType() {
		return source.getConfirmedPriceConditionType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getClosingOrderType()
	 */
	public String getClosingOrderType() {
		return source.getClosingOrderType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getErrorReasonCode()
	 */
	public String getErrorReasonCode() {
		return source.getErrorReasonCode();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getRequestType()
	 */
	public String getRequestType() {
		return source.getRequestType();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getFirstOrderUnitId()
	 */
	public long getFirstOrderUnitId() {
		return source.getFirstOrderUnitId();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getFirstOrderUnitIdIsNull()
	 */
	public boolean getFirstOrderUnitIdIsNull() {
		return source.getFirstOrderUnitIdIsNull();
	}

    /* 
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getSubmitOrderRouteDiv()
     */
    public String getSubmitOrderRouteDiv()
    {
        return source.getSubmitOrderRouteDiv();
    }

    /* 
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderRev()
     */
    public String getOrderRev() {        
        return source.getOrderRev();
    }

    /* 
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrderRevIsSet()
     */
    public boolean getOrderRevIsSet() {
        return source.getOrderRevIsSet();
    }

    /* 
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getConfirmedOrderRev()
     */
    public String getConfirmedOrderRev() {
        return source.getConfirmedOrderRev();
    }

    /* 
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getConfirmedOrderRevIsSet()
     */
    public boolean getConfirmedOrderRevIsSet() {
        return source.getConfirmedOrderRevIsSet();
    }

    /* 
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getReserveOrderExistFlag()
     */
    public String getReserveOrderExistFlag() {
        return source.getReserveOrderExistFlag();
    }

    /* 
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getReserveOrderExistFlagIsSet()
     */
    public boolean getReserveOrderExistFlagIsSet() {
        return source.getReserveOrderExistFlagIsSet();
    }
    
	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getCreatedTimestamp()
	 */
	public Timestamp getCreatedTimestamp() {
		return source.getCreatedTimestamp();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getCreatedTimestampIsSet()
	 */
	public boolean getCreatedTimestampIsSet() {
		return source.getCreatedTimestampIsSet();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getLastUpdatedTimestamp()
	 */
	public Timestamp getLastUpdatedTimestamp() {
		return source.getLastUpdatedTimestamp();
	}

	/* 
	 * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getLastUpdatedTimestampIsSet()
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
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOriginalQuantity()
     */
    public double getOriginalQuantity()
    {
        return source.getOriginalQuantity();
    }

    /* 
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOriginalQuantityIsNull()
     */
    public boolean getOriginalQuantityIsNull()
    {
        return source.getOriginalQuantityIsNull();
    }

    /* 
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOriginalQuantityIsSet()
     */
    public boolean getOriginalQuantityIsSet()
    {
        return source.getOriginalQuantityIsSet();
    }

    /* 
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getStopOrderOrderedTimestamp()
     */
    public Timestamp getStopOrderOrderedTimestamp()
    {
        return source.getStopOrderOrderedTimestamp();
    }

    
    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrgOrderConditionType()
     */
    public String getOrgOrderConditionType()
    {
        return source.getOrgOrderConditionType();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrgOrderCondOperator()
     */
    public String getOrgOrderCondOperator()
    {
        return source.getOrgOrderCondOperator();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrgStopOrderPrice()
     */
    public double getOrgStopOrderPrice()
    {
        return source.getOrgStopOrderPrice();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrgStopOrderPriceIsNull()
     */
    public boolean getOrgStopOrderPriceIsNull()
    {
        return source.getOrgStopOrderPriceIsNull();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrgWLimitExecCondType()
     */
    public EqTypeExecutionConditionType getOrgWLimitExecCondType()
    {
        return source.getOrgWLimitExecCondType();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrgWLimitPrice()
     */
    public double getOrgWLimitPrice()
    {
        return source.getOrgWLimitPrice();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getOrgWLimitPriceIsNull()
     */
    public boolean getOrgWLimitPriceIsNull()
    {
        return source.getOrgWLimitPriceIsNull();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getWLimitBeforeExecCondType()
     */
    public EqTypeExecutionConditionType getWLimitBeforeExecCondType()
    {
        return source.getWLimitBeforeExecCondType();
    }

    /* 
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getWLimitBeforeLimitPrice()
     */
    public double getWLimitBeforeLimitPrice()
    {
        return source.getWLimitBeforeLimitPrice();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getWLimitBeforeLimitPriceIsNull()
     */
    public boolean getWLimitBeforeLimitPriceIsNull()
    {
        return source.getWLimitBeforeLimitPriceIsNull();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getWLimitExecCondType()
     */
    public EqTypeExecutionConditionType getWLimitExecCondType()
    {
        return source.getWLimitExecCondType();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getSubmitOrderDelayFlag()
     */
    public BooleanEnum getSubmitOrderDelayFlag()
    {
        return source.getSubmitOrderDelayFlag();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getSubmitOrderDelayFlagIsSet()
     */
    public boolean getSubmitOrderDelayFlagIsSet()
    {
        return source.getSubmitOrderDelayFlagIsSet();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getForcedSettleReasonType()
     */
    public String getForcedSettleReasonType()
    {
        return source.getForcedSettleReasonType();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getApproveStatusType()
     */
    public String getApproveStatusType()
    {
        return source.getApproveStatusType();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getApproverCode()
     */
    public String getApproverCode()
    {
        return source.getApproverCode();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getApproveTimestamp()
     */
    public Timestamp getApproveTimestamp()
    {
        return source.getApproveTimestamp();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getMarginMaintenanceRate()
     */
    public double getMarginMaintenanceRate()
    {
        return source.getMarginMaintenanceRate();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getMarginMaintenanceRateIsNull()
     */
    public boolean getMarginMaintenanceRateIsNull()
    {
        return source.getMarginMaintenanceRateIsNull();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getAdditionalMarginDate()
     */
    public Timestamp getAdditionalMarginDate()
    {
        return source.getAdditionalMarginDate();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getAdditionalMarginAccruedDays()
     */
    public long getAdditionalMarginAccruedDays()
    {
        return source.getAdditionalMarginAccruedDays();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getAdditionalMarginAccruedDaysIsNull()
     */
    public boolean getAdditionalMarginAccruedDaysIsNull()
    {
        return source.getAdditionalMarginAccruedDaysIsNull();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getForcedExpireType()
     */
    public String getForcedExpireType()
    {
        return source.getForcedExpireType();
    }

    /*
     * @@see com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow#getForcedExpireTypeIsSet()
     */
    public boolean getForcedExpireTypeIsSet()
    {
        return source.getForcedExpireTypeIsSet();
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

    public boolean getPriceConditionTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getPriceConditionTypeIsSet();
    }

    public boolean getPriceConditionTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getPriceConditionTypeIsModified();
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

    public boolean getSwapTaxTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getSwapTaxTypeIsModified();
    }

    public boolean getRepaymentTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getRepaymentTypeIsSet();
    }

    public boolean getRepaymentTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getRepaymentTypeIsModified();
    }

    public boolean getRepaymentNumIsSet() {
        // TODO Auto-generated method stub
        return source.getRepaymentNumIsSet();
    }

    public boolean getRepaymentNumIsModified() {
        // TODO Auto-generated method stub
        return source.getRepaymentNumIsModified();
    }

    public boolean getSonarRepaymentTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getSonarRepaymentTypeIsSet();
    }

    public boolean getSonarRepaymentTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getSonarRepaymentTypeIsModified();
    }

    public boolean getBizDateIsModified() {
        // TODO Auto-generated method stub
        return source.getBizDateIsModified();
    }

    public boolean getProductIdIsModified() {
        // TODO Auto-generated method stub
        return source.getProductIdIsModified();
    }

    public boolean getQuantityTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getQuantityTypeIsModified();
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

    public boolean getCapitalGainIsModified() {
        // TODO Auto-generated method stub
        return source.getCapitalGainIsModified();
    }

    public boolean getCapitalGainTaxIsModified() {
        // TODO Auto-generated method stub
        return source.getCapitalGainTaxIsModified();
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

    public boolean getShortSellOrderFlagIsSet() {
        // TODO Auto-generated method stub
        return source.getShortSellOrderFlagIsSet();
    }

    public boolean getShortSellOrderFlagIsModified() {
        // TODO Auto-generated method stub
        return source.getShortSellOrderFlagIsModified();
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

    public boolean getSubmitOrderRouteDivIsSet() {
        // TODO Auto-generated method stub
        return source.getSubmitOrderRouteDivIsSet();
    }

    public boolean getSubmitOrderRouteDivIsModified() {
        // TODO Auto-generated method stub
        return source.getSubmitOrderRouteDivIsModified();
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

    public boolean getConfirmedPriceConditionTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getConfirmedPriceConditionTypeIsSet();
    }

    public boolean getConfirmedPriceConditionTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getConfirmedPriceConditionTypeIsModified();
    }

    public boolean getClosingOrderTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getClosingOrderTypeIsSet();
    }

    public boolean getClosingOrderTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getClosingOrderTypeIsModified();
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

    public boolean getConfirmedOrderRevIsModified() {
        // TODO Auto-generated method stub
        return source.getConfirmedOrderRevIsModified();
    }

    public boolean getOrderRevIsModified() {
        // TODO Auto-generated method stub
        return source.getOrderRevIsModified();
    }

    public boolean getReserveOrderExistFlagIsModified() {
        // TODO Auto-generated method stub
        return source.getReserveOrderExistFlagIsModified();
    }

    public boolean getOriginalQuantityIsModified() {
        // TODO Auto-generated method stub
        return source.getOriginalQuantityIsModified();
    }

    public boolean getStopOrderOrderedTimestampIsSet() {
        // TODO Auto-generated method stub
        return source.getStopOrderOrderedTimestampIsSet();
    }

    public boolean getStopOrderOrderedTimestampIsModified() {
        // TODO Auto-generated method stub
        return source.getStopOrderOrderedTimestampIsModified();
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

    public boolean getForcedSettleReasonTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getForcedSettleReasonTypeIsSet();
    }

    public boolean getForcedSettleReasonTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getForcedSettleReasonTypeIsModified();
    }

    public boolean getApproveStatusTypeIsSet() {
        // TODO Auto-generated method stub
        return source.getApproveStatusTypeIsSet();
    }

    public boolean getApproveStatusTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getApproveStatusTypeIsModified();
    }

    public boolean getApproverCodeIsSet() {
        // TODO Auto-generated method stub
        return source.getApproverCodeIsSet();
    }

    public boolean getApproverCodeIsModified() {
        // TODO Auto-generated method stub
        return source.getApproverCodeIsModified();
    }

    public boolean getApproveTimestampIsSet() {
        // TODO Auto-generated method stub
        return source.getApproveTimestampIsSet();
    }

    public boolean getApproveTimestampIsModified() {
        // TODO Auto-generated method stub
        return source.getApproveTimestampIsModified();
    }

    public boolean getMarginMaintenanceRateIsSet() {
        // TODO Auto-generated method stub
        return source.getMarginMaintenanceRateIsSet();
    }

    public boolean getMarginMaintenanceRateIsModified() {
        // TODO Auto-generated method stub
        return source.getMarginMaintenanceRateIsModified();
    }

    public boolean getAdditionalMarginDateIsSet() {
        // TODO Auto-generated method stub
        return source.getAdditionalMarginDateIsSet();
    }

    public boolean getAdditionalMarginDateIsModified() {
        // TODO Auto-generated method stub
        return source.getAdditionalMarginDateIsModified();
    }

    public boolean getAdditionalMarginAccruedDaysIsSet() {
        // TODO Auto-generated method stub
        return source.getAdditionalMarginAccruedDaysIsSet();
    }

    public boolean getAdditionalMarginAccruedDaysIsModified() {
        // TODO Auto-generated method stub
        return source.getAdditionalMarginAccruedDaysIsModified();
    }

    public boolean getForcedExpireTypeIsModified() {
        // TODO Auto-generated method stub
        return source.getForcedExpireTypeIsModified();
    }
}
@
