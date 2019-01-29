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
filename	WEB3AdminBondProductListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券銘柄一覧サービス(WEB3AdminBondProductListService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 何文敏 (中訊) 新規作成
*/

package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者債券銘柄一覧サービス)<BR>
 * 債券管理者銘柄一覧サービスインターフェイス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0 
 */
public interface WEB3AdminBondProductListService extends WEB3BusinessService
{
    
    /**
     * 債券管理者銘柄一覧を実施する。<BR>
     * @@param l_request - (リクエストオブジェクト)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B5FDFC0090
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
