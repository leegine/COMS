head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLProductCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ�������T�[�r�X�����N���X(WEB3AdminAioSLProductCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 ���^�] (���u) �V�K�쐬 �d�l�ύX���f��760 ���f��766 ���f��769 ���f��771
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.WEB3AdminAioSLProductRegistControlService;
import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.message.WEB3AdminSLProductCancelCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductCancelCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductCancelConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductCancelConfirmResponse;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (�S�ۖ�������T�[�r�XImpl) <BR>
 * �S�ۖ�������T�[�r�X�����N���X<BR>
 *
 * @@author ���^�]
 * @@version 1.0
 */
public class WEB3AdminAioSLProductCancelServiceImpl implements WEB3AdminAioSLProductCancelService
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductCancelServiceImpl.class);

    /**
     * �S�ۖ���������������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �P�|�P�j �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^����m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate�S�ۖ������()���R�[������B<BR>
     * <BR>
     * �P�|�Q�j �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^����������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�S�ۖ������()���R�[������B<BR>
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

        if (l_request instanceof WEB3AdminSLProductCancelConfirmRequest)
        {
            //validate�S�ۖ������()���R�[������
            l_response = validateSLProductCancel((WEB3AdminSLProductCancelConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminSLProductCancelCompleteRequest)
        {
            //submit�S�ۖ������()���R�[������
            l_response = submitSLProductCancel((WEB3AdminSLProductCancelCompleteRequest)l_request);
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
     * �ivalidate�S�ۖ�������j<BR>
     * �S�ۖ����̎���m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uvalidate�S�ۖ�������v �Q��<BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu�@@:�@@���R�[�h���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�Ώۃ��R�[�h���݂Ȃ��G���[��throw����B<BR>
     * �@@�@@�@@�@@class�@@:  WEB3BusinessLayerException  <BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_02837<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �S�ۖ����o�^����m�F���N�G�X�g
     * @@return WEB3AdminSLProductCancelConfirmResponse
     * @@throws WEB3BaseException
     */
    public WEB3AdminSLProductCancelConfirmResponse validateSLProductCancel(
        WEB3AdminSLProductCancelConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSLProductCancel(WEB3AdminSLProductCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )(�S�ۖ����o�^����m�F���N�G�X�g::validate)
        //���N�G�X�g�f�[�^�̐������`�F�b�N���s���B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //���O�C�������Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_web3Administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Y���̊Ǘ��҂����̋@@�\���g���邩�����`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F �hB0602�h
        //is�X�V�F true
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE,
            true);

        WEB3AdminAioSLProductRegistControlService l_aioSLProductRegistControlService =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
                WEB3AdminAioSLProductRegistControlService.class);

        //���N�G�X�g.�S�ۖ��������L�[.����ID
        long l_lngProductId = l_request.searchConditions.productId;

        //���N�G�X�g.�S�ۖ��������L�[.�K�p����from
        Date l_datTargetPeriodFrom = l_request.searchConditions.targetPeriodFrom;

        //get�S�ۖ����s(long, Date)(�S�ۖ����o�^����T�[�r�XImpl::get�S�ۖ����s)
        //��L�[����S�ۖ����s���擾����B
        //[����]
        //����ID�F ���N�G�X�g.�S�ۖ��������L�[.����ID
        //�K�p����from�F ���N�G�X�g.�S�ۖ��������L�[.�K�p����from
        SecurityProductRow l_securityProductRow = null;
        try
        {
            l_securityProductRow =
                l_aioSLProductRegistControlService.getSecurityProductRow(l_lngProductId, l_datTargetPeriodFrom);
        }
        catch (WEB3BaseException l_ex)
        {
            //���R�[�h���擾�ł��Ȃ��ꍇ�A�Ώۃ��R�[�h���݂Ȃ��G���[��throw����B
            log.error("���R�[�h�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���R�[�h�����݂��܂���B");
        }

        //�g���v���_�N�g�}�l�[�W�����擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        ProductManager l_equityProductManager = l_tradingModule.getProductManager();

        //getProduct(arg0 : long)
        //�����I�u�W�F�N�g���擾����B
        //[����]
        // arg0�F ���N�G�X�g.�S�ۖ��������L�[.����ID
        Product l_product = null;
        try
        {
            l_product = l_equityProductManager.getProduct(l_lngProductId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }

        //createResponse( )
        //���X�|���X�I�u�W�F�N�g�̐���
        WEB3AdminSLProductCancelConfirmResponse l_response =
            (WEB3AdminSLProductCancelConfirmResponse)l_request.createResponse();

        // �v���p�e�B�Z�b�g
        WEB3SLProductInfoUnit l_stockLoanProductInfo = new WEB3SLProductInfoUnit();

        //���X�|���X�Ɉȉ��̓��e���Z�b�g����B
        //���X�|���X.�����o�^���.����ID = �S�ۖ���Row.����ID
        l_stockLoanProductInfo.productId = l_securityProductRow.getProductId();

        //���X�|���X.�����o�^���.�����R�[�h = �S�ۖ���Row.�����R�[�h
        l_stockLoanProductInfo.productCode = l_securityProductRow.getProductCode();

        //���X�|���X.�����o�^���.�����^�C�v = �S�ۖ���Row.�����^�C�v
        l_stockLoanProductInfo.productType = l_securityProductRow.getProductType().intValue() + "";

        //���X�|���X.�����o�^���.������ = getProduct().������
        l_stockLoanProductInfo.productName = l_product.getStandardName();

        //���X�|���X.�����o�^���.�K�i�敪 = �S�ۖ���Row.�K�i�敪
        l_stockLoanProductInfo.qualifiedDiv = l_securityProductRow.getFitFlg();

        //���X�|���X.�����o�^���.�|�� = �S�ۖ���Row.�|�ڂ̏����_�ȉ����J�b�g��String�^�ɕϊ������l
        l_stockLoanProductInfo.weight = (long)l_securityProductRow.getEstimationRatio() + "";

        //���X�|���X.�����o�^���.�K�p����from = �S�ۖ���Row.�K�p����from
        l_stockLoanProductInfo.targetPeriodFrom = l_securityProductRow.getApplyTermFrom();

        //���X�|���X.�����o�^���.�K�p����to = �S�ۖ���Row.�K�p����to
        l_stockLoanProductInfo.targetPeriodTo = l_securityProductRow.getApplyTermTo();

        //���X�|���X.�����o�^���.���R = �S�ۖ���Row.���R
        l_stockLoanProductInfo.reason = l_securityProductRow.getReason();

        l_response.stockLoanProductInfo = l_stockLoanProductInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �isubmit�S�ۖ�������j<BR>
     * �S�ۖ����̎�������������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usubmit�S�ۖ�������v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �S�ۖ����o�^����������N�G�X�g
     * @@return WEB3AdminSLProductCancelCompleteResponse
     * @@throws WEB3BaseException
     */
    public WEB3AdminSLProductCancelCompleteResponse submitSLProductCancel(
        WEB3AdminSLProductCancelCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSLProductCancel(WEB3AdminSLProductCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )(�S�ۖ����o�^����������N�G�X�g::validate)
        //���N�G�X�g�f�[�^�̐������`�F�b�N
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //���O�C�������Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_web3Administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Y���̊Ǘ��҂����̋@@�\���g���邩�����`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F �hB0602�h
        //is�X�V�F true
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE,
            true);

        //validate����p�X���[�h(�p�X���[�h : String)
        //����p�X���[�h�̔F�؂��s���B
        //[validate����p�X���[�h()�Ɏw�肷�����]
        //����p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_web3Administrator.validateTradingPassword(
            l_request.password);

        WEB3AdminAioSLProductRegistControlService l_aioSLProductRegistControlService =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
                WEB3AdminAioSLProductRegistControlService.class);

        //delete�S�ۖ������(�S�ۖ����������)
        //��L�[��ΏۂɒS�ۖ����e�[�u���̃��R�[�h���폜����B
        //[����]
        //�폜�ΏۃL�[�F ���N�G�X�g.�S�ۖ��������L�[
        l_aioSLProductRegistControlService.deleteSecurityProductInfo(l_request.searchConditions);

        //createResponse( )
        //���X�|���X�I�u�W�F�N�g�̐���
        WEB3AdminSLProductCancelCompleteResponse l_response =
            (WEB3AdminSLProductCancelCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
