// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessageHandlerDispatcher.java

package com.fitechlabs.xtrade.kernel.handler;

import com.fitechlabs.xtrade.kernel.error.ErrorResponseRegistry;
import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

// Referenced classes of package com.fitechlabs.xtrade.kernel.handler:
//            MessageHandlerException, MessageHandler, DefaultSecurityManager, MessageSecurityManager, 
//            MessageHandlersParamsContext, HandlerEventNotificationCallback, MessageHandlingContext

public final class MessageHandlerDispatcher
{
    private static class HandlerMethodInfo
    {

        private MessageHandler handler;
        private Method method;
        private HandlerEventNotificationCallback callbacks[];




        private HandlerMethodInfo(MessageHandler handler, Method method, HandlerEventNotificationCallback callbacks[])
        {
            this.handler = handler;
            this.method = method;
            this.callbacks = callbacks;
        }

    }


    public MessageHandlerDispatcher()
    {
    }

    public static synchronized void setSecurityManager(MessageSecurityManager securityManager)
        throws MessageHandlerException
    {
        theSecurityManager = securityManager;
    }

    public static MessageHandlingContext getHandlingContext()
        throws MessageHandlerException
    {
        return theSecurityManager.getHandlingContext();
    }

    public static final void Register(Class messageType, MessageHandler handler, String methodName, HandlerEventNotificationCallback callbacks[])
        throws MessageHandlerException, Exception
    {
        if(messageType.isAssignableFrom(com.fitechlabs.xtrade.kernel.message.Message.class))
            throw new MessageHandlerException("MessageHandler.Register: First argument does not derive from Message class");
        if(handlerMap.containsKey(messageType))
            throw new MessageHandlerException("MessageHandler.Register: Message type " + messageType.getName() + " is already registered.");
        Class argTypes[] = {
            messageType
        };
        Method method = handler.getClass().getMethod(methodName, argTypes);
        HandlerMethodInfo info = new HandlerMethodInfo(handler, method, callbacks);
        if(DBG)
            log.debug("Registering Handler: messageType=" + messageType + " handler=" + handler + " method=" + method);
        handlerMap.put(messageType, info);
    }

    public static void unRegisterHandler(Class messageType)
        throws MessageHandlerException
    {
        HandlerMethodInfo info = (HandlerMethodInfo)handlerMap.remove(messageType);
        if(info == null)
            throw new MessageHandlerException("MessageHandler.Register: Message type " + messageType.getName() + " has not been registered.");
        else
            return;
    }

    public static void registerHandler(Class messageClass, Class handlerClass, String handlerMethodName, HandlerEventNotificationCallback callbacks[])
        throws Exception
    {
        Register(messageClass, getHandlerInstance(handlerClass.getName()), handlerMethodName, callbacks);
    }

    private static MessageHandler getHandlerInstance(String handlerClassName)
        throws Exception
    {
        if(handlerInstances.containsKey(handlerClassName))
            return (MessageHandler)handlerInstances.get(handlerClassName);
        MessageHandler handler;
        handler = (MessageHandler)Class.forName(handlerClassName).newInstance();
        handlerInstances.put(handlerClassName, handler);
        return handler;
        ClassCastException cce;
        cce;
        throw new Exception("Class " + handlerClassName + " does not implement MessageHandler");
    }

    public static Response HandleSecurely(Message msg)
    {
        if(theSecurityManager == null)
            break MISSING_BLOCK_LABEL_36;
        if(!theSecurityManager.allow(msg))
            return ErrorResponseRegistry.NOT_ALLOWED;
        break MISSING_BLOCK_LABEL_36;
        MessageHandlerException mhe;
        mhe;
        return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.NOT_ALLOWED, "Security manager threw exception.", mhe);
        return Handle(msg);
    }

    public static Response Handle(Message msg)
    {
        HandlerMethodInfo info;
        Class messageType = msg.getClass();
        info = (HandlerMethodInfo)handlerMap.get(messageType);
        if(info == null)
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.HANDLER_NOT_FOUND, "Class=" + messageType.getName());
        Response response;
        MessageHandlersParamsContext.setMessageHandlerArgs(new Object[] {
            msg
        });
        response = Handle(msg, info);
        MessageHandlersParamsContext.setMessageHandlerArgs(null);
        return response;
        Exception exception;
        exception;
        MessageHandlersParamsContext.setMessageHandlerArgs(null);
        throw exception;
    }

    private static Response Handle(Message msg, HandlerMethodInfo info)
    {
        Class messageType = msg.getClass();
        HandlerEventNotificationCallback callbacks[] = info.callbacks;
        Response errorResponse = null;
        Response resp = null;
        int last = -1;
        Error error = null;
        if(callbacks != null)
        {
            for(int i = 0; errorResponse == null && i < callbacks.length;)
            {
                last = i;
                try
                {
                    if(DBG)
                        log.debug("Calling onEnter.");
                    callbacks[i].onEnter();
                    continue;
                }
                catch(Throwable e)
                {
                    String errorInfo = "handler error: onEnter() failed. handler=" + info.handler + " method=" + info.method;
                    log.error(errorInfo, e);
                    errorResponse = ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.HANDLER_ON_ENTER_EXCEPTION, errorInfo, e);
                    if(e instanceof Error)
                        error = (Error)e;
                    i++;
                }
            }

        }
        if(errorResponse == null && error == null)
            try
            {
                if(DBG)
                    log.debug("Calling Handler: message=" + msg + " handler=" + info.handler + " method=" + info.method);
                resp = (Response)info.method.invoke(info.handler, new Object[] {
                    msg
                });
            }
            catch(InvocationTargetException ite)
            {
                String errorInfo = "handler exception: handler=" + info.handler + " method=" + info.method;
                log.error(errorInfo, ite.getTargetException());
                errorResponse = ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.HANDLER_EXCEPTION, ite.getTargetException());
            }
            catch(Exception ex)
            {
                String errorInfo = "internal error: handler=" + info.handler + " method=" + info.method;
                log.error(errorInfo, ex);
                errorResponse = ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.INTERNAL_ERROR, "Handler invocation failed.", ex);
            }
            catch(Throwable e)
            {
                String errorInfo = "internal error: handler=" + info.handler + " method=" + info.method;
                log.error(errorInfo, e);
                if(e instanceof Error)
                    error = (Error)e;
            }
        if(callbacks != null)
        {
            for(int i = last; i >= 0;)
                try
                {
                    if(DBG)
                        log.debug("Calling onExit.");
                    callbacks[i].onExit();
                    continue;
                }
                catch(Throwable e)
                {
                    String errorInfo = "handler error: onExit() failed. handler=" + info.handler + " method=" + info.method;
                    log.error(errorInfo, e);
                    if(errorResponse == null)
                        errorResponse = ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.HANDLER_ON_EXIT_EXCEPTION, errorInfo, e);
                    if(error == null && (e instanceof Error))
                        error = (Error)e;
                    i--;
                }

        }
        if(error != null)
            throw error;
        else
            return errorResponse == null ? resp : errorResponse;
    }

    private static final Logit log;
    private static final boolean DBG;
    private static final HashMap handlerMap = new HashMap();
    private static final HashMap handlerInstances = new HashMap();
    private static final MessageSecurityManager theDefaultSecurityManager;
    private static MessageSecurityManager theSecurityManager;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.handler.MessageHandlerDispatcher.class);
        DBG = log.ison();
        theDefaultSecurityManager = new DefaultSecurityManager();
        theSecurityManager = theDefaultSecurityManager;
    }
}
