head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.39.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	AbstractQuoteDataQueryParamsSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価情報の検索条件を表す抽象クラス(AbstractQuoteDataQueryParamsSpec.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/14 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.quoteadaptor.prototype;

import java.text.DateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.quoteadaptor.prototype.data.*;

/**
 * 時価情報の検索条件を表す抽象クラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
abstract class AbstractQuoteDataQueryParamsSpec
    implements QuoteDataQueryParamsSpec
{

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("QuoteDataQueryParamsSpec={");
        buf.append("where=").append(getQueryString());
        buf.append(", bindVars={");
        Object[] bindVars = getBindVars();
        if (bindVars != null)
        {
            for (int i = 0; i < bindVars.length; i++)
            {
                buf.append(bindVars[i]).append(",");
            }
        }
        buf.append("}");
        buf.append("}");
        return buf.toString();
    }

    /*
     * @@see webbroker3.quoteadaptor.prototype.QuoteDataQueryParamsSpec#newWeb3QuoteProtoRow()
     */
    public Web3QuoteProtoRow newWeb3QuoteProtoRow() throws DataException
    {
        Web3QuoteProtoParams params = new Web3QuoteProtoParams();
        params.setQuoteDataId(Web3QuoteProtoDao.newPkValue());
        params.setQuoteDate(toYYYYMMDDString(GtlUtils.getSystemTimestamp()));
        params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return params;
    }
    
    protected String toYYYYMMDDString(Date target)
    {
        DateFormat dateFormat = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        return dateFormat.format(target);
    }
    
    protected String toHHMMString(Date target)
    {
        DateFormat dateFormat = GtlUtils.getThreadSafeSimpleDateFormat("HHmm");
        return dateFormat.format(target);
    }
    
    

}
@
