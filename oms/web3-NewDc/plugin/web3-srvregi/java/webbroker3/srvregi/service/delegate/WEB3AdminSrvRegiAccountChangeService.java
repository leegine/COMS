head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.50.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客変更サービス(WEB3AdminSrvRegiAccountChangeService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 張学剛 新規作成
*/

package webbroker3.srvregi.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (サービス利用管理者顧客変更サービス)<BR>
 * サービス利用管理者顧客変更サービスインターフェイスクラス<BR> 
 *                                                               
 * @@author 張学剛
 * @@version 1.0
 */
public interface WEB3AdminSrvRegiAccountChangeService extends WEB3BusinessService 
{
    
    /**
     * サービス利用管理者顧客変更処理を行う。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E3400233
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
