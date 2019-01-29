head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.21.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackLoginUsernameImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3LoginUserNameCallbackクラス(WEB3LoginUserNameCallback.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/24 山田　@卓司 (FLJ) 新規作成
 */
package webbroker3.preloader.stdimpls;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameRow;

import webbroker3.preloader.WEB3DefaultPreLoaderCallback;

/**
 * 
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3PreLoaderCallbackLoginUsernameImpl extends WEB3DefaultPreLoaderCallback
{

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#load(com.fitechlabs.dbind.Row)
     */
    public void load(Row l_row) throws DataException
    {
    	// ログインIDによる検索
        LoginUsernameRow l_loginUsernameRow = (LoginUsernameRow) l_row;
        LoginUsernameDao.findRowsByLoginId(l_loginUsernameRow.getLoginId()); 
    }

}
@
