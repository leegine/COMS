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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[���G���W���֒������M����������T�[�r�X����(WEB3RlsRegisterServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/12 �V�� �h�O(FLJ) �V�K�쐬
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
 * ���[���G���W���֒������M����������T�[�r�X����
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3RlsConnectorServiceImpl implements
        WEB3RlsConnectorService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsConnectorServiceImpl.class);

    /**
     * �f�o�b�Oison
     */
    private static boolean DBG = log.ison();

    /**
     * �R�l�N�^
     */
    private WEB3RuleEngineConnector connector;
    
    /**
     * �w���p�[
     */
    private WEB3RlsConnectorHelpler helper;
    
    /**
     * �r������
     */
    private Object mutex = new Object();
    
    /**
     * xTier�X�^�[�g�σt���O
     */
    private boolean isXtierStarted = false;
    
    /**
     * connector�����σt���O
     */
    private boolean isConnectorPrepared = false;
    
    /**
     * xTier�N��Try��
     */
    private long lngRetryCount = 0;
    
    /**
     * �O��try����
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
            //�R�l�N�g��������
            prepareConditions();
            
            //�V�K�������쐬
            WEB3RlsOrders l_rlsOrders = helper.createRlsOrders(l_request, l_unit);
            
            if(DBG)
            {
                log.debug("new rls orders = " + l_rlsOrders);
            }
            
            //new CondOrder �쐬
            l_newCondOrder = helper.createCondOrder(l_request, l_rlsOrders, true);

            if(DBG)
            {
                log.debug("new cond order = " + l_newCondOrder);
            }

            //�o�^
            connector.registerCondOrder(l_newCondOrder);
        }
        catch (RuleEngineConnectorException e)
        {
            //�l�b�g���[�N�ُ�̏ꍇ
            if(RuleEngineConnectorException.NETWORK_ERR == e.getErrType())
            {
                try
                {
                    //���g���C
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
            //�R�l�N�g��������
            prepareConditions();

            //�V�K�������쐬
            WEB3RlsOrders l_rlsOrders = helper.createRlsOrders(l_request, l_unit);
            
            if(DBG)
            {
                log.debug("new rls orders = " + l_rlsOrders);
            }

            //DB����ȑO�̒������Z�b�g
            l_rlsOrders = helper.addDbOrders(l_request, l_rlsOrders);

            if(DBG)
            {
                log.debug("new and old rls orders = " + l_rlsOrders);
            }

            //old CondOrder �쐬
            CondOrder l_oldCondOrder = helper.createCondOrder(l_request, l_rlsOrders, false);

            if(DBG)
            {
                log.debug("old cond order = " + l_oldCondOrder);
            }
            
            //new CondOrder �쐬
            CondOrder l_newCondOrder = helper.createCondOrder(l_request, l_rlsOrders, true);

            if(DBG)
            {
                log.debug("new cond order = " + l_newCondOrder);
            }
        
            //operation �쐬
            l_operation = new CondOrderOperationImpl(CondOrderOpType.MODIFY, l_newCondOrder, l_oldCondOrder);
        
            //����
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
            //�l�b�g���[�N�ُ�̏ꍇ
            if(RuleEngineConnectorException.NETWORK_ERR == e.getErrType())
            {
                try
                {
                    //���g���C
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
            //�R�l�N�g��������
            prepareConditions();

            //�V�K�������쐬
            WEB3RlsOrders l_rlsOrders = helper.createRlsOrders(l_request, l_unit);

            if(DBG)
            {
                log.debug("new rls orders = " + l_rlsOrders);
            }

            //DB����ȑO�̒������Z�b�g
            l_rlsOrders = helper.addDbOrders(l_request, l_rlsOrders);

            if(DBG)
            {
                log.debug("new and old rls orders = " + l_rlsOrders);
            }
        
            //old CondOrder �쐬
            CondOrder l_oldCondOrder = helper.createCondOrder(l_request, l_rlsOrders, false);

            if(DBG)
            {
                log.debug("old cond order = " + l_oldCondOrder);
            }
            
            //new CondOrder �쐬
            CondOrder l_newCondOrder = helper.createCondOrder(l_request, l_rlsOrders, true);

            if(DBG)
            {
                log.debug("new cond order = " + l_newCondOrder);
            }
        
            //operation �쐬
            l_operation = new CondOrderOperationImpl(CondOrderOpType.CANCEL, l_newCondOrder, l_oldCondOrder);
        
            //���
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
            //�l�b�g���[�N�ُ�̏ꍇ
            if(RuleEngineConnectorException.NETWORK_ERR == e.getErrType())
            {
                try
                {
                    //���g���C
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
     * Connect��������������B
     */
    protected void prepareConditions() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "prepareConditions";
        
        //xTier�X�^�[�g�ς݂��`�F�b�N
        if(! isXtierStarted)
        {
            //lock
            synchronized(mutex)
            {
                //xTier�X�^�[�g�ς݂��ă`�F�b�N
                if(! isXtierStarted)
                {
                    long l_lngCurrentTime = GtlUtils.getSystemTimestamp().getTime();

                    long l_lngCount = WEB3RlsConnectorUtil.getProperty(WEB3RlsConnectorConstants.XTIER_RETRY_SEQUENTIAL_COUNT, WEB3RlsConnectorConstants.DEFAULT_XTIER_RETRY_SEQUENTIAL_COUNT);
                    
                    //�A���J�E���g����菬�����ꍇ
                    if(lngRetryCount < l_lngCount)
                    {
                        try
                        {
                            //xTier�N��
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
                                //xTier�N��
                                startXtier();
                            }
                            finally
                            {
                                //������
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
     * xTier���N������
     */
    protected synchronized void startXtier() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "startXtier";
        try
        {
            //xTier�N��
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
        
        //connector�����ς݂��`�F�b�N
        if(! isConnectorPrepared)
        {
            //lock
            synchronized(mutex)
            {
                //xTier�N���ς݂�����
                if(isXtierStarted)
                {
                    //connector�����ς݂��ă`�F�b�N
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
     * Exception�����ɍ��킹�ăG���[�J�^���O��I������B
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
