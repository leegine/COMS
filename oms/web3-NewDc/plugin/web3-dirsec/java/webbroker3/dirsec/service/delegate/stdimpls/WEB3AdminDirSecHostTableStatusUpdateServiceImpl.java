head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableStatusUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�XImpl(WEB3AdminDirSecHostTableStatusUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 ���r(���u) �V�K�쐬
*/

package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.RowType;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.WEB3AdminDirSecHostTableDataManager;
import webbroker3.dirsec.data.HostManagementRow;
import webbroker3.dirsec.define.WEB3AdminDirSecSortKeyItemDef;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableDetail;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableReferenceRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableReferenceResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableUnit;
import webbroker3.dirsec.message.WEB3AdminDirSecSortKey;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecHostTableStatusUpdateService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�XImpl)<BR>
 * �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X�����N���X�B<BR>
 * 
 * @@author ���r(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableStatusUpdateServiceImpl implements 
    WEB3AdminDirSecHostTableStatusUpdateService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
      private  static WEB3LogUtility log = WEB3LogUtility.getInstance(
              WEB3AdminDirSecHostTableStatusUpdateServiceImpl.class);
    
    /**
     * @@roseuid 442A1C85002E
     */
    public WEB3AdminDirSecHostTableStatusUpdateServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������J�n����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A <BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B <BR>
     * <BR>
     * ���Ǘ��ҁE�L���[�e�[�u���ꗗ���N�G�X�g�̏ꍇ <BR>
     * �@@this.get�L���[�e�[�u���ꗗ()���R�[������B <BR>
     * <BR>
     * ���Ǘ��ҁE�L���[�e�[�u���������̓��N�G�X�g�̏ꍇ <BR>
     * �@@this.get�����������͉��()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�L���[�e�[�u���������ʃ��N�G�X�g�̏ꍇ <BR>
     * �@@this.get�������ʈꗗ()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�m�F���N�G�X�g�̏ꍇ <BR>
     * �@@this.get�X�V�m�F���()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g�̏ꍇ <BR>
     * �@@this.get�X�V�������()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4417DA7E03C0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
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
        
        //���Ǘ��ҁE�L���[�e�[�u���ꗗ���N�G�X�g�̏ꍇ  
        //this.get�L���[�e�[�u���ꗗ()���R�[������
        if (l_request instanceof WEB3AdminDirSecHostTableReferenceRequest)
        {
            l_response = this.getHostTableList(
                (WEB3AdminDirSecHostTableReferenceRequest)l_request);
        }
        
        //���Ǘ��ҁE�L���[�e�[�u���������̓��N�G�X�g�̏ꍇ  
        //this.get�����������͉��()���R�[������B
        else if (l_request instanceof WEB3AdminDirSecHostTableSearchInputRequest)
        {   
            l_response = this.getQueryConditionInputScreen(
                (WEB3AdminDirSecHostTableSearchInputRequest)l_request);
        }
        
        //���Ǘ��ҁE�L���[�e�[�u���������ʃ��N�G�X�g�̏ꍇ  
        //this.get�������ʈꗗ()���R�[������B 
        else if (l_request instanceof WEB3AdminDirSecHostTableSearchListRequest)
        {   
            l_response = this.getQueryResultList(
                (WEB3AdminDirSecHostTableSearchListRequest)l_request);
        }
        
        //���Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�m�F���N�G�X�g�̏ꍇ  
        //this.get�X�V�m�F���()���R�[������B
        else if (l_request instanceof WEB3AdminDirSecHostTableStatusConfirmRequest)
        {   
            l_response = this.getUpdateConfirmScreen(
                (WEB3AdminDirSecHostTableStatusConfirmRequest)l_request);
        }
        
        //���Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g�̏ꍇ  
        //this.get�X�V�������()���R�[������B
        else if (l_request instanceof WEB3AdminDirSecHostTableStatusCompleteRequest)
        {   
            l_response = this.getUpdateCompleteScreen(
                (WEB3AdminDirSecHostTableStatusCompleteRequest)l_request);
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
     * (get�L���[�e�[�u���ꗗ)<BR>
     * �Ǘ��҃L���[�e�[�u���ꗗ��ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Ǘ��ҁjget�L���[�e�[�u���ꗗ�v�Q�ƁB <BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i�Ǘ��ҁjget�L���[�e�[�u���ꗗ       <BR>
     *         ��̈ʒu    :  1.4 isDIR�Ǘ���( )  <BR>
     *         DIR�Ǘ��Ҍ����`�F�b�N �B<BR>
     *        �iisDIR�Ǘ���( ) == false �̏ꍇ�A<BR>
     *         [DIR�Ǘ��Ҍ����`�F�b�N�G���[�B]�Ƃ��ė�O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     *  
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�L���[�e�[�u���ꗗ���N�G�X�g�N���X�B<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminDirSecHostTableReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 4418B74F01EB
     */
    protected WEB3AdminDirSecHostTableReferenceResponse 
        getHostTableList(WEB3AdminDirSecHostTableReferenceRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getHostTableList(WEB3AdminDirSecHostTableReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate�����i�j�Ɏw�肷�����] 
        //�@@�\�J�e�S���R�[�h�F�V�X�e���Ǘ��i�L���[�e�[�u���X�e�[�^�X�X�V�j 
        //is�X�V�Ftrue 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);
        
        //1.4 isDIR�Ǘ���( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
        
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }
        
        //1.5 create�L���[�e�[�u���Ǘ��\�[�g����(�L���[�e�[�u���\�[�g�L�[�m�n)
        //[create�\�[�g�����i�j�Ɏw�肷�����] 
        //�L���[�e�[�u���\�[�g�L�[�@@�F�@@���N�G�X�g�I�u�W�F�N�g.�L���[�e�[�u���\�[�g�L�[ 
        String l_strSortCondition = createHostManageSortCondition(l_request.sortKeys);
        
        //1.6 get�L���[�e�[�u���ꗗ(String, String, Object�m�n)
        //[get�L���[�e�[�u���ꗗ()�Ɏw�肷�����] 
        //���������F null 
        //�\�[�g�����F�@@create�\�[�g�����i�j�Ŏ擾�����l�B 
        //���������f�[�^�F�@@null
        WEB3AdminDirSecHostTableDataManager l_hostTableDataManager = 
            new WEB3AdminDirSecHostTableDataManager();
        List l_lisHostTableList = l_hostTableDataManager.getHostTableList(
            null, l_strSortCondition, null);
        
        //1.7 WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //[WEB3PageIndexInfo�i�j�Ɏw�肷�����] 
        //l_list : get�L���[�e�[�u���ꗗ�i�j�̖߂�l 
        //l_intRequestPageIndex :�@@���N�G�X�g�f�[�^.�v���y�[�W�ԍ���int�^�ɕϊ������l 
        //l_intRequestPageSize :�@@���N�G�X�g�f�[�^.�y�[�W���\���s����int�^�ɕϊ������l
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisHostTableList, l_intPageIndex, l_intPageSize);
        
        //1.8 getListReturned( )
        List l_lisPageIndexInfoResult = l_pageIndexInfo.getListReturned();
        
        //1.9 create�L���[�e�[�u���ꗗ���(List)
        //[create�L���[�e�[�u���ꗗ���i�j�Ɏw�肷�����] 
        //�L���[�e�[�u���ꗗList�@@�F�@@getListReturned�i�j�Ŏ擾�����l�B
        WEB3AdminDirSecHostTableUnit[] l_hostTableUnits = 
            l_hostTableDataManager.createHostTableListInfo(l_lisPageIndexInfoResult); 
        
        //1.10 getTotalPages( )
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();
        
        //1.11 getTotalRecords( )
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();
        
        // 1.12 createResponse( )
        WEB3AdminDirSecHostTableReferenceResponse l_response = 
            (WEB3AdminDirSecHostTableReferenceResponse) l_request.createResponse();
        
        // 1.13 �i*�j�v���p�e�B�Z�b�g
        //���y�[�W��       = WEB3StringTypeUtility.formatNumber
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(totalPages()�̖߂�l);
        //�����R�[�h��     = WEB3StringTypeUtility.formatNumber
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(totalSize()�̖߂�l);
        //�\���y�[�W�ԍ� = ���N�G�X�g�I�u�W�F�N�g.�\���y�[�W�ԍ�
        //
        //�L���[�e�[�u���ꗗ = create�L���[�e�[�u���ꗗ���()�̖߂�l
        l_response.totalPages = "" + l_intTotalPages;
        l_response.totalRecords = "" + l_intTotalRecords;
        l_response.pageIndex = l_request.pageIndex;
        l_response.hostTables = l_hostTableUnits;
        
        log.exiting(STR_METHOD_NAME);                
        return l_response;
    }
    
    /**
     * (get�����������͉��)<BR>
     * �Ǘ��҃L���[�e�[�u�����R�[�h������ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Ǘ��ҁjget�����������͉�ʁv�Q�ƁB <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i�Ǘ��ҁjget�����������͉��       <BR>
     *         ��̈ʒu    :  1.4 isDIR�Ǘ���( )  <BR>
     *         DIR�Ǘ��Ҍ����`�F�b�N �B<BR>
     *        �iisDIR�Ǘ���( ) == false �̏ꍇ�A<BR>
     *         [DIR�Ǘ��Ҍ����`�F�b�N�G���[�B]�Ƃ��ė�O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�L���[�e�[�u���������̓��N�G�X�g�N���X�B<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4418B93F03C0
     */
    protected WEB3AdminDirSecHostTableSearchInputResponse getQueryConditionInputScreen(
        WEB3AdminDirSecHostTableSearchInputRequest l_request) 
        throws WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = 
            " getQueryConditionInputScreen(WEB3AdminDirSecHostTableSearchInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate�����i�j�Ɏw�肷�����] 
        //�@@�\�J�e�S���R�[�h�F�V�X�e���Ǘ��i�L���[�e�[�u���X�e�[�^�X�X�V�j 
        //is�X�V�Ftrue 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //1.4 isDIR�Ǘ���( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
        
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }
        
        //1.5 get���X�R�[�h( )
        String l_strBranchCode = l_admin.getBranchCode();
        
        //1.6 get�L���[�e�[�u���Ǘ�(String)
        //[get�L���[�e�[�u���Ǘ��i�j�Ɏw�肷��l] 
        //�L���[�e�[�u�����@@�F�@@���N�G�X�g�I�u�W�F�N�g.�L���[�e�[�u�����B
        WEB3AdminDirSecHostTableDataManager l_hostTableDataManager = 
            new WEB3AdminDirSecHostTableDataManager();
        HostManagementRow l_hostManagementRow = 
            l_hostTableDataManager.getHostTableManagement(l_request.tableJpnName);
        
        //1.7 createResponse( )
        WEB3AdminDirSecHostTableSearchInputResponse l_response = 
            (WEB3AdminDirSecHostTableSearchInputResponse) l_request.createResponse();
        
        //1.8 (*)�v���p�e�B�Z�b�g
        //�e�[�u����    = �@@ get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�e�[�u�����i�j
        l_response.tableJpnName = l_hostManagementRow.getHostTableName();
        
        //�e�[�u���������@@=�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�e�[�u���������i�j
        l_response.tableName = l_hostManagementRow.getHostTablePhysicsName();
        
        //���ʃR�[�h�L���t���O�@@=�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get���ʃR�[�h�L���t���O�i�j
        l_response.identityCodeFlag = l_hostManagementRow.getOrderRequestNumberDiv();
        
        //�쐬���t�L���t���O�@@=�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�쐬���t�L���t���O�i�j
        l_response.createDateFlag = l_hostManagementRow.getCreatedTimestampDiv();
        
        //���X�R�[�h�@@=�@@�Ǘ��҃N���X�I�u�W�F�N�g.get���X�R�[�h�i�j�̖߂�l
        l_response.branchCode = l_strBranchCode;
        
        log.exiting(STR_METHOD_NAME);                
        return l_response;        
    }
    
    /**
     * (get�������ʈꗗ)<BR>
     * �Ǘ��҃L���[�e�[�u�����R�[�h�������ʉ�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Ǘ��ҁjget�������ʈꗗ�v�Q�ƁB <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i�Ǘ��ҁjget�������ʈꗗ       <BR>
     *         ��̈ʒu    :  1.4 isDIR�Ǘ���( )  <BR>
     *         DIR�Ǘ��Ҍ����`�F�b�N �B<BR>
     *        �iisDIR�Ǘ���( ) == false �̏ꍇ�A<BR>
     *         [DIR�Ǘ��Ҍ����`�F�b�N�G���[�B]�Ƃ��ė�O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�L���[�e�[�u���������ʃ��N�G�X�g�N���X�B<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4418B9C900D2
     */
    protected WEB3AdminDirSecHostTableSearchListResponse getQueryResultList(
        WEB3AdminDirSecHostTableSearchListRequest l_request) 
        throws WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = 
            " getQueryResultList(WEB3AdminDirSecHostTableSearchListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate�����i�j�Ɏw�肷�����] 
        //�@@�\�J�e�S���R�[�h�F�V�X�e���Ǘ��i�L���[�e�[�u���X�e�[�^�X�X�V�j 
        //is�X�V�Ftrue 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);
        
        //1.4 isDIR�Ǘ���( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
        
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }
        
        //1.5 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 get�L���[�e�[�u���Ǘ�(String)
        //[get�L���[�e�[�u���Ǘ��i�j�Ɏw�肷��l] 
        //�L���[�e�[�u�����@@�F�@@���N�G�X�g�I�u�W�F�N�g.�L���[�e�[�u�����B
        WEB3AdminDirSecHostTableDataManager l_hostTableDataManager = 
            new WEB3AdminDirSecHostTableDataManager();
        HostManagementRow l_hostManagementRow = 
            l_hostTableDataManager.getHostTableManagement(l_request.tableJpnName);
        
        //1.7 getRowType(String, String)
        //[getRowType()�Ɏw�肷�����] 
        //�e�[�u���������@@�F�@@���N�G�X�g�I�u�W�F�N�g.�e�[�u�������� 
        //�N�G���v���Z�b�T���@@�F�@@get�L���[�e�[�u���Ǘ�()�Ŏ擾���� 
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@Row�I�u�W�F�N�g.get�N�G���v���Z�b�T��
        RowType l_rowType = l_hostTableDataManager.getRowType(
            l_request.tableName, l_hostManagementRow.getQueryprocessorName());
        
        //1.8 create�L���[�e�[�u���\�[�g����(�L���[�e�[�u���\�[�g�L�[[])
        //[create�L���[�e�[�u���\�[�g����( )�Ɏw�肷�����] 
        //�L���[�e�[�u���\�[�g�L�[�@@�F�@@���N�G�X�g�I�u�W�F�N�g.�L���[�e�[�u���\�[�g�L�[[]
        String l_strSortCondition = createHostTableSortCondition(l_request.sortKeys);
        
        //1.9 create�L���[�e�[�u����������(WEB3GenRequest, String)
        //[create�L���[�e�[�u����������()�Ɏw�肷�����] 
        //���N�G�X�g�I�u�W�F�N�g�@@�F�@@�Ǘ��ҁE�L���[�e�[�u���������ʃ��N�G�X�g�N���X 
        //�،���ЃR�[�h�@@�F�@@get�،���ЃR�[�h�i�j�̖߂�l
        String l_strQueryCondition = createHostTableQueryCondition(
            l_request, l_strInstitutionCode);
        
        //1.10 create�L���[�e�[�u�����������f�[�^(WEB3GenRequest, String)
        //[create�L���[�e�[�u�����������f�[�^�i�j�Ɏw�肷�����] 
        //���N�G�X�g�I�u�W�F�N�g�@@�F�@@�Ǘ��ҁE�L���[�e�[�u���������ʃ��N�G�X�g�N���X 
        //�،���ЃR�[�h�@@�F�@@get�،���ЃR�[�h�i�j�̖߂�l
        Object[] l_objQueryConditionDatas = createHostTableQueryConditionData(
            l_request, l_strInstitutionCode);
        
        //1.11 get�L���[�e�[�u�����R�[�h(String, Object[], RowType, String, int, int)
        //[get�L���[�e�[�u�����R�[�h(�j�Ɏw�肷�����] 
        //���������@@�F�@@create�L���[�e�[�u�����������i�j�̖߂�l 
        //���������f�[�^�@@�F�@@create�L���[�e�[�u�����������f�[�^�i�j�̖߂�l 
        //�L���[�e�[�u��RowType�@@�F�@@getRowType�i�j�̖߂�l 
        //�\�[�g�����@@�F�@@create�L���[�e�[�u���\�[�g�����i�j�̖߂�l
        List l_lisHostTableRecords = l_hostTableDataManager.getHostTableRecord(
            l_strQueryCondition,
            l_objQueryConditionDatas,
            l_rowType,
            l_strSortCondition);
        
        //1.12 WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //[WEB3PageIndexInfo�i�j�Ɏw�肷�����] 
        //l_list : get�L���[�e�[�u���ꗗ�i�j�̖߂�l 
        //l_intRequestPageIndex :�@@���N�G�X�g�f�[�^.�v���y�[�W�ԍ���int�^�ɕϊ������l 
        //l_intRequestPageSize :�@@���N�G�X�g�f�[�^.�y�[�W���\���s����int�^�ɕϊ������l
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisHostTableRecords, l_intPageIndex, l_intPageSize);
        
        //1.13 getListReturned( )
        List l_lisPageIndexInfoResult = l_pageIndexInfo.getListReturned();
        
        //1.14 create�L���[�e�[�u�����R�[�h�ڍ�(List, String, String)
        //[create�L���[�e�[�u�����R�[�h�ڍׁi�j�Ɏw�肷�����] 
        //�L���[�e�[�u�����R�[�hList�@@�F�@@getListReturned�i�j�Ŏ擾�����l  
        //���ʃR�[�h�L���t���O�@@�F�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get���ʃR�[�h�L���t���O 
        //�쐬���t�L���t���O�@@�F�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�쐬���t�L���t���O
        WEB3AdminDirSecHostTableDetail[] l_hostTableDetails = 
            l_hostTableDataManager.createHostTableDetails(
                l_lisPageIndexInfoResult, 
                l_hostManagementRow.getOrderRequestNumberDiv(),
                l_hostManagementRow.getCreatedTimestampDiv());
        
        //1.15 getTotalPages( )
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();
        
        //1.16 getTotalRecords( )
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();
        
        //1.17 createResponse( )
        WEB3AdminDirSecHostTableSearchListResponse l_response = 
            (WEB3AdminDirSecHostTableSearchListResponse) l_request.createResponse();
        
        //1.18 (*)�v���p�e�B�Z�b�g
        //�e�[�u����    = �@@ get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�e�[�u�����i�j
        l_response.tableJpnName = l_hostManagementRow.getHostTableName();
        
        //�e�[�u���������@@=�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�e�[�u���������i�j
        l_response.tableName = l_hostManagementRow.getHostTablePhysicsName();
        
        //���ʃR�[�h�L���t���O�@@=�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get���ʃR�[�h�L���t���O�i�j
        l_response.identityCodeFlag = l_hostManagementRow.getOrderRequestNumberDiv();
        
        //�쐬���t�L���t���O�@@=�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�쐬���t�L���t���O�i�j
        l_response.createDateFlag = l_hostManagementRow.getCreatedTimestampDiv();
        
        //���y�[�W��       = WEB3StringTypeUtility.formatNumber
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(totalPages()�̖߂�l);
        l_response.totalPages = "" + l_intTotalPages;
        
        //�����R�[�h��     = WEB3StringTypeUtility.formatNumber
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(totalSize()�̖߂�l);
        l_response.totalRecords = "" + l_intTotalRecords;

        //�\���y�[�W�ԍ� = ���N�G�X�g�I�u�W�F�N�g.�\���y�[�W�ԍ�
        l_response.pageIndex = l_request.pageIndex;

        //�L���[�e�[�u�����R�[�h�ڍ� = create�L���[�e�[�u�����R�[�h�ڍ�()�̖߂�l
        l_response.hostTableDetails = l_hostTableDetails;
        
        log.exiting(STR_METHOD_NAME);                
        return l_response;   
    }
    
    /**
     * (get�X�V�m�F���)<BR>
     * �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�m�F��ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Ǘ��ҁjget�X�V�m�F��ʁv�Q�ƁB <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i�Ǘ��ҁjget�X�V�m�F���      <BR>
     *         ��̈ʒu    :  1.4 isDIR�Ǘ���( )  <BR>
     *         DIR�Ǘ��Ҍ����`�F�b�N �B<BR>
     *        �iisDIR�Ǘ���( ) == false �̏ꍇ�A<BR>
     *         [DIR�Ǘ��Ҍ����`�F�b�N�G���[�B]�Ƃ��ė�O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�m�F���N�G�X�g�N���X�B<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4418BAB30007
     */
    protected WEB3AdminDirSecHostTableStatusConfirmResponse getUpdateConfirmScreen(
        WEB3AdminDirSecHostTableStatusConfirmRequest l_request) 
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getUpdateConfirmScreen" +
                "(WEB3AdminDirSecHostTableStatusConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����] 
        //�@@�\�J�e�S���R�[�h�F�V�X�e���Ǘ��i�L���[�e�[�u���X�e�[�^�X�X�V�j 
        //is�X�V�Ftrue 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);
                
        //1.4 isDIR�Ǘ���( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
        
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }
        //1.5 get�،���ЃR�[�h( )   
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 get�L���[�e�[�u���Ǘ�(String)
        //[get�L���[�e�[�u���Ǘ��i�j�Ɏw�肷�����] 
        //�L���[�e�[�u�����@@�F�@@���N�G�X�g�I�u�W�F�N�g.�e�[�u����        
        WEB3AdminDirSecHostTableDataManager l_hostTableDataManager = 
            new WEB3AdminDirSecHostTableDataManager();
        
        HostManagementRow l_hostManagementRow = 
            l_hostTableDataManager.getHostTableManagement(
            l_request.tableJpnName);
        
        //1.7 getRowType(String, String)
        //[����] 
        //�e�[�u���������@@�F�@@���N�G�X�g�I�u�W�F�N�g.�e�[�u�������� 
        //�N�G���v���Z�b�T���@@�F�@@get�L���[�e�[�u���Ǘ�()�Ŏ擾����
        //Row�I�u�W�F�N�g.get�N�G���v���Z�b�T�� 
        RowType l_rowType = l_hostTableDataManager.getRowType(
            l_request.tableName,
            l_hostManagementRow.getQueryprocessorName());
        
        //1.8 create�L���[�e�[�u���Ǘ��\�[�g����(�L���[�e�[�u���\�[�g�L�[�m�n)
        //[create�L���[�e�[�u���\�[�g����( )�Ɏw�肷�����] 
        //�L���[�e�[�u���\�[�g�L�[�@@�F�@@���N�G�X�g�I�u�W�F�N�g.�L���[�e�[�u���\�[�g�L�[[]
        String l_strSortCondition = this.createHostTableSortCondition(
            l_request.sortKeys);
        
        //1.9 create�L���[�e�[�u����������(WEB3GenRequest, String)
        //[create�L���[�e�[�u����������()�Ɏw�肷�����] 
        //���N�G�X�g�I�u�W�F�N�g�@@�F�@@�Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g�N���X 
        //�،���ЃR�[�h�@@�F�@@get�،���ЃR�[�h�i�j�̖߂�l 
        String l_strQueryCondition = this.createHostTableQueryCondition(
            l_request, l_strInstitutionCode);
        
        //1.10 create�L���[�e�[�u�����������f�[�^(WEB3GenRequest, String)
        //[create�L���[�e�[�u�����������f�[�^�i�j�Ɏw�肷�����] 
        //���N�G�X�g�I�u�W�F�N�g�@@�F�@@�Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g�N���X 
        //�،���ЃR�[�h�@@�F�@@get�،���ЃR�[�h�i�j�̖߂�l
        Object[] l_conditionDatas = this.createHostTableQueryConditionData(
            l_request, l_strInstitutionCode);
        
        //1.11 get�L���[�e�[�u�����R�[�h(String, Object[], RowType, String)
        //[get�L���[�e�[�u�����R�[�h�i�j�Ɏw�肷�����] 
        //���������@@�F�@@create�L���[�e�[�u�����������i�j�̖߂�l 
        //���������f�[�^�@@�F�@@create�L���[�e�[�u�����������f�[�^�i�j�̖߂�l 
        //�L���[�e�[�u��RowType�@@�F�@@getRowType�i�j�̖߂�l 
        //�\�[�g�����@@�F�@@create�L���[�e�[�u���\�[�g�����i�j�̖߂�l
        List l_lstHostTableRecord = 
            l_hostTableDataManager.getHostTableRecord(
                l_strQueryCondition,
                l_conditionDatas,
                l_rowType,
                l_strSortCondition);
        
        //1.12 create�L���[�e�[�u�����R�[�h�ڍ�(List, String, String)
        //[create�L���[�e�[�u�����R�[�h�ڍׁi�j�Ɏw�肷�����] 
        //�L���[�e�[�u�����R�[�hList�@@�F�@@get�L���[�e�[�u�����R�[�h�i�j�̖߂�l 
        //���ʃR�[�h�L���t���O�@@�F�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get���ʃR�[�h�L���t���O 
        //�쐬���t�L���t���O�@@�F�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�쐬���t�L���t���O
        WEB3AdminDirSecHostTableDetail[] l_hostTableDetails =
            l_hostTableDataManager.createHostTableDetails(
            l_lstHostTableRecord,
            l_hostManagementRow.getOrderRequestNumberDiv(),
            l_hostManagementRow.getCreatedTimestampDiv());
        
        //1.13 createResponse( )
        WEB3AdminDirSecHostTableStatusConfirmResponse l_response = 
            (WEB3AdminDirSecHostTableStatusConfirmResponse)l_request.createResponse();
        
        //�e�[�u����    = �@@ get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�e�[�u�����i�j
        l_response.tableJpnName = l_hostManagementRow.getHostTableName();
        
        //�e�[�u���������@@=�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�e�[�u���������i�j
        l_response.tableName = l_hostManagementRow.getHostTablePhysicsName();
        
        //���ʃR�[�h�L���t���O�@@=�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get���ʃR�[�h�L���t���O�i�j
        l_response.identityCodeFlag = l_hostManagementRow.getOrderRequestNumberDiv();
        
        //�쐬���t�L���t���O�@@=�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�쐬���t�L���t���O�i�j
        l_response.createDateFlag = l_hostManagementRow.getCreatedTimestampDiv();
        
        //�L���[�e�[�u�����R�[�h�ڍ� = create�L���[�e�[�u��
        l_response.hostTableDetails = l_hostTableDetails;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�X�V�������)<BR>
     * �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V������ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Ǘ��ҁjget�X�V������ʁv�Q�ƁB <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i�Ǘ��ҁjget�X�V�������     <BR>
     *         ��̈ʒu    :  1.4 isDIR�Ǘ���( )  <BR>
     *         DIR�Ǘ��Ҍ����`�F�b�N �B<BR>
     *        �iisDIR�Ǘ���( ) == false �̏ꍇ�A<BR>
     *         [DIR�Ǘ��Ҍ����`�F�b�N�G���[�B]�Ƃ��ė�O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g�N���X�B<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusCompleteResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4418BC550362
     */
    protected WEB3AdminDirSecHostTableStatusCompleteResponse getUpdateCompleteScreen(
        WEB3AdminDirSecHostTableStatusCompleteRequest l_request) 
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getUpdateCompleteScreen" +
                "(WEB3AdminDirSecHostTableStatusCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����] 
        //�@@�\�J�e�S���R�[�h�F�V�X�e���Ǘ��i�L���[�e�[�u���X�e�[�^�X�X�V�j 
        //is�X�V�Ftrue 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);
                
        //1.4 isDIR�Ǘ���( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
        
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }
        //1.5 get�،���ЃR�[�h( )   
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 validate����p�X���[�h(�p�X���[�h : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.7 get�L���[�e�[�u���Ǘ�(String)
        //[get�L���[�e�[�u���Ǘ��i�j�Ɏw�肷�����] 
        //�L���[�e�[�u�����@@�F�@@���N�G�X�g�I�u�W�F�N�g.�e�[�u����        
        WEB3AdminDirSecHostTableDataManager l_hostTableDataManager = 
            new WEB3AdminDirSecHostTableDataManager();
        
        HostManagementRow l_hostManagementRow = 
            l_hostTableDataManager.getHostTableManagement(
            l_request.tableJpnName);
        
        //1.8 getRowType(String, String)
        //[����] 
        //�e�[�u���������@@�F�@@���N�G�X�g�I�u�W�F�N�g.�e�[�u�������� 
        //�N�G���v���Z�b�T���@@�F�@@get�L���[�e�[�u���Ǘ�()�Ŏ擾����
        //Row�I�u�W�F�N�g.get�N�G���v���Z�b�T�� 
        RowType l_rowType = l_hostTableDataManager.getRowType(
            l_request.tableName,
            l_hostManagementRow.getQueryprocessorName());
        
        //1.9 create�L���[�e�[�u���Ǘ��\�[�g����(�L���[�e�[�u���\�[�g�L�[�m�n)
        //[create�L���[�e�[�u���\�[�g����( )�Ɏw�肷�����] 
        //�L���[�e�[�u���\�[�g�L�[�@@�F�@@���N�G�X�g�I�u�W�F�N�g.�L���[�e�[�u���\�[�g�L�[[]
        String l_strSortCondition = this.createHostTableSortCondition(
            l_request.sortKeys);
        
        //1.10 create�L���[�e�[�u����������(WEB3GenRequest, String)
        //[create�L���[�e�[�u����������()�Ɏw�肷�����] 
        //���N�G�X�g�I�u�W�F�N�g�@@�F�@@�Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g�N���X 
        //�،���ЃR�[�h�@@�F�@@get�،���ЃR�[�h�i�j�̖߂�l 
        String l_strQueryCondition = this.createHostTableQueryCondition(
            l_request, l_strInstitutionCode);
        
        //1.11 create�L���[�e�[�u�����������f�[�^(WEB3GenRequest, String)
        //[create�L���[�e�[�u�����������f�[�^�i�j�Ɏw�肷�����] 
        //���N�G�X�g�I�u�W�F�N�g�@@�F�@@�Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g�N���X 
        //�،���ЃR�[�h�@@�F�@@get�،���ЃR�[�h�i�j�̖߂�l
        Object[] l_conditionDatas = this.createHostTableQueryConditionData(
            l_request, l_strInstitutionCode);
        
        //1.12 get�L���[�e�[�u�����R�[�h(String, Object[], RowType, String)
        //[get�L���[�e�[�u�����R�[�h�i�j�Ɏw�肷�����] 
        //���������@@�F�@@create�L���[�e�[�u�����������i�j�̖߂�l 
        //���������f�[�^�@@�F�@@create�L���[�e�[�u�����������f�[�^�i�j�̖߂�l 
        //�L���[�e�[�u��RowType�@@�F�@@getRowType�i�j�̖߂�l 
        //�\�[�g�����@@�F�@@create�L���[�e�[�u���\�[�g�����i�j�̖߂�l
        List l_lstHostTableRecord = 
            l_hostTableDataManager.getHostTableRecord(
                l_strQueryCondition,
                l_conditionDatas,
                l_rowType,
                l_strSortCondition);
        
        //1.13 get�X�e�[�^�XMap(String)
        //[get�X�e�[�^�XMap�i�j�Ɏw�肷�����] 
        //�X�e�[�^�X�@@�F�@@���N�G�X�g�I�u�W�F�N�g.�X�V��X�e�[�^�X
        Map l_mapStatusMap = l_hostTableDataManager.getStatusMap(
            l_request.updateStatus);
        
        //1.14 update�L���[�e�[�u�����R�[�h(String, Object[], RowType, Map)
        //[update�L���[�e�[�u�����R�[�h�i�j�Ɏw�肷�����] 
        //���������@@�F�@@create���������i�j�Ŏ擾�����l 
        //���������f�[�^�@@�F�@@create���������f�[�^�i�j�Ŏ擾�����l 
        //�L���[�e�[�u��RowType�@@�F�@@getRowType�i�j�Ŏ擾�����l 
        //�X�V�X�e�[�^�X�@@�F�@@get�X�e�[�^�XMap�i�j�Ŏ擾�����l 
        l_hostTableDataManager.updateHostTableRecord(
            l_strQueryCondition,
            l_conditionDatas,
            l_rowType,
            l_mapStatusMap);
        
        //1.15 create�L���[�e�[�u�����R�[�h�ڍ�(List, String, String)
        //[create�L���[�e�[�u�����R�[�h�ڍׁi�j�Ɏw�肷�����] 
        //�L���[�e�[�u�����R�[�hList�@@�F�@@get�L���[�e�[�u�����R�[�h�i�j�̖߂�l 
        //���ʃR�[�h�L���t���O�@@�F�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get���ʃR�[�h�L���t���O 
        //�쐬���t�L���t���O�@@�F�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�쐬���t�L���t���O
        WEB3AdminDirSecHostTableDetail[] l_hostTableDetails =
            l_hostTableDataManager.createHostTableDetails(
            l_lstHostTableRecord,
            l_hostManagementRow.getOrderRequestNumberDiv(),
            l_hostManagementRow.getCreatedTimestampDiv());
        
        //1.16 createResponse( )
        WEB3AdminDirSecHostTableStatusCompleteResponse l_response = 
            (WEB3AdminDirSecHostTableStatusCompleteResponse)l_request.createResponse();
        
        //�e�[�u����    = �@@ get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�e�[�u�����i�j
        l_response.tableJpnName = l_hostManagementRow.getHostTableName();
        
        //�e�[�u���������@@=�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�e�[�u���������i�j
        l_response.tableName = l_hostManagementRow.getHostTablePhysicsName();
        
        //���ʃR�[�h�L���t���O�@@=�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get���ʃR�[�h�L���t���O�i�j
        l_response.identityCodeFlag = l_hostManagementRow.getOrderRequestNumberDiv();
        
        //�쐬���t�L���t���O�@@=�@@get�L���[�e�[�u���Ǘ��i�j�̖߂�l.get�쐬���t�L���t���O�i�j
        l_response.createDateFlag = l_hostManagementRow.getCreatedTimestampDiv();
        
        //�L���[�e�[�u�����R�[�h�ڍ� = create�L���[�e�[�u��
        l_response.hostTableDetails = l_hostTableDetails;
        
        log.exiting(STR_METHOD_NAME);   
        return l_response;
    }
    
    /**
     * (create�L���[�e�[�u���Ǘ��\�[�g����)<BR>
     * �L���[�e�[�u���Ǘ������\�[�g�������쐬����B <BR>
     * <BR>
     * �P�j�@@�\�[�g����������I�u�W�F�N�g(�FString)���쐬����B <BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�\�[�g�L�[ == null�̏ꍇ�A <BR>
     * <BR>
     * �@@�@@"�e�[�u����"�̃\�[�g������ԋp����B <BR>
     * <BR>
     * �R�j�@@�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B <BR>
     * <BR>
     * �@@�R�|�P�j�@@�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>
     * �@@�@@�@@�@@�@@�@@�쐬�����\�[�g����������ɒǉ�����B <BR>
     * <BR>
     * �@@�E�u�e�[�u�����v�@@���@@�L���[�e�[�u���Ǘ�.�e�[�u���� <BR>
     * �@@�E�u�e�[�u���������v�@@���@@�L���[�e�[�u���Ǘ�.�e�[�u��������  <BR>
     * <BR>
     * �@@�R�|�Q�j�@@�\�[�g�L�[.�����^�~���ɑΉ�����擾����(asc or desc)<BR>
     * �@@�@@�@@�@@�@@�@@���\�[�g����������ɒǉ�����B <BR>
     * <BR>
     * �S�j�@@�쐬�����\�[�g�����������ԋp����B <BR>
     * @@param l_hostTableSortKeys - (�L���[�e�[�u���\�[�g�L�[)<BR>
     * �L���[�e�[�u���\�[�g�L�[�N���X�B<BR>
     * @@return String
     * @@roseuid 4418EE5C02A6
     */
    private String createHostManageSortCondition(
        WEB3AdminDirSecSortKey[] l_hostTableSortKeys)
    {
        final String STR_METHOD_NAME = "createHostManageSortCondition(WEB3AdminDirSecSortKey[])";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�\�[�g����������I�u�W�F�N�g(�FString)���쐬����B
        StringBuffer l_sbSortQueryString = new StringBuffer();
        //�Q�j�p�����[�^.�\�[�g�L�[ == null�̏ꍇ�A"�e�[�u����"�̃\�[�g������ԋp����B
        if (l_hostTableSortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            l_sbSortQueryString.append("host_table_name ");
            return l_sbSortQueryString.toString();
        }

        //�R�j�@@�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        for(int i = 0; i < l_hostTableSortKeys.length; i++)
        {
            //�R�|�P�j�@@�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A�쐬�����\�[�g����������ɒǉ�����B
            if(WEB3AdminDirSecSortKeyItemDef.HOST_TABLE_NAME.equals(
                l_hostTableSortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("host_table_name ");
            }
            else if(WEB3AdminDirSecSortKeyItemDef.HOST_TABLE_PHYSICS_NAME.equals(
                l_hostTableSortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("host_table_physics_name ");
            }
            else 
            {
                continue;
            }
            //�R�|�Q�j�@@�\�[�g�L�[.�����^�~���ɑΉ�����擾����(asc or desc)���\�[�g����������ɒǉ�����B
            if (WEB3AscDescDef.ASC.equals(l_hostTableSortKeys[i].ascDesc))
            {
                l_sbSortQueryString.append("asc,");
            }
            if (WEB3AscDescDef.DESC.equals(l_hostTableSortKeys[i].ascDesc))
            {
                l_sbSortQueryString.append("desc,");
            }
        }
        String l_strSortString = l_sbSortQueryString.toString();
        
        if ((!"".equals(l_strSortString)) && 
            l_strSortString.charAt(l_strSortString.length()-1) == ',')
        {
            l_strSortString = l_strSortString.substring(0,l_strSortString.length()-1);
        }
        log.exiting(STR_METHOD_NAME);
        return l_strSortString;
    }

    /**
     * (create�L���[�e�[�u���\�[�g����)<BR>
     * �L���[�e�[�u�������\�[�g�������쐬����B <BR>
     * <BR>
     * �P�j�@@�\�[�g����������I�u�W�F�N�g(�FString)���쐬����B <BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�\�[�g�L�[ == null�̏ꍇ�A <BR>
     * <BR>
     * �@@�@@"���ʃR�[�h"�̃\�[�g������ԋp����B <BR>
     * <BR>
     * �R�j�@@�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B <BR>
     * <BR>
     * �@@�R�|�P�j�@@�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>
     *             �쐬�����\�[�g����������ɒǉ�����B <BR>
     * <BR>
     * �@@�E�u���ʃR�[�h�v�@@���@@�L���[�e�[�u��.���ʃR�[�h<BR>
     * �@@�E�u�X�e�[�^�X�v�@@���@@�L���[�e�[�u��.�X�e�[�^�X<BR>
     * �@@�E�u�쐬���t�v�@@���@@�L���[�e�[�u��.�쐬���t<BR>
     * <BR>
     * <BR>
     * �@@�R�|�Q�j�@@�\�[�g�L�[.�����^�~���ɑΉ�����擾����(asc or desc)��<BR>
     *              �\�[�g����������ɒǉ�����B <BR>
     * <BR>
     * �S�j�@@�쐬�����\�[�g�����������ԋp����B <BR>
     * @@param l_hostTableSortKeys - (�L���[�e�[�u���\�[�g�L�[)<BR>
     * �L���[�e�[�u���\�[�g�L�[�B<BR>
     * @@return String
     * @@roseuid 44210D0402DD
     */
    private String createHostTableSortCondition(WEB3AdminDirSecSortKey[] l_hostTableSortKeys)
    {
        final String STR_METHOD_NAME = " createHostTableSortCondition(WEB3AdminDirSecSortKey[])";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�\�[�g����������I�u�W�F�N�g(�FString)���쐬����B
        StringBuffer l_sbSortQueryString = new StringBuffer();
        //�Q�j�p�����[�^.�\�[�g�L�[ == null�̏ꍇ�A"���ʃR�[�h"�̃\�[�g������ԋp����B
        if (l_hostTableSortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            l_sbSortQueryString.append(" order_request_number ");
            return l_sbSortQueryString.toString();
        }

        //�R�j�@@�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        for(int i = 0; i < l_hostTableSortKeys.length; i++)
        {
            //�R�|�P�j�@@�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A�쐬�����\�[�g����������ɒǉ�����B
            if(WEB3AdminDirSecSortKeyItemDef.ORDER_REQUEST_NUMBER.equals(
                l_hostTableSortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("order_request_number ");
            }
            else if(WEB3AdminDirSecSortKeyItemDef.BEFORESTATUS.equals(
                l_hostTableSortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("status ");
            }
            else if(WEB3AdminDirSecSortKeyItemDef.CREATED_TIMESTAMP.equals(
                l_hostTableSortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("created_timestamp ");
            }
            else
            {
                continue;
            }
            //�R�|�Q�j�@@�\�[�g�L�[.�����^�~���ɑΉ�����擾����(asc or desc)���\�[�g����������ɒǉ�����B
            if (WEB3AscDescDef.ASC.equals(l_hostTableSortKeys[i].ascDesc))
            {
                l_sbSortQueryString.append("asc,");
            }
            if (WEB3AscDescDef.DESC.equals(l_hostTableSortKeys[i].ascDesc))
            {
                l_sbSortQueryString.append("desc,");
            }
        }
        
        String l_strSortString = l_sbSortQueryString.toString();
        
        if ((!"".equals(l_strSortString)) && 
            l_strSortString.charAt(l_strSortString.length()-1) == ',')
        {
            l_strSortString = l_strSortString.substring(0,l_strSortString.length()-1);
        }
        log.exiting(STR_METHOD_NAME);
        return l_strSortString;
    }

    /**
     * (create�L���[�e�[�u����������)<BR>
     * �L���[�e�[�u���̌���������������쐬����B <BR>
     * <BR>
     * �P�j�@@��������������C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@����:�،���ЃR�[�h != null�̏ꍇ�A<BR>
     * �@@�@@�،���ЃR�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@"institution_code = ? "<BR>
     * <BR>
     * �R�j�@@����:���N�G�X�g�I�u�W�F�N�g.���X�R�[�h != null�̏ꍇ�A <BR>
     * �@@�@@ ���X�R�[�h����������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and branch_code = ? " <BR>
     * <BR>
     * �S�j�@@����:���N�G�X�g�I�u�W�F�N�g.���ʃR�[�h != null�̏ꍇ�A <BR>
     * �@@�@@ ���ʃR�[�h����������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and order_request_number like ? " <BR>
     * <BR>
     * �T�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tFrom != null�̏ꍇ�A <BR>
     * �@@�@@ �쐬���tFrom����������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and<BR> TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') >= ? " <BR>
     * <BR>
     * �U�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tTo != null�̏ꍇ�A <BR>
     * �@@�@@ �쐬���tTo����������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and<BR> TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') <= ? "<BR>
     * <BR>
     * �V�j�@@����:���N�G�X�g�I�u�W�F�N�g.�X�e�[�^�X != null�̏ꍇ�A <BR>
     * �@@�@@ �X�e�[�^�X����������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and status= ? " <BR>
     * <BR>
     * �W�j�@@�쐬������������������C���X�^���X��ԋp����B <BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>
     * ���N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4421492300FC
     */
    private String createHostTableQueryCondition(
        WEB3GenRequest l_request, String l_strInstitutionCode)
        throws WEB3BaseException
    {        
        final String STR_METHOD_NAME = 
            " createHostTableQueryCondition(WEB3GenRequest,String)";
        log.entering(STR_METHOD_NAME);
    
        //�P�j�@@��������������C���X�^���X�𐶐�����B
        StringBuffer l_sbSortQueryString = new StringBuffer();
        //�Q�j�@@����:�،���ЃR�[�h != null�̏ꍇ�A�،���ЃR�[�h����������������ɒǉ�����B
        if(l_strInstitutionCode != null)
        {
            l_sbSortQueryString.append("institution_code = ? ");
        }
    
        if (l_request instanceof WEB3AdminDirSecHostTableSearchListRequest)
        {
            WEB3AdminDirSecHostTableSearchListRequest l_tempRequest =
                (WEB3AdminDirSecHostTableSearchListRequest) l_request;
            //�R�j�@@����:���N�G�X�g�I�u�W�F�N�g.���X�R�[�h != null�̏ꍇ�A
            //      ���X�R�[�h����������������ɒǉ�����B
            if(l_tempRequest.branchCode != null)
            {
                l_sbSortQueryString.append("and branch_code = ? ");
            }
            //�S�j����:���N�G�X�g�I�u�W�F�N�g.���ʃR�[�h != null�̏ꍇ�A
            //    ���ʃR�[�h����������������ɒǉ�����B
            if(l_tempRequest.identityCode != null)
            {
                l_sbSortQueryString.append("and order_request_number like ? ");
            }
            //�T�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tFrom != null�̏ꍇ�A
            //      �쐬���tFrom����������������ɒǉ�����B
            if(l_tempRequest.createDateFrom != null)
            {
                l_sbSortQueryString.append(
                    "and TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') >= ? ");
            }
            //�U�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tTo != null�̏ꍇ�A
            //      �쐬���tTo����������������ɒǉ�����B
            if(l_tempRequest.createDateTo != null)
            {
                l_sbSortQueryString.append(
                    "and TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') <= ? ");
            }
            //�V�j�@@����:���N�G�X�g�I�u�W�F�N�g.�X�e�[�^�X != null�̏ꍇ�A
            //      �X�e�[�^�X����������������ɒǉ�����B
            if(l_tempRequest.status != null)
            {
                l_sbSortQueryString.append("and status= ? ");
            }
        }
        else if (l_request instanceof WEB3AdminDirSecHostTableStatusConfirmRequest)
        {
            WEB3AdminDirSecHostTableStatusConfirmRequest l_tempRequest =
                (WEB3AdminDirSecHostTableStatusConfirmRequest) l_request;
            //�R�j�@@����:���N�G�X�g�I�u�W�F�N�g.���X�R�[�h != null�̏ꍇ�A
            //      ���X�R�[�h����������������ɒǉ�����B
            if(l_tempRequest.branchCode != null)
            {
                l_sbSortQueryString.append("and branch_code = ? ");
            }
            //�S�j����:���N�G�X�g�I�u�W�F�N�g.���ʃR�[�h != null�̏ꍇ�A
            //    ���ʃR�[�h����������������ɒǉ�����B
            if(l_tempRequest.identityCode != null)
            {
                l_sbSortQueryString.append("and order_request_number like ? ");
            }
            //�T�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tFrom != null�̏ꍇ�A
            //      �쐬���tFrom����������������ɒǉ�����B
            if(l_tempRequest.createDateFrom != null)
            {
                l_sbSortQueryString.append(
                    "and TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') >= ? ");
            }
            //�U�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tTo != null�̏ꍇ�A
            //      �쐬���tTo����������������ɒǉ�����B
            if(l_tempRequest.createDateTo != null)
            {
                l_sbSortQueryString.append(
                    "and TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') <= ? ");
            }
            //�V�j�@@����:���N�G�X�g�I�u�W�F�N�g.�X�e�[�^�X != null�̏ꍇ�A
            //      �X�e�[�^�X����������������ɒǉ�����B
            if(l_tempRequest.status != null)
            {
                l_sbSortQueryString.append("and status= ? ");
            }
        }
        else if(l_request instanceof WEB3AdminDirSecHostTableStatusCompleteRequest)
        {
            WEB3AdminDirSecHostTableStatusCompleteRequest l_tempRequest =
                (WEB3AdminDirSecHostTableStatusCompleteRequest) l_request;
            //�R�j�@@����:���N�G�X�g�I�u�W�F�N�g.���X�R�[�h != null�̏ꍇ�A
            //      ���X�R�[�h����������������ɒǉ�����B
            if(l_tempRequest.branchCode != null)
            {
                l_sbSortQueryString.append("and branch_code = ? ");
            }
            //�S�j����:���N�G�X�g�I�u�W�F�N�g.���ʃR�[�h != null�̏ꍇ�A
            //    ���ʃR�[�h����������������ɒǉ�����B
            if(l_tempRequest.identityCode != null)
            {
                l_sbSortQueryString.append("and order_request_number like ? ");
            }
            //�T�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tFrom != null�̏ꍇ�A
            //      �쐬���tFrom����������������ɒǉ�����B
            if(l_tempRequest.createDateFrom != null)
            {
                l_sbSortQueryString.append(
                        "and TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') >= ? ");
            }
            //�U�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tTo != null�̏ꍇ�A
            //      �쐬���tTo����������������ɒǉ�����B
            if(l_tempRequest.createDateTo != null)
            {
                l_sbSortQueryString.append(
                        "and TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') <= ? ");
            }
            //�V�j�@@����:���N�G�X�g�I�u�W�F�N�g.�X�e�[�^�X != null�̏ꍇ�A
            //      �X�e�[�^�X����������������ɒǉ�����B
            if(l_tempRequest.status != null)
            {
                l_sbSortQueryString.append("and status= ? ");
            }
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
    //�W�j�@@�쐬������������������C���X�^���X��ԋp����B
    return l_sbSortQueryString.toString();
    }

    /**
     * (create�L���[�e�[�u�����������f�[�^)<BR>
     * ���������f�[�^�R���e�i��ҏW����B <BR>
     * <BR>
     * <BR>
     * �P�j�@@ArrayList�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����:�،���ЃR�[�h != null�̏ꍇ�A<BR>
     * �@@�@@�،���ЃR�[�h��List�I�u�W�F�N�g�ɒǉ�����B<BR>
     * <BR>
     * �@@List�I�u�W�F�N�g.add(����:�،���ЃR�[�h); <BR>
     * <BR>
     * �R�j�@@����:���N�G�X�g�I�u�W�F�N�g.���X�R�[�h != null�̏ꍇ�A <BR>
     * �@@�@@ ���X�R�[�h��List�I�u�W�F�N�g�ɒǉ�����B <BR>
     * <BR>
     * �@@List�I�u�W�F�N�g.add(����:���N�G�X�g�I�u�W�F�N�g.���X�R�[�h); <BR>
     * <BR>
     * �S�j�@@����:���N�G�X�g�I�u�W�F�N�g.���ʃR�[�h != null�̏ꍇ�A <BR>
     * �@@�@@ ���ʃR�[�h��List�I�u�W�F�N�g�ɒǉ�����B <BR>
     * <BR>
     * �@@List�I�u�W�F�N�g.add(����:���N�G�X�g�I�u�W�F�N�g.���ʃR�[�h); <BR>
     * <BR>
     * �T�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tFrom != null�̏ꍇ�A <BR>
     * �@@�@@ �쐬���tFrom��List�I�u�W�F�N�g�ɒǉ�����B <BR>
     * <BR>
     * �@@List�I�u�W�F�N�g.add(����:���N�G�X�g�I�u�W�F�N�g.�쐬���tFrom); <BR>
     * <BR>
     * �U�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tTo != null�̏ꍇ�A <BR>
     * �@@�@@ �쐬���tTo��List�I�u�W�F�N�g�ɒǉ�����B <BR>
     * <BR>
     * �@@List�I�u�W�F�N�g.add(����:���N�G�X�g�I�u�W�F�N�g.�쐬���tTo); <BR>
     * <BR>
     * �V�j�@@����:���N�G�X�g�I�u�W�F�N�g.�X�e�[�^�X != null�̏ꍇ�A <BR>
     * �@@�@@ �X�e�[�^�X��List�I�u�W�F�N�g�ɒǉ�����B <BR>
     * <BR>
     * �@@List�I�u�W�F�N�g.add(����:���N�G�X�g�I�u�W�F�N�g.�X�e�[�^�X); <BR>
     * <BR>
     * <BR>
     * �W�j�@@Object�^�̔z��I�u�W�F�N�g��ArrayList�I�u�W�F�N�g�̃T�C�Y�Ő�������B<BR>
     * <BR>
     * �X�j�@@toArray()�ŁA���X�g���̗v�f���i�[����z��I�u�W�F�N�g�ɕϊ�����B<BR>
     * <BR>
     * �@@�@@ArrayList�I�u�W�F�N�g.toArray(Object�^�̔z��I�u�W�F�N�g); <BR>
     * <BR>
     * �P�O�j�@@�ϊ������z��I�u�W�F�N�g��ԋp����B <BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>
     * ���N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 44214E080254
     */
    private Object[] createHostTableQueryConditionData(
        WEB3GenRequest l_request, String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createHostTableQueryConditionData(" +
            "WEB3GenRequest, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@ArrayList�I�u�W�F�N�g�𐶐�����B
        List l_lisQueryCondition = new ArrayList();
        //�Q�j�@@����:�،���ЃR�[�h != null�̏ꍇ�A
        //      �،���ЃR�[�h��List�I�u�W�F�N�g�ɒǉ�����B
        if(l_strInstitutionCode != null)
        {
            l_lisQueryCondition.add(l_strInstitutionCode);
        }
        if (l_request instanceof WEB3AdminDirSecHostTableSearchListRequest)
        {
            WEB3AdminDirSecHostTableSearchListRequest l_tempRequest =
                (WEB3AdminDirSecHostTableSearchListRequest) l_request;
            //�R�j�@@����:���N�G�X�g�I�u�W�F�N�g.���X�R�[�h != null�̏ꍇ�A
            //      ���X�R�[�h��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.branchCode != null)
            {
                l_lisQueryCondition.add(l_tempRequest.branchCode);
            }
            //�S�j����:���N�G�X�g�I�u�W�F�N�g.���ʃR�[�h != null�̏ꍇ�A
            //    ���ʃR�[�h��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.identityCode != null)
            {
                l_lisQueryCondition.add(l_tempRequest.identityCode);
            }
            //�T�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tFrom != null�̏ꍇ�A
            //      �쐬���tFrom��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.createDateFrom != null)
            {
                l_lisQueryCondition.add(l_tempRequest.createDateFrom);
            }
            //�U�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tTo != null�̏ꍇ�A
            //      �쐬���tTo��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.createDateTo != null)
            {
                l_lisQueryCondition.add(l_tempRequest.createDateTo);
            }
            //�V�j�@@����:���N�G�X�g�I�u�W�F�N�g.�X�e�[�^�X != null�̏ꍇ�A
            //      �X�e�[�^�X��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.status != null)
            {
                l_lisQueryCondition.add(l_tempRequest.status);
            }
        }
        else if (l_request instanceof WEB3AdminDirSecHostTableStatusConfirmRequest)
        {
            WEB3AdminDirSecHostTableStatusConfirmRequest l_tempRequest =
                (WEB3AdminDirSecHostTableStatusConfirmRequest) l_request;
            //�R�j�@@����:���N�G�X�g�I�u�W�F�N�g.���X�R�[�h != null�̏ꍇ�A
            //      ���X�R�[�h��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.branchCode != null)
            {
                l_lisQueryCondition.add(l_tempRequest.branchCode);
            }
            //�S�j����:���N�G�X�g�I�u�W�F�N�g.���ʃR�[�h != null�̏ꍇ�A
            //    ���ʃR�[�h��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.identityCode != null)
            {
                l_lisQueryCondition.add(l_tempRequest.identityCode);
            }
            //�T�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tFrom != null�̏ꍇ�A
            //      �쐬���tFrom��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.createDateFrom != null)
            {
                l_lisQueryCondition.add(l_tempRequest.createDateFrom);
            }
            //�U�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tTo != null�̏ꍇ�A
            //      �쐬���tTo��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.createDateTo != null)
            {
                l_lisQueryCondition.add(l_tempRequest.createDateTo);
            }
            //�V�j�@@����:���N�G�X�g�I�u�W�F�N�g.�X�e�[�^�X != null�̏ꍇ�A
            //      �X�e�[�^�X��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.status != null)
            {
                l_lisQueryCondition.add(l_tempRequest.status);
            }
        }
        else if(l_request instanceof WEB3AdminDirSecHostTableStatusCompleteRequest)
        {
            WEB3AdminDirSecHostTableStatusCompleteRequest l_tempRequest = 
                (WEB3AdminDirSecHostTableStatusCompleteRequest) l_request;
            //�R�j�@@����:���N�G�X�g�I�u�W�F�N�g.���X�R�[�h != null�̏ꍇ�A
            //      ���X�R�[�h��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.branchCode != null)
            {
                l_lisQueryCondition.add(l_tempRequest.branchCode);
            }
            //�S�j����:���N�G�X�g�I�u�W�F�N�g.���ʃR�[�h != null�̏ꍇ�A
            //    ���ʃR�[�h��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.identityCode != null)
            {
                l_lisQueryCondition.add(l_tempRequest.identityCode);
            }
            //�T�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tFrom != null�̏ꍇ�A
            //      �쐬���tFrom��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.createDateFrom != null)
            {
                l_lisQueryCondition.add(l_tempRequest.createDateFrom);
            }
            //�U�j�@@����:���N�G�X�g�I�u�W�F�N�g.�쐬���tTo != null�̏ꍇ�A
            //      �쐬���tTo��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.createDateTo != null)
            {
                l_lisQueryCondition.add(l_tempRequest.createDateTo);
            }
            //�V�j�@@����:���N�G�X�g�I�u�W�F�N�g.�X�e�[�^�X != null�̏ꍇ�A
            //      �X�e�[�^�X��List�I�u�W�F�N�g�ɒǉ�����B
            if(l_tempRequest.status != null)
            {
                l_lisQueryCondition.add(l_tempRequest.status);
            }
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
        //�W�j Object�^�̔z��I�u�W�F�N�g��ArrayList�I�u�W�F�N�g�̃T�C�Y�Ő�������B
        Object[] l_objQueryConditions = new Object[l_lisQueryCondition.size()];
        //�X�j�@@toArray()�ŁA���X�g���̗v�f���i�[����z��I�u�W�F�N�g�ɕϊ�����B
        l_lisQueryCondition.toArray(l_objQueryConditions);

        log.exiting(STR_METHOD_NAME);
        //�P�O�j�@@�ϊ������z��I�u�W�F�N�g��ԋp����B
        return l_objQueryConditions;
    }
}
@
