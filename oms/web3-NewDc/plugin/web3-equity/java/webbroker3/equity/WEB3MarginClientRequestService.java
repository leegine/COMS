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
filename	WEB3MarginClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引クライアントリクエストサービス(WEB3MarginClientRequestService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/27 王暁傑 (Sinocom) 新規作成
                   2005/01/06 岡村和明 (SRA) JavaDoc修正
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;

import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;

/**
 * （信用取引クライアントリクエストサービス）。<BR>
 * <BR>
 * 汎用クライアントリクエストサービス(信用取引用)<BR>
 * <BR>
 * クライアントからのリクエストを実現するサービスの共通スーパークラス。
 * @@version 1.0
 */
public class WEB3MarginClientRequestService extends WEB3ClientRequestService
{
    
    /**
     * @@roseuid 4142BDBE0081
     */
    public WEB3MarginClientRequestService() 
    {
     
    }
    
    /**
     * (get補助口座)<BR>
     * （getSubAccountのオーバーロード ） <BR>
     * <BR>
     * ログインセキュリティサービスより補助口座を取得する。 <BR>
     * <BR>
     * １）　@ログインセキュリティサービスより口座ＩＤを取得し、<BR>
     * 該当する顧客オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@アカウントマネージャ.getSubAccount( )にて、<BR>
     * 該当顧客の株式用補助口座オブジェクトを取得する。<BR>
     * <BR>
     * 　@[getSubAccount引数]<BR>
     * 　@SubAccountTypeEnum.株式取引口座<BR>
     * @@return WEB3GentradeSubAccount<BR>
     * @@throws WEB3BaseException
     * @@roseuid 410F2C880053
     */
    public WEB3GentradeSubAccount getSubAccount() 
        throws WEB3BusinessLayerException, WEB3SystemLayerException
    {
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
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
            try
            {
                return (WEB3GentradeSubAccount)l_accountManager.getSubAccount(l_accountId, SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            catch (NotFoundException l_ne)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                    getClass().getName() + ".getSubAccount()",
                    l_ne.getMessage(),
                    l_ne);
            }
        }
        else
        {
            try
            {
                return (WEB3GentradeSubAccount)super.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            catch (WEB3SystemLayerException l_e)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                    getClass().getName() + ".getSubAccount()",
                    l_e.getMessage(),
                    l_e);
            }
        }
    }
}
@
