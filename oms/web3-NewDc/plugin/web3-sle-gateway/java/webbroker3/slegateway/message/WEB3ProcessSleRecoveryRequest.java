head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3ProcessSleRecoveryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3ProcessSleRecoveryRequestクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/26 孫(FLJ) 新規作成
 */
package webbroker3.slegateway.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * リカバリーメッセージを示す
 * 
 * @@author      孫（FLJ）
 * @@version     V1.0  
 */
public class WEB3ProcessSleRecoveryRequest extends WEB3GenRequest
{

    /** このメッセージのPTYPEです。 */
    public static final String PTYPE = "RECOVERY_REQUEST";
    
    /**
     * スレッドNO 
     */
    public  Long threadNo;
    
    /**
     * 口座開始ID
     */
    public long fromAccountId;
    
    /**
     * 口座終了ID
     */
    public long toAccountId;
    
    /**
     * リクエスト送信日時
     */
    public Date date;

    /* (非 Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}
@
