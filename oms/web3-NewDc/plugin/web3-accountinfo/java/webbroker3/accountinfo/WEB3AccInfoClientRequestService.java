head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報クライアントリクエストサービス(WEB3AccInfoClientRequestService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3ClientRequestService;


/**
 * (お客様情報クライアントリクエストサービス)<BR>
 * お客様情報クライアントリクエストサービスクラス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoClientRequestService extends WEB3ClientRequestService 
{
    
    /**
     * @@roseuid 418F38510157
     */
    public WEB3AccInfoClientRequestService() 
    {
     
    }
    
    /**
     * (get顧客)<BR>
     * ログインセキュリティサービスより顧客オブジェクトを取得する。<BR>
     * <BR>
     * １）　@ログインセキュリティサービスより口座ＩＤを取得する。<BR>
     * ２）　@口座ＩＤに該当する顧客オブジェクトを返却する。<BR>
     * @@return MainAccount
     * @@roseuid 413BFDA9032A
     */
    public MainAccount getMainAccount() throws WEB3SystemLayerException
    {
        return super.getMainAccount();
    }
    
    /**
     * （get補助口座）
     * @@return
     * @@throws WEB3SystemLayerException
     */
    public SubAccount getSubAccount()
        throws WEB3SystemLayerException
    {
        return super.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
    }
    
}
@
