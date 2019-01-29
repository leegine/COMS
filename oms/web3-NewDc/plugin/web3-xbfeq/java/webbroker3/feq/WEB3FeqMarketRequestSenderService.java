head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqMarketRequestSenderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������s�ꃊ�N�G�X�g���M�T�[�r�X(WEB3FeqMarketRequestSenderService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  䈋�(���u) �V�K�쐬
                   2005/07/26 �����F(���u) ���r���[
                 �@@                   2006/09/18  ����(���u) �d�l�ύX�E���f��237�A238 �A239    
                   2006/10/08  ꎉ�(���u) �d�l�ύX�E�c�a�X�V�d�l 067 
                   2006/10/12 ���G��(���u) �o�O3074�̑Ή�
                   2006/11/22 �����(���u)�d�l�ύX�E�c�a�X�V�d�l 075
                   2006/12/04 �� �r(���u) �d�l�ύX�E ���f�� 308 309 �c�a�X�V�d�l 076
                   2007/01/09 �����(���u)�d�l�ύX�E ���f�� 328
*/
package webbroker3.feq;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqMarketRequestSenderService;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.market.messages.FeqChangeOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.market.messages.FeqNewOrderMarketRequestMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.feq.service.delegate.WEB3FeqMailSenderService;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.slebase.data.SleSendQDao;
import webbroker3.slebase.data.SleSendQParams;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������s�ꃊ�N�G�X�g���M�T�[�r�X) <BR>
 * �O�������s�ꃊ�N�G�X�g���M�T�[�r�X
 * 
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3FeqMarketRequestSenderService implements FeqMarketRequestSenderService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3FeqMarketRequestSenderService.class);

    
    /**
     * @@roseuid 42D0D3CE0148
     */
    public WEB3FeqMarketRequestSenderService() 
    {
     
    }
    
    /**
     * (�V�K�������M) <BR>
     * �isend(FeqNewOrderMarketRequestMessage)�̎����j <BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�i�s�ꃊ�N�G�X�g�j�V�K�������M�v �Q�� <BR>
     * @@param l_feqNewOrderRequest - (�V�K�������N�G�X�g���b�Z�[�W)
     * @@return MarketRequestSendResult
     * @@roseuid 4284571702A8
     */
    public MarketRequestSendResult send(FeqNewOrderMarketRequestMessage l_feqNewOrderRequest) 
    {
        final String STR_METHOD_NAME = "send(FeqNewOrderMarketRequestMessage l_feqNewOrderRequest) ";
        log.entering(STR_METHOD_NAME);
        if (l_feqNewOrderRequest == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
        
        //1.1 getFeqOrderUnitRow( )
        //�����P�ʂ̍s�I�u�W�F�N�g���擾����B
        FeqOrderUnitRow l_feqOrderUnitRow = l_feqNewOrderRequest.getFeqOrderUnitRow();
        
        //1.2 toOrderUnit(Row)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        WEB3FeqOrderUnit l_feqOrderUnit = 
            (WEB3FeqOrderUnit)l_orderManager.toOrderUnit(l_feqOrderUnitRow);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //1.3 get�s��( )
            WEB3GentradeMarket l_gentradeMarket = l_feqOrderUnit.getMarket();
            
            //1.4 is�V�X�e���A��( )
            boolean l_blnIsSystemLock = l_gentradeMarket.isSystemInterLock();
            
            //1.5  �i����t���[�F�@@is�V�X�e���A��() == false�i��A���j�̏ꍇ�j
            if (!l_blnIsSystemLock)
            {
                //1.5.1 create�V�K����Mail(FeqOrderUnit)
                WEB3FeqMailSenderService l_mailSenderService = 
                    (WEB3FeqMailSenderService)Services.getService(WEB3FeqMailSenderService.class);
                l_mailSenderService.createNewOrderMail(l_feqOrderUnit);
            }
            
            //1.6 �i����t���[�F�@@is�V�X�e���A��() == true�i�A���j�̏ꍇ�j
            else
            {
                //1.6.1 (�����L���[�f�[�^�o�^)
                //�����L���[�f�[�^�̓o�^���s���B
                //�ڍׂ́@@DB�X�V�d�l�u�V�K_�O�������SEND_Q�e�[�u���v�Q��
                SleSendQParams l_sleSendQParams = new SleSendQParams();
                
                //�L���[ID: �����̔Ԃ����l
                l_sleSendQParams.setQueueId(SleSendQDao.newPkValue());
                
                //�����^�C�v: �O�������P��.�����^�C�v
                l_sleSendQParams.setProductType(l_feqOrderUnit.getProductType());
                
                //�s��R�[�h: �O�������P��.�s��h�c�ɊY������s��.�s��R�[�h
                l_sleSendQParams.setMarketCode(l_gentradeMarket.getMarketCode());
                
                //�u���[�J�[: 
                //�O�������P��.�s��h�c�ɊY������s��v���t�@@�����X�́u�v���t�@@�����X���ږ��v��
                //"feq.sle.broker"���u���ږ��A�ԁv��1�́u�v���t�@@�����X�̒l�v
                int l_intValue = 1;
                MarketPreferencesRow l_marketPreferencesRow = null;
                String l_strName = WEB3MarketPreferencesNameDef.FEQ_SLE_BROKER;
                l_marketPreferencesRow = MarketPreferencesDao.findRowByPk(
                    l_gentradeMarket.getMarketId(),
                    l_strName,
                    l_intValue);
                
                l_sleSendQParams.setBrokerName(l_marketPreferencesRow.getValue());
                
                //�،���ЃR�[�h�F �O�������P��.�،���ЃR�[�h
                l_sleSendQParams.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());
                
                //���X�R�[�h: �O�������P��.���X�R�[�h
                l_sleSendQParams.setBranchCode(l_feqOrderUnit.getBranchCode());
                
                //�����R�[�h: �O�������P��.�����h�c�ɊY������O������.���n�����R�[�h
                WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)l_feqOrderUnit.getProduct();
                String l_strProductCode = l_feqProduct.getOffshoreProductCode();
                if (l_strProductCode != null)
                {
                    while (l_strProductCode.startsWith("0"))
                    {
                        l_strProductCode = l_strProductCode.replaceFirst("0", "");
                    }
                }
                l_sleSendQParams.setProductCode(l_strProductCode);
                
                //����ID: �O�������P��.����ID
                l_sleSendQParams.setOrderId(l_feqOrderUnit.getOrderId());
                
                //�����P��ID: �O�������P��.�����P��ID
                l_sleSendQParams.setOrderUnitId(l_feqOrderUnit.getOrderUnitId());
                
                //������: �O�������P��.������
                l_sleSendQParams.setBizDate(l_feqOrderUnit.getBizDate());
                
                //�I�y���[�^�^�C�v: 0:�V�K(�iSleSendqOpTypeEnum�ɂĒ�`)
                l_sleSendQParams.setOpType(SleSendqOpTypeEnum.NEW_ORDER);
                
                //�������: �O�������P��.�������
                l_sleSendQParams.setOrderType(l_feqOrderUnit.getOrderType());
                
                //�w�l: �O�������P��.�w�l
                if (!l_feqOrderUnitRow.getLimitPriceIsNull())
                {
                    l_sleSendQParams.setLimitPrice(l_feqOrderUnit.getLimitPrice());
                }

                //��������: �O�������P��.��������
                if(l_feqOrderUnitRow.getQuantityIsSet())
                {
                    l_sleSendQParams.setQuantity(l_feqOrderUnit.getQuantity());
                }

                //�A�J�E���gID: �O�������P��.����ID
                l_sleSendQParams.setAccountId(l_feqOrderUnit.getAccountId());
                
                //�A�J�E���g�R�[�h: �O�������P��.����ID�ɊY������ڋq.�����R�[�h
                MainAccount l_mainAccount = 
                    l_accountManager.getMainAccount(
                        l_feqOrderUnit.getAccountId());
                l_sleSendQParams.setAccountCode(l_mainAccount.getAccountCode());
                    
                //�T�u�A�J�E���gID: �O�������P��.�⏕����ID:
                l_sleSendQParams.setSubAccountId(l_feqOrderUnit.getSubAccountId());
                
                //�����敪:
                boolean l_blnIsTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null);
                SleSendqProcStatusEnum l_sleSendqProcStatusEnum = null;
                //������ԊǗ�.is�g���K���s()==true�̏ꍇ�A0:�����҂�
                if (l_blnIsTrigger)
                {
                    l_sleSendqProcStatusEnum = SleSendqProcStatusEnum.TODO;
                }
                //������ԊǗ�.is�g���K���s()==false�̏ꍇ�A7:�����M
                //�iSleSendqProcStatusEnum�ɂĒ�`�j
                else
                {
                    l_sleSendqProcStatusEnum = SleSendqProcStatusEnum.NOT_PROCESSED;
                }
                l_sleSendQParams.setStatus(l_sleSendqProcStatusEnum);
                
                //��M�m�F�t���O: 0:FALSE BooleanEnum�ɂĒ�`�j
                l_sleSendQParams.setConfirmedBySleRcvdQ(BooleanEnum.FALSE);
                
                //�^�p�R�[�h: �O�������P��.�^�p�R�[�h
                l_sleSendQParams.setOrderEmpCode(l_feqOrderUnit.getOrderEmpCode());
                
                //���ʃR�[�h: �O�������P��.���ʃR�[�h
                l_sleSendQParams.setOrderRequestNumber(
                    l_feqOrderUnitRow.getOrderRequestNumber());
                
                //�d�q���[�����M����: Null
                l_sleSendQParams.setSendProcessDateTime(null); 
                
                //�쐬���t: �o�^����sysdate ���ݒ肳���
                l_sleSendQParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //�X�V���t: �o�^����sysdate ���ݒ肳���
                l_sleSendQParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                l_queryProcessor.doInsertQuery(l_sleSendQParams);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
      }
        
      log.exiting(STR_METHOD_NAME);
      return DefaultMarketRequestSendResult.newSuccessResultInstance(0);
      
    }
    
    /**
     * (�����������M) <BR>
     * �isend(FeqChangeOrderMarketRequestMessage)�̎����j <BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�i�s�ꃊ�N�G�X�g�j�����������M�v �Q�� <BR>
     * @@param l_feqChangeOrderRequest - (�����������N�G�X�g���b�Z�[�W)
     * @@param l_blnIsMarketNoSend - (is�s�ꖢ���M) <BR>
     *  <BR>
     * �������������s�ꖢ���M�̏ꍇ��true�A <BR>
     * �������������s�ꑗ�M�ς̏ꍇ��false���w�肷��B  <BR>
     * false�̏ꍇ�ASONAR�֒�����ʒm����B  <BR>
     * 
     * @@return MarketRequestSendResult
     * @@roseuid 4284571702AA
     */
    public MarketRequestSendResult send(FeqChangeOrderMarketRequestMessage l_feqChangeOrderRequest, 
        boolean l_blnIsMarketNoSend)
    {
        final String STR_METHOD_NAME = "send(FeqChangeOrderMarketRequestMessage l_feqChangeOrderRequest, " +
                "boolean l_blnIsMarketNoSend)";
        log.entering(STR_METHOD_NAME);
        if (l_feqChangeOrderRequest == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
        
        //1.1 getOrderId( )
        long l_lngOrderId = l_feqChangeOrderRequest.getOrderId();
        
        //1.2 getFeqOrderUnitRow( )
        //�����P�ʂ̍s�I�u�W�F�N�g���擾����B
        FeqOrderUnitRow l_feqOrderUnitRow = l_feqChangeOrderRequest.getFeqOrderUnitRow();
        
        //1.3 toOrderUnit(Row)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        WEB3FeqOrderUnit l_feqOrderUnit = 
            (WEB3FeqOrderUnit)l_orderManager.toOrderUnit(l_feqOrderUnitRow);
        SleSendQParams l_sleSendQParams = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();    
            
            //1.4 get�s��( )
            WEB3GentradeMarket l_gentradeMarket = l_feqOrderUnit.getMarket();
            
            //1.5 is�V�X�e���A��( )
            boolean l_blnIsSystemLock = l_gentradeMarket.isSystemInterLock();
            
            //1.6 is�g���K���s(�������� : String)
            boolean l_blnIsTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null);
            
            //1.7 �i����t���[�F�@@is�V�X�e���A��() == false�i��A���j�̏ꍇ�j
            if (!l_blnIsSystemLock)
            {
                //1.5.1 create��������Mail(FeqOrderUnit)
                WEB3FeqMailSenderService l_mailSenderService = 
                    (WEB3FeqMailSenderService)Services.getService(WEB3FeqMailSenderService.class);
                l_mailSenderService.createChangeOrderMail(l_feqOrderUnit);
            }
            //1.8 �i����t���[�F�@@is�V�X�e���A��() == true�i�A���j�̏ꍇ�j
            else
            {
                //1.8.1 �i����t���[�F�@@����.is�s�ꖢ���M==true(�s�ꖢ���M)�̏ꍇ�j
                if (l_blnIsMarketNoSend)
                {
                    //�����L���[�f�[�^�̓o�^���s���B
                    //�ڍׂ́@@DB�X�V�d�l�u����_�O�������SEND_Q�e�[�u��.xls�v�̃V�[�g
                    //�u����_�O�������SEND_Q�e�[�u��(�s�ꖢ���M)�v�Q��
                    
                    //���@@����ID==�O�������P��.����ID���I�y���[�^�^�C�v=="�V�K"
                    //�������敪=="�����M"�f�[�^�����݂���ꍇ�A���Y���R�[�h���X�V�iUpdate�j����B
                    
                    String l_strWhere = " order_id = ? and op_type = ? and status = ? ";
                    Object[] l_objWhere = new Object[3];
                    l_objWhere[0] = l_feqOrderUnit.getOrderId() + "";
                    l_objWhere[1] = SleSendqOpTypeEnum.NEW_ORDER;
                    l_objWhere[2] = SleSendqProcStatusEnum.NOT_PROCESSED;
                    List l_lisSleSendQRow =  
                        l_queryProcessor.doFindAllQuery(
                            SleSendQRow.TYPE,
                            l_strWhere,
                            l_objWhere);
                    if (l_lisSleSendQRow != null && l_lisSleSendQRow.size() != 0)
                    {
                        SleSendQRow l_sleSendQRow = (SleSendQRow)l_lisSleSendQRow.get(0);
                        l_sleSendQParams = new SleSendQParams(l_sleSendQRow);
                       
                        //�w�l: �O�������P��.�w�l
                        if (!l_feqOrderUnitRow.getLimitPriceIsNull())
                        {
                            l_sleSendQParams.setLimitPrice(l_feqOrderUnit.getLimitPrice());
                        }
                        
                        //��������: �O�������P��.��������
                        if(l_feqOrderUnitRow.getQuantityIsSet())
                        {
                            l_sleSendQParams.setQuantity(l_feqOrderUnit.getQuantity());
                        } 
                        //�X�V���t: ���ݓ����iGtlUtils.getSystemTimestamp()�j
                        l_sleSendQParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        int l_updcount = l_queryProcessor.doUpdateQuery(l_sleSendQParams);
                        
                        if (l_updcount == 0)
                        {
                            // �X�V�s����0���������ꍇ�A�Ɩ��G���[�Ƃ���B
                            log.error("SLE_SEND_Q�̊Y�����R�[�h�ɑ΂�����������Ɏ��s���܂����B" + l_sleSendQParams);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_03104, 
                                    getClass().getName() + STR_METHOD_NAME);
                        }
                    }
                    else
                    {
                        // �V�K�������s�ꖢ���M�̒����ŁASEND_Q�ɑ��݂��Ȃ����͂��蓾�Ȃ��̂�
                        // �Ɩ��G���[�Ƃ���B
                        log.error("�����Ώۂ̐V�K�������R�[�h���ASLE_SEND_Q�ɑ��݂��܂���B" +
                            "[ order_id�F" + l_feqOrderUnit.getOrderId() + " ]");
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03104, 
                                getClass().getName() + STR_METHOD_NAME);
                    }
                }
                
                //1.8.2 �i����t���[�F�@@����.is�s�ꖢ���M == false(�s�ꑗ�M�ς�)�̏ꍇ�j
                else
                {
                    //1.8.2.1 �i�����L���[�f�[�^�o�^�j
                    //�����L���[�f�[�^�̓o�^���s���B
                    //�ڍׂ́@@DB�X�V�d�l�u����_�O�������SEND_Q�e�[�u��.xls�v�̃V�[�g
                    //�u����_�O�������SEND_Q�e�[�u��(�s�ꑗ�M��)�v�Q��
                    l_sleSendQParams = new SleSendQParams();
                    
                    //�L���[ID: �����̔Ԃ����l
                    l_sleSendQParams.setQueueId(SleSendQDao.newPkValue());
                    
                    //�����^�C�v: �O�������P��.�����^�C�v
                    l_sleSendQParams.setProductType(l_feqOrderUnit.getProductType());
                    
                    //�s��R�[�h: �O�������P��.�s��h�c�ɊY������s��.�s��R�[�h
                    l_sleSendQParams.setMarketCode(l_gentradeMarket.getMarketCode());
                    
                    //�u���[�J�[: 
                    //�O�������P��.�s��h�c�ɊY������s��v���t�@@�����X�́u�v���t�@@�����X���ږ��v��
                    //"feq.sle.broker"���u���ږ��A�ԁv��1�́u�v���t�@@�����X�̒l�v
                    int l_intValue = 1;
                    MarketPreferencesRow l_marketPreferencesRow = null;
                    String l_strName = WEB3MarketPreferencesNameDef.FEQ_SLE_BROKER;
                    l_marketPreferencesRow = MarketPreferencesDao.findRowByPk(
                        l_gentradeMarket.getMarketId(),
                        l_strName,
                        l_intValue);
                    l_sleSendQParams.setBrokerName(l_marketPreferencesRow.getValue());
                    
                    //�،���ЃR�[�h�F �O�������P��.�،���ЃR�[�h
                    l_sleSendQParams.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());
                    
                    //���X�R�[�h: �O�������P��.���X�R�[�h
                    l_sleSendQParams.setBranchCode(l_feqOrderUnit.getBranchCode());
                    
                    //�����R�[�h: �O�������P��.�����h�c�ɊY������O������.���n�����R�[�h
                    WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)l_feqOrderUnit.getProduct();
                    String l_strProductCode = l_feqProduct.getOffshoreProductCode();
                    if (l_strProductCode != null)
                    {
                        while (l_strProductCode.startsWith("0"))
                        {
                            l_strProductCode = l_strProductCode.replaceFirst("0", "");
                        }
                    }
                    l_sleSendQParams.setProductCode(l_strProductCode);
                    //����ID: �O�������P��.����ID
                    l_sleSendQParams.setOrderId(l_feqOrderUnit.getOrderId());
                    
                    //�����P��ID: �O�������P��.�����P��ID
                    l_sleSendQParams.setOrderUnitId(l_feqOrderUnit.getOrderUnitId());
                    
                    //������: �O�������P��.������
                    l_sleSendQParams.setBizDate(l_feqOrderUnit.getBizDate());
                    
                    //�I�y���[�^�^�C�v: 1:����(�iSleSendqOpTypeEnum�ɂĒ�`)
                    l_sleSendQParams.setOpType(SleSendqOpTypeEnum.CHANGE_ORDER);
                    
                    //�������: �O�������P��.�������
                    l_sleSendQParams.setOrderType(l_feqOrderUnit.getOrderType());
                    
                    //�w�l: �O�������P��.�w�l
                    if (!l_feqOrderUnitRow.getLimitPriceIsNull())
                    {
                        l_sleSendQParams.setLimitPrice(l_feqOrderUnit.getLimitPrice());
                    }
    
                    //��������: �O�������P��.�s�ꂩ��m�F�ς݂̐���
                    if(l_feqOrderUnitRow.getQuantityIsSet())
                    {
                        l_sleSendQParams.setQuantity(l_feqOrderUnit.getQuantity());
                    }
                    
                    //��������: �O�������P��.�s�ꂩ��m�F�ς݂̐���
                    if(!l_feqOrderUnitRow.getConfirmedQuantityIsNull())
                    {
                        l_sleSendQParams.setChangeQuantity(l_feqOrderUnit.getConfirmedQuantity());
                    }
                    
                    //�����w�l: �O�������P��.�s�ꂩ��m�F�ς݂̎w�l
                    if (!l_feqOrderUnitRow.getConfirmedPriceIsNull())
                    {
                        l_sleSendQParams.setChangeLimitPrice(l_feqOrderUnit.getConfirmedPrice());  
                    }
                   
                    //��萔��: �O�������P��.��萔��
                    if (!l_feqOrderUnitRow.getExecutedQuantityIsNull())
                    {
                        l_sleSendQParams.setAlreadyExecdQuantity(l_feqOrderUnitRow.getExecutedQuantity());
                    }
                    
                    //�A�J�E���gID: �O�������P��.����ID
                    l_sleSendQParams.setAccountId(l_feqOrderUnit.getAccountId());
                    
                    //�A�J�E���g�R�[�h: �O�������P��.����ID�ɊY������ڋq.�����R�[�h
                    MainAccount l_mainAccount = 
                        l_accountManager.getMainAccount(
                            l_feqOrderUnit.getAccountId());
                    l_sleSendQParams.setAccountCode(l_mainAccount.getAccountCode());
                        
                    //�T�u�A�J�E���gID: �O�������P��.�⏕����ID:
                    l_sleSendQParams.setSubAccountId(l_feqOrderUnit.getSubAccountId());
                    
                    //�����敪:
                    SleSendqProcStatusEnum l_sleSendqProcStatusEnum = null;
                    //������ԊǗ�.is�g���K���s()==true�̏ꍇ�A0:�����҂�
                    if (l_blnIsTrigger)
                    {
                        l_sleSendqProcStatusEnum = SleSendqProcStatusEnum.TODO;
                    }
                    //������ԊǗ�.is�g���K���s()==false�̏ꍇ�A7:�����M
                    //�iSleSendqProcStatusEnum�ɂĒ�`�j
                    else
                    {
                        l_sleSendqProcStatusEnum = SleSendqProcStatusEnum.NOT_PROCESSED;
                    }
                    l_sleSendQParams.setStatus(l_sleSendqProcStatusEnum);
                    
                    //��M�m�F�t���O: 0:FALSE BooleanEnum�ɂĒ�`�j
                    l_sleSendQParams.setConfirmedBySleRcvdQ(BooleanEnum.FALSE);
                    
                    //�^�p�R�[�h: �O�������P��.�^�p�R�[�h
                    l_sleSendQParams.setOrderEmpCode(l_feqOrderUnit.getOrderEmpCode());
                    
                    //���ʃR�[�h: �O�������P��.���ʃR�[�h
                    l_sleSendQParams.setOrderRequestNumber(
                        l_feqOrderUnitRow.getOrderRequestNumber());
                    
                    //�d�q���[�����M����: Null
                    l_sleSendQParams.setSendProcessDateTime(null);
                    
                    //�쐬���t: �o�^����sysdate ���ݒ肳���
                    l_sleSendQParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                    //�X�V���t: �o�^����sysdate ���ݒ肳���
                    l_sleSendQParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                    l_queryProcessor.doInsertQuery(l_sleSendQParams);
                }
                
                //1.9 �i����t���[�F�@@is�s��J�ǎ��ԑ�()�̖߂�l == false  
                //������.is�s�ꖢ���M == false�̏ꍇ�j
                boolean l_blnIsTradeOpenTimeZone =
                    WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
                if (!l_blnIsMarketNoSend && !l_blnIsTradeOpenTimeZone)
                {
                    //1.9.1 �����m��(long)
                    this.changeConfirm(l_lngOrderId);
                }
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
      }
      
      log.exiting(STR_METHOD_NAME);
      return DefaultMarketRequestSendResult.newSuccessResultInstance(0);
  }
    
    /**
     * (����������M) <BR>
     * �isend(CancelOrderMarketRequestMessage)�̎����j <BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�i�s�ꃊ�N�G�X�g�j����������M�v �Q�� <BR>
     * @@param l_cancelOrderRequest - (����������N�G�X�g���b�Z�[�W)
     * @@param l_blnIsMarketNoSend - (is�s�ꖢ���M) <BR>
     *  <BR>
     * ������������s�ꖢ���M�̏ꍇ��true�A <BR>
     * ������������s�ꑗ�M�ς̏ꍇ��false���w�肷��B  <BR>
     * false�̏ꍇ�ASONAR�֎����ʒm����B  <BR>
     * 
     * @@return MarketRequestSendResult
     * @@roseuid 428457170298
     */
    public MarketRequestSendResult send(CancelOrderMarketRequestMessage l_cancelOrderRequest, 
        boolean l_blnIsMarketNoSend)
    {
        final String STR_METHOD_NAME = "send(CancelOrderMarketRequestMessage l_cancelOrderRequest, " +
                "boolean l_blnIsMarketNoSend)";
        log.entering(STR_METHOD_NAME);
        if (l_cancelOrderRequest == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();   
            
            //1.1 getOrderId( )
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            WEB3FeqOrderManager l_orderManager = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            long l_lngOrderId = l_cancelOrderRequest.getOrderId();
            
            //1.2 get�����P��ByOrderId(long)�B
            WEB3FeqOrderUnit l_feqOrderUnit = 
                (WEB3FeqOrderUnit)l_orderManager.getOrderUnitByOrderId(
                    l_lngOrderId);
            FeqOrderUnitRow l_feqOrderUnitRow = 
                (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();

            SleSendQParams l_sleSendQParams = null;
            
            //1.3 get�s��( )
            WEB3GentradeMarket l_gentradeMarket = l_feqOrderUnit.getMarket();
            
            //1.4 is�V�X�e���A��( )
            boolean l_blnIsSystemLock = l_gentradeMarket.isSystemInterLock();
            
            //1.5 is�g���K���s(�������� : String)
            boolean l_blnIsTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null);
            
            //1.6 �i����t���[�F�@@is�V�X�e���A��() == false�i��A���j�̏ꍇ�j
            if (!l_blnIsSystemLock)
            {
                //1.6.1  create�������Mail(FeqOrderUnit)
                //��������̏������[�����M�e�[�u���A���[�����M�g���e�[�u���ɓo�^����B 
                //[create�������Mail()�Ɏw�肷�����] 
                //�@@�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g 
                WEB3FeqMailSenderService l_mailSenderService = 
                    (WEB3FeqMailSenderService)Services.getService(WEB3FeqMailSenderService.class);
                l_mailSenderService.createCancelOrderMail(l_feqOrderUnit);
            }
            //1.7 �i����t���[�F�@@is�V�X�e���A��() == true�i�A���j�̏ꍇ�j
            else
            {
                //1.7.1 �i����t���[�F�@@����.is�s�ꖢ���M==true(�s�ꖢ���M)�̏ꍇ�j
                if (l_blnIsMarketNoSend)
                {
                    //�����L���[�f�[�^�̓o�^���s���B
                    //�ڍׂ́@@DB�X�V�d�l�u���_�O�������SEND_Q�e�[�u��.xls�v�̃V�[�g
                    //�u���_�O�������SEND_Q�e�[�u��(�s�ꖢ���M)�v�Q��
                    
                    //���@@����ID==�O�������P��.����ID���I�y���[�^�^�C�v=="�V�K"
                    //�������敪=="�����M"�f�[�^�����݂���ꍇ�A���Y���R�[�h���X�V�iUpdate�j����B
                    
                    String l_strWhere = " order_id = ? and op_type = ? and status = ? ";
                    Object[] l_objWhere = new Object[3];
                    l_objWhere[0] = l_feqOrderUnit.getOrderId() + "";
                    l_objWhere[1] = SleSendqOpTypeEnum.NEW_ORDER;
                    l_objWhere[2] = SleSendqProcStatusEnum.NOT_PROCESSED;
                    List l_lisSleSendQRow =  
                        l_queryProcessor.doFindAllQuery(
                            SleSendQRow.TYPE,
                            l_strWhere,
                            l_objWhere);
                    if (l_lisSleSendQRow != null && l_lisSleSendQRow.size() != 0)
                    {
                        SleSendQRow l_sleSendQRow = (SleSendQRow)l_lisSleSendQRow.get(0);
                        l_sleSendQParams = new SleSendQParams(l_sleSendQRow);
                       
                        //�����敪: 8:�����ȗ�
                        l_sleSendQParams.setStatus(SleSendqProcStatusEnum.SKIP_PROCESSING_LOCAL);

                        //�X�V���t: ���ݓ����iGtlUtils.getSystemTimestamp()�j
                        l_sleSendQParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        
                        int l_updcount = l_queryProcessor.doUpdateQuery(l_sleSendQParams);
                        
                        if (l_updcount == 0)
                        {
                            // �X�V�s����0���������ꍇ�A�Ɩ��G���[�Ƃ���B
                            log.error("SLE_SEND_Q�̊Y�����R�[�h�ɑ΂����������Ɏ��s���܂����B" + l_sleSendQParams);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00394, 
                                    getClass().getName() + STR_METHOD_NAME);
                        }
                    }
                    else
                    {
                        // �V�K�������s�ꖢ���M�̒����ŁASEND_Q�ɑ��݂��Ȃ����͂��蓾�Ȃ��̂�
                        // �Ɩ��G���[�Ƃ���B
                        log.error("����Ώۂ̐V�K�������R�[�h���ASLE_SEND_Q�ɑ��݂��܂���B" +
                            "[ order_id�F" + l_feqOrderUnit.getOrderId() + " ]");
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00394, 
                                getClass().getName() + STR_METHOD_NAME);
                    }
                }
                
                //1.7.2 �i����t���[�F�@@����.is�s�ꖢ���M == false(�s�ꑗ�M�ς�)�̏ꍇ�j
                else
                {
                    //1.7.2.1 �i�����L���[�f�[�^�o�^�j
                    //�����L���[�f�[�^�̓o�^���s���B
                    //�ڍׂ́@@DB�X�V�d�l�u���_�O�������SEND_Q�e�[�u��.xls�v�̃V�[�g
                    //�u���_�O�������SEND_Q�e�[�u��(�s�ꑗ�M��)�v�Q��
                    l_sleSendQParams = new SleSendQParams();
                    
                    //�L���[ID: �����̔Ԃ����l
                    l_sleSendQParams.setQueueId(SleSendQDao.newPkValue());
                    
                    //�����^�C�v: �O�������P��.�����^�C�v
                    l_sleSendQParams.setProductType(l_feqOrderUnit.getProductType());
                    
                    //�s��R�[�h: �O�������P��.�s��h�c�ɊY������s��.�s��R�[�h
                    l_sleSendQParams.setMarketCode(l_gentradeMarket.getMarketCode());
                    
                    //�u���[�J�[: 
                    //�O�������P��.�s��h�c�ɊY������s��v���t�@@�����X�́u�v���t�@@�����X���ږ��v��
                    //"feq.sle.broker"���u���ږ��A�ԁv��1�́u�v���t�@@�����X�̒l�v
                    MarketPreferencesRow l_marketPreferencesRow = null;
                    int l_intValue = 1;
                    String l_strName = WEB3MarketPreferencesNameDef.FEQ_SLE_BROKER;
                    l_marketPreferencesRow = MarketPreferencesDao.findRowByPk(
                        l_gentradeMarket.getMarketId(),
                        l_strName,
                        l_intValue);
                    l_sleSendQParams.setBrokerName(l_marketPreferencesRow.getValue());
                    
                    //�،���ЃR�[�h�F �O�������P��.�،���ЃR�[�h
                    l_sleSendQParams.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());
                    
                    //���X�R�[�h: �O�������P��.���X�R�[�h
                    l_sleSendQParams.setBranchCode(l_feqOrderUnit.getBranchCode());
                    
                    //�����R�[�h: �O�������P��.�����h�c�ɊY������O������.���n�����R�[�h
                    WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)l_feqOrderUnit.getProduct();
                    String l_strProductCode = l_feqProduct.getOffshoreProductCode();
                    if (l_strProductCode != null)
                    {
                        while (l_strProductCode.startsWith("0"))
                        {
                            l_strProductCode = l_strProductCode.replaceFirst("0", "");
                        }
                    }
                    l_sleSendQParams.setProductCode(l_strProductCode);
                    //����ID: �O�������P��.����ID
                    l_sleSendQParams.setOrderId(l_feqOrderUnit.getOrderId());
                    
                    //�����P��ID: �O�������P��.�����P��ID
                    l_sleSendQParams.setOrderUnitId(l_feqOrderUnit.getOrderUnitId());
                    
                    //������: �O�������P��.������
                    l_sleSendQParams.setBizDate(l_feqOrderUnit.getBizDate());
                    
                    //�I�y���[�^�^�C�v: 2:���(�iSleSendqOpTypeEnum�ɂĒ�`)
                    l_sleSendQParams.setOpType(SleSendqOpTypeEnum.CANCEL_ORDER);
                    
                    //�������: �O�������P��.�������
                    l_sleSendQParams.setOrderType(l_feqOrderUnit.getOrderType());
                    
                    //�A�J�E���gID: �O�������P��.����ID
                    l_sleSendQParams.setAccountId(l_feqOrderUnit.getAccountId());
                    
                    //�A�J�E���g�R�[�h: �O�������P��.����ID�ɊY������ڋq.�����R�[�h
                    MainAccount l_mainAccount = 
                        l_accountManager.getMainAccount(
                            l_feqOrderUnit.getAccountId());
                    l_sleSendQParams.setAccountCode(l_mainAccount.getAccountCode());
                        
                    //�T�u�A�J�E���gID: �O�������P��.�⏕����ID:
                    l_sleSendQParams.setSubAccountId(l_feqOrderUnit.getSubAccountId());
                    
                    //�����敪:
                    SleSendqProcStatusEnum l_sleSendqProcStatusEnum = null;
                    //������ԊǗ�.is�g���K���s()==true�̏ꍇ�A0:�����҂�
                    if (l_blnIsTrigger)
                    {
                        l_sleSendqProcStatusEnum = SleSendqProcStatusEnum.TODO;
                    }
                    //������ԊǗ�.is�g���K���s()==false�̏ꍇ�A7:�����M
                    //�iSleSendqProcStatusEnum�ɂĒ�`�j
                    else
                    {
                        l_sleSendqProcStatusEnum = SleSendqProcStatusEnum.NOT_PROCESSED;
                    }
                    l_sleSendQParams.setStatus(l_sleSendqProcStatusEnum);
                    
                    //��M�m�F�t���O: 0:FALSE BooleanEnum�ɂĒ�`�j
                    l_sleSendQParams.setConfirmedBySleRcvdQ(BooleanEnum.FALSE);
                    
                    //�^�p�R�[�h: �O�������P��.�^�p�R�[�h
                    l_sleSendQParams.setOrderEmpCode(l_feqOrderUnit.getOrderEmpCode());
                    
                    //���ʃR�[�h: �O�������P��.���ʃR�[�h
                    l_sleSendQParams.setOrderRequestNumber(
                        l_feqOrderUnitRow.getOrderRequestNumber());
                    
                    //�d�q���[�����M����: Null
                    l_sleSendQParams.setSendProcessDateTime(null);  
                    
                    l_queryProcessor.doInsertQuery(l_sleSendQParams);
                }
                
                //1.8 �i����t���[�F�@@is�s��J�ǎ��ԑ�()�̖߂�l == false
                //������.is�s�ꖢ���M == false�̏ꍇ�j
                boolean l_blnIsTradeOpenTimeZone =
                    WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
                if (!l_blnIsMarketNoSend && !l_blnIsTradeOpenTimeZone)
                {
                    //1.8.1 ����m��(long)
                    this.cancelConfirm(l_lngOrderId);
                }
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
      }
      
      log.exiting(STR_METHOD_NAME);
      return DefaultMarketRequestSendResult.newSuccessResultInstance(0);
    }
    
    /**
     * (�����m��) <BR>
     * �����������m�肳����B <BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�i�s�ꃊ�N�G�X�g�j�����m��v �Q�� <BR>
     * @@param l_lngOrderId - (����ID)
     * @@throws WEB3BaseException
     * @@roseuid 429D6E220379
     */
    protected void changeConfirm(long l_lngOrderId) throws WEB3BaseException
    {       
        final String STR_METHOD_NAME = "changeConfirm(long l_lngOrderId) ";
        log.entering(STR_METHOD_NAME);
        //�P�D�P�O�����������m��X�V�C�x���g�C���^�Z�v�^( )
        //�C���^�Z�v�^�𐶐�����B
        WEB3FeqChangeConfirmUpdateInterceptor l_interceptor = new WEB3FeqChangeConfirmUpdateInterceptor();
        //�P�D�Q
        //setThreadLocalPersistenceEventInterceptor(arg0 : FeqOrderManagerPersistenceEventInterceptor)
        //�C���^�Z�v�^���Z�b�g����B

        //[����]
        //arg0�F �O�����������m��X�V�C���^�Z�v�^
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //�P�D�RDefaultChangeOrderAcceptedMarketResponseMessage(����ID : long)
        //�������ʁi���������j�I�u�W�F�N�g�𐶐�����B

        //[����]
        //�����h�c�F ����.����ID
        DefaultChangeOrderAcceptedMarketResponseMessage l_message = 
            new DefaultChangeOrderAcceptedMarketResponseMessage(l_lngOrderId);
        //�P�D�Sprocess(arg0 : ChangeOrderAcceptedMarketResponseMessage)
        //���������𒍕��ɍX�V����B

        //[����]
        //arg0�F �������ʃI�u�W�F�N�g
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        MarketResponseReceiverCallbackService l_service = 
            l_marketAdapter.getMarketResponseReceiverCallbackService();
        ProcessingResult l_result = l_service.process(l_message);
        if (l_result.isFailedResult())
        {
            log.debug("���������𒍕��ɍX�VFailed!" + l_result.getErrorInfo());
            throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (����m��) <BR>
     * ����������m�肳����B <BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�i�s�ꃊ�N�G�X�g�j����m��v �Q�� <BR>
     * @@param l_lngOrderId - (����ID)
     * @@throws WEB3BaseException
     * @@roseuid 429D7F02031C
     */
    protected void cancelConfirm(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "cancelConfirm(long l_lngOrderId) ";
        log.entering(STR_METHOD_NAME);
        //�P�D�P    �O����������m��X�V�C�x���g�C���^�Z�v�^( ) �C���^�Z�v�^�𐶐�����B
        WEB3FeqCancelConfirmUpdateInterceptor l_interceptor = 
            new WEB3FeqCancelConfirmUpdateInterceptor();
        //�P�D�Q    setThreadLocalPersistenceEventInterceptor(arg0 : FeqOrderManagerPersistenceEventInterceptor)
        //�C���^�Z�v�^���Z�b�g����B

        //[����]
        //arg0�F �O����������m��X�V�C���^�Z�v�^
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        //�P�D�R    DefaultCancelOrderAcceptedMarketResponseMessage(����ID : long)
        //������ʁi��������j�I�u�W�F�N�g�𐶐�����B

        //[����]
        //����ID�F ����.����ID
        DefaultCancelOrderAcceptedMarketResponseMessage l_message
            = new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);
        //�P�D�S    process(arg0 : CancelOrderAcceptedMarketResponseMessage)
        //��������𒍕��ɍX�V����B

        //[����]
        //arg0�F ������ʃI�u�W�F�N�g
    
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        MarketResponseReceiverCallbackService l_service = 
            l_marketAdapter.getMarketResponseReceiverCallbackService();
        ProcessingResult l_result = l_service.process(l_message);
        if (l_result.isFailedResult())
        {
            log.debug("��������𒍕��ɍX�VFailed!" + l_result.getErrorInfo());
            throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
        }

        log.exiting(STR_METHOD_NAME);
     
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSenderService#send(com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage)
     */
    public MarketRequestSendResult send(MarketRequestMessage arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }
}@
