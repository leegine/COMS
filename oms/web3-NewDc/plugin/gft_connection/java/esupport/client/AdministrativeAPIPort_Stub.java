head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.03.05.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	AdministrativeAPIPort_Stub.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package esupport.client;
/**
 * Generated class, do not edit.
 *
 * This stub class was generated by weblogic
 * webservice stub gen on Thu Oct 30 17:29:00 JST 2008 */

public class AdministrativeAPIPort_Stub 
     extends weblogic.webservice.core.rpc.StubImpl 
     implements  esupport.client.AdministrativeAPIPort{

  public AdministrativeAPIPort_Stub( weblogic.webservice.Port _port ){
    super( _port, esupport.client.AdministrativeAPIPort.class );
  }

  /**
   * sendSyncRequest 
   */

  public com.gftforex.soap.api.SendSyncRequestResponse sendSyncRequest(com.gftforex.soap.api.SendSyncRequest parameters) 
       throws esupport.client.AuthorizationFailedException, esupport.client.InternalErrorException, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           try{
      java.lang.Object _result = _invoke( "sendSyncRequest", _args );
      return (com.gftforex.soap.api.SendSyncRequestResponse)_result;
    
    } catch (esupport.client.AuthorizationFailedException e) {
      throw e;
    } catch (esupport.client.InternalErrorException e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * getResponse 
   */

  public com.gftforex.soap.api.ResultInfoBase[] getResponse(com.gftforex.soap.api.GetResponse parameters) 
       throws esupport.client.AuthorizationFailedException, esupport.client.InternalErrorException, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           try{
      java.lang.Object _result = _invoke( "getResponse", _args );
      return (com.gftforex.soap.api.ResultInfoBase[])_result;
    
    } catch (esupport.client.AuthorizationFailedException e) {
      throw e;
    } catch (esupport.client.InternalErrorException e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * sendRequest 
   */

  public com.gftforex.soap.api.SendRequestResponse sendRequest(com.gftforex.soap.api.SendRequest parameters) 
       throws esupport.client.AuthorizationFailedException, esupport.client.InternalErrorException, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           try{
      java.lang.Object _result = _invoke( "sendRequest", _args );
      return (com.gftforex.soap.api.SendRequestResponse)_result;
    
    } catch (esupport.client.AuthorizationFailedException e) {
      throw e;
    } catch (esupport.client.InternalErrorException e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * getResponseByCommandId 
   */

  public com.gftforex.soap.api.GetResponseByCommandIdResponse getResponseByCommandId(com.gftforex.soap.api.GetResponseByCommandId parameters) 
       throws esupport.client.AuthorizationFailedException, esupport.client.InternalErrorException, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           try{
      java.lang.Object _result = _invoke( "getResponseByCommandId", _args );
      return (com.gftforex.soap.api.GetResponseByCommandIdResponse)_result;
    
    } catch (esupport.client.AuthorizationFailedException e) {
      throw e;
    } catch (esupport.client.InternalErrorException e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * Convenience method for getResponseByCommandId 
   */

  public com.gftforex.soap.api.ResultInfoBase getResponseByCommandId(java.lang.String commandId, com.gftforex.soap.api.AuthToken authToken)
       throws esupport.client.AuthorizationFailedException, esupport.client.InternalErrorException, java.rmi.RemoteException {

    com.gftforex.soap.api.GetResponseByCommandId _input = 
         new com.gftforex.soap.api.GetResponseByCommandId();

        _input.setCommandId( commandId );
    _input.setAuthToken( authToken );
      
    com.gftforex.soap.api.GetResponseByCommandIdResponse _result = getResponseByCommandId( _input );

        return _result.getGetResponseByCommandIdResult();
      }
  /**
   * isOk 
   */

  public com.gftforex.soap.api.IsOkResponse isOk(com.gftforex.soap.api.IsOk parameters) 
       throws java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           try{
      java.lang.Object _result = _invoke( "isOk", _args );
      return (com.gftforex.soap.api.IsOkResponse)_result;
        } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * Convenience method for isOk 
   */

  public boolean isOk()
       throws java.rmi.RemoteException {

    com.gftforex.soap.api.IsOk _input = 
         new com.gftforex.soap.api.IsOk();

          
    com.gftforex.soap.api.IsOkResponse _result = isOk( _input );

        return _result.getIsOkResult();
      }
}@
