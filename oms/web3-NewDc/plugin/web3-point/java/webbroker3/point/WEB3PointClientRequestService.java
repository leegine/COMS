head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.01.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイントクライアントリクエストサービス(WEB3PointClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (ポイントクライアントリクエストサービス)<BR>
 * ポイントクライアントリクエストサービスクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointClientRequestService extends WEB3ClientRequestService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3PointClientRequestService.class);
    
    /**
     * (get補助口座)<BR>
     * 補助口座オブジェクトを取得する。 <BR>
     * <BR>
     * super.get補助口座()にて補助口座オブジェクトを取得する。 <BR>
     * <BR>
     * [super.get補助口座()に指定する引数] <BR>
     * SubAccountTypeEnum.EQUITY_SUB_ACCOUNT<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount
     * @@roseuid 418F43730108
     */
    public SubAccount getSubAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSubAccount()";
        log.entering(STR_METHOD_NAME);

        SubAccount l_subAccount = super.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
    
    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを取得する。<BR>
     * <BR>
     * １）補助口座オブジェクトの取得<BR>
     * <BR>
     *    this.get補助口座()をコールする。<BR>
     * <BR>
     * ２）補助口座.getInstitution().getInstitutionCode()の戻り値を返却する。<BR>
     * @@return String
     * @@roseuid 41A43C50021B
     */
    public String getInstitutionCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstitutionCode()";
        log.entering(STR_METHOD_NAME);

        //１）補助口座オブジェクトの取得
        SubAccount l_subAccount = this.getSubAccount();
        
        //２）補助口座.getInstitution().getInstitutionCode()の戻り値を返却する。
        if (l_subAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        Institution institution = l_subAccount.getInstitution();
        if (institution == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        String l_strInstitutionCode = institution.getInstitutionCode();

        log.exiting(STR_METHOD_NAME);
        return l_strInstitutionCode;
    }
    
    /**
     * (get部店コード)<BR>
     * 部店コードを取得する。<BR>
     * <BR>
     * １）補助口座オブジェクトの取得<BR>
     * <BR>
     *    this.get補助口座()をコールする。<BR>
     * <BR>
     * ２）補助口座.get取引店().getBranchCode()の戻り値を返却する。<BR>
     * @@return String
     * @@roseuid 41A43CCF0017
     */
    public String getBranchCode() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getBranchCode()";
        log.entering(STR_METHOD_NAME);

        // １）補助口座オブジェクトの取得
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
        
        // ２）補助口座.get取引店().getBranchCode()の戻り値を返却する。
        if (l_subAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        if (l_branch == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        String l_strBranchCode = l_branch.getBranchCode();

        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }
    
    /**
     * (get顧客コード)<BR>
     * 顧客コードを取得する。<BR>
     * <BR>
     * １）補助口座オブジェクトの取得<BR>
     * <BR>
     *    this.get補助口座()をコールする。<BR>
     * <BR>
     * ２）補助口座.getMainAccount().getAccountCode()の戻り値を返却する。<BR>
     * @@return String
     * @@roseuid 41A43CD6017F
     */
    public String getAccountCode() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getAccountCode()";
        log.entering(STR_METHOD_NAME);

        // １）補助口座オブジェクトの取得
        SubAccount l_subAccount = super.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        log.exiting(STR_METHOD_NAME);
        //２）補助口座.getMainAccount().getAccountCode()の戻り値を返却する。
        if (l_subAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        if (l_mainAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        String l_strAccountCode = l_mainAccount.getAccountCode();

        log.exiting(STR_METHOD_NAME);
        return l_strAccountCode;
    }
}
@
