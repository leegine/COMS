head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.39.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	EquityQuoteDataQueryParamsSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����̎�����񌟍����Ɏg�p���錟��������\���N���X(EquityQuoteDataQueryParamsSpec.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/14 �R�c�@@��i(FLJ) �V�K�쐬
                 : 2005/03/17 �R�c�@@��i(FLJ) ������񂪑��݂��Ȃ��ꍇ�A���ݒl��Double.NaN��Ԃ��B
*/
package webbroker3.quoteadaptor.prototype;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;

import webbroker3.quoteadaptor.DataType;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoParams;
import webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoRow;

/**
 * �����̎�����񌟍����Ɏg�p���錟��������\���N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class EquityQuoteDataQueryParamsSpec extends AbstractQuoteDataQueryParamsSpec
{

    private final String where;
    private final Object[] bindVars;

    private final RealType realType;
    private final DataType dataType;
    private final String marketCode;
    private final String productCode;

    EquityQuoteDataQueryParamsSpec(
        EqTypeTradedProduct tradedProduct,
        RealType realType)
    {
        if (tradedProduct == null || realType == null)
        {
            throw new IllegalArgumentException("tradedProduct and realType is required.");
        }
        this.realType = realType;
        this.dataType = DataType.EQUITY;
        this.marketCode = tradedProduct.getMarket().getMarketCode();
        this.productCode =
            ((EqTypeProduct) tradedProduct.getProduct()).getProductCode();
        this.where =
            "real_type=? and data_type=? and market_code=? and product_code=?";
        this.bindVars =
            new Object[] { realType, dataType, marketCode, productCode };
    }

    /* 
     * @@see webbroker3.quoteadaptor.prototype.QuoteDataQueryParamsSpec#getBindVars()
     */
    public Object[] getBindVars()
    {
        return bindVars;
    }

    /* 
     * @@see webbroker3.quoteadaptor.prototype.QuoteDataQueryParamsSpec#getWhere()
     */
    public String getQueryString()
    {
        return where;
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
        return params;
    }

}
@
