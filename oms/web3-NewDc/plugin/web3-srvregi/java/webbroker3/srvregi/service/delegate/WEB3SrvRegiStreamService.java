head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.50.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStreamService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用債券連携サービス(WEB3SrvRegiStreamService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/19 馮海濤 新規作成 モデル371,379
*/

package webbroker3.srvregi.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (サービス利用債券連携サービス)<BR>
 * サービス利用債券連携サービスインターフェイスクラス<BR>
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public interface WEB3SrvRegiStreamService extends WEB3BusinessService
{

   /**
    * サービス利用債券連携サービス処理を行う。<BR>
    * @@param l_request - リクエストデータ<BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException
    * @@roseuid 48158C9C03B0
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
