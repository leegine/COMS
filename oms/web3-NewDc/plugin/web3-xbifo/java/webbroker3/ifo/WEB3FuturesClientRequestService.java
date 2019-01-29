head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           :先物クライアントリクエストサービス(WEB3FuturesClientRequestService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20   王暁傑 (Sinocom) 新規作成
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;


/**
 * (先物クライアントリクエストサービス)<BR>
 */
public class WEB3FuturesClientRequestService extends WEB3ClientRequestService
{
    
    /**
     * @@roseuid 40F7A03800AB
     */
    public WEB3FuturesClientRequestService() 
    {
     
    }
    
    /**
     * (get補助口座)<BR>
     * （getSubAccountのオーバーライド）<BR>
     * <BR>
     * ログインセキュリティサービスより補助口座を取得する。<BR>
     * <BR>
     * １）　@ログインセキュリティサービスより口座ＩＤを取得する。<BR>
     * <BR>
     * ２）　@アカウントマネージャ.getSubAccount( )にて、<BR>
     * 該当顧客の指数先物取引用補助口座オブジェクトを取得する。<BR>
     * <BR>
     * 　@[getSubAccount引数]<BR>
     * 　@SubAccountTypeEnum.株式オプション取引口座(先物証拠金)(=7)<BR>
     * <BR>
     * ※先物の取引では、上記のEnumが設定される補助口座を使用する。<BR>
     * <BR>
     * ３）　@２）で例外がスローされた場合、「先物口座未開設エラー」をスローする。<BR>
     * @@return SubAccount
     * @@roseuid 404EE5420094
     */
    public SubAccount getSubAccount() throws WEB3BaseException
    {
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            long l_accountId = l_opLoginSec.getAccountId();

        if (l_accountId == 0L)
        {
            Long l_longAccountId = (Long)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.ACCOUNT_ID);
            if (l_longAccountId != null)
            {
                l_accountId = l_longAccountId.longValue();
            }
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            try
            {
                return (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_accountId,
                    SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "getSubAccount()",
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        else
        {
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount = super.getSubAccount(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            }
            catch(WEB3SystemLayerException l_ex)
            {
                //先物口座未開設エラーをスローする
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00284,
                    this.getClass().getName());
            }

            return l_subAccount;
        }

    }
}
@
