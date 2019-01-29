head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券クライアントリクエストサービス(WEB3BondClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/22 徐大方 (中訊) 新規作成
*/

package webbroker3.bd;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;



import webbroker3.gentrade.WEB3ClientRequestService;


/**
 * (債券クライアントリクエストサービス )<BR>
 * 債券クライアントリクエストサービス
 * 
 * @@author  徐大方
 * @@version 1.0
 */
public class WEB3BondClientRequestService extends WEB3ClientRequestService
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3BondClientRequestService.class);
            
    /**
     * @@roseuid 424CC4BE01B5
     */
    public WEB3BondClientRequestService() 
    {
     
    }
    
    /**
     * (get補助口座)<BR>
     * （getSubAccountのオーバーロード）  <BR>
     * <BR>
     * ログインセキュリティサービスより補助口座を取得する。  <BR>
     * <BR>
     * １）　@ログインセキュリティサービスより口座ＩＤを取得し、該当する顧客オブジェクト
     * を取得する。 <BR>
     * <BR>
     * ２）　@拡張アカウントマネージャ.getSubAccount()にて、該当顧客の補助口座オブジェク
     * トを取得する。 <BR>
     * <BR>
     * 　@[getSubAccount()にセットするパラメータ] <BR>
     * 　@　@補助口座タイプ：　@<BR>
     * 　@　@　@[顧客オブジェクト.is信用口座開設(”指定なし”) == trueの場合]<BR>
     * 　@　@　@　@SubAccountTypeEnum.株式信用取引口座<BR>
     * 　@　@　@[上記以外の場合<BR>
     * 　@　@　@　@SubAccountTypeEnum.株式取引口座<BR>
     * @@return 補助口座
     * @@roseuid 421947A80030
     */
    public SubAccount getSubAccount() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);
        //１）ログインセキュリティサービスより口座ＩＤを取得する。
        OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        //）　@ログインセキュリティサービスより口座ＩＤを取得し、該当する顧客オブジェクトを取得する。      
        long l_lngAccountId = l_opLoginSec.getAccountId();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount  l_subAccount = null;
        try
        {
            SubAccountTypeEnum l_subAccountType = null;
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
            //[顧客オブジェクト.is信用口座開設(”指定なし”) == trueの場合]SubAccountTypeEnum.株式信用取引口座
            if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                l_subAccountType = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
            }
            //上記以外の場合SubAccountTypeEnum.株式取引口座
            else
            {
                l_subAccountType =SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }
            //２）　@拡張アカウントマネージャ.getSubAccount()にて、該当顧客の補助口座オブジェクトを取得する。
            l_subAccount = l_accountManager.getSubAccount(l_lngAccountId,l_subAccountType);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);        
        return l_subAccount;
    }
}
@
