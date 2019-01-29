head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionContractInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP���ʏƉ�T�[�r�XImpl(WEB3OptionContractInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/16 ���� (���u) �V�K�쐬
              001: 2004/07/26 ���Ō� (���u)  log�̒�`��ύX �o�b�O IFO_UT-000008
                                             execute()��ύX �o�b�O IFO_UT-000009�AIFO_UT-0000010�AIFO_UT-0000011
                                                                    IFO_UT-000012�AIFO_UT-000013�AIFO_UT-000014
              002: 2004/07/30 ���Q�@@ (���u) WEB3_IFO_UT-000083��Ή����܂����B
              003: 2004/07/30 ���Q�@@ (���u) WEB3_IFO_UT-000092��Ή����܂����B
              004: 2004/08/14 ���Ō� �Ή��o�O BUG142

*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3PageIndexInfo;

import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3OptionsContractReferenceRequest;
import webbroker3.ifo.message.WEB3OptionsContractReferenceResponse;
import webbroker3.ifo.message.WEB3OptionsContractReferenceUnit;
import webbroker3.ifo.message.WEB3OptionsProductCodeComparator;
import webbroker3.ifo.message.WEB3OptionsContractDivisionComparator;
import webbroker3.ifo.message.WEB3OptionsProfitAndLossComparator;
import webbroker3.ifo.message.WEB3OptionsOpenDateComparator;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.service.delegate.WEB3OptionContractInquiryService;

/**
 * (OP���ʏƉ�T�[�r�XImpl)<BR>
 * �����w���I�v�V�������ʏƉ�T�[�r�X�����N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3OptionContractInquiryServiceImpl
    extends WEB3OptionClientRequestService
    implements WEB3OptionContractInquiryService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionContractInquiryServiceImpl.class);

    /**
     * @@roseuid 40C0BB580119
     */
    public WEB3OptionContractInquiryServiceImpl()
    {

    }

    /**
     * �����w���I�v�V�������ʏƉ�T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP���ʏƉ�T�[�r�X�j���ʏƉ�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsContractReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057F932039C
     */
//  UTBUG�iWEB3_IFO_UT-000083�j��Ή��@@START�@@20040729   ���Q
//    public WEB3OptionsContractReferenceResponse execute(WEB3OptionsContractReferenceRequest l_request)
//        throws WEB3BaseException
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
//  UTBUG�iWEB3_IFO_UT-000083�j��Ή��@@END�@@20040729   ���Q
    {
        final String STR_METHOD_NAME =
            getClass().getName() + ".execute(WEB3OptionsContractReferenceRequest)";

        log.debug(STR_METHOD_NAME + " : ENTER");
        WEB3OptionsContractReferenceRequest l_OpRequest = (WEB3OptionsContractReferenceRequest)l_request;
        FinApp l_finApp = null;
        l_finApp = (FinApp)Services.getService(FinApp.class);

        try
        {
            //2.validate()
            log.debug("2.validate() : ENTER");
            l_OpRequest.validate();
            log.debug("2.validate() : END");

            //3.get�⏕����()
            log.debug("3.get�⏕����() : ENTER");
            SubAccount l_subAccount = this.getSubAccount();
            log.debug("3.get�⏕����() : END");

            //4.�V�X�e��������~(�o�b�`���A�ً}��~��)�`�F�b�N�����{����B
            log.debug("4.�V�X�e��������~(�o�b�`���A�ً}��~��)�`�F�b�N�����{����B : ENTER");
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug("4.�V�X�e��������~(�o�b�`���A�ً}��~��)�`�F�b�N�����{����B : END");

            //5.getInstance()
            log.debug("5.getInstance() : ENTER");
            WEB3IfoOrderManagerReusableValidations l_managerReusableValidations =
                (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations
                    .getInstance();
            log.debug("5.getInstance() : END");

            //6.validate�敨OP�����J��(�⏕���� : �⏕����, �敨�^�I�v�V�����敪 : String)
            log.debug("6.validate�敨OP�����J�� : ENTER");
            //Start 2004/07/26 ���Ō�  �Ή��o�b�O IFO_UT-000009
//            l_managerReusableValidations.validateFuturesOptionAccountOpen(
//                l_subAccount,
//                WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
            l_managerReusableValidations.validateFuturesOptionAccountOpen(
                l_subAccount,
                WEB3FuturesOptionDivDef.OPTION);
            //End 2004/07/26 ���Ō�  �Ή��o�b�O IFO_UT-000009

            log.debug("6.validate�敨OP�����J�� : END");

            //7.(*1)����t���[
            //���N�G�X�g�f�[�^�ɖ����R�[�h���Z�b�g����Ă����̂݁A
            //�ȉ��̏��������{����B
            log.debug("7.(*1)����t���[ : ENTER");
            if (l_OpRequest.opProductCode != null && !l_OpRequest.opProductCode.equals(""))
            {
                //8.get����(Institution, String)
                log.debug("8.get���� : ENTER");
                //Start 2004/07/26 ���Ō�  �Ή��o�b�O IFO_UT-000010
                //WEB3IfoProductManagerImpl l_ifoProductManager = new WEB3IfoProductManagerImpl();
                WEB3IfoProductManagerImpl l_ifoProductManager = (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

                //Start 2004/07/26 ���Ō�  �Ή��o�b�O IFO_UT-000011
//                //NotFoundException
//                l_ifoProductManager.getProduct(
//                    l_subAccount.getInstitution(),
//                    l_OpRequest.opProductCode);
                l_ifoProductManager.getIfoProduct(
                    l_subAccount.getInstitution(),
                    l_OpRequest.opProductCode);
                //End 2004/07/26 ���Ō�  �Ή��o�b�O IFO_UT-000011
                log.debug("8.get���� : END");
            }

            //9.is����\�ڋq(�⏕���� : �⏕����)
            log.debug("9.is����\�ڋq : ENTER");
            //WEB3OptionOrderManagerImpl l_orderManagerImpl = new WEB3OptionOrderManagerImpl();
            WEB3OptionOrderManagerImpl l_orderManagerImpl = (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

            WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;

            boolean l_blnIsTradedPossibleCustomer =
                l_orderManagerImpl.isTradedPossibleCustomer(l_gentradeSubAccount);
            log.debug("9.is����\�ڋq : END");

            WEB3IfoPositionManagerImpl l_positionManagerImpl = (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();

            WEB3FuturesOptionsProductCodeNameUnit[] l_createProductCodeNameFromContract =
                l_positionManagerImpl.createProductCodeNameFromContract(
                    l_gentradeSubAccount,
                    false,
                    WEB3FuturesOptionDivDef.OPTION);

            //16.createResponse( )
            WEB3OptionsContractReferenceResponse l_response =
                (WEB3OptionsContractReferenceResponse)l_OpRequest.createResponse();

            //(�Y���f�[�^�`�F�b�N)
            //create�����R�[�h����from���ʂ̕Ԃ�l��NULL�̏ꍇ�A
            //���X�|���X�쐬�A�v���p�e�B�Z�b�g�������s���B
            //�i���ʏƉ�׍쐬�A�\�[�g�����A�y�[�W���O�����͍s��Ȃ��j
            if (l_createProductCodeNameFromContract == null)
            {
                log.debug("17.���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g : ENTER");
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
                //Start 2004/08/14 ���Ō� �Ή��o�O BUG142
                //l_response.accountLock = l_blnIsTradedPossibleCustomer;
                l_response.accountLock = !l_blnIsTradedPossibleCustomer;
                //End 2004/08/14 ���Ō� �Ή��o�O BUG142

                log.debug("17.���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g : END");

                log.debug("7.(*1)����t���[ : END");

                log.debug(STR_METHOD_NAME + " : END");

                return l_response;
            }

            //���ʏƉ�׍쐬
            WEB3OptionsContractReferenceUnit[] l_contractReferenceUnit = null;

            //11.create��������������(String)
            log.debug("11.create�������������� : ENTER");
            String l_createReferenceCondCharacterString =
                createQueryString(l_OpRequest.opProductCode);
            log.debug("11.create�������������� : END");

            //12.create���������f�[�^�R���e�i(String)
            //NotFoundException, WEB3BaseException
            log.debug("12.create���������f�[�^�R���e�i : ENTER");
            String[] l_createReferenceCondDataContainer =
                createQueryContainer(l_OpRequest.opProductCode);
            log.debug("12.create���������f�[�^�R���e�i : END");

            //�V�[�P���X�} (�敨OP�c��)createOP���ʏƉ��
            //13.createOP���ʏƉ��(�⏕����, String, String, String, String[])
            log.debug("13.createOP���ʏƉ�� : ENTER");
            l_contractReferenceUnit =
                l_positionManagerImpl.createOptionsContractReferenceUnits(
                    l_gentradeSubAccount,
                    WEB3FuturesOptionDivDef.OPTION,
                    l_OpRequest.settlementState,
                    l_createReferenceCondCharacterString,
                    l_createReferenceCondDataContainer);

            //l_contractReferenceUnit = new WEB3OptionsContractReferenceUnit[20];

            log.debug("13.createOP���ʏƉ�� : END");

            //(���������Y���f�[�^�`�F�b�N)
            //create���ʏƉ�ׂ̕Ԃ�l��NULL�̏ꍇ�A
            //���X�|���X�쐬�A�v���p�e�B�Z�b�g�������s���B
            //(�\�[�g�����A�y�[�W���O�����͍s��Ȃ�)
