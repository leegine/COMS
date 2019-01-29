head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoProductChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �Ǘ���IPO���������T�[�r�XImpl(WEB3AdminIpoProductChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2004/08/16 �d�� (���u) �V�K�쐬
                   2004/09/02 ���o�� (���u) �C��
Revesion History : 2004/12/27 ���(SRA) �c�Ή�>>>041
Revesion History : 2004/12/29 ���(SRA) �c�Ή�>>>042,043
Revesion History : 2005/01/07 ���(SRA) �c�Ή�>>>060
Revesion History : 2005/01/17 ���(SRA) �C��
Revesion History : 2010/09/21 ��V�� (���u) �����̖�� No.018
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3PublicMarketDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoBookbuildingOrderActionImpl;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoBookbuildingOrderActionParams;
import webbroker3.ipo.data.IpoBookbuildingOrderActionRow;
import webbroker3.ipo.data.IpoOrderDao;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeInputResponse;
import webbroker3.ipo.message.WEB3IPOProductInfo;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductChangeService;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

/**
 * (�Ǘ���IPO���������T�[�r�XImpl)<BR>
 * 
 * @@author �d��
 * @@version 1.0
 */

public class WEB3AdminIpoProductChangeServiceImpl implements WEB3AdminIpoProductChangeService 
{
    
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminIpoProductChangeServiceImpl.class);
     
    /**
     * (get���͉��)<BR>
     * �Ǘ���IPO�����������͉�ʕ\���f�[�^�쐬�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���������jget���͉�ʁv�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>                                    
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE���������jget���͉�ʁv): <BR>
     *      7�iis�폜����() == true�j�̏ꍇ�A��O���X���[����<BR>                             
     * <BR>                                    
     *   class: WEB3BusinessLayerException<BR>                                   
     *   tag:   BUSINESS_ERROR_00588<BR>                                 
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE���������jget���͉�ʁv):<BR> 
     *      7�iis��W���~() == true�j�̏ꍇ�A��O���X���[����<BR>                                 
     * <BR>                                    
     *   class: WEB3BusinessLayerException<BR>                                   
     *   tag:   BUSINESS_ERROR_00589<BR>                                 
     * ==========================================================<BR>
     * <BR>   
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO�����������̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductChangeInputResponse
     * @@roseuid 40C94577004A
     */
    protected WEB3AdminIPOProductChangeInputResponse getInputScreen(WEB3AdminIPOProductChangeInputRequest l_request) throws WEB3BaseException 
    {
       
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminIPOProductChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
                
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����( )       
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //IPO����(long)
        long l_lngId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl =
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();

        try
        {
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngId);
            
            //is�폜����( )
            boolean l_blnProduct = l_ipoProduct.isDeletedProduct();
            
            //is���~( )
            boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
            
            //(*1) �iis�폜����() == true Or is���~() == true�j�̏ꍇ�A��O���X���[����
            // 2004/11/26 U00478 is�폜������is���~��if�߂̕]�������t�@@���@@SRA START
            if(l_blnProduct)
            {  
                log.debug("is�폜����() == true");
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_00588, 
                    this.getClass().getName() + STR_METHOD_NAME);            
            }
            if(l_blnDiscontinuation)
            {  
                log.debug("is��W���~() == true");
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_00589, 
                    this.getClass().getName() + STR_METHOD_NAME);                              
            }
//						if(l_blnDiscontinuation)
//						{  
//							log.debug("is�폜����() == true");
//							throw new WEB3BusinessLayerException
//								(WEB3ErrorCatalog.BUSINESS_ERROR_00588, 
//								this.getClass().getName() + STR_METHOD_NAME);            
//						}
//						if(l_blnProduct)
//						{  
//							log.debug("is���~() == true");
//							throw new WEB3BusinessLayerException
//								(WEB3ErrorCatalog.BUSINESS_ERROR_00589, 
//								this.getClass().getName() + STR_METHOD_NAME);            
//						}            
			// 2004/11/26 U00478 is�폜������is���~��if�߂̕]�������t�@@���@@SRA END
			
			            
            //getSystemTimestamp( )           
            Timestamp l_tsSystemTimestamp = l_finApp.getTradingSystem().getSystemTimestamp();
       
            //createIPO�������(long)
            WEB3IpoProductInfoService l_ipoProductInfoService = (WEB3IpoProductInfoService)Services.getService
                (WEB3IpoProductInfoService.class);
            WEB3IPOProductInfo l_ipoProductInfo = l_ipoProductInfoService.createIpoProductInfo(l_lngId);
            
            //�Ǘ���IPO�����������̓��X�|���X(WEB3GenRequest)
            WEB3AdminIPOProductChangeInputResponse l_ipoProductChangeInputResponse = (WEB3AdminIPOProductChangeInputResponse)l_request.createResponse();
            
            // (*2) �v���p�e�B�Z�b�g
            l_ipoProductChangeInputResponse.publicOfferingMarketList = new String[17];
            
            l_ipoProductChangeInputResponse.publicOfferingMarketList[0] = WEB3PublicMarketDef.TOKYO_STOCK_EXCHANGE;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[1] = WEB3PublicMarketDef.TSE_NO_ONE_DEPT;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[2] = WEB3PublicMarketDef.TSE_NO_TWO_DEPT;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[3] = WEB3PublicMarketDef.MOTHERS;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[4] = WEB3PublicMarketDef.OSAKA_SECURITIES_EXCHANGE;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[5] = WEB3PublicMarketDef.OSE_NO_ONE_DEPT;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[6] = WEB3PublicMarketDef.OSE_NO_TWO_DEPT;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[7] = WEB3PublicMarketDef.NAGOYA_STOCK_EXCHANGE;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[8] = WEB3PublicMarketDef.NSE_NO_ONE_DEPT;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[9] = WEB3PublicMarketDef.NSE_NO_TWO_DEPT;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[10] = WEB3PublicMarketDef.CENTREX;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[11] = WEB3PublicMarketDef.FUKUOKA_STOCK_EXCHANGE;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[12] = WEB3PublicMarketDef.Q_BOARD;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[13] = WEB3PublicMarketDef.SAPPORO_STOCK_EXCHANGE;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[14] = WEB3PublicMarketDef.AMBITIOUS;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[15] = WEB3PublicMarketDef.JASDAQ_STANDARD;            //JASDAQ�i�X�^���_�[�h�j
            l_ipoProductChangeInputResponse.publicOfferingMarketList[16] = WEB3PublicMarketDef.JASDAQ_CLOSE;               //JASDAQ�i�O���[�X�j
            l_ipoProductChangeInputResponse.currentDate = l_tsSystemTimestamp;
            l_ipoProductChangeInputResponse.ipoProductInfo = l_ipoProductInfo;
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductChangeInputResponse;
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            // 2004/11/19 ��Q�Ǘ��[No.U00443 �r�W�l�X�G���[�łȂ��V�X�e���G���[�ɕϊ����Ă��� ����@@SRA START
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
            // 2004/11/19 ��Q�Ǘ��[No.U00443 �r�W�l�X�G���[�łȂ��V�X�e���G���[�ɕϊ����Ă��� ����@@SRA END
        }      
    }
    
    /**
     * (validate��������)<BR>
     * �Ǘ���IPO���������m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���������jvalidate���������v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO���������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductChangeConfirmResponse
     * @@roseuid 40C945770069
     */
    protected WEB3AdminIPOProductChangeConfirmResponse validateProductChange(WEB3AdminIPOProductChangeConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateProductChange(WEB3AdminIPOProductChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1.validate( )
        l_request.validate();
        log.debug("validate");
        
        //1.2.getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
          
        //1.3.validate����( )
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.4.IPO����(long)
        long l_lngId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        
        try
        {
            WEB3IpoProductManagerImpl l_ipoProductManagerImpl =
                (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProductBefore = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngId);

            //1.5.is�폜����()
            boolean l_blnProduct = l_ipoProductBefore.isDeletedProduct();
            //1.6.is���~()
            boolean l_blnDiscontinuation = l_ipoProductBefore.isDiscontinuation();
            //1.7.is�폜����() == true�j�̏ꍇ�A��O���X���[����
            if(l_blnProduct)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            }
            //(is���~() == true�j�̏ꍇ�A��O���X���[����
            if(l_blnDiscontinuation)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            }       
       
            //1.8.createIPO����(IPO����Params, IPO�������, �Ǘ���)
            WEB3IpoProductInfoService l_ipoProductInfoService = 
                (WEB3IpoProductInfoService)Services.getService(WEB3IpoProductInfoService.class);
            
            ////1.8.1.IPO����        
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_ipoProductBefore.getDataSourceObject();
            IpoProductParams l_ipoProductParams = new IpoProductParams(l_ipoProductRow);                       
            WEB3IpoProductImpl l_ipoProductAfter = l_ipoProductInfoService.createIpoProduct
                (l_ipoProductParams, l_request.ipoProductInfo, l_administrator);           
            log.debug("createIPO����");
            
            //1.9.validate�X�P�W���[��(null;)    
            l_ipoProductAfter.validateSchedule(null);
            log.debug("validate�X�P�W���[��");
            
            //1.10.get�،���ЃR�[�h( )
            String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
            //1.11.validate���ԏd���o�^(String, String, Date, Date, long)                 
            l_ipoProductManagerImpl.validateDuplicateTerm
                (l_strInstitutionCode, l_request.ipoProductInfo.productCode, l_request.ipoProductInfo.bookBuildingStartDate,
                l_request.ipoProductInfo.publicOfferingDate.startDate, l_lngId);    
            log.debug("validate���ԏd���o�^(String, String, Date, Date, long)");
            
            //1.12.validateChangeIPO����(IPO����, IPO����)
            log.debug("validateChangeIPO����(IPO����, IPO����)");
            this.validateChangeIpoProduct(l_ipoProductBefore, l_ipoProductAfter); 
//            ////1.12.1.is�X�P�W���[������(�����O)
//            boolean l_blSchChkBfr = l_ipoProductBefore.isScheduleDecision();
//
//            ////1.12.2.is�X�P�W���[������(������)     
//            boolean l_blSchChkAft = l_ipoProductAfter.isScheduleDecision();
//            if(!(l_blSchChkBfr) || !(l_blSchChkAft))
//            {
//            
//                log.exiting(STR_METHOD_NAME);
//                throw new WEB3BusinessLayerException(
//                //"�X�P�W���[��������ł��邱�Ƃ̒ʒm"
//                    WEB3ErrorCatalog.BUSINESS_ERROR_00528,
//                    this.getClass().getName() + STR_METHOD_NAME);
//             
//            }
                      
            //1.13.validate��������()
            l_ipoProductAfter.validateProduct();
            log.debug("validate��������");
                                   
            //1.14.set������( )
            log.debug("set������( )");
            l_ipoProductAfter.setStandardName();
        
            //1.15.createResponse( )
            WEB3AdminIPOProductChangeConfirmResponse l_productChangeConfirmResponse =  
                (WEB3AdminIPOProductChangeConfirmResponse)l_request.createResponse();
                
            //1.16.get������( )
            String l_strStandardName = l_ipoProductAfter.getStandardName();
            log.debug("l_strStandardName = " + l_strStandardName);
        
            //1.17.(*1) �v���p�e�B�Z�b�g
            l_productChangeConfirmResponse.productName = l_strStandardName;
 
            log.exiting(STR_METHOD_NAME);               
            return l_productChangeConfirmResponse;
    
        }
        catch (NotFoundException l_ex)
        {  
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
 
    }
    
    /**
     * (submit��������)<BR>
     * �Ǘ���IPO�������������������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���������jsubmit���������v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO���������������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductChangeCompleteResponse
     * @@roseuid 40C945770078
     */
    protected WEB3AdminIPOProductChangeCompleteResponse submitProductChange(WEB3AdminIPOProductChangeCompleteRequest l_request) 
        throws WEB3BaseException 
    {   
        final String STR_METHOD_NAME = " submitProductChange(WEB3AdminIPOProductChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        // 1.2.getInstanceFrom���O�C�����( )     
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate����( )     
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.4.validate����p�X���[�h(String)
        l_administrator.validateTradingPassword(l_request.password);
               
        //1.5.IPO����(long)
        long l_lngId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                
        try
        {
            WEB3IpoProductManagerImpl l_ipoProductManagerImpl =
                (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProductBefore = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngId);

            //1.6.is�폜����()
            boolean l_blnProduct = l_ipoProductBefore.isDeletedProduct();
            //1.7.is���~()
            boolean l_blnDiscontinuation = l_ipoProductBefore.isDiscontinuation();
            //1.8.is�폜����() == true�j�̏ꍇ�A��O���X���[����
            if(l_blnProduct)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            }
            //(is���~() == true�j�̏ꍇ�A��O���X���[����
            if(l_blnDiscontinuation)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            }       
       
            //1.9.createIPO����(IPO����Params, IPO�������, �Ǘ���)
            WEB3IpoProductInfoService l_ipoProductInfoService = 
                (WEB3IpoProductInfoService)Services.getService(WEB3IpoProductInfoService.class);
            
            ////1.9.1.IPO����        
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_ipoProductBefore.getDataSourceObject();
            IpoProductParams l_ipoProductParams = new IpoProductParams(l_ipoProductRow);                       
            WEB3IpoProductImpl l_ipoProductAfter = l_ipoProductInfoService.createIpoProduct
                (l_ipoProductParams, l_request.ipoProductInfo, l_administrator);           
            log.debug("createIPO����");
            
            //1.10.validate�X�P�W���[��(�V�K�쐬�����@@Timestamp)    
            l_ipoProductAfter.validateSchedule(null);
            log.debug("validate�X�P�W���[��");
            
            //1.11.get�،���ЃR�[�h( )
            String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
            //1.12.validate���ԏd���o�^(String, String, Date, Date, long)                 
            l_ipoProductManagerImpl.validateDuplicateTerm
                (l_strInstitutionCode, l_request.ipoProductInfo.productCode, l_request.ipoProductInfo.bookBuildingStartDate,
                l_request.ipoProductInfo.publicOfferingDate.startDate, l_lngId);    
            log.debug("validate���ԏd���o�^(String, String, Date, Date, long)");
            
            //1.13.validateChangeIPO����(IPO����, IPO����)
            log.debug("validateChangeIPO����(IPO����, IPO����)");
            this.validateChangeIpoProduct(l_ipoProductBefore, l_ipoProductAfter); 
//            ////1.13.1.is�X�P�W���[������(�����O)
//            boolean l_blSchChkBfr = l_ipoProductBefore.isScheduleDecision();
//
//            ////1.13.2.is�X�P�W���[������(������)     
//            boolean l_blSchChkAft = l_ipoProductAfter.isScheduleDecision();
//            if(!(l_blSchChkBfr) || !(l_blSchChkAft))
//            {
//            
//                log.exiting(STR_METHOD_NAME);
//                throw new WEB3BusinessLayerException(
//                //"�X�P�W���[��������ł��邱�Ƃ̒ʒm"
//                    WEB3ErrorCatalog.BUSINESS_ERROR_00528,
//                    this.getClass().getName() + STR_METHOD_NAME);
//             
//            }
            
            //1.14.validate��������()
            l_ipoProductAfter.validateProduct();
            log.debug("validate��������");
                        
            //1.15.set������( )
            log.debug("set������( )");
            l_ipoProductAfter.setStandardName();
            
            //1.16.save����(IPO����)
            l_ipoProductManagerImpl.saveProduct(l_ipoProductAfter);
            log.debug("save����(IPO����)");
                        
            //1.17.(*2) �u�b�N�r���f�B���O�I���������������ꂽ�ꍇ
            IpoProductRow l_ipoProductRowAfter = (IpoProductRow)(l_ipoProductAfter.getDataSourceObject());
            IpoProductRow l_ipoProductRowBefore = (IpoProductRow)(l_ipoProductBefore.getDataSourceObject());
            
            if(l_ipoProductRowBefore.getBookbuildingEndDatetime().compareTo(l_ipoProductRowAfter.getBookbuildingEndDatetime()) != 0)
            {           
                //1.17.1.findRowsByIpoProductId()
                log.debug("findRowsByIpoProductId");
                long l_lngIpoProductId = l_ipoProductRowAfter.getIpoProductId();
                List l_lisIpoOrders = IpoOrderDao.findRowsByIpoProductId(l_lngIpoProductId);
                
                //1.17.2.get�Ǘ��҃R�[�h( )
                String l_strAdministratorCode = l_administrator.getAdministratorCode();
                
                //1.17.3.(*1.1) �u�b�N�r���f�B���O�\���e�v�f���Ƃ�LOOP
                IpoOrderRow[] l_ipoOrderRow = null;
                l_ipoOrderRow = new IpoOrderRow[l_lisIpoOrders.size()];
                
                l_lisIpoOrders.toArray(l_ipoOrderRow); 
                          
                for(int i = 0; i < l_ipoOrderRow.length; i++)
                {                   
                    log.debug("updateIPO�\��");
                    //1.17.3.1.updateIPO�\��(long, Date, String)                       
                    this.updateIpoOrder
                        (l_ipoOrderRow[i].getIpoOrderId(),l_ipoProductRowAfter.getBookbuildingEndDatetime(),l_strAdministratorCode);
                }                                          
            }
            //1.18.createResponse( )
            WEB3AdminIPOProductChangeCompleteResponse l_productChangeCompleteResponse =  
                (WEB3AdminIPOProductChangeCompleteResponse)l_request.createResponse();
                
            log.exiting(STR_METHOD_NAME);
            return l_productChangeCompleteResponse;   
        }    
        catch(NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        } 
        catch(DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName()  + STR_METHOD_NAME,
                l_ex);
            
        } 
        catch(DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        }       
    }

    /** 
     * �Ǘ���IPO�����������������{����B<BR>
     * <BR>
     * �P�j�@@�Ɩ������ݒ�<BR>
     * �@@������ԊǗ�.setBusinessTimestamp()���R�[������B<BR>
     * <BR>
     * �Q�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO�����������̓��N�G�X�g�̏ꍇ<BR>
     * �@@�|get���͉��()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO���������m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate��������()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO���������������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit��������()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40C945770098
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " execute(WEB3GenRequest)";
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
        
        if(l_request instanceof WEB3AdminIPOProductChangeInputRequest)
        {
            WEB3AdminIPOProductChangeInputResponse l_response = getInputScreen(
                (WEB3AdminIPOProductChangeInputRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if(l_request instanceof WEB3AdminIPOProductChangeConfirmRequest)
        {
            WEB3AdminIPOProductChangeConfirmResponse l_productChangeConfirmResponse = validateProductChange(
                (WEB3AdminIPOProductChangeConfirmRequest)l_request);                
            
            log.exiting(STR_METHOD_NAME);
            return l_productChangeConfirmResponse;
        }
        else if(l_request instanceof WEB3AdminIPOProductChangeCompleteRequest)
        {
            WEB3AdminIPOProductChangeCompleteResponse l_ipoProductChangeCompleteResponse = submitProductChange(
                (WEB3AdminIPOProductChangeCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductChangeCompleteResponse;
        }
        else
        {
            // Timestamp���Z�b�g
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + STR_METHOD_NAME);
        }
 
    }
    
    /**
     * (validateChangeIPO����)<BR>
     * �������ڂ��`�F�b�N����B<BR>
     * �@@���@@�������ڂ����邩�`�F�b�N���s���B<BR>
     * �@@���@@����σX�P�W���[��������ɒ�������Ă��Ȃ����`�F�b�N���s���B<BR>
     * �@@���@@�i�u�b�N�r�����ԒZ�k�̏ꍇ�j�X�P�W���[�����ڂ����肳��Ă��邩�`�F�b�N���s���B<BR>
     * �@@���@@�e���ڂɂ��āA�����\���ԓ����`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�������ڂ����邩�`�F�b�N���s���B<BR>
     * �@@IPO�����i�����O�j.IPO�����s��IPO�����i������j.IPO�����s�̈ȉ��̊e<BR>���ڂɂ��āA�l�������ł��邩�𔻒肷��B<BR>
     * ���ׂĂ̒l�������ł���ꍇ�A�������ڂ�����Ȃ��Ɣ��肵�A��O���X���[����B<BR>
     * <BR>
     * �@@[��r���鍀��]<BR>
     * �@@IPO�o�^�敪<BR>
     * �@@IPO�o�^�敪�ڍ�<BR>
     * �@@�����R�[�h<BR>
     * �@@������<BR>
     * �@@���J��<BR>
     * �@@���J������<BR>
     *   WEB3-IPO-A-CD-0024.xls<BR>
     *  �~�@@�s��ID�C���敪�i���폜�j<BR>
     *  ���@@���J�s��i���ǉ��j<BR>
     *   ���J�s��<BR>
     * �@@�������敪<BR>
     * �@@�����������l<BR>
     * �@@����������l<BR>
     * �@@�������񎦓�<BR>
     * �@@���吔��<BR>
     * �@@���o����<BR>
     * �@@���Ў戵����<BR>
     * �@@�劲���،���Ж�<BR>
     * �@@�w���\���P��<BR>
     * �@@����<BR>
     * �@@�\���p�P�ʋ敪<BR>
     *   ���s�\<BR>
     * �@@�u�b�N�r���f�B���O�J�n����<BR>
     * �@@�u�b�N�r���f�B���O�I������<BR>
     * �@@���J���i�����<BR>
     * �@@���J���i<BR>
     * �@@���J���i�i�f�B�X�J�E���g���j<BR>
     * �@@���I��<BR>
     * �@@���I������<BR>
     * �@@�w���\���J�n�����i���Аݒ�j<BR>
     * �@@�w���\���I�������i���Аݒ�j<BR>
     * �@@�w���\���J�n�������i���Аݒ�j<BR>
     * �@@�w���\���I���������i���Аݒ�j<BR>
     * �@@�w���\���J�n���i�ژ_�����L�ځj<BR>
     * �@@�w���\���I�����i�ژ_�����L�ځj<BR>
     * �@@�w���\���J�n�������i�ژ_�����L�ځj<BR>
     * �@@�w���\���I���������i�ژ_�����L�ځj<BR>
     * �@@���s��Ѓ��S�t�@@�C���t�q�k<BR>
     * �@@���s��ЃE�F�u�T�C�g�t�q�k<BR>
     * �@@���s��ЊT�v<BR>
     * �@@���l<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00547<BR>
     * <BR>
     * �Q�j�@@�X�P�W���[������`�F�b�N<BR>
     * �@@�Q�|�P�j�@@����ւ̕ύX�`�F�b�N<BR>
     * �@@�@@����ς̃X�P�W���[���𖢒�ɒ������ꂽ�ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@[Error����]<BR>
     * �@@�@@�iIPO�����i�����O�j.is�X�P�W���[������() == true�j &&<BR>
     * �@@�@@�iIPO�����i������j.is�X�P�W���[������() == false�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00548<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�u�b�N�r���f�B���O�I�������̒����`�F�b�N<BR>
     * �@@�@@�u�b�N�r���f�B���O�I�����������ݓ����ȑO�ɒ�������Ă����ꍇ�A<BR>
     * �@@�@@�������IPO�����̃X�P�W���[��������ł���Η�O���X���[����B<BR>
     * <BR>
     * �@@�@@[Error����]<BR>
     * �@@�@@�iIPO�����i�����O�j.�u�b�N�r���f�B���O�I������ != IPO�����i������j.�u�b�N<BR>�r���f�B���O�I�������j &&<BR>
     * �@@�@@�iIPO�����i������j.is�u�b�N�r���f�B���O�I��() == true�j &&<BR>
     * �@@�@@�iIPO�����i������j.is�X�P�W���[������() == false�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00549<BR>
     * <BR>
     * �R�j�@@�����\���ԃ`�F�b�N<BR>
     * <BR>
     * �@@�R�|�O�j�@@�����\���Ԃ��A�i�`�u�b�N�r���f�B���O�J�n�����j�̍��ڂ̃`�F�b�N<BR>
     * �@@<BR>
     * �@@�E�u���J���i�v��Null���ǂ����Ɋ֌W�Ȃ���������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00582<BR>
     * �@@�E�u���J���i�i�f�B�X�J�E���g���j�v��Null���ǂ����Ɋ֌W�Ȃ���������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00584<BR>
     * <BR>
     * �@@�R�|�P�j�@@�����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N<BR>
     * �@@�iIPO�����i�����O�j.is�u�b�N�r���f�B���O�J�n() == true�j�̏ꍇ�A�ȉ��̃`�F�b�N<BR>�����{����B<BR>
     * �@@<BR>
     * �@@�E�uIPO�o�^�敪�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00550<BR>
     * �@@�E�uIPO�o�^�敪�ڍׁv����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00551<BR>
     * �@@�E�u�������v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00552<BR>
     * �@@�E�u���J�s��v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00553<BR>
     * �@@�E�u�������敪�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00554<BR>
     * �@@�E�u����������l�v��null�ɒ�������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00555<BR>
     * �@@�E�u�����������l�v��null�ɒ�������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00556<BR>
     * �@@�E�u�������񎦓��v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00557<BR>
     * �@@�E�u�w���\���P�ʁv����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00558<BR>
     * �@@�E�u���݁v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00559<BR>
     * �@@�E�u�\���p�P�ʋ敪�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00561<BR>
     *   �E�u���s�\�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01234<BR>
     * �@@�E�u�劲���،���Ж��v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00562<BR>
     * �@@�E�u�u�b�N�r���f�B���O�J�n�����v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00563<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�����\���Ԃ��A�V�K���I�I���܂ł̍��ڂ̃`�F�b�N<BR>
     * �@@�iIPO�����i�����O�j.is�V�K���I�I��() == true�j�̏ꍇ�A�ȉ��̃`�F�b�N�����{����B<BR>
     * <BR>
     * �@@�E�u���J���v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00564<BR>
     * �@@�E�u����������l�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00565<BR>
     * �@@�E�u�����������l�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00566<BR>
     * �@@�E�u�u�b�N�r���f�B���O�I�������v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00567<BR>
     * �@@�E�u���J���i������v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00568<BR>
     * �@@�E�u���I���v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00569<BR>
     * �@@�E�u���J���i�v��null�ɒ�������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00582<BR>
     * <BR>
     * �@@�R�|�R�j�@@�����\���Ԃ��A���J���܂ł̍��ڂ̃`�F�b�N<BR>
     * �@@�iIPO�����i�����O�j.is���J��() == true�j�̏ꍇ�A�ȉ��̃`�F�b�N�����{����B<BR>
     * <BR>
     * �@@�E�u�����R�[�h�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00570<BR>
     * �@@�E�u���吔�ʁv����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00571<BR>
     * �@@�E�u���o���ʁv����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00572<BR>
     * �@@�E�u���Ў戵���ʁv����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00573<BR>
     * �@@�E�u���s��Ѓ��S�t�@@�C��URL�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00574<BR>
     * �@@�E�u���s��ЃE�F�u�T�C�gURL�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00575<BR>
     * �@@�E�u���s��ЊT�v�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00576<BR>
     * �@@�E�u���l�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00577<BR>
     * <BR>
     * �@@�R�|�S�j�@@�����\���Ԃ��A�w���\���J�n���i�ژ_�����L�ځj�܂ł̍��ڂ̃`�F�b�N<BR>
     * �@@�iIPO�����i�����O�j.is�w���\���J�n�i�ژ_�����L�ځj() == true�j�̏ꍇ�A<BR>�ȉ��̃`�F�b�N�����{����B<BR>
     * <BR>
     * �@@�E�u�w���\���J�n���i�ژ_�����L�ځj�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00578<BR>
     * <BR>
     * �@@�R�|�T�j�@@�����\���Ԃ��A�w���\���I�����i�ژ_�����L�ځj�܂ł̍��ڂ̃`�F�b�N<BR>
     * �@@�iIPO�����i�����O�j.is�w���\���I���i�ژ_�����L�ځj() == true�j�̏ꍇ�A<BR>�ȉ��̃`�F�b�N�����{����B<BR>
     * <BR>
     * �@@�E�u�w���\���I�����i�ژ_�����L�ځj�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00579<BR>
     * �@@<BR>
     * �@@�R�|�U�j�@@�����\���Ԃ��A�w���\���J�n�����i���Аݒ�j�܂ł̍��ڂ̃`�F�b�N<BR>
     * �@@�iIPO�����i�����O�j.is�w���\���J�n�i���Аݒ�j() == true�j�̏ꍇ�A�ȉ�<BR>�̃`�F�b�N�����{����B<BR>
     * <BR>
     * �@@�E�u�w���\���J�n�����i���Аݒ�j�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00580<BR>
     * �@@�E�u���J���i�v��Null�łȂ��Ƃ���������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00582<BR>
     * �@@�E�u���J���i�i�f�B�X�J�E���g���j�v��Null�łȂ��Ƃ���������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00584<BR>
     * <BR>
     * �@@�R�|�V�j�@@�����\���Ԃ��A�w���\���I�������i���Аݒ�j�܂ł̍��ڂ̃`�F�b�N<BR>
     * �@@�iIPO�����i�����O�j.is�w���\���I���i���Аݒ�j() == true�j�̏ꍇ�A�ȉ�<BR>�̃`�F�b�N�����{����B<BR>
     * <BR>
     * �@@�E�u�w���\���I�������i���Аݒ�j�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00581<BR>
     * �@@<BR>
     * �@@�R�|�W�j�@@�����\���Ԃ��A�i�u�b�N�r���f�B���O�J�n�����`�V�K���I�I���j�̍��ڂ̃`�F�b�N<BR>
     * �@@�iIPO�����i�����O�j.is�u�b�N�r���f�B���O�J�n() == false Or<BR>
     * �@@�@@IPO�����i�����O�j.is�V�K���I�I��() == true�j�̏ꍇ�A�ȉ��̃`�F�b�N�����{����B<BR>
     * <BR>
     * �@@�E�u���J���i�v��null�ɒ�������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00582<BR>
     * <BR>
     * �@@�R�|�X�j�@@�����\���Ԃ��A�i�u�b�N�r���f�B���O�J�n�����`�w���\���J�n��<BR>���i���Аݒ�j�j�̍��ڂ̃`�F�b�N<BR>
     * �@@�iIPO�����i�����O�j.is�u�b�N�r���f�B���O�J�n() == false Or<BR>
     * �@@�@@IPO�����i�����O�j.is�w���\���J�n�i���Аݒ�j() == true�j�̏ꍇ�A�ȉ�<BR>�̃`�F�b�N�����{����B<BR>
     * <BR>
     * �@@�E�u���J���i�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00583<BR>
     * �@@�E�u���J���i�i�f�B�X�J�E���g���j�v����������Ă���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00584<BR>
     * @@param l_ipoProductBeforeChanging - (IPO�����i�����O�j)<BR>
     * IPO�����I�u�W�F�N�g<BR>
     * �� �����f�[�^�̒l
     * 
     * @@param l_ipoProductChanged - (IPO�����i������j)<BR>
     * IPO�����I�u�W�F�N�g<BR>
     * �� �������͒l
     * @@roseuid 40C95A87027C
     */
    protected void validateChangeIpoProduct(WEB3IpoProductImpl l_ipoProductBeforeChanging, WEB3IpoProductImpl l_ipoProductChanged) throws WEB3BusinessLayerException 
    {
        String STR_METHOD_NAME = " validateChangeIpoProduct(WEB3IpoProductImpl,WEB3IpoProductImpl)";
                 log.entering(STR_METHOD_NAME);
        
        IpoProductRow l_ipoProductRow = (IpoProductRow)(l_ipoProductBeforeChanging.getDataSourceObject());
        IpoProductParams l_ipoProductBeforeChangingParams = new IpoProductParams(l_ipoProductRow);
        
        IpoProductRow l_ipoProductChangedRow = (IpoProductRow)(l_ipoProductChanged.getDataSourceObject());
        IpoProductParams l_ipoProductChangedParams = new IpoProductParams(l_ipoProductChangedRow);
        
            //IPO�o�^�敪                                
        if (l_ipoProductBeforeChangingParams.getIpoRegistDiv().equals(l_ipoProductChangedParams.getIpoRegistDiv())                   
            //IPO�o�^�敪�ڍ�        
            && l_ipoProductBeforeChangingParams.getIpoRegistDetailDiv().equals(l_ipoProductChangedParams.getIpoRegistDetailDiv()) 
            //�����R�[�h
            && l_ipoProductBeforeChangingParams.getProductCode().equals(l_ipoProductChangedParams.getProductCode())                   
            //������
            && compareToString(l_ipoProductBeforeChangingParams.getStandardName(), l_ipoProductChangedParams.getStandardName())                 
            //���J��
            && (WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getPublicOfferingDate(), 
                l_ipoProductChangedParams.getPublicOfferingDate()) == 0)   
            //���J������    
            && l_ipoProductBeforeChangingParams.getPublicOfferingDateCountIsNull() == l_ipoProductChangedParams.getPublicOfferingDateCountIsNull()
            && l_ipoProductBeforeChangingParams.getPublicOfferingDateCount() == l_ipoProductChangedParams.getPublicOfferingDateCount()  
            //���J�s��
            && l_ipoProductBeforeChangingParams.getPublicMarket().equals(l_ipoProductChangedParams.getPublicMarket())                                                                
            //�������敪                                                                                                       
            && l_ipoProductBeforeChangingParams.getProvisionalValueDiv().equals(l_ipoProductChangedParams.getProvisionalValueDiv())   
            //�����������l
            && l_ipoProductBeforeChangingParams.getProvisionalMinValueIsNull() == l_ipoProductChangedParams.getProvisionalMinValueIsNull()
            && l_ipoProductBeforeChangingParams.getProvisionalMinValue() == l_ipoProductChangedParams.getProvisionalMinValue()  
            //����������l 
            && l_ipoProductBeforeChangingParams.getProvisionalMaxValueIsNull() == l_ipoProductChangedParams.getProvisionalMaxValueIsNull()
            && l_ipoProductBeforeChangingParams.getProvisionalMaxValue() == l_ipoProductChangedParams.getProvisionalMaxValue() 
            //�������񎦓�
            && (WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getProvisionalValueOpenDate(), 
                l_ipoProductChangedParams.getProvisionalValueOpenDate()) == 0)
            //���吔��
            && l_ipoProductBeforeChangingParams.getPublicOfferingQuantityIsNull() == l_ipoProductChangedParams.getPublicOfferingQuantityIsNull()
            && l_ipoProductBeforeChangingParams.getPublicOfferingQuantity() == l_ipoProductChangedParams.getPublicOfferingQuantity()
            //���o����
            && l_ipoProductBeforeChangingParams.getPublicSalesQuantityIsNull() == l_ipoProductChangedParams.getPublicSalesQuantityIsNull()
            && l_ipoProductBeforeChangingParams.getPublicSalesQuantity() == l_ipoProductChangedParams.getPublicSalesQuantity() 
            //���Ў戵����
            && l_ipoProductBeforeChangingParams.getDealingQuantityIsNull() == l_ipoProductChangedParams.getDealingQuantityIsNull()
            && l_ipoProductBeforeChangingParams.getDealingQuantity() == l_ipoProductChangedParams.getDealingQuantity()     
            //�劲���،���Ж�
            && compareToString(l_ipoProductBeforeChangingParams.getLeadManagingUnderwriter(), l_ipoProductChangedParams.getLeadManagingUnderwriter())
            //�w���\���P��
            && l_ipoProductBeforeChangingParams.getLotSizeIsNull() == l_ipoProductChangedParams.getLotSizeIsNull()
            && l_ipoProductBeforeChangingParams.getLotSize() == l_ipoProductChangedParams.getLotSize()             
            //����
            && l_ipoProductBeforeChangingParams.getTickValueIsNull() == l_ipoProductChangedParams.getTickValueIsNull()
            && l_ipoProductBeforeChangingParams.getTickValue() == l_ipoProductChangedParams.getTickValue()         
            //�\���p�P�ʋ敪
            && compareToString(l_ipoProductBeforeChangingParams.getIpoUnitDiv(), l_ipoProductChangedParams.getIpoUnitDiv()) 
			//	2004/11/25 U00467 ���s�\���������ꂽ���ǂ����̃`�F�b�N�ǉ��@@���@@SRA  START
            //���s�\
			&& compareToString(l_ipoProductBeforeChangingParams.getEnableMarketOrder(), l_ipoProductChangedParams.getEnableMarketOrder())
			//	2004/11/25 U00467 ���s�\���������ꂽ���ǂ����̃`�F�b�N�ǉ��@@���@@SRA  END
            //�u�b�N�r���f�B���O�J�n����
			//	2004/11/25 U00466 ���������x�܂Ŕ�r����悤�ɏC���@@���@@SRA  START            
            && WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getBookbuildingStartDatetime(), 
                l_ipoProductChangedParams.getBookbuildingStartDatetime()) == 0
