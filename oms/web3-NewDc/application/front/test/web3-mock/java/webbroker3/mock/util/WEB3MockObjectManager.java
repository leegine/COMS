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
Copyright        : ���u�v�Z�@@�n���i�k���j�L�����i�@@���n�����ƕ�
File Name        : �e�X�gStubManager(TestStubManager)
Author Name      : SinoCom.cn
Revesion History : 2005/06/24 ���Ō� (���u) �V�K�쐬
Revesion History : 2007/06/01 ���� (���u) �ucheckMethodParamsValue�v�̃��\�b�h��ǉ�����B
*/

package webbroker3.mock.util;

import java.util.List;

public interface WEB3MockObjectManager
{
    /**
     * Mock���ꂽ�֐��̖߂�l��ݒ�
     * 
     */
    public void setMethodReturnValue(
        String l_strClassName,
        String l_strMethodName,
        Class[] l_methodPraType,  
        Object l_objReturnValues);
    
    /**
     * Mock���ꂽ�֐��̖߂�l���擾 
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
