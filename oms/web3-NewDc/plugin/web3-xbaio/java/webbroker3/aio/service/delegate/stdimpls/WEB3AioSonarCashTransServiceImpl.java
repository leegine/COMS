head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o���T�[�r�XImpl(WEB3AioSonarCashTransServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���� (���u) �V�K�쐬
                   2004/10/26 ���z (���u) ���r���[
                   2004/12/09 ���E (���u) �c�Ή�
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostCashTransferParams;
import webbroker3.aio.data.HostCashTransferRow;
import webbroker3.aio.message.WEB3AioSonarCashTransResponse;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (SONAR���o���T�[�r�XImpl)<BR>
 * SONAR���o���T�[�r�X�����N���X<BR>
 *
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AioSonarCashTransServiceImpl
    implements WEB3AioSonarCashTransService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransServiceImpl.class);

    /**
     * SONAR���o���T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iSONAR���o���jexecute�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40FF74E2001F
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�P.�P�j SONAR���o���g�����U�N�V�����R�[���o�b�N�C���X�^���X�𐶐�����B
            WEB3AioSonarCashTransTransactionCallback
                l_aioSonarCashTransTransactionCallback =
                    new WEB3AioSonarCashTransTransactionCallback();

            //�P.�Q�j�N�G���v���Z�b�T���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�P.�R�j�@@DB�g�����U�N�V�������������{����B
            //[doTransaction()�Ɏw�肷�����]
            //�g�����U�N�V���������F�@@TX_CREATE_NEW
            //�g�����U�N�V�����R�[���o�b�N�F�@@
            //SONAR���o��TransactionCallback�C���X�^���X
            l_queryProcessor.doTransaction(l_aioSonarCashTransTransactionCallback);

            //�P.�S�j�@@SONAR���o�����N�G�X�g���X�|���X�𐶐����A���^�[������B
            //�|SONAR���o�����N�G�X�g.create���X�|���X()���R�[�����A
            // SONAR���o�����X�I�u�W�F�N�g�𐶐�����B
            WEB3AioSonarCashTransResponse
                l_aioSonarCashTransResponse =
                    (WEB3AioSonarCashTransResponse) l_request.createResponse();

            //�|��������SONAR���o�����X�|���X��Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return l_aioSonarCashTransResponse;
        }
        catch (DataException l_ex)
        {
            log.error("�N�G���v���Z�b�T�̃C���X�^���X���擾���s", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (SONAR���o��TransactionCallback)<BR>
     */
    public class WEB3AioSonarCashTransTransactionCallback
        implements TransactionCallback
    {
        /**
         *  ���O���[�e�B���e�B<BR>
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3AioSonarCashTransTransactionCallback.class);

        /**
         * (SONAR���o��TransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^<BR>
         * @@return WEB3AioSonarCashTransTransactionCallback
         * @@roseuid 40FF759D0177
         */
        public WEB3AioSonarCashTransTransactionCallback()
        {

        }

        /**
         * SONAR���o���������s���B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�iSONAR���o���jprocess�v �Q��<BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 40FF76C3009C
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process() ";
            m_log.entering(STR_METHOD_NAME);

            //�N�G���v���Z�b�T���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�P.�P�j���b�Z�[�W (*1) ���o���e�[�u���Ǎ���
            //(*1) �ȉ��̏����� ���o���e�[�u������
            // �s���b�N�I�v�V�����ɂă��R�[�h���擾����B
            //[��������]
            //�f�[�^�R�[�h = "GI811"�i�ʁj or "FI811"�i�{���ꊇ�j
            //�����敪 = "0"�i�������j
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" (request_code = ?");
            l_strWhere.append(" or request_code = ?)");
            l_strWhere.append(" and status = ? ");
            // ���u�� U01832�̎b��Ή� start
//            String l_strCondition = " for update ";
            String l_strCondition = null;
            // ���u�� U01832�̎b��Ή� end
            Object[] l_objMutualFrgncalWhere =
            {
               WEB3HostRequestCodeDef.AIO_INDIVIDUAL,
               WEB3HostRequestCodeDef.AIO_ALL_HEADQUARTERS ,
               WEB3StatusDef.NOT_DEAL
            };

            /*����*/
            List l_lisHostCashTransferRows =
                l_queryProcessor.doFindAllQuery(
                    HostCashTransferRow.TYPE,
                    l_strWhere.toString(),
                    l_strCondition,
                    l_objMutualFrgncalWhere);

            int l_intSize = 0;
            if (l_lisHostCashTransferRows != null && !l_lisHostCashTransferRows.isEmpty())
            {
                l_intSize = l_lisHostCashTransferRows.size();
            }
            //============================FinApp=========================
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            //���o�������}�l�[�W���N���X���擾����B
            WEB3AioOrderManager l_aioOrderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            //�g���A�J�E���g�}�l�[�W���擾����
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            //�P.�Q�j���b�Z�[�W �擾�������R�[�h����Loop����
            for (int i = 0; i < l_intSize; i++)
            {
                //�������ʂ̓��o���e�[�u���̊e�s�ɑ΂��Ď��{����

                //���o��Params���擾����
                HostCashTransferRow l_hostCashTransferRow =
                       (HostCashTransferRow) l_lisHostCashTransferRows.get(i);
                HostCashTransferParams l_hostCashTransferParams =
                    new HostCashTransferParams(l_hostCashTransferRow);

                // ���u�� U01832�̎b��Ή� start
//                //���o�����z���擾
//                long l_lngAmount = l_hostCashTransferParams.getAmount();
//                m_log.debug("���o�����z���擾:"+ l_lngAmount);
                // ���u�� U01832�̎b��Ή� end

                //==========���o���e�[�u���̏����敪�̍X�V�ݒ�============
                boolean l_blnIsFail = false;
                //�����敪��ݒ�
                HashMap l_map = new HashMap();
                //���o���e�[�u���̏����敪�̍X�V�����ݒ�
                String l_strUpdateWhere =  " rowid = ? ";
                //���o���e�[�u���̏����敪�̍X�V�l�ݒ�
                String[] l_updateParams = {l_hostCashTransferRow.getRowid()};

                //==========================End==========================

                try
                {
                    // ���u�� U01832�̎b��Ή� start
                    WEB3AioSonarCashTransNormalTransactionCallback l_transactionCallback =
                        new WEB3AioSonarCashTransNormalTransactionCallback(
                            l_hostCashTransferRow);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

//                    // SONAR���o��UnitService���擾����B
//                    WEB3AioSonarCashTransUnitService
//                        l_aioSonarCashTransUnitService =
//                            (WEB3AioSonarCashTransUnitService) Services.getService(
//                                WEB3AioSonarCashTransUnitService.class);
//
//                    // ���o����tUnitService���擾����B
//                    WEB3AioCashTransferAcceptUnitService
//                        l_aioCashTransferAcceptUnitService =
//                            (WEB3AioCashTransferAcceptUnitService) Services.getService(
//                                WEB3AioCashTransferAcceptUnitService.class);
//
//                    //���o������UnitService���擾����B
//                    WEB3AioCashTransferCompleteUnitService
//                        l_aioCashTransferCompleteUnitService =
//                            (WEB3AioCashTransferCompleteUnitService) Services.getService(
//                                WEB3AioCashTransferCompleteUnitService.class);
//
//                    //���o�������P��
//                    AioOrderUnit l_aioOrderUnit = null;
//
//                    //�P.�Q.�P�j���o��Params.���o�����z > 0 �̏ꍇ
//                    if (l_lngAmount > 0)
//                    {
//
//                        //�P.�Q.�P.1�jsubmit����(���o��Params)
//                        //�V�K�����̓o�^���s���B
//                        //[����] ���o��Params�F ���o��Params�I�u�W�F�N�g
//
//                        //����ID���擾����
//                        long l_lngOrderId =
//                            l_aioSonarCashTransUnitService.submitOrder(
//                                l_hostCashTransferParams);
//
//                        //�P.�Q.�P.�Q�jexecute(AioOrderUnit, String, String)
//                        //���o����tDB�X�V�������s���B
//                        //[����]
//                        //�����P�ʁF ����(submit����()�̖߂�l).getOrderUnits()[0] �̖߂�l
//                        //�G���[�R�[�h�F "0000"�i����j
//                        //��t�ʒm�敪�F "1"�i��t�����j
//
//                        //���o�������P�ʃI�u�W�F�N�g���擾����
//                        //===================NotFoundException=================
//                        l_aioOrderUnit =
//                            (AioOrderUnit)l_aioOrderManager.getOrderUnits(l_lngOrderId)[0];
//
//                        //���o����tDB�X�V�������s��
//                        l_aioCashTransferAcceptUnitService.execute(
//                            l_aioOrderUnit,
//                            WEB3ErrorReasonCodeDef.NORMAL,
//                            WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE);
//
//                        //�P.�Q.�P.�R�jcomplete���o��(AioOrderUnit)
//                        //���o�����������ɔ��������f�[�^�̍X�V�ƃg�����U�N�V�����f�[�^�̐������s���B
//                        //[����]
//                        //�����P�ʁF ����(submit����()�̖߂�l).getOrderUnits()[0] �̖߂�l
//                        l_aioCashTransferCompleteUnitService.completeCashTransfer(l_aioOrderUnit);
//                    }
//
//                    //�P.�Q.�Q�j���o��Params.���o�����z < 0 �̏ꍇ
//                    if (l_lngAmount < 0)
//                    {
//                        //�P.�Q.�Q.�P�jget�����P��(���o��Params)
//                        //����Ώۂ̒����P�ʂ��擾����B
//                        //[����] ���o��Params�F ���o��Params�I�u�W�F�N�g
//                        l_aioOrderUnit =
//                            this.getOrderUnit(l_hostCashTransferParams);
//
//                        //�P.�Q.�Q.�Q�jcomplete���o�����(AioOrderUnit)
//                        //�����̎���������s���B
//                        //[����] �����P�ʁF get�����P��()�̖߂�l
//                        l_aioCashTransferCompleteUnitService.completeCashTransferCancel(l_aioOrderUnit);
//                    }
//
//                    //�P.�Q.�R�j InstitutionImpl(InstitutionRow)
//                    //�،���ЃC���X�^���X�𐶐�����B
//                    //[����]
//                    //�،���ЃR�[�h�F ���o���e�[�u��.�،���ЃR�[�h
//                    //===============NotfoundException===================
//                    Institution l_institution =
//                        l_accMgr.getInstitution(
//                            l_hostCashTransferParams.getInstitutionCode());
//
//                    //�P.�Q.�S)MainAccountImpl(MainAccountRow)
//                    //�ڋq�C���X�^���X�𐶐�����B
//                    //[����]
//                    //�،����ID�F �،����.getInstitutionId()�̖߂�l
//                    //���X�R�[�h�F ���o���e�[�u��.���X�R�[�h
//                    //�ڋq�R�[�h�F ���o���e�[�u��.�ڋq�R�[�h
//                    //===============NotfoundException===================
//
//                    MainAccount l_mainAccount =
//                        l_accMgr.getMainAccount(
//                            l_institution.getInstitutionId(),
//                            l_hostCashTransferParams.getBranchCode(),
//                            l_hostCashTransferParams.getAccountCode());
//
//                    //=========remain zhou-yong 2004/12/7 NO.1 begin ===========
//                    //1.2.5) �]�͍Čv�Z(�⏕���� : �⏕����)
//                    //�A�C�e���̒�`
//                    //�]�͂̍X�V������B
//                    //[����]
//                    //�⏕�����F�@@�⏕�����I�u�W�F�N�g
//                    WEB3TPTradingPowerService l_service =
//                        (WEB3TPTradingPowerService) Services.getService(
//                            WEB3TPTradingPowerService.class);
//                    SubAccount l_subAccount =
//                        l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//
//                    WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
//
//                    l_service.reCalcTradingPower(l_gentradeSubAccount);
                    // ���u�� U01832�̎b��Ή� end
                    //=========remain zhou-yong 2004/12/7 NO.1 end ===========
                }
                // ���u�� U01832�̎b��Ή� start
//                catch(WEB3BaseException l_ex)
//                {
//                    l_blnIsFail = true;
//                    m_log.debug("__an WEB3BaseException__ ", l_ex);
//                }
//                catch (NotFoundException l_ex)
//                {
//                    l_blnIsFail = true;
//                    m_log.debug("__an NotFoundException__ ", l_ex);
//                }
//                if (l_blnIsFail)
//                {
//                    //"9"�i�G���[�j�F ��L�����iLoop���̏����j�ŗ�O�����������ꍇ
//                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
//                }
//                else
//                {
//                    //�����敪�̍X�V( "1"�i�����ρj�F ����ȊO�̏ꍇ)
//                    l_map.put("status", WEB3StatusDef.DEALT);
//                }
//
//                //�P.�Q.�V)���b�Z�[�W (*2) ���o���e�[�u���̏����敪�̍X�V
//                // (*2)���o���e�[�u��.�����敪�Ɉȉ��̒l���Z�b�g���čX�V����B
//                l_queryProcessor.doUpdateAllQuery(
//                    HostCashTransferRow.TYPE,
//                    l_strUpdateWhere,
//                    l_updateParams,
//                    l_map);

                catch(Exception l_ex)
                {
                    l_blnIsFail = true;
                    m_log.debug("__an Exception__ ", l_ex);
                }
                if (l_blnIsFail)
                {
                    //"9"�i�G���[�j�F ��L�����iLoop���̏����j�ŗ�O�����������ꍇ
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);

                    //�P.�Q.�V)���b�Z�[�W (*2) ���o���e�[�u���̏����敪�̍X�V
                    // (*2)���o���e�[�u��.�����敪�Ɉȉ��̒l���Z�b�g���čX�V����B
                    l_queryProcessor.doUpdateAllQuery(
                        HostCashTransferRow.TYPE,
                        l_strUpdateWhere,
                        l_updateParams,
                        l_map);
                }
                // ���u�� U01832�̎b��Ή� end
            }
            m_log.exiting(STR_METHOD_NAME);
            return null;
          }

        // ���u�� U01832�̎b��Ή� start
//        /**
//         * (get�����P��)<BR>
//         * ���o��Params�̓��e�ɍ��v���钍���P�ʂ��擾����B<BR>
//         * <BR>
//         * �P�j�،���ЃI�u�W�F�N�g���擾����B<BR>
//         * <BR>
//         *   [�R���X�g���N�^�ɓn������]<BR>
//         *   ���o��Params.�،���ЃR�[�h<BR>
//         * <BR>
//         * �Q�j���X�I�u�W�F�N�g���擾����B<BR>
//         * <BR>
//         *   [�R���X�g���N�^�ɓn������]<BR>
//         *   �،���ЃI�u�W�F�N�g<BR>
//         *   ���o��Params.���X�R�[�h<BR>
//         * <BR>
//         * �R�j�ڋq�I�u�W�F�N�g���擾����B<BR>
//         * <BR>
//         *   [�R���X�g���N�^�ɓn������]<BR>
//         *   �،����.�،����ID<BR>
//         *   ���X.���XID<BR>
//         *   ���o��Params.�ڋq�R�[�h<BR>
//         * <BR>
//         * �S�j�⏕�����I�u�W�F�N�g���擾����B<BR>
//         * <BR>
//         *   [�R���X�g���N�^�ɓn������]<BR>
//         *   1�i�a��������j<BR>
//         * <BR>
//         * �T�j�ȉ��̏����ŁA����ΏۂƂȂ钍���P�ʂ���������B<BR>
//         * <BR>
//         *    [��������]<BR>
//         *    ����ID�F ����.����ID<BR>
//         *    �⏕����ID�F �⏕����.�⏕����ID<BR>
//         *    ���XID�F ���X.���XID<BR>
//         *    ������ʁF ���o��Params.�敪='1'�i�o���j�̏ꍇ�A1001<BR>
//         *                  ���o��Params.�敪='2'�i�����j�̏ꍇ�A1002<BR>
//         *    ������ԁF 6�h�������s�i�V�K�����j�h�A14�h�����ρi��������j�h�ȊO <BR>
//         *    ���ʁF ���o��Params.���z * -1<BR>
//         *    ��n���F ���o��Params.��n��<BR>
//         * <BR>
//         * �U�j�擾���������P�ʂ�Ԃ��B<BR>
//         * �������擾���ꂽ�ꍇ�́A�����P��.�󒍓����̈�ԌÂ����̂�ԋp����B
//         * <BR>
//         * @@param l_hostCashTransferParams - (���o��Params)<BR>
//         * ���o��Params�I�u�W�F�N�g<BR>
//         * @@return AioOrderUnit
//         * @@roseuid 41417583006F
//         */
//        protected AioOrderUnit getOrderUnit(HostCashTransferParams l_hostCashTransferParams)
//            throws WEB3BaseException
//        {
//            final String STR_METHOD_NAME =
//                 "getOrderUnit(HostCashTransferParams l_hostCashTransferParams)";
//            m_log.entering(STR_METHOD_NAME);
//
//            if (l_hostCashTransferParams == null)
//            {
//                log.debug("�p�����[�^Null�o���Ȃ��B");
//                throw new WEB3BaseRuntimeException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
//                    this.getClass().getName() + "." + STR_METHOD_NAME);
//            }
//
//            try
//            {
//                //�A�J�E���g�}�l�[�W���擾����
//                AccountManager l_accountManager = GtlUtils.getAccountManager();
//
//                //�P�j�،���ЃI�u�W�F�N�g���擾����B
//                Institution l_institution =
//                    l_accountManager.getInstitution(
//                        l_hostCashTransferParams.getInstitutionCode());
//
//                //�Q�j���X�I�u�W�F�N�g���擾����B
//                Branch l_branch =
//                    l_accountManager.getBranch(
//                        l_institution, l_hostCashTransferParams.getBranchCode());
//
//                //�R�j�ڋq�I�u�W�F�N�g���擾����B
//                MainAccount l_mainAccount =
//                    l_accountManager.getMainAccount(
//                        l_institution.getInstitutionId(),
//                        l_branch.getBranchId(),
//                        l_hostCashTransferParams.getAccountCode());
//
//
//                //�S�j�⏕�����I�u�W�F�N�g���擾����B
//                SubAccount l_subAccount =
//                    l_mainAccount.getSubAccount(
//                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//
//                //�T�j�ȉ��̏����ŁA����ΏۂƂȂ钍���P�ʂ���������B
//                StringBuffer l_strWhere = new StringBuffer();
//                l_strWhere.append(" account_id = ?");
//                l_strWhere.append(" and sub_account_id = ?");
//                l_strWhere.append(" and branch_id = ?");
//                l_strWhere.append(" and order_type = ?");
//                //������ԁF 6�h�������s�i�V�K�����j�h�A14�h�����ρi��������j�h�ȊO
//                l_strWhere.append(" and order_status != " + OrderStatusEnum.NOT_ORDERED.intValue());
//                l_strWhere.append(" and order_status != " + OrderStatusEnum.CANCELLED.intValue());
//                l_strWhere.append(" and quantity = ?");
//                l_strWhere.append(" and delivery_date = ? ");
//
//                // ����ID�F ����.����ID���擾����
//                long l_lngAccountId =  l_subAccount.getAccountId();
//                //�⏕����ID�F �⏕����.�⏕����ID
//                long l_lngSubAccountId = l_subAccount.getSubAccountId();
//                //���XID�F ���X.���XID
//                long l_lngBranchId = l_branch.getBranchId();
//                //������ʂ��擾����
//                OrderTypeEnum l_orderType = null;
//                //������ʁF ���o��Params.�敪='1'�i�o���j�̏ꍇ�A1001�i�o�������j���Z�b�g����B
//                if ((WEB3OrderDivDef.CASHOUT).equals((l_hostCashTransferParams.getOrderDiv())))
//                {
//                    l_orderType =  OrderTypeEnum.CASH_OUT;
//
//                }
//                else
//                {
//                    //���o��Params.���o���敪="2"�i�����j�̏ꍇ�A
//                    //1002�i���������j���Z�b�g����B
//                    if ((WEB3OrderDivDef.CASHIN).equals((l_hostCashTransferParams.getOrderDiv())))
//                    {
//                        l_orderType = OrderTypeEnum.CASH_IN;
//                    }
//                }
//
//                //���ʂ��擾����
//                long l_lngQuantity = l_hostCashTransferParams.getAmount() * -1;
//                //��n�����擾����F ���o��Params.��n��
//                Timestamp l_tsDeliveryDate =
//                    l_hostCashTransferParams.getCashTransDate();
//                Object[] l_objMutualFrgncalWhere =
//                {
//                    new Long(l_lngAccountId),
//                    new Long(l_lngSubAccountId),
//                    new Long(l_lngBranchId),
//                    l_orderType,
//                    new Long(l_lngQuantity),
//                    l_tsDeliveryDate
//                };
//
//                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
//
//                //����
//                List l_lisAioOrderUnitRows =
//                    l_queryProcessor.doFindAllQuery(
//                        AioOrderUnitRow.TYPE,
//                        l_strWhere.toString(),
//                        " delivery_date ",
//                        null,
//                        l_objMutualFrgncalWhere);
//
//                if (l_lisAioOrderUnitRows == null || l_lisAioOrderUnitRows.isEmpty())
//                {
//                    log.debug("Error In �����P�ʂ��������� ");
//                    throw new WEB3SystemLayerException(
//                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                        this.getClass().getName() + "." + STR_METHOD_NAME);
//                }
//
//                //�����}�l�[�W���N���X���擾����B
//                AioOrderManager l_orderManager =
//                    (AioOrderManager) GtlUtils.getTradingModule(
//                        ProductTypeEnum.AIO).getOrderManager();
//
//                //�����P�ʃI�u�W�F�N�g���擾����
//                AioOrderUnitRow l_orderUnitRow =
//                   (AioOrderUnitRow) l_lisAioOrderUnitRows.get(0);
//                AioOrderUnit l_orderUnit =
//                    (AioOrderUnit) l_orderManager.toOrderUnit(l_orderUnitRow);
//
//                //�U�j�擾���������P�ʂ�Ԃ��B
//                m_log.exiting(STR_METHOD_NAME);
//                return l_orderUnit ;
//            }
//            catch (NotFoundException l_ex)
//            {
//                m_log.error("__NotFoundException__", l_ex);
//                m_log.exiting(STR_METHOD_NAME);
//                throw new WEB3SystemLayerException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                    this.getClass().getName() + "." + STR_METHOD_NAME,
//                    l_ex.getMessage(),
//                    l_ex);
//            }
//            catch (DataException l_ex)
//            {
//                m_log.error("__DataException__", l_ex);
//                m_log.exiting(STR_METHOD_NAME);
//                throw new WEB3SystemLayerException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                    this.getClass().getName() + "." + STR_METHOD_NAME,
//                    l_ex.getMessage(),
//                    l_ex);
//            }
//        }
        // ���u�� U01832�̎b��Ή� end
    }
}@
