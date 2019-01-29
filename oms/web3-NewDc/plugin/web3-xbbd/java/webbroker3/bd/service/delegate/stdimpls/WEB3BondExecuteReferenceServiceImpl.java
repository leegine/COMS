head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ɖ�T�[�r�XImpl (WEB3BondExecuteReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/21 ���� (���u) �V�K�쐬
Revesion History : 2007/02/08 �����Q (���u) �d�l�ύX�E���f��158
Revesion History : 2007/07/11 ���n�m (���u) �d�l�ύX�E���f��197
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.bd.define.WEB3BondExecuteReferenceDetailUnitDef;
import webbroker3.bd.define.WEB3BondProductDivDef;
import webbroker3.bd.define.WEB3BondReferenceTypeDef;
import webbroker3.bd.message.WEB3BondExecuteReferenceDetailUnit;
import webbroker3.bd.message.WEB3BondExecuteReferenceRequest;
import webbroker3.bd.message.WEB3BondExecuteReferenceResponse;
import webbroker3.bd.message.WEB3BondSortKey;
import webbroker3.bd.service.delegate.WEB3BondExecuteReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.define.WEB3GentradeCurrencyCodeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���������Ɖ�T�[�r�XImpl)<BR>
 * ���������Ɖ�T�[�r�X�����N���X
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3BondExecuteReferenceServiceImpl extends WEB3BondClientRequestService
    implements WEB3BondExecuteReferenceService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondExecuteReferenceServiceImpl.class);
    
    /**
     * ���������Ɖ�����s���B <BR>
     * <BR>
     * this.get�������Ɖ�()���\�b�h���R�[������B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        WEB3GenResponse l_response = null;
        
        //validate���p����
        if (l_request instanceof WEB3BondExecuteReferenceRequest)
        {
            l_response = getExecuteReference(
                (WEB3BondExecuteReferenceRequest) l_request);
        }
        else 
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s��");             
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�������Ɖ�)<BR>
     * ���������Ɖ�������{����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i���������Ɖ�T�[�r�X�jget�������Ɖ�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3BondExecuteReferenceResponse
     * @@throws WEB3BaseException 
     * @@roseuid 44E9470401FC
     */
    protected WEB3BondExecuteReferenceResponse getExecuteReference(
        WEB3BondExecuteReferenceRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getExecuteReference(WEB3BondExecuteReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.validate������t�\( )
        //�V�X�e���ً}��~���E�o�b�`���`�F�b�N���s���B
        WEB3BondTradingTimeManagement.validateOrderAccept();
        
        //1.3.createResponse( )
        WEB3BondExecuteReferenceResponse l_response =
            (WEB3BondExecuteReferenceResponse) l_request.createResponse();
        
        //1.4.create��������������(String)
        String l_strQueryString = createQueryString(l_request.productDiv);
        
        //1.5.create���������f�[�^(String)
        Object[] l_objQueryDatas = createQueryData(l_request.productDiv);
        
        //1.6.create�\�[�g����(���\�[�g�L�[[])
        //�\�[�g�������쐬����B 
        //[����] 
        //�\�[�g�L�[�ꗗ�F ���N�G�X�g�f�[�^.�\�[�g�L�[
        String l_strSortCond = createSortCond(l_request.sortKeys);
        
        //1.7.get�����P�ʈꗗ(String, Object[], String)
        //�w������ɊY������g���������P�ʃI�u�W�F�N�g�̈ꗗ���擾����B 
        //[����] 
        //��������������F ���������������������� 
        //���������f�[�^�R���e�i�F �����������������f�[�^�R���e�i 
        //�\�[�g�����F ���������\�[�g����
        List l_lisOrderUnitLists = 
            getOrderUnitList(l_strQueryString, l_objQueryDatas, l_strSortCond);
        
        //1.8.(*)�Y���f�[�^�Ȃ��iget�����P�ʈꗗ()�̖߂�l == null�j�̏ꍇ
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_orderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        
        WEB3GentradeFinObjectManager l_finObjectManager = 
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        if (l_lisOrderUnitLists == null)
        {
            //1.8.1.�v���p�e�B�Z�b�g
            l_response.pageIndex = "0";
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.details = null;
            
            //1.8.2.���X�|���X�f�[�^
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            //1.9.ArrayList( )
            ArrayList l_lisUnitLists = new ArrayList();
            //1.10.(*)get�����P�ʈꗗ()�̖߂�l�̗v�f����Loop����
            for (int i = 0; i < l_lisOrderUnitLists.size(); i++)
            {
                WEB3BondOrderUnit l_orderUnit = 
                    (WEB3BondOrderUnit) l_lisOrderUnitLists.get(i);
                
                //1.10.1.validate��������\���(�g���������P��)
                //����������\���`�F�b�N���s���B 
                //[����] 
                //�������P�ʁF �����Ώۂ̊g���������P��
                try
                {
                    l_orderManager.validateOrderCancelPossibleStatus(l_orderUnit);
                }
                catch(WEB3BaseException l_ex)
                {
                    //1.10.2.(*)validate��������\���()����O��throw����ꍇ
                    //1.10.2.1. (*)���N�G�X�g�f�[�^.�Ɖ�敪 == "����ꗗ"�̏ꍇ�ALoop�����̐擪�ɖ߂�
                    if (WEB3BondReferenceTypeDef.CANCEL_LIST.equals(l_request.referenceType))
                    {
                        continue;
                    }
                    //1.10.2.2.(*)���N�G�X�g�f�[�^.�Ɖ�敪 == "�������Ɖ�"�̏ꍇ�A�����𑱍s����
                    else if (WEB3BondReferenceTypeDef.EXECUTE_REFERENCE.equals(l_request.referenceType))
                    {

                    }
                }
                
                //1.10.3.getProduct( )
                //�g��������.����ID�ɊY������������I�u�W�F�N�g���擾����B
                WEB3BondProduct l_product = (WEB3BondProduct) l_orderUnit.getProduct();
                BondOrderUnitRow l_unitRow = (BondOrderUnitRow) l_orderUnit.getDataSourceObject();
                
                //1.10.4.���������Ɖ��( )
                //���������Ɖ�׃N���X�̃C���X�^���X�𐶐�����B
                WEB3BondExecuteReferenceDetailUnit l_detailUnit = new WEB3BondExecuteReferenceDetailUnit();
                
                ////1.10.5.�v���p�e�B�Z�b�g
                //(*)�����������������Ɖ�׃I�u�W�F�N�g�̃v���p�e�B�Ɉȉ��̒l���Z�b�g����B
                
                //(*1)�g���������P��.get���^�C�v() != "�O����" �̏ꍇ��null���Z�b�g�B
                //(*2)�g���������P��.get�ʉ݃R�[�h() == null or "T0" �̏ꍇ��null���Z�b�g�B
                //(*3)this.get�㗝���͎�() != null �i�R�[���Z���^�[����̎Q�Ɓj�̏ꍇ
                //�̂݃Z�b�g�B����ȊO�̏ꍇ�Anull�B
                //����ID�@@�@@�@@�@@�@@�@@�@@�@@���@@�g���������P��.getOrderId()�̖߂�l
                l_detailUnit.orderId = l_orderUnit.getOrderId() + "";
                
                //�������@@�@@�@@�@@�@@�@@�@@�@@���@@������.get������()�̖߂�l
                l_detailUnit.productName = l_product.getProductName();
                
                //��ʃR�[�h�@@�@@�@@�@@�@@�@@���@@������.get��ʃR�[�h()�̖߂�l
                l_detailUnit.bondCategCode = l_product.getBondCategCode();
                
                //����敪�@@�@@�@@�@@�@@�@@�@@���@@�g���������P��.get����敪()�̖߂�l
                l_detailUnit.stateDiv = l_orderUnit.getDealDiv();
                
                //���ϋ敪�@@�@@�@@�@@�@@�@@�@@���@@�g���������P��.get���ϋ敪()�̖߂�l
                l_detailUnit.settleDiv = l_orderUnit.getSettlementDiv();
                
                //�z�ʋ��z�@@�@@�@@�@@�@@�@@�@@���@@�g���������P��.get�������敪() == "����"�̏ꍇ�A
                //�g���������P��.getExecutedQuantity()�̖߂�l                
                //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�g���������P��.get�������敪() != "����"�̏ꍇ�A
                //�g���������P��.getQuantity()�̖߂�l
                if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_orderUnit.getOrderExecStatus()))
                {
                    if (!l_unitRow.getExecutedQuantityIsNull())
                    {
                        l_detailUnit.faceAmount = 
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedQuantity());
                    }
                }
                else 
                {
                    if (l_unitRow.getQuantityIsSet())
                    {
                        l_detailUnit.faceAmount = 
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
                    }
                }
                
                //�����P���@@�@@�@@�@@�@@�@@�@@���@@�g���������P��.get�������敪() == "����"�̏ꍇ�A
                //�g���������P��.get���P��()�̖߂�l                
                //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�g���������P��.get�������敪() != "����"�̏ꍇ�A
                //�g���������P��.getLimitPrice()�̖߂�l
                if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_orderUnit.getOrderExecStatus()))
                {
                    if (!l_unitRow.getExecutedPriceIsNull())
                    {
                        l_detailUnit.orderPrice = 
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedPrice());
                    }
                }
                else 
                {
                    if (!l_unitRow.getLimitPriceIsNull())
                    {
                        l_detailUnit.orderPrice = 
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
                    }
                }
                
                //��������i�~�݁j�@@�@@�@@���@@�g���������P��.get��������i�~�݁j()�̖߂�l
                if (!l_unitRow.getTradingPriceIsNull())
                {
                    l_detailUnit.yenTradePrice = 
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getTradingPrice());
                }
                
                //�o�ߗ��q�i�~�݁j�@@�@@�@@���@@�g���������P��.get�o�ߗ��q�i�~�݁j()�̖߂�l
                if (!l_unitRow.getAccruedInterestIsNull())
                {
                    l_detailUnit.yenAccruedInterest = 
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getAccruedInterest());
                }
                
                //��n����i�~�݁j�@@�@@�@@���@@�g���������P��.get��n����i�~�݁j()�̖߂�l
                if (!l_unitRow.getEstimatedPriceIsNull())
                {
                    l_detailUnit.yenDeliveryPrice = 
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getEstimatedPrice());
                }
            
                if (BondTypeEnum.FOREIGN_BOND.equals(l_orderUnit.getBondType()))
                {
                    //�ʉ݃R�[�h(*1)�@@�@@�@@�@@���@@�g���������P��.get�ʉ݃R�[�h()�̖߂�l
                    l_detailUnit.currencyCode = l_orderUnit.getCurrencyCode();
                    
                    //���n����(*1)�@@�@@�@@�@@���@@�g���������P��.get���n����()�̖߂�l
                    l_detailUnit.foreignExecutionDate = l_orderUnit.getForeignExecDate();
                }
                
                if (l_orderUnit.getCurrencyCode() != null && 
                    !WEB3GentradeCurrencyCodeDef.JPY.equals(l_orderUnit.getCurrencyCode()))
                {
                    //�בփ��[�g(*2)�@@�@@�@@�@@���@@�g���������P��.get�������敪() == "����"�̏ꍇ�A
                    //�g���������P��.get���בփ��[�g()�̖߂�l
                    //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�g���������P��.get�������敪() != "����"�̏ꍇ�A
                    //�g���������P��.get��בփ��[�g()�̖߂�l
                    if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_orderUnit.getOrderExecStatus()))
                    {
                        if (!l_unitRow.getExecFxRateIsNull())
                        {
                            l_detailUnit.fxRate = 
                                WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecFxRate());
                        }
                    }
                    else 
                    {
                        if (!l_unitRow.getBaseFxRateIsNull())
                        {
                            l_detailUnit.fxRate = 
                                WEB3StringTypeUtility.formatNumber(l_orderUnit.getBaseFxRate());
                        }
                    }
                    
                    //��������i�O�݁j(*2)�@@���@@�g���������P��.get��������i�O�݁j()�̖߂�l
                    if (!l_unitRow.getForeignTradingPriceIsNull())
                    {
                        l_detailUnit.foreignTradePrice = 
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getForeignTradingPrice());
                    }
                    
                    //�o�ߗ��q�i�O�݁j(*2)�@@���@@�g���������P��.get�o�ߗ��q�i�O�݁j()�̖߂�l
                    if (!l_unitRow.getForeignAccruedInterestIsNull())
                    {
                        l_detailUnit.foreignAccruedInterest = 
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getForeignAccruedInterest());
                    }
                    
                    //��n����i�O�݁j(*2)�@@���@@�g���������P��.get��n����i�O�݁j()�̖߂�l
                    if (!l_unitRow.getForeignEstimatedPriceIsNull())
                    {
                        l_detailUnit.foreignDeliveryPrice = 
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getForeignEstimatedPrice());
                    }
                }
                                
                //���������@@�@@�@@�@@�@@�@@�@@���@@�g���������P��.get�󒍓���()�̖߂�l
                l_detailUnit.orderDate = l_orderUnit.getReceivedDateTime();
                
                //�����@@�@@�@@�@@�@@�@@�@@�@@���@@�g���������P��.get����()�̖߂�l
                l_detailUnit.domesticExecutionDate = l_orderUnit.getExecDate();
                
                if (WEB3BondDealDivDef.RECRUIT.equals(l_orderUnit.getDealDiv()))
                {
                    //�g���������P��.get����敪 == "����h�̏ꍇ�A
                    //��n���@@���@@��n���g���������P��.get������()�̖߂�l
                    l_detailUnit.domesticDeliveryDate = l_orderUnit.getPaymentDate();

                    //���n��n��(*1)���g���������P��.get������()�̖߂�l
                    l_detailUnit.foreignDeliveryDate = l_orderUnit.getPaymentDate();
                }
                else
                {
                    //�g���������P��.get����敪 != "����h�̏ꍇ�A
                    //��n���@@���@@�g���������P��.get��n��()�̖߂�l
                    l_detailUnit.domesticDeliveryDate = l_orderUnit.getDeliveryDate();

                    //���n��n��(*1)���g���������P��.get���n��n��()�̖߂�l
                    l_detailUnit.foreignDeliveryDate = l_orderUnit.getForeignDeliveryDate();
                }
                //(*1)�g���������P��.get���^�C�v() != "�O����"�̏ꍇ��null���Z�b�g�B
                if (!BondTypeEnum.FOREIGN_BOND.equals(l_orderUnit.getBondType()))
                {
                    l_detailUnit.foreignDeliveryDate = null;
                }

                //������ԁ@@�@@�@@�@@�@@�@@�@@���@@�g���������P��.get�������敪()�̖߂�l
                l_detailUnit.executionState = l_orderUnit.getOrderExecStatus();
                
                if (getTrader() != null)
                {
                    //�����o�H�敪(*3)�@@�@@�@@�@@�@@���@@�g���������P��.get�����o�H�敪()�̖߂�l
                    l_detailUnit.orderRootDiv = l_orderUnit.getOrderRootDiv();
                    
                    //�����`���l��(*3)�@@�@@�@@���@@�g���������P��.get���񒍕��̒����`���l��()�̖߂�l
                    l_detailUnit.orderChannel = l_orderUnit.getOrderChannel();
                    
                    //�I�y���[�^�R�[�h(*3)�@@���@@
                    //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrader(�g���������P��.getTraderId()).getTraderCode()�̖߂�l
                    if(l_orderUnit.getTraderId() != 0)
                    {
                        try
                        {
                            l_detailUnit.operatorCode = 
                                l_finObjectManager.getTrader(l_orderUnit.getTraderId()).getTraderCode();
                        }
                        catch (NotFoundException l_ex)
                        {
                            log.error("�e�[�u���ɊY������f�[�^������܂���B",l_ex);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);
                        }
                    }
                }
                
                //����\�t���O�@@���@@validate��������\���()����O��throw���Ȃ��ꍇ��true
                //�@@�@@�@@�@@�@@�@@�@@�@@�@@validate��������\���()����O��throw�����ꍇ��false
                try
                {
                    l_orderManager.validateOrderCancelPossibleStatus(l_orderUnit);
                    l_detailUnit.cancelDiv  = true;
                }
                catch(WEB3BaseException l_ex)
                {
                    l_detailUnit.cancelDiv  = false;
                }
                
                //1.10.6.add(�I�u�W�F�N�g : Object)
                //ArrayList�Ƀv���p�e�B�Z�b�g�������������Ɖ�׃C���X�^���X��ǉ�����B 
                //[����] 
                //�I�u�W�F�N�g�F �v���p�e�B�Z�b�g�������������Ɖ�׃C���X�^���X
                l_lisUnitLists.add(l_detailUnit);
            }
            
            //1.11.(*)ArrayList�̗v�f�� == 0 �̏ꍇ
            if (l_lisUnitLists.size() == 0)
            {
                //1.11.1.�v���p�e�B�Z�b�g
                l_response.pageIndex = "0";
                l_response.totalPages = "0";
                l_response.totalRecords = "0";
                l_response.details = null;
                
                //1.11.2.���X�|���X�f�[�^
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            
            //1.12.WEB3PageIndexInfo(���X�g : List, ����1 : int, ����2 : int)
            //WEB3PageIndexInfo�C���X�^���X�𐶐�����B 
            //[����] 
            //���X�g�F ArrayList 
            //����1�F ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� 
            //����2�F ���N�G�X�g�f�[�^.�y�[�W���\���s��
            
            int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
            int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
            
            WEB3PageIndexInfo l_pageIndexInfo = 
                new WEB3PageIndexInfo(
                    l_lisUnitLists, 
                    l_intRequestPageIndex, 
                    l_intRequestPageSize);
            
            //1.13.getArrayReturned(�N���X : Class)
            //�\���Ώۂ̍��������Ɖ�ׂ̔z����擾����B 
            //[����] 
            //�N���X�F ���������Ɖ��.class
            WEB3BondExecuteReferenceDetailUnit[] l_executeReferenceDetailUnitList = 
                (WEB3BondExecuteReferenceDetailUnit[])l_pageIndexInfo.getArrayReturned(
                    WEB3BondExecuteReferenceDetailUnit.class);
            
            //1.14.�v���p�e�B�Z�b�g
            //�\���y�[�W�ԍ� = WEB3PageIndexInfo.getPageIndex()
            //���y�[�W�� = WEB3PageIndexInfo.getTotalPages()
            //�����R�[�h�� = WEB3PageIndexInfo.getTotalRecords()
            //���������Ɖ�׈ꗗ = �擾�������������Ɖ�ׂ̔z��
            l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
            l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
            l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
            l_response.details = l_executeReferenceDetailUnitList;
        }
        
        //1.15.���b�Z�[�W ���X�|���X�f�[�^
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create��������������)<BR>
     * ����������������쐬���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�ȉ��̕������ԋp����B<BR>
     * <BR>
     * �@@�p�����[�^�D���i�敪����"���ׂĂ̖���" or �p�����[�^�D���i�敪����null �̏ꍇ<BR>
     * �@@�haccount_id = ? and sub_account_id = ?<BR>
     * �@@�@@and (biz_date >= ? or order_exec_status = ?)�h<BR>
     * <BR>
     * �@@�p�����[�^�D���i�敪����"�O���������̂�"<BR>
     *   or �p�����[�^�D���i�敪����"�l�������̂�" �̏ꍇ<BR>
     * �@@�@@�haccount_id = ? and sub_account_id = ?<BR>
     * �@@�@@�@@and (biz_date >= ? or order_exec_status = ?) and bond_type = ? �h<BR>
     * <BR>
     * �@@�p�����[�^�D���i�敪����"�������i�l���������܂ށj" �̏ꍇ<BR>
     * �@@�@@�haccount_id = ? and sub_account_id = ? and (biz_date >= ?<BR>
     * �@@�@@�@@or order_exec_status = ?) and bond_type not in(?) �h<BR>
     * <BR>
     * �@@�p�����[�^�D���i�敪����"�������i�l���������܂܂Ȃ��j" �̏ꍇ<BR>
     * �@@�@@�haccount_id = ? and sub_account_id = ? and (biz_date >= ?<BR>
     * �@@�@@�@@or order_exec_status = ?) and bond_type not in(?, ?) �h<BR>
     * @@param l_strReferenceType - (���i�敪)<BR>
     * ���i�敪
     * @@return String
     */
    protected String createQueryString(String l_strProductDiv)
    {
        final String STR_METHOD_NAME = " createQueryString(String)";
        log.entering(STR_METHOD_NAME);

        String l_strQueryString = null;

        // �P�j�@@�ȉ��̕������ԋp����B
        // �p�����[�^�D���i�敪����"���ׂĂ̖���" or �p�����[�^�D���i�敪����null �̏ꍇ
        if (WEB3BondProductDivDef.ALL_PRODUCT.equals(l_strProductDiv)
            || l_strProductDiv == null)
        {
            // �p�����[�^�D���i�敪����"���ׂĂ̖���" or �p�����[�^�D���i�敪����null �̏ꍇ
            // account_id = ? and sub_account_id = ?
            // and (biz_date >= ? or order_exec_status = ?)
            l_strQueryString = "account_id = ? and sub_account_id = ? "
                + "and (biz_date >= ? or order_exec_status = ?) ";
        }
        else if (WEB3BondProductDivDef.FOREIGN_BOND_ONLY.equals(l_strProductDiv)
            || WEB3BondProductDivDef.DOMESTIC_BOND_INDIVIDUAL.equals(l_strProductDiv))
        {
            // �p�����[�^�D���i�敪����"�O���������̂�"
            // or �p�����[�^�D���i�敪����"�l�������̂�" �̏ꍇ
            // �haccount_id = ? and sub_account_id = ?
            // and (biz_date >= ? or order_exec_status = ?) and bond_type = ? �h
            l_strQueryString = "account_id = ? and sub_account_id = ? "
                + "and (biz_date >= ? or order_exec_status = ?) and bond_type = ? ";
        }
        else if (WEB3BondProductDivDef.DOMESTIC_BOND.equals(l_strProductDiv))
        {
            // �p�����[�^�D���i�敪����"�������i�l���������܂ށj" �̏ꍇ
            // �haccount_id = ? and sub_account_id = ? and (biz_date >= ?
            // and (biz_date >= ? or order_exec_status = ?) and bond_type not in(?) �h
            l_strQueryString = "account_id = ? and sub_account_id = ? "
                + "and (biz_date >= ? or order_exec_status = ?) and bond_type not in(?) ";
        }
        else if (WEB3BondProductDivDef.DOMESTIC_BOND_EXCEPT_INDIVIDUAL.equals(l_strProductDiv))
        {
            // �p�����[�^�D���i�敪����"�������i�l���������܂܂Ȃ��j" �̏ꍇ
            // account_id = ? and sub_account_id = ? and (biz_date >= ?
            // or order_exec_status = ?) and bond_type not in(?, ?) �h
            l_strQueryString = "account_id = ? and sub_account_id = ? "
                + "and (biz_date >= ? or order_exec_status = ?) and bond_type not in(?, ?) ";
        }

        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }
    
    /**
     * (create���������f�[�^)<BR>
     * ���������f�[�^�R���e�i(�FObject[]�j���쐬���A�ԋp����B <BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@this.get�⏕����()���R�[�����A�⏕�����I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �R�j�@@�擾�����⏕����.����ID��Long�N���X�ɕϊ������I�u�W�F�N�g���A<BR> 
     * �@@ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �S�j�@@�擾�����⏕����.�⏕����ID��Long�N���X�ɕϊ������I�u�W�F�N�g���A<BR> 
     * �@@ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �T�j�@@�Ɩ����t(*1)���hYYYYMMDD�h�`���ɕϊ������������ArrayList�ɒǉ�����B<BR> 
     * <BR>
     * �U�j�@@�����i����ԋ敪�j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �V�j�@@���i�敪��ArrayList�ɒǉ�����B<BR>
     * �@@�p�����[�^�D���i�敪����"�O���������̂�"
     *   or �p�����[�^�D���i�敪����"�������i�l���������܂ށj" �̏ꍇ<BR>
     * �@@ArrayList��"���^�C�v�D�O����"��ǉ�����B<BR>
     * <BR>
     * �@@�p�����[�^�D���i�敪����"�������i�l���������܂܂Ȃ��j" �̏ꍇ<BR>
     * �@@ArrayList��"���^�C�v�D�O����"��ǉ�����B<BR>
     * �@@ArrayList��"���^�C�v�D�l��������"��ǉ�����B<BR>
     * <BR>
     * �@@�p�����[�^�D���i�敪����"�l�������̂�" �̏ꍇ<BR>
     * �@@ArrayList��"���^�C�v�D�l��������"��ǉ�����B<BR>
     * �W�j�@@ArrayList.toArray()�̖߂�l��ԋp����B <BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_strReferenceType - (���i�敪)<BR>
     * ���i�敪
     * @@return Object[]
     * @@throws WEB3BaseException 
     */
    protected Object[] createQueryData(String l_strProductDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createQueryData(String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ArrayList�𐶐�����B
        List l_lisQuery = new ArrayList();
        

        //�Q�j�@@this.get�⏕����()���R�[�����A�⏕�����I�u�W�F�N�g���擾����B 
        SubAccount l_subAccount = getSubAccount();
        
        //�R�j�@@�擾�����⏕����.����ID��Long�N���X�ɕϊ������I�u�W�F�N�g���A 
        //�@@ArrayList�ɒǉ�����B 
        l_lisQuery.add(new Long(l_subAccount.getAccountId()));
        
        //�S�j�@@�擾�����⏕����.�⏕����ID��Long�N���X�ɕϊ������I�u�W�F�N�g���A 
        //�@@ArrayList�ɒǉ�����B 
        l_lisQuery.add(new Long(l_subAccount.getSubAccountId()));
        
        //�T�j�@@�Ɩ����t(*1)���hYYYYMMDD�h�`���ɕϊ������������ArrayList�ɒǉ�����B 
        String l_strBizDate = 
            WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd");
        l_lisQuery.add(l_strBizDate);
        
        //�U�j�@@�����i����ԋ敪�j��ArrayList�ɒǉ�����B
        l_lisQuery.add(WEB3BondOrderExecStatusDef.UNEXECUTED);

        //�V�j�@@���i�敪��ArrayList�ɒǉ�����B
        if(WEB3BondProductDivDef.FOREIGN_BOND_ONLY.equals(l_strProductDiv)
            || WEB3BondProductDivDef.DOMESTIC_BOND.equals(l_strProductDiv))
        {
            //�p�����[�^�D���i�敪����"�O���������̂�"
            //or �p�����[�^�D���i�敪����"�������i�l���������܂ށj" �̏ꍇ
            //ArrayList��"���^�C�v�D�O����"��ǉ�����B
            l_lisQuery.add(BondTypeEnum.FOREIGN_BOND);
        }
        else if (WEB3BondProductDivDef.DOMESTIC_BOND_EXCEPT_INDIVIDUAL.equals(l_strProductDiv))
        {
            //�p�����[�^�D���i�敪����"�������i�l���������܂܂Ȃ��j" �̏ꍇ
            //ArrayList��"���^�C�v�D�O����"��ǉ�����B
            //ArrayList��"���^�C�v�D�l��������"��ǉ�����B
            l_lisQuery.add(BondTypeEnum.FOREIGN_BOND);
            l_lisQuery.add(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND);
        }
        else if (WEB3BondProductDivDef.DOMESTIC_BOND_INDIVIDUAL.equals(l_strProductDiv))
        {
            //�p�����[�^�D���i�敪����"�l�������̂�" �̏ꍇ
            //ArrayList��"���^�C�v�D�l��������"��ǉ�����B
            l_lisQuery.add(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND);
        }
        
        //�W�j�@@ArrayList.toArray()�̖߂�l��ԋp����B 
        //(*1)GtlUtils.getTradingSystem().getBizDate()
        log.exiting(STR_METHOD_NAME);
        return l_lisQuery.toArray();
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�������쐬���A�ԋp����B<BR> 
     * <BR>
     * �P�j�@@�p�����[�^.�\�[�g�L�[�ꗗ�̗v�f�����A�Ή�����e�[�u���̗񕨗����A <BR>
     * �@@�@@�����^�~���w���t�����Z�b�g <BR>
     * <BR>
     * �@@�@@�E�L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ� <BR>
     * �@@�@@�@@���e�[�u�����F�������P�ʃe�[�u��(bond_order_unit) <BR>
     * �@@�@@�@@���L�[���ڕ�����i�V���{�����j�́A���b�Z�[�W��`�����Q�� <BR>
     * �@@�@@�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q�� <BR>
     * <BR>
     * �@@�@@�@@�@@�ϊ��O�@@�@@�@@�@@�@@�@@�@@�@@�@@�ϊ��� <BR>
     * -------------�@@�@@�@@�@@----------------------------- <BR>
     * �@@�@@�@@�@@�E�������@@�@@�@@�@@�@@�@@�@@�@@�F�������P�ʃe�[�u��.����ID <BR>
     * �@@�@@�@@�@@�E����敪�@@�@@�@@�@@�@@�@@�@@�F�������P�ʃe�[�u��.��� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������P�ʃe�[�u��.������� <BR>
     * �@@�@@�@@�@@�E���ϋ敪�@@�@@�@@�@@�@@�@@�@@�F�������P�ʃe�[�u��.���ϋ敪 <BR>
     * �@@�@@�@@�@@�E���������@@�@@�@@�@@�@@�@@�@@�F�������P�ʃe�[�u��.�󒍓��� <BR>
     * �@@�@@�@@�@@�E������ԁ@@�@@�@@�@@�@@�@@�@@�F�������P�ʃe�[�u��.�������敪 <BR>
     * <BR>
     * �@@�@@�E�����^�~���w��́A���\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ� <BR>
     * <BR>
     * �Q�j�@@�쐬�����\�[�g�����������ԋp����B<BR>
     * @@param l_sortKeys - (�\�[�g�L�[�ꗗ)<BR>
     * �\�[�g�L�[�ꗗ
     * @@return String
     * @@throws WEB3BaseException 
     */
    protected String createSortCond(WEB3BondSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKeys == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        StringBuffer l_sbReturn = new StringBuffer(); 
        
        //�P�j�@@�p�����[�^.�\�[�g�L�[�ꗗ�̗v�f�����A�Ή�����e�[�u���̗񕨗����A 
        //�@@�����^�~���w���t�����Z�b�g 
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //�E�������@@�@@�@@�@@�@@�@@�@@�@@�F�������P�ʃe�[�u��.����ID 
            if (WEB3BondExecuteReferenceDetailUnitDef.PRODUCT_NAME.equals(
                l_sortKeys[i].keyItem))
            {
                l_sbReturn.append("bond_order_unit.product_id");
            }
            //�@@�@@�@@�@@�E����敪�@@�@@�@@�@@�@@�@@�@@�F�������P�ʃe�[�u��.��� 
            //            �������P�ʃe�[�u��.������� 
            else if (WEB3BondExecuteReferenceDetailUnitDef.STATE_DIV.equals(
                l_sortKeys[i].keyItem))
            {
                l_sbReturn.append("bond_order_unit.deal_type, bond_order_unit.order_type");
            }
            //�@@�@@�@@�@@�E���ϋ敪�@@�@@�@@�@@�@@�@@�@@�F�������P�ʃe�[�u��.���ϋ敪 
            else if(WEB3BondExecuteReferenceDetailUnitDef.SETTLE_DIV.equals(
                l_sortKeys[i].keyItem))
            {
                l_sbReturn.append("bond_order_unit.settlement_div");
            }
            //�@@�@@�@@�@@�E���������@@�@@�@@�@@�@@�@@�@@�F�������P�ʃe�[�u��.�󒍓��� 
            else if(WEB3BondExecuteReferenceDetailUnitDef.ORDER_DATE.equals(
                l_sortKeys[i].keyItem))
            {
                l_sbReturn.append("bond_order_unit.received_date_time");
            }
            //�@@�@@�@@�@@�E������ԁ@@�@@�@@�@@�@@�@@�@@�F�������P�ʃe�[�u��.�������敪 
            else if(WEB3BondExecuteReferenceDetailUnitDef.EXECUTION_STATE.equals(
                l_sortKeys[i].keyItem))
            {
                l_sbReturn.append("bond_order_unit.order_exec_status");
            }
            else
            {
                continue;
            }
            //�E�����^�~���w��́A���\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ�
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                if (i != l_sortKeys.length - 1)
                {
                    l_sbReturn.append("�@@ASC , ");
                }
                else
                {
                    l_sbReturn.append("�@@ASC  ");
                }
            }
            else
            {
                if (i != l_sortKeys.length - 1)
                {
                    l_sbReturn.append("�@@DESC , ");
                }
                else
                {
                    l_sbReturn.append("�@@DESC  ");
                }
            }
        }
        
        //�Q�j�@@�쐬�����\�[�g�����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbReturn.toString();
    }
    
    /**
     * (get�����P�ʈꗗ)<BR>
     * �w������ɊY������g���������P�ʃI�u�W�F�N�g�̈ꗗ��ԋp����B <BR>
     * <BR>
     * �P�j�@@QueryProcessor.doFindAllQuery()�ɂ��ABondOrderUnitRow��List���擾����B <BR>
     * �@@�@@�@@[����] <BR>
     * �@@�@@�@@Row�^�C�v�F �������P��Row.TYPE <BR>
     * �@@�@@�@@��������������F �p�����^.�������������� <BR>
     * �@@�@@�@@�\�[�g�����F �p�����^.�\�[�g���� <BR>
     * �@@�@@�@@�N�G�������F null <BR>
     * �@@�@@�@@���������f�[�^�R���e�i�F �p�����^.���������f�[�^�R���e�i <BR>
     * <BR>
     * �@@�@@�@@���Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B <BR>
     * <BR>
     * �Q�j�@@ArrayList�𐶐�����B <BR>
     * <BR>
     * �R�j�@@�P�j�̖߂�l�̗v�f�����ȉ��̏�����Loop����B <BR>
     * �@@�@@�@@�|�@@�g���������}�l�[�W��.to�������P��((*1)BondOrderUnitRow)���\�b�h���R�[������B<BR> 
     * �@@�@@�@@�|�@@to�������P��()�̖߂�l��ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�@@(*1)BondOrderUnitRow�E�E�E�����Ώۂ̗v�f��BondOrderUnitRow�ɃL���X�g����B <BR>
     * <BR>
     * �S�j�@@ArrayList��ԋp����B<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������
     * @@param l_objQueryDatas - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����
     * @@return ArrayList
     * @@throws WEB3BaseException 
     */
    protected ArrayList getOrderUnitList(
        String l_strQueryString, Object[] l_objQueryDatas, String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@QueryProcessor.doFindAllQuery()�ɂ��ABondOrderUnitRow��List���擾����B 
        // �@@[����] 
        // �@@�@@Row�^�C�v�F �������P��Row.TYPE 
        // �@@�@@��������������F �p�����^.�������������� 
        // �@@�@@�\�[�g�����F �p�����^.�\�[�g���� 
        // �@@�@@�N�G�������F null 
        // �@@�@@���������f�[�^�R���e�i�F �p�����^.���������f�[�^�R���e�i
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    BondOrderUnitRow.TYPE,
                    l_strQueryString,
                    l_strSortCond,
                    null,
                    l_objQueryDatas);
        }
        catch (DataException l_de)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME, l_de);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);            
        }
        
        //���Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B 
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�Q�j�@@ArrayList�𐶐�����B 
        ArrayList l_lisOrderUnits = new ArrayList();
        
        //�R�j�@@�P�j�̖߂�l�̗v�f�����ȉ��̏�����Loop����B  
        // �@@�|�@@to�������P��()�̖߂�l��ArrayList�ɒǉ�����B 
        for (int i = 0; i < l_lisRecords.size(); i++)
        {
            BondOrderUnitRow l_orderUnitRow = (BondOrderUnitRow) l_lisRecords.get(i);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3BondOrderManager l_orderManager = 
                (WEB3BondOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.BOND).getOrderManager();
            
            // �@@�|�@@�g���������}�l�[�W��.to�������P��((*1)BondOrderUnitRow)���\�b�h���R�[������B
            //(*1)BondOrderUnitRow�E�E�E�����Ώۂ̗v�f��BondOrderUnitRow�ɃL���X�g����B 
            OrderUnit l_orderUnit = l_orderManager.toOrderUnit(l_orderUnitRow);
            
            //�|�@@to�������P��()�̖߂�l��ArrayList�ɒǉ�����B 
            l_lisOrderUnits.add(l_orderUnit);
        }

        //�S�j�@@ArrayList��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisOrderUnits;
    }
}
@
