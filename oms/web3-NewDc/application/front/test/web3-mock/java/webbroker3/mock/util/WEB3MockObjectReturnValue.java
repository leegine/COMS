head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.38.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MockObjectReturnValue.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mock.util;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;

public class WEB3MockObjectReturnValue
{

    public WEB3MockObjectReturnValue(Object l_objReturnValue)
    {
        super();
        returnValue = l_objReturnValue;
    }
    private Object returnValue;
    
    public void setReturnValue(Object l_objReturnValue) {
        returnValue = l_objReturnValue;
    }
    public Object asObject()
    {
        if (returnValue == null)
        {
            return null;
        }
        else
        {
            checkValue();
            return returnValue;            
        }
    }
    
    public void asVoid()
    {        
        checkValue();
    }
    
    public int asInt()
    {

        if (returnValue == null)
        {
            return 0;
        }    
        else if (returnValue instanceof Integer)
        {
            return ((Integer)returnValue).intValue();
        }
        else
        {
            checkValue();
            throw new RuntimeException("the return value's Type is incorrect , need Integer but is" + returnValue.getClass());
        }
    }
    
    public long asLong()
    {

        if (returnValue == null)
        {
            return 0;
        }    
        else if (returnValue instanceof Long)
        {      
            return ((Long)returnValue).longValue();
        }
        else
        {            
            checkValue();
            throw new RuntimeException("the return value's Type is incorrect , need Long but is" + returnValue.getClass());
        }
    }
 
    public double asDouble()
    {
        
        if (returnValue == null)
        {
            return 0;
        }    
        else if (returnValue instanceof Double)
        {
            return ((Double)returnValue).doubleValue();
        }
        else
        {            
            checkValue();
            throw new RuntimeException("the return value's Type is incorrect , need Double but is" + returnValue.getClass());
        }
    }
    
    public boolean asBoolean()
    {
        if (returnValue == null)
        {
            return false;
        }    
        else if (returnValue instanceof Boolean)
        {
            return ((Boolean)returnValue).booleanValue();
        }
        else
        {            
            checkValue();
            throw new RuntimeException("the return value's Type is incorrect , need Boolean but is" + returnValue.getClass());
        }
    }
    
    public float asFloat()
    {
        if (returnValue == null)
        {
            return 0;
        }    
        else if (returnValue instanceof Float)
        {
            return ((Float)returnValue).floatValue();
        }
        else
        {
            checkValue();
            throw new RuntimeException("the return value's Type is incorrect , need Float but is" + returnValue.getClass());
        }
    }
    
    public void asWEB3BaseException() throws WEB3BaseException
    {
        if (returnValue == null)
        {
            return;
        }
        if (WEB3BaseException.class.isAssignableFrom(returnValue.getClass()))
        {
            throw (WEB3BaseException)returnValue;
        }       
    }
    
    public void asWEB3SystemLayerException() throws WEB3SystemLayerException
    {
        if (returnValue == null)
        {
            return;
        }
        if (WEB3SystemLayerException.class.isAssignableFrom(returnValue.getClass()))
        {
            throw (WEB3SystemLayerException)returnValue;
        }       
    }

    public void asWEB3BaseRuntimeException() throws WEB3BaseRuntimeException
    {
        if (returnValue == null)
        {
            return;
        }
        if (WEB3BaseRuntimeException.class.isAssignableFrom(returnValue.getClass()))
        {
            throw (WEB3BaseRuntimeException)returnValue;
        }       
    }

    private void checkValue()
    {
        if (returnValue == null)
        {
            return;
        }
        if (Throwable.class.isAssignableFrom(returnValue.getClass() ))
        {
            throw new WEB3MockObjectRuntimeException((Throwable)returnValue);
        } 
    }
}
@
