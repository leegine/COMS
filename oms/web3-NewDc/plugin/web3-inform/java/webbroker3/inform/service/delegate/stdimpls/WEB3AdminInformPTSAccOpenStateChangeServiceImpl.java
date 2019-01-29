head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.45.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccOpenStateChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ���PTS�����J�ݏ󋵕ύX�T�[�r�XImpl(WEB3AdminInformPTSAccOpenStateChangeServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/27 �đo�g(���u) �V�K�쐬 ���f��No.129�ANo.131�ANo.132�A�c�a�X�V�d�lNo.021
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExtDiv1Def;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.define.WEB3informAfterPtsAccOpenDiv;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeSrcRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeSrcResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformPTSAccOpenStateChangeService;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;

/**
 * �Ǘ���PTS�����J�ݏ󋵕ύX�T�[�r�XImpl<BR>
 * �Ǘ���PTS�����J�ݏ󋵕ύX�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccOpenStateChangeServiceImpl implements
    WEB3AdminInformPTSAccOpenStateChangeService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformPTSAccOpenStateChangeServiceImpl.class);

    /**
     * @@roseuid 47B9271A015F
     */
    public WEB3AdminInformPTSAccOpenStateChangeServiceImpl()
    {

    }

    /**
     * �Ǘ���PTS�����J�ݏ󋵕ύX�T�[�r�X�������s���B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A0132203E3
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
        if (l_request instanceof WEB3AdminInformPTSAccOpenStateChangeSrcRequest)
        {
            //�Ǘ���PTS�����J�ݏ󋵕ύX������ʂ̎擾���s���B
            l_response =
                this.getSearchScreen((WEB3AdminInformPTSAccOpenStateChangeSrcRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformPTSAccOpenStateChangeInpRequest)
        {
            //�Ǘ���PTS�����J�ݏ󋵕ύX���͉�ʂ̎擾���s���B
            l_response =
                this.getInputScreen((WEB3AdminInformPTSAccOpenStateChangeInpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformPTSAccOpenStateChangeCnfRequest)
        {
            //�Ǘ���PTS�����J�ݏ󋵕ύX�m�F�������s���B
            l_response =
                this.validateChange((WEB3AdminInformPTSAccOpenStateChangeCnfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformPTSAccOpenStateChangeCmpRequest)
        {
            //�Ǘ���PTS�����J�ݏ󋵕ύX�����������s���B
            l_response =
                this.submitChange((WEB3AdminInformPTSAccOpenStateChangeCmpRequest)l_request);
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
     * (get�������)<BR>
     * �Ǘ���PTS�����J�ݏ󋵕ύX������ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��ҁiPTS�����J�ݏ󋵕ύX�jget������ʁv �Q��<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformPTSAccOpenStateChangeSrcResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A0133F0249
     */
    protected WEB3AdminInformPTSAccOpenStateChangeSrcResponse getSearchScreen(
        WEB3AdminInformPTSAccOpenStateChangeSrcRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchScreen(WEB3AdminInformPTSAccOpenStateChangeSrcRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        // �@@�\�J�e�S���R�[�h�F "A0501"
        // is�X�V�Ffalse
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.PTS_ACCOUNT_MANAGE,
            false);

        //createResponse( )
        WEB3AdminInformPTSAccOpenStateChangeSrcResponse l_response =
            (WEB3AdminInformPTSAccOpenStateChangeSrcResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉�� )<BR>
     * �Ǘ���PTS�����J�ݏ󋵕ύX���͉�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��ҁiPTS�����J�ݏ󋵕ύX�jget���͉�ʁv �Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformPTSAccOpenStateChangeInpResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A013730264
     */
    protected WEB3AdminInformPTSAccOpenStateChangeInpResponse getInputScreen(
        WEB3AdminInformPTSAccOpenStateChangeInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminInformPTSAccOpenStateChangeInpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        // �@@�\�J�e�S���R�[�h�F "A0501"
        // is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.PTS_ACCOUNT_MANAGE,
            true);

        //validate���X����(���X�R�[�h : String)
        //[����]
        // ���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        //[get�ڋq()�Ɏw�肷�����]
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        // �����R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAcccount =
            l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);

        //getDataSourceObject( )
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_mainAcccount.getDataSourceObject();

        //createResponse( )
        WEB3AdminInformPTSAccOpenStateChangeInpResponse l_response =
            (WEB3AdminInformPTSAccOpenStateChangeInpResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //�ڋq���F�@@�ڋq�s.���O�i�c���j
        l_response.accountName = l_mainAccountRow.getFamilyName();

        //�ύX�O�\���敪�F�@@�ڋq�s.PTS�����J�݋敪
        l_response.beforePtsAccOpenDiv = l_mainAccountRow.getPtsAccOpenDiv();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�ύX)<BR>
     * �Ǘ���PTS�����J�ݏ󋵕ύX�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��ҁiPTS�����J�ݏ󋵕ύX�jvalidate�ύX�v �Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformPTSAccOpenStateChangeCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A0137612F3
     */
    protected WEB3AdminInformPTSAccOpenStateChangeCnfResponse validateChange(
        WEB3AdminInformPTSAccOpenStateChangeCnfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AdminInformPTSAccOpenStateChangeCnfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        // �@@�\�J�e�S���R�[�h�F "A0501"
        // is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.PTS_ACCOUNT_MANAGE,
            true);

        //validate���X����(���X�R�[�h : String)
        //[����]
        // ���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        //[get�ڋq()�Ɏw�肷�����]
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        // �����R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAcccount =
            l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);

        //validate�\���敪�ύX�L��(�ڋq, String)
        //[����]
        // �ڋq�F�@@get�ڋq()�̖߂�l
        // �ύX��\���敪�F���N�G�X�g�f�[�^.�ύX��\���敪
        this.validateApplyDivIsChange(
            l_mainAcccount,
            l_request.afterPtsAccOpenDiv);

        //createResponse( )
        WEB3AdminInformPTSAccOpenStateChangeCnfResponse l_response =
            (WEB3AdminInformPTSAccOpenStateChangeCnfResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�ύX)<BR>
     * �Ǘ���PTS�����J�ݏ󋵕ύX�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��ҁiPTS�����J�ݏ󋵕ύX�jsubmit�ύX�v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformPTSAccOpenStateChangeCmpResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B11D8A024B
     */
    protected WEB3AdminInformPTSAccOpenStateChangeCmpResponse submitChange(
        WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AdminInformPTSAccOpenStateChangeCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        // �@@�\�J�e�S���R�[�h�F "A0501"
        // is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.PTS_ACCOUNT_MANAGE,
            true);

        //validate���X����(���X�R�[�h : String)
        //[����]
        // ���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);

        //validate����p�X���[�h(�p�X���[�h : String)
        //[validate����p�X���[�h()�Ɏw�肷�����]
        // �p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        //[get�ڋq()�Ɏw�肷�����]
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        // �����R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAcccount =
            l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);

        //getDataSourceObject( )
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_mainAcccount.getDataSourceObject();

        //validate�\���敪�ύX�L��(�ڋq, String)
        //[����]
        // �ڋq�F�@@get�ڋq()�̖߂�l
        // �ύX��\���敪�F���N�G�X�g�f�[�^.�ύX��\���敪
        this.validateApplyDivIsChange(
            l_mainAcccount,
            l_request.afterPtsAccOpenDiv);

        //get�V�K���ʃR�[�h(String, String)
        //[����]
        // �،���ЃR�[�h�F �ڋq�s.�،���ЃR�[�h
        // �A����ʁF ���N�G�X�g�f�[�^.�A�����
        WEB3InformHostReqOrderNumberManageService l_informHostReqOrderNumberManageService =
            (WEB3InformHostReqOrderNumberManageService)Services.getService(
                WEB3InformHostReqOrderNumberManageService.class);
        String l_strRequestCode =
            l_informHostReqOrderNumberManageService.getNewOrderRequestCode(
                l_mainAccountRow.getInstitutionCode(),
                l_request.informType);

        //get�e��A��(�ڋq, String)
        //[����]
        // �ڋq�F�@@�ڋq�I�u�W�F�N�g
        // �A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        WEB3Inform l_inform =
            WEB3Inform.getVariousInform(l_mainAcccount, l_request.informType);

        //get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //get�e��A���̖߂�l != null �̏ꍇ�A�ȉ��̏������s���B
        if (l_inform != null)
        {
            //updatePTS�e��A��(Map)
            // �E�敪�P�F�@@"0"�i�����j
            // �E�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
            // �E�X�V�����F�@@��������
            Map l_informUpdateMap = new HashMap();
            l_informUpdateMap.put("ext_div1", WEB3ExtDiv1Def.INVALIDITY);
            l_informUpdateMap.put("last_updater", l_strAdministratorCode);
            l_informUpdateMap.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            l_inform.updateInform(l_informUpdateMap);
        }

        //createNew�e��A��(�ڋq, String, String, String, String)
        //[����]
        // �ڋq�F�@@�ڋq
        // �A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        // PTS�����J�݋敪�F�@@���N�G�X�g�f�[�^.�ύX��\���敪
        // �X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
        // ���ʃR�[�h�F�@@�A���Ǘ����ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()�̖߂�l
        WEB3Inform l_newVariousInform =
            WEB3Inform.createNewVariousInform(
                l_mainAcccount,
                l_request.informType,
                l_request.afterPtsAccOpenDiv,
                l_strAdministratorCode,
                l_strRequestCode);

        // saveNew�e��A��( )
        l_newVariousInform.saveNewVariousInform();

        try
        {
            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //doUpdateQuery(arg0 : PrimaryKey, arg1 : Map)
            //[doUpdateQuery()�Ɏw�肷�����]
            // arg0�iprimaryKey�j�F�@@�ڋq�s.����ID
            // arg1�ichanges�j�F
            //�@@�@@�y��Trade�z�⑫����.DB�X�V�uPTS�����J�ݐ\��_�ڋq�}�X�^�[�v�Q�ƁB
            MainAccountPK l_mainAccountPK =
                new MainAccountPK(l_mainAccountRow.getAccountId());

            Map l_mainAccountUpdateMap = new HashMap();
            //PTS�����J�݋敪
            // �Ǘ��҂̏ꍇ�A���N�G�X�g�f�[�^.�ύX��\���敪
            l_mainAccountUpdateMap.put("pts_acc_open_div", l_request.afterPtsAccOpenDiv);

            //PTS�����J�݋敪�X�V�҃R�[�h
            // �Ǘ��҂̏ꍇ�A�Ǘ��҃e�[�u��.�Ǘ��҃R�[�h
            l_mainAccountUpdateMap.put("pts_acc_open_div_last_updater", l_strAdministratorCode);

            //PTS�����J�݋敪�X�V����
            // ��������
            l_mainAccountUpdateMap.put("pts_acc_open_div_timestamp", GtlUtils.getSystemTimestamp());

            l_queryProcessor.doUpdateQuery(
                l_mainAccountPK,
                l_mainAccountUpdateMap);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //createResponse( )
        WEB3AdminInformPTSAccOpenStateChangeCmpResponse l_response =
            (WEB3AdminInformPTSAccOpenStateChangeCmpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�\���敪�ύX�L��)<BR>
     * PTS�����J�݋敪�ɕύX�����邩�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�ڋq.isPTS�����J��()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�ȉ��̂����ꂩ�ɊY������ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@�G���[���b�Z�[�W�u�\���敪�ɕύX������܂���B�v<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_03046<BR>
     * <BR>
     * �Q�|�P�j�@@����.�ύX��\���敪 == "1"�i�J�݁j �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�P�j�̖߂�l == true<BR>
     * <BR>
     * �Q�|�Q�j�@@����.�ύX��\���敪 == "0"�i���J�݁j �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�P�j�̖߂�l == false<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strAfterChangeApplyDiv - (�ύX��\���敪)<BR>
     * �ύX��\���敪<BR>
     * 0�F���J��<BR>
     * 1�F�J��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47A2C31102E5
     */
    private void validateApplyDivIsChange(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strAfterChangeApplyDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateApplyDivIsChange(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);

        //�ڋq.isPTS�����J��()���R�[������B
        boolean l_blnIsPTSAccountOpen = l_mainAccount.isPTSAccountOpen();

        boolean l_blnErrorFlag = false;
        //����.�ύX��\���敪 == "1"�i�J�݁j �̏ꍇ�A
        //isPTS�����J��()�̖߂�l == true
        if (WEB3informAfterPtsAccOpenDiv.OPEN.equals(l_strAfterChangeApplyDiv)
            && l_blnIsPTSAccountOpen)
        {
            l_blnErrorFlag = true;
        }

        //����.�ύX��\���敪 == "0"�i���J�݁j �̏ꍇ�A
        //isPTS�����J��()�̖߂�l == false
        if (WEB3informAfterPtsAccOpenDiv.NOT_OPEN.equals(l_strAfterChangeApplyDiv)
            && !l_blnIsPTSAccountOpen)
        {
            l_blnErrorFlag = true;
        }

        if (l_blnErrorFlag)
        {
            log.debug("�\���敪�ɕύX������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03046,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                "�\���敪�ɕύX������܂���B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
