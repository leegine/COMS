head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradePasswordConvService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 暗証番号変換サービスインタフェイス(WEB3GentradePasswordConvService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 仲川(ＳＲＡ) 新規作成
*/
package webbroker3.gentrade.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;
import webbroker3.gentrade.message.WEB3GentradePasswordConvMainAccountRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvMainAccountResponse;

/**
 * 暗証番号変換サービス実装クラス<br /> 
 * <br />
 * @@author ＳＲＡ仲川
 */
public interface WEB3GentradePasswordConvService 
	extends WEB3BackBusinessService
{
	/**
	 * 暗証番号変換サービス処理を行う。<br />
	 * <br />
	 * @@param l_request - リクエストデータ
	 * @@exception  SYSTEM_ERROR_80003:　@DBエラー
	 * @@return WEB3BackResponse<br />
	 * @@roseuid 421036A8039E
	 */
	public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;

}
@
