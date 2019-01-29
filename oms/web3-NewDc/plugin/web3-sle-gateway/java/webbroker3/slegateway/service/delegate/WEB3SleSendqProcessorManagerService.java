head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.02.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleSendqProcessorManagerService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SleSendqProcessorManagerServiceクラス
Author Name      : Daiwa Institute of Research
Revision History : 2006/04/24 呉 新規作成
　@　@　@　@　@　@　@　@　@ 2006/09/20 李 WEB3実装方針にあわせ
*/
package webbroker3.slegateway.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * SEND_Q送信管理クラスのインタフェース
 */
public interface WEB3SleSendqProcessorManagerService extends WEB3BusinessService{
	/**
	  * send_q送信管理処理を実施する。<BR>
	  * @@param l_request - (リクエストデータ)<BR>
	  * リクエストデータ<BR>
	  * 
	  * @@return WEB3GenResponse
	  * @@throws WEB3BaseException
	  */
	 public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
