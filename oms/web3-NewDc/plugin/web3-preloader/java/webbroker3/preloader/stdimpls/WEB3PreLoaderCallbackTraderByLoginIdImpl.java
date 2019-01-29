head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.21.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackTraderByLoginIdImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3PreLoaderCallbackTraderByLoginIdImplクラス(WEB3PreLoaderCallbackTraderByLoginIdImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/25 山田　@卓司 (FLJ) 新規作成
 */
package webbroker3.preloader.stdimpls;

import webbroker3.preloader.WEB3PreLoaderCallback;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

/**
 * 
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3PreLoaderCallbackTraderByLoginIdImpl implements WEB3PreLoaderCallback
{

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#process(com.fitechlabs.dbind.Row)
     */
    public void process(Row l_row) throws DataException
    {
    	LoginRow l_loginRow = (LoginRow) l_row;
    	String l_strWhere = "login_id = ?";
    	Object[] l_objBindVars = { new Long(l_loginRow.getLoginId()) };
    	Processors.getDefaultProcessor().doFindAllQuery(
    	    TraderRow.TYPE,
    	    l_strWhere,
    	    l_objBindVars);
    }

}
@
