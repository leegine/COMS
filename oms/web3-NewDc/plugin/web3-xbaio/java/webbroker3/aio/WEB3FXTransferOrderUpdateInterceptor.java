head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTransferOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�U�֒����X�V�C���^�Z�v�^(WEB3FXTransferOrderUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬 
                 : 2006/4/25 ���n�@@�a��(SCS) ��Q�[ U02830 �Ή�
Revesion History : 2009/03/16 �đo�g (���u) �c�a�X�V�d�lNo.209�A211�A224�A225
*/

package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX�U�֒����X�V�C���^�Z�v�^) <BR>
 * FX�U�֒����X�V�C���^�Z�v�^�N���X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransferOrderUpdateInterceptor extends
    WEB3AioCashTransUpdateInterceptor
{
    /**
    * ���O���[�e�B���e�B
    */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FXTransferOrderUpdateInterceptor.class);
    
    /**
     * (���o���������e) <BR>
     * ���o���������e
     */
    private WEB3AioNewOrderSpec cashTransOrderSpec;

    /**
     * (������) <BR>
     * ������
     */
    private Date bizDate;

    /**
     * (��n��) <BR>
     * ��n��
     */
    private Date deliveryDate;

    /**
     * (���ʃR�[�h)<BR> 
     * ���ʃR�[�h
     */
    private String orderRequestNumber;

    /**
     * (MQ�X�e�[�^�X) <BR>
     * MQ�X�e�[�^�X
     */
    private String mqStatus;

    /**
     * @@roseuid 41E7693503A9
     */
    public WEB3FXTransferOrderUpdateInterceptor()
    {
    }

    /**
     * (FX�U�֒����X�V�C���^�Z�v�^) <BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �C���X�^���X�𐶐����A <BR>
     * �����̓��o���������e���v���p�e�B�ɃZ�b�g����B
     * 
     * @@param l_cashTransOrderSpec - ���o���������e�I�u�W�F�N�g
     * @@roseuid 41C11C6101F2
     */
    public WEB3FXTransferOrderUpdateInterceptor(
        WEB3AioNewOrderSpec l_cashTransOrderSpec)
    {
        //�����̓��o���������e���v���p�e�B�ɃZ�b�g����
        this.cashTransOrderSpec = l_cashTransOrderSpec;        
    }

    /** [�X�V�l�ݒ�]
     * �imutate���\�b�h�̎����j �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V �uFX����U��_�����P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * @@param l_updateType - INSERT�܂��́AUPDATE
     * �iOrderManagerPersistenceType�ɂĒ萔��`�B�j
     * @@param l_process - �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_orderUnitParams - �����P�ʂ̃p�����[�^�N���X
     * @@return AioOrderUnitParams
     * @@roseuid 41BEE5C600D7
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process,
        AioOrderUnitParams l_orderUnitParams)
    {
        String l_strMethodName = 
            "mutate(" +
            "OrderManagerPersistenceType l_updateType, " +
            "OrderManagerPersistenceContext l_process, " +
            "AioOrderUnitParams l_orderUnitParams)";
        log.entering(l_strMethodName);
        
        if (l_orderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);            
        }
        
        //1) �����J�e�S��(15�F�ב֕ۏ؋��U��)   ---------- >>  WEB3QuestionDivDef
        l_orderUnitParams.setOrderCateg(OrderCategEnum.FX);
        
        //2) ������(�C���^�Z�v�^.������)
        l_orderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.bizDate, "yyyyMMdd"));

        //3) ��n��(�C���^�Z�v�^.��n��)
        l_orderUnitParams.setDeliveryDate(this.deliveryDate);
        
        //4) �ŋ敪(�f�t�H���g�F0)
        l_orderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
        
        //5) �󒍓���
        //ThreadLocalSystemAttributesRegistry.getAttributes(�hxblocks.gtl.attributes.systemtimestamp�h)�̖߂�l
        l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
        //6) ���҃R�[�h�iSONAR�j(�ڋq.���҃R�[�h)
        
        //a> accountId
        long l_lngAccountId = l_orderUnitParams.getAccountId();
        
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
            String l_strTraderCode = l_mainAccountRow.getSonarTraderCode();
        
            //set���҃R�[�h
            l_orderUnitParams.setSonarTraderCode(l_strTraderCode);        
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
        l_orderUnitParams.setOrderRequestNumber(this.orderRequestNumber);
        
        //8) .com�f�r�b�g���ώ���ԍ�(null)
        l_orderUnitParams.setComDebitNumber(null);
        
        //9) �ۏ؋��敪(null)
        l_orderUnitParams.setGuaranteeDiv(null);
        
        //10) �o���\���敪(null)
        l_orderUnitParams.setPaymentApplicationDiv(null);
        
        //11) ��������敪(0�F�����l)
        l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        
        //12) �����o�H�敪(�Z�b�V�������擾���������ڂ̒l)
        OpLoginSecurityService l_opLoginSecurityService = 
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_strRootDiv = 
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        
        //�����o�H�敪���擾�ł��Ȃ��A��������ʂ��ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�� �ꍇ�́A�Ǘ��҂�ݒ肷��
        if (l_strRootDiv == null && OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.stringValue().equals(this.cashTransOrderSpec.getOrderTypeEnum().stringValue()))
        {
            l_strRootDiv = WEB3OrderRootDivDef.ADMIN;
        }
            
        l_orderUnitParams.setOrderRootDiv(l_strRootDiv); 
        
        //13) �����G���[���R�R�[�h(0000�F����)
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        //14) MQ�X�e�[�^�X(�C���^�Z�v�^.MQ�X�e�[�^�X)
        l_orderUnitParams.setMqStatus(this.mqStatus);

        //�E�v�R�[�h(���o���������e.�E�v�R�[�h)
        l_orderUnitParams.setRemarkCode(this.cashTransOrderSpec.getRemarkCode());

        //�E�v��(���o���������e.�E�v��)
        l_orderUnitParams.setRemarkName(this.cashTransOrderSpec.getRemarkName());

        log.exiting(l_strMethodName);
        
        return l_orderUnitParams;
    }

    /**
     * (set������) <BR>
     * ���������Z�b�g����B
     * 
     * @@param l_datOrderBizDate - ������
     * @@roseuid 41C266E7009E
     */
    public void setOrderBizDate(Date l_datOrderBizDate)
    {
        String l_strMethodName = "setOrderBizDate(Date l_datOrderBizDate)";
        log.entering(l_strMethodName);
       
        this.bizDate = l_datOrderBizDate; 
        
        log.exiting(l_strMethodName);     
    }

    /**
     * (get������) <BR>
     * ���������擾����B
     * 
     * @@return Date
     * @@roseuid 41C2684802F0
     */
    public Date getOrderBizDate()
    {
        return this.bizDate;
    }

    /**
     * (set��n��) <BR>
     * ��n�����Z�b�g����B
     * 
     * @@param l_datDeliveryDate - ��n��
     * @@roseuid 41C7D64B02C2
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        String l_strMethodName = "setDeliveryDate(Date l_datDeliveryDate)";
        log.entering(l_strMethodName);
        
        this.deliveryDate = l_datDeliveryDate; 
        
        log.exiting(l_strMethodName);     
    }

    /**
     * (get��n��) <BR>
     * ��n�����擾����B
     * 
     * @@return Date
     * @@roseuid 41C7D64B02E2
     */
    public Date getDeliveryDate()
    {
        return this.deliveryDate;
    }

    /**
     * (set���ʃR�[�h) <BR>
     * ���ʃR�[�h���Z�b�g����B
     * 
     * @@param l_strOrderRequestNumber - ���ʃR�[�h
     * @@roseuid 41C266E700B0
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber)
    {
        String l_strMethodName = "setOrderRequestNumber(String l_strOrderRequestNumber)";
        log.entering(l_strMethodName);
        
        this.orderRequestNumber = l_strOrderRequestNumber; 
        
        log.exiting(l_strMethodName);     
    }

    /**
     * (get���ʃR�[�h) <BR>
     * ���ʃR�[�h���擾����B
     * 
     * @@return String
     * @@roseuid 41C268600169
     */
    public String getOrderRequestNumber()
    {
        return this.orderRequestNumber;
    }

    /**
     * (setMQ�X�e�[�^�X) <BR>
     * MQ�X�e�[�^�X���Z�b�g����B
     * 
     * @@param l_strMQStatus - MQ�X�e�[�^�X
     * @@roseuid 41C26A9A010B
     */
    public void setMQStatus(String l_strMQStatus)
    {
        String l_strMethodName = "setMQStatus(String l_strMQStatus)";
        log.entering(l_strMethodName);
        
        this.mqStatus = l_strMQStatus; 
        
        log.exiting(l_strMethodName);     
    }

    /**
     * (getMQ�X�e�[�^�X) <BR>
     * MQ�X�e�[�^�X���擾����B
     * 
     * @@return String
     * @@roseuid 41C26A9A011B
     */
    public String getMQStatus()
    {
        return this.mqStatus;
    }
}@
