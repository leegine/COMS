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
filename	WEB3SystemLayerException.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ɩ��A�v���P�[�V�����V�X�e���G���[��O��N���X(WEB3SystemLayerException.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 �����@@���F(SRA) �V�K�쐬
*/
package webbroker3.common;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * �Ɩ��A�v���P�[�V�����Ŏg�p����V�X�e���G���[�̗�O�N���X�B<BR>
 *<BR>
 * @@author �����@@���F(SRA)
 * @@version 1.0
 * @@see webbroker3.common.WEB3BaseException
 */
public class WEB3SystemLayerException extends WEB3BaseException
{

    /**
     * �R���X�g���N�^�B<BR>
     *<BR>
     * @@param l_errorInfo ���������G���[�ɑΉ������G���[���I�u�W�F�N�g
     * @@param l_strErrorMethod �G���[�������������\�b�h��
     */
    public WEB3SystemLayerException(
        ErrorInfo l_errorInfo,
        String l_strErrorMethod)
    {
        super(l_errorInfo, l_strErrorMethod, "");
    }

    /**
     * �R���X�g���N�^�B<BR>
     *<BR>
     * @@param l_errorInfo ���������G���[�ɑΉ������G���[���I�u�W�F�N�g
     * @@param l_strErrorMethod �G���[�������������\�b�h��
     * @@param l_errorMessage �G���[�̓��e
     */
    public WEB3SystemLayerException(
        ErrorInfo l_errorInfo,
        String l_strErrorMethod,
        String l_strErrorMessage)
    {
        super(l_errorInfo, l_strErrorMethod, l_strErrorMessage);
    }

    /**
     * �R���X�g���N�^�B<BR>
     *<BR>
     * @@param l_errorInfo ���������G���[�ɑΉ������G���[���I�u�W�F�N�g
     * @@param l_strErrorMethod �G���[�������������\�b�h��
     * @@param l_errorMessage �G���[�̓��e
     * @@param l_exception ����������O�I�u�W�F�N�g
     */
    public WEB3SystemLayerException(
        ErrorInfo l_errorInfo,
        String l_strErrorMethod,
        String l_strErrorMessage,
        Exception l_exception)
    {
        super(l_errorInfo, l_strErrorMethod, l_strErrorMessage, l_exception);
    }

}
@
