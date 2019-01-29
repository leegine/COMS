head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.24.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginProcessingListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログイン処理一覧サービス (WEB3AdminTMLoginProcessingListService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 張少傑 (中訊) 新規作成 モデルNo.005
*/

package webbroker3.trademanagement.service.delegate;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (ログイン処理一覧サービス)<BR>
 * 管理者ログイン処理一覧サービスインタフェイス。<BR>
 * <BR>
 * @@author 張少傑
 * @@version 1.0
 */
public interface WEB3AdminTMLoginProcessingListService extends WEB3BusinessService
{
    /**
     * 管理者ログイン処理一覧サービス処理を行う。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE15770188
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
