head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderQueueTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������L���[TransactionCallback(WEB3FeqOrderQueueTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/07/15 ���(SRA) �V�K�쐬
Revesion History : 2008/07/25 ����(SRA) ����:No.019
*/
package webbroker3.feq;

import java.util.List;

import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;

/**
 * �i�O�����������L���[TransactionCallback�j�B<BR>
 * <BR>
 * �O�����������L���[�g�����U�N�V�������������{����N���X�B<BR>
 * @@version 1.0
 */
public class WEB3FeqOrderQueueTransactionCallback implements TransactionCallback
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderQueueTransactionCallback.class);

    /**
     * (�����P��)<BR>
     * �����P��<BR>
     */
    private WEB3FeqOrderUnit orderUnit;

    /**
     * �R���X�g���N�^<BR>
     * @@param l_orderUnit �O�����������P��
     */
    public WEB3FeqOrderQueueTransactionCallback(FeqOrderUnit l_orderUnit)
    {
        this.orderUnit = (WEB3FeqOrderUnit)l_orderUnit;
    }

    /**
     * �g�����U�N�V�������������{����B<BR>
     * <BR>
     * �����L���[�e�[�u���Ƀ��b�N��������B<BR>
     * this.lockSLE���M�L���[���R�[������B<BR>
     * <BR>
     * @@return Object
     * @@throws DataNetworkException
     * @@throws DataQueryException
     */
    public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = " process() ";
        log.entering(STR_METHOD_NAME);
        
        // �����L���[�e�[�u���Ƀ��b�N��������B                
        this.lockSleSendQueue();

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (lockSLE���M�L���[)<BR>
     * SLE_SEND_Q�e�[�u���ɑ΂��A�ȉ��̏�����<BR>
     * �hselect for update nowait �h���g�p���ă��R�[�h�����b�N����B<BR>
     * <BR>
�@@     * [ �������� ]<BR>
�@@     * ����ID�@@�@@�@@�@@   =�@@�v���p�e�B�̒����P��.����ID<BR>
�@@     * �I�y���[�^�^�C�v = "�V�K" <BR>
�@@     * �����敪�@@�@@�@@�@@=�@@"�����M"<BR>
     *
     */
    private void lockSleSendQueue() throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = " lockSleSendQueue() ";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

        String l_strWhere = " order_id = ? and op_type = ? and status = ? ";
        Object[] l_objWhere = new Object[3];
        l_objWhere[0] = orderUnit.getOrderId() + "";
        l_objWhere[1] = SleSendqOpTypeEnum.NEW_ORDER;
        l_objWhere[2] = SleSendqProcStatusEnum.NOT_PROCESSED;

        List l_lisSleSendQRow =  
            l_queryProcessor.doFindAllQuery(
                SleSendQRow.TYPE,
                l_strWhere,
                "for update nowait",
                l_objWhere);

        if (l_lisSleSendQRow == null || l_lisSleSendQRow.size() == 0)
        {
            log.error("lock�ΏۂɊY������SLE_SEND_Q���R�[�h�����݂��܂���B");
            throw new DataCallbackException("Error while locking SLE_SEND_Q :"
                + " order_id = " + orderUnit.getOrderId()
                + " op_type = " + SleSendqOpTypeEnum.NEW_ORDER
                + " status = " + SleSendqProcStatusEnum.NOT_PROCESSED);
        }

        log.exiting(STR_METHOD_NAME);

    }
}@