//			&& WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getBookbuildingStartDatetime(), 
//						l_ipoProductChangedParams.getBookbuildingStartDatetime()) == 0
   			//	2004/11/25 U00466 ���������x�܂Ŕ�r����悤�ɏC���@@���@@SRA  END                 
            //�u�b�N�r���f�B���O�I������
			//	2004/11/25 U00466 ���������x�܂Ŕ�r����悤�ɏC���@@���@@SRA  START  
            && WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getBookbuildingEndDatetime(), 
                l_ipoProductChangedParams.getBookbuildingEndDatetime()) == 0
//			&& WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getBookbuildingEndDatetime(), 
//			l_ipoProductChangedParams.getBookbuildingEndDatetime()) == 0
			//	2004/11/25 U00466 ���������x�܂Ŕ�r����悤�ɏC���@@���@@SRA  END     
            //���J���i�����
            && WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getPublicPriceSettleDate(), 
                l_ipoProductChangedParams.getPublicPriceSettleDate()) == 0
            //���J���i
            && l_ipoProductBeforeChangingParams.getPublicPriceIsNull() == l_ipoProductChangedParams.getPublicPriceIsNull()
            && l_ipoProductBeforeChangingParams.getPublicPrice() == l_ipoProductChangedParams.getPublicPrice()         
            //���J���i�i�f�B�X�J�E���g���j
            && l_ipoProductBeforeChangingParams.getPublicPriceDiscountRateIsNull() == l_ipoProductChangedParams.getPublicPriceDiscountRateIsNull()
            && l_ipoProductBeforeChangingParams.getPublicPriceDiscountRate() == l_ipoProductChangedParams.getPublicPriceDiscountRate() 
            //���I��
            && WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getLotDate(), 
                l_ipoProductChangedParams.getLotDate()) == 0        
            //���I������
            && l_ipoProductBeforeChangingParams.getLotDateCountIsNull() == l_ipoProductChangedParams.getLotDateCountIsNull()
            && l_ipoProductBeforeChangingParams.getLotDateCount() == l_ipoProductChangedParams.getLotDateCount()       
            //�w���\���J�n�����i���Аݒ�j
			//	2004/11/25 U00466 ���������x�܂Ŕ�r����悤�ɏC���@@���@@SRA  START
            && WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getOfferStartDatetime(), 
                l_ipoProductChangedParams.getOfferStartDatetime()) == 0
