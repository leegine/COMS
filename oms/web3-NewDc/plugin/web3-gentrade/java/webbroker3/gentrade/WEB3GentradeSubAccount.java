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
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 補助口座(WEB3GentradeSubAccount)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/01/26 本郷　@千草(SRA) 新規作成
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
 * 信用取引口座、オプション取引口座等、資金を表現する。<BR>
 * xTradeのSubAccountを拡張したクラス。<BR>
 *<BR> 
 * @@author 本郷　@千草(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl
 */
public class WEB3GentradeSubAccount extends SubAccountImpl {

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3GentradeSubAccount.class);
 
    /**
     * 顧客オブジェクト。<BR>
     */
    private MainAccount mainAccount;

    /**
     * 補助口座Row。<BR>
     */
    private SubAccountRow subAccountRow;

    /**
     * コンストラクタ<BR>
     *<BR> 
     * @@param l_mainAccount 顧客オブジェクト
     * @@param l_subAccountRow 補助口座Rowオブジェクト
     */
    public WEB3GentradeSubAccount(MainAccount l_mainAccount, SubAccountRow l_subAccountRow) {
        super(l_mainAccount, l_subAccountRow);
        mainAccount = l_mainAccount;
        subAccountRow = l_subAccountRow;
    }

    /**
     * コンストラクタ。<BR>
     *<BR> 
     * @@param l_lngAccountId 口座ID
     * @@param l_lngSubAccountId 補助口座ID
     * @@throws DataQueryException SQLの実行に失敗した場合
     * @@throws DataNetworkException DBサーバとの接続した場合
     */
    public WEB3GentradeSubAccount(long l_lngAccountId, long l_lngSubAccountId)
        throws DataQueryException, DataNetworkException
    {
        this((WEB3GentradeMainAccount)null, SubAccountDao.findRowByPk(l_lngAccountId, l_lngSubAccountId));
    }

    /**
     * コンストラクタ。<BR>
     *<BR> 
     * @@param l_subAcctRow 補助口座Rowオブジェクト
     */
    public WEB3GentradeSubAccount(SubAccountRow l_subAcctRow)
    {
        this((WEB3GentradeMainAccount)null, l_subAcctRow);
    }

    /**
     * 当オブジェクトに関連する顧客オブジェクトを返す。<BR>
     *<BR> 
     * @@return 顧客オブジェクト
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
     * 引数で与えられた顧客オブジェクトに関連のある補助口座オブジェクトの配列を返す。<BR>
     *<BR> 
     * @@param l_mainAccount 顧客オブジェクト
     * @@return 補助口座オブジェクトの配列
     * @@throws DataQueryException SQLの実行に失敗した場合
     * @@throws DataNetworkException DBサーバとの接続した場合
     * @@throws DataFindException QueryProcessorの取得に失敗した場合
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
     * (get取引店)<BR>
     *<BR> 
     * 指定顧客の取引店である部店オブジェクトを取得する。<BR>
     *<BR> 
     * １）　@getMainAccount()にて顧客オブジェクトを取得する。<BR>
     *<BR> 
     * ２）　@顧客.getBranch()にて顧客の取引店である部店オブジェクトを取得する。<BR>
     *<BR> 
     * ３）　@取得した部店オブジェクトを返却する。<BR>
     * @@return 会社・顧客エンティティ.WEB3GentradeBranch
     */
    public WEB3GentradeBranch getWeb3GenBranch()
    {        
        MainAccount l_mainAccount = getMainAccount();
        return (WEB3GentradeBranch)l_mainAccount.getBranch();
    }

    /**
     * 口座IDを返す。<BR>
     *<BR> 
     * @@return 口座ID
     */
    private long getMainAccountId()
    {
        return subAccountRow.getAccountId();
    }
}
@
