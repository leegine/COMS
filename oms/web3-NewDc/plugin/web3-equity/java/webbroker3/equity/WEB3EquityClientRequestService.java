head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式クライアントリクエストサービス(WEB3EquityClientRequestService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/27   王暁傑 (Sinocom) 新規作成 
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;

/**
 * （株式クライアントリクエストサービス）。<BR>
 * <BR>
 * クライアントからのリクエストを実現するサービスの共通スーパークラス。
 * @@version 1.0
 */
public class WEB3EquityClientRequestService extends WEB3ClientRequestService
{
    
    /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3EquityClientRequestService.class);
        
    /**
     * @@roseuid 4142BDBE0013
     */
    public WEB3EquityClientRequestService() 
    {
     
    }
    
    /**
     * (get補助口座)<BR>
     * <BR>
     * （getSubAccountのオーバーロード） <BR>
     * <BR>
     * ログインセキュリティサービスより補助口座を取得する。 <BR>
     * <BR>
     * １）　@ログインセキュリティサービスより口座ＩＤを取得し、該当する顧客オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@当該客が信用客かどうか判定する。<BR>
     * <BR>
     * 　@　@　@顧客.is信用口座開設("0：指定なし")==trueの場合は信用客である。<BR>
     * 　@　@　@以外、非信用客である。<BR>
     * <BR>
     * ３）　@拡張アカウントマネージャ.getSubAccount( )にて、該当顧客の補助口座オブジェクトを取得する。<BR>
     * <BR>
     * 　@[getSubAccount引数]<BR>
     * 　@信用客の場合：　@SubAccountTypeEnum.信用取引口座（EQUITY_MARGIN_SUB_ACCOUNT）<BR>
     * 　@非信用客の場合：　@SubAccountTypeEnum.株式取引口座（EQUITY_SUB_ACCOUNT）<BR>
     * <BR>
     * @@return WEB3GentradeSubAccount<BR>
     * @@throws WEB3BaseException
     * @@roseuid 410F2C880053
     */
    public WEB3GentradeSubAccount getSubAccount() 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeSubAccount l_subAccount = null;

        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        long l_accountId = l_opLoginSec.getAccountId();
        if (l_accountId == 0L)
        {
            Long l_longAccountId = (Long)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3MarginAttributeNameDef.ACCOUNT_ID);
            if (l_longAccountId != null)
            {
	            l_accountId = l_longAccountId.longValue();
            }
        }
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        try
        {
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_accountId);
            SubAccountTypeEnum l_subAccountType;
            if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                l_subAccountType = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
            }
            else
            {
                l_subAccountType = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(l_accountId, l_subAccountType);
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }

        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
}
@
