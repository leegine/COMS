head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3DefaultMQSendResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : MQMessageSendResult�C���^�[�t�F�[�X�̎����N���X(WEB3DefaultMQSendResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2003/02/13 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.mqgateway.stdimpls;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;

import webbroker3.mqgateway.WEB3MQSendResult;

/**
 * MQMessageSendResult�C���^�[�t�F�[�X�̎����N���X<br>
 *<br> 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3DefaultMQSendResult implements WEB3MQSendResult
{

    public static final WEB3MQSendResult SUCCESS_RESULT_INSTANCE =
        WEB3DefaultMQSendResult.newSuccessResultInstance();

    /**
     * �������ʂ�\���I�u�W�F�N�g
     */
    private ProcessingResult result;

    // �R���X�g���N�^���g�p���Ȃ��悤��private�Ő錾
    private WEB3DefaultMQSendResult()
    {

    }

    // �R���X�g���N�^���g�p���Ȃ��悤��private�Ő錾
    private WEB3DefaultMQSendResult(ProcessingResult result)
    {
        this.result = result;
    }

    /**
     * ������\���V�����C���X�^���X�𐶐�����<br>
     * <br>
     * @@return ������\���V�����C���X�^���X
     */
    static WEB3DefaultMQSendResult newSuccessResultInstance()
    {
        return new WEB3DefaultMQSendResult(
            ProcessingResult.newSuccessResultInstance());
    }

    /**
     * ���s��\���V�����C���X�^���X�𐶐�����<br>
     * <br>
     * @@return ���s��\���V�����C���X�^���X
     */
    static WEB3DefaultMQSendResult newFailedResultInstance(ErrorInfo errorInfo)
    {
        return new WEB3DefaultMQSendResult(
            ProcessingResult.newFailedResultInstance(errorInfo));
    }

    /**
     * @@see webbroker3.mqgateway.WEB3MQSendResult#getErrorInfo()
     */
    public ErrorInfo getErrorInfo()
    {
        return result.getErrorInfo();
    }

    /**
     * @@see webbroker3.mqgateway.WEB3MQSendResult#isFailedResult()
     */
    public boolean isFailedResult()
    {
        return result.isFailedResult();
    }

    /**
     * @@see webbroker3.mqgateway.WEB3MQSendResult#isSuccessResult()
     */
    public boolean isSuccessResult()
    {
        return result.isSuccessfulResult();
    }

}
@
