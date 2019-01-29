head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.21.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackEqtypeTradedProductUpdqImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3PreLoaderCallbackEqtypeTradedProductUpdqImplクラス(WEB3PreLoaderCallbackEqtypeTradedProductUpdqImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/25 山田　@卓司 (FLJ) 新規作成
 */
package webbroker3.preloader.stdimpls;

import webbroker3.preloader.WEB3DefaultPreLoaderCallback;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

/**
 * 
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3PreLoaderCallbackEqtypeTradedProductUpdqImpl
    extends WEB3DefaultPreLoaderCallback
{

    /**
     * @@see webbroker3.preloader.WEB3DefaultPreLoaderCallback#load(com.fitechlabs.dbind.Row)
     */
    protected void load(Row l_row) throws DataException
    {

        EqtypeTradedProductUpdqRow l_tradedProductUpdqRow =
            (EqtypeTradedProductUpdqRow) l_row;
        QueryProcessor l_qp = Processors.getDefaultProcessor();

        String l_strInstitutionCode =
            l_tradedProductUpdqRow.getInstitutionCode();
        Long l_lngProductId = new Long(l_tradedProductUpdqRow.getProductId());
        String l_strValidUntilBizDate =
            l_tradedProductUpdqRow.getValidUntilBizDate();

        l_qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "institution_code=? and product_id=? and valid_until_biz_date=?",
            new Object[] {
                l_strInstitutionCode,
                l_lngProductId,
                l_strValidUntilBizDate });

        l_qp.doFindAllQuery(
            EqtypeTradedProductUpdqRow.TYPE,
            "product_id = ? AND valid_until_biz_date = ? AND today_dep_fund_reg = ?",
            new Object[] {
                l_lngProductId,
                l_strValidUntilBizDate,
                BooleanEnum.TRUE });

    }

}
@
