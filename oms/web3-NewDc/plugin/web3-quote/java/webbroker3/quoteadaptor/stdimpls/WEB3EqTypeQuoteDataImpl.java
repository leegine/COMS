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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����p�������N���X(WEB3EqTypeQuoteDataImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 �R�c�@@��i(FLJ) �V�K�쐬
                   2009/04/20 ���@@�@@�@@�@@��(FLJ) �����V�X�e���֑ؑΉ�
*/
package webbroker3.quoteadaptor.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;

import webbroker3.quoteadaptor.DataType;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3EqTypeQuoteData;

/**
 * �����p�������N���X
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
     * �R���X�g���N�^
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
