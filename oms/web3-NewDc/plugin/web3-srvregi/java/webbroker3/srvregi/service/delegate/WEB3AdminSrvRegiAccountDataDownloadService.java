head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.50.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataDownloadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客データダウンロードサービス(WEB3AdminSrvRegiAccountDataDownloadService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (サービス利用管理者顧客データダウンロードサービス)<BR>
 * 管理者顧客データダウンロードサービスインタフェイスクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public interface WEB3AdminSrvRegiAccountDataDownloadService extends WEB3BusinessService 
{
    
    /**
     * サービス利用管理者顧客データダウンロード処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410725D30176
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
