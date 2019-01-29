head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeOpenMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������V�K�����N�G�X�g�A�_�v�^�N���X(WEB3MarginChangeOpenMarginRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 Ḗ@@��(���u) �V�K�쐬
Revesion History : 2004/12/15 �������F(SRA) �c�Č��Ή��ɂ��C��
Revesion History : 2005/01/05 ����   (SRA) JavaDoc�C��
Revesion History : 2006/11/28 ��іQ (���u) ���f�� No.1009
Revesion History : 2007/06/13 �����q (���u) ���f�� 1170
*/

package webbroker3.equity.service.delegate;

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
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeConfirmRequest;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p��������V�K�����N�G�X�g�A�_�v�^�N���X�j�B
 * @@author �@@��
 * @@version 1.0
 */
public class WEB3MarginChangeOpenMarginRequestAdapter 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginChangeOpenMarginRequestAdapter.class);
    /**
     * (���N�G�X�g�f�[�^)
     */
    private WEB3GenRequest request;
    
    /**
     * @@roseuid 4142B6790229
     */
    private WEB3MarginChangeOpenMarginRequestAdapter() 
    {
     
    }
    
    /**
     * �M�p��������V�K�����N�G�X�g�A�_�v�^�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�����B<BR>
     * �Q�j�@@���������C���X�^���X�Ɉ����̃��N�G�X�g�f�[�^���Z�b�g����B<BR>
     * �R�j�@@�C���X�^���X��ԋp����B<BR>
     * <BR>
     * �i�f�t�H���g�R���X�g���N�^��private�Ƃ��A<BR>
     * �{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3MarginOpenMarginChangeRequestAdapter<BR>
     * @@roseuid 40E2409502FD
     */
    public static WEB3MarginChangeOpenMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = "WEB3MarginChangeOpenMarginRequestAdapter create(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3MarginChangeOpenMarginRequestAdapter" + "." + STR_METHOD_NAME);
        }
        WEB3MarginChangeOpenMarginRequestAdapter  l_adapter = new WEB3MarginChangeOpenMarginRequestAdapter();
        l_adapter.request = l_request;
        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }
    
    /**
     * (get�����㒍������)<BR>
     * ���N�G�X�g�f�[�^.�����������AAP�w�Ŏg�p��������㒍��������ԋp����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.����������null�̏ꍇ<BR>
     * �@@0��ԋp����B<BR>
     * <BR>
     * �ȊO�A���N�G�X�g�f�[�^.����������double�l��ԋp����B<BR>
     * @@return double<BR>
     * @@roseuid 40E24095031C
     */
    public double getModifiedOrderQuantity() 
    {
        final String STR_METHOD_NAME = "getModifiedOrderQuantity";
        log.entering(STR_METHOD_NAME);
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            WEB3MarginOpenMarginChangeConfirmRequest l_request1 = (WEB3MarginOpenMarginChangeConfirmRequest)request;
            //���N�G�X�g�f�[�^.����������null�̏ꍇ
            if (l_request1.orderQuantity == null)
            {
                return 0;   
            }
            //�ȊO�A���N�G�X�g�f�[�^.����������double�l��ԋp����
            else
            {
                return Double.parseDouble(l_request1.orderQuantity);
            }
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            WEB3MarginOpenMarginChangeCompleteRequest l_request2 = (WEB3MarginOpenMarginChangeCompleteRequest)request;    
            //���N�G�X�g�f�[�^.����������null�̏ꍇ
            if (l_request2.orderQuantity == null)
            {
                return 0;   
            }
            //�ȊO�A���N�G�X�g�f�[�^.����������double�l��ԋp����
            else
            {
                return Double.parseDouble(l_request2.orderQuantity);
            }
        }
        log.exiting(STR_METHOD_NAME);   
        return 0;
    }
    
    /**
     * (get�����㎷�s����)<BR>
     * ���N�G�X�g�f�[�^.���s�������A<BR>
     * AP�w�Ŏg�p��������㎷�s�����iEqTypeExecutionConditionType�j��ԋp����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.get���s����(���N�G�X�g�f�[�^.���s����)��delegate����B<BR>
     * @@return EqTypeExecutionConditionType<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40E24095030C
     */
    public EqTypeExecutionConditionType getModifiedExecutionConditionType() throws WEB3BaseException
    {
        final String  STR_METHOD_NAME = "getModifiedExecutionConditionType() ";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        String l_strExecCondType = null;
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            WEB3MarginOpenMarginChangeConfirmRequest l_request1 = (WEB3MarginOpenMarginChangeConfirmRequest)request;
            l_strExecCondType = l_request1.execCondType;
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            WEB3MarginOpenMarginChangeCompleteRequest l_request2 = (WEB3MarginOpenMarginChangeCompleteRequest)request;
            l_strExecCondType = l_request2.execCondType;
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderManager.getExecutionConditionType(l_strExecCondType);
    }
    
    /**
     * (get�����㒍��������)<BR>
     * ���N�G�X�g�f�[�^.�����L���������A<BR>
     * AP�w�Ŏg�p��������㒍����������ԋp����B<BR>
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
     * �@@�@@�@@�@@�@@�@@�mget�����L�������̈����ݒ�n<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����L�������F�@@���N�G�X�g�f�[�^.�����L������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����R�[�h�F�@@(*)��������.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�s��R�[�h�F�@@(*)�s��.�s��R�[�h<BR>
     * <BR>
     * (*)�@@�Q�|�P�Ŏ擾���������P�ʃI�u�W�F�N�g�z���0�Ԗڂ̗v�f���擾<BR>
     * <BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40E24B1101C4
     */
    public Date getModifiedExpirationDate() throws WEB3BaseException 
    {
        final String  STR_METHOD_NAME = "getModifiedExpirationDate() ";
        log.entering(STR_METHOD_NAME);

        Date l_datExpirationDate;
        long l_lngOrderId;
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            WEB3MarginOpenMarginChangeConfirmRequest l_confirmRequest =
                (WEB3MarginOpenMarginChangeConfirmRequest)request;
            l_datExpirationDate = l_confirmRequest.expirationDate;
            l_lngOrderId =  Long.parseLong(l_confirmRequest.id);
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            WEB3MarginOpenMarginChangeCompleteRequest l_completeRequest =
                (WEB3MarginOpenMarginChangeCompleteRequest)request;
            l_datExpirationDate = l_completeRequest.expirationDate;
            l_lngOrderId =  Long.parseLong(l_completeRequest.id);
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

        if(l_datExpirationDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        //�ȊO�A���N�G�X�g�f�[�^.�����L��������ԋp����
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
            EqtypeOrderUnitRow l_orderUnitRow=(EqtypeOrderUnitRow)l_orderUnits[0].getDataSourceObject();
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
     * (get������t�w�l��l)<BR>
     * ���N�G�X�g�f�[�^.�����������AAP�w�Ŏg�p���������t�w�l��l��ԋp����B<BR>
     * <BR>
     * ���N�G�X�g.���������敪���h�w��Ȃ��h�̏ꍇ<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�������� �� �h�t�w�l�h�̏ꍇ<BR>
     * �@@���N�G�X�g�f�[�^.�t�w�l�p���������P����ԋp����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�������� �� �hW�w�l�h�̏ꍇ<BR>
     * �@@���N�G�X�g�f�[�^.W�w�l�p���������P����ԋp����B<BR>
     * @@return double<BR>
     * @@roseuid 40E240C90147
     */
    public double getModifiedStopOrderPrice() 
    {
        final String  STR_METHOD_NAME = "getModifiedStopOrderPrice() ";
        log.entering(STR_METHOD_NAME);
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            WEB3MarginOpenMarginChangeConfirmRequest l_request1 = (WEB3MarginOpenMarginChangeConfirmRequest)request;
            //���N�G�X�g�f�[�^.����������null�̏ꍇ
            if(WEB3OrderingConditionDef.DEFAULT.equals(l_request1.orderCondType))
            {
                return 0.0D;                  
            }
            //���N�G�X�g�f�[�^.�������� �� �h�t�w�l�h�̏ꍇ<
            else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request1.orderCondType))
            {
                //�t�w�l�p���������P����ԋp����
                return Double.parseDouble(l_request1.stopOrderCondPrice);   
            }
            //���N�G�X�g�f�[�^.�������� �� �hW�w�l�h�̏ꍇ
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request1.orderCondType))
            {
                //W�w�l�p���������P����ԋp����B
                return Double.parseDouble(l_request1.wlimitOrderCondPrice);   
            }
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            WEB3MarginOpenMarginChangeCompleteRequest l_request2 = (WEB3MarginOpenMarginChangeCompleteRequest)request;    
            //���N�G�X�g�f�[�^.����������null�̏ꍇ
            if(WEB3OrderingConditionDef.DEFAULT.equals(l_request2.orderCondType))
            {
                return 0.0D;                   
            }
            //���N�G�X�g�f�[�^.�������� �� �h�t�w�l�h�̏ꍇ<
            else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request2.orderCondType))
            {
                //�t�w�l�p���������P����ԋp����
                return Double.parseDouble(l_request2.stopOrderCondPrice);   
            }
            //���N�G�X�g�f�[�^.�������� �� �hW�w�l�h�̏ꍇ
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request2.orderCondType))
            {
                //W�w�l�p���������P����ԋp����B
                return Double.parseDouble(l_request2.wlimitOrderCondPrice);   
            }
        }
        log.exiting(STR_METHOD_NAME);
        return 0.0D;
    }
    
    /**
     * (get�����㔭���������Z�q)<BR>
     * ���N�G�X�g�f�[�^.�����������A
     * AP�w�Ŏg�p��������㔭���������Z�q��ԋp����B<BR>
     * <BR>
     * ���N�G�X�g.���������敪���h�w��Ȃ��h�̏ꍇ<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�������� �� �h�t�w�l�h�̏ꍇ<BR>
     * �@@���N�G�X�g�f�[�^.�t�w�l�p�����������Z�q��ԋp����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�������� �� �hW�w�l�h�̏ꍇ<BR>
     * �@@���N�G�X�g�f�[�^.W�w�l�p�����������Z�q��ԋp����B<BR>
     * @@return String<BR>
     * @@roseuid 40E241B9029F
     */
    public String getModifiedOrderCondOperator() 
    {
        final String  STR_METHOD_NAME = "getModifiedOrderCondOperator() ";
        log.entering(STR_METHOD_NAME);
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            WEB3MarginOpenMarginChangeConfirmRequest l_request1 = (WEB3MarginOpenMarginChangeConfirmRequest)request;
            //���N�G�X�g.���������敪���h�w��Ȃ��h�̏ꍇ
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_request1.orderCondType))
            {
                //return null
                return null;   
            }
            //���N�G�X�g�f�[�^.�������� �� �h�t�w�l�h�̏ꍇ<
            else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request1.orderCondType))
            {
                //�t�w�l�p�����������Z�q��ԋp����
                return l_request1.stopOrderCondOperator;   
            }
            //���N�G�X�g�f�[�^.�������� �� �hW�w�l�h�̏ꍇ
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request1.orderCondType))
            {
                //W�w�l�p�����������Z�q��ԋp����B
                return l_request1.wlimitOrderCondOperator;   
            }
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            WEB3MarginOpenMarginChangeCompleteRequest l_request2 = (WEB3MarginOpenMarginChangeCompleteRequest)request;    
            //���N�G�X�g.���������敪���h�w��Ȃ��h�̏ꍇ
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_request2.orderCondType))
            {
                //return null
                return null;   
            }
            //���N�G�X�g�f�[�^.�������� �� �h�t�w�l�h�̏ꍇ<
            else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request2.orderCondType))
            {
                //�t�w�l�p�����������Z�q��ԋp����
                return l_request2.stopOrderCondOperator;   
            }
            //���N�G�X�g�f�[�^.�������� �� �hW�w�l�h�̏ꍇ
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request2.orderCondType))
            {
                //W�w�l�p�����������Z�q��ԋp����B
                return l_request2.wlimitOrderCondOperator;   
            }
        }
        log.exiting(STR_METHOD_NAME);    
        return null;
    }
    
    /**
     * (is�o����܂Œ���)<BR>
     * ���N�G�X�g�f�[�^.���������敪���A
     * �������͓��e���u�o����܂Œ����v���ǂ����𔻒肷��B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.���������敪���h�o����܂Œ����h�̏ꍇ��true���A<BR>
     * ���N�G�X�g�f�[�^.���������敪���h��������h�̏ꍇ��false��ԋp����B<BR>
     * @@return boolean<BR>
     * @@roseuid 40E4E7E7005A
     */
    public boolean isCarriedOrder() 
    {
        final String  STR_METHOD_NAME = "isCarriedOrder()";
        log.entering(STR_METHOD_NAME); 
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            WEB3MarginOpenMarginChangeConfirmRequest l_request1 = (WEB3MarginOpenMarginChangeConfirmRequest)request;
            //���N�G�X�g�f�[�^.���������敪���h�o����܂Œ����h�̏ꍇ��true
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request1.expirationDateType))
            {
                return true;   
            }
            //���N�G�X�g�f�[�^.���������敪���h��������h�̏ꍇ��false��ԋp����B
            else if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request1.expirationDateType))
            {
                return false;
            }
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            WEB3MarginOpenMarginChangeCompleteRequest l_request2 = (WEB3MarginOpenMarginChangeCompleteRequest)request;    
            //���N�G�X�g�f�[�^.���������敪���h�o����܂Œ����h�̏ꍇ��true
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request2.expirationDateType))
            {
                return true;   
            }
            //���N�G�X�g�f�[�^.���������敪���h��������h�̏ꍇ��false��ԋp����B
            else if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request2.expirationDateType))
            {
                return false;
            }
        } 
        log.exiting(STR_METHOD_NAME);   
        return false;       
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
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            WEB3MarginOpenMarginChangeConfirmRequest l_request =
                (WEB3MarginOpenMarginChangeConfirmRequest)request;

            l_strWlimitExecCondType = l_request.wlimitExecCondType;
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            WEB3MarginOpenMarginChangeCompleteRequest l_request =
                (WEB3MarginOpenMarginChangeCompleteRequest)request;

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
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            l_datExpirationDate = ((WEB3MarginOpenMarginChangeConfirmRequest)request).expirationDate;
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            l_datExpirationDate = ((WEB3MarginOpenMarginChangeCompleteRequest)request).expirationDate;
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

        // ���N�G�X�g�f�[�^.�����L��������null�̏ꍇ
        if (l_datExpirationDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            // ��L�ȊO�̏ꍇ
            // this.get�����㒍��������()�̖߂�l��ԋp����B
            l_datExpirationDate = this.getModifiedExpirationDate();

            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }
    }
}
@
