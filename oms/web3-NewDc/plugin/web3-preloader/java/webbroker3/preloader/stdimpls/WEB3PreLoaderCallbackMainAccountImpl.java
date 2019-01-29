head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.22.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackMainAccountImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3PreLoaderCallbackMainAccountImplクラス(WEB3PreLoaderCallbackMainAccountImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.preloader.stdimpls;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.preloader.WEB3DefaultPreLoaderCallback;


/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3PreLoaderCallbackMainAccountImpl extends
        WEB3DefaultPreLoaderCallback
{

    /**
     * @@see webbroker3.preloader.WEB3DefaultPreLoaderCallback#load(com.fitechlabs.dbind.Row)
     */
    protected void load(Row l_row) throws DataException
    {
        MainAccountRow l_mainAccountRow = (MainAccountRow) l_row;
        BatchedQuery[] l_queries = {
                createFindByInstitutionCodeBranchCodeAccountCodeQuery(l_mainAccountRow),
                createFindByInstitutionIdBranchCodeAccountCodeQuery(l_mainAccountRow)
        };
        Processors.getDefaultProcessor().doQueries(l_queries);
    }
    
    private BatchedQuery createFindByInstitutionCodeBranchCodeAccountCodeQuery(
            MainAccountRow l_mainAccountRow)
    {
        String l_strWhere = 
            "institution_code = ? and branch_code = ? and account_code = ?";
        Object[] l_objBindVars = {
                l_mainAccountRow.getInstitutionCode(),
                l_mainAccountRow.getBranchCode(),
                l_mainAccountRow.getAccountCode()
        };
        return BatchedQuery.createFindAllQuery(
                MainAccountRow.TYPE,
                l_strWhere,
                l_objBindVars);
    }
    
    private BatchedQuery createFindByInstitutionIdBranchCodeAccountCodeQuery(
            MainAccountRow l_mainAccountRow)
    {
        String l_strWhere = 
            "institution_id=? and branch_code=? and account_code=?";
        Object[] l_objBindVars = {
                new Long(l_mainAccountRow.getInstitutionId()),
                l_mainAccountRow.getBranchCode(),
                l_mainAccountRow.getAccountCode()
        };
        return BatchedQuery.createFindAllQuery(
                MainAccountRow.TYPE,
                l_strWhere,
                l_objBindVars);
    }

}
@