//          UTBUG�iWEB3_IFO_UT-000090�j��Ή��@@START�@@20040730   ���Q
            //if (l_contractReferenceUnit == null)
            if ((l_contractReferenceUnit == null) || (l_contractReferenceUnit.length == 0))
//          UTBUG�iWEB3_IFO_UT-000090�j��Ή��@@END�@@20040730   ���Q
            {
                log.debug("17.���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g : ENTER");
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
                l_response.futOpProductCodeNames = l_createProductCodeNameFromContract;
                //Start 2004/08/14 ���Ō� �Ή��o�O BUG142
                //l_response.accountLock = l_blnIsTradedPossibleCustomer;
                l_response.accountLock = !l_blnIsTradedPossibleCustomer;
                //End 2004/08/14 ���Ō� �Ή��o�O BUG142

                log.debug("17.���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g : END");

                log.debug("7.(*1)����t���[ : END");

                log.debug(STR_METHOD_NAME + " : END");

                return l_response;
            }

            //14.sort���ʏƉ��(�����w���I�v�V�������ʏƉ��[], �����w���敨�I�v�V�����\�[�g�L�[[])
            log.debug("14.sort���ʏƉ�� : ENTER");
            WEB3OptionsContractReferenceUnit[] l_sortContractInquiryDetails =
                sortContractInquiryDetails(l_contractReferenceUnit, l_OpRequest.futOpSortKeys);
            log.debug("14.sort���ʏƉ�� : END");

            //15.create�y�[�W(�����w���I�v�V�������ʏƉ�N�G�X�g, �����w���I�v�V�������ʏƉ��[])
            log.debug("15.create�y�[�W : ENTER");
            //Start 2004/07/26 ���Ō�  �Ή��o�b�O IFO_UT-000012
