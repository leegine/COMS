head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoProductImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : IPO����(WEB3IpoProductImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2004/08/11 ���o�� �V�K�쐬
Revesion History : 2004/12/27 ���(SRA) �c�Ή�>>>037
Revesion History : 2005/01/05 ���(SRA) �c�Ή�>>>057
Revesion History : 2005/01/06 ���(SRA) �c�Ή�>>>059
Revesion History : 2005/01/07 ���(SRA) �c�Ή�>>>060,067
                   2006/10/16 ���G��(���u) ���f��  No.158
                   2006/11/22 �����q (���u) ���f�� No.165
Revesion History : 2010/09/21 ��V�� (���u) ���f�� No.180
*/

package webbroker3.ipo;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3IpoRegistDivDef;
import webbroker3.common.define.WEB3IpoStopDef;
import webbroker3.common.define.WEB3LotStatusDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProvisionalValueDivDef;
import webbroker3.common.define.WEB3PublicMarketDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.data.IpoProductDao;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3IpoScheduleDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO�����N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3IpoProductImpl implements Product
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3IpoProductImpl.class);
            
    /**
     * IPO�����s�I�u�W�F�N�g<BR>
     * �� IPO����Params�N���X��DDL��莩����������B
     */
    private IpoProductParams ipoProductRow;
    
    /**
     * @@roseuid 411301780038
     */
    public WEB3IpoProductImpl() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �w�肵��IPO�����h�c�ɊY������s��IPO�����e�[�u����茟������B<BR>
     * �������ʂ�IPO�����s�I�u�W�F�N�g�������Ɏw�肵�āA�R���X�g���N�^���R�[��
     * <BR>����B<BR>
     * �R���X�g���N�^�̖߂�l��ԋp����B<BR>
     * @@param l_lngIpoProductId - IPO����ID
     * @@return webbroker3.ipo.WEB3IpoProductImpl
     * @@roseuid 40BDB1C30315
     */
    public WEB3IpoProductImpl(long l_lngIpoProductId) throws DataFindException, DataQueryException, DataNetworkException
    {

        this(IpoProductDao.findRowByPk(l_lngIpoProductId));
                  
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �s�w��s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g���A�C���X�^���X�𐶐�����B
     * @@param l_ipoProductParams - IPO�����s�I�u�W�F�N�g
     * �� IPO����Params�N���X��DDL��莩����������B
     * 
     * @@return webbroker3.ipo.WEB3IpoProductImpl
     * @@roseuid 40BDB0C20363
     */
    public WEB3IpoProductImpl(Row l_ipoProductParams) 
    {
        final String STR_METHOD_NAME = " WEB3IpoProductImpl(Row)";
        log.entering(STR_METHOD_NAME);
        
        ipoProductRow = new IpoProductParams((IpoProductRow)l_ipoProductParams);
        log.debug("ipoProductRow = " + ipoProductRow);
                
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * �igetDataSourceObject�̎����j<BR>
     * <BR>
     * this.IPO�����s��ԋp����B
     * @@return Object
     * @@roseuid 40BDA40702D7
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return ipoProductRow;       
    }
    
    /**
     * (get�������s)<BR>
     * �istatic method�j
     * @@return webbroker3.ipo.data.IpoProductParams
     * @@roseuid 40BEF64403B8
     */
    public static IpoProductParams getInitializationParams() 
    {
        final String STR_METHOD_NAME = " getInitializationParams()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return new IpoProductParams();
    }
    
    /**
     * (getIPO�����h�c)
     * �igetProductId�̎����j<BR>
     * <BR>
     * this.IPO�����s.IPO�����h�c ��ԋp����B
     * @@return long
     * @@roseuid 40BDA40702D8
     */
    public long getProductId() 
    {
        final String STR_METHOD_NAME = " getProductId()";
        log.entering(STR_METHOD_NAME);      
        
        log.exiting(STR_METHOD_NAME);
        return ipoProductRow.getIpoProductId();
    }
    
    /**
     * (get�����^�C�v)
     * �igetProductType�̎����j<BR>
     * <BR>
     * this.IPO�����s.�����^�C�v ��ԋp����B
     * @@return ProductTypeEnum
     * @@roseuid 40BDA407030A
     */
    public ProductTypeEnum getProductType() 
    {
        final String STR_METHOD_NAME = " getProductType()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return ipoProductRow.getProductType();
    }
    
    /**
     * (get�w���\���P��)<BR>
     * �igetLotSize�̎����j<BR>
     * <BR>
     * this.IPO�����s.�w���\���P�� ��ԋp����B
     * @@return double
     * @@roseuid 40BDA40702F6
     */
    public double getLotSize() 
    {
        final String STR_METHOD_NAME = " getLotSize()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        if(ipoProductRow.getLotSizeIsNull())
        {
            return (0.0D / 0.0D);
        }
        else
        {
            return ipoProductRow.getLotSize();
        }     
    }
    
    /**
     * (get���J�s��)<BR>
     * this.IPO�����s.�s��R�[�h ��ԋp����B
     * @@return String
     * @@roseuid 40BDA4070308
     */
    public String getPublicMarket() 
    {
        final String STR_METHOD_NAME = " getPublicMarket()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return ipoProductRow.getPublicMarket();
    }
    
    /**
     * (get������)<BR>
     * �igetStandardName�̎����j<BR>
     * <BR>
     * this.IPO�����s.������ ��ԋp����B
     * @@return String
     * @@roseuid 40BDA4070315
     */
    public String getStandardName() 
    {
        final String STR_METHOD_NAME = " getStandardName()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return ipoProductRow.getStandardName();
    }
    
    /**
     * (get���Ў戵����)<BR>
     * this.IPO�����s.���Ў戵���� ��ԋp����B
     * @@return double
     * @@roseuid 40F519310345
     */
    public double getDealingQuantity() 
    {
        final String STR_METHOD_NAME = " getDealingQuantity()";
        log.entering(STR_METHOD_NAME);
        
        if(ipoProductRow.getDealingQuantityIsNull())
        {
            return (0.0D / 0.0D);
        }
        else
        {
            return ipoProductRow.getDealingQuantity();
        }  
    }
    
    /**
     * (get�،����)<BR>
     * �igetInstitution�̎����j<BR>
     * <BR>
     * this.IPO�����s.�،���ЃR�[�h�ɊY������،���ЃI�u�W�F�N�g��ԋp����B
     * @@return Institution
     * @@roseuid 40BDA4070318
     */
    public Institution getInstitution()
    {
        final String STR_METHOD_NAME = " getInstitution()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        String l_strInstitutionCode = ipoProductRow.getInstitutionCode();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        Institution l_institution;

        try
        {
            l_institution =
                l_finApp.getAccountManager().getInstitution(
                    l_strInstitutionCode);
            log.debug("�،���ЃI�u�W�F�N�g���擾����");
            log.exiting(STR_METHOD_NAME);
            return l_institution;        
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�،���ЃI�u�W�F�N�g���擾���Ȃ�");
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return null;
        }                
    }
    
    /**
     * (getIPO�X�P�W���[��)
     * IPO�X�P�W���[�����擾����B<BR>
     * <BR>
     * [IPO�X�P�W���[��]<BR>
     * 1�F�@@�u�b�N�r���f�B���O�J�n�O<BR>
     * 2�F�@@�u�b�N�r���f�B���O���Ԓ�<BR>
     * 3�F�@@�u�b�N�r���f�B���O�I��<BR>
     * 4�F�@@���J���i����<BR>
     * 5�F�@@���I�I��<BR>
     * 6�F�@@�w���\�����Ԓ�<BR>
     * 7�F�@@�w���\���I��<BR>
     * 8�F�@@���J<BR>
     * <BR>
     * �P�j�@@�u�b�N�r���f�B���O�J�n�O�̔���<BR>
     * �@@�|�ithis.is�u�b�N�r���f�B���O�J�n() == false�j�̏ꍇ�A<BR>
     * �@@�@@�@@IPO�X�P�W���[��.�h�u�b�N�r���f�B���O�J�n�O�h��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�u�b�N�r���f�B���O���Ԓ��̔���<BR>
     * �@@�|�ithis.is�u�b�N�r���f�B���O�J�n() == true && <BR>
     * �@@�@@�@@this.is�u�b�N�r���f�B���O�I��() == false�j�̏ꍇ�A<BR>
     * �@@�@@�@@IPO�X�P�W���[��.�h�u�b�N�r���f�B���O���Ԓ��h��ԋp����B<BR>
     * <BR>
     * �R�j�@@�u�b�N�r���f�B���O�I���̔���<BR>
     * �@@�|�ithis.is�u�b�N�r���f�B���O�I��() == true &&<BR>
     * �@@�@@�@@this.is���J���i����() == false�j�̏ꍇ�A<BR>
     * �@@�@@�@@IPO�X�P�W���[��.�h�u�b�N�r���f�B���O�I���h��ԋp����B<BR>
     * <BR>
     * �S�j�@@���J���i����̔���<BR>
     * �@@�|�ithis.is���J���i����() == true &&<BR>
     * �@@�@@�@@this.is�V�K���I�I��() == false�j�̏ꍇ�A<BR>
     * �@@�@@�@@IPO�X�P�W���[��.�h���J���i����h��ԋp����B<BR>
     * <BR>
     * �T�j�@@���I�I���̔���<BR>
�@@   * �|�ithis.is�V�K���I�I��() == true�j && <BR>
�@@�@@ *     this.is�w���\���J�n�i���Аݒ�j() == false�j�̏ꍇ�A<BR> 
�@@�@@ *     IPO�X�P�W���[��.�h���I�I���h��ԋp����B<BR> 
     * <BR>
     * �U�j�@@�w���\�����Ԓ��̔���<BR>
     * �@@�|�ithis.is�w���\���J�n�i���Аݒ�j() == true &&<BR>
     * �@@�@@�@@this.is�w���\���I���i���Аݒ�j() == false�j�̏ꍇ�A<BR>
     * �@@�@@�@@IPO�X�P�W���[��.�h�w���\�����Ԓ��h��ԋp����B<BR>
     * <BR>
     * �V�j�@@�w���\���I���̔���<BR>
     * �@@�|�ithis.is�w���\���I���i���Аݒ�j() == true &&<BR>
     * �@@�@@�@@this.is���J��() == false�j�̏ꍇ�A<BR>
     * �@@�@@�@@IPO�X�P�W���[��.�h�w���\���I���h��ԋp����B<BR>
     * <BR>
     * �W�j�@@���J�̔���<BR>
     * �@@�|�ithis.is���J��() == true�j�̏ꍇ�A<BR>
     * �@@�@@�@@IPO�X�P�W���[��.�h���J�h��ԋp����B<BR>
     * <BR>
     * @@return String
     * @@roseuid 40C69A6E01BB
     */
    public String getIpoSchedule() 
    {
        final String STR_METHOD_NAME = " getIpoSchedule()";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�u�b�N�r���f�B���O�J�n�O�̔���
        if (!this.isBookbuildingStart())
        {
            log.debug("�u�b�N�r���f�B���O�J�n�O�̔���");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.BOOKBUIDING_START_BEFORE;
        }
        
        //�Q�j�@@�u�b�N�r���f�B���O���Ԓ��̔���
        if (this.isBookbuildingStart() && 
            !this.isBookbuildingEnd())
        { 
            log.debug("�u�b�N�r���f�B���O���Ԓ��̔���");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.BOOKBUIDING_TERM;
        }
        
        //�R�j�@@�u�b�N�r���f�B���O�I���̔���
        if (this.isBookbuildingEnd() && 
            !this.isPublicPriceSettle())
        {
            log.debug("�u�b�N�r���f�B���O�I���̔���");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.BOOKBUIDING_END;
        }
        
        //�S�j�@@���J���i����̔���
        //�������敪���~�̎�
        if (this.isPublicPriceSettle() && !this.isNewLotteryEnd())
        {   
            log.debug("���J���i����̔���");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.PUBLIC_PRICE_DECISION;
        }
        
        //�T�j�@@���I�I���̔���
        log.debug("this.isNewLotteryEnd() = " + this.isNewLotteryEnd());
        log.debug("this.isOfferStart() = " + this.isOfferStart());
        if (this.isNewLotteryEnd() &&
            !this.isOfferStart())
        {
            log.debug("���I�I���̔���");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.LOTTERY_END;
        }
        
        //�U�j�@@�w���\�����Ԓ��̔���
        if (this.isOfferStart() && 
            !this.isOfferEnd())
        {
            log.debug("�w���\�����Ԓ��̔���");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.PURCHASE_APPLICATION_TERM;
        }
        
        //�V�j�@@�w���\���I���̔���
        if (this.isOfferEnd() && 
            !this.isPublicEnd())
        {
            log.debug("�w���\���I���̔���");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.PURCHASE_APPLICATION_END;
        }
        
        //���J�̔���
        if (this.isPublicEnd())
        {
            log.debug("���J�̔���");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.PUBLIC;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get����)<BR>
     * �����T�[�o���AIPO�����ɊY�����鎞�����擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iIPO���i�jget�����v�Q�ƁB
     * @@param l_account - �ڋq�I�u�W�F�N�g
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * @@return double
     * @@roseuid 40D7DBBD0181
     */
    public double getCurrentPrice(MainAccount l_account, WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCurrentPrice(MainAccount,WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);       
        
        // 1.get�s��R�[�h( )
        log.debug("get�s��R�[�h");
        String l_strMarketCode = this.getMarketCode();
        
        //2.reset�s��R�[�h(String)
        log.debug("reset�s��R�[�h");
        log.debug("l_strMarketCode = " + l_strMarketCode);
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        
        //3.reset��t���ԋ敪(String)
        log.debug("reset��t���ԋ敪");
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        
        //4.getTradingModule(ProductTypeEnum)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY );
        
        //5.getProductManager( )
        log.debug("getProductManager");
        EqTypeProductManager l_eqTypeProductManager = (EqTypeProductManager)l_tradingModule.getProductManager();
        
        //6.getInstitution( )
        Institution l_institution = l_account.getInstitution();        
        log.debug("l_institution = " + l_institution);
        
        //7.get���������R�[�h()
        String l_strIpoProductCode = this.getIpoProductCode();        
        
        try
        {
            //8.getTradedProduct            
            WEB3EquityTradedProduct l_eqTypeTradedProduct = 
               (WEB3EquityTradedProduct)l_eqTypeProductManager.getTradedProduct(l_institution,l_strIpoProductCode,l_ipoProduct.getMarketCode());
            
            //9.getSubAccount
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_account.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            //10.get�������
            WEB3EquityProductQuote l_equityProductQuote =
                l_eqTypeTradedProduct.getProductQuote(l_subAccount);
            double l_dblQuote;
            if (l_equityProductQuote != null)
            {
                //11.get����
                l_dblQuote = l_equityProductQuote.getQuote();
            }
            else
            {
                l_dblQuote = Double.NaN;
            }
            
            //12.reset�s��R�[�h(String)
            WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarginTradingDivDef.DEFAULT);
            
            //13.reset��t���ԋ敪(String)
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.IPO);
            
            log.exiting(STR_METHOD_NAME);
            return l_dblQuote;
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get�s��R�[�h)
     * ���J�s��ɊY������s��R�[�h���擾����B<BR>
     * <BR>
     * �|this.get���J�s��()���ȉ��̂����ꂩ�ł���΁A�s��R�[�h.�h�����h��ԋp����B<BR>
     * �@@10�F�@@����<BR>�@@
     * �@@11�F�@@���؈ꕔ�@@<BR>
     * �@@12�F�@@���ؓ񕔁@@<BR>
     * �@@13�F�@@�}�U�[�Y�@@<BR>
     * <BR>
     * �|this.get���J�s��()���ȉ��̂����ꂩ�ł���΁A�s��R�[�h.�h���h��ԋp����B<BR>
     * �@@20�F�@@���<BR>�@@
     * �@@21�F�@@��؈ꕔ�@@<BR>
     * �@@22�F�@@��ؓ񕔁@@<BR>
     * <BR>
     * �|this.get���J�s��()���ȉ��̂����ꂩ�ł���΁A�s��R�[�h.�h���É��h��ԋp����B<BR>
     * �@@30�F�@@����<BR>�@@
     * �@@31�F�@@���؈ꕔ�@@<BR>
     * �@@32�F�@@���ؓ񕔁@@<BR>
     * �@@33�F�@@�Z���g���b�N�X<BR>
     * <BR>
     * �|this.get���J�s��()���ȉ��̂����ꂩ�ł���΁A�s��R�[�h.�h�����h��ԋp����B<BR>
     * �@@40�F�@@����<BR>�@@
     * �@@41�F�@@Q-Board<BR>
     * <BR>
     * �|this.get���J�s��()���ȉ��̂����ꂩ�ł���΁A�s��R�[�h.�h�D�y�h��ԋp����B<BR>
     * �@@50�F�@@�D��<BR>�@@
     * �@@51�F�@@�A���r�V���X<BR>
     * <BR>
     * �|this.get���J�s��()���ȉ��̂����ꂩ�ł���΁A�s��R�[�h.�hJASDAQ�h��<BR>
     * �ԋp����B<BR>
     * �@@90�F�@@JASDAQ�i�X�^���_�[�h�j<BR>
     * �@@91�F�@@JASDAQ�i�O���[�X�j<BR>
     * <BR>
     * @@return String
     * @@roseuid 40D7DC1B00B6
     */
    public String getMarketCode() 
    {
        final String STR_METHOD_NAME = " getMarketCode()";
        log.entering(STR_METHOD_NAME); 
        
        //�s��R�[�h.�h�����h��ԋp����B
        String l_strPublicmarket = this.getPublicMarket();
        if (WEB3PublicMarketDef.TOKYO_STOCK_EXCHANGE.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.TSE_NO_ONE_DEPT.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.TSE_NO_TWO_DEPT.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.MOTHERS.equals(l_strPublicmarket))
        {
            log.debug("�s��R�[�h.�h�����h��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return WEB3MarketCodeDef.TOKYO;
        }
        
        //�s��R�[�h.�h���h��ԋp����
        if (WEB3PublicMarketDef.OSAKA_SECURITIES_EXCHANGE.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.OSE_NO_ONE_DEPT.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.OSE_NO_TWO_DEPT.equals(l_strPublicmarket))
        {
            log.debug("�s��R�[�h.�h���h��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return WEB3MarketCodeDef.OSAKA;
        }
        
        //�s��R�[�h.�h���É��h��ԋp����
        if (WEB3PublicMarketDef.NAGOYA_STOCK_EXCHANGE.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.NSE_NO_ONE_DEPT.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.NSE_NO_TWO_DEPT.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.CENTREX.equals(l_strPublicmarket))
        {
            log.debug("�s��R�[�h.�h���É��h��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return WEB3MarketCodeDef.NAGOYA;
        }
        
        //�s��R�[�h.�h�����h��ԋp����
        if (WEB3PublicMarketDef.FUKUOKA_STOCK_EXCHANGE.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.Q_BOARD.equals(l_strPublicmarket)) 
        {
            log.debug("�s��R�[�h.�h�����h��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return WEB3MarketCodeDef.FUKUOKA;
        }
        
        //�s��R�[�h.�h�D�y�h��ԋp����
        if (WEB3PublicMarketDef.SAPPORO_STOCK_EXCHANGE.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.AMBITIOUS.equals(l_strPublicmarket)) 
        {
            log.debug("�s��R�[�h.�h�D�y�h��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return WEB3MarketCodeDef.SAPPORO;
        }

        //�s��R�[�h.�hJASDAQ�h��ԋp����
        if (WEB3PublicMarketDef.JASDAQ_STANDARD.equals(l_strPublicmarket)
            || WEB3PublicMarketDef.JASDAQ_CLOSE.equals(l_strPublicmarket)) 
        {
            log.debug("�s��R�[�h.�hJASDAQ�h��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return WEB3MarketCodeDef.JASDAQ;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * 0��ԋp����B
     * @@return double
     * @@roseuid 40BDA4070306
     */
    public double getMarginRatio() 
    {
        final String STR_METHOD_NAME = " getMarginRatio()";
        log.entering(STR_METHOD_NAME); 
        log.exiting(STR_METHOD_NAME);
        return 0;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Market[]
     * @@roseuid 40BDA4070307
     */
    public Market[] getTradedMarkets() 
    {
        final String STR_METHOD_NAME = " getTradedMarkets()";
        log.entering(STR_METHOD_NAME); 
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return TradedProduct
     * @@roseuid 40BDA4070309
     */
    public TradedProduct getPrimaryTradedProduct() 
    {
        final String STR_METHOD_NAME = " getPrimaryTradedProduct()";
        log.entering(STR_METHOD_NAME); 
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * true��ԋp����B
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 40BDA407030B
     */
    public boolean isTradedOnMarket(Market l_arg0) 
    {
        final String STR_METHOD_NAME = " isTradedOnMarket()";
        log.entering(STR_METHOD_NAME); 
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return String
     * @@roseuid 40BDA4070316
     */
    public String getNameAlt1() 
    {
        final String STR_METHOD_NAME = " getNameAlt1()";
        log.entering(STR_METHOD_NAME); 
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return String
     * @@roseuid 40BDA4070317
     */
    public String getNameAlt2() 
    {
        final String STR_METHOD_NAME = " getNameAlt2()";
        log.entering(STR_METHOD_NAME); 
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return Market
     * @@roseuid 40D18A81006D
     */
    public Market getPrimaryMarket() 
    {
        final String STR_METHOD_NAME = " getPrimaryMarket()";
        log.entering(STR_METHOD_NAME); 
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (is�u�b�N�r���f�B���O�J�n)
     * �u�b�N�r���f�B���O�J�n�������߂��Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �@@�|�ithis.IPO�����s.�u�b�N�r���f�B���O�J�n���� <= ���ݓ���(*1)�j�̏ꍇ�A<BR>
     * true��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * (*1) ���ݓ���<BR>
     * TradingSystem.getSystemTimestamp()�ɂĎ擾����B<BR>
     * @@return boolean
     * @@roseuid 40BE92670297
     */
    public boolean isBookbuildingStart() 
    {
        final String STR_METHOD_NAME = " isBookbuildingStart()";
        log.entering(STR_METHOD_NAME); 
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        log.debug("l_tsCurrentTime = " + l_tsCurrentTime);
        if (WEB3DateUtility.compareToMinute(this.ipoProductRow.getBookbuildingStartDatetime(), l_tsCurrentTime) <= 0)
        {
            log.debug("true��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("false��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return false;       
        }
    }
    
    /**
     * (is�u�b�N�r���f�B���O�I��)<BR>
     * �u�b�N�r���f�B���O�I���������߂��Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �@@�|�ithis.IPO�����s.�u�b�N�r���f�B���O�I������ <= ���ݓ���(*1)�j�̏ꍇ�A<BR>
     * true��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * (*1) ���ݓ���<BR>
     * TradingSystem.getSystemTimestamp()�ɂĎ擾����B
     * @@return boolean
     * @@roseuid 40BE94850381
     */
    public boolean isBookbuildingEnd() 
    {
        final String STR_METHOD_NAME = " isBookbuildingEnd()";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        log.debug("l_tsCurrentTime = " + l_tsCurrentTime);
        log.debug("ipoProductRow.getBookbuildingEndDatetime() = " + ipoProductRow.getBookbuildingEndDatetime());
		if (WEB3DateUtility.compareToMinute(this.ipoProductRow.getBookbuildingEndDatetime(), l_tsCurrentTime) <= 0)
        {
            log.debug("true��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("false��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return false;       
        }
    }
    
    /**
     * (is���J���i����)<BR>
     * ���J���i���肪���肵�A�o�^���I����Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �@@�|�ithis.is�u�b�N�r���f�B���O�I��() == false�j�̏ꍇ�Afalse��ԋp����B<BR>
     * �@@�|�ithis.IPO�����s.���J���i == null�j�̏ꍇ�Afalse��ԋp����B<BR>
     * �@@�|�ȊO�Atrue��ԋp����B
     * @@return boolean
     * @@roseuid 40BEA1E50379
     */
    public boolean isPublicPriceSettle() 
    {
        final String STR_METHOD_NAME = " isPublicPriceSettle()";
        log.entering(STR_METHOD_NAME);
        
        if (!this.isBookbuildingEnd())
        {
            log.debug("false��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else if(this.ipoProductRow.getPublicPriceIsNull())
        {
            log.debug("false��ԋp����");            
            log.exiting(STR_METHOD_NAME);
            return false;            
        }
        else
        {
            log.debug("true��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return true;
        }       
    }
    
    /**
     * (is�w���\���J�n�i�ژ_�����L�ځj)
     * �w���\���J�n���i�ژ_�����L�ځj���߂��Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �@@�|�ithis.IPO�����s.�w���\���J�n���i�ژ_�����L�ځj <= ���ݓ�(*1)�j��
     * <BR>�ꍇ�Atrue��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * ���Ώۍ��ڂ�null�̏ꍇ��false�Ƃ���B <BR>
     * <BR>
     * (*1) ���ݓ�<BR>
     * TradingSystem.getSystemTimestamp()�Ŏ擾�������ݓ����̓��t
     * <BR>�iYYYYMMDD�j�����B
     * @@return boolean
     * @@roseuid 40BEA4B8001E
     */
    public boolean isOfferStartProspec() 
    {
        final String STR_METHOD_NAME = " isOfferStartProspec()";
        log.entering(STR_METHOD_NAME);
        if(ipoProductRow.getOfferStartDateProspec() != null)
        {
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
			Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        
			if (WEB3DateUtility.compareToDay(this.ipoProductRow.getOfferStartDateProspec(), l_tsCurrentTime) <= 0)
			{
				log.debug("true��ԋp����");
				log.exiting(STR_METHOD_NAME);
				return true;
			}
			else
			{
				log.debug("false��ԋp����");
				log.exiting(STR_METHOD_NAME);
				return false;
			} 			
        }	
	    else
	    {
			log.debug("�w���\���J�n���i�ژ_�����L�ځj������Ȃ̂�false��ԋp����");
			log.exiting(STR_METHOD_NAME);
			return false;
        }            
   
    }
    
    /**
     * (is�w���\���I���i�ژ_�����L�ځj)
     * �w���\���I�����i�ژ_�����L�ځj���߂��Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �@@�|�ithis.IPO�����s.�w���\���I�����i�ژ_�����L�ځj < ���ݓ�(*1)�j��
     * <BR>
     * �ꍇ�Atrue��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * ���Ώۍ��ڂ�null�̏ꍇ��false�Ƃ���B <BR>
     * <BR>
     * (*1) ���ݓ�<BR>
     * TradingSystem.getSystemTimestamp()�Ŏ擾�������ݓ����̓��t<BR>
     * �iYYYYMMDD�j�����B
     * @@return boolean
     * @@roseuid 40BEA55A033B
     */
    public boolean isOfferEndProspec() 
    {
        final String STR_METHOD_NAME = " isOfferEndProspec()";
        log.entering(STR_METHOD_NAME);
        
		if(ipoProductRow.getOfferEndDateProspec() != null)
		{
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
			Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        
			if (WEB3DateUtility.compareToDay(this.ipoProductRow.getOfferEndDateProspec(), l_tsCurrentTime) < 0)
			{
				log.debug("true��ԋp����");
				log.exiting(STR_METHOD_NAME);
				return true;
			}
			else
			{
				log.debug("false��ԋp����");
				log.exiting(STR_METHOD_NAME);
				return false;
			} 			
		}	
		else
		{
			log.debug("�w���\���I�����i�ژ_�����L�ځj������Ȃ̂�false��ԋp����");
			log.exiting(STR_METHOD_NAME);
			return false;
		}                           

    }
    
    /**
     * (is�w���\���J�n�i���Аݒ�j)
     * �w���\���J�n�����i���Аݒ�j���߂��Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �@@�|�ithis.IPO�����s.�w���\���J�n�����i���Аݒ�j <= ���ݓ���(*1)�j��
     * <BR>
     * �ꍇ�Atrue��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * ���Ώۍ��ڂ�null�̏ꍇ��false�Ƃ���B <BR>
     * <BR>
     * (*1) ���ݓ���<BR>
     * TradingSystem.getSystemTimestamp()�Ŏ擾����B
     * @@return boolean
     * @@roseuid 40BEA5930212
     */
    public boolean isOfferStart() 
    {
        final String STR_METHOD_NAME = " isOfferStart()";
        log.entering(STR_METHOD_NAME);

		if(ipoProductRow.getOfferStartDatetime() != null)
		{
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
			Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        
			if (WEB3DateUtility.compareToMinute(this.ipoProductRow.getOfferStartDatetime(), l_tsCurrentTime) <= 0)
			{
				log.debug("true��ԋp����");
				log.exiting(STR_METHOD_NAME);
				return true;
			}
			else
			{
				log.debug("false��ԋp����");
				log.exiting(STR_METHOD_NAME);
				return false;
			} 			
		}	
		else
		{
			log.debug("�w���\���J�n�����i���Аݒ�j������Ȃ̂�false��ԋp����");
			log.exiting(STR_METHOD_NAME);
			return false;
		}         

    }
    
    /**
     * (is�w���\���I���i���Аݒ�j)
     * �w���\���I�������i���Аݒ�j���߂��Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �@@�|�ithis.IPO�����s.�w���\���I�������i���Аݒ�j <= ���ݓ���(*1)�j��
     * <BR>
     * �ꍇ�Atrue��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * ���Ώۍ��ڂ�null�̏ꍇ��false�Ƃ���B <BR>
     * <BR>
     *      * (*1) ���ݓ���<BR>
     * TradingSystem.getSystemTimestamp()�Ŏ擾����B
     * @@return boolean
     * @@roseuid 40BEA5C301F3
     */
    public boolean isOfferEnd() 
    {
        final String STR_METHOD_NAME = " isOfferEnd()";
        log.entering(STR_METHOD_NAME);
        
		if(ipoProductRow.getOfferEndDatetime() != null)
		{
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
			Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        
			if (WEB3DateUtility.compareToMinute(this.ipoProductRow.getOfferEndDatetime(), l_tsCurrentTime) <= 0)
			{
				log.debug("true��ԋp����");
				log.exiting(STR_METHOD_NAME);
				return true;
			}
			else
			{
				log.debug("false��ԋp����");
				log.exiting(STR_METHOD_NAME);
				return false;
			} 			
		}	
		else
		{
			log.debug("�w���\���I�������i���Аݒ�j������Ȃ̂�false��ԋp����");
			log.exiting(STR_METHOD_NAME);
			return false;
		}                
    }
    
    /**
     * (is�V�K���I�I��)
     * ���I���I�����Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �@@�|�ȉ��̏����ɓ��Ă͂܂�ꍇ�Atrue��ԋp����B<BR>
     *  �@@�@@�ithis.IPO�����s.���I��� != �h0�FDEFAULT�i���I���ρj�h�j<BR>
     * �@@�|�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * (*1) ���ݓ�<BR>
     * TradingSystem.getSystemTimestamp()�Ŏ擾�������ݓ����̓��t<BR>
     * �iYYYYMMDD�j�����B<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40BEA686035A
     */
    public boolean isNewLotteryEnd() 
    {
        final String STR_METHOD_NAME = " isNewLotteryEnd()";
        log.entering(STR_METHOD_NAME);
        
        if (!WEB3LotStatusDef.DEFAULT.equals(this.ipoProductRow.getLotStatus()))
        {
            log.debug("true��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("false��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is���J��)
     * ���J�ς�IPO�������𔻒肷��B<BR>
     * <BR>
     * �@@�|�ithis.IPO�����s.���J�� <= ���ݓ�(*2)�j�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * ���Ώۍ��ڂ�null�̏ꍇ��false�Ƃ���B <BR>
     * <BR>
     * (*1) ���ݓ���<BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * (*2) ���ݓ�<BR>
     * ���ݓ����̓��t�iYYYYMMDD�j�����B<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40BEA5E40270
     */
    public boolean isPublicEnd() 
    {
        final String STR_METHOD_NAME = " isPublicEnd()";
        log.entering(STR_METHOD_NAME);
            
		if(ipoProductRow.getPublicOfferingDate() != null)
		{
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
			Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
			String l_strCurrentTime = WEB3DateUtility.formatDate(l_tsCurrentTime,"yyyyMMdd");
        
			String l_strPublicOfferingDate = WEB3DateUtility.formatDate(this.ipoProductRow.getPublicOfferingDate(),"yyyyMMdd");

            if (l_strPublicOfferingDate.compareTo(l_strCurrentTime) <= 0)
			{
				log.debug("���J�� <= ���ݓ�");
				log.exiting(STR_METHOD_NAME);
				return true;
			}
			else
			{
				log.debug("false��ԋp����");
				log.exiting(STR_METHOD_NAME);
				return false;
			}			
		}
		else
		{
			log.debug("���J��������Ȃ̂�false��ԋp����");
			log.exiting(STR_METHOD_NAME);
			return false;
		}       

    }
    
    /**
     * (is�u�b�N�r���f�B���O�\���\)
     * �Y���������u�b�N�r���f�B���O�\���\�ȏ�Ԃ��𔻒肷��B<BR>
     * <BR>
     * �P�j�@@this.IPO�����s�̈ȉ��̍��ڂ�null���Z�b�g����Ă���ꍇ�Afalse��
     * <BR>
     * �ԋp����B<BR>
     * <BR>
     * �@@IPO�o�^�敪�ڍ�<BR>
     * �@@�����R�[�h<BR>
     * �@@������<BR>
     * �@@���J�s��<BR>
     * �@@�������敪<BR>
     * �@@�������񎦓�<BR>
     * �@@�劲���،���Ж�<BR>
     * �@@�\���p�P�ʋ敪<BR>
     * �@@�u�b�N�r���f�B���O�J�n����<BR>
     * �@@�u�b�N�r���f�B���O�I������<BR>�@@
     *   �����������l <BR>
     *   ����������l <BR>
     *   ���Ў戵���� <BR>
     *   �w���\���P�� <BR>
     *   ���� <BR>
     *   ���吔�� <BR>
     *   ���o���� <BR>
     * <BR>
     * �Q�j�@@�u�b�N�r���f�B���O���ԓ��̔���<BR>
     * <BR>
     * �@@�|�u�b�N�r���f�B���O���ԓ��łȂ��ꍇ<BR>
     * �@@�ithis.is�u�b�N�r���f�B���O�J�n() == false Or<BR> 
     * �@@�@@this.is�u�b�N�r���f�B���O�I��() == true�j�Afalse��ԋp����B<BR>
     * <BR>
     * �R�j�@@IPO���~�^IPO��~�̔���<BR>
     * �@@�|IPO���~�A�܂��͒�~�̏ꍇ<BR>
     * �@@�ithis.is�戵��() == false�j�Afalse��ԋp����B<BR>
     * <BR>
     * �S�j�@@�ȊO�̏ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40D2BE5800E1
     */
    public boolean isBookbuildingOrderPossible() 
    {
        final String STR_METHOD_NAME = " isBookbuildingOrderPossible()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@
        log.debug("ipoProductRow.getPublicOfferingDate() = " + ipoProductRow.getPublicOfferingDate());
        log.debug("this.ipoProductRow.getPublicOfferingDateCount() = " + this.ipoProductRow.getPublicOfferingDateCount());
        if (this.ipoProductRow.getIpoRegistDetailDiv() == null ||
            this.ipoProductRow.getProductCode() == null ||
            this.ipoProductRow.getStandardName() == null ||
            this.ipoProductRow.getPublicMarket() == null ||
            this.ipoProductRow.getProvisionalValueDiv() == null ||
            this.ipoProductRow.getProvisionalValueOpenDate() == null ||
            this.ipoProductRow.getLeadManagingUnderwriter() == null ||
            this.ipoProductRow.getIpoUnitDiv() == null ||
            this.ipoProductRow.getBookbuildingStartDatetime() == null ||
            this.ipoProductRow.getBookbuildingEndDatetime() == null ||
            this.ipoProductRow.getProvisionalMinValueIsNull() ||
            this.ipoProductRow.getProvisionalMaxValueIsNull() || 
            this.ipoProductRow.getDealingQuantityIsNull()||
            this.ipoProductRow.getLotSizeIsNull() ||
            this.ipoProductRow.getTickValueIsNull() ||
            this.ipoProductRow.getPublicOfferingQuantityIsNull() ||
            this.ipoProductRow.getPublicSalesQuantityIsNull())
        {
            log.debug(STR_METHOD_NAME + "= false" + " : IPO�u�b�N�r���f�B���O�\���s�\");
            log.exiting(STR_METHOD_NAME);
            return false;
        }                            
        //�Q�j�u�b�N�r���f�B���O���ԓ��łȂ��ꍇ
        else if (!this.isBookbuildingStart() || this.isBookbuildingEnd())
        {
            log.debug(STR_METHOD_NAME + "= false" + " : �u�b�N�r���f�B���O���ԓ��łȂ�");
            log.exiting(STR_METHOD_NAME);
            return false;
        }        
        //�R�jIPO���~�A�܂��͒�~�̏ꍇ
        else if (!this.isDealtInProcess())
        {
            log.debug(STR_METHOD_NAME + "= false" + " : IPO�J�Ò��łȂ�");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //�S�j
        else
        {
            log.debug(STR_METHOD_NAME + "= true" + " : IPO�u�b�N�r���f�B���O�\���\");   
            log.exiting(STR_METHOD_NAME);
            return true;    
        }
    }
    
    /**
     * (is�w���\���\)
     * �Y���������w���\���\�ȏ�Ԃ��𔻒肷��B<BR>
     * <BR>
     * �P�j�@@IPO���~�^IPO��~�̔���<BR>
     * �@@�|IPO���~�A�܂��͒�~�̏ꍇ<BR>
     * �@@�ithis.is�戵��() == false�j�Afalse��ԋp����B<BR>
     * �Q�j�@@�w���\�����ԓ��ł��邩�̔���<BR>
     * �@@�|�@@�iIPO����.is�w���\���J�n�i���Аݒ�j() == ture && IPO����.is<BR>�w���\���I���i���Аݒ�j() == 
     * false�j�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40DBB716029D
     */ 
    public boolean isOfferPossible() 
    {
        final String STR_METHOD_NAME = " isOfferPossible()";
        log.entering(STR_METHOD_NAME);
        
        //IPO���~�^IPO��~�̔���
        if (!this.isDealtInProcess())
        {
            log.debug("IPO���~�^IPO��~�̔���");
            log.exiting(STR_METHOD_NAME);
            return false;    
        }        
        //�w���\�����ԓ��ł��邩�̔���
        if (this.isOfferStart() && 
            !this.isOfferEnd())
        {
            log.debug("�w���\�����ԓ��ł��邩�̔���");
            log.exiting(STR_METHOD_NAME);
            return true;   
        }
        else   
        {
            log.debug("�ȊO�Afalse��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return false;
        }      
    }
    
    /**
     * (is���މ\)
     * �Y�����������މ\�ȏ�Ԃ��𔻒肷��B<BR>
     * <BR>
     * �P�j�@@IPO���~�^IPO��~�̔���<BR>
     * �@@�|IPO���~�A�܂��͒�~�̏ꍇ<BR>
     * �@@�ithis.is�戵��() == false�j�Afalse��ԋp����B
     * �Q�j�@@���މ\���ԓ��ł��邩�̔���<BR>
     * �@@�|�iIPO����.is�V�K���I�I��() == ture && IPO����.is�w���\���I��
     *  <BR>�i���Аݒ�j() == false�j�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40DBB76200B4
     */
    public boolean isDeclinePossible() 
    {
        final String STR_METHOD_NAME = " isDeclinePossible()";
        log.entering(STR_METHOD_NAME); 
        //IPO���~�^IPO��~�̔���
        if (!this.isDealtInProcess())
        {
            log.debug("IPO���~�^IPO��~�̔���");
            log.exiting(STR_METHOD_NAME);
            return false;    
        } 
        //���މ\���ԓ��ł��邩�̔���
        if (this.isNewLotteryEnd() && 
            !this.isOfferEnd())
        {
            log.debug("���މ\���ԓ��ł��邩�̔���");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("�ȊO�Afalse��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is�폜����)
     * �L���Ȗ����i�폜����Ă��Ȃ��j���𔻒肷��B<BR>
     * <BR>
     * �@@�|�ithis.IPO�����s.�폜�t���O == BooleanEnum.TRUE�j�̏ꍇ�Atrue��<BR>�ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B
     * @@return boolean
     * @@roseuid 40C6B2CF026F
     */
    public boolean isDeletedProduct() 
    {
        final String STR_METHOD_NAME = " isDeletedProduct()";
        log.entering(STR_METHOD_NAME);
        
        if (BooleanEnum.TRUE.equals(this.ipoProductRow.getDeleteFlag()))
        {
            log.debug("return true");
            log.exiting(STR_METHOD_NAME);
            return true; 
        }
        else
        {
            log.debug("return false");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is���~)
     * IPO��������W���~��Ԃł��邩�𔻒肷��B<BR>
     * <BR>
     * �@@�ithis.IPO�����s.IPO��~ == �h���~�h�j�̏ꍇtrue�A�ȊO��false��ԋp����B
     * @@return boolean
     * @@roseuid 40C6B6B002DC
     */
    public boolean isDiscontinuation() 
    {
        final String STR_METHOD_NAME = " isDiscontinuation()";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3IpoStopDef.DISCONTINUATION.equals(this.ipoProductRow.getIpoStop()))
        {
            log.debug("return true");
            log.exiting(STR_METHOD_NAME);
            return true; 
        }
        else
        {
            log.debug("return false");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is�戵��)
     * IPO�������戵���ł��邩�𔻒肷��B<BR>
     * <BR>
     * �@@�ithis.IPO�����s.IPO��~ == �hDEFAULT�i�J�Ò��j�h�j�̏ꍇtrue�A<BR>
     * �ȊO��false��ԋp����B
     * @@return boolean
     * @@roseuid 40BF11C70212
     */
    public boolean isDealtInProcess() 
    {
        final String STR_METHOD_NAME = " isDealtInProcess()";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3IpoStopDef.DEFAULT.equals(this.ipoProductRow.getIpoStop()))
        {
            log.debug("return true");
            log.exiting(STR_METHOD_NAME);
            return true; 
        }
        else
        {
            log.debug("return false");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is�X�P�W���[������)
     * �X�P�W���[�������肵�Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �|�ȉ��̍��ڂŁA�P�ł�null�������Ă���ꍇ�́Afalse��ԋp����B<BR>
     * �|�ȉ��̍��ڂ��A���ׂ�null�łȂ��ꍇ�inot null�j�́Atrue��ԋp����B<BR>
     * <BR>
     * �@@this.IPO�����s.���J���i�����<BR>
     * �@@this.IPO�����s.���I��<BR>
     * �@@this.IPO�����s.�w���\���J�n���i�ژ_�����L�ځj<BR>
     * �@@this.IPO�����s.�w���\���I�����i�ژ_�����L�ځj<BR>
     * �@@this.IPO�����s.�w���\���J�n�����i���Аݒ�j<BR>
     * �@@this.IPO�����s.�w���\���I�������i���Аݒ�j<BR>
     * �@@this.IPO�����s.���J��<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40C57241009A
     */
    public boolean isScheduleDecision() 
    {
        final String STR_METHOD_NAME = " isScheduleDecision()";
        log.entering(STR_METHOD_NAME);

        if (this.ipoProductRow.getPublicPriceSettleDate() == null ||
            this.ipoProductRow.getLotDate() == null ||
            this.ipoProductRow.getOfferStartDateProspec() == null || 
            this.ipoProductRow.getOfferEndDateProspec() == null || 
            this.ipoProductRow.getOfferStartDatetime() == null ||
            this.ipoProductRow.getOfferEndDatetime() == null || 
            this.ipoProductRow.getPublicOfferingDate() == null)
        {
            log.debug("return false");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.debug("return true");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }
    
    /**
     * (is�f�B�X�J�E���g����)
     * ���Y�������f�B�X�J�E���g�������𔻒肷��B<BR>
     * <BR>
     * �@@�|�ithis.IPO�����s�������敪 == �h�f�B�X�J�E���g���h�j�̏ꍇ�A<BR>
     * true��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B
     * @@return boolean
     * @@roseuid 40D7E584026C
     */
    public boolean isDiscountHandling() 
    {
        final String STR_METHOD_NAME = " isDiscountHandling()";
        log.entering(STR_METHOD_NAME);
        if (WEB3ProvisionalValueDivDef.DISCOUNT_RATIO.equals(this.ipoProductRow.getProvisionalValueDiv()))
        {
            log.debug("return true");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("return false");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (get���������R�[�h)<BR>
     * ���������R�[�h���擾����B<BR> 
     * <BR>
     * this.IPO�����s.�����R�[�h�̖������h1�h�̏ꍇ�A<BR>
     * �������h0�h�ɕύX���������R�[�h��ԋp����B<BR>
     * �ȊO�Athis.IPO�����s.�����R�[�h��ԋp����B<BR>
     * <BR>
     * @@return String
     */
    public String getIpoProductCode() 
    {
        final String STR_METHOD_NAME = " getIpoProductCode() ";
        log.entering(STR_METHOD_NAME);
        String l_strIpoProductCode = ipoProductRow.product_code;
        if(l_strIpoProductCode.endsWith("1"))
        {
            log.debug("������1�̏ꍇ�̊��������R�[�h�ԋp�l; " 
                + (l_strIpoProductCode.substring(0,(l_strIpoProductCode.length()-1)) + "0"));
            log.exiting(STR_METHOD_NAME);
            return (l_strIpoProductCode.substring(0,(l_strIpoProductCode.length()-1)) + "0");
        }
        else
        {
            log.debug("������0�̏ꍇ�̊��������R�[�h�ԋp�l; " + l_strIpoProductCode);
            log.exiting(STR_METHOD_NAME);
            return l_strIpoProductCode;
        }
    }
        
    /**
     * IPO�����h�c��V�K�̔�(*1)���Athis.IPO�����s.IPO�����h�c�ɃZ�b�g����B<BR>
     * <BR>
     * (*1) IPO�����h�c�̐V�K�̔�<BR>
     * �@@IPO����DAO.newPkValue()���\�b�h�ɂĎ擾����B<BR>
     * �@@�� IPO����DAO�N���X��DDL��莩����������B
     * @@roseuid 40BEEC7E0221
     */
    public void setNewId() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setNewId()";
        log.entering(STR_METHOD_NAME);
        try
        {
            log.debug("IPO�����h�c�̐V�K�̔�");
            long l_lngNewPkValue = IpoProductDao.newPkValue();
            this.ipoProductRow.setIpoProductId(l_lngNewPkValue);
            log.debug("l_lngNewPkValue = " + l_lngNewPkValue);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80004,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80004,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }     
    }
    
    /**
     * (set������)
     * ���������e�[�u�����������擾���A�{�I�u�W�F�N�g�ɃZ�b�g����B<BR>
     * �������ithis.IPO�����s.IPO�o�^�敪 == �h�����h�j�̏ꍇ�̂ݏ������{�B<BR>
     * ���V�K���ithis.IPO�����s.IPO�o�^�敪 == �h�V�K���j�̏ꍇ�́A<BR>
     *   �Z�b�g���s�킸�������I������B<BR> 
     * <BR>
     * <BR>
     * �P�j�@@���������e�[�u���Ǎ�<BR>
     * �@@���������e�[�u�����A�،���ЁA�����R�[�h�ɊY������s���擾����(*1)�B<BR>
     *   �s���擾�ł��Ȃ������ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00495<BR>
     * <BR>
     * (*1) �����������擾���郁�\�b�h<BR>
     * �@@�@@EqtypeProductDao.findRowByInstitutionCodeProductCode()<BR>
     * <BR>
     * �@@�@@[findRowByInstitutionCodeProductCode()�Ɏw�肷�����]<BR>
     * �@@�@@p_institutionCode�F�@@this.IPO�����s.�،���ЃR�[�h<BR>
     * �@@�@@p_productCode�F�@@this.get���������R�[�h() <BR>
     * <BR>
     * �Q�j�@@�������Z�b�g<BR>
     * �@@�擾�������������s�iEqTypeProductParams�j.���������A<BR>
     * this.IPO�����s.�������ɃZ�b�g����B<BR>
     * <BR>
     * @@roseuid 40BEA8B0030C
     */
    public void setStandardName() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setStandardName()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�������Z�b�g
            if (WEB3IpoRegistDivDef.LISTED.equals(this.ipoProductRow.getIpoRegistDiv()))
            {
                //���������e�[�u���Ǎ�
                EqtypeProductRow l_eqtypeProductRow = 
                    EqtypeProductDao.findRowByInstitutionCodeProductCode(this.ipoProductRow.getInstitutionCode(),
                        this.getIpoProductCode());
                log.debug(this.getIpoProductCode());
            
                if (l_eqtypeProductRow == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00495,
                        getClass().getName() + STR_METHOD_NAME);
                }
            
                log.debug("�����");
                this.ipoProductRow.setStandardName(l_eqtypeProductRow.getStandardName());
                log.exiting(STR_METHOD_NAME);
            }
            else if (WEB3IpoRegistDivDef.OPEN_LISTING.equals(this.ipoProductRow.getIpoRegistDiv()))
            {
                log.debug("�V�K���");
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
     
    }
    
    /**
     * (set��W��~�^�ĊJ)
     * IPO������IPO��~��Ԃ�ύX����B<BR>
     * <BR>
     * �@@�|�ithis.IPO�����s.IPO��~ == �h��~���h�j�̏ꍇ�A<BR>
     * �@@�@@this.IPO�����s.IPO��~ = �hDEFAULT�i�J�Ò��j�h���Z�b�g����B<BR>
     * �@@�|�ithis.IPO�����s.IPO��~ == �hDEFAULT�i�J�Ò��j�h�j�̏ꍇ�A<BR>
     * �@@�@@this.IPO�����s.IPO��~ = �h��~���h���Z�b�g����B<BR>
     * �@@�|�ȊO�A�������Ȃ��B
     * @@roseuid 40D0255A01B3
     */
    public void setRecruitStopResumption() 
    {
        final String STR_METHOD_NAME = " setRecruitStopResumption()";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3IpoStopDef.STOPPING.equals(this.ipoProductRow.getIpoStop()))
        {
            log.debug("IPO��~ == �h��~���h");
            this.ipoProductRow.setIpoStop(WEB3IpoStopDef.DEFAULT);
        }
        else if (WEB3IpoStopDef.DEFAULT.equals(this.ipoProductRow.getIpoStop()))
        {
            log.debug("IPO��~ == �hDEFAULT�i�J�Ò��j�h");
            this.ipoProductRow.setIpoStop(WEB3IpoStopDef.STOPPING);
        }
        
        log.exiting(STR_METHOD_NAME);    
    }
    
    /**
     * (set���~)
     * IPO������IPO���~��Ԃ��Z�b�g����B<BR>
     * <BR>
     * �@@�|�ithis.IPO�����s.IPO��~ = �h���~�h�j���Z�b�g����B
     * @@roseuid 40D027D000A9
     */
    public void setDiscontinuation() 
    {
        final String STR_METHOD_NAME = " setDiscontinuation()";
        log.entering(STR_METHOD_NAME);
        
        this.ipoProductRow.setIpoStop(WEB3IpoStopDef.DISCONTINUATION);
        log.debug("setDiscontinuation------------->ok");
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set���I�I��)
     * IPO�����ɒ��I�I�����Z�b�g����B<BR>
     * <BR>
     * ���@@�V�K���I�iis�V�K���I() == true�j�̏ꍇ<BR>
     * �@@�|�ithis.IPO�����s.���I��� = �h�V�K���I�ρh�j���Z�b�g����B<BR>
     * <BR>
     * ���@@�J�㒊�I�iis�V�K���I() == false�j�̏ꍇ<BR>
     * �@@�|�ithis.IPO�����s.���I��� = �h�J�㒊�I�ρh�j���Z�b�g����B<BR>
     * @@param l_isNewLottery�@@ - �V�K���I���̔���<BR>
     * <BR>
     * true�F�@@�V�K���I<BR>
     * false�F�@@�J�㒊�I<BR>
     * @@roseuid 40F656CE03AB
     */
    public void setLotteryEnd(boolean l_isNewLottery) 
    {
        final String STR_METHOD_NAME = " setLotteryEnd(boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_isNewLottery)     //�V�K���I
        {
            log.debug("�V�K���I");
            this.ipoProductRow.setLotStatus(WEB3LotStatusDef.OPEN_LOTTERY_END);
        }
        else if (!l_isNewLottery)     //�J�㒊�I
        {
            log.debug("�J�㒊�I");
            this.ipoProductRow.setLotStatus(WEB3LotStatusDef.ADVANCED_LOTTERY_END);
        }
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (�폜)
     * �����ɍ폜��Ԃ��Z�b�g����B<BR>
     * <BR>
     * �@@this.IPO�����s.�폜�t���O = BooleanEnum.TRUE
     * @@roseuid 40C7F009005D
     */
    public void delete() 
    {
        final String STR_METHOD_NAME = " delete()";
        log.entering(STR_METHOD_NAME);
        
        this.ipoProductRow.setDeleteFlag(BooleanEnum.TRUE);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�X�P�W���[��)
     * IPO�X�P�W���[���������������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�u�b�N�r���f�B���O�J�n�����̃`�F�b�N�@@<BR>
     * �@@�i�V�K�쐬���� != null�j�̏ꍇ�̂݃`�F�b�N�����{����B<BR>
     * �ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@�V�K�쐬���� <= this.IPO�����s.�u�b�N�r���f�B���O�J�n����<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00496<BR>
     * �@@<BR>
     * �Q�j�@@�u�b�N�r���f�B���O�J�n�����̃`�F�b�N�A<BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@this.IPO�����s.�������񎦓� <= this.IPO�����s.�u�b�N�r���f�B���O�J�n����<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00497<BR>
     * <BR>
     * �R�j�@@�u�b�N�r���f�B���O�I�������̃`�F�b�N<BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@this.IPO�����s.�u�b�N�r���f�B���O�J�n���� < this.IPO�����s.�u�b�N�r���f�B���O<BR>�I������<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00498<BR>
     * <BR>
     * �S�j�@@���J���i������̃`�F�b�N<BR>
     * �@@���J���i����������͂���Ă���ꍇ<BR>
     * �ithis.IPO�����s.���J���i����� != null�j<BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@this.IPO�����s.�u�b�N�r���f�B���O�I������ �̓��t���� <=
     * <BR>
     * this.IPO�����s.���J���i�����<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00499<BR>
     * <BR>
     * �T�j�@@���I���̃`�F�b�N<BR>
     * �@@���I�������͂���Ă���ꍇ<BR>
     * �ithis.IPO�����s.���I�� != null�j�̂݁A�`�F�b�N���s���B<BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@this.IPO�����s.���J���i����� <= this.IPO�����s.���I��<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00500<BR>
     * <BR>
     * �U�j�@@�w���\���J�n���i�ژ_�����L�ځj�̃`�F�b�N<BR>
     * �@@�w���\���J�n���i�ژ_�����L�ځj�����͂���Ă���ꍇ<BR>
     * �ithis.IPO�����s.�w���\���J�n���i�ژ_�����L�ځj != null�j
     * <BR>�̂݁A�`�F�b�N���s���B<BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@this.IPO�����s.���I�� <= this.IPO�����s.�w���\���J�n��<BR>
     * �i�ژ_�����L�ځj<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00501<BR>
     * <BR>
     * �V�j�@@�w���\���J�n�����i���Аݒ�j�̃`�F�b�N<BR>
     * �@@�w���\���J�n�����i���Аݒ�j�����͂���Ă���ꍇ�A<BR>
     * �ithis.IPO�����s.�w���\���J�n�����i���Аݒ�j != null�j<BR>
     * �̂݁A�`�F�b�N���s���B<BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@this.IPO�����s.�u�b�N�r���f�B���O�I������ < this.IPO�����s.�w���\��<BR>
     * �J�n�����i���Аݒ�j &&<BR>
     * �@@this.IPO�����s.�w���\���J�n���i�ژ_�����L�ځj <= <BR>
     * this.IPO�����s.�w���\���J�n�����i���Аݒ�j �̓��t����<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00502<BR>
     * <BR>
     * 
     * �W�j�@@�w���\���I�������i���Аݒ�j�̃`�F�b�N<BR>
     * �@@�w���\���I�������i���Аݒ�j�����͂���Ă���ꍇ<BR>
     * �ithis.IPO�����s.�w���\���I�������i���Аݒ�j != null�j<BR>
     * �̂݁A�`�F�b�N���s���B<BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@this.IPO�����s.�w���\���J�n�����i���Аݒ�j < this.IPO�����s.�w���\��<BR>
     * �I�������i���Аݒ�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00503<BR>
     * <BR>
     * �X�j�@@�w���\���I�����i�ژ_�����L�ځj�̃`�F�b�N<BR>
     * �@@�w���\���I�����i�ژ_�����L�ځj�����͂���Ă���ꍇ<BR>
     * �ithis.IPO�����s.�w���\���I�����i�ژ_�����L�ځj != null�j<BR>
     * �̂݁A�`�F�b�N���s���B<BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@�Ethis.IPO�����s.�w���\���I�������i���Аݒ�j<BR>
     * �̓��t�����iYYYYMMDD�j <= this.IPO�����s.�w���\���I����<BR>
     * �i�ژ_�����L�ځj <BR>
     * <BR>
     * �@@�Ethis.IPO�����s.�w���\���I�����i�ژ_�����L�ځj ���u��c�Ɠ��v<BR>
     * �łȂ����ƁB<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00504<BR>
     * <BR>
     * �P�O�j�@@���J���̃`�F�b�N<BR>
     * �@@���J�������͂���Ă���ꍇ�ithis.IPO�����s.���J�� != null�j<BR>
     * �̂݁A�`�F�b�N���s���B<BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@this.IPO�����s.�w���\���I�����i�ژ_�����L�ځj < this.IPO�����s.���J��<BR>
     * <BR>
     * �� �w�w�����idatetime�j�̍��ڂƁA�w�w���idate�j�̍��ڂ́Adatetime����<BR>
     * �̓��t�����iYYYYMMDD�j�݂̂��r����B<BR>
     * �@@�@@�i���Ԃ͍l�����Ȃ��j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00505<BR>
     * <BR>
     * �P�P�j�@@����X�P�W���[���̃`�F�b�N<BR>
     * �@@����̏ꍇ�ɓo�^�����������ڂɖ������Ȃ������`�F�b�N����B<BR>
     * �@@�A���Anull���Z�b�g����Ă���ꍇ�A���̍��ڂ̓`�F�b�N�ΏۂƂ��Ȃ��B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@this.IPO�����s.���I������ <BR>
     * �@@�@@<= this.IPO�����s.�w���\���J�n�������i�ژ_�����L�ځj<BR>
     * �@@�@@<= this.IPO�����s.�w���\���J�n�������i���Аݒ�j<BR>
     * �@@�@@<= this.IPO�����s.�w���\���I���������i���Аݒ�j<BR>
     * �@@�@@<= this.IPO�����s.�w���\���I���������i�ژ_�����L�ځj<BR>
     * �@@�@@< ���J������<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00596<BR> 
     * <BR>
     * @@param l_tsCreatedTimestamp - �V�K�쐬����
     * @@roseuid 40BDBF0B006C
     */
    public void validateSchedule(Timestamp l_tsCreatedTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSchedule(Timestamp)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�u�b�N�r���f�B���O�J�n�����̃`�F�b�N�@@ 
        if (l_tsCreatedTimestamp != null && 
            WEB3DateUtility.compareToMinute(l_tsCreatedTimestamp, this.ipoProductRow.getBookbuildingStartDatetime()) > 0)    
        {
            log.debug("�u�b�N�r���f�B���O�J�n�����̃`�F�b�N�@@");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00496,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //�u�b�N�r���f�B���O�J�n�����̃`�F�b�N�A
        if (WEB3DateUtility.compareToDay(ipoProductRow.getProvisionalValueOpenDate(), ipoProductRow.getBookbuildingStartDatetime()) > 0)
        {
            log.debug("�u�b�N�r���f�B���O�J�n�����̃`�F�b�N�A");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00497,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //�R�j�@@�u�b�N�r���f�B���O�I�������̃`�F�b�N
		if (WEB3DateUtility.compareToMinute(ipoProductRow.getBookbuildingStartDatetime(), ipoProductRow.getBookbuildingEndDatetime()) >= 0)
		{
            log.debug("�u�b�N�r���f�B���O�I�������̃`�F�b�N");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00498,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //�S�j�@@���J���i������̃`�F�b�N
        String l_strPublicPriceSettleDate = WEB3DateUtility.formatDate(this.ipoProductRow.getPublicPriceSettleDate(),"yyyyMMdd");
        String l_strBookbuildingEndDate = WEB3DateUtility.formatDate(this.ipoProductRow.getBookbuildingEndDatetime(),"yyyyMMdd");
        if (this.ipoProductRow.getPublicPriceSettleDate() != null && 
            l_strBookbuildingEndDate.compareTo(l_strPublicPriceSettleDate) > 0)
        {
            log.debug("���J���i������̃`�F�b�N");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00499,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //�T�j�@@���I���̃`�F�b�N
        if (this.ipoProductRow.getLotDate() != null && 
            WEB3DateUtility.compareToDay(this.ipoProductRow.getPublicPriceSettleDate(), this.ipoProductRow.getLotDate()) > 0)
        {
            log.debug("���I���̃`�F�b�N");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00500,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //�U�j�@@�w���\���J�n���i�ژ_�����L�ځj�̃`�F�b�N
        if (this.ipoProductRow.getOfferStartDateProspec() != null && 
            WEB3DateUtility.compareToDay(this.ipoProductRow.getLotDate(), this.ipoProductRow.getOfferStartDateProspec()) > 0)
        {
            log.debug("�w���\���J�n���i�ژ_�����L�ځj�̃`�F�b�N");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00501,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //�V�j�@@�w���\���J�n�����i���Аݒ�j�̃`�F�b�N
        if (this.ipoProductRow.getOfferStartDatetime() != null)
        { 
			if (WEB3DateUtility.compareToMinute(ipoProductRow.getBookbuildingEndDatetime(), ipoProductRow.getOfferStartDatetime()) >= 0 ||
				 WEB3DateUtility.compareToDay(ipoProductRow.getOfferStartDateProspec(), ipoProductRow.getOfferStartDatetime()) > 0) 
            {
                log.debug("�w���\���J�n�����i���Аݒ�j�̃`�F�b�N");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00502,
                    getClass().getName() + STR_METHOD_NAME);                
            }
        }
        
        //�W�j�@@�w���\���I�������i���Аݒ�j�̃`�F�b�N 
		if (this.ipoProductRow.getOfferEndDatetime() != null && 
					WEB3DateUtility.compareToMinute(ipoProductRow.getOfferStartDatetime(), ipoProductRow.getOfferEndDatetime()) >= 0)   
        {
            log.debug("�w���\���I�������i���Аݒ�j�̃`�F�b�N");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00503,
                getClass().getName() + STR_METHOD_NAME);                
        }
        
        //�X�j�@@�w���\���I�����i�ژ_�����L�ځj�̃`�F�b�N
        String l_strOfferEndDate = WEB3DateUtility.formatDate(this.ipoProductRow.getOfferEndDatetime(),"yyyyMMdd");    
        String l_strOfferEndDateProspec = WEB3DateUtility.formatDate(this.ipoProductRow.getOfferEndDateProspec(),"yyyyMMdd");
        Timestamp l_tsOfferEndDateProspec = this.ipoProductRow.getOfferEndDateProspec();

        if (this.ipoProductRow.getOfferEndDateProspec() != null && 
            (l_strOfferEndDate.compareTo(l_strOfferEndDateProspec) > 0 || 
                WEB3BizDateTypeDef.NO_BIZ_DATE.equals(WEB3GentradeTradingTimeManagement.getBizDateType(l_tsOfferEndDateProspec))))
        {
            log.debug("�w���\���I�����i�ژ_�����L�ځj�̃`�F�b�N");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00504,
                getClass().getName() + STR_METHOD_NAME);                
        }  
        
        //�P�O�j�@@���J���̃`�F�b�N
        if (this.ipoProductRow.getPublicOfferingDate() != null && 
            WEB3DateUtility.compareToDay(ipoProductRow.getOfferEndDateProspec(), ipoProductRow.getPublicOfferingDate()) >= 0)
        {
            log.debug("���J���̃`�F�b�N");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00505,
                getClass().getName() + STR_METHOD_NAME);                
        }   
        
        //�P�P�j�@@����X�P�W���[���̃`�F�b�N
        ArrayList l_arrayDateCount = new ArrayList();
        if (!this.ipoProductRow.getLotDateCountIsNull())                             //���I������
        {
            l_arrayDateCount.add(this.ipoProductRow.lot_date_count);           
        }
        
        if (!this.ipoProductRow.getOfferStartDateCountProspecIsNull())             //�w���\���J�n�������i�ژ_�����L�ځj
        {
            l_arrayDateCount.add(this.ipoProductRow.offer_start_date_count_prospec);
        }
        
        if (!this.ipoProductRow.getOfferStartDateCountIsNull())                      //�w���\���J�n�������i���Аݒ�j
        {
            l_arrayDateCount.add(this.ipoProductRow.offer_start_date_count);
        } 
               
        if (!this.ipoProductRow.getOfferEndDateCountIsNull())                        //�w���\���I���������i���Аݒ�j
        {
            l_arrayDateCount.add(this.ipoProductRow.offer_end_date_count);
        }
        
        if (!this.ipoProductRow.getOfferEndDateCountProspecIsNull())                //�w���\���I���������i�ژ_�����L�ځj
        {
            l_arrayDateCount.add(this.ipoProductRow.offer_end_date_count_prospec);
        }

        boolean l_blnCheck = true;
        if (l_arrayDateCount.size() > 0)
        {
            Integer[] l_IntDateCount = (Integer[]) l_arrayDateCount.toArray(new Integer[0]);
            for (int i = 0; i < l_IntDateCount.length; i++)
            {
                for(int j = i + 1; j < l_IntDateCount.length; j++)
                {
                    if(l_IntDateCount[i].compareTo(l_IntDateCount[j]) > 0)
                    {
                        l_blnCheck = false;
                        break;
                    }
                }
                
                if (!l_blnCheck)
                {
                    break;                 
                }
                
                if (!this.ipoProductRow.getPublicOfferingDateCountIsNull()
                    && l_IntDateCount[i].compareTo(this.ipoProductRow.public_offering_date_count) >= 0 )
                {
                    l_blnCheck = false;
                    break;
                }                
            }   
        }
        
        if (!l_blnCheck)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00596,
                getClass().getName() + STR_METHOD_NAME); 
        }
        
        log.exiting(STR_METHOD_NAME);     
    } 
    
    /**
     * (validate��������)<BR>
     * ���������^������������ɖ��������݂��邩���`�F�b�N����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iIPO���i�jvalidate���������v�Q�ƁB <BR>
     */
    public void validateProduct() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateProduct()";
        log.entering(STR_METHOD_NAME);
        
        //1.1.is�f�B�X�J�E���g����()
        boolean l_blisDH = this.isDiscountHandling();
        
        //1.2.(*)�f�B�X�J�E���g�����łȂ��ꍇ(is�f�B�X�J�E���g����()==false),�����I��
        if(!l_blisDH)
        {
            return;
        }
        
        //1.3.get�s��R�[�h()
        String l_strMarketCode = this.getMarketCode();
        
        //1.4.reset�s��R�[�h(�s��R�[�h)
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        
        //1.5.reset��t���ԋ敪(��t���ԋ敪(="�����E�M�p"))
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        
        //1.6.getTradingModule(ProductTypeEnum."����")
        ////FinApp�̎擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
              
        //1.7.getProductManager
        l_tradingModule.overrideProductManager(new WEB3EquityProductManager());
        WEB3EquityProductManager l_eqProdMgr = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        
        //1.8.getInstitution()
        Institution l_instInstitution = this.getInstitution();
        
        
        //1.9.get���������R�[�h
        String l_strProdCode = this.getIpoProductCode();
        
        
        //1.10.getTradedProduct(�،����,�����R�[�h,�s��R�[�h)
        log.debug("�،���ЃR�[�h = "+l_instInstitution.getInstitutionCode());
        log.debug("���������R�[�h = "+l_strProdCode);
        log.debug("�s��R�[�h = "+l_strMarketCode);
        try
        {

            l_eqProdMgr.getTradedProduct(l_instInstitution,
                                         l_strProdCode,
                                         l_strMarketCode);
        }
        catch(NotFoundException nf_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                            getClass().getName() + STR_METHOD_NAME); 
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (createForUpdateParams ())<BR>
     * this.IPO�����s���R�s�[���āA<BR>
     * �������e�̕ʃC���X�^���X���쐬����iclone�j�B<BR>
     * <BR>
     * �쐬�����R�s�[�����g��this.IPO�����s�ɃZ�b�g����B<BR>
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        IpoProductParams l_ipoProductParams = new IpoProductParams(this.ipoProductRow);
        this.ipoProductRow = l_ipoProductParams;
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�ژ_�����{���敪())<BR>
     * this.IPO�����s.�ژ_�����{���敪��ԋp����B<BR>
     * <BR>
     * @@return String
     */
    public String getDocReadingDiv()
    {
        final String STR_METHOD_NAME = "getDocReadingDiv()";
        log.entering(STR_METHOD_NAME);

        String l_strDocReadingDiv = ipoProductRow.getDocReadingDiv();

        log.exiting(STR_METHOD_NAME);
        return l_strDocReadingDiv;
    }

}
@
