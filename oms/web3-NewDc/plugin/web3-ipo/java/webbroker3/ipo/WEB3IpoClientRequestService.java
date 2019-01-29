head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOクライアントリクエストサービス(WEB3IpoClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
*/

package webbroker3.ipo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3ClientRequestService;

/**
 * IPOクライアントリクエストサービスクラス
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IpoClientRequestService extends WEB3ClientRequestService 
{
    
    /**
     * @@roseuid 4112FBCD00C7
     */
    public WEB3IpoClientRequestService() 
    {
     
    }
    
    /**
     * (get補助口座)<BR>
     * 補助口座オブジェクトを取得する。<BR>
     * <BR>
     * super.get補助口座()にて補助口座オブジェクトを取得する。<BR>
     * <BR>
     * [super.get補助口座()に指定する引数] <BR>
     * SubAccountTypeEnum.EQUITY_SUB_ACCOUNT<BR>
     * @@return SubAccount
     * @@roseuid 40D2AB5300C2
     */
    public SubAccount getSubAccount() throws WEB3BaseException
    {
        return super.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
    }
}
@
