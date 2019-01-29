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
filename	WEB3BondBalanceReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���c���Ɖ�T�[�r�XImpl(WEB3BondBalanceReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/21 ��іQ (���u) �V�K�쐬
Revesion History : 2006/10/12 �ęԍg (���u)  WEB�V�J���W���̌������̑Ή��inew BigDecimal�����j
Revesion History : 2007/03/09 ꎉ�   (���u) �d�l�ύX�E���f��160
Revesion History : 2007/07/17 ���g   (���u) �d�l�ύX�E���f��207
Revesion History : 2007/07/17 �Ӑ� (���u) �d�l�ύX�E���f��208
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondEstimatedAssetCalcResult;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondPositionManager;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondBalanceReferenceDetailUnitDef;
import webbroker3.bd.define.WEB3BondBalanceReferenceTypeDef;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.bd.define.WEB3BondSellPossDivDef;
import webbroker3.bd.message.WEB3BondBalanceReferenceComparator;
import webbroker3.bd.message.WEB3BondBalanceReferenceDetailUnit;
import webbroker3.bd.message.WEB3BondBalanceReferenceRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceResponse;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalResponse;
import webbroker3.bd.message.WEB3BondCurrencyInfo;
import webbroker3.bd.message.WEB3BondSortKey;
import webbroker3.bd.service.delegate.WEB3BondBalanceReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���c���Ɖ�T�[�r�XImpl)<BR>
 * ���c���Ɖ�T�[�r�X�����N���X
 * 
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3BondBalanceReferenceServiceImpl 
    extends WEB3BondClientRequestService    
    implements WEB3BondBalanceReferenceService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceServiceImpl.class);  
    /**
     * @@roseuid 424CC4BE031C
     */
    public WEB3BondBalanceReferenceServiceImpl()
    {

    }
    
    /**
     * ���c���Ɖ�����s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * �����c���Ɖ�c�����v���N�G�X�g�̏ꍇ<BR>
     * �@@this.get�c�����v()���R�[������B<BR>
     * <BR>
     * �����c���Ɖ�N�G�X�g�̏ꍇ<BR>
     * �@@this.get�c���Ɖ�()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 421964C401AD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
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

        if (l_request instanceof WEB3BondBalanceReferenceTotalRequest)
        {
            //���c���Ɖ�c�����v���N�G�X�g
            l_response = this.getBalanceTotal((WEB3BondBalanceReferenceTotalRequest)l_request);
        }
        else if (l_request instanceof WEB3BondBalanceReferenceRequest)
        {
            //���c���Ɖ�N�G�X�g
            l_response = this.getBalanceReference((WEB3BondBalanceReferenceRequest)l_request);
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
     * (get�c���Ɖ�)<BR>
     * ���c���Ɖ�������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���c���Ɖ�T�[�r�X)get�c���Ɖ�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���c���Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3BondBalanceReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 421964E3019C
     */  
    protected WEB3BondBalanceReferenceResponse getBalanceReference(
        WEB3BondBalanceReferenceRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3BondBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1validate( )
        l_request.validate();

        if (l_request.referenceType == null)
        {
            l_request.referenceType = WEB3BondBalanceReferenceTypeDef.ALL_PRODUCT;
        }

        //1.2validate������t�\()
        WEB3BondTradingTimeManagement.validateOrderAccept();
        
        //1.3get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
        
        //1.4create�c���Ɖ�׈ꗗ(�⏕����, String)
        String l_strReferenceDiv = l_request.referenceType;
        
        WEB3BondBalanceReferenceDetailUnit[] l_referenceDetailUnit =
            this.createBalanceReferenceDetailList(l_subAccount, l_strReferenceDiv);
        
        //1.5(*)create�c���Ɖ�׈ꗗ()�̖߂�l == null
        if (l_referenceDetailUnit == null)
        {
            //1.5.1���X�|���X�f�[�^�𐶐�����B
            WEB3BondBalanceReferenceResponse l_response = 
                (WEB3BondBalanceReferenceResponse)l_request.createResponse();
            
            //1.5.2(*)�v���p�e�B�Z�b�g
            //(*)���X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B
            //���X�|���X�f�[�^.�ʉݏ�� = null
            //���X�|���X�f�[�^.�c���Ɖ�� = null
            //���X�|���X�f�[�^.�\���y�[�W�ԍ� = 0
            //���X�|���X�f�[�^.���y�[�W�� = 0
            //���X�|���X�f�[�^.�����R�[�h�� = 0
            l_response.currencyList = null;    
            l_response.balanceReference = null;
            l_response.pageIndex = "0";
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        // 1.6 get�i���ʁj�ʉ�(String)
        WEB3GentradeCurrency[] l_currency = 
            WEB3GentradeCurrency.getGentradeCurrency(
                l_subAccount.getInstitution().getInstitutionCode());
        
        List l_lisCurrencyInfos = new ArrayList();
        //1.7(*)get�i���ʁj�ʉ�()�̗v�f���ALOOP����
        for (int i = 0; i < l_currency.length; i++)
        {
 
            //1.7.1�ʉݏ��( )
            WEB3BondCurrencyInfo l_currencyInfo = new WEB3BondCurrencyInfo();
            
            //1.72�i*2�j�v���p�e�B�Z�b�g
            //�ʉݏ��.�ʉ݃R�[�h�@@=�@@�ʉ݃I�u�W�F�N�g.�ʉ݃R�[�h
            //�ʉݏ��.�בփ��[�g�@@=�@@�ʉ݃I�u�W�F�N�g.���񔄕t�בփ��[�g
            l_currencyInfo.currencyCode = l_currency[i].getCurrencyCode();
            l_currencyInfo.fxRate = 
                WEB3StringTypeUtility.formatNumber(l_currency[i].getSellBaseRate());
          
            l_lisCurrencyInfos.add(l_currencyInfo); 

        }
        
        //1.8sort�c���Ɖ��(���c���Ɖ��[], ���\�[�g�L�[[])
        //�c���Ɖ�ׁF�@@create�c���Ɖ�׈ꗗ()�̖߂�l 
        //�\�[�g�L�[�F�@@���N�G�X�g�f�[�^.�\�[�g�L�[
        this.sortBalanceReferenceDetail(
            l_referenceDetailUnit,
            l_request.sortKeys);
        
        //1.9.WEB3PageIndexInfo(arg0 : �_���r���[::java::lang::Object[], arg1 : int, arg2 : int)
        //arg0�F�@@�\�[�g���ꂽ�c���Ɖ�� 
        //arg1�F�@@���N�G�X�g�f�[�^.�v���y�[�W�ԍ� 
        //arg2�F�@@���N�G�X�g�f�[�^.�y�[�W���\���s��
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_referenceDetailUnit,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));
        //1.10createResponse( )
        WEB3BondBalanceReferenceResponse l_response = (
            WEB3BondBalanceReferenceResponse)l_request.createResponse();
        
        //1.11
        //���X�|���X�f�[�^.�ʉݏ��       ���@@�쐬�����ʉݏ��̔z��
     
        WEB3BondCurrencyInfo[] l_currencyInfos = null;
        if (!l_lisCurrencyInfos.isEmpty())
        {
            l_currencyInfos = new WEB3BondCurrencyInfo[l_lisCurrencyInfos.size()];
            l_lisCurrencyInfos.toArray(l_currencyInfos);
        }
        l_response.currencyList = l_currencyInfos;

        //���X�|���X�f�[�^.�\���y�[�W�ԍ�    ���@@WEB3PageIndexInfo�I�u�W�F�N�g.getPageIndex()
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        //���X�|���X�f�[�^.���y�[�W��      ���@@WEB3PageIndexInfo�I�u�W�F�N�g.getTotalPages()
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        //���X�|���X�f�[�^.�����R�[�h��     ���@@WEB3PageIndexInfo�I�u�W�F�N�g.getTatalRecords()
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        //���X�|���X�f�[�^.�c���Ɖ��     ���@@
        //WEB3PageIndexInfo�I�u�W�F�N�g.getArrayReturned(���c���Ɖ��.class)
        Object[] l_objs = l_pageIndexInfo.getArrayReturned(
            WEB3BondBalanceReferenceDetailUnit.class);
        int l_arrayLen = 0;
        if (l_objs != null)
        {
            l_arrayLen = l_objs.length;
        }
        WEB3BondBalanceReferenceDetailUnit[] l_detailUnits = 
            new WEB3BondBalanceReferenceDetailUnit[l_arrayLen];
        for (int i = 0; i < l_arrayLen; i++)
        {
            l_detailUnits[i] = (WEB3BondBalanceReferenceDetailUnit)l_objs[i];
        }
        
        l_response.balanceReference = l_detailUnits;
        log.exiting(STR_METHOD_NAME);
      
        return l_response;
 
    }
    
    
    /**
     * (create�c���Ɖ�׈ꗗ)<BR>
     * ���c���Ɖ�ׂ̈ꗗ���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���c���Ɖ�T�[�r�X)create�c���Ɖ�׈ꗗ�v�Q��<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@param l_strReferenceDiv - �Ɖ�敪
     * @@return WEB3BondBalanceReferenceDetailUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4219769201E4
     */
    protected WEB3BondBalanceReferenceDetailUnit[] createBalanceReferenceDetailList(
        WEB3GentradeSubAccount l_subAccount, String l_strReferenceDiv)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createBalanceReferenceDetailList(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        //1.1get���ۗL���Y�ꗗ(�⏕����, String, String[], String)
        //�⏕�����F�@@�p�����[�^.�⏕���� 
        //��������������F�@@null 
        //���������f�[�^�R���e�i�F�@@null 
        //�\�[�g�����F�@@null
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3BondPositionManager l_bondPositionManager = (
            WEB3BondPositionManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getPositionManager();
        
        List l_lisAsserts = l_bondPositionManager.getAssets(
            l_subAccount,
            null,
            null,
            null);
        
        //1.2(*)get���ۗL���Y�ꗗ()�̖߂�l == null�̏ꍇ�Anull��ԋp���ďI������B
        if (l_lisAsserts.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //1.3ArrayList( )
        ArrayList l_array = new ArrayList();
        
        //1.4(*)get���ۗL���Y�ꗗ()�̖߂�l�̗v�f(=�ۗL���Y)����Loop����
        WEB3BondProductManager l_BondProductManger = (
            WEB3BondProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();
        
        for (int i = 0; i < l_lisAsserts.size(); i++)
        {
            AssetRow l_assetRow = (AssetRow) l_lisAsserts.get(i);

            try
            {
                Asset l_asset = 
                    l_bondPositionManager.getAsset(l_assetRow.getAssetId());
                //1.4.1get������(long)(long)
                //����ID�F�@@�ۗL���Y.����ID
                WEB3BondProduct l_BondProduct = (
                    WEB3BondProduct)l_BondProductManger.getProduct(
                        l_assetRow.getProductId());

                //is�\���Ώۖ���(String, ������)
                //�Ɖ�敪�@@�F�@@���N�G�X�g�f�[�^.�Ɖ�敪
                //�������@@�F�@@get�������i�j�̖߂�l
                boolean l_blnIsIndicationObjectDetails =
                    this.isIndicationObjectDetails(l_strReferenceDiv, l_BondProduct);

                //is�\���Ώۖ���(String, ������)==false�A�ȍ~�̏�����skip���A
                //���ۗ̕L���Y�I�u�W�F�N�g�̏������s�Ȃ��B
                if (!l_blnIsIndicationObjectDetails)
                {
                    continue;
                }

                //1.4.2calc�T�Z�]���z(�⏕����, double, ������, boolean, boolean)
                //�⏕�����F�@@�����̕⏕���� 
                //���ʁF�@@�ۗL���Y.���� �| �ۗL���Y.get���b�N������  
                //�������F�@@�擾�����������I�u�W�F�N�g 
                //is���t�F�@@false 
                //is���v�Z�F�@@false
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
                WEB3BondBizLogicProvider l_bondBizLogicProvider =
                    (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();
                
                BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_assetRow.getQuantity()));
                BigDecimal l_bdLockedQuantity = new BigDecimal(String.valueOf(l_asset.getLockedQuantity()));
                BigDecimal l_bdSellAbleFaceAmount = l_bdQuantity.subtract(l_bdLockedQuantity);
                
                WEB3BondEstimatedAssetCalcResult l_calcResult = 
                    l_bondBizLogicProvider.calcEstimatedAsset(
                        l_subAccount,
                        l_bdSellAbleFaceAmount.doubleValue(),
                        l_BondProduct,
                        false,
                        false);
                
                //1.4.3validate�ڋq�戵�\����(������, String)
                //�ڋq�戵�\�`�F�b�N���s���A���c���Ɖ��.���p�\�敪�ɃZ�b�g����l�𔻒肷��B 
                //�������F�@@get�������i�j�̖߂�l 
                //����敪�F�@@���p
                WEB3BondOrderManager l_bondOrderManager = 
                    (WEB3BondOrderManager) l_finApp.getTradingModule(
                        ProductTypeEnum.BOND).getOrderManager();
                
                boolean l_validateAccountHandlingPossibleProduct = true;
                
                try
                {
                    l_bondOrderManager.validateAccountHandlingPossibleProduct(
                        l_BondProduct,
                        WEB3BondDealDivDef.SELL);
                }
                catch(WEB3BaseException l_ex)
                {
                    l_validateAccountHandlingPossibleProduct = false;
                }
                
                //1.4.4���c���Ɖ��( )
                WEB3BondBalanceReferenceDetailUnit l_referenceDetailUnit = 
                    new WEB3BondBalanceReferenceDetailUnit();
                
                BondProductRow l_bondProductRow = 
                    (BondProductRow)l_BondProduct.getDataSourceObject();
                //1.4.5(*)�v���p�e�B�Z�b�g
                //(*)���c���Ɖ�׃C���X�^���X�Ƀv���p�e�B���Z�b�g����B
                //���c���Ɖ��.ID     ���@@�ۗL���Y.�ۗL���YID
                l_referenceDetailUnit.id = l_assetRow.getAssetId() + "";
                
                //���c���Ɖ��.���^�C�v      ���@@get������()�̖߂�l.���^�C�v
                l_referenceDetailUnit.bondKind = l_BondProduct.getBondType().intValue() + "";
                
                //���c���Ɖ��.���     ���@@get������()�̖߂�l.��ʃR�[�h
                l_referenceDetailUnit.bondCategCode = l_BondProduct.getBondCategCode();
                
                //���c���Ɖ��.�����R�[�h      ���@@get������()�̖߂�l.�����R�[�h(SONAR)
                l_referenceDetailUnit.productCode = l_BondProduct.getHostProductCode();
                
                //���c���Ɖ��.�񍆃R�[�h      ���@@get������()�̖߂�l.�񍆃R�[�h(SONAR)
                l_referenceDetailUnit.productIssueCode = l_BondProduct.getHostProductIssueCode();
                
                //���c���Ɖ��.������        ���@@get������()�̖߂�l.get������()
                l_referenceDetailUnit.productName = l_BondProduct.getProductName();
                
                //(*1)������.is�]���Ѝ� == true�̏ꍇ�A�Z�b�g�Bfalse�̏ꍇ��NULL���Z�b�g�B
                //���c���Ɖ��.�����敪�@@  ���@@�ۗL���Y.�ŋ敪 == "���"�̏ꍇ�A"���"���Z�b�g�B    
                //"����" or "������������򒥎�"�̏ꍇ�A"����"���Z�b�g�B(*1)
                if (l_BondProduct.isExperienceDivWt())
                {
                    if (TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
                    {
                        l_referenceDetailUnit.taxType = WEB3TaxTypeDef.NORMAL;
                    }
                    else if (TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType()) || 
                        TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_assetRow.getTaxType()))
                    {
                        l_referenceDetailUnit.taxType = WEB3TaxTypeDef.SPECIAL;
                    }
                }
                else
                {
                    l_referenceDetailUnit.taxType =  null;
                }
                
                //���c���Ɖ��.���p�\���� ���@@�ۗL���Y.get���ʁ|�ۗL���Y.get���b�N������                
                l_referenceDetailUnit.sellAbleQty = WEB3StringTypeUtility.formatNumber(
                    l_bdSellAbleFaceAmount.doubleValue());

                //���c���Ɖ��.���p�s�\���� ���@@�ۗL���Y.���t�s�\����
                l_referenceDetailUnit.sellDisableQty = 
                    WEB3StringTypeUtility.formatNumber(l_assetRow.getQuantityCannotSell());
                
                //���c���Ɖ��.�ʉ�     ���@@get������()�̖߂�l.�ʉ݃R�[�h
                l_referenceDetailUnit.currencyCode = l_BondProduct.getCurrencyCode();
                
                //���c���Ɖ��.���p�i�]���j�P��   ���@@get������()�̖߂�l.���p�P��
                if(!l_bondProductRow.getSellPriceIsNull())
                {
                    l_referenceDetailUnit.sellEvaluationPrice = 
                        WEB3StringTypeUtility.formatNumber(l_BondProduct.getSellPrice());
                }
                
                //���c���Ɖ��.�T�Z�]���z�i�~�݁j  ���@@���T�Z�]���z�v�Z����.�T�Z�]���z�i�~�݁j���Z�b�g�B
                l_referenceDetailUnit.yenEstimatedAsset = 
                    WEB3StringTypeUtility.formatNumber(l_calcResult.getEstimatedAsset().doubleValue());
                //l_calcResult.getEstimatedAsset().toString()
                //���c���Ɖ��.�T�Z�]���z�i�O�݁j  ���@@���T�Z�]���z�v�Z����.�T�Z�]���z�i�O�݁j���Z�b�g�B
                if (l_calcResult.getForeignEstimatedAsset() != null)
                {
                    l_referenceDetailUnit.foreignEstimatedAsset = 
                        WEB3StringTypeUtility.formatNumber(
                            l_calcResult.getForeignEstimatedAsset().doubleValue());
                }
                //���c���Ɖ��.���s��        ���@@get������()�̖߂�l.���s��
                l_referenceDetailUnit.issueDate = l_BondProduct.getIssueDate();
                
                //���c���Ɖ��.���s���i       ���@@get������()�̖߂�l.���s���i
                l_referenceDetailUnit.issuePrice = 
                    WEB3StringTypeUtility.formatNumber(l_BondProduct.getIssuePrice());
                
                //���c���Ɖ��.���ғ�        ���@@get������()�̖߂�l.���ғ�
                l_referenceDetailUnit.maturityDate = l_BondProduct.getMaturityDate();
                
                //���c���Ɖ��.�N�ԗ�������       ���@@get������()�̖߂�l.�N�ԗ�����
                l_referenceDetailUnit.yearlyInterestPayments = 
                    l_BondProduct.getYearlyInterestPayments() + "";
                
                //���c���Ɖ��.�������P       ���@@get������()�̖߂�l.�������P
                l_referenceDetailUnit.interestPaymentDay1 = l_BondProduct.getInterestPaymentDay1();
                
                //���c���Ɖ��.�������Q       ���@@get������()�̖߂�l.�������Q
                l_referenceDetailUnit.interestPaymentDay2 = l_BondProduct.getInterestPaymentDay2();
                
                //���c���Ɖ��.�N����        ���@@get������()�̖߂�l.����
                l_referenceDetailUnit.coupon = 
                    WEB3StringTypeUtility.formatNumber(l_BondProduct.getCoupon());
                
                //���c���Ɖ��.���p�\�敪 ���@@validate�ڋq�戵�\����()����O��throw���Ȃ� &&
                //                                                ���c���Ɖ��.���p�\���� > 0 �̏ꍇ�́h�戵�h���Z�b�g�B
                //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@      ����ȊO�́h�戵�s�h���Z�b�g�B               
                if((l_validateAccountHandlingPossibleProduct) && (Double.parseDouble(l_referenceDetailUnit.sellAbleQty)) > 0)
                {
                    l_referenceDetailUnit.sellPossDiv = WEB3BondSellPossDivDef.SELL_POSS;
                }
                else
                {
                    l_referenceDetailUnit.sellPossDiv = WEB3BondSellPossDivDef.SELL_POSS_NO;
                }
                
                //1.4.6add(arg0 : Object)
                //ArrayList�ɍ��c���Ɖ�ׂ�ǉ�����B 
                //arg0�F�@@�v���p�e�B�Z�b�g�������c���Ɖ��
                l_array.add(l_referenceDetailUnit);

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
        //toArray( )
        //���c���Ɖ�ׂ̔z����쐬����B
        WEB3BondBalanceReferenceDetailUnit[] l_bondBalanceReferenceUnits = 
            new WEB3BondBalanceReferenceDetailUnit[l_array.size()];
        
        l_array.toArray(l_bondBalanceReferenceUnits);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_bondBalanceReferenceUnits;
    }
   
    /**
     * (sort�c���Ɖ��)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Ďc���Ɖ�ׂ̃\�[�g���s���B <BR>
     * <BR>
     * �P�jArrayList�𐶐�����B  <BR>
     * <BR>
     * �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B <BR>
     * �@@�Q�|�P�j�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ������r���ڂ�Comparator�𐶐����A <BR>
     * �@@�@@�@@ArrayList�ɒǉ�����B  <BR>
     * <BR>
     * �@@�@@�@@�@@�\�[�g�L�[.�L�[���ڂ��A<BR>
     * �@@�@@�@@�@@["������"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�ȉ��̍��ڂ̐����������c���Ɖ�Comparator�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@��orderBy�͈ꗥ�A�\�[�g�L�[.�����^�~�����Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�E��r���ځF���c���Ɖ��.���<BR>
     * �@@�@@�@@�@@�@@�@@�E��r���ځF���c���Ɖ��.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�E��r���ځF���c���Ɖ��.�񍆃R�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�ȉ��̈����ɂč��c���Ɖ�Comparator�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]  <BR>
     * �@@�@@�@@�@@�@@�@@orderBy�F �\�[�g�L�[.�����^�~�� <BR>
     * �@@�@@�@@�@@�@@�@@��r���ځF�@@�\�[�g�L�[.�L�[���� <BR>
     * <BR>
     * �@@�@@�@@�AArrayList�ɐ�������Comparator��ǉ�����B <BR>
     * <BR>
     * �R�jWEB3ArraysUtility.sort()���\�b�h���R�[������B  <BR>
     * <BR>
     * �@@[sort()�ɃZ�b�g����p�����[�^]  <BR>
     * �@@�@@obj�F�@@�p�����[�^.�c���Ɖ�� <BR>
     * �@@�@@com�F�@@��������ArrayList.toArray()�̖߂�l <BR>
     * @@param l_bondBalanceReferenceDetailUnits - (�c���Ɖ��)<BR>
     * ���c���Ɖ�׃I�u�W�F�N�g�̔z��<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * ���\�[�g�L�[�̔z��<BR>
     * @@roseuid 421979C60148
     */
    protected void sortBalanceReferenceDetail(
        WEB3BondBalanceReferenceDetailUnit[] l_bondBalanceReferenceDetailUnits,
        WEB3BondSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME =
            "sortBalanceReferenceDetail(WEB3BondBalanceReferenceDetailUnit[] WEB3BondSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        // �P�jArrayList�𐶐�����B   
        ArrayList l_lisComparators = new ArrayList();
        
        // �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B 
        // �@@�Q�|�P�j�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ������r���ڂ�Comparator�𐶐����A 
        // �@@�@@�@@ArrayList�ɒǉ�����B  
        int l_intSortKeysLen = 0; 
        if (l_sortKeys != null)
        {
            l_intSortKeysLen = l_sortKeys.length;
        }
        for (int i = 0; i < l_intSortKeysLen; i++)
        {
            //�@@�\�[�g�L�[.�L�[���ڂ��A
            // ["������"�̏ꍇ]
            // �@@�ȉ��̍��ڂ̐����������c���Ɖ�Comparator�𐶐�����B
            // �@@��orderBy�͈ꗥ�A�\�[�g�L�[.�����^�~�����Z�b�g�B
            // �@@�@@�E��r���ځF���c���Ɖ��.���
            // �@@�@@�E��r���ځF���c���Ɖ��.�����R�[�h
            // �@@�@@�E��r���ځF���c���Ɖ��.�񍆃R�[�h
            if (WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_NAME.equals(l_sortKeys[i].keyItem))
            {              
                l_lisComparators.add(new WEB3BondBalanceReferenceComparator(
                    l_sortKeys[i].ascDesc, 
                    WEB3BondBalanceReferenceDetailUnitDef.BOND_CATEG_CODE));
                             
                l_lisComparators.add(new WEB3BondBalanceReferenceComparator(
                    l_sortKeys[i].ascDesc,
                    WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_CODE));
                
                l_lisComparators.add(new WEB3BondBalanceReferenceComparator(
                    l_sortKeys[i].ascDesc,
                    WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_ISSUE_CODE));
            }
            // [��L�ȊO�̏ꍇ]
            // �@@�ȉ��̈����ɂč��c���Ɖ�Comparator�𐶐�����B 
            // �@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]  
            // �@@�@@orderBy�F �\�[�g�L�[.�����^�~�� 
            // �@@�@@��r���ځF�@@�\�[�g�L�[.�L�[���� 
            else
            {
                l_lisComparators.add(new WEB3BondBalanceReferenceComparator(
                    l_sortKeys[i].ascDesc,
                    l_sortKeys[i].keyItem));
            }

        }
        //�AArrayList�ɐ�������Comparator��ǉ�����B 
        Comparator[] l_comparators = new Comparator[l_lisComparators.size()];
        l_lisComparators.toArray(l_comparators);
        
        // �R�jWEB3ArraysUtility.sort()���\�b�h���R�[������B  
        // [sort()�ɃZ�b�g����p�����[�^]  
        // obj�F�@@�p�����[�^.�c���Ɖ�� 
        // com�F�@@��������ArrayList.toArray()�̖߂�l 
        WEB3ArraysUtility.sort(l_bondBalanceReferenceDetailUnits,l_comparators);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�c�����v)<BR>
     * ���c�����v���������{����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i���c���Ɖ�T�[�r�X�jget�c�����v�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - ���c���Ɖ�c�����v���N�G�X�g
     * @@return WEB3BondBalanceReferenceTotalResponse - ���c���Ɖ�c�����v���X�|���X
     * @@throws WEB3BaseException
     */
    protected WEB3BondBalanceReferenceTotalResponse getBalanceTotal(
        WEB3BondBalanceReferenceTotalRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBalanceTotal(WEB3BondBalanceReferenceTotalRequest)";
        log.entering(STR_METHOD_NAME);

        //�T�Z�]���z�i�~�݁j���v
        BigDecimal l_bdYenEstimatedTotalAsset = new BigDecimal("0");

        //validate()
        l_request.validate();

        //validate������t�\( )
        //�V�X�e����"�o�b�`��"�A"�ً}��~��"�ł��邩�ǂ����̃`�F�b�N���s��
        WEB3BondTradingTimeManagement.validateOrderAccept();

        //get�⏕����( )
        //�⏕�������擾����
        SubAccount l_subAccount = this.getSubAccount();

        //get���ۗL���Y�ꗗ
        //�⏕�����F�@@�p�����[�^.�⏕����
        //��������������F�@@null
        //���������f�[�^�R���e�i�F�@@null
        //�\�[�g�����F�@@null
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3BondPositionManager l_bondPositionManager =
            (WEB3BondPositionManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getPositionManager();
        List l_lisAssets = l_bondPositionManager.getAssets(l_subAccount, null, null, null);

        //createResponse( )
        WEB3BondBalanceReferenceTotalResponse l_response =
            (WEB3BondBalanceReferenceTotalResponse)l_request.createResponse();

        //get���ۗL���Y�ꗗ()�̖߂�l�̗v�f(=�ۗL���Y)����Loop����
        Iterator l_iterator = l_lisAssets.iterator();
        while (l_iterator.hasNext())
        {
            WEB3BondProductManager l_bondProductManger =
                (WEB3BondProductManager)l_finApp.getTradingModule(
                    ProductTypeEnum.BOND).getProductManager();

            //�ۗL���Y
            AssetRow l_assetRow = (AssetRow)l_iterator.next();
            //����ID
            long l_lngProductID = l_assetRow.getProductId();

            //get������(long)
            WEB3BondProduct l_bondProduct =
                (WEB3BondProduct)l_bondProductManger.getBondProduct(l_lngProductID);

            //is�\���Ώۖ���(String, ������)
            //�@@�@@�Ɖ�敪�@@�F�@@���N�G�X�g�f�[�^.�Ɖ�敪
            //    �������@@�F�@@get�������i�j�̖߂�l
            boolean l_blnIsIndicationObjectDetails =
                this.isIndicationObjectDetails(l_request.referenceType, l_bondProduct);

            //is�\���Ώۖ��ׁi�j�̖߂�l == false �ꍇ�A�ȍ~�̏�����skip���A
            //���ۗ̕L���Y�I�u�W�F�N�g�̏������s�Ȃ��B
            if (!l_blnIsIndicationObjectDetails)
            {
                continue;
            }

            //calc�T�Z�]���z
            //�⏕�����F�@@�擾�����⏕����
            //���ʁF�@@�ۗL���Y.���� �| �ۗL���Y.get���b�N������
            //�������F�@@�擾�����������I�u�W�F�N�g
            //is���t�F�@@false
            //is���v�Z�F�@@false
            WEB3BondBizLogicProvider l_bizLogicProvider =
                (WEB3BondBizLogicProvider)l_finApp.getTradingModule(
                    ProductTypeEnum.BOND).getBizLogicProvider();

            //���ʁF�@@�ۗL���Y.���� �| �ۗL���Y.get���b�N������
            Asset l_asset = null;
            try
            {
                l_asset = l_bondPositionManager.getAsset(l_assetRow.getAssetId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_asset.getQuantity()));
            BigDecimal l_bdLockedQuantity = new BigDecimal(String.valueOf(l_asset.getLockedQuantity()));
            double l_dblQuantityT = (l_bdQuantity.subtract(l_bdLockedQuantity)).doubleValue();

            WEB3BondEstimatedAssetCalcResult l_bondEstimatedAssetCalcResult =
                l_bizLogicProvider.calcEstimatedAsset(
                    l_subAccount,
                    l_dblQuantityT,
                    l_bondProduct,
                    false,
                    false);

            //���T�Z�]���z�v�Z����.�T�Z�]���z�i�~�݁j��
            //���X�|���X�f�[�^.�T�Z�]���z�i�~�݁j���v�ɉ��Z����B
            l_bdYenEstimatedTotalAsset =
                l_bdYenEstimatedTotalAsset.add(l_bondEstimatedAssetCalcResult.getEstimatedAsset());
        }

        //���X�|���X�f�[�^.�T�Z�]���z�i�~�݁j���v
        l_response.yenEstimatedTotalAsset = l_bdYenEstimatedTotalAsset.toString();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (is�\���Ώۖ���)<BR>
     * �\���Ώۖ��ׂ��ǂ����`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�Ɖ�敪 == �h�O���������̂݁h ����<BR>
     *�@@�@@������.���^�C�v �� �h�O�����h�̏ꍇ�A<BR>
     *�@@�@@false��ԋp����B<BR>
     * <BR>
     * �A�Ɖ�敪 == �h�������i�l���������܂ށj�h ����<BR>
     *�@@�@@������.���^�C�v == �h�O�����h�̏ꍇ�A<BR>
     *�@@�@@false��ԋp����B<BR>
     * <BR>
     * �B�Ɖ�敪 == �h�������i�l���������܂܂Ȃ��j�h ����<BR>
     *�@@�@@�i�@@������.���^�C�v == �h�O�����h<BR>
     *�@@ �܂��́A������.���^�C�v == �h�l�������h�@@�j�̏ꍇ�A<BR>
     *�@@�@@false��ԋp����B<BR>
     * <BR>
     * �C�Ɖ�敪 == �h�l�������̂݁h ����<BR>
     *�@@�@@������.���^�C�v ��  �h�l�������h�̏ꍇ�A<BR>
     *�@@�@@false��ԋp����B<BR>
     * <BR>
     * �D��L�ȊO�̏ꍇ�Atrue��ԋp����B<BR>
     * @@param l_strReferenceType - (�Ɖ�敪)<BR>
     * �Ɖ�敪<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@return boolean
     */
    protected boolean isIndicationObjectDetails(String l_strReferenceType, WEB3BondProduct l_bondProduct)
    {
        final String STR_METHOD_NAME = "isIndicationObjectDetails(String, WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);

        //�@@�Ɖ�敪 == �h�O���������̂݁h ���� ������.���^�C�v �� �h�O�����h�̏ꍇ�A
        //false��ԋp����B
        if (WEB3BondBalanceReferenceTypeDef.FOREIGN_BOND_ONLY.equals(l_strReferenceType)
            && !BondTypeEnum.FOREIGN_BOND.equals(l_bondProduct.getBondType()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�A�Ɖ�敪 == �h�������i�l���������܂ށj�h ���� ������.���^�C�v == �h�O�����h�̏ꍇ�A
        //false��ԋp����B
        else if (WEB3BondBalanceReferenceTypeDef.DOMESTIC_BOND.equals(l_strReferenceType)
            && BondTypeEnum.FOREIGN_BOND.equals(l_bondProduct.getBondType()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�B�Ɖ�敪 == �h�������i�l���������܂܂Ȃ��j�h ���� �i�@@������.���^�C�v == �h�O�����h
        //�܂��́A������.���^�C�v == �h�l�������h�@@�j�̏ꍇ�A
        //false��ԋp����B
        else if (WEB3BondBalanceReferenceTypeDef.DOMESTIC_BOND_EXCEPT_INDIVIDUAL.equals(l_strReferenceType)
            && (BondTypeEnum.FOREIGN_BOND.equals(l_bondProduct.getBondType())
            || BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND.equals(l_bondProduct.getBondType())))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�C�Ɖ�敪 == �h�l�������̂݁h ���� ������.���^�C�v ��  �h�l�������h�̏ꍇ�A
        //false��ԋp����B
        else if (WEB3BondBalanceReferenceTypeDef.DOMESTIC_BOND_INDIVIDUAL.equals(l_strReferenceType)
            && !BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND.equals(l_bondProduct.getBondType()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        //�D��L�ȊO�̏ꍇ�Atrue��ԋp����B
        return true;
    }
}
@
