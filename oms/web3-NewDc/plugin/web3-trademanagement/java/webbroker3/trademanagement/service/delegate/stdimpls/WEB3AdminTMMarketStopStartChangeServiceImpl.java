head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.18.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMMarketStopStartChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҏs��ʎ����~�ĊJ�ύX�T�[�r�XImpl(WEB3AdminTMMarketStopStartChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/17 ��іQ(���u) �d�l�ύX���f��No.167
*/
package webbroker3.trademanagement.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.gentrade.data.BranchMarketDealtCondRow;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondRow;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.trademanagement.define.WEB3AdminTMHandlingPossibleMarketDef;
import webbroker3.trademanagement.define.WEB3AdminTMTradeStopDivDef;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTMMarketTradingStatusUnit;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMMarketStopStartChangeService;

/**
 * (�Ǘ��Ҏs��ʎ����~�ĊJ�ύX�T�[�r�XImpl)<BR>
 * <BR>
 * �Ǘ��Ҏs��ʎ����~�ĊJ�ύX�T�[�r�X�����N���X<BR>
 * <BR>
 * WEB3AdminTMMarketStopStartChangeServiceImpl<BR>
 * <BR>
 * WEB3AdminTMMarketStopStartChangeServiceImpl class<BR>
 * <BR>
 * @@author Sarvanan K
 * @@version 1.0
 */
public class WEB3AdminTMMarketStopStartChangeServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminTMMarketStopStartChangeService
{
    /** Log Variable.<BR> */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMMarketStopStartChangeServiceImpl.class);

    /**
     * @@roseuid 41DD3EC501A7
     */
    public WEB3AdminTMMarketStopStartChangeServiceImpl()
    {
    }

    /**
     * �s��ʎ����~�ĊJ�ύX�������s���B<BR>
     * <BR>
     * �����̃��N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�s��ʎ����~�ĊJ�ύX���̓��N�G�X�g�̏ꍇ<BR>
     * �@@this.get�ύX���͉��()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�s��ʎ����~�ĊJ�ύX�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.validate�ύX()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�s��ʎ����~�ĊJ�ύX�������N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�ύX()���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Execute WEB3AdminTMMarketStopStartChangeService process.<BR>
     * <BR>
     * Call the following methods and divided them according to the type of the
     * argument, l_request.<BR>
     * <BR>
     * ��If WEB3AdminTMMarketStopStartChangeInputRequest. <BR>
     *       Call this.getChangeInputScreen()<BR>
     * <BR>
     * ��If WEB3AdminTMMarketStopStartChangeConfirmRequest. <BR>
     *       Call this.validateChange()<BR>
     * <BR>
     * ��If WEB3AdminTMMarketStopStartChangeCompleteRequest. <BR>
     *       Call this.submitChange()<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@exception WEB3BaseException WEB3BaseException
     * @@roseuid 41770C92037F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        try
        {
            if (l_request instanceof WEB3AdminTMMStopStartChgInputRequest)
            {
                l_response =
                    this.getChangeInputScreen((WEB3AdminTMMStopStartChgInputRequest) l_request);

            } else if (l_request instanceof WEB3AdminTMMStopStartChgConfirmRequest)
            {
                l_response =
                    this.validateChange((WEB3AdminTMMStopStartChgConfirmRequest) l_request);
            } else if (l_request instanceof WEB3AdminTMMStopStartChgCompleteRequest)
            {
                l_response = this.submitChange((WEB3AdminTMMStopStartChgCompleteRequest) l_request);
            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT ���N�G�X�g NOT �Ǘ��Ҏs��ʎ����~�ĊJ�ύX�T�[�r�X���N�G�X�g");
            }
        } catch (DataQueryException l_dataQueryException)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dataQueryException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataQueryException.toString(),
                l_dataQueryException);
        } catch (DataNetworkException l_dataNetworkException)
        {
            String l_strMsg = "Error while aquiring the Data";
            log.error(l_strMsg, l_dataNetworkException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataNetworkException.toString(),
                l_dataNetworkException);

        } catch (NotFoundException l_notFoundException)
        {
            log.error(l_notFoundException.getMessage(), l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_notFoundException.getMessage(), l_notFoundException);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ύX���͉��)<BR>
     * <BR>
     * �s��ʎ����~�ĊJ�ύX���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҏs��ʎ����~�ĊJ�ύX�T�[�r�X)get�ύX���͉�ʁv<BR>
     * �Q��<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getChangeInputScreen<BR>
     * <BR>
     * Execute WEB3AdminTMMarketStopStartChangeService input screen process<BR>
     * <BR>
     * Refer to the sequence diagram "(Administrator market trade stop start change)
     * getChangeInputScreen". <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE�s��ʎ����~�ĊJ�ύX���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMMStopStartChgInputRequest object<BR>
     * <BR>
     * @@return webbroker3.trademnagement.message.WEB3AdminTMMStopStartChgInputResponse
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@exception WEB3BaseException WEB3BaseException
     * @@roseuid 41770CDF0302
     */
    protected WEB3AdminTMMStopStartChgInputResponse
          getChangeInputScreen(WEB3AdminTMMStopStartChgInputRequest l_request)
               throws WEB3BaseException, DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "getChangeInputScreen(WEB3AdminTMMStopStartChgInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMMStopStartChgInputResponse l_response = null;
        WEB3AdminTMMarketTradingStatusUnit l_tradingStatusUnit = null;
        WEB3AdminTMMarketTradingStatusUnit[] l_arrayTradingStatusUnit = null;
        WEB3Administrator l_admin = null;
        Institution l_institution = null;
        Market[] l_market = null;
        List l_arrayList = new ArrayList();
        boolean l_isUpdate;
        boolean l_isHandlingPossibleMarket;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        l_isUpdate = false;

        // 1.1 getInstanceFromLoginInfo
        l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        // 1.2 calling validateAuthority()
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRADE_MANAGEMENT_MARKET, l_isUpdate);

        // 1.3 calling getInstitution()
        l_institution = l_admin.getInstitution();

        // 1.4 calling getMarkets()
        l_market = l_finObjectManager.getMarkets(l_institution);

        int l_intMarketCnt = l_market.length;
        for (int i = 0; i < l_intMarketCnt; i++)
        {
            // 1.6.1 calling isHandlingPossibleMarket
            l_isHandlingPossibleMarket =
                this.isHandlingPossibleMarket((WEB3GentradeMarket) l_market[i]);

            // 1.6.2 new instance for WEB3AdminTMMarketTradingStatusUnit
            l_tradingStatusUnit = new WEB3AdminTMMarketTradingStatusUnit();

            // 1.6.3 setting values to l_tradingStatusUnit
            MarketParams l_marketRow = (MarketParams) l_market[i].getDataSourceObject();
            if(!l_marketRow.getMarketCode().equals(WEB3MarketCodeDef.DEFAULT))
            {
                l_tradingStatusUnit.marketCode = l_marketRow.getMarketCode();
                l_tradingStatusUnit.marketName = l_marketRow.getMarketName();
                l_tradingStatusUnit.afterTradeStopDiv = null;
                if (l_isHandlingPossibleMarket)
                {
                    l_tradingStatusUnit.tradeStopDiv = l_marketRow.getSuspension();
                } else
                {
                    l_tradingStatusUnit.tradeStopDiv = WEB3AdminTMTradeStopDivDef.DISABLE;
                }

                // 1.6.4 l_tradingStatusUnit is added to arraylist
                l_arrayList.add(l_tradingStatusUnit);
            }

        }

        // 1.7 converting arraylist to array
        l_arrayTradingStatusUnit =
            (WEB3AdminTMMarketTradingStatusUnit[]) l_arrayList.toArray(new WEB3AdminTMMarketTradingStatusUnit[0]);

        // 1.8 creating response object of WEB3AdminTMMStopStartChgInputResponse
        l_response = (WEB3AdminTMMStopStartChgInputResponse) l_request.createResponse();

        // 1.9 setting the values to response object
        l_response.currentDate =
            (Date) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_response.marketTradingStatusList = l_arrayTradingStatusUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�ύX)<BR>
     * <BR>
     * �s��ʎ����~�ĊJ�ύX�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҏs��ʎ����~�ĊJ�ύX�T�[�r�X)validate�ύX�v<BR>
     * �Q��<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * validateChange<BR>
     * <BR>
     * Execute WEB3AdminTMMarketStopStartChangeService validate process.<BR>
     * <BR>
     * Refer to the sequence diagram "(Administrator market trade stop start
     * change)validateChange". <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE�s��ʎ����~�ĊJ�ύX�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMMStopStartChgConfirmRequest object<BR>
     * <BR>
     * @@return
     * webbroker3.trademnagement.message.WEB3AdminTMMStopStartChgConfirmResponse
     * @@exception NotFoundException NotFoundException
     * @@exception WEB3BaseException WEB3BaseException
     * @@roseuid 41770CDF0312
     */
    protected WEB3AdminTMMStopStartChgConfirmResponse
       validateChange(WEB3AdminTMMStopStartChgConfirmRequest l_request)
       throws NotFoundException, WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AdminTMMStopStartChgConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMMStopStartChgConfirmResponse l_response = null;
        WEB3Administrator l_admin = null;
        WEB3GentradeFinObjectManager l_finObjectManager = null;
        WEB3AdminTMMarketTradingStatusUnit[] l_marketTradingStatusList = null;
        Market l_market = null;
        MarketRow l_marketRow = null;
        boolean l_isUpdate = true;
        String l_strInsitutionCode = null;
        String l_strMarketCode = null;

        // 1.1 validating the l_request
        l_request.validate();

        // 1.2 calling the getInstanceFromLoginInfo()
        l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        // 1.3 calling validateAuthority()
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRADE_MANAGEMENT_MARKET, l_isUpdate);

        // 1.4 calling getInstitutionCode()
        l_strInsitutionCode = l_admin.getInstitutionCode();

        // 1.5
        l_marketTradingStatusList = l_request.marketTradingStatusList;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_finObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        int l_intMarketTradingStatusListCnt = l_marketTradingStatusList.length;
        for (int i = 0; i < l_intMarketTradingStatusListCnt; i++)
        {
            // 1.5.1 calling isHandlingPossibleMarget
            if(l_marketTradingStatusList[i].tradeStopDiv.equals(WEB3AdminTMTradeStopDivDef.DISABLE))
            {
                continue;
            }
            
            // 1.5.2 getting marketCode and getting the market
            l_strMarketCode = l_marketTradingStatusList[i].marketCode;
            l_market = l_finObjectManager.getMarket(l_strInsitutionCode, l_strMarketCode);

            // 1.5.3 setting tradeStopDiv value to l_marketTradingStatusList.tradeStopDiv
            l_marketRow = (MarketRow) l_market.getDataSourceObject();
            l_marketTradingStatusList[i].tradeStopDiv = l_marketRow.getSuspension();
        }

        // 1.6 creating the response object for WEB3AdminTMMStopStartChgConfirmResponse
        l_response = (WEB3AdminTMMStopStartChgConfirmResponse) l_request.createResponse();

        // 1.7 setting the values to response object
        l_response.currentDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_response.marketTradingStatusList = l_marketTradingStatusList;

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * (submit�ύX)<BR>
     * <BR>
     * �s��ʎ����~�ĊJ�ύX�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҏs��ʎ����~�ĊJ�ύX�T�[�r�X)submit�ύX�v<BR>
     * �Q��<BR>
     * <BR>
     * -----<English>--------------<BR>
     * <BR>
     * submitChange<BR>
     * <BR>
     * Execute WEB3AdminTMMarketStopStartChangeService submit process.<BR>
     * <BR>
     * Refer to the sequence diagram  "(Administrator market trade stop start
     * change)submitChange".<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE�s��ʎ����~�ĊJ�ύX�������N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMMStopStartChgCompleteRequest object<BR>
     * <BR>
     * @@return
     * webbroker3.trademnagement.message.WEB3AdminTMMStopStartChgCompleteResponse
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@exception NotFoundException NotFoundException
     * @@exception WEB3BaseException WEB3BaseException
     * @@roseuid 41770CDF0331
     */
    protected WEB3AdminTMMStopStartChgCompleteResponse
        submitChange(WEB3AdminTMMStopStartChgCompleteRequest l_request)
        throws DataQueryException, DataNetworkException, NotFoundException, WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AdminTMMStopStartChgInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMMStopStartChgCompleteResponse l_response = null;
        WEB3Administrator l_admin = null;
        WEB3GentradeFinObjectManager l_finObjectManager = null;
        WEB3AdminTMMarketTradingStatusUnit[] l_marketTradingStatusList = null;
        Market l_market = null;
        MarketParams l_marketParams = null;
        MarketRow l_marketRow = null;
        boolean l_isUpdate = true;
        String l_strInsitutionCode;
        String l_strMarketCode;
        String l_strTradeStopDiv = null;

        // 1.1 validating the l_request
        l_request.validate();

        // 1.2 calling getInstanceFromLoginInfo()
        l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        // 1.3 calling validateAuthority
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRADE_MANAGEMENT_MARKET, l_isUpdate);

        // 1.4 validating the password
        l_admin.validateTradingPassword(l_request.password);

        // 1.5 getting institution code
        l_strInsitutionCode = l_admin.getInstitutionCode();

        // 1.6
        l_marketTradingStatusList = l_request.marketTradingStatusList;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_finObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        Timestamp l_timeStamp =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        for (int i = 0; i < l_marketTradingStatusList.length; i++)
        {
            
            // 1.6.1 calling isHandlingPossibleMarget
            if(l_marketTradingStatusList[i].tradeStopDiv.equals(WEB3AdminTMTradeStopDivDef.DISABLE))
            {
                continue;
            }
            
            // 1.6.2 calling getMarket()
            l_strMarketCode = l_marketTradingStatusList[i].marketCode;
            l_market = l_finObjectManager.getMarket(l_strInsitutionCode, l_strMarketCode);

            // 1.6.3 value assigned to tradeStopDiv of  WEB3AdminTMMarketTradingStatusUnit
            l_marketRow = (MarketRow) l_market.getDataSourceObject();
            l_strTradeStopDiv = l_marketRow.getSuspension();
            l_marketTradingStatusList[i].tradeStopDiv = l_strTradeStopDiv;

            // 1.6.4
            if (l_strTradeStopDiv != null
                && !l_strTradeStopDiv.equals(l_marketTradingStatusList[i].afterTradeStopDiv))
            {
                // 1.6.4.1 calling getDataSourceObject()
                l_marketRow = (MarketRow) l_market.getDataSourceObject();

                // 1.6.4.2 creating MarketParams object
                l_marketParams = new MarketParams(l_marketRow);

                // 1.6.4.3 set Property
                l_marketParams.suspension = l_marketTradingStatusList[i].afterTradeStopDiv;
                l_marketParams.last_updater = l_admin.getAdministratorCode();
                l_marketParams.last_updated_timestamp = l_timeStamp;

                // 1.6.4.4 updating the market values
                this.updateMarket(l_marketParams);
            }

            // 1.7 creating the response object of WEB3AdminTMMStopStartChgCompleteResponse
            l_response = (WEB3AdminTMMStopStartChgCompleteResponse) l_request.createResponse();

            // 1.8 set property
            l_response.currentDate = l_timeStamp;
            l_response.marketTradingStatusList = l_marketTradingStatusList;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (is�戵�\)<BR>
     * <BR>
     * isHandlingPossibleMarket<BR>
     * <BR>
     * �����Ɏw�肳�ꂽ�s�ꂪ�A�戵�\�ł��邩���ʂ���B<BR>
     * <BR>
     * �戵�\�ł���ꍇ��true��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * �P�j�ȉ��̏�����\�����ʌ��������������<BR>
     * �@@ArrayList(�p�����[�^�Z�b�g)���쐬����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h = �p�����[�^.�s��.�،���ЃR�[�h<BR>
     * �@@�@@�@@�s��R�[�h = �p�����[�^.�s��.�s��R�[�h<BR>
     * �@@�@@�@@�戵�\ = "�戵�\"<BR>
     * <BR>
     * �@@�����ʌ������������� = " institution_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and market_code = ? "<BR>
     * <BR>
     * �@@���p�����[�^�Z�b�g<BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l�����ɃZ�b�g�B<BR>
     * �@@�@@�@@�E�p�����[�^.�s��.�،���ЃR�[�h<BR>
     * �@@�@@�@@�E�p�����[�^.�s��.�s��R�[�h<BR>
     * �@@�@@�@@�E"1�F�戵�\"<BR>
     * <BR>
     * �Q�j�戵�\�`�F�b�N(����)<BR>
     * �@@�Q�|�P�j(���X�s���)�戵�����e�[�u���p�̌���������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = �P�j�ɂč쐬�������ʌ�������������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and mart_can_dealt_equity = ?"<BR>
     * <BR>
     * �@@�Q�|�Q�jQueryProcessor.doFindAllQuery()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@�O�������łȂ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�|PTS�s��̏ꍇ(����.�s��.isPTS�s��() == true)�́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@"(���X�s��ʁEPTS)�戵�����e�[�u��"(branch_market_pts_dealt_cond)<BR>
     * �@@�@@�@@�@@�@@�@@�|PTS�s��ȊO�̏ꍇ(����.�s��.isPTS�s��() == false)�́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@"(���X�s���)�戵�����e�[�u��"(branch_market_dealt_cond)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�O�������̏ꍇ�́A"(���X�s���.�O��)�戵�����e�[�u��"(feq_branch_market_dealt_cond)<BR>
     * �@@�@@�@@arg1�F�@@�Q�|�P�j�ɂč쐬������������������<BR>
     * �@@�@@�@@arg2�F�@@�P�j�ɍ쐬����ArrayList.toArray()<BR>
     * <BR>
     * �@@�Q�|�R�j�������ʂ��擾�ł����ꍇ�́Atrue��ԋp����B<BR>
     * <BR>
     * �R�j�戵�\�`�F�b�N(�M�p)<BR>
     * �@@�R�|�P�j(���X�s��ٍϕ�)�戵�����e�[�u���p�̌���������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = �P�j�ɂč쐬�������ʌ�������������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and mart_can_dealt = ?"<BR>
     * <BR>
     * �@@�R�|�Q�jQueryProcessor.doFindAllQuery()���R�[������B<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@"(���X�s��ٍϕ�)�戵�����e�[�u��"(branch_market_repay_dealt_cond)
     * <BR>
     * �@@�@@�@@arg1�F�@@�R�|�P�j�ɂč쐬������������������<BR>
     * �@@�@@�@@arg2�F�@@�P�j�ɍ쐬����ArrayList.toArray()<BR>
     * <BR>
     * �@@�R�|�R�j�������ʂ��擾�ł����ꍇ�́Atrue��ԋp����B<BR>
     * <BR>
     * �S�j�戵�\�`�F�b�N(�敨OP)<BR>
     * �@@�S�|�P�j(���X�w����)�戵�����e�[�u���p�̌���������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = �P�j�ɂč쐬�������ʌ�������������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and enable_order = ?"<BR>
     * <BR>
     * �@@�S�|�Q�jQueryProcessor.doFindAllQuery()���R�[������B<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@"(���X�w����)�戵�����e�[�u��"(branch_index_dealt_cond)<BR>
     * �@@�@@�@@arg1�F�@@�S�|�P�j�ɂč쐬������������������<BR>
     * �@@�@@�@@arg2�F�@@�P�j�ɍ쐬����ArrayList.toArray()<BR>
     * <BR>
     * �@@�S�|�R�j�������ʂ��擾�ł����ꍇ�́Atrue��ԋp����B<BR>
     * <BR>
     * �T�jfalse��ԋp����B�@@���戵�s�̎s��ł���ׁB<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * (isHandlingPossibleMarket)<BR>
     * <BR>
     * isHandlingPossibleMarket<BR>
     * <BR>
     * decide whether marketCode is possible to handle.<BR>
     * <BR>
     * Return true if handling is possible.<BR>
     * if not�Areturn false.<BR>
     * <BR>
     * �P�jGenerate the common search conditional string and ArrayList to show the
     * following conditions.<BR>
     * <BR>
     * �@@�@@[Condition]<BR>
     * �@@�@@�@@institutionCode = parameter.market.institution_code<BR>
     * �@@�@@�@@marketCode = parameter.market.market_code<BR>
     * �@@�@@�@@handlingPossibleMarket = Def.NORMAL<BR>
     * <BR>
     * �@@��The common search condition = " institution_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and market_code = ? "<BR>
     * <BR>
     * �@@��Set parameter<BR>
     * �@@�@@Generate ArrayList, and set the following values.<BR>
     * �@@�@@�@@�Eparameter.market.institution_code<BR>
     * �@@�@@�@@�Eparameter.market.market_code<BR>
     * �@@�@@�@@�E"1 : Def.NORMAL"<BR>
     * <BR>
     * �Q�jHandling possible check(Stock)<BR>
     * �@@�Q�|�P�jCreate a search character row for (branch
     * market)branch_market_dealt_cond table<BR>
     * <BR>
     * �@@�@@Search character row  = The common search condition created at 1)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and mart_can_dealt_equity = ?"<BR>
     * <BR>
     * �@@�Q�|�Q�jCall QueryProcessor.doFindAllQuery().<BR>
     * <BR>
     * �@@�@@[Parameter set in doFindAllQuery()]<BR>
     * �@@�@@�@@arg0�F�@@"(branch market)branch_market_dealt_cond table
     * "(branch_market_dealt_cond)<BR>
     * �@@�@@�@@arg1�F�@@The search character row made in 2-1)<BR>
     * �@@�@@�@@arg2�F�@@ArrayList.toArray() made in 1)<BR>
     * <BR>
     * �@@�Q�|�R�jReturn true when the search result can be acquired.<BR>
     * <BR>
     * �R�jHandling possible check(Margin)<BR>
     * �@@�R�|�P�jCreate a search character row for (branch market
     * pay)branch_market_dealt_cond table<BR>
     * <BR>
     * �@@�@@Search character row = The common search condition created at 1)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and mart_can_dealt = ?"<BR>
     * <BR>
     * �@@�R�|�Q�jCall QueryProcessor.doFindAllQuery().<BR>
     * <BR>
     * �@@�@@[The parameter to set in doFindAllQuery()]<BR>
     * �@@�@@�@@arg0�F�@@"(branch market pay)branch_market_dealt_cond
     * table"(branch_market_repay_dealt_cond)<BR>
     * �@@�@@�@@arg1�F�@@The search character row made in 3-1)<BR>
     * �@@�@@�@@arg2�F�@@ArrayList.toArray() made in 1)<BR>
     * <BR>
     * �@@�R�|�R�jReturn true when the search result can be acquired. <BR>
     * <BR>
     * �S�jHandling possible check(Future OP)<BR>
     * �@@�S�|�P�jCreate a search character row for (branch
     * index)branch_market_dealt_cond table<BR>
     * <BR>
     * �@@�@@Search character row = The common search condition created at 1)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and enable_order = ?"<BR>
     * <BR>
     * �@@�S�|�Q�jCall QueryProcessor.doFindAllQuery().<BR>
     * <BR>
     * �@@�@@[Parameter set in doFindAllQuery()]<BR>
     * �@@�@@�@@arg0�F�@@"(branch
     * index)branch_market_dealt_cond"(branch_index_dealt_cond)<BR>
     * �@@�@@�@@arg1�F�@@The search character row made in 4-1)<BR>
     * �@@�@@�@@arg2�F�@@ArrayList.toArray() made in 1)<BR>
     * <BR>
     * �@@�S�|�R�jIf the search result is aquired return true.<BR>
     * <BR>
     * �T�jReturn false. �@@��Because it is a non acceptable market to handle.<BR>
     * @@param l_market - (�s��I�u�W�F�N�g)<BR>
     * <BR>
     * Market object<BR>
     * <BR>
     * @@return boolean
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@roseuid 417CCD5D0309
     */
    protected boolean isHandlingPossibleMarket(WEB3GentradeMarket l_market)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "isHandlingPossibleMarket(WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);
        String l_strInstitutionCode = null;
        String l_strMarketCode = null;
        String l_strWhere = null;
        List l_lisSearchResult = null;
        QueryProcessor l_queryProcessor = null;

        // �P) Generate the common search conditional string and ArrayList.
        String l_strWhereClause = "institution_code=? and market_code=? ";

        /*
         * Generate ArrayList, and set the parameter.
         * WEB3HandlingPossibleMarketDef.DEFAULT (1)
         */
        l_strInstitutionCode = l_market.getInstitution().getInstitutionCode();
        l_strMarketCode = l_market.getMarketCode();
        Object[] l_arrObj =
          {l_strInstitutionCode, l_strMarketCode, WEB3AdminTMHandlingPossibleMarketDef.DEFAULT };

        l_queryProcessor = Processors.getDefaultProcessor();

        // �Q�jHandling possible check(Stock)
        l_strWhere = l_strWhereClause + " and mart_can_dealt_equity = ? ";
        if (l_strMarketCode.matches("^[A-Za-z].*"))
        {
            l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(FeqBranchMarketDealtCondRow.TYPE, l_strWhere, l_arrObj);
        }
        else
        {
            try
            {
                if(l_market.isPTSMarket())
                {
                    l_lisSearchResult =
                        l_queryProcessor.doFindAllQuery(BranchMarketPtsDealtCondRow.TYPE, l_strWhere, l_arrObj);
                }
                else
                {
                    l_lisSearchResult =
                        l_queryProcessor.doFindAllQuery(BranchMarketDealtCondRow.TYPE, l_strWhere, l_arrObj);    
                }
            }
            catch (WEB3BaseException l_wbe)
            {
                log.error(l_wbe.getMessage(), l_wbe);
                throw new WEB3BaseRuntimeException(
                    l_wbe.getErrorInfo(), 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_wbe.getMessage(), l_wbe);
            }
        }

        // If the search result(Stock) is aquired then return true.
        if (l_lisSearchResult.size() != 0)
        {
            return true;
        }

        // �R�jHandling possible check(Margin)
        l_strWhere = l_strWhereClause + " and mart_can_dealt = ? ";
        l_lisSearchResult =
            l_queryProcessor.doFindAllQuery(
                BranchMarketRepayDealtCondRow.TYPE,
                l_strWhere,
                l_arrObj);

        // If the search result(Margin) is aquired then return true.
        if (l_lisSearchResult.size() != 0)
        {
            return true;
        }

        // �S�jHandling possible check(Future OP)
        l_strWhere = l_strWhereClause + " and enable_order = ? ";
        l_lisSearchResult =
            l_queryProcessor.doFindAllQuery(BranchIndexDealtCondRow.TYPE, l_strWhere, l_arrObj);

        // If the search result(Future OP) is aquired then return true.
        if (l_lisSearchResult.size() != 0)
        {
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (update�s��)<BR>
     * <BR>
     * �����̎s��Params�Ŏs��e�[�u�����X�V����B<BR>
     * <BR>
     * �P�jQueryProcessor.doUpdateQuery()���\�b�h��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �@@[doUpdateQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�p�����[�^.�s��Params<BR>
     * <BR>
     * -----<English>-----------------<BR>
     * <BR>
     * updateMarket<BR>
     * <BR>
     * Update the market table with the argument, marketParams. <BR>
     * <BR>
     * �P�jCall QueryProcessor.doUpdateQuery() method. <BR>
     * <BR>
     * �@@[Parameter to set in doUpdateQuery()]<BR>
     * �@@�@@arg0�F�@@parameter.marketParams<BR>
     * <BR>
     * @@param l_marketParams - (�s��Params)<BR>
     * <BR>
     * l_marketParams<BR>
     * <BR>
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@roseuid 41947D34032E
     */
    protected void updateMarket(MarketParams l_marketParams)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "updateMarket(MarketParams)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        l_queryProcessor = Processors.getDefaultProcessor();

        // Update the market table with the argument, marketParams.
        l_queryProcessor.doUpdateQuery(l_marketParams);

        log.exiting(STR_METHOD_NAME);
    }
}@
