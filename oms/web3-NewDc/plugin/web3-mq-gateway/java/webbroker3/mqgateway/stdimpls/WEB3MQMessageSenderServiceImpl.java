head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQMessageSenderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MQMessageSenderクラス(WEB3MQMessageSender.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/26 山田　@卓司 (FLJ) 新規作成
 */
package webbroker3.mqgateway.stdimpls;

import jp.co.dir.ms.mq.WFMQConnection;
import jp.co.dir.ms.mq.WFMQException;
import jp.co.dir.ms.mq.WFMQMaxasMessage;
import jp.co.dir.ms.mq.WFMQMessage;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mqgateway.WEB3MQMessageContext;
import webbroker3.mqgateway.WEB3MQMessageSenderService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3MQMessageSenderServiceImpl implements WEB3MQMessageSenderService
{

    /**
     * ログ出力ユーティリティ
     */
    private static final WEB3LogUtility LOG = 
        WEB3LogUtility.getInstance(WEB3MQMessageSenderServiceImpl.class);
    
    /**
     * デバックフラグ
     */
    private static final boolean DBG = LOG.ison();

    private final WEB3MQMessageBuilder messageBuilder;
    private final WEB3MQMessageContextFactory contextFactory;

    public WEB3MQMessageSenderServiceImpl()
    {
        messageBuilder = new WEB3MQMessageBuilder();
        contextFactory = new WEB3MQMessageContextFactory();
    }

    /**
     * @@see webbroker3.mqgateway.WEB3MQMessageSenderService#send(webbroker3.mqgateway.WEB3MQMessageSpec)
     */
    public WEB3MQSendResult send(WEB3MQMessageSpec l_messageSpec) throws WEB3SystemLayerException
    {
        WEB3MQMessageContext l_messageContext = contextFactory.create(l_messageSpec); 
        return this.send(l_messageSpec, l_messageContext);
    }

    /**
     * @@see webbroker3.mqgateway.WEB3MQMessageSenderService#send(webbroker3.mqgateway.WEB3MQMessageSpec,
     *      webbroker3.mqgateway.WEB3MQMessageContext)
     */
    public WEB3MQSendResult send(WEB3MQMessageSpec l_messageSpec,
            WEB3MQMessageContext l_messageContext)
            throws WEB3SystemLayerException
    {

        WEB3MQSendResult l_result = WEB3DefaultMQSendResult.SUCCESS_RESULT_INSTANCE;
        
        // QueueIdを取得
        String l_strQueueId = WEB3MQGatewayUtils.getQueueId(l_messageSpec);
        // MQConnectionを生成
        WFMQConnection l_mqCon = new WFMQConnection(l_strQueueId);
        
        if (DBG)
        {
            LOG.debug("MQConnection created. " + WEB3MQGatewayUtils.toString(l_mqCon));
        }
        
        // MaxasMessageを生成
        WFMQMaxasMessage l_maxasMessage = 
            messageBuilder.create(l_messageSpec, l_messageContext);

        try
        {
            // MQMessageを生成
            WFMQMessage l_message = l_maxasMessage.createMQMessage();
            
            if (DBG)
            {
                LOG.debug("Message : " + new String(l_message.getMessage()));
            }

            // Queueにプット
            l_mqCon.put(l_message);

        } catch (WFMQException l_mqe)
        {
            LOG.error(l_mqe.getMessage(), l_mqe);
        } catch (Throwable l_th)
        {
            LOG.error(l_th.getMessage(), l_th);
        }
        finally
        {
            // MQConnectionをクローズ
            if (l_mqCon != null)
            {
                try
                {
                    l_mqCon.close();
                } catch (WFMQException l_mqe)
                {
                    LOG.error(l_mqe.getMessage(), l_mqe);
                }
            }
        }

        return l_result;
    }

}@
