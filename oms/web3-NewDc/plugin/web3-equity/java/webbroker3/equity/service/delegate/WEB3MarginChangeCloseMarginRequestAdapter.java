head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeCloseMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������ԍσ��N�G�X�g�A�_�v�^
                 : (WEB3MarginChangeCloseMarginRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/30 li-songfeng (���u) �V�K�쐬
Revesion History : 2004/12/16 �������F(SRA) �c�Č��Ή��ɔ����C��
Revesion History : 2005/01/05 �����a��(SRA) JavaDoc�C��
Revesion History : 2006/11/27 ��іQ (���u) ���f�� No.1021
Revesion History : 2007/06/14 �����q (���u) ���f�� 1172
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeConfirmRequest;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p��������ԍσ��N�G�X�g�A�_�v�^�j�B<BR>
 * <BR>
 * �M�p��������ԍσ��N�G�X�g�A�_�v�^�N���X
 * @@version 1.0
 */
public class WEB3MarginChangeCloseMarginRequestAdapter 
{ 
   /**
    * (���O���[�e�B���e�B)<BR>
    */
   private static WEB3LogUtility log =
      WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginRequestAdapter.class);

    
    /**
     * (���N�G�X�g�f�[�^)
     */
    private WEB3GenRequest request;
    
    /**
     * @@roseuid 4142B67A011C
     */
    private WEB3MarginChangeCloseMarginRequestAdapter() 
    {
     
    }
    
    /**
     * �M�p��������ԍσ��N�G�X�g�A�_�v�^�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�����B<BR>
     * �Q�j�@@���������C���X�^���X�Ɉ����̃��N�G�X�g�f�[�^���Z�b�g����B<BR>
     * �R�j�@@�C���X�^���X��ԋp����B<BR>
     * <BR>
     * �i�f�t�H���g�R���X�g���N�^��private�Ƃ��A<BR>
     * �{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j<BR>
     * @@param l_request - ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3MarginCloseMarginChangeRequestAdapter<BR>
     * @@roseuid 40C7EAE700D9
     */
    public static WEB3MarginChangeCloseMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME =
              "WEB3MarginChangeCloseMarginRequestAdapter create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@�{�C���X�^���X�𐶐�����B
        WEB3MarginChangeCloseMarginRequestAdapter l_adapter = 
              new WEB3MarginChangeCloseMarginRequestAdapter();
        
        // �Q�j�@@���������C���X�^���X�Ɉ����̃��N�G�X�g�f�[�^���Z�b�g����B
        l_adapter.request = l_request;
        
        log.exiting(STR_METHOD_NAME);
        // �R�j�@@�C���X�^���X��ԋp����B
        return l_adapter;
    }
    
    /**
     * (get���s����)<BR>
     * ���N�G�X�g�f�[�^.���s�������A<BR>
     * AP�w�Ŏg�p���鎷�s�����iEqTypeExecutionConditionType�j��ԋp����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.get���s����(���N�G�X�g�f�[�^.���s����)��delegate����B<BR>
     * @@return EqTypeExecutionConditionType<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40C7EAE700DB
     */
    public EqTypeExecutionConditionType getExecutionCondition() throws WEB3BaseException
    {
        final String  STR_METHOD_NAME = "getExecutionCondition()";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        String l_strExecCondType = null;
        if (request instanceof WEB3MarginCloseMarginChangeConfirmRequest)
        {
            WEB3MarginCloseMarginChangeConfirmRequest l_request = (WEB3MarginCloseMarginChangeConfirmRequest)request;
            l_strExecCondType = l_request.execCondType;
        }
        else if (request instanceof WEB3MarginCloseMarginChangeCompleteRequest)
        {
            WEB3MarginCloseMarginChangeCompleteRequest l_request = (WEB3MarginCloseMarginChangeCompleteRequest)request;
            l_strExecCondType = l_request.execCondType;
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderManager.getExecutionConditionType(l_strExecCondType);
    }
    
    /**
     * (get��������)<BR>
     * ���N�G�X�g�f�[�^.�����������AAP�w�Ŏg�p���钍��������ԋp����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.����������null�̏ꍇ�A0��ԋp����B<BR>
     * �ȊO�A���N�G�X�g�f�[�^.����������double�l��ԋp����B<BR>
     * @@return double<BR>
     * @@roseuid 40C7EAE700DC
     */
    public double getOrderQuantity() 
    {
        final String STR_METHOD_NAME = "getOrderQuantity()";
        log.entering(STR_METHOD_NAME);
       
        String l_strChangeQuantity = null;            //�����㊔��
        if (this.request instanceof WEB3MarginCloseMarginChangeConfirmRequest)
        {
            l_strChangeQuantity =
            ((WEB3MarginCloseMarginChangeConfirmRequest)this.request).orderQuantity;
        }
        else if (this.request instanceof WEB3MarginCloseMarginChangeCompleteRequest)
        {
            l_strChangeQuantity =
            ((WEB3MarginCloseMarginChangeCompleteRequest)this.request).orderQuantity;
        }
        else
        {
            log.error(
               "__�����͖��Ή��ł�(2004/10/10)__",
                       new WEB3BaseException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                           this.getClass().getName() + "getOrderQuantity"));
        }
        if (l_strChangeQuantity == null)
        {
            return 0.0D;
        }

        log.exiting(STR_METHOD_NAME);
        return Double.parseDouble(l_strChangeQuantity);
    }
    
    /**
     * (is�o����܂Œ���)<BR>
     * ���N�G�X�g�f�[�^.���������敪���A<BR>
     * �������͓��e���u�o����܂Œ����v���ǂ����𔻒肷��B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.���������敪���h�o����܂Œ����h�̏ꍇ��true���A<BR>
     * ���N�G�X�g�f�[�^.���������敪���h��������h�̏ꍇ��false��ԋp����B<BR>
     * @@return boolean<BR>
     * @@roseuid 40C7EBD5030C
     */
    public boolean isCarriedOrder() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isCarriedOrder()";
        log.entering(STR_METHOD_NAME);
        String l_expirationDateType = null;
        boolean l_isCarriedOrder = false;
        if (this.request instanceof WEB3MarginCloseMarginChangeConfirmRequest)
        {
            l_expirationDateType = ((WEB3MarginCloseMarginChangeConfirmRequest)this.request).expirationDateType;
        }
        else if (this.request instanceof WEB3MarginCloseMarginChangeCompleteRequest)
       {
           l_expirationDateType =
           ((WEB3MarginCloseMarginChangeCompleteRequest) this.request).expirationDateType;
       }
       else
       {
           log.error(
              "__�����͖��Ή��ł�(2004/10/10)__",
                      new WEB3BaseException(
                          WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                          this.getClass().getName() + "isCarriedOrder"));
           throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + "isCarriedOrder");
       }
       if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_expirationDateType))
       {
           log.exiting(STR_METHOD_NAME);
           l_isCarriedOrder = true;
       }
       if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_expirationDateType))
       {
           log.exiting(STR_METHOD_NAME);
           l_isCarriedOrder = false;
       }
       log.exiting(STR_METHOD_NAME);
       return l_isCarriedOrder;
    }

    /**
     * (get������iW�w�l�j���s����)<BR>
     * ���N�G�X�g�f�[�^.W�w�l�p���s�������A<BR>
     * AP�w�Ŏg�p���鎷�s�����iEqTypeExecutionConditionType�j��ԋp����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.get���s����(���N�G�X�g�f�[�^.�v�w�l�p���s����)��delegate����B<BR>
     * @@return EqTypeExecutionConditionType<BR>
     * @@throws WEB3BaseException <BR>
     * @@roseuid 40C7EBD5030C
     */
    public EqTypeExecutionConditionType getModifiedWLimitExecCondType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getModifiedWLimitExecCondType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

        String l_strWlimitExecCondType = null;
        if (request instanceof WEB3MarginCloseMarginChangeConfirmRequest)
        {
            WEB3MarginCloseMarginChangeConfirmRequest l_request =
                (WEB3MarginCloseMarginChangeConfirmRequest)request;

            l_strWlimitExecCondType = l_request.wlimitExecCondType;
        }
        else if (request instanceof WEB3MarginCloseMarginChangeCompleteRequest)
        {
            WEB3MarginCloseMarginChangeCompleteRequest l_request =
                (WEB3MarginCloseMarginChangeCompleteRequest)request;

            l_strWlimitExecCondType = l_request.wlimitExecCondType;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        EqTypeExecutionConditionType l_eqTypeExecutionConditionType =
            l_orderManager.getExecutionConditionType(l_strWlimitExecCondType);

        log.exiting(STR_METHOD_NAME);
        return l_eqTypeExecutionConditionType;
    }

    /**
     * (get�����㒍��������)<BR>
     * ���N�G�X�g�f�[�^.�����L���������AAP�w�Ŏg�p��������㒍����������ԋp����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^.�����L��������null�̏ꍇ<BR>
     * �@@�@@�@@(��������̒����̏ꍇ)<BR>
     * �@@�@@�@@�@@get������()�̖߂�l��ԋp����B<BR>
     * <BR>
     * �Q�j�@@��L�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�g�����������}�l�[�W��.getOrderUnits()���R�[�����A<BR>
     * �@@�@@�@@�@@�@@�@@�����P�ʃI�u�W�F�N�g�̔z����擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[getOrderUnits�̈����ݒ�]<BR>
     * �@@�@@�@@�@@�@@�@@�@@����ID�F�@@���N�G�X�g�f�[�^.�h�c<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�g�����������}�l�[�W��.get�����L������()���R�[�����A<BR>
     * �@@�@@�@@�@@�@@�@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�mget�����L�������̈����ݒ�n<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����L�������F�@@���N�G�X�g�f�[�^.�����L������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����R�[�h�F�@@(*)��������.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�s��R�[�h�F�@@(*)�s��.�s��R�[�h<BR>
     * <BR>
     * (*)�@@�Q�|�P�Ŏ擾���������P�ʃI�u�W�F�N�g�z���0�Ԗڂ̗v�f���擾<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getModifiedExpirationDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getModifiedExpirationDate()";
        log.entering(STR_METHOD_NAME);

        Date l_datExpirationDate;
        long l_lngOrderId;
        if (request instanceof WEB3MarginCloseMarginChangeConfirmRequest)
        {
            WEB3MarginCloseMarginChangeConfirmRequest l_confirmRequest =
                (WEB3MarginCloseMarginChangeConfirmRequest)request;
            l_datExpirationDate = l_confirmRequest.expirationDate;
            l_lngOrderId = Long.parseLong(l_confirmRequest.id);
        }
        else if (request instanceof WEB3MarginCloseMarginChangeCompleteRequest)
        {
            WEB3MarginCloseMarginChangeCompleteRequest l_completeRequest =
                (WEB3MarginCloseMarginChangeCompleteRequest)request;
            l_datExpirationDate = l_completeRequest.expirationDate;
            l_lngOrderId = Long.parseLong(l_completeRequest.id);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        if (l_datExpirationDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        //��L�ȊO�̏ꍇ
        else
        {
            // �g�����������}�l�[�W��
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            // �@@�Q�|�P�j�g�����������}�l�[�W��.getOrderUnits()
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);

            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnits[0].getDataSourceObject();
            ProductManager l_productManager = l_tradingModule.getProductManager();
            WEB3EquityProduct l_product = null;
            long l_lngMarketId = l_orderUnitRow.getMarketId();
            Market l_market = null;
            try
            {
                l_product =
                    (WEB3EquityProduct)l_productManager.getProduct(
                        l_orderUnitRow.getProductId());
                l_market = (Market)l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // �����R�[�h�F�@@(*)��������.�����R�[�h
            String l_strProductCode = l_product.getProductCode();
            // �s��R�[�h�F�@@(*)�s��.�s��R�[�h
            String l_strMarketCode = l_market.getMarketCode();
            // �g�����������}�l�[�W��.get�����L������()���R�[�����A�߂�l��ԋp����B
            Date l_datReturnExpirationDate = l_orderManager.getExpirationDate(
                l_datExpirationDate, l_strProductCode, l_strMarketCode);

            log.exiting(STR_METHOD_NAME);
            return l_datReturnExpirationDate;
        }
    }

    /**
     * (get�����L������)<BR>
     * �����L���������擾����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^.�����L��������null�̏ꍇ<BR>
     * �@@�@@�@@(��������̒����̏ꍇ)<BR>
     * �@@�@@�@@�@@null��ԋp����B<BR>
     * <BR>
     * �Q�j�@@��L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@this.get�����㒍��������()�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getExpirationDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDate()";
        log.entering(STR_METHOD_NAME);

        Date l_datExpirationDate = null;
        if (request instanceof WEB3MarginCloseMarginChangeConfirmRequest)
        {
            l_datExpirationDate = ((WEB3MarginCloseMarginChangeConfirmRequest)request).expirationDate;
        }
        else if (request instanceof WEB3MarginCloseMarginChangeCompleteRequest)
        {
            l_datExpirationDate = ((WEB3MarginCloseMarginChangeCompleteRequest)request).expirationDate;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        if (l_datExpirationDate == null)
        {
            // ���N�G�X�g�f�[�^.�����L��������null�̏ꍇ
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            // this.get�����㒍��������()�̖߂�l��ԋp����B
            l_datExpirationDate = this.getModifiedExpirationDate();

            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }
    }
}
@
