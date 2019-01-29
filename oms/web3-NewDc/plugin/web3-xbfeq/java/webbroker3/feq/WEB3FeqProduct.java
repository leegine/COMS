head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O����������(WEB3FeqProduct)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  䈋�(���u) �V�K�쐬
Revesion History : 2008/01/16 �đo�g(���u) ���f��No.372
*/

package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqProductImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;
/**
 * (�O����������) <BR>
 * �O����������
 *
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3FeqProduct extends FeqProductImpl
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqTradedProduct.class);

    FeqProductRow l_feqProductRow = (FeqProductRow)this.getDataSourceObject();

    public FeqTradedProductRow l_feqTPRow = null;

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3FeqProduct(ProductRow l_prow)
        throws DataQueryException, DataNetworkException
    {
        super(l_prow);
    }

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3FeqProduct(FeqProductRow l_row)
        throws DataQueryException, DataNetworkException
    {
        super(l_row);
    }

    /**
     * (is��������戵�K��) <BR>
     * ��������戵�K�������𔻒肷��Btrue�̏ꍇ�A�K�����C <BR>
     * false�̏ꍇ����\�B <BR>
     *  <BR>
     * this.�O������Params.��������戵�K�� == 0:  <BR>
     * �@@�@@�@@��������ɂĎ戵�s�� �̏ꍇ�Atrue��ԋp����B <BR>
     * �ȊO�Afalse��ԋp����B <BR>
     * @@return boolean
     * @@roseuid 428052650150
     */
    public boolean isCapitalGainTaxDealingsReg()
    {
        final String STR_METHOD_NAME = "isCapitalGainTaxDealingsReg() ";
        log.entering(STR_METHOD_NAME);
        if (l_feqProductRow.getCapitalGainTaxDealingsReg() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

    }

    /**
     * (get��������) <BR>
     * �����������擾����B <BR>
     *  <BR>
     * this.�O������Params.����������ԋp����B <BR>
     * @@return Date
     * @@roseuid 4282B77300BE
     */
    public Date getExRightDate()
    {
        return this.l_feqProductRow.getExRightDate();
    }

    /**
     * (get�s��) <BR>
     * �igetMarket�j <BR>
     * �s��I�u�W�F�N�g���擾����B <BR>
     *  <BR>
     * �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()�ɂĎs��I�u�W�F�N�g���擾���ԋp����B <BR>
     *  <BR>
     * �@@[getMarket()�Ɏw�肷�����] <BR>
     * �@@�،���ЁiInstitution�j�F�@@this.getInstitution() <BR>
     * �@@�s��R�[�h�iString�j�F�@@this.get�s��R�[�h() <BR>
     * @@return WEB3GentradeMarket
     * @@throws WEB3BaseException
     * @@roseuid 4282C52A00AF
     */
    public WEB3GentradeMarket getMarket() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarket()";
        log.entering(STR_METHOD_NAME);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        try
        {
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finObjectManager.getMarket(this.getInstitution(), this.getMarketCode());
            log.exiting(STR_METHOD_NAME);
            return l_market;
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }
    }

    /**
     * (get�s��R�[�h) <BR>
     * �igetMarketCode�j <BR>
     * �s��R�[�h���擾����B <BR>
     *  <BR>
     * this.�O������Params.�s��R�[�h��ԋp����B <BR>
     * @@return String
     * @@roseuid 4282C5310310
     */
    public String getMarketCode()
    {
        return l_feqProductRow.getMarketCode();
    }

    /**
     * (get�ʉ݃R�[�h) <BR>
     * �igetcurrencyCode�j <BR>
     * �ʉ݃R�[�h���擾����B <BR>
     *  <BR>
     * this.�O������Params.�ʉ݃R�[�h��ԋp����B <BR>
     * @@return String
     * @@roseuid 428330A700CE
     */
    public String getCurrencyCode()
    {
        return l_feqProductRow.getCurrencyCode();
    }

    /**
     * (get�������) <BR>
     * �igetFeqTradedProduct�j <BR>
     * ����������擾����B <BR>
     *  <BR>
     * �P�j�@@�s��R�[�h���Z�b�g <BR>
     * �@@������ԊǗ�.reset�s��R�[�h()�ɂāA�s����Z�b�g����B <BR>
     *  <BR>
     * �@@[reset�s��R�[�h()�Ɏw�肷�����] <BR>
     * �@@�s��R�[�h�F�@@this.get�s��R�[�h() <BR>
     *  <BR>
     * �Q�j�@@��������擾 <BR>
     * �@@�O�������v���_�N�g�}�l�[�W��.getFeqTradedProduct()�ɂ� <BR>
     * �@@�O����������������擾���ԋp����B <BR>
     *  <BR>
     * �@@[getFeqTradedProduct()�Ɏw�肷�����] <BR>
     * �@@�،���ЁF�@@this.getInstitution() <BR>
     * �@@�����R�[�h�F�@@this.getProductCode() <BR>
     * �@@�s��R�[�h�F�@@this.get�s��R�[�h() <BR>
     * @@return WEB3FeqTradedProduct
     * @@throws WEB3BaseException
     * @@roseuid 4282C53802E1
     */
    public WEB3FeqTradedProduct getFeqTradedProduct() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFeqTradedProduct() ";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeTradingTimeManagement.resetMarketCode(this.getMarketCode());
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqProductManager l_productManager = (WEB3FeqProductManager)l_tradingModule.getProductManager();
        try
        {
            WEB3FeqTradedProduct l_tradedProduct =
                (WEB3FeqTradedProduct)l_productManager.
                getFeqTradedProduct(this.getInstitution(),
                    this.getProductCode(), this.getMarketCode());

            log.exiting(STR_METHOD_NAME);

            return l_tradedProduct;
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

    }

    /**
     * (get�ʉ�) <BR>
     * �ʉ݂��擾����B <BR>
     *  <BR>
     * �i���ʁj�ʉ݃I�u�W�F�N�g�𐶐����ԋp����B <BR>
     *  <BR>
     * [�R���X�g���N�^�Ɏw�肷�����] <BR>
     * �،���ЃR�[�h�F�@@getInstitution().getInstitutionCode() <BR>
     * �ʉ݃R�[�h�F�@@get�ʉ݃R�[�h() <BR>
     * @@return WEB3GentradeCurrency
     * @@throws WEB3BaseException
     * @@roseuid 42898A9001D4
     */
    public WEB3GentradeCurrency getCurrency() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCurrency() ";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeCurrency l_currency =
            WEB3GentradeCurrency.genCurrency(
                this.getInstitution().getInstitutionCode(),
                this.getCurrencyCode());

        log.exiting(STR_METHOD_NAME);
        return l_currency;
    }

    /**
     * (get���n�����R�[�h) <BR>
     * ���n�����R�[�h���擾����B <BR>
     *  <BR>
     * this.�O������Params.���n�����R�[�h��ԋp����B <BR>
     * @@return String
     * @@roseuid 42A975E603D7
     */
    public String getOffshoreProductCode()
    {
        return this.l_feqProductRow.getOffshoreProductCode();
    }

    /**
     * (get�\��������) <BR>
     * �\���̂��߂̖��������擾����B <BR>
     *  <BR>
     * �P�jthis.�O������Params.�������i�����j != null �̏ꍇ <BR>
     *  <BR>
     *    this.�O������Params.�������i�����j��ԋp����B <BR>
     *  <BR>
     * �Q�j����ȊO�̏ꍇ <BR>
     *  <BR>
     *    this.�O������Params.�������i�J�i�j��ԋp����B <BR>
     * @@return String
     * @@roseuid 42B0DAF70057
     */
    public String getDisplayProductName()
    {
        final String STR_METHOD_NAME = "getDisplayProductName()";
        log.entering(STR_METHOD_NAME);
        if (this.l_feqProductRow.getStandardNameKanji() != null)
        {
            log.exiting(STR_METHOD_NAME);
            return this.l_feqProductRow.getStandardNameKanji();
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return this.l_feqProductRow.getStandardNameKana();
        }
    }
}
@
