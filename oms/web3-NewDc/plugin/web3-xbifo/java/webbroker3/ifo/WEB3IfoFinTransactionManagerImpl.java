head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoFinTransactionManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�g�����U�N�V�����}�l�[�W��(WEB3IfoTransactionManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 �����g(���u) �V�K�쐬
              001: 2004/07/23  ���Ō� (���u)  getTransactions���C��
Revesion History : 2008/08/18 ���z(���u) IFO�����_�Ή�
*/

package webbroker3.ifo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoFinTransactionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�g�����U�N�V�����}�l�[�W��)<BR>
 * �敨OP�g�����U�N�V�����}�l�[�W���N���X<BR>
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3IfoFinTransactionManagerImpl extends IfoFinTransactionManagerImpl 
{
    
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoFinTransactionManagerImpl.class);

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * @@roseuid 40C0750B01E4
     */
    public WEB3IfoFinTransactionManagerImpl() 
    {
        super();
        
        // --- Test Log
        log.debug("------- >>>>>>>> WEB3IfoPositionManagerImpl Has Intializtion Successed !!!");
    }
    
    /**
     * (get�g�����U�N�V����)<BR>
     * <BR>
     * �igetTransactions�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �����Ŏw�肵�������P�ʂɊY������敨OP�g�����U�N�V�������擾����B<BR>
     * <BR>
     * �P�j�@@�����g�����U�N�V�����e�[�u������<BR>
     * �@@�ȉ��̏����Ő敨OP�g�����U�N�V�����i�������j�e�[�u�����������A<BR>
     * �擾���ʂ��W���ŕԋp����B<BR>
     * <BR>
     * [��������]<BR>
     * �����P��.����ID<BR>
     * �����P��.�����P��ID<BR>
     * �폜�t���O = FALSE
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * 
     * @@return List
     * @@roseuid 40A2DDCE0226
     */
    public List getTransactions(OrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransactions";    
        log.entering(STR_METHOD_NAME);
        
        
        // --- Test Log
        log.debug("Input Parm: �����P�ʃI�u�W�F�N�g l_orderUnit = " + l_orderUnit);

        List l_returnList = null;
        
        //�f�[�^���m
        QueryProcessor processor = null;
        try
        {
            processor = Processors.getDefaultProcessor();
            
            String l_strWhere = " order_id = ? and order_unit_id = ? and delete_flag = ? ";
            Object[] l_strBindValue = new Object[3];
            l_strBindValue[0] = new Long(l_orderUnit.getOrderId()).toString();
            l_strBindValue[1] = new Long(l_orderUnit.getOrderUnitId()).toString();
            l_strBindValue[2] = BooleanEnum.FALSE;
                        
            l_returnList = processor.doFindAllQuery(IfoFinTransactionRow.TYPE,
                l_strWhere, l_strBindValue);

        } 
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "�敨OP�g�����U�N�V�����i�������j�e�[�u�������� error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch (DataNetworkException l_ex)        
        {
            String l_strMessage = " �敨OP�g�����U�N�V�����i�������j�e�[�u�������� error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }        
        log.exiting(STR_METHOD_NAME);
        
        return l_returnList;
    }
    
    /**
     * (get�g�����U�N�V����)<BR>
     * <BR>
     * �igetTransactions�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �w�肵������ID�A�g�����U�N�V�����J�e�S���A�������t�ɊY������g�����U�N�V����(��E
     * ���薾��)�̈ꗗ���擾����B<BR>
     * <BR>
     * (1)�g�����U�N�V�����e�[�u������<BR>
     * �ȉ��̏����Ńg�����U�N�V�����e�[�u������������<BR>
     * <BR>
     * [��������]<BR>
     * ����ID = �p�����[�^.����ID<BR>
     * �g�����U�N�V�����J�e�S�� = �p�����[�^.�g�����U�N�V�����J�e�S��<BR>
     * �g�����U�N�V������������ = �p�����[�^.�������t�Ɠ������t<BR>
     * �폜�t���O = FALSE 
     * <BR>
     * (2)(1)�̎擾���ʂ̈ꗗ��ԋp<BR>
     * ���f�[�^�����݂��Ȃ��ꍇ�ɂ�NULL��ԋp��<BR>
     * @@param l_lngContractID - ����ID<BR>
     * @@param l_intTransactionCategory - �g�����U�N�V�����J�e�S��<BR>
     * <BR>
     * 0�F���̑�<BR>
     * 10�F���o��<BR>
     * 20�F�������<BR>
     * 30�F�V�K�����<BR>
     * 40�F�ԍώ��<BR>
     * 60�F�����E���n���<BR>
     * 70�F����p�֘A<BR>
     * 80�F���o��<BR>
     * @@param l_datOccurDate - (�������t)<BR>
     * <BR>
     * YYYYMMDD<BR>
     * @@return List
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40AAB60B0292
     */
    public List getTransactions(long l_lngContractID, 
        FinTransactionCateg l_transactionCategory, Date l_datOccurDate) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransactions";
        log.entering(STR_METHOD_NAME);
        
        // --- Test Log
        log.debug("Input Parm: ����ID l_lngContractID = " + l_lngContractID);
        log.debug("Input Parm: �g�����U�N�V�����J�e�S�� l_intTransactionCategory = " + l_transactionCategory);
        log.debug("Input Parm: �������t l_datOccurDate = " + l_datOccurDate);

        List l_returnList = null;
        
        //�f�[�^���m
        QueryProcessor processor = null;
        try
        {
            processor = Processors.getDefaultProcessor();
                        
            String l_strWhere = " contract_id = ? and fin_transaction_categ = ?"
                + " and to_char(fin_transaction_timestamp,'yyyyMMdd') = ? and delete_flag = ?";
                            
            SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strOccurDate = l_format.format(l_datOccurDate);
            
            Object[] l_objBindValue = new Object[4];
            l_objBindValue[0] = "" + l_lngContractID;
            l_objBindValue[1] =  l_transactionCategory;
            l_objBindValue[2] = l_strOccurDate;
            l_objBindValue[3] = BooleanEnum.FALSE;
                        
            l_returnList = processor.doFindAllQuery(
                IfoFinTransactionRow.TYPE,
                l_strWhere, 
                l_objBindValue);
               
        }
        catch(DataQueryException l_ex)
        {
            String l_strMessage = "�g�����U�N�V�����e�[�u�������� error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch(DataNetworkException l_ex)        
        {
            String l_strMessage = "�g�����U�N�V�����e�[�u�������� error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }        

        log.exiting(STR_METHOD_NAME);
        return l_returnList;
    }
    
    /**
     * (getNetAmount)<BR>
     * (get��n���z���v)<BR>
     * �w�蒍���Ɋ֘A����g�����U�N�V�����f�[�^���A�����ɑΉ����鍇�v��n���z���Z�o����B<BR>
     * <BR>
     * �P�j�@@�����g�����U�N�V�����e�[�u������<BR>
     * �@@�����Ŏw�肵�������P�ʂɊY�����銔���g�����U�N�V������get�g�����U�N�V����()�ɂĎ擾����B<BR>
     * <BR>
     * [����]<BR>
     * �����P�ʁF�@@�����̒����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * �Q�j�@@���v���z�v�Z<BR>
     * �@@�擾�������ׂĂ̊����g�����U�N�V�����̎�n������W�v���A�v�Z���ʂ�ԋp����B<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * 
     * @@return double
     * @@roseuid 40A2DDCE0254
     */
    public double getNetAmount(OrderUnit l_orderUnit) 
    {
        final String STR_METHOD_NAME = "getNetAmount";
        log.entering(STR_METHOD_NAME);
        
        double l_dblAmout = 0;
        BigDecimal l_bdAmout = new BigDecimal("0");
        //get�g�����U�N�V����

        List l_lisTransactions = null;
        try
        {
            
            l_lisTransactions = this.getTransactions(l_orderUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("Error In get�g�����U�N�V����: ", l_ex);
        }
        //���v���z�v�Z               
        
        if (l_lisTransactions != null) 
        {
            IfoFinTransactionRow l_transactionRow = null;
            int size = l_lisTransactions.size();

            for (int i = 0; i < size; i++)
            {
                //��n���z���W�v���A�v�Z����
                l_transactionRow = (IfoFinTransactionRow)l_lisTransactions.get(i);

                BigDecimal l_bdNetAmout = new BigDecimal(l_transactionRow.getNetAmount() + "");
                l_bdAmout = l_bdAmout.add(l_bdNetAmout);
                l_dblAmout = l_bdAmout.doubleValue();
            }
        } 
        log.debug("------ >>>>> ���v���z�v�Z = " + l_dblAmout);

        log.exiting(STR_METHOD_NAME);
        
        return l_dblAmout;
    }
    
    /**
     *�igetTransactions�̃I�[�o�[���[�h�j<BR>
     *   �w�肵�������P��ID�A����ID�ɊY������g�����U�N�V����(������薾��)�̈ꗗ���擾����B<BR>
     *(1)�g�����U�N�V�����e�[�u������<BR>
     *�ȉ��̏����Ńg�����U�N�V�����e�[�u������������<BR>
     *  [��������]<BR>
     *  �����P��ID = �p�����[�^.�����P��ID<BR>
     *  ����ID = �p�����[�^.����ID<BR>
     *  �폜�t���O = FALSE<BR>
     *(2)(1)�̎擾���ʂ̈ꗗ��ԋp����B<BR>
     * @@param l_orderUnitId
     * @@param l_contractId
     * @@return
     * @@throws WEB3BaseException
     */
    public List getTransactions(long l_orderUnitId ,long l_contractId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransactions";    
        log.entering(STR_METHOD_NAME);
               
        List l_returnList = null;
        
        //�f�[�^���m
        QueryProcessor processor = null;
        try
        {
            processor = Processors.getDefaultProcessor();

            String l_strWhere = " order_unit_id = ? and contract_id = ? and delete_flag = ? ";
            Object[] l_strBindValue = new Object[3];
            l_strBindValue[0] = new Long(l_orderUnitId);
            l_strBindValue[1] = new Long(l_contractId);
            l_strBindValue[2] = BooleanEnum.FALSE;            
            l_returnList = processor.doFindAllQuery(IfoFinTransactionRow.TYPE,
                l_strWhere, l_strBindValue);
        } 
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "�敨OP�g�����U�N�V�����i�������j�e�[�u�������� error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);            
        } 
        catch (DataNetworkException l_ex)        
        {
            String l_strMessage = " �敨OP�g�����U�N�V�����i�������j�e�[�u�������� error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);           
        }
        
        log.debug(" �敨OP�g�����U�N�V�����i�������j�e�[�u�������� l_returnList = " + l_returnList);
        log.exiting(STR_METHOD_NAME);
        
        return l_returnList;
    }
}
@
