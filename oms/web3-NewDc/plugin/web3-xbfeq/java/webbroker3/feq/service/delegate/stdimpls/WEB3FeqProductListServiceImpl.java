head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������ꗗ�T�[�r�XImpl(WEB3FeqProductListServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 ���� (���u) �V�K�쐬
                 : 2005/08/03 �A�C��(���u) ���r���[
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3FeqProductListRequest;
import webbroker3.feq.message.WEB3FeqProductListResponse;
import webbroker3.feq.message.WEB3FeqProductUnit;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.feq.service.delegate.WEB3FeqProductListService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;
/**
 * (�O�����������ꗗ�T�[�r�XImpl)<BR>
 * �O�����������ꗗ�T�[�r�X�����N���X
 *
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqProductListServiceImpl extends WEB3FeqClientRequestService
    implements WEB3FeqProductListService
{

    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqProductListServiceImpl.class);

    /**
     * @@roseuid 42CE39F90000
     */
    public WEB3FeqProductListServiceImpl()
    {

    }

    /**
     * �O�����������ꗗ���������{����B<BR>
     * <BR>
     * get�����ꗗ()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429602060304
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3FeqProductListRequest)
        {
            //get�������ꗗ
            l_response =
                this.getProductInformationList(
                    (WEB3FeqProductListRequest) l_request);
        }
        else
        {
            log.debug(
                "���N�G�X�g�f�[�^��"
                    + " WEB3FeqProductListRequest �ȊO�ł���, but is " +
                    l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�������ꗗ)<BR>
     * �O�����������ꗗ���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�����ꗗ�jget�������ꗗ�v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�����������ꗗ���N�G�X�g
     * @@return webbroker3.feq.message.WEB3FeqProductListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4297136E0167
     */
    protected WEB3FeqProductListResponse getProductInformationList(WEB3FeqProductListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getProductInformationList(WEB3FeqProductListRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        //1.1) validate( )
        l_request.validate();

        //1.2 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.3 get�،���ЃR�[�h( )
        String l_strInstitutionCode = this.getInstitutionCode();

        //1.4 create�\�[�g����(�O�������\�[�g�L�[[])
        //�\�[�g�����������ҏW����B
        //[create�\�[�g�L�[()�Ɏw�肷�����]
        //�\�[�g�L�[�F�@@���N�G�X�g�f�[�^.�\�[�g�L�[
        String l_strSortCond = this.createSortCond(l_request.sortKeys);

        //1.5 get����(String, String, String, String, boolean, String)
        //�\���Ώۖ������擾����B
        //[get����()�Ɏw�肷�����]
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()
        //�s��R�[�h�F�@@���N�G�X�g�f�[�^.�s��R�[�h
        //�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h
        //�������F�@@���N�G�X�g�f�[�^.������
        //is����\�����F�@@true
        //�\�[�g�����F�@@create�\�[�g����()
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        WEB3FeqProduct[] l_feqProducts =
            l_feqProductManager.getProduct(
                l_strInstitutionCode,
                l_request.marketCode,
                l_request.productCode,
                l_request.productName,
                true,
                l_strSortCond);

        if (l_feqProducts == null)
        {
            log.debug("�O�������������擾�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6 WEB3PageIndexInfo(l_objs�i=get�L������()�̖߂�l�j : Object[],
        //   l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //�y�[�W���O�����I�u�W�F�N�g�𐶐�����B
        //[�R���X�g���N�^�̈���]
        //l_obj�F�@@get�L������()
        //l_intRequestPageIndex�F�@@���N�G�X�g�f�[�^.�v���y�[�W�ԍ�
        //l_intRequestPageSize�F�@@���N�G�X�g�f�[�^.�y�[�W���\���s��
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_feqProducts,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));

        //1.7 getArrayReturned( )
        WEB3FeqProduct[] l_feqProductes =
            (WEB3FeqProduct[]) l_pageIndexInfo.getArrayReturned(WEB3FeqProduct.class);

        //�O��������������( )
        WEB3FeqProductUnit l_feqProductUnit = null;
        List l_lisFeqProductUnits = new ArrayList();

        //1.8 (*) �\���Ώۃy�[�W�ɊY������I�u�W�F�N�g�v�f��LOOP����
        for (int i = 0; i < l_feqProductes.length; i++)
        {
            //1.8.1 �O��������������( )
            //�O�������������׃��b�Z�[�W�f�[�^�I�u�W�F�N�g�𐶐�����B
            l_feqProductUnit = new WEB3FeqProductUnit();

            //1.18.2 create�O��������������(�O��������������, �O����������)
            //�O�������������׃��b�Z�[�W�f�[�^�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B
            //[create�O��������������()�Ɏw�肷�����]
            //�O�������������ׁF�@@���������I�u�W�F�N�g
            //�����F�@@getArrayReturned()[index]
            this.createFeqProductDetails(l_feqProductUnit, l_feqProductes[i]);
            l_lisFeqProductUnits.add(l_feqProductUnit);
         }

         //1.9 get�戵�\�O���s��(�،���ЃR�[�h : String)
         //�،���ЂɊY������O���s����擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        Market[] l_markets =
            l_finObjectManager.getOpenFeqMarkets(l_strInstitutionCode);

        //1.10 getTotalPages( ) ���y�[�W�����擾����B
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();

        //1.11 getTotalRecords( ) �����R�[�h�����擾����B
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

        //1.12 getPageIndex( ) �\���y�[�W�ԍ����擾����B
        int l_intPageIndex = l_pageIndexInfo.getPageIndex();

        //1.13 createResponse( )
        WEB3FeqProductListResponse l_response =
            (WEB3FeqProductListResponse)l_request.createResponse();

        //1.14 (*) �v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
        //�s��R�[�h�ꗗ�F�@@get�戵�\�O���s��Ŏ擾�����e�s��.getMarketCode()

        //�s��R�[�h�ꗗ
        String[] l_strMarketCodeList = null;
        if (l_markets != null)
        {
            l_strMarketCodeList = new String[l_markets.length];
            for (int i = 0; i < l_markets.length; i++)
            {
                l_strMarketCodeList[i] = l_markets[i].getMarketCode();
            }
        }
        l_response.marketList = l_strMarketCodeList;

        //�������ꗗ�F�@@���������O��������������[]
        WEB3FeqProductUnit[] l_feqProductCodeNames = null;
        if (l_lisFeqProductUnits != null)
        {
            l_feqProductCodeNames =
                new WEB3FeqProductUnit[l_lisFeqProductUnits.size()];
            l_lisFeqProductUnits.toArray(l_feqProductCodeNames);
        }
        l_response.productCodeNames = l_feqProductCodeNames;

        //���y�[�W���F�@@getTotalPages()
        l_response.totalPages = l_intTotalPages + "";

        //�����R�[�h���F�@@getTotalRecords()
        l_response.totalRecords = l_intTotalRecords + "";

        //�\���y�[�W�ԍ��F�@@getPageIndex()
        l_response.pageIndex = l_intPageIndex + "";

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�O��������������)<BR>
     * �w������̓��e�ŁA<BR>
     * �O�������������׃��b�Z�[�W�I�u�W�F�N�g�v���p�e�B�ɒl���Z�b�g����B<BR>
     * <BR>
     * �O�������������ׂ̊e�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B<BR>
     * <BR>
     * �@@�����R�[�h�F�@@����.getProductCode()<BR>
     * �@@�������F�@@����.get�\��������()<BR>
     * �@@���t�\�F�@@�i�������.is����~() == true�j�̏ꍇ�Afalse�B<BR>
     * �ȊOtrue�B<BR>
     * �@@���t�\�F�@@�i�������.is����~() == true�j�̏ꍇ�Afalse�B<BR>
     * �ȊOtrue�B<BR>
     * �@@���n�����R�[�h�F�@@����.���n�����R�[�h<BR>
     * �@@���t�P�ʁF�@@�������.���t�P��<BR>
     * �@@�Œᔃ�t�P�ʁF�@@�������.�Œᔃ��������<BR>
     * �@@���t�P�ʁF�@@�������.���t�P��<BR>
     * �@@�Œᔄ�t�P�ʁF�@@�������.�Œᔄ��������<BR>
     * �@@���o�^���F�@@�������.���i�o�^�j��<BR>
     * �@@���p�~���F�@@�������.���i�o�^�j�p�~��<BR>
     * <BR>
     * ���@@��������̎擾�F�@@����.get�������()�ɂĎ擾����B<BR>
     * @@param l_feqProductDetail - (�O��������������)<BR>
     * �O�������������׃��b�Z�[�W
     * @@param l_product - (����)<BR>
     * �O�����������I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 4297136E016B
     */
    protected void createFeqProductDetails(
        WEB3FeqProductUnit l_feqProductDetail, WEB3FeqProduct l_product)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createFeqProductDetails(WEB3FeqProductUnit, WEB3FeqProduct)";
        log.entering(STR_METHOD_NAME);

        //�w������̓��e�ŁA�O�������������׃��b�Z�[�W�I�u�W�F�N�g�v���p�e�B�ɒl���Z�b�g����B
        //�O�������������ׂ̊e�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B

        //�����R�[�h�F�@@����.getProductCode()
        l_feqProductDetail.productCode = l_product.getProductCode();

        //�������F�@@����.get�\��������()
        l_feqProductDetail.productName = l_product.getDisplayProductName();

        //���t�\�F�@@�i�������.is����~() == true�j�̏ꍇ�Afalse�B�ȊOtrue�B

        //���@@��������̎擾�F�@@����.get�������()�ɂĎ擾����B
        //�C���@@���@@begin
//        WEB3FeqTradedProduct l_feqTradedProduct =
//            l_product.getFeqTradedProduct();
       FeqTradedProductRow l_feq_tp_row=null;
       if(l_product.l_feqTPRow==null){
           try
           {
               l_feq_tp_row = FeqTradedProductDao.
                   findRowByProductIdMarketId(l_product.getProductId(),
                                              l_product.getMarket().getMarketId());
           }
           catch (Exception l_ex)
           {

               log.error(l_ex.getMessage(), l_ex);
               throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_ex.toString());

           }
       }
       else {
           l_feq_tp_row=l_product.l_feqTPRow;
       }

        if (l_feq_tp_row == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (WEB3FeqTradedProduct.isBuyStop(l_feq_tp_row))
        {
            l_feqProductDetail.buyPossFlag = false;
        }
        else
        {
            l_feqProductDetail.buyPossFlag = true;
        }

        //���t�\�F�@@�i�������.is����~() == true�j�̏ꍇ�Afalse�B�ȊOtrue�B
        if (WEB3FeqTradedProduct.isSellStop(l_feq_tp_row))
        {
            l_feqProductDetail.sellPossFlag = false;
        }
        else
        {
            l_feqProductDetail.sellPossFlag = true;
        }

        //���n�����R�[�h�F�@@����.���n�����R�[�h
        l_feqProductDetail.localProductCode =
            l_product.getOffshoreProductCode();

        //���t�P�ʁF�@@�������.���t�P��
        l_feqProductDetail.buyUnit =
            WEB3StringTypeUtility.formatNumber(
                l_feq_tp_row.getBuyLotSize());

        //�Œᔃ�t�P�ʁF�@@�������.�Œᔃ��������
        l_feqProductDetail.minBuyUnit =
            WEB3StringTypeUtility.formatNumber(
                l_feq_tp_row.getBuyMinQty());

        //���t�P�ʁF�@@�������.���t�P��
        l_feqProductDetail.sellUnit =
            WEB3StringTypeUtility.formatNumber(
                l_feq_tp_row.getSellLotSize());

        //�Œᔄ�t�P�ʁF�@@�������.�Œᔄ��������
        l_feqProductDetail.minSellUnit =
            WEB3StringTypeUtility.formatNumber(
                l_feq_tp_row.getSellMinQty());

        //���o�^���F�@@�������.���i�o�^�j��
        l_feqProductDetail.listedDate = l_feq_tp_row.getListedDate();

        //���p�~���F�@@�������.���i�o�^�j�p�~��
        l_feqProductDetail.unlistedDate = l_feq_tp_row.getUnlistedDate();

        //�s��R�[�h
        l_feqProductDetail.marketCode = l_product.getMarketCode();

        //end


        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�\�[�g����)<BR>
     * �w��̓��e�ŁA<BR>
     * �w��̓��e�ŁA�O�����������e�[�u���ɑ΂���\�[�g����������iorder by��j<BR>
     * ��ҏW���ԋp����B<BR>
     * ���L�[�Ƃ��āu�������v���w�肳��Ă���ꍇ�A�\�[�g�����Ƃ���e�[�u���̍��ڂ́A<BR>
     * �u�������i�J�i�j�v�Ƃ���B <BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[
     * @@return String
     * @@roseuid 429722B7030D
     */
    protected String createSortCond(WEB3ForeignSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME =
            "createSortCond(WEB3ForeignSortKey[] l_sortKeys)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_strSortKeys = new StringBuffer();
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            if (WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" product_code ASC");
                }
                else
                {
                    l_strSortKeys.append(" product_code DESC");
                }
            }
            else if (WEB3FeqSortKeyItemNameDef.OFFSHORE_PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" offshore_product_code ASC");
                }
                else
                {
                    l_strSortKeys.append(" offshore_product_code DESC");
                }
            }
            else if (WEB3FeqSortKeyItemNameDef.PRODUCT_NAME.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" standard_name_kana ASC");
                }
                else
                {
                    l_strSortKeys.append(" standard_name_kana DESC");
                }
            }
            if (i < l_sortKeys.length - 1)
            {
                l_strSortKeys.append(" , ");
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_strSortKeys.toString();
    }
}
@
