head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文繰越サービス(WEB3EquityOrderCarryOverService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 鄒政 (中訊) 新規作成
                   2004/09/14 李海波 (中訊) 修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

import webbroker3.gentrade.WEB3GentradeMainAccount;

/**
 * （注文繰越サービスインタフェース）。
 * @@version 1.0
 */
public interface WEB3EquityOrderCarryOverService extends WEB3BackBusinessService 
{ 
      
    /**
     * 注文繰越サービス処理を実施する
     * @@param l_request - リクエスト
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4137CDD90324
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
    
    /**
     * (顧客単位繰越実行)<BR>
     * 注文繰越処理を行う。
     * @@param l_mainAccount - 顧客オブジェクト。
     * @@roseuid 411ABEF002FB
     */
    public void execCarryOverForAccount(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;
}
@
