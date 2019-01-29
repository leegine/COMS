head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.22.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackEqtypeTradedProductImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3PreLoaderCallbackEqtypeTradedProductImplクラス(WEB3PreLoaderCallbackEqtypeTradedProductImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/24 山田　@卓司 (FLJ) 新規作成
 */
package webbroker3.preloader.stdimpls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import webbroker3.preloader.WEB3DefaultPreLoaderCallback;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

/**
 * 
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3PreLoaderCallbackEqtypeTradedProductImpl
    extends WEB3DefaultPreLoaderCallback
{

    // 検索対象となる有効日の配列
    private String[] validUntilDates = null;

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#load(com.fitechlabs.dbind.Row)
     */
    public void load(Row l_row) throws DataException
    {
        // 会社コード、銘柄ID、有効期限日による検索 
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow) l_row;
        
        String l_strWhere =
            "institution_code=? and product_id=? and market_id=? and valid_until_biz_date=?";
        Object[] l_objBindVars = new Object[4];
        l_objBindVars[0] = l_tradedProductRow.getInstitutionCode();
        l_objBindVars[1] = new Long(l_tradedProductRow.getProductId());
        l_objBindVars[2] = new Long(l_tradedProductRow.getMarketId());
        
        String[] l_strValidUntilsDates = getValidUntilDates(l_tradedProductRow);
        
        for (int i = 0; i < l_strValidUntilsDates.length; i++)
        {
        	l_objBindVars[3] = l_strValidUntilsDates[i];
			Processors.getDefaultProcessor().doFindAllQuery(
				EqtypeTradedProductRow.TYPE,
				l_strWhere,
				l_objBindVars);
        }
        
        l_strWhere = "product_id = ? AND valid_until_biz_date = ? AND today_dep_fund_reg = ?";
        l_objBindVars = new Object[3];
        l_objBindVars[0] = new Long(l_tradedProductRow.getProductId());
        l_objBindVars[1] = new Long(l_tradedProductRow.getValidUntilBizDate());
        l_objBindVars[2] = BooleanEnum.TRUE;
		Processors.getDefaultProcessor().doFindAllQuery(
				EqtypeTradedProductRow.TYPE,
				l_strWhere,
				l_objBindVars);
        
    }

    // 有効日の配列を取得する
    private String[] getValidUntilDates(EqtypeTradedProductRow l_row)
        throws DataException
    {
        if (validUntilDates == null)
        {
            
            List l_tempDates = new ArrayList();
            l_tempDates.add(l_row.getValidUntilBizDate());
            
            List l_updqRows =
                EqtypeTradedProductUpdqDao.findRowsByTradedProductId(
                    l_row.getTradedProductId());
            if (l_updqRows != null)
            {
                for (Iterator l_it = l_updqRows.iterator(); l_it.hasNext();)
                {
                    EqtypeTradedProductUpdqRow l_updqRow =
                        (EqtypeTradedProductUpdqRow) l_it.next();
                    l_tempDates.add(l_updqRow.getValidUntilBizDate());
                }
            }
            validUntilDates =
                (String[]) l_tempDates.toArray(new String[l_tempDates.size()]);
        }
        return validUntilDates;
    }

}
@
