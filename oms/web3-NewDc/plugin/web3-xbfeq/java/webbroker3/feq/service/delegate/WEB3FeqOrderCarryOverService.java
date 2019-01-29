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
filename	WEB3FeqOrderCarryOverService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文繰越サービス(WEB3FeqOrderCarryOverService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー       
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;


/**
 * (外国株式注文繰越サービス)<BR>
 * 外国株式注文繰越サービスインタフェイス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public interface WEB3FeqOrderCarryOverService extends WEB3BackBusinessService 
{
    
    /**
     * 外国株式注文繰越サービスを行う。
     * @@param l_request - (リクエスト)<BR>
     * リクエスト
     * 
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 42B8A97D03C3
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
