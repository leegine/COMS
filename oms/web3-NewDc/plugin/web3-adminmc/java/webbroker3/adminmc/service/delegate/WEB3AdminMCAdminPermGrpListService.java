head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.33.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ一覧サービス(WEB3AdminMCAdminPermGrpListService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/25 Tongwei(中訊) 新規作成
*/

package webbroker3.adminmc.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;


/**
 * (管理者メニュー制御管理者権限グループ一覧サービス)<BR>
 * 管理者メニュー制御管理者権限グループ一覧サービスインタフェイス<BR>
 * <BR>
 * 
 * @@author Tongwei
 * @@version 1.0 
 */
public interface WEB3AdminMCAdminPermGrpListService extends WEB3BusinessService 
{
    
    /**
     * 管理者権限グループ一覧／詳細処理を実施する。<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41774B960000
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
