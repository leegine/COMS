head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsAccountsManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ルールエンジン用アカウントマネージャー実装(WEB3RlsAccountsManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/17 FLJ齋藤　@新規作成
*/
package webbroker3.rlsgateway.connector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.fin.intellioms.account.AccountException;
import com.fitechlabs.fin.intellioms.account.AccountIDRange;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.rlsgateway.data.RlsAccountRow;


/**
 *
 * ルールエンジン用アカウントマネージャー実装
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3RlsAccountsManagerImpl implements WEB3RlsAccountsManager
{

    /**
     * 
     */
    public WEB3RlsAccountsManagerImpl()
    {
        super();
    }

    /**
     * @@see com.fitechlabs.fin.intellioms.account.AccountsManager#getAllAccountIds()
     */
    public List getAllAccountIds() throws AccountException
    {
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_list = l_qp.doFindAllQuery(RlsAccountRow.TYPE, null, "id", null, null);
            
            List retList = new ArrayList();
            
            Iterator l_iter = l_list.iterator();
            while(l_iter.hasNext())
            {
                RlsAccountRow l_row = (RlsAccountRow)l_iter.next();
                retList.add(new Long(l_row.getId()));
            }

            return retList;
        }
        catch (DataException e)
        {
            throw new AccountException(e.getMessage(), e);
        }
    }

    /**
     * @@see com.fitechlabs.fin.intellioms.account.AccountsManager#getAccountsCount()
     */
    public AccountIDRange getAccountsCount() throws AccountException
    {
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_list = l_qp.doFindAllQuery(RlsAccountRow.TYPE, null, "id", null, null);
            
            int maxIndex = 0;
            if(l_list.size() > maxIndex)
            {
                maxIndex = l_list.size() - 1;
            }
            else
            {
                throw new AccountException("no data found from rls_account");
            }
            
            RlsAccountRow l_min = (RlsAccountRow)l_list.get(0);
            RlsAccountRow l_max = (RlsAccountRow)l_list.get(maxIndex);
            
            return new AccountIDRange(l_min.getId(), l_max.getId());
        }
        catch (DataException e)
        {
            throw new AccountException(e.getMessage(), e);
        }
    }

}
@
