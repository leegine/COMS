head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoProductInfoServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�������쐬�T�[�r�XImpl(WEB3IpoProductInfoServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/23 ���o�� �V�K�쐬
                 : 2006/11/22 �����q (���u) �d�l�ύXNo.167�Ή�
                 : 2006/11/22 �����q (���u) �c�a�X�V�d�l No.27
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DocReadingDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3UndecideDecideDivDef;
import webbroker3.ipo.message.WEB3IPOProductInfo;
import webbroker3.ipo.message.WEB3IPOTermUnit;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPO�������쐬�T�[�r�X�����N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3IpoProductInfoServiceImpl implements WEB3IpoProductInfoService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoProductInfoServiceImpl.class);
    
    /**
     * @@roseuid 4112F19100A2
     */
    public WEB3IpoProductInfoServiceImpl() 
    {
     
    }
    
    /**
     * (createIPO�������)<BR>
     * ������IPO����ID�ɊY������IPO�����̓��e�ŁA<BR>
     * IPO������񃁃b�Z�[�W�f�[�^�C���X�^���X���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i���ʁjcreateIPO�������v�Q�ƁB<BR>
     * @@param l_lngProductId - IPO�����h�c
     * @@return webbroker3.ipo.message.WEB3IpoProductInfo
     * @@throws WEB3BaseException
     * @@roseuid 40C66A8802EA
     */
    public WEB3IPOProductInfo createIpoProductInfo(long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createIpoProductInfo(long)";
        log.entering(STR_METHOD_NAME);
        
        //1.IPO����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();

        try
        {
            WEB3IpoProductImpl l_ipoProductImpl = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngProductId);
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_ipoProductImpl.getDataSourceObject();
            IpoProductParams l_ipoProductParams = new IpoProductParams(l_ipoProductRow);
            
            //2.is�X�P�W���[������
            boolean l_blnScheduleDecision = l_ipoProductImpl.isScheduleDecision();
            
            //3.IPO���Ԏw��
            WEB3IPOTermUnit l_ipoTermUnitPublicDate = new WEB3IPOTermUnit();
            
            //4.���J���C���X�^���X �v���p�e�B�Z�b�g
            l_ipoTermUnitPublicDate.startDate = l_ipoProductParams.getPublicOfferingDate();
            if (l_ipoProductParams.getPublicOfferingDateCountIsNull())
            {
                l_ipoTermUnitPublicDate.bizDateUpper = null;
            }
            else
            {
                l_ipoTermUnitPublicDate.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicOfferingDateCount());
            }
            
            //5.IPO���Ԏw��
            WEB3IPOTermUnit l_ipoTermUnitPublicPriceSettleDate = new WEB3IPOTermUnit();
            
            //6.���J���i������C���X�^���X �v���p�e�B�Z�b�g
            l_ipoTermUnitPublicPriceSettleDate.startDate = l_ipoProductParams.getPublicPriceSettleDate();
            // 200411/16 U00422 ���J���i�����.�c�Ɠ�����̃Z�b�g�s�v�̂��ߑΉ��@@���@@SRA  START
//            if (l_ipoProductParams.getPublicOfferingDateCountIsNull())
//            {
//                l_ipoTermUnitPublicPriceSettleDate.bizDateUpper = null;
//            }
//            else
//            {
//                l_ipoTermUnitPublicPriceSettleDate.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicOfferingDateCount());
//            }
			// 200411/16 U00422 ���J���i�����.�c�Ɠ�����̃Z�b�g�s�v�̂��ߑΉ��@@���@@SRA  END
            
            //7.IPO���Ԏw��
            WEB3IPOTermUnit l_ipoTermUnitLotDate = new WEB3IPOTermUnit();
            
            //8.���I���C���X�^���X �v���p�e�B�Z�b�g
            l_ipoTermUnitLotDate.startDate = l_ipoProductParams.getLotDate();
            if (l_ipoProductParams.getLotDateCountIsNull())
            {
                l_ipoTermUnitLotDate.bizDateUpper = null;
            }
            else
            {
                l_ipoTermUnitLotDate.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getLotDateCount());
            }            
            
            //9.IPO���Ԏw��( )
            WEB3IPOTermUnit l_ipoTermUnitOfferDate = new WEB3IPOTermUnit();
            
            //10.�w���\�����ԁi�ژ_�����j�C���X�^���X �v���p�e�B�Z�b�g
            l_ipoTermUnitOfferDate.startDate = l_ipoProductParams.getOfferStartDateProspec();
            l_ipoTermUnitOfferDate.endDate = l_ipoProductParams.getOfferEndDateProspec();
			//// 2004/10/29 U00341�̑Ή��@@�c�Ɠ���������̃Z�b�g���t�̂��ߏC���@@���@@SRA�@@START
			if (l_ipoProductParams.getOfferStartDateCountProspecIsNull())
            {
                l_ipoTermUnitOfferDate.bizDateUpper = null;
            }
            else
            {
                l_ipoTermUnitOfferDate.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferStartDateCountProspec());
            }
            if (l_ipoProductParams.getOfferEndDateCountProspecIsNull())
            {
                l_ipoTermUnitOfferDate.bizDateLower = null;
            }
            else
            {
                l_ipoTermUnitOfferDate.bizDateLower = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferEndDateCountProspec());
            }
