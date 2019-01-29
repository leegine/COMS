head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondRecruitBuyInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券応募/買付入力サービス(WEB3BondRecruitBuyInputService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/08 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (債券応募/買付入力サービス)<BR>
 * 債券応募/買付入力サービス<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public interface WEB3BondRecruitBuyInputService extends WEB3BusinessService 
{
    
    /**
     * 債券応募/買付入力サービス処理を実施する。
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C452560075
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
