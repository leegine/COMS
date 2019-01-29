head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFrgnMmfOrderAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨MMF注文受付サービス(WEB3MutualFrgnMmfOrderAcceptService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06 張騰宇 (中訊) 新規作成 (モデル534)
*/
package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (外貨MMF注文受付サービス)<BR>
 * 外貨MMF注文受付サービスインターフェイス<BR>
 * 
 * @@author 張騰宇(中訊)
 * @@version 1.0
 */
public interface WEB3MutualFrgnMmfOrderAcceptService extends WEB3BackBusinessService
{

    /**
     * 外貨MMF注文受付サービス処理を実施する。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B9C7B901C7
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
