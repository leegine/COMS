head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.48.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQSendMessageRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3MQSendMessageRequestクラス(WEB3MQSendMessageRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/31 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.mqgateway.manager.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3MQSendMessageRequest extends WEB3GenRequest
{
    
    public static final String PTYPE = "mq_send_message";
    
    public WEB3MQSendMessageRequest()
    {
        super();
    }
    
    /**
     * 会社コード
     */
    public String institutionCode;
    
    /**
     * データコード
     */
    public String dataCode;
    
    /**
     * ユーザーデータ
     */
    public String userData;
    
    /**
     * OracleSID
     */
    public String oracleSID;
    
    /**
     * 顧客ID（自）
     */
    public String accountIdStart;
    
    /**
     * 顧客ID（至）
     */
    public String accountIdEnd;
    
    /**
     * 発注日（yyyyMMdd）
     */
    public String bizDate;


    /**
     * @@return dataCode を取得する。
     */
    public String getDataCode()
    {
        return dataCode;
    }
    /**
     * @@return institutionCode を取得する。
     */
    public String getInstitutionCode()
    {
        return institutionCode;
    }
    /**
     * @@return userData を取得する。
     */
    public String getUserData()
    {
        return userData;
    }
    
    /**
     * @@return accountIdEnd を取得する。
     */
    public String getAccountIdEnd()
    {
        return accountIdEnd;
    }
    /**
     * @@return accountIdStart を取得する。
     */
    public String getAccountIdStart()
    {
        return accountIdStart;
    }
    /**
     * @@return bizDate を取得する。
     */
    public String getBizDate()
    {
        return bizDate;
    }
    /**
     * @@return oracleSID を取得する。
     */
    public String getOracleSID()
    {
        return oracleSID;
    }
    
    /**
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MQSendMessageResponse();
    }
    
}
@
