head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioBondOnPaymentCooperationServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���A�g�T�[�r�XImpl(WEB3AioBondOnPaymentCooperationServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 ���G�� (���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

import webbroker3.aio.WEB3AioBondOnPaymentInfo;
import webbroker3.aio.message.WEB3AioBondOnPaymentCooperationRequest;
import webbroker3.aio.message.WEB3AioBondOnPaymentCooperationResponse;
import webbroker3.aio.service.delegate.WEB3AioBondOnPaymentCooperationService;
import webbroker3.aio.service.delegate.WEB3AioBondOnPaymentCooperationUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondDivDef;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���o���A�g�T�[�r�XImpl)<BR>
 * ���o���A�g�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioBondOnPaymentCooperationServiceImpl
    implements WEB3AioBondOnPaymentCooperationService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioBondOnPaymentCooperationServiceImpl.class);

    /**
     * ���o���A�g�T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o���A�g�jexecute�v �Q��<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3AioBondOnPaymentCooperationRequest l_bondOnPaymentCooperationRequest = null;
        WEB3AioBondOnPaymentCooperationResponse l_bondOnPaymentCooperationResponse = null;

        if (l_request instanceof WEB3AioBondOnPaymentCooperationRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u ���o���A�g���N�G�X�g �v�̏ꍇ
            l_bondOnPaymentCooperationRequest =
                (WEB3AioBondOnPaymentCooperationRequest)l_request;
        }
        else
        {
            log.debug("Error[���͒l���s���ł�]");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            //1.1.validate( )
            //���N�G�X�g�̃`�F�b�N���s���B
            //�ȈՃ`�F�b�N�݂̂Ƃ���B
            l_bondOnPaymentCooperationRequest.validate();

            //1.2. ���o���A�gTransactionCallback(String)
            //���o���A�g�g�����U�N�V�����R�[���o�b�N�C���X�^���X�𐶐�����B
            //[����]
            //�،���ЃR�[�h�F ����.���N�G�X�g�f�[�^.�،���ЃR�[�h
            WEB3AioBondOnPaymentCooperationTransactionCallback
            l_bondOnPaymentCooperationTransactionCallback =
                new WEB3AioBondOnPaymentCooperationTransactionCallback(
                    l_bondOnPaymentCooperationRequest.institutionCode);

            //1.3.getDefaultProcessor( )
            //�N�G���v���Z�b�T���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //1.4.doTransaction(�g�����U�N�V�������� : int,
            //�g�����U�N�V�����R�[���o�b�N : TransactionCallback)
            //DB�g�����U�N�V�������������{����B
            //[doTransaction()�Ɏw�肷�����]
            //�g�����U�N�V���������F�@@TX_CREATE_NEW
            //�g�����U�N�V�����R�[���o�b�N�F�@@���o���A�gTransactionCallback�C���X�^���X
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_bondOnPaymentCooperationTransactionCallback);

            //1.5.createResponse( )
            l_bondOnPaymentCooperationResponse =
                (WEB3AioBondOnPaymentCooperationResponse)l_request.createResponse();

            //�|�����������o���A�g���X�|���X��Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return l_bondOnPaymentCooperationResponse;
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (���o���A�gTransactionCallback)<BR>
     * ���o���A�g�g�����U�N�V�����R�[���o�b�N�N���X<BR>
     */
    public class WEB3AioBondOnPaymentCooperationTransactionCallback
        implements TransactionCallback
    {
        /**
         * ���O���[�e�B���e�B<BR>
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3AioBondOnPaymentCooperationTransactionCallback.class);

        /**
         * �،���ЃR�[�h
         */
        private String institutionCode;

        /**
         * (���o���A�gTransactionCallback)<BR>
         * �R���X�g���N�^<BR>
         * <BR>
         * �P�j�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B<BR>
         * <BR>
         * �Ethis.�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
         * <BR>
         * @@param l_strInstitutionCode - �،���ЃR�[�h
         */
        public WEB3AioBondOnPaymentCooperationTransactionCallback(
            String l_strInstitutionCode)
        {
            final String STR_METHOD_NAME =
                "WEB3AioBondOnPaymentCooperationTransactionCallback(String)";
            m_log.entering(STR_METHOD_NAME);

            //this.�،���ЃR�[�h = ����.�،���ЃR�[�h
            this.institutionCode = l_strInstitutionCode;

            m_log.exiting(STR_METHOD_NAME);
        }

        /**
         * ���o���A�g�������s���B <BR>
         * <BR>
         * �V�[�P���X�} <BR>
         * �u�i���o���A�g�jprocess�v �Q��<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         */
        public Object process()
            throws DataNetworkException, DataQueryException
        {
            final String STR_METHOD_NAME = "process()";
            m_log.entering(STR_METHOD_NAME);

            Institution l_institution = null;
            //���o���A�gUnitService���擾����B
            WEB3AioBondOnPaymentCooperationUnitService
                l_bondOnPaymentCooperationUnitService =
                    (WEB3AioBondOnPaymentCooperationUnitService)Services.getService(
                        WEB3AioBondOnPaymentCooperationUnitService.class);

            //1.1. getInstitution(�،���ЃR�[�h : String)
            //�،���ЃI�u�W�F�N�g���擾����B
            //[����]
            //�،���ЃR�[�h = this.�،���ЃR�[�h
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            //�A�J�E���g�}�l�[�W���擾����
            AccountManager l_accountManager = l_finApp.getAccountManager();
            try
            {
                //===========================NotFoundException====================
                l_institution = l_accountManager.getInstitution(this.institutionCode);
            }
            catch (NotFoundException l_ex)
            {
                ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
                l_errorInfo.setErrorClass(l_ex.getClass().getName());
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_errorInfo);
            }
            //1.2.getBranches( )
            //�������镔�X�I�u�W�F�N�g��z��Ŏ擾����B
            Branch[] l_branchs = l_institution.getBranches();
            int l_intLength = 0;

            if (l_branchs != null && l_branchs.length != 0)
            {
                l_intLength = l_branchs.length;
                log.debug("Branch�I�u�W�F�N�g�̔z��.length := " + l_intLength);
            }

            //1.3.(*1) �擾�������X�z��̗v�f�����[�v
            log.debug("(*1) �擾�������X�z��̗v�f�����[�v" + l_intLength);
            for (int i = 0; i < l_intLength; i++)
            {
                List l_lisBondOrderUnitRows = null;

                Map l_japaneseOrderMap = null;
                try
                {
                    //1.3.1.is���o���A�g���s(long)
                    //���o���A�g�����s���邩�ǂ����𔻒肷��B
                    //[����]
                    //���XID�F ���X[index].get���XID()
                    log.debug("���XID�F ���X[index].get���XID()" + l_branchs[i].getBranchId());
                    boolean l_blnBondOnPaymentCooperationExecute =
                        this.isBondOnPaymentCooperationExecute(l_branchs[i].getBranchId());

                    //1.3.2.���o���A�g�����s���Ȃ��ꍇ
                    if (!l_blnBondOnPaymentCooperationExecute)
                    {
                        //1.3.2.1.continue
                        log.debug("���o���A�g�����s���Ȃ��ꍇ");
                        continue;
                    }
                    //1.3.3.�~�ݒ���Map�I�u�W�F�N�g�̐���
                    //�ڋq�ʂɉ~�ݒ�����Gross���Ǘ�����HashMap
                    //key�F ����ID
                    //value�F ���o�����
                    l_japaneseOrderMap = new HashMap();

                    //1.3.4.(*2) �������P�ʃe�[�u���Ǎ���
                    //    (*2) �ȉ��̏����ŁA�������P�ʃe�[�u������
                    // �@@�@@�@@�s���b�N�I�v�V�����ɂă��R�[�h���擾����B
                    // [��������]
                    // ���XID = ���X[index].get���XID()
                    // ������� = 402�i�����蒍���j
                    // ��n�� = ���ݓ��t�̗��c�Ɠ�
                    // �������敪 = 1�i���ρj
                    StringBuffer l_sbWhere = new StringBuffer();
                    l_sbWhere.append(" branch_id = ? ");
                    l_sbWhere.append(" and order_type = ? ");
                    l_sbWhere.append(" and to_char(delivery_date, 'YYYYMMDD') = ? ");
                    l_sbWhere.append(" and order_exec_status = ? ");

                    Object[] l_objValues =
                    {
                        new Long(l_branchs[i].getBranchId()),
                        OrderTypeEnum.BOND_SELL,
                        WEB3DateUtility.formatDate(
                            new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(1), "yyyyMMdd"),
                        WEB3BondOrderExecStatusDef.EXECUTED,
                    };
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_lisBondOrderUnitRows =
                        l_queryProcessor.doFindAllQuery(
                            BondOrderUnitRow.TYPE,
                            l_sbWhere.toString(),
                            null,
                            l_objValues);
                }
                catch (Exception l_ex)
                {
                    log.debug("Error in Exception...", l_ex);
                    log.debug("(*1)���[�v���ŗ�O�����������ꍇ");
                    continue;
                }

                //1.3.5.(*3) �擾�������R�[�h�����[�v
                for (Iterator l_iter = l_lisBondOrderUnitRows.iterator(); l_iter.hasNext();)
                {
                    try
                    {
                        log.debug("(*3) �擾�������R�[�h�����[�v");
                        BondOrderUnitRow l_bondOrderUnitRow =
                            (BondOrderUnitRow)l_iter.next();
                        BondOrderUnitParams l_bondOrderUnitParams =
                            new BondOrderUnitParams(l_bondOrderUnitRow);

                        //1.3.5.1.�~�݂̏ꍇ
                        //�������P��Params.���ϋ敪 = "1"�i�~�݁j�̏ꍇ
                        if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(
                            l_bondOrderUnitParams.getSettlementDiv()))
                        {
                            log.debug("�������P��Params.���ϋ敪 = 1�i�~�݁j�̏ꍇ");
                            //1.3.5.1.1.calc�~�ݒ���(Map, �������P��Params)
                            //�ڋq�ʂɉ~�ݒ�����Gross����B
                            //[����]
                            //�~�ݒ���Map�F �~�ݒ���Map�I�u�W�F�N�g
                            //�������P��Params�F �������P��Params�I�u�W�F�N�g
                            this.calcJapaneseOrder(
                                l_japaneseOrderMap,
                                l_bondOrderUnitParams);
                        }
                        //1.3.5.2.�O�݂̏ꍇ
                        //�������P��Params.���ϋ敪 = "2"�i�O�݁j�̏ꍇ
                        if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(
                            l_bondOrderUnitParams.getSettlementDiv()))
                        {
                            log.debug("�������P��Params.���ϋ敪 = 2�i�O�݁j�̏ꍇ");
                            //���o���A�gUnitServiceImpl����
                            WEB3AioBondOnPaymentCooperationUnitServiceImpl
                                l_bondOnPaymentCooperationUnitServiceImpl =
                                    new WEB3AioBondOnPaymentCooperationUnitServiceImpl();

                            //1.3.5.2.1.create�O�ݒ���(�������P��Params)
                            //�O�ݗp�̍��o�����C���X�^���X�𐶐�����B
                            //[����]
                            //�������P��Params�F �������P��Params�I�u�W�F�N�g
                            WEB3AioBondOnPaymentInfo l_bondOnPaymentInfo =
                                l_bondOnPaymentCooperationUnitServiceImpl.createForeignOrder(
                                    l_bondOrderUnitParams);

                            //1.3.5.2.2.submit����(���o�����)
                            //�V�K�����̓o�^���s���B
                            //[����]
                            //���o�����F create�O�ݒ���()�̖߂�l
                            l_bondOnPaymentCooperationUnitService.submitOrder(
                                l_bondOnPaymentInfo);
                        }
                    }
                    catch (Exception l_ex)
                    {
                        log.debug("Error in Exception...", l_ex);
                        log.debug("(*3)���[�v���ŗ�O�����������ꍇ");
                        continue;
                    }
                }
                //1.3.6.�~�ݒ���Map���Ǘ�������o������z��Ŏ擾
                //Map.values().toArray()
                int l_intJapaneseOrderCnt = l_japaneseOrderMap.size();
                log.debug("l_japaneseOrderMap.size() = " + l_intJapaneseOrderCnt);

                WEB3AioBondOnPaymentInfo[] l_bondOnPaymentInfos =
                    new WEB3AioBondOnPaymentInfo[l_intJapaneseOrderCnt];
                l_japaneseOrderMap.values().toArray(l_bondOnPaymentInfos);

                //1.3.7.(*4) �擾�������o�����z��̗v�f�����[�v
                //(*1) (*3) (*4)
                //���[�v���ŗ�O�����������ꍇ
                //���O���o�͂��Acontinue����B
                for (int j = 0; j < l_intJapaneseOrderCnt; j++)
                {
                    try
                    {
                        log.debug("(*4) �擾�������o�����z��̗v�f�����[�v"
                            + l_intJapaneseOrderCnt);
                        //1.3.7.1.submit����(���o�����)
                        //�V�K�����̓o�^���s���B
                        //[����]
                        //���o�����F ���o�����I�u�W�F�N�g
                        l_bondOnPaymentCooperationUnitService.submitOrder(
                            l_bondOnPaymentInfos[j]);
                    }
                    catch (Exception l_ex)
                    {
                        log.debug("Error in Exception...", l_ex);
                        log.debug("(*4)���[�v���ŗ�O�����������ꍇ");
                        continue;
                    }
                }
            }

            m_log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (is���o���A�g���s)<BR>
         * ���o���A�g�����s���邩�ǂ����𔻒肷��B<BR>
         * <BR>
         * [�߂�l]<BR>
         * true�F ���o���A�g�����s����B<BR>
         * false�F ���o���A�g�����s���Ȃ��B <BR>
         * <BR>
         * �P�j�ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR>
         * <BR>
         * [����]  <BR>
         * ���XID = ����.���XID <BR>
         * �v���t�@@�����X�� = "aio.bond.payment.cooperation"  <BR>
         * �v���t�@@�����X���̘A�� = 1  <BR>
         * <BR>
         * �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l = �h1�h �̏ꍇ�Atrue��ԋp����B <BR>
         * <BR>
         * �R�j����ȊO�̏ꍇ�́Afalse��ԋp����B  <BR>
         * �����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB <BR>
         * <BR>
         * @@param l_lngBranchId - ���XID
         * @@return boolean
         * @@throws WEB3BaseException
         */
        private boolean isBondOnPaymentCooperationExecute(long l_lngBranchId)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "isBondOnPaymentCooperationExecute(long l_lngBranchId)";
            m_log.entering(STR_METHOD_NAME);

            // �P�j�ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B
            // [����]
            // ���XID = ����.���XID
            // �v���t�@@�����X�� = "aio.bond.payment.cooperation"
            // �v���t�@@�����X���̘A�� = 1
            BranchPreferencesRow l_branchReferencesRow = null;
            try
            {
                l_branchReferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId,
                    WEB3BranchPreferencesNameDef.AIO_BOND_PAYMENT_COOPERATION,
                    1);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���:", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            // �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l = �h1�h �̏ꍇ�Atrue��ԋp����B
            if (l_branchReferencesRow != null
                && WEB3BondDivDef.ENFORCEMENT.equals(l_branchReferencesRow.getValue()))
            {
                log.debug("�擾�������R�[�h.�v���t�@@�����X�̒l = �h1�h �̏ꍇ");
                log.exiting(STR_METHOD_NAME);
                return true;
            }

            // �R�j����ȊO�̏ꍇ�́Afalse��ԋp����B
            m_log.exiting(STR_METHOD_NAME);
            return false;
        }

        /**
         * (calc�~�ݒ���)<BR>
         * �ڋq�ʂɉ~�ݒ�����Gross����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i���o���A�g�jcalc�~�ݒ����v �Q��<BR>
         * <BR>
         * @@param l_japanexeOrderMap - (�~�ݒ���Map)<BR>
         * �~�ݒ���Map�I�u�W�F�N�g<BR>
         * @@param l_bondOrderUnitParams - (�������P��Params)<BR>
         * �������P��Params�I�u�W�F�N�g<BR>
         */
        private void calcJapaneseOrder(Map l_japanexeOrderMap,
            BondOrderUnitParams l_bondOrderUnitParams)
        {
            final String STR_METHOD_NAME = "calcJapaneseOrder(Map, BondOrderUnitParams)";
            m_log.entering(STR_METHOD_NAME);

            //���o���A�gUnitServiceImpl����
            WEB3AioBondOnPaymentCooperationUnitServiceImpl l_bondOnPaymentCooperationUnitServiceImpl =
                new WEB3AioBondOnPaymentCooperationUnitServiceImpl();

            //1.1.�~�ݒ���Map������o�������擾
            WEB3AioBondOnPaymentInfo l_bondOnPaymentInfo =
                (WEB3AioBondOnPaymentInfo)l_japanexeOrderMap.get(
                    new Long(l_bondOrderUnitParams.getAccountId()));

            //1.2.���o�������擾�ł��Ȃ��ꍇ
            if (l_bondOnPaymentInfo == null)
            {
                log.debug("���o�������擾�ł��Ȃ��ꍇ");
                //1.2.1.create�~�ݒ���(�������P��Params)
                //�~�ݗp�̍��o�����C���X�^���X�𐶐�����B
                //[����]
                //�������P��Params�F ����.�������P��Params�I�u�W�F�N�g
                l_bondOnPaymentInfo =
                    l_bondOnPaymentCooperationUnitServiceImpl.createJapaneseOrder(
                    l_bondOrderUnitParams);

                //1.2.2. �~�ݒ���Map�ɐ����������o������ǉ�
                l_japanexeOrderMap.put(l_bondOnPaymentInfo.getAccountId(),
                    l_bondOnPaymentInfo);
            }
            //1.3.���o�������擾�ł���ꍇ
            else
            {
                log.debug("���o�������擾�ł���ꍇ");
                //1.3.1.calc�~�ݒ���(���o�����, �������P��Params)
                //�~�ݒ�����Gross����B
                //[����]
                //���o�����F �擾�������o�����I�u�W�F�N�g
                //�������P��Params�F ����.�������P��Params�I�u�W�F�N�g
                l_bondOnPaymentCooperationUnitServiceImpl.calcJapaneseOrder(
                    l_bondOnPaymentInfo,
                    l_bondOrderUnitParams);
            }

            m_log.exiting(STR_METHOD_NAME);
        }
    }
}
@
