head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����V�K�����̓T�[�r�XImpl(WEB3MarginOpenMarginInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���Ō� (Sinocom) �V�K�쐬 
                 : 2006/11/24 ������ (Sinocom)�@@���f��No.993
                 : 2007/01/11 ��іQ (���u) ���f�� No.1083,1088,1089
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginOpenMarginInputRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginInputResponse;
import webbroker3.equity.message.WEB3MarginProductSelectRequest;
import webbroker3.equity.message.WEB3MarginProductSelectResponse;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginInputService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.define.WEB3GentradeEquityMarginDivDef;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;

/**
 * �i�M�p����V�K�����̓T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p����V�K�����̓T�[�r�X�����N���X�B
 * @@author ���Ō�
 * @@version 1.0
 */
public class WEB3MarginOpenMarginInputServiceImpl extends WEB3MarginClientRequestService
    implements WEB3MarginOpenMarginInputService
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginOpenMarginInputServiceImpl.class);

    /**
     * �i�R���X�g���N�^�j�B
     * @@roseuid 4140066F0147
     */
    public WEB3MarginOpenMarginInputServiceImpl()
    {

    }

    /**
     * �iexecute�j�B<BR>
     * <BR>
     * �M�p����V�K�����̓T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Aget�����I�����()�܂��́Aget�V�K�����͉��()<BR>
     * ���\�b�h�̂����ꂩ���R�[������B
     * @@param l_request ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407BBCFA0140
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = getClass().getName() + "." + "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3MarginProductSelectRequest)
        {
            l_response = this.getProductSelectScreen((WEB3MarginProductSelectRequest) l_request);
        }
        else if (l_request instanceof WEB3MarginOpenMarginInputRequest)
        {
            l_response = this.getOpenMarginInputScreen((WEB3MarginOpenMarginInputRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        return l_response;
    }

    /**
     * (get�����I�����)�B<BR>
     * <BR>
     * �M�p����̖����I����ʕ\���T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����V�K�����̓T�[�r�X�jget�����I����ʁv�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�M�p����V�K�����̓T�[�r�X�jget�����I����ʁv): <BR>
     *     3.validate�M�p����(�⏕���� : �⏕����, �ٍϋ敪 : String)<BR>
     *     �ٍϋ敪�́hDEFAULT�h�i�w��Ȃ��j�Őݒ肷��B����ʐM�p�A<BR>
     *     ���x�M�p�ǂ�������{���Ă��Ȃ����X�E�ڋq�̏ꍇ�G���[�Ƃ��A��O��throw����B<BR>
     *         class: WEB3BusinessLayerException<BR>                                                                                           
     *         tag:   BUSINESS_ERROR_00644<BR>                                                                                                 
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�M�p����V�K�����̓T�[�r�X�jget�����I����ʁv): <BR>            
     *     7.get�戵�\�s��(���X : ���X, �ٍϋ敪 : String, �ٍϊ����l : double)<BR>   
     *     �戵�\�s�ꂪ�ꌏ�����݂��Ȃ��ꍇ�́A�u�戵�\�s��Ȃ��v�̗�O��throw����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00643<BR>
     * ==========================================================
     * @@param l_request ���N�G�X�g�f�[�^
     * @@return WEB3MarginProductSelectResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F64A4A031E
     */
    protected WEB3MarginProductSelectResponse getProductSelectScreen(WEB3MarginProductSelectRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductSelectScreen()";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //�g�����������}�l�[�W��
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

        //validate�M�p����
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //�ٍϋ敪�F�@@�hDEFAULT�h�i�w��Ȃ��j
        l_orderManager.validateMarginOrder(l_subAccount, WEB3GentradeRepaymentDivDef.DEFAULT);
        log.debug("validate�M�p���������s���܂�");

        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();

        //�Y���ڋq�����x�M�p����������J�݂��Ă��邩�𔻒肷��B
        //�ٍϋ敪�F�h���x�M�p�h
        boolean l_blnIsMarketMarginAccount =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
        log.debug("���x�M�p��������J�� = " + l_blnIsMarketMarginAccount);

        //�Y���ڋq����ʐM�p����������J�݂��Ă��邩�𔻒肷��B
        //�ٍϋ敪�F�h��ʐM�p�h
        boolean l_blnIsInstitutionMarginAccount =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
        log.debug("��ʐM�p��������J�� = " + l_blnIsInstitutionMarginAccount);

        //�M�p����敪:
        //�E���x�M�p�A��ʐM�p�Ƃ������J�݂��Ă���ꍇ�A�h���x�^��ʐM�p(����)�h�B 
        //    (�ڋq.is�M�p�����J��(�h���x�M�p�h)==true�A���A�ڋq.is�M�p�����J��(�h��ʐM�p�h)==true)
        //�E���x�M�p�̂݌����J�݂��Ă���ꍇ�A�h���x�M�p�h�B 
        //    (�ڋq.is�M�p�����J��(�h���x�M�p�h)==true�A���A�ڋq.is�M�p�����J��(�h��ʐM�p�h)==false) 
        //�E��ʐM�p�̂݌����J�݂��Ă���ꍇ�A�h��ʐM�p�h�B 
        //    (�ڋq.is�M�p�����J��(�h���x�M�p�h)==false�A���A�ڋq.is�M�p�����J��(�h��ʐM�p�h)==true) 
        String l_strMarginTradingDiv = null;

        if (l_blnIsMarketMarginAccount && l_blnIsInstitutionMarginAccount)
        {
            l_strMarginTradingDiv = WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN;
        }
        else if (l_blnIsMarketMarginAccount && !l_blnIsInstitutionMarginAccount)
        {
            l_strMarginTradingDiv = WEB3MarginTradingDivDef.MARKET_MARGIN;
        }
        else if (!l_blnIsMarketMarginAccount && l_blnIsInstitutionMarginAccount)
        {
            l_strMarginTradingDiv = WEB3MarginTradingDivDef.INSTITUTION_MARGIN;
        }
        log.debug("�M�p����敪 = " + l_strMarginTradingDiv);

        // get�s��ǌx���s��
        String[] l_strTradeCloseMarkets =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY, l_strMarginTradingDiv);

        //get�戵�\�s��
        String[] l_strHandlingPossibleMarkets =
            WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(
            l_subAccount.getWeb3GenBranch(), WEB3GentradeRepaymentDivDef.DEFAULT, 0D);
        //0�����̓G���[
        if (l_strHandlingPossibleMarkets == null || l_strHandlingPossibleMarkets.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00643,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �������
        OrderTypeEnum l_orderTypeEnum = null;
        boolean l_flg = false;
        try
        {
            log.debug("1.7 validate�ڋq�����ʎ����~�i�V�K���������j");
            log.debug("����敪���A�V�K���������Ƃ��ď������܂��B");
            l_orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
            l_orderManager.validateAccountProductOrderStop(l_subAccount, 0, l_orderTypeEnum);
            l_flg = true;
        }
        catch (WEB3BaseException l_be)
        {
            log.debug("1.8 validate�ڋq�����ʎ����~�i�V�K���������j");
            log.debug("����敪���A�V�K���������Ƃ��ď������܂��B");
            l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
            l_orderManager.validateAccountProductOrderStop(l_subAccount, 0, l_orderTypeEnum);
        }
        catch (Exception ex)
        {
            log.debug("1.8 validate�ڋq�����ʎ����~�i�V�K���������j");
            log.debug("����敪���A�V�K���������Ƃ��ď������܂��B");
            l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
            l_orderManager.validateAccountProductOrderStop(l_subAccount, 0, l_orderTypeEnum);
        }

        if (l_flg)
        {
            try
            {
                log.debug("1.8 validate�ڋq�����ʎ����~�i�V�K���������j");
                log.debug("����敪���A�V�K���������Ƃ��ď������܂��B");
                l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
                l_orderManager.validateAccountProductOrderStop(l_subAccount, 0, l_orderTypeEnum);
            }
            catch (WEB3BaseException l_be)
            {
            }
            catch (Exception ex)
            {
            }
        }

        WEB3MarginProductSelectResponse l_productSelectResponse =
            (WEB3MarginProductSelectResponse) l_request.createResponse();

        //�s��R�[�h�ꗗ�F�@@(���X�s��ٍϕ�)�戵����.get�戵�\�s��( )�̖߂�l
        //����I���x���s��R�[�h�ꗗ�F�@@������ԊǗ�.get�s��ǌx���s
        l_productSelectResponse.messageSuspension = l_strTradeCloseMarkets;
        l_productSelectResponse.marketList = l_strHandlingPossibleMarkets;

        log.exiting(STR_METHOD_NAME);
        return l_productSelectResponse;
    }

    /**
     * �iget�V�K�����͉�ʁj�B<BR>
     * <BR>
     * �M�p����̐V�K�����͉�ʕ\���T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����V�K�����̓T�[�r�X�jget�V�K�����͉�ʁv�Q�ƁB<BR>
     * <BR>
     * <BR>
     *  ========================================================<BR>                                                           
     * �V�[�P���X�}(�u�i�M�p����V�K�����̓T�[�r�X�jget�V�K�����͉�ʂP�v): <BR>                                               
     *        (12*) �iget�戵�\�s��( )�̖߂�l�z��̗v�f����Loop���A<BR>
     *        ���N�G�X�g�f�[�^.�s��R�[�h���܂܂�Ă��邩�`�F�b�N�j<BR>
     *        ���N�G�X�g�f�[�^.�s��R�[�h���߂�l�z��Ɋ܂܂�Ă��Ȃ��ꍇ�F<BR>
     *       �u��戵�s��G���[�v�̗�O��throw����B<BR>         
     *         class: WEB3BusinessLayerException<BR>                                                                           
     *         tag:   BUSINESS_ERROR_00645<BR>                                                                                 
     * ==========================================================<BR>                                                          
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}(�u�i�M�p����V�K�����̓T�[�r�X�jget�V�K�����͉�ʂP�v): <BR>
     * (22*) get�戵�\�s��(���X : ���X, �ٍϋ敪 : String, �ٍϊ����l : double)<BR>
     * �戵�\�s�ꂪ�ꌏ�����݂��Ȃ��ꍇ�́A�u�戵�\�s��Ȃ��v�̗�O��throw����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_00643<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *    �V�[�P���X�}(�u�i�M�p����V�K�����̓T�[�r�X�jget�V�K�����͉�ʂP�v): <BR>
     *�@@ (28*)validateJASDAQ�����戵�\(���X)<BR>
     *   (�����A�戵�\�s��`�F�b�N)<BR>
     *   �P�j�@@����敪���w�肳��Ă���ꍇ(���N�G�X�g�f�[�^.����敪 != null)<BR>
     *   �Evalidate��������i�M�p�j����O��throw<BR>
     *   �EvalidateJASDAQ�����戵�\( )����O��throw<BR>
     *   ��L�̂����ꂩ�̏ꍇ�A���Y�s��R�[�h�����X�|���X.�s��R�[�h�ꗗ�̐ݒ�ΏۊO�Ƃ��ď�������B<BR>
     *   <BR>
     *   �Q�j�@@����敪���w�肳��Ă��Ȃ��ꍇ(���N�G�X�g�f�[�^.����敪 == null)<BR>
     *    �Evalidate��������i�M�p�j(is����(=true�j�j��validate��������i�M�p�j(is����(=false�j�j���ǂ������O��throw<BR>
     *   �EvalidateJASDAQ�����戵�\( )����O��throw<BR>
     *   ��L�̂����ꂩ�̏ꍇ�A���Y�s��R�[�h�����X�|���X.�s��R�[�h�ꗗ�̐ݒ�ΏۊO�Ƃ��ď�������B<BR>
     *   <BR>
     *   ��validate�戵�\�s��̖߂�l�̎s��R�[�h�S�Ăɑ΂��A�@@��L(�����A�戵�\�s��`�F�b�N)���s�������ʁA<BR>
     *   �s��R�[�h�z��̃T�C�Y��0�ɂȂ����ꍇ�A�u��戵�����v�G���[�̗�O��throw����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00664<BR>
     *   ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *   �V�[�P���X�}(�u�i�M�p����V�K�����̓T�[�r�X�jget�V�K�����͉�ʂP�v): <BR>
     *  (29*) get�戵�\�ٍ�(���X : ���X, �ٍϋ敪 : String, �s��R�[�h�ꗗ : String[])<BR>
     *  �戵�\�ٍς��ꌏ�����݂��Ȃ��ꍇ�́A�u�戵�\�ٍςȂ��v�̗�O��throw����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00646<BR>
     *   ==========================================================
     * @@param l_request ���N�G�X�g�f�[�^
     * @@return WEB3MarginOpenMarginInputResponse
     * @@roseuid 40F64A4A033D
     */
    protected WEB3MarginOpenMarginInputResponse getOpenMarginInputScreen(
        WEB3MarginOpenMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOpenMarginInputScreen(WEB3MarginOpenMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //�⏕�������擾
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //get�s��R�[�h(String, String, String)
        l_request.marketCode = this.getMarketCode(
            l_request.productCode,
            l_request.marketCode,
            l_subAccount.getInstitution().getInstitutionCode());

        //reset�s��R�[�h(�s��R�[�h : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);

        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        WEB3EquityProductManager l_prodcutManager =
            (WEB3EquityProductManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        //validate�M�p����
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //�ٍϋ敪�F�@@�hDEFAULT�h�i�w��Ȃ��j
        l_orderManager.validateMarginOrder(l_subAccount, WEB3GentradeRepaymentDivDef.DEFAULT);

        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();

        //�Y���ڋq�����x�M�p����������J�݂��Ă��邩�𔻒肷��B
        //�ٍϋ敪�F�h���x�M�p�h
        boolean l_blnIsMarketMarginAccount =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
        log.debug("is�M�p�����J��(���x�M�p)= " + l_blnIsMarketMarginAccount);

        //�Y���ڋq����ʐM�p����������J�݂��Ă��邩�𔻒肷��B
        //�ٍϋ敪�F�h��ʐM�p�h
        boolean l_blnIsInstitutionMarginAccount =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
        log.debug("is�M�p�����J��(��ʐM�p) = " + l_blnIsInstitutionMarginAccount);

        //�ٍϋ敪�F 
        //�E���x�M�p�A��ʐM�p�Ƃ������J�݂��Ă���ꍇ�A�hDEFAULT�h�B 
        //    (�ڋq.is�M�p�����J��(�h���x�M�p�h)==true�A���A�ڋq.is�M�p�����J��(�h��ʐM�p�h)==true) 
        //�E���x�M�p�̂݌����J�݂��Ă���ꍇ�A�h���x�M�p�h�B 
        //    (�ڋq.is�M�p�����J��(�h���x�M�p�h)==true�A���A�ڋq.is�M�p�����J��(�h��ʐM�p�h)==false) 
        //�E��ʐM�p�̂݌����J�݂��Ă���ꍇ�A�h��ʐM�p�h�B 
        //    (�ڋq.is�M�p�����J��(�h���x�M�p�h)==false�A���A�ڋq.is�M�p�����J��(�h��ʐM�p�h)==true)
        String l_strPayTypeDiv = null;

        if (l_blnIsMarketMarginAccount && l_blnIsInstitutionMarginAccount)
        {
            l_strPayTypeDiv = WEB3GentradeRepaymentDivDef.DEFAULT;
        }
        else if (l_blnIsMarketMarginAccount && !l_blnIsInstitutionMarginAccount)
        {
            l_strPayTypeDiv = WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS;
        }
        else if (!l_blnIsMarketMarginAccount && l_blnIsInstitutionMarginAccount)
        {
            l_strPayTypeDiv = WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN;
        }
        log.debug("�ٍϋ敪 = " + l_strPayTypeDiv);

        WEB3EquityProduct l_product = null;
        //(*1)����t���[
        //�������w�肳��Ă���ꍇ�̂݁A���������{����B
        //�i���N�G�X�g�f�[�^.�����R�[�h != null)
        log.debug("l_request.productCode = " + l_request.productCode);

        boolean l_boolIsInsider;
        if (l_request.productCode != null)
        {
            //validate�����R�[�h
            l_product = l_orderManager.validateProductCode(
                l_request.productCode, l_mainAccount.getInstitution().getInstitutionCode(), l_strPayTypeDiv);
            //1.17is�C���T�C�_�[�x���\��
            l_boolIsInsider = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_product.getProductId());
        }
        else
        {
            l_boolIsInsider = false;
        }

        log.debug("1.7 validate�ڋq�����ʎ����~���s���B");
        // �����h�c
        long l_lngProductId = 0;
        if (l_request.productCode != null)
        {
            l_lngProductId = l_product.getProductId();
        }
        // �������
        OrderTypeEnum l_orderTypeEnum = null;
        if (l_request.tradingType == null)
        {
            log.debug("����敪���m�t�k�k�ł��B");
            try
            {
                log.debug("����敪���A�V�K���������Ƃ��ď������܂��B");
                l_orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
                l_orderManager.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_orderTypeEnum);
            }
            catch (WEB3BaseException l_be)
            {
                log.debug("����敪���A�V�K���������ŗ�O�����������̂ŁA�V�K���������Ƃ��ď������܂��B");
                l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
                l_orderManager.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_orderTypeEnum);
            }
        }
        else
        {
            if (l_request.tradingType.equals("3"))
            {
                log.debug("����敪�́A�V�K���������ł��B");
                l_orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
            }
            else
            {
                log.debug("����敪�́A�V�K���������ł��B");
                l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
            }
            l_orderManager.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_orderTypeEnum);
        }

        WEB3GentradeMarket l_market = null;
        //�s�ꂪ�w�肳��Ă���ꍇ�̂݁A���������{����B
        //(���N�G�X�g�f�[�^.�s��R�[�h != null)
        log.debug("���N�G�X�g�f�[�^.�s��R�[�h = " + l_request.marketCode);
        if (l_request.marketCode != null)
        {
            log.debug("���N�G�X�g�f�[�^.�s��R�[�h != null�̏ꍇ");
            log.debug("�ٍϋ敪 = " + l_strPayTypeDiv);

            //get�戵�\�s��
            String[] l_strHandlingPossibleMarkets =
                WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(
                l_branch, l_strPayTypeDiv, 0D);

            log.debug("�戵�\�s�� = " + l_strHandlingPossibleMarkets);
            if (l_strHandlingPossibleMarkets == null || l_strHandlingPossibleMarkets.length == 0)
            {
                //�戵�\�s�ꂪ�ꌏ�����݂��Ȃ��ꍇ�́A�u�戵�\�s��Ȃ��v�̗�O��throw����B
                log.debug("�戵�\�s�ꂪ�ꌏ�����݂��Ȃ��ꍇ");
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00643, STR_METHOD_NAME);
            }

            //get�戵�\�s��( )�̖߂�l�z��̗v�f����Loop���A
            //���N�G�X�g�f�[�^.�s��R�[�h���܂܂�Ă��邩�`�F�b�N
            int l_intLen = l_strHandlingPossibleMarkets.length;
            log.debug("�戵�\�s��̗v�f�� = " + l_intLen);
            for (int i = 0; i < l_intLen; i++)
            {
                log.debug("l_request.marketCode  = " + l_request.marketCode);
                log.debug("l_strHandlingPossibleMarkets[i]  = " + l_strHandlingPossibleMarkets[i]);
                if (l_request.marketCode.equals(l_strHandlingPossibleMarkets[i]))
                {
                    break;
                }
                if (i == l_intLen - 1)
                {
                    //���N�G�X�g�f�[�^.�s��R�[�h���߂�l�z��Ɋ܂܂�Ă��Ȃ��ꍇ�F
                    //�u��戵�s��G���[�v�̗�O��throw����B
                    log.debug("���N�G�X�g�f�[�^.�s��R�[�h���߂�l�z��Ɋ܂܂�Ă��Ȃ��ꍇ");
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00645, STR_METHOD_NAME);
                }
            }

            //validate�s��R�[�h
            l_market = (WEB3GentradeMarket) l_orderManager.validateMarket(
                l_request.marketCode, l_subAccount.getInstitution().getInstitutionCode());
        }

        String[] l_orderPriceDivList = null;
        WEB3EquityTradedProduct l_tradedProduct = null;
        boolean l_blnIsSpecialAccountEstablished = false;
        //�����Ǝs�ꂪ�����w�肳��Ă���ꍇ�̂݁A���������{����B
        //(���N�G�X�g�f�[�^.�����R�[�h != null�A���A���N�G�X�g�f�[�^.�s��R�[�h != null)
        if (l_request.productCode != null && l_request.marketCode != null)
        {
            log.debug("���N�G�X�g�f�[�^.�����R�[�h != null�A���A���N�G�X�g�f�[�^.�s��R�[�h != null�̏ꍇ");

            log.debug("l_request.tradingType = " + l_request.tradingType);
            if (l_request.tradingType == null)
            {
                //����敪���w�肳��Ă��Ȃ��ꍇ(���N�G�X�g�f�[�^.����敪 == null�j�̂݁A���{����
                log.debug("����敪���w�肳��Ă��Ȃ��ꍇ");

                //validate�������(�M�p)�ɂ��ẮA
                //validate��������i�M�p�j(is����(=true�j�j��validate��������i�M�p�j(is����(=false�j)�̃`�F�b�N�s���B
                //is����(=true�j
                try
                {
                    l_tradedProduct = l_orderManager.validateTradedProductForMarginTrading(
                        l_subAccount, l_product, l_market, l_branch, l_strPayTypeDiv,
                        OrderCategEnum.OPEN_MARGIN, true);
                }
                catch (WEB3BaseException l_wbex)
                {
                    log.debug(STR_METHOD_NAME, l_wbex);
                    //is����(=false�j
                    l_tradedProduct = l_orderManager.validateTradedProductForMarginTrading(
                        l_subAccount, l_product, l_market, l_branch, l_strPayTypeDiv,
                        OrderCategEnum.OPEN_MARGIN, false);
                }
            }
            else
            {

                if (WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN.equals(l_request.tradingType))
                {
                    //is����(=true�j
                    l_tradedProduct = l_orderManager.validateTradedProductForMarginTrading(
                        l_subAccount, l_product, l_market, l_branch, l_strPayTypeDiv,
                        OrderCategEnum.OPEN_MARGIN, true);
                }
                else
                {
                    //is����(=false�j
                    l_tradedProduct = l_orderManager.validateTradedProductForMarginTrading(
                        l_subAccount, l_product, l_market, l_branch, l_strPayTypeDiv,
                        OrderCategEnum.OPEN_MARGIN, false);
                }
            }

            //validateJASDAQ�����戵�\
            l_tradedProduct.validateJASDAQProductHandling(l_branch);
            log.debug("validateJASDAQ�����戵�\�����s���܂�");

            //is��������J��
            l_blnIsSpecialAccountEstablished = l_mainAccount.isSpecialAccountEstablished(
                l_tradedProduct.getDailyDeliveryDate(), l_subAccount, WEB3GentradeEquityMarginDivDef.MARGIN);

            // get�����P���敪�ꗗ
            l_orderPriceDivList = l_orderManager.getOrderPriceDivs(l_branch, l_tradedProduct);
        }
        else
        {
            //�i���N�G�X�g�f�[�^.�����R�[�h == null�A�܂��́A���N�G�X�g�f�[�^.�s��R�[�h == null)�̏ꍇ
            log.debug("�i���N�G�X�g�f�[�^.�����R�[�h == null�A�܂��́A���N�G�X�g�f�[�^.�s��R�[�h == null)�̏ꍇ");
            //is��������J��
            l_blnIsSpecialAccountEstablished =
                l_mainAccount.isSpecialAccountEstablished(l_subAccount, WEB3GentradeEquityMarginDivDef.MARGIN);
        }
        log.debug("l_blnIsSpecialAccountEstablished = " + l_blnIsSpecialAccountEstablished);

        //get�V�K���\�z
        double l_dblOpenMarginPossiblePrice = this.getMarginTradingPower(l_subAccount);

        log.debug("�M�p�V�K���\�z = " + l_dblOpenMarginPossiblePrice);

        //get�戵�\�s��
        String[] l_strHandlingPossibleMarkets =
            WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(
            l_branch, WEB3GentradeRepaymentDivDef.DEFAULT, 0D);

        log.debug("�戵�\�s����擾 = " + l_strHandlingPossibleMarkets);
        if (l_strHandlingPossibleMarkets == null || l_strHandlingPossibleMarkets.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00643, STR_METHOD_NAME);
        }

        List l_lisHandlingPossibleMarkets = new ArrayList();

        // �s�ꖈ�̔������ꗗ���쐬�iget�o����܂Œ����J�n���^�ŏI���^�j���ꗗ�Ŏg�p�j
        List l_marketOrderBizDateList = new ArrayList();
        if (l_request.marketCode == null)
        {
			int l_intLen = l_strHandlingPossibleMarkets.length;
			for (int i = 0; i < l_intLen; i++)
			{
				WEB3GentradeTradingTimeManagement.resetMarketCode(l_strHandlingPossibleMarkets[i]);
				Date l_mktBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
				l_marketOrderBizDateList.add(l_mktBizDate);
				log.debug("�s��R�[�h[" + l_strHandlingPossibleMarkets[i] + "]�̔�����" + "�F"
				    + l_mktBizDate.toString());
			}
        }

        //�������w�肳��Ă���ꍇ�̂݁A���������{����B
        //�i���N�G�X�g�f�[�^.�����R�[�h != null)
        WEB3GentradeMarket l_wkMarket = null;
        if (l_request.productCode != null)
        {
            int l_intLen = l_strHandlingPossibleMarkets.length;
            for (int i = 0; i < l_intLen; i++)
            {
                log.debug("@@@@@@@@@@@@@@" + i + "@@@@@@@@@@@@@@");
                //reset�s��R�[�h
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strHandlingPossibleMarkets[i]);
                log.debug("�s��R�[�h = " + l_strHandlingPossibleMarkets[i]);

                try
                {
                    l_wkMarket = (WEB3GentradeMarket) l_finObjectManager.getMarket(
                        l_subAccount.getInstitution().getInstitutionCode(), l_strHandlingPossibleMarkets[i]);
                    //�Evalidate�����������O��throw
                    //��L�̏ꍇ�A���Y�s��R�[�h�����X�|���X.�s��R�[�h�ꗗ�̐ݒ�ΏۊO�Ƃ��ď�������    
                    l_tradedProduct =
                        (WEB3EquityTradedProduct)l_orderManager.validateTradedProduct(l_product, l_wkMarket);
                }
                catch (WEB3BaseException l_webex)
                {
                    log.debug(STR_METHOD_NAME, l_webex);
                    continue;
                }
                catch (NotFoundException l_nfe)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                l_lisHandlingPossibleMarkets.add(l_strHandlingPossibleMarkets[i]);
            }
            log.debug("l_lisHandlingPossibleMarkets.size() = " + l_lisHandlingPossibleMarkets.size());
            if (l_lisHandlingPossibleMarkets.size() == 0)
            {
                //�@@��L(�����A�戵�\�s��`�F�b�N)���s�������ʁA�s��R�[�h�z��̃T�C�Y��0�ɂȂ����ꍇ�A
                // �u��戵�����v�G���[�̗�O��throw����B
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00664, STR_METHOD_NAME);
            }
        }
        else
        {
            l_lisHandlingPossibleMarkets = null;
        }

        //get�戵�\�ٍ�
        WEB3MarginRepaymentUnit[] l_repaymentUnit =
            this.getHandlingRepayment(l_branch, l_strPayTypeDiv, l_strHandlingPossibleMarkets);

        if (l_repaymentUnit == null || l_repaymentUnit.length == 0)
        {
            //�戵�\�ٍς��ꌏ�����݂��Ȃ��ꍇ�́A
            //�u�戵�\�ٍςȂ��v�̗�O��throw����B
            log.debug("�戵�\�ٍς��ꌏ�����݂��Ȃ��ꍇ");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00646, STR_METHOD_NAME);
        }

        //�M�p����敪:
        //�E���x�M�p�A��ʐM�p�Ƃ������J�݂��Ă���ꍇ�A�h���x�^��ʐM�p(����)�h�B 
        //    (�ڋq.is�M�p�����J��(�h���x�M�p�h)==true�A���A�ڋq.is�M�p�����J��(�h��ʐM�p�h)==true)
        //�E���x�M�p�̂݌����J�݂��Ă���ꍇ�A�h���x�M�p�h�B 
        //    (�ڋq.is�M�p�����J��(�h���x�M�p�h)==true�A���A�ڋq.is�M�p�����J��(�h��ʐM�p�h)==false) 
        //�E��ʐM�p�̂݌����J�݂��Ă���ꍇ�A�h��ʐM�p�h�B 
        //    (�ڋq.is�M�p�����J��(�h���x�M�p�h)==false�A���A�ڋq.is�M�p�����J��(�h��ʐM�p�h)==true) 
        String l_strMarginTradingDiv = null;

        if (l_blnIsMarketMarginAccount && l_blnIsInstitutionMarginAccount)
        {
            l_strMarginTradingDiv = WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN;
        }
        else if (l_blnIsMarketMarginAccount && !l_blnIsInstitutionMarginAccount)
        {
            l_strMarginTradingDiv = WEB3MarginTradingDivDef.MARKET_MARGIN;
        }
        else if (!l_blnIsMarketMarginAccount && l_blnIsInstitutionMarginAccount)
        {
            l_strMarginTradingDiv = WEB3MarginTradingDivDef.INSTITUTION_MARGIN;
        }

        //get�s��ǌx���s��
        String[] l_strTradeCloseMarkets = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY, l_strMarginTradingDiv);
        log.debug("�s��ǌx���s�� = " + l_strTradeCloseMarkets);

        //�戵�\���������𐶐�
        WEB3GentradeHandlingOrderCond l_handlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                WEB3MarketCodeDef.DEFAULT);

        //�M�p����V�K���������̓��X�|���X�ɉ��L�̒ʂ�v���p�e�B���Z�b�g����B
        WEB3MarginOpenMarginInputResponse l_openMarginInputResponse =
            (WEB3MarginOpenMarginInputResponse) l_request.createResponse();

        //�����P���敪�ꗗ
        if (l_request.productCode != null && l_request.marketCode != null)
        {
        }
        else
        {
            l_orderPriceDivList = new String[2];
            l_orderPriceDivList[0] = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_orderPriceDivList[1] = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        l_openMarginInputResponse.orderPriceDivList = l_orderPriceDivList;

        //�l�i�����敪�ꗗ
        String[] l_strPriceCondList = l_handlingOrderCond.getHandlingPriceCond();
        l_openMarginInputResponse.priceCondList = l_strPriceCondList;

        //���s�����ꗗ�F�@@�戵�\��������.�戵�\���s�����擾( )�̖߂�l�z��
        l_openMarginInputResponse.execCondList = l_handlingOrderCond.getHandlingPossibleExecCond();

        //get�v�w�l�p���s�����ꗗ(String[], String[])
        //�v�w�l�p���s�����ꗗ���擾����B
        //�����͈ȉ��̒ʂ�ݒ肷��B
        //���s�����ꗗ�F�@@�戵�\��������.�戵�\���s�����擾( )�̖߂�l
        //���������ꗗ�F�@@�戵�\��������.�戵�\���������擾( )�̖߂�l
        String[] l_strWLimitExecutionConditionTypeList =
            WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(
                l_handlingOrderCond.getHandlingPossibleExecCond(),
                l_handlingOrderCond.getHandlingPossibleOrderCond());

        //W�w�l�p���s�����ꗗ�F�@@�����f�[�^�A�_�v�^.get�v�w�l�p���s�����ꗗ( )�̖߂�l�z��
        l_openMarginInputResponse.wlimitExecCondList =
            l_strWLimitExecutionConditionTypeList;

        //�L�������J�n���F�@@(**1)�戵�\��������.get�o����܂Œ����J�n��( )�̖߂�l
        //�L�������ŏI���F�@@(**1)�戵�\��������.get�o����܂Œ����ŏI��( )�̖߂�l
        //�L���������j���ꗗ�F�@@(**1)�戵�\��������.get�����������j���ꗗ( )�̖߂�l�z��
        //(**1)�@@�o����܂Œ����戵�\(�戵�\��������.is�o����܂Œ����戵�\( ) == true)�̏ꍇ�̂ݐݒ�B
        //�ȊO�Anull��ݒ�B

        if (l_handlingOrderCond.isOrderUntilDeadLinePossibleHandling())
        {
            //reset�s��R�[�h(
			WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);

            //�o����܂Œ���from���t�F            
            //���N�G�X�g�f�[�^.�s��R�[�h == null�̏ꍇ�A�ȉ��̂悤�ɃZ�b�g����B
            Date l_datOrderUntilDeadLineFromDate = null;
            Timestamp l_datSysTimeStamp = l_finApp.getTradingSystem().getSystemTimestamp();
            if (l_request.marketCode == null)
            {
                for (int i = 0; i < l_marketOrderBizDateList.size(); i++)
                {
                    //��t���t�Ɠ����������������݂���ꍇ�͎�t���t���Z�b�g
                    if (WEB3DateUtility.compareToDay((Date) l_marketOrderBizDateList.get(i),
                        l_datSysTimeStamp) == 0)
                    {
                        l_datOrderUntilDeadLineFromDate = WEB3DateUtility.toDay(l_datSysTimeStamp);
                        log.debug("��t���t�𔭒����Ƃ���F[" + l_datOrderUntilDeadLineFromDate.toString() + "]");
                        break;
                    }
                }
                //�S����������t���t�ƈقȂ�ꍇ�͎�t���t�̗��c�Ɠ����Z�b�g
                if (l_datOrderUntilDeadLineFromDate == null)
                {
                    WEB3GentradeBizDate l_genBizDat = new WEB3GentradeBizDate(l_datSysTimeStamp);
                    l_datOrderUntilDeadLineFromDate = WEB3DateUtility.toDay(l_genBizDat.roll(1));
                    log.debug("��t���t�̗��c�Ɠ��𔭒����Ƃ���F["
                        + l_datOrderUntilDeadLineFromDate.toString() + "]");
                }
            }
            //�ȊO�Anull���Z�b�g�B
            else
            {
                l_datOrderUntilDeadLineFromDate = null;
            }

            //�L�������J�n��
            l_openMarginInputResponse.expirationStartDate =
                WEB3DateUtility.toDay(l_handlingOrderCond.getOrderUntilDeadLineStartDay(
                l_datOrderUntilDeadLineFromDate));

            //�L�������ŏI��
            l_openMarginInputResponse.expirationEndDate =
                WEB3DateUtility.toDay(l_handlingOrderCond.getOrderUntilDeadLineEndDay(
                l_datOrderUntilDeadLineFromDate));

            //�L���������j���ꗗ
            l_openMarginInputResponse.holidayList =
                l_handlingOrderCond.getExpirationDateHoliday(l_datOrderUntilDeadLineFromDate);
        }
        else
        {
            //�L�������J�n��
            l_openMarginInputResponse.expirationStartDate = null;

            //�L�������ŏI��
            l_openMarginInputResponse.expirationEndDate = null;

            //�L���������j���ꗗ
            l_openMarginInputResponse.holidayList = null;
        }

        //����I���x���s��R�[�h�ꗗ�F�@@������ԊǗ�.get�s��ǌx���s��( )�̖߂�l�z��
        l_openMarginInputResponse.messageSuspension = l_strTradeCloseMarkets;

        //is�C���T�C�_�[�x���\��
        l_openMarginInputResponse.insiderWarningFlag = l_boolIsInsider;

        //�V�K���\�z�F�@@calc�M�p�V�K���\�z()�̖߂�l
        l_openMarginInputResponse.marginTradingPower =
            WEB3StringTypeUtility.formatNumber(l_dblOpenMarginPossiblePrice);

        //�������F�@@��������.�������@@
        //�������R�[�h�w��(���N�G�X�g�f�[�^.�����R�[�h != null)�̏ꍇ�̂ݐݒ�B�ȊO�Anull��ݒ�
        //������������validate�����R�[�h�i�M�p�j( )�̖߂�l�ɂĎ擾
        if (l_request.productCode == null)
        {
            l_openMarginInputResponse.productName = null;
        }
        else
        {
            EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
            l_openMarginInputResponse.productName = l_productRow.getStandardName();
        }

        //�s��R�[�h�ꗗ�F
        //(���X�s��ٍϕ�)�戵����.get�戵�\�s��( )�̖߂�l�̎s��R�[�h�̔z��
        //�������R�[�h�w��̏ꍇ�́A�Y���������戵�\�Ȏs��R�[�h�̔z��        
        if (l_request.productCode != null)
        {
            l_strHandlingPossibleMarkets = new String[l_lisHandlingPossibleMarkets.size()];
            l_lisHandlingPossibleMarkets.toArray(l_strHandlingPossibleMarkets);

            l_openMarginInputResponse.marketList = l_strHandlingPossibleMarkets;
        }
        else
        {
            l_openMarginInputResponse.marketList = l_strHandlingPossibleMarkets;
        }

        //�����敪�ꗗ�F�@@
        //�ڋq.is��������J��( )==true�̏ꍇ�A�h0�F��ʁh�Ɓh1�F����h�̂Q�̕�����z����Z�b�g�B
        //�ڋq.is��������J��( )==false�̏ꍇ�A�h0�F��ʁh�݂̂��Z�b�g�B
        if (l_blnIsSpecialAccountEstablished)
        {
            String[] l_strTaxTypeList = { WEB3TaxTypeSpecialDef.NORMAL, WEB3TaxTypeSpecialDef.SPECIAL };
            l_openMarginInputResponse.taxTypeList = l_strTaxTypeList;
        }
        else
        {
            String[] l_strTaxTypeList = { WEB3TaxTypeSpecialDef.NORMAL };
            l_openMarginInputResponse.taxTypeList = l_strTaxTypeList;
        }

        //�ٍψꗗ�F(���X�s��ٍϕ�)�戵����.get�戵�\�ٍ�( )�̖߂�l�̐M�p����ٍς̔z��
        l_openMarginInputResponse.repaymentList = l_repaymentUnit;

        //���������敪�ꗗ�F�@@�戵�\��������.�戵�\���������敪�擾( )�̖߂�l�z��
        l_openMarginInputResponse.expirationDateTypeList =
            l_handlingOrderCond.getHandlingPossibleExpirationDateType();

        //���������敪�ꗗ�F�@@�戵�\��������.�戵�\���������擾( )�̖߂�l�z��
        l_openMarginInputResponse.orderCondTypeList = l_handlingOrderCond.getHandlingPossibleOrderCond();

        //�s��R�[�h: ���N�G�X�g.�s��R�[�h
        l_openMarginInputResponse.marketCode = l_request.marketCode;

        //����(���ݒl)�F�@@(**2)getCurrentPrice( )�̖߂�l
        //�O����F�@@(**2)getChange( )�̖߂�l
        //�������(�������\����)�F�@@(**2)getCurrentPriceTime( )�̖߂�l
        //��񍀖ڂ̐ݒ�́A(**2)�ɊY������ꍇ�̂݁B�ݒ�d�l�͈ȉ��̒ʂ�B
        //���ݒl�F�@@�擾�������������������.get���ݒl()�̖߂�l���Z�b�g
        //���ݒl�����F�@@�擾�������������������.get���ݒl����()�̖߂�l���Z�b�g
        //���ݒl�敪�F�@@�擾�������������������.get���ݒl�敪()�̖߂�l���Z�b�g
        //���ݒl�O����F�@@�擾�������������������.get���ݒl�O����()�̖߂�l���Z�b�g
        //�o�����F�@@�擾�������������������.get�o����()�̖߂�l���Z�b�g
        //�o���������F�@@�擾�������������������.get�o��������()�̖߂�l���Z�b�g
        //���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪()�̖߂�l���Z�b�g
        //���C�z�l�F�@@�擾�������������������.get���C�z�l()�̖߂�l���Z�b�g
        //���C�z�l�����F�@@�擾�������������������.get���C�z�l����()�̖߂�l���Z�b�g
        //���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪()�̖߂�l���Z�b�g
        //���C�z�l�F�@@�擾�������������������.get���C�z�l()�̖߂�l���Z�b�g
        //���C�z�l�����F�@@�擾�������������������.get���C�z�l����()�̖߂�l���Z�b�g
        //��l�i�F�@@�擾�������������������.get��l�i()�̖߂�l���Z�b�g
        //(**2)�@@�����R�[�h�E�s��R�[�h�w��(���N�G�X�g�f�[�^.�����R�[�h != null�A
        //���A���N�G�X�g�f�[�^.�s��R�[�h != null)�̏ꍇ�̂ݐݒ�B�ȊO�Anull��ݒ�B
        if (l_request.marketCode != null && l_request.productCode != null)
        {
            //reset�s��R�[�h(
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);

            //����������擾
            try
            {
                l_tradedProduct = (WEB3EquityTradedProduct) l_prodcutManager.getTradedProduct(
                    l_subAccount.getInstitution(), l_request.productCode, l_request.marketCode);

                // �g���v���_�N�g�}�l�[�W���擾
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
                
                // get�\���p�������
                WEB3EquityProductQuote l_productQuote =
                    l_productManager.getDisplayEquityProductQuote(l_tradedProduct, l_subAccount);
                    
                double l_dblCurrentPrice = l_productQuote.getQuote();
                double l_dblChange = l_productQuote.getComparedPreviousDay();

                l_openMarginInputResponse.currentPriceDiv = l_productQuote.getQuoteTypeDiv();
                l_openMarginInputResponse.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
                l_openMarginInputResponse.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
                l_openMarginInputResponse.currentPriceTime = l_productQuote.getQuoteTime();
                l_openMarginInputResponse.boardCurrentPrice = l_productQuote.getBoardCurrentPrice();
                l_openMarginInputResponse.boardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime();
                l_openMarginInputResponse.boardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv();
                l_openMarginInputResponse.boardChange = l_productQuote.getBoardChange();
                l_openMarginInputResponse.volume = l_productQuote.getVolume();
                l_openMarginInputResponse.volumeTime = l_productQuote.getVolumeTime();
                l_openMarginInputResponse.askPriceTitle = l_productQuote.getAskPriceTitle();
                l_openMarginInputResponse.askPrice = l_productQuote.getAskPrice();
                l_openMarginInputResponse.askPriceTime = l_productQuote.getAskPriceTime();
                l_openMarginInputResponse.bidPriceTitle = l_productQuote.getBidPriceTitle();
                l_openMarginInputResponse.bidPrice = l_productQuote.getBidPrice();
                l_openMarginInputResponse.bidPriceTime = l_productQuote.getBidPriceTime();
                l_openMarginInputResponse.basePrice = l_productQuote.getBasePrice();
            }
            catch (NotFoundException l_nfex)
            {
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME);
            }
        }
        else
        {
            l_openMarginInputResponse.currentPrice = null;
            l_openMarginInputResponse.comparedPreviousDay = null;
            l_openMarginInputResponse.currentPriceTime = null;
            l_openMarginInputResponse.boardCurrentPrice = null;
            l_openMarginInputResponse.boardCurrentPriceTime = null;
            l_openMarginInputResponse.boardCurrentPriceDiv = null;
            l_openMarginInputResponse.boardChange = null;
            l_openMarginInputResponse.volume = null;
            l_openMarginInputResponse.volumeTime = null;
            l_openMarginInputResponse.askPriceTitle = null;
            l_openMarginInputResponse.askPrice = null;
            l_openMarginInputResponse.askPriceTime = null;
            l_openMarginInputResponse.bidPriceTitle = null;
            l_openMarginInputResponse.bidPrice = null;
            l_openMarginInputResponse.bidPriceTime = null;
            l_openMarginInputResponse.basePrice = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_openMarginInputResponse;
    }
    /**
     * �iget�戵�\�ٍρj�B<BR>
     * <BR>
     * �p�����[�^�̕��X�ɊY������i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g��S�Ď擾���A<BR>
     * �p�����[�^�ٍ̕ϋ敪�A�s��R�[�h�ꗗ(*)�ɊY������I�u�W�F�N�g�ٍ̕ϋ敪�ƕٍϊ����l��<BR>
     * �Z�b�g�����M�p����ٍσI�u�W�F�N�g�̔z���ԋp����B<BR>
     * <BR>
     * ���M�p����ٍσI�u�W�F�N�g�ւ̐ݒ�́A�ٍϋ敪(�����j�A�ٍϊ����l(����)�̏��Ƃ���B<BR>
     * <BR>
     * (*)�ٍϋ敪�A�s��R�[�h�ꗗ�͎w�莞�̂݃`�F�b�N���e�ɒǉ�����B<BR>
     * <BR>
     * �P�j�p�����[�^�`�F�b�N<BR>
     * �@@�p�����[�^.�s��R�[�h�ꗗ!=null�A���A�v�f����0�̏ꍇ�A<BR>
     * �@@�u�p�����[�^�w��G���[�v�̗�O��throw����B<BR>
     * <BR>
     * �Q�j�f�[�^�擾<BR>
     * �@@�i���X�s��ٍϕʁj�戵����.get�i���X�s��ٍϕʁj�戵����(�p�����[�^.���X)�ɂ��A<BR>
     * �@@�p�����[�^�̕��X�ɊY������i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g��S�Ď擾����B<BR>
     * <BR>
     * �R�jArrayList�𐶐�����B<BR>
     * <BR>
     * �S�j�戵�\�`�F�b�N<BR>
     * �@@�Q�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@[�`�F�b�N���e]<BR>
     * �@@�@@�@@----------------------------------------------------<BR>
     * �@@�@@�@@�S�|�P�j(���X�s��ٍϕ�)�戵����.is�戵�\ == true�ł��邱�ƁB<BR>
     * <BR>
     * �@@�@@�@@�S�|�Q�j�ȉ��̏����ɂ��`�F�b�N�𕪊򂷂�B<BR>
     * �@@�@@�@@�@@[�p�����[�^.�ٍϋ敪==�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@[�p�����[�^.�s��R�[�h�ꗗ!=null�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@(���X�s��ٍϕ�)�戵����.�s��R�[�h���p�����[�^.�s��R�[�h�ꗗ�̂�����ɂ��Y�����Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@���̃I�u�W�F�N�g�֏������ڍs����B(continue)<BR>
     * <BR>
     * �@@�@@�@@�@@[�p�����[�^.�ٍϋ敪!=�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�i���X�s��ٍϕʁj�戵����.�ٍϋ敪 != �p�����[�^.�ٍϋ敪�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@���̃I�u�W�F�N�g�֏������ڍs����B(continue)<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[�p�����[�^.�s��R�[�h�ꗗ!=null�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@(���X�s��ٍϕ�)�戵����.�s��R�[�h���p�����[�^.�s��R�[�h�ꗗ�̂�����ɂ��Y�����Ȃ��ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�@@�@@���̃I�u�W�F�N�g�֏������ڍs����B(continue)<BR>
     * �@@�@@�@@----------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�S�|�R�j��L�`�F�b�N��ʉ߂����I�u�W�F�N�g�ɂ��āA�ȉ��̏��������{����B<BR>
     * �@@�@@�@@�@@�@@�M�p����ٍσI�u�W�F�N�g�𐶐��B <BR>
     * �@@�@@�@@�@@�A���Y���R�[�h�ٍ̕ϋ敪�A�ٍϊ����l�𐶐������I�u�W�F�N�g�̓��v���p�e�B�ɃZ�b�g�B<BR>
     * �@@�@@�@@�@@�B�M�p����ٍσI�u�W�F�N�g��ArrayList�ɒǉ��B<BR>
     * �@@�@@�@@�@@��ArrayList�ɒǉ�����M�p����ٍσI�u�W�F�N�g�́A�ٍϋ敪�ƕٍϊ����l�̑g�ݍ��킹��<BR>
     * �@@�@@�@@�@@�@@�d�����Ȃ��I�u�W�F�N�g�݂̂��Z�b�g���邱�ƁB <BR>
     * <BR>
     * �T�j�쐬����ArrayList.toArray()�̌��ʂ�ԋp����B
     * @@param l_branch ���X<BR>
     * �@@�@@�@@���X�I�u�W�F�N�g
     * @@param l_strRepaymentType �ٍϋ敪<BR>
     * �@@�@@�@@0�FDEFAULT�i�w��Ȃ��j <BR>
     * �@@�@@�@@1�F���x�M�p<BR>
     * �@@�@@�@@2�F��ʐM�p
     * @@param l_marketCodeList �s��R�[�h�ꗗ<BR>
     * �@@�@@�@@�戵�\�ȕٍς��擾����Ώۂ̎s��R�[�h�̈ꗗ�B <BR>
     * �@@�@@�@@�w��Ȃ��̏ꍇ�Anull�B
     * @@return WEB3MarginRepaymentUnit[]
     */
    protected WEB3MarginRepaymentUnit[] getHandlingRepayment(
        WEB3GentradeBranch l_branch, String l_strRepaymentType, String[] l_marketCodeList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHandlingRepayment()";
        log.entering(STR_METHOD_NAME);

        //�P�j�p�����[�^�`�F�b�N 
        // �p�����[�^.�s��R�[�h�ꗗ!=null�A���A�v�f����0�̏ꍇ�A 
        //�u�p�����[�^�w��G���[�v�̗�O��throw����B 
        if (l_marketCodeList != null && l_marketCodeList.length == 0)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }

        //�Q�j�f�[�^�擾 
        //�i���X�s��ٍϕʁj�戵����.get�i���X�s��ٍϕʁj�戵����(�p�����[�^.���X)�ɂ��A 
        //�p�����[�^�̕��X�ɊY������i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g��S�Ď擾����
        WEB3GentradeBranchMarketRepayDealtCond[] l_branchMarketRepayDealtConds =
            WEB3GentradeBranchMarketRepayDealtCond.getBranchMarketRepayDealtCond(l_branch);

        if (l_branchMarketRepayDealtConds == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }

        //���ʃ��X�g
        List l_lisMarginRepaymentUnits = new ArrayList();

        int l_intLen = l_branchMarketRepayDealtConds.length;
        int l_intLenMarkets = 0;
        boolean l_blnHasSameMarketCode;

        for (int i = 0; i < l_intLen; i++)
        {
            l_blnHasSameMarketCode = false;

            //�Q�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s��ArrayList�ɒǉ�����B 
            WEB3GentradeBranchMarketRepayDealtCond l_branchMarketRepayDealtCond = l_branchMarketRepayDealtConds[i];
            BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow =
                (BranchMarketRepayDealtCondRow) l_branchMarketRepayDealtCond.getDataSourceObject();

            //(���X�s��ٍϕ�)�戵����.is�戵�\ == true�ł��邱�ƁB
            if (!l_branchMarketRepayDealtCond.isHandlingPossible())
            {
                continue;
            }
            if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
            {
                //[[�p�����[�^.�ٍϋ敪==�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ]                                                                 
            } else
            {
                //[�p�����[�^.�ٍϋ敪!=�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ] 
                //�i���X�s��ٍϕʁj�戵����.�ٍϋ敪 != �p�����[�^.�ٍϋ敪�̏ꍇ�A 
                //  ���̃I�u�W�F�N�g�֏������ڍs����B(continue) 
                if (l_branchMarketRepayDealtCondRow.getRepaymentDivIsSet() &&
                    !l_branchMarketRepayDealtCondRow.getRepaymentDiv().equals(l_strRepaymentType))
                {
                    continue;
                }

            }
            //[�p�����[�^.�s��R�[�h�ꗗ!=null�̏ꍇ] 
            //    (���X�s��ٍϕ�)�戵����.�s��R�[�h���p�����[�^.�s��R�[�h�ꗗ�̂�����ɂ��Y�����Ȃ��ꍇ�A 
            //    ���̃I�u�W�F�N�g�֏������ڍs����B(continue)
            if (l_marketCodeList != null)
            {
                l_intLenMarkets = l_marketCodeList.length;

                for (int j = 0; j < l_intLenMarkets; j++)
                {
                    if (l_branchMarketRepayDealtCond.getMarketCode().equals(l_marketCodeList[j]))
                    {
                        l_blnHasSameMarketCode = true;
                        break;
                    }
                }

                if (!l_blnHasSameMarketCode)
                {
                    continue;
                }
            }
            //�S�|�R�j��L�`�F�b�N��ʉ߂����I�u�W�F�N�g�ɂ��āA�ȉ��̏��������{����B 
            //    �@@�M�p����ٍσI�u�W�F�N�g�𐶐��B 
            //    �A���Y���R�[�h�ٍ̕ϋ敪�A�ٍϊ����l�𐶐������I�u�W�F�N�g�̓��v���p�e�B�ɃZ�b�g�B 
            //    �B�M�p����ٍσI�u�W�F�N�g��ArrayList�ɒǉ��B 
            //    ��ArrayList�ɒǉ�����M�p����ٍσI�u�W�F�N�g�́A�ٍϋ敪�ƕٍϊ����l�̑g�ݍ��킹�� 
            //    �d�����Ȃ��I�u�W�F�N�g�݂̂��Z�b�g���邱�ƁB

            WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
            l_repaymentUnit.repaymentDiv = l_branchMarketRepayDealtCondRow.getRepaymentDiv();
            l_repaymentUnit.repaymentTimeLimit = "" + l_branchMarketRepayDealtCondRow.getRepaymentNum();

            int l_intLenRepaymentUnits = l_lisMarginRepaymentUnits.size();

            if (l_intLenRepaymentUnits == 0)
            {

                l_lisMarginRepaymentUnits.add(l_repaymentUnit);
            }

            for (int k = 0; k < l_intLenRepaymentUnits; k++)
            {

                WEB3MarginRepaymentUnit l_exsitedRepaymentUnit =
                    (WEB3MarginRepaymentUnit) l_lisMarginRepaymentUnits.get(k);
                if (l_repaymentUnit.repaymentDiv.equals(l_exsitedRepaymentUnit.repaymentDiv) &&
                    l_repaymentUnit.repaymentTimeLimit.equals(l_exsitedRepaymentUnit.repaymentTimeLimit))
                {
                    break;
                }

                if (k == l_intLenRepaymentUnits - 1)
                {
                    l_lisMarginRepaymentUnits.add(l_repaymentUnit);
                }
            }

        }

        //�ٍϋ敪(�����j�A�ٍϊ����l(����)�Ƀ\�[�g
        Collections.sort(l_lisMarginRepaymentUnits, new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                WEB3MarginRepaymentUnit first = (WEB3MarginRepaymentUnit) o1;
                WEB3MarginRepaymentUnit second = (WEB3MarginRepaymentUnit) o2;
                int result;

                if (!first.repaymentDiv.equals(second.repaymentDiv))
                {
                    result = Integer.parseInt(first.repaymentDiv) - Integer.parseInt(second.repaymentDiv);
                }
                else
                {
                    result = Integer.parseInt(first.repaymentTimeLimit) - Integer.parseInt(second.repaymentTimeLimit);
                }

                return result;
            }
        });

        //�쐬����ArrayList.toArray()�̌��ʂ�ԋp����B 
        WEB3MarginRepaymentUnit[] l_repaymentUnits = new WEB3MarginRepaymentUnit[l_lisMarginRepaymentUnits.size()];
        l_lisMarginRepaymentUnits.toArray(l_repaymentUnits);

        log.exiting(STR_METHOD_NAME);
        return l_repaymentUnits;
    }
    
    /**
     * (get�V�K���\�z)<BR>
     * �V�K���\�z���擾����B<BR>
     * <BR>
     * ����]�̓T�[�r�X.get�M�p�V�K���\�z(�⏕����)���R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@return double
     * @@throws WEB3BaseException
     */
    protected double getMarginTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarginTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblOpenMarginPossiblePrice =
            l_tradingPowerService.getMarginTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblOpenMarginPossiblePrice;
    }

    /**
     * (get�s��R�[�h )<BR>
     * �s��R�[�h���擾����B <BR>
     * <BR>
     * �P�j�@@�p�����[�^.�s��R�[�h == null�܂��� <BR>
     * �@@�@@�p�����[�^.�����R�[�h == null�̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�s��R�[�h != null <BR>
     * �@@�@@���@@�p�����[�^.�����R�[�h != null <BR>
     * �@@�@@���@@�p�����[�^.�s��R�[�h != "99�F�D��s��"�̏ꍇ�A<BR>
     * �@@�@@�p�����[�^.�s��R�[�h��ԋp����B<BR>
     * <BR>
     * �R�j�@@��L�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�R�|�P�j�@@�g���v���_�N�g�}�l�[�W��.getProduct()���R�[�����A�����������擾����B<BR>
     * �@@�@@�@@�@@�@@�@@[getProduct()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@�����R�[�h�@@�@@�@@�F�@@�p�����[�^.�����R�[�h <BR>
     * <BR>
     * �@@�R�|�Q�j�@@��������.get�D��s��()���R�[�����A�s����擾����B<BR>
     * <BR>
     * �@@�R�|�R�j�@@�R�|�Q�j�Ŏ擾�����s�ꂪnull�̏ꍇ�A��O��throw����B <BR>
     * �@@�@@�@@�@@�@@�@@�ȊO�A�s��.getMarketCode()�̖߂�l��ԋp����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:    BUSINESS_ERROR_02702<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * �����R�[�h
     * @@param l_strMarketCode - �s��R�[�h<BR>
     * �s��R�[�h
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * �،���ЃR�[�h
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getMarketCode(
        String l_strProductCode,
        String l_strMarketCode,
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarketCode(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�p�����[�^.�s��R�[�h == null�܂���
        //�@@�p�����[�^.�����R�[�h == null�̏ꍇ�Anull��ԋp����B
        if (l_strProductCode == null || l_strMarketCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�p�����[�^.�s��R�[�h != null
        //�@@���@@�p�����[�^.�����R�[�h != null
        //�@@���@@�p�����[�^.�s��R�[�h != "99�F�D��s��"�̏ꍇ�A�p�����[�^.�s��R�[�h��ԋp����B
        if (l_strMarketCode != null && l_strProductCode != null
            && !WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_strMarketCode))
        {
            log.exiting(STR_METHOD_NAME);
            return l_strMarketCode;
        }

        //�@@�R�|�P�j�@@�g���v���_�N�g�}�l�[�W��.getProduct()���R�[�����A�����������擾����B
        //�@@�@@�@@[getProduct()�ɐݒ肷�����]
        //�@@�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h
        //�@@�@@�@@�@@�����R�[�h�@@�@@�@@�F�@@�p�����[�^.�����R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3EquityProductManager l_equityProductManager
            = (WEB3EquityProductManager)l_tradingModule.getProductManager();
        Institution l_institution = null;
        WEB3EquityProduct l_eqTypeProduct = null;
        try
        {
            l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            l_eqTypeProduct =
                (WEB3EquityProduct)l_equityProductManager.getProduct(
                    l_institution,
                    l_strProductCode);
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //�R�|�Q�j�@@��������.get�D��s��()���R�[�����A�s����擾����B
        Market l_market = l_eqTypeProduct.getPrimaryMarket();

        //�R�|�R�j�@@�R�|�Q�j�Ŏ擾�����s�ꂪnull�̏ꍇ�A��O��throw����B
        //�@@�@@�@@�ȊO�A�s��.getMarketCode()�̖߂�l��ԋp����B
        if (l_market == null)
        {
            log.debug("�D��s�ꂪ���w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02702,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "�D��s�ꂪ���w��ł��B");
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_market.getMarketCode();
        }
    }

}
@
