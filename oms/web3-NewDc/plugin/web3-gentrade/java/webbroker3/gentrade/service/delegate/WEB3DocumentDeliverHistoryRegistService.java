head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocumentDeliverHistoryRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 書面交付履歴登録サービス(WEB3DocumentDeliverHistoryRegistService)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/25 大澤喜宗@(SRA) 新規作成
*/
package webbroker3.gentrade.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (書面交付履歴登録サービス)<BR>
 * <BR>
 * 書面交付履歴登録サービスインターフェイス<BR>
 * @@author 大澤喜宗@
 * @@version 1.0
 */
public interface WEB3DocumentDeliverHistoryRegistService extends WEB3BusinessService 
{

    /**
     * (execute)<BR>
     * <BR>
	 * 書面交付履歴登録サービス処理を行う。<BR>
     * <BR>
     * @@param l_request リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
	 */
	public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;

}
@
