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
filename	WEB3RuitoCancelService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資取消サービス
                   (WEB3RuitoCancelService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15  李志強 (中訊) 新規作成
*/
package webbroker3.xbruito.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;

/**
 * 累積投資取消サービスインターフェイス<BR>
 */
public interface WEB3RuitoCancelService extends 
WEB3BusinessService 
{
   
   /**
    * 累積投資取消サービス処理を実施する。<BR>
    * @@param l_request - リクエストデータ
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 40581C1900AE
    */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
    throws WEB3BaseException;
}
@
