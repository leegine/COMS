head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAfterRepayTransitionReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 返済後余力情報画面サービス(WEB3TPAfterRepayTransitionReferenceService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/04/08 nakazato(ACT) 新規作成
 */
package webbroker3.tradingpower.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (返済後余力情報画面サービス)
 */
public interface WEB3TPAfterRepayTransitionReferenceService
        extends WEB3BusinessService
{
    /**
     * (execute)
     * 
     * ※シーケンス図「（資産余力情報画面表示サービス）execute」参照
     * 
     * @@param リクエスト
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
            throws WEB3BaseException;
}@
