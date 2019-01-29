head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連絡管理クライアントリクエストサービス(WEB3InformClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 艾興 (中訊) 新規作成
*/
package webbroker3.inform;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (連絡管理クライアントリクエストサービス)<BR>
 * 連絡管理クライアントリクエストサービスクラス
 */
public class WEB3InformClientRequestService extends WEB3ClientRequestService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformClientRequestService.class);

    /**
     * @@roseuid 41EE642D0138
     */
    public WEB3InformClientRequestService()
    {

    }

    /**
     * (get補助口座)<BR>
     * 補助口座オブジェクトを取得する。 <BR>
     * <BR>
     * super.get補助口座()にて補助口座オブジェクトを取得する。 <BR>
     * <BR>
     * [super.get補助口座()に指定する引数] 
     * SubAccountTypeEnum.EQUITY_SUB_ACCOUNT<BR>
     * @@return WEB3GentradeSubAccount
     * @@roseuid 418F43730108
     */
    public WEB3GentradeSubAccount getSubAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)
                super.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
}
@
