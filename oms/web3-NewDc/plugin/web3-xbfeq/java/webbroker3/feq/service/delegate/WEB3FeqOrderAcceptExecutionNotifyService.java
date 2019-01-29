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
filename	WEB3FeqOrderAcceptExecutionNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文受付出来通知サービス(WEB3FeqOrderAcceptExecutionNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 齊珂 (中訊) 新規作成
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (外国株式注文受付出来通知サービス)<BR>
 * 外国株式注文受付出来通知サービスインタフェース<BR>
 * <BR>
 * （トランザクション属性：設定不要）<BR>
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public interface WEB3FeqOrderAcceptExecutionNotifyService extends WEB3BackBusinessService 
{
    /**
     * 外国株式注文受付出来通知サービス処理を実施する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストオブジェクト<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
