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
Copyright        : 中訊計算機@系統（北京）有限公司　@第一系統事業部
File Name        : テストStubManager(TestStubManager)
Author Name      : SinoCom.cn
Revesion History : 2005/06/24 王暁傑 (中訊) 新規作成
Revesion History : 2007/06/01 金傑 (中訊) 「checkMethodParamsValue」のメソッドを追加する。
*/

package webbroker3.mock.util;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import webbroker3.util.WEB3LogUtility;

/**
 * @@author 王暁傑
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
     * コンストラクタ関数
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
     * メソッドの戻る値のMap
     * 
     * key:
     * value:
     */
    private HashMap returnValueMap;
    
    /**
     * メソッドのパラメータのMap
     * 
     * key:
     * value:
     */
    private HashMap paramsValueMap;
    
    /**
     * Mock使用フラグ
     * true: Mockを使用。
     * false: Mockを使用しない。 
     * 
     * defaultがfalse
     */
    private boolean isMockBeUsed;

    private List l_lisMockMethodValueNotChecked = null;
    
    private List l_lisMockMethodValueChecked = null;
    
    private List l_lisMockMethodParamsCheckedErrors = null;

    /**
     * 関数の戻る値を設定
     * 
     * 入力された値をHashMapに設定しました。
     * key: Method
     * value: l_objReturnValues
     */
    public void setMethodReturnValue(
            String l_strClassName,
            String l_strMethodName,
            Class[] l_methodPraType,  
            Object l_objReturnValues)
    {
        //Mockオブジェクトのメソッド
        Method l_methodMockObject = getMethodWithMock(l_strClassName, l_strMethodName, l_methodPraType);        
        
        //元オブジェクトのメソッド
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
     * 指定されたパラメータの対するMethodオブジェクトを取得。 
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
            log.error("指定されたクラス名が正しくないです。 ClassName = " + l_strClassName, l_nsfex);
            return null;
        }
    }
    
    /**
     * 指定されたパラメータの対するMethodオブジェクトを取得。 
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
            log.error("指定されたクラス名が正しくないです。 ClassName = " + l_strClassName, l_nsfex);
            return null;
        }
    }
    
    /**
     * 指定されたパラメータの対するMethodオブジェクトを取得。 
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
            log.error("指定されたメソッド名またはパラメータタイプが正しくないです。 " + 
                " methodName = " + l_strMethodName + 
                "l_methodPraType = " + Arrays.asList(l_methodPraType), l_nsfex);
            return null;
        }
    }
    
    /**
     *  Mockオブジェクト使用のフラグ
     * true: Mockを使用。
     * false: Mockを使用しない。 
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
            throw new WEB3MockObjectRuntimeException("メソッド:" + l_method + "があまりコールされていません.");
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
