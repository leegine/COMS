head	1.4;
access;
symbols;
locks; strict;
comment	@// @;


1.4
date	2011.03.25.08.43.34;	author liu-lei;	state Exp;
branches;
next	1.3;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4444d8c55b65f67;
filename	WEB3GentradeSystemSoapConnectServiceImpl.java;

1.3
date	2011.03.25.04.36.08;	author liu-lei;	state Exp;
branches;
next	1.2;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3dc4d8c1bb82207;
filename	WEB3GentradeSystemSoapConnectServiceImpl.java;

1.2
date	2011.03.24.08.50.01;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5cc4d8b0586158f;
filename	WEB3GentradeSystemSoapConnectServiceImpl.java;

1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeSystemSoapConnectServiceImpl.java;


desc
@@


1.4
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���V�X�e��SOAP�ڑ��T�[�r�XImpl(WEB3GentradeSystemSoapConnectServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15 ������(���u) �V�K�쐬
                 : 2006/04/13 ���n�@@�a��(SCS) ��Q�Ǘ� U02821�Ή�
                 : 2006/05/09 ��  ��(���u) ��Q�Ǘ� U02828�Ή�
*/
package webbroker3.gentrade.service.delegate.stdimpls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.jaxws.core.MessageContext;
import org.apache.axis2.jaxws.handler.SoapMessageContext;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties.ProxyProperties;

import sun.net.www.protocol.http.HttpURLConnection;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SoapSendReceiveDivDef;
import webbroker3.gentrade.data.SoapConnectPrefMsgDao;
import webbroker3.gentrade.data.SoapConnectPrefMsgParams;
import webbroker3.gentrade.data.SoapConnectPrefMsgRow;
import webbroker3.gentrade.data.SoapConnectPrefRpcDao;
import webbroker3.gentrade.data.SoapConnectPrefRpcRow;
import webbroker3.gentrade.data.SoapMessageParams;
import webbroker3.gentrade.service.delegate.WEB3GentradeSystemSoapConnectService;
import webbroker3.util.WEB3LogUtility;

/**
 * �O���V�X�e��SOAP�ڑ��T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3GentradeSystemSoapConnectServiceImpl implements WEB3GentradeSystemSoapConnectService
{
    /**
     *  ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3GentradeSystemSoapConnectServiceImpl.class);

    /**
     * CONTENT_TYPE_VALUE = "text/xml;"
     */
    private static final String CONTENT_TYPE_VALUE = "text/xml;";

    /**
     * CONTENT_TYPE_KEY = "Content-Type"
     */
    private static final String CONTENT_TYPE_KEY = "Content-Type";

    /**
     * SPACE = " "
     */
    private static final String SPACE = " ";

    /**
     * CHARSET = "charset="
     */
    private static final String CHARSET = "charset=";

    /**
     * CSET_COOKIE = "set-cookie"
     */
    private static final String SET_COOKIE = "set-cookie";

    /**
     * JSESSION_ID = "JSESSIONID"
     */
    private static final String JSESSION_ID = "JSESSIONID";

    /**
     * SESSION_ID = "SessionID"
     */
    private static final String SESSION_ID = "SessionID";

    /**
     * PARAMETER_LIST_DEL = ":"
     */
    private static final String PARAMETER_LIST_DEL = ":";

    /**
     * ENDPOINT_NAME_DEL = ";"
     */
    private static final String ENDPOINT_NAME_DEL = ";";

	/**
	 * (sendMessage)<BR>
     * SOAP���b�Z�[�W�𐶐����A���b�Z�[�W�̑��M���s���B<BR>
     * <BR>
     * �V�[�P���X�}�uSOAP���b�Z�[�W���M�v �Q�ƁB<BR>
	 * @@param l_lngBranchId - ���XID
     * @@param l_strConnectDiv - �ڑ��敪
     * @@param l_strParameterlists - �p�����[�^���X�g
	 * @@throws WEB3BaseException
	 * @@return Object[]
	 * @@roseuid 421036A8039E
	 */
	public Object[] sendMessage(
        long l_lngBranchId, 
        String l_strConnectDiv, 
        String[] l_strParameterlists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "sendMessage(long, String, String[])";
        log.entering(STR_METHOD_NAME);

        try
        {
            //(*)�ȉ��̏����ŁA�O���V�X�e��SOAP�ڑ��v���t�@@�����X
            //�i���b�Z�[�W�`���j�e�[�u�����烌�R�[�h���擾
            //[����]
            //  ���XID �� ����.���XID
            //  �ڑ��敪 �� ����.�ڑ��敪
            SoapConnectPrefMsgRow l_msgRow = 
                SoapConnectPrefMsgDao.findRowByBranchIdConnectDiv(
                    l_lngBranchId, 
                    l_strConnectDiv);

            //MessageContext( )
            MessageContext l_msessageContext = new MessageContext();

            //SoapMessageContext( )
            SoapMessageContext l_soapMessageContextForSend =
                new SoapMessageContext(l_msessageContext);

            //MessageFactory( )
            MessageFactory l_messageFactory = MessageFactory.newInstance();

            //createMessage( )
            // SOAPMessage���擾����B 
            SOAPMessage l_soapMessage = l_messageFactory.createMessage();
            
            //createMessage(�O���V�X�e��SOAP�ڑ��v���t�@@�����X�i���b�Z�[�W�`���jParams, String[], SOAPMessage)
            //  (�O���V�X�e��SOAP�ڑ��T�[�r�XImpl::createMessage)
            //  �A�C�e���̒�`
            //  ���M���郁�b�Z�[�W�̓��e�𐶐�����B 
            //  [����] 
            //    �v���t�@@�����X�F �擾�����v���t�@@�����X 
            //    �p�����[�^���X�g�F ����.�p�����[�^���X�g 
            //    ���M���b�Z�[�W�F �擾����SOAPMessage 
            SoapConnectPrefMsgParams l_msgParams = new SoapConnectPrefMsgParams(l_msgRow);
            this.createMessage(
                l_msgParams,
                l_strParameterlists,
                l_soapMessage);

            //setMessage( )
            l_soapMessageContextForSend.setMessage(l_soapMessage);

            //���b�Z�[�W getEndpointName().split(arg0 : String)
            //�v���t�@@�����X.�G���h�|�C���g�𕪊�����
            //[����] 
            //arg0�F ";"
            String l_urlArr[] = l_msgParams.getEndpointName().split(";");
            String l_soapUrl = "";
            String l_logMessage = "";

            //���b�Z�[�W �v���t�@@�����X.�G���h�|�C���g�̕������ɂ�菈���𕪊򂵂܂��B
            //���b�Z�[�W �v���t�@@�����X.�G���h�|�C���g�̕��������R�̏ꍇ
            //�������ꂽ���ꂼ��̍��ڂ̓v���L�V�T�[�o�z�X�g�A�v���L�V�T�[�o�|�[�g�A���M��Ƃ���
            if (l_urlArr.length == 3)
            {
                l_soapUrl = l_urlArr[2];

                //���M�悪�A"https://"�Ŏn�܂�ꍇ�A���L�̈�����ݒ�
                //�P�Darg0:"https.proxyHost"�@@arg1:�v���L�V�T�[�o�z�X�g
                //�Q�Darg0:"https.proxyPort"�@@arg1:�v���L�V�T�[�o�|�[�g
                //�R�Darg0:"weblogic.webservice.transport.https.proxy.host"�@@arg1:�v���L�V�T�[�o�z�X�g
                //�S�Darg0:"weblogic.webservice.transport.https.proxy.port"�@@arg1:�v���L�V�T�[�o�|�[�g
                if (l_soapUrl.indexOf("https://") >= 0)
                {
                    System.setProperty("https.proxyHost", l_urlArr[0]);
                    System.setProperty("https.proxyPort", l_urlArr[1]);
                }
                //���M�悪�A"http://"�Ŏn�܂�ꍇ�A���L�̈�����ݒ�
                //�P�Darg0:"http.proxyHost"�@@arg1:�v���L�V�T�[�o�z�X�g
                //�Q�Darg0:"http.proxyPort"�@@arg1:�v���L�V�T�[�o�|�[�g
                else
                {
                    System.setProperty("http.proxyHost", l_urlArr[0]);
                    System.setProperty("http.proxyPort", l_urlArr[1]);
                }
            }
            //���b�Z�[�W �v���t�@@�����X.�G���h�|�C���g�̕��������P�̏ꍇ
            //�v���t�@@�����X.�G���h�|�C���g�𑗐M��ɂ���
            else if (l_urlArr.length == 1)
            {
                l_soapUrl = l_msgParams.getEndpointName();
            }
            //���b�Z�[�W �v���t�@@�����X.�G���h�|�C���g�̕���������L�ȊO�̏ꍇ
            //�O���V�X�e���ڑ��G���[�̗�O���X���[����
            else
            {
                l_logMessage = 
                    "�O���V�X�e��SOAP�ڑ��v���t�@@�����X(���b�Z�[�W�`��).�G���h�|�C���g��" + 
                    "�̃Z�N�V���������A�قȂ��Ă��܂��B\n" +
                    "�u[proxy-host;proxy-port;]soap-url�v�ŃZ�b�g���Ă��������B";
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_logMessage
                    );
            }

            //ByteArrayOutputStream( )
            //  �o�̓X�g���[���𐶐�����
            ByteArrayOutputStream l_outputStream = new ByteArrayOutputStream();
            
            //writeTo(arg0 : OutputStream)
            //  �o�̓X�g���[���ɑ��M���b�Z�[�W���o�͂���B 
            //  [����] 
            //      arg0�F ���������o�̓X�g���[��
            l_soapMessage.writeTo(l_outputStream);     
            
            //���b�Z�[�W insertSOAPMessage(long, String, String, String)
            //  (�O���V�X�e��SOAP�ڑ��T�[�r�XImpl::insertSOAPMessage)
            // [����] 
            //    ���XID�F ����.���XID 
            //    �ڑ��敪�F ����.�ڑ��敪 
            //    ����M�敪�F �h���M�h 
            //    ���b�Z�[�W�F �o�̓X�g���[��.toString()�̖߂�l
            this.insertSOAPMessage(
                l_lngBranchId,
                l_strConnectDiv,
                WEB3SoapSendReceiveDivDef.SEND,
                l_outputStream.toString());

            //1.14 sendMessage(BindingInfo, DefaultMessageContext)
            //  (�O���V�X�e��SOAP�ڑ��T�[�r�XImpl::sendMessage)
            //[����] 
            //  �o�C���h���F ���������o�C���h��� 
            //  ���M���b�Z�[�W�F �����������M�p���b�Z�[�W�R���e�L�X
            SoapMessageContext l_soapMessageContextForReceive = null;
            try
            {
                l_soapMessageContextForReceive =
                    this.sendMessage(
                        l_soapUrl,
                        l_msgParams,
                        l_soapMessageContextForSend);
            }
            catch (ConnectException l_connectExp)
            {
                log.debug("�O���V�X�e���ڑ��G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            catch (SocketTimeoutException l_timeoutExp)
            {
                log.debug("�ڑ��^�C���A�E�g�G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02399,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            catch (MalformedURLException l_urlExp)
            {
                log.debug("�ڑ��^�C���A�E�g�G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01053,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //���b�Z�[�W reset( )
            l_outputStream.reset();

            //getMessage( )
            SOAPMessage l_soapMessageAgain =
                l_soapMessageContextForReceive.getMessage();

            //���b�Z�[�W writeTo(arg0 : OutputStream)
            //[����] 
            //  arg0�F ���������o�̓X�g���[��
            l_soapMessageAgain.writeTo(l_outputStream);

            //insertSOAPMessage(long, String, String, String)
            //  (�O���V�X�e��SOAP�ڑ��T�[�r�XImpl::insertSOAPMessage)
            // [����] 
            //   ���XID�F ����.���XID 
            //   �ڑ��敪�F ����.�ڑ��敪 
            //   ����M�敪�F �h��M�h 
            //   ���b�Z�[�W�F �o�̓X�g���[��.toString()�̖߂�l 
            this.insertSOAPMessage(
                l_lngBranchId,
                l_strConnectDiv,
                WEB3SoapSendReceiveDivDef.RECEIVE,
                l_outputStream.toString());

            //1.19 ���b�Z�[�W getReturnValues(�O���V�X�e��SOAP�ڑ��v���t�@@�����X�i���b�Z�[�W�`���jParams, SoapMessageContext)
            //  (�O���V�X�e��SOAP�ڑ��T�[�r�XImpl::getReturnValues)
            //[����] 
            //  �v���t�@@�����X�F �擾�����v���t�@@�����X 
            //  ��M���b�Z�[�W�F sendMessage()�̖߂�l 
            Object[] l_objValues =
                this.getReturnValues(
                    l_msgParams,
                    l_soapMessageContextForReceive);

            //1.18 getReturnValue()�̖߂�l��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_objValues;
        }
        catch (DataFindException l_dfExp)
        {
            log.error(l_dfExp.getMessage(),l_dfExp);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfExp.getMessage(),
                l_dfExp);
        }
        catch (DataNetworkException l_dnwExp)
        {
            log.error(l_dnwExp.getMessage(),l_dnwExp);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnwExp.getMessage(),
                l_dnwExp);
        }
        catch (DataQueryException l_dqExp)
        {
            log.error(l_dqExp.getMessage(),l_dqExp);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqExp.getMessage(),
                l_dqExp);
        }
        catch (IOException l_ioExp)
        {
            log.error(l_ioExp.getMessage(),l_ioExp);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ioExp.getMessage(),
                l_ioExp);
        }
        catch (SOAPException l_soapExp)
        {
            log.error(l_soapExp.getMessage(),l_soapExp);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_soapExp.getMessage(),
                l_soapExp);
        }
    }

    /**
     * (rpcCall)<BR>
     * RPC������SOAP�ڑ����s���B<BR>
     * <BR>
     * �V�[�P���X�}�uSOAP_RPC�R�[���v �Q�ƁB<BR>
     * @@param l_lngBranchId - ���XID
     * @@param l_strConnectDiv - �ڑ��敪
     * @@param l_strParameterlists - �p�����[�^���X�g
     * @@throws WEB3BaseException
     * @@return Object
     * @@roseuid 421036A8039E
     */
    public Object rpcCall(
        long l_lngBranchId, 
        String l_strConnectDiv, 
        String[] l_strParameterlists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "rpcCall(long, String, String[])";
        log.entering(STR_METHOD_NAME);
        
        RPCServiceClient l_rpcServiceClient = null;
        ProxyProperties l_proxyProperties = null;
        Options l_options = null;
        
        try
        {
            //1.1(*)�ȉ��̏����ŁA�O���V�X�e��SOAP�ڑ��v���t�@@�����X
            //�iRPC�`���j�e�[�u�����烌�R�[�h���擾
            //[����]
            //  ���XID �� ����.���XID
            //  �ڑ��敪 �� ����.�ڑ��敪
            SoapConnectPrefRpcRow l_rpcRow = 
                SoapConnectPrefRpcDao.findRowByBranchIdConnectDiv(
                    l_lngBranchId, 
                    l_strConnectDiv);

            QName l_operationName =
                new QName(
                    l_rpcRow.getServiceName(),
                    l_rpcRow.getOperationName());

            int l_intCount = 0;
            String l_logMessage = "";
            String[] l_strValues = null;
            if (l_rpcRow.getParameterList() != null)
            {
                l_strValues = l_rpcRow.getParameterList().split(PARAMETER_LIST_DEL);
                l_intCount = l_strValues.length;

                if (l_intCount !=l_strParameterlists.length)
                {
                    l_logMessage = 
                        "�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���j.�p�����[�^���X�g" + 
                        "�̃Z�N�V���������A�����̐��ƈقȂ��Ă��܂��B";
                    log.debug(l_logMessage);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_logMessage
                        );
                }
            }

            //getEndpointName( )
            String l_urlArr[] = l_rpcRow.getEndpointName().split(ENDPOINT_NAME_DEL);
            String l_soapUrl = "";

            //RPCServiceClient
            l_rpcServiceClient = new RPCServiceClient();

            //getOptions( )
            l_options = l_rpcServiceClient.getOptions();

            if (l_urlArr.length == 3)
            {
                l_soapUrl = l_urlArr[2];

                if (l_soapUrl.indexOf("https://") >= 0)
                {
                    System.setProperty("https.proxyHost", l_urlArr[0]);
                    System.setProperty("https.proxyPort", l_urlArr[1]);

                    l_proxyProperties = new ProxyProperties();
                    l_proxyProperties.setProxyName(l_urlArr[0]);
                    l_proxyProperties.setProxyPort(Integer.parseInt(l_urlArr[1]));
                    l_options.setProperty(HTTPConstants.PROXY, l_proxyProperties);

                    l_logMessage = 
                        "\nhttps.proxyHost (" + l_urlArr[0] + ")" +
                        "\nhttps.proxyPort (" + l_urlArr[1] + ")";
                } else
                {
                    System.setProperty("http.proxyHost", l_urlArr[0]);
                    System.setProperty("http.proxyPort", l_urlArr[1]);

                    l_proxyProperties = new ProxyProperties();
                    l_proxyProperties.setProxyName(l_urlArr[0]);
                    l_proxyProperties.setProxyPort(Integer.parseInt(l_urlArr[1]));
                    l_options.setProperty(HTTPConstants.PROXY, l_proxyProperties);

                    l_logMessage = 
                        "\nhttp.proxyHost (" + l_urlArr[0] + ")" +
                        "\nhttp.proxyPort (" + l_urlArr[1] + ")";

                }

                log.debug(l_logMessage);
            } else if (l_urlArr.length == 1)
            {
                l_soapUrl = l_rpcRow.getEndpointName();
            } else
            {
                l_logMessage = 
                    "�O���V�X�e��SOAP�ڑ��v���t�@@�����X(RPC�`��).�G���h�|�C���g��" + 
                    "�̃Z�N�V���������A�قȂ��Ă��܂��B\n" +
                    "�u[proxy-host;proxy-port;]soap-url�v�ŃZ�b�g���Ă��������B";
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_logMessage
                    );
            }

            //getTargetNamespaceName( )
            String l_targetNamespace = 
                l_soapUrl + l_rpcRow.getTargetNamespaceName();

            //EndpointReference
            EndpointReference l_endpointReference =
                new EndpointReference(
                    l_targetNamespace);

            //setTo( )
            l_options.setTo(l_endpointReference);

            //setProperty( )
            l_options.setProperty(
                HTTPConstants.SO_TIMEOUT,
                l_rpcRow.getResponseTimeout());

            //setProperty( )
            l_options.setProperty(
                HTTPConstants.CONNECTION_TIMEOUT,
                l_rpcRow.getResponseTimeout());

            //getClass
            Class[] returnTypes = new Class[] { l_rpcRow.getResponseParamType().getClass() };

            //1.16  invoke(arg0 : �_���r���[::java::lang::Object[])
            //[����] 
            //  arg0�F ����.�p�����[�^���X�g 
            Object l_objValue =
                l_rpcServiceClient.invokeBlocking(
                    l_operationName,
                    l_strParameterlists,
                    returnTypes);

            //cleanupTransport( )
            l_rpcServiceClient.cleanupTransport();

            //invokeBlocking�̖߂�l��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_objValue;        
        }
        catch (DataFindException l_dfExp)
        {
            log.error(l_dfExp.getMessage(),l_dfExp);
    
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfExp.getMessage(),
                l_dfExp);
        }
        catch (DataNetworkException l_dnwExp)
        {
            log.error(l_dnwExp.getMessage(),l_dnwExp);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnwExp.getMessage(),
                l_dnwExp);
        }
        catch (DataQueryException l_dqExp)
        {
            log.error(l_dqExp.getMessage(),l_dqExp);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqExp.getMessage(),
                l_dqExp);
        }
        catch (RemoteException l_rmExp)
        {
            log.error(l_rmExp.getMessage(),l_rmExp);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_rmExp.getMessage(),
                l_rmExp);
        }
    }

    /**
     * (sendMessage)<BR>
     * SOAP���b�Z�[�W�𑗐M���A�������b�Z�[�W����M����B<BR>
     * <BR>
     * �V�[�P���X�}�u���b�Z�[�W����M�v �Q�ƁB<BR>
     * @@param l_soapUrl - URL
     * @@param l_msgParams - �O���V�X�e��SOAP�ڑ��v���t�@@�����X
     * @@param l_soapMessageContextForSend - SOAP���b�Z�[�W�R���e�L�X�g
     * @@throws WEB3BaseException
     * @@return Object
     * @@roseuid 421036A8039E
     */
    protected SoapMessageContext sendMessage(
        String l_soapUrl,
        SoapConnectPrefMsgParams l_msgParams,
        SoapMessageContext l_soapMessageContextForSend)
        throws WEB3BaseException, ConnectException, SocketTimeoutException, MalformedURLException
    {
        final String STR_METHOD_NAME = "sendMessage(BindingInfo, DefaultMessageContext)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //Creates URL
            //[����]
            //  arg0�F �v���t�@@�����X.�G���h�|�C���g
            URL l_url = new URL(l_soapUrl);
    
            //openConnection( )
            HttpURLConnection l_httpURLConnection =

                (HttpURLConnection)l_url.openConnection();
            //send( )
            this.send(
                l_httpURLConnection,
                l_msgParams,
                l_soapMessageContextForSend);

            //SoapMessageContext
            SoapMessageContext l_soapMessageContextForReceive = null;
            
            //receive( )
            l_soapMessageContextForReceive =
                this.receive(
                    l_httpURLConnection,
                    l_soapMessageContextForSend,
                    l_soapMessageContextForReceive);

            //1.8 ��M�������b�Z�[�W��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_soapMessageContextForReceive;  
        }
        catch (IOException l_ioExp)
        {
            log.error(l_ioExp.getMessage(),l_ioExp);

            if (l_ioExp instanceof ConnectException)
            {
                //�O���V�X�e���ڑ��G���[
                throw new ConnectException(l_ioExp.getMessage());
            }
            else if (l_ioExp instanceof SocketTimeoutException)
            {
                //�ڑ��^�C���A�E�g�G���[
                throw new SocketTimeoutException(l_ioExp.getMessage());
            }
            else if (l_ioExp instanceof MalformedURLException)
            {
                //URL�G���[
                throw new MalformedURLException(l_ioExp.getMessage());
            }
            else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ioExp.getMessage(),
                    l_ioExp);
            }
        }
        catch (SOAPException l_soapExp)
        {
            log.error(l_soapExp.getMessage(),l_soapExp);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_soapExp.getMessage(),
                l_soapExp);
        }
    }

    /**
     * (createMessage)<BR>
     * ���M����SOAP���b�Z�[�W�𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}�u���M���b�Z�[�W�����v �Q�ƁB<BR>
     * @@param l_prefMsgParams - �v���t�@@�����X
     * @@param l_strParameterlists - �p�����[�^���X�g
     * @@param l_soapMessage - ���M���b�Z�[�W
     * @@throws WEB3BaseException
     * @@return Object
     * @@roseuid 421036A8039E
     */
    protected void createMessage(
        SoapConnectPrefMsgParams l_prefMsgParams, 
        String[] l_strParameterlists, 
        SOAPMessage l_soapMessage) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createMessage(SoapConnectPrefMsgParams, String[], SOAPMessage)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //1.1 getSOAPPart()
            SOAPPart l_soapPart = l_soapMessage.getSOAPPart();

            //1.2 getEnvelope()
            SOAPEnvelope l_soapEnvelope = l_soapPart.getEnvelope();

            //1.3 addNamespaceDeclaration(arg0 : String, arg1 : String)
            // [����]
            // arg0�F ����.�v���t�@@�����X.�ړ���
            // arg1�F ����.�v���t�@@�����X.�^�[�Q�b�g�l�[���X�y�[�X��
            l_soapEnvelope.addNamespaceDeclaration(
                l_prefMsgParams.getPrefix(),
                l_prefMsgParams.getTargetNamespaceName());

            //1.4 getBody( )
            SOAPBody l_soapBody = l_soapEnvelope.getBody();

            //1.5 createName(arg0 : String, arg1 : String, arg2 : String)
            //[����] 
            //  arg0�F ����.�v���t�@@�����X.�I�y���[�V������ 
            //  arg1�F ����.�v���t�@@�����X.�ړ��� 
            //  arg2�F ����.�v���t�@@�����X.�^�[�Q�b�g�l�[���X�y�[�X�� 
            Name l_name = l_soapEnvelope.createName(
                l_prefMsgParams.getOperationName(), 
                l_prefMsgParams.getPrefix(),
                l_prefMsgParams.getTargetNamespaceName());

            //1.6 addBodyElement(arg0 : Name)
            //Body�G�������g��ǉ�����B 
            //[����] 
            //  arg0�F ���������I�y���[�V������Name�I�u�W�F�N�g
            SOAPElement l_soapElementOperation = l_soapBody.addBodyElement(l_name);

            //1.7 )����.�p�����[�^���X�g�̊e�v�f�ɂ���Loop����
            int l_intCount = 0;
            if (l_strParameterlists != null)
            {
                l_intCount = l_strParameterlists.length;
            }
            for (int i = 0; i < l_intCount; i++)
            {
                //1.7.1 createName(arg0 : String, arg1 : String, arg2 : String)
                //�p�����[�^�p��Name�C���X�^���X�𐶐�����B 
                //[����] 
                //  arg0�F ����.�v���t�@@�����X.�p�����[�^���X�g.split(":") �̖߂�l�̊Y������v�f 
                //  arg1�F ����.�v���t�@@�����X.�ړ��� 
                //  arg2�F ����.�v���t�@@�����X.�^�[�Q�b�g�l�[���X�y�[�X�� 
                Name l_nameIndex = l_soapEnvelope.createName(
                    l_prefMsgParams.getParameterList().split(":")[i],
                    l_prefMsgParams.getPrefix(),
                    l_prefMsgParams.getTargetNamespaceName());

                //1.7.2 addChildElement(arg0 : String)
                //Child�G�������g��ǉ�����B 
                //[����] 
                //  arg0�F ���������p�����[�^��Name�I�u�W�F�N�g 
                SOAPElement l_soapElement = l_soapElementOperation.addChildElement(l_nameIndex);

                //1.7.3  addTextNode(arg0 : String)
                //[����] 
                //  arg0�F ����.�p�����[�^���X�g�̗v�f 
                l_soapElement.addTextNode(l_strParameterlists[i]);
            }

            //1.8 getMimeHeaders( )
            //SOAPMessage����MimeHeaders�C���X�^���X���擾����B
            MimeHeaders l_mimeHeaders = l_soapMessage.getMimeHeaders();

            //1.9 setHeader(arg0 : String, arg1 : String)
            //[����] 
            //  arg0�F "SOAPAction" 
            //  arg1�F ����.�v���t�@@�����X.SOAPAction 
            l_mimeHeaders.setHeader("SOAPAction", l_prefMsgParams.soap_action);

            //1.10 saveChanges( )
            l_soapMessage.saveChanges();

            log.exiting(STR_METHOD_NAME);
        }
        catch (SOAPException l_soapExp)
        {
            log.error(l_soapExp.getMessage(),l_soapExp);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_soapExp.getMessage(),
                l_soapExp);
        }
    }

    /**
     * (getReturnValues)<BR>
     * ��M����SOAP���b�Z�[�W����߂�l���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u��M���b�Z�[�W��́v �Q�ƁB<BR>
     * <BR>
     * @@param l_prefMsgParams - �v���t�@@�����X
     * @@param l_soapMessageContextForReceive - SOAP���b�Z�[�W�R���e�L�X�g
     * @@throws WEB3BaseException
     * @@return Object
     * @@roseuid 421036A8039E
     */
    protected Object[] getReturnValues(
        SoapConnectPrefMsgParams l_prefMsgParams, 
        SoapMessageContext l_soapMessageContextForReceive) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getReturnValues(SoapConnectPrefMsgParams, DefaultMessageContext)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1.1  getMessage( )
            SOAPMessage l_soapMessage =
                l_soapMessageContextForReceive.getMessage();
                
            //1.2 getSOAPPart( )
            SOAPPart l_soapPart = l_soapMessage.getSOAPPart();
            
            //1.3 getEnvelope( )
            SOAPEnvelope l_soapEnvelope = l_soapPart.getEnvelope();

            //1.4 getBody( )
            SOAPBody l_soapBody = l_soapEnvelope.getBody();

            //1.5 createName(arg0 : String, arg1 : String, arg2 : String)
            //���X�|���X�ɂ��Ă�Name�C���X�^���X�𐶐�����
            // [����] 
            //    arg0�F ����.�v���t�@@�����X.���X�|���X�� 
            //    arg1�F ����.�v���t�@@�����X.�ړ��� 
            //    arg2�F ����.�v���t�@@�����X.�^�[�Q�b�g�l�[���X�y�[�X�� 
            Name l_responseName = l_soapEnvelope.createName(
                l_prefMsgParams.getResponseName(),
                l_prefMsgParams.getPrefix(),
                l_prefMsgParams.getTargetNamespaceName());

            //1.6 getChildElements(arg0 : Name)
            //���X�|���X�̃G�������g���擾����
            //[����] 
            //  arg0�F �����������X�|���X��Name�I�u�W�F�N�g 
            SOAPElement l_soapBodyElement = (SOAPElement)(l_soapBody.getChildElements(l_responseName).next());

            //1.7 ArrayList( )
            List l_lisArray = new ArrayList();
            
            //1.8 (*1)����.�v���t�@@�����X.���X�|���X�p�����[�^���X�g.split(":") �̖߂�l
            //�̊e�v�f�ɂ���Loop����
            String[] l_strValues = l_prefMsgParams.getResponseParamList().split(":");
            
            for (int i = 0; i < l_strValues.length; i++)
            {
                //1.8.1 createName(arg0 : String, arg1 : String, arg2 : String)
                //���X�|���X�p�����[�^�p��Name�C���X�^���X�𐶐�����
                //[����] 
                //   arg0�F ����.�v���t�@@�����X.���X�|���X�p�����[�^���X�g.split(":") �̖߂�l�̊Y������v�f 
                //   arg1�F ����.�v���t�@@�����X.�ړ��� 
                //   arg2�F ����.�v���t�@@�����X.�^�[�Q�b�g�l�[���X�y�[�X��
                Name l_parameterNameIndex = l_soapEnvelope.createName(
                    l_strValues[i],
                    l_prefMsgParams.getPrefix(),
                    l_prefMsgParams.getTargetNamespaceName());

                //1.8.2 getChildElements(arg0 : Name)
                //���X�|���X�p�����[�^�̃G�������g���擾����
                //[����] 
                //  arg0�F �����������X�|���X�p�����[�^��Name�I�u�W�F�N�g 
                Iterator l_iterator = l_soapBodyElement.getChildElements(l_parameterNameIndex);

                //1.8.3  (*2)Iterator�̗v�f��Loop����
                while (l_iterator.hasNext())
                {
                    //1.8.3.1 next()
                    Object l_objValue = l_iterator.next();

                    //1.8.3.2  add(arg0 : Object)
                    //[����] 
                    //  arg0�F �inext()�̖߂�l�j.getValue()�̖߂�l
                    if (l_objValue instanceof SOAPElement)
                    {
                        SOAPElement l_soapElement = (SOAPElement)l_objValue;
                        l_lisArray.add(l_soapElement.getValue());
                    }
                }
            }
            
            //1.9  toArray( )
            //�擾�����z���ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_lisArray.toArray();
        }
        catch (SOAPException l_soapExp)
        {
            log.error(l_soapExp.getMessage(),l_soapExp);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_soapExp.getMessage(),
                l_soapExp);
        }
    }

    /**
     * (insertSOAPMessage)<BR>
     * SOAP���b�Z�[�W�ۑ��e�[�u����SOAP���b�Z�[�W��ۑ�����B<BR>
     * <BR>
     * �P�jSOAP���b�Z�[�W�ۑ�Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j���������C���X�^���X�ɁA�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     * ���XID�F ����.���XID<BR>
     * �ڑ��敪�F ����.�ڑ��敪<BR>
     * ����M�敪�F ����.����M�敪<BR>
     * ���b�Z�[�W�F ����.���b�Z�[�W<BR>
     * �쐬���t�F �V�X�e���^�C���X�^���v<BR>
     * <BR>
     * �R�j�e�[�u���ɃC���T�[�g����B<BR>
     * @@param l_lngBranchId - ���XID
     * @@param l_strConnectDiv - �ڑ��敪
     * @@param l_strSendReceiveDiv - ����M�敪
     * @@param l_strMessage - ���b�Z�[�W
     * @@throws WEB3BaseException
     * @@return Object
     * @@roseuid 421036A8039E
     */
    protected void insertSOAPMessage(
        long l_lngBranchId, 
        String l_strConnectDiv, 
        String l_strSendReceiveDiv,
        String l_strMessage) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertSOAPMessage(long, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jSOAP���b�Z�[�W�ۑ�Params�C���X�^���X�𐶐�����B
        SoapMessageParams l_soapMessageParams = new SoapMessageParams();
        
        //�Q�j���������C���X�^���X�ɁA�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
        //���XID�F ����.���XID
        l_soapMessageParams.setBranchId(l_lngBranchId);
        
        //�ڑ��敪�F ����.�ڑ��敪
        l_soapMessageParams.setConnectDiv(l_strConnectDiv);
        
        //����M�敪�F ����.����M�敪
        l_soapMessageParams.setSendReceiveDiv(l_strSendReceiveDiv);
        
        //���b�Z�[�W�F ����.���b�Z�[�W
        l_soapMessageParams.setSoapMessage(l_strMessage);
        
        //�쐬���t�F �V�X�e���^�C���X�^���v
        l_soapMessageParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {
            //�R�j�e�[�u���ɃC���T�[�g����B
			// TransactionCallback����
			SystemConnectCallback l_systemConnectCallback = 
				new SystemConnectCallback(l_soapMessageParams);

			//�N�G���v���Z�b�T���擾����B
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			
			//DB�g�����U�N�V�������������{����B 
			//[����] 
			//�g�����U�N�V���������F�@@TX_CREATE_NEW 
			//�g�����U�N�V�����R�[���o�b�N�F�@@�O���V�X�e���ڑ�TransactionCallback�C���X�^���X 

			l_queryProcessor.doTransaction(
				QueryProcessor.TX_CREATE_NEW,
				l_systemConnectCallback
				);
        }
        catch (DataQueryException l_dqExp)
        {
            log.error(l_dqExp.getMessage(),l_dqExp);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqExp.getMessage(),
                l_dqExp);
        }
        catch (DataNetworkException l_dnwExp)
        {
            log.error(l_dnwExp.getMessage(),l_dnwExp);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnwExp.getMessage(),
                l_dnwExp);
        }

        log.exiting(STR_METHOD_NAME);
    }

	/**
	 * (�O���V�X�e���ڑ�TransactionCallback) <BR>
	 * <BR>
	 * �O���V�X�e���ڑ�TransactionCallback�N���X<BR>
	 */
	public class SystemConnectCallback implements TransactionCallback
	{

		/**
		 * SOAP���b�Z�[�W�ۑ�Params<BR>
		 * �C���T�[�g�p�����^<BR>
		 */
		private SoapMessageParams soapMessageParams;

		/**
		 * �R���X�g���N�^<BR>
		 * <BR>
		 * ����.SOAP���b�Z�[�W�ۑ�Params�𑮐�.�C���T�[�g�p�����^�ɕۑ�����B<BR>
		 * <BR>
	     * @@param l_soapMessageParams SOAP���b�Z�[�W�ۑ�Params
		 */
		public SystemConnectCallback(SoapMessageParams l_soapMessageParams)
		{
			this.soapMessageParams = l_soapMessageParams;
		}

		/**
		 * ����.�C���T�[�g�p�����^�ŁA�e�[�u���ɃC���T�[�g����B<BR>
		 * @@return Object
		 * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
		 * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
		 * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
		 */
		public Object process()
			throws DataNetworkException, DataQueryException, DataCallbackException
		{
			final String STR_METHOD_NAME = "process()";
			log.entering(STR_METHOD_NAME);

			SoapMessageParams l_soapMessageParams = this.soapMessageParams;

			//SOAP���b�Z�[�W�ۑ�Params �̓��e��DB�ɍs��}���iinsert�j����B
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_queryProcessor.doInsertQuery(l_soapMessageParams);

			log.exiting(STR_METHOD_NAME);
			return null;
		}
	}

    private void send(
        HttpURLConnection l_httpURLConnection,
        SoapConnectPrefMsgParams l_msgParams,
        SoapMessageContext l_soapMessageContextForSend)
        throws IOException, SOAPException
    {
        final String STR_METHOD_NAME =
            "send(HttpURLConnection, SoapConnectPrefMsgParams, SoapMessageContext)";
        log.entering(STR_METHOD_NAME);

        //
        SOAPMessage l_soapMessage = l_soapMessageContextForSend.getMessage();

        //setDoInput(arg0 : boolean)
        l_httpURLConnection.setDoInput(true);

        //setDoOutput(arg0 : boolean)
        l_httpURLConnection.setDoOutput(true);

        //setConnectTimeout(arg0 : int)
        //[����]
        //  arg0�F �v���t�@@�����X.�ڑ��^�C���A�E�g����
        l_httpURLConnection.setConnectTimeout(
            Integer.valueOf(l_msgParams.getResponseTimeout()).intValue());

        //getMimeHeaders( )
        MimeHeaders l_mimeHeaders = l_soapMessage.getMimeHeaders();
        
        //Content-Type�쐬
        String l_strContentType =
            CONTENT_TYPE_VALUE + SPACE + CHARSET + l_msgParams.getCharset();

        //HTTP�w�b�_�[�쐬
        l_httpURLConnection.setRequestProperty(CONTENT_TYPE_KEY, l_strContentType);
        if(l_mimeHeaders != null)
        {
            for(Iterator iterator = l_mimeHeaders.getAllHeaders(); iterator.hasNext();)
            {
                MimeHeader mimeheader = (MimeHeader)iterator.next();
                if (!CONTENT_TYPE_KEY.equals(mimeheader.getName()))
                {
                    l_httpURLConnection.setRequestProperty(
                        mimeheader.getName(),
                        mimeheader.getValue());
                }
            }
        }
        
        //getOutputStream( )
        OutputStream outputStream = l_httpURLConnection.getOutputStream();

        //writeTo( )
        l_soapMessage.writeTo(outputStream);

        log.exiting(STR_METHOD_NAME);
    }

    private SoapMessageContext receive(
        HttpURLConnection l_httpURLConnection,
        SoapMessageContext l_soapMessageContextForSend,
        SoapMessageContext l_soapMessageContextForReceive)
        throws SOAPException, IOException
    {
        final String STR_METHOD_NAME =
            "receive(HttpURLConnection, SoapMessageContext)";
        log.entering(STR_METHOD_NAME);

        //MessageContext( )
        MessageContext l_msessageContext = new MessageContext();

        //getHeaderField( )
        String l_headerField =
            l_httpURLConnection.getHeaderField(SET_COOKIE);

        if ((l_headerField != null) && (l_headerField.startsWith(JSESSION_ID)))
        {
            l_msessageContext.setProperty(SESSION_ID, l_headerField);
        }

        //SoapMessageContext
        l_soapMessageContextForReceive =
            new SoapMessageContext(l_msessageContext);

        //getStream( )
        InputStream l_inputstream = this.getStream(l_httpURLConnection);

        //MessageFactory
        MessageFactory l_messageFactory = MessageFactory.newInstance();
        
        //getMimeHeaders( )
        MimeHeaders l_mimeHeaders = this.getMimeHeaders(l_httpURLConnection);

        //setMessage( )
        l_soapMessageContextForReceive.setMessage(
            l_messageFactory.createMessage(l_mimeHeaders, l_inputstream));

        log.exiting(STR_METHOD_NAME);
        return l_soapMessageContextForReceive;
    }
    
    private InputStream getStream(HttpURLConnection l_httpURLConnection)
    {
        InputStream l_inputstream = null;
        try
        {
            l_inputstream = l_httpURLConnection.getInputStream();
        }
        catch(IOException ioexception)
        {
            l_inputstream = l_httpURLConnection.getErrorStream();
        }
        return l_inputstream;
    }
    
    private MimeHeaders getMimeHeaders(
            HttpURLConnection l_httpURLConnection)
    {
        MimeHeaders mimeheaders = new MimeHeaders();
        int i = 1;
        String s = null;
        while((s = l_httpURLConnection.getHeaderFieldKey(i)) != null) 
        {
            String s1 = l_httpURLConnection.getHeaderField(i++);
            if(s1 != null && s1.length() != 0)
                mimeheaders.setHeader(s, s1);
        }
        return mimeheaders;
    }
}
@


