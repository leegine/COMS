head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式クライアントリクエストサービス(WEB3FeqClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 呉艶飛(中訊) 新規作成
                   2005/07/26 王亞洲(中訊) レビュー
*/
package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式クライアントリクエストサービス)<BR>
 * 外国株式クライアントリクエストサービスクラス<BR>
 * <BR>
 * @@ author 呉艶飛 <BR>
 * @@ version 1.0<BR>
 */
public class WEB3FeqClientRequestService extends WEB3ClientRequestService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FeqClientRequestService.class);
    
    /**
     * @@roseuid 42CE39ED0000
     */
    public WEB3FeqClientRequestService() 
    {
     
    }
    
    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを取得する。<BR>
     * <BR>
     * this.get口座().getInstitution().getInstitutionCode()を返却する。<BR>
     * @@return String
     * @@roseuid 42971A8601F4
     */
    public String getInstitutionCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInstitutionCode()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getMainAccount().getInstitution().getInstitutionCode();
        
    }
    
    /**
     * (get補助口座)<BR>
     * （getSubAccountのオーバーロード）<BR>
     * <BR>
     * ログインセキュリティサービスより補助口座を取得する。<BR>
     * <BR>
     * １）　@ログインセキュリティサービスより口座ＩＤを取得し、該当する顧客<BR>
     * オブジェクトを取得する。<BR>
     *     ※取得した口座ID == 0の場合、<BR>
     * ThreadLocalSystemAttributeRegistry.getAttribute()より<BR>
     *        口座IDを取得する。<BR>
     *        設定キー： ACCOUNT_ID<BR>
     * <BR>
     * ２）　@外国株式クライアントリクエストサービス.get補助口座()に<BR>
     * 委譲（deligate）する。<BR>
     * <BR>
     * 　@[get補助口座()に指定する引数]<BR>
     * 　@顧客：　@取得した顧客オブジェクト<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount
     * @@throws WEB3BaseException 
     * @@roseuid 428AF2AA0018
     */
    public SubAccount getSubAccount() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);
        //ログインセキュリティサービスより補助口座を取得する
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
            OpLoginSecurityService.class);
        //１）　@ログインセキュリティサービスより口座ＩＤを取得し、該当する顧客オブジェクトを取得する。
        long l_lngAccountId = l_opLoginSec.getAccountId();
        if (l_lngAccountId == 0L)
        {
            Long l_longaccountId =
                (Long)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3FeqLocalSystemAttributesDef.ACCOUNT_ID);
            if (l_longaccountId != null)
            {
                l_lngAccountId = l_longaccountId.longValue();
            }
        }
        //２）　@外国株式クライアントリクエストサービス.get補助口座()に委譲（deligate）する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        SubAccount l_subAccount = WEB3FeqClientRequestService.getSubAccount(l_mainAccount);
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
    
    /**
     * (get補助口座)<BR>
     * （staticメソッド）<BR>
     * <BR>
     * １）　@当該客が信用客かどうか判定する。<BR>
     * <BR>
     *    顧客.is信用口座開設("0：指定なし")==trueの場合は、信用客である。<BR>
     *    以外、非信用客である。<BR>
     * <BR>
     * ２）　@アカウントマネージャ.getSubAccount( )にて、該当顧客の補助口座オブジェクトを取得する。<BR>
     * <BR>
     *   [getSubAccount()に指定する引数]<BR>
     * 　@accountId：　@顧客.getAccountId()<BR>
     * 　@subAccountType：　@<BR>
     *    －信用客の場合⇒ SubAccountTypeEnum.信用取引口座<BR>
     * 　@　@　@（EQUITY_MARGIN_SUB_ACCOUNT）<BR>
     *    －非信用客の場合⇒ SubAccountTypeEnum.株式取引口座<BR>
     * 　@　@　@（EQUITY_SUB_ACCOUNT）<BR>
     * @@param l_account - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return WEB3GentradeSubAccount
     * @@throws WEB3BaseException 
     * @@roseuid 42B6744001C5
     */
    public static WEB3GentradeSubAccount getSubAccount(WEB3GentradeMainAccount l_account) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getSubAccount(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_account == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3FeqClientRequestService" + "." + STR_METHOD_NAME);
        }
        
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_account;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeSubAccount  l_subAccount = null;
        try
        {
            //顧客.is信用口座開設("0：指定なし")==trueの場合は、信用客である。
            if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                //２）　@アカウントマネージャ.getSubAccount( )にて、該当顧客の補助口座オブジェクトを取得する。
                l_subAccount = 
                    (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                        l_mainAccount.getAccountId(), SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            //以外、非信用客である。
            else
            {
                l_subAccount = 
                    (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                        l_mainAccount.getAccountId(), SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                "WEB3FeqClientRequestService" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
}
@
