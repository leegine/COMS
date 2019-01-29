head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OPクライアントリクエストサービス(WEB3OptionClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 張宝楠 (中訊) 新規作成
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
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (OPクライアントリクエストサービス)<BR>
 * OPクライアントリクエストサービスクラス<BR>
 * 汎用クライアントリクエストサービス（オプション用）<BR>
 * クライアントからのリクエストを実現するサービスの共通スーパークラス。<BR>
 * @@author  張宝楠
 * @@version 1.0
 */
public class WEB3OptionClientRequestService extends WEB3ClientRequestService
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionClientRequestService.class);
            
    /**
     * （get補助口座のオーバーライド）<BR>
     * <BR>
     * ログインセキュリティサービスより補助顧客を取得する。<BR>
     * <BR>
     * １）ログインセキュリティサービスより口座ＩＤを取得する。<BR>
     * <BR>
     * ２）口座IDから、顧客オブジェクト（口座オブジェクト）を取得する。<BR>
     * 
     * ３）顧客.getOP取引口座タイプ()により該当顧客の補助口座タイプを取得する。<BR>
     * 
     * ４）アカウントマネージャ.getSubAccount(補助口座タイプ)にて、該当顧客の指数オプション取引用補助口座オブジェクトを取得する。<BR>
     * <BR>
     * 　@[getSubAccount引数]<BR>
     * 　@SubAccountTypeEnum.指数オプション取引口座<BR>
     * 
     * @@return 指数オプション取引用補助口座オブジェクトを取得する。
     * @@roseuid 404EE42800C3
     */
    public SubAccount getSubAccount() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);
        //１）ログインセキュリティサービスより口座ＩＤを取得する。
        OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        //１）ログインセキュリティサービスより口座ＩＤを取得する。        
        long l_lngAccountId = l_opLoginSec.getAccountId();
        if (l_lngAccountId == 0L)
        {
            Long l_longaccountId =
                (Long)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.ACCOUNT_ID);
            if (l_longaccountId != null)
            {
                l_lngAccountId = l_longaccountId.longValue();
            }
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        SubAccount  l_subAccount = null;
        try
        {
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
            //３）顧客.getOP取引口座タイプ()により該当顧客の補助口座タイプを取得する。
            SubAccountTypeEnum l_subAccountType = l_mainAccount.getOpSubAccountType();
            //４）アカウントマネージャ.getSubAccount(補助口座タイプ)にて、該当顧客の指数オプション取引用補助口座オブジェクトを取得する。
            l_subAccount = l_accountManager.getSubAccount(l_lngAccountId,l_subAccountType);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);        
        return l_subAccount;
    }

    /**
     * （execute）<BR>
     * <BR> 
     * @@param l_request 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        return l_request.createResponse();
    }
}
@
