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
filename	WEB3AdminEquityForcedSettleOrderApproveServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�XImpl(WEB3AdminEquityForcedSettleOrderApproveServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 �����F (���u) �V�K�쐬 �d�l�ύX���f��No.129
Revision History : 2007/05/16 �����F (���u) ���f��152
Revision History : 2007/07/24 �����q (���u) �V�K�쐬�@@�d�l�ύX���f��No.159
Revision History : 2008/11/20 ���Q�� (���u) ���f��212
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.comm.client.CommunicationException;
import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.ServerConnector;
import com.fitechlabs.xtrade.kernel.comm.client.ServerException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.data.db.ServerConfigRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DaemonTriggerTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3ServerUrlAccessorDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityApproveTypeDef;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleTemporaryOrderUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveService;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.system.tune.affinity.message.WEB3AffinityDescendRequest;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�XImpl)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderApproveServiceImpl extends WEB3ClientRequestService
    implements WEB3AdminEquityForcedSettleOrderApproveService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveServiceImpl.class);

    /**
     * ServerAccessor�I�u�W�F�N�g <BR>
     * <BR>
     * ���F�^�񏳔F�������e�T�[�o�ɐU�蕪����B<BR>
     * ��������s���ɃZ�b�g�����B<BR>
     */
    private ServerAccessor accessor;

    /**
     * @@roseuid 462CA424017C
     */
    public WEB3AdminEquityForcedSettleOrderApproveServiceImpl()
    {

    }

    /**
     * �������ω��������F�^�񏳔F�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�������ω��������F�^�񏳔F�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.validate���F�^�񏳔F()���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^:�@@�p�����[�^.���N�G�X�g�f�[�^<BR>
     * <BR>
     * ���Ǘ��ҁE�������ω��������F�^�񏳔F�����N�����N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.run���F�^�񏳔F()���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^:�@@�p�����[�^.���N�G�X�g�f�[�^<BR>
     * <BR>
     * ���Ǘ��ҁE�������ω��������F�^�񏳔F�����X�e�[�^�X�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.validate�����X�e�[�^�X()���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^:�@@�p�����[�^.���N�G�X�g�f�[�^<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 460323DE036B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        //�Ǘ��ҁE�������ω��������F�^�񏳔F�m�F���N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminForcedSettleApproveConfirmRequest)
        {
            l_response = this.validateApprove((WEB3AdminForcedSettleApproveConfirmRequest)l_request);
        }
        //�Ǘ��ҁE�������ω��������F�^�񏳔F�����N�����N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminForcedSettleApproveRunRequest)
        {
            l_response = this.runApprove((WEB3AdminForcedSettleApproveRunRequest)l_request);
        }
        //�Ǘ��ҁE�������ω��������F�^�񏳔F�����X�e�[�^�X�m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminForcedSettleApproveStatusRequest)
        {
            l_response = this.validateStatus((WEB3AdminForcedSettleApproveStatusRequest)l_request);
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
     * (validate���F�^�񏳔F)<BR>
     * �������ω��������F�^�񏳔F�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҋ������ω��������F�^�񏳔F�T�[�r�X�jvalidate���F�^�񏳔F�v�Q��<BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :1.4�@@get�������ϒ����ꗗ(����ID�ꗗ �F<BR>
     * �@@�@@�@@�@@null���ԋp���ꂽ�ꍇ�A<BR>
     * �@@�@@�@@�@@�u�Y�����������݂��܂���B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException       <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_02086        <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :1.5.4�@@(*)�����Ώۃ`�F�b�N�F<BR>
     * �@@�@@�@@�@@(*)is���F�^�񏳔F�����Ώے���()�̖߂�l == false�̏ꍇ�A<BR>
     * �@@�@@�@@�@@���̗v�f�͏����ΏۊO�ł���ׁA���X�g���珜������B�i�ԋp���Ȃ��j<BR>
     * �@@�@@�@@�@@���擾�������R�[�h���S�ď����ΏۊO�������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�Y�����������݂��܂���B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException       <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_02086         <BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminForcedSettleApproveConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 460320D901C5
     */
    protected WEB3AdminForcedSettleApproveConfirmResponse validateApprove(
        WEB3AdminForcedSettleApproveConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateApprove(WEB3AdminForcedSettleApproveConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminForcedSettleApproveConfirmResponse l_response = null;

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate���F�^�񏳔F�\(�Ǘ���, WEB3GenRequest)
        this.validateApprovePossibility(l_admin, l_request);

        //get�������ϒ����ꗗ(String[], �������σ\�[�g�L�[[], Long, Long)
        AdminEqForcedSettleOrderRow[] l_forcedSettleOrders =
            WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                l_request.id,
                l_request.sortKeys,
                null,
                null);

        if (l_forcedSettleOrders == null)
        {
            log.debug("�Y�����������݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02086,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y�����������݂��܂���B");
        }

        int l_intLength = l_forcedSettleOrders.length;
        ArrayList l_lisForcedSettleOrderList = new ArrayList();

        //get�������ϒ����ꗗ()�̖߂�l�̗v�f�����ALoop����
        for (int i = 0; i < l_intLength; i++)
        {
            AdminEqForcedSettleOrderRow l_forcedSettleOrderRow = l_forcedSettleOrders[i];

            //get�،���ЃR�[�h( )
            String l_strInstitutionCode = l_admin.getInstitutionCode();

            //reset����J�����_�R���e�L�X�g(String, Long, Long, String)
            //   [����]
            //   �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
            //   ���XID�F�@@�����Ώۂ̗v�f.���XID
            //   �s��ID�F�@@�����Ώۂ̗v�f.�s��ID
            //   ��t���ԋ敪�F�@@"�����E�M�p"
            WEB3AdminPMEquityDataManager.resetTradingCalContext(
                l_strInstitutionCode,
                new Long(l_forcedSettleOrderRow.getBranchId()),
                new Long(l_forcedSettleOrderRow.getMarketId()),
                WEB3TradingTimeTypeDef.EQUITY);

            //is���F�^�񏳔F�����Ώے���(EqtypeOrderUnitRow)
            //[����]
            //�����P��Row�F�@@�����Ώۂ̗v�f.�����P��ID�ɊY�����钍���P��Row�I�u�W�F�N�g
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            EqTypeOrderUnit l_eqOrderUnit = null;
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            try
            {
                l_eqOrderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_forcedSettleOrderRow.getOrderUnitId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();
            boolean l_blnIsApproveProcessTargetOrder =
                WEB3AdminPMEquityDataManager.isApproveProcessTargetOrder(l_eqtypeOrderUnitRow);

            //is���F�^�񏳔F�����Ώے���()�̖߂�l == false�̏ꍇ
            //���̗v�f�͏����ΏۊO�ł���ׁA���X�g���珜������B�i�ԋp���Ȃ��j
            if (l_blnIsApproveProcessTargetOrder)
            {
                l_lisForcedSettleOrderList.add(l_forcedSettleOrders[i]);
            }
        }
        //���擾�������R�[�h���S�ď����ΏۊO�������ꍇ�A
        //�@@�u�Y�����������݂��܂���B�v�̗�O���X���[����B
        if (l_lisForcedSettleOrderList == null || l_lisForcedSettleOrderList.size() == 0)
        {
            log.debug("�Y�����������݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02086,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y�����������݂��܂���B");
        }

        int l_intSize = l_lisForcedSettleOrderList.size();
        AdminEqForcedSettleOrderRow[] l_newForcedSettleOrders =
            new AdminEqForcedSettleOrderRow[l_intSize];
        l_lisForcedSettleOrderList.toArray(l_newForcedSettleOrders);

        //create�������ϒ����Ɖ���ꗗ(�������ϒ���Row[], String)
        //�������ꗗ�F�@@�����ΏۂƔ��ʂ��ꂽ�������ϒ����̈ꗗ
        //���F�敪�F�@@���N�G�X�g�f�[�^.���F�敪
        WEB3AdminForcedSettleTemporaryOrderUnit[] l_forcedSettleTemporaryOrderUnitList =
            WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(
                l_newForcedSettleOrders,
                l_request.approveType);

        // createResponse( )
        l_response = (WEB3AdminForcedSettleApproveConfirmResponse)l_request.createResponse();

        //���ݎ���          ���@@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        //�������ω������ꗗ ���@@create�������ϒ����Ɖ���ꗗ()�̖߂�l
        l_response.forcedSettleTemporaryOrderList = l_forcedSettleTemporaryOrderUnitList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (run���F�^�񏳔F)<BR>
     * �������ω��������F�^�񏳔F�����N�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҋ������ω��������F�^�񏳔F�T�[�r�X�jrun���F�^�񏳔F�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F�����N�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminForcedSettleApproveLapseRunResponse
     * @@throws WEB3BaseException
     * @@roseuid 460320D901F4
     */
    protected WEB3AdminForcedSettleApproveRunResponse runApprove(
        WEB3AdminForcedSettleApproveRunRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "runApprove(WEB3AdminForcedSettleApproveRunRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate���F�^�񏳔F�\(�Ǘ���, WEB3GenRequest)
        this.validateApprovePossibility(l_admin, l_request);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        // delete�I�����C�����s����(String, String)
        //[����]
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        //���F�敪�F�@@���N�G�X�g�f�[�^.���F�敪
        this.deleteOnlineRunResult(l_strInstitutionCode, l_request.approveType);

        //get�f�[�����g���K�[�ꗗ(String)
        List l_lisDaemonTriggerUnits = this.getDaemonTriggerUnits(l_request.approveType);

        //getServerAccessor
        ServerAccessor l_serverAccessor = this.getServerAccessor();

        //get�f�[�����g���K�[�ꗗ()�̖߂�l�̗v�f�����ALoop����
        int l_intSize = l_lisDaemonTriggerUnits.size();
        try
        {
            for (int i = 0; i < l_intSize; i++)
            {
                DaemonTriggerRow l_row = (DaemonTriggerRow)l_lisDaemonTriggerUnits.get(i);
                //updateAP�ďo��(long)
                //[����]
                //�X���b�hNo�F�@@�����Ώۂ̗v�f.�X���b�h�ԍ�
                this.updateAPCalling(l_row.getThreadNo());

                //�Ǘ��ҁE�������ω��������F�^�񏳔F���C�����N�G�X�g( )
                WEB3AdminEquityForcedSettleOrderApproveMainRequest l_mainRequest =
                    new WEB3AdminEquityForcedSettleOrderApproveMainRequest();

                //�،���ЃR�[�h   ���@@get�،���ЃR�[�h()�̖߂�l
                l_mainRequest.institutionCode = l_strInstitutionCode;

                //�X���b�hNo        ���@@�����Ώۂ̗v�f.�X���b�h�ԍ�
                l_mainRequest.threadNo = new Long(l_row.getThreadNo());

                //From����ID  ���@@�����Ώۂ̗v�f.�ڋq�R�[�h�i���j
                l_mainRequest.rangeFrom = new Long(l_row.getRangeFrom());

                //To����ID        ���@@�����Ώۂ̗v�f.�ڋq�R�[�h�i���j
                l_mainRequest.rangeTo = new Long(l_row.getRangeTo());

                //���F�敪      ���@@���N�G�X�g�f�[�^.���F�敪
                l_mainRequest.approveType = l_request.approveType;

                //����ID�ꗗ    ���@@���N�G�X�g�f�[�^.ID
                l_mainRequest.orderIdList = l_request.id;

                //�Ǘ���ID    =     �擾�����Ǘ���.�Ǘ���ID
                l_mainRequest.administratorId = new Long(l_admin.getAdministratorId());

                //WEB3AffinityDescendRequest()
                WEB3AffinityDescendRequest l_descendRequest = new WEB3AffinityDescendRequest();
                l_descendRequest.account_id_range = l_row.getRangeFrom() + "," + l_row.getRangeTo();
                WEB3AdminEquityForcedSettleOrderApproveMainRequest[] l_mainRequests =
                    new WEB3AdminEquityForcedSettleOrderApproveMainRequest[1];
                l_mainRequests[0] = l_mainRequest;
                l_descendRequest.request = l_mainRequests;

                //doRequest(arg0 : Request)
                //[����]
                //arg0�F�@@�����������N�G�X�g�f�[�^�iWEB3AffinityDescendRequest�j
                l_serverAccessor.doRequest(l_descendRequest);
            }
        }
        catch (CommunicationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (ServerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // createResponse( )
        WEB3AdminForcedSettleApproveRunResponse l_response =
            (WEB3AdminForcedSettleApproveRunResponse)l_request.createResponse();

        //���ݎ���      ���@@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�����X�e�[�^�X)<BR>
     * �������ω��������F�^�񏳔F�̏����X�e�[�^�X���m�F����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҋ������ω��������F�^�񏳔F�T�[�r�X�jvalidate�����X�e�[�^�X�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F�����X�e�[�^�X�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminForcedSettleApproveLapseStatusResponse
     * @@throws WEB3BaseException
     * @@roseuid 460320D90203
     */
    protected WEB3AdminForcedSettleApproveStatusResponse validateStatus(
        WEB3AdminForcedSettleApproveStatusRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "runApprove(WEB3AdminForcedSettleApproveStatusRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���������i�������ρj
        //is�X�V�F�@@true�i�X�V����j
        l_admin.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_FORCED_SETTLE, true);

        //get�f�[�����g���K�[�ꗗ(String)
        List l_lisDaemonTriggerUnits = this.getDaemonTriggerUnits(l_request.approveType);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get�I�����C�����s���ʈꗗ(String, String)
        //[����]
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        //���F�敪�F�@@���N�G�X�g�f�[�^.���F�敪
        List l_lisOnlineRunResultUnits =
            this.getOnlineRunResultUnits(l_strInstitutionCode, l_request.approveType);

        //createResponse( )
        WEB3AdminForcedSettleApproveStatusResponse l_response =
            (WEB3AdminForcedSettleApproveStatusResponse)l_request.createResponse();

        //�����X�e�[�^�X   ���@@�ȉ��̕���ɂ��Z�b�g����B
        //�@@�@@"������"���Z�b�g�������
        //�@@�@@�E�I�����C�����s���ʃ��R�[�h���擾�ł��Ȃ������ꍇ
        //�@@�@@�E�擾�����f�[�����g���K�[���R�[�h�̌����ƁA�I�����C�����s���ʃ��R�[�h�̌������قȂ�ꍇ
        //�@@�@@�E�擾�����f�[�����g���K�[���R�[�h.������Ԃ�"������"��1���ł����݂���ꍇ
        //�@@�@@�E�擾�����I�����C�����s���ʃ��R�[�h.���s�X�e�[�^�X�敪��"������"��1���ł����݂���ꍇ
        //�@@�A"������"���Z�b�g�������
        //�@@�@@�E�擾�����S�Ẵf�[�����g���K�[���R�[�h.������� == "���ғ�"�@@����
        //�@@�@@�@@�擾�����S�ẴI�����C�����s���ʃ��R�[�h.���s�X�e�[�^�X�敪 == "������"
        //�@@�B"�G���["���Z�b�g�������
        //�@@�@@�@@�A�A�ȊO�̏ꍇ
        if (l_lisOnlineRunResultUnits == null || l_lisDaemonTriggerUnits.size() != l_lisOnlineRunResultUnits.size())
        {
            l_response.lapseStatus = WEB3RunStatusDivDef.DEALING;
        }
        else
        {
            int l_intSize = l_lisDaemonTriggerUnits.size();
            DaemonTriggerRow l_daemonTriggerRow = null;
            OnlineRunStatusRow l_onlineRunStatusRow = null;
            int l_intFlag = 0;
            for (int i = 0; i < l_intSize; i++)
            {
                l_daemonTriggerRow = (DaemonTriggerRow)l_lisDaemonTriggerUnits.get(i);
                l_onlineRunStatusRow = (OnlineRunStatusRow)l_lisOnlineRunResultUnits.get(i);
                //�@@�E�擾�����f�[�����g���K�[���R�[�h.������Ԃ�"������"��1���ł����݂���ꍇ
                //�@@�E�擾�����I�����C�����s���ʃ��R�[�h.���s�X�e�[�^�X�敪��"������"��1���ł����݂���ꍇ
                if (WEB3DaemonTriggerStatusDef.THREAD_PROCESSING.equals(l_daemonTriggerRow.getTriggerStatus())
                    || WEB3RunStatusDivDef.DEALING.equals(l_onlineRunStatusRow.getRunStatusDiv()))
                {
                    l_intFlag = 1;
                    break;
                }
                else if (!WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus())
                    || !WEB3RunStatusDivDef.DEALED.equals(l_onlineRunStatusRow.getRunStatusDiv()))
                {
                    l_intFlag = 2;
                }
            }
            //�@@"������"���Z�b�g�������
            if (l_intFlag == 1)
            {
                l_response.lapseStatus = WEB3RunStatusDivDef.DEALING;
            }
            //�@@�A"������"���Z�b�g�������
            //�@@�E�擾�����S�Ẵf�[�����g���K�[���R�[�h.������� == "���ғ�"�@@����
            //�@@�擾�����S�ẴI�����C�����s���ʃ��R�[�h.���s�X�e�[�^�X�敪 == "������"
            else if (l_intFlag == 0)
            {
                l_response.lapseStatus = WEB3RunStatusDivDef.DEALED;
            }
            //"�G���["���Z�b�g�������
            //�ȊO�̏ꍇ
            else if (l_intFlag == 2)
            {
                l_response.lapseStatus = WEB3RunStatusDivDef.ERROR;
            }
        }

        //���ݎ���      ���@@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate���F�^�񏳔F�\)<BR>
     * �������Ϗ��F�^�񏳔F���������s�\���ǂ����`�F�b�N����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҋ������ω��������F�^�񏳔F�T�[�r�X�jvalidate���F�^�񏳔F�\�v�Q�ƁB<BR>
     * <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :1.7.(*)��d�N���`�F�b�N<BR>
     * �@@�@@�@@�@@�ȉ��̏����ɊY�����Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�u�w��AP�N�����i��d�N���G���[�j�B�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@get�I�����C�����s���ʈꗗ()�̖߂�l == null<BR>
     * �@@�@@�@@�@@�Aget�I�����C�����s���ʈꗗ()�̖߂�l�̌��� ==<BR>
     * �@@�@@�@@�@@get�f�[�����g���K�[�ꗗ()�̖߂�l�̌��� ����<BR>
     * �@@�@@�@@�@@get�I�����C�����s���ʈꗗ()�̖߂�l�̊e�v�f��<BR>
     * �@@�@@�@@�@@���s�X�e�[�^�X�敪��"������"�����݂��Ȃ� ����<BR>
     * �@@�@@�@@�@@get�f�[�����g���K�[�ꗗ()�̖߂�l�̊e�v�f��<BR>
     * �@@�@@�@@�@@������Ԃ�"���ғ�"�B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException       <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_01992         <BR>
     * =============================================== <BR>
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 460320D90232
     */
    protected void validateApprovePossibility(WEB3Administrator l_admin, WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateApprovePossibility(WEB3Administrator, WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //(*)���N�G�X�g�̌^��instanceof�ɂĔ��ʂ��A
        //�@@�m�F���N�G�X�g or �����N�����N�G�X�g�ɃL���X�g����B
        //�@@�L���X�g�����e���N�G�X�g����ȉ��̍��ڂ��擾���A�ϐ��Ɋi�[���Ă������ƁB
        //�@@�@@�E���N�G�X�g.���F�敪
        WEB3AdminForcedSettleApproveConfirmRequest l_confirmRequest = null;
        WEB3AdminForcedSettleApproveRunRequest l_runRequest = null;
        String l_strApproveType = "";
        if (l_request instanceof WEB3AdminForcedSettleApproveConfirmRequest)
        {
            l_confirmRequest = (WEB3AdminForcedSettleApproveConfirmRequest)l_request;
            l_strApproveType = l_confirmRequest.approveType;
        }
        else if (l_request instanceof WEB3AdminForcedSettleApproveRunRequest)
        {
            l_runRequest = (WEB3AdminForcedSettleApproveRunRequest)l_request;
            l_strApproveType = l_runRequest.approveType;
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

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���������i�������ρj
        //is�X�V�F�@@true�i�X�V����j
        l_admin.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_FORCED_SETTLE, true);

        // (*)�����N�����N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminForcedSettleApproveRunRequest)
        {
            // validate����p�X���[�h(�p�X���[�h : String)
            //[����]  �p�X���[�h�F�@@�����N�����N�G�X�g.�Ïؔԍ�
            l_admin.validateTradingPassword(l_runRequest.password);
        }

        //get�f�[�����g���K�[�ꗗ(String)
        List l_lisDaemonTriggerUnits = this.getDaemonTriggerUnits(l_strApproveType);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get�I�����C�����s���ʈꗗ(String, String)
        //[����]
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        //���F�敪�F�@@�擾�������F�敪
        List l_lisOnlineRunResultUnits = this.getOnlineRunResultUnits(l_strInstitutionCode, l_strApproveType);

        //��d�N���`�F�b�N
        //�ȉ��̏����ɊY�����Ȃ��ꍇ�́A
        //�u�w��AP�N�����i��d�N���G���[�j�B�v�̗�O���X���[����B
        //�@@get�I�����C�����s���ʈꗗ()�̖߂�l == null
        //�Aget�I�����C�����s���ʈꗗ()�̖߂�l�̌��� ==
        //�@@get�f�[�����g���K�[�ꗗ()�̖߂�l�̌��� ����
        //�@@get�I�����C�����s���ʈꗗ()�̖߂�l�̊e�v�f��
        //�@@���s�X�e�[�^�X�敪��"������"�����݂��Ȃ� ����
        //�@@get�f�[�����g���K�[�ꗗ()�̖߂�l�̊e�v�f��
        //�@@������Ԃ�"���ғ�"�B
        if (l_lisOnlineRunResultUnits != null)
        {
            if (l_lisOnlineRunResultUnits.size() != l_lisDaemonTriggerUnits.size())
            {
                log.debug("�w��AP�N�����i��d�N���G���[�j�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w��AP�N�����i��d�N���G���[�j�B");
            }
            else
            {
                OnlineRunStatusRow l_onlineRunStatusRow = null;
                DaemonTriggerRow l_daemonTriggerRow = null;
                for (int i = 0; i < l_lisOnlineRunResultUnits.size(); i++)
                {
                    l_onlineRunStatusRow = (OnlineRunStatusRow)l_lisOnlineRunResultUnits.get(i);
                    l_daemonTriggerRow = (DaemonTriggerRow)l_lisDaemonTriggerUnits.get(i);
                    if (WEB3RunStatusDivDef.DEALING.equals(l_onlineRunStatusRow.getRunStatusDiv())
                        || !WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus()))
                    {
                        log.debug("�w��AP�N�����i��d�N���G���[�j�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "�w��AP�N�����i��d�N���G���[�j�B");
                    }
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�f�[�����g���K�[�ꗗ)<BR>
     * �����̏����ɊY������f�[�����g���K�[�e�[�u����<BR>
     * ���R�[�h��ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�ȉ��̏����Ńf�[�����g���K�[�e�[�u������������B<BR>
     * �@@[����]<BR>
     * �@@�@@[�p�����[�^.���F�敪 == "���F"�̏ꍇ]<BR>
     * �@@�@@�@@�����^�C�v = "�������ρi���F�j"<BR>
     * �@@�@@[�p�����[�^.���F�敪 == "�񏳔F"�̏ꍇ]<BR>
     * �@@�@@�@@�����^�C�v = "�������ρi�񏳔F�j"<BR>
     * <BR>
     * �@@[�\�[�g����]<BR>
     * �@@�@@�X���b�h�ԍ��@@����<BR>
     * <BR>
     * �@@�Y���f�[�^�Ȃ��̏ꍇ�A�u�Y���f�[�^�Ȃ��v��<BR>
     * �@@�V�X�e���G���[���X���[����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * @@param l_strApproveType - (���F�敪)<BR>
     * ���F�敪<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 460320D90251
     */
    protected List getDaemonTriggerUnits(String l_strApproveType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDaemonTriggerUnits(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@DB����
        //�@@�ȉ��̏����Ńf�[�����g���K�[�e�[�u������������B
        //�@@[����]
        //�@@�@@[�p�����[�^.���F�敪 == "���F"�̏ꍇ]
        //�@@�@@�@@�����^�C�v = "�������ρi���F�j"
        //�@@�@@[�p�����[�^.���F�敪 == "�񏳔F"�̏ꍇ]
        //�@@�@@�@@�����^�C�v = "�������ρi�񏳔F�j"
        String l_strTriggerType = null;
        if (WEB3AdminEquityApproveTypeDef.APPROVE.equals(l_strApproveType))
        {
            l_strTriggerType = WEB3DaemonTriggerTypeDef.FORCED_SETTLE_APPROVAL;
        }
        else if (WEB3AdminEquityApproveTypeDef.DISAPPROVE.equals(l_strApproveType))
        {
            l_strTriggerType = WEB3DaemonTriggerTypeDef.FORCED_SETTLE_UNAPPROVAL;
        }
        Object[] l_objValues = {l_strTriggerType};

        //�@@[�\�[�g����]
        //�@@�@@�X���b�h�ԍ��@@����
        String l_strCondition = "thread_no asc";

        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                DaemonTriggerRow.TYPE,
                " trigger_type = ?",
                l_strCondition,
                null,
                l_objValues);
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

        //�@@�Y���f�[�^�Ȃ��̏ꍇ�A�u�Y���f�[�^�Ȃ��v��
        //�@@�V�X�e���G���[���X���[����B
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }

    /**
     * (get�I�����C�����s���ʈꗗ)<BR>
     * �����̏����ɊY������I�����C�����s���ʃe�[�u����<BR>
     * ���R�[�h��ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�ȉ��̏����ŃI�����C�����s���ʃe�[�u������������B<BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�����^�C�v = "����"<BR>
     * �@@�@@�敨�^�I�v�V�����敪 = "DEFAULT"<BR>
     * �@@�@@[�p�����[�^.���F�敪 == "���F"�̏ꍇ]<BR>
     * �@@�@@�@@�I�����C���T�[�r�X�敪 = "�������ρi���F�j"<BR>
     * �@@�@@[�p�����[�^.���F�敪 == "�񏳔F"�̏ꍇ]<BR>
     * �@@�@@�@@�I�����C���T�[�r�X�敪 = "�������ρi�񏳔F�j"<BR>
     * <BR>
     * �@@�Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strApproveType - (���F�敪)<BR>
     * ���F�敪<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 460320D90271
     */
    protected List getOnlineRunResultUnits(String l_strInstitutionCode, String l_strApproveType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOnlineRunResultUnits(String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@DB����
        //�@@�ȉ��̏����ŃI�����C�����s���ʃe�[�u������������B
        //�@@[����]
        //�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
        //�@@�@@�����^�C�v = "����"
        //�@@�@@�敨�^�I�v�V�����敪 = "DEFAULT"
        //�@@�@@[�p�����[�^.���F�敪 == "���F"�̏ꍇ]
        //�@@�@@�@@�I�����C���T�[�r�X�敪 = "�������ρi���F�j"
        //�@@�@@[�p�����[�^.���F�敪 == "�񏳔F"�̏ꍇ]
        //�@@�@@�@@�I�����C���T�[�r�X�敪 = "�������ρi�񏳔F�j"
        String l_strWhere = " institution_code = ? and product_type = ? " +
            "and future_option_div = ? and online_service_div = ?";

        String l_strOnlineServiceDiv = null;
        if (WEB3AdminEquityApproveTypeDef.APPROVE.equals(l_strApproveType))
        {
            l_strOnlineServiceDiv = WEB3OnlineServiceDiv.FORCED_SETTLE_APPROVAL;
        }
        else if (WEB3AdminEquityApproveTypeDef.DISAPPROVE.equals(l_strApproveType))
        {
            l_strOnlineServiceDiv = WEB3OnlineServiceDiv.FORCED_SETTLE_UNAPPROVAL;
        }

        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(ProductTypeEnum.EQUITY);
        l_lisBindValues.add(WEB3FuturesOptionDivDef.DEFAULT);
        l_lisBindValues.add(l_strOnlineServiceDiv);
        Object[] l_bindValues = new Object[l_lisBindValues.size()];
        l_lisBindValues.toArray(l_bindValues);

        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                OnlineRunStatusRow.TYPE,
                l_strWhere,
                l_bindValues);
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
        //�@@�Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //�Q�j�@@�������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }

    /**
     * (delete�I�����C�����s����)<BR>
     * �����ɊY������I�����C�����s���ʃe�[�u����<BR>
     * ���R�[�h�𕨗��폜����B<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ɊY������I�����C�����s���ʃe�[�u����<BR>
     * �@@���R�[�h��delete����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�����^�C�v = "����"<BR>
     * �@@�@@�敨�^�I�v�V�����敪 = "DEFAULT"<BR>
     * �@@�@@�I�����C���T�[�r�X�敪 = <BR>
     * �@@�@@�@@[�p�����[�^.���F�敪 == "���F"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"�������ρi���F�j"<BR>
     * �@@�@@�@@[�p�����[�^.���F�敪 == "�񏳔F"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"�������ρi�񏳔F�j"<BR>
     * <BR>
     * �@@���폜�Ώۂ̃��R�[�h���Ȃ��Ă���O�Ƃ��Ȃ����ƁB<BR>
     * �@@���{�����͐V�K�g�����U�N�V�����ŏ������s���A<BR>
     * �@@�@@�����������ɍX�V�����f�����悤�ɂ��邱�ƁB<BR>
     * �@@�@@�i�Q�l�FWEB3GentradeDaemonTriggerManager.startThread()�j<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strApproveType - (���F�敪)<BR>
     * ���F�敪<BR>
     * @@throws WEB3BaseException
     * @@roseuid 460320D90290
     */
    protected void deleteOnlineRunResult(String l_strInstitutionCode, String l_strApproveType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteOnlineRunResult(String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�ȉ��̏����ɊY������I�����C�����s���ʃe�[�u����
        //�@@���R�[�h��delete����B
        //
        //�@@[����]
        //�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
        //�@@�@@�����^�C�v = "����"
        //�@@�@@�敨�^�I�v�V�����敪 = "DEFAULT"
        //�@@�@@�I�����C���T�[�r�X�敪 =
        //�@@�@@�@@[�p�����[�^.���F�敪 == "���F"�̏ꍇ]
        //�@@�@@�@@�@@"�������ρi���F�j"
        //�@@�@@�@@[�p�����[�^.���F�敪 == "�񏳔F"�̏ꍇ]
        //�@@�@@�@@�@@"�������ρi�񏳔F�j"
        final String l_strWhere = " institution_code = ? and product_type = ? " +
            "and future_option_div = ? and online_service_div = ?";
        final String l_strCondition = "for update";

        String l_strOnlineServiceDiv = "";
        if (WEB3AdminEquityApproveTypeDef.APPROVE.equals(l_strApproveType))
        {
            l_strOnlineServiceDiv = WEB3OnlineServiceDiv.FORCED_SETTLE_APPROVAL;
        }
        else if (WEB3AdminEquityApproveTypeDef.DISAPPROVE.equals(l_strApproveType))
        {
            l_strOnlineServiceDiv = WEB3OnlineServiceDiv.FORCED_SETTLE_UNAPPROVAL;
        }

        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(ProductTypeEnum.EQUITY);
        l_lisBindValues.add(WEB3FuturesOptionDivDef.DEFAULT);
        l_lisBindValues.add(l_strOnlineServiceDiv);

        final Object[] l_bindValues = new Object[l_lisBindValues.size()];
        l_lisBindValues.toArray(l_bindValues);

        try
        {
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_queryProcesser.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                new TransactionCallback()
                    {
                        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
                        {
                            List l_lisRows = l_queryProcesser.doFindAllQuery(
                                OnlineRunStatusRow.TYPE,
                                l_strWhere,
                                l_strCondition,
                                l_bindValues);
                            int l_intSize =  l_lisRows.size();
                            for (int i = 0; i < l_intSize; i++)
                            {
                                OnlineRunStatusRow l_row = (OnlineRunStatusRow)l_lisRows.get(i);
                                WEB3DataAccessUtility.deleteRow(l_row);
                            }
                            log.exiting(STR_METHOD_NAME);
                            return null;
                        }
                    }
            );
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
    }

    /**
     * (updateAP�ďo��)<BR>
     * �����̃X���b�hNo�ɊY������f�[�����g���K�[��<BR>
     * ���R�[�h���A"AP�ďo��"��update����B<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ɊY������f�[�����g���K�[�e�[�u����<BR>
     * �@@���R�[�h��update����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�X���b�h�ԍ� = �p�����[�^.�X���b�hNo<BR>
     * <BR>
     * �@@[�X�V�l]<BR>
     * �@@�@@������� = "�g���K�[�iAP�ďo���j"<BR>
     * <BR>
     * �@@���{�����͐V�K�g�����U�N�V�����ŏ������s���A<BR>
     * �@@�@@�����������ɍX�V�����f�����悤�ɂ��邱�ƁB<BR>
     * �@@�@@�i�Q�l�FWEB3GentradeDaemonTriggerManager.startThread()�j<BR>
     * @@param l_lngThreadNo - (�X���b�hNo)<BR>
     * �X���b�hNo<BR>
     * @@throws WEB3BaseException
     * @@roseuid 460320D902AF
     */
    protected void updateAPCalling(long l_lngThreadNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateAPCalling(long)";
        log.entering(STR_METHOD_NAME);

        final int UPDATE_SUCCESS = 0; // �X�V����������I�������l
        final int UPDATE_FAIL = -1; // �X�V�ΏۃX���b�h���̍X�V�Ɏ��s�����ꍇ�̒l
        final int NO_ROWS = -2; // �X�V�ΏۃX���b�h�����擾�ł��Ȃ������ꍇ�̒l

        //�����̃X���b�hNo�ɊY������f�[�����g���K�[��
        //���R�[�h���A"AP�ďo��"��update����B
        //�P�j�@@�ȉ��̏����ɊY������f�[�����g���K�[�e�[�u����
        //�@@���R�[�h��update����B
        //�@@[����]
        //�@@�@@�X���b�h�ԍ� = �p�����[�^.�X���b�hNo
        //�@@[�X�V�l]
        //�@@�@@������� = "�g���K�[�iAP�ďo���j"
        //�@@���{�����͐V�K�g�����U�N�V�����ŏ������s���A
        //�@@�@@�����������ɍX�V�����f�����悤�ɂ��邱�ƁB
        //�@@�@@�i�Q�l�FWEB3GentradeDaemonTriggerManager.startThread()�j
        try
        {
            final HashMap l_changes = new HashMap();
            l_changes.put("trigger_status", WEB3DaemonTriggerStatusDef.THREAD_API_CALL);

            final String l_strWhere = "thread_no = ?";
            final String l_strCondition = "for update";
            final Object l_bindVars[] = {new Long(l_lngThreadNo)};
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            Integer l_intResult = (Integer)
                l_queryProcesser.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    new TransactionCallback()
                    {
                        public Object process() throws DataNetworkException,
                            DataQueryException,
                            DataCallbackException
                        {
                            Integer l_intResult = null;
                            List l_lisRows = l_queryProcesser.doFindAllQuery(
                                DaemonTriggerRow.TYPE,
                                l_strWhere, l_strCondition, l_bindVars);
                            if (l_lisRows != null && l_lisRows.size() > 0)
                            {
                                DaemonTriggerRow l_row =
                                    (DaemonTriggerRow)l_lisRows.get(0);
                                WEB3DataAccessUtility.updateRow(l_row, l_changes);
                                l_intResult = new Integer(UPDATE_SUCCESS);
                            }
                            else
                            {
                                l_intResult = new Integer(NO_ROWS);
                            }
                            return l_intResult;
                        }
                    }
                );

            if (l_intResult.intValue() == UPDATE_FAIL)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            }
            else if (l_intResult.intValue() == NO_ROWS)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
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
     * ���ו��U�������s���ׂ�ServerAccessor�I�u�W�F�N�g��<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�j�@@this.accessor != null�̏ꍇ�Athis.accessor��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�P�j�ȊO�̏ꍇ�A�ȍ~�̎菇�ɂ�ServerAccessor<BR>
     * �@@�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �R�j�@@�N���X�^�����O��T�[�o�[URL���擾����B<BR>
     * �@@�@@QueryProcessor.doFindAllQuery()���\�b�h��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@ServerConfigRow.TYPE<BR>
     * �@@�@@�@@arg1�F�@@"config_categ = ?"<BR>
     * �@@�@@�@@arg2�F�@@"cluster.urls"�݂̂�v�f�Ƃ���z��<BR>
     * <BR>
     * �@@�@@��"cluster.urls"�͒萔��`�N���X�Q�Ƃ��邱�ƁB<BR>
     * <BR>
     * �@@�@@�������ʂ̊e�v�f.config_value���擾���A������z���<BR>
     * �@@�@@�쐬����B<BR>
     * �@@�@@���������ʂ��擾�ł��Ȃ������ꍇ�A�u�Y���f�[�^�Ȃ��v��<BR>
     * �@@�@@�@@�V�X�e���G���[���X���[����B<BR>
     * <BR>
     * �S�j�@@ServerAccessor�̍쐬<BR>
     * �@@ServerConnector.createAccessor()���\�b�h���R�[�����A<BR>
     * �@@�߂�l��this.accessor�ɃZ�b�g������A�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[craeteAccessor()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�iACCESSOR_KEY�j�F�@@"web3-static-cluster"<BR>
     * �@@�@@arg1�iURL�j�F�@@�R�j�ɂč쐬����������z��<BR>
     * <BR>
     * �@@�@@��"web3-static-cluster"�͒萔��`�N���X�Q�Ƃ��邱�ƁB<BR>
     * @@return ServerAccessor
     * @@throws WEB3BaseException
     * @@roseuid 460320D902EE
     */
    protected ServerAccessor getServerAccessor() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getServerAccessor()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@this.accessor != null�̏ꍇ�Athis.accessor��ԋp����B
        if (this.accessor != null)
        {
            log.exiting(STR_METHOD_NAME);
            return this.accessor;
        }
        //�Q�j�@@�P�j�ȊO�̏ꍇ�A�ȍ~�̎菇�ɂ�ServerAccessor �I�u�W�F�N�g���擾����
        //�R�j�@@�N���X�^�����O��T�[�o�[URL���擾����B
        //�@@�@@QueryProcessor.doFindAllQuery()���\�b�h��
        //�@@�@@�R�[������B
        //�@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]
        //�@@�@@�@@arg0�F�@@ServerConfigRow.TYPE
        //�@@�@@�@@arg1�F�@@"config_categ = ?"
        //�@@�@@�@@arg2�F�@@"cluster.urls"�݂̂�v�f�Ƃ���z��
        //��"cluster.urls"�͒萔��`�N���X�Q�Ƃ��邱�ƁB
        //�������ʂ̊e�v�f.config_value���擾���A������z���
        //�@@�@@�쐬����B
        //�@@�@@���������ʂ��擾�ł��Ȃ������ꍇ�A�u�Y���f�[�^�Ȃ��v��
        //�@@�@@�@@�V�X�e���G���[���X���[����B

        String l_strQueryWhere = "config_categ = ?";
        Object[] l_bindValues = {WEB3ServerUrlAccessorDef.CLUSTER_URLS};
        List l_lisRecords = null;

        try
        {
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcesser.doFindAllQuery(
                ServerConfigRow.TYPE,
                l_strQueryWhere,
                l_bindValues);

            if (l_lisRecords == null || l_lisRecords.size() == 0)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
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

        //�S�j�@@ServerAccessor�̍쐬
        //�@@ServerConnector.createAccessor()���\�b�h���R�[�����A
        //�@@�߂�l��this.accessor�ɃZ�b�g������A�߂�l��ԋp����B
        //�@@[craeteAccessor()�ɃZ�b�g����p�����[�^]
        //�@@�@@arg0�iACCESSOR_KEY�j�F�@@"web3-static-cluster"
        //�@@�@@arg1�iURL�j�F�@@�R�j�ɂč쐬����������z��
        //�@@�@@��"web3-static-cluster"�͒萔��`�N���X�Q�Ƃ��邱�ƁB
        int l_intLength = l_lisRecords.size();
        ArrayList l_lisConfigValues = new ArrayList();
        for (int i = 0; i < l_intLength; i++)
        {
            ServerConfigRow l_serverConfigRow = (ServerConfigRow)l_lisRecords.get(i);
            l_lisConfigValues.add(l_serverConfigRow.getConfigValue());
        }

        String[] l_strServerConfigList = new String[l_lisConfigValues.size()];
        l_lisConfigValues.toArray(l_strServerConfigList);

        ServerAccessor l_serverAccessor = null;
        try
        {
            l_serverAccessor = ServerConnector.createAccessor(
                WEB3ServerUrlAccessorDef.WEB3_STATIC_CLUSTER,
                l_strServerConfigList);
        }
        catch (CommunicationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (ServerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        this.accessor = l_serverAccessor;

        log.exiting(STR_METHOD_NAME);
        return l_serverAccessor;
    }
}@
