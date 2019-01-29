head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferManagementServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�U�֊Ǘ��T�[�r�X�����N���X(WEB3AdminFXTransferManagementServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/20 ������ (���u) �V�K�쐬
                   2006/04/24 ���� (���u) �d�l�ύX NO.532
                   2006/07/12 ������ (���u) �d�l�ύX NO.595
                   2006/07/18 ��O�� (���u) �d�l�ύX NO.603
                   2006/08/04 ��� (SCS) �d�l�ύX NO.610
Revesion History : 2007/09/12 ���n�m (���u) �d�l�ύX�E���f��No.762
Revesion History : 2008/04/08 ���g (���u) �d�l�ύX�E���f��No.839
Revesion History : 2008/05/20 �đo�g (���u) �d�l�ύX�E���f��861
Revesion History : 2008/06/26 �đo�g (���u) �d�l�ύX�E���f��907
Revesion History : 2008/09/23 �g�C�� (���u) �d�l�ύX�E���f��No.999,1056,1071
Revesion History : 2009/03/11 ���u�� (���u) �d�l�ύX�E���f��No.1116
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioFxTransferDivDef;
import webbroker3.aio.define.WEB3AioTransferDetailMessageDef;
import webbroker3.aio.define.WEB3AioTransferOperationDivDef;
import webbroker3.aio.message.WEB3AdminFXTransferCancelCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXTransferCancelCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXTransferCancelConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXTransferCancelConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXTransferListConditionRequest;
import webbroker3.aio.message.WEB3AdminFXTransferListConditionResponse;
import webbroker3.aio.message.WEB3AdminFXTransferListRequest;
import webbroker3.aio.message.WEB3AdminFXTransferListResponse;
import webbroker3.aio.message.WEB3FXSearchConditionUnit;
import webbroker3.aio.message.WEB3FXTransferDetailUnit;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferManagementService;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferManagementUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FxTransStatusOperationDivDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX�U�֊Ǘ��T�[�r�XImpl) <BR>
 * FX�U�֊Ǘ��T�[�r�X�����N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AdminFXTransferManagementServiceImpl implements
    WEB3AdminFXTransferManagementService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferManagementServiceImpl.class);

    /**
     * (execute) <BR>
     * FX�U�֊Ǘ��T�[�r�X�������s���B <BR>
     * <BR>
     * ���N�G�X�g�̌^�ɂ���āA <BR>
     * <BR>
     * get�������͉��() <BR>
     * get�ꗗ���() <BR>
     * validate���() <BR>
     * submit���() <BR>
     * <BR>
     * �̃��\�b�h���R�[������B
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C0FBEC003D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (l_request instanceof WEB3AdminFXTransferCancelConfirmRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�Ǘ��ҁEFX�U�֎���m�F���N�G�X�g�v�̏ꍇ
            WEB3AdminFXTransferCancelConfirmResponse l_Response = 
                this.validateCancel((WEB3AdminFXTransferCancelConfirmRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else if (l_request instanceof WEB3AdminFXTransferCancelCompleteRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�Ǘ��ҁEFX�U�֎���������N�G�X�g�N���X�v�̏ꍇ
            WEB3AdminFXTransferCancelCompleteResponse l_Response = 
                this.submitCancel((WEB3AdminFXTransferCancelCompleteRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else if (l_request instanceof WEB3AdminFXTransferListRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�Ǘ��ҁEFX�U�ֈꗗ���N�G�X�g�N���X�v�̏ꍇ
            WEB3AdminFXTransferListResponse l_Response = 
                this.getListScreen((WEB3AdminFXTransferListRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else if (l_request instanceof WEB3AdminFXTransferListConditionRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�Ǘ��ҁEFX�U�ֈꗗ�������̓��N�G�X�g�N���X�v�̏ꍇ
            WEB3AdminFXTransferListConditionResponse l_Response = 
                this.getCondInputScreen((WEB3AdminFXTransferListConditionRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * (get�������͉��) <BR>
     * �������͉�ʕ\���f�[�^�̎擾�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�U�֊Ǘ��jget�������͉�ʁv �Q��
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFXTransferListConditionResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C0FC300185
     */
    protected WEB3AdminFXTransferListConditionResponse getCondInputScreen(
        WEB3AdminFXTransferListConditionRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCondInputScreen()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1. ���N�G�X�g�f�[�^�̃`�F�b�N���s���B
        l_request.validate();
        
        // 1.2. �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3. �����`�F�b�N���s���B 
        // [����] 
        // �@@�\�J�e�S���R�[�h�F "B0402" 
        // is�X�V�F false 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE,
            false
            );
        
        // 1.4. ���X�����̃`�F�b�N���s���B 
        // [����] 
        // ���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_admin.validateBranchPermission(l_request.branchCodeList);
        
        // 1.5. ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFXTransferListConditionResponse l_response = 
            (WEB3AdminFXTransferListConditionResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ꗗ���) <BR>
     * �ꗗ��ʕ\���f�[�^�̎擾�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�U�֊Ǘ��jget�ꗗ��ʁv �Q��
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFXTransferListResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C0FC3800F8
     */
    protected WEB3AdminFXTransferListResponse getListScreen(
        WEB3AdminFXTransferListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getListScreen()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1. ���N�G�X�g�f�[�^�̃`�F�b�N���s���B
        l_request.validate();
        
        // 1.2. �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3. �����`�F�b�N���s���B 
        // [����] 
        // �@@�\�J�e�S���R�[�h�F "B0402" 
        // is�X�V�F false 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE,
            false
            );
        
        // 1.4.���X�����̃`�F�b�N���s���B 
        // [����] 
        // ���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_admin.validateBranchPermission(l_request.branchCodeList);
        
        // 1.5. �擾�����̕�����𐶐�����B 
        // [����] 
        // ���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h 
        // �ڋq�R�[�h�F ���N�G�X�g�f�[�^.�ڋq�R�[�h 
        // �U�֋敪�F ���N�G�X�g�f�[�^.�U�֋敪 
        // ��t���i���j�F ���N�G�X�g�f�[�^.��t���i���j 
        // ��t���i���j�F ���N�G�X�g�f�[�^.��t���i���j 
        // �U�֓��F ���N�G�X�g�f�[�^.�U�֓� 
        // �X�e�[�^�X�敪�F ���N�G�X�g�f�[�^.�X�e�[�^�X�敪
        // FX�V�X�e���R�[�h�F���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        String l_strWhere = 
            this.createQueryString(
                l_request.branchCodeList,
                l_request.accountCode,
                l_request.fxTransferDiv,
                l_request.receiptDateFrom,
                l_request.receiptDateTo,
                WEB3DateUtility.formatDate(l_request.transferDate, "yyyyMMdd"),
                l_request.statusDiv,
                l_request.fxSystemCode);
        
        // 1.6. �擾�����̃f�[�^�R���e�i�𐶐�����B 
        // [����] 
        // �،���ЃR�[�h�F �Ǘ���.�،���ЃR�[�h 
        // ���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h 
        // �ڋq�R�[�h�F ���N�G�X�g�f�[�^.�ڋq�R�[�h 
        // �U�֋敪�F ���N�G�X�g�f�[�^.�U�֋敪 
        // ��t���i���j�F ���N�G�X�g�f�[�^.��t���i���j 
        // ��t���i���j�F ���N�G�X�g�f�[�^.��t���i���j 
        // �U�֓��F ���N�G�X�g�f�[�^.�U�֓� 
        // �X�e�[�^�X�敪�F ���N�G�X�g�f�[�^.�X�e�[�^�X�敪
        // FX�V�X�e���R�[�h�F���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        Object[] l_objVars = 
            this.createQueryContainer(
                l_admin.getInstitutionCode(),
                l_request.branchCodeList,
                l_request.accountCode,
                l_request.fxTransferDiv,
                l_request.receiptDateFrom,
                l_request.receiptDateTo,
                WEB3DateUtility.formatDate(l_request.transferDate, "yyyyMMdd"),
                l_request.statusDiv,
                l_request.fxSystemCode);
        
        List l_lisRows = null;
        WEB3PageIndexInfo l_pageInfo = null;
        try
        {
            // 1.7. �N�G���v���Z�b�T���擾����B
            QueryProcessor l_queryProcessor = 
                Processors.getDefaultProcessor();
            
            // 1.8. GFT�U�֏󋵃e�[�u�����烌�R�[�h���擾����B 
            // [����] 
            // Row�^�C�v�F GFT�U�֏�Row.TYPE 
            // Where�F create�擾����������()�̖߂�l 
            // orderBy�F "created_timestamp desc" 
            // condition�F null 
            // ���X�g�F create�擾�����f�[�^�R���e�i()�̖߂�l 
            // �y�[�W�T�C�Y�F ���N�G�X�g�f�[�^.�y�[�W���\���s�� 
            // �y�[�W�ԍ��F ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� - 1 
            List l_lisRowTemp = 
                l_queryProcessor.doFindAllQuery(
                    GftTransferStatusRow.TYPE,
                    l_strWhere,                    
                    " created_timestamp desc ",
                    null,
                    l_objVars,
                    Integer.parseInt(l_request.pageSize),
                    Integer.parseInt(l_request.pageIndex) - 1);
                    
            //log for test
            log.debug("*************** l_lisRowTemp.size = " + l_lisRowTemp.size());                    
            
            l_pageInfo = 
                new WEB3PageIndexInfo(
                    l_lisRowTemp,
                    Integer.parseInt(l_request.pageIndex),
                    Integer.parseInt(l_request.pageSize)
                    );    
            l_lisRows = l_pageInfo.getListReturned();
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // 1.9. ��̃��X�g�𐶐�����B
        List l_lisFXTrsfDetailUnits = new Vector();
        
        // 1.10. �擾�������R�[�h����Loop����
        int l_intLength = 0;
        if(l_lisRows != null)
        {
            l_intLength = l_lisRows.size();
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        //�g���A�J�E���g�}�l�[�W���擾����    
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        for(int i = 0; i < l_intLength; i++)
        {
            GftTransferStatusRow l_gftTrsStatusRow = 
                (GftTransferStatusRow) l_lisRows.get(i);

            String l_strMsgCode;
			String l_strAccountName;
			boolean l_mainAccountFlag = true; 
            try
            {
				// 1.10.1. �ڋq�C���X�^���X���擾����B 
				// [����] 
				// �،���ЃR�[�h�F GFT�U�֏�Params.�،���ЃR�[�h 
				// ���X�R�[�h�F GFT�U�֏�Params.���X�R�[�h 
				// �����R�[�h�F GFT�U�֏�Params.�ڋq�R�[�h 
				WEB3GentradeMainAccount l_mainAccount = 
					l_accountManager.getMainAccount(
						l_gftTrsStatusRow.getInstitutionCode(),
						l_gftTrsStatusRow.getBranchCode(),
						l_gftTrsStatusRow.getAccountCode()
						);
            
				// 1.10.2. �ڋq�����擾����B 
				l_strAccountName = l_mainAccount.getDisplayAccountName();
				
				// 1.10.3. ���b�Z�[�W�R�[�h���擾����B 
				// [����] 
				// �U�֏󋵋敪�F GFT�U�֏�Params.�U�֏󋵋敪 
				// ����M�敪�F GFT�U�֏�Params.����M�敪 
				// ��t���ʃR�[�h�F GFT�U�֏�Params.��t���ʃR�[�h 
				l_strMsgCode = this.getMessageCode(
						l_gftTrsStatusRow.getTransferStatusDiv(),
						l_gftTrsStatusRow.getSendRcvDiv(),
						l_gftTrsStatusRow.getResultCode()
						);
            	
            }
            catch(WEB3BaseException l_ex)
            {
				l_strAccountName = null;
				l_strMsgCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_90000009;
				l_mainAccountFlag = false;
            }
            
            // 1.10.4. FX�U�֖��ׂ𐶐�
            WEB3FXTransferDetailUnit l_detailUnit = 
                new WEB3FXTransferDetailUnit();
            
            // 1.10.5. �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
            // FX�U�֖���.�I���\�t���O = �i�ȉ��̂Ƃ���j�i���P�j
            //     GFT�U�֏�Params.�U�֏󋵋敪 == 0�i���ϒ��j �̏ꍇ�Atrue
            //     GFT�U�֏�Params.�U�֏󋵋敪 == 2�i���σG���[) 
            //        && �iGFT�U�֏�Params.��M���ʃR�[�h == 00000801�i2�d��M�G���[�j
            //            || GFT�U�֏�Params.��M���ʃR�[�h == 00000990 �iGFT�ڑ��G���[�j�j�̏ꍇ�Atrue
            //     GFT�U�֏�Params.�U�֏󋵋敪 != 0�i���ϒ��j �̏ꍇ�Afalse�@@�i����L�����i2�d��M�G���[���AGFT�ڑ��G���[���j�͏����j
            if(l_mainAccountFlag)
            {
				if(WEB3TransferStatusDivDef.PROCESSING.equals(
					l_gftTrsStatusRow.getTransferStatusDiv()))
				{
					l_detailUnit.selectableFlag = true;
				}
				else if(WEB3TransferStatusDivDef.PROCESS_ERROR.equals(
					l_gftTrsStatusRow.getTransferStatusDiv()) &&
					(WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801.equals(l_gftTrsStatusRow.getResultCode())
                        || WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000990.equals(
                            l_gftTrsStatusRow.getResultCode())))
				{
					l_detailUnit.selectableFlag = true;
				}
				else
				{
					l_detailUnit.selectableFlag = false;
				}	
            }
            else
            {
				l_detailUnit.selectableFlag = false;
            }          
            
            // FX�U�֖���.�U�֋敪 = �i�ȉ��̂Ƃ���j
            //    GFT�U�֏�Params.�����敪 == 01�i�،���������ב֕ۏ؋��ցj �̏ꍇ�A2�F�o���iFX)
            //    GFT�U�֏�Params.�����敪 == 02�i�ב֕ۏ؋�����،������ցj �̏ꍇ�A1�F�����iFX)
            //    GFT�U�֏�Params.�����敪 == 03�i�،��������犔��؋����ցj �̏ꍇ�A3�F�o���i��OP�j
            //    GFT�U�֏�Params.�����敪 == 04�i����؋�������،������ցj �̏ꍇ�A4�F�����i��OP�j
            if(WEB3FxTransStatusOperationDivDef.TO_FX.equals(
                l_gftTrsStatusRow.getOperationDiv()))
            {
                l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_OUT_FX;
            }
            else if(WEB3FxTransStatusOperationDivDef.FROM_FX.equals(
                l_gftTrsStatusRow.getOperationDiv()))
            {
                l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_IN_FX;
            }
            else if(WEB3FxTransStatusOperationDivDef.TO_FUOP.equals(
                l_gftTrsStatusRow.getOperationDiv()))
            {
                l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_OUT_OP;
            }
            else if(WEB3FxTransStatusOperationDivDef.FROM_FUOP.equals(
                l_gftTrsStatusRow.getOperationDiv()))
            {
                l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_IN_OP;
            }
            
            // FX�U�֖���.���X�R�[�h = GFT�U�֏�Params.���X�R�[�h
            l_detailUnit.branchCode = l_gftTrsStatusRow.getBranchCode();
            
            // FX�U�֖���.�ڋq�R�[�h = GFT�U�֏�Params.�ڋq�R�[�h
            String l_strAccountCodeToSet = l_gftTrsStatusRow.getAccountCode();
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() > 6)
            {
                l_strAccountCodeToSet = l_gftTrsStatusRow.getAccountCode().substring(0, 6);
            }

            l_detailUnit.accountCode = l_strAccountCodeToSet;
            
            // FX�U�֖���.�ڋq�� = get�ڋq�\����()�̖߂�l�i���Q�j
            // get�ڋq()�ɂė�O���X���[���ꂽ�ꍇ�́A�uNULL�v���Z�b�g
            l_detailUnit.accountName = l_strAccountName;
            
            // FX�U�֖���.���ʃR�[�h = GFT�U�֏�Params.���ʃR�[�h
            l_detailUnit.requestNumber = 
                l_gftTrsStatusRow.getOrderRequestNumber();
            
            // FX�U�֖���.��t���� =GFT�U�֏�Params.�쐬���t
            l_detailUnit.receiptTime = l_gftTrsStatusRow.getCreatedTimestamp();
            
            // FX�U�֖���.�U�֓� = GFT�U�֏�Params.��n�\���
            l_detailUnit.transferDate = 
                WEB3DateUtility.getDate(l_gftTrsStatusRow.getTransferDate(), "yyyyMMdd");
            
            // FX�U�֖���.�U�֋��z = GFT�U�֏�Params.���z
            l_detailUnit.transferAmount = l_gftTrsStatusRow.getAmount() + "";
            
            // FX�U�֖���.�iFX�j�����ԍ� = GFT�U�֏�Params.�����ԍ�
            l_detailUnit.fxAccountCode = l_gftTrsStatusRow.getFxAccountCode();
            
            // FX�U�֖���.�iFX�j�R�[�X�敪 = GFT�U�֏�Params.�R�[�X�敪
            l_detailUnit.fxCourseDiv = l_gftTrsStatusRow.getCourseDiv();
            
            // FX�U�֖���.FX�V�X�e����t���� = GFT�U�֏�Params.�������ԁi��M�j
            l_detailUnit.fxReceiptTime = l_gftTrsStatusRow.getReceiveTime();
            
            // FX�U�֖���.�X�e�[�^�X�敪 = GFT�U�֏�Params.�U�֏󋵋敪
            l_detailUnit.statusDiv = l_gftTrsStatusRow.getTransferStatusDiv();
            
            // FX�U�֖���.���b�Z�[�W = get���b�Z�[�W�R�[�h()�̖߂�l�i���R�j
            // get�ڋq()�ɂė�O���X���[���ꂽ�ꍇ�́A�u90000009(���������j�v���Z�b�g
            l_detailUnit.fxMessage = l_strMsgCode;

            //FX�U�֖���.FX�V�X�e���R�[�h = GFT�U�֏�Params.FX�V�X�e���R�[�h
            l_detailUnit.fxSystemCode = l_gftTrsStatusRow.getFxSystemCode();

            //�i���P�jget�ڋq()�ŗ�O�����������ꍇ�́A�ufalse�v���Z�b�g�B
            //�i���Q�jget�ڋq()�ŗ�O�����������ꍇ�́A�uNULL�v���Z�b�g�B
            //�i���R�jget�ڋq()�ŗ�O�����������ꍇ�́A�u90000009�i���������j�v���Z�b�g����B
            
            // 1.10.6. ���X�g��FX�U�֖��׃I�u�W�F�N�g��ǉ�����B 
            // [����] 
            // arg0�F FX�U�֖��׃I�u�W�F�N�g
            l_lisFXTrsfDetailUnits.add(l_detailUnit);
        }

        //�U�֏o��
        long l_lngFxTotalDepositToGuaranty = 0;
        //�U�֓���
        long l_lngFxTotalGuarantyToDeposit = 0;
        boolean l_blnFxTransFlag = false;

        //1.11���N�G�X�g�f�[�^.�U�֓� != null
        if (l_request.transferDate != null)
        {
            //(*4)create�擾����������()�̖߂�l�Ɉȉ��̕������ǉ�
            //"and transfer_status_div=1 and send_rcv_div=2 and result_code=00000000"
            String l_strQueryString =
                l_strWhere + " and transfer_status_div=1 and send_rcv_div=2 and result_code=00000000 ";

            //doFindAllQuery(arg0 : RowType, arg1 : String, arg2 : Object[])
            //�A�C�e���̒�`
            //GFT�U�֏󋵃e�[�u�����烌�R�[�h���擾����B
            //[����]
            //�@@�@@arg0 �F GFT�U�֏�Row.TYPE
            //�@@�@@arg1 �F (*4)�ɂĐ�������������
            //�@@�@@arg2 �Fcreate�擾�����f�[�^�R���e�i()�̖߂�l
            List l_lisGftTransferStatusRows = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisGftTransferStatusRows = l_queryProcessor.doFindAllQuery(
                     GftTransferStatusRow.TYPE,
                     l_strQueryString,
                     l_objVars);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //(*5)�擾�������R�[�h����Loop����
            for (int i = 0; i < l_lisGftTransferStatusRows.size(); i++)
            {
                GftTransferStatusRow l_gftTransferStatusRow =
                    (GftTransferStatusRow)l_lisGftTransferStatusRows.get(i);
                l_blnFxTransFlag = true;
                // (*6)�U�֋��z�̌v�Z
                //GFT�U�֏�Params.�����敪 = 01(�،���������ב֕ۏ؋��ցj�̏ꍇ
                //�@@�@@�U�֏o�� = �U�֏o�� + GFT�U�֏�Params.���z
                if(WEB3FxTransStatusOperationDivDef.TO_FX.equals(
                    l_gftTransferStatusRow.getOperationDiv()))
                {
                    l_lngFxTotalDepositToGuaranty =
                        l_lngFxTotalDepositToGuaranty
                            + l_gftTransferStatusRow.getAmount();
                }
                //GFT�U�֏�Params.�����敪 = 02(�ב֕ۏ؋�����،������ցj�̏ꍇ
                //�@@�@@�@@�U�֓��� = �U�֓��� + GFT�U�֏�Params.���z
                if(WEB3FxTransStatusOperationDivDef.FROM_FX.equals(
                    l_gftTransferStatusRow.getOperationDiv()))
                {
                    l_lngFxTotalGuarantyToDeposit =
                        l_lngFxTotalGuarantyToDeposit
                            + l_gftTransferStatusRow.getAmount();
                }
            }
        }

        // 1.11. ���X�g����z����擾����B
        WEB3FXTransferDetailUnit[] l_arrFXTrsfDetailUnits = 
            new WEB3FXTransferDetailUnit[l_lisFXTrsfDetailUnits.size()];
        l_lisFXTrsfDetailUnits.toArray(l_arrFXTrsfDetailUnits);
        
        // 1.12. �\���y�[�W�ԍ����擾����B
        int l_intPageIndex = l_pageInfo.getPageIndex();
        
        // 1.13. ���y�[�W�����擾����B
        int l_intTotalPages = l_pageInfo.getTotalPages();
        //log for test
        log.debug("********************** l_intTotalPages = " + l_intTotalPages);
        
        // 1.14. �����R�[�h�����擾����B
        int l_intTotalRecords = l_pageInfo.getTotalRecords();
        //log for test
        log.debug("********************** l_intTotalRecords = " + l_intTotalRecords);
        
        // 1.15. ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFXTransferListResponse l_response = 
            (WEB3AdminFXTransferListResponse) l_request.createResponse();
        
        // 1.16. �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        // ���N�G�X�g�f�[�^.���X�R�[�h�̗v�f����1�̏ꍇ�F���X�|���X.���X�R�[�h�ɂ��̗v�f���Z�b�g
        // ���N�G�X�g�f�[�^.���X�R�[�h�̗v�f���������̏ꍇ�F���X�|���X.���X�R�[�h��null���Z�b�g
        if(l_request.branchCodeList.length == 1)
        {
            l_response.branchCode = l_request.branchCodeList[0];
        }
        else
        {
            l_response.branchCode = null;
        }
        
        // ���X�|���X.�ڋq�R�[�h = ���N�G�X�g�f�[�^.�ڋq�R�[�h
        l_response.accountCode = l_request.accountCode;
        
        // ���X�|���X.�U�֋敪 = ���N�G�X�g�f�[�^.�U�֋敪
        l_response.fxTransferDiv = l_request.fxTransferDiv;
        
        // ���X�|���X.��t���i���j = ���N�G�X�g�f�[�^.��t���i���j
        l_response.receiptDateFrom = l_request.receiptDateFrom;
        
        // ���X�|���X.��t���i���j = ���N�G�X�g�f�[�^.��t���i���j
        l_response.receiptDateTo = l_request.receiptDateTo;
        
        // ���X�|���X.�U�֓� = ���N�G�X�g�f�[�^.�U�֓�
        l_response.transferDate= l_request.transferDate;
        
        // ���X�|���X.�X�e�[�^�X�敪 = ���N�G�X�g�f�[�^.�X�e�[�^�X�敪
        l_response.statusDiv = l_request.statusDiv;
        
        // ���X�|���X.FX�U�֖��׈ꗗ = FX�U�֖��ׂ̔z��
        l_response.fxTransferDetailList = l_arrFXTrsfDetailUnits;
        
        // ���X�|���X.�\���y�[�W�ԍ� = pageNumber()�̖߂�l
        l_response.pageIndex = l_intPageIndex + "";
        
        // ���X�|���X.���y�[�W�� = totalPages()�̖߂�l
        l_response.totalPages = l_intTotalPages + "";
        
        // ���X�|���X.�����R�[�h�� = totalSize()�̖߂�l
        l_response.totalRecords = l_intTotalRecords + "";

        //���U�֏o���A�U�֓����A�U�֍��v�͐U�֋��z�̌v�Z���s���Ȃ������ꍇ�Anull��ԋp����B
        if (l_blnFxTransFlag)
        {
            //���X�|���X.�U�֏o�� = �U�֏o���̌v�Z����
            l_response.fxTotalDepositToGuaranty = l_lngFxTotalDepositToGuaranty + "";

            //���X�|���X.�U�֓��� = �U�֓����̌v�Z����
            l_response.fxTotalGuarantyToDeposit = l_lngFxTotalGuarantyToDeposit + "";

            //���X�|���X.�U�֍��v = �U�֓����̌v�Z���� - �U�֏o���̌v�Z����
            l_response.fxTransferTotal = (l_lngFxTotalGuarantyToDeposit - l_lngFxTotalDepositToGuaranty) + "";
        }
        else
        {
            //���X�|���X.�U�֏o�� = �U�֏o���̌v�Z����
            l_response.fxTotalDepositToGuaranty = null;

            //���X�|���X.�U�֓��� = �U�֓����̌v�Z����
            l_response.fxTotalGuarantyToDeposit = null;

            //���X�|���X.�U�֍��v = �U�֓����̌v�Z���� - �U�֏o���̌v�Z����
            l_response.fxTransferTotal = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate���) <BR>
     * ��������̊m�F���s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�U�֊Ǘ��jvalidate����v �Q��
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFXTransferCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C0FC3D006C
     */
    protected WEB3AdminFXTransferCancelConfirmResponse validateCancel(
        WEB3AdminFXTransferCancelConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCancel()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1. ���N�G�X�g�f�[�^�̃`�F�b�N���s���B
        l_request.validate();
        
        // 1.2. �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3. �����`�F�b�N���s���B 
        // [����] 
        // �@@�\�J�e�S���R�[�h�F "B0402" 
        // is�X�V�F true 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE,
            true
            );
        
        // 1.4. ��̃��X�g�𐶐�����B
        List l_lisFXTransferDetailUnits = new Vector();
        
        // 1.5. FX���������ꗗ�̊e�v�f����Loop����
        WEB3FXSearchConditionUnit[] l_arrFXSearchConditionUnits = 
            l_request.fxSearchConditionList;

        // AIO�����}�l�[�W�����擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        //�g���A�J�E���g�}�l�[�W���擾����    
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        for(int i = 0; i < l_arrFXSearchConditionUnits.length; i++)
        {
            WEB3FXSearchConditionUnit l_conditionUnit = 
                l_arrFXSearchConditionUnits[i];
            
            // 1.5.1. ���X�������`�F�b�N����B 
            // [����] 
            // ���X�R�[�h�F FX��������.���X�R�[�h
            l_admin.validateBranchPermission(l_conditionUnit.branchCode);
            
            // 1.5.2. GFT�U�֏󋵍s�I�u�W�F�N�g���擾����B 
            // [����] 
            // �،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
            // ���X�R�[�h�F FX��������.���X�R�[�h 
            // ���ʃR�[�h�F FX��������.���ʃR�[�h 
            WEB3FXDataControlService l_controlService = 
                (WEB3FXDataControlService) Services.getService(
                    WEB3FXDataControlService.class);               
            
            GftTransferStatusRow l_gftStatusRow = 
                l_controlService.getGFTTransferStatus(
                    l_admin.getInstitutionCode(),
                    l_conditionUnit.branchCode,
                    l_conditionUnit.requestNumber
                    );
            
            // 1.5.3. 
            // �@@ GFT�U�֏�Params.�U�֏󋵋敪 == 0�i���ϒ��j
            // �A GFT�U�֏�Params.�U�֏󋵋敪 == 2�i���σG���[�j && �iGFT�U�֏�Params.��t���ʃR�[�h == 00000801�i2�d��M�G���[�j
            //|| GFT�U�֏�Params.��M���ʃR�[�h == 00000990�iGFT�ڑ��G���[�j�j
            //     �̏ꍇ�A���{
            if((WEB3TransferStatusDivDef.PROCESSING.equals(
                l_gftStatusRow.getTransferStatusDiv())) || 
                (WEB3TransferStatusDivDef.PROCESS_ERROR.equals(
                l_gftStatusRow.getTransferStatusDiv()) &&
                (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801.equals( l_gftStatusRow.getResultCode())
                    || WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000990.equals(
                        l_gftStatusRow.getResultCode()))))
            {
                // 1.5.3.1. �ڋq�C���X�^���X���擾����B 
                // [����] 
                // �،���ЃR�[�h�F GFT�U�֏�Params.�،���ЃR�[�h 
                // ���X�R�[�h�F GFT�U�֏�Params.���X�R�[�h 
                // �����R�[�h�F GFT�U�֏�Params.�ڋq�R�[�h 
                WEB3GentradeMainAccount l_mainAccount = 
                    l_accountManager.getMainAccount(
                        l_gftStatusRow.getInstitutionCode(),
                        l_gftStatusRow.getBranchCode(),
                        l_gftStatusRow.getAccountCode()
                        );
                
                // 1.5.3.2. �ڋq�����擾����B
                String l_strAccountName = 
                    l_mainAccount.getDisplayAccountName();
                
                // 1.5.3.3. ���b�Z�[�W�R�[�h���擾����B 
                // [����] 
                // �U�֏󋵋敪�F GFT�U�֏�Params.�U�֏󋵋敪 
                // ����M�敪�F GFT�U�֏�Params.����M�敪 
                // ��t���ʃR�[�h�F GFT�U�֏�Params.��t���ʃR�[�h 
                String l_strMsgCode = this.getMessageCode(
                    l_gftStatusRow.getTransferStatusDiv(),
                    l_gftStatusRow.getSendRcvDiv(),
                    l_gftStatusRow.getResultCode()
                    );
                
                // 1.5.3.4. FX�U�֖��׃C���X�^���X�𐶐�����B
                WEB3FXTransferDetailUnit l_detailUnit = 
                    new WEB3FXTransferDetailUnit();
                
                // 1.5.3.5. �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
                // FX�U�֖���.�I���\�t���O = true
                l_detailUnit.selectableFlag = true;
                
                // FX�U�֖���.�U�֋敪 = �i�ȉ��̂Ƃ���j
                //    GFT�U�֏�Params.�����敪 == 01�i�،���������ב֕ۏ؋��ցj �̏ꍇ�A2�F�o���iFX)
                //    GFT�U�֏�Params.�����敪 == 02�i�ב֕ۏ؋�����،������ցj �̏ꍇ�A1�F�����iFX)
                //    GFT�U�֏�Params.�����敪 == 03�i�،��������犔��؋����ցj �̏ꍇ�A3�F�o���i��OP�j
                //    GFT�U�֏�Params.�����敪 == 04�i����؋�������،������ցj �̏ꍇ�A4�F�����i��OP�j
                if(WEB3FxTransStatusOperationDivDef.TO_FX.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_OUT_FX;
                }
                else if(WEB3FxTransStatusOperationDivDef.FROM_FX.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_IN_FX;
                }
                else if(WEB3FxTransStatusOperationDivDef.TO_FUOP.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_OUT_OP;
                }
                else if(WEB3FxTransStatusOperationDivDef.FROM_FUOP.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_IN_OP;
                }
                
                // FX�U�֖���.���X�R�[�h = GFT�U�֏�Params.���X�R�[�h
                l_detailUnit.branchCode = l_gftStatusRow.getBranchCode();
                
                // FX�U�֖���.�ڋq�R�[�h = GFT�U�֏�Params.�ڋq�R�[�h
                String l_strAccountCodeToSet = l_gftStatusRow.getAccountCode();
                if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() > 6)
                {
                    l_strAccountCodeToSet = l_gftStatusRow.getAccountCode().substring(0, 6);
                }

                l_detailUnit.accountCode = l_strAccountCodeToSet;
                
                // FX�U�֖���.�ڋq�� = get�ڋq�\����()�̖߂�l
                l_detailUnit.accountName = l_strAccountName;
                
                // FX�U�֖���.���ʃR�[�h = GFT�U�֏�Params.���ʃR�[�h
                l_detailUnit.requestNumber = 
                    l_gftStatusRow.getOrderRequestNumber();
                
                // FX�U�֖���.��t���� =GFT�U�֏�Params.�쐬���t
                l_detailUnit.receiptTime = l_gftStatusRow.getCreatedTimestamp();
                
                // FX�U�֖���.�U�֓� = GFT�U�֏�Params.��n�\���
                l_detailUnit.transferDate = 
                    WEB3DateUtility.getDate(l_gftStatusRow.getTransferDate(), "yyyyMMdd");
                
                // FX�U�֖���.�U�֋��z = GFT�U�֏�Params.���z
                l_detailUnit.transferAmount = l_gftStatusRow.getAmount() + "";
                
                // FX�U�֖���.�iFX�j�����ԍ� = GFT�U�֏�Params.�����ԍ�
                l_detailUnit.fxAccountCode = l_gftStatusRow.getFxAccountCode();
                
                // FX�U�֖���.�iFX�j�R�[�X�敪 = GFT�U�֏�Params.�R�[�X�敪
                l_detailUnit.fxCourseDiv = l_gftStatusRow.getCourseDiv();
                
                // FX�U�֖���.FX�V�X�e����t���� = GFT�U�֏�Params.�������ԁi��M�j
                l_detailUnit.fxReceiptTime = l_gftStatusRow.getReceiveTime();
                
                // FX�U�֖���.�X�e�[�^�X�敪 = GFT�U�֏�Params.�U�֏󋵋敪
                l_detailUnit.statusDiv = l_gftStatusRow.getTransferStatusDiv();
                
                // FX�U�֖���.���b�Z�[�W = get���b�Z�[�W�R�[�h()�̖߂�l
                l_detailUnit.fxMessage = l_strMsgCode;

                // FX�U�֖���.FX�V�X�e���R�[�h = GFT�U�֏�Params.FX�V�X�e���R�[�h
                l_detailUnit.fxSystemCode = l_gftStatusRow.getFxSystemCode();

                // 1.5.3.6. ���X�g��FX�U�֖��׃I�u�W�F�N�g��ǉ�����B 
                // [����] 
                // arg0�F FX�U�֖��׃I�u�W�F�N�g 
                l_lisFXTransferDetailUnits.add(l_detailUnit);
            }
        }
        
        // 1.6. ���X�g����z����擾����B
        WEB3FXTransferDetailUnit[] l_arrFXTransferDetailUnits = 
            new WEB3FXTransferDetailUnit[l_lisFXTransferDetailUnits.size()];
        l_lisFXTransferDetailUnits.toArray(l_arrFXTransferDetailUnits);
        
        // 1.7. ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFXTransferCancelConfirmResponse l_response = 
            (WEB3AdminFXTransferCancelConfirmResponse) l_request.createResponse();
        
        // 1.8. �v���p�e�B�Z�b�g
        l_response.fxTransferDetailList = l_arrFXTransferDetailUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit���) <BR>
     * �����̎�����s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�U�֊Ǘ��jsubmit����v �Q��
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFXTransferCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C0FC41029E
     */
    protected WEB3AdminFXTransferCancelCompleteResponse submitCancel(
        WEB3AdminFXTransferCancelCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitCancel()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }       
        
        // 1.1. ���N�G�X�g�f�[�^�̃`�F�b�N���s���B
        l_request.validate();
        
        // 1.2. �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3. �����`�F�b�N���s���B 
        // [����] 
        // �@@�\�J�e�S���R�[�h�F "B0402" 
        // is�X�V�F true 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE,
            true
            );
        
        // 1.4. �p�X���[�h�̃`�F�b�N���s���B 
        // [����] 
        // �p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);
        
        // 1.5. ��̃��X�g�𐶐�����B
        List l_lisFXTransferDetailUnits = new Vector();
        
        // 1.6. FX���������ꗗ�̊e�v�f����Loop����
        WEB3FXSearchConditionUnit[] l_arrFXSearchConditionUnits = 
            l_request.fxSearchConditionList;

        // AIO�����}�l�[�W�����擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        //�g���A�J�E���g�}�l�[�W���擾����    
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        for(int i = 0; i < l_arrFXSearchConditionUnits.length; i++)
        {
            WEB3FXSearchConditionUnit l_conditionUnit = 
                l_arrFXSearchConditionUnits[i];
            
            // 1.6.1. ���X�������`�F�b�N����B 
            // [����] 
            // ���X�R�[�h�F FX��������.���X�R�[�h
            l_admin.validateBranchPermission(l_conditionUnit.branchCode);
            
            // 1.6.2. GFT�U�֏󋵍s�I�u�W�F�N�g���擾����B 
            // [����] 
            // �،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
            // ���X�R�[�h�F FX��������.���X�R�[�h 
            // ���ʃR�[�h�F FX��������.���ʃR�[�h 
            WEB3FXDataControlService l_controlService = 
                (WEB3FXDataControlService) Services.getService(
                    WEB3FXDataControlService.class);
                       
            GftTransferStatusRow l_gftStatusRow = 
                l_controlService.getGFTTransferStatus(
                    l_admin.getInstitutionCode(),
                    l_conditionUnit.branchCode,
                    l_conditionUnit.requestNumber
                    );
            
            // 1.6.3. 
            // �@@ GFT�U�֏�Params.�U�֏󋵋敪 == 0�i���ϒ��j
            // �A GFT�U�֏�Params.�U�֏󋵋敪 == 2�i���σG���[�j && �iGFT�U�֏�Params.��t���ʃR�[�h == 00000801�i2�d��M�G���[�j
            //  || GFT�U�֏�Params.��M���ʃR�[�h == 00000990�iGFT�ڑ��G���[�j�j
            //     �̏ꍇ�A���{ 
            if((WEB3TransferStatusDivDef.PROCESSING.equals(
                l_gftStatusRow.getTransferStatusDiv())) || 
                (WEB3TransferStatusDivDef.PROCESS_ERROR.equals(
                l_gftStatusRow.getTransferStatusDiv()) &&
                (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801.equals( l_gftStatusRow.getResultCode())
                    || WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000990.equals(
                        l_gftStatusRow.getResultCode()))))
            {
                // 1.6.3.1. �ڋq�C���X�^���X���擾����B 
                // [����] 
                // �،���ЃR�[�h�F GFT�U�֏�Params.�،���ЃR�[�h 
                // ���X�R�[�h�F GFT�U�֏�Params.���X�R�[�h 
                // �����R�[�h�F GFT�U�֏�Params.�ڋq�R�[�h 
                WEB3GentradeMainAccount l_mainAccount = 
                    l_accountManager.getMainAccount(
                        l_gftStatusRow.getInstitutionCode(),
                        l_gftStatusRow.getBranchCode(),
                        l_gftStatusRow.getAccountCode()
                        );
                
                // 1.6.3.2. �ڋq�����擾����B
                String l_strAccountName = 
                    l_mainAccount.getDisplayAccountName();
                
                // 1.6.3.3. ���b�Z�[�W�R�[�h���擾����B 
                // [����] 
                // �U�֏󋵋敪�F GFT�U�֏�Params.�U�֏󋵋敪 
                // ����M�敪�F GFT�U�֏�Params.����M�敪 
                // ��t���ʃR�[�h�F GFT�U�֏�Params.��t���ʃR�[�h 
                String l_strMsgCode = this.getMessageCode(
                    l_gftStatusRow.getTransferStatusDiv(),
                    l_gftStatusRow.getSendRcvDiv(),
                    l_gftStatusRow.getResultCode()
                    );
                
                // 1.6.3.4. FX�U�֖��׃C���X�^���X�𐶐�����B
                WEB3FXTransferDetailUnit l_detailUnit = 
                    new WEB3FXTransferDetailUnit();
                
                // 1.6.3.5. �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
                // FX�U�֖���.�I���\�t���O = true
                l_detailUnit.selectableFlag = true;
                
                // FX�U�֖���.�U�֋敪 = �i�ȉ��̂Ƃ���j
                //    GFT�U�֏�Params.�����敪 == 01�i�،���������ב֕ۏ؋��ցj �̏ꍇ�A2�F�o���iFX)
                //    GFT�U�֏�Params.�����敪 == 02�i�ב֕ۏ؋�����،������ցj �̏ꍇ�A1�F�����iFX)
                //    GFT�U�֏�Params.�����敪 == 03�i�،��������犔��؋����ցj �̏ꍇ�A3�F�o���i��OP�j
                //    GFT�U�֏�Params.�����敪 == 04�i����؋�������،������ցj �̏ꍇ�A4�F�����i��OP�j
                if(WEB3FxTransStatusOperationDivDef.TO_FX.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_OUT_FX;
                }
                else if(WEB3FxTransStatusOperationDivDef.FROM_FX.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_IN_FX;
                }
                else if(WEB3FxTransStatusOperationDivDef.TO_FUOP.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_OUT_OP;
                }
                else if(WEB3FxTransStatusOperationDivDef.FROM_FUOP.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_IN_OP;
                }
                
                // FX�U�֖���.���X�R�[�h = GFT�U�֏�Params.���X�R�[�h
                l_detailUnit.branchCode = l_gftStatusRow.getBranchCode();
                
                // FX�U�֖���.�ڋq�R�[�h = GFT�U�֏�Params.�ڋq�R�[�h
                String l_strAccountCodeToSet = l_gftStatusRow.getAccountCode();
                if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() > 6)
                {
                    l_strAccountCodeToSet = l_gftStatusRow.getAccountCode().substring(0, 6);
                }

                l_detailUnit.accountCode = l_strAccountCodeToSet;
                
                // FX�U�֖���.�ڋq�� = get�ڋq�\����()�̖߂�l
                l_detailUnit.accountName = l_strAccountName;
                
                // FX�U�֖���.���ʃR�[�h = GFT�U�֏�Params.���ʃR�[�h
                l_detailUnit.requestNumber = 
                    l_gftStatusRow.getOrderRequestNumber();
                
                // FX�U�֖���.��t���� =GFT�U�֏�Params.�쐬���t
                l_detailUnit.receiptTime = l_gftStatusRow.getCreatedTimestamp();
                
                // FX�U�֖���.�U�֓� = GFT�U�֏�Params.��n�\���
                l_detailUnit.transferDate = 
                    WEB3DateUtility.getDate(l_gftStatusRow.getTransferDate(), "yyyyMMdd");
                
                // FX�U�֖���.�U�֋��z = GFT�U�֏�Params.���z
                l_detailUnit.transferAmount = l_gftStatusRow.getAmount() + "";
                
                // FX�U�֖���.�iFX�j�����ԍ� = GFT�U�֏�Params.�����ԍ�
                l_detailUnit.fxAccountCode = l_gftStatusRow.getFxAccountCode();
                
                // FX�U�֖���.�iFX�j�R�[�X�敪 = GFT�U�֏�Params.�R�[�X�敪
                l_detailUnit.fxCourseDiv = l_gftStatusRow.getCourseDiv();
                
                // FX�U�֖���.FX�V�X�e����t���� = GFT�U�֏�Params.�������ԁi��M�j
                l_detailUnit.fxReceiptTime = l_gftStatusRow.getReceiveTime();
                
                // FX�U�֖���.�X�e�[�^�X�敪 = GFT�U�֏�Params.�U�֏󋵋敪
                l_detailUnit.statusDiv = l_gftStatusRow.getTransferStatusDiv();
                
                // FX�U�֖���.���b�Z�[�W = get���b�Z�[�W�R�[�h()�̖߂�l
                l_detailUnit.fxMessage = l_strMsgCode;

                // FX�U�֖���.FX�V�X�e���R�[�h = GFT�U�֏�Params.FX�V�X�e���R�[�h
                l_detailUnit.fxSystemCode = l_gftStatusRow.getFxSystemCode();

                // 1.6.3.6. ���X�g��FX�U�֖��׃I�u�W�F�N�g��ǉ�����B 
                // [����] 
                // arg0�F FX�U�֖��׃I�u�W�F�N�g 
                l_lisFXTransferDetailUnits.add(l_detailUnit);
                
                // 1.6.3.7. ����������s���B 
                // [����] 
                // GFT�U�֏�Params�F GFT�U�֏󋵍s�I�u�W�F�N�g 
                // �Ǘ��ҁF �Ǘ��҃I�u�W�F�N�g 
                // �p�X���[�h�F ����.�p�X���[�h
                WEB3AdminFXTransferManagementUnitService l_unitService = 
                    (WEB3AdminFXTransferManagementUnitService) Services.getService(
                        WEB3AdminFXTransferManagementUnitService.class);
                GftTransferStatusParams l_gftStatusParams = 
                    new GftTransferStatusParams(l_gftStatusRow);
                l_unitService.submitCancel(l_gftStatusParams, l_admin, l_request.password);
            }
        }
        
        // 1.7. ���X�g����z����擾����B
        WEB3FXTransferDetailUnit[] l_arrFXSearchConditionUnitsForRsp = 
            new WEB3FXTransferDetailUnit[l_lisFXTransferDetailUnits.size()];
        l_lisFXTransferDetailUnits.toArray(l_arrFXSearchConditionUnitsForRsp);
        
        // 1.8. ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFXTransferCancelCompleteResponse l_response = 
            (WEB3AdminFXTransferCancelCompleteResponse) l_request.createResponse();
        
        // 1.9. �v���p�e�B�Z�b�g
        l_response.fxTransferDetailList = l_arrFXSearchConditionUnitsForRsp;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�擾����������) <BR>
     * GFT�U�֏󋵃e�[�u������f�[�^���擾����ۂ̏����𐶐�����B <BR>
     * <BR>
     * �P�j��̕�����𐶐�����B <BR>
     * <BR>
     * �Q�j�،���ЃR�[�h <BR>
     * <BR>
     * "institution_code=?"���P�j�̕�����ɒǉ�����B<BR>
     * �R�j���X�R�[�h <BR>
     * <BR>
     * �R�|�P�j����.���X�R�[�h.length() == 1 �̏ꍇ <BR>
     * <BR>
     * "branch_code=?"���P�j�̕�����ɒǉ�����B <BR>
     * <BR>
     * �R�|�Q�j����.���X�R�[�h.length() > 1 �̏ꍇ <BR>
     * <BR>
     * "(branch_code=? or branch_code=? or ... or branch_code=?)"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * ��"branch_code=?"�̐��́A���X�R�[�h�̗v�f���Ɠ��� <BR>
     * <BR>
     * �S�j�ڋq�R�[�h <BR>
     * <BR>
     * ����.�ڋq�R�[�h != null �̏ꍇ <BR>
     * <BR>
     * " and account_code=?"���P�j�̕�����ɒǉ�����B <BR>
     * <BR>
     * �T�j�U�֋敪 <BR>
     * <BR>
     * ����.�U�֋敪 != null �̏ꍇ <BR>
     * <BR>
     * " and operation_div=?"���P�j�̕�����ɒǉ�����B <BR>
     * <BR>
     * �U�j��t���i���j <BR>
     * <BR>
     * ����.��t���i���j != null �̏ꍇ <BR>
     * <BR>
     * " and created_timestamp>=?"���P�j�̕�����ɒǉ�����B <BR>
     * <BR>
     * �V�j��t���i���j <BR>
     * <BR>
     * ����.��t���i���j != null �̏ꍇ <BR>
     * <BR>
     * " and created_timestamp <=?"���P�j�̕�����ɒǉ�����B <BR>
     * <BR>
     * �W�j�U�֓� <BR>
     * <BR>
     * ����.�U�֓� != null �̏ꍇ <BR>
     * <BR>
     * " and transfer_date=?"���P�j�̕�����ɒǉ�����B <BR>
     * <BR>
     * �X�j�X�e�[�^�X�敪 <BR>
     * <BR>
     * �X�|�P�j����.�X�e�[�^�X�敪 == 1�i���ϊ����j �̏ꍇ <BR>
     * <BR>
     * " and transfer_status_div=?"���P�j�̕�����ɒǉ�����B <BR>
     * <BR>
     * �X�|�Q�j����.�X�e�[�^�X�敪 == 5�i���̑��j �̏ꍇ <BR>
     * <BR>
     * " and transfer_status_div!=?"���P�j�̕�����ɒǉ�����B <BR>
     * <BR>
     * �P�O�jFX�V�X�e���R�[�h<BR>
     * <BR>
     * �@@����.FX�V�X�e���R�[�h != null�̏ꍇ<BR>
     * �@@" and fx_system_code=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �P�P�j�������ԋp����B<BR>
     * <BR>
     * @@param l_strBranchCodes - ���X�R�[�h�̔z��
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strFxTransferDiv - �U�֋敪
     * @@param l_datReceiptDateFrom - ��t���i���j
     * @@param l_datReceiptDateTo - ��t���i���j
     * @@param l_strTransferDate - �U�֓�
     * @@param l_strStatusDiv - �X�e�[�^�X�敪
     * @@param l_strFxSystemCode - FX�V�X�e���R�[�h
     * @@return String
     * @@roseuid 41C1144B0127
     */
    protected String createQueryString(String[] l_strBranchCodes,
        String l_strAccountCode, String l_strFxTransferDiv,
        Date l_datReceiptDateFrom, Date l_datReceiptDateTo,
        String l_strTransferDate, String l_strStatusDiv,
        String l_strFxSystemCode)
    {
        final String STR_METHOD_NAME =
            "createQueryString(String[], String, String, Date, Date, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBranchCodes == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j��̕�����𐶐�����
        StringBuffer l_strWhere = new StringBuffer();
        
        // �Q�j�،���ЃR�[�h 
        // "institution_code=?"���P�j�̕�����ɒǉ�����B
        l_strWhere.append(" institution_code = ? ");
        
        // �R�j���X�R�[�h  
        if(l_strBranchCodes.length > 0)
        {
            // �R�|�P�j����.���X�R�[�h.length() == 1 �̏ꍇ 
            // " and branch_code=?"���P�j�̕�����ɒǉ�����B
            l_strWhere.append(" and ( branch_code = ? ");
            
            // �R�|�Q�j����.���X�R�[�h.length() > 1 �̏ꍇ 
            // " and (branch_code=? or branch_code=? or ... or branch_code=?)"���P�j�̕�����ɒǉ�����B 
            // ��"branch_code=?"�̐��́A���X�R�[�h�̗v�f���Ɠ���
            for(int i = 1; i < l_strBranchCodes.length; i++)
            {
                l_strWhere.append(" or branch_code = ? ");
            }
            
            l_strWhere.append(" ) ");
        }
        
        // �S�j�ڋq�R�[�h 
        // ����.�ڋq�R�[�h != null �̏ꍇ 
        // " and account_code=?"���P�j�̕�����ɒǉ�����B
        //�������������� += "and substr(account_code, 0, 6) = ? "
        if(!WEB3StringTypeUtility.isEmpty(l_strAccountCode))
        {
            l_strWhere.append(" and substr(account_code, 0, 6) = ? ");
        }
        
        // �T�j�U�֋敪 
        // ����.�U�֋敪 != null �̏ꍇ 
        // " and operation_div=?"���P�j�̕�����ɒǉ�����B
        if(!WEB3StringTypeUtility.isEmpty(l_strFxTransferDiv))
        {
            l_strWhere.append(" and operation_div = ? ");
        }
        
        // �U�j��t���i���j 
        // ����.��t���i���j != null �̏ꍇ 
        // " and created_timestamp>=?"���P�j�̕�����ɒǉ�����B
        if(l_datReceiptDateFrom != null)
        {
            l_strWhere.append(" and to_char(created_timestamp, 'YYYYMMDD') >= ? ");
        }
        
        // �V�j��t���i���j 
        // ����.��t���i���j != null �̏ꍇ 
        // " and created_timestamp<=?"���P�j�̕�����ɒǉ�����B
        if(l_datReceiptDateTo != null)
        {
            l_strWhere.append(" and to_char(created_timestamp, 'YYYYMMDD') <= ? ");
        }
        
        // �W�j�U�֓� 
        // ����.�U�֓� != null �̏ꍇ 
        // " and transfer_date=?"���P�j�̕�����ɒǉ�����B
        if(!WEB3StringTypeUtility.isEmpty(l_strTransferDate))
        {
            l_strWhere.append(" and transfer_date = ? ");
        }
        
        // �X�j�X�e�[�^�X�敪 
        // �X�|�P�j����.�X�e�[�^�X�敪 == 1�i���ϊ����j �̏ꍇ 
        // " and transfer_status_div=?"���P�j�̕�����ɒǉ�����B 
        if(WEB3TransferStatusDivDef.PROCESS_COMPLETE.equals(l_strStatusDiv))
        {
            l_strWhere.append(" and transfer_status_div = ? ");
        }
        else
        {
            // �X�|�Q�j����.�X�e�[�^�X�敪 == 5�i���̑��j �̏ꍇ 
            // " and transfer_status_div!=?"���P�j�̕�����ɒǉ�����B
            if(WEB3TransferStatusDivDef.OTHER.equals(l_strStatusDiv))
            {
                l_strWhere.append(" and transfer_status_div != ? ");
            }
        }

        //����.FX�V�X�e���R�[�h != null�̏ꍇ
        // " and fx_system_code=?"���P�j�̕�����ɒǉ�����B
        if (l_strFxSystemCode != null)
        {
            l_strWhere.append(" and fx_system_code = ? ");
        }

        log.debug("l_strWhere = " + l_strWhere);
        //�������ԋp����B
        
        //log for test
        log.debug("************** the StringWhere = " + l_strWhere);
        
        log.exiting(STR_METHOD_NAME);
        return l_strWhere.toString();
    }

    /**
     * (create�擾�����f�[�^�R���e�i) <BR>
     * GFT�U�֏󋵃e�[�u������f�[�^���擾 <BR>
     * ����ۂ̏����̃f�[�^�R���e�i�𐶐�����B <BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j�،���ЃR�[�h <BR>
     * <BR>
     * ����.�،���ЃR�[�h�̊e�v�f���P�j��List�ɒǉ�����B<BR>
     * �R�j���X�R�[�h <BR>
     * <BR>
     * ����.���X�R�[�h�̊e�v�f���P�j��List�ɒǉ�����B <BR>
     * <BR>
     * �S�j�ڋq�R�[�h <BR>
     * <BR>
     * ����.�ڋq�R�[�h != null �̏ꍇ <BR>
     * <BR>
     * ����.�ڋq�R�[�h���P�j��List�ɒǉ�����B <BR>
     * <BR>
     * �T�j�U�֋敪 <BR>
     * <BR>
     * ����.�U�֋敪 != null �̏ꍇ <BR>
     * <BR>
     * ����.�U�֋敪==�h����(FX)�h�̏ꍇ�́A�h�ב֕ۏ؋�����،������ցh <BR> 
     * ����.�U�֋敪==�h�o��(FX)�h�̏ꍇ�́A�h�،���������ב֕ۏ؋��ցh <BR> 
     * ����.�U�֋敪==�h�o��(��OP)�h�̏ꍇ�́A�h�a������犔��؋����ցh <BR> 
     * ����.�U�֋敪==�h����(��OP)�h�̏ꍇ�́A�h����؋�������a����ցh <BR> 
     * <BR>
     * ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �U�j��t���i���j <BR>
     * <BR>
     * ����.��t���i���j != null �̏ꍇ <BR>
     * <BR>
     * ����.��t���i���j���P�j��List�ɒǉ�����B <BR>
     * <BR>
     * �V�j��t���i���j <BR>
     * <BR>
     * ����.��t���i���j != null �̏ꍇ <BR>
     * <BR>
     * ����.��t���i���j���P�j��List�ɒǉ�����B <BR>
     * <BR>
     * �W�j�U�֓� <BR>
     * <BR>
     * ����.�U�֓� != null �̏ꍇ <BR>
     * <BR>
     * ����.�U�֓����P�j��List�ɒǉ�����B <BR>
     * <BR>
     * �X�j�X�e�[�^�X�敪 <BR>
     * <BR>
     * ����.�X�e�[�^�X�敪 != null �̏ꍇ <BR>
     * <BR>
     * �U�֏󋵋敪.���ϊ���(1)��List�ɒǉ�����B <BR>
     * <BR>
     * �P�O�jFX�V�X�e���R�[�h<BR>
     * <BR>
     * �@@����.FX�V�X�e���R�[�h != null �̏ꍇ<BR>
     * <BR>
     * �@@����.FX�V�X�e���R�[�h���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �P�P�jList����z����擾���āA�ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCodes - ���X�R�[�h�̔z��
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strFxTransferDiv - �U�֋敪
     * @@param l_datReceiptDateFrom - ��t���i���j
     * @@param l_datReceiptDateTo - ��t���i���j
     * @@param l_strTransferDate - �U�֓�
     * @@param l_strStatusDiv - �X�e�[�^�X�敪
     * @@param l_strFxSystemCode - FX�V�X�e���R�[�h
     * @@return Object[]
     * @@roseuid 41C1221801F2
     */
    protected Object[] createQueryContainer(String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strAccountCode, String l_strFxTransferDiv,
        Date l_datReceiptDateFrom, Date l_datReceiptDateTo,
        String l_strTransferDate, String l_strStatusDiv,
        String l_strFxSystemCode)
    {
        final String STR_METHOD_NAME =
            "createQueryContainer(String, String[], String, String, Date, Date, String, String, String)";
        log.entering(STR_METHOD_NAME);
                
        if (l_strBranchCodes == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j���ArrayList�𐶐�����B
        List l_lisVars = new Vector();
        
        // �Q�j�،���ЃR�[�h 
        // ����.�،���ЃR�[�h�̊e�v�f���P�j��List�ɒǉ�����B
        l_lisVars.add(l_strInstitutionCode);
        
        // �R�j���X�R�[�h 
        // ����.���X�R�[�h�̊e�v�f���P�j��List�ɒǉ�����B
        for(int i = 0; i < l_strBranchCodes.length; i++)
        {
            l_lisVars.add(l_strBranchCodes[i]);   
        }
        
        // �S�j�ڋq�R�[�h 
        //����.�ڋq�R�[�h != null �̏ꍇ 
        //����.�ڋq�R�[�h���P�j��List�ɒǉ�����B
        if(!WEB3StringTypeUtility.isEmpty(l_strAccountCode))
        {
            l_lisVars.add(l_strAccountCode.substring(0, 6));
        }
        
        // �T�j�U�֋敪 
        // ����.�U�֋敪 != null �̏ꍇ 
        if(!WEB3StringTypeUtility.isEmpty(l_strFxTransferDiv))
        {
            if (WEB3AioTransferOperationDivDef.CASH_IN.equals(l_strFxTransferDiv))
            {
                // ����.�U�֋敪==�h����(FX)�h�̏ꍇ�́A�h�ב֕ۏ؋�����،������ցh
                l_lisVars.add(WEB3FxTransStatusOperationDivDef.FROM_FX);
            }
            else if(WEB3AioTransferOperationDivDef.CASH_OUT.equals(l_strFxTransferDiv))
            {
                // ����.�U�֋敪==�h�o��(FX)�h�̏ꍇ�́A�h�،���������ב֕ۏ؋��ցh
                l_lisVars.add(WEB3FxTransStatusOperationDivDef.TO_FX);
            }
            else if(WEB3AioTransferOperationDivDef.FUOP_OUT.equals(l_strFxTransferDiv))
            {
                // ����.�U�֋敪==�h�o��(��OP)�h�̏ꍇ�́A�h�a������犔��؋����ցh
                l_lisVars.add(WEB3FxTransStatusOperationDivDef.TO_FUOP);

            }
            else if(WEB3AioTransferOperationDivDef.FUOP_IN.equals(l_strFxTransferDiv))
            {
                // ����.�U�֋敪==�h����(��OP)�h�̏ꍇ�́A�h����؋�������a����ցh
                l_lisVars.add(WEB3FxTransStatusOperationDivDef.FROM_FUOP);
            }
        }
        
        // �U�j��t���i���j 
        // ����.��t���i���j != null �̏ꍇ 
        // ����.��t���i���j���P�j��List�ɒǉ�����B
        if(l_datReceiptDateFrom != null)
        {
            l_lisVars.add(WEB3DateUtility.formatDate(l_datReceiptDateFrom, "yyyyMMdd"));
            //log for test
            log.debug("******************* ��t���i���j = " + l_datReceiptDateFrom);
        }
        
        // �V�j��t���i���j 
        // ����.��t���i���j != null �̏ꍇ 
        // ����.��t���i���j���P�j��List�ɒǉ�����B
        if(l_datReceiptDateTo != null)
        {
            l_lisVars.add(WEB3DateUtility.formatDate(l_datReceiptDateTo, "yyyyMMdd"));
            //log for test
            log.debug("******************* ��t���i���j = " + l_datReceiptDateTo);
        }
        
        // �W�j�U�֓� 
        // ����.�U�֓� != null �̏ꍇ 
        // ����.�U�֓����P�j��List�ɒǉ�����B
        if(!WEB3StringTypeUtility.isEmpty(l_strTransferDate))
        {
            l_lisVars.add(l_strTransferDate);
        }
        
        // �X�j�X�e�[�^�X�敪
        // ����.�X�e�[�^�X�敪 != null �̏ꍇ 
        // �U�֏󋵋敪.���ϊ���(1)��List�ɒǉ�����B
        if(!WEB3StringTypeUtility.isEmpty(l_strStatusDiv))
        {
            l_lisVars.add(WEB3TransferStatusDivDef.PROCESS_COMPLETE);
        }

        //FX�V�X�e���R�[�h
        // ����.FX�V�X�e���R�[�h != null �̏ꍇ
        // ����.FX�V�X�e���R�[�h���P�j��List�ɒǉ�����B
        if (l_strFxSystemCode != null)
        {
            l_lisVars.add(l_strFxSystemCode);
        }

        //List����z����擾���āA�ԋp����B
        String[] l_strArrVars = new String[l_lisVars.size()]; 
        l_lisVars.toArray(l_strArrVars);
        
        //log for test
        for (int i = 0; i < l_strArrVars.length; i++)
        {
            log.debug("************* StringValue[" + i + "]= " + l_strArrVars[i]);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strArrVars;
    }

    /**
     * (get���b�Z�[�W�R�[�h) <BR>
     * ���b�Z�[�W�R�[�h���擾����B <BR>
     * <BR>
     * �P�j����.�U�֏󋵋敪 == 0�i���ϒ��j �̏ꍇ <BR>
     * <BR>
     * �P�|�P�j����.����M�敪 == 1�i���M�ρj �̏ꍇ <BR>
     * <BR>
     * �h���ϒ��h��ԋp����B <BR>
     * <BR>
     * �P�|�Q�j����ȊO �̏ꍇ <BR>
     * <BR>
     * �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B <BR>
     * <BR>
     * �Q�j����.�U�֏󋵋敪 == 1�i���ϊ����j �̏ꍇ <BR>
     * <BR>
     * �Q�|�P�j����.����M�敪 == 2�i��M�ρj <BR>
     * and ����.��t���ʃR�[�h == 00000000 �̏ꍇ <BR>
     * <BR>
     * �h���ϊ����h��ԋp����B <BR>
     * <BR>
     * �Q�|�Q�j����ȊO �̏ꍇ <BR>
     * <BR>
     * �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B�h <BR>
     * <BR>
     * �R�j����.�U�֏󋵋敪 == 2�i���σG���[�j �̏ꍇ <BR>
     * <BR>
     * �R�|�P�j����.����M�敪 == 2�i��M�ρj �̏ꍇ <BR>
     * <BR>
     * ����.��t���ʃR�[�h��ԋp����B <BR>
     * <BR>
     * �R�|�Q�j����ȊO �̏ꍇ <BR>
     * <BR>
     * �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B <BR>
     * <BR>
     * �S�j����.�U�֏󋵋敪 == 3�i����j �̏ꍇ <BR>
     * <BR>
     * �S�|�P�j����.����M�敪 == 1�i���M�ρj �̏ꍇ <BR>
     * <BR>
     * �h��������h��ԋp����B <BR>
     * <BR>
     * �S�|�Q�j����ȊO �̏ꍇ <BR>
     * <BR>
     * �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B <BR>
     * <BR>
     * �T�j��L�̏����̂�����ɂ���v���Ȃ��ꍇ <BR>
     * <BR>
     * �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B
     * 
     * @@param l_strTransferStatusDiv - �U�֏󋵋敪
     * @@param l_strSendRcvDiv - ����M�敪
     * @@param l_strResultCode - ��t���ʃR�[�h
     * @@return String
     * @@roseuid 41C12F750398
     */
    protected String getMessageCode(String l_strTransferStatusDiv,
        String l_strSendRcvDiv, String l_strResultCode)
    {
        final String STR_METHOD_NAME = "getMessageCode()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j����.�U�֏󋵋敪 == 0�i���ϒ��j �̏ꍇ 
        if(WEB3TransferStatusDivDef.PROCESSING.equals(l_strTransferStatusDiv))
        {
            //�P�|�P�j����.����M�敪 == 1�i���M�ρj �̏ꍇ 
            //�h���ϒ��h��ԋp����B 
            if(WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_strSendRcvDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_10000000;
            }
            else
            {
                //�P�|�Q�j����ȊO �̏ꍇ 
                //�h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B  
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_99999999;
            } 
        }
        
        // �Q�j����.�U�֏󋵋敪 == 1�i���ϊ����j �̏ꍇ 
        else if(WEB3TransferStatusDivDef.PROCESS_COMPLETE.equals(l_strTransferStatusDiv))
        {
            // �Q�|�P�j����.����M�敪 == 2�i��M�ρj and ����.��t���ʃR�[�h == 00000000 �̏ꍇ 
            // �h���ϊ����h��ԋp����B 
            if(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_strSendRcvDiv) &&
                WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000000.equals(
                    l_strResultCode))
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000000;
            }
            else
            {
                // �Q�|�Q�j����ȊO �̏ꍇ 
                // �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B�h  
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_99999999;
            }
        }
        
        // �R�j����.�U�֏󋵋敪 == 2�i���σG���[�j �̏ꍇ 
        else if(WEB3TransferStatusDivDef.PROCESS_ERROR.equals(l_strTransferStatusDiv))
        {
            // �R�|�P�j����.����M�敪 == 2�i��M�ρj �̏ꍇ 
            // ����.��t���ʃR�[�h��ԋp����B 
            if(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_strSendRcvDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return l_strResultCode;
            }
            else
            {
                // �R�|�Q�j����ȊO �̏ꍇ 
                // �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B  
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_99999999;
            }   
        }
        
        // �S�j����.�U�֏󋵋敪 == 3�i����j �̏ꍇ 
        else if(WEB3TransferStatusDivDef.CANCEL.equals(l_strTransferStatusDiv))
        {
            // �S�|�P�j����.����M�敪 == 1�i���M�ρj �̏ꍇ 
            // �h��������h��ԋp����B 
            if(WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_strSendRcvDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_90000000;
            }
            else
            {
                // �S�|�Q�j����ȊO �̏ꍇ 
                // �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B  
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_99999999;
            } 
        }
        
        // �T�j��L�̏����̂�����ɂ���v���Ȃ��ꍇ 
        // �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B 
        else
        {
            // �S�|�Q�j����ȊO �̏ꍇ 
            // �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B  
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_99999999;
        }
    }
}@
