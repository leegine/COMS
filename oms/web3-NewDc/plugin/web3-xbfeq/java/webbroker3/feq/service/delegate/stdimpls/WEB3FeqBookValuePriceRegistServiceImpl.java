head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBookValuePriceRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������뉿�P���o�^�T�[�r�XImpl(WEB3FeqBookValuePriceRegistServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 ���� (���u) �V�K�쐬   
                 : 2005/08/03 �A�C��(���u) ���r���[       
Revesion History : 2008/01/14 �đo�g(���u) ���f��No.371�ANo.374�ANo.376�ANo.380�A�c�a�X�V�d�lNo.088�ANo.089
Revesion History : 2008/01/29 �đo�g(���u) ���f��No.388
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.message.WEB3FeqBookPriceConfirmRequest;
import webbroker3.feq.message.WEB3FeqBookPriceConfirmResponse;
import webbroker3.feq.message.WEB3FeqBookPriceInputRequest;
import webbroker3.feq.message.WEB3FeqBookPriceInputResponse;
import webbroker3.feq.message.WEB3FeqBookPriceRegistRequest;
import webbroker3.feq.message.WEB3FeqBookPriceRegistResponse;
import webbroker3.feq.service.delegate.WEB3FeqBookValuePriceRegistService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������뉿�P���o�^�T�[�r�XImpl)<BR>
 * �O�������뉿�P���o�^�T�[�r�X�����N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqBookValuePriceRegistServiceImpl extends WEB3FeqClientRequestService 
    implements WEB3FeqBookValuePriceRegistService 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FeqBookValuePriceRegistServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F802EE
     */
    public WEB3FeqBookValuePriceRegistServiceImpl() 
    {
     
    }
    
    /**
     * �O�������뉿�P���o�^�T�[�r�X���s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^���A<BR>
     * [�O�������뉿�P���o�^���̓��N�G�X�g�̏ꍇ]<BR>
     * �@@this.���͉��()���R�[������B<BR>
     * <BR>
     * [�O�������뉿�P���m�F���N�G�X�g�̏ꍇ]<BR>
     * �@@this.validate�뉿�P��()���R�[������B<BR>
     * <BR>
     * [�O�������뉿�P���o�^���N�G�X�g�̏ꍇ]<BR>
     * �@@this.submit�뉿�P��()���R�[������B<BR>
     * @@param l_request - <���N�G�X�g><BR>
     * ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A937900079
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
        if (l_request instanceof WEB3FeqBookPriceInputRequest)
        {
            //get���͉��
            l_response =
                this.getInputScreen(
                    (WEB3FeqBookPriceInputRequest) l_request);
        }
        else if (l_request instanceof WEB3FeqBookPriceConfirmRequest)
        {
            //�O�������뉿�P���m�F���N�G�X�g�̏ꍇ
            l_response =
                this.validateBookValuePrice(
                    (WEB3FeqBookPriceConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3FeqBookPriceRegistRequest)
        {
            //validate����
            l_response =
                this.submitBookValuePrice(
                    (WEB3FeqBookPriceRegistRequest) l_request);
        }
        else
        {
            log.debug(
                "���N�G�X�g�f�[�^��"
                    + " WEB3FeqBookPriceInputRequest "
                    + "�� WEB3FeqBookPriceRegistRequest �ȊO�ł���, but is "
                    + l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
  
        log.exiting(STR_METHOD_NAME); 
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * �O�������뉿�P���o�^���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O���뉿�P���o�^�jget���͉�ʁv�Q��<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�O���뉿�P���o�^�v(�O���뉿�P���o�^�jget���͉��)<BR>
     * �@@�@@:  1.3 (*)��ʌ����`�F�b�N<BR> 
     * �@@�@@(��ʌ����`�F�b�N)<BR>
     *     getAsset()�̖߂�l.�ŋ敪 != "���"�̏ꍇ�A<BR>
     *     �u�����ΏۊO�f�[�^�v�̗�O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02178<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������뉿�P���o�^���̓��N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.feq.message.WEB3FeqBookPriceInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A94C3C01F0
     */
    protected WEB3FeqBookPriceInputResponse getInputScreen(WEB3FeqBookPriceInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqBookPriceInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);    
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2  getAsset(arg0 : long)
        //�ۗL���Y���擾����B 
        //[����] 
        //arg0�F�@@���N�G�X�g�f�[�^.�ۗL���YID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqPositionManager l_feqPositionManager =
            (WEB3FeqPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();
        //�ۗL���Y        
        Asset l_asset = null;
        long l_lngAsstId = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.assetId))
        {
            l_lngAsstId = Long.parseLong(l_request.assetId);
        }
        try
        {
            l_asset = 
                l_feqPositionManager.getAsset(l_lngAsstId);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�ۗL���Y.�ۗL���YID
        long l_lngAssetId = l_asset.getAssetId();
        
        //1.3 (*)��ʌ����`�F�b�N
        //(��ʌ����`�F�b�N)
        //getAsset()�̖߂�l.�ŋ敪 != "���"�̏ꍇ�A
        //�u�����ΏۊO�f�[�^�v�̗�O���X���[����B
        TaxTypeEnum l_taxType = l_asset.getTaxType();
        log.debug("getAsset()�̖߂�l.�ŋ敪 = " + l_taxType);
        if (!TaxTypeEnum.NORMAL.equals(l_taxType))
        {
            log.debug("�����ΏۊO�f�[�^�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ΏۊO�f�[�^�B");
        }
              
        //1.4 getProduct(arg0 : long)
        //�O�������������擾����B 
        //[����] 
        //arg0�F�@@�ۗL���Y.����ID
        
        //����ID 
        long l_lngProductId = l_asset.getProduct().getProductId();
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        WEB3FeqProduct l_feqProduct =
            (WEB3FeqProduct) l_feqProductManager.getFeqProduct(l_lngProductId);
        
        //1.5 createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3FeqBookPriceInputResponse l_response = 
            (WEB3FeqBookPriceInputResponse) l_request.createResponse();
            
        //1.6 (*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�ۗL���YID  ���@@�ۗL���Y.�ۗL���YID
        l_response.assetId = l_lngAssetId + "";
        
        //�����R�[�h       ���@@getProduct()�̖߂�l.�����R�[�h
        l_response.productCode = l_feqProduct.getProductCode();
                
        //���n�����R�[�h ���@@getProduct()�̖߂�l.���n�����R�[�h
        l_response.localProductCode = l_feqProduct.getOffshoreProductCode();
        
        //������     ���@@getProduct()�̖߂�l.get�\��������()�̖߂�l
        l_response.productName = l_feqProduct.getDisplayProductName();
        
        //�����敪   ���ۗL���Y.�ŋ敪 == "���"�̏ꍇ�A"���"���Z�b�g�B
        String l_strTaxType = null;
        if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
        {
            l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else 
        {
            if (TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
            {
                l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
        }
        l_response.taxType = l_strTaxType;
        
        AssetRow l_assetRow = (AssetRow) l_asset.getDataSourceObject();
        
        //�c������ ���ۗL���Y.���� + �ۗL���Y.���t�s�\����
        double l_dbBalanceQuantity =  
            l_assetRow.getQuantity() + l_assetRow.getQuantityCannotSell();
        l_response.balanceQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dbBalanceQuantity);
        
        //���t�\���� ���ۗL���Y.���� - �ۗL���Y.getLockedQuantity()
        double l_dblSellPossQuantity = 
            l_asset.getQuantity() - l_asset.getLockedQuantity();
        l_response.sellPossQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblSellPossQuantity);
        
        //����������   ���ۗL���Y.getLockedQuantity()
        l_response.orderedQuantity = 
            WEB3StringTypeUtility.formatNumber(l_asset.getLockedQuantity());
        
        //���t�s�\����  ���ۗL���Y.���t�s�\����
        l_response.sellImpossQuantity = 
            WEB3StringTypeUtility.formatNumber(l_assetRow.getQuantityCannotSell());

        //���͕뉿�P��  ���ۗL���Y.���͕뉿�P��
        if (l_assetRow.getInputBookValueIsNull())
        {
            l_response.inputBookPrice = null;
        }
        else
        {
            l_response.inputBookPrice = 
                WEB3StringTypeUtility.formatNumber(l_assetRow.getInputBookValue());
        }
            
        //�뉿�P�����͓���    ���ۗL���Y.�뉿�P�����͓���
        l_response.bookPriceInputDate = l_assetRow.getInputTimestamp();
        
        //�T�Z�뉿�P��  ���O�������v�Z�T�[�r�X.calc�T�Z�뉿�P��(
        //  �ۗL���Y.�뉿(�뉿�P���v�Z�p), �ۗL���Y.����(�뉿�P���v�Z�p))
        //�@@�@@�������_6�ʂ��l�̌ܓ�����B

        //�O�������v�Z�T�[�r�X
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_feqBizLogicProvider =
            (WEB3FeqBizLogicProvider) l_tradingModule.getBizLogicProvider();
        BigDecimal l_bdPrice =
            l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                l_assetRow.getBookValue(),
                l_assetRow.getQuantityForBookValue());
        BigDecimal l_bdEstimatedBookPrice = l_bdPrice.setScale(5, BigDecimal.ROUND_HALF_UP);
        l_response.estimatedBookPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdEstimatedBookPrice.doubleValue());
                
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�뉿�P��)<BR>
     * �O�������뉿�P���o�^�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O���뉿�P���o�^�jsubmit�뉿�P���v�Q��<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�O���뉿�P���o�^�v(�O���뉿�P���o�^�jsubmit�뉿�P��)<BR>
     * �@@�@@:  1.5 (*)�����`�F�b�N<BR> 
     *     (�����`�F�b�N)<BR> 
     *     �v�Z�����뉿(*1)�̌����@@>= 12���̏ꍇ�A<BR> 
     *     �u�v�Z��뉿�G���[�i�������߁j�v�̗�O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01921<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * �@@ �O���뉿�P���o�^�v(�O���뉿�P���o�^�jsubmit�뉿�P��)<BR>
     * �@@�@@:  1.6 (*)��ʌ����`�F�b�N<BR> 
     *     (��ʌ����`�F�b�N)<BR> 
     *     getAsset()�̖߂�l.�ŋ敪 != "���"�̏ꍇ�A<BR>
     *     �u�����ΏۊO�f�[�^�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02178<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������뉿�P���o�^���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.feq.message.WEB3FeqBookPriceRegistResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A94C3C0210
     */
    protected WEB3FeqBookPriceRegistResponse submitBookValuePrice(WEB3FeqBookPriceRegistRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitBookValuePrice(WEB3FeqBookPriceRegistRequest l_request) ";
        log.entering(STR_METHOD_NAME);    
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2 get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();    
            
        //1.3 lock����(String, String, String)   
        //���������b�N����B 
        //[����] 
        //�،���ЃR�[�h�F�@@�⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.getMainAccount().���X�R�[�h 
        //�����R�[�h�F�@@�⏕����.getMainAccount().�����R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        //���X�R�[�h
        String l_strBranchCode = 
            l_subAccount.getMainAccount().getBranch().getBranchCode();
        //�����R�[�h
        String l_strAccountCode = 
            l_subAccount.getMainAccount().getAccountCode();     
        l_accountManager.lockAccount(
            l_strInstitutionCode, 
            l_strBranchCode, 
            l_strAccountCode);
                
        //1.4  getAsset(arg0 : long)
        //�ۗL���Y���擾����B 
        //[����] 
        //arg0�F�@@���N�G�X�g�f�[�^.�ۗL���YID
        WEB3FeqPositionManager l_feqPositionManager =
            (WEB3FeqPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();
        //�ۗL���Y        
        Asset l_asset = null;
        long l_lngAsstId = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.assetId))
        {
            l_lngAsstId = Long.parseLong(l_request.assetId);
        }
        try
        {
            l_asset = 
                l_feqPositionManager.getAsset(l_lngAsstId);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //validate�c������(Asset, Double)
        //[����]
        //�ۗL���Y�@@�F�@@getAsset()�̖߂�l
        //�c�������@@�F�@@���N�G�X�g�f�[�^�D�c������
        double l_dblBalanceQuantity = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.balanceQuantity))
        {
            l_dblBalanceQuantity = Double.parseDouble(l_request.balanceQuantity);
        }
        this.validateBalanceQuantity(
            l_asset,
            l_dblBalanceQuantity);

        //1.6 (��ʌ����`�F�b�N)
        //getAsset()�̖߂�l.�ŋ敪 != "���"�̏ꍇ�A
        //�u�����ΏۊO�f�[�^�v�̗�O���X���[����B
        
        TaxTypeEnum l_taxType = l_asset.getTaxType();
        log.debug("getAsset()�̖߂�l.�ŋ敪 = " + l_taxType);
        if (!TaxTypeEnum.NORMAL.equals(l_taxType))
        {
            log.debug("�����ΏۊO�f�[�^�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ΏۊO�f�[�^�B");
        }
        
        //1.7 create�ۗL���YParams(Asset, double)
        //�ۗL���YParams���쐬����B 
        //[����] 
        //�ۗL���Y�F�@@getAsset()�̖߂�l 
        //�뉿�P���F�@@���N�G�X�g�f�[�^.�ύX��뉿�P��
        //�뉿���z�F�@@���N�G�X�g�f�[�^.�v�Z�p���͕뉿���z
        //�c�������F�@@���N�G�X�g�f�[�^.�c������
        AssetParams l_assetParams =
            this.createAssetParams(
                l_asset, 
                Double.parseDouble(l_request.aftBookPrice),
                Double.parseDouble(l_request.aftBookAmount),
                l_dblBalanceQuantity);
        
        //1.8 updateRow(l_row : Row)
        //�ۗL���Y���X�V����B 
        //[����] 
        //�ۗL���YParams�F�@@create�ۗL���YParams()�̖߂�l
        try
        {
            WEB3DataAccessUtility.updateRow(l_assetParams);
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.9 createResponse( )
        WEB3FeqBookPriceRegistResponse l_response = 
            (WEB3FeqBookPriceRegistResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (create�ۗL���YParams)<BR>
     * �����ۗ̕L���Y����A�ۗL���YParams�𐶐�����B<BR>
     * <BR>
     * �P�jAssetRow�I�u�W�F�N�g���擾����B<BR>
     * �@@�p�����[�^.�ۗL���Y.getDataSourceObject()���\�b�h��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �Q�jAssetParams�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �R�j�f�[�^�̃R�s�[<BR>
     * �@@GtlUtils.copyRow2Params()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[copyRow2Params()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�P�j�̖߂�l<BR>
     * �@@�@@arg1�F�@@�Q�j�̖߂�l<BR>
     * <BR>
     * �S�j�v���p�e�B�Z�b�g<BR>
     * �@@��������AssetParams�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����(�뉿�P���v�Z�p) = �p�����[�^�D�c������<BR>
     * �@@�뉿(�뉿�P���v�Z�p) =  �p�����[�^�D�뉿���z<BR>
     * �@@���͕뉿�P�� = �p�����[�^.�뉿�P��<BR>
     * �@@�뉿�P�����͓��� = ���ݎ���(*1)<BR>
     * �@@�X�V���t = ���ݎ���<BR>
     * <BR>
     * �T�j�v���p�e�B�Z�b�g�����ۗL���YParams��ԋp����B<BR>
     * <BR>
     * (*1)<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG)<BR>
     * �ɂĎ擾�������ݎ����B<BR>
     * @@param l_asset - (�ۗL���Y)<BR>
     * �ۗL���Y�I�u�W�F�N�g
     * @@param l_dblBookPrice - (�뉿�P��)<BR>
     * �뉿�P��
     * @@param l_dblBookAmount - (�뉿���z)<BR>
     * �뉿���z
     * @@param l_dblBalanceQuantity - (�c������)<BR>
     * �c������
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams
     * @@roseuid 42A95515026D
     */
    protected AssetParams createAssetParams(
        Asset l_asset,
        double l_dblBookPrice,
        double l_dblBookAmount,
        double l_dblBalanceQuantity) 
    {
        final String STR_METHOD_NAME = 
            "createAssetParams(Asset, double, double, double)";
        log.entering(STR_METHOD_NAME);    
        
        //�����ۗ̕L���Y����A�ۗL���YParams�𐶐�����B 
        //�P�jAssetRow�I�u�W�F�N�g���擾����B 
        //�p�����[�^.�ۗL���Y.getDataSourceObject()���\�b�h�� 
        //�R�[������B 
        AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();
        
        //�Q�jAssetParams�C���X�^���X�𐶐�����B 
        //�R�j�f�[�^�̃R�s�[ 
        //GtlUtils.copyRow2Params()���\�b�h���R�[������B 
        //[copyRow2Params()�ɃZ�b�g����p�����[�^] 
        //arg0�F�@@�P�j�̖߂�l 
        //arg1�F�@@�Q�j�̖߂�l 
        AssetParams l_assetParams = new AssetParams(l_assetRow);
        
        //�S�j�v���p�e�B�Z�b�g 
        //��������AssetParams�Ɉȉ��̃v���p�e�B���Z�b�g����B 
        //����(�뉿�P���v�Z�p) = �p�����[�^�D�c������
        l_assetParams.setQuantityForBookValue(l_dblBalanceQuantity);

        //�뉿(�뉿�P���v�Z�p) =  �p�����[�^�D�뉿���z
        l_assetParams.setBookValue(l_dblBookAmount);
        
        //���͕뉿�P�� = �p�����[�^.�뉿�P��  
        l_assetParams.setInputBookValue(l_dblBookPrice);

        //�뉿�P�����͓��� = ���ݎ���(*1) 
        //(*1) 
        //ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG) 
        //�ɂĎ擾�������ݎ����B
        
        //���ݎ���
        Timestamp l_tsCurrentTime = GtlUtils.getSystemTimestamp(); 
        //�뉿�P�����͓���
        l_assetParams.setInputTimestamp(l_tsCurrentTime);

        //�X�V���t = ���ݎ��� 
        l_assetParams.setLastUpdatedTimestamp(l_tsCurrentTime);
        
        //�T�j�v���p�e�B�Z�b�g�����ۗL���YParams��ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return l_assetParams;
    }

    /**
     * (validate�뉿�P��)<BR>
     * �O�������뉿�P���m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O���뉿�P���o�^�jvalidate�뉿�P���v�Q��<BR>
     * <BR>
     * =============================================== <BR>
     * �@@�@@��̈ʒu : (*)�����`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@calc�T�Z�뉿�P��()�̖߂�l.�������̌��� > 8���̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u�ύX��T�Z�뉿�P�����s���Ȓl�v�̗�O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_02110<BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������뉿�P���o�^�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3FeqBookPriceConfirmResponse
     * @@throws WEB3BaseException
     */
    protected WEB3FeqBookPriceConfirmResponse validateBookValuePrice(
        WEB3FeqBookPriceConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateBookValuePrice(WEB3FeqBookPriceConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqPositionManager l_feqPositionManager =
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();

        //getAsset(arg0 : long)
        //[����]
        //  arg0 �F ���N�G�X�g�f�[�^�D�ۗL���YID
        Asset l_asset = null;
        try
        {
            l_asset =
                l_feqPositionManager.getAsset(Long.parseLong(l_request.assetId));
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //validate�c������(Asset, Double)
        //[����]
        // �ۗL���Y�@@�F�@@getAsset()�̖߂�l
        // �c�������@@�F�@@���N�G�X�g�f�[�^�D�c������
        double l_dblBalanceQuantity = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.balanceQuantity))
        {
            l_dblBalanceQuantity = Double.parseDouble(l_request.balanceQuantity);
        }
        this.validateBalanceQuantity(
            l_asset,
            l_dblBalanceQuantity);

        WEB3FeqBizLogicProvider l_feqBizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();

        //calc�T�Z�뉿�P��(double, double)
        //[����]
        //�@@�뉿�@@�F�@@���N�G�X�g�f�[�^�D�v�Z�p���͕뉿�P��
        //�@@���ʁ@@�F�@@���N�G�X�g�f�[�^�D�c������
        BigDecimal l_bdPrice = l_feqBizLogicProvider.calcEstimatedBookValuePrice(
            Double.parseDouble(l_request.aftBookAmount),
            l_dblBalanceQuantity);

        //calc�T�Z�뉿�P��()�̖߂�l.�������̌��� > 8���̏ꍇ
        //�u�ύX��T�Z�뉿�P�����s���Ȓl�v�̗�O���X���[����B
        String l_strPrice = l_bdPrice.toString();
        if (l_strPrice.split("\\.")[0].length() > 8)
        {
            log.debug("�ύX��T�Z�뉿�P�����s���Ȓl�ɂȂ��Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02110,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ύX��T�Z�뉿�P�����s���Ȓl�ɂȂ��Ă��܂��B");
        }

        //createResponse( )
        WEB3FeqBookPriceConfirmResponse l_response =
            (WEB3FeqBookPriceConfirmResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //�ύX��T�Z�뉿�P���@@���@@calc�T�Z�뉿�P��()�̖߂�l�D�����_��6�ʂ��l�̌ܓ�
        BigDecimal l_bdAftBookPrice = l_bdPrice.setScale(5, BigDecimal.ROUND_HALF_UP);
        l_response.aftBookPrice =
            WEB3StringTypeUtility.formatNumber(l_bdAftBookPrice.doubleValue());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�c������)<BR>
     * �����D�c���������A<BR>
     * �����D�ۗL���Y�̎c�������i���ʁ{���t�s�\���ʁj�Ɠ������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�ۗL���Y�̎c�������̎Z�o<BR>
     * �@@�@@�����D�ۗL���Y�D���ʁ@@�{�@@�����D�ۗL���Y�D���t�s�\���ʁ@@���v�Z����B<BR>
     * <BR>
     * �Q�j�@@�c�������̃`�F�b�N<BR>
     * �@@�P�j�ŎZ�o�����l�@@�ƁA�����D�c���������r���A<BR>
     * �@@�l���ق�ꍇ�́u�f�[�^�s�����G���[�i�c�������f�[�^�s�����j�v���X���[����B<BR>
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_02982<BR>
     * <BR>
     * @@param l_asset - (�ۗL���Y)<BR>
     * �ۗL���Y<BR>
     * @@param l_dblBalanceQuantity - (�c������)<BR>
     * �c������<BR>
     * @@throws WEB3BaseException
     */
    protected void validateBalanceQuantity(
        Asset l_asset,
        double l_dblBalanceQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateBalanceQuantity(Asset, double)";
        log.entering(STR_METHOD_NAME);

        if (l_asset == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();

        //�ۗL���Y�̎c�������̎Z�o
        //  �����D�ۗL���Y�D���ʁ@@�{�@@�����D�ۗL���Y�D���t�s�\���ʁ@@���v�Z����B
        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_assetRow.getQuantity()));
        BigDecimal l_bdQuantityCannotSell =
            new BigDecimal(String.valueOf(l_assetRow.getQuantityCannotSell()));

        double l_dblAssetBalanceQuantity =
            (l_bdQuantity.add(l_bdQuantityCannotSell)).doubleValue();

        //�Z�o�����l�@@�ƁA�����D�c���������r���A
        //�@@�l���ق�ꍇ�́u�f�[�^�s�����G���[�i�c�������f�[�^�s�����j�v���X���[����B
        if (l_dblAssetBalanceQuantity != l_dblBalanceQuantity)
        {
            log.debug("�f�[�^�s�����G���[�i�c�������f�[�^�s�����j");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02982,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�i�c�������f�[�^�s�����j");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
