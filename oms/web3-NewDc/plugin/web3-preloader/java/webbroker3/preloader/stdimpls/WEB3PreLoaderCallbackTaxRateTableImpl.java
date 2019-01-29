head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.22.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackTaxRateTableImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3PreLoaderCallbackTaxRateTableImplクラス(WEB3PreLoaderCallbackTaxRateTableImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/01 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.preloader.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.gentrade.data.TaxRateTableRow;
import webbroker3.preloader.WEB3PreLoaderCallback;


/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3PreLoaderCallbackTaxRateTableImpl implements
        WEB3PreLoaderCallback
{
    
    private Timestamp bizDate;
    
    public WEB3PreLoaderCallbackTaxRateTableImpl()
    {
        bizDate = new Timestamp(
            GtlUtils.getTradingSystem().getBizDate().getTime());
    }

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#process(com.fitechlabs.dbind.Row)
     */
    public void process(Row l_row) throws DataException
    {
        TaxRateTableRow l_trtRow = (TaxRateTableRow) l_row;
        
        Object[] l_objBindVars1 = { 
                l_trtRow.getInstitutionCode(), 
                l_trtRow.getTaxType(), 
                bizDate, 
                bizDate };
        
        Object[] l_objBindVars2 = { 
                l_trtRow.getTaxType(), 
                bizDate, 
                bizDate };
        
        QueryProcessor l_qp = Processors.getDefaultProcessor();
        l_qp.doFindAllQuery(
            TaxRateTableRow.TYPE, 
            "institution_code=? and tax_type=? and (appli_start_date<=? and appli_end_date>=?)", 
            l_objBindVars1);
        
        l_qp.doFindAllQuery(
            TaxRateTableRow.TYPE, 
            "tax_type=? and (appli_start_date<=? and appli_end_date>=?)", 
            l_objBindVars2);
    }

}
@
