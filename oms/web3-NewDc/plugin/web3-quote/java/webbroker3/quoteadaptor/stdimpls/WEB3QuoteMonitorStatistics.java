head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteMonitorStatistics.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �s�ꂲ�Ǝ������Ď����v(WEB3QuoteMonitorStatistics.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2009/01/28 ���@@�@@��(FLJ) �V�K�쐬
 */
package webbroker3.quoteadaptor.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.quoteadaptor.WEB3QuoteData;
import webbroker3.util.WEB3LogUtility;


public class WEB3QuoteMonitorStatistics {

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static final WEB3LogUtility LOG = 
        WEB3LogUtility.getInstance(WEB3QuoteMonitorStatistics.class);
    
    /**
     * �f�o�b�N�o�̓t���O
     */
    private static final boolean DBG = LOG.ison();
    
    /**
     * �s��R�[�h
     */
    private String marketCode;
    
    /**
     * ���O�o�͐���t���O
     */
    private boolean logFlg;
    
    /**
     * �������Map
     */
    private Map tradedProducts = new HashMap();
    
    /**
     * �G���[�������X�g
     */
    private List errorProductList= new ArrayList();
    
    /**
     * �������ƃJ�E���gMap
     */
    private Map productCountMap = new HashMap();
    
    /**
     * �J�E���g臒l
     */
    private int countThreshold;
    
    /**
     * �^�C��臒l
     */
    private int timeThreshold;
    
    /**
     * �����^�C�v
     */
    private ProductTypeEnum productType = null;
    
    
    WEB3QuoteMonitorStatistics(String l_marketCode, int l_countThreshold)
    {
        this.marketCode = l_marketCode;
        this.countThreshold = l_countThreshold;
        
        this.timeThreshold = WEB3QuoteMonitorSettings.getInstance().getWarningThreshold();
        this.logFlg = true;
    }
    
    /**
     * ����������}�b�v�ɒǉ�
     *
     * @@param  l_tradedProduct TradedProduct
     */
    public void addTradedProduct(TradedProduct l_tradedProduct)
    {
        tradedProducts.put(l_tradedProduct, null);
        productCountMap.put(l_tradedProduct, new ProductCount());
        if (productType == null)
        {
            productType = l_tradedProduct.getProduct().getProductType();
        }
    }
    
    /**
     * ��������ꗗ���擾
     */
    public Set getTradedProducts()
    {
        return tradedProducts.keySet();
    }
    
    /**
     * �����X�V�`�F�b�N����
     */
    public void checkQuote(TradedProduct l_tradedProduct, WEB3QuoteData l_currentQuoteData)
    {
        boolean isUpdated = false;
        WEB3QuoteData l_quoteData = (WEB3QuoteData)tradedProducts.get(l_tradedProduct);
        
        if (l_quoteData == null)
        {
            tradedProducts.put(l_tradedProduct, l_currentQuoteData);
            return;
        }

        ProductCount l_productCount;

        //���C�z�l�`�F�b�N
        Timestamp lastUpdatedTimestamp = l_quoteData.getAskPriceTime();
        Timestamp currentTimestamp = l_currentQuoteData.getAskPriceTime();
        if (lastUpdatedTimestamp != null)
        {
            if (currentTimestamp != null 
                && (lastUpdatedTimestamp.compareTo(currentTimestamp) < 0))
            {
                isUpdated = true;
            }
        } 
        else
        {
            if (currentTimestamp != null)
            {
                isUpdated = true;
            }
        }
        
        if (!isUpdated)
        {
            //���C�z�l�`�F�b�N
            lastUpdatedTimestamp = l_quoteData.getBidPriceTime();
            currentTimestamp = l_currentQuoteData.getBidPriceTime();
            if (lastUpdatedTimestamp != null)
            {
                if (currentTimestamp != null 
                    && (lastUpdatedTimestamp.compareTo(currentTimestamp) < 0))
                {
                    isUpdated = true;
                }
            } 
            else
            {
                if (currentTimestamp != null)
                {
                    isUpdated = true;
                }
            }
        }
        
        if (isUpdated)
        {
            //�Y�������J�E���g���N���A����
            l_productCount = (ProductCount) productCountMap.get(l_tradedProduct);
            l_productCount.clear();
            //�G���[�������X�g����Y���������폜����
            errorProductList.remove(l_tradedProduct);
            //�����X�V
            tradedProducts.put(l_tradedProduct, l_currentQuoteData);
        }
        else
        {
            //�Y�������J�E���g�𑝉�����
            l_productCount = (ProductCount) productCountMap.get(l_tradedProduct);
            l_productCount.add();
            
            //�^�C��臒l�ȏ�̏ꍇ�A�G���[�������X�g�ɊY��������ǉ�����
            if (l_productCount.get() >= this.timeThreshold && 
                errorProductList.contains(l_tradedProduct) == false)
            {
                errorProductList.add(l_tradedProduct);
            }
        }

    }
    
    /**
     * �s��J�E���g臒l��r����
     */
    public void checkCount()
    {
        String l_productType = "";
        if(ProductTypeEnum.EQUITY.equals(productType))
        {
            l_productType = "�i�����j";
        }
        else if(ProductTypeEnum.IFO.equals(productType))
        {
            l_productType = "�i�敨OP�j";
        }
        
        if (DBG)
        {
            //�z�M�R��󋵏o��
            if (errorProductList.size() > 0)
            {
                LOG.debug(l_productType+
                        "�ꒆ�z�M�R���(missing quote products)�́@@�s��R�[�h�F"+marketCode+
                        ",�R�ꌏ���F"+errorProductList.size()+"");
            }
        }
        
        //�s��J�E���g臒l�ȏ�̏ꍇ
        if (errorProductList.size() >= this.countThreshold)
        {
            if (logFlg == true)
            {
                List l_list = new ArrayList();
                for (int i = 0; i < errorProductList.size(); i++)
                {
                    if (i >= WEB3QuoteConstants.MISSING_QUOTE_MAX_NUMBER)
                    {
                        break;
                    }
                    TradedProduct l_tradedProduct = (TradedProduct)errorProductList.get(i);
                    l_list.add(toProductKeyword(l_tradedProduct));
                }
                
                //�����z�M�R�ꃍ�O�o��
                LOG.error(l_productType+
                        "�ꒆ�����z�M�R�ꂪ����. �s��R�[�h�F"+marketCode+
                        ",����(amount of missing quote products)�F"+errorProductList.size());
                LOG.info(l_productType+
                        "�ꒆ�z�M�R�����(missing quote products)�́@@�s��R�[�h�F"+marketCode+
                        ",�������F"+l_list.toString()+"");

                logFlg = false;
            }
            return;
        }
        
        //�s��J�E���g臒l���O�̏ꍇ�i�������j
        if (errorProductList.size() == 0)
        {
            if (logFlg == false)
            {
                //�����z�M�������O�o��
                LOG.info(l_productType+
                        "�ꒆ�����z�M��������܂���.�s��R�[�h�F"+marketCode+
                        " (missing quote recovery)");
                logFlg = true;
            }
        }
    }
    
    public String getMarketCode()
    {
        return marketCode;
    }
    
    private String toProductId(long l_productId)
    {
        String l_strProductId = "" + l_productId;
        return l_strProductId.substring(WEB3QuoteConstants.PRODUCT_HEAD_SIZE,
                WEB3QuoteConstants.PRODUCT_HEAD_SIZE + WEB3QuoteConstants.PRODUCT_CODE_LENGTH);
    }
    
    private String toProductKeyword(TradedProduct l_tradedProduct)
    {
        String l_productKeyword = "";
        if(ProductTypeEnum.EQUITY.equals(productType))
        {
            l_productKeyword = toProductId(l_tradedProduct.getProduct().getProductId());
        }
        else if(ProductTypeEnum.IFO.equals(productType))
        {
            IfoProductRow l_ifoProductRow = ((WEB3IfoProductFakeImpl)l_tradedProduct.getProduct()).getIfoProductRow();
            l_productKeyword = l_ifoProductRow.getUnderlyingProductCode() 
                                    + "-"
                                    + l_ifoProductRow.getMonthOfDelivery();
            if(WEB3FuturesOptionDivDef.OPTION.equals(l_ifoProductRow.getFutureOptionDiv()))
            {
                l_productKeyword = l_productKeyword 
                    + "-"
                    + l_ifoProductRow.getDerivativeType().intValue()
                    + "-"
                    + (long)l_ifoProductRow.getStrikePrice();
            }
        }
        
        return l_productKeyword;
    }

    /**
     * �������ƃJ�E���g�N���X
     */
    private class ProductCount
    {
        int count = 0;
        
        void clear()
        {
            count = 0;
        }
        
        void add()
        {
            count++;
        }
        
        int get()
        {
            return count;
        }
    }
}
@