1.3
log
@*** empty log message ***
@
text
@d417 4
d464 5
a468 3
            RPCServiceClient l_rpcServiceClient = null;
            ProxyProperties l_proxyProperties = null;
            Options l_options = null;
a471 6
                //RPCServiceClient
                l_rpcServiceClient = new RPCServiceClient();

                //getOptions( )
                l_options = l_rpcServiceClient.getOptions();

d645 5
a649 4
            this.receive(
                l_httpURLConnection,
                l_soapMessageContextForSend,
                l_soapMessageContextForReceive);
d1121 1
a1121 1
    private void receive(
d1153 2
a1154 2
        MimeHeaders l_mimeHeaders =
            l_soapMessageContextForSend.getMessage().getMimeHeaders();
d1161 1
d1176 15
@


1.2
log
@*** empty log message ***
@
text
@d13 2
d16 1
d18 1
d25 2
a26 1

d36 10
a68 1

d84 45
d148 1
a148 1
        
d151 1
a151 1
            //1.1(*)�ȉ��̏����ŁA�O���V�X�e��SOAP�ڑ��v���t�@@�����X
d160 12
a171 5
                
            //1.2 DefaultMessageContext( )
            DefaultMessageContext l_defaultMsessageContext = new DefaultMessageContext();
            
            //1.3 getMessage( )
d173 1
a173 1
            SOAPMessage l_soapMessage = l_defaultMsessageContext.getMessage();
d175 1
a175 1
            //1.4 createMessage(�O���V�X�e��SOAP�ڑ��v���t�@@�����X�i���b�Z�[�W�`���jParams, String[], SOAPMessage)
d184 4
a187 1
            this.createMessage(l_msgParams, l_strParameterlists, l_soapMessage);
d189 2
a190 10
            //1.5  BindingInfo()
            //�A�C�e���̒�`
            //�o�C���h���̃C���X�^���X�𐶐�����B
            BindingInfo l_bindingInfo = new BindingInfo();
            
            //1.6 ���b�Z�[�W setCharset(arg0 : String)
            //���M���b�Z�[�W�̃L�����N�^��ݒ肷��B 
            //[����] 
            //  arg0�F �v���t�@@�����X.�L�����N�^�[�Z�b�g 
            l_bindingInfo.setCharset(l_msgParams.getCharset());
d192 1
a192 1
            //1.7 ���b�Z�[�W getEndpointName().split(arg0 : String)
d200 2
a201 2
            //1.8 ���b�Z�[�W �v���t�@@�����X.�G���h�|�C���g�̕������ɂ�菈���𕪊򂵂܂��B
            //1.8.1 ���b�Z�[�W �v���t�@@�����X.�G���h�|�C���g�̕��������R�̏ꍇ
a215 21
                    System.setProperty("weblogic.webservice.transport.https.proxy.host", l_urlArr[0]);
                    System.setProperty("weblogic.webservice.transport.https.proxy.port", l_urlArr[1]);

                  l_logMessage = 
                  "\nhttps.proxyHost (" + l_urlArr[0] + ")" +
                  "\nhttps.proxyPort (" + l_urlArr[1] + ")" +
                  "\nweblogic.webservice.transport.https.proxy.host (" + l_urlArr[0] + ")" +
                  "\nweblogic.webservice.transport.https.proxy.port (" + l_urlArr[1] + ")";
                    log.debug(l_logMessage);

                    log.debug("���ݒ�O��");
                    log.debug("SSLProxyHost: " + HttpsClient.SSLProxyHost);
                    log.debug("SSLProxyPort: " + HttpsClient.SSLProxyPort);

                    //proxy�̍Đݒ�
                    HttpsClient.resetSSLProperties();
                    
                    log.debug("���ݒ�い");
                    log.debug("SSLProxyHost: " + HttpsClient.SSLProxyHost);
                    log.debug("SSLProxyPort: " + HttpsClient.SSLProxyPort);

a219 2
                //�R�Darg0:"weblogic.webservice.transport.http.proxy.host"�@@arg1:�v���L�V�T�[�o�z�X�g
                //�S�Darg0:"weblogic.webservice.transport.http.proxy.port"�@@arg1:�v���L�V�T�[�o�|�[�g
a223 20
                    System.setProperty("weblogic.webservice.transport.http.proxy.host", l_urlArr[0]);
                    System.setProperty("weblogic.webservice.transport.http.proxy.port", l_urlArr[1]);

                  l_logMessage = 
                      "\nhttp.proxyHost (" + l_urlArr[0] + ")" +
                      "\nhttp.proxyPort (" + l_urlArr[1] + ")" +
                      "\nweblogic.webservice.transport.http.proxy.host (" + l_urlArr[0] + ")" +
                      "\nweblogic.webservice.transport.http.proxy.port (" + l_urlArr[1] + ")";
                    log.debug(l_logMessage);

                    log.debug("���ݒ�O��");
                    log.debug("proxyHost: " + HttpClient.proxyHost);
                    log.debug("proxyPortt: " + HttpClient.proxyPort);

                    //proxy�̍Đݒ�
                    HttpClient.resetProperties();
                    
                    log.debug("���ݒ�い");
                    log.debug("proxyHost: " + HttpClient.proxyHost);
                    log.debug("proxyPortt: " + HttpClient.proxyPort);
a224 1

d226 1
a226 1
            //1.8.2 ���b�Z�[�W �v���t�@@�����X.�G���h�|�C���g�̕��������P�̏ꍇ
d232 1
a232 1
            //1.8.3 ���b�Z�[�W �v���t�@@�����X.�G���h�|�C���g�̕���������L�ȊO�̏ꍇ
d247 1
a247 15
            //1.9 ���b�Z�[�W setAddress(arg0 : String)
            //���M���b�Z�[�W�̑��M���ݒ肷��B 
            //[����] 
            //arg0�F �v���t�@@�����X.�G���h�|�C���g
            l_bindingInfo.setAddress(l_soapUrl);

            //1.10 setProperty(arg0 : String, arg1 : Object)
            //[����] 
            //  arg0�F "weblogic.webservice.rpc.timeoutsecs" 
            //  arg1�F �v���t�@@�����X.�ڑ��^�C���A�E�g����
            l_defaultMsessageContext.setProperty(
                "weblogic.webservice.rpc.timeoutsecs",
                l_msgParams.getResponseTimeout());
            
            //1.11 ByteArrayOutputStream( )
d251 1
a251 1
            //1.12 writeTo(arg0 : OutputStream)
d255 1
a255 1
            l_soapMessage.writeTo(l_outputStream);
d257 1
a257 1
            //1.13 ���b�Z�[�W insertSOAPMessage(long, String, String, String)
d275 1
a275 1
            SOAPMessageContext l_soapMessageContext = null;
d278 5
a282 1
                l_soapMessageContext = this.sendMessage(l_bindingInfo, l_defaultMsessageContext);
d298 7
d306 1
a306 1
            //1.15 ���b�Z�[�W reset( )
a307 3
            
            //1.16 getMessage( )
            SOAPMessage l_soapMessageAgain = l_soapMessageContext.getMessage();
d309 5
a313 1
            //1.17 ���b�Z�[�W writeTo(arg0 : OutputStream)
d317 2
a318 2
            
            //1.18 insertSOAPMessage(long, String, String, String)
d330 2
a331 2
            
            //1.19 ���b�Z�[�W getReturnValues(�O���V�X�e��SOAP�ڑ��v���t�@@�����X�i���b�Z�[�W�`���jParams, DefaultMessageContext)
d336 4
a339 1
            Object[] l_objValues = this.getReturnValues(l_msgParams, (DefaultMessageContext)l_soapMessageContext);
d341 1
a341 1
            //1.18 getReturnValue()�̖߂�l��ԋp����B 
d429 4
a432 22
            //1.2 ���b�Z�[�W setProperty(arg0 : String, arg1 : String)
            // [����] 
            //   arg0�F "javax.xml.rpc.ServiceFactory" 
            //   arg1�F "weblogic.webservice.core.rpc.ServiceFactoryImpl" 
            System.setProperty("javax.xml.rpc.ServiceFactory", "weblogic.webservice.core.rpc.ServiceFactoryImpl");

            //1.3 ���b�Z�[�W newInstance( )
            // �A�C�e���̒�`
            // �T�[�r�X�t�@@�N�g���𐶐�����B 
            ServiceFactory l_serviceFactory = ServiceFactory.newInstance();

            //1.4 ���b�Z�[�W QName(arg0 : String, arg1 : String)
            //[����] 
            //  arg0�F targetNamespace 
            //  arg1�F �v���t�@@�����X.�T�[�r�X��
            QName l_serviceName = new QName(l_rpcRow.getTargetNamespaceName(), l_rpcRow.getServiceName());
            
            //1.5 ���b�Z�[�W QName(arg0 : String, arg1 : String)
            //[����] 
            //  arg0�F targetNamespace 
            //  arg1�F �v���t�@@�����X.�|�[�g�^�C�v�� 
            QName l_portTypeName = new QName(l_rpcRow.getTargetNamespaceName(), l_rpcRow.getPortTypeName());
a433 27
            //1.6 ���b�Z�[�W QName(arg0 : String, arg1 : String)
            //[����] 
            //  arg0�F �v���t�@@�����X.�T�[�r�X�� 
            //  arg1�F �v���t�@@�����X.�I�y���[�V������ 
            QName l_operationName = new QName(l_rpcRow.getServiceName(), l_rpcRow.getOperationName());
            
            //1.7 ���b�Z�[�W createService(arg0 : QName)
            //[����] 
            //  arg0�F �ݒ肵���T�[�r�X���i�@@�j 
            l_serviceFactory.createService(l_serviceName);
            
            //1.8 ���b�Z�[�W createCall( )
            //  Call�I�u�W�F�N�g�𐶐�����B
            Service l_service = l_serviceFactory.createService(l_serviceName);
            Call l_call = l_service.createCall();
            
            //1.9 setPortTypeName(arg0 : QName)
            //[����] 
            //  arg0�F �|�[�g�^�C�v���i�A�j 
            l_call.setPortTypeName(l_portTypeName);
            
            //1.10 setOperationName(arg0 : QName)
            //[����] 
            //  arg0�F �I�y���[�V�������i�B�j
            l_call.setOperationName(l_operationName);

            //1.11 �v���t�@@�����X.�p�����[�^���X�g.split(":") �̖߂�l�̊e�v�f�ɂ���Loop����
d435 1
d439 1
a439 1
                l_strValues = l_rpcRow.getParameterList().split(":");
a440 1
            }
