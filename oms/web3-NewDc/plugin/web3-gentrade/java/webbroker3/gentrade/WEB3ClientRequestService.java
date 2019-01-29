head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : クライアント・リクエスト・サービス(WEB3ClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 洪華 (中訊) 修正
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (クライアント・リクエスト・サービス)<BR>
 * <BR> 
 * 汎用クライアントリクエストサービス<BR> 
 * クライアントからのリクエストを実現するサービスの共通スーパークラス。<BR>
 * <BR> 
 */
public abstract class WEB3ClientRequestService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ClientRequestService.class);

    /**
     * ログインチャネルのタグ名
     * @@roseuid 403496D10132
     */
    public WEB3ClientRequestService()
    {

    }

    /**
     * (get口座)<BR>
     * <BR> 
     * ログインセキュリティサービスより顧客を取得する。<BR>
     * <BR> 
     * @@return WEB3GentradeMainAccount 口座
     * @@throws WEB3SystemLayerException
     * @@roseuid 403496D10140
     */
    public MainAccount getMainAccount() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMainAccount()";
        log.entering(STR_METHOD_NAME);

        long l_accountId;
        MainAccount l_mainAccount;

        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        l_accountId = l_opLoginSec.getAccountId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        try
        {
            l_mainAccount = l_accMgr.getMainAccount(l_accountId);
        }
        catch (NotFoundException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_mainAccount;
    }

    /**
     * (get補助口座)<BR>
     * <BR> 
     * ログインセキュリティサービスより補助顧客を取得する。<BR>
     * @@param l_subAccountType
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount
     * @@throws webbroker3.common.WEB3SystemLayerException
     * @@roseuid 403496D101AE
     */
    public SubAccount getSubAccount(SubAccountTypeEnum l_subAccountType)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getSubAccount(SubAccountTypeEnum)";
        log.entering(STR_METHOD_NAME);

        long l_accountId;
        MainAccount l_mainAccount;
        SubAccount l_subAccount;

        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        l_accountId = l_opLoginSec.getAccountId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        
        try
        {
            l_mainAccount = l_accMgr.getMainAccount(l_accountId);
            l_subAccount = l_mainAccount.getSubAccount(l_subAccountType);
        }
        catch (NotFoundException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }

    /**
     * (get代理入力者)<BR>
     * <BR> 
     * ログインセキュリティサービスより代理入力者を取得する。<BR>
     * 顧客入力の場合はnullを返却する。<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Trader
     * @@throws webbroker3.common.WEB3SystemLayerException
     * @@roseuid 403496D1022C
     */
    public Trader getTrader() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTrader()";
        log.entering(STR_METHOD_NAME);

        long l_loginId;
        Trader l_trader = null;

        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        l_loginId = l_opLoginSec.getLoginInfo().getLoginId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();

        try
        {
            l_trader = l_finObjMgr.getTraderByLoginId(l_loginId);
        }
        catch (NotFoundException e)
        {
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_trader;
    }

    /**
     * (getログインチャネル)<BR>
     * <BR> 
     * ログインセキュリティサービスよりログインチャネルを取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 403496D10298
     */
    public String getLoginChannel()
    {
        final String STR_METHOD_NAME = "getLoginChannel()";
        log.entering(STR_METHOD_NAME);
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_strChannel =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);
        if (l_strChannel == null || l_strChannel.equals(""))
        {
            l_strChannel = WEB3ChannelDef.BRANCH;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strChannel;
    }

}
@