//			&& WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferStartDatetime(), 
//						l_ipoProductChangedParams.getOfferStartDatetime()) == 0
			//	2004/11/25 U00466 ���������x�܂Ŕ�r����悤�ɏC���@@���@@SRA  END 
            //�w���\���I������
			//	2004/11/25 U00466 ���������x�܂Ŕ�r����悤�ɏC���@@���@@SRA  START
            && WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getOfferEndDatetime(), 
                l_ipoProductChangedParams.getOfferEndDatetime()) == 0
//			&& WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferEndDatetime(), 
//						l_ipoProductChangedParams.getOfferEndDatetime()) == 0
			//	2004/11/25 U00466 ���������x�܂Ŕ�r����悤�ɏC���@@���@@SRA  END 
            //�w���\���J�n�������i���Аݒ�j 
            && l_ipoProductBeforeChangingParams.getOfferStartDateCountIsNull() == l_ipoProductChangedParams.getOfferStartDateCountIsNull()
            && l_ipoProductBeforeChangingParams.getOfferStartDateCount() == l_ipoProductChangedParams.getOfferStartDateCount()
            //�w���\���I���������i���Аݒ�j
            && l_ipoProductBeforeChangingParams.getOfferEndDateCountIsNull() == l_ipoProductChangedParams.getOfferEndDateCountIsNull()
            && l_ipoProductBeforeChangingParams.getOfferEndDateCount() == l_ipoProductChangedParams.getOfferEndDateCount()
            //�w���\���J�n���i�ژ_�����L�ځj
            && WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferStartDateProspec(),
                l_ipoProductChangedParams.getOfferStartDateProspec()) == 0
            //�w���\���I�����i�ژ_�����L�ځj
            && WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferEndDateProspec(),
                l_ipoProductChangedParams.getOfferEndDateProspec()) == 0 
            //�w���\���J�n�������i�ژ_�����L��)
            && l_ipoProductBeforeChangingParams.getOfferStartDateCountProspecIsNull() == l_ipoProductChangedParams.getOfferStartDateCountProspecIsNull() 
            && l_ipoProductBeforeChangingParams.getOfferStartDateCountProspec() == l_ipoProductChangedParams.getOfferStartDateCountProspec()
		    //�w���\���I���������i�ژ_�����L�ځj�@@
            && l_ipoProductBeforeChangingParams.getOfferEndDateCountProspecIsNull() == l_ipoProductChangedParams.getOfferEndDateCountProspecIsNull()
            && l_ipoProductBeforeChangingParams.getOfferEndDateCountProspec() == l_ipoProductChangedParams.getOfferEndDateCountProspec()
            //���s��Ѓ��S�t�@@�C���t�q�k
            && compareToString(l_ipoProductBeforeChangingParams.getCompanyLogoUrl(),l_ipoProductChangedParams.getCompanyLogoUrl()) 
            //���s��ЃE�F�u�T�C�g�t�q�k
            && compareToString(l_ipoProductBeforeChangingParams.getCompanyUrl(), l_ipoProductChangedParams.getCompanyUrl())
            //���s��ЊT�v
            && compareToString(l_ipoProductBeforeChangingParams.getCompanyOutline(), l_ipoProductChangedParams.getCompanyOutline())
            //�@@���l
            && compareToString(l_ipoProductBeforeChangingParams.getNote(), l_ipoProductChangedParams.getNote())
            // �ژ_�����{���敪 
            && compareToString(l_ipoProductBeforeChangingParams.getDocReadingDiv(), l_ipoProductChangedParams.getDocReadingDiv()))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00547,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //�X�P�W���[������`�F�b�N
        //�Q�|�P�j�@@����ւ̕ύX�`�F�b�N
        if(l_ipoProductBeforeChanging.isScheduleDecision() && !l_ipoProductChanged.isScheduleDecision())
        {  
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00548,
                this.getClass().getName() + STR_METHOD_NAME);         
        }
        //�Q�|�Q�j�@@�u�b�N�r���f�B���O�I�������̒����`�F�b�N
		//2004/11/17 U00433 ���������x�̔�r�ɏC���@@���@@SRA   START
        if (WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getBookbuildingEndDatetime(),
            l_ipoProductChangedParams.getBookbuildingEndDatetime()) != 0            
            && l_ipoProductChanged.isBookbuildingEnd()
            && !l_ipoProductChanged.isScheduleDecision())
