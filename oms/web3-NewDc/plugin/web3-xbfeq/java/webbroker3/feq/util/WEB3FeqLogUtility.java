head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqLogUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 * Created on 2005/07/28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package webbroker3.feq.util;

import java.lang.reflect.Field;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.xtrade.kernel.message.Response;

import webbroker3.common.WEB3BaseException;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WEB3FeqLogUtility
{


    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqLogUtility.class);
    
    
    /**
     * 
     */
    public WEB3FeqLogUtility()
    {
        super();
    }
    
    /**
     * 引数のParamsオブジェクトの値を表示する<BR>
     * ColunmName1, ColunmName12, ColunmName3 ..... ....<BR>
     * value1,value2,value3........<BR>
     * 順序がＤＢレイアウトと一致する<BR>
     * @@param l_params- (Paramsオブジェクト)<BR>     
     */
    public static void printParamsValues(Params l_params)
    {
        if (l_params == null)
        {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ OutPut table don't have data for print @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            return;
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ OutPut table " + l_params.getRowType() + " begin @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(getParamsColumns(l_params));
        System.out.println(getParamsValues(l_params));
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ OutPut table " + l_params.getRowType() + " End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    
    /**
     * 引数のParamsオブジェクトの値を表示する<BR>
     * ColunmName1, ColunmName12, ColunmName3 ..... ....<BR>
     * value1,value2,value3........<BR>
     * value2_1,value2_2,value2_3........<BR>
     * 順序がＤＢレイアウトと一致する<BR>
     * @@param l_params- (Paramsオブジェクト)<BR>     
     */
    public static void printParamsValues(Params[] l_params)
    {
        if (l_params == null || l_params.length == 0)
        {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ OutPut table don't have data for print @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            return;
        }        
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ OutPut table " + l_params[0].getRowType() + " begin @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(getParamsColumns(l_params[0]));
        for (int i = 0; i < l_params.length; i++)
        {            
            System.out.println(getParamsValues(l_params[i]));   
        }                        
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ OutPut table " + l_params[0].getRowType() + " End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        
        return;
    }
    
    private static String getParamsColumns(Params l_params)
    {
        String l_strOutputString = l_params.toString().substring(1, l_params.toString().length() - 1);               
        
        String[] l_strColumnAndValues = l_strOutputString.split(",");
        
        int l_intLen = l_strColumnAndValues.length;
        
        StringBuffer l_strColumns = new StringBuffer();
        
        String l_strTmp = null;
        for (int i = 0; i < l_intLen; i++)
        {
            l_strTmp = l_strColumnAndValues[i];
            
            l_strColumns.append("'");                        
            l_strColumns.append(l_strTmp.substring(0, l_strTmp.indexOf("=")));            
            l_strColumns.append("\t");            
        }
        return l_strColumns.toString();
    }
    
    private static String getParamsValues(Params l_params)
    {
        String l_strOutputString = l_params.toString().substring(1, l_params.toString().length() - 1);               
        
        String[] l_strColumnAndValues = l_strOutputString.split(",");
        
        int l_intLen = l_strColumnAndValues.length;
        StringBuffer l_strValues = new StringBuffer();
        
        String l_strTmp = null;
        for (int i = 0; i < l_intLen; i++)
        {
            l_strTmp = l_strColumnAndValues[i];
            
            l_strValues.append("'");           
            l_strValues.append(l_strTmp.substring(l_strTmp.indexOf("=") + 1));  
            l_strValues.append("\t");
        }
        return l_strValues.toString();
    }
    
    public static String getParamsValuesForAssert(Params l_params)
    {
        String l_strOutputString = l_params.toString().substring(1, l_params.toString().length() - 1);               
        
        String[] l_strColumnAndValues = l_strOutputString.split(",");
        
        int l_intLen = l_strColumnAndValues.length;
        StringBuffer l_strValues = new StringBuffer();
        
        String l_strTmp = null;
        for (int i = 0; i < l_intLen; i++)
        {
            l_strTmp = l_strColumnAndValues[i];
            
            l_strValues.append("'");           
            l_strValues.append(l_strTmp.substring(l_strTmp.indexOf("=") + 1));  
            l_strValues.append("#");
        }
        return l_strValues.toString();
    }
    
    public static String getParamsValuesForAssert(Params[] l_params)
    {
        StringBuffer l_strValues = new StringBuffer();
        
        String l_strTmp = null;
        for (int i = 0; i < l_params.length; i++)
        {
            l_strValues.append(getParamsValuesForAssert(l_params[i]));
            l_strValues.append("@@");
        }
        return l_strValues.toString();
    }
    
    private static void printResponseValues(Response l_response)
    {
        Field[] l_fields = 
            l_response.getClass().getFields();
        
        String[] l_strFieldNames = new String[l_fields.length];
        Object[] l_objFieldValues = new Object[l_fields.length];
        try
        {
            for (int i = 0; i < l_fields.length; i++)
            {
                l_strFieldNames[i] = l_fields[i].getName();
                //l_fields[i].setAccessible(true);
                l_objFieldValues[i] = l_response.getClass().getField(l_strFieldNames[i]).get(l_response);
            }            
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
        }
        String l_strNameForPrint = ""; 
        
        for (int i = 0; i < l_fields.length; i++)
        {
            l_strNameForPrint = l_strNameForPrint + l_strFieldNames[i] + "\t";
        }
        String l_strValueForPrint = "";
        for (int i = 0; i < l_fields.length; i++)
        {
            l_strValueForPrint = l_strValueForPrint + "'" + l_objFieldValues[i] + "\t";
        }
        
        System.out.println(l_strNameForPrint);
        System.out.println(l_strValueForPrint);
    }
    
    public static String getQueryString(
        String l_strWhere, 
        String l_strOrderby, 
        String l_strConditions, 
        Object[] l_objs)
    {
        String[] l_strWheres = l_strWhere.split("\\?");
        
        StringBuffer l_strReturnValue = new StringBuffer();

        l_strReturnValue.append(getQueryString(l_strWhere, l_objs));
        if (l_strOrderby != null)
        {
            l_strReturnValue.append(" order by "); 
            l_strReturnValue.append(l_strOrderby);    
        }
        if (l_strConditions != null)
        {
            l_strReturnValue.append(l_strConditions);    
        }
                
        return l_strReturnValue.toString();
    }
    
    public static String getQueryString(
        String l_strWhere, 
        Object[] l_objs)
    {
        String[] l_strWheres = l_strWhere.split("\\?");
        
        StringBuffer l_strReturnValue = new StringBuffer();
        
        if (l_strWheres.length - 1 != l_objs.length)
        {
            log.debug("&&&&&&&&&& error where printQueryString &&&&&&&&&& ");
            log.debug("l_objs.length = " + l_objs.length + "But the amount of ”?” is " + (l_strWheres.length - 1));
            log.debug("&&&&&&&&&& error where printQueryString &&&&&&&&&& ");
            return "";
        }
        for (int i = 0; i < l_strWheres.length; i++)
        {
            if (i != l_strWheres.length - 1)
            {
                l_strReturnValue.append(l_strWheres[i]);
                l_strReturnValue.append(l_objs[i]);   
            }
            else
            {
                l_strReturnValue.append(l_strWheres[i]);
            }
        }                
        return l_strReturnValue.toString();
    }
}
@
