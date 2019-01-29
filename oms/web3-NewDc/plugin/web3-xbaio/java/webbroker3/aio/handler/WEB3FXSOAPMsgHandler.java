head	1.4;
access;
symbols;
locks; strict;
comment	@// @;


1.4
date	2011.04.19.01.04.21;	author zhang-tengyu;	state Exp;
branches;
next	1.3;
deltatype	text;
kopt	kv;
permissions	666;
commitid	61c4dacdf94213f;
filename	WEB3FXSOAPMsgHandler.java;

1.3
date	2011.03.30.08.33.04;	author zhang-tengyu;	state Exp;
branches;
next	1.2;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3cc4d92eac05e08;
filename	WEB3FXSOAPMsgHandler.java;

1.2
date	2011.03.28.06.23.20;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2f84d9029585e79;
filename	WEB3FXSOAPMsgHandler.java;

1.1
date	2011.03.16.05.21.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXSOAPMsgHandler.java;


desc
@@


1.4
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V���� �h�s�C�m�x�[�V�������Ɩ{��
File Name        : FXSOAP���b�Z�[�W�n���h���N���X(WEB3FXSOAPMsgHandler)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2009/1/22 �g�� (SCS) �V�K�쐬
*/

package webbroker3.aio.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.common.define.WEB3SoapSendReceiveDivDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;


/**
 * (FXSOAP���b�Z�[�W�n���h��) <BR> 
 * FXSOAP���b�Z�[�W�n���h�� <BR>
 * @@author 0824102
 * @@version 1.0
 */
public final class WEB3FXSOAPMsgHandler implements SOAPHandler
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSOAPMsgHandler.class);

    public Set getHeaders()
    {
        return null;
    }

    public boolean handleMessage(MessageContext mc)
    {
        String STR_METHOD_NAME = "handleMessage(MessageContext)";
        log.entering(STR_METHOD_NAME);
        String l_strSendReceiveDiv = "";
        String l_strRequestResponseDiv = "";
        if (mc.get(MessageContext.HTTP_RESPONSE_CODE) == null)
        {
            l_strSendReceiveDiv = WEB3SoapSendReceiveDivDef.SEND;
            l_strRequestResponseDiv = "Request";
        }
        else
        {
            l_strSendReceiveDiv = WEB3SoapSendReceiveDivDef.RECEIVE;
            l_strRequestResponseDiv = "Response";
        }
        SOAPMessageContext messageContext = (SOAPMessageContext) mc;

        // �P�j���XID,�ڑ��敪�̐ݒ�
        long l_lngBranchId = Long.parseLong((String) ThreadLocalSystemAttributesRegistry.getAttribute("branch_id"));
        String l_strConnectDiv = (String) ThreadLocalSystemAttributesRegistry.getAttribute("connect_div");

        // �Q�j�o�̓X�g���[���ɑ��M���b�Z�[�W���o��
        ByteArrayOutputStream l_outputStream = new ByteArrayOutputStream();

        // insertSOAPMessage()�Ăяo������t���O
        boolean l_callInsSoapMsg = true;

        try
        {
            if (messageContext.getMessage() != null)
            {
                messageContext.getMessage().writeTo(l_outputStream);
                log.debug("\n" + "*****handle" + l_strRequestResponseDiv
                        + " getMessage()*****" + "\n"
                        + l_outputStream.toString());
            }

        }
        catch (SOAPException l_ex)
        {
            log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
            l_callInsSoapMsg = false;

        }
        catch (IOException l_ex)
        {

            log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
            l_callInsSoapMsg = false;

        }

        // �R�jDB�ւ̃f�[�^�o�^����
        // �o�̓X�g���[���ɑ��M���b�Z�[�W���o�͂��鏈��������ɍs��ꂽ�ꍇ�̂ݎ��s
        if (l_callInsSoapMsg)
        {
            WEB3FXDataControlService l_fxDataControlService = (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
            // ���b�Z�[�W insertSOAPMessage(long, String, String, String)
            // (FXSOAP���O�n���h��::insertSOAPMessage)
            // [����]
            // ���XID �F map.���XID
            // �ڑ��敪 �F map.�ڑ��敪
            // ����M�敪�F �h��M�h
            // ���b�Z�[�W�F �o�̓X�g���[��.toString()�̖߂�l
            l_fxDataControlService.insertSOAPMessage(
                l_lngBranchId, l_strConnectDiv, l_strSendReceiveDiv, l_outputStream.toString());

        }
        return true;
    }

    /**
     * (SOAP���b�Z�[�W��Q�̏o��)
     */
    public boolean handleFault(MessageContext mc)
    {
        String STR_METHOD_NAME = "handleFault(MessageContext mc)";
        log.entering(STR_METHOD_NAME);

        SOAPMessageContext messageContext = (SOAPMessageContext) mc;

        if (messageContext.getMessage() != null)
        {
            SOAPMessage l_SOAPMessage = messageContext.getMessage();
            SOAPPart l_SOAPPart = l_SOAPMessage.getSOAPPart();
            try
            {
                SOAPEnvelope l_SOAPEnvelope = l_SOAPPart.getEnvelope();
                log.debug("\n" + "*****handleFault getMessage()*****" + "\n"
                        + l_SOAPEnvelope.toString());
            }
            catch (SOAPException l_ex)
            {
                log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            }
        }

        log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B");
        log.exiting(STR_METHOD_NAME);

        return false;
    }

    public void close(MessageContext arg0)
    {
        return;
    }

}
@


1.3
log
@*** empty log message ***
@
text
@a46 1
        System.out.println("********************getHeaders");
d52 1
a52 1
        String STR_METHOD_NAME ="handleMessage(MessageContext)";
d57 1
a57 1
    	{
d59 4
a62 4
            l_strRequestResponseDiv="Request";
    	}
    	else
    	{
d64 54
a117 55
            l_strRequestResponseDiv="Response";
    	}
        SOAPMessageContext messageContext = (SOAPMessageContext)mc;

    		      // �P�j���XID,�ڑ��敪�̐ݒ�
    	          long l_lngBranchId = Long.parseLong((String)ThreadLocalSystemAttributesRegistry.getAttribute("branch_id"));
    	          String l_strConnectDiv = (String)ThreadLocalSystemAttributesRegistry.getAttribute("connect_div");
   		      
    		      // �Q�j�o�̓X�g���[���ɑ��M���b�Z�[�W���o��
    		      ByteArrayOutputStream l_outputStream = new ByteArrayOutputStream();
    		      
    		      // insertSOAPMessage()�Ăяo������t���O
    		      boolean l_callInsSoapMsg = true;
    		      
    		      try 
    		      {
        		      if (messageContext.getMessage() != null) 
        		      {
                          messageContext.getMessage().writeTo(l_outputStream);
                          log.debug("\n" + "*****handle"+l_strRequestResponseDiv+" getMessage()*****" + 
	    		   		        "\n" + l_outputStream.toString());
        		      }

    		      }
    		      catch (SOAPException l_ex) 
    		      {	  	
    		          log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
    		          l_callInsSoapMsg = false;
    		          
    		  	  } catch (IOException l_ex) {
    		  	  	
    		          log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
    		          l_callInsSoapMsg = false;
    		          
    		  	  }
    		      
    		      // �R�jDB�ւ̃f�[�^�o�^����
    			  // �o�̓X�g���[���ɑ��M���b�Z�[�W���o�͂��鏈��������ɍs��ꂽ�ꍇ�̂ݎ��s
    			  if(l_callInsSoapMsg){
    			  	
    			      WEB3FXDataControlService l_fxDataControlService =
    			        (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
    				  // ���b�Z�[�W insertSOAPMessage(long, String, String, String)
    				  //  (FXSOAP���O�n���h��::insertSOAPMessage)
    				  // [����] 
    				  //    ���XID    �F map.���XID 
    				  //    �ڑ��敪  �F map.�ڑ��敪 
    				  //    ����M�敪�F �h��M�h 
    				  //    ���b�Z�[�W�F �o�̓X�g���[��.toString()�̖߂�l
    			   l_fxDataControlService.insertSOAPMessage(
                       l_lngBranchId,
				       l_strConnectDiv,
				       l_strSendReceiveDiv,
				       l_outputStream.toString());
    			  
a123 1
     * 
d127 2
a128 2
      String STR_METHOD_NAME ="handleFault(MessageContext mc)";
      log.entering(STR_METHOD_NAME);
d130 1
a130 1
      SOAPMessageContext messageContext = (SOAPMessageContext)mc;
d132 13
a144 16
      if (messageContext.getMessage() != null) 
      {
    	  SOAPMessage l_SOAPMessage =  messageContext.getMessage();
    	  SOAPPart l_SOAPPart = l_SOAPMessage.getSOAPPart();
    	  try 
    	  {
			SOAPEnvelope l_SOAPEnvelope = l_SOAPPart.getEnvelope();
	          log.debug("\n" + "*****handleFault getMessage()*****" +
	     		        "\n" + l_SOAPEnvelope.toString());
		   } 
	     catch (SOAPException l_ex) 
	    {
		  	
	        log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
	        
		  } 
d146 2
a147 1
      }
d149 2
a150 2
      log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B");
      log.exiting(STR_METHOD_NAME);
d152 1
a152 1
      return false;
a156 1
        System.out.println("********************close");
@


1.2
log
@*** empty log message ***
@
text
@a22 1
import webbroker3.aio.WEB3FXDataControlServiceImpl;
a106 1
//    				  WEB3FXDataControlServiceImpl l_fxDataControlService = new WEB3FXDataControlServiceImpl();
@


1.1
log
@*** empty log message ***
@
text
@d12 1
a12 1
import java.util.Map;
d14 1
a14 5
import javax.xml.rpc.handler.Handler;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.namespace.QName;
d16 5
a21 4
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
d23 1
d25 4
d37 1
a37 1
public final class WEB3FXSOAPMsgHandler implements Handler
d46 5
a50 1
    private HandlerInfo handlerInfo;
d52 72
a123 2
    public void init(HandlerInfo hi) {
      handlerInfo = hi;
a125 4
    public void destroy() {}

    public QName[ ] getHeaders() { return handlerInfo.getHeaders(); }

d127 1
a127 1
     * (���N�G�X�gSOAP���b�Z�[�W�̏o��)
d130 3
a132 3
    public boolean handleRequest(MessageContext mc) {
    	
      String STR_METHOD_NAME ="handleRequest(MessageContext mc)";
a133 64
    	
      SOAPMessageContext messageContext = (SOAPMessageContext) mc;
      
      if (messageContext.getMessage() != null) 
      {
      log.debug("\n" + "*****handleRequest getMessage()*****" + 
      		    "\n" + messageContext.getMessage().toString());
      }
      
      // �P�j���XID,�ڑ��敪�̐ݒ�
      Map HandlerConfig = handlerInfo.getHandlerConfig();
      
      long l_lngBranchId = Long.parseLong(HandlerConfig.get("BranchId").toString());
      String l_strConnectDiv = HandlerConfig.get("ConnectDiv").toString();
      
      // �Q�j�o�̓X�g���[���ɑ��M���b�Z�[�W���o��
      ByteArrayOutputStream l_outputStream = new ByteArrayOutputStream();
      
      // insertSOAPMessage()�Ăяo������t���O
      boolean l_callInsSoapMsg = true;
      
      try {
      	
		  messageContext.getMessage().writeTo(l_outputStream);
		  
	  } catch (SOAPException l_ex) {
	  	
        log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
        l_callInsSoapMsg = false;
        
	  } catch (IOException l_ex) {
	  	
        log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
        l_callInsSoapMsg = false;
        
	  }
	  
	  // �R�jDB�ւ̃f�[�^�o�^���� 
	  // �o�̓X�g���[���ɑ��M���b�Z�[�W���o�͂��鏈��������ɍs��ꂽ�ꍇ�̂ݎ��s
	  if(l_callInsSoapMsg){
	  
	      WEB3FXDataControlService l_fxDataControlService =
	        (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
	      
		  // ���b�Z�[�W insertSOAPMessage(long, String, String, String)
		  //  (FXSOAP���O�n���h��::insertSOAPMessage)
		  // [����] 
		  //    ���XID    �F map.���XID 
		  //    �ڑ��敪  �F map.�ڑ��敪 
		  //    ����M�敪�F �h���M�h 
		  //    ���b�Z�[�W�F �o�̓X�g���[��.toString()�̖߂�l

	      	l_fxDataControlService.insertSOAPMessage(
			      l_lngBranchId,
			      l_strConnectDiv,
			      WEB3SoapSendReceiveDivDef.SEND,
				  l_outputStream.toString());
	  
	  }
	  
	  log.exiting(STR_METHOD_NAME);
      
      return true;
    }
d135 1
a135 10
    /**
     * (���X�|���XSOAP���b�Z�[�W�̏o��)
     * 
     */
    public boolean handleResponse(MessageContext mc) {
      
      String STR_METHOD_NAME ="handleResponse(MessageContext mc)";
      log.entering(STR_METHOD_NAME);
      
      SOAPMessageContext messageContext = (SOAPMessageContext) mc;
d139 14
a152 59
      log.debug("\n" + "*****handleResponse getMessage()*****" + 
   		        "\n" + messageContext.getMessage().toString());
      }
      
      // �P�j���XID,�ڑ��敪�̐ݒ�
      Map HandlerConfig = handlerInfo.getHandlerConfig();
      
      long l_lngBranchId = Long.parseLong(HandlerConfig.get("BranchId").toString());
      String l_strConnectDiv = HandlerConfig.get("ConnectDiv").toString();
      
      // �Q�j�o�̓X�g���[���ɑ��M���b�Z�[�W���o��
      ByteArrayOutputStream l_outputStream = new ByteArrayOutputStream();
      
      // insertSOAPMessage()�Ăяo������t���O
      boolean l_callInsSoapMsg = true;
      
      try {
      	
		  messageContext.getMessage().writeTo(l_outputStream);
		  
	  } catch (SOAPException l_ex) {
	  	
        log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
        l_callInsSoapMsg = false;
        
	  } catch (IOException l_ex) {
	  	
        log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
        l_callInsSoapMsg = false;
        
	  }
	  
      // �R�jDB�ւ̃f�[�^�o�^����
	  // �o�̓X�g���[���ɑ��M���b�Z�[�W���o�͂��鏈��������ɍs��ꂽ�ꍇ�̂ݎ��s
	  if(l_callInsSoapMsg){
	  	
	      WEB3FXDataControlService l_fxDataControlService =
	        (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
	      
		  // ���b�Z�[�W insertSOAPMessage(long, String, String, String)
		  //  (FXSOAP���O�n���h��::insertSOAPMessage)
		  // [����] 
		  //    ���XID    �F map.���XID 
		  //    �ڑ��敪  �F map.�ڑ��敪 
		  //    ����M�敪�F �h��M�h 
		  //    ���b�Z�[�W�F �o�̓X�g���[��.toString()�̖߂�l

	      	l_fxDataControlService.insertSOAPMessage(
			      l_lngBranchId,
			      l_strConnectDiv,
			      WEB3SoapSendReceiveDivDef.RECEIVE,
			      l_outputStream.toString());
	  
	  }
	  
	  log.exiting(STR_METHOD_NAME);
      
      return true;
    }
a153 15
    /**
     * (SOAP���b�Z�[�W��Q�̏o��)
     * 
     */
    public boolean handleFault(MessageContext mc) {
    	
      String STR_METHOD_NAME ="handleFault(MessageContext mc)";
      log.entering(STR_METHOD_NAME);
    	
      SOAPMessageContext messageContext = (SOAPMessageContext) mc;
      
      if (messageContext.getMessage() != null) 
      {
      log.debug("\n" + "*****handleFault getMessage()*****" + 
   		        "\n" + messageContext.getMessage().toString());
d160 1
d162 4
@

