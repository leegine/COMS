head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransForeignNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o���i�O�݁j���폈���ꌏTransactionCallback(WEB3AioSonarCashTransForeignNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 ���G�� (���u) �V�K�쐬
Revesion History : 2007/03/01 ���G�� (���u) �����̖��009
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
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
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostForeignCashTransferParams;
import webbroker3.aio.data.HostForeignCashTransferRow;
import webbroker3.aio.service.delegate.WEB3AioCashTransferAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashTransferCompleteUnitService;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransForeignUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3OrderDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (SONAR���o���i�O�݁j���폈���ꌏTransactionCallback)<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignNormalTransactionCallback 
    implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransForeignNormalTransactionCallback.class);

    /**
      * �O�ݓ��o���e�[�u��Row�I�u�W�F�N�g�B<BR>
      */
    private HostForeignCashTransferRow hostForeignCashTransferRow;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_hostForeignCashTransferRow - (�O�ݓ��o���e�[�u��Row)
     */
    public WEB3AioSonarCashTransForeignNormalTransactionCallback(
        HostForeignCashTransferRow l_hostForeignCashTransferRow)
    {
        hostForeignCashTransferRow = l_hostForeignCashTransferRow;
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

        // SONAR���o���i�O�݁jUnitService���擾����B
        WEB3AioSonarCashTransForeignUnitService
            l_aioSonarCashTransForeignUnitService =
                (WEB3AioSonarCashTransForeignUnitService)Services.getService(
                    WEB3AioSonarCashTransForeignUnitService.class);

        // ���o����tUnitService���擾����B
        WEB3AioCashTransferAcceptUnitService
            l_aioCashTransferAcceptUnitService =
                (WEB3AioCashTransferAcceptUnitService)Services.getService(
                    WEB3AioCashTransferAcceptUnitService.class);

        //���o������UnitService���擾����B
        WEB3AioCashTransferCompleteUnitService
            l_aioCashTransferCompleteUnitService =
                (WEB3AioCashTransferCompleteUnitService)Services.getService(
                    WEB3AioCashTransferCompleteUnitService.class);

        //�O�ݓ��o�������P��
        AioOrderUnit l_aioOrderUnit = null;

        HostForeignCashTransferRow l_hostForeignCashTransferRow = hostForeignCashTransferRow;
        HostForeignCashTransferParams l_hostForeignCashTransferParams =
            new HostForeignCashTransferParams(l_hostForeignCashTransferRow);

        //���o�����z���擾
        double l_dblAmount = l_hostForeignCashTransferParams.getAmount();
        log.debug("�O�ݓ��o�����z���擾:"+ l_dblAmount);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        //���o�������}�l�[�W���N���X���擾����B
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();

        try
        {
            //�P.�Q.�P�j�O�ݓ��o��Params.�U�֓��o���z > 0�̏ꍇ
            if (l_dblAmount > 0)
            {
                //�P.�Q.�P.1�jsubmit����(�O�ݓ��o��Params)
                //�V�K�����̓o�^���s���B
                //[����] �O�ݓ��o��Params�F �O�ݓ��o��Params�I�u�W�F�N�g

                //����ID���擾����
                long l_lngOrderId =
                    l_aioSonarCashTransForeignUnitService.submitOrder(
                        l_hostForeignCashTransferParams);

                //�P.�Q.�P.�Q�jexecute(AioOrderUnit, String, String)
                //���o����tDB�X�V�������s���B
                //[����]
                //�����P�ʁF ����(submit����()�̖߂�l).getOrderUnits()[0] �̖߂�l
                //�G���[�R�[�h�F "0000"�i����j
                //��t�ʒm�敪�F "1"�i��t�����j

                //�O�ݓ��o�������P�ʃI�u�W�F�N�g���擾����
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

            //�P.�Q.�Q�j�O�ݓ��o��Params.�U�֓��o���z < 0 �̏ꍇ
            if (l_dblAmount < 0)
            {
                //�P.�Q.�Q.�P�jget�����P��(�O�ݓ��o��Params)
                //����Ώۂ̒����P�ʂ��擾����B
                //[����] �O�ݓ��o��Params�F �O�ݓ��o��Params�I�u�W�F�N�g
                l_aioOrderUnit =
                    this.getOrderUnit(l_hostForeignCashTransferParams);

                //�P.�Q.�Q.�Q�jcomplete���o�����(AioOrderUnit)
                //�����̎���������s���B
                //[����] �����P�ʁF get�����P��()�̖߂�l
                l_aioCashTransferCompleteUnitService.completeCashTransferCancel(l_aioOrderUnit);
            }
        }
        catch(WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }

        HashMap l_map = new HashMap();
        
        //�O�ݓ��o���e�[�u���̏����敪�̍X�V�����ݒ�
        String l_strUpdateWhere = " rowid = ?";
        
        //�O�ݓ��o���e�[�u���̏����敪�̍X�V�l�ݒ�
        String[] l_strUpdateParams = {l_hostForeignCashTransferRow.getRowid()};
        l_map.put("status", WEB3StatusDef.DEALT);

        //�N�G���v���Z�b�T���擾����B
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

        //�P.�Q.�R)(*2) �O�ݓ��o���e�[�u���̏����敪�̍X�V
        // (*2)�O�ݓ��o���e�[�u��.�����敪�Ɉȉ��̒l���Z�b�g���čX�V����B
        l_queryProcessor.doUpdateAllQuery(
            HostForeignCashTransferRow.TYPE,
            l_strUpdateWhere,
            l_strUpdateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get�����P��)<BR>
     * �O�ݓ��o��Params�̓��e�ɍ��v���钍���P�ʂ��擾����B<BR> 
     * <BR>
     * �P�j�،���ЃI�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �@@[�R���X�g���N�^�ɓn������] <BR>
     * �@@�O�ݓ��o��Params.�،���ЃR�[�h <BR>
     * <BR>
     * �Q�j���X�I�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �@@[�R���X�g���N�^�ɓn������] <BR>
     * �@@�،���ЃI�u�W�F�N�g <BR>
     * �@@�O�ݓ��o��Params.���X�R�[�h <BR>
     * <BR>
     * �R�j�ڋq�I�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �@@[�R���X�g���N�^�ɓn������]<BR> 
     * �@@�،����.�،����ID <BR>
     * �@@���X.���XID <BR>
     * �@@�O�ݓ��o��Params.�ڋq�R�[�h<BR> 
     * <BR>
     * �S�j�⏕�����I�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �@@[�R���X�g���N�^�ɓn������] <BR>
     * �@@1�i�a��������j <BR>
     * <BR>
     * �T�j�ȉ��̏����ŁA����ΏۂƂȂ钍���P�ʂ���������B<BR> 
     * <BR>
     * �@@�@@[��������] <BR>
     * �@@�@@����ID�F ����.����ID <BR>
     * �@@�@@�⏕����ID�F �⏕����.�⏕����ID <BR>
     * �@@�@@���XID�F ���X.���XID <BR>
     * �@@�@@������ʁF �O�ݓ��o��Params.���o���敪='1'�i�o���j�̏ꍇ�A1001�h�o�������h<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�O�ݓ��o��Params.���o���敪='2'�i�����j�̏ꍇ�A1002�h���������h<BR> 
     * �@@�@@������ԁF 6�h�������s�i�V�K�����j�h�A14�h�����ρi��������j�h�ȊO <BR>
     * �@@�@@�������ʁF �O�ݓ��o��Params.�U�֓��o���z * -1 <BR>
     * �@@�@@��n���F �O�ݓ��o��Params.��n�� <BR>
     * �@@�@@�ʉ݃R�[�h�F �O�ݓ��o��Params.�ʉ݃R�[�h <BR>
     * <BR>
     * �U�j�擾���������P�ʂ�Ԃ��B <BR>
     * �@@�@@�������擾���ꂽ�ꍇ�́A�����P��.�󒍓����̈�ԌÂ����̂�ԋp����B<BR>
     * <BR>
     * @@param l_hostCashTransferForeignParams - (�O�ݓ��o��Params)<BR>
     * �O�ݓ��o��Params�I�u�W�F�N�g
     * <BR>
     * @@return AioOrderUnit
     * @@throws WEB3BaseException
     */
    protected AioOrderUnit getOrderUnit(
        HostForeignCashTransferParams l_hostForeignCashTransferParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderUnit(HostCashTransferForeignParams)";
        log.entering(STR_METHOD_NAME);

        if (l_hostForeignCashTransferParams == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
       
        try
        {
            // �O�ݓ��o��Params�̓��e�ɍ��v���钍���P�ʂ��擾����B 
            // �P�j�،���ЃI�u�W�F�N�g���擾����B 
            //   [�R���X�g���N�^�ɓn������] 
            //   �O�ݓ��o��Params.�،���ЃR�[�h 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            String l_strInstitutionCode = 
                l_hostForeignCashTransferParams.getInstitutionCode();
            Institution l_institution = 
                l_accountManager.getInstitution(l_strInstitutionCode);
            
            // �Q�j���X�I�u�W�F�N�g���擾����B 
            //   [�R���X�g���N�^�ɓn������] 
            //   �،���ЃI�u�W�F�N�g 
            //   �O�ݓ��o��Params.���X�R�[�h 
            Branch l_branch =
                l_accountManager.getBranch(
                    l_institution, l_hostForeignCashTransferParams.getBranchCode());
            
            // �R�j�ڋq�I�u�W�F�N�g���擾����B 
            //   [�R���X�g���N�^�ɓn������] 
            //   �،����.�،����ID 
            //   ���X.���XID 
            //   �O�ݓ��o��Params.�ڋq�R�[�h 
            MainAccount l_mainAccount =
                l_accountManager.getMainAccount(
                    l_institution.getInstitutionId(),
                    l_branch.getBranchId(),
                    l_hostForeignCashTransferParams.getAccountCode());
            
            // �S�j�⏕�����I�u�W�F�N�g���擾����B 
            //   [�R���X�g���N�^�ɓn������] 
            //   1�i�a��������j 
            SubAccount l_subAccount =
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            // �T�j�ȉ��̏����ŁA����ΏۂƂȂ钍���P�ʂ���������B 
            //    [��������] 
            StringBuffer l_sbWhere = new StringBuffer();
            
            // ����ID
            l_sbWhere.append(" account_id = ? ");
            
            //�⏕����ID
            l_sbWhere.append(" and sub_account_id = ? ");
            
            //���XID
            l_sbWhere.append(" and branch_id = ? ");
            
            //�������
            l_sbWhere.append(" and order_type = ? ");
            
            //�������
            l_sbWhere.append(" and order_status != ? ");
            
            //�������
            l_sbWhere.append(" and order_status != ? ");
            
            //��������
            l_sbWhere.append(" and quantity = ? ");
            
            //��n��
            l_sbWhere.append(" and delivery_date = ? ");
            
            //�ʉ݃R�[�h
            l_sbWhere.append(" and currency_code = ? ");

            // ����ID�F ����.����ID���擾����
            long l_lngAccountId =  l_mainAccount.getAccountId();
            
            //�⏕����ID�F �⏕����.�⏕����ID
            long l_lngSubAccountId = l_subAccount.getSubAccountId();
            
            //���XID�F ���X.���XID
            long l_lngBranchId = l_branch.getBranchId();
            
            //    ������ʁF �O�ݓ��o��Params.���o���敪='1'�i�o���j�̏ꍇ�A1001�h�o�������h 
            //                  �O�ݓ��o��Params.���o���敪='2'�i�����j�̏ꍇ�A1002�h���������h 
            OrderTypeEnum l_orderType = null;
            if (WEB3OrderDivDef.CASHOUT.equals(l_hostForeignCashTransferParams.getOrderDiv()))
            {
                l_orderType = OrderTypeEnum.CASH_OUT;
            }
            else if (WEB3OrderDivDef.CASHIN.equals(l_hostForeignCashTransferParams.getOrderDiv()))
            {
                l_orderType = OrderTypeEnum.CASH_IN;
            }
            
            //    �������ʁF �O�ݓ��o��Params.�U�֓��o���z * -1 
            double l_dblQuantity = l_hostForeignCashTransferParams.getAmount() * -1;
            
            //    ��n���F �O�ݓ��o��Params.��n�� 
            Timestamp l_tsDeliveryDate =
                l_hostForeignCashTransferParams.getCashTransDate();
            
            //    �ʉ݃R�[�h�F �O�ݓ��o��Params.�ʉ݃R�[�h 
            String l_strCurrencyCode = l_hostForeignCashTransferParams.getCurrencyCode();
            
            Object[] l_objValues =
            {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                new Long(l_lngBranchId),
                l_orderType,
                OrderStatusEnum.NOT_ORDERED, 
                OrderStatusEnum.CANCELLED, 
                new Double(l_dblQuantity),
                l_tsDeliveryDate,
                l_strCurrencyCode
            };

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //����
            List l_lisAioOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    "received_date_time asc",
                    null,
                    l_objValues);

            if (l_lisAioOrderUnitRows == null || l_lisAioOrderUnitRows.isEmpty())
            {
                log.debug("Error In �����P�ʂ��������� ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //�����}�l�[�W���N���X���擾����B
            AioOrderManager l_orderManager =
                (AioOrderManager)GtlUtils.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();

            //�����P�ʃI�u�W�F�N�g���擾����
            AioOrderUnitRow l_orderUnitRow =
               (AioOrderUnitRow)l_lisAioOrderUnitRows.get(0);
            AioOrderUnit l_orderUnit =
                (AioOrderUnit)l_orderManager.toOrderUnit(l_orderUnitRow);

            //* �U�j�擾���������P�ʂ�Ԃ��B 
            //*    �������擾���ꂽ�ꍇ�́A�����P��.�󒍓����̈�ԌÂ����̂�ԋp����B
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
}
@
