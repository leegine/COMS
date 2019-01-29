head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.45.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteMessageHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����T�[�r�X�̊Ǘ��p���b�Z�[�W���������郁�b�Z�[�W�n���h���[(WEB3QuoteMessageHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 �R�c�@@��i(FLJ) �V�K�쐬
                 : 2005/04/28 �R�c�@@��i(FLJ) �����Ď��̊J�n�E�I��������ǉ�
*/
package webbroker3.quoteadaptor.stdimpls.handler;

import java.util.*;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.comm.client.*;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.config.ServerConfig;
import com.fitechlabs.xtrade.kernel.error.ErrorManager;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.message.Response;

import webbroker3.quoteadaptor.stdimpls.*;
import webbroker3.quoteadaptor.stdimpls.message.*;
import webbroker3.util.WEB3LogUtility;

/**
 * <p>
 * �����T�[�r�X�̊Ǘ��p���b�Z�[�W���������郁�b�Z�[�W�n���h���[�B
 * �ʏ�A�W���u��X�P�W���[�����玞���T�[�r�X���N���E��~����Ƃ��Ɏg�p�����B
 * </p>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class WEB3QuoteMessageHandler implements MessageHandler
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuoteMessageHandler.class);

    private static final boolean DBG = log.ison();

    // OK_RESPONSE
    private static final Response OK_RESPONSE = new Response();

    /**
     * �G���[���
     */
    private static ErrorInfo ERROR_INFO =
        ErrorManager.getInstance(
            WEB3QuoteMessageHandler.class).defineErrorInfo(
            "QUOTE_REQUEST_PROCESS_ERROR",
            "Failed to process the request.");

    /**
     * �G���[���
     */
    private static ErrorInfo BROADCAST_ERROR =
        ErrorManager.getInstance(
            WEB3QuoteMessageHandler.class).defineErrorInfo(
            "QUOTE_BROADCAST_ERROR",
            "Failed to process the request.");

    /**
     * �G���[���
     */
    private static ErrorInfo DUMP_ERROR =
        ErrorManager.getInstance(
            WEB3QuoteMessageHandler.class).defineErrorInfo(
            "QUOTE_DUMP_ERROR",
            "Failed to process the request.");
    
    /**
     * �G���[���
     */
    private static final ErrorInfo START_MONITORING_ERROR = 
        ErrorManager.getInstance(
            WEB3QuoteMessageHandler.class).defineErrorInfo(
                "START_QUOTE_MONITORING_ERROR",
                "Failed to process the request.");

    /**
     * �G���[���
     */
    private static final ErrorInfo STOP_MONITORING_ERROR = 
        ErrorManager.getInstance(
            WEB3QuoteMessageHandler.class).defineErrorInfo(
                "STOP_QUOTE_MONITORING_ERROR",
                "Failed to process the request.");

    /**
     * �G���[���
     */
    private static final ErrorInfo SWITCH_PROTOCOL_ERROR = 
        ErrorManager.getInstance(
            WEB3QuoteMessageHandler.class).defineErrorInfo(
                "SWITCH_QUOTE_PROTOCOL_ERROR",
                "Failed to process the request.");
    /**
     * �G���[���
     */
    private static final ErrorInfo CHANGE_RMM_DATASOURCE_ERROR = 
        ErrorManager.getInstance(
            WEB3QuoteMessageHandler.class).defineErrorInfo(
                "CHANGE_RMM_DATASOURCE_ERROR",
                "Failed to process the request.");

    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3QuoteMessageHandler()
    {
    }

    /**
     * <p>
     * WEB3StartQuoteServiceRequest���b�Z�[�W����������B
     * </p>
     * <p>
     * WEB3StartQuoteServiceRequest�Ŏw�肳�ꂽ�����T�[�r�X���N������B
     * �N�����ɃG���[�����������ꍇ�́A�G���[��񂪒ǉ����ꂽ�������b�Z�[�W��Ԃ��B
     * </p>
     * 
     * @@param req WEB3StartQuoteServiceRequest���b�Z�[�W
     * @@return �������b�Z�[�W
     */
    public Response handle(final WEB3StartQuoteServiceRequest req)
    {
        if (DBG)
        {
            log.debug("processing StartQuoteServiceRequest message");
        }

        try
        {

            WEB3QuoteDataSupplierServiceManager manager =
                getWEB3QuoteDataSupplierServiceManager();

            if (WEB3QuoteConstants.ALL_SERVICES.equals(req.service_id))
            {
                manager.startAllServices();
            } else
            {
                manager.startService(req.service_id);
            }
            return OK_RESPONSE;
        } catch (RuntimeException re)
        {
            log.warn(re.getMessage(), re);
            Response resp = new Response();
            resp.server_exception = ERROR_INFO.addText(re.getMessage());
            return resp;
        }
    }

    /**
     * <p>
     * WEB3StopQuoteServiceRequest���b�Z�[�W����������B
     * </p>
     * <p>
     * WEB3StopQuoteServiceRequest�Ŏw�肳�ꂽ�����T�[�r�X���~����B
     * ��~���ɃG���[�����������ꍇ�́A�G���[��񂪒ǉ����ꂽ�������b�Z�[�W��Ԃ��B
     * </p>
     * 
     * @@param req WEB3StartQuoteServiceRequest���b�Z�[�W
     * @@return �������b�Z�[�W
     */
    public Response handle(final WEB3StopQuoteServiceRequest req)
    {
        if (DBG)
        {
            log.debug("processing StopQuoteServiceRequest message");
        }

        try
        {

            WEB3QuoteDataSupplierServiceManager manager =
                getWEB3QuoteDataSupplierServiceManager();

            if (WEB3QuoteConstants.ALL_SERVICES.equals(req.service_id))
            {
                manager.stopAllServices();
            } else
            {
                manager.stopService(req.service_id);
            }
            return OK_RESPONSE;
        } catch (RuntimeException re)
        {
            log.warn(re.getMessage(), re);
            Response resp = new Response();
            resp.server_exception = ERROR_INFO.addText(re.getMessage());
            return resp;
        }
    }

    /**
     * <p>
     * WEB3StartQuoteServiceBroadcastRequest���b�Z�[�W����������B
     * </p>
     * <p>
     * �N���X�^�[���̑S�ẴT�[�o��ŁA
     * WEB3StartQuoteServiceBroadcastRequest�Ŏw�肳�ꂽ�����T�[�r�X���N������B
     * </p>
     * 
     * @@param req WEB3StartQuoteServiceBroadcastRequest���b�Z�[�W
     * @@return �������b�Z�[�W
     */
    public Response handle(final WEB3StartQuoteServiceBroadcastRequest req)
    {
        if (DBG)
        {
            log.debug("processing StartQuoteServiceBroadcastRequest message");
        }

        if (isHoliday())
        {
            Response resp = new Response();
            return resp;
        }

        try
        {
            String[] urls = getClusterUrls();
            if (urls == null)
            {
                Response resp = new Response();
                resp.server_exception =
                    BROADCAST_ERROR.addText("No URL is registered.");
                return resp;
            }

            Response[] responses = new Response[urls.length];
            WEB3StartQuoteServiceRequest forwardReq =
                new WEB3StartQuoteServiceRequest();
            StringBuffer errorMsgBuf = new StringBuffer(100);

            for (int i = 0; i < urls.length; i++)
            {
                // get Accessor
                ServerAccessor accessor = null;
                if (urls[i].startsWith("sockpool:"))
                {
                    accessor = new SocketPoolAccessor(urls[i]);
                } else
                {
                    accessor = new HttpServerAccessor(urls[i]);
                }

                // make a request
                forwardReq.service_id = req.service_id;

                try
                {
                    // send it
                    responses[i] = accessor.doRequest(forwardReq);
                } catch (Exception ex)
                {
                    log.warn("Failed in forwarding a quote request.", ex);
                }

                // check response		
                if (responses[i] == null
                    || responses[i].server_exception != null)
                {
                    errorMsgBuf.append(urls[i] + ", ");
                }
            }

            if (errorMsgBuf.length() == 0)
            {
                return OK_RESPONSE;
            } else
            {
                errorMsgBuf.insert(0, " Failed: ");
                String errorMsg = errorMsgBuf.toString();
                if (errorMsg.endsWith(", "))
                {
                    errorMsg = errorMsg.substring(0, errorMsg.length() - 2);
                }

                Response resp = new Response();
                resp.server_exception = BROADCAST_ERROR.addText(errorMsg);
                return resp;
            }
        } catch (Exception e)
        {
            log.error("Unexpected error", e);
            Response resp = new Response();
            resp.server_exception = BROADCAST_ERROR.addText(e.getMessage());
            return resp;
        }
    }

    /**
     * <p>
     * WEB3StopQuoteServiceBroadcastRequest���b�Z�[�W����������B
     * </p>
     * <p>
     * �N���X�^�[���̑S�ẴT�[�o��ŁA
     * WEB3StopQuoteServiceBroadcastRequest�Ŏw�肳�ꂽ�����T�[�r�X���~����B
     * </p>
     * 
     * @@param req WEB3StopQuoteServiceBroadcastRequest���b�Z�[�W
     * @@return �������b�Z�[�W
     */
    public Response handle(final WEB3StopQuoteServiceBroadcastRequest req)
    {
        if (DBG)
        {
            log.debug("processing StopQuoteServiceBroadcastRequest message");
        }

        if (isHoliday())
        {
            log.info("Today is holiday. Stop operation.");
            Response resp = new Response();
            return resp;
        }

        try
        {
            String[] urls = getClusterUrls();
            if (urls == null)
            {
                Response resp = new Response();
                resp.server_exception =
                    BROADCAST_ERROR.addText("No URL is registered.");
                return resp;
            }

            Response[] responses = new Response[urls.length];
            WEB3StopQuoteServiceRequest forwardReq =
                new WEB3StopQuoteServiceRequest();
            StringBuffer errorMsgBuf = new StringBuffer(100);

            for (int i = 0; i < urls.length; i++)
            {
                // get Accessor
                ServerAccessor accessor = null;
                if (urls[i].startsWith("sockpool:"))
                {
                    accessor = new SocketPoolAccessor(urls[i]);
                } else
                {
                    accessor = new HttpServerAccessor(urls[i]);
                }

                // make a request
                forwardReq.service_id = req.service_id;

                try
                {
                    // send it
                    responses[i] = accessor.doRequest(forwardReq);
                } catch (Exception ex)
                {
                    log.warn("Failed in forwarding a quote request.", ex);
                }

                // check response		
                if (responses[i] == null
                    || responses[i].server_exception != null)
                {
                    errorMsgBuf.append(urls[i] + ", ");
                }
            }

            if (errorMsgBuf.length() == 0)
            {
                return OK_RESPONSE;
            } else
            {
                errorMsgBuf.insert(0, " Failed: ");
                String errorMsg = errorMsgBuf.toString();
                if (errorMsg.endsWith(", "))
                {
                    errorMsg = errorMsg.substring(0, errorMsg.length() - 2);
                }

                Response resp = new Response();
                resp.server_exception = BROADCAST_ERROR.addText(errorMsg);
                return resp;
            }
        } catch (Exception e)
        {
            log.error("Unexpected error", e);
            Response resp = new Response();
            resp.server_exception = BROADCAST_ERROR.addText(e.getMessage());
            return resp;
        }
    }
    
    public Response handle(final WEB3QuoteDumpRequest request)
    {
        try
        {
            WEB3QuoteDataSupplierServiceManager manager = getWEB3QuoteDataSupplierServiceManager();
            manager.dump();
            return OK_RESPONSE;
        } catch (Exception e)
        {
            log.error("Unexpected error.", e);
            Response resp = new Response();
            resp.server_exception = DUMP_ERROR.addText(e.getMessage());
            return resp;
        }
    }
    
    public Response handle(final WEB3StartQuoteMonitoringRequest request)
    {
        try
        {
            WEB3QuoteDataSupplierServiceManager manager = getWEB3QuoteDataSupplierServiceManager();
            manager.startMonitoring();
            return OK_RESPONSE;
        } catch (Exception e)
        {
            log.error("Unexpected error.", e);
            Response resp = new Response();
            resp.server_exception = START_MONITORING_ERROR.addText(e.getMessage());
            return resp;
        }
    }

    public Response handle(final WEB3StopQuoteMonitoringRequest request)
    {
        try
        {
            WEB3QuoteDataSupplierServiceManager manager = getWEB3QuoteDataSupplierServiceManager();
            manager.stopMonitoring();
            return OK_RESPONSE;
        } catch (Exception e)
        {
            log.error("Unexpected error.", e);
            Response resp = new Response();
            resp.server_exception = STOP_MONITORING_ERROR.addText(e.getMessage());
            return resp;
        }
    }

    public Response handle(final WEB3SwitchQuoteProtocolRequest request)
    {
        try
        {
            WEB3QuoteDataSupplierServiceManager manager = getWEB3QuoteDataSupplierServiceManager();
            String l_strMessage = manager.switchDataSourceProtocol();
            
            WEB3SwitchQuoteProtocolResponse resp = new WEB3SwitchQuoteProtocolResponse();
            resp.info = l_strMessage;
            return resp;
        } catch (Exception e)
        {
            log.error("Unexpected error.", e);
            WEB3SwitchQuoteProtocolResponse resp = new WEB3SwitchQuoteProtocolResponse();
            resp.server_exception = SWITCH_PROTOCOL_ERROR.addText(e.getMessage());
            return resp;
        }
    }

    public Response handle(final WEB3ChangeRMMQuoteDataSourceRequest request)
    {
        try
        {
            WEB3QuoteDataSupplierServiceManager manager = getWEB3QuoteDataSupplierServiceManager();
            WEB3QuoteFutureData l_data = manager.changeRMMDataSource();
            String l_strMessage = l_data.getData();
            
            WEB3ChangeRMMQuoteDataSourceResponse resp = new WEB3ChangeRMMQuoteDataSourceResponse();
            resp.info = l_strMessage;
            return resp;
        } catch (Exception e)
        {
            log.error("Unexpected error.", e);
            WEB3ChangeRMMQuoteDataSourceResponse resp = new WEB3ChangeRMMQuoteDataSourceResponse();
            resp.server_exception = CHANGE_RMM_DATASOURCE_ERROR.addText(e.getMessage());
            return resp;
        }
    }

    /**
     * �Ǘ��p�T�[�r�X���擾����B
     * 
     * @@return �Ǘ��p�T�[�r�X
     */
    private WEB3QuoteDataSupplierServiceManager getWEB3QuoteDataSupplierServiceManager()
    {
        return (WEB3QuoteDataSupplierServiceManager) Services.getService(
            WEB3QuoteDataSupplierServiceManager.class);
    }

    private static String[] getClusterUrls()
    {
        try
        {
            Properties entries = ServerConfig.getConfigCategory("cluster.urls");
            if (entries == null || entries.isEmpty())
            {
                return null;
            }

            List list = new ArrayList();
            for (Enumeration e = entries.propertyNames(); e.hasMoreElements();)
            {
                String url = entries.getProperty((String) e.nextElement());
                
                if (url != null)
                {
                    list.add(url);
                }
            }
            String[] urls = (String[]) list.toArray(new String[list.size()]);
            return urls;
        } catch (DataNetworkException dne)
        {
            return null;
        }
    }

    /**
     * �x���`�F�b�N���\�b�h
     * 
     * <p>
     * JobUtil�Ŏ����T�[�r�X���N���E��~����Ƃ��A
     * �����݂̂̐ݒ���s���Ă��A
     * �J�����_�[�ɓo�^���ꂽ�x���i�Փ��j�͔���ł��Ȃ��B
     * ���̂��߁A��������N���E��~���b�Z�[�W����M������ŁA
     * �J�����_�[�e�[�u������x�������擾���A
     * �x���̏ꍇ�̓T�[�r�X�̋N���E��~���������s���Ȃ��悤�ɂ���B
     * </p>
     * 
     * @@return �x�����茋�ʁ@@true�@@�x���^�@@false ��x��
     * 
     * TODO �v�����i���݂͏��false�i��x���j��Ԃ��j
     */
    protected boolean isHoliday()
    {
        return false;
    }

}
@
