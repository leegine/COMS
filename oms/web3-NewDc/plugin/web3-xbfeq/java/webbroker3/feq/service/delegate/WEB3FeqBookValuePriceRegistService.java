head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBookValuePriceRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式簿価単価登録サービス(WEB3FeqBookValuePriceRegistService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 黄建 (中訊) 新規作成   
                 : 2005/08/03 鄭海良(中訊) レビュー       
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (外国株式簿価単価登録サービス)<BR>
 * 外国株式簿価単価登録サービスインタフェイス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public interface WEB3FeqBookValuePriceRegistService extends WEB3BusinessService 
{
    
    /**
     * 外国株式簿価単価登録サービスを行う。
     * @@param l_request - (リクエスト)<BR>
     * リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A9375202CB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException;
}
@
