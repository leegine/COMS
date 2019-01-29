head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o�����폈���ꌏTransactionCallback(WEB3AioSonarCashTransNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/20 ���u��(���{���u) �V�K�쐬
Revesion History : 2006/02/24 �ʉ�(SRA) �{�ԏ�QH00109�Ή�
Revesion History : 2006/7/24 ����(���u) �d�l�ύX���f�� 607
Revesion History : 2007/02/07 ���G��(���u) �d�l�ύX���f�� 696
Revesion History : 2007/02/28 ���� ���I(SCS) �d�l�ύX���f�� 709
Revesion History : 2007/03/19 ���� ���I(SCS) �d�l�ύX���f�� 717
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostCashTransferParams;
import webbroker3.aio.data.HostCashTransferRow;
import webbroker3.aio.service.delegate.WEB3AioCashTransferAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashTransferCompleteUnitService;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3PaymentAutomaticDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;


/**
 * �i������t���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AioSonarCashTransNormalTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSonarCashTransNormalTransactionCallback.class);

    /**
      * ���o���e�[�u��Row�I�u�W�F�N�g�B<BR>
      */
    private HostCashTransferRow hostCashTransferRow;


    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_hostCashTransOrderAcceptParams - (���o���e�[�u��Row)
     */
    public WEB3AioSonarCashTransNormalTransactionCallback(
        HostCashTransferRow l_hostCashTransferRow)
    {
        hostCashTransferRow = l_hostCashTransferRow;
    }

    /**
     * �g�����U�N�V�������������{����B<BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        // SONAR���o��UnitService���擾����B
        WEB3AioSonarCashTransUnitService
            l_aioSonarCashTransUnitService =
                (WEB3AioSonarCashTransUnitService) Services.getService(
                    WEB3AioSonarCashTransUnitService.class);

        // ���o����tUnitService���擾����B
        WEB3AioCashTransferAcceptUnitService
            l_aioCashTransferAcceptUnitService =
                (WEB3AioCashTransferAcceptUnitService) Services.getService(
                    WEB3AioCashTransferAcceptUnitService.class);

        //���o������UnitService���擾����B
        WEB3AioCashTransferCompleteUnitService
            l_aioCashTransferCompleteUnitService =
                (WEB3AioCashTransferCompleteUnitService) Services.getService(
                    WEB3AioCashTransferCompleteUnitService.class);

        //���o�������P��
        AioOrderUnit l_aioOrderUnit = null;

        HostCashTransferRow l_hostCashTransferRow = hostCashTransferRow;
        HostCashTransferParams l_hostCashTransferParams =
            new HostCashTransferParams(l_hostCashTransferRow);

        //���o�����z���擾
        long l_lngAmount = l_hostCashTransferParams.getAmount();
        log.debug("���o�����z���擾:"+ l_lngAmount);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //���o�������}�l�[�W���N���X���擾����B
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        //�g���A�J�E���g�}�l�[�W���擾����
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        try
        {
            //�P.�Q.�P�j���o��Params.���o�����z > 0 and is�d������()�̖߂�l == false �̏ꍇ
            if (l_lngAmount > 0 && !isDuplicateOrder(l_hostCashTransferParams))
            {

                //�P.�Q.�P.1�jsubmit����(���o��Params)
                //�V�K�����̓o�^���s���B
                //[����] ���o��Params�F ���o��Params�I�u�W�F�N�g

                //����ID���擾����
                long l_lngOrderId =
                    l_aioSonarCashTransUnitService.submitOrder(
                        l_hostCashTransferParams);

                //�P.�Q.�P.�Q�jexecute(AioOrderUnit, String, String)
                //���o����tDB�X�V�������s���B
                //[����]
                //�����P�ʁF ����(submit����()�̖߂�l).getOrderUnits()[0] �̖߂�l
                //�G���[�R�[�h�F "0000"�i����j
                //��t�ʒm�敪�F "1"�i��t�����j

                //���o�������P�ʃI�u�W�F�N�g���擾����
                //===================NotFoundException=================
                l_aioOrderUnit =
                    (AioOrderUnit)l_aioOrderManager.getOrderUnits(l_lngOrderId)[0];

                //���o����tDB�X�V�������s��
                l_aioCashTransferAcceptUnitService.execute(
                    l_aioOrderUnit,
                    WEB3ErrorReasonCodeDef.NORMAL,
                    WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE);

                //�P.�Q.�P.�R�jcomplete���o��(AioOrderUnit)
                //���o�����������ɔ��������f�[�^�̍X�V�ƃg�����U�N�V�����f�[�^�̐������s���B
                //[����]
                //�����P�ʁF ����(submit����()�̖߂�l).getOrderUnits()[0] �̖߂�l
                l_aioCashTransferCompleteUnitService.completeCashTransfer(l_aioOrderUnit);
            }

            //�P.�Q.�Q�j���o��Params.���o�����z < 0 �̏ꍇ
            if (l_lngAmount < 0)
            {
                //�P.�Q.�Q.�P�jget�����P��(���o��Params)
                //����Ώۂ̒����P�ʂ��擾����B
                //[����] ���o��Params�F ���o��Params�I�u�W�F�N�g
                l_aioOrderUnit =
                    this.getOrderUnit(l_hostCashTransferParams);

                //�P.�Q.�Q.�Q�jcomplete���o�����(AioOrderUnit)
                //�����̎���������s���B
                //[����] �����P�ʁF get�����P��()�̖߂�l
                l_aioCashTransferCompleteUnitService.completeCashTransferCancel(l_aioOrderUnit);
            }

            //�P.�Q.�R�j InstitutionImpl(InstitutionRow)
            //�،���ЃC���X�^���X�𐶐�����B
            //[����]
            //�،���ЃR�[�h�F ���o���e�[�u��.�،���ЃR�[�h
            //===============NotfoundException===================
            Institution l_institution =
                l_accMgr.getInstitution(
                    l_hostCashTransferParams.getInstitutionCode());

            //�P.�Q.�S)MainAccountImpl(MainAccountRow)
            //�ڋq�C���X�^���X�𐶐�����B
            //[����]
            //�،����ID�F �،����.getInstitutionId()�̖߂�l
            //���X�R�[�h�F ���o���e�[�u��.���X�R�[�h
            //�ڋq�R�[�h�F ���o���e�[�u��.�ڋq�R�[�h
            //===============NotfoundException===================

            MainAccount l_mainAccount =
                l_accMgr.getMainAccount(
                    l_institution.getInstitutionId(),
                    l_hostCashTransferParams.getBranchCode(),
                    l_hostCashTransferParams.getAccountCode());

            //=========remain zhou-yong 2004/12/7 NO.1 begin ===========
            //1.2.5) �]�͍Čv�Z(�⏕���� : �⏕����)
            //�A�C�e���̒�`
            //�]�͂̍X�V������B
            //[����]
            //�⏕�����F�@@�⏕�����I�u�W�F�N�g
			WEB3TPTradingPowerReCalcService l_service =
                (WEB3TPTradingPowerReCalcService) Services.getService(
			WEB3TPTradingPowerReCalcService.class);
            SubAccount l_subAccount =
                l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;

            l_service.reCalcTradingPower(l_gentradeSubAccount);

        }
        catch(WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }
        catch (NotFoundException l_ex)
        {
            ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            l_errorInfo.setErrorClass(l_ex.getClass().getName());
            throw new DataCallbackException(
                l_ex.getMessage(),
                l_errorInfo);
        }

        HashMap l_map = new HashMap();
        //���o���e�[�u���̏����敪�̍X�V�����ݒ�
        String l_strUpdateWhere =  " rowid = ? ";
        //���o���e�[�u���̏����敪�̍X�V�l�ݒ�
        String[] l_updateParams = {l_hostCashTransferRow.getRowid()};

        l_map.put("status", WEB3StatusDef.DEALT);

        //�N�G���v���Z�b�T���擾����B
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

        //�P.�Q.�V)���b�Z�[�W (*2) ���o���e�[�u���̏����敪�̍X�V
        // (*2)���o���e�[�u��.�����敪�Ɉȉ��̒l���Z�b�g���čX�V����B
        l_queryProcessor.doUpdateAllQuery(
            HostCashTransferRow.TYPE,
            l_strUpdateWhere,
            l_updateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }

    /**
     * (get�����P��)<BR>
     * ���o��Params�̓��e�ɍ��v���钍���P�ʂ��擾����B<BR>
     * <BR>
     * �P�j�،���ЃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     *   [�R���X�g���N�^�ɓn������]<BR>
     *   ���o��Params.�،���ЃR�[�h<BR>
     * <BR>
     * �Q�j���X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *   [�R���X�g���N�^�ɓn������]<BR>
     *   �،���ЃI�u�W�F�N�g<BR>
     *   ���o��Params.���X�R�[�h<BR>
     * <BR>
     * �R�j�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *   [�R���X�g���N�^�ɓn������]<BR>
     *   �،����.�،����ID<BR>
     *   ���X.���XID<BR>
     *   ���o��Params.�ڋq�R�[�h<BR>
     * <BR>
     * �S�j�⏕�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *   [�R���X�g���N�^�ɓn������]<BR>
     *   1�i�a��������j<BR>
     * <BR>
     * �T�j�ȉ��̏����ŁA����ΏۂƂȂ钍���P�ʂ���������B<BR>
     * <BR>
     *    [��������]<BR>
     *    ����ID�F ����.����ID<BR>
     *    �⏕����ID�F �⏕����.�⏕����ID<BR>
     *    ���XID�F ���X.���XID<BR>
     *    ������ʁF ���o��Params.�敪='1'�i�o���j�̏ꍇ�A1001<BR>
     *                  ���o��Params.�敪='2'�i�����j�̏ꍇ�A1002<BR>
     *    ������ԁF 6�h�������s�i�V�K�����j�h�A14�h�����ρi��������j�h�ȊO<BR>
     *    ���ʁF ���o��Params.���z * -1<BR>
     *    ���o��Params.�敪='1'�i�o���j�̏ꍇ�A��n���F ���o��Params.���o����<BR>
     *    ���o��Params.�敪='2'�i�����j�̏ꍇ�A�U�֗\����̓��t�F <BR>
     *        ���o��Params.���o�����̑O�c�Ɠ��ȍ~�̓��t<BR>
     *           (TO_CHAR(�U�֗\���,YYYYMMDD)>TO_CHAR(���o��Params.���o�����̑O�c�Ɠ�,YYYYMMDD))�A
     *        ���A<BR>
     *        ���o��Params.���o����(*1)�ȑO�̓��t<BR>
     *           (TO_CHAR(�U�֗\���,YYYYMMDD)��TO_CHAR(���o��Params.���o����(*1),YYYYMMDD))<BR>
     * <BR>
     *    (*1)���o��Params.���o��������c�Ɠ��̏ꍇ�A���c�Ɠ����Z�b�g<BR>
     * <BR>
     * �U�j�擾���������P�ʂ�Ԃ��B<BR>
     *    �������擾���ꂽ�ꍇ�́A�����P��.�󒍓���(received_date_time)�̈�ԌÂ����̂�ԋp����B<BR>
     * <BR>
     * @@param l_hostCashTransferParams - (���o��Params)<BR>
     * ���o��Params�I�u�W�F�N�g<BR>
     * @@return AioOrderUnit
     * @@roseuid 41417583006F
     */
    protected AioOrderUnit getOrderUnit(HostCashTransferParams l_hostCashTransferParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
             "getOrderUnit(HostCashTransferParams l_hostCashTransferParams)";
        log.entering(STR_METHOD_NAME);

        if (l_hostCashTransferParams == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�A�J�E���g�}�l�[�W���擾����
            AccountManager l_accountManager = GtlUtils.getAccountManager();

            //�P�j�،���ЃI�u�W�F�N�g���擾����B
            Institution l_institution =
                l_accountManager.getInstitution(
                    l_hostCashTransferParams.getInstitutionCode());

            //�Q�j���X�I�u�W�F�N�g���擾����B
            Branch l_branch =
                l_accountManager.getBranch(
                    l_institution, l_hostCashTransferParams.getBranchCode());

            //�R�j�ڋq�I�u�W�F�N�g���擾����B
            MainAccount l_mainAccount =
                l_accountManager.getMainAccount(
                    l_institution.getInstitutionId(),
                    l_branch.getBranchId(),
                    l_hostCashTransferParams.getAccountCode());


            //�S�j�⏕�����I�u�W�F�N�g���擾����B
            SubAccount l_subAccount =
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            //�T�j�ȉ��̏����ŁA����ΏۂƂȂ钍���P�ʂ���������B
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" account_id = ?");
            l_strWhere.append(" and sub_account_id = ?");
            l_strWhere.append(" and branch_id = ?");
            l_strWhere.append(" and order_type = ?");
            //������ԁF 6�h�������s�i�V�K�����j�h�A14�h�����ρi��������j�h�ȊO
            l_strWhere.append(" and order_status != ? ");
            l_strWhere.append(" and order_status != ? ");
            l_strWhere.append(" and quantity = ?");
            if (WEB3OrderDivDef.CASHOUT.equals(l_hostCashTransferParams.getOrderDiv()))
            {
                //���o��Params.�敪='1'�i�o���j�̏ꍇ�A��n���F ���o��Params.���o����
                l_strWhere.append(" and delivery_date = ? ");
            }
            else if (WEB3OrderDivDef.CASHIN.equals(l_hostCashTransferParams.getOrderDiv()))
            {
                //���o��Params.�敪='2'�i�����j�̏ꍇ�A�U�֗\����̓��t�F
                //���o��Params.���o�����̑O�c�Ɠ��ȍ~�̓��t
                //(TO_CHAR(�U�֗\���,YYYYMMDD)>TO_CHAR(���o��Params.���o�����̑O�c�Ɠ�,YYYYMMDD))�A���A
                //���o��Params.���o����(*1)�ȑO�̓��t
                //(TO_CHAR(�U�֗\���,YYYYMMDD)��TO_CHAR(���o��Params.���o����(*1),YYYYMMDD))
                l_strWhere.append(" and (TO_CHAR(est_transfer_date,'YYYYMMDD') > TO_CHAR(?,'YYYYMMDD') and TO_CHAR(est_transfer_date,'YYYYMMDD') <= TO_CHAR(?,'YYYYMMDD')) ");
            }

            List l_lisValue = new ArrayList();
            
            // ����ID�F ����.����ID���擾����
            l_lisValue.add(new Long(l_subAccount.getAccountId()));
            //�⏕����ID�F �⏕����.�⏕����ID
            l_lisValue.add(new Long(l_subAccount.getSubAccountId()));
            //���XID�F ���X.���XID
            l_lisValue.add(new Long(l_branch.getBranchId()));
            //������ʁF ���o��Params.�敪='1'�i�o���j�̏ꍇ�A1001�i�o�������j���Z�b�g����B
            if ((WEB3OrderDivDef.CASHOUT).equals((l_hostCashTransferParams.getOrderDiv())))
            {
                l_lisValue.add(OrderTypeEnum.CASH_OUT);
            }
            //������ʁF ���o��Params.�敪='2'�i�����j�̏ꍇ�A1002�i���������j���Z�b�g����B
            else if ((WEB3OrderDivDef.CASHIN).equals((l_hostCashTransferParams.getOrderDiv())))
            {
                l_lisValue.add(OrderTypeEnum.CASH_IN);
            }
            //������ԁF 6�h�������s�i�V�K�����j�h�A14�h�����ρi��������j�h�ȊO
            l_lisValue.add(OrderStatusEnum.NOT_ORDERED);
            l_lisValue.add(OrderStatusEnum.CANCELLED);
            //���ʂ��擾����
            l_lisValue.add(new Long(l_hostCashTransferParams.getAmount() * -1));
            //�����o�����F ���o��Params.���o�������擾����
            Timestamp l_tsDate = l_hostCashTransferParams.getCashTransDate();
            //���o��Params.�敪='1'�i�o���j�̏ꍇ�A��n���F ���o��Params.���o�������擾����
            if (WEB3OrderDivDef.CASHOUT.equals(l_hostCashTransferParams.getOrderDiv()))
            {
                l_lisValue.add(l_tsDate);
            }
            //���o��Params.�敪='2'�i�����j�̏ꍇ�A
            else if (WEB3OrderDivDef.CASHIN.equals(l_hostCashTransferParams.getOrderDiv()))
            {
                //�U�֗\����F ���o��Params.���o�����̑O�c�Ɠ����擾����
                l_lisValue.add(new WEB3GentradeBizDate(l_tsDate).roll(-1));
                //�U�֗\����F ���o��Params.���o����(*1)���擾����
                //(*1)���o��Params.���o��������c�Ɠ��̏ꍇ�A���c�Ɠ����Z�b�g
                String l_strCashTransDate = 
                    WEB3GentradeTradingTimeManagement.getBizDateType(l_tsDate);
                if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strCashTransDate))
                {
                    l_lisValue.add(new WEB3GentradeBizDate(l_tsDate).roll(1));
                }
                else
                {
                    l_lisValue.add(l_tsDate);
                }
            }

            Object[] l_objMutualFrgncalWhere = new Object[l_lisValue.size()];
            l_lisValue.toArray(l_objMutualFrgncalWhere);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //����
            List l_lisAioOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strWhere.toString(),
                    " received_date_time ",
                    null,
                    l_objMutualFrgncalWhere);

            if (l_lisAioOrderUnitRows == null || l_lisAioOrderUnitRows.isEmpty())
            {
                log.debug("Error In �����P�ʂ��������� ");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //�����}�l�[�W���N���X���擾����B
            AioOrderManager l_orderManager =
                (AioOrderManager) GtlUtils.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();

            //�����P�ʃI�u�W�F�N�g���擾����
            AioOrderUnitRow l_orderUnitRow =
               (AioOrderUnitRow) l_lisAioOrderUnitRows.get(0);
            AioOrderUnit l_orderUnit =
                (AioOrderUnit) l_orderManager.toOrderUnit(l_orderUnitRow);

            //�U�j�擾���������P�ʂ�Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return l_orderUnit ;
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataException l_ex)
        {
            log.error("__DataException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (is�d������)<BR>
     * ���o��Params�̓��e�ɍ��v���钍���P�ʂ����邩�ǂ����𔻒肷��B<BR>
     * <BR>
     * �P�j�،���Ѓe�[�u�����A����.���o��Params.�،���ЃR�[�h���L�[�Ƃ��Č������A <BR>
     *�@@�{���ꊇ�����������{="1"�̏ꍇ�Afalse��ԋp����B <BR>
     *    <BR>
     * �Q�j����.���o��Params.���o���敪="2"�i�����jor<BR>
     * �@@����.���o��Params.�f�[�^�R�[�h="FI811"�i�{���ꊇ�j�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �R�j�ȉ��̏����ƈ�v������o��Params����������B<BR>
     * <BR>
     *�@@[��������]<BR>
     *�@@�f�[�^�R�[�h�F "GI811"�i�ʁj<BR>
     *�@@�،���ЃR�[�h�F ����.���o��Params.�،���ЃR�[�h<BR>
     *�@@���X�R�[�h�F ����.���o��Params.���X�R�[�h<BR>
     *�@@�ڋq�R�[�h�F ����.���o��Params.�ڋq�R�[�h<BR>
     *�@@���o���敪�F ���o��Params.���o���敪<BR>
     *�@@���o�����z�F ���o��Params.���o�����z<BR>
     *�@@���o�����F ���o��Params.���o����<BR>
     *�@@�����敪�F "1"�i�����ρj<BR>
     * <BR>
     *�@@��v������o��Params���擾�ł����ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �S�j�،���ЃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     *�@@[�R���X�g���N�^�ɓn������]<BR>
     *�@@����.���o��Params.�،���ЃR�[�h<BR>
     * <BR>
     * �T�j���X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *�@@[�R���X�g���N�^�ɓn������]<BR>
     *�@@�،���ЃI�u�W�F�N�g<BR>
     *�@@����.���o��Params.���X�R�[�h<BR>
     * <BR>
     * �U�j�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *�@@[�R���X�g���N�^�ɓn������]<BR>
     *�@@�،����.�،����ID<BR>
     *�@@���X.���XID<BR>
     *�@@����.���o��Params.�ڋq�R�[�h<BR>
     * <BR>
     * �V�j�⏕�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *�@@[�R���X�g���N�^�ɓn������]<BR>
     *�@@1�i�a��������j<BR>
     * <BR>
     * �W�j�ȉ��̏����ƈ�v���钍���P�ʂ���������B<BR>
     * <BR>
     *�@@[��������]<BR>
     *�@@����ID�F ����.����ID<BR>
     *�@@�⏕����ID�F �⏕����.�⏕����ID<BR>
     *�@@���XID�F ���X.���XID<BR>
     *�@@������ʁF OrderTypeEnum.�o��<BR>
     *�@@������ԁF 1�h��t�ρi�V�K�����j�h�A6�h�������s�i�V�K�����j�h�A14�h�����ρi��������j�h�ȊO <BR>
     *�@@���ʁF ����.���o��Params.���o�����z<BR>
     *�@@��n���F ����.���o��Params.���o����<BR>
     *�@@�����o�H�敪�F 9�h�z�X�g�h�ȊO<BR>
     * �@@�o���\���敪�F null<BR>
     * <BR>
     * �X�j�����ƈ�v���钍���P�ʂ����݂���ꍇ�́Atrue��ԋp����B<BR>
     *�@@���݂��Ȃ��ꍇ�́Afalse��ԋp����B
     * <BR>
     * @@param l_hostCashTransferParams - (���o��Params)<BR>
     * ���o��Params�I�u�W�F�N�g<BR>
     * @@return boolean
     * @@roseuid 41417583006F
     */
    protected boolean isDuplicateOrder(HostCashTransferParams l_hostCashTransferParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
             "isDuplicateOrder(HostCashTransferParams l_hostCashTransferParams)";
        log.entering(STR_METHOD_NAME);

        if (l_hostCashTransferParams == null)
        {
            log.debug("�p�����[�^��null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        boolean l_blnOrderFlag = false;

        //�P�j�،���Ѓe�[�u�����A����.���o��Params.�،���ЃR�[�h���L�[�Ƃ��Č������A
        //�{���ꊇ�����������{="1"�̏ꍇ�Afalse��ԋp����B
        try
        {
        	InstitutionRow l_institutionRow = 
        		InstitutionDao.findRowByInstitutionCode(l_hostCashTransferParams.getInstitutionCode());
        	if (l_institutionRow != null)
        	{
	        	InstitutionParams l_institutionParams = new InstitutionParams(l_institutionRow);
	        	if (WEB3PaymentAutomaticDef.ENFORCEMENT.equals(l_institutionParams.getPaymentAutomatic()))
	        	{
	        		return false;
	        	}
        	}
        }
        catch (DataException l_ex)
        {
            log.error("__DataException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //2�j����.���o��Params.���o���敪="2"�i�����jor
        //    ����.���o��Params.�f�[�^�R�[�h="FI811"�i�{���ꊇ�j�̏ꍇ�Afalse��ԋp����B
        //����L�ȊO�̏ꍇ�̂݌㑱�̏������s���B
        if (WEB3OrderDivDef.CASHOUT.equals(l_hostCashTransferParams.getOrderDiv()) &&
        		!WEB3HostRequestCodeDef.AIO_ALL_HEADQUARTERS.
        			equals(l_hostCashTransferParams.getRequestCode()))
        {
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                //3�j�ȉ��̏����ƈ�v������o��Params����������B
                //   [��������]
                //   �f�[�^�R�[�h�F "GI811"�i�ʁj
                //   �،���ЃR�[�h�F ����.���o��Params.�،���ЃR�[�h
                //   ���X�R�[�h�F ����.���o��Params.���X�R�[�h
                //   �ڋq�R�[�h�F ����.���o��Params.�ڋq�R�[�h
                //   ���o���敪�F ���o��Params.���o���敪
                //   ���o�����z�F ���o��Params.���o�����z
                //   ���o�����F ���o��Params.���o����
                //   �����敪�F "1"�i�����ρj
                StringBuffer l_strHostCashTransWhere = new StringBuffer();
                l_strHostCashTransWhere.append(" request_code = ?");
                l_strHostCashTransWhere.append(" and institution_code = ?");
                l_strHostCashTransWhere.append(" and branch_code = ?");
                l_strHostCashTransWhere.append(" and account_code = ?");
                l_strHostCashTransWhere.append(" and order_div = ? ");
                l_strHostCashTransWhere.append(" and amount = ? ");
                l_strHostCashTransWhere.append(" and cash_trans_date = ? ");
                l_strHostCashTransWhere.append(" and status = ?");

                Object[] l_objHostCashTransWhere =
                {
                   	WEB3HostRequestCodeDef.AIO_INDIVIDUAL,
                   	l_hostCashTransferParams.institution_code,
                   	l_hostCashTransferParams.branch_code,
                   	l_hostCashTransferParams.account_code,
                   	l_hostCashTransferParams.order_div,
                   	l_hostCashTransferParams.amount,
                   	l_hostCashTransferParams.cash_trans_date,
                   	WEB3StatusDef.DEALT
                };

                //����
                List l_lisHostCashTransferRows =
                    l_queryProcessor.doFindAllQuery(
                        HostCashTransferRow.TYPE,
                        l_strHostCashTransWhere.toString(),
                        null,
                        null,
                        l_objHostCashTransWhere);
                
                if (l_lisHostCashTransferRows == null || l_lisHostCashTransferRows.isEmpty())
                {
                    //�A�J�E���g�}�l�[�W���擾����
                    AccountManager l_accountManager = GtlUtils.getAccountManager();

                    //4�j�،���ЃI�u�W�F�N�g���擾����B
                    Institution l_institution =
                        l_accountManager.getInstitution(
                            l_hostCashTransferParams.getInstitutionCode());

                    //5�j���X�I�u�W�F�N�g���擾����B
                    Branch l_branch =
                        l_accountManager.getBranch(
                            l_institution, l_hostCashTransferParams.getBranchCode());

                    //6�j�ڋq�I�u�W�F�N�g���擾����B
                    MainAccount l_mainAccount =
                        l_accountManager.getMainAccount(
                            l_institution.getInstitutionId(),
                            l_branch.getBranchId(),
                            l_hostCashTransferParams.getAccountCode());


                    //7�j�⏕�����I�u�W�F�N�g���擾����B
                    SubAccount l_subAccount =
                        l_mainAccount.getSubAccount(
                            SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

                    //8�j�ȉ��̏����ƈ�v���钍���P�ʂ���������B
                    StringBuffer l_strAioOrderUnitWhere = new StringBuffer();
                    l_strAioOrderUnitWhere.append(" account_id = ?");
                    l_strAioOrderUnitWhere.append(" and sub_account_id = ?");
                    l_strAioOrderUnitWhere.append(" and branch_id = ?");
                    l_strAioOrderUnitWhere.append(" and order_type = ?");
                    //������ԁF 1�h��t�ρi�V�K�����j�h�A6�h�������s�i�V�K�����j�h�A
                    //          14�h�����ρi��������j�h�ȊO
                    l_strAioOrderUnitWhere.append(" and order_status != ? ");
                    l_strAioOrderUnitWhere.append(" and order_status != ? ");
                    l_strAioOrderUnitWhere.append(" and order_status != ? ");
                    l_strAioOrderUnitWhere.append(" and quantity = ?");
                    l_strAioOrderUnitWhere.append(" and delivery_date = ? ");
                    l_strAioOrderUnitWhere.append(" and order_root_div != ? ");
                    l_strAioOrderUnitWhere.append(" and payment_application_div is null ");

                    // ����ID�F ����.����ID���擾����
                    long l_lngAccountId =  l_subAccount.getAccountId();
                    //�⏕����ID�F �⏕����.�⏕����ID
                    long l_lngSubAccountId = l_subAccount.getSubAccountId();
                    //���XID�F ���X.���XID
                    long l_lngBranchId = l_branch.getBranchId();
                    //������ʁF OrderTypeEnum.�o��
                    OrderTypeEnum l_orderType =  OrderTypeEnum.CASH_OUT;
                    //���ʂ��擾����
                    long l_lngQuantity = l_hostCashTransferParams.getAmount();
                    //��n�����擾����F ����.���o��Params.��n��
                    Timestamp l_tsDeliveryDate =
                        l_hostCashTransferParams.getCashTransDate();
                    Object[] l_objAioOrderUnitWhere =
                    {
                        new Long(l_lngAccountId),
                        new Long(l_lngSubAccountId),
                        new Long(l_lngBranchId),
                        l_orderType,
                        OrderStatusEnum.ACCEPTED, 
                        OrderStatusEnum.NOT_ORDERED, 
                        OrderStatusEnum.CANCELLED, 
                        new Long(l_lngQuantity),
                        l_tsDeliveryDate,
                        WEB3OrderRootDivDef.HOST
                    };

                    //����
                    List l_lisAioOrderUnitRows =
                        l_queryProcessor.doFindAllQuery(
                            AioOrderUnitRow.TYPE,
                            l_strAioOrderUnitWhere.toString(),
                            null,
                            null,
                            l_objAioOrderUnitWhere);

                    //9�j�����ƈ�v���钍���P�ʂ����݂���ꍇ�́Atrue��ԋp����B
                    //���݂��Ȃ��ꍇ�́Afalse��ԋp����B
                    if (l_lisAioOrderUnitRows.size() > 0)
                    {
                        log.debug("�d����������");
                        l_blnOrderFlag = true;
                    }
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataException l_ex)
            {
                log.error("__DataException__", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnOrderFlag;
    }
}




@
