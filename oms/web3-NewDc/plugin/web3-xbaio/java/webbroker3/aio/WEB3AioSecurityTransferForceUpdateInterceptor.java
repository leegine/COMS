head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.27.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecurityTransferForceUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֋����X�V�C���^�Z�v�^(WEB3AioSecurityTransferForceUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 ���z (���u) �V�K�쐬 
*/

package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�،��U�֋����X�V�C���^�Z�v�^)<BR>
 * �،��U�֋����X�V�C���^�Z�v�^�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferForceUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{
    /**
    * ���O���[�e�B���e�B
    */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferForceUpdateInterceptor.class);
    
    /**
     * (���o���������e)<BR>
     * ���o���������e
     */
    protected WEB3AioNewOrderSpec cashTransOrderSpec;
    
    /**
     * (������)<BR>
     * ������
     */
    protected Date bizDate;
    
    /**
     * (��n��)<BR>
     * ��n��
     */
    protected Date deliveryDate;
    
    /**
     * (�ŋ敪)<BR>
     * �ŋ敪
     */
    protected TaxTypeEnum taxType;
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h
     */
    protected String orderRequestNumber;
    
    /**
     * @@roseuid 41B0458B01F4
     */
    public WEB3AioSecurityTransferForceUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�،��U�֋����X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�����̓��o���������e���v���p�e�B�ɃZ�b�g����B
     * @@param l_securityTransferForceUpdateInterceptor - ���o���������e�I�u�W�F�N�g
     * @@return webbroker3.aio.WEB3AioSecurityTransferForceUpdateInterceptor
     * @@roseuid 416CAF1A02D0
     */
    public WEB3AioSecurityTransferForceUpdateInterceptor(WEB3AioNewOrderSpec l_aioNewOrderSpec) 
    {      
        //�C���X�^���X�𐶐����A�����̓��o���������e���v���p�e�B�ɃZ�b�g����B
        this.cashTransOrderSpec = l_aioNewOrderSpec; 
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB <BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�،��U�֋���_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE<BR>
     * <BR>
     * �iOrderManagerPersistenceType�ɂĒ萔��`�B�j
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_aioOrderUnitParams - (�����P��Params)<BR>
     * �����P�ʂ̃p�����[�^�N���X
     * @@return AioOrderUnitParams
     * @@roseuid 416CAF1A02D2
     */
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType, 
        OrderManagerPersistenceContext l_orderManagerPersistenceContext, 
        AioOrderUnitParams l_aioOrderUnitParams) 
    {
        String l_strMethodName = 
            "mutate(" +
            "OrderManagerPersistenceType l_orderManagerPersistenceType, " +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "AioOrderUnitParams l_aioOrderUnitParams)";
        log.entering(l_strMethodName);
        
        if (l_aioOrderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);            
        }
        
        //1) �����J�e�S��(14�F�،��U��)
        l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET_TRANSFER);
        
        //2) ������(�C���^�Z�v�^.������)
        l_aioOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.bizDate, "yyyyMMdd"));
                    
        //3) ��n��(�C���^�Z�v�^.��n��)
        l_aioOrderUnitParams.setDeliveryDate(this.deliveryDate);
        
        //4) �ŋ敪(�C���^�Z�v�^.�ŋ敪)
        l_aioOrderUnitParams.setTaxType(this.taxType);
        
        //5) �󒍓���
        //ThreadLocalSystemAttributesRegistry.getAttributes(�hxblocks.gtl.attributes.systemtimestamp�h)�̖߂�l
        l_aioOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
        //6) ���҃R�[�h�iSONAR�j(�ڋq.���҃R�[�h)
        
        //a> accountId
        long l_lngAccountId = l_aioOrderUnitParams.getAccountId();
        
        //b> FinApp, AccountManager, MainAccount(Row)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();            
        MainAccount l_mainAccount;
        try
        {
            //throw NotFoundException
            l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            
            //c> MainAccountRow
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
            //d> �ڋq.���҃R�[�h
            String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
        
            //set���҃R�[�h
            l_aioOrderUnitParams.setSonarTraderCode(l_strSonarTraderCode);        
        }
        catch (NotFoundException l_ex)
        {
            log.error("__�Y������ڋq�I�u�W�F�N�g������܂���__", l_ex); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
            
        //7) ���ʃR�[�h(�C���^�Z�v�^.���ʃR�[�h)
        l_aioOrderUnitParams.setOrderRequestNumber(orderRequestNumber);
        
        //8) .com�f�r�b�g���ώ���ԍ�(null)
        l_aioOrderUnitParams.setComDebitNumber(null);
        
        //9) �ۏ؋��敪(null)
        l_aioOrderUnitParams.setGuaranteeDiv(null);
        
        //10) �o���\���敪(null)
        l_aioOrderUnitParams.setPaymentApplicationDiv(null);
        
        //11) ��������敪(0�F�����l)
        l_aioOrderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        
        //12) �����o�H�敪(9�FHOST)
        l_aioOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST); 
        
        //13) �����G���[���R�R�[�h(0000�F����)
        l_aioOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        //14) MQ�X�e�[�^�X(1:���M��)
        l_aioOrderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
        
        log.exiting(l_strMethodName);
        
        return l_aioOrderUnitParams;
    }
    
    /**
     * (set������)<BR>
     * ���������Z�b�g����B
     * @@param l_datOrderBizDate - ������
     * @@roseuid 416CAF1A02D6
     */
    public void setOrderBizDate(Date l_datOrderBizDate) 
    {
        String l_strMethodName = "setOrderBizDate(Date l_datOrderBizDate)";
        log.entering(l_strMethodName);
       
        this.bizDate = l_datOrderBizDate; 
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set��n��)<BR>
     * ��n�����Z�b�g����B
     * @@param l_datDeliveryDate - ��n��
     * @@roseuid 416CAF1A02E0
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        String l_strMethodName = "setDeliveryDate(Date l_datDeliveryDate)";
        log.entering(l_strMethodName);
        
        this.deliveryDate = l_datDeliveryDate; 
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set���ʃR�[�h)<BR>
     * ���ʃR�[�h���Z�b�g����B
     * @@param l_strOrderRequestNumber - ���ʃR�[�h
     * @@roseuid 416CAF1A02E2
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber) 
    {
        String l_strMethodName = "setOrderRequestNumber(String l_strOrderRequestNumber)";
        log.entering(l_strMethodName);
        
        this.orderRequestNumber = l_strOrderRequestNumber; 
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set�ŋ敪)<BR>
     * �ŋ敪���Z�b�g����B
     * @@param l_taxType - �ŋ敪
     * @@roseuid 416CB9CB02C0
     */
    public void setTaxType(TaxTypeEnum l_taxType) 
    {
        String l_strMethodName = "setTaxType(TaxTypeEnum l_taxType)";
        log.entering(l_strMethodName);        
        
        this.taxType = l_taxType;
        
        log.exiting(l_strMethodName);
    }
}
@
