head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderLockStatusUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文ロック区分更新サービス(WEB3AdminBondOrderLockStatusUpdateService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 周捷(中訊) 新規作成         
*/

package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (債券管理者注文ロック区分更新サービス)<BR>
 * 管理者債券注文ロック区分更新サービスインタフェース
 * 
 * @@author 周捷
 * @@version 1.0
 */
public interface WEB3AdminBondOrderLockStatusUpdateService extends WEB3BusinessService
{
    
    /**
     * 債券管理者注文ロック区分更新処理を行う
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C42D9D001E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException;
}
@
