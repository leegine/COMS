head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.18.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginRejectIPManagementServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��O�C������IP�Ǘ��T�[�r�XImpl(WEB3AdminTMLoginRejectIPManagementServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 ���z(���u) �V�K�쐬 ���f�� No.004 006
Revision History : 2008/09/24 �����F(���u) DB�X�V�d�l 002
Revision History : 2008/10/10 ���z(���u) �d�l�ύX ���f�� No.016
*/

package webbroker3.trademanagement.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3LoginRegistDivDef;
import webbroker3.common.define.WEB3LoginRejectIpStatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3UpdatedDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.LoginRejectIpDao;
import webbroker3.gentrade.data.LoginRejectIpParams;
import webbroker3.gentrade.data.LoginRejectIpRow;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginRejectIPManagementService;
import webbroker3.trademanagement.define.WEB3AdminTMKeyItemDef;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlReferenceUnit;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlSortKey;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteCompleteResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�Ǘ��҃��O�C������IP�Ǘ��T�[�r�XImpl)<BR>
 * �Ǘ��҃��O�C������IP�Ǘ��T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ���z
 * @@version 1.0
 */
public class WEB3AdminTMLoginRejectIPManagementServiceImpl implements WEB3AdminTMLoginRejectIPManagementService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginRejectIPManagementServiceImpl.class);

    /**
     * @@roseuid 48D75CD80250
     */
    public WEB3AdminTMLoginRejectIPManagementServiceImpl()
    {

    }

    /**
     * �Ǘ��҃��O�C������IP�Ǘ��T�[�r�X�������s���B<BR>
     * <BR>
     * �����̃��N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE���O�C������IP�ꗗ���N�G�X�g�̏ꍇ<BR>
     * �@@this.get�ꗗ���()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE���O�C������IP�o�^���N�G�X�g�̏ꍇ<BR>
     * �@@this.get�o�^���()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE���O�C������IP�o�^�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.validate�o�^()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE���O�C������IP�o�^�������N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�o�^()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE���O�C������IP�o�^���ύX���N�G�X�g�̏ꍇ<BR>
     * �@@this.get�ύX���()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE���O�C������IP�o�^���ύX�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.validate�ύX()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE���O�C������IP�o�^���ύX�������N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�ύX()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE���O�C������IP�o�^���폜�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.validate�폜()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE���O�C������IP�o�^���폜�������N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�폜()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE15EA00F2
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        //�����̃��N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B
        //���Ǘ��ҁE���O�C������IP�ꗗ���N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminTraderAdminIPControlListRequest)
        {
            //this.get�ꗗ���()���R�[������B
            l_response = getListScreen((WEB3AdminTraderAdminIPControlListRequest)l_request);
        }
        //���Ǘ��ҁE���O�C������IP�o�^���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminTraderAdminIPControlRegistInputRequest)
        {
            //this.get�o�^���()���R�[������B
            l_response = getRegistScreen((WEB3AdminTraderAdminIPControlRegistInputRequest)l_request);
        }
        //���Ǘ��ҁE���O�C������IP�o�^�m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminTraderAdminIPControlRegistConfirmRequest)
        {
            //this.validate�o�^()���R�[������B
            l_response = validateRegist((WEB3AdminTraderAdminIPControlRegistConfirmRequest)l_request);
        }
        //���Ǘ��ҁE���O�C������IP�o�^�������N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminTraderAdminIPControlRegistCompleteRequest)
        {
            //this.submit�o�^()���R�[������B
            l_response = submitRegist((WEB3AdminTraderAdminIPControlRegistCompleteRequest)l_request);
        }
        //���Ǘ��ҁE���O�C������IP�o�^���ύX���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminTraderAdminIPControlUpdateInputRequest)
        {
            //this.get�ύX���()���R�[������B
            l_response = getChangedScreen((WEB3AdminTraderAdminIPControlUpdateInputRequest)l_request);
        }
        //���Ǘ��ҁE���O�C������IP�o�^���ύX�m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminTraderAdminIPControlUpdateConfirmRequest)
        {
            //this.validate�ύX()���R�[������B
            l_response = validateChange((WEB3AdminTraderAdminIPControlUpdateConfirmRequest)l_request);
        }
        //���Ǘ��ҁE���O�C������IP�o�^���ύX�������N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminTraderAdminIPControlUpdateCompleteRequest)
        {
            //this.submit�ύX()���R�[������B
            l_response = submitChange((WEB3AdminTraderAdminIPControlUpdateCompleteRequest)l_request);
        }
        //���Ǘ��ҁE���O�C������IP�o�^���폜�m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminTraderAdminIPControlDeleteConfirmRequest)
        {
            //this.validate�폜()���R�[������B
            l_response = validateDelete((WEB3AdminTraderAdminIPControlDeleteConfirmRequest)l_request);
        }
        //���Ǘ��ҁE���O�C������IP�o�^���폜�������N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminTraderAdminIPControlDeleteCompleteRequest)
        {
            //this.submit�폜()���R�[������B
            l_response = submitDelete((WEB3AdminTraderAdminIPControlDeleteCompleteRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ꗗ���)<BR>
     * ���O�C������IP�ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �ȉ��̃V�[�P���X�}���Q��<BR>
     * �u�i�Ǘ��҃��O�C������IP�Ǘ��jget�ꗗ��ʁv<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���O�C������IP�ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminTraderAdminIPControlListResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE281102E4
     */
    protected WEB3AdminTraderAdminIPControlListResponse getListScreen(
        WEB3AdminTraderAdminIPControlListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getListScreen(WEB3AdminTraderAdminIPControlListRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�����̃`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "C1301"
        //is�X�V�F false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, false);

        //get�،���ЃR�[�h( )
        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //createQuerySortCond(�\�[�g�L�[[])
        //���O�C������IP�ꗗ�\�[�g�������쐬����B
        //[create�����\�[�g����()�Ɏw�肷�����]
        //�\�[�g�L�[�@@�F�@@���N�G�X�g�f�[�^.�\�[�g�L�[
        String l_strQuerySortCond = createQuerySortCond(l_request.sortKeys);

        //getLoginRejectIPList(String, String, String, String)
        //���O�C������IP�e�[�u�����烌�R�[�h���擾����B
        //[get���O�C������IP���R�[�h()�Ɏw�肷�����]
        //�����\�[�g���� �F create�����\�[�g����()�̖߂�l
        //�،���ЃR�[�h �F get�،���ЃR�[�h()�̖߂�l
        //�y�[�W���\���s�� �F ���N�G�X�g�f�[�^.�y�[�W���\���s��
        //�v���y�[�W�ԍ� �F ���N�G�X�g�f�[�^.�v���y�[�W�ԍ�
        List l_lisLoginRejectIPs = getLoginRejectIPList(
            l_strQuerySortCond,
            l_strInstitutionCode,
            l_request.pageSize,
            l_request.pageIndex);

        //ArrayList( )
        //���ArrayList�𐶐��B
        List l_lisAdminIPControlReferenceUnits = new ArrayList();

        //�擾�������R�[�h��LOOP����
        int l_intLength = l_lisLoginRejectIPs.size();
        for (int i = 0; i < l_intLength; i++)
        {
            LoginRejectIpRow l_loginRejectIPRow =
                (LoginRejectIpRow)l_lisLoginRejectIPs.get(i);

            LoginRejectIpParams l_loginRejectIPParams =
                new LoginRejectIpParams(l_loginRejectIPRow);

            //createLoginRejectIPList(���O�C������IPParams)
            //���O�C������IP��񃁃b�Z�[�W�f�[�^���쐬����B
            //[create���O�C������IP���()�Ɏw�肷�����]
            //���O�C������IP�s �F get���O�C������IP�ꗗ()�ɂĎ擾�������O�C������IPParams
            WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit =
                createLoginRejectIPList(l_loginRejectIPParams);

            //add(arg0 : Object)
            //���X�g�Ƀ��O�C������IP���I�u�W�F�N�g��ǉ�����B
            //[����]
            //arg0�F create���O�C������IP���()�̖߂�l
            l_lisAdminIPControlReferenceUnits.add(l_adminIPControlReferenceUnit);
        }

        //toArray( )
        //���X�g����z����擾����B
        WEB3AdminTraderAdminIPControlReferenceUnit[] l_adminIPControlReferenceUnits =
            new WEB3AdminTraderAdminIPControlReferenceUnit[l_lisAdminIPControlReferenceUnits.size()];
        l_lisAdminIPControlReferenceUnits.toArray(l_adminIPControlReferenceUnits);

        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisLoginRejectIPs,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        //getPageIndex( )
        //�\���y�[�W�ԍ����擾����B
        int l_intPageIndex = l_pageIndexInfo.getPageIndex();

        //getTotalPages( )
        //���y�[�W�����擾����B
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();

        //getTotalRecords( )
        //�����R�[�h�����擾����B
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTraderAdminIPControlListResponse l_response =
            (WEB3AdminTraderAdminIPControlListResponse)l_request.createResponse();

        //(*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
        //�@@���X�|���X�f�[�^.�\���y�[�W�ԍ�    ���@@getPageIndex()�̖߂�l
        l_response.pageIndex = l_intPageIndex + "";
        //�@@���X�|���X�f�[�^.���y�[�W��      ���@@getTotalPages()�̖߂�l
        l_response.totalPages = l_intTotalPages + "";
        //�@@���X�|���X�f�[�^.�����R�[�h��     ���@@getTotalRecords()�̖߂�l
        l_response.totalRecords = l_intTotalRecords + "";
        //�@@���X�|���X�f�[�^.���O�C������IP�ꗗ ���@@���O�C������IP���̔z��
        l_response.ipControlList = l_adminIPControlReferenceUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�o�^���)<BR>
     * ���O�C������IP�o�^��ʕ\���������s���B<BR>
     * <BR>
     * �ȉ��̃V�[�P���X�}���Q��<BR>
     * �u�i�Ǘ��҃��O�C������IP�Ǘ��jget�o�^��ʁv<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���O�C������IP�o�^���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminTraderAdminIPControlRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE28EE01D7
     */
    protected WEB3AdminTraderAdminIPControlRegistInputResponse getRegistScreen(
        WEB3AdminTraderAdminIPControlRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getRegistScreen(WEB3AdminTraderAdminIPControlRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�����̃`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "C1301"
        //is�X�V�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTraderAdminIPControlRegistInputResponse l_response =
            (WEB3AdminTraderAdminIPControlRegistInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�o�^)<BR>
     * ���O�C������IP�o�^�m�F�������s���B<BR>
     * <BR>
     * �ȉ��̃V�[�P���X�}���Q��<BR>
     * �u�i�Ǘ��҃��O�C������IP�Ǘ��jvalidate�o�^�v<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���O�C������IP�o�^�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminTraderAdminIPControlRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE28EF0264
     */
    protected WEB3AdminTraderAdminIPControlRegistConfirmResponse validateRegist(
        WEB3AdminTraderAdminIPControlRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateRegist(WEB3AdminTraderAdminIPControlRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�����̃`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "C1301"
        //is�X�V�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTraderAdminIPControlRegistConfirmResponse l_response =
            (WEB3AdminTraderAdminIPControlRegistConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�o�^)<BR>
     * ���O�C������IP�o�^�����������s���B<BR>
     * <BR>
     * �ȉ��̃V�[�P���X�}���Q��<BR>
     * �u�i�Ǘ��҃��O�C������IP�Ǘ��jsubmit�o�^�v<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���O�C������IP�o�^�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminTraderAdminIPControlRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE29800362
     */
    protected WEB3AdminTraderAdminIPControlRegistCompleteResponse submitRegist(
        WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitRegist(WEB3AdminTraderAdminIPControlRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�����̃`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "C1301"
        //is�X�V�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //validate����p�X���[�h(�p�X���[�h : String)
        //����p�X���[�h�����������̃`�F�b�N���s���B
        //[validate����p�X���[�h()�Ɏw�肷�����]
        //�p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //get�،���ЃR�[�h( )
        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get�Ǘ��҃R�[�h( )
        //�Ǘ��҃R�[�h���擾����B
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //saveLoginRejectIP(�Ǘ��ҁE���O�C������IP�o�^�������N�G�X�g, String, String)
        //���O�C������IP�e�[�u���Ƀ��R�[�h���쐬����B
        //[save���O�C������IP()�Ɏw�肷�����]
        //���N�G�X�g�f�[�^�F�@@���N�G�X�g�f�[�^
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        //�Ǘ��҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
        saveLoginRejectIP(l_request, l_strInstitutionCode, l_strAdministratorCode);

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTraderAdminIPControlRegistCompleteResponse l_response =
            (WEB3AdminTraderAdminIPControlRegistCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ύX���)<BR>
     * ���O�C������IP�o�^���ύX��ʕ\���������s���B<BR>
     * <BR>
     * �ȉ��̃V�[�P���X�}���Q��<BR>
     * �u�i�Ǘ��҃��O�C������IP�Ǘ��jget�ύX��ʁv<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���O�C������IP�o�^���ύX���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminTraderAdminIPControlUpdateInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE29D802FC
     */
    protected WEB3AdminTraderAdminIPControlUpdateInputResponse getChangedScreen(
        WEB3AdminTraderAdminIPControlUpdateInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getChangedScreen(WEB3AdminTraderAdminIPControlUpdateInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�����̃`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "C1301"
        //is�X�V�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //getLoginRejectIPParams(String, boolean)
        //�����ɊY�����郍�O�C������IPParams��ԋp����B
        //[get���O�C������IP�s()�Ɏw�肷�����]
        //���O�C������ID�F ���N�G�X�g�f�[�^.���O�C������ID
        //���b�N�t���O�F false
        LoginRejectIpParams l_loginRejectIpParams =
            getLoginRejectIPParams(l_request.denyLoginID, false);

        //createLoginRejectIPList(���O�C������IPParams)
        //���O�C������IP��񃁃b�Z�[�W�f�[�^���쐬����B
        //[create���O�C������IP���()�Ɏw�肷�����]
        //���O�C������IP�s �F get���O�C������IP�s()�̖߂�l
        WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit =
            createLoginRejectIPList(l_loginRejectIpParams);

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTraderAdminIPControlUpdateInputResponse l_response =
            (WEB3AdminTraderAdminIPControlUpdateInputResponse)l_request.createResponse();

        //(*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
        //�@@���X�|���X�f�[�^.�X�V�O���@@���@@create���O�C������IP���()�̖߂�l
        l_response.beforeUpdateInfo = l_adminIPControlReferenceUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�ύX)<BR>
     * ���O�C������IP�o�^���ύX�m�F�������s���B<BR>
     * <BR>
     * �ȉ��̃V�[�P���X�}���Q��<BR>
     * �u�i�Ǘ��҃��O�C������IP�Ǘ��jvalidate�ύX�v<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���O�C������IP�o�^���ύX�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminTraderAdminIPControlUpdateConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE29D8030C
     */
    protected WEB3AdminTraderAdminIPControlUpdateConfirmResponse validateChange(
        WEB3AdminTraderAdminIPControlUpdateConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateChange(WEB3AdminTraderAdminIPControlUpdateConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�����̃`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "C1301"
        //is�X�V�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //gget���O�C������IP�s(String, boolean)
        //�����ɊY�����郍�O�C������IPParams��ԋp����B
        //[get���O�C������IP�s()�Ɏw�肷�����]
        //���O�C������ID�F ���N�G�X�g�f�[�^.���O�C������ID
        //���b�N�t���O�F false
        LoginRejectIpParams l_loginRejectIpParams =
            getLoginRejectIPParams(l_request.denyLoginID, false);

        //validate�ύX�L��(���O�C������IPParams, String, Date)
        //���͓��e�ɕύX�����邩�`�F�b�N���s���B
        //[validate�ύX�L��()�Ɏw�肷�����]
        //���O�C������IP�s�F get���O�C������IP�s()�̖߂�l
        //�X�e�[�^�X�F ���N�G�X�g�f�[�^.�X�e�[�^�X
        //�K�p�I�������F ���N�G�X�g�f�[�^.�K�p�I������
        validateIsChange(l_loginRejectIpParams, l_request.status, l_request.endDate);

        //create���O�C������IP���(���O�C������IPParams)
        //���O�C������IP��񃁃b�Z�[�W�f�[�^���쐬����B
        //[create���O�C������IP���()�Ɏw�肷�����]
        //���O�C������IP�s �F get���O�C������IP�s()�̖߂�l
        WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit =
            createLoginRejectIPList(l_loginRejectIpParams);

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTraderAdminIPControlUpdateConfirmResponse l_response =
            (WEB3AdminTraderAdminIPControlUpdateConfirmResponse)l_request.createResponse();

        //(*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
        //�@@���X�|���X�f�[�^.�X�V�O���@@���@@create���O�C������IP���()�̖߂�l
        l_response.beforeUpdateInfo = l_adminIPControlReferenceUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�ύX)<BR>
     * ���O�C������IP�o�^���ύX�����������s���B<BR>
     * <BR>
     * �ȉ��̃V�[�P���X�}���Q��<BR>
     * �u�i�Ǘ��҃��O�C������IP�Ǘ��jsubmit�ύX�v<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���O�C������IP�o�^���ύX�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminTraderAdminIPControlUpdateCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE29D8031C
     */
    protected WEB3AdminTraderAdminIPControlUpdateCompleteResponse submitChange(
        WEB3AdminTraderAdminIPControlUpdateCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitChange(WEB3AdminTraderAdminIPControlUpdateCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�����̃`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "C1301"
        //is�X�V�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //validate����p�X���[�h(�p�X���[�h : String)
        //����p�X���[�h�����������̃`�F�b�N���s���B
        //[validate����p�X���[�h()�Ɏw�肷�����]
        //�p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //get�Ǘ��҃R�[�h( )
        //�Ǘ��҃R�[�h���擾����B
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //gget���O�C������IP�s(String, boolean)
        //�����ɊY�����郍�O�C������IPParams��ԋp����B
        //[get���O�C������IP�s()�Ɏw�肷�����]
        //���O�C������ID�F ���N�G�X�g�f�[�^.���O�C������ID
        //���b�N�t���O�F true
        LoginRejectIpParams l_loginRejectIpParams =
            getLoginRejectIPParams(l_request.denyLoginID, true);

        //validate�ύX�L��(���O�C������IPParams, String, Date)
        //���͓��e�ɕύX�����邩�`�F�b�N���s���B
        //[validate�ύX�L��()�Ɏw�肷�����]
        //���O�C������IP�s�F get���O�C������IP�s()�̖߂�l
        //�X�e�[�^�X�F ���N�G�X�g�f�[�^.�X�e�[�^�X
        //�K�p�I�������F ���N�G�X�g�f�[�^.�K�p�I������
        validateIsChange(l_loginRejectIpParams, l_request.status, l_request.endDate);

        //create���O�C������IP���(���O�C������IPParams)
        //���O�C������IP��񃁃b�Z�[�W�f�[�^���쐬����B
        //[create���O�C������IP���()�Ɏw�肷�����]
        //���O�C������IP�s �F get���O�C������IP�s()�̖߂�l
        WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit =
            createLoginRejectIPList(l_loginRejectIpParams);

        //Map�I�u�W�F�N�g�𐶐�����i���e�͉��L���Q�Ɓj�B
        //DB�X�V�d�l�u���O�C������IP�o�^���ύX_���O�C������IPDB�X�V�d�l.xls�v
        Map l_mapUpdateContents = new HashMap();
        //�X�e�[�^�X
        //���N�G�X�g�f�[�^.�X�e�[�^�X
        l_mapUpdateContents.put("status", l_request.status);
        //�K�p�I������
        //���N�G�X�g�f�[�^.�K�p�I������
        l_mapUpdateContents.put("appli_end_timestamp", l_request.endDate);
        //�X�V�敪
        //"1"�i�Ǘ��ҁj
        l_mapUpdateContents.put("updated_div", WEB3UpdatedDivDef.MANAGER);
        //�X�V�҃R�[�h
        //get�Ǘ��҃R�[�h()�̖߂�l
        l_mapUpdateContents.put("last_updater", l_strAdministratorCode);
        //�X�V���t
        //��������
        l_mapUpdateContents.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        //update���O�C������IP(String, Map)
        //���O�C������IP�e�[�u���̃��R�[�h���X�V����B
        //[update���O�C������IP()�Ɏw�肷�����]
        //���O�C������ID�F ���N�G�X�g�f�[�^.���O�C������ID
        //�X�V���e�F ��������Map�I�u�W�F�N�g
        updateLoginRejectIP(l_request.denyLoginID, l_mapUpdateContents);

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTraderAdminIPControlUpdateCompleteResponse l_response =
            (WEB3AdminTraderAdminIPControlUpdateCompleteResponse)l_request.createResponse();

        //(*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
        //�@@���X�|���X�f�[�^.�X�V�O���@@���@@create���O�C������IP���()�̖߂�l
        l_response.beforeUpdateInfo = l_adminIPControlReferenceUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�폜)<BR>
     * ���O�C������IP�o�^���폜�m�F�������s���B<BR>
     * <BR>
     * �ȉ��̃V�[�P���X�}���Q��<BR>
     * �u�i�Ǘ��҃��O�C������IP�Ǘ��jvalidate�폜�v<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���O�C������IP�o�^���폜�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminTraderAdminIPControlDeleteConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE2A890000
     */
    protected WEB3AdminTraderAdminIPControlDeleteConfirmResponse validateDelete(
        WEB3AdminTraderAdminIPControlDeleteConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateDelete(WEB3AdminTraderAdminIPControlDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�����̃`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "C1301"
        //is�X�V�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //gget���O�C������IP�s(String, boolean)
        //�����ɊY�����郍�O�C������IPParams��ԋp����B
        //[get���O�C������IP�s()�Ɏw�肷�����]
        //���O�C������ID�F ���N�G�X�g�f�[�^.���O�C������ID
        //���b�N�t���O�F false
        LoginRejectIpParams l_loginRejectIpParams =
            getLoginRejectIPParams(l_request.denyLoginID, false);

        //create���O�C������IP���(���O�C������IPParams)
        //���O�C������IP��񃁃b�Z�[�W�f�[�^���쐬����B
        //[create���O�C������IP���()�Ɏw�肷�����]
        //���O�C������IP�s �F get���O�C������IP�s()�̖߂�l
        WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit =
            createLoginRejectIPList(l_loginRejectIpParams);

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTraderAdminIPControlDeleteConfirmResponse l_response =
            (WEB3AdminTraderAdminIPControlDeleteConfirmResponse)l_request.createResponse();

        //(*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
        //�@@���X�|���X�f�[�^.�X�V�O���@@���@@create���O�C������IP���()�̖߂�l
        l_response.beforeUpdateInfo = l_adminIPControlReferenceUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�폜)<BR>
     * ���O�C������IP�o�^���폜�����������s���B<BR>
     * <BR>
     * �ȉ��̃V�[�P���X�}���Q��<BR>
     * �u�i�Ǘ��҃��O�C������IP�Ǘ��jsubmit�폜�v<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���O�C������IP�o�^��폜���������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminTraderAdminIPControlDeleteCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE2A89000F
     */
    protected WEB3AdminTraderAdminIPControlDeleteCompleteResponse submitDelete(
        WEB3AdminTraderAdminIPControlDeleteCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitDelete(WEB3AdminTraderAdminIPControlDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�����̃`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "C1301"
        //is�X�V�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //validate����p�X���[�h(�p�X���[�h : String)
        //����p�X���[�h�����������̃`�F�b�N���s���B
        //[validate����p�X���[�h()�Ɏw�肷�����]
        //�p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //get�Ǘ��҃R�[�h( )
        //�Ǘ��҃R�[�h���擾����B
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //gget���O�C������IP�s(String, boolean)
        //�����ɊY�����郍�O�C������IPParams��ԋp����B
        //[get���O�C������IP�s()�Ɏw�肷�����]
        //���O�C������ID�F ���N�G�X�g�f�[�^.���O�C������ID
        //���b�N�t���O�F true
        LoginRejectIpParams l_loginRejectIpParams =
            getLoginRejectIPParams(l_request.denyLoginID, true);

        //create���O�C������IP���(���O�C������IPParams)
        //���O�C������IP��񃁃b�Z�[�W�f�[�^���쐬����B
        //[create���O�C������IP���()�Ɏw�肷�����]
        //���O�C������IP�s �F get���O�C������IP�s()�̖߂�l
        WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit =
            createLoginRejectIPList(l_loginRejectIpParams);

        //Map�I�u�W�F�N�g�𐶐�����i���e�͉��L���Q�Ɓj�B
        //DB�X�V�d�l�u���O�C������IP�o�^���폜_���O�C������IPDB�X�V�d�l.xls�v
        Map l_mapUpdateContents = new HashMap();
        //�X�e�[�^�X
        //"1"�i����)
        l_mapUpdateContents.put("status", WEB3LoginRejectIpStatusDef.INEFFECTIVE);
        //�X�V�敪
        //"1"�i�Ǘ��ҁj
        l_mapUpdateContents.put("updated_div", WEB3UpdatedDivDef.MANAGER);
        //�X�V�҃R�[�h
        //get�Ǘ��҃R�[�h()�̖߂�l
        l_mapUpdateContents.put("last_updater", l_strAdministratorCode);
        //�X�V���t
        //��������
        l_mapUpdateContents.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        //update���O�C������IP(String, Map)
        //���O�C������IP�e�[�u���̃��R�[�h���X�V����B
        //[update���O�C������IP()�Ɏw�肷�����]
        //���O�C������ID�F ���N�G�X�g�f�[�^.���O�C������ID
        //�X�V���e�F ��������Map�I�u�W�F�N�g
        updateLoginRejectIP(l_request.denyLoginID, l_mapUpdateContents);

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTraderAdminIPControlDeleteCompleteResponse l_response =
            (WEB3AdminTraderAdminIPControlDeleteCompleteResponse)l_request.createResponse();

        //(*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
        //�@@���X�|���X�f�[�^.�X�V�O���@@���@@create���O�C������IP���()�̖߂�l
        l_response.beforeUpdateInfo = l_adminIPControlReferenceUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�����\�[�g����)<BR>
     * ���O�C������IP�ꗗ�\�[�g�������쐬����B<BR>
     * <BR>
     * �P�j�@@�\�[�g����������I�u�W�F�N�g(�FString)���쐬����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�\�[�g�L�[.�L�[���ځ��uIP�A�h���X�v�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̕�������쐬�����\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@"NVL( LPAD( SUBSTR( IP_ADDRESS, 1, INSTR( IP_ADDRESS, '.', 1, 1) -1), 3, '0'), '000') ||<BR>
     * �@@�@@ NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 1) +1,<BR>
     * �@@�@@�@@INSTR( IP_ADDRESS, '.', 1, 2) - INSTR( IP_ADDRESS, '.', 1, 1)-1), 3, '0'), '000') ||<BR>
     * �@@�@@ NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 2) +1,<BR>
     * �@@�@@�@@INSTR( IP_ADDRESS, '.', 1, 3) - INSTR( IP_ADDRESS, '.', 1, 2)-1), 3, '0'), '000') ||<BR>
     * �@@�@@ NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 3) +1), 3, '0'), '000')"<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�Q�|�P�j�ȊO�̏ꍇ�A�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>
     * �@@�@@�쐬�����\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�E�u�K�p�J�n�����v ���@@���O�C������IP.�K�p�J�n����<BR>
     * �@@�@@�E�u�X�e�[�^�X�v ���@@���O�C������IP.�X�e�[�^�X<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�\�[�g�L�[.�����^�~���ɑΉ�����擾����(asc or desc)���\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@�쐬�����\�[�g�����������ԋp����B<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[�N���X�B<BR>
     * @@return String
     * @@roseuid 48BE549C01A9
     */
    private String createQuerySortCond(WEB3AdminTraderAdminIPControlSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME =
            "createQuerySortCond(WEB3AdminTraderAdminIPControlSortKey[])";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�\�[�g����������I�u�W�F�N�g(�FString)���쐬����B
        StringBuffer l_sbQuerySortCond = new StringBuffer();

        String l_strSortCond =
            " NVL( LPAD( SUBSTR( IP_ADDRESS, 1, INSTR( IP_ADDRESS, '.', 1, 1) -1), 3, '0'), '000') ||" +
            " NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 1) +1," +
            " INSTR( IP_ADDRESS, '.', 1, 2) - INSTR( IP_ADDRESS, '.', 1, 1)-1), 3, '0'), '000') ||" +
            " NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 2) +1," +
            " INSTR( IP_ADDRESS, '.', 1, 3) - INSTR( IP_ADDRESS, '.', 1, 2)-1), 3, '0'), '000') ||" +
            " NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 3) +1), 3, '0'), '000')";

        //�Q�j�@@�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        int l_intLength = l_sortKeys.length;
        for (int i = 0; i < l_intLength; i++)
        {

            //�Q�|�P�j�@@�\�[�g�L�[.�L�[���ځ��uIP�A�h���X�v�̏ꍇ�A
            if (WEB3AdminTMKeyItemDef.IP_ADDRESS.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuerySortCond.append(l_strSortCond);
            }
            //�E�u�K�p�J�n�����v ���@@���O�C������IP.�K�p�J�n����
            else if (WEB3AdminTMKeyItemDef.START_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuerySortCond.append(" appli_start_timestamp ");
            }
            //�E�u�X�e�[�^�X�v ���@@���O�C������IP.�X�e�[�^�X
            else if (WEB3AdminTMKeyItemDef.STATUS.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuerySortCond.append(" status ");
            }

            //�Q�|�R�j�@@�\�[�g�L�[.�����^�~���ɑΉ�����擾����(asc or desc)���\�[�g����������ɒǉ�����B
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbQuerySortCond.append("Asc");
            }
            else
            {
                l_sbQuerySortCond.append("Desc");
            }

            if (i < l_intLength - 1)
            {
                l_sbQuerySortCond.append(",");
            }
        }

        //�R�j�@@�쐬�����\�[�g�����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbQuerySortCond.toString();
    }

    /**
     * (get���O�C������IP�ꗗ)<BR>
     * ���O�C������IP�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * �P�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * [doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@ arg0�F�@@���O�C������IPRowType<BR>
     * �@@�@@ arg1�F�@@"institution_code=? and status in (0, 2)"<BR>
     * �@@�@@ arg2�F�@@(����)�����\�[�g����<BR>
     * �@@�@@ arg3�F�@@null<BR>
     * �@@�@@ arg4�F�@@(����)�،���ЃR�[�h<BR>
     * �@@�@@ arg5�F�@@(����)�y�[�W���\���s��<BR>
     * �@@�@@ arg6�F�@@(����)�v���y�[�W�ԍ� - 1<BR>
     * <BR>
     * ���������ʂ�0���̏ꍇ�A�G���[��ԋp����B<BR>
     * �G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v�iBUSINESS_ERROR_01037�j�j<BR>
     * <BR>
     * �Q�j �P�j �̖߂�l��ԋp����B<BR>
     * @@param l_strQuerySortCond - (�����\�[�g����)<BR>
     * �����\�[�g����<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strPageSize - (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     * @@param l_strPageIndex - (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 48BE5A8002FD
     */
    private List getLoginRejectIPList(
        String l_strQuerySortCond,
        String l_strInstitutionCode,
        String l_strPageSize,
        String l_strPageIndex) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getLoginRejectIPList(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        List l_lisLoginRejectIpRows = null;

        try
        {
            //�P�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            //[doFindAllQuery()�ɃZ�b�g����p�����[�^]
            //�@@ arg0�F�@@���O�C������IPRowType
            //�@@ arg1�F�@@"institution_code=? and status in (0, 2)"
            //�@@ arg2�F�@@(����)�����\�[�g����
            //�@@ arg3�F�@@null
            //�@@ arg4�F�@@(����)�،���ЃR�[�h
            //�@@ arg5�F�@@(����)�y�[�W���\���s��
            //�@@ arg6�F�@@(����)�v���y�[�W�ԍ� - 1
            String l_strWhere = "institution_code=? and status in (0, 2)";
            Object[] l_objVars = {l_strInstitutionCode};
            l_lisLoginRejectIpRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    LoginRejectIpRow.TYPE,
                    l_strWhere,
                    l_strQuerySortCond,
                    null,
                    l_objVars,
                    Integer.parseInt(l_strPageSize),
                    Integer.parseInt(l_strPageIndex) - 1);
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

        //���������ʂ�0���̏ꍇ�A�G���[��ԋp����B
        //�G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v�iBUSINESS_ERROR_01037�j�j
        if (l_lisLoginRejectIpRows == null || l_lisLoginRejectIpRows.isEmpty())
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        //�Q�j �P�j �̖߂�l��ԋp����B
        return l_lisLoginRejectIpRows;
    }

    /**
     * (get���O�C������IP�s)<BR>
     * �����ɊY�����郍�O�C������IPParams��ԋp����B<BR>
     * <BR>
     * �P�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * �@@�@@���i�����j���b�N�t���O==true�̏ꍇ��"FOR UPDATE"��t������B<BR>
     * <BR>
     * [doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@ arg0�F�@@���O�C������IPRowType<BR>
     * �@@�@@ arg1�F�@@"login_reject_id=? and status in (0, 2)"<BR>
     * �@@�@@ arg2�F�@@"FOR UPDATE"�i�i�����j���b�N�t���O==true�̏ꍇ�ǉ�����B�j<BR>
     * �@@�@@ arg3�F�@@(����)���O�C������ID<BR>
     * <BR>
     * ���������ʂ��擾�ł��Ȃ������ꍇ�A�G���[��ԋp����B<BR>
     * �@@�G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v�iBUSINESS_ERROR_01037�j<BR>
     * <BR>
     * �Q�j �P�j �̖߂�l��ԋp����B<BR>
     * @@param l_strDenyLoginID - (���O�C������ID)<BR>
     * ���O�C������ID<BR>
     * @@param l_blnLockFlag - (���b�N�t���O)<BR>
     * ���b�N�t���O<BR>
     * <BR>
     * true�F�@@�s���b�N���s���B<BR>
     * false�F�@@�s���b�N���s��Ȃ��B<BR>
     * @@return LoginRejectIpParams
     * @@throws WEB3BaseException
     * @@roseuid 48C5CF32011D
     */
    private LoginRejectIpParams getLoginRejectIPParams(
        String l_strDenyLoginID,
        boolean l_blnLockFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLoginRejectIPParams(String, boolean)";
        log.entering(STR_METHOD_NAME);

        List l_lisLoginRejectIpRows = null;

        try
        {
            String l_strConditions = null;
            //�P�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            //�@@���i�����j���b�N�t���O==true�̏ꍇ��"FOR UPDATE"��t������B
            if (l_blnLockFlag)
            {
                l_strConditions = "FOR UPDATE";
            }
            //[doFindAllQuery()�ɃZ�b�g����p�����[�^]
            //�@@ arg0�F�@@���O�C������IPRowType
            //�@@ arg1�F�@@"login_reject_id=? and status in (0, 2)"
            //�@@ arg2�F�@@"FOR UPDATE"�i�i�����j���b�N�t���O==true�̏ꍇ�ǉ�����B�j
            //�@@ arg3�F�@@(����)���O�C������ID
            String l_strWhere = "login_reject_id=? and status in (0, 2)";
            Object[] l_objVars = {l_strDenyLoginID};
            l_lisLoginRejectIpRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    LoginRejectIpRow.TYPE,
                    l_strWhere,
                    l_strConditions,
                    l_objVars);
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

        //���������ʂ��擾�ł��Ȃ������ꍇ�A�G���[��ԋp����B
        //�@@�G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v�iBUSINESS_ERROR_01037�j
        if (l_lisLoginRejectIpRows == null || l_lisLoginRejectIpRows.isEmpty())
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        //�Q�j �P�j �̖߂�l��ԋp����B
        LoginRejectIpRow l_loginRejectIPRow =
            (LoginRejectIpRow)l_lisLoginRejectIpRows.get(0);

        LoginRejectIpParams l_loginRejectIPParams =
            new LoginRejectIpParams(l_loginRejectIPRow);

        return l_loginRejectIPParams;
    }

    /**
     * (create���O�C������IP���)<BR>
     * ���O�C������IP��񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j�@@���O�C������IP���I�u�W�F�N�g�̐���<BR>
     * <BR>
     * �Q�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�E�@@���O�C������IP���.���O�C������ID�@@ ���@@(����)���O�C������IP�s.���O�C������ID<BR>
     * �@@�@@�E�@@���O�C������IP���.IP�A�h���X�@@   ���@@(����)���O�C������IP�s.IP�A�h���X<BR>
     * �@@�@@�E�@@���O�C������IP���.�X�e�[�^�X�@@    ���@@(����)���O�C������IP�s.�X�e�[�^�X<BR>
     * �@@�@@�E�@@���O�C������IP���.�K�p�J�n�����@@   ���@@(����)���O�C������IP�s.�K�p�J�n����<BR>
     * �@@�@@�E�@@���O�C������IP���.�K�p�I�������@@   ���@@(����)���O�C������IP�s.�K�p�I������<BR>
     * �@@�@@�E�@@���O�C������IP���.�o�^�敪�@@ ���@@(����)���O�C������IP�s.�o�^�敪<BR>
     * �@@�@@�E�@@���O�C������IP���.�X�V�敪�@@ ���@@(����)���O�C������IP�s.�X�V�敪<BR>
     * �@@�@@�E�@@���O�C������IP���.�X�V�҃R�[�h�@@   ���@@�ȉ��̒ʂ�<BR>
     * �@@�@@�@@�@@�|(����)���O�C������IP�s.�X�V�敪 == "0"(�f�[����)�̏ꍇ�ANULL<BR>
     * �@@�@@�@@�@@�|(����)���O�C������IP�s.�X�V�敪 == "1"(�Ǘ���)�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@(����)���O�C������IP�s.�X�V�҃R�[�h<BR>
     * <BR>
     * �R�j�@@�����������O�C������IP���I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_loginRejectIPParams - (���O�C������IP�s)<BR>
     * ���O�C������IP�s<BR>
     * @@return WEB3AdminTraderAdminIPControlReferenceUnit
     * @@roseuid 48C5D3B80066
     * @@throws WEB3BaseException
     */
    private WEB3AdminTraderAdminIPControlReferenceUnit createLoginRejectIPList(
        LoginRejectIpParams l_loginRejectIPParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createLoginRejectIPList(LoginRejectIpParams, String, Date)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit =
            new WEB3AdminTraderAdminIPControlReferenceUnit();
        //�@@�@@�E�@@���O�C������IP���.���O�C������ID�@@ ���@@(����)���O�C������IP�s.���O�C������ID
        l_adminIPControlReferenceUnit.denyLoginID = String.valueOf(l_loginRejectIPParams.getLoginRejectId());
        //�@@�@@�E�@@���O�C������IP���.IP�A�h���X�@@ ���@@(����)���O�C������IP�s.IP�A�h���X
        l_adminIPControlReferenceUnit.ipAddress = l_loginRejectIPParams.getIpAddress();
        //�@@�@@�E�@@���O�C������IP���.�X�e�[�^�X�@@ ���@@(����)���O�C������IP�s.�X�e�[�^�X
        l_adminIPControlReferenceUnit.status = l_loginRejectIPParams.getStatus();
        //�@@�@@�E�@@���O�C������IP���.�K�p�J�n�����@@ ���@@(����)���O�C������IP�s.�K�p�J�n����
        l_adminIPControlReferenceUnit.startDate = l_loginRejectIPParams.getAppliStartTimestamp();
        //�@@�@@�E�@@���O�C������IP���.�K�p�I�������@@ ���@@(����)���O�C������IP�s.�K�p�I������
        l_adminIPControlReferenceUnit.endDate = l_loginRejectIPParams.getAppliEndTimestamp();
        //�@@�@@�E�@@���O�C������IP���.�o�^�敪�@@ ���@@(����)���O�C������IP�s.�o�^�敪
        l_adminIPControlReferenceUnit.registDiv = l_loginRejectIPParams.getRegistDiv();
        //�@@�@@�E�@@���O�C������IP���.�X�V�敪�@@ ���@@(����)���O�C������IP�s.�X�V�敪
        l_adminIPControlReferenceUnit.updateDiv = l_loginRejectIPParams.getUpdatedDiv();
        //�@@�@@�E�@@���O�C������IP���.�X�V�҃R�[�h�@@ ���@@�ȉ��̒ʂ�
        //�@@�@@�@@�@@�|(����)���O�C������IP�s.�X�V�敪 == "0"(�f�[����)�̏ꍇ�ANULL
        //�@@�@@�@@�@@�|(����)���O�C������IP�s.�X�V�敪 == "1"(�Ǘ���)�̏ꍇ�A(����)���O�C������IP�s.�X�V�҃R�[�h
        if (WEB3UpdatedDivDef.DAEMON.equals(l_loginRejectIPParams.getUpdatedDiv()))
        {
            l_adminIPControlReferenceUnit.updaterCode = null;
        }
        else if (WEB3UpdatedDivDef.MANAGER.equals(l_loginRejectIPParams.getUpdatedDiv()))
        {
            l_adminIPControlReferenceUnit.updaterCode = l_loginRejectIPParams.getLastUpdater();
        }

        log.exiting(STR_METHOD_NAME);
        return l_adminIPControlReferenceUnit;
    }

    /**
     * (validate�ύX�L��)<BR>
     * ���͓��e�ɕύX�����邩�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�ȉ��̑S�ĂɊY������ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@�G���[���b�Z�[�W�u���͓��e�ɕύX������܂���B�v<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          �@@tag: BUSINESS_ERROR_03132<BR>
     * <BR>
     * �P�|�P�j�@@(����)���O�C������IP�s.�X�e�[�^�X == (����)�X�e�[�^�X<BR>
     * <BR>
     * �P�|�Q�j�@@(����)���O�C������IP�s.�K�p�I������ == (����)�K�p�I������<BR>
     * @@param l_loginRejectIPParams - (���O�C������IP�s)<BR>
     * ���O�C������IP�s<BR>
     * @@param l_strStatus - (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     * @@param l_datEndDate - (�K�p�I������)<BR>
     * �K�p�I������<BR>
     * @@roseuid 48C626AE0001
     * @@throws WEB3BaseException
     */
    private void validateIsChange(
        LoginRejectIpParams l_loginRejectIPParams,
        String l_strStatus,
        Date l_datEndDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateIsChange(LoginRejectIpParams, String, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_loginRejectIPParams == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@�ȉ��̑S�ĂɊY������ꍇ�A��O��throw����B
        //�@@�@@�@@�G���[���b�Z�[�W�u���͓��e�ɕύX������܂���B�v
        //�P�|�P�j�@@(����)���O�C������IP�s.�X�e�[�^�X == (����)�X�e�[�^�X
        //�P�|�Q�j�@@(����)���O�C������IP�s.�K�p�I������ == (����)�K�p�I������
        if (l_loginRejectIPParams.getStatus().equals(l_strStatus)
            && WEB3DateUtility.compareToSecond(l_loginRejectIPParams.getAppliEndTimestamp(), l_datEndDate) == 0)
        {
            log.debug("���͓��e�ɕύX������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03132,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���͓��e�ɕύX������܂���B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (save���O�C������IP)<BR>
     * ���O�C������IP�e�[�u���Ƀ��R�[�h���쐬����B<BR>
     * <BR>
     * DB�X�V�d�l�u���O�C������IP�o�^_���O�C������IPDB�X�V�d�l.xls�v���Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strAdministratorCode - (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h<BR>
     * @@roseuid 48C0E2DB0057
     * @@throws WEB3BaseException
     */
    private void saveLoginRejectIP(
        WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request,
        String l_strInstitutionCode,
        String l_strAdministratorCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "saveLoginRejectIP(WEB3AdminTraderAdminIPControlRegistCompleteRequest, String, String)";
        log.entering(STR_METHOD_NAME);
        try
        {
            LoginRejectIpParams l_loginRejectIpParams = new LoginRejectIpParams();

            //���O�C������ID  xTrade�ɂ�莩���̔Ԃ��ꂽ�l
            l_loginRejectIpParams.setLoginRejectId(LoginRejectIpDao.newPkValue());

            //�،���ЃR�[�h   ����.�،���ЃR�[�h
            l_loginRejectIpParams.setInstitutionCode(l_strInstitutionCode);

            //IP�A�h���X        ���N�G�X�g�f�[�^.IP�A�h���X
            l_loginRejectIpParams.setIpAddress(l_request.ipAddress);

            //�X�e�[�^�X     ���N�G�X�g�f�[�^.�X�e�[�^�X
            l_loginRejectIpParams.setStatus(l_request.status);

            //�K�p�J�n����    ���N�G�X�g�f�[�^.�K�p�J�n����
            l_loginRejectIpParams.setAppliStartTimestamp(l_request.startDate);

            //�K�p�I������    ���N�G�X�g�f�[�^.�K�p�I������
            l_loginRejectIpParams.setAppliEndTimestamp(l_request.endDate);

            //�o�^�敪      "1"�i�Ǘ��ҁj
            l_loginRejectIpParams.setRegistDiv(WEB3LoginRegistDivDef.MANAGER);

            //�X�V�敪      "1"�i�Ǘ��ҁj
            l_loginRejectIpParams.setUpdatedDiv(WEB3UpdatedDivDef.MANAGER);

            //�X�V�҃R�[�h  ����.�Ǘ��҃R�[�h
            l_loginRejectIpParams.setLastUpdater(l_strAdministratorCode);

            //�쐬���t      ��������
            l_loginRejectIpParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

            //�X�V���t      ��������
            l_loginRejectIpParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_loginRejectIpParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
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
     * (update���O�C������IP)<BR>
     * ���O�C������IP�e�[�u���̃��R�[�h���X�V����B<BR>
     * <BR>
     * [����]<BR>
     * �@@���O�C������ID�@@���@@(����)���O�C������ID<BR>
     * @@param l_strLoginRejectId - (���O�C������ID)<BR>
     * ���O�C������ID<BR>
     * @@param l_mapUpdateContents - (�X�V���e)<BR>
     * �X�V���e<BR>
     * @@roseuid 48C6312F01E4
     * @@throws WEB3BaseException
     */
    private void updateLoginRejectIP(
        String l_strLoginRejectId,
        Map l_mapUpdateContents)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateLoginRejectIP(String, Map)";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            //[����]
            //���O�C������ID�@@���@@(����)���O�C������ID
            String l_strWhere = "login_reject_id = ?";
            Object[] l_objWhere = {l_strLoginRejectId};
            l_processor.doUpdateAllQuery(
                LoginRejectIpRow.TYPE,
                l_strWhere,
                l_objWhere,
                l_mapUpdateContents);
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

        log.exiting(STR_METHOD_NAME);
    }
}@
