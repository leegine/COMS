head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.48.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQManagementServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MQManagementServiceImplクラス(WEB3MQManagementServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/31 山田　@卓司(FLJ) 新規作成
                  : 2005/03/08 山田　@卓司(FLJ) リクエストメッセージで指定された発注日が正しく設定されない問題を修正
 */
package webbroker3.mqgateway.manager.service.delegate.stdimpls;

import java.text.ParseException;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mqgateway.WEB3MQMessageContext;
import webbroker3.mqgateway.WEB3MQMessageSenderService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.mqgateway.manager.message.WEB3MQSendMessageRequest;
import webbroker3.mqgateway.manager.message.WEB3MQSendMessageResponse;
import webbroker3.mqgateway.manager.service.delegate.WEB3MQManagementService;

/**
 * 
 * 
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3MQManagementServiceImpl implements WEB3MQManagementService
{

    /**
     * @@see webbroker3.common.service.delegate.WEB3BusinessService#execute(webbroker3.common.message.WEB3GenRequest)
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
            throws WEB3BaseException
    {
        WEB3MQSendMessageRequest l_req = null;
        try
        {
            l_req = (WEB3MQSendMessageRequest) l_request;
        } catch (ClassCastException l_cce)
        {
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                    getClass().getName() + "execute(WEB3GenRequest)", 
                    l_cce.getMessage(), 
                    l_cce);
        }
        return sendMessage(l_req);
    }

    protected WEB3MQSendMessageResponse sendMessage(
            WEB3MQSendMessageRequest l_request)
        throws WEB3BaseException
    {
        
        WEB3MQSendMessageResponse l_response = 
            (WEB3MQSendMessageResponse) l_request.createResponse();

        WEB3MQMessageSpec l_messageSpec = toMessageSpec(l_request);
        WEB3MQMessageContext l_messageContext = toMessageContext(l_request);
        
        WEB3MQMessageSenderService l_sender = 
            (WEB3MQMessageSenderService) Services.getService(WEB3MQMessageSenderService.class);
        WEB3MQSendResult l_result = l_sender.send(l_messageSpec, l_messageContext);
        
        return l_response;
        
    }
    
    private WEB3MQMessageSpec toMessageSpec(WEB3MQSendMessageRequest l_request)
    {
        return new WEB3MQMessageSpec(
                l_request.getInstitutionCode(), 
                l_request.getDataCode(), 
                l_request.getUserData());
    }
    
    private WEB3MQMessageContext toMessageContext(WEB3MQSendMessageRequest l_request)
    {
        
        // 顧客コード（自）、(至）
        Long l_lngAccountIdStart = toLong(l_request.getAccountIdStart());
        Long l_lngAccountIdEnd = toLong(l_request.getAccountIdEnd());
        
        // 発注日
        Date l_datBizDate = toDate(l_request.getBizDate());
        
        // コンテキスト生成
        return new WEB3MQMessageContext(
                l_request.getInstitutionCode(),
                l_request.getOracleSID(),
                l_lngAccountIdStart,
                l_lngAccountIdEnd,
                l_datBizDate);
    }

    /**
     * String⇔Long変換
     */
    private Long toLong(String l_strSource)
    {
        Long l_lngValue = null;
        if (l_strSource != null)
        {
            try {
                l_lngValue = Long.valueOf(l_strSource);
            } catch (NumberFormatException l_nfe)
            {
                
            }
        }
        return l_lngValue;
    }
    
    /**
     * String⇔Date変換
     */
    private Date toDate(String l_strSource)
    {
        Date l_datBizDate = null; 
        if (l_strSource != null)
        {
            try
            {
                l_datBizDate = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd").parse(l_strSource);
            } catch (ParseException l_pe)
            {
            }
        }
        if (l_datBizDate == null)
        {
            l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        }
        return l_datBizDate;
    }

}@
