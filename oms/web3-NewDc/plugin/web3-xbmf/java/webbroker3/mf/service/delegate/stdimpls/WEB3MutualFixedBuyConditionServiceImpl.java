head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t���������o�^�T�[�r�XImpl(WEB3MutualFixedBuyConditionServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/16 ���n(���u) �V�K�쐬 ���f��No.608,615,616,617,618,619
Revision History : 2008/07/31 ���g(���u) �d�l�ύX ���f��No.620,622,623,624
Revision History : 2008/08/05 ���g(���u) �d�l�ύX ���f��No.625
Revision History : 2008/08/06 ���g(���u) �d�l�ύX ���f��No.626
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChangeDivDef;
import webbroker3.common.define.WEB3FinInstitutionDivDef;
import webbroker3.common.define.WEB3MFStatusDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.gentrade.message.WEB3GentradeMultiCheckResults;
import webbroker3.gentrade.message.WEB3GentradeMultiDocCheckResultUnit;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFixedBuyCloseDateDrawDateCalc;
import webbroker3.mf.WEB3MutualFixedBuyCommonService;
import webbroker3.mf.WEB3MutualFixedBuyDrawAccount;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualProductCategory;
import webbroker3.mf.data.MfFixedBuyingChangeHistParams;
import webbroker3.mf.data.MfFixedBuyingChangeHistRow;
import webbroker3.mf.data.MfFixedBuyingChangeParams;
import webbroker3.mf.data.MfFixedBuyingChangeRow;
import webbroker3.mf.data.MfFixedBuyingCondRow;
import webbroker3.mf.data.MutualFundProductCategoryRow;
import webbroker3.mf.define.WEB3MFFirstDisplayDivDef;
import webbroker3.mf.define.WEB3MFSonarSendCheckDivDef;
import webbroker3.mf.message.WEB3MutualFixedBuyAccountInfo;
import webbroker3.mf.message.WEB3MutualFixedBuyCommonUnit;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionUnit;
import webbroker3.mf.message.WEB3MutualFixedBuyTotalUnit;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyConditionService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (���M�莞��z���t���������o�^�T�[�r�XImpl)<BR>
 * ���M�莞��z���t���������o�^�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3MutualFixedBuyConditionServiceImpl extends WEB3MutualClientRequestService
    implements WEB3MutualFixedBuyConditionService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionServiceImpl.class);

    /**
     * @@roseuid 487597AA0167
     */
    public WEB3MutualFixedBuyConditionServiceImpl()
    {

    }

    /**
     * ���M�莞��z���t���������o�^�T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * input�莞��z���t���������o�^()�A<BR>
     * validate�莞��z���t���������o�^()�A<BR>
     * submit�莞��z���t���������o�^()�����ꂩ�̃��\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4851C24201FE
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
        else if (l_request instanceof WEB3MutualFixedBuyConditionInputRequest)
        {

            l_response = this.inputMutualFixedBuyCondition((WEB3MutualFixedBuyConditionInputRequest)l_request);
        }
        else if (l_request instanceof WEB3MutualFixedBuyConditionConfirmRequest)
        {
            l_response = this.validateMutualFixedBuyCondition((WEB3MutualFixedBuyConditionConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3MutualFixedBuyConditionCompleteRequest)
        {
            l_response = this.submitMutualFixedBuyCondition((WEB3MutualFixedBuyConditionCompleteRequest)l_request);
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
     * (input�莞��z���t���������o�^)<BR>
     * �����M���莞��z���t���������o�^���͂��s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * <BR>
     * =========================================================<BR>
     * ��̈ʒu�Fis�d�q����Q���̖߂�l == true�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@BUSINESS_ERROR_01984�u��Q�������s�v�G���[���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_01984<BR>
     * =========================================================<BR>
     * <BR>
     * �u�i���M�j�莞��z���t���������o�^���́v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MutualFixedBuyConditionInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4851C2F00242
     */
    protected WEB3MutualFixedBuyConditionInputResponse inputMutualFixedBuyCondition(
        WEB3MutualFixedBuyConditionInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "inputMutualFixedBuyCondition(WEB3MutualFixedBuyConditionInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        l_request.validate();

        //validate������t�\
        //�ً}��~����Ă��Ȃ����A���邢�̓o�b�`�������łȂ����`�F�b�N����
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //get�⏕����
        //���O�C���Z�L�����e�B�T�[�r�X���⏕�ڋq���擾����
        SubAccount l_subAccount = this.getSubAccount();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //getCommonOrderValidator
        //�����`�F�b�N�I�u�W�F�N�g���擾����
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //get������
        //���������擾����
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //validate����\�ڋq
        //���Y�ڋq������\�Ȍڋq���ǂ����𔻒肷��
        //[validate����\�ڋq�ɓn������]
        //�@@�ڋq�F�擾�����⏕����.getMainAccount()
        //�@@�������F������ԊǗ�.get������()�̖߂�l
        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        OrderValidationResult l_validateAccountForTrading = l_orderValidator.validateAccountForTrading(
            l_genMainAccount,
            new Timestamp(l_datOrderBizDate.getTime()));

        if (!l_validateAccountForTrading.getProcessingResult().isSuccessfulResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validateAccountForTrading.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //validate���������o�^
        //�����������o�^�ς��ǂ����`�F�b�N���s��
        //�mvalidate���������o�^�̈����n
        //�،���ЃR�[�h�F�擾�����⏕����.getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�F�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        //�����R�[�h�F�擾�����⏕����.getMainAccount().getAccountCode()�̖߂�l
        String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strMainAccountCode = l_subAccount.getMainAccount().getAccountCode();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);

        l_mutualFixedBuyCommonService.validateDrawAccountRegist(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode,
            l_strMainAccountCode);

        //get���M�����J�e�S���[���X�g
        //���M�����J�e�S���[���X�g���擾����
        //[get���M�����J�e�S���[���X�g�ɓn���p�����^]
        //�،���ЃR�[�h�F�擾�����⏕����.getInstitution().getInstitutionCode()�̖߂�l
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
        List l_lisMFProductCategorysLists =
            l_mfProductManager.getMutualFundProductCategoryList(l_strSubAccountInstitutionCode);

        WEB3MutualProductCategoryUnit[] l_mutualProductCategoryUnits = null;
        //<���򏈗�> get���M�����J�e�S���[���X�g�i�j�̖߂�l > 0���̏ꍇ
        if (l_lisMFProductCategorysLists != null && l_lisMFProductCategorysLists.size() > 0)
        {
            //create���M�����J�e�S���[�ꗗ(List)
            l_mutualProductCategoryUnits =
                l_mfProductManager.createMutualFundProductCategoryList(l_lisMFProductCategorysLists);
        }

        //calc�K�p�J�n�N���i�Ɩ����t�j
        //[calc�K�p�J�n�N�����i�Ɩ����t�j�̈���]
        //�،���ЃR�[�h�F�擾�����⏕����.getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�F�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        Date l_datOrderBiz = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode);

        //calc�K�p�J�n�N���i���ݓ����j
        //[calc�K�p�J�n�N�����i���ݓ����j�̈���]
        //�،���ЃR�[�h�F�擾�����⏕����.getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�F�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        Date l_datCurrent = l_mutualFixedBuyCommonService.calcValidStartDateCurrentDate(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode);

        //merge�莞��z���t�����ύX
        //��ʕ\������A�ϗ��o�^�f�[�^�ƐV�K�ǉ��f�[�^���擾����
        //�mmerge�莞��z���t�����ύX�̈����n
        //�⏕�����F�擾�����⏕����
        //�K�p�J�n�N���Fcalc�K�p�J�n�N���i�Ɩ����t�j�̖߂�l
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            this.mergeMutualFixedBuyConditionChange(l_subAccount, l_datOrderBiz);

        boolean l_blnFutureCustomer = false;
        WEB3MutualFixedBuyConditionUnit[] l_mutualfixedBuyConditionUnitSorts = null;
        WEB3MutualFixedBuyTotalUnit[] l_mutualFixedBuyTotalUnits = null;
        Institution l_institution = l_subAccount.getInstitution();
        int l_intMutualFixedBuyConditionUnitsLength = 0;
        List l_lisUnitLists = new ArrayList();
        Iterator l_iteUnitLists  = null;
        if (l_mutualFixedBuyConditionUnits != null)
        {
            l_intMutualFixedBuyConditionUnitsLength = l_mutualFixedBuyConditionUnits.length;
            for (int i = 0; i < l_intMutualFixedBuyConditionUnitsLength; i++)
            {
                l_lisUnitLists.add(l_mutualFixedBuyConditionUnits[i]);
            }
            l_iteUnitLists = l_lisUnitLists.iterator();
        }
        
        //<���򏈗�> merge�莞��z���t�����ύX�̌����̖߂�l��0���ȊO�̏ꍇ
        if (l_mutualFixedBuyConditionUnits != null && l_intMutualFixedBuyConditionUnitsLength != 0)
        {

            //<LOOP����> merge�莞��z���t�����ύX�̌����̖߂�l�̗v�f����LOOP
            while(l_iteUnitLists.hasNext())
            {
                //get���M����
                //�g�����M�����̎擾
                //[����]
                //�@@�،���ЁF�擾�����⏕�����I�u�W�F�N�g.getInstitution()�̖߂�l
                //�@@�����R�[�h�F���M�莞��z���t�����s.�����R�[�h
                //�@@�񍆃R�[�h�F0
                WEB3MutualFundProduct l_mutualFundProduct = null;
                try
                {
                    WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                        (WEB3MutualFixedBuyConditionUnit) l_iteUnitLists.next();
                    l_mutualFundProduct = 
                        (WEB3MutualFundProduct)l_mfProductManager.getMutualFundProduct(
                        l_institution, l_mutualFixedBuyConditionUnit.mutualProductCode, "0");
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //is���t�\
                //���t�\���Ԃ̃`�F�b�N
                //[����]
                //�@@�������Fget�������̖߂�l
                boolean l_blnAcquirePossible = l_mutualFundProduct.isAcquiredPossible(l_datOrderBizDate);

                //<���򏈗�> is���t�\�̖߂�l���Afalse�̏ꍇ
                if(l_blnAcquirePossible == false)
                {
                    //merge�莞��z���t�����ύX�̌����̖߂�l���폜����
                       l_iteUnitLists.remove();
                }
            }

            l_mutualFixedBuyConditionUnits =
                new WEB3MutualFixedBuyConditionUnit[l_lisUnitLists.size()];
            l_lisUnitLists.toArray(l_mutualFixedBuyConditionUnits);

            //����������ڋq�̔��f
            //���Y�ڋq�̃f�[�^�����������ǂ������f����B
            //�ȉ��̏ꍇ�A����������ڋq�t���O��true���Z�b�g����
            // merge�莞��z���t�����ύX�̖߂�l[0]�D�K�p�J�n�N�� >  calc�K�p�J�n�N��(���ݓ����j�̖߂�l�̏ꍇ�A
            //true���Z�b�g����
            if (l_lisUnitLists != null && l_lisUnitLists.size() != 0)
            {

                if (WEB3DateUtility.compareToMonth(l_mutualFixedBuyConditionUnits[0].validStartDate, l_datCurrent) > 0)
                {
                    l_blnFutureCustomer = true;
                }

                //sort�莞��z���t�����ꗗ
                //[sort���M�莞��z���t����()�ɓn������]
                //���M�莞��z���t�����s[ ]�Fmerge�莞��z���t�����ύX�̖߂�l
                l_mutualfixedBuyConditionUnitSorts =
                    l_mutualFixedBuyCommonService.sortFixedBuyConditionList(l_mutualFixedBuyConditionUnits);

                //get�莞��z���t���z���v
                //�莞��z���t���t���z�̍��v���擾����B
                //[get�莞��z���t���t���z���v�̈���]
                //���M�莞��z���t�����s[ ]�Fsort�莞��z���t�����ꗗ�̖߂�l
                l_mutualFixedBuyTotalUnits =
                    this.getFixedBuyTotalUnit(l_mutualfixedBuyConditionUnitSorts);
            }
        }

        List l_lisLowMutualFundProductCategoryLists = null;
        //<���򏈗�> ���N�G�X�g�D�J�e�S���R�[�h��null�ȊO�̏ꍇ
        if (l_request.categoryCode != null)
        {
            //get���M�����J�e�S���[(String, String)
            //�،���ЃR�[�h =  �擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
            //���M�����J�e�S���[�R�[�h = ���N�G�X�g�D�J�e�S���[�R�[�h
            WEB3MutualProductCategory l_mutualProductCategory =
                l_mfProductManager.getMutualFundProductCategory(
                    l_strSubAccountInstitutionCode,
                    l_request.categoryCode);

            //get���ʓ��M�����J�e�S���[���X�g
            //�w�肳�ꂽ�J�e�S���[�R�[�h�ɕR�t�����ʃJ�e�S���[�̓��M�����J�e�S���[Params�̃��X�g��Ԃ��B
            //get���ʓ��M�����J�e�S���[���X�g�̈����n
            //�،���ЃR�[�h =  �擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
            //�J�e�S���[�R�[�h = ���N�G�X�g�D�J�e�S���[�R�[�h
            l_lisLowMutualFundProductCategoryLists = l_mfProductManager.getLowMutualFundProductCategoryList(
                l_strSubAccountInstitutionCode,
                l_request.categoryCode);

            //get���ʓ��M�����J�e�S���[���X�g�̖߂�l��get���M�����J�e�S���[�̖߂�l��ǉ�����B
            if (l_mutualProductCategory != null)
            {
                l_lisLowMutualFundProductCategoryLists.add(
                    (MutualFundProductCategoryRow)l_mutualProductCategory.getDataSourceObject());
            }
        }

        List l_lisFixedBuyPossibleProductLists = null;
        WEB3MutualFixedBuyConditionUnit[] l_mfBuyConditionUnitNews = null;

        //<���򏈗�> �i ���N�G�X�g�D����\���t���O == �u0�F�\������v)�@@�܂���
        //�@@�i���N�G�X�g�D����\���t���O == �u2�F�{���`�F�b�N���v�@@���@@���N�G�X�g.�����R�[�h != null �j�j �̏ꍇ
        if ((WEB3MFFirstDisplayDivDef.DISPLAY.equals(l_request.firstDisplayDiv)
            || (WEB3MFFirstDisplayDivDef.READING_CHECK.equals(l_request.firstDisplayDiv)
                && l_request.productCode != null)))
        {

            //<���򏈗�> ����������ڋq�t���O == true�̏ꍇ�A�G���[���X���[����B
            if(l_blnFutureCustomer)
            {
                log.debug("�������\�����������邽�߁A�V�K�����ǉ��s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03110,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������\�����������邽�߁A�V�K�����ǉ��s�B");
            }

            //�莞��z�Ŕ��t�\�Ȗ������擾����
            //�mget�莞��z���t�\���X�g�̈����n
            //�،���ЃR�[�h =  �擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
            //�����R�[�h�ꗗ = ���N�G�X�g�D�����R�[�h
            //�J�e�S���[���X�g = get���ʓ��M�����J�e�S���[���X�g�̖߂�l��get���M�����J�e�S���[�̖߂�l��ǉ��������X�g
            l_lisFixedBuyPossibleProductLists = l_mfProductManager.getFixedBuyPossibleProductList(
                l_strSubAccountInstitutionCode,
                l_request.productCode,
                l_lisLowMutualFundProductCategoryLists);

            int l_intFixedBuyPossibleProductListCnt = l_lisFixedBuyPossibleProductLists.size();
            //<LOOP����> get�莞��z���t�\���X�g�̖߂�l�̗v�f����LOOP
            l_mfBuyConditionUnitNews = new WEB3MutualFixedBuyConditionUnit[l_intFixedBuyPossibleProductListCnt];
            for (int i = 0; i < l_intFixedBuyPossibleProductListCnt; i++)
            {
                //���M�莞��z���t�����s( )
                l_mfBuyConditionUnitNews[i] =
                    new WEB3MutualFixedBuyConditionUnit();

                //�v���p�e�B�Z�b�g
                //�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
                //�����R�[�h�F�g�����M����.getProductCode
                WEB3MutualFundProduct l_fundProduct =
                    (WEB3MutualFundProduct)l_lisFixedBuyPossibleProductLists.get(i);
                MutualFundProductRow l_row =
                    (MutualFundProductRow)l_fundProduct.getDataSourceObject();
                l_mfBuyConditionUnitNews[i].mutualProductCode = l_row.getProductCode();
                //�������F�擾�����g�����M����.get������
                l_mfBuyConditionUnitNews[i].mutualProductName = l_row.getStandardName();
                //���M�����J�e�S���[�R�[�h�F�擾�����g�����M����.get�J�e�S���R�[�h
                l_mfBuyConditionUnitNews[i].categoryCode = l_row.getCategoryCode();
                //���t���z�i���X�j�Fnull
                l_mfBuyConditionUnitNews[i].monthlyBuyAmount = null;
                //���t���z�i�ςݑ����j�Fnull
                l_mfBuyConditionUnitNews[i].increaseBuyAmount = null;
                //�����\�������F�擾�����g�����M����.get�\������
                if (l_row.getIndicationRankingIsNull())
                {
                    l_mfBuyConditionUnitNews[i].displayOrder = null;
                }
                else
                {
                    l_mfBuyConditionUnitNews[i].displayOrder =
                        l_row.getIndicationRanking() + "";
                }
                //�K�p�J�n�N���Fcalc�K�p�J�n�N���i���ݓ����j�̖߂�l
                l_mfBuyConditionUnitNews[i].validStartDate = l_datCurrent;
                //�X�V�����Fnull
                l_mfBuyConditionUnitNews[i].updateDate = null;
                //���������N���Fnull
                l_mfBuyConditionUnitNews[i].debitAccountYM = null;
                //�m��������z�i�ςݑ����j�Fnull
                l_mfBuyConditionUnitNews[i].definiteIncreaseBuyAmount = null;
                //�ژ_�����{���`�F�b�N�Fnull
                l_mfBuyConditionUnitNews[i].checkResult = null;
                //�ꎞ��~���t���O�Ffalse
                l_mfBuyConditionUnitNews[i].suspensionFlag = false;
            }
        }

        int l_intMutualFixedBuyConditionUnitsCnt = 0;
        if (l_mutualFixedBuyConditionUnits != null)
        {
            l_intMutualFixedBuyConditionUnitsCnt = l_mutualFixedBuyConditionUnits.length;
        }
        WEB3GentradeMultiDocCheckResultUnit[] l_gentradeMultiDocCheckResultUnits =
            new WEB3GentradeMultiDocCheckResultUnit[l_intMutualFixedBuyConditionUnitsCnt];
        String l_strUpMutualFundProductCategoryCode = null;
        WEB3GentradeBatoClientService l_gentradeBatoClientService = null;
        //<���򏈗�> ���N�G�X�g�D�d�q���`�F�b�N�t���O == true�̏ꍇ
        if (l_request.batoCheckFlag)
        {
            //is�d�q����~��
            //is�d�q����Q���̖߂�l == true�̏ꍇ�A
            //BUSINESS_ERROR_01984�u��Q�������s�v�G���[���X���[����B
            l_gentradeBatoClientService =
                (WEB3GentradeBatoClientService)Services.getService(
                    WEB3GentradeBatoClientService.class);
            if (l_gentradeBatoClientService.isBatoStopping())
            {
                log.debug("[�d�q���V�X�e����Q��]��Q�������s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01984,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "[�d�q���V�X�e����Q��]��Q�������s�B");
            }

            //<LOOP����> merge�莞��z���t�����ύX�̖߂�l�̌�����LOOP���A
            //���������ژ_�����{���`�F�b�NUnit�N���X�̔z����쐬����

            for (int i = 0; i < l_intMutualFixedBuyConditionUnitsCnt; i++)
            {
                //<���򏈗�> ���N�G�X�g�D��ʃR�[�h == null�̏ꍇ
                if (l_request.categoryCode == null)
                {
                    //get��ʓ��M�����J�e�S���[�R�[�h
                    //�w�肳�ꂽ�����̐e�J�e�S���[�R�[�h���擾����B
                    //�ivalidate�ژ_�����{���Ŏg�p�j
                    //�mget��ʓ��M�����J�e�S���[���X�g�̈����n
                    //�J�e�S���[�R�[�h = merge�莞��z���t�����ύX�̖߂�l.�J�e�S���R�[�h
                    //��ЃR�[�h =  �擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
                    l_strUpMutualFundProductCategoryCode = l_mfProductManager.getUpMutualFundProductCategoryCode(
                        l_mutualFixedBuyConditionUnits[i].categoryCode,
                        l_strSubAccountInstitutionCode);
                }

                //���������ژ_�����{���`�F�b�N����Unit�N���X
                l_gentradeMultiDocCheckResultUnits[i] = new WEB3GentradeMultiDocCheckResultUnit();
                //���L�̒ʂ�A�v���p�e�B���Z�b�g����B
                //�����R�[�h = merge�莞��z���t�����ύX�̖߂�l�D�����R�[�h
                //��ʃR�[�h = ���N�G�X�g�D��ʃR�[�h == null�̏ꍇ�Aget��ʓ��M�����J�e�S���[�R�[�h�̖߂�l(0���l��3��)
                //             ���N�G�X�g�D��ʃR�[�h == null�ȊO�̏ꍇ�A���N�G�X�g�D��ʃR�[�h
                //�`�F�b�N���� = null
                l_gentradeMultiDocCheckResultUnits[i].productCode =
                    l_mutualFixedBuyConditionUnits[i].mutualProductCode;
                if (l_request.typeCode == null)
                {
                    if (l_strUpMutualFundProductCategoryCode == null
                        || l_strUpMutualFundProductCategoryCode.length() == 0)
                    {
                        l_strUpMutualFundProductCategoryCode = "000";
                    }
                    else if (l_strUpMutualFundProductCategoryCode.length() == 1)
                    {
                        l_strUpMutualFundProductCategoryCode = "00" + l_strUpMutualFundProductCategoryCode;
                    }
                    else if (l_strUpMutualFundProductCategoryCode.length() == 2)
                    {
                        l_strUpMutualFundProductCategoryCode = "0" + l_strUpMutualFundProductCategoryCode;
                    }
                    l_gentradeMultiDocCheckResultUnits[i].typeCode = l_strUpMutualFundProductCategoryCode;
                }
                else
                {
                    l_gentradeMultiDocCheckResultUnits[i].typeCode = l_request.typeCode;
                }
                l_gentradeMultiDocCheckResultUnits[i].checkResult = null;
            }

            WEB3GentradeMultiCheckResults l_gentradeMultiCheckResults = null;
            //<���򏈗�> ���������ژ_�����{���`�F�b�NUnit�N���X�̔z��null�ȊO���T�C�Y��0�ȊO�̏ꍇ
            if (l_gentradeMultiDocCheckResultUnits != null
                && l_gentradeMultiDocCheckResultUnits.length != 0)
            {
                //validate���������ژ_�����{��
                //�ژ_�������{���ς����ς��`�F�b�N����B
                //�mvalidate���������ژ_�����{���̈����n
                //���������ژ_�����{���`�F�b�N���X�g = ���������ژ_�����{���`�F�b�NUnit�N���X�m�n
                //�㗝���͕s���`�F�b�N�t���O = false�F�`�F�b�N���Ȃ�
                l_gentradeMultiCheckResults = l_gentradeBatoClientService.validateMultiProspectus(
                    l_gentradeMultiDocCheckResultUnits,
                    false);
            }

            // <LOOP����> merge�莞��z���t�����ύX�̖߂�l�̌�����LOOP���A
            //���M�莞��z���t�����s�ɖژ_�����{���`�F�b�N���ʂ��Z�b�g����
            for (int i = 0; i < l_intMutualFixedBuyConditionUnitsCnt; i++)
            {
                //�v���p�e�B�Z�b�g
                //�莞��z���t�����s�I�u�W�F�N�g�D�ژ_�����{���`�F�b�N�ɁA�v���p�e�B���Z�b�g����B
                //validate���������ژ_�����{���̖߂�l[index]�D�`�F�b�N���ʂ�0�F �{���ς̏ꍇ
                //�@@�@@�ژ_�����{���`�F�b�N =  0�F �{����
                //validate���������ژ_�����{���̖߂�l[index]�D�`�F�b�N���ʂ�1�F �{�����ς̏ꍇ
                //�@@�@@�ژ_�����{���`�F�b�N =  1�F �{������
                if (WEB3GentradeBatoCheckResultDef.INSPECTION.equals(
                    l_gentradeMultiCheckResults.checkResult[i].checkResult))
                {
                    l_mutualFixedBuyConditionUnits[i].checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
                }
                else if (WEB3GentradeBatoCheckResultDef.UNINSPECTION.equals(
                    l_gentradeMultiCheckResults.checkResult[i].checkResult))
                {
                    l_mutualFixedBuyConditionUnits[i].checkResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION;
                }
            }

            //<LOOP����> �V�K�ǉ����M�莞��z���t�����s�̗v�f����LOOP���A
            //���������ژ_�����{���`�F�b�NUnit�N���X�̔z����쐬����
            l_gentradeMultiDocCheckResultUnits = null;
            if (l_mfBuyConditionUnitNews != null && l_mfBuyConditionUnitNews.length > 0)
            {
                int l_intMfBuyConditionUnitNewsCnt = l_mfBuyConditionUnitNews.length;
                l_gentradeMultiDocCheckResultUnits =
                    new WEB3GentradeMultiDocCheckResultUnit[l_intMfBuyConditionUnitNewsCnt];
                for (int i = 0; i < l_intMfBuyConditionUnitNewsCnt; i++)
                {
                    //<���򏈗�> ���N�G�X�g�D��ʃR�[�h == null�̏ꍇ
                    //�w�肳�ꂽ�����̐e�J�e�S���[�R�[�h���擾����B
                    //�ivalidate�ژ_�����{���Ŏg�p�j
                    //�mget��ʓ��M�����J�e�S���[���X�g�̈����n
                    //�J�e�S���[�R�[�h = �V�K�ǉ����M�莞��z���t�����s�D�J�e�S���[�R�[�h
                    //��ЃR�[�h =  �擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
                    if (l_request.typeCode == null)
                    {
                        l_strUpMutualFundProductCategoryCode = l_mfProductManager.getUpMutualFundProductCategoryCode(
                            l_mfBuyConditionUnitNews[i].categoryCode,
                            l_strSubAccountInstitutionCode);
                    }

                    //���������ژ_�����{���`�F�b�N����Unit�N���X
                    l_gentradeMultiDocCheckResultUnits[i] =
                        new WEB3GentradeMultiDocCheckResultUnit();

                    //�v���p�e�B�Z�b�g
                    //���L�̒ʂ�A�v���p�e�B���Z�b�g����B
                    //�����R�[�h = �V�K�ǉ����M�莞��z���t�����s�D�����R�[�h
                    //��ʃR�[�h = ���N�G�X�g�D��ʃR�[�h == null�̏ꍇ�Aget��ʓ��M�����J�e�S���[�R�[�h�̖߂�l(0���l��3��)
                    //���N�G�X�g�D��ʃR�[�h == null�ȊO�̏ꍇ�A���N�G�X�g�D��ʃR�[�h
                    //�`�F�b�N���� = null
                    l_gentradeMultiDocCheckResultUnits[i].productCode =
                        l_mfBuyConditionUnitNews[i].mutualProductCode;
                    if (l_request.typeCode == null)
                    {
                        if (l_strUpMutualFundProductCategoryCode == null
                            || l_strUpMutualFundProductCategoryCode.length() == 0)
                        {
                            l_strUpMutualFundProductCategoryCode = "000";
                        }
                        else if (l_strUpMutualFundProductCategoryCode.length() == 1)
                        {
                            l_strUpMutualFundProductCategoryCode = "00" + l_strUpMutualFundProductCategoryCode;
                        }
                        else if (l_strUpMutualFundProductCategoryCode.length() == 2)
                        {
                            l_strUpMutualFundProductCategoryCode = "0" + l_strUpMutualFundProductCategoryCode;
                        }
                        l_gentradeMultiDocCheckResultUnits[i].typeCode = l_strUpMutualFundProductCategoryCode;
                    }
                    else if (l_request.typeCode != null)
                    {
                        l_gentradeMultiDocCheckResultUnits[i].typeCode = l_request.typeCode;
                    }
                    l_gentradeMultiDocCheckResultUnits[i].checkResult = null;
                }
            }

            //<���򏈗�> ���������ژ_�����{���`�F�b�NUnit�N���X�̔z��null�ȊO���T�C�Y��0�ȊO�̏ꍇ
            if (l_gentradeMultiDocCheckResultUnits != null
                && l_gentradeMultiDocCheckResultUnits.length != 0)
            {
                //validate���������ژ_�����{��(���������ژ_�����{���`�F�b�N���X�g
                //�ژ_�������{���ς����ς��`�F�b�N����B
                //�mvalidate���������ژ_�����{���̈����n
                //���������ژ_�����{���`�F�b�N���X�g = ���������ژ_�����{���`�F�b�NUnit�N���X�̔z��
                //�㗝���͕s���`�F�b�N�t���O = false�F�`�F�b�N���Ȃ�
                l_gentradeMultiCheckResults = l_gentradeBatoClientService.validateMultiProspectus(
                    l_gentradeMultiDocCheckResultUnits,
                    false);
            }

            //<LOOP����> �V�K�ǉ����M�莞��z���t�����s�̗v�f����LOOP���A
            //���M�莞��z���t�����s�ɖژ_�����{���`�F�b�N���ʂ��Z�b�g����
            if (l_mfBuyConditionUnitNews != null && l_mfBuyConditionUnitNews.length > 0)
            {
                int l_intMfBuyConditionUnitNewsCnt = l_mfBuyConditionUnitNews.length;
                for (int i = 0; i< l_intMfBuyConditionUnitNewsCnt; i++)
                {
                    //�v���p�e�B�Z�b�g
                    //�莞��z���t�����s�I�u�W�F�N�g�D�ژ_�����{���`�F�b�N�ɁA�v���p�e�B���Z�b�g����B
                    //validate���������ژ_�����{���̖߂�l[index]�D�`�F�b�N���ʂ�0�F �{���ς̏ꍇ
                    //�ژ_�����{���`�F�b�N =  0�F �{����
                    //validate���������ژ_�����{���̖߂�l[index]�D�`�F�b�N���ʂ�1�F �{�����ς̏ꍇ
                    //�ژ_�����{���`�F�b�N =  1�F �{������
                  if (WEB3GentradeBatoCheckResultDef.INSPECTION.equals(
                      l_gentradeMultiCheckResults.checkResult[i].checkResult))
                  {
                      l_mfBuyConditionUnitNews[i].checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
                  }
                  else if (WEB3GentradeBatoCheckResultDef.UNINSPECTION.equals(
                      l_gentradeMultiCheckResults.checkResult[i].checkResult))
                  {
                      l_mfBuyConditionUnitNews[i].checkResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION;
                  }
                }
            }
        }

        //�莞��z���t��������
        //�莞��z���t���������N���X�𐶐�����B
        //[�莞��z���t���������ɓn������]
        //�@@�،���ЃR�[�h�F�擾�����⏕����.getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�F�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        //�����R�[�h�F�擾�����⏕����.getMainAccount().getAccountCode()�̖߂�l
        WEB3MutualFixedBuyDrawAccount l_mfBuyDrawAccount = null;
        try
        {
            l_mfBuyDrawAccount = new WEB3MutualFixedBuyDrawAccount(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode,
                l_strMainAccountCode);
        }
        catch (WEB3BaseException l_be)
        {
            log.debug("�莞��z���t���������ɊY������f�[�^������܂���!");
        }
        //<���򏈗�> �莞��z���t���������Dget���Z�@@�֋敪=��s�̏ꍇ
        String[] l_strFinBranchNames = null;
        if (l_mfBuyDrawAccount != null)
        {
            if (WEB3FinInstitutionDivDef.BANK.equals(l_mfBuyDrawAccount.getFinInstitutionDiv()))
            {
                //get��s�x�X��
                //[get��s�x�X���ɓn������]
                //��s�R�[�h�F�莞��z���t���������D��s�R�[�h
                //�x�X�R�[�h�F�莞��z���t���������D�x�X�R�[�h
                l_strFinBranchNames = l_mutualFixedBuyCommonService.getFinBranchName(
                    l_mfBuyDrawAccount.getFinInstitutionCode(),
                    l_mfBuyDrawAccount.getFinBranchCode());
            }
        }

        //���M�莞��z�����������
        WEB3MutualFixedBuyAccountInfo l_mfBuyAccountInfo = new WEB3MutualFixedBuyAccountInfo();
        //�v���p�e�B�Z�b�g
        //�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
        //�E��s�R�[�h=�莞��z���t���������D��s�R�[�h
        l_mfBuyAccountInfo.financialInstitutionCode = l_mfBuyDrawAccount.getFinInstitutionCode();
        //�E�x�X�R�[�h=�莞��z���t���������D�x�X�R�[�h
        l_mfBuyAccountInfo.financialBranchCode = l_mfBuyDrawAccount.getFinBranchCode();
        //�E���Z�@@�֋敪=�莞��z���t���������D���Z�@@�֋敪
        l_mfBuyAccountInfo.financialInstitutionDiv = l_mfBuyDrawAccount.getFinInstitutionDiv();
        //�E���������ԍ�=�莞��z���t���������D���������ԍ�
        l_mfBuyAccountInfo.financialAccountCode = l_mfBuyDrawAccount.getDrawAccountNo();
        //�E�����������`�l�i�J�i�j=�莞��z���t���������D�����������`�l�i�J�i�j
        l_mfBuyAccountInfo.financialAccountName = l_mfBuyDrawAccount.getDrawAccountNameKana();
        //���Z�@@�֋敪����s�̏ꍇ
        if (WEB3FinInstitutionDivDef.BANK.equals(l_mfBuyDrawAccount.getFinInstitutionDiv()))
        {
            //�E��s��=get��s�x�X���̖߂�lString[0]
            l_mfBuyAccountInfo.financialInstitutionName = l_strFinBranchNames[0];
            //�E�x�X��=get��s�x�X���̖߂�lString[1]
            l_mfBuyAccountInfo.financialBranchName = l_strFinBranchNames[1];
            //�E�a���敪=�莞��z���t���������D�a���敪
            l_mfBuyAccountInfo.financialAccountDiv = l_mfBuyDrawAccount.getDepositDiv();
        }
        //���Z�@@�֋敪���X�֋ǂ̏ꍇ
        else if (WEB3FinInstitutionDivDef.POST_OFFICE.equals(l_mfBuyDrawAccount.getFinInstitutionDiv()))
        {
            //�E��s��=null
            l_mfBuyAccountInfo.financialInstitutionName = null;
            //�E�x�X��=null
            l_mfBuyAccountInfo.financialBranchName = null;
            //�E�a���敪=null
            l_mfBuyAccountInfo.financialAccountDiv = null;
        }

        //create���X�|���X
        //�icreateResponse�̎����j
        //�莞��z���t���������o�^���̓��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B
        WEB3MutualFixedBuyConditionInputResponse l_response =
            (WEB3MutualFixedBuyConditionInputResponse)l_request.createResponse();

        //���X�|���X�Z�b�g
        //���M�莞��z���t�ϗ��o�^���e  = sort�莞��z���t�����ꗗ�̖߂�l
        //���M�莞��z���t�V�K�ǉ����e�@@=�@@�V�K�ǉ����M�莞��z���t�����s
        //���M�莞��z���������@@=�@@�擾�������M�莞��z�����������
        //���M�莞��z���t���z���v�@@= get���M�莞��z���t���z���v�̖߂�l
        //���M�����J�e�S���[�ꗗ = create���M�����J�e�S���[�ꗗ�̖߂�l
        if (l_mutualfixedBuyConditionUnitSorts != null && l_mutualfixedBuyConditionUnitSorts.length != 0)
        {
            l_response.conditionList = l_mutualfixedBuyConditionUnitSorts;
        }

        l_response.addConditionList = l_mfBuyConditionUnitNews;
        l_response.acountInfo = l_mfBuyAccountInfo;

        if (l_mutualFixedBuyTotalUnits != null && l_mutualFixedBuyTotalUnits.length != 0)
        {
            l_response.totalList = l_mutualFixedBuyTotalUnits;
        }
        l_response.categoryList = l_mutualProductCategoryUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�莞��z���t���������o�^)<BR>
     * �����M���莞��z���t���������o�^�m�F���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���M�j�莞��z���t���������o�^�m�F�v�Q�ƁB<BR>
     * <BR>
     * =========================================================<BR>
     * ��̈ʒu�Fvalidate�O���،������J��<BR>
     * �@@�@@�@@�@@�@@�@@(�⏕���� : SubAccount, �g�����M���� : �g�����M����)<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_01341<BR>
     * =========================================================<BR>
     *  <BR>
     * =========================================================<BR>
     * ��̈ʒu�F�����򏈗���is�莞��z���t�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02480<BR>
     * =========================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MutualFixedBuyConditionConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4851C53F0149
     */
    protected WEB3MutualFixedBuyConditionConfirmResponse validateMutualFixedBuyCondition(
        WEB3MutualFixedBuyConditionConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMutualFixedBuyCondition(WEB3MutualFixedBuyConditionConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        //���N�G�X�g�`�F�b�N
        l_request.validate();

        //validate������t�\
        //�ً}��~����Ă��Ȃ����A���邢�̓o�b�`�������łȂ����`�F�b�N����
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //get�⏕����
        //���O�C���Z�L�����e�B�T�[�r�X���⏕�ڋq���擾����
        SubAccount l_subAccount = this.getSubAccount();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //getCommonOrderValidator
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //get������
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //validate����\�ڋq
        //���Y�ڋq������\�Ȍڋq���ǂ����𔻒肷��B
        //[validate����\�ڋq�ɓn������]
        //�@@�ڋq�F�擾�����⏕����.getMainAccount()
        //�@@�������F������ԊǗ�.get������()�̖߂�l
        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        OrderValidationResult l_validateAccountForTrading = l_orderValidator.validateAccountForTrading(
            l_genMainAccount,
            new Timestamp(l_datOrderBizDate.getTime()));

        if (!l_validateAccountForTrading.getProcessingResult().isSuccessfulResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validateAccountForTrading.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //validate���������o�^
        //�����������o�^�ς��ǂ����`�F�b�N���s���B
        //�mvalidate���������o�^�̈����n
        //�،���ЃR�[�h�F�擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�F�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        //�����R�[�h�F�擾�����⏕����.getMainAccount().getAccountCode()�̖߂�l
        String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strMainAccountCode = l_subAccount.getMainAccount().getAccountCode();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);

        l_mutualFixedBuyCommonService.validateDrawAccountRegist(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode,
            l_strMainAccountCode);

        //calc�K�p�J�n�N���i�Ɩ����t�j
        //�K�p�J�n�N���i�Ɩ����t�j���擾����B
        //[calc�K�p�J�n�N���i�Ɩ����t�j�̈���]
        //�،���ЃR�[�h�F�擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�F�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        Date l_datOrderBiz = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode);

        //calc�K�p�J�n�N���i���ݓ����j
        //�K�p�J�n�N���i���ݓ����j���擾����B
        //[calc�K�p�J�n�N���i���ݓ����j�̈���]
        //�،���ЃR�[�h�F�擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�F�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        Date l_datCurrent = l_mutualFixedBuyCommonService.calcValidStartDateCurrentDate(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode);

        //�莞��z���t���ؓ��������v�Z
        //�m�莞��z���t���ؓ��������v�Z�̈����n
        //�،���ЃR�[�h�F�擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�F�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        WEB3MutualFixedBuyCloseDateDrawDateCalc l_mutualFixedBuyCloseDateDrawDateCalc =
            new WEB3MutualFixedBuyCloseDateDrawDateCalc(l_strSubAccountInstitutionCode, l_strMainAccountBranchCode);

        //calc�ܗ^���ؓ���
        //�ܗ^���ؓ����擾����B
        //[calc�ܗ^���ؓ��̈���]
        //�w��N���Fcalc�K�p�J�n�N���i���ݓ����j�̖߂�l
        Date l_datCalcPrizeAndCloseDateHour =
            l_mutualFixedBuyCloseDateDrawDateCalc.calcPrizeAndCloseDateHour(l_datCurrent);

        //calc�ʏ���ؓ����iWEB�j
        //�ʏ���ؓ���(WEB)���擾����
        //[calc�ʏ���ؓ����iWEB�j�̈���]
        //�w��N���Fcalc�K�p�J�n�N���i���ݓ����j�̖߂�l
        Date l_datCalcUsuallyCloseDateTime =
            l_mutualFixedBuyCloseDateDrawDateCalc.calcUsuallyCloseDateTime(l_datCurrent);

        //���t���z�ςݑ����ύX�t���O == false
        //���t���z�ςݑ����ύX�t���O��false���Z�b�g����B
        //�ȉ���LOOP�����ŁA���N�G�X�g�D���t���z�i�ςݑ����j!=null�̏ꍇ�Atrue���Z�b�g����
        int l_intConditionLisCnt = 0;
        if (l_request.conditionList != null)
        {
            l_intConditionLisCnt = l_request.conditionList.length;
        }
        boolean l_blnIncreaseBuyAmountFlag = false;


        //<LOOP����> ���N�G�X�g�f�[�^�D���M�莞��z���t�ϗ��o�^���e�̌�����LOOP
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        MutualFundProduct l_mutualFundProduct = null;
        Institution l_institution = l_subAccount.getInstitution();
        for (int i = 0; i < l_intConditionLisCnt; i++)
        {
            //get���M����
            //�g�����M�������擾����B
            //[get���M�����ɓn���p�����^]
            //�،���ЁF�@@�擾�����⏕����.getInstitution()�̖߂�l
            //�����R�[�h�F ���N�G�X�g�f�[�^.���M�莞��z���t�ϗ��o�^���e.�����R�[�h
            try
            {
                l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_request.conditionList[i].mutualProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_mutualFundProduct;
            //isFWF
            //FWF��������s��
            boolean l_blnIsFWF = l_mfProduct.isFWF();

            //is�O�����M
            //�O�����M��������s��
            boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();

            //isFWF()�̖߂�l == true or is�O�����M()�̖߂�l == true
            if (l_blnIsFWF || l_blnIsForeignFund)
            {
                //�O���،������J�ݍς�������s��
                boolean l_blnIsForeignAccountOpen = l_genMainAccount.isForeignAccountOpen();

                //�߂�l��false�̏ꍇ�A�u�O���،��������J�݃G���[�v�Ƃ��ė�O���X���[����
                if (!l_blnIsForeignAccountOpen)
                {
                    log.debug("���Y�ڋq�͊O���،������J�݂Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���Y�ڋq�͊O���،������J�݂Ȃ��B");
                }
            }

            //validate�莞��z���t���z
            //�莞��z���t���z�̃`�F�b�N���s�Ȃ��B
            //[validate�莞��z���t���z�ɓn���p�����^]
            //�g�����M�����F�擾�����⏕����
            //���t���z�i���X�j�F���N�G�X�g�f�[�^.���M�莞��z���t�ϗ��o�^���e.���t���z�i���X�j
            //���t���z�i�ςݑ����j�F���N�G�X�g�f�[�^.���M�莞��z���t�ϗ��o�^���e.���t���z�i�ςݑ����j
            l_mutualFixedBuyCommonService.validateFixedBuyAmount(
                l_subAccount,
                l_request.conditionList[i].monthlyBuyAmount,
                l_request.conditionList[i].increaseBuyAmount);

            //is�莞��z���t�\
            //�莞��z���t�\�������ǂ����̃`�F�b�N
            boolean l_blnFixedBuyPos = l_mfProduct.isFixedBuyPossible();

            //�����򏈗���is�莞��z���t�\()�̖߂�l == false �̏ꍇ�A��O���X���[����
            if (!l_blnFixedBuyPos)
            {
                //�莞��z���t�s�����G���[�Ƃ��ė�O���X���[����B
                //����O������̒ǉ�
                //�@@�iWEB3Exception.errorMessage)
                //�Ɉȉ��̓��e���Z�b�g����B
                //���N�G�X�g.�莞��z���t���ʏ��.�����R�[�h
                log.debug("�莞��z���t�s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02480,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_request.conditionList[i].mutualProductCode);
            }

            //���N�G�X�g�D���M�莞��z���t�ϗ��o�^���e�D���t���z�i�ςݑ����j != null�̏ꍇ�A
            //���t���z�ςݑ����ύX�t���O��true���Z�b�g����B
            if (l_request.conditionList[i].increaseBuyAmount != null)
            {
                l_blnIncreaseBuyAmountFlag = true;
            }
        }

        //<LOOP����> ���N�G�X�g�f�[�^�D���M�莞��z���t�V�K�ǉ����e�̌�����LOOP
        int l_intAddConditionListCnt = 0;
        if (l_request.addConditionList != null)
        {
            l_intAddConditionListCnt = l_request.addConditionList.length;
        }
        //���M�莞��z���t�����s
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            new WEB3MutualFixedBuyConditionUnit[l_intAddConditionListCnt];
        for (int i = 0; i < l_intAddConditionListCnt; i++)
        {
            //get���M����
            //�g�����M�������擾����B
            //[get���M�����ɓn���p�����^]
            //�،���ЁF�擾�����⏕����.getInstitution()�̖߂�l
            //�����R�[�h�F���N�G�X�g�f�[�^.���M�莞��z���t�V�K�ǉ����e.�����R�[�h
            try
            {
                l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_request.addConditionList[i].mutualProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_mutualFundProduct;
            //isFWF
            //FWF��������s��
            boolean l_blnIsFWF = l_mfProduct.isFWF();

            //is�O�����M
            //�O�����M��������s��
            boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();

            //isFWF()�̖߂�l == true or is�O�����M()�̖߂�l == true
            if (l_blnIsFWF || l_blnIsForeignFund)
            {
                //�O���،������J�ݍς�������s��
                boolean l_blnIsForeignAccountOpen = l_genMainAccount.isForeignAccountOpen();

                //�߂�l��false�̏ꍇ�A�u�O���،��������J�݃G���[�v�Ƃ��ė�O���X���[����
                if (!l_blnIsForeignAccountOpen)
                {
                    log.debug("���Y�ڋq�͊O���،������J�݂Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���Y�ڋq�͊O���،������J�݂Ȃ��B");
                }
            }

            //validate�莞��z���t���z
            //�莞��z���t���z�̃`�F�b�N���s�Ȃ��B
            //[validate�莞��z���t���z�ɓn���p�����^]
            //�g�����M�����F�擾�����⏕����
            //���t���z�i���X�j�F���N�G�X�g�f�[�^.���M�莞��z���t�V�K�ǉ����e.���t���z�i���X�j
            //���t���z�i�ςݑ����j�F���N�G�X�g�f�[�^.���M�莞��z���t�V�K�ǉ����e.���t���z�i�ςݑ����j
            l_mutualFixedBuyCommonService.validateFixedBuyAmount(
                l_subAccount,
                l_request.addConditionList[i].monthlyBuyAmount,
                l_request.addConditionList[i].increaseBuyAmount);

            //is�莞��z���t�\
            //�莞��z���t�\�������ǂ����̃`�F�b�N
            boolean l_blnFixedBuyPos = l_mfProduct.isFixedBuyPossible();

            //�����򏈗���is�莞��z���t�\()�̖߂�l == false �̏ꍇ�A��O���X���[����
            if (!l_blnFixedBuyPos)
            {
                //�莞��z���t�s�����G���[�Ƃ��ė�O���X���[����B
                //����O������̒ǉ�
                //�@@�iWEB3Exception.errorMessage)
                //�Ɉȉ��̓��e���Z�b�g����B
                //���N�G�X�g.�莞��z���t���ʏ��.�����R�[�h
                log.debug("�莞��z���t�s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02480,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_request.addConditionList[i].mutualProductCode);
            }

            //���M�莞��z���t�����s
            l_mutualFixedBuyConditionUnits[i] =
                new WEB3MutualFixedBuyConditionUnit();
            MutualFundProductRow l_mutualFundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            //���L�̒ʂ�A�v���p�e�B���Z�b�g����B
            //�����R�[�h�F���N�G�X�g�D�莞��z���t�V�K�ǉ����e�D�����R�[�h
            l_mutualFixedBuyConditionUnits[i].mutualProductCode = l_request.addConditionList[i].mutualProductCode;
            //���t���z�i���X�j�F���N�G�X�g�D�莞��z���t�V�K�ǉ����e�D���t���z�i���X�j
            l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount = l_request.addConditionList[i].monthlyBuyAmount;
            //���t���z�i�ςݑ����j�F���N�G�X�g�D�莞��z���t�V�K�ǉ����e�D���t���z�i�ςݑ����j
            l_mutualFixedBuyConditionUnits[i].increaseBuyAmount = l_request.addConditionList[i].increaseBuyAmount;
            //�����\�������F�擾�����g�����M����.get�\������
            if (l_mutualFundProductRow.getIndicationRankingIsNull())
            {
                l_mutualFixedBuyConditionUnits[i].displayOrder = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnits[i].displayOrder =
                    l_mutualFundProductRow.getIndicationRanking() + "";
            }
            //�K�p�J�n�N���Fcalc�K�p�J�n�N���i���ݓ����j�̖߂�l
            l_mutualFixedBuyConditionUnits[i].validStartDate = l_datCurrent;
            //�X�V�����Fnull
            l_mutualFixedBuyConditionUnits[i].updateDate = null;
            //���������N���Fnull
            l_mutualFixedBuyConditionUnits[i].debitAccountYM = null;
            //�m��������z�i�ςݑ����j�Fnull
            l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount = null;
            //�ژ_�����{���`�F�b�N�Fnull
            l_mutualFixedBuyConditionUnits[i].checkResult = null;
            //�ꎞ��~���t���O�Ffalse
            l_mutualFixedBuyConditionUnits[i].suspensionFlag = false;
        }

        //���ؓ��A���[�g�v���t���O�̔��f
        //�ȉ��̏ꍇ�A���ؓ��A���[�g�v���t���O��true���Z�b�g����B
        //is�ܗ^�� = true ����
        //calc�ܗ^���ؓ��� < ���ݓ��� <= calc�ʏ���ؓ���(WEB)�@@����
        //���t���z�ςݑ����ύX�t���O == true
        //[is�ܗ^���̈���]
        //�،���ЃR�[�h�F�擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
        //�w��N���Fcalc�K�p�J�n�N���i���ݓ����j�̖߂�l
        boolean l_blnClosingAlertRequestFlags = false;
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        if (l_mutualFixedBuyCommonService.isPrizeAndMonth(l_strSubAccountInstitutionCode, l_datCurrent)
            && WEB3DateUtility.compareToSecond(l_datCalcPrizeAndCloseDateHour, l_tsSystemTimestamp) < 0
            && WEB3DateUtility.compareToSecond(l_tsSystemTimestamp, l_datCalcUsuallyCloseDateTime) <= 0
            && l_blnIncreaseBuyAmountFlag)
        {
            l_blnClosingAlertRequestFlags = true;
        }

        //merge�莞��z���t�����ύX
        //��ʕ\������A�ϗ��o�^�f�[�^�ƐV�K�ǉ��f�[�^���擾����B
        //�mmerge�莞��z���t�����ύX�̈���
        //�⏕�����F�擾�����⏕�����I�u�W�F�N�g
        //�K�p�J�n�N���Fcalc�K�p�J�n�N���i�Ɩ����t�j�̖߂�l
        WEB3MutualFixedBuyConditionUnit[] l_mfBuyConditionUnits =
            this.mergeMutualFixedBuyConditionChange(l_subAccount, l_datOrderBiz);

        WEB3MutualFixedBuyConditionUnit[] l_sortFixedBuyConditionList = null;
        //<���򏈗�> merge�莞��z���t�����ύX�̌�����0���ȊO�̏ꍇ
        if (l_mfBuyConditionUnits != null && l_mfBuyConditionUnits.length != 0)
        {
            //sort�莞��z���t�����ꗗ
            //�\�[�g����
            //[sort���M�莞��z���t����()�ɓn������]
            // merge�莞��z���t�����ύX�̖߂�l
            l_sortFixedBuyConditionList =
                l_mutualFixedBuyCommonService.sortFixedBuyConditionList(l_mfBuyConditionUnits);
        }

        //create���X�|���X
        //�莞��z���t���������o�^�m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ�
        WEB3MutualFixedBuyConditionConfirmResponse l_response =
            (WEB3MutualFixedBuyConditionConfirmResponse)l_request.createResponse();

        //���X�|���X�Z�b�g
        //���M�莞��z���t�ϗ��o�^���e  = sort�莞��z���t�����ꗗ�̖߂�l
        //���M�莞��z���t�V�K�ǉ����e�@@=�@@�V�K�ǉ����M�莞��z���t�����s
        //���M�莞��z���������@@=�@@null
        //���M�莞��z���t���z���v�@@= null
        //���M�����J�e�S���[�ꗗ  =�@@null
        //���ؓ��A���[�g�v���t���O = ���ؓ��A���[�g�v���t���O�̔��f�ŃZ�b�g�����l
        l_response.conditionList = l_sortFixedBuyConditionList;
        l_response.addConditionList = l_mutualFixedBuyConditionUnits;
        l_response.acountInfo = null;
        l_response.totalList = null;
        l_response.categoryList = null;
        l_response.closingDateAlertFlag = l_blnClosingAlertRequestFlags;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�莞��z���t���������o�^)<BR>
     * �����M���莞��z���t���������o�^�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���M�j�莞��z���t���������o�^�����v�Q�ƁB<BR>
     * <BR>
     * =========================================================<BR>
     * ��̈ʒu�Fvalidate�O���،������J��<BR>
     * �@@�@@�@@�@@�@@�@@(�⏕���� : SubAccount, �g�����M���� : �g�����M����)<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_01341<BR>
     * =========================================================<BR>
     * <BR>
     * =========================================================<BR>
     * ��̈ʒu�F�����򏈗���is�莞��z���t�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02480<BR>
     * =========================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MutualFixedBuyConditionCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4851C5E802A5
     */
    protected WEB3MutualFixedBuyConditionCompleteResponse submitMutualFixedBuyCondition(
        WEB3MutualFixedBuyConditionCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitMutualFixedBuyCondition(WEB3MutualFixedBuyConditionCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        //���N�G�X�g�`�F�b�N
        l_request.validate();

        //validate������t�\
        //�ً}��~����Ă��Ȃ����A���邢�̓o�b�`�������łȂ����`�F�b�N����
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //get�⏕����
        //���O�C���Z�L�����e�B�T�[�r�X���⏕�ڋq���擾����
        SubAccount l_subAccount = this.getSubAccount();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //getCommonOrderValidator
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //get������
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //validate����\�ڋq
        //���Y�ڋq������\�Ȍڋq���ǂ����𔻒肷��B
        //[validate����\�ڋq�ɓn������]
        //�@@�ڋq�F�擾�����⏕����.getMainAccount()
        //�@@�������F������ԊǗ�.get������()�̖߂�l
        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        OrderValidationResult l_validateAccountForTrading = l_orderValidator.validateAccountForTrading(
            l_genMainAccount,
            new Timestamp(l_datOrderBizDate.getTime()));

        if (!l_validateAccountForTrading.getProcessingResult().isSuccessfulResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validateAccountForTrading.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //get�㗝���͎�
        //�㗝���͎҃I�u�W�F�N�g���擾����B
        //�i���肷�܂��ł͂Ȃ��ꍇ�Anull ���ԋp�����j
        Trader l_trader = this.getTrader();

        //validate����p�X���[�h
        //validate����p�X���[�h( )���R�[������
        //[validate����p�X���[�h�ɓn���p�����^]
        //�㗝���͎ҁF�擾�����㗝���͎�
        //�⏕�����F�擾�����⏕�����I�u�W�F�N�g
        //�p�X���[�h�F���N�G�X�g�f�[�^.�Ïؔԍ�
        OrderValidationResult l_validateTradingPassword = l_orderValidator.validateTradingPassword(
            l_trader,
            l_subAccount,
            l_request.password);

        if (!l_validateTradingPassword.getProcessingResult().isSuccessfulResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validateTradingPassword.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //validate���������o�^
        //�����������o�^�ς��ǂ����`�F�b�N���s���B
        //�mvalidate���������o�^�̈����n
        //�،���ЃR�[�h�F�擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�F�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        //�����R�[�h�F�擾�����⏕����.getMainAccount().getAccountCode()�̖߂�l
        String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strMainAccountCode = l_subAccount.getMainAccount().getAccountCode();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);

        l_mutualFixedBuyCommonService.validateDrawAccountRegist(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode,
            l_strMainAccountCode);

        //calc�K�p�J�n�N���i�Ɩ����t�j
        //�K�p�J�n�N���i�Ɩ����t�j���擾����B
        //[calc�K�p�J�n�N���i�Ɩ����t�j�̈���]
        //�،���ЃR�[�h�F�擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�F�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        Date l_datOrderBiz = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode);

        //calc�K�p�J�n�N���i���ݓ����j
        //�K�p�J�n�N���i���ݓ����j���擾����B
        //[calc�K�p�J�n�N���i���ݓ����j�̈���]
        //�،���ЃR�[�h�F�擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�F�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        Date l_datCurrent = l_mutualFixedBuyCommonService.calcValidStartDateCurrentDate(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode);

        //�莞��z���t���ؓ��������v�Z
        //�m�莞��z���t���ؓ��������v�Z�̈����n
        //�،���ЃR�[�h�F�擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�F�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        WEB3MutualFixedBuyCloseDateDrawDateCalc l_mutualFixedBuyCloseDateDrawDateCalc =
            new WEB3MutualFixedBuyCloseDateDrawDateCalc(l_strSubAccountInstitutionCode, l_strMainAccountBranchCode);

        //calc�ܗ^���ؓ���
        //�ܗ^���ؓ����擾����B
        //[calc�ܗ^���ؓ��̈���]
        //�w��N���Fcalc�K�p�J�n�N���i���ݓ����j�̖߂�l
        Date l_datCalcPrizeAndCloseDateHour =
            l_mutualFixedBuyCloseDateDrawDateCalc.calcPrizeAndCloseDateHour(l_datCurrent);

        //calc�ʏ���ؓ����iWEB�j
        //�ʏ���ؓ���(WEB)���擾����
        //[calc�ʏ���ؓ����iWEB�j�̈���]
        //�w��N���Fcalc�K�p�J�n�N���i���ݓ����j�̖߂�l
        Date l_datCalcUsuallyCloseDateTime =
            l_mutualFixedBuyCloseDateDrawDateCalc.calcUsuallyCloseDateTime(l_datCurrent);

        //���t���z�ςݑ����ύX�t���O == false
        //���t���z�ςݑ����ύX�t���O��false���Z�b�g����B
        //�ȉ���LOOP�����ŁA���N�G�X�g�D���t���z�i�ςݑ����j!=null�̏ꍇ�Atrue���Z�b�g����
        int l_intConditionLisCnt = 0;
        if (l_request.conditionList != null)
        {
            l_intConditionLisCnt = l_request.conditionList.length;
        }
        boolean l_blnIncreaseBuyAmountFlag = false;

        //<LOOP����> ���N�G�X�g�f�[�^�D���M�莞��z���t�ϗ��o�^���e�̌�����LOOP
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        MutualFundProduct l_mutualFundProduct = null;
        Institution l_institution = l_subAccount.getInstitution();
        for (int i = 0; i < l_intConditionLisCnt; i++)
        {
            //get���M����
            //�g�����M�������擾����B
            //[get���M�����ɓn���p�����^]
            //�،���ЁF�@@�擾�����⏕����.getInstitution()�̖߂�l
            //�����R�[�h�F ���N�G�X�g�f�[�^.���M�莞��z���t�ϗ��o�^���e.�����R�[�h
            try
            {
                l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_request.conditionList[i].mutualProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_mutualFundProduct;
            //isFWF
            //FWF��������s��
            boolean l_blnIsFWF = l_mfProduct.isFWF();

            //is�O�����M
            //�O�����M��������s��
            boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();

            //isFWF()�̖߂�l == true or is�O�����M()�̖߂�l == true
            if (l_blnIsFWF || l_blnIsForeignFund)
            {
                //�O���،������J�ݍς�������s��
                boolean l_blnIsForeignAccountOpen = l_genMainAccount.isForeignAccountOpen();

                //�߂�l��false�̏ꍇ�A�u�O���،��������J�݃G���[�v�Ƃ��ė�O���X���[����
                if (!l_blnIsForeignAccountOpen)
                {
                    log.debug("���Y�ڋq�͊O���،������J�݂Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���Y�ڋq�͊O���،������J�݂Ȃ��B");
                }
            }

            //validate�莞��z���t���z
            //�莞��z���t���z�̃`�F�b�N���s�Ȃ��B
            //[validate�莞��z���t���z�ɓn���p�����^]
            //�g�����M�����F�擾�����⏕����
            //���t���z�i���X�j�F���N�G�X�g�f�[�^.�莞��z���t�ϗ��o�^���e.���t���z�i���X�j
            //���t���z�i�ςݑ����j�F���N�G�X�g�f�[�^.�莞��z���t�ϗ��o�^���e.���t���z�i�ςݑ����j
            l_mutualFixedBuyCommonService.validateFixedBuyAmount(
                l_subAccount,
                l_request.conditionList[i].monthlyBuyAmount,
                l_request.conditionList[i].increaseBuyAmount);

            //is�莞��z���t�\
            //�莞��z���t�\�������ǂ����̃`�F�b�N
            boolean l_blnFixedBuyPos = l_mfProduct.isFixedBuyPossible();

            //�����򏈗���is�莞��z���t�\()�̖߂�l == false �̏ꍇ�A��O���X���[����
            if (!l_blnFixedBuyPos)
            {
                //�莞��z���t�s�����G���[�Ƃ��ė�O���X���[����B
                //����O������̒ǉ�
                //�@@�iWEB3Exception.errorMessage)
                //�Ɉȉ��̓��e���Z�b�g����B
                //���N�G�X�g.�莞��z���t���ʏ��.�����R�[�h
                log.debug("�莞��z���t�s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02480,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_request.conditionList[i].mutualProductCode);
            }

            //���N�G�X�g�D�莞��z���t�ϗ��o�^���e�D���t���z�i�ςݑ����j != null�̏ꍇ�A
            //���t���z�ςݑ����ύX�t���O��true���Z�b�g����
            if (l_request.conditionList[i].increaseBuyAmount != null)
            {
                l_blnIncreaseBuyAmountFlag = true;
            }
        }

        //<LOOP����> ���N�G�X�g�f�[�^�D���M�莞��z���t�V�K�ǉ����e�̌�����LOOP
        int l_intAddConditionListCnt = 0;
        if (l_request.addConditionList != null)
        {
            l_intAddConditionListCnt = l_request.addConditionList.length;
        }
        //���M�莞��z���t�����s
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            new WEB3MutualFixedBuyConditionUnit[l_intAddConditionListCnt];
        for (int i = 0; i < l_intAddConditionListCnt; i++)
        {
            //get���M����
            //�g�����M�������擾����B
            //[get���M�����ɓn���p�����^]
            //�،���ЁF�擾�����⏕����.getInstitution()�̖߂�l
            //�����R�[�h�F���N�G�X�g�f�[�^.���M�莞��z���t�V�K�ǉ����e.�����R�[�h
            try
            {
                l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_request.addConditionList[i].mutualProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_mutualFundProduct;
            //isFWF
            //FWF��������s��
            boolean l_blnIsFWF = l_mfProduct.isFWF();

            //is�O�����M
            //�O�����M��������s��
            boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();

            //isFWF()�̖߂�l == true or is�O�����M()�̖߂�l == true
            if (l_blnIsFWF || l_blnIsForeignFund)
            {
                //�O���،������J�ݍς�������s��
                boolean l_blnIsForeignAccountOpen = l_genMainAccount.isForeignAccountOpen();

                //�߂�l��false�̏ꍇ�A�u�O���،��������J�݃G���[�v�Ƃ��ė�O���X���[����
                if (!l_blnIsForeignAccountOpen)
                {
                    log.debug("���Y�ڋq�͊O���،������J�݂Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���Y�ڋq�͊O���،������J�݂Ȃ��B");
                }
            }

            //validate�莞��z���t���z
            //�莞��z���t���z�̃`�F�b�N���s�Ȃ��B
            //[validate�莞��z���t���z�ɓn���p�����^]
            //�g�����M�����F�擾�����⏕����
            //���t���z�i���X�j�F���N�G�X�g�f�[�^.���M�莞��z���t�V�K�ǉ����e.���t���z�i���X�j�j
            //���t���z�i�ςݑ����j�F���N�G�X�g�f�[�^.���M�莞��z���t�V�K�ǉ����e.���t���z�i�ςݑ����j
            l_mutualFixedBuyCommonService.validateFixedBuyAmount(
                l_subAccount,
                l_request.addConditionList[i].monthlyBuyAmount,
                l_request.addConditionList[i].increaseBuyAmount);

            //is�莞��z���t�\
            //�莞��z���t�\�������ǂ����̃`�F�b�N
            boolean l_blnFixedBuyPos = l_mfProduct.isFixedBuyPossible();

            //�����򏈗���is�莞��z���t�\()�̖߂�l == false �̏ꍇ�A��O���X���[����
            if (!l_blnFixedBuyPos)
            {
                //�莞��z���t�s�����G���[�Ƃ��ė�O���X���[����B
                //����O������̒ǉ�
                //�@@�iWEB3Exception.errorMessage)
                //�Ɉȉ��̓��e���Z�b�g����B
                //���N�G�X�g.�莞��z���t���ʏ��.�����R�[�h
                log.debug("�莞��z���t�s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02480,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_request.addConditionList[i].mutualProductCode);
            }

            //���M�莞��z���t�����s
            l_mutualFixedBuyConditionUnits[i] =
                new WEB3MutualFixedBuyConditionUnit();
            MutualFundProductRow l_mutualFundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            //���L�̒ʂ�A�v���p�e�B���Z�b�g����B
            //�����R�[�h�F���N�G�X�g�D�莞��z���t�V�K�ǉ����e�D�����R�[�h
            l_mutualFixedBuyConditionUnits[i].mutualProductCode = l_request.addConditionList[i].mutualProductCode;
            //���t���z�i���X�j�F���N�G�X�g�D�莞��z���t�V�K�ǉ����e�D���t���z�i���X�j
            l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount = l_request.addConditionList[i].monthlyBuyAmount;
            //���t���z�i�ςݑ����j�F���N�G�X�g�D�莞��z���t�V�K�ǉ����e�D���t���z�i�ςݑ����j
            l_mutualFixedBuyConditionUnits[i].increaseBuyAmount = l_request.addConditionList[i].increaseBuyAmount;
            //�����\�������F�擾�����g�����M����.get�\������
            if (l_mutualFundProductRow.getIndicationRankingIsNull())
            {
                l_mutualFixedBuyConditionUnits[i].displayOrder = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnits[i].displayOrder =
                    l_mutualFundProductRow.getIndicationRanking() + "";
            }
            //�K�p�J�n�N���Fcalc�K�p�J�n�N���i���ݓ����j�̖߂�l
            l_mutualFixedBuyConditionUnits[i].validStartDate = l_datCurrent;
            //�X�V�����Fnull
            l_mutualFixedBuyConditionUnits[i].updateDate = null;
            //���������N���Fnull
            l_mutualFixedBuyConditionUnits[i].debitAccountYM = null;
            //�m��������z�i�ςݑ����j�Fnull
            l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount = null;
            //�ژ_�����{���`�F�b�N�Fnull
            l_mutualFixedBuyConditionUnits[i].checkResult = null;
            //�ꎞ��~���t���O�Ffalse
            l_mutualFixedBuyConditionUnits[i].suspensionFlag = false;
        }

        //���ؓ��A���[�g�v���t���O�̔��f
        //�ȉ��̏ꍇ�A���ؓ��A���[�g�v���t���O��true���Z�b�g����B
        //is�ܗ^�� = true ����
        //calc�ܗ^���ؓ��� < ���ݓ��� <= calc�ʏ���ؓ���(WEB)�@@����
        //���t���z�ςݑ����ύX�t���O == true
        //[is�ܗ^���̈���]
        //�،���ЃR�[�h�F�擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l
        //�w��N���Fcalc�K�p�J�n�N���i���ݓ����j�̖߂�l
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        boolean l_blnClosingAlertRequestFlags = false;
        if (l_mutualFixedBuyCommonService.isPrizeAndMonth(
            l_strSubAccountInstitutionCode, l_datCurrent)
            && WEB3DateUtility.compareToSecond(l_datCalcPrizeAndCloseDateHour, l_tsSystemTimestamp) < 0
            && WEB3DateUtility.compareToSecond(l_tsSystemTimestamp, l_datCalcUsuallyCloseDateTime) <= 0
            && l_blnIncreaseBuyAmountFlag)
        {
            l_blnClosingAlertRequestFlags = true;
        }

        //merge�莞��z���t�����ύX
        //��ʕ\������A�ϗ��o�^�f�[�^�ƐV�K�ǉ��f�[�^���擾����B
        //�mmerge�莞��z���t�����ύX�̈����n
        //�⏕�����F�擾�����⏕�����I�u�W�F�N�g
        //�K�p�J�n�N���Fcalc�K�p�J�n�N���i�Ɩ����t�j�̖߂�l
        WEB3MutualFixedBuyConditionUnit[] l_mfBuyConditionUnits =
            this.mergeMutualFixedBuyConditionChange(l_subAccount, l_datOrderBiz);

        WEB3MutualFixedBuyConditionUnit[] l_sortFixedBuyConditionList = null;
        //<���򏈗�> merge�莞��z���t�����ύX�̌�����0���ȊO�̏ꍇ
        if (l_mfBuyConditionUnits != null && l_mfBuyConditionUnits.length != 0)
        {
            //sort�莞��z���t�����ꗗ
            //�\�[�g����
            //[sort���M�莞��z���t����()�ɓn������]
            // merge�莞��z���t�����ύX�̖߂�l
            l_sortFixedBuyConditionList =
                l_mutualFixedBuyCommonService.sortFixedBuyConditionList(l_mfBuyConditionUnits);
        }

        //<LOOP����> ���M�莞��z���t�ϗ��o�^���e�̌�����LOOP
        for (int i = 0; i < l_intConditionLisCnt; i++)
        {
            //�X�V�O�̒莞��z���t�����s�̎擾
            //merge�莞��z���t�����ύX�̖߂�l����Y�������̒莞��z���t�����s���擾����B
            //1) merge�莞��z���t�����ύX�̖߂�l�̌������A���[�v
            //  1)-1) merge�莞��z���t�����ύX�̖߂�l.�����R�[�h == ���M�莞��z���t�ϗ��o�^���e.�����R�[�h����v�����ꍇ
            //    1)-1)-2) merge�莞��z���t�����ύX�̖߂�l[index]���擾
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit = null;
            int l_intMfBuyConditionUnits = 0;
            if (l_mfBuyConditionUnits != null)
            {
                l_intMfBuyConditionUnits = l_mfBuyConditionUnits.length;
            }
            for (int j = 0; j < l_intMfBuyConditionUnits; j++)
            {

                if (WEB3Toolkit.isEquals(
                    l_request.conditionList[i].mutualProductCode,
                    l_mfBuyConditionUnits[j].mutualProductCode))
                {
                    l_mutualFixedBuyConditionUnit = l_mfBuyConditionUnits[j];
                }
            }    
            //update�莞��z���t�����ύX
            //[update�莞��z���t�����ύX�̈���]
            //�⏕�����Fget�⏕�����̖߂�l
            //���ҁFget�㗝���͎҂̖߂�l
            //�莞��z���t���ʏ��F���M�莞��z���t�ϗ��o�^���e[index]
            //���M�莞��z���t�����s�F�O�����Ŏ擾�����莞��z���t�����s[index]
            //�������F�擾����������
            //�K�p�J�n�N���Fcalc�K�p�J�n�N���i���ݓ����j�̖߂�l
            //�ܗ^���ؓ����Fcalc�ܗ^���ؓ����̖߂�l
            //�ʏ���ؓ���(WEB3)�Fcalc�ʏ���ؓ���(WEB3)�̖߂�
            this.updateMutualFixedBuyConditionChange(
                l_subAccount,
                l_trader,
                l_request.conditionList[i],
                l_mutualFixedBuyConditionUnit,
                l_datOrderBizDate,
                l_datCurrent,
                new Timestamp(l_datCalcPrizeAndCloseDateHour.getTime()),
                new Timestamp(l_datCalcUsuallyCloseDateTime.getTime()));
        }

        // <LOOP����> ���M�莞��z���t�V�K�ǉ����e�̌�����LOOP
        for (int i = 0; i < l_intAddConditionListCnt; i++)
        {
            //update�莞��z���t�����ύX
            //[update�莞��z���t�����ύX�̈���]
            //�⏕�����Fget�⏕�����̖߂�l
            //���ҁFget�㗝���͎҂̖߂�l
            //�莞��z���t���ʏ��F���M�莞��z���t�V�K�ǉ����e[index]
            //���M�莞��z���t�����s�Fnull
            //�������F�擾����������
            //�K�p�J�n�N���Fcalc�K�p�J�n�N���i���ݓ����j�̖߂�l
            //�ܗ^���ؓ����Fcalc�ܗ^���ؓ����̖߂�l
            //�ʏ���ؓ���(WEB3)�Fcalc�ʏ���ؓ���(WEB3)�̖߂�l
            this.updateMutualFixedBuyConditionChange(
                l_subAccount,
                l_trader,
                l_request.addConditionList[i],
                null,
                l_datOrderBizDate,
                l_datCurrent,
                new Timestamp(l_datCalcPrizeAndCloseDateHour.getTime()),
                new Timestamp(l_datCalcUsuallyCloseDateTime.getTime()));
        }

        //create���X�|���X
        //�icreateResponse�̎����j
        //�莞��z���t���������o�^�������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ�
        WEB3MutualFixedBuyConditionCompleteResponse l_response =
            (WEB3MutualFixedBuyConditionCompleteResponse)l_request.createResponse();

        //���X�|���X�Z�b�g
        //���M�莞��z���t�ϗ��o�^���e  = sort�莞��z���t�����ꗗ�̖߂�l
        //���M�莞��z���t�V�K�ǉ����e�@@=�@@�V�K�ǉ����M�莞��z���t�����s
        //���M�莞��z���������@@=�@@null
        //���M�莞��z���t���z���v�@@= null
        //���M�����J�e�S���[�ꗗ  =�@@null
        //���ؓ��A���[�g�v���t���O = ���ؓ��A���[�g�v���t���O�̔��f�ŃZ�b�g�����l
        l_response.conditionList = l_sortFixedBuyConditionList;
        l_response.addConditionList = l_mutualFixedBuyConditionUnits;
        l_response.acountInfo = null;
        l_response.totalList = null;
        l_response.categoryList = null;
        l_response.closingDateAlertFlag = l_blnClosingAlertRequestFlags;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (merge�莞��z���t�����ύX�i�������f�j)<BR>
     * �莞��z���t�������X�g�̓��e�ƒ莞��z���t�����ύX���X�g�̓��e���}�[�W���A<BR>
     * ���M�莞��z���t�����s�̔z����쐬����B<BR>
     * <BR>
     * 1) �莞��z���t���ʃT�[�r�X���擾����B<BR>
     * <BR>
     * 2) �擾�����莞��z���t���ʃT�[�r�X.calc�K�p�J�n�N���i���ݓ����j���R�[������<BR>
     * �@@�@@[calc�K�p�J�n�N���i���ݓ����j�̈���]<BR>
     * �@@�@@�@@�،���ЃR�[�h:����.�،����.getInstitutionCode()�̖߂�l<BR>
     * �@@�@@�@@���X�R�[�h    :����.���X�R�[�h<BR>
     * <BR>
     * 3) ����.�莞��z���t�������X�g�̌��������[�v���A<BR>
     * �@@�@@���M�莞��z���t�����s�̃��X�g���쐬����B<BR>
     * <BR>
     * �@@3)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@3)-2) �g�����M�����}�l�[�W���[.get���M����()���R�[������B<BR>
     * �@@�@@�mget���M�����̈����n<BR>
     * �@@�@@�@@�@@�،���ЁF����.�،����<BR>
     * �@@�@@�@@�@@�����R�[�h�F�����D�莞��z���t�������X�g.�莞��z���t����Row�D�����R�[�h<BR>
     * <BR>
     * �@@3)-3) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B<BR>
     * �@@�@@�@@�@@******************************************************************<BR>
     * �@@�@@�@@�@@**** �莞��z���t�����e�[�u���̓��e�𓊐M�莞��z���t�����s�I�u�W�F�N�g��<BR>
     * �@@�@@�@@�@@**** �@@�v���p�e�B�փZ�b�g����B<BR>
     * �@@�@@�@@�@@******************************************************************<BR>
     * �@@�@@�@@�@@[�Z�b�g������e]<BR>
     * �@@�@@�@@�@@�����R�[�h�F�����D�莞��z���t�������X�g.�莞��z���t����Row�D�����R�[�h<BR>
     * �@@�@@�@@�@@�������F�擾�����g�����M����.get������<BR>
     * �@@�@@�@@�@@���M�����J�e�S���[�R�[�h�F�擾�������M�����}�X�^Row.get�J�e�S���R�[�h<BR>
     * �@@�@@�@@�@@���t���z�i���X�j�F<BR>
     * �@@�@@�@@�@@�@@�@@�����D�莞��z���t�������X�g.�莞��z���t����Row�D���t���z�i���X�j<BR>
     * �@@�@@�@@�@@���t���z�i�ςݑ����j�F<BR>
     * �@@�@@�@@�@@�@@�@@�����D�莞��z���t�������X�g.�莞��z���t����Row�D���t���z�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�����\�������F�擾�������M�����}�X�^Row.get�\������<BR>
     * �@@�@@�@@�@@�K�p�J�n�N���F<BR>
     * �@@�@@�@@�@@�@@�@@�����D�莞��z���t�������X�g.�莞��z���t����Row�D���������N��>=<BR>
     * �@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�������X�g.�莞��z���t����Row�D���������N�����Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@�����D�莞��z���t�������X�g.�莞��z���t����Row�D���������N����<BR>
     * �@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l���Z�b�g<BR>
     * �@@�@@�@@�@@�X�V�����Fnull<BR>
     * �@@�@@�@@�@@���������N���F<BR>
     * �@@�@@�@@�@@�@@�@@�����D�莞��z���t�������X�g.�莞��z���t����Row�D���������N��>=<BR>
     * �@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�������X�g.�莞��z���t����Row�D���������N�����Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@�����D�莞��z���t�������X�g.�莞��z���t����Row�D���������N����<BR>
     * �@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l���Z�b�g<BR>
     * �@@�@@�@@�@@�m��������z�i�ςݑ����j�Fnull<BR>
     * �@@�@@�@@�@@�ژ_�����{���`�F�b�N�Fnull<BR>
     * �@@�@@�@@�@@�ꎞ��~���t���O�Ffalse<BR>
     * �@@�@@�@@�@@sonar���M�`�F�b�N�F "1�Fsonar���M�\������"<BR> 
     * <BR>
     * �@@3)-4) ���M�莞��z���t�����s�I�u�W�F�N�g�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B<BR>
     * <BR>
     * 4) ����.�莞��z���t�����ύX���X�g�Ń��[�v����<BR>
     * <BR>
     * �@@4)-1) ���M�莞��z���t�����s�̃��X�g�Ń��[�v����B<BR>
     * <BR>
     * �@@�@@�@@4)-1)-1) �ȉ��̏����Ŕ�r���A��v����ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h  ==<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���M�莞��z���t�����s�D�����R�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@4)-1)-1)-1) �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u1�F�ǉ��v�܂��́u2�F�ύX�v�܂��́u4�F�ꎞ��~�v�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@4)-1)-1)-1)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@********************************************************<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u���̓��e�œ��M�莞��z<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �@@���t�����s�̓��e���㏑������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@********************************************************<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���t���z�i���X�j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i���X�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���t���z�i�ςݑ����j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�K�p�J�n�N���F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��>=<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N�����Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���������N���F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��>=<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N�����Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�ꎞ��~���t���O�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u4�F�ꎞ��~�v�̏ꍇ�Atrue<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u4�F�ꎞ��~�v�ȊO�̏ꍇ�Afalse<BR>
     * <BR>
     * �@@�@@�@@�@@�@@4)-1)-1)-2) ��L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@********************************************************<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u���̓��e�����ɓ��M�莞��z<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �@@���t�����s�̃��X�g����폜����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u��.�ύX�敪���u3�F�����v�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �@@���M�莞��z���t�����s�̃��X�g����폜����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@********************************************************<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@4)-1)-1)-2)-1) ���M�莞��z���t�����s���X�g����Y������s�I�u�W�F�N�g���폜����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@4)-1)-1)-3) ����.�莞��z���t�����ύX���X�g�̃��[�v�֖߂�B<BR>
     * <BR>
     * �@@***********************************************************************<BR>
     * �@@**** �莞��z���t�����ύX���X�g�ɑ��݂��A<BR>
     * �@@**** �@@���M�莞��z���t�����s�̃��X�g�ɑ��݂��Ȃ��ꍇ�́A���̉ӏ��ɏ���������B<BR>
     * �@@***********************************************************************<BR>
     * �@@4)-2)�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�u1�F�ǉ��v�܂��́u2�F�ύX�v�܂��́u4�F�ꎞ��~�v�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@4)-2)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@4)-2)-2) �g�����M�����}�l�[�W���[.get���M����()���R�[������B<BR>
     * �@@�@@�@@�@@�mget���M�����̈����n<BR>
     * �@@�@@�@@�@@�@@�@@�،���ЁF����.�،����<BR>
     * �@@�@@�@@�@@�@@�@@�����R�[�h�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h<BR>
     * <BR>
     * �@@�@@�@@4)-2)-3) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B<BR>
     * �@@�@@�@@�@@******************************************************************<BR>
     * �@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u���݂̂ɑ��݂���ꍇ�A<BR>
     * �@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u���̓��e�𓊐M�莞��z���t�����s��<BR>
     * �@@�@@�@@�@@**** �@@�v���p�e�B�փZ�b�g����B<BR>
     * �@@�@@�@@�@@******************************************************************<BR>
     * �@@�@@�@@�@@[�Z�b�g������e]<BR>
     * �@@�@@�@@�@@�����R�[�h�F<BR>
     * �@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h<BR>
     * �@@�@@�@@�@@�������F�擾�����g�����M����.get������<BR>
     * �@@�@@�@@�@@���M�����J�e�S���[�R�[�h�F�擾�������M�����}�X�^Row.get�J�e�S���R�[�h<BR>
     * �@@�@@�@@�@@���t���z�i���X�j�F<BR>
     * �@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i���X�j<BR>
     * �@@�@@�@@�@@���t���z�i�ςݑ����j�F<BR>
     * �@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�����\�������F�擾�������M�����}�X�^Row.get�\������<BR>
     * �@@�@@�@@�@@�K�p�J�n�N���F<BR>
     * �@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��>=<BR>
     * �@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N�����Z�b�g<BR>
     * �@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N����<BR>
     * �@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l���Z�b�g<BR>
     * �@@�@@�@@�@@�X�V�����Fnull<BR>
     * �@@�@@�@@�@@���������N���F<BR>
     * �@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��>=<BR>
     * �@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N�����Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N����<BR>
     * �@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@calc�K�p�J�n�N���i���ݓ����j�̖߂�l���Z�b�g<BR>
     * �@@�@@�@@�@@�m��������z�i�ςݑ����j�Fnull<BR>
     * �@@�@@�@@�@@�ژ_�����{���`�F�b�N�Fnull<BR>
     * �@@�@@�@@�@@�ꎞ��~���t���O�F<BR>
     * �@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�@@�@@�@@�@@�u4�F�ꎞ��~�v�̏ꍇ�Atrue<BR>
     * �@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�@@�@@�@@�@@�u4�F�ꎞ��~�v�ȊO�̏ꍇ�Afalse<BR>
     * �@@�@@�@@�@@sonar���M�`�F�b�N�F "0�Fsonar���M�\���Ȃ�"<BR> 
     * <BR>
     * �@@�@@�@@4)-2)-4)  ���M�莞��z���t�����s�I�u�W�F�N�g�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B<BR>
     * <BR>
     * 5)�@@���M�莞��z���t�����s�̃��X�g���瓊�M�莞��z���t�����s�̔z����쐬����B<BR>
     * <BR>
     * 6) ���M�莞��z���t�����s�̔z������^�[������B<BR>
     * @@param l_institution - (�،����)<BR>
     * �،����<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_lisFixedBuyConditionLists - (�莞��z���t�������X�g)<BR>
     * �莞��z���t�������X�g<BR>
     * @@param l_lisFixedBuyConditionChangeLists - (�莞��z���t�����ύX���X�g)<BR>
     * �莞��z���t�����ύX���X�g<BR>
     * @@return WEB3MutualFixedBuyConditionUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 48525B4F0079
     */
    protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionChangeMonth(
        Institution l_institution,
        String l_strBranchCode,
        List l_lisFixedBuyConditionLists,
        List l_lisFixedBuyConditionChangeLists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "mergeMutualFixedBuyConditionChangeMonth("
            + "Institution, String, List, List)";
        log.entering(STR_METHOD_NAME);

        if (l_lisFixedBuyConditionLists == null
            || l_lisFixedBuyConditionChangeLists == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //1) �莞��z���t���ʃT�[�r�X���擾����B
        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);

        //2) �擾�����莞��z���t���ʃT�[�r�X.calc�K�p�J�n�N���i���ݓ����j���R�[������
        //[calc�K�p�J�n�N���i���ݓ����j�̈���]
        //�،���ЃR�[�h:����.�،����.getInstitutionCode()�̖߂�l
        //���X�R�[�h    :����.���X�R�[�h
        Date l_datCurrentDate =
            l_mutualFixedBuyCommonService.calcValidStartDateCurrentDate(
                l_institution.getInstitutionCode(),
                l_strBranchCode);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //�g�����M�����}�l�[�W�����擾����B
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        //3) ����.�莞��z���t�������X�g�̌��������[�v���A���M�莞��z���t�����s�̃��X�g���쐬����B
        Iterator l_iteratorFixedBuyConditionLists =
            l_lisFixedBuyConditionLists.iterator();
        List l_lisUnitLists = new ArrayList();
        MutualFundProduct l_mutualFundProduct;
        while (l_iteratorFixedBuyConditionLists.hasNext())
        {
            MfFixedBuyingCondRow l_mfFixedBuyingCondRow =
                (MfFixedBuyingCondRow)l_iteratorFixedBuyConditionLists.next();

            //3)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�𐶐�����B
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();

            //3)-2) �g�����M�����}�l�[�W���[.get���M����()���R�[������B
            //�mget���M�����̈����n
            //�،���ЁF����.�،����
            //�����R�[�h�F�����D�莞��z���t�������X�g.�莞��z���t����Row�D�����R�[�h
            try
            {
                l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_mfFixedBuyingCondRow.getProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            MutualFundProductRow l_mutualFundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            //3)-3) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B
            //�莞��z���t�����e�[�u���̓��e�𓊐M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�փZ�b�g����B
            //[�Z�b�g������e]
            //�����R�[�h�F�����D�莞��z���t�������X�g.�莞��z���t����Row�D�����R�[�h
            l_mutualFixedBuyConditionUnit.mutualProductCode =
                l_mfFixedBuyingCondRow.getProductCode();
            //�������F�擾�����g�����M����.get������
            l_mutualFixedBuyConditionUnit.mutualProductName =
                l_mutualFundProductRow.getStandardName();
            //���M�����J�e�S���[�R�[�h�F�擾�������M�����}�X�^Row.get�J�e�S���R�[�h
            l_mutualFixedBuyConditionUnit.categoryCode =
                l_mutualFundProductRow.getCategoryCode();
            //���t���z�i���X�j�F�����D�莞��z���t�������X�g.�莞��z���t����Row�D���t���z�i���X�j
            if (l_mfFixedBuyingCondRow.getMonthlyBuyAmountIsNull())
            {
                l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                    WEB3StringTypeUtility.formatNumber(
                        l_mfFixedBuyingCondRow.getMonthlyBuyAmount());
            }
            //���t���z�i�ςݑ����j�F�����D�莞��z���t�������X�g.�莞��z���t����Row�D���t���z�i�ςݑ����j
            if (l_mfFixedBuyingCondRow.getIncreaseBuyAmountIsNull())
            {
                l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                    WEB3StringTypeUtility.formatNumber(
                        l_mfFixedBuyingCondRow.getIncreaseBuyAmount());
            }
            //�����\�������F�擾�������M�����}�X�^Row.get�\������
            if (l_mutualFundProductRow.getIndicationRankingIsNull())
            {
                l_mutualFixedBuyConditionUnit.displayOrder = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnit.displayOrder =
                    WEB3StringTypeUtility.formatNumber(
                        l_mutualFundProductRow.getIndicationRanking());
            }

            //�����D�莞��z���t�������X�g.�莞��z���t����Row�D���������N��>=
            //calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A
            int l_intCompareToMonth = WEB3DateUtility.compareToMonth(
                l_datCurrentDate,
                l_mfFixedBuyingCondRow.getDrawDate());
            if (l_intCompareToMonth <= 0)
            {
                //�K�p�J�n�N���F
                //�����D�莞��z���t�������X�g.�莞��z���t����Row�D���������N�����Z�b�g
                l_mutualFixedBuyConditionUnit.validStartDate =
                    l_mfFixedBuyingCondRow.getDrawDate();

                //���������N���F
                //�����D�莞��z���t�������X�g.�莞��z���t����Row�D���������N�����Z�b�g
                l_mutualFixedBuyConditionUnit.debitAccountYM =
                    new Timestamp(l_mfFixedBuyingCondRow.getDrawDate().getTime());
            }
            //�����D�莞��z���t�������X�g.�莞��z���t����Row�D���������N����
            //calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A
            else
            {
                //�K�p�J�n�N���F�A
                //calc�K�p�J�n�N���i���ݓ����j�̖߂�l���Z�b�g
                l_mutualFixedBuyConditionUnit.validStartDate = l_datCurrentDate;

                //���������N���F
                //calc�K�p�J�n�N���i���ݓ����j�̖߂�l���Z�b�g
                l_mutualFixedBuyConditionUnit.debitAccountYM =
                    new Timestamp(l_datCurrentDate.getTime());
            }
            //�X�V�����Fnull
            l_mutualFixedBuyConditionUnit.updateDate = null;
            //�m��������z�i�ςݑ����j�Fnull
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
            //�ژ_�����{���`�F�b�N�Fnull
            l_mutualFixedBuyConditionUnit.checkResult = null;
            //�ꎞ��~���t���O�Ffalse
            l_mutualFixedBuyConditionUnit.suspensionFlag = false;
            //sonar���M�`�F�b�N�F "1�Fsonar���M�\������"
            l_mutualFixedBuyConditionUnit.sonarSendCheck = 
                    WEB3MFSonarSendCheckDivDef.SEND_POSSIBILITY;

            //3)-4) ���M�莞��z���t�����s�I�u�W�F�N�g�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B
            l_lisUnitLists.add(l_mutualFixedBuyConditionUnit);
        }

        //4) ����.�莞��z���t�����ύX���X�g�Ń��[�v����
        Iterator l_iteratorChangeLists =
            l_lisFixedBuyConditionChangeLists.iterator();
        while (l_iteratorChangeLists.hasNext())
        {
            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
                (MfFixedBuyingChangeRow)l_iteratorChangeLists.next();

            String l_strChangeDiv = l_mfFixedBuyingChangeRow.getChangeDiv();

            //4)-1) ���M�莞��z���t�����s�̃��X�g�Ń��[�v����B
            Iterator l_iteratorUnitLists = l_lisUnitLists.iterator();
            boolean l_blnFlag = true;
            while (l_iteratorUnitLists.hasNext())
            {
                WEB3MutualFixedBuyConditionUnit l_unit =
                    (WEB3MutualFixedBuyConditionUnit)l_iteratorUnitLists.next();

                //4)-1)-1) �ȉ��̏����Ŕ�r���A��v����ꍇ
                //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h  ==
                //���M�莞��z���t�����s�D�����R�[�h
                if (l_mfFixedBuyingChangeRow.getProductCode().equals(
                    l_unit.mutualProductCode))
                {
                    //4)-1)-1)-1) �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
                    //�u1�F�ǉ��v�܂���
                    //�u2�F�ύX�v�܂���
                    //�u4�F�ꎞ��~�v�̏ꍇ
                    if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                    {
                        //4)-1)-1)-1)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B
                        //�莞��z���t�����ύX�e�[�u���̓��e�œ��M�莞��z���t�����s�̓��e���㏑������B
                        //���t���z�i���X�j�F
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i���X�j
                        if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                        {
                            l_unit.monthlyBuyAmount = null;
                        }
                        else
                        {
                            l_unit.monthlyBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                        }
                        //���t���z�i�ςݑ����j�F
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j
                        if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                        {
                            l_unit.increaseBuyAmount = null;
                        }
                        else
                        {
                            l_unit.increaseBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                        }

                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��>=
                        //calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A
                        int l_intCompareToMonth = WEB3DateUtility.compareToMonth(
                            l_datCurrentDate,
                            l_mfFixedBuyingChangeRow.getValidStartDate());
                        if (l_intCompareToMonth <= 0)
                        {
                            //�K�p�J�n�N���F
                            //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N�����Z�b�g�B
                            l_unit.validStartDate =
                                l_mfFixedBuyingChangeRow.getValidStartDate();

                            //���������N���F
                            //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N�����Z�b�g�B
                            l_unit.debitAccountYM =
                                new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                        }
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N����
                        //calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ
                        else
                        {
                            //�K�p�J�n�N���F
                            //calc�K�p�J�n�N���i���ݓ����j�̖߂�l���Z�b�g�B
                            l_unit.validStartDate = l_datCurrentDate;

                            //���������N���F
                            //calc�K�p�J�n�N���i���ݓ����j�̖߂�l���Z�b�g�B
                            l_unit.debitAccountYM =
                                new Timestamp(l_datCurrentDate.getTime());
                        }
                        //�ꎞ��~���t���O�F
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
                        //�u4�F�ꎞ��~�v�̏ꍇ�Atrue
                        if (WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                        {
                            l_unit.suspensionFlag = true;
                        }
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
                        //�u4�F�ꎞ��~�v�ȊO�̏ꍇ�Afalse
                        else
                        {
                            l_unit.suspensionFlag = false;
                        }
                    }
                    //4)-1)-1)-2) ��L�ȊO�̏ꍇ
                    //�莞��z���t�����ύX�e�[�u���̓��e�����ɓ��M�莞��z���t�����s�̃��X�g����폜����B
                    //�莞��z���t�����ύX�e�[�u��.�ύX�敪���u3�F�����v�̏ꍇ�A
                    //���M�莞��z���t�����s�̃��X�g����폜����B
                    else
                    {
                        //4)-1)-1)-2)-1) ���M�莞��z���t�����s���X�g����Y������s�I�u�W�F�N�g���폜����B
                        l_iteratorUnitLists.remove();
                    }

                    l_blnFlag = false;
                    //4)-1)-1)-3) ����.�莞��z���t�����ύX���X�g�̃��[�v�֖߂�B
                    break;
                }
            }

            if (!l_blnFlag)
            {
                continue;
            }

            //�莞��z���t�����ύX���X�g�ɑ��݂��A���M�莞��z���t�����s�̃��X�g�ɑ��݂��Ȃ��ꍇ�́A
            //���̉ӏ��ɏ���������B
            //4)-2) �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
            //�u1�F�ǉ��v�܂��́u2�F�ύX�v�܂��́u4�F�ꎞ��~�v�̏ꍇ
            if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv)
                || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
            {
                //4)-2)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�𐶐�����B
                WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                    new WEB3MutualFixedBuyConditionUnit();

                //4)-2)-2) �g�����M�����}�l�[�W���[.get���M����()���R�[������B
                //�mget���M�����̈����n
                //�،���ЁF����.�،����
                //�����R�[�h�F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h
                try
                {
                    l_mutualFundProduct =
                        l_mutualFundProductManager.getMutualFundProduct(
                            l_institution,
                            l_mfFixedBuyingChangeRow.getProductCode());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
                //4)-2)-3) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B
                //�莞��z���t�����ύX�e�[�u���݂̂ɑ��݂���ꍇ�A
                //�莞��z���t�����ύX�e�[�u���̓��e�𓊐M�莞��z���t�����s�̃v���p�e�B�փZ�b�g����B
                if (l_mfFixedBuyingChangeRow != null)
                {
                    //[�Z�b�g������e]
                    //�����R�[�h�F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h
                    l_mutualFixedBuyConditionUnit.mutualProductCode =
                        l_mfFixedBuyingChangeRow.getProductCode();
                    //�������F�擾�����g�����M����.get������
                    l_mutualFixedBuyConditionUnit.mutualProductName =
                        l_mutualFundProductRow.getStandardName();
                    //���M�����J�e�S���[�R�[�h�F�擾�������M�����}�X�^Row.get�J�e�S���R�[�h
                    l_mutualFixedBuyConditionUnit.categoryCode =
                        l_mutualFundProductRow.getCategoryCode();
                    //���t���z�i���X�j�F
                    //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i���X�j
                    if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                    {
                        l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
                    }
                    else
                    {
                        l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                            WEB3StringTypeUtility.formatNumber(
                                l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                    }
                    //���t���z�i�ςݑ����j�F
                    //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j
                    if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                    {
                        l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
                    }
                    else
                    {
                        l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                            WEB3StringTypeUtility.formatNumber(
                                l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                    }
                    //�����\�������F�擾�������M�����}�X�^Row.get�\������
                    if (l_mutualFundProductRow.getIndicationRankingIsNull())
                    {
                        l_mutualFixedBuyConditionUnit.displayOrder = null;
                    }
                    else
                    {
                        l_mutualFixedBuyConditionUnit.displayOrder =
                            WEB3StringTypeUtility.formatNumber(
                                l_mutualFundProductRow.getIndicationRanking());
                    }

                    //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��>=
                    //calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A
                    int l_intCompareToMonth = WEB3DateUtility.compareToMonth(
                        l_datCurrentDate,
                        l_mfFixedBuyingChangeRow.getValidStartDate());
                    if (l_intCompareToMonth <= 0)
                    {
                        //�K�p�J�n�N���F
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N�����Z�b�g
                        l_mutualFixedBuyConditionUnit.validStartDate =
                            l_mfFixedBuyingChangeRow.getValidStartDate();

                        //���������N���F
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N�����Z�b�g
                        l_mutualFixedBuyConditionUnit.debitAccountYM =
                            new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                    }
                    //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N����
                    //calc�K�p�J�n�N���i���ݓ����j�̖߂�l�̏ꍇ�A
                    else
                    {
                        //�K�p�J�n�N���F
                        //calc�K�p�J�n�N���i���ݓ����j�̖߂�l���Z�b�g
                        l_mutualFixedBuyConditionUnit.validStartDate =
                            l_datCurrentDate;

                        //���������N���F
                        //calc�K�p�J�n�N���i���ݓ����j�̖߂�l���Z�b�g
                        l_mutualFixedBuyConditionUnit.debitAccountYM =
                            new Timestamp(l_datCurrentDate.getTime());
                    }
                    //�X�V�����Fnull
                    l_mutualFixedBuyConditionUnit.updateDate = null;
                    //�m��������z�i�ςݑ����j�Fnull
                    l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
                    //�ژ_�����{���`�F�b�N�Fnull
                    l_mutualFixedBuyConditionUnit.checkResult = null;
                    //�ꎞ��~���t���O�F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
                    //�u4�F�ꎞ��~�v�̏ꍇ�Atrue
                    if (WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                    {
                        l_mutualFixedBuyConditionUnit.suspensionFlag = true;
                    }
                    //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
                    //�u4�F�ꎞ��~�v�ȊO�̏ꍇ�Afalse
                    else
                    {
                        l_mutualFixedBuyConditionUnit.suspensionFlag = false;
                    }
                    //sonar���M�`�F�b�N�F "0�Fsonar���M�\���Ȃ�" 
                    l_mutualFixedBuyConditionUnit.sonarSendCheck = 
                            WEB3MFSonarSendCheckDivDef.SEND_NO_POSSIBILITY;
                }

                //4)-2)-4)  ���M�莞��z���t�����s�I�u�W�F�N�g�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B
                l_lisUnitLists.add(l_mutualFixedBuyConditionUnit);
            }
        }

        //5)�@@���M�莞��z���t�����s�̃��X�g���瓊�M�莞��z���t�����s�̔z����쐬����B
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            new WEB3MutualFixedBuyConditionUnit[l_lisUnitLists.size()];
        l_lisUnitLists.toArray(l_mutualFixedBuyConditionUnits);

        //6) ���M�莞��z���t�����s�̔z������^�[������B
        log.exiting(STR_METHOD_NAME);
        return l_mutualFixedBuyConditionUnits;
    }

    /**
     * (merge�莞��z���t�����ύX�i���Ҍ����f�j)<BR>
     * merge�莞��z���t�����s���X�g�̓��e�ƒ莞��z���t�����ύX���X�g�̓��e���}�[�W���A<BR>
     * ���M�莞��z���t�����s�̔z����쐬����B<BR>
     * <BR>
     * 1) ����.�莞��z���t�����ύX���X�g�̌��������[�v����B<BR>
     * <BR>
     * �@@1)-1) �g�����M�����}�l�[�W���[.get���M����()���R�[������B<BR>
     * �@@�@@�mget���M�����̈����n<BR>
     * �@@�@@�@@�،���ЁF�����D�،����<BR>
     * �@@�@@�@@�����R�[�h�F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h<BR>
     * <BR>
     * �@@1)-2) ����.merge�莞��z���t�����s���X�g�Ń��[�v����<BR>
     * <BR>
     * �@@�@@�@@1)-2)-1) �ȉ��̏����Ŕ�r����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h ==<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����Dmerge�莞��z���t�����s���X�g�D�����R�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@1)-2)-1)-1) �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u1�F�ǉ��v�܂��́u2�F�ύX�v�܂��́u4�F�ꎞ��~�v�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@1)-2)-1)-1)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@********************************************************<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u���̓��e��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �@@���M�莞��z���t�����s�̓��e���㏑������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@********************************************************<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���t���z�i���X�j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i���X�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���t���z�i�ςݑ����j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�K�p�J�n�N���F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���������N���F<BR>
     *  �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�ꎞ��~���t���O�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u4�F�ꎞ��~�v�̏ꍇ�Atrue<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u4�F�ꎞ��~�v�ȊO�̏ꍇ�Afalse<BR>
     * <BR>
     * �@@�@@�@@�@@�@@1)-2)-1)-2) ��L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@********************************************************<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u���̓��e�����ɓ��M�莞��z<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �@@���t�����s�̃��X�g����폜����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u��.�ύX�敪���u3�F�����v�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �@@���M�莞��z���t�����s�̃��X�g����폜����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@********************************************************<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@1)-2)-1)-2)-1) ���M�莞��z���t�����s�̃��X�g����Y������s�I�u�W�F�N�g���폜����B<BR>
     * <BR>
     *  �@@�@@�@@�@@�@@1)-2)-1)-3) �莞��z���t�����ύX���X�g�̃��[�v�֖߂�B<BR>
     * <BR>
     * �@@***********************************************************************<BR>
     * �@@**** �莞��z���t�����ύX���X�g�ɑ��݂��A<BR>
     * �@@**** �@@merge�莞��z���t�����s���X�g�ɑ��݂��Ȃ��ꍇ�́A���̉ӏ��ɏ���������B<BR>
     * �@@***********************************************************************<BR>
     * �@@1)-3) �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�@@�@@�@@�u1�F�ǉ��v�܂��́u2�F�ύX�v�܂��́u4�F�ꎞ��~�v�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@1)-3)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@1)-3)-2) �g�����M�����}�l�[�W���[.get���M����()���R�[������B<BR>
     * �@@�@@�@@�@@�mget���M�����̈����n<BR>
     * �@@�@@�@@�@@�@@�@@�،���ЁF�����D�،����<BR>
     * �@@�@@�@@�@@�@@�@@�����R�[�h�F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h<BR>
     * <BR>
     * �@@�@@�@@1)-3)-3) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B<BR>
     * �@@�@@�@@�@@******************************************************************<BR>
     * �@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u���݂̂ɑ��݂���ꍇ�A<BR>
     * �@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u���̓��e�𓊐M�莞��z���t�����s��<BR>
     * �@@�@@�@@�@@**** �@@�v���p�e�B�փZ�b�g����B<BR>
     * �@@�@@�@@�@@******************************************************************<BR>
     * �@@�@@�@@�@@[�Z�b�g������e]<BR>
     * �@@�@@�@@�@@�����R�[�h�F<BR>
     * �@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h<BR>
     * �@@�@@�@@�@@�������F�擾�����g�����M����.get������<BR>
     * �@@�@@�@@�@@���M�����J�e�S���[�R�[�h�F�擾�������M�����}�X�^Row.get�J�e�S���R�[�h<BR>
     * �@@�@@�@@�@@���t���z�i���X�j�F<BR>
     * �@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i���X�j<BR>
     * �@@�@@�@@�@@���t���z�i�ςݑ����j�F<BR>
     * �@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�����\�������F�擾�������M�����}�X�^Row.get�\������<BR>
     * �@@�@@�@@�@@�K�p�J�n�N���F<BR>
     * �@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��<BR>
     * �@@�@@�@@�@@�X�V�����Fnull<BR>
     * �@@�@@�@@�@@���������N���F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��<BR>
     * �@@�@@�@@�@@�m��������z�i�ςݑ����j�Fnull<BR>
     * �@@�@@�@@�@@�ژ_�����{���`�F�b�N�Fnull<BR>
     * �@@�@@�@@�@@�ꎞ��~���t���O�F<BR>
     * �@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�@@�@@�@@�@@�u4�F�ꎞ��~�v�̏ꍇ�Atrue<BR>
     * �@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�@@�@@�@@�@@�u4�F�ꎞ��~�v�ȊO�̏ꍇ�Afalse<BR>
     * �@@�@@�@@�@@sonar���M�`�F�b�N�F "0�Fsonar���M�\���Ȃ�"<BR>
     * <BR>
     * �@@�@@�@@1)-3)-4) ���M�莞��z���t�����s�I�u�W�F�N�g�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B<BR>
     * <BR>
     * 2)�@@���M�莞��z���t�����s�̃��X�g���瓊�M�莞��z���t�����s�̔z����쐬����B<BR>
     * <BR>
     * 3) ���M�莞��z���t�����s�̔z������^�[������B<BR>
     * @@param l_institution - (�،����)<BR>
     * �،����<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_mergeMutualFixedBuyConditionUnitLists - (merge�莞��z���t�����s���X�g)<BR>
     * merge�莞��z���t�����s���X�g<BR>
     * @@param l_lisFixedBuyConditionChangeLists - (�莞��z���t�����ύX���X�g)<BR>
     * �莞��z���t�����ύX���X�g<BR>
     * @@return WEB3MutualFixedBuyConditionUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4855C52A02F7
     */
    protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionChangeFutureMonth(
        Institution l_institution,
        String l_strBranchCode,
        WEB3MutualFixedBuyConditionUnit[] l_mergeMutualFixedBuyConditionUnitLists,
        List l_lisFixedBuyConditionChangeLists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "mergeMutualFixedBuyConditionChangeFutureMonth("
            + "Institution, String, WEB3MutualFixedBuyConditionUnit[], List)";
        log.entering(STR_METHOD_NAME);

        if (l_lisFixedBuyConditionChangeLists == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //�g�����M�����}�l�[�W�����擾����B
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        int l_intLength = 0;
        if (l_mergeMutualFixedBuyConditionUnitLists != null)
        {
            l_intLength = l_mergeMutualFixedBuyConditionUnitLists.length;
        }

        List l_lisUnitLists = new ArrayList();
        for (int i = 0; i < l_intLength; i++)
        {
            l_lisUnitLists.add(l_mergeMutualFixedBuyConditionUnitLists[i]);
        }
        //1) ����.�莞��z���t�����ύX���X�g�̌��������[�v����B
        Iterator l_iteratorChangeLists =
            l_lisFixedBuyConditionChangeLists.iterator();
        MutualFundProduct l_mutualFundProduct;
        while (l_iteratorChangeLists.hasNext())
        {
            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
                (MfFixedBuyingChangeRow)l_iteratorChangeLists.next();

            String l_strChangeDiv = l_mfFixedBuyingChangeRow.getChangeDiv();

            //1)-1) �g�����M�����}�l�[�W���[.get���M����()���R�[������B
            //�mget���M�����̈����n
            //�،���ЁF�����D�،����
            //�����R�[�h�F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h
            try
            {
                l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_mfFixedBuyingChangeRow.getProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            boolean l_blnFlag = true;
            //1)-2) ����.merge�莞��z���t�����s���X�g�Ń��[�v����
            Iterator l_iteratorMutualFixedBuyConditionUnits =
                l_lisUnitLists.iterator();
            while (l_iteratorMutualFixedBuyConditionUnits.hasNext())
            {
                //1)-2)-1) �ȉ��̏����Ŕ�r����B
                //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h   ==
                //�����Dmerge�莞��z���t�����s���X�g�D�����R�[�h
                WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                    (WEB3MutualFixedBuyConditionUnit)l_iteratorMutualFixedBuyConditionUnits.next();
                if (l_mfFixedBuyingChangeRow.getProductCode().equals(
                    l_mutualFixedBuyConditionUnit.mutualProductCode))
                {
                    //1)-2)-1)-1) �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
                    //�u1�F�ǉ��v�܂��́u2�F�ύX�v�܂��́u4�F�ꎞ��~�v�̏ꍇ
                    if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                    {
                        //1)-2)-1)-1)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B
                        //�莞��z���t�����ύX�e�[�u���̓��e�œ��M�莞��z���t�����s�̓��e���㏑������B
                        //���t���z�i���X�j�F
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i���X�j
                        if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                        {
                            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
                        }
                        else
                        {
                            l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                        }
                        //���t���z�i�ςݑ����j�F
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j
                        if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                        {
                            l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
                        }
                        else
                        {
                            l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                        }
                        //�K�p�J�n�N���F
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��
                        l_mutualFixedBuyConditionUnit.validStartDate =
                            l_mfFixedBuyingChangeRow.getValidStartDate();
                        //���������N���F
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��
                        l_mutualFixedBuyConditionUnit.debitAccountYM =
                            new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                        //�ꎞ��~���t���O�F
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
                        //�u4�F�ꎞ��~�v�̏ꍇ�Atrue
                        if (WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                        {
                            l_mutualFixedBuyConditionUnit.suspensionFlag = true;
                        }
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
                        //�u4�F�ꎞ��~�v�ȊO�̏ꍇ�Afalse
                        else
                        {
                            l_mutualFixedBuyConditionUnit.suspensionFlag = false;
                        }
                    }

                    //1)-2)-1)-2) ��L�ȊO�̏ꍇ
                    //�莞��z���t�����ύX�e�[�u���̓��e�����ɓ��M�莞��z���t�����s�̃��X�g����폜����B
                    //�莞��z���t�����ύX�e�[�u��.�ύX�敪���u3�F�����v�̏ꍇ�A
                    //���M�莞��z���t�����s�̃��X�g����폜����B
                    //1)-2)-1)-2)-1) ���M�莞��z���t�����s�̃��X�g����Y������s�I�u�W�F�N�g���폜����B
                    else
                    {
                        l_iteratorMutualFixedBuyConditionUnits.remove();
                    }
                    l_blnFlag = false;
                    //1)-2)-1)-3) �莞��z���t�����ύX���X�g�̃��[�v�֖߂�B
                    break;
                }
            }
            //�莞��z���t�����ύX���X�g�ɑ��݂��Amerge�莞��z���t�����s���X�g�ɑ��݂��Ȃ��ꍇ�́A
            //���̉ӏ��ɏ���������B

            if (!l_blnFlag)
            {
                continue;
            }

            //1)-3) �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
            //�u1�F�ǉ��v�܂��́u2�F�ύX�v�܂��́u4�F�ꎞ��~�v�̏ꍇ
            if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv)
                || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
            {
                //1)-3)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�𐶐�����B
                WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                    new WEB3MutualFixedBuyConditionUnit();

                //1)-3)-2) �g�����M�����}�l�[�W���[.get���M����()���R�[������B
                //�mget���M�����̈����n
                //�،���ЁF�����D�،����
                //�����R�[�h�F
                //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h
                try
                {
                    l_mutualFundProduct =
                        l_mutualFundProductManager.getMutualFundProduct(
                            l_institution,
                            l_mfFixedBuyingChangeRow.getProductCode());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
                //1)-3)-3) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B
                //�莞��z���t�����ύX�e�[�u���݂̂ɑ��݂���ꍇ�A
                //�莞��z���t�����ύX�e�[�u���̓��e�𓊐M�莞��z���t�����s�̃v���p�e�B�փZ�b�g����B
                //[�Z�b�g������e]
                //�����R�[�h�F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h
                l_mutualFixedBuyConditionUnit.mutualProductCode =
                    l_mfFixedBuyingChangeRow.getProductCode();
                //�������F�擾�����g�����M����.get������
                l_mutualFixedBuyConditionUnit.mutualProductName =
                    l_mutualFundProductRow.getStandardName();
                //���M�����J�e�S���[�R�[�h�F�擾�������M�����}�X�^Row.get�J�e�S���R�[�h
                l_mutualFixedBuyConditionUnit.categoryCode =
                    l_mutualFundProductRow.getCategoryCode();
                //���t���z�i���X�j�F
                //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i���X�j
                if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                {
                    l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
                }
                else
                {
                    l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                        WEB3StringTypeUtility.formatNumber(
                            l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                }
                //���t���z�i�ςݑ����j�F
                //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j
                if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                {
                    l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
                }
                else
                {
                    l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                        WEB3StringTypeUtility.formatNumber(
                            l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                }
                //�����\�������F�擾�������M�����}�X�^Row.get�\������
                if (l_mutualFundProductRow.getIndicationRankingIsNull())
                {
                    l_mutualFixedBuyConditionUnit.displayOrder = null;
                }
                else
                {
                    l_mutualFixedBuyConditionUnit.displayOrder =
                        WEB3StringTypeUtility.formatNumber(
                            l_mutualFundProductRow.getIndicationRanking());
                }
                //�K�p�J�n�N���F
                //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��
                l_mutualFixedBuyConditionUnit.validStartDate =
                    l_mfFixedBuyingChangeRow.getValidStartDate();
                //�X�V�����Fnull
                l_mutualFixedBuyConditionUnit.updateDate = null;
                //���������N���F
                //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��
                l_mutualFixedBuyConditionUnit.debitAccountYM =
                    new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                //�m��������z�i�ςݑ����j�Fnull
                l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
                //�ژ_�����{���`�F�b�N�Fnull
                l_mutualFixedBuyConditionUnit.checkResult = null;
                //�ꎞ��~���t���O�F
                //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
                //�u4�F�ꎞ��~�v�̏ꍇ�Atrue
                if (WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                {
                    l_mutualFixedBuyConditionUnit.suspensionFlag = true;
                }
                //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
                //�u4�F�ꎞ��~�v�ȊO�̏ꍇ�Afalse
                else
                {
                    l_mutualFixedBuyConditionUnit.suspensionFlag = false;
                }
                //sonar���M�`�F�b�N�F "0�Fsonar���M�\���Ȃ�"
                l_mutualFixedBuyConditionUnit.sonarSendCheck = 
                        WEB3MFSonarSendCheckDivDef.SEND_NO_POSSIBILITY;

                //1)-3)-4)  ���M�莞��z���t�����s�I�u�W�F�N�g��
                //���M�莞��z���t�����s�̃��X�g�֒ǉ�����B
                l_lisUnitLists.add(l_mutualFixedBuyConditionUnit);
            }
        }

        //2)�@@���M�莞��z���t�����s�̃��X�g���瓊�M�莞��z���t�����s�̔z����쐬����B
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            new WEB3MutualFixedBuyConditionUnit[l_lisUnitLists.size()];
        l_lisUnitLists.toArray(l_mutualFixedBuyConditionUnits);

        //3) ���M�莞��z���t�����s�̔z������^�[������B
        log.exiting(STR_METHOD_NAME);
        return l_mutualFixedBuyConditionUnits;
    }

    /**
     * (get�ŐV����ԍ�)<BR>
     * �莞��z���t�����ύX�����e�[�u���̗���ԍ����̔Ԃ���B<BR>
     * <BR>
     * �P�j�莞��z���t�����ύX�����e�[�u�����������A<BR>
     * �@@�@@�@@�莞��z���t�����ύX����Row�̃��X�g���擾�B<BR>
     * �@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h�F�����D�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�����D���X�R�[�h<BR>
     * �@@�@@�����R�[�h�F�����D�����R�[�h<BR>
     * �@@�@@�����R�[�h�F�����D�����R�[�h<BR>
     * �@@�@@�K�p�J�n�N���F�����D�K�p�J�n�N��<BR>
     * �@@[�\�[�g����]<BR>
     * �@@order by ����ԍ� desc<BR>
     * <BR>
     * �Q�j1���R�[�h�ڂ̒莞��z���t�����ύX����Row�D����ԍ� + 1�����^�[������B<BR>
     * <BR>
     * �����R�[�h�����݂��Ȃ��ꍇ�A1�����^�[������B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_datValidStartDate - (�K�p�J�n�N��)<BR>
     * �K�p�J�n�N��<BR>
     * @@return long
     * @@throws WEB3BaseException
     * @@roseuid 485A4685007C
     */
    protected long getLastNumber(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strProductCode,
        Date l_datValidStartDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getLastNumber(String, String, String, String, Date)";
        log.entering(STR_METHOD_NAME);

        //�P�j�莞��z���t�����ύX�����e�[�u�����������A
        //  �莞��z���t�����ύX����Row�̃��X�g���擾�B
        List l_lisRows = null;

        //[��������]
        //�@@�،���ЃR�[�h�F�����D�،���ЃR�[�h
        //�@@���X�R�[�h�F�����D���X�R�[�h
        //�@@�����R�[�h�F�����D�����R�[�h
        //�@@�����R�[�h�F�����D�����R�[�h
        //�@@�K�p�J�n�N���F�����D�K�p�J�n�N��
        String l_strWhere =
            " institution_code = ? and "
            + " branch_code = ? and "
            + " account_code = ? and "
            + " product_code = ? and "
            + " to_char(valid_start_date, 'yyyyMM') = ? ";

        //[�\�[�g����]
        //order by ����ԍ� desc
        String l_strOrderBy = " serial_no desc ";

        Object[] l_bindVars =
            {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_strProductCode,
                WEB3DateUtility.formatDate(
                    l_datValidStartDate,
                    WEB3GentradeTimeDef.DATE_FORMAT_YM)
            };
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRows =
                l_queryProcessor.doFindAllQuery(
                    MfFixedBuyingChangeHistRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_bindVars);
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

        //���R�[�h�����݂��Ȃ��ꍇ�A1�����^�[������B
        if (l_lisRows == null || l_lisRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return 1L;
        }
        else
        {
            //1���R�[�h�ڂ̒莞��z���t�����ύX����Row�D����ԍ� + 1�����^�[������B
            MfFixedBuyingChangeHistRow l_mfFixedBuyingChangeHistRow =
                (MfFixedBuyingChangeHistRow)l_lisRows.get(0);
            long l_lngSerialNo = l_mfFixedBuyingChangeHistRow.getSerialNo() + 1;

            log.exiting(STR_METHOD_NAME);
            return l_lngSerialNo;
        }
    }

    /**
     * (merge�莞��z���t�����ύX)<BR>
     * �莞��z���t�����e�[�u���̓��e�ƒ莞��z���t�����ύX�e�[�u���̓��e���}�[�W����<BR>
     * ���M�莞��z���t�����s�̔z����쐬����B<BR>
     * <BR>
     * ================================================<BR>
     * ���莞��z���t�����e�[�u���̃��R�[�h���擾����B<BR>
     * �@@�@@�i�����ύX�\�ȃ��R�[�h���擾����B�j<BR>
     * ================================================�@@<BR>
     * �@@1) ��������������Ƃ��āA�ȉ��̕�������쐬����B<BR>
     * �@@�@@�@@"���������N�� >= ? " <BR>
     * <BR>
     * �@@2) ���������l�Ƃ��āA�ȉ���Object�z����쐬����B<BR>
     * �@@�@@�@@�E����.�K�p�J�n�N��<BR>
     * <BR>
     * �@@3) �莞��z���t���ʃT�[�r�X.get�莞��z���t�������X�g���R�[������B<BR>
     * �@@�@@�@@[get�莞��z���t�������X�g�̈���]<BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F<BR>
     * �@@�@@�@@�@@�@@����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l<BR>
     * �@@�@@�@@�@@���X�R�[�h�F<BR>
     * �@@�@@�@@�@@�@@����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l<BR>
     * �@@�@@�@@�@@�����R�[�h�F����.�⏕����.getMainAccount().getAccountCode()�̖߂�l<BR>
     * �@@�@@�@@�@@��������������F�쐬������������������<BR>
     * �@@�@@�@@�@@���������l�F�쐬�������������l<BR>
     * <BR>
     * ================================================<BR>
     * ���莞��z���t�����ύX�e�[�u���̃��R�[�h���擾����B<BR>
     * �@@�@@�iSONAR���f�O�̃��R�[�h�Ɣ��f�ς݂̈ꎞ��~���R�[�h���擾����B�j<BR>
     * ================================================<BR>
     * �@@4) ��������������Ƃ��āA�ȉ��̕�������쐬����B <BR>
     * �@@�@@�@@�@@"( �����敪 in (?,?) or<BR>
     * �@@�@@�@@�@@�ύX�敪 =�@@? ) and<BR>
     * �@@�@@�@@�@@�K�p�J�n�N�� = ? " <BR>
     * <BR>
     * �@@5) ���������l�Ƃ��āA�ȉ���Object�z����쐬����B<BR>
     * �@@�@@�@@�E1�Fsonar�����M<BR>
     * �@@�@@�@@�E�Q�Fsonar���M��<BR>
     * �@@�@@�@@�E�S�F�ꎞ��~<BR>
     * �@@�@@�@@�E����.�K�p�J�n�N��<BR>
     * <BR>
     * �@@6) �莞��z���t���ʃT�[�r�X.get�莞��z���t�����ύX���X�g���R�[������B<BR>
     * �@@�@@�@@[get�莞��z���t�����ύX���X�g�̈���]<BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F<BR>
     * �@@�@@�@@�@@�@@����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l<BR>
     * �@@�@@�@@�@@���X�R�[�h�F<BR>�@@�@@
     * �@@�@@�@@�@@�@@����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l<BR>�@@�@@
     * �@@�@@�@@�@@�����R�[�h�F����.�⏕����.getMainAccount().getAccountCode()�̖߂�l<BR>
     * �@@�@@�@@�@@��������������F�쐬������������������<BR>
     * �@@�@@�@@�@@���������l�F�쐬�������������l<BR>
     * <BR>
     * ================================================<BR>
     * ���莞��z���t�����e�[�u���̃��R�[�h�̓��e��<BR>
     * �@@�@@�莞��z���t�����ύX�e�[�u���̃��R�[�h�̓��e���}�[�W���A<BR>
     * �@@�@@���M�莞��z���t�����s�̔z����쐬����B<BR>
     * ================================================<BR>
     * �@@7) this.merge�莞��z���t�����ύX(�������f)���R�[������B<BR>
     * �@@�@@�@@[merge�莞��z���t�����ύX(�������f)�̈���]<BR>
     * �@@�@@�@@�@@�،���ЁF����.�⏕����.getInstitution()�̖߂�l<BR>
     * �@@�@@�@@�@@���X�R�[�h�F<BR>
     * �@@�@@�@@�@@�@@����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l<BR>
     * �@@�@@�@@�@@�莞��z���t�������X�g�Fget�莞��z���t�������X�g�̖߂�l<BR>
     * �@@�@@�@@�@@�莞��z���t�����ύX���X�g�Fget�莞��z���t�����ύX���X�g�̖߂�l<BR>
     * <BR>
     * ================================================<BR>
     * ���莞��z���t�����ύX�e�[�u���̃��R�[�h���擾����B<BR>
     * �@@�@@�i3�����\���p�̃��R�[�h�Ɩ������ڋq�̕ύX���R�[�h�j<BR>
     * ================================================<BR>
     * �@@8) ��������������Ƃ��āA�ȉ��̕�������쐬����B  <BR>
     * �@@�@@�@@"�����敪 in (?,?) and<BR>
     * �@@�@@�@@ �K�p�J�n�N�� > ?"<BR>
     * <BR>
     * �@@9) ���������l�Ƃ��āA�ȉ���Object�z����쐬����B<BR>
     * �@@�@@�@@�E1�Fsonar�����M<BR>
     * �@@�@@�@@�E�Q�Fsonar���M��<BR>
     * �@@�@@�@@�E����.�K�p�J�n�N��<BR>
     * <BR>
     * �@@10) �莞��z���t���ʃT�[�r�X.get�莞��z���t�����ύX���X�g���R�[������B<BR>
     * �@@�@@�@@[get�莞��z���t�����ύX���X�g�̈���]<BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F<BR>
     * �@@�@@�@@�@@�@@����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l<BR>
     * �@@�@@�@@�@@���X�R�[�h�F<BR>
     * �@@�@@�@@�@@�@@����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l<BR>�@@
     * �@@�@@�@@�@@�����R�[�h�F����.�⏕����.getMainAccount().getAccountCode()�̖߂�l<BR>
     * �@@�@@�@@�@@��������������F�쐬������������������<BR>
     * �@@�@@�@@�@@���������l�F�쐬�������������l<BR>
     * <BR>
     * �@@11�jget�莞��z���t�����ύX���X�g�̖߂�l���O���̏ꍇ�́A<BR>
     * �@@�@@merge�莞��z���t�����ύX(�������f)�̖߂�l�����^�[������B<BR>
     * <BR>
     * ================================================<BR>
     * ���쐬�������M�莞��z���t�����s�̔z���<BR>
     * �@@�@@3�����\���p�̃��R�[�h�Ɩ������ڋq�̕ύX���R�[�h�̓��e���}�[�W���A<BR>
     * �@@�@@���M�莞��z���t�����s�̔z����쐬���ĕԋp����B<BR>
     * ================================================<BR>
     * �@@12�jget�莞��z���t�����ύX���X�g�̖߂�l���O���łȂ��ꍇ�A�ȉ��̏������s���B�@@<BR>
     * <BR>
     * �@@12-1) this.merge�莞��z���t�����ύX(���������f)���R�[������B<BR>
     * �@@�@@�@@[merge�莞��z���t�����ύX(���������f)�̈���]<BR>
     * �@@�@@�@@�@@�،���ЁF����.�⏕����.getInstitution()�̖߂�l<BR>
     * �@@�@@�@@�@@���X�R�[�h�F<BR>
     * �@@�@@�@@�@@�@@����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l<BR>
     * �@@�@@�@@�@@merge�莞��z���t�����s���X�g�F<BR>
     * �@@�@@�@@�@@�@@merge�莞��z���t�����ύX�i�������f�j�̖߂�l<BR>
     * �@@�@@�@@�@@�莞��z���t�����ύX���X�g�Fget�莞��z���t�����ύX���X�g�̖߂�l<BR>
     * <BR>
     * �@@12-2) merge�莞��z���t�����ύX(���������f)�̖߂�l�����^�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_datValidStartDate - (�K�p�J�n�N��)<BR>
     * �K�p�J�n�N��<BR>
     * @@return WEB3MutualFixedBuyConditionUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 485F020E01E7
     */
    protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionChange(
        SubAccount l_subAccount, Date l_datValidStartDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "mergeMutualFixedBuyConditionChange(SubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null
            || l_datValidStartDate == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //���莞��z���t�����e�[�u���̃��R�[�h���擾����B
        //�i�����ύX�\�ȃ��R�[�h���擾����B�j
        //1) ��������������Ƃ��āA�ȉ��̕�������쐬����B
        //"���������N�� >= ? "
        String l_strWhere = " to_char(draw_date, 'yyyyMM') >= ? ";

        //2) ���������l�Ƃ��āA�ȉ���Object�z����쐬����B
        Object[] l_objValues = new Object[]{
            WEB3DateUtility.formatDate(
                l_datValidStartDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YM)};

        //get�莞��z���t�������X�g�̈���]
        //�،���ЃR�[�h�@@�@@�F�@@����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�@@�@@�@@   �F�@@����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        //�����R�[�h�@@�@@�@@   �F�@@����.�⏕����.getMainAccount().getAccountCode()�̖߂�l
        //��������������     �F�쐬������������������
        //���������l�@@  �@@   �F�쐬�������������l
        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        List l_fixedBuyConditionLists = l_mutualFixedBuyCommonService.getFixedBuyConditionList(
            l_subAccount.getInstitution().getInstitutionCode(),
            l_subAccount.getMainAccount().getBranch().getBranchCode(),
            l_subAccount.getMainAccount().getAccountCode(),
            l_strWhere,
            l_objValues);

        //���莞��z���t�����ύX�e�[�u���̃��R�[�h���擾����B
        //SONAR���f�O�̃��R�[�h�Ɣ��f�ς݂̈ꎞ��~���R�[�h���擾����B
        //4) ��������������Ƃ��āA�ȉ��̕�������쐬����B
        //"( �����敪 in (?,?) or �ύX�敪 =�@@? ) and �K�p�J�n�N�� = ? "
        l_strWhere = " (status in (?, ?) or change_div = ?) and to_char(valid_start_date, 'yyyyMM') = ? ";

        //5) ���������l�Ƃ��āA�ȉ���Object�z����쐬����B
        //�E1�Fsonar�����M
        //�E�Q�Fsonar���M��
        //�E�S�F�ꎞ��~
        //�E����.�K�p�J�n�N��
        l_objValues = new Object[]{
            WEB3MFStatusDef.SONAR_NOT_SEND,
            WEB3MFStatusDef.SONAR_SENDED,
            WEB3ChangeDivDef.TEMP_STOP,
            WEB3DateUtility.formatDate(
                l_datValidStartDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YM)};

        //6) �莞��z���t���ʃT�[�r�X.get�莞��z���t�����ύX���X�g���R�[������B
        //[get�莞��z���t�����ύX���X�g�̈���]
        //�،���ЃR�[�h�@@�@@�F�@@����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�@@�@@�@@   �F�@@����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        //�����R�[�h�@@�@@�@@   �F�@@����.�⏕����.getMainAccount().getAccountCode()�̖߂�l
        //��������������     �F�쐬������������������
        //���������l�@@  �@@   �F�쐬�������������l
        List l_fixedBuyConditionChangeLists =
            l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_strWhere,
                l_objValues);

        //�莞��z���t�����e�[�u���̃��R�[�h�̓��e��
        //�莞��z���t�����ύX�e�[�u���̃��R�[�h�̓��e���}�[�W��
        //���M�莞��z���t�����s�̔z����쐬����B
        //7) this.merge�莞��z���t�����ύX(�������f)���R�[������B
        //[merge�莞��z���t�����ύX(�������f)�̈���]
        //�،���Ё@@�@@�@@�@@�F����.�⏕����.getInstitution()�̖߂�l
        //���X�R�[�h�@@�@@�@@�@@�F����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        //�莞��z���t�������X�g   �@@�@@�Fget�莞��z���t�������X�g�̖߂�l
        //�莞��z���t�����ύX���X�g   �Fget�莞��z���t�����ύX���X�g�̖߂�l
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            this.mergeMutualFixedBuyConditionChangeMonth(
                l_subAccount.getInstitution(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_fixedBuyConditionLists,
                l_fixedBuyConditionChangeLists);

        //���莞��z���t�����ύX�e�[�u���̃��R�[�h���擾����B
        //�i3�����\���p�̃��R�[�h�Ɩ������ڋq�̕ύX���R�[�h�j
        //8) ��������������Ƃ��āA�ȉ��̕�������쐬����B
        //"�����敪 in (?,?) and �K�p�J�n�N�� > ?"
        l_strWhere = " status in (?, ?) and to_char(valid_start_date, 'yyyyMM') > ? ";

        //9) ���������l�Ƃ��āA�ȉ���Object�z����쐬����B
        //�E1�Fsonar�����M
        //�E�Q�Fsonar���M��
        //�E����.�K�p�J�n�N��
        l_objValues = new Object[]{
            WEB3MFStatusDef.SONAR_NOT_SEND,
            WEB3MFStatusDef.SONAR_SENDED,
            WEB3DateUtility.formatDate(
                l_datValidStartDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YM)};

        //10) �莞��z���t���ʃT�[�r�X.get�莞��z���t�����ύX���X�g���R�[������B
        //[get�莞��z���t�����ύX���X�g�̈���]
        //�،���ЃR�[�h�@@�@@�F�@@����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�@@�@@�@@   �F�@@����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        //�����R�[�h�@@�@@�@@   �F�@@����.�⏕����.getMainAccount().getAccountCode()�̖߂�l
        //��������������     �F�쐬������������������
        //���������l�@@  �@@   �F�쐬�������������l
        l_fixedBuyConditionChangeLists =
            l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_strWhere,
                l_objValues);

        //11�jget�莞��z���t�����ύX���X�g�̖߂�l���O���̏ꍇ�́A
        //merge�莞��z���t�����ύX(�������f)�̖߂�l�����^�[������B
        if (l_fixedBuyConditionChangeLists.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return l_mutualFixedBuyConditionUnits;
        }

        //���쐬�������M�莞��z���t�����s�̔z���
        //3�����\���p�̃��R�[�h�Ɩ������ڋq�̕ύX���R�[�h�̓��e���}�[�W���A
        //���M�莞��z���t�����s�̔z����쐬���ĕԋp����B
        //12�jget�莞��z���t�����ύX���X�g�̖߂�l���O���łȂ��ꍇ�A�ȉ��̏������s���B
        //12-1) this.merge�莞��z���t�����ύX(���������f)���R�[������B
        // [merge�莞��z���t�����ύX(���������f)�̈���]
        //�،���Ё@@�@@�@@�@@�F����.�⏕����.getInstitution()�̖߂�l
        //���X�R�[�h�@@�@@�@@�@@�F����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        //merge�莞��z���t�����s���X�g�Fmerge�莞��z���t�����ύX�i�������f�j�̖߂�l
        //�莞��z���t�����ύX���X�g   �Fget�莞��z���t�����ύX���X�g�̖߂�l
        l_mutualFixedBuyConditionUnits =
            this.mergeMutualFixedBuyConditionChangeFutureMonth(
                l_subAccount.getInstitution(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_mutualFixedBuyConditionUnits,
                l_fixedBuyConditionChangeLists);

        //12-2) merge�莞��z���t�����ύX(���������f)�̖߂�l�����^�[������B
        log.exiting(STR_METHOD_NAME);
        return l_mutualFixedBuyConditionUnits;
    }

    /**
     * (update�莞��z���t�����ύX)<BR>
     * �莞��z���t�����ύX�e�[�u���ƒ莞��z���t�����ύX�����e�[�u����update�܂���insert����B<BR>
     * <BR>
     * ============================================<BR>
     * ���莞��z���t�����ύX�e�[�u������������B<BR>
     * ============================================<BR>
     * 1) �莞��z���t�����ύX�e�[�u�����������A�莞��z���t�����ύXParams���擾����B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�،���ЃR�[�h�F����.�⏕���� .getInstitution().getInstitutionCode()�̖߂�l<BR>
     * �@@�@@���X�R�[�h�F����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l<BR>
     * �@@�@@�����R�[�h�F����.�⏕����.getMainAccount().getAccountCode()�̖߂�l<BR>
     * �@@�@@�����R�[�h�F�����D���M�莞��z���t���ʏ��D�����R�[�h<BR>
     * �@@�@@�K�p�J�n�N���F<BR>
     * �@@�@@�@@����.���M�莞��z���t���ʏ��D�K�p�J�n�N�� < calc�K�p�J�n�N���i���ݓ����j�i*1�j<BR>
     * �@@�@@�@@�@@�̏ꍇ�Acalc�K�p�J�n�N���i���ݓ����j�i*1�j<BR>
     * �@@�@@�@@����ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@����.���M�莞��z���t���ʏ��D�K�p�J�n�N��<BR>
     * <BR>
     * =============================================<BR>
     * ���莞��z���t�����ύX�e�[�u���Ƀ��R�[�h�����݂���ꍇ�A<BR>
     * �@@�@@�莞��z���t�����ύX�e�[�u����update����B<BR>
     * =============================================<BR>
     * 2�j��������==1���̏ꍇ�A<BR>
     * �@@�@@�@@�擾�����莞��z���t�����ύXParams�I�u�W�F�N�g����N���[�����쐬����B <BR>
     * <BR>
     * �@@�@@2)-1) �N���[�������莞��z���t�����ύXParams�ւ̃Z�b�g�B<BR>
     * �@@�@@�@@�@@[�N���[�������莞��z���t�����ύXParams�ւ̃Z�b�g]<BR>
     * �@@�@@�@@�@@�Z�b�g���e�́ADB�X�V�d�l�Q�ƁB<BR>
     * �@@�@@�@@�@@�i���M�莞��z���t���������o�^_�莞��z���t�����ύX�e�[�u��.xls<BR>
     * �@@�@@�@@�@@�@@�莞��z���t�����ύX�e�[�u��(�X�V�̏ꍇ)�V�[�g�Q��)<BR>
     * <BR>
     * �@@�@@2)-2) �莞��z���t�����ύX�e�[�u����update����B<BR>
     * <BR>
     * =============================================<BR>
     * ���莞��z���t�����ύX�e�[�u���Ƀ��R�[�h�����݂��Ȃ��ꍇ�A<BR>
     * �@@�莞��z���t�����ύX�e�[�u����insert����B<BR>
     * =============================================<BR>
     * 3�j��������==0���̏ꍇ�A�莞��z���t�����ύXParams�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�@@3)-1) ���������莞��z���t�����ύXParams�ւ̃Z�b�g�B<BR>
     * �@@�@@�@@�@@[�莞��z���t�����ύXParams�ւ̃Z�b�g]<BR>
     * �@@�@@�@@�@@�Z�b�g���e�́ADB�X�V�d�l�Q�ƁB<BR>
     * �@@�@@�@@�@@�i���M�莞��z���t���������o�^_�莞��z���t�����ύX�e�[�u��.xls<BR>
     * �@@�@@�@@�@@�@@�莞��z���t�����ύX�e�[�u��(�}���̏ꍇ)�V�[�g�Q��)<BR>
     * <BR>
     * �@@�@@3)-2) �莞��z���t�����ύX�e�[�u����insert����B<BR>
     * <BR>
     * ==============================================<BR>
     * ���莞��z���t�����ύX�����e�[�u����insert����B<BR>
     * ==============================================<BR>
     * 4�j�莞��z���t�����ύX����Params�C���X�^���X�𐶐�����B<BR>
     * �@@�@@[�莞��z���t�����ύX����Params�ւ̃Z�b�g]<BR>
     * �@@�@@�@@�@@�Z�b�g���e�́ADB�X�V�d�l�Q�ƁB<BR>
     * �@@�@@�@@�@@�i���M�莞��z���t���������o�^_�莞��z���t�����ύX�����e�[�u��.xls�j<BR>
     * <BR>
     * 5) �莞��z���t�����ύX�����e�[�u����insert����B<BR>
     * <BR>
     * �i*1�j�����D�K�p�J�n�N��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_trader - (����)<BR>
     * ����<BR>
     * @@param l_mutualFixedBuyCommonUnit - (���M�莞��z���t���ʏ��)<BR>
     * ���M�莞��z���t���ʏ��<BR>
     * @@param l_mutualFixedBuyConditionUnit - (���M�莞��z���t�����s)<BR>
     * ���M�莞��z���t�����s<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@param l_datValidStartDate - (�K�p�J�n�N��)<BR>
     * �K�p�J�n�N��<BR>
     * @@param l_tsPrizeClosingDate - (�ܗ^���ؓ���)<BR>
     * �ܗ^���ؓ���<BR>
     * @@param l_tsCommonClosingDate - (�ʏ���ؓ���(WEB3))<BR>
     * �ʏ���ؓ���(WEB3)<BR>
     * @@roseuid 486DF9E7010E
     * @@throws WEB3BaseException
     */
    protected void updateMutualFixedBuyConditionChange(
        SubAccount l_subAccount,
        Trader l_trader,
        WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit,
        WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit,
        Date l_datBizDate,
        Date l_datValidStartDate,
        Timestamp l_tsPrizeClosingDate,
        Timestamp l_tsCommonClosingDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateMutualFixedBuyConditionChange("
            + "SubAccount, Trader,"
            + " WEB3MutualFixedBuyCommonUnit, WEB3MutualFixedBuyConditionUnit,"
            + " Date, Date, Timestamp, Timestamp)";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFixedBuyCommonUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //���莞��z���t�����ύX�e�[�u������������B
        //1) �莞��z���t�����ύX�e�[�u�����������A�莞��z���t�����ύXParams���擾����B
        //�،���ЃR�[�h�F����.�⏕���� .getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�F����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        //�����R�[�h�F����.�⏕����.getMainAccount().getAccountCode()�̖߂�l
        //�����R�[�h�F�����D���M�莞��z���t���ʏ��D�����R�[�h
        //�K�p�J�n�N���F����.���M�莞��z���t���ʏ��D�K�p�J�n�N�� < calc�K�p�J�n�N���i���ݓ����j�̏ꍇ�A
        //calc�K�p�J�n�N���i���ݓ����j
        //����ȊO�̏ꍇ�A����.���M�莞��z���t���ʏ��D�K�p�J�n�N��
        Timestamp l_tsValidStartDate = null;
        if (WEB3DateUtility.compareToMonth(
            l_mutualFixedBuyCommonUnit.validStartDate,
            l_datValidStartDate) < 0)
        {
            l_tsValidStartDate = new Timestamp(
                l_datValidStartDate.getTime());
        }
        else
        {
            l_tsValidStartDate = new Timestamp(
                l_mutualFixedBuyCommonUnit.validStartDate.getTime());
        }

        List l_lisMfFixedBuyingChangeRows = null;
        String l_strMfFixedBuyingChangeQuery =
            " institution_code = ? and branch_code = ? and account_code = ? "
            + " and product_code = ? and to_char(valid_start_date, 'yyyyMM') = ? ";
        Object[] l_objMfFixedBuyingChangeQuerys = new Object[]{
            l_subAccount.getInstitution().getInstitutionCode(),
            l_subAccount.getMainAccount().getBranch().getBranchCode(),
            l_subAccount.getMainAccount().getAccountCode(),
            l_mutualFixedBuyCommonUnit.mutualProductCode,
            WEB3DateUtility.formatDate(
                l_tsValidStartDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YM)};

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisMfFixedBuyingChangeRows = l_queryProcessor.doFindAllQuery(
                MfFixedBuyingChangeRow.TYPE,
                l_strMfFixedBuyingChangeQuery,
                l_objMfFixedBuyingChangeQuerys);

            //2�j��������==1���̏ꍇ�A�擾�����莞��z���t�����ύXParams�I�u�W�F�N�g����N���[�����쐬����B
            if (l_lisMfFixedBuyingChangeRows.size() == 1)
            {
                //2)-1) �N���[�������莞��z���t�����ύXParams�ւ̃Z�b�g�B
                //[�N���[�������莞��z���t�����ύXParams�ւ̃Z�b�g]
                //�Z�b�g���e�́ADB�X�V�d�l�Q�ƁB
                //���M�莞��z���t���������o�^_�莞��z���t�����ύX�e�[�u��.xls
                //�@@�莞��z���t�����ύX�e�[�u��(�X�V�̏ꍇ)�V�[�g�Q��)
                MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
                    (MfFixedBuyingChangeRow)l_lisMfFixedBuyingChangeRows.get(0);
                l_mfFixedBuyingChangeParams =
                    new MfFixedBuyingChangeParams(
                        l_mfFixedBuyingChangeRow);

                //���t���z�i���X�j
                //����.���M�莞��z���t���ʏ��D�ύX�敪 == �u2:�ύX�v�̏ꍇ
                //���� ����.���M�莞��z���t���ʏ��D���t���z�i���X�j== null�̏ꍇ�A
                //����.���M�莞��z���t�����s�D���t���z(���X)���Z�b�g
                //��L�ȊO
                //����.���M�莞��z���t���ʏ��D���t���z�i���X�j���Z�b�g
                if (WEB3ChangeDivDef.CHANGE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    && l_mutualFixedBuyCommonUnit.monthlyBuyAmount == null)
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyConditionUnit.monthlyBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyConditionUnit.monthlyBuyAmount));
                    }
                    else
                    {
                        l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(null);
                    }
                }
                else
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyCommonUnit.monthlyBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyCommonUnit.monthlyBuyAmount));
                    }
                    else
                    {
                        l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(null);
                    }
                }

                //���t���z�i�ςݑ����j
                //����.���M�莞��z���t���ʏ��D�ύX�敪 == �u1:�ǉ��v�̏ꍇ
                //���� ����.���M�莞��z���t���ʏ��D���t���z�i�ςݑ����j == null�̏ꍇ�A0���Z�b�g
                //����.���M�莞��z���t���ʏ��D�ύX�敪 == �u2:�ύX�v�̏ꍇ ����
                //����.���M�莞��z���t���ʏ��D���t���z�i�ςݑ����j == null�̏ꍇ�A
                //����.���M�莞��z���t�����s�D���t���z(�ςݑ���)���Z�b�g
                //��L�ȊO
                //����.���M�莞��z���t���ʏ��D���t���z�i�ςݑ����j���Z�b�g
                if (WEB3ChangeDivDef.ADD.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    && l_mutualFixedBuyCommonUnit.increaseBuyAmount == null)
                {
                    l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(0);
                }
                else if (WEB3ChangeDivDef.CHANGE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    && l_mutualFixedBuyCommonUnit.increaseBuyAmount == null)
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyConditionUnit.increaseBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyConditionUnit.increaseBuyAmount));
                    }
                    else
                    {
                        l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(null);
                    }
                }
                else
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyCommonUnit.increaseBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyCommonUnit.increaseBuyAmount));
                    }
                    else
                    {
                        l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(null);
                    }
                }

                //�m��������t���z�i�ςݑ����j
                //�擾�����莞��z���t�����ύXParams.�m��������t���z�i�ܗ^�j��null�̏ꍇ
                if (l_mfFixedBuyingChangeParams.getFinDrawIncreaseBuyAmountIsNull())
                {
                    //�莞��z���t���ʃT�[�r�X.is�ܗ^��(*1) = true
                    //���� ����.�ܗ^���ؓ��� < ���ݓ���(*2) <= �����D�ʏ���ؓ���(WEB)
                    //(*1)[is�ܗ^���̈���]
                    //�،���ЃR�[�h�F�擾�����莞��z���t�����ύXParams�Dget�،���ЃR�[�h
                    //�K�p�J�n�N���F����.�K�p�J�n�N��
                    //(*2)GtlUtils.getTradingSystem().getSystemTimestamp()�̒l
                    //��Q�Ǘ��[3107
                    if (l_mutualFixedBuyCommonService.isPrizeAndMonth(
                        l_mfFixedBuyingChangeParams.getInstitutionCode(),
                        l_datValidStartDate)
                        && (WEB3DateUtility.compareToSecond(l_tsPrizeClosingDate,
                            GtlUtils.getTradingSystem().getSystemTimestamp()) < 0)
                        && (WEB3DateUtility.compareToSecond(l_tsCommonClosingDate,
                            GtlUtils.getTradingSystem().getSystemTimestamp()) >= 0))
                    {
                        //����.���M�莞��z���t���ʏ��D�ύX�敪���ǉ��̏ꍇ�A0
                        if (WEB3ChangeDivDef.ADD.equals(
                            l_mutualFixedBuyCommonUnit.changeDiv))
                        {
                            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(0);
                        }
                        else
                        {
                            if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyConditionUnit.increaseBuyAmount))
                            {
                                //����.���M�莞��z���t���ʏ��D�ύX�敪���ύX�E�����E�ꎞ��~�̏ꍇ�A
                                //����.���M�莞��z���t�����s.���t���z(�ςݑ���)
                                if(WEB3ChangeDivDef.CHANGE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                                    || WEB3ChangeDivDef.RELEASE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                                    || WEB3ChangeDivDef.TEMP_STOP.equals(l_mutualFixedBuyCommonUnit.changeDiv))
                                {
                                    l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(
                                        Double.parseDouble(
                                            l_mutualFixedBuyConditionUnit.increaseBuyAmount));
                                }
                            }
                            else
                            {
                                l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
                            }
                        }
                    }
                }

                //�ύX�敪:����.���M�莞��z���t���ʏ��D�ύX�敪
                l_mfFixedBuyingChangeParams.setChangeDiv(
                    l_mutualFixedBuyCommonUnit.changeDiv);

                //�����敪
                //�u1�Fsonar�����M�v
                l_mfFixedBuyingChangeParams.setStatus(
                    WEB3MFStatusDef.SONAR_NOT_SEND);

                //������:�����D������
                l_mfFixedBuyingChangeParams.setBizDate(l_datBizDate);

                //�\������:GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
                l_mfFixedBuyingChangeParams.setOrderTimestamp(
                    GtlUtils.getTradingSystem().getSystemTimestamp());

                //�`���l��
                //�Z�b�V�������擾���������`���l��
                OpLoginSecurityService l_opLoginSec =
                    (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                String l_strOrderChanel =
                    l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);
                l_mfFixedBuyingChangeParams.setOrderChanel(l_strOrderChanel);

                //�폜�t���O
                //����.���M�莞��z���t���ʏ��D�ύX�敪���u1�F�ǉ��v�܂��́u2�F�ύX�v�܂��́u4�F�ꎞ��~�v�̏ꍇ�A
                //�u0�FFALSE�v
                //����.���M�莞��z���t���ʏ��D�ύX�敪���u3�F�����v����
                //�N���[�����ꂽ�莞��z���t�����ύXParams.get�����敪���u4�Fsonar���M�ΏۊO�v����
                //�N���[�����ꂽ�莞��z���t�����ύXParams.get�m��������z�i�ܗ^�j��null�̏ꍇ
                //�u1:TRUE�v
                //��L�ȊO
                //�u0�FFALSE�v
                if (WEB3ChangeDivDef.ADD.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    || WEB3ChangeDivDef.CHANGE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    || WEB3ChangeDivDef.TEMP_STOP.equals(l_mutualFixedBuyCommonUnit.changeDiv))
                {
                    l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
                }
                else if (WEB3ChangeDivDef.RELEASE.equals(l_mutualFixedBuyCommonUnit.changeDiv))
                {
                    //��Q3106
                    if (WEB3MFStatusDef.SONAR_SEND_EXCEPT_OBJECT.equals(
                        l_mfFixedBuyingChangeParams.getStatus())
                        && l_mfFixedBuyingChangeParams.getFinDrawIncreaseBuyAmountIsNull())
                    {
                        l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.TRUE);
                    }
                    else
                    {
                        l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
                    }
                }

                //�X�V�҃R�[�h
                //�ڋq���͂̏ꍇ.����.�⏕�����I�u�W�F�N�g .�����R�[�h���Z�b�g����B
                //�㗝���͂̏ꍇ.����.����.���҃R�[�h���Z�b�g����B
                if (l_trader != null)
                {
                    l_mfFixedBuyingChangeParams.setLastUpdater(
                        l_trader.getTraderCode());
                }
                else
                {
                    l_mfFixedBuyingChangeParams.setLastUpdater(
                        l_subAccount.getMainAccount().getAccountCode());
                }

                //�쐬���tGtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
                l_mfFixedBuyingChangeParams.setCreatedTimestamp(
                    GtlUtils.getTradingSystem().getSystemTimestamp());

                //�X�V���tGtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
                l_mfFixedBuyingChangeParams.setLastUpdatedTimestamp(
                    GtlUtils.getTradingSystem().getSystemTimestamp());

                //2)-2) �莞��z���t�����ύX�e�[�u����update����B
                l_queryProcessor.doUpdateQuery(l_mfFixedBuyingChangeParams);
            }
            else if (l_lisMfFixedBuyingChangeRows.size() == 0)
            {
                //3�j��������==0���̏ꍇ�A�莞��z���t�����ύXParams�C���X�^���X�𐶐�����B
                l_mfFixedBuyingChangeParams =
                    new MfFixedBuyingChangeParams();

                //�،���ЃR�[�h
                //����.�⏕���� .getInstitution().getInstitutionCode()�̖߂�l
                l_mfFixedBuyingChangeParams.setInstitutionCode(
                    l_subAccount.getInstitution().getInstitutionCode());

                //���X�R�[�h
                //����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
                l_mfFixedBuyingChangeParams.setBranchCode(
                    l_subAccount.getMainAccount().getBranch().getBranchCode());

                //�����R�[�h
                //����.�⏕����.getMainAccount().getAccountCode()�̖߂�l
                l_mfFixedBuyingChangeParams.setAccountCode(
                    l_subAccount.getMainAccount().getAccountCode());

                //�����R�[�h
                //����.���M�莞��z���t���ʏ��D�����R�[�h
                l_mfFixedBuyingChangeParams.setProductCode(
                    l_mutualFixedBuyCommonUnit.mutualProductCode);

                //�K�p�J�n�N��
                //����.���M�莞��z���t���ʏ��D�K�p�N���� < calc�K�p�J�n�N���i���ݓ����j(*3)�̏ꍇ�A
                //calc�K�p�J�n�N���i���ݓ����j���Z�b�g
                //����ȊO�̏ꍇ�A
                //����.���M�莞��z���t���ʏ��D�K�p�N����
                //(*3)�����D�K�p�J�n�N��
                if (WEB3DateUtility.compareToMonth(
                    l_mutualFixedBuyCommonUnit.validStartDate,
                    l_datValidStartDate) < 0)
                {
                    l_mfFixedBuyingChangeParams.setValidStartDate(
                        l_datValidStartDate);
                }
                else
                {
                    l_mfFixedBuyingChangeParams.setValidStartDate(
                        l_mutualFixedBuyCommonUnit.validStartDate);
                }

                //���t���z�i���X�j
                //����.���M�莞��z���t���ʏ��D�ύX�敪 == �u2:�ύX�v�̏ꍇ
                //���� ����.���M�莞��z���t���ʏ��D���t���z�i���X�j== null�̏ꍇ�A
                //����.���M�莞��z���t�����s�D���t���z(���X)���Z�b�g
                //��L�ȊO
                //����.���M�莞��z���t���ʏ��D���t���z�i���X�j���Z�b�g
                if (WEB3ChangeDivDef.CHANGE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    && l_mutualFixedBuyCommonUnit.monthlyBuyAmount == null)
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyConditionUnit.monthlyBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyConditionUnit.monthlyBuyAmount));
                    }
                }
                else
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyCommonUnit.monthlyBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyCommonUnit.monthlyBuyAmount));
                    }
                }

                //���t���z�i�ςݑ����j
                //����.���M�莞��z���t���ʏ��D�ύX�敪 == �u1:�ǉ��v�̏ꍇ
                //���� ����.���M�莞��z���t���ʏ��D���t���z�i�ςݑ����j == null�̏ꍇ�A0���Z�b�g
                //����.���M�莞��z���t���ʏ��D�ύX�敪 == �u2:�ύX�v�̏ꍇ ����
                //����.���M�莞��z���t���ʏ��D���t���z�i�ςݑ����j == null�̏ꍇ�A
                //����.���M�莞��z���t�����s�D���t���z(�ςݑ���)���Z�b�g
                //��L�ȊO
                //����.���M�莞��z���t���ʏ��D���t���z�i�ςݑ����j���Z�b�g
                if (WEB3ChangeDivDef.ADD.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    && l_mutualFixedBuyCommonUnit.increaseBuyAmount == null)
                {
                    l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(0);
                }
                else if (WEB3ChangeDivDef.CHANGE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    && l_mutualFixedBuyCommonUnit.increaseBuyAmount == null)
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyConditionUnit.increaseBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyConditionUnit.increaseBuyAmount));
                    }
                }
                else
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyCommonUnit.increaseBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyCommonUnit.increaseBuyAmount));
                    }
                }

                //�m��������t���z�i�ςݑ����j
                //�莞��z���t���ʃT�[�r�X.is�ܗ^��(*1) = true
                //���� ����.�ܗ^���ؓ��� < ���ݓ���(*2) <= ����.�ʏ���ؓ���(WEB)
                //(*1)[is�ܗ^���̈���]
                //�،���ЃR�[�h�F�擾�����莞��z���t�����ύXParams�Dget�،���ЃR�[�h
                //�K�p�J�n�N���F����.�K�p�J�n�N��
                //(*2)GtlUtils.getTradingSystem().getSystemTimestamp()�̒l
                if (l_mutualFixedBuyCommonService.isPrizeAndMonth(
                    l_mfFixedBuyingChangeParams.getInstitutionCode(),
                    l_datValidStartDate)
                    && (WEB3DateUtility.compareToSecond(l_tsPrizeClosingDate,
                        GtlUtils.getTradingSystem().getSystemTimestamp()) < 0)
                    && (WEB3DateUtility.compareToSecond(l_tsCommonClosingDate,
                        GtlUtils.getTradingSystem().getSystemTimestamp()) >= 0))
                {
                    if (WEB3ChangeDivDef.ADD.equals(
                        l_mutualFixedBuyCommonUnit.changeDiv))
                    {
                        //����.���M�莞��z���t���ʏ��D�ύX�敪���ǉ��̏ꍇ�A0
                        l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(0);
                    }
                    else
                    {
                        if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyConditionUnit.increaseBuyAmount))
                        {
                            //����.���M�莞��z���t���ʏ��D�ύX�敪���ύX�E�����E�ꎞ��~�̏ꍇ�A
                            //����.���M�莞��z���t�����s.���t���z(�ςݑ���)
                            if(WEB3ChangeDivDef.CHANGE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                                ||WEB3ChangeDivDef.RELEASE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                                || WEB3ChangeDivDef.TEMP_STOP.equals(l_mutualFixedBuyCommonUnit.changeDiv))
                            {
                                l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(
                                    Double.parseDouble(
                                        l_mutualFixedBuyConditionUnit.increaseBuyAmount));
                            }
                        }
                        else
                        {
                            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
                        }
                    }
                }
                else
                {
                    l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
                }

                //�ύX�敪:����.���M�莞��z���t���ʏ��D�ύX�敪
                l_mfFixedBuyingChangeParams.setChangeDiv(
                    l_mutualFixedBuyCommonUnit.changeDiv);

                //�����敪
                //�u1�Fsonar�����M�v
                l_mfFixedBuyingChangeParams.setStatus(WEB3MFStatusDef.SONAR_NOT_SEND);

                //������
                //�����D������
                l_mfFixedBuyingChangeParams.setBizDate(l_datBizDate);

                //�\������:GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
                l_mfFixedBuyingChangeParams.setOrderTimestamp(
                    GtlUtils.getTradingSystem().getSystemTimestamp());

                //�`���l��
                //�Z�b�V�������擾���������`���l��
                OpLoginSecurityService l_opLoginSec =
                    (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                String l_strOrderChanel =
                    l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);
                l_mfFixedBuyingChangeParams.setOrderChanel(l_strOrderChanel);

                //�폜�t���O
                //�u0�FFALSE�v
                l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);

                //�X�V�҃R�[�h
                //�ڋq���͂̏ꍇ.����.�⏕�����I�u�W�F�N�g .�����R�[�h���Z�b�g����B
                //�㗝���͂̏ꍇ.����.����.���҃R�[�h���Z�b�g����B
                if (l_trader != null)
                {
                    l_mfFixedBuyingChangeParams.setLastUpdater(
                        l_trader.getTraderCode());
                }
                else
                {
                    l_mfFixedBuyingChangeParams.setLastUpdater(
                        l_subAccount.getMainAccount().getAccountCode());
                }

                //�쐬���tGtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
                l_mfFixedBuyingChangeParams.setCreatedTimestamp(
                    GtlUtils.getTradingSystem().getSystemTimestamp());

                //�X�V���tGtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
                l_mfFixedBuyingChangeParams.setLastUpdatedTimestamp(
                    GtlUtils.getTradingSystem().getSystemTimestamp());

                //3)-2) �莞��z���t�����ύX�e�[�u����insert����B
                WEB3DataAccessUtility.insertRow(l_mfFixedBuyingChangeParams);
            }

            //���莞��z���t�����ύX�����e�[�u����insert����B
            //�j�莞��z���t�����ύX����Params�C���X�^���X�𐶐�����B
            //[�莞��z���t�����ύX����Params�ւ̃Z�b�g]
            //�Z�b�g���e�́ADB�X�V�d�l�Q�ƁB
            //�i���M�莞��z���t���������o�^_�莞��z���t�����ύX�����e�[�u��.xls�j
            MfFixedBuyingChangeHistParams l_mfFixedBuyingChangeHistParams =
                new MfFixedBuyingChangeHistParams();

            //�،���ЃR�[�h:�擾�����莞��z���t�����ύXParams�Dget�،���ЃR�[�h
            l_mfFixedBuyingChangeHistParams.setInstitutionCode(
                l_mfFixedBuyingChangeParams.getInstitutionCode());

            //���X�R�[�h:�擾�����莞��z���t�����ύXParams�Dget���X�R�[�h
            l_mfFixedBuyingChangeHistParams.setBranchCode(
                l_mfFixedBuyingChangeParams.getBranchCode());

            //�����R�[�h:�擾�����莞��z���t�����ύXParams�Dget�����R�[�h
            l_mfFixedBuyingChangeHistParams.setAccountCode(
                l_mfFixedBuyingChangeParams.getAccountCode());

            //�����R�[�h:�擾�����莞��z���t�����ύXParams�Dget�����R�[�h
            l_mfFixedBuyingChangeHistParams.setProductCode(
                l_mfFixedBuyingChangeParams.getProductCode());

            //�K�p�J�n�N��:�擾�����莞��z���t�����ύXParams�Dget�K�p�J�n�N��
            l_mfFixedBuyingChangeHistParams.setValidStartDate(
                l_mfFixedBuyingChangeParams.getValidStartDate());

            //����ԍ�:this�Dget�ŐV����ԍ��i*1�j�̖߂�l
            //(*1)[get�ŐV����ԍ��̈���]
            //�،���ЃR�[�h�F�擾�����莞��z���t�����ύXParams�Dget�،���ЃR�[�h
            //���X�R�[�h�F�擾�����莞��z���t�����ύXParams�Dget���X�R�[�h
            //�����R�[�h�F�擾�����莞��z���t�����ύXParams�Dget�����R�[�h
            //�����R�[�h�F�擾�����莞��z���t�����ύXParams�Dget�����R�[�h
            //�K�p�J�n�N���F�擾�����莞��z���t�����ύXParams�Dget�K�p�J�n�N��
            l_mfFixedBuyingChangeHistParams.setSerialNo(
                (int)this.getLastNumber(
                    l_mfFixedBuyingChangeParams.getInstitutionCode(),
                    l_mfFixedBuyingChangeParams.getBranchCode(),
                    l_mfFixedBuyingChangeParams.getAccountCode(),
                    l_mfFixedBuyingChangeParams.getProductCode(),
                    l_mfFixedBuyingChangeParams.getValidStartDate()));

            //���t���z�i���X�j:�擾�����莞��z���t�����ύXParams�Dget���t���z�i���X�j
            if (!l_mfFixedBuyingChangeParams.getMonthlyBuyAmountIsNull())
            {
                l_mfFixedBuyingChangeHistParams.setMonthlyBuyAmount(
                    l_mfFixedBuyingChangeParams.getMonthlyBuyAmount());
            }
            else
            {
                l_mfFixedBuyingChangeHistParams.setMonthlyBuyAmount(null);
            }

            //���t���z�i�ςݑ����j:�擾�����莞��z���t�����ύXParams�Dget���t���z�i�ܗ^�j
            if (!l_mfFixedBuyingChangeParams.getIncreaseBuyAmountIsNull())
            {
                l_mfFixedBuyingChangeHistParams.setIncreaseBuyAmount(
                    l_mfFixedBuyingChangeParams.getIncreaseBuyAmount());
            }
            else
            {
                l_mfFixedBuyingChangeHistParams.setIncreaseBuyAmount(null);
            }

            //�m��������t���z�i�ςݑ����j:�擾�����莞��z���t�����ύXParams�Dget�m��������t���z�i�ܗ^�j
            if (!l_mfFixedBuyingChangeParams.getFinDrawIncreaseBuyAmountIsNull())
            {
                l_mfFixedBuyingChangeHistParams.setFinDrawIncreaseBuyAmount(
                    l_mfFixedBuyingChangeParams.getFinDrawIncreaseBuyAmount());
            }
            else
            {
                l_mfFixedBuyingChangeHistParams.setFinDrawIncreaseBuyAmount(null);
            }

            //�ύX�敪:�擾�����莞��z���t�����ύXParams�Dget�ύX�敪
            l_mfFixedBuyingChangeHistParams.setChangeDiv(
                l_mfFixedBuyingChangeParams.getChangeDiv());

            //�����敪:�擾�����莞��z���t�����ύXParams�Dget�����敪
            l_mfFixedBuyingChangeHistParams.setStatus(
                l_mfFixedBuyingChangeParams.getStatus());

            //������  :�擾�����莞��z���t�����ύXParams�Dget������
            l_mfFixedBuyingChangeHistParams.setBizDate(
                l_mfFixedBuyingChangeParams.getBizDate());

            //�\������:�擾�����莞��z���t�����ύXParams�Dget�\������
            l_mfFixedBuyingChangeHistParams.setOrderTimestamp(
                l_mfFixedBuyingChangeParams.getOrderTimestamp());

            //�`���l��:�擾�����莞��z���t�����ύXParams�Dget�`���l��
            l_mfFixedBuyingChangeHistParams.setOrderChanel(
                l_mfFixedBuyingChangeParams.getOrderChanel());

            //�폜�t���O  :�擾�����莞��z���t�����ύXParams�Dget�폜�t���O
            l_mfFixedBuyingChangeHistParams.setDeleteFlag(
                l_mfFixedBuyingChangeParams.getDeleteFlag());

            //�X�V�҃R�[�h:�擾�����莞��z���t�����ύXParams�Dget�X�V�҃R�[�h
            l_mfFixedBuyingChangeHistParams.setLastUpdater(
                l_mfFixedBuyingChangeParams.getLastUpdater());

            //�쐬���t    :�擾�����莞��z���t�����ύXParams�Dget�쐬���t
            l_mfFixedBuyingChangeHistParams.setCreatedTimestamp(
                l_mfFixedBuyingChangeParams.getCreatedTimestamp());

            //�X�V���t    :�擾�����莞��z���t�����ύXParams�Dget�X�V���t
            l_mfFixedBuyingChangeHistParams.setLastUpdatedTimestamp(
                l_mfFixedBuyingChangeParams.getLastUpdatedTimestamp());

            //5) �莞��z���t�����ύX�����e�[�u����insert����B
            WEB3DataAccessUtility.insertRow(l_mfFixedBuyingChangeHistParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�莞��z���t���z���v)<BR>
     * get�莞��z���t���z���v<BR>
     * �莞��z���t���z�̍��v���v�Z����B<BR>
     * <BR>
     * �P�D���M�莞��z���t���z���v�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * <BR>
     * �Q�D���M�莞��z���t�����s[]�̌������A�ȉ��̏������J�Ԃ��B<BR>
     * <BR>
     * �@@�Q�|�P�D���M�莞��z���t�����s.�ꎞ��~���t���O�@@==�@@false �̏ꍇ�A<BR>
     * �@@�@@�Q-�P-�P�D���M�莞��z���t���z���v.���X���v =<BR>
     * �@@�@@�@@�@@�@@�@@���M�莞��z���t���z���v.���X���v + ���M�莞��z���t�����s.���t���z�i���X�j<BR>
     * <BR>
     * �@@�@@�Q-�P-�Q�D���M�莞��z���t���z���v.�ςݑ������v =<BR>
     * �@@�@@�@@�@@�@@�@@���M�莞��z���t���z���v.�ςݑ������v + ���M�莞��z���t�����s.���t���z�i�ςݑ����j<BR>
     * <BR>
     * �@@�@@�Q-�P-�R�D���M�莞��z���t���z���v.���������N�� = ���������̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�Q-�P-�R-�P�D���M�莞��z���t���z���v.���������N�� =�@@���M�莞��z���t�����s.���������N��<BR>
     * <BR>
     * �R�D�߂�l �莞��z���t���z���v[0]�ɓ��M�莞��z���t���z���v���Z�b�g����B<BR>
     * �@@�@@�@@�i*)null�łȂ��ꍇ�A�Z�b�g����B<BR>
     * @@param l_mutualFixedBuyConditionUnits - (���M�莞��z���t�����s[])<BR>
     * ���M�莞��z���t�����s[]<BR>
     * @@return WEB3MutualFixedBuyTotalUnit[]<BR>
     * @@throws WEB3BaseException
     */
    protected WEB3MutualFixedBuyTotalUnit[] getFixedBuyTotalUnit(
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFixedBuyTotalUnit(WEB3MutualFixedBuyConditionUnit[])";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFixedBuyConditionUnits == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�D���M�莞��z���t���z���v�I�u�W�F�N�g�𐶐�����B
        WEB3MutualFixedBuyTotalUnit l_mfBuyTotalUnit = new WEB3MutualFixedBuyTotalUnit();

        //�Q�D���M�莞��z���t�����s[]�̌������A�ȉ��̏������J�Ԃ��B
        int l_intLength = l_mutualFixedBuyConditionUnits.length;
        for (int i = 0; i < l_intLength; i ++)
        {
            //�Q�|�P�D���M�莞��z���t�����s.�ꎞ��~���t���O�@@==�@@false �̏ꍇ�A

            if (!l_mutualFixedBuyConditionUnits[i].suspensionFlag)
            {
                //�Q-�P-�P�D���M�莞��z���t���z���v.���X���v =
                //���M�莞��z���t���z���v.���X���v + ���M�莞��z���t�����s.���t���z�i���X�j
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnit.monthlyBATotal))
                {
                    l_mfBuyTotalUnit.monthlyBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount = "0";
                }

                l_mfBuyTotalUnit.monthlyBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnit.monthlyBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount));

                //�Q-�P-�Q�D���M�莞��z���t���z���v.�ςݑ������v =
                //���M�莞��z���t���z���v.�ςݑ������v + ���M�莞��z���t�����s.���t���z�i�ςݑ����j
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnit.increaseBATotal))
                {
                    l_mfBuyTotalUnit.increaseBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].increaseBuyAmount = "0";
                }
                l_mfBuyTotalUnit.increaseBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnit.increaseBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount));

                //�Q-�P-�R�D���M�莞��z���t���z���v.���������N�� = ���������̏ꍇ
                //�Q-�P-�R-�P�D���M�莞��z���t���z���v.���������N�� =�@@���M�莞��z���t�����s.���������N��
                if (l_mfBuyTotalUnit.debitAccountYM == null)
                {
                    //�Q-�P-�R-�P�D���M�莞��z���t���z���v.���������N�� =
                    //�@@���M�莞��z���t�����s.���������N��
                    l_mfBuyTotalUnit.debitAccountYM = l_mutualFixedBuyConditionUnits[i].debitAccountYM;
                }
            }
        }

        //�R�D�߂�l �莞��z���t���z���v[0]�ɓ��M�莞��z���t���z���v���Z�b�g����B
        //�i*)null�łȂ��ꍇ�A�Z�b�g����B
        List l_lisMutualFixedBuyTotalUnits = new ArrayList();
        if (l_mfBuyTotalUnit.debitAccountYM != null
            && l_mfBuyTotalUnit.increaseBATotal != null
            && l_mfBuyTotalUnit.monthlyBATotal != null)
        {
            l_lisMutualFixedBuyTotalUnits.add(l_mfBuyTotalUnit);
        }
        WEB3MutualFixedBuyTotalUnit[] l_mutualFixedBuyTotalUnits =
            new WEB3MutualFixedBuyTotalUnit[l_lisMutualFixedBuyTotalUnits.size()];
        l_lisMutualFixedBuyTotalUnits.toArray(l_mutualFixedBuyTotalUnits);
        log.exiting(STR_METHOD_NAME);
        return l_mutualFixedBuyTotalUnits;
    }
}
@
