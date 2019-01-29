head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�ꗗ�T�[�r�XImpl(WEB3AdminToTradeStopListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04  �A����(���u) �V�K�쐬
                 : 2006/04/12  �A����(���u) �d�l�ύX�E���f��055
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.admintriggerorder.WEB3AdminToDataManager;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInfoUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopListRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopListResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.triggerorder.base.data.TriggerOrderStopRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~�ꗗ�T�[�r�XImpl)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�ꗗ�T�[�r�X�����N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminToTradeStopListServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminToTradeStopListService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopListServiceImpl.class);

    /**
     * @@roseuid 4430D99F02FD
     */
    public WEB3AdminToTradeStopListServiceImpl() 
    {
     
    }
    
    /**
     * �戵��~�ꗗ�������s���B<BR>
     * <BR>
     * this.get�ꗗ���()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410117401E8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
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
        if (l_request instanceof WEB3AdminToTradeStopListRequest)
        {
            l_response = this.getListScreen((WEB3AdminToTradeStopListRequest) l_request);
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
     * (get�ꗗ���)<BR>
     * �戵��~�ꗗ�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�戵��~�ꗗ�T�[�r�X�jget�ꗗ��ʁv�Q�ƁB<BR>
     * ============================================================== <BR>
     *    �V�[�P���X�}�F�u�i�g���K�[�����Ǘ��ҁE�戵��~�ꗗ�T�[�r�X�jget�ꗗ��ʁv<BR>
     *    ��̈ʒu�F1.7.2 (*)��L�ȊO�̏ꍇ<BR>
     *    �u�����ɊY������f�[�^�����݂��Ȃ��B�v��<BR>
     *    �Ɩ��G���[���X���[����B<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_01037<BR>
     * ============================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410124A01F5
     */
    protected WEB3AdminToTradeStopListResponse getListScreen(WEB3AdminToTradeStopListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminToTradeStopListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, false);
 
        //1.4 validate���X����(���X�R�[�h : String[])
        l_admin.validateBranchPermission(l_request.branchCode);
               
        //1.5 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 get���ꎷ�s�����戵��~�ꗗ(String, String[], String, 
        //String, �戵��~�\�[�g�L�[[])
        List l_lisConditions = WEB3AdminToDataManager.getTriggerOrderStopList(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.tradeStopDiv,
            l_request.productCode,
            l_request.sortKeys);
        
        WEB3AdminToTradeStopListResponse l_response = null;
        //1.7 (*)get���ꎷ�s�����戵��~�ꗗ()�̖߂�l == null�̏ꍇ
        if (l_lisConditions == null || l_lisConditions.size() == 0)
        {
            //1.7.1 (*)�y�[�W���O���s���ꍇ�i���N�G�X�g�f�[�^.�v���y�[�W�ԍ� != null 
            //���� ���N�G�X�g�f�[�^.�y�[�W���\���s�� != null�j�̏ꍇ
            if (WEB3StringTypeUtility.isNotEmpty(l_request.pageIndex)
                && WEB3StringTypeUtility.isNotEmpty(l_request.pageSize))
            {
                //1.7.1.1 createResponse( )
                l_response = (WEB3AdminToTradeStopListResponse) l_request.createResponse();
                l_response.currentTime = GtlUtils.getSystemTimestamp();
                
                //1.7.1.2
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
           
            //1.7.2 (*)��L�ȊO�̏ꍇ
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }
        
        //1.8 (*)�y�[�W���O���s���ꍇ�i���N�G�X�g�f�[�^.�v���y�[�W�ԍ� != null 
        //���� ���N�G�X�g�f�[�^.�y�[�W���\���s�� != null�j�̏ꍇ
        WEB3PageIndexInfo l_pageIndexInfo = null;
        TriggerOrderStopRow[] l_rows = null;
        if (WEB3StringTypeUtility.isNotEmpty(l_request.pageIndex)
            && WEB3StringTypeUtility.isNotEmpty(l_request.pageSize))
        {
            //1.8.1 WEB3PageIndexInfo(arg0 : List, arg1 : int, arg2 : int)
            l_pageIndexInfo = new WEB3PageIndexInfo(
                l_lisConditions,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));
            
            //1.8.2 getArrayReturned(arg0 : Class)
            l_rows = (TriggerOrderStopRow[]) l_pageIndexInfo.getArrayReturned(TriggerOrderStopRow.class);
        }
        else
        {
            l_rows = new TriggerOrderStopRow[l_lisConditions.size()];
            l_lisConditions.toArray(l_rows);
        }
        
        //1.9 get�،����( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.10 create�戵��~���ꗗ(�،����, ���ꎷ�s�����戵��~Row[])
        WEB3AdminToTradeStopInfoUnit[] l_tradeStopInfoUnits =
            WEB3AdminToDataManager.createTradeStopInfoList(l_institution, l_rows);
        
        //1.11 createResponse( )
        l_response = (WEB3AdminToTradeStopListResponse) l_request.createResponse();
        
        //1.12 (*)�v���p�e�B�Z�b�g
        //���ݎ���        ���@@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        if (l_pageIndexInfo != null)
        {
            //���y�[�W��   ���@@(*1)WEB3PageIndexInfo.getTotalPages()
            l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
            //�����R�[�h��  ���@@(*1)WEB3PageIndexInfo.getTotalRecords()
            l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());
            //�\���y�[�W�ԍ� ���@@(*1)WEB3PageIndexInfo.getPageIndex()
            l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex()); 
        }
        
        //�戵��~���ꗗ    ���@@create�戵��~���ꗗ()�̖߂�l
        l_response.tradeStopInfoList = l_tradeStopInfoUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
