head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferForceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֋����T�[�r�XImpl(WEB3AioSecurityTransferForceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 ���z (���u) �V�K�쐬
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

import webbroker3.aio.data.HostMrgsecTransNotifyParams;
import webbroker3.aio.data.HostMrgsecTransNotifyRow;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferForceService;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferForceUnitService;
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
 * (�،��U�֋����T�[�r�XImpl)<BR>
 * �،��U�֋����T�[�r�X�����N���X
 *
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferForceServiceImpl implements WEB3AioSecurityTransferForceService
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferForceServiceImpl.class);

    /**
     * �،��U�֋����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�،��U�֋����jexecute�v �Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 41579661009E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3BackRequest l_request)";
        log.entering(l_strMethodName);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }

        //1.1 �،��U�֋����g�����U�N�V�����R�[���o�b�N�C���X�^���X�𐶐�����B
        WEB3AioSecurityTransferForceTransactionCallBack l_aioSecurityTransferForceTransactionCallBack =
            new WEB3AioSecurityTransferForceTransactionCallBack();

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
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("�N�G���v���Z�b�T�̗�O", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        //1.3 doTransaction(TransactionCallback)
        //[doTransaction()�Ɏw�肷�����]
        //�g�����U�N�V���������F�@@TX_CREATE_NEW
        //�g�����U�N�V�����R�[���o�b�N�F�،��U�֋���TransactionCallback�C���X�^���X
        try
        {
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_aioSecurityTransferForceTransactionCallBack);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�g�����U�N�V���������̗�O", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�g�����U�N�V���������̗�O", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        //1.4 ���X�|���X�f�[�^�𐶐�����B
        WEB3BackResponse l_response = l_request.createResponse();

        log.exiting(l_strMethodName);

        return l_response;
    }

    /**
     * (�،��U�֋���TransactionCallback)<BR>
     * �،��U�֋���TransactionCallback�N���X
     */
    public class WEB3AioSecurityTransferForceTransactionCallBack implements TransactionCallback
    {
        /**
         * (�،��U�֋���TransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^
         * @@return
         * webbroker3.aio.service.delegate.stdimpls.WEB3AioSecurityTransferForceServiceImpl.WEB3AioSecurityTransferForceTransaction
         * CallBack
         * @@roseuid 415796810178
         */
        public WEB3AioSecurityTransferForceTransactionCallBack()
        {

        }

        /**
         * �،��U�֋����������s���B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�،��U�֋����jprocess�v �Q��
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 415796B5005F
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String l_strMethodName = "process()";
            log.entering(l_strMethodName);

            //1.1 (*1) �ȉ��̏����� ��p�U�֋����L���[�e�[�u������
            //�s���b�N�I�v�V�����ɂă��R�[�h���擾����B
            //[��������]
            //�f�[�^�R�[�h = "GI813"�i��p�U�֋����j
            //�����敪 = "0"�i�������j

            //a> queryprocessr
            //throw DataNetWorkException, DataFindException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //b> where
            String l_strWhere = " request_code = ? and status = ? ";
            //c> data
            Object[] l_bindVars =
                {WEB3HostRequestCodeDef.AIO_MRGSEC_TRANS_NOTIFY, WEB3StatusDef.NOT_DEAL};
            //d> condition
            // ���u�� U01362�̎b��Ή� start
//            String l_strCondition = "for update";
            String l_strCondition = null;
            // ���u�� U01362�̎b��Ή� end

            //throw DataQueryException, DataNetworkException
            List l_lisHostMrgsecTransNotifyRows =
                l_queryProcessor.doFindAllQuery(
                    HostMrgsecTransNotifyRow.TYPE,
                    l_strWhere,
                    l_strCondition,
                    l_bindVars);

            //1.2 �擾�����L���[�e�[�u���̃��R�[�h����Loop����
            int l_intSize = 0;
            if (l_lisHostMrgsecTransNotifyRows != null && l_lisHostMrgsecTransNotifyRows.size() != 0)
            {
                l_intSize = l_lisHostMrgsecTransNotifyRows.size();
            }

            for (int i = 0; i < l_intSize; i++)
            {
                //1.2.1 �V�K�����i�U�֌��{�U�֐�j�̓o�^���s���B
                //[����]
                //��p�U�֋����L���[Params�F ��p�U�֋����L���[Params�I�u�W�F�N�g

                //�،��U�֋���UnitService
                WEB3AioSecurityTransferForceUnitService l_aioSecurityTransferForceUnitService =
                    (WEB3AioSecurityTransferForceUnitService)Services.getService(
                        WEB3AioSecurityTransferForceUnitService.class);

                //get the row
                HostMrgsecTransNotifyRow l_hostMrgsecTransNotifyRow =
                    (HostMrgsecTransNotifyRow)l_lisHostMrgsecTransNotifyRows.get(i);

                //get the params
                HostMrgsecTransNotifyParams l_hostMrgsecTransNotifyParams =
                    new HostMrgsecTransNotifyParams(l_hostMrgsecTransNotifyRow);

                //****************pre-deal before 1.2.3*****************
                //a> the map to be updated
                HashMap l_map = new HashMap();

                //b> where
                String l_strWhereUpdate =
                    " institution_code = ? and branch_code = ? and account_code = ? and order_request_number = ? ";

                //c> data
                Object[] l_bindVarsUpdate =
                    {l_hostMrgsecTransNotifyRow.getInstitutionCode(),
                        l_hostMrgsecTransNotifyRow.getBranchCode(),
                        l_hostMrgsecTransNotifyRow.getAccountCode(),
                        l_hostMrgsecTransNotifyRow.getOrderRequestNumber()};
                //****************end of the pre-deal*******************

                try
                {
                    // ���u�� U01362�̎b��Ή� start
                    // TransactionCallback����
                    WEB3AioSecurityTransferForceNormalTransactionCallBack l_transactionCallback =
                        new WEB3AioSecurityTransferForceNormalTransactionCallBack(
                        l_hostMrgsecTransNotifyRow);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

//                    //throw WEB3BaseException
//                    AioOrderUnit[] l_aioOrderUnits =
//                        l_aioSecurityTransferForceUnitService.submitOrder(l_hostMrgsecTransNotifyParams);
//
//                    if (l_aioOrderUnits == null || l_aioOrderUnits.length < 1)
//                    {
//                        log.debug("�G���[�F�Y���̐U�֒����P�ʂ��Ȃ�");
//                        throw new WEB3SystemLayerException(
//                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
//                            this.getClass().getName() + "." + l_strMethodName);
//                    }
//
//                    //1.2.2 �،��U�֒ʒm�������s���B
//                    //[����]
//                    //�����P�ʁF submit����()�̖߂�l --l_aioOrderUnit
//                    //�G���[�R�[�h�F "0000"�i����j--WEB3ErrorReasonCodeDef.NORMAL
//                    //��t�ʒm�敪�F "1"�i��t�����j--WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE
//
//                    //�،��U�֒ʒmUnitService
//                    WEB3AioSecurityTransferNotifyUnitService l_aioSecurityTransferNotifyUnitService =
//                        (WEB3AioSecurityTransferNotifyUnitService)Services.getService(
//                            WEB3AioSecurityTransferNotifyUnitService.class);
//
//                    //throw WEB3BaseException
//                    l_aioSecurityTransferNotifyUnitService.execute(
//                        l_aioOrderUnits,
//                        WEB3ErrorReasonCodeDef.NORMAL,
//                        WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE);
//
//                    //1.2.3 ��p�U�֋����L���[.�����敪�Ɉȉ��̒l���Z�b�g���čX�V����B
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
//                    HostMrgsecTransNotifyRow.TYPE,
//                    l_strWhereUpdate,
//                    l_bindVarsUpdate,
//                    l_map);

                catch (WEB3BaseRuntimeException l_ex)
                {
                    log.debug("�،��U�֒ʒm�������s�ł���", l_ex);
                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

                    //update
                    l_queryProcessor.doUpdateAllQuery(
                        HostMrgsecTransNotifyRow.TYPE,
                        l_strWhereUpdate,
                        l_bindVarsUpdate,
                        l_map);
                }
				catch (Exception l_ex)
				{
					log.debug("__an Exception__ ", l_ex);
					l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

					//update
					l_queryProcessor.doUpdateAllQuery(
						HostMrgsecTransNotifyRow.TYPE,
						l_strWhereUpdate,
						l_bindVarsUpdate,
						l_map);
				}
                // ���u�� U01362�̎b��Ή� end
            }

            log.exiting(l_strMethodName);

            return null;
        }
    }
}
@
