head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.20.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����\�񒍕��X�V�T�[�r�XImpl(WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 �s�p (���u) �V�K�쐬 
Revesion History : 2007/4/25 ������ (���u) ���f��NO.232 
*/

package webbroker3.triggerorder.base.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvEqOrderActionParams;
import webbroker3.triggerorder.base.data.RsvEqOrderActionRow;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitDao;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����\�񒍕��X�V�T�[�r�XImpl)<BR>
 * �����\�񒍕��X�V�T�[�r�X�̎����N���X�B
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl implements WEB3ToSuccReservationEqTypeOrderUpdateService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl.class);
        
    /**
     * @@roseuid 4348D9800280
     */
    public WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl() 
    {
     
    }
    
    /**
     * (insert�\�񒍕�����)<BR>
     * �ŐV�̊����\�񒍕��P�ʃe�[�u���̓��e���A<BR>
     * �����\�񒍕��������P���R�[�h�쐬���o�^����B<BR>
     * <BR>
     * �P�j�@@�����\�񒍕��P�ʃe�[�u������Y�����R�[�h���擾����B<BR>
     * <BR>
     * �@@�����\�񒍕��P�ʃe�[�u�����A�����̒���ID�ɊY�����郌�R�[�h���擾����B<BR>
     * <BR>
     * �Q�j�@@�擾���������\�񒍕��P�ʃI�u�W�F�N�g���A�����\�񒍕�������<BR>
     * �P���R�[�h�o�^����B<BR>
     * <BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�u�i�A���j�������������o�^_�����\�񒍕������e�[�u��.xls�v<BR>
     * �@@���Q�ƁB
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B<BR>
     * �i�����\�񒍕��P��.����ID���Z�b�g�j
     * @@throws WEB3BaseException
     * @@roseuid 43378D0602A2
     */
    public void insertReserveOrderAction(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " insertReserveOrderAction(long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�P�j�@@�����\�񒍕��P�ʃe�[�u������Y�����R�[�h���擾����B
            //�����\�񒍕��P�ʃe�[�u�����A�����̒���ID�ɊY�����郌�R�[�h���擾����B
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = RsvEqOrderUnitDao.findRowByPk(l_lngOrderId);
            //DataFindException,DataNetworkException,DataQueryException
            
            //�Q�j�@@�擾���������\�񒍕��P�ʃI�u�W�F�N�g���A�����\�񒍕�������
            //�P���R�[�h�o�^����B
            RsvEqOrderActionParams l_rsvEqOrderActionParams = new RsvEqOrderActionParams();
            
            //�����h�c: �����\�񒍕��P�ʃe�[�u���̓�����
            l_rsvEqOrderActionParams.setAccountId(l_rsvEqOrderUnitRow.getAccountId());
                                                                                            
            //�⏕�����h�c: �����\�񒍕��P�ʃe�[�u���̓�����
            l_rsvEqOrderActionParams.setSubAccountId(l_rsvEqOrderUnitRow.getSubAccountId());
                                                                                                
            //����҂h�c: �����\�񒍕��P�ʃe�[�u���̓�����
            if (!l_rsvEqOrderUnitRow.getTraderIdIsNull())
            {
                l_rsvEqOrderActionParams.setTraderId(l_rsvEqOrderUnitRow.getTraderId());
            }
                                                                                            
            //�����h�c: �����\�񒍕��P�ʃe�[�u���̓�����                                                                                
            l_rsvEqOrderActionParams.setOrderId(l_rsvEqOrderUnitRow.getOrderId());
            
            //��������: �����\�񒍕��P�ʃe�[�u���̓�����                                                                                
            l_rsvEqOrderActionParams.setQuantity(l_rsvEqOrderUnitRow.getQuantity());
            
            //�w�l: �����\�񒍕��P�ʃe�[�u���̓�����      
            if (!l_rsvEqOrderUnitRow.getLimitPriceIsNull())   
            {
                l_rsvEqOrderActionParams.setLimitPrice(l_rsvEqOrderUnitRow.getLimitPrice());                                                   
            }            
            
            //�P�������l: �����\�񒍕��P�ʃe�[�u���̓�����      
            if (!l_rsvEqOrderUnitRow.getPriceAdjustValueIsNull()) 
            {
                l_rsvEqOrderActionParams.setPriceAdjustValue(l_rsvEqOrderUnitRow.getPriceAdjustValue());
            }                                                                                  
            
            //�����������t: �����\�񒍕��P�ʃe�[�u���̓�����                                                                                    
            l_rsvEqOrderActionParams.setExpirationDate(l_rsvEqOrderUnitRow.getExpirationDate());
            
            //�������: �����\�񒍕��P�ʃe�[�u���̓�����                                                                                
            l_rsvEqOrderActionParams.setOrderStatus(l_rsvEqOrderUnitRow.getOrderStatus());
            
            //�����L�����: �����\�񒍕��P�ʃe�[�u���̓�����                                                                                    
            l_rsvEqOrderActionParams.setOrderOpenStatus(l_rsvEqOrderUnitRow.getOrderOpenStatus());
            
            //�����敪: �����\�񒍕��P�ʃe�[�u���̓�����                                                                                
            l_rsvEqOrderActionParams.setExpirationStatus(l_rsvEqOrderUnitRow.getExpirationStatus());
            
            //��������ԍ�: �����\�񒍕��P�ʃe�[�u��.���������ŏI�ʔ�                                                                           
            l_rsvEqOrderActionParams.setOrderActionSerialNo(l_rsvEqOrderUnitRow.getLastOrderActionSerialNo());
            
            //�T�Z��n���: �����\�񒍕��P�ʃe�[�u���̓�����        
            if (!l_rsvEqOrderUnitRow.getEstimatedPriceIsNull())      
            {
                l_rsvEqOrderActionParams.setEstimatedPrice(l_rsvEqOrderUnitRow.getEstimatedPrice());                                                        
            }            
            
            //�����o�H�敪: �����\�񒍕��P�ʃe�[�u���̓�����                                                                                    
            l_rsvEqOrderActionParams.setOrderRootDiv(l_rsvEqOrderUnitRow.getOrderRootDiv());
            
            //�쐬���t: ���ݎ���                                                                                
            l_rsvEqOrderActionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //�X�V���t: �����\�񒍕��P�ʃe�[�u���̓�����
            l_rsvEqOrderActionParams.setLastUpdatedTimestamp(l_rsvEqOrderUnitRow.getLastUpdatedTimestamp());
            
			//IP�A�h���X: 
			// ���O�C���Z�L�����e�B�T�[�r�X���擾�\�ȏꍇ�F�@@�Z�b�V�������擾���������ڂ̒l
			// ���O�C���Z�L�����e�B�T�[�r�X���擾�s�ȏꍇ�F�@@null
			OpLoginSecurityService l_securityService = 
				(OpLoginSecurityService) Services.getService(
					OpLoginSecurityService.class);
            
			try 
			{
				String l_strIpAddress = 
					l_securityService.getSessionProperty(
						WEB3SessionAttributeDef.IP_ADDRESS);
				l_rsvEqOrderActionParams.setIpAddress(l_strIpAddress);
			} 
			catch (IllegalSessionStateException e) 
			{
				l_rsvEqOrderActionParams.setIpAddress(null);
			}
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            
            l_queryProcessor.doInsertQuery(l_rsvEqOrderActionParams);
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataFindException l_ex)
        {
            log.error("�����\�񒍕��P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                              
    }
    
    /**
     * (invalidate�\�񒍕��P��)<BR>
     * �iinvalidateOrderUnit�j<BR>
     * <BR>
     * �����\�񒍕��P�ʍs������������B<BR>
     * <BR>
     * �P�j�@@�����̊����\�񒍕��P�ʍs������������B�iupdate����j<BR>
     * <BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�u�A�����������iNG�j_�����\�񒍕��P�ʃe�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�����\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@this.insert�����\�񒍕�����(�����̊����\�񒍕��P�ʍs.����ID)��<BR>
     * �@@�R�[������B
     * @@param l_rsvEqOrderUnitRow - (�����\�񒍕��P�ʍs)<BR>
     * �����\�񒍕��P�ʍs�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩�����������B
     * @@param l_strErrorCode - (�����G���[�R�[�h)<BR>
     * �����G���[�R�[�h�B<BR>
     * �i�G���[�����̓��肪�\��ErrorInfo.error_code���Z�b�g�A<BR>
     * �����G���[�ȊO�Ŏ�������ꍇ�Anull���Z�b�g�j
     * @@throws WEB3BaseException
     * @@roseuid 4337904800BE
     */
    public void invalidateOrderUnit(
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow, 
        String l_strErrorCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " invalidateOrderUnit(RsvEqOrderUnitRow, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_rsvEqOrderUnitRow == null)
        {
            log.debug("�����\�񒍕��P�ʍs�I�u�W�F�N�g�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�����\�񒍕��P�ʍs�I�u�W�F�N�g�����݂��Ȃ��B");
        }
        
        try
        {
            //�P�j�@@�����̊����\�񒍕��P�ʍs������������B�iupdate����j
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = 
                new RsvEqOrderUnitParams(l_rsvEqOrderUnitRow);
            
            //���������ŏI�ʔ�: �i�����l�j�{�P
            int l_intLastOrderActionSerialNo = l_rsvEqOrderUnitParams.getLastOrderActionSerialNo() + 1;
            l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(l_intLastOrderActionSerialNo);
            
            //�������:
            //�����̔����G���[�R�[�h���w�肳��Ă���ꍇ�F
            //6:�������s�i�V�K�����j
            // �iOrderStatusEnum�ɂĒ�`�j
            //�����̔����G���[�R�[�h���w�肳��Ă��Ȃ��ꍇ�F�i�����l�j 
            if (!WEB3StringTypeUtility.isEmpty(l_strErrorCode)) 
            {
                l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            }
            
            //�����L�����:
            //2:�N���[�Y�iOrderOpenStatusEnum�ɂĒ�`�j
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            
            //�����敪:
            //3:�}�[�P�b�g���ہiOrderExpirationStatusEnum�ɂĒ�`�j
            l_rsvEqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            
            //�����G���[�R�[�h:
            //�����̔����G���[�R�[�h���w�肳��Ă���ꍇ�F
            //�����G���[�R�[�h���Z�b�g
            //�i* �G���[�����̓��肪�\��ErrorInfo.error_code���Z�b�g�j
            // �����̔����G���[�R�[�h���w�肳��Ă��Ȃ��ꍇ�F�i�����l�j
            if (!WEB3StringTypeUtility.isEmpty(l_strErrorCode)) 
            {
                l_rsvEqOrderUnitParams.setOrderErrorCode(l_strErrorCode);
            }
            
            //�X�V���t: ���ݓ���
            l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doUpdateQuery(l_rsvEqOrderUnitParams);
            
            //�Q�j�@@�����\�񒍕��������쐬����B
            //this.insert�����\�񒍕�����(�����̊����\�񒍕��P�ʍs.����ID)���R�[������B
            this.insertReserveOrderAction(l_rsvEqOrderUnitRow.getOrderId());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (invalidateAll�\�񒍕��P��)<BR>
     * �iinvalidateAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��P�ʂ�<BR>
     * �S�Ď���������B<BR>
     * �������������s���Ȃ������i�L���ȗ\�񒍕��Ȃ��j�ꍇ�A<BR>
     * �@@false��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * <BR>
     * �P�j�@@�L���Ȋ����\�񒍕��P�ʃ��R�[�h���擾����B<BR>
     * �@@�@@�@@this.get�L���\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B<BR>
     * �@@�@@�@@�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B<BR>
     * �@@�Q�|�P�j�@@this.invalidate�\�񒍕��P��(�����Ώۂ̗v�f, null)���R�[������B<BR>
     * <BR>
     * �R�j�@@true��ԋp����B<BR>
     * @@param l_lngParentOrderId - �i�e�����̒���ID�j<BR>
     * �e�����̒���ID�B<BR>
     * 
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4344AA59016F
     */
    public boolean invalidateAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " invalidateAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�L���Ȋ����\�񒍕��P�ʃ��R�[�h���擾����B
        List l_lisOrderUnitRows = this.getOpenReserveEqtypeOrderUnits(l_lngParentOrderId);
        
        //�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())        
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B
        //�Q�|�P�j�@@this.invalidate�\�񒍕��P��(�����Ώۂ̗v�f, null)���R�[������B
        int l_intCnt = l_lisOrderUnitRows.size();
        
        for (int i = 0; i < l_intCnt; i++)
        {
            this.invalidateOrderUnit((RsvEqOrderUnitRow)l_lisOrderUnitRows.get(i), null);
        }
        
        //�R�j�@@true��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (cancel�\�񒍕��P��)<BR>
     * �icancelOrderUnit�j<BR>
     * �����̊����\�񒍕��P�ʍs���������B<BR>
     * <BR>
     * �P�j�@@�����̊����\�񒍕��P�ʍs���������B�iupdate����j<BR>
     * <BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�u�i�A���j���������������_�����\�񒍕��P�ʃe�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�����\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@this.insert�\�񒍕�����(�����̊����\�񒍕��P�ʍs.����ID)���R�[������B<BR>
     * @@param l_rsvEqOrderUnitRow - (�����\�񒍕��P�ʍs)<BR>
     * �����\�񒍕��P�ʍs�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩�����������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433A1D6802AF
     */
    public void cancelOrderUnit(RsvEqOrderUnitRow l_rsvEqOrderUnitRow) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " cancelOrderUnit(RsvEqOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        if (l_rsvEqOrderUnitRow == null)
        {
            log.debug("�����\�񒍕��P�ʍs�I�u�W�F�N�g�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�����\�񒍕��P�ʍs�I�u�W�F�N�g�����݂��Ȃ��B");
        }
        
        //�P�j�@@�����̊����\�񒍕��P�ʍs���������B�iupdate����j
        RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams(l_rsvEqOrderUnitRow);
        
        try
        {
            //����҂h�c(trader_id) :
            //���O�C���Z�L�����e�B�T�[�r�X���擾�\�ȏꍇ�F
            //�Z�b�V��������擾�������O�C��ID�ɊY�����鈵��.�����ID
            //���擾�ł��Ȃ������ꍇ�́Anull���Z�b�g        
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);        
            long l_loginId = l_opLoginSecurityService.getLoginInfo().getLoginId();//IllegalSessionStateException
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
            Trader l_trader = null;
            
            try
            {
                l_trader = l_finObjMgr.getTraderByLoginId(l_loginId);
                l_rsvEqOrderUnitParams.setTraderId(l_trader.getTraderId());
            }
            catch (NotFoundException l_ex)
            {
                l_rsvEqOrderUnitParams.setTraderId(null);
            }
                        
            //�����o�H�敪(order_root_div) :
            //���O�C���Z�L�����e�B�T�[�r�X���擾�\�ȏꍇ�F�Z�b�V��������擾���������o�H�敪    
            l_rsvEqOrderUnitParams.setOrderRootDiv(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV)); 
        }
        catch (IllegalSessionStateException l_ex)
        {
            //����҂h�c(trader_id) :
            //���O�C���Z�L�����e�B�T�[�r�X���擾�s�ȏꍇ�F�i�����l�j
            //�����o�H�敪(order_root_div) :
            //���O�C���Z�L�����e�B�T�[�r�X���擾�s�ȏꍇ�F�i�����l�j
        } 
        
        //���������ŏI�ʔ�(last_order_action_serial_no) :�i�����l�j + 1
        l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(l_rsvEqOrderUnitParams.getLastOrderActionSerialNo() + 1);
        
        //�������(order_status) :14:�����ρi��������j
        l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
        
        //�����L�����(order_open_status) :2:�N���[�Y
        l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);        
        
        //�X�V���t(last_updated_timestamp) :���ݓ����iGtlUtils.getSystemTimestamp()�j
        l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doUpdateQuery(l_rsvEqOrderUnitParams);//DataNetworkException,DataQueryException
            
            //�Q�j�@@�����\�񒍕��������쐬����B
            //this.insert�\�񒍕�����(�����̊����\�񒍕��P�ʍs.����ID)���R�[������B
            this.insertReserveOrderAction(l_rsvEqOrderUnitRow.getOrderId());
                
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
    }
    
    /**
     * (cancelAll�\�񒍕��P��)<BR>
     * �icancelAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��P�ʂ�<BR>
     * �S�Ď������B<BR>
     * ������������s���Ȃ������i�L���ȗ\�񒍕��Ȃ��j�ꍇ�A<BR>
     * �@@false��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * <BR>
     * �P�j�@@�L���Ȋ����\�񒍕��P�ʃ��R�[�h���擾����B<BR>
     * �@@�@@�@@this.get�L���\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B<BR>
     * �@@�@@�@@�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B<BR>
     * �@@�Q�|�P�j�@@this.cancel�\�񒍕��P��(�����Ώۂ̗v�f)���R�[������B<BR>
     * <BR>
     * �R�j�@@true��ԋp����B<BR>
     * @@param l_lngParentOrderId - �i�e�����̒���ID�j<BR>
     * �e�����̒���ID�B<BR>
     * 
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 433A18D20109
     */
    public boolean cancelAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " cancelAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�L���Ȋ����\�񒍕��P�ʃ��R�[�h���擾����B
        //this.get�L���\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B
        List l_lisOrderUnitRows = this.getOpenReserveEqtypeOrderUnits(l_lngParentOrderId);
        
        //�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())        
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B
        //�Q�|�P�j�@@this.cancel�\�񒍕��P��(�����Ώۂ̗v�f)���R�[������B
        int l_intCnt = l_lisOrderUnitRows.size();
        
        for (int i = 0; i < l_intCnt; i++)
        {
            this.cancelOrderUnit((RsvEqOrderUnitRow)l_lisOrderUnitRows.get(i));
        }
        
        //�R�j�@@true��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (expire�\�񒍕��P��)<BR>
     * �iexpireOrderUnit�j<BR>
     * �����̊����\�񒍕��P�ʍs������������B<BR>
     * <BR>
     * �P�j�@@�����̊����\�񒍕��P�ʍs������������B�iupdate����j<BR>
     * <BR>
     * �@@�@@DB�X�V�d�l<BR>
     * �@@�@@�u���������o���I���ʒm_�����\�񒍕��P�ʃe�[�u��.xls�v<BR>
     * �@@�@@���Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�����\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�@@�@@this.insert�\�񒍕�����(�����̊����\�񒍕��P�ʍs.����ID)���R�[������B<BR>
     * @@param l_rsvEqOrderUnitRow - (�����\�񒍕��P�ʍs)<BR>
     * �����\�񒍕��P�ʍs�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩�����������B<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 433A20720399
     */
    public void expireOrderUnit(RsvEqOrderUnitRow l_rsvEqOrderUnitRow) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " expireOrderUnit(RsvEqOrderUnitRow)";
        log.entering(STR_METHOD_NAME);     
        
        if (l_rsvEqOrderUnitRow == null)
        {
            log.debug("�����\�񒍕��P�ʍs�I�u�W�F�N�g�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�����\�񒍕��P�ʍs�I�u�W�F�N�g�����݂��Ȃ��B");
        }  
        
        try
        {
            //�P�j�@@�����̊����\�񒍕��P�ʍs������������B�iupdate����j
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams(l_rsvEqOrderUnitRow);
        
            //���������ŏI�ʔ�(last_order_action_serial_no) :�i�����l�j�{�P
            l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(
                l_rsvEqOrderUnitParams.getLastOrderActionSerialNo() + 1);
        
            //�����L�����(order_open_status) : 2:�N���[�Y�iOrderOpenStatusEnum�ɂĒ�`�j
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        
            //�����敪(expiration_status) : 2:�I���iOrderExpirationStatusEnum�ɂĒ�`�j 
            l_rsvEqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
             
            //�X�V���t(last_updated_timestamp) : ���ݓ����iGtlUtils.getSystemTimestamp()�j
            l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doUpdateQuery(l_rsvEqOrderUnitParams);//DataNetworkException,DataQueryException
            
            //�Q�j�@@�����\�񒍕��������쐬����B
            //this.insert�\�񒍕�����(�����̊����\�񒍕��P�ʍs.����ID)���R�[������B
            this.insertReserveOrderAction(l_rsvEqOrderUnitRow.getOrderId());
                
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
    }
    
    /**
     * (expireAll�\�񒍕��P��)<BR>
     * �iexpireAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��P�ʂ�<BR>
     * �S�Ď���������B<BR>
     * �������������s���Ȃ������i�L���ȗ\�񒍕��Ȃ��j�ꍇ�A<BR>
     * �@@false��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * <BR>
     * �P�j�@@�L���Ȋ����\�񒍕��P�ʃ��R�[�h���擾����B<BR>
     * �@@�@@�@@this.get�L���\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B<BR>
     * �@@�@@�@@�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B<BR>
     * �@@�Q�|�P�j�@@this.expire�\�񒍕��P��(�����Ώۂ̗v�f)���R�[������B<BR>
     * <BR>
     * �R�j�@@true��ԋp����B<BR>
     * @@param l_lngParentOrderId - �i�e�����̒���ID�j<BR>
     * �e�����̒���ID�B<BR>
     * 
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 433A2072039B
     */
    public boolean expireAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " expireAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�L���Ȋ����\�񒍕��P�ʃ��R�[�h���擾����B
        //this.get�L���\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B
        List l_lisOrderUnitRows = this.getOpenReserveEqtypeOrderUnits(l_lngParentOrderId);
        
        //�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())        
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B
        //�Q�|�P�j�@@this.expire�\�񒍕��P��(�����Ώۂ̗v�f)���R�[������B
        int l_intCnt = l_lisOrderUnitRows.size();
        
        for (int i = 0; i < l_intCnt; i++)
        {
            this.expireOrderUnit((RsvEqOrderUnitRow)l_lisOrderUnitRows.get(i));
        }
        
        //�R�j�@@true��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (get�L���\�񒍕��P�ʈꗗ)<BR>
     * �igetOpenReserveEqtypeOrderUnits�j<BR>
     * <BR>
     * �w�肳�ꂽ�e�����ɕR�t���A�L���Ȋ����\�񒍕��P�ʍs�̔z���ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�@@�ȉ��̏������w�肵�āA�y�����\�񒍕��P�ʃe�[�u���z����������B<BR>
     * <BR>
     * �@@�@@------------------------------- <BR>
     * �@@�@@������������<BR>
     * <BR>
     * �@@�@@�@@�@@�e�����̒���ID = ����.�e�����̒���ID <BR>
     * �@@�@@���@@�����L����� = "�I�[�v��"<BR>
     * <BR>
     * �@@�@@���u�e�������A�ԁv�ŏ����\�[�g�w�肷��B<BR>
     * �@@�@@------------------------------- <BR>
     * <BR>
     * �@@�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 433A1C640157
     */
    public List getOpenReserveEqtypeOrderUnits(long l_lngParentOrderId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOpenReserveEqtypeOrderUnits(long)";
        log.entering(STR_METHOD_NAME);
        
        try 
        {
            //�P�j�@@DB����
            //�ȉ��̏������w�肵�āA�y�����\�񒍕��P�ʃe�[�u���z����������B
            String l_strWhere = " parent_order_id = ? and order_open_status = ? ";                
            Object[] l_objs = {new Long(l_lngParentOrderId), OrderOpenStatusEnum.OPEN};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
              
            List l_lisRsvEqOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvEqOrderUnitRow.TYPE, 
                    l_strWhere, 
                    "serial_no_in_parent asc",
                    null,
                    l_objs);//DataNetworkException,DataQueryException
            
            if (l_lisRsvEqOrderUnitRows != null && !l_lisRsvEqOrderUnitRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                
                //�Q�j�@@�������ʂ�ԋp����B
                return l_lisRsvEqOrderUnitRows;
            }

            log.exiting(STR_METHOD_NAME);
            
            //�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
            return null;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (deleteAll�\�񒍕��P��)<BR>
     * �ideleteAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��f�[�^��<BR>
     * �S�č폜����B<BR>
     * �������J�z�X�L�b�v�����ʒm�T�[�r�X�ɂĎg�p�̂��߁A<BR>
�@@   *   �������n�̏ꍇ�̍l���͂Ȃ��i�������n�͓������蒍���̂݁j<BR>
     * ���폜�������s���Ȃ������i�\�񒍕��Ȃ��j�ꍇ�A<BR>
     * �@@false��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * ���f�[�^�x�[�Xdelete�́A�S�ăN�G���v���Z�b�T���g�p���A<BR>
     * �@@SQL���𔭍s���邱�Ƃōs���B<BR>
     * <BR>
     * �P�j�@@�����\�񒍕��P�ʃ��R�[�h���擾����B<BR>
     * �@@�@@�@@this.get�\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)<BR>
     * �@@�@@�@@���R�[������B<BR>
     * �@@�@@�@@�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�Y���\�񒍕����������ɑ΂���ԍϒ����̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�i�����Ώۂ̗v�f.�A����������敪=="�M�p�ԍρi�����c�j"�j�A<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�\�񌚊��ԍώw����f�[�^��<BR>
     * �@@�@@�@@�@@�@@�@@�y�����\�񌚊��ԍώw����e�[�u���z���delete����B<BR>
     * �@@�@@�@@�@@�@@�@@�폜�L�[�ɂ́A�����Ώۂ̗v�f.����ID ���w�肷��B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�\�񒍕������f�[�^��<BR>
     * �@@�@@�@@�@@�@@�@@�y�����\�񒍕������e�[�u���z���delete����B<BR>
     * �@@�@@�@@�@@�@@�@@�폜�L�[�ɂ́A�����Ώۂ̗v�f.����ID ���w�肷��B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�\�񒍕��P�ʂ̃f�[�^��<BR>
     * �@@�@@�@@�@@�@@�@@�y�����\�񒍕��P�ʃe�[�u���z���delete����B<BR>
     * �@@�@@�@@�@@�@@�@@�폜�L�[�ɂ́A�����Ώۂ̗v�f.����ID ���w�肷��B<BR>
     * <BR>
     * �R�j�@@true��ԋp����B<BR>
     * @@param l_lngParentOrderId - �i�e�����̒���ID�j<BR>
     * �e�����̒���ID�B<BR>
     * 
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4355E2000186
     */
    public boolean deleteAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�P�j�@@�����\�񒍕��P�ʃ��R�[�h���擾����B
            //this.get�\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)
            List l_lisOrderUnitRows = this.getReserveEqtypeOrderUnits(l_lngParentOrderId);
            
            //�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B
            if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())        
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
                 
            //�Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B   
            int l_intCnt = l_lisOrderUnitRows.size();
            
            for (int i = 0; i < l_intCnt; i++)
            {
                RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_lisOrderUnitRows.get(i);
                
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                //DataNetworkException,DataQueryException
                
                String l_strWhere = " order_id = ? ";                
                Object[] l_objs = {new Long(l_rsvEqOrderUnitRow.getOrderId())};  
                
                //�Q�|�P�j�@@�Y���\�񒍕����������ɑ΂���ԍϒ����̏ꍇ
                //�i�����Ώۂ̗v�f.�A����������敪=="�M�p�ԍρi�����c�j"�j�A
                if (WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_EXISTING_REMAINDER.equals(
                        l_rsvEqOrderUnitRow.getReserveOrderTradingType()))
                {
                    //�\�񌚊��ԍώw����f�[�^��
                    //�y�����\�񌚊��ԍώw����e�[�u���z���delete����B
                    //�폜�L�[�ɂ́A�����Ώۂ̗v�f.����ID ���w�肷��B                    
                    l_queryProcessor.doDeleteAllQuery(
                        RsvEqClosingContractSpecRow.TYPE, 
                        l_strWhere, 
                        l_objs);//DataNetworkException,DataQueryException                    
                }    
                
                //�Q�|�Q�j�@@�\�񒍕������f�[�^��
                //�y�����\�񒍕������e�[�u���z���delete����B
                //�폜�L�[�ɂ́A�����Ώۂ̗v�f.����ID ���w�肷��B                     
                l_queryProcessor.doDeleteAllQuery(
                    RsvEqOrderActionRow.TYPE, 
                    l_strWhere, 
                    l_objs);//DataNetworkException,DataQueryException
                
                //�Q�|�R�j�@@�\�񒍕��P�ʂ̃f�[�^��
                //�y�����\�񒍕��P�ʃe�[�u���z���delete����B
                //�폜�L�[�ɂ́A�����Ώۂ̗v�f.����ID ���w�肷��B
                l_queryProcessor.doDeleteAllQuery(
                    RsvEqOrderUnitRow.TYPE, 
                    l_strWhere, 
                    l_objs);//DataNetworkException,DataQueryException
            }  
            
            //�R�j�@@true��ԋp����B    
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get�\�񒍕��P�ʈꗗ)<BR>
     * �igetReserveEqtypeOrderUnits�j<BR>
     * <BR>
     * �w�肳�ꂽ�e�����ɕR�t���A�����\�񒍕��P�ʍs�̔z���ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�@@�ȉ��̏������w�肵�āA�y�����\�񒍕��P�ʃe�[�u���z����������B<BR>
     * <BR>
     * �@@�@@------------------------------- <BR>
     * �@@�@@������������<BR>
     * <BR>
     * �@@�@@�@@�@@�e�����̒���ID = ����.�e�����̒���ID <BR>
     * <BR>
     * �@@�@@���u�e�������A�ԁv�ŏ����\�[�g�w�肷��B<BR>
     * �@@�@@------------------------------- <BR>
     * <BR>
     * �@@�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getReserveEqtypeOrderUnits(long l_lngParentOrderId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getReserveEqtypeOrderUnits(long)";
        log.entering(STR_METHOD_NAME);
        
        try 
        {
            //�P�j�@@DB����
            //�ȉ��̏������w�肵�āA�y�����\�񒍕��P�ʃe�[�u���z����������B
            String l_strWhere = " parent_order_id = ?";                
            Object[] l_objs = {new Long(l_lngParentOrderId)};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
              
            List l_lisRsvEqOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvEqOrderUnitRow.TYPE, 
                    l_strWhere, 
                    "serial_no_in_parent asc",
                    null,
                    l_objs);
            
            if (l_lisRsvEqOrderUnitRows != null && !l_lisRsvEqOrderUnitRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                
                //�Q�j�@@�������ʂ�ԋp����B
                return l_lisRsvEqOrderUnitRows;
            }

            log.exiting(STR_METHOD_NAME);
            
            //�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
            return null;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (update�\�񒍕��f�[�^)<BR>
     * (updateReserveOrderData)<BR>
     * <BR>
     * �w�肳�ꂽ�����I�u�W�F�N�g���g�p���AQueryProcessor�ɂ��\�񒍕��f�[�^�ނ̍X�V���s���B<BR>
     * <BR>
     * �P�j update�����̊����\�񒍕��P��
     *     �����̊����\�񒍕��P��Row�I�u�W�F�N�g�̓��e�� �����\�񒍕��P�ʃe�[�u����update����B<BR>
     * �Q�j insert�����̊����\�񒍕�����
     *     �����̊����\�񒍕�����Row��"null"�łȂ��ꍇ�̂݁A  <BR>
�@@   *     �����̊����\�񒍕�����Row�I�u�W�F�N�g�̓��e�� <BR>
�@@   *     �����\�񒍕������e�[�u����insert����B  <BR>
     * <BR>
     * @@param l_rsvEqOrderUnitRow - (�����\�񒍕��P�ʍs)<BR>
     * �����\�񒍕��P�ʍs�B
     * @@param l_rsvEqOrderActionRow - (�����\�񒍕������s)<BR>
     * �����\�񒍕������s�B
     * @@throws WEB3BaseException
     */
    public void updateReserveOrderData(RsvEqOrderUnitRow l_rsvEqOrderUnitRow, 
        RsvEqOrderActionRow l_rsvEqOrderActionRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateReserveOrderData(RsvEqOrderUnitRow, RsvEqOrderActionRow)";
        log.entering(STR_METHOD_NAME);
        try 
        {
            // �f�[�^���X�V����
            QueryProcessor processor = Processors.getDefaultProcessor();
            // �P�j update�����̊����\�񒍕��P��
            //P1�����h�c  P2�e�����̒����h�c P3�e�������A��
            String l_strWhere = "order_id = ? ";
            Object[] l_objUpdWhere = {
                new Long(l_rsvEqOrderUnitRow.getOrderId())
                };
            processor.doUpdateQuery(
                l_rsvEqOrderUnitRow, 
                l_strWhere, 
                l_objUpdWhere
                );
            // �Q�j insert�����̊����\�񒍕�����
            if (l_rsvEqOrderActionRow != null)
            {
                processor.doInsertQuery(l_rsvEqOrderActionRow);                
            }
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);            
        }
        catch (DataQueryException l_ex) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);            
        }
    }
}
@
