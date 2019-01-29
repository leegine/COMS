head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqFinTransactionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������g�����U�N�V�����}�l�[�W��(WEB3FeqFinTransactionManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ������(���u) �V�K�쐬
                 : 2005/07/26 ���U(���u) ���r���[
Revesion History : 2007/11/20 �����q(���u) �d�l�ύX ���f��No.365
*/
package webbroker3.feq;

import java.math.BigDecimal;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqFinTransactionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������g�����U�N�V�����}�l�[�W��) <BR>
 * �O�������g�����U�N�V�����}�l�[�W��<BR>
 * <BR>
 * @@ author ������ <BR>
 * @@ version 1.0<BR>
 */
public class WEB3FeqFinTransactionManager extends FeqFinTransactionManagerImpl 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FeqFinTransactionManager.class);
    
    /**
     * @@roseuid 42CE39E703B9
     */
    public WEB3FeqFinTransactionManager() 
    {
     
    }
    
    /**
     * (get��n������v) <BR>
     * �igetNetAmount�j  <BR>
     * �w�蒍���Ɋ֘A����g�����U�N�V�����f�[�^���A <BR>
     * �����ɑΉ����鍇�v��n���z���Z�o����B  <BR>
     *  <BR>
     * �P�j�@@�g�����U�N�V�����i������薾�ׁj�e�[�u������  <BR>
     * �@@�����Ŏw�肵�������P�ʂɊY������O���g�����U�N�V������ <BR>
     *   get�g�����U�N�V����()�ɂĎ擾����B  <BR>
     *  <BR>
     * [����]  <BR>
     * �����P�ʁF�@@�����̒����P�ʃI�u�W�F�N�g  <BR>
     *  <BR>
     * �Q�j�@@���v���z�v�Z  <BR>
     * �@@�擾�������ׂĂ̊����g�����U�N�V�����̎�n������W�v���A <BR>
     * �v�Z���ʂ�ԋp����B  <BR>
     * @@param l_feqOrderUnit - (�����P��)<BR>
     * �����P��
     * @@return Double
     * @@throws WEB3BaseException 
     * @@roseuid 4292AFE90302
     */
    public double getNetAmount(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getNetAmount(FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�g�����U�N�V�����i������薾�ׁj�e�[�u������
        //�����Ŏw�肵�������P�ʂɊY������O���g�����U�N�V������ 
        //get�g�����U�N�V����()�ɂĎ擾����B
        List l_lstTransaction = this.getTransactions(l_feqOrderUnit);
        double  l_dblNetAmount = 0.0D;
        if (l_lstTransaction != null && !l_lstTransaction.isEmpty())
        {
            for (int i = 0; i < l_lstTransaction.size(); i++)
            {
                FeqFinTransactionRow l_transactionRow = (FeqFinTransactionRow)l_lstTransaction.get(i);
                //�Q�j�@@���v���z�v�Z 
                l_dblNetAmount += l_transactionRow.getNetAmount();
            }
            
        }
        log.exiting(STR_METHOD_NAME);
        //�v�Z���ʂ�ԋp����B
        return l_dblNetAmount;
    }
    
    /**
     * (get��n������v�i�O�݁j) <BR>
     * �igetNetAmountFc�j  <BR>
     * �w�蒍���Ɋ֘A����g�����U�N�V�����f�[�^���A <BR>
     * �����ɑΉ����鍇�v��n���z�i�O�݁j���Z�o����B  <BR>
     *  <BR>
     * �P�j�@@�g�����U�N�V�����i������薾�ׁj�e�[�u������  <BR>
     * �@@�����Ŏw�肵�������P�ʂɊY������O���g�����U�N�V������ <BR>
     * �@@get�g�����U�N�V����()�ɂĎ擾����B  <BR>
     *  <BR>
     * [����]  <BR>
     * �����P�ʁF�@@�����̒����P�ʃI�u�W�F�N�g  <BR>
     *  <BR>
     * �Q�j�@@���v���z�v�Z  <BR>
     * �@@�擾�������ׂĂ̊����g�����U�N�V�����̎�n����i�O�݁j���W�v���A <BR>
     * �@@�v�Z���ʂ�ԋp����B  <BR>
     * @@param l_feqOrderUnit - (�����P��)<BR>
     * �����P��
     * @@return Double
     * @@throws WEB3BaseException 
     * @@roseuid 4292BA3702A4
     */
    public double getNetAmountFc(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getNetAmountFc(FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        //�P�j�g�����U�N�V�����i������薾�ׁj�e�[�u������  
        //�����Ŏw�肵�������P�ʂɊY������O���g�����U�N�V������ 
        //get�g�����U�N�V����()�ɂĎ擾����B
        List l_lstTransaction = this.getTransactions(l_feqOrderUnit);
        BigDecimal l_bdNetAmountFc = new BigDecimal("0");
        if (l_lstTransaction != null && !l_lstTransaction.isEmpty())
        {
            for (int i = 0; i < l_lstTransaction.size(); i++)
            {
                FeqFinTransactionRow l_transactionRow = (FeqFinTransactionRow)l_lstTransaction.get(i);
                //�Q�j�@@���v���z�v�Z 
                l_bdNetAmountFc =
                    l_bdNetAmountFc.add(new BigDecimal(String.valueOf(l_transactionRow.getNetAmountFc())));
            }
        }
        log.exiting(STR_METHOD_NAME);
        //�v�Z���ʂ�ԋp����B
        return l_bdNetAmountFc.doubleValue();
    }
    
    /**
     * (get�g�����U�N�V����) <BR>
     * �igetTransactions�̃I�[�o�[���[�h�j  <BR>
     *  <BR>
     * �����Ŏw�肵�������P�ʂɊY������g�����U�N�V�������擾����B  <BR>
     *  <BR>
     * �P�j�@@�g�����U�N�V�����e�[�u������  <BR>
     * �@@�ȉ��̏����ŊO�������g�����U�N�V�����i������薾�ׁj���������A <BR>
     * �@@�擾���ʂ��W���ł���B<BR>
     *  <BR>
     * �@@[��������]  <BR>
     * �@@����ID = �����P��.����ID  <BR>
     * �@@�����P��ID = �����P��.�����P��ID  <BR>
     * �@@�폜�t���O = FALSE  <BR>
     * @@param l_feqOrderUnit - (�����P��) <BR>
     * �����P��
     * @@return List
     * @@throws WEB3BaseException 
     * @@roseuid 4292B03A01C9
     */
    public List getTransactions(FeqOrderUnit l_feqOrderUnit)throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getTransactions(FeqOrderUnit)";    
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        List l_lstreturnTransaction = null;
        
        //�f�[�^���m
        QueryProcessor processor = null;
        try
        {
            processor = Processors.getDefaultProcessor();
            //�P�j�@@�g�����U�N�V�����e�[�u������
            String l_strWhere = " order_id = ? and order_unit_id = ? and delete_flag = ? ";
            Object[] l_strBindValue = new Object[3];
            l_strBindValue[0] = new Long(l_feqOrderUnit.getOrderId());
            l_strBindValue[1] = new Long(l_feqOrderUnit.getOrderUnitId());
            l_strBindValue[2] = BooleanEnum.FALSE;
                        
            l_lstreturnTransaction = processor.doFindAllQuery(FeqFinTransactionRow.TYPE,
                l_strWhere, l_strBindValue);

        } 
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch (DataNetworkException l_ex)        
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }        
        log.exiting(STR_METHOD_NAME);        
        return l_lstreturnTransaction;
    }
    
    /**
     * (get�g�����U�N�V����) <BR>
     * �igetTransaction�̃I�[�o�[���[�h�j  <BR>
     *  <BR>
     * �����Ŏw�肵�����ɊY������g�����U�N�V�������擾����B  <BR>
     *  <BR>
     * �P�j�@@�g�����U�N�V�����e�[�u������  <BR>
     * �@@�ȉ��̏����ŊO�������g�����U�N�V�����i������薾�ׁj���������A <BR>
     * �@@�擾�s��ԋp����B  <BR>
     *  <BR>
     * �@@[��������]  <BR>
     * �@@����ID = ���.����ID  <BR>
     * �@@�����P��ID = ���.�����P��ID  <BR>
     * �@@���ID = ���.���ID  <BR>
     *  <BR>
     * �@@�Y���s���Ȃ��ꍇ�A<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01037<BR>
     * �@@�Y���s���Q���ȏ゠��ꍇ��O���X���[����B <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01461<BR>
     * @@param l_feqOrderExecution - (���)<BR>
     * ���I�u�W�F�N�g
     * @@return FeqFinTransactionParams
     * @@throws WEB3BaseException 
     * @@roseuid 4294367703A2
     */
    public FeqFinTransactionParams getTransaction(FeqOrderExecution l_feqOrderExecution) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getTransactions(FeqOrderExecution)";    
        log.entering(STR_METHOD_NAME);

        if (l_feqOrderExecution == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        List l_lstTransaction = null;
        
        //�f�[�^���m
        QueryProcessor processor = null;
        try
        {
            processor = Processors.getDefaultProcessor();
            //�P�j�@@�g�����U�N�V�����e�[�u������
            String l_strWhere = " order_id = ? and order_unit_id = ? and order_execution_id = ? ";
            FeqOrderExecutionRow l_executionRow = 
                (FeqOrderExecutionRow)l_feqOrderExecution.getDataSourceObject();
            Object[] l_strBindValue = new Object[3];
            l_strBindValue[0] = new Long(l_executionRow.getOrderId());
            l_strBindValue[1] = new Long(l_executionRow.getOrderUnitId());
            l_strBindValue[2] = new Long(l_executionRow.getOrderExecutionId());

                        
            l_lstTransaction = processor.doFindAllQuery(FeqFinTransactionRow.TYPE,
                l_strWhere, l_strBindValue);

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
        catch (DataNetworkException l_ex)        
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }        
        
        if (l_lstTransaction == null || l_lstTransaction.isEmpty())
        {
            String l_strMessage = "�����ɊY������f�[�^�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMessage);
        }
        if (l_lstTransaction.size() > 1)
        {
            String l_strMessage = "�e�[�u���ɏd������Y���f�[�^�����݂��܂��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01461,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMessage);
        }
        log.exiting(STR_METHOD_NAME);        
        return new FeqFinTransactionParams((FeqFinTransactionRow)l_lstTransaction.get(0));
    }

    /**
     * (get�K�p�בփ��[�g)<BR>
     * �����Ŏw�肵�������P�ʂɊY������g�����U�N�V����.�K�p�בփ��[�g���擾����B<BR>
     * �@@���������擾�ł���ꍇ�́A�g�����U�N�V����List(0)�̈בփ��[�g���擾����B<BR>
     * <BR>
     * �P�j�@@�g�����U�N�V�����e�[�u������<BR>
     * �@@�ȉ��̏����ŊO�������g�����U�N�V�����i������薾�ׁj���������A�擾���ʂ�<BR>
     * �@@�W���Ŏ擾����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@����ID = �����P��.����ID<BR>
     * �@@�����P��ID = �����P��.�����P��ID<BR>
     * <BR>
     * �@@���@@�g�����U�N�V�������擾�ł��Ȃ��ꍇ�́A�V�X�e���G���[<BR>
     * �@@�h�Y���f�[�^�Ȃ��h��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�K�p�בփ��[�g�擾<BR>
     * �@@�g�����U�N�V����List(0)�D�K�p�בփ��[�g��ԋp����B<BR>
     * <BR>
     * @@param l_feqOrderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return Double
     * @@throws WEB3BaseException
     */
    public Double getFxRate(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "getFxRate(FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_feqOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �P�j�@@�g�����U�N�V�����e�[�u������
        List l_lisTransactions = null;
        try
        {
            QueryProcessor processor = Processors.getDefaultProcessor();
            // [��������]
            // ����ID = �����P��.����ID
            // �����P��ID = �����P��.�����P��ID
            String l_strWhere = " order_id = ? and order_unit_id = ? ";
            Object[] l_bindValues = new Object[2];
            l_bindValues[0] = new Long(l_feqOrderUnit.getOrderId());
            l_bindValues[1] = new Long(l_feqOrderUnit.getOrderUnitId());

            l_lisTransactions = processor.doFindAllQuery(
                FeqFinTransactionRow.TYPE,
                l_strWhere,
                l_bindValues);
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
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �g�����U�N�V�������擾�ł��Ȃ��ꍇ�́A�V�X�e���G���[�h�Y���f�[�^�Ȃ��h��ԋp����B
        if (l_lisTransactions.isEmpty())
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }

        // �Q�j�@@�K�p�בփ��[�g�擾
        // �@@�g�����U�N�V����List(0)�D�K�p�בփ��[�g��ԋp����B
        FeqFinTransactionRow l_feqFinTransactionRow =
            (FeqFinTransactionRow)l_lisTransactions.get(0);
        double l_dblFxRate = l_feqFinTransactionRow.getFxRate();

        log.exiting(STR_METHOD_NAME);
        return new Double(l_dblFxRate);
    }
}
@
