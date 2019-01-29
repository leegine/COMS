head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֒ʒm�T�[�r�X�����N���X(WEB3AioSecurityTransferNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 �����(���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioUtility;
import webbroker3.aio.data.HostMrgsecTransAcceptRow;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferNotifyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�،��U�֒ʒm�T�[�r�XImpl)
 * �،��U�֒ʒm�T�[�r�X�����N���X
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferNotifyServiceImpl implements WEB3AioSecurityTransferNotifyService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferNotifyServiceImpl.class);

    /**
     * @@roseuid 41B0317503A9
     */
    public WEB3AioSecurityTransferNotifyServiceImpl()
    {

    }

    /**
     * �،��U�֒ʒm�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�،��U�֒ʒm�jexecute�v �Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 415781B7037A
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1 �،��U�֒ʒmTransactionCallback( )
        WEB3AioSecurityTransferNotifyTransactionCallback l_callBackService =
            new WEB3AioSecurityTransferNotifyTransactionCallback();

        //1.2 getDefaultProcessor( )
        QueryProcessor l_processors = WEB3AioUtility.getProcessor();

        try
        {
            //1.3 doTransaction(TransactionCallback)
            l_processors.doTransaction(l_callBackService);

            //1.4 createResponse( )
            WEB3BackResponse l_response = l_request.createResponse();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (DataException l_ex)
        {
            log.error("DB�g�����U�N�V�������������{�G���[", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (�،��U�֒ʒmTransactionCallback)<BR>
     * �،��U�֒ʒmTransactionCallback�N���X
     */
    public class WEB3AioSecurityTransferNotifyTransactionCallback implements TransactionCallback
    {
        /**
         *  ���O���[�e�B���e�B<BR>
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3AioSecurityTransferNotifyTransactionCallback.class);

        /**
         * (�،��U�֒ʒmTransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^
         * @@roseuid 4157822D02ED
         */
        public WEB3AioSecurityTransferNotifyTransactionCallback()
        {
        }

        /**
         * ������t���������{����B <BR>
         * <BR>
         * �V�[�P���X�} <BR>
         * �u�i�،��U�֒ʒm�jprocess�v �Q��
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 415781E3031C
         */
        public Object process()
            throws DataQueryException, DataNetworkException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            m_log.entering(STR_METHOD_NAME);

            // 1.1 ��p�U�֎�t�L���[�e�[�u���Ǎ���
            //�N�G���v���Z�b�T���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //(*1) �ȉ��̏����� ��p�U�֎�t�L���[�e�[�u������
            //�s���b�N�I�v�V�����ɂă��R�[�h���擾����B
            //[��������]
            //�f�[�^�R�[�h = "GI80G"�i��p�U�֐�����t�j
            //�����敪 = "0"�i�������j
            List l_lisHostMrgsecTransAcceptRows =
                l_queryProcessor.doFindAllQuery(
                    HostMrgsecTransAcceptRow.TYPE,
                    "request_code = ? and status = ?",
                    // ���u�� U01362�̎b��Ή� start
//                    "for update",
                    null,
                    // ���u�� U01362�̎b��Ή� end
                    new Object[]{
                        WEB3HostRequestCodeDef.AIO_MRGSEC_TRANS_ACCEPT,
                        WEB3HostStatusDef.NOT_STARTED_PROCESS});

            // 1.2 �擾�����L���[�e�[�u���̃��R�[�h����Loop����
            for (int i = 0; i < l_lisHostMrgsecTransAcceptRows.size(); i++)
            {
                HostMrgsecTransAcceptRow l_hostMrgsecTransAcceptRow =
                    (HostMrgsecTransAcceptRow)l_lisHostMrgsecTransAcceptRows.get(i);

                HashMap l_map = new HashMap();
                String l_strUpdateWhere =
                    " institution_code = ? and branch_code = ? and account_code = ? and order_request_number = ? ";
                String[] l_strUpdateWhereValues = {
                        l_hostMrgsecTransAcceptRow.getInstitutionCode(),
                        l_hostMrgsecTransAcceptRow.getBranchCode(),
                        l_hostMrgsecTransAcceptRow.getAccountCode(),
                        l_hostMrgsecTransAcceptRow.getOrderRequestNumber() };

                WEB3AioOrderManager l_orderManager =
                    (WEB3AioOrderManager)GtlUtils.getTradingModule(ProductTypeEnum.AIO).getOrderManager();

                try
                {
                    //1.2.1  get�U�֒����P��(String, String, String, String)
                    //[����]
                    // �،���ЃR�[�h�F ��p�U�֎�t�L���[.�،���ЃR�[�h
                    // ���X�R�[�h�F ��p�U�֎�t�L���[.���X�R�[�h
                    // �ڋq�R�[�h�F ��p�U�֎�t�L���[.�ڋq�R�[�h
                    // ���ʃR�[�h�F ��p�U�֎�t�L���[.���ʃR�[�h
                    AioOrderUnit[] l_aioOrderUnits =
                        l_orderManager.getTransferOrderUnit(
                            l_hostMrgsecTransAcceptRow.getInstitutionCode(),
                            l_hostMrgsecTransAcceptRow.getBranchCode(),
                            l_hostMrgsecTransAcceptRow.getAccountCode(),
                            l_hostMrgsecTransAcceptRow.getOrderRequestNumber());

                    //
                    if (l_aioOrderUnits == null || l_aioOrderUnits.length < 1)
                    {
                        log.error("�G���[�F�Y���̐U�֒����P�ʂ����݂��Ȃ�");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
                    }

                    // ���u�� U01362�̎b��Ή� start
                    // TransactionCallback����
                    WEB3AioSecurityTransferNotifyNormalTransactionCallback l_transactionCallback =
                        new WEB3AioSecurityTransferNotifyNormalTransactionCallback(
                            l_hostMrgsecTransAcceptRow,
                            l_aioOrderUnits);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

//                    WEB3AioSecurityTransferNotifyUnitService l_unitService =
//                        (WEB3AioSecurityTransferNotifyUnitService)
//                            Services.getService(WEB3AioSecurityTransferNotifyUnitService.class);
//
//                    //1.2.2 execute(AioOrderUnit[], String, String)
//                    //[����]
//                    // �����P�ʁF get�U�֒����P��()�̖߂�l
//                    // �G���[�R�[�h�F ��p�U�֎�t�L���[.�G���[���b�Z�[�W
//                    // ��t�ʒm�敪�F ��p�U�֎�t�L���[.��t�ʒm�敪
//                    l_unitService.execute(
//                        l_aioOrderUnits,
//                        l_hostMrgsecTransAcceptRow.getErrorMessage(),
//                        l_hostMrgsecTransAcceptRow.getAcceptDiv());
//
//                    l_map.put("status", WEB3HostStatusDef.COMPLETE_PROCESS);
                    // ���u�� U01362�̎b��Ή� end
                }
                // ���u�� U01362�̎b��Ή� start
//                catch (NotFoundException l_ex)
//                {
//                    log.error("�G���[�F�Y���̐U�֒����P�ʂ����݂��Ȃ�", l_ex);
//                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);
//                }
//                catch (WEB3BaseException l_ex)
//                {
//                    log.error("�G���[�F�،��U�֒ʒm�������̃G���[", l_ex);
//                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);
//                }
//
//                //  1.2.3 �L���[�e�[�u���̃��R�[�h�̏����敪�̍X�V
//                l_queryProcessor.doUpdateAllQuery(
//                    HostMrgsecTransAcceptRow.TYPE,
//                    l_strUpdateWhere,
//                    l_strUpdateWhereValues,
//                    l_map);

                catch (WEB3BaseRuntimeException l_ex)
                {
                    log.error("__an WEB3BaseRuntimeException__ ", l_ex);
                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

                    //  1.2.3 �L���[�e�[�u���̃��R�[�h�̏����敪�̍X�V
                    l_queryProcessor.doUpdateAllQuery(
                        HostMrgsecTransAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strUpdateWhereValues,
                        l_map);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�G���[�F�Y���̐U�֒����P�ʂ����݂��Ȃ�", l_ex);
                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

                    //  1.2.3 �L���[�e�[�u���̃��R�[�h�̏����敪�̍X�V
                    l_queryProcessor.doUpdateAllQuery(
                        HostMrgsecTransAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strUpdateWhereValues,
                        l_map);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error("�G���[�F�،��U�֒ʒm�������̃G���[", l_ex);
                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

                    //  1.2.3 �L���[�e�[�u���̃��R�[�h�̏����敪�̍X�V
                    l_queryProcessor.doUpdateAllQuery(
                        HostMrgsecTransAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strUpdateWhereValues,
                        l_map);
                }
				catch (Exception l_ex)
				{
					log.error("__an Exception__ ", l_ex);
					l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

					//  1.2.3 �L���[�e�[�u���̃��R�[�h�̏����敪�̍X�V
					l_queryProcessor.doUpdateAllQuery(
						HostMrgsecTransAcceptRow.TYPE,
						l_strUpdateWhere,
						l_strUpdateWhereValues,
						l_map);
				}
                // ���u�� U01362�̎b��Ή� end
            }
            m_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
