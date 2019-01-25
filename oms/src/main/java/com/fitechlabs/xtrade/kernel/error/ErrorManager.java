// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ErrorManager.java

package com.fitechlabs.xtrade.kernel.error;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.data.db.*;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ObjectPrettyPrinter;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.error:
//            ErrorInfoFormat

public class ErrorManager
{

    public String toString()
    {
        return "ErrorManager(" + clientClass.getName() + ")";
    }

    public static ErrorManager getInstance(Class clientClass)
    {
        ErrorManager em;
        synchronized(perClass)
        {
            em = (ErrorManager)perClass.get(clientClass);
            if(null == em)
            {
                try
                {
                    KernelPlugin.plug();
                }
                catch(Exception e)
                {
                    throw new RuntimeException("ErrorManager cannot boot KernelPlugin: " + e);
                }
                em = new ErrorManager(clientClass);
                perClass.put(clientClass, em);
            }
        }
        return em;
    }

    private ErrorManager(Class clientClass)
    {
        this.clientClass = clientClass;
    }

    public ErrorInfo defineErrorInfo(String tagValue, String unlocalizedMessage)
    {
        return defineErrorInfo(0, tagValue, unlocalizedMessage, null);
    }

    /**
     * @deprecated Method createErrorInfo is deprecated
     */

    public ErrorInfo createErrorInfo(String unlocalizedMessage)
    {
        return defineErrorInfo(null, unlocalizedMessage);
    }

    public ErrorInfo defineErrorInfo(String tagValue, String unlocalizedMessage, String notesToLocalizers)
    {
        return defineErrorInfo(0, tagValue, unlocalizedMessage, notesToLocalizers);
    }

    /**
     * @deprecated Method createErrorInfo is deprecated
     */

    public ErrorInfo createErrorInfo(String unlocalizedMessage, String notesToLocalizers)
    {
        return defineErrorInfo(((String) (null)), unlocalizedMessage, notesToLocalizers);
    }

    public ErrorInfo defineErrorInfo(int code, String tagValue, String unlocalizedMessage)
    {
        return defineErrorInfo(code, tagValue, unlocalizedMessage, null);
    }

    /**
     * @deprecated Method createErrorInfo is deprecated
     */

    public ErrorInfo createErrorInfo(int code, String unlocalizedMessage)
    {
        return defineErrorInfo(code, null, unlocalizedMessage);
    }

    public ErrorInfo defineErrorInfo(int code, String tagValue, String unlocalizedMessage, String notesToLocalizer)
    {
        ErrorMessageRow row = getOrCreateErrorMessageRow(code, tagValue, unlocalizedMessage, notesToLocalizer);
        String errorCode = row.getErrorCode();
        String errorClass = row.getErrorClass();
        String errorTag = row.getErrorTag();
        String message = row.getLocalizedText();
        ErrorInfo ei = new ErrorInfo();
        ei.error_code = errorCode;
        ei.error_class = errorClass;
        ei.error_tag = errorTag;
        ei.error_message = message;
        return ei;
    }

    /**
     * @deprecated Method createErrorInfo is deprecated
     */

    public ErrorInfo createErrorInfo(int code, String unlocalizedMessage, String notesToLocalizer)
    {
        return defineErrorInfo(code, null, unlocalizedMessage, notesToLocalizer);
    }

    public ErrorInfoFormat defineErrorInfoFormat(String tagValue, String messageFormat)
    {
        return defineErrorInfoFormat(0, tagValue, messageFormat, null);
    }

    /**
     * @deprecated Method createErrorInfoFormat is deprecated
     */

    public ErrorInfoFormat createErrorInfoFormat(String messageFormat)
    {
        return defineErrorInfoFormat(null, messageFormat);
    }

    public ErrorInfoFormat defineErrorInfoFormat(String tagValue, String messageFormat, String notesToLocalizers)
    {
        return defineErrorInfoFormat(0, tagValue, messageFormat, notesToLocalizers);
    }

    /**
     * @deprecated Method createErrorInfoFormat is deprecated
     */

    public ErrorInfoFormat createErrorInfoFormat(String messageFormat, String notesToLocalizers)
    {
        return defineErrorInfoFormat(((String) (null)), messageFormat, notesToLocalizers);
    }

    public ErrorInfoFormat defineErrorInfoFormat(int code, String tagValue, String messageFormat)
    {
        return defineErrorInfoFormat(code, tagValue, messageFormat, null);
    }

    /**
     * @deprecated Method createErrorInfoFormat is deprecated
     */

    public ErrorInfoFormat createErrorInfoFormat(int code, String messageFormat)
    {
        return defineErrorInfoFormat(code, null, messageFormat);
    }

    public ErrorInfoFormat defineErrorInfoFormat(int code, String tagValue, String messageFormat, String notesToLocalizers)
    {
        ErrorMessageRow row = getOrCreateErrorMessageRow(code, tagValue, messageFormat, notesToLocalizers);
        String errorCode = row.getErrorCode();
        String errorClass = row.getErrorClass();
        String errorTag = row.getErrorTag();
        String message = row.getLocalizedText();
        return new ErrorInfoFormat(errorCode, errorClass, errorTag, message);
    }

    /**
     * @deprecated Method createErrorInfoFormat is deprecated
     */

