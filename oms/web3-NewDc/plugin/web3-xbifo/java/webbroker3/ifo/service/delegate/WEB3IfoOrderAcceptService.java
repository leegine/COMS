head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright       : (株)大和総研 証券ソリューションシステム第二部
File Name       :先物OP注文受付サービスインターフェイス(WEB3IfoOrderAcceptService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/21   王暁傑 (Sinocom) 新規作成 
              001: 2004/07/28  王暁傑 (Sinocom) 対応詳細設計バッグ 
*/
package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (先物OP注文受付サービス)<BR>
 * 先物OP注文受付サービスインターフェイス<BR>
 */
public interface WEB3IfoOrderAcceptService extends WEB3BackBusinessService 
{
    
    /**
     * 先物OP注文受付サービス処理を実施する。
     * @@param l_request - リクエストデータ
     * @@return webbroker3.ifo.message.WEB3IfoOrderAcceptResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057CEC4010C
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
