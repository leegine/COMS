head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3Exception.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3��O�C���^�t�F�[�X(WEB3Exception.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 �����(���u) �V�K�쐬
*/
package webbroker3.common;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * �Ɩ��A�v���P�[�V�����Ŏg�p�����O�N���X�̃C���^�t�F�[�X�B<BR>
 *<BR>
 * @@author �����(���u)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
 */
public interface WEB3Exception
{
    
    /**
     * �G���[����Ԃ��B<BR>
     *<BR>
     * @@return �G���[���
     */
    public ErrorInfo getErrorInfo();

    /**
     * �G���[�������������\�b�h����Ԃ��B<BR>
     *<BR>
     * @@return �G���[�������������\�b�h��
     */
    public String getErrorMethod();

    /**
     * �G���[���e��Ԃ��B<BR>
     *<BR>
     * @@return �G���[���e
     */
    public String getErrorMessage();

    /**
     * ��O�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ��O�I�u�W�F�N�g
     */
    public Exception getException();
}
@
