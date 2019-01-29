head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoOfferStopResumeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO��W��~�ĊJ�T�[�r�X�����N���X(WEB3AdminIpoOfferStopResumeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/26 ���C�g (���u) �V�K�쐬
Revesion History : 2004/12/29 ���(SRA) �c�Ή�>>>043
Revesion History : 2005/01/07 ���(SRA) �c�Ή�>>>061
*/

package webbroker3.ipo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeConfirmResponse;
import webbroker3.ipo.message.WEB3IPOProductInfo;
import webbroker3.ipo.service.delegate.WEB3AdminIpoOfferStopResumeService;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.util.WEB3LogUtility;

/**
 * �Ǘ���IPO��W��~�ĊJ�T�[�r�X�����N���X
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminIpoOfferStopResumeServiceImpl implements WEB3AdminIpoOfferStopResumeService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoOfferStopResumeServiceImpl.class);
    
    /**
     * @@roseuid 4112EEE30290
     */
    public WEB3AdminIpoOfferStopResumeServiceImpl() 
    {
     
    }
    
    /**
     * (validate��W��~�ĊJ)<BR>
     * �Ǘ���IPO��W��~�ĊJ�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE��W��~�^�ĊJ�jvalidate��W��~�ĊJ�v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>                        
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE��W��~�^�ĊJ�jvalidate��W��~�ĊJ�v): <BR>                      
     *      1.6(*1) �iis�폜����() == true�j�̏ꍇ�A��O���X���[����<BR>                      
     *          class: WEB3BusinessLayerException<BR>                       
     *          tag:   BUSINESS_ERROR_00588<BR>                     
     *      1.6(*1) �iis��W���~() == true�j�̏ꍇ�A��O���X���[����<BR>                      
     *          class: WEB3BusinessLayerException<BR>                       
     *          tag:   BUSINESS_ERROR_00589<BR>                     
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE��W��~�^�ĊJ�jvalidate��W��~�ĊJ�v): <BR>
     *         1.10(*2) �iis�X�P�W���[������() == true && is�w���\���I���i���Аݒ�j() == true 
     *                    && is�戵��() == true�j�̏ꍇ�A��O���X���[����<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00590<BR>
     * ==========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE��W��~�^�ĊJ�jvalidate��W��~�ĊJ�v): <BR>
     *         1.12(*3) �iis�X�P�W���[������() == true && is���J��() == true�j�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00560<BR>
     * ==========================================================<BR>
     * @@param l_request - �Ǘ���IPO��W��~�ĊJ�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.ipo.message.WEB3AdminIpoOfferStopResumeConfirmResponse
     * @@roseuid 40D0166F01B3
     */
    protected WEB3AdminIPOOfferStopResumeConfirmResponse validateOfferStopResume(
        WEB3AdminIPOOfferStopResumeConfirmRequest l_request) throws  WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateOfferStopResume(WEB3AdminIpoOfferStopResumeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate����()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.3.IPO����(long) l_lng = id
        long l_lngId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            
        WEB3IpoProductImpl l_ipoProduct = null;    
        try
        {    
            
            l_ipoProduct = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngId);
            
        }
        catch(NotFoundException l_ex)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,STR_METHOD_NAME);
            
        }
           
        //1.4.is�폜����()
        boolean l_blnProduct = l_ipoProduct.isDeletedProduct();
        
        //1.5.is���~()
        boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
        
        //1.6.is�폜����() == true�j�̏ꍇ�A��O���X���[����
        if(l_blnProduct)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        //(is��W���~() == true�j�̏ꍇ�A��O���X���[����
        if(l_blnDiscontinuation)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        //1.7.is�X�P�W���[������()
        boolean l_blnScheduleDecision = l_ipoProduct.isScheduleDecision();
        
        //1.8.is�w���\���I���i���Аݒ�j()
        boolean l_blnSetting = l_ipoProduct.isOfferEnd();
        
        //1.9.is�戵��()
        boolean l_blnDealtInProcess = l_ipoProduct.isDealtInProcess();
        
        //1.10.�iis�X�P�W���[������() == true && is�w���\���I���i���Аݒ�j() == true && is�戵��() == true�j�̏ꍇ�A��O���X���[����
        if(l_blnScheduleDecision && l_blnSetting && l_blnDealtInProcess)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00590,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        //1.11.is���J��()
        boolean l_blnPublicEnd = l_ipoProduct.isPublicEnd();
        
        //1.12.(*3)�iis�X�P�W���[������() == true && is���J��() == true�j�̏ꍇ�A��O���X���[����
        if(l_blnScheduleDecision && l_blnPublicEnd)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00560,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }        
        
        //1.13.createIPO�������(long)
        WEB3IpoProductInfoService l_ipoProductInfoServiceImpl = 
            (WEB3IpoProductInfoService)Services.getService(WEB3IpoProductInfoService.class);        
        WEB3IPOProductInfo l_info = l_ipoProductInfoServiceImpl.createIpoProductInfo(l_lngId);
        
        //1.14.�Ǘ���IPO��W��~�ĊJ�m�F���X�|���X(WEB3GenRequest)
        WEB3GenResponse l_genResponse = l_request.createResponse();        
        WEB3AdminIPOOfferStopResumeConfirmResponse l_response = (WEB3AdminIPOOfferStopResumeConfirmResponse)l_genResponse;
        
        l_response.ipoProductInfo = l_info;
        l_response.offerStateDiv = l_blnDealtInProcess;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
    
    /**
     * (submit��W��~�ĊJ)<BR>
     * �Ǘ���IPO��W��~�ĊJ�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE��W��~�^�ĊJ�jsubmit��W��~�ĊJ�v�Q�ƁB<BR>
     * @@param l_request - �Ǘ���IPO��W��~�ĊJ�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIpoOfferStopResumeCompleteResponse
     * @@roseuid 40D0166F01D2
     */
    protected WEB3AdminIPOOfferStopResumeCompleteResponse submitOfferStopResume(
        WEB3AdminIPOOfferStopResumeCompleteRequest l_request) throws  WEB3BaseException
    {

        final String STR_METHOD_NAME = " submitOfferStopResume(WEB3AdminIpoOfferStopResumeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        //1.1.getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate����()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.3.validate����p�X���[�h(String)
        String l_strPassword = l_request.password;
        l_administartor.validateTradingPassword(l_strPassword);
        
        //1.4.IPO����(long) l_lng = id
        long l_lngId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            
        WEB3IpoProductImpl l_ipoProduct = null;    
        try
        {    
            
            l_ipoProduct = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngId);
            
        }
        catch(NotFoundException l_ex)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,STR_METHOD_NAME);
            
        }
        
        //1.5.is�폜����()
        boolean l_blnProduct = l_ipoProduct.isDeletedProduct();
        
        //1.6.is���~()
        boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
        
        //1.7.is�폜����() == true�j�̏ꍇ�A��O���X���[����
        if(l_blnProduct)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        //(is��W���~() == true�j�̏ꍇ�A��O���X���[����
        if(l_blnDiscontinuation)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        //1.8.is�X�P�W���[������()
        boolean l_blnScheduleDecision = l_ipoProduct.isScheduleDecision();
        
        //1.9.is�w���\���I���i���Аݒ�j()
        boolean l_blnSetting = l_ipoProduct.isOfferEnd();
        
        //1.10.is�戵��()
        boolean l_blnDealtInProcess = l_ipoProduct.isDealtInProcess();
        
        //1.11.�iis�X�P�W���[������() == true && is�w���\���I���i���Аݒ�j() == true && is�戵��() == true�j�̏ꍇ�A��O���X���[����
        if(l_blnScheduleDecision && l_blnSetting && l_blnDealtInProcess)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00590,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        //1.12.is���J��()
        boolean l_blnPublicEnd = l_ipoProduct.isPublicEnd();
        
        //1.13.(*3)�iis�X�P�W���[������() == true && is���J��() == true�j�̏ꍇ�A��O���X���[����
        if(l_blnScheduleDecision && l_blnPublicEnd)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00560,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }        
        
        //1.14.�X�V�p�̍s�I�u�W�F�N�g�𐶐�����B
        l_ipoProduct.createForUpdateParams();   
        
        //1.15.set��W��~�^�ĊJ( )
        l_ipoProduct.setRecruitStopResumption();
        //1.16.IPO�v���_�N�g�}�l�[�W��
        l_ipoProductManagerImpl.saveProduct(l_ipoProduct);
        
        //1.17.createResponse()
        WEB3GenResponse l_genResponse = l_request.createResponse();        
        WEB3AdminIPOOfferStopResumeCompleteResponse l_response = (WEB3AdminIPOOfferStopResumeCompleteResponse)l_genResponse;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
    
    /**
     * �Ǘ���IPO��W��~�ĊJ���������{����B<BR>
     * <BR>
     * �P�j�@@�Ɩ������ݒ�<BR>
     * �@@������ԊǗ�.setBusinessTimestamp()���R�[������B<BR>
     * <BR>
     * �Q�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO��W��~�ĊJ�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate��W��~�ĊJ()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO��W��~�ĊJ�������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit��W��~�ĊJ()���R�[������B<BR>
     * <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40D0166F02BC
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

        WEB3GenResponse l_response = null;
        
        if(l_request instanceof WEB3AdminIPOOfferStopResumeConfirmRequest)
        {

            WEB3AdminIPOOfferStopResumeConfirmResponse l_ipoOfferStopResumeConfirmResponse = 
                this.validateOfferStopResume((WEB3AdminIPOOfferStopResumeConfirmRequest)l_request);
                
            l_response = l_ipoOfferStopResumeConfirmResponse;
            
        }
        else if(l_request instanceof WEB3AdminIPOOfferStopResumeCompleteRequest)
        {
            
            WEB3AdminIPOOfferStopResumeCompleteResponse l_ipoOfferStopResumeCompleteResponse = 
                this.submitOfferStopResume((WEB3AdminIPOOfferStopResumeCompleteRequest)l_request);
                
            l_response = l_ipoOfferStopResumeCompleteResponse;
            
        }
        else
        {
            // Timestamp���Z�b�g
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        // Timestamp���Z�b�g
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
}
@
