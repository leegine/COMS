head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �t�w�l���������T�[�r�X�C���^�Z�v�^(WEB3ToStopOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 ������(���u) �V�K�쐬
*/
package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;

import webbroker3.util.WEB3LogUtility;


/**
 * (�t�w�l���������T�[�r�X�C���^�Z�v�^)<BR>
 * �t�w�l���������T�[�r�X�C���^�Z�v�^<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3ToStopOrderServiceInterceptor implements Interceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopOrderServiceInterceptor.class);
    
    /**
     * @@roseuid 436ACB98034B
     */
    public WEB3ToStopOrderServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * �����ɉ������Ȃ��B<BR>
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     * @@roseuid 434C7BCA0026
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam) ";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * �����ɉ������Ȃ��B<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 434C7BCA0093
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * �����ɉ������Ȃ��B<BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 434C7BCA00D2
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
     
    }
}
@
