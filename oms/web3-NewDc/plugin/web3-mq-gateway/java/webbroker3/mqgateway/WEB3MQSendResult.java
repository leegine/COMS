head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQSendResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : MQ�ւ̃��b�Z�[�W�̑��M���ʂ�\���N���X(WEB3MQSendResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2003/02/13 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.mqgateway;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * MQ�ւ̃��b�Z�[�W�̑��M���ʂ�\���N���X�B<BR>
 * <BR>
 * @@author Takuji Yamada
 * @@version 1.0
 * @@see webbroker3.mqgateway.WEB3MQGatewayService#send(webbroker3.mqgateway.WEB3MQMessageSpec)
 */
public interface WEB3MQSendResult
{
    /**
     * �G���[�����擾����B<BR>
     * <BR>
     * @@return �G���[���
     */
    public ErrorInfo getErrorInfo();
    
    /**
     * ���ʂ����s�̏ꍇ��<code>true</code>��Ԃ��B����ȊO��<code>false</code>��Ԃ��B
     */
    public boolean isFailedResult();
    
    /**
     * ���ʂ������̏ꍇ��<code>true</code>��Ԃ��B����ȊO��<code>false</code>��Ԃ��B
     */
    public boolean isSuccessResult();
}@
