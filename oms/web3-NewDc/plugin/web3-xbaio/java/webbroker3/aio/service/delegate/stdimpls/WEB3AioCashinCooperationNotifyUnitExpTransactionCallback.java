head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationNotifyUnitExpTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�g�ʒm1���ُ펞�g�����U�N�V�����R�[���o�b�N (WEB3AioCashinCooperationNotifyUnitExpTransactionCallback)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/16 ���� (���u) �V�K�쐬
                 : 2006/11/14 ���G�� (���u)  �c�a�X�V�d�l�@@No.131
                 : 2006/11/14 ���G�� (���u)  �c�a�X�V�d�l�@@No.132
*/

package webbroker3.aio.service.delegate.stdimpls;

import webbroker3.aio.data.BankDepositErrorHistoryDao;
import webbroker3.aio.data.BankDepositErrorHistoryParams;
import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.common.WEB3Exception;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3UpdatePersonDef;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

/**
 * �����A�g�ʒm1���ُ펞�g�����U�N�V�����R�[���o�b�N <BR>
 * 
 * @@author ����
 * @@version 1.0
 */

public class WEB3AioCashinCooperationNotifyUnitExpTransactionCallback 
	implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashinCooperationNotifyUnitExpTransactionCallback.class);
    
    /**
     * �����ʒmParams<BR>
     */
    private BankDepositNotifyParams bankDepositNotifyParams;
    
    /**
     * ��O<BR>
     */
    private Exception exp;
    
    /**
     * �R���X�g���N�^�B  <BR>
     * ����.�����ʒmParams��this.�����ʒmParams�ɃZ�b�g�B  <BR>
     * ����.��O��this.��O�ɃZ�b�g�B <BR>
     *  <BR>
     * @@param l_bankDepositNotifyParams - (�����ʒmParams)
     * @@param l_exp - (��O)
     */
    public WEB3AioCashinCooperationNotifyUnitExpTransactionCallback(
        BankDepositNotifyParams l_bankDepositNotifyParams, 
        Exception l_exp)
    {
        //����.�����ʒmParams��this.�����ʒmParams�ɃZ�b�g�B
        this.bankDepositNotifyParams = l_bankDepositNotifyParams;
        //����.��O��this.��O�ɃZ�b�g�B
        this.exp = l_exp;
    }

   /**
     * �����ʒmParams���X�V�A<BR>
     * �����ʒm�����G���[�����e�[�u����1���ǉ�����B<BR> 
     * <BR>
     * [�X�V���e] <BR>
     * <BR>
     * �����敪 = "�G���[" <BR>
     * �G���[�R�����g = ��O.��O���b�Z�[�W <BR>
     * <BR>
     * <DB�X�V�d�l�Q��> 
     * ��s�����ʒm_�����ʒm�e�[�u��.xls <BR>
     * ��s�����ʒm_�����ʒm�����G���[�����e�[�u��.xls <BR>
     * <BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);
        
        //�����ʒmParams���X�V�A
        int l_intLastErrorHistorySerialNo = 
            this.bankDepositNotifyParams.getLastErrorHistorySerialNo() + 1;

        //1) �����敪 = "�G���[" 
        this.bankDepositNotifyParams.setStatus(WEB3StatusDef.DATA_ERROR);
        //2) �G���[�R�����g = ��O.��O���b�Z�[�W 
        if (this.exp instanceof WEB3Exception)
        {
            WEB3Exception l_web3Exception = (WEB3Exception)this.exp;
            this.bankDepositNotifyParams.setDepositErrorComment(l_web3Exception.getErrorMessage());
        }
        else
        {
            this.bankDepositNotifyParams.setDepositErrorComment(this.exp.getMessage()); 
        }

        //3) �G���[�����ŏI�ʔ�
        this.bankDepositNotifyParams.setLastErrorHistorySerialNo(l_intLastErrorHistorySerialNo);
        //4) �X�V�S����
        this.bankDepositNotifyParams.setUpdatePerson(WEB3UpdatePersonDef.SYSTEM);
        //5) �X�V���t
        this.bankDepositNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        WEB3DataAccessUtility.updateRow(this.bankDepositNotifyParams);
        
        //�����ʒm�����G���[�����e�[�u����1���ǉ�����B
        //��s�����ʒm�����G���[����Params
        BankDepositErrorHistoryParams l_bankDepositErrorHistoryParams = 
            new BankDepositErrorHistoryParams();
        //1)��s�����ʒm�����G���[�����e�[�u��ID
        l_bankDepositErrorHistoryParams.setBankDepositErrorHistoryId(
            BankDepositErrorHistoryDao.newPkValue());
        //2)��s�����ʒm�e�[�u��ID
        l_bankDepositErrorHistoryParams.setBankDepositNotifyId(
            this.bankDepositNotifyParams.getBankDepositNotifyId());
        //3)����ԍ�
        l_bankDepositErrorHistoryParams.setSerialNo(
            this.bankDepositNotifyParams.getLastErrorHistorySerialNo());
        //4)�����G���[�R�����g
        l_bankDepositErrorHistoryParams.setDepositErrorComment(
            this.bankDepositNotifyParams.getDepositErrorComment());
        //5)���l
        l_bankDepositErrorHistoryParams.setRemark(
            this.bankDepositNotifyParams.getRemark());
        //6)�X�V�S����
        l_bankDepositErrorHistoryParams.setUpdatePerson(WEB3UpdatePersonDef.SYSTEM);
        //7)�쐬���t
        l_bankDepositErrorHistoryParams.setCreatedTimestamp(
            GtlUtils.getSystemTimestamp());
        //8)�X�V���t
        l_bankDepositErrorHistoryParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());
        //9)�،���ЃR�[�h = ��s�����ʒm�e�[�u��.�،���ЃR�[�h
        l_bankDepositErrorHistoryParams.setInstitutionCode(
            this.bankDepositNotifyParams.getInstitutionCode());
        //10)�f�[�^�捞�敪 = ��s�����ʒm�e�[�u��.�f�[�^�捞�敪
        l_bankDepositErrorHistoryParams.setDataLoadDiv(
            this.bankDepositNotifyParams.getDataLoadDiv());
        QueryProcessor l_processor = Processors.getDefaultProcessor();
        l_processor.doInsertQuery(l_bankDepositErrorHistoryParams);

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}




@
