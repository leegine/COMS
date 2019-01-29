head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8853d04940;
filename	WEB3MQSendMessageHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3MQSendMessageHandlerクラス(WEB3MQSendMessageHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/31 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.mqgateway.manager.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.mqgateway.manager.message.WEB3MQSendMessageRequest;
import webbroker3.mqgateway.manager.message.WEB3MQSendMessageResponse;
import webbroker3.mqgateway.manager.service.delegate.WEB3MQManagementService;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3MQSendMessageHandler implements MessageHandler
{
    
    public WEB3MQSendMessageResponse sendMessage(WEB3MQSendMessageRequest l_request)
    {
        
        WEB3MQManagementService l_service = 
            (WEB3MQManagementService) Services.getService(WEB3MQManagementService.class);
        
        WEB3MQSendMessageResponse l_response = null;
        
        try
        {
            l_response = 
                (WEB3MQSendMessageResponse) l_service.execute(l_request); 
        } catch (WEB3BaseException e)
        {
            l_response = (WEB3MQSendMessageResponse) l_request.createResponse();
            l_response.server_exception = e.getErrorInfo();
        }
        return l_response;
    }
    
}
@