//            if (l_ipoProductParams.getOfferStartDateCountProspecIsNull())
//            {
//                l_ipoTermUnitOfferDate.bizDateUpper = null;
//            }
//            else
//            {
//                l_ipoTermUnitOfferDate.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferStartDateCountProspec());
//            }
//            if (l_ipoProductParams.getOfferEndDateCountProspecIsNull())
//            {
//                l_ipoTermUnitOfferDate.bizDateLower = null;
//            }
//            else
//            {
//                l_ipoTermUnitOfferDate.bizDateLower = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferEndDateCountProspec());
//            }
			//// 2004/10/29 U00341�̑Ή��@@�c�Ɠ���������̃Z�b�g���t�̂��ߏC���@@���@@SRA�@@END
            
            
            //11.IPO���Ԏw��( )
            WEB3IPOTermUnit l_ipoTermUnitOfferdDtetime = new WEB3IPOTermUnit();
            
            //12.�w���\�����ԁi���Ўw��j�C���X�^���X �v���p�e�B�Z�b�g
            l_ipoTermUnitOfferdDtetime.startDate = l_ipoProductParams.getOfferStartDatetime();
            l_ipoTermUnitOfferdDtetime.endDate = l_ipoProductParams.getOfferEndDatetime();
            ////// 2004/10/29 U00341�̑Ή��@@�c�Ɠ���������̃Z�b�g���t�̂��ߏC���@@���@@SRA�@@START
            
            //U01313 bizDateLower -> bizDateUpper
			if (l_ipoProductParams.getOfferStartDateCountIsNull())
            {
                l_ipoTermUnitOfferdDtetime.bizDateUpper = null;
            }
            else
            {
                l_ipoTermUnitOfferdDtetime.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferStartDateCount());
            }
            //U01313 bizDateUpper -> bizDateLower
            if (l_ipoProductParams.getOfferEndDateCountIsNull())
            {
                l_ipoTermUnitOfferdDtetime.bizDateLower = null;
            }
            else
            {
                l_ipoTermUnitOfferdDtetime.bizDateLower = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferEndDateCount());
            }  
