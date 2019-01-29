head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.22.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackAccountProductOrderStopImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3PreLoaderCallbackAccountProductOrderStopImplクラス(WEB3PreLoaderCallbackAccountProductOrderStopImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 山田　@卓司(FLJ) 新規作成
                 : 2005/05/19 山田　@卓司(FLJ) ACCOUNT_PRODUCT_ORDER_STOPテーブルのキャッシュタイプがACCOUNTに変更されたため非推奨とした。
 */
package webbroker3.preloader.stdimpls;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.gentrade.data.AccountProductOrderStopRow;
import webbroker3.preloader.WEB3PreLoaderCallback;


/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 * @@deprecated ACCOUNT_PRODUCT_ORDER_STOPテーブルはACCOUNTタイプになったためプリロードは不要
 */
public class WEB3PreLoaderCallbackAccountProductOrderStopImpl implements WEB3PreLoaderCallback
{

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#process(com.fitechlabs.dbind.Row)
     */
    public void process(Row l_row) throws DataException
    {
        MainAccountRow l_mainAccountRow = (MainAccountRow) l_row;
        String l_strWhere = "institution_code=? and branch_id=? and account_id=?";
        Object[] l_objBindVars = {
                l_mainAccountRow.getInstitutionCode(),
                new Long(l_mainAccountRow.getBranchId()),
                new Long(l_mainAccountRow.getAccountId())
        };
        Processors.getDefaultProcessor().doFindAllQuery(
            AccountProductOrderStopRow.TYPE,
            l_strWhere,
            l_objBindVars);
    }

}
@
