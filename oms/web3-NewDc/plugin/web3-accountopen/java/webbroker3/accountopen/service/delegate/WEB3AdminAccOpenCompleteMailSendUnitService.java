head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設完了メール送信UnitService(WEB3AdminAccOpenCompleteMailSendUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 張学剛 新規作成
*/
package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;


/**
 * (口座開設完了メール送信UnitService)<BR>
 * 口座開設完了メール送信UnitServiceインタフェイス<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public interface WEB3AdminAccOpenCompleteMailSendUnitService extends Service 
{
    
    /**
     * (sendMailProcess)<BR>
     * 指定顧客に口座開設完了メールを送信する。<BR>
     * @@param l_mainAccount - 顧客オブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 41A56076008D
     */
    public void sendMailProcess(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;
}
@
