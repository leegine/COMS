head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.37.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MQMessageSenderServiceForTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEBMQMessageSenderServiceForTestクラス(WEB3MQMessageSenderServiceForTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.mqgateway.stdimpls;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mqgateway.WEB3MQMessageContext;
import webbroker3.mqgateway.WEB3MQMessageSenderService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;


/**
 * WEB3MQMessageSenderServiceの単体テスト用実装クラス
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3MQMessageSenderServiceForTest implements WEB3MQMessageSenderService
{

    /**
     * @@see webbroker3.mqgateway.WEB3MQMessageSenderService#send(webbroker3.mqgateway.WEB3MQMessageSpec)
     */
    public WEB3MQSendResult send(WEB3MQMessageSpec l_messageSpec)
            throws WEB3SystemLayerException
    {
        return WEB3DefaultMQSendResult.SUCCESS_RESULT_INSTANCE;
    }

    /**
     * @@see webbroker3.mqgateway.WEB3MQMessageSenderService#send(webbroker3.mqgateway.WEB3MQMessageSpec, webbroker3.mqgateway.WEB3MQMessageContext)
     */
    public WEB3MQSendResult send(WEB3MQMessageSpec l_messageSpec, WEB3MQMessageContext l_messageContext) throws WEB3SystemLayerException
    {
        
        
        return WEB3DefaultMQSendResult.SUCCESS_RESULT_INSTANCE;
    }

}
@
