head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualDisplayOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者銘柄表示順序登録サービス(WEB3AdminMutualDisplayOrderService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 黄建 (中訊) 新規作成 
*/

package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (投信管理者銘柄表示順序登録サービス)<BR>
 * 投資信託管理者銘柄表示順序登録サービスインターフェイス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public interface WEB3AdminMutualDisplayOrderService extends WEB3BusinessService 
{   
    /**
     * (execute)<BR>
     * 投信管理者銘柄表示順序登録サービスを実施する。
     * @@param l_request - (リクエストデータ)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8A20325
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
