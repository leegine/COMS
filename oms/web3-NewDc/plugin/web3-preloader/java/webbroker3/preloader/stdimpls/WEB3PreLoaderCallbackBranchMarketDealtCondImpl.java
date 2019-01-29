head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.22.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackBranchMarketDealtCondImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3PreLoaderCallbackBranchMarketDealtCondImplクラス(WEB3PreLoaderCallbackBranchMarketDealtCondImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/26 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.preloader.stdimpls;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;

import webbroker3.gentrade.data.BranchMarketDealtCondRow;
import webbroker3.preloader.WEB3DefaultPreLoaderCallback;

/**
 * 
 * 
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3PreLoaderCallbackBranchMarketDealtCondImpl extends
        WEB3DefaultPreLoaderCallback
{

    /**
     * @@see webbroker3.preloader.WEB3DefaultPreLoaderCallback#load(com.fitechlabs.dbind.Row)
     */
    protected void load(Row l_row) throws DataException
    {
        BranchMarketDealtCondRow l_branchMarketDealtCondRow = 
            (BranchMarketDealtCondRow) l_row;
        String l_strInstitutionCode = 
            l_branchMarketDealtCondRow.getInstitutionCode();
        String l_strBranchCode = l_branchMarketDealtCondRow.getBranchCode();
        Processors.getDefaultProcessor().doFindAllQuery(
                BranchMarketDealtCondRow.TYPE,
                " institution_code = ?  and  branch_code = ? ",
                " to_number(market_code) ",
                null,
                new Object[] { l_strInstitutionCode, l_strBranchCode });
    }

}@
