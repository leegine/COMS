head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeSubAccount.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �⏕����(WEB3GentradeSubAccount)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/01/26 �{���@@�瑐(SRA) �V�K�쐬
*/
package webbroker3.gentrade;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * �M�p��������A�I�v�V��������������A������\������B<BR>
 * xTrade��SubAccount���g�������N���X�B<BR>
 *<BR> 
 * @@author �{���@@�瑐(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl
 */
public class WEB3GentradeSubAccount extends SubAccountImpl {

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3GentradeSubAccount.class);
 
    /**
     * �ڋq�I�u�W�F�N�g�B<BR>
     */
    private MainAccount mainAccount;

    /**
     * �⏕����Row�B<BR>
     */
    private SubAccountRow subAccountRow;

    /**
     * �R���X�g���N�^<BR>
     *<BR> 
     * @@param l_mainAccount �ڋq�I�u�W�F�N�g
     * @@param l_subAccountRow �⏕����Row�I�u�W�F�N�g
     */
    public WEB3GentradeSubAccount(MainAccount l_mainAccount, SubAccountRow l_subAccountRow) {
        super(l_mainAccount, l_subAccountRow);
        mainAccount = l_mainAccount;
        subAccountRow = l_subAccountRow;
    }

    /**
     * �R���X�g���N�^�B<BR>
     *<BR> 
     * @@param l_lngAccountId ����ID
     * @@param l_lngSubAccountId �⏕����ID
     * @@throws DataQueryException SQL�̎��s�Ɏ��s�����ꍇ
     * @@throws DataNetworkException DB�T�[�o�Ƃ̐ڑ������ꍇ
     */
    public WEB3GentradeSubAccount(long l_lngAccountId, long l_lngSubAccountId)
        throws DataQueryException, DataNetworkException
    {
        this((WEB3GentradeMainAccount)null, SubAccountDao.findRowByPk(l_lngAccountId, l_lngSubAccountId));
    }

    /**
     * �R���X�g���N�^�B<BR>
     *<BR> 
     * @@param l_subAcctRow �⏕����Row�I�u�W�F�N�g
     */
    public WEB3GentradeSubAccount(SubAccountRow l_subAcctRow)
    {
        this((WEB3GentradeMainAccount)null, l_subAcctRow);
    }

    /**
     * ���I�u�W�F�N�g�Ɋ֘A����ڋq�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR> 
     * @@return �ڋq�I�u�W�F�N�g
     */
    public MainAccount getMainAccount()
    {
        final String STR_METHOD_NAME = "getMainAccount()";
        
        if (mainAccount == null)
        {
            try
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                AccountManager l_accountMgr = l_finApp.getAccountManager();
                mainAccount = l_accountMgr.getMainAccount(getMainAccountId());
            }
            catch (NotFoundException nfe)
            {
                log.error("MainAccount could not be obtained for sub account id: " + getSubAccountId(), nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            }
        }
        return mainAccount;
    }

    /**
     * �����ŗ^����ꂽ�ڋq�I�u�W�F�N�g�Ɋ֘A�̂���⏕�����I�u�W�F�N�g�̔z���Ԃ��B<BR>
     *<BR> 
     * @@param l_mainAccount �ڋq�I�u�W�F�N�g
     * @@return �⏕�����I�u�W�F�N�g�̔z��
     * @@throws DataQueryException SQL�̎��s�Ɏ��s�����ꍇ
     * @@throws DataNetworkException DB�T�[�o�Ƃ̐ڑ������ꍇ
     * @@throws DataFindException QueryProcessor�̎擾�Ɏ��s�����ꍇ
     */
    public static SubAccount[] getSubAccounts(MainAccount l_mainAccount)
        throws DataQueryException, DataNetworkException, DataFindException
    {        
        QueryProcessor l_qp = Processors.getDefaultProcessor();
        List l_lisRows = l_qp.doFindAllQuery(
                SubAccountRow.TYPE,
                "account_id=?",
                new Object[] { new Long(l_mainAccount.getAccountId()) });
  
        int l_intSize = l_lisRows.size();
        SubAccount l_subAccounts[] = new SubAccount[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_subAccounts[i] = new WEB3GentradeSubAccount(l_mainAccount, (SubAccountRow)l_lisRows.get(i));
        }

        return l_subAccounts;
    }

    /**
     * (get����X)<BR>
     *<BR> 
     * �w��ڋq�̎���X�ł��镔�X�I�u�W�F�N�g���擾����B<BR>
     *<BR> 
     * �P�j�@@getMainAccount()�ɂČڋq�I�u�W�F�N�g���擾����B<BR>
     *<BR> 
     * �Q�j�@@�ڋq.getBranch()�ɂČڋq�̎���X�ł��镔�X�I�u�W�F�N�g���擾����B<BR>
     *<BR> 
     * �R�j�@@�擾�������X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return ��ЁE�ڋq�G���e�B�e�B.WEB3GentradeBranch
     */
    public WEB3GentradeBranch getWeb3GenBranch()
    {        
        MainAccount l_mainAccount = getMainAccount();
        return (WEB3GentradeBranch)l_mainAccount.getBranch();
    }

    /**
     * ����ID��Ԃ��B<BR>
     *<BR> 
     * @@return ����ID
     */
    private long getMainAccountId()
    {
        return subAccountRow.getAccountId();
    }
}
@
