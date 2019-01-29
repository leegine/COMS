head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  (信用取引注文通知サービス)<BR>
                 :  信用取引注文通知サービスインタフェース
                 :  (WEB3MarginOrderNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李松峰 (中訊) 新規作成
                   2005/01/05 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * （信用取引注文通知サービス）。<BR>
 * <BR>
 * 信用取引注文通知サービスインタフェース<BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@version 1.0
 */
public interface WEB3MarginOrderNotifyService extends WEB3BackBusinessService 
{
    
    /**
     * 信用取引注文通知サービス処理を実施する。
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057B11F0041
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
