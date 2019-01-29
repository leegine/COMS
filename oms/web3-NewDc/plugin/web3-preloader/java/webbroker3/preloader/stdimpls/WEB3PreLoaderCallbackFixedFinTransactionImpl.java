head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.22.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackFixedFinTransactionImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3PreLoaderCallbackFixedFinTransactionImpl�N���X(WEB3PreLoaderCallbackFixedFinTransactionImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/01 �R�c�@@��i(FLJ) �V�K�쐬
                   2005/05/19 �V���@@�h�O(FLJ) ����������ύX
 */
package webbroker3.preloader.stdimpls;

import java.util.Date;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import webbroker3.preloader.WEB3PreLoaderCallback;
import webbroker3.tradingpower.data.FixedFinTransactionRow;


/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3PreLoaderCallbackFixedFinTransactionImpl implements
        WEB3PreLoaderCallback
{
    
    private Date bizDate;
    
    public WEB3PreLoaderCallbackFixedFinTransactionImpl()
    {
        bizDate = GtlUtils.getTradingSystem().getBizDate();
    }

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#process(com.fitechlabs.dbind.Row)
     */
    public void process(Row l_row) throws DataException
    {
        FixedFinTransactionRow l_fftRow = (FixedFinTransactionRow) l_row;
        
        String l_strWhere = 
            "account_id = ? AND sub_account_id = ? AND fin_transaction_categ IN (?, ?) AND delivery_date > ? AND delete_flag = ? ";
        Object[] l_objBindVars = {
                new Long(l_fftRow.getAccountId()),
                new Long(l_fftRow.getSubAccountId()),
                FinTransactionCateg.EQTYPE_CLOSE_MARGIN,
                FinTransactionCateg.EQTYPE_SWAP_MARGIN,
                bizDate,
                BooleanEnum.FALSE
        };
        
        QueryProcessor l_qp = Processors.getDefaultProcessor();
        l_qp.doFindAllQuery(FixedFinTransactionRow.TYPE, l_strWhere, l_objBindVars);


        //�����ڋq�ƐM�p�ڋq�Ō����������قȂ�
        SubAccountRow l_equitySubAccount = 
            SubAccountDao.findRowByAccountIdSubAccountType(
                l_fftRow.getAccountId(), 
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        SubAccountRow l_marginSubAccount = 
            SubAccountDao.findRowByAccountIdSubAccountType(
                l_fftRow.getAccountId(), 
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        
        if (l_equitySubAccount != null)
        {
            if (l_marginSubAccount != null)
            {
                l_strWhere = "account_id = ? AND sub_account_id in (?, ?)"
                    + " AND (delivery_date >= ? AND delivery_date <= ?)"
                    + " AND delete_flag = ?";
                l_objBindVars = new Object[] {
                        new Long(l_fftRow.getAccountId()),
                        new Long(l_equitySubAccount.getSubAccountId()),
                        new Long(l_marginSubAccount.getSubAccountId()),
                        bizDate,
                        bizDate,
                        BooleanEnum.FALSE
                };
            } else
            {
                l_strWhere = "account_id = ? AND sub_account_id = ?"
                    + " AND (delivery_date >= ? AND delivery_date <= ?)"
                    + " AND delete_flag = ?";
                l_objBindVars = new Object[] {
                        new Long(l_fftRow.getAccountId()),
                        new Long(l_equitySubAccount.getSubAccountId()),
                        bizDate,
                        bizDate,
                        BooleanEnum.FALSE
                };
            }
        } else
        {
            return;
        }
        
        l_qp.doFindAllQuery(FixedFinTransactionRow.TYPE, l_strWhere, l_objBindVars);
        
    }

}
@
