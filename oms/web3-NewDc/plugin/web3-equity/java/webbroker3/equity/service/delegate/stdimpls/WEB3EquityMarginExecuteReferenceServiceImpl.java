head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMarginExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �����E�M�p�������Ɖ�T�[�rImpl(WEB3EquityMarginExecuteReferenceServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/01/08  �����@@(���u) �V�K�쐬
 Revesion History : 2007/01/11  �֔�(���u)   ���\�b�h��ǉ�
 Revesion History : 2007/01/11  �g��i(���u) ���\�b�h��ǉ�
 Revesion History : 2007/01/11  �����Q(���u) ���\�b�h��ǉ�
 Revesion History : 2007/03/01  �֔�(���u) �d�l�ύX���f��1128
 Revesion History : 2007/03/07  �ʉ�(SRA) �d�l�ύX���f��1132
 Revesion History : 2007/04/25  ���g(���u) �d�l�ύX���f��1137
 Revesion History : 2007/06/05 �����q (���u) �d�l�ύX�E���f��1164
 Revesion History : 2007/10/16 ����(���u) �d�l�ύX���f��1201�A�d�l�ύX���f��1202
 Revesion History : 2007/12/18 ��іQ (���u) ���f�� No.1233,1234,1235,1246,1252,1268
 Revesion History : 2007/12/18 ��іQ (���u) ���f�� No.1284,1291
 Revesion History : 2008/04/16  �����Q (���u) ���f�� No.1314
 Revesion History : 2008/04/21  ���� (���u) ���f�� No.1318
 */

package webbroker3.equity.service.delegate.stdimpls;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSwapOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityFinTransactionManager;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityProductDivDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.equity.message.WEB3EquityMarginExecuteGroup;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceRequest;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceResponse;
import webbroker3.equity.message.WEB3EquityMarginExecuteUnit;
import webbroker3.equity.message.WEB3EquityMarginOrderIdUnit;
import webbroker3.equity.message.WEB3EquityMarginSortKey;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.service.delegate.WEB3EquityMarginExecuteReferenceService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�����E�M�p�������Ɖ�T�[�r�XImpl)<BR>
 * �����E�M�p�������Ɖ�T�[�r�X�����N���X<BR>
 * @@author ����(���u)
 * @@author �֔�(���u)
 * @@author �g��i(���u)
 * @@author �����Q(���u)
 * @@version 1.0 <BR>
 */
public class WEB3EquityMarginExecuteReferenceServiceImpl extends WEB3EquityClientRequestService implements
    WEB3EquityMarginExecuteReferenceService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceServiceImpl.class);

    /**
     * @@roseuid 45A3606E032C
     */
    public WEB3EquityMarginExecuteReferenceServiceImpl()
    {

    }

    /**
     * �����E�M�p�������Ɖ�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����E�M�p�������Ɖ�)01.�������f�[�^�擾�v �Q�ƁB<BR>
     * <BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�����E�M�p������ʃT�[�r�X���f��) /<BR>
     *  �����E�M�p�������Ɖ�v(�i�����E�M�p�������Ɖ�j01.�������f�[�^�擾)<BR>
     * �@@�@@is�M�p�戵���{(�ٍϋ敪 : String)<BR>
     * �@@�@@�߂�l == false�̏ꍇ�A<BR>
     * �@@�@@�w�M�p�����{�G���[�x�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag   : BUSINESS_ERROR_00746<BR>
     * ==========================================================<BR>
     * <BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�����E�M�p������ʃT�[�r�X���f��) /<BR>
     *  �����E�M�p�������Ɖ�v(�i�����E�M�p�������Ɖ�j01.�������f�[�^�擾)<BR>
     * �@@�@@is�M�p�����J��(�ٍϋ敪 : String)<BR>
     * �@@�@@�߂�l == false�̏ꍇ�A<BR>
     * �@@�@@�w�M�p�������J�݃G���[�x�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag   : BUSINESS_ERROR_00747<BR>
     * ==========================================================<BR>
     * <BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�����E�M�p������ʃT�[�r�X���f��) /<BR>
     *  �����E�M�p�������Ɖ�v(�i�����E�M�p�������Ɖ�j01.�������f�[�^�擾)<BR>
     * �@@1.9.2 getProduct(�،���� : Institution, �����R�[�h : String)<BR>
     * �@@�@@���݃`�F�b�N�̎��{�擾�ł��Ȃ������ꍇ�́A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag   : BUSINESS_ERROR_00301<BR>
     * ==========================================================<BR>
     * <BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�����E�M�p������ʃT�[�r�X���f��) /<BR>
     *  �����E�M�p�������Ɖ�v(�i�����E�M�p�������Ɖ�j01.�������f�[�^�擾)<BR>
     * �@@1.9.3.3 get�������(�������� : ��������, �s�� : �s��)<BR>
     * �@@�@@���݃`�F�b�N�̎��{�擾�ł��Ȃ������ꍇ�́A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag   : BUSINESS_ERROR_00638<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 455D1CD60076
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("�p�����[�^��null�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //validate( )
        WEB3EquityMarginExecuteReferenceRequest l_equityMarginExecuteReferenceRequest =
            (WEB3EquityMarginExecuteReferenceRequest) l_request;
        l_equityMarginExecuteReferenceRequest.validate();

        //get����( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();

        //getBranch( )
        WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_mainAccount.getBranch();

        //is�M�p�����J��(�ٍϋ敪 : String)
        boolean l_blnIsMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //(*)���N�G�X�g.���i�敪 == �h�M�p����h�̏ꍇ�A���{
        if (WEB3EquityProductDivDef.MARGIN.equals(l_equityMarginExecuteReferenceRequest.productDiv))
        {
            //is�M�p�戵���{(�ԍϋ敪�FString)
            boolean l_isMarginTradeEnforcement =
                l_branch.isMarginTradeEnforcement(WEB3GentradeRepaymentDivDef.DEFAULT);
            if (!l_isMarginTradeEnforcement)
            {
                log.error("���Y���X�ł́A�戵���s�\�ł�");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00746,
                    this.getClass().getName()
                    + STR_METHOD_NAME, "���Y���X�ł́A�戵���s�\�ł�");
            }
            if (!l_blnIsMarginAccountEstablished)
            {
                log.error("�M�p�������J�݃G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                    this.getClass().getName()
                    + STR_METHOD_NAME, "�M�p�������J�݃G���[");
            }
        }

        //getDataSourceObject( )
        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();

        //validate������t�\(String, String, boolean, boolean)
        boolean l_blnIsExecute = false;
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getOffFloorDiv()))
        {
            l_blnIsExecute = true;
        }
        if (WEB3EnforcementDef.NOT_ENFORCEMENT.equals(l_branchRow.getOffFloorDiv()))
        {
            l_blnIsExecute = false;
        }
        OrderStatus l_orderStatus = null;
        l_orderStatus = this.validateOrderAccept(l_equityMarginExecuteReferenceRequest.referenceType,
            l_equityMarginExecuteReferenceRequest.productDiv, l_blnIsMarginAccountEstablished, l_blnIsExecute);

        //reset�s��R�[�h(�s��R�[�h : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_equityMarginExecuteReferenceRequest.marketCode);

        //���N�G�X�g.�Ɖ�敪��"��������ꗗ"�̏ꍇ�̂ݎ��s
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(
            l_equityMarginExecuteReferenceRequest.referenceType))
        {
            //validate����\�ڋq(�ڋq, String)
            //[����]
            // �ڋq�F �擾�����ڋq�I�u�W�F�N�g
            //�s��R�[�h�F���N�G�X�g.�s��R�[�h
            OrderValidationResult l_orderValidationResult =
                this.validateAccountForTrading(
                    l_mainAccount,
                    l_equityMarginExecuteReferenceRequest.marketCode);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("validate����\�ڋq�Ƃ������\�b�h�̃`�F�b�N�����s");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_orderValidationResult.getProcessingResult().getErrorInfo().getErrorMessage());
            }
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //���N�G�X�g.�����R�[�h != null �̏ꍇ
        if (WEB3StringTypeUtility.isNotEmpty(l_equityMarginExecuteReferenceRequest.productCode))
        {
            //getInstitution( )
            WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_mainAccount.getInstitution();

            //getProduct(�،���� : Institution, �����R�[�h : String)
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            EqTypeProduct l_eqTypeProduct = null;
            try
            {
                l_eqTypeProduct = l_productManager.getProduct(l_institution,
                    l_equityMarginExecuteReferenceRequest.productCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�w�肵�������R�[�h�ɍ��v���Ă�����������݂��܂���B",
                    l_ex);
            }

            //���N�G�X�g.�s��R�[�h != null �̏ꍇ
            if (WEB3StringTypeUtility.isNotEmpty(l_equityMarginExecuteReferenceRequest.marketCode))
            {
                //reset��t���ԋ敪(��t���ԋ敪 : String)
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

                //get�s��(�،���ЃR�[�h : , �s��R�[�h : )
                WEB3GentradeFinObjectManager l_gentradeFinObjectManager = (WEB3GentradeFinObjectManager)
                    l_finApp.getFinObjectManager();
                Market l_market = null;
                try
                {
                    l_market = l_gentradeFinObjectManager.getMarket(l_institution.getInstitutionCode(),
                        l_equityMarginExecuteReferenceRequest.marketCode);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�s��I�u�W�F�N�g���������Ȃ�", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //get�������(��������, �s��)
                try
                {
                    l_productManager.getTradedProduct(l_eqTypeProduct, l_market);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("����������擾�ł��Ȃ�", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00638,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�w������͎w��s��ł̎戵�s�B");
                }
            }
        }

        //createResponse()
        WEB3EquityMarginExecuteReferenceResponse l_equityMarginExecuteReferenceResponse =
            (WEB3EquityMarginExecuteReferenceResponse)l_equityMarginExecuteReferenceRequest.
                createResponse();

        // create������薾��(�����E�M�p�������Ɖ�N�G�X�g, �����E�M�p�������Ɖ�X�|���X,
        // ������t���)(�����E�M�p�������Ɖ�T�[�r�XImpl::create������薾��)
        if (l_orderStatus == null)
        {
            log.debug("������t��ԃI�u�W�F�N�g��null�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        this.createExecuteGroup(l_equityMarginExecuteReferenceRequest, l_equityMarginExecuteReferenceResponse,
            l_orderStatus);

        //reset��t���ԋ敪(��t���ԋ敪 : String)
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

        String[] l_strPossibleMarket;
        String l_strMargineTradeDiv = null;
        String[] l_strAscPossibleMarkets = null;
        if (WEB3EquityProductDivDef.MARGIN.equals(l_equityMarginExecuteReferenceRequest.productDiv))
        {
            //���N�G�X�g.���i�敪 == �h�M�p����h �̏ꍇ
            String l_strRepaymentDiv = null;
            if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(
                l_mainAccount.getMainAccountRow().getMarginSysAccOpenDiv()) &&
                WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN.equals(
                l_mainAccount.getMainAccountRow().getMarginGenAccOpenDiv()))
            {
                //�ٍϋ敪�F
                //�ڋq.���x�M�p��������J�݋敪 == �h�����J�݁h and
                //�ڋq.��ʐM�p��������J�݋敪 == �hDEFAULT�i�����Ȃ��j�h �̏ꍇ�A�h���x�M�p�h
                l_strRepaymentDiv = WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS;

                //�M�p����敪�F
                //�ڋq.���x�M�p��������J�݋敪 == �h�����J�݁h and
                //�ڋq.��ʐM�p��������J�݋敪 == �hDEFAULT�i�����Ȃ��j�h �̏ꍇ�A�h���x�M�p�h
                l_strMargineTradeDiv = WEB3MarginTradingDivDef.MARKET_MARGIN;
            }

            if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN.equals(l_mainAccount.getMainAccountRow().
                getMarginSysAccOpenDiv()) && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(
                l_mainAccount.getMainAccountRow().getMarginGenAccOpenDiv()))
            {
                //�ٍϋ敪�F
                //�ڋq.���x�M�p��������J�݋敪 == �hDEFAULT�i�����Ȃ��j�h and
                //�ڋq.��ʐM�p��������J�݋敪 == �h�����J�݁h �̏ꍇ�A�h��ʐM�p�h
                l_strRepaymentDiv = WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN;

                //�M�p����敪�F
                //�ڋq.���x�M�p��������J�݋敪 == �hDEFAULT�i�����Ȃ��j�h and
                //�ڋq.��ʐM�p��������J�݋敪 == �h�����J�݁h �̏ꍇ�A�h��ʐM�p�h
                l_strMargineTradeDiv = WEB3MarginTradingDivDef.INSTITUTION_MARGIN;
            }

            if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccount.getMainAccountRow().
                getMarginSysAccOpenDiv()) && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(
                l_mainAccount.getMainAccountRow().getMarginGenAccOpenDiv()))
            {
                //�ٍϋ敪�F
                //�ڋq.���x�M�p��������J�݋敪 == �h�����J�݁h and
                //�ڋq.��ʐM�p��������J�݋敪 == �h�����J�݁h �̏ꍇ�A�hDEFAULT�h
                l_strRepaymentDiv = WEB3GentradeRepaymentDivDef.DEFAULT;

                //�M�p����敪�F
                //�ڋq.���x�M�p��������J�݋敪 == �h�����J�݁h and
                //�ڋq.��ʐM�p��������J�݋敪 == �h�����J�݁h �̏ꍇ�A�h���x/��ʐM�p�i�����j�h
                l_strMargineTradeDiv = WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN;
            }
            //get�戵�\�s��(���X : ���X, �ٍϋ敪 : String, �ٍϊ����l : double)
            l_strPossibleMarket = WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(l_branch,
                l_strRepaymentDiv, 0.0d);
        }
        else
        {
            //���N�G�X�g.���i�敪 != �h�M�p����h �̏ꍇ
            //get�戵�\�s��(���X : ���X, �����^�C�v : ProductTypeEnum)
            //�戵�\�s��擾���\�b�h�@@
            l_strPossibleMarket =
                WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(l_branch, ProductTypeEnum.EQUITY);

            //get�戵�\�s��(���X : ���X, �����^�C�v : ProductTypeEnum)
            //�戵�\�s��擾���\�b�h�A
            String[] l_strPTSPossibleMarkets =
                WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(l_branch, ProductTypeEnum.EQUITY);

            //�戵�\�s��擾���\�b�h�@@�Ǝ戵�\�s��擾���\�b�h�A�̌��ʂ�
            //�s��R�[�h�����Ń}�[�W����B
            l_strAscPossibleMarkets = this.mergeAndSort(l_strPossibleMarket, l_strPTSPossibleMarkets);

            //�M�p����敪�F�hDEFAULT�h
            l_strMargineTradeDiv = WEB3MarginTradingDivDef.DEFAULT;
        }

        //get�������ꗗ(String[])
        Date[] l_datOrderBizDateLists = this.getOrderBizDateList(l_strPossibleMarket);

        //get�s��ǌx���s��(���X : ���X, �����^�C�v : ProductTypeEnum, �M�p����敪 : String)
        String[] l_strCloseMarkets = this.getTradeCloseMarket(
            l_branch, ProductTypeEnum.EQUITY, l_strMargineTradeDiv);

        //�v���p�e�B���Z�b�g����B
        //�s��R�[�h�ꗗ�F get�戵�\�s��()�̖߂�l
        //���i�敪!="�M�p���"�̏ꍇ
        //�戵�\�s��擾���\�b�h�@@�Ǝ戵�\�s��擾���\�b�h�A�̌��ʂ�
        //�s��R�[�h�����Ń}�[�W�������̂��Z�b�g����B
        if (WEB3EquityProductDivDef.MARGIN.equals(l_equityMarginExecuteReferenceRequest.productDiv))
        {
            l_equityMarginExecuteReferenceResponse.marketList = l_strPossibleMarket;
        }
        else
        {
            l_equityMarginExecuteReferenceResponse.marketList = l_strAscPossibleMarkets;
        }

        //�������ꗗ�F get�������ꗗ()�̖߂�l
        l_equityMarginExecuteReferenceResponse.orderBizDateList = l_datOrderBizDateLists;

        //����I���x���s��R�[�h�ꗗ�F get�s��ǌx���s��()�̖߂�l
        l_equityMarginExecuteReferenceResponse.messageSuspension = l_strCloseMarkets;

        //(*)���X�|���X.�������ꗗ == null or
        //   ���X�|���X.�������ꗗ�̗v�f�� == 0 �̏ꍇ�A�ȉ��̃v���p�e�B���Z�b�g����B
        //
        //   �\���y�[�W�ԍ�	�F "0"
        //   ���y�[�W��		�F "0"
        //   �����R�[�h��	�F "0"
        if (l_equityMarginExecuteReferenceResponse.executeGroups == null ||
        	l_equityMarginExecuteReferenceResponse.executeGroups.length == 0)
        {
        	l_equityMarginExecuteReferenceResponse.pageIndex = "0";
        	l_equityMarginExecuteReferenceResponse.totalPages = "0";
        	l_equityMarginExecuteReferenceResponse.totalRecords = "0";
        }

        log.exiting(STR_METHOD_NAME);
        return l_equityMarginExecuteReferenceResponse;
    }

    /**
     * (validate������t�\)<BR>
     * ��t���ԃ`�F�b�N�A�V�X�e��������~�`�F�b�N���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����E�M�p�������Ɖ�j02.������t�\�`�F�b�N�v �Q�ƁB<BR>
     * <BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�����E�M�p������ʃT�[�r�X���f��) /<BR>
     *  �����E�M�p�������Ɖ�v(�i�����E�M�p�������Ɖ�j02.������t�\�`�F�b�N)<BR>
     * �@@1.2.3 �R�[���������ׂĂ� validate������t�X�e�C�^�X() �̌��ʂ��h��t�s�h�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     *           ���u�ً}��~���v�̏ꍇ�A�u�ً}��~���v�̗�O���X���[<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag   : BUSINESS_ERROR_00011<BR>
     *           ���u�o�b�`�������v�̏ꍇ�A�u�o�b�`�������v�̗�O���X���[<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag   : BUSINESS_ERROR_00012<BR>
     * ==========================================================<BR>
     * <BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�����E�M�p������ʃT�[�r�X���f��) /<BR>
     *  �����E�M�p�������Ɖ�v(�i�����E�M�p�������Ɖ�j02.������t�\�`�F�b�N)<BR>
     * �@@1.3.3 �R�[���������ׂĂ� validate������t�X�e�C�^�X() �̌��ʂ��h��t�s�h�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     *           ���u�ً}��~���v�̏ꍇ�A�u�ً}��~���v�̗�O���X���[<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag   : BUSINESS_ERROR_00011<BR>
     *           ���u�o�b�`�������v�̏ꍇ�A�u�o�b�`�������v�̗�O���X���[<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag   : BUSINESS_ERROR_00012<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_strReferenceType - (�Ɖ�敪)<BR>
     * �Ɖ�敪<BR>
     * <BR>
     * @@param l_strProductType - (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * @@param l_blnIsMargin - (is�M�p�q)<BR>
     * �M�p�q���ǂ����̃t���O<BR>
     * <BR>
     * true�F �M�p�q<BR>
     * false�F �����q<BR>
     * <BR>
     * @@param l_blnIsOffFloorExecute - (is����O�������{)<BR>
     * ����O���������{���Ă��邩�ǂ����̃t���O<BR>
     * <BR>
     * true�F ���{���<BR>
     * false�F �����{���<BR>
     * <BR>
     * @@return
     * webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServ
     * iceImpl.OrderStatus
     * @@roseuid 455D5FE50180
     */
    protected OrderStatus validateOrderAccept(
            String l_strReferenceType,
            String l_strProductType,
            boolean l_blnIsMargin,
            boolean l_blnIsOffFloorExecute) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validateOrderAccept()";
        log.entering(STR_METHOD_NAME);

        OrderStatus l_orderStatus = new OrderStatus();

        // (*1) ����.�Ɖ�敪 == �h�������Ɖ�h �̏ꍇ
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(
                l_strReferenceType))
    {
            WEB3BaseException l_web3BaseException = null;
            
            // (*1-1) ����.���i�敪 != �h�M�p����h �̏ꍇ
            if (!WEB3EquityProductDivDef.MARGIN.equals(l_strProductType))
            {
                try
                {
                    //validate������t�X�e�C�^�X()
                    WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                    l_orderStatus.interestEquityFlag = true;
                }
                catch (WEB3BaseException l_bex)
                {
                    l_web3BaseException = l_bex;
                    log.debug("�Ɖ�敪 == �h�������Ɖ�h And ���i�敪 != �h�M�p����h�̏ꍇ�A" +
                            "validate������t�X�e�C�^�X���h��t�s�h�ł���", l_bex);
                    l_orderStatus.interestEquityFlag = false;
                }


                // (*1-2) ����.is����O�������{ == true �̏ꍇ
                if (l_blnIsOffFloorExecute)
                {
                    try
                    {
                        // reset������t���i(������t���i:String)
                        WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(
                                WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
                        //validate������t�X�e�C�^�X( )
                        WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                        l_orderStatus.offFloor = true;
                    }
                    catch (WEB3BaseException l_bex)
                    {
                        l_web3BaseException = l_bex;
                        log.debug("�Ɖ�敪 == �h�������Ɖ�h And is����O�������{ == true�̏ꍇ�A" +
                                "validate������t�X�e�C�^�X���h��t�s�h�ł���", l_bex);
                        l_orderStatus.offFloor = false;
                    }
                }
            }

            // (*1-3) ����.���i�敪 != �h���������h and ����.is�M�p�q == true �̏ꍇ
            if (!WEB3EquityProductDivDef.EQUITY.equals(l_strProductType) &&
                (l_blnIsMargin))
            {
                try
                {

                    // reset������t���i(������t���i : String)
                    WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(
                            WEB3OrderAccProductDef.MARGIN);
                    //validate������t�X�e�C�^�X()
                    WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                    l_orderStatus.interestMarginFlag = true;
                    l_orderStatus.swapFlag = true;
                }
                catch (WEB3BaseException l_bex)
                {
                    l_web3BaseException = l_bex;
                    log.debug("����.���i�敪 != �h���������h and ����.is�M�p�q == true �̏ꍇ�A" +
                            "validate������t�X�e�C�^�X���h��t�s�h�ł���", l_bex);
                    l_orderStatus.interestMarginFlag = false;
                    l_orderStatus.swapFlag = false;
                }
            }
            
            //�R�[���������ׂĂ� validate������t�X�e�C�^�X() �̌��ʂ��h��t�s�h�̏ꍇ�A
            //�i�R�[���������ׂĂ� validate������t�X�e�C�^�X() ����O���X���[�����ꍇ�A�j
            //��O���X���[����B
            //���u�ً}��~���v�̏ꍇ�A�u�ً}��~���v�̗�O���X���[
            //���u�o�b�`�������v�̏ꍇ�A�u�o�b�`�������v�̗�O���X���[
            if (l_orderStatus.interestEquityFlag == false &&
                l_orderStatus.offFloor == false &&
                l_orderStatus.interestMarginFlag == false &&
                l_orderStatus.swapFlag == false )
            {
              // ������t�X�e�C�^�X���o�b�`��
              if (l_web3BaseException.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00011)
              {
                  throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                        WEB3GentradeTradingTimeManagement.class.getName()
                            + "." + STR_METHOD_NAME);
              }
              // ������t�X�e�C�^�X���ً}��~��
              else if (l_web3BaseException.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00012)
              {
                throw new WEB3BusinessLayerException(
                      WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                      WEB3GentradeTradingTimeManagement.class.getName()
                          + "." + STR_METHOD_NAME);
              }
              else
              {
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_02706,
                     this.getClass().getName()+ STR_METHOD_NAME,
                     "�Ɖ�s�B");
              }
            }
        }

        // (*2) ����.�Ɖ�敪 == �h��������ꗗ�h �̏ꍇ
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(
                l_strReferenceType))
        {
            WEB3BaseException l_web3BaseException = null;

            // (*2-1) ����.���i�敪 != �h�M�p����h �̏ꍇ
            if (!WEB3EquityProductDivDef.MARGIN.equals(l_strProductType))
            {
                try
                {
                    //validate������t�X�e�C�^�X()
                    WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                    l_orderStatus.interestEquityFlag = true;
                }
                catch (WEB3BaseException l_bex)
                {
                    l_web3BaseException = l_bex;
                    log.debug("�Ɖ�敪 == �h��������ꗗ�h and ���i�敪 != �h�M�p����h �̏ꍇ�A" +
                            "validate������t�X�e�C�^�X���h��t�s�h�ł���", l_bex);
                    l_orderStatus.interestEquityFlag = false;
                }
                // reset������t�g�����U�N�V����(������t�g�����U�N�V���� : String)
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                        WEB3OrderAccTransactionDef.CANCEL);
                if (!l_orderStatus.interestEquityFlag)
                {
                    try
                    {
                        //validate������t�X�e�C�^�X()
                        WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                        l_orderStatus.interestEquityFlag = true;

                    }
                    catch (WEB3BaseException l_bex)
                    {
                        l_web3BaseException = l_bex;
                        log.debug("�Ɖ�敪 == �h��������ꗗ�h and ���i�敪 != �h�M�p����h �̏ꍇ�A" +
                                "validate������t�X�e�C�^�X���h��t�s�h�ł���", l_bex);
                        l_orderStatus.interestEquityFlag = false;
                    }                    
                }

                // (*2-2) ����.is����O�������{ == true �̏ꍇ
                if (l_blnIsOffFloorExecute)
                {
                    // reset������t���i(������t���i : String)
                    WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(
                        WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);

                    try
                    {
                        //validate������t�X�e�C�^�X( )
                        WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                        l_orderStatus.offFloor = true;
                    }
                    catch (WEB3BaseException l_bex)
                    {
                        l_web3BaseException = l_bex;
                        log.debug("�Ɖ�敪 == �h��������ꗗ�h and ����.is����O�������{ == true �̏ꍇ�A" +
                                "validate������t�X�e�C�^�X���h��t�s�h�ł���", l_bex);
                        l_orderStatus.offFloor = false;
                    }
                }
            }
            
            //  (*2-3) ����.���i�敪 != �h���������h and ����.is�M�p�q == true �̏ꍇ
            if (!WEB3EquityProductDivDef.EQUITY.equals(l_strProductType) &&
                    (l_blnIsMargin))
            {
                // reset������t���i(������t���i : String)
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(
                        WEB3OrderAccProductDef.MARGIN);

                // reset������t�g�����U�N�V����(������t�g�����U�N�V���� : String)
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                        WEB3OrderAccTransactionDef.CHANGE);

                try
                {
                    //validate������t�X�e�C�^�X( )
                    WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                    l_orderStatus.interestMarginFlag = true;
                }
                catch (WEB3BaseException l_bex)
                {
                    l_web3BaseException = l_bex;
                    log.debug("���i�敪 != �h���������h and ����.is�M�p�q == true �̏ꍇ�A" +
                            "validate������t�X�e�C�^�X���h��t�s�h�ł���", l_bex);
                    l_orderStatus.interestMarginFlag = false;
                }
                
                // reset������t�g�����U�N�V����(������t�g�����U�N�V���� : String)
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                        WEB3OrderAccTransactionDef.CANCEL);

                try
                {
                    //validate������t�X�e�C�^�X( )
                    WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                    l_orderStatus.interestMarginFlag = true;
                    l_orderStatus.swapFlag = true;
                }
                catch (WEB3BaseException l_bex)
                {
                    l_web3BaseException = l_bex;
                    log.debug("���i�敪 != �h���������h and ����.is�M�p�q == true �̏ꍇ�A" +
                            "validate������t�X�e�C�^�X���h��t�s�h�ł���", l_bex);
                    l_orderStatus.swapFlag = false;
                }
            }
            
            //�R�[���������ׂĂ� validate������t�X�e�C�^�X() �̌��ʂ��h��t�s�h�̏ꍇ�A
            //�i�R�[���������ׂĂ� validate������t�X�e�C�^�X() ����O���X���[�����ꍇ�A�j
            //��O���X���[����B
            //���u�ً}��~���v�̏ꍇ�A�u�ً}��~���v�̗�O���X���[
            //���u�o�b�`�������v�̏ꍇ�A�u�o�b�`�������v�̗�O���X���[
            if (l_orderStatus.interestEquityFlag == false &&
                l_orderStatus.offFloor == false &&
                l_orderStatus.interestMarginFlag == false &&
                !l_orderStatus.swapFlag)
            {

                // ������t�X�e�C�^�X���o�b�`��
                if (l_web3BaseException.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00011)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                        WEB3GentradeTradingTimeManagement.class.getName()
                            + "." + STR_METHOD_NAME);
                }
                // ������t�X�e�C�^�X���ً}��~��
                else if (l_web3BaseException.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00012)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                        WEB3GentradeTradingTimeManagement.class.getName()
                            + "." + STR_METHOD_NAME);
                }
                else
                {
                	throw new WEB3BusinessLayerException(
                		WEB3ErrorCatalog.BUSINESS_ERROR_02177,
                		this.getClass().getName() + STR_METHOD_NAME,
                    	"��������s�B");
                }
            }
        }
        return l_orderStatus;
    }

    /**
     * (create������薾��)<BR>
     * �\���ΏۂƂȂ钍����薾�ׂ𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����E�M�p�������Ɖ�j03.������薾�א����P�v<BR>
     * �u�i�����E�M�p�������Ɖ�j04.������薾�א����Q�v<BR>
     * �u�i�����E�M�p�������Ɖ�j05.������薾�א����R�v �Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * <BR>
     * @@param l_response - (���X�|���X)<BR>
     * ���X�|���X�f�[�^<BR>
     * <BR>
     * @@param l_orderStatus - (������t���)<BR>
     * ������t��ԃI�u�W�F�N�g<BR>
     * @@throws webbroker3.common.WEB3BaseException<BR>
     * @@roseuid 455D820402D6
     */
    protected void createExecuteGroup(WEB3EquityMarginExecuteReferenceRequest l_request,
            WEB3EquityMarginExecuteReferenceResponse l_response, OrderStatus l_orderStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".createExecuteGroup(" + "WEB3EquityMarginExecuteReferenceRequest,"
                + "WEB3EquityMarginExecuteReferenceResponse," + "OrderStatus)";
        log.entering(STR_METHOD_NAME);
        // get�⏕����()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

        // �g�����Z�I�u�W�F�N�g�}�l�[�W�����擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_objectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        // �g���g�����U�N�V�����}�l�[�W���I�u�W�F�N�g���擾����
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityFinTransactionManager l_objectFinTransactionManager = 
            (WEB3EquityFinTransactionManager) l_tradingModule.getFinTransactionManager();
        WEB3GentradeMarket l_market = null;
        WEB3EquityTradedProduct l_tradedProduct = null;
        Date l_datOpenDate = null;
        String l_strContractPrice = null;
        // create��������������
        String l_strSearchCond = this.createQueryString(l_request.productCode, l_request.marketCode,
                l_request.orderBizDate, l_orderStatus, l_request.orderCondType);
        // create���������f�[�^�R���e�i
        Object[] l_searchCondContainers = this.createQueryDataContainer(l_subAccount, l_request.productCode,
                l_request.marketCode, l_request.orderBizDate, l_orderStatus, l_request.orderCondType);
        // create�\�[�g����
        String l_strSortCond = this.createSortCond(l_request.sortKeys);
        // �g�����������}�l�[�W���I�u�W�F�N�g���擾����
        WEB3EquityOrderManager l_equityOrderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        // get�����P�ʈꗗ
        List l_lisRecords = l_equityOrderManager.getOrderUnits(l_subAccount, ProductTypeEnum.EQUITY, l_strSearchCond,
                l_searchCondContainers, l_strSortCond);
        if (l_lisRecords.isEmpty())
        {
            l_response.executeGroups = null;
            log.exiting(STR_METHOD_NAME);
            return;
        }

        Iterator l_itRecords = l_lisRecords.iterator();
        // ����.���N�G�X�g.����ԋ敪 != null �̏ꍇ
        if (!WEB3StringTypeUtility.isEmpty(l_request.execType))
        {
            while (l_itRecords.hasNext())
            {

                // is�w������ == false
                if (!this.isDesignateExecutedStatus(l_request.execType, (EqTypeOrderUnit) l_itRecords.next()))
                {
                    l_itRecords.remove();
                }
            }
            if (l_lisRecords.isEmpty())
            {
                l_response.executeGroups = null;
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }
        EqTypeOrderUnit[] l_arrOrderUnit = (EqTypeOrderUnit[]) 
        l_lisRecords.toArray(new EqTypeOrderUnit[l_lisRecords.size()]);
        // remove�J�z�������P��
        l_arrOrderUnit = l_equityOrderManager.removeCarryOverOriginalOrderUnit(l_arrOrderUnit);
        List l_lisOrderUnit = new ArrayList();
        List l_lisOrderUnitId = new ArrayList();
        List l_lisReferenceOrderUnit = new ArrayList();
        boolean l_blnReferenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_request.referenceType);

        for (int i = 0; i < l_arrOrderUnit.length; i++)
        {
            // get����X()
            WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();

            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_arrOrderUnit[i].getDataSourceObject();
            // �Y�������P�ʂ��M�p��������̏ꍇ
            // (�����P��.�����J�e�S�� == �h�V�K�������h or �h�ԍϒ����h or �h�����E���n�����h �̏ꍇ)
            boolean l_blnMarginTradeEnforcement = true;
            boolean l_blnMarginAccountEstablished = true;
            if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnitRow.getOrderCateg())
                    || OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnitRow.getOrderCateg())
                    || OrderCategEnum.SWAP_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
            {
                l_blnMarginTradeEnforcement = l_branch.isMarginTradeEnforcement(
                        l_orderUnitRow.getRepaymentType());
                // ����.���N�G�X�g.�Ɖ�敪 == �h��������ꗗ�h and �߂�l == false �̏ꍇ
                if (!l_blnMarginTradeEnforcement && l_blnReferenceType)
                {
                    continue;
                }
                // �ڋq�I�u�W�F�N�g���擾����
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
                // is�M�p�����J��
                l_blnMarginAccountEstablished = l_mainAccount.isMarginAccountEstablished(
                        l_orderUnitRow.getRepaymentType());
                // ����.���N�G�X�g.�Ɖ�敪 == �h��������ꗗ�h and �߂�l == false �̏ꍇ
                if (!l_blnMarginAccountEstablished && l_blnReferenceType)
                {
                    continue;
                }
            }
            // is��������\���ԑ�
            boolean l_blnIsChangeCancelEnableTimeZone = false;
            l_blnIsChangeCancelEnableTimeZone = this.isChangeCancelEnableTimeZone(l_arrOrderUnit[i]);
            // ����.���N�G�X�g.�Ɖ�敪 == �h��������ꗗ�h and �߂�l == false �̏ꍇ
            if (!l_blnIsChangeCancelEnableTimeZone && l_blnReferenceType)
            {
                continue;
            }
            // �����I�u�W�F�N�g���擾����
            WEB3EquityProduct l_product = (WEB3EquityProduct) (l_arrOrderUnit[i].getProduct());

            try
            {
                // �s��I�u�W�F�N�g���擾����
                l_market = (WEB3GentradeMarket) l_objectManager.getMarket(l_orderUnitRow.getMarketId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(), l_nfe);
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName()+ STR_METHOD_NAME, 
                        l_nfe.getMessage(), 
                        l_nfe);
            }
            boolean l_blnIsValidateTradedProduct = false;
            try
            {
                // validate�������
                l_tradedProduct = (WEB3EquityTradedProduct) l_equityOrderManager.validateTradedProduct(l_product,
                        l_market);
            }
            catch (WEB3BaseException l_web3BaseException)
            {
                l_blnIsValidateTradedProduct = true;
            }
            // ����.���N�G�X�g.�Ɖ�敪 == �h��������ꗗ�h and ��O���X���[���ꂽ�ꍇ
            if (l_blnIsValidateTradedProduct && l_blnReferenceType)
            {
                continue;
            }


            // ���������̒����̏ꍇ
            boolean l_blnIsValidateHandlingMarketMargin = false;
            boolean l_blnIsValidateHandlingMarket = false;
            if (OrderCategEnum.ASSET.equals(l_orderUnitRow.getOrderCateg()))
            {
                try
                {
                    // validate�戵�\�s��
                    this.validateHandlingMarket(l_branch, l_tradedProduct);
                }
                catch (WEB3BaseException l_web3BaseException)
                {
                    l_blnIsValidateHandlingMarket = true;
                }
                // ����.���N�G�X�g.�Ɖ�敪 == �h��������ꗗ�h and ��O���X���[���ꂽ�ꍇ
                if (l_blnIsValidateHandlingMarket && l_blnReferenceType)
                {
                    continue;
                }
            }
            else
            {
                try
                {
                    // validate�戵�\�s��i�M�p�j
                    l_equityOrderManager.validateHandlingMarket(l_branch, l_tradedProduct, l_market.getMarketCode(),
                            l_orderUnitRow.getRepaymentType(), l_orderUnitRow.getRepaymentNum());
                }
                catch (WEB3BaseException l_web3BaseException)
                {
                    l_blnIsValidateHandlingMarketMargin = true;
                }
                // ����.���N�G�X�g.�Ɖ�敪 == �h��������ꗗ�h and ��O���X���[���ꂽ�ꍇ
                if (l_blnIsValidateHandlingMarketMargin && l_blnReferenceType)
                {
                    continue;
                }

            }
            boolean l_blnIsValidateOrderForChangeability = false;
            try
            {
                // validate���������\���
                if (l_blnMarginTradeEnforcement 
                    && l_blnMarginAccountEstablished 
                    && l_blnIsChangeCancelEnableTimeZone
                    && !l_blnIsValidateTradedProduct 
                    && !l_blnIsValidateHandlingMarket 
                    && !l_blnIsValidateHandlingMarketMargin)
                {
                    this.validateOrderForChangeability(l_subAccount, l_product, l_market, l_branch, l_arrOrderUnit[i]);
                }
                else
                {
                    l_blnIsValidateOrderForChangeability = true;
                }
            }
            catch (WEB3BaseException l_web3BaseException)
            {
                l_blnIsValidateOrderForChangeability = true;
            }
            boolean l_blnIsValidateOrderForCancel = false;
            try
            {
                // validate��������\���
                if (l_blnMarginTradeEnforcement 
                    && l_blnMarginAccountEstablished 
                    && l_blnIsChangeCancelEnableTimeZone
                    && !l_blnIsValidateTradedProduct 
                    && !l_blnIsValidateHandlingMarket 
                    && !l_blnIsValidateHandlingMarketMargin)
                {
                    this.validateOrderForCancellation(l_arrOrderUnit[i].getOrder(), l_market);
                }
                else
                {
                    l_blnIsValidateOrderForCancel = true;
                }
            }
            catch (WEB3BaseException l_web3BaseException)
            {
                l_blnIsValidateOrderForCancel = true;
            }
          
            // ����O���������̏ꍇ
            boolean l_blnIsSalesOutSideMarket = 
                WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(
                    l_orderUnitRow.getSonarTradedCode());
            boolean l_blnIsOrderRootDiv = false;
            boolean l_blnIsValidateOffFloorOrderPossible = false;
            boolean l_blnIs = false; 
            // �Y�������P��.�����o�H�敪 == �hHOST�h �̏ꍇ
            if (l_blnIsSalesOutSideMarket)
            {

                if (WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
                {
                    l_blnIsOrderRootDiv = true;
                    l_blnIs = true;
                }
                try
                {
                    // validate����O������t�\
                    l_equityOrderManager.validateOffFloorOrderPossible(l_orderUnitRow.getProductId(), 
                            l_orderUnitRow.getMarketId(), l_subAccount);
                }
                catch (WEB3BaseException l_web3BaseException)
                {
                    l_blnIsValidateOffFloorOrderPossible = true;
                    l_blnIs = true;
                }
            }
           
            // ����.���N�G�X�g.�Ɖ�敪 == �h��������ꗗ�h and
            // �iis��������\���ԑ�()�̖߂�l == false 
            //   or�ivalidate���������\���()�̃`�F�b�N���ʂ�NG�i��O���X���[�j and validate��������\���()�̃`�F�b�N���ʂ�NG�i��O���X���[�j�j
            //   or (�����P�ʍs�I�u�W�F�N�g.�����o�H�敪 == �hHOST�h and ����O���������̏ꍇ)
            //   or (validate����O������t�\()�̃`�F�b�N���ʂ�NG�i��O���X���[) and ����O���������̏ꍇ)
            //   )
            if(l_blnReferenceType && 
                (!l_blnIsChangeCancelEnableTimeZone 
                   || (l_blnIsValidateOrderForChangeability && l_blnIsValidateOrderForCancel)
                   || (l_blnIsOrderRootDiv && l_blnIsSalesOutSideMarket)
                   || (l_blnIsValidateOffFloorOrderPossible && l_blnIsSalesOutSideMarket)))

            {
                continue;
            }

            WEB3EquityMarginExecuteGroup l_marginExecuteGroup = new WEB3EquityMarginExecuteGroup();
            l_marginExecuteGroup.id = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrderId());
            EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_product.getDataSourceObject();
            l_marginExecuteGroup.productCode = l_eqtypeProductRow.getProductCode();
            l_marginExecuteGroup.productName = l_eqtypeProductRow.getStandardName();
            l_marginExecuteGroup.marketCode = l_market.getMarketCode();

            if (l_blnIsChangeCancelEnableTimeZone && !l_blnIsValidateOrderForChangeability)
            {
                l_marginExecuteGroup.changeFlag = true;
            }
            else
            {
                l_marginExecuteGroup.changeFlag = false;
            }
            if (l_blnIsChangeCancelEnableTimeZone && !(l_blnIsValidateOrderForCancel || l_blnIs))
            {
                l_marginExecuteGroup.cancelFlag = true;
            }
            else
            {
                l_marginExecuteGroup.cancelFlag = false;
            }
            WEB3EquityMarginOrderIdUnit l_marginOrderIdUnit = new WEB3EquityMarginOrderIdUnit();
            l_marginOrderIdUnit.id = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrderId());
            l_marginOrderIdUnit.productDiv = WEB3EquityDataAdapter.getProductType(l_arrOrderUnit[i]);
            l_lisOrderUnit.add(l_arrOrderUnit[i]);
            l_lisOrderUnitId.add(l_marginOrderIdUnit);
            l_lisReferenceOrderUnit.add(l_marginExecuteGroup);
        }

        // �\���M�p�������Ɖ���P�ʑΏۍs�̒��o
        // �v���y�[�W�ԍ�
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        // �y�[�W���\���s��
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_referenceOrderUnitPageIndexInfo = new WEB3PageIndexInfo(
                (WEB3EquityMarginExecuteGroup[]) 
                l_lisReferenceOrderUnit.toArray(new WEB3EquityMarginExecuteGroup[l_lisReferenceOrderUnit.size()]), l_intPageIndex,
                l_intPageSize);

        WEB3EquityMarginExecuteGroup[] l_arrReferenceOrderUnit = (WEB3EquityMarginExecuteGroup[]) 
        l_referenceOrderUnitPageIndexInfo.getArrayReturned(WEB3EquityMarginExecuteGroup.class);

        // �\���M�p�����P�ʑΏۍs�̒��o
        WEB3PageIndexInfo orderUnitl_pageIndexInfo = new WEB3PageIndexInfo(
                (EqTypeOrderUnit[]) 
                l_lisOrderUnit.toArray(new EqTypeOrderUnit[l_lisOrderUnit.size()]), l_intPageIndex, l_intPageSize);
        l_arrOrderUnit = (EqTypeOrderUnit[]) orderUnitl_pageIndexInfo.getArrayReturned(EqTypeOrderUnit.class);

        for (int i = 0; i < l_arrOrderUnit.length; i++)
        {
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_arrOrderUnit[i].getDataSourceObject();
            try
            {
                // �s��I�u�W�F�N�g���擾����
                l_market = (WEB3GentradeMarket) l_objectManager.getMarket(l_orderUnitRow.getMarketId());

            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(), l_nfe);
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName()+ STR_METHOD_NAME, 
                        l_nfe.getMessage(), 
                        l_nfe);
            }
            // reset�s��R�[�h
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());
            // reset��t���ԋ敪
            // ����O�����̏ꍇ
            if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET);
            }
            // �������n�̏ꍇ
            else if (WEB3TransactionTypeSONARDef.SWAP_CONTRACT.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
            }
            // ����ȊO �̏ꍇ
            else
            {
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            }
            // get�����敪
            // �ŋ敪�i�����E���n�����j

            String l_strSwapTaxType = null;
            if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
            {
                l_strSwapTaxType = WEB3EquityDataAdapter.getTaxType(l_orderUnitRow.getSwapTaxType());
            }
            // �M�p�������Ɖ���P��.�����敪�i�����E���n�j= get�����敪()�̖߂�l
            l_arrReferenceOrderUnit[i].swapTaxType = l_strSwapTaxType;
            
            // �M�p�������Ɖ���P��.�����敪 = get�����敪
            l_arrReferenceOrderUnit[i].taxType = 
                WEB3EquityDataAdapter.getTaxType(l_orderUnitRow.getTaxType());
            
            // get����敪
            String l_strTradingType = 
                WEB3EquityDataAdapter.getTradingType(l_orderUnitRow.getOrderType());
            // �M�p�������Ɖ���P��.���s���� = get���s�����iSONAR�j
            l_arrReferenceOrderUnit[i].execCondType = 
                l_equityOrderManager.getExecutionConditionTypeSonar(
                    l_orderUnitRow.getExecutionConditionType());
            // �M�p�������Ɖ���P��.������ = get�����󋵋敪()�̖߂�l
            l_arrReferenceOrderUnit[i].transactionStateType = 
                WEB3EquityDataAdapter.getTransactionStatusType(l_arrOrderUnit[i]);

            // �����P��.�������� == �hW�w�l�h�̏ꍇ
            String l_strExecutionConditionTypeSonar = null;
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
            {
                // get���s�����iSONAR�j()�iW�w�l�p�j
                l_strExecutionConditionTypeSonar = l_equityOrderManager.getExecutionConditionTypeSonar(
                    l_orderUnitRow.getWLimitExecCondType());
            }

            // getW�w�l�p�L����ԋ敪()
            String l_strWLimitEnableStatusDiv =
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_arrOrderUnit[i]);

            //getW�w�l�p�֑ؑO�����P��()
            String l_strWLimitBefSwitchPrice =
                WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_arrOrderUnit[i]);

            //getW�w�l�p�֑ؑO���s����()
            String l_strWLimitBefSwitchExecCondType =
                WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_arrOrderUnit[i]);

            //get��W�w�l�p�����P���敪()
            String l_strOrgWLimitOrderPriceDiv =
                WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_arrOrderUnit[i]);

            // get��W�w�l�p�����P��()
            String l_strOrgWLimitOrderPrice = null;
            // ��W�w�l�p�����P���敪 == �h�w�l�h �̏ꍇ�̂�
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
            {
                l_strOrgWLimitOrderPrice = WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_arrOrderUnit[i]);
            }

            // get��W�w�l�p���s����()
            String l_strOrgWLimitExecCondType =
                WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_arrOrderUnit[i]);

            //get�x���敪()
            String l_strDelayDiv =
                WEB3EquityDataAdapter.getDelayDiv(l_arrOrderUnit[i]);

            //is�蓮�����\()
            boolean l_blnManualOrderPossible = l_equityOrderManager.isManualOrderPossible(l_arrOrderUnit[i]);

            // isUnexecuted()�̖߂�l == false �̏ꍇ
            String l_strEstimatedProfitLoss = null;
            WEB3EquityMarginExecuteUnit[] l_arrMarginExecuteUnit = null;
            double l_dblEstimateDeliveryAmountForClose = 0;
            if (!(l_arrOrderUnit[i].isUnexecuted()))
            {
                // get��������n���
                l_dblEstimateDeliveryAmountForClose = 
                    l_equityOrderManager.getEstimateDeliveryAmountForClose(l_arrOrderUnit[i]);
                // �����������̏ꍇ
                if (OrderTypeEnum.EQUITY_SELL.equals(l_orderUnitRow.getOrderType()))
                {
                    // get�g�����U�N�V����
                    List l_lisReturn = l_objectFinTransactionManager.getTransactions(l_arrOrderUnit[i]);
                    if (!l_lisReturn.isEmpty())
                    {
                        // get�T�Z���v
                        l_strEstimatedProfitLoss = l_objectFinTransactionManager.getEstimatedProfitLoss(l_lisReturn);
                    }

                }

                // create�������ꗗ
                l_arrMarginExecuteUnit = this.createExecuteUnits(l_arrOrderUnit[i]);
                // �M�p�������Ɖ���P��.��芔�� = �����P��.��萔��

                double l_dblExecutedQuantity = l_orderUnitRow.getExecutedQuantity();
                l_arrReferenceOrderUnit[i].execQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
                BigDecimal l_bdExecutedQuantity = new BigDecimal("" + l_dblExecutedQuantity);

                double l_dblExecutedAmount = l_orderUnitRow.getExecutedAmount();
                BigDecimal l_bdExecutedAmount = new BigDecimal("" + l_dblExecutedAmount);
                // �M�p�������Ɖ���P��.���P�� = �����P��.���v�����z / �����P��.��萔��
                l_arrReferenceOrderUnit[i].execPrice = WEB3StringTypeUtility.formatNumber(l_bdExecutedAmount.divide(
                        l_bdExecutedQuantity, 0, BigDecimal.ROUND_HALF_UP).doubleValue());
                // �M�p�������Ɖ���P��.��n��� = get��������n���()�̖߂�l
                l_arrReferenceOrderUnit[i].deliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblEstimateDeliveryAmountForClose);
            }

            WEB3MarginRepaymentUnit l_repayment = null;
            // �Y�������P�ʂ��M�p��������̏ꍇ
            // (�����P��.�����J�e�S�� == �h�V�K�������h or �h�ԍϒ����h or �h�����E���n�����h �̏ꍇ)
            if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnitRow.getOrderCateg())
                    || OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnitRow.getOrderCateg())
                    || OrderCategEnum.SWAP_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
            {
                l_repayment = new WEB3MarginRepaymentUnit();
                l_repayment.repaymentDiv = l_orderUnitRow.getRepaymentType();
                l_repayment.repaymentTimeLimit = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getRepaymentNum());
                // �M�p�������Ɖ���P��.�ٍ� = l_repayment;
                l_arrReferenceOrderUnit[i].repayment = l_repayment;
                // get����(�����P��)
                l_datOpenDate = this.getOpenDate(l_arrOrderUnit[i]);
                // �M�p�������Ɖ���P��.���� = get����(�����P��);
                l_arrReferenceOrderUnit[i].openDate = l_datOpenDate;
                // get���P��(�����P��)
                l_strContractPrice = this.getContractPrice(l_arrOrderUnit[i]);
                // �M�p�������Ɖ���P��.���P�� = get���P��(�����P��);
                l_arrReferenceOrderUnit[i].contractPrice = l_strContractPrice;
            }

            if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                // �M�p�������Ɖ���P��.����敪 =
                // WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET;
                l_arrReferenceOrderUnit[i].tradingType = WEB3TradingTypeDef.PRESENCE_ORDER;

            }
            else
            {
                l_arrReferenceOrderUnit[i].tradingType = l_strTradingType;
            }

            // �M�p�������Ɖ���P��.�l�i���� = l_orderUnitRow.getPriceConditionType();
            l_arrReferenceOrderUnit[i].priceCondType = l_orderUnitRow.getPriceConditionType();

            // �M�p�������Ɖ���P��.���������敪 = l_orderUnitRow.getOrderConditionType();
            l_arrReferenceOrderUnit[i].orderCondType = l_orderUnitRow.getOrderConditionType();
            // �����P��.�������� == �h�t�w�l�h �̏ꍇ�̂݁B
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
            {
                // �M�p�������Ɖ���P��.�t�w�l�p���������P�� =  �����P��.�t�w�l��l;
                if (l_orderUnitRow.getStopOrderPriceIsNull())
                {
                    l_arrReferenceOrderUnit[i].stopOrderCondPrice = null;
                }
                else
                {
                    l_arrReferenceOrderUnit[i].stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                            l_orderUnitRow.getStopOrderPrice());
                }
                    
                // �M�p�������Ɖ���P��.�t�w�l�p�����������Z�q =
                // l_orderUnitRow.getOrderCondOperator();
                l_arrReferenceOrderUnit[i].stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
            }
            // �����P��.�������� == �hW�w�l�h�̏ꍇ
            String l_strOrderConditionType = l_orderUnitRow.getOrderConditionType();
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                // �M�p�������Ɖ���P��.W�w�l�p���������P�� = l_orderUnitRow.getStopOrderPrice();
                if (l_orderUnitRow.getStopOrderPriceIsNull())
                {
                    l_arrReferenceOrderUnit[i].wlimitOrderCondPrice = null;
                }
                else
                {
                    l_arrReferenceOrderUnit[i].wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                            l_orderUnitRow.getStopOrderPrice());
                }
                // �M�p�������Ɖ���P��.�t�w�l�p�����������Z�q =
                // l_orderUnitRow.getOrderCondOperator();
                l_arrReferenceOrderUnit[i].wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
                // �����P��.�iW�w�l�j�����w�l == 0 �̏ꍇ
                double l_dblWLimitPrice = l_orderUnitRow.getWLimitPrice();
                if (l_dblWLimitPrice == 0)
                {
                    // �M�p�������Ɖ���P��.W�w�l�p�����P���敪 = ���s
                    l_arrReferenceOrderUnit[i].wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {
                    // �M�p�������Ɖ���P��.W�w�l�p�����P���敪 = �w�l
                    l_arrReferenceOrderUnit[i].wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    // �M�p�������Ɖ���P��.W�w�l�p�����P�� = �����P��.�iW�w�l�j�����w�l
                        l_arrReferenceOrderUnit[i].wLimitPrice = WEB3StringTypeUtility.formatNumber(
                                l_orderUnitRow.getWLimitPrice());
                }
            }
            // �M�p�������Ɖ���P��.W�w�l�p���s���� = get���s�����iSONAR�j()�iW�w�l�p�j�̖߂�l
            l_arrReferenceOrderUnit[i].wlimitExecCondType = l_strExecutionConditionTypeSonar;
            // �M�p�������Ɖ���P��.W�w�l�p�L����ԋ敪 = getW�w�l�p�L����ԋ敪()
            l_arrReferenceOrderUnit[i].wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
            // �M�p�������Ɖ���P��.W�w�l�p�֑ؑO�����P�� = getW�w�l�p�֑ؑO�����P��()�̖߂�l
            l_arrReferenceOrderUnit[i].wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;
            // �M�p�������Ɖ���P��.W�w�l�p�֑ؑO���s���� = getW�w�l�p�֑ؑO���s����()�̖߂�l
            l_arrReferenceOrderUnit[i].wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;
            // �M�p�������Ɖ���P��.�����������敪 = �����P��.����������
            l_arrReferenceOrderUnit[i].orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();
            // �M�p�������Ɖ���P��.�����������P�� = �����P��.�����������P��
            if(l_orderUnitRow.getOrgStopOrderPriceIsNull())
            {
                l_arrReferenceOrderUnit[i].orgOrderCondPrice = null;
            }
            else
            {
                l_arrReferenceOrderUnit[i].orgOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                        l_orderUnitRow.getOrgStopOrderPrice());
            }
            // �M�p�������Ɖ���P��.�������������Z�q = �����P��.�������������Z�q
            l_arrReferenceOrderUnit[i].orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();
            // �M�p�������Ɖ���P��.��W�w�l�p�����P���敪 = get��W�w�l�p�����P���敪
            l_arrReferenceOrderUnit[i].orgWlimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;
            // �M�p�������Ɖ���P��.��W�w�l�p�����P�� = get��W�w�l�p�����P��
            l_arrReferenceOrderUnit[i].orgWlimitPrice = l_strOrgWLimitOrderPrice;
            // �M�p�������Ɖ���P��.��W�w�l�p���s���� = get��W�w�l�p���s����
            l_arrReferenceOrderUnit[i].orgWlimitExecCondType = l_strOrgWLimitExecCondType;
            // �M�p�������Ɖ���P��.�������� = �����P��.��������
            l_arrReferenceOrderUnit[i].orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getQuantity());
            // �����P��.isMarketOrder()�̖߂�l == true �̏ꍇ
            boolean l_blnMarketOrder = l_arrOrderUnit[i].isMarketOrder();
            if (l_blnMarketOrder)
            {
                // �M�p�������Ɖ���P��.�����P���敪 = ���s
                l_arrReferenceOrderUnit[i].orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                // �M�p�������Ɖ���P��.�����P���敪 = �w�l
                l_arrReferenceOrderUnit[i].orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
            // �M�p�������Ɖ���P��.�����P���敪 == �w�l
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_arrReferenceOrderUnit[i].orderPriceDiv))
            {
                // �M�p�������Ɖ���P��.�����P�� = �����P��.�w�l
                if (l_orderUnitRow.getPriceIsNull())
                {
                    l_arrReferenceOrderUnit[i].limitPrice = null;
                }
                else
                {
                    l_arrReferenceOrderUnit[i].limitPrice = WEB3StringTypeUtility.formatNumber(
                            l_orderUnitRow.getLimitPrice());
                }
            }

            // �M�p�������Ɖ���P��.�T�Z��n��� = �����P��.�T�Z��n���
            l_arrReferenceOrderUnit[i].estimatedPrice = 
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());

            // �M�p�������Ɖ���P��.�T�Z���v = get�T�Z���v
            l_arrReferenceOrderUnit[i].estimatedProfitLoss = l_strEstimatedProfitLoss;
            // �M�p�������Ɖ���P��.�������� = �����P��.�쐬���t
            l_arrReferenceOrderUnit[i].orderDate = l_orderUnitRow.getCreatedTimestamp();
            // �M�p�������Ɖ���P��.������ = �����P��.������
            l_arrReferenceOrderUnit[i].orderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            // �o����܂Œ����̏ꍇ
            boolean l_blnCarriedOrderUnit = l_equityOrderManager.isCarriedOrderUnit(l_arrOrderUnit[i]);
            if (l_blnCarriedOrderUnit)
            {
                // �M�p�������Ɖ���P��.�����L����
                l_arrReferenceOrderUnit[i].expirationDate = l_orderUnitRow.getExpirationDate();
            }

            // �M�p�������Ɖ���P��.�����`���l�� = �����P��.���񒍕��̒����`���l��
            l_arrReferenceOrderUnit[i].orderChannel = l_orderUnitRow.getOrderChanel();
            // �M�p�������Ɖ���P��.�����o�H�敪 = �����P��.�����o�H�敪
            l_arrReferenceOrderUnit[i].orderRootDiv = l_orderUnitRow.getOrderRootDiv();
            try
            {
                WEB3GentradeTrader l_trader = (WEB3GentradeTrader) l_objectManager.getTrader(l_orderUnitRow
                        .getTraderId());
                // �M�p�������Ɖ���P��.�I�y���[�^�R�[�h = �߂�l.���҃R�[�h
                l_arrReferenceOrderUnit[i].operatorCode = l_trader.getTraderCode();
            }
            catch (NotFoundException l_ex)
            {
                // �M�p�������Ɖ���P��.�I�y���[�^�R�[�h = null
                l_arrReferenceOrderUnit[i].operatorCode = null;
            }
            // ��肪����ꍇ : �����P��.isUnexecuted()�̖߂�l == false �̏ꍇ
            if (!(l_arrOrderUnit[i].isUnexecuted()))
            {
                // �M�p�������Ɖ���P��.�������ꗗ
                l_arrReferenceOrderUnit[i].executeUnits = l_arrMarginExecuteUnit;
            }
            // �M�p�������Ɖ���P��.�x���敪 = get�x���敪()�̖߂�l
            l_arrReferenceOrderUnit[i].delayDiv = l_strDelayDiv;
            // �M�p�������Ɖ���P��.�蓮�����\�t���O = is�蓮�����\()�̖߂�l
            l_arrReferenceOrderUnit[i].manualFlag = l_blnManualOrderPossible;
            // �������ϗ��R
            l_arrReferenceOrderUnit[i].forcedSettleReason = l_orderUnitRow.getForcedSettleReasonType();
            // ���������敪
            l_arrReferenceOrderUnit[i].forcedLapseDiv = l_orderUnitRow.getForcedExpireType();
        }
        int l_intOrderUnitPageIndex = orderUnitl_pageIndexInfo.getPageIndex();
        int l_intTotalPages = orderUnitl_pageIndexInfo.getTotalPages();
        int l_intTotalRecords = orderUnitl_pageIndexInfo.getTotalRecords();

        WEB3EquityMarginOrderIdUnit[] l_arrOrderUnitId = (WEB3EquityMarginOrderIdUnit[]) 
        l_lisOrderUnitId.toArray(new WEB3EquityMarginOrderIdUnit[l_lisOrderUnitId.size()]);
        // �������ꗗ
        l_response.executeGroups = l_arrReferenceOrderUnit;
        // ID�ꗗ
        l_response.idList = l_arrOrderUnitId;
        // �\���y�[�W�ԍ�
        l_response.pageIndex = String.valueOf(l_intOrderUnitPageIndex);
        // ���y�[�W��
        l_response.totalPages = String.valueOf(l_intTotalPages);
        // �����R�[�h��
        l_response.totalRecords = String.valueOf(l_intTotalRecords);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create��������������)<BR>
     * ���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * �P�j�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�������������P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �Q�|�P�j����.������ != null �i�������w��j�̏ꍇ<BR>
     * <BR> " biz_date = ?"<BR>
     * <BR>
     * �Q�|�Q�j����.������ == null �i�������w��Ȃ��j�̏ꍇ<BR>
     * <BR> " biz_date >= ?"<BR>
     * <BR>
     * �R�j������ʏ�����ǉ�����B<BR>
     * <BR>
     * �R�|�P�j<BR>
     * n = 0 �Ƃ���B<BR>
     * <BR>
     * �R�|�Q�j<BR>
     * ����.������t���.���������t���O == true or<BR>
     * ����.������t���.����O�����t���O == true �̏ꍇ<BR>
     * <BR>
     * n = n + 2 �Ƃ���B<BR>
     * <BR>
     * �R�|�R�j<BR>
     * ����.������t���.�M�p����t���O == true �̏ꍇ<BR>
     * <BR>
     * n = n + 4 �Ƃ���B<BR>
     * <BR>
     * �R�|�S�j<BR>
     * ����.������t���.�����E���n�t���O == true �̏ꍇ<BR>
     * <BR>
     * n = n + 2 �Ƃ���B<BR>
     * <BR>
     * �R�|�T�j<BR>
     * �ȉ��̕�������P�j�̕�����ɒǉ�����B<BR>
     * <BR> " and order_type in (?, ?, ...)"<BR>
     * <BR>
     * ��"?"�̐��͂��ɂȂ�悤�ɂ���B<BR>
     * <BR>
     * ��n=0�̃p�^�[���͂����ł͗L�蓾�Ȃ��B�ivalidate������t���()�ŗ�O�ɂȂ��Ă���? ���j<BR>
     * <BR>
     * �S�j����R�[�h�iSONAR)������ǉ�����B<BR>
     * <BR>
     * �S�|�P�j<BR>
     * n = 0 �Ƃ���B<BR>
     * <BR>
     * �S�|�Q�j<BR>
     * ����.������t���.���������t���O == true �̏ꍇ<BR>
     * <BR>
     * n = n + 1 �Ƃ���B<BR>
     * <BR>
     * �S�|�R�j<BR>
     * ����.������t���.����O�����t���O == true �̏ꍇ<BR>
     * <BR>
     * n = n + 1 �Ƃ���B<BR>
     * <BR>
     * �S�|�S�j<BR>
     * ����.������t���.�M�p����t���O == true �̏ꍇ<BR>
     * <BR>
     * n = n + 2 �Ƃ���B<BR>
     * <BR>
     * �S�|�T�j<BR>
     * ����.������t���.�����E���n�t���O == true �̏ꍇ<BR>
     * <BR>
     * n = n + 1 �Ƃ���B<BR>
     * <BR>
     * �S�|�U�j<BR>
     * �ȉ��̕�������P�j�̕�����ɒǉ�����B<BR>
     * <BR> " and sonar_traded_code in (?, ?, ...)"<BR>
     * <BR>
     * ��"?"�̐��͂��ɂȂ�悤�ɂ���B<BR>
     * <BR>
     * ��n=0�̃p�^�[���͂����ł͗L�蓾�Ȃ��B�ivalidate������t���()�ŗ�O�ɂȂ��Ă���͂��j<BR>
     * ���j<BR>
     * <BR>
     * �T�j����.�����R�[�h != null �i�����R�[�h�w��j�̏ꍇ�A�����w��������P�j�̕�����ɒǉ�����B<BR>
     * �i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
     * <BR> " and product_id = ?"<BR>
     * <BR>
     * �U�j����.�s��R�[�h != null �i�s��R�[�h�w��j�̏ꍇ�A�s��w��������P�j�̕�����ɒǉ�����B<BR>
     * �i�s��R�[�h�ɑΉ�����s��ID�Ō������s��)<BR>
     * <BR> " and market_id = ?"<BR>
     * <BR>
     * �V�j����.���������敪!=null�i���������敪�w��j�̏ꍇ�A<BR>
     * ���������敪�w��������P�j�̕�����ɒǉ�����B<BR>
     *�i���������P�ʃe�[�u��.�����������ɒl���ݒ肳��Ă���ꍇ�́A<BR>
     * ���������������ƂɌ�������B<BR>
     * �����������ɒl���ݒ肳��Ă��Ȃ��ꍇ�́A<BR>
     * ���������P�ʃe�[�u��.�������������ƂɌ�������B) <BR>
     * <BR>
     * �@@" and nvl(org_order_condition_type,order_condition_type) = ?"<BR>
     * <BR>  
     * �W�j���������������ԋp����B<BR>
     * <BR>
     * 
     * @@param l_strProductCode -
     *            (�����R�[�h)<BR>
     *            �����R�[�h<BR>
     *            <BR>
     * @@param l_strMarketCode -
     *            (�s��R�[�h)<BR>
     *            �s��R�[�h<BR>
     *            <BR>
     * @@param l_datOrderBizDate -
     *            (������)<BR>
     *            ������<BR>
     *            <BR>
     * @@param l_orderStatus -
     *            (������t���)<BR>
     *            ������t��ԃI�u�W�F�N�g<BR>
     *            <BR>
     * @@param l_strOrderConditionDiv -
     *            (���������敪)<BR>
     *            ���������敪<BR>
     *            <BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 455D8AA00369
     */
    protected String createQueryString(String l_strProductCode,
        String l_strMarketCode,
        Date l_datOrderBizDate,
        OrderStatus l_orderStatus,
        String l_strOrderConditionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".createQueryString(String, String, Date, OrderStatus, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�����
        StringBuffer l_sbWhere = new StringBuffer();

        // �Q�j�������������P�j�̕�����ɒǉ�����
        // �Q�|�P�j����.������ != null �i�������w��j�̏ꍇ
        if (l_datOrderBizDate != null)
        {
            log.debug("�������w��̏ꍇ");
            l_sbWhere.append(" biz_date = ?");
        }
        else
        {
            // �Q�|�Q�j����.������ == null �i�������w��Ȃ��j�̏ꍇ
            log.debug("�������w��Ȃ��̏ꍇ");
            l_sbWhere.append(" biz_date >= ?");
        }

        // �R�j������ʏ�����ǉ�����B
        // �R�|�P�jn = 0 �Ƃ���B
        int l_intForOrderTypeCnt = 0;

        // �S�j����R�[�h�iSONAR)������ǉ�����
        // �S�|�P�jm = 0 �Ƃ���
        int l_intForTradedCodeCnt = 0;

        if (l_orderStatus == null)
        {
            log.debug("������t��Ԃ�null�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if (l_orderStatus.interestEquityFlag || l_orderStatus.offFloor)
        {
            // ����.������t���.���������t���O == true or
            // ����.������t���.����O�����t���O == true �̏ꍇ
            l_intForOrderTypeCnt += 2;
        }
        if (l_orderStatus.interestEquityFlag)
        {
            // ����.������t���.���������t���O == true �̏ꍇ
            l_intForTradedCodeCnt += 1;
        }
        if (l_orderStatus.offFloor)
        {
            // ����.������t���.����O�����t���O == true �̏ꍇ
            l_intForTradedCodeCnt += 1;
        }
        if (l_orderStatus.interestMarginFlag)
        {
            // ����.������t���.�M�p����t���O == true �̏ꍇ
            l_intForOrderTypeCnt += 4;
            l_intForTradedCodeCnt += 2;
        }
        if (l_orderStatus.swapFlag)
        {
            // ����.������t���.�����E���n�t���O == true �̏ꍇ
            l_intForOrderTypeCnt += 2;
            l_intForTradedCodeCnt += 1;
        }
        // �ȉ��̕�������P�j�̕�����ɒǉ�����B
        // " and order_type in (?, ?, ...)"
        // ��"?"�̐��͂��ɂȂ�悤�ɂ���B
        // ��n=0�̃p�^�[���͂����ł͗L�蓾�Ȃ��B�ivalidate������t���()�ŗ�O�ɂȂ��Ă���͂��j
        for (int i = 0; i < l_intForOrderTypeCnt; i++)
        {
            if (i == 0)
            {
                l_sbWhere.append(" and order_type in (");
            }
            l_sbWhere.append("?");
            if ((i + 1) == l_intForOrderTypeCnt)
            {
                l_sbWhere.append(")");
            }
            else
            {
                l_sbWhere.append(", ");
            }
        }

        // �ȉ��̕�������P�j�̕�����ɒǉ�����B
        // " and sonar_traded_code in (?, ?, ...)"
        // ��"?"�̐��͂��ɂȂ�悤�ɂ���B
        // ����=0�̃p�^�[���͂����ł͗L�蓾�Ȃ��B�ivalidate������t���()�ŗ�O�ɂȂ��Ă���͂��j
        for (int j = 0; j < l_intForTradedCodeCnt; j++)
        {
            if (j == 0)
            {
                l_sbWhere.append(" and sonar_traded_code in (");
            }
            l_sbWhere.append("?");
            if ((j + 1) == l_intForTradedCodeCnt)
            {
                l_sbWhere.append(")");
            }
            else
            {
                l_sbWhere.append(", ");
            }
        }

        if (l_strProductCode != null)
        {
            log.debug("�����R�[�h != null�̏ꍇ");
            // �T�j����.�����R�[�h != null �i�����R�[�h�w��j�̏ꍇ�A�����w��������P�j�̕�����ɒǉ�����B
            l_sbWhere.append(" and product_id = ?");
        }
        if (l_strMarketCode != null)
        {
            log.debug("�s��R�[�h != null�̏ꍇ");
            // �U�j����.�s��R�[�h != null �i�s��R�[�h�w��j�̏ꍇ�A�s��w��������P�j�̕�����ɒǉ�����B
            l_sbWhere.append(" and market_id = ?");
        }

        // �V�j����.���������敪!=null�i���������敪�w��j�̏ꍇ
        if (l_strOrderConditionDiv != null)
        {
            l_sbWhere.append(" and nvl(org_order_condition_type,order_condition_type) = ?");
        }

        // �W�j���������������ԋp����B
        String l_strQueryString = l_sbWhere.toString();
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }


    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������ɃZ�b�g����l�̔z��𐶐�����B<BR>
     * <BR>
     * �P�j���X�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�������w��l���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �Q�|�P�j����.������ != null �i�������w��j�̏ꍇ<BR>
     * <BR>
     * �������w��l �� �p�����[�^.������<BR>
     * <BR>
     * �Q�|�Q�j����.������ == null �i�������w��Ȃ��j�̏ꍇ<BR>
     * <BR>
     * �������w��l �� �Ɩ����t(*)<BR>
     * <BR>
     * (*)GtlUtils.getTradingSystem( ).getBizDate( ) <BR>
     * <BR>
     * �R�j������ʂ̐ݒ�l��ǉ�����B<BR>
     * <BR>
     * �R�|�P�j<BR>
     * ����.������t���.���������t���O == true or<BR>
     * ����.������t���.����O�����t���O == true �̏ꍇ<BR>
     * <BR>
     * OrderTypeEnum.����������<BR>
     * OrderTypeEnum.���������� ��ǉ�����B<BR>
     * <BR>
     * �R�|�Q�j<BR>
     * ����.������t���.�M�p����t���O == true �̏ꍇ<BR>
     * <BR>
     * OrderTypeEnum.�V�K��������<BR>
     * OrderTypeEnum.�V�K��������<BR>
     * OrderTypeEnum.�����ԍϒ���<BR>
     * OrderTypeEnum.�����ԍϒ��� ��ǉ�����B<BR>
     * <BR>
     * �R�|�R�j<BR>
     * ����.������t���.�����E���n�t���O == true �̏ꍇ<BR>
     * <BR>
     * OrderTypeEnum.��������<BR>
     * OrderTypeEnum.���n���� ��ǉ�����B<BR>
     * <BR>
     * �S�j����R�[�h�iSONAR�j�̐ݒ�l��ǉ�����B<BR>
     * <BR>
     * �S�|�P�j<BR>
     * ����.������t���.���������t���O == true �̏ꍇ<BR>
     * <BR>
     * WEB3TransactionTypeSONARDef.���ʊ��� ��ǉ�����B<BR>
     * <BR>
     * �S�|�Q�j<BR>
     * ����.������t���.����O�����t���O == true �̏ꍇ<BR>
     * <BR>
     * WEB3TransactionTypeSONARDef.����O���� ��ǉ�����B<BR>
     * <BR>
     * �S�|�R�j<BR>
     * ����.������t���.�M�p����t���O == true �̏ꍇ<BR>
     * <BR>
     * WEB3TransactionTypeSONARDef.�M�p��<BR>
     * WEB3TransactionTypeSONARDef.�M�p�ԍ� ��ǉ�����B<BR>
     * <BR>
     * �S�|�S�j<BR>
     * ����.������t���.�����E���n�t���O == true �̏ꍇ<BR>
     * <BR>
     * WEB3TransactionTypeSONARDef.�������n ��ǉ�����B<BR>
     * <BR>
     * �T�j����.�����R�[�h != null �i�����R�[�h�w��j�̏ꍇ�A����ID�����X�g�ɒǉ�����B<BR>
     * �i�����R�[�h�ɑΉ��������ID�Ō������s���j<BR>
     * <BR>
     * �T�|�P�j�g���v���_�N�g�}�l�[�W��.get����()���R�[������B<BR>
     * <BR>
     * [����]<BR>
     * �،���ЁF ����.�⏕����.getInstitution()�̖߂�l<BR>
     * �����R�[�h�F ����.�����R�[�h<BR>
     * <BR>
     * �T�|�Q�j�擾���������̖���ID�����X�g�ɒǉ�����B<BR>
     * <BR>
     * �U�j����.�s��R�[�h != null �i�s��R�[�h�w��j�̏ꍇ�A�s��ID�����X�g�ɒǉ�����B<BR>
     * �i�s��R�[�h�ɑΉ�����s��ID�Ō������s��)<BR>
     * <BR>
     * �U�|�P�j���Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[������B<BR>
     * <BR>
     * [����]<BR>
     * �،���ЃR�[�h�F ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l<BR>
     * �s��R�[�h�F ����.�s��R�[�h<BR>
     * <BR>
     * �U�|�Q�j�擾�����s��̎s��ID�����X�g�ɒǉ�����B<BR>
     * <BR>
     * �V�j����.���������敪 != null �i���������敪�w��j�̏ꍇ�A<BR>
     * ���������敪�����X�g�ɒǉ�����B<BR>
     * <BR>
     * �@@���������敪 �� �p�����[�^.���������敪 <BR>
     * <BR>
     * �W�j�����������X�g����z����擾���āA�ԋp����B<BR>
     * <BR>
     * 
     * @@param l_subAccount -
     *            (�⏕����)<BR>
     *            �⏕�����I�u�W�F�N�g<BR>
     *            <BR>
     * @@param l_strProductCode -
     *            (�����R�[�h)<BR>
     *            �����R�[�h<BR>
     *            <BR>
     * @@param l_strMarketCode -
     *            (�s��R�[�h)<BR>
     *            �s��R�[�h<BR>
     *            <BR>
     * @@param l_datOrderBizDate -
     *            (������)<BR>
     *            ������<BR>
     *            <BR>
     * @@param l_orderStatus -
     *            (������t���)<BR>
     *            ������t��ԃI�u�W�F�N�g<BR>
     *            <BR>
     * @@param l_strOrderConditionDiv -
     *            (���������敪)<BR>
     *            ���������敪<BR>
     *            <BR>
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 455D98F902DB
     */
    protected Object[] createQueryDataContainer(WEB3GentradeSubAccount l_subAccount, String l_strProductCode,
        String l_strMarketCode, Date l_datOrderBizDate, OrderStatus l_orderStatus, String l_strOrderConditionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".createQueryDataContainer("
            + "WEB3GentradeSubAccount, String, String, Date, OrderStatus, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j���X�g�𐶐�����
        ArrayList l_lisQueryContainer = new ArrayList();

        if (l_subAccount == null)
        {
            log.debug("�⏕������null�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        if (l_orderStatus == null)
        {
            log.debug("������t��Ԃ�null�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();

        WEB3GentradeFinObjectManager l_finObjManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        // �Q�j�������w��l���P�j�̃��X�g�ɒǉ�����B
        // �Q�|�P�j����.������ != null �i�������w��j�̏ꍇ
        // �������w��l �� �p�����[�^.������
        if (l_datOrderBizDate != null)
        {
            log.debug("�������w��̏ꍇ");
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd"));
        }
        // �Q�|�Q�j����.������ == null �i�������w��Ȃ��j�̏ꍇ
        else
        {
            log.debug("�������w��Ȃ��̏ꍇ");
            Date l_datBusinessDate = GtlUtils.getTradingSystem().getBizDate();
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datBusinessDate, "yyyyMMdd"));
        }
        // �R�j������ʂ̐ݒ�l��ǉ�����B
        // �R�|�P�j
        // ����.������t���.���������t���O == true or
        // ����.������t���.����O�����t���O == true �̏ꍇ
        // OrderTypeEnum.����������
        // OrderTypeEnum.���������� ��ǉ�����B
        if (l_orderStatus.interestEquityFlag || l_orderStatus.offFloor)
        {
            log.debug("�������� or ����O�����̏ꍇ");
            l_lisQueryContainer.add(OrderTypeEnum.EQUITY_BUY);
            l_lisQueryContainer.add(OrderTypeEnum.EQUITY_SELL);
        }

        // �R�|�Q�j
        // ����.������t���.�M�p����t���O == true �̏ꍇ
        // OrderTypeEnum.�V�K��������
        // OrderTypeEnum.�V�K��������
        // OrderTypeEnum.�����ԍϒ���
        // OrderTypeEnum.�����ԍϒ��� ��ǉ�����B
        if (l_orderStatus.interestMarginFlag)
        {
            log.debug("�M�p����̏ꍇ");
            l_lisQueryContainer.add(OrderTypeEnum.MARGIN_LONG);
            l_lisQueryContainer.add(OrderTypeEnum.MARGIN_SHORT);
            l_lisQueryContainer.add(OrderTypeEnum.CLOSE_MARGIN_LONG);
            l_lisQueryContainer.add(OrderTypeEnum.CLOSE_MARGIN_SHORT);
        }

        // �R�|�R�j
        // ����.������t���.�����E���n�t���O == true �̏ꍇ
        // OrderTypeEnum.��������
        // OrderTypeEnum.���n���� ��ǉ�����B
        if (l_orderStatus.swapFlag)
        {
            log.debug("�����E���n�̏ꍇ");
            l_lisQueryContainer.add(OrderTypeEnum.SWAP_MARGIN_LONG);
            l_lisQueryContainer.add(OrderTypeEnum.SWAP_MARGIN_SHORT);
        }

        // �S�j����R�[�h�iSONAR�j�̐ݒ�l��ǉ�����B
        // �S�|�P�jWEB3TransactionTypeSONARDef.���ʊ��� ��ǉ�����B
        // ����.������t���.���������t���O == true �̏ꍇ
        if (l_orderStatus.interestEquityFlag)
        {
            l_lisQueryContainer.add(WEB3TransactionTypeSONARDef.MARKET_TRADING);
        }

        // �S�|�Q�j
        // ����.������t���.����O�����t���O == true �̏ꍇ
        // WEB3TransactionTypeSONARDef.����O���� ��ǉ�����B
        if (l_orderStatus.offFloor)
        {
            l_lisQueryContainer.add(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
        }

        // �S�|�R�j
        // ����.������t���.�M�p����t���O == true �̏ꍇ
        // WEB3TransactionTypeSONARDef.�M�p��
        // WEB3TransactionTypeSONARDef.�M�p�ԍ� ��ǉ�����B
        if (l_orderStatus.interestMarginFlag)
        {
            l_lisQueryContainer.add(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            l_lisQueryContainer.add(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
        }

        // �S�|�S�j
        // ����.������t���.�����E���n�t���O == true �̏ꍇ
        // WEB3TransactionTypeSONARDef.�������n ��ǉ�����B
        if (l_orderStatus.swapFlag)
        {
            l_lisQueryContainer.add(WEB3TransactionTypeSONARDef.SWAP_CONTRACT);
        }

        try
        {
            // �T�j����.�����R�[�h != null �i�����R�[�h�w��j�̏ꍇ�A����ID�����X�g�ɒǉ�����B
            if (l_strProductCode != null)
            {
                log.debug("�����R�[�h�w��̏ꍇ");
                // �T�|�P�j�g���v���_�N�g�}�l�[�W��.get����()���R�[������B
                WEB3EquityProduct l_equityProduct =
                    (WEB3EquityProduct) l_productManager.getProduct(l_subAccount.getInstitution(), l_strProductCode);
                // �T�|�Q�j�擾���������̖���ID�����X�g�ɒǉ�����B
                l_lisQueryContainer.add(Long.toString(l_equityProduct.getProductId()));

            }
            // �U�j����.�s��R�[�h != null �i�s��R�[�h�w��j�̏ꍇ�A�s��ID�����X�g�ɒǉ�����B
            // �U�|�P�j���Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[������B
            if (l_strMarketCode != null)
            {
                log.debug("�s��R�[�h�w��̏ꍇ");
                Market l_market = l_finObjManager.getMarket(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_strMarketCode);
                // �U�|�Q�j�擾�����s��̎s��ID�����X�g�ɒǉ�����B
                l_lisQueryContainer.add(Long.toString(l_market.getMarketId()));
            }

        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // �V�j����.���������敪 != null �i���������敪�w��j�̏ꍇ
        if (l_strOrderConditionDiv != null)
        {
            // ���������敪�����X�g�ɒǉ�����
            l_lisQueryContainer.add(l_strOrderConditionDiv);
        }

        //�W�j�����������X�g����z����擾���āA�ԋp����B
        Object[] l_strQueryContainers = new Object[l_lisQueryContainer.size()];
        l_strQueryContainers = l_lisQueryContainer.toArray();

        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
    }

    /**
     * (create�\�[�g����)<BR>
     * �\�[�g������������쐬����B<BR>
     * <BR>
     * �P�j����.�\�[�g�L�[.�L�[���ڂ̐����A�Ή�����e�[�u���̗񕨗�����<BR>
     *     �����^�~���w��Ƌ��ɒǉ�����B<BR>
     * <BR>
     *   �E�L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�<BR>
     *     ���e�[�u�����F �����P�ʃe�[�u��(eqtype_order_unit)<BR>
     *     ���L�[���ڕ�����i�V���{�����j�́A���b�Z�[�W��`�����Q��<BR>
     *     ���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q��<BR>
     * <BR>
     *   �ϊ��O          �ϊ���<BR>
     *   ----------   -----------------------------<BR>
     *   �����R�[�h     �����P�ʃe�[�u���D����ID<BR>
     *   �����敪      �����P�ʃe�[�u���D�ŋ敪<BR>
     *   �s��R�[�h     �����P�ʃe�[�u���D�s��ID<BR>
     *   ����敪      �����P�ʃe�[�u���D������ʁA����R�[�h�iSONAR�j(*1)<BR>
     *   �l�i����      �����P�ʃe�[�u���D�l�i����<BR>
     *   ���s����      �����P�ʃe�[�u���D���s����<BR>
     *   ��������      �����P�ʃe�[�u���D��������<BR>
     *   ��������      �����P�ʃe�[�u���D�쐬���t<BR>
     *   ������         �����P�ʃe�[�u���D������<BR>
     *   ��������      �����P�ʃe�[�u���D�����������t<BR>
     *   �ٍϋ敪      �����P�ʃe�[�u���D�ٍϋ敪<BR>
     *   �ٍϊ����l   �����P�ʃe�[�u���D�ٍϊ����l<BR>
     * <BR>
     *   �E�����^�~���w��́A�M�p����\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ�<BR>
     * <BR>
     *   (*1)�����w��̏ꍇ�F �ȉ��̇@@���B�̏��ƂȂ�B<BR>
     *           �@@�������=="����������"�{����R�[�h�iSONAR�j=="���ʊ���"<BR>
     *           �A�������=="����������"�{����R�[�h�iSONAR�j=="����O����"<BR>
     * <BR>
     * �B�������=="����������"�i�������́A����R�[�h�iSONAR�j=="���ʊ���"�Œ�j<BR>
     *        �~���w��̏ꍇ�F�@@��L�̇B���@@�̏��ƂȂ�B<BR>
     * <BR>
     * �Q�j�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��Œǉ�����B<BR>
     * <BR>
     * �R�j�쐬�����\�[�g�����������ԋp����B<BR>
     * <BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[�̔z��<BR>
     * <BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 455D99160077
     */
    protected String createSortCond(WEB3EquityMarginSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            ".createSortCond(WEB3EquityMarginSortKey[])";
        log.entering(STR_METHOD_NAME);

        if (l_sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̒�����0�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //�\�[�g������������쐬����B
        StringBuffer l_sbWhere = new StringBuffer();

        Map l_map = new HashMap();
        //�����R�[�h �� �����P�ʃe�[�u���D����ID
        l_map.put(WEB3EquityKeyItemDef.PRODUCTCODE, EqtypeOrderUnitParams.PTYPE + ".product_id");
        //�����敪 �� �����P�ʃe�[�u���D�ŋ敪
        l_map.put(WEB3EquityKeyItemDef.ACCOUNTTYPE, EqtypeOrderUnitParams.PTYPE + ".tax_type");
        //�s��R�[�h �� �����P�ʃe�[�u���D�s��ID
        l_map.put(WEB3EquityKeyItemDef.TRADEMARKET, EqtypeOrderUnitParams.PTYPE + ".market_id");
        //����敪 �� �����P�ʃe�[�u���D������ʁA����R�[�h�iSONAR�j
        String l_strSort = null;
        String l_strOperation = null;
        //�l�i���� �� �����P�ʃe�[�u���D�l�i����
        l_map.put(WEB3EquityKeyItemDef.PRICE_COND, EqtypeOrderUnitParams.PTYPE + ".price_condition_type");
        //���s���� �� �����P�ʃe�[�u���D���s����
        l_map.put(WEB3EquityKeyItemDef.EXECUTE_COND, EqtypeOrderUnitParams.PTYPE + ".execution_condition_type");
        //�������� �� �����P�ʃe�[�u���D��������
        l_map.put(WEB3EquityKeyItemDef.SEND_COND, EqtypeOrderUnitParams.PTYPE + ".order_condition_type");
        //�������� �� �����P�ʃe�[�u���D�쐬���t
        l_map.put(WEB3EquityKeyItemDef.ORDER_TIME, EqtypeOrderUnitParams.PTYPE + ".created_timestamp");
        //������ �� �����P�ʃe�[�u���D������
        l_map.put(WEB3EquityKeyItemDef.SEND_DATE, EqtypeOrderUnitParams.PTYPE + ".biz_date");
        //�������� �� �����P�ʃe�[�u���D�����������t
        l_map.put(WEB3EquityKeyItemDef.ORDER_TIMELIMIT, EqtypeOrderUnitParams.PTYPE + ".expiration_date");
        //�ٍϋ敪 �� �����P�ʃe�[�u���D�ٍϋ敪
        l_map.put(WEB3EquityKeyItemDef.REPAYMENT_DIV, EqtypeOrderUnitParams.PTYPE + ".repayment_type");
        //�ٍϊ����l �� �����P�ʃe�[�u���D�ٍϊ����l
        l_map.put(WEB3EquityKeyItemDef.REPAYMENTNUM, EqtypeOrderUnitParams.PTYPE + ".repayment_num");

        for (int i = 0; i < l_sortKeys.length; i++)
        {
            l_strOperation = WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc) ? " ASC" : " DESC";

            //�P�j����.�\�[�g�L�[.�L�[���ڂ̐����A�Ή�����e�[�u���̗񕨗����������^�~���w��Ƌ��ɒǉ�����
            if (WEB3EquityKeyItemDef.TRADETYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strSort = EqtypeOrderUnitParams.PTYPE + ".order_type";
                l_strSort += l_strOperation;
                l_strSort += ", ";
                l_strSort += EqtypeOrderUnitParams.PTYPE;
                l_strSort += ".sonar_traded_code";
                l_map.put(WEB3EquityKeyItemDef.TRADETYPE, l_strSort);
            }

            if (l_map.containsKey(l_sortKeys[i].keyItem))
            {
                l_sbWhere.append(l_map.get(l_sortKeys[i].keyItem));
                l_sbWhere.append(l_strOperation);
                l_sbWhere.append(", ");
            }

        }

        //�Q�j�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��Œǉ�����B
        l_strSort = EqtypeOrderUnitParams.PTYPE;
        l_strSort += ".last_updated_timestamp";
        l_strSort += " ASC";
        l_sbWhere.append(l_strSort);

        //�R�j�쐬�����\�[�g�����������ԋp����B
        String l_strQueryString = l_sbWhere.toString();
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (is�w������)<BR>
     * �w�肳�ꂽ����Ԃɍ��v���Ă��邩�ǂ����𔻒肵�A<BR>
     * ���v���Ă���ꍇ��true���A���v���Ă��Ȃ��ꍇ��false���A���ꂼ��Ԃ��B<BR>
     * <BR>
     * �P�j�����f�[�^�A�_�v�^.get����ԋ敪()���R�[������B<BR>
     * <BR>
     *   [����]<BR>
     *   �����P�ʁF ����.�����P��<BR>
     * <BR>
     * �Q�j�擾��������ԋ敪 == ����.����ԋ敪�̏ꍇ�́Atrue��Ԃ��B<BR>
     *     �ȊO�Afalse��Ԃ��B<BR>
     * <BR>
     * @@param l_strExecutedStatus - (����ԋ敪)<BR>
     * ����ԋ敪<BR>
     * <BR>
     * 0�F �����<BR>
     * 1�F �ꕔ����<BR>
     * 2�F �S������<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 456293230152
     */
    protected boolean isDesignateExecutedStatus(String l_strExecutedStatus, EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = ".isDesignateExecutedStatus(String, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        boolean l_blnReturn;

        String l_strExecType = WEB3EquityDataAdapter.getExecType(l_orderUnit);
        if (l_strExecType.equals(l_strExecutedStatus))
        {
            //�擾��������ԋ敪������.����ԋ敪�̏ꍇ
            log.debug("�擾��������ԋ敪������.����ԋ敪�̏ꍇ");
            l_blnReturn = true;
        }
        else
        {
            //�擾��������ԋ敪������.����ԋ敪�̏ꍇ
            log.debug("�擾��������ԋ敪������.����ԋ敪�̏ꍇ");
            l_blnReturn = false;
        }

        log.exiting(STR_METHOD_NAME);

        return l_blnReturn;
    }

    /**
     * (is��������\���ԑ�)<BR>
     * ����������\�Ȏ��ԑт��ǂ����𔻒肷��B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����E�M�p�������Ɖ�j06.��������\���ԑу`�F�b�N�v �Q�ƁB<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 4562BA770118
     */
    protected boolean isChangeCancelEnableTimeZone(EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            ".isChangeCancelEnableTimeZone(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�����P�ʂ�null�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)((EqTypeOrderUnit)l_orderUnit).getDataSourceObject();

        try
        {
            //2.getMarket(�s��ID : long)
            Market l_market = null;
            try
            {
                l_market = l_finObjectManager.getMarket(l_eqTypeOrderUnitRow.getMarketId());
            }
            catch (NotFoundException l_ex)
            {
                log.debug("�s�ꂪnull�ł���");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //3.reset�s��R�[�h(String)
            WEB3GentradeTradingTimeManagement.resetMarketCode(
                l_market.getMarketCode());

            //4.reset��t���ԋ敪(String)
            if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_eqTypeOrderUnitRow.getSonarTradedCode()))
            {
                log.debug("����O�����̏ꍇ");
                //����.�����P��.����R�[�h�iSONAR�j == �h����O�����h �̏ꍇ�A�h����O�����h
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET);
            }
            else if (WEB3TransactionTypeSONARDef.SWAP_CONTRACT.equals(l_eqTypeOrderUnitRow.getSonarTradedCode()))
            {
                log.debug("�������n�̏ꍇ");
                //����.�����P��.����R�[�h�iSONAR�j == �h�������n�h �̏ꍇ�A�h�����E���n�h
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
            }
            else
            {
                log.debug("����ȊO�̏ꍇ");
                //����ȊO�̏ꍇ�A�h�����E�M�p�h
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            }

            //5.reset������t���i(String)
            if (WEB3TransactionTypeSONARDef.MARKET_TRADING.equals(l_eqTypeOrderUnitRow.getSonarTradedCode()))
            {
                log.debug("���ʊ����̏ꍇ");
                //����.�����P��.����R�[�h�iSONAR�j == �h���ʊ����h �̏ꍇ�A�h�����h
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
            }
            else if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(
                l_eqTypeOrderUnitRow.getSonarTradedCode()))
            {
                log.debug("����O�����̏ꍇ");
                //����.�����P��.����R�[�h�iSONAR�j == �h����O�����h �̏ꍇ�A�h����O�����h
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
            }
            else
            {
                log.debug("����ȊO�̏ꍇ");
                //����ȊO�̏ꍇ�A�h�M�p����h
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            }
            //6.reset������t�g�����U�N�V����(String)
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);

            //isPTS�s��( )
            if (((WEB3GentradeMarket)l_market).isPTSMarket())
            {
                // validate������t�\( )
                WEB3EquityPTSTradingTimeManagement.validateOrderAccept();

                //true��ԋp����B
                return true;
            }

            //7.validate������t�\()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            //8.get������( )
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            Date l_datOrderBizDate =
                WEB3DateUtility.getDate(
                    l_eqTypeOrderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            //9.get������()�̖߂�l > ����.�����P��.������ �̏ꍇ
            //10.validate�ǌ���������t�\(ProductTypeEnum)
            if (WEB3DateUtility.compareToDay(l_datBizDate, l_datOrderBizDate) > 0)
            {
                log.debug("get������()�̖߂�l > ����.�����P��.�������̏ꍇ");
                WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(ProductTypeEnum.EQUITY);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            //��O���X���[���ꂽ�ꍇ�i������t�s�̏ꍇ�j�Afalse ��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //11.true ��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate���������\���)<BR>
     * �����������\�ȏ�Ԃ��ǂ������`�F�b�N����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����E�M�p�������Ɖ�j07.���������\��ԃ`�F�b�N�v �Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * <BR>
     * @@param l_product - (����)<BR>
     * �����I�u�W�F�N�g<BR>
     * <BR>
     * @@param l_market - (�s��)<BR>
     * �s��I�u�W�F�N�g<BR>
     * <BR>
     * @@param l_branch - (���X)<BR>
     * ���X�I�u�W�F�N�g<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * @@roseuid 4562D87300A1
     */
    protected void validateOrderForChangeability(WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product,
        WEB3GentradeMarket l_market, WEB3GentradeBranch l_branch, EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validateOrderForChangeability("
            + "WEB3GentradeSubAccount, WEB3EquityProduct, WEB3GentradeMarket, WEB3GentradeBranch, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // 1.1 getOrder()
        // 1.2 validate���������\���
        this.validateOrderForChangeability(l_orderUnit.getOrder(), l_market);

        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow) ((EqTypeOrderUnit) l_orderUnit).getDataSourceObject();

        WEB3EquityOrderManager l_orderMgr = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();

        // 1.3 (*) ���������̒����̏ꍇ
        // ����.�����P��.����R�[�h�iSONAR�j == �h���ʊ����h or �h����O�����h
        if (WEB3TransactionTypeSONARDef.MARKET_TRADING.equals(l_eqTypeOrderUnitRow.getSonarTradedCode()) ||
            WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_eqTypeOrderUnitRow.getSonarTradedCode()))
        {
            log.debug("���������̒����̏ꍇ");
            l_orderMgr.validateTradedProduct(l_product, l_market);
        }
        else
        {
            // 1.4 (*)�M�p����̒����̏ꍇ
            // validate��������i�M�p�j(�⏕���� : �⏕����,
            // �������� : ��������,
            // �s�� : �s��,
            // ���X : ���X,
            // �ٍϋ敪 : String,
            // �����J�e�S�� : OrderCategEnum,
            // is���� : boolean,
            // is������~�`�F�b�N : boolean)
            // [����]
            // �⏕�����F ����.�⏕����
            // ���������F ����.����
            // �s��F ����.�s��
            // ���X�F ����.���X
            // �ٍϋ敪�F ����.�����P��.�ٍϋ敪
            // �����J�e�S���F ����.�����P��.�����J�e�S��
            // is�����F �i�ȉ��̂Ƃ���j
            // �P�j����.�����P��.getSide()���擾����B
            // �Q�j�ȉ��̂Ƃ���ɂ�
            // ������.�����P��.�����J�e�S�� == �h�V�K�������h �̏ꍇ
            // ����.�����P��.getSide()�̖߂�l == SideEnum.�h�����h �̏ꍇ�Afalse ���Z�b�g����B
            // ����.�����P��.getSide()�̖߂�l == SideEnum.�h����h �̏ꍇ�Atrue ���Z�b�g����B
            // ������.�����P��.�����J�e�S�� != �h�V�K�������h �̏ꍇ
            // ����.�����P��.getSide()�̖߂�l == SideEnum.�h�����h �̏ꍇ�Atrue ���Z�b�g����B
            // ����.�����P��.getSide()�̖߂�l == SideEnum.�h����h �̏ꍇ�Afalse ���Z�b�g����B
            // is������~�`�F�b�N�F false
            log.debug("�M�p����̒����̏ꍇ");
            WEB3MarginRepaymentUnit l_repayment = new WEB3MarginRepaymentUnit();
            l_repayment.repaymentDiv = l_eqTypeOrderUnitRow.getRepaymentType();

            // is����
            boolean l_blnIsOpenSell = true;
            if (OrderCategEnum.OPEN_MARGIN.equals(l_eqTypeOrderUnitRow.getOrderCateg()))
            {
                if (SideEnum.BUY.equals(l_orderUnit.getSide()))
                {
                    l_blnIsOpenSell = false;
                }
                else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                {
                    l_blnIsOpenSell = true;
                }
            }
            else
            {
                if (SideEnum.BUY.equals(l_orderUnit.getSide()))
                {
                    l_blnIsOpenSell = true;
                }
                else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                {
                    l_blnIsOpenSell = false;
                }
            }

            l_orderMgr.validateTradedProductForMarginTrading(
                l_subAccount,
                l_product,
                l_market,
                l_branch,
                l_repayment.repaymentDiv,
                l_eqTypeOrderUnitRow.getOrderCateg(),
                l_blnIsOpenSell,
                false);
        }

        // 1.5 validate�C���T�C�_�[
        l_orderMgr.validateInsider(l_subAccount, l_product);

        // 1.6 validate�ڋq�����ʎ����~
        //�⏕�����F ����.�⏕����
        //����ID�F ����.�����P��.����ID
        //������ʁF ����.�����P��.�������
        //�s��F ����.�s��
        this.validateAccountProductOrderStop(
            l_subAccount,
            l_orderUnit.getProduct().getProductId(),
            l_orderUnit.getOrderType(),
            l_market);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�������ꗗ)<BR>
     * �������̔z��𐶐�����B<BR>
     * <BR>
     * �P�j���f�[�^���擾����B<BR>
     * <BR>
     *   ����.�����P��.getExecutions()���R�[������B<BR>
     * <BR>
     * �Q�j��̃��X�g�iArryaList�j�𐶐�����B<BR>
     * <BR>
     * �R�j�擾�������f�[�^�̗v�f�����A�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �R�|�P�j�����E�M�p�������̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �R�|�Q�j�����E�M�p�������̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     *   �������F ���f�[�^.������<BR>
     *   ��芔���F ���f�[�^.��萔��<BR>
     *   ���P���F ���f�[�^.���P��<BR>
     * <BR>
     * �R�|�R�j���X�g�Ɋ����E�M�p�������C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �S�j�����������X�g����z����擾���A�ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * @@return �����E�M�p�������[]
     * @@roseuid 45641A5603A9
     */
    protected WEB3EquityMarginExecuteUnit[] createExecuteUnits(EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "createExecuteUnits(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�P�j���f�[�^���擾����B
        //����.�����P��.getExecutions()���R�[������B
        OrderExecution[] l_orderExecution = l_orderUnit.getExecutions();

        //�Q�j��̃��X�g�iArryaList�j�𐶐�����B
        List l_lisArrayList = new ArrayList();

        //�R�j�擾�������f�[�^�̗v�f�����A�ȉ��̏������J��Ԃ��B
        for (int i = 0; i < l_orderExecution.length; i++)
        {
            //�R�|�P�j�����E�M�p�������̃C���X�^���X�𐶐�����B
            WEB3EquityMarginExecuteUnit l_instant = new WEB3EquityMarginExecuteUnit();

            //�R�|�Q�j�����E�M�p�������̃v���p�e�B���Z�b�g����B
            l_instant.executionTimestamp = l_orderExecution[i].getExecutionTimestamp();
            l_instant.execQuantity = WEB3StringTypeUtility.formatNumber(l_orderExecution[i].getExecutionQuantity());
            l_instant.execPrice = WEB3StringTypeUtility.formatNumber(l_orderExecution[i].getExecutionPrice());

            //�R�|�R�j���X�g�Ɋ����E�M�p�������C���X�^���X��ǉ�����B
            l_lisArrayList.add(l_instant);
        }

        //�S�j�����������X�g����z����擾���A�ԋp����B
        WEB3EquityMarginExecuteUnit[] l_equityMarginExecuteUnit =
            new WEB3EquityMarginExecuteUnit[l_orderExecution.length];
        l_lisArrayList.toArray(l_equityMarginExecuteUnit);

        log.exiting(STR_METHOD_NAME);
        return l_equityMarginExecuteUnit;
    }

    /**
     * (get����)<BR>
     * �������擾����B<BR>
     * <BR>
     * �P�j����.�����P��.�����J�e�S�� == �h�V�K�������h �̏ꍇ<BR>
     * <BR>
     *   null ��ԋp����B<BR>
     * <BR>
     * �Q�j����.�����P��.�����J�e�S�� != �h�V�K�������h �̏ꍇ<BR>
     * <BR>
     * �Q�|�P�j����.�����P��.���Ϗ����敪 == null �̏ꍇ<BR>
     * <BR>
     * �Q�|�P�|�P�j����ID���擾����B<BR>
     * <BR>
     * �Q�|�P�|�P�|�P�j<BR>
     *   ����.�����P��.getContractsToClose()���R�[�����A<BR>
     *   �ԍώw������擾����B<BR>
     * <BR>
     * �Q�|�P�|�P�|�Q�j<BR>
     *   �擾�����ԍώw���񂩂猚��ID���擾����B<BR>
     * <BR>
     * �Q�|�P�|�Q�j<BR>
     *   �����|�W�V�����}�l�[�W��.getContract()���R�[������B<BR>
     * <BR>
     *   [����]<BR>
     *   ����ID�F �擾��������ID<BR>
     * <BR>
     * �Q�|�P�|�R�j<BR>
     *   �擾��������.������ԋp����B<BR>
     * <BR>
     * �Q�|�Q�j����.�����P��.���Ϗ����敪 != null �̏ꍇ<BR>
     * <BR>
     *   null ��ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - �����P��<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * @@return Date
     * @@roseuid 45641D2F01E4
     */
    protected Date getOpenDate(EqTypeOrderUnit l_orderUnit)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getOpenDate()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)((EqTypeOrderUnit)l_orderUnit).getDataSourceObject();
        OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
        EqTypeClosingContractSpec[] l_closingContractSpecs = null;
        Date l_datContractOpenDate = null;
        // �P�j����.�����P��.�����J�e�S�� == �h�V�K�������h �̏ꍇ
        //
        // null ��ԋp����B
        if (OrderCategEnum.OPEN_MARGIN.equals(l_eqTypeOrderUnitRow.getOrderCateg()))
        {
            return null;
        }

        // �Q�j����.�����P��.�����J�e�S�� != �h�V�K�������h �̏ꍇ
        else
        {
            // �Q�|�P�j����.�����P��.���Ϗ����敪 == null �̏ꍇ
            if (WEB3StringTypeUtility.isEmpty(l_eqTypeOrderUnitRow.getClosingOrderType()))
            {
                // �Q�|�P�|�P�j����ID���擾����B
                //
                // �Q�|�P�|�P�|�P�j
                // ����.�����P��.getContractsToClose()���R�[�����A
                // �ԍώw������擾����B
                if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
                {
                    EqTypeContractSettleOrderUnit l_closingOrderUnit =
                        (EqTypeContractSettleOrderUnit)l_orderUnit;
                    l_closingContractSpecs =
                        l_closingOrderUnit.getContractsToClose();
                }
                else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
                {
                    EqTypeContractSwapOrderUnit l_swapOrderUnit =
                        (EqTypeContractSwapOrderUnit)l_orderUnit;
                    l_closingContractSpecs =
                        l_swapOrderUnit.getContractsToClose();
                }
                else
                {
                    return null;
                }

                if (l_closingContractSpecs == null || l_closingContractSpecs.length == 0)
                {
                    return null;
                }
                
                // �Q�|�P�|�P�|�Q�j
                // �擾�����ԍώw���񂩂猚��ID���擾����B
                long l_lngContractId = l_closingContractSpecs[0].getContractId();
                try
                {
                    // �Q�|�P�|�Q�j 
                    // �����|�W�V�����}�l�[�W��.getContract()���R�[������B 
                    WEB3EquityContract l_contract = 
                        (WEB3EquityContract) l_positionManager.getContract(l_lngContractId);
                    // �Q�|�P�|�R�j
                    // �擾��������.������ԋp����B
                    l_datContractOpenDate = l_contract.getOpenDate();
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME,
                        l_nfe.getMessage(), 
                        l_nfe);
                }

            }
            // �Q�|�Q�j����.�����P��.���Ϗ����敪 != null �̏ꍇ
            //
            // null ��ԋp����B
            else
            {
                return null;
            }
        }
        return l_datContractOpenDate;
    }

    /**
     * (get���P��)<BR>
     * ���P�����擾����B<BR>
     * <BR>
     * �P�j����.�����P��.�����J�e�S�� == �h�V�K�������h �̏ꍇ<BR>
     * <BR>
     *   null ��ԋp����B<BR>
     * <BR>
     * �Q�j����.�����P��.�����J�e�S�� != �h�V�K�������h �̏ꍇ<BR>
     * <BR>
     * �Q�|�P�j<BR>
     *   ����.�����P��.getContractsToClose()���R�[�����A<BR>
     *   �ԍώw������擾����B<BR>
     * <BR>
     * �Q�|�Q�j�ԍώw����̗v�f���A�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �Q�|�Q�|�P�j<BR>
     *   ��������W�v����B<BR>
     * <BR>
     *   ����� = ����� �{ �i�ԍώw����.����ID����擾����錚���̌��P�� �~
     * �ԍώw����.�ԍϒ������ʁj<BR>
     * <BR>
     * �Q�|�Q�|�Q�j<BR>
     *   �ԍϒ������ʂ��W�v����B<BR>
     * <BR>
     *   �ԍϐ��� = �ԍϐ��� �{ �ԍώw����.�ԍϒ�������<BR>
     * <BR>
     * �Q�|�R�j<BR>
     *   ���P�����Z�o����B<BR>
     * <BR>
     *   ���P�� = �W�v��������� / �W�v�����ԍϐ���<BR>
     * <BR>
     *   ���v�Z���ʂ͉~�������l�̌ܓ�����B<BR>
     * <BR>
     * �Q�|�S�j<BR>
     *   �Z�o�������P����ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * @@return String
     * @@roseuid 4564259D001F
     */
    protected String getContractPrice(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getContractPrice()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        EqTypeClosingContractSpec[] l_closingContractSpecs = null;
        double l_dblContractPrice = 0D;
        // �W�v���������
        BigDecimal l_bdTotalContractAmount = new BigDecimal(0);
        // �ԍϐ���
        BigDecimal l_bdContractQuantity = new BigDecimal(0);

        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)((EqTypeOrderUnit)l_orderUnit).getDataSourceObject();


        // �P�j����.�����P��.�����J�e�S�� == �h�V�K�������h �̏ꍇ
        //
        //   null ��ԋp����B
        if (OrderCategEnum.OPEN_MARGIN.equals(l_eqTypeOrderUnitRow.getOrderCateg()))
        {
            return null;
        }
        // �Q�j����.�����P��.�����J�e�S�� != �h�V�K�������h �̏ꍇ
        else
        {
            // �Q�|�P�j
            //   ����.�����P��.getContractsToClose()���R�[�����A
            //   �ԍώw������擾����B
            OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
            {
                EqTypeContractSettleOrderUnit l_closingOrderUnit =
                    (EqTypeContractSettleOrderUnit)l_orderUnit;
                l_closingContractSpecs =
                    l_closingOrderUnit.getContractsToClose();
            }
            else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
            {
                EqTypeContractSwapOrderUnit l_swapOrderUnit =
                    (EqTypeContractSwapOrderUnit)l_orderUnit;
                l_closingContractSpecs =
                    l_swapOrderUnit.getContractsToClose();
            }
            else
            {
                return null;
            }
            
            // �Q�|�Q�j�ԍώw����̗v�f���A�ȉ��̏������J��Ԃ��B<BR>
            //
            // �Q�|�Q�|�P�j<BR>
            //   ��������W�v����B<BR>
            //
            //   ����� = ����� �{ �i�ԍώw����.����ID����擾����錚���̌��P�� �~
            //   �ԍώw����.�ԍϒ������ʁj

            for (int j = 0;j < l_closingContractSpecs.length;j++)
            {
                long l_lngContractId = l_closingContractSpecs[j].getContractId();
                WEB3EquityContract l_contract;
                try
                {
                    l_contract = (WEB3EquityContract)
                        l_positionManager.getContract(l_lngContractId);
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                        STR_METHOD_NAME,
                        l_nfe.getMessage(), l_nfe);
                }

                double l_dblQuantity = l_closingContractSpecs[j].getQuantity();
                //�ԍώw����.����ID����擾����錚���̌��P��
                BigDecimal l_dbContractPrice = new BigDecimal("" + l_contract.getContractPrice());
                // �ԍώw����.�ԍϒ�������
                BigDecimal l_bdQuantity = new BigDecimal("" + l_dblQuantity);
                // �ԍώw����.����ID����擾����錚���̌��P�� �~ �ԍώw����.�ԍϒ�������
                BigDecimal l_bdContractAmount = l_dbContractPrice.multiply(l_bdQuantity);
                l_bdTotalContractAmount = l_bdTotalContractAmount.add(l_bdContractAmount);

                //�Q�|�Q�|�Q�j
                //   �ԍϒ������ʂ��W�v����B
                //
                //   �ԍϐ��� = �ԍϐ��� �{ �ԍώw����.�ԍϒ�������
                l_bdContractQuantity = l_bdContractQuantity.add(l_bdQuantity);

            }
            
            // �Q�|�R�j<BR>
            //   ���P�����Z�o����B<BR>
            //
            //   ���P�� = �W�v��������� / �W�v�����ԍϐ���
            //  ���v�Z���ʂ͉~�������l�̌ܓ�����B 
            if (l_bdContractQuantity.doubleValue() == 0)
            {
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, STR_METHOD_NAME);
            }
            else
            {
                l_dblContractPrice = l_bdTotalContractAmount.divide(
                    l_bdContractQuantity,
                    0,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        //�Q�|�S�j
        //   �Z�o�������P����ԋp����B
        return WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
    }

    /**
     * (get�������ꗗ)<BR>
     * �������ꗗ���擾����B<BR>
     * <BR>
     * �P�j���List�𐶐�����B<BR>
     * <BR>
     * �Q�j�Ɩ����t�̑O�c�Ɠ��ƋƖ����t���P�j��List�ɒǉ�����B<BR>
     * <BR>
     *    ���Ɩ����t�F GtlUtils.getTradingSystem().getBizDate()<BR>
     * <BR>
     * �R�j����.�s��R�[�h�ꗗ�̊e�v�f�ɂ��āA�ȉ��̏������s���B<BR>
     * <BR>
     * �R�|�P�j����J�����_�R���e�L�X�g�̎s��R�[�h��ݒ肷��B<BR>
     * <BR>
     *    ������ԊǗ�.reset�s��R�[�h()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    �s��R�[�h�F ����.�s��R�[�h�ꗗ�̗v�f<BR>
     * <BR>
     * �R�|�Q�j���������擾����B<BR>
     * <BR>
     *    ������ԊǗ�.get������()���R�[������B<BR>
     * <BR>
     * �R�|�R�j�擾�������������P�j��List�ɑ��݂��Ȃ��ꍇ�A�P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �S�j�P�j��List����z����擾���A�ԋp����B<BR>
     * <BR>
     * @@param l_strMarketCodeList - (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h�ꗗ<BR>
     * <BR>
     * @@return Date[]
     * @@roseuid 456A42F5024F
     */
    protected Date[] getOrderBizDateList(String[] l_strMarketCodeList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getOrderBizDateList()";
        log.entering(STR_METHOD_NAME);
        // �P�j���List�𐶐�����B
        ArrayList l_lisOrderBizDateList = new ArrayList();

        // �Q�j�Ɩ����t�̑O�c�Ɠ��ƋƖ����t���P�j��List�ɒǉ�����B
        Timestamp l_tsBizDate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
        WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_tsBizDate);
        l_lisOrderBizDateList.add(l_gentradeBizDate.roll(-1));
        l_lisOrderBizDateList.add(GtlUtils.getTradingSystem().getBizDate());

        // �R�j����.�s��R�[�h�ꗗ�̊e�v�f�ɂ��āA�ȉ��̏������s���B
        // �R�|�P�j����J�����_�R���e�L�X�g�̎s��R�[�h��ݒ肷��B
        //    ������ԊǗ�.reset�s��R�[�h()���R�[������B
        //    [����]
        //    �s��R�[�h�F ����.�s��R�[�h�ꗗ�̗v�f
        for (int i = 0; i < l_strMarketCodeList.length; i++)
        {
            WEB3GentradeTradingTimeManagement.resetMarketCode(
                    l_strMarketCodeList[i]);

            // �R�|�Q�j���������擾����B<BR>
            //
            //    ������ԊǗ�.get������()���R�[������B
            Date l_datBizDte;
            l_datBizDte = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            // �R�|�R�j�擾�������������P�j��List�ɑ��݂��Ȃ��ꍇ�A�P�j��List�ɒǉ�����B
            boolean l_blnExist = false;
            for (int j = 0; j < l_lisOrderBizDateList.size(); j++)
            {
                if (WEB3DateUtility.compareToDay(l_datBizDte, (Date)l_lisOrderBizDateList.get(j)) == 0)
                {
                    l_blnExist = true;
                    break;
                }
            }
            if (!l_blnExist)
            {
                l_lisOrderBizDateList.add(l_datBizDte);
            }
        }
        // �S�j�P�j��List����z����擾���A�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return (Date[]) l_lisOrderBizDateList.toArray(new Date[l_lisOrderBizDateList.size()]);
    }

    /**
     * (get�s��ǌx���s��)<BR>
     * �s��ǌx���s����擾����B<BR>
     * <BR>
     * �P�j������ԊǗ�.get�s��ǌx���s��()���R�[�����A�s��R�[�h�̔z����擾����B<BR>
     * <BR>
     * �@@�@@[������ԊǗ�.get�s��ǌx���s��()�ɃZ�b�g�������]<BR>
     * �@@�@@���X�F�@@����.���X<BR>
     * �@@�@@�����^�C�v�F�@@"����"<BR>
     * �@@�@@�M�p����敪�F ����.�M�p����敪<BR>
     * <BR>
     * <BR>
     * �Q�jPTS������ԊǗ�.get�s��ǌx���s��()���R�[�����A�s��R�[�h�̔z����擾����<BR>
     * <BR>
     * �@@�@@[PTS������ԊǗ�.get�s��ǌx���s��()�ɃZ�b�g�������]<BR>
     * �@@�@@���X�F�@@����.���X<BR>
     * �@@�@@�����^�C�v�F�@@"����"<BR>
     * �@@�@@�M�p����敪�F�@@"DEFAULT"<BR>
     * <BR>
     * �R�j���ʂ��s��R�[�h�����Ń}�[�W����B<BR>
     * <BR>
     * �S�j�擾�����s��R�[�h�̔z���ԋp����B<BR>
     * <BR>
     * @@param l_gentradeBranch - (���X)<BR>
     * ���X<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * @@param l_strMarginTradeDiv - (�M�p����敪)<BR>
     * �M�p����敪<BR>
     * @@throws WEB3BaseException
     * @@return String[]
     */
    protected String[] getTradeCloseMarket(
        WEB3GentradeBranch l_gentradeBranch,
        ProductTypeEnum l_productType,
        String l_strMarginTradeDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradeCloseMarket(WEB3GentradeBranch, ProductTypeEnum, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j������ԊǗ�.get�s��ǌx���s��()���R�[�����A�s��R�[�h�̔z����擾����B
        //[������ԊǗ�.get�s��ǌx���s��()�ɃZ�b�g�������]
        //���X�F�@@����.���X
        //�����^�C�v�F�@@"����"
        //�M�p����敪�F ����.�M�p����敪
        String[] l_strTradeCloseMarkets = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            l_gentradeBranch, ProductTypeEnum.EQUITY, l_strMarginTradeDiv);

        //�Q�jPTS������ԊǗ�.get�s��ǌx���s��()���R�[�����A�s��R�[�h�̔z����擾����
        //[PTS������ԊǗ�.get�s��ǌx���s��()�ɃZ�b�g�������]
        //���X�F�@@����.���X
        //�����^�C�v�F�@@"����"
        //�M�p����敪�F�@@"DEFAULT"
        String[] l_strPTSTradeCloseMarkets = WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
            l_gentradeBranch, ProductTypeEnum.EQUITY, WEB3MarginTradingDivDef.DEFAULT);

        //�R�j���ʂ��s��R�[�h�����Ń}�[�W����B
        String[] l_strAscTradeCloseMarkets =
            this.mergeAndSort(l_strTradeCloseMarkets, l_strPTSTradeCloseMarkets);

        //�S�j�擾�����s��R�[�h�̔z���ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strAscTradeCloseMarkets;
    }

    /**
     * (validate��������\���)<BR>
     * ����������\�ȏ�Ԃ��ǂ������`�F�b�N����B<BR>
     * �����̎s�ꂪPTS�s��ł��邩�ŃR�[�����郁�\�b�h���Ăѕ�����B<BR>
     * <BR>
     * �P�j�s�ꂪPTS�s��ł��邩�𔻒肷��B<BR>
     * �@@����.�s��.isPTS�s��()���R�[������B<BR>
     * <BR>
     * �@@��PTS�s��̏ꍇ(����.�s��.isPTS�s��() == true )�͈ȉ��������s���B<BR>
     * �@@�@@PTS�����}�l�[�W��.validatePTS��������\���()���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�����F ����.����<BR>
     * <BR>
     * �@@��PTS�s��ȊO�̏ꍇ(����.�s��.isPTS�s��() == false )�͈ȉ��������s���B<BR>
     * �@@�@@�g�����������}�l�[�W��.validate��������\���()���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�����F�@@����.����<BR>
     * <BR>
     * @@param l_order - (����)<BR>
     * ����<BR>
     * @@param l_market - (�s��)<BR>
     * �s��I�u�W�F�N�g<BR>
     */
    protected void validateOrderForCancellation(
        Order l_order,
        WEB3GentradeMarket l_market)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForCancellation(Order, WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);

        if (l_market == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "," + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //PTS�s��̏ꍇ(����.�s��.isPTS�s��() == true )�͈ȉ��������s���B
        if (l_market.isPTSMarket())
        {
            WEB3EquityPTSOrderManager l_equityPTSOrderManager =
                (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
            //PTS�����}�l�[�W��.validatePTS��������\���()���R�[������B
            //[����]
            //�����F ����.����
            l_equityPTSOrderManager.validatePTSOrderForCancellation(l_order);
        }
        else
        {
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            //�g�����������}�l�[�W��.validate��������\���()���R�[������B
            //[����]
            //�����F�@@����.����
            l_equityOrderManager.validateOrderForCancellation(l_order);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�戵�\�s��)<BR>
     * ����\�s��`�F�b�N���s���B<BR>
     * �s�ꂪPTS�s��ł��邩�ŃR�[�����郁�\�b�h���Ăѕ�����B<BR>
     * <BR>
     * 1)�s����擾����B<BR>
     * �@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h�F�@@����.�������.�،���ЃR�[�h<BR>
     * �@@�s��R�[�h�F�@@����.�������.�s��R�[�h<BR>
     * <BR>
     * �Q�j�s�ꂪPTS�s��ł��邩�𔻒肷��B<BR>
     * �@@�P�j�Ŏ擾�����s��.isPTS�s��()���R�[������B<BR>
     * <BR>
     * �@@��PTS�s��̏ꍇ(����.�s��.isPTS�s��() == true )�͈ȉ��������s���B<BR>
     * �@@�@@PTS�����}�l�[�W��.validate�戵�\PTS�s����R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@���X�F�@@����.���X<BR>
     * �@@�@@��������F�@@����.�������<BR>
     * <BR>
     * �@@��PTS�s��ȊO�̏ꍇ(����.�s��.isPTS�s��() == false )�͈ȉ��������s���B<BR>
     * �@@�@@�g�����������}�l�[�W��.validate�戵�\�s����R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@���X�F�@@����.���X<BR>
     * �@@�@@��������F�@@����.�������<BR>
     * <BR>
     * @@param l_branch - (���X)<BR>
     * ���X�I�u�W�F�N�g<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    protected void validateHandlingMarket(
        WEB3GentradeBranch l_branch,
        WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingMarket(WEB3GentradeBranch, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "," + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //�g�����Z�I�u�W�F�N�g�}�l�[�W��
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        //�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[������B
        //[����]
        //�،���ЃR�[�h�F�@@����.�������.�،���ЃR�[�h
        //�s��R�[�h�F�@@����.�������.�s��R�[�h
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                l_tradedProduct.getInstitutionCode(),
                l_tradedProduct.getMarketCode());
        }
        catch(NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //PTS�s��̏ꍇ(����.�s��.isPTS�s��() == true )�͈ȉ��������s���B
        if (l_market.isPTSMarket())
        {
            //PTS�����}�l�[�W��.validate�戵�\PTS�s����R�[������B
            //[����]
            //���X�F�@@����.���X
            //��������F�@@����.�������
            WEB3EquityPTSOrderManager l_equityPTSOrderManager =
                (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();

            l_equityPTSOrderManager.validateHandlingPossiblePTSMarket(l_branch, l_tradedProduct);
        }
        else
        {
            //�g�����������}�l�[�W��.validate�戵�\�s����R�[������B
            //[����]
            //���X�F�@@����.���X
            //��������F�@@����.�������
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            l_equityOrderManager.validateHandlingMarket(l_branch, l_tradedProduct);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�ڋq�����ʎ����~)<BR>
     * �ڋq�����ʎ����~�`�F�b�N���s���B<BR>
     * �����̎s�ꂪPTS�s��ł��邩�ŃR�[�����郁�\�b�h���Ăѕ�����B<BR>
     * <BR>
     * �P�j�s�ꂪPTS�s��ł��邩�𔻒肷��B<BR>
     * �@@����.�s��.isPTS�s��()���R�[������B<BR>
     * <BR>
     * �@@��PTS�s��̏ꍇ(����.�s��.isPTS�s��() == true )�͈ȉ��������s���B<BR>
     * �@@�@@PTS�����}�l�[�W��.validate�ڋq�����ʎ����~(PTS)()���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�⏕�����F�@@����.�⏕����<BR>
     * �@@�@@����ID�F�@@����.����ID<BR>
     * �@@�@@������ʁF�@@����.�������<BR>
     * <BR>
     * �@@��PTS�s��ȊO�̏ꍇ(����.�s��.isPTS�s��() == false )�͈ȉ��������s���B<BR>
     * �@@�@@�g�����������}�l�[�W��.validate�ڋq�����ʎ����~()���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�⏕�����F�@@����.�⏕����<BR>
     * �@@�@@����ID�F�@@����.����ID<BR>
     * �@@�@@������ʁF�@@����.�������<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_orderType - (�������)<BR>
     * �������<BR>
     * @@param l_market - (�s��)<BR>
     * �s��<BR>
     * @@throws WEB3BaseException
     */
    protected void validateAccountProductOrderStop(
        SubAccount l_subAccount,
        long l_lngProductId,
        OrderTypeEnum  l_orderType,
        WEB3GentradeMarket l_market)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateAccountProductOrderStop(SubAccount, long, OrderTypeEnum, WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);

        if (l_market == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "," + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //PTS�s��̏ꍇ(����.�s��.isPTS�s��() == true )�͈ȉ��������s���B
        if (l_market.isPTSMarket())
        {
            //PTS�����}�l�[�W��.validate�ڋq�����ʎ����~(PTS)()���R�[������B
            //[����]
            //�⏕�����F�@@����.�⏕����
            //����ID�F�@@����.����ID
            //������ʁF�@@����.�������
            WEB3EquityPTSOrderManager l_equityPTSOrderManager =
                (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();

            l_equityPTSOrderManager.validatePTSAccountProductOrderStop(
                l_subAccount, l_lngProductId, l_orderType);
        }
        else
        {
            //�g�����������}�l�[�W��.validate�ڋq�����ʎ����~()���R�[������B
            //[����]
            //�⏕�����F�@@����.�⏕����
            //����ID�F�@@����.����ID
            //������ʁF�@@����.�������
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            l_equityOrderManager.validateAccountProductOrderStop(
                l_subAccount, l_lngProductId, l_orderType);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���������\���)<BR>
     * �����������\�ȏ�Ԃ��ǂ������`�F�b�N����B<BR>
     * �����̎s�ꂪPTS�s��ł��邩�ŃR�[�����郁�\�b�h���Ăѕ�����B<BR>
     * <BR>
     * �P�j�s�ꂪPTS�s��ł��邩�𔻒肷��B<BR>
     * �@@����.�s��.isPTS�s��()���R�[������B<BR>
     * <BR>
     * �@@��PTS�s��̏ꍇ(����.�s��.isPTS�s��() == true )�͈ȉ��������s���B<BR>
     * �@@�@@PTS�����}�l�[�W��.validatePTS���������\���()���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�����F ����.����<BR>
     * <BR>
     * �@@��PTS�s��ȊO�̏ꍇ(����.�s��.isPTS�s��() == false )�͈ȉ��������s���B<BR>
     * �@@�@@�g�����������}�l�[�W��.validate���������\���()���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�����F�@@����.����<BR>
     * <BR>
     * @@param l_order - (����)<BR>
     * ����<BR>
     * @@param l_market - (�s��)<BR>
     * �s��I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    protected void validateOrderForChangeability(
        Order l_order,
        WEB3GentradeMarket l_market)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(Order, WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);

        if (l_market == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "," + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //PTS�s��̏ꍇ(����.�s��.isPTS�s��() == true )�͈ȉ��������s���B
        if (l_market.isPTSMarket())
        {
            //PTS�����}�l�[�W��.validatePTS���������\���()���R�[������B
            //[����]
            //�����F ����.����
            WEB3EquityPTSOrderManager l_equityPTSOrderManager =
                (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();

            l_equityPTSOrderManager.validatePTSOrderForChangeability(l_order);
        }
        else
        {
            //�g�����������}�l�[�W��.validate���������\���()���R�[������B
            //[����]
            //�����F�@@����.����
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            l_equityOrderManager.validateOrderForChangeability(l_order);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate����\�ڋq)<BR>
     * ����\�ڋq���ǂ����`�F�b�N����B<BR>
     * PTS�s�ꂪ�w�肳��Ă���ꍇ�́APTS������ԊǗ�����<BR>
     * ���������擾���A�������w��Ń`�F�b�N���s���B<BR>
     * <BR>
     * �P�j����.�s��R�[�h��null�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[�����A<BR>
     * �@@�@@�s��I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�،���ЃR�[�h:�@@����.�ڋq.getInstitution().getInstitutionCode()<BR>
     * �@@�@@�@@�s��R�[�h:�@@����.�s��R�[�h<BR>
     * <BR>
     * �Q�j�����`�F�b�N�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �R�j�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�R�|�P�j����.�s��R�[�h��null�@@����<BR>
     * �@@�@@�@@�@@�@@�s��.isPTS�s��()��true�@@�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�����`�F�b�N.validate����\�ڋq(�ڋq�A������)���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�ڋq�F�@@����.�ڋq<BR>
     * �@@�@@�@@�������F�@@PTS������ԊǗ�.get������()<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�R�|�P�j�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�����`�F�b�N.validate����\�ڋq(�ڋq)���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�ڋq�F�@@����.�ڋq<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@return OrderValidationResult
     */
    protected OrderValidationResult validateAccountForTrading(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strMarketCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccountForTrading(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017));
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //�P�j����.�s��R�[�h��null�̏ꍇ�A
        WEB3GentradeMarket l_market = null;
        boolean l_blnIsPTSMarket = false;
        if (l_strMarketCode != null)
        {
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[�����A�s��I�u�W�F�N�g�𐶐�����B
            //[�����̐ݒ�]
            //�،���ЃR�[�h:�@@����.�ڋq.getInstitution().getInstitutionCode()
            //�s��R�[�h:�@@����.�s��R�[�h
            try
            {
                l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_strMarketCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005));
            }

            try
            {
                l_blnIsPTSMarket = l_market.isPTSMarket();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        l_ex.getErrorInfo()));
            }
        }

        //�Q�j�����`�F�b�N�I�u�W�F�N�g�𐶐�����B
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //�R�|�P�j����.�s��R�[�h��null�@@���� �s��.isPTS�s��()��true�@@�̏ꍇ
        if (l_strMarketCode != null && l_blnIsPTSMarket)
        {
            //PTS������ԊǗ�.get������()
            Date l_datOrderBizDate = null;
            try
            {
                l_datOrderBizDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
            }
            catch (WEB3SystemLayerException l_ex)
            {
                log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        l_ex.getErrorInfo()));
            }

            //�����`�F�b�N.validate����\�ڋq(�ڋq�A������)���R�[������B
            //[�����̐ݒ�]
            //�ڋq�F�@@����.�ڋq
            //�������F�@@PTS������ԊǗ�.get������()
            OrderValidationResult l_validationPtsResult =
                l_gentradeOrderValidator.validateAccountForTrading(
                    l_mainAccount,
                    new Timestamp(l_datOrderBizDate.getTime()));

            log.exiting(STR_METHOD_NAME);
            return l_validationPtsResult;
        }
        else
        {
            //�ȊO�̏ꍇ�����`�F�b�N.validate����\�ڋq(�ڋq)���R�[������B
            //[�����̐ݒ�]
            //�ڋq�F�@@����.�ڋq
            OrderValidationResult l_validationResult =
                l_gentradeOrderValidator.validateAccountForTrading(l_mainAccount);

            log.exiting(STR_METHOD_NAME);
            return l_validationResult;
        }
    }

    /**
     * (������t���)<BR>
     * ������t��ԃN���X<BR>
     * <BR>
     * �e����̒�����t��Ԃ�ێ�����B<BR>
     * <BR>
     */
    public class OrderStatus
    {

       /**
        * (���������t���O)<BR>
        * ���������̒�����t��Ԃ�\���t���O<BR>
        * <BR>
        * true�F ��t��<BR>
        * false�F ��t�s��<BR>
        * <BR>
        */
       public boolean interestEquityFlag = false;

       /**
        * (����O�����t���O)<BR>
        * ����O�����̒�����t��Ԃ�\���t���O<BR>
        * <BR>
        * true�F ��t��<BR>
        * false�F ��t�s��<BR>
        * <BR>
        */
       public boolean offFloor = false;

       /**
        * (�M�p����t���O)<BR>
        * �M�p����̒�����t��Ԃ�\���t���O<BR>
        * <BR>
        * true�F ��t��<BR>
        * false�F ��t�s��<BR>
        * <BR>
        */
       public boolean interestMarginFlag = false;

       /**
        * (�����E���n�t���O)<BR>
        * �����E���n�̒�����t��Ԃ�\���t���O<BR>
        * <BR>
        * true�F ��t��<BR>
        * false�F ��t�s��<BR>
        * <BR>
        */
       public boolean swapFlag = false;

       /**
        * (������t���)<BR>
        * �R���X�g���N�^<BR>
        * <BR>
        * @@roseuid 455D940D01A6
        */
       public OrderStatus()
       {

       }
    }

    /**
     * ��̔z�����̔z��ɍ������āA�����\�[�g�ŕԋp����
     * @@param l_strArraySrc
     * @@param l_strDest
     * @@return String[]
     */
    private String[] mergeAndSort(String[] l_strArraySrcs, String[] l_strDests)
    {
        final String STR_METHOD_NAME = "mergeAndSort(String[], String[])";
        log.entering(STR_METHOD_NAME);
        
         Object[] l_objMergeArrays =
             WEB3Toolkit.toUnique(WEB3Toolkit.merge(l_strArraySrcs, l_strDests));

         if (l_objMergeArrays == null)
         {
             return null;
         }

         String[] l_strResults = new String[l_objMergeArrays.length];

         for (int i = 0; i < l_objMergeArrays.length; i++)
         {
             l_strResults[i] = (String)l_objMergeArrays[i];
         }

         String l_strTemp = null;
         for (int i = 0; i < l_strResults.length; i++)
         {
             for (int j = i + 1; j < l_strResults.length; j++)
             {
                 if (Integer.parseInt(l_strResults[i]) > Integer.parseInt(l_strResults[j]))
                 {
                     l_strTemp = l_strResults[j];
                     l_strResults[j] = l_strResults[i];
                     l_strResults[i] = l_strTemp;
                 }
             }
         }

         return l_strResults;
    }
}
@
