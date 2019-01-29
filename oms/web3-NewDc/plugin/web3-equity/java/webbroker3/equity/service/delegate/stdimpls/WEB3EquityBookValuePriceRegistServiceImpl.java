head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBookValuePriceRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������뉿�P���o�^�T�[�r�X�����N���X(WEB3EquityBookValuePriceRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 �򑺁@@�m�m(SRA) �V�K�쐬
                   2006/08/29 �ęԍg(���u) �d�l�ύX���f��970
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityAsset;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.message.WEB3EquityBookPriceInputRequest;
import webbroker3.equity.message.WEB3EquityBookPriceInputResponse;
import webbroker3.equity.message.WEB3EquityBookPriceRegistRequest;
import webbroker3.equity.message.WEB3EquityBookPriceRegistResponse;
import webbroker3.equity.service.delegate.WEB3EquityBookValuePriceRegistService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i���������뉿�P���o�^�T�[�r�XImpl�j�B<BR>
 * <BR>
 * ���������뉿�P���o�^�T�[�r�X�����N���X<BR>
 */
public class WEB3EquityBookValuePriceRegistServiceImpl
extends WEB3EquityClientRequestService
implements WEB3EquityBookValuePriceRegistService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBookValuePriceRegistServiceImpl.class);    
        
    /**
     * @@roseuid 4206EF8F02EA
     */
    public WEB3EquityBookValuePriceRegistServiceImpl() 
    {
    
    }
   
    /**
     * ���������뉿�P���o�^�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * �����������뉿�P���o�^���̓��N�G�X�g�̏ꍇ<BR>
     * �@@this.get���͉��()���\�b�h���R�[������B<BR>
     * <BR>
     * �����������뉿�P���o�^���N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�뉿�P���o�^()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41B92550005B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
       final String STR_METHOD_NAME =
           "WEB3EquityBookValuePriceRegistServiceImpl.execute()";
       log.entering(STR_METHOD_NAME);
        
       if (l_request == null)
       {
           log.error("�p�����[�^.���N�G�X�g�f�[�^��null�ł��B");
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME);
       }

       WEB3GenResponse l_response = null;
        
       if (l_request instanceof WEB3EquityBookPriceInputRequest)
       {
           l_response = 
               this.getInputScreen((WEB3EquityBookPriceInputRequest)l_request);
       }
       else if (l_request instanceof WEB3EquityBookPriceRegistRequest)
       {
           l_response = 
               this.submitBookValuePrice((WEB3EquityBookPriceRegistRequest)l_request);
       }
       else
       {
           log.error("�p�����[�^.���N�G�X�g�f�[�^�̌^���s���ł��B");
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80018,
               getClass().getName() + "." + STR_METHOD_NAME);
       }
        
       log.exiting(STR_METHOD_NAME);
       return l_response;
    }
   
    /**
     * ���������뉿�P���o�^���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���������뉿�P���o�^�T�[�r�X)get���͉�ʁv<BR>
     * �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)���������뉿�P���o�^���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3EquityBookPriceInputResponse<BR>
     * @@throws WEB3BaseException
     * ���X�|���X
     * @@roseuid 41B9259E004B
     */
    protected WEB3EquityBookPriceInputResponse getInputScreen(WEB3EquityBookPriceInputRequest l_request) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3EquityBookPriceInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        // validate
        l_request.validate();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // �g���|�W�V�����}�l�[�W���擾
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            
        // getAsset
        WEB3EquityAsset l_asset = null;
        try
        {
            l_asset =
                (WEB3EquityAsset)l_positionManager.getAsset(Long.parseLong(l_request.assetId));
        } catch (NotFoundException l_ex) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ۗL���YID:[" + l_request.assetId + "]�ɊY������f�[�^�����݂��܂���B");
        }
        
        // ��ʌ����`�F�b�N
        TaxTypeEnum l_taxTypeEnum = l_asset.getTaxType();
        if (!(TaxTypeEnum.NORMAL.equals(l_taxTypeEnum) ||
            TaxTypeEnum.STOCK_OPTION.equals(l_taxTypeEnum)))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ۗL���YID:[" + l_request.assetId + "]�̌����敪�F��ʂ̌������X�g�b�N�I�v�V���������ł͂���܂���B");
        }
        
        // getProduct
        WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_asset.getProduct();
        
        // ���X�|���X�f�[�^����&�v���p�e�B�Z�b�g
        WEB3EquityBookPriceInputResponse l_response =
            (WEB3EquityBookPriceInputResponse)l_request.createResponse();
        
        // �ۗL���YID
        l_response.assetId = String.valueOf(l_asset.getAssetId());
        // �����R�[�h
        l_response.productCode = l_eqtypeProduct.getProductCode();
        // ������
        EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_eqtypeProduct.getDataSourceObject();
        l_response.productName = l_eqtypeProductRow.getStandardName();
        // �����敪
        l_response.taxType = WEB3EquityDataAdapter.getTaxType(l_taxTypeEnum);
        // �c������
        AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();
        double l_dblBalanceQuantity = l_asset.getQuantity() + l_assetRow.getQuantityCannotSell();
        double l_dblLockedQuantity = l_asset.getLockedQuantity();
        l_response.balanceQuantity = WEB3StringTypeUtility.formatNumber(l_dblBalanceQuantity);
        
        // ���t�\����
        l_response.sellPossQuantity = WEB3StringTypeUtility.formatNumber(l_asset.getQuantity() - l_dblLockedQuantity);
        // ����������
        l_response.orderedQuantity = WEB3StringTypeUtility.formatNumber(l_dblLockedQuantity);
        // ���t�s�\����
        l_response.sellImpossQuantity = WEB3StringTypeUtility.formatNumber(l_assetRow.getQuantityCannotSell());
        // ���͕뉿�P��&�뉿�P�����͓���
        if (l_assetRow.getInputBookValueIsNull())
        {
            l_response.inputBookPrice = null;
            l_response.bookPriceInputDate = null;
        }
        else
        {
            l_response.inputBookPrice = WEB3StringTypeUtility.formatNumber(l_assetRow.getInputBookValue());
            l_response.bookPriceInputDate = l_assetRow.getInputTimestamp();
        }
        // �T�Z�뉿�P��
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblBookValuePrice = l_bizLogicProvider.calcEstimatedBookPrice(l_assetRow.getBookValue(), l_assetRow.getQuantityForBookValue(), 0);
        
        l_response.estimatedBookPrice = WEB3StringTypeUtility.formatNumber(l_dblBookValuePrice);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * ���������뉿�P���o�^�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���������뉿�P���o�^�T�[�r�X)submit�뉿�P���o�^�v<BR>
     * �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) ���������뉿�P���o�^���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3EquityBookPriceRegistResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41B9259E006A
     */
    protected WEB3EquityBookPriceRegistResponse submitBookValuePrice(WEB3EquityBookPriceRegistRequest l_request) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitBookValuePrice(WEB3EquityBookPriceRegistRequest)";
        log.entering(STR_METHOD_NAME);
        
        // validate
        l_request.validate();
        
        // get�⏕����
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        // �g���A�J�E���g�}�l�[�W���A�g���|�W�V�����}�l�[�W���擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            
        // �ڋq���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        WEB3GentradeInstitution l_institution =
            (WEB3GentradeInstitution)l_subAccount.getInstitution();
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
            
        // lock����
        l_accountManager.lockAccount(
            l_institution.getInstitutionCode(),
            l_branch.getBranchCode(),
            l_mainAccount.getAccountCode());
        
        // getAsset
        WEB3EquityAsset l_asset = null;
        try
        {
            l_asset =
                (WEB3EquityAsset)l_positionManager.getAsset(Long.parseLong(l_request.assetId));
        } catch (NotFoundException l_ex) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ۗL���YID:[" + l_request.assetId + "]�ɊY������f�[�^�����݂��܂���B");
        }
        
        // �v�Z�����뉿�̎Z�o
        AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();
        double l_dblAfterBookValuePrice = Double.parseDouble(l_request.aftBookPrice);
        // �v�Z�����뉿 = (getAsset()�̖߂�l.���� + getAsset()�̖߂�l.���t�s�\����) * ���N�G�X�g�f�[�^.�ύX��뉿�P��
        double l_dblCalcBookValue =
            (l_asset.getQuantity() + l_assetRow.getQuantityCannotSell()) * l_dblAfterBookValuePrice;
        
        // �v�Z�����뉿�̌����`�F�b�N
        if (WEB3StringTypeUtility.formatNumber(l_dblCalcBookValue).length() >= 12)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01921,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���͂����뉿�P���̒l���傫�����܂��B(�v�Z��̕뉿�̌�����12���ȏ�)");
        }
        
        // ��ʌ����`�F�b�N
        TaxTypeEnum l_taxTypeEnum = l_asset.getTaxType();
        if (!(TaxTypeEnum.NORMAL.equals(l_taxTypeEnum) ||
            TaxTypeEnum.STOCK_OPTION.equals(l_taxTypeEnum)))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ۗL���YID:[" + l_request.assetId + "]�̌����敪�F��ʂ̌������X�g�b�N�I�v�V���������ł͂���܂���B");
        }
        
        // create�ۗL���YParams
        AssetParams l_assetParams = this.createAssetParams(l_asset, l_dblAfterBookValuePrice);
        
        // �ۗL���Y���X�V����
        try
        {
            WEB3DataAccessUtility.updateRow(l_assetParams);
        } catch (DataException l_ex) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ۗL���Y�̍X�V�����Ɏ��s���܂����B", l_ex);
        }
        
        // ���X�|���X�f�[�^����
        WEB3EquityBookPriceRegistResponse l_response =
            (WEB3EquityBookPriceRegistResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
   
    /**
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
     * �@@����(�뉿�P���v�Z�p) = AssetParams.����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ AssetParams.���t�s�\����<BR>
     * �@@�뉿(�뉿�P���v�Z�p) =  (AssetParams.����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ AssetParams.���t�s�\����)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@* �p�����[�^.�뉿�P��<BR>
     * �@@���͕뉿�P�� = �p�����[�^.�뉿�P��<BR>
     * �@@�뉿�P�����͓��� = ���ݎ���(*1)<BR>
     * �@@�X�V���t = ���ݎ���<BR>
     * <BR>
     * �T�j�v���p�e�B�Z�b�g�����ۗL���YParams��ԋp����B<BR>
     * <BR>
     * (*1)<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG)<BR>
     * �ɂĎ擾�������ݎ����B<BR>
     * @@param l_asset - (�ۗL���Y) �ۗL���Y�I�u�W�F�N�g<BR>
     * @@param l_dblBookValuePrice - (�뉿�P��) �뉿�P��<BR>
     * @@return AssetParams<BR>
     * @@roseuid 41BCF2C00330
     */
    protected AssetParams createAssetParams(WEB3EquityAsset l_asset, double l_dblBookValuePrice) 
    {
        final String STR_METHOD_NAME = "createAssetParams(WEB3EquityAsset, double)";
        log.entering(STR_METHOD_NAME);
        
        // AssetRow�I�u�W�F�N�g�̎擾
        AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();
        
        // AssetParams�C���X�^���X�̐���
        AssetParams l_assetParams = new AssetParams();
        GtlUtils.copyRow2Params(l_assetRow, l_assetParams);
        
        // �v���p�e�B�Z�b�g
        // ���ʁi�뉿�P���v�Z�p�j
        l_assetParams.quantity_for_book_value = l_assetParams.quantity + l_assetParams.quantity_cannot_sell;
        // �뉿�i�뉿�P���v�Z�p�j
        l_assetParams.book_value =
            (l_assetParams.quantity + l_assetParams.quantity_cannot_sell) * l_dblBookValuePrice;
        // ���͕뉿�P��
        l_assetParams.input_book_value = new Double(l_dblBookValuePrice);
        
        // ���ݎ������擾
        Timestamp l_currentTime = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        // �뉿�P�����͓���
        l_assetParams.input_timestamp = l_currentTime;
        // �X�V���t
        l_assetParams.last_updated_timestamp = l_currentTime;
        
        log.exiting(STR_METHOD_NAME);
        return l_assetParams;
    }
}
@
