head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoTradedProductImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP��������N���X(WEB3IfoTradedProductImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ����(���u) �V�K�쐬
Revesion History : 2004/06/11 ����(���u) ����(�،���ЃR�[�h�A�����R�[�h�A�s��R�[�h)��ǉ�
Revesion History : 2007/02/09 ��іQ(���u) ���f�� No.630                   
Revesion History : 2007/06/08 �����F(���u) ���f�� No.649
*/
package webbroker3.ifo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlDbUtils;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoTradedProductImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3QuoteFromDivDef;
import webbroker3.common.define.WEB3RegistDivisionDef;
import webbroker3.common.define.WEB3SkipMarketCodeDef;
import webbroker3.common.define.WEB3SkipProductCodeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.data.IfoOrderCarryoverSkipProdRow;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �敨OP��������I�u�W�F�N�g<BR>
 * �敨OP��������I�u�W�F�N�g�N���X<BR>
 *�iDB���C�A�E�g �u�敨OP��������e�[�u��.xls�v�Q�Ɓj<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3IfoTradedProductImpl extends IfoTradedProductImpl 
{
    /**
     * �敨OP��������s�I�u�W�F�N�g<BR>
     * �i��������DAO�N���X�j<BR>
     */
    private IfoTradedProductRow futuresOptionTradedProductRow;

    /**
     * (�،���ЃR�[�h)<BR>
     */
    private String institutionCode;

    /**
     * (�����R�[�h)<BR>
     */
    private String productCode;

    /**
     * (�s��R�[�h)<BR>
     */
    private String marketCode;
    
    /**
     * Logger
     */
    public static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3IfoTradedProductImpl.class);
        
    /**
     * �R���X�g���N�^�B<BR>
     * �����̎������ID�Ɉ�v����敨OP��������I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �P�j�@@��������擾<BR>
     * �@@�������ID�ɂĐ敨OP����������擾����B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR>
     * �@@�擾���ʂ̍s�I�u�W�F�N�g�i�敨OP�������Row�j��<BR>
     *   �،���ЃR�[�h�A�����R�[�h�A�s��R�[�h���Z�b�g����<BR>
     *
     * @@param l_lngTradedProductID - �������ID
     * @@return webbroker3.ifo.WEB3IfoTradedProductImpl
     * @@roseuid 405E7AF503D6
     */

    public WEB3IfoTradedProductImpl(long l_lngTradedProductID) 
        throws DataQueryException, DataNetworkException, DataFindException
    {
        super(GtlDbUtils.getTradedProductRow(l_lngTradedProductID));
        this.futuresOptionTradedProductRow = (IfoTradedProductRow)super.getDataSourceObject();
		
		//�،���ЃR�[�h�A�����R�[�h�A�s��R�[�h���Z�b�g����
		this.institutionCode = this.futuresOptionTradedProductRow.getInstitutionCode();
		log.debug("institutionCode = " + institutionCode);  

		WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(futuresOptionTradedProductRow.getProductId());
		this.productCode = l_ifoProduct.getProductCode();
		log.debug("productCode = " + productCode);

		if (l_ifoProduct.getPrimaryMarket() != null)
		{
			this.marketCode = l_ifoProduct.getPrimaryMarket().getMarketCode();
		}
		log.debug("marketCode = " + marketCode);
    }
    
    public WEB3IfoTradedProductImpl(TradedProductRow l_row) 
        throws DataQueryException, DataNetworkException, DataFindException
    {        
        super(l_row);      
        this.futuresOptionTradedProductRow = (IfoTradedProductRow)super.getDataSourceObject();
        
        //�،���ЃR�[�h�A�����R�[�h�A�s��R�[�h���Z�b�g����
        this.institutionCode = this.futuresOptionTradedProductRow.getInstitutionCode();
        log.debug("institutionCode = " + institutionCode);  

		WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(l_row.getProductId());
        this.productCode = l_ifoProduct.getProductCode();
        log.debug("productCode = " + productCode);

        if (l_ifoProduct.getPrimaryMarket() != null)
        {
            this.marketCode = l_ifoProduct.getPrimaryMarket().getMarketCode();
        }
        log.debug("marketCode = " + marketCode);  
    }    
    
    /**
     * �igetDeliveryDateShiftDays�̎����j<BR>
     * <BR>
     * �P��ԋp����B<BR>
     * �i��n�����������̉�����ɂȂ邩�̓����j<BR>
     * @@return int
     * @@roseuid 405ABF0C0306
     */
    protected int getDeliveryDateShiftDays() 
    {
        return 1;
    }
    
    /**
     * this.�敨OP�������Row��ԋp����B<BR>
     * @@return Object
     * @@roseuid 405E7AF503B6
     */
    public Object getDataSourceObject() 
    {
        return this.futuresOptionTradedProductRow;
    }
    
    /**
     * (get���Z�l)<BR>
     * <BR>
     * �Y�������̐��Z�l���擾����B<BR>
     * <BR>
     * �{�I�u�W�F�N�g���ێ�����s�I�u�W�F�N�g�̐��Z�l��ԋp����B<BR>
     * @@return double
     * @@roseuid 40612A87027D
     */
    public double getLiquidationPrice() 
    {
        return this.futuresOptionTradedProductRow.getLiquidationPrice();
    }
    
    /**
     * (is����K��)<BR>
     * <BR>
     * �iisTradingSuspended�̃I�[�o�[���[�h�j<BR>
     * �@@�{�I�u�W�F�N�g���ێ�����s�I�u�W�F�N�g�̍��ڒl�ɂ�<BR>
     * ����K���𔻒�A���茋�ʂ�ԋp����B<BR>
     * <BR>
     * �P�j �e����̋K������<BR>
     * ���@@�V�K�����iis����==true && is�V�K��==true�j�̏ꍇ<BR>
     * �@@�敨OP�������.�V�K������~ == �h��~���h�̏ꍇtrue��Ԃ��B<BR>
     * <BR>
     * ���@@�V�K�����iis����==false && is�V�K��==true�j�̏ꍇ<BR>
     * �@@�敨OP�������.�V�K������~ == �h��~���h�̏ꍇtrue��Ԃ��B<BR>
     * <BR>
     * ���@@�������ԍρiis����==false && is�V�K��==false�j�̏ꍇ<BR>
     * �@@�敨OP�������.�������ԍϒ�~ == �h��~���h�̏ꍇtrue��Ԃ��B<BR>
     * <BR>
     * ���@@�������ԍρiis����==true && is�V�K��==false�j�̏ꍇ<BR>
     * �@@�敨OP�������.�������ԍϒ�~ == �h��~���h�̏ꍇtrue��Ԃ��B<BR>
     * <BR>
     * �Q�j�@@������~�i�S����j�̔���<BR>
     * �@@this.is����K��()�i�����Ȃ��̃I�[�o�[���[�h���\�b�h�j�̖߂�l��ԋp����B<BR>
     * @@param l_blnLongOrder - �iisLongOrder�j
     * �������ǂ����̔���B
     * �����̏ꍇtrue�A�����̏ꍇfalse�B
     * @@param l_blnIsOpenContract - (is�V�K��)<BR>
     * <BR>
     * �V�K��������ǂ����̔���B<BR>
     * �V�K���̏ꍇtrue�A�ԍς̏ꍇfalse�B<BR>
     * 
     * @@return boolean
     * @@roseuid 40643AC3036C
     */
    public boolean isTradingSuspended(
        boolean l_blnLongOrder, 
        boolean l_blnIsOpenContract) 
    {   
        //�P�j �e����̋K������         
        if (l_blnLongOrder && l_blnIsOpenContract)
        {   
            log.debug("is���� == true ;is�V�K�� == true");
            //�敨OP�������.�V�K������~ == �h��~���h�̏ꍇtrue��Ԃ��B
            if (BooleanEnum.TRUE.equals(this.futuresOptionTradedProductRow.getBuyToOpenStopFlag()))
            {
                return true;
            }
        }
        else if ((!l_blnLongOrder) && l_blnIsOpenContract)        
        {   
            log.debug("is���� == false ;is�V�K�� == true");
            //�敨OP�������.�V�K������~ == �h��~���h�̏ꍇtrue��Ԃ��B
            if (BooleanEnum.TRUE.equals(this.futuresOptionTradedProductRow.getSellToOpenStopFlag()))
            {
                return true;
            }
            
        }
        else if ((!l_blnLongOrder) && (!l_blnIsOpenContract))
        {   //�敨OP�������.�������ԍϒ�~ == �h��~���h�̏ꍇtrue��Ԃ�
            if (BooleanEnum.TRUE.equals(this.futuresOptionTradedProductRow.getBuyToCloseStopFlag()))
            {
                return true;
            }            
        }
        else 
        {   //�敨OP�������.�������ԍϒ�~ == �h��~���h�̏ꍇtrue��Ԃ�
            if (BooleanEnum.TRUE.equals(this.futuresOptionTradedProductRow.getSellToCloseStopFlag()))
            {
                return true;
            }            
        }
        //�Q�j�@@������~�i�S����j�̔���   
        return this.isTradingSuspended();
    }
    
    /**
     * (get�����Y����)<BR>
     * <BR>
     * �Y�������̌����Y�������擾����B<BR>
     * <BR>
     * �{�I�u�W�F�N�g���ێ�����s�I�u�W�F�N�g�̌����Y������ԋp����B<BR>
     * @@return double
     * @@roseuid 4067BA340104
     */
    public double getUnderlyingCurrentPrice() 
    {
        return this.futuresOptionTradedProductRow.getTargetSpotPrice();
    }
    
    /**
     * (is����K��)<BR>
     * <BR>
     * �iisTradingSuspended�j<BR>
     * �@@�{�I�u�W�F�N�g���ێ�����s�I�u�W�F�N�g�̍��ڒl�ɂ�<BR>
     * ����K���𔻒�A���茋�ʂ�ԋp����B<BR>
     * <BR>
     * �P�j ������~�i���ЋK���^������K���j�̔���<BR>
     * �@@�敨OP�������.������~ == �h��~�łȂ��h�̏ꍇ�Afalse��Ԃ��B<BR>
     * �@@�ȊO�Atrue��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 4076501403C7
     */
    public boolean isTradingSuspended() 
    {   
        log.debug("getTradeStopFlag() =" + this.futuresOptionTradedProductRow.getTradeStopFlag().toString());
        if (BooleanEnum.FALSE.equals(this.futuresOptionTradedProductRow.getTradeStopFlag()))
        {
            return  false;            
        }
        else
        {
            return true;
        }        
    }
    
    /**
     * (is���)<BR>
     * <BR>
     * �������Ώێs��ɏ�ꒆ�ł��邩�𔻒肷��B<BR>
     * <BR>
     * �@@�{�I�u�W�F�N�g���ێ�����s�I�u�W�F�N�g�̏��t���O���h���h�ł����true�A�ȊO��false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 407652560399
     */
    public boolean isListed() 
    {
        log.debug("getListFlag() =" + this.futuresOptionTradedProductRow.getListFlag().toString());
        if (BooleanEnum.TRUE.equals(this.futuresOptionTradedProductRow.getListFlag()))
        {
            return true;
        }
        else
        {
            return false;
        }
     
    }
    
    /**
     * (is�l���`�F�b�N���{)<BR>
     * <BR>
     * �l���`�F�b�N���s�����𔻒肷��B<BR>
     * <BR>
     * �i�敨OP�������.�����Y���� == 0�@@�܂��́A<BR>
     * �敨OP�������.���Z�l == 0�j�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �ȊO�i�ǂ���̍��ڂ�0�łȂ��ꍇ�j�Atrue��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 407693430379
     */
    public boolean isPriceRangeChecked() 
    {
        log.debug("getUnderlyingCurrentPrice() = " + this.getUnderlyingCurrentPrice());
        log.debug("getLiquidationPrice() = " + this.getLiquidationPrice());
        if (this.getUnderlyingCurrentPrice() == 0 ||
                this.getLiquidationPrice() == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
     
    }
    
    /**
     * (is�J�z�X�L�b�v����)<BR>
     * <BR>
     * �����J�z�����̃X�L�b�v�Ώۖ����ł��邩�ǂ����𔻒肷��B<BR>
     * �J�z�X�L�b�v�����ł���ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�敨OP�����J�z�X�L�b�v�����e�[�u�����A�ȉ��̏����Ńf�[�^���擾����B<BR>
     * <BR>
     * �@@�@@�،���ЃR�[�h��this.�،���ЃR�[�h<BR>
     * �@@�@@���@@�����R�[�h��(ALL(�S����) or this.�����R�[�h)<BR>
     * �@@�@@���@@�s��R�[�h��(F(�S�s��) or this.�s��R�[�h)<BR>
     * �@@�@@���@@�o�^�敪��1(�����J�z�X�L�b�v)<BR>
     * <BR>
     * �Q�j�@@�Y������f�[�^�����݂���ꍇ��true���A�Y���f�[�^�Ȃ��̏ꍇ��false��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 407CD5130242
     */
    public boolean isCarryOverSkipProduct()         
        throws WEB3BaseException
    {
        String METHOD_NAME = "isCarryOverSkipProduct()";
        log.entering(METHOD_NAME);        
        
        //�Ԋ҂̒l�̐ݒ�
        boolean l_blnReturn = false;
        List l_lisRows = null;
        
        try
        {
            String l_strWhere = " institution_code = ? and (product_code = ? or product_code = ?)"
            + " and (market_code = ? or market_code = ?) and regist_division = ? ";
            
            Object l_bindVars[] = 
                {
                    this.institutionCode,
                    WEB3SkipProductCodeDef.ALL,
                    this.productCode,
                    WEB3SkipMarketCodeDef.FULL_MARKET,
                    this.marketCode,
                    WEB3RegistDivisionDef.ORDER_TRANSFER_STOP
                };
            l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    IfoOrderCarryoverSkipProdRow.TYPE,
                    l_strWhere,
                    null,
                    null,
                    l_bindVars);                
          } 
          catch(DataException l_de)
          {
              log.error(l_de.getMessage(), l_de);  
              log.exiting(METHOD_NAME);
              throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + METHOD_NAME,
                            l_de.getMessage(),
                            l_de);
          }
          
          if (l_lisRows != null && l_lisRows.size() != 0)
          {
              //�Y������f�[�^�����݂���ꍇ��true��
              l_blnReturn = true;
          }
          
          log.exiting(METHOD_NAME);
          return l_blnReturn;
    }
    
    /**
     * (is�����ԓ�)<BR>
     * <BR>
     * ��������ꒆ�ł��邩�𔻒肷��B<BR>
     * <BR>
     * �P�j�@@�������擾<BR>
     * �@@������ԊǗ�.get������()�ɂĔ��������擾����B<BR>
     * <BR>
     * �Q�j�@@�����Ԓ�����<BR>
     * �@@�{�I�u�W�F�N�g���ێ�����s�I�u�W�F�N�g�̍��ڒl�Ɣ����������̒ʂ��r���A<BR>���茋�ʂ�ԋp����B<BR>
     * <BR>
     * ���i�敨OP�������.�����J�n�� <= ������ <= �敨OP�������.�����I�����j &&<BR>
     * �@@�i�敨OP�������.���i�o�^�j�� <= ������ < �敨OP�������.���i�o�^�j�p�~���j�̏ꍇ<BR>
     * �@@true��ԋp����B<BR>
     * <BR>
     * ����L�ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 409F7E5F02F8
     */
    public boolean isInListedTerm() 
        throws WEB3BaseException
    {             
        Date l_datOrder;        
        Date l_datStartTradingDate;
        Date l_datLastTradingDate;
        Date l_datListDate;
        Date l_datUnlistedDate;        
           
        l_datOrder              = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        l_datStartTradingDate   = this.futuresOptionTradedProductRow.getStartTradingDate();
        l_datLastTradingDate    = this.futuresOptionTradedProductRow.getLastTradingDate();
        l_datListDate           = this.futuresOptionTradedProductRow.getListDate();
        l_datUnlistedDate       = this.futuresOptionTradedProductRow.getUnlistedDate();               
      
        log.debug("������ = " + l_datOrder);
        log.debug("�����J�n�� = " + l_datStartTradingDate);
        log.debug("�����I���� = " + l_datLastTradingDate);
        log.debug("���(�o�^)�� = " + l_datListDate);
        log.debug("���(�o�^)�p�~�� = " + l_datUnlistedDate);
        
        if ((WEB3DateUtility.compareToDay(l_datOrder, l_datStartTradingDate) >= 0 
            && WEB3DateUtility.compareToDay(l_datOrder, l_datLastTradingDate) <= 0) 
            && WEB3DateUtility.compareToDay(l_datOrder, l_datListDate) >= 0 
            && WEB3DateUtility.compareToDay(l_datOrder, l_datUnlistedDate) < 0)
        {
            return true;
        }
        else
        {
            return false;
        }
        
     
    }
    
    /**
     * (get�������)<BR>
     * �������i�����擾�敪�A�����A�������\���ԁj���擾����B <BR>
     * ���������擾�ł��Ȃ������ꍇ�́Anull��Ԃ��B <BR>
     * <BR>
     * �P�j�߂�l�I�u�W�F�N�g����<BR>
     * �@@�敨OP�������C���X�^���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�敨OP�������.�s��R�[�h = this.getMarket().�s��R�[�h<BR>
     * <BR>
     * �Q�j�������擾�T�[�r�X���擾����B<BR>
     * �@@WEB3DefaultQuoteDataSupplierService.getIfoQuote()���\�b�h��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �@@[getQuote()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@��������F�@@this<BR>
     * �@@�@@���A���敪�F�@@<BR>
     * �@@�@@�@@[�p�����[�^.�⏕���� == null�̏ꍇ]<BR>
     * �@@�@@�@@�@@"���A��"���Z�b�g�B<BR>
     * �@@�@@�@@[��L�ȊO]<BR>
     * �@@�@@�@@�@@�@@�p�����[�^.�⏕����.getMainAccount()�ɂ��<BR>
     * �@@�@@�@@�@@�@@�ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�@@�A�ڋq�I�u�W�F�N�g.is���A���ڋq() == true�̏ꍇ�A"���A��"���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�ȊO�A"20���f�B���C"���Z�b�g�B<BR>
     * <BR>
     * �R�j�����擾<BR>
     * �@@�ȉ��̏����łQ�j�̖߂�l��莞���A�������\�������擾���A<BR>
     * �@@�������擾�ł���(�擾�������� > 0)���_�ŁA<BR>
     * �@@���������敨OP�������̃v���p�e�B�ɃZ�b�g����B<BR>
     * �@@�@@�@@���ݒl�A���ݒl����<BR>
     * �@@�@@�A���C�z�l�A���C�z�l����<BR>
     * �@@�@@�B���C�z�l�A���C�z�l����<BR>
     * �@@�@@�Cthis.��l(�I�l)�@@���C�̏ꍇ�A�������\������null�Ƃ���B�@@<BR>
     * <BR>
     * �@@�敨OP�������.���� = �擾��������<BR>
     * �@@�敨OP�������.�O���� = �������擾�T�[�r�X.getChange()<BR>
     * �@@�敨OP�������.�����擾���� = �擾�����������\����<BR>
     * �@@�敨OP�������.�����擾�敪 = "����"�@@���C�̏ꍇ�A"�O���I�l"���Z�b�g�B<BR>
     * <BR> 
     * �@@�������擾�ł��Ȃ������ꍇ�́Anull��ԋp���ďI������B<BR>
     * <BR>
     * �S�j�v���p�e�B�Z�b�g�����敨OP��������ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g<BR>
     * <BR>
     * �������擾�敪(���A�� or 20���f�B���C)�̔��ʂɎg�p�B<BR>
     * �@@��Ƀ��A���Ŏ擾����ꍇ�́Anull���Z�b�g�B<BR>
     * @@return webbroker3.ifo.WEB3IfoCurrentInfo
     * @@roseuid 41AC32D5038A
     */
    public WEB3IfoProductQuote getCurrentInfo(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCurrentInfo()";
        log.entering(STR_METHOD_NAME);
        try
        {

            //�敨OP�������C���X�^���X�𐶐���
            WEB3IfoProductQuote l_ProductQuote = new WEB3IfoProductQuote();
            //�敨OP�������.�s��R�[�h = this.getMarket().�s��R�[�h
            l_ProductQuote.setMarketCode(this.getMarket().getMarketCode());
            // �Q�j�������擾�T�[�r�X���擾����B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3QuoteDataSupplierService l_supplierService = (WEB3QuoteDataSupplierService)l_tradingModule.getQuoteDataSupplierService();
            RealType l_realType = null;
            //�p�����[�^.�⏕���� == null�̏ꍇ
            if (l_subAccount == null)
            {
                l_realType = RealType.REAL;
            }
            else
            {
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
                //�ڋq�I�u�W�F�N�g.is���A���ڋq() == true�̏ꍇ�A"���A��"���Z�b�g�B           
                if (l_mainAccount.isRealCustomer())
                {
                    l_realType = RealType.REAL;
                }
                //�ȊO�A"20���f�B���C"���Z�b�g�B
                else
                {
                    l_realType = RealType.DELAY;
                }
            }
        
            //�R�j�����擾
            WEB3IfoQuoteData l_IfoQuoteData = l_supplierService.getIfoQuote(this,l_realType);
            //�敨OP�������.�O���� = �������擾�T�[�r�X.getChange() 
            double l_dblChange = l_IfoQuoteData.getChange();
            log.debug("�敨OP�������.�O���� = " + l_dblChange);
            l_ProductQuote.setComparedPreviousDay(l_dblChange);
            //�@@���ݒl�A���ݒl����
            double l_dblcurrentPrice = l_IfoQuoteData.getCurrentPrice();
            if (Double.isNaN(l_dblcurrentPrice))
            {
                l_dblcurrentPrice = 0;
            }
            
            log.debug("�敨OP�������.���ݒl = " + l_dblcurrentPrice);
            if (l_dblcurrentPrice > 0)
            {
                l_ProductQuote.setCurrentPrice(l_dblcurrentPrice);
                //�敨OP�������.�����擾���� = �擾�����������\����
                l_ProductQuote.setCurrentPriceTime(l_IfoQuoteData.getCurrentPriceTime());
                //�敨OP�������.�����擾�敪 = "����"
                l_ProductQuote.setCurrentPriceDiv(WEB3QuoteFromDivDef.MARKET_PRICE);
                
                log.exiting(STR_METHOD_NAME);
                return l_ProductQuote;
            }
            //�A���C�z�l�A���C�z�l����
            double l_dblBidPrice = l_IfoQuoteData.getBidPrice();
            if (Double.isNaN(l_dblBidPrice))
            {
                l_dblBidPrice = 0;
            }
            
            log.debug("�敨OP�������.���C�z�l = " + l_dblBidPrice);
            if (l_dblBidPrice > 0)
            {
                l_ProductQuote.setCurrentPrice(l_dblBidPrice);
                //�敨OP�������.�����擾���� = �擾�����������\����
                l_ProductQuote.setCurrentPriceTime(l_IfoQuoteData.getBidPriceTime());
                //�敨OP�������.�����擾�敪 = "����"
                l_ProductQuote.setCurrentPriceDiv(WEB3QuoteFromDivDef.MARKET_PRICE);
                
                log.exiting(STR_METHOD_NAME);
                return l_ProductQuote;
            }
            //�B���C�z�l�A���C�z�l����
            double l_dblAskPrice = l_IfoQuoteData.getAskPrice();
            if (Double.isNaN(l_dblAskPrice))
            {
                l_dblAskPrice = 0;
            }
            
            log.debug("�敨OP�������.���C�z�l = " + l_dblAskPrice);
            if (l_dblAskPrice > 0)
            {
                l_ProductQuote.setCurrentPrice(l_dblAskPrice);
                //�敨OP�������.�����擾���� = �擾�����������\����
                l_ProductQuote.setCurrentPriceTime(l_IfoQuoteData.getAskPriceTime());
                //�敨OP�������.�����擾�敪 = "����"
                l_ProductQuote.setCurrentPriceDiv(WEB3QuoteFromDivDef.MARKET_PRICE);
                
                log.exiting(STR_METHOD_NAME);
                return l_ProductQuote;
            }
            //�Cthis.��l(�I�l)�@@���C�̏ꍇ�A�������\������null�Ƃ���B
            double l_dblClosingPrice = this.getLastClosingPrice();
            if (Double.isNaN(l_dblClosingPrice))
            {
                l_dblClosingPrice = 0;
            }
            
            log.debug("��l = " + l_dblClosingPrice);
            if (l_dblClosingPrice > 0)
            {
                l_ProductQuote.setCurrentPrice(l_dblClosingPrice);
                l_ProductQuote.setCurrentPriceTime(null);
                //�C�̏ꍇ�A"�O���I�l"���Z�b�g�B
                l_ProductQuote.setCurrentPriceDiv(WEB3QuoteFromDivDef.LAST_CLOSING_PRICE);
                
                log.exiting(STR_METHOD_NAME);
                return l_ProductQuote;
            }

        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        //�������擾�ł��Ȃ������ꍇ�́Anull��ԋp���ďI������B
        log.exiting(STR_METHOD_NAME);    
        return null;
    }

    /**
     * (get��n��)<BR>
     * �igetDailyDeliveryDate�̃I�[�o�[���C�h���\�b�h�j <BR>
     * ������ԑт�����������n����ԋp����B <BR>
     * <BR>
     * �P�j super.getDailyDeliveryDate�ɂĎ�n�����擾����B <BR>
     * <BR>
     * �Q�j ������ԑт𔻒肷��B <BR>
     * <BR>
     * �@@�@@�m������ԊǗ�.is�[�ꎞ�ԑ�() == true�̏ꍇ�n <BR>
     * �@@�@@�@@�@@�c�Ɠ��v�Z.calc�c�Ɠ�()(*)�ɂĎZ�o���ꂽ��n����ԋp����B <BR>
     * <BR>
     * �@@�@@�m������ԊǗ�.is�[�ꎞ�ԑ�() == false�̏ꍇ�n <BR>
     * �@@�@@�@@�@@�P�j�Ŏ擾������n����ԋp����B <BR>
     * <BR>
     * <BR>
     * �@@�@@(*) �c�Ɠ��v�Z.calc�c�Ɠ�()�ւ̈��� <BR>
     * �@@�@@�@@�@@�@@�@@��� �F 1�j�Ŏ擾������n�� <BR>
     * �@@�@@�@@�@@�@@�@@���Z�^���Z���� �F 1�i���c�Ɠ��j<BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getDailyDeliveryDate()
    {
        final String STR_METHOD_NAME = "getDailyDeliveryDate()";
        log.entering(STR_METHOD_NAME);
        //�P�j super.getDailyDeliveryDate�ɂĎ�n�����擾����B
        Date l_datDeliveryDate = super.getDailyDeliveryDate();

        //�Q�j ������ԑт𔻒肷��B
        //�@@�@@�m������ԊǗ�.is�[�ꎞ�ԑ�() == true�̏ꍇ�n
        //�@@�@@�@@�@@�c�Ɠ��v�Z.calc�c�Ɠ�()(*)�ɂĎZ�o���ꂽ��n����ԋp����B
        //�@@�@@(*) �c�Ɠ��v�Z.calc�c�Ɠ�()�ւ̈���
        //�@@�@@�@@�@@�@@�@@��� �F 1�j�Ŏ擾������n��
        //�@@�@@�@@�@@�@@�@@���Z�^���Z���� �F 1�i���c�Ɠ��j
        Date l_datRetDeliveryDate = null;
        try
        {
            if (WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone())
            {
                WEB3GentradeBizDate l_bizDate =
                    new WEB3GentradeBizDate(new Timestamp(l_datDeliveryDate.getTime()));
                l_datRetDeliveryDate = l_bizDate.roll(1);
            }
            //�@@�@@�m������ԊǗ�.is�[�ꎞ�ԑ�() == false�̏ꍇ�n
            //�@@�@@�@@�@@�P�j�Ŏ擾������n����ԋp����B
            else
            {
                l_datRetDeliveryDate = l_datDeliveryDate;
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_datRetDeliveryDate;
    }
}
@
