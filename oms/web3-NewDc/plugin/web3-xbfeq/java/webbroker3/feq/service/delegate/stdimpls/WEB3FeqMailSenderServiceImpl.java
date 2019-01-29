head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqMailSenderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������[�����M�T�[�r�XImpl(WEB3FeqMailSenderServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  䈋�(���u) �V�K�쐬
                   2005/07/26 �����F(���u) ���r���[
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
*/
package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3OrderConditionOperatorDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.define.WEB3FeqItemContentsDef;
import webbroker3.feq.define.WEB3FeqItemNameDef;
import webbroker3.feq.service.delegate.WEB3FeqMailSenderService;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.ExtMailProcParams;
import webbroker3.gentrade.data.ExtMailProcTempParams;
import webbroker3.gentrade.data.ExtMailProcTempRow;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.gentrade.data.MailProcTempParams;
import webbroker3.gentrade.data.MailProcTempRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O���������[�����M�T�[�r�XImpl) <BR>
 * �O���������[�����M�T�[�r�X�����N���X
 * @@author 䈋�
 * @@version 1.0 
 */
public class WEB3FeqMailSenderServiceImpl implements WEB3FeqMailSenderService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqMailSenderServiceImpl.class);
  
    /**
     * @@roseuid 42CE39F5007D
     */
    public WEB3FeqMailSenderServiceImpl() 
    {
     
    }
    
    /**
     * (create�V�K����Mail) <BR>
     * �V�K�����̓��e���A���[�����M�e�[�u���A <BR>
     * ���[�����M�g���e�[�u���ɓo�^����B <BR>
     *  <BR>
     * �P�j�ȉ��̏����ŁA���[���e�[�u�����烌�R�[�h���擾����B <BR>
     *  <BR>
     *    [����] <BR>
     *    �،���ЃR�[�h = ����.�����P��.�،���ЃR�[�h <BR>
     *    ���M���[���敪 = "1001" <BR>
     *  <BR>
     * �Q�j���[�����M�i�e���|�����j�e�[�u���A <BR>
     * ���[�����M�g���i�e���|�����j�e�[�u���ɓo�^����B <BR>
     *  <BR>
     * �Q�|�P�j������ԊǗ�.is�s��J�ǎ��ԑ�()�̖߂�l == true �̏ꍇ <BR>
     *  <BR>
     *    this.createMail()���R�[������B <BR>
     *  <BR>
     *    [����] <BR>
     *    ���[�����F �P�j�Ŏ擾�������[���e�[�u���s�̔z�� <BR>
     *    �����P�ʁF ����.�����P�� <BR>
     *  <BR>
     * �Q�|�Q�j������ԊǗ�.is�s��J�ǎ��ԑ�()�̖߂�l == false �̏ꍇ <BR>
     *  <BR>
     *    this.createTempMail()���R�[������B <BR>
     *  <BR>
     *    [����] <BR>
     *    ���[�����F �P�j�Ŏ擾�������[���e�[�u���s�̔z�� <BR>
     *    �����P�ʁF ����.�����P�� <BR>
     * @@param l_feqOrderUnit - (�����P��)
     * @@throws WEB3BaseException
     * @@roseuid 4295F452005D
     */
    public void createNewOrderMail(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createNewOrderMail(FeqOrderUnit l_feqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        // �P�j�ȉ��̏����ŁA���[���e�[�u�����烌�R�[�h���擾����B 
        //  
        //    [����] 
        //    �،���ЃR�[�h = ����.�����P��.�،���ЃR�[�h 
        //    ���M���[���敪 = "1001" 
        //  
        List l_lisMailInfo = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "institution_code = ? and sendmail_div = ?";
            Object[] l_objWhereValue = new Object[2];
            FeqOrderUnitRow l_feqOrderUnitRow = 
                (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
            l_objWhereValue[0] = l_feqOrderUnitRow.getInstitutionCode();
            l_objWhereValue[1] = WEB3SendmailDivDef.FEQ_ORDER_ACCEPT;
            
            l_lisMailInfo = 
                l_processor.doFindAllQuery(MailInfoRow.TYPE, l_strWhere, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch(DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        // �Q�j���[�����M�i�e���|�����j�e�[�u���A 
        // ���[�����M�g���i�e���|�����j�e�[�u���ɓo�^����B 
        //  
        // �Q�|�P�j������ԊǗ�.is�s��J�ǎ��ԑ�()�̖߂�l == true �̏ꍇ 
        //  
        //    this.createMail()���R�[������B 
        //  
        //    [����] 
        //    ���[�����F �P�j�Ŏ擾�������[���e�[�u���s�̔z�� 
        //    �����P�ʁF ����.�����P�� 
        // 

        if ((l_lisMailInfo != null) && (l_lisMailInfo.size() != 0))
        {
            MailInfoRow[] l_mailInfoRows = new MailInfoRow[l_lisMailInfo.size()];
            l_lisMailInfo.toArray(l_mailInfoRows);
            if (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
            {
                this.createMail(l_mailInfoRows, (WEB3FeqOrderUnit)l_feqOrderUnit);
            }
            // �Q�|�Q�j������ԊǗ�.is�s��J�ǎ��ԑ�()�̖߂�l == false �̏ꍇ 
            //  
            //    this.createTempMail()���R�[������B 
            //  
            //    [����] 
            //    ���[�����F �P�j�Ŏ擾�������[���e�[�u���s�̔z�� 
            //    �����P�ʁF ����.�����P�� 
            else
            {
                this.createTempMail(l_mailInfoRows, (WEB3FeqOrderUnit)l_feqOrderUnit);
            }
            log.exiting(STR_METHOD_NAME);
        }
        else
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (create��������Mail) <BR>
     * ���������̓��e���A���[�����M�e�[�u���A <BR>
     * ���[�����M�g���e�[�u���ɓo�^����B <BR>
     *  <BR>
     * �P�j������ԊǗ�.is�s��J�ǎ��ԑ�()�̖߂�l == false �̏ꍇ <BR>
     *  <BR>
     *    �������̃��[�����M�e�[�u���A <BR>
     *    ���[�����M�g���e�[�u���̃f�[�^��������� <BR>
     *    �ɂ��킹�čX�V����B <BR>
     *  <BR>
     *    this.updateTempMail()���R�[������B <BR>
     *  <BR>
     *    [����] <BR>
     *    �����P�ʁF ����.�����P�� <BR>
     *  <BR>
     * �Q�j������ԊǗ�.is�s��J�ǎ��ԑ�()�̖߂�l == true �̏ꍇ <BR>
     *  <BR>
     * �Q�|�P�j�ȉ��̏����ŁA���[���e�[�u�����烌�R�[�h���擾����B <BR>
     *  <BR>
     *    [����] <BR>
     *    �،���ЃR�[�h = ����.�����P��.�،���ЃR�[�h <BR>
     *    ���M���[���敪 = "1002" <BR>
     *  <BR>
     * �Q�|�Q�j���[�����M�e�[�u���A���[�����M�g���e�[�u���ɓo�^����B <BR>
     *  <BR>
     *    this.createMail()���R�[������B <BR>
     *  <BR>
     *    [����] <BR>
     *    ���[�����F �P�j�Ŏ擾�������[���e�[�u���s�̔z�� <BR>
     *    �����P�ʁF ����.�����P�� <BR>
     * @@param l_feqOrderUnit - (�����P��)
     * @@throws WEB3BaseException
     * @@roseuid 429995B700B5
     */
    public void createChangeOrderMail(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createChangeOrderMail(FeqOrderUnit l_feqOrderUnit)";
        log.entering(STR_METHOD_NAME);

        try
        {
            // �P�j������ԊǗ�.is�s��J�ǎ��ԑ�()�̖߂�l == false �̏ꍇ 
            //  
            //    �������̃��[�����M�e�[�u���A 
            //    ���[�����M�g���e�[�u���̃f�[�^��������� 
            //    �ɂ��킹�čX�V����B 
            //  
            //    this.updateTempMail()���R�[������B 
            //  
            //    [����] 
            //    �����P�ʁF ����.�����P�� 
            //  
            if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
            {
                this.updateTempMail((WEB3FeqOrderUnit)l_feqOrderUnit);
            }
            // �Q�j������ԊǗ�.is�s��J�ǎ��ԑ�()�̖߂�l == true �̏ꍇ 
            //  
            // �Q�|�P�j�ȉ��̏����ŁA���[���e�[�u�����烌�R�[�h���擾����B 
            //  
            //    [����] 
            //    �،���ЃR�[�h = ����.�����P��.�،���ЃR�[�h 
            //    ���M���[���敪 = "1002" 
            //  
            // �Q�|�Q�j���[�����M�e�[�u���A���[�����M�g���e�[�u���ɓo�^����B 
            //  
            //    this.createMail()���R�[������B 
            //  
            //    [����] 
            //    ���[�����F �P�j�Ŏ擾�������[���e�[�u���s�̔z�� 
            //    �����P�ʁF ����.�����P�� 
            else
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                String l_strWhere = "institution_code = ? and sendmail_div = ?";
                Object[] l_objWhereValue = new Object[2];
                FeqOrderUnitRow l_feqOrderUnitRow = 
                    (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
                l_objWhereValue[0] = l_feqOrderUnitRow.getInstitutionCode();
                l_objWhereValue[1] = WEB3SendmailDivDef.FEQ_ORDER_CHANGE;
                
                List l_lisMailInfo = 
                    l_processor.doFindAllQuery(MailInfoRow.TYPE, l_strWhere, l_objWhereValue);
                if (l_lisMailInfo != null && !l_lisMailInfo.isEmpty())
                {
                    MailInfoRow[] l_mailInfoRows = new MailInfoRow[l_lisMailInfo.size()];
                    l_lisMailInfo.toArray(l_mailInfoRows);
                    this.createMail(l_mailInfoRows, (WEB3FeqOrderUnit)l_feqOrderUnit);
                }
                else
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            log.exiting(STR_METHOD_NAME);

        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch(DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * (create�������Mail) <BR>
     * ��������̓��e���A���[�����M�e�[�u���A <BR>
     * ���[�����M�g���e�[�u���ɓo�^����B <BR>
     *  <BR>
     * �P�j������ԊǗ�.is�s��J�ǎ��ԑ�()�̖߂�l == false �̏ꍇ <BR>
     *  <BR>
     *    �������̃��[�����M�e���|�����e�[�u���A <BR>
     *    ���[�����M�g���e���|�����e�[�u���̃f�[�^��_���폜����B <BR>
     *  <BR>
     *    this.deleteTempMail()���R�[������B <BR>
     *  <BR>
     *    [����] <BR>
     *    �����P�ʁF ����.�����P�� <BR>
     *  <BR>
     * �Q�j������ԊǗ�.is�s��J�ǎ��ԑ�()�̖߂�l == true �̏ꍇ <BR>
     *  <BR>
     * �Q�|�P�j�ȉ��̏����ŁA���[���e�[�u�����烌�R�[�h���擾����B <BR>
     *  <BR>
     *    [����] <BR>
     *    �،���ЃR�[�h = ����.�����P��.�،���ЃR�[�h <BR>
     *    ���M���[���敪 = "1003" <BR>
     *  <BR>
     * �Q�|�Q�j���[�����M�e�[�u���A���[�����M�g���e�[�u���ɓo�^����B <BR>
     *  <BR>
     *    this.createMail()���R�[������B <BR>
     *  <BR>
     *    [����] <BR>
     *    ���[�����F �P�j�Ŏ擾�������[���e�[�u���s�̔z�� <BR>
     *    �����P�ʁF ����.�����P�� <BR>
     * @@param l_feqOrderUnit - (�����P��)
     * @@throws WEB3BaseException
     * @@roseuid 429995B700C5
     */
    public void createCancelOrderMail(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCancelOrderMail(FeqOrderUnit l_feqOrderUnit) ";
        log.entering(STR_METHOD_NAME);
        // �P�j������ԊǗ�.is�s��J�ǎ��ԑ�()�̖߂�l == false �̏ꍇ 
        //  
        //    �������̃��[�����M�e���|�����e�[�u���A 
        //    ���[�����M�g���e���|�����e�[�u���̃f�[�^��_���폜����B 
        //  
        //    this.deleteTempMail()���R�[������B 
        //  
        //    [����] 
        //    �����P�ʁF ����.�����P�� 
        //  
        try
        {
            if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
            {
                this.deleteTempMail((WEB3FeqOrderUnit)l_feqOrderUnit);
            }
            else
            {
                // �Q�j������ԊǗ�.is�s��J�ǎ��ԑ�()�̖߂�l == true �̏ꍇ 
                //  
                // �Q�|�P�j�ȉ��̏����ŁA���[���e�[�u�����烌�R�[�h���擾����B 
                //  
                //    [����] 
                //    �،���ЃR�[�h = ����.�����P��.�،���ЃR�[�h 
                //    ���M���[���敪 = "1003" 
                //  
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                String l_strWhere = "institution_code = ? and sendmail_div = ?";
                Object[] l_objWhereValue = new Object[2];
                FeqOrderUnitRow l_feqOrderUnitRow = 
                    (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
                l_objWhereValue[0] = l_feqOrderUnitRow.getInstitutionCode();
                l_objWhereValue[1] = WEB3SendmailDivDef.FEQ_ORDER_CANCEL;
                
                List l_lisMailInfo = 
                    l_processor.doFindAllQuery(MailInfoRow.TYPE, l_strWhere, l_objWhereValue);
                
                // �Q�|�Q�j���[�����M�e�[�u���A���[�����M�g���e�[�u���ɓo�^����B 
                //  
                //    this.createMail()���R�[������B 
                //  
                //    [����] 
                //    ���[�����F �P�j�Ŏ擾�������[���e�[�u���s�̔z�� 
                //    �����P�ʁF ����.�����P�� 
                if (l_lisMailInfo != null && !l_lisMailInfo.isEmpty())
                {
                    MailInfoRow[] l_mailInfoRows = new MailInfoRow[l_lisMailInfo.size()];
                    l_lisMailInfo.toArray(l_mailInfoRows);
                    this.createMail(l_mailInfoRows, (WEB3FeqOrderUnit)l_feqOrderUnit);
                }   
                else
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createMail) <BR>
     * ���[�����M�e�[�u���A���[�����M�g���e�[�u���ɓo�^����B <BR>
     *  <BR>
     * ����.���[�����s�̗v�f����Loop�������s���B <BR>
     *  <BR>
     * �P�j���[�����M�e�[�u���Ƀ��R�[�h��o�^����B <BR>
     *  <BR>
     *    ���X�V���e�ɂ��ẮA <BR>
     *    DB�X�V�d�l�u�O������_���[�����M�e�[�u��.xls�v�Q�� <BR>
     *  <BR>
     * �Q�j���[�����M�g���e�[�u���Ƀ��R�[�h��o�^����B <BR>
     *  <BR>
     *    ���X�V���e�ɂ��ẮA <BR>
     *    DB�X�V�d�l�u�O������_���[�����M�g���e�[�u��.xls�v�Q�� <BR>
     * @@param l_mailInfo - (���[�����) <BR>
     * ���[���e�[�u���̍s�I�u�W�F�N�g�̔z��
     * 
     * @@param l_feqOrderUnit - (�����P��)
     * @@throws WEB3BaseException
     * @@roseuid 429D9FAE006C
     */
    protected void createMail(MailInfoRow[] l_mailInfo, 
            WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createMail";
        log.entering(STR_METHOD_NAME);
        // ����.���[�����s�̗v�f����Loop�������s���B 
        if (l_mailInfo == null || l_feqOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
        long l_lngBranchId = l_feqOrderUnit.getBranchId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        Branch l_branch = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        WEB3FeqProduct l_product = null;
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3GentradeFinObjectManager l_finObjectManager
            = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            
        try
        {
            l_product = 
                (WEB3FeqProduct)l_tradingModule.getProductManager().getProduct(l_feqOrderUnitRow.getProductId());
            l_branch = l_accountManager.getBranch(l_lngBranchId);
            l_mainAccount = 
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_feqOrderUnit.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }

        Market l_market = null;        
        if (!l_feqOrderUnitRow.getMarketIdIsNull())
        {
            try
            {
                l_market = l_finObjectManager.getMarket(l_feqOrderUnitRow.getMarketId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(), l_ex);

            }
        }

		//���������f�[�^���擾
		FeqOrderActionRow l_orderActionRow = null;
		l_orderActionRow = getFeqOrderAction(l_feqOrderUnit);

        final String STR_BLANK = " ";
        // // �Q�j���[�����M�g���e�[�u���Ƀ��R�[�h��o�^����BSTART** 
        List l_strItemNames = new ArrayList();
        List l_strItemValues = new ArrayList();
        //1 �^�p�R�[�h    �����P��.�^�p�R�[�h
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_EMP_CODE);
        if (l_feqOrderUnitRow.getOrderEmpCode() != null)
        {
            l_strItemValues.add(l_feqOrderUnitRow.getOrderEmpCode());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //2 ����No    �����P��.����ID
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_NO);
        l_strItemValues.add("" + l_feqOrderUnitRow.getOrderId());
        //3 �`�[No    �����P��.�`�[No
        l_strItemNames.add(WEB3FeqItemNameDef.VOUCHER_NO);
        if (l_feqOrderUnitRow.getVoucherNo() != null)
        {
            l_strItemValues.add(l_feqOrderUnitRow.getVoucherNo());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //4 �����R�[�h    �����P��.����ID�ɊY����������R�[�h
        l_strItemNames.add(WEB3FeqItemNameDef.PRODUCT_CODE);
        l_strItemValues.add(l_product.getProductCode());
        //5 ���������R�[�h    �����P��.����ID�ɊY�����錻�n�����R�[�h
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_PRODUCT_CODE);
        l_strItemValues.add(l_product.getOffshoreProductCode());
        //6 ������    �����P��.����ID�ɊY�����������
        l_strItemNames.add(WEB3FeqItemNameDef.STANDARD_NAME);
        l_strItemValues.add(l_product.getDisplayProductName());
        //7 ����    "�����P��.������� == �h�O�������h�̏ꍇ�A�h���t�h
        //�����P��.������� == �h�O������h�̏ꍇ�A�h���t�h"
        l_strItemNames.add(WEB3FeqItemNameDef.TRADE);
        if (OrderTypeEnum.FEQ_BUY.equals(l_feqOrderUnitRow.getOrderType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.BUY);
        }
        else if (OrderTypeEnum.FEQ_SELL.equals(l_feqOrderUnitRow.getOrderType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.SELL);
        }
        //8 ���ϕ��@@    "�����P��.���ϋ敪 == �h�~�݁h �̏ꍇ�A�h�~�݁h
        //�����P��.���ϋ敪 == �h�O�݁h �̏ꍇ�A�h�O�݁h"
        l_strItemNames.add(WEB3FeqItemNameDef.SETTLE_DIV);
        if (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(l_feqOrderUnitRow.getSettleDiv()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.JAPANESE_CURRENCY);
        }
        else if (WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(l_feqOrderUnitRow.getSettleDiv()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.FOREIGN_CURRENCY);
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //9 ����    "�����P��.�ŋ敪 == �h��ʁh �̏ꍇ�A�h��ʁh
        //�����P��.�ŋ敪 == �h����h �̏ꍇ�A�h����h"
        l_strItemNames.add(WEB3FeqItemNameDef.ACCOUNT);
        if (TaxTypeEnum.NORMAL.equals(l_feqOrderUnitRow.getTaxType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.NORMAL);
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_feqOrderUnitRow.getTaxType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.SPECIAL);
        }
        //10 �s��R�[�h    �����P��.�s��ID�ɊY������s��R�[�h
        l_strItemNames.add(WEB3FeqItemNameDef.MARKET_CODE);
        if (l_market != null)
        {
            l_strItemValues.add(l_market.getMarketCode());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //11 �s��    �����P��.�s��ID�ɊY������s�ꖼ
        l_strItemNames.add(WEB3FeqItemNameDef.MARKET);
        if (l_market != null)
        {
            l_strItemValues.add(l_market.getMarketName());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //12 ���s����    "�����P��.���s���� == �h�����Ȃ��h �̏ꍇ�A�h�������h
        //�����P��.���s���� == �h���h �̏ꍇ�A�h��t�h
        //�����P��.���s���� == �h�����h �̏ꍇ�A�h�����h
        //�����P��.���s���� == �h�s�o���������s�h �̏ꍇ�A�h�o�����Έ���(�s��)�h"
        l_strItemNames.add(WEB3FeqItemNameDef.EXECUTION_CONDITION_TYPE);
        if (FeqExecutionConditionType.NONE.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.NONE);
        }
        else if (FeqExecutionConditionType.AT_MARKET_OPEN.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.AT_MARKET_OPEN);
        }
        else if (FeqExecutionConditionType.AT_MARKET_CLOSE.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.AT_MARKET_CLOSE);
        }
        else if (FeqExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.AT_MARKET_CLOSE_NOT_EXECUTED);
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //13 ���������敪    "�����P��.���񒍕��̒����P�ʂh�c == null �̏ꍇ�A�h��������h
        //�����P��.���񒍕��̒����P�ʂh�c != null �̏ꍇ�A�h�o����܂Œ����h"
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_EXPIRATION_DATE_TYPE);
        if(l_feqOrderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.DAY_LIMIT);
        }
        else
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.CARRIED_ORDER);
        }
        //14 ��������    "�����P��.�������� == �h�t�w�l�h �̏ꍇ�A�h�t�w�l�h
        //�����P��.�������� == �hW�w�l�h �̏ꍇ�A�hW�w�l�h"
        //�����P��.�������� == �hDEFAULT�i�����w��Ȃ��j�h�̏ꍇ�A�hDEFAULT�i�����w��Ȃ��j�h
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_CONDITION_TYPE);
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.STOP_LIMIT_PRICE);
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.W_LIMIT_PRICE);
        }
        else if (WEB3OrderingConditionDef.DEFAULT.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.ORDER_CONDITION_DEFAULT);
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //15 �����������Z�q    "�����P��.�����������Z�q == �h�ȏ�h �̏ꍇ�A�h�ȏ�h
        //�����P��.�����������Z�q == �h�ȉ��h �̏ꍇ�A�h�ȉ��h"
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_COND_OPERATOR);
        if (WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(l_feqOrderUnitRow.getOrderCondOperator()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.ABOVE_BASE_PRICE);
        }
        else if (WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(l_feqOrderUnitRow.getOrderCondOperator()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.BELOW_BASE_PRICE);
        }
        else 
        {
            l_strItemValues.add(STR_BLANK);
        }
        //16 ���������P��    �����P��.�t�w�l��l
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_COND_PRICE);
        DecimalFormat formatSOPrice = new DecimalFormat("###0.######");
        String SOPrice = formatSOPrice.format(l_feqOrderUnitRow.getStopOrderPrice());
        if (l_feqOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_strItemValues.add(STR_BLANK);
        }
        else
        {
            l_strItemValues.add(SOPrice);
        }

        //17 �����w�l    �����P��.�iW�w�l�j�����w�l
        l_strItemNames.add(WEB3FeqItemNameDef.LIMIT_PRICE);
        DecimalFormat formatWLPrice = new DecimalFormat("###0.######");
        String WLPrice = formatWLPrice.format(l_feqOrderUnitRow.getWLimitPrice());
        if (l_feqOrderUnitRow.getWLimitPriceIsNull())
        {
            l_strItemValues.add(STR_BLANK);
        }
        else
        {
            l_strItemValues.add(WLPrice);
        }

		//18 ������    �����P��.�N�����P
		l_strItemNames.add(WEB3FeqItemNameDef.BIZ_DATE);
		//�����P��.���������hYYYY�NMM��DD���h�̌`���Őݒ�
		l_strItemValues.add(WEB3DateUtility.formatDate(WEB3DateUtility.getDate(l_feqOrderUnitRow.getBizDate(),"yyyyMMdd"),"yyyy�NMM��dd��"));

		//19 ��������    �����P��.�N�����Q
		l_strItemNames.add(WEB3FeqItemNameDef.RECEIVED_DATE_TIME);
		//��������.�쐬�������hYYYY�NMM��DD�� HH24:MI�h�̌`���Őݒ�
		l_strItemValues.add(WEB3DateUtility.formatDate(l_orderActionRow.getCreatedTimestamp(),"yyyy�NMM��dd�� HH:mm"));
		
		//20 ��������    �����P��.����
		l_strItemNames.add(WEB3FeqItemNameDef.ORDER_QUANTITY);
		//�����P��.�������ʂ��J���}�ҏW���Đݒ�
		DecimalFormat formatQuantity = new DecimalFormat("#,##0");
		l_strItemValues.add(formatQuantity.format(l_feqOrderUnitRow.getQuantity()));

		//21 �����P��    �����P��.���z
		l_strItemNames.add(WEB3FeqItemNameDef.PRICE);
		//�����P��.�w�l���J���}�ҏW���Đݒ�@@�����P��.�w�l=�O�̏ꍇ�́h���s�h��ݒ�
		DecimalFormat formatPrice = new DecimalFormat("###0.######");
		String limitPrice = formatPrice.format(l_feqOrderUnitRow.getLimitPrice());
		if(limitPrice.equals(WEB3OrderPriceDivDef.MARKET_PRICE))
		{
			l_strItemValues.add("���s");
		}
		else
		{
			l_strItemValues.add(limitPrice);
		}

        //�Q�j���[�����M�g���e�[�u���Ƀ��R�[�h��o�^����BEND** 
        try
        {    
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            Timestamp l_timeStamp =
                GtlUtils.getTradingSystem().getSystemTimestamp();
            for (int i = 0; i < l_mailInfo.length; i++)
            {   

                // �P�j���[�����M�e�[�u���Ƀ��R�[�h��o�^����B 
                //  
                //    ���X�V���e�ɂ��ẮA 
                //    DB�X�V�d�l�u�O������_���[�����M�e�[�u��.xls�v�Q�� 
                //  
                MailProcParams l_mailProcParams = new MailProcParams();
                MailInfoRow l_mailInfoRow = l_mailInfo[i];
                //�،���ЃR�[�hinstitution_code�����P��.�،���ЃR�[�h    
                l_mailProcParams.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());
                //���X�R�[�hbranch_code�����P��.���XID�ɊY�����镔�X�R�[�h
                l_mailProcParams.setBranchCode(l_branch.getBranchCode());
                //���M���[���敪sendmail_div���[���e�[�u��.���M���[���敪
                l_mailProcParams.setSendmailDiv(l_mailInfoRow.getSendmailDiv());
                //����ID    discernment_id    ���[���e�[�u��.����ID
                l_mailProcParams.setDiscernmentId(l_mailInfoRow.getDiscernmentId());
                //�����R�[�haccount_code    �����P��.����ID�ɊY����������R�[�h�i6���j
                l_mailProcParams.setAccountCode(l_mainAccount.getDisplayAccountCode());
                //���[��IDmail_id    ��������.��������ID        
				l_mailProcParams.setMailId(l_orderActionRow.getOrderActionId());
                //�N�����Pdate_1    null
                l_mailProcParams.setDate1(null);
                //�N�����Qdate_2 null
                l_mailProcParams.setDate2(null);
                //�N�����Rdate_3    �����P��.����������        
                l_mailProcParams.setDate3(l_feqOrderUnitRow.getExpirationDate());
                //�N�����Sdate_4        null                                        
                l_mailProcParams.setDate4(null);
                //����quantity    null    
                l_mailProcParams.setQuantity(null);
                //���zamount    null                                            
                l_mailProcParams.setAmount(null);
                //IDorder_id  �����P��.�����P��ID                                                
                l_mailProcParams.setOrderId(l_feqOrderUnitRow.getOrderUnitId());
                //�敪division        null            
                l_mailProcParams.setDivision(null);
                //����1    name_1        �����P��.����ID�ɊY������ڋq��
                l_mailProcParams.setName1(((WEB3GentradeMainAccount)l_mainAccount).getDisplayAccountName());
                //����2    name_2        �����P��.�ʉ݃R�[�h�ɊY������ʉݗ���
                WEB3GentradeCurrency l_genCurrency = l_feqOrderUnit.getCurrency();
                GenCurrencyRow l_currencyRow = (GenCurrencyRow)l_genCurrency.getDataSourceObject();
                l_mailProcParams.setName2(l_currencyRow.getCurrencyShortName());
                //�d�q���[�����M�X�e�C�^�Xstatus            0�F�������iEmail�����M�j    
                l_mailProcParams.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);
                //�d�q���[�����M����send_process_date_time        null        
                l_mailProcParams.setSendProcessDateTime(null);
                //�d�q���[�����M�G���[�R�[�herror_code        null    
                l_mailProcParams.setErrorCode(null);
                //�Ǘ��Ҋm�F�p���[���敪    null
                l_mailProcParams.setAdminMailDiv(null);
                //�đ��敪    null
                l_mailProcParams.setResendStatus(null);
                //�d�q���[���đ�����resend_process_date_time    null
                l_mailProcParams.setResendProcessDateTime(null);
                //email�A�h���X    email_address        
                l_mailProcParams.setEmailAddress(l_mailInfoRow.getSendAddress());
                //���Memail�A�h���Xsend_email_address    ���[���e�[�u��.���o�l
                l_mailProcParams.setSendEmailAddress(l_mailInfoRow.getMailSender());
                //����subject
				//���V�K������t���[���̏ꍇ
				//	�^�p�R�[�h�{���p�X�y�[�X�Q�{�g�O����������h�{���p�X�y�[�X�P�{�g�����`�[�h
				//������������t���[���̏ꍇ
				//	�^�p�R�[�h�{���p�X�y�[�X�Q�{�g�O����������h�{���p�X�y�[�X�P�{�g���������`�[�h
				//�����������t���[���̏ꍇ
				//	�^�p�R�[�h�{���p�X�y�[�X�Q�{�g�O����������h�{���p�X�y�[�X�P�{�g��������`�[�h

				String sendMailDiv = null;
				String subject = null;
				//�V�K������t���[���̏ꍇ
				if(l_mailInfoRow.getSendmailDiv().equals(WEB3SendmailDivDef.FEQ_ORDER_ACCEPT))
				{
					sendMailDiv = "�����`�[";
				}
				//����������t���[���̏ꍇ
				else if(l_mailInfoRow.getSendmailDiv().equals(WEB3SendmailDivDef.FEQ_ORDER_CHANGE))
				{
					sendMailDiv = "���������`�[";
				}
				//���������t���[���̏ꍇ
				else if(l_mailInfoRow.getSendmailDiv().equals(WEB3SendmailDivDef.FEQ_ORDER_CANCEL))
				{
					sendMailDiv = "��������`�[";
				}
				
				subject = l_feqOrderUnitRow.getOrderEmpCode() + "  �O��������� " + sendMailDiv;
				
                l_mailProcParams.setSubject(subject);
                //���[���{��mail_text    null
                l_mailProcParams.setMailText(null);
                //�폜�t���Odelete_flag                            0:FALSE�i�L���j
                l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
                //�쐬����created_timestamp                        ��������    

                l_mailProcParams.setCreatedTimestamp(l_timeStamp);
                //�X�V����last_updated_timestamp                    ��������
                l_mailProcParams.setLastUpdatedTimestamp(l_timeStamp);
                l_processor.doInsertQuery(l_mailProcParams);
                // �Q�j���[�����M�g���e�[�u���Ƀ��R�[�h��o�^����B 
                //  
                //    ���X�V���e�ɂ��ẮA 
                //    DB�X�V�d�l�u�O������_���[�����M�g���e�[�u��.xls�v�Q�� 
                

                for (int j = 0; j < l_strItemNames.size(); j++)
                {
                    ExtMailProcParams l_extMailProcParams = new ExtMailProcParams();
                    //�،���ЃR�[�hinstitution_code���[�����M�e�[�u��.�،���ЃR�[�h    
                    l_extMailProcParams.setInstitutionCode(l_mailProcParams.getInstitutionCode());
                    //���X�R�[�hbranch_code        ���[�����M�e�[�u��.���X�R�[�h                            
                    l_extMailProcParams.setBranchCode(l_mailProcParams.getBranchCode());
                    //���M���[���敪sendmail_div                        ���[�����M�e�[�u��.���M���[���敪        
                    l_extMailProcParams.setSendmailDiv(l_mailProcParams.getSendmailDiv());
                    //����IDdiscernment_id                    ���[�����M�e�[�u��.����ID            
                    l_extMailProcParams.setDiscernmentId(l_mailProcParams.getDiscernmentId());
                    //�����R�[�haccount_code                    ���[�����M�e�[�u��.�����R�[�h                
                    l_extMailProcParams.setAccountCode(l_mailProcParams.getAccountCode());
                    //���[��IDmail_id                    ���[�����M�e�[�u��.���[��ID                    
                    l_extMailProcParams.setMailId(l_mailProcParams.getMailId());
                    //���ږ�item_name                    �V�[�g�u�o�^���e[�V�K]�v�Q��    
                    l_extMailProcParams.setItemName((String)l_strItemNames.get(j));
                    //���ړ��eitem_contents                        �V�[�g�u�o�^���e[�V�K]�v�Q��        
                    l_extMailProcParams.setItemContents((String)l_strItemValues.get(j));
                    //�폜�t���Odelete_flag                0:FALSE�i�L���j    
                    l_extMailProcParams.setDeleteFlag(BooleanEnum.FALSE);
                    //�쐬����created_timestamp                                ��������
                    l_extMailProcParams.setCreatedTimestamp(l_timeStamp);
                    //�X�V����last_updated_timestamp                            ��������
                    l_extMailProcParams.setLastUpdatedTimestamp(l_timeStamp);
                    l_processor.doInsertQuery(l_extMailProcParams);
                }

            }
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }

    }
    
    /**
     * (createTempMail) <BR>
     * ���[�����M�e���|�����e�[�u���A <BR>
     * ���[�����M�g���e���|�����e�[�u���ɓo�^����B <BR>
     *  <BR>
     * ����.���[�����s�̗v�f����Loop�������s���B <BR>
     *  <BR>
     * �P�j���[�����M�e���|�����e�[�u���Ƀ��R�[�h��o�^����B <BR>
     *  <BR>
     *    ���X�V���e�ɂ��ẮA <BR>
     * �@@DB�X�V�d�l�u�O������_���[�����M�e�[�u��.xls�v�Q�� <BR>
     *  <BR>
     * �Q�j���[�����M�g���e���|�����e�[�u���Ƀ��R�[�h��o�^����B <BR>
     *  <BR>
     *    ���X�V���e�ɂ��ẮA <BR>
     * �@@DB�X�V�d�l�u�O������_���[�����M�g���e�[�u��.xls�v�Q�� <BR>
     * @@param l_mailInfo - (���[�����) <BR>
     * ���[���e�[�u���̍s�I�u�W�F�N�g�̔z��
     * 
     * @@param l_feqOrderUnit - (�����P��)
     * @@throws WEB3BaseException
     * @@roseuid 42A0518F0026
     */
    protected void createTempMail(MailInfoRow[] l_mailInfo, WEB3FeqOrderUnit l_feqOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createTempMail(MailInfoRow[] l_mailInfo, WEB3FeqOrderUnit l_feqOrderUnit) ";
        log.entering(STR_METHOD_NAME);
        // ����.���[�����s�̗v�f����Loop�������s���B 
        
        if (l_mailInfo == null)
        {
            return;
        }        
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
        long l_lngBranchId = l_feqOrderUnit.getBranchId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        Branch l_branch = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        WEB3FeqProduct l_product = null;
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3GentradeFinObjectManager l_finObjectManager
            = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        try
        {
            l_product = 
                (WEB3FeqProduct)l_tradingModule.getProductManager().getProduct(l_feqOrderUnitRow.getProductId());
            l_branch = l_accountManager.getBranch(l_lngBranchId);
            l_mainAccount = 
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_feqOrderUnit.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        Market l_market = null;
                
        if (!l_feqOrderUnitRow.getMarketIdIsNull())
        {
            try
            {
                l_market = l_finObjectManager.getMarket(l_feqOrderUnitRow.getMarketId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(), l_ex);
            }
        }

		//���������f�[�^���擾
		FeqOrderActionRow l_orderActionRow = null;
		l_orderActionRow = getFeqOrderAction(l_feqOrderUnit);
		
        final String STR_BLANK = " ";
        // // �Q�j���[�����M�g���e�[�u���Ƀ��R�[�h��o�^����BSTART** 
        List l_strItemNames = new ArrayList();
        List l_strItemValues = new ArrayList();
        //1 �^�p�R�[�h    �����P��.�^�p�R�[�h
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_EMP_CODE);
        if (l_feqOrderUnitRow.getOrderEmpCode() != null)
        {
            l_strItemValues.add(l_feqOrderUnitRow.getOrderEmpCode());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //2 ����No    �����P��.����ID
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_NO);
        l_strItemValues.add("" + l_feqOrderUnitRow.getOrderId());
        //3 �`�[No    �����P��.�`�[No
        l_strItemNames.add(WEB3FeqItemNameDef.VOUCHER_NO);
        if (l_feqOrderUnitRow.getVoucherNo() != null)
        {
            l_strItemValues.add(l_feqOrderUnitRow.getVoucherNo());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //4 �����R�[�h    �����P��.����ID�ɊY����������R�[�h
        l_strItemNames.add(WEB3FeqItemNameDef.PRODUCT_CODE);
        l_strItemValues.add(l_product.getProductCode());
        //5 ���������R�[�h    �����P��.����ID�ɊY�����錻�n�����R�[�h
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_PRODUCT_CODE);
        l_strItemValues.add(l_product.getOffshoreProductCode());
        //6 ������    �����P��.����ID�ɊY�����������
        l_strItemNames.add(WEB3FeqItemNameDef.STANDARD_NAME);
        l_strItemValues.add(l_product.getDisplayProductName());
        //7 ����    "�����P��.������� == �h�O�������h�̏ꍇ�A�h���t�h
        //�����P��.������� == �h�O������h�̏ꍇ�A�h���t�h"
        l_strItemNames.add(WEB3FeqItemNameDef.TRADE);
        if (OrderTypeEnum.FEQ_BUY.equals(l_feqOrderUnitRow.getOrderType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.BUY);
        }
        else if (OrderTypeEnum.FEQ_SELL.equals(l_feqOrderUnitRow.getOrderType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.SELL);
        }
        //8 ���ϕ��@@    "�����P��.���ϋ敪 == �h�~�݁h �̏ꍇ�A�h�~�݁h
        //�����P��.���ϋ敪 == �h�O�݁h �̏ꍇ�A�h�O�݁h"
        l_strItemNames.add(WEB3FeqItemNameDef.SETTLE_DIV);
        if (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(l_feqOrderUnitRow.getSettleDiv()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.JAPANESE_CURRENCY);
        }
        else if (WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(l_feqOrderUnitRow.getSettleDiv()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.FOREIGN_CURRENCY);
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //9 ����    "�����P��.�ŋ敪 == �h��ʁh �̏ꍇ�A�h��ʁh
        //�����P��.�ŋ敪 == �h����h �̏ꍇ�A�h����h"
        l_strItemNames.add(WEB3FeqItemNameDef.ACCOUNT);
        if (TaxTypeEnum.NORMAL.equals(l_feqOrderUnitRow.getTaxType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.NORMAL);
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_feqOrderUnitRow.getTaxType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.SPECIAL);
        }
        //10 �s��R�[�h    �����P��.�s��ID�ɊY������s��R�[�h
        l_strItemNames.add(WEB3FeqItemNameDef.MARKET_CODE);
        if (l_market != null)
        {
            l_strItemValues.add(l_market.getMarketCode());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //11 �s��    �����P��.�s��ID�ɊY������s�ꖼ
        l_strItemNames.add(WEB3FeqItemNameDef.MARKET);
        if (l_market != null)
        {
            l_strItemValues.add(l_market.getMarketName());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //12 ���s����    "�����P��.���s���� == �h�����Ȃ��h �̏ꍇ�A�h�������h
        //�����P��.���s���� == �h���h �̏ꍇ�A�h��t�h
        //�����P��.���s���� == �h�����h �̏ꍇ�A�h�����h
        //�����P��.���s���� == �h�s�o���������s�h �̏ꍇ�A�h�o�����Έ���(�s��)�h"
        l_strItemNames.add(WEB3FeqItemNameDef.EXECUTION_CONDITION_TYPE);
        if (FeqExecutionConditionType.NONE.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.NONE);
        }
        else if (FeqExecutionConditionType.AT_MARKET_OPEN.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.AT_MARKET_OPEN);
        }
        else if (FeqExecutionConditionType.AT_MARKET_CLOSE.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.AT_MARKET_CLOSE);
        }
        else if (FeqExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.AT_MARKET_CLOSE_NOT_EXECUTED);
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //13 ���������敪    "�����P��.���񒍕��̒����P�ʂh�c == null �̏ꍇ�A�h��������h
        //�����P��.���񒍕��̒����P�ʂh�c != null �̏ꍇ�A�h�o����܂Œ����h"
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_EXPIRATION_DATE_TYPE);
        if(l_feqOrderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.DAY_LIMIT);
        }
        else
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.CARRIED_ORDER);
        }
        //14 ��������    "�����P��.�������� == �h�t�w�l�h �̏ꍇ�A�h�t�w�l�h
        //�����P��.�������� == �hW�w�l�h �̏ꍇ�A�hW�w�l�h"
        //�����P��.�������� == �hDEFAULT�i�����w��Ȃ��j�h�̏ꍇ�A�hDEFAULT�i�����w��Ȃ��j�h
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_CONDITION_TYPE);
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.STOP_LIMIT_PRICE);
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.W_LIMIT_PRICE);
        }
        else if (WEB3OrderingConditionDef.DEFAULT.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.ORDER_CONDITION_DEFAULT);
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //15 �����������Z�q    "�����P��.�����������Z�q == �h�ȏ�h �̏ꍇ�A�h�ȏ�h
        //�����P��.�����������Z�q == �h�ȉ��h �̏ꍇ�A�h�ȉ��h"
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_COND_OPERATOR);
        if (WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(l_feqOrderUnitRow.getOrderCondOperator()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.ABOVE_BASE_PRICE);
        }
        else if (WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(l_feqOrderUnitRow.getOrderCondOperator()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.BELOW_BASE_PRICE);
        }
        else 
        {
            l_strItemValues.add(STR_BLANK);
        }
        //16 ���������P��    �����P��.�t�w�l��l
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_COND_PRICE);
        DecimalFormat formatSOPrice = new DecimalFormat("###0.######");
        String SOPrice = formatSOPrice.format(l_feqOrderUnitRow.getStopOrderPrice());
        if (l_feqOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_strItemValues.add(STR_BLANK);
        }
        else
        {
            l_strItemValues.add(SOPrice);
        }

        //17 �����w�l    �����P��.�iW�w�l�j�����w�l
        l_strItemNames.add(WEB3FeqItemNameDef.LIMIT_PRICE);
        DecimalFormat formatWLPrice = new DecimalFormat("###0.######");
        String WLPrice = formatWLPrice.format(l_feqOrderUnitRow.getWLimitPrice());
        if (l_feqOrderUnitRow.getWLimitPriceIsNull())
        {
            l_strItemValues.add(STR_BLANK);
        }
        else
        {
            l_strItemValues.add(WLPrice);
        }

		//18 ������    �����P��.�N�����P
		l_strItemNames.add(WEB3FeqItemNameDef.BIZ_DATE);
		//�����P��.���������hYYYY�NMM��DD���h�̌`���Őݒ�
		l_strItemValues.add(WEB3DateUtility.formatDate(WEB3DateUtility.getDate(l_feqOrderUnitRow.getBizDate(),"yyyyMMdd"),"yyyy�NMM��dd��"));

		//19 ��������    �����P��.�N�����Q
		l_strItemNames.add(WEB3FeqItemNameDef.RECEIVED_DATE_TIME);
		//��������.�쐬�������hYYYY�NMM��DD�� HH24:MI�h�̌`���Őݒ�
		l_strItemValues.add(WEB3DateUtility.formatDate(l_orderActionRow.getCreatedTimestamp(),"yyyy�NMM��dd�� HH:mm"));
		
		//20 ��������    �����P��.����
		l_strItemNames.add(WEB3FeqItemNameDef.ORDER_QUANTITY);
		//�����P��.�������ʂ��J���}�ҏW���Đݒ�
		DecimalFormat formatQuantity = new DecimalFormat("#,##0");
		l_strItemValues.add(formatQuantity.format(l_feqOrderUnitRow.getQuantity()));

		//21 �����P��    �����P��.���z
		l_strItemNames.add(WEB3FeqItemNameDef.PRICE);
		//�����P��.�w�l���J���}�ҏW���Đݒ�@@�����P��.�w�l=�O�̏ꍇ�́h���s�h��ݒ�
		DecimalFormat formatPrice = new DecimalFormat("###0.######");

		String limitPrice = formatPrice.format(l_feqOrderUnitRow.getLimitPrice());
		if(limitPrice.equals(WEB3OrderPriceDivDef.MARKET_PRICE))
		{
			l_strItemValues.add("���s");
		}
		else
		{
			l_strItemValues.add(limitPrice);
		}

        //�Q�j���[�����M�g���e�[�u���Ƀ��R�[�h��o�^����BEND** 
        QueryProcessor l_processor = null;

        try
        {
            l_processor = Processors.getDefaultProcessor();
            Timestamp l_timeStamp =
                GtlUtils.getTradingSystem().getSystemTimestamp();
            for (int i = 0; i < l_mailInfo.length; i++)
            {  

                // �P�j���[�����M�e�[�u���Ƀ��R�[�h��o�^����B 
                //  
                //    ���X�V���e�ɂ��ẮA 
                //    DB�X�V�d�l�u�O������_���[�����M�e�[�u��.xls�v�Q�� 
                //  
                MailProcTempParams l_mailProcTempParams = new MailProcTempParams();
                MailInfoRow l_mailInfoRow = l_mailInfo[i];
                //�،���ЃR�[�hinstitution_code�����P��.�،���ЃR�[�h    
                l_mailProcTempParams.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());
                //���X�R�[�hbranch_code�����P��.���XID�ɊY�����镔�X�R�[�h
                l_mailProcTempParams.setBranchCode(l_branch.getBranchCode());
                //���M���[���敪sendmail_div���[���e�[�u��.���M���[���敪
                l_mailProcTempParams.setSendmailDiv(l_mailInfoRow.getSendmailDiv());
                //����ID    discernment_id    ���[���e�[�u��.����ID
                l_mailProcTempParams.setDiscernmentId(l_mailInfoRow.getDiscernmentId());
                //�����R�[�haccount_code    �����P��.����ID�ɊY����������R�[�h�i6���j
                l_mailProcTempParams.setAccountCode(l_mainAccount.getDisplayAccountCode());
				//���[��IDmail_id    ��������.��������ID        
				l_mailProcTempParams.setMailId(l_orderActionRow.getOrderActionId());
                //�N�����Pdate_1    null
                l_mailProcTempParams.setDate1(null);
                //�N�����Qdate_2    null
                l_mailProcTempParams.setDate2(null);
                //�N�����Rdate_3    �����P��.����������        
                l_mailProcTempParams.setDate3(l_feqOrderUnitRow.getExpirationDate());
                //�N�����Sdate_4        null                                        
                l_mailProcTempParams.setDate4(null);
                //����quantity    null    
                l_mailProcTempParams.setQuantity(null);
                //���zamount    null                                            
                l_mailProcTempParams.setAmount(null);
                //IDorder_id  �����P��.�����P��ID                                                
                l_mailProcTempParams.setOrderId(l_feqOrderUnitRow.getOrderUnitId());
                //�敪division        null            
                l_mailProcTempParams.setDivision(null);
                //����1    name_1        �����P��.����ID�ɊY������ڋq��
                l_mailProcTempParams.setName1(((WEB3GentradeMainAccount)l_mainAccount).getDisplayAccountName());
                //����2    name_2        �����P��.�ʉ݃R�[�h�ɊY������ʉݗ���
                WEB3GentradeCurrency l_genCurrency = l_feqOrderUnit.getCurrency();
                GenCurrencyRow l_currencyRow = (GenCurrencyRow)l_genCurrency.getDataSourceObject();
                l_mailProcTempParams.setName2(l_currencyRow.getCurrencyShortName());
                //�d�q���[�����M�X�e�C�^�Xstatus            0�F�������iEmail�����M�j    
                l_mailProcTempParams.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);
                //�d�q���[�����M����send_process_date_time        null        
                l_mailProcTempParams.setSendProcessDateTime(null);
                //�d�q���[�����M�G���[�R�[�herror_code        null    
                l_mailProcTempParams.setErrorCode(null);
                //�Ǘ��Ҋm�F�p���[���敪    null
                l_mailProcTempParams.setAdminMailDiv(null);
                //�đ��敪    null
                l_mailProcTempParams.setResendStatus(null);
                //�d�q���[���đ�����resend_process_date_time    null
                l_mailProcTempParams.setResendProcessDateTime(null);
                //���Memail�A�h���Xsend_email_address  
                l_mailProcTempParams.setSendEmailAddress(l_mailInfoRow.getMailSender());
                //email�A�h���X    email_address
                l_mailProcTempParams.setEmailAddress(l_mailInfoRow.getSendAddress());

				//����subject
				//���V�K������t���[���̏ꍇ
				//	�^�p�R�[�h�{���p�X�y�[�X�Q�{�g�O����������h�{���p�X�y�[�X�P�{�g�����`�[�h
				//������������t���[���̏ꍇ
				//	�^�p�R�[�h�{���p�X�y�[�X�Q�{�g�O����������h�{���p�X�y�[�X�P�{�g���������`�[�h
				//�����������t���[���̏ꍇ
				//	�^�p�R�[�h�{���p�X�y�[�X�Q�{�g�O����������h�{���p�X�y�[�X�P�{�g��������`�[�h

				String sendMailDiv = null;
				String subject = null;
				//�V�K������t���[���̏ꍇ
				if(l_mailInfoRow.getSendmailDiv().equals(WEB3SendmailDivDef.FEQ_ORDER_ACCEPT))
				{
					sendMailDiv = "�����`�[";
				}
				//����������t���[���̏ꍇ
				else if(l_mailInfoRow.getSendmailDiv().equals(WEB3SendmailDivDef.FEQ_ORDER_CHANGE))
				{
					sendMailDiv = "���������`�[";
				}
				//���������t���[���̏ꍇ
				else if(l_mailInfoRow.getSendmailDiv().equals(WEB3SendmailDivDef.FEQ_ORDER_CANCEL))
				{
					sendMailDiv = "��������`�[";
				}
				
				subject = l_feqOrderUnitRow.getOrderEmpCode() + "  �O��������� " + sendMailDiv;
				
				l_mailProcTempParams.setSubject(subject);

                //���[���{��mail_text    null
                l_mailProcTempParams.setMailText(null);
                //�폜�t���Odelete_flag                            0:FALSE�i�L���j
                l_mailProcTempParams.setDeleteFlag(BooleanEnum.FALSE.intValue());
                //�쐬����created_timestamp                        ��������    

                l_mailProcTempParams.setCreatedTimestamp(l_timeStamp);
                //�X�V����last_updated_timestamp                    ��������
                l_mailProcTempParams.setLastUpdatedTimestamp(l_timeStamp);
                l_processor.doInsertQuery(l_mailProcTempParams);
                // �Q�j���[�����M�g���e�[�u���Ƀ��R�[�h��o�^����B 
                //  
                //    ���X�V���e�ɂ��ẮA 
                //    DB�X�V�d�l�u�O������_���[�����M�g���e�[�u��.xls�v�Q�� 
                for (int j = 0; j < l_strItemNames.size(); j++)
                {
                    ExtMailProcTempParams l_extMailProcTempParams = new ExtMailProcTempParams();
                    //�،���ЃR�[�hinstitution_code���[�����M�e�[�u��.�،���ЃR�[�h    
                    l_extMailProcTempParams.setInstitutionCode(l_mailProcTempParams.getInstitutionCode());
                    //���X�R�[�hbranch_code        ���[�����M�e�[�u��.���X�R�[�h                            
                    l_extMailProcTempParams.setBranchCode(l_mailProcTempParams.getBranchCode());
                    //���M���[���敪sendmail_div                        ���[�����M�e�[�u��.���M���[���敪        
                    l_extMailProcTempParams.setSendmailDiv(l_mailProcTempParams.getSendmailDiv());
                    //����IDdiscernment_id                    ���[�����M�e�[�u��.����ID            
                    l_extMailProcTempParams.setDiscernmentId(l_mailProcTempParams.getDiscernmentId());
                    //�����R�[�haccount_code                    ���[�����M�e�[�u��.�����R�[�h                
                    l_extMailProcTempParams.setAccountCode(l_mailProcTempParams.getAccountCode());
                    //���[��IDmail_id                    ���[�����M�e�[�u��.���[��ID                    
                    l_extMailProcTempParams.setMailId(l_mailProcTempParams.getMailId());
                    //���ږ�item_name                    �V�[�g�u�o�^���e[�V�K]�v�Q��    
                    l_extMailProcTempParams.setItemName((String)l_strItemNames.get(j));
                    //���ړ��eitem_contents                        �V�[�g�u�o�^���e[�V�K]�v�Q��        
                    l_extMailProcTempParams.setItemContents((String)l_strItemValues.get(j));
                    //�폜�t���Odelete_flag                0:FALSE�i�L���j    
                    l_extMailProcTempParams.setDeleteFlag(BooleanEnum.FALSE.intValue());
                    //�쐬����created_timestamp                                ��������
                    l_extMailProcTempParams.setCreatedTimestamp(l_timeStamp);
                    //�X�V����last_updated_timestamp                            ��������
                    l_extMailProcTempParams.setLastUpdatedTimestamp(l_timeStamp);
                    l_processor.doInsertQuery(l_extMailProcTempParams);
                }
            }
            
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (updateTempMail) <BR>
     * ���[�����M�e���|�����e�[�u���A <BR>
     * ���[�����M�g���e���|�����e�[�u�����X�V����B <BR>
     *  <BR>
     * �P�j���[�����M�e���|�����e�[�u���̍X�V <BR>
     *  <BR>
     * �P�|�P�j�ȉ��̏����Ń��[�����M�e���|�����e�[�u������f�[�^���擾����B <BR>
     *  <BR>
     *    [����] <BR>
     *    �،���ЃR�[�h = ����.�����P��.�،���ЃR�[�h <BR>
     *    ���M���[���敪 = "1001" <BR>
     *    ���[��ID = ����.��������.��������ID <BR>
     *  <BR>
     * �P�|�Q�j���[�����M�e���|�����e�[�u���̃��R�[�h���X�V����B <BR>
     *  <BR>
     *    ���X�V���e�ɂ��ẮA <BR>
     *    DB�X�V�d�l�u�O������_���[�����M�e�[�u��.xls�v�Q�� <BR>
     *    ���������擾�ł����ꍇ�́A���ׂĂɂ��čX�V���s���B <BR>
     *  <BR>
     * �Q�j���[�����M�g���e���|�����e�[�u���̍X�V <BR>
     *  <BR>
     * �Q�|�P�j�ȉ��̏����Ń��[�����M�g���e���|�����e�[�u������ <BR>
     *    �f�[�^���擾����B <BR>
     *  <BR>
     *    [����] <BR>
     *    �،���ЃR�[�h = ����.�����P��.�،���ЃR�[�h <BR>
     *    ���M���[���敪 = "1001" <BR>
     *    ���[��ID = ����.��������.��������ID <BR>
     *  <BR>
     * �Q�|�Q�j���[�����M�g���e���|�����e�[�u���̃��R�[�h���X�V����B <BR>
     *  <BR>
     *    ���X�V���e�ɂ��ẮA <BR>
     * �@@DB�X�V�d�l�u�O������_���[�����M�g���e�[�u��.xls�v�Q�� <BR>
     *    �����ׂẴ��R�[�h�ɂ��čX�V���s���B <BR>
     * @@param l_feqOrderUnit - (�����P��)
     * @@throws WEB3BaseException
     * @@roseuid 42A0564C0362
     */
    protected void updateTempMail(WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateTempMail(WEB3FeqOrderUnit l_feqOrderUnit) ";
        log.entering(STR_METHOD_NAME);
        // �P�j���[�����M�e���|�����e�[�u���̍X�V 
        //  
        // �P�|�P�j�ȉ��̏����Ń��[�����M�e���|�����e�[�u������f�[�^���擾����B 
        //  
        //    [����] 
        //    �،���ЃR�[�h = ����.�����P��.�،���ЃR�[�h 
        //    ���M���[���敪 = "1001" 
        //    ���[��ID = ����.��������.��������ID 
        // 
        Timestamp l_timeStamp =
            GtlUtils.getTradingSystem().getSystemTimestamp();
        List l_lisMailProcTemp = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();

			//���������f�[�^���擾
			FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
			FeqOrderActionRow l_orderActionRow = null;
			l_orderActionRow = getFeqOrderAction(l_feqOrderUnit);
			Long l_prevMailId = null;

            String l_strWhere = "institution_code = ? and sendmail_div = ? and order_id = ?";
            Object[] l_objWhereValue = new Object[3];
            l_objWhereValue[0] = l_feqOrderUnitRow.getInstitutionCode();
            l_objWhereValue[1] = WEB3SendmailDivDef.FEQ_ORDER_ACCEPT;
			l_objWhereValue[2] = new Long(l_feqOrderUnitRow.getOrderUnitId());

            l_lisMailProcTemp = 
                l_processor.doFindAllQuery(MailProcTempRow.TYPE, l_strWhere, l_objWhereValue);
            if ((l_lisMailProcTemp != null) && (l_lisMailProcTemp.size() != 0))
            {
                // �P�|�Q�j���[�����M�e���|�����e�[�u���̃��R�[�h���X�V����B 
                //  
                //    ���X�V���e�ɂ��ẮA 
                //    DB�X�V�d�l�u�O������_���[�����M�e�[�u��.xls�v�Q�� 
                //    ���������擾�ł����ꍇ�́A���ׂĂɂ��čX�V���s���B 
                //  
                for (int i = 0; i < l_lisMailProcTemp.size(); i++ )
                {
                    MailProcTempRow l_mailProcTempRow = 
                        (MailProcTempRow)l_lisMailProcTemp.get(i);
                    MailProcTempParams l_mailProcTempParams = new MailProcTempParams(l_mailProcTempRow);
					l_prevMailId =  new Long(l_mailProcTempParams.getMailId());
					l_mailProcTempParams.setMailId(l_orderActionRow.getOrderActionId());
                    l_mailProcTempParams.setDate3(l_feqOrderUnitRow.getExpirationDate());
                    l_mailProcTempParams.setLastUpdatedTimestamp(l_timeStamp);
					l_processor.doDeleteAllQuery(MailProcTempRow.TYPE,l_strWhere,l_objWhereValue);
                    l_processor.doInsertQuery(l_mailProcTempParams);
                }
            }
            // �Q�j���[�����M�g���e���|�����e�[�u���̍X�V 
            //  
            // �Q�|�P�j�ȉ��̏����Ń��[�����M�g���e���|�����e�[�u������ 
            //    �f�[�^���擾����B 
            //  
            //    [����] 
            //    �،���ЃR�[�h = ����.�����P��.�،���ЃR�[�h 
            //    ���M���[���敪 = "1001" 
            //    ���[��ID = ����.��������.��������ID 
            //  
            // �Q�|�Q�j���[�����M�g���e���|�����e�[�u���̃��R�[�h���X�V����B 
            //  
            //    ���X�V���e�ɂ��ẮA 
            // �@@DB�X�V�d�l�u�O������_���[�����M�g���e�[�u��.xls�v�Q�� 
            //    �����ׂẴ��R�[�h�ɂ��čX�V���s���B 

			String l_strWhereExt = "institution_code = ? and sendmail_div = ? and mail_id = ?";
			Object[] l_objWhereValueExt = new Object[3];
			l_objWhereValueExt[0] = l_feqOrderUnitRow.getInstitutionCode();
			l_objWhereValueExt[1] = WEB3SendmailDivDef.FEQ_ORDER_ACCEPT;
			l_objWhereValueExt[2] = l_prevMailId;
		

            List l_lisExtMailProcTemp = 
                l_processor.doFindAllQuery(ExtMailProcTempRow.TYPE, l_strWhereExt, l_objWhereValueExt);
            if ((l_lisExtMailProcTemp == null) || (l_lisExtMailProcTemp.size() == 0))
            {
                return;
            }
            
            final String STR_BLANK = " ";
            for (int i = 0; i < l_lisExtMailProcTemp.size(); i++)
            {
                ExtMailProcTempRow l_row = (ExtMailProcTempRow)l_lisExtMailProcTemp.get(i);
                ExtMailProcTempParams l_params = new ExtMailProcTempParams(l_row);
                if (WEB3FeqItemNameDef.EXECUTION_CONDITION_TYPE.equals(l_params.getItemName()))
                {
                    //�����P��.���s���� == �h�����Ȃ��h �̏ꍇ�A�h�������h
    
                    if (FeqExecutionConditionType.NONE.
                            equals(l_feqOrderUnitRow.getExecutionConditionType()))
                    {
                        l_params.setItemContents(WEB3FeqItemContentsDef.NONE);
                    }
                    //�����P��.���s���� == �h���h �̏ꍇ�A�h��t�h
    
                    else if (FeqExecutionConditionType.AT_MARKET_OPEN.
                            equals(l_feqOrderUnitRow.getExecutionConditionType()))
                    {
                        l_params.setItemContents(WEB3FeqItemContentsDef.AT_MARKET_OPEN);
                    }
                    //�����P��.���s���� == �h�����h �̏ꍇ�A�h�����h
    
                    else if (FeqExecutionConditionType.AT_MARKET_CLOSE.
                            equals(l_feqOrderUnitRow.getExecutionConditionType()))
                    {
                        l_params.setItemContents(WEB3FeqItemContentsDef.AT_MARKET_CLOSE);
                    }
                    //�����P��.���s���� == �h�s�o���������s�h �̏ꍇ�A�h�o�����Έ���(�s��)�h
                    else if (FeqExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.
                            equals(l_feqOrderUnitRow.getExecutionConditionType()))
                    {
                        l_params.setItemContents(WEB3FeqItemContentsDef.AT_MARKET_CLOSE_NOT_EXECUTED);
                    }
                    //null�̏ꍇ�́A�u�����N(���p�X�y�[�X)���Z�b�g����B
                    else
                    {
                        l_params.setItemContents(STR_BLANK);
                    }
                }
                else if (WEB3FeqItemNameDef.ORDER_COND_OPERATOR.equals(l_params.getItemName()))
                {
                    //�����P��.�����������Z�q == �h�ȏ�h �̏ꍇ�A�h�ȏ�h
                    if (WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.
                            equals(l_feqOrderUnitRow.getOrderCondOperator()))
                    {
                        l_params.setItemContents(WEB3FeqItemContentsDef.ABOVE_BASE_PRICE);
                    }
                    //�����P��.�����������Z�q == �h�ȉ��h �̏ꍇ�A�h�ȉ��h
                    else if (WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.
                            equals(l_feqOrderUnitRow.getOrderCondOperator()))
                    {
                        l_params.setItemContents(WEB3FeqItemContentsDef.BELOW_BASE_PRICE);
                    }
                    //null�̏ꍇ�́A�u�����N(���p�X�y�[�X)���Z�b�g����B
                    else
                    {
                        l_params.setItemContents(STR_BLANK);
                    }
                }
                else if (WEB3FeqItemNameDef.ORDER_COND_PRICE.equals(l_params.getItemName()))
                { 
                    DecimalFormat formatSOPrice = new DecimalFormat("###0.######");
                    String SOPrice = formatSOPrice.format(l_feqOrderUnitRow.getStopOrderPrice());
                    if (l_feqOrderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_params.setItemContents(STR_BLANK);
                    }
                    else
                    {
                        l_params.setItemContents(SOPrice);
                    }

                }
                else if (WEB3FeqItemNameDef.LIMIT_PRICE.equals(l_params.getItemName()))
                {
                    DecimalFormat formatWLPrice = new DecimalFormat("###0.######");
                    String WLPrice = formatWLPrice.format(l_feqOrderUnitRow.getWLimitPrice());
                    if (l_feqOrderUnitRow.getWLimitPriceIsNull())
                    {
                        l_params.setItemContents(STR_BLANK);
                    }
                    else
                    {
                        l_params.setItemContents(WLPrice);
                    }
                }
                
                //��������
				else if (WEB3FeqItemNameDef.ORDER_QUANTITY.equals(l_params.getItemName()))
				{
					DecimalFormat formatQuantity = new DecimalFormat("#,##0");
					l_params.setItemContents("" + formatQuantity.format(l_feqOrderUnitRow.getQuantity()));
				}
				
				//�����P��
				else if (WEB3FeqItemNameDef.PRICE.equals(l_params.getItemName()))
				{
					//�����P��.�w�l���J���}�ҏW���Đݒ�@@�����P��.�w�l=�O�̏ꍇ�́h���s�h��ݒ�
					DecimalFormat formatPrice = new DecimalFormat("###0.######");
					String limitPrice = formatPrice.format(l_feqOrderUnitRow.getLimitPrice());
					if(limitPrice.equals(WEB3OrderPriceDivDef.MARKET_PRICE))
					{
						l_params.setItemContents("���s");
					}
                    else
					{
                        l_params.setItemContents(limitPrice);
					}
				}
				//�����쐬���t
				else if (WEB3FeqItemNameDef.RECEIVED_DATE_TIME.equals(l_params.getItemName()))
				{
					 l_params.setItemContents(WEB3DateUtility.formatDate(l_orderActionRow.getCreatedTimestamp(),"yyyy�NMM��dd�� HH:mm"));
				}
				l_params.setMailId(l_orderActionRow.getOrderActionId());
                l_params.setLastUpdatedTimestamp(l_timeStamp);
				l_processor.doDeleteAllQuery(ExtMailProcTempRow.TYPE,l_strWhereExt,l_objWhereValueExt);
                l_processor.doInsertQuery(l_params);                   
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch(DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        } 
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (deleteTempMail) <BR>
     * ���[�����M�e���|�����e�[�u���A <BR>
     * ���[�����M�g���e���|�����e�[�u�����폜����B <BR>
     *  <BR>
     * �P�j���[�����M�e���|�����e�[�u���̍폜 <BR>
     *  <BR>
     * �P�|�P�j�ȉ��̏����Ń��[�����M�e���|�����e�[�u������f�[�^���擾����B <BR>
     *  <BR>
     *    [����] <BR>
     *    �،���ЃR�[�h = ����.�����P��.�،���ЃR�[�h <BR>
     *    ���M���[���敪 = "1001" <BR>
     *    ���[��ID = ����.��������.��������ID <BR>
     *  <BR>
     * �P�|�Q�j���[�����M�e���|�����e�[�u���̃��R�[�h��_���폜����B <BR>
     *  <BR>
     *    ���X�V���e�ɂ��ẮA <BR>
     * �@@DB�X�V�d�l�u�O������_���[�����M�e�[�u��.xls�v�Q�� <BR>
     *    ���������擾�ł����ꍇ�́A���ׂĂɂ��Ę_���폜���s���B <BR>
     *  <BR>
     * �Q�j���[�����M�g���e���|�����e�[�u���̍폜 <BR>
     *  <BR>
     * �Q�|�P�j�ȉ��̏����Ń��[�����M�g���e���|�����e�[�u������ <BR>
     *    �f�[�^���擾����B <BR>
     *  <BR>
     *    [����] <BR>
     *    �،���ЃR�[�h = ����.�����P��.�،���ЃR�[�h <BR>
     *    ���M���[���敪 = "1001" <BR>
     *    ���[��ID = ����.��������.��������ID <BR>
     *  <BR>
     * �Q�|�Q�j���[�����M�g���e���|�����e�[�u���̃��R�[�h��_���폜����B <BR>
     *  <BR>
     *    ���X�V���e�ɂ��ẮA <BR>
     *    DB�X�V�d�l�u�O������_���[�����M�g���e�[�u��.xls�v�Q�� <BR>
     *    �����ׂẴ��R�[�h�ɂ��Ę_���폜���s���B <BR>
     * @@param l_feqOrderUnit - (�����P��)
     * @@throws WEB3BaseException
     * @@roseuid 42A058A80093
     */
    protected void deleteTempMail(WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteTempMail(WEB3FeqOrderUnit l_feqOrderUnit) ";
        log.entering(STR_METHOD_NAME);
        Timestamp l_timeStamp =
            GtlUtils.getTradingSystem().getSystemTimestamp();
        // �P�j���[�����M�e���|�����e�[�u���̍폜 
        //  
        // �P�|�P�j�ȉ��̏����Ń��[�����M�e���|�����e�[�u������f�[�^���擾����B 
        //  
        //    [����] 
        //    �،���ЃR�[�h = ����.�����P��.�،���ЃR�[�h 
        //    ���M���[���敪 = "1001" 
        //    ���[��ID = ����.��������.��������ID 
        //  
        List l_lisMailProcTemp = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();

			//���������f�[�^���擾
			FeqOrderActionRow l_orderActionRow = null;
			l_orderActionRow = getFeqOrderAction(l_feqOrderUnit);
			FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
			Long l_prevMailId = null;

			String l_strWhere = "institution_code = ? and sendmail_div = ? and order_id = ?";
			Object[] l_objWhereValue = new Object[3];
			l_objWhereValue[0] = l_feqOrderUnitRow.getInstitutionCode();
			l_objWhereValue[1] = WEB3SendmailDivDef.FEQ_ORDER_ACCEPT;
			l_objWhereValue[2] = new Long(l_feqOrderUnitRow.getOrderUnitId());

            l_lisMailProcTemp = 
                l_processor.doFindAllQuery(MailProcTempRow.TYPE, l_strWhere, l_objWhereValue);
            // �P�|�Q�j���[�����M�e���|�����e�[�u���̃��R�[�h��_���폜����B 
            //  
            //    ���X�V���e�ɂ��ẮA 
            // �@@DB�X�V�d�l�u�O������_���[�����M�e�[�u��.xls�v�Q�� 
            //    ���������擾�ł����ꍇ�́A���ׂĂɂ��Ę_���폜���s���B 
            if ((l_lisMailProcTemp != null) && (l_lisMailProcTemp.size() != 0))
            {
                for (int i = 0; i < l_lisMailProcTemp.size(); i++)
                {
                    MailProcTempRow l_row = (MailProcTempRow)l_lisMailProcTemp.get(i);
                    MailProcTempParams l_params = new MailProcTempParams(l_row);
					l_prevMailId =  new Long(l_params.getMailId());
					l_params.setMailId(l_orderActionRow.getOrderActionId());
                    l_params.setDeleteFlag(BooleanEnum.TRUE.intValue());
                    l_params.setLastUpdatedTimestamp(l_timeStamp);
					l_processor.doDeleteAllQuery(MailProcTempRow.TYPE,l_strWhere,l_objWhereValue);
					l_processor.doInsertQuery(l_params);
                }
            }
            //  
            // �Q�j���[�����M�g���e���|�����e�[�u���̍폜 
            //  
            // �Q�|�P�j�ȉ��̏����Ń��[�����M�g���e���|�����e�[�u������ 
            //    �f�[�^���擾����B 
            //  
            //    [����] 
            //    �،���ЃR�[�h = ����.�����P��.�،���ЃR�[�h 
            //    ���M���[���敪 = "1001" 
            //    ���[��ID = ����.��������.��������ID 
            //  
            // �Q�|�Q�j���[�����M�g���e���|�����e�[�u���̃��R�[�h��_���폜����B 
            //  
            //    ���X�V���e�ɂ��ẮA 
            //    DB�X�V�d�l�u�O������_���[�����M�g���e�[�u��.xls�v�Q�� 
            //    �����ׂẴ��R�[�h�ɂ��Ę_���폜���s���B 

			String l_strWhereExt = "institution_code = ? and sendmail_div = ? and mail_id = ?";
			Object[] l_objWhereValueExt = new Object[3];
			l_objWhereValueExt[0] = l_feqOrderUnitRow.getInstitutionCode();
			l_objWhereValueExt[1] = WEB3SendmailDivDef.FEQ_ORDER_ACCEPT;
			l_objWhereValueExt[2] = l_prevMailId;

            List l_lisExtMailProcTemp = 
                l_processor.doFindAllQuery(ExtMailProcTempRow.TYPE, l_strWhereExt, l_objWhereValueExt);
            // �P�|�Q�j���[�����M�e���|�����e�[�u���̃��R�[�h��_���폜����B 
            //  
            //    ���X�V���e�ɂ��ẮA 
            // �@@DB�X�V�d�l�u�O������_���[�����M�e�[�u��.xls�v�Q�� 
            //    ���������擾�ł����ꍇ�́A���ׂĂɂ��Ę_���폜���s���B 
            if ((l_lisExtMailProcTemp != null) && (l_lisExtMailProcTemp.size() != 0))
            {
                for (int i = 0; i < l_lisExtMailProcTemp.size(); i++)
                {
                    ExtMailProcTempRow l_row = (ExtMailProcTempRow)l_lisExtMailProcTemp.get(i);
                    ExtMailProcTempParams l_params = new ExtMailProcTempParams(l_row);
                    l_params.setDeleteFlag(BooleanEnum.TRUE.intValue());
					//�����쐬���t
					if (WEB3FeqItemNameDef.RECEIVED_DATE_TIME.equals(l_params.getItemName()))
					{
						 l_params.setItemContents(WEB3DateUtility.formatDate(l_orderActionRow.getCreatedTimestamp(),"yyyy�NMM��dd�� HH:mm"));
					}

					l_params.setMailId(l_orderActionRow.getOrderActionId());
                    l_params.setLastUpdatedTimestamp(l_timeStamp);
					l_processor.doDeleteAllQuery(ExtMailProcTempRow.TYPE,l_strWhereExt,l_objWhereValueExt);
					l_processor.doInsertQuery(l_params);                   

                }
            }

        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch(DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
	/**
	 * (getFeqOrderAction) <BR>
	 * ���������f�[�^���擾����B <BR>
	 *  <BR>
	 * �P�j�ȉ��̏����Œ��������e�[�u������ <BR>
	 *    �f�[�^���擾����B <BR>
	 *  <BR>
	 *    [����] <BR>
	 *    ����ID = ����.�����P��.����ID <BR>
	 *    ��������ԍ� = ����.�����P��.���������ŏI�ʔ� <BR>
	 *  <BR>
	 * @@param l_feqOrderUnit - (�����P��)
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow
	 * @@throws WEB3BaseException
	 * @@roseuid 42A058A80093
	 */
	protected FeqOrderActionRow getFeqOrderAction(WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getFeqOrderAction";
		log.entering(STR_METHOD_NAME);
		FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();

		//���������f�[�^���擾
		List l_lisFeqOrderActionRows = null;
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException    
			long l_lngOrderId = l_feqOrderUnitRow.getOrderId();
			int l_intLastOrderActionSerialNo = l_feqOrderUnitRow.getLastOrderActionSerialNo();
            
			String l_strWhereAction = " order_id = ? and order_action_serial_no = ? ";

			Object[] l_objAction = {new Long(l_lngOrderId),
				new Integer(l_intLastOrderActionSerialNo)};
                
			l_lisFeqOrderActionRows = l_queryProcessor.doFindAllQuery(
				FeqOrderActionRow.TYPE, 
				l_strWhereAction,
				l_objAction);//DataNetworkException, DataQueryException


			if (l_lisFeqOrderActionRows == null || l_lisFeqOrderActionRows.isEmpty())
			{
				log.debug("�Y�����钍���P��ID�A���������ŏI�ʔԃf�[�^������܂���B");
				log.exiting(STR_METHOD_NAME);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80005,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			if (l_lisFeqOrderActionRows.size() > 1)
			{
				log.debug("�Y�����钍���P��ID�A���������ŏI�ʔԃf�[�^�͕s��������B");
				log.exiting(STR_METHOD_NAME);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80006,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
		}
		catch (DataQueryException l_ex)
		{
			log.error(l_ex.getMessage(), l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(), l_ex);
		}
		catch(DataNetworkException l_ex)
		{
			log.error(l_ex.getMessage(), l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(), l_ex);
		}

		FeqOrderActionRow l_orderActionRow = (FeqOrderActionRow) l_lisFeqOrderActionRows.get(0);
		log.exiting(STR_METHOD_NAME);
		
		return l_orderActionRow;
	}
}
@
