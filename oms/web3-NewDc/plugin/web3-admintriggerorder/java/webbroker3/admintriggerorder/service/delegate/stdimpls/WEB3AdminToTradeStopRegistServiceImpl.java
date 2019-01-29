head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�XImpl(WEB3AdminToTradeStopRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04  �A����(���u) �V�K�쐬
                 : 2006/04/12  �A����(���u) �d�l�ύX�E���f��052
*/
package webbroker3.admintriggerorder.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInfoUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegConfirmResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3TargetTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.triggerorder.base.data.TriggerOrderStopDao;
import webbroker3.triggerorder.base.data.TriggerOrderStopParams;
import webbroker3.triggerorder.base.data.TriggerOrderStopRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�XImpl)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�X�����N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminToTradeStopRegistServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminToTradeStopRegistService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopRegistServiceImpl.class);

    /**
     * @@roseuid 4430DC770138
     */
    public WEB3AdminToTradeStopRegistServiceImpl() 
    {
     
    }
    
    /**
     * �戵��~�o�^�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���g���K�[�����Ǘ��ҁE�戵��~�o�^�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.validate�o�^()���R�[������B<BR>
     * <BR>
     * ���g���K�[�����Ǘ��ҁE�戵��~�o�^�������N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�o�^()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410D4D70155
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

        WEB3GenResponse l_response = null;
        //���g���K�[�����Ǘ��ҁE�戵��~�o�^�m�F���N�G�X�g�̏ꍇ
        // �@@this.validate�o�^()���R�[������B
        if (l_request instanceof WEB3AdminToTradeStopRegConfirmRequest)
        {
            l_response = this.validateRegist((WEB3AdminToTradeStopRegConfirmRequest) l_request);
        }
        // ���g���K�[�����Ǘ��ҁE�戵��~�o�^�������N�G�X�g�̏ꍇ
        // �@@this.submit�o�^()���R�[������B
        else if (l_request instanceof WEB3AdminToTradeStopRegCompleteRequest)
        {
            l_response = this.submitRegist((WEB3AdminToTradeStopRegCompleteRequest) l_request);
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
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�o�^)<BR>
     * �戵��~�o�^�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�X�jvalidate�o�^�v�Q�ƁB<BR>
     * ============================================================== <BR>
     *    �V�[�P���X�}�F�u�i�g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�X�jvalidate�o�^�v<BR>
     *    ��̈ʒu�F<BR>
     *    1.5 getProduct(arg0 : Institution, arg1 : �_���r���[::java::lang::String)<BR>
     *    �擾�ł��Ȃ������ꍇ�A<BR>
     *    �u�����ɊY������f�[�^�����݂��Ȃ��B�v��<BR>
     *    ��O���X���[����B<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_01037<BR>
     * ============================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�o�^�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopRegConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410D704009A
     */
    protected WEB3AdminToTradeStopRegConfirmResponse validateRegist(WEB3AdminToTradeStopRegConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRegist(WEB3AdminToTradeStopRegConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, true);
        
        //1.4 get�،����( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.5 getProduct(arg0 : Institution, arg1 : �_���r���[::java::lang::String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productMgr = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        EqTypeProduct l_eqtypeProduct = null;
        try
        {
            l_eqtypeProduct = l_productMgr.getProduct(l_institution, l_request.tradeStopInfoUnit.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����ɊY������f�[�^�����݂��Ȃ��B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }

        //1.6 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7 validate�d���f�[�^(String, �戵��~���) 
        this.validateDuplicateData(l_strInstitutionCode, l_request.tradeStopInfoUnit);
        
        //1.8 createResponse( )
        WEB3AdminToTradeStopRegConfirmResponse l_response = 
            (WEB3AdminToTradeStopRegConfirmResponse) l_request.createResponse();
        
        //1.9 (*)�v���p�e�B�Z�b�g
        //������   ���@@getProduct()�̖߂�l.getDataSourceObject().������
        l_response.productName = ((EqtypeProductRow) l_eqtypeProduct.getDataSourceObject()).getStandardName();
        
        //1.10 
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�o�^)<BR>
     * �戵��~�o�^�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�X�jsubmit�o�^�v�Q�ƁB<BR>
     * ============================================================== <BR>
     *    �V�[�P���X�}�F�u�i�g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�X�jsubmit�o�^�v<BR>
     *    ��̈ʒu�F<BR>
     *    1.6 getProduct(arg0 : Institution, arg1 : �_���r���[::java::lang::String)   <BR>
     *    �擾�ł��Ȃ������ꍇ�A<BR>
     *    �u�����ɊY������f�[�^�����݂��Ȃ��B�v��<BR>
     *    ��O���X���[����B<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_01037<BR>
     * ============================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�o�^�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopRegCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410D70400B9
     */
    protected WEB3AdminToTradeStopRegCompleteResponse submitRegist(WEB3AdminToTradeStopRegCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRegist(WEB3AdminToTradeStopRegCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, true);
        
        //1.4 validate����p�X���[�h(�p�X���[�h : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5 get�،����( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.6 getProduct(arg0 : Institution, arg1 : �_���r���[::java::lang::String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productMgr = (WEB3EquityProductManager) l_tradingModule.getProductManager();

        try
        {
            l_productMgr.getProduct(l_institution, l_request.tradeStopInfoUnit.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����ɊY������f�[�^�����݂��Ȃ��B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        //1.7 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.8 validate�d���f�[�^(String, �戵��~���)
        this.validateDuplicateData(l_strInstitutionCode, l_request.tradeStopInfoUnit);
        
        //1.9 submit�V�K�戵��~(�Ǘ���, �戵��~���)
        this.submitNewTradeStop(l_admin, l_request.tradeStopInfoUnit);
        
        //1.10 createResponse( )
        WEB3AdminToTradeStopRegCompleteResponse l_response =
            (WEB3AdminToTradeStopRegCompleteResponse) l_request.createResponse();
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        //1.11
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�d���f�[�^)<BR>
     * ���͂��ꂽ�戵��~��񂪊��ɓo�^�ς݂��ǂ���<BR>
     * �`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�ȉ��̏����ŁA���ꎷ�s�����戵��~�e�[�u����<BR>
     * �@@��������B<BR>
     * �@@�@@�y���������z<BR>
     * �@@�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@And ���X�R�[�h = "000"<BR>
     * �@@�@@�@@And �ݒ�Ώێ�� = "����"<BR>
     * �@@�@@�@@And �L�[��� = �p�����[�^.�戵��~���.�����R�[�h<BR>
     * �@@�@@�@@And �L������From = �p�����[�^.�戵��~���.�L������From<BR>
     * �@@�@@�@@And �폜�t���O = "DEFAULT"<BR>
     * <BR>
     * �Q�j�@@�������ʂ��擾�ł����ꍇ�A<BR>
     * �@@�u���͂����戵��~���͊��ɓo�^�ρv�̗�O���X���[����B<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_02432<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_tradeStopInfoUnit - (�戵��~���)<BR>
     * �戵��~���<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44113CBE005B
     */
    protected void validateDuplicateData(String l_strInstitutionCode, WEB3AdminToTradeStopInfoUnit l_tradeStopInfoUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateData(String, WEB3AdminToTradeStopInfoUnit)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@DB����
        // �@@�ȉ��̏����ŁA���ꎷ�s�����戵��~�e�[�u������������B
        // �@@�@@�y���������z
        // �@@�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
        // �@@�@@�@@And ���X�R�[�h = "000"
        // �@@�@@�@@And �ݒ�Ώێ�� = "����"
        // �@@�@@�@@And �L�[��� = �p�����[�^.�戵��~���.�����R�[�h
        // �@@�@@�@@And �L������From = �p�����[�^.�戵��~���.�L������From
        // �@@�@@�@@And �폜�t���O = "DEFAULT"
        String l_strWhere = " institution_code = ? and branch_code = ? and target_type = ? and key = ? ";
        l_strWhere += "and valid_term_from = ? and delete_flag = ? ";
        
        Object[] l_objValues = new Object[] {
            l_strInstitutionCode,
            WEB3BranchCodeDef.DEFAULT,
            WEB3TargetTypeDef.PRODUCT,
            l_tradeStopInfoUnit.productCode,
            l_tradeStopInfoUnit.expirationStartDate,
            BooleanEnum.FALSE};
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TriggerOrderStopRow.TYPE,
                l_strWhere,
                null,
                l_objValues);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // �Q�j�@@�������ʂ��擾�ł����ꍇ�A
        //�u���͂����戵��~���͊��ɓo�^�ρv�̗�O���X���[����B
        if (l_lisRecords != null && l_lisRecords.size() > 0)
        {
            log.debug("���͂����戵��~���͊��ɓo�^�ςł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02432,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "���͂����戵��~���͊��ɓo�^�ςł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�V�K�戵��~)<BR>
     * ���ꎷ�s�����戵��~�e�[�u����1���R�[�hinsert����B<BR>
     * <BR>
     * �P�j�@@���ꎷ�s�����戵��~Params�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@��������Params�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@���ꎷ�s�����戵��~ID =<BR>
     * �@@�@@�@@���ꎷ�s�����戵��~Dao.newPkValue()<BR>
     * �@@�@@�،���ЃR�[�h = �Ǘ���.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h = �p�����[�^.�戵��~���̓�������<BR>
     * �@@�@@�ݒ�Ώێ�� = "����"<BR>
     * �@@�@@�L�[��� = �p�����[�^.�戵��~���.�����R�[�h<BR>
     * �@@�@@��~���R = �p�����[�^.�戵��~���̓�������<BR>
     * �@@�@@�L������From = �p�����[�^.�戵��~���̓�������<BR>
     * �@@�@@�L������To = �p�����[�^.�戵��~���̓�������<BR>
     * �@@�@@�폜�t���O = "DEFAULT"<BR>
     * �@@�@@�X�V�҃R�[�h = �Ǘ���.�Ǘ��҃R�[�h<BR>
     * �@@�@@�쐬���t = ���ݎ���<BR>
     * �@@�@@�X�V���t = ���ݎ���<BR>
     * <BR>
     * �R�j�@@�p�����[�^.�戵��~���.������~�󋵈ꗗ�̗v�f�����A<BR>
     * �@@�ȉ��̏�����Loop����B<BR>
     * �@@�R�|�P�j�@@�����Ώۂ̗v�f.����������� == "�A������"�̏ꍇ<BR>
     * �@@�@@�v���p�e�B�Z�b�g����Params.�A��������~�t���O =<BR>
     * �@@�@@�@@�����Ώۂ̗v�f.��~�t���O���Z�b�g�B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�����Ώۂ̗v�f.����������� == "OCO����"�̏ꍇ<BR>
     * �@@�@@�v���p�e�B�Z�b�g����Params.OCO������~�t���O =<BR>
     * �@@�@@�@@�����Ώۂ̗v�f.��~�t���O���Z�b�g�B<BR>
     * <BR>
     * �@@�R�|�R�j�@@�����Ώۂ̗v�f.����������� == "IFD����"�̏ꍇ<BR>
     * �@@�@@�v���p�e�B�Z�b�g����Params.IFD������~�t���O =<BR>
     * �@@�@@�@@�����Ώۂ̗v�f.��~�t���O���Z�b�g�B<BR>
     * <BR>
     * �@@�R�|�S�j�@@�����Ώۂ̗v�f.����������� == "�t�w�l����"�̏ꍇ<BR>
     * �@@�@@�v���p�e�B�Z�b�g����Params.�t�w�l������~�t���O =<BR>
     * �@@�@@�@@�����Ώۂ̗v�f.��~�t���O���Z�b�g�B<BR>
     * <BR>
     * �@@�R�|�T�j�@@�����Ώۂ̗v�f.����������� == "W�w�l����"�̏ꍇ<BR>
     * �@@�@@�v���p�e�B�Z�b�g����Params.W�w�l������~�t���O =<BR>
     * �@@�@@�@@�����Ώۂ̗v�f.��~�t���O���Z�b�g�B<BR>
     * <BR>
     * �S�j�@@QueryProcessor.doInsertQuery()���R�[������B<BR>
     * <BR>
     * �@@[doInsertQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�v���p�e�B�Z�b�g����Params<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ���<BR>
     * @@param l_tradeStopInfoUnit - (�戵��~���)<BR>
     * �戵��~���<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44113F64029E
     */
    protected void submitNewTradeStop(
        WEB3Administrator l_administrator,
        WEB3AdminToTradeStopInfoUnit l_tradeStopInfoUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitNewTradeStop(WEB3Administrator, WEB3AdminToTradeStopInfoUnit)";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@���ꎷ�s�����戵��~Params�𐶐�����B
        TriggerOrderStopParams l_params = new TriggerOrderStopParams();
        
        // �Q�j�@@��������Params�Ɉȉ��̃v���p�e�B���Z�b�g����B
        // �@@�@@�،���ЃR�[�h = �Ǘ���.�،���ЃR�[�h
        l_params.setInstitutionCode(l_administrator.getInstitutionCode());
        // �@@�@@���X�R�[�h = �p�����[�^.�戵��~���̓�������
        l_params.setBranchCode(l_tradeStopInfoUnit.branchCode);
        // �@@�@@�ݒ�Ώێ�� = "����"
        l_params.setTargetType(WEB3TargetTypeDef.PRODUCT);
        // �@@�@@�L�[��� = �p�����[�^.�戵��~���.�����R�[�h
        l_params.setKey(l_tradeStopInfoUnit.productCode);
        // �@@�@@��~���R = �p�����[�^.�戵��~���̓�������
        l_params.setStopReason(l_tradeStopInfoUnit.stopReason);
        // �@@�@@�L������From = �p�����[�^.�戵��~���̓�������
        l_params.setValidTermFrom(WEB3DateUtility.getDate(l_tradeStopInfoUnit.expirationStartDate, "yyyyMMdd"));
        // �@@�@@�L������To = �p�����[�^.�戵��~���̓�������
        l_params.setValidTermTo(WEB3DateUtility.getDate(l_tradeStopInfoUnit.expirationEndDate, "yyyyMMdd"));
        // �@@�@@�폜�t���O = "DEFAULT"
        l_params.setDeleteFlag(BooleanEnum.FALSE.intValue());
        // �@@�@@�X�V�҃R�[�h = �Ǘ���.�Ǘ��҃R�[�h
        l_params.setLastUpdater(l_administrator.getAdministratorCode());
        // �@@�@@�쐬���t = ���ݎ���
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        // �@@�@@�X�V���t = ���ݎ���
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        // �R�j�@@�p�����[�^.�戵��~���.������~�󋵈ꗗ�̗v�f�����A
        // �@@�ȉ��̏�����Loop����B
        for (int i = 0, l_intValue = 0, l_intLen = l_tradeStopInfoUnit.orderStopStateList.length; i < l_intLen; i++)
        {
            if (l_tradeStopInfoUnit.orderStopStateList[i].stopFlag)
            {
                l_intValue = BooleanEnum.TRUE.intValue();
            }
            else
            {
                l_intValue = BooleanEnum.FALSE.intValue();
            }
            
            // �@@�R�|�P�j�@@�����Ώۂ̗v�f.����������� == "�A������"�̏ꍇ
            // �@@�@@�v���p�e�B�Z�b�g����Params.�A��������~�t���O =
            // �@@�@@�@@�����Ώۂ̗v�f.��~�t���O���Z�b�g�B
            if (WEB3TriggerOrderTypeDef.SUCC.equals(l_tradeStopInfoUnit.orderStopStateList[i].triggerOrderType))
            {
                l_params.setSuccOrderStopFlag(l_intValue);
            }
            // �@@�R�|�Q�j�@@�����Ώۂ̗v�f.����������� == "OCO����"�̏ꍇ
            // �@@�@@�v���p�e�B�Z�b�g����Params.OCO������~�t���O =
            // �@@�@@�@@�����Ώۂ̗v�f.��~�t���O���Z�b�g�B
            else if (WEB3TriggerOrderTypeDef.OCO.equals(l_tradeStopInfoUnit.orderStopStateList[i].triggerOrderType))
            {
                l_params.setOcoOrderStopFlag(l_intValue);
            }
            // �@@�R�|�R�j�@@�����Ώۂ̗v�f.����������� == "IFD����"�̏ꍇ
            // �@@�@@�v���p�e�B�Z�b�g����Params.IFD������~�t���O =
            // �@@�@@�@@�����Ώۂ̗v�f.��~�t���O���Z�b�g�B
            else if (WEB3TriggerOrderTypeDef.IFD.equals(l_tradeStopInfoUnit.orderStopStateList[i].triggerOrderType))
            {
                l_params.setIfdOrderStopFlag(l_intValue);
            }
            // �@@�R�|�S�j�@@�����Ώۂ̗v�f.����������� == "�t�w�l����"�̏ꍇ
            // �@@�@@�v���p�e�B�Z�b�g����Params.�t�w�l������~�t���O =
            // �@@�@@�@@�����Ώۂ̗v�f.��~�t���O���Z�b�g�B
            else if (WEB3TriggerOrderTypeDef.STOP.equals(l_tradeStopInfoUnit.orderStopStateList[i].triggerOrderType))
            {
                l_params.setStopOrderStopFlag(l_intValue);
            }
            // �@@�R�|�T�j�@@�����Ώۂ̗v�f.����������� == "W�w�l����"�̏ꍇ
            // �@@�@@�v���p�e�B�Z�b�g����Params.W�w�l������~�t���O =
            // �@@�@@�@@�����Ώۂ̗v�f.��~�t���O���Z�b�g�B
            else if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_tradeStopInfoUnit.orderStopStateList[i].triggerOrderType))
            {
                l_params.setWlimitOrderStopFlag(l_intValue);
            }
        }
        
        // �S�j�@@QueryProcessor.doInsertQuery()���R�[������B
        // �@@[doInsertQuery()�ɃZ�b�g����p�����[�^]
        // �@@�@@arg0�F�@@�v���p�e�B�Z�b�g����Params
        try
        {
            // �@@�@@���ꎷ�s�����戵��~ID =
            // �@@�@@�@@���ꎷ�s�����戵��~Dao.newPkValue()
            l_params.setTriggerOrderStopId(TriggerOrderStopDao.newPkValue());
            Processors.getDefaultProcessor().doInsertQuery(l_params);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
