head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.22.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackEqtypeFixedContractImpl2.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3PreLoaderCallbackEqtypeFixedContractImpl2�N���X(WEB3PreLoaderCallbackEqtypeFixedContractImpl2.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/15 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.preloader.stdimpls;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import webbroker3.preloader.WEB3PreLoaderCallback;
import webbroker3.tradingpower.data.EqtypeFixedContractRow;


/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3PreLoaderCallbackEqtypeFixedContractImpl2 implements
        WEB3PreLoaderCallback
{

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#process(com.fitechlabs.dbind.Row)
     */
    public void process(Row l_row) throws DataException
    {
        
        MainAccountRow l_mainAccount = (MainAccountRow) l_row;
        
        // ���������⏕�������擾
        SubAccountRow l_equitySubAccount = 
            SubAccountDao.findRowByAccountIdSubAccountType(
                    l_mainAccount.getAccountId(), 
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
    
        // �����M�p�⏕�������擾
        SubAccountRow l_marginSubAccount =
            SubAccountDao.findRowByAccountIdSubAccountType(
                    l_mainAccount.getAccountId(), 
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        
        // ���������⏕�����Ń��[�h
        if (l_equitySubAccount != null)
        {
            findRowsByAccountIdSubAccountId(l_equitySubAccount);
        }

        // �����M�p�⏕�����Ń��[�h
        if (l_marginSubAccount != null)
        {
            findRowsByAccountIdSubAccountId(l_marginSubAccount);
        }

    }
    
    public void findRowsByAccountIdSubAccountId(SubAccountRow l_subAccount) throws DataException
    {
        final String l_strWhere = "account_id = ? AND sub_account_id = ? ";
        Processors.getDefaultProcessor().doFindAllQuery(
            EqtypeFixedContractRow.TYPE,
            l_strWhere,
            new Object[] {
                    new Long(l_subAccount.getAccountId()),
                    new Long(l_subAccount.getSubAccountId())
            });
            
    }

}
@