d442 12
a453 20
            for (int i = 0; i < l_intCount; i++)
            {
                //1.11.1 QName(arg0 : String, arg1 : String)
                //�p�����[�^�̃l�[���X�y�[�X���Z�b�g����
                //[����] 
                //  arg0�F "http://www.w3.org/2001/XMLSchema" 
                //  arg1�F �v���t�@@�����X.�p�����[�^�^�C�v���X�g[i]  
                QName l_parameterNameSpace = new QName(
                    "http://www.w3.org/2001/XMLSchema",
                    l_rpcRow.getParameterTypeList().split(":")[i]);

                //1.11.2  addParameter(arg0 : String, arg1 : QName, arg2 : ParameterMode)
                //[����] 
                //  arg0�F �v���t�@@�����X.�p�����[�^���X�g[i] 
                //  arg1�F �p�����[�^�^�C�v�̃l�[���X�y�[�X 
                //  arg2�F ParameterMode.IN
                l_call.addParameter(
                    l_strValues[i],
                    l_parameterNameSpace,
                    ParameterMode.IN);
d456 3
a458 13
            //1.12 QName(arg0 : String, arg1 : String)
            //�߂�l�̃l�[���X�y�[�X���Z�b�g����
            // [����] 
            //    arg0�F "http://www.w3.org/2001/XMLSchema" 
            //    arg1�F �v���t�@@�����X.���X�|���X�p�����[�^�^�C�v
            QName l_nameSpace = new QName(
                "http://www.w3.org/2001/XMLSchema",
                l_rpcRow.getResponseParamType());

            //1.13 setReturnType(arg0 : QName)
            //[����] 
            //  arg0�F �߂�l�̃l�[���X�y�[�X
            l_call.setReturnType(l_nameSpace);
d460 3
a462 6
            //1.14 setTargetEndpointAddress(arg0 : String)
            //[����] 
            //  arg0�F �v���t�@@�����X.�G���h�|�C���g��
            String l_logMessage = "";
            String l_urlArr[] = l_rpcRow.getEndpointName().split(";");
            String l_soapUrl = "";
d466 6
d478 6
a483 2
                    System.setProperty("weblogic.webservice.transport.https.proxy.host", l_urlArr[0]);
                    System.setProperty("weblogic.webservice.transport.https.proxy.port", l_urlArr[1]);
d486 1
a486 3
                        "\nhttps.proxyPort (" + l_urlArr[1] + ")" +
                        "\nweblogic.webservice.transport.https.proxy.host (" + l_urlArr[0] + ")" +
                        "\nweblogic.webservice.transport.https.proxy.port (" + l_urlArr[1] + ")";
d491 6
a496 2
                    System.setProperty("weblogic.webservice.transport.http.proxy.host", l_urlArr[0]);
                    System.setProperty("weblogic.webservice.transport.http.proxy.port", l_urlArr[1]);
d499 1
a499 3
                        "\nhttp.proxyPort (" + l_urlArr[1] + ")" +
                        "\nweblogic.webservice.transport.http.proxy.host (" + l_urlArr[0] + ")" +
                        "\nweblogic.webservice.transport.http.proxy.port (" + l_urlArr[1] + ")";
a518 2
            
            l_call.setTargetEndpointAddress(l_soapUrl);
d520 25
a544 7
            
            //1.15 setProperty(arg0 : String, arg1 : Object)
            //[����] 
            //  arg0�F "weblogic.webservice.rpc.timeoutsecs" 
            //  arg1�F �v���t�@@�����X.�ڑ��^�C���A�E�g���� 
            l_call.setProperty("weblogic.webservice.rpc.timeoutsecs", l_rpcRow.getResponseTimeout());
            
d548 5
a552 1
            Object l_objValue = l_call.invoke(l_strParameterlists);
d554 4
a557 1
            //1.17 invoke�̖߂�l��ԋp����B
a590 10
        catch (ServiceException l_srvExp)
        {
            log.error(l_srvExp.getMessage(),l_srvExp);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_srvExp.getMessage(),
                l_srvExp);
        }
