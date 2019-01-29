head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProductManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������v���_�N�g�}�l�[�W��(WEB3FeqProductManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  䈋�(���u) �V�K�쐬
                   2005/07/27 �����F(���u) ���r���[
Revesion History : 2007/11/16 �g�C��(���u) ���f��No.362 No.364 No.366 No.367 ����.006
Revesion History : 2008/01/14 �đo�g(���u) ���f��No.368�ANo.386
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
*/
package webbroker3.feq;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqProductManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.data.FeqLimitPriceRangeMstRow;
import webbroker3.feq.data.FeqTickValuesMstRow;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.quoteadaptor.RealType;
import webbroker3.util.WEB3LogUtility;
import java.util.Hashtable;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductDao;
import java.util.Date;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.FeqLastClosingPriceRow;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.GenCurrencyRow;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

/**
 * (�O�������v���_�N�g�}�l�[�W��) <BR>
 * �O�������v���_�N�g�}�l�[�W��
 *
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3FeqProductManager extends FeqProductManagerImpl
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FeqProductManager.class);
    /**
     * @@roseuid 42CE39E8030D
     */
    public WEB3FeqProductManager()
    {

    }

    /**
     * (get�\���p�������) <BR>
     * ���͉�ʂɕ\�����鎞�������擾����B <BR>
     *  <BR>
     * �P�j�������擾����B <BR>
     *  <BR>
     *    �i������ �ڍז��� �������j <BR>
     *  <BR>
     * �Q�j�擾�������������ȉ��̗D�揇�ʂŕ]�����A <BR>
     * �@@�@@�l���t���Ă���i==0�łȂ��j�P���������Ƃ��āA <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.���� �ɃZ�b�g����B <BR>
     *  <BR>
     *     �P�D���ݒl�i�P�j�̏ڍׂ��m�肷��܂ł́A0�j <BR>
     *     �Q�D���C�z�l�i�P�j�̏ڍׂ��m�肷��܂ł́A0�j <BR>
     *     �R�D���C�z�l�i�P�j�̏ڍׂ��m�肷��܂ł́A0�j <BR>
     *     �S�D�������.��l�i�I�l�j�i�����̎������.getLastClosingPrice( )�j <BR>
     *     �i������ �P�j�̏ڍ׊m���ɕύX���� �������j <BR>
     *  <BR>
     * �@@�@@�܂��A�����Ƃ����P���ɊY�����鎞���敪 <BR>
     * �@@�@@�i���ݒl�^���C�z�l�^���C�z�l�^�O���I�l�j���A <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.�����敪 �ɃZ�b�g����B <BR>
     *  <BR>
     * �R�j�߂�l�I�u�W�F�N�g.�O����ɁA0���Z�b�g����B <BR>
     *     �i������ �P�j�̏ڍ׊m���ɕύX���� �������j <BR>
     *  <BR>
     * �S�j�߂�l�I�u�W�F�N�g.�������\���ԂɁA�Q�j�Ŏ����Ƃ����P�� <BR>
     * �@@�@@�ɑΉ����鎞�����\���Ԃ��Z�b�g����B <BR>
     *     �i������ �P�j�̏ڍ׊m���ɕύX���� �������j <BR>
     *  <BR>
     * �T�j�߂�l�I�u�W�F�N�g.�����擾�敪�ɁA"����"���Z�b�g����B <BR>
     *  <BR>
     * �U�j�߂�l�I�u�W�F�N�g.�s��R�[�h�ɁA�����̎������.�s��ID <BR>
     * �@@�@@�ɊY������s��.�s��R�[�h���Z�b�g����B <BR>
     *  <BR>
     * �V�j�쐬�����߂�l�I�u�W�F�N�g��Ԃ��B <BR>
     * @@param l_feqTradedProduct - (�������) <BR>
     * �O��������������I�u�W�F�N�g
     * @@param l_subAccount - (�⏕����) <BR>
     * �⏕�����I�u�W�F�N�g
     * @@return webbroker3.feq.WEB3FeqProductQuote
     * @@roseuid 428B2962033B
     */
    public WEB3FeqProductQuote getIndicationCurrentPriceUnit(WEB3FeqTradedProduct l_feqTradedProduct, WEB3GentradeSubAccount l_subAccount)
    {
        final String STR_METHOD_NAME = "getIndicationCurrentPriceUnit";
        log.entering(STR_METHOD_NAME);
        //�P�j�������擾����B
        //
        //   �i������ �ڍז��� �������j
        //
        double l_dblCurrentPrice = 0;
        //�Q�j�擾�������������ȉ��̗D�揇�ʂŕ]�����A
        //�@@�@@�l���t���Ă���i==0�łȂ��j�P���������Ƃ��āA
        //�@@�@@�߂�l�I�u�W�F�N�g.���� �ɃZ�b�g����B
        //
        //    �P�D���ݒl�i�P�j�̏ڍׂ��m�肷��܂ł́A0�j
        //    �Q�D���C�z�l�i�P�j�̏ڍׂ��m�肷��܂ł́A0�j
        //    �R�D���C�z�l�i�P�j�̏ڍׂ��m�肷��܂ł́A0�j
        //    �S�D�������.��l�i�I�l�j�i�����̎������.getLastClosingPrice( )�j
        //    �i������ �P�j�̏ڍ׊m���ɕύX���� �������j
        //
        //�@@�@@�܂��A�����Ƃ����P���ɊY�����鎞���敪
        //�@@�@@�i���ݒl�^���C�z�l�^���C�z�l�^�O���I�l�j���A
        //�@@�@@�߂�l�I�u�W�F�N�g.�����敪 �ɃZ�b�g����B
//        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//        TradingModule l_tradingModule =
//            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
//        WEB3QuoteDataSupplierService l_quoteSupplier =
//            (WEB3QuoteDataSupplierService)l_tradingModule.getQuoteDataSupplierService();
//        // �������(EqTypeQuoteData�j���擾����
//        //throw NotFoundException
//        QuoteData l_eqtypeQuoteData =
//            l_quoteSupplier.getQuote(l_feqTradedProduct);
//        l_dblCurrentPrice = l_productQuote.getCurrentPrice();
//        if (l_dblCurrentPrice == 0)
//        {
//            //l_productQuote.get
//        }
        //l_productQuote.setCurrentPrice(l_feqTradedProduct.getLastClosingPrice());
        //
        //�R�j�߂�l�I�u�W�F�N�g.�O����ɁA0���Z�b�g����B
        //    �i������ �P�j�̏ڍ׊m���ɕύX���� �������j
        //
        //�S�j�߂�l�I�u�W�F�N�g.�������\���ԂɁA�Q�j�Ŏ����Ƃ����P��
        //�@@�@@�ɑΉ����鎞�����\���Ԃ��Z�b�g����B
        //    �i������ �P�j�̏ڍ׊m���ɕύX���� �������j
        //
        //�T�j�߂�l�I�u�W�F�N�g.�����擾�敪�ɁA"����"���Z�b�g����B
        //
        //�U�j�߂�l�I�u�W�F�N�g.�s��R�[�h�ɁA�����̎������.�s��ID
        //�@@�@@�ɊY������s��.�s��R�[�h���Z�b�g����B
        //
        //�V�j�쐬�����߂�l�I�u�W�F�N�g��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get�������) <BR>
     * �w����e�ɉ��������������擾����B <BR>
     *  <BR>
     *�P�j�@@�������擾����B<BR>
     *�@@�|�P�j�@@�O���I�l�e�[�u�����ȉ��̏����Ō�������B<BR>
     *�@@�@@�@@�@@�@@�@@����ID�@@�@@�@@==�@@�i�����j��������D����ID<BR>
     *�@@�@@�@@�@@�@@�@@�s��R�[�h�@@==�@@�i�����j��������D�s��ID�ɊY������s��R�[�h<BR>
     *<BR>
     *�@@�@@�@@�@@�@@�@@���Y�����郌�R�[�h�����������݂���ꍇ�́A������ő�l�̃��R�[�h��Ԃ��B<BR>
     *<BR>
     *�@@�|�Q�j�@@�|�P�j�Ńf�[�^���擾�ł��Ȃ������ꍇ<BR>
     *�@@�@@�@@�@@�@@�@@null��ԋp����B<BR>
     *<BR>
     *�Q�j�@@�v���p�e�B���Z�b�g���� <BR>
     *  <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g�̃v���p�e�B���ȉ��̒ʂ�ɐݒ肵�A�ԋp����B <BR>
     * �@@�@@-----------------------------------------------------------------<BR>
     * �@@�@@���߂�l�v���p�e�B�ݒ聄 <BR>
     *  <BR>
     * �@@�@@�O�����������������.���� = �P�j�Ŏ擾�������� <BR>
     * �@@�@@�O�����������������.�O���� = 0 <BR>
     * �@@�@@�O�����������������.�������\���� = �O���I�l�e�[�u���D���<BR>
     * �@@�@@�O�����������������.�����擾�敪 = "�O���I�l" <BR>
     * �@@�@@�O�����������������.�����敪 = "�O���I�l" <BR>
     * �@@�@@�O�����������������.�s��R�[�h = �i�����j�������.�s��ID�ɊY������s��R�[�h <BR>
     * �@@�@@----------------------------------------------------------------- <BR>
     * @@param l_feqTradedProduct - (�������)
     *
     * @@param l_realType - ���A���^�C�v
     * �i�����擾�敪�j
     *
     * @@return webbroker3.feq.WEB3FeqProductQuote
     * @@throws WEB3BaseException
     * @@roseuid 4292B1E4007C
     */
    public WEB3FeqProductQuote getCurrentPriceUnit(FeqTradedProduct l_feqTradedProduct, RealType l_realType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCurrentPriceUnit(FeqTradedProduct, RealType)";
        log.entering(STR_METHOD_NAME);

        double l_dblCurrentPrice = 0;
        Timestamp l_tsBaseDate = null;

        WEB3FeqTradedProduct l_web3FeqTradedProduct = (WEB3FeqTradedProduct)l_feqTradedProduct;
    	FeqTradedProductRow l_feqTradedProductRow =
    		(FeqTradedProductRow)(l_web3FeqTradedProduct.getDataSourceObject());

        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "product_id = ? and feq_closing_price_mrkt_code_s = ? ";
            Object[] l_whereValues = new Object[2];

            l_whereValues[0] = new Long(l_feqTradedProductRow.getProductId());
            l_whereValues[1] = l_feqTradedProduct.getMarket().getMarketCode();

            String l_strSort = " base_date DESC ";

            List l_lisFeqProducts =
                l_processor.doFindAllQuery(
                    FeqLastClosingPriceRow.TYPE,
                    l_strWhere,
                    l_strSort,
                    null,
                    l_whereValues);
            if (l_lisFeqProducts.isEmpty())
            {
                //�f�[�^���擾�ł��Ȃ������ꍇnull��ԋp����B
            	log.exiting(STR_METHOD_NAME);
            	return null;
            }
            else
            {
                FeqLastClosingPriceRow l_feqLastClosingPriceRow = (FeqLastClosingPriceRow)l_lisFeqProducts.get(0);
                l_dblCurrentPrice = l_feqLastClosingPriceRow.getFeqClosingPrice();
                l_tsBaseDate = l_feqLastClosingPriceRow.getBaseDate();
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString());
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString());
        }

        WEB3FeqProductQuote l_feqProductQuote = new WEB3FeqProductQuote();
        //�O�����������������.���� =  �P�j�Ŏ擾��������
        l_feqProductQuote.setCurrentPrice(l_dblCurrentPrice);
        //�O�����������������.�O���� = 0
        l_feqProductQuote.setComparedPreviousDay(0);
        //�O�����������������.�������\���� = �O���I�l�e�[�u���D���
        l_feqProductQuote.setCurrentPricePublicTime(l_tsBaseDate);
        //�O�����������������.�����擾�敪 = "�O���I�l"
        l_feqProductQuote.setCurrentPriceGetDiv("�O���I�l");
        //�O�����������������.�����敪 = "�O���I�l"
        l_feqProductQuote.setCurrentPriceDiv("�O���I�l");
        //�O�����������������.�s��R�[�h = (�����j�������.�s��ID�ɊY������s��R�[�h
        l_feqProductQuote.setMarketCode(l_feqTradedProduct.getMarket().getMarketCode());

        log.exiting(STR_METHOD_NAME);
        return l_feqProductQuote;
    }

    /**
     * (get����) <BR>
     * �������擾����B <BR>
     * �擾���͈ȉ��̒ʂ�B <BR>
     * ----------------------------------------------- <BR>
     * �ꒆ�F�@@�@@�����T�[�o���O��������������e�[�u�� <BR>
     * ������F�@@�I�l�e�[�u���������T�[�o���O��������������e�[�u�� <BR>
     * ----------------------------------------------- <BR>
     *  <BR>
     * �P�j�@@this.get�������( )���R�[������B <BR>
     *  <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * �@@�@@�@@��get�������( )�F�����ݒ�d�l�� <BR>
     *  <BR>
     * �@@�@@�@@��������F�@@�����̎������ <BR>
     * �@@�@@�@@RealType�F�@@"���A��" <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     *  <BR>
     * �Q�j�@@�߂�l�I�u�W�F�N�g.���� ��ԋp����B <BR>
     * �@@�@@�@@�@@���߂�l�I�u�W�F�N�g == null �̏ꍇ�́A0��ԋp����B<BR>
     * @@param l_feqTradedProduct - (�������)
     *
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4292B55B02CE
     */
    public double getCurrentPrice(FeqTradedProduct l_feqTradedProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCurrentPrice(FeqTradedProduct l_feqTradedProduct)";
        log.entering(STR_METHOD_NAME);
        WEB3FeqProductQuote l_quote =  this.getCurrentPriceUnit(l_feqTradedProduct, RealType.REAL);
        log.exiting(STR_METHOD_NAME);

        if (l_quote == null)
        {
            //���߂�l�I�u�W�F�N�g == null �̏ꍇ�́A0��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        log.exiting(STR_METHOD_NAME);
        return l_quote.getCurrentPrice();
    }

    /**
     * (get�O����������) <BR>
     * �O�����������I�u�W�F�N�g���擾����B <BR>
     *  <BR>
     * �P�j�ȉ��̏����ŊO�����������e�[�u������f�[�^���擾����B <BR>
     *  <BR>
     *    [����] <BR>
     *    ����ID�F ����.����ID <BR>
     *  <BR>
     * �Q�j�擾�����f�[�^����O�����������I�u�W�F�N�g�𐶐����A�ԋp����B <BR>
     * @@param l_lngProductId - (����ID)
     *
     * @@return WEB3FeqProduct
     * @@throws WEB3BaseException
     * @@roseuid 4292D06600DA
     */
    public WEB3FeqProduct getFeqProduct(long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFeqProduct(long l_lngProductId) ";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqProduct l_feqProduct =
                (WEB3FeqProduct)this.getProduct(l_lngProductId);
            log.exiting(STR_METHOD_NAME);
            return l_feqProduct;
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (get�O����������) <BR>
     * �igetFeqProduct()�̃I�[�o�[���C�h�j <BR>
     * �O�����������I�u�W�F�N�g���擾����B <BR>
     *  <BR>
     * �ȉ��̌��������ɂĊO�������e�[�u������������B <BR>
     *  <BR>
     *  [����] <BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،����.�،���ЃR�[�h And <BR>
     * �@@�i�����R�[�h = �p�����[�^.�����R�[�h Or <BR>
     * �@@ ���n�����R�[�h = �p�����[�^.�����R�[�h�j <BR>
     *  <BR>
     * �������ʂ��擾�ł����ꍇ�A�������ʂ�ԋp����B <BR>
     * @@param l_institution - (�،����) <BR>
     * �،���ЃI�u�W�F�N�g
     * @@param l_strProductCode - �����R�[�h <BR>
     *  <BR>
     * ���O����������.�����R�[�h or ���n�����R�[�h�̒l���w�肷��B
     * @@return webbroker3.feq.WEB3FeqProduct
     * @@roseuid 42ACE6730165
     */
    public FeqProduct getFeqProduct(Institution l_institution, String l_strProductCode) throws NotFoundException
    {
        final String STR_METHOD_NAME = "getFeqProduct(Institution l_institution, String l_strProductCode) ";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "institution_code = ? and (product_code = ? or offshore_product_code = ?)";
            Object[] l_objWhereValues = new Object[3];
            l_objWhereValues[0] = l_institution.getInstitutionCode();
            l_objWhereValues[1] = l_strProductCode;
            l_objWhereValues[2] = l_strProductCode;
            List l_lisFeqProduct =
                l_processor.doFindAllQuery(FeqProductRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
            if (l_lisFeqProduct == null || l_lisFeqProduct.isEmpty())
            {
                String msg = "No FeqProduct found with institutionCode, productCode : " +
                    l_institution.getInstitutionCode() + "," + l_strProductCode;
                throw new NotFoundException(msg);
            }
            if (l_lisFeqProduct.size() > 1)
            {
                log.debug("�O�����������e�[�u���ɏd������Y���f�[�^�����݂��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            FeqProductRow l_feqProductRow = (FeqProductRow)l_lisFeqProduct.get(0);
            return (FeqProduct)this.toProduct(l_feqProductRow);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString());

        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString());
        }
    }

    /**
     * (get����) <BR>
     * �w������̖����I�u�W�F�N�g���擾����B <BR>
     *  <BR>
     * �P�j�@@�����e�[�u������ <BR>
     * �@@�ȉ��̏����ŊO�����������e�[�u�����������A <BR>
     * �@@�Y���s�I�u�W�F�N�g�ɂĊO�����������I�u�W�F�N�g�𐶐�����B <BR>
     * �@@�Y���s���Ȃ��ꍇ�́A��O���X���[����B <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02142<BR>
     *  <BR>
     * �@@[����] <BR>
     * �@@�O����������.�،���ЃR�[�h = �،���ЃR�[�h And <BR>
     * �@@�O����������.�s��R�[�h = �s��R�[�h And  <BR>
     *   ���s��R�[�h != null�̏ꍇ�̂� <BR>
     * �@@�i�O����������.�����R�[�h = �����R�[�h Or <BR>
     * �@@�@@�O����������.�����R�[�h = ���n�����R�[�h�j And  <BR>
     *   �������R�[�h != null�̏ꍇ�̂� <BR>
     * �@@�i�O����������.�������i�J�i�j like ������ Or
     *    �O����������.�������i�����j like �������j
     * �������� != null�̏ꍇ�̂݁B"%"��PR�w�ɂĖ������ɕt������Ă��邽�߁AAP�ł̕t���͕s�v�B
     *  <BR>
     * �@@[�擾��] <BR>
     * �@@�\�[�g���� <BR>
     *  <BR>
     * �Q�j�@@����\�̔��� <BR>
     * �@@�|�iis����\���� == true�j�̏ꍇ�A�P�j�Ŏ擾�����v�f�̂����A <BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂���̂�z��ɂĕԋp����B <BR>
     *  <BR>
     * �@@�@@�@@[����] <BR>
     * �@@�@@�@@�i�O����������.get�������().is�����ԓ�() == true�j And <BR>
     * �@@�@@�@@�i�O����������.get�������().is����~() == false Or <BR>
     * �@@�@@�@@�@@�O����������.get�������().is����~() == false�j <BR>
     *  <BR>���f��131�d�l�ύX�@@�@@�@@�@@2005/8/29  ���I
     *  ������.�s��R�[�h==null �̏ꍇ�́A�ȉ��̏����ɂ����Ă͂܂邩���`�F�b�N����B
     *    �O����������.get�s��().is�����~ == false
     * �@@�|�iis����\���� == false�j�̏ꍇ�A <BR>
     * �@@�@@�@@�P�j�Ŏ擾�����I�u�W�F�N�g��z��ɂĕԋp����B <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strMarketCode - (�s��R�[�h)
     *
     * ���@@null�w��̏ꍇ�͑S�s��
     * @@param l_strProductCode - �����R�[�h�i�����R�[�h�^���n�����R�[�h�j <BR>
     *  <BR>
     * ���@@���S��v <BR>
     * @@param l_strProductName - (������) <BR>
     *  <BR>
     * ���@@�O���^�����v
     * @@param l_blnIsTradedPossProduct - is����\���� <BR>
     *  <BR>
     * true�F�@@����\�Ȗ����̂݌��� <BR>
     * false�F�@@����\�^�s�Ɋւ�炸���� <BR>
     *
     *
     * @@param l_strSortCond - (�\�[�g����)
     * @@return webbroker3.feq.WEB3FeqProduct[]
     * @@throws WEB3BaseException
     * @@roseuid 4295E6C800C1
     */
    public WEB3FeqProduct[] getProduct(String l_strInstitutionCode,
        String l_strMarketCode, String l_strProductCode, String l_strProductName,
        boolean l_blnIsTradedPossProduct, String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProduct";
        log.entering(STR_METHOD_NAME);
        // �P�j�@@�����e�[�u������
        // �@@�ȉ��̏����ŊO�����������e�[�u�����������A
        // �@@�Y���s�I�u�W�F�N�g�ɂĊO�����������I�u�W�F�N�g�𐶐�����B
        // �@@�Y���s���Ȃ��ꍇ�́A��O���X���[����B
        //
        // �@@[����]
        // �@@�O����������.�،���ЃR�[�h = �،���ЃR�[�h And
        // �@@�O����������.�s��R�[�h = �s��R�[�h And
        //   ���s��R�[�h != null�̏ꍇ�̂�
        // �@@�i�O����������.�����R�[�h = �����R�[�h Or
        // �@@�@@�O����������.�����R�[�h = ���n�����R�[�h�j And
        //   �������R�[�h != null�̏ꍇ�̂�
        // �@@�i�O����������.�������i�J�i�j like ������ Or
        //    �O����������.�������i�����j like �������j
        // �������� != null�̏ꍇ�̂݁B"%"��PR�w�ɂĖ������ɕt������Ă��邽�߁AAP�ł̕t���͕s�v�B
        //
        // �@@[�擾��]
        // �@@�\�[�g����
        List l_lisFeqProduct = null;
        try
        {
            List l_lisWhereValue = new ArrayList();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "institution_code = ?";
            l_lisWhereValue.add(l_strInstitutionCode);

            if (l_strMarketCode != null)
            {
                l_strWhere += " and market_code = ?";
                l_lisWhereValue.add(l_strMarketCode);
            }

            if (l_strProductCode != null)
            {
                l_strWhere += " and (product_code = ? or offshore_product_code = ?) ";
                l_lisWhereValue.add(l_strProductCode);
                l_lisWhereValue.add(l_strProductCode);

            }
            if (l_strProductName != null)
            {
                l_strWhere += " and (standard_name_kana like ? or standard_name_kanji like ?)";
                l_lisWhereValue.add(l_strProductName);
                l_lisWhereValue.add(l_strProductName);
            }
            Object[] l_objWhereValues = new Object[l_lisWhereValue.size()];
            l_lisWhereValue.toArray(l_objWhereValues);
            l_lisFeqProduct = l_processor.doFindAllQuery(FeqProductRow.TYPE,
                l_strWhere,
                l_strSortCond,
                null,
                l_objWhereValues);
            if (l_lisFeqProduct == null || l_lisFeqProduct.isEmpty())
            {
                log.error("�O���������������݂��Ȃ��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            // �Q�j�@@����\�̔���
            // �@@�|�iis����\���� == true�j�̏ꍇ�A�P�j�Ŏ擾�����v�f�̂����A
            // �@@�ȉ��̏����ɓ��Ă͂܂���̂�z��ɂĕԋp����B
            //
            // �@@�@@�@@[����]
            // �@@�@@�@@�i�O����������.get�������().is�����ԓ�() == true�j And
            // �@@�@@�@@�i�O����������.get�������().is����~() == false Or
            // �@@�@@�@@�@@�O����������.get�������().is����~() == false�j
            //
            // �@@�|�iis����\���� == false�j�̏ꍇ�A
            // �@@�@@�@@�P�j�Ŏ擾�����I�u�W�F�N�g��z��ɂĕԋp����B
            List l_listFeqProductRow = new ArrayList();

            //�C�����ǉ��@@begin
            Hashtable l_tblBizDate=new Hashtable(3);
            Hashtable l_tblmarket=new Hashtable(3);
            //end

            if ((l_blnIsTradedPossProduct))
            {
                Iterator l_iterator = l_lisFeqProduct.iterator();
                int i = 0;
                while (l_iterator.hasNext())
                {
                    FeqProductRow l_feqProductRow = (FeqProductRow)l_iterator.next();

                    //�C�����ǉ��@@begin

                    WEB3FeqProduct l_feqProduct =
                        (WEB3FeqProduct)this.toProduct(l_feqProductRow);
                    String TRADING_CAL_CONTEXT_PATH = "web3.tradingcalendarcontext";
                    //������ԃR���e�L�X�g�̎擾
                    WEB3GentradeTradingClendarContext l_clendarContext =
                        (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                            TRADING_CAL_CONTEXT_PATH);
                    String l_strMarketKey=l_clendarContext.getInstitutionCode().trim()+l_feqProduct.getMarketCode();
                    WEB3GentradeMarket l_marker =(WEB3GentradeMarket)l_tblmarket.get(l_strMarketKey);
                    if(l_marker==null){
                        l_marker = (WEB3GentradeMarket) l_feqProduct.getMarket();
                        l_tblmarket.put(l_strMarketKey,l_marker);
                    }
                   WEB3GentradeTradingTimeManagement.resetMarketCode(l_marker.getMarketCode());
                    Date l_datBizDate=(Date)l_tblBizDate.get(l_marker.getMarketCode());
                    if(l_datBizDate==null){
                        l_datBizDate = WEB3GentradeTradingTimeManagement.
                            getOrderBizDate();
                       l_tblBizDate.put(l_marker.getMarketCode(),l_datBizDate);
                    }

                    FeqTradedProductRow l_feq_tp_row = FeqTradedProductDao.
                        findRowByProductIdMarketId(l_feqProduct.getProductId(),
                        l_marker.getMarketId());
                    l_feqProduct.l_feqTPRow=l_feq_tp_row;
                    //end

                    //���f��131�d�l�ύX�@@�@@�@@�@@2005/8/29  ���I
                    // ����.�s��R�[�h==null �̏ꍇ�́A�ȉ��̏����ɂ����Ă͂܂邩���`�F�b�N����B
                    // �O����������.get�s��().is�����~ == false

                    //�C���@@���@@�����@@begin
                    if (l_strMarketCode == null)
                    {

                        if (!WEB3FeqTradedProduct.isOpen(l_datBizDate,l_feq_tp_row) ||
                            (WEB3FeqTradedProduct.isBuyStop(l_feq_tp_row) && WEB3FeqTradedProduct.isSellStop(l_feq_tp_row)) ||
                            l_marker.isSuspension())
                        {
                        }
                        else
                        {
//                            WEB3FeqProduct l_product = new WEB3FeqProduct( (FeqProductRow)
//                                l_lisFeqProduct.get(i));
                            l_listFeqProductRow.add(l_feqProduct);
                        }
                    }
                    else
                    {
                        if (!WEB3FeqTradedProduct.isOpen(l_datBizDate,l_feq_tp_row) ||
                            (WEB3FeqTradedProduct.isBuyStop(l_feq_tp_row) && WEB3FeqTradedProduct.isSellStop(l_feq_tp_row)))
                        {
                        }
                        else
                        {
//                            WEB3FeqProduct l_product = new WEB3FeqProduct((FeqProductRow)l_lisFeqProduct.get(i));
//                            l_listFeqProductRow.add(l_product);
                            l_listFeqProductRow.add(l_feqProduct);

                        }
                    //end
               //end

                    }
                    i++;
                }
            }
            else
            {
                for (int i = 0; i< l_lisFeqProduct.size(); i++)
                {
                    WEB3FeqProduct l_product = new WEB3FeqProduct((FeqProductRow)l_lisFeqProduct.get(i));
                    l_listFeqProductRow.add(l_product);
                }
            }
            if (l_listFeqProductRow == null || l_listFeqProductRow.isEmpty())
            {
                return null;
            }

            WEB3FeqProduct[] l_feqProduct = new WEB3FeqProduct[l_listFeqProductRow.size()];
            l_listFeqProductRow.toArray(l_feqProduct);

            log.exiting(STR_METHOD_NAME);
            return l_feqProduct;
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

    }

    /**
     * (get���ݒl) <BR>
     * �Ēl�e�[�u������A�Y�����鍏�ݒl���擾����B <BR>
     *  <BR>
     * �P�j�Ώۍs�擾 <BR>
     *  <BR>
     *    �O���Ēl�e�[�u�����ȉ��̏����Ŏ擾����B <BR>
     *  <BR>
     *    [����] <BR>
     *    �s��R�[�h = ����.�O����������.�s��R�[�h <BR>
     *  <BR>
     * �@@�����Ɉ�v����s���A <BR>
     * �@@�i�O���Ēl�e�[�u��.�����l�@@< ����.�P�� <= �O���Ēl�e�[�u��.����l�j <BR>
     * �@@ �ɊY������s�̍s�I�u�W�F�N�g���擾����B <BR>
     *  <BR>
     * �Q�j���ݒl�ԋp <BR>
     *  <BR>
     *    �擾�����s.���ݒl��ԋp����B <BR>
     * @@param l_feqPrduct - (�O����������) <BR>
     * �O�����������I�u�W�F�N�g
     *
     * @@param l_dblPrice - (�P��)
     *
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 42A8276F027F
     */
    public double getTickValue(WEB3FeqProduct l_feqPrduct, double l_dblPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTickValue(WEB3FeqProduct l_feqPrduct, double l_dblPrice) ";
        log.entering(STR_METHOD_NAME);

        if (l_feqPrduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �P�j�Ώۍs�擾
        //
        //    �O���Ēl�e�[�u�����ȉ��̏����Ŏ擾����B
        //
        //    [����]
        //    �s��R�[�h = ����.�O����������.�s��R�[�h
        //
        // �@@�����Ɉ�v����s���A
        // �@@�i�O���Ēl�e�[�u��.�����l�@@< ����.�P�� <= �O���Ēl�e�[�u��.����l�j
        // �@@ �ɊY������s�̍s�I�u�W�F�N�g���擾����B
        //
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "market_code = ? and low_price < ? and cap_price >= ?";
            Object[] l_objWhereValues = new Object[3];
            l_objWhereValues[0] = l_feqPrduct.getMarketCode();
            l_objWhereValues[1] = new Double(l_dblPrice);
            l_objWhereValues[2] = new Double(l_dblPrice);
            List l_lisFeqTickValueMst =
                l_processor.doFindAllQuery(FeqTickValuesMstRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
            // �Q�j���ݒl�ԋp
            //
            //    �擾�����s.���ݒl��ԋp����B
            if (l_lisFeqTickValueMst == null || l_lisFeqTickValueMst.isEmpty())
            {
                log.debug("FeqTickValuesMstRow�擾���Ȃ�");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (l_lisFeqTickValueMst.size() > 1)
            {
                log.debug("FeqTickValuesMstRow�擾>1");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            FeqTickValuesMstRow l_feqTickValuesMstRow = (FeqTickValuesMstRow)l_lisFeqTickValueMst.get(0);
            return l_feqTickValuesMstRow.getTickValue();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (get�����l��) <BR>
     * �l���e�[�u������A�Y�����鐧���l�����擾����B <BR>
     *  <BR>
     * �P�j�Ώۍs�擾 <BR>
     *  <BR>
     *    �O���l���e�[�u�����ȉ��̏����Ŏ擾����B <BR>
     *  <BR>
     *    [����] <BR>
     *    �s��R�[�h = ����.�O����������.�s��R�[�h <BR>
     *  <BR>
     *    �����Ɉ�v����s���A <BR>
     * �@@�i�O���l���e�[�u��.�����l�@@< ����.�P�� <= �O���l���e�[�u��.����l�j <BR>
     * �@@ �ɊY������s�̍s�I�u�W�F�N�g���擾����B <BR>
     *  <BR>
     * �Q�j�l���ԋp <BR>
     *  <BR>
     *    �擾�����s.�l����ԋp����B <BR>
     * @@param l_feqPrduct - (�O����������) <BR>
     * �O�����������I�u�W�F�N�g
     *
     * @@param l_dblPrice - (�P��)
     *
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 42A82862028E
     */
    public double getLimitRange(WEB3FeqProduct l_feqPrduct, double l_dblPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLimitRange(WEB3FeqProduct l_feqPrduct, double l_dblPrice) ";
        log.entering(STR_METHOD_NAME);

        if (l_feqPrduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �P�j�Ώۍs�擾
        //
        //    �O���l���e�[�u�����ȉ��̏����Ŏ擾����B
        //
        //    [����]
        //    �s��R�[�h = ����.�O����������.�s��R�[�h
        //
        //    �����Ɉ�v����s���A
        // �@@�i�O���l���e�[�u��.�����l�@@< ����.�P�� <= �O���l���e�[�u��.����l�j
        // �@@ �ɊY������s�̍s�I�u�W�F�N�g���擾����B
        //
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "market_code = ? and low_price < ? and cap_price >= ?";
            Object[] l_objWhereValues = new Object[3];
            l_objWhereValues[0] = l_feqPrduct.getMarketCode();
            l_objWhereValues[1] = new Double(l_dblPrice);
            l_objWhereValues[2] = new Double(l_dblPrice);
            List l_lisFeqLimitPriceRangeMst =
                l_processor.doFindAllQuery(FeqLimitPriceRangeMstRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
            // �Q�j�l���ԋp
            //
            //    �擾�����s.�l����ԋp����B

            if (l_lisFeqLimitPriceRangeMst == null || l_lisFeqLimitPriceRangeMst.isEmpty())
            {
                log.debug("�O���l���e�[�u���擾�����s == null");
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (l_lisFeqLimitPriceRangeMst.size() > 1)
            {
                log.debug("�O���l���e�[�u���͕s��������B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            FeqLimitPriceRangeMstRow l_feqLimitPriceRangeMst =
                (FeqLimitPriceRangeMstRow)l_lisFeqLimitPriceRangeMst.get(0);
            log.exiting(STR_METHOD_NAME);
            return l_feqLimitPriceRangeMst.getRange();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }

    }

    /**
     * (get�ʉ�) <BR>
     * ��،���Ђ��o�^���Ă���ʉ݂����ׂĎ擾����B<BR>
     * <BR>
     * �i���ʁj�ʉ݃e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�i���ʁj�ʉ݃e�[�u��.�،���ЃR�[�h = �،���ЃR�[�h<BR>
     * <BR>
     * �Y���s�ɂāi���ʁj�ʉ݃I�u�W�F�N�g�𐶐����A�z��ɂĕԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@return WEB3GentradeCurrency
     * @@throws WEB3BaseException
     * @@roseuid 42AFD30101C4
     */
    public WEB3GentradeCurrency[] getCurrency(String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCurrency(String l_strInstitutionCode) ";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            Object[] l_objWhereValues = {l_strInstitutionCode};
            List l_lisCurrencys =
                l_processor.doFindAllQuery(GenCurrencyRow.TYPE,
                    "institution_code = ?",
                    l_objWhereValues);
            if (l_lisCurrencys == null || l_lisCurrencys.isEmpty())
            {
                log.debug("�ʉ݃I�u�W�F�N�g == null");
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            GenCurrencyRow[] l_currencyRow = new GenCurrencyRow[l_lisCurrencys.size()];
            l_lisCurrencys.toArray(l_currencyRow);

            List l_lisGenCurrencys = new ArrayList();
            for (int i = 0; i < l_currencyRow.length; i++)
            {
                WEB3GentradeCurrency l_currency =
                    new WEB3GentradeCurrency(new GenCurrencyParams(l_currencyRow[i]));
                l_lisGenCurrencys.add(l_currency);
            }
            WEB3GentradeCurrency[] l_genCurrency = new WEB3GentradeCurrency[l_lisGenCurrencys.size()];
            l_lisGenCurrencys.toArray(l_genCurrency);

            return l_genCurrency;
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }

    public Product toProduct(Row l_prow)
    {
        try
        {
            if (l_prow instanceof ProductRow)
            {
                return new WEB3FeqProduct((ProductRow)l_prow);
            }
            else
            {
                return new WEB3FeqProduct((FeqProductRow)l_prow);
            }

        }
        catch (DataException l_dfex)
        {
            String l_strMsg = "Exception when creating WEB3FeqProduct for product id: " + ((ProductRow)l_prow).getProductId();
            log.error(l_strMsg, l_dfex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                l_strMsg,
                l_dfex);
        }

    }

    public TradedProduct toTradedProduct(Row l_tprow)
    {
        try
        {
            if (l_tprow instanceof TradedProductRow)
            {
                return new WEB3FeqTradedProduct((TradedProductRow)l_tprow);
            }
            else
            {
                return new WEB3FeqTradedProduct((FeqTradedProductRow)l_tprow);
            }

        }
        catch (DataException l_dfex)
        {
            String l_strMsg = "Exception when creating FeqTradedProduct for traded product id: " + ((TradedProductRow)l_tprow).getTradedProductId();
            log.error(l_strMsg, l_dfex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                l_strMsg,
                l_dfex);
        }
    }

    public FeqTradedProduct getFeqTradedProduct(Institution inst, String productCode, String marketCode)
        throws NotFoundException
    {
        Product l_product;
        Market l_market;

        FeqTradedProduct l_feqTradedProduct =
            super.getFeqTradedProduct(inst, productCode, marketCode);

        return (FeqTradedProduct)toTradedProduct((FeqTradedProductRow)l_feqTradedProduct.getDataSourceObject());
    }
}
@
