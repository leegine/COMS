head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LogSysTimeInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ԃ��O�o�̓C���^�Z�v�^�N���X(WEB3LogSysTimeInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 �����@@���F(SRA) �V�K�쐬
*/
package webbroker3.common;

import java.lang.reflect.Method;
import java.util.Date;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * �Ɩ����W�b�N�̊J�n���ԂƏI�����Ԃ����O�ɏo�͂���N���X�B<BR>
 *<BR>
 * @@author �����@@���F(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.kernel.interceptor.Interceptor
 */
public class WEB3LogSysTimeInterceptor implements Interceptor
{
    /**
     * ���O�o�͕�����i���\�b�h�� �F �j�B<BR>
     */
    private final static String METHOD_NAME = "���\�b�h�� �F ";
    /**
     * ���O�o�͕�����i.�j�B<BR>
     */
    private final static String DOT = ".";
    /**
     * ���O�o�͕�����i�J�n���� �F �j�B<BR>
     */
    private final static String START_DATE = "�Ɩ������J�n���� �F ";
    /**
     * ���O�o�͕�����i�I������ �F �j�B<BR>
     */
    private final static String END_DATE = "�Ɩ������I������ �F ";
    /**
     * �C���^�Z�v�^���ꂽ���\�b�h���B<BR>
     */
    private String methodName;
    /**
     * �C���^�Z�v�^���ꂽ���\�b�h����`����Ă���N���X�B<BR>
     */
    private Class clazz;
    /**
     * ���O���[�e�B���e�B�I�u�W�F�N�g�B<BR>
     */
    private WEB3LogUtility log;

    /**
     * ���\�b�h���Ăяo����A���\�b�h�̏��������s�����O�ɌĂяo����郁�\�b�h�B<BR>
     *<BR>
     * @@param l_method �Ăяo���ꂽ���\�b�h�I�u�W�F�N�g
     * @@param l_args ���\�b�h�̈����̔z��
     * @@return null���Ԃ����
     */
    public Object onCall(Method l_method, Object[] l_args)
    {
        methodName = l_method.getName();
        clazz = l_method.getDeclaringClass();

        log = WEB3LogUtility.getInstance(clazz);
        log.info(startDate());

        return null;
    }

    /**
     * ���\�b�h���畜�A������ɌĂяo����郁�\�b�h�B<BR>
     *<BR>
     * @@param l_context null
     * @@param l_returnValue ���\�b�h�̖߂�l
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        log.info(endDate());
    }

    /**
     * ���\�b�h����O���X���[�������ɌĂяo����郁�\�b�h�B<BR>
     *<BR>
     * @@param l_context null
     * @@param l_thrownObject �X���[���ꂽ�I�u�W�F�N�g
     */
    public void onThrowable(Object l_context, Throwable l_thrownObject)
    {
        log.info(endDate());
    }

    /**
     * �J�n�����̕������Ԃ��B<BR>
     *<BR>
     * @@return �J�n�����̕�����
     */
    private String startDate()
    {
        StringBuffer l_sbMessage = new StringBuffer();

        l_sbMessage.append(METHOD_NAME);
        l_sbMessage.append(clazz.getName() + DOT + methodName);
        l_sbMessage.append(START_DATE + new Date().toString());

        return l_sbMessage.toString();
    }

    /**
     * �I�������̕������Ԃ��B<BR>
     *<BR>
     * @@return �I�������̕�����
     */
    private String endDate()
    {
        StringBuffer l_sbMessage = new StringBuffer();

        l_sbMessage.append(METHOD_NAME);
        l_sbMessage.append(clazz.getName() + DOT + methodName);
        l_sbMessage.append(END_DATE + new Date().toString());

        return l_sbMessage.toString();
    }
}
@
