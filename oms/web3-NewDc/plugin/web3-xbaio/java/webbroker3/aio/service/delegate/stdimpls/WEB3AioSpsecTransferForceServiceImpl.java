head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.33.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSpsecTransferForceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������U�֋����T�[�r�XImpl(WEB3AioSpsecTransferForceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/04 ��O�� (���u) �V�K�쐬
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;

import webbroker3.aio.data.HostSpsecTransNotifyParams;
import webbroker3.aio.data.HostSpsecTransNotifyRow;
import webbroker3.aio.service.delegate.WEB3AioSpsecTransferForceService;
import webbroker3.aio.service.delegate.WEB3AioSpsecTransferForceUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * ( ��������U�֋����T�[�r�XImpl )<BR>
 * ��������U�֋����T�[�r�X�����N���X
 *
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceServiceImpl implements WEB3AioSpsecTransferForceService
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSpsecTransferForceServiceImpl.class);

    /**
     * ��������U�֋����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i��������U�֋����jexecute�v �Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 41579661009E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1 ��������U�֋����g�����U�N�V�����R�[���o�b�N�C���X�^���X�𐶐�����B
        WEB3AioSpsecTransferForceTransactionCallBack l_spsecTransferForceTransactionCallBack =
            new WEB3AioSpsecTransferForceTransactionCallBack();

        //1.2 getDefaultProcessor()
        QueryProcessor l_queryProcessor;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
        }
        catch (DataFindException l_ex)
        {
            log.error("�N�G���v���Z�b�T�̗�O", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("�N�G���v���Z�b�T�̗�O", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.3 doTransaction(TransactionCallback)
        //[doTransaction()�Ɏw�肷�����]
        //�g�����U�N�V���������F�@@TX_CREATE_NEW
        //�g�����U�N�V�����R�[���o�b�N�F�@@��������U�֋���TransactionCallback�C���X�^���X
        try
        {
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_spsecTransferForceTransactionCallBack);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�g�����U�N�V���������̗�O", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�g�����U�N�V���������̗�O", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.4 ���X�|���X�f�[�^�𐶐�����B
        WEB3BackResponse l_response = l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * ( ��������U�֋���TransactionCallback)<BR>
     *  ��������U�֋���TransactionCallback�N���X
     */
    public class WEB3AioSpsecTransferForceTransactionCallBack implements TransactionCallback
    {
        /**
         * (��������U�֋���TransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^
         * @@return WEB3AioSecurityTransferForceTransactionCallBack
         * @@roseuid 415796810178
         */
        public WEB3AioSpsecTransferForceTransactionCallBack()
        {

        }

        /**
         * ��������U�֋����������s���B <BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i��������U�֋����jprocess�v �Q��
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 415796B5005F
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {

            String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //1.1 (*1) �ȉ��̏����� ������������U�փL���[�e�[�u������
            //�s���b�N�I�v�V�����ɂă��R�[�h���擾����B
            //[��������]
            //�f�[�^�R�[�h = "GI835"�i������������j
            //�����敪 = "0"�i�������j

            //a> queryprocessr
            //throw DataNetWorkException, DataFindException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //b> where
            String l_strWhere = " request_code = ? and status = ? ";
            //c> data
            Object[] l_bindVars =
                {WEB3HostRequestCodeDef.AIO_SPSEC_TRANS_NOTIFY,
                 WEB3StatusDef.NOT_DEAL};
            //d> condition
            // ���u�� U01362�̎b��Ή� start
//            String l_strCondition = "for update";
            String l_strCondition = null;
            // ���u�� U01362�̎b��Ή� end

            //throw DataQueryException, DataNetworkException
            List l_lisHostSpsecTransNotifyRows =
                l_queryProcessor.doFindAllQuery(
                    HostSpsecTransNotifyRow.TYPE,
                    l_strWhere,
                    l_strCondition,
                    l_bindVars);

            //1.2 �擾�����L���[�e�[�u���̃��R�[�h����Loop����
            int l_intSize = 0;
            if (l_lisHostSpsecTransNotifyRows != null)
            {
                l_intSize = l_lisHostSpsecTransNotifyRows.size();
            }

            for (int i = 0; i < l_intSize; i++)
            {
                //get the row
                HostSpsecTransNotifyRow l_hostSpsecTransNotifyRow =
                    (HostSpsecTransNotifyRow)l_lisHostSpsecTransNotifyRows.get(i);
                //get the params
                HostSpsecTransNotifyParams l_hostSpsecTransNotifyParams =
                    new HostSpsecTransNotifyParams(l_hostSpsecTransNotifyRow);

                log.debug("������������U�փL���[Row" + l_hostSpsecTransNotifyRow);

                WEB3AioSpsecTransferForceUnitService l_aioSpsecTransferForceUnitService =
                    (WEB3AioSpsecTransferForceUnitService)Services.getService(
                        WEB3AioSpsecTransferForceUnitService.class);

                //a> the map to be updated
                HashMap l_map = new HashMap();

                //b> where
                String l_strWhereUpdate =
                    "institution_code = ? and branch_code = ? and " +
                    "account_code = ? and order_request_number = ? ";

                //c> data
                Object[] l_bindVarsUpdate =
                    {l_hostSpsecTransNotifyRow.getInstitutionCode(),
                        l_hostSpsecTransNotifyRow.getBranchCode(),
                        l_hostSpsecTransNotifyRow.getAccountCode(),
                        l_hostSpsecTransNotifyRow.getOrderRequestNumber()};

                try
                {
                    // ���u�� U01362�̎b��Ή� start
                    // TransactionCallback����
                    WEB3AioSpsecTransferForceNormalTransactionCallBack l_transactionCallback =
                        new WEB3AioSpsecTransferForceNormalTransactionCallBack(
                        l_hostSpsecTransNotifyRow);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

//                    //1.2.1 ������������U�փL���[.���� > 0 �̏ꍇ
//                    if (l_hostSpsecTransNotifyRow.getQuantity() > 0)
//                    {
//                        log.debug("������������U�փL���[.���� > 0 �̏ꍇ");
//                        //1.2.1.1 �V�K�����i�U�֌��{�U�֐�j�̓o�^���s���B
//                        //[����]
//                        //������������U�փL���[Params�F ������������U�փL���[Params�I�u�W�F�N�g
//
//                        //throw WEB3BaseException
//                        AioOrderUnit[] l_aioOrderUnits =
//                            l_aioSpsecTransferForceUnitService.submitOrder(
//                                    l_hostSpsecTransNotifyParams);
//
//                        if (l_aioOrderUnits == null || l_aioOrderUnits.length < 1)
//                        {
//                            log.debug("�G���[�F�Y���̐U�֒����P�ʂ��Ȃ�");
//                            throw new WEB3SystemLayerException(
//                                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
//                                this.getClass().getName() + "." + STR_METHOD_NAME);
//                        }
//                        //1.2.1.2 �،��U�֒ʒm�������s���B
//                        //[����]
//                        //�����P�ʁF submit����()�̖߂�l
//                        //�G���[�R�[�h�F "0000"�i����j
//                        //��t�ʒm�敪�F "1"�i��t�����j
//
//                        //�،��U�֒ʒmUnitService
//                        WEB3AioSecurityTransferNotifyUnitService l_aioSecurityTransferNotifyUnitService =
//                            (WEB3AioSecurityTransferNotifyUnitService)Services.getService(
//                                WEB3AioSecurityTransferNotifyUnitService.class);
//
//                        //throw WEB3BaseException
//                        l_aioSecurityTransferNotifyUnitService.execute(
//                            l_aioOrderUnits,
//                            WEB3ErrorReasonCodeDef.NORMAL,
//                            WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE);
//                    }
//                    //1.2.2 ������������U�փL���[.���� < 0 �̏ꍇ
//                    else if (l_hostSpsecTransNotifyRow.getQuantity() < 0)
//                    {
//                        log.debug("������������U�փL���[.���� < 0 �̏ꍇ");
//                        //1.2.2.1 �Y�������̎�����s���B
//                        //[����]
//                        //������������U�փL���[Params�F ������������U�փL���[Params�I�u�W�F�N�g
//                        l_aioSpsecTransferForceUnitService.submitCancel(
//                                l_hostSpsecTransNotifyParams);
//                    }
//
//                    //1.2.3 ������������U�փL���[.�����敪�Ɉȉ��̒l���Z�b�g���čX�V����B
//                    //"9"�i�G���[�j�F ��L�����iLoop���̏����j�ŗ�O�����������ꍇ
//                    //"1"�i�����ρj�F ����ȊO�̏ꍇ
//                    l_map.put("status", WEB3HostStatusDef.COMPLETE_PROCESS);
                    // ���u�� U01362�̎b��Ή� end
                }
                // ���u�� U01362�̎b��Ή� start
//                catch (WEB3BaseException l_ex)
//                {
//                    log.debug("�،��U�֒ʒm�������s�ł���", l_ex);
//                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);
//                }
//
//                //update
//                l_queryProcessor.doUpdateAllQuery(
//                    HostSpsecTransNotifyRow.TYPE,
//                    l_strWhereUpdate,
//                    l_bindVarsUpdate,
//                    l_map);

                catch (WEB3BaseRuntimeException l_ex)
                {
                    log.debug("�،��U�֒ʒm�������s�ł���", l_ex);
                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

                    //update
                    l_queryProcessor.doUpdateAllQuery(
                        HostSpsecTransNotifyRow.TYPE,
                        l_strWhereUpdate,
                        l_bindVarsUpdate,
                        l_map);
                }
                catch (Exception l_ex)
                {
                    log.debug("�،��U�֒ʒm�������s�ł���", l_ex);
                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

                    //update
                    l_queryProcessor.doUpdateAllQuery(
                        HostSpsecTransNotifyRow.TYPE,
                        l_strWhereUpdate,
                        l_bindVarsUpdate,
                        l_map);
                }
                // ���u�� U01362�̎b��Ή� end
            }

            log.exiting(STR_METHOD_NAME);

            return null;
        }
    }
}
@
