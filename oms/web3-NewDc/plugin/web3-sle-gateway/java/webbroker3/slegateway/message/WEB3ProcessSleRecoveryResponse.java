head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3ProcessSleRecoveryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3ProcessSleRecoveryResponseクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/26 孫(FLJ) 新規作成
 */
package webbroker3.slegateway.message;

import java.util.Date;


import webbroker3.common.message.WEB3GenResponse;


/**
 * リカバリー処理のレスポンスクラスです。
 * 
 * @@author      孫（FLJ）
 * @@version     V1.0  
 */
public class WEB3ProcessSleRecoveryResponse extends WEB3GenResponse
{

    /** このメッセージのPTYPEです。 */
    public static final String PTYPE = "market.adapter.sle.process_recovery";
    
    /**
     * レスポンス返信日時
     */
    public Date date;
   
}
@
