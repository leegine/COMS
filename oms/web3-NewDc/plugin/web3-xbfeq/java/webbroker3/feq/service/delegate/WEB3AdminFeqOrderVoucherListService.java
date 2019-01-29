head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderVoucherListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式当日注文伝票一覧サービス(WEB3AdminFeqOrderVoucherListService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 郭英 (中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー     
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者外国株式当日注文伝票一覧サービス)<BR>
 * 管理者外国株式当日注文伝票一覧サービスインターフェイス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3AdminFeqOrderVoucherListService extends WEB3BusinessService 
{
    
    /**
     * 外国株式当日注文伝票一覧サービス処理を行う。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FC1D900CE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
