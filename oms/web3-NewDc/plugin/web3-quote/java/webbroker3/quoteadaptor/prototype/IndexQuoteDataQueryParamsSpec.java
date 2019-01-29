head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.39.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	IndexQuoteDataQueryParamsSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 各種指数の時価情報検索条件を表すクラス(IndexQuoteDataQueryParamsSpec.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/14 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.quoteadaptor.prototype;

import com.fitechlabs.xtrade.kernel.data.DataException;

import webbroker3.quoteadaptor.*;
import webbroker3.quoteadaptor.DataType;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoParams;
import webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoRow;

/**
 * 各種指数の時価情報検索条件を表すクラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class IndexQuoteDataQueryParamsSpec extends AbstractQuoteDataQueryParamsSpec
{

    private String where;
    private Object[] bindVars;
    private RealType realType;
    private String productCode;
    private IndexType indexType;

    IndexQuoteDataQueryParamsSpec(RealType realType, IndexType indexType)
    {
        if (realType == null || indexType == null)
        {
            throw new IllegalArgumentException("realType and indexType is required.");
        }
        this.realType = realType;
        this.indexType = indexType;
        this.productCode = toIndexProductCode(indexType);
        where = "real_type=? and data_type=? and product_code=?";
        bindVars = new Object[] { this.realType, DataType.INDEX, this.productCode };
    }

    /*
     * @@see webbroker3.quoteadaptor.prototype.QuoteDataQueryParamsSpec#bindVars()
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
        Web3QuoteProtoParams params = (Web3QuoteProtoParams) super.newWeb3QuoteProtoRow();
        params.setRealType(realType);
        params.setDataType(DataType.INDEX);
        params.setMarketCode(" ");
        params.setProductCode(productCode);
        return params;
    }
    
    public IndexType getIndexType()
    {
        return indexType;
    }

    private String toIndexProductCode(IndexType indexType)
    {
        switch (indexType.intValue())
        {
            case IndexType.IntValues.NIKKEI225_INDEX :
                return "0016";
            case IndexType.IntValues.TOPIX :
                return "0005";
            case IndexType.IntValues.NIKKEI300_INDEX :
                return "0018";
            case IndexType.IntValues.JASDAQ_INDEX :
                return "9004";
            case IndexType.IntValues.TSE_2ND_SECTION_INDEX :
                return "9005";
            default :
                StringBuffer message = new StringBuffer();
                message.append("IndexType[").append(indexType).append("]");
                message.append(" is unsupported IndexType.");
                throw new IllegalArgumentException(message.toString());

        }
    }

}
@
