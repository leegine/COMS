head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNettingExchangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 外国株式為替ネッティングサービス(WEB3FeqNettingExchangeService.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/09/08 車進 (中訊) 新規作成 モデルNo.549
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (外国株式為替ネッティングサービス)<BR>
 * 外国株式為替ネッティングサービスインターフェイス <BR>
 * <BR>
 * @@author 車進
 * @@version 1.0
 */
public interface WEB3FeqNettingExchangeService extends WEB3BackBusinessService 
{
    /**
     * 外国株式為替ネッティングサービス処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストオブジェクト<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