    public ErrorInfoFormat createErrorInfoFormat(int code, String messageFormat, String notesToLocalizers)
    {
        return defineErrorInfoFormat(code, null, messageFormat, notesToLocalizers);
    }

    public String defineErrorValue(String unlocalizedString)
    {
        return defineErrorValue(unlocalizedString, (String)null);
    }

    /**
     * @deprecated Method createErrorValue is deprecated
     */

    public String createErrorValue(String unlocalizedString)
    {
        return defineErrorValue(unlocalizedString);
    }

    public String defineErrorValue(String unlocalizedString, ErrorInfoFormat context)
    {
        return defineErrorValue(unlocalizedString, context.getMessageFormatString());
    }

    /**
     * @deprecated Method createErrorValue is deprecated
     */

    public String createErrorValue(String unlocalizedString, ErrorInfoFormat context)
    {
        return defineErrorValue(unlocalizedString, context);
    }

    public String defineErrorValue(String unlocalizedString, String notesToLocalizers)
    {
        ErrorMessageRow row = getOrCreateErrorMessageRow(0, null, unlocalizedString, notesToLocalizers);
        String message = row.getLocalizedText();
        return message;
    }

    /**
     * @deprecated Method createErrorValue is deprecated
     */

    public String createErrorValue(String unlocalizedString, String notesToLocalizers)
    {
        return defineErrorValue(unlocalizedString, notesToLocalizers);
    }

    private ErrorMessageRow getOrCreateErrorMessageRow(int errorCode, String errorTag, String initialText, String contextText)
    {
        QueryProcessor qp = null;
        try
        {
            qp = Processors.getDefaultProcessor();
            if(qp == null)
                throw new InternalError("no default processor.");
        }
        catch(DataException e)
        {
            throw new InternalError("no default processor.");
        }
        if(qp != null)
        {
            ErrorMessageRow preExistingRow = getPreExistingRow(qp, errorCode, errorTag, initialText, contextText);
            if(preExistingRow != null)
            {
                if(DBG)
                    log.debug("found " + preExistingRow);
                return preExistingRow;
            }
        }
        long id;
        try
        {
            id = ErrorMessageDao.newPkValue();
        }
        catch(DataException e)
        {
            log.warn("id allocation failure: " + e);
            id = System.currentTimeMillis();
        }
        ErrorMessageParams params = new ErrorMessageParams();
        params.setErrorMessageId(-id);
        params.setErrorClass(clientClass.getName());
        params.setErrorCode(String.valueOf(errorCode != 0 ? errorCode : -id));
        params.setErrorTag(errorTag != null ? errorTag : "SYS-" + id);
        params.setInitialText(initialText);
        params.setContextText(contextText);
        params.setLocalizedText(initialText);
        try
        {
            log.info("persisting " + params);
            qp.doQuery(2, BatchedQuery.createInsertQuery(params));
        }
        catch(DataException e)
        {
            log.error("persistence failure, using: " + params, e);
        }
        return params;
    }

    private ErrorMessageRow getPreExistingRow(QueryProcessor qp, int errorCode, String errorTag, String initialText, String contextText)
    {
        String c = clientClass.getName();
        ErrorMessageRow row;
        if(errorTag != null)
        {
            row = findUnique(qp, "error_class=? and error_tag=?", new Object[] {
                c, errorTag
            });
            if(row != null)
                return row;
            if(DBG)
                log.debug("tagged error not found for " + c + "." + errorTag);
        }
        if(errorCode > 0)
        {
            row = findUnique(qp, "error_class=? and error_code=?", new Object[] {
                c, String.valueOf(errorCode)
            });
            if(row != null)
                return row;
            if(DBG)
                log.debug("error with code not found for " + c + "." + errorCode);
        }
        row = contextText == null ? findUnique(qp, "initial_text=? and context_text is null", new Object[] {
            initialText
        }) : findUnique(qp, "initial_text=? and context_text=?", new Object[] {
            initialText, contextText
        });
        if(row != null)
            return row;
        if(DBG)
            log.debug("error with matching text not found: " + c + "." + initialText + "(" + contextText + ")");
        return null;
    }

    private ErrorMessageRow findUnique(QueryProcessor qp, String where, Object args[])
    {
        List list = (List)qp.doQuery(2, BatchedQuery.createFindAllQuery(ErrorMessageRow.TYPE, where, args));
        list.size();
        JVM INSTR lookupswitch 2: default 66
    //                   0: 52
    //                   1: 54;
           goto _L1 _L2 _L3
_L2:
        return null;
_L3:
        return (ErrorMessageRow)list.get(0);
_L1:
        ErrorMessageRow row;
        row = (ErrorMessageRow)list.get(0);
        log.error("uniquesness error: '" + where + "'(" + ObjectPrettyPrinter.toString(((Object) (args))) + "), returning first value: " + row);
        return row;
        DataException de;
        de;
        log.error("lookup failure: '" + where + "'(" + ObjectPrettyPrinter.toString(((Object) (args))) + "): " + de);
        return null;
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit log;
    private static final boolean DBG;
    private static Map perClass = new HashMap();
    private Class clientClass;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.error.ErrorManager.class);
        DBG = log.ison();
    }
}
