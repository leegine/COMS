head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.39.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	FutureOptionQuoteDataQueryParamsSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物・オプションの時価情報検索時に使用する検索条件を表すクラス(FutureOptionQuoteDataQueryParamsSpec.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/14 山田　@卓司(FLJ) 新規作成
                 : 2005/03/17 山田　@卓司(FLJ) 時価情報が存在しない場合、現在値はDouble.NaNを返す。
*/
package webbroker3.quoteadaptor.prototype;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;

import webbroker3.quoteadaptor.*;
import webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoParams;
import webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoRow;

/**
 * 先物・オプションの時価情報検索時に使用する検索条件を表すクラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class FutureOptionQuoteDataQueryParamsSpec
    extends AbstractQuoteDataQueryParamsSpec
{

    private final String where;
    private final Object[] bindVars;
    
    private final RealType realType;
    private final DataType dataType;
    private final String marketCode;
    private final String productCode;
    private final String monthOfDelivery;
    private final String putAndCall;
    private final Double strikePrice;

    FutureOptionQuoteDataQueryParamsSpec(
        IfoTradedProduct tradedProduct,
        RealType realType)
    {
        
        if (tradedProduct == null || realType == null)
        {
            throw new IllegalArgumentException("tradedProduct and realType is required");
        }
        
        IfoProduct product = (IfoProduct) tradedProduct.getProduct();
        
        this.realType = realType;
        this.dataType = DataType.getDataType(tradedProduct);
        this.marketCode = tradedProduct.getMarket().getMarketCode();
        this.productCode = product.getUnderlyingProductCode();
        this.monthOfDelivery = product.getMonthOfDelivery();
        
        StringBuffer where = new StringBuffer();
        where.append(
            "real_type=? and data_type=? and market_code=? and product_code=? and contract_month=?");
        List bindVars = new ArrayList();
        bindVars.add(this.realType);
        bindVars.add(this.dataType);
        bindVars.add(this.marketCode);
        bindVars.add(this.productCode);
        bindVars.add(this.monthOfDelivery);
        
        if (DataType.INDEX_OPTION.equals(dataType))
        {
            
            this.putAndCall =
                String.valueOf(product.getDerivativeType().intValue());
            this.strikePrice = new Double(product.getStrikePrice());
            
            where.append(" and put_and_call=? and strike_price=?");
            bindVars.add(this.putAndCall);
            bindVars.add(this.strikePrice);
            
        } else {
            
            this.putAndCall = null;
            this.strikePrice = null;
            
        }
        this.where = where.toString();
        this.bindVars = bindVars.toArray(new Object[0]);
    }

    /*
     * @@see webbroker3.quoteadaptor.prototype.QuoteDataQueryParamsSpec#getWhere()
     */
    public String getQueryString()
    {
        return where;
    }

    /*
     * @@see webbroker3.quoteadaptor.prototype.QuoteDataQueryParamsSpec#getBindVars()
     */
    public Object[] getBindVars()
    {
        return bindVars;
    }

    /*
     * @@see webbroker3.quoteadaptor.prototype.QuoteDataQueryParamsSpec#newWeb3QuoteProtoRow()
     */
    public Web3QuoteProtoRow newWeb3QuoteProtoRow() throws DataException
    {
        Web3QuoteProtoParams params =
            (Web3QuoteProtoParams) super.newWeb3QuoteProtoRow();
        params.setRealType(realType);
        params.setDataType(dataType);
        params.setMarketCode(marketCode);
        params.setProductCode(productCode);
        params.setContractMonth(monthOfDelivery);
        params.setPutAndCall(putAndCall);
        params.setStrikePrice(strikePrice);
        return params;
    }

}
@
