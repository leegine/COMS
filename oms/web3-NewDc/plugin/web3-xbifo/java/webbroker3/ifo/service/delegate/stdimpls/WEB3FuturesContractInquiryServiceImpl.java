head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesContractInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨���ʏƉ�T�[�r�XImpl(WEB3FuturesContractInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 ���� (���u) �V�K�쐬
            001: 2004/09/1 ����(���u) UT-000341��Ή����܂����B
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.message.WEB3FuturesContractDivisionComparator;
import webbroker3.ifo.message.WEB3FuturesContractReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesContractReferenceResponse;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOpenDateComparator;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3FuturesProductCodeComparator;
import webbroker3.ifo.message.WEB3FuturesProfitAndLossComparator;
import webbroker3.ifo.service.delegate.WEB3FuturesContractInquiryService;

/**
 * (�敨���ʏƉ�T�[�r�XImpl)<BR>
 * �����w���敨���ʏƉ�T�[�r�X�����N���X
 * @@author ����
 * @@version 1.0
 */
public class WEB3FuturesContractInquiryServiceImpl extends WEB3FuturesClientRequestService 
    implements WEB3FuturesContractInquiryService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesContractInquiryServiceImpl.class);
    /**
     * @@roseuid 40F7A2C30290
     */
    public WEB3FuturesContractInquiryServiceImpl() 
    {
     
    }
    
    /**
     * �����w���敨���ʏƉ�T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨���ʏƉ�T�[�r�X�j���ʏƉ�v�Q�ƁB<BR>
     * ==========================================================<BR>
     * �V�[�P���X�}(�u�i�敨���ʏƉ�T�[�r�X�j���ʏƉ�v): 8 get����(Institution, String)<BR>
     * (�����R�[�h�`�F�b�N)<BR>
     * get�����Ŏw��̖����R�[�h���擾�ł��Ȃ������ꍇ�́A<BR>
     *�u�����R�[�h�`�F�b�N�G���[�v�̗�O��throw����B<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00301<BR>
     * ==========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FuturesContractReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A996300103
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
    throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "execute(WEB3FuturesContractReferenceRequest)";

        log.entering(STR_METHOD_NAME);
        
        if (!(l_request instanceof WEB3FuturesContractReferenceRequest))
        {             
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                 
        }
        //2.validate()      
        WEB3FuturesContractReferenceRequest l_request0 = (WEB3FuturesContractReferenceRequest) l_request;
        l_request0.validate();

        //3.get�⏕����()           
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
        log.debug("3.get�⏕����() : " + l_subAccount);

        //4.�V�X�e��������~(�o�b�`���A�ً}��~��)�`�F�b�N�����{����B            
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //5.getInstance()            
        WEB3IfoOrderManagerReusableValidations l_managerReusableValidations =
            (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();
        log.debug("5.getInstance() : " + l_managerReusableValidations);

        //6.validate�敨OP�����J��(�⏕���� : �⏕����, �敨�^�I�v�V�����敪 : String)           
        l_managerReusableValidations.validateFuturesOptionAccountOpen(
            l_subAccount,
            WEB3FuturesOptionDivDef.FUTURES);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
       
        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        log.debug("get : l_orderMgr = " + l_orderMgr);       
       
        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
        log.debug("get : l_productMgr = " + l_productMgr);
        
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
        log.debug("get : l_positionManager = " + l_positionManager);
        
        //7.(*1)����t���[
        //���N�G�X�g�f�[�^�ɖ����R�[�h���Z�b�g����Ă����̂݁A
        //�ȉ��̏��������{����B
        //8.get����(Institution, String)            
        //NotFoundException
        if (l_request0.futProductCode != null && !"".equals(l_request0.futProductCode))
        {   
            try
            {          
                log.debug("l_subAccount.getInstitution() = " + l_subAccount.getInstitution().getInstitutionCode());                 
                WEB3IfoProductImpl l_product = l_productMgr.getIfoProduct(l_subAccount.getInstitution(), l_request0.futProductCode);
    
                log.debug("get : l_product = " + l_product);
            }        
            catch (NotFoundException l_ex)
            {
                String msg = "�Y�������Ȃ��B";
                log.error(msg);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //9.is����\�ڋq(�⏕���� : �⏕����)
        boolean l_blnIsTradedPossibleCustomer =
            l_orderMgr.isTradedPossibleCustomer(l_subAccount);
        log.debug("9.is����\�ڋq : =" + l_blnIsTradedPossibleCustomer);

        WEB3FuturesOptionsProductCodeNameUnit[] l_productCodeNameUnits = 
            l_positionManager.createProductCodeNameFromContract(
                l_subAccount,
                false,
                WEB3FuturesOptionDivDef.FUTURES);                
        log.debug("10.create�����R�[�h����from���� : = " + l_productCodeNameUnits);               

        //16.createResponse( )
        WEB3FuturesContractReferenceResponse l_response =
            (WEB3FuturesContractReferenceResponse)l_request0.createResponse();

        //(�Y���f�[�^�`�F�b�N)
        //create�����R�[�h����from���ʂ̕Ԃ�l��NULL�̏ꍇ�A
        //���X�|���X�쐬�A�v���p�e�B�Z�b�g�������s���B
        //�i���ʏƉ�׍쐬�A�\�[�g�����A�y�[�W���O�����͍s��Ȃ��j
        if (l_productCodeNameUnits == null)
        {
            //(1)�Y���f�[�^�Ȃ��̏ꍇ(create�����R�[�h����from���ʂ̕Ԃ�l��NULL)
            //���X�|���X.���ʏƉ�� = NULL
            //���X�|���X.���y�[�W�� = 0
            //���X�|���X.�����R�[�h�� = 0
            //���X�|���X.�\���y�[�W�ԍ� = 0
            //���X�|���X.�����w���敨�I�v�V���������R�[�h���� = NULL
            //���X�|���X.�ڋq���b�N�敪 = is����\�ڋq�̕Ԃ�l��false�Ȃ�true
            l_response.contractReferenceUnits = null;
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";
            l_response.futOpProductCodeNames = null;
            if (l_blnIsTradedPossibleCustomer)
            {
                l_response.accountLock = false;
            }
            else
            {
                l_response.accountLock = true;
            }
         
            log.exiting(STR_METHOD_NAME);

            return l_response;
        }



        //11.create��������������(String)
        String l_searchCondString = createSearchCondCharacter(l_request0.futProductCode);
        log.debug("11.create�������������� : = " + l_searchCondString);

        //12.create���������f�[�^�R���e�i(String)
        //NotFoundException, WEB3BaseException           
        String[] l_searchCondDataContainer =
            createSearchCondDataContainer(l_request0.futProductCode);
        log.debug("12.create���������f�[�^�R���e�i : = " + l_searchCondDataContainer);
        
        //13.create�敨���ʏƉ��(�⏕����, String, String, String, String[])        
        //���ʏƉ��ʂɕ\�����錚�ʏƉ�ׂ̈ꗗ���쐬����B
        WEB3FuturesContractReferenceUnit[] l_contractReferenceUnits = 
            l_positionManager.createFuturesContractInquiryDetails(
                l_subAccount,
                WEB3FuturesOptionDivDef.FUTURES,
                l_request0.settlementState,
                l_searchCondString,
                l_searchCondDataContainer);           

        log.debug("13.create�敨���ʏƉ�� : = " + l_contractReferenceUnits);

        //(���������Y���f�[�^�`�F�b�N)
        //create�敨���ʏƉ�ׂ̕Ԃ�l��NULL�̏ꍇ�A
        //���X�|���X�쐬�A�v���p�e�B�Z�b�g�������s���B
        //(�\�[�g�����A�y�[�W���O�����͍s��Ȃ�)
        if (l_contractReferenceUnits == null)
        {
            //(2)���������Y���f�[�^�Ȃ��̏ꍇ(create���ʏƉ�ׂ̕Ԃ�l��NULL�̏ꍇ)

            //���X�|���X.���ʏƉ�� = NULL
            //���X�|���X.���y�[�W�� = 0
            //���X�|���X.�����R�[�h�� = 0
            //���X�|���X.�\���y�[�W�ԍ� = 0
            //���X�|���X.�����w���敨�I�v�V���������R�[�h���� = create�����R�[�h����from���ʂ̕Ԃ�l
            //���X�|���X.�ڋq���b�N�敪 = is����\�ڋq�̕Ԃ�l��false�Ȃ�true
            l_response.contractReferenceUnits = null;
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";
            l_response.futOpProductCodeNames = l_productCodeNameUnits;
            if (l_blnIsTradedPossibleCustomer)
            {
                l_response.accountLock = false;
            }
            else
            {
                l_response.accountLock = true;
            }
          
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //14.sort���ʏƉ��(�����w���敨���ʏƉ��[], �����w���敨�I�v�V�����\�[�g�L�[[])            
        WEB3FuturesContractReferenceUnit[] l_sortedContractReferenceUnit =
            sortContractReferenceUnit(l_contractReferenceUnits, l_request0.futOpSortKeys);
        log.debug("14.sort���ʏƉ�� : = " + l_sortedContractReferenceUnit.length);

        //15.create�y�[�W(�����w���敨���ʏƉ�N�G�X�g, �����w���敨���ʏƉ��[])            
        WEB3FuturesContractReferenceUnit[] l_createPage =
            createPage(l_request0, l_sortedContractReferenceUnit);
        log.debug("15.create�y�[�W : = " + l_createPage);

        //(*2)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�Y���f�[�^�`�F�b�N�A���������Y���f�[�^�`�F�b�N���ʂɂ��ƂÂ��A�Z�b�g�����v���p�e�B���قȂ�B

        //(3)(1)(2)�ȊO�̏ꍇ

        //���X�|���X.���ʏƉ�� = create�y�[�W�̕Ԃ�l
        //���X�|���X.�����R�[�h�� = create���ʏƉ�ׂ̕Ԃ�l�̃T�C�Y�@@
        //���X�|���X.���y�[�W�� = �����R�[�h�� �� ���N�G�X�g�f�[�^.�y�[�W���\���s�� ���v�Z���ʂ�1�����A�܂��͗]�肪���݂���ꍇ�ɂ͍X��1�����Z����
        //���X�|���X.�\���y�[�W�ԍ� = ���N�G�X�g�f�[�^.�v���y�[�W�ԍ�
        //�����X�|���X.�����R�[�h�� <= (���N�G�X�g�f�[�^.�y�[�W���\���s�� * (���N�G�X�g�f�[�^.�v���y�[�W�ԍ� - 1))�̏ꍇ�́A���X�|���X.���y�[�W�����Z�b�g(�ŏI�y�[�W�ԍ����Z�b�g)
        //���X�|���X.�����w���敨�I�v�V���������R�[�h���� = ��reate�����R�[�h����from���ʂ̕Ԃ�l
        //���X�|���X.�ڋq���b�N�敪 = is����\�ڋq�̕Ԃ�l��false�Ȃ�true  �����b�N�ڋq�̏ꍇ�Ftrue�A���b�N�ڋq�łȂ��ꍇ�Ffalse�ƂȂ�
        int l_intTotalRecords = l_contractReferenceUnits.length;
        int l_intPageSize = Integer.parseInt(l_request0.pageSize);
        int l_intTotalPages = l_intTotalRecords / l_intPageSize;
        int l_intRemains = l_intTotalRecords % l_intPageSize;
        
        l_response.contractReferenceUnits = l_createPage;
        l_response.totalRecords = Integer.toString(l_intTotalRecords);            

        if (l_intRemains != 0)
        {
            l_intTotalPages += 1;
        }
        
        l_response.totalPages = Integer.toString(l_intTotalPages);
        l_response.pageIndex = l_request0.pageIndex;
        if (l_intTotalRecords
            <= (l_intPageSize * (Integer.parseInt(l_request0.pageIndex) - 1)))
        {
            l_response.pageIndex = l_response.totalPages;
        }
        l_response.futOpProductCodeNames = l_productCodeNameUnits;
        
        if (l_blnIsTradedPossibleCustomer)
        {
            l_response.accountLock = false;
        }
        else
        {
            l_response.accountLock = true;
        }
       
        log.exiting(STR_METHOD_NAME);
        return l_response;    

    }
    
    /**
     * (create��������������)<BR>
     * ���N�G�X�g�f�[�^�����ƂɁA���������iwhere�ȉ��w��̕�����j<BR>
     * ���쐬����B<BR>
     * <BR>
     * (1)�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�<BR>
     * <BR>
     * (2)�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ�A<BR>
     * ����ID�w��𕶎���C���X�^���X�ɒǉ��i�����R�[�h�ɑΉ�<BR>
     * �������ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@" and product_id = ?"<BR>
     * <BR>
     * (3)������C���X�^���X��ԋp<BR>
     * �i�p�����[�^.�����R�[�h��NULL�̏ꍇ�ANULL��ԋp����j<BR>
     * @@param l_strProductCode - �����R�[�h
     * @@return String
     * @@roseuid 40A995010122
     */
    protected String createSearchCondCharacter(String l_strProductCode) 
    {
        final String STR_METHOD_NAME =
            "createSearchCondCharacter(String)";

        log.entering(STR_METHOD_NAME);
        //(1)�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�<BR>
        String l_strReturnValue = null;

        if (l_strProductCode != null && !"".equals(l_strProductCode))
        {          
            l_strReturnValue = " and product_id = ?";  
            log.exiting(STR_METHOD_NAME);
            return l_strReturnValue;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_strReturnValue;
        }
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���N�G�X�g�f�[�^����A���������iwhere�ȉ��w��̕�����j��<BR>
     * �p�����[�^���X�g���쐬����B<BR>
     * <BR>
     * (1)������z����쐬<BR>
     * <BR>
     * (2)�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ�A����ID��<BR>
     * �@@�@@������z��ɃZ�b�g�i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@����ID �� �敨OP�v���_�N�g�}�l�[�W��.get����<BR>
     * (�،���ЃI�u�W�F�N�g(*1), �p�����[�^.�����R�[�h).����ID<BR>
     * <BR>
     * (*1)�،���ЃI�u�W�F�N�g�́A�⏕����.getInstitution( )�Ŏ擾���ݒ�<BR>
     * <BR>
     * (3)������z���ԋp<BR>
     * �i�p�����[�^.�����R�[�h��NULL�̏ꍇ�ANULL��ԋp����j<BR>
     * @@param l_strProductCode - �����R�[�h
     * @@return String[]
     * @@roseuid 40A995010131
     */
    protected String[] createSearchCondDataContainer(String l_strProductCode) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createSearchCondDataContainer(String)";

        log.entering(STR_METHOD_NAME);
        //(1)������z����쐬
        String[] l_strReturnValue = new String[1];

        //(2)�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ�A����ID��<BR>
        //�@@�@@������z��ɃZ�b�g�i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
        if (l_strProductCode != null && !"".equals(l_strProductCode))
        {

            String l_strProductId = null;           
            
            //(*1)�،���ЃI�u�W�F�N�g�́A�⏕����.getInstitution( )�Ŏ擾���ݒ�
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();       
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
     
            WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
            log.debug("get : l_productMgr = " + l_productMgr);         
            try
            {
                l_strProductId =
                    Long.toString(
                        l_productMgr.getIfoProduct(l_subAccount.getInstitution(), 
                                                   l_strProductCode).getProductId());

                l_strReturnValue[0] = l_strProductId;    
                log.debug("reuturn value = " + l_strProductId);
            }
            catch (NotFoundException l_ex)
            {
                String msg = "�Y�������Ȃ��B";
                log.error(msg);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);

            }
            log.exiting(STR_METHOD_NAME);
            return l_strReturnValue;       
          
        }
        else
        {   // (3)������z���ԋp<BR>
            // �i�p�����[�^.�����R�[�h��NULL�̏ꍇ�ANULL��ԋp����j<BR>
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    /**
     * (create�y�[�W)<BR>
     * �v���y�[�W�ԍ��ɊY�����銔���w���敨���ʏƉ�ׂ̔z���<BR>
     * �쐬����B<BR>
     * <BR>
     * (1)�y�[�W���\���s���A�v���y�[�W�ԍ��̎擾<BR>
     * �y�[�W���\���s�� = �p�����[�^.���N�G�X�g�f�[�^.�y�[�W���\���s��<BR>
     * �v���y�[�W�ԍ� = �p�����[�^.���N�G�X�g�f�[�^.�v���y�[�W�ԍ�<BR>
     * <BR>
     * (1)�v���y�[�W�J�n�ʒu������<BR>
     * fromIndex = �y�[�W���\���s�� * (�v���y�[�W�ԍ� - 1)<BR>
     * <BR>
     * (2)�v���y�[�W�I���ʒu������<BR>
     * toIndex = (�y�[�W���\���s�� * �v���y�[�W�ԍ�) - 1<BR>
     * <BR>
     * ���p�����[�^.���ʏƉ�ׂ̗v�f�� <= fromIndex�̏ꍇ�A<BR>
     * (�����R�[�h�����v���y�[�W�ԍ��ɖ����Ȃ��ꍇ)<BR>
     * fromIndex =�@@�p�����[�^.���ʏƉ�ׂ̗v�f�� - �y�[�W���\���s��<BR>
     * toIndex = �p�����[�^.���ʏƉ�ׂ̗v�f�� - 1<BR>
     * <BR>
     * (3)ArrayList���쐬<BR>
     * <BR>
     * (4)�p�����[�^.���ʏƉ�א���Loop����<BR>
     * <BR>
     * ���ʏƉ�ׂ̃C���f�b�N�X��<BR>
     * fromIndex��toIndex�͈͓̔�(fromIndex�ȏ�AtoIndex�ȉ�)<BR>
     * �ł���ꍇ�́A<BR>
     * �쐬����ArrayList�Ɍ��ʏƉ�׃I�u�W�F�N�g��ǉ�<BR>
     * <BR>
     * (5)ArrayList.toArray�ŊY���y�[�W�ԍ��̌��ʏƉ�ׂ̔z���ԋp����
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���敨���ʏƉ�N�G�X�g�I�u�W�F�N�g
     * @@param l_contractReferenceUnit - (���ʏƉ��)<BR>
     * �����w���敨���ʏƉ�ׂ̔z��<BR>
     * (�����R�[�h�����̔z��)
     * @@return WEB3FuturesContractReferenceUnit[]
     * @@roseuid 40A995010141
     */
    protected WEB3FuturesContractReferenceUnit[] createPage(
        WEB3FuturesContractReferenceRequest l_request, 
        WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit) 
    {
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
        l_contractReferenceUnit, 
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));
                       
         return (WEB3FuturesContractReferenceUnit[])l_pageIndexInfo.getArrayReturned(WEB3FuturesContractReferenceUnit.class);
    }
    
    /**
     * (sort���ʏƉ��)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Č��ʏƉ�ׂ̃\�[�g���s���B<BR>
     * <BR>
     * (1)ArrayList���쐬<BR>
     * <BR>
     * (2)�p�����[�^.�\�[�g�L�[�̔z�񐔕�Loop����<BR>
     * �@@(2-1)�p�����[�^.�\�[�g�L�[.�L�[���ڂ��擾<BR>
     * <BR>
     * �@@(2-2)�p�����[�^.�\�[�g�L�[.����/�~�����擾<BR>
     * <BR>
     * �@@(2-3)�L�[���ڂɂ�镪�򏈗�<BR>
     * �@@�@@�L�[���ڂ������R�[�h�ł������ꍇ�A�敨�����R�[�hComparator<BR>
     * �𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����v�ł������ꍇ�A�敨���vComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ������ł������ꍇ�A�敨����Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����敪�ł������ꍇ�A�敨���敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@(2-4)(2-3)�ɂč쐬����Comparator��ArrayList�ɒǉ�<BR>
     * <BR>
     * (3)ArrayList����Comparator�̔z����쐬<BR>
     * <BR>
     * (4)Comparator�̔z�񏇂̃\�[�g����<BR>
     * (web3-common)WEB3ArraysUtility.sort(�p�����[�^.���ʏƉ�ׁA<BR>Comparator[])<BR>
     * <BR>
     * (5)�\�[�g���ꂽ���ʏƉ�ׂ̔z���ԋp<BR>
     * @@param l_contractReferenceUnit - (���ʏƉ��)<BR>
     * �����w���敨���ʏƉ�ׂ̔z��
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * �����w���敨�I�v�V�����\�[�g�L�[�̔z��
     * @@return WEB3FuturesContractReferenceUnit[]
     * @@roseuid 40A995010180
     */
    protected WEB3FuturesContractReferenceUnit[] sortContractReferenceUnit(
        WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit, 
        WEB3FuturesOptionsSortKey[] l_sortKey) 
    {
        final String STR_METHOD_NAME =
            "sortContractReferenceUnit(WEB3FuturesContractReferenceUnit[], WEB3FuturesOptionsSortKey[])";

        log.entering(STR_METHOD_NAME);
        //(1)ArrayList���쐬
        List l_lisComparator = new ArrayList();

        //(2)�p�����[�^.�\�[�g�L�[�̔z�񐔕�Loop����
        int l_intSortKeyLength = l_sortKey.length;
        log.debug("�p�����[�^.�\�[�g�L�[�̗̂v�f�� = " + l_intSortKeyLength);

        String l_strKeyItem; //�p�����[�^.�\�[�g�L�[.�L�[����
        String l_strAscDesc; //�p�����[�^.�\�[�g�L�[.����/�~��
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            //�@@(2-1)�p�����[�^.�\�[�g�L�[.�L�[���ڂ��擾
            l_strKeyItem = l_sortKey[i].keyItem;
            log.debug("�p�����[�^.�\�[�g�L�[.�L�[���� " + i + 1 + " = " + l_strKeyItem);

            //�@@(2-2)�p�����[�^.�\�[�g�L�[.����/�~�����擾
            l_strAscDesc = l_sortKey[i].ascDesc;
            log.debug("�p�����[�^.�\�[�g�L�[.����/�~�� " + i + 1 + " = " + l_strAscDesc);

            //�@@(2-3)�L�[���ڂɂ�镪�򏈗�
            //�@@�@@�L�[���ڂ������R�[�h�ł������ꍇ�A�����R�[�hComparator�𐶐�
            if (WEB3IfoKeyItemDef.FUTPRODUCTCODE.equals(l_strKeyItem))
            {               
                WEB3FuturesProductCodeComparator l_productCodeComparator =
                    new WEB3FuturesProductCodeComparator(l_strAscDesc);
                //�쐬����Comparator��ArrayList�ɒǉ�
                l_lisComparator.add(l_productCodeComparator); 
            }
            //�L�[���ڂ����v�ł������ꍇ�A���vComparator�𐶐�
            else if (WEB3IfoKeyItemDef.INCOME.equals(l_strKeyItem))
            {                
                WEB3FuturesProfitAndLossComparator l_profitAndLossComparator =
                    new WEB3FuturesProfitAndLossComparator(l_strAscDesc);
                l_lisComparator.add(l_profitAndLossComparator);
            }
            //�L�[���ڂ������ł������ꍇ�A����Comparator�𐶐�
            else if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {              
                WEB3FuturesOpenDateComparator l_openDateComparator =
                    new WEB3FuturesOpenDateComparator(l_strAscDesc);
                l_lisComparator.add(l_openDateComparator);
            }
            //�L�[���ڂ����敪�ł������ꍇ�A���敪Comparator�𐶐�
            else if (WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(l_strKeyItem))
            {                
                WEB3FuturesContractDivisionComparator l_contractDivisionComparator =
                    new WEB3FuturesContractDivisionComparator(l_strAscDesc);
                l_lisComparator.add(l_contractDivisionComparator);
            }
        }

        //(3)ArrayList����Comparator�̔z����쐬
      
        Comparator[] l_comparators = new Comparator[l_lisComparator.size()];
        l_lisComparator.toArray(l_comparators);

        //(4)Comparator�̔z�񏇂̃\�[�g����        
        WEB3ArraysUtility.sort(l_contractReferenceUnit, l_comparators);
        
        log.exiting(STR_METHOD_NAME);
        return l_contractReferenceUnit;
    }    

}
@
