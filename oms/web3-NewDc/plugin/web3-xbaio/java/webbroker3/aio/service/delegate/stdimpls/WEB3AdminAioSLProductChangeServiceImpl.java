head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLProductChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����ύX�T�[�r�XImpl(WEB3AdminAioSLProductChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �g�E�N�|(���u) �V�K�쐬 �d�l�ύX���f��No.760 No.769
Revision History : 2007/10/26 �g�E�N�|(���u) �d�l�ύX���f��No.816 �c�a�X�V�d�l158
Revision History : 2007/10/26 �g�E�N�|(���u) �d�l�ύX���f��No.814
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.WEB3AdminAioSLProductRegistControlService;
import webbroker3.aio.data.SecurityProductParams;
import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.message.WEB3AdminSLProductChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLProductChangeInputRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeInputResponse;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.message.WEB3SLProductSearchConditions;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductChangeService;
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
 * (�S�ۖ����ύX�T�[�r�XImpl)<BR>
 * �S�ۖ����ύX�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3AdminAioSLProductChangeServiceImpl implements WEB3AdminAioSLProductChangeService
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductChangeServiceImpl.class);

    /**
     * @@roseuid 46E8EE2A0308
     */
    public WEB3AdminAioSLProductChangeServiceImpl()
    {

    }

    /**
     * �S�ۖ����ύX���������{����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �P�|�P�j �����̃��N�G�X�g�f�[�^���A�S�ۖ����ύX���̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get�S�ۖ����ύX���͉��()���R�[������B <BR>
     * <BR>
     * �P�|�Q�j �����̃��N�G�X�g�f�[�^���A�S�ۖ����ύX�m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�S�ۖ����ύX()���R�[������B <BR>
     * <BR>
     * �P�|�R�j �����̃��N�G�X�g�f�[�^���A�S�ۖ����ύX�������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�S�ۖ����ύX()���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DCBC11001F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
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

        //�P�|�P�j �����̃��N�G�X�g�f�[�^���A�S�ۖ����ύX���̓��N�G�X�g�̏ꍇ
        //�@@�|get�S�ۖ����ύX���͉��()���R�[������B
        if (l_request instanceof WEB3AdminSLProductChangeInputRequest)
        {
            l_response = this.getSLProductChangeInputScreen(
                (WEB3AdminSLProductChangeInputRequest)l_request);
        }
        //�P�|�Q�j �����̃��N�G�X�g�f�[�^���A�S�ۖ����ύX�m�F���N�G�X�g�̏ꍇ
        //�@@�|validate�S�ۖ����ύX()���R�[������B
        else if (l_request instanceof WEB3AdminSLProductChangeConfirmRequest)
        {
            l_response = validateSLProductChange(
                (WEB3AdminSLProductChangeConfirmRequest)l_request);
        }
        //�P�|�R�j �����̃��N�G�X�g�f�[�^���A�S�ۖ����ύX�������N�G�X�g�̏ꍇ
        //�@@�|submit�S�ۖ����ύX()���R�[������B
        else if (l_request instanceof WEB3AdminSLProductChangeCompleteRequest)
        {
            l_response = submitSLProductChange(
                (WEB3AdminSLProductChangeCompleteRequest)l_request);
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
     * (get�S�ۖ����ύX���͉��)<BR>
     * �S�ۖ����ύX���͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �uget�S�ۖ����ύX���͉�ʁv�Q�ƁB <BR>
     * ========================================================<BR>
     * �V�[�P���X�} (�u�،��S�ۃ��[���T�[�r�X���f��(�Ǘ���)�v)<BR>
     * �@@�@@��̈ʒu�F���R�[�h���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�Ώۃ��R�[�h���݂Ȃ��G���[��throw����B<BR>
     * �@@�@@class �@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag  �@@ : BUSINESS_ERROR_02837<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminSLProductChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DCBDDE008D
     */
    protected WEB3AdminSLProductChangeInputResponse getSLProductChangeInputScreen(
        WEB3AdminSLProductChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSLProductChangeInputScreen(WEB3AdminSLProductChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�F �hB0602�h
        //is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE, true);

        //get�S�ۖ����s(long, Date)
        long l_lngProductId = l_request.searchConditions.productId;

        Date l_datTargetPeriodFrom = l_request.searchConditions.targetPeriodFrom;

        //�S�ۖ����ύX�T�[�r�X���擾��
        WEB3AdminAioSLProductRegistControlService l_service =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
                WEB3AdminAioSLProductRegistControlService.class);

        SecurityProductRow l_securityProductRow = null;

        try
        {
            //�S�ۖ����s���擾����
            l_securityProductRow = l_service.getSecurityProductRow(l_lngProductId, l_datTargetPeriodFrom);
        }
        catch (WEB3BaseException l_ex)
        {
            //���R�[�h���擾�ł��Ȃ��ꍇ�A�Ώۃ��R�[�h���݂Ȃ��G���[throw����
            log.error("���R�[�h�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //getProduct(arg0 : long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        ProductManager l_productManager = l_tradingModule.getProductManager();

        Product l_product = null;
        try
        {
            //�����I�u�W�F�N�g���擾����
            l_product = l_productManager.getProduct(l_lngProductId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����I�u�W�F�N�g���擾����: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //createResponse( )
        WEB3AdminSLProductChangeInputResponse l_response =
            (WEB3AdminSLProductChangeInputResponse)l_request.createResponse();

        //���b�Z�[�W �v���p�e�B�Z�b�g
        l_response.stockLoanProductInfo = new WEB3SLProductInfoUnit();
        //���X�|���X.�����o�^���.����ID = �S�ۖ���Row.����ID
        l_response.stockLoanProductInfo.productId = l_securityProductRow.getProductId();
        //���X�|���X.�����o�^���.�����R�[�h = �S�ۖ���Row.�����R�[�h
        l_response.stockLoanProductInfo.productCode = l_securityProductRow.getProductCode();
        //���X�|���X.�����o�^���.�����^�C�v = �S�ۖ���Row.�����^�C�v
        l_response.stockLoanProductInfo.productType = l_securityProductRow.getProductType().intValue() + "";
        //���X�|���X.�����o�^���.������ = getProduct().������
        l_response.stockLoanProductInfo.productName = l_product.getStandardName();
        //���X�|���X.�����o�^���.�K�i�敪 = �S�ۖ���Row.�K�i�敪
        l_response.stockLoanProductInfo.qualifiedDiv = l_securityProductRow.getFitFlg();
        //���X�|���X.�����o�^���.�|�� = �S�ۖ���Row.�|�ڂ̏����_�ȉ����J�b�g��String�^�ɕϊ������l
        l_response.stockLoanProductInfo.weight = (long)l_securityProductRow.getEstimationRatio() + "";
        //���X�|���X.�����o�^���.�K�p����from = �S�ۖ���Row.�K�p����from
        l_response.stockLoanProductInfo.targetPeriodFrom = l_securityProductRow.getApplyTermFrom();
        //���X�|���X.�����o�^���.�K�p����to = �S�ۖ���Row.�K�p����to
        l_response.stockLoanProductInfo.targetPeriodTo = l_securityProductRow.getApplyTermTo();
        //���X�|���X.�����o�^���.���R = �S�ۖ���Row.���R
        l_response.stockLoanProductInfo.reason = l_securityProductRow.getReason();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�S�ۖ����ύX)<BR>
     * �S�ۖ����ύX�m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �uvalidate�S�ۖ����ύX�v�Q�ƁB <BR>
     * ========================================================<BR>
     * �V�[�P���X�} (�u�،��S�ۃ��[���T�[�r�X���f��(�Ǘ���)�v)<BR>
     * �@@�@@��̈ʒu�F���R�[�h���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�Ώۃ��R�[�h���݂Ȃ��G���[��throw����B<BR>
     * �@@�@@class �@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag  �@@ : BUSINESS_ERROR_02837<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminSLProductChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DCBEF602CF
     */
    protected WEB3AdminSLProductChangeConfirmResponse validateSLProductChange(
        WEB3AdminSLProductChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSLProductChange(WEB3AdminSLProductChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�F �hB0602�h
        //is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE, true);

        //get�S�ۖ����s(long, Date)
        long l_lngProductId = l_request.searchConditions.productId;

        Date l_datTargetPeriodFrom = l_request.searchConditions.targetPeriodFrom;

        //�S�ۖ����ύX�T�[�r�X���擾��
        WEB3AdminAioSLProductRegistControlService l_service =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
                WEB3AdminAioSLProductRegistControlService.class);

        SecurityProductRow l_securityProductRow = null;

        try
        {
            //�S�ۖ����s���擾����
            l_securityProductRow = l_service.getSecurityProductRow(l_lngProductId, l_datTargetPeriodFrom);
        }
        catch (WEB3BaseException l_ex)
        {
            //���R�[�h���擾�ł��Ȃ��ꍇ�A�Ώۃ��R�[�h���݂Ȃ��G���[throw����
            log.error("���R�[�h�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //validate�ύX����(�S�ۖ���Row, �S�ۖ����o�^���)
        //[����]
        //�ύX�O�S�ۖ������F get�S�ۖ����s�i�j�̖߂�l
        //�ύX��S�ۖ������F ���N�G�X�g.�ύX������o�^���
        validateChangeItem(l_securityProductRow, l_request.changedStockLoanProductInfo);

        //createResponse( )
        WEB3AdminSLProductChangeConfirmResponse l_response =
            (WEB3AdminSLProductChangeConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�S�ۖ����ύX)<BR>
     * �S�ۖ����ύX�����������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �usubmit�S�ۖ����ύX�v�Q�ƁB <BR>
     * ========================================================<BR>
     * �V�[�P���X�} (�u�،��S�ۃ��[���T�[�r�X���f��(�Ǘ���)�v)<BR>
     * �@@�@@��̈ʒu�F���R�[�h���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�Ώۃ��R�[�h���݂Ȃ��G���[��throw����B<BR>
     * �@@�@@class �@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag  �@@ : BUSINESS_ERROR_02837<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminSLProductChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DCBFCE039A
     */
    protected WEB3AdminSLProductChangeCompleteResponse submitSLProductChange(
        WEB3AdminSLProductChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSLProductChange(WEB3AdminSLProductChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�F �hB0602�h
        //is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE, true);

        //validate����p�X���[�h(�p�X���[�h : String)
        l_administrator.validateTradingPassword(l_request.password);

        //get�S�ۖ����s(long, Date)
        long l_lngProductId = l_request.searchConditions.productId;

        Date l_datTargetPeriodFrom = l_request.searchConditions.targetPeriodFrom;

        //�S�ۖ����ύX�T�[�r�X���擾��
        WEB3AdminAioSLProductRegistControlService l_service =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
                WEB3AdminAioSLProductRegistControlService.class);

        SecurityProductRow l_securityProductRow = null;

        try
        {
            //�S�ۖ����s���擾����
            l_securityProductRow = l_service.getSecurityProductRow(l_lngProductId, l_datTargetPeriodFrom);
        }
        catch (WEB3BaseException l_ex)
        {
            //���R�[�h���擾�ł��Ȃ��ꍇ�A�Ώۃ��R�[�h���݂Ȃ��G���[throw����
            log.error("���R�[�h�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //validate�ύX����(�S�ۖ���Row, �S�ۖ����o�^���)
        //�ύX�O�S�ۖ������F get�S�ۖ����s�i�j�̖߂�l
        //�ύX��S�ۖ������F ���N�G�X�g.�ύX������o�^���

        WEB3SLProductInfoUnit l_changedStockLoanProductInfo = l_request.changedStockLoanProductInfo;
        validateChangeItem(l_securityProductRow, l_changedStockLoanProductInfo);

        //get�Ǘ��҃R�[�h( )
        String l_strAdminCode = l_administrator.getAdministratorCode();

        //�X�V���t�FTradingSystem.getSystemTimestamp()�̖߂�l
        Date l_datTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();

        //create�S�ۖ����X�V���(�S�ۖ����o�^���, String, Date, �S�ۖ���Row)
        SecurityProductRow l_changedSecurityProductRow = createSLProductChangeUpdateInfo(
            l_changedStockLoanProductInfo, l_strAdminCode, l_datTimestamp, l_securityProductRow);

        //�S�ۖ����������
        WEB3SLProductSearchConditions l_searchKeyConditions = l_request.searchConditions;

        //update�S�ۖ������(�S�ۖ����������, �S�ۖ���Row)
        l_service.updateSecurityProductInfo(l_searchKeyConditions, l_changedSecurityProductRow);

        //createResponse( )
        WEB3AdminSLProductChangeCompleteResponse l_response =
            (WEB3AdminSLProductChangeCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�S�ۖ����X�V���)<BR>
     * �S�ۖ����e�[�u���ɍX�V������𐶐����A�ԋp����B<BR>
     * <BR> 
     * �P�j�@@�S�ۖ���Row�̊e���ڂ��X�V����B <BR>
     * <BR>
     * �E����.�S�ۖ���Row.�]���|�� = ����.�ύX��������.�]���|�� <BR>
     * �@@* �l��null�̏ꍇ�A0 <BR>
     * �E����.�S�ۖ���Row.�K�i�敪 = ����.�ύX��������.�K�i�敪 <BR>
     * �E����.�S�ۖ���Row.�K�p����from = ����.�ύX��������.�K�p����from <BR>
     * �E����.�S�ۖ���Row.�K�p����to = ����.�ύX��������.�K�p����to <BR>
     * �@@* �l��null�̏ꍇ�A9999/12/31 <BR>
     * �E����.�S�ۖ���Row.���R = ����.�ύX��������.���R <BR>
     * �E����.�S�ۖ���Row.�X�V�҃R�[�h = ����:�Ǘ��҃R�[�h <BR>
     * �E����.�S�ۖ���Row.�X�V���t = �����X�V���t <BR>
     * <BR>
     * �Q�j�@@�S�ۖ���Row��ԋp����B<BR>
     * <BR>
     * @@param l_changedProductInfo - (�ύX��������)<BR>
     * �ύX��������<BR>
     * @@param l_strAdminCode - (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h<BR>
     * @@param l_datUpdateDate - (�X�V���t)<BR>
     * �X�V���t<BR>
     * @@param l_securityProductRow - (�S�ۖ���Row)<BR>
     * �S�ۖ���Row<BR>
     * @@return �S�ۖ���Row
     * @@throws WEB3BaseException
     * @@roseuid 46DD47F00331
     */
    private SecurityProductRow createSLProductChangeUpdateInfo(
        WEB3SLProductInfoUnit l_changedProductInfo,
        String l_strAdminCode,
        Date l_datUpdateDate,
        SecurityProductRow l_securityProductRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSLProductChangeUpdateInfo(" +
            "WEB3SLProductInfoUnit, String, Date, SecurityProductRow)";

        log.entering(STR_METHOD_NAME);

        if (l_securityProductRow == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        SecurityProductParams l_securityProductParams = new SecurityProductParams(l_securityProductRow);

        //����.�ύX��������.�]���|�ڒl��null�̏ꍇ
        if (l_changedProductInfo.weight == null)
        {
            //����.�S�ۖ���Row.�]���|�� = 0
            l_securityProductParams.setEstimationRatio(0);
        }
        else
        {
            //����.�S�ۖ���Row.�]���|�� = ����.�ύX��������.�]���|��
            l_securityProductParams.setEstimationRatio(Double.parseDouble(l_changedProductInfo.weight));

        }
        //����.�S�ۖ���Row.�K�i�敪 = ����.�ύX��������.�K�i�敪
        l_securityProductParams.setFitFlg(l_changedProductInfo.qualifiedDiv);
        //����.�S�ۖ���Row.�K�p����from = ����.�ύX��������.�K�p����from
        l_securityProductParams.setApplyTermFrom(l_changedProductInfo.targetPeriodFrom);

        //����.�ύX��������.�K�p����to�l��null�̏ꍇ
        if (l_changedProductInfo.targetPeriodTo == null)
        {
            //����.�S�ۖ���Row.�K�p����to = 9999/12/31
            l_securityProductParams.setApplyTermTo(
                WEB3DateUtility.getDate(WEB3GentradeTimeDef.MAX_YMD, WEB3GentradeTimeDef.DATE_SPLIT_YMD));
        }
        else
        {
            //����.�S�ۖ���Row.�K�p����to = ����.�ύX��������.�K�p����to
            l_securityProductParams.setApplyTermTo(l_changedProductInfo.targetPeriodTo);
        }

        //����.�S�ۖ���Row.���R = ����.�ύX��������.���R
        l_securityProductParams.setReason(l_changedProductInfo.reason);
        //����.�S�ۖ���Row.�X�V�҃R�[�h = ����:�Ǘ��҃R�[�h
        l_securityProductParams.setLastUpdater(l_strAdminCode);
        //����.�S�ۖ���Row.�X�V���t = �����X�V���t
        l_securityProductParams.setLastUpdatedTimestamp(l_datUpdateDate);

        log.exiting(STR_METHOD_NAME);
        return l_securityProductParams;
    }

    /**
     * (edit�S�ۖ������)<BR>
     * �w�肳�ꂽ�����̃��R�[�h�����݂����ꍇ�A�s��remove����<BR>
     * List��ԋp����B<BR>
     * <BR>
     * �P�j ����:�S�ۖ������̗v�f���ALoop�������s���B<BR>
     * <BR>
     * �@@�P�|�P�j�@@�S�ۖ����s�̎擾<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�S�ۖ����s.�K�p����from == ����:�K�p����from�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�v�f��remove()����B<BR>
     * <BR>
     * �Q�j�@@�S�ۖ�������ԋp����B<BR>
     * @@param l_datTargetPeriodFrom - (�K�p����from)<BR>
     * �K�p����from<BR>
     * @@param l_lisSecurityProductInfos - (�S�ۖ������)<BR>
     * �S�ۖ������<BR>
     * @@return List
     * @@roseuid 46DE6DA301B6
     */
    private List editSLProductInfo(Date l_datTargetPeriodFrom, List l_lisSecurityProductInfos)
    {
        final String STR_METHOD_NAME = "editSLProductInfo(Date, List)";
        log.entering(STR_METHOD_NAME);

        List l_lisCopies = new ArrayList();

        int l_intLength = l_lisSecurityProductInfos.size();
        for (int i = 0; i < l_intLength; i++)
        {
            l_lisCopies.add(l_lisSecurityProductInfos.get(i));
        }

        Iterator l_itRecords = l_lisCopies.iterator();
        //�P�j ����:�S�ۖ������̗v�f���ALoop�������s��
        while (l_itRecords.hasNext())
        {
            //�P�|�P�j�@@�S�ۖ����s�̎擾
            SecurityProductRow l_securityProductRow =
                (SecurityProductRow)l_itRecords.next();

            //�P�|�Q�j�@@�S�ۖ����s.�K�p����from == ����:�K�p����from�̏ꍇ�A�v�f��remove()����
            if (WEB3DateUtility.compareToDay(
                l_securityProductRow.getApplyTermFrom(), l_datTargetPeriodFrom) == 0)
            {
                l_itRecords.remove();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisCopies;
    }

    /**
     * (validate�ύX����)<BR>
     * �ύX���ڂɂ��Ẵ`�F�b�N���s���B<BR>
     * <BR>
     * �V�[�P���X�}�uvalidate�ύX���ځv�Q��<BR>
     * ========================================================<BR>
     * �V�[�P���X�} (�u�،��S�ۃ��[���T�[�r�X���f��(�Ǘ���)�v)<BR>
     * �@@�@@��̈ʒu�F�ȉ��̏����̖߂�l�� 1 �̏ꍇ�A��O���X���[<BR>
     * �@@�@@class �@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag  �@@ : BUSINESS_ERROR_02688<BR>
     * ========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�} (�u�،��S�ۃ��[���T�[�r�X���f��(�Ǘ���)�v)<BR>
     * �@@�@@��̈ʒu�F����:�ύX��S�ۏ�����.�K�p����from < roll()<BR>
     *      �@@�@@�@@�@@�Ŏ擾�����l �̏ꍇ�A��O���X���[<BR>
     * �@@�@@class �@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag  �@@ : BUSINESS_ERROR_02929<BR>
     * ========================================================<BR>
     * @@param l_preSecurityProductInfo - (�ύX�O�S�ۖ������)<BR>
     * �ύX�O�S�ۖ������<BR>
     * @@param l_changedSecurityProductInfo - (�ύX��S�ۖ������)<BR>
     * �ύX��S�ۖ������<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DF4B960201
     */
    private void validateChangeItem(SecurityProductRow l_preSecurityProductInfo,
        WEB3SLProductInfoUnit l_changedSecurityProductInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangeItem(SecurityProductRow, WEB3SLProductInfoUnit)";
        log.entering(STR_METHOD_NAME);

        //�ȉ��̏����̖߂�l�� 1 �̏ꍇ�A��O���X���[
        //compare�ύX���(�S�ۖ���Row, �S�ۖ����o�^���)
        WEB3AdminAioSLProductRegistControlService l_service =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
                WEB3AdminAioSLProductRegistControlService.class);

        int l_intCompareChangeInfo = l_service.compareChangeInfo(
            l_preSecurityProductInfo, l_changedSecurityProductInfo);

        if (l_intCompareChangeInfo == 1)
        {
            log.debug("�ύX���ږ����G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02688,
                this.getClass().getName() + "."  + STR_METHOD_NAME,
                "�ύX���ږ����G���[");
        }

        //is���ڕύX(String, String)
        Date l_datPreFrom = l_preSecurityProductInfo.getApplyTermFrom();
        Date l_datChangedFrom = l_changedSecurityProductInfo.targetPeriodFrom;

        boolean l_blnIsItemChangeFrom = l_service.isItemChange(
            WEB3DateUtility.formatDate(l_datPreFrom, WEB3GentradeTimeDef.DATE_FORMAT_YMD),
            WEB3DateUtility.formatDate(l_datChangedFrom, WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //is���ڕύX(String, String)
        Date l_datPreTo = l_preSecurityProductInfo.getApplyTermTo();
        Date l_datChangedTo = l_changedSecurityProductInfo.targetPeriodTo;

        boolean l_blnIsItemChangeTo = l_service.isItemChange(
            WEB3DateUtility.formatDate(l_datPreTo, WEB3GentradeTimeDef.DATE_FORMAT_YMD),
            WEB3DateUtility.formatDate(l_datChangedTo, WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //�K�p����from or �K�p����to ���ύX����Ă���ꍇ�A�ȉ��̏��������s
        if (l_blnIsItemChangeFrom || l_blnIsItemChangeTo)
        {
            //get�S�ۖ������(long)
            List l_lisSecurityProductInfos = l_service.getSecurityProductInfo(
                l_preSecurityProductInfo.getProductId());

            //edit�S�ۖ������(Date, List)
            List l_editedSLProductInfos = editSLProductInfo(
                l_preSecurityProductInfo.getApplyTermFrom(), l_lisSecurityProductInfos);

            //�K�p����from���ύX����Ă���ꍇ
            if (l_blnIsItemChangeFrom)
            {
                //�c�Ɠ��v�Z(��� : Timestamp)
                Timestamp l_tsStandardDate = GtlUtils.getTradingSystem().getSystemTimestamp();
                WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(l_tsStandardDate);

                //roll(���Z�^���Z���� : int)
                Date l_datBizDate = l_bizDate.roll(1);

                //����:�ύX��S�ۏ�����.�K�p����from < roll()�Ŏ擾�����l �̏ꍇ�A��O���X���[
                if (WEB3DateUtility.compareToDay(l_datChangedFrom, l_datBizDate) < 0)
                {
                    log.debug("�K�p����from�͗��c�Ɠ���菬�����ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02929,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�K�p����from�͗��c�Ɠ���菬�����ł��B");
                }
            }

            //edit�S�ۖ������i�j�̖߂�l�v�f != 0 �̏ꍇ�A�ȉ��̏��������s
            if (l_editedSLProductInfos.size() != 0)
            {
                //validate�S�ۖ����������(List, Date, Date)
                //[����]
                // �S�ۖ������F edit�S�ۖ������i�j�̖߂�l
                // �K�p����from�F ����.�ύX��S�ۖ������.�K�p����from
                // �K�p����to�F ����.�ύX��S�ۖ������.�K�p����to
                l_service.validateSecurityProductSameTerm(
                    l_editedSLProductInfos, l_datChangedFrom, l_datChangedTo);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}@
