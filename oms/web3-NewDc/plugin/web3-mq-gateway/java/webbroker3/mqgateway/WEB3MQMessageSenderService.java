head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQMessageSenderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MQMessageSenderServiceクラス(WEB3MQMessageSenderService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/31 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.mqgateway;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3SystemLayerException;

/**
 *  
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public interface WEB3MQMessageSenderService extends Service
{

    public WEB3MQSendResult send(WEB3MQMessageSpec l_messageSpec)
            throws WEB3SystemLayerException;

    public WEB3MQSendResult send(WEB3MQMessageSpec l_messageSpec,
            WEB3MQMessageContext l_messageContext)
            throws WEB3SystemLayerException;

}@
