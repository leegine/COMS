head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M���������o�^�T�[�r�X�@@����(WEB3AdminMutualConditionsServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 �����(���u) �V�K�쐬
Revesion History : 2004/08/25 ��O�� (���u) ���r���[   
Revesion History : 2004/12/06 ������ (���u) �c�Ή� 
Revesion History : 2005/10/23 ���� (���u) �t�B�f���e�B�Ή� 
Revesion History : 2006/05/18 �юu�� (���u)�@@�d�l�ύX ���f��414      
Revesion History : 2006/10/19 ���� (���u) �d�l�ύX�E���f��499�A505      
Revesion History : 2006/10/31 ���� (���u) ��Q�ԍ�:U02921       
Revesion History : 2006/11/06 SRA�吙 ��Q�ԍ�:U02932       
Revesion History : 2007/04/10 ��іQ (���u) ���f�� No.545
Revesion History : 2007/10/15 ���^�] (���u) ���f�� No.580 �c�a�X�V�d�l093
*/

package webbroker3.mf.service.delegate.stdimpls;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MfTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MfTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BuyPossibleDivDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3SellPossibleDivDef;
import webbroker3.common.define.WEB3SpecDivDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.common.define.WEB3SwtPossibleDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mf.WEB3AdminMutualFrgncal;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductCondSpec;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradedProduct;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.data.MutualFund2ndProductSonarDao;
import webbroker3.mf.data.MutualFund2ndProductSonarRow;
import webbroker3.mf.define.WEB3MFRecruitPossibleDivDef;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputResponse;
import webbroker3.mf.message.WEB3MutualProductConditionsCommonInfo;
import webbroker3.mf.service.delegate.WEB3AdminMutualConditionsService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M���������o�^�T�[�r�X�@@�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminMutualConditionsServiceImpl extends WEB3MutualClientRequestService 
    implements WEB3AdminMutualConditionsService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsServiceImpl.class);
    
    /**
     * �Ǘ��ғ����M�� ���������o�^�����{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h�̂����ꂩ���R�[������B<BR>
     * �@@�@@input���������o�^()<BR>
     * �@@�@@validate���������o�^()<BR>
     * �@@�@@submit���������o�^()<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E530C2013A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminMutualConditionsInputRequest)
        {
            // �|input���������o�^()
            l_response = this.inputProductConditionsRegist(
                (WEB3AdminMutualConditionsInputRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminMutualConditionsConfirmRequest)
        {
            // �|validate���������o�^()
            l_response = this.validateProductConditionsRegist(
                (WEB3AdminMutualConditionsConfirmRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminMutualConditionsCompleteRequest)
        {
            // �|submit���������o�^()
            l_response = this.submitProductConditionsRegist(
                (WEB3AdminMutualConditionsCompleteRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else
        {
            // �p�����[�^�l���s��
            log.debug(l_strMethodName + " �p�����[�^�l���s���I");
            log.exiting(l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        } 
    }
    
    /**
     * (input���������o�^)<BR>
     * ���M���������o�^���͏������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u���M�i�Ǘ��ҁjinput���������o�^�v�Q��<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsRefInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E3F1030164
     */
    protected WEB3AdminMutualConditionsInputResponse inputProductConditionsRegist(
        WEB3AdminMutualConditionsInputRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "inputProductConditionsRegist(" 
            + "WEB3AdminMutualConditionsInputRequest l_request)";
        log.entering(l_strMethodName);

        //�Q�j���̓`�F�b�N 
        //�@@����.���N�G�X�g�f�[�^.validate()���R�[������B 
        l_request.validate();

        //�R�j�Ǘ��Ҍ����`�F�b�N 
        //�@@�R�|�P�j�Ǘ��҃I�u�W�F�N�g.getInstanceFrom���O�C�����( )���R�[������B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //�@@�R�|�Q�j�Ǘ��҃I�u�W�F�N�g.validate����( )���R�[������B 
        // [validate����()�Ɏw�肷�����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���M�i�J�����_�[�Ǘ��j 
        // is�X�V�F�@@true
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS,
                true);
        
        // �S�j���M���������o�^���̓��X�|���X�̐ݒ�B 
        //  �擾�������M�����I�u�W�F�N�g�����ɁA���X�|���X�̐ݒ���s���B 

        // --------- Start -------------- �g�����M�����}�l�[�W���̎擾���s��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
        // --------- End -------------- �g�����M�����}�l�[�W���̎擾���s��
        
        WEB3AdminMutualConditionsInputResponse l_response = 
            (WEB3AdminMutualConditionsInputResponse)l_request.createResponse();
        try
        {
            // �S�|�P�j�g�����M�����}�l�[�W��.getProduct()�̖߂�l�𓊐M�����N���X�ŃL���X�g����B 
            WEB3MutualFundProduct l_mfProduct = 
                (WEB3MutualFundProduct)l_mfProductManager.getProduct(Long.parseLong(l_request.id));
            
            log.debug("�g�����M�����}�l�[�W��.getProduct()�̖߂�l Product ID = " + l_mfProduct.getProductId());
            
            //�S�|�Q�j���M��2�����}�X�^Dao.findRowByProductCode()���R�[�����A 
            // ���M��2�����}�X�^Row�I�u�W�F�N�g���擾����B
            MutualFund2ndProductSonarRow l_mfProductSonar2Row = 
                MutualFund2ndProductSonarDao.findRowByProductCodeInstitutionCode(
                    l_mfProduct.getProductCode(),
                    l_admin.getInstitutionCode());
            log.debug("���M��2�����}�X�^Row�I�u�W�F�N�g���擾����̖߂�l ProductCode = " + 
                    l_mfProductSonar2Row.getProductCode());
            
            MutualFundProductRow l_mfProductRow = 
                (MutualFundProductRow)l_mfProduct.getDataSourceObject();
            
            //�S�|�R�j���M���������o�^���ʏ��I�u�W�F�N�g�𐶐����A�ȉ��̃v���p�e�B���Z�b�g����B
            WEB3MutualProductConditionsCommonInfo l_mfProductInfo = 
                new WEB3MutualProductConditionsCommonInfo();
            
            // -------------------------------------------------------------
            //�@@�@@�����R�[�h�����M����.getProductCode()
            l_mfProductInfo.mutualProductCode = l_mfProduct.getProductCode();
            
            //WEBBROKER3�戵�󋵁����M����.getDataSourceObject().get�V�X�e���戵�敪() 
            l_mfProductInfo.web3TreatmentFlag = l_mfProductRow.getSystemHandlingDiv();
            
            //�������i�a���j�����M����.get������() 
            l_mfProductInfo.jpnProductName = l_mfProductRow.getStandardName();
            
            //�������i�p���j�����M����.getDataSourceObject().get�������i�p���j() 
            l_mfProductInfo.engProductName = l_mfProductRow.getEngProductName();
            
            //���M�����J�e�S���[�R�[�h�����M����.getDataSourceObject().get�J�e�S���[�R�[�h() 
            l_mfProductInfo.categoryCode = l_mfProductRow.getCategoryCode();
            
            //���t�J�n�������M����.getDataSourceObject().get���t�J�n��() 
            l_mfProductInfo.buyStartDate = l_mfProductRow.getBuyStartDate();
            
            //���t�I���������M����.getDataSourceObject().get���t�I����() 
            l_mfProductInfo.buyEndDate = l_mfProductRow.getBuyEndDate();
            
            //���抷�J�n�������M����.getDataSourceObject().get���抷�J�n��() 
            l_mfProductInfo.sellSwitchingStartDate = l_mfProductRow.getSellSwtStartDate();
            
            //���抷�I���������M����.getDataSourceObject().get���抷�I����() 
            l_mfProductInfo.sellSwitchingEndDate = l_mfProductRow.getSellSwtEndDate();
            
            //���搿���J�n�������M����.getDataSourceObject().get���搿���J�n��() 
            l_mfProductInfo.buyClaimStartDate = l_mfProductRow.getBuyClaimStartDate();
            
            //���搿���I���������M����.getDataSourceObject().get���搿���I����() 
            l_mfProductInfo.buyClaimEndDate = l_mfProductRow.getBuyClaimEndDate();
            
            //��W�J�n�����g�����M����.get��W�J�n��() 
            l_mfProductInfo.applyAbleStartDate = l_mfProductRow.getRecruitStartDate();

            //��W�I�������g�����M����.get��W�I����()
			l_mfProductInfo.applyAbleEndDate = l_mfProductRow.getRecruitEndDate();

            //�w����@@�i���t�j�����M����.get�w����@@�i���t�j() 
            l_mfProductInfo.buySelectable = l_mfProductRow.getBuySpecityDiv();
            
            //�Œ�����i�V�K���t�j�����M����.get�Œ�����i�V�K���t�j()
            if(l_mfProductRow.getNewBuyMinQtyIsNull())
            {
                l_mfProductInfo.newBuyMinQty = null;    
            }
            else
            {
                l_mfProductInfo.newBuyMinQty = 
                    Long.toString(l_mfProductRow.getNewBuyMinQty());
            }
            
            //�P�ʌ����i�V�K���t�j�����M����.get�P�ʌ����i�V�K���t�j()
            if(l_mfProductRow.getNewBuyUnitQtyIsNull()) 
            {
                l_mfProductInfo.newBuyUnitQty = null;
            }
            else
            {
                l_mfProductInfo.newBuyUnitQty = 
                    Long.toString(l_mfProductRow.getNewBuyUnitQty());
            }
            
            //�Œ���z�i�V�K���t�j�����M����.get�Œ���z�i�V�K���t�j()
            if(l_mfProductRow.getNewBuyMinAmtIsNull()) 
            {
                l_mfProductInfo.newBuyMinAmt = null;
            }
            else
            {
                l_mfProductInfo.newBuyMinAmt = 
                    Long.toString(l_mfProductRow.getNewBuyMinAmt());
            }
            
            //�P�ʋ��z�i�V�K���t�j�����M����.get�P�ʋ��z�i�V�K���t�j()
            if(l_mfProductRow.getNewBuyUnitAmtIsNull())
            { 
                l_mfProductInfo.newBuyUnitAmt = null;
            }
            else
            {
                l_mfProductInfo.newBuyUnitAmt = 
                    Long.toString(l_mfProductRow.getNewBuyUnitAmt());
            }

            //�Œ�����i�ǉ����t�j�����M����.get�Œ�����i�ǉ����t�j() 
            if(l_mfProductRow.getAddBuyMinQtyIsNull())
            {
                l_mfProductInfo.addBuyMinQty = null;
            }
            else
            {
                l_mfProductInfo.addBuyMinQty = 
                    Long.toString(l_mfProductRow.getAddBuyMinQty());
            }
           
            //�P�ʌ����i�ǉ����t�j�����M����.get�P�ʌ����i�ǉ����t�j() 
            if(l_mfProductRow.getAddBuyUnitQtyIsNull())
            {
                l_mfProductInfo.addBuyUnitQty = null;
            }
            else
            {
                l_mfProductInfo.addBuyUnitQty = 
                    Long.toString(l_mfProductRow.getAddBuyUnitQty());
            }
            
            //�Œ���z�i�ǉ����t�j�����M����.get�Œ���z�i�ǉ����t�j() 
            if(l_mfProductRow.getAddBuyMinAmtIsNull())
            {
                l_mfProductInfo.addBuyMinAmt = null;
            }
            else
            {
                l_mfProductInfo.addBuyMinAmt = 
                    Long.toString(l_mfProductRow.getAddBuyMinAmt());
            }

            //�P�ʋ��z�i�ǉ����t�j�����M����.get�P�ʋ��z�i�ǉ����t�j() 
            if(l_mfProductRow.getAddBuyUnitAmtIsNull())
            {
                l_mfProductInfo.addBuyUnitAmt = null;
            }
            else
            {
                l_mfProductInfo.addBuyUnitAmt = 
                    Long.toString(l_mfProductRow.getAddBuyUnitAmt());
            }
            
            //�w����@@�i���j�����M����.get�w����@@�i���j() 
            l_mfProductInfo.sellSelectable = l_mfProductRow.getSellSpecifyDiv();
            
            //�Œ�����i���j�����M����.get�Œ�����i���j()
            if(l_mfProductRow.getSellMinQtyIsNull())
            {
                l_mfProductInfo.sellMinQty = null;
            }
            else
            {
                l_mfProductInfo.sellMinQty = 
                    Long.toString(l_mfProductRow.getSellMinQty());
            }
            
            //�P�ʌ����i���j�����M����.get�P�ʌ����i���j() 
            if(l_mfProductRow.getSellUnitQtyIsNull())
            {
                l_mfProductInfo.sellUnitQty = null;
            }
            else
            {
                l_mfProductInfo.sellUnitQty = 
                    Long.toString(l_mfProductRow.getSellUnitQty());
            }
           
            //�Œ���z�i���j�����M����.get�Œ���z�i���j() 
            if(l_mfProductRow.getSellMinAmtIsNull())
            {
                l_mfProductInfo.sellMinAmt = null;
            }
            else
            {
                l_mfProductInfo.sellMinAmt = 
                    Long.toString(l_mfProductRow.getSellMinAmt());
            }
            
            //�P�ʋ��z�i���j�����M����.get�P�ʋ��z�i���j()
            if(l_mfProductRow.getSellUnitAmtIsNull())
            {
                l_mfProductInfo.sellUnitAmt = null;
            }
            else
            {
                l_mfProductInfo.sellUnitAmt = 
                    Long.toString(l_mfProductRow.getSellUnitAmt());
            }
      
            //�w����@@�i�抷�j�����M����.get�w����@@�i�抷�j() 
            l_mfProductInfo.switchingSelectable = l_mfProductRow.getSwtSpecifyDiv();
            
            //�Œ�����i�抷�j�����M����.get�Œ�����i�抷�j() 
            if(l_mfProductRow.getSwtMinQtyIsNull())
            {
                l_mfProductInfo.switchingMinQty = null;
            }
            else
            {
                l_mfProductInfo.switchingMinQty = 
                    Long.toString(l_mfProductRow.getSwtMinQty());
            }
           
            //�P�ʌ����i�抷�j�����M����.get�P�ʌ����i�抷�j()
            if(l_mfProductRow.getSwtUnitQtyIsNull())
            {
                l_mfProductInfo.switchingUnitQty = null;
            }
            else
            {
                l_mfProductInfo.switchingUnitQty = 
                    Long.toString(l_mfProductRow.getSwtUnitQty());
            }
            
            //�Œ���z�i�抷�j�����M����.get�Œ���z�i�抷�j()
            if(l_mfProductRow.getSwtMinAmtIsNull())
            {
                l_mfProductInfo.switchingMinAmt = null;
            }
            else
            {
                l_mfProductInfo.switchingMinAmt = 
                    Long.toString(l_mfProductRow.getSwtMinAmt());
            }

            //�P�ʋ��z�i�抷�j�����M����.get�P�ʋ��z�i�抷�j()
            if(l_mfProductRow.getSwtUnitAmtIsNull())
            {
                l_mfProductInfo.switchingUnitAmt = null;
            }
            else
            {
                l_mfProductInfo.switchingUnitAmt = 
                    Long.toString(l_mfProductRow.getSwtUnitAmt());
            }
            
            //�w����@@�i��W�j���g�����M����.get�w����@@�i��W�j() 
            l_mfProductInfo.applySelectable =  l_mfProductRow.getRecruitSpecityDiv();
            
            //�Œ�����i��W�j���g�����M����.get�Œ�����i��W�j() 
            if (l_mfProductRow.getRecruitMinQtyIsNull())
            {
                l_mfProductInfo.applyMinQty = null;
            }
            else
            {
                l_mfProductInfo.applyMinQty =  l_mfProductRow.getRecruitMinQty() + "";
            }

            //�P�ʌ����i��W�j���g�����M����.get�P�ʌ����i��W�j() 
            if (l_mfProductRow.getRecruitUnitQtyIsNull())
            {
                l_mfProductInfo.applyUnitQty = null;
            }
            else
            {
                l_mfProductInfo.applyUnitQty =  l_mfProductRow.getRecruitUnitQty() + "";
            }
            
            //�Œ���z�i��W�j���g�����M����.get�Œ���z�i��W�j() 
            if (l_mfProductRow.getRecruitMinAmtIsNull())
            {
                l_mfProductInfo.applyMinAmt = null;
            }
            else
            {
                l_mfProductInfo.applyMinAmt =  l_mfProductRow.getRecruitMinAmt() + "";
            }
            
            //�P�ʋ��z�i��W�j���g�����M����.get�P�ʋ��z�i��W�j() 
            if (l_mfProductRow.getRecruitUnitAmtIsNull())
            {
                l_mfProductInfo.applyUnitAmt = null;
            }
            else
            {
                l_mfProductInfo.applyUnitAmt = l_mfProductRow.getRecruitUnitAmt() + "";
            }

            //���t�����敪���g�����M����.getDataSourceObject().get���t�����敪() 
            l_mfProductInfo.buyRestrictionDiv = l_mfProductRow.getBuyLimitDiv();
            
            //��n���@@���g�����M����.get��n���@@() 
            l_mfProductInfo.deliveryVariation = l_mfProductRow.getDeliveryMethod();
            
            //�������������敪���g�����M����.getDataSourceObject().get�������������敪()
            l_mfProductInfo.unitTypeProductDiv = l_mfProductRow.getUnitTypeProductDiv();

            //�O�ݍŒ���z�i�V�K���t�j���g�����M����.get�O�ݍŒ���z�i�V�K���t�j
            if (l_mfProductRow.getFrgnNewBuyMinAmtIsNull())
            {
                l_mfProductInfo.frgnMinAmtBuy = null;
            }
            else
            {
                l_mfProductInfo.frgnMinAmtBuy = l_mfProductRow.getFrgnNewBuyMinAmt() + "";
            }

            //�O�ݒP�ʋ��z�i�V�K���t�j���g�����M����.get�O�ݒP�ʋ��z�i�V�K���t�j
            if (l_mfProductRow.getFrgnNewBuyUnitAmtIsNull())
            {
                l_mfProductInfo.frgnUnitAmtBuy = null;
            }
            else
            {
                l_mfProductInfo.frgnUnitAmtBuy = l_mfProductRow.getFrgnNewBuyUnitAmt() + "";
            }

            //�O�ݍŒ���z�i�ǉ����t�j���g�����M����.get�O�ݍŒ���z�i�ǉ����t�j
            if (l_mfProductRow.getFrgnAddBuyMinAmtIsNull())
            {
                l_mfProductInfo.frgnMinAmtAdd = null;
            }
            else
            {
                l_mfProductInfo.frgnMinAmtAdd = l_mfProductRow.getFrgnAddBuyMinAmt() + "";
            }

            //�O�ݒP�ʋ��z�i�ǉ����t�j���g�����M����.get�O�ݒP�ʋ��z�i�ǉ����t�j
            if (l_mfProductRow.getFrgnAddBuyUnitAmtIsNull())
            {
                l_mfProductInfo.frgnUnitAmtAdd = null;
            }
            else
            {
                l_mfProductInfo.frgnUnitAmtAdd = l_mfProductRow.getFrgnAddBuyUnitAmt() + "";
            }

            //�O�ݍŒ���z�i���j    ���g�����M����.get�O�ݍŒ���z�i���j
            if (l_mfProductRow.getFrgnSellMinAmtIsNull())
            {
                l_mfProductInfo.frgnMinAmtSell = null;
            }
            else
            {
                l_mfProductInfo.frgnMinAmtSell = l_mfProductRow.getFrgnSellMinAmt() + "";
            }

            //�O�ݒP�ʋ��z�i���j    ���g�����M����.get�O�ݒP�ʋ��z�i���
            if (l_mfProductRow.getFrgnSellUnitAmtIsNull())
            {
                l_mfProductInfo.frgnUnitAmtSell = null;
            }
            else
            {
                l_mfProductInfo.frgnUnitAmtSell = l_mfProductRow.getFrgnSellUnitAmt() + "";
            }

            //��W�萔���敪           ���g�����M����.get��W�萔���敪
            l_mfProductInfo.applyCommissionDiv = l_mfProductRow.getRecruitCommissionDiv();

            //--------------- ����\�敪�i�����������^���c�Ɠ��������j ------------
            //�����ݓ��t�̎擾 
            //�|GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l�����ݓ����Ƃ���B
            Timestamp l_datSystemDate = 
                GtlUtils.getTradingSystem().getSystemTimestamp();
            
            //�|�擾�������ݓ��t�������ɁAthis.is�����c�Ɠ�()���R�[������B    
            boolean l_blnIsCurBizDate = this.isCalendarBizDate(l_datSystemDate);
            
            //���C�O�s��̋x�����l�������������̎擾
            //get���M������()���R�[������B         
            Date l_datMfBizDate = 
                WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
                                
            //�����c�Ɠ����̎�������I�u�W�F�N�g�̎擾 
            //�|�g�����M�����}�l�[�W��.get���M�������()���R�[�����A��������I�u�W�F�N�g���擾����B 
            //[get���M��������ɓn������] 
            //�E�،���Ё��Ǘ��҃I�u�W�F�N�g.get�،����()�̖߂�l 
            //�E�����R�[�h�����M����.get�����R�[�h()�̖߂�l 
            WEB3MutualFundTradedProduct l_tradedProductTodayOrder =
                (WEB3MutualFundTradedProduct)l_mfProductManager.getMutualFundTradedProduct(
                    l_admin.getInstitution(),
                    l_mfProduct.getProductCode());
           
            MutualFundTradedProductRow l_mfTradedProductRow = 
                (MutualFundTradedProductRow)l_tradedProductTodayOrder
                        .getDataSourceObject();
                
            //���擾������������I�u�W�F�N�g�́u�L���r�W�l�X���t�v���擾
            String l_strValidForBizDate = l_mfTradedProductRow.getValidForBizDate();
                        
            //�|�擾�����L���r�W�l�X���t�������ɁAthis.is�c�Ɠ�()���R�[������B
            Date l_dateValidForBizDate = 
                    WEB3DateUtility.getDate(l_strValidForBizDate,"yyyyMMdd");
            boolean l_blnIsBizDate = 
                this.isBizDate(
                        l_admin.getInstitutionCode(),
                        l_mfProduct.getProductCode(),
                        new Timestamp (l_dateValidForBizDate.getTime()));
            
            // ���c�Ɠ����̓��M��������I�u�W�F�N�g
            WEB3MutualFundTradedProduct l_tradedProductNextDayOrder = null;

            //��is�����c�Ɠ���true ���Ais�c�Ɠ�()��true�̏ꍇ 
            if(l_blnIsCurBizDate && l_blnIsBizDate)
            {
                log.debug("���ݓ��t�������c�Ɠ����A���������������M�c�Ɠ�");
                //����������������̗��c�Ɠ����擾   
                //�|�������������擾���� 
                Date l_datBizDate = 
                    WEB3GentradeTradingTimeManagement.getOrderBizDate();
                    
                //�|�c�Ɠ��v�Z�I�u�W�F�N�g.roll()���R�[������B 
                //�@@[roll�ɓn������] 
                //�@@�@@���Z�^���Z�������P 
                Timestamp l_datNextBizDate = new WEB3GentradeBizDate(
                        new Timestamp(l_datBizDate.getTime())).roll(1);
                   
                //�����c�Ɠ����̎������UPDQ�I�u�W�F�N�g�̎擾 
                //�|���M��������ꎞ�e�[�u������������B 
                //�@@[��������] 
                //�@@�@@�������ID���������A�I�u�W�F�N�g.get�������ID() and 
                //�@@�@@�L���r�W�l�X���t��roll()�̖߂�l 
                //�|��������!=1���̏ꍇ�A[�f�[�^�s����]�Ƃ��ė�O���X���[����B
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                String l_strWhere = "traded_product_id = ? and valid_for_biz_date = ?";
                Object[] l_objWhereValues = {
                    new Long(l_tradedProductTodayOrder.getTradedProductId()),
                    WEB3DateUtility.formatDate(l_datNextBizDate, "yyyyMMdd") };
                    
                List l_lisTradedProductNextDay = l_processor.doFindAllQuery(
                        MfTradedProductUpdqRow.TYPE,
                        l_strWhere,
                        null,
                        l_objWhereValues); 
                    
                //�|��������!=1���̏ꍇ�A[�f�[�^�s����]�Ƃ��ė�O���X���[����B
                if(l_lisTradedProductNextDay == null || l_lisTradedProductNextDay.size() != 1)
                {
                    log.debug("�f�[�^�s�����G���[!"); 
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + l_strMethodName);
                }
                MutualFundTradedProductParams l_mfTradedProductBParams = 
                    new MutualFundTradedProductParams();
                GtlUtils.copyRow2Params((Row)l_lisTradedProductNextDay.get(0), l_mfTradedProductBParams);
                Row l_mfTradedProductBRow = l_mfTradedProductBParams;
                l_tradedProductNextDayOrder = 
                     (WEB3MutualFundTradedProduct)l_mfProductManager.toTradedProduct(
                          l_mfTradedProductBRow);
                
                // set values-----------------
                
                //�E���t�\�敪�i�����������j�������������̓��M��������I�u�W�F�N�g.is���t�\()
                //  �߂�l�� true �̏ꍇ�h�h���Z�b�g�Afalse �̏ꍇ�h�s�h���Z�b�g����B
                l_mfProductInfo.todayBuyPossDiv = WEB3BuyPossibleDivDef.NOT_ACQUIRED;
                if (l_tradedProductTodayOrder.isAcquiredPossible())
                {
                    l_mfProductInfo.todayBuyPossDiv = WEB3BuyPossibleDivDef.ACQUIRED;
                }
                
                //�E���\�敪�i�����������j�������������̓��M��������I�u�W�F�N�g.is���\()
                //  �߂�l�� true �̏ꍇ�h�h���Z�b�g�Afalse �̏ꍇ�h�s�h���Z�b�g����B
                l_mfProductInfo.todaySellPossDiv = WEB3SellPossibleDivDef.NOT_SELL;
                if (l_tradedProductTodayOrder.isSellPossible())
                {
                    l_mfProductInfo.todaySellPossDiv = WEB3SellPossibleDivDef.SELL;
                }
                
                //�E�抷�\�敪�i�����������j�������������̓��M��������I�u�W�F�N�g.is�抷�\()
                //�@@�߂�l�� true �̏ꍇ�h�h���Z�b�g�Afalse �̏ꍇ�h�s�h���Z�b�g����B
                l_mfProductInfo.todaySwitchingPossDiv = WEB3SwtPossibleDivDef.NOT_SWITCHING;
                if (l_tradedProductTodayOrder.isSwitchingPossible())
                {
                    l_mfProductInfo.todaySwitchingPossDiv = WEB3SwtPossibleDivDef.SWITCHING;
                }
                
                //��W�\�敪�i�����������j���������A�I�u�W�F�N�g.is��W�\() (*) 
                //�߂�l�� true �̏ꍇ�h�h���Z�b�g�Afalse �̏ꍇ�h�s�h���Z�b�g����B
                l_mfProductInfo.todayApplyPossDiv = WEB3MFRecruitPossibleDivDef.NOT_RECRUIT;
                if (l_tradedProductTodayOrder.isRecruitPossible())
                {
                    l_mfProductInfo.todayApplyPossDiv = WEB3MFRecruitPossibleDivDef.RECRUIT;
                }

                //�E���t�\�敪�i�����������j�����c�Ɠ��������̓��M��������I�u�W�F�N�g.is���t�\()
                //�@@�߂�l�� true �̏ꍇ�h�h���Z�b�g�Afalse �̏ꍇ�h�s�h���Z�b�g����B
                l_mfProductInfo.nextDayBuyPossDiv = WEB3BuyPossibleDivDef.NOT_ACQUIRED;
                if (l_tradedProductNextDayOrder.isAcquiredPossible())
                {
                    l_mfProductInfo.nextDayBuyPossDiv = WEB3BuyPossibleDivDef.ACQUIRED;
                }
                
                //�E���\�敪�i�����������j�����c�Ɠ��������̓��M��������I�u�W�F�N�g.is���\()
                //�@@�߂�l�� true �̏ꍇ�h�h���Z�b�g�Afalse �̏ꍇ�h�s�h���Z�b�g����B
                l_mfProductInfo.nextDaySellPossDiv = WEB3SellPossibleDivDef.NOT_SELL;
                if (l_tradedProductNextDayOrder.isSellPossible())
                {
                    l_mfProductInfo.nextDaySellPossDiv = WEB3SellPossibleDivDef.SELL;
                }
                
                //�E�抷�\�敪�i�����������j�����c�Ɠ��������̓��M��������I�u�W�F�N�g.is�抷�\() 
                //�@@�߂�l�� true �̏ꍇ�h�h���Z�b�g�Afalse �̏ꍇ�h�s�h���Z�b�g����B
                l_mfProductInfo.nextDaySwitchingPossDiv = WEB3SwtPossibleDivDef.NOT_SWITCHING;
                if (l_tradedProductNextDayOrder.isSwitchingPossible())
                {
                    l_mfProductInfo.nextDaySwitchingPossDiv = WEB3SwtPossibleDivDef.SWITCHING;
                }
                
                //��W�\�敪�i�����������j���������B�I�u�W�F�N�g.is��W�\() (*)
                //�߂�l�� true �̏ꍇ�h�h���Z�b�g�Afalse �̏ꍇ�h�s�h���Z�b�g����B
                l_mfProductInfo.nextDayApplyPossDiv = WEB3MFRecruitPossibleDivDef.NOT_RECRUIT;
                if (l_tradedProductNextDayOrder.isRecruitPossible())
                {
                    l_mfProductInfo.nextDayApplyPossDiv = WEB3MFRecruitPossibleDivDef.RECRUIT;
                }
                
                //�@@���ݓ�����̔��������Z�b�g����B
                l_response.curBizDate = l_datMfBizDate;
                
                //�@@���ݓ�����̗��c�Ɠ������M������ԊǗ�.get���M���c�Ɠ�()�̖߂�l���Z�b�g����B
                l_response.nextBizDate = 
                    WEB3MutualFundTradingTimeManagement.getMutualNextOrderBizDate(
                        l_admin.getInstitutionCode(),
                        l_mfProduct.getProductCode());
                
            }
            
            //��is�����c�Ɠ���false�@@�܂��́@@is�c�Ɠ�()��false�̏ꍇ
            else if(!l_blnIsCurBizDate || !l_blnIsBizDate)
            {
                log.debug("���ݓ��t�������c�Ɠ��܂��́A���������������M�c�Ɠ�");
                // �E�擾������������I�u�W�F�N�g���h���c�Ɠ��������h�Ɣ��肷��B
                l_tradedProductNextDayOrder = l_tradedProductTodayOrder;

                //�E���t�\�敪�i�����������j�������������̓��M��������I�u�W�F�N�g.is���t�\()
                //�@@is�c�Ɠ�()��false �̏ꍇ�A�S�Ă� null ���Z�b�g����B
                l_mfProductInfo.todayBuyPossDiv = null;
                //�E���\�敪�i�����������j�������������̓��M��������I�u�W�F�N�g.is���\()
                //�@@is�c�Ɠ�()��false �̏ꍇ�A�S�Ă� null ���Z�b�g����B
                l_mfProductInfo.todaySellPossDiv = null;
                //�E�抷�\�敪�i�����������j�������������̓��M��������I�u�W�F�N�g.is�抷�\()
                //�@@is�c�Ɠ�()��false �̏ꍇ�A�S�Ă� null ���Z�b�g����B
                l_mfProductInfo.todaySwitchingPossDiv = null;
                //��W�\�敪�i�����������j��null 
                l_mfProductInfo.todayApplyPossDiv = null;

                //�E���t�\�敪�i�����������j�����c�Ɠ��������̓��M��������I�u�W�F�N�g.is���t�\()
                //�@@�߂�l�� true �̏ꍇ�h�h���Z�b�g�Afalse �̏ꍇ�h�s�h���Z�b�g����B
                l_mfProductInfo.nextDayBuyPossDiv = WEB3BuyPossibleDivDef.NOT_ACQUIRED;
                if (l_tradedProductNextDayOrder.isAcquiredPossible())
                {
                    l_mfProductInfo.nextDayBuyPossDiv = WEB3BuyPossibleDivDef.ACQUIRED;
                }
                //�E���\�敪�i�����������j�����c�Ɠ��������̓��M��������I�u�W�F�N�g.is���\()
                //�@@�߂�l�� true �̏ꍇ�h�h���Z�b�g�Afalse �̏ꍇ�h�s�h���Z�b�g����B
                l_mfProductInfo.nextDaySellPossDiv = WEB3SellPossibleDivDef.NOT_SELL;
                if (l_tradedProductNextDayOrder.isSellPossible())
                {
                    l_mfProductInfo.nextDaySellPossDiv = WEB3SellPossibleDivDef.SELL;
                }
                //�E�抷�\�敪�i�����������j�����c�Ɠ��������̓��M��������I�u�W�F�N�g.is�抷�\() 
                //�@@�߂�l�� true �̏ꍇ�h�h���Z�b�g�Afalse �̏ꍇ�h�s�h���Z�b�g����B
                l_mfProductInfo.nextDaySwitchingPossDiv = WEB3SwtPossibleDivDef.NOT_SWITCHING;
                if (l_tradedProductNextDayOrder.isSwitchingPossible())
                {
                    l_mfProductInfo.nextDaySwitchingPossDiv = WEB3SwtPossibleDivDef.SWITCHING;
                }
                //��W�\�敪�i�����������j���������A�I�u�W�F�N�g.is��W�\() (*) 
                //(*) �߂�l�� true �̏ꍇ�g�h���Z�b�g�Afalse �̏ꍇ�g�s�h���Z�b�g����B
                l_mfProductInfo.nextDayApplyPossDiv = WEB3MFRecruitPossibleDivDef.NOT_RECRUIT;
                if (l_tradedProductNextDayOrder.isRecruitPossible())
                {
                    l_mfProductInfo.nextDayApplyPossDiv = WEB3MFRecruitPossibleDivDef.RECRUIT;
                }

                //�Eis�c�Ɠ���false�̏ꍇ�A���ݓ�����̔�������null ���Z�b�g����B
                l_response.curBizDate = null;
                
                //�Eis�c�Ɠ���false�̏ꍇ�A���ݓ�����̗��c�Ɠ���get.���M������()�̖߂�l���Z�b�g����B
                l_response.nextBizDate = l_datMfBizDate; 
            }
            
            // ------------ ����\�敪�i�����������^���c�Ɠ��������j ------------
            
            //�@@�E�I�y���[�V�������t���擾�������ݓ��t 
            l_mfProductInfo.operationDate = l_datSystemDate;
            
            // -------------- ������t���؎��ԁi�����j ------------------ 
            
            //��������ԃe�[�u���v���ȉ��̏����Ō�������B 
            //�@@[��������] 
            //�@@�@@�،���ЃR�[�h�����M����.get�،���ЃR�[�h() and 
            //�@@�@@��t���ԋ敪���h�����M���h 
            //�@@�@@���i�R�[�h�����M����.get�����R�[�h and 
            //�@@�@@�c�Ɠ��敪���h�I���c�Ɠ��h and 
            //�@@�@@�s��g���K���s���hSONAR��MQ�g���K�����{���Ȃ��h and 
            //�@@�@@��t�\���h��t�s�h and 
            //�@@�@@�������v�Z���h���c�Ɠ��h 
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            
            String l_whereClause = 
                " institution_code = ? and " +
                " trading_time_type = ? and " +
                " product_code = ? and " +
                " biz_date_type = ? and " +
                " submit_market_trigger = ? and " +
                " enable_order = ? and " +
                " bizdate_calc_parameter = ? ";

            Object l_bindVars[] = {
                l_mfProduct.getInstitution().getInstitutionCode(),
                WEB3TradingTimeTypeDef.MUTUAL_FUND,
                l_mfProduct.getProductCode(),
                WEB3BizDateTypeDef.BIZ_DATE,
                WEB3SubmitMarketTriggerDef.NOT_TRIGGER,
                WEB3EnableOrderDef.DISABLED,
                WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE };
            
            // test log
            log.debug("l_whereClause = " + l_whereClause);
            for (int f = 0; f < l_bindVars.length; f++)
            {
                log.debug("l_bindVars[" + f + "] = " + l_bindVars[f]);
            }
            
            List l_lisTimeFullTradingTimeRows =
                l_processor.doFindAllQuery(
                    TradingTimeRow.TYPE,
                    l_whereClause,
                    null,
                    l_bindVars);            
            
            if(l_lisTimeFullTradingTimeRows == null || l_lisTimeFullTradingTimeRows.size() == 0)
            {
                //�|�������ʂ̌���=0���̏ꍇ�A�i�f�[�^�s�����j�̗�O���X���[����B
                log.debug("�f�[�^�s�����G���[!"); 
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + l_strMethodName
                );
            }
            //�|�������ʂ̂P���ڂ��擾���A������ԃe�[�u��Row�ŃL���X�g����B 
            TradingTimeRow l_tradingTimeRowTimeFull = (TradingTimeRow) l_lisTimeFullTradingTimeRows.get(0);

            //�|�쐬����������ԃe�[�u��Row�����ƂɁA�ȉ��̃v���p�e�B���Z�b�g����B 
            //�E�������؊J�n���ԁi�����j��������ԃe�[�u��Row.get�J�n����()�̐擪����S�� 
            if(l_tradingTimeRowTimeFull.getStartTime().compareTo(l_tradingTimeRowTimeFull.getEndTime()) == 0)
            {
                Date l_datStartTimeFull = 
                    WEB3DateUtility.getDate(l_tradingTimeRowTimeFull.getStartTime(), "HHmmss");
                l_datStartTimeFull = WEB3DateUtility.addSecond(l_datStartTimeFull, 1L);                    
                l_mfProductInfo.orderCloseStartTimeFull = 
                    WEB3DateUtility.formatDate(l_datStartTimeFull, "HHmmss").substring(0, 4);
            }
            else
            {
                l_mfProductInfo.orderCloseStartTimeFull = 
                    l_tradingTimeRowTimeFull.getStartTime().substring(0, 4);
            }

            //�E�������؏I�����ԁi�����j��������ԃe�[�u��Row.get�I������()�̂P�b��̎��Ԃ̐擪����S��(*2)           
            Date l_datEndTimeFull = 
                WEB3DateUtility.getDate(l_tradingTimeRowTimeFull.getEndTime(), "HHmmss");
            l_datEndTimeFull = WEB3DateUtility.addSecond(l_datEndTimeFull, 1L);                    
            l_mfProductInfo.orderCloseEndTimeFull = 
                WEB3DateUtility.formatDate(l_datEndTimeFull, "HHmmss").substring(0, 4);
            
            //------------- ������t���؎��ԁi�����j ------------------
            
            // ------------- ������t���؎��ԁi�����j ------------------ 
            //��������ԃe�[�u���v���ȉ��̏����Ō�������B 
            //�@@[��������] 
            //�@@�@@�،���ЃR�[�h�����M����.get�،���ЃR�[�h() and 
            //�@@�@@��t���ԋ敪���h�����M���h 
            //�@@�@@���i�R�[�h�����M����.get�����R�[�h and 
            //�@@�@@�c�Ɠ��敪���h�����i�ߑO�̂݁j�h and 
            //�@@�@@�s��g���K���s���hSONAR��MQ�g���K�����{���Ȃ��h and 
            //�@@�@@��t�\���h��t�s�h and 
            //�@@�@@�������v�Z���h���c�Ɠ��h 
           
            String l_whereClauseHalf = 
                " institution_code = ? and " +
                " trading_time_type = ? and " +
                " product_code = ? and " +
                " biz_date_type = ? and " +
                " submit_market_trigger = ? and " +
                " enable_order = ? and " +
                " bizdate_calc_parameter = ? ";

            Object l_bindVarsHalf[] = {
                    l_mfProduct.getInstitution().getInstitutionCode(),
                    WEB3TradingTimeTypeDef.MUTUAL_FUND,
                    l_mfProduct.getProductCode(),
                    WEB3BizDateTypeDef.BIZ_DATE_AM,
                    WEB3SubmitMarketTriggerDef.NOT_TRIGGER,
                    WEB3EnableOrderDef.DISABLED,
                    WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE };

            // test log
            log.debug("l_whereClauseHalf = " + l_whereClause);
            for (int f = 0; f < l_bindVars.length; f++)
            {
                log.debug("l_bindVarsHalf[" + f + "] = " + l_bindVarsHalf[f]);
            }
            
            List l_lisTimeHalfTradingTimeRows =
                l_processor.doFindAllQuery(
                    TradingTimeRow.TYPE,
                    l_whereClauseHalf,
                    null,
                    l_bindVarsHalf);            
            
            if(l_lisTimeHalfTradingTimeRows == null || l_lisTimeHalfTradingTimeRows.size() == 0)
            {
                //�|�������ʂ̌���=0���̏ꍇ�A�i�f�[�^�s�����j�̗�O���X���[����B
                log.debug("�f�[�^�s�����G���[!"); 
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + l_strMethodName
                );
            }
            else
            {
                //�|�������ʂ̂P���ڂ��擾���A������ԃe�[�u��Row�ŃL���X�g����B 
                //�|��������ƂɁA�ȉ��̃v���p�e�B���Z�b�g����B 
                //�E�������؊J�n���ԁi�����j��������ԃe�[�u��Row.get�J�n����()�̐擪����S�� 
                //�E�������؏I�����ԁi�����j��������ԃe�[�u��Row.get�I������()�̂P�b��̎��Ԃ̐擪����S��(*2) 
                TradingTimeRow l_tradingTimeRowTimeHalf = (TradingTimeRow) l_lisTimeHalfTradingTimeRows.get(0);
                
                if(l_tradingTimeRowTimeHalf.getStartTime().compareTo(l_tradingTimeRowTimeHalf.getEndTime()) == 0)
                {
                    Date l_datStartTimeHalf = 
                        WEB3DateUtility.getDate(l_tradingTimeRowTimeHalf.getStartTime(), "HHmmss");
                    l_datStartTimeHalf = WEB3DateUtility.addSecond(l_datStartTimeHalf, 1L);                    
                    l_mfProductInfo.orderCloseStartTimeHalf = 
                        WEB3DateUtility.formatDate(l_datStartTimeHalf, "HHmmss").substring(0, 4);
                }
                else
                {
                    l_mfProductInfo.orderCloseStartTimeHalf = 
                        l_tradingTimeRowTimeHalf.getStartTime().substring(0, 4);
                }

                Date l_datEndTimeHalf = 
                    WEB3DateUtility.getDate(l_tradingTimeRowTimeHalf.getEndTime(), "HHmmss");
                l_datEndTimeHalf = WEB3DateUtility.addSecond(l_datEndTimeHalf, 1L);                    
                
                l_mfProductInfo.orderCloseEndTimeHalf = 
                    WEB3DateUtility.formatDate(l_datEndTimeHalf, "HHmmss").substring(0, 4);
            }

            // ----------------- ������t���؎��ԁi�����j ------------------
            
            //���擾�������X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B 
            
            //�@@���M��������R�[�h�����M����.getDataSourceObject().get���M��������R�[�h() 
            l_response.mutualAssocProductCode = 
                l_mfProductRow.getMutualassocProductCode();
            
            //�@@�ŏI�X�V�҃R�[�h�����M����.getDataSourceObject().get�ŏI�X�V��() 
            l_response.lastUpdaterCode = 
                l_mfProductRow.getLastUpdater();
            
            //  �ŏI�X�V�������g�����M����.getDataSourceObject().get�X�V���t�i�I�����C���j() 
            l_response.lastUpdateTime = 
                l_mfProductRow.getOnlineUpdatedTimestamp();
            
            //�@@�ݒ�������M����.get�ݒ��() 
            l_response.settingDate = 
                WEB3DateUtility.toDay(l_mfProductRow.getSettingDate());
            
            //�@@���ғ������M����.get���ғ�() 
            l_response.refundDate = 
                WEB3DateUtility.toDay(l_mfProductRow.getRedemptionDate());
            
            //�@@�����֓������M����.get�����֓�() 
            l_response.removalOfNotSell = 
                WEB3DateUtility.toDay(l_mfProductRow.getSellBanDate());

            //-- �w����@@�ꗗ�i���t�j -------------------------- 
            //�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get���t���z�w��敪()=="1:�\"�ł���A 
            //�@@�����M��2�����}�X�^Row�I�u�W�F�N�g.get���t�����w��敪()=="0:�s��"�̏ꍇ 
            //�@@"3:���z"�ňꗗ���쐬����B 
            
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getBuyAmtSpecDiv()) 
                && WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getBuyQtySpecDiv()))
            {
                String[] l_strBuySelectableList = {WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE};
                l_response.buySelectableList = l_strBuySelectableList;
            }
            //�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get���t���z�w��敪()=="0:�s��"�ł���A 
            //�@@�����M��2�����}�X�^Row�I�u�W�F�N�g.get���t�����w��敪()=="1:�\"�̏ꍇ 
            //�@@"4:����"�ňꗗ���쐬����B 
            if (WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getBuyAmtSpecDiv()) 
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getBuyQtySpecDiv()))
            {
                String[] l_strBuySelectableList = 
                    {WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE};
                l_response.buySelectableList = l_strBuySelectableList;
            }
            //�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get���t�����w��敪()=="1:�\"�ł���A 
            //�@@�����M��2�����}�X�^Row�I�u�W�F�N�g.get���t���z�w��敪()=="1:�\"�̏ꍇ 
            //�@@"0:�I���w��"�A"3:���z"�A"4:����"�ňꗗ���쐬����B 
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getBuyAmtSpecDiv()) 
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getBuyQtySpecDiv()))
            {
                String[] l_strBuySelectableList = 
                    {
                        WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE,
                        WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE,
                        WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE
                    };
                l_response.buySelectableList = l_strBuySelectableList;
            }

            //-- �w����@@�ꗗ�i���j ---------------------- 
            //�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�����z�w��敪()=="1:�\"�ł���A 
            //�@@�����M��2�����}�X�^Row�I�u�W�F�N�g.get�������w��敪()=="0:�s��"�̏ꍇ 
            //�@@"3:���z"�ňꗗ���쐬����B 
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSellAmtSpecDiv()) 
                && WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getSellQtySpecDiv()))
            {
                String[] l_strSellSelectableList = {WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE};
                l_response.sellSelectableList = l_strSellSelectableList;
            }
            //�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�����z�w��敪()=="0:�s��"�ł���A 
            //�@@�����M��2�����}�X�^Row�I�u�W�F�N�g.get�������w��敪()=="1:�\"�̏ꍇ 
            //�@@"4:����"�ňꗗ���쐬����B 
            if (WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getSellAmtSpecDiv()) 
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSellQtySpecDiv()))
            {
                String[] l_strSellSelectableList = {WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE};
                l_response.sellSelectableList = l_strSellSelectableList;
            }
            //�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�������w��敪()=="1:�\"�ł���A 
            //�@@�����M��2�����}�X�^Row�I�u�W�F�N�g.get�����z�w��敪()=="1:�\"�̏ꍇ 
            //�@@"0:�I���w��"�A"3:���z"�A"4:����"�ňꗗ���쐬����B 
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSellAmtSpecDiv()) 
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSellQtySpecDiv()))
            {
                String[] l_strSellSelectableList = 
                    {
                        WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE,
                        WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE,
                        WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE
                    };
                l_response.sellSelectableList = l_strSellSelectableList;
            }

            //-- �w����@@�ꗗ�i�抷�j ---------------------- 
            //�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�抷���z�w��敪()=="1:�\"�ł���A 
            //�@@�����M��2�����}�X�^Row�I�u�W�F�N�g.get�抷�����w��敪()=="0:�s��"�̏ꍇ 
            //�@@"3:���z"�ňꗗ���쐬����B 
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSwtAmtSpecDiv()) 
                && WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getSwtQtySpecDiv()))
            {
                String[] l_strSwtSelectableList = {WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE};
                l_response.switchingSelectableList = l_strSwtSelectableList;
            }
            //�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�抷���z�w��敪()=="0:�s��"�ł���A 
            //�@@�����M��2�����}�X�^Row�I�u�W�F�N�g.get�抷�����w��敪()=="1:�\"�̏ꍇ 
            //�@@"4:����"�ňꗗ���쐬����B 
            if (WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getSwtAmtSpecDiv()) 
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSwtQtySpecDiv()))
            {
                String[] l_strSwtSelectableList = {WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE};
                l_response.switchingSelectableList = l_strSwtSelectableList;
            }
            //�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�抷�����w��敪()=="1:�\"�ł���A 
            //�@@�����M��2�����}�X�^Row�I�u�W�F�N�g.get�抷���z�w��敪()=="1:�\"�̏ꍇ 
            //�@@"0:�I���w��"�A"3:���z"�A"4:����"�ňꗗ���쐬����B 
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSwtAmtSpecDiv()) 
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSwtQtySpecDiv()))
            {
                String[] l_strSwtSelectableList = 
                    {
                        WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE,
                        WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE,
                        WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE
                    };
                l_response.switchingSelectableList = l_strSwtSelectableList;
            }

            //-- �w����@@�ꗗ�i��W�j ---------------------- 
            //(*) ���M��2�����}�X�^Row�I�u�W�F�N�g.get��W���z�w��敪()=="1:�\"�ł���A 
            //�����M��2�����}�X�^Row�I�u�W�F�N�g.get��W�����w��敪()=="0:�s��"�̏ꍇ 
            //"3:���z"�ňꗗ���쐬����B
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getRecruitAmtSpecDiv())
                && WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getRecruitQtySpecDiv()))
            {
                String[] l_strApplySelectableList = {WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE};
                l_response.applySelectableList = l_strApplySelectableList;
            }
            
            //(*) ���M��2�����}�X�^Row�I�u�W�F�N�g.get��W���z�w��敪()=="0:�s��"�ł���A 
            //�����M��2�����}�X�^Row�I�u�W�F�N�g.get��W�����w��敪()=="1:�\"�̏ꍇ 
            //"4:����"�ňꗗ���쐬����B 
            if (WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getRecruitAmtSpecDiv())
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getRecruitQtySpecDiv()))
            {
                String[] l_strApplySelectableList = {WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE};
                l_response.applySelectableList = l_strApplySelectableList;
            }
            
            //(*) ���M��2�����}�X�^Row�I�u�W�F�N�g.get��W�����w��敪()=="1:�\"�ł���A 
            //�����M��2�����}�X�^Row�I�u�W�F�N�g.get��W���z�w��敪()=="1:�\"�̏ꍇ 
            //"0:�I���w��"�A"3:���z"�A"4:����"�ňꗗ���쐬����B 
            
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getRecruitAmtSpecDiv())
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getRecruitQtySpecDiv()))
            {
                String[] l_strApplySelectableList = {
                    WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE,
                    WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE,
                    WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE};
                l_response.applySelectableList = l_strApplySelectableList;
            }
            //-- �w����@@�ꗗ�i��W�j ---------------------- 
            
            //��������񁁐����������M���������o�^���ʏ��I�u�W�F�N�g
            l_response.mutualProductInfo = l_mfProductInfo;

            //�O��MMF�t���O���g�����M����.is�O��MMF()�̖߂�l
            l_response.frgnMmfFlag = l_mfProduct.isFrgnMmf();

            //�ʉ݃R�[�h���g�����M����.get�ʉ݃R�[�h()�̖߂�l
            l_response.currencyCode = l_mfProduct.getCurrencyCode();

        }
        catch (NotFoundException l_ex)
        {
            log.error("�Y������،���ЃI�u�W�F�N�g�܂��͓��M�����I�u�W�F�N�g������܂���!"); 
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���! ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���! ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        return l_response;        
    }
    
    /**
     * (submit���������o�^)<BR>
     * ���M���������o�^�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u���M�i�Ǘ��ҁj���������o�^�v�Q��<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E3F14000E6
     */
    protected WEB3AdminMutualConditionsCompleteResponse submitProductConditionsRegist(
        WEB3AdminMutualConditionsCompleteRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "submitProductConditionsRegist(" 
            + "WEB3AdminMutualConditionsCompleteResponse l_request)";
        log.entering(l_strMethodName);
        
        //�����̓`�F�b�N 
        //�@@����.���N�G�X�g�f�[�^.validate()���R�[������B 
        l_request.validate();
        
        //���Ǘ��Ҍ����`�F�b�N 
        //�@@�Ǘ��҃I�u�W�F�N�g.getInstanceFrom���O�C�����( )���R�[������B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //�@@�Ǘ��҃I�u�W�F�N�g.validate����( )���R�[������B 
        //�@@�@@[validate����()�Ɏw�肷�����] 
        //�@@�@@�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���M�i�J�����_�[�Ǘ��j 
        //�@@�@@is�X�V�F�@@true
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS,
                true);
        
        //���Ǘ��҂̃p�X���[�h�`�F�b�N���s���B 
        //�@@�Ǘ��҃I�u�W�F�N�g.validate�p�X���[�h���R�[������B         
        l_admin.validateTradingPassword(l_request.password);
        
        //���I�y���[�V�������t�`�F�b�N
        //�@@���ݓ��t�擾
        //�@@GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l 
        Timestamp l_datSystemDate = 
            GtlUtils.getTradingSystem().getSystemTimestamp();
            
        log.debug("���ݓ��t" + l_datSystemDate);
        
        //�@@���N�G�X�g�f�[�^.�������.�I�y���[�V�������t!=���ݓ��t�̏ꍇ�A 
        //�@@��O���X���[����B
        Date l_datOperationDate = l_request.mutualProductInfo.operationDate;
        if(WEB3DateUtility.compareToDay(l_datSystemDate,l_datOperationDate) != 0)
        {
            log.debug("�������̃I�y���[�V�������t�͌��ݓ��t�ł͂���܂���B ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01353,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        
        //���،���ЃI�u�W�F�N�g�̎擾 
        //�@@�Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h���R�[������B 
        Institution l_institution = l_admin.getInstitution();
        
        //�����M�����������e�I�u�W�F�N�g�̐����ƒl�̐ݒ� 
        WEB3MutualProductConditionsCommonInfo l_mfInfo = 
            l_request.mutualProductInfo;
        
        WEB3MutualFundProductCondSpec l_mfProductCondSpec = new WEB3MutualFundProductCondSpec();
        l_mfProductCondSpec.setMutualProductCode(l_mfInfo.mutualProductCode);
        l_mfProductCondSpec.setBuyStartDate(l_mfInfo.buyStartDate);
        l_mfProductCondSpec.setBuyEndDate(l_mfInfo.buyEndDate);
        l_mfProductCondSpec.setSellSwitchingStartDate(l_mfInfo.sellSwitchingStartDate);
        l_mfProductCondSpec.setSellSwitchingEndDate(l_mfInfo.sellSwitchingEndDate);
        l_mfProductCondSpec.setBuyClaimStartDate(l_mfInfo.buyClaimStartDate);
        l_mfProductCondSpec.setBuyClaimEndDate(l_mfInfo.buyClaimEndDate);
        l_mfProductCondSpec.setBuySelectable(l_mfInfo.buySelectable);
        
        //�E��W�J�n�������N�G�X�g�f�[�^.�������.��W�J�n��
        l_mfProductCondSpec.setApplyAbleStartDate(l_mfInfo.applyAbleStartDate);
        //�E��W�I���������N�G�X�g�f�[�^.�������.��W�I���� 
        l_mfProductCondSpec.setApplyAbleEndDate(l_mfInfo.applyAbleEndDate);
        
        if (l_mfInfo.newBuyMinQty == null)
        {
            l_mfProductCondSpec.setNewBuyMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyMinQty(l_mfInfo.newBuyMinQty);
        }
        
        if (l_mfInfo.newBuyUnitQty == null)
        {
            l_mfProductCondSpec.setNewBuyUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyUnitQty(l_mfInfo.newBuyUnitQty);
        }
        
        if (l_mfInfo.newBuyMinAmt == null)
        {
            l_mfProductCondSpec.setNewBuyMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyMinAmt(l_mfInfo.newBuyMinAmt);
        }
        
        if (l_mfInfo.newBuyUnitAmt == null)
        {
            l_mfProductCondSpec.setNewBuyUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyUnitAmt(l_mfInfo.newBuyUnitAmt);
        }
        
        if (l_mfInfo.addBuyMinQty == null)
        {
            l_mfProductCondSpec.setAddBuyMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyMinQty(l_mfInfo.addBuyMinQty);
        }
        
        if (l_mfInfo.addBuyUnitQty == null)
        {
            l_mfProductCondSpec.setAddBuyUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyUnitQty(l_mfInfo.addBuyUnitQty);
        }
        
        if (l_mfInfo.addBuyMinAmt == null)
        {
            l_mfProductCondSpec.setAddBuyMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyMinAmt(l_mfInfo.addBuyMinAmt);
        }
        
        if (l_mfInfo.addBuyUnitAmt == null)
        {
            l_mfProductCondSpec.setAddBuyUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyUnitAmt(l_mfInfo.addBuyUnitAmt);
        }

        l_mfProductCondSpec.setSellSelectable(l_mfInfo.sellSelectable);
        
        if (l_mfInfo.sellMinQty == null)
        {
            l_mfProductCondSpec.setSellMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSellMinQty(l_mfInfo.sellMinQty);
        }  
        
        if (l_mfInfo.sellUnitQty == null)
        {
            l_mfProductCondSpec.setSellUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSellUnitQty(l_mfInfo.sellUnitQty);
        }
        
        if (l_mfInfo.sellMinAmt == null)
        {
            l_mfProductCondSpec.setSellMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSellMinAmt(l_mfInfo.sellMinAmt);
        } 
        
        if (l_mfInfo.sellUnitAmt == null)
        {
            l_mfProductCondSpec.setSellUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSellUnitAmt(l_mfInfo.sellUnitAmt);
        } 

        l_mfProductCondSpec.setSwitchingSelectable(l_mfInfo.switchingSelectable);
        
        if (l_mfInfo.switchingMinQty == null)
        {
            l_mfProductCondSpec.setSwitchingMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingMinQty(l_mfInfo.switchingMinQty);
        }  
        
        if (l_mfInfo.switchingUnitQty == null)
        {
            l_mfProductCondSpec.setSwitchingUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingUnitQty(l_mfInfo.switchingUnitQty);
        }
        
        if (l_mfInfo.switchingMinAmt == null)
        {
            l_mfProductCondSpec.setSwitchingMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingMinAmt(l_mfInfo.switchingMinAmt);
        } 
        
        if (l_mfInfo.switchingUnitAmt == null)
        {
            l_mfProductCondSpec.setSwitchingUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingUnitAmt(l_mfInfo.switchingUnitAmt);
        } 
        
        //�E�w����@@�i��W�j�����N�G�X�g�f�[�^.�������.�w����@@�i��W�j
        if (l_mfInfo.applySelectable == null)
        {
            l_mfProductCondSpec.setApplySelectable(null);
        }
        else
        {
            l_mfProductCondSpec.setApplySelectable(l_mfInfo.applySelectable);
        }
        //�E�Œ�����i��W�j�����N�G�X�g�f�[�^.�������.�Œ�����i��W�j 
        if (l_mfInfo.applyMinQty == null)
        {
            l_mfProductCondSpec.setApplyMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyMinQty(l_mfInfo.applyMinQty);
        }
        //�E�P�ʌ����i��W�j�����N�G�X�g�f�[�^.�������.�P�ʌ����i��W�j 
        if (l_mfInfo.applyUnitQty == null)
        {
            l_mfProductCondSpec.setApplyUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyUnitQty(l_mfInfo.applyUnitQty);
        }
        //�E�Œ���z�i��W�j�����N�G�X�g�f�[�^.�������.�Œ���z�i��W�j 
        if (l_mfInfo.applyMinAmt == null)
        {
            l_mfProductCondSpec.setApplyMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyMinAmt(l_mfInfo.applyMinAmt);
        }
        //�E�P�ʋ��z�i��W�j�����N�G�X�g�f�[�^.�������.�P�ʋ��z�i��W�j
        if (l_mfInfo.applyUnitAmt == null)
        {
            l_mfProductCondSpec.setApplyUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyUnitAmt(l_mfInfo.applyUnitAmt);
        }
        
        l_mfProductCondSpec.setBuyPossibleDivTheDay(
                l_mfInfo.todayBuyPossDiv);
        l_mfProductCondSpec.setBuyPossibleDivTheNextDay(
                l_mfInfo.nextDayBuyPossDiv);
        l_mfProductCondSpec.setSellPossibleDivTheDay(
                l_mfInfo.todaySellPossDiv);
        l_mfProductCondSpec.setSellPossibleDivTheNextDay(
                l_mfInfo.nextDaySellPossDiv);
        l_mfProductCondSpec.setSwtPossibleDivTheDay(
                l_mfInfo.todaySwitchingPossDiv);
        l_mfProductCondSpec.setSwtPossibleDivTheNextDay(
                l_mfInfo.nextDaySwitchingPossDiv);
        
        //�E��W�\�敪�i�����������j�����N�G�X�g�f�[�^.�������.��W�\�敪�i�����������j 
        l_mfProductCondSpec.setApplyPossDivTheDay(
            l_mfInfo.todayApplyPossDiv);
        
        l_mfProductCondSpec.setOrderCloseStartTimeFull(
                l_mfInfo.orderCloseStartTimeFull);
        l_mfProductCondSpec.setOrderCloseEndTimeFull(
                l_mfInfo.orderCloseEndTimeFull);
        l_mfProductCondSpec.setOrderCloseStartTimeHalf(
                l_mfInfo.orderCloseStartTimeHalf);
        l_mfProductCondSpec.setOrderCloseEndTimeHalf(
                l_mfInfo.orderCloseEndTimeHalf);
        
        //�E��W�\�敪�i�����������j�����N�G�X�g�f�[�^.�������.��W�\�敪�i�����������j 
        l_mfProductCondSpec.setApplyPossDivTheNextDay(
            l_mfInfo.nextDayApplyPossDiv);

        //�O�ݍŒ���z�i�V�K���t�j�����N�G�X�g�f�[�^.�������.�O�ݍŒ���z�i�V�K���t�j
        l_mfProductCondSpec.setBuyFrgnMinAmtBuy(l_mfInfo.frgnMinAmtBuy);

        //�E�O�ݒP�ʋ��z�i�V�K���t�j�����N�G�X�g�f�[�^.�������.�O�ݒP�ʋ��z�i�V�K���t�j
        l_mfProductCondSpec.setBuyFrgnUnitAmtBuy(l_mfInfo.frgnUnitAmtBuy);

        //�E�O�ݍŒ���z�i�ǉ����t�j�����N�G�X�g�f�[�^.�������.�O�ݍŒ���z�i�ǉ����t�j
        l_mfProductCondSpec.setBuyFrgnMinAmtAdd(l_mfInfo.frgnMinAmtAdd);

        //�E�O�ݒP�ʋ��z�i�ǉ����t�j�����N�G�X�g�f�[�^.�������.�O�ݒP�ʋ��z�i�ǉ����t�j
        l_mfProductCondSpec.setBuyFrgnUnitAmtAdd(l_mfInfo.frgnUnitAmtAdd);

        //�E�O�ݍŒ���z�i���j�����N�G�X�g�f�[�^.�������.�O�ݍŒ���z�i���j
        l_mfProductCondSpec.setSellFrgnMinAmtSell(l_mfInfo.frgnMinAmtSell);

        //�E�O�ݒP�ʋ��z�i���j�����N�G�X�g�f�[�^.�������.�O�ݒP�ʋ��z�i���j
        l_mfProductCondSpec.setSellFrgnUnitAmtSell(l_mfInfo.frgnUnitAmtSell);

        //�����������̓o�^�R�����{ 
        //�@@�g�����M�����}�l�[�W���̎擾���s��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

        //�@@�g�����M�����}�l�[�W��.validate��������()���R�[������B 
        l_mfProductManager.validateProductCond(l_institution, l_mfProductCondSpec);
         
        //--------------- ���M�����}�X�^�e�[�u���̍X�V --------------------         
        try
        {
            //�����M�����I�u�W�F�N�g�̎擾 
            //�@@�g�����M�����}�l�[�W��.get�X�V�p���M����()�̖߂�l�𓊐M�����N���X�ŃL���X�g����B
            WEB3MutualFundProduct l_mfProduct = 
                l_mfProductManager.getUpdateMutualFundTradedProduct(
                    l_institution.getInstitutionCode(), 
                    l_mfInfo.mutualProductCode);
            
            //�����M�����I�u�W�F�N�g���A���M����Params�I�u�W�F�N�g�̐��� 
            //�@@���M�����I�u�W�F�N�g.getDataSourceObject()���R�[������B
            MutualFundProductRow l_mfProductRow = 
                (MutualFundProductRow)l_mfProduct.getDataSourceObject();
            MutualFundProductParams l_mfProductParams = 
                new MutualFundProductParams(l_mfProductRow);
            
            //�����M����Params�I�u�W�F�N�g�ɍX�V���e���Z�b�g����B
            if (l_mfInfo.web3TreatmentFlag != null)
            { 
                l_mfProductParams.setSystemHandlingDiv(l_mfInfo.web3TreatmentFlag);   
            }
            if (l_mfInfo.jpnProductName != null)
            {
                l_mfProductParams.setStandardName(l_mfInfo.jpnProductName);   
            }
            if (l_mfInfo.engProductName != null)
            {
                l_mfProductParams.setEngProductName(l_mfInfo.engProductName);   
            }
            if (l_mfInfo.categoryCode != null)
            {
                l_mfProductParams.setCategoryCode(l_mfInfo.categoryCode);
            }
            if (l_mfInfo.buySelectable != null)
            {
                l_mfProductParams.setBuySpecityDiv(l_mfInfo.buySelectable); 
            }
            if (l_mfInfo.sellSelectable != null)
            {
                l_mfProductParams.setSellSpecifyDiv(l_mfInfo.sellSelectable);
            }
            if (l_mfInfo.switchingSelectable != null)
            {
                l_mfProductParams.setSwtSpecifyDiv(l_mfInfo.switchingSelectable);
            }
            if (l_mfInfo.newBuyMinQty != null)
            {
                l_mfProductParams.setNewBuyMinQty(Long.parseLong(l_mfInfo.newBuyMinQty));
            }
            if (l_mfInfo.addBuyMinQty != null)
            {
                l_mfProductParams.setAddBuyMinQty(Long.parseLong(l_mfInfo.addBuyMinQty));
            }
            if (l_mfInfo.sellMinQty != null)
            {
                l_mfProductParams.setSellMinQty(Long.parseLong(l_mfInfo.sellMinQty));
            }
            if (l_mfInfo.switchingMinQty != null)
            {
                l_mfProductParams.setSwtMinQty(Long.parseLong(l_mfInfo.switchingMinQty));
            }
            if (l_mfInfo.newBuyUnitQty != null)
            {
                l_mfProductParams.setNewBuyUnitQty(Long.parseLong(l_mfInfo.newBuyUnitQty));
            }
            if (l_mfInfo.addBuyUnitQty != null)
            {
                l_mfProductParams.setAddBuyUnitQty(Long.parseLong(l_mfInfo.addBuyUnitQty));
            }
            if (l_mfInfo.sellUnitQty != null)
            {
                l_mfProductParams.setSellUnitQty(Long.parseLong(l_mfInfo.sellUnitQty));
            }
            if (l_mfInfo.switchingUnitQty != null)
            {
                l_mfProductParams.setSwtUnitQty(Long.parseLong(l_mfInfo.switchingUnitQty));
            }
            if (l_mfInfo.newBuyMinAmt != null)
            {
                l_mfProductParams.setNewBuyMinAmt(Long.parseLong(l_mfInfo.newBuyMinAmt));
            }
            if (l_mfInfo.addBuyMinAmt != null)
            {
                l_mfProductParams.setAddBuyMinAmt(Long.parseLong(l_mfInfo.addBuyMinAmt));
            }
            if (l_mfInfo.sellMinAmt != null)
            {
                l_mfProductParams.setSellMinAmt(Long.parseLong(l_mfInfo.sellMinAmt));
            }
            if (l_mfInfo.switchingMinAmt != null)
            {
                l_mfProductParams.setSwtMinAmt(Long.parseLong(l_mfInfo.switchingMinAmt));
            }
            if (l_mfInfo.newBuyUnitAmt != null)
            {
                l_mfProductParams.setNewBuyUnitAmt(Long.parseLong(l_mfInfo.newBuyUnitAmt));
            }
            if (l_mfInfo.addBuyUnitAmt != null)
            {
                l_mfProductParams.setAddBuyUnitAmt(Long.parseLong(l_mfInfo.addBuyUnitAmt));
            }
            if (l_mfInfo.sellUnitAmt != null)
            {
                l_mfProductParams.setSellUnitAmt(Long.parseLong(l_mfInfo.sellUnitAmt));
            }
            if (l_mfInfo.switchingUnitAmt != null)
            {
                l_mfProductParams.setSwtUnitAmt(Long.parseLong(l_mfInfo.switchingUnitAmt));
            }
            if (l_mfInfo.buyStartDate != null)
            {
                l_mfProductParams.setBuyStartDate(l_mfInfo.buyStartDate);
            }
            if (l_mfInfo.buyEndDate != null)
            {
                l_mfProductParams.setBuyEndDate(l_mfInfo.buyEndDate);
            }
            if (l_mfInfo.sellSwitchingStartDate != null)
            {
                l_mfProductParams.setSellSwtStartDate(l_mfInfo.sellSwitchingStartDate);
            }
            if (l_mfInfo.sellSwitchingEndDate != null)
            {
                l_mfProductParams.setSellSwtEndDate(l_mfInfo.sellSwitchingEndDate);
            }
            if (l_mfInfo.buyClaimStartDate != null)
            {
                l_mfProductParams.setBuyClaimStartDate(l_mfInfo.buyClaimStartDate);
            }
            if (l_mfInfo.buyClaimEndDate != null)
            {
                l_mfProductParams.setBuyClaimEndDate(l_mfInfo.buyClaimEndDate);
            }
            
            //�w����@@�i��W�j�����N�G�X�g�f�[�^.�������.�w����@@�i��W�j
            if (l_mfInfo.applySelectable != null)
            {
                l_mfProductParams.setRecruitSpecityDiv(l_mfInfo.applySelectable);
            }
            
            //�Œ�����i��W�j�����N�G�X�g�f�[�^.�������.�Œ�����i��W�j 
            if (l_mfInfo.applyMinQty != null)
            {
                l_mfProductParams.setRecruitMinQty(Long.parseLong(l_mfInfo.applyMinQty));
            }
            
            //�P�ʌ����i��W�j�����N�G�X�g�f�[�^.�������.�P�ʌ����i��W�j 
            if (l_mfInfo.applyUnitQty != null)
            {
                l_mfProductParams.setRecruitUnitQty(Long.parseLong(l_mfInfo.applyUnitQty));
            }
            
            //�Œ���z�i��W�j�����N�G�X�g�f�[�^.�������.�Œ���z�i��W�j 
            if (l_mfInfo.applyMinAmt != null)
            {
                l_mfProductParams.setRecruitMinAmt(Long.parseLong(l_mfInfo.applyMinAmt));
            }
            
            //�P�ʋ��z�i��W�j�����N�G�X�g�f�[�^.�������.�P�ʋ��z�i��W�j 
            if (l_mfInfo.applyUnitAmt != null)
            {
                l_mfProductParams.setRecruitUnitAmt(Long.parseLong(l_mfInfo.applyUnitAmt));
            }
            
            //���t�����敪�����N�G�X�g�f�[�^.�������.���t�����敪
            if (l_mfInfo.buyRestrictionDiv != null)
            {
                l_mfProductParams.setBuyLimitDiv(l_mfInfo.buyRestrictionDiv);
            }
            
            //��W�J�n�������N�G�X�g�f�[�^.�������.��W�J�n�� 
            if (l_mfInfo.applyAbleStartDate != null)
            {
                l_mfProductParams.setRecruitStartDate(l_mfInfo.applyAbleStartDate);
            }           
            
            //��W�I���������N�G�X�g�f�[�^.�������.��W�I����
            if (l_mfInfo.applyAbleEndDate != null)
            {
				l_mfProductParams.setRecruitEndDate(l_mfInfo.applyAbleEndDate);
            }
            
            //��n���@@�����N�G�X�g�f�[�^.�������.��n���@@ 
            if (l_mfInfo.deliveryVariation != null)
            {
                l_mfProductParams.setDeliveryMethod(l_mfInfo.deliveryVariation);
            }
            
            //�������������敪�����N�G�X�g�f�[�^.�������������敪
            if (l_mfInfo.unitTypeProductDiv != null)
            {
                l_mfProductParams.setUnitTypeProductDiv(l_mfInfo.unitTypeProductDiv);
            }

            //�O�ݍŒ���z�i�V�K���t�j�����N�G�X�g�f�[�^.�������.�O�ݍŒ���z�i�V�K���t�j
            if (l_mfInfo.frgnMinAmtBuy != null)
            {
                l_mfProductParams.setFrgnNewBuyMinAmt(
                    Long.parseLong(l_mfInfo.frgnMinAmtBuy));
            }

            //�O�ݒP�ʋ��z�i�V�K���t�j�����N�G�X�g�f�[�^.�������.�O�ݒP�ʋ��z�i�V�K���t
            if (l_mfInfo.frgnUnitAmtBuy != null)
            {
                l_mfProductParams.setFrgnNewBuyUnitAmt(
                    Long.parseLong(l_mfInfo.frgnUnitAmtBuy));
            }

            //�O�ݍŒ���z�i�ǉ����t�j�����N�G�X�g�f�[�^.�������.�O�ݍŒ���z�i�ǉ����t�j
            if (l_mfInfo.frgnMinAmtAdd != null)
            {
                l_mfProductParams.setFrgnAddBuyMinAmt(
                    Long.parseLong(l_mfInfo.frgnMinAmtAdd));
            }

            //�O�ݒP�ʋ��z�i�ǉ����t�j�����N�G�X�g�f�[�^.�������.�O�ݒP�ʋ��z�i�ǉ����t�j
            if (l_mfInfo.frgnUnitAmtAdd != null)
            {
                l_mfProductParams.setFrgnAddBuyUnitAmt(
                    Long.parseLong(l_mfInfo.frgnUnitAmtAdd));
            }

            //�O�ݍŒ���z�i���j�����N�G�X�g�f�[�^.�������.�O�ݍŒ���z�i���j
            if (l_mfInfo.frgnMinAmtSell != null)
            {
                l_mfProductParams.setFrgnSellMinAmt(
                    Long.parseLong(l_mfInfo.frgnMinAmtSell));
            }

            //�O�ݒP�ʋ��z�i���j�����N�G�X�g�f�[�^.�������.�O�ݒP�ʋ��z�i���j
            if (l_mfInfo.frgnUnitAmtSell != null)
            {
                l_mfProductParams.setFrgnSellUnitAmt(
                    Long.parseLong(l_mfInfo.frgnUnitAmtSell));
            }

            //��W�萔���敪�����N�G�X�g�f�[�^.�������.��W�萔���敪
            if (l_mfInfo.applyCommissionDiv != null)
            {
                l_mfProductParams.setRecruitCommissionDiv(l_mfInfo.applyCommissionDiv);
            }

            //�����M�����}�X�^�e�[�u���̍X�V���� 
            //�@@���ׂĂ̍��ڂ�null�̏ꍇ�����}�X�^�e�[�u���̍X�V���s��Ȃ��B
            if ((l_mfInfo.web3TreatmentFlag != null)
                || (l_mfInfo.jpnProductName != null)
                || (l_mfInfo.engProductName != null)
                || (l_mfInfo.categoryCode != null)
                || (l_mfInfo.buySelectable != null)
                || (l_mfInfo.sellSelectable != null)
                || (l_mfInfo.switchingSelectable != null)
                || (l_mfInfo.newBuyMinQty != null)
                || (l_mfInfo.addBuyMinQty != null)
                || (l_mfInfo.sellMinQty != null)
                || (l_mfInfo.switchingMinQty != null)
                || (l_mfInfo.newBuyUnitQty != null)
                || (l_mfInfo.addBuyUnitQty != null)
                || (l_mfInfo.sellUnitQty != null)
                || (l_mfInfo.switchingUnitQty != null)
                || (l_mfInfo.newBuyMinAmt != null)
                || (l_mfInfo.addBuyMinAmt != null)
                || (l_mfInfo.sellMinAmt != null)
                || (l_mfInfo.switchingMinAmt != null)
                || (l_mfInfo.newBuyUnitAmt != null)
                || (l_mfInfo.addBuyUnitAmt != null)
                || (l_mfInfo.sellUnitAmt != null)
                || (l_mfInfo.switchingUnitAmt != null)
                || (l_mfInfo.buyStartDate != null)
                || (l_mfInfo.buyEndDate != null)
                || (l_mfInfo.sellSwitchingStartDate != null)
                || (l_mfInfo.sellSwitchingEndDate != null)
                || (l_mfInfo.buyClaimStartDate != null)
                || (l_mfInfo.buyClaimEndDate != null)
                || (l_mfInfo.applyAbleEndDate != null) 
                || (l_mfInfo.applyAbleStartDate != null)
                || (l_mfInfo.applyMinAmt != null)
                || (l_mfInfo.applyMinQty != null)
                || (l_mfInfo.applySelectable != null)
                || (l_mfInfo.applyUnitAmt != null)
                || (l_mfInfo.applyUnitQty != null)
                || (l_mfInfo.applyUnitAmt != null)
                || (l_mfInfo.buyRestrictionDiv != null)
                || (l_mfInfo.deliveryVariation != null)
                || (l_mfInfo.unitTypeProductDiv != null)
                || (l_mfInfo.frgnMinAmtBuy != null)
                || (l_mfInfo.frgnUnitAmtBuy != null)
                || (l_mfInfo.frgnMinAmtAdd != null)
                || (l_mfInfo.frgnUnitAmtAdd != null)
                || (l_mfInfo.frgnMinAmtSell != null)
                || (l_mfInfo.frgnUnitAmtSell != null)
                || (l_mfInfo.applyCommissionDiv != null))
            {
                l_mfProductParams.setLastUpdater(l_admin.getAdministratorCode());
            
                l_mfProductParams.setOnlineUpdatedTimestamp (GtlUtils.getSystemTimestamp());
            
                //�@@QueryProcessor.doUpdateQuery()���R�[������B
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_processor.doUpdateQuery(l_mfProductParams);
            }
            
            //--------------- ����\�敪�i�����������^���c�Ɠ��������j�̍X�V ------------           
            //�����M��������̎擾 
            //�@@�|����J�����_�R���e�L�X�g�̐ݒ�l�X�V���������{ 
            //�@@�|���M��������I�u�W�F�N�g�擾���������{ 
            MutualFundTradedProduct l_mfTradedProduct = 
                l_mfProductManager.getMutualFundTradedProduct(
                    l_admin.getInstitution(),
                    l_request.mutualProductInfo.mutualProductCode);
            
            //�����ݓ��t���A�����c�Ɠ����̃`�F�b�N
            //�@@�擾�������ݓ��t�������ɁAthis.is�����c�Ɠ�()���R�[������B    
            boolean l_blnIsCalendarBizDate = this.isCalendarBizDate(l_datSystemDate);
                    
            //�������������̎擾 
            Date l_datBizDate = 
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
            String l_strBizDate = 
                WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
            
            //���������������A���M�c�Ɠ����̃`�F�b�N
            //�@@�擾����������������������this.is�c�Ɠ�()���R�[������B
            boolean l_blnIsBizDate = 
                this.isBizDate(
                    l_admin.getInstitution().getInstitutionCode(),
                    l_request.mutualProductInfo.mutualProductCode,
                    new Timestamp (l_datBizDate.getTime()));
                
            //�����M�������̎擾
            // �@@get���M������()���R�[������B
            Date l_datMfBizDate =
                WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
            
            //���C�O�s��̋x�����l���������c�Ɠ��̎擾
            //�@@ get���M���c�Ɠ�()���R�[������B
            Timestamp l_datNextMfBizDate = 
                WEB3MutualFundTradingTimeManagement.getMutualNextOrderBizDate(
                    l_admin.getInstitution().getInstitutionCode(),
                    l_request.mutualProductInfo.mutualProductCode);
            
            //�����N�G�X�g�̔��t�E���E�抷�\�敪(���c�Ɠ����E���c�Ɠ���)��null�`�F�b�N
            WEB3MutualProductConditionsCommonInfo l_mfProductInfo = 
                l_request.mutualProductInfo;
            boolean l_blnBuy = l_mfProductInfo.todayBuyPossDiv != null;
            boolean l_blnSell = l_mfProductInfo.todaySellPossDiv != null;
            boolean l_blnSwt = l_mfProductInfo.todaySwitchingPossDiv != null;
            boolean l_blnRecruit = l_mfProductInfo.todayApplyPossDiv != null;
            
            boolean l_blnBuyNext = l_mfProductInfo.nextDayBuyPossDiv != null;
            boolean l_blnSellNext = l_mfProductInfo.nextDaySellPossDiv != null;
            boolean l_blnSwtNext = l_mfProductInfo.nextDaySwitchingPossDiv != null;
            boolean l_blnRecruitNext = l_mfProductInfo.nextDayApplyPossDiv != null;
            
            //����������ixTrade�Ŏ擾�����j�̍X�V 
            //�@@�|���M��������e�[�u���̍X�V�����A�܂��͓��M��������ꎞ�e�[�u���̍X�V�������s���B
            QueryProcessor l_processor = Processors.getDefaultProcessor();
                    
            String l_strNextDayBuyPossDiv = null;
            String l_strNextDaySellPossDiv = null;
            String l_strNextDaySwtPossDiv = null;
            String l_strNextDayRecruitDiv = null;
            Timestamp l_tmsScramBizDate = null;     

            //[��������] 
            //�@@�������ID����������I�u�W�F�N�g.get�������ID() and 
            //�@@�L���r�W�l�X���t���擾�������������� 
            String l_strWhere = "traded_product_id = ? and valid_for_biz_date = ?";
            log.debug("��������e�[�u���̌���");
            log.debug("�������ID��"+ l_mfTradedProduct.getTradedProductId());
            log.debug("�L���r�W�l�X���t��"+ l_strBizDate);
            
            //��������I�u�W�F�N�g�̎擾 
            //�@@���M��������e�[�u�����ȉ��̏����Ō�������B 
            //[��������] 
            //�@@�������ID����������I�u�W�F�N�g.get�������ID() and 
            //�@@�L���r�W�l�X���t���擾�������������� 
            //�E�������� = 0���̏ꍇ�A��������ꎞ�e�[�u������������B
            //�E�������� > 1���̏ꍇ�A[�f�[�^�s����]�Ƃ��ė�O���X���[����B
            Object[] l_objWhereValues = {
                new Long(l_mfTradedProduct.getTradedProductId()),
                    l_strBizDate};
                   
            List l_lisTradedProduct = l_processor.doFindAllQuery(
                    MutualFundTradedProductRow.TYPE,
                    l_strWhere,
                    null,
                    l_objWhereValues);
                    
            log.debug("size ="  + l_lisTradedProduct.size());               
            //��������e�[�u����������==0���̏ꍇ�A�������UPDQ�e�[�u������������B
            if(l_lisTradedProduct.size() == 0)
            {
                log.debug("�������UPDQ�e�[�u���̌���");
                log.debug("�������ID��"+ l_mfTradedProduct.getTradedProductId());
                log.debug("�L���r�W�l�X���t��"+ l_strBizDate);
                // �������UPDQ�I�u�W�F�N�g�̎擾 
                //�@@���M��������e�[�u�����ȉ��̏����Ō�������B 
                //[��������] 
                //�@@�������ID����������I�u�W�F�N�g.get�������ID() and 
                //�@@�L���r�W�l�X���t���擾�������������� 
                //�E��������!=1���̏ꍇ�A[�f�[�^�s����]�Ƃ��ė�O���X���[����B
                List l_lisTradedProductUpdq = l_processor.doFindAllQuery(
                        MfTradedProductUpdqRow.TYPE,
                        l_strWhere,
                        null,
                        new Object[] {
                                new Long(l_mfTradedProduct.getTradedProductId()),
                                    l_strBizDate });
                        
                log.debug("size ="  + l_lisTradedProductUpdq.size());
                //�������UPDQ�e�[�u����������!=1���̏ꍇ�A[�f�[�^�s����]�Ƃ��ė�O���X���[����B
                if(l_lisTradedProductUpdq == null || l_lisTradedProductUpdq.size() != 1)
                {
                    log.debug("�f�[�^�s�����G���[!"); 
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + l_strMethodName
                    );
                }
                //�������Updq�I�u�W�F�N�g�̍X�V
                //�ݓ���������ꎞ�e�[�u��Params�Ɉȉ��̃v���p�e�B���Z�b�g���A
                //���̓��e�ōX�V�������s���B        
                MfTradedProductUpdqRow l_mfTradedProductUpdqTodayRow =
                    (MfTradedProductUpdqRow)l_lisTradedProductUpdq.get(0);          
            
                MfTradedProductUpdqParams l_mfTradedProductUpdqParams = 
                    new MfTradedProductUpdqParams(l_mfTradedProductUpdqTodayRow);
                
                // �E���ݓ��t�������c�Ɠ������������������M�c�Ɠ��̏ꍇ
                // �@@���A���t�E���E�抷�敪(���c�Ɠ���)�̂ǂꂩ�ɒl���Z�b�g����Ă����ꍇ
                if(l_blnIsCalendarBizDate && l_blnIsBizDate && (l_blnBuy || l_blnSell || l_blnSwt || l_blnRecruit))
                {
                    log.debug("is�c�Ɠ�()��true�̏ꍇ");
                    log.debug("���c�Ɠ����̎������UPDQ���X�V����B");
                    //�u���t�E���E�抷�\�敪�v�̃Z�b�g     
                    if(l_blnBuy)
                    {
                        l_mfTradedProductUpdqParams.setBuyPossibleDiv(
                            l_mfProductInfo.todayBuyPossDiv);
                    }
                    if(l_blnSell)
                    {
                        l_mfTradedProductUpdqParams.setSellPossibleDiv(
                            l_mfProductInfo.todaySellPossDiv);
                    }
                    if(l_blnSwt)
                    {
                        l_mfTradedProductUpdqParams.setSwtPossibleDiv(
                            l_mfProductInfo.todaySwitchingPossDiv);
                    }
                    if (l_blnRecruit)
                    {
                        l_mfTradedProductUpdqParams.setRecruitPossibleDiv(
                            l_mfProductInfo.todayApplyPossDiv);
                    }

                    //�u�ً}��~�L�����v�̃Z�b�g
                    // �u���t�E���E�抷�\�敪�v�����ׂĉ\�̏ꍇ�Anull���Z�b�g
                    //  ����ȊO�́Aget���M������()�̖߂�l���Z�b�g             
                    String l_dbBuyPossDiv = l_mfTradedProductUpdqParams.getBuyPossibleDiv();
                    String l_dbSellPossDiv = l_mfTradedProductUpdqParams.getSellPossibleDiv();
                    String l_dbSwtPossDiv = l_mfTradedProductUpdqParams.getSwtPossibleDiv();
                    String l_dbRecruitPossDiv = l_mfTradedProductUpdqParams.getRecruitPossibleDiv();
                    if(WEB3BuyPossibleDivDef.ACQUIRED.equals(l_dbBuyPossDiv) 
                            && WEB3SellPossibleDivDef.SELL.equals(l_dbSellPossDiv) 
                            && WEB3SwtPossibleDivDef.SWITCHING.equals(l_dbSwtPossDiv) 
                            && WEB3MFRecruitPossibleDivDef.RECRUIT.equals(l_dbRecruitPossDiv))
                    {
                        l_mfTradedProductUpdqParams.setScramBizDate(null);  
                    }
                    else
                    {
                        l_mfTradedProductUpdqParams.setScramBizDate(l_datMfBizDate);     
                    }
                
                    l_mfTradedProductUpdqParams.setLastUpdater(
                            l_admin.getAdministratorCode());
                    l_mfTradedProductUpdqParams.setOnlineUpdatedTimestamp(
                            GtlUtils.getSystemTimestamp());
                
                    l_processor.doUpdateQuery(l_mfTradedProductUpdqParams);   
                }
                // �E���ݓ��t�������c�Ɠ��܂��͍��������������M�c�Ɠ��̏ꍇ
                // �@@���A���t�E���E�抷�敪(���c�Ɠ���)�̂ǂꂩ�ɒl���Z�b�g����Ă����ꍇ
                else if((!l_blnIsCalendarBizDate || !l_blnIsBizDate)
                                && (l_blnBuyNext || l_blnSellNext || l_blnSwtNext || l_blnRecruitNext))
                {
                    log.debug("is�c�Ɠ�()��false�̏ꍇ");
                    log.debug("���c�Ɠ����̎������UPDQ���X�V����B");
                    //�u���t�E���E�抷�\�敪�v�̃Z�b�g                      
                    if(l_blnBuyNext)
                    {
                        l_mfTradedProductUpdqParams.setBuyPossibleDiv(
                            l_mfProductInfo.nextDayBuyPossDiv);
                    }
                    if(l_blnSellNext)
                    {
                        l_mfTradedProductUpdqParams.setSellPossibleDiv(
                            l_mfProductInfo.nextDaySellPossDiv);
                    }
                    if(l_blnSwtNext)
                    {
                        l_mfTradedProductUpdqParams.setSwtPossibleDiv(
                            l_mfProductInfo.nextDaySwitchingPossDiv);
                    }
                    if(l_blnRecruitNext)
                    {
                        l_mfTradedProductUpdqParams.setRecruitPossibleDiv(
                            l_mfProductInfo.nextDayApplyPossDiv);
                    }
                    
                    // ���c�Ɠ����X�V���e�̍쐬             
                    l_strNextDayBuyPossDiv =
                        l_mfTradedProductUpdqParams.getBuyPossibleDiv();
                    l_strNextDaySellPossDiv =
                        l_mfTradedProductUpdqParams.getSellPossibleDiv();
                    l_strNextDaySwtPossDiv =
                        l_mfTradedProductUpdqParams.getSwtPossibleDiv();
                    l_strNextDayRecruitDiv = 
                        l_mfTradedProductUpdqParams.getRecruitPossibleDiv();
                    
                    //�u�ً}��~�L�����v�̃Z�b�g
                    // �u���t�E���E�抷�\�敪�v�����ׂĉ\�̏ꍇ�Anull���Z�b�g
                    //  ����ȊO�́Aget���M������()�̖߂�l���Z�b�g                 
                    if(WEB3BuyPossibleDivDef.ACQUIRED.equals(l_strNextDayBuyPossDiv) 
                        && WEB3SellPossibleDivDef.SELL.equals(l_strNextDaySellPossDiv) 
                        && WEB3SwtPossibleDivDef.SWITCHING.equals(l_strNextDaySwtPossDiv)
                        && WEB3MFRecruitPossibleDivDef.RECRUIT.equals(l_strNextDayRecruitDiv))
                    {
                        l_mfTradedProductUpdqParams.setScramBizDate(null);  
                    }
                    else
                    {
                        l_mfTradedProductUpdqParams.setScramBizDate(l_datMfBizDate);     
                    }
                
                    l_mfTradedProductUpdqParams.setLastUpdater(
                            l_admin.getAdministratorCode());
                    l_mfTradedProductUpdqParams.setOnlineUpdatedTimestamp(
                            GtlUtils.getSystemTimestamp());
                
                    l_processor.doUpdateQuery(l_mfTradedProductUpdqParams);   
                }                                       
            }        
            //��������e�[�u����������>1���̏ꍇ�A[�f�[�^�s����]�Ƃ��ė�O���X���[����B
            else if(l_lisTradedProduct == null || l_lisTradedProduct.size() > 1)
            {
                log.debug("�f�[�^�s�����G���[!"); 
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + l_strMethodName
                );
            }
            
            //��������e�[�u���������ʁ�1���̏ꍇ
            else
            {
                MutualFundTradedProduct l_mfTradedProductToday = 
                    (WEB3MutualFundTradedProduct)l_mfProductManager.toTradedProduct(
                        (Row)l_lisTradedProduct.get(0));
                
                MutualFundTradedProductRow l_mfTradedProductTodayRow =
                    (MutualFundTradedProductRow)l_mfTradedProductToday.
                        getDataSourceObject();                  
            
                MutualFundTradedProductParams l_mfTradedProductParams = 
                    new MutualFundTradedProductParams(l_mfTradedProductTodayRow);
                //��������I�u�W�F�N�g�̍X�V
                //�@@���M��������e�[�u��Params�Ɉȉ��̃v���p�e�B���Z�b�g���A
                //�@@���̓��e�ōX�V�������s���B
                // �E���ݓ��t�������c�Ɠ������������������M�c�Ɠ��̏ꍇ
                // �@@���A���t�E���E�抷�敪(���c�Ɠ���)�̂ǂꂩ�ɒl���Z�b�g����Ă����ꍇ
                if(l_blnIsCalendarBizDate && l_blnIsBizDate && (l_blnBuy || l_blnSell || l_blnSwt || l_blnRecruit))
                {
                    log.debug("is�c�Ɠ�()��true�̏ꍇ");
                    log.debug("���c�Ɠ����̎���������X�V����B");
                    //�u���t�E���E�抷�\�敪�v�̃Z�b�g        
                    if(l_blnBuy)
                    {
                    l_mfTradedProductParams.setBuyPossibleDiv(
                            l_mfProductInfo.todayBuyPossDiv);
                    }
                    if(l_blnSell)
                    {
                        l_mfTradedProductParams.setSellPossibleDiv(
                            l_mfProductInfo.todaySellPossDiv);
                    }
                    if(l_blnSwt)
                    {
                        l_mfTradedProductParams.setSwtPossibleDiv(
                            l_mfProductInfo.todaySwitchingPossDiv);
                    }
                    if(l_blnRecruit)
                    {
                        l_mfTradedProductParams.setRecruitPossibleDiv(
                            l_mfProductInfo.todayApplyPossDiv);
                    }
                
                    l_mfTradedProductParams.setScramBizDate(null);
                    l_mfTradedProductParams.setLastUpdater(
                            l_admin.getAdministratorCode());
                    l_mfTradedProductParams.setOnlineUpdatedTimestamp(
                            GtlUtils.getSystemTimestamp());
               
                    l_processor.doUpdateQuery(l_mfTradedProductParams);
                }
                // �E���ݓ��t�������c�Ɠ��܂��͍��������������M�c�Ɠ��̏ꍇ
                // �@@���A���t�E���E�抷�敪(���c�Ɠ���)�̂ǂꂩ�ɒl���Z�b�g����Ă����ꍇ
                else if((!l_blnIsCalendarBizDate || !l_blnIsBizDate)
                                && (l_blnBuyNext || l_blnSellNext || l_blnSwtNext || l_blnRecruitNext))
                {
                    log.debug("is�c�Ɠ�()��false�̏ꍇ");
                    log.debug("���c�Ɠ����̎���������X�V����B");
                    //�u���t�E���E�抷�\�敪�v�̃Z�b�g     
                    if(l_blnBuyNext)
                    {
                        l_mfTradedProductParams.setBuyPossibleDiv(
                            l_mfProductInfo.nextDayBuyPossDiv);
                    }
                    if(l_blnSellNext)
                    {
                        l_mfTradedProductParams.setSellPossibleDiv(
                            l_mfProductInfo.nextDaySellPossDiv);
                    }
                    if(l_blnSwtNext)
                    {
                        l_mfTradedProductParams.setSwtPossibleDiv(
                            l_mfProductInfo.nextDaySwitchingPossDiv);
                    }
                    if(l_blnRecruitNext)
                    {
                        l_mfTradedProductParams.setRecruitPossibleDiv(
                            l_mfProductInfo.nextDayApplyPossDiv);
                    }
                    
                    // ���c�Ɠ����X�V���e�̍쐬
                    l_strNextDayBuyPossDiv =
                        l_mfTradedProductParams.getBuyPossibleDiv();                
                    l_strNextDaySellPossDiv =
                        l_mfTradedProductParams.getSellPossibleDiv();
                    l_strNextDaySwtPossDiv =
                        l_mfTradedProductParams.getSwtPossibleDiv();
                    l_strNextDayRecruitDiv =
                        l_mfTradedProductParams.getRecruitPossibleDiv();
                
                    l_mfTradedProductParams.setScramBizDate(null);
                    l_mfTradedProductParams.setLastUpdater(
                            l_admin.getAdministratorCode());
                    l_mfTradedProductParams.setOnlineUpdatedTimestamp(
                            GtlUtils.getSystemTimestamp());
               
                    l_processor.doUpdateQuery(l_mfTradedProductParams);
                }   
            }
            
            //����������ꎞ�e�[�u���i�������c�Ɠ����瓊�M���c�Ɠ����܂Łj�̍X�V
            // ���N�G�X�g.���t�E���E�抷�\�敪�i���c�Ɠ����j
            // �̂��Âꂩ�ɒl���Z�b�g����Ă����ꍇ
            if(l_blnBuyNext || l_blnSellNext || l_blnSwtNext || l_blnRecruitNext)
            {
                log.debug("���c�Ɠ����܂ł̎������UPDQ�̌���");
                log.debug("�������ID��"+ l_mfTradedProduct.getTradedProductId());
                //���݂���́u�����������v�������ɁA
                //�@@�c�Ɠ��v�Z�I�u�W�F�N�g�𐶐�����B 
                Timestamp l_tmValidForBizDate =
                    new Timestamp (l_datBizDate.getTime());
                WEB3GentradeBizDate l_GentradeBizDate = 
                    new WEB3GentradeBizDate(l_tmValidForBizDate);
                    
                //�c�Ɠ��v�Z�I�u�W�F�N�g.roll()���R�[������B 
                //�@@[roll�ɓn������] 
                //�@@���Z�^���Z�������P 
                Timestamp l_datNextBizDate = l_GentradeBizDate.roll(1);
                
                //���M��������ꎞ�e�[�u���̌����B
                //�@@[��������] 
                //�@@�@@�������ID����������I�u�W�F�N�g.get�������ID() and 
                //    �s��ID���s��I�u�W�F�N�g.getMarketId() and
                //�@@�@@�L���r�W�l�X���t>���擾�����������c�Ɠ� and
                //    �L���r�W�l�X���t<�����M���c�Ɠ�
                String l_whereClause = " product_id = ? and " +
                        " market_id = ? and " +
                        " valid_for_biz_date >= ? and " +
                        " valid_for_biz_date <= ? ";
                String l_strCondition = " for update ";

                WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                    (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                MutualFundTradedProductRow l_mfTraderProductRow = 
                    (MutualFundTradedProductRow) l_mfTradedProduct.getDataSourceObject();
                
                long l_lngMarketId = 
                    l_gentradeFinObjectManager.getMarket(
                        l_admin.getInstitutionCode(),
                        WEB3MarketCodeDef.DEFAULT
                        ).getMarketId();
                
                //�������c�Ɠ�
                String l_strNextBizDate = 
                    WEB3DateUtility.formatDate(l_datNextBizDate,"yyyyMMdd");
                
                //���M���c�Ɠ�  
                String l_strNextMfBizDate = null;
                //�E���ݓ��t�������c�Ɠ������������������M�c�Ɠ��̏ꍇ
                //  get���M���c�Ɠ�()�̖߂�l    
                if(l_blnIsCalendarBizDate && l_blnIsBizDate)
                {  
                    l_strNextMfBizDate = 
                        WEB3DateUtility.formatDate(l_datNextMfBizDate,"yyyyMMdd");  
                }
                //�E���ݓ��t�������c�Ɠ��܂��͍��������������M�c�Ɠ��̏ꍇ
                //  get���M������()�̖߂�l    
                else if(!l_blnIsCalendarBizDate || !l_blnIsBizDate)
                {
                    l_strNextMfBizDate = 
                        WEB3DateUtility.formatDate(l_datMfBizDate,"yyyyMMdd");
                }

                log.debug("�L���r�W�l�X���t(�������c�Ɠ�)>��"+ l_strNextBizDate); 
                log.debug("�L���r�W�l�X���t(���M���c�Ɠ�)<��"+ l_strNextMfBizDate);               
                Object l_bindVars[] = {
                        new Long(l_mfTraderProductRow.getProductId()),
                        new Long(l_lngMarketId),
                        l_strNextBizDate,
                        l_strNextMfBizDate};
                
                List l_rtnList =
                    l_processor.doFindAllQuery(
                        MfTradedProductUpdqRow.TYPE,
                        l_whereClause,
                        null,
                        l_strCondition,
                        l_bindVars);    
                
                log.debug("size ="+ l_rtnList.size());                
                if(l_rtnList != null && l_rtnList.size() != 0)
                {
                    int l_intSize = l_rtnList.size();

                    for (int i = 0; i < l_intSize; i++)
                    {
                        MfTradedProductUpdqRow l_mfTradedProductUpdqRow = 
                            (MfTradedProductUpdqRow) l_rtnList.get(i);
                       
                        MfTradedProductUpdqParams l_mfTradedProductUpdqParams = 
                            new MfTradedProductUpdqParams(l_mfTradedProductUpdqRow);

                        //�E���c�Ɠ����X�V���e���쐬����Ă��Ȃ��ꍇ�A
                        //  �������c�Ɠ����̓��M��������ꎞ�e�[�u�����痂�c�Ɠ����X�V���e���쐬����B
                        if (l_strNextDayBuyPossDiv == null && l_strNextDaySellPossDiv == null 
                                && l_strNextDaySwtPossDiv == null && l_strNextDayRecruitDiv == null
                                    && l_mfTradedProductUpdqParams.getValidForBizDate().
                                            compareTo(l_strNextBizDate) == 0)
                        {
                            log.debug("���c�Ɠ����X�V���e���쐬");
                            //�u���t�E���E�抷�\�敪�v�̃Z�b�g     
                            if(l_blnBuyNext)
                            {
                                l_strNextDayBuyPossDiv =
                                    l_mfProductInfo.nextDayBuyPossDiv;
                            }
                            else
                            {
                                l_strNextDayBuyPossDiv =
                                    l_mfTradedProductUpdqParams.getBuyPossibleDiv();
                            }
                            if(l_blnSellNext)
                            {
                                l_strNextDaySellPossDiv =
                                    l_mfProductInfo.nextDaySellPossDiv;
                            }
                            else
                            {
                                l_strNextDaySellPossDiv =
                                    l_mfTradedProductUpdqParams.getSellPossibleDiv();
                            }
                            if(l_blnSwtNext)
                            {
                                l_strNextDaySwtPossDiv =
                                    l_mfProductInfo.nextDaySwitchingPossDiv;   
                            }
                            else
                            {
                                l_strNextDaySwtPossDiv =
                                    l_mfTradedProductUpdqParams.getSwtPossibleDiv();
                            }
                            if (l_blnRecruitNext)
                            {
                                l_strNextDayRecruitDiv =
                                    l_mfProductInfo.nextDayApplyPossDiv;
                            }
                            else
                            {                           
                                l_strNextDayRecruitDiv = 
                                    l_mfTradedProductUpdqParams.getRecruitPossibleDiv();
                            }
                        }
                    }                   
                    //�u�ً}��~�L�����v�̃Z�b�g
                    // �u���t�E���E�抷�\�敪�v�����ׂĉ\�̏ꍇ�Anull���Z�b�g
                    //  ����ȊO�́A�ȉ����Z�b�g����B
                    //�@@�E���ݓ��t�������c�Ɠ������������������M�c�Ɠ��̏ꍇ
                    //  �@@get���M���c�Ɠ�()�̖߂�l
                    //�@@�E���ݓ��t�������c�Ɠ��܂��͍��������������M�c�Ɠ��̏ꍇ
                    //  �@@get���M������()�̖߂�l               
                    if(WEB3BuyPossibleDivDef.ACQUIRED.equals(l_strNextDayBuyPossDiv) 
                            && WEB3SellPossibleDivDef.SELL.equals(l_strNextDaySellPossDiv) 
                            && WEB3SwtPossibleDivDef.SWITCHING.equals(l_strNextDaySwtPossDiv)
                            && WEB3MFRecruitPossibleDivDef.RECRUIT.equals(l_strNextDayRecruitDiv))
                    {
                        l_tmsScramBizDate = null;  
                    }
                    else
                    {
                        if(l_blnIsCalendarBizDate && l_blnIsBizDate)
                        {  
                            l_tmsScramBizDate = l_datNextMfBizDate;
                        }
                        else if(!l_blnIsCalendarBizDate || !l_blnIsBizDate)
                        {
                            l_tmsScramBizDate = new Timestamp(l_datMfBizDate.getTime());
                        }
                    }
                    
                    //�E���M���c�Ɠ����܂ł̓��M��������ꎞ�e�[�u�����X�V
                    for (int i = 0; i < l_intSize; i++)
                    {
                        log.debug("���M���c�Ɠ����܂ł̓��M�������UPDQ���X�V");
                        MfTradedProductUpdqRow l_mfTradedProductUpdqRow = 
                            (MfTradedProductUpdqRow) l_rtnList.get(i);
                        
                        MfTradedProductUpdqParams l_mfTradedProductUpdqParams = 
                            new MfTradedProductUpdqParams(l_mfTradedProductUpdqRow);

                        l_mfTradedProductUpdqParams.setBuyPossibleDiv(
                            l_strNextDayBuyPossDiv);

                        l_mfTradedProductUpdqParams.setSellPossibleDiv(
                            l_strNextDaySellPossDiv);

                        l_mfTradedProductUpdqParams.setSwtPossibleDiv(
                            l_strNextDaySwtPossDiv); 
                        
                        l_mfTradedProductUpdqParams.setRecruitPossibleDiv(
                            l_strNextDayRecruitDiv);
                        
                        l_mfTradedProductUpdqParams.setScramBizDate(l_tmsScramBizDate);  

                        l_mfTradedProductUpdqParams.setLastUpdater(
                                l_admin.getAdministratorCode());

                        l_mfTradedProductUpdqParams.setOnlineUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        
                        l_processor.doUpdateQuery(l_mfTradedProductUpdqParams);
                    }
                }
            }
            
            //--------------- ������ԃe�[�u���̍X�V --------------------         
            //�����X�R�[�h�ꗗ�̎擾 
            Branch l_Branchs[] = l_institution.getBranches();
            String l_arrBranchCode[] = new String[l_Branchs.length];
            for (int i = 0; i < l_Branchs.length; i++)
            {
                l_arrBranchCode[i] = l_Branchs[i].getBranchCode();
            }
            
            //���������؎��ԁi�����j�̍X�V 
            // �@@�|���M������ԊǗ�.�������؎��ԍX�V()���R�[������B 
            if((l_mfProductInfo.orderCloseStartTimeFull != null) 
                    || (l_mfProductInfo.orderCloseEndTimeFull != null))
            {
                WEB3MutualFundTradingTimeManagement.updateOrderCloseTime(
                        l_institution.getInstitutionCode(),
                        l_arrBranchCode,
                        l_mfProductInfo.mutualProductCode,
                        WEB3BizDateTypeDef.BIZ_DATE,
                        l_mfProductInfo.orderCloseStartTimeFull,
                        l_mfProductInfo.orderCloseEndTimeFull);
            }
            
            //���������؎��ԁi�����j�̍X�V 
            // �@@�|���M������ԊǗ�.�������؎��ԍX�V()���R�[������B
            if((l_mfProductInfo.orderCloseStartTimeHalf != null) 
                    || (l_mfProductInfo.orderCloseEndTimeHalf != null))
            {
                WEB3MutualFundTradingTimeManagement.updateOrderCloseTime(
                        l_institution.getInstitutionCode(),
                        l_arrBranchCode,
                        l_mfProductInfo.mutualProductCode,
                        WEB3BizDateTypeDef.BIZ_DATE_AM,
                        l_mfProductInfo.orderCloseStartTimeHalf,
                        l_mfProductInfo.orderCloseEndTimeHalf);
            }
            
            WEB3AdminMutualConditionsCompleteResponse l_response = 
                (WEB3AdminMutualConditionsCompleteResponse)l_request.createResponse();

            return l_response;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂��� ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���! ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���! ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        
    }
    
    /**
     * (validate���������o�^)<BR>
     * ���M���������o�^�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u���M�i�Ǘ��ҁjvalidate���������o�^�v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u���M�i�Ǘ��ҁjvalidate���������o�^�v: <BR>
     *        5((���ݓ��t�擾 <BR>
     *        ���N�G�X�g�f�[�^.�������.�I�y���[�V�������t!=���ݓ��t�̏ꍇ <BR>
     *         ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01353<BR>
     *  ========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E3F11B01A2
     */
    protected WEB3AdminMutualConditionsConfirmResponse validateProductConditionsRegist(
        WEB3AdminMutualConditionsConfirmRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "validateProductConditionsRegist(" 
            + "WEB3AdminMutualConditionsConfirmResponse l_request)";
        log.entering(l_strMethodName);

        
        // �P�j���̓`�F�b�N���s���B
        l_request.validate();
        
        //�R�j�Ǘ��Ҍ����`�F�b�N 
        //�@@�R�|�P�j�Ǘ��҃I�u�W�F�N�g.getInstanceFrom���O�C�����( )���R�[������B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //�@@�R�|�Q�j�Ǘ��҃I�u�W�F�N�g.validate����( )���R�[������B 
        // [validate����()�Ɏw�肷�����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���M�i�J�����_�[�Ǘ��j 
        // is�X�V�F�@@true
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS,
                true);
        
        // �S�j�I�y���[�V�������t�`�F�b�N
        // �@@���ݓ��t�擾
        //�@@GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l 
        Timestamp l_datSystemDate = 
            GtlUtils.getTradingSystem().getSystemTimestamp();
            
        log.debug("���ݓ��t��" + l_datSystemDate);
        
        // �A���N�G�X�g�f�[�^.�������.�I�y���[�V�������t!=���ݓ��t�̏ꍇ�A 
        //�@@ ��O���X���[����B
        Date l_datOperationDate = l_request.mutualProductInfo.operationDate;
        if(WEB3DateUtility.compareToDay(l_datSystemDate,l_datOperationDate) != 0)
        {
            log.debug("�������̃I�y���[�V�������t�͌��ݓ��t�ł͂���܂���B ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01353,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        
        //�T�j�،���ЃI�u�W�F�N�g�̎擾 
        //�@@�T�|�P�j�Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h���R�[������B 
        Institution l_institution = l_admin.getInstitution();
        
        //�U�j���M�����������e�I�u�W�F�N�g�̐����ƒl�̐ݒ� 
        WEB3MutualProductConditionsCommonInfo l_mfInfo = 
            l_request.mutualProductInfo;
        
        WEB3MutualFundProductCondSpec l_mfProductCondSpec = new WEB3MutualFundProductCondSpec();
        l_mfProductCondSpec.setMutualProductCode(l_mfInfo.mutualProductCode);
        l_mfProductCondSpec.setBuyStartDate(l_mfInfo.buyStartDate);
        l_mfProductCondSpec.setBuyEndDate(l_mfInfo.buyEndDate);
        l_mfProductCondSpec.setSellSwitchingStartDate(l_mfInfo.sellSwitchingStartDate);
        l_mfProductCondSpec.setSellSwitchingEndDate(l_mfInfo.sellSwitchingEndDate);
        l_mfProductCondSpec.setBuyClaimStartDate(l_mfInfo.buyClaimStartDate);
        l_mfProductCondSpec.setBuyClaimEndDate(l_mfInfo.buyClaimEndDate);
        l_mfProductCondSpec.setBuySelectable(l_mfInfo.buySelectable);
        
        //�E��W�J�n�������N�G�X�g�f�[�^.�������.��W�J�n��
        l_mfProductCondSpec.setApplyAbleStartDate(l_mfInfo.applyAbleStartDate);
        //�E��W�I���������N�G�X�g�f�[�^.�������.��W�I���� 
        l_mfProductCondSpec.setApplyAbleEndDate(l_mfInfo.applyAbleEndDate);
        
        if (l_mfInfo.newBuyMinQty == null)
        {
            l_mfProductCondSpec.setNewBuyMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyMinQty(l_mfInfo.newBuyMinQty);
        }
        
        if (l_mfInfo.newBuyUnitQty == null)
        {
            l_mfProductCondSpec.setNewBuyUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyUnitQty(l_mfInfo.newBuyUnitQty);
        }
        
        if (l_mfInfo.newBuyMinAmt == null)
        {
            l_mfProductCondSpec.setNewBuyMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyMinAmt(l_mfInfo.newBuyMinAmt);
        }
        
        if (l_mfInfo.newBuyUnitAmt == null)
        {
            l_mfProductCondSpec.setNewBuyUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyUnitAmt(l_mfInfo.newBuyUnitAmt);
        }
        
        if (l_mfInfo.addBuyMinQty == null)
        {
            l_mfProductCondSpec.setAddBuyMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyMinQty(l_mfInfo.addBuyMinQty);
        }
        
        if (l_mfInfo.addBuyUnitQty == null)
        {
            l_mfProductCondSpec.setAddBuyUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyUnitQty(l_mfInfo.addBuyUnitQty);
        }
        
        if (l_mfInfo.addBuyMinAmt == null)
        {
            l_mfProductCondSpec.setAddBuyMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyMinAmt(l_mfInfo.addBuyMinAmt);
        }
        
        if (l_mfInfo.addBuyUnitAmt == null)
        {
            l_mfProductCondSpec.setAddBuyUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyUnitAmt(l_mfInfo.addBuyUnitAmt);
        }

        l_mfProductCondSpec.setSellSelectable(l_mfInfo.sellSelectable);
        
        if (l_mfInfo.sellMinQty == null)
        {
            l_mfProductCondSpec.setSellMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSellMinQty(l_mfInfo.sellMinQty);
        }  
        
        if (l_mfInfo.sellUnitQty == null)
        {
            l_mfProductCondSpec.setSellUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSellUnitQty(l_mfInfo.sellUnitQty);
        }
        
        if (l_mfInfo.sellMinAmt == null)
        {
            l_mfProductCondSpec.setSellMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSellMinAmt(l_mfInfo.sellMinAmt);
        } 
        
        if (l_mfInfo.sellUnitAmt == null)
        {
            l_mfProductCondSpec.setSellUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSellUnitAmt(l_mfInfo.sellUnitAmt);
        } 

        l_mfProductCondSpec.setSwitchingSelectable(l_mfInfo.switchingSelectable);
        
        if (l_mfInfo.switchingMinQty == null)
        {
            l_mfProductCondSpec.setSwitchingMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingMinQty(l_mfInfo.switchingMinQty);
        }  
        
        if (l_mfInfo.switchingUnitQty == null)
        {
            l_mfProductCondSpec.setSwitchingUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingUnitQty(l_mfInfo.switchingUnitQty);
        }
        
        if (l_mfInfo.switchingMinAmt == null)
        {
            l_mfProductCondSpec.setSwitchingMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingMinAmt(l_mfInfo.switchingMinAmt);
        } 
        
        if (l_mfInfo.switchingUnitAmt == null)
        {
            l_mfProductCondSpec.setSwitchingUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingUnitAmt(l_mfInfo.switchingUnitAmt);
        } 
        
        //�E�w����@@�i��W�j�����N�G�X�g�f�[�^.�������.�w����@@�i��W�j
        if (l_mfInfo.applySelectable == null)
        {
            l_mfProductCondSpec.setApplySelectable(null);
        }
        else
        {
            l_mfProductCondSpec.setApplySelectable(l_mfInfo.applySelectable);
        }
        //�E�Œ�����i��W�j�����N�G�X�g�f�[�^.�������.�Œ�����i��W�j 
        if (l_mfInfo.applyMinQty == null)
        {
            l_mfProductCondSpec.setApplyMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyMinQty(l_mfInfo.applyMinQty);
        }
        //�E�P�ʌ����i��W�j�����N�G�X�g�f�[�^.�������.�P�ʌ����i��W�j 
        if (l_mfInfo.applyUnitQty == null)
        {
            l_mfProductCondSpec.setApplyUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyUnitQty(l_mfInfo.applyUnitQty);
        }
        //�E�Œ���z�i��W�j�����N�G�X�g�f�[�^.�������.�Œ���z�i��W�j 
        if (l_mfInfo.applyMinAmt == null)
        {
            l_mfProductCondSpec.setApplyMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyMinAmt(l_mfInfo.applyMinAmt);
        }
        //�E�P�ʋ��z�i��W�j�����N�G�X�g�f�[�^.�������.�P�ʋ��z�i��W�j
        if (l_mfInfo.applyUnitAmt == null)
        {
            l_mfProductCondSpec.setApplyUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyUnitAmt(l_mfInfo.applyUnitAmt);
        }
        
        l_mfProductCondSpec.setBuyPossibleDivTheDay(
                l_mfInfo.todayBuyPossDiv);
        l_mfProductCondSpec.setBuyPossibleDivTheNextDay(
                l_mfInfo.nextDayBuyPossDiv);
        l_mfProductCondSpec.setSellPossibleDivTheDay(
                l_mfInfo.todaySellPossDiv);
        l_mfProductCondSpec.setSellPossibleDivTheNextDay(
                l_mfInfo.nextDaySellPossDiv);
        l_mfProductCondSpec.setSwtPossibleDivTheDay(
                l_mfInfo.todaySwitchingPossDiv);
        l_mfProductCondSpec.setSwtPossibleDivTheNextDay(
                l_mfInfo.nextDaySwitchingPossDiv);
        //�E��W�\�敪�i�����������j�����N�G�X�g�f�[�^.�������.��W�\�敪�i�����������j 
        l_mfProductCondSpec.setApplyPossDivTheDay(
            l_mfInfo.todayApplyPossDiv);
        
        l_mfProductCondSpec.setOrderCloseStartTimeFull(
                l_mfInfo.orderCloseStartTimeFull);
        l_mfProductCondSpec.setOrderCloseEndTimeFull(
                l_mfInfo.orderCloseEndTimeFull);
        l_mfProductCondSpec.setOrderCloseStartTimeHalf(
                l_mfInfo.orderCloseStartTimeHalf);
        l_mfProductCondSpec.setOrderCloseEndTimeHalf(
                l_mfInfo.orderCloseEndTimeHalf);
        //�E��W�\�敪�i�����������j�����N�G�X�g�f�[�^.�������.��W�\�敪�i�����������j 
        l_mfProductCondSpec.setApplyPossDivTheNextDay(
            l_mfInfo.nextDayApplyPossDiv);

        //�O�ݍŒ���z�i�V�K���t�j�����N�G�X�g�f�[�^.�������.�O�ݍŒ���z�i�V�K���t�j
        l_mfProductCondSpec.setBuyFrgnMinAmtBuy(l_mfInfo.frgnMinAmtBuy);

        //�E�O�ݒP�ʋ��z�i�V�K���t�j�����N�G�X�g�f�[�^.�������.�O�ݒP�ʋ��z�i�V�K���t�j
        l_mfProductCondSpec.setBuyFrgnUnitAmtBuy(l_mfInfo.frgnUnitAmtBuy);

        //�E�O�ݍŒ���z�i�ǉ����t�j�����N�G�X�g�f�[�^.�������.�O�ݍŒ���z�i�ǉ����t�j
        l_mfProductCondSpec.setBuyFrgnMinAmtAdd(l_mfInfo.frgnMinAmtAdd);

        //�E�O�ݒP�ʋ��z�i�ǉ����t�j�����N�G�X�g�f�[�^.�������.�O�ݒP�ʋ��z�i�ǉ����t�j
        l_mfProductCondSpec.setBuyFrgnUnitAmtAdd(l_mfInfo.frgnUnitAmtAdd);

        //�E�O�ݍŒ���z�i���j�����N�G�X�g�f�[�^.�������.�O�ݍŒ���z�i���j
        l_mfProductCondSpec.setSellFrgnMinAmtSell(l_mfInfo.frgnMinAmtSell);

        //�E�O�ݒP�ʋ��z�i���j�����N�G�X�g�f�[�^.�������.�O�ݒP�ʋ��z�i���j
        l_mfProductCondSpec.setSellFrgnUnitAmtSell(l_mfInfo.frgnUnitAmtSell);

        // �V�j���������̓o�^�R�������{
        //    �g�����M�����}�l�[�W��.validate��������()���R�[������B
        // --------- Start -------------- �g�����M�����}�l�[�W���̎擾���s��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
        // --------- End -------------- �g�����M�����}�l�[�W���̎擾���s��
        l_mfProductManager.validateProductCond(
                l_institution, 
                l_mfProductCondSpec);

        WEB3AdminMutualConditionsConfirmResponse l_response = 
            (WEB3AdminMutualConditionsConfirmResponse)l_request.createResponse();

        return l_response;
    }
    
    /**
     * (is�c�Ɠ�)<BR>
     * ����.�Ώۓ��t���x�����ǂ����𔻒肷��B <BR>
     * <BR>
     * �P�j����.�Ώۓ��t���h�y�j���h�܂��́h���j���h�̏ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �Q�j�ȉ��̏����Łu�J�����_�[�e�[�u���v����������B <BR>
     * <BR>
     * [��������] <BR>
     * �@@���t������.�Ώۓ��t and <BR>
     * �@@�c�Ɠ��敪���h��c�Ɠ��h <BR>
     * <BR>
     * �|�������ʂ̌�����0���̏ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �R�j�C�O�s��J�����_�[.is�x��()���R�[�����Atrue���ԋp���ꂽ�ꍇ�Afalse��ԋp����B <BR>
     * [is�x���ɓn������] <BR>
     * �@@�،���ЃR�[�h������.�،���ЃR�[�h <BR>
     * �@@�����R�[�h������.�����R�[�h <BR>
     * �@@���t������.�Ώۓ��t <BR>
     * <BR>
     * �S�j��L�ȊO�̏ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_strProductCode -  �����R�[�h<BR>
     * @@param l_dat - �Ώۓ��t<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 40E3F14000E6
     */
    public boolean isBizDate(
            String l_strInstitutionCode,
            String l_strProductCode,
            Timestamp l_dat) throws WEB3BaseException
    {
        final String l_strMethodName = "validateProductConditionsRegist(" 
            + "WEB3AdminMutualConditionsConfirmResponse l_request)";
        log.entering(l_strMethodName);
        
        boolean l_blnReturnValue = true;
        try
        {
            String l_strBizDateType = 
                WEB3GentradeTradingTimeManagement.getBizDateType(l_dat);   
            if(WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
            {
                l_blnReturnValue = false;
            }
            else
            {
                WEB3AdminMutualFrgncal l_adminMutualFrgncal = 
                    new WEB3AdminMutualFrgncal();
                boolean l_blnIsHoliday = l_adminMutualFrgncal.isHoliday(
                        l_strInstitutionCode,
                        l_strProductCode,
                        l_dat);
                l_blnReturnValue = !l_blnIsHoliday;
            }
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("�Y������،���ЃI�u�W�F�N�g�܂��͓��M�����I�u�W�F�N�g������܂���!"); 
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(l_strMethodName);
        return l_blnReturnValue;
    }
    
    /**
     * (is�����c�Ɠ�)<BR>
     *����.�Ώۓ��t���x�����ǂ����𔻒肷��B <BR>
     *<BR>
     *�P�j����.�Ώۓ��t���h�y�j���h�܂��́h���j���h�̏ꍇ�Afalse��ԋp����B<BR> 
     *<BR>
     *�Q�j�ȉ��̏����Łu�J�����_�[�e�[�u���v����������B<BR>
     *[��������] <BR>
�@@   *���t������.�Ώۓ��t and <BR>
�@@   *�c�Ɠ��敪���h��c�Ɠ��h <BR>
     *�|�������ʂ̌�����0���̏ꍇ�Afalse��ԋp����B<BR>
     *<BR>
     *�R�j��L�ȊO�̏ꍇ�Atrue��ԋp����B<BR>
     * @@param l_timestap - �Ώۓ��t
     * @@return l_bln
     * @@throws WEB3BaseException
     * @@roseuid 406932820270
     */
    public boolean isCalendarBizDate(Timestamp l_tsObjectDate)throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isCalendarBizDate(WEB3AdminMutualConditionsServiceImpl)";
        log.entering(STR_METHOD_NAME);       
        
        Date l_dateObjectDate = WEB3DateUtility.getDate(
            WEB3DateUtility.formatDate(l_tsObjectDate,"yyyyMMdd"),"yyyyMMdd");
        l_tsObjectDate = new Timestamp(l_dateObjectDate.getTime());
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_tsObjectDate);
        
        int l_intWeekDiv = l_calendar.get(Calendar.DAY_OF_WEEK) - 1;
        
        //�P�j����.�Ώۓ��t���h�y�j���h�܂��́h���j���h�̏ꍇ�Afalse��ԋp����B 
        if (l_intWeekDiv == 6 || l_intWeekDiv == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�ȉ��̏����Łu�J�����_�[�e�[�u���v����������B
        try
        {
            String l_strWhereClause = 
                "holiday = ? and biz_date_type = ?";
            //DataQueryException,DataNetworkException
            List l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        CalendarRow.TYPE,
                    l_strWhereClause,
                    new Object[] { 
                        l_tsObjectDate, 
                        WEB3BizDateTypeDef.NO_BIZ_DATE });

            if (l_lisRows.size() > 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;                      
            }           
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }
}
@
