head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3IndexQuoteDataImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 指数用時価情報クラス(WEB3IndexQuoteDataImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 山田　@卓司(FLJ) 新規作成
                   2009/04/20 許　@　@　@　@競(FLJ) 時価システム切替対応
*/
package webbroker3.quoteadaptor.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.quoteadaptor.*;

/**
 * 指数用時価情報クラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3IndexQuoteDataImpl
    extends AbstractWEB3QuoteData
    implements WEB3IndexQuoteData
{
    
    IndexType indexType;

    /**
     * コンストラクタ
     */
    WEB3IndexQuoteDataImpl(IndexType indexType, RealType realType)
    {
        super();
        super.quoteDate = WEB3QuoteUtil.getSystemDate();
        super.realType = realType;
        super.dataType = DataType.INDEX;
        super.productCode = WEB3QuoteProductCodes.toIndexProductCode(indexType);
        this.indexType = indexType;
    }
    
    public IndexType getIndexType()
    {
        return indexType;
    }

    /**
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.QuoteData#getQuoteTimestamp()
     */
    public Timestamp getQuoteTimestamp()
    {
        return getCurrentPriceTime();
    }

}
@
