head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.48.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQSendMessageResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3MQSendMessageResponseクラス(WEB3MQSendMessageResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/31 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.mqgateway.manager.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3MQSendMessageResponse extends WEB3GenResponse
{
    
    public static final String PTYPE = "mq_send_message";
    
}
@
