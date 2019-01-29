head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I�����T�[�r�XImpl(WEB3AdminIpoLotServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 ���@@��(���u) �V�K�쐬
                   2006/01/16 ���@@��(�k�����u) �d�l�ύX�E���f��115
                   2006/01/18 ���@@�(�k�����u) �d�l�ύX�E���f��116�A117
                   2006/01/31 ����(SCS) �d�l�ύX�E���f��119�A120                   
Revesion History : 2009/02/16 ���u�� (���u) �d�l�ύX���f��No.179
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3LotStatusDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoOrderValidator;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoBookbuildingParams;
import webbroker3.ipo.data.IpoBookbuildingRow;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductDao;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3IPOBookBuldingProcDef;
import webbroker3.ipo.define.WEB3IPOBookBuldingStatusDef;
import webbroker3.ipo.define.WEB3IpoDisplayDivDef;
import webbroker3.ipo.define.WEB3IpoTransactionStatusDef;
import webbroker3.ipo.define.WEB3IpoTransactionStatusTypeDef;
import webbroker3.ipo.define.WEB3LotDivDef;
import webbroker3.ipo.define.WEB3LotResultDivDef;
import webbroker3.ipo.message.WEB3AdminIPOLotCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotInputResponse;
import webbroker3.ipo.message.WEB3IPOLotDetailUnit;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���IPO���I�����T�[�r�XImpl)<BR>
 * �Ǘ���IPO���I�����T�[�r�X�����N���X�B
 * 
 * @@author ���@@��(���u)
 * @@version 1.0
 */
public class WEB3AdminIpoLotServiceImpl implements WEB3AdminIpoLotService
{ 
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotServiceImpl.class);
    
    /**
     * @@roseuid 4112EEE30395
     */
    public WEB3AdminIpoLotServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ���IPO���I���I�������������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO���I��������ظ��Ă̏ꍇ<BR>
     * �@@�|get���I�������͉��()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO���I�����m�Fظ��Ă̏ꍇ<BR>
     * �@@�|get���I�����m�F()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO���I��������ظ��Ă̏ꍇ<BR>
     * �@@�|get���I��������()���R�[������B<BR>
     * <BR>
     * ���T�[�r�X���\�b�h�ɂė�O�����������ꍇ�́A<BR>
     * �@@��O�I�u�W�F�N�g�̒ǉ�������iWEB3BaseException.errorMessage�j��<BR>
     * �@@���X�|���X�f�[�^.errorMessage�ɃZ�b�g����B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40C666B003B6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        if (l_request instanceof WEB3AdminIPOLotInputRequest)
        {
            WEB3AdminIPOLotInputResponse l_response = new WEB3AdminIPOLotInputResponse();
            try
            {
                l_response = this.getLotInput((WEB3AdminIPOLotInputRequest) l_request);
            }
            catch(WEB3BaseException l_ex)
            {
                log.error("get���I�������͉��()�ɗ�O���������܂���", l_ex);
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        else if (l_request instanceof WEB3AdminIPOLotConfirmRequest)
        {
            WEB3AdminIPOLotConfirmResponse l_response = new WEB3AdminIPOLotConfirmResponse();
            try
            {
                l_response = this.getLotConfirm((WEB3AdminIPOLotConfirmRequest) l_request);
            }
            catch(WEB3BaseException l_ex)
            {
                log.error("get���I�����m�F()�ɗ�O���������܂���", l_ex);
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        else if (l_request instanceof WEB3AdminIPOLotCompleteRequest)
        {
            WEB3AdminIPOLotCompleteResponse l_response = new WEB3AdminIPOLotCompleteResponse();
            try
            {
                l_response = this.getLotComplete((WEB3AdminIPOLotCompleteRequest) l_request);
            }
            catch(WEB3BaseException l_ex)
            {
                log.error("get���I��������()�ɗ�O���������܂���", l_ex);
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        else 
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
    }
    
    /**
     * (get���I��������)<BR>
     * IPO�Ǘ���IPO���I�������͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁEIPO���I�����jget���I�������́v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO�����Ǘ����N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3AdminIPOLotInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 40C6677200B8
     */
    protected WEB3AdminIPOLotInputResponse getLotInput(WEB3AdminIPOLotInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLotInput(WEB3AdminIPOLotInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3 getInstanceFrom���O�C�����
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE, true);
        
        //1.5  IPO����(long)
        WEB3IpoProductImpl l_ipoProduct = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3IpoProductManagerImpl l_ipoProductManager = 
            (WEB3IpoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        try
        {
            l_ipoProduct = (WEB3IpoProductImpl) (l_ipoProductManager.getProduct(Long.parseLong(l_request.id))); 
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_nfe.getMessage(), 
                l_nfe);
        }
        
        //1.6.1 validate���I����(IPO����)
        this.validateLotProduct(l_ipoProduct);
        
        //1.6.2 get���I�敪(IPO����)
        String l_strLotDiv = this.getLotDiv(l_ipoProduct);
        
        //1.7 get���������R�[�h
//        String l_strIpoProductCode = l_ipoProduct.getIpoProductCode();
        String l_strIpoProductCode = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProductCode();
        
        //1.8  get������
        String l_strStandardName = l_ipoProduct.getStandardName();
        
        //1.9 get�����\����
        long l_lngAllotQuantity = this.getAllotAbleQuantity(l_request, l_ipoProduct, l_strLotDiv);
        
        //1.10 createResponse( )
        WEB3AdminIPOLotInputResponse l_response = (WEB3AdminIPOLotInputResponse) l_request.createResponse();
        
        //�����R�[�h
        l_response.productCode = l_strIpoProductCode;
        
        //������
        l_response.productName = l_strStandardName;
        
        //���I�敪
        l_response.lotDiv = l_strLotDiv;
        
        //�����\����
        l_response.allotAbleQuantity = String.valueOf(l_lngAllotQuantity);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���I�����m�F)<BR>
     * IPO�Ǘ���IPO���I�����҂� or �m�F��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁEIPO���I�����jget���I�����m�F�v�Q�ƁB<BR>
     *  ========================================================<BR>
     * �V�[�P���X�}IPO�i�Ǘ��ҁEIPO���I�����jget���I�����m�F�v<BR>
     *  1.10.1.���N�G�X�g�I�u�W�F�N�g.���X�R�[�h[] = null �̏ꍇ�A<BR>
     *          ��O���X���[����B<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_01429<BR> 
     *  1.10.3.1.is�d�����I�����̌��ʂ�true�̏ꍇ�A��O���X���[<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02318<BR> 
     *  1.10.5.���I�敪 = "1�F�V�K���I"�̏ꍇ�A�ȉ��̏��������s���A<BR>
     *          ���ʂ�false�̏ꍇ�A��O���X���[�B<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02309<BR> 
     *  1.11.1.1.get�ŐV���I���R�[�h�i�j�̖߂�l.length�@@=�@@0�@@�̏ꍇ�A<BR>
     *          ��O���X���[<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02308<BR> 
     *  ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO�����ڍ׃��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3AdminIPOLotConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40C6677C01F0
     */
    protected WEB3AdminIPOLotConfirmResponse getLotConfirm(
        WEB3AdminIPOLotConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLotConfirm(WEB3AdminIPOLotConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE, true);
        
        //1.4 validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5 get�،���ЃR�[�h
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.6 IPO����(long)
        WEB3IpoProductImpl l_ipoProduct = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3IpoProductManagerImpl l_ipoProductManager = 
            (WEB3IpoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        try
        {
            l_ipoProduct = (WEB3IpoProductImpl) (l_ipoProductManager.getProduct(Long.parseLong(l_request.id))); 
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_nfe.getMessage(), 
                l_nfe);
        }
        
        //1.7.1 validate���I����(IPO����)
        this.validateLotProduct(l_ipoProduct);
        
        //1.7.2 get���I�敪(IPO����)
        String l_strLotDiv = this.getLotDiv(l_ipoProduct);
        
        //1.8 get���������R�[�h
//      String l_strIpoProductCode = l_ipoProduct.getIpoProductCode();
        String l_strIpoProductCode = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProductCode();
        
        //1.9 get������
        String l_strStandardName = l_ipoProduct.getStandardName();
        
        IpoBookbuildingRow[] l_ipoBookbuildingRows = null;
        IpoBookbuildingRow[] l_newIpoBookbuildingRows = null;
        IpoBookbuildingRow[] l_ipoBookbuildings = null;
        String l_strTransactionStateType = null;
        //1.10 ��ʋ敪��"�P�F�o�^"�̏ꍇ�A�ȉ��̏��������s����B
        if (WEB3IpoDisplayDivDef.LOGIN.equals(l_request.displayDiv))
        {
            //1.10.1 ���b�Z�[�W ���N�G�X�g�I�u�W�F�N�g.���X�R�[�h[] = null �̏ꍇ�A��O���X���[����B
            if (l_request.branchCode == null || l_request.branchCode.length == 0)
            {
                log.debug("���X�R�[�h�ꗗ�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01429, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�ꗗ�����w��ł��B");
            }
            
            //1.10.2 ���N�G�X�g�I�u�W�F�N�g.���X�R�[�h[]�̗v�f��Loop�����������Ȃ��A���XIDList�𐶐�����B
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            int l_intBranchCodeLength = l_request.branchCode.length;
            List l_lisBranchIds = new ArrayList();
            for (int i = 0; i < l_intBranchCodeLength; i++)
            {
                //1.10.2.1 get���X(�،���ЃR�[�h : String, ���X�R�[�h : String)
                Branch l_branch = null;
                try
                {
                    l_branch = l_accountManager.getWeb3GenBranch(l_strInstitutionCode, l_request.branchCode[i]);
                }
                catch (NotFoundException l_nfe)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        l_nfe.getMessage(), 
                        l_nfe);
                }
                
                //1.10.2.2 getBranchId()
                long l_lngBranchId = l_branch.getBranchId();
                
                //1.10.2.3 ���XID���X�g��.add����B
                l_lisBranchIds.add(new Long(l_lngBranchId));
            }
            
            // validate�ُ�I��(String, String)
            this.validateAbnormalEnd(l_request.id, l_strLotDiv);
            
            //1.10.3 is�d�����I����(String, String)
            boolean l_blnIsDuplicateLotTransaction = this.isDuplicateLotTransaction(l_request.id, l_strLotDiv);
            
            //1.10.3.1  is�d�����I�����̌��ʂ�true�̏ꍇ�A��O���X���[�B
            if (l_blnIsDuplicateLotTransaction)
            {
                log.debug("���I�������s���́A�Ē��I�ł��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02318, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���I�������s���́A�Ē��I�ł��܂���B");
            }
            
            //1.10.4 validate���I�Ó���(String, String, List)
            this.validateLotValidity(l_request.id, l_strLotDiv, l_lisBranchIds);
            
            //1.10.5 ���I�敪 = "1�F�V�K���I"�̏ꍇ�A�ȉ��̏��������s���A���ʂ�false�̏ꍇ�A��O���X���[�B
            if (WEB3LotDivDef.OPEN_LOTTERY.equals(l_strLotDiv))
            {
                //1.10.5.1 is���I�m��(String, String, List)
                boolean l_blnIsLotConfirm = this.isLotConfirm(l_request.id, l_strLotDiv, l_lisBranchIds);
                
                if (!l_blnIsLotConfirm)
                {
                    log.debug("���I�����͌��ʊm�蒆�A�������͌��ʊm�肪�I�����Ă��܂��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02309, 
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���I�����͌��ʊm�蒆�A�������͌��ʊm�肪�I�����Ă��܂��B");
                }
            }
            
            //1.10.6 validate�������͍���(�Ǘ���IPO���I�����m�F���N�G�X�g, String, IPO����)
            this.validateAllotInputItem(l_request, l_strLotDiv, l_ipoProduct);
                        
            //1.10.7 get���I�V�[�P���X(String, String)
            long l_lngBbSeq = this.getBbSeq(l_request.id, l_strLotDiv);
            
            //1.10.8 get���͍���Row(�Ǘ���IPO���I�����m�F���N�G�X�g, String, long, List)
            l_ipoBookbuildingRows = this.getInputItemRow(
                l_request, l_strLotDiv, l_lngBbSeq, l_lisBranchIds);
            
            //1.10.9 getDefaultProcessor( )
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor(); 
                
                //1.10.10 get���͍���Row(�Ǘ���IPO���I�������̓��N�G�X�g, String, long, List)
                int l_intLength = l_ipoBookbuildingRows.length;
                for (int i = 0; i < l_intLength; i++)
                {
                    //1.10.10.1 doInsertQuery(arg0 : Row)
                    l_processor.doInsertQuery(l_ipoBookbuildingRows[i]);
                }
            }
            catch (DataQueryException l_dqe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dqe.getMessage(), 
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dne.getMessage(), 
                    l_dne);
            }
            l_ipoBookbuildings = l_ipoBookbuildingRows;
            
            l_strTransactionStateType = WEB3IpoTransactionStatusTypeDef.WAITING;
        }
        
        //1.11 ��ʋ敪��"2�F�Q��"�̏ꍇ�A�ȉ��̏��������s����B
        else if (WEB3IpoDisplayDivDef.REFERENCE.equals(l_request.displayDiv))
        {
            //1.11.1 get�ŐV���I���R�[�h(String, String)
            l_newIpoBookbuildingRows = this.getNewLotRecord(l_request.id, l_strLotDiv);
            
            //1.11.1.1 get�ŐV���I���R�[�h�i�j�̖߂�l.length�@@=�@@0�@@�̏ꍇ�A��O���X���[
            if (l_newIpoBookbuildingRows.length == 0)
            {
                log.debug("���I���R�[�h�����݂��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02308, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���I���R�[�h�����݂��܂���B");
            }
            
            //1.11.2.1 get�ŐV���I���R�[�h()�̖߂�l����1���ڂ��擾����
            IpoBookbuildingRow l_ipoBookbuildingRow = l_newIpoBookbuildingRows[0];
            
            //1.11.3 validate���I��(IPO���IRow)
            this.validateLotState(l_ipoBookbuildingRow);
            
            //1.11.4 get�����󋵋敪(IPO���IRow)
            l_strTransactionStateType = this.getTransactionStateType(l_ipoBookbuildingRow);
            
            l_ipoBookbuildings = l_newIpoBookbuildingRows;
        }
        
        //1.12 create���I�����ڍ�(IPO���IRow[])
        WEB3IPOLotDetailUnit[] l_ipoLotDetailUnit = this.createLotDetail(l_ipoBookbuildings);
        
        //1.13 createResponse( )
        WEB3AdminIPOLotConfirmResponse l_response = (WEB3AdminIPOLotConfirmResponse) l_request.createResponse();
        
        //�����R�[�h
        l_response.productCode = l_strIpoProductCode;
        
        //������ 
        l_response.productName = l_strStandardName;
        
        //���I�敪
        l_response.lotDiv = l_strLotDiv;
        
        //�����󋵋敪
        l_response.transactionStateType = l_strTransactionStateType;

        //���I�����ڍ�
        l_response.lotDetail = l_ipoLotDetailUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���I��������)<BR>
     * IPO�Ǘ���IPO���I���������󋵉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁEIPO���I�����jget���I���������v�Q�ƁB<BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�uIPO�i�Ǘ��ҁEIPO���I�����jget���I���������v<BR>
     *  1.10.4.1.is�d�����I�����̌��ʂ�true�̏ꍇ�A��O���X���[<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02318<BR> 
     *  1.10.6.���I�敪 = "1�F�V�K���I"�̏ꍇ�A�ȉ��̏��������s���A<BR>
     *          ���ʂ�false�̏ꍇ�A��O���X���[�B<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02309<BR> 
     *  1.11.1.1.get�����󋵃��R�[�h�̖߂�l.length = 0 �̏ꍇ�A<BR>
     *          ��O���X���[����B<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02323<BR>
     *  ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO�����ڍ׃��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3AdminIPOLotCompleteResponse
     * @@roseuid 40C6677C01F0
     * @@throws WEB3BaseException
     */
    protected WEB3AdminIPOLotCompleteResponse getLotComplete(
        WEB3AdminIPOLotCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLotComplete(WEB3AdminIPOLotCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE, true);
        
        //1.4 validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5 IPO����(long)
        WEB3IpoProductImpl l_ipoProduct = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3IpoProductManagerImpl l_ipoProductManager = 
            (WEB3IpoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        try
        {
            l_ipoProduct = (WEB3IpoProductImpl) (l_ipoProductManager.getProduct(Long.parseLong(l_request.id))); 
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_nfe.getMessage(), 
                l_nfe);
        }
        
        //1.6.1 validate���I����(IPO����)
        this.validateLotProduct(l_ipoProduct);
        
        //1.6.2 get���I�敪(IPO����)
        String l_strLotDiv = this.getLotDiv(l_ipoProduct);
        
        //1.7 get���������R�[�h
//        String l_strIpoProductCode = l_ipoProduct.getIpoProductCode();
        String l_strIpoProductCode = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProductCode();
        
        //1.8 get������
        String l_strStandardName = l_ipoProduct.getStandardName();
        
        IpoBookbuildingRow[] l_ipoBookbuildingRows = null;
        IpoBookbuildingRow[] l_ipoBookbuildingRowCompleteds = null;
        IpoBookbuildingRow[] l_ipoBookbuildings = null;
        String l_strTransactionStateType = null;
        //1.9 ��ʋ敪��"�P�F�o�^"�̏ꍇ�A�ȉ��̏��������s����B
        if (WEB3IpoDisplayDivDef.LOGIN.equals(l_request.displayDiv))
        {
            //1.9.1 validate����p�X���[�h(�p�X���[�h : String)
            l_administrator.validateTradingPassword(l_request.password);
            
            //1.9.2 get���I�m�蕔�XID(String, String, long)
            if (l_request.lotSequence == null)
            {
                l_request.lotSequence = "0";
            }
            List l_lisBranchIds = this.getLotConfirmBranchId(
                l_request.id, l_strLotDiv, Long.parseLong(l_request.lotSequence));
            
            //1.9.3  GtlUtils.getSystemTimestamp();�ŁA���ݓ��t���擾����B
            Timestamp l_tsTimestamp = GtlUtils.getSystemTimestamp();
            
            // validate�ُ�I��(String, String)
            this.validateAbnormalEnd(l_request.id, l_strLotDiv);
            
            //1.9.4 is�d�����I����(String, String)
            boolean l_blnIsDuplicateLotTransaction = this.isDuplicateLotTransaction(l_request.id, l_strLotDiv);
            
            //1.9.4.1 is�d�����I�����̌��ʂ�true�̏ꍇ�A��O���X���[
            if (l_blnIsDuplicateLotTransaction)
            {
                log.debug("���I�������s���́A�Ē��I�ł��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02318, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���I�������s���́A�Ē��I�ł��܂���B");
            }
            
            //1.9.5 validate���I�Ó���(String, String, List)
            this.validateLotValidity(l_request.id, l_strLotDiv, l_lisBranchIds);
            
            //1.9.6 ���I�敪 = "1�F�V�K���I"�̏ꍇ�A�ȉ��̏��������s���A���ʂ�false�̏ꍇ�A��O���X���[�B
            if (WEB3LotDivDef.OPEN_LOTTERY.equals(l_strLotDiv))
            {
                //1.9.6.1 is���I�m��(String, String, List)
                boolean l_blnIsLotConfirm = this.isLotConfirm(l_request.id, l_strLotDiv, l_lisBranchIds);
                
                if (!l_blnIsLotConfirm)
                {
                    log.debug("���I�����͌��ʊm�蒆�A�������͌��ʊm�肪�I�����Ă��܂��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02309, 
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���I�����͌��ʊm�蒆�A�������͌��ʊm�肪�I�����Ă��܂��B");
                }
            }
            
            //1.9.7 save���I�m��(String, String, long, List, Timestamp)
            this.saveLotConfirm(
                l_request.id, l_strLotDiv, Long.parseLong(l_request.lotSequence), l_lisBranchIds, l_tsTimestamp);
            
            //1.9.8 get�����󋵃��R�[�h(String, String, String)
            l_ipoBookbuildingRows = this.getCompleteStateRecord(
                l_request.id, l_strLotDiv, l_request.lotSequence);
            
            //1.9.9 cloneIPO���IRows(IPO���IRow�m�n)
            IpoBookbuildingRow[] l_cloneIpoBookBuildingRows = this.cloneIpoBookBuildingRows(l_ipoBookbuildingRows);
            
            //1.9.10 cloneIPO���IRows�Ŏ擾�����l�̗v�f���A
            //Loop�����������Ȃ��AIPO���IRow�̒l��ҏW����B
            int l_intLength = l_cloneIpoBookBuildingRows.length;
            for (int i = 0; i < l_intLength; i++)
            {
                ((IpoBookbuildingParams) l_cloneIpoBookBuildingRows[i]).setProcess(WEB3IPOBookBuldingProcDef.CONFIRM_INPUT);
                ((IpoBookbuildingParams) l_cloneIpoBookBuildingRows[i]).setStatus(WEB3IPOBookBuldingStatusDef.DEALING);
                ((IpoBookbuildingParams) l_cloneIpoBookBuildingRows[i]).setLastUpdatedTimestamp(l_tsTimestamp);
            }
            l_ipoBookbuildings = l_cloneIpoBookBuildingRows;
            
            l_strTransactionStateType = WEB3IpoTransactionStatusTypeDef.WAITING;
        }
        //1.10 ��ʋ敪��"2�F�Q��"�̏ꍇ�A�ȉ��̏��������s����B
        else if (WEB3IpoDisplayDivDef.REFERENCE.equals(l_request.displayDiv))
        {
            //1.10.1 get�����󋵃��R�[�h(String, String, String)
            l_ipoBookbuildingRowCompleteds = this.getCompleteStateRecord(
                l_request.id, l_strLotDiv, l_request.lotSequence);
            
            //1.10.1.1 get�����󋵃��R�[�h�̖߂�l.length = 0 �̏ꍇ�A��O���X���[����B
            if (l_ipoBookbuildingRowCompleteds.length == 0)
            {
                log.debug("�m�菈���������Ȃ���񂪂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02323, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�m�菈���������Ȃ���񂪂���܂���B");
            }
            
            //1.10.1.2 get�����󋵃��R�[�h�i�j�̖߂�l����A�Ō����1�����擾����B
            IpoBookbuildingRow l_ipoBookbuildingRow = 
                l_ipoBookbuildingRowCompleteds[l_ipoBookbuildingRowCompleteds.length - 1];
            
            //1.10.2 validate�m���(IPO���IRow)
            this.validateConfirmState(l_ipoBookbuildingRow);
            
            //1.10.3 get�����󋵋敪(IPO���IRow)
            l_strTransactionStateType = this.getTransactionStateType(l_ipoBookbuildingRow);
            
            l_ipoBookbuildings = l_ipoBookbuildingRowCompleteds;
        }
        
        //1.11 create���I�����ڍ�(IPO���IRow[])
        WEB3IPOLotDetailUnit[] l_ipoLotDetailUnit = this.createLotDetail(l_ipoBookbuildings);
        
        //1.12 createResponse( )
        WEB3AdminIPOLotCompleteResponse l_response = (WEB3AdminIPOLotCompleteResponse) l_request.createResponse();

        //�����R�[�h
        l_response.productCode = l_strIpoProductCode;
        
        //������ 
        l_response.productName = l_strStandardName;
        
        //���I�敪
        l_response.lotDiv = l_strLotDiv;
        
        //�����󋵋敪
        l_response.transactionStateType = l_strTransactionStateType;

        //���I�����ڍ�
        l_response.lotDetail = l_ipoLotDetailUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
     
    /**
     * (create���I�����ڍ�)
     * ���I�����ڍׂ̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�j�@@ArrayList�I�u�W�F�N�g�̐����B<BR>
     * <BR>
     * �Q�j�@@����.IPO���IRow[]�̗v�f���ALoop�����������Ȃ��B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�Ǘ���IPO���I�����ڍ׃N���X�̃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�Q�|�P�j�Ő��������I�u�W�F�N�g�Ɉȉ��̓��e���Z�b�g����B<BR>
     * �@@�@@�@@�E�@@IPO���I�����ڍ׃I�u�W�F�N�g.���I�V�[�P���X =<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:IPO���IRow[ i ].���I�V�[�P���X<BR>
     * <BR>
     * �@@�@@�@@�E�@@IPO���I�����ڍ׃N���X�I�u�W�F�N�g.���X�R�[�h =<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:IPO���IRow[ i ].���XID���畔�X�R�[�h���擾�B<BR>
     * <BR>
     * �@@�@@�@@�E�@@IPO���I�����ڍ׃N���X�I�u�W�F�N�g.���I�������g���� =<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:IPO���IRow[ i ].���I�������g����<BR>
     * <BR>
     * �@@�@@�@@�E�@@IPO���I�����ڍ׃N���X�I�u�W�F�N�g.����������� =<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:IPO���IRow[ i ].�����������<BR>
     * <BR>
     * �@@�@@�@@�E�@@IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�����ςݐ��� =<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:IPO���IRow[ i ].�����ςݐ���<BR>
     * <BR>
     * �@@�@@�@@�E�@@IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�����Ώیڋq�� =<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:IPO���IRow[ i ].�����Ώیڋq��<BR>
     * <BR>
     * �@@�@@�@@�E�@@IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�����ő�l =<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:IPO���IRow[ i ].�����ő�l<BR>
     * <BR>
     * �@@�@@�@@�E�@@IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�����ŏ��l =<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:IPO���IRow[ i ].�����ŏ��l<BR>
     * <BR>
     * �@@�@@�@@�E�@@IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�⌇�������g���� =<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:IPO���IRow[ i ].�⌇�������g����<BR>
     * <BR>
     * �@@�@@�@@�E�@@IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�⌇����������� =<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:IPO���IRow[ i ].�⌇�����������<BR>
     * <BR>
     * �@@�@@�@@�E�@@IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�⌇�����ςݐ��� =<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:IPO���IRow[ i ].�⌇�����ςݐ���<BR>
     * <BR>
     * �@@�@@�@@�E�@@IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�⌇�����Ώیڋq�� =<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:IPO���IRow[ i ].�⌇�����Ώیڋq��<BR>
     * <BR>
     * �@@�@@�@@�E�@@IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�⌇�����ő�l =<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:IPO���IRow[ i ].�⌇�����ő�l<BR>
     * <BR>
     * �@@�@@�@@�E�@@IPO���I�����ڍ׃I�u�W�F�N�g.�⌇�����ŏ��l =<BR>
     * �@@�@@�@@�@@�@@�@@�@@����:IPO���IRow[ i ].�⌇�����ŏ��l<BR>
     * <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@IPO���I�ڍ׃I�u�W�F�N�g.�������� = IPO���IRow.�X�V����<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�ȉ��̏�����IPO���I�ڍ׃I�u�W�F�N�g.������Ԃ��Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@���P�@@ IPO���IRow.�����󋵁@@=<BR>
     * �@@�@@�@@                 "11�F�m�����" or "12�F�f�[������t" or<BR>
     * �@@�@@�@@                 "13�F�m��J�n"����<BR>
     * �@@�@@�@@                 IPO���IRow.�X�e�[�^�X�@@=�@@"1�F������"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@IPO���I�ڍ׃I�u�W�F�N�g.������� = "1�F�m�菈����"<BR>
     * <BR>
     *              ��2�@@ IPO���IRow.�����󋵁@@=<BR>
     * �@@�@@�@@                 "11�F�m�����" or "12�F�f�[������t" or<BR>
     * �@@�@@�@@                 "13�F�m��J�n"����<BR>
     * �@@�@@�@@                 IPO���IRow.�X�e�[�^�X�@@=�@@"9�F�ُ�I��"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@IPO���I�ڍ׃I�u�W�F�N�g.������� = "9�F�ُ�I��"<BR>
     * <BR>
     *               ��3�@@ IPO���IRow.�����󋵁@@=�@@"14�F�m��I��"�@@����<BR>
     * �@@�@@�@@                 IPO���IRow.�X�e�[�^�X�@@=�@@"0�F����I��"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@IPO���I�ڍ׃I�u�W�F�N�g.������� = "2�F�m�芮��"<BR>
     * <BR>
     *                ���P�A�Q�A�R�ȊO��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@IPO���I�ڍ׃I�u�W�F�N�g.������� = null<BR>
     * <BR>
     * �@@�Q�|�S�j�@@ArrayList�I�u�W�F�N�g.add(IPO���I�����ڍ׃I�u�W�F�N�g)<BR>
     * <BR>
     * �R�j�@@ArrayList�I�u�W�F�N�g��IPO���I�����ڍ�[]�ɕϊ�����B<BR>
     * <BR>
     * �S�j�@@�R�j�ŕϊ�����IPO���I�����ڍ�[]��ԋp����B<BR>
     * @@param  - (IPO���IRow[])<BR>
     * IPO���IRow[]�I�u�W�F�N�g
     * @@return WEB3IPOLotDetailUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3IPOLotDetailUnit[] createLotDetail(
        IpoBookbuildingRow[] l_ipoBookbuildingRows) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createLotDetail(IpoBookbuildingRow[])";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ArrayList�I�u�W�F�N�g�̐����B
        List l_lisIPOLotDetailUnits = new ArrayList();
        
        //�Q�j�@@����.IPO���IRow[]�̗v�f���ALoop�����������Ȃ��B
        int l_intLength = l_ipoBookbuildingRows.length;
        for (int i = 0; i < l_intLength; i++)
        {
            //�Q�|�P�j�@@�Ǘ���IPO���I�����ڍ׃N���X�̃I�u�W�F�N�g�𐶐�����B
            WEB3IPOLotDetailUnit l_iPOLotDetailUnit = new WEB3IPOLotDetailUnit();

            //IPO���I�����ڍ׃I�u�W�F�N�g.���I�V�[�P���X =
            //����:IPO���IRow[ i ].���I�V�[�P���X
            l_iPOLotDetailUnit.lotSequence = String.valueOf(l_ipoBookbuildingRows[i].getBbSeq());
            
            //IPO���I�����ڍ׃N���X�I�u�W�F�N�g.���X�R�[�h =
            //����:IPO���IRow[ i ].���XID���畔�X�R�[�h���擾�B
            String l_strBranchCode = null;
            try
            {
                l_strBranchCode = 
                    BranchDao.findRowByPk(l_ipoBookbuildingRows[i].getBranchId()).getBranchCode();
            }
            catch (DataFindException l_dfe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dfe.getMessage(), 
                    l_dfe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dqe.getMessage(), 
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dne.getMessage(), 
                    l_dne);
            } 
            l_iPOLotDetailUnit.branchCode = l_strBranchCode;
            
            //IPO���I�����ڍ׃N���X�I�u�W�F�N�g.���I�������g���� =
            //����:IPO���IRow[ i ].���I�������g����
            l_iPOLotDetailUnit.lotAllotTotalQuantity = String.valueOf(l_ipoBookbuildingRows[i].getBbQuantityAll());
            
            //IPO���I�����ڍ׃N���X�I�u�W�F�N�g.����������� =
            //����:IPO���IRow[ i ].�����������
            l_iPOLotDetailUnit.allotLimitQuantity = String.valueOf(l_ipoBookbuildingRows[i].getBbQuantityLoop());
            
            //IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�����ςݐ��� =
            //����:IPO���IRow[ i ].�����ςݐ���
            if (l_ipoBookbuildingRows[i].getBbResultQuantitySumIsNull())
            {
                l_iPOLotDetailUnit.allotFinLimitQuantity = null;
            }
            else 
            {
                l_iPOLotDetailUnit.allotFinLimitQuantity = String.valueOf(
                    l_ipoBookbuildingRows[i].getBbResultQuantitySum());
            }
            
            //IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�����Ώیڋq�� =
            //����:IPO���IRow[ i ].�����Ώیڋq��
            if (l_ipoBookbuildingRows[i].getBbResultAccCountIsNull())
            {
                l_iPOLotDetailUnit.allotTargetNumber = null;
            }
            else
            {
                l_iPOLotDetailUnit.allotTargetNumber = String.valueOf(l_ipoBookbuildingRows[i].getBbResultAccCount());
            }
            
            //IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�����ő�l =
            //����:IPO���IRow[ i ].�����ő�l
            if (l_ipoBookbuildingRows[i].getBbResultQuantityMaxIsNull())
            {
                l_iPOLotDetailUnit.allotMax = null;
            }
            else 
            {
                l_iPOLotDetailUnit.allotMax = String.valueOf(l_ipoBookbuildingRows[i].getBbResultQuantityMax());
            }
            
            //IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�����ŏ��l =
            //����:IPO���IRow[ i ].�����ŏ��l
            if (l_ipoBookbuildingRows[i].getBbResultQuantityMinIsNull())
            {
                l_iPOLotDetailUnit.allotMin = null;
            }
            else
            {
                l_iPOLotDetailUnit.allotMin = String.valueOf(l_ipoBookbuildingRows[i].getBbResultQuantityMin());
            }
            
            //IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�⌇�������g���� =
            //����:IPO���IRow[ i ].�⌇�������g����
            if (l_ipoBookbuildingRows[i].getSubBbQuantityAllIsNull())
            {
                l_iPOLotDetailUnit.waitingAllotTotalQuantity = null;
            }
            else
            {
                l_iPOLotDetailUnit.waitingAllotTotalQuantity = 
                    String.valueOf(l_ipoBookbuildingRows[i].getSubBbQuantityAll());
            }
             
            //IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�⌇����������� =
            //����:IPO���IRow[ i ].�⌇�����������
            if (l_ipoBookbuildingRows[i].getSubBbQuantityLoopIsNull())
            {
                l_iPOLotDetailUnit.waitingAllotLimitQuantity = null;
            }
            else
            {
                l_iPOLotDetailUnit.waitingAllotLimitQuantity = 
                    String.valueOf(l_ipoBookbuildingRows[i].getSubBbQuantityLoop());
            }
                
            //IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�⌇�����ςݐ��� =
            //����:IPO���IRow[ i ].�⌇�����ςݐ���
            if (l_ipoBookbuildingRows[i].getSubBbResultQuantitySumIsNull())
            {
                l_iPOLotDetailUnit.waitingAllotFinLimitQuantity = null;
            }
            else
            {
                l_iPOLotDetailUnit.waitingAllotFinLimitQuantity = 
                    String.valueOf(l_ipoBookbuildingRows[i].getSubBbResultQuantitySum());
            }
            
            //IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�⌇�����Ώیڋq�� =
            //����:IPO���IRow[ i ].�⌇�����Ώیڋq��
            if (l_ipoBookbuildingRows[i].getSubBbResultAccCountIsNull())
            {
                l_iPOLotDetailUnit.waitingAllotTargetNumber = null;
            }
            else
            {
                l_iPOLotDetailUnit.waitingAllotTargetNumber = 
                    String.valueOf(l_ipoBookbuildingRows[i].getSubBbResultAccCount());
            }
            
            //IPO���I�����ڍ׃N���X�I�u�W�F�N�g.�⌇�����ő�l =
            //����:IPO���IRow[ i ].�⌇�����ő�l
            if (l_ipoBookbuildingRows[i].getSubBbResultQuantityMaxIsNull())
            {
                l_iPOLotDetailUnit.waitingAllotMax = null;
            }
            else
            {
                l_iPOLotDetailUnit.waitingAllotMax = 
                    String.valueOf(l_ipoBookbuildingRows[i].getSubBbResultQuantityMax()); 
            }
            
            //IPO���I�����ڍ׃I�u�W�F�N�g.�⌇�����ŏ��l =
            //����:IPO���IRow[ i ].�⌇�����ŏ��l
            if (l_ipoBookbuildingRows[i].getSubBbResultQuantityMinIsNull())
            {
                l_iPOLotDetailUnit.waitingAllotMin = null;
            }
            else
            {
                l_iPOLotDetailUnit.waitingAllotMin = 
                    String.valueOf(l_ipoBookbuildingRows[i].getSubBbResultQuantityMin());
            }
            
            //�Q�|�Q�j�@@IPO���I�ڍ׃I�u�W�F�N�g.�������� = IPO���IRow.�X�V����
            l_iPOLotDetailUnit.transactionDate = l_ipoBookbuildingRows[i].getLastUpdatedTimestamp();
            
            //�Q�|�R�j�@@�ȉ��̏�����IPO���I�ڍ׃I�u�W�F�N�g.������Ԃ��Z�b�g����B
            String l_strTransactionState = null;

            //IPO���IRow.�����󋵁@@=
            //"11�F�m�����" or "12�F�f�[������t" or
            // "13�F�m��J�n"����
            // IPO���IRow.�X�e�[�^�X�@@=�@@"1�F������"
            //IPO���I�ڍ׃I�u�W�F�N�g.������� = "1�F�m�菈����"
            String l_strProcess = l_ipoBookbuildingRows[i].getProcess();
            String l_strStatus = l_ipoBookbuildingRows[i].getStatus();
            if ((WEB3IPOBookBuldingProcDef.CONFIRM_INPUT.equals(l_strProcess) || 
                WEB3IPOBookBuldingProcDef.CONFIRM_DAEMON_ACCEPT.equals(l_strProcess) ||
                WEB3IPOBookBuldingProcDef.CONFIRM_BEGIN.equals(l_strProcess)) &&
                (WEB3IPOBookBuldingStatusDef.DEALING.equals(l_strStatus)))
            {
                l_strTransactionState = WEB3IpoTransactionStatusDef.DEALING;
            }
            
            //IPO���IRow.�����󋵁@@=
            //"11�F�m�����" or "12�F�f�[������t" or
            //"13�F�m��J�n"����
            //IPO���IRow.�X�e�[�^�X�@@=�@@"9�F�ُ�I��"
            //IPO���I�ڍ׃I�u�W�F�N�g.������� = "9�F�ُ�I��"
            if ((WEB3IPOBookBuldingProcDef.CONFIRM_INPUT.equals(l_strProcess) || 
                WEB3IPOBookBuldingProcDef.CONFIRM_DAEMON_ACCEPT.equals(l_strProcess) ||
                WEB3IPOBookBuldingProcDef.CONFIRM_BEGIN.equals(l_strProcess)) &&
                (WEB3IPOBookBuldingStatusDef.ABNORMAL_END.equals(l_strStatus)))
            {
                l_strTransactionState = WEB3IpoTransactionStatusDef.ERROR;
            }
            
            //IPO���IRow.�����󋵁@@=�@@"14�F�m��I��"�@@����
            //IPO���IRow.�X�e�[�^�X�@@=�@@"0�F����I��"
            //IPO���I�ڍ׃I�u�W�F�N�g.������� = "2�F�m�芮��"
            if ((WEB3IPOBookBuldingProcDef.CONFIRM_END.equals(l_strProcess)) &&
                (WEB3IPOBookBuldingStatusDef.NORMAL_END.equals(l_strStatus)))
            {
                l_strTransactionState = WEB3IpoTransactionStatusDef.CONFIRM_COMPLETED;
            }
            
            l_iPOLotDetailUnit.transactionState = l_strTransactionState;
            
            //�@@�Q�|�S�j�@@ArrayList�I�u�W�F�N�g.add(IPO���I�����ڍ׃I�u�W�F�N�g)
            l_lisIPOLotDetailUnits.add(l_iPOLotDetailUnit);
        }
       
        //�R�j�@@ArrayList�I�u�W�F�N�g��IPO���I�����ڍ�[]�ɕϊ�����B
        WEB3IPOLotDetailUnit[] l_iPOLotDetailUnits = new WEB3IPOLotDetailUnit[l_intLength];

        l_lisIPOLotDetailUnits.toArray(l_iPOLotDetailUnits);

        log.exiting(STR_METHOD_NAME);
        //�S�j�@@�R�j�ŕϊ�����IPO���I�����ڍ�[]��ԋp����B
        return l_iPOLotDetailUnits;
    }
     
    /**
     * (is�d�����I����)<BR>
     * �d�����I�����𔻒肷�郁�\�b�h�B<BR>
     * <BR>
     * �P�j�@@this.�ŐV���I���R�[�h�i����:����ID , ����:���I�敪�j�ŁA<BR>
     * �@@�@@�@@IPO���I�e�[�u���̃��R�[�h�s���擾����B<BR>
     * <BR>
     * �Q�j�@@if �P�j�̖߂�l:IPO���IRow[].size() = 0 �̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �R�j�@@else�@@�P�j�̖߂�l:IPO���IRow[]�̃T�C�Y���ALoop�������s���B<BR>
     * <BR>
     * �@@�R�|�P�j�@@IPO���IRow[i]����A�����󋵂��擾<BR>
     * <BR>
     * �@@�R�|�Q�j�@@IPO���IRow[i]����A�X�e�[�^�X���擾<BR>
     * <BR>
     * �@@�R�|�R�j�@@if �����󋵂� "04�F���I�I��","14�F�m��I��" �ȊO�ŁA����<BR>
     * �@@�@@�@@�@@�@@�@@�X�e�[�^�X��"1�F������"���R�[�h�����݂���ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@true��ԋp����B<BR>
     * <BR>
     * �@@�R�|�S�j�@@else�@@Loop�����𑱂���B<BR>
     * <BR>
     * �S�j�@@�R�|�R�j�̏�����1�������v������Loop�������I�����ꍇ�Afalse��ԋp<BR>
     * @@param l_strProductId - ����ID
     * @@param l_strLotDiv - (���I�敪)<BR>
     * �V�K/�J�㒊�I�敪�B<BR>
     * <BR>
     * �@@�P�F�V�K���I<BR>
     * �@@�Q�F�J�㒊�I<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isDuplicateLotTransaction(String l_strProductId, String l_strLotDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isDuplicateLotTransaction(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@this.�ŐV���I���R�[�h�i����:����ID , ����:���I�敪�j�ŁA
        // �@@�@@�@@IPO���I�e�[�u���̃��R�[�h�s���擾����B
        IpoBookbuildingRow[] l_ipoBookbuildingRows = this.getNewLotRecord(l_strProductId, l_strLotDiv);
        
        int l_intLength = l_ipoBookbuildingRows.length;
        IpoBookbuildingRow ipoBookbuildingRow = null;
        boolean l_blnDuplicateLotTransaction = false;
        
        //�Q�j�@@if �P�j�̖߂�l:IPO���IRow[].size() = 0 �̏ꍇ�Afalse��ԋp����
        if (l_ipoBookbuildingRows.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //else�@@�P�j�̖߂�l:IPO���IRow[]�̃T�C�Y���ALoop�������s���B
        else
        {
            for (int i = 0; i < l_intLength; i++)
            {
                ipoBookbuildingRow = l_ipoBookbuildingRows[i];
                
                //�R�|�P�j�@@IPO���IRow[i]����A�����󋵂��擾
                String l_strProcess = ipoBookbuildingRow.getProcess();
                //�R�|�Q�j�@@IPO���IRow[i]����A�X�e�[�^�X���擾
                String l_strStatus = ipoBookbuildingRow.getStatus();
                
                //�R�|�R�j�@@if �����󋵂� "04�F���I�I��","14�F�m��I��" �ȊO�ŁA����
                // �@@�@@�@@�@@�@@�@@�X�e�[�^�X��"1�F������"���R�[�h�����݂���ꍇ�A
                // �@@�@@�@@�@@�@@�@@true��ԋp����B
                if ((!WEB3IPOBookBuldingProcDef.LOT_END.equals(l_strProcess) &&
                    !WEB3IPOBookBuldingProcDef.CONFIRM_END.equals(l_strProcess)) && 
                    (WEB3IPOBookBuldingStatusDef.DEALING.equals(l_strStatus)))
                {
                    l_blnDuplicateLotTransaction = true;
                    break;
                }
                else
                {
                    //�R�|�S�jelse�@@Loop�����𑱂���B
                    continue;
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        //�S�j�@@�R�|�R�j�̏�����1�������v������Loop�������I�����ꍇ�Afalse��ԋp
        return l_blnDuplicateLotTransaction;
    }
    
    /**
     * (is���I�m��)<BR>
     * ���I�m�蒆�������́A���I�m�肪�I�����Ă��邩���肷��B<BR>
     * <BR>
     * �P�j�@@����:���XID���X�g�̃T�C�Y���ALoop�����������Ȃ��B<BR>
     * <BR>
     * �@@�P�|�P�j�@@���XID���X�g�I�u�W�F�N�g.get( i )�ŕ��XID���擾����B<BR>
     * �@@�@@�@@�@@�@@�@@<BR>
     * �@@�P�|�Q�j�@@doFindAllQuery()�ȉ��̌��������ŁA<BR>
     * �@@�@@�@@�@@�@@�@@IPO���I�e�[�u���̃��R�[�h����������B<BR>
     * <BR>
     * �@@�@@[�����Ώۃe�[�u��]<BR>
     * �@@�@@IPO���I�e�[�u��<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@����ID = ����:����ID and<BR>
     * �@@�@@���XID = �P�|�P�j�Ŏ擾�������XID and <BR>
     * �@@�@@���I�敪 = "1�F�V�K���I" and<BR>
     * �@@�@@������ = "14�F�m��I��" and<BR>
     * �@@�@@�X�e�[�^�X�@@=�@@"0�F����I��"<BR>
     * <BR>
     * �@@�P�|�R�j�@@�������ʂ��P���ȏ㑶�݂���ꍇ�Afalse��ԋp����B<BR>
     * �@@�@@�@@�@@�@@�@@�������ʂ����݂��Ȃ��ꍇ�́ALoop�����𑱂���B<BR>
     * <BR>
     * �Q�j�@@Loop�������I�����ꍇ�Atrue��ԋp����B<BR>
     * @@param l_strProductId - (����ID)<BR>
     * @@param l_strLotDiv - (���I�敪)<BR>
     * �V�K/�J�㒊�I�敪�B<BR>
     * <BR>
     * �@@�P�F�V�K���I<BR>
     * �@@�Q�F�J�㒊�I<BR>
     * @@param l_lisBranchId - (���XID���X�g)<BR>
     * ���XID���X�g�I�u�W�F�N�g�B
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isLotConfirm(
        String l_strProductId, String l_strLotDiv, List l_lisBranchIdLists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isLotConfirm(String, String, List)";
        log.entering(STR_METHOD_NAME);
        
        //1) ����:���XID���X�g�̃T�C�Y���ALoop�����������Ȃ��B
        long l_lngBranchIdLength = l_lisBranchIdLists.size();
        
        List l_lisRecords = new ArrayList();
        for (int i = 0; i < l_lngBranchIdLength; i++)
        {
            //�P�|�P�j�@@���XID���X�g�I�u�W�F�N�g.get( i )�ŕ��XID���擾����B
            Long l_lngBranchId = (Long) l_lisBranchIdLists.get(i);
            
            //�P�|�Q�j�@@doFindAllQuery()�ȉ��̌��������ŁAIPO���I�e�[�u���̃��R�[�h����������B
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();  

                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" ipo_product_id = ? ");   //����ID
                l_sbWhere.append(" and branch_id = ? ");    //���XID
                l_sbWhere.append(" and bb_div = ? ");       //���I�敪
                l_sbWhere.append(" and process = ? ");      //������
                l_sbWhere.append(" and status = ? ");       //�X�e�[�^�X

                Object[] l_objWhere = {
                    l_strProductId,
                    l_lngBranchId,
                    WEB3LotDivDef.OPEN_LOTTERY,
                    WEB3IPOBookBuldingProcDef.CONFIRM_END,
                    WEB3IPOBookBuldingStatusDef.NORMAL_END};

                l_lisRecords = l_processor.doFindAllQuery(
                    IpoBookbuildingRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objWhere);
            }
            catch (DataFindException l_dfe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dfe.getMessage(), 
                    l_dfe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dqe.getMessage(), 
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dne.getMessage(), 
                    l_dne);
            }
            
            //�P�|�R�j�@@�������ʂ��P���ȏ㑶�݂���ꍇ�Afalse��ԋp����B
            // �@@�@@�@@�@@�@@�@@�������ʂ����݂��Ȃ��ꍇ�́ALoop�����𑱂���B
            if (l_lisRecords.size() > 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        //�Q�j�@@Loop�������I�����ꍇ�Atrue��ԋp����B
        return true;
    }
    
    /**
     * (get���I�敪)<BR>
     * ���ݓ��t�ɂ���āA�V�K/�J�㒊�I�敪��ԋp����B<BR>
     * <BR>
     * �P�j�@@if�@@IPO�����I�u�W�F�N�g.is�u�b�N�r���f�B���O�I���i�j��true ���@@<BR>
     * �@@�@@�@@�@@IPO�����I�u�W�F�N�g.is�w���\���J�n�i���Аݒ�j�i�j��false�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�@@return WEB3LotStatusDef.OPEN_LOTTERY_END�i�V�K���I�j<BR>
     * <BR>
     * �Q�j�@@else if �@@IPO�����I�u�W�F�N�g.is�w���\���J�n�i���Аݒ�j�i�j<BR>
     * �@@�@@�@@�@@��true ����<BR>
     * �@@�@@�@@�@@IPO�����I�u�W�F�N�g.is�w���\���I���i�ژ_�����L�ځj��false�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�@@return WEB3LotStatusDef.ADVANCED_LOTTERY_END�i�J�㒊�I�j<BR>
     * <BR>
     * �R�j�@@else  ��O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02311<BR>
     * <BR>
     * �@@�@@�G���[���b�Z�[�W�u���I�E�������Ԃł͂���܂���B�v<BR>
     * @@param l_ipoProduct - (IPO�����I�u�W�F�N�g)<BR>
     * IPO�����N���X�I�u�W�F�N�g�B
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getLotDiv(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLotDiv(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@if�@@IPO�����I�u�W�F�N�g.is�u�b�N�r���f�B���O�I���i�j��true ���@@
        // �@@�@@�@@�@@ IPO�����I�u�W�F�N�g.is�w���\���J�n�i���Аݒ�j�i�j��false�̏ꍇ�A
        //         return WEB3LotStatusDef.OPEN_LOTTERY_END�i�V�K���I�j;
        if (l_ipoProduct.isBookbuildingEnd() && !l_ipoProduct.isOfferStart())
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3LotStatusDef.OPEN_LOTTERY_END;
        }
        //�Q�j�@@else if �@@IPO�����I�u�W�F�N�g.is�w���\���J�n�i���Аݒ�j�i�j��true ����
        //�@@�@@�@@�@@�@@�@@�@@�@@IPO�����I�u�W�F�N�g.is�w���\���I���i�ژ_�����L�ځj��false�̏ꍇ�A
        //               return WEB3LotStatusDef.ADVANCED_LOTTERY_END�i�J�㒊�I�j
        else if (l_ipoProduct.isOfferStart() && !l_ipoProduct.isOfferEndProspec())
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3LotStatusDef.ADVANCED_LOTTERY_END;
        }
        else 
        {
            log.debug("���I�E�������Ԃł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02311, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "���I�E�������Ԃł͂���܂���B");
        }
    }
    
    /**
     * (get�����\����)<BR>
     * ���I���Ɋ����\�Ȑ��ʂ��擾����B<BR>
     * <BR>
     * �P�j�@@if ���I�敪=�V�K���I�̏ꍇ�AdoFindAllQuery�i�j��<BR>
     * �@@�@@�@@�g�p����IPO�\���e�[�u����<BR>
     * �@@�@@�@@�ȉ��̏����Ń��R�[�h���擾����B<BR>
     * <BR>
     * [�����Ώۃe�[�u��]<BR>
     * IPO�\���e�[�u��<BR>
     * <BR>
     * [��������]<BR>
     * ����ID = ����.����ID and<BR>
     * ���I���� = "1�F���I"<BR>
     * <BR>
     * <BR>
     * �@@�P�|�P�j�@@if�@@�������� = 0���̏ꍇ�A<BR>
     *            IPO�����I�u�W�F�N�g.get���Ў戵���� ()�̒l��ԋp����B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@else if �������� != 0 �̏ꍇ�A�擾���ʂ̗v�f���A<BR>
     * �@@�@@�@@�@@�@@�@@Loop�������s���e���R�[�h�̓��I���ʂ�<BR>
     * �@@�@@�@@�@@�@@�@@�J�E���g���A���I���ʍ��v���v�Z����B<BR>
     * <BR>
     * <BR>
     * �@@�P�|�R�j�@@�����\���� = IPO�����I�u�W�F�N�g.get���Ў戵���� ()<BR>
     * �@@�@@�@@�@@�@@�@@�|���I���ʍ��v���v�Z����B<BR>
     * <BR>
     * �@@�P�|�S�j�@@�P�|�R�j�Ōv�Z���������\���ʂ�ԋp����B<BR>
     * <BR>
     * �Q�j�@@else if ���I�敪=�J�㒊�I�̏ꍇ�A<BR>
     * <BR>
     * �@@�Q�|�P�j�@@if IPO�����I�u�W�F�N�g.is�w���\���I���i���Аݒ�j�i�j��<BR>
     * �@@�@@�@@�@@�@@�@@�߂�l��true�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@<BR>
     * �@@�@@�Q�|�P�|�P�j�@@doFindAllQuery�i�j���g�p����IPO�\���e�[�u���Ɉȉ���<BR>
     * �@@�@@�@@�@@�@@�@@�����Ń��R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@[�����Ώۃe�[�u��]<BR>
     * �@@�@@IPO�\���e�[�u��<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@����ID = ����.����ID and<BR>
     * <BR>
     * �@@�@@(���I���� = "1�F���I"�@@and �w���\���敪 = "1�F�w���\��") <BR>
     * �@@�@@or<BR>
     * �@@�@@(���I���� = "2�F�⌇"�@@and ���I���ʁi�J��j = "1�F���I") <BR>
     * <BR>
     * <BR>
     * �@@�@@�Q�|�P�|�Q�j�@@�擾�������ʂ̗v�f���ALoop�����������Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�Q�|�P�|�Q�D�P�j�@@if�@@���I���� = "1�F���I"�@@and �w���\���敪 =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1�F�w���\��"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������ʃ��R�[�h����w���\�����ʂ��擾���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w���\�����ʍ��v���J�E���g����B<BR>
     * <BR>
     * �@@�@@�@@�Q�|�P�|�Q�D�Q�j�@@else if ���I���� = "2�F�⌇"�@@and<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���I���ʁi�J��j = "1�F���I"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������ʃ��R�[�h���瓖�I���ʂ��擾���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���I���ʍ��v���J�E���g����B<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�R�j�@@IPO�����I�u�W�F�N�g.get���Ў戵���� ()�@@�|<BR>
     * �@@�@@�@@�@@�@@�@@�i�w���\�����ʍ��v + ���I���ʍ��v�j�@@��ԋp����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@if IPO�����I�u�W�F�N�g.is�w���\���I���i���Аݒ�j�i�j��<BR>
     * �@@�@@�@@�@@�@@�@@�߂�l��false�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@<BR>
     * �@@�@@�Q�|�Q�|�P�j�@@doFindAllQuery�i�j���g�p����IPO�\���e�[�u���Ɉȉ���<BR>
     * �@@�@@�@@�@@�@@�@@�����Ń��R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@[�����Ώۃe�[�u��]<BR>
     * �@@�@@IPO�\���e�[�u��<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@����ID = ����.����ID and<BR>
     * <BR>
     * �@@�@@(���I���� = "1�F���I"�@@and �w���\���敪 = "1�F�w���\��") <BR>
     * �@@�@@or<BR>
     * �@@�@@(���I���� = "2�F�⌇"�@@and ���I���ʁi�J��j = "1�F���I") <BR>
     * �@@�@@or<BR>
     * �@@�@@(���I���� = "1�F���I"�@@and �w���\���敪 = "0�F�f�t�H���g") <BR>
     * <BR>
     * <BR>
     * �@@�Q�|�Q�|�Q�j�@@�擾�������ʂ̗v�f���ALoop�����������Ȃ��B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�D�P�j�@@if�@@���I���� = "1�F���I"�@@and �w���\���敪 =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1�F�w���\��"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������ʃ��R�[�h����w���\�����ʂ��擾���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w���\�����ʍ��v���J�E���g����B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�D�Q�j�@@else if ���I���� = "2�F�⌇"�@@and<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���I���ʁi�J��j = "1�F���I"�@@�܂���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���I���� = "1�F���I"�@@and<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w���\���敪 = "0�F�f�t�H���g"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������ʃ��R�[�h���瓖�I���ʂ��擾���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���I���ʍ��v���J�E���g����B<BR>
     * <BR>
     * �@@�Q�|�Q�|�R�j�@@IPO�����I�u�W�F�N�g.get���Ў戵���� ()�@@�|<BR>
     * �@@�@@�@@�@@�@@�@@�i�w���\�����ʍ��v + ���I���ʍ��v�j�@@��ԋp����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@param l_ipoProduct - (IPO�����I�u�W�F�N�g)
     * IPO�����N���X�I�u�W�F�N�g�B
     * @@param l_strLotDiv - ���I�敪
     * �V�K/�J�㒊�I�敪�B<BR>
     * <BR>
     * �@@�P�F�V�K���I<BR>
     * �@@�Q�F�J�㒊�I<BR>
     * @@return long
     * @@throws WEB3BaseException
     */
    protected long getAllotAbleQuantity(
        WEB3GenRequest l_request,
        WEB3IpoProductImpl l_ipoProduct,
        String l_strLotDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAllotAbleQuantity(WEB3GenRequest, WEB3IpoProductImpl, String)";
        log.entering(STR_METHOD_NAME);
        
        long l_lngAllotAbleQuantity = 0;
        QueryProcessor l_processor = null;
        //�P�j�@@if ���I�敪=�V�K���I�̏ꍇ�AdoFindAllQuery�i�j��
        //       �g�p����IPO�\���e�[�u����
        // �@@�@@�@@�ȉ��̏����Ń��R�[�h���擾����B
        try
        {
            if (WEB3LotDivDef.OPEN_LOTTERY.equals(l_strLotDiv))
            {
                List l_lisRecords = new ArrayList();
                
                l_processor = Processors.getDefaultProcessor();  

                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" ipo_product_id = ? ");   //����ID
                l_sbWhere.append(" and lot_result = ? ");   //���I����

                Object[] l_objWhere = {
                    new Long(l_ipoProduct.getProductId()),
                    WEB3LotResultDivDef.ELECTION};

                l_lisRecords = l_processor.doFindAllQuery(
                    IpoOrderRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objWhere);
                     
                //�P�|�P�j�@@if�@@�������� = 0���̏ꍇ�A
                //         IPO�����I�u�W�F�N�g.get���Ў戵���� ()�̒l��ԋp����B
                int l_intLength = l_lisRecords.size();
                if (l_lisRecords == null || l_intLength == 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return (long) l_ipoProduct.getDealingQuantity();
                }
                //�P�|�Q�j�@@else if �������� != 0 �̏ꍇ�A�擾���ʂ̗v�f���A
                // �@@�@@�@@�@@�@@�@@Loop�������s���e���R�[�h�̓��I���ʂ�
                // �@@�@@�@@�@@�@@�@@�J�E���g���A���I���ʍ��v���v�Z����B
                else if (l_intLength != 0)
                {
                    long l_lngElectedQuantity = 0;
                    for (int i = 0; i < l_intLength; i ++)
                    {
                        l_lngElectedQuantity +=  ((IpoOrderRow) l_lisRecords.get(i)).getElectedQuantity();  
                    }
                    // �����\���� = IPO�����I�u�W�F�N�g.get���Ў戵���� ()
                    //�|���I���ʍ��v���v�Z����B
                    l_lngAllotAbleQuantity = (long) l_ipoProduct.getDealingQuantity() - l_lngElectedQuantity;
                    
                    //�P�|�S�j�@@�P�|�R�j�Ōv�Z���������\���ʂ�ԋp����
                    log.exiting(STR_METHOD_NAME);
                    return l_lngAllotAbleQuantity;  
                }   
            }
            else if (WEB3LotDivDef.ADVANCED_LOTTERY.equals(l_strLotDiv))
            {   
                if (l_ipoProduct.isOfferEnd())
                {
                    //doFindAllQuery�i�j���g�p����IPO�\���e�[�u���Ɉȉ���
                    //�����Ń��R�[�h���擾����B
                    List l_lisRecords = new ArrayList();

                    l_processor = Processors.getDefaultProcessor();  

                    StringBuffer l_sbWhere = new StringBuffer();
                    l_sbWhere.append(" ipo_product_id = ? ");   //����ID
                    l_sbWhere.append(" and ((lot_result = ? and offering_div = ?)");
                    l_sbWhere.append(" or (lot_result = ? and lot_result_retry = ?))");
                    Object[] l_objWhere = {
                        new Long(l_ipoProduct.getProductId()),
                        WEB3LotResultDivDef.ELECTION,
                        WEB3OfferingDivDef.PURCHASE_APPLICATION,
                        WEB3LotResultDivDef.SUPPLEMENT,
                        WEB3LotResultRetryDef.ELECTION};

                    l_lisRecords = l_processor.doFindAllQuery(
                        IpoOrderRow.TYPE,
                        l_sbWhere.toString(),
                        null,
                        l_objWhere);

                    long l_lngElectedQuantity = 0;
                    long l_lngApplicationQuantity = 0;
                    int l_intLength = l_lisRecords.size();
                    for (int i = 0; i < l_intLength; i ++)
                    {
                        IpoOrderRow l_ipoOrderRow = (IpoOrderRow) l_lisRecords.get(i);
                        if ((WEB3LotResultDivDef.ELECTION.equals(l_ipoOrderRow.getLotResult())) && 
                            WEB3OfferingDivDef.PURCHASE_APPLICATION.equals(l_ipoOrderRow.getOfferingDiv()))
                        {
                            //�Q�|�P�|�Q�D�P�j�@@if�@@���I���� = "1�F���I"�@@and �w���\���敪 =
                            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1�F�w���\��"�̏ꍇ�A
                            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������ʃ��R�[�h����w���\�����ʂ��擾���A
                            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w���\�����ʍ��v���J�E���g����B
                            l_lngApplicationQuantity +=  ((IpoOrderRow) l_lisRecords.get(i)).getApplicationQuantity(); 
                        }
                        //�Q�|�P�|�Q�D�Q�j�@@else if ���I���� = "2�F�⌇"�@@and
                        // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���I���ʁi�J��j = "1�F���I"�̏ꍇ�A
                        // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������ʃ��R�[�h���瓖�I���ʂ��擾���A
                        // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���I���ʍ��v���J�E���g����B
                        else if (WEB3LotResultDivDef.SUPPLEMENT.equals(l_ipoOrderRow.getLotResult()) &&
                            WEB3LotResultRetryDef.ELECTION.equals(l_ipoOrderRow.getLotResultRetry()))
                        {
                            l_lngElectedQuantity += ((IpoOrderRow) l_lisRecords.get(i)).getElectedQuantity(); 
                        }
                        
                    }
                    //�Q�|�P�|�R�j�@@IPO�����I�u�W�F�N�g.get���Ў戵���� ()
                    //�@@�@@�@@�@@�@@�@@�i�w���\�����ʍ��v + ���I���ʍ��v�j�@@��ԋp����B
                    l_lngAllotAbleQuantity = 
                        (long) l_ipoProduct.getDealingQuantity() - (l_lngApplicationQuantity + l_lngElectedQuantity);
                }
                //�Q�|�Q�j�@@if IPO�����I�u�W�F�N�g.is�w���\���I���i���Аݒ�j�i�j
                //�@@�@@�@@�@@�@@�@@�߂�l��false�̏ꍇ
                else
                {
                    //doFindAllQuery�i�j���g�p����IPO�\���e�[�u���Ɉȉ���
                    //�����Ń��R�[�h���擾����B
                    List l_lisRecords = new ArrayList();
                   
                    l_processor = Processors.getDefaultProcessor();  

                    StringBuffer l_sbWhere = new StringBuffer();
                    l_sbWhere.append(" ipo_product_id = ? ");   //����ID
                    l_sbWhere.append(" and ((lot_result = ? and offering_div = ?)");
                    l_sbWhere.append(" or (lot_result = ? and lot_result_retry = ?)");
                    l_sbWhere.append(" or (lot_result = ? and offering_div = ?))");
                    
                    Object[] l_objWhere = {
                        new Long(l_ipoProduct.getProductId()),
                        WEB3LotResultDivDef.ELECTION,
                        WEB3OfferingDivDef.PURCHASE_APPLICATION,
                        WEB3LotResultDivDef.SUPPLEMENT,
                        WEB3LotResultRetryDef.ELECTION,
                        WEB3LotResultDivDef.ELECTION,
                        WEB3OfferingDivDef.DEFAULT};

                    l_lisRecords = l_processor.doFindAllQuery(
                        IpoOrderRow.TYPE,
                        l_sbWhere.toString(),
                        null,
                        l_objWhere);
                   
                    long l_lngElectedQuantity = 0;
                    long l_lngApplicationQuantity = 0;
                    int l_intLength = l_lisRecords.size();
                    
                    for (int i = 0; i < l_intLength; i ++)
                    {
                        IpoOrderRow l_ipoOrderRow = (IpoOrderRow) l_lisRecords.get(i);
                        if ((WEB3LotResultDivDef.ELECTION.equals(l_ipoOrderRow.getLotResult())) && 
                            WEB3OfferingDivDef.PURCHASE_APPLICATION.equals(l_ipoOrderRow.getOfferingDiv()))
                        {
                            //�Q�|�Q�|�Q�D�P�j�@@if�@@���I���� = "1�F���I"�@@and �w���\���敪 =
                            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1�F�w���\��"�̏ꍇ�A
                            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������ʃ��R�[�h����w���\�����ʂ��擾���A
                            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w���\�����ʍ��v���J�E���g����B
                            l_lngApplicationQuantity +=  ((IpoOrderRow) l_lisRecords.get(i)).getApplicationQuantity(); 
                        }
                        
                        else if ((WEB3LotResultDivDef.SUPPLEMENT.equals(l_ipoOrderRow.getLotResult()) &&
                            WEB3LotResultRetryDef.ELECTION.equals(l_ipoOrderRow.getLotResultRetry())) ||
                            (WEB3LotResultDivDef.ELECTION.equals(l_ipoOrderRow.getLotResult()) && 
                             WEB3OfferingDivDef.DEFAULT.equals(l_ipoOrderRow.getOfferingDiv())))
                        {
                            //�Q�|�Q�|�Q�D�Q�j�@@else if ���I���� = "2�F�⌇"�@@and
                            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���I���ʁi�J��j = "1�F���I"�@@�܂���
                            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���I���� = "1�F���I"�@@and
                            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w���\���敪 = "0�F�f�t�H���g"�̏ꍇ�A
                            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������ʃ��R�[�h���瓖�I���ʂ��擾���A
                            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���I���ʍ��v���J�E���g����B
                            l_lngElectedQuantity += ((IpoOrderRow) l_lisRecords.get(i)).getElectedQuantity();   
                        }
                    }
                    //�Q�|�Q�|�R�j�@@IPO�����I�u�W�F�N�g.get���Ў戵���� ()�@@�|
                    // �@@�@@�@@�@@�@@�@@�i�w���\�����ʍ��v + ���I���ʍ��v�j�@@��ԋp����B
                    l_lngAllotAbleQuantity = 
                        (long) l_ipoProduct.getDealingQuantity() - (l_lngApplicationQuantity + l_lngElectedQuantity);  
                }
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dfe.getMessage(), 
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dqe.getMessage(), 
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dne.getMessage(), 
                l_dne);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lngAllotAbleQuantity;
    }
    
    /**
     * (get���I�V�[�P���X)<BR>
     * IPO���I�e�[�u���̃��R�[�h�ŁA���I�V�[�P���X��Max�l�̃��R�[�h��<BR>
     * ���I�V�[�P���X+1��ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get�ŐV���I���R�[�h�i<BR>
     * �@@�@@���N�G�X�g�I�u�W�F�N�g.����ID , ����:���I�敪�j�ŁA<BR>
     * �@@�@@���R�[�h����������B<BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾�����������ʂ�0���̏ꍇ�A<BR>
     * <BR>
     * �@@�@@���I�V�[�P���X = 1 ��ԋp����B<BR>
     * <BR>
     * �R�j�@@�P�j�Ŏ擾�����������ʂ��P���ȏ�̏ꍇ�A�@@<BR>
     * �@@�R�|�P�j�@@�����������R�[�h��1���ڂ����o���A���I�V�[�P���X���擾����B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�擾�������I�V�[�P���X + 1 ��ԋp����B<BR>
     * @@param l_strProductId - ����ID
     * @@param l_strLotDiv - (���I�敪)
     * �V�K/�J�㒊�I�敪�B<BR>
     * <BR>
     * �@@�P�F�V�K���I<BR>
     * �@@�Q�F�J�㒊�I<BR>
     * @@return long
     * @@throws WEB3BaseException
     */
    protected long getBbSeq(String l_strProductId, String l_strLotDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBbSeq(String, String)";
        log.entering(STR_METHOD_NAME);
        
        long l_lngBbseq = 0;
        
        //�P�j�@@this.get�ŐV���I���R�[�h�i
        // �@@�@@���N�G�X�g�I�u�W�F�N�g.����ID , ����:���I�敪�j�ŁA���R�[�h����������B
        IpoBookbuildingRow[] l_ipoBookbuildingRows = this.getNewLotRecord(l_strProductId, l_strLotDiv);
        
        //�Q�j�@@�P�j�Ŏ擾�����������ʂ�0���̏ꍇ�A���I�V�[�P���X = 1 ��ԋp����B
        if (l_ipoBookbuildingRows.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return 1;
        }
        
        //�R�j�@@�P�j�Ŏ擾�����������ʂ��P���ȏ�̏ꍇ�A
        //�R�|�P�j�@@�����������R�[�h��1���ڂ����o���A���I�V�[�P���X���擾����B
        //�R�|�Q�j�@@�擾�������I�V�[�P���X + 1 ��ԋp����B
        l_lngBbseq = l_ipoBookbuildingRows[0].getBbSeq() + 1;

        log.exiting(STR_METHOD_NAME);
        return l_lngBbseq;
    }
    
    /**
     * (get�����󋵋敪)<BR>
     * �����󋵋敪��ԋp����B<BR>
     * <BR>
     * �@@�P�j�@@����:IPO���IRow����X�e�[�^�X���擾����B<BR>
     * <BR>
     * �@@�Q�j�@@if �X�e�[�^�X = "1�F������"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@return "1�F�҂���"<BR>
     * <BR>
     * �@@�R�j�@@else if �X�e�[�^�X = "9�F�ُ�I��"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@return "9�F�ُ�I��"<BR>
     * <BR>
     * �@@�S�j�@@else if �X�e�[�^�X = "0�F����I��"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@return "2�F������"<BR>
     * @@param  - (IPO���IRow)<BR>
     * IPO���IRow�I�u�W�F�N�g
     * @@return String
     */
    protected String getTransactionStateType(IpoBookbuildingRow l_ipoBookbuildingRow)
    {
        final String STR_METHOD_NAME = " getTransactionStateType(IpoBookbuildingRow)";
        log.entering(STR_METHOD_NAME);
        
        //�@@�P�j�@@����:IPO���IRow����X�e�[�^�X���擾����B
        String l_strStatus = l_ipoBookbuildingRow.getStatus();
        
        //�Q�j�@@if �X�e�[�^�X = "1�F������"�̏ꍇ
        if (WEB3IPOBookBuldingStatusDef.DEALING.equals(l_strStatus))
        {
            //return 1�F�҂���
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoTransactionStatusTypeDef.WAITING;
        }
        //�R�j�@@else if �X�e�[�^�X = 9�F�ُ�I��"�̏ꍇ
        else if (WEB3IPOBookBuldingStatusDef.ABNORMAL_END.equals(l_strStatus))
        {
            //return 9�F�ُ�I��
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoTransactionStatusTypeDef.ERROR;
        }
        //�@@�S�j�@@else if �X�e�[�^�X = "0�F����I��"�̏ꍇ
        else if (WEB3IPOBookBuldingStatusDef.NORMAL_END.equals(l_strStatus))
        {
            //return 2�F������
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoTransactionStatusTypeDef.COMPLETED;
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get���I�m�蕔�XID)<BR>
     * ���I�m��Ώۂ̕��XID���X�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@ArrayList�I�u�W�F�N�g�̐����B<BR>
     * <BR>
     * �Q�j�@@�ȉ��̏����ŁAdoFindAllQuery�i�j���g�p����IPO���I�e�[�u������������B<BR>
     * [�����Ώۃe�[�u��]<BR>
     * IPO���I�e�[�u��<BR>
     * <BR>
     * [��������]<BR>
     * ����ID = ����:����ID<BR>
     * ���I�敪 = ����:���I�敪<BR>
     * ���I�V�[�P���X = ����:���I�V�[�P���X<BR>
     * <BR>
     * �R�j�@@�������� != 0 �̏ꍇ�A�擾�������R�[�h�̗v�f��Loop�����������Ȃ��B<BR>
     * <BR>
     * �@@�R�|�P�j�@@���R�[�h���畔�XID���擾����B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@ArrayList�I�u�W�F�N�g.add(�擾�������XID)<BR>
     * <BR>
     * �S�j�@@�������ʂ�0���̏ꍇ�A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02340<BR>
     * <BR>
     * �T�j�@@ArrayList�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_strProductId - ����ID
     * @@param l_strLotDiv - (���I�敪)
     * �V�K/�J�㒊�I�敪�B<BR>
     * <BR>
     * �@@�P�F�V�K���I<BR>
     * �@@�Q�F�J�㒊�I<BR>
     * @@param l_lngBbSeq - ���I�V�[�P���X
     * @@return List
     * @@throws WEB3BaseException
     */
    protected List getLotConfirmBranchId(
        String l_strProductId, 
        String l_strLotDiv,
        long l_lngBbSeq) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLotConfirmBranchId(String, String, long)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ArrayList�I�u�W�F�N�g�̐����B
        List l_lisBranchIds = new ArrayList();
        List l_lisRecords = new ArrayList();
        try
        {
            //�Q�j�@@�ȉ��̏����ŁAdoFindAllQuery�i�j���g�p����IPO���I�e�[�u������������B
            QueryProcessor l_processor = Processors.getDefaultProcessor();  

            StringBuffer l_sbWhere = new StringBuffer();
            //����ID
            l_sbWhere.append(" ipo_product_id = ? ");   
            //���I�敪 
            l_sbWhere.append(" and bb_div = ? ");       
            //���I�V�[�P���X
            l_sbWhere.append(" and bb_seq = ? ");

            Object[] l_objWhere = {
                l_strProductId,
                l_strLotDiv,
                new Long(l_lngBbSeq)};

            l_lisRecords = l_processor.doFindAllQuery(
                IpoBookbuildingRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dfe.getMessage(), 
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dqe.getMessage(), 
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dne.getMessage(), 
                l_dne);
        }
        
        //�R�j�@@�������� != 0 �̏ꍇ�A�擾�������R�[�h�̗v�f��Loop�����������Ȃ��B
        int l_intLength = l_lisRecords.size();
        
        // �������ʂ�0���̏ꍇ�A��O���X���[����B
        if (l_lisRecords == null || l_intLength == 0)
        {
            log.debug("���I�m��Ώۂ̕��XID���X�g���擾�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02340,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "���I�m��Ώۂ̕��XID���X�g���擾�ł��܂���B");
        }
        
        for (int i = 0; i < l_intLength; i++)
        {
            IpoBookbuildingRow l_ipoBookbuildingRow = (IpoBookbuildingRow) l_lisRecords.get(i);
            //  �R�|�P�j�@@���R�[�h���畔�XID���擾����B
            //�@@�R�|�Q�j�@@ArrayList�I�u�W�F�N�g.add(�擾�������XID)
            l_lisBranchIds.add(new Long(l_ipoBookbuildingRow.getBranchId()));
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisBranchIds;
    }
    
    /**
     * (get���͍���Row)<BR>
     * IPO���I�e�[�u���ɍX�V�����iInsert�j�������Ȃ��ׂɁA�Ώە��X�̗v�f��<BR>
     * IPO���IRow�I�u�W�F�N�g�Ő������AIPO���IRow[]��ԋp����B<BR>
     * <BR>
     * �P�j�@@ArrayList�I�u�W�F�N�g�̐���<BR>
     * <BR>
     * �Q�j�@@����:���XID���X�g�̗v�f���ALoop�����������Ȃ��B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@���XID���X�g.get�i i �j�ŁA���XID���擾����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@IPO���Iparams�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�ȉ��̃p�����[�^���P�|�P�j��<BR>
     * �@@�@@��������params�I�u�W�F�N�g�ɃZ�b�g����B<BR>
     * �@@�@@�Z�b�g������e�́A <BR>
     * <BR>
     * �@@�@@DB�X�V�d�l <BR>
     * �@@�@@�u���I����_IPO���I�e�[�u���d�l.xls�v�Q�ƁB <BR>
     * <BR>
     * �@@�Q�|�S�j�@@ArrayList�I�u�W�F�N�g.add(IPO���Iparams�I�u�W�F�N�g)<BR>
     * <BR>
     * �R�j�@@ArrayList�I�u�W�F�N�g��IPO���IRow[]�ɕϊ�����B<BR>
     * <BR>
     * �S�j�@@�R�j�ŕϊ�����IPO���IRow��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)
     * �Ǘ���IPO���I�����m�F���N�G�X�g�I�u�W�F�N�g�B
     * @@param l_strLotDiv - (���I�敪)
     * �V�K/�J�㒊�I�敪�B<BR>
     * <BR>
     * �@@�P�F�V�K���I<BR>
     * �@@�Q�F�J�㒊�I<BR>
     * @@param l_lngBbSeq - ���I�V�[�P���X
     * @@param l_lisBranchIdLists - (���XID���X�g)
     * ���XID���X�g�I�u�W�F�N�g
     * @@return - IPO���IRow[]
     * @@throws WEB3BaseException
     */
    protected IpoBookbuildingRow[] getInputItemRow(
        WEB3AdminIPOLotConfirmRequest l_request,
        String l_strLotDiv,
        long l_lngBbSeq,
        List l_lisBranchIdLists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputItemRow(WEB3AdminIPOLotInputRequest, String, long, List)";
        log.entering(STR_METHOD_NAME); 
        
        //�P�j�@@ArrayList�I�u�W�F�N�g�̐���
        List l_lisRecords = new ArrayList();
        
        int l_intLength = l_lisBranchIdLists.size();
        
        l_lngBbSeq = this.getBbSeq(l_request.id, l_strLotDiv);
        
        //�Q�j�@@����:���XID���X�g�̗v�f���ALoop�����������Ȃ��B
        for (int i = 0; i < l_intLength; i++)
        {
            //�Q�|�P�j�@@���XID���X�g.get�i i �j�ŁA���XID���擾����B
            long l_lngBranchId = Long.parseLong(String.valueOf(l_lisBranchIdLists.get(i)));
            
            //�Q�|�Q�j�@@IPO���Iparams�I�u�W�F�N�g�𐶐�����B
            IpoBookbuildingParams l_ipoBookbuildingParams = new IpoBookbuildingParams();
            
            //IPO�����h�c
            l_ipoBookbuildingParams.setIpoProductId(Long.parseLong(l_request.id));
            
            //���I�敪
            l_ipoBookbuildingParams.setBbDiv(l_strLotDiv);
            
            //���I�V�[�P���X
            l_ipoBookbuildingParams.setBbSeq(l_lngBbSeq);
            
            //���X�h�c
            l_ipoBookbuildingParams.setBranchId(l_lngBranchId);
            
            String l_strProductCode = null;
            try
            {
                l_strProductCode = 
                    IpoProductDao.findRowByPk(Long.parseLong(l_request.id)).getProductCode();
            }
            catch (DataFindException l_dfe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dfe.getMessage(), 
                    l_dfe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dqe.getMessage(), 
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dne.getMessage(), 
                    l_dne);
            }
            
            //�����R�[�h
            l_ipoBookbuildingParams.setProductCode(l_strProductCode);
            
            //���I�������g���� 
            if (l_request.allotTotalQuantity == null)
            {
                l_request.allotTotalQuantity = "0";
            }
            l_ipoBookbuildingParams.setBbQuantityAll(Long.parseLong(l_request.allotTotalQuantity));
            
            //����������ʁi�P���[�v������j
            if (l_request.allotLimitQuantity == null)
            {
                log.debug("�p�����[�^�l�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�p�����[�^�l�s���B");
            }
            if (l_request.allotLimitQuantity == null)
            {
                l_request.allotLimitQuantity = "0";
            }
            l_ipoBookbuildingParams.setBbQuantityLoop(Long.parseLong(l_request.allotLimitQuantity));
            
            if (WEB3LotDivDef.OPEN_LOTTERY.equals(l_strLotDiv))
            {
                //�⌇�������g����
                l_ipoBookbuildingParams.setSubBbQuantityAll(new Long(l_request.waitingAllotTotalQuantity));
                
                //�⌇����������ʁi�P���[�v������j
                l_ipoBookbuildingParams.setSubBbQuantityLoop(new Long(l_request.waitingAllotLimitQuantity));
            }

            //������
            l_ipoBookbuildingParams.setProcess(WEB3IPOBookBuldingProcDef.LOT_INPUT);
            
            //�X�e�[�^�X
            l_ipoBookbuildingParams.setStatus(WEB3IPOBookBuldingStatusDef.DEALING);
            
            //�쐬����
            l_ipoBookbuildingParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //�X�V����
            l_ipoBookbuildingParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //�Q�|�S�j�@@ArrayList�I�u�W�F�N�g.add(IPO���Iparams�I�u�W�F�N�g)
            l_lisRecords.add(l_ipoBookbuildingParams);
            
        }
        IpoBookbuildingRow[] l_ipoBookbuildingRows = new IpoBookbuildingRow[l_intLength];

        //ArrayList�I�u�W�F�N�g��IPO���IRow[]�ɕϊ�����B
        l_lisRecords.toArray(l_ipoBookbuildingRows);

        log.exiting(STR_METHOD_NAME);
        //�S�j�@@�R�j�ŕϊ�����IPO���IRow��ԋp����B
        return l_ipoBookbuildingRows;
    }
    
    /**
     * (get�ŐV���I���R�[�h)<BR>
     * IPO���I�e�[�u�����璊�I�V�[�P���X���ŐV�̃��R�[�h���擾���A<BR>
     * �@@�@@�ԋp���郁�\�b�h�B<BR>
     * <BR>
     * �P�j�@@doFindAllQuery�i�j���g�p���Ĉȉ��̏����ŁA<BR>
     * �@@�@@IPO���I�e�[�u���Ƀ��R�[�h���擾����B<BR>
     * [�����Ώۃe�[�u��]<BR>
     * IPO���I�e�[�u��<BR>
     * <BR>
     * [��������]<BR>
     * ����ID = ����:����ID<BR>
     * ���I�敪 = ����:���I�敪<BR>
     * ���I�V�[�P���X = (select max(���I�V�[�P���X) from IPO���I�e�[�u�� where <BR>
     *                         ����ID = ����:����ID and<BR>
     *                         ���I�敪 = ����:���I�敪)<BR>
     * <BR>
     * �Q�j�@@�P�j�̌������ʂ�List��IPO���IRow[]�ɕϊ�����B<BR>
     * <BR>
     * �R�j�@@�Q�j�ŕϊ�����IPO���IRow[]��ԋp����B<BR>
     * @@param l_strProductId - ����ID
     * @@param l_strLotDiv - (���I�敪)
     * �V�K/�J�㒊�I�敪�B<BR>
     * <BR>
     * �@@�P�F�V�K���I<BR>
     * �@@�Q�F�J�㒊�I<BR>
     * @@return - IPO���IRow[]
     * @@throws WEB3BaseException
     */
    protected IpoBookbuildingRow[] getNewLotRecord(String l_strProductId, String l_strLotDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNewLotRecord(String, String)";
        log.entering(STR_METHOD_NAME); 
        
        //doFindAllQuery�i�j���g�p���Ĉȉ��̏����ŁAIPO���I�e�[�u���Ƀ��R�[�h���擾����B
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();  

            StringBuffer l_sbWhere = new StringBuffer();
            //����ID
            l_sbWhere.append(" ipo_product_id = ? ");   
            //���I�敪 
            l_sbWhere.append(" and bb_div = ? ");       
            //���I�V�[�P���X
            l_sbWhere.append(
                " and bb_seq = (select max(bb_seq) from ipo_bookbuilding where ipo_product_id = ? and bb_div = ? )");

            Object[] l_objWhere = {
                l_strProductId,
                l_strLotDiv,
                l_strProductId,
                l_strLotDiv};

            l_lisRecords = l_processor.doFindAllQuery(
                IpoBookbuildingRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dfe.getMessage(), 
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dqe.getMessage(), 
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dne.getMessage(), 
                l_dne);
        }
        
        //�Q�j�@@�������ʂ�List��IPO���IRow�z��ɕϊ�����B
        if (l_lisRecords == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");   
        }
        
        int l_intLength = l_lisRecords.size();
        IpoBookbuildingRow[] l_ipoBookbuildingRows = new IpoBookbuildingRow[l_intLength];

        l_lisRecords.toArray(l_ipoBookbuildingRows);

        log.exiting(STR_METHOD_NAME);
        //�R�j�@@�Q�j�ŕϊ�����IPO���IRow[]��ԋp����B
        return l_ipoBookbuildingRows;
    }
    
    /**
     * (get�����󋵃��R�[�h)<BR>
     * �����󋵉�ʂɕ\�����郌�R�[�h���擾���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@dofindAllQuery()���g�p���āA�ȉ��̌��������Ń��R�[�h���擾����B<BR>
     * <BR>
     * [�����Ώۃe�[�u��]<BR>
     * IPO���I�e�[�u��<BR>
     * <BR>
     * [��������]<BR>
     * IPO����ID = ����:����ID and<BR>
     * ���I�敪 = ����:���I�敪 and<BR>
     * ���I�V�[�P���X = <BR>
     * <BR>
     * if ����:���I�V�[�P���X != null�@@�̏ꍇ<BR>
     * �@@����.���I�V�[�P���X<BR>
     * else<BR>
     * �@@(������ = "14�F�m��I��" and "�X�e�[�^�X = "0�F����I��"<BR>
     * �@@or<BR>
     * �@@������ in �i"11�F�m�����","12�F�f�[������t","13:�m��J�n"�j�@@and�@@<BR>
     * �@@�@@�X�e�[�^�X in �i"1:������","9:�ُ�I��"�j�j<BR>
     * <BR>
     * [�\�[�g����]<BR>
     * ���I�V�[�P���X.asc<BR>
     * <BR>
     * �Q�j�@@�������ʂ�List��IPO���IRow�z��ɕϊ�����B<BR>
     * <BR>
     * �R�j�@@�Q�j�ŕϊ�����IPO���IRow[]��ԋp����B<BR>
     * @@param l_strProductId - ����ID
     * @@param l_strLotDiv - (���I�敪)
     * �V�K/�J�㒊�I�敪�B<BR>
     * <BR>
     * �@@�P�F�V�K���I<BR>
     * �@@�Q�F�J�㒊�I<BR>
     * @@param l_strBbSeq - ���I�V�[�P���X
     * @@return - IPO���IRow[]
     * @@throws WEB3BaseException
     */
    protected IpoBookbuildingRow[] getCompleteStateRecord(
        String l_strProductId,
        String l_strLotDiv,
        String l_strBbSeq) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCompleteStateRecord(String, String, String)";
        log.entering(STR_METHOD_NAME); 
        
        //dofindAllQuery()���g�p���āA�ȉ��̌��������Ń��R�[�h���擾����
        List l_lisRecords = new ArrayList();
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor(); 

            StringBuffer l_sbWhere = new StringBuffer();
            Object[] l_objWhere = null;
            l_sbWhere.append(" ipo_product_id = ? ");   //IPO����ID
            l_sbWhere.append(" and bb_div = ? ");       //���I�敪 
            
            //if ����:���I�V�[�P���X != null�@@�̏ꍇ
            if (l_strBbSeq != null)
            {
                l_sbWhere.append(" and bb_seq = ? ");       //���I�V�[�P���X
                
                l_objWhere = new Object[]{
                    l_strProductId,
                    l_strLotDiv,
                    l_strBbSeq};
            }
            //else
            //�@@(������ = "14�F�m��I��" and "�X�e�[�^�X = "0�F����I��"
            //�@@or
            //�@@������ in �i"11�F�m�����","12�F�f�[������t","13:�m��J�n"�j�@@and
            //�@@�@@�X�e�[�^�X in �i"1:������","9:�ُ�I��"�j�j
            else
            {
                l_sbWhere.append(" and (process = ? and status = ?");
                l_sbWhere.append(" or process in (?, ?, ?) and status in (?, ?)) ");       //���I�V�[�P���X
                l_objWhere = new Object[]{
                    l_strProductId,
                    l_strLotDiv,
                    WEB3IPOBookBuldingProcDef.CONFIRM_END,
                    WEB3IPOBookBuldingStatusDef.NORMAL_END,
                    WEB3IPOBookBuldingProcDef.CONFIRM_INPUT,
                    WEB3IPOBookBuldingProcDef.CONFIRM_DAEMON_ACCEPT,
                    WEB3IPOBookBuldingProcDef.CONFIRM_BEGIN,
                    WEB3IPOBookBuldingStatusDef.DEALING,
                    WEB3IPOBookBuldingStatusDef.ABNORMAL_END};
            }
            
            String l_strSortKey = " bb_seq asc";
            
            l_lisRecords = l_processor.doFindAllQuery(
                IpoBookbuildingRow.TYPE,
                l_sbWhere.toString(),
                l_strSortKey,
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dfe.getMessage(), 
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dqe.getMessage(), 
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dne.getMessage(), 
                l_dne);
        }
        
        //�Q�j�@@�������ʂ�List��IPO���IRow�z��ɕϊ�����B
        int l_intLength = l_lisRecords.size();
        IpoBookbuildingRow[] l_ipoBookbuildingRows = new IpoBookbuildingRow[l_intLength];

        l_lisRecords.toArray(l_ipoBookbuildingRows);

        log.exiting(STR_METHOD_NAME); 
        //�R�j�@@�Q�j�ŕϊ�����IPO���IRow[]��ԋp����B
        return l_ipoBookbuildingRows;
    }
    
    /**
     * (save���I�m��)<BR>
     * �A�C�e���̒�`<BR>
     * ���I�������R�[�h��update�������s���B <BR>
     * <BR>
     * <BR>
     * �P�j�@@����.���XID���X�g�̃T�C�Y��Loop�����������Ȃ��A�X�V���������s����B<BR>
     * <BR>
     * �@@�P�|�P�j�@@����:���XID���X�g.get�i i �j�ŁA���XID���擾����B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@doUpdateAllQuery�i�j���g�p���Ĉȉ��̏����ŁAIPO���I�e�[�u����<BR>
     * �@@�@@�@@�@@�@@�@@���R�[�h��Update����B<BR>
     * �@@[�X�V����]<BR>
     * �@@�E�@@IPO����ID = ����:����ID<BR>
     * �@@�E�@@���I�敪 = ����.���I�敪<BR>
     * �@@�E�@@���I�V�[�P���X = ����:���I�V�[�P���X<BR>
     * �@@�E�@@���XID = �P�|�P�j�Ŏ擾�������XID<BR>
     * <BR>
     * �@@[�X�V���e]<BR>
     * �@@�E�@@������ = "11�F�m�����"<BR>
     * �@@�E�@@�X�e�[�^�X = "1�F������"�@@<BR>
     * �@@�E�@@�X�V���� =  ����:�X�V����<BR>
     * <BR>
     * �@@DB�X�V�d�l <BR>
     * �@@�u���I����_IPO���I�e�[�u���d�l.xls�v�Q�ƁB <BR>
     * @@param l_strProductId - ����ID
     * @@param l_strLotDiv - (���I�敪)
     * �V�K/�J�㒊�I�敪�B<BR>
     * <BR>
     * �@@�P�F�V�K���I<BR>
     * �@@�Q�F�J�㒊�I<BR>
     * @@param l_lngBbSeq - ���I�V�[�P���X
     * @@param l_lisBranchIdLists - (���XID���X�g)
     * ���XID���X�g�I�u�W�F�N�g�B
     * @@param l_tsLastUpdatedTimestamp- (�X�V����)
     * GtlUtils.getSystemTimestamp()�Ŏ擾�����X�V����
     * @@throws WEB3BaseException
     */
    protected void saveLotConfirm(
        String l_strProductId,
        String l_strLotDiv,
        long l_lngBbSeq,
        List l_lisBranchIdLists,
        Timestamp l_tsLastUpdatedTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveLotConfirm(" +
            " String," +
            " String," +
            " long," +
            " List," +
            " Timestamp)";
        log.entering(STR_METHOD_NAME);
        
        long l_lngBranchIdLength = l_lisBranchIdLists.size();
        //�P�j�@@����.���XID���X�g�̃T�C�Y��Loop�����������Ȃ��A�X�V���������s����B
        for (int i = 0; i < l_lngBranchIdLength; i++)
        {
            //�P�|�P�|�P�j�@@���XID���X�g���畔�XID��get( i )����B
            Long l_lngBranchId = (Long) l_lisBranchIdLists.get(i);
            
            //doUpdateAllQuery()���g�p���Ĉȉ��̏����ŁAIPO���I�e�[�u���̃��R�[�h��Update����B
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();  

                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" ipo_product_id = ? ");    //IPO����ID
                l_sbWhere.append(" and bb_div = ? ");        //���I�敪 
                l_sbWhere.append(" and bb_seq = ? ");        //���I�V�[�P���X
                l_sbWhere.append(" and branch_id = ? ");     //���XID

                Object[] l_objWhere = {
                    l_strProductId,
                    l_strLotDiv,
                    new Long(l_lngBbSeq),
                    l_lngBranchId};

                Map l_map = new HashMap();
                l_map.put("process", WEB3IPOBookBuldingProcDef.CONFIRM_INPUT);
                l_map.put("status", WEB3IPOBookBuldingStatusDef.DEALING);
                l_map.put("last_updated_timestamp", l_tsLastUpdatedTimestamp);
                l_processor.doUpdateAllQuery(
                    IpoBookbuildingRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere,
                    l_map);
            }
            catch (DataFindException l_dfe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dfe.getMessage(), 
                    l_dfe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dqe.getMessage(), 
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dne.getMessage(), 
                    l_dne);
            }
        }
        log.exiting(STR_METHOD_NAME); 
    }
    
   /** 
    * (validate���I����)
    * IPO���I�����̑Ó����`�F�b�N���s���B<BR>
    * <BR>
    * �P�j�@@IPO�����I�u�W�F�N�g.is�폜�����i�j�ŁA�폜�������𔻒肷��B<BR>
    * �@@�@@  ���ʂ�true�̏ꍇ�A��O���X���[����B <BR>
    * class: WEB3BusinessLayerExceptio<BR>
    * tag:  BUSINESS_ERROR_02326<BR>
    * <BR>
    * �Q�j�@@IPO�����I�u�W�F�N�g.is���~�i�j�ŁA���~�������𔻒肷��B<BR>
    * �@@�@@  ���ʂ�true�̏ꍇ�A��O���X���[����B <BR>
    * class: WEB3BusinessLayerExceptio<BR>
    * tag:  BUSINESS_ERROR_02327<BR>
    * <BR>
    * �R�j�@@IPO�����I�u�W�F�N�g.is�u�b�N�r���f�B���O�I���i�j�ŁA<BR>
    *       �u�b�N�r���f�B���O���Ԃ�<BR>
    * �@@�@@�@@�I�����Ă��邩�𔻒肷��B<BR>
    * �@@�@@  ���ʂ�false�̏ꍇ�A��O���X���[����B<BR>
    * class: WEB3BusinessLayerExceptio<BR>
    * tag:  BUSINESS_ERROR_02328<BR>
    * <BR>
    * �S�j�@@IPO�����I�u�W�F�N�g.is�X�P�W���[������i�j�ŁA<BR>
    *       �X�P�W���[�����ڂ��Ó����𔻒肷��B<BR>
    * �@@�@@   ���ʂ�false�̏ꍇ�A��O���X���[����B<BR>
    * class: WEB3BusinessLayerException<BR>
    * tag:  BUSINESS_ERROR_02329<BR>
    * <BR>
    * �T�j�@@IPO�����I�u�W�F�N�g.is���J���i����i�j�ŁA<BR>
    *       ���J���i���ݒ肳��Ă��邩�𔻒肷��B<BR>
    * �@@�@@  ���ʂ�false�̏ꍇ�A��O���X���[����B<BR>
    * class: WEB3BusinessLayerException<BR>
    * tag:  BUSINESS_ERROR_02330<BR>
    * <BR>
    * �U�j�@@IPO�����I�u�W�F�N�g.get�戵���ʁi�j�ŁA�戵���ʂ��擾����B<BR>
    * �@@�U�|�P�j�@@�擾�����戵���ʂ� 0�A<BR>
    * �@@�@@�@@�܂���Double.isNaN(�擾�����戵����)�̏ꍇ�A��O���X���[����B<BR>
    * class: WEB3BusinessLayerException<BR>
    * tag:  BUSINESS_ERROR_02331<BR>
    * <BR>
    * �V�j�@@IPO�����I�u�W�F�N�g.get�w���\���P�ʁi�j�ŁA<BR>
    *       �w���\���P�ʂ��擾����B<BR>
    * �@@�V�|�P�j�@@�擾�����w���\���P�ʂ� 0�A<BR>
    * �@@�@@�@@�܂���Double.isNaN(�擾�����w���\���P��)�̏ꍇ�A��O���X���[����B<BR>
    * class: WEB3BusinessLayerException<BR>
    * tag:  BUSINESS_ERROR_02332<BR>
    * @@param l_ipoProductImpl - (IPO�����I�u�W�F�N�g)
    * @@throws WEB3BaseException
    */
    protected void validateLotProduct(WEB3IpoProductImpl l_ipoProductImpl) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateLotProduct(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@IPO�����I�u�W�F�N�g.is�폜�����i�j�ŁA�폜�������𔻒肷��B
        //���ʂ�true�̏ꍇ�A��O���X���[����B
        if (l_ipoProductImpl.isDeletedProduct())
        {
            log.debug("���I�������폜�����ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02326,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "���I�������폜�����ł��B");
        }
        
        //�Q�j�@@IPO�����I�u�W�F�N�g.is���~�i�j�ŁA���~�������𔻒肷��B
        //���ʂ�true�̏ꍇ�A��O���X���[����B
        if (l_ipoProductImpl.isDiscontinuation())
        {
            log.debug("���I���������~�����ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02327,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "���I���������~�����ł��B");
        }
        
        //�R�j�@@IPO�����I�u�W�F�N�g.is�u�b�N�r���f�B���O�I���i�j�ŁA
        //�u�b�N�r���f�B���O���Ԃ��I�����Ă��邩�𔻒肷��B
        //���ʂ�false�̏ꍇ�A��O���X���[����B
        if (!l_ipoProductImpl.isBookbuildingEnd())
        {
            log.debug("���I�E�������Ԃł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02311,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "���I�E�������Ԃł͂���܂���B");
        }
        
        //�S�j�@@IPO�����I�u�W�F�N�g.is�X�P�W���[������i�j�ŁA
        //�X�P�W���[�����ڂ��Ó����𔻒肷��B���ʂ�false�̏ꍇ�A��O���X���[����B
        if (!l_ipoProductImpl.isScheduleDecision())
        {
            log.debug("���I�����̃X�P�W���[�����ڂ��s�K�؂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02329,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "���I�����̃X�P�W���[�����ڂ��s�K�؂ł��B");
        }
        
        //�T�j�@@IPO�����I�u�W�F�N�g.is���J���i����i�j�ŁA
        //���J���i���ݒ肳��Ă��邩�𔻒肷��B���ʂ�false�̏ꍇ�A��O���X���[����B
        if (!l_ipoProductImpl.isPublicPriceSettle())
        {
            log.debug("���I�����̌��J���i���ݒ肳��Ă��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02330,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "���I�����̌��J���i���ݒ肳��Ă��܂���B");
        }
        
        //�U�j�@@IPO�����I�u�W�F�N�g.get�戵���ʁi�j�ŁA�戵���ʂ��擾����B
        //�@@�U�|�P�j�@@�擾�����戵���ʂ� 0�A
        //�@@�@@�@@�܂���Double.isNaN(�擾�����戵����)�̏ꍇ�A��O���X���[����B
        double l_dblDealingQuantity = l_ipoProductImpl.getDealingQuantity();
        if (Double.isNaN(l_dblDealingQuantity) || l_dblDealingQuantity == 0)
        {
            log.debug("���I�����̎戵���ʂ� 0�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02331,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "���I�����̎戵���ʂ� 0�ł��B"); 
        }
        
        //�V�j�@@IPO�����I�u�W�F�N�g.get�w���\���P�ʁi�j�ŁA�w���\���P�ʂ��擾����B
        //�@@�V�|�P�j�@@�擾�����w���\���P�ʂ� 0�A
        //�@@�@@�@@�܂���Double.isNaN(�擾�����w���\���P��)�̏ꍇ�A��O���X���[����B
        double l_dblLotSize = l_ipoProductImpl.getLotSize();
        if (Double.isNaN(l_dblLotSize) || l_dblLotSize == 0)
        {
            log.debug("���I�����̍w���\���P�ʂ� 0�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02332,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "���I�����̍w���\���P�ʂ� 0�ł��B"); 
        }
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (validate���I�Ó���)<BR>
     * IPO�\���e�[�u���̒��I�Ó������`�F�b�N���郁�\�b�h�B<BR>
     * <BR>
     * �P�j�@@if�@@���I�敪 = "�V�K���I"�@@�̏ꍇ<BR>
     * �@@�P�|�P�j�@@doFindAllQuery()���g�p���Ĉȉ��̏����ŁAIPO�\���e�[�u������������B<BR>
     * <BR>
     * �@@�@@[�����Ώۃe�[�u��]<BR>
     * �@@�@@IPO�\���e�[�u��<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@����ID = ����:����ID and<BR>
     * �@@�@@���XID in (����:���XID���X�g[0],����:���XID���X�g[1]�����) and<BR>
     * �@@�@@�u�b�N�r���f�B���O�\����� != "���" and<BR>
     * �@@�@@���I���� = "�����I"<BR>
     * <BR>
     * �@@�P�|�Q�j�@@if �������ʂ�0���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�G���[���b�Z�[�W�u���I�Ώیڋq�����݂��܂���B�v<BR>
     * �@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@:�@@�@@BUSINESS_ERROR_02312<BR>
     * <BR>
     * �Q�j�@@if�@@���I�敪 = "�J�㒊�I"�@@�̏ꍇ<BR>
     * �@@�Q�|�P�j�@@doFindAllQuery()���g�p���Ĉȉ��̏����ŁAIPO�\���e�[�u������������B<BR>
     * <BR>
     * �@@�@@[�����Ώۃe�[�u��]<BR>
     * �@@�@@IPO�\���e�[�u��<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@����ID = ����:����ID and<BR>
     * �@@�@@���XID in (����:���XID���X�g[0],����:���XID���X�g[1]�����) and<BR>
     * �@@�@@���I���� = "�⌇" and<BR>
     * �@@�@@�w���\���敪 != "����" and<BR>
     * �@@�@@���I���ʁi�J��j = "DEFAULT(�����I)"<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@if �������ʂ�0���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�G���[���b�Z�[�W�u�⌇�f�[�^�����݂��܂���B�v<BR>
     * �@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@:�@@�@@BUSINESS_ERROR_02333<BR>
     * <BR>
     * ������:���XID���X�g�̗v�f���ǉ�<BR>
     * <BR>
     * @@param l_strProductId - (����ID)<BR>
     * IPO����ID<BR>
     * @@param l_strLotDiv - (���I�敪)<BR>
     * �V�K/�J�㒊�I�敪<BR>
     * <BR>
     * �P�F�V�K���I<BR>
     * �Q�F�J�㒊�I<BR>
     * @@param l_lisBranchIds - (���XID���X�g)<BR>
     * ���XID���X�g�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    protected void validateLotValidity(
        String l_strProductId, String l_strLotDiv, List l_lisBranchIds) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateLotValidity(" +
            " String," +
            " String," +
            " List)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@if�@@���I�敪 = "�V�K���I"�@@�̏ꍇ
        int l_intBranchIdLength = l_lisBranchIds.size();
        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisQueryContainers = new ArrayList();
        if (WEB3LotDivDef.OPEN_LOTTERY.equals(l_strLotDiv))
        {
            //�P�|�P�j�@@doFindAllQuery()���g�p���Ĉȉ��̏����ŁAIPO�\���e�[�u������������B
            //[�����Ώۃe�[�u��]
            //IPO�\���e�[�u��
            //[��������]
            //����ID = ����:����ID and
            //���XID in (����:���XID���X�g[0],����:���XID���X�g[1]�����) and
            //�u�b�N�r���f�B���O�\����� != "���" and
            //���I���� = "�����I"
            l_sbWhere.append(" ipo_product_id = ? ");
            l_lisQueryContainers.add(l_strProductId);

            l_sbWhere.append(" and branch_id in (");
            for (int i = 0; i < l_intBranchIdLength; i++)
            {
                if (i == (l_intBranchIdLength - 1))
                {
                    l_sbWhere.append(" ? ");
                }
                else
                {
                    l_sbWhere.append(" ?, ");
                }
                l_lisQueryContainers.add((Long)l_lisBranchIds.get(i));
            }
            l_sbWhere.append(")");

            l_sbWhere.append(" and order_status <> ? ");
            l_sbWhere.append(" and lot_result = ? ");

            l_lisQueryContainers.add(new Long(OrderStatusEnum.CANCELLED.intValue()));
            l_lisQueryContainers.add(WEB3LotResultDivDef.DEFAULT);

            Object[] l_objWhere = new Object[l_lisQueryContainers.size()];
            l_lisQueryContainers.toArray(l_objWhere);

            List l_lisRecords = null;
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();

                l_lisRecords = l_processor.doFindAllQuery(
                    IpoOrderRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objWhere);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�@@�P�|�Q�j�@@if �������ʂ�0���̏ꍇ�A��O���X���[����B
            //�G���[���b�Z�[�W�u���I�Ώیڋq�����݂��܂���B�v
            if (l_lisRecords == null || l_lisRecords.size() == 0)
            {
                log.debug("���I�Ώیڋq�����݂��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02312,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���I�Ώیڋq�����݂��܂���B");
            }
        }
        else if (WEB3LotDivDef.ADVANCED_LOTTERY.equals(l_strLotDiv))
        {
            //�Q�|�P�j�@@doFindAllQuery()���g�p���Ĉȉ��̏����ŁAIPO�\���e�[�u������������B
            //[�����Ώۃe�[�u��]
            //IPO�\���e�[�u��
            //[��������]
            //����ID = ����:����ID and
            //���XID in (����:���XID���X�g[0],����:���XID���X�g[1]�����) and
            //���I���� = "�⌇" and
            //�w���\���敪 != "����" and
            //���I���ʁi�J��j = "DEFAULT(�����I)"
            l_sbWhere.append(" ipo_product_id = ? ");
            l_lisQueryContainers.add(l_strProductId);

            l_sbWhere.append(" and branch_id in (");
            for (int i = 0; i < l_intBranchIdLength; i++)
            {
                if (i == (l_intBranchIdLength - 1))
                {
                    l_sbWhere.append(" ? ");
                }
                else
                {
                    l_sbWhere.append(" ?, ");
                }
                l_lisQueryContainers.add((Long)l_lisBranchIds.get(i));
            }
            l_sbWhere.append(")");

            l_sbWhere.append(" and lot_result = ? ");
            l_sbWhere.append(" and offering_div <> ? ");
            l_sbWhere.append(" and lot_result_retry = ? ");

            l_lisQueryContainers.add(WEB3LotResultDivDef.SUPPLEMENT);
            l_lisQueryContainers.add(WEB3OfferingDivDef.REFUSAL);
            l_lisQueryContainers.add(WEB3LotResultDivDef.DEFAULT);

            Object[] l_objWhere = new Object[l_lisQueryContainers.size()];
            l_lisQueryContainers.toArray(l_objWhere);

            List l_lisRecords = null;
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();

                l_lisRecords = l_processor.doFindAllQuery(
                    IpoOrderRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objWhere);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�Q�|�Q�j�@@if �������ʂ�0���̏ꍇ�A��O���X���[����B
            //�G���[���b�Z�[�W�u�⌇�f�[�^�����݂��܂���B�v
            if (l_lisRecords == null || l_lisRecords.size() == 0)
            {
                log.debug("�⌇�f�[�^�����݂��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02333,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�⌇�f�[�^�����݂��܂���B");
            }
        }
        log.exiting(STR_METHOD_NAME); 
    }
    
    /** 
     * (validate�������͍���)
     * ���͍��ڂ̐��l�`�F�b�N�������Ȃ��B<BR>
     * <BR>
     * �P�j�@@null�`�F�b�N<BR>
     * �@@�P�|�P�j�@@if ���N�G�X�g�I�u�W�F�N�g.�������g���ʁ@@= null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@�G���[���b�Z�[�W�u�������g���ʂ����l�ȊO�ł��B�v<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02314<BR>
     * <BR>
     * �@@�P�|�Q�j�@@if ���N�G�X�g�I�u�W�F�N�g.����������� = null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@�G���[���b�Z�[�W�u����������ʂ����l�ȊO�ł��B�v<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02315 <BR>
     * <BR>
     * �@@����:���I�敪 = "�V�K���I"�̏ꍇ�̂݁A�P�|�R�j�`�P�|�S�j�̏��������s<BR>
     * <BR>
     * �@@�P�|�R�j�@@if�@@���N�G�X�g�I�u�W�F�N�g.�⌇�������g���ʁ@@= null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@�G���[���b�Z�[�W�u�⌇�������g���ʂ����l�ȊO�ł��B�v<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02316 <BR>
     * <BR>
     * �@@�P�|�S�j�@@if ���N�G�X�g�I�u�W�F�N�g.�⌇����������ʁ@@= null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@�G���[���b�Z�[�W�u�⌇����������ʂ����l�ȊO�ł��B�v<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02317 <BR>
     * <BR>
     * �Q�j�@@�����\���ʒ��߃`�F�b�N<BR>
     * �@@this.get�����\���ʁi���N�G�X�g�I�u�W�F�N�g , <BR>
     * �@@IPO�����I�u�W�F�N�g , ���I�敪�j ��<BR>
     * �@@���N�G�X�g�I�u�W�F�N�g.�������g���ʂ̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@<BR>
     * �@@�G���[���b�Z�[�W�u�������g���ʂ������\���ʂ𒴉߂��Ă��܂��B�v<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02313<BR>
     * <BR>
     * �R�j�@@�P�ʐ��ʃ`�F�b�N<BR>
     * <BR>
     * �@@�R�|�P�j�@@IPO�����`�F�b�N�N���X�̃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�������g���ʂ̃`�F�b�N�@@<BR>
     * <BR>
     * �@@�@@�R�|�Q�|�P�j�@@if ���N�G�X�g�I�u�W�F�N�g.�������g���ʁ@@!= 0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȉ������s<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@IPO�����N���X�I�u�W�F�N�g.validate����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�����N���X�I�u�W�F�N�g , ���ʁj��<BR>
     * �G���[�����������ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�G���[���b�Z�[�W�u�������g���ʂ́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w���\���P�ʂ̐����{�œ��͂��Ă��������B�v<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02319<BR>
     * <BR>
     * �@@�R�|�R�j�@@����������ʂ̃`�F�b�N<BR>
     * <BR>
     * �@@�@@�R�|�R�|�P�j�@@if ���N�G�X�g�I�u�W�F�N�g.����������ʁ@@!= 0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȉ������s<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@IPO�����N���X�I�u�W�F�N�g.validate����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�����N���X�I�u�W�F�N�g , ���ʁj��<BR>
     * �G���[�����������ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�G���[���b�Z�[�W�u����������ʂ́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w���\���P�ʂ̐����{�œ��͂��Ă��������B�v<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02320 <BR>
     * <BR>
     * �@@�R�|�S�j�@@����:���I�敪 = "�V�K���I"�̏ꍇ�A�⌇�������g���ʁA<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�⌇����������ʂ̃`�F�b�N<BR>
     * <BR>
     * �@@�@@�R�|�S�|�P�j�@@if ���N�G�X�g�I�u�W�F�N�g.�⌇�������g���ʁ@@!= 0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȉ������s<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@IPO�����N���X�I�u�W�F�N�g.validate����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�����N���X�I�u�W�F�N�g , ���ʁj��<BR>
     * �G���[�����������ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�G���[���b�Z�[�W�u�⌇�������g���ʂ́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w���\���P�ʂ̐����{�œ��͂��Ă��������B�v<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02321 <BR>
     * <BR>
     * �@@�@@�R�|�S�|�Q�j�@@if ���N�G�X�g�I�u�W�F�N�g.�⌇����������ʁ@@!= 0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȉ������s<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@IPO�����N���X�I�u�W�F�N�g.validate����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�����N���X�I�u�W�F�N�g , ���ʁj��<BR>
     * �G���[�����������ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�G���[���b�Z�[�W�u�⌇����������ʂ́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w���\���P�ʂ̐����{�œ��͂��Ă��������B�v<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02333<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)
     * @@param l_strLotDiv - (���I�敪)
     * @@param l_ipoProductImpl - (IPO����)
     * @@throws WEB3BaseException
     */
    protected void validateAllotInputItem(
        WEB3AdminIPOLotConfirmRequest l_request,
        String l_strLotDiv, 
        WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAllotInputItem(" +
            " WEB3AdminIPOLotConfirmRequest," +
            " String," +
            " WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@null�`�F�b�N 
        //�P�|�P�jif ���N�G�X�g�I�u�W�F�N�g.�������g���ʁ@@= null �̏ꍇ�A��O���X���[ 
        //�@@�@@�@@�@@�G���[���b�Z�[�W�u�������g���ʂ����l�ȊO�ł��B�v 
        if (l_request.allotTotalQuantity == null)
        {
            log.debug("�������g���ʂ����l�ȊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02314,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�������g���ʂ����l�ȊO�ł��B");
        }
        
        //�P�|�Q�jif ���N�G�X�g�I�u�W�F�N�g.����������� = null �̏ꍇ�A��O���X���[ 
        //�@@�@@�@@  �G���[���b�Z�[�W�u����������ʂ����l�ȊO�ł��B�v 
        if (l_request.allotLimitQuantity == null)
        {
            log.debug("����������ʂ����l�ȊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02315,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "����������ʂ����l�ȊO�ł��B");
        }
        
        //����:���I�敪 = "�V�K���I"�̏ꍇ�̂݁A�P�|�R�j�`�P�|�S�j�̏��������s 
        if (WEB3LotDivDef.OPEN_LOTTERY.equals(l_strLotDiv))
        {
            //�P�|�R�j�@@if�@@���N�G�X�g�I�u�W�F�N�g.�⌇�������g���ʁ@@= null �̏ꍇ�A
            //          ��O���X���[�A�G���[���b�Z�[�W�u�⌇�������g���ʂ����l�ȊO�ł��B�v 
            if (l_request.waitingAllotTotalQuantity == null)
            {
                log.debug("�⌇�������g���ʂ����l�ȊO�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02316,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�⌇�������g���ʂ����l�ȊO�ł��B");
            }
            
            //�P�|�S�j�@@if ���N�G�X�g�I�u�W�F�N�g.�⌇����������ʁ@@= null �̏ꍇ�A
            //    ��O���X���[ �A�G���[���b�Z�[�W�u�⌇����������ʂ����l�ȊO�ł��B�v 
            if (l_request.waitingAllotLimitQuantity == null)
            {
                log.debug("�⌇����������ʂ����l�ȊO�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02317,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�⌇����������ʂ����l�ȊO�ł��B");
            }
        }
            
        //�Q�j�@@�����\���ʒ��߃`�F�b�N 
        //this.get�����\����
        //�i���N�G�X�g�I�u�W�F�N�g , IPO�����I�u�W�F�N�g , ���I�敪�j �� 
        //���N�G�X�g�I�u�W�F�N�g.�������g���ʂ̏ꍇ�A��O���X���[����B 
        //�G���[���b�Z�[�W�u�������g���ʂ������\���ʂ𒴉߂��Ă��܂��B�v 
        long l_lngAllotQuantity = this.getAllotAbleQuantity(l_request, l_ipoProduct, l_strLotDiv);
        
        if (l_lngAllotQuantity < Long.parseLong(l_request.allotTotalQuantity))
        {
            log.debug("�������g���ʂ������\���ʂ𒴉߂��Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02313,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�������g���ʂ������\���ʂ𒴉߂��Ă��܂��B");
        }
        
        //�R�j�@@�P�ʐ��ʃ`�F�b�N 
        //�R�|�P�j�@@IPO�����`�F�b�N�N���X�̃I�u�W�F�N�g�𐶐�����B
        WEB3IpoOrderValidator l_ipoOrderValidator = new WEB3IpoOrderValidator();
        
        //�@@�@@�R�|�Q�|�P�j�@@if ���N�G�X�g�I�u�W�F�N�g.
        //�������g���ʁ@@!= 0 �̏ꍇ�A�ȉ������s 
        //IPO�����N���X�I�u�W�F�N�g.
        //validate���ʁi�����N���X�I�u�W�F�N�g , ���ʁj�� 
        //�G���[�����������ꍇ�A��O���X���[ 
        //�G���[���b�Z�[�W�u�������g���ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B�v 
        double l_dblAllotTotalQuantity = Double.parseDouble(l_request.allotTotalQuantity);
        if (l_dblAllotTotalQuantity != 0)
        {
            try
            {
                l_ipoOrderValidator.validateQuantity(l_ipoProduct, l_dblAllotTotalQuantity);
            }
            catch (WEB3BusinessLayerException l_ble)
            {
                log.error("�������g���ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02319, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�������g���ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B", 
                    l_ble);
            } 
        }
        
        //�R�|�R�j�@@����������ʂ̃`�F�b�N 
        //�R�|�R�|�P�j�@@if ���N�G�X�g�I�u�W�F�N�g.����������ʁ@@!= 0 �̏ꍇ�A
        //�ȉ������s IPO�����N���X�I�u�W�F�N�g.validate���ʁi
        //�����N���X�I�u�W�F�N�g , ���ʁj�� �G���[�����������ꍇ�A��O���X���[ 
        //�G���[���b�Z�[�W�u����������ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B�v 
        double l_dblAllotLimitQuantity = Double.parseDouble(l_request.allotLimitQuantity);
        if (l_dblAllotLimitQuantity != 0)
        {
            try
            {
                l_ipoOrderValidator.validateQuantity(l_ipoProduct, l_dblAllotLimitQuantity);
            }
            catch (WEB3BusinessLayerException l_ble)
            {
                log.error("����������ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02320, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "����������ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B", 
                    l_ble);
            }
        }
        
        //�R�|�S�j�@@����:���I�敪 = "�V�K���I"�̏ꍇ�A�⌇�������g���ʁA
        //          �⌇����������ʂ̃`�F�b�N 
        if (WEB3LotDivDef.OPEN_LOTTERY.equals(l_strLotDiv))
        {
            //�R�|�S�|�P�j�@@if ���N�G�X�g�I�u�W�F�N�g.�⌇�������g���ʁ@@!= 0 �̏ꍇ�A
            //�ȉ������s IPO�����N���X�I�u�W�F�N�g.validate���ʁi
            //�����N���X�I�u�W�F�N�g , ���ʁj�� �G���[�����������ꍇ�A��O���X���[ 
            //�G���[���b�Z�[�W�u�⌇�������g���ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B�v
            double l_dblWaitingAllotTotalQuantity = Double.parseDouble(l_request.waitingAllotTotalQuantity);
            if (l_dblWaitingAllotTotalQuantity != 0)
            {
                try
                {
                    l_ipoOrderValidator.validateQuantity(l_ipoProduct, l_dblWaitingAllotTotalQuantity);
                }
                catch (WEB3BusinessLayerException l_ble)
                {
                    log.error("�⌇�������g���ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02321, 
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        "�⌇�������g���ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B", 
                        l_ble);
                }
            }
            
            //�R�|�S�|�Q�j�@@if ���N�G�X�g�I�u�W�F�N�g.�⌇����������ʁ@@!= 0 �̏ꍇ�A
            //�ȉ������s IPO�����N���X�I�u�W�F�N�g.validate���ʁi
            //�����N���X�I�u�W�F�N�g , ���ʁj�� �G���[�����������ꍇ�A��O���X���[ 
            //�G���[���b�Z�[�W�u�⌇����������ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B�v
            double l_dblWaitingAllotLimitQuantity = Double.parseDouble(l_request.waitingAllotLimitQuantity);
            if (l_dblWaitingAllotLimitQuantity != 0)
            {
                try
                {
                    l_ipoOrderValidator.validateQuantity(l_ipoProduct, l_dblWaitingAllotLimitQuantity);
                }
                catch (WEB3BusinessLayerException l_ble)
                {
                    log.error("�⌇����������ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02322, 
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        "�⌇����������ʂ́A�w���\���P�ʂ̐����{�œ��͂��Ă��������B", 
                        l_ble);
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * (validate�ُ�I��)<BR>
     * ���I�������ُ�I�����ۂ����`�F�b�N���郁�\�b�h�B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁAdoFindAllQuery�i�j���g�p����IPO���I�e�[�u������������B<BR>
     * <BR>
     * [�����Ώۃe�[�u��]
     * IPO���I�e�[�u��
     * <BR>
     * [��������]<BR>
     * ����ID = ����:����ID<BR>
     * ���I�敪 = ����:���I�敪<BR>
     * ������ in ("11�F�m�����","12�F�f�[������t","13�F�m��J�n","14�F�m��I��")<BR>
     * �X�e�[�^�X = "9�F�ُ�I��"<BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾�����������ʂ��P���ȏ�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�G���[���b�Z�[�W�u���I�������ɃG���[���������܂����B�v
     * @@param l_strProductId - ����ID
     * @@param l_strLotDiv - (���I�敪)
     * �V�K/�J�㒊�I�敪�B<BR>
     * <BR>
     * �@@�P�F�V�K���I<BR>
     * �@@�Q�F�J�㒊�I<BR>
     * @@return long
     * @@throws WEB3BaseException
     */
    protected void validateAbnormalEnd(String l_strProductId, String l_strLotDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAbnormalEnd(String , String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRecords = new ArrayList();

        try
        {
            //�P�j�@@�ȉ��̏����ŁAdoFindAllQuery�i�j���g�p����IPO���I�e�[�u������������B
            QueryProcessor l_processor = Processors.getDefaultProcessor();  

            StringBuffer l_sbWhere = new StringBuffer();

            // ����ID
            l_sbWhere.append(" ipo_product_id = ? ");   
            // ���I�敪 
            l_sbWhere.append(" and bb_div = ? ");       
            // ������
            l_sbWhere.append(" and process in (?, ?, ?, ?)");
            // �X�e�[�^�X
            l_sbWhere.append(" and status = ? ");

            Object[] l_objWhere = {
                l_strProductId,
                l_strLotDiv,
                WEB3IPOBookBuldingProcDef.CONFIRM_INPUT,
                WEB3IPOBookBuldingProcDef.CONFIRM_DAEMON_ACCEPT,
                WEB3IPOBookBuldingProcDef.CONFIRM_BEGIN,
                WEB3IPOBookBuldingProcDef.CONFIRM_END,
                WEB3IPOBookBuldingStatusDef.ABNORMAL_END
                };

            l_lisRecords = l_processor.doFindAllQuery(
                IpoBookbuildingRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dfe.getMessage(), 
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dqe.getMessage(), 
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dne.getMessage(), 
                l_dne);
        }

        //�Q�j�@@�P�j�Ŏ擾�������ʂ�1���ȏ�̏ꍇ�A��O���X���[����B
        int l_intLength = l_lisRecords.size();

        if (l_intLength != 0)
        {
            log.debug("���I�������ɃG���[���������܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02310,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "���I�������ɃG���[���������܂����B");
        }
    }
     
   /** 
    * (validate���I��)
    * ���I�󋵂̊m�F���s���A�G���[��ʂ�\������ꍇ�͗�O��<BR>
    *   �X���[����B<BR>
    * <BR>
    * �@@�P�j�@@����.IPO���IRow���珈���󋵂��擾<BR>
    * <BR>
    * �@@�Q�|�Q�j�@@����.IPO���IRow����X�e�[�^�X���擾<BR>
    * <BR>
    * �@@�Q�|�R�j�@@if ������ in �i"01�F���I����","02�F�f�[������t",<BR>
    *   "03�F���I�J�n",<BR>
    * �@@"11�F�m�����","12�F�f�[������t","13�F�m��J�n"�j�@@����<BR>
    * �@@�X�e�[�^�X��"9�F�ُ�I��"�̏ꍇ�A��O���X���[����B<BR>
    *              class: WEB3BusinessLayerException<BR>
    *              tag:   BUSINESS_ERROR_02310<BR><BR>
    * <BR>
    * �@@�G���[���b�Z�[�W�u���I�������ɃG���[���������܂����B�v<BR>
    * <BR>
    * �@@�Q�|�S�j�@@if ������ in �i"11�F�m�����","12�F�f�[������t",<BR>
    *    "13�F�m��J�n","14�F�m��I��"�j�@@����<BR>
    * �@@�X�e�[�^�X in ("0�F����I��","1�F������")�̏ꍇ�A��O���X���[����B<BR>
    *       class: WEB3BusinessLayerException<BR>
    *       tag:   BUSINESS_ERROR_02309<BR>
    * <BR>
    * �@@�G���[���b�Z�[�W�u���I�����͌��ʊm�蒆�A<BR>
    *   �������͌��ʊm�肪�I�����Ă��܂��B�v<BR>
    * @@param l_ipoBookbuildingRow - (IPO���IRow)
    * @@throws WEB3BaseException
    */
    protected void validateLotState(IpoBookbuildingRow l_ipoBookbuildingRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateLotState(IpoBookbuildingRow)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����:IPO���IRow���珈���󋵂��擾
        String l_strProcess = l_ipoBookbuildingRow.getProcess();
        
        //�Q�|�Q�j�@@����.IPO���IRow����X�e�[�^�X���擾
        String l_strStatus = l_ipoBookbuildingRow.getStatus();
        
        //�Q�|�R�j�@@if ������ in �i"01�F���I����","02�F�f�[������t",
        //   "03�F���I�J�n",
        // �@@"11�F�m�����","12�F�f�[������t","13�F�m��J�n"�j�@@����
        // �@@�X�e�[�^�X��"9�F�ُ�I��"�̏ꍇ�A��O���X���[����B
        if ((WEB3IPOBookBuldingProcDef.LOT_INPUT.equals(l_strProcess) ||
            WEB3IPOBookBuldingProcDef.LOT_DAEMON_ACCEPT.equals(l_strProcess) ||
            WEB3IPOBookBuldingProcDef.LOT_BEGIN.equals(l_strProcess) || 
            WEB3IPOBookBuldingProcDef.CONFIRM_INPUT.equals(l_strProcess) ||
            WEB3IPOBookBuldingProcDef.CONFIRM_DAEMON_ACCEPT.equals(l_strProcess) || 
            WEB3IPOBookBuldingProcDef.CONFIRM_BEGIN.equals(l_strProcess)) &&
            (WEB3IPOBookBuldingStatusDef.ABNORMAL_END.equals(l_strStatus)))
        {
            log.debug("���I�������ɃG���[���������܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02310,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "���I�������ɃG���[���������܂����B");
        }
        
        //�Q�|�S�j�@@if ������ in �i"11�F�m�����","12�F�f�[������t",
        //   "13�F�m��J�n","14�F�m��I��"�j�@@����
        // �@@�X�e�[�^�X in ("0�F����I��","1�F������")�̏ꍇ�A
        //   ��O���X���[����B
        if ((WEB3IPOBookBuldingProcDef.CONFIRM_INPUT.equals(l_strProcess) || 
            WEB3IPOBookBuldingProcDef.CONFIRM_DAEMON_ACCEPT.equals(l_strProcess) ||
            WEB3IPOBookBuldingProcDef.CONFIRM_BEGIN.equals(l_strProcess) || 
            WEB3IPOBookBuldingProcDef.CONFIRM_END.equals(l_strProcess)) &&
            (WEB3IPOBookBuldingStatusDef.NORMAL_END.equals(l_strStatus) || 
            WEB3IPOBookBuldingStatusDef.DEALING.equals(l_strStatus)))
        {
            log.debug("���I�����͌��ʊm�蒆�A�������͌��ʊm�肪�I�����Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02309,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "���I�����͌��ʊm�蒆�A�������͌��ʊm�肪�I�����Ă��܂��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
   /** 
    * (validate�m���)
    * �m��󋵂̊m�F���s���A�G���[��ʂ�\������ꍇ�͗�O��<BR>
    *  �X���[����B<BR>
    * <BR>
    * �@@�P�j�@@����:IPO���IRow���珈���󋵂��擾<BR>
    * <BR>
    * �@@�Q�j�@@if ������ != �i"11�F�m�����","12�F�f�[������t",<BR>
    *"13�F�m��J�n", "14�F�m��I��")�̏ꍇ�A��O���X���[����B<BR>
    *              class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02323<BR>
    * �G���[���b�Z�[�W�u�m�菈���������Ȃ���񂪂���܂���B�v<BR>
    * @@param l_ipoBookbuildingRow - (IPO���IRow)
    * @@throws WEB3BaseException
    */
    protected void validateConfirmState(IpoBookbuildingRow l_ipoBookbuildingRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateConfirmState(IpoBookbuildingRow)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����:IPO���IRow���珈���󋵂��擾
        String l_strProcess = l_ipoBookbuildingRow.getProcess();
        
        //�Q�j�@@if ������ != �i"11�F�m�����","12�F�f�[������t",
        //"13�F�m��J�n", "14�F�m��I��")�̏ꍇ�A��O���X���[����B
        // �G���[���b�Z�[�W�u�m�菈���������Ȃ���񂪂���܂���B�v
        if (!WEB3IPOBookBuldingProcDef.CONFIRM_INPUT.equals(l_strProcess) && 
            !WEB3IPOBookBuldingProcDef.CONFIRM_BEGIN.equals(l_strProcess) && 
            !WEB3IPOBookBuldingProcDef.CONFIRM_END.equals(l_strProcess) &&  
            !WEB3IPOBookBuldingProcDef.CONFIRM_DAEMON_ACCEPT.equals(l_strProcess))
        {
            log.debug("�m�菈���������Ȃ���񂪂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02323,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�m�菈���������Ȃ���񂪂���܂���B");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (cloneIPO���IRows)<BR>
     * ������IPO���IRow�m�n���R�s�[���āA <BR>
     * �������e�̕ʃC���X�^���X���쐬���A�ԋp����B <BR>
     * <BR>
     * �P�j�@@ArrayList�N���X�I�u�W�F�N�g����<BR>
     * <BR>
     * �Q�j�@@����:IPO���IRow[]�̗v�f���ALoop�������s���B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@IPO���IRow cloneRow = new IPO���IParams(IPO���IRow[i])���s���A<BR>
     * �@@�@@�@@�@@�@@�@@Row�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@ArrayList�N���X�I�u�W�F�N�g.add(cloneRow);<BR>
     * <BR>
     * �R�j�@@ArrayList�N���X�I�u�W�F�N�g��IPO���IRow[]�ɕϊ�����B<BR>
     * <BR>
     * �S�j�@@�R�j�ŕϊ�����IPO���IRow[]��ԋp����B<BR>
     * @@param l_ipoBookbuildingRows - IPO���IRows<BR>
     * IPO���IRow[]�I�u�W�F�N�g�B
     * @@return IPO���IRow[]
     * @@throws WEB3BaseException
     */
    protected IpoBookbuildingRow[] cloneIpoBookBuildingRows(IpoBookbuildingRow[] l_ipoBookbuildingRows)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " cloneIpoBookBuildingRows(IpoBookbuildingRow[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoBookbuildingRows == null || l_ipoBookbuildingRows.length == 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        
        //�P�j�@@ArrayList�N���X�I�u�W�F�N�g����
        List l_lisIpoBookBuildingRows = new ArrayList();
        
        int l_intLength = l_ipoBookbuildingRows.length;
        //�Q�j�@@����:IPO���IRow[]�̗v�f���ALoop�������s���B
        for (int i = 0; i < l_intLength; i++)
        {
            //�Q�|�P�j�@@IPO���IRow cloneRow = new IPO���IParams(IPO���IRow[i])���s���A
            //�@@�@@�@@�@@�@@Row�I�u�W�F�N�g�𐶐�����B
            IpoBookbuildingRow l_cloneRow = new IpoBookbuildingParams(l_ipoBookbuildingRows[i]);
            
            //�Q�|�Q�j�@@ArrayList�N���X�I�u�W�F�N�g.add(cloneRow);
            l_lisIpoBookBuildingRows.add(l_cloneRow);
        }
        
        //�R�j�@@ArrayList�N���X�I�u�W�F�N�g��IPO���IRow[]�ɕϊ�����B
        IpoBookbuildingRow[] l_cloneIpoBookBuildingRows = new IpoBookbuildingRow[l_intLength];
        
        //�S�j�@@�R�j�ŕϊ�����IPO���IRow[]��ԋp����B
        l_lisIpoBookBuildingRows.toArray(l_cloneIpoBookBuildingRows);
        
        log.exiting(STR_METHOD_NAME);
        return l_cloneIpoBookBuildingRows;
    }
}
@
