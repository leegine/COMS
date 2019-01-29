head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticRecruitLimitManageServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ��������X�ʉ���g�Ǘ��T�[�r�XImpl(WEB3AdminBondDomesticRecruitLimitManageServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.214,���f��No.242
Revision History : 2007/07/23 �đo�g (���u) ���f��No.244,���f��No.247
Revision History : 2007/09/10 ���n�m (���u) �d�l�ύX�E���f��No.254,���f��No.255
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.bd.WEB3BondBranchCondition;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.data.BondBranchRecruitLimitDao;
import webbroker3.bd.data.BondBranchRecruitLimitParams;
import webbroker3.bd.data.BondBranchRecruitLimitRow;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageInputRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageInputResponse;
import webbroker3.bd.message.WEB3BondDomesticBranchRecruitLimitInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticRecruitLimitManageService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondBranchRecruitLimitBranchCodeDef;
import webbroker3.common.define.WEB3BranchRecruitLimitDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҍ��������X�ʉ���g�Ǘ��T�[�r�XImpl)<BR>
 * �Ǘ��ҍ��������X�ʉ���g�Ǘ��T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminBondDomesticRecruitLimitManageServiceImpl
    implements WEB3AdminBondDomesticRecruitLimitManageService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticRecruitLimitManageServiceImpl.class);

    /**
     * @@roseuid 46A473FD002E
     */
    public WEB3AdminBondDomesticRecruitLimitManageServiceImpl()
    {

    }

    /**
     * �Ǘ��ҍ��������X�ʉ���g�Ǘ��T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * input����g�Ǘ�()�Avalidate����g�Ǘ�()�Asubmit����g�Ǘ�()<BR>
     * �̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 468B7F930369
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
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminBondDomesticRecruitLimitManageInputRequest)
        {
            l_response =
                this.inputRecruitLimitManage(
                    (WEB3AdminBondDomesticRecruitLimitManageInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminBondDomesticRecruitLimitManageConfirmRequest)
        {
            l_response =
                this.validateRecruitLimitManage(
                    (WEB3AdminBondDomesticRecruitLimitManageConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminBondDomesticRecruitLimitManageCompleteRequest)
        {
            l_response =
                this.submitRecruitLimitManage(
                    (WEB3AdminBondDomesticRecruitLimitManageCompleteRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
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
     * (input����g�Ǘ�)<BR>
     * �Ǘ��ҍ��������X�ʉ���g�Ǘ����͏������s�Ȃ��B<BR>
     * <BR>
     * �V�[�P���X�}�F�u�i�Ǘ��ҍ��������X�ʉ���g�Ǘ��jinput����g�Ǘ��v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminBondDomesticRecruitLimitManageInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 468B7DBC0212
     */
    protected WEB3AdminBondDomesticRecruitLimitManageInputResponse inputRecruitLimitManage(
        WEB3AdminBondDomesticRecruitLimitManageInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " inputRecruitLimitManage(WEB3AdminBondDomesticRecruitLimitManageInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        // �@@�\�J�e�S���R�[�h�@@�F�@@�\�J�e�S���R�[�h.�������Ǘ�
        // is�X�V�@@�Ffalse
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            false);

        //is�S���X����( )
        boolean l_blnIsAllBranchsPermission = l_administrator.isAllBranchsPermission();

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get���X�R�[�h( )
        String l_strBranchCode = l_administrator.getBranchCode();

        //get������(long)
        //[����]
        // ����ID �F ���N�G�X�g.����ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        long l_lngProductId = Long.parseLong(l_request.productId);
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_productManager.getBondProduct(l_lngProductId);

        //get���X(�،���ЃR�[�h : String, ���X�R�[�h : String)
        //[����]
        //�@@�@@�،���ЃR�[�h�F�擾�����،���ЃR�[�h
        //�@@�@@���X�R�[�h�@@�@@�@@�F�擾�������X�R�[�h
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_gentradeAccountManager.getWeb3GenBranch(
                l_strInstitutionCode,
                l_strBranchCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //getBranchId( )
        long l_lngBranchId = l_branch.getBranchId();

        //�����X�ʏ���(long)
        //[����]
        //�@@���XID�F�擾�������XID
        WEB3BondBranchCondition l_bondBranchCondition =
            new WEB3BondBranchCondition(l_lngBranchId);

        //get����g���X�ʊǗ��敪( )
        String l_strBranchRecruitLimitDiv =
            l_bondBranchCondition.getBranchRecruitLimitDiv();

        //create���������X�ʉ���g���(long, String, String)
        //[����]
        //  ����ID�F���N�G�X�g.����ID
        //�@@�،���ЃR�[�h�F�擾�����،���ЃR�[�h
        //�@@���X�R�[�h�@@�@@�Fis�S���X����true�̏ꍇ�Anull�B
        //           is�S���X����false���A
        //               get����g���X�ʊǗ��敪()���u���X�ʊǗ����Ȃ��v�̏ꍇ�A�u---�v�i�S���X)�B
        //           is�S���X����false���A
        //               get����g���X�ʊǗ��敪()���u���X�ʊǗ�����v�̏ꍇ�A�擾�������X�R�[�h�B
        String l_strBranchRecruitCode = null;
        if (l_blnIsAllBranchsPermission)
        {
            l_strBranchRecruitCode = null;
        }
        else if (WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT.equals(l_strBranchRecruitLimitDiv))
        {
            l_strBranchRecruitCode = WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH;
        }
        else if (WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT.equals(l_strBranchRecruitLimitDiv))
        {
            l_strBranchRecruitCode = l_strBranchCode;
        }
        WEB3BondDomesticBranchRecruitLimitInfo[] l_branchRecruitLimitInfos =
            l_productManager.createAdminBondDomesticRecruitLimitInfo(
                l_lngProductId,
                l_strInstitutionCode,
                l_strBranchRecruitCode);

        //createResponse( )
        WEB3AdminBondDomesticRecruitLimitManageInputResponse l_response =
            (WEB3AdminBondDomesticRecruitLimitManageInputResponse)l_request.createResponse();

        //�v���p�e�B�E�Z�b�g
        //�����R�[�h         �F������.�����R�[�h(SONAR)
        l_response.productCode = l_bondProduct.getHostProductCode();

        //�񍆃R�[�h         �F������.�񍆃R�[�h(SONAR)
        l_response.productIssueCode = l_bondProduct.getHostProductIssueCode();

        //������           �F������.������
        l_response.productName = l_bondProduct.getProductName();

        //��ʃR�[�h         �F������.��ʃR�[�h
        l_response.bondCategCode = l_bondProduct.getBondCategCode();

        //���s�N����     �F������.���s��
        l_response.issueDate = l_bondProduct.getIssueDate();

        //���s���i          �F������.���s���i
        l_response.issuePrice = WEB3StringTypeUtility.formatNumber(l_bondProduct.getIssuePrice());

        //�N����           �F������.����
        l_response.annualRate = WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());

        //���������P     �F������.�������P
        l_response.couponPaymentDate1 = l_bondProduct.getInterestPaymentDay1();

        //���������Q     �F������.�������Q
        l_response.couponPaymentDate2 = l_bondProduct.getInterestPaymentDay2();

        //���ҔN����       �F������.���ғ�
        l_response.maturityDate = l_bondProduct.getMaturityDate();

        //����J�n����      �F������.�戵�J�n����
        l_response.recruitStartDate = l_bondProduct.getTradeStartDate();

        //����I����       �F������.�戵�I������
        l_response.recruitEndDate = WEB3DateUtility.toDay(l_bondProduct.getTradeEndDate());

        //���������X�ʉ���g���  �Fcreate���������X�ʉ���g���̖߂�l
        l_response.bondDomesticBranchRecruitLimitInfo = l_branchRecruitLimitInfos;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate����g�Ǘ�)<BR>
     * �Ǘ��ҍ��������X�ʉ���g�Ǘ��m�F�������s�Ȃ��B<BR>
     * <BR>
     * �V�[�P���X�}�F�u�i�Ǘ��ҍ��������X�ʉ���g�Ǘ��jvalidate����g�Ǘ��v�Q�ƁB<BR>
     * ==========================================================<BR>
     * �@@�V�[�P���X�}:�i�Ǘ��ҍ��������X�ʉ���g�Ǘ��jvalidate����g�Ǘ�<BR>
     * �@@��̈ʒu    : is�S���X����( )<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@false�̏ꍇ�A�Ǘ��Ґ����`�F�b�N�G���[��Ԃ��B<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@tag   : BUSINESS_ERROR_01380<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminBondDomesticRecruitLimitManageConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 468B7E4F003D
     */
    protected WEB3AdminBondDomesticRecruitLimitManageConfirmResponse validateRecruitLimitManage(
        WEB3AdminBondDomesticRecruitLimitManageConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateRecruitLimitManage(WEB3AdminBondDomesticRecruitLimitManageConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�@@�F�@@�\�J�e�S���R�[�h.�������Ǘ�
        // is�X�V�@@�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            true);

        //is�S���X����( )
        boolean l_blnIsAllBranchsPermission = l_administrator.isAllBranchsPermission();

        //validate����g�ύX�\(String)
        //[����]
        //�@@�@@����ID�@@�F�@@���N�G�X�g�f�[�^.����ID
        this.validateRecruitLimitChangePossibility(l_request.productId);

        //false�̏ꍇ�A�Ǘ��Ґ����`�F�b�N�G���[��Ԃ��B
        if (!l_blnIsAllBranchsPermission)
        {
            log.debug("�Ǘ��Ґ����`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ǘ��Ґ����`�F�b�N�G���[");
        }

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //create���������X�ʉ���g���(long, String, String)
        //[����]
        // ����ID�F���N�G�X�g.����ID
        // �،���ЃR�[�h�F�擾�����،���ЃR�[�h
        // ���X�R�[�h�@@�@@�@@�Fnull
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondDomesticBranchRecruitLimitInfo[] l_branchRecruitLimitInfos =
            l_productManager.createAdminBondDomesticRecruitLimitInfo(
                Long.parseLong(l_request.productId),
                l_strInstitutionCode,
                null);

        //validate���X�ʉ���g(���������X�ʉ���g���[], ���������X�ʉ���g���[])
        //[����]
        //�@@�@@�@@�@@WEB3����g(�ύX�O)�Fcreate���������X�ʉ���g���̖߂�l
        //�@@�@@�@@�@@WEB3����g(�ύX��)�F���N�G�X�g.���������X�ʉ���g���
        this.validateBranchRecruitLimit(
            l_branchRecruitLimitInfos,
            l_request.bondDomesticBranchRecruitLimitInfo);

        //createResponse( )
        WEB3AdminBondDomesticRecruitLimitManageConfirmResponse l_response =
            (WEB3AdminBondDomesticRecruitLimitManageConfirmResponse)l_request.createResponse();

        //�v���p�e�B�E�Z�b�g
        //���������X�ʉ���g��� �F create���������X�ʉ���g���̖߂�l
        l_response.bondDomesticBranchRecruitLimitInfo = l_branchRecruitLimitInfos;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����g�Ǘ�)<BR>
     * �Ǘ��ҍ��������X�ʉ���g�Ǘ������������s�Ȃ��B<BR>
     * <BR>
     * �V�[�P���X�}�F�u�i�Ǘ��ҍ��������X�ʉ���g�Ǘ��jsubmit����g�Ǘ��v�Q�ƁB<BR>
     * ==========================================================<BR>
     * �@@�V�[�P���X�}:�i�Ǘ��ҍ��������X�ʉ���g�Ǘ��jsubmit����g�Ǘ�<BR>
     * �@@��̈ʒu�@@  : is�S���X����( )<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@false�̏ꍇ�A�Ǘ��Ґ����`�F�b�N�G���[��Ԃ��B<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@tag   : BUSINESS_ERROR_01380<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminBondDomesticRecruitLimitManageCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 468B7F1000AA
     */
    protected WEB3AdminBondDomesticRecruitLimitManageCompleteResponse submitRecruitLimitManage(
        WEB3AdminBondDomesticRecruitLimitManageCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " submitRecruitLimitManage(WEB3AdminBondDomesticRecruitLimitManageCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�@@�F�@@�\�J�e�S���R�[�h.�������Ǘ�
        //�@@is�X�V�@@�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            true);

        //is�S���X����( )
        boolean l_blnIsAllBranchsPermission = l_administrator.isAllBranchsPermission();

        //false�̏ꍇ�A�Ǘ��Ґ����`�F�b�N�G���[��Ԃ��B
        if (!l_blnIsAllBranchsPermission)
        {
            log.debug("�Ǘ��Ґ����`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ǘ��Ґ����`�F�b�N�G���[");
        }

        //validate����p�X���[�h(�p�X���[�h : String)
        //[����]
        //�@@�@@�p�X���[�h�@@�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //validate����g�ύX�\(String)
        //[����]
        //�@@�@@����ID�@@�F�@@���N�G�X�g�f�[�^.����ID
        this.validateRecruitLimitChangePossibility(l_request.productId);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //create���������X�ʉ���g���(long, String, String)
        //[����]
        // ����ID�F���N�G�X�g.����ID
        // �،���ЃR�[�h�F�擾�����،���ЃR�[�h
        // ���X�R�[�h�@@�@@�@@�Fnull
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondDomesticBranchRecruitLimitInfo[] l_branchRecruitLimitInfos =
            l_productManager.createAdminBondDomesticRecruitLimitInfo(
                Long.parseLong(l_request.productId),
                l_strInstitutionCode,
                null);

        //validate���X�ʉ���g(���������X�ʉ���g���[], ���������X�ʉ���g���[])
        //[����]
        //�@@�@@�@@�@@WEB3����g(�ύX�O)�Fcreate���������X�ʉ���g���̖߂�l
        //�@@�@@�@@�@@WEB3����g(�ύX��)�F���N�G�X�g.���������X�ʉ���g���
        this.validateBranchRecruitLimit(
            l_branchRecruitLimitInfos,
            l_request.bondDomesticBranchRecruitLimitInfo);

        //update����g(�Ǘ���, String, ���������X�ʉ���g���[])
        //[����]
        //�@@�@@�Ǘ��ҁF�擾�����Ǘ��҃I�u�W�F�N�g
        //�@@�@@����ID�F���N�G�X�g.����ID
        //�@@�@@���������X�ʉ���g���F���N�G�X�g.���������X�ʉ���g���
        this.updateRecruitLimit(
            l_administrator,
            l_request.productId,
            l_request.bondDomesticBranchRecruitLimitInfo);

        //createResponse( )
        WEB3AdminBondDomesticRecruitLimitManageCompleteResponse l_response =
            (WEB3AdminBondDomesticRecruitLimitManageCompleteResponse)l_request.createResponse();

        //�v���p�e�B�E�Z�b�g
        //���������X�ʉ���g��� �F create���������X�ʉ���g���̖߂�l
        l_response.bondDomesticBranchRecruitLimitInfo = l_branchRecruitLimitInfos;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate���X�ʉ���g)<BR>
     * ����g���͒l�̐������`�F�b�N���s���B<BR>
     * �ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@���Y���X�̒������z���v�i���A���j�̌v�Z���� �� �ύX���WEB3����g�i���X�ʁj<BR>
     * �A�S���X�̒������z���v�i���A���j�̌v�Z���� �� �ύX���WEB3����g�i�S���X�j<BR>
     * �B�S���X��WEB3����g�i���X�ʁj�̏W�v���� �� �ύX���WEB3����g�i�S���X�j<BR>
     * <BR>
     * �O�j�O����<BR>
     * <BR>
     * �@@�O�|�P�j<BR>
     * �@@�@@����.WEB3����g�i�ύX��j�̃n�b�V����<BR>
     * �@@�@@�ȉ��̗v�̂ŁA����.WEB3����g�i�ύX��j�̔z��̑S�Ă̗v�f���n�b�V��������B<BR>
     * <BR>
     * �@@�@@�@@�@@�L�[ �F WEB3����g�i�ύX��j.���X�R�[�h<BR>
     * �@@�@@�@@�@@�l   �F WEB3����g�i�ύX��j.WEB3����g<BR>
     * <BR>
     * �@@�@@�@@�����̃n�b�V����"�ύX��n�b�V��"�Ƃ���B<BR>
     * <BR>
     * �@@�O�|�Q�j<BR>
     * �@@�@@���X�ʉ���g���v = 0 �Ƃ���B<BR>
     * <BR>
     * �P�j����.WEB3����g�i�ύX�O�j�̗v�f��LOOP���A�e�v�f�ɂ��Ĉȉ��̏������s���B<BR>
     * <BR>
     * �@@�P�|�P�j
     * <BR>
     * �@@�@@"�ύX��n�b�V��"����WEB3����g�i�ύX�O).���X�R�[�h<BR>
     * �@@�@@�@@�Ɠ������X�R�[�h�̒l���擾�ł����ꍇ<BR>
     * �@@�@@�i�܂�AWEB3����g�i�ύX�O).���X�R�[�h�ɂ��Ă̕ύX��̒l���������ꍇ�j�A<BR>
     * �@@�@@�@@�A�̏����𖞂������ǂ����̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�@@WEB3����g�i�ύX�O�j.�������z���v > �n�b�V������擾�����l<BR>
     * <BR>
     * �@@�@@�@@��L�̏����𖞂����ꍇ�A<BR>
     * �@@�@@�@@�@@�u�������z���v��������͂��邱�Ƃ��ł��܂���v�̗�O���X���[����B<BR>
     * �@@�@@�@@����O���X���[����ꍇ�A�iWEB3Exception.errorMessage�j<BR>
     * �@@�@@�@@��WEB3����g�i�ύX�O).���X�R�[�h���Z�b�g����B<BR>
     * �@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag   : BUSINESS_ERROR_02885<BR>
     * <BR>
     * �@@���P)��LOOP�������ŕ��X�ʂ̕��X�R�[�h�ƑS���X��<BR>
     * �@@�@@���X�R�[�h"---"�����������̂ŁA<BR>
     * �@@�@@���ʂƂ��ć@@�A�̏����̃`�F�b�N���s�������ƂɂȂ�B<BR>
     * <BR>
     * �@@�P�|�Q�j<BR>
     * <BR>
     * �@@�@@�B�̏����𖞂������ǂ����̃`�F�b�N���s���O�����Ƃ��āA<BR>
     * �@@�@@�@@���X�ʉ���g�̍��v���Z�o����B<BR>
     * �@@�@@�@@��WEB3����g�i�ύX�O).���X�R�[�h="---"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�S���X�̉���g�����Z����K�v�������̂ł��̏������X�L�b�v����B<BR>
     * <BR>
     * �@@�@@�@@�P�|�Q�|�P�j<BR>
     * <BR>
     * �@@�@@�@@�@@"�ύX��n�b�V��"����WEB3����g�i�ύX�O).���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�Ɠ������X�R�[�h�̒l���擾�ł����ꍇ<BR>
     * �@@�@@�@@�@@�i�܂�AWEB3����g�i�ύX�O).���X�R�[�h�ɂ��Ă̕ύX��̒l���������ꍇ�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���X�ʉ���g���v += �n�b�V������擾�����l<BR>
     * <BR>
     * �@@�@@�@@�P�|�Q�|�Q�j<BR>
     * �@@�@@�@@�@@�擾�ł��Ȃ������ꍇ<BR>
     * �@@�@@�@@�@@�i�܂�A<BR>
     * �@@�@@�@@�@@�@@WEB3����g�i�ύX�O).���X�R�[�h�ɂ��Ă̕ύX��̒l���Ȃ������ꍇ�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���X�ʉ���g���v += WEB3����g�i�ύX�O�j.WEB3����g<BR>
     * <BR>
     * �Q�j�B�̏����𖞂������ǂ����̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�Q�|�P�j<BR>
     * �@@�@@��r�ΏۂƂȂ�S���X����g�̌���<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�P�j<BR>
     * �@@�@@�@@"�ύX��n�b�V��"���畔�X�R�[�h = "---" �̒l���擾�ł����ꍇ<BR>
     * �@@�@@�@@�i�܂�A�S���X�̕ύX��̒l���������ꍇ�j<BR>
     * <BR>
     * �@@�@@�@@�@@�S���X����g = �n�b�V������擾�����l<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�Q�j<BR>
     * �@@�@@�@@ �擾�ł��Ȃ������ꍇ<BR>
     * �@@�@@�@@�i�܂�A�S���X�̕ύX��̒l���Ȃ������ꍇ�j<BR>
     * <BR>
     * �@@�@@�@@�@@�S���X����g = WEB3����g�i�ύX�O�j.WEB3����g(*�j<BR>
     * �@@�@@�@@�@@(*)�S���X�i���X�R�[�h = "---"�j�̂���<BR>
     * <BR>
     * �@@�Q�|�Q�j<BR>
     * <BR>
     * �@@�@@�ȉ��̏����𖞂����ꍇ�A<BR>
     * �@@�@@�u���X�ʂ̉���g���v���S���X�̉���g�𒴂��Ă��܂��B�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@�S���X����g �� ���X�ʉ���g���v<BR>
     * �@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag   : BUSINESS_ERROR_02886<BR>
     * @@param l_recruitLimitBefores - (WEB3����g(�ύX�O))<BR>
     * WEB3����g(�ύX�O)<BR>
     * @@param l_recruitLimitAfters - (WEB3����g(�ύX��))<BR>
     * WEB3����g(�ύX��)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46956E9E00C0
     */
    protected void validateBranchRecruitLimit(
        WEB3BondDomesticBranchRecruitLimitInfo[] l_recruitLimitBefores,
        WEB3BondDomesticBranchRecruitLimitInfo[] l_recruitLimitAfters)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateBranchRecruitLimit("
            + "WEB3BondDomesticBranchRecruitLimitInfo[], WEB3BondDomesticBranchRecruitLimitInfo[])";
        log.entering(STR_METHOD_NAME);

        if (l_recruitLimitBefores == null || l_recruitLimitAfters == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�O�j�O����
        // �O�|�P�j
        //  ����.WEB3����g�i�ύX��j�̃n�b�V����
        //  �ȉ��̗v�̂ŁA����.WEB3����g�i�ύX��j�̔z��̑S�Ă̗v�f���n�b�V��������B
        //    �L�[ �F WEB3����g�i�ύX��j.���X�R�[�h
        //    �l   �F WEB3����g�i�ύX��j.WEB3����g
        HashMap l_hmUpdatedMap = new HashMap();
        for (int i = 0; i < l_recruitLimitAfters.length; i++)
        {
            l_hmUpdatedMap.put(
                l_recruitLimitAfters[i].branchCode,
                l_recruitLimitAfters[i].web3RecruitLimit);
        }

        //�O�|�Q�j
        //���X�ʉ���g���v = 0 �Ƃ���B
        double l_dblBranchRecruitLimitTotal = 0;
        double l_dblRecruitLimitBefore = 0;

        //�P�j����.WEB3����g�i�ύX�O�j�̗v�f��LOOP���A�e�v�f�ɂ��Ĉȉ��̏������s���B
        for (int i = 0; i < l_recruitLimitBefores.length; i++)
        {
            WEB3BondDomesticBranchRecruitLimitInfo l_branchRecruitLimitinfo =
                l_recruitLimitBefores[i];
            //�P�|�P�j
            //"�ύX��n�b�V��"����WEB3����g�i�ύX�O).���X�R�[�h�Ɠ������X�R�[�h�̒l���擾�ł����ꍇ
            double l_dblOrderAmountTotal = Double.parseDouble(l_branchRecruitLimitinfo.orderAmountTotal);
            double l_dblRecruitLimitTemp = Double.parseDouble(l_branchRecruitLimitinfo.web3RecruitLimit);
            boolean l_blnIsIncludeAllBranch =
                WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH.equals(l_branchRecruitLimitinfo.branchCode);

            if (l_hmUpdatedMap.containsKey(l_branchRecruitLimitinfo.branchCode))
            {
                //WEB3����g�i�ύX�O�j.�������z���v > �n�b�V������擾�����l
                //��L�̏����𖞂����ꍇ�A�u�������z���v��������͂��邱�Ƃ��ł��܂���v�̗�O���X���[����B
                //����O���X���[����ꍇ�A
                //�iWEB3Exception.errorMessage�j��WEB3����g�i�ύX�O).���X�R�[�h���Z�b�g����B
                double l_dblRecruitLimit =
                    Double.parseDouble((String)l_hmUpdatedMap.get(l_branchRecruitLimitinfo.branchCode));
                if (l_dblOrderAmountTotal > l_dblRecruitLimit)
                {
                    log.debug("�������z���v��������͂��邱�Ƃ��ł��܂���");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02885,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_branchRecruitLimitinfo.branchCode);
                }

                //�P�|�Q�j
                // �B�̏����𖞂������ǂ����̃`�F�b�N���s���O�����Ƃ��āA���X�ʉ���g�̍��v���Z�o����B
                //��WEB3����g�i�ύX�O).���X�R�[�h="---" �̏ꍇ�́A
                // �S���X�̉���g�����Z����K�v�������̂ł��̏������X�L�b�v����B
                // �P�|�Q�|�P�j
                //  "�ύX��n�b�V��"����WEB3����g�i�ύX�O).���X�R�[�h�Ɠ������X�R�[�h�̒l���擾�ł����ꍇ
                //  ���X�ʉ���g���v += �n�b�V������擾�����l
                if (l_blnIsIncludeAllBranch)
                {
                    l_dblRecruitLimitBefore = l_dblRecruitLimitTemp;
                    continue;
                }
                l_dblBranchRecruitLimitTotal += l_dblRecruitLimit;
            }
            else
            {
                // �P�|�Q�|�Q�j
                //  �擾�ł��Ȃ������ꍇ
                //���X�ʉ���g���v += WEB3����g�i�ύX�O�j.WEB3����g
                if (l_blnIsIncludeAllBranch)
                {
                    l_dblRecruitLimitBefore = l_dblRecruitLimitTemp;
                    continue;
                }
                l_dblBranchRecruitLimitTotal += l_dblRecruitLimitTemp;
            }
        }

        //�Q�j�B�̏����𖞂������ǂ����̃`�F�b�N���s��
        // �Q�|�P�j��r�ΏۂƂȂ�S���X����g�̌���
        double l_dblAllBranchRecruitLimit = 0;

        // �Q�|�P�|�P�j "�ύX��n�b�V��"���畔�X�R�[�h = "---" �̒l���擾�ł����ꍇ
        //  �S���X����g = �n�b�V������擾�����l
        if (l_hmUpdatedMap.containsKey(WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH))
        {
            l_dblAllBranchRecruitLimit =
                Double.parseDouble((String)l_hmUpdatedMap.get(WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH));
        }
        else
        {
            //  �Q�|�P�|�Q�j �擾�ł��Ȃ������ꍇ
            //   �S���X����g = WEB3����g�i�ύX�O�j.WEB3����g(*�j
            //   (*)�S���X�i���X�R�[�h = "---"�j�̂���
            l_dblAllBranchRecruitLimit = l_dblRecruitLimitBefore;
        }

        // �Q�|�Q�j
        //�ȉ��̏����𖞂����ꍇ�A
        //  �u���X�ʂ̉���g���v���S���X�̉���g�𒴂��Ă��܂��B�v�̗�O���X���[����B
        //  �S���X����g �� ���X�ʉ���g���v
        if (l_dblAllBranchRecruitLimit < l_dblBranchRecruitLimitTotal)
        {
            log.debug("���X�ʂ̉���g���v���S���X�̉���g�𒴂��Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02886,
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�ʂ̉���g���v���S���X�̉���g�𒴂��Ă��܂��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update����g)<BR>
     * �����X�ʉ���g�e�[�u�����X�V����B<BR>
     * <BR>
     * 1�j����.���������X�ʉ���g���̔z��̗v�f�����ȉ���LOOP�������s�Ȃ��B<BR>
     * <BR>
     * �@@1-1)�����X�ʉ���gParams�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�@@[����]�@@����ID�F����.����ID<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F����.�Ǘ���.get.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���X�R�[�h�F����.���������X�ʉ���g���.���X�R�[�h<BR>
     * <BR>
     * �@@1-2�j�����X�ʉ���gParams�I�u�W�F�N�g�̃N���[���𐶐�����B<BR>
     * <BR>
     * �@@1-3�j�N���[���Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@����g�@@�@@�@@�@@�@@�@@�F����.���������X�ʉ���g.WEB3����g<BR>
     * �@@�@@�@@�@@�ŏI�X�V�҃R�[�h�F����.�Ǘ���.get�Ǘ��҃R�[�h<BR>
     * �@@�@@�@@�@@�X�V���t�@@�@@�@@�@@�F<BR>
     * �@@�@@�@@�@@�@@GtlUtils.getTradingSystem(�@@).getSystemTimestamp(�@@)�̖߂�l<BR>
     * <BR>
     * �@@1-4�j�N���[���̓��e��DB���X�V����B<BR>
     * <BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ���<BR>
     * @@param l_strProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_bondDomesticBranchRecruitLimitInfos - (���������X�ʉ���g���)<BR>
     * ���������X�ʉ���g���<BR>
     * @@throws WEB3BaseException
     * @@roseuid 469C22EE00D7
     */
    protected void updateRecruitLimit(
        WEB3Administrator l_administrator,
        String l_strProductId,
        WEB3BondDomesticBranchRecruitLimitInfo[] l_bondDomesticBranchRecruitLimitInfos)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateRecruitLimit(WEB3Administrator, String,"
            + " WEB3BondDomesticBranchRecruitLimitInfo[])";
        log.entering(STR_METHOD_NAME);

        if (l_bondDomesticBranchRecruitLimitInfos == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //1�j����.���������X�ʉ���g���̔z��̗v�f�����ȉ���LOOP�������s�Ȃ��B
        for (int i = 0; i < l_bondDomesticBranchRecruitLimitInfos.length; i++)
        {
            WEB3BondDomesticBranchRecruitLimitInfo l_branchRecruitLimitInfo =
                l_bondDomesticBranchRecruitLimitInfos[i];
            //1-1)�����X�ʉ���gParams�I�u�W�F�N�g�𐶐�����B
            //����]  ����ID�F����.����ID
            //       �،���ЃR�[�h�F����.�Ǘ���.get.�،���ЃR�[�h
            //       ���X�R�[�h�F����.���������X�ʉ���g���.���X�R�[�h
            BondBranchRecruitLimitRow l_bondBranchRecruitLimitRow = null;
            try
            {
                l_bondBranchRecruitLimitRow =
                    (BondBranchRecruitLimitRow)BondBranchRecruitLimitDao.findRowByProductIdInstitutionCodeBranchCode(
                        Long.parseLong(l_strProductId),
                        l_administrator.getInstitutionCode(),
                        l_branchRecruitLimitInfo.branchCode);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            if (l_bondBranchRecruitLimitRow != null)
            {
                BondBranchRecruitLimitParams l_params =
                    new BondBranchRecruitLimitParams(l_bondBranchRecruitLimitRow);

                //1-2�j�����X�ʉ���gParams�I�u�W�F�N�g�̃N���[���𐶐�����
                BondBranchRecruitLimitParams l_updateParams =
                    new BondBranchRecruitLimitParams(l_params);

                //1-3�j�N���[���Ƀv���p�e�B���Z�b�g����B
                // ����g�@@�@@�@@�@@�@@�@@�F����.���������X�ʉ���g.WEB3����g
                l_updateParams.setRecruitLimit(
                    Double.parseDouble(l_branchRecruitLimitInfo.web3RecruitLimit));

                // �ŏI�X�V�҃R�[�h�F����.�Ǘ���.get�Ǘ��҃R�[�h
                l_updateParams.setLastUpdater(l_administrator.getAdministratorCode());

                // �X�V���t          �FGtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
                l_updateParams.setLastUpdatedTimestamp(
                    GtlUtils.getTradingSystem().getSystemTimestamp());

                try
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doUpdateQuery(l_updateParams);
                }
                catch (DataQueryException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate����g�ύX�\)<BR>
     * ����g�̕ύX�\�`�F�b�N<BR>
     * <BR>
     * �P�j���v���_�N�g�}�l�[�W��.get������()�����������擾����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�@@����ID�@@�F����.����ID<BR>
     * <BR>
     * �Q�j����g�ύX�\���Ԃ̃`�F�b�N<BR>
     * �@@�@@�@@������.����I�����iSONAR)�����ݓ����@@�̏ꍇ�A<BR>
     * �@@�@@�@@��O�u����g�ύX�\���ԊO�ł��B�v���X���[����B<BR>
     * �@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@�@@:�@@BUSINESS_ERROR_02901<BR>
     * <BR>
     * @@param l_strProductId - (����ID)<BR>
     * ����ID<BR>
     * @@throws WEB3BaseException
     */
    protected void validateRecruitLimitChangePossibility(String l_strProductId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRecruitLimitChangePossibility(String)";
        log.entering(STR_METHOD_NAME);

        // �P�j���v���_�N�g�}�l�[�W��.get������()�����������擾����B
        // �@@�@@[����]
        // �@@�@@�@@�@@����ID�@@�F����.����ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();

        WEB3BondProduct l_bondProduct  =
            (WEB3BondProduct)l_bondProductManager.getBondProduct(Long.parseLong(l_strProductId));

        // �Q�j����g�ύX�\���Ԃ̃`�F�b�N
        // �@@�@@�@@������.����I�����iSONAR)�����ݓ����@@�̏ꍇ�A
        // �@@�@@�@@��O�u����g�ύX�\���ԊO�ł��B�v���X���[����B
        if (WEB3DateUtility.compareToDay(l_bondProduct.getHostRecruitEndDate(),
            GtlUtils.getSystemTimestamp()) < 0)
        {
            log.debug("����g�ύX�\���ԊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02901,
                this.getClass().getName() + STR_METHOD_NAME,
                "����g�ύX�\���ԊO�ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
