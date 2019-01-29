head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�����������ꗗ�T�[�r�XImpl(WEB3AdminFeqProductListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/24 �s�p (���u) �V�K�쐬
                 : 2005/08/01 ��O��(���u) ���r���[   
*/
package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3AdminFeqProductListRequest;
import webbroker3.feq.message.WEB3AdminFeqProductListResponse;
import webbroker3.feq.message.WEB3FeqProductUnit;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.feq.service.delegate.WEB3AdminFeqProductListService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҊO�����������ꗗ�T�[�r�XImpl)<BR>
 * �Ǘ��ҊO�����������ꗗ�T�[�r�X�����N���X<BR>
 */
public class WEB3AdminFeqProductListServiceImpl implements WEB3AdminFeqProductListService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqProductListServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F203D8
     */
    public WEB3AdminFeqProductListServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��ҊO�����������ꗗ�T�[�r�X�������s���B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 421AA20C0057
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqProductListRequest)
        {
            l_response = 
                this.getProductInfoList((WEB3AdminFeqProductListRequest)l_request);
        }        
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�������ꗗ)<BR>
     * �Ǘ��ҊO�����������ꗗ���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)�����ꗗ�jget�������ꗗ�v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO�������������ꗗ���N�G�X�g<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqProductListResponse
     * @@throws WEB3BaseException
     * @@roseuid 429725DA031C
     */
    protected WEB3AdminFeqProductListResponse getProductInfoList(WEB3AdminFeqProductListRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProductInfoList(WEB3AdminFeqProductListRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate()
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B");
        }
    
        //1.3:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_PRODUCT_MANAGE, false);
        
        //1.4:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5:create�\�[�g����(�O�������\�[�g�L�[[])
        String l_strSortCond = this.createSortCond(l_request.sortKeys);
        
        //1.6:get����(String, String, String, String, boolean, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "FinApp�����݂��Ȃ��B");
        }
        
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        if (l_tradingModule == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "TradingModule�����݂��Ȃ��B");
        }
        
        WEB3FeqProductManager l_productMgr = (WEB3FeqProductManager)l_tradingModule.getProductManager();
        if (l_productMgr == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "�O�������v���_�N�g�}�l�[�W�����݂��Ȃ��B");
        }
        
        WEB3FeqProduct[] l_products = l_productMgr.getProduct(
            l_strInstitutionCode,
            l_request.marketCode,
            l_request.productCode,
            l_request.productName,
            false,
            l_strSortCond);
            
        //1.7:WEB3PageIndexInfo(l_objs�i=get�L������()�̖߂�l�j : Object[], 
        //    l_intRequestPageIndex : int, l_intRequestPageSize : int)
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_products, 
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));
        
        //1.8:getArrayReturned( )    
        WEB3FeqProduct[] l_feqProducts = (WEB3FeqProduct[])
            l_pageIndexInfo.getArrayReturned(WEB3FeqProduct.class);
        
        WEB3FeqProductUnit[] l_productUnits = null;
        
        //1.9:(*) �\���Ώۃy�[�W�ɊY������I�u�W�F�N�g�v�f��LOOP����
        if (l_feqProducts != null && l_feqProducts.length > 0)
        {
            int l_intCnt = l_feqProducts.length;
            
            l_productUnits = new WEB3FeqProductUnit[l_intCnt];
            
            for (int i = 0; i < l_intCnt; i++)
            {
                //1.9.1:�O��������������( )
                l_productUnits[i] = new WEB3FeqProductUnit();
                
                //1.9.2:create�O��������������(�O��������������, �O����������)
                this.createFeqProductDetail(l_productUnits[i], l_feqProducts[i]);
            }
        }
        
        //1.10:get�O���s��(String)
        WEB3GentradeFinObjectManager l_finObjManager = 
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        if (l_finObjManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "�g�����Z�I�u�W�F�N�g�}�l�[�W�������݂��Ȃ��B");
        }
        
        Market[] l_markets = l_finObjManager.getFeqMarkets(l_strInstitutionCode);
        
        //1.11:getTotalPages( )
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();
        
        //1.12:getTotalRecords( )
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();
        
        //1.13:getPageIndex( )
        int l_intPageIndex = l_pageIndexInfo.getPageIndex();
        
        //1.14:createResponse( )
        WEB3AdminFeqProductListResponse l_response = 
            (WEB3AdminFeqProductListResponse)l_request.createResponse();
            
        //1.15:(*) �v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
        //�s��R�[�h�ꗗ�F�@@get�s��()�Ŏ擾�����e�s��.getMarketCode()
        String[] l_strMarketCodes = null;
        
        if (l_markets != null && l_markets.length > 0)
        {
            List l_lisMarketCodes = new ArrayList();
            int l_intCnt = l_markets.length;
            
            for (int i = 0; i < l_intCnt; i++)
            {
                Market l_market = l_markets[i];
                
                if (l_market != null)
                {
                    l_lisMarketCodes.add(l_market.getMarketCode());
                }                    
            }
            
            if (!l_lisMarketCodes.isEmpty())
            {
                l_strMarketCodes = new String[l_lisMarketCodes.size()];
                l_lisMarketCodes.toArray(l_strMarketCodes);
            }                        
        }
        l_response.marketList = l_strMarketCodes;
        
        //�������ꗗ�F�@@���������O��������������[]
        l_response.productCodeNames = l_productUnits;
        
        //���y�[�W���F�@@getTotalPages()
        l_response.totalPages = Integer.toString(l_intTotalPages);
        
        //�����R�[�h���F�@@getTotalRecords()
        l_response.totalRecords = Integer.toString(l_intTotalRecords);
        
        //�\���y�[�W�ԍ��F�@@getPageIndex
        l_response.pageIndex = Integer.toString(l_intPageIndex);
               
        log.exiting(STR_METHOD_NAME);
        
        return l_response;

        
    }
    
    /**
     * (create�O��������������)<BR>
     * �w������̓��e�ŁA�O�������������׃��b�Z�[�W�I�u�W�F�N�g�v���p�e�B�ɒl<BR>
     * ���Z�b�g����B<BR>
     * <BR>
     * �O�������������ׂ̊e�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B<BR>
     * <BR>
     * �@@�����R�[�h�F�@@����.getProductCode()<BR>
     * �@@�������F�@@����.get�\��������()<BR>
     * �@@���t�\�F�@@�i�������.is����~() == true�j�̏ꍇ�Afalse�B�ȊOtrue�B<BR>
     * �@@���t�\�F�@@�i�������.is����~() == true�j�̏ꍇ�Afalse�B�ȊOtrue�B<BR>
     * �@@���n�����R�[�h�F�@@����.���n�����R�[�h<BR>
     * �@@���t�P�ʁF�@@�������.���t�P��<BR>
     * �@@�Œᔃ�t�P�ʁF�@@�������.�Œᔃ��������<BR>
     * �@@���t�P�ʁF�@@�������.���t�P��<BR>
     * �@@�Œᔄ�t�P�ʁF�@@�������.�Œᔄ��������<BR>
     *   �s��R�[�h�F�@@����.get�s��R�[�h()<BR>
     * �@@���o�^���F�@@�������.���i�o�^�j��<BR>
     * �@@���p�~���F�@@�������.���i�o�^�j�p�~��<BR>
     * <BR>
     * ���@@��������̎擾�F�@@����.get�������()�ɂĎ擾����B<BR>
     * @@param l_feqProductDetail - (�O��������������)<BR>
     * �O�������������׃��b�Z�[�W<BR>
     * @@param l_product - (����)<BR>
     * �O�����������I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 429725DA033C
     */
    protected void createFeqProductDetail(WEB3FeqProductUnit l_feqProductDetail, WEB3FeqProduct l_product) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createFeqProductDetail(WEB3FeqProductUnit, WEB3FeqProduct) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqProductDetail == null || l_product == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�����w��(null)�ł��B");
        }
        
        //�O�������������ׂ̊e�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
        //�����R�[�h�F�@@����.getProductCode()
        l_feqProductDetail.productCode = l_product.getProductCode();
        
        //�������F�@@����.get�\��������()
        l_feqProductDetail.productName = l_product.getDisplayProductName();
        
        //���t�\�F�@@�i�������.is����~() == true�j�̏ꍇ�Afalse�B�ȊOtrue�B
        WEB3FeqTradedProduct l_tradedProduct = l_product.getFeqTradedProduct();
        if (l_tradedProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "������������݂��Ȃ��B");
        }
        
        if (l_tradedProduct.isBuyStop())
        {
            l_feqProductDetail.buyPossFlag = false;
        }
        else
        {
            l_feqProductDetail.buyPossFlag = true;
        }
        
        //���t�\�F�@@�i�������.is����~() == true�j�̏ꍇ�Afalse�B�ȊOtrue�B
        if (l_tradedProduct.isSellStop())
        {
            l_feqProductDetail.sellPossFlag = false;
        }
        else
        {
            l_feqProductDetail.sellPossFlag = true;
        }
        
        //���n�����R�[�h�F�@@����.���n�����R�[�h
        l_feqProductDetail.localProductCode = l_product.getOffshoreProductCode();
        
        //���t�P�ʁF�@@�������.���t�P��
        l_feqProductDetail.buyUnit = 
            WEB3StringTypeUtility.formatNumber(l_tradedProduct.getBuyOrderLotSize());
        
        //�Œᔃ�t�P�ʁF�@@�������.�Œᔃ��������
        l_feqProductDetail.minBuyUnit = 
            WEB3StringTypeUtility.formatNumber(l_tradedProduct.getBuyOrderMinimumQuantity());
            
        //���t�P�ʁF�@@�������.���t�P��
        l_feqProductDetail.sellUnit = 
            WEB3StringTypeUtility.formatNumber(l_tradedProduct.getSellOrderLotSize());               
        
        //�Œᔄ�t�P�ʁF�@@�������.�Œᔄ��������
        l_feqProductDetail.minSellUnit = 
            WEB3StringTypeUtility.formatNumber(l_tradedProduct.getSellOrderMinimumQuantity());
        
        //�s��R�[�h�F�@@����.get�s��R�[�h()
        l_feqProductDetail.marketCode = l_product.getMarketCode();
             
        //���o�^���F�@@�������.���i�o�^�j��
        l_feqProductDetail.listedDate = WEB3DateUtility.toDay(l_tradedProduct.getListedDate());
           
        //���p�~���F�@@�������.���i�o�^�j�p�~��
        l_feqProductDetail.unlistedDate = WEB3DateUtility.toDay(l_tradedProduct.getUnlistedDate());
        
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �w��̓��e�ŁA�O�����������e�[�u���ɑ΂���\�[�g����������<BR>
     * �iorder by��j��ҏW���ԋp����B<BR>
     * ���L�[�Ƃ��āu�������v���w�肳��Ă���ꍇ�A�\�[�g�����Ƃ���e�[�u��<BR>
     * �̍��ڂ́A�u�������i�J�i�j�v�Ƃ���B<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 429725DA034B
     */
    protected String createSortCond(WEB3ForeignSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createSortCond(WEB3ForeignSortKey[]) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKeys == null || l_sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�����w��(null)�ł��B");
        }
        
        StringBuffer l_sbOrderBy = new StringBuffer();
        
        int l_intCnt = l_sortKeys.length;
        
        List l_listKeys = new ArrayList();
        for (int i = 0; i < l_intCnt; i++)
        {
            String l_strKeyItem = l_sortKeys[i].keyItem;
            String l_strAOrD = l_sortKeys[i].ascDesc;
            
            if (!l_listKeys.contains(l_strKeyItem))
            {
                l_listKeys.add(l_sortKeys[i].keyItem);
                
                //�O��������������.�����R�[�h
                if (WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(l_strKeyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_strAOrD))
                    {
                        l_sbOrderBy.append("product_code asc, ");
                    }
                    else
                    {
                        l_sbOrderBy.append("product_code desc, ");
                    }
                    
                }
                //�O��������������.���n�����R�[�h
                else if (WEB3FeqSortKeyItemNameDef.OFFSHORE_PRODUCT_CODE.equals(l_strKeyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_strAOrD))
                    {
                        l_sbOrderBy.append("offshore_product_code asc, ");
                    }
                    else
                    {
                        l_sbOrderBy.append("offshore_product_code desc, ");
                    }
                    
                }//�L�[�Ƃ��āu�������v���w�肳��Ă���ꍇ�A�\�[�g�����Ƃ���e�[�u��
                //�̍��ڂ́A�u�������i�J�i�j�v�Ƃ���B
                else if (WEB3FeqSortKeyItemNameDef.PRODUCT_NAME.equals(l_strKeyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_strAOrD))
                    {
                        l_sbOrderBy.append("standard_name_kana asc, ");
                    }
                    else
                    {
                        l_sbOrderBy.append("standard_name_kana desc, ");
                    }                    
                }
            }            
        }
        
        String l_strOrderBy = null;
        
        if (l_sbOrderBy.length() > 0)
        {
            l_strOrderBy = l_sbOrderBy.toString();
            l_strOrderBy = l_strOrderBy.substring(0, l_strOrderBy.length() - 2) + " ";
        }
           
        log.exiting(STR_METHOD_NAME);
            
        return " " + l_strOrderBy;        
    }
}
@
