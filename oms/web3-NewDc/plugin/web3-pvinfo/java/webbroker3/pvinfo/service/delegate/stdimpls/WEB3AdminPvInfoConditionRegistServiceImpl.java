head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҕ\���ݒ�o�^�T�[�r�XImpl(WEB3AdminPvInfoConditionRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/28 ���O�B(���u) �쐬
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputResponse;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayConditionUnit;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionRegistService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҕ\���ݒ�o�^�T�[�r�XImpl)<BR>
 * �Ǘ��ҕ\���ݒ�o�^�T�[�r�X�����N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionRegistServiceImpl extends WEB3ClientRequestService implements WEB3AdminPvInfoConditionRegistService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionRegistServiceImpl.class);

    /**
     * �Ǘ��ҕ\���ݒ�o�^�������s���B<BR>
     * <BR>
     * �����̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����<BR>
     * <BR>
     * ���Ǘ��ҁE�\���ݒ�o�^���̓��N�G�X�g�̏ꍇ<BR>
     * �@@this.get�\���ݒ�o�^���͉��()���\�b�h���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�\���ݒ�o�^�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.validate�\���ݒ�o�^()���\�b�h���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�\���ݒ�o�^�������N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�\���ݒ�o�^()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415BFD8A0395
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminPvInfoConditionRegistInputRequest)
        {
            //�Ǘ��ҁE�\���ݒ�o�^���̓��N�G�X�g�̏ꍇ,this.get�\���ݒ�o�^���͉��()���\�b�h���R�[������B
            l_response = this.getConditionRegistInputScreen((WEB3AdminPvInfoConditionRegistInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminPvInfoConditionRegistConfirmRequest)
        {
            //�Ǘ��ҁE�\���ݒ�o�^�m�F���N�G�X�g�̏ꍇ,this.validate�\���ݒ�o�^()���\�b�h���R�[������B
            l_response = this.validateConditionRegist((WEB3AdminPvInfoConditionRegistConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminPvInfoConditionRegistCompleteRequest)
        {
            //�Ǘ��ҁE�\���ݒ�o�^�������N�G�X�g�̏ꍇ,this.submit�\���ݒ�o�^()���\�b�h���R�[������B
            l_response = this.submitConditionRegist((WEB3AdminPvInfoConditionRegistCompleteRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = "�p�����[�^�̗ތ^���s���A�Y������WEB3AdminPvInfoConditionRegistInputRequest,"
                + "WEB3AdminPvInfoConditionRegistConfirmRequest, WEB3AdminPvInfoConditionRegistCompleteRequest�ތ^�B";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�\���ݒ�o�^���͉��)<BR>
     * �\���ݒ�o�^���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҕ\���ݒ�o�^�T�[�r�X)get�\���ݒ�o�^���͉�ʁv�Q��<BR>
     * ========================================================<BR>
     * create�\���������ꗗ(�Ǘ���)<BR>
     * <BR>
     * null���ԋp���ꂽ�ꍇ�́A<BR>
     * �u�\�������ݒ�Ȃ��v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01036<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�o�^���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputResponse
     * @@roseuid 415BFDF3023C
     */
    protected WEB3AdminPvInfoConditionRegistInputResponse getConditionRegistInputScreen(WEB3AdminPvInfoConditionRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConditionRegistInputScreen(WEB3AdminPvInfoConditionRegistInputRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3Administrator l_administrator = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1 getInstanceFrom���O�C�����()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFrom���O�C���������s���܂�");

        //2 validate����(�@@�\�R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate���������s���܂�");

        //3 create�\���������ꗗ(�Ǘ���)
        WEB3PvInfoDisplayConditionUnit[] l_dispConditionUnits = l_dataManager.createDisplayConditionList(l_administrator);
        log.debug("create�\���������ꗗ�����s���܂�");
        if (l_dispConditionUnits == null)
        {
            log.info("�\�������ݒ�Ȃ�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01036,
                getClass().getName() + STR_METHOD_NAME);
        }

        //4 createResponse()
        WEB3AdminPvInfoConditionRegistInputResponse l_response = (WEB3AdminPvInfoConditionRegistInputResponse)l_request.createResponse();

        //5 �v���p�e�B�Z�b�g
        l_response.displayConditionUnits = l_dispConditionUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�\���ݒ�o�^)<BR>
     * �\���ݒ�o�^�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҕ\���ݒ�o�^�T�[�r�X)validate�\���ݒ�o�^�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�o�^�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmResponse
     * @@roseuid 415BFDF3024C
     */
    protected WEB3AdminPvInfoConditionRegistConfirmResponse validateConditionRegist(WEB3AdminPvInfoConditionRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateConditionRegist(WEB3AdminPvInfoConditionRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3Administrator l_administrator = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1 validate()
        l_request.validate();
        log.debug("validate�����s���܂�");

        //2 getInstanceFrom���O�C�����()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFrom���O�C���������s���܂�");
        
        //3 validate����(�@@�\�R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate���������s���܂�");

        //4 get�V�K�\�����eID()
        long l_lngNewDisplayContentsId = l_dataManager.getNewDisplayContentsId();
        log.debug("get�V�K�\�����eID = " + l_lngNewDisplayContentsId);
        
        //5 createResponse()
        WEB3AdminPvInfoConditionRegistConfirmResponse l_response = (WEB3AdminPvInfoConditionRegistConfirmResponse)l_request.createResponse();

        //6 �v���p�e�B�Z�b�g
        l_response.displayContentsId = WEB3StringTypeUtility.formatNumber(l_lngNewDisplayContentsId);
        log.debug("�\�����eID = " + l_response.displayContentsId);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�\���ݒ�o�^)<BR>
     * �\���ݒ�o�^�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҕ\���ݒ�o�^�T�[�r�X)submit�\���ݒ�o�^�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�o�^�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteResponse
     * @@roseuid 415BFDF3026B
     */
    protected WEB3AdminPvInfoConditionRegistCompleteResponse submitConditionRegist(WEB3AdminPvInfoConditionRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitConditionRegist(WEB3AdminPvInfoConditionRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3Administrator l_administrator = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1 validate()
        l_request.validate();
        log.debug("validate�����s���܂�");
        
        //2 getInstanceFrom���O�C�����()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFrom���O�C���������s���܂�");

        //3 validate����(�@@�\�R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate���������s���܂�");

        //4 validate����p�X���[�h(String)
        l_administrator.validateTradingPassword(l_request.password);
        log.debug("validate����p�X���[�h�����s���܂�");

        //5  insert�\�����e(�Ǘ���, �\�����e���)
        l_dataManager.insertDisplayContents(l_administrator, l_request.displayContentsUnit);
        log.debug("insert�\�����e�����s���܂�");

        //6 createResponse()
        WEB3AdminPvInfoConditionRegistCompleteResponse l_response = (WEB3AdminPvInfoConditionRegistCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
