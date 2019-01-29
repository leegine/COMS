head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.22.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackLoginAccountManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3LoginAccountManagerCallback�N���X(WEB3LoginAccountManagerCallback.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/24 �R�c�@@��i (FLJ) �V�K�쐬
 */
package webbroker3.preloader.stdimpls;

import webbroker3.preloader.WEB3PreLoaderCallback;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAccountManagerRow;

/**
 * 
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3PreLoaderCallbackLoginAccountManagerImpl
    implements WEB3PreLoaderCallback
{

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#load(com.fitechlabs.dbind.Row)
     */
    public void process(Row l_row) throws DataException
    {
        LoginAccountManagerRow l_loginAccountManagerRow =
            (LoginAccountManagerRow) l_row;
        String l_strWhere = "account_id=?";
        Object[] l_objBindVars =
            { new Long(l_loginAccountManagerRow.getAccountId())};
        Processors.getDefaultProcessor().doFindAllQuery(
            LoginAccountManagerRow.TYPE,
            l_strWhere,
            l_objBindVars);
    }

}
@
