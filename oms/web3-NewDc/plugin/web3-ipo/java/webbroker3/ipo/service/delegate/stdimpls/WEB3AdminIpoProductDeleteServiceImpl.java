head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoProductDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�����폜�T�[�r�XImpl(WEB3AdminIpoProductDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/20 ���o�� �V�K�쐬
Revesion History : 2004/12/29 ���(SRA) �c�Ή�>>>043
Revesion History : 2005/01/07 ���(SRA) �c�Ή�>>>062
*/

package webbroker3.ipo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteConfirmResponse;
import webbroker3.ipo.message.WEB3IPOProductInfo;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductDeleteService;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���IPO�����폜�T�[�r�XImpl)<BR>
 * �Ǘ���IPO�����폜�T�[�r�X�����N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminIpoProductDeleteServiceImpl implements WEB3AdminIpoProductDeleteService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoProductDeleteServiceImpl.class);
    
    /**
     * @@roseuid 4112EEE400C5
     */
    public WEB3AdminIpoProductDeleteServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ���IPO�����폜���������{����B<BR>
     * <BR>
     * �P�j�@@�Ɩ������ݒ�<BR>
     * �@@������ԊǗ�.setBusinessTimestamp()���R�[������B<BR>
     * <BR>
     * �Q�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO�����폜�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate�����폜()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO�����폜�������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�����폜()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40C7DF4201C4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        // Timestamp���Z�b�g
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        // Timestamp�ݒ�
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            l_processTime);
        
        if (l_request instanceof WEB3AdminIPOProductDeleteConfirmRequest)
        {
            log.debug("WEB3AdminIpoProductDeleteConfirmRequest");
            WEB3AdminIPOProductDeleteConfirmResponse l_ipoProductDeleteConfirmResponse = validateProductDelete(
                (WEB3AdminIPOProductDeleteConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductDeleteConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminIPOProductDeleteCompleteRequest)
        {
            log.debug("WEB3AdminIpoProductDeleteCompleteRequest");
            WEB3AdminIPOProductDeleteCompleteResponse l_ipoProductDeleteCompleteResponse = submitProductDelete(
                (WEB3AdminIPOProductDeleteCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductDeleteCompleteResponse;
        }
        else
        {
            // Timestamp���Z�b�g
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (validate�����폜)<BR>
     * �Ǘ���IPO�����폜�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�����폜�jvalidate�����폜�v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE�����폜�jvalidate�����폜�v): 6�iis�폜����() == true�j�̏ꍇ<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00588<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO�����폜�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductDeleteConfirmResponse
     * @@roseuid 40C7DF520280
     */
    protected WEB3AdminIPOProductDeleteConfirmResponse validateProductDelete(WEB3AdminIPOProductDeleteConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateProductDelete(WEB3AdminIPOProductDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate����( )
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.3.IPO����(long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        try
        {
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(Long.parseLong(l_request.id));
            
            //1.4.is�폜����( )
            boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
            
            //1.5.(*1) �iis�폜����() == true�j�̏ꍇ
            if (l_blnDeletionProduct)
            {
                log.debug("is�폜����() == true");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    getClass().getName() + STR_METHOD_NAME);
            }
            
            log.debug("is�폜����() != true");
            
            //1.6.createIPO�������(long)
            WEB3IpoProductInfoService l_service =
                (WEB3IpoProductInfoService)Services.getService(WEB3IpoProductInfoService.class);
            WEB3IPOProductInfo l_ipoProductInfo = l_service.createIpoProductInfo(Long.parseLong(l_request.id));    
                
            //1.7.�Ǘ���IPO�����폜�m�F���X�|���X
            WEB3AdminIPOProductDeleteConfirmResponse l_ipoProductDeleteConfirmResponse = 
                (WEB3AdminIPOProductDeleteConfirmResponse)l_request.createResponse();
                    
            //1.8.�v���p�e�B�Z�b�g
            l_ipoProductDeleteConfirmResponse.ipoProductInfo = l_ipoProductInfo;
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductDeleteConfirmResponse;   
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }
    
    /**
     * (submit�����폜)<BR>
     * �Ǘ���IPO�����폜�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�����폜�jsubmit�����폜�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO�����폜�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductDeleteCompleteResponse
     * @@roseuid 40C7DF52035A
     */
    protected WEB3AdminIPOProductDeleteCompleteResponse submitProductDelete(WEB3AdminIPOProductDeleteCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitProductDelete(WEB3AdminIPOProductDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate����( )
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.3.validate����p�X���[�h
        l_administrator.validateTradingPassword(l_request.password);
        
        //1.4.IPO����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();        
        try
        {
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(Long.parseLong(l_request.id));
            
            //1.5.is�폜����( )
            boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
            
            //1.6.(*) �iis�폜����() == true�j�̏ꍇ
            if (l_blnDeletionProduct)
            {
                log.debug("is�폜����() == true");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    getClass().getName() + STR_METHOD_NAME);
            }           
            log.debug("is�폜����() != true");            
            
            //1.7.�X�V�p�̍s�I�u�W�F�N�g�𐶐�����B
            l_ipoProduct.createForUpdateParams();
            
            //1.8..�폜( )
            l_ipoProduct.delete();
            
            //1.9..save����(IPO����)
            l_ipoProductManagerImpl.saveProduct(l_ipoProduct);
            log.debug("l_ipoProduct.getDataSourceObject() = " + l_ipoProduct.getDataSourceObject());
            
            //1.10.�Ǘ���IPO�����폜�������X�|���X
            WEB3AdminIPOProductDeleteCompleteResponse l_ipoProductDeleteCompleteResponse = 
                (WEB3AdminIPOProductDeleteCompleteResponse)l_request.createResponse();
                
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductDeleteCompleteResponse;    
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }          
    }
}
@
