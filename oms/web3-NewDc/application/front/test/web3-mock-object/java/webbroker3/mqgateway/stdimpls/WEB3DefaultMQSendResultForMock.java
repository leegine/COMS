head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.37.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3DefaultMQSendResultForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3DefaultMQSendResultForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/05 ���G�� (���u) �V�K�쐬
*/
package webbroker3.mqgateway.stdimpls;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;

import webbroker3.mqgateway.WEB3MQSendResult;

/**
 * XXXXXX�N���X//TODO
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3DefaultMQSendResultForMock implements WEB3MQSendResult
{

    public static final WEB3MQSendResult SUCCESS_RESULT_INSTANCE =
        WEB3DefaultMQSendResult.newSuccessResultInstance();

    /**
     * �������ʂ�\���I�u�W�F�N�g
     */
    private ProcessingResult result;

    // �R���X�g���N�^���g�p���Ȃ��悤��private�Ő錾
    private WEB3DefaultMQSendResultForMock()
    {

    }

    // �R���X�g���N�^���g�p���Ȃ��悤��private�Ő錾
    private WEB3DefaultMQSendResultForMock(ProcessingResult result)
    {
        this.result = result;
    }

    /**
     * ������\���V�����C���X�^���X�𐶐�����<br>
     * <br>
     * @@return ������\���V�����C���X�^���X
     */
    public static WEB3DefaultMQSendResultForMock newSuccessResultInstance()
    {
        return new WEB3DefaultMQSendResultForMock(
            ProcessingResult.newSuccessResultInstance());
    }

    /**
     * ���s��\���V�����C���X�^���X�𐶐�����<br>
     * <br>
     * @@return ���s��\���V�����C���X�^���X
     */
    public static WEB3DefaultMQSendResultForMock newFailedResultInstance(ErrorInfo errorInfo)
    {
        return new WEB3DefaultMQSendResultForMock(
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
