head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductInfoUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������X�V�T�[�r�XImpl(WEB3AdminFeqProductInfoUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/27 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 ��O��(���u) ���r���[       
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateDetailsInputRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateDetailsInputResponse;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateInputRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqProductInfoUpdateService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������������X�V�T�[�r�XImpl)<BR>
 * �O�������������X�V�T�[�r�X�����N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqProductInfoUpdateServiceImpl implements WEB3AdminFeqProductInfoUpdateService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqProductInfoUpdateServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F300FA
     */
    public WEB3AdminFeqProductInfoUpdateServiceImpl() 
    {
     
    }
    
    /**
     * �O�������������X�V�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *    get���͉��<BR>
     *    get���ד��͉��()<BR>
     *    validate�X�V()<BR>
     *    submit�X�V()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 421AB5F6025B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqProductInformationUpdateInputRequest)
        {
            //get���͉��()
            l_response = 
                this.getInputScreen((WEB3AdminFeqProductInformationUpdateInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqProductInformationUpdateDetailsInputRequest)
        {
            //get���ד��͉��
            l_response = 
                this.getDetailInputScreen((WEB3AdminFeqProductInformationUpdateDetailsInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqProductInformationUpdateConfirmRequest)
        {
            //validate�X�V()
            l_response = 
                this.validateUpdate((WEB3AdminFeqProductInformationUpdateConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqProductInformationUpdateCompleteRequest)
        {
            //submit�X�V()
            l_response = 
                this.submitUpdate((WEB3AdminFeqProductInformationUpdateCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʕ\���f�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj�������X�V�jget���͉�ʁv �Q��<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�i�ǁj�������X�V�v(�i�ǁj�������X�V�jget���͉��)<BR>
     * �@@�@@:  1.5 get�O����������(long)<BR> 
     * �@@�@@�擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02142<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�i�ǁj�������X�V�v(�i�ǁj�������X�V�jget���͉��)<BR>
     * �@@�@@:  1.6�@@get�������( )<BR> 
     *   �@@�擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02088<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BF94440239
     */
    protected WEB3AdminFeqProductInformationUpdateInputResponse getInputScreen(
        WEB3AdminFeqProductInformationUpdateInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getInputScreen(WEB3AdminFeqProductInformationUpdateInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        //1.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_PRODUCT_MANAGE, true);//WEB3BaseException
        
        //1.3 createResponse( )
        WEB3AdminFeqProductInformationUpdateInputResponse l_response = 
            (WEB3AdminFeqProductInformationUpdateInputResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���ד��͉��)<BR>
     * ���ד��͉�ʕ\���f�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj�������X�V�jget���ד��͉�ʁv �Q��<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�i�ǁj�������X�V�v(�i�ǁj�������X�V�jget���ד��͉��)<BR>
     * �@@�@@:  1.5 get�O����������(Institution, String)<BR> 
     * �@@�@@�擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02142<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * �@@ �i�ǁj�������X�V�v(�i�ǁj�������X�V�jget���ד��͉��)<BR>
     * �@@�@@:  1.6�@@get�������( )<BR> 
     *   �@@�擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02088<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateDetailsInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 421AB5AB021C
     */
    protected WEB3AdminFeqProductInformationUpdateDetailsInputResponse getDetailInputScreen(
        WEB3AdminFeqProductInformationUpdateDetailsInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getDetailInputScreen(WEB3AdminFeqProductInformationUpdateDetailsInputRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_PRODUCT_MANAGE, true);//WEB3BaseException
        
        //1.4 get�،����( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.5 get�O����������(Institution, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinApp�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModule�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        if (l_productManager == null)
        {
            String l_strMessage = "�O�������v���_�N�g�}�l�[�W�������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProduct l_feqProduct = null;
        try
        {
            l_feqProduct = (WEB3FeqProduct) l_productManager.getFeqProduct(l_institution, l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�O���������������݂��Ȃ��B�����R�[�h�u" 
                + l_request.productCode + "�v";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage,
                l_ex);
        }
        if (l_feqProduct == null)
        {
            String l_strMessage = "�O���������������݂��Ȃ��B�����R�[�h�u" 
                + l_request.productCode + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //1.6 get�������( )
        WEB3FeqTradedProduct l_feqTradedProduct = l_feqProduct.getFeqTradedProduct();//WEB3BaseException
        if (l_feqTradedProduct == null)
        {
            String l_strMessage = "�O������������������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02088,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
            
        //1.7 createResponse()
        WEB3AdminFeqProductInformationUpdateDetailsInputResponse l_response = 
            (WEB3AdminFeqProductInformationUpdateDetailsInputResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���X�|���X��null�ł��B");
        }    
        
        //1.8 �v���p�e�B�Z�b�g
        FeqProductRow l_feqProductRow = (FeqProductRow)l_feqProduct.getDataSourceObject();
        FeqTradedProductRow l_feqTradedProductRow = (FeqTradedProductRow)l_feqTradedProduct.getDataSourceObject();
        //�����R�[�h�F �O����������.�����R�[�h
        l_response.productCode = l_feqProductRow.getProductCode();
        //�������i�J�i�j�F �O����������.�������i�J�i�j
        l_response.productNameKana = l_feqProductRow.getStandardNameKana();
        //�������i�����j�F �O����������.�������i�����j
        l_response.productNameKanji = l_feqProductRow.getStandardNameKanji();
        //�s��R�[�h�F �O����������.�s��R�[�h
        l_response.marketCode = l_feqProductRow.getMarketCode();
        //���t��~�敪�F �O�������������.���t��~
        if (!l_feqTradedProductRow.getBuyStopIsNull())
        {
            l_response.buyStopDiv = l_feqTradedProductRow.getBuyStop() + "";
        }
        //���t��~�敪�F �O�������������.���t��~
        if (!l_feqTradedProductRow.getSellStopIsNull())
        {
            l_response.sellStopDiv = l_feqTradedProductRow.getSellStop() + "";
        }
        //���n�����R�[�h�F �O����������.���n�����R�[�h
        l_response.localProductCode = l_feqProductRow.getOffshoreProductCode();
        //���t�P�ʁF �O�������������.���t�P��
        l_response.buyUnit = 
            WEB3StringTypeUtility.formatNumber(l_feqTradedProduct.getBuyOrderLotSize());
        //�Œᔃ�t�P�ʁF �O�������������.�Œᔃ�t��������
        l_response.minBuyUnit = 
            WEB3StringTypeUtility.formatNumber(l_feqTradedProductRow.getBuyMinQty());
        //���t�P�ʁF �O�������������.���t�P��
        l_response.sellUnit = 
            WEB3StringTypeUtility.formatNumber(l_feqTradedProduct.getSellOrderLotSize());
        //�Œᔄ�t�P�ʁF �O�������������.�Œᔄ�t��������
        l_response.minSellUnit = 
            WEB3StringTypeUtility.formatNumber(l_feqTradedProductRow.getSellMinQty());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�X�V)<BR>
     * �X�V���e�̊m�F���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj�������X�V�jvalidate�X�V�v �Q��<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�i�ǁj�������X�V�v(�i�ǁj�������X�V�jvalidate�X�V)<BR>
     * �@@�@@:  1.5 get�O����������(long)<BR> 
     * �@@�@@�擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02142<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * �@@ �i�ǁj�������X�V�v(�i�ǁj�������X�V�jvalidate�X�V)<BR>
     * �@@�@@:  1.6�@@get�������( )<BR> 
     *   �@@�擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02088<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 421AB5AB022C
     */
    protected WEB3AdminFeqProductInformationUpdateConfirmResponse validateUpdate(
        WEB3AdminFeqProductInformationUpdateConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateUpdate(WEB3AdminFeqProductInformationUpdateConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_PRODUCT_MANAGE, true);//WEB3BaseException
        
        //1.4 get�،����( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.5 get�O����������(�،���� : Institution, �����R�[�h : String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinApp�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModule�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        if (l_productManager == null)
        {
            String l_strMessage = "�O�������v���_�N�g�}�l�[�W�������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProduct l_feqProduct = null;
        try
        {
            l_feqProduct = (WEB3FeqProduct) l_productManager.getFeqProduct(l_institution, l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�O���������������݂��Ȃ��B�����R�[�h�u" 
                + l_request.productCode + "�v";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage,
                l_ex);
        }
        if (l_feqProduct == null)
        {
            String l_strMessage = "�O���������������݂��Ȃ��B�����R�[�h�u" 
                + l_request.productCode + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //1.6 get�������( )
        WEB3FeqTradedProduct l_feqTradedProduct = l_feqProduct.getFeqTradedProduct();//WEB3BaseException
        if (l_feqTradedProduct == null)
        {
            String l_strMessage = "�O������������������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02088,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //1.7 createResponse()
        WEB3AdminFeqProductInformationUpdateConfirmResponse l_response = 
            (WEB3AdminFeqProductInformationUpdateConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�X�V)<BR>
     * DB�̍X�V���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj�������X�V�jsubmit�X�V�v �Q��<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�i�ǁj�������X�V�v(�i�ǁj�������X�V�jsubmit�X�V)<BR>
     * �@@�@@:  1.5 get�O����������(long)<BR> 
     * �@@�@@�擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02142<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�i�ǁj�������X�V�v(�i�ǁj�������X�V�jsubmit�X�V)<BR>
     * �@@�@@:  1.6�@@get�������( )<BR> 
     *   �@@�擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02088<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 421AB5AB024B
     */
    protected WEB3AdminFeqProductInformationUpdateCompleteResponse submitUpdate(
        WEB3AdminFeqProductInformationUpdateCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateUpdate(WEB3AdminFeqProductInformationUpdateConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_PRODUCT_MANAGE, true);//WEB3BaseException
        
        //1.4 get�،����( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.5 get�O����������(�،���� : Institution, �����R�[�h : String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinApp�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModule�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        if (l_productManager == null)
        {
            String l_strMessage = "�O�������v���_�N�g�}�l�[�W�������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProduct l_feqProduct = null;
        try
        {
            l_feqProduct = (WEB3FeqProduct) l_productManager.getFeqProduct(l_institution, l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�O���������������݂��Ȃ��B�����R�[�h�u" 
                + l_request.productCode + "�v";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage,
                l_ex);
        }
        if (l_feqProduct == null)
        {
            String l_strMessage = "�O���������������݂��Ȃ��B�����R�[�h�u" 
                + l_request.productCode + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //1.6 get�������( )
        WEB3FeqTradedProduct l_feqTradedProduct = l_feqProduct.getFeqTradedProduct();//WEB3BaseException
        if (l_feqTradedProduct == null)
        {
            String l_strMessage = "�O������������������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02088,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.7 validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException
        
        //1.8 update�O������(String, �O����������Params, String, String)
        FeqProductParams l_feqProductParams = 
            new FeqProductParams((FeqProductRow)l_feqProduct.getDataSourceObject());
        this.updateFeqProduct(
            l_admin.getAdministratorCode(),
            l_feqProductParams,
            l_request.productNameKanji,
            l_request.localProductCode);//WEB3BaseException
            
        //1.9 update�O���������(String, �O�������������Params, String, String, String, String, String, String)
        FeqTradedProductParams l_feqTradedProductParams = 
            new FeqTradedProductParams((FeqTradedProductRow)l_feqTradedProduct.getDataSourceObject());
        this.updateFeqTradedProduct(
            l_admin.getAdministratorCode(),
            l_feqTradedProductParams,
            l_request.buyStopDiv,
            l_request.sellStopDiv,
            l_request.buyUnit,
            l_request.minBuyUnit,
            l_request.sellUnit,
            l_request.minSellUnit);//WEB3BaseException
        
        //1.10 createResponse()
        WEB3AdminFeqProductInformationUpdateCompleteResponse l_response = 
            (WEB3AdminFeqProductInformationUpdateCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (update�O������)<BR>
     * �O��������DB�X�V���s���B<BR>
     * <BR>
     * �P�j�s�I�u�W�F�N�g�̃N���[���𐶐�����B<BR>
     * <BR>
     * �Q�j�N���[���Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     *    �������i�����j�F ����.�������i�����j<BR>
     *    ���n�����R�[�h�F ����.���n�����R�[�h<BR>
     *    �X�V�҃R�[�h�F ����.�Ǘ��҃R�[�h<BR>
     *    �X�V���t�F �V�X�e���^�C���X�^���v<BR>
     * <BR>
     * �R�jDB���X�V����B<BR>
     * <BR>
     *    WEB3DataAccessUtility.updateRow()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    l_orw�F �s�I�u�W�F�N�g�̃N���[��<BR>
     * @@param l_strAdminCode - (�Ǘ��҃R�[�h)
     * �Ǘ��҃R�[�h
     * 
     * @@param l_productParams - (�����s)<BR>
     * �O�����������s�I�u�W�F�N�g<BR>
     * @@param l_strStandardNameKanji - (�������i�����j)<BR>
     * �������i�����j<BR>
     * 
     * @@param l_strOffshoreProductCode - (���n�����R�[�h)<BR>
     * ���n�����R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B2BDEF034B
     */
    protected void updateFeqProduct(
        String l_strAdminCode, 
        FeqProductParams l_productParams, 
        String l_strStandardNameKanji, 
        String l_strOffshoreProductCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateUpdate(WEB3AdminFeqProductInformationUpdateConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_productParams == null)
        {
            String l_strMessage = "�p�����[�^�l�s���B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�P�j�s�I�u�W�F�N�g�̃N���[���𐶐�����B
        FeqProductParams l_params = new FeqProductParams(l_productParams);

        //�Q�j�N���[���Ƀv���p�e�B���Z�b�g����B
        //   �������i�����j�F ����.�������i�����j
        //   ���n�����R�[�h�F ����.���n�����R�[�h
        //   �X�V�҃R�[�h�F ����.�Ǘ��҃R�[�h
        //   �X�V���t�F �V�X�e���^�C���X�^���v
        l_params.setStandardNameKanji(l_strStandardNameKanji);
        l_params.setOffshoreProductCode(l_strOffshoreProductCode);
        l_params.setLastUpdater(l_strAdminCode);
        Timestamp l_currentTime = GtlUtils.getSystemTimestamp();
        l_params.setLastUpdatedTimestamp(l_currentTime);

        //�R�jDB���X�V����B
        //   WEB3DataAccessUtility.updateRow()���R�[������B
        //   [����]
        //   l_orw�F �s�I�u�W�F�N�g�̃N���[��
        try
        {
            WEB3DataAccessUtility.updateRow(l_params);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (update�O���������)<BR>
     * �O�����������DB�X�V���s���B<BR>
     * <BR>
     * �P�j�s�I�u�W�F�N�g�̃N���[���𐶐�����B<BR>
     * <BR>
     * �Q�j�N���[���Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     *    ���t��~�F ����.���t��~�敪<BR>
     *    ���t��~�F ����.���t��~�敪<BR>
     *    ���t�P�ʁF ����.���t�P��<BR>
     *    �Œᔃ�t�������ʁF ����.�Œᔃ�t�P��<BR>
     *    ���t�P�ʁF ����.���t�P��<BR>
     *    �Œᔄ�t�������ʁF ����.�Œᔄ�t�P��<BR>
     *    �X�V�҃R�[�h�F ����.�Ǘ��҃R�[�h<BR>
     *    �X�V���t�F �V�X�e���^�C���X�^���v<BR>
     * <BR>
     * �R�jDB���X�V����B<BR>
     * <BR>
     *    WEB3DataAccessUtility.updateRow()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    l_orw�F �s�I�u�W�F�N�g�̃N���[��<BR>
     * @@param l_strAdminCode - (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h<BR>
     * 
     * @@param l_tradeProductParams - (��������s)<BR>
     * �O��������������s�I�u�W�F�N�g<BR>
     * @@param l_strBuyStopDiv - (���t��~�敪)<BR>
     * ���t��~�敪<BR>
     * 
     * @@param l_strSellStopDiv - (���t��~�敪)<BR>
     * ���t��~�敪<BR>
     * 
     * @@param l_strBuyLotSize - (���t�P��)<BR>
     * ���t�P��<BR>
     * 
     * @@param l_strMinBuyLotSize - (�Œᔃ�t�P��)<BR>
     * �Œᔃ�t�P��<BR>
     * 
     * @@param l_strSellLotSize - (���t�P��)<BR>
     * ���t�P��<BR>
     * 
     * @@param l_strMinSellLotSize - (�Œᔄ�t�P��)<BR>
     * �Œᔄ�t�P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B2BF2503B8
     */
    protected void updateFeqTradedProduct(
        String l_strAdminCode, 
        FeqTradedProductParams l_tradeProductParams, 
        String l_strBuyStopDiv, 
        String l_strSellStopDiv, 
        String l_strBuyLotSize, 
        String l_strMinBuyLotSize, 
        String l_strSellLotSize, 
        String l_strMinSellLotSize) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateUpdate(WEB3AdminFeqProductInformationUpdateConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        //�P�j�s�I�u�W�F�N�g�̃N���[���𐶐�����B
        FeqTradedProductParams l_params = new FeqTradedProductParams(l_tradeProductParams);
        
        //�Q�j�N���[���Ƀv���p�e�B���Z�b�g����B
        //   ���t��~�F ����.���t��~�敪
        //   ���t��~�F ����.���t��~�敪
        //   ���t�P�ʁF ����.���t�P��
        //   �Œᔃ�t�������ʁF ����.�Œᔃ�t�P��
        //   ���t�P�ʁF ����.���t�P��
        //   �Œᔄ�t�������ʁF ����.�Œᔄ�t�P��
        //   �X�V�҃R�[�h�F ����.�Ǘ��҃R�[�h
        //   �X�V���t�F �V�X�e���^�C���X�^���v
        if (l_strBuyStopDiv != null)
        {
            l_params.setBuyStop(Integer.parseInt(l_strBuyStopDiv));
        }
        if (l_strSellStopDiv != null)
        {
            l_params.setSellStop(Integer.parseInt(l_strSellStopDiv));
        }
        if (l_strBuyLotSize != null)
        {
            l_params.setBuyLotSize(Double.parseDouble(l_strBuyLotSize));
        }
        if (l_strMinBuyLotSize != null)
        {
            l_params.setBuyMinQty(Double.parseDouble(l_strMinBuyLotSize));
        }
        if (l_strSellLotSize != null)
        {
            l_params.setSellLotSize(Double.parseDouble(l_strSellLotSize));
        }
        if (l_strMinSellLotSize != null)
        {
            l_params.setSellMinQty(Double.parseDouble(l_strMinSellLotSize));
        }

        l_params.setLastUpdater(l_strAdminCode);
        Timestamp l_currentTime = GtlUtils.getSystemTimestamp();
        l_params.setLastUpdatedTimestamp(l_currentTime);
        
        //�R�jDB���X�V����B
        //   WEB3DataAccessUtility.updateRow()���R�[������B
        //   [����]
        //   l_orw�F �s�I�u�W�F�N�g�̃N���[��
        try
        {
            WEB3DataAccessUtility.updateRow(l_params);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