//                WEB3OptionsContractReferenceUnit[] l_createPage =
//                    createPage(l_OpRequest, l_contractReferenceUnit);
            WEB3OptionsContractReferenceUnit[] l_createPage =
                createPage(l_OpRequest, l_sortContractInquiryDetails);
            //End 2004/07/26 ���Ō�  �Ή��o�b�O IFO_UT-000012
            log.debug("15.create�y�[�W : END");

            //(*2)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
            //�Y���f�[�^�`�F�b�N�A���������Y���f�[�^�`�F�b�N���ʂɂ��ƂÂ��A�Z�b�g�����v���p�e�B���قȂ�B

            log.debug("17.���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g : ENTER");

            //(3)(1)(2)�ȊO�̏ꍇ

            //���X�|���X.���ʏƉ�� = create�y�[�W�̕Ԃ�l
            //���X�|���X.�����R�[�h�� = create���ʏƉ�ׂ̕Ԃ�l�̃T�C�Y
            //���X�|���X.���y�[�W�� = �����R�[�h�� �� ���N�G�X�g�f�[�^.�y�[�W���\���s�� ���v�Z���ʂ�1�����A�܂��͗]�肪���݂���ꍇ�ɂ͍X��1�����Z����
            //���X�|���X.�\���y�[�W�ԍ� = ���N�G�X�g�f�[�^.�v���y�[�W�ԍ�
            //�����X�|���X.�����R�[�h�� <= (���N�G�X�g�f�[�^.�y�[�W���\���s�� * (���N�G�X�g�f�[�^.�v���y�[�W�ԍ� - 1))�̏ꍇ�́A���X�|���X.���y�[�W�����Z�b�g(�ŏI�y�[�W�ԍ����Z�b�g)
            //���X�|���X.�����w���敨�I�v�V���������R�[�h���� = ��reate�����R�[�h����from���ʂ̕Ԃ�l
            //���X�|���X.�ڋq���b�N�敪 = is����\�ڋq�̕Ԃ�l��false�Ȃ�true  �����b�N�ڋq�̏ꍇ�Ftrue�A���b�N�ڋq�łȂ��ꍇ�Ffalse�ƂȂ�
            int l_intTotalRecords = l_sortContractInquiryDetails.length;
            int l_intPageSize = Integer.parseInt(l_OpRequest.pageSize);
            //int l_intTotalPages = l_intTotalRecords / l_intPageSize;

            l_response.contractReferenceUnits = l_createPage;
            l_response.totalRecords = Integer.toString(l_intTotalRecords);
            //Start 2004/07/26 ���Ō�  �Ή��o�b�O IFO_UT-000013
