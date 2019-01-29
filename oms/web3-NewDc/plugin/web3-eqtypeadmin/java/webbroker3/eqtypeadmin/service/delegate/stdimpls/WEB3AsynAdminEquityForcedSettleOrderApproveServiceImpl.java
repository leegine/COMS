head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�񓯊��j�Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�XImpl(WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 �����F (���u) �V�K�쐬 �d�l�ύX���f��No.129
Revision History : 2007/05/16 �����F (���u) ���f��152
Revision History : 2008/01/17 �����F (���u) �d�l�ύX���f��170 180
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityApproveTypeDef;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveUnitService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�i�񓯊��j�Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�XImpl�j<BR>
 * �i�񓯊��j�Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X�����N���X<BR>
 * <BR>
 * ���F�^�񏳔F������񓯊��ōs���B<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl implements Runnable
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl.class);

    /**
     * (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F���C�����N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3AdminEquityForcedSettleOrderApproveMainRequest request;

    /**
     * @@roseuid 462CAC42039E
     */
    public WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl()
    {

    }

    /**
     * (�i�񓯊��j�Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�XImpl)<BR>
     * �f�t�H���g�R���X�g���N�^�B<BR>
     * <BR>
     * ������this.���N�G�X�g�f�[�^�ɃZ�b�g����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F���C�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl
     * @@roseuid 460329EA02BF
     */
    public WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl(
        WEB3AdminEquityForcedSettleOrderApproveMainRequest l_request)
    {
        this.request = l_request;
    }

    /**
     * �i�񓯊��j�������ω��������F�^�񏳔F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�񓯊��j�Ǘ��ҋ������ω��������F�^�񏳔F�jrun�v�Q�ƁB<BR>
     * @@roseuid 460329EA029F
     */
    public void run()
    {
        final String STR_METHOD_NAME = " run()";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;    
        List l_lisDaemonTrigger = null;
        boolean l_blnIsExeError = false;

        try
        { 
            //�Ǘ��ҁE�������ω��������F�^�񏳔F�f�[�����g���K�[TransactionCallback(long)
            WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback l_daemonTriggerCallBack = 
                new WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback(
                    this.request.threadNo.longValue());

            //getDefaultProcessor()
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //doTransaction(arg0 : int, arg1 : TransactionCallback)
            l_lisDaemonTrigger = (List)l_queryProcessor.doTransaction(
                QueryProcessor.TX_JOIN_EXISTING,
                l_daemonTriggerCallBack);

            if (l_lisDaemonTrigger == null)
            {
                log.entering(STR_METHOD_NAME);
                return;
            }

            //�Ǘ��ҁE�������ω��������F�^�񏳔F�I�����C�����s����TransactionCallback�𐶐�����B 
            WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback l_onlineRunStatusCallBack = 
                new WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback(
                    this.request.institutionCode,
                    this.request.rangeFrom.longValue(),
                    this.request.rangeTo.longValue(),
                    this.request.approveType);

            //doTransaction(arg0 : int, arg1 : TransactionCallback)
            l_onlineRunStatus = (WEB3GentradeOnlineRunStatus)l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_onlineRunStatusCallBack);
        }
        catch (DataNetworkException l_dataNetworkException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataNetworkException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataNetworkException.getMessage(),
                l_dataNetworkException);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataQueryException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataQueryException.getMessage(),
                l_dataQueryException);
        }

        AdminEqForcedSettleOrderRow[] l_orderRows = null;
        try
        {
            l_orderRows = WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                this.request.orderIdList, 
                null, 
                this.request.rangeFrom, 
                this.request.rangeTo);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Ǘ��҃I�u�W�F�N�g
        WEB3Administrator l_admin = null;
        try
        {
            l_admin = new WEB3Administrator(this.request.administratorId.longValue());
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get�������ϒ����ꗗ()�̖߂�l�̗v�f�i=�������ϒ���Row�j����Loop����
        if (l_orderRows != null)
        {
            boolean l_blnIsError = false;
            int l_intLength = l_orderRows.length;
            for (int i = 0; i < l_intLength; i++)
            {
                AdminEqForcedSettleOrderRow l_row = l_orderRows[i];

                String l_strApproveType = this.request.approveType;
                WEB3AdminEquityForcedSettleOrderApproveUnitService l_service = 
                    (WEB3AdminEquityForcedSettleOrderApproveUnitService)Services.getService(
                        WEB3AdminEquityForcedSettleOrderApproveUnitService.class);

                //���F�����ithis.���N�G�X�g�f�[�^.���F�敪 == "���F"�j�̏ꍇ
                if (WEB3AdminEquityApproveTypeDef.APPROVE.equals(l_strApproveType))
                {
                    //exec���F(�������ϒ���Row)
                    try
                    {
                        l_blnIsError = l_service.execApprove(l_row, l_admin);
                    }
                    catch (Exception l_ex)
                    {
                        log.error("exec���F(�������ϒ���Row)�ďo���ɗ�O����", l_ex);
                        l_blnIsError = true;
                    }

                }

                //�񏳔F�����i��L�ȊO�j�̏ꍇ 
                else
                {
                    //exec�񏳔F(�������ϒ���Row)
                    try
                    {
                        l_blnIsError = l_service.execDisapprove(l_row, l_admin);
                    }
                    catch (Exception l_ex)
                    {
                        log.error("exec�񏳔F(�������ϒ���Row)�ďo���ɗ�O����", l_ex);
                        l_blnIsError = true;
                    }
                }

                if (l_blnIsError)
                {
                    l_blnIsExeError = true;
                }
            }
        }

        //update���s�X�e�[�^�X�敪(���s�X�e�[�^�X�敪 : String)  
        String l_strRunStatusDiv = null;
        if(l_blnIsExeError)
        {
            l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
        }
        else
        {
            l_strRunStatusDiv = WEB3RunStatusDivDef.DEALED;
        }

        try
        {
            l_onlineRunStatus.updateRunStatusDiv(l_strRunStatusDiv);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //(*)�r�����b�N�����f�[�����g���K�[�e�[�u���̃��R�[�h��"���ғ�"��update����B
        DaemonTriggerParams l_daemonTriggerParams =
            new DaemonTriggerParams((DaemonTriggerRow) l_lisDaemonTrigger.get(0));

        l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
        //���ݎ�����set����B
        l_daemonTriggerParams.setTriggerDate(GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_daemonTriggerParams);
        }
        catch (DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                new ErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�Ǘ��ҁE�������ω��������F�^�񏳔F�I�����C�����s����TransactionCallback)<BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F�I�����C�����s����TransactionCallback<BR>
     * <BR>
     * ���������F�^�񏳔F�̏����J�n�^�I����<BR>
     * �I�����C�����s���ʃe�[�u���ɋL�^����B<BR>
     */
    public class WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback
        implements TransactionCallback
    {

        /**
         * (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h<BR>
         */
        public String institutionCode;

        /**
         * (From����ID)<BR>
         * From����ID<BR>
         */
        public long accountIdFrom;

        /**
         * (To����ID)<BR>
         * To����ID<BR>
         */
        public long accountIdTo;

        /**
         * (���F�敪)<BR>
         * ���F�敪<BR>
         */
        public String approveType;

        /**
         * @@roseuid 462CAC9D018B
         */
        public WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback()
        {

        }

        /**
         * (�Ǘ��ҁE�������ω��������F�^�񏳔F�I�����C�����s����TransactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * ���������g�̓������ڂɃZ�b�g����B<BR>
         * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h<BR>
         * @@param l_lngAccountIdFrom - (From����ID)<BR>
         * From����ID<BR>
         * @@param l_lngAccountIdTo - (To����ID)<BR>
         * To����ID<BR>
         * @@param l_strApproveType - (���F�敪)<BR>
         * ���F�敪<BR>
         * @@roseuid 46032C62002E
         */
        public WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback(
            String l_strInstitutionCode,
            long l_lngAccountIdFrom,
            long l_lngAccountIdTo,
            String l_strApproveType)
        {
            final String STR_METHOD_NAME =
                " WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback(" +
                "String, long, long, String)";
            log.entering(STR_METHOD_NAME);

            this.institutionCode = l_strInstitutionCode;
            this.accountIdFrom = l_lngAccountIdFrom;
            this.accountIdTo = l_lngAccountIdTo;
            this.approveType = l_strApproveType;

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * �I�����C�����s���ʃe�[�u���̊Y�����R�[�h��<BR>
         * "������"��update����B<BR>
         * <BR>
         * �P�j�@@�I�����C�����s����.set������()���\�b�h���R�[������B<BR>
         * �@@[set������()�ɃZ�b�g����p�����[�^]<BR>
         * �@@�@@�،���ЃR�[�h�F�@@this.�،���ЃR�[�h<BR>
         * �@@�@@�����^�C�v�F�@@"����"<BR>
         * �@@�@@�敨�^�I�v�V�����敪�F�@@"DEFAULT"<BR>
         * �@@�@@�I�����C���T�[�r�X�敪�F<BR>
         * �@@�@@�@@[this.���F�敪 == "���F"�̏ꍇ]<BR>
         * �@@�@@�@@�@@"�������ρi���F�j"<BR>
         * �@@�@@�@@[this.���F�敪 == "�񏳔F"�̏ꍇ]<BR>
         * �@@�@@�@@�@@"�������ρi�񏳔F�j"<BR>
         * �@@�@@From����ID�F�@@this.From����ID<BR>
         * �@@�@@To����ID�F�@@this.To����ID<BR>
         * <BR>
         * �Q�j�@@set������()�̖߂�l��ԋp����B<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 46032C62004E
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                //�I�����C�����s���ʃe�[�u���ɏ����J�n���R�[�h�𔽉f����B  
                // �����͈ȉ��̒ʂ�ɃZ�b�g����B  
                // [����]
                //�@@ �،���ЃR�[�h�F�@@�v���p�e�B.�،���ЃR�[�h  
                //�@@ �����^�C�v�F�@@"����"  
                //�@@ �敨�^�I�v�V�����敪�F�@@"DEFAULT" 
                //   �I�����C���T�[�r�X�敪�F
                //�@@�@@�@@[this.���F�敪 == "���F"�̏ꍇ] 
                //�@@�@@�@@�@@"�������ρi���F�j" 
                //�@@�@@�@@[this.���F�敪 == "�񏳔F"�̏ꍇ] 
                //�@@�@@�@@�@@"�������ρi�񏳔F�j" 
                //�@@�@@From����ID�F�@@this.From����ID 
                //�@@�@@To����ID�F�@@this.To����ID 
                String l_strApproveType = null;
                if (WEB3AdminEquityApproveTypeDef.APPROVE.equals(this.approveType))
                {
                    l_strApproveType = WEB3OnlineServiceDiv.FORCED_SETTLE_APPROVAL;
                }
                else if (WEB3AdminEquityApproveTypeDef.DISAPPROVE.equals(this.approveType))
                {
                    l_strApproveType = WEB3OnlineServiceDiv.FORCED_SETTLE_UNAPPROVAL;
                }

                l_onlineRunStatus =
                    WEB3GentradeOnlineRunStatus.setDealing(
                        this.institutionCode,
                        ProductTypeEnum.EQUITY,
                        WEB3FuturesOptionDivDef.DEFAULT,
                        l_strApproveType,
                        this.accountIdFrom,
                        this.accountIdTo);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                        new ErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
            }

            //�Q�j�P�j�̖߂�l��Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }
    }

    /**
     * (�Ǘ��ҁE�������ω��������F�^�񏳔F�f�[�����g���K�[TransactionCallback)<BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F�f�[�����g���K�[TransactionCallback<BR>
     * �i�g�����U�N�V���������FTX_JOIN_EXISTING�j<BR>
     */
    public class WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback
        implements TransactionCallback
    {

        /**
         * (�X���b�hNo)<BR>
         * �X���b�hNo<BR>
         */
        public long threadNo;

        /**
         * @@roseuid 462CAC9D01AA
         */
        public WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback()
        {

        }

        /**
         * (�Ǘ��ҁE�������ω��������F�^�񏳔F�f�[�����g���K�[TransactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * ���������g�̓������ڂɃZ�b�g����B<BR>
         * @@param l_lngThreadNo - (�X���b�hNo)<BR>
         * �X���b�hNo
         * @@roseuid 46032B790177
         */
        public WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback(long l_lngThreadNo)
        {
            final String STR_METHOD_NAME =
                " WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback(long)";
            log.entering(STR_METHOD_NAME);

            this.threadNo = l_lngThreadNo;

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * this.�X���b�hNo�ɊY������f�[�����g���K�[�e�[�u����<BR>
         * ���R�[�h�����b�N����B<BR>
         * <BR>
         * �P�j�@@DB����<BR>
         * �@@�ȉ��̏����ɊY������f�[�����g���K�[�e�[�u����<BR>
         * �@@���R�[�h���A"for update nowait"�I�v�V�����œǂݍ��ށB<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�@@�X���b�h�ԍ� = this.�X���b�hNo<BR>
         * <BR>
         * �Q�j�@@�������ʂ�ԋp����B<BR>
         * �@@���������ʂ��擾�ł��Ȃ������ꍇ�A�����X���b�h��<BR>
         * �@@��L���b�N�Ɏ��s�����|��ERROR�Ń��O�ɏo�͂��Anull��ԋp����B<BR>
         * @@return Object
         * @@roseuid 46032B790196
         */
        public Object process()
        {
            //�P�j�@@DB����
            //�ȉ��̏����ɊY������f�[�����g���K�[�e�[�u����
            //���R�[�h���A"for update nowait"�I�v�V�����œǂݍ��ށB
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            try
            {
                String l_strWhere = " thread_no = ? ";
                String l_strCondition = " for update nowait ";
                Object l_bindVars[] = { new Long(threadNo)};
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                List l_lisRows = l_queryProcesser.doFindAllQuery(
                    DaemonTriggerRow.TYPE,
                    l_strWhere,
                    l_strCondition,
                    l_bindVars);

                //�Q�j�@@�������ʂ�ԋp����B
                //�������ʂ��擾�ł��Ȃ������ꍇ�A�����X���b�h��
                // ��L���b�N�Ɏ��s�����|��ERROR�Ń��O�ɏo�͂��Anull��ԋp����B
                if (l_lisRows.isEmpty())
                {
                    log.error("�����X���b�h�̐�L���b�N�Ɏ��s����");
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return l_lisRows;
                }
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.error("�����X���b�h�̐�L���b�N�Ɏ��s����");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }
    }
}
@
