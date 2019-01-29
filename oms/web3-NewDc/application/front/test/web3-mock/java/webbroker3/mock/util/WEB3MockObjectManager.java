head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.37.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MockObjectManager.java;


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

import java.util.List;

public interface WEB3MockObjectManager
{
    /**
     * Mockされた関数の戻る値を設定
     * 
     */
    public void setMethodReturnValue(
        String l_strClassName,
        String l_strMethodName,
        Class[] l_methodPraType,  
        Object l_objReturnValues);
    
    /**
     * Mockされた関数の戻る値を取得 
     */
    public WEB3MockObjectReturnValue getMethodReturnValue(
        String l_strClassName,
        String l_strMethodName,
        Class[] l_methodPraType);
    
    public boolean isMockUsed(
        String l_strClassName,
        String l_strMethodName,
        Class[] l_methodPraType);
    
    public void setIsMockUsed(boolean l_blnIsMockBeUsed);
    
    public void clear();
    
    public void setMethodParamsValue(
        String l_strClassName,
        String l_strMethodName,
        Class[] l_methodPraType,  
        Object[] l_paramsValues);

    public WEB3MockObjectParamsValue getMethodParamsValue(
        String l_strClassName,
        String l_strMethodName,
        Class[] l_methodPraType);

    public List checkMethodParamsValue();
}
@
