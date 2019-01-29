head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.25.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMProductStopStartChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者商品別取扱停止再開変更サービス(WEB3AdminTMProductStopStartChangeService.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者商品別取扱停止再開変更サービス)<BR>
 * <BR>
 * 管理者商品別取扱停止再開変更サービスインタフェイス<BR>
 * <BR>
 * WEB3AdminTMProductStopStartChangeService<BR>
 * <BR>
 * WEB3AdminTMProductStopStartChangeService interface<BR>
 * <BR>
 * @@author shruti
 * @@version 1.0
 */
public interface WEB3AdminTMProductStopStartChangeService extends WEB3BusinessService
{

   /**
    * 商品別取扱停止／再開処理を行う。<BR>
    * <BR>
    * Execute WEB3AdminTMProductStopStartChangeService process.<BR>
    * <BR>
    * @@param l_request - リクエスト<BR>
    * <BR>
    * l_request<BR>
    * <BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException WEB3BaseException
    * @@roseuid 41747A520189
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
