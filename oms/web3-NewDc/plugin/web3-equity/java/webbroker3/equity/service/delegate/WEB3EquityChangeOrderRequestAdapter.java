head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������N�G�X�g�A�_�v�^(WEB3EquityChangeOrderRequestAdapter)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/12 �����(���u) �V�K�쐬
Revesion History : 2004/12/15 �������F(SRA) �c�Č��Ή��ɂ��C��
Revesion History : 2005/01/06 �����a��(SRA) JavaDoc�C��
Revesion History : 2006/11/03 �����F(���u) ���f�� 1002
Revesion History : 2007/06/14 �����q (���u) ���f�� 1174
Revesion History : 2007/12/19 �����F(���u) ���f�� 1249
Revesion History : 2008/02/13 �g�E�N�| (���u) �d�l�ύX���f��No.1299
*/
package webbroker3.equity.service.delegate;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������������N�G�X�g�A�_�v�^�j�B<BR>
 * <BR>
 * ��ʂɈˑ��������������b�v����A�_�v�^�N���X�B<BR>
 * <BR>
 * ���Y�N���X�́A�e�،���Ђ̉�ʗp���ɂ���āA<BR>
 * �����쐬����邱�Ƃ�O��Ƃ���B
 * @@version 1.0
 */
public class WEB3EquityChangeOrderRequestAdapter
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeOrderRequestAdapter.class);

    /**
     * (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * ���N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3GenRequest requestData;

    /**
     * @@roseuid 409EFFD00060
     */
    protected WEB3EquityChangeOrderRequestAdapter()
    {

    }

    /**
     * (create)<BR>
     * ���������������N�G�X�g�A�_�v�^�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�������B<BR>
     * �Q�j�@@���������C���X�^���X�Ɉ����̃��N�G�X�g�f�[�^���Z�b�g����B<BR>
     * �R�j�@@�C���X�^���X��ԋp����B<BR>
     * <BR>
     * �i�f�t�H���g�R���X�g���N�^��protected�Ƃ��A<BR>
     * �{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j<BR>
     * @@param ���N�G�X�g - ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return �����������N�G�X�g�A�_�v�^
     * @@roseuid 4021C7780319
     */
    public static WEB3EquityChangeOrderRequestAdapter create(WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME =
             "WEB3EquityChangeOrderRequestAdapter create(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        // �{�C���X�^���X�𐶐�������
        WEB3EquityChangeOrderRequestAdapter l_adapter =
            new WEB3EquityChangeOrderRequestAdapter();

        // ���������C���X�^���X�Ɉ����̃��N�G�X�g�f�[�^���Z�b�g����
        if (l_request instanceof WEB3EquityChangeConfirmRequest)
        {
            l_adapter.requestData = (WEB3EquityChangeConfirmRequest)l_request;
        }
        else if (l_request instanceof WEB3EquityChangeCompleteRequest)
        {
            l_adapter.requestData = (WEB3EquityChangeCompleteRequest)l_request;
        }

        log.exiting(STR_METHOD_NAME);
        // �C���X�^���X��ԋp����
        return l_adapter;
    }

    /**
     * (get���s����)<BR>
     * <BR>
     * ��ʂ̎��s�������A<BR>
     * AP�w�Ŏg�p���������̎��s�����iEqTypeExecutionConditionType�j��ԋp����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.get���s����(���N�G�X�g�f�[�^.���s����)��delegate����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType
     * @@roseuid 4021C778031C
     */
    public EqTypeExecutionConditionType getExecCondType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecCondType()";
        log.entering(STR_METHOD_NAME);

        String l_strExecCondType = null;
        if (this.requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            l_strExecCondType =
                ((WEB3EquityChangeConfirmRequest)this.requestData).execCondType;
        }
        else if (this.requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            l_strExecCondType =
                ((WEB3EquityChangeCompleteRequest)this.requestData).execCondType;
        }
        else
        {
            log.error("�����͖��Ή��ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        return l_orderManager.getExecutionConditionType(l_strExecCondType);
    }

    /**
     * (get�����㊔��)<BR>
     * <BR>
     * ������̊��������N�G�X�g�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.����������ԋp����B<BR>
     * @@return double
     * @@roseuid 4021DD0C0173
     */
    public double getRequestOrderQuantity()
    {
        final String STR_METHOD_NAME = "getRequestOrderQuantity()";
        log.entering(STR_METHOD_NAME);

        String l_strChangeQuantity = "0";            //�����㊔��

        if (this.requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            l_strChangeQuantity =
                ((WEB3EquityChangeConfirmRequest) this.requestData).orderQuantity;
        }
        else if (this.requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            l_strChangeQuantity =
                ((WEB3EquityChangeCompleteRequest) this.requestData).orderQuantity;
        }
        else
        {
            log.error(
                "__�����͖��Ή��ł�(2004/03/25)__",
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "getChangeOrderQuantity"));
        }

        BigDecimal l_decChangeQuantity = new BigDecimal(l_strChangeQuantity);

        log.exiting(STR_METHOD_NAME);
        return l_decChangeQuantity.doubleValue();
    }

    /**
     * (get������P��)<BR>
     * <BR>
     * ������̒P�������N�G�X�g�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * ���N�G�X�g.�����P���敪=="�w�l"�̏ꍇ�́A
     * ���N�G�X�g�f�[�^.�����P����ԋp����B<BR> 
     * <BR>
     * ���N�G�X�g.�����P���敪=="���s"�̏ꍇ�́A <BR>
     * 0��ԋp����B <BR>
     * @@return double
     * @@roseuid 4021DD1600A8
     */
    public double getRequestLimitPrice()
    {
        final String STR_METHOD_NAME = "getRequestLimitPrice()";
        log.entering(STR_METHOD_NAME);

        String l_strChangeLimitPrice = "0";               //������P��

        if (this.requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            l_strChangeLimitPrice =
                ((WEB3EquityChangeConfirmRequest) this.requestData).limitPrice;
            if (l_strChangeLimitPrice == null
                || l_strChangeLimitPrice.trim().equals(""))
            {
                l_strChangeLimitPrice = "0";
            }
        }
        else if (this.requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            l_strChangeLimitPrice =
                ((WEB3EquityChangeCompleteRequest) this.requestData).limitPrice;
            if (l_strChangeLimitPrice == null
                || l_strChangeLimitPrice.trim().equals(""))
            {
                l_strChangeLimitPrice = "0";
            }
        }
        else
        {
            log.error(
                "__�����͖��Ή��ł�(2004/03/25)__",
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "getRequestLimitPrice"));
        }

        BigDecimal l_decChangeLimitPrice = new BigDecimal(l_strChangeLimitPrice);

        log.exiting(STR_METHOD_NAME);
        return l_decChangeLimitPrice.doubleValue();
    }

    /**
     * (get����ID)<BR>
     * <BR>
     * �����Ώۂ̒����h�c�����N�G�X�g�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�����h�c��ԋp����B<BR>
     * @@return long
     * @@roseuid 4021DD300154
     */
    public long getRequestOrderId()
    {
        final String STR_METHOD_NAME = "getRequestOrderId()";
        log.entering(STR_METHOD_NAME);
      
        if (requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            log.exiting(STR_METHOD_NAME);
            return new Long(((WEB3EquityChangeCompleteRequest) requestData).id).longValue();
        }
        if (requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            String l_id = ((WEB3EquityChangeConfirmRequest) requestData).id;

            long l_value = Long.parseLong(l_id);

            log.exiting(STR_METHOD_NAME);
            return l_value;
        }
        else
        {
            log.error("__�����͖��Ή��ł�(2004/03/25)__",
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, //�\�����Ȃ��V�X�e���G���[���������܂���
            this.getClass().getName() + "getRequestOrderId"));
        }

        log.exiting(STR_METHOD_NAME);
        return -1L;
    }

    /**
     * (is�o����܂Œ���)<BR>
     * <BR>
     * �������͓��e���u�o����܂Œ����v���ǂ����𔻒肷��B<BR>
     * <BR>
     * �E���N�G�X�g�f�[�^.���������敪���h�o����܂Œ����h�̏ꍇ��true���A<BR>
     * �@@���N�G�X�g�f�[�^.���������敪���h��������h�̏ꍇ��false���A���ꂼ��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4074B97501C4
     */
    public boolean isOrderUntilDeadLine() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "isOrderUntilDeadLine()";
        log.entering(STR_METHOD_NAME);
               
        // ���N�G�X�g�f�[�^.���������敪
        String l_strExpirationDateType = this.getExpirationDateType();

        // ���N�G�X�g�f�[�^.���������敪���h�o����܂Œ����h�̏ꍇ��true
        if (l_strExpirationDateType.equals(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        // ���N�G�X�g�f�[�^.���������敪���h��������h�̏ꍇ��false
        else if (l_strExpirationDateType.equals(WEB3OrderExpirationDateTypeDef.DAY_LIMIT))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00170,
                getClass().getName() + "." + STR_METHOD_NAME
                );
        }
    }

    /**
     * (get���������敪)<BR>
     * <BR>
     * �����Ώۂ̒��������敪�����N�G�X�g�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.���������敪��ԋp����B<BR>
     * @@return String
     */
    public String getExpirationDateType()
    {
        final String STR_METHOD_NAME = "getExpirationDateType()";
        log.entering(STR_METHOD_NAME);
             
        String l_strExpirationDateType = null;            

        if (this.requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            l_strExpirationDateType =
                ((WEB3EquityChangeConfirmRequest) this.requestData).expirationDateType;
        }
        else if (this.requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            l_strExpirationDateType =
                ((WEB3EquityChangeCompleteRequest) this.requestData).expirationDateType;
        }
        else
        {
            log.error(
                "__�����͖��Ή��ł�(2004/03/25)__",
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "getExpirationDateType"));
        }
       
        log.exiting(STR_METHOD_NAME);
        return l_strExpirationDateType;
    }

   /**
    * ���N�G�X�g�f�[�^��获�s�������擾����B<BR>
    *<BR>
    * @@return ���s����
    */
   public String getRequestExecCondType()
   {
       final String STR_METHOD_NAME = "getRequestExecCondType()";
       log.entering(STR_METHOD_NAME);

       if (this.requestData instanceof WEB3EquityChangeConfirmRequest)
       {
            log.exiting(STR_METHOD_NAME);
            return ((WEB3EquityChangeConfirmRequest)this.requestData).execCondType;
       }
       else if (this.requestData instanceof WEB3EquityChangeCompleteRequest)
       {
            log.exiting(STR_METHOD_NAME);
            return ((WEB3EquityChangeCompleteRequest)this.requestData).execCondType;
       }
       else
       {
            log.error("__�����͖��Ή��ł�(2004/03/25)__",
               new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                   this.getClass().getName() + "getRequestExecCondType"));
       }

       log.exiting(STR_METHOD_NAME);
       return null;
    }
    
    /**
     * (get�����㒍��������)<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�����L���������A<BR>
     * AP�w�Ŏg�p��������㒍����������ԋp����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^.�����L��������null�̏ꍇ
     * (��������̒����̏ꍇ)<BR>
     * �@@get������()�̖߂�l��ԋp����B<BR>
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
    public Date getExpirationDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDate()";
        log.entering(STR_METHOD_NAME);

        Date l_datExpirationDate;
        long l_lngOrderId;
        if (requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            WEB3EquityChangeConfirmRequest l_equityChangeConfirmRequest =
                (WEB3EquityChangeConfirmRequest)requestData;
            l_datExpirationDate = l_equityChangeConfirmRequest.expirationDate;
            l_lngOrderId = Long.parseLong(l_equityChangeConfirmRequest.id);
        }
        else if (requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            WEB3EquityChangeCompleteRequest l_equityChangeCompleteRequest =
                (WEB3EquityChangeCompleteRequest)requestData;
            l_datExpirationDate = l_equityChangeCompleteRequest.expirationDate;
            l_lngOrderId = Long.parseLong(l_equityChangeCompleteRequest.id);
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

        //���N�G�X�g�f�[�^.�����L��������null�̏ꍇ
        if (l_datExpirationDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        else
        {
            // �g�����������}�l�[�W��
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            //�g�����������}�l�[�W��.getOrderUnits()
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
                        new Long(l_orderUnitRow.getProductId()).longValue());
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
     * (get�iW�w�l�j���s����)<BR>
     * ��ʂ̎��s�������A <BR>
     * AP�w�Ŏg�p���鎷�s�����iEqTypeExecutionConditionType�j��ԋp����B <BR>
     *<BR>
     * �g�����������}�l�[�W��.get���s����(���N�G�X�g�f�[�^.�i�v�w�l�j���s����)��delegate����B<BR>
     * @@return EqTypeExecutionConditionType
     * @@throws WEB3BaseException
     */
    public EqTypeExecutionConditionType getWLimitExecCondType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitExecCondType()";
        log.entering(STR_METHOD_NAME);

        String l_strWlimitExecCondType = null;
        if (this.requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            l_strWlimitExecCondType =
                ((WEB3EquityChangeConfirmRequest)this.requestData).wlimitExecCondType;
        }
        else if (this.requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            l_strWlimitExecCondType =
                ((WEB3EquityChangeCompleteRequest)this.requestData).wlimitExecCondType;
        }
        else
        {
            log.error("�����͖��Ή��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

        EqTypeExecutionConditionType l_eqTypeExecutionConditionType =
            l_orderManager.getExecutionConditionType(l_strWlimitExecCondType);

        log.exiting(STR_METHOD_NAME);
        return l_eqTypeExecutionConditionType;
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
    public Date getOrderExpirationDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderExpirationDate()";
        log.entering(STR_METHOD_NAME);

        Date l_datExpirationDate = null;
        if (requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            l_datExpirationDate = ((WEB3EquityChangeConfirmRequest)requestData).expirationDate;
        }
        else if (requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            l_datExpirationDate = ((WEB3EquityChangeCompleteRequest)requestData).expirationDate;
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
            l_datExpirationDate = this.getExpirationDate();

            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }
    }
}
@
