head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.21.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackTpCashBalanceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3PreLoaderCallbackTpCashBalanceImplクラス(WEB3PreLoaderCallbackTpCashBalanceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/01 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.preloader.stdimpls;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import webbroker3.preloader.WEB3PreLoaderCallback;
import webbroker3.tradingpower.data.TpCashBalanceDao;
import webbroker3.tradingpower.data.TpCashBalanceRow;


/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3PreLoaderCallbackTpCashBalanceImpl implements
        WEB3PreLoaderCallback
{

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#process(com.fitechlabs.dbind.Row)
     */
    public void process(Row l_row) throws DataException
    {
        
        TpCashBalanceRow l_tpcbRow = (TpCashBalanceRow) l_row;
        
        TpCashBalanceDao.findRowByAccountIdSubAccountId(
                l_tpcbRow.getAccountId(), 
                l_tpcbRow.getSubAccountId());
        
        SubAccountRow l_equitySubAccount = 
            SubAccountDao.findRowByAccountIdSubAccountType(
                l_tpcbRow.getAccountId(), 
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        SubAccountRow l_marginSubAccount = 
            SubAccountDao.findRowByAccountIdSubAccountType(
                l_tpcbRow.getAccountId(), 
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        
        String l_strWhere = null;
        Object[] l_objBindVars = null;
        
        if (l_equitySubAccount != null)
        {
            if (l_marginSubAccount != null)
            {
                l_strWhere = "account_id = ? AND sub_account_id in (?, ?)";
                l_objBindVars = new Object[] {
                        new Long(l_tpcbRow.getAccountId()),
                        new Long(l_equitySubAccount.getSubAccountId()),
                        new Long(l_marginSubAccount.getSubAccountId())
                };
            } else
            {
                l_strWhere = "account_id = ? AND sub_account_id in (?)";
                l_objBindVars = new Object[] {
                        new Long(l_tpcbRow.getAccountId()),
                        new Long(l_equitySubAccount.getSubAccountId()),
                };
            }
        } else
        {
            return;
        }
        
        Processors.getDefaultProcessor().doFindAllQuery(
                TpCashBalanceRow.TYPE, l_strWhere, l_objBindVars);
        
    }

}
@