d608 3
a610 2
     * @@param l_bindingInfo - �o�C���h���
     * @@param l_messageContext - ���M���b�Z�[�W
d615 5
a619 4
    protected SOAPMessageContext sendMessage(
        BindingInfo l_bindingInfo, 
        DefaultMessageContext l_messageContext) 
        throws WEB3BaseException, ConnectException, SocketTimeoutException
d623 1
a623 1
        
d626 1
a626 1
            //1.1 �g�p����n���h���̃p�b�P�[�W�̐ݒ���s���B
d628 5
a632 15
            //  arg0�F "java.protocol.handler.pkgs"
            //  arg1�F "weblogic.net"
            System.setProperty("java.protocol.handler.pkgs", "weblogic.net");
            
            //1.3 Http11ClientBinding�C���X�^���X�𐶐�����B
            Http11ClientBinding l_clientBinding = new Http11ClientBinding();
        
            //1.4 �o�C���h������������B 
            //[����] 
            //  arg0�F ����.�o�C���h��� 
            l_clientBinding.init(l_bindingInfo);
        
            //1.5 DefaultMessageContext()
            // ��M���b�Z�[�W�p�̃��b�Z�[�W�R���e�L�X�g�̃C���X�^���X�𐶐�����B 
            DefaultMessageContext l_defaultMessageContext = new DefaultMessageContext();
d634 15
a648 8
            //1.6 send(arg0 : MessageContext)
            //[����] 
            //  arg0�F ����.���M���b�Z�[�W 
            l_clientBinding.send(l_messageContext);
            //1.7 receive(arg0 : MessageContext)
            //[����] 
            //  arg0�F ����������M�p���b�Z�[�W�R���e�L�X�g 
            l_clientBinding.receive(l_defaultMessageContext);
d652 1
a652 1
            return l_defaultMessageContext;  
d668 5
d713 1
a713 1
        
d718 1
a718 1
            
d732 1
a732 1
            
d748 1
a748 1
            
d773 1
a773 1
                
d779 1
a779 1
            
d783 1
a783 1
            
d789 1
a789 1
            
d792 1
a792 1
            
d814 1
a814 1
     * @@param l_defaultMessageContext - ��M���b�Z�[�W
d821 1
a821 1
        DefaultMessageContext l_defaultMessageContext) throws WEB3BaseException
d829 2
a830 1
            SOAPMessage l_soapMessage = l_defaultMessageContext.getMessage();
d1062 113
@


1.1
log
@*** empty log message ***
@
text
@d21 1
a21 6
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
d53 1
a53 5
import weblogic.net.http.HttpClient;
import weblogic.net.http.HttpsClient;
import weblogic.webservice.binding.BindingInfo;
import weblogic.webservice.binding.http11.Http11ClientBinding;
import weblogic.webservice.core.DefaultMessageContext;
@