//            l_response.totalPages = Integer.toString(l_intTotalPages);
//            if (l_intTotalPages < 1)
//            {
//                l_response.totalPages = "1";
//            }
            if (l_intTotalRecords % l_intPageSize == 0)
            {
                l_response.totalPages = String.valueOf(l_intTotalRecords / l_intPageSize);
            }
            else
            {
                l_response.totalPages = String.valueOf(l_intTotalRecords / l_intPageSize + 1);
            }

            //End 2004/07/26 ���Ō�  �Ή��o�b�O IFO_UT-000013
          l_response.pageIndex = l_OpRequest.pageIndex;
          if (l_intTotalRecords
              <= (l_intPageSize * (Integer.parseInt(l_OpRequest.pageIndex) - 1)))
          {
              l_response.pageIndex = l_response.totalPages;
          }
            l_response.futOpProductCodeNames = l_createProductCodeNameFromContract;
            //Start 2004/08/14 ���Ō� �Ή��o�O BUG142
            //l_response.accountLock = l_blnIsTradedPossibleCustomer;
            l_response.accountLock = !l_blnIsTradedPossibleCustomer;
            //End 2004/08/14 ���Ō� �Ή��o�O BUG142

            log.debug("17.���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g : END");

            log.debug("7.(*1)����t���[ : END");

            log.debug(STR_METHOD_NAME + " : END");

            return l_response;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
