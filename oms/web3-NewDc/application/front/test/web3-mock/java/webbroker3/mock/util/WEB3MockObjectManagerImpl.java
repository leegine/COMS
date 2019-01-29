head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.37.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MockObjectManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ���u�v�Z�@@�n���i�k���j�L�����i�@@���n�����ƕ�
File Name        : �e�X�gStubManager(TestStubManager)
Author Name      : SinoCom.cn
Revesion History : 2005/06/24 ���Ō� (���u) �V�K�쐬
Revesion History : 2007/06/01 ���� (���u) �ucheckMethodParamsValue�v�̃��\�b�h��ǉ�����B
*/

package webbroker3.mock.util;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import webbroker3.util.WEB3LogUtility;

/**
 * @@author ���Ō�
 * @@version 1.0
 */
public class WEB3MockObjectManagerImpl implements WEB3MockObjectManager
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MockObjectManagerImpl.class);
    
    /**
     * �R���X�g���N�^�֐�
     */
    public WEB3MockObjectManagerImpl()
    {
        super();
        returnValueMap = new HashMap();
        paramsValueMap = new HashMap();
        l_lisMockMethodValueNotChecked = new ArrayList();
        l_lisMockMethodValueChecked = new ArrayList();
        l_lisMockMethodParamsCheckedErrors = new ArrayList();
        isMockBeUsed = false;
    }

    /**
     * ���\�b�h�̖߂�l��Map
     * 
     * key:
     * value:
     */
    private HashMap returnValueMap;
    
    /**
     * ���\�b�h�̃p�����[�^��Map
     * 
     * key:
     * value:
     */
    private HashMap paramsValueMap;
    
    /**
     * Mock�g�p�t���O
     * true: Mock���g�p�B
     * false: Mock���g�p���Ȃ��B 
     * 
     * default��false
     */
    private boolean isMockBeUsed;

    private List l_lisMockMethodValueNotChecked = null;
    
    private List l_lisMockMethodValueChecked = null;
    
    private List l_lisMockMethodParamsCheckedErrors = null;

    /**
     * �֐��̖߂�l��ݒ�
     * 
     * ���͂��ꂽ�l��HashMap�ɐݒ肵�܂����B
     * key: Method
     * value: l_objReturnValues
     */
    public void setMethodReturnValue(
            String l_strClassName,
            String l_strMethodName,
            Class[] l_methodPraType,  
            Object l_objReturnValues)
    {
        //Mock�I�u�W�F�N�g�̃��\�b�h
        Method l_methodMockObject = getMethodWithMock(l_strClassName, l_strMethodName, l_methodPraType);        
        
        //���I�u�W�F�N�g�̃��\�b�h
        Method l_methodSource = getMethodWithOutMock(l_strClassName, l_strMethodName, l_methodPraType);
        
        //Method l_methodToSet = getMethodWithOutMock(l_methodWithMock)
        if (l_methodMockObject != null && l_methodSource != null)
        {
            returnValueMap.put(l_methodMockObject, l_objReturnValues);     
            
            if (l_methodPraType != null)
            {
                if(l_methodPraType.length!=0)
                {
                    l_lisMockMethodValueNotChecked.add(l_strClassName + "." + l_strMethodName);
                }
            }
            
            log.debug("##########Mock Successful!###########");
            log.debug("l_methodToSet  = " + l_methodMockObject);
            log.debug("##########Mock Successful!###########");
            return;
        }
        else
        {
            log.debug("**********Mock Error!**********");
            log.debug("l_methodToSet  = " + l_methodMockObject);
            log.debug("**********Mock Error!**********");
            return;
        }

    }
    
    public WEB3MockObjectReturnValue getMethodReturnValue(String l_strClassName, String l_strMethodName, Class[] l_methodPraType)
    {
        return this.getMethodReturnValue(
            getMethodWithMock(l_strClassName, l_strMethodName, l_methodPraType));
    }

    public boolean isMockUsed(String l_strClassName, String l_strMethodName, Class[] l_methodPraType)
    {        
        return isMockUsed(getMethodWithMock(l_strClassName, l_strMethodName, l_methodPraType));
    }

    private WEB3MockObjectReturnValue getMethodReturnValue(Method l_method) 
    {
        Object l_returnObj = returnValueMap.get(l_method);

        return new WEB3MockObjectReturnValue(l_returnObj);
    }

    private boolean isMockUsed(Method l_method)
    {        
        return isMockBeUsed && returnValueMap.containsKey(l_method);
    }
    
    /**
     * �w�肳�ꂽ�p�����[�^�̑΂���Method�I�u�W�F�N�g���擾�B 
     */
    private Method getMethodWithMock(
            String l_strClassName,
            String l_strMethodName,
            Class[] l_methodPraType)
    {
        try
        {
            Class l_classToSet = Class.forName(l_strClassName + "ForMock");
            return getMethod(l_classToSet, l_strMethodName, l_methodPraType);
        } 
        catch (ClassNotFoundException l_nsfex)
        {
            log.error("�w�肳�ꂽ�N���X�����������Ȃ��ł��B ClassName = " + l_strClassName, l_nsfex);
            return null;
        }
    }
    
    /**
     * �w�肳�ꂽ�p�����[�^�̑΂���Method�I�u�W�F�N�g���擾�B 
     */
    private Method getMethodWithOutMock(
            String l_strClassName,
            String l_strMethodName,
            Class[] l_methodPraType)
    {
        try
        {
            Class l_classToSet = Class.forName(l_strClassName);
            return getMethod(l_classToSet, l_strMethodName, l_methodPraType);
        } 
        catch (ClassNotFoundException l_nsfex)
        {
            log.error("�w�肳�ꂽ�N���X�����������Ȃ��ł��B ClassName = " + l_strClassName, l_nsfex);
            return null;
        }
    }
    
    /**
     * �w�肳�ꂽ�p�����[�^�̑΂���Method�I�u�W�F�N�g���擾�B 
     */
    private Method getMethod(
            Class l_classToSet,
            String l_strMethodName,
            Class[] l_methodPraType)
    {
        Method l_method = null;
        try
        {    
            if (l_methodPraType != null)
            {
                l_method = l_classToSet.getMethod(l_strMethodName, l_methodPraType);
            }          
            else
            {
                Method[] l_methods = l_classToSet.getMethods();
                int l_intOverLoadMethodCnt = 0;
                for (int i = 0; i < l_methods.length; i ++)
                {
                    if (l_methods[i].getName().equals(l_strMethodName))
                    {
                        l_method = l_methods[i];
                        l_intOverLoadMethodCnt++;
                        
                        if (l_intOverLoadMethodCnt > 1)
                        {                            
                            log.error("find overLoad Method, the l_methodPraType must be setted");
                            l_method = null;
                            break;
                        }
                    }      
                }                
            }
            return l_method;
        }
        catch (NoSuchMethodException l_nsfex)
        {            
            log.error("�w�肳�ꂽ���\�b�h���܂��̓p�����[�^�^�C�v���������Ȃ��ł��B " + 
                " methodName = " + l_strMethodName + 
                "l_methodPraType = " + Arrays.asList(l_methodPraType), l_nsfex);
            return null;
        }
    }
    
    /**
     *  Mock�I�u�W�F�N�g�g�p�̃t���O
     * true: Mock���g�p�B
     * false: Mock���g�p���Ȃ��B 
     */
    public void setIsMockUsed(boolean l_blnIsMockBeUsed)
    {
        isMockBeUsed = l_blnIsMockBeUsed;
    }
    
    /**
     *  clear
     */
    public void clear()
    {
        returnValueMap.clear();
        paramsValueMap.clear();
        l_lisMockMethodValueNotChecked.clear();
        l_lisMockMethodValueChecked.clear();
        l_lisMockMethodParamsCheckedErrors.clear();
        isMockBeUsed = false;
    }

    public void setMethodParamsValue(
        String l_strClassName, 
        String l_strMethodName, 
        Class[] l_methodPraType, 
        Object[] l_paramsValues)
    {
        Method l_method = getMethodWithOutMock(l_strClassName, l_strMethodName, l_methodPraType);
        List l_lisParamsValue = new ArrayList();
        if (paramsValueMap.containsKey(l_method))
        {
            l_lisParamsValue = (List)paramsValueMap.get(l_method);
            l_lisParamsValue.add(l_paramsValues);            
        }
        else
        {
            l_lisParamsValue.add(l_paramsValues);            
        }
        paramsValueMap.put(l_method, l_lisParamsValue);
    }

    public WEB3MockObjectParamsValue getMethodParamsValue(
        String l_strClassName, 
        String l_strMethodName, 
        Class[] l_methodPraType)
    {
        Method l_method = getMethodWithOutMock(l_strClassName, l_strMethodName, l_methodPraType);
        if (!paramsValueMap.containsKey(l_method))
        {
            throw new WEB3MockObjectRuntimeException("���\�b�h:" + l_method + "�����܂�R�[������Ă��܂���.");
        }

        l_lisMockMethodValueChecked.add(l_strClassName+"."+l_strMethodName);
        return new WEB3MockObjectParamsValue((List)paramsValueMap.get(l_method));
    }

    public List checkMethodParamsValue()
    {
        
        String[] l_strTotalCheckedMethodParams = (String[]) l_lisMockMethodValueChecked.toArray(new String[0]);
        Arrays.sort(l_strTotalCheckedMethodParams);
        int l_intCheckedMethodParamsSize = l_lisMockMethodValueNotChecked.size();
        
        for(int i=0;i<l_intCheckedMethodParamsSize;i++)
        {
            int l_search = Arrays.binarySearch(l_strTotalCheckedMethodParams, l_lisMockMethodValueNotChecked.get(i));
            if (l_search < 0)
            {
                l_lisMockMethodParamsCheckedErrors.add(l_lisMockMethodValueNotChecked.get(i));
            }
        }
        return l_lisMockMethodParamsCheckedErrors;
    }
}
@
