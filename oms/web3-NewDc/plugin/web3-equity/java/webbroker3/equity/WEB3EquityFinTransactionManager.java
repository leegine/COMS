head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityFinTransactionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���g�����U�N�V�����}�l�[�W���N���X(WEB3EquityFinTransactionManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/29 ������(���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��

*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.AssetTransferFinTransactionImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.CashTransferFinTransactionImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeCashBasedOrderTransactionImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractOpenTransactionImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractSettleTransactionImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractSwapTransactionImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeFinTransactionManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransaction;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CapitalGainStatusDef;

/**
 * �i�g���g�����U�N�V�����}�l�[�W���j�B<BR>
 * <BR>
 * �����ڋq���薾�ׁi�g�����U�N�V�����j�ɑ΂���葱����\������B<BR>
 * xTrade��EqTypeFinTransactionManager���g�������N���X�B
 * @@author ������
 * @@version 1.0
 */
public class WEB3EquityFinTransactionManager extends EqTypeFinTransactionManagerImpl
{

    /**
     * (���O���[�e�B���e�B)
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityFinTransactionManager.class);

    /**
     * �����ŗ^����ꂽEqtypeFinTransactionRow�I�u�W�F�N�g�̃g�����U�N�V�����^�C�v<BR>
     * �ɑΉ������g�����U�N�V�����I�u�W�F�N�g��Ԃ��B<BR>
     * <BR> 
     * @@param EqtypeFinTransactionRow�I�u�W�F�N�g
     * @@param l_r
     * @@return FinTransaction
     * @@roseuid 4042EC5F0259
     */
    protected FinTransaction toFinTransaction(Row l_r)
    {
        EqtypeFinTransactionRow l_row = (EqtypeFinTransactionRow) l_r;
        switch (l_row.getFinTransactionType().intValue())
        {
            case 70 : // 'F'
            case 80 : // 'P'
            case 201 :
            case 202 :
                return new EqTypeCashBasedOrderTransactionImpl(l_row);

            case 90 : // 'Z'
            case 100 : // 'd'
                return new EqTypeContractOpenTransactionImpl(l_row);

            case 110 : // 'n'
            case 120 : // 'x'
                return new EqTypeContractSettleTransactionImpl(l_row);

            case 130 :
            case 140 :
                return new EqTypeContractSwapTransactionImpl(l_row);

            case 1003 :
            case 1004 :
                return new AssetTransferFinTransactionImpl(l_row);

            case 10 : // '\n'
            case 20 : // '\024'
                return new CashTransferFinTransactionImpl(l_row);
        }
        return new WEB3EquityFinTransaction(l_row);
    }

    /**
     * (get�g�����U�N�V����)<BR>
     * �igetTransactions�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �����Ŏw�肵�������P�ʂɊY�����銔���g�����U�N�V������<BR>
     * �擾����B<BR>
     * <BR>
     * �P�j�@@�����ڋq���薾�׃e�[�u������<BR>
     * �@@�ȉ��̏����Ŋ����ڋq���薾�׃e�[�u�����������A<BR>
     *   �擾���ʂ�List�ŕԋp����B <BR>
     * <BR>
     * [��������]<BR>
     * �����P��.����ID<BR>
     * �����P��.�����P��ID<BR>
     * �폜�t���O == �hFALSE�h<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 40BE43F800DA
     */
    public List getTransactions(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransaction(OrderUnit)";

        log.entering(STR_METHOD_NAME);

        // (1)�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����
        List l_lisReturnValue = new ArrayList();

        // (2)���������ǉ�
        // (2-1�j����������������쐬
        String l_strWhere = "order_id = ? and order_unit_id = ? and delete_flag = ? ";
        long l_lngDeleteFlag = 0L;
        // (2-2)������z��𐶐���
        Object[] l_objWhereValue = { new Long(l_orderUnit.getOrderId()), new Long(l_orderUnit.getOrderUnitId()), new Long(l_lngDeleteFlag)};

        // (3)QueryProcessor.doFindAllQuery( )�ɂ��A
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "�����ڋq���薾�׃e�[�u��������  error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "�����ڋq���薾�׃e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        if (l_lisReturnValue.size() == 0 || l_lisReturnValue == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisReturnValue;
    }

    /**
     * (get�g�����U�N�V����)<BR>
     * �igetTransactions�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �w�肵������ID�A�������t�ɊY�����銔���ڋq���薾�ׂ̈ꗗ���擾����B<BR>
     * <BR>
     * �P�j�@@�����ڋq���薾�׃e�[�u������<BR>
     * �ȉ��̏����Ŋ����ڋq���薾�׃e�[�u������������<BR>
     * <BR>
     * [��������]<BR>
     * ����ID������.����ID<BR>
     * �g�����U�N�V������������������.�������t�Ɠ������t<BR>
     * �폜�t���O���hFALSE�h<BR>
     * <BR>
     * �Q�j�@@�P�j�̎擾���ʂ̈ꗗ��ԋp<BR>
     * ���f�[�^�����݂��Ȃ��ꍇ�ɂ�null��ԋp����<BR>
     * @@param l_lngContractId - ����ID
     * @@param l_datTimestamp - (�������t)<BR>
     * YYYYMMDD
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 40EB858103BA
     */
    public List getTransactions(long l_lngContractId, Date l_datTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransactions";

        log.entering(STR_METHOD_NAME);

        // (1)�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����
        List l_lisReturnValue = new ArrayList();

        // (2)���������ǉ�
        // (2-1�j����������������쐬
        String l_strWhere = "contract_id = ? and to_char(fin_transaction_timestamp,'yyyyMMdd')  = ? and delete_flag = ?";
        long l_lngDeleteFlag = 0L;
        // (2-2)������z��𐶐���
        Object[] l_objWhereValue = { new Long(l_lngContractId), WEB3DateUtility.formatDate(l_datTimestamp, "yyyyMMdd"), new Long(l_lngDeleteFlag)};

        // (3)QueryProcessor.doFindAllQuery( )�ɂ��A
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "�����ڋq���薾�׃e�[�u��������  error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "�����ڋq���薾�׃e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        if (l_lisReturnValue.size() == 0 || l_lisReturnValue == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisReturnValue;
    }

    /**
     * (get��n���z���v)<BR>
     * �igetNetAmount�j<BR>
     * <BR>
     * �w�蒍���Ɋ֘A����g�����U�N�V�����f�[�^���A<BR>
     * �����ɑΉ����鍇�v��n���z���Z�o����B<BR>
     * <BR>
     * �P�j�@@�����g�����U�N�V�����e�[�u������<BR>
     * �@@�����Ŏw�肵�������P�ʂɊY�����銔���g�����U�N�V������<BR>
     * get�g�����U�N�V����()�ɂĎ擾����B<BR>
     * <BR>
     * [����]<BR>
     * �����P�ʁF�@@�����̒����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * �Q�j�@@���v���z�v�Z<BR>
     * �@@�擾�������ׂĂ̊����g�����U�N�V�����̎�n������W�v���A<BR>
     * �v�Z���ʂ�ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * 
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40BE44C30251
     */
    public double getNetAmountTotal(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNetAmountTotal(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        double l_dblAmout = 0;
        //get�g�����U�N�V����
        List l_lsttransaction = this.getTransactions(l_orderUnit);
        //���v���z�v�Z
        if (l_lsttransaction != null)
        {
            EqtypeFinTransactionRow l_transactionRow = null;
            int l_intSize = l_lsttransaction.size();
            for (int i = 0; i < l_intSize; i++)
            {
                //��n���z���W�v���A�v�Z����
                l_transactionRow = (EqtypeFinTransactionRow) l_lsttransaction.get(i);
                l_dblAmout = l_dblAmout + l_transactionRow.getNetAmount();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblAmout;
    }

    /**
     * (get�ϑ��萔�����v)<BR>
     * �w�蒍���Ɋ֘A����g�����U�N�V�����f�[�^���A<BR>
     * �����ɑΉ�����ϑ��萔�����v���Z�o����B<BR>
     * <BR>
     * �P�j�@@�����g�����U�N�V�����e�[�u������<BR>
     * �@@�����Ŏw�肵�������P�ʂɊY�����銔���g�����U�N�V������<BR>
     * get�g�����U�N�V����()�ɂĎ擾����B<BR>
     * <BR>
     * [����]<BR>
     * �����P�ʁF�@@�����̒����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * �Q�j�@@���v���z�v�Z<BR>
     * �@@�擾�������ׂĂ̊����g�����U�N�V�����̈ϑ��萔�����W�v���A<BR>
     * �@@�v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)�����P�ʃI�u�W�F�N�g
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40A40EA000BB
     */
    public double getCommTotal(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCommTotal(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�ϑ��萔�����v
        double l_dblCommtatle = 0;
        //get�g�����U�N�V����
        List l_lsttransaction = this.getTransactions(l_orderUnit);
        if (l_lsttransaction != null)
        {
            EqtypeFinTransactionRow l_transactionRow = null;
            int l_strSize = l_lsttransaction.size();
            for (int i = 0; i < l_strSize; i++)
            {
                //�ϑ��萔�����W�v���A�v�Z����
                l_transactionRow = (EqtypeFinTransactionRow) l_lsttransaction.get(i);
                l_dblCommtatle = l_dblCommtatle + l_transactionRow.getCommissionFee();
            }
        }
        log.debug("�ϑ��萔�����W�v���A�v�Z���� l_commtatle = " + l_dblCommtatle);

        log.exiting(STR_METHOD_NAME);
        return l_dblCommtatle;
    }

    /**
     * (get�ϑ��萔������ō��v)<BR>
     * �w�蒍���Ɋ֘A����g�����U�N�V�����f�[�^���A<BR>
     * �����ɑΉ�����ϑ��萔������ō��v���Z�o����B<BR>
     * <BR>
     * �P�j�@@�����g�����U�N�V�����e�[�u������<BR>
     * �@@�����Ŏw�肵�������P�ʂɊY�����銔���g�����U�N�V������<BR>
     * �@@get�g�����U�N�V����()�ɂĎ擾����B<BR>
     * <BR>
     * [����]<BR>
     * �����P�ʁF�@@�����̒����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * �Q�j�@@���v���z�v�Z<BR>
     * �@@�擾�������ׂĂ̊����g�����U�N�V�����̈ϑ��萔������ł��W�v���A<BR>
     * �@@�v�Z���ʂ�ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)�����P�ʃI�u�W�F�N�g
     * 
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40A40EFE029F
     */
    public double getCommConsumptionTaxTotal(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCommConsumptionTaxTotal(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�ϑ��萔������ō��v
        double l_dblCommtatleTax = 0;
        //get�g�����U�N�V����
        List l_lsttransaction = this.getTransactions(l_orderUnit);
        if (l_lsttransaction != null)
        {
            EqtypeFinTransactionRow l_transactionRow = null;
            int l_intSize = l_lsttransaction.size();
            for (int i = 0; i < l_intSize; i++)
            {
                //�ϑ��萔������ł��W�v���A�v�Z����
                l_transactionRow = (EqtypeFinTransactionRow) l_lsttransaction.get(i);
                l_dblCommtatleTax = l_dblCommtatleTax + l_transactionRow.getCommissionFeeTax();
            }
        }
        log.debug("�ϑ��萔������ł��W�v���A�v�Z���� l_commtatleTax = " + l_dblCommtatleTax);

        log.exiting(STR_METHOD_NAME);
        return l_dblCommtatleTax;
    }

    /**
     * (get�T�Z���v(�ڋq���薾��))<BR>
     * <BR>
     * �T�Z���n�v���z�̏W�v���s���B<BR>
     * <BR>
     * �P�j���n���v�̏W�v���s���B<BR>
     * �@@�P�|�P�j����.�ڋq���薾��.���n���v�L����Ԃ����ׂ�"�L��"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@����.�ڋq���薾��.���n�v���z�����ׂĉ��Z���ĕԋp����B<BR>
     * �@@�P�|�Q�j��L�ȊO�̏ꍇ�Anull��ԋp����B <BR>
     * <BR>
     * @@param l_lisTransactions - (List)<BR>
     * �ڋq���薾�ׁB <BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getEstimatedProfitLoss(
        List l_lisTransactions) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEstimatedProfitLoss(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���n���v�̏W�v���s���B
        //�T�Z���v
        String l_strEstimatedProfitLoss = null;
        if(l_lisTransactions != null)
        {
            //�T�Z���n�v���z�̏W�v
            BigDecimal l_bdEstimatedProfitLoss = new BigDecimal(0);

            //�L���A�����𔻒f
            boolean l_blnIsValidity = true;

            for(int i = 0; i < l_lisTransactions.size(); i++)
            {
                EqtypeFinTransactionRow l_eqtypeFinTransaction = (EqtypeFinTransactionRow) l_lisTransactions.get(i);

                //�P�|�P�j����.�ڋq���薾��.���n���v�L����Ԃ����ׂ�"�L��"�̏ꍇ�A
                //�@@�@@�@@�@@����.�ڋq���薾��.���n�v���z�����ׂĉ��Z���ĕԋp����B
                if(WEB3CapitalGainStatusDef.VALIDITY.equals(l_eqtypeFinTransaction.getCapitalGainStatus()))
                {
                    BigDecimal l_bdCapitalGain = new BigDecimal(l_eqtypeFinTransaction.getCapitalGain());
                    l_bdEstimatedProfitLoss = l_bdEstimatedProfitLoss.add(l_bdCapitalGain);
                }
                
                //�P�|�Q�j��L�ȊO�̏ꍇ�Anull��ԋp����B
                else
                {
                    l_blnIsValidity = false;
                    break;
                }
            }

            if(l_blnIsValidity)
            {
                l_strEstimatedProfitLoss = "" + l_bdEstimatedProfitLoss;
            }
        }
        
        log.debug("�y�T�Z���v�z= " + l_strEstimatedProfitLoss);
        log.exiting(STR_METHOD_NAME);
        
        //�T�Z���v��ԋp����B
        return l_strEstimatedProfitLoss;
    }

    /**
     * (get��n���z���v(�ڋq���薾��))<BR>
     * �igetNetAmount�j<BR>
     * <BR>
     * �g�����U�N�V�����f�[�^�ɑΉ����鍇�v��n���z���Z�o����B<BR>
     * <BR>
     * �P�j���v���z�v�Z<BR>
     * �@@�S�Ă̈���.�ڋq���薾�ׂ̎�n������W�v���A�v�Z���ʂ�ԋp����B
     * @@param l_lisTransactions - (List)<BR>
     * �����ڋq���薾��<BR>
     * 
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40BE44C30251
     */
    public double getNetAmountTotal(List l_lisTransactions) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNetAmountTotal(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        double l_dblAmout = 0;

        //���v���z�v�Z
        if (l_lisTransactions != null)
        {
            EqtypeFinTransactionRow l_transactionRow = null;
            int l_intSize = l_lisTransactions.size();
            for (int i = 0; i < l_intSize; i++)
            {
                //��n���z���W�v���A�v�Z����
                l_transactionRow = (EqtypeFinTransactionRow) l_lisTransactions.get(i);
                l_dblAmout = l_dblAmout + l_transactionRow.getNetAmount();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblAmout;
    }

}
@
