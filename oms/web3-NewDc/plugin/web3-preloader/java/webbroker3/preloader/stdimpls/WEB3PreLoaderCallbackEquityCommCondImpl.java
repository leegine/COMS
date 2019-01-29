head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.22.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackEquityCommCondImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3PreLoaderCallbackEquityCommCondImplクラス(WEB3PreLoaderCallbackEquityCommCondImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.preloader.stdimpls;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;

import webbroker3.gentrade.data.EquityCommCondRow;
import webbroker3.preloader.WEB3PreLoaderCallback;


/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3PreLoaderCallbackEquityCommCondImpl implements
        WEB3PreLoaderCallback
{

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#process(com.fitechlabs.dbind.Row)
     */
    public void process(Row l_row) throws DataException
    {
        EquityCommCondRow l_equityCommCondRow = (EquityCommCondRow) l_row;
        String l_strWhere = "institution_code=? and comm_product_code=? and reg_no=?";
        Object[] l_objBindVars = {
                l_equityCommCondRow.getInstitutionCode(),
                l_equityCommCondRow.getCommProductCode(),
                l_equityCommCondRow.getRegNo()
        };
        Processors.getDefaultProcessor().doFindAllQuery(
                EquityCommCondRow.TYPE,
                l_strWhere,
                l_objBindVars);
    }

}
@
