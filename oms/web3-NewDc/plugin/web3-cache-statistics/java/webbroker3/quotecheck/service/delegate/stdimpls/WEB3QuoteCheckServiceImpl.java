head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.57.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuoteCheckServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ��������_�`�F�b�N�T�[�r�X����(WEB3QuoteCheckServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2009/02/03 �� (FLJ)�V�K�쐬
 */

package webbroker3.quotecheck.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuoteData;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.quotecheck.message.WEB3QuoteCheckRequest;
import webbroker3.quotecheck.message.WEB3QuoteCheckResponse;
import webbroker3.quotecheck.service.delegate.WEB3QuoteCheckService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i��������_�`�F�b�N�T�[�r�X�����j�B
 * @@version 1.0
 */
public class WEB3QuoteCheckServiceImpl
    implements WEB3QuoteCheckService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuoteCheckServiceImpl.class);
    
    /**
     * DB�}�l�[�W���B
     */
    private static WEB3QuoteCheckDBManager quoteCheckDBManager =
        WEB3QuoteCheckDBManager.getInstance();
    
    /**
     * DB�}�l�[�W���B
     */
    private static int MISSING_QUOTE_MAX_NUMBER = 50;
    
    /**
     * �`�F�b�N�ΏۊO�̐敨��������
     */
    private static String EXCEPT_MONTH_OF_DELIVERY = "999912";

    /**
     * ��������_�`�F�b�N
     *
     * @@param l_request WEB3QuoteCheckRequest
     * @@throws WEB3BaseException
     * @@return WEB3QuoteCheckResponse
     */
    public WEB3QuoteCheckResponse execute(WEB3QuoteCheckRequest l_request) throws
        WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3QuoteCheckResponse l_response = new WEB3QuoteCheckResponse();
        int totalCount = 0;

        try
        {
            totalCount = checkEqtypeQuote();
            
            totalCount = totalCount + checkIfoQuote();
        }
        catch (Exception l_e)
        {
            log.error(l_e.getMessage(), l_e);
            throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002,this.getClass().getName() + STR_METHOD_NAME,l_e.getMessage());
        }
        
        if (totalCount > 0)
        {
            log.error("��_�����z�M�R�ꌏ��(amount of missing quote products)�F"+totalCount);
        }
        l_response.count = totalCount;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * ���������̎�������_�`�F�b�N
     *
     * @@throws Exception
     * @@return int
     */
    private int checkEqtypeQuote() throws Exception
    {
        
        int count = 0;
        Map quoteMissMap = new HashMap();
        // �����A���������}�X�^����
        Hashtable l_tblProduct = quoteCheckDBManager.getProducts(ProductTypeEnum.EQUITY);
        Hashtable l_tblEqtypeProduct = quoteCheckDBManager.getEqtypeProducts();
        
        // ������������}�X�^����
        Hashtable l_tradedProductTbl = 
            quoteCheckDBManager.getEqtypeTradedProducts();
        
        if(l_tradedProductTbl == null)
        {
            return count;
        }
        
        log.debug("������������}�X�^�Ƀ`�F�b�N�Ώۖ�����"+l_tradedProductTbl.size()+"��.");
        
        String l_strMarketCode;
        List l_productList = null;
        
        Enumeration e = l_tradedProductTbl.keys();
        while (e.hasMoreElements())
        {
            String l_key = (String) e.nextElement();

            EqtypeTradedProductRow l_tradedProductRow = 
                (EqtypeTradedProductRow) l_tradedProductTbl.get(l_key);

            long l_productId = l_tradedProductRow.getProductId();
            ProductRow l_productRow = 
                (ProductRow)l_tblProduct.get(new Long(l_productId));
            EqtypeProductRow l_eqtypeProductRow = 
                (EqtypeProductRow)l_tblEqtypeProduct.get(new Long(l_productId));
            TradedProduct l_tradedProduct =
                getEqTypeTradedProduct(l_productRow, l_eqtypeProductRow, l_tradedProductRow);
            
            // �����T�[�o���玞�����擾
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3QuoteDataSupplierService l_quoteDataSupplierService =
                (WEB3QuoteDataSupplierService) l_finApp.getTradingModule(ProductTypeEnum.
                EQUITY).getQuoteDataSupplierService();

            try
            {
                QuoteData l_quoteData = 
                    l_quoteDataSupplierService.getQuoteForCheck(l_tradedProduct, RealType.REAL);
                
                //�����z�M�Ȃ��ꍇ
                if (l_quoteData == null)
                {
                    //����ʈȉ��̏ꍇ�A�����z�M�R��Map�ɖ�������o�^
                    if (count < MISSING_QUOTE_MAX_NUMBER)
                    {
                        l_strMarketCode  = l_tradedProduct.getMarket().getMarketCode();
                        l_productList = (List)quoteMissMap.get(l_strMarketCode);
                        if (l_productList == null || l_productList.size() == 0)
                        {
                            l_productList = new ArrayList();
                        }
                        l_productList.add(
                                WEB3QuoteCheckDBManager.toProductId(l_tradedProductRow.getProductId()));

                        quoteMissMap.put(l_strMarketCode, l_productList);
                    }
                    count = count + 1;
                }
            }
            catch (NotFoundException l_exp)
            {
                log.info(" ����ID�F[" + l_tradedProduct.getProduct().getProductId()
                        + "] �s��R�[�h�F[" + l_tradedProduct.getMarket().getMarketCode()
                        + "�ɑΉ����鎞����񂪌�����܂���B"
                        );
            }
        }

        // ���O�o��
        for (Iterator it = quoteMissMap.entrySet().iterator(); it.hasNext();)
        {
            Map.Entry entry = (Map.Entry) it.next();
            l_strMarketCode  = (String) entry.getKey();
            l_productList = (List) entry.getValue();
            log.error("�i�����j��_�����`�F�b�N�z�M�R�����(missing quote products)�́@@�s��R�[�h�F"+l_strMarketCode+",�������F"+l_productList.toString()+"");
        }

        return count;
    }
    
    /**
     * �敨OP�����̎�������_�`�F�b�N
     *
     * @@throws Exception
     * @@return int
     */
    private int checkIfoQuote() throws Exception
    {
        int count = 0;
        Map quoteMissMap = new HashMap();
        // �����A�敨OP�����}�X�^����
        Hashtable l_tblProduct = quoteCheckDBManager.getProducts(ProductTypeEnum.IFO);
        Hashtable l_tblIfoProduct = quoteCheckDBManager.getIfoProducts();
        
        // �敨OP��������}�X�^����
        List l_lisSearchResult = 
            quoteCheckDBManager.getIfoTradedProducts();
        //�@@�Y���f�[�^�Ȃ��̏ꍇ�A
        if (l_lisSearchResult == null || l_lisSearchResult.size() == 0)
        {
            return count;
        }
        
        //�d���`�F�b�N
        Hashtable l_tradedProductTbl = new Hashtable();
        for (int i = 0; i < l_lisSearchResult.size(); i++)
        {
            IfoTradedProductRow l_ifoTradedProductRow = (IfoTradedProductRow)l_lisSearchResult.get(i);
            
            long l_productId = l_ifoTradedProductRow.getProductId();
            IfoProductRow l_ifoProductRow = 
                (IfoProductRow)l_tblIfoProduct.get(new Long(l_productId));
            
            //�������u999912�v�ł���敨�������ΏۊO
            if (EXCEPT_MONTH_OF_DELIVERY.equals(l_ifoProductRow.getMonthOfDelivery()))
            {
                continue;
            }
            String l_key = WEB3QuoteCheckDBManager.toMarketId(l_ifoTradedProductRow.getMarketId());
            l_key = l_key 
                + l_ifoProductRow.getUnderlyingProductCode()
                + l_ifoProductRow.getMonthOfDelivery();
            if(WEB3FuturesOptionDivDef.OPTION.equals(l_ifoProductRow.getFutureOptionDiv()))
            {
                l_key = l_key 
                    + l_ifoProductRow.getDerivativeType().intValue()
                    + l_ifoProductRow.getStrikePrice();
            }
            l_tradedProductTbl.put(l_key, l_ifoTradedProductRow);
        }
        
        log.debug("�敨OP��������}�X�^�Ƀ`�F�b�N�Ώۖ�����"+l_tradedProductTbl.size()+"��.");
               
        String l_strMarketCode;
        List l_productList = null;

        Enumeration e = l_tradedProductTbl.keys();
        while (e.hasMoreElements())
        {
            String l_key = (String) e.nextElement();
            IfoTradedProductRow l_tradedProductRow = 
                (IfoTradedProductRow) l_tradedProductTbl.get(l_key);

            long l_productId = l_tradedProductRow.getProductId();
            ProductRow l_productRow = 
                (ProductRow)l_tblProduct.get(new Long(l_productId));
            IfoProductRow l_ifoProductRow = 
                (IfoProductRow)l_tblIfoProduct.get(new Long(l_productId));
            
            TradedProduct l_tradedProduct =
                getIfoTradedProduct(l_productRow, l_ifoProductRow, l_tradedProductRow);
            
            // �����T�[�o���玞�����擾
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3QuoteDataSupplierService l_quoteDataSupplierService =
                (WEB3QuoteDataSupplierService) l_finApp.getTradingModule(ProductTypeEnum.
                IFO).getQuoteDataSupplierService();

            try
            {
                QuoteData l_quoteData = 
                    l_quoteDataSupplierService.getQuoteForCheck(l_tradedProduct, RealType.REAL);
                
                //�����z�M�Ȃ��ꍇ
                if (l_quoteData == null)
                {
                    //����ʈȉ��̏ꍇ�A�����z�M�R��Map�ɖ�������o�^
                    if (count < MISSING_QUOTE_MAX_NUMBER)
                    {
                        l_strMarketCode  = l_tradedProduct.getMarket().getMarketCode();
                        l_productList = (List)quoteMissMap.get(l_strMarketCode);
                        if (l_productList == null || l_productList.size() == 0)
                        {
                            l_productList = new ArrayList();
                        }
                        l_productList.add(
                                WEB3QuoteCheckDBManager.toProductKeyword(l_tradedProduct));

                        quoteMissMap.put(l_strMarketCode, l_productList);
                    }
                    count = count + 1;
                }
            }
            catch (NotFoundException l_exp)
            {
                log.info(" ����ID�F[" 
                        + l_tradedProduct.getProduct().getProductId() 
                        + "] �s��R�[�h�F[" + l_tradedProduct.getMarket().getMarketCode()
                        + "]�ɑΉ����鎞����񂪌�����܂���B");
            }
        }
        
        // ���O�o��
        for (Iterator it = quoteMissMap.entrySet().iterator(); it.hasNext();)
        {
            Map.Entry entry = (Map.Entry) it.next();
            l_strMarketCode  = (String) entry.getKey();
            l_productList = (List) entry.getValue();
            log.error("�i�敨OP�j��_�����`�F�b�N�z�M�R�����(missing quote products)�́@@�s��R�[�h�F"+l_strMarketCode+",�������F"+l_productList.toString()+"");
        }
        return count;
    }
    
    /**
     * ����������擾
     *
     * @@param  l_productRow ProductRow
     * @@param  l_eqtypeProductRow EqtypeProductRow
     * @@param  l_tradedProductRow EqtypeTradedProductRow
     * @@throws Exception
     * @@return TradedProduct
     */
    private EqTypeTradedProduct getEqTypeTradedProduct(
            ProductRow l_productRow,
            EqtypeProductRow l_eqtypeProductRow,
            EqtypeTradedProductRow l_tradedProductRow) throws Exception
    {
        EqTypeProduct l_eqTypeProduct = new WEB3EqTypeProductFakeImpl(l_productRow, l_eqtypeProductRow);
        
        Market l_market = new WEB3GentradeMarket(l_tradedProductRow.getMarketId());
        EqTypeTradedProduct l_tradedProduct = new WEB3EqTypeTradedProductFakeImpl(l_eqTypeProduct, l_market);
        
        return(l_tradedProduct);
    }
    
    /**
     * ����������擾
     *
     * @@param  l_productRow ProductRow
     * @@param  l_ifoProductRow IfoProductRow
     * @@param  l_tradedProductRow IfoTradedProductRow
     * @@throws Exception
     * @@return TradedProduct
     */
    private IfoTradedProduct getIfoTradedProduct(
            ProductRow l_productRow,
            IfoProductRow l_ifoProductRow,
            IfoTradedProductRow l_tradedProductRow) throws Exception
    {
        IfoProduct l_ifoProduct = new WEB3IfoProductFakeImpl(l_productRow, l_ifoProductRow);
        
        Market l_market = new WEB3GentradeMarket(l_tradedProductRow.getMarketId());
        IfoTradedProduct l_tradedProduct = new WEB3IfoTradedProductFakeImpl(l_ifoProduct, l_market);
        
        return(l_tradedProduct);
    }
    
}
@
