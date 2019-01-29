head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.45.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3StartQuoteMonitoringRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3StartQuoteMonitoringRequestクラス(WEB3StartQuoteMonitoringRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/27 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.quoteadaptor.stdimpls.message;

import com.fitechlabs.xtrade.kernel.message.Request;


/**
 * 時価情報の監視を開始するリクエストメッセージ
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3StartQuoteMonitoringRequest extends Request
{
    
    /**
     * このメッセージのPTYPE
     */
    public static final String PTYPE = "start_quote_monitoring";

}
@
