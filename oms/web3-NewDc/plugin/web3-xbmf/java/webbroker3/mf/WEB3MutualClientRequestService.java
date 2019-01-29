head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信クライアントリクエストサービス(WEB3MutualClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 王蘭芬(中訊) 新規作成
                   2004/12/10 黄建(中訊) 残対応
*/

package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (投信クライアントリクエストサービス)<BR>
 * 汎用クライアントリクエストサービス（投資信託用）<BR>
 * クライアントからのリクエストを実現するサービスの共通スーパークラス<BR>
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3MutualClientRequestService extends WEB3ClientRequestService 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualClientRequestService.class);
    
    /**
     * (get補助口座)<BR>
     * (getSubAccountのオーバーライド)<BR>
     * <BR>
     * ログインセキュリティサービスより補助顧客を取得する。 <BR>
     * <BR>
     * １）　@super.get補助口座( )にて、<BR>
     * 該当顧客の投資信託取引用補助口座オブジェクトを取得する。 <BR>
     * <BR>
     * 　@[get補助口座に渡すパラメタ] <BR>
     *   (*)以下の手順で設定。  <BR>
     *   －ログインセキュリティサービスより口座IDを取得。  <BR>
     *   －取得した口座IDを引数に、拡張アカウントマネージャ.get顧客()をコール。 <BR> 
     *   －取得した顧客オブジェクト.is信用口座開設()をコール。  <BR>
     *   ・is信用口座開設=true の場合、以下を指定。  <BR>
     *   　@　@SubAccountTypeEnum.株式信用取引口座（保証金）  <BR>
     *   ・is信用口座開設=false の場合、以下を指定。  <BR>
     *   　@　@SubAccountTypeEnum.株式取引口座（預り金）  <BR>
     *    <BR>
     *   ※）is信用口座開設に渡す引数  <BR>
     *   　@弁済区分＝”0：指定なし” <BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount
     * @@roseuid 40554CAF000E
     */
    public SubAccount getSubAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccount()";
        log.entering(STR_METHOD_NAME);
        
        //１）super.get補助口座( )にて、該当顧客の投資信託取引用補助口座オブジェクトを取得する。 
        //  [get補助口座に渡すパラメタ] 
        //　@補助口座タイプ＝(*) 
        //  (*)以下の手順で設定。 
        //  －ログインセキュリティサービスより口座IDを取得。
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        long l_accountId = l_opLoginSec.getAccountId();
         
        //  －取得した口座IDを引数に、拡張アカウントマネージャ.get顧客()をコール。 
        //拡張アカウントマネージャ取得
        FinApp l_finApp = 
            (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManaer = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = 
                (WEB3GentradeMainAccount) l_gentradeAccountManaer.getMainAccount(l_accountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(" When 取得した顧客オブジェクト..");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //  －取得した顧客オブジェクト.is信用口座開設()をコール。
        //  ※）is信用口座開設に渡す引数 
        //　@弁済区分＝”0：指定なし 
        boolean l_blnisMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        // ・is信用口座開設=true の場合、以下を指定。 
        // SubAccountTypeEnum.株式信用取引口座（保証金） 
        if (l_blnisMarginAccountEstablished == true)
        {
            log.exiting(STR_METHOD_NAME);
            return super.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        }
        else
        {
            // is信用口座開設=false の場合、以下を指定。 
            //　@SubAccountTypeEnum.株式取引口座（預り金）
            log.exiting(STR_METHOD_NAME);
            return super.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        }
    }
    
}
@
