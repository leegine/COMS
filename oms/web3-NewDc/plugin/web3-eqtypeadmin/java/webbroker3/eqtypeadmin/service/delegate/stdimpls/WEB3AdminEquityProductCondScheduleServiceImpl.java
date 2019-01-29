head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityProductCondScheduleServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҋ������������\��ꗗ�T�[�r�X
                        (WEB3AdminEquityProductCondScheduleServiceImpl.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3PriceRangeIdDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.data.EqtypeProductConditionParams;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityLargeItemDivDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySmallItemDivDef;
import webbroker3.eqtypeadmin.define.WEB3AdminPriceRangeValueDef;
import webbroker3.eqtypeadmin.define.WEB3LargeItemDef;
import webbroker3.eqtypeadmin.message.WEB3AdminPMItemInfoUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondScheduleUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondScheduleService;

import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;

/**
 * �i�Ǘ��Ҋ������������\��ꗗ�T�[�r�XImpl�j<BR>
 * <BR>
 * �Ǘ��Ҋ������������\��ꗗ�T�[�r�X�����N���X<BR>
 * <BR>
 * WEB3AdminEquityProductCondScheduleServiceImpl class<BR>
 * <BR>
 * @@author Manjula Honnappa
 * @@version 1.0
 */
public class WEB3AdminEquityProductCondScheduleServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminEquityProductCondScheduleService
{

    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityProductCondScheduleServiceImpl.class);

    /**
     * @@roseuid 41FD90FA00EA
     */
    public WEB3AdminEquityProductCondScheduleServiceImpl()
    {

    }

    /**
     * �������������\��ꗗ�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��ȉ��̃��\�b�h��<BR>
     * �Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�������������\��ꗗ���N�G�X�g�̏ꍇ<BR>
     * �@@this.get�\��ꗗ()���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Execute WEB3AdminEquityProductCondSettingService process<BR>
     * <BR>
     * Call one of the following methods based on the type of the argument,
     * l_request.<BR>
     * <BR>
     * ��If WEB3AdminPMProductCondListInputRequest<BR>
     * �@@this.getSchedule()<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�j<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4191C0C801B1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        try
        {
            /*
             * If l_request is of type WEB3AdminPMProductCondListInputRequest call
             * getInputScreen(). If l_request is of type WEB3AdminPMProductCondListRequest
             * call getSchedule(). Other wise throw WEB3SystemLayerException
             */
            if (l_request instanceof WEB3AdminPMProductCondListInputRequest)
            {

                l_response = getInputScreen((WEB3AdminPMProductCondListInputRequest) l_request);

            } else if (l_request instanceof WEB3AdminPMProductCondListRequest)
            {

                l_response = getSchedule((WEB3AdminPMProductCondListRequest) l_request);

            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT ���N�G�X�g NOT �������������\��ꗗ���N�G�X�g");
            }
        } catch (NotFoundException l_notFoundException)
        {
            log.error(l_notFoundException.getMessage(), l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_notFoundException.getMessage(),
                l_notFoundException);

        } catch (DataNetworkException l_dnEx)
        {
            log.debug(l_dnEx.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dnEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnEx.toString(),
                l_dnEx);

        } catch (DataFindException l_dqEx)
        {
            log.debug(l_dqEx.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dqEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqEx.toString(),
                l_dqEx);

        } catch (DataQueryException l_dqEx)
        {
            log.debug(l_dqEx.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dqEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqEx.toString(),
                l_dqEx);

        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �iget���͉�ʁj<BR>
     * <BR>
     * �������������\��ꗗ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҋ������������\��ꗗ�T�[�r�X)get���͉�ʁv<BR>
     * �Q��<BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     * WEB3AdminEquityProductCondScheduleService input screen process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: equity product condition schedule
     * list service)getInputScreen"<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * �Ǘ��ҁE�������������\��ꗗ���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMProductCondListInputRequest object<BR>
     * <BR>
     * @@return WEB3AdminPMProductCondListInputResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 41944C4D009E
     */
    protected WEB3AdminPMProductCondListInputResponse
        getInputScreen(WEB3AdminPMProductCondListInputRequest l_request)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminPMProductCondListInputRequest)";
        log.entering(STR_METHOD_NAME);

        String l_strBranchCode = null;
        String[] l_strSmalItemDiv = null;
        String l_strMarginSysDiv = null;
        String l_strMarginGenDiv = null;
        String l_strMstkDiv = null;
        boolean l_isUpdate = false;
        ArrayList l_lisPMItemInfoUnit = null;
        ArrayList l_lisSmallItemDiv = new ArrayList();
        Timestamp l_tsCurrentDate = null;
        boolean l_blnSecurityEnforcement = false;

        WEB3Administrator l_administrator = null;
        Institution l_institution = null;
        WEB3GentradeInstitution l_genTradeInstitution = null;
        WEB3GentradeAccountManager l_gentradeAccountManager = null;
        Branch l_branch = null;
        WEB3AdminPMItemInfoUnit l_adminPMItemInfoUnit = null;
        WEB3AdminPMItemInfoUnit[] l_adminPMItemInfoUnitList = new WEB3AdminPMItemInfoUnit[1];
        WEB3AdminPMProductCondListInputResponse l_response = null;
        BranchParams l_branchParams = null;
        FinApp l_finApp = null;

        // Step 1.1
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step 1.2
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_PRODUCT_SETTING,
            l_isUpdate);

        // Step 1.3
        l_institution = l_administrator.getInstitution();
        l_genTradeInstitution = (WEB3GentradeInstitution) l_institution;

        // Step 1.4
        l_strBranchCode = l_administrator.getBranchCode();

        // Step 1.5
        l_finApp = (FinApp) Services.getService(FinApp.class);
        l_gentradeAccountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        l_branch = l_gentradeAccountManager.getBranch(l_institution, l_strBranchCode);
        l_branchParams = (BranchParams) l_branch.getDataSourceObject();

        // Step 1.6
        l_lisPMItemInfoUnit = new ArrayList();

        // Step 1.7
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.TRADE_STOP);

        l_strMarginSysDiv = l_branchParams.getMarginSysDiv();
        l_strMarginGenDiv = l_branchParams.getMarginGenDiv();
        l_strMstkDiv = l_branchParams.getMstkDiv();

        // Check ENFORCEMENT is equal to marginSysDiv value
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv))
        {
			l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_TRADE_STOP);
        }
        
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv))
        {
			l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_TRADE_STOP);
        }
        
		l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.BUY_CASH_STOP);
		l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SELL_CASH_STOP);

		if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv))
		{
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_SYS_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_SYS_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_CLOSE_MARGIN_SYS_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_CLOSE_MARGIN_SYS_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_SWAP_MARGIN_SYS_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_SWAP_MARGIN_SYS_STOP);
		}

		if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv))
		{
			l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_GEN_STOP);
			l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_GEN_STOP);
			l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_CLOSE_MARGIN_GEN_STOP);
			l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_CLOSE_MARGIN_GEN_STOP);
			l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_SWAP_MARGIN_GEN_STOP);
			l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_SWAP_MARGIN_GEN_STOP);
		}

		l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.BUY_SPOT_MARKET_ORD_DES_STOP);
		l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SELL_SPOT_MARKET_ORD_DES_STOP);
		
		if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv))
		{
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_MS_MARKET_ORD_DES_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_MS_MARKET_ORD_DES_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_CMS_MARKET_ORD_DES_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_CMS_MARKET_ORD_DES_STOP);
        }

        // Checks ENFORCEMENT is equal to MarginGenDiv value
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv))
        {
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_MG_MARKET_ORD_DES_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_MG_MARKET_ORD_DES_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_CMG_MARKET_ORD_DES_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_CMG_MARKET_ORD_DES_STOP);
        }

        // Checks ENFORCEMENT is equal to MstkDiv value
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMstkDiv))
        {
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.BUY_MINI_STOCK_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SELL_MINI_STOCK_STOP);
        }

        l_strSmalItemDiv = (String[]) l_lisSmallItemDiv.toArray(new String[0]);
        l_adminPMItemInfoUnit =
            new WEB3AdminPMItemInfoUnit(
                WEB3AdminEquityLargeItemDivDef.TRADING_REGULATION,
                l_strSmalItemDiv);

        // Step 1.8
        l_lisPMItemInfoUnit.add(l_adminPMItemInfoUnit);
		l_lisSmallItemDiv.clear();

        // Step 1.9
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.STANDARD_NAME);
		l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.PRIMARY_MARKET);
		l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.CAPITAL_GAIN_TAX_DEALING);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LOT_SIZE);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.COMPULSIVE_LIMITED_UNIT);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.OPEN_OTC_DIV);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LIST_TYPE);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.TODAY_DEP_FUND_REG);

        // Checks ENFORCEMENT is equal to MstkDiv value
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMstkDiv))
        {
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.MINI_STOCK_MARKET);
        }

        l_strSmalItemDiv = (String[]) l_lisSmallItemDiv.toArray(new String[0]);
        l_adminPMItemInfoUnit =
            new WEB3AdminPMItemInfoUnit(
                WEB3AdminEquityLargeItemDivDef.BASIC_INFO,
                l_strSmalItemDiv);

        // Step 1.10
        l_lisPMItemInfoUnit.add(l_adminPMItemInfoUnit);
		l_lisSmallItemDiv.clear();

        // Step 1.11
        /*
         * Checks if ENFORCEMENT is equal to margin sys div or enforcement is
         * equal to margin gen div
         */
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv)
            || WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv))
        {
            // Checks ENFORCEMENT is equal to margin sys div value
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv))
            {
                l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_PRODUCT_TYPE);
            }

            // Checks ENFORCEMENT is equal to margin gen div
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv))
            {
                l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_PRODUCT_TYPE);
            }

            l_strSmalItemDiv = (String[]) l_lisSmallItemDiv.toArray(new String[0]);

            // Step 1.11.2
            l_adminPMItemInfoUnit =
                new WEB3AdminPMItemInfoUnit(
                    WEB3AdminEquityLargeItemDivDef.MARGIN_PRODUCT_INFO,
                    l_strSmalItemDiv);
            l_lisPMItemInfoUnit.add(l_adminPMItemInfoUnit);
			l_lisSmallItemDiv.clear();

            // Step 1.11.3
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_DEPOSIT_RATE);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_DEPOSIT_RATE);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_CASH_MARGIN_DEPOSIT_RATE);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_CASH_MARGIN_DEPOSIT_RATE);

            l_strSmalItemDiv = (String[]) l_lisSmallItemDiv.toArray(new String[0]);
            l_adminPMItemInfoUnit =
                new WEB3AdminPMItemInfoUnit(
                    WEB3AdminEquityLargeItemDivDef.DEPOSIT_RATE,
                    l_strSmalItemDiv);
            l_lisPMItemInfoUnit.add(l_adminPMItemInfoUnit);
			l_lisSmallItemDiv.clear();
        }

        // Step 1.12
        l_blnSecurityEnforcement = l_genTradeInstitution.isInstitutionStockEvaluation();

        // Check margin enforcement company or a securities estimation enforcement company
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv)
            || WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv)
            || l_blnSecurityEnforcement)
        {

            // Check margin enforcement company && a securities estimation enforcement company
            if ((WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv)
                || WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv))
                && l_blnSecurityEnforcement)
            {
                l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.MARGIN_RATIO);
                l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SECURITIES_ESTIMATION_RATIO);
            }

            // Check margin enforcement company
            if ((WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv)
                || WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv))
                && !l_blnSecurityEnforcement)
            {
                l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.MARGIN_RATIO);
            }

            // Check securities estimation enforcement company
            if (l_blnSecurityEnforcement
                && !(WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv)
                    || WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv)))
            {
                l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SECURITIES_ESTIMATION_RATIO);
            }
            
			l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.ESTIMATION_PRICE);
			
            l_strSmalItemDiv = (String[]) l_lisSmallItemDiv.toArray(new String[0]);
            l_adminPMItemInfoUnit =
                new WEB3AdminPMItemInfoUnit(
                    WEB3AdminEquityLargeItemDivDef.SUBSTITUTE_SECURITY_INFO,
                    l_strSmalItemDiv);

            // Step 1.12.2
            l_lisPMItemInfoUnit.add(l_adminPMItemInfoUnit);
			l_lisSmallItemDiv.clear();
        }

        // Step 1.13
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LAST_CLOSING_PRICE);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.BASE_PRICE);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_TYPE);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.COMPULSIVE_PRICE_RANGE);

        // Step 1.14
        l_strSmalItemDiv = (String[]) l_lisSmallItemDiv.toArray(new String[0]);
        l_adminPMItemInfoUnit =
            new WEB3AdminPMItemInfoUnit(
                WEB3AdminEquityLargeItemDivDef.PRICE_INFO,
                l_strSmalItemDiv);
        l_lisPMItemInfoUnit.add(l_adminPMItemInfoUnit);
		l_lisSmallItemDiv.clear();

        // Step 1.15
        l_adminPMItemInfoUnitList =
            (WEB3AdminPMItemInfoUnit[]) l_lisPMItemInfoUnit.toArray(l_adminPMItemInfoUnitList);

        // Step 1.16
        l_response = (WEB3AdminPMProductCondListInputResponse) l_request.createResponse();

        l_tsCurrentDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        // Step 1.17
        l_response.currentDate = l_tsCurrentDate;

        l_response.itemInfoList = l_adminPMItemInfoUnitList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �iget�\��ꗗ�j<BR>
     * <BR>
     * �������������\��ꗗ�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҋ������������\��ꗗ�T�[�r�X)get�\��ꗗ�v<BR>
     * �Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getSchedule<BR>
     * <BR>
     * WEB3AdminEquityProductCondScheduleService process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: equity product condition schedule
     * list service)getSchedule"<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * �Ǘ��ҁE�������������\��ꗗ���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3AdminPMProductCondListResponse
     * @@throws DataFindException DataFindException
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 4191C0C70098
     */
    protected WEB3AdminPMProductCondListResponse
        getSchedule(WEB3AdminPMProductCondListRequest l_request)
        throws
            WEB3BaseException,
            DataFindException,
            DataNetworkException,
            DataQueryException,
            NotFoundException
    {
        final String STR_METHOD_NAME = "getSchedule(WEB3AdminPMProductCondListRequest)";
        log.entering(STR_METHOD_NAME);

        final String[] l_largeItemDivConstants =
            {
                WEB3AdminEquityLargeItemDivDef.TRADING_REGULATION,
                WEB3AdminEquityLargeItemDivDef.BASIC_INFO,
                WEB3AdminEquityLargeItemDivDef.MARGIN_PRODUCT_INFO,
                WEB3AdminEquityLargeItemDivDef.DEPOSIT_RATE,
                WEB3AdminEquityLargeItemDivDef.SUBSTITUTE_SECURITY_INFO,
                WEB3AdminEquityLargeItemDivDef.PRICE_INFO };

        String l_strInstitutionCode = null;
        String l_strProductCode = null;
        String l_strLargeItemDiv = null;
        String l_strSmallItemDiv = null;
        String l_strTermFrom = null;
        String l_strTermTo = null;
        ArrayList l_lisLargeItemDiv = null;
        List l_lisEqtypeProductConditionParamsList = null;
        Timestamp l_currentData = null;
        String l_strQueryString = null;
        Object[] l_queryDataContainer = null;
        String l_strSortCond = null;
        WEB3AdminPMProductCondScheduleUnit[] l_lisEqtypeProductList = null;
        int l_largeItemDivDefConstantsCnt = 0;
        Iterator l_itrLargeItemDiv = null;

        WEB3Administrator l_administrator = null;
        WEB3AdminPMProductCondListResponse l_response = null;
        WEB3AdminPMEquityDataManager l_adminPMEquityDataManager =
            new WEB3AdminPMEquityDataManager();
        EqtypeProductConditionParams[] l_eqtypeProductParamsList = null;

        // Step 1.1
        l_request.validate();

        // Step 1.2
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //Step 1.3
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_PRODUCT_SETTING,
            true);

        // Step 1.4
        l_strInstitutionCode = l_administrator.getInstitutionCode();

        // Step 1.6
        l_response = (WEB3AdminPMProductCondListResponse) l_request.createResponse();

        // Step 1.7
        l_strLargeItemDiv = l_request.largeItemDiv;
        l_strProductCode = l_request.productCode;
        l_strSmallItemDiv = l_request.smallItemDiv;
        l_strTermFrom = l_request.applyStartDate;
        l_strTermTo = l_request.applyEndDate;
        l_largeItemDivDefConstantsCnt = l_largeItemDivConstants.length;

        //checks l_request.largeItemDiv != null
        if (l_strLargeItemDiv != null)
        {
            l_lisLargeItemDiv = new ArrayList();
            l_lisLargeItemDiv.add(l_strLargeItemDiv);

        } else
        {
            l_lisLargeItemDiv = new ArrayList();
            for (int i = 0; i < l_largeItemDivDefConstantsCnt; i++)
            {

                l_lisLargeItemDiv.add(l_largeItemDivConstants[i]);
            }
        }
        // Step 1.7.6
        l_itrLargeItemDiv = l_lisLargeItemDiv.iterator();

        while (l_itrLargeItemDiv.hasNext())
        {
            l_strLargeItemDiv = (String) l_itrLargeItemDiv.next();

            l_strQueryString =
                this.createQueryString(
                    l_strProductCode,
                    l_strLargeItemDiv,
                    l_strSmallItemDiv,
                    l_strTermFrom,
                    l_strTermTo);

            // Step 1.7.2
            l_queryDataContainer =
                this.createQueryDataContainer(
                    l_strInstitutionCode,
                    l_strProductCode,
                    l_strLargeItemDiv,
                    l_strSmallItemDiv,
                    l_strTermFrom,
                    l_strTermTo);

            // Step 1.7.3
            l_strSortCond = this.createSortCond();

            // Step 1.7.4
            // Loop until getEqtypeProductConditionParamsList() returns not null value
            l_lisEqtypeProductConditionParamsList = null;
            l_lisEqtypeProductConditionParamsList =
                l_adminPMEquityDataManager.getEqtypeProductConditionParamsList(
                    l_strQueryString,
                    l_queryDataContainer,
                    l_strSortCond);

            // Check list is empty
            if (l_lisEqtypeProductConditionParamsList == null)
            {
                continue;
            }
            
            List l_lisEqytpeProductConditionParams = null;
            
            l_lisEqytpeProductConditionParams = 
                sortEqtypeProductParamsList(l_strLargeItemDiv, l_lisEqtypeProductConditionParamsList);
            
            if (l_lisEqytpeProductConditionParams.size() != 0)
            {
                l_lisEqtypeProductConditionParamsList = l_lisEqytpeProductConditionParams;
            }
            
            l_eqtypeProductParamsList = new EqtypeProductConditionParams[1];
            l_eqtypeProductParamsList =
                (EqtypeProductConditionParams[]) l_lisEqtypeProductConditionParamsList.toArray(
                    l_eqtypeProductParamsList);


            // Step 1.7.5
            l_lisEqtypeProductList =
                this.createScheduleInfoList(l_strLargeItemDiv, l_eqtypeProductParamsList);

            // Checks TRADING_REGULATION is equal to l_strLargeItemDiv value
            if (WEB3AdminEquityLargeItemDivDef.TRADING_REGULATION.equals(l_strLargeItemDiv))
            {
                l_response.tradingRegulationList = l_lisEqtypeProductList;
            }
            // Checks BASIC_INFO is equal to l_strLargeItemDiv value
            if (WEB3AdminEquityLargeItemDivDef.BASIC_INFO.equals(l_strLargeItemDiv))
            {
                l_response.basicInfoList = l_lisEqtypeProductList;
            }
            // Checks MARGIN_PRODUCT_INFO is equal to l_strLargeItemDiv value
            if (WEB3AdminEquityLargeItemDivDef.MARGIN_PRODUCT_INFO.equals(l_strLargeItemDiv))
            {
                l_response.stockMarginInfoList = l_lisEqtypeProductList;
            }
            // Checks DEPOSIT_RATE is equal to l_strLargeItemDiv value
            if (WEB3AdminEquityLargeItemDivDef.DEPOSIT_RATE.equals(l_strLargeItemDiv))
            {
                l_response.depositRateList = l_lisEqtypeProductList;
            }
            // Checks SUBSTITUTE_SECURITY_INFO is equal to l_strLargeItemDiv value
            if (WEB3AdminEquityLargeItemDivDef.SUBSTITUTE_SECURITY_INFO.equals(l_strLargeItemDiv))
            {
                l_response.substituteSecurityInfoList = l_lisEqtypeProductList;
            }
            //  Checks PRICE_INFO is equal to l_strLargeItemDiv value
            if (WEB3AdminEquityLargeItemDivDef.PRICE_INFO.equals(l_strLargeItemDiv))
            {
                l_response.priceInfoList = l_lisEqtypeProductList;
            }
        }


        // Step 1.8
        l_currentData =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_response.currentDate = l_currentData;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �icreate��������������j<BR>
     * <BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j�ȉ��̏�����\������������������쐬����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h�@@����<BR>
     * �@@�@@�ݒ�f�[�^(�\��) != null�@@����<BR>
     * <BR>
     * �@@�������������� = " institution_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and data_plan != null "<BR>
     * <BR>
     * �Q�j�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@�ȉ��̏�������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and product_code = ? "<BR>
     * <BR>
     * �R�j�p�����[�^.�區�ڋ敪 != null�̏ꍇ�A<BR>
     * �@@�ȉ��̏�������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and large_item_div = ? "<BR>
     * <BR>
     * �S�j�p�����[�^.�����ڋ敪 != null�̏ꍇ�A<BR>
     * �@@�ȉ��̏�������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and small_item_div = ? "<BR>
     * <BR>
     * �T�j�p�����[�^.�K�p����From != null�̏ꍇ ����<BR>
     * �@@�p�����[�^.�K�p����To != null�̏ꍇ�A<BR>
     * �@@�ȉ��̏�������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and term_from <= ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and term_to >= ? "<BR>
     * <BR>
     * �U�j�쐬�������������������ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * createQueryString<BR>
     * <BR>
     * Create queryString<BR>
     * <BR>
     * 1)Create queryString to shows the following condition<BR>
     * <BR>
     * �@@[Condition]<BR>
     * �@@�@@institution_code = parameter.institutionCode�@@and<BR>
     * �@@�@@data_plan != null and<BR>
     * <BR>
     * �@@l_strQueryCond = " institution_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and data_plan != null "<BR>
     * <BR>
     * 2)If parameter.productCode != null<BR>
     * �@@Add the following condition to l_strQueryCond<BR>
     * <BR>
     * �@@l_strQueryCond += "and product_code = ? "<BR>
     * <BR>
     * 3)If parameter.largeItemDiv != null<BR>
     * �@@Add the following condition to l_strQueryCondBR>
     * <BR>
     * �@@l_strQueryCond += "and large_item_div = ? "<BR>
     * <BR>
     * 4)If parameter.smallItemDiv != null<BR>
     * �@@Add the following condition to l_strQueryCond<BR>
     * <BR>
     * �@@l_strQueryCond += "and small_item_div = ? "<BR>
     * <BR>
     * 5)If parameter.applyStartDate != null and<BR>
     * �@@parameter.applyEndDate != null<BR>
     * �@@Add the following condition to l_strQueryCond<BR>
     * <BR>
     * �@@l_strQueryCond += "and term_from <= ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and term_to >= ? "<BR>
     * <BR>
     * 6)Return the created l_strQueryCond<BR>
     * <BR>
     * @@param l_strProductCode - �i�����R�[�h�j<BR>
     * <BR>
     * �����R�[�h
     * <BR>
     * l_strProductCode<BR>
     * <BR>
     * @@param l_largeItemDiv - �i�區�ڋ敪�j<BR>
     * <BR>
     * �區�ڋ敪<BR>
     * <BR>
     * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
     * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_largeItemDiv<BR>
     * <BR>
     * ��Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * values<BR>
     * <BR>
     *
     * @@param l_strSmallItemDiv - �i�����ڋ敪�j<BR>
     * <BR>
     * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
     * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_strSmallItemDiv<BR>
     * <BR>
     * ��Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * values<BR>
     * <BR>
     *
     * @@param l_datTermFrom - �i�K�p����From�j<BR>
     * <BR>
     * l_datTermFrom<BR>
     * <BR>
     *
     * @@param l_datTermTo - �i�K�p����To�j<BR>
     * <BR>
     * l_datTermTo<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 4191C4E0024D
     */
    protected String createQueryString(
        String l_strProductCode,
        String l_largeItemDiv,
        String l_strSmallItemDiv,
        String l_datTermFrom,
        String l_datTermTo)
    {
        final String STR_METHOD_NAME = "createQueryString(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbQueryCond = new StringBuffer();

        l_sbQueryCond.append(
            " institution_code = ?  " + "and data_plan is not null  ");

        // Checks productCode is not null
        if (l_strProductCode != null)
        {
            l_sbQueryCond.append(" and product_code = ? ");
        }

        l_sbQueryCond.append(" and large_item_div = ?");

        // Checks smallItemDiv is not null
        if (l_strSmallItemDiv != null)
        {
            if (l_strSmallItemDiv.equals(WEB3AdminEquitySmallItemDivDef.COMPULSIVE_PRICE_RANGE))
            {
                l_sbQueryCond.append(" and small_item_div in (?, ?, ?) ");
            }
            else
            {
	            l_sbQueryCond.append(" and small_item_div  = ?");
            }
        }

        // Checks to date and from date are not null
        if (l_datTermFrom != null && l_datTermTo != null)
        {
            l_sbQueryCond.append(" and term_from  <= ? and term_to >= ? ");
        }

        log.exiting(STR_METHOD_NAME);
        return l_sbQueryCond.toString();
    }

    /**
     * �icreate���������f�[�^�R���e�i�j<BR>
     * <BR>
     * ���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�ȉ��̒l���ォ�珇�ɐ�������ArrayList��<BR>
     * �@@�@@�Z�b�g����B<BR>
     * �@@�@@�E�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�E"0�F���폜"<BR>
     * <BR>
     * �R�j�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@�p�����[�^�����R�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�p�����[�^.�區�ڋ敪 != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�區�ڋ敪��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�j�p�����[�^.�����ڋ敪 != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�����ڋ敪��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �T�j�p�����[�^.�K�p����From != null�̏ꍇ ����<BR>
     * �@@�p�����[�^.�K�p����To != null�̏ꍇ�A<BR>
     * �@@�ȉ��̏��ԂœK�p����From/To��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�E�p�����[�^.�K�p����To<BR>
     * �@@�@@�E�p�����[�^.�K�p����From<BR>
     * <BR>
     * �U�j�쐬�������������f�[�^�R���e�i��ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * createQueryDataContainer<BR>
     * <BR>
     * Carete queryDataContainer<BR>
     * <BR>
     * 1)Created ArrayList<BR>
     * <BR>
     * 2)Set the following values to the created ArrayList sequentially.<BR>
     * �@@�@@�Eparameter.institutionCode<BR>
     * �@@�@@�E"0: Def.NOT_DELETE"<BR>
     * <BR>
     * 3)If parameter.productCode != null,<BR>
     * �@@Add parameter.productCode to ArrayList<BR>
     * <BR>
     * 3)If parameter.largeItemDiv != null,<BR>
     * �@@Add parameter.largeItemDiv to ArrayList<BR>
     * <BR>
     * 4)If parameter.smallItemDiv != null,<BR>
     * �@@Add parameter.smallItemDiv to ArrayList<BR>
     * <BR>
     * 5)If parameter.applyStartDate != null and<BR>
     * �@@parameter.applyEndDate != null<BR>
     * �@@Add applyStartDate/applyEndDate to ArrayList in the following order<BR>
     * �@@�@@�Eparameter.applyEndDate<BR>
     * �@@�@@�Eparameter.applyStartDate<BR>
     * <BR>
     * 6)Return the created queryDataContainer<BR>
     * <BR>
     * @@param l_strInstitutionCode - �i�،���ЃR�[�h�j<BR>
     * <BR>
     * �،���ЃR�[�h<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_strProductCode - �i�����R�[�h�j<BR>
     * <BR>
     * �����R�[�h
     * <BR>
     * l_strProductCode<BR>
     * <BR>
     * @@param l_largeItemDiv - �i�區�ڋ敪�j<BR>
     * <BR>
     * �區�ڋ敪<BR>
     * <BR>
     * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
     * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * largeItemDiv<BR>
     * <BR>
     * ��Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * values<BR>
     * <BR>
     * @@param l_strSmallItemDiv - �i�����ڋ敪�j<BR>
     * <BR>
     * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
     * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * smallItemDiv<BR>
     * <BR>
     * ��Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * values<BR>
     * <BR>
     * @@param l_datTermFrom - �i�K�p����From�j<BR>
     * <BR>
     * l_datTermFrom<BR>
     * <BR>
     *
     * @@param l_datTermTo - �i�K�p����To�j<BR>
     * <BR>
     * l_datTermTo<BR>
     * <BR>
     * @@return Object[]
     * @@roseuid 41936D150178
     */
    protected Object[] createQueryDataContainer(
        String l_strInstitutionCode,
        String l_strProductCode,
        String l_largeItemDiv,
        String l_strSmallItemDiv,
        String l_datTermFrom,
        String l_datTermTo)
    {
        final String STR_METHOD_NAME =
            "createQueryDataContainer(String, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        ArrayList l_arrQueryDataList = new ArrayList();
        Object[] l_queryData = null;
        l_arrQueryDataList.add(l_strInstitutionCode);

        // Checks productCode is not null
        if (l_strProductCode != null)
        {
            l_arrQueryDataList.add(l_strProductCode);
        }

        l_arrQueryDataList.add(l_largeItemDiv);

        // Checks smallItem is not null
        if (l_strSmallItemDiv != null)
        {
            if (l_strSmallItemDiv.equals(WEB3AdminEquitySmallItemDivDef.COMPULSIVE_PRICE_RANGE))
            {
                l_arrQueryDataList.add(WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_UNIT_TYPE);
                l_arrQueryDataList.add(WEB3AdminEquitySmallItemDivDef.HIGH_COMPULSIVE_PRICE_RANGE);
                l_arrQueryDataList.add(WEB3AdminEquitySmallItemDivDef.LOW_COMPULSIVE_PRICE_RANGE);
            }
            else
            {
	            l_arrQueryDataList.add(l_strSmallItemDiv);
            }
        }

        // Checks from date and toDate are not null
        if (l_datTermFrom != null && l_datTermTo != null)
        {
            l_arrQueryDataList.add(l_datTermTo);
            l_arrQueryDataList.add(l_datTermFrom);
        }

        l_queryData = l_arrQueryDataList.toArray();

        log.exiting(STR_METHOD_NAME);
        return l_queryData;
    }

    /**
     * �icreate�\�[�g�����j<BR>
     * <BR>
     * �\�[�g�������쐬����B<BR>
     * <BR>
     * �P�j�ȉ��̃\�[�g�����ɕ\���\�[�g������������쐬����B<BR>
     * <BR>
     * �@@[�\�[�g����]<BR>
     * �@@�@@�@@�����ڋ敪�@@����<BR>
     * �@@�@@�A�����R�[�h�@@����<BR>
     * �@@�@@�B�K�p����From ����<BR>
     * <BR>
     * �@@�\�[�g���������� = "small_item_div asc, "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "product_code asc, "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "term_from asc "<BR>
     * <BR>
     * �Q�j�쐬�����\�[�g�����������ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * createSortCond<BR>
     * <BR>
     * Create sortCond<BR>
     * <BR>
     * 1)Create a sortCondList that shows the following sortCond<BR>
     * <BR>
     * �@@[sortCond]<BR>
     * �@@�@@�@@smallItemDiv�@@ascending order<BR>
     * �@@�@@�AproductCode�@@ascending order<BR>
     * �@@�@@�BapplyStartDate ascending order<BR>
     * <BR>
     * �@@sortCondList = "small_item_div asc, "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "product_code asc, "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "term_from asc "<BR>
     * <BR>
     * 2) Return the created sortCondList<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41936E15029F
     */
    protected String createSortCond()
    {
        final String STR_METHOD_NAME = "createSortCond()";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sortCondList = new StringBuffer();

        // Sort condition
        l_sortCondList.append("to_number(small_item_div) asc, ");
        l_sortCondList.append("to_number(product_code) asc, ");
        l_sortCondList.append("term_from asc ");

        log.exiting(STR_METHOD_NAME);
        return l_sortCondList.toString();
    }

    /**
     * �icreate�\����ꗗ�j<BR>
     * <BR>
     * �\����̈ꗗ���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.�區�ڋ敪 == "�l�i���"�̏ꍇ�A<BR>
     * �@@�����l���쐬�p��HashMap���쐬����B<BR>
     * �@@�Q�|�P�jHashMap�𐶐�����B<BR>
     * �@@�Q�|�Q�j�p�����[�^.�������������ݒ�Params�ꗗ��<BR>
     * �@@�@@�@@�@@�v�f(=�������������ݒ�Params)�����A�ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�Q�|�Q�|�P�j��������HashMap.put()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key�F�@@�����Ώۂ̊������������ݒ�Params.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �����Ώۂ̊������������ݒ�Params.�s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �����Ώۂ̊������������ݒ�Params.�����ڋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@obj�F�@@�����Ώۂ̊������������ݒ�Params<BR>
     * <BR>
     * �R�j�p�����[�^.�������������ݒ�Params�ꗗ��<BR>
     * �@@�v�f(=�������������ݒ�Params)�����A�ȉ��̏������J��Ԃ��B<BR>
     * �@@�R�|�P�j�����Ώۂ̊������������ݒ�Params.�����ڋ敪 ==<BR>
     * �@@�@@�@@�@@�@@�@@"�����l��(���)" or "�����l��(����)"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�ȍ~�̏������s�킸�A���̗v�f�֏������ڍs����B(continue)<BR>
     * �@@�R�|�Q�j���������\����C���X�^���X�𐶐�����B<BR>
     * �@@�R�|�R�j�����������������\����C���X�^���X��<BR>
     * �@@�@@�@@�@@�@@�ȉ��̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�區�ڋ敪�@@=�@@�����Ώۂ̊������������ݒ�Params.�區�ڋ敪
     * �@@�@@�����ڋ敪�@@=�@@�����Ώۂ̊������������ݒ�Params.�����ڋ敪
     * �@@�@@�����R�[�h   = �����Ώۂ̊������������ݒ�Params.�����R�[�h<BR>
     * �@@�@@������       = �����Ώۂ̊������������ݒ�Params.����ID�ɊY�����������<BR>
     * �@@�@@�s��R�[�h   = �����Ώۂ̊������������ݒ�Params.�s��R�[�h<BR>
     * �@@�@@�o�^�l       = (*1)<BR>
     * �@@�@@�K�p����From = �����Ώۂ̊������������ݒ�Params.�K�p����From<BR>
     * �@@�@@�K�p����To   = �����Ώۂ̊������������ݒ�Params.�K�p����To<BR>
     * �@@�@@�X�V�҃R�[�h = �����Ώۂ̊������������ݒ�Params.�X�V�҃R�[�h<BR>
     * <BR>
     * �@@(*1)
     * �@@�@@[�����Ώۂ̊������������ݒ�Params.�����ڋ敪 ==<BR>
     * �@@�@@�@@"�����l��(�l���敪)"�̏ꍇ]<BR>
     * �@@�@@�@@get�����l��()�̖߂�l���Z�b�g�B<BR>
     * <BR>
     * �@@�@@[get�����l��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�����R�[�h�F�@@�����Ώۂ̊������������ݒ�Params.�����R�[�h<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����Ώۂ̊������������ݒ�Params.�s��R�[�h<BR>
     * �@@�@@�@@�������������ݒ�ꗗ�F�@@��������HashMapv
     * <BR>
     * �@@�@@[�����Ώۂ̊������������ݒ�Params.�����ڋ敪 ==<BR> 
     * �@@�@@"�D��s��"�̏ꍇ] <BR>
     * �@@�@@�����ڋ敪 = "�D��s��"<BR> 
     * �@@�@@�o�^�l = �����Ώۂ̊������������ݒ�Params.�ݒ�f�[�^(�\��)�ɊY������s��.�s��R�[�h<BR> 
     * <BR>
     * �@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�����Ώۂ̊������������ݒ�Params.�ݒ�f�[�^(�\��)���Z�b�g�B<BR>
     * <BR>
     * �@@�R�|�S�j��������ArrayList.add()���\�b�h���R�[�����A<BR>
     * �@@�@@�@@�@@�@@�v���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[add()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@arg0�F�@@�v���p�e�B�Z�b�g�������������\����<BR>
     * <BR>
     * �S�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * createScheduleInfoList<BR>
     * <BR>
     * Create scheduleInfoList<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)If parameter.largeItemDiv == "Def.PRICE_INFO"<BR>
     * �@@Create HashMap to make compulsive price range<BR>
     * �@@2-1)Create HashMap<BR>
     * �@@2-2)Loop the following process for as many times as
     * elements(=eqtypeProductConditionParams)<BR>
     *         of lists of parameter.eqtypeProductConditionParams<BR>
     * �@@�@@2-2-1)Call created HashMap.put() method<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[parameter set into put()]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key: eqtypeProductConditionParams to be
     * processed.product_code<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ eqtypeProductConditionParams to be
     * processed.market_code<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ eqtypeProductConditionParams to be
     * processed.small_item_div<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@obj: eqtypeProductConditionParams to be processed<BR>
     * <BR>
     * 3)Loop the following process for as many times as
     * elements(=eqtypeProductConditionParams)<BR>
     *         of lists of parameter.eqtypeProductConditionParams<BR>
     * �@@3-1)eqtypeProductConditionParams to be processed.small_item_div ==<BR>
     * �@@�@@�@@�@@�@@�@@If "Def.HIGH_COMPULSIVE_PRICE_RANGE" or
     * "Def.LOW_COMPULSIVE_PRICE_RANGE",<BR>
     * �@@�@@�@@�@@�@@don't execute the folloiwng process and jump to the next element to
     * process(continue)<BR>
     * �@@3-2)Create  WEB3AdminPMProductCondScheduleUnit instance<BR>
     * �@@3-3)Set the following properties to the created
     * WEB3AdminPMProductCondScheduleUnit instance<BR>
     * <BR>
     *    largeItemDiv  = eqtypeProductConditionParams to be
     * processed.large_item_div
     *    smallItemDiv  = eqtypeProductConditionParams to be
     * processed.small_item_div
     * �@@�@@productCode   = eqtypeProductConditionParams to be
     * processed.product_code<BR>
     * �@@�@@productName       = eqtypeProductConditionParams to be processed.productName
     * corresponding to product_id<BR>
     * �@@�@@marketCode   = eqtypeProductConditionParams to be processed.market_code<BR>
     * �@@�@@registData       = (*1)<BR>
     * �@@�@@applyStartDate = eqtypeProductConditionParams to be processed.term_from<BR>
     * �@@�@@applyEndDate   = eqtypeProductConditionParams to be processed.term_to<BR>
     * <BR>
     * �@@(*1)
     * �@@�@@[ If eqtypeProductConditionParams to be processed.small_item_div ==<BR>
     * �@@�@@�@@"Def.PRICE_RANGE_UNIT_TYPE)"]<BR>
     * �@@�@@�@@Set return value of getCompulsivePriceRange()<BR>
     * <BR>
     * �@@�@@[parameter set into getCompulsivePriceRange()]<BR>
     * �@@�@@�@@l_strProductCode: eqtypeProductConditionParams to be
     * processed.product_code<BR>
     * �@@�@@�@@l_strMarketCode: eqtypeProductConditionParams to be
     * processed.market_code<BR>
     * �@@�@@�@@l_eqtypeProductConditionList: created HashMapv
     * <BR>
     * �@@�@@[For other cases]<BR>
     * �@@�@@�@@Set eqtypeProductConditionParams to be processed.data_plan<BR>
     * <BR>
     * �@@3-4)Call created ArrayList.add() method, and<BR>
     * �@@�@@�@@�@@�@@add the instance set into'Property Set'<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[parameter set into add()]<BR>
     * �@@�@@�@@�@@�@@�@@arg0: WEB3AdminPMProductCondScheduleUnit set into 'Property Set'<BR>
     * <BR>
     * 4)Return return value of the created ArrayList.toArray()<BR>
     * <BR>
     * @@param l_largeItemDiv - �i�區�ڋ敪�j<BR>
     * <BR>
     * �區�ڋ敪<BR>
     * <BR>
     * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
     * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_largeItemDiv<BR>
     * <BR>
     * ��Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * values<BR>
     * <BR>
     * @@param l_eqtypeProductConditionParamsList - �������������ݒ�Params�ꗗ<BR>
     * <BR>
     * �������������ݒ�Params�̈ꗗ<BR>
     * <BR>
     * l_eqtypeProductConditionParamsList<BR>
     * <BR>
     * @@return ArrayList
     * @@throws NotFoundException NotFoundException
     * @@roseuid 419418BC012A
     */
    protected WEB3AdminPMProductCondScheduleUnit[] createScheduleInfoList(
        String l_largeItemDiv,
        EqtypeProductConditionParams[] l_eqtypeProductConditionParamsList)
        throws NotFoundException
    {
        final String STR_METHOD_NAME =
            "createScheduleInfoList(String, EqtypeProductConditionParams[])";
        log.entering(STR_METHOD_NAME);

        // Step 1
        ArrayList l_listProductCondScheduleUnitList = new ArrayList();
        HashMap l_mapComPriceRang = null;
        int l_eqtypeProductCondParamsCnt = 0;
        String l_strProductCode = null;
        String l_strMarketCode = null;
        String l_strSmallItemDiv = null;
        String l_strKey = null;
        long l_lngProductId = 0L;
        String l_strProductName = null;

        EqtypeProductConditionParams l_eqtypeProductConditionParams = null;
        WEB3AdminPMProductCondScheduleUnit l_adminPMProductCondScheduleUnit = null;
        WEB3EquityProductManager l_equityProductManager = null;
        WEB3AdminPMProductCondScheduleUnit[] l_productCondScheduleUnit =
            new WEB3AdminPMProductCondScheduleUnit[1];

        l_eqtypeProductCondParamsCnt = l_eqtypeProductConditionParamsList.length;

        // Step 2
        // Checks PRICE_INFO is equal to l_largeItemDiv
        if (WEB3AdminEquityLargeItemDivDef.PRICE_INFO.equals(l_largeItemDiv))
        {
            l_mapComPriceRang = new HashMap();

            //l_listProductCondScheduleUnitList.add(WEB3AdminEquityLargeItemDivDef.PRICE_INFO);

            // Step 2.2
            for (int i = 0; i < l_eqtypeProductCondParamsCnt; i++)
            {
                l_eqtypeProductConditionParams = l_eqtypeProductConditionParamsList[i];

                // Step 2.2.1
                l_strProductCode = l_eqtypeProductConditionParams.getProductCode();
                l_strMarketCode = l_eqtypeProductConditionParams.getMarketCode();
                l_strSmallItemDiv = l_eqtypeProductConditionParams.getSmallItemDiv();

                l_strKey = l_strProductCode + l_strMarketCode + l_strSmallItemDiv;
                l_mapComPriceRang.put(l_strKey, l_eqtypeProductConditionParams);
            }
        }

        // Step 3
        for (int i = 0; i < l_eqtypeProductCondParamsCnt; i++)
        {
            l_eqtypeProductConditionParams = l_eqtypeProductConditionParamsList[i];
            l_strProductCode = l_eqtypeProductConditionParams.getProductCode();
            l_strMarketCode = l_eqtypeProductConditionParams.getMarketCode();
            l_strSmallItemDiv = l_eqtypeProductConditionParams.getSmallItemDiv();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            l_equityProductManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();

            l_lngProductId = l_eqtypeProductConditionParams.getProductId();
            WEB3EquityProduct l_product =
                (WEB3EquityProduct) l_equityProductManager.getProduct(l_lngProductId);

            l_strProductName =
			((EqtypeProductRow)l_product.getDataSourceObject()).getStandardName();

            // Step 3-1
            /* If HIGH_COMPULSIVE_PRICE_RANGE or LOW_COMPULSIVE_PRICE_RANGE
             * equal to l_strSmallItemDiv then skip
             */
            if (WEB3AdminEquitySmallItemDivDef
                .HIGH_COMPULSIVE_PRICE_RANGE
                .equals(l_strSmallItemDiv)
                || WEB3AdminEquitySmallItemDivDef.LOW_COMPULSIVE_PRICE_RANGE.equals(
                    l_strSmallItemDiv))
            {
                continue;
            }
            // Step 3-2
            l_adminPMProductCondScheduleUnit = new WEB3AdminPMProductCondScheduleUnit();

            // Step 3-3
            l_adminPMProductCondScheduleUnit.largeItemDiv =
                l_eqtypeProductConditionParams.getLargeItemDiv();
            
            l_adminPMProductCondScheduleUnit.smallItemDiv = l_strSmallItemDiv;
            
            l_adminPMProductCondScheduleUnit.productCode =
                l_eqtypeProductConditionParams.getProductCode();

            l_adminPMProductCondScheduleUnit.productName = l_strProductName;

            l_adminPMProductCondScheduleUnit.marketCode =
                l_eqtypeProductConditionParams.getMarketCode();
                
			l_adminPMProductCondScheduleUnit.lastUpdater =
				l_eqtypeProductConditionParams.getLastUpdater();
                

            /* If PRICE_RANGE_UNIT_TYPE equals l_strSmallItemDiv, assign return of
             * getCompulsionPriceRange() to registData else assign data_plan
             */
            if (WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_UNIT_TYPE.equals(l_strSmallItemDiv))
            {
                l_adminPMProductCondScheduleUnit.smallItemDiv = WEB3AdminEquitySmallItemDivDef.COMPULSIVE_PRICE_RANGE;
                l_adminPMProductCondScheduleUnit.registData =
                    this.getCompulsionPriceRange(
                        l_strProductCode,
                        l_strMarketCode,
                        l_mapComPriceRang);
            } else if (WEB3AdminEquitySmallItemDivDef.PRIMARY_MARKET.equals(l_strSmallItemDiv))
            {
				l_adminPMProductCondScheduleUnit.smallItemDiv = WEB3AdminEquitySmallItemDivDef.PRIMARY_MARKET;
				WEB3GentradeFinObjectManager l_finObjectManager =
					(WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
				WEB3GentradeMarket l_market =
					(WEB3GentradeMarket) l_finObjectManager.getMarket(Long.parseLong(l_eqtypeProductConditionParams.getDataPlan()));
				l_adminPMProductCondScheduleUnit.registData = l_market.getMarketCode();
            }else
            {
				l_adminPMProductCondScheduleUnit.smallItemDiv = l_strSmallItemDiv;
                l_adminPMProductCondScheduleUnit.registData =
                    l_eqtypeProductConditionParams.data_plan;
            }
            l_adminPMProductCondScheduleUnit.applyStartDate =
                WEB3DateUtility.formatDate(l_eqtypeProductConditionParams.term_from, "yyyyMMdd");
            l_adminPMProductCondScheduleUnit.applyEndDate =
                WEB3DateUtility.formatDate(l_eqtypeProductConditionParams.term_to, "yyyyMMdd");
            // Step 3-4)
            l_listProductCondScheduleUnitList.add(l_adminPMProductCondScheduleUnit);
        }
        log.exiting(STR_METHOD_NAME);

        l_productCondScheduleUnit =
            (WEB3AdminPMProductCondScheduleUnit[]) l_listProductCondScheduleUnitList.toArray(
                l_productCondScheduleUnit);

        return l_productCondScheduleUnit;
    }

    /**
     * �iget�����l���j<BR>
     * <BR>
     * �����l����ԋp����B<BR>
     * <BR>
     * �P�j�����l��(�l���敪)���擾����B<BR>
     * �@@�p�����[�^.�������������ݒ�ꗗ.get()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[get()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@key�F�@@�p�����[�^.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@+ �p�����[�^.�s��R�[�h<BR>
     * �@@�@@�@@�@@�@@+ "�����l��(�l���敪)"(�����ڋ敪)<BR>
     * <BR>
     * �Q�j�����l��(����)���擾����B<BR>
     * �@@�p�����[�^.�������������ݒ�ꗗ.get()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[get()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@key�F�@@�p�����[�^.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@+ �p�����[�^.�s��R�[�h<BR>
     * �@@�@@�@@�@@�@@+ "�����l��(����)"(�����ڋ敪)<BR>
     * <BR>
     * �R�j�����l��(���)���擾����B<BR>
     * �@@�p�����[�^.�������������ݒ�ꗗ.get()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[get()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@key�F�@@�p�����[�^.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@+ �p�����[�^.�s��R�[�h<BR>
     * �@@�@@�@@�@@�@@+ "�����l��(����)"(�����ڋ敪)<BR>
     * <BR>
     * �S�j�����l�����쐬����B<BR>
     * �@@�@@[�@@�����l��(�l���敪) == "�w��Ȃ�"�̏ꍇ]<BR>
     * �@@�@@�@@�����l�� = �����l��(�l���敪)<BR>
     * <BR>
     * �@@�@@[�A�����l��(�l���敪) == "1/XX"�̏ꍇ]<BR>
     * �@@�@@�@@�����l�� = �����l��(�l���敪)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �����l��(����)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "�`"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �����l��(�l���敪)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �����l��(���)<BR>
     * <BR>
     * �@@�@@[�@@�A�A�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�����l�� = �����l��(����)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �����l��(�l���敪)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "�`"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �����l��(���)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �����l��(�l���敪)<BR>
     * <BR>
     * �T�j�쐬���������l����ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getCompulsionPriceRange<BR>
     * <BR>
     * Acquire compulsionPriceRange<BR>
     * <BR>
     * 1)Acquire PRICE_RANGE_UNIT_TYPE<BR>
     * �@@Call parameter.l_eqtypeProductConditionList.get() method<BR>
     * <BR>
     * �@@[parameter set into get()]<BR>
     * �@@�@@key: parameter.product_code<BR>
     * �@@�@@�@@�@@�@@+ parameter.market_code<BR>
     * �@@�@@�@@�@@�@@+ "PRICE_RANGE_UNIT_TYPE"(small_item_div)<BR>
     * <BR>
     * 2)Acquire LOW_COMPULSIVE_PRICE_RANGE<BR>
     * �@@Call parameter.l_eqtypeProductConditionList.get() method<BR>
     * <BR>
     * �@@[parameter set into get()]<BR>
     * �@@�@@key: parameter.product_code<BR>
     * �@@�@@�@@�@@�@@+ parameter.market_code<BR>
     * �@@�@@�@@�@@�@@+ "LOW_COMPULSIVE_PRICE_RANGE"(small_item_div)<BR>
     * <BR>
     * 3)Acquire HIGH_COMPULSIVE_PRICE_RANGE<BR>
     * �@@Call parameter.l_eqtypeProductConditionList.get() method<BR>
     * <BR>
     * �@@[parameter set into get()]<BR>
     * �@@�@@key: parameter.product_code<BR>
     * �@@�@@�@@�@@�@@+ parameter.market_code<BR>
     * �@@�@@�@@�@@�@@+ "HIGH_COMPULSIVE_PRICE_RANGE"(small_item_div)<BR>
     * <BR>
     * 4)Create COMPULSIVE_PRICE_RANGE<BR>
     * �@@�@@[If �@@PRICE_RANGE_UNIT_TYPE == Def.DEFAULT]<BR>
     * �@@�@@�@@compulsionPriceRange = PRICE_RANGE_UNIT_TYPE<BR>
     * <BR>
     * �@@�@@[If �APRICE_RANGE_UNIT_TYPE == Def.FRACTION]<BR>
     * �@@�@@�@@compulsionPriceRange = PRICE_RANGE_UNIT_TYPE<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ LOW_COMPULSIVE_PRICE_RANGE<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "�`"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ PRICE_RANGE_UNIT_TYPE<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ HIGH_COMPULSIVE_PRICE_RANGE<BR>
     * <BR>
     * �@@�@@[For other cases except �@@�A�A]<BR>
     * �@@�@@�@@compulsionPriceRange = LOW_COMPULSIVE_PRICE_RANGE<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ PRICE_RANGE_UNIT_TYPE<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "�`"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ HIGH_COMPULSIVE_PRICE_RANGE<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ PRICE_RANGE_UNIT_TYPE<BR>
     * <BR>
     * 5)Return the created compulsionPriceRange<BR>
     * <BR>
     * @@param l_strProductCode - �i�����R�[�h�j<BR>
     * <BR>
     * l_strProductCode<BR>
     * <BR>
     *
     * @@param l_strMarketCode - �i�s��R�[�h�j<BR>
     * <BR>
     * l_strMarketCode<BR>
     * <BR>
     *
     * @@param l_eqtypeProductConditionList - �i�������������ݒ�ꗗ�j<BR>
     * <BR>
     * �������������ݒ�ꗗ<BR>
     * �������R�[�h + �s��R�[�h + �����ڋ敪��key�Ƃ���HashMap<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_eqtypeProductConditionList<BR>
     * ��HashMap whose keys are productCode + marketCode + smallItemDiv<BR>
     * <BR>
     *
     *
     * @@return java.lang.String
     * @@roseuid 41946A260292
     */
    protected String getCompulsionPriceRange(
        String l_strProductCode,
        String l_strMarketCode,
        HashMap l_eqtypeProductConditionList)
    {
        String l_strLowCompulsivePriceRangeType = null;
        String l_strHighCompulsivePriceRangeType = null;
        String l_strPriceRangeType = null;
        String l_strKey = null;
        EqtypeProductConditionParams l_eqtypeProductConditionParams = null;
        String l_strCompulsionPriceRange = null;

        // Step 1 Compulsive price range
        l_strKey =
            l_strProductCode
                + l_strMarketCode
                + WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_UNIT_TYPE;

        l_eqtypeProductConditionParams =
            (EqtypeProductConditionParams) l_eqtypeProductConditionList.get(l_strKey);
        l_strPriceRangeType = l_eqtypeProductConditionParams.data_plan;

        // Step 2 Low compulsive price range
        l_strKey =
            l_strProductCode
                + l_strMarketCode
                + WEB3AdminEquitySmallItemDivDef.LOW_COMPULSIVE_PRICE_RANGE;

        l_eqtypeProductConditionParams =
            (EqtypeProductConditionParams) l_eqtypeProductConditionList.get(l_strKey);

        if (l_eqtypeProductConditionParams != null)
        {
			l_strLowCompulsivePriceRangeType = l_eqtypeProductConditionParams.data_plan;
        }else
        {
			l_strLowCompulsivePriceRangeType = null;
        }

        // Step 3 High compulsive price range
        l_strKey =
            l_strProductCode
                + l_strMarketCode
                + WEB3AdminEquitySmallItemDivDef.HIGH_COMPULSIVE_PRICE_RANGE;

        l_eqtypeProductConditionParams =
            (EqtypeProductConditionParams) l_eqtypeProductConditionList.get(l_strKey);
        
        if (l_eqtypeProductConditionParams != null)
        {
			l_strHighCompulsivePriceRangeType = l_eqtypeProductConditionParams.data_plan;
        }else
        {
			l_strHighCompulsivePriceRangeType = null;
        }
        
        if (l_strPriceRangeType == null)
        {
			l_strCompulsionPriceRange = null;
        }else
        {
			String l_strPriceRangeValue = new String();
	        
			if (l_strPriceRangeType.equals(WEB3PriceRangeIdDef.YEN))
			{
				l_strPriceRangeValue = WEB3AdminPriceRangeValueDef.YEN;
			} else if (l_strPriceRangeType.equals(WEB3PriceRangeIdDef.PERCENT))
			{
				l_strPriceRangeValue = WEB3AdminPriceRangeValueDef.PERCENT;
			} else if (l_strPriceRangeType.equals(WEB3PriceRangeIdDef.FRACTION))
			{
				l_strPriceRangeValue = WEB3AdminPriceRangeValueDef.FRACTION;
			}
			                                                 	
			
			// Step 4
	        // Depending on l_strPriceRangeType, value to l_strCompulsionPriceRange is set
	        if (WEB3PriceRangeIdDef.DEFAULT.equals(l_strPriceRangeType))
	        {
	            l_strCompulsionPriceRange = l_strPriceRangeType;
	        } else if (WEB3PriceRangeIdDef.FRACTION.equals(l_strPriceRangeType))
	        {
	            l_strCompulsionPriceRange =
				    l_strPriceRangeValue
	                    + l_strLowCompulsivePriceRangeType
	                    + "�^"
	                    + l_strPriceRangeValue
	                    + l_strHighCompulsivePriceRangeType;
	        } else
	        {
	            l_strCompulsionPriceRange =
	                l_strLowCompulsivePriceRangeType
	                    + l_strPriceRangeValue
	                    + "�^"
	                    + l_strHighCompulsivePriceRangeType
	                    + l_strPriceRangeValue;
	        }
        }

        return l_strCompulsionPriceRange;
    }
    
    /**
     * �isort�������������ݒ�Params�ꗗ�j<BR>
     * <BR>
     *�������������ݒ�Params�ꗗ����בւ��ĕԋp����B<BR>
     *<BR>
     *�P�jHashtable��List��ǉ��B<BR>
�@@   *�p�����[�^.�������������ݒ�Params�ꗗ�̗v�f�̐������A�ȉ��̏������J��Ԃ��B<BR>
     *<BR>
     *�@@�P�|�P�j�Y���̗v�f��ArrayList�ɒǉ�����B<BR>
     *<BR>
     *�@@�P�|�Q�j�Ō�̗v�f�̏ꍇ or �����ڋ敪�����̗v�f�ƈقȂ����ꍇ<BR>
     *<BR>
     *�@@�@@�P�|�Q�|�P�jArrayList��Hashtable�ɒǉ�������B<BR>
     *�@@�@@�@@�@@�@@Key�F�p�����[�^.�������������ݒ�Params�ꗗ�i�Y���v�f�j�̏����ڋ敪<BR>
     *<BR>
     *�@@�@@�P�|�Q�|�R�jArrayList������������B<BR>
     *<BR>
     *�Q�j�區�ڋ敪 == 5�i�l�����j�̏ꍇ<BR>
     *<BR>
     *�@@�Q�|�P�j�ȉ��̏��ԂŃf�[�^�擾Key��z��ɃZ�b�g����B<BR>
     *�@@�@@�@@�@@�@@��l�i�I�l�j�A��l�A�l���`�F�b�N�A<BR>
     *�@@�@@�@@�@@�@@�����l���i�l���敪�j�A�����l���i�����j�A�����l���i����j<BR>
     *<BR>
     *�@@�Q�|�Q�j�f�[�^�擾Key�̏��Ԃ�Hashtable����f�[�^���擾���A<BR>
     *�@@�@@�@@�@@�@@List�ɃZ�b�g����B<BR>
     *<BR>
     *�R�j�쐬����List��ԋp����B<BR>
     * <BR>
     * @@param �區�ڋ敪<BR>
     * <BR>
     * �區��<BR>
     * <BR>
     * @@param �������������ݒ�Params�ꗗ<BR>
     * <BR>
     * �������������ݒ�Params�ꗗ<BR>
     * @@return List
     * @@roseuid 41946A260292
     */

    protected List sortEqtypeProductParamsList(String l_strLargeItemDiv, List l_lisEqtypeProductConditionParamsList)
    {
        Hashtable l_htProductParamsList = new Hashtable();
        ArrayList l_alWork = new ArrayList();
        ArrayList l_alSmallItemDiv  = new ArrayList();
        EqtypeProductConditionParams l_eqtypeProductConditionParams = null;
        String l_smallItemDiv = null;
        
        int l_intListSize = l_lisEqtypeProductConditionParamsList.size();
        
        // Hashtable��List��ǉ��B
        for (int i = 0; i < l_intListSize; i++)
        {
            
            l_eqtypeProductConditionParams = 
                    (EqtypeProductConditionParams) l_lisEqtypeProductConditionParamsList.get(i);
            
            //�v�f��ArrayList�ɒǉ�����B
            l_alWork.add(l_eqtypeProductConditionParams);
            l_smallItemDiv = l_eqtypeProductConditionParams.small_item_div;
            
            //�Ō�̗v�f�̏ꍇ or �����ڋ敪�����̗v�f�ƈقȂ����ꍇ
            if(i == l_intListSize - 1 || 
               !l_smallItemDiv.equals(((EqtypeProductConditionParams) l_lisEqtypeProductConditionParamsList.get(i + 1)).getSmallItemDiv()))
            {
                //Hashtable�ɒǉ�������B
                l_htProductParamsList.put(l_smallItemDiv,l_alWork.clone());
                
                //ArrayList������������B
                l_alWork.clear();
            }

        }
        
        ArrayList l_arrQueryDataList = new ArrayList();
        String[] l_strTarget = null;
        
        //�區�ڋ敪 == 5�i�l�����j�̏ꍇ
        if (l_strLargeItemDiv.equals(WEB3LargeItemDef.PRICE_INFO))
        {
            //�ȉ��̏��ԂŔz��ɃZ�b�g����B
            //��l�i�I�l�j�A��l�A�l���`�F�b�N�A�����l��
            l_strTarget = new String[] {WEB3AdminEquitySmallItemDivDef.LAST_CLOSING_PRICE,
                                        WEB3AdminEquitySmallItemDivDef.BASE_PRICE,
                                        WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_TYPE,
                                        WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_UNIT_TYPE,
                                        WEB3AdminEquitySmallItemDivDef.LOW_COMPULSIVE_PRICE_RANGE,
                                        WEB3AdminEquitySmallItemDivDef.HIGH_COMPULSIVE_PRICE_RANGE};
            
            List l_lisProductParams = null;
            
            //�f�[�^�擾�L�[�̏��Ԃɕ��וς����s���B
            for (int i = 0; i < l_strTarget.length; i++)
            {
                l_lisProductParams = (List)l_htProductParamsList.get(l_strTarget[i]); 
                if ( l_lisProductParams != null)
                {
                    l_arrQueryDataList.addAll(l_lisProductParams);
                }   
            }
        }
        
        //�쐬����List��return����B
        return l_arrQueryDataList;
    }       
}@
