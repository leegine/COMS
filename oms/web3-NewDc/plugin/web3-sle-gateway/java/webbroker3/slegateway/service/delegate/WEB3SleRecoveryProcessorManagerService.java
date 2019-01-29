head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.02.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleRecoveryProcessorManagerService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : WEB3SleRecoveryProcessorManagerServiceクラス
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2006/05/26 孫(FLJ) 新規作成
 */
package webbroker3.slegateway.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.slegateway.message.WEB3ProcessSleRecoveryRequest;


/**
 * リカバリー処理インタフェース
 * 
 * @@author  孫(FLJ)
 * @@version 1.0
 */
public interface WEB3SleRecoveryProcessorManagerService extends WEB3BusinessService
{
    /**
     * 請求により、処理の初期化を行う
     * @@param request 請求メッセージ
     */
    public  void initService(WEB3ProcessSleRecoveryRequest request);
    
    /**
     * リカバリー処理を実行
     */
    public  void startProcessor();

}
@
