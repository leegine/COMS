head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݏ����w��ꗗ�T�[�r�XImpl (WEB3AdminAccInfoCampaignAccOpenListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 ���؎q (���u) �V�K�쐬
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon;
import webbroker3.accountinfo.define.WEB3AccInfoUpdateFlagDef;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignAccOpenListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�ݏ����w��ꗗ�T�[�r�XImpl)<BR>
 * �����J�ݏ����w��ꗗ�T�[�r�XImpl<BR>
 * <BR>
 * @@author ���؎q<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoCampaignAccOpenListServiceImpl implements WEB3AdminAccInfoCampaignAccOpenListService
{
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenListServiceImpl.class);

    /**
     * @@roseuid 45C08B52033C
     */
    public WEB3AdminAccInfoCampaignAccOpenListServiceImpl()
    {

    }

    /**
     * �����J�ݏ����w��ꗗ�\�����������{����B<BR>
     * <BR>
     * �@@�|get�ꗗ���()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 45AB0467020A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".execute(l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        //�����̃��N�G�X�g�f�[�^���A �Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ꗗظ��ăf�[�^�I�u�W�F�N�g�̏ꍇ
        if(l_request instanceof WEB3AdminAccInfoCampaignAccOpenListRequest)
        {
            l_response = this.getListScreen((WEB3AdminAccInfoCampaignAccOpenListRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ꗗ���)<BR>
     * �����J�ݏ����w��ꗗ��ʕ\�����s���B <BR>
     * <BR>
     * �V�[�P���X�}  <BR>
     * �u�Ǘ��҂��q�l���i�����J�ݏ����w��ꗗ�jget�ꗗ��ʁv�Q�ƁB  <BR>
     * @@param l_request - �Ǘ��҂��q�l���萔����������߰�<BR>
     * �����J�ݏ����ꗗظ��ăf�[�^�I�u�W�F�N�g<BR>
     *
     * @@return WEB3AdminAccInfoCampaignAccOpenListResponse
     * @@throws WEB3BaseException
     * @@roseuid 45AB4A33000E
     */
    protected WEB3AdminAccInfoCampaignAccOpenListResponse getListScreen(
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getListScreen(l_request)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN, false);

        // validate���X����(String)
        l_administrator.validateBranchPermission(l_request.campaignSearchItem.branchCode);

        // get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // �萔�������L�����y�[�����ʃT�[�r�X
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon = WEB3AdminAccInfoCampaignCommon.getInstance();

        // �o�^�^�C�v
        String[] l_strUpdateFlag = {WEB3AccInfoUpdateFlagDef.LOGIN};

        // get�L�����y�[���ꗗ(WEB3GenRequest, String, String[])
        WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfo =
            l_accInfoCampaignCommon.getCampaignList(
            l_request, l_strInstitutionCode, l_strUpdateFlag);

        // get�����R�[�h����(�萔�������L�����y�[����������, String, String[])
        int l_intAllRecordCount =
            l_accInfoCampaignCommon.getAllRecordCount(
            l_request.campaignSearchItem, l_strInstitutionCode, l_strUpdateFlag);

        // createResponse()
        WEB3AdminAccInfoCampaignAccOpenListResponse l_response =
            (WEB3AdminAccInfoCampaignAccOpenListResponse)l_request.createResponse();

        // �v���p�e�B�Z�b�g
        int l_pageSize = Integer.parseInt(l_request.pageSize);

        if (l_pageSize < 1)
        {
        	l_pageSize = l_intAllRecordCount;
        }

        if (l_intAllRecordCount == 0)
        {
            l_response.totalPages = "" + 0;
        }
        else if (l_intAllRecordCount % l_pageSize == 0)
        {
            l_response.totalPages = "" + l_intAllRecordCount / l_pageSize;
        }
        else
        {
            l_response.totalPages = "" + (l_intAllRecordCount / l_pageSize + 1);
        }

        l_response.accopenConditionInfo = l_accInfoCampaignInfo;
        l_response.totalRecords = "" + l_intAllRecordCount;
        l_response.pageIndex = l_request.pageIndex;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
