head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RuleEngineConnectorImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ルールエンジンコネクタ実装(WEB3RuleEngineConnectorImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/15 齋藤 栄三(FLJ) 新規作成
*/
package webbroker3.rlsgateway.connector;

import com.fitechlabs.fin.intellioms.rlsclt.RuleEngineConnector;
import com.fitechlabs.fin.intellioms.rlsclt.RuleEngineConnectorException;
import com.fitechlabs.fin.intellioms.rlsclt.RuleEngineReplyListener;
import com.fitechlabs.fin.intellioms.rlsclt.impl.ConnectorImpl;
import com.fitechlabs.fin.intellioms.rulesys.CondOrder;
import com.fitechlabs.fin.intellioms.rulesys.CondOrderOperation;
import com.fitechlabs.fin.intellioms.util.InitializationException;

import webbroker3.util.WEB3LogUtility;


/**
*
* WEB3ルールエンジンコネクタ実装
* @@author Eizo Saito (FLJ)
* @@version 1.0
*/
public class WEB3RuleEngineConnectorImpl implements WEB3RuleEngineConnector
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuleEngineConnectorImpl.class);
    
    /**
     * ルールエンジンコネクタ
     */
    private RuleEngineConnector ruleEngineConnector;

    /**
     * 接続済み判定フラグ
     */
    private boolean isConnected = false;

    /**
     * 
     */
    public WEB3RuleEngineConnectorImpl()
    {
        WEB3RlsAccountsManager l_accountsManager = new WEB3RlsAccountsManagerImpl();
        
        setRuleEngineConnector(new ConnectorImpl(l_accountsManager));
    }

    /**
     * @@see com.fitechlabs.fin.intellioms.rlsclt.RuleEngineConnector#registerCondOrder(com.fitechlabs.fin.intellioms.rulesys.CondOrder)
     */
    public synchronized void registerCondOrder(CondOrder l_condOrder)
            throws RuleEngineConnectorException
    {
        checkConnection();
        ruleEngineConnector.registerCondOrder(l_condOrder);
    }

    /**
     * @@see com.fitechlabs.fin.intellioms.rlsclt.RuleEngineConnector#submitCondOrderOperation(com.fitechlabs.fin.intellioms.rulesys.CondOrderOperation)
     */
    public synchronized void submitCondOrderOperation(CondOrderOperation l_operation)
            throws RuleEngineConnectorException
    {
        checkConnection();
        ruleEngineConnector.submitCondOrderOperation(l_operation);
    }

    private synchronized void checkConnection()
    {
        if(isConnected() == false)
        {
            restart();
        }
    }


    /**
     * @@see com.fitechlabs.fin.intellioms.util.Startable#start()
     */
    public synchronized void start() throws InitializationException
    {
        ruleEngineConnector.start();
        setConnected(true);
    }

    /**
     * @@see com.fitechlabs.fin.intellioms.util.Startable#stop()
     */
    public synchronized void stop()
    {
        ruleEngineConnector.stop();
        setConnected(false);
    }

    /**
     * @@see com.fitechlabs.fin.intellioms.rlsclt.RuleEngineConnector#setReplyListener(com.fitechlabs.fin.intellioms.rlsclt.RuleEngineReplyListener)
     */
    public void setReplyListener(RuleEngineReplyListener arg0)
    {

    }
    /**
     * @@see webbroker3.rlsgateway.connector.WEB3RuleEngineConnector#restart()
     */
    public synchronized void restart()
    {
        try
        {
            log.info("disconnecting rule engine");
            stop();
            log.info("disconnected  rule engine");
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            try
            {
                //再ストップ
                log.info("retry disconnecting rule engine");
                stop();
                log.info("disconnected  rule engine");
            }
            catch (Exception e1)
            {
                log.error(e1.getMessage(), e1);
            }
        }

        try
        {
            log.info("connecting rule engine");
            start();
            log.info("connected  rule engine");
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
    }
    /**
     * @@see webbroker3.rlsgateway.connector.WEB3RuleEngineConnector#setRuleEngineConnector(com.fitechlabs.fin.intellioms.rlsclt.RuleEngineConnector)
     */
    public synchronized void setRuleEngineConnector(RuleEngineConnector ruleEngineConnector)
    {
        this.ruleEngineConnector = ruleEngineConnector;
    }
    /**
     * @@return isConnected を戻します。
     */
    public synchronized boolean isConnected()
    {
        return isConnected;
    }
    /**
     * @@param isConnected isConnected を設定。
     */
    public synchronized void setConnected(boolean isConnected)
    {
        this.isConnected = isConnected;
    }
    /**
     * @@see webbroker3.rlsgateway.connector.WEB3RuleEngineConnector#retryRegisterCondOrder(com.fitechlabs.fin.intellioms.rulesys.CondOrder)
     */
    public synchronized void retryRegisterCondOrder(CondOrder l_condOrder)
            throws RuleEngineConnectorException
    {
        try
        {
            restart();
            ruleEngineConnector.registerCondOrder(l_condOrder);
        }
        catch (RuleEngineConnectorException e)
        {
            if(RuleEngineConnectorException.NETWORK_ERR == e.getErrType())
            {
                setConnected(false);
            }
            throw e;
        }
    }
    /**
     * @@see webbroker3.rlsgateway.connector.WEB3RuleEngineConnector#retrySubmitCondOrderOperation(com.fitechlabs.fin.intellioms.rulesys.CondOrderOperation)
     */
    public synchronized void retrySubmitCondOrderOperation(CondOrderOperation l_operation)
            throws RuleEngineConnectorException
    {
        try
        {
            restart();
            ruleEngineConnector.submitCondOrderOperation(l_operation);
        }
        catch (RuleEngineConnectorException e)
        {
            if(RuleEngineConnectorException.NETWORK_ERR == e.getErrType())
            {
                setConnected(false);
            }
            throw e;
        }
    }
}
@
