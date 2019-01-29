head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.32.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	89c4d7d7dfd671c;
filename	TestSpecialClassUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ɃN���X�́A����ȃ��\�b�h�̎����e�X�g�N���X(TestSpecialClassUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/12/13  ���Ō� (���u) �V�K�쐬
*/

package test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.comm.InternalAccessor;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ���ɃN���X�́A����ȃ��\�b�h�̎����e�X�g�N���X�B
 * @@author ���Ō�
 * @@version 1.0
 */
public class TestSpecialClassUtility extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        TestSpecialClassUtility.class);
    
    private static StringBuffer l_subStrSuccessLog = new StringBuffer("\n");
    
    public static int intNoThrowEx = 1;
    
    public static int intThrowEx = 2;
    
    public static int intTestBoth = 3;
    
    public static boolean isTest4AppPlugin = false;
    
    /**
     * �R���X�g���N�^
     */
    public TestSpecialClassUtility(String arg)
    {
        super(arg);
    }

    /**
     * ���N�G�X�g.createResponse()�̎����e�X�g<BR>
     * <BR>
     * ���Ă��ꂽ���N�G�X�g.createResponse()�������������`�F�b�N����B<BR>
     * Class l_tagetRequest<BR>
     * @@param l_tagetRequest - �e�X�g���ꂽ���N�G�X�g
     */
    public static void testCreateResponse(Class l_tagetRequest)
    {
        final String STR_METHOD_NAME = ".testCreateResponse(Class)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Method thisMethod = l_tagetRequest.getDeclaredMethod("createResponse",null);
            Object obj = thisMethod.invoke(l_tagetRequest.newInstance(),null);
            
            String str = obj.getClass().toString();
            
            String strExpect = l_tagetRequest.toString();
            strExpect = strExpect.substring(0,strExpect.length()-7) + "Response";
            
            assertEquals(strExpect,str);
            
        } catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���N�G�X�g.createResponse()�̎����e�X�g<BR>
     * <BR>
     * ���Ă��ꂽ���N�G�X�g.createResponse()�������������`�F�b�N����B<BR>
     * @@param l_tagetRequest - �e�X�g���ꂽ���N�G�X�g
     */
    public static void testCreateResponse(Class[] l_tagetRequest)
    {
        final String STR_METHOD_NAME = ".testCreateResponse(Class[])";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            for (int i = 0; i < l_tagetRequest.length; i++) {
                Method thisMethod = l_tagetRequest[i].getDeclaredMethod("createResponse",null);
                Object obj = thisMethod.invoke(l_tagetRequest[i].newInstance(),null);
                
                String str = obj.getClass().toString();
                
                String strExpect = l_tagetRequest.toString();
                strExpect = strExpect.substring(0,strExpect.length()-7) + "Response";
                
                assertEquals(strExpect,str);
            }
            
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ServiceInterceptor.onReturn()��ServiceInterceptor.onThrowable()�̎����e�X�g<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����邩���`�F�b�N����B<BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * ��L�ȊO�̓��e���e�X�g�̏ꍇ�AtestServiceInterceptor(Class, Object[])���g�p���Ă��������B<BR>
     * @@param l_inteceptor - �e�X�g���ꂽServiceInterceptor
     */
    public static void testServiceInterceptor(Interceptor l_inteceptor)
    {
        final String STR_METHOD_NAME = ".testServiceInterceptor(Interceptor)";
        log.entering(STR_METHOD_NAME);
        testServiceInterceptor(l_inteceptor, null);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ServiceInterceptor.onReturn()��ServiceInterceptor.onThrowable()�̎����e�X�g<BR>
     * <BR>
     * �P�jThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����邩���`�F�b�N����B
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * �Q�jThreadLocalSystemAttributesRegistry�̎w�肳�ꂽ���e���N���A����邩���`�F�b�N����B
     * �p�����[�^.l_clearObjects<BR>
     * @@param l_inteceptor - �e�X�g���ꂽServiceInterceptor
     * @@param l_clearObjects - 
     */
    public static void testServiceInterceptor(Interceptor l_inteceptor,String[] l_clearObjects)
    {
        final String STR_METHOD_NAME = ".testServiceInterceptor(Interceptor,Object[])";
        log.entering(STR_METHOD_NAME);
        
        try 
        {
            if (l_clearObjects == null)
            {
                l_clearObjects = new String[]{};
            }
            //������ԊǗ�.TIMESTAMP_TAG
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, "WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG");
            //������ԊǗ�.OFFSET_TAG
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG, "WEB3GentradeTradingTimeManagement.OFFSET_TAG");
            //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, "WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH");
            
            for (int i = 0; i < l_clearObjects.length; i++)
            {
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    l_clearObjects[i], l_clearObjects[i]);                  
            }
            
            l_inteceptor.onReturn(null, null);
            
            // ������ԊǗ�.TIMESTAMP_TAG
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));
            // ������ԊǗ�.OFFSET_TAG
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG));
            // ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));
            
            for (int i = 0; i < l_clearObjects.length; i++)
            {
                assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                    l_clearObjects[i]));                  
            }
            
            
            //������ԊǗ�.TIMESTAMP_TAG
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, "WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG");
            //������ԊǗ�.OFFSET_TAG
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG, "WEB3GentradeTradingTimeManagement.OFFSET_TAG");
            //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, "WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH");  
            
            for (int i = 0; i < l_clearObjects.length; i++)
            {
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    l_clearObjects[i], l_clearObjects[i]);                  
            }
                        
            l_inteceptor.onThrowable(null, null);
            
            // ������ԊǗ�.TIMESTAMP_TAG
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));
            // ������ԊǗ�.OFFSET_TAG
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG));
            // ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));
            
            for (int i = 0; i < l_clearObjects.length; i++)
            {
                assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                    l_clearObjects[i]));                  
            }
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * XXXrequest�N���Xvalidate()�e�X�g���\�b�h<BR>
     * @@param l_classObject ���e�X�g���ꂽ�N���X��Class�^<BR>
     *              (���ӁF�e�X�g���ꂽ���\�b�h�����ۃN���X�ɓ��������C<BR>
     *              l_classObject���e�X�g���ꂽ�N���X�̌p���N���XXXXForTest�̃N���X�^)<BR>
     * @@param l_strFilePath .csv�t�@@�C���̕����o�X<BR> 
     *              �ieg: l_strFilePath = "application/front/test/web3-xbfeq-lmz/test/java"�j<BR>
     * @@version 1.0<BR>
     */
    public void testRequestValidate(Class l_classObject , String l_strFilePath)  
    {
        boolean errorFlag = false;
        
        final String STR_METHOD_NAME =".requestValidateTest(Class,String)";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_runLogInfo = new StringBuffer();
        CommonUnit commonUnit = new CommonUnit();
        try {
            // Field Object Array
            List l_listField = new ArrayList();
            // ���t�t�H�[�}�b�g
            SimpleDateFormat l_dateFormat = new SimpleDateFormat("yyyyMMdd");
            
            Object[] l_objectTempArray = commonUnit.resetArrayLength(commonUnit.getContent(l_strFilePath , l_classObject));
            
            // �e�X�g���ꂽ�I�u�W�F�N�g�Ώ�
            Object l_doObject = l_classObject.newInstance();
            
            if (l_objectTempArray == null) 
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            
            String[] l_caseNO = new String[((String[])l_objectTempArray[0]).length];
            for (int i = 0; i < ((String[])l_objectTempArray[0]).length; i++) 
            {
                if ("".equals((String)((String[])l_objectTempArray[0])[i]))
                {
                    l_caseNO[i] = "�� " + i + " ��";
                }
                else
                {
                    l_caseNO[i] = (String)((String[])l_objectTempArray[0])[i];
                }
            }
            
            Object[] l_objectArray = new Object[l_objectTempArray.length-1];
            
            for (int i = 0; i < l_objectTempArray.length - 1; i++) 
            {
                l_objectArray[i] = l_objectTempArray[i+1];
            }
    
            int l_lengthA = l_objectArray.length;
            for (int i = 0; i < l_objectArray.length - 2; i++) 
            {
                String l_strTemp = (String)((String[])l_objectArray[i])[0];
                Field f = l_classObject.getField(l_strTemp);
                l_listField.add(f);
            }
            
            // �G���[���b�Z�[�W�����
            String[] errorCodeExpect = (String[]) l_objectArray[l_lengthA-2];
            String[] errorMessageExpect = (String[]) l_objectArray[l_lengthA-1];

            // �Ώۃt�B�[���h�֒l���グ��
            int l_lengthB = ((String[])l_objectArray[0]).length;
            for (int i = 1; i < l_lengthB; i++) 
            {
                log.debug(l_caseNO[i] + " -- begin");
                String successMsg = null;
                String errorMsg = null;
                String successInfo = null;
                String lostInfo = null;
                String testClassPath = null;

                if (l_classObject.getName().toString().indexOf("ForTest") < 0) 
                {
                    testClassPath = l_classObject.getName().toString() + "Test";
                }
                else 
                {
                    int length = l_classObject.getName().toString().length();
                    testClassPath = l_classObject.getName().toString().substring(0,length-7) + "Test";
                }
                
                successMsg = "\n||||||||||*****  " + testClassPath + " -- " + l_caseNO[i] + " �P�[�X ����";
                errorMsg = "\n||||||||||*****  " + testClassPath + " -- " + l_caseNO[i] + " �P�[�X ���s";
                successInfo = "\n    " + l_caseNO[i] + " �P�[�X ����";
                lostInfo = "\n    " + l_caseNO[i] + " �P�[�X ���s";
                
                try {
                    StringBuffer runDate = new StringBuffer("\n" + l_caseNO[i] +  "Run Date : ");
                    for (int j = 0; j < l_listField.size(); j++) 
                    {
                        String tempRunDate = null;
                        
                        Field l_field = (Field) l_listField.get(j);
                        
                        if (!"null".equals((String)((String[])l_objectArray[j])[i]))
                        {   
                            if (l_field.getType().equals(java.util.Date.class))
                            {
                                Date tempDate = null;
                                long l_dateTemp;
                                String l_strTemp = (String)((String[])l_objectArray[j])[i];
                                l_dateTemp = l_dateFormat.parse(l_strTemp).getTime();
                                tempDate = new Date(l_dateTemp);
                                l_field.set(l_doObject,tempDate);
                                
                                tempRunDate = WEB3DateUtility.formatDate(tempDate, "yyyyMMdd");
                            }
                            else if(l_field.getType().equals(int.class))
                            {
                                int tempDate = Integer.parseInt((String)((String[])l_objectArray[j])[i]);
                                l_field.setInt(l_doObject , tempDate);
                                
                                tempRunDate = Integer.toString(tempDate);
                            }
                            else if (l_field.getType().equals(java.lang.String.class))
                            {
                                StringBuffer l_sb = new StringBuffer((String)((String[])l_objectArray[j])[i]);
                                String l_str = l_sb.toString();
                                
                                if (l_sb.length() > 1 && l_sb.charAt(0) == '#')
                                {
                                    l_str = l_sb.substring(1, l_sb.length()); 
                                }
                                l_field.set(l_doObject , l_str);
                                
                                tempRunDate = l_str;
                            }
                            else 
                            {
                                log.debug(errorMsg + "  --  �^����ꂽ�l�̌^���~�X�ƂȂ���");
                                l_runLogInfo.append(lostInfo);
                                errorFlag = true;
                                break;
                            }
                        }
                        else 
                        {
                            l_field.set(l_doObject , null);
                            
                            tempRunDate = "null";
                        }
                        
                        if (j == 0)
                        {
                            runDate.append("{" + tempRunDate + ",");
                        }
                        else if (j == l_listField.size()-1) 
                        {
                            runDate.append(tempRunDate + "}");
                        }
                        else
                        {
                            runDate.append(tempRunDate + ",");
                        }
                    }
                    
                    log.debug(runDate.toString());

                    try 
                    {
                        Method thisMethod = l_classObject.getMethod("validate" , null);
                        thisMethod.invoke(l_doObject , null);
        
                        if (commonUnit.noExAssertEquals(errorMsg,(String)errorCodeExpect[i].toUpperCase(),"OK") &&
                            (commonUnit.noExAssertEquals(errorMsg,(String)errorMessageExpect[i].toUpperCase(),"OK")))
                        {
                            log.debug(successMsg);
                            l_runLogInfo.append(successInfo);
                        }
                        else
                        {
                            errorFlag = true;
                            log.debug(errorMsg);
                            l_runLogInfo.append(lostInfo);
                        }
                    } 
                    catch(InvocationTargetException ex) 
                    {
                        if (!WEB3BusinessLayerException.class.equals(ex.getTargetException().getClass())) 
                        {
                            log.error("\n�� " + i + " �ڃG���[����",ex.getTargetException());
                            errorFlag = true;
                            continue;
                        }
                        
                        WEB3BaseException doTempClass = (WEB3BaseException)ex.getTargetException();                    
                        if (commonUnit.noExAssertEquals(
                                errorMsg,errorCodeExpect[i],doTempClass.getErrorInfo().getErrorTag()) &&
                            commonUnit.noExAssertEquals(
                                errorMsg,errorMessageExpect[i],doTempClass.getErrorMessage()))
                        {
                            log.debug(successMsg);
                            l_runLogInfo.append(successInfo);
                        }
                        else
                        {
                            errorFlag = true;
                            log.debug("",(WEB3BaseException)ex.getTargetException());
                            l_runLogInfo.append(lostInfo);
                        }
                    }
                }
                catch(Exception l_ex)
                {
                    log.debug(errorMsg);
                    log.error(STR_METHOD_NAME, l_ex);
                    l_runLogInfo.append(lostInfo);
                    errorFlag = true;
                }
                log.debug(l_caseNO[i] + " -- end\n");
            }
        }
        catch(Exception l_ex) 
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
            
        }
        
        if (errorFlag)
        {
            fail(l_runLogInfo.toString());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public static String getResponseClass(Class l_classRequest)
    {
        String l_strResponse = null;
        String l_strRequest = null;
        
        l_strRequest = l_classRequest.getName();
        
        l_strResponse = l_strRequest.substring(0,l_strRequest.length()-7) + "Response";
        System.out.println(l_strResponse);
        return l_strResponse;
    }
    
    public static void testAppPlugIn(Class[] l_requestArray,Class[] l_serviceArray,int l_flag)
    {
        String STR_METHOD_NAME =".testAppPlugIn(Class[] ,Class[] ,int)";
        log.entering(STR_METHOD_NAME);
        
        for (int i = 0; i < l_serviceArray.length; i++)
        {
            Services.addInterceptor(l_serviceArray[i] , new WEB3InterceptorForTest());
        }
        
        isTest4AppPlugin = true;
        
        for (int i = 0; i < l_requestArray.length; i++)
        {
            try
            {
                InternalAccessor accessor = new InternalAccessor();
                Request request = (Request) l_requestArray[i].newInstance();
                Response response = accessor.doRequest(request);

                if (Response.class.equals(response.getClass()))
                {
                    if (l_flag != intNoThrowEx && l_flag != intTestBoth)
                    {
                    log.debug(l_subStrSuccessLog.toString());
                    log.exiting(STR_METHOD_NAME);
                    fail();
                    }
                    assertEquals("The handler threw an exception during message handling.",
                        response.server_exception.getErrorMessage());
                    l_subStrSuccessLog.append(l_requestArray[i].getName() + "-- Throw Exception--" + " Don't success!!!\n");
                } 
                else
                {
                    if (l_flag != intThrowEx && l_flag != intTestBoth)
                    {
                        log.debug(l_subStrSuccessLog.toString());
                        log.exiting(STR_METHOD_NAME);
                        fail();
                    }
                    assertEquals(getResponseClass(l_requestArray[i]),response.getClass().getName());
                    l_subStrSuccessLog.append(l_requestArray[i].getName() + "-- Throw Exception --" + " success!!!\n");
                }
            } catch(Exception l_ex)
            {
                isTest4AppPlugin = false;
                log.debug(l_subStrSuccessLog.toString());
                log.error("",l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        
        isTest4AppPlugin = false;
        log.debug(l_subStrSuccessLog.toString());
        log.exiting(STR_METHOD_NAME);
    }
    
    public class CommonUnit 
    {
        /**
         * �t�@@�C�����f�[�^��ǂݏo��<BR>
         * 
         * @@param l_strFilePath <BR>
         * @@param l_objectClass <BR>
         * @@return Object[] <BR>
         */
        private Object[] getContent(String l_strFilePath, Class l_objectClass)
        {
            final String STR_METHOD_NAME = ".getContent(String ,Class )";
            log.entering(STR_METHOD_NAME);

            Object[] l_objs = null;
            String l_strContent = "";
            if (l_strFilePath == null || "".equals(l_strFilePath))
            {
                return l_objs;
            }

            String l_strClassPath = l_objectClass.getName().toString();
            l_strClassPath = l_strClassPath.replace('.', '/');

            String l_strPath = "./" + l_strFilePath + "/" + l_strClassPath + ".csv";
            File file = new File(l_strPath);
            BufferedReader l_br = null;
            try
            {
                StringBuffer bstr = new StringBuffer();
                l_br = new BufferedReader(new FileReader(file));
                int l_intBb = l_br.read();
                while (l_intBb != -1)
                {
                    bstr.append((char) l_intBb);

                    l_intBb = l_br.read();
                }
                l_strContent = bstr.toString();
                String l_strArrayS[] = l_strContent.split("\r\n");
                l_objs = new Object[l_strArrayS.length];
                for (int i = 0; i < l_strArrayS.length; i++)
                {
                    String l_strTemp = l_strArrayS[i];
                    int l_intBegin = l_strTemp.indexOf(",");

                    l_strTemp = l_strTemp.substring(l_intBegin + 1);
                    String l_strArrayTemps[] = l_strTemp.split(",");
                    for (int j = 0; j < l_strArrayTemps.length; j++)
                    {
                        l_strArrayTemps[j] = l_strArrayTemps[j].trim().toString();
                    }
                    l_objs[i] = l_strArrayTemps;
                }
            }
            catch (FileNotFoundException l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
            catch (IOException l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
            finally
            {
                try
                {
                    l_br.close();
                }
                catch (IOException l_ioe)
                {
                    l_ioe.printStackTrace();
                    log.exiting(STR_METHOD_NAME);
                    log.error(STR_METHOD_NAME, l_ioe);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return l_objs;
        }
        
        private boolean noExAssertEquals(String errorMessage, String expected, String actual) 
        {
            if(expected == null && actual == null)
            {
                return true;
            }
                
            if(expected != null && expected.equals(actual))
            {
                return true;
            }
            else
            {
                log.debug(errorMessage);
                log.error("expected<" + expected + "> but actual<" + actual + ">");
                return false;
            }
        }
        
        private Object[] resetArrayLength(Object[] l_objectArray) 
        {
            int l_length = l_objectArray.length;
            int l_maxLength = 0;
            for (int i = 1; i < l_length-1; i++) 
            {
                if (((Object[])l_objectArray[i]).length <= ((Object[])l_objectArray[i+1]).length) {
                    l_maxLength = ((Object[])l_objectArray[i+1]).length;
                }
                else
                {
                    l_maxLength = ((Object[])l_objectArray[i]).length;
                }
            }
            
            for (int i = 0; i <l_length; i++)
            {
                int l_tempLength = ((Object[])l_objectArray[i]).length;
                if(l_tempLength < l_maxLength)
                {
                    Object[] l_tempArray = new String[l_maxLength];
                    for (int j = 0; j < l_maxLength; j++)
                    {
                        if (j < l_tempLength) 
                        {
                            l_tempArray[j] = ((Object[])l_objectArray[i])[j];
                        }
                        else 
                        {
                            l_tempArray[j] = "";
                        }
                    }
                    l_objectArray[i] = l_tempArray;
                }
            }
            
            return l_objectArray;
        }
    }
    
    public static class WEB3InterceptorForTest implements Interceptor
    {

        public Object onCall(Method arg0, Object[] arg1)
        {
            String STR_METHOD_NAME = "onCall(Method[],Object[])";
            
            //AppPlugin�̃e�X�g���\�b�h�����ŁA��O���X���[�ł��B
            if (isTest4AppPlugin)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "onCall ERROR");
            }
            return null;
                
        }

        public void onReturn(Object arg0, Object arg1)
        {
            
        }

        public void onThrowable(Object arg0, Throwable arg1)
        {
            
        }
        
    }
}
@