//            if (l_ipoProductParams.getOfferStartDateCountIsNull())
//            {
//                l_ipoTermUnitOfferdDtetime.bizDateUpper = null;
//            }
//            else
//            {
//                l_ipoTermUnitOfferdDtetime.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferStartDateCount());
//            }
//            if (l_ipoProductParams.getOfferEndDateCountIsNull())
//            {
//                l_ipoTermUnitOfferdDtetime.bizDateLower = null;
//            }
//            else
//            {
//                l_ipoTermUnitOfferdDtetime.bizDateLower = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getOfferEndDateCount());
//            }          
//			//// 2004/10/29 U00341�̑Ή��@@�c�Ɠ���������̃Z�b�g���t�̂��ߏC���@@���@@SRA�@@END



            //13.IPO�������
            WEB3IPOProductInfo l_ipoProductInfo = new WEB3IPOProductInfo();
            
            //14.IPO������� �v���p�e�B�Z�b�g                
            //IPO�������.IPO�o�^�敪�F�@@IPO����.IPO�o�^�敪
            l_ipoProductInfo.ipoRegistDiv = l_ipoProductParams.getIpoRegistDiv();
            //IPO�������.IPO�o�^�敪�ڍׁF�@@IPO����.IPO�o�^�敪�ڍ�
            l_ipoProductInfo.ipoRegistDetailDiv = l_ipoProductParams.getIpoRegistDetailDiv();            
            //IPO�������.�����R�[�h�F�@@IPO����.�����R�[�h
            l_ipoProductInfo.productCode = l_ipoProductParams.getProductCode();
            //IPO�������.�������F�@@IPO����.������
            l_ipoProductInfo.productName = l_ipoProductParams.getStandardName();
            //IPO�������.���茈��敪�F
                //�iIPO����.is�X�P�W���[������() == true�j�̏ꍇ�A�h����h
                //�iIPO����.is�X�P�W���[������() == false�j�̏ꍇ�A�h����h
            if (l_blnScheduleDecision)
            {
                log.debug("is�X�P�W���[������() == true");
                l_ipoProductInfo.undecideDecideDiv = WEB3UndecideDecideDivDef.SCHEDULE_DECIDED;   
            }
            else
            {
                log.debug("is�X�P�W���[������() == false");
                l_ipoProductInfo.undecideDecideDiv = WEB3UndecideDecideDivDef.SCHEDULE_UNDECIDED;
            }
            //IPO�������.���J���F�@@���J���i�FIPO���Ԏw��j
            l_ipoProductInfo.publicOfferingDate = l_ipoTermUnitPublicDate;
            //IPO�������.���J�s��R�[�h�F�@@IPO����.���J�s��
            l_ipoProductInfo.publicOfferingMarketCode = l_ipoProductParams.getPublicMarket();
            //IPO�������.�������敪�F�@@IPO����.�������敪
            l_ipoProductInfo.temporaryConditionDiv = l_ipoProductParams.getProvisionalValueDiv();
            
            //IPO�������.�����������l�F�@@IPO����.�����������l
            log.debug("l_ipoProductParams.getProvisionalMinValue() = " + l_ipoProductParams.getProvisionalMinValue());
            if (l_ipoProductParams.getProvisionalMinValueIsNull())
            {
                log.debug("l_ipoProductParams.provisional_min_value.isNaN()");
                l_ipoProductInfo.temporaryConditionLower = null;
            }
            else
            {
                log.debug("IPO����.�����������l");
                l_ipoProductInfo.temporaryConditionLower = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getProvisionalMinValue());
            }            
            
            //IPO�������.����������l�F�@@IPO����.����������l
            if (l_ipoProductParams.getProvisionalMaxValueIsNull())
            {
                log.debug("l_ipoProductParams.provisional_max_value.isNaN()");
                l_ipoProductInfo.temporaryConditionUpper = null;
            }
            else
            {
                log.debug("IPO����.����������l");
                l_ipoProductInfo.temporaryConditionUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getProvisionalMaxValue());
            }            
            
            //IPO�������.�������񎦓��F�@@IPO����.�������񎦓�
            l_ipoProductInfo.temporaryConditionPresentationDate = l_ipoProductParams.getProvisionalValueOpenDate();
            //IPO�������.���吔�ʁF�@@IPO����.���吔��
            if (l_ipoProductParams.getPublicOfferingQuantityIsNull())
            {
                l_ipoProductInfo.issuedQuantity = null;
            }
            else
            {
                l_ipoProductInfo.issuedQuantity = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicOfferingQuantity());
            }
            
            //IPO�������.���o���ʁF�@@IPO����.���o����
            if (l_ipoProductParams.getPublicSalesQuantityIsNull())
            {
                l_ipoProductInfo.offeringQuantity = null;    
            }
            else
            {
                l_ipoProductInfo.offeringQuantity = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicSalesQuantity());
            }
            
            //IPO�������.���Ў戵���ʁF�@@IPO����.���Ў戵����
            if (l_ipoProductParams.getDealingQuantityIsNull())
            {
                l_ipoProductInfo.handlingQuantity = null;
            }
            else
            {
                l_ipoProductInfo.handlingQuantity = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getDealingQuantity());
            }
            
            //IPO�������.�w���\���P�ʁF�@@IPO����.�w���\���P��
            if (l_ipoProductParams.getLotSizeIsNull())
            {
                l_ipoProductInfo.offerUnit = null;
            }
            else
            {
                l_ipoProductInfo.offerUnit = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getLotSize());
            }
            
            //IPO�������.���݁F�@@IPO����.����
            if (l_ipoProductParams.getTickValueIsNull())
            {
                log.debug("l_ipoProductParams.tick_value.isNaN()");
                l_ipoProductInfo.tickValue = null;
            }
            else
            {
                log.debug("IPO����.����");
                l_ipoProductInfo.tickValue = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getTickValue());
            }            
            
            //IPO�������.�\���p�P�ʋ敪�F�@@IPO����.�\���p�P�ʋ敪
            l_ipoProductInfo.displayUnitDiv = l_ipoProductParams.getIpoUnitDiv();
            //IPO�������.���s�\�F�@@IPO����.���s�\
            l_ipoProductInfo.marketOrderFlag = l_ipoProductParams.getEnableMarketOrder();
            //IPO�������.�劲���،���Ж��F�@@IPO����.�劲���،���Ж�
            l_ipoProductInfo.leadManagingUnderwriter = l_ipoProductParams.getLeadManagingUnderwriter();
            //IPO�������.�u�b�N�r���f�B���O�J�n�����F�@@IPO����.�u�b�N�r���f�B���O�J�n����
            l_ipoProductInfo.bookBuildingStartDate = l_ipoProductParams.getBookbuildingStartDatetime();
            //IPO�������.�u�b�N�r���f�B���O�I�������F�@@IPO����.�u�b�N�r���f�B���O�I������
            l_ipoProductInfo.bookBuildingEndDate = l_ipoProductParams.getBookbuildingEndDatetime();
            //IPO�������.���J���i������F�@@���J���i������i�FIPO���Ԏw��j
            l_ipoProductInfo.publicOfferingPriceDetermDate = l_ipoTermUnitPublicPriceSettleDate;
            //IPO�������.���J���i�F�@@IPO����.���J���i
            if (l_ipoProductParams.getPublicPriceIsNull())
            {
                log.debug("l_ipoProductParams.public_price.isNaN()");
                l_ipoProductInfo.publicOfferingPrice = null;
            }
            else
            {
                log.debug("IPO����.���J���i");
                l_ipoProductInfo.publicOfferingPrice = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicPrice());
            }           
            
            //IPO�������.���J���i�f�B�X�J�E���g���F�@@IPO����.���J���i�i�f�B�X�J�E���g���j
            if (l_ipoProductParams.getPublicPriceDiscountRateIsNull())
            {
                log.debug("l_ipoProductParams.public_price_discount_rate.isNaN()");
                l_ipoProductInfo.publicOfferingDiscountRate = null;
            }
            else
            {
                log.debug("IPO����.���J���i�i�f�B�X�J�E���g���j");
                l_ipoProductInfo.publicOfferingDiscountRate = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicPriceDiscountRate());
            }            
            
            //IPO�������.���I���F�@@���I���i�FIPO���Ԏw��j
            l_ipoProductInfo.lotDate = l_ipoTermUnitLotDate;
            //IPO�������.�w���\�����ԁi�ژ_�����j�F�@@�w���\�����ԁi�ژ_�����j�i�FIPO���Ԏw��j
            l_ipoProductInfo.prospectusOfferTerm = l_ipoTermUnitOfferDate;
            //IPO�������.�w���\�����ԁi���Ўw��j�F�@@�w���\�����ԁi���Ўw��j�i�FIPO���Ԏw��j
            l_ipoProductInfo.appointOfferTerm = l_ipoTermUnitOfferdDtetime;
            //IPO�������.���s��Ѓ��S�t�@@�C��URL�F�@@IPO����.���s��Ѓ��S�t�@@�C��URL
            l_ipoProductInfo.issuerLogoFileURL = l_ipoProductParams.getCompanyLogoUrl();
            //IPO�������.���s��ЃE�F�u�T�C�gURL�F�@@IPO����.���s��ЃE�F�u�T�C�gURL
            l_ipoProductInfo.issuerWebSiteURL = l_ipoProductParams.getCompanyUrl();
            //IPO�������.���s��ЊT�v�F�@@IPO����.���s��ЊT�v
            l_ipoProductInfo.issuerCorporateOutline = l_ipoProductParams.getCompanyOutline();
            //IPO�������.���l�F�@@IPO����.���l
            l_ipoProductInfo.note = l_ipoProductParams.getNote();
            //IPO�������.�ژ_�����{���敪:   IPO����.�ژ_�����{���敪
            l_ipoProductInfo.prospectusReadDiv = l_ipoProductParams.getDocReadingDiv();
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductInfo;  
        }
        catch (NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage());
        }
    }
    
    /**
     * (createIPO����)<BR>
     * ��ʓ��͒l���IPO���������쐬����B<BR>
     * <BR>
     * �P�j�@@�����iIPO���������̓��b�Z�[�W�^�Ǘ��҃I�u�W�F�N�g�j���A<BR>
     * �o�^���e���Z�b�g����B<BR>
     * �@@����.IPO�����s�̍��ڂɓo�^�l���Z�b�g����B<BR>
     * <BR>
     * �@@�Z�b�g���e�ɂ��ẮA<BR>
     * �@@�⑫����.DB�X�V<BR>
     * �@@�@@�u�Ǘ��ҁE�����o�^_IPO�����e�[�u���d�l.xls�v�Q�ƁB<BR>
     * �@@�������ł́A��L�t�@@�C������<BR>
     * �@@�h���N�G�X�g�f�[�^.�h�܂��́A�h�Ǘ���.�h���ҏW����w�肪���镔����<BR>
     * �݃Z�b�g�ΏہB<BR>
     * <BR>
     * �Q�j IPO�����I�u�W�F�N�g����<BR>
     * �@@IPO����Params�������ɁAIPO�����I�u�W�F�N�g�𐶐����A�ԋp����B<BR>
     * @@param l_ipoProductParams - IPO�����s�I�u�W�F�N�g
     * @@param l_productInfoInputMsg - IPO���������̓��b�Z�[�W
     * @@param l_admin - �Ǘ��҃I�u�W�F�N�g
     * @@return webbroker3.ipo.WEB3IpoProductImpl
     * @@roseuid 40CE77BF0384
     */
    public WEB3IpoProductImpl createIpoProduct(IpoProductParams l_ipoProductParams, WEB3IPOProductInfo l_productInfoInputMsg, WEB3Administrator l_admin) 
    {
        final String STR_METHOD_NAME = " createIpoProduct(IpoProductParams,WEB3IpoProductInfo,WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        //�P�j����.IPO�����s�̍��ڂɓo�^�l���Z�b�g����
        //�،���ЃR�[�h
        l_ipoProductParams.setInstitutionCode(l_admin.getInstitutionCode());
        
        //�����R�[�h
        l_ipoProductParams.setProductCode(l_productInfoInputMsg.productCode);
        
        //������
        l_ipoProductParams.setStandardName(l_productInfoInputMsg.productName);
        
        //IPO�o�^�敪
        l_ipoProductParams.setIpoRegistDiv(l_productInfoInputMsg.ipoRegistDiv);
        
        //IPO�o�^�敪�ڍ�
        l_ipoProductParams.setIpoRegistDetailDiv(l_productInfoInputMsg.ipoRegistDetailDiv);
        
        //���J��
        l_ipoProductParams.setPublicOfferingDate(l_productInfoInputMsg.publicOfferingDate.startDate);
        
        //���J������
        if (l_productInfoInputMsg.publicOfferingDate.bizDateUpper == null)
        {
            l_ipoProductParams.setPublicOfferingDateCount(null);
        }
        else
        {
            l_ipoProductParams.setPublicOfferingDateCount(Integer.parseInt(l_productInfoInputMsg.publicOfferingDate.bizDateUpper));
        }       
               
        //���J�s��
        l_ipoProductParams.setPublicMarket(l_productInfoInputMsg.publicOfferingMarketCode);
        
        //�������敪
        l_ipoProductParams.setProvisionalValueDiv(l_productInfoInputMsg.temporaryConditionDiv);
        
        //�����������l
        //����������l
        if (WEB3StringTypeUtility.isEmptyOrBlank(l_productInfoInputMsg.temporaryConditionUpper)
            && WEB3StringTypeUtility.isEmptyOrBlank(l_productInfoInputMsg.temporaryConditionLower))
        {
            l_ipoProductParams.setProvisionalMinValue(null);
            l_ipoProductParams.setProvisionalMaxValue(null);
        }
        else if (WEB3StringTypeUtility.isEmptyOrBlank(l_productInfoInputMsg.temporaryConditionUpper))
        {
            l_ipoProductParams.setProvisionalMinValue(Double.valueOf(l_productInfoInputMsg.temporaryConditionLower));
            l_ipoProductParams.setProvisionalMaxValue(null);
        }
        else if (WEB3StringTypeUtility.isEmptyOrBlank(l_productInfoInputMsg.temporaryConditionLower))
        {
            l_ipoProductParams.setProvisionalMinValue(null);
            l_ipoProductParams.setProvisionalMaxValue(Double.valueOf(l_productInfoInputMsg.temporaryConditionUpper));
        }
        else
        {
            BigDecimal l_bdTempCondUpper = new BigDecimal(l_productInfoInputMsg.temporaryConditionUpper.trim());
            BigDecimal l_bdTempCondLower = new BigDecimal(l_productInfoInputMsg.temporaryConditionLower.trim());
            if (l_bdTempCondUpper.compareTo(l_bdTempCondLower) >= 0)
            {
                l_ipoProductParams.setProvisionalMinValue(Double.valueOf(l_productInfoInputMsg.temporaryConditionLower));

                l_ipoProductParams.setProvisionalMaxValue(Double.valueOf(l_productInfoInputMsg.temporaryConditionUpper));   
            }
            else
            {
                l_ipoProductParams.setProvisionalMinValue(Double.valueOf(l_productInfoInputMsg.temporaryConditionUpper));
            
                l_ipoProductParams.setProvisionalMaxValue(Double.valueOf(l_productInfoInputMsg.temporaryConditionLower));            
            }
        }
        
        //�������񎦓�
        l_ipoProductParams.setProvisionalValueOpenDate(l_productInfoInputMsg.temporaryConditionPresentationDate);
        
        //���吔��
        if (l_productInfoInputMsg.issuedQuantity == null)
        {
            log.debug("l_productInfoInputMsg.publicOfferingQuantity.equals(null)");
            l_ipoProductParams.setPublicOfferingQuantity(null);
        }
        else
        {
            l_ipoProductParams.setPublicOfferingQuantity(Long.parseLong(l_productInfoInputMsg.issuedQuantity));
        }
               
        //���o����
        if (l_productInfoInputMsg.offeringQuantity == null)
        {
            log.debug("l_productInfoInputMsg.publicSalesQuantity.equals(null)");
            l_ipoProductParams.setPublicSalesQuantity(null);
        }
        else
        {
            l_ipoProductParams.setPublicSalesQuantity(Long.parseLong(l_productInfoInputMsg.offeringQuantity));
        }        
        
        //���Ў戵����
        if (l_productInfoInputMsg.handlingQuantity == null)
        {
            log.debug("l_productInfoInputMsg.dealingQuantity.equals(null)");
            l_ipoProductParams.setDealingQuantity(null);
        }
        else
        {
            l_ipoProductParams.setDealingQuantity(Long.parseLong(l_productInfoInputMsg.handlingQuantity));
        }       
        
        //�劲���،���Ж�
        l_ipoProductParams.setLeadManagingUnderwriter(l_productInfoInputMsg.leadManagingUnderwriter);
        
        //�w���\���P��
        if (l_productInfoInputMsg.offerUnit == null)
        {
            log.debug("l_productInfoInputMsg.lotSize.equals(null)");
            l_ipoProductParams.setLotSize(null);
        }
        else
        {
            l_ipoProductParams.setLotSize(Long.parseLong(l_productInfoInputMsg.offerUnit));
        }
        
        //����
        if (l_productInfoInputMsg.tickValue == null)
        {
            log.debug("l_productInfoInputMsg.tickValue.equals(null)");
            l_ipoProductParams.setTickValue(null);
        }
        else
        {
            l_ipoProductParams.setTickValue(Double.valueOf(l_productInfoInputMsg.tickValue));
        }        
        
        //�\���p�P�ʋ敪
        l_ipoProductParams.setIpoUnitDiv(l_productInfoInputMsg.displayUnitDiv);
        
        //���s�\
        l_ipoProductParams.setEnableMarketOrder(l_productInfoInputMsg.marketOrderFlag);
        
        //�u�b�N�r���f�B���O�J�n����
        l_ipoProductParams.setBookbuildingStartDatetime(l_productInfoInputMsg.bookBuildingStartDate);
        
        //�u�b�N�r���f�B���O�I������
        l_ipoProductParams.setBookbuildingEndDatetime(l_productInfoInputMsg.bookBuildingEndDate);
        
        //���J���i�����
        if(l_productInfoInputMsg.publicOfferingPriceDetermDate == null) 
        {   
            l_ipoProductParams.setPublicPriceSettleDate(null);  
        }   
        else    
        {   
            l_ipoProductParams.setPublicPriceSettleDate(l_productInfoInputMsg.publicOfferingPriceDetermDate.startDate);
        }
        
        //���J���i
        if (l_productInfoInputMsg.publicOfferingPrice == null)
        {
            log.debug("l_productInfoInputMsg.publicPrice.equals(null)");
            l_ipoProductParams.setPublicPrice(null);
        }
        else
        {
            l_ipoProductParams.setPublicPrice(Double.valueOf(l_productInfoInputMsg.publicOfferingPrice));
        }
                
        //���J���i�i�f�B�X�J�E���g���j
        if (l_productInfoInputMsg.publicOfferingDiscountRate == null)
        {
            log.debug("l_productInfoInputMsg.publicPriceDiscountRate.equals(null)");
            l_ipoProductParams.setPublicPriceDiscountRate(null);
        }
        else
        {
            l_ipoProductParams.setPublicPriceDiscountRate(Double.valueOf(l_productInfoInputMsg.publicOfferingDiscountRate));
        }
        
        //���I��
        l_ipoProductParams.setLotDate(l_productInfoInputMsg.lotDate.startDate);
        
        //���I������
        if (l_productInfoInputMsg.lotDate.bizDateUpper == null)
        {
            l_ipoProductParams.setLotDateCount(null);
        }
        else
        {
            l_ipoProductParams.setLotDateCount(Integer.parseInt(l_productInfoInputMsg.lotDate.bizDateUpper));
        }
        
        //�w���\���J�n����(���Аݒ�)
        l_ipoProductParams.setOfferStartDatetime(l_productInfoInputMsg.appointOfferTerm.startDate);
        
        //�w���\���J�n������(���Аݒ�j
        
		///2004/10/29  U00341�̉c�Ɠ�����l�����l�̐ݒ���C���@@���@@SRA�@@�@@START
        
        //U01313 bizDateLower -> bizDateUpper
        if (l_productInfoInputMsg.appointOfferTerm.bizDateUpper == null)
        {
            l_ipoProductParams.setOfferStartDateCount(null);
        }
        else
        {
            l_ipoProductParams.setOfferStartDateCount(Integer.parseInt(l_productInfoInputMsg.appointOfferTerm.bizDateUpper));
        }
//		if (l_productInfoInputMsg.appointOfferTerm.bizDateUpper == null)
//				{
//					l_ipoProductParams.setOfferStartDateCount(null);
//				}
//				else
//				{
//					l_ipoProductParams.setOfferStartDateCount(Integer.parseInt(l_productInfoInputMsg.appointOfferTerm.bizDateUpper));
//				}
		///2004/10/29  U00341�̉c�Ɠ�����l�����l�̐ݒ���C���@@���@@SRA�@@�@@END
		
		        
        //�w���\���I������(���Аݒ�)
        l_ipoProductParams.setOfferEndDatetime(l_productInfoInputMsg.appointOfferTerm.endDate);
        
        //�w���\���I��������(���Аݒ�j
        ///2004/10/29  U00341�̉c�Ɠ�����l�����l�̐ݒ���C���@@���@@SRA�@@�@@START
        
        //U01313 bizDateUpper -> bizDateLower
        if (l_productInfoInputMsg.appointOfferTerm.bizDateLower == null)
        {
            l_ipoProductParams.setOfferEndDateCount(null);
        }
        else
        {
            l_ipoProductParams.setOfferEndDateCount(Integer.parseInt(l_productInfoInputMsg.appointOfferTerm.bizDateLower));
        }
//		if (l_productInfoInputMsg.appointOfferTerm.bizDateLower == null)
//				{
//					l_ipoProductParams.setOfferEndDateCount(null);
//				}
//				else
//				{
//					l_ipoProductParams.setOfferEndDateCount(Integer.parseInt(l_productInfoInputMsg.appointOfferTerm.bizDateLower));
//				}
		///2004/10/29  U00341�̉c�Ɠ�����l�����l�̐ݒ���C���@@���@@SRA�@@�@@END
        
        //�w���\���J�n��(�ژ_�����L��)
        l_ipoProductParams.setOfferStartDateProspec(l_productInfoInputMsg.prospectusOfferTerm.startDate);
        
        //�w���\���J�n������(�ژ_�����L��)
        
		///2004/10/29  U00341�̉c�Ɠ�����l�����l�̐ݒ���C���@@���@@SRA�@@�@@START
        if (l_productInfoInputMsg.prospectusOfferTerm.bizDateUpper == null)
        {
            l_ipoProductParams.setOfferStartDateCountProspec(null);
        }
        else
        {
            l_ipoProductParams.setOfferStartDateCountProspec(Integer.parseInt(l_productInfoInputMsg.prospectusOfferTerm.bizDateUpper));
        }
//		if (l_productInfoInputMsg.prospectusOfferTerm.bizDateUpper == null)
//				{
//					l_ipoProductParams.setOfferStartDateCountProspec(null);
//				}
//				else
//				{
//					l_ipoProductParams.setOfferStartDateCountProspec(Integer.parseInt(l_productInfoInputMsg.prospectusOfferTerm.bizDateUpper));
//				}
//		/2004/10/29  U00341�̉c�Ɠ�����l�����l�̐ݒ���C���@@���@@SRA�@@�@@END
        
        //�w���\���I����(�ژ_�����L��)
        l_ipoProductParams.setOfferEndDateProspec(l_productInfoInputMsg.prospectusOfferTerm.endDate);
        
        //�w���\���I��������(�ژ_�����L��)
        
        ///2004/10/29  U00341�̉c�Ɠ�����l�����l�̐ݒ���C���@@���@@SRA�@@�@@START
		if (l_productInfoInputMsg.prospectusOfferTerm.bizDateLower == null)
        {
            l_ipoProductParams.setOfferEndDateCountProspec(null);
        }
        else
        {
            l_ipoProductParams.setOfferEndDateCountProspec(Integer.parseInt(l_productInfoInputMsg.prospectusOfferTerm.bizDateLower));
        }
//        if (l_productInfoInputMsg.prospectusOfferTerm.bizDateLower == null)
//        {
//            l_ipoProductParams.setOfferEndDateCountProspec(null);
//        }
//        else
//        {
//            l_ipoProductParams.setOfferEndDateCountProspec(Integer.parseInt(l_productInfoInputMsg.prospectusOfferTerm.bizDateLower));
//        }       
		///2004/10/29  U00341�̉c�Ɠ�����l�����l�̐ݒ���C���@@���@@SRA�@@�@@END
        
        
        
        //���s��Ѓ��S�t�@@�C��URL
        l_ipoProductParams.setCompanyLogoUrl(l_productInfoInputMsg.issuerLogoFileURL);
        
        //���s��ЃE�F�u�T�C�gURL
        l_ipoProductParams.setCompanyUrl(l_productInfoInputMsg.issuerWebSiteURL);
        
        //���s��ЊT�v
        l_ipoProductParams.setCompanyOutline(l_productInfoInputMsg.issuerCorporateOutline);
        
        //���l
        l_ipoProductParams.setNote(l_productInfoInputMsg.note);
        
        //�X�V�҃R�[�h
        l_ipoProductParams.setLastUpdater(l_admin.getAdministratorCode());
        
        //�ژ_�����{���敪
        if (l_productInfoInputMsg.prospectusReadDiv == null)
        {
            l_ipoProductParams.setDocReadingDiv(WEB3DocReadingDivDef.DEFAULT);
        }
        else
        {
            l_ipoProductParams.setDocReadingDiv(l_productInfoInputMsg.prospectusReadDiv);
        }

        //�Q�jIPO�����I�u�W�F�N�g�𐶐����A�ԋp����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        WEB3IpoProductImpl l_ipoProductImpl = (WEB3IpoProductImpl)l_ipoProductManagerImpl.toProduct(l_ipoProductParams);
        log.debug("l_ipoProductImpl" + l_ipoProductImpl.getDataSourceObject());
        
        log.exiting(STR_METHOD_NAME);
        return l_ipoProductImpl;
    }    
}
@
