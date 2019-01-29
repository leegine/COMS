head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.13.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioInputOutputHistoryService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴サービス(WEB3AioInputOutputHistoryService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 艾興 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (入出庫履歴サービス)<BR>
 * 入出庫履歴サービスインターフェイス
 * @@author 艾興
 * @@version 1.0
 */
public interface WEB3AioInputOutputHistoryService extends WEB3BusinessService 
{

    
    /**
     * 入出庫履歴サービス処理を行う。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@roseuid 41B7B7360318
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
