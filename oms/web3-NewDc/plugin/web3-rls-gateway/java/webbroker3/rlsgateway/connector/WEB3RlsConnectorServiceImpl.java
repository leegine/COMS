head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsConnectorServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ルールエンジンへ注文送信処理をするサービス実装(WEB3RlsRegisterServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/12 齋藤 栄三(FLJ) 新規作成
*/
package webbroker3.rlsgateway.connector;

import com.fitechlabs.fin.intellioms.enums.CondOrderOpType;
import com.fitechlabs.fin.intellioms.rlsclt.RuleEngineConnectorException;
import com.fitechlabs.fin.intellioms.rulesys.CondOrder;
import com.fitechlabs.fin.intellioms.rulesys.CondOrderOperation;
import com.fitechlabs.fin.intellioms.rulesys.impl.CondOrderOperationImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.rlsgateway.connector.WEB3RlsConnectorHelpler;
import webbroker3.rlsgateway.connector.WEB3RlsOrders;
import webbroker3.rlsgateway.data.OmsConOrderRequestRow;
import webbroker3.util.WEB3LogUtility;

/**
 *
 * ルールエンジンへ注文送信処理をするサービス実装
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3RlsConnectorServiceImpl implements
        WEB3RlsConnectorService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsConnectorServiceImpl.class);

    /**
     * デバッグison
     */
    private static boolean DBG = log.ison();

    /**
     * コネクタ
     */
    private WEB3RuleEngineConnector connector;
    
    /**
     * ヘルパー
     */
    private WEB3RlsConnectorHelpler helper;
    
    /**
     * 排他制御
     */
    private Object mutex = new Object();
    
    /**
     * xTierスタート済フラグ
     */
    private boolean isXtierStarted = false;
    
    /**
     * connector準備済フラグ
     */
    private boolean isConnectorPrepared = false;
    
    /**
     * xTier起動Try回数
     */
    private long lngRetryCount = 0;
    
    /**
     * 前回try時間
     */
    private long lngPreviousTime = 0;
    
    /**
     * 
     */
    public WEB3RlsConnectorServiceImpl()
    {
    }
    

    /**
     * @@see webbroker3.rlsgateway.connector.WEB3RlsConnectorService#register(webbroker3.rlsgateway.data.OmsConOrderRequestRow, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit)
     */
    public void register(OmsConOrderRequestRow l_request, OrderUnit l_unit)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "register(OmsConOrderRequestRow, OrderUnit)";
        
        CondOrder l_newCondOrder = null;
        
        try
        {
            //コネクト条件準備
            prepareConditions();
            
            //新規注文を作成
            WEB3RlsOrders l_rlsOrders = helper.createRlsOrders(l_request, l_unit);
            
            if(DBG)
            {
                log.debug("new rls orders = " + l_rlsOrders);
            }
            
            //new CondOrder 作成
            l_newCondOrder = helper.createCondOrder(l_request, l_rlsOrders, true);

            if(DBG)
            {
                log.debug("new cond order = " + l_newCondOrder);
            }

            //登録
            connector.registerCondOrder(l_newCondOrder);
        }
        catch (RuleEngineConnectorException e)
        {
            //ネットワーク異常の場合
            if(RuleEngineConnectorException.NETWORK_ERR == e.getErrType())
            {
                try
                {
                    //リトライ
                    connector.retryRegisterCondOrder(l_newCondOrder);
                }
                catch (RuleEngineConnectorException eRetry)
                {
                    log.error(eRetry.getMessage(), eRetry);
                    WEB3BaseException l_ex = handleRuleEngineConnectorException(eRetry, STR_METHOD_NAME);
                    throw l_ex;
                }
            }
            else
            {
                log.error(e.getMessage(), e);
                WEB3BaseException l_ex = handleRuleEngineConnectorException(e, STR_METHOD_NAME);
                throw l_ex;
            }
        }
    }

    /**
     * @@see webbroker3.rlsgateway.connector.WEB3RlsConnectorService#modify(webbroker3.rlsgateway.data.OmsConOrderRequestRow, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit)
     */
    public void modify(OmsConOrderRequestRow l_request, OrderUnit l_unit)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "modify(OmsConOrderRequestRow, OrderUnit)";
        
        CondOrderOperation l_operation = null;
        
        try
        {
            //コネクト条件準備
            prepareConditions();

            //新規注文を作成
            WEB3RlsOrders l_rlsOrders = helper.createRlsOrders(l_request, l_unit);
            
            if(DBG)
            {
                log.debug("new rls orders = " + l_rlsOrders);
            }

            //DBから以前の注文もセット
            l_rlsOrders = helper.addDbOrders(l_request, l_rlsOrders);

            if(DBG)
            {
                log.debug("new and old rls orders = " + l_rlsOrders);
            }

            //old CondOrder 作成
            CondOrder l_oldCondOrder = helper.createCondOrder(l_request, l_rlsOrders, false);

            if(DBG)
            {
                log.debug("old cond order = " + l_oldCondOrder);
            }
            
            //new CondOrder 作成
            CondOrder l_newCondOrder = helper.createCondOrder(l_request, l_rlsOrders, true);

            if(DBG)
            {
                log.debug("new cond order = " + l_newCondOrder);
            }
        
            //operation 作成
            l_operation = new CondOrderOperationImpl(CondOrderOpType.MODIFY, l_newCondOrder, l_oldCondOrder);
        
            //訂正
            connector.submitCondOrderOperation(l_operation);
        }
        catch (WEB3SystemLayerException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseException(
                    e.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    e.getMessage(),
                    e);
        }
        catch (RuleEngineConnectorException e)
        {
            //ネットワーク異常の場合
            if(RuleEngineConnectorException.NETWORK_ERR == e.getErrType())
            {
                try
                {
                    //リトライ
                    connector.retrySubmitCondOrderOperation(l_operation);
                }
                catch (RuleEngineConnectorException eRetry)
                {
                    log.error(eRetry.getMessage(), eRetry);
                    WEB3BaseException l_ex = handleRuleEngineConnectorException(eRetry, STR_METHOD_NAME);
                    throw l_ex;
                }
            }
            else
            {
                log.error(e.getMessage(), e);
                WEB3BaseException l_ex = handleRuleEngineConnectorException(e, STR_METHOD_NAME);
                throw l_ex;
            }
        }
    }

    /**
     * @@see webbroker3.rlsgateway.connector.WEB3RlsConnectorService#cancel(webbroker3.rlsgateway.data.OmsConOrderRequestRow, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit)
     */
    public void cancel(OmsConOrderRequestRow l_request, OrderUnit l_unit)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "cancel(OmsConOrderRequestRow, OrderUnit)";
        
        CondOrderOperation l_operation = null;
        try
        {
            //コネクト条件準備
            prepareConditions();

            //新規注文を作成
            WEB3RlsOrders l_rlsOrders = helper.createRlsOrders(l_request, l_unit);

            if(DBG)
            {
                log.debug("new rls orders = " + l_rlsOrders);
            }

            //DBから以前の注文もセット
            l_rlsOrders = helper.addDbOrders(l_request, l_rlsOrders);

            if(DBG)
            {
                log.debug("new and old rls orders = " + l_rlsOrders);
            }
        
            //old CondOrder 作成
            CondOrder l_oldCondOrder = helper.createCondOrder(l_request, l_rlsOrders, false);

            if(DBG)
            {
                log.debug("old cond order = " + l_oldCondOrder);
            }
            
            //new CondOrder 作成
            CondOrder l_newCondOrder = helper.createCondOrder(l_request, l_rlsOrders, true);

            if(DBG)
            {
                log.debug("new cond order = " + l_newCondOrder);
            }
        
            //operation 作成
            l_operation = new CondOrderOperationImpl(CondOrderOpType.CANCEL, l_newCondOrder, l_oldCondOrder);
        
            //取消
            connector.submitCondOrderOperation(l_operation);
        }
        catch (WEB3SystemLayerException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseException(
                    e.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    e.getMessage(),
                    e);
        }
        catch (RuleEngineConnectorException e)
        {
            //ネットワーク異常の場合
            if(RuleEngineConnectorException.NETWORK_ERR == e.getErrType())
            {
                try
                {
                    //リトライ
                    connector.retrySubmitCondOrderOperation(l_operation);
                }
                catch (RuleEngineConnectorException eRetry)
                {
                    log.error(eRetry.getMessage(), eRetry);
                    WEB3BaseException l_ex = handleRuleEngineConnectorException(eRetry, STR_METHOD_NAME);
                    throw l_ex;
                }
            }
            else
            {
                log.error(e.getMessage(), e);
                WEB3BaseException l_ex = handleRuleEngineConnectorException(e, STR_METHOD_NAME);
                throw l_ex;
            }
        }
    }
    
    /**
     * Connect条件を準備する。
     */
    protected void prepareConditions() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "prepareConditions";
        
        //xTierスタート済みかチェック
        if(! isXtierStarted)
        {
            //lock
            synchronized(mutex)
            {
                //xTierスタート済みか再チェック
                if(! isXtierStarted)
                {
                    long l_lngCurrentTime = GtlUtils.getSystemTimestamp().getTime();

                    long l_lngCount = WEB3RlsConnectorUtil.getProperty(WEB3RlsConnectorConstants.XTIER_RETRY_SEQUENTIAL_COUNT, WEB3RlsConnectorConstants.DEFAULT_XTIER_RETRY_SEQUENTIAL_COUNT);
                    
                    //連続カウント数より小さい場合
                    if(lngRetryCount < l_lngCount)
                    {
                        try
                        {
                            //xTier起動
                            startXtier();
                        }
                        finally
                        {
                            lngRetryCount++;
                            lngPreviousTime = l_lngCurrentTime;
                        }
                    }
                    else
                    {
                        long l_lngInterval = l_lngCurrentTime - lngPreviousTime;
                        
                        long l_lngWaitInterval = WEB3RlsConnectorUtil.getProperty(WEB3RlsConnectorConstants.XTIER_RETRY_INTERVAL, WEB3RlsConnectorConstants.DEFAULT_XTIER_RETRY_INTERVAL);

                        if(l_lngInterval > l_lngWaitInterval)
                        {
                            try
                            {
                                //xTier起動
                                startXtier();
                            }
                            finally
                            {
                                //初期化
                                lngRetryCount = 0;
                                lngPreviousTime = l_lngCurrentTime;
                            }
                        }
                        else
                        {
                            throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    "interval from previous trial = " + l_lngInterval + " msec. waiting for time to be interval > " + l_lngWaitInterval);
                        }
                    }
                }
            }
        }

        prepareConnection2Rls();
        
    }
    
    /**
     * xTierを起動する
     */
    protected synchronized void startXtier() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "startXtier";
        try
        {
            //xTier起動
            WEB3RlsXtierUtil.startXtier();

            setIsXtierStarted(true);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    e.getMessage(),
                    e);
        }
    }
    
    /**
     * @@see webbroker3.rlsgateway.connector.WEB3RlsConnectorService#prepareConnection2Rls()
     */
    public void prepareConnection2Rls() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "prepareConnection2Rls";
        
        //connector準備済みかチェック
        if(! isConnectorPrepared)
        {
            //lock
            synchronized(mutex)
            {
                //xTier起動済みが条件
                if(isXtierStarted)
                {
                    //connector準備済みか再チェック
                    if(! isConnectorPrepared)
                    {
                        try
                        {
                            connector = new WEB3RuleEngineConnectorImpl();
                            
                            helper = new WEB3RlsConnectorHelplerImpl();
        
                            isConnectorPrepared = true;
                            
                            connector.start();
                            
                        }
                        catch (Exception e)
                        {
                            log.error(e.getMessage(), e);
                            throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    e.getMessage(),
                                    e);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * @@see webbroker3.rlsgateway.connector.WEB3RlsConnectorService#setIsXtierStarted(boolean)
     */
    public synchronized void setIsXtierStarted(boolean l_isXtierStarted)
    {
        isXtierStarted = l_isXtierStarted;
    }
    
    /**
     * handleRuleEngineConnectorException
     * <BR>
     * Exception原因に合わせてエラーカタログを選択する。
     * <BR>
     * return WEB3BaseException
     */
    protected WEB3BaseException handleRuleEngineConnectorException(RuleEngineConnectorException l_ex, String l_strMethodName)
    {
        WEB3BaseException l_retEx = null;

        if(RuleEngineConnectorException.OTHER_ERR == l_ex.getErrType())
        {
            l_retEx = new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02467,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        else if(RuleEngineConnectorException.NETWORK_ERR == l_ex.getErrType())
        {
            l_retEx = new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02468,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        else if(RuleEngineConnectorException.NO_ORDER_ID == l_ex.getErrType())
        {
            l_retEx = new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02469,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        else if(RuleEngineConnectorException.DUP_ORDER_ID == l_ex.getErrType())
        {
            l_retEx = new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02470,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        else if(RuleEngineConnectorException.TOO_LATE == l_ex.getErrType())
        {
            l_retEx = new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02471,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        else
        {
            l_retEx = new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        
        return l_retEx;
    }
}
@
