head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConTransferMngServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���U�֊Ǘ��T�[�r�XImpl(WEB3AdminFEqConTransferMngServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/21 ���E (���u) �V�K�쐬
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.aio.data.UwgTransferStatusRow;
import webbroker3.aio.define.WEB3AioFEqConTransferReportUnitMessageDef;
import webbroker3.aio.define.WEB3AioTransferOperationDivDef;
import webbroker3.aio.message.WEB3AdminFEqConTransferListInputRequest;
import webbroker3.aio.message.WEB3AdminFEqConTransferListInputResponse;
import webbroker3.aio.message.WEB3AdminFEqConTransferListRequest;
import webbroker3.aio.message.WEB3AdminFEqConTransferListResponse;
import webbroker3.aio.message.WEB3FEqConTransferReportUnit;
import webbroker3.aio.service.delegate.WEB3AdminFEqConTransferMngService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FeqTransOperationDivDef;
import webbroker3.common.define.WEB3FeqTransResultCodeDef;
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
 * (�O���U�֊Ǘ��T�[�r�XImpl)<BR>
 * �O���U�֊Ǘ��T�[�r�X�����N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConTransferMngServiceImpl implements WEB3AdminFEqConTransferMngService 
{
    
    /**
     * @@roseuid 42356517030D
     */
    public WEB3AdminFEqConTransferMngServiceImpl() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConTransferMngServiceImpl.class);

    
    /**
     * �O�������ւ̐U�֊Ǘ��������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *   get���͉��()<BR>
     *   get�ꗗ���()
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E398E40123
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
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
        
        WEB3GenResponse l_response = null;
        if(l_request instanceof  WEB3AdminFEqConTransferListInputRequest)
        {
            l_response = 
                this.getInputScreen((WEB3AdminFEqConTransferListInputRequest) l_request);
        }
        else if(l_request instanceof WEB3AdminFEqConTransferListRequest)
        {
            l_response = 
                this.getListScreen((WEB3AdminFEqConTransferListRequest) l_request);
        }
        else
        {
            log.debug(
                    "���N�G�X�g�f�[�^��"
                    + " WEB3AdminFEqConTransferListInputRequest "
                    + " �� WEB3AdminFEqConTransferListRequest�ȊO�ł���, but is "
                    + l_request.getClass().getName());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʎ擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O���U�֊Ǘ��jget���͉�ʁv �Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConTransferListInputResponse
     * @@roseuid 41E399F60152
     */
    protected WEB3AdminFEqConTransferListInputResponse getInputScreen(
        WEB3AdminFEqConTransferListInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminFEqConTransferListInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3) validate����
        //�A�C�e���̒�`
        //�����`�F�b�N���s���B
        //[����] 
        //�@@�\�J�e�S���R�[�h�F �@@�\�J�e�S���R�[�h.�O�������U�֘A�g�i�U�֊Ǘ��j 
        //is�X�V�F false 
        //�� �@@�\�J�e�S���R�[�h�́ADB���C�A�E�g�u�Ǘ��Ҍ����e�[�u��.xls#�i�⑫�����j�@@�\�J�e�S���R�[�h�ꗗ�v�Q�ƁB
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_TRANSFER_MANAGE,
            false);
        
        //1.4)  validate���X����(String[])
        //�A�C�e���̒�`
        //���X�����̃`�F�b�N���s���B
        //[����] 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h 
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5) createResponse( )
        WEB3AdminFEqConTransferListInputResponse l_respose = 
            (WEB3AdminFEqConTransferListInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_respose;
    }
    
    /**
     * (get�ꗗ���)<BR>
     * �ꗗ��ʎ擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O���U�֊Ǘ��jget�ꗗ��ʁv �Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConTransferListResponse
     * @@roseuid 41E399F60172
     */
    protected WEB3AdminFEqConTransferListResponse getListScreen(
        WEB3AdminFEqConTransferListRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "getListScreen(WEB3AdminFEqConTransferListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2)getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.3) validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�A�C�e���̒�`
        //�����`�F�b�N���s���B 
        //[����] 
        //�@@�\�J�e�S���R�[�h�F �@@�\�J�e�S���R�[�h.�O�������U�֘A�g�i�U�֊Ǘ��j 
        //is�X�V�F false 
        //�� �@@�\�J�e�S���R�[�h�́ADB���C�A�E�g�u�Ǘ��Ҍ����e�[�u��.xls#�i�⑫�����j�@@�\�J�e�S���R�[�h�ꗗ�v�Q�ƁB
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_TRANSFER_MANAGE,
            false);

        //1.4)  validate���X����(String[])
        //�A�C�e���̒�`
        //���X�����̃`�F�b�N���s���B
        //[����] 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h 
        l_administrator.validateBranchPermission(l_request.branchCode);

        //1.5) create�擾����������(String[], String, String, String, String, String, String)
        //�A�C�e���̒�`
        //�擾�����̕�����𐶐�����B
        //[����] 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h 
        //�ڋq�R�[�h�F ���N�G�X�g�f�[�^.�ڋq�R�[�h 
        //�U�֋敪�F ���N�G�X�g�f�[�^.�U�֋敪 
        //��t���i���j�F ���N�G�X�g�f�[�^.��t���i���j 
        //��t���i���j�F ���N�G�X�g�f�[�^.��t���i���j 
        //�U�֓��F ���N�G�X�g�f�[�^.�U�֓� 
        //�X�e�[�^�X�敪�F ���N�G�X�g�f�[�^.�X�e�[�^�X�敪 
        
        String l_strCreateQueryString = this.createQueryString(
            l_request.branchCode,
            l_request.accountCode,
            l_request.transferDiv,
            l_request.receiptDateFrom,
            l_request.receiptDateTo,
            l_request.transferDate,
            l_request.statusDiv);
        
        //1.6)  create�擾�����f�[�^�R���e�i(String, String[], String, String, String, String, String, String)
        //�A�C�e���̒�`
        //�擾�����̃f�[�^�R���e�i�𐶐�����B 
        //[����] 
        //�،���ЃR�[�h�F �Ǘ���.�،���ЃR�[�h 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h 
        //�ڋq�R�[�h�F ���N�G�X�g�f�[�^.�ڋq�R�[�h 
        //�U�֋敪�F ���N�G�X�g�f�[�^.�U�֋敪 
        //��t���i���j�F ���N�G�X�g�f�[�^.��t���i���j 
        //��t���i���j�F ���N�G�X�g�f�[�^.��t���i���j 
        //�U�֓��F ���N�G�X�g�f�[�^.�U�֓� 
        //�X�e�[�^�X�敪�F ���N�G�X�g�f�[�^.�X�e�[�^�X�敪 
        Object[] l_strCreateQueryContainer = this.createQueryContainer(
            l_administrator.getInstitutionCode(),
            l_request.branchCode,
            l_request.accountCode,
            l_request.transferDiv,
            l_request.receiptDateFrom,
            l_request.receiptDateTo,
            l_request.transferDate,
            l_request.statusDiv);
        
        //1.7) getDefaultProcessor( )
        //1.8) doFindAllQuery(RowType, String, String, String, Object[], int, int)
        //�A�C�e���̒�`
        //UWG�U�֏󋵃e�[�u�����烌�R�[�h���擾����B
        //[����] 
        //Row�^�C�v�F UWG�U�֏�Row.TYPE 
        //Where�F create�擾����������()�̖߂�l 
        //orderBy�F "created_timestamp desc" 
        //condition�F null 
        //���X�g�F create�擾�����f�[�^�R���e�i()�̖߂�l 
        //�y�[�W�T�C�Y�F ���N�G�X�g�f�[�^.�y�[�W���\���s�� 
        //�y�[�W�ԍ��F ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� - 1 
        WEB3PageIndexInfo l_pageInfo = null;
        try
        {            
            List l_listPage  = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    UwgTransferStatusRow.TYPE,
                    l_strCreateQueryString,  
                    "created_timestamp desc",
                    null,
                    l_strCreateQueryContainer); 
            
            l_pageInfo = 
                new WEB3PageIndexInfo(
                    l_listPage,
                    Integer.parseInt(l_request.pageIndex),
                    Integer.parseInt(l_request.pageSize)
                    );    
                                     
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
        
        //1.9)  ArrayList( )
        List l_lisFEqConTransferReportUnits = new Vector();
        
        int l_intLength = 0;
        List l_lisReturn = l_pageInfo.getListReturned();
        if(l_pageInfo.getListReturned() != null)
        {
            l_intLength = l_lisReturn.size();
        }
        log.debug("l_intLength = " + l_intLength);
        
        //1.10) �擾�������R�[�h����Loop����
        for(int i = 0; i < l_intLength; i++)
        {
            UwgTransferStatusRow l_uwgTransferStatusRow =
                (UwgTransferStatusRow) l_lisReturn.get(i);
                       
            String l_strDisplayAccountName;
			String l_strMessageCode;
            try
            {
				//1.10.1) get�ڋq(String, String, String)
				//�A�C�e���̒�`
				//�ڋq�C���X�^���X���擾����B
				//[����] 
				//�،���ЃR�[�h�F UWG�U�֏�Params.�،���ЃR�[�h 
				//���X�R�[�h�F UWG�U�֏�Params.���X�R�[�h 
				//�����R�[�h�F UWG�U�֏�Params.�ڋq�R�[�h 
				FinApp l_finApp = (FinApp) Services.getService(FinApp.class);           
				WEB3GentradeAccountManager l_gentradeAccountManager = 
					(WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
            
				WEB3GentradeMainAccount l_mainAccount = 
					l_gentradeAccountManager.getMainAccount(
						l_uwgTransferStatusRow.getInstitutionCode(),
						l_uwgTransferStatusRow.getBranchCode(),
						l_uwgTransferStatusRow.getAccountCode().substring(0, 6));

				//1.10.2) get�ڋq�\����( )
				//�A�C�e���̒�`
				//�ڋq�����擾����B
				l_strDisplayAccountName = l_mainAccount.getDisplayAccountName();
				
				//1.10.3) ���m�莖������
				//get���b�Z�[�W�R�[�h(String, String, String, String)
				//�A�C�e���̒�`
				//���b�Z�[�W�R�[�h���擾����B
				//[����] 
				//�U�֏󋵋敪�F UWG�U�֏�Params.�U�֏󋵋敪 
				//����M�敪�F UWG�U�֏�Params.����M�敪 
				//��t���ʃR�[�h�F UWG�U�֏�Params.��t���ʃR�[�h 
				//�G���[���R�R�[�h�F UWG�U�֏�Params.�G���[���R�R�[�h
				log.debug("l_uwgTransferStatusRow[" + i + "].TransferStatusDiv = " + l_uwgTransferStatusRow.getTransferStatusDiv());
				log.debug("l_uwgTransferStatusRow[" + i + "].SendRcvDiv = " + l_uwgTransferStatusRow.getSendRcvDiv());
				log.debug("l_uwgTransferStatusRow[" + i + "].ResultCode = " + l_uwgTransferStatusRow.getResultCode());
				log.debug("l_uwgTransferStatusRow[" + i + "].ErrorReasonCode = " + l_uwgTransferStatusRow.getErrorReasonCode());
				l_strMessageCode = this.getMessageCode(
					l_uwgTransferStatusRow.getTransferStatusDiv(),
					l_uwgTransferStatusRow.getSendRcvDiv(),
					l_uwgTransferStatusRow.getResultCode(),
					l_uwgTransferStatusRow.getErrorReasonCode());
            }
            catch(WEB3BaseException l_ex)
            {
				l_strDisplayAccountName = null;
				l_strMessageCode = WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_90000009;
            }
                       
            //1.10.4)
            //�O���U�փ��|�[�g����( )
            //�A�C�e���̒�`
            //�O���U�փ��|�[�g���׃C���X�^���X�𐶐�����
            WEB3FEqConTransferReportUnit l_fEqConTransferReportUnit = 
                new WEB3FEqConTransferReportUnit();  
            
            //1.10.5) �v���p�e�B�Z�b�g
            //(*2) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B

            //�O���U�փ��|�[�g����.�U�֋敪 = �i�ȉ��̂Ƃ���j
            //    UWG�U�֏�Params.�����敪 == 01�i�،���������O�����������ցj �̏ꍇ�A2�i�o���j
            //    UWG�U�֏�Params.�����敪 == 02�i�O��������������،������ցj �̏ꍇ�A1�i�����j
            if(WEB3FeqTransOperationDivDef.TRANSFER_TO_FEQ.equals(l_uwgTransferStatusRow.getOperationDiv()))
            {
                l_fEqConTransferReportUnit.transferDiv = WEB3AioTransferOperationDivDef.CASH_OUT;
            }
            else if(WEB3FeqTransOperationDivDef.TRANSFER_FROM_FEQ.equals(l_uwgTransferStatusRow.getOperationDiv()))
            {
                l_fEqConTransferReportUnit.transferDiv = WEB3AioTransferOperationDivDef.CASH_IN;
            }
            
            //�O���U�փ��|�[�g����.�ڋq�R�[�h = UWG�U�֏�Params.�ڋq�R�[�h
            l_fEqConTransferReportUnit.accountCode = l_uwgTransferStatusRow.getAccountCode().substring(0, 6);
            
            //�O���U�փ��|�[�g����.���O�i���j = get�ڋq�\����()�̖߂�l
            //get�ڋq()�ɂė�O�����������ꍇ�A�uNULL�v���Z�b�g
            l_fEqConTransferReportUnit.familyName = l_strDisplayAccountName;
            
            //�O���U�փ��|�[�g����.���ʃR�[�h = UWG�U�֏�Params.���ʃR�[�h
            l_fEqConTransferReportUnit.requestNumber = l_uwgTransferStatusRow.getOrderRequestNumber();
            
            //�O���U�փ��|�[�g����.��t���� =UWG�U�֏�Params.�쐬���t
            l_fEqConTransferReportUnit.receptionDate = l_uwgTransferStatusRow.getCreatedTimestamp();
            
            //�O���U�փ��|�[�g����.�U�֓� = UWG�U�֏�Params.��n�\���
            l_fEqConTransferReportUnit.transferDate = l_uwgTransferStatusRow.getTransferDate();
            
            //�O���U�փ��|�[�g����.�U�֋��z = UWG�U�֏�Params.���z
            l_fEqConTransferReportUnit.changeAmt = String.valueOf(l_uwgTransferStatusRow.getAmount());
            
            //�O���U�փ��|�[�g����.�O�������ԍ� = UWG�U�֏�Params.�����ԍ�
            l_fEqConTransferReportUnit.fstkAccountCode = l_uwgTransferStatusRow.getFeqAccountCode();
            
            //�O���U�փ��|�[�g����.UWG��t���� = UWG�U�֏�Params.�쐬���t
            l_fEqConTransferReportUnit.uwgReceptionDate = l_uwgTransferStatusRow.getCreatedTimestamp();
            
            //�O���U�փ��|�[�g����.�X�e�[�^�X�敪 = UWG�U�֏�Params.�U�֏󋵋敪
            l_fEqConTransferReportUnit.statusDiv = l_uwgTransferStatusRow.getTransferStatusDiv();
            
            //�O���U�փ��|�[�g����.���b�Z�[�W = get���b�Z�[�W�R�[�h()�̖߂�l
            //get�ڋq()�ɂė�O�����������ꍇ�A�u90000009(��������)�v���Z�b�g
            l_fEqConTransferReportUnit.message = l_strMessageCode;
            
            //1.10.6)  add(arg0 : Object)
            //�A�C�e���̒�`
            //���X�g�ɊO���U�փ��|�[�g���׃I�u�W�F�N�g��ǉ�����B
            //[����] 
            //arg0�F �O���U�փ��|�[�g���׃I�u�W�F�N�g 
            l_lisFEqConTransferReportUnits.add(l_fEqConTransferReportUnit);
        }
        
        //1.11) toArray( )
        //�A�C�e���̒�`
        //���X�g����z����擾����B
        WEB3FEqConTransferReportUnit[] l_fEqConTransferReportUnits = 
            new WEB3FEqConTransferReportUnit[l_lisFEqConTransferReportUnits.size()];
        l_lisFEqConTransferReportUnits.toArray(l_fEqConTransferReportUnits);
        
        //1.12) pageNumber( )
        //�A�C�e���̒�`
        //�\���y�[�W�ԍ����擾����B
        //1.13) totalPages( )
        //�A�C�e���̒�`
        //���y�[�W�����擾����B 
        //1.14)totalSize( )
        //�A�C�e���̒�`
        //�����R�[�h�����擾����B 
        //1.15) createResponse( )
        //�A�C�e���̒�`
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFEqConTransferListResponse l_response = 
            (WEB3AdminFEqConTransferListResponse) l_request.createResponse();
        
        //1.16) (*3) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //���X�|���X.�O���U�փ��|�[�g = �O���U�փ��|�[�g���ׂ̔z��
        l_response.fstkTransferReport = l_fEqConTransferReportUnits;
        
        //���X�|���X.�\���y�[�W�ԍ� = pageNumber()�̖߂�l
        l_response.pageIndex = String.valueOf(l_pageInfo.getPageIndex());
        
        //���X�|���X.���y�[�W�� = totalPages()�̖߂�l
        l_response.totalPages = String.valueOf(l_pageInfo.getTotalPages());
        
        //���X�|���X.�����R�[�h�� = totalSize()�̖߂�l
        l_response.totalRecords = String.valueOf(l_pageInfo.getTotalRecords());
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create�擾����������)<BR>
     * UWG�U�֏󋵃e�[�u������f�[�^���擾����ۂ̏����𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * <BR>
     *   "institution_code=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h<BR>
     * <BR>
     * �R�|�P�j����.���X�R�[�h.length() == 1 �̏ꍇ<BR>
     * <BR>
     *   " and branch_code=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�|�Q�j����.���X�R�[�h.length() > 1 �̏ꍇ<BR>
     * <BR>
     *   " and (branch_code=? or branch_code=? or ... or branch_code=?)"<BR>
     *      ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     *   ��"branch_code=?"�̐��́A���X�R�[�h�̗v�f���Ɠ���<BR>
     * <BR>
     * �S�j�ڋq�R�[�h<BR>
     * <BR>
     *   ����.�ڋq�R�[�h != null �̏ꍇ<BR>
     * <BR>
     *   " and account_code=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �T�j�U�֋敪<BR>
     * <BR>
     *   ����.�U�֋敪 != null �̏ꍇ<BR>
     * <BR>
     *   " and operation_div=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �U�j��t���i���j<BR>
     * <BR>
     *   ����.��t���i���j != null �̏ꍇ<BR>
     * <BR>
     *   " and created_timestamp>=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �V�j��t���i���j<BR>
     * <BR>
     *   ����.��t���i���j != null �̏ꍇ<BR>
     * <BR>
     *   " and created_timestamp<=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �W�j�U�֓�<BR>
     * <BR>
     *   ����.�U�֓� != null �̏ꍇ<BR>
     * <BR>
     *   " and transfer_date=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �X�j�X�e�[�^�X�敪<BR>
     * <BR>
     * �X�|�P�j����.�X�e�[�^�X�敪 == 1�i���ϊ����j �̏ꍇ<BR>
     * <BR>
     *   " and transfer_status_div=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �X�|�Q�j����.�X�e�[�^�X�敪 == 5�i���̑��j �̏ꍇ<BR>
     * <BR>
     *   " and transfer_status_div!=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �P�O�j�������ԋp����B
     * @@param l_strBranchCodes - ���X�R�[�h�̔z��
     * 
     * @@param l_strAccountCode - �ڋq�R�[�h
     * 
     * @@param l_strTransferDiv - �U�֋敪
     * 
     * @@param l_strReceiptDateFrom - ��t���i���j
     * 
     * @@param l_strReceiptDateTo - ��t���i���j
     * 
     * @@param l_strTransferDate - �U�֓�
     * 
     * @@param l_strStatusDiv - �X�e�[�^�X�敪
     * 
     * @@return String
     * @@roseuid 41E7555001CE
     */
    protected String createQueryString(
            String[] l_strBranchCodes, 
            String l_strAccountCode, 
            String l_strTransferDiv, 
            Date l_strReceiptDateFrom, 
            Date l_strReceiptDateTo, 
            Date l_strTransferDate, 
            String l_strStatusDiv) 
    {
        final String STR_METHOD_NAME = 
            "createQueryString(String[] l_strBranchCodes,String l_strAccountCode," +
            "String l_strTransferDiv,String l_strReceiptDateFrom, String l_strReceiptDateTo," +
            "String l_strTransferDate,String l_strStatusDiv)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j��̕�����𐶐�����B 
        //�Q�j�،���ЃR�[�h 
        //"institution_code=?"���P�j�̕�����ɒǉ�����B
        StringBuffer l_strBuff = new StringBuffer();
        l_strBuff.append("institution_code = ? ");
        
        //�R�j���X�R�[�h 
        //�R�|�P�j����.���X�R�[�h.length() == 1 �̏ꍇ
        //" and branch_code=?"���P�j�̕�����ɒǉ�����B
        if(l_strBranchCodes.length == 1)
        {
            l_strBuff.append(" and branch_code = ? ");
        }
        
        //�R�|�Q�j����.���X�R�[�h.length() > 1 �̏ꍇ 
        //" and (branch_code=? or branch_code=? or ... or branch_code=?)"���P�j�̕�����ɒǉ�����B
        //��"branch_code=?"�̐��́A���X�R�[�h�̗v�f���Ɠ���
        if(l_strBranchCodes.length > 1)
        {
            l_strBuff.append(" and ( branch_code = ? ");
            
            for(int i = 0; i < l_strBranchCodes.length -1; i++)
            {
                l_strBuff.append(" or branch_code = ? ");
            }
            
            l_strBuff.append(")");
            
        }
        
        //�S�j�ڋq�R�[�h 
        //����.�ڋq�R�[�h != null �̏ꍇ
        //" and account_code=?"���P�j�̕�����ɒǉ�����B
        if(!WEB3StringTypeUtility.isEmpty(l_strAccountCode))
        {
            l_strBuff.append(" and substr(account_code, 1, 6) = ? ");
        }
        
        //�T�j�U�֋敪
        //����.�U�֋敪 != null �̏ꍇ
        //" and operation_div=?"���P�j�̕�����ɒǉ�����B 
        if(!WEB3StringTypeUtility.isEmpty(l_strTransferDiv))
        {
            l_strBuff.append(" and operation_div = ? ");
        }
        
        //�U�j��t���i���j 
        //����.��t���i���j != null �̏ꍇ
        //" and created_timestamp>=?"���P�j�̕�����ɒǉ�����B 
        if(l_strReceiptDateFrom != null)
        {
            l_strBuff.append(" and to_char(created_timestamp, 'YYYYMMDD') >= ? ");
        }
        
        //�V�j��t���i���j 
        //����.��t���i���j != null �̏ꍇ
        //" and created_timestamp<=?"���P�j�̕�����ɒǉ�����B 
        if(l_strReceiptDateTo != null)
        {
            l_strBuff.append(" and to_char(created_timestamp, 'YYYYMMDD') <= ? ");
        }
        
        //�W�j�U�֓� 
        //����.�U�֓� != null �̏ꍇ
        //" and transfer_date=?"���P�j�̕�����ɒǉ�����B 
        if(l_strTransferDate != null)
        {
            l_strBuff.append(" and transfer_date = ? ");
        }
        
        //�X�j�X�e�[�^�X�敪 
        //�X�|�P�j����.�X�e�[�^�X�敪 == 1�i���ϊ����j �̏ꍇ
        //" and transfer_status_div=?"���P�j�̕�����ɒǉ�����B 
        if(WEB3TransferStatusDivDef.PROCESS_COMPLETE.equals(l_strStatusDiv))
        {
            l_strBuff.append(" and transfer_status_div = ? ");
        }
        
        // �X�|�Q�j����.�X�e�[�^�X�敪 == 5�i���̑��j �̏ꍇ
        //" and transfer_status_div!=?"���P�j�̕�����ɒǉ�����B 
        if(WEB3TransferStatusDivDef.OTHER.equals(l_strStatusDiv))
        {
            l_strBuff.append(" and transfer_status_div <> ? ");
        }
        
        //�P�O�j�������ԋp����B 
        
        log.exiting(STR_METHOD_NAME);
        return l_strBuff.toString();
    }
    
    /**
     * (create�擾�����f�[�^�R���e�i)<BR>
     * UWG�U�֏󋵃e�[�u������f�[�^���擾����ۂ̏����̃f�[�^�R���e�i�𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * <BR>
     *   ����.�،���ЃR�[�h�̊e�v�f���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h<BR>
     * <BR>
     *   ����.���X�R�[�h�̊e�v�f���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �S�j�ڋq�R�[�h<BR>
     * <BR>
     *   ����.�ڋq�R�[�h != null �̏ꍇ<BR>
     * <BR>
     *   ����.�ڋq�R�[�h���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �T�j�U�֋敪<BR>
     * <BR>
     *   ����.�U�֋敪 != null �̏ꍇ<BR>
     * <BR>
     *   ����.�U�֋敪==�h�����h�̏ꍇ�́A�h�O��������������،������ցh<BR>
     *   ����.�U�֋敪==�h�o���h�̏ꍇ�́A�h�،���������O�����������ցh <BR> 
     *   ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �U�j��t���i���j<BR>
     * <BR>
     *   ����.��t���i���j != null �̏ꍇ<BR>
     * <BR>
     *   ����.��t���i���j���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �V�j��t���i���j<BR>
     * <BR>
     *   ����.��t���i���j != null �̏ꍇ<BR>
     * <BR>
     *   ����.��t���i���j���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �W�j�U�֓�<BR>
     * <BR>
     *   ����.�U�֓� != null �̏ꍇ<BR>
     * <BR>
     *   ����.�U�֓����P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �X�j�X�e�[�^�X�敪<BR>
     * <BR>
     *   ����.�X�e�[�^�X�敪 != null �̏ꍇ<BR>
     * <BR>
     *   �U�֏󋵋敪.���ϊ���(1)��List�ɒǉ�����B<BR>
     * <BR>
     * �P�O�jList����z����擾���āA�ԋp����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * 
     * @@param l_strBranchCodes - ���X�R�[�h�̔z��
     * 
     * @@param l_strAccountCode - �ڋq�R�[�h
     * 
     * @@param l_strTransferDiv - �U�֋敪
     * 
     * @@param l_strReceiptDateFrom - ��t���i���j
     * 
     * @@param l_strReceiptDateTo - ��t���i���j
     * 
     * @@param l_strTransferDate - �U�֓�
     * 
     * @@param l_strStatusDiv - �X�e�[�^�X�敪
     * 
     * @@return Object[]
     * @@roseuid 41E7555001DE
     */
    protected Object[] createQueryContainer(
            String l_strInstitutionCode, 
            String[] l_strBranchCodes, 
            String l_strAccountCode, 
            String l_strTransferDiv, 
            Date l_datReceiptDateFrom, 
            Date l_datReceiptDateTo, 
            Date l_datTransferDate, 
            String l_strStatusDiv) 
    {
        final String STR_METHOD_NAME = 
            "createQueryContainer(String l_strInstitutionCode, String[] l_strBranchCodes," +
            "String l_strAccountCode,String l_strTransferDiv, Date l_datReceiptDateFrom, " +
            "Date l_datReceiptDateTo, Date l_datTransferDate, String l_strStatusDiv)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���ArrayList�𐶐�����B
        //�Q�j�،���ЃR�[�h
        //����.�،���ЃR�[�h�̊e�v�f���P�j��List�ɒǉ�����B 
        List l_lisQueryContainers = new Vector();
        l_lisQueryContainers.add(l_strInstitutionCode);
        
        //�R�j���X�R�[�h 
        //����.���X�R�[�h�̊e�v�f���P�j��List�ɒǉ�����B 
        for(int i = 0; i < l_strBranchCodes.length; i++)
        {
            l_lisQueryContainers.add(l_strBranchCodes[i]);
        }
        
        //�S�j�ڋq�R�[�h
        //����.�ڋq�R�[�h != null �̏ꍇ
        //����.�ڋq�R�[�h���P�j��List�ɒǉ�����B 
        if(!WEB3StringTypeUtility.isEmpty(l_strAccountCode))
        {
            l_lisQueryContainers.add(l_strAccountCode);
        }
        
        //�T�j�U�֋敪
        //����.�U�֋敪 != null �̏ꍇ
        //����.�U�֋敪==�h�����h�̏ꍇ�́A�h�O��������������،������ցh 
        //����.�U�֋敪==�h�o���h�̏ꍇ�́A�h�،���������O�����������ցh 
        //���P�j��List�ɒǉ�����B�B 
        if(!WEB3StringTypeUtility.isEmpty(l_strTransferDiv))
        {
            if(WEB3AioTransferOperationDivDef.CASH_IN.equals(l_strTransferDiv))
            {
                l_lisQueryContainers.add(WEB3FeqTransOperationDivDef.TRANSFER_FROM_FEQ);
            }
            else if(WEB3AioTransferOperationDivDef.CASH_OUT.equals(l_strTransferDiv))
            {
                l_lisQueryContainers.add(WEB3FeqTransOperationDivDef.TRANSFER_TO_FEQ);
            }
        }
        
        //�U�j��t���i���j
        //����.��t���i���j != null �̏ꍇ
        //����.��t���i���j���P�j��List�ɒǉ�����B 
        if(l_datReceiptDateFrom != null)
        {       
            log.debug("����.��t���i���j= " + l_datReceiptDateFrom);
            log.debug("����.��t���i���j= " + WEB3DateUtility.formatDate(l_datReceiptDateFrom, "yyyyMMdd"));
            l_lisQueryContainers.add(WEB3DateUtility.formatDate(l_datReceiptDateFrom, "yyyyMMdd"));
        }
        
        //�V�j��t���i���j
        //����.��t���i���j != null �̏ꍇ
        //����.��t���i���j���P�j��List�ɒǉ�����B 
        if(l_datReceiptDateTo != null)
        {
            log.debug("����.��t���i���j= " + l_datReceiptDateTo);
            log.debug("����.��t���i���j= " + WEB3DateUtility.formatDate(l_datReceiptDateTo, "yyyyMMdd"));
            l_lisQueryContainers.add(WEB3DateUtility.formatDate(l_datReceiptDateTo, "yyyyMMdd"));
        }
        
        //�W�j�U�֓�
        //����.�U�֓� != null �̏ꍇ
        //����.�U�֓����P�j��List�ɒǉ�����B 
        if(l_datTransferDate != null)
        {
            l_lisQueryContainers.add(WEB3DateUtility.formatDate(l_datTransferDate, "yyyyMMdd"));
        }
        
        //�X�j�X�e�[�^�X�敪 
        //����.�X�e�[�^�X�敪 != null �̏ꍇ
        //�U�֏󋵋敪.���ϊ���(1)��List�ɒǉ�����B 
        if(!WEB3StringTypeUtility.isEmpty(l_strStatusDiv))
        {
            l_lisQueryContainers.add(WEB3TransferStatusDivDef.PROCESS_COMPLETE);
        }
        
        //�P�O�jList����z����擾���āA�ԋp����B
        
        log.exiting(STR_METHOD_NAME);
        return l_lisQueryContainers.toArray();
    }
    
    /**
     * (get���b�Z�[�W�R�[�h)<BR>
     * ���b�Z�[�W�R�[�h���擾����B<BR>     
     * <BR>
     * �P�j����.�U�֏󋵋敪 == �h���ϒ��h �̏ꍇ <BR>
     * <BR>
     * �P�|�P�j����.����M�敪 == �h�����M�h �̏ꍇ <BR>
     * <BR>
     * �h��t�ρh��ԋp����B <BR>
     * <BR>
     * �P�|�Q�j����.����M�敪 == �h���M�ρh �̏ꍇ <BR>
     * <BR>
     * �h���ϒ��h��ԋp����B <BR>
     * <BR>
     * �P�|�R�j����ȊO �̏ꍇ <BR>
     * <BR>
     * �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B <BR>
     * <BR>
     * �Q�j����.�U�֏󋵋敪 == �h���ϊ����h �̏ꍇ <BR>
     * <BR>
     * �Q�|�P�j����.����M�敪 == �h��M�ρh and ����.��t���ʃR�[�h == �h���ϊ����h �̏ꍇ <BR>
     * <BR>
     * �h���ϊ����h��ԋp����B <BR>
     * <BR>
     * �Q�|�Q�j����ȊO �̏ꍇ <BR>
     * <BR>
     * �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B�h <BR>
     * <BR>
     * �R�j����.�U�֏󋵋敪 == �h���σG���[�h �̏ꍇ <BR>
     * <BR>
     * �R�|�P�j����.����M�敪 == �h��M�ρh �̏ꍇ <BR>
     * <BR>
     * ����.�G���[���R�R�[�h��ԋp����B <BR>
     * <BR>
     * �R�|�Q�j����ȊO �̏ꍇ <BR>
     * <BR>
     * �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B <BR>
     * <BR>
     * �S�j����.�U�֏󋵋敪 == �h����h �̏ꍇ <BR>
     * <BR>
     * �S�|�P�j����.����M�敪 == �h�����M�h �̏ꍇ <BR>
     * <BR>
     * �h����ρh��ԋp����B <BR>
     * <BR>
     * �S�|�Q�j����ȊO �̏ꍇ <BR>
     * <BR>
     * �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B <BR>
     * <BR>
     * �T�j��L�̏����̂�����ɂ���v���Ȃ��ꍇ <BR>
     * <BR>
     * �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B <BR>
     * <BR> 
     * @@param l_strTransferStatusDiv - �U�֏󋵋敪
     * 
     * @@param l_strSendRcvDiv - ����M�敪
     * 
     * @@param l_strResultCode - ��t���ʃR�[�h
     * 
     * @@return String
     * @@roseuid 41E7555001E6
     */
    protected String getMessageCode(
            String l_strTransferStatusDiv, 
            String l_strSendRcvDiv, 
            String l_strResultCode,
            String l_strErrorReasonCode) 
    {
        final String STR_METHOD_NAME = 
            "getMessageCode(String l_strTransferStatusDiv, String l_strSendRcvDiv," +
            "String l_strResultCode, String l_strErrorReasonCode)";
        log.entering(STR_METHOD_NAME);
       
        //�P�j����.�U�֏󋵋敪 == �h���ϒ��h �̏ꍇ 
        //�P�|�P�j����.����M�敪 == �h�����M�h �̏ꍇ 
        //  �h��t�ρh��ԋp����B 
        //�P�|�Q�j����.����M�敪 == �h���M�ρh �̏ꍇ 
        //  �h���ϒ��h��ԋp����B 
        //�P�|�R�j����ȊO �̏ꍇ 
        //  �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B 
        if(WEB3TransferStatusDivDef.PROCESSING.equals(l_strTransferStatusDiv))
        {
            if(WEB3SendRcvDivDef.NOT_SEND.equals(l_strSendRcvDiv))
            {
                return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_10000000;
            }
            if(WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_strSendRcvDiv))
            {
                return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_20000000;
            }            
            return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_99999999;
        }
        
        //�Q�j����.�U�֏󋵋敪 == �h���ϊ����h �̏ꍇ 
        //�Q�|�P�j����.����M�敪 == �h��M�ρh and ����.��t���ʃR�[�h == �h���ϊ����h �̏ꍇ 
        //  �h���ϊ����h��ԋp����B 
        //�Q�|�Q�j����ȊO �̏ꍇ 
        //  �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B�h  
        if(WEB3TransferStatusDivDef.PROCESS_COMPLETE.equals(l_strTransferStatusDiv))
        {
            if(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_strSendRcvDiv)
                && WEB3FeqTransResultCodeDef.TRANSFER_COMPLETE.equals(l_strResultCode))
            {
                return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_00000000;
            }
            
            return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_99999999;
        }        

        //�R�j����.�U�֏󋵋敪 == �h���σG���[�h �̏ꍇ 
        //�R�|�P�j����.����M�敪 == �h��M�ρh �̏ꍇ 
        //  ����.�G���[���R�R�[�h��ԋp����B 
        //�R�|�Q�j����ȊO �̏ꍇ 
        //  �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B 
        if(WEB3TransferStatusDivDef.PROCESS_ERROR.equals(l_strTransferStatusDiv))
        {
            if(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_strSendRcvDiv))
            {
                return l_strErrorReasonCode;                                               
            }
            
            return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_99999999;
        }

        //�S�j����.�U�֏󋵋敪 == �h����h �̏ꍇ 
        //�S�|�P�j����.����M�敪 == �h�����M�h �̏ꍇ 
        //  �h����ρh��ԋp����B 
        //�S�|�Q�j����ȊO �̏ꍇ 
        //  �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B 
        if(WEB3TransferStatusDivDef.CANCEL.equals(l_strTransferStatusDiv))
        {
            if(WEB3SendRcvDivDef.NOT_SEND.equals(l_strSendRcvDiv))
            {
                return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_90000000;
            }
            
            return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_99999999;
        }

        //�T�j��L�̏����̂�����ɂ���v���Ȃ��ꍇ 
        //  �h���ώ��s�i�V�X�e���G���[�j�h��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_99999999;
    }
}
@
