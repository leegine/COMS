head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����t�ꗗ�Ɖ�T�[�r�X�����N���X(WEB3MutualBuyListServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 ���E (���u) �V�K�쐬
Revesion History : 2004/08/23 ���� (���u) ���r���[ 
Revesion History : 2004/12/06 ������ (���u) �c�Ή�
Revesion History : 2005/10/23 ���� (���u) �t�B�f���e�B�Ή�
Revesion History : 2006/03/08 ��� (SRA) �d�l�ύX�i���f���j�F402
Revesion History : 2006/05/15 �юu��(���u) �d�l�ύX�i���f��) :411,415
Revesion History : 2006/09/19 ���� (���u) �d�l�ύX�E���f��483�A491
Revesion History : 2006/10/25  �����F (���u) ���f�� 512,513
Revesion History : 2007/02/05  ������ (���u) ���f�� 523
Revesion History : 2007/04/09  ������ (���u) ���f�� 558  ����005
Revesion History : 2007/08/30  �И��� (���u) ���f�� 570
Revesion History : 2007/09/14  �И��� (���u) ���f�� 577
Revesion History : 2007/09/25  ���{ (SRA) ���f�� 578
Revesion History : 2008/04/21  ���u�� (���u) ���f�� 594
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuyLimitDivDef;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.data.MutualFundProductCategoryRow;
import webbroker3.mf.define.WEB3MFDealDivDef;
import webbroker3.mf.define.WEB3MFEstimatedPriceCurrencyCodeDef;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3MFReferenceDivDef;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.define.WEB3RemarkDivDef;
import webbroker3.mf.define.WEBMFSortConditionDivDef;
import webbroker3.mf.message.WEB3MutualBuyListRequest;
import webbroker3.mf.message.WEB3MutualBuyListResponse;
import webbroker3.mf.message.WEB3MutualBuyProductGroup;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.service.delegate.WEB3MutualBuyListService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M�����t�ꗗ�Ɖ�T�[�r�X�����N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualBuyListServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualBuyListService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyListServiceImpl.class);
    /**
     * �����M�����t�ꗗ�Ɖ�T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���M)���t�ꗗ�Ɖ�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40AC775C0056
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualBuyListRequest l_mutualBuyListRequest = null;
        if (l_request instanceof WEB3MutualBuyListRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u���M���t�������̓��N�G�X�g�v�̏ꍇ
            l_mutualBuyListRequest = (WEB3MutualBuyListRequest) l_request;
        }
        else
        {
            log.debug(
                "the parameter of method isn't WEB3MutualBuyListRequest type");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1)�@@���N�G�X�g�f�[�^��validate����
        l_mutualBuyListRequest.validate();
        
        //���M�E�O��MMF�\���敪
        //��null�̏ꍇ�A�u0:���M�̂݁v�Ƃ���
        if (l_mutualBuyListRequest.mutualFrgnMmfDisplayDiv == null)
        {
            l_mutualBuyListRequest.mutualFrgnMmfDisplayDiv =
                WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND;
        }

        //1.2)�@@this.get�⏕����( )���R�[�����A�⏕�����I�u�W�F�N�g���擾����
        SubAccount l_subAccount = this.getSubAccount();
        
        //�ڋq�ʎ����~�����`�F�b�N
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);  
        
        //1.3)�@@FinApp.getCommonOrderValidator( )���R�[�����A�����`�F�b�N�I�u�W�F�N�g���擾����
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //1.4)get���M������( )
        Timestamp l_datBizDate = 
            new Timestamp(WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate().getTime());
        
        //1.5�j�@@�����`�F�b�N.validate����\�ڋq( )���R�[������O���Ԃ��ꂽ�ꍇ�A��O���X���[����
        WEB3GentradeMainAccount l_genMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        OrderValidationResult l_validationResult =  
            l_gentradeOrderValidator.validateAccountForTrading(
                l_genMainAccount,
                l_datBizDate);
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.6)�@@��t���ԃ`�F�b�N�E�V�X�e�������~�`�F�b�N
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // 1.7)���M�����J�e�S���[�R�[�h����������B
        //�Ŏ擾�����⏕�����I�u�W�F�N�g���،���ЃI�u�W�F�N�g���擾����
        Institution l_institution = l_subAccount.getInstitution();
        //�Ŏ擾�����،���ЃI�u�W�F�N�g���،���ЃR�[�h���擾����
        String l_institutionCode = l_institution.getInstitutionCode();
        //���M�����J�e�S���[�R�[�h����        
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        //�g�����M�����}�l�[�W��.get���M�����J�e�S���[���X�g( )���R�[������
        List l_mutualFundProductCategoryList =
            l_mutualFundProductManager.getMutualFundProductCategoryList(
                l_institutionCode);
        
        //1.8.2)���N�G�X�g�f�[�^.���M�����J�e�S���[�R�[�h != null�̏�
        //�̌��ʂ�1���ȏ㑶�݂����ꍇ�A�g�����M�����}�l�[�W��.create���M�����J�e�S���[�ꗗ( )��
        //�R�[�����āA���M�����J�e�S���[�ꗗ���쐬����
        WEB3MutualProductCategoryUnit[] l_mutualProductCategoryUnits = null;
        if (l_mutualFundProductCategoryList != null && l_mutualFundProductCategoryList.size() != 0)
        {
            //1.8.2.1)���M�����J�e�S���[�ꗗ
            l_mutualProductCategoryUnits =
                l_mutualFundProductManager.createMutualFundProductCategoryList(
                    l_mutualFundProductCategoryList);
        }
        //1.8)get���M�����J�e�S���[���X�g()�̖߂�>0���̏ꍇ�A���M�����J�e�S���[�ꗗ��null�Ƃ���        
        else
        {
            l_mutualProductCategoryUnits = null;
        }
        
        //1.9)���������ǉ�
        //������������ 
        //���������f�[�^�R���e�i�̍쐬        
        //1.8�Ŏ擾�������M�����J�e�S���[�ꗗ��null�܂���0��
        // �ȊO�̏ꍇ�A�����M���t�ꗗ�Ɖ�N�G�X�g.���M�����J�e�S���[�R�[�h�� 
        //   null�ȊO�̏ꍇ�A
        
        String[] l_strSearchCondDataContainer = null;
        if (!(l_mutualProductCategoryUnits == null || l_mutualProductCategoryUnits.length == 0)
            && l_mutualBuyListRequest.categoryCode != null)
        {
            l_strSearchCondDataContainer =
                this.createSearchCondDataContainer(
                    l_institutionCode,
                    l_mutualBuyListRequest.categoryCode);
        }
        
        //1.9 �����������ǉ���
        // create���������f�[�^�R���e�i()�̖߂�l�ɁA�ȉ��̒l��ǉ�����B 
        
		//�����N�G�X�g.�Ɖ�敪 == �h���t�ꗗ�h �̏ꍇ 
		//�g1�FWEBEBROKER�V�Ŏ�舵���h 
		//�h0�F���t�\�h
        //�h���ݓ����iyyyyMMddHHmmss�j�h 
        //�h���ݓ����iyyyyMMdd�j�h 
        //�h���ݓ����iyyyyMMddHHmmss�j�h 
        //�h���ݓ����iyyyyMMdd�j�h
        String[] l_strDivDef = null;
        //���ݓ��t�̎擾 
        //ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A���ݓ������擾����B
        Timestamp l_tmsSystemTimestamp = 
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);           
        SimpleDateFormat l_simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        if (WEB3MFReferenceDivDef.BUY_REFERENCE.equals(l_mutualBuyListRequest.referenceType))
        {            
            l_strDivDef = new String[6];
            l_strDivDef[0] = WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN;
            l_strDivDef[1] = WEB3BuyLimitDivDef.BUY_POSSIBLE;
            l_strDivDef[2] = l_simpleDateFormat.format(l_tmsSystemTimestamp);
            l_strDivDef[3] = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");
            l_strDivDef[4] = l_simpleDateFormat.format(l_tmsSystemTimestamp);
            l_strDivDef[5] = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");
        }

        //�����N�G�X�g.�Ɖ�敪 == �h�ژ_�����X�������ꗗ�h �̏ꍇ
		//�g1�FWEBEBROKER�V�Ŏ�舵���h
        //�h0�F���t�\�h
        //�h���ݓ����iyyyyMMddHHmmss�j�h
        //�h���ݓ����iyyyyMMdd�j�h
        //�h���ݓ����iyyyyMMddHHmmss�j�h
        //�h���ݓ����iyyyyMMdd�j�h
		//�h2�F�X�������̂݁h
        else if (WEB3MFReferenceDivDef.BOOK_REFERENCE.equals(l_mutualBuyListRequest.referenceType))
        {
            l_strDivDef = new String[6];
            l_strDivDef[0] = WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN;
            l_strDivDef[1] = l_simpleDateFormat.format(l_tmsSystemTimestamp);
            l_strDivDef[2] = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_strDivDef[3] = l_simpleDateFormat.format(l_tmsSystemTimestamp);
            l_strDivDef[4] = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_strDivDef[5] = WEB3SystemHandlingDivDef.MAIL_REQUEST_ONLY;
        }
        
		//�����N�G�X�g.�Ɖ�敪 == �h���t�̂݁h �̏ꍇ 
		//�E�g1�FWEBEBROKER?�Ŏ�舵���h  
		//�E�h0�F���t�\�h  
		//�E�h���ݓ����iyyyyMMddHHmmss�j�h  
		//�E�h���ݓ����iyyyyMMdd�j�h  
        else if(WEB3MFReferenceDivDef.BUY.equals(l_mutualBuyListRequest.referenceType))
        {
            l_strDivDef = new String[4];
            l_strDivDef[0] = WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN;
            l_strDivDef[1] = WEB3BuyLimitDivDef.BUY_POSSIBLE;
            l_strDivDef[2] = l_simpleDateFormat.format(l_tmsSystemTimestamp);
            l_strDivDef[3] = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");
        }
        
		//�����N�G�X�g.�Ɖ�敪 == �h��W�̂݁h �̏ꍇ 
		//�E�g1�FWEBEBROKER?�Ŏ�舵���h  
		//�E�h���ݓ����iyyyyMMddHHmmss�j�h  
		//�E�h���ݓ����iyyyyMMdd�j�h  
        else if(WEB3MFReferenceDivDef.RECRUIT.equals(l_mutualBuyListRequest.referenceType))
        {
            l_strDivDef = new String[3];
            l_strDivDef[0] = WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN;
            l_strDivDef[1] = l_simpleDateFormat.format(l_tmsSystemTimestamp);
            l_strDivDef[2] = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");
        }

        String[] l_strWhereValues = null;
        if (l_strSearchCondDataContainer != null)
        {
            l_strWhereValues =
                new String[l_strDivDef.length + l_strSearchCondDataContainer.length];
            System.arraycopy(l_strDivDef, 0, l_strWhereValues, 0, l_strDivDef.length);
            System.arraycopy(l_strSearchCondDataContainer, 0, l_strWhereValues,
                l_strDivDef.length, l_strSearchCondDataContainer.length);
        }
        else
        {
            l_strWhereValues = l_strDivDef;
        }
        
        // 1.10)�@@����������������쐬��
        String l_strWhere = null;
        
		//�@@��������������Ƃ��āA�ȉ��̕������ݒ肷��B 
		//�����N�G�X�g.�Ɖ�敪 == �h���t�ꗗ�h �̏ꍇ 
		//" �V�X�e���戵�敪=? and  
		//(  ( ���t�����敪=? and ���t�J�n���iYYYYMMDDHH24MISS�j=<?�@@
        //and ? <= ���t�I�����iYYYYMMDD�j) or 
		//( ��W�J�n���iSONAR�j�iYYYYMMDDHH24MISS�j=<?�@@
        //and ? <= ��W�I�����iSONAR�j�iYYYYMMDD�j)  )  " 
        if (WEB3MFReferenceDivDef.BUY_REFERENCE.equals(l_mutualBuyListRequest.referenceType))
        {
            l_strWhere = " system_handling_div = ? and ((buy_limit_div = ? and " + 
            	"to_char(buy_start_date, 'YYYYMMDDHH24MISS') <= ? and " + 
            	"to_char(buy_end_date, 'YYYYMMDD') >= ? ) or " + 
            	"(to_char(recruit_start_date_sonar, 'YYYYMMDDHH24MISS') <= ? and " +
            	"to_char(recruit_end_date_sonar, 'YYYYMMDD') >= ?))";
        }
        
		//�����N�G�X�g.�Ɖ�敪 == �h�ژ_�����X�������ꗗ�h �̏ꍇ
		//"( ( �V�X�e���戵�敪 = ? and
        //( ( ���t�J�n���iYYYYMMDDHH24MISS�j =< ?�@@and ? <= ���t�I�����iYYYYMMDD�j ) or
        // ( ��W�J�n���iSONAR�j�iYYYYMMDDHH24MISS�j =< ?�@@and ? <= ��W�I�����iSONAR�j�iYYYYMMDD�j ) ) ) or
        //�V�X�e���戵�敪 = ? )"
        else if (WEB3MFReferenceDivDef.BOOK_REFERENCE.equals(l_mutualBuyListRequest.referenceType))
        {
            l_strWhere = " ((system_handling_div = ? and (( " +
                "to_char(buy_start_date, 'YYYYMMDDHH24MISS') <= ? and " +
                "to_char(buy_end_date, 'YYYYMMDD') >= ? ) or " +
                "(to_char(recruit_start_date_sonar, 'YYYYMMDDHH24MISS') <= ? and " +
                "to_char(recruit_end_date_sonar, 'YYYYMMDD') >= ?))) " +
                "or system_handling_div = ?) ";
        }
        
		//�����N�G�X�g.�Ɖ�敪 == �h���t�̂݁h �̏ꍇ 
		//" �V�X�e���戵�敪=? and ���t�����敪=? and  
		//���t�J�n���iYYYYMMDDHH24MISS�j=<?�@@and ? <= ���t�I�����iYYYYMMDD�j " 
        else if (WEB3MFReferenceDivDef.BUY.equals(l_mutualBuyListRequest.referenceType))
        {
            l_strWhere = " system_handling_div = ? and buy_limit_div = ? and " + 
            	"(to_char(buy_start_date, 'YYYYMMDDHH24MISS') <= ? and " + 
            	"to_char(buy_end_date, 'YYYYMMDD') >= ?)";
        }
        
		//�����N�G�X�g.�Ɖ�敪 == �h��W�̂݁h �̏ꍇ 
		//" �V�X�e���戵�敪=? and ��W�J�n���iSONAR�j�iYYYYMMDDHH24MISS�j=<?�@@" +
		//"and ? <= ��W�I�����iSONAR�j�iYYYYMMDD�j " 
        else if (WEB3MFReferenceDivDef.RECRUIT.equals(l_mutualBuyListRequest.referenceType))
        {
            l_strWhere = " system_handling_div = ? and " + 
            	"(to_char(recruit_start_date_sonar, 'YYYYMMDDHH24MISS') <= ? and " + 
            	"to_char(recruit_end_date_sonar, 'YYYYMMDD') >= ?)";
        }
       
        //�Acreate���M�����J�e�S���[�ꗗ()�̖߂�l�� null �܂��� 0���ȊO�̏ꍇ�ł���A���� 
        //���N�G�X�g�f�[�^.���M�����J�e�S���[�R�[�h�� null �ȊO�̏ꍇ�A  
        //   ������u" and �J�e�S���[�R�[�h in ("�v�Ɓu")"�v�̊ԂɁA
        //create���M�����J�e�S���[�ꗗ()�̌��ʂ̗v�f�����A","��؂��"?"��ǉ����A 
        //�@@��������������ɒǉ�����B
        if (!(l_mutualProductCategoryUnits == null || l_mutualProductCategoryUnits.length == 0)
            && l_mutualBuyListRequest.categoryCode != null)
        {
            l_strWhere += "and category_code in ( ";
            for (int i = 0; i < l_strSearchCondDataContainer.length; i ++)
            {
                if (i < l_strSearchCondDataContainer.length - 1)
                {
                    l_strWhere += " ?, ";
                }
                else
                {
                    l_strWhere += " ? ) ";
                }
            }
        }   

        //�����N�G�X�g.���M�E�O��MMF�\���敪  !=  "����"�̏ꍇ��
        if (!WEB3MutualFrgnMmfDisplayDivDef.BOTH.equals(
            l_mutualBuyListRequest.mutualFrgnMmfDisplayDiv))
        {
            //���������E���������������ǉ�����
            //1�jcreate���������f�[�^�R���e�i()�̖߂�l�ɁA�ȉ��̒l��ǉ�����B
            //�E���M�^�C�v.�O��MMF
            l_strDivDef = new String[1];
            l_strDivDef[0] = MutualFundTypeEnum.FOREIGN_MMF.intValue() + "";
            String[] l_strValues = l_strWhereValues;
            l_strWhereValues =
                new String[l_strValues.length + l_strDivDef.length];
            System.arraycopy(l_strValues, 0, l_strWhereValues, 0, l_strValues.length);
            System.arraycopy(l_strDivDef, 0, l_strWhereValues,
                l_strValues.length, l_strDivDef.length);

            //2)���������������ǉ�����B
            //    2-1�j���N�G�X�g.���M�E�O��MMF�\���敪��"���M�̂�"�̏ꍇ�A"and ���M�^�C�v<>?"
            //    2-2�j���N�G�X�g.���M�E�O��MMF�\���敪��"�O��MMF�̂�"�̏ꍇ�A"and ���M�^�C�v=?"
            if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(
                l_mutualBuyListRequest.mutualFrgnMmfDisplayDiv))
            {
                l_strWhere += "and fund_type <> ?";
            }
            else if (WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(
                l_mutualBuyListRequest.mutualFrgnMmfDisplayDiv))
            {
                l_strWhere += "and fund_type = ?";
            }
        }

        //1.11)�ō쐬�����������������ɁA�g�����M�����}�l�[�W��.
        //get���M�������X�g( )���R�[�����A�������ʂ��擾����B
        List l_mutualFundProductList =
            l_mutualFundProductManager.getMutualFundProductList(
                l_institutionCode,
                l_strWhere,
                l_strWhereValues,
                WEBMFSortConditionDivDef.MUTUAL_BUY_LIST);
        //1.12)�@@�������ʂ�0���������ꍇ�A"�Y���f�[�^����"�Ƃ��ė�O���X���[����B
        //get���M�������X�g()�̖߂�l�̌������J��Ԃ��A���M���t�����ꗗ�s�I�u�W�F�N�g�̔z����쐬����
        if (l_mutualFundProductList == null || l_mutualFundProductList.size() == 0)
        {
            log.debug("�������ʂ�0���������ꍇ�A�Y���f�[�^�����Ƃ��ė�O���X���[����");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y���f�[�^����");
        }
        int l_intRecords = l_mutualFundProductList.size();
        //���ׂ̐���
        //������ԃR���e�L�X�g�X�V
        List l_lisMutualBuyProductGroup = new Vector();
        for (int j = 0; j < l_intRecords; j++)
        {
            MutualFundProductParams l_mutualFundProductParams =
                (MutualFundProductParams) l_mutualFundProductList.get(j);
            //1.12.1)�@@�g�����M�����}�l�[�W��.to���M����( )���R�[������    
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct) l_mutualFundProductManager.toProduct(
                    l_mutualFundProductParams);
            //1.12.2)�@@���M������ԊǗ�.reset�����R�[�h( )���R�[������
            WEB3MutualFundTradingTimeManagement.resetProductCode(
                l_mutualFundProduct.getProductCode());
            //1.12.3)�@@������ԊǗ�.setTimestamp()���R�[������
            WEB3GentradeTradingTimeManagement.setTimestamp();
            //1.12.4)�@@�g�����M��������̎擾
            try
            {
                l_mutualFundProductManager.getMutualFundTradedProduct(
                        l_institution,
                        l_mutualFundProduct.getProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("no found WEB3MutualFundTradedProduct ");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00372,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //1.12.5)�@@���M���t�����ꗗ�s�N���X�𐶐�����
            WEB3MutualBuyProductGroup l_mutualBuyProductGroup = 
                new WEB3MutualBuyProductGroup();
            
            //1.12.6)���l�̐ݒ�
            String l_strNoteType = null;
            //1.12.6.1 is��W�\(Date)
            boolean l_blnIsRecruitPossible = 
                l_mutualFundProduct.isRecruitPossible(l_datBizDate);
            
            //1.12.6.2 if is��W�\() == true
            if(l_blnIsRecruitPossible)
            {
                log.debug("���l = ��W���Ԓ����Z�b�g");
                l_strNoteType = WEB3RemarkDivDef.RECRUIT_BETWEEN;
            }
            //1.12.6.3 if is��W�\() == false
            else
            {
                //1.12.6.3.1 is���t�\()
                // ���t��~���`�F�b�N 
                //���M����.is���t�\( ) = false�̏ꍇ�A
                //���M���t�����ꗗ�s�I�u�W�F�N�g.���l"���t��~��"���Z�b�g�B
               
                if (!l_mutualFundProduct.isAcquiredPossible(l_datBizDate))
                {
                    log.debug("���l = ����s��(���t��~��)���Z�b�g");
                    l_strNoteType = WEB3RemarkDivDef.HANDLING_DISABLE;
                }
            }

            // 1.12.6.4) ������ԊO�`�F�b�N
            //���M������ԊǗ�.validate������t�\( )���R�[�����A
            //��O���X���[���ꂽ�ꍇ"������ԊO������~��"���Z�b�g
            try
            {
                WEB3MutualFundTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BaseException l_ex)
            {
                //1.12.6.5 validate������t�\()�����O���Ԃ��ꂽ�ꍇ
                //���g��t���ԊO�h�̃Z�b�g��
                log.debug("���l = ������ԊO������~���i��t���ԊO�j���Z�b�g");
                l_strNoteType = WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
            }
            
            //1.12.6.6) �ً}��~���`�F�b�N
            WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
                (WEB3MutualFundOrderManagerReusableValidationsCheck)
                    MutualFundProductTypeOrderManagerReusableValidations.getInstance();
            try
            {
				//�����敪�F 
				//(*)is��W�\()==true�̏ꍇ�́A�h��W�h���w�� 
				//����ȊO�́h���t�h���w�� 
                if (l_blnIsRecruitPossible)
                {
                    l_validationsCheck.validateEmergencyStop(l_mutualFundProduct,
                        WEB3ProcessDivDef.RECRUIT); 
                }
                else
                {
                    l_validationsCheck.validateEmergencyStop(l_mutualFundProduct,
                        WEB3ProcessDivDef.BUY);
                }
            }
            catch(WEB3BaseException l_ex)
            {
                //1.12.6.7 validate�ً}��~()�����O���Ԃ��ꂽ�ꍇ
                //���h�ً}��~���h�̃Z�b�g��
                log.debug("���l = �ً}��~�����Z�b�g");
                l_strNoteType = WEB3RemarkDivDef.EMERGENCY_STOP;
            }
            
            //1.12.6.8is��W�\(SONAR)
            boolean l_blnIsRecruitPossibleSonar = 
                l_mutualFundProduct.isRecruitPossibleSonar();

            l_mutualBuyProductGroup.noteType = l_strNoteType;
            
            //1.12.6.9 if validate����\�ڋq()�ŕ�W���OK�A
            if (l_validationResult.getProcessingResult().isSuccessfulResult())  
            {
                //���Ais��W�\(SONAR) == true
        		if ( l_blnIsRecruitPossibleSonar)
                {
                    //1.12.6.9.1)�@@�擾�����g�����M�����I�u�W�F�N�g�����ɁA 
                    //�����������M���t�����ꗗ�s�I�u�W�F�N�g�Ɉȉ��̃v���p�e�B���Z�b�g����B 
                    //����������A�ȉ��̒l�𓊐M���t�����ꗗ�s�ɃZ�b�g����B
                   
                    //ID                         = ���M����.getProductId( )
                    l_mutualBuyProductGroup.id =
                        Long.toString(l_mutualFundProductParams.getProductId());
                    //�����R�[�h                   = ���M����.getProductCode( ) 
                    l_mutualBuyProductGroup.mutualProductCode = 
                        l_mutualFundProductParams.getProductCode();
                    //�������@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ = ���M����.get������( )
                    l_mutualBuyProductGroup.mutualProductName =
                        l_mutualFundProductParams.getStandardName();
                    //���M�����J�e�S���[�R�[�h  = ���M����.get�J�e�S���[�R�[�h( )
                    l_mutualBuyProductGroup.categoryCode =
                        l_mutualFundProductParams.getCategoryCode();
                    //���t����z�ʉ݃R�[�h = ���M����.get�ʉ݃R�[�h( )
                    l_mutualBuyProductGroup.constantValueCurrencyCode =
                        l_mutualFundProductParams.getCurrencyCode();
                    //���t����z�@@�@@�@@�@@�@@�@@  = ���M����.get��W���z( ) 
                    if (MutualFundTypeEnum.FOREIGN_MMF.equals(l_mutualFundProductParams.getFundType())
                        && l_mutualFundProductParams.getRecruitConstantValueIsNull())
                    {//�Ō��[�w�E����( 2007/3/5  �Ō��[_���t�ꗗ�i����n�j)
                        l_mutualBuyProductGroup.constantValue = null;
                    }
                    else
                    {
                        l_mutualBuyProductGroup.constantValue =
                            WEB3StringTypeUtility.formatNumber(
                                l_mutualFundProduct.getRecruitConstantValue());
                    }

                    //���t����z�K�p���@@�@@ = null;
                    l_mutualBuyProductGroup.constantValueAppDate = null;
   				  	//�V�K���t���P�ʌ����@@�@@ = ���M����.get�P�ʌ���(��W)( ) 
                    if (!l_mutualFundProductParams.getRecruitUnitQtyIsNull())
                    {
                        l_mutualBuyProductGroup.newBuyUnitQty = 
                            l_mutualFundProductParams.getRecruitUnitQty() + "";
                    }
   					//�V�K���t���Œ�����@@�@@ = ���M����.get�Œ����(��W)( ) 
                    if(!l_mutualFundProductParams.getRecruitMinQtyIsNull())
                    {
                        l_mutualBuyProductGroup.newBuyMinQty = 
                            l_mutualFundProductParams.getRecruitMinQty() + "";
                    }
   					//�V�K���t���P�ʋ��z�@@�@@ = ���M����.get�P�ʋ��z(��W)( ) 
                    if(!l_mutualFundProductParams.getRecruitUnitAmtIsNull())
                    {
                        l_mutualBuyProductGroup.newBuyUnitAmt = 
                            l_mutualFundProductParams.getRecruitUnitAmt() + "";
                    }
   					//�V�K���t���Œ���z�@@�@@ = ���M����.get�Œ���z(��W)( ) 
                    if(!l_mutualFundProductParams.getRecruitMinAmtIsNull())
                    {
                        l_mutualBuyProductGroup.newBuyMinAmt = 
                            l_mutualFundProductParams.getRecruitMinAmt() + "";
                    }
                    //�ǉ����t���P�ʌ����@@�@@ = null
                    l_mutualBuyProductGroup.addBuyUnitQty = null;
                    //�ǉ����t���Œ�����@@�@@ = null
                    l_mutualBuyProductGroup.addBuyMinQty = null;
                    //�ǉ����t���P�ʋ��z�@@�@@ = null
                    l_mutualBuyProductGroup.addBuyUnitAmt = null;
                    //�ǉ����t���Œ���z�@@�@@ = null
                    l_mutualBuyProductGroup.addBuyMinAmt = null;
                   
                    //������t���؎��ԁ@@ �@@�@@ = 
                    //���M������ԊǗ�.get������t����( )���R�[�����A 
                    //�߂��ꂽ�l�̂P�b��̎���"HHMMSS"��"HH:MM"�ɕҏW���ăZ�b�g����B

                    String l_strOrderCloseTime = 
                        WEB3MutualFundTradingTimeManagement.getOrderCloseTime();
                    Date l_datOrderCloseTime = 
                        WEB3DateUtility.getDate(l_strOrderCloseTime, "HHmmss");
                    l_strOrderCloseTime = 
                        WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(l_datOrderCloseTime, 1L),"HHmmss");  
                    l_strOrderCloseTime =  l_strOrderCloseTime.substring(0, 2)
                        + WEB3GentradeTimeDef.STR_COLON
                        + l_strOrderCloseTime.substring(2, 4);
                    l_mutualBuyProductGroup.orderCloseTime = 
                        l_strOrderCloseTime;
                   
                    //�~�]����z 
                    //�@@�@@1�j���M����.get�ʉ݃R�[�h()��T0�̏ꍇ �܂���
                    //�@@�@@    ���M����.is�O��MMF��true�̏ꍇ
                    //�@@�@@�@@�@@null���Z�b�g����B                  
                    //�@@�@@2�j���M����.get�ʉ݃R�[�h()��T0�łȂ��ꍇ ����
                    //�@@�@@�@@�@@���M����.is�O��MMF��false�̏ꍇ
                    //�@@�@@�@@�@@�g�����M����.get�~�]����z(WEB3MFDealDivDef.���t)���Z�b�g����B 
                    //�E�Q�l���[�g 
                    //�@@�@@1�j���M����.get�ʉ݃R�[�h( )��T0�̏ꍇ 
                    //�@@�@@�@@ null���Z�b�g����B 
                    //�@@�@@2�j���M����.get�ʉ݃R�[�h( )��T0�łȂ��ꍇ 
                    //        2-1) ���M����.is�O��MMF �� true�̏ꍇ
                    //             �g�����M����.get�O��MMF�בփ��[�g()�̖߂�l�O��MMF�בփ��[�gParams��TTS
                    //              ���Z�b�g����B�i������R�ʂŎl�̌ܓ��j
                    //       2-2) ���M����.is�O��MMF �� false�̏ꍇ
                    //�@@�@@�@@ �g�����M����.get�בփ��[�g()�̖߂�l�בփ��[�gParams��TTS / ���בփ��[�g�v�Z�P�� 
                    //�@@�@@�@@���Z�b�g����B�i������R�ʂŎl�̌ܓ��j
                    String l_strCurrencyCode = l_mutualFundProductParams.getCurrencyCode();
                    if (l_mutualFundProduct.isFrgnMmf())
                    {
                        l_mutualBuyProductGroup.yenConstantValue = null;
                    }
                    if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_strCurrencyCode)) 
                    {
                        l_mutualBuyProductGroup.yenConstantValue = null;
                        l_mutualBuyProductGroup.referenceRate = null;
                    }
                    else 
                    {
                        if (l_mutualFundProduct.isFrgnMmf())
                        {
                            BigDecimal l_bdTtSellingRate =
                                new BigDecimal(l_mutualFundProduct.getFrgnMmfExchangeRate().getTtSellingRate());
                            l_mutualBuyProductGroup.referenceRate =
                                WEB3StringTypeUtility.formatNumber(
                                    l_bdTtSellingRate.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        }
                        else
                        {
                            l_mutualBuyProductGroup.yenConstantValue =
                                l_mutualFundProduct.getYenConstantValue(WEB3MFDealDivDef.BUY); 
                            BigDecimal l_bdTtSellingRate = 
                                new BigDecimal(l_mutualFundProduct.getExchangeRate().getTtSellingRate());
                            BigDecimal l_bdExchangeCalcUnit = 
                                new BigDecimal(l_mutualFundProduct.getExchangeRate().getExchangeCalcUnit());
                            l_mutualBuyProductGroup.referenceRate = 
                                WEB3StringTypeUtility.formatNumber(
                                    l_bdTtSellingRate.divide(l_bdExchangeCalcUnit, 2, BigDecimal.ROUND_HALF_UP).doubleValue()); 
                        }
                    }
                   
					//   �E��W�J�n���@@= �g�����M����.get��W�J�n�� 
					//   �E��W�I�����@@= �g�����M����.get��W�I���� 
					//   �E��W�J�n���iSONAR�j�@@= �g�����M����.get��W�J�n���iSONAR�j 
					//   �E��W�I�����iSONAR�j�@@= �g�����M����.get��W�I�����iSONAR�j
                    l_mutualBuyProductGroup.applyAbleStartDate = l_mutualFundProduct.getRecruitStartDate();
                    l_mutualBuyProductGroup.applyAbleEndDate = l_mutualFundProduct.getRecruitEndDate();
                    l_mutualBuyProductGroup.applyAbleStartDateSonar = l_mutualFundProduct.getApplyAbleStartDateSonar();
                    l_mutualBuyProductGroup.applyAbleEndDateSonar = l_mutualFundProduct.getApplyAbleEndDateSonar();               
                   
                    //�E�Q�l���[�g�m���
                    //    1�j���M����.get�ʉ݃R�[�h( )��T0�̏ꍇ
                    //       null���Z�b�g����B
                    if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_strCurrencyCode))
                    {
                        l_mutualBuyProductGroup.referenceRateFixedDay = null;
                    }

                    //    2�j���M����.get�ʉ݃R�[�h( )��T0�łȂ��ꍇ
                    else
                    {
                        //       2-1) ���M����.is�O��MMF �� true�̏ꍇ
                        //            �g�����M����.get�O��MMF�בփ��[�g()�̖߂�l
                        //            �O��MMF�בփ��[�gParams�̈בփ��[�g�m������Z�b�g����B
                        if (l_mutualFundProduct.isFrgnMmf())
                        {
                            l_mutualBuyProductGroup.referenceRateFixedDay =
                                l_mutualFundProduct.getFrgnMmfExchangeRate().getExecTimestamp();
                        }
                        //       2-2) ���M����.is�O��MMF �� false�̏ꍇ
                        //            �g�����M����.get�בփ��[�g()�̖߂�l
                        //            �בփ��[�gParams�̈בփ��[�g�m������Z�b�g����B
                        else
                        {
                            l_mutualBuyProductGroup.referenceRateFixedDay =
                                l_mutualFundProduct.getExchangeRate().getExecTimestamp();
                        }
                    }

                    //�E�O��MMF�t���O = ���M����.is�O��MMF
                    l_mutualBuyProductGroup.frgnMmfFlag = l_mutualFundProduct.isFrgnMmf();

                    //�E�w����@@ = ���M����.get�w����@@�i��W�j
                    l_mutualBuyProductGroup.buySelectable =
                        l_mutualFundProduct.getRecruitSpecityDiv();

                    //1.12.6.9.2 ���M���t�����ꗗ�s�I�u�W�F�N�g�֒ǉ�
                    l_lisMutualBuyProductGroup.add(l_mutualBuyProductGroup);
               }
               
               //1.12.6.10 ���Ais��W�\(SONAR) == false
               else
               {
                   //1.12.6.10.1)�@@�擾�����g�����M�����I�u�W�F�N�g�����ɁA 
                   //�����������M���t�����ꗗ�s�I�u�W�F�N�g�Ɉȉ��̃v���p�e�B���Z�b�g����B 
                   //����������A�ȉ��̒l�𓊐M���t�����ꗗ�s�ɃZ�b�g����B
                   
                   //ID                         = ���M����.getProductId( )
                   l_mutualBuyProductGroup.id =
                       Long.toString(l_mutualFundProductParams.getProductId());
                   //�����R�[�h                   = ���M����.getProductCode( ) 
                   l_mutualBuyProductGroup.mutualProductCode = 
                       l_mutualFundProductParams.getProductCode();
                   //�������@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ = ���M����.get������( )
                   l_mutualBuyProductGroup.mutualProductName =
                       l_mutualFundProductParams.getStandardName();
                   //���M�����J�e�S���[�R�[�h  = ���M����.get�J�e�S���[�R�[�h( )
                   l_mutualBuyProductGroup.categoryCode =
                       l_mutualFundProductParams.getCategoryCode();
                   //���t����z�ʉ݃R�[�h = ���M����.get�ʉ݃R�[�h( )
                   l_mutualBuyProductGroup.constantValueCurrencyCode =
                       l_mutualFundProductParams.getCurrencyCode();
                   //���t����z�@@�@@�@@�@@�@@�@@  = ���M����.get���t����z( )
                   if (MutualFundTypeEnum.FOREIGN_MMF.equals(l_mutualFundProductParams.getFundType())
                       &&l_mutualFundProductParams.getBuyConstantValueIsNull())
                   {//�Ō��[�w�E����( 2007/3/5  �Ō��[_���t�ꗗ�i����n�j)
                       l_mutualBuyProductGroup.constantValue = null;
                   }
                   else
                   {
                       l_mutualBuyProductGroup.constantValue =
                           WEB3StringTypeUtility.formatNumber(
                               l_mutualFundProduct.getConstantValue());
                   }

                   //���t����z�K�p���@@�@@ = ���M����.get����z�K�p��( )
                   l_mutualBuyProductGroup.constantValueAppDate =
                       l_mutualFundProductParams.getConstantValueAppDate();
                   //�V�K���t���P�ʌ����@@�@@ = ���M����.get�P�ʌ���(�V�K���t)( )
                   if (!l_mutualFundProductParams.getNewBuyUnitQtyIsNull())
                   {
                       l_mutualBuyProductGroup.newBuyUnitQty =
                           Long.toString(l_mutualFundProductParams.getNewBuyUnitQty());
                   }
                   //�V�K���t���Œ�����@@�@@ = ���M����.get�Œ����(�V�K���t)( )
                   if(!l_mutualFundProductParams.getNewBuyMinQtyIsNull())
                   {
                       l_mutualBuyProductGroup.newBuyMinQty =
                           Long.toString(l_mutualFundProductParams.getNewBuyMinQty());
                   }
                   //�V�K���t���P�ʋ��z�@@�@@ = ���M����.get�P�ʋ��z(�V�K���t)( )
                   if (!l_mutualFundProductParams.getNewBuyUnitAmtIsNull())
                   {
                       l_mutualBuyProductGroup.newBuyUnitAmt =
                           Long.toString(l_mutualFundProductParams.getNewBuyUnitAmt());
                   }
                   //�V�K���t���Œ���z�@@�@@ = ���M����.get�Œ���z(�V�K���t)( )
                   if (!l_mutualFundProductParams.getNewBuyMinAmtIsNull())
                   {
                       l_mutualBuyProductGroup.newBuyMinAmt =
                           Long.toString(l_mutualFundProductParams.getNewBuyMinAmt());
                   }
                   //�ǉ����t���P�ʌ����@@�@@ = ���M����.get�P�ʌ���(�ǉ����t)( )
                   if(!l_mutualFundProductParams.getAddBuyUnitQtyIsNull())
                   {
                       l_mutualBuyProductGroup.addBuyUnitQty =
                           Long.toString(l_mutualFundProductParams.getAddBuyUnitQty());
                   }
                   //�ǉ����t���Œ�����@@�@@ = ���M����.get�Œ����(�ǉ����t)( )
                   if (!l_mutualFundProductParams.getAddBuyMinQtyIsNull())
                   {
                       l_mutualBuyProductGroup.addBuyMinQty =
                           Long.toString(l_mutualFundProductParams.getAddBuyMinQty());
                   }
                   //�ǉ����t���P�ʋ��z�@@�@@ = ���M����.get�P�ʋ��z(�ǉ����t)( )
                   if (!l_mutualFundProductParams.getAddBuyUnitAmtIsNull())
                   {
                       l_mutualBuyProductGroup.addBuyUnitAmt =
                           Long.toString(l_mutualFundProductParams.getAddBuyUnitAmt());
                   }
                   //�ǉ����t���Œ���z�@@�@@ = ���M����.get�Œ���z(�ǉ����t)( )
                   if (!l_mutualFundProductParams.getAddBuyMinAmtIsNull())
                   {
                       l_mutualBuyProductGroup.addBuyMinAmt =
                           Long.toString(l_mutualFundProductParams.getAddBuyMinAmt());
                   }
                   //������t���؎��ԁ@@ �@@�@@ = ���M������ԊǗ�.get������t���؎���( )���R�[�����߂��ꂽ�l"HHMMSS"��"HH:MM"�ɕҏW���ăZ�b�g�B
                   String l_strOrderCloseTime = 
                       WEB3MutualFundTradingTimeManagement.getOrderCloseTime();
                   Date l_datOrderCloseTime = 
                       WEB3DateUtility.getDate(l_strOrderCloseTime, "HHmmss");
                   l_strOrderCloseTime = 
                       WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(l_datOrderCloseTime, 1L),"HHmmss");  
                   l_strOrderCloseTime =  l_strOrderCloseTime.substring(0, 2)
                   	+ WEB3GentradeTimeDef.STR_COLON
                   	+ l_strOrderCloseTime.substring(2, 4);
                   l_mutualBuyProductGroup.orderCloseTime = 
                       l_strOrderCloseTime;
                   
                   //�~�]����z 
                   //�@@�@@1�j���M����.get�ʉ݃R�[�h()��T0�̏ꍇ �܂���
                   //  �@@�@@���M����.is�O��MMF��true�̏ꍇ
                   //�@@�@@�@@�@@null���Z�b�g����B                  
                   //�@@�@@2�j���M����.get�ʉ݃R�[�h()��T0�łȂ��ꍇ ����
                   //�@@�@@�@@�@@���M����.is�O��MMF��false�̏ꍇ
                   //�@@�@@�@@�@@�g�����M����.get�~�]����z(WEB3MFProcessDivDef.���t)���Z�b�g����B 
                   //�E�Q�l���[�g 
                   //�@@�@@1�j���M����.get�ʉ݃R�[�h( )��T0�̏ꍇ 
                   //�@@�@@�@@ null���Z�b�g����B 
                   //�@@�@@2�j���M����.get�ʉ݃R�[�h( )��T0�łȂ��ꍇ 
                   //       2-1) ���M����.is�O��MMF �� true�̏ꍇ
                   //              �g�����M����.get�O��MMF�בփ��[�g()�̖߂�l�O��MMF�בփ��[�gParams��TTS
                   //              ���Z�b�g����B�i������R�ʂŎl�̌ܓ��j
                   //       2-2) ���M����.is�O��MMF �� false�̏ꍇ
                   //�@@�@@�@@ �g�����M����.get�בփ��[�g()�̖߂�l�בփ��[�gParams��TTS / ���בփ��[�g�v�Z�P�� 
                   //�@@�@@�@@���Z�b�g����B�i������R�ʂŎl�̌ܓ��j
                   if (l_mutualFundProduct.isFrgnMmf())
                   {
                       l_mutualBuyProductGroup.yenConstantValue = null;
                   }
                   if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_mutualFundProductParams.getCurrencyCode())) 
                   {
                       l_mutualBuyProductGroup.yenConstantValue = null;
                       l_mutualBuyProductGroup.referenceRate = null;
                   }
                   else 
                   {
                       if (l_mutualFundProduct.isFrgnMmf())
                       {
                           BigDecimal l_bdTtSellingRate =
                               new BigDecimal(l_mutualFundProduct.getFrgnMmfExchangeRate().getTtSellingRate());
                           l_mutualBuyProductGroup.referenceRate =
                               WEB3StringTypeUtility.formatNumber(
                                   l_bdTtSellingRate.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                       }
                       else
                       {
                           l_mutualBuyProductGroup.yenConstantValue = 
                               l_mutualFundProduct.getYenConstantValue(WEB3MFProcessDivDef.BUY); 
                           BigDecimal l_bdTtSellingRate = 
                               new BigDecimal(l_mutualFundProduct.getExchangeRate().getTtSellingRate());
                           BigDecimal l_bdExchangeCalcUnit = 
                               new BigDecimal(l_mutualFundProduct.getExchangeRate().getExchangeCalcUnit());
                           l_mutualBuyProductGroup.referenceRate = 
                               WEB3StringTypeUtility.formatNumber(
                                   l_bdTtSellingRate.divide(l_bdExchangeCalcUnit, 2, BigDecimal.ROUND_HALF_UP).doubleValue()); 
                       }
                   }
                    //   �E��W�J�n���@@= �g�����M����.get��W�J�n�� 
					//   �E��W�I�����@@= �g�����M����.get��W�I���� 
					//   �E��W�J�n���iSONAR�j�@@= �g�����M����.get��W�J�n���iSONAR�j 
					//   �E��W�I�����iSONAR�j�@@= �g�����M����.get��W�I�����iSONAR�j
				   	l_mutualBuyProductGroup.applyAbleStartDate = l_mutualFundProduct.getRecruitStartDate();
				   	l_mutualBuyProductGroup.applyAbleEndDate = l_mutualFundProduct.getRecruitEndDate();
				   	l_mutualBuyProductGroup.applyAbleStartDateSonar = l_mutualFundProduct.getApplyAbleStartDateSonar();
				   	l_mutualBuyProductGroup.applyAbleEndDateSonar = l_mutualFundProduct.getApplyAbleEndDateSonar();  
                   
                     //�E�Q�l���[�g�m���
                     //    1�j���M����.get�ʉ݃R�[�h( )��T0�̏ꍇ
                     //       null���Z�b�g����B
                    if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(
                        l_mutualFundProductParams.getCurrencyCode()))
                    {
                        l_mutualBuyProductGroup.referenceRateFixedDay = null;
                    }

                     //    2�j���M����.get�ʉ݃R�[�h( )��T0�łȂ��ꍇ
                    else
                    {
                        //       2-1) ���M����.is�O��MMF �� true�̏ꍇ
                        //            �g�����M����.get�O��MMF�בփ��[�g()�̖߂�l
                        //            �O��MMF�בփ��[�gParams�̈בփ��[�g�m������Z�b�g����B
                        if (l_mutualFundProduct.isFrgnMmf())
                        {
                            l_mutualBuyProductGroup.referenceRateFixedDay =
                                l_mutualFundProduct.getFrgnMmfExchangeRate().getExecTimestamp();
                        }

                        //       2-2) ���M����.is�O��MMF �� false�̏ꍇ
                        //            �g�����M����.get�בփ��[�g()�̖߂�l
                        //            �בփ��[�gParams�̈בփ��[�g�m������Z�b�g����B
                        else
                        {
                            l_mutualBuyProductGroup.referenceRateFixedDay =
                                l_mutualFundProduct.getExchangeRate().getExecTimestamp();
                        }
                    }

                    //�E�O��MMF�t���O = ���M����.is�O��MMF
                    l_mutualBuyProductGroup.frgnMmfFlag = l_mutualFundProduct.isFrgnMmf();
                    //�V�K���t���O�ݒP�ʋ��z�@@�@@ = ���M����.get�O�ݒP�ʋ��z(�V�K���t)( )
                    if (!l_mutualFundProductParams.getFrgnNewBuyUnitAmtIsNull())
                    {
                        l_mutualBuyProductGroup.newBuyFrgnUnitAmt =
                            l_mutualFundProduct.getFrgnNewBuyUnitAmt() + "";
                    }
                    //�E�V�K���t���O�ݍŒ���z�@@�@@ = ���M����.get�O�ݍŒ���z(�V�K���t)( )
                    if (!l_mutualFundProductParams.getFrgnNewBuyMinAmtIsNull())
                    {
                        l_mutualBuyProductGroup.newBuyFrgnMinAmt =
                            l_mutualFundProduct.getFrgnNewBuyMinAmt() + "";
                    }
                    //�E�ǉ����t���O�ݒP�ʋ��z�@@�@@ = ���M����.get�O�ݒP�ʋ��z(�ǉ����t)( )
                    if (!l_mutualFundProductParams.getFrgnAddBuyUnitAmtIsNull())
                    {
                        l_mutualBuyProductGroup.addBuyFrgnUnitAmt =
                            l_mutualFundProduct.getFrgnAddBuyUnitAmt() + "";
                    }
                    //�E�ǉ����t���O�ݍŒ���z�@@�@@ = ���M����.get�O�ݍŒ���z(�ǉ����t)( )
                    if (!l_mutualFundProductParams.getFrgnAddBuyMinAmtIsNull())
                    {
                        l_mutualBuyProductGroup.addBuyFrgnMinAmt =
                            l_mutualFundProduct.getFrgnAddBuyMinAmt() + "";
                    }

                    //�E�w����@@ = ���M����.get�w����@@(���t)
                    l_mutualBuyProductGroup.buySelectable =
                        l_mutualFundProduct.getBuySelectable();

                   //1.12.6.10.2 ���M���t�����ꗗ�s�I�u�W�F�N�g�֒ǉ�
                   l_lisMutualBuyProductGroup.add(l_mutualBuyProductGroup);
               }
            }
        }
        WEB3MutualBuyProductGroup[] l_mutualBuyProductGroups = 
            new WEB3MutualBuyProductGroup[l_lisMutualBuyProductGroup.size()];
        l_lisMutualBuyProductGroup.toArray(l_mutualBuyProductGroups);
        //�@@�y�[�W���O���� 
        //1.13)�@@���M���t�ꗗ�Ɖ�N�G�X�g.create���X�|���X( )���\�b�h���R�[�����A���M���t�ꗗ�Ɖ�X�|���X�N���X�𐶐�����B 
        WEB3MutualBuyListResponse l_mutualBuyListResponse =
            (WEB3MutualBuyListResponse) l_mutualBuyListRequest.createResponse();
        //1.14)�@@���M���t�ꗗ�Ɖ�X�|���X�̈ȉ��̍��ڂ�ݒ肷��
        //�����M���t�ꗗ�Ɖ�X�|���X.���y�[�W��: 
        int l_int = l_intRecords / Integer.parseInt(l_mutualBuyListRequest.pageSize);
        if (l_intRecords == 0)
        {
            l_mutualBuyListResponse.totalPages = "0";
        }
        else if (l_intRecords % Integer.parseInt(l_mutualBuyListRequest.pageSize) == 0)
        {
            l_mutualBuyListResponse.totalPages = l_int + "";
        }
        else if (l_intRecords % Integer.parseInt(l_mutualBuyListRequest.pageSize) > 0)
        {
            l_mutualBuyListResponse.totalPages = Integer.toString(l_int + 1);
        }

        //�����M���t�ꗗ�Ɖ�X�|���X.�����R�[�h��:�@@�V)�Ŋm�肵�����ׂ̗v�f��
        l_mutualBuyListResponse.totalRecords = Integer.toString(l_intRecords);
        //�����M���t�ꗗ�Ɖ�X�|���X.�\���y�[�W�ԍ�(�\�������y�[�W�ڂɂ����邩):�ȉ����Z�b�g
        if (l_intRecords > Integer.parseInt(l_mutualBuyListRequest.pageSize)
            * (Integer.parseInt(l_mutualBuyListRequest.pageIndex) - 1))
        {
            l_mutualBuyListResponse.pageIndex = l_mutualBuyListRequest.pageIndex;
        }
        else
        {
            l_mutualBuyListResponse.pageIndex = l_mutualBuyListResponse.totalPages;
        }
        //�ݒ��A���M���t�ꗗ�Ɖ�X�|���X.���y�[�W�� = 0 �̏ꍇ�́A
        //���M���t�ꗗ�Ɖ�X�|���X.���t�����ꗗ(���M���t�����ꗗ�s[ ])��null���Z�b�g��
        //��O���X���[����B
        if ("0".equals(l_mutualBuyListResponse.totalPages))
        {
            l_mutualBuyListResponse.buyProductGroups = null;
            log.debug("���M���t�ꗗ�Ɖ�X�|���X.���y�[�W�� = 0");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00397,
                STR_METHOD_NAME);
        }
        //�@@���ׂ̃Z�b�g
        //(���M���t�ꗗ�Ɖ�N�G�X�g.�y�[�W���\���s���~(���M���t�ꗗ�Ɖ�X�|���X.
        //�\���y�[�W�ԍ� - 1)�����A�V)�Ŋm�肵�����M���t�ꗗ�Ɖ�X�|���X���׃f�[�^�ꗗ��
        //�v�f���X�L�b�v����
        // -------------- Record Begin
        int l_intRecordBegin =
            Integer.parseInt(l_mutualBuyListRequest.pageSize)
                * (Integer.parseInt(l_mutualBuyListResponse.pageIndex) - 1);
        // -------------- Record End
        int l_intRecordEnd = 0;
        if (l_mutualBuyListResponse.pageIndex.equals(l_mutualBuyListResponse.totalPages))
        {
            l_intRecordEnd = l_intRecords;
        }
        else
        {
            l_intRecordEnd = l_intRecordBegin + Integer.parseInt(l_mutualBuyListRequest.pageSize);
        }
        //���M���t�ꗗ�Ɖ�X�|���X���׃f�[�^�𓊐M���t�ꗗ�Ɖ�X�|���X�f�[�^.���t�����ꗗ�Ƃ��ăZ�b�g����B
        List l_lisBuyProductGroups = new Vector();
        for (int i = l_intRecordBegin ; i < l_intRecordEnd; i++) 
        {
            // ------------ ���M���t�ꗗ�Ɖ�X�|���X���׃f�[�^�ꗗ�̗v�f���Z�b�g����
            l_lisBuyProductGroups.add(l_mutualBuyProductGroups[i]);
        }
        WEB3MutualBuyProductGroup[] l_BuyProductGroups = new WEB3MutualBuyProductGroup[l_lisBuyProductGroups.size()];
        l_lisBuyProductGroups.toArray(l_BuyProductGroups); 
        l_mutualBuyListResponse.buyProductGroups = l_BuyProductGroups;
        // �Ŏ擾�������M�����J�e�S���[�ꗗ���A���M���t�ꗗ�Ɖ�X�|���X�f�[�^ 
        //  .���M�����J�e�S���[�ꗗ�ɃZ�b�g����B
        l_mutualBuyListResponse.categoryList = l_mutualProductCategoryUnits;
        log.exiting(STR_METHOD_NAME);
        return l_mutualBuyListResponse;
    }
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * �w�肳�ꂽ�J�e�S���[�R�[�h����A���������ƂȂ肤��S�ẴJ�e�S���[�R�[�h��<BR>
     * �擾���A�����z��ɂ��ĕԋp����B<BR>
     * <BR>
     * �P)�@@�g�����M�����}�l�[�W��.get���ʓ��M�����J�e�S���[���X�g( )���R�[������B<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@����:�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@����:�J�e�S���[�R�[�h<BR>
     * <BR>
     * �Q)�@@�P)�Ō������ʂƂ��Ď擾�����S�ẴJ�e�S���[�R�[�h��String�̔z���<BR>
     * �@@�i�[���ă��^�[������B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strCategoryCode - �����J�e�S���[�R�[�h
     * @@return String[ ]
     * @@roseuid 40D17FC00150
     */
    public String[] createSearchCondDataContainer(
        String l_strInstitutionCode,
        String l_strCategoryCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createSearchCondDataContainer(String l_strInstitutionCode,String l_strCategoryCode)";
        if("".equals(l_strInstitutionCode) || "".equals(l_strCategoryCode))
        {
            log.debug("the parameter of mothed l_strInstitutionCode " +
                "or l_strCategoryCode is null or blank");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P)�@@�g�����M�����}�l�[�W��.get���ʓ��M�����J�e�S���[���X�g( )���R�[������
        List l_LowMutualFundProductCategoryList = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getProductManager();
        try
        {
            l_LowMutualFundProductCategoryList =
                l_mutualFundProductManager.getLowMutualFundProductCategoryList(
                    l_strInstitutionCode,
                    l_strCategoryCode);
        }
        catch (WEB3BaseException e)
        {
            log.error(" no found LowMutualFundProductCategoryList");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getErrorMessage(),
                e);
        }
        // �Q)�@@�P)�Ō������ʂƂ��Ď擾�����S�ẴJ�e�S���[�R�[�h��String�̔z��Ɋi�[���ă��^�[������B
        if (l_LowMutualFundProductCategoryList == null)
        {
            log.debug("���ʓ��M�����J�e�S���[���X�g null");
            l_LowMutualFundProductCategoryList = new Vector();
        }
        int l_intmfProductCategory = l_LowMutualFundProductCategoryList.size();
        String[] l_strMFProductCategoryCodes = new String[l_intmfProductCategory + 1];
        for (int i = 0; i < l_intmfProductCategory; i++)
        {
            MutualFundProductCategoryRow l_mfProductCategoryRow = 
                (MutualFundProductCategoryRow)l_LowMutualFundProductCategoryList.get(i);
            l_strMFProductCategoryCodes[i] = l_mfProductCategoryRow.getCategoryCode();
        }
        l_strMFProductCategoryCodes[l_intmfProductCategory] = l_strCategoryCode;
        return l_strMFProductCategoryCodes;
    }
}
@
