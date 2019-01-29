head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.39.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3EqTypeQuoteDataImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式用時価情報クラス(WEB3EqTypeQuoteDataImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 山田　@卓司(FLJ) 新規作成
                   2009/04/20 許　@　@　@　@競(FLJ) 時価システム切替対応
*/
package webbroker3.quoteadaptor.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;

import webbroker3.quoteadaptor.DataType;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3EqTypeQuoteData;

/**
 * 株式用時価情報クラス
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3EqTypeQuoteDataImpl
    extends AbstractWEB3QuoteData
    implements WEB3EqTypeQuoteData
{
    
    private EqTypeTradedProduct tradedProduct;

    /**
     * コンストラクタ
     */
    WEB3EqTypeQuoteDataImpl(
        EqTypeTradedProduct tradedProduct,
        RealType realType)
    {
        super();
        super.quoteDate = WEB3QuoteUtil.getSystemDate();
        super.realType = realType;
        super.dataType = DataType.EQUITY;
        super.marketCode = tradedProduct.getMarket().getMarketCode();
        super.productCode =
            ((EqTypeProduct) tradedProduct.getProduct()).getProductCode();
        this.tradedProduct = tradedProduct;
    }
    
    /**
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.QuoteData#getQuoteTimestamp()
     */
    public Timestamp getQuoteTimestamp()
    {
        return getCurrentPriceTime();
    }

}@
