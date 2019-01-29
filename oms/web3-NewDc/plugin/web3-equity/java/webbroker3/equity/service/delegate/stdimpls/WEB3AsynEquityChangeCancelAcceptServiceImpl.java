head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AsynEquityChangeCancelAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������t�T�[�r�XImpl(WEB3AsynEquityChangeCancelAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 �����(���u) �V�K�쐬
                   2004/09/14 ���C�g(���u) �C��
                   2004/09/22 Ḗ@@��(���u) �C��
                   2004/12/13 �����a��(SRA) �c�Č��Ή� �m��.�R�S�Q
                   2004/12/21 �����a��(SRA) JavaDoc�C��
                   2005/01/05 �����a��(SRA) �������b�N�C���Ή�
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2005/03/09 ���iFLJ�j�񓯊����s�Ή��i�V�K�N���X�j
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Iterator;
import java.util.List;

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
import webbroker3.equity.message.WEB3EquityChangeCancelAcceptRequest;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;

import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;

/**
 *  (�������������t�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �������������t�T�[�r�X�����N���X
 * @@author �@@��
 * @@version 1.0
 */
public class WEB3AsynEquityChangeCancelAcceptServiceImpl
    implements Runnable
{
    /**
     * <p>�i���O�o�̓��[�e�B���e�B)�B</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3AsynEquityChangeCancelAcceptServiceImpl.class);

    /**
     *�@@�������������t�������N�G�X�g
     */
    private WEB3EquityChangeCancelAcceptRequest l_eqOrderAcceptRequest;

   /**
     * @@roseuid 40AD626100FC
     */
    public WEB3AsynEquityChangeCancelAcceptServiceImpl(WEB3EquityChangeCancelAcceptRequest
        l_eqOrderAcceptRequest)
    {
        this.l_eqOrderAcceptRequest=l_eqOrderAcceptRequest;
    }

    /**
     * <p>�������������t���������{����B<br>
     * <br>
     * �V�[�P���X�}�u�i�������������t�T�[�r�X�j���������t�v�Q�ƁB</p>
     * @@param l_request ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3EquityChangeCancelAcceptResponse
     * @@throws WEB3BaseException
     * @@roseuid 4137CF3500E0
     */
     public void run()
    {
        final String STR_METHOD_NAME = "run()";
        log.entering(STR_METHOD_NAME);

        // �������������t�������s��
        try
        {
            //1.2 getDefaultProcessor()
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            //1.3 �������������tTransactionCallback()
            WEB3EquityChangeCancelAcceptServiceTransactionCallback l_callBack =
                new WEB3EquityChangeCancelAcceptServiceTransactionCallback();
            //1.4 set���ʃR�[�h�v���t�B�N�X�ꗗ()
            l_callBack.setOrderRequestNumberPrefixGroup(
                l_eqOrderAcceptRequest.orderRequestNumberPrefixGroup);
            //1.5 doTransaction()
            l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW, l_callBack);
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
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
            new WEB3GentradeDaemonTriggerManager().releaseThread(l_eqOrderAcceptRequest.threadNo.longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * <p>�i�������������tTransactionCallback�j�B</p>
     * <p>�������������tTransactionCallback�N���X�B</p>
     */
    private class WEB3EquityChangeCancelAcceptServiceTransactionCallback
        implements TransactionCallback
    {

        /**
         * <p>�i���ʃR�[�h�v���t�B�N�X�ꗗ�j�B</p>
         * <p>���ʃR�[�h�v���t�B�N�X�ꗗ�B</p>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * <p>�i�������������tTransactionCallback �j�B</p>
         * <p>�R���X�g���N�^�B</p>
         */
        public WEB3EquityChangeCancelAcceptServiceTransactionCallback()
        {
        }

        /**
         * <p>�iprocess�j�B</p>
         * <p>�g�����U�N�V�������������{����B<br>
         * <br>
         * �V�[�P���X�}<br>
         * �u�i�������������t�T�[�r�X�jprocess�v�Q�ƁB</p>
         * @@return WEB3BaseException�i���������ɂ�WEB3BaseException��O�����������ꍇ�j<BR>
         * @@throws DataCallbackException
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@roseuid 4137CF3501A8
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            // 1.1 get���ʃR�[�h�v���t�B�N�X�ꗗ()
            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();

            // 1.2 [��������]������t�L���[�e�[�u��.�f�[�^�R�[�h == "�����������"�iAI80B�j
            // ������t�L���[�e�[�u��.�����敪 == "������"

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");
            l_sbWhere.append(" and status = ? ");
            int l_intRequestNumberPrefixGroupCnt = 0;
            if (l_orderRequestNumberPrefixGroup != null)
            {
                l_intRequestNumberPrefixGroupCnt = l_orderRequestNumberPrefixGroup.length;
            }
            if (l_intRequestNumberPrefixGroupCnt > 0)
            {
                l_sbWhere.append(" and (order_request_number like ?");
                for (int i = 1; i < l_intRequestNumberPrefixGroupCnt; i++)
                {
                    l_sbWhere.append(" or order_request_number like ?");
                }
                l_sbWhere.append(")");
            }
            Object[] l_objEquityOrderUnitWhere = new Object[l_intRequestNumberPrefixGroupCnt + 2];
            l_objEquityOrderUnitWhere[0] = WEB3HostRequestCodeDef.EQUITY_CHANGE_CANCEL;
            l_objEquityOrderUnitWhere[1] = WEB3StatusDef.NOT_DEAL;
            for (int i = 0 ; i < l_intRequestNumberPrefixGroupCnt ; i++)
            {
                l_objEquityOrderUnitWhere[i + 2] = l_orderRequestNumberPrefixGroup[i] + "%";
                log.debug("���ʃR�[�h�v���t�B�N�X�ꗗ[" + i + "]�F[" + l_orderRequestNumberPrefixGroup[i] + "%]");
            }

            QueryProcessor l_qp = Processors.getDefaultProcessor();

            // ������t�L���[�e�[�u���̓ǂݍ��ݎ��s
            List l_lisHostEqtypeOrderAccept =
                l_qp.doFindAllQuery(
                    HostEqtypeOrderAcceptRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    null,
                    l_objEquityOrderUnitWhere);
            
            WEB3EquityChangeCancelAcceptUnitService l_changeCancelService =
                (WEB3EquityChangeCancelAcceptUnitService) Services.getService(WEB3EquityChangeCancelAcceptUnitService.class);
            if (l_lisHostEqtypeOrderAccept != null)
            {
                Iterator l_iterator = l_lisHostEqtypeOrderAccept.iterator();
                HostEqtypeOrderAcceptParams l_params;

                // 1.3 * �擾�����L���[�e�[�u���̃��R�[�h�����ALoop����
                while (l_iterator.hasNext())
                {
                    l_params = new HostEqtypeOrderAcceptParams((HostEqtypeOrderAcceptRow)l_iterator.next());
                    try
                    {
                        // 1.3.1 notify���������t(����������t�L���[Params : ����������t�L���[Params)
                        String l_strAcceptStatus = l_params.getAcceptStatus();
                        if (WEB3AcceptStatusDef.OVER.equals(l_strAcceptStatus) ||
                            WEB3AcceptStatusDef.ERROR.equals(l_strAcceptStatus))
                        {
                            l_changeCancelService.notifyChangeCancelAccept(l_params);
                        }
                        else
                        {
                            l_changeCancelService.notifyChangeCancelAcceptOvertime(l_params);
                        }

                        // 1.3.2 * ����������t�L���[�e�[�u��.�����敪��update����
                         //�L���[�̍X�V�͊������������t�ꌏ�T�[�r�X�ֈڂ�
                        //log.debug("1.3.2 ����������t�L���[�e�[�u��.�����敪��update����");
                        //l_mapChanges.put("status", WEB3StatusDef.DEALT);
                        //l_qp.doUpdateQuery(l_params.getPrimaryKey(),l_mapChanges);
                    }
                    catch (Exception l_ex)
                    {
                        if (l_ex instanceof WEB3BaseRuntimeException)
                        {
                            WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_ex;
                            if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
                            {
                                log.debug("�������b�N���s�F" + l_params.toString());
                                continue;
                            }
                        }
                        log.error("�ꌏ�����ɂăG���[����", l_ex);
                        // 1.3.2 * ����������t�L���[�e�[�u��.�����敪��update����
                        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_params.setStatus(WEB3StatusDef.DATA_ERROR);
                        l_qp.doUpdateQuery(l_params);
                    }
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * <p>�iget���ʃR�[�h�v���t�B�N�X�ꗗ�j�B</p>
         * <p>���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B</p>
         * @@return ���ʃR�[�h�v���t�B�N�X�ꗗ
         */
        public String[] getOrderRequestNumberPrefixGroup() {
            return this.orderRequestNumberPrefixGroup;
        }

        /**
         * <p>�iset���ʃR�[�h�v���t�B�N�X�ꗗ�j�B</p>
         * <p>�����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B</p>
         * @@param orderRequestNumberPrefixGroup ���ʃR�[�h�v���t�B�N�X�ꗗ
         */
        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup) {
            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
        }
    }
}
@
