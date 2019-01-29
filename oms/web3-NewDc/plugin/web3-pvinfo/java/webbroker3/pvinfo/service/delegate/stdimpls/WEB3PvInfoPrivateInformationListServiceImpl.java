head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoPrivateInformationListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�XImpl(WEB3PvInfoPrivateInformationListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/25 ���O�B(���u) �쐬
Revesion History : 2005/08/16 ��(FLJ), ��(SRA) ���捞No.016(�p�t�H�[�}���X���P)
Revesion History : 2005/08/29 ��Y(SRA) �p�X���[�h�ύX�莞�ύX���b�Z�[�W�Ή�
Revesion History : 2005/10/07 ���(���u) �d�l�ύXNo.056~058
Revesion History : 2006/05/22 �юu��(���u) �d�l�ύX ���f��063
Revesion History : 2006/07/27 ꎉ�(���u) �d�l�ύX ���f��068�A069
Revesion History : 2006/09/12 �����F(���u) �d�l�ύX���f��070
Revesion History : 2007/02/26 ����(���u) �d�l�ύX���f��073
Revesion History : 2007/06/27 ���g(���u) �d�l�ύX���f��079
Revesion History : 2007/06/29 ���g(���u) �d�l�ύX���f��081
Revision History : 2007/07/13 �Ӑ�(���u) �d�l�ύX���f��082
Revision History : 2007/09/07 �g�E�N�|(���u) �d�l�ύX���f��091
Revision History : 2007/09/13 �g�E�N�|(���u) �d�l�ύX���f��093
Revision History : 2007/12/07 �И���(���u) �d�l�ύX���f��094
Revision History : 2008/02/18 ���g(���u) �d�l�ύX���f��102
Revision History : 2008/10/07 ���O(���u) �d�l�ύX���f��110
Revision History : 2008/10/07 ���m�a(���u) �d�l�ύX���f��107
Revision History : 2009/01/14 ���g(���u) �d�l�ύX���f��114
*/
package webbroker3.pvinfo.service.delegate.stdimpls;
    
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3PaymentStopDivDef;
import webbroker3.common.define.WEB3PvInfoBlinkDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoConditionDef;
import webbroker3.common.define.WEB3PvInfoDeleteFlagDef;
import webbroker3.common.define.WEB3PvInfoDisplayDeviceDef;
import webbroker3.common.define.WEB3PvInfoEffectiveFlagDef;
import webbroker3.common.define.WEB3PvInfoLastUpdateTimeDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoReadFlagDef;
import webbroker3.common.define.WEB3PwdChangeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.WEB3PvInfoDisplayMessageAccrualDateComparator;
import webbroker3.pvinfo.WEB3PvInfoDisplayTermToComparator;
import webbroker3.pvinfo.WEB3PvInfoFinalModTimeStampComparator;
import webbroker3.pvinfo.WEB3PvInfoReadUnReadComparator;
import webbroker3.pvinfo.WEB3PvInfoRegistMethodComparator;
import webbroker3.pvinfo.data.BrowseHistoryParams;
import webbroker3.pvinfo.data.BrowseHistoryRow;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.data.DisplayContentsRow;
import webbroker3.pvinfo.define.WEB3PvInfoConditionNoIntDef;
import webbroker3.pvinfo.define.WEB3PvInfoProductDivDef;
import webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionRequest;
import webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionResponse;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayMessageUnit;
import webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionRequest;
import webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionResponse;
import webbroker3.pvinfo.message.WEB3PvInfoIpoProductUnit;
import webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateRequest;
import webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateResponse;
import webbroker3.pvinfo.message.WEB3PvInfoTradeCountUnit;
import webbroker3.pvinfo.message.WEB3PvInfoTradePriceUnit;
import webbroker3.pvinfo.service.delegate.WEB3PvInfoPrivateInformationListService;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.define.WEB3TPShortfallGenerationStateDivDef;
import webbroker3.tradingpower.define.WEB3TPTradingStopDivDef;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�XImpl)<BR>
 * ��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X�����N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoPrivateInformationListServiceImpl extends WEB3ClientRequestService implements WEB3PvInfoPrivateInformationListService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PvInfoPrivateInformationListServiceImpl.class);
    /**
     * ��ײ�ްĲ�̫Ұ��݈ꗗ�������s���B<BR>
     * <BR>
     * �����̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���ڋq�A�����N�G�X�g�̏ꍇ<BR>
     * �@@�Eget�ڋq�A�����()���\�b�h���R�[��<BR>
     * <BR>
     * ���،���ИA�����N�G�X�g�̏ꍇ<BR>
     * �@@�Eget�،���ИA�����()���\�b�h���R�[��<BR>
     * <BR>
     * ���������󋵃��N�G�X�g�̏ꍇ<BR>
     * �@@�Eget�������󋵉��()���\�b�h���R�[��<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4145297302DD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3PvInfoAccountConnectionRequest)
        {
            //�ڋq�A�����N�G�X�g�̏ꍇ�Aget�ڋq�A�����()���\�b�h���R�[���B
            l_response = getAccountConnectionScreen((WEB3PvInfoAccountConnectionRequest)l_request);
        }
        else if (l_request instanceof WEB3PvInfoInstitutionConnectionRequest)
        {
            //�،���ИA�����N�G�X�g�̏ꍇ�Aget�،���ИA�����()���\�b�h���R�[���B
            l_response = getInstitutionConnectionScreen((WEB3PvInfoInstitutionConnectionRequest)l_request);
        }
        else if(l_request instanceof WEB3PvInfoOrderExecStateRequest)
        {
            //�������󋵃��N�G�X�g�̏ꍇ�Aget�������󋵉��()���\�b�h���R�[���B
            l_response = getOrderExecStateScreen((WEB3PvInfoOrderExecStateRequest)l_request);
        }
        else
        {
            String l_strErrorMessage =
                "�p�����[�^�̗ތ^���s���A�Y������WEB3PvInfoAccountConnectionRequest," +
                "WEB3PvInfoInstitutionConnectionRequest,WEB3PvInfoOrderExecStateRequest�ތ^�B";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorMessage);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ڋq�A�����)<BR>
     * �ڋq�A����ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X)get�ڋq�A����ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ڋq�A�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionResponse
     * @@roseuid 414529B002FC
     */
    protected WEB3PvInfoAccountConnectionResponse getAccountConnectionScreen(WEB3PvInfoAccountConnectionRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getAccountConnectionScreen(WEB3PvInfoAccountConnectionRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3PvInfoAccountConnectionResponse l_response = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1.1 validate()
        l_request.validate();

        //1.2 get�⏕����(SubAccountTypeEnum)
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.3 getMainAccount()
        l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //1.4 validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        ///////////////////////////////////////////////////////�ǉ�
        //1.5 get�{������ParamsTbl(�ڋq)
        Hashtable tbl= getBrowseHistoryParamsTbl(l_mainAccount);
        ///////////////////////////////////////////////////////
        
        //1.6 get�\�������ԍ��ꗗ�|�Ǘ��Ώ�( )
        String[] l_strManageDispConditionNoLists = this.getManagedDispCondNoList();

        // create��������������for�\�����e(String[], Hashtable)
        String l_strQueryStringForDispContents =
            this.createQueryStringForDispContents(l_strManageDispConditionNoLists, tbl);

        // create���������f�[�^�R���e�ifor�\�����e(�ڋq, String[], Hashtable)
        String[] l_strQueryDataContainerForDispContents =
            this.createQueryDataContainerForDispContents(l_mainAccount, l_strManageDispConditionNoLists, tbl);

        //1.9 get�\�����eParams�ꗗ(String, String[], String)
        List l_lisDisplayContentsParams = l_dataManager.getDisplayContentsParamsList(l_strQueryStringForDispContents, l_strQueryDataContainerForDispContents, null);

        //1.11 ArrayList()
        List l_lisArrayList = new ArrayList();
        
        if (l_lisDisplayContentsParams != null)
        {
            //1.10 get�\�����eParams�ꗗ�|�\���Ώ�(�ڋq, �⏕����, �\�����eParams[])
            DisplayContentsParams[] l_displayContensParamLists = new DisplayContentsParams[l_lisDisplayContentsParams.size()];
            l_lisDisplayContentsParams.toArray(l_displayContensParamLists);
            l_lisDisplayContentsParams = getDisplayContensInDisplayTerm(l_mainAccount, l_subAccount, l_displayContensParamLists);     
                 
            if (l_lisDisplayContentsParams != null)
            {

                //1.12 (*) get�\�����eParams�ꗗ()�̖߂�l�̗v�f����Loop���������{����B
                int l_intDispContParamsCnt = l_lisDisplayContentsParams.size();
                log.debug("get�\�����eParams�ꗗ()�̖߂�l�̗v�f�� = " + l_intDispContParamsCnt);
                for (int i = 0; i < l_intDispContParamsCnt; i++)
                {
                    //1.12.1 get�{������Params(�ڋq, long)
                    DisplayContentsParams l_dispContentsParams = (DisplayContentsParams)l_lisDisplayContentsParams.get(i);
        
                    //�C��///////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    //BrowseHistoryParams l_browseHistoryParams = l_dataManager.getBrowseHistoryParams(l_mainAccount, l_dispContentsParams.display_contents_id);
                    Long l_lngConditionId = new Long(l_dispContentsParams.getDisplayContentsId());
                    BrowseHistoryParams l_browseHistoryParams = null;
                    if(tbl!=null){
                        l_browseHistoryParams = (BrowseHistoryParams) tbl.get(l_lngConditionId);
                    }
                    //�C��///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
                    //1.12.2 �_�C���N�g�w��`�F�b�N
                    if (WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_dispContentsParams.condition_no) && l_browseHistoryParams == null)
                    {
                        continue;
                    }
        
                    //1.12.3 �폜�t���O�`�F�b�N
                    if (l_browseHistoryParams != null && WEB3PvInfoDeleteFlagDef.DELETE_YES.equals(l_browseHistoryParams.delete_flag))
                    {
                        continue;
                    }
                    //1.12.6 �\�����b�Z�[�W���()
                    WEB3PvInfoDisplayMessageUnit l_dispMessage = new WEB3PvInfoDisplayMessageUnit();
        
                    //1.12.4 ����t���[      
                    Date l_datClaimGenDate = null;  
                    Date l_datAdvanceGenDate = null;  
                    if ((WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(l_dispContentsParams.condition_no) 
                            || WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(l_dispContentsParams.condition_no)))
                    {
                        //1.12.4.1 get��������������(�⏕���� : �⏕����)
                        l_datClaimGenDate = l_dataManager.getPayClaimGenDate((WEB3GentradeSubAccount)l_subAccount);
                        l_dispMessage.displayMessageDate = l_datClaimGenDate;
                    }
        
                    //1.12.5 ����t���[
                    else if (WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(l_dispContentsParams.condition_no))
                    {
                        //1.12.5.1 get���֋�������(�⏕���� : �⏕����)
                        l_datAdvanceGenDate = l_dataManager.getAdvanceGenDate((WEB3GentradeSubAccount)l_subAccount);
                        l_dispMessage.displayMessageDate = l_datAdvanceGenDate;
                    }
                    else
                    {
                        l_dispMessage.displayMessageDate = GtlUtils.getTradingSystem().getBizDate();
                    }
        
                    //1.12.7 �v���p�e�B�Z�b�g
                    l_dispMessage.displayContentsId = WEB3StringTypeUtility.formatNumber(l_dispContentsParams.display_contents_id);
                    log.debug("�\�����b�Z�[�W���.displayContentsId = " + l_dispMessage.displayContentsId);
                    l_dispMessage.conditionNumber = l_dispContentsParams.condition_no;
                    log.debug("�\�����b�Z�[�W���.conditionNumber = " + l_dispMessage.conditionNumber);
                    l_dispMessage.priorityDiv = l_dispContentsParams.priority_div;
                    log.debug("�\�����b�Z�[�W���.priorityDiv = " + l_dispMessage.priorityDiv);
                    l_dispMessage.listStartDate = null;
                    log.debug("�\�����b�Z�[�W���.listStartDate = " + l_dispMessage.listStartDate);
                    l_dispMessage.listEndDate = null;
                    log.debug("�\�����b�Z�[�W���.listEndDate = " + l_dispMessage.listEndDate);
                    l_dispMessage.displayTitle = l_dispContentsParams.display_title;
                    log.debug("�\�����b�Z�[�W���.displayTitle = " + l_dispMessage.displayTitle);
                    l_dispMessage.displayMessage = null;
                    log.debug("�\�����b�Z�[�W���.displayMessage = " + l_dispMessage.displayMessage);
                    l_dispMessage.displayColor = l_dispContentsParams.display_color;
                    log.debug("�\�����b�Z�[�W���.displayColor = " + l_dispMessage.displayColor);
        
                    if (WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES.equals(l_dispContentsParams.blink_display_flag))
                    {
                        l_dispMessage.blinkDisplayFlag = true;
                    }
                    else
                    {
                        l_dispMessage.blinkDisplayFlag = false;
                    }
                    log.debug("�\�����b�Z�[�W���.blinkDisplayFlag = " + l_dispMessage.blinkDisplayFlag);
        
                    l_dispMessage.displayUrl = null;
                    log.debug("�\�����b�Z�[�W���.displayUrl = " + l_dispMessage.displayUrl);
        
                    if (WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES.equals(l_dispContentsParams.last_update_time_display_flag))
                    {
                        l_dispMessage.lastUpdateTimeDisplayFlag = false;
                    }
                    else
                    {
                        l_dispMessage.lastUpdateTimeDisplayFlag = true;
                    }
                    log.debug("�\�����b�Z�[�W���.lastUpdateTimeDisplayFlag = " + l_dispMessage.lastUpdateTimeDisplayFlag);
        
                    if (WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES.equals(l_dispContentsParams.effective_flag))
                    {
                        l_dispMessage.effectiveFlag = false;
                    }
                    else
                    {
                        l_dispMessage.effectiveFlag = true;
                    }
                    log.debug("�\�����b�Z�[�W���.effectiveFlag = " + l_dispMessage.effectiveFlag);
        
                    l_dispMessage.displayDevice = l_dispContentsParams.display_device;
                    log.debug("�\�����b�Z�[�W���.displayDevice = " + l_dispMessage.displayDevice);
                    l_dispMessage.lastUpdateMember = null;
                    log.debug("�\�����b�Z�[�W���.lastUpdateMember = " + l_dispMessage.lastUpdateMember);
                    l_dispMessage.lastUpdatedTimestamp = l_dispContentsParams.last_updated_timestamp;
                    log.debug("�\�����b�Z�[�W���.lastUpdatedTimestamp = " + l_dispMessage.lastUpdatedTimestamp);
                    //�{������ID ���@@get�{������Params()�̖߂�l == null�̏ꍇ�́Anull���Z�b�g�B
                    if (l_browseHistoryParams == null)
                    {
                        l_dispMessage.browseHistoryId = null;
                    }
                    //�ȊO�Aget�{������Params()�̖߂�l.�{������ID���Z�b�g�B
                    else
                    {
                        l_dispMessage.browseHistoryId = "" + l_browseHistoryParams.getBrowseHistoryId();
                    }
        
                    log.debug("�\�����b�Z�[�W���.displayMessageDate = " + l_dispMessage.displayMessageDate);
                    if (l_browseHistoryParams == null)
                    {
                        l_dispMessage.readFlag = false;
                    }
                    else if (WEB3PvInfoReadFlagDef.READ_YES.equals(l_browseHistoryParams.read_flag))
                    {
                       l_dispMessage.readFlag = true;
                    }
                    else
                    {
                        l_dispMessage.readFlag = false;
                    }
                    log.debug("�\�����b�Z�[�W���.readFlag = " + l_dispMessage.readFlag);
        
                    //1.12.8 add(�\�����b�Z�[�W��� : Object)
                    l_lisArrayList.add(l_dispMessage);
                }
            }
        }
        //1.13 toArray()
        WEB3PvInfoDisplayMessageUnit[] l_dispMessageUnits = new WEB3PvInfoDisplayMessageUnit[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_dispMessageUnits);
        
        //1.14 sort�\�����b�Z�[�W���
        sortDispMessageUnit(l_dispMessageUnits);

        //1.15 createResponse()
        l_response = (WEB3PvInfoAccountConnectionResponse)l_request.createResponse();

        //1.16 ����t���[: toArray()�̖߂�l.length == 0�̏ꍇ
        if (l_dispMessageUnits.length == 0)
        {    
            // �v���p�e�B�Z�b�g
            //�ڋq��
            l_response.accountName = l_mainAccount.getNameDetails().getFamilyName();
            //���y�[�W�� ���@@1
            l_response.totalPages = "1";
            //�����R�[�h��  ���@@0
            l_response.totalRecords = "0";
            //�\���y�[�W�ԍ� ���@@1
            l_response.pageIndex = "1";
            //�\�����b�Z�[�W���ꗗ   ���@@null
            l_response.displayMessageUnits = null;

            log.debug("l_response.accountName = " + l_response.accountName);
            log.debug("l_response.totalPages = " + l_response.totalPages);
            log.debug("l_response.totalRecords = " + l_response.totalRecords);
            log.debug("l_response.pageIndex = " + l_response.pageIndex);
            log.debug("l_response.displayMessageUnits = " + l_response.displayMessageUnits);
            log.exiting(STR_METHOD_NAME);
            return l_response;

        }
        //1.17 ����t���[: toArray()�̖߂�l.length != 0�̏ꍇ
        else 
        {
            //�v���p�e�B�Z�b�g
            //�ڋq��
            l_response.accountName = l_mainAccount.getNameDetails().getFamilyName();

            //�d�l�ύX:�����R�[�h�� = toArray()�̖߂�l.length
            l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_dispMessageUnits.length);

            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            int l_intPageSize = Integer.parseInt(l_request.pageSize);

            WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_dispMessageUnits, l_intPageIndex, l_intPageSize);
            l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
            l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
            l_response.displayMessageUnits = (WEB3PvInfoDisplayMessageUnit[])l_pageIndexInfo.getArrayReturned(WEB3PvInfoDisplayMessageUnit.class);

            log.debug("l_response.accountName" + "=" + l_response.accountName);
            log.debug("l_response.totalPages" + "=" + l_response.totalPages);
            log.debug("l_response.pageIndex" + "=" + l_response.pageIndex);
            log.debug("l_response.totalRecords" + "=" + l_response.totalRecords);
            log.debug("l_response.displayMessageUnits" + "=" + l_response.displayMessageUnits.length);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�،���ИA�����)<BR>
     * �،���ИA����ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X)get�،���ИA����ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �،���ИA�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionResponse
     * @@roseuid 414529B0031B
     */
    protected WEB3PvInfoInstitutionConnectionResponse getInstitutionConnectionScreen(WEB3PvInfoInstitutionConnectionRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstitutionConnectionScreen(WEB3PvInfoInstitutionConnectionRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3PvInfoInstitutionConnectionResponse l_response = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1.1 validate()
        l_request.validate();

        //1.2 get�⏕����(SubAccountTypeEnum)
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.3 getMainAccount()
        l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //1.4 validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5 get�\�������ԍ��ꗗ�|�Ǘ��ΏۊO( )
        String[] l_strManageDispConditionNoLists = this.getManagedOutDispCondNoList();

        //1.6 create��������������for�\�����e(String[])
        String l_strQueryStringForDispContents = this.createQueryStringForDispContents(l_strManageDispConditionNoLists);

        //1.7 create���������f�[�^�R���e�ifor�\�����e(�ڋq, String[])
        String[] l_strQueryDataContainerForDispContents = this.createQueryDataContainerForDispContents(l_mainAccount, l_strManageDispConditionNoLists);

        //1.8 get�\�����eParams�ꗗ(String, String[], String)
        String l_strCondition = "priority_div asc,"
            + "last_updated_timestamp desc,"
            + "term_to desc";
        List l_lisDisplayContentsParams = l_dataManager.getDisplayContentsParamsList(l_strQueryStringForDispContents, l_strQueryDataContainerForDispContents, l_strCondition);
        log.debug("get�\�����eParams�ꗗ(String, String[], String)�����s���܂�");

        //1.10 ArrayList()
        List l_lisArrayList = new ArrayList();

        WEB3PageIndexInfo l_pageIndexInfo = null;
        
        if (l_lisDisplayContentsParams != null)
        {
            //1.9 get�\�����eParams�ꗗ�|�\���Ώ�(�ڋq, �⏕����, �\�����eParams[])    
            DisplayContentsParams[] l_displayContensParamLists = new DisplayContentsParams[l_lisDisplayContentsParams.size()];
            l_lisDisplayContentsParams.toArray(l_displayContensParamLists);
//          *** ���捞No.016 START ***
            l_lisDisplayContentsParams = this.getDisplayContensInDisplayTerm(l_mainAccount, l_subAccount, l_displayContensParamLists);
//          *** ���捞No.016 END ***
            if (l_lisDisplayContentsParams != null)
            {

                //1.11 get�\�����eParams�ꗗ()�̖߂�l�̂����A�\���Ώۍs�ifromIndex �` toIndex�j�̊�Loop���������{����B
                DisplayContentsParams[] l_dcParams = new DisplayContentsParams[l_lisDisplayContentsParams.size()];
                l_lisDisplayContentsParams.toArray(l_dcParams);
                int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
                int l_intPageSize = Integer.parseInt(l_request.pageSize);
                l_pageIndexInfo = new WEB3PageIndexInfo(l_dcParams, l_intPageIndex, l_intPageSize);
                DisplayContentsParams[] l_dcParamsReturns = (DisplayContentsParams[])l_pageIndexInfo.getArrayReturned(DisplayContentsParams.class);

                if (l_dcParamsReturns != null)
                {
                
                    int l_intDispContParamsCnt = l_dcParamsReturns.length;
                    for (int i = 0; i < l_intDispContParamsCnt; i++)
                    {
                        DisplayContentsParams l_dispContentsParams = l_dcParamsReturns[i];
            
                        //1.11.1 �\�����e���()
                        WEB3PvInfoDisplayContentsUnit l_dispContentsUnits = new WEB3PvInfoDisplayContentsUnit();
            
                        //1.11.2 �v���p�e�B�Z�b�g
                        l_dispContentsUnits.displayContentsId = WEB3StringTypeUtility.formatNumber(l_dispContentsParams.display_contents_id);
                        log.debug("�\�����e���.displayContentsId" + "=" + l_dispContentsUnits.displayContentsId);
                        l_dispContentsUnits.conditionNumber = l_dispContentsParams.condition_no;
                        log.debug("�\�����e���.conditionNumber" + "=" + l_dispContentsUnits.conditionNumber);
                        l_dispContentsUnits.priorityDiv = l_dispContentsParams.priority_div;
                        log.debug("�\�����e���.priorityDiv" + "=" + l_dispContentsUnits.priorityDiv);
                        l_dispContentsUnits.listStartDate = null;
                        log.debug("�\�����e���.listStartDate" + "=" + l_dispContentsUnits.listStartDate);
                        l_dispContentsUnits.listEndDate = null;
                        log.debug("�\�����e���.listEndDate" + "=" + l_dispContentsUnits.listEndDate);
                        l_dispContentsUnits.displayTitle = l_dispContentsParams.display_title;
                        log.debug("�\�����e���.displayTitle" + "=" + l_dispContentsUnits.displayTitle);
                        l_dispContentsUnits.displayMessage = null;
                        log.debug("�\�����e���.displayMessage" + "=" + l_dispContentsUnits.displayMessage);
                        l_dispContentsUnits.displayColor = l_dispContentsParams.display_color;
                        log.debug("�\�����e���.displayColor" + "=" + l_dispContentsUnits.displayColor);
            
                        if (WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES.equals(l_dispContentsParams.blink_display_flag))
                        {
                            l_dispContentsUnits.blinkDisplayFlag = true;
                        }
                        else
                        {
                            l_dispContentsUnits.blinkDisplayFlag = false;
                        }
                        log.debug("�\�����e���.blinkDisplayFlag" + "=" + l_dispContentsUnits.blinkDisplayFlag);
            
                        l_dispContentsUnits.displayUrl = null;
                        log.debug("�\�����e���.displayUrl" + "=" + l_dispContentsUnits.displayUrl);
            
                        if (WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES.equals(l_dispContentsParams.last_update_time_display_flag))
                        {
                            l_dispContentsUnits.lastUpdateTimeDisplayFlag = false;
                        }
                        else
                        {
                            l_dispContentsUnits.lastUpdateTimeDisplayFlag = true;
                        }
                        log.debug("�\�����e���.lastUpdateTimeDisplayFlag" + "=" + l_dispContentsUnits.lastUpdateTimeDisplayFlag);
            
                        if (WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES.equals(l_dispContentsParams.effective_flag))
                        {
                            l_dispContentsUnits.effectiveFlag = false;
                        }
                        else
                        {
                            l_dispContentsUnits.effectiveFlag = true;
                        }
                        log.debug("�\�����e���.effectiveFlag" + "=" + l_dispContentsUnits.effectiveFlag);
            
                        l_dispContentsUnits.displayDevice = l_dispContentsParams.display_device;
                        log.debug("�\�����e���.displayDevice" + "=" + l_dispContentsUnits.displayDevice);
                        l_dispContentsUnits.lastUpdateMember = null;
                        log.debug("�\�����e���.lastUpdateMember" + "=" + l_dispContentsUnits.lastUpdateMember);
                        l_dispContentsUnits.lastUpdatedTimestamp = l_dispContentsParams.last_updated_timestamp;
                        log.debug("�\�����e���.lastUpdatedTimestamp" + "=" + l_dispContentsUnits.lastUpdatedTimestamp);
            
                        //1.11.3 add(�\�����e���I�u�W�F�N�g : Object)
                        l_lisArrayList.add(l_dispContentsUnits);
                    }
                }
            }
        }    

        //1.12  toArray()
        WEB3PvInfoDisplayContentsUnit[] l_dispContentsUnits = new WEB3PvInfoDisplayContentsUnit[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_dispContentsUnits);

        //1.13  createResponse()
        l_response = (WEB3PvInfoInstitutionConnectionResponse)l_request.createResponse();

        //1.14 ����t���[ :toArray()�̖߂�l.length == 0�̏ꍇ
        if (l_dispContentsUnits.length == 0)
        {
            //1.14.1 �v���p�e�B�Z�b�g
            //�،���Ж� ���@@getMainAccount()�̖߂�l.getInstitution().��Ж�
            l_response.institutionName = l_mainAccount.getInstitution().getInstitutionName();
            //���y�[�W�� ���@@1
            l_response.totalPages = "1";
            //�����R�[�h�� ���@@0
            l_response.totalRecords = "0";
            //�\���y�[�W�ԍ� ���@@1
            l_response.pageIndex = "1";
            //�\�����e���ꗗ ���@@null
            l_response.displayContentsUnits = null;
        }
        //1.15 ����t���[ : toArray()�̖߂�l.length != 0�̏ꍇ
        else
        {   
            l_response.institutionName = l_mainAccount.getInstitution().getInstitutionName();
            l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
            l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
            l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
            l_response.displayContentsUnits = l_dispContentsUnits;
    
            log.debug("***l_response.institutionName" + "=" + l_response.institutionName);
            log.debug("***l_response.totalPages" + "=" + l_response.totalPages);
            log.debug("***l_response.totalRecords" + "=" + l_response.totalRecords);
            log.debug("***l_response.pageIndex" + "=" + l_response.pageIndex);
            log.debug("***l_response.displayContentsUnits" + "=" + l_response.displayContentsUnits.length);
            log.exiting(STR_METHOD_NAME);
        }    
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�������󋵉��)<BR>
     * �������󋵉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X)get�������󋵉�ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �������󋵃��N�G�X�g�I�u�W�F�N�g<BR>
     *
     * @@return webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateResponse
     * @@roseuid 414529B0032B
     */
    protected WEB3PvInfoOrderExecStateResponse getOrderExecStateScreen(WEB3PvInfoOrderExecStateRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getOrderExecStateScreen(WEB3PvInfoOrderExecStateRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3PvInfoOrderExecStateResponse l_response = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1.1 get�⏕����(SubAccountTypeEnum)
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.2 getMainAccount()
        l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //1.3 validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.4 create��������������for��������()
        String l_strQueryStringForOrderCnt = this.createQueryStringForOrderCnt();

        //1.5 create���������f�[�^�R���e�ifor��������(�ڋq)
        String[] l_strQueryDataContainerForOrderCnts = this.createQueryDataContainerForOrderCnt(l_mainAccount);

        //1.6 �����E��茏���擾�Ώۂ̏��i�敪�̐���Loop����
        int[][] l_intCounts = new int[5][3];

        for (int i = 0; i <= 4; i++)
        {
            //1.6.1 get��������(String, boolean, String, String[])
            l_intCounts[i][0] = l_dataManager.getOrderCnt(WEB3StringTypeUtility.formatNumber(i), true, l_strQueryStringForOrderCnt, l_strQueryDataContainerForOrderCnts);

            //1.6.2 get��������(String, boolean, String, String[])
            l_intCounts[i][1] = l_dataManager.getOrderCnt(WEB3StringTypeUtility.formatNumber(i), false, l_strQueryStringForOrderCnt, l_strQueryDataContainerForOrderCnts);

            //1.6.3 get��茏��(�ڋq, String)
            l_intCounts[i][2] = l_dataManager.getExecuteCnt(l_mainAccount, WEB3StringTypeUtility.formatNumber(i));
            

        }
        
        //�ȉ��̏��i�敪�ɂ���Loop����B
        //�E"0�F����"
        //�E"1�F�M�p"
        //�E"2�F�敨"
        //�E"3�F�I�v�V
        //1.7 ��������擾�Ώۂ̏��i�敪�̐���Loop����
        double[][] l_dblPrice = new double[4][1];
        String[] l_strProductDivDef = {WEB3PvInfoProductDivDef.PD_SPOT,WEB3PvInfoProductDivDef.PD_CREDIT,WEB3PvInfoProductDivDef.PD_FUTURE,WEB3PvInfoProductDivDef.PD_OPTION};
        for (int i = 0; i < l_strProductDivDef.length; i++)
        {
            //1.7.1 get�������(���i�敪 : String, �������������� : String, ���������f�[�^�R���e�i : String[])
           l_dblPrice[i][0] = l_dataManager.getTradePrice(l_strProductDivDef[i],l_strQueryStringForOrderCnt,l_strQueryDataContainerForOrderCnts);
        }
        //l_dataManager.getTradePrice()
        //1.8 ����������()
        WEB3PvInfoTradeCountUnit l_todayTradeCountUnit = new WEB3PvInfoTradeCountUnit();
        WEB3PvInfoTradeCountUnit l_tomorrowTradeCountUnit = new WEB3PvInfoTradeCountUnit();
        WEB3PvInfoTradeCountUnit l_todayOrderTradeCountUnit = new WEB3PvInfoTradeCountUnit();

        //1.9 �v���p�e�B�Z�b�g

        l_todayTradeCountUnit.equityTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[0][0]);
        log.debug("������������������.equityTradeCount" + "=" + l_todayTradeCountUnit.equityTradeCount);
        l_todayTradeCountUnit.marginTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[1][0]);
        log.debug("������������������.marginTradeCount" + "=" + l_todayTradeCountUnit.marginTradeCount);
        l_todayTradeCountUnit.futuresTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[2][0]);
        log.debug("������������������.futuresTradeCount" + "=" + l_todayTradeCountUnit.futuresTradeCount);
        l_todayTradeCountUnit.optionsTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[3][0]);
        log.debug("������������������.optionsTradeCount" + "=" + l_todayTradeCountUnit.optionsTradeCount);
        //�O����������� ���@@�擾����������������(�O����)
        l_todayTradeCountUnit.foreignEquityTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[4][0]);

        l_tomorrowTradeCountUnit.equityTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[0][1]);
        log.debug("������������������.equityTradeCount" + "=" + l_tomorrowTradeCountUnit.equityTradeCount);
        l_tomorrowTradeCountUnit.marginTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[1][1]);
        log.debug("������������������.marginTradeCount" + "=" + l_tomorrowTradeCountUnit.marginTradeCount);
        l_tomorrowTradeCountUnit.futuresTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[2][1]);
        log.debug("������������������.futuresTradeCount" + "=" + l_tomorrowTradeCountUnit.futuresTradeCount);
        l_tomorrowTradeCountUnit.optionsTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[3][1]);
        log.debug("������������������.optionsTradeCount" + "=" + l_tomorrowTradeCountUnit.optionsTradeCount);
        //�O�����������   ���@@�擾����������������(�O����)
        l_tomorrowTradeCountUnit.foreignEquityTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[4][1]);

        l_todayOrderTradeCountUnit.equityTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[0][2]);
        log.debug("��������������������.equityTradeCount" + "=" + l_todayOrderTradeCountUnit.equityTradeCount);
        l_todayOrderTradeCountUnit.marginTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[1][2]);
        log.debug("��������������������.marginTradeCount" + "=" + l_todayOrderTradeCountUnit.marginTradeCount);
        l_todayOrderTradeCountUnit.futuresTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[2][2]);
        log.debug("��������������������.futuresTradeCount" + "=" + l_todayOrderTradeCountUnit.futuresTradeCount);
        l_todayOrderTradeCountUnit.optionsTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[3][2]);
        log.debug("��������������������.optionsTradeCount" + "=" + l_todayOrderTradeCountUnit.optionsTradeCount);
        //�O�����������   ���@@�擾����������茏��(�O����)
        l_todayOrderTradeCountUnit.foreignEquityTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[4][2]);

        //1.10 ����������C���X�^���X�𐶐�����B
        WEB3PvInfoTradePriceUnit l_tradePriceUnit = new WEB3PvInfoTradePriceUnit();

        //1.11 ������������������I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B
        //�����������        ���@@�擾���������������(����)
        l_tradePriceUnit.equityTradePrice = WEB3StringTypeUtility.formatNumber(l_dblPrice[0][0]);
        //�M�p����������        ���@@�擾���������������(�M�p)
        l_tradePriceUnit.marginTradePrice = WEB3StringTypeUtility.formatNumber(l_dblPrice[1][0]);
        //�敨����������        ���@@�擾���������������(�敨)
        l_tradePriceUnit.futuresTradePrice = WEB3StringTypeUtility.formatNumber(l_dblPrice[2][0]);
        //�I�v�V�������������� ���@@�擾���������������(�I�v�V����)
        l_tradePriceUnit.optionsTradePrice = WEB3StringTypeUtility.formatNumber(l_dblPrice[3][0]);
        //1.12 getIPO�\��Params(�ڋq)
        List l_lisIpoOrderParams = l_dataManager.getIpoOrderParams(l_mainAccount);

        //1.13 ArrayList()
        List l_lisArrayList = new ArrayList();

        //1.14 getIPO�\��Params()�̖߂�l�̐���Loop����
        if (l_lisIpoOrderParams != null)
        {
            int l_intIpoOrderParamsCnt = l_lisIpoOrderParams.size();
            log.debug("getIPO�\��Params()�̖߂�l�̗v�f�� = " + l_intIpoOrderParamsCnt);
            for (int i = 0; i < l_intIpoOrderParamsCnt; i++)
            {
                //1.14.1 getIPO����Params(IPO����ID : long)
                IpoOrderParams l_ipoOrderParams = (IpoOrderParams)l_lisIpoOrderParams.get(i);
                IpoProductParams  l_productParams = null;
                
                boolean l_isAdvancedElection = true;
                // ���I�̏ꍇ
                if (WEB3LotResultDef.ELECTION.equals(l_ipoOrderParams.getLotResult()))
                {
                    l_isAdvancedElection = false;
                }
                
                l_productParams = l_dataManager.getIpoProductParams(l_ipoOrderParams.ipo_product_id, l_isAdvancedElection);
                if (l_productParams == null)
                {
                    continue;
                }
                //1.14.2 IPO���I�������()
                WEB3PvInfoIpoProductUnit l_ipoProductUnit = new WEB3PvInfoIpoProductUnit();

                //1.14.3 �v���p�e�B�Z�b�g
                l_ipoProductUnit.lotWinProductName = l_productParams.standard_name;
                log.debug("IPO���I�������.lotWinProductName" + "=" + l_ipoProductUnit.lotWinProductName);
                 l_ipoProductUnit.lotWinCount = l_ipoOrderParams.elected_quantity.toString();
                log.debug("IPO���I�������.lotWinCount" + "=" + l_ipoProductUnit.lotWinCount);
                //1.14.4 add(IPO���I������� : Object)
                l_lisArrayList.add(l_ipoProductUnit);
            }
        }
        //1.15 toArray()
        WEB3PvInfoIpoProductUnit[] l_ipoProductUnits = new WEB3PvInfoIpoProductUnit[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_ipoProductUnits);

        //1.16 is�M�p�����J��(String)
        boolean l_blnMarginAccountEstablished = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //1.17 is�敨OP�����J��(String)
        boolean l_blnIfoAccountOpen = l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES);

        //1.18 is�敨OP�����J��(String)
        boolean l_blnIfoOptionAccountOpen = l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION);
        //1.19 �c�Ɠ��̈ꗗ���擾����B
        Date[] l_datBizDateLists = l_dataManager.getBizDateList();
        //1.20 creatResponse()
        l_response = (WEB3PvInfoOrderExecStateResponse)l_request.createResponse();

        //1.21 �v���p�e�B�Z�b�g
        l_response.bizDateList = l_datBizDateLists;
        l_response.marginAccOpen = l_blnMarginAccountEstablished;
        l_response.futuresAccOpen = l_blnIfoAccountOpen;
        l_response.optionsAccOpen = l_blnIfoOptionAccountOpen;
        l_response.orderCountsToday = l_todayTradeCountUnit;
        l_response.orderCountsTomorrow = l_tomorrowTradeCountUnit;
        l_response.execCountsToday = l_todayOrderTradeCountUnit;
        l_response.ipoProductUnits = l_ipoProductUnits;
        l_response.tradePriceUnit = l_tradePriceUnit;

        log.debug("l_response.marginAccOpen" + "=" + l_response.marginAccOpen);
        log.debug("l_response.futuresAccOpen" + "=" + l_response.futuresAccOpen);
        log.debug("l_response.optionsAccOpen" + "=" + l_response.optionsAccOpen);
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create��������������for��������)<BR>
     * ���������擾�p�̌�������������(���ʕ���)���쐬����B<BR>
     * <BR>
     * �P�j�ȉ��̌���������\���A����������������쐬����B<BR>
     * �@@[��������]<BR>
     * �@@----------------------------------------------<BR>
     * �@@�@@����ID = �ڋq.getAccountId()�@@����<BR>
     * �@@�@@������� != "6�F�������s(�V�K����)" ����<BR>
     * �@@�@@(�����敪 != "3�F�}�[�P�b�g����" or  <BR>
     * �@@�@@(��萔�� is not null and ��萔�� > 0))�@@����  <BR>
     * �@@�@@������������敪 != "3�F�S���������"<BR>
     * �@@----------------------------------------------<BR>
     * ��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = "account_id = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "order_status != ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(expiration_status != ? or "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(executed_quantity is not null and executed_quantity > ?)) and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "modify_cancel_type != ? "<BR>
     * <BR>
     * 2�j�쐬�������������������ԋp����B<BR>
     * @@return String
     * @@roseuid 41456B320202
     */
    protected String createQueryStringForOrderCnt() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " createQueryStringForOrderCnt()";
        log.entering(STR_METHOD_NAME );

        //������������ɁA����������������쐬����B
        String l_strQueryString = " account_id = ? and "
                                        + "order_status != ? and "
                                        + "(expiration_status != ? or "
                                        + "(executed_quantity is not null and executed_quantity > ?)) and "
                                        + "modify_cancel_type != ? ";

        log.debug("��������������:l_strQueryString" + "=" + l_strQueryString);
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (create���������f�[�^�R���e�ifor��������)<BR>
     * ���������擾�p�̌�������������(���ʕ���)�́g?�h�����ɃZ�b�g����<BR>
     * �p�����[�^���X�g(������z��)���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�ȉ��̏��ԂŌڋq����ArrayList�ɒǉ�����B<BR>
     * ���ȍ~�AArrayList�ɒǉ�����ۂɂ́AString�^�ɕϊ����Ă���ǉ����邱�ƁB<BR>
     * �@@�@@�E�p�����[�^.�ڋq.getAccountId()<BR>
     * �@@�@@�E"6�F�������s(�V�K����)"<BR>
     * �@@�@@�E"3�F�}�[�P�b�g����"<BR>
     * �@@�@@�E"0"<BR>
     * �@@�@@�E"3�F�S���������"<BR>
     * <BR>
     * �U�jArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return String[]
     * @@roseuid 41456B320221
     */
    protected String[] createQueryDataContainerForOrderCnt(WEB3GentradeMainAccount l_mainAccount)
    {

        final String STR_METHOD_NAME = " createQueryDataContainerForOrderCnt(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME );

        //1.ArrayList
        List l_lisArrayList = new ArrayList();

        //�Q.�ڋq����ArrayList�ɒǉ�����B
        //�p�����[�^.�ڋq.getAccountId()
        l_lisArrayList.add(WEB3StringTypeUtility.formatNumber(l_mainAccount.getAccountId()));
        //"6�F�������s(�V�K����)"
        l_lisArrayList.add(new Integer(OrderStatusEnum.NOT_ORDERED.intValue()).toString());
        //"3�F�}�[�P�b�g����"
        l_lisArrayList.add(new Integer(OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue()).toString());
        //"0"
        l_lisArrayList.add("0");
        //"3�F�S���������"
        l_lisArrayList.add(WEB3ModifyCancelTypeDef.CANCELED);

        //ArrayList.toArray()�̖߂�l��ԋp����B
        String[] l_strArrayLists = new String[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_strArrayLists);

        log.exiting(STR_METHOD_NAME);
        return l_strArrayLists;
    }

    /**
     * (create��������������for�\�����e)<BR>
     * �\�����e�擾�p�̌���������������쐬����B<BR>
     * <BR>
     * �P�j�ȉ��̌���������\���A����������������쐬����B<BR>
     * �@@[��������]<BR>
     * �@@----------------------------------------------<BR>
     * �@@�@@�،���ЃR�[�h = �ڋq.�،���ЃR�[�h�@@����<BR>
     * �@@�@@���ݏ� = "0�F�L��"�@@����<BR>
     * �@@�@@(�\������From <= ���ݎ����@@�܂���<BR>
     * �@@�@@�@@�\������From IS null)�@@����<BR>
     * �@@�@@(�\������To >= ���ݎ����@@�܂���<BR>
     * �@@�@@�@@�\������To IS null)�@@����<BR>
     * �@@�@@�\���}�� = (���O�C���`���l���ɂ��)<BR>
     * �@@----------------------------------------------<BR>
     * ��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = "institution_code = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "effective_flag = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(term_from <= to_date(?, 'YYYYMMDDHH24MI') <BR>
     *                               or term_from is null) and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(term_to >= to_date(?, 'YYYYMMDDHH24MI') <BR>
     *                               or term_to is null) and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "display_device in (?, ?) "<BR>
     * <BR>
     * �Q�j�p�����[�^.�\�������ԍ��ꗗ != null�̏ꍇ�A�ȉ��̏������s���B<BR>
     * �@@�Q�|�P�j��������������Ɉȉ��̏�����ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += " and condition_no in ("<BR>
     * <BR>
     * �@@�Q�|�Q�j�p�����[�^.�\�������ԍ��ꗗ.length�̐����A<BR>
     * �@@�@@�@@�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�@@�@@�������������� += "?"<BR>
     * �@@�@@�@@��2��ڂ����"?"�ł͂Ȃ��A", ?"��ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�R�j��������������Ɉȉ��̏�����ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += ") "<BR>
     * <BR>
     * �U�j�쐬�������������������ԋp����B<BR>
     * @@param l_strDispConditonNoList - (�\�������ԍ��ꗗ)<BR>
     * �\�������ԍ��ꗗ<BR>
     * <BR>
     * 0000�F�@@�_�C���N�g�w��<BR>
     * 1001�F�@@������������&�M�p�����J��<BR>
     * 1002�F�@@������������&�M�p�������J��<BR>
     * 1003�F�@@���֋�����<BR>
     * 1004�F�@@���֋�����<BR>
     * 1005�F�@@�؋����s��<BR>
     * 1006�F�@@���ϊ����ԋ߁i�ꃖ���O�j�̌��ʕۗL<BR>
     * 1007�F�@@���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL<BR>
     * 1008�F�@@�M�p�����J��<BR>
     * 1009�F�@@�M�p�������J��<BR>
     * 1010�F�@@�I�v�V���������J��<BR>
     * 1011�F�@@�����ۗL<BR>
     * 1012�F�@@�M�p���ʕۗL<BR>
     * 1013�F�@@���M�ۗL<BR>
     * 1014�F�@@�ݓ��ۗL<BR>
     * 1015�F�@@�I�v�V�������ʕۗL<BR>
     * 1016�F�@@�~�j���ۗL<BR>
     * 1017�F�@@�敨�ۗL<BR>
     * 1018�F�@@�a����L��&�،��c����<BR>
     * 1019�F�@@�a�������&�،��c����<BR>
     * 1020�F�@@�����E�M�p���������i�����j<BR>
     * 1021�F�@@�����E�M�p���������i�����j<BR>
     * 1022�F�@@�����E�M�p��蔭��<BR>
     * 1023�F�@@�S�ڋq<BR>
     * 1024�F�@@���[���A�h���X���o�^<BR>
     * 1025�F�@@IPO���I<BR>
     * 1026�F�@@IPO�J�グ���I<BR>
     * 1027�F�@@�����~<BR>
     * @@return String
     * @@roseuid 41459B280108
     */
    protected String createQueryStringForDispContents(String[] l_strDispConditonNoList) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " createQueryStringForDispContents(String[])";
        log.entering(STR_METHOD_NAME );

        //�P�j����������\���A����������������쐬����B
        //������������ɁA����������������쐬����
        String l_strQueryStringForDispContents = " institution_code = ? and "
                                                        + "effective_flag = ? and "
                                                        + "(to_char(term_from, 'YYYYMMDDHH24') <= ? or term_from is null) and "
                                                        + "(to_char(term_to, 'YYYYMMDDHH24') >= ? or term_to is null) and "
                                                        + "display_device in (?, ?) ";

        //2.�p�����[�^.�\�������ԍ��ꗗ != null�̏ꍇ�A�ȉ��̏������s���B
        if (l_strDispConditonNoList != null)
        {
            //�Q�|�P�j��������������Ɉȉ��̏�����ǉ�����B
            l_strQueryStringForDispContents += " and condition_no in (";

            //�Q�|�Q�j�p�����[�^.�\�������ԍ��ꗗ.length�̐����A�ȉ��̏������J��Ԃ��B
            int l_intDispConditionNoListCnt = l_strDispConditonNoList.length;
            l_strQueryStringForDispContents += "?";

            for (int i = 1; i < l_intDispConditionNoListCnt; i++)
            {
                l_strQueryStringForDispContents += ", ?";
            }
            l_strQueryStringForDispContents += ")";
        }

        log.debug("��������������:l_strQueryStringForDispContents" + "=" + l_strQueryStringForDispContents);
        log.exiting(STR_METHOD_NAME);
        return l_strQueryStringForDispContents;
    }

    /**
     * (create��������������for�\�����e)<BR>
     * �\�����e�擾�p�̌���������������쐬����B<BR>
     * <BR>
     * �P�j�ȉ��̌���������\���A����������������쐬����B<BR>
     * �@@[��������]<BR>
     * �@@----------------------------------------------<BR>
     * �@@�@@�،���ЃR�[�h = �ڋq.�،���ЃR�[�h�@@����<BR>
     * �@@�@@���ݏ� = "0�F�L��"�@@����<BR>
     * �@@�@@(�\������From <= ���ݎ����@@�܂���<BR>
     * �@@�@@�@@�\������From IS null)�@@����<BR>
     * �@@�@@(�\������To >= ���ݎ����@@�܂���<BR>
     * �@@�@@�@@�\������To IS null)�@@����<BR>
     * �@@�@@�\���}�� = (���O�C���`���l���ɂ��)<BR>
     * �@@----------------------------------------------<BR>
     * ��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = "( institution_code = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "effective_flag = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(to_char(term_from, 'YYYYMMDDHH24') <= ? <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@or term_from is null) and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(to_char(term_to, 'YYYYMMDDHH24') >= ? <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@or term_to is null) and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "display_device in (?, ?) "<BR>
     * <BR>
     * �Q�j�p�����[�^.�\�������ԍ��ꗗ != null�̏ꍇ�A�ȉ��̏������s���B<BR>
     * �@@�Q�|�P�j��������������Ɉȉ��̏�����ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += " and condition_no in ("<BR>
     * <BR>
     * �@@�Q�|�Q�j�p�����[�^.�\�������ԍ��ꗗ���"0000�F�_�C���N�g�w��"��������<BR>
     * �@@�@@�@@�p�����[�^.�\�������ԍ��ꗗ.length�̐����A�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�@@�@@�������������� += "?"<BR>
     * �@@�@@�@@��2��ڂ����"?"�ł͂Ȃ��A", ?"��ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�R�j��������������Ɉȉ��̏�����ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += ") "<BR>
     * <BR>
     * �@@�Q�|�S�j�p�����[�^.�{������Tbl != null�̏ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�Q�|�S�|�P�j��������������Ɉȉ��̏�����ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += ") or ( effective_flag = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(to_char(term_from, 'YYYYMMDDHH24') <= ? <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@or term_from is null) and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(to_char(term_to, 'YYYYMMDDHH24') >= ? <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@or term_to is null) and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "display_device in (?, ?) and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "condition_no = '0000' and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "display_contents_id in ("<BR>
     * <BR>
     * �@@�@@�Q�|�S�|�Q�j�p�����[�^.�{������Tbl�̗v�f�����A<BR>
     * �@@�@@�@@�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�@@�@@�������������� += "?"<BR>
     * �@@�@@�@@��2��ڂ����"?"�ł͂Ȃ��A", ?"��ǉ�����B<BR>
     * <BR>
     * �@@�@@�Q�|�S�|�R�j��������������Ɉȉ��̏�����ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += ")"<BR>
     * <BR>
     * �R�j��������������Ɉȉ��̏�����ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += ")"<BR>
     * <BR>
     * �S�j�쐬�������������������ԋp����B<BR>
     * @@param l_strDispConditionNoList - (�\�������ԍ��ꗗ)<BR>
     * �\�������ԍ��ꗗ<BR>
     * <BR>
     * @@param l_htBrowseHistory - (�{������Tbl)<BR>
     * �{������Tbl<BR>
     * <BR>
     * @@return String
     */
    protected String createQueryStringForDispContents(
        String[] l_strDispConditionNoList,
        Hashtable l_htBrowseHistory)
    {

        final String STR_METHOD_NAME = " createQueryStringForDispContents(String[], Hashtable)";
        log.entering(STR_METHOD_NAME);

        //�P�j�ȉ��̌���������\���A����������������쐬����B
        String l_strQueryStringForDispContents =
            "( institution_code = ? and "
            + "effective_flag = ? and "
            + "(to_char(term_from, 'YYYYMMDDHH24') <= ? or term_from is null) and "
            + "(to_char(term_to, 'YYYYMMDDHH24') >= ? or term_to is null) and "
            + "display_device in (?, ?) ";

        //�Q�j�p�����[�^.�\�������ԍ��ꗗ != null�̏ꍇ�A�ȉ��̏������s���B
        if (l_strDispConditionNoList != null)
        {
            //�Q�|�P�j��������������Ɉȉ��̏�����ǉ�����B
            //�������������� += " and condition_no in ("
            l_strQueryStringForDispContents += " and condition_no in (";

            //�Q�|�Q�j�p�����[�^.�\�������ԍ��ꗗ���"0000�F�_�C���N�g�w��"��������
            //�p�����[�^.�\�������ԍ��ꗗ.length�̐����A�ȉ��̏������J��Ԃ��B
            int l_intDispConditionNoListCnt = l_strDispConditionNoList.length;
            List l_lisArrayList = new ArrayList();
            for (int i = 0; i < l_intDispConditionNoListCnt; i++)
            {
                if (!WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_strDispConditionNoList[i]))
                {
                    l_lisArrayList.add(l_strDispConditionNoList[i]);
                }
            }

            String[] l_strArrayLists;
            l_strArrayLists = new String[l_lisArrayList.size()];
            l_lisArrayList.toArray(l_strArrayLists);

            l_intDispConditionNoListCnt = l_strArrayLists.length;
            //�������������� += "?"
            l_strQueryStringForDispContents += "?";

            for (int i = 1; i < l_intDispConditionNoListCnt; i++)
            {
                //��2��ڂ����"?"�ł͂Ȃ��A", ?"��ǉ�����B
                l_strQueryStringForDispContents += ", ?";
            }

            //�Q�|�R�j��������������Ɉȉ��̏�����ǉ�����B
            //�������������� += ") "
            l_strQueryStringForDispContents += ")";

            //�Q�|�S�j�p�����[�^.�{������Tbl != null�̏ꍇ�A�ȉ��̏������s���B
            if (l_htBrowseHistory != null)
            {
                //�Q�|�S�|�P�j��������������Ɉȉ��̏�����ǉ�����B
                l_strQueryStringForDispContents += ") or ( effective_flag = ? and "
                    + "(to_char(term_from, 'YYYYMMDDHH24') <= ? or term_from is null) and "
                    + "(to_char(term_to, 'YYYYMMDDHH24') >= ? or term_to is null) and "
                    + "display_device in (?, ?) and "
                    + "condition_no = '0000' and "
                    + "display_contents_id in (";

                //�Q�|�S�|�Q�j�p�����[�^.�{������Tbl�̗v�f�����A
                //�ȉ��̏������J��Ԃ��B
                //�������������� += "?"
                int l_intBrowseHistorySize = l_htBrowseHistory.size();
                l_strQueryStringForDispContents += "?";

                //��2��ڂ����"?"�ł͂Ȃ��A", ?"��ǉ�����B
                for (int i = 1; i < l_intBrowseHistorySize; i++)
                {
                    //��2��ڂ����"?"�ł͂Ȃ��A", ?"��ǉ�����B
                    l_strQueryStringForDispContents += ", ?";
                }

                //�Q�|�S�|�R�j��������������Ɉȉ��̏�����ǉ�����B
                //�������������� += ")"
                l_strQueryStringForDispContents += ")";
            }
        }

        //�R�j��������������Ɉȉ��̏�����ǉ�����B
        //�������������� += ")"
        l_strQueryStringForDispContents += ")";

        //�S�j�쐬�������������������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strQueryStringForDispContents;
    }

    /**
     * (create���������f�[�^�R���e�ifor�\�����e)<BR>
     * ��������������́g?�h�����ɃZ�b�g����p�����[�^���X�g(������z��)��<BR>
     * �쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�ȉ��̏��ԂŃp�����[�^��ArrayList�ɒǉ�����B<BR>
     * �@@�@@���ȍ~�AArrayList�ɒǉ�����ۂɂ́AString�^�ɕϊ����Ă���ǉ�<BR>
     * ���邱�ƁB<BR>
     * �@@�@@�E�p�����[�^.�ڋq.�،���ЃR�[�h<BR>
     * �@@�@@�E"0�F�L��"<BR>
     * �@@�@@�E���ݎ���(*1)�@@���t�H�[�}�b�g�FyyyyMMddHHmm<BR> �@@�@@
     * �@@�@@�E���ݎ����@@���t�H�[�}�b�g�FyyyyMMddHHmm<BR>
     *     �E"0�FDEFAULT(PC&���o�C������)"<BR>
     * �@@�@@�E�\���}��<BR>
     * �@@�@@�@@�@@[this.get���O�C���`���l��()�̖߂�l == "3�F���o�C��"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@"2�F���o�C��"���Z�b�g�B<BR>
     * �@@�@@�@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@"1�FPC"���Z�b�g�B<BR>
     * �@@�@@�E�p�����[�^.�\�������ԍ��ꗗ�̗v�f�S��<BR>
     * <BR>
     * �R�jArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * (*1)���ݎ���<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * (������ԊǗ�.TIMESTAMP_TAG)�ɂĎ擾�������ݎ���<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     *
     * @@param l_strDispConditionNoList - (�\�������ԍ��ꗗ)<BR>
     * �\�������ԍ��ꗗ<BR>
     * <BR>
     * 0000�F�@@�_�C���N�g�w��<BR>
     * 1001�F�@@������������&�M�p�����J��<BR>
     * 1002�F�@@������������&�M�p�������J��<BR>
     * 1003�F�@@���֋�����<BR>
     * 1004�F�@@���֋�����<BR>
     * 1005�F�@@�؋����s��<BR>
     * 1006�F�@@���ϊ����ԋ߁i�ꃖ���O�j�̌��ʕۗL<BR>
     * 1007�F�@@���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL<BR>
     * 1008�F�@@�M�p�����J��<BR>
     * 1009�F�@@�M�p�������J��<BR>
     * 1010�F�@@�I�v�V���������J��<BR>
     * 1011�F�@@�����ۗL<BR>
     * 1012�F�@@�M�p���ʕۗL<BR>
     * 1013�F�@@���M�ۗL<BR>
     * 1014�F�@@�ݓ��ۗL<BR>
     * 1015�F�@@�I�v�V�������ʕۗL<BR>
     * 1016�F�@@�~�j���ۗL<BR>
     * 1017�F�@@�敨�ۗL<BR>
     * 1018�F�@@�a����L��&�،��c����<BR>
     * 1019�F�@@�a�������&�،��c����<BR>
     * 1020�F�@@�����E�M�p���������i�����j<BR>
     * 1021�F�@@�����E�M�p���������i�����j<BR>
     * 1022�F�@@�����E�M�p��蔭��<BR>
     * 1023�F�@@�S�ڋq<BR>
     * 1024�F�@@���[���A�h���X���o�^<BR>
     * 1025�F�@@IPO���I<BR>
     * 1026�F�@@IPO�J�グ���I<BR>
     * 1027�F�@@�����~<BR>
     * @@return String[]
     * @@roseuid 41459B280127
     */
    protected String[] createQueryDataContainerForDispContents(WEB3GentradeMainAccount l_mainAccount, String[] l_strDispConditionNoList)
    {

        final String STR_METHOD_NAME = " createQueryDataContainerForsDispContents(WEB3GentradeMainAccount, String[])";
        log.entering(STR_METHOD_NAME );

        //�P�jArrayList�𐶐�����B
        List l_lisArrayList = new ArrayList();

        //�ȉ��̏��ԂŃp�����[�^��ArrayList�ɒǉ�����B
        Timestamp l_tsNowDateTime = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_lisArrayList.add(l_mainAccount.getInstitution().getInstitutionCode());
        l_lisArrayList.add(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES);
        l_lisArrayList.add(WEB3DateUtility.formatDate(l_tsNowDateTime, "yyyyMMddHH"));
        l_lisArrayList.add(WEB3DateUtility.formatDate(l_tsNowDateTime, "yyyyMMddHH"));
        l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.DEFAULT_DEVICE);

        if (WEB3ChannelDef.MOBILE.equals(this.getLoginChannel()))
        {
            l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.MOBILE_DEVICE);
        }
        else
        {
            l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.PC_DEVICE);
        }
        int l_intDispConditionNoListCnt = l_strDispConditionNoList.length;
        for (int i = 0; i < l_intDispConditionNoListCnt; i++)
        {
            l_lisArrayList.add(l_strDispConditionNoList[i]);
        }

        //ArrayList.toArray()�̖߂�l��ԋp����B
        String[] l_strArrayLists;
        l_strArrayLists = new String[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_strArrayLists);

        log.exiting(STR_METHOD_NAME);
        return l_strArrayLists;
    }

    /**
     * (create���������f�[�^�R���e�ifor�\�����e)<BR>
     * ���������������"?"�����ɃZ�b�g����p�����[�^���X�g(������z��)���쐬����B <BR>
     * <BR>
     * �P�jArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j�ȉ��̏��ԂŃp�����[�^��ArrayList�ɒǉ�����B <BR>
     * �@@�@@���ȍ~�AArrayList�ɒǉ�����ۂɂ́AString�^�ɕϊ����Ă���ǉ����邱�ƁB <BR>
     * �@@�@@�E�p�����[�^.�ڋq.�،���ЃR�[�h <BR>
     * �@@�@@�E"0�F�L��" <BR>
     * �@@�@@�E���ݎ���(*1)�@@���t�H�[�}�b�g�FyyyyMMddHH<BR>
     * �@@�@@�E���ݎ����@@���t�H�[�}�b�g�FyyyyMMddHH <BR>
     * �@@�@@�E"0�FDEFAULT(PC&���o�C������)" <BR>
     * �@@�@@�E�\���}�� <BR>
     * �@@�@@�@@�@@[this.get���O�C���`���l��()�̖߂�l == "3�F���o�C��"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@"2�F���o�C��"���Z�b�g�B <BR>
     * �@@�@@�@@�@@[��L�ȊO�̏ꍇ] <BR>
     * �@@�@@�@@�@@�@@"1�FPC"���Z�b�g�B <BR>
     * �@@�@@�E�p�����[�^.�\�������ԍ��ꗗ���"0000�F�_�C���N�g�w��"���������v�f�S��<BR>
     * <BR>
     * �R�j�p�����[�^.�{������Tbl != null�̏ꍇ�A�ȉ��̏��ԂŃp�����[�^��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�E"0�F�L��" <BR>
     * �@@�@@�E���ݎ���(*1)�@@���t�H�[�}�b�g�FyyyyMMddHH<BR>
     * �@@�@@�E���ݎ����@@���t�H�[�}�b�g�FyyyyMMddHH <BR>
     * �@@�@@�E"0�FDEFAULT(PC&���o�C������)" <BR>
     * �@@�@@�E�\���}�� <BR>
     * �@@�@@�@@�@@[this.get���O�C���`���l��()�̖߂�l == "3�F���o�C��"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@"2�F���o�C��"���Z�b�g�B <BR>
     * �@@�@@�@@�@@[��L�ȊO�̏ꍇ] <BR>
     * �@@�@@�@@�@@�@@"1�FPC"���Z�b�g�B <BR>
     * �@@�@@�E�p�����[�^.�{������Tbl��key�S��<BR>
     * <BR>
     * �S�jArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * (*1)���ݎ���<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * �@@�@@������ԊǗ�.TIMESTAMP_TAG)�ɂĎ擾�������ݎ���<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strDispConditionNoList - (�\�������ԍ��ꗗ)<BR>
     * �\�������ԍ��ꗗ<BR>
     * <BR>
     * @@param l_htBrowseHistory - (�{������Tbl)<BR>
     * �{������Tbl<BR>
     * <BR>
     * @@return String[]
     */
    protected String[] createQueryDataContainerForDispContents(
        WEB3GentradeMainAccount l_mainAccount,
        String[] l_strDispConditionNoList,
        Hashtable l_htBrowseHistory)
    {

        final String STR_METHOD_NAME =
            " createQueryDataContainerForsDispContents(WEB3GentradeMainAccount, String[], Hashtable)";
        log.entering(STR_METHOD_NAME);
        //�P�jArrayList�𐶐�����B
        List l_lisArrayList = new ArrayList();

        //�Q�j�ȉ��̏��ԂŃp�����[�^��ArrayList�ɒǉ�����B
        //(*1)���ݎ���
        //ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TIMESTAMP_TAG)�ɂĎ擾�������ݎ���
        Timestamp l_tsNowDateTime =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        //�E�p�����[�^.�ڋq.�،���ЃR�[�h
        l_lisArrayList.add(l_mainAccount.getInstitution().getInstitutionCode());

        //�E"0�F�L��"
        l_lisArrayList.add(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES);

        //�E���ݎ���(*1)�@@���t�H�[�}�b�g�FyyyyMMddHH
        l_lisArrayList.add(WEB3DateUtility.formatDate(l_tsNowDateTime, "yyyyMMddHH"));

        //�E���ݎ����@@���t�H�[�}�b�g�FyyyyMMddHH
        l_lisArrayList.add(WEB3DateUtility.formatDate(l_tsNowDateTime, "yyyyMMddHH"));

        //�E"0�FDEFAULT(PC&���o�C������)"
        l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.DEFAULT_DEVICE);

        //[this.get���O�C���`���l��()�̖߂�l == "3�F���o�C��"�̏ꍇ]
        if (WEB3ChannelDef.MOBILE.equals(this.getLoginChannel()))
        {
            //"2�F���o�C��"���Z�b�g�B
            l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.MOBILE_DEVICE);
        }
        else
        {
            //"1�FPC"���Z�b�g�B
            l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.PC_DEVICE);
        }

        //�p�����[�^.�\�������ԍ��ꗗ���"0000�F�_�C���N�g�w��"���������v�f�S��
        int l_intDispConditionNoListCnt = l_strDispConditionNoList.length;
        for (int i = 0; i < l_intDispConditionNoListCnt; i++)
        {
            if (!WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_strDispConditionNoList[i]))
            {
                l_lisArrayList.add(l_strDispConditionNoList[i]);
            }
        }

        //�R�j�p�����[�^.�{������Tbl != null�̏ꍇ�A�ȉ��̏��ԂŃp�����[�^��ArrayList�ɒǉ�����B
        if (l_htBrowseHistory != null)
        {
            //�E"0�F�L��"
            l_lisArrayList.add(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES);

            //�E���ݎ���(*1)�@@���t�H�[�}�b�g�FyyyyMMddHH
            l_lisArrayList.add(WEB3DateUtility.formatDate(l_tsNowDateTime, "yyyyMMddHH"));

            //�E���ݎ����@@���t�H�[�}�b�g�FyyyyMMddHH
            l_lisArrayList.add(WEB3DateUtility.formatDate(l_tsNowDateTime, "yyyyMMddHH"));

            //�E"0�FDEFAULT(PC&���o�C������)"
            l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.DEFAULT_DEVICE);

            //[this.get���O�C���`���l��()�̖߂�l == "3�F���o�C��"�̏ꍇ]
            if (WEB3ChannelDef.MOBILE.equals(this.getLoginChannel()))
            {
                //"2�F���o�C��"���Z�b�g�B
                l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.MOBILE_DEVICE);
            }
            else
            {
                //"1�FPC"���Z�b�g�B
                l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.PC_DEVICE);
            }

            //�E�p�����[�^.�{������Tbl��key�S��
            Set l_setBrowseHistory = l_htBrowseHistory.keySet();
            Iterator l_iteratorBrowseHistory = l_setBrowseHistory.iterator();
            while (l_iteratorBrowseHistory.hasNext())
            {
                l_lisArrayList.add(l_iteratorBrowseHistory.next().toString());
            }
        }

        //ArrayList.toArray()�̖߂�l��ԋp����B
        String[] l_strArrayLists;
        l_strArrayLists = new String[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_strArrayLists);
        log.exiting(STR_METHOD_NAME);
        return l_strArrayLists;
    }

    /**
     * (sort�\�����b�Z�[�W���)<BR>
     * �����̕\�����b�Z�[�W���ꗗ���\�[�g����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�ȉ���Comparator�𐶐����A�ォ�珇��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�E���Ǌ���Comparator<BR>
     * �@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@orderBy�F�@@"A�F����"<BR>
     * �@@�@@�E�o�^���@@Comparator<BR>
     * �@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@orderBy�F�@@"A�F����"<BR>
     * �@@�@@�E�ŏI�X�V����Comparator<BR>
     * �@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@orderBy�F�@@"D�F�~��"<BR>
     * �@@�@@�E�\������ToComparator<BR>
     * �@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@orderBy�F�@@"D�F�~��"<BR>
     * �@@�@@�E�\�����b�Z�[�W������Comparator<BR>
     * �@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@orderBy�F�@@"A�F����"<BR>
     * <BR>
     * �R�jWEB3ArraysUtility.sort()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[sort()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@obj�F�@@�p�����[�^.�\�����b�Z�[�W���ꗗ<BR>
     * �@@�@@com�F�@@��������ArrayList.toArray()�̖߂�l<BR>
     * @@param l_dispMessageUnitList - (�\�����b�Z�[�W���ꗗ)<BR>
     * �\�����b�Z�[�W���̔z��<BR>
     * @@roseuid 414675BC0273
     */
    protected void sortDispMessageUnit(WEB3PvInfoDisplayMessageUnit[] l_dispMessageUnitList)
    {

        final String STR_METHOD_NAME = " sortDispMessageUnit(WEB3PvInfoDisplayMessageUnit[])";
        log.entering(STR_METHOD_NAME );

        //�P�jArrayList�𐶐�����B
        List l_lisArrayList = new ArrayList();

        // �Q�jComparator�𐶐����A�ォ�珇��ArrayList�ɒǉ�����B
        // ���Ǌ���Comparator
        WEB3PvInfoReadUnReadComparator l_readUnReadComparator = new WEB3PvInfoReadUnReadComparator(WEB3AscDescDef.ASC);
        l_lisArrayList.add(l_readUnReadComparator);

        //�o�^���@@Comparator
        WEB3PvInfoRegistMethodComparator l_registmehtodComparator = new WEB3PvInfoRegistMethodComparator(WEB3AscDescDef.ASC);
        l_lisArrayList.add(l_registmehtodComparator);

        //�ŏI�X�V����Comparator
        WEB3PvInfoFinalModTimeStampComparator l_lastUpdateTimeStampComparator = new WEB3PvInfoFinalModTimeStampComparator(WEB3AscDescDef.DESC);
        l_lisArrayList.add(l_lastUpdateTimeStampComparator);

        //�\������ToComparator
        WEB3PvInfoDisplayTermToComparator l_displayTermToComparator = new WEB3PvInfoDisplayTermToComparator(WEB3AscDescDef.DESC);
        l_lisArrayList.add(l_displayTermToComparator);

        //�\�����b�Z�[�W������Comparator
        WEB3PvInfoDisplayMessageAccrualDateComparator l_dispMessageAccrualDateComparator = new WEB3PvInfoDisplayMessageAccrualDateComparator(WEB3AscDescDef.ASC);
        l_lisArrayList.add(l_dispMessageAccrualDateComparator);

        Comparator[] l_cmpArray = new Comparator[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_cmpArray);

        //�R�jWEB3ArraysUtility.sort()���\�b�h���R�[������B
        WEB3ArraysUtility.sort(l_dispMessageUnitList, l_cmpArray);

        log.exiting(STR_METHOD_NAME );

    }

    /**
     * (is������������)<BR>
     * �����������������Ă���ڋq�����ʂ���B<BR>
     * �������Ă���ꍇ��true�A�ȊOfalse��ԋp����B<BR>
     * <BR>
     * �P�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.get��������������()<BR>
     * �@@���R�[������B<BR>
     * <BR>
     * �@@[get��������������()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * <BR>
     * �Q�j�P�j�̖߂�l == null�̏ꍇ�Afalse��ԋp����B<BR>
     * �@@�ȊO�Atrue��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@return boolean<BR>
     * @@roseuid 41457A9803D7
     */
    protected boolean isDepositRequestGen(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " isDepositRequestGen(WEB3PvInfoDisplayMessageUnit[])";
        log.entering(STR_METHOD_NAME );
        //�P�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.get��������������()
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        Date l_genDate = l_dataManager.getPayClaimGenDate(l_subAccount);

        //�Q�j�P�j�̖߂�l == null�̏ꍇ�Afalse��ԋp����B�ȊO�Atrue��ԋp����B
        log.exiting(STR_METHOD_NAME );
        if (l_genDate == null)
        {
            return false;
        }
        log.exiting(STR_METHOD_NAME );
        return true;
    }

    /**
     * (is�����~�ڋq)<BR>
     * �����~�ڋq�����ʂ���B<BR>
     * �����~�ڋq�̏ꍇ��true�A�ȊOfalse��ԋp����B<BR>
     * <BR>
     * �P�jGtlUtils.getCommonOrderValidator()���\�b�h���R�[�����A<BR>
     * �@@�����`�F�b�N�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�����`�F�b�N.validate����\�ڋq()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[validate����\�ڋq()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * <BR>
     * �R�j�P�j�̌��ʁA��O���X���[���ꂽ�ꍇ��true��ԋp����B<BR>
     * �@@�ȊO�Afalse��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@return boolean
     * @@roseuid 414688980012
     */
    protected boolean isSuspensionAccount(WEB3GentradeSubAccount l_subAccount)
    {
        final String STR_METHOD_NAME = " isSuspensionAccount(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME );

        //�P�jGtlUtils.getCommonOrderValidator()���\�b�h���R�[�����A�����`�F�b�N�I�u�W�F�N�g���擾����B
        CommonOrderValidator l_commonOrderValidator = GtlUtils.getCommonOrderValidator();

        //�Q�j�����`�F�b�N.validate����\�ڋq()���\�b�h���R�[������B
        OrderValidationResult l_orderValidationResult = l_commonOrderValidator.validateSubAccountForTrading(l_subAccount);

        //�R�j�P�j�̌��ʁA��O���X���[���ꂽ�ꍇ��true��ԋp����B
        if (l_orderValidationResult.getProcessingResult().isSuccessfulResult())
        {
            log.exiting(STR_METHOD_NAME );
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME );
            return true;
        }

    }

    /**
     * (is�؋����s��)<BR>
     * �؋������s�����Ă��邩���ʂ���B<BR>
     * �s�����Ă���ꍇ��true�A�ȊOfalse��ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.�ڋq.getSubAccount()���R�[������B<BR>
     * <BR>
     *     [getSubAccount()�ɃZ�b�g����p�����[�^]<BR>
     *     �⏕�����^�C�v�FSubAccountTypeEnum.�����I�v�V������������i�敨�؋����j<BR>
     * <BR>
     *     �擾�o���Ȃ������ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �Q�jWEB3IfoDepositCalcService�𐶐�����B<BR>
     * <BR>
     * �R�jWEB3IfoDepositCalcService.getIfoDepositCalc()���R�[������<BR>
     * <BR>
     *     [getIfoDepositCalc()�ɃZ�b�g����p�����[�^]<BR>
     *     �⏕�����F��L�P�j�Ŏ擾�����⏕����<BR>
     * <BR>
     * �S�j��L�R�j�Ŏ擾�����؋����v�Z.calcNonPayAmount()���R�[������<BR>
     * <BR>
     *     ���\�b�h�̖߂�l > 0�̏ꍇ��true�A�ȊOfalse��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return boolean
     * @@roseuid 4146AE5801F6
     */
    protected boolean isIfoDepositShortage(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " isIfoDepositShortage(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME );

        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^Null�o���Ȃ�");
        }
        
        //�P�j�p�����[�^.�ڋq.getSubAccount()���R�[������B
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = 
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�jWEB3IfoDepositCalcService�𐶐�����B
        WEB3IfoDepositCalcService l_service = 
            (WEB3IfoDepositCalcService)Services.getService(WEB3IfoDepositCalcService.class);
        
        //�R�jWEB3IfoDepositCalcService.getIfoDepositCalc()���R�[������
        WEB3IfoDepositCalc l_calc = l_service.getIfoDepositCalc(l_subAccount);
        
        //�S�j��L�R�j�Ŏ擾�����؋����v�Z.calcNonPayAmount()���R�[������
        double l_dblAmount = l_calc.calcNonPayAmount();
        
        if (l_dblAmount > 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is�a����c���L��)<BR>
     * �ڋq�̗a����c��(T+0)�����݂���(�v���X�ł���)�����ʂ���B<BR>
     * ���݂���ꍇ��true�A�ȊOfalse��ԋp����B<BR>
     * �P�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.get���Y�]�͏��()<BR>
     * �@@���R�[������B<BR>
     * �@@[get���Y�]�͏��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �Q�j�p�����[�^.is�M�p�����J�݂��A<BR>
     * �@@[false�̏ꍇ]<BR>
     * �@@�@@�@@�P�j�̖߂�l�����Y�]�͏��<�����ڋq>�^�ɃL���X�g����B<BR>
     * �@@�@@�A���Y�]�͏��<�����ڋq>.get�a����c��()���\�b�h���R�[������B<BR>
     * �@@�@@�@@[get�a����c��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�w����F�@@0<BR>
     * �@@�@@�B�A�̖߂�l > 0�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�P�j�̖߂�l�����Y�]�͏��<�M�p�ڋq>�^�ɃL���X�g����B<BR>
     * �@@�@@�A���Y�]�͏��<�M�p�ڋq>.get�a����c��()���\�b�h���R�[������B<BR>
     * �@@�@@�@@[get�a����c��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�w����F�@@0<BR>
     * �@@�@@�B�A�̖߂�l > 0�̏ꍇ�Atrue��ԋp����B<BR>
     * �S�jfalse��ԋp����B�@@���a����c�� <= 0�ł���ׁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_isOpenMarginAccount - (is�M�p�����J��)<BR>
     * �ڋq���M�p�������J�݂��Ă��邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@���J��<BR>
     * true�F�@@�J�ݍ�<BR>
     * @@return boolean<BR>
     * @@roseuid 415A643F0288
     */
    protected boolean isAccountBalanceHas(WEB3GentradeSubAccount l_subAccount, boolean l_isMarginAccountOpen) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " isAccountBalanceHas(WEB3GentradeSubAccount, boolean)";
        log.entering(STR_METHOD_NAME );
        //�P�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.get���Y�]�͏��()
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        Object l_objPowerInfo = l_dataManager.getTradingPowerInfo(l_subAccount);
        
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        boolean l_blnIsMarginAccount = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        // �Q�j�p�����[�^.is�M�p�����J�݂��A[false�̏ꍇ]
        if (!l_blnIsMarginAccount)
        {
            //�@@�P�j�̖߂�l�����Y�]�͏��<�����ڋq>�^�ɃL���X�g����B
            WEB3TPTradingPowerCalcEquity l_powerCalcEquity = (WEB3TPTradingPowerCalcEquity)l_objPowerInfo;
            //�A���Y�]�͏��<�����ڋq>.get�a����c��()���\�b�h���R�[������B
            double l_dblBalance = l_powerCalcEquity.getAccountBalance(0);
            //�B�A�̖߂�l > 0�̏ꍇ�Atrue��ԋp����B
            if (l_dblBalance > 0)
            { 
                return true;
            }
        }
        else
        {
            //�@@�P�j�̖߂�l�����Y�]�͏��<�M�p�ڋq>�^�ɃL���X�g����B
            WEB3TPTradingPowerCalcMargin l_powerCalcMargin = (WEB3TPTradingPowerCalcMargin) l_objPowerInfo;
            //�A���Y�]�͏��<�M�p�ڋq>.get�a����c��()���\�b�h���R�[������B
            double l_dblBalance = l_powerCalcMargin.getAccountBalance(0);
            //�B�A�̖߂�l > 0�̏ꍇ�Atrue��ԋp����B
            if (l_dblBalance > 0)
            { 
                return true;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (isIPO���I)<BR>
     * �ڋq��IPO�ɓ��I���Ă��邩���ʂ���B<BR>
     * ���I���Ă���ꍇ��true�A�ȊOfalse��ԋp����B<BR>
     * <BR>
     * �P�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.getIPO�\��Params()��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �@@[getIPO�\��Params()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�ڋq�F�@@�p�����[�^.�ڋq<BR>
     * <BR>
     * �@@���\�b�h�̖߂�l == null�̏ꍇ��false��ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�̃��\�b�h�̖߂�l�̗v�f��(IPO�\��Params)��<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�Q�|�P�j���I���Ă��邩�̃`�F�b�N<BR>
     * �@@�@@�@@�@@[�p�����[�^.is�J�グ���I == false�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�����Ώۂ�IPO�\��Params.���I���� != "1�F���I"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@���̗v�f�֏������ڍs����B(continue;)<BR>
     * <BR>
     * �@@�@@�@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�����Ώۂ�IPO�\��Params.���I����(�J��) != "1�F���I"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@���̗v�f�֏������ڍs����B(continue;)<BR>
     * <BR>
     * �@@�Q�|�Q�j�w���\���I�������ȓ��ł��邩�̃`�F�b�N<BR>
     * �@@�@@�@@�@@��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.getIPO����Params()��<BR>
     * �@@�@@�@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[getIPO����Params()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@IPO����ID�F�@@�����Ώۂ�IPO�\��Params.IPO����ID<BR>
     * <BR>
     * �@@�@@�@@�@@��L���\�b�h�̖߂�l != null�̏ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �R�jfalse��ԋp����B�@@�����I�f�[�^�����̈�<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_isAdvancedElection - (is�J�グ���I)<BR>
     * �J�グ���I���ǂ����𔻕ʂ���t���O<BR>
     * <BR>
     * false�F�@@�J�グ���I�łȂ�<BR>
     * true�F�@@�J�グ���I<BR>
     * @@return boolean
     * @@roseuid 415A58A70362
     */
    protected boolean isIpoElection(WEB3GentradeMainAccount l_mainAccount, boolean l_isAdvancedElection) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isIpoElection(WEB3GentradeMainAccount, boolean)";
        log.entering(STR_METHOD_NAME );

        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //�P�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.getIPO�\��Params()���R�[������B
        List l_lisIpoOrderParams = l_dataManager.getIpoOrderParams(l_mainAccount);
        if (l_lisIpoOrderParams == null)
        {
            log.info("IPO�\��Params��null");
            log.exiting(STR_METHOD_NAME );
            return false;
        }

        //�Q�j�P�j�̃��\�b�h�̖߂�l�̗v�f��(IPO�\��Params)���ȉ��̏������J��Ԃ��B
        int l_intIpoOrderParamsCnt = l_lisIpoOrderParams.size();
        log.debug("getIPO�\��Params�̖߂�l�̗v�f�� = " + l_intIpoOrderParamsCnt);
        for (int i = 0; i < l_intIpoOrderParamsCnt; i++)
        {
            //�Q�|�P�j���I���Ă��邩�̃`�F�b�N
            IpoOrderParams l_ipoOrderParams = (IpoOrderParams)l_lisIpoOrderParams.get(i);
            if (!l_isAdvancedElection)
            {
                if (!WEB3LotResultDef.ELECTION.equals(l_ipoOrderParams.lot_result))
                {
                    continue;
                }
            }
            else
            {
                if (!WEB3LotResultRetryDef.ELECTION.equals(l_ipoOrderParams.lot_result_retry))
                {
                    continue;
                }
            }

            //�Q�|�Q�j�w���\���I�������ȓ��ł��邩�̃`�F�b�N
            IpoProductParams l_ipoProductParams = l_dataManager.getIpoProductParams(l_ipoOrderParams.ipo_product_id, l_isAdvancedElection);
            if (l_ipoProductParams != null)
            {
                log.debug("IpoProductParams is not null.");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME );
        return false;
    }
    
    /**
     * (is���֋�����)<BR>
     * ���֋����������Ă���ڋq�����ʂ���B<BR>
     * �������Ă���ꍇ��true�A�ȊOfalse��ԋp����B<BR>
     * <BR>
     * �P�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.get���֋�������()<BR>
     * ���R�[������B<BR>
     * �@@[get���֋�������()�ɃZ�b�g����p�����[�^ <BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * <BR>
     * �Q�j�P�j�̖߂�l == null�̏ꍇ�Afalse��ԋp���� <BR>
     * �@@�ȊO�Atrue��ԋp����B<BR>
     * @@param l_subAccount<BR>
     * @@return<BR>
     */
    protected boolean isAdvanceGen(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " isAdvanceGen(WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME );
        //�P�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.get���֋�������()
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        Date l_datGenDate = l_dataManager.getAdvanceGenDate(l_subAccount);
        log.exiting(STR_METHOD_NAME);
        
        //�Q�j�P�j�̖߂�l == null�̏ꍇ�Afalse��ԋp����
        if (l_datGenDate == null)
        {
            log.exiting(STR_METHOD_NAME );
            return false;
        }
        
        log.exiting(STR_METHOD_NAME );
        //�ȊO�Atrue��ԋp����B
        return true;
    }
    
    /**
     * (is���֋�����)<BR>
     * ���֋����т�����ڋq�����ʂ���B<BR>
     * ���т�����ꍇ��true�A�ȊOfalse��ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.�⏕����.getMainAccount()���R�[�����A<BR>
     * �@@�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.get�]�͎����~�敪()<BR>
     * �@@���R�[������B<BR>
     * <BR>
     * �@@[get�]�͎����~�敪()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�ڋq�F�@@�P�j�Ŏ擾�����ڋq�I�u�W�F�N�g<BR>
     * <BR>
     * �R�j��L�Q�j�̖߂�l���A<BR>
     * �@@[ 1�̏ꍇ]<BR>
     * �@@�@@true��ԋp����B<BR>
     * <BR>
     * �@@[ 1�ȊO�̏ꍇ]<BR>
     * �@@�@@false��ԋp����B<BR>
     * @@param l_subAccount<BR>
     * @@return<BR>
     */
    protected boolean isAdvancePerformance(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " isAdvancePerformance(WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME );

        //�P�j�p�����[�^.�⏕����.getMainAccount()���R�[�����A
        //�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //�Q�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.get�]�͎����~�敪()
        //���R�[������B
        //get�]�͎����~�敪()�ɃZ�b�g����p�����[�^]
        //�ڋq�F�@@�P�j�Ŏ擾�����ڋq�I�u�W�F�N�g
        WEB3PvInfoDataManager l_dataManager =
            (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        String l_strTPTradingStop = l_dataManager.getTPTradingStop(l_account);

        //�R�j��L�Q�j�̖߂�l���A
        if (WEB3TPTradingStopDivDef.TRADING_STOP.equals(l_strTPTradingStop))
        {
            //[ 1�̏ꍇ]
            //true��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            //[ 1�ȊO�̏ꍇ]
            //false��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (get�{������ParamsTbl)<BR>
     * �Y���ڋq�̉{������row��key�i�\�����eID�j��<BR>
     * �t������Hashtable�ɂ��ĕԋp����B<BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0�F�@@�{������rowtype<BR>
     * �@@arg1�F�@@"institution_code=? and branch_code=? and account_code=?"<BR>
     * �@@arg2�F�@@null<BR>
     * �@@arg3�F�@@new Object[]<BR>
     * �@@arg4�F�@@{<BR>
     * �@@�@@�@@�@@�@@�@@�p�����[�^.�ڋq.getInstitution().getInstitutionCode(),<BR>
     * �@@�@@�@@�@@�@@�@@�p�����[�^.�ڋq.getBranch().getBranchCode(),<BR>
     * �@@�@@�@@�@@�@@�@@�p�����[�^.�ڋq.getAccountCode().substring(0, 6)<BR>
     * �@@�@@�@@�@@�@@}<BR>
     * <BR>
     * �@@�G���[�̏ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �Q�j��L�P�j�Ŏ擾�����{������row�̗v�f�����A�ȉ��̏������J��Ԃ�<BR>
     * <BR>
     * �@@�Q�|�P�j�{������row.getDisplayContentsId()�ƁA<BR>
     * �@@�@@�@@�@@�@@�{������row��<BR>
     * �@@�@@�@@�@@�@@Hashtable�ɒǉ�����<BR>
     * <BR>
     * �R�j��L�Q�j�ō쐬����Hashtable��ԋp����B<BR>  
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * @@return tbl <BR>
     * @@throws WEB3BaseException <BR>
     */
    public Hashtable getBrowseHistoryParamsTbl(
        WEB3GentradeMainAccount l_mainAccount
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getBrowseHistoryParams(WEB3GentradeMainAccount, long)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�ڋq���`�F�b�N����B
        if (l_mainAccount == null)
        {
            log.error("�p�����[�^.�ڋqNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�ڋqNull�o���Ȃ��B");
        }

        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //���ڋq�R�[�h�͐擪6byte�Ŕ�r���邱�ƁB
        String l_strAccountCode = l_mainAccount.getAccountCode().substring(0, 6);

        //�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B
        List list = null;
        try
        {
            list = findRowByInstitutionCodeBranchCodeAccountCode(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode
                );

        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        if (list.size() == 0)return null;

        Hashtable tbl = new Hashtable();
        for (int i = 0; i < list.size(); i++)
        {
            BrowseHistoryRow row = (BrowseHistoryRow) list.get(i);
            tbl.put(new Long(row.getDisplayContentsId()), row);
        }
        return tbl;
    }

    public List findRowByInstitutionCodeBranchCodeAccountCode(String p_institutionCode,
        String p_branchCode, String p_accountCode) throws DataNetworkException,
        DataFindException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BrowseHistoryRow.TYPE,
            "institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[]
            {p_institutionCode, p_branchCode, p_accountCode});
        return list;
    }


// *** ���捞No.016 START ***
    /**
     * (get�\�����eParams�ꗗ�|�\���Ώ�)<BR>
     * �\�����eParams�ꗗ��N���������ōi�荞�񂾌�A�X�ɕ\���Ώۂ�����<BR>
     * <BR> 
     * �P�j�\�����eParams�̗v�f�����ALoop����<BR>
     * <BR> 
     * �@@�i�P�jArrayList�𐶐�����B<BR>
     * <BR> 
     * �@@�i�Q�j�ȉ��̏����ɊY�����Ȃ��ꍇ�ALoop�����̐擪�ɖ߂�B<BR>
     * <BR>
     *      [����]<BR>
     * �@@   �\�����eParams.�\������From <= �V�X�e�����t ����<BR>
     * �@@   �\�����eParams.�\������To >= �V�X�e�����t<BR>
     *      ����r���x�͕��܂łƂ���B<BR>
     * <BR> 
     *   �i�R�jthis.is�\���Ώ۔���()���R�[�����߂�l��false�̏ꍇ�ALoop�����̐擪�ɖ߂�B<BR>
     * �@@�@@�@@[����]<BR>
     *�@@�@@   �ڋq�F�p�����[�^.�ڋq<BR>
     *�@@�@@   �⏕�����F�p�����[�^.�⏕����<BR>
     *�@@�@@   �\�����eParams�F�\�����eParams[Index]<BR>
     * <BR>  
     * �@@�i�S�j���ʋ��������̏d�����폜����<BR> 
     * �@@�@@�i�S�|�P�j�\�����eParams[Index].�\�������ԍ� = "1041" �̏ꍇ<BR> 
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1045")<BR> 
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1046")<BR> 
     * �@@�@@�@@�@@���R�[���������ꂩ�̖߂�l�� 0 �ȏ�̏ꍇ�AArrayList[�߂�l]�̃f�[�^���폜����<BR> 
     *<BR> 
     * �@@�@@�i�S�|�Q�j�\�����eParams[Index].�\�������ԍ� = "1042" �̏ꍇ<BR> 
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1047")<BR> 
     * �@@�@@�@@�@@���R�[�����߂�l�� 0 �ȏ�̏ꍇ�AArrayList[�߂�l]�̃f�[�^���폜����<BR> 
     * <BR> 
     * �@@�@@�i�S�|�R�j�\�����eParams[Index].�\�������ԍ� = "1043" �̏ꍇ<BR> 
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1045") <BR>
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1046") <BR>
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1047") <BR>
     * �@@�@@�@@�@@���R�[���������ꂩ�̖߂�l�� 0 �ȏ�̏ꍇ�AArrayList[�߂�l]�̃f�[�^���폜����<BR> 
     * <BR> 
     * �@@�@@�i�S�|�S�j�\�����eParams[Index].�\�������ԍ� = "1044" �̏ꍇ<BR> 
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1045") <BR>
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1046") <BR>
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1047") <BR>
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1048") <BR>
     * �@@�@@�@@�@@���R�[���������ꂩ�̖߂�l�� 0 �ȏ�̏ꍇ�AArrayList[�߂�l]�̃f�[�^���폜����<BR> 
     * <BR> 
     * �@@�@@�i�S�|�T�j�\�����eParams[Index].�\�������ԍ� = "1045" �̏ꍇ<BR> 
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1041") <BR>
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1043") <BR>
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1044") <BR>
     * �@@�@@�@@�@@���R�[���������ꂩ�̖߂�l�� 0 �ȏ�̏ꍇ�ALoop�����̐擪�ɖ߂�B<BR> 
     * <BR> 
     * �@@�@@�i�S�|�U�j�\�����eParams[Index].�\�������ԍ� = "1046" �̏ꍇ<BR> 
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1041") <BR>
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1043") <BR>
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1044") <BR>
     * �@@�@@�@@�@@���R�[���������ꂩ�̖߂�l�� 0 �ȏ�̏ꍇ�ALoop�����̐擪�ɖ߂�B<BR> 
     * <BR> 
     * �@@�@@�i�S�|�V�j�\�����eParams[Index].�\�������ԍ� = "1047" �̏ꍇ<BR> 
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1042") <BR> 
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1043") <BR>
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1044") <BR>
     * �@@�@@�@@�@@���R�[���������ꂩ�̖߂�l�� 0 �ȏ�̏ꍇ�ALoop�����̐擪�ɖ߂�B<BR> 
     * <BR> 
     * �@@�@@�i�S�|�W�j�\�����eParams[Index].�\�������ԍ� = "1048" �̏ꍇ<BR> 
     * �@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1044") <BR>
     * �@@�@@�@@�@@���R�[�����߂�l�� 0 �ȏ�̏ꍇ�ALoop�����̐擪�ɖ߂�B<BR> 
     * <BR> 
     * �@@�i�T�jArrayList�ɒǉ�����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_lisDisplayContens - (�\�����eParams�ꗗ)<BR>
     * @@return List<BR>
     * @@throws WEB3BaseException<BR> 
     */
    protected List getDisplayContensInDisplayTerm(
        WEB3GentradeMainAccount l_mainAccount,
        SubAccount l_subAccount,
        DisplayContentsParams[] l_lisDisplayContens)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = " getDisplayContensInDisplayTerm(WEB3GentradeMainAccount, " +
            "SubAccount, " +
            "DisplayContentsParams[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_lisDisplayContens == null || l_lisDisplayContens.length == 0)
        {
            return null;
        }
        
        ArrayList l_lisRet = new ArrayList();
        Timestamp l_tsSystemTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        
        for (int i = 0; i < l_lisDisplayContens.length; i++)
        {
            DisplayContentsParams l_displayContensParams =
                (DisplayContentsParams)l_lisDisplayContens[i];
            Timestamp l_tsTermFrom = l_displayContensParams.getTermFrom();
            Timestamp l_tsTermTo = l_displayContensParams.getTermTo();
            
            if (l_tsTermFrom != null && WEB3DateUtility.compareToMinute(l_tsSystemTimestamp, l_tsTermFrom) < 0)
            {
                continue;
            }
            if (l_tsTermTo != null && WEB3DateUtility.compareToMinute(l_tsSystemTimestamp, l_tsTermTo) > 0)
            {
                continue;
            }
            
            //this.is�\���Ώ۔���()���R�[�����߂�l��false�̏ꍇ�ALoop�����̐擪�ɖ߂�B
            if (!this.isDisplayObject(l_mainAccount, (WEB3GentradeSubAccount)l_subAccount, l_displayContensParams))
            {
                continue;
            }
            
            //�i�S�j���ʋ��������̏d�����폜���� 
            //�i�S�|�P�j�\�����eParams[Index].�\�������ԍ� = "1041" �̏ꍇ 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1045") 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1046") 
            //�@@�@@�@@�@@���R�[���������ꂩ�̖߂�l�� 0 �ȏ�̏ꍇ�AArrayList[�߂�l]�̃f�[�^���폜����             
            int l_intConditonNo1045 = 0;
            int l_intConditonNo1046 = 0;
            if (WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN.equals(
                l_displayContensParams.getConditionNo()))
            {
                l_intConditonNo1045 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_2TO4DAY);
                if (l_intConditonNo1045 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1045); 
                }
                
                l_intConditonNo1046 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_5DAY);                
                if (l_intConditonNo1046 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1046); 
                }
            }

            //�@@�@@�i�S�|�Q�j�\�����eParams[Index].�\�������ԍ� = "1042" �̏ꍇ 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1047") 
            //�@@�@@�@@�@@���R�[�����߂�l�� 0 �ȏ�̏ꍇ�AArrayList[�߂�l]�̃f�[�^���폜���� 
            int l_intConditonNo1047 = 0;
                
            if (WEB3PvInfoConditionDef.BREAK_1DAY_AND_6DAY.equals(
                l_displayContensParams.getConditionNo()))
            {
                l_intConditonNo1047 =
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_6DAY);
                if (l_intConditonNo1047 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1047);
                }
            }

            //�@@�@@�i�S�|�R�j�\�����eParams[Index].�\�������ԍ� = "1043" �̏ꍇ 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1045") 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1046") 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1047") 
            //�@@�@@�@@�@@���R�[���������ꂩ�̖߂�l�� 0 �ȏ�̏ꍇ�AArrayList[�߂�l]�̃f�[�^���폜���� 
            if (WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN.equals(
                l_displayContensParams.getConditionNo()))
            {
                l_intConditonNo1045 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_2TO4DAY);
                if (l_intConditonNo1045 >= 0) 
                {
                    l_lisRet.remove(l_intConditonNo1045);
                }
                
                l_intConditonNo1046 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_5DAY);                              
                if (l_intConditonNo1046 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1046); 
                }
                
                l_intConditonNo1047 =
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_6DAY);
                if (l_intConditonNo1047 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1047); 
                }
            }

            //�@@�@@�i�S�|�S�j�\�����eParams[Index].�\�������ԍ� = "1044" �̏ꍇ 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1045") 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1046") 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1047") 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1048") 
            //�@@�@@�@@�@@���R�[���������ꂩ�̖߂�l�� 0 �ȏ�̏ꍇ�AArrayList[�߂�l]�̃f�[�^���폜���� 
            int l_intConditonNo1048 = 0;                
            if (WEB3PvInfoConditionDef.BREAK_3DAY_OVER.equals(
                l_displayContensParams.getConditionNo()))
            {
                l_intConditonNo1045 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_2TO4DAY);
                if (l_intConditonNo1045 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1045);
                }
                
                l_intConditonNo1046 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_5DAY);                               
                if (l_intConditonNo1046 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1046);
                }
                
                l_intConditonNo1047 =
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_6DAY);
                if (l_intConditonNo1047 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1047);
                }
                
                l_intConditonNo1048 =
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_7DAY_OVER);
                if (l_intConditonNo1048 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1048);
                }
            }

            //�@@�@@�i�S�|�T�j�\�����eParams[Index].�\�������ԍ� = "1045" �̏ꍇ 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1041") 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1043") 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1044") 
            //�@@�@@�@@�@@���R�[���������ꂩ�̖߂�l�� 0 �ȏ�̏ꍇ�ALoop�����̐擪�ɖ߂�B 
            int l_intConditonNo1041 = 0; 
            int l_intConditonNo1043 = 0;
            int l_intConditonNo1044 = 0; 
            if (WEB3PvInfoConditionDef.BREAK_2TO4DAY.equals(
                l_displayContensParams.getConditionNo())) 
            {
                l_intConditonNo1041 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN);
                l_intConditonNo1043 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN);
                l_intConditonNo1044 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_3DAY_OVER);
                
                if (l_intConditonNo1041 >= 0 ||
                        l_intConditonNo1043 >= 0 ||
                        l_intConditonNo1044 >= 0)
                {
                    continue;
                }
            }
 
            //�@@�@@�i�S�|�U�j�\�����eParams[Index].�\�������ԍ� = "1046" �̏ꍇ 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1041") 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1043") 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1044") 
            //�@@�@@�@@�@@���R�[���������ꂩ�̖߂�l�� 0 �ȏ�̏ꍇ�ALoop�����̐擪�ɖ߂�B 
            if (WEB3PvInfoConditionDef.BREAK_5DAY.equals(
                l_displayContensParams.getConditionNo())) 
            {
                l_intConditonNo1041 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN);
                l_intConditonNo1043 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN);
                l_intConditonNo1044 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_3DAY_OVER);
                
                if (l_intConditonNo1041 >= 0 ||
                        l_intConditonNo1043 >= 0 ||
                        l_intConditonNo1044 >= 0)
                {
                    continue;
                }
            }

            //�@@�@@�i�S�|�V�j�\�����eParams[Index].�\�������ԍ� = "1047" �̏ꍇ 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1042") 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1043") 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1044") 
            //�@@�@@�@@�@@���R�[���������ꂩ�̖߂�l�� 0 �ȏ�̏ꍇ�ALoop�����̐擪�ɖ߂�B 
            int l_intConditonNo1042 = 0;
            if (WEB3PvInfoConditionDef.BREAK_6DAY.equals(
                l_displayContensParams.getConditionNo())) 
            {
                l_intConditonNo1042 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_1DAY_AND_6DAY);
                l_intConditonNo1043 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN);
                l_intConditonNo1044 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_3DAY_OVER);
                
                if (l_intConditonNo1042 >= 0 ||
                        l_intConditonNo1043 >= 0 ||
                        l_intConditonNo1044 >= 0)
                {
                    continue;
                }
            }

            //�@@�@@�i�S�|�W�j�\�����eParams[Index].�\�������ԍ� = "1048" �̏ꍇ 
            //�@@�@@�@@�@@this.get�w��\�������ԍ�(ArrayList, "1044") 
            //�@@�@@�@@�@@���R�[�����߂�l�� 0 �ȏ�̏ꍇ�ALoop�����̐擪�ɖ߂�B 
            if (WEB3PvInfoConditionDef.BREAK_7DAY_OVER.equals(
                l_displayContensParams.getConditionNo())) 
            {
                l_intConditonNo1044 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_3DAY_OVER);
                
                if (l_intConditonNo1044 >= 0)
                {
                    continue;
                }
            }

            l_lisRet.add(l_displayContensParams);
        }
        
        if (l_lisRet.size() == 0)
        {
            l_lisRet = null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisRet;
    }
// *** ���捞No.016 END ***

   /**
    * (is�p�X���[�h�ύX�v (�ڋq �ڋq))<BR>
    * �p�X���[�h�̕ύX���K�v�ȋq�����ʂ���B<BR> 
    * �ύX�v�̏ꍇ��true�A�ȊOfalse��ԋp����B<BR> 
    * <BR>
    * �P�j OpLoginAdminService�𐶐�����B<BR> 
    * <BR>
    * �Q�j �ڋq.get���O�C��ID()���R�[������<BR>
    * <BR>
    * �R�j OpLoginAdminService.getLoginInfo()���R�[������<BR> 
    * <BR>
    * [getLoginInfo()�ɃZ�b�g����p�����[�^]<BR> 
    * ���O�C��ID�F��L�Q�j�̖߂�l<BR>
    * <BR>
    * �S�j OpLoginAdminService.getLoginTypeAttributes()���R�[������<BR> 
    * <BR>
    * [getLoginTypeAttributes()�ɃZ�b�g����p�����[�^]<BR> 
    * ���O�C���^�C�vID�F��L�R�j�̖߂�l.getLoginTypeId()<BR>
    * <BR>
    * �T�j ���O�C���^�C�v����.get("�����Ԍo�ߌ�p�X���[�h�ύX���{�t���O")���R�[������B<BR> 
    * <BR>
    * �U�j ��L�T�j�̖߂�l��null�̏ꍇ�Afalse��ԋp����B<BR> 
    * <BR>
    * �V�j ��L�T�j�̖߂�l��"1�F�ύX�K�v"�̏ꍇ�Afalse��ԋp����B<BR> 
    * <BR>
    * �W�j ���O�C���^�C�v����.get("�p�X���[�h�L������")���R�[������B<BR> 
    * <BR>
    * �X�j ��L�W�j�̖߂�l��null�̏ꍇ�Afalse��ԋp����B<BR> 
    * <BR>
    * �P�O�j ��L�W�j�̖߂�l��"0"�̏ꍇ�Afalse��ԋp����B<BR> 
    * <BR>
    * �P�P�j ��L�W�j�̖߂�l����L�ȊO�̏ꍇ�A�ȉ��̏������s���B<BR>
    * <BR>
    * �P�P�|�P�j OpLoginAdminService.getLoginAttributes()���R�[������ <BR>
    * <BR>
    * [getLoginAttributes()�ɃZ�b�g����p�����[�^]<BR> 
    * ���O�C��ID�F��L�Q�j�̖߂�l <BR>
    * <BR>
    * �P�P�|�Q�j ���O�C������.get("�ŏI���O�C������")���R�[������B<BR> 
    * <BR>
    * �P�P�|�R�j ���񃍃O�C���i����L�P�P�|�Q�j�̖߂�l�������ɕϊ��o���Ȃ��j�̏ꍇ�Afalse��ԋp����B<BR> 
    * <BR>
    * �P�P�|�S�j ���O�C������.get("�O��p�X���[�h�ύX���t")���擾����B<BR> 
    * <BR>
    * �P�P�|�T�j ��L�P�P�|�S�j�̖߂�l��null�̏ꍇ�Afalse��ԋp����B<BR> 
    * <BR>
    * �P�P�|�U�j ��L�P�P�|�S�j�̖߂�l����L�ȊO�̏ꍇ�A�߂�l�ɏ�L�W�j�̒l�������Ƃ��ĉ��Z����B<BR> 
    * <BR>
    * �P�P�|�V�j ��L�P�P�|�U�j�̒l���A���ݓ����i��new Date()�j�����߂��Ă���ꍇ��true��ԋp����B<BR>
    * <BR>
    * �P�P�|�W�j ��L�P�P�|�V�j�łȂ��ꍇ�́Afalse��ԋp����B<BR>
    * @@param l_mainAccount - (�ڋq)<BR>
    * �ڋq�I�u�W�F�N�g<BR>
    * <BR>
    * @@return boolean
    */
   protected boolean isPasswordChange(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
   {
       final String STR_METHOD_NAME = " isPasswordChange(WEB3GentradeMainAccount)";
       log.entering(STR_METHOD_NAME );

       if (l_mainAccount == null)
       {
           log.debug("�ڋq�����w��ł��B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + STR_METHOD_NAME,
               "�ڋq�����w��ł��B");
       }

       // OpLoginAdminService�𐶐�����B
       OpLoginAdminService l_opLoginSec =
           (OpLoginAdminService) Services.getService(OpLoginAdminService.class);

       // �ڋq.get���O�C��ID()���R�[������
       long l_lngLoginId = l_mainAccount.getLoginId();

       // OpLoginAdminService.getLoginInfo()���R�[������
       // [getLoginInfo()�ɃZ�b�g����p�����[�^]
       // ���O�C��ID�F�ڋq.getLoginId()�̖߂�l
       LoginInfo l_loginInfo = l_opLoginSec.getLoginInfo(l_lngLoginId);
       if (l_loginInfo == null)
       {
           log.debug("LoginInfo�����݂��Ȃ��B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + STR_METHOD_NAME,
               "LoginInfo�����݂��Ȃ��B");
       }

       // OpLoginAdminService.getLoginTypeAttributes()���R�[������
       // [getLoginTypeAttributes()�ɃZ�b�g����p�����[�^]
       // ���O�C���^�C�vID�F���O�C�����.getLoginTypeId()�̖߂�l
       Map l_loginTypeAttributes = l_opLoginSec.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());

       // ���O�C���^�C�v����.get("�����Ԍo�ߌ�p�X���[�h�ύX���{�t���O")���R�[������B
       String l_strPWDChangeIntervalFlag =
           (String)l_loginTypeAttributes.get(WEB3LoginTypeAttributeKeyDef.PWDCHANGE_INTERVAL_FLAG);

       // �����Ԍo�ߌ�p�X���[�h�ύX���{�t���O��null�̏ꍇ�Afalse��ԋp����B
       if (l_strPWDChangeIntervalFlag == null)
       {
           log.exiting(STR_METHOD_NAME );
           return false;
       }

       // �����Ԍo�ߌ�p�X���[�h�ύX���{�t���O��"1�F�ύX�K�v"�̏ꍇ�Afalse��ԋp����B
       if (WEB3PwdChangeDef.REQUIRED.equals(l_strPWDChangeIntervalFlag))
       {
           log.exiting(STR_METHOD_NAME );
           return false;
       }

       // ���O�C���^�C�v����.get("�p�X���[�h�L������")���R�[������B
       Object l_objPWDInterval =
           l_loginTypeAttributes.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_INTERVAL);

       // �p�X���[�h�L�����Ԃ�null�̏ꍇ�Afalse��ԋp����B
       if (l_objPWDInterval == null)
       {
           log.exiting(STR_METHOD_NAME );
           return false;
       }
       int l_intPWDInterval = Integer.parseInt((String)l_objPWDInterval);
       // �p�X���[�h�L�����Ԃ�0�̏ꍇ�Afalse��ԋp����B
       if (l_intPWDInterval == 0)
       {
           log.exiting(STR_METHOD_NAME );
           return false;
       }

       // OpLoginAdminService.getLoginAttributes()���R�[������
       // [getLoginAttributes()�ɃZ�b�g����p�����[�^]
       // ���O�C��ID�F�ڋq.getLoginId()�̖߂�l
       Map l_loginAttributes = l_opLoginSec.getLoginAttributes(l_lngLoginId);

       // ���O�C������.get("�ŏI���O�C������")���R�[������B
       Object l_objLastLogin = l_loginAttributes.get(WEB3LoginAttributeKeyDef.LAST_LOGIN);

       // ���񃍃O�C���i���ŏI���O�C�������̒l�������ɕϊ��o���Ȃ��j�̏ꍇ�Afalse��ԋp����B
       Date l_datChange = WEB3DateUtility.getDate((String)l_objLastLogin, "yyyy.MM.dd HH:mm:ss");

       if (l_datChange == null)
       {
           log.exiting(STR_METHOD_NAME );
           return false;
       }

       // ���O�C������.get("�O��p�X���[�h�ύX���t")���擾����B
       Date l_datLastPWDChange =
           WEB3DateUtility.getDate(
               (String)l_loginAttributes.get(WEB3LoginAttributeKeyDef.LAST_PWDCHANGE),
               "yyyy.MM.dd HH:mm:ss");

       // �O��p�X���[�h�ύX���t��null�̏ꍇ�Afalse��ԋp����B
       if (l_datLastPWDChange == null)
       {
           log.exiting(STR_METHOD_NAME );
           return false;
       }

       // �O��p�X���[�h�ύX���t�̒l�ɁA�p�X���[�h�L�����Ԃ̒l�������Ƃ��ĉ��Z����B
       Calendar l_cal = new GregorianCalendar();
       l_cal.setTime(l_datLastPWDChange);
       l_cal.add(Calendar.MONTH, l_intPWDInterval);

       Date l_datAfterAdd = l_cal.getTime();

       // ���Z���ʂ��A���ݓ����i��new Date()�j�����߂��Ă���ꍇ��true��ԋp����B
       Date l_datnow = new Date();

       int l_intCompare =
           WEB3DateUtility.compareToSecond(l_datnow, l_datAfterAdd);
       if (l_intCompare > 0)
       {
           log.exiting(STR_METHOD_NAME );
           return true;
       }

       // ��L�ȊO�́Afalse��ԋp����B
       log.exiting(STR_METHOD_NAME );
       return false;
   }
   
   /**
    * (get�\�������ԍ��ꗗ�|�Ǘ��Ώ�)<BR>
    * ���Ǌ��ǊǗ��Ώۂ̕\�������ԍ��̈ꗗ��ԋp����B<BR>
    * <BR>
    * �ԋp����\�������ԍ��͈ȉ��̒ʂ�<BR>
    * �@@"1001"�i���������������M�p�����J�݁j<BR>
    * �@@"1002"�i���������������M�p�������J�݁j<BR>
    * �@@"1007"�i���ϊ����ԋ�(��T�ԑO)�̌��ʕۗL�j<BR>
    * �@@"1003"�i���֋������j<BR>
    * �@@"1005"�i�؋����s���j<BR>
    * �@@"1041"�i20������1����30������5���ȉ��j<BR> 
    * �@@"1042"�i20������1����30������6���j<BR> 
    * �@@"1043"�i20������2����30������6���ȉ��j<BR> 
    * �@@"1044"�i20������3���ȏ�j<BR> 
    * �@@"1045"�i30������2�`4���j<BR> 
    * �@@"1046"�i30������5���j<BR> 
    * �@@"1047"�i30������6���j<BR> 
    * �@@"1048"�i30������7���ȏ�j<BR>
    * �@@"1049"�i�ꕔ�o����~�j<BR>
    * �@@"1050"�i�S�z�o����~�j<BR>
    *   "1051"�i�萔�������L�����y�[���j<BR>
    * �@@"1054"�i�s�����������M�p�������J�݁j<BR>
    * �@@"1055"�i�s�����������M�p�����J�݁j<BR>
    * �@@"1056"�i��ꐅ���Ǐؔ����j<BR>
    * �@@"1057"�i��񐅏��Ǐؔ����j<BR>
    * �@@"0000"�i�_�C���N�g�w��j<BR>
    * @@return String[]<BR>
    */
   protected String[] getManagedDispCondNoList()
   {
       final String STR_METHOD_NAME = " getManagedDispCondNoList()";
       log.entering(STR_METHOD_NAME);
       
       List l_lisArrayLists = new ArrayList();
       //�ԋp����\�������ԍ��͈ȉ��̒ʂ�
       //"1001"�i���������������M�p�����J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN);
       //"1002"�i���������������M�p�������J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE);
       //"1007"�i���ϊ����ԋ�(��T�ԑO)�̌��ʕۗL�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS);
       //"1003"�i���֋������j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.ADVANCE_GENERATION);
       //"1005"�i�؋����s���j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE);
       //"1041"�i20������1����30������5���ȉ�)
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN);
       //�@@"1042"�i20������1����30������6���j 
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_1DAY_AND_6DAY);
       //�@@"1043"�i20������2����30������6���ȉ��j 
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN);
       //�@@"1044"�i20������3���ȏ�j 
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_3DAY_OVER);
       //�@@"1045"�i30������2�`4���j 
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_2TO4DAY);
       //�@@"1046"�i30������5���j 
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_5DAY);
       //�@@"1047"�i30������6���j 
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_6DAY);
       //�@@"1048"�i30������7���ȏ�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_7DAY_OVER);
       //"1049"�i�ꕔ�o����~�j 
       l_lisArrayLists.add(WEB3PvInfoConditionDef.PART_PAYMENT_STOP);
       //"1050"�i�S�z�o����~�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FULL_PAYMENT_STOP);
       //"1051"�i�萔�������L�����y�[���j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.COMMISSION_DISCOUNT_CAMPAIGN);
       //"1054"�i�s�����������M�p�������J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_CLOSE);
       //"1055"�i�s�����������M�p�����J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_OPEN);
       //"1056"�i��ꐅ���Ǐؔ����j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FIRST_ADDITIONAL_DEPOSIT_OCCUR);
       //"1057"�i��񐅏��Ǐؔ����j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.SECOND_ADDITIONAL_DEPOSIT_OCCUR);
       //"0000"�i�_�C���N�g�w��j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.DIRECT_ASSIGN);
       String[] l_strConLists = new String[l_lisArrayLists.size()];
       l_lisArrayLists.toArray(l_strConLists);
       
       log.exiting(STR_METHOD_NAME);
       return l_strConLists;
   }
   
   /**
    * (get�\�������ԍ��ꗗ�|�Ǘ��ΏۊO)<BR>
    * ���Ǌ��ǊǗ��ΏۊO�̕\�������ԍ��̈ꗗ��ԋp����B<BR>
    * <BR>
    * �ԋp����\�������ԍ��͈ȉ��̒ʂ�<BR>
    * �@@"1006"�i���ϊ����ԋ�(�ꃖ���O)�̌��ʕۗL�j<BR>
    * �@@"1027"�i�����~�j<BR>
    * �@@"1004"�i���֋����сj<BR>
    * �@@"1008"�i�M�p�����J�݁j<BR>
    * �@@"1009"�i�M�p�������J�݁j<BR>
    * �@@"1010"�i�I�v�V���������J�݁j<BR>
    * �@@"1029"�i�O���،������J�݁j<BR>
    * �@@"1011"�i�����ۗL�j<BR>
    * �@@"1012"�i�M�p���ʕۗL�j<BR>
    * �@@"1013"�i���M�ۗL�j<BR>
    * �@@"1014"�i�ݓ��ۗL�j<BR>
    * �@@"1015"�i�I�v�V�������ʕۗL�j<BR>
    * �@@"1016"�i�~�j���ۗL�j<BR>
    * �@@"1017"�i�敨�ۗL�j<BR>
    * �@@"1030"�i�O���ۗL�j<BR>
    * �@@"1018"�i�a����L�聕�،��c�����j<BR>
    * �@@"1019"�i�a����������،��c�����j<BR>
    * �@@"1020"�i�����E�M�p��������(����)�j<BR>
    * �@@"1021"�i�����E�M�p��������(����)�j<BR>
    * �@@"1022"�i�����E�M�p��蔭���j<BR>
    * �@@"1031"�i�O����������(����)�j<BR>
    * �@@"1032"�i�O����������(����)�j<BR>
    * �@@"1033"�i�O����蔭���j<BR>
    * �@@"1023"�i�S�ڋq�j<BR>
    * �@@"1024"�i���[���A�h���X���o�^�j<BR>
    * �@@"1025"�iIPO���I�j<BR>
    * �@@"1026"�iIPO�J�グ���I�j<BR>
    * �@@"1028"�i�p�X���[�h�ύX�v�j<BR>
    * �@@"1034"�i���o�C����p�����J�݁j<BR>
    * �@@"1035"�i���o�C����p�������J�݁j<BR>
    *�@@ "1036"�i�،��S�ۃ��[�������J�݁j<BR>
    * �@@"1037"�i���ʌ�t�����11�����o�߁j<BR>
    * �@@"1038"�iPTS�����J�݁j<BR>
    * �@@"1039"�iPTS�������J�݁j<BR>
    * �@@"1058"�iCFD�����J�݁j<BR>
    * �@@"1059"�iCFD�������J�݁j<BR>
    * @@return String[]<BR>
    */
   protected String[] getManagedOutDispCondNoList()
   {
       final String STR_METHOD_NAME = " getManagedOutDispCondNoList()";
       log.entering(STR_METHOD_NAME);
       
       List l_lisArrayLists = new ArrayList();
       //�ԋp����\�������ԍ��͈ȉ��̒ʂ�
       //"1006"�i���ϊ����ԋ�(�ꃖ���O)�̌��ʕۗL�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.SETTLE_BEF_AMONTH_CONTRACT_HAS);
       //"1027"�i�����~�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.TRADE_SUSPENSION);
       //"1004"�i���֋����сj
       l_lisArrayLists.add(WEB3PvInfoConditionDef.ADVANCE_RESULTS);
       //"1008"�i�M�p�����J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.MARGIN_ACCOUNT_OPEN);
       //"1009"�i�M�p�������J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.MARGIN_ACCOUNT_CLOSE);
       //"1010"�i�I�v�V���������J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.OPTION_ACCOUNT_OPEN);
       //"1029"�i�O���،������J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FEQ_ACCOUNT_OPEN);
       //"1011"�i�����ۗL�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.STOCKS_HAS);
       //"1012"�i�M�p���ʕۗL�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.MARGIN_CONTRACT_HAS);
       //"1013"�i���M�ۗL�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.MUTUAL_HAS);
       //"1014"�i�ݓ��ۗL�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.RUITO_HAS);
       //"1015"�i�I�v�V�������ʕۗL�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.OPTION_CONTRACT_HAS);
       //"1016"�i�~�j���ۗL�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.MINI_STOCK_HAS);
       //"1017"�i�敨�ۗL�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FUTURE_HAS);
       //"1030"�i�O���ۗL�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FEQ_HAS);
       //"1018"�i�a����L�聕�،��c�����j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.ACCOUNT_BAL_NON_SECURITIES_BAL);
       //"1019"�i�a����������،��c�����j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.NON_ACCOUNT_BAL_NON_SECURITIES_BAL);
       //"1020"�i�����E�M�p��������(����)�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.STOCK_MARGIN_ORDER_GENERATION_TODAY);
       //"1021"�i�����E�M�p��������(����)�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.STOCK_MARGIN_ORDER_GENERATION_NEXT_DAY);
       //"1022"�i�����E�M�p��蔭���j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.STOCK_MARGIN_EXECUTE_GENERATION);
       //"1031"�i�O����������(����)�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FEQ_ORDER_GENERATION_TODAY);
       //"1032"�i�O����������(����)�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FEQ_ORDER_GENERATION_NEXT_DAY);
       //"1033"�i�O����蔭���j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FEQ_EXECUTE_GENERATION);
       //"1023"�i�S�ڋq�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.ALL_ACCOUNT);
       //"1024"�i���[���A�h���X���o�^�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.MAIL_ADDRESS_NON_REGIST);
       //"1025"�iIPO���I�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.IPO_ELECTION);
       //"1026"�iIPO�J�グ���I�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.IPO_RETRY_ELECTION);
       //"1028"�i�p�X���[�h�ύX�v�j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.LOGIN_PASSWORD_CHANGE);
       //"1034"�i���o�C����p�����J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.ONLY_MOBILE_OPEN);
       //"1035"�i���o�C����p�������J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.ONLY_MOBILE_NOT_OPEN);
       //"1036"�i�،��S�ۃ��[�������J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.SECURED_LOAN_ACCOUNT_OPEN);
       //"1037"�i���ʌ�t�����11�����o�߁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FROM_DELIVERY_DATE_11MONTH);
       //"1038"�iPTS�����J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.PTS_ACCOUNT_OPEN);
       //"1039"�iPTS�������J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.PTS_ACCOUNT_CLOSE);
       //"1058"�iCFD�����J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.CFD_ACCOUNT_OPEN);
       //"1059"�iCFD�������J�݁j
       l_lisArrayLists.add(WEB3PvInfoConditionDef.CFD_ACCOUNT_CLOSE);
       String[] l_strConLists = new String[l_lisArrayLists.size()];
       l_lisArrayLists.toArray(l_strConLists);
       
       log.exiting(STR_METHOD_NAME);
       return l_strConLists;
   }
   
   /**
    * (is�\���Ώ۔���)<BR>
    * ����.�\�����eParams���A�\���Ώۂ����肷��B<BR>
    * �\���Ώۂł���ꍇtrue�A�ȊOfalse��ԋp����<BR>
    * <BR>
    * <BR>
    * �V�[�P���X�}<BR>
    * �u(��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X)is�\���Ώ۔���<BR>
    * @@param l_mainAccount - (�ڋq)<BR>
    * �ڋq�I�u�W�F�N�g<BR>
    * @@param l_subAccount - (�⏕����)<BR>
    * �⏕�����I�u�W�F�N�g<BR>
    * @@param l_displayContentsParams - (�\�����eParams)<BR>
    * �\�����eParams�I�u�W�F�N�g<BR>
    * @@return boolean<BR>
    * @@throws WEB3BaseException<BR>
    */
    protected boolean isDisplayObject(
        WEB3GentradeMainAccount l_mainAccount,
        WEB3GentradeSubAccount l_subAccount,
        DisplayContentsParams l_displayContentsParams)
        throws WEB3BaseException
   {
        final String STR_METHOD_NAME = 
            " isDisplayObject(WEB3GentradeMainAccount, " +
            "WEB3GentradeSubAccount, " +
            "DisplayContentsParams)";
        log.entering(STR_METHOD_NAME);
        
        if ((l_mainAccount == null) || (l_subAccount == null) || (l_displayContentsParams == null))
        {
            log.error("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        WEB3PvInfoDataManager l_pvInfoDataMgr = 
            (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        
        boolean l_blnMarginAccountEstablished = false;
        boolean l_blnIsDepositRequestGen = false;
        boolean l_blnIsAssetHasEquity = false;
        boolean l_blnIsAccountBalanceHas = false;
        boolean l_blnIsAdvanceGen = false;
        boolean l_blnIsAdvancePerformance = false;
        boolean l_blnIsIfoDepositShortage = false;
        boolean l_blnIsIfoAccountOpen = false;
        boolean l_blnIsForeignAccountOpen = false;
        boolean l_blnIsContractHasMargin = false;
        boolean l_blnIsAssetHasMutual = false;
        boolean l_blnIsAssetHasRuito = false;
        boolean l_blnIsContractHasOption = false;
        boolean l_blnIsAssetHasMini = false;
        boolean l_blnIsContractHasFuture = false;
        boolean l_blnIsAssetHasFeq = false;
        boolean l_blnIsIpoElection = false;
        boolean l_blnIsIpoElectionRetry = false;
        boolean l_blnIsSuspensionAccount = false;
        boolean l_blnIsPasswordChange = false;         
        List l_lisGetSettleContractLists = null;
        
        // create���������f�[�^�R���e�ifor��������(�ڋq)
        String[] l_strQueryDataContainerForOrderCnts =
            createQueryDataContainerForOrderCnt(l_mainAccount);
        // create��������������for��������()
        String l_strQueryStringForOrderCnt = createQueryStringForOrderCnt();
        String[] l_strProductDivDef = 
            {WEB3PvInfoProductDivDef.PD_SPOT, WEB3PvInfoProductDivDef.PD_CREDIT};
        int l_intProductDivDefLength = l_strProductDivDef.length;
        
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        
        boolean l_blnReturn = false;
        int l_intConditionNo = Integer.parseInt(l_displayContentsParams.condition_no);
        switch (l_intConditionNo)
        {
            // �\�������ԍ��F1001�i���������������M�p�����J�݁j
            case WEB3PvInfoConditionNoIntDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN:
                // is�M�p�����J��(�ٍϋ敪 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                // is������������(�⏕����)
                l_blnIsDepositRequestGen = isDepositRequestGen(l_subAccount);
                l_blnReturn = l_blnIsDepositRequestGen && l_blnMarginAccountEstablished;
                break;
            // �\�������ԍ��F1002�i���������������M�p�������J�݁j
            case WEB3PvInfoConditionNoIntDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE:
                // is�M�p�����J��(�ٍϋ敪 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                // is������������(�⏕����)
                l_blnIsDepositRequestGen = isDepositRequestGen(l_subAccount);
                l_blnReturn = l_blnIsDepositRequestGen && (!l_blnMarginAccountEstablished);
                break;
            // �\�������ԍ��F1006�i���ϊ����ԋ߁i�ꃖ���O�j�̌��ʕۗL�j
            case WEB3PvInfoConditionNoIntDef.SETTLE_BEF_AMONTH_CONTRACT_HAS:
                // is�M�p�����J��(�ٍϋ敪 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                if (l_blnMarginAccountEstablished)
                {
                    // get���ϊ����ԋߌ��ʈꗗ(�ڋq : �ڋq, is���ψ�T�ԑO : boolean)               
                    l_lisGetSettleContractLists = l_pvInfoDataMgr.getSettleContractList(l_mainAccount, false);
                    l_blnReturn = (l_blnMarginAccountEstablished) && (l_lisGetSettleContractLists != null);
                }
                break;
            // �\�������ԍ��F1007�i���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL
            case WEB3PvInfoConditionNoIntDef.SETTLE_BEF_AWEEK_CONTRACT_HAS:
                // is�M�p�����J��(�ٍϋ敪 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                if (l_blnMarginAccountEstablished)
                {
                    // get���ϊ����ԋߌ��ʈꗗ(�ڋq : �ڋq, is���ψ�T�ԑO : boolean)
                    l_lisGetSettleContractLists = l_pvInfoDataMgr.getSettleContractList(l_mainAccount, true);
                    l_blnReturn = (l_blnMarginAccountEstablished) && (l_lisGetSettleContractLists != null);
                }
                break;
            // �\�������ԍ��F1008�i�M�p�����J�݁j
            case WEB3PvInfoConditionNoIntDef.MARGIN_ACCOUNT_OPEN:
                // is�M�p�����J��(�ٍϋ敪 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                l_blnReturn = l_blnMarginAccountEstablished;
                break;
            // �\�������ԍ��F1009�i�M�p�������J�݁j
            case WEB3PvInfoConditionNoIntDef.MARGIN_ACCOUNT_CLOSE:
                // is�M�p�����J��(�ٍϋ敪 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                l_blnReturn = !l_blnMarginAccountEstablished;
                break;
            // �\�������ԍ��F1011�i�����ۗL�j
            case WEB3PvInfoConditionNoIntDef.STOCKS_HAS:
                // is���Y�ۗL(�ڋq : �ڋq, �����^�C�v : ProductTypeEnum, is�~�j�� : boolean)
                l_blnIsAssetHasEquity = l_pvInfoDataMgr.isAssetHas(l_mainAccount, ProductTypeEnum.EQUITY, false);
                l_blnReturn = l_blnIsAssetHasEquity;
                break;
            // �\�������ԍ��F1018�i�a����L�聕�،��c�����j
            case WEB3PvInfoConditionNoIntDef.ACCOUNT_BAL_NON_SECURITIES_BAL:
                // is���Y�ۗL(�ڋq : �ڋq, �����^�C�v : ProductTypeEnum, is�~�j�� : boolean)
                l_blnIsAssetHasEquity = l_pvInfoDataMgr.isAssetHas(l_mainAccount, ProductTypeEnum.EQUITY, false);
                // is�M�p�����J��(�ٍϋ敪 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                // is�a����c���L��(�⏕����, boolean)
                l_blnIsAccountBalanceHas = isAccountBalanceHas(l_subAccount, l_blnMarginAccountEstablished);
                l_blnReturn = l_blnIsAccountBalanceHas && (!l_blnIsAssetHasEquity);
                break;
            // �\�������ԍ��F1019�i�a����������،��c�����j
            case WEB3PvInfoConditionNoIntDef.NON_ACCOUNT_BAL_NON_SECURITIES_BAL:
                // is���Y�ۗL(�ڋq : �ڋq, �����^�C�v : ProductTypeEnum, is�~�j�� : boolean)
                l_blnIsAssetHasEquity = l_pvInfoDataMgr.isAssetHas(l_mainAccount, ProductTypeEnum.EQUITY, false);
                // is�M�p�����J��(�ٍϋ敪 : String)
                l_blnMarginAccountEstablished =
                     l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                // is�a����c���L��(�⏕����, boolean)
                l_blnIsAccountBalanceHas = isAccountBalanceHas(l_subAccount, l_blnMarginAccountEstablished);
                l_blnReturn = (!l_blnIsAccountBalanceHas) && (!l_blnIsAssetHasEquity);
                break;
            // �\�������ԍ��F1003�i���֋������j
            case WEB3PvInfoConditionNoIntDef.ADVANCE_GENERATION:
                // is���֋�����(�⏕����)
                l_blnIsAdvanceGen = isAdvanceGen(l_subAccount);
                l_blnReturn = l_blnIsAdvanceGen;
                break;
            // �\�������ԍ��F1004�i���֋����сj
            case WEB3PvInfoConditionNoIntDef.ADVANCE_RESULTS:
                // is���֋�����(�⏕����)
                l_blnIsAdvancePerformance = isAdvancePerformance(l_subAccount);
                l_blnReturn = l_blnIsAdvancePerformance;
                break;
            // �\�������ԍ��F1005�i�؋����s���j
            case WEB3PvInfoConditionNoIntDef.IFO_DEPOSIT_SHORTAGE:
                // is�؋����s��(�ڋq)
                l_blnIsIfoDepositShortage = isIfoDepositShortage(l_mainAccount);
                l_blnReturn = l_blnIsIfoDepositShortage;
                break;
            // �\�������ԍ��F1010�i�I�v�V���������J�݁j
            case WEB3PvInfoConditionNoIntDef.OPTION_ACCOUNT_OPEN:
                // is�敨OP�����J��(�敨�^�I�v�V�����敪 : String)
                l_blnIsIfoAccountOpen = l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION);
                l_blnReturn = l_blnIsIfoAccountOpen;
                break;
            // �\�������ԍ��F1029�i�O���،������J�݁j
            case WEB3PvInfoConditionNoIntDef.FEQ_ACCOUNT_OPEN:
                // is�O���،������J��()
                l_blnIsForeignAccountOpen = l_mainAccount.isForeignAccountOpen();
                l_blnReturn = l_blnIsForeignAccountOpen;
                break;
            // �\�������ԍ��F1012�i�M�p���ʕۗL�j
            case WEB3PvInfoConditionNoIntDef.MARGIN_CONTRACT_HAS:
                // is���ʕۗL(�ڋq : �ڋq, �����^�C�v : ProductTypeEnum, �敨�^�I�v�V�����敪 : String)
                l_blnIsContractHasMargin = 
                    l_pvInfoDataMgr.isContractHas(l_mainAccount, ProductTypeEnum.EQUITY, null);
                l_blnReturn = l_blnIsContractHasMargin;
                break;
            // �\�������ԍ��F1013�i���M�ۗL�j
            case WEB3PvInfoConditionNoIntDef.MUTUAL_HAS:
                // is���Y�ۗL(�ڋq : �ڋq, �����^�C�v : ProductTypeEnum, is�~�j�� : boolean)
                l_blnIsAssetHasMutual = l_pvInfoDataMgr.isAssetHas(l_mainAccount, ProductTypeEnum.MUTUAL_FUND, false);
                l_blnReturn = l_blnIsAssetHasMutual;
                break;
            // �\�������ԍ��F1014�i�ݓ��ۗL�j
            case WEB3PvInfoConditionNoIntDef.RUITO_HAS:
                // is���Y�ۗL(�ڋq : �ڋq, �����^�C�v : ProductTypeEnum, is�~�j�� : boolean)
                l_blnIsAssetHasRuito = l_pvInfoDataMgr.isAssetHas(l_mainAccount, ProductTypeEnum.RUITO, false);
                l_blnReturn = l_blnIsAssetHasRuito;
                break;
            // �\�������ԍ��F1015�i�I�v�V�������ʕۗL�j
            case WEB3PvInfoConditionNoIntDef.OPTION_CONTRACT_HAS:
                //1.10.1 is���ʕۗL(�ڋq : �ڋq, �����^�C�v : ProductTypeEnum, �敨�^�I�v�V�����敪 : String)
                l_blnIsContractHasOption = 
                    l_pvInfoDataMgr.isContractHas(l_mainAccount, ProductTypeEnum.IFO, WEB3FuturesOptionDivDef.OPTION);
                l_blnReturn = l_blnIsContractHasOption;
                break;
            // �\�������ԍ��F1016�i�~�j���ۗL�j
            case WEB3PvInfoConditionNoIntDef.MINI_STOCK_HAS:
                // is���Y�ۗL(�ڋq : �ڋq, �����^�C�v : ProductTypeEnum, is�~�j�� : boolean)
                l_blnIsAssetHasMini = l_pvInfoDataMgr.isAssetHas(l_mainAccount, ProductTypeEnum.EQUITY, true);
                l_blnReturn = l_blnIsAssetHasMini;
                break;
            // �\�������ԍ��F1017�i�敨�ۗL�j
            case WEB3PvInfoConditionNoIntDef.FUTURE_HAS:
                // is���ʕۗL(�ڋq : �ڋq, �����^�C�v : ProductTypeEnum, �敨�^�I�v�V�����敪 : String)
                l_blnIsContractHasFuture = 
                    l_pvInfoDataMgr.isContractHas(l_mainAccount, ProductTypeEnum.IFO, WEB3FuturesOptionDivDef.FUTURES);
                l_blnReturn = l_blnIsContractHasFuture;
                break;
            // �\�������ԍ��F1030�i�O���ۗL�j
            case WEB3PvInfoConditionNoIntDef.FEQ_HAS:
                // is���Y�ۗL(�ڋq : �ڋq, �����^�C�v : ProductTypeEnum, is�~�j�� : boolean)
                l_blnIsAssetHasFeq = l_pvInfoDataMgr.isAssetHas(l_mainAccount, ProductTypeEnum.FOREIGN_EQUITY, false);
                l_blnReturn = l_blnIsAssetHasFeq;
                break;
            // �\�������ԍ��F1020�i�����E�M�p���������i�����j�j
            case WEB3PvInfoConditionNoIntDef.STOCK_MARGIN_ORDER_GENERATION_TODAY:
                int l_intGetOrderCntTodayStockMargin = 0;
                // ���i�敪�̐���Loop����
                for (int i = 0; i < l_intProductDivDefLength; i++)
                {
                    // get��������(���i�敪 : String, is�������� : boolean,
                    //   �������������� : String, ���������f�[�^�R���e�i : String[])
                    l_intGetOrderCntTodayStockMargin = 
                        l_intGetOrderCntTodayStockMargin + l_pvInfoDataMgr.getOrderCnt(
                            l_strProductDivDef[i],
                            true,
                            l_strQueryStringForOrderCnt,
                            l_strQueryDataContainerForOrderCnts);
                }
                if (l_intGetOrderCntTodayStockMargin > 0)
                {
                    l_blnReturn = true;
                }
                break;
            // �\�������ԍ��F1021�i�����E�M�p���������i�����j�j
            case WEB3PvInfoConditionNoIntDef.STOCK_MARGIN_ORDER_GENERATION_NEXT_DAY:
                int l_intGetOrderCntNextDayStockMargin = 0;
                // ���i�敪�̐���Loop����
                for (int i = 0; i < l_intProductDivDefLength; i++)
                {
                    // get��������(���i�敪 : String, is�������� : boolean,
                    //   �������������� : String, ���������f�[�^�R���e�i : String[])
                    l_intGetOrderCntNextDayStockMargin = 
                        l_intGetOrderCntNextDayStockMargin + l_pvInfoDataMgr.getOrderCnt(
                            l_strProductDivDef[i],
                            false,
                            l_strQueryStringForOrderCnt,
                            l_strQueryDataContainerForOrderCnts);
                }
                if (l_intGetOrderCntNextDayStockMargin > 0)
                {
                    l_blnReturn = true;
                }
                break;
            // �\�������ԍ��F1022�i�����E�M�p��蔭���j
            case WEB3PvInfoConditionNoIntDef.STOCK_MARGIN_EXECUTE_GENERATION:
                int l_intGetExecuteCntStockMargin = 0;
                // ���i�敪�̐���Loop����
                for (int i = 0; i < l_intProductDivDefLength; i++)
                {
                    //
                    l_intGetExecuteCntStockMargin = 
                        l_intGetExecuteCntStockMargin +
                        l_pvInfoDataMgr.getExecuteCnt(l_mainAccount, l_strProductDivDef[i]);
                }
                if (l_intGetExecuteCntStockMargin > 0)
                {
                    l_blnReturn = true;
                }
                break;
            // �\�������ԍ��F1031�i�O�����������i�����j�j
            case WEB3PvInfoConditionNoIntDef.FEQ_ORDER_GENERATION_TODAY:
                int l_intGetOrderCntTodayFeq = 0;
                // get��������(���i�敪 : String, is�������� : boolean,
                //   �������������� : String, ���������f�[�^�R���e�i : String[])
                l_intGetOrderCntTodayFeq = l_pvInfoDataMgr.getOrderCnt(
                    WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY,
                    true,
                    l_strQueryStringForOrderCnt,
                    l_strQueryDataContainerForOrderCnts);
                if (l_intGetOrderCntTodayFeq > 0)
                {
                    l_blnReturn = true;
                }
                break;
            // �\�������ԍ��F1032�i�O�����������i�����j�j
            case WEB3PvInfoConditionNoIntDef.FEQ_ORDER_GENERATION_NEXT_DAY:
                int l_intGetOrderCntNextDayFeq = 0;
                // get��������(���i�敪 : String, is�������� : boolean,
                //   �������������� : String, ���������f�[�^�R���e�i : String[])
                l_intGetOrderCntNextDayFeq = l_pvInfoDataMgr.getOrderCnt(
                    WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY,
                    false,
                    l_strQueryStringForOrderCnt,
                    l_strQueryDataContainerForOrderCnts);
                if (l_intGetOrderCntNextDayFeq > 0)
                {
                    l_blnReturn = true;
                }
                break;
            // �\�������ԍ��F1033�i�O����蔭���j
            case WEB3PvInfoConditionNoIntDef.FEQ_EXECUTE_GENERATION:
                int l_intGetExecuteCntFeq = 0;
                // get��茏��(�ڋq : �ڋq, ���i�敪 : String)
                l_intGetExecuteCntFeq = l_pvInfoDataMgr.getExecuteCnt(l_mainAccount, WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY);
                if (l_intGetExecuteCntFeq > 0)
                {
                    l_blnReturn = true;
                }
                break;
            // �\�������ԍ��F1025�iIPO���I�j
            case WEB3PvInfoConditionNoIntDef.IPO_ELECTION:
                // isIPO���I(�ڋq, boolean)
                l_blnIsIpoElection = isIpoElection(l_mainAccount, false);
                l_blnReturn = l_blnIsIpoElection;
                break;
            // �\�������ԍ��F1026�iIPO�J�グ���I�j
            case WEB3PvInfoConditionNoIntDef.IPO_RETRY_ELECTION:
                //1.16.1 isIPO���I(�ڋq, boolean)
                l_blnIsIpoElectionRetry = isIpoElection(l_mainAccount, true);
                l_blnReturn = l_blnIsIpoElectionRetry;
                break;
            // �\�������ԍ��F1027�i�����~�j
            case WEB3PvInfoConditionNoIntDef.TRADE_SUSPENSION:
                // is�����~�ڋq(�⏕����)
                l_blnIsSuspensionAccount = isSuspensionAccount(l_subAccount);
                l_blnReturn = l_blnIsSuspensionAccount;
                break;
            // �\�������ԍ��F1028�i�p�X���[�h�ύX�v�j
            case WEB3PvInfoConditionNoIntDef.LOGIN_PASSWORD_CHANGE:
                //1.18.1 is�p�X���[�h�ύX�v(�ڋq)
                l_blnIsPasswordChange = isPasswordChange(l_mainAccount);
                l_blnReturn = l_blnIsPasswordChange;
                break;
            // �\�������ԍ��F1024�i���[���A�h���X���o�^�j
            case WEB3PvInfoConditionNoIntDef.MAIL_ADDRESS_NON_REGIST:
                String l_strEmailAddress = null;
                long l_lngInstitutionId = l_mainAccount.getInstitution().getInstitutionId();
                long l_lngBranchId = l_mainAccount.getBranch().getBranchId();
                String l_strAccountCode = l_mainAccount.getAccountCode();
                try
                {
                    l_strEmailAddress = WEB3GentradeMainAccount.getMainAccountRow(                    
                        l_lngInstitutionId, l_lngBranchId, l_strAccountCode).getEmailAddress();
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                if (l_strEmailAddress == null)
                {
                    l_blnReturn = true;
                }
                break;
                
            // �\�������ԍ��F1041�i20������1����30������5���ȉ��j
            case WEB3PvInfoConditionNoIntDef.BREAK_1DAY_AND_5DAY_DOWN:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //�\�������ԍ��F 1042�i20������1����30������6���j
            case WEB3PvInfoConditionNoIntDef.BREAK_1DAY_AND_6DAY:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //�\�������ԍ��F  1043�i20������2����30������6���ȉ��j 
            case WEB3PvInfoConditionNoIntDef.BREAK_2DAY_AND_6DAY_DOWN:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //�\�������ԍ��F 1044�i20������3���ȏ�j
            case WEB3PvInfoConditionNoIntDef.BREAK_3DAY_OVER:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //�\�������ԍ��F 1045�i30������2�`4���j
            case WEB3PvInfoConditionNoIntDef.BREAK_2TO4DAY:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //�\�������ԍ��F 1046�i30������5���j 
            case WEB3PvInfoConditionNoIntDef.BREAK_5DAY:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //�\�������ԍ��F 1047�i30������6���j 
            case WEB3PvInfoConditionNoIntDef.BREAK_6DAY:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //�\�������ԍ��F 1048�i30������7���ȏ�j
            case WEB3PvInfoConditionNoIntDef.BREAK_7DAY_OVER:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //�\�������ԍ��F 1049�i�ꕔ�o����~�j
            case WEB3PvInfoConditionNoIntDef.PART_PAYMENT_STOP:
                if (l_dataManager.isCashoutStop(l_mainAccount,WEB3PaymentStopDivDef.PART))
                {
                    l_blnReturn = true;
                }
                break;
                
            //�\�������ԍ��F 1050�i�S�z�o����~�j
            case WEB3PvInfoConditionNoIntDef.FULL_PAYMENT_STOP:
                if (l_dataManager.isCashoutStop(l_mainAccount,WEB3PaymentStopDivDef.FULL))
                {
                    l_blnReturn = true;
                }
                break; 

            //�\�������ԍ��F 1051�i�萔�������L�����y�[���j
            case WEB3PvInfoConditionNoIntDef.ACC_INFO_CAMPAIGN:
                if (l_dataManager.isAccInfoCampaign(l_mainAccount))
                {
                    l_blnReturn = true;
                }
                break; 

            //�\�������ԍ��F 1034�i���o�C����p�����J�݁j
            case WEB3PvInfoConditionNoIntDef.ONLY_MOBILE_OPEN:
                if (l_dataManager.isOnlyMobileOpen(l_mainAccount))
                {
                    l_blnReturn = true;
                }
                break;

            //�\�������ԍ��F 1035�i���o�C����p�������J�݁j
            case WEB3PvInfoConditionNoIntDef.ONLY_MOBILE_NOT_OPEN:
                if (!l_dataManager.isOnlyMobileOpen(l_mainAccount))
                {
                    l_blnReturn = true;
                }
                break;

            //�\�������ԍ��F 1036�i�،��S�ۃ��[�������J�݁j
            case WEB3PvInfoConditionNoIntDef.SECURED_LOAN_ACCOUNT_OPEN:
                if (l_mainAccount.isSecuredLoanAccountOpen())
                {
                    l_blnReturn = true;
                }
                break;

            //�\�������ԍ��F 1037�i���ʌ�t�����11�����o�߁j
            case WEB3PvInfoConditionNoIntDef.FROM_DELIVERY_DATE_11MONTH:
                if (l_dataManager.isDeliveryDate(l_mainAccount))
                {
                    l_blnReturn = true;
                }
                break;

            //�\�������ԍ��F1038�iPTS�����J�݁j
            //isPTS�����J��() == true
            case WEB3PvInfoConditionNoIntDef.PTS_ACCOUNT_OPEN:
                if (l_mainAccount.isPTSAccountOpen())
                {
                    l_blnReturn = true;
                }
                break;

            //�\�������ԍ��F1039�iPTS�������J�݁j
            //isPTS�����J��() == false
            case WEB3PvInfoConditionNoIntDef.PTS_ACCOUNT_CLOSE:
                if (!l_mainAccount.isPTSAccountOpen())
                {
                    l_blnReturn = true;
                }
                break;

            //�\�������ԍ��F1054�i�s�����������M�p�������J�݁j
            case WEB3PvInfoConditionNoIntDef.SHORT_FALL_GENERATION_MARGIN_ACC_CLOSE:
            //get�s����������() == "1"
                if(WEB3TPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_EQUITY_ACCOUNT.equals(
                    l_pvInfoDataMgr.getShortfallGenerationStatus(l_mainAccount)))
                {
                    l_blnReturn = true;
                }
                break;

            //�\�������ԍ��F1055�i�s�����������M�p�����J�݁j
            case WEB3PvInfoConditionNoIntDef.SHORT_FALL_GENERATION_MARGIN_ACC_OPEN:
                //get�s����������() == "2"
                if(WEB3TPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_MARGIN_ACCOUNT.equals(
                    l_pvInfoDataMgr.getShortfallGenerationStatus(l_mainAccount)))
                {
                    l_blnReturn = true;
                }
                break;

            //�\�������ԍ��F1056�i��ꐅ���Ǐؔ����j
            case WEB3PvInfoConditionNoIntDef.FIRST_ADDITIONAL_DEPOSIT_OCCUR:
                //is�M�p�����J��(�ٍϋ敪 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                //is�M�p�����J��()==true�̏ꍇ
                if(l_blnMarginAccountEstablished)
                {
                    //get�Ǐؔ�����() == "1"
                    if(WEB3TPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_EQUITY_ACCOUNT.equals(
                        l_pvInfoDataMgr.getAdddepositGenerationStatus(l_mainAccount)))
                    {
                        l_blnReturn = true;
                    }
                }
                break;

            //�\�������ԍ��F1057�i��񐅏��Ǐؔ����j
            case WEB3PvInfoConditionNoIntDef.SECOND_ADDITIONAL_DEPOSIT_OCCUR:
                //is�M�p�����J��(�ٍϋ敪 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                //is�M�p�����J��()==true�̏ꍇ
                if(l_blnMarginAccountEstablished)
                {
                    //get�Ǐؔ�����() == "2"
                    if(WEB3TPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_MARGIN_ACCOUNT.equals(
                        l_pvInfoDataMgr.getAdddepositGenerationStatus(l_mainAccount)))
                    {
                        l_blnReturn = true;
                    }
                }
                break;

            //�\�������ԍ��F1058�iCFD�����J�݁j
            //isCFD�����J��() == true
            case WEB3PvInfoConditionNoIntDef.CFD_ACCOUNT_OPEN:
                if (l_mainAccount.isCFDAccountOpen())
                {
                    l_blnReturn = true;
                }
                break;

            //�\�������ԍ��F1059�iCFD�������J�݁j
            //isCFD�����J��() == false
            case WEB3PvInfoConditionNoIntDef.CFD_ACCOUNT_CLOSE:
                if (!l_mainAccount.isCFDAccountOpen())
                {
                    l_blnReturn = true;
                }
                break;

            // �\�������ԍ��F0000�i�_�C���N�g�w��j
            case WEB3PvInfoConditionNoIntDef.DIRECT_ASSIGN:
                l_blnReturn = true;
                break;
            // �\�������ԍ��F1023�i�S�ڋq�j
            case WEB3PvInfoConditionNoIntDef.ALL_ACCOUNT:
                l_blnReturn = true;
                break;
            // ��L�ȊO�͂Ȃɂ����Ȃ�
            default:
                break;
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnReturn;
   }
    
    /**
     * (get�w��\�������ԍ�)<BR>
     * �\�����eParams�ꗗ����w�肵���\�������ԍ��̗L����ԋp����<BR> 
     * <BR>
     * �P�j�\�����eParams�ꗗ�̗v�f�����ALoop����<BR> 
     * <BR>
     * �@@�i�P�j�\�����eParams�ꗗ[Index].�\�������ԍ� = �p�����[�^.�\�������ԍ� �̏ꍇ<BR> 
     * <BR>
     * �@@�@@�@@Index ��ԋp����<BR> 
     * <BR>
     * �Q�jLoop�����I����<BR> 
     * <BR>
     * �@@�@@�@@-1 ��ԋp����<BR>
     * <BR>
     * @@param l_lisDisplayContens - (�\�����eParams�ꗗ)
     * �\�����eParams�I�u�W�F�N�g���i�[�������X�g<BR>
     * @@param l_strConditionNo - (�\�������ԍ�)
     * �\�����eParams�ꗗ����T���o���������ڂ̕\�������ԍ�<BR>
     * @@return int
     */
    private int getAssignedConditonNo(ArrayList l_lisDisplayContens, String l_strConditionNo)
    {
        final String STR_METHOD_NAME = 
            "getAssignedConditonNo(ArrayList l_lisDisplayContens, String l_strConditionNo)";
        log.entering(STR_METHOD_NAME);
        
        for (int i = 0; i < l_lisDisplayContens.size(); i ++) 
        {
            //�\�����eParams�ꗗ[Index].�\�������ԍ� = �p�����[�^.�\�������ԍ� �̏ꍇ
            DisplayContentsRow l_displayContensRow =
                (DisplayContentsRow)l_lisDisplayContens.get(i);          
            if (l_strConditionNo.equals(l_displayContensRow.getConditionNo())) 
            {
                //Index ��ԋp����
                log.exiting(STR_METHOD_NAME);
                return i;
            }
        }
        
        //-1 ��ԋp����
        log.exiting(STR_METHOD_NAME);
        return -1;
    }
}@
