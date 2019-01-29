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
filename	WEB3BaseRuntimeException.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �V�X�e��RuntimeException��O���N���X(WEB3BaseRuntimeException.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 �����(���u) �V�K�쐬
*/
package webbroker3.common;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * �Ɩ��A�v���P�[�V�����Ŏg�p����RuntimeException��O�N���X�̊��N���X�B<BR>
 *<BR>
 * @@author �����(���u)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
 */
public class WEB3BaseRuntimeException extends RuntimeException implements WEB3Exception
{
    /**
     * �G���[���B<BR>
     */
    private ErrorInfo errorInfo;
    /**
     * �G���[�������������\�b�h���B<BR>
     */
    private String errorMethod;
    /**
     * �ǉ����b�Z�[�W�B<BR>
     */
    private String errorMessage;
    /**
     * ����������O�I�u�W�F�N�g�B<BR>
     */
    private Exception exception;

    /**
     * �R���X�g���N�^�B<BR>
     *<BR>
     * @@param l_errorInfo ���������G���[�ɑΉ������G���[���I�u�W�F�N�g
     * @@param l_strErrorMethod �G���[�������������\�b�h��
     */
    public WEB3BaseRuntimeException(ErrorInfo l_errorInfo, String l_strErrorMethod)
    {
        errorInfo = l_errorInfo;
        errorMethod = l_strErrorMethod;
    }

    /**
     * �R���X�g���N�^�B<BR>
     *<BR>
     * @@param l_errorInfo ���������G���[�ɑΉ������G���[���I�u�W�F�N�g
     * @@param l_strErrorMethod �G���[�������������\�b�h��
     * @@param l_exception ����������O�I�u�W�F�N�g
     */
    public WEB3BaseRuntimeException(ErrorInfo l_errorInfo, String l_strErrorMethod, Exception l_exception)
    {

        errorInfo = l_errorInfo;
        errorMethod = l_strErrorMethod;
        exception = l_exception;
    }

    /**
     * �R���X�g���N�^�B<BR>
     *<BR>
     * @@param l_errorInfo ���������G���[�ɑΉ������G���[���I�u�W�F�N�g
     * @@param l_strErrorMethod �G���[�������������\�b�h��
     * @@param l_strErrorMessage �G���[�̓��e
     */
    public WEB3BaseRuntimeException(ErrorInfo l_errorInfo, String l_strErrorMethod, String l_strErrorMessage)
    {

        errorInfo = l_errorInfo;
        errorMethod = l_strErrorMethod;
        errorMessage = l_strErrorMessage;
    }

    /**
     * �R���X�g���N�^�B<BR>
     *<BR>
     * @@param l_errorInfo ���������G���[�ɑΉ������G���[���I�u�W�F�N�g
     * @@param l_strErrorMethod �G���[�������������\�b�h��
     * @@param l_strErrorMessage �G���[�̓��e
     * @@param l_exception ����������O�I�u�W�F�N�g
     */
    public WEB3BaseRuntimeException(ErrorInfo l_errorInfo, String l_strErrorMethod, String l_strErrorMessage, Exception l_exception)
    {

        errorInfo = l_errorInfo;
        errorMethod = l_strErrorMethod;
        errorMessage = l_strErrorMessage;
        exception = l_exception;
    }

    /**
     * �G���[����Ԃ��B<BR>
     *<BR>
     * @@return �G���[���
     */
    public ErrorInfo getErrorInfo()
    {
        return errorInfo;
    }

    /**
     * �G���[�������������\�b�h����Ԃ��B<BR>
     *<BR>
     * @@return �G���[�������������\�b�h��
     */
    public String getErrorMethod()
    {
        return errorMethod;
    }

    /**
     * �G���[���e��Ԃ��B<BR>
     *<BR>
     * @@return �G���[���e
     */
    public String getErrorMessage()
    {
        return errorMessage;
    }

    /**
     * ��O�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ��O�I�u�W�F�N�g
     */
    public Exception getException()
    {
        return exception;
    }
}
@
