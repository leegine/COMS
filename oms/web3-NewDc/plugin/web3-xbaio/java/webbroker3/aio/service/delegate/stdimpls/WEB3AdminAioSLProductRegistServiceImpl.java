head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLProductRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����o�^�T�[�r�XImpl(WEB3AdminAioSLProductRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 �И��� (���u) �V�K�쐬 ���f��No.760
Revision History : 2007/09/18 �И��� (���u) ���f��No.769
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.WEB3AdminAioSLProductRegistControlService;
import webbroker3.aio.define.WEB3AdminAioSLStockLoanWeightDef;
import webbroker3.aio.message.WEB3AdminSLProductRegistCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductRegistConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLProductRegistInputRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistInputResponse;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�S�ۖ����o�^�T�[�r�XImpl)<BR>
 * �S�ۖ����o�^�T�[�r�X�����N���X<BR>
 *
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminAioSLProductRegistServiceImpl implements WEB3AdminAioSLProductRegistService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductRegistServiceImpl.class);

    /**
     * �S�ۖ����o�^���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �P�|�P�j �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^���̓��N�G�X�g�̏ꍇ<BR>
     * �@@�|get�S�ۖ������͉��()���R�[������B <BR>
     * <BR>
     * �P�|�Q�j �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate�S�ۖ����o�^()���R�[������B<BR>
     * <BR>
     * �P�|�R�j �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^�������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�S�ۖ����o�^()���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^���̓��N�G�X�g�̏ꍇ
        //�|get�S�ۖ������͉��()���R�[������B
        if (l_request instanceof WEB3AdminSLProductRegistInputRequest)
        {
            l_response =
                this.getSLProductInputScreen((WEB3AdminSLProductRegistInputRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^�m�F���N�G�X�g�̏ꍇ
        //�|validate�S�ۖ����o�^()���R�[������
        else if (l_request instanceof WEB3AdminSLProductRegistConfirmRequest)
        {
            l_response =
                this.validateSLProductRegist((WEB3AdminSLProductRegistConfirmRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^�������N�G�X�g�̏ꍇ
        //�|submit�S�ۖ����o�^()���R�[������B
        else if (l_request instanceof WEB3AdminSLProductRegistCompleteRequest)
        {
            l_response =
                this.submitSLProductRegist((WEB3AdminSLProductRegistCompleteRequest)l_request);
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
     * (get�S�ۖ������͉��)<BR>
     * �S�ۖ����o�^���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uget�S�ۖ������͉�ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (�S�ۖ����o�^���̓��N�G�X�g�I�u�W�F�N�g)<BR>
     * �S�ۖ����o�^���̓��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminSLProductRegistInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminSLProductRegistInputResponse getSLProductInputScreen(
        WEB3AdminSLProductRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSLProductInputScreen(WEB3AdminSLProductRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE, true);

        //���X�|���X�f�[�^�𐶐�����
        WEB3AdminSLProductRegistInputResponse l_response =
            (WEB3AdminSLProductRegistInputResponse)l_request.createResponse();

        //���c�Ɠ����t
        l_response.nextBizDate = new WEB3GentradeBizDate(
            new Timestamp(GtlUtils.getSystemTimestamp().getTime())).roll(1);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�S�ۖ����o�^)<BR>
     * <BR>
     * �S�ۖ����o�^�m�F��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uvalidate�S�ۖ����o�^�v�Q�ƁB<BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F(validate�S�ۖ����o�^) <BR>
     * ��̈ʒu�F(���N�G�X�g.�S�ۖ����o�^���.�K�p����from < roll()�Ŏ擾�����l<BR>
     * �@@�@@�@@�@@�̏ꍇ�A��O���X���[)<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02929 <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F(validate�S�ۖ����o�^) <BR>
     * ��̈ʒu�F(���������I�u�W�F�N�g���擾�ł��Ȃ��ꍇ<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02928 <BR>
     * ========================================================== <BR>
     * @@param l_request - (�S�ۖ����o�^�m�F���N�G�X�g�I�u�W�F�N�g)<BR>
     * �S�ۖ����o�^�m�F���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminSLProductRegistConfirmResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminSLProductRegistConfirmResponse validateSLProductRegist(
        WEB3AdminSLProductRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSLProductRegist(WEB3AdminSLProductRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE, true);

        //�c�Ɠ��v�Z
        //roll(���Z�^���Z���� : int)
        Date l_datBizDate = new WEB3GentradeBizDate(
            new Timestamp(GtlUtils.getSystemTimestamp().getTime())).roll(1);

        //�S�ۖ������o�^���I�u�W�F�N�g.�K�p����From < roll()
        //throw�G���[
        if (WEB3DateUtility.compareToDay(
            l_request.stockLoanProductInfo.targetPeriodFrom, l_datBizDate) < 0)
        {
            log.debug("�K�p����from�͗��c�Ɠ���菬�����ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02929,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�p����from�͗��c�Ɠ���菬�����ł��B");
        }

        //get�،����
        Institution l_institution = l_administrator.getInstitution();

        Product l_product = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeProductManager l_eqTypeProductManager =
            (EqTypeProductManager)l_tradingModule.getProductManager();

        try
        {
            l_product =
                l_eqTypeProductManager.getProduct(
                    l_institution,
                    l_request.stockLoanProductInfo.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("���������I�u�W�F�N�g���擾�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02928,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�S�ۖ����o�^����T�[�r�XImpl
        WEB3AdminAioSLProductRegistControlService l_service =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
            WEB3AdminAioSLProductRegistControlService.class);

        //get�S�ۖ������(long)
        List l_lisProductInfos = l_service.getSecurityProductInfo(l_product.getProductId());

        if (l_lisProductInfos != null && l_lisProductInfos.size() != 0)
        {
            //validate�S�ۖ����������
            l_service.validateSecurityProductSameTerm(
            	l_lisProductInfos,
                l_request.stockLoanProductInfo.targetPeriodFrom,
                l_request.stockLoanProductInfo.targetPeriodTo);
        }

        //���X�|���X�f�[�^�𐶐�����
        WEB3AdminSLProductRegistConfirmResponse l_response =
            (WEB3AdminSLProductRegistConfirmResponse)l_request.createResponse();

        //���X�|���X.�����o�^���
        l_response.stockLoanProductInfo = new WEB3SLProductInfoUnit();
        //���X�|���X.�����o�^���.����ID
        l_response.stockLoanProductInfo.productId = l_product.getProductId();

        //���X�|���X.�����o�^���.�����R�[�h
        l_response.stockLoanProductInfo.productCode = l_request.stockLoanProductInfo.productCode;

        //���X�|���X.�����o�^���.�����^�C�v
        l_response.stockLoanProductInfo.productType = l_request.stockLoanProductInfo.productType;

        //���X�|���X.�����o�^���.������
        l_response.stockLoanProductInfo.productName = l_product.getStandardName();

        //���X�|���X.�����o�^���.�K�i�敪
        l_response.stockLoanProductInfo.qualifiedDiv = l_request.stockLoanProductInfo.qualifiedDiv;

        //���X�|���X.�����o�^���.�|��
        if (l_request.stockLoanProductInfo.weight != null)
        {
            l_response.stockLoanProductInfo.weight = l_request.stockLoanProductInfo.weight;
        }
        else
        {
            l_response.stockLoanProductInfo.weight = WEB3AdminAioSLStockLoanWeightDef.WEIGHT;
        }

        //���X�|���X.�����o�^���.�K�p����From
        l_response.stockLoanProductInfo.targetPeriodFrom = l_request.stockLoanProductInfo.targetPeriodFrom;

        //���X�|���X.�����o�^���.�K�p����To
        if (l_request.stockLoanProductInfo.targetPeriodTo != null)
        {
            l_response.stockLoanProductInfo.targetPeriodTo = l_request.stockLoanProductInfo.targetPeriodTo;
        }
        else
        {
            l_response.stockLoanProductInfo.targetPeriodTo =
                WEB3DateUtility.getDate(WEB3GentradeTimeDef.MAX_YMD, WEB3GentradeTimeDef.DATE_SPLIT_YMD);
        }

        //���X�|���X.�����o�^���.���R
        l_response.stockLoanProductInfo.reason = l_request.stockLoanProductInfo.reason;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�S�ۖ����o�^)<BR>
     * <BR>
     * �S�ۖ����o�^������ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usubmit�S�ۖ����o�^�v�Q�ƁB<BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F(submit�S�ۖ����o�^) <BR>
     * ��̈ʒu�F(���N�G�X�g.�S�ۖ����o�^���.�K�p����from < roll()�Ŏ擾�����l<BR>
     * �@@�@@�@@�@@�̏ꍇ�A��O���X���[)<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02929 <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F(submit�S�ۖ����o�^) <BR>
     * ��̈ʒu�F(���������I�u�W�F�N�g���擾�ł��Ȃ��ꍇ<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02928 <BR>
     * ========================================================== <BR>
     * @@param l_request - (�S�ۖ����o�^�������N�G�X�g�I�u�W�F�N�g)<BR>
     * �S�ۖ����o�^�������N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminSLProductRegistCompleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminSLProductRegistCompleteResponse submitSLProductRegist(
        WEB3AdminSLProductRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSLProductRegist(WEB3AdminSLProductRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE, true);

        //validate����p�X���[�h
        l_administrator.validateTradingPassword(l_request.password);

        //�c�Ɠ��v�Z
        //roll(���Z�^���Z���� : int)
        Date l_datBizDate = new WEB3GentradeBizDate(
            new Timestamp(GtlUtils.getSystemTimestamp().getTime())).roll(1);

        //�S�ۖ������o�^���I�u�W�F�N�g.�K�p����From < roll()
        //throw�G���[
        if (WEB3DateUtility.compareToDay(
            l_request.stockLoanProductInfo.targetPeriodFrom, l_datBizDate) < 0)
        {
            log.debug("�K�p����from�͗��c�Ɠ���菬�����ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02929,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�p����from�͗��c�Ɠ���菬�����ł��B");
        }

        //get�،����
        Institution l_institution = l_administrator.getInstitution();

        Product l_product = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeProductManager l_eqTypeProductManager =
            (EqTypeProductManager)l_tradingModule.getProductManager();

        try
        {
            //getProduct
            l_product =
                l_eqTypeProductManager.getProduct(
                    l_institution,
                    l_request.stockLoanProductInfo.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("���������I�u�W�F�N�g���擾�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02928,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�S�ۖ����o�^����T�[�r�XImpl
        WEB3AdminAioSLProductRegistControlService l_service =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
            WEB3AdminAioSLProductRegistControlService.class);

        //get�S�ۖ������(long)
        List l_lisProductInfos = l_service.getSecurityProductInfo(l_product.getProductId());

        if (l_lisProductInfos != null && l_lisProductInfos.size() != 0)
        {
            //validate�S�ۖ����������
            l_service.validateSecurityProductSameTerm(
            	l_lisProductInfos,
                l_request.stockLoanProductInfo.targetPeriodFrom,
                l_request.stockLoanProductInfo.targetPeriodTo);
        }

        //get�Ǘ��҃R�[�h
        String l_strAdminCode = l_administrator.getAdministratorCode();

        //insert�S�ۖ������(String, �S�ۖ����o�^���, String)
        l_service.insertSecurityProductInfo(
            l_institution.getInstitutionCode(),
            l_request.stockLoanProductInfo,
            l_strAdminCode);

        //���X�|���X�f�[�^�𐶐�����
        WEB3AdminSLProductRegistCompleteResponse l_response =
            (WEB3AdminSLProductRegistCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
