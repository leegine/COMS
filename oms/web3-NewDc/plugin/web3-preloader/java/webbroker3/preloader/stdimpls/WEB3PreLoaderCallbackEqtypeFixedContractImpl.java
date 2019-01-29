head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.21.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackEqtypeFixedContractImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3PreLoaderCallbackEqtypeFixedContractImplクラス(WEB3PreLoaderCallbackEqtypeFixedContractImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/01 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.preloader.stdimpls;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;

import webbroker3.preloader.WEB3PreLoaderCallback;
import webbroker3.tradingpower.data.EqtypeFixedContractRow;


/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3PreLoaderCallbackEqtypeFixedContractImpl implements
        WEB3PreLoaderCallback
{

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#process(com.fitechlabs.dbind.Row)
     */
    public void process(Row l_row) throws DataException
    {
        EqtypeFixedContractRow l_efcRow =
            (EqtypeFixedContractRow) l_row;
        String l_strWhere = "account_id = ? AND sub_account_id = ?";
        Object[] l_objBindVars = {
                new Long(l_efcRow.getAccountId()),
                new Long(l_efcRow.getSubAccountId())
        };
        Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeFixedContractRow.TYPE,
                l_strWhere,
                l_objBindVars);
    }

}
@
