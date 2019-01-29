head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecuteEndNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP出来終了通知サービスインターフェイス(WEB3IfoExecuteEndNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/22 艾興 (中訊) 新規作成
              001: 2004/07/29 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000063 execute()を修正
*/
package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (先物OP出来終了通知サービス)<BR>
 * 先物OP出来終了通知サービスインターフェイス<BR>
 */
public interface WEB3IfoExecuteEndNotifyService extends WEB3BackBusinessService 
{
    
    /**
     * 先物OP出来終了通知サービス処理を実施する。
     * @@param l_request - リクエストデータ
     * @@return webbroker3.ifo.message.WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057B9EA0283
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
