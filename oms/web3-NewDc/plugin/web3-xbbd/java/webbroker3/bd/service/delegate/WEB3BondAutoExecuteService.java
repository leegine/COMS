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
filename	WEB3BondAutoExecuteService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券自動約定サービス(WEB3BondAutoExecuteService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/29 齊珂 (中訊) 新規作成
*/

package webbroker3.bd.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (債券自動約定サービス)<BR>
 * 債券自動約定サービスインターフェース<BR>
 * 
 * @@author 齊珂(中訊)
 * @@version 1.0 
 */
public interface WEB3BondAutoExecuteService extends WEB3BackBusinessService
{
    /**
     * 債券自動約定処理を実施する。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
    
    /**
     * (exec自動約定For顧客)<BR>
     * exec自動約定For顧客 <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@throws WEB3BaseException
     */
    public void execAutoExecuteForAccount(MainAccount l_mainAccount) throws WEB3BaseException;
}
@