//        catch (DataQueryException l_ex)
//        {
//            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
//
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                this.getClass().getName() + STR_METHOD_NAME,
//                l_ex.getMessage(),
//                l_ex);
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.error("�\�����Ȃ��V�X�e���G���[���������܂���", l_ex);
//
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
//                this.getClass().getName() + STR_METHOD_NAME,
//                l_ex.getMessage(),
//                l_ex);
//        }
    }

    /**
     * (create��������������)<BR>
     * ���N�G�X�g�f�[�^�����ƂɁA<BR>
     * ���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * (1)�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�<BR>
     * <BR>
     * (2)�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ�A<BR>
     * ����ID�w��𕶎���C���X�^���X�ɒǉ�<BR>
     * �i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@" and product_id = ?"<BR>
     * <BR>
     * (3)������C���X�^���X��ԋp<BR>
     * �i�p�����[�^.�����R�[�h��NULL�̏ꍇ�ANULL��ԋp����j<BR>
     * @@param l_strProductCode - �����R�[�h
     * @@return String
     * @@roseuid 407A1F1B006C
     */
    protected String createQueryString(String l_strProductCode)
    {
        //(1)�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�<BR>
        String l_strReturnValue;

        if (l_strProductCode != null && !"".equals(l_strProductCode))
        {
            log.debug("�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ: ENTER");

            //Start 2004/07/26 ���Ō�  �Ή��o�b�O IFO_UT-000014
            //l_strReturnValue = l_strProductCode + " and product_id =?";
            l_strReturnValue = " and product_id =?";

            //End 2004/07/26 ���Ō�  �Ή��o�b�O IFO_UT-000014
            log.debug("�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ: END");
            return l_strReturnValue;
        }
        else
        {
            return null;
        }
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���N�G�X�g�f�[�^����A<BR>
     * ���������iwhere�ȉ��w��̕�����j�̃p�����[�^���X�g���쐬����B<BR>
     * <BR>
     * (1)������z����쐬<BR>
     * <BR>
     * (2)�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ�A����ID��<BR>
     * �@@�@@������z��ɃZ�b�g�i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
     * <BR>
     * �@@�@@�@@����ID �� �敨OP�v���_�N�g�}�l�[�W��.get���� <BR>
     * (�،���ЃI�u�W�F�N�g(*1), �p�����[�^.�����R�[�h).����ID<BR>
     * <BR>
     * (*1)�،���ЃI�u�W�F�N�g�́A�⏕����.getInstitution( )�Ŏ擾���ݒ�<BR>
     * <BR>
     * (3)������z���ԋp<BR>
     * �i�p�����[�^.�����R�[�h��NULL�̏ꍇ�ANULL��ԋp����j<BR>
     * @@param l_strProductCode - �����R�[�h
     * @@return java.lang.String[]
     * @@roseuid 407A1F1B006E
     */
    protected String[] createQueryContainer(String l_strProductCode)
        throws NotFoundException, WEB3BaseException
    {
        FinApp l_finApp = null;
                l_finApp = (FinApp)Services.getService(FinApp.class);

        //(1)������z����쐬
        String[] l_strReturnValue = new String[1];

        //(2)�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ�A����ID��<BR>
        //�@@�@@������z��ɃZ�b�g�i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
        if (l_strProductCode != null && !"".equals(l_strProductCode))
        {
            log.debug("�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ: ENTER");

            String l_strProductId = null;

            //(*1)�،���ЃI�u�W�F�N�g�́A�⏕����.getInstitution( )�Ŏ擾���ݒ�
            SubAccount l_subAccount = this.getSubAccount();

            Institution l_institution = l_subAccount.getInstitution();
            //Start 2004/07/26 ���Ō�  �Ή��o�b�O IFO_UT-000009
            //WEB3IfoProductManagerImpl l_ifoProductManager = new WEB3IfoProductManagerImpl();

            WEB3IfoProductManagerImpl l_ifoProductManager = (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

            //End 2004/07/26 ���Ō�  �Ή��o�b�O IFO_UT-000009

            l_strProductId =
                Long.toString(
                    l_ifoProductManager
                        .getIfoProduct(l_institution, l_strProductCode)
                        .getProductId());

            l_strReturnValue[0] = l_strProductId;

            log.debug("�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ: END");
            return l_strReturnValue;
        }
        // (3)������z���ԋp<BR>
        // �i�p�����[�^.�����R�[�h��NULL�̏ꍇ�ANULL��ԋp����j<BR>
        else
        {
            return null;
        }
    }

    /**
     * (create�y�[�W)<BR>
     * �v���y�[�W�ԍ��ɊY������<BR>
     * �����w���I�v�V�������ʏƉ�ׂ̔z����쐬����B<BR>
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
     * (5)ArrayList.toArray�ŊY���y�[�W�ԍ��̌��ʏƉ�ׂ̔z���ԋp<BR>
     * ����<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V�������ʏƉ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@param l_contractInquiryDetails - (���ʏƉ��)<BR>
     * �����w���I�v�V�������ʏƉ�ׂ̔z��<BR>
     * (�����R�[�h�����̔z��)<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsContractReferenceUnit[]
     * @@roseuid 407B5AA7025E
     */
    protected WEB3OptionsContractReferenceUnit[] createPage(
        WEB3OptionsContractReferenceRequest l_request,
        WEB3OptionsContractReferenceUnit[] l_contractInquiryDetails)
    {

        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_contractInquiryDetails,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        return (WEB3OptionsContractReferenceUnit[])l_pageIndexInfo.getArrayReturned(WEB3OptionsContractReferenceUnit.class);
    }

    /**
     * (sort���ʏƉ��)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A<BR>
     * ���~���ɂ��ǂ��Č��ʏƉ�ׂ̃\�[�g���s���B<BR>
     * <BR>
     * (1)ArrayList���쐬<BR>
     * <BR>
     * (2)�p�����[�^.�\�[�g�L�[�̔z�񐔕�Loop����<BR>
     * �@@(2-1)�p�����[�^.�\�[�g�L�[.�L�[���ڂ��擾<BR>
     * <BR>
     * �@@(2-2)�p�����[�^.�\�[�g�L�[.����/�~�����擾<BR>
     * <BR>
     * �@@(2-3)�L�[���ڂɂ�镪�򏈗�<BR>
     * �@@�@@�L�[���ڂ������R�[�h�ł������ꍇ�A<BR>
     * �����R�[�hComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����v�ł������ꍇ�A���vComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ������ł������ꍇ�A����Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�L�[���ڂ����敪�ł������ꍇ�A���敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@(2-4)(2-3)�ɂč쐬����Comparator��ArrayList�ɒǉ�<BR>
     * <BR>
     * (3)ArrayList����Comparator�̔z����쐬<BR>
     * <BR>
     * (4)Comparator�̔z�񏇂̃\�[�g����<BR>
     * (web3-common)WEB3ArraysUtility.sort<BR>
     * (�p�����[�^.���ʏƉ�ׁAComparator[])<BR>
     * <BR>
     * (5)�\�[�g���ꂽ���ʏƉ�ׂ̔z���ԋp<BR>
     * @@param l_contractInquiryDetails - (���ʏƉ��)<BR>
     * �����w���I�v�V�������ʏƉ�ׂ̔z��<BR>
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * �����w���I�v�V�����\�[�g�L�[�̔z��<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsContractReferenceUnit[]
     * @@roseuid 408F300401C3
     */
    protected WEB3OptionsContractReferenceUnit[] sortContractInquiryDetails(
        WEB3OptionsContractReferenceUnit[] l_contractInquiryDetails,
        WEB3FuturesOptionsSortKey[] l_sortKey)
    {
        //(1)ArrayList���쐬
        List l_arrayList = new ArrayList();

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
            if (WEB3IfoKeyItemDef.PRODUCTCODE.equals(l_strKeyItem))
            {
                log.debug("�쐬���������R�[�hComparator��ArrayList�ɒǉ�: ENTER");
                WEB3OptionsProductCodeComparator l_productCodeComparator =
                    new WEB3OptionsProductCodeComparator(l_strAscDesc);
                l_arrayList.add(l_productCodeComparator); //�쐬����Comparator��ArrayList�ɒǉ�
                log.debug("�쐬���������R�[�hComparator��ArrayList�ɒǉ�: END");
            }
            //�L�[���ڂ����v�ł������ꍇ�A���vComparator�𐶐�
            else if (WEB3IfoKeyItemDef.INCOME.equals(l_strKeyItem))
            {
                log.debug("�쐬�������vComparator��ArrayList�ɒǉ�: ENTER");
                WEB3OptionsProfitAndLossComparator l_profitAndLossComparator =
                    new WEB3OptionsProfitAndLossComparator(l_strAscDesc);
                l_arrayList.add(l_profitAndLossComparator);
                log.debug("�쐬�������vComparator��ArrayList�ɒǉ�: END");
            }
            //�L�[���ڂ������ł������ꍇ�A����Comparator�𐶐�
            else if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {
                log.debug("�쐬��������Comparator��ArrayList�ɒǉ�: ENTER");
                WEB3OptionsOpenDateComparator l_openDateComparator =
                    new WEB3OptionsOpenDateComparator(l_strAscDesc);
                l_arrayList.add(l_openDateComparator);
                log.debug("�쐬��������Comparator��ArrayList�ɒǉ�: END");
            }
            //�L�[���ڂ����敪�ł������ꍇ�A���敪Comparator�𐶐�
            else if (WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(l_strKeyItem))
            {
                log.debug("�쐬�������敪Comparator��ArrayList�ɒǉ�: ENTER");
                WEB3OptionsContractDivisionComparator l_contractDivisionComparator =
                    new WEB3OptionsContractDivisionComparator(l_strAscDesc);
                l_arrayList.add(l_contractDivisionComparator);
                log.debug("�쐬�������敪Comparator��ArrayList�ɒǉ�: END");
            }
        }

        //(3)ArrayList����Comparator�̔z����쐬
        Object[] l_comparatorObjects = l_arrayList.toArray();
        int l_intcomparatorObjectsLength = l_comparatorObjects.length;
        Comparator[] l_comparatorArrayList = new Comparator[l_intcomparatorObjectsLength];
        for (int i = 0; i < l_intcomparatorObjectsLength; i++)
        {
            l_comparatorArrayList[i] = (Comparator)l_comparatorObjects[i];
        }

        //(4)Comparator�̔z�񏇂̃\�[�g����
        log.debug("Comparator�̔z�񏇂̃\�[�g����: ENTER");
        WEB3ArraysUtility.sort((Object[])l_contractInquiryDetails, l_comparatorArrayList);
        log.debug("Comparator�̔z�񏇂̃\�[�g����: END");

        return l_contractInquiryDetails;
    }

    /**
     * @@param l_request
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40C0BB5801D4
     */
//  UTBUG�iWEB3_IFO_UT-000083�j��Ή��@@START�@@20040730   ���Q
//    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
//    {
//        final String STR_METHOD_NAME = getClass().getName() + ".execute(WEB3GenRequest)";
//
//        log.entering(STR_METHOD_NAME);
//
//        WEB3GenResponse l_response = (WEB3GenResponse)l_request.createResponse();
//
//        log.exiting(STR_METHOD_NAME);
//
//        return l_response;
//    }
//  UTBUG�iWEB3_IFO_UT-000083�j��Ή��@@START�@@20040730   ���Q
}
@