//		if (WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getBookbuildingEndDatetime(),
//					l_ipoProductChangedParams.getBookbuildingEndDatetime()) != 0            
//					&& l_ipoProductChanged.isBookbuildingEnd()
//					&& !l_ipoProductChanged.isScheduleDecision())
		//2004/11/17 U00433 ���������x�̔�r�ɏC���@@���@@SRA   END
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00549,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //�����\���ԃ`�F�b�N        
		//�R�|�O�j�@@�����\���Ԃ��A�i�`�u�b�N�r���f�B���O�J�n�����j�̍��ڂ̃`�F�b�N   
			  // 2004/11/16 U00423 BB�J�n���O�̓��ɒ������o���Ȃ��s��Ή��@@���@@SRA  START         
			  if (!l_ipoProductBeforeChanging.isBookbuildingStart())
			  {
				  //�u���J���i�v��Null���ǂ����Ɋ֌W�Ȃ���������Ă���΁A��O���X���[����
				  if(l_ipoProductBeforeChangingParams.getPublicPrice() != l_ipoProductChangedParams.getPublicPrice())
				  {
					// 2004/11/25 U00465 �G���[���b�Z�[�W���s�K�؂̂��ߑΉ��@@���@@SRA  START
					  throw new WEB3BusinessLayerException(
						  WEB3ErrorCatalog.BUSINESS_ERROR_00583,
						  this.getClass().getName() + STR_METHOD_NAME);
//					  throw new WEB3BusinessLayerException(
//											  WEB3ErrorCatalog.BUSINESS_ERROR_00582,
//											  this.getClass().getName() + STR_METHOD_NAME);
					// 2004/11/25 U00465 �G���[���b�Z�[�W���s�K�؂̂��ߑΉ��@@���@@SRA  END	  
				  }
				  //�u���J���i�i�f�B�X�J�E���g���j�v��Null���ǂ����Ɋ֌W�Ȃ���������Ă���΁A��O���X���[����
				  if(l_ipoProductBeforeChangingParams.getPublicPriceDiscountRate()
					  != l_ipoProductChangedParams.getPublicPriceDiscountRate())
				  {    
					  throw new WEB3BusinessLayerException(
						  WEB3ErrorCatalog.BUSINESS_ERROR_00584,
						  this.getClass().getName() + STR_METHOD_NAME); 
				  }  
			  }
			  // 2004/11/16 U00423 BB�J�n���O�̓��ɒ������o���Ȃ��s��Ή��@@���@@SRA  END           
        
        //�R�|�P�j�@@�����\���Ԃ��A�u�b�N�r���f�B���O�J�n�O�܂ł̍��ڂ̃`�F�b�N
        if(l_ipoProductBeforeChanging.isBookbuildingStart())
        {
            
            //�uIPO�o�^�敪�v����������Ă���΁A��O���X���[����B
            if (!compareToString(l_ipoProductBeforeChangingParams.getIpoRegistDiv(), 
                l_ipoProductChangedParams.getIpoRegistDiv()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00550,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�uIPO�o�^�敪�ڍׁv����������Ă���΁A��O���X���[����B
            if(!compareToString(l_ipoProductBeforeChangingParams.getIpoRegistDetailDiv(), 
                l_ipoProductChangedParams.getIpoRegistDetailDiv()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00551,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�u�������v����������Ă���΁A��O���X���[����B
            if(!compareToString(l_ipoProductBeforeChangingParams.getStandardName(), 
                l_ipoProductChangedParams.getStandardName()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00552,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�u���J�s��v����������Ă���΁A��O���X���[����B
            if(!compareToString(l_ipoProductBeforeChangingParams.getPublicMarket(),
                l_ipoProductChangedParams.getPublicMarket()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00553,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�u�������敪�v����������Ă���΁A��O���X���[����B
            if(!compareToString(l_ipoProductBeforeChangingParams.getProvisionalValueDiv(),
                l_ipoProductChangedParams.getProvisionalValueDiv()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00554,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //�u����������l�v��null�ɒ�������Ă���΁A��O���X���[����B
            if (l_ipoProductChangedParams.getProvisionalMaxValueIsNull() ||
                Double.isNaN(l_ipoProductChangedParams.getProvisionalMaxValue())) 
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00555,
                    this.getClass().getName() + STR_METHOD_NAME);
            }     
            //�u�����������l�v��null�ɒ�������Ă���΁A��O���X���[����B
            if (l_ipoProductChangedParams.getProvisionalMinValueIsNull()|| 
                Double.isNaN(l_ipoProductChangedParams.getProvisionalMinValue()))    
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00556,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //�u�������񎦓��v����������Ă���΁A��O���X���[����B
            if (WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getProvisionalValueOpenDate(),
                l_ipoProductChangedParams.getProvisionalValueOpenDate()) != 0)        
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00557,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //�u�w���\���P�ʁv����������Ă���΁A��O���X���[����B
            if((!l_ipoProductBeforeChangingParams.getLotSizeIsNull() || !l_ipoProductChangedParams.getLotSizeIsNull()) &&
                l_ipoProductBeforeChangingParams.getLotSize() != l_ipoProductChangedParams.getLotSize()) 
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00558,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //�u���݁v����������Ă���΁A��O���X���[����B
            if((!l_ipoProductBeforeChangingParams.getTickValueIsNull() || !l_ipoProductChangedParams.getTickValueIsNull()) &&
                l_ipoProductBeforeChangingParams.getTickValue() != l_ipoProductChangedParams.getTickValue())   
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00559,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //�u�\���p�P�ʋ敪�v����������Ă���΁A��O���X���[����B
            if(!compareToString(l_ipoProductBeforeChangingParams.getIpoUnitDiv(),
                l_ipoProductChangedParams.getIpoUnitDiv()))  
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00561,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            // 2004/11/25 U00467 �u���s�\�v����������Ă���Ƃ��̃`�F�b�N�ǉ��@@���@@SRA START
//			//�u���s�\�v����������Ă���΁A��O���X���[����B
			if(!compareToString(l_ipoProductBeforeChangingParams.getEnableMarketOrder(),
			    l_ipoProductChangedParams.getEnableMarketOrder()))  
			{
			   throw new WEB3BusinessLayerException(
			  	    WEB3ErrorCatalog.BUSINESS_ERROR_01234,
				    this.getClass().getName() + STR_METHOD_NAME);
			} 
			// 2004/11/25 U00467 �u���s�\�v����������Ă���Ƃ��̃`�F�b�N�ǉ��@@���@@SRA END       
            //�u�劲���،���Ж��v����������Ă���΁A��O���X���[����B
            if(!compareToString(l_ipoProductBeforeChangingParams.getLeadManagingUnderwriter(),
                l_ipoProductChangedParams.getLeadManagingUnderwriter()))   
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00562,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //�u�u�b�N�r���f�B���O�J�n�����v����������Ă���΁A��O���X���[����B
            //2004/11/17 U00433 ���������x�̔�r�ɏC���@@���@@SRA   START
			if (WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getBookbuildingStartDatetime(),
							l_ipoProductChangedParams.getBookbuildingStartDatetime()) != 0) 
//            if (WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getBookbuildingStartDatetime(),
//                l_ipoProductChangedParams.getBookbuildingStartDatetime()) != 0) 
			//2004/11/17 U00433 ���������x�̔�r�ɏC���@@���@@SRA   END
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00563,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
        }
        //�R�|�Q�j�@@�����\���Ԃ��A�V�K���I�I���܂ł̍��ڂ̃`�F�b�N
        if(l_ipoProductBeforeChanging.isNewLotteryEnd())
        {
            //�u���J���v����������Ă���΁A��O���X���[����B   
            if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getPublicOfferingDate(),
                l_ipoProductChangedParams.getPublicOfferingDate()) != 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00564,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //�u����������l�v����������Ă���΁A��O���X���[����
            if((!l_ipoProductBeforeChangingParams.getProvisionalMaxValueIsNull() || !l_ipoProductChangedParams.getProvisionalMaxValueIsNull()) &&
                l_ipoProductBeforeChangingParams.getProvisionalMaxValue() != l_ipoProductChangedParams.getProvisionalMaxValue())
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00565,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //�u�����������l�v����������Ă���΁A��O���X���[����
            if((!l_ipoProductBeforeChangingParams.getProvisionalMinValueIsNull() || !l_ipoProductChangedParams.getProvisionalMinValueIsNull()) && 
                l_ipoProductBeforeChangingParams.getProvisionalMinValue() != l_ipoProductChangedParams.getProvisionalMinValue())
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00566,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //�u�u�b�N�r���f�B���O�I�������v����������Ă���΁A��O���X���[����
			//2004/11/17 U00433 ���������x�̔�r�ɏC���@@���@@SRA   START            
            if(WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getBookbuildingEndDatetime(),
                l_ipoProductChangedParams.getBookbuildingEndDatetime()) != 0)
//			if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getBookbuildingEndDatetime(),
//							l_ipoProductChangedParams.getBookbuildingEndDatetime()) != 0)
			//2004/11/17 U00433 ���������x�̔�r�ɏC���@@���@@SRA   END                
            {   
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00567,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //�u���J���i������v����������Ă���΁A��O���X���[����
            if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getPublicPriceSettleDate(),
                l_ipoProductChangedParams.getPublicPriceSettleDate()) != 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00568,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�u���I���v����������Ă���΁A��O���X���[����
            if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getLotDate(), 
                l_ipoProductChangedParams.getLotDate()) != 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00569,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
			// 2004/11/16 U00423 BB�J�n���O�̓��ɒ������o���Ȃ��s��Ή��@@���@@SRA  START 
			//�u���J���i�v��null�ɒ�������Ă���΁A��O���X���[����
			if(l_ipoProductChangedParams.getPublicPriceIsNull() || 
				Double.isNaN(l_ipoProductChangedParams.getPublicPrice()))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00582,
					this.getClass().getName() + STR_METHOD_NAME);
			}
			// 2004/11/16 U00423 BB�J�n���O�̓��ɒ������o���Ȃ��s��Ή��@@���@@SRA  END			

        }
            //�R�|�R�j�@@�����\���Ԃ��A���J���܂ł̍��ڂ̃`�F�b�N
        if(l_ipoProductBeforeChanging.isPublicEnd()) 
        {
            //�u�����R�[�h�v����������Ă���΁A��O���X���[����
            if(!compareToString(l_ipoProductBeforeChangingParams.getProductCode(),
                l_ipoProductChangedParams.getProductCode()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00570,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //�u���吔�ʁv����������Ă���΁A��O���X���[����
            if((!l_ipoProductBeforeChangingParams.getPublicOfferingQuantityIsNull() || !l_ipoProductChangedParams.getPublicOfferingQuantityIsNull()) 
                && l_ipoProductBeforeChangingParams.getPublicOfferingQuantity() != l_ipoProductChangedParams.getPublicOfferingQuantity())
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00571,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�u���o���ʁv����������Ă���΁A��O���X���[����
            if((!l_ipoProductBeforeChangingParams.getPublicSalesQuantityIsNull() || !l_ipoProductChangedParams.getPublicSalesQuantityIsNull())
                && l_ipoProductBeforeChangingParams.getPublicSalesQuantity() != l_ipoProductChangedParams.getPublicSalesQuantity())
            {
          
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00572,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�u���Ў戵���ʁv����������Ă���΁A��O���X���[����
            if((!l_ipoProductBeforeChangingParams.getDealingQuantityIsNull() || !l_ipoProductChangedParams.getDealingQuantityIsNull())
                && l_ipoProductBeforeChangingParams.getDealingQuantity() != l_ipoProductChangedParams.getDealingQuantity())
            {
           
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00573,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�u���s��Ѓ��S�t�@@�C��URL�v����������Ă���΁A��O���X���[����
            if(!compareToString(l_ipoProductBeforeChangingParams.getCompanyLogoUrl(),
                l_ipoProductChangedParams.getCompanyLogoUrl()))
            {
          
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00574,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�u���s��ЃE�F�u�T�C�gURL�v����������Ă���΁A��O���X���[����
            if(!compareToString(l_ipoProductBeforeChangingParams.getCompanyUrl(),
                l_ipoProductChangedParams.getCompanyUrl()))
            {
          
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00575,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�u���s��ЊT�v�v����������Ă���΁A��O���X���[����
            if(!compareToString(l_ipoProductBeforeChangingParams.getCompanyOutline(),
                l_ipoProductChangedParams.getCompanyOutline()))
            {
            
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00576,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�u���l�v����������Ă���΁A��O���X���[����
            if(!compareToString(l_ipoProductBeforeChangingParams.getNote(),
                l_ipoProductChangedParams.getNote()))
            {
         
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00577,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }                        
        //�R�|�S�j�@@�����\���Ԃ��A�w���\���J�n���i�ژ_�����L�ځj�܂ł̍��ڂ̃`�F�b�N
        if(l_ipoProductBeforeChanging.isOfferStartProspec()) 
        {
            //�u�w���\���J�n���i�ژ_�����L�ځj�v����������Ă���΁A��O���X���[����
            if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferStartDateProspec(),
                l_ipoProductChangedParams.getOfferStartDateProspec()) != 0) 
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00578,
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
        }                                                
        //�R�|�T�j�@@�����\���Ԃ��A�w���\���I�����i�ژ_�����L�ځj�܂ł̍��ڂ̃`�F�b�N 
        if(l_ipoProductBeforeChanging.isOfferEndProspec())
        {
            //�u�w���\���I�����i�ژ_�����L�ځj�v����������Ă���΁A��O���X���[����
            if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferEndDateProspec(),
                l_ipoProductChangedParams.getOfferEndDateProspec()) != 0)
            {          
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00579,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        //�R�|�U�j�@@�����\���Ԃ��A�w���\���J�n�����i���Аݒ�j�܂ł̍��ڂ̃`�F�b�N
        if(l_ipoProductBeforeChanging.isOfferStart())
        {
            //�u�w���\���J�n�����i���Аݒ�j�v����������Ă���΁A��O���X���[����
			//2004/11/17 U00433 ���������x�̔�r�ɏC���@@���@@SRA   START  
            if(WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getOfferStartDatetime(),
                l_ipoProductChangedParams.getOfferStartDatetime()) != 0)
//			if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferStartDatetime(),
//						   l_ipoProductChangedParams.getOfferStartDatetime()) != 0)
			//2004/11/17 U00433 ���������x�̔�r�ɏC���@@���@@SRA   END                  
            {            
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00580,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
			//2004/11/16 U00423 BB�J�n���O�̓��ɒ������o���Ȃ��s��Ή��@@���@@SRA  START        	 
			//�u���J���i�v����������Ă���΁A��O���X���[����
			if((!l_ipoProductBeforeChangingParams.getPublicPriceIsNull() || !l_ipoProductChangedParams.getPublicPriceIsNull())
			   && l_ipoProductBeforeChangingParams.getPublicPrice() != l_ipoProductChangedParams.getPublicPrice()) 
			{
			    throw new WEB3BusinessLayerException(
					 WEB3ErrorCatalog.BUSINESS_ERROR_00583,
					 this.getClass().getName() + STR_METHOD_NAME); 
			}
			//�u���J���i�i�f�B�X�J�E���g���j�v����������Ă���΁A��O���X���[����
			if((!l_ipoProductBeforeChangingParams.getPublicPriceDiscountRateIsNull()
				 || !l_ipoProductChangedParams.getPublicPriceDiscountRateIsNull())
				 && l_ipoProductBeforeChangingParams.getPublicPriceDiscountRate()
				 != l_ipoProductChangedParams.getPublicPriceDiscountRate())
			{    
				 throw new WEB3BusinessLayerException(
					 WEB3ErrorCatalog.BUSINESS_ERROR_00584,
					 this.getClass().getName() + STR_METHOD_NAME); 
			}
			// 2004/11/16 U00423 BB�J�n���O�̓��ɒ������o���Ȃ��s��Ή��@@���@@SRA  END            
        }
        //�R�|�V�j�@@�����\���Ԃ��A�w���\���I�������i���Аݒ�j�܂ł̍��ڂ̃`�F�b�N<BR>
        if(l_ipoProductBeforeChanging.isOfferEnd())
        { 
            //�u�w���\���I�������i���Аݒ�j�v����������Ă���΁A��O���X���[����
			//2004/11/17 U00433 ���������x�̔�r�ɏC���@@���@@SRA   START 
            if(WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getOfferEndDatetime(),
                l_ipoProductChangedParams.getOfferEndDatetime()) != 0)
//			if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferEndDatetime(),
//							l_ipoProductChangedParams.getOfferEndDatetime()) != 0)
			//2004/11/17 U00433 ���������x�̔�r�ɏC���@@���@@SRA   END
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00581,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        

        
        //�R�|�W�j�@@�����\���Ԃ��A�i�u�b�N�r���f�B���O�J�n�����`�V�K���I�I���j�̍��ڂ̃`�F�b�N   
        // 2004/11/16 U00423 BB�J�n���O�̓��ɒ������o���Ȃ��s��Ή��@@���@@SRA  START         
//		if (!l_ipoProductBeforeChanging.isBookbuildingStart() || l_ipoProductBeforeChanging.isNewLotteryEnd())
//		{ 
//			//�u���J���i�v��null�ɒ�������Ă���΁A��O���X���[����
//			if(l_ipoProductChangedParams.getPublicPriceIsNull() || 
//				Double.isNaN(l_ipoProductChangedParams.getPublicPrice()))
//			{
//				throw new WEB3BusinessLayerException(
//					WEB3ErrorCatalog.BUSINESS_ERROR_00582,
//					this.getClass().getName() + STR_METHOD_NAME);
//			}		
//		}
		// 2004/11/16 U00423 BB�J�n���O�̓��ɒ������o���Ȃ��s��Ή��@@���@@SRA  END	
		
		
        //�R�|�X�j�@@�����\���Ԃ��A�i�u�b�N�r���f�B���O�J�n�����`�w���\���J�n��<BR>���i���Аݒ�j�j�̍��ڂ̃`�F�b�N
		// 2004/11/16 U00423 BB�J�n���O�̓��ɒ������o���Ȃ��s��Ή��@@���@@SRA  START        
//        if (!l_ipoProductBeforeChanging.isBookbuildingStart() 
//            || l_ipoProductBeforeChanging.isOfferStart())
//        {       	 
//            //�u���J���i�v����������Ă���΁A��O���X���[����
//            if((!l_ipoProductBeforeChangingParams.getPublicPriceIsNull() || !l_ipoProductChangedParams.getPublicPriceIsNull())
//            && l_ipoProductBeforeChangingParams.getPublicPrice() != l_ipoProductChangedParams.getPublicPrice()) 
//            {
//                throw new WEB3BusinessLayerException(
//                    WEB3ErrorCatalog.BUSINESS_ERROR_00583,
//                    this.getClass().getName() + STR_METHOD_NAME); 
//            }
//            //�u���J���i�i�f�B�X�J�E���g���j�v����������Ă���΁A��O���X���[����
//            if((!l_ipoProductBeforeChangingParams.getPublicPriceDiscountRateIsNull()
//                || !l_ipoProductChangedParams.getPublicPriceDiscountRateIsNull())
//                && l_ipoProductBeforeChangingParams.getPublicPriceDiscountRate()
//                != l_ipoProductChangedParams.getPublicPriceDiscountRate())
//            {    
//                throw new WEB3BusinessLayerException(
//                    WEB3ErrorCatalog.BUSINESS_ERROR_00584,
//                    this.getClass().getName() + STR_METHOD_NAME); 
//            }
//            
//        }
		// 2004/11/16 U00423 BB�J�n���O�̓��ɒ������o���Ȃ��s��Ή��@@���@@SRA  END                
        log.exiting(STR_METHOD_NAME);
    }
   
    /**
     * (updateIPO�\��)<BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���������jupdateIPO�\���v�Q�ƁB
     * @@param l_lngIpoOrderId - IPO�\���h�c
     * @@param l_datBookbuildingEndDate - (�u�b�N�r���f�B���O�I������)<BR>
     * �u�b�N�r���f�B���O�I�������i������j
     * @@param l_strLastUpdater - �X�V�҃R�[�h
     * @@roseuid 40CE903103D2
     */
    protected void updateIpoOrder(long l_lngIpoOrderId, Date l_datBookbuildingEndDate, String l_strLastUpdater) 
        throws NotInstalledException, NotFoundException
    {   
        String STR_METHOD_NAME = " updateIpoOrder(long, Date, String)";
        log.entering(STR_METHOD_NAME);
        
        //getIPO�\��(long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl =
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_ipoOrderManagerImpl.getOrderUnit(l_lngIpoOrderId);
                
        IpoOrderRow l_ipoOrdertRow = (IpoOrderRow)(l_ipoOrder.getDataSourceObject());
        IpoOrderParams l_ipoOrderParams = new IpoOrderParams(l_ipoOrdertRow);
                
        //get�u�b�N�r���f�B���O�\������( )
        WEB3IpoBookbuildingOrderActionImpl[] l_ipoBookbuildingOrderAction = null; 
        l_ipoBookbuildingOrderAction = new WEB3IpoBookbuildingOrderActionImpl[l_ipoOrder.getOrderActions().length];
        log.debug("l_ipoOrder.getOrderActions().length = " + l_ipoOrder.getOrderActions().length);
        
        for (int i = 0; i < l_ipoOrder.getOrderActions().length; i++)
        {
            l_ipoBookbuildingOrderAction[i] = (WEB3IpoBookbuildingOrderActionImpl)l_ipoOrder.getOrderActions()[i];
            log.debug("l_ipoBookbuildingOrderAction[i].getDataSourceObject(); = " + l_ipoBookbuildingOrderAction[i].getDataSourceObject()); 
        }
        
        
        //(*1) �u�b�N�r���f�B���O�I�������ȍ~�ɐV�K�\�����ꂽ�f�[�^�̏ꍇ
        int l_intLength = l_ipoBookbuildingOrderAction.length;
        log.debug("l_intLength = " + l_intLength);
        // 2004/11/19 U00451 ���������x�̔�r�ɏC���@@���@@SRA�@@START
		if(WEB3DateUtility.compareToMinute(l_ipoOrderParams.getCreatedTimestamp(), l_datBookbuildingEndDate) >= 0)
//        if(WEB3DateUtility.compareToDay(l_ipoOrderParams.getCreatedTimestamp(), l_datBookbuildingEndDate) >= 0)
		// 2004/11/19 U00451 ���������x�̔�r�ɏC���@@���@@SRA�@@END        
        {                     
            log.debug("�u�b�N�r���f�B���O�I�������ȍ~�ɐV�K�\�����ꂽ�f�[�^�̏ꍇ");
            //updateIPO�\��From�u�b�N�r���f�B���O����(IPO�\��, �u�b�N�r���f�B���O�\������, OrderOpenStatusEnum, String)
            this.updateIpoOrderFromBookbuildingAction
                (l_ipoOrder, l_ipoBookbuildingOrderAction[0], OrderOpenStatusEnum.CLOSED, l_strLastUpdater);
        }        
        //(*2) �u�b�N�r���f�B���O�I���������O�ɐV�K�\�����ꂽ�f�[�^�̏ꍇ
        else
        {           
            log.debug("�u�b�N�r���f�B���O�I���������O�ɐV�K�\�����ꂽ�f�[�^�̏ꍇ");
            int l_intTemp = 0;
            //2004/11/24 U00450 IPO_ORDER�e�[�u�����X�V����Ȃ��@@���@@SRA  START
            for (int i = l_intLength - 1; i >= 0 ; i--)
//			for (int i = l_intLength - 1; i == 0 ; i--)
			//2004/11/24 U00450 IPO_ORDER�e�[�u�����X�V����Ȃ��@@���@@SRA  END            
            {
                log.debug("i = " + i);
                Date l_datOrderActionTimestamp = l_ipoBookbuildingOrderAction[i].getOrderActionTimestamp();
				// 2004/11/19 U00451 ���������x�̔�r�ɏC���@@���@@SRA�@@START
				if(WEB3DateUtility.compareToMinute(l_datOrderActionTimestamp,
									l_datBookbuildingEndDate) < 0)                
//                if(WEB3DateUtility.compareToDay(l_datOrderActionTimestamp,
//                    l_datBookbuildingEndDate) < 0)
				// 2004/11/19 U00451 ���������x�̔�r�ɏC���@@���@@SRA�@@END                    
                {
                    l_intTemp = i;
                    log.debug("l_intTemp = " + l_intTemp);
                    break;
                }
            }

            // updateIPO�\��From�u�b�N�r���f�B���O����(IPO�\��, �u�b�N�r���f�B���O�\������, OrderOpenStatusEnum, String)
            IpoBookbuildingOrderActionRow l_ipoBookbuildingOrderActionRow = 
                (IpoBookbuildingOrderActionRow)l_ipoBookbuildingOrderAction[l_intTemp].getDataSourceObject();
            log.debug("l_ipoBookbuildingOrderActionRow.getCreatedTimestamp() = " + l_ipoBookbuildingOrderActionRow.getCreatedTimestamp());
            this.updateIpoOrderFromBookbuildingAction
                (l_ipoOrder,l_ipoBookbuildingOrderAction[l_intTemp],l_ipoBookbuildingOrderActionRow.getOrderOpenStatus(),l_strLastUpdater);
        }
              
        //(*3) �u�b�N�r���f�B���O�\�������e�v�f���Ƃ�LOOP
        //update�u�b�N�r���f�B���O�\������(�u�b�N�r���f�B���O�\������, Date, String)  
        for(int i = 0; i <= l_intLength - 1; i++)
        {
            //�X�V�p�̍s�I�u�W�F�N�g�𐶐�����B
            //((WEB3IpoBookbuildingOrderActionImpl)l_ipoBookbuildingOrderAction[i]).createForUpdateParams();
            log.debug("update�u�b�N�r���f�B���O�\������");
            this.updateBookbuildingOrderAction
                (l_ipoBookbuildingOrderAction[i], l_datBookbuildingEndDate, l_strLastUpdater);   
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (updateIPO�\��From�u�b�N�r���f�B���O����)<BR>
     * �u�b�N�r���f�B���O�\�������̓��e���AIPO�\���s�ɒl���Z�b�g���X�V����B<BR>
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g�擾<BR>
     * �@@�|����,IPO�\��.getDataSourceObject()�ɂāAIPO�\���s�I�u�W�F�N�g���擾����B<BR>
     * �@@�|����,�u�b�N�r���f�B���O�\������.getDataSourceObject()�ɂāA�u�b�N�r���f�B<BR>���O�\�������s�I�u�W�F�N�g���擾����B
     * <BR>
     * <BR>
     * �Q�j�@@�ύX����<BR>
     * �@@�iIPO�\���s.�X�V���� == �u�b�N�r���f�B���O�\�������s.�X�V�����j &&<BR>
     * �@@�iIPO�\���s.�u�b�N�r���f�B���O�L����� == ����.IPO�\���L����ԁj�̏ꍇ<BR>
     * �@@�ύX�Ȃ��Ɣ��肵�A�������I���ireturn�j����B<BR>
     * <BR>
     * �R�j�@@�ύX�l�Z�b�g<BR>
     * �@@IPO�\���s�ɕύX�l���ȉ��̒ʂ�Z�b�g����B<BR>
     * <BR>
     * �@@[�ύX���e]<BR>
     * �@@IPO�\���s.���� = �u�b�N�r���f�B���O�\�������s.����<BR>
     * �@@IPO�\���s.�w�l = �u�b�N�r���f�B���O�\�������s.�w�l<BR>
     * �@@IPO�\���s.�u�b�N�r���f�B���O�\����� = �u�b�N�r���f�B���O�\�������s.�u�b�N�r<BR>���f�B���O�\�����<BR>
     * �@@IPO�\���s.IPO�\���L����� = ����.IPO�\���L�����<BR>
     * �@@IPO�\���s.�v�Z�P�� = �u�b�N�r���f�B���O�\�������s.�v�Z�P��<BR>
     * �@@IPO�\���s.��l�i�����j = �u�b�N�r���f�B���O�\�������s.��l�i�����j<BR>
     * �@@IPO�\���s.�\�������z = �u�b�N�r���f�B���O�\�������s.�\�������z<BR>
     *   IPO�\���s.�V�K�\������ = �u�b�N�r���f�B���O�\�������s.�V�K�\������(20040916_�d�l�ύX)<BR>
     * �@@IPO�\���s.�����ID = �u�b�N�r���f�B���O�\�������s.�����ID <BR>
     * �@@IPO�\���s.�����o�H�敪 = �u�b�N�r���f�B���O�\�������s.�����o�H�敪<BR> 
     * �@@IPO�\���s.�X�V�҃R�[�h = �����̍X�V�҃R�[�h<BR>
     *   IPO�\���s.�X�V���� = ���ݓ���<BR>
     * <BR>
     * �S�j�@@DB�X�V<BR>
     * �@@QueryProcessor.doUpdateQuery() �ɂāAPO�\���s��DB�X�V�iUpdate�j����B<BR>
     * @@param l_ipoOrder - (IPO�\��)<BR>
     * IPO�\���I�u�W�F�N�g
     * @@param l_bookbuildingOrderAction - (�u�b�N�r���f�B���O�\������)<BR>
     * �u�b�N�r���f�B���O�\�������I�u�W�F�N�g
     * @@param l_ipoOrderOpenStatus - IPO�\���L�����
     * @@param l_strLastUpdater - �X�V�҃R�[�h
     * @@roseuid 40CEA0120038
     */
    protected void updateIpoOrderFromBookbuildingAction(WEB3IpoOrderImpl l_ipoOrder, WEB3IpoBookbuildingOrderActionImpl l_bookbuildingOrderAction, 
        OrderOpenStatusEnum l_ipoOrderOpenStatus, String l_strLastUpdater)
    {
        
        String STR_METHOD_NAME = " updateIpoOrderFromBookbuildingAction(WEB3IpoOrderImpl,WEB3IpoBookbuildingOrderAction,OrderOpenStatusEnum,String)";
        log.entering(STR_METHOD_NAME);
        
        //�s�I�u�W�F�N�g�擾
        IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
        IpoBookbuildingOrderActionRow l_ipoBookbuildingOrderActionRow = (IpoBookbuildingOrderActionRow)l_bookbuildingOrderAction.getDataSourceObject();
                
        //�@@�ύX����
		// 2004/11/24 U00457 �c�Č��O�P�U�̎捞�݂ŕύX���胍�W�b�N���폜�@@���@@SRA START        
		// 2004/11/19 U00451 ���������x�̔�r�ɏC���@@���@@SRA�@@START        
//        if (WEB3DateUtility.compareToMinute(l_ipoOrderRow.getLastUpdatedTimestamp(), 
//            l_ipoBookbuildingOrderActionRow.getLastUpdatedTimestamp()) == 0 &&
//            l_ipoOrderOpenStatus.equals(l_ipoOrderRow.getOrderOpenStatus()))
////		if (WEB3DateUtility.compareToDay(l_ipoOrderRow.getLastUpdatedTimestamp(), 
////					l_ipoBookbuildingOrderActionRow.getLastUpdatedTimestamp()) == 0 &&
////					l_ipoOrderOpenStatus.equals(l_ipoOrderRow.getOrderOpenStatus()))
//		// 2004/11/19 U00451 ���������x�̔�r�ɏC���@@���@@SRA�@@END            
//        {
//            log.exiting(STR_METHOD_NAME);
//            log.debug("return");
//            return;        
//        }
//		// 2004/11/24 U00457 �c�Č��O�P�U�̎捞�݂ŕύX���胍�W�b�N���폜�@@���@@SRA END
        IpoOrderParams l_ipoOrderParams = new IpoOrderParams(l_ipoOrderRow);

        //�ύX�l�Z�b�g
        l_ipoOrderParams.setQuantity(l_ipoBookbuildingOrderActionRow.getQuantity());
        l_ipoOrderParams.setLimitPrice(l_ipoBookbuildingOrderActionRow.getLimitPrice()); 
        l_ipoOrderParams.setOrderStatus(l_ipoBookbuildingOrderActionRow.getOrderStatus());
        l_ipoOrderParams.setOrderOpenStatus(l_ipoOrderOpenStatus);
        l_ipoOrderParams.setPrice(l_ipoBookbuildingOrderActionRow.getPrice());
        if(!l_ipoBookbuildingOrderActionRow.getCurrentPriceIsNull())
        {
            l_ipoOrderParams.setCurrentPrice(l_ipoBookbuildingOrderActionRow.getCurrentPrice());
        }
        else
        {
            l_ipoOrderParams.setCurrentPrice(null);
        }
        l_ipoOrderParams.setBookbuildingPrice(l_ipoBookbuildingOrderActionRow.getBookbuildingPrice());
        l_ipoOrderParams.setBookbuildingCreatedTimestamp(l_ipoBookbuildingOrderActionRow.getBookbuildingCreatedTimestamp()); 
        l_ipoOrderParams.setTraderId(l_ipoBookbuildingOrderActionRow.getTraderId());
        l_ipoOrderParams.setOrderRootDiv(l_ipoBookbuildingOrderActionRow.getOrderRootDiv());       
        l_ipoOrderParams.setLastUpdater(l_strLastUpdater);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        l_ipoOrderParams.setLastUpdatedTimestamp(l_finApp.getTradingSystem().getSystemTimestamp());
        
        
        //DB�X�V
        try
        {
            Processors.getDefaultProcessor().doUpdateQuery(l_ipoOrderParams);
        }
        catch (DataFindException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        } 
        catch (DataNetworkException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (updateIPO�\��From�u�b�N�r���f�B���O����)<BR>
     * �u�b�N�r���f�B���O�\��������L���܂��́A�����ɍX�V����B<BR>
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g�擾<BR>
     * �@@����,�u�b�N�r���f�B���O�\������.getDataSourceObject()�ɂ�<BR>
     * �u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�폜�t���O����<BR>
     * �@@�i�u�b�N�r���f�B���O�\�������s.�쐬�����@@< ����.�u�b�N�r���f�B���O�I�������j�̏ꍇ�A<BR>
     * �@@�@@�|�u�b�N�r���f�B���O�\�������s.�폜�t���O == �h�폜�h�iBooleanEnum.True�j�ł���΁A<BR>
     * �@@�@@�@@�ȉ��̒ʂ�A�u�b�N�r���f�B���O�\�������s�ɒl���Z�b�g����DB�ɍX�V�iUpdate�j���s���B<BR>
     * <BR>
     * �@@�@@�@@[�Z�b�g���e]<BR>
     * �@@�@@�@@�폜�t���O = �h�폜�łȂ��h�iBooleanEnum.False�j<BR>
     * �@@�@@�@@�X�V�҃R�[�h = ����.�X�V�҃R�[�h<BR>
     * �@@�@@�@@�X�V���� = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �@@�i�u�b�N�r���f�B���O�\�������s.�쐬�����@@>=  ����.�u�b�N�r���f�B���O�I����<BR>���j�̏ꍇ�A<BR>
     * �@@�@@�|�u�b�N�r���f�B���O�\�������s.�폜�t���O == �h�폜�łȂ��h�i<BR>BooleanEnum.False�j�ł���΁A<BR>
     * �@@�@@�@@�ȉ��̒ʂ�A�u�b�N�r���f�B���O�\�������s�ɒl���Z�b�g����DB�ɍX�V�i<BR>Update�j���s���B<BR>
     * <BR>
     * �@@�@@�@@[�Z�b�g���e]<BR>
     * �@@�@@�@@�폜�t���O = �h�폜�h�iBooleanEnum.True�j<BR>
     * �@@�@@�@@�X�V�҃R�[�h = ����.�X�V�҃R�[�h<BR>
     * �@@�@@�@@�X�V���� = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �@@
     * @@param l_bookbuildingOrderAction - (�u�b�N�r���f�B���O�\������)<BR>
     * �u�b�N�r���f�B���O�\�������I�u�W�F�N�g
     * @@param l_datBookbuildingEndDate - �u�b�N�r���f�B���O�I������
     * 
     * @@param l_strLastUpdater - �X�V�҃R�[�h
     * @@roseuid 40CEADE802A9
     */
    protected void updateBookbuildingOrderAction(WEB3IpoBookbuildingOrderActionImpl l_bookbuildingOrderAction, Date l_datBookbuildingEndDate, String l_strLastUpdater) 
    {
        String STR_METHOD_NAME = " updateBookbuildingOrderAction(WEB3IpoBookbuildingOrderAction,Date,String)";
        log.entering(STR_METHOD_NAME);
        
        //�s�I�u�W�F�N�g�擾
        IpoBookbuildingOrderActionRow l_bookbuildingOrderActionRow = 
            ((IpoBookbuildingOrderActionRow)l_bookbuildingOrderAction.getDataSourceObject());
        
        //�폜�t���O����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        Timestamp l_tsSystemTimestamp = l_finApp.getTradingSystem().getSystemTimestamp();       
            
        IpoBookbuildingOrderActionParams l_ipobookbuildingOrderActionParams 
            = new IpoBookbuildingOrderActionParams(l_bookbuildingOrderActionRow);
                        
        try
        {
            QueryProcessor l_queryProcessor;

            l_queryProcessor = Processors.getDefaultProcessor();
			// 2004/11/19 U00451 ���������x�̔�r�ɏC���@@���@@SRA�@@START             
            if(WEB3DateUtility.compareToMinute(l_bookbuildingOrderActionRow.getCreatedTimestamp(), l_datBookbuildingEndDate) < 0
                 && (BooleanEnum.TRUE).equals(l_bookbuildingOrderActionRow.getDeleteFlag()))
//			if(WEB3DateUtility.compareToDay(l_bookbuildingOrderActionRow.getCreatedTimestamp(), l_datBookbuildingEndDate) < 0
//							 && (BooleanEnum.TRUE).equals(l_bookbuildingOrderActionRow.getDeleteFlag()))
			// 2004/11/19 U00451 ���������x�̔�r�ɏC���@@���@@SRA�@@END                  
            {
                log.debug("�폜�t���O����1");
                l_ipobookbuildingOrderActionParams.setDeleteFlag(BooleanEnum.FALSE);
                l_ipobookbuildingOrderActionParams.setLastUpdater(l_strLastUpdater);
				// 2004/11/19 U00453 �쐬�������X�V���Ă��邽�ߍX�V�������X�V�ɏC���@@���@@SRA�@@START
				l_ipobookbuildingOrderActionParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
//                l_ipobookbuildingOrderActionParams.setCreatedTimestamp(l_tsSystemTimestamp);
				// 2004/11/19 U00453 �쐬�������X�V���Ă��邽�ߍX�V�������X�V�ɏC���@@���@@SRA�@@END
         
                l_queryProcessor.doUpdateQuery(l_ipobookbuildingOrderActionParams);
            }
			// 2004/11/19 U00451 ���������x�̔�r�ɏC���@@���@@SRA�@@START          
            if(WEB3DateUtility.compareToMinute(l_bookbuildingOrderActionRow.getCreatedTimestamp(), l_datBookbuildingEndDate) >= 0
                && (BooleanEnum.FALSE).equals(l_bookbuildingOrderActionRow.getDeleteFlag()))
//			if(WEB3DateUtility.compareToDay(l_bookbuildingOrderActionRow.getCreatedTimestamp(), l_datBookbuildingEndDate) >= 0
//							&& (BooleanEnum.FALSE).equals(l_bookbuildingOrderActionRow.getDeleteFlag()))
			// 2004/11/19 U00451 ���������x�̔�r�ɏC���@@���@@SRA�@@END
            {                   
                log.debug("�폜�t���O����2");
                l_ipobookbuildingOrderActionParams.setDeleteFlag(BooleanEnum.TRUE);
                l_ipobookbuildingOrderActionParams.setLastUpdater(l_strLastUpdater);
				// 2004/11/19 U00453 �쐬�������X�V���Ă��邽�ߍX�V�������X�V�ɏC���@@���@@SRA�@@START
				l_ipobookbuildingOrderActionParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
//				  l_ipobookbuildingOrderActionParams.setCreatedTimestamp(l_tsSystemTimestamp);
				// 2004/11/19 U00453 �쐬�������X�V���Ă��邽�ߍX�V�������X�V�ɏC���@@���@@SRA�@@END
                
                l_queryProcessor.doUpdateQuery(l_ipobookbuildingOrderActionParams);
            }
        }
 
        catch (DataFindException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        }
        catch(DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        }   
        log.exiting(STR_METHOD_NAME);          
    }
    
    /**
     * compareToString<BR>
     * String�ތ^�̔�r<BR>
     * <BR>
     * @@param l_str1 l_str2<BR>
     * @@return boolean
     * @@roseuid 40C945770078
     */    
    private boolean compareToString(String l_str1, String l_str2)
    {
        if (l_str1 == null && l_str2 == null)
        {
            return true;
        }
        else if (l_str1 == null || l_str2 == null)
        {
            return false;
        }
        else
        {
            return l_str1.equals(l_str2);
        }
    }
    
}
@
