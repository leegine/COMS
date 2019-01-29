head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSChangeOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (PTS)�������������������N�G�X�g�A�_�v�^(WEB3EquityPTSChangeOrderRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 �����F (���u) �V�K�쐬 ���f��1241 1250
Revision History : 2008/02/13 �g�E�N�| (���u) �d�l�ύX���f��No.1299
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * ((PTS)�������������������N�G�X�g�A�_�v�^)<BR>
 * ��ʂɈˑ��������������b�v����A�_�v�^�N���X�B <BR>
 * <BR>
 * ���Y�N���X�́A�e�،���Ђ̉�ʗp���ɂ���āA�����쐬����邱�Ƃ�O��Ƃ���B<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3EquityPTSChangeOrderRequestAdapter extends WEB3EquityChangeOrderRequestAdapter
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSChangeOrderRequestAdapter.class);

    /**
     * @@roseuid 4766071F0092
     */
    protected WEB3EquityPTSChangeOrderRequestAdapter()
    {

    }

    /**
     * (PTS)�������������������N�G�X�g�A�_�v�^�C���X�^���X�𐶐�����B <BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�����B <BR>
     * �Q�j�@@���������C���X�^���X�Ɉ����̃��N�G�X�g�f�[�^���Z�b�g����B <BR>
     * �R�j�@@�C���X�^���X��ԋp����B <BR>
     * <BR>
     * �i�f�t�H���g�R���X�g���N�^��protected�Ƃ��A<BR>
     * �@@�{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3EquityChangeOrderRequestAdapter
     * @@roseuid 474B76140205
     */
    public static WEB3EquityChangeOrderRequestAdapter create(WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�{�C���X�^���X�𐶐�����B
        WEB3EquityPTSChangeOrderRequestAdapter l_adapter =
            new WEB3EquityPTSChangeOrderRequestAdapter();

        //�Q�j�@@���������C���X�^���X�Ɉ����̃��N�G�X�g�f�[�^���Z�b�g����B
        if (l_request instanceof WEB3EquityChangeConfirmRequest)
        {
            l_adapter.requestData = (WEB3EquityChangeConfirmRequest)l_request;
        }
        else if (l_request instanceof WEB3EquityChangeCompleteRequest)
        {
            l_adapter.requestData = (WEB3EquityChangeCompleteRequest)l_request;
        }

        //�R�j�@@�C���X�^���X��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }

    /**
     * (get�����㒍��������)<BR>
     * ���N�G�X�g�f�[�^.�����L���������A <BR>
     * AP�w�Ŏg�p��������㒍����������ԋp����B <BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * PTS������ԊǗ�.get������( )�̖߂�l��ԋp����B<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 474D0D8800F3
     */
    public Date getExpirationDate() throws WEB3BaseException
    {
        return WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
    }
}
@
