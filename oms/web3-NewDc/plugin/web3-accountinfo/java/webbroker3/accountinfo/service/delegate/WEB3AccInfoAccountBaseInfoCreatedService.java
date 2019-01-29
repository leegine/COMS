head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.19.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountBaseInfoCreatedService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報顧客基本情報作成サービス(WEB3AccInfoAccountBaseInfoCreatedService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo;
import webbroker3.accountinfo.message.WEB3AccInfoStopInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;

/**
 * (お客様情報顧客基本情報作成サービス)<BR>
 * お客様情報顧客基本情報作成サービスインタフェイス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public interface WEB3AccInfoAccountBaseInfoCreatedService extends Service 
{
    /**
     * (create顧客基本情報)<BR>
     * 顧客オブジェクトより、顧客基本情報メッセージデータを作成する。<BR>
     * @@param l_mainAccount - 顧客オブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo
     * @@roseuid 415D16F4032A
     */
    public WEB3AccInfoAccountBaseInfo createAccountBaseInfo(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;
    
    /**
     * (create停止情報)<BR>
     * 顧客オブジェクトより、停止情報メッセージデータを作成する。 <BR>
     * @@param l_mainAccount - 顧客オブジェクト
     * @@return　@webbroker3.accountinfo.message.WEB3AccInfoStopInfo
     * @@throws WEB3BaseException
     */
    public WEB3AccInfoStopInfo createStopInfo(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException; 
}
@
