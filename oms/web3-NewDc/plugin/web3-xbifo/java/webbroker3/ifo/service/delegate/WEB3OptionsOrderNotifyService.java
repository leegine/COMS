head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOrderNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文通知サービス(WEB3OptionsOrderNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 凌建平 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (OP注文通知サービス)<BR>
 * 株式指数オプション注文通知サービスインタフェイス<BR>
 * @@author  : 凌建平
 * @@version : 1.0
 */
public interface WEB3OptionsOrderNotifyService extends WEB3BackBusinessService 
{
   
   /**
    * 株価指数オプション注文通知サービス処理を実施する。
    * @@param l_request - WEB3BackRequest
    * @@return WEB3BackResponse
    * @@throws WEB3BaseException
    * @@roseuid 4163A7F30193
    */
   public WEB3BackResponse execute(WEB3BackRequest  l_request) throws WEB3BaseException;
}
@
