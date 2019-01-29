head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.33.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoOfferStopResumeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO募集停止再開サービスインタフェイス(WEB3AdminIpoOfferStopResumeService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/26 李海波 (中訊) 新規作成
*/

package webbroker3.ipo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * 管理者IPO募集停止再開サービスインタフェイス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3AdminIpoOfferStopResumeService extends WEB3BusinessService 
{
    
    /**
     * 管理者IPO募集停止再開処理を実施する。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40D0164C0145
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
