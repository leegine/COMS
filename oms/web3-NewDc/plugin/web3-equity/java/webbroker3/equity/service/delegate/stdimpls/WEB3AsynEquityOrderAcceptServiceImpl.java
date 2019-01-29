head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AsynEquityOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ����������t�T�[�r�X�N���X(WEB3AsynEquityOrderAcceptServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History :�����ŗ����F
                   2004/02/24 �����@@���F(SRA) �V�K�쐬
                   2004/09/20 羐� (���u) �C��
                   2004/11/05 �@@�� �C��
                   2004/12/15 �X��  (SRA) �c�Č��Ή�
                   2005/01/06 �����a��(SRA) JavaDoc�C��
 �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�񓯊��Ή��ŗ����F
                   2005/03/09 ���iFLJ�j�񓯊����s�Ή��i�V�K�N���X�j
 */
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.equity.data.HostEqtypeOrderAcceptRow;
import webbroker3.equity.message.WEB3EquityOrderAcceptRequest;
import webbroker3.equity.service.delegate.WEB3EquityOrderAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.gentrade.WEB3GentradeRequestCodesForRead;

import java.util.List;

/**
 * �i�񓯊��Ή�����������t�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �񓯊��Ή�����������t�T�[�r�X�N���X�B
 * @@author  : ���iFLJ�j
 * @@version 1.0
 */
public class WEB3AsynEquityOrderAcceptServiceImpl
    implements Runnable
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynEquityOrderAcceptServiceImpl.class);

    /**
     * (����������t���N�G�X�g)�B<BR>
     */
    private WEB3EquityOrderAcceptRequest request;

    /**
     * (�R���X�g���N�^)�B<BR>
     * @@roseuid 4042ED5D016D
     */
    public WEB3AsynEquityOrderAcceptServiceImpl(WEB3EquityOrderAcceptRequest l_request)
    {
        this.request = l_request;
    }

    /**
     * (������t���������{����)�B<BR>
     * <BR>
     * �V�[�P���X�}�u�i����������t�T�[�r�X�j������t�v�Q�ƁB<BR>
     * <BR>
     */
    public void run()
    {
        final String STR_METHOD_NAME = "run()";
        log.entering(STR_METHOD_NAME);
        WEB3EquityOrderAcceptRequest l_eqOrderAcceptRequest =
            (WEB3EquityOrderAcceptRequest) request;

        try
        {
            //getDefaultProcessor
            QueryProcessor l_qp = Processors.getDefaultProcessor();

            //����������tTransactionCallback
            WEB3EquityOrderAcceptServiceTransactionCallback l_eqOdrAptSrvTranCallback;
            l_eqOdrAptSrvTranCallback = new
                WEB3EquityOrderAcceptServiceTransactionCallback();

            //set���ʃR�[�h�v���t�B�N�X�ꗗ
            l_eqOdrAptSrvTranCallback.setOrderRequestNumberPrefixGroup(
                l_eqOrderAcceptRequest.orderRequestNumberPrefixGroup);

            //doTransaction
            l_qp.doTransaction(
                QueryProcessor.TX_JOIN_EXISTING,
                l_eqOdrAptSrvTranCallback);
        }
        catch (DataCallbackException l_dce)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", (WEB3BaseException)l_dce.getDetails());
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de));
        }
        catch (Exception l_e)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e));
        }

        //�X���b�h�J��
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(request.threadNo.
                longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * �i����������tTransactionCallback�j�B<BR>
     * <BR>
     * ����������t�������s���g�����U�N�V�����E�R�[���o�b�N�N���X�B
     */
    private class WEB3EquityOrderAcceptServiceTransactionCallback
        implements TransactionCallback
    {
        /**
         * �i���ʃR�[�h�v���t�B�N�X�ꗗ�j�B
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * �iget���ʃR�[�h�v���t�B�N�X�ꗗ�j�B<BR>
         * <BR>
         * ���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B
         * @@return ���ʃR�[�h�v���t�B�N�X�ꗗ
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return this.orderRequestNumberPrefixGroup;
        }

        /**
         * �iset���ʃR�[�h�v���t�B�N�X�ꗗ�j�B<BR>
         * <BR>
         * �����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B
         * @@param l_prefixGroup - (���ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * ���ʃR�[�h�v���t�B�N�X�ꗗ�B
         */
        public void setOrderRequestNumberPrefixGroup(String[] l_prefixGroup)
        {
            this.orderRequestNumberPrefixGroup = l_prefixGroup;
        }

        /**
         * �i�����敪��update����j�B
         * @@param l_hostEqtypeOrderAcceptParams - (����������t�L���[Params)
         * @@param l_strStatus - (�����敪)
         * @@roseuid 4042ED5D016E
         */
        public void updateStatus(
            HostEqtypeOrderAcceptParams l_params,
            String l_strStatus)
            throws WEB3SystemLayerException
        {
            final String STR_METHOD_NAME =
                "updateStatus(HostEqtypeOrderAcceptParams, String)";
            log.entering(STR_METHOD_NAME);
            
            l_params.setStatus(l_strStatus);
            l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_params);
            }
            catch (DataException l_de)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_de.getMessage(),
                    l_de);
            }
            log.exiting(STR_METHOD_NAME);
        }

        /**
         * �iprocess�j�B<BR>
         * <BR>
         * �g�����U�N�V�������������{����B <BR>
         * <BR>
         * �V�[�P���X�} <BR>
         * �u�i����������t�T�[�r�X�jprocess�v�Q�ƁB <BR>
         * <BR>
         * @@return WEB3BaseException�i���������ɂ�WEB3BaseException��O�����������ꍇ�j<BR>
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 4050215102CD
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME =
                "WEB3EquityOrderAcceptServiceTransactionCallback.process()";
            log.entering(STR_METHOD_NAME);
            
            // 1.1. get���ʃR�[�h�v���t�B�N�X�ꗗ()
            String[] l_odrReqNumPfxGrp = this.getOrderRequestNumberPrefixGroup();
            
            // 1.2. get�����Ώۃf�[�^�R�[�h�ꗗ()
            String[] l_requestCodesForReadList = null;
            try
            {
                l_requestCodesForReadList =
                    WEB3GentradeRequestCodesForRead.getRequestCodesForReadList(
                        WEB3EquityOrderAcceptRequest.PTYPE);
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new DataCallbackException(l_wbe.getMessage(), l_wbe);
            }
            
            // 1.3. ������t�L���[�e�[�u���ǂݍ���
            int l_intOdrReqNumPfxGrpCnt = 0;
            if (l_odrReqNumPfxGrp != null)
            {
                l_intOdrReqNumPfxGrpCnt = l_odrReqNumPfxGrp.length;
            }
            int l_intRequestCodesForReadListCnt = 0;
            if (l_requestCodesForReadList != null)
            {
                l_intRequestCodesForReadListCnt = l_requestCodesForReadList.length;
            }
            
            StringBuffer l_sbWhere = new StringBuffer();
            if (l_intRequestCodesForReadListCnt > 0)
            {
                l_sbWhere.append("(request_code = ?");
                for (int i = 1; i < l_intRequestCodesForReadListCnt; i++)
                {
                    l_sbWhere.append(" or request_code = ?");
                }
                l_sbWhere.append(") and ");
            }
            if (l_intOdrReqNumPfxGrpCnt > 0)
            {
                l_sbWhere.append("(order_request_number like ?");
                for (int i = 1; i < l_intOdrReqNumPfxGrpCnt; i++)
                {
                    l_sbWhere.append(" or order_request_number like ?");
                }
                l_sbWhere.append(") and ");
            }
            l_sbWhere.append("status = ?");
            log.debug("��������������:[" + l_sbWhere.toString() + "]");
            
            Object[] l_objWhere = new Object[l_intRequestCodesForReadListCnt + l_intOdrReqNumPfxGrpCnt + 1];
            int l_intCnt = 0;
            for (int i = 0; i < l_intRequestCodesForReadListCnt; i++)
            {
                l_objWhere[l_intCnt++] = l_requestCodesForReadList[i];
                log.debug("�����Ώۃf�[�^�R�[�h�ꗗ[" + i + "]�F[" + l_requestCodesForReadList[i] + "]");
            }
            for (int i = 0; i < l_intOdrReqNumPfxGrpCnt; i++)
            {
                l_objWhere[l_intCnt++] = l_odrReqNumPfxGrp[i]+ "%";
                log.debug("���ʃR�[�h�v���t�B�N�X�ꗗ[" + i + "]�F[" + l_odrReqNumPfxGrp[i] + "%]");
            }
            l_objWhere[l_intCnt] = WEB3HostStatusDef.NOT_STARTED_PROCESS;
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                HostEqtypeOrderAcceptRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objWhere);
            
            // 1.4. �擾�����L���[�e�[�u���̃��R�[�h�����ALoop����
            if (l_lisRecords != null && !l_lisRecords.isEmpty())
            {
                WEB3EquityOrderAcceptUnitService l_service =
                    (WEB3EquityOrderAcceptUnitService)Services.getService(
                        WEB3EquityOrderAcceptUnitService.class);
                int l_intSize = l_lisRecords.size();
                for (int i = 0; i < l_intSize; i++)
                {
                    String l_strStatus = WEB3HostStatusDef.COMPLETE_PROCESS;
                    
                    HostEqtypeOrderAcceptParams l_params =
                        new HostEqtypeOrderAcceptParams(
                            (HostEqtypeOrderAcceptRow)l_lisRecords.get(i));
                    // 1.4.1. notify������t()
                    try
                    {
                        //����������t�ꌏ�T�[�r�X.notify������t
                        //�L���[�̏��������X�V�͊�����t�ꌏ�T�[�r�X�ֈڂ�
                        String l_strAcceptStatus = l_params.getAcceptStatus();
                        if (WEB3AcceptStatusDef.OVER.equals(l_strAcceptStatus) ||
                            WEB3AcceptStatusDef.ERROR.equals(l_strAcceptStatus))
                        {
                            l_service.notifyOrderAccept(l_params);
                        }
                        else
                        {
                            l_service.notifyOrderAcceptOvertime(l_params);
                        }
                    }
                    catch (Exception l_e)
                    {
                        //--------------------
                        //�����ΏۃL���[UPDATE�@@(�G���[��)
                        //--------------------
                        if (l_e instanceof WEB3BaseRuntimeException)
                        {
                            WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_e;
                            if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
                            {
                                log.error("�������b�N���s�F" + l_params.toString());
                                continue;
                            }
                        }
    
                        //�G���[������ȊO�̏ꍇ�@@(=>�G���[)
                        log.error("�ꌏ�����ɂăG���[�����F" + l_params.toString(), l_e);
                        l_strStatus = WEB3HostStatusDef.DATA_ERROR;
                    }
    
                    // 1.4.2. ����������t�L���[�e�[�u��.�����敪��update����
                    if (!WEB3HostStatusDef.COMPLETE_PROCESS.equals(l_strStatus))
                    {
                        try
                        {
                            updateStatus(l_params, l_strStatus);
                        }
                        catch (WEB3SystemLayerException l_wse)
                        {
                            throw new DataCallbackException(l_wse.getMessage(), l_wse);
                        }
                    }
                }
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
