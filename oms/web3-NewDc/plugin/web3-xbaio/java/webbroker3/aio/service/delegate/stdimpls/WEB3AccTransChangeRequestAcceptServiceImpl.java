head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.33.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֐�����t�T�[�r�XImpl(WEB3AccTransChangeRequestAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/25 �����(���u) ���r���[
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostTransferAcceptRow;
import webbroker3.aio.service.delegate.WEB3AccTransChangeAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeCompleteUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�U�֐�����t�T�[�r�XImpl)<BR>
 * �U�֐�����t�T�[�r�X�����N���X<BR>
 *
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestAcceptServiceImpl
    implements WEB3AccTransChangeRequestAcceptService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeRequestAcceptServiceImpl.class);

    /**
     * �U�֐�����t�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�U�֐�����t�j�U�֐�����t�v�Q��
     * @@param l_request - (���N�G�X�g�f�[�^)
     *
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1C13036C
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            // �P�j �U�֐�����t�g�����U�N�V�����R�[���o�b�N�C���X�^���X�𐶐�����
            WEB3AccTransChangeRequestAcceptTransactionCallback l_transactionCallback =
                new WEB3AccTransChangeRequestAcceptTransactionCallback();

            // �Q�j�@@�N�G���v���Z�b�T���擾����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // �R�jDB�g�����U�N�V�������������{����B
            // [doTransaction()�Ɏw�肷�����]
            // �g�����U�N�V���������F�@@TX_CREATE_NEW
            // �g�����U�N�V�����R�[���o�b�N�F�@@�U�֐�����tTransactionCallback�C���X�^���X
            l_queryProcessor.doTransaction(l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME + " __Error[DB�T�[�o�Ƃ̒ʐM�Ɏ��s����]__" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        WEB3BackResponse l_response =
            l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�U�֐�����tTransactionCallback�N���X)
     */
    public class WEB3AccTransChangeRequestAcceptTransactionCallback
        implements TransactionCallback
    {

        /**
         * ���O���[�e�B���e�B<BR>
         */
        private WEB3LogUtility l_log =
            WEB3LogUtility.getInstance(
                WEB3AccTransChangeRequestAcceptTransactionCallback.class);

        /**
         * �f�t�H���g�R���X�g���N�^
         * @@return WEB3AccTransChangeRequestAcceptTransactionCallback
         * @@roseuid 415A6F3F0270
         */
        public WEB3AccTransChangeRequestAcceptTransactionCallback()
        {

        }

        /**
         * �U�֐�����t���������{����B <BR>
         * <BR>
         * �V�[�P���X�} <BR>
         * �u�i�U�֐�����t�jprocess�v�Q��
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 413C1FDC003F
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "process()";
            l_log.entering(STR_METHOD_NAME);

            // 1) �U�֐�����t�L���[�e�[�u���Ǎ���
            String l_strWhere = "request_code = ? " +
                                "and status = ?";
            // ���u�� U01504�̎b��Ή� start
//            String l_strCondition = "for update";
            String l_strCondition = null;
            // ���u�� U01504�̎b��Ή� end

            Object[] l_objWhere = { WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ACCEPT ,
                WEB3StatusDef.NOT_DEAL };

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(
                HostTransferAcceptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhere);

            int l_intResultLength = 0;
            if (l_lisSearchResult != null && l_lisSearchResult.size() != 0)
            {
                l_intResultLength = l_lisSearchResult.size();
            }

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_orderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();

            WEB3AccTransChangeAcceptUnitService l_AcceptService =
                (WEB3AccTransChangeAcceptUnitService) Services.getService(
                    WEB3AccTransChangeAcceptUnitService.class);

            WEB3AccTransChangeCompleteUnitService l_CompleteService =
                (WEB3AccTransChangeCompleteUnitService) Services.getService(
                    WEB3AccTransChangeCompleteUnitService.class);


            // 2) �擾�������R�[�h����Loop����
            for (int i = 0; i < l_intResultLength; i++)
            {
                // �U�֐�����t�L���[Params�̎擾
                HostTransferAcceptRow l_hostTransferAcceptRow =
                    (HostTransferAcceptRow)l_lisSearchResult.get(i);


                // 2 - 1) �����P�ʃI�u�W�F�N�g�̔z����擾����B
                // [����]
                // �،���ЃR�[�h�F �U�֐�����t�L���[�e�[�u��.�،���ЃR�[�h
                // ���X�R�[�h�F �U�֐�����t�L���[�e�[�u��.���X�R�[�h
                // �ڋq�R�[�h�F �U�֐�����t�L���[�e�[�u��.�ڋq�R�[�h
                // ���ʃR�[�h�F �U�֐�����t�L���[�e�[�u��.���ʃR�[�h
                AioOrderUnit[] l_aioOrderUnit = null;

                boolean l_blnIsFailProcess = false;
                Map l_map = new Hashtable();

                try
                {

                    // ���u�� U01504�̎b��Ή� start
                    WEB3AccTransChangeRequestAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3AccTransChangeRequestAcceptNormalTransactionCallback(
                        l_hostTransferAcceptRow);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

//                    l_aioOrderUnit = l_orderManager.getTransferOrderUnit(
//                        l_hostTransferAcceptRow.getInstitutionCode(),
//                        l_hostTransferAcceptRow.getBranchCode(),
//                        l_hostTransferAcceptRow.getAccountCode(),
//                        l_hostTransferAcceptRow.getOrderRequestNumber());
//
//                    // 2 - 2) �擾���������P�ʖ���Loop����
//                    if(l_aioOrderUnit.length != 0)
//                    {
//                        for(int j = 0; j<l_aioOrderUnit.length; j++)
//                        {
//                            // 2 - 2 - 1)�U�֐�����tDB�X�V�������s���B
//                            // [����]
//                            // �����P�ʁF �����P�ʃI�u�W�F�N�g
//                            // �G���[�R�[�h�F �U�֐�����t�L���[�e�[�u��.�G���[���b�Z�[�W
//                            // ��t�ʒm�敪�F �U�֐�����t�L���[�e�[�u��.��t�ʒm�敪
//                            log.debug("l_hostTransferAcceptRow.getErrorMessage() = "
//                                    + l_hostTransferAcceptRow.getErrorMessage());
//                            l_AcceptService.execute(
//                                l_aioOrderUnit[j],
//                                l_hostTransferAcceptRow.getErrorMessage(),
//                                l_hostTransferAcceptRow.getAcceptDiv());
//
//                            if(WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(
//                                l_hostTransferAcceptRow.getAcceptDiv()))
//                            {
//                                // 2 - 2 - 2)�U�֊��������ɔ��������f�[�^�̍X�V��
//                                // �g�����U�N�V�����f�[�^�̐������s���B
//                                // [����]
//                                // �����P�ʁF �����P�ʃI�u�W�F�N�g
//                                l_CompleteService.completeChange(
//                                    l_aioOrderUnit[j]);
//                            }
//                        }
//                    }
                    // ���u�� U01504�̎b��Ή� end
                }
                  // ���u�� U01504�̎b��Ή� start
//                catch (WEB3BaseException l_ex)
//                {
//                    l_log.debug("__an WEB3BaseException__", l_ex);
//                    l_blnIsFailProcess = true;
//                }
//                catch (NotFoundException l_ex)
//                {
//                    l_log.debug(
//                        "__NotFoundException__ when l_orderManager.getTransferOrderUnit:"
//                        , l_ex);
//                    l_blnIsFailProcess = true;
//                }
                catch (Exception l_ex)
                {
                    l_log.debug("__an Exception__", l_ex);
                    l_blnIsFailProcess = true;
                }
                // ���u�� U01504�̎b��Ή� end

                // ���u�� U01504�̎b��Ή� start
//                if (l_blnIsFailProcess)
//                {
//                    // �����Ώۂ̐U�֐�����t�L���[�e�[�u��.�����敪��ݒ�p
//                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
//                    l_log.debug("__an WEB3BaseException__ ");
//                }
//                else
//                {
//                    // �����Ώۂ̐U�֐�����t�L���[�e�[�u��.�����敪��ݒ�p
//                    l_map.put("status", WEB3StatusDef.DEALT);
//                }
//
//                // �����Ώۂ̐U�֐�����t�L���[�e�[�u��.�����敪��ݒ�p
//                String l_strUpdateWhere = " institution_code = ? "
//                                   + " and branch_code = ? "
//                                   + " and account_code = ? "
//                                   + "and order_request_number = ?";
//
//                String[] l_strArrayUpdateParams = {
//                    l_hostTransferAcceptRow.getInstitutionCode(),
//                    l_hostTransferAcceptRow.getBranchCode(),
//                    l_hostTransferAcceptRow.getAccountCode(),
//                    l_hostTransferAcceptRow.getOrderRequestNumber()
//                };
//
//                // 2 - 3�j�@@�����Ώۂ̎����t�L���[���R�[�h.�����敪��ݒ�h
//                l_queryProcessor.doUpdateAllQuery(
//                    HostTransferAcceptRow.TYPE,
//                    l_strUpdateWhere,
//                    l_strArrayUpdateParams,
//                    l_map);

                if (l_blnIsFailProcess)
                {
                    // �����Ώۂ̐U�֐�����t�L���[�e�[�u��.�����敪��ݒ�p
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);

                    // �����Ώۂ̐U�֐�����t�L���[�e�[�u��.�����敪��ݒ�p
                    String l_strUpdateWhere = " institution_code = ? "
                                       + " and branch_code = ? "
                                       + " and account_code = ? "
                                       + "and order_request_number = ?";

                    String[] l_strArrayUpdateParams = {
                        l_hostTransferAcceptRow.getInstitutionCode(),
                        l_hostTransferAcceptRow.getBranchCode(),
                        l_hostTransferAcceptRow.getAccountCode(),
                        l_hostTransferAcceptRow.getOrderRequestNumber()
                    };

                    // 2 - 3�j�@@�����Ώۂ̎����t�L���[���R�[�h.�����敪��ݒ�h
                    l_queryProcessor.doUpdateAllQuery(
                        HostTransferAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strArrayUpdateParams,
                        l_map);
                }
                // ���u�� U01504�̎b��Ή� end

            }

            l_log.entering(STR_METHOD_NAME);
            return null;
        }
    }
}
@
