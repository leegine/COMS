head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountAppliStateListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ�T�[�r�XImpl(WEB3AdminSrvRegiAccountAppliStateListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/01 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3SrvRegiOfferingDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiLotteryStateGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiNoLotteryStateGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiStateRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiStateResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountAppliStateListService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ�T�[�r�XImpl)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ�T�[�r�X�����N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountAppliStateListServiceImpl
    extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiAccountAppliStateListService
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountAppliStateListServiceImpl.class);

    /**
     * @@roseuid 416F392803D8
     */
    public WEB3AdminSrvRegiAccountAppliStateListServiceImpl()
    {

    }

    /**
     * �T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ�Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj�ڋq�\���󋵌����v�Q��<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F520C70298
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME );

        WEB3AdminSrvRegiStateRequest l_stateRequest = null;


        if (l_request instanceof WEB3AdminSrvRegiStateRequest)
        {
            l_stateRequest = (WEB3AdminSrvRegiStateRequest)l_request;
        }
        else
        {
            String l_strErrorMessage = "�p�����[�^�̗ތ^���s���B";
            log.debug(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 validate( )
        l_stateRequest.validate();

        //1.3 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, false);

        //1.5.1 validate���X����(String[])
        l_admin.validateBranchPermission(l_stateRequest.branchCode);

        //1.6 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.7 get�T�[�r�X�}�X�^�[�ꗗ(String, String)
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster[] l_srvMasterLists =
            l_srvRegiServiceInfoManagement.getSrvMasterList(l_strInstitutionCode, WEB3SrvRegiOfferingDivDef.REQUIRE);

        List l_lisNoLotteryStateGroups = new LinkedList();
        List  l_lisLotteryStateGroups = new LinkedList();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        int l_intListCount = l_srvMasterLists.length;
        //1.8 <�J��Ԃ����� *1>
        for (int i = 0; i < l_intListCount; i++)
        {
            WEB3SrvRegiServiceMaster  l_srvMaster = l_srvMasterLists[i];

            //1.8.1 get�\���v�T�[�r�X(boolean)
            WEB3SrvRegiApplicationRequiredService l_appRequiredService = l_srvMaster.getAppliRequiredSrv(false);

            if (l_appRequiredService == null)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //1.8.2 get�ڋq(String, String, String)
            WEB3GentradeMainAccount l_mainAccount =
                l_accountManager.getMainAccount(l_admin.getInstitutionCode(), l_stateRequest.branchCode, l_stateRequest.accountCode);

            //1.8.3 getAccountCode( )
            String l_strAccountCode = l_mainAccount.getAccountCode();

            //1.8.4 get�T�[�r�X�\���o�^�ꗗ(String, String, String, String, boolean)
            WEB3SrvRegiRegistService l_appliRegiService =
                (WEB3SrvRegiRegistService) Services.getService(WEB3SrvRegiRegistService.class);

			//��Q�Ή� NO_2049
            //�����́A�u�،���ЃR�[�h�A���X�R�[�h�A�T�[�r�X�敪�A�����R�[�h�A�K�p�I�����敪�v
            SrvRegiApplicationParams[] l_appParams = l_appliRegiService.getServiceRegistLists(l_strInstitutionCode,
                l_stateRequest.branchCode, l_srvMaster.getSrvDiv(), l_strAccountCode, false);

            String l_strLotDiv = l_appRequiredService.getLotDiv();

            //1.8.5 <���򏈗� *1>
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
            {
                //1.8.5.1 �T�[�r�X���p�ڋq�\���󋵈ꗗ���I���T�[�r�X�ꗗ�s( )
                WEB3AdminSrvRegiNoLotteryStateGroup l_noLotteryStateGroup = new WEB3AdminSrvRegiNoLotteryStateGroup();

                //1.8.5.2 ���b�Z�[�W <�v���p�e�B�Z�b�g *1>
                //���T�[�r�X�敪=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X�敪( )�̖߂�l
                l_noLotteryStateGroup.serviceDiv = l_srvMaster.getSrvDiv();

                //���T�[�r�X����=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X����( )�̖߂�l
                l_noLotteryStateGroup.serviceName = l_srvMaster.getSrvName();

                //���T�[�r�X�\���o�^�I�u�W�F�N�g�����݂���ꍇ
                if (l_appParams != null && l_appParams.length > 0)
                {
                    //���\���o�^ID=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�\���o�^ID( )�̖߂�l
                    l_noLotteryStateGroup.applyRegId =
                        WEB3StringTypeUtility.formatNumber(l_appParams[0].getRegistId());

                    //���K�p�J�n��=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�J�n��( )�̖߂�l
                    l_noLotteryStateGroup.trialStartDate = l_appParams[0].getAppliStartDate();

                    //���K�p�I����=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�I����( )�̖߂�l
                    l_noLotteryStateGroup.trialEndDate = l_appParams[0].getAppliEndDate();

                    //���o�^�敪=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�o�^�敪( )�̖߂�l
                    l_noLotteryStateGroup.registDiv = l_appParams[0].getPaymentDiv();

                    //�����p����=�T�[�r�X�\���o�^�I�u�W�F�N�g.get���p����( )�̖߂�l
                    if (!l_appParams[0].getUseAmtIsNull())
                    {
                        l_noLotteryStateGroup.chargeAmt =
                            WEB3StringTypeUtility.formatNumber(l_appParams[0].getUseAmt());
                    }

                    //���\�����I�敪���T�[�r�X�\���o�^�I�u�W�F�N�g.get�\�����I�敪( )�̖߂�l
                    l_noLotteryStateGroup.applyLotteryDiv = l_appParams[0].getAppliLotDiv();

                    //���o�^�\�敪=false
                    l_noLotteryStateGroup.registAbleDiv = false;

					//��Q�Ή� NO_2057
					try
					{
						//�Ǘ��ҍX�V�����`�F�b�N
						l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
						
						//��Q�Ή� NO_2049
						Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
						Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);
						
						//���o�^�\�敪
						if(l_srvMaster.isAppliPossible() &&
							WEB3SrvRegiCancelDivDef.USUAL_DEFAULT.equals(l_appParams[0].getCancelDiv()) &&
							WEB3DateUtility.compareToDay(l_datSystemDate, l_appParams[0].getAppliEndDate()) > 0)
						{
							//���ݓ��t���K�p�I�����̏ꍇ�˓o�^�\
							l_noLotteryStateGroup.registAbleDiv = true;
						}
						else
						{
							//���ݓ��t���K�p�I�����̏ꍇ�˓o�^�s��
							l_noLotteryStateGroup.registAbleDiv = false;
						}
						
											  					
						//(*2-1) �T�[�r�X�\���o�^�I�u�W�F�N�g.get����敪( )="�ʏ�"�̏ꍇtrue���Z�b�g����B
						if(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT.equals(l_appParams[0].getCancelDiv()))
						{
							//���ݓ��t���K�p�I�����̏ꍇ�˕ύX�\
							if(WEB3DateUtility.compareToDay(l_datSystemDate, l_appParams[0].getAppliEndDate()) <= 0)
							{
								l_noLotteryStateGroup.changeAbleDiv = true;
							}
							else
							//���ݓ��t���K�p�I�����łȂ��ꍇ�˕ύX�s�\
							{
								l_noLotteryStateGroup.changeAbleDiv = false;
							}
						}
						//(*2-2) �T�[�r�X�\���o�^�I�u�W�F�N�g.get����敪( )="���"�̏ꍇfalse���Z�b�g����B
						else
						{
							l_noLotteryStateGroup.changeAbleDiv = false;
						}
					}
					catch(WEB3BaseException ex)
					{
						//�X�V�������Ȃ��ׁA�o�^�A�ύX�{�^����\�����Ȃ��B
						//�o�^�\�敪=false
						l_noLotteryStateGroup.registAbleDiv = false;
						
						//�ύX�\�敪=false
						l_noLotteryStateGroup.changeAbleDiv = false;
					}

                    //���ŏI�X�V��=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�ŏI�X�V��( )�̖߂�l
                    l_noLotteryStateGroup.lastUpdateTime = l_appParams[0].getLastUpdatedTimestamp();

                    //���ŏI�X�V��=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�ŏI�X�V��( )�̖߂�l
                    l_noLotteryStateGroup.lastUpdater = l_appParams[0].last_updater;
                }
                //���T�[�r�X�\���o�^�I�u�W�F�N�g�����݂��Ȃ��ꍇ
                else
                {
                    //���\���o�^ID=null
                    l_noLotteryStateGroup.applyRegId = null;

                    //���K�p�J�n��=null
                    l_noLotteryStateGroup.trialStartDate = null;

                    //���K�p�I����=null
                    l_noLotteryStateGroup.trialEndDate = null;

                    //���o�^�敪=null
                    l_noLotteryStateGroup.registDiv = null;

                    //�����p����=null
                    l_noLotteryStateGroup.chargeAmt = null;

                    //���\�����I�敪=null
                    l_noLotteryStateGroup.applyLotteryDiv = null;

					//��Q�Ή� NO_2057
					try
					{
						//�Ǘ��ҍX�V�����`�F�b�N
						l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
						
						//���o�^�\�敪=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.is�\���\( )�̖߂�l
						l_noLotteryStateGroup.registAbleDiv = l_srvMaster.isAppliPossible();
						
						//���ύX�\�敪=false
						l_noLotteryStateGroup.changeAbleDiv = false;
					}
					catch(WEB3BaseException ex)
					{
						//�X�V�������Ȃ��ׁA�o�^�A�ύX�{�^����\�����Ȃ��B
						//�o�^�\�敪=false
						l_noLotteryStateGroup.registAbleDiv = false;
						
						//�ύX�\�敪=false
						l_noLotteryStateGroup.changeAbleDiv = false;
					}

                    //Q&A:WEB3-SRVREGI-A-UT-0079
                    //���ŏI�X�V��=null
                    l_noLotteryStateGroup.lastUpdateTime = null;

                    //Q&A:WEB3-SRVREGI-A-UT-0079
                    //���ŏI�X�V��=null
                    l_noLotteryStateGroup.lastUpdater = null;
                }
                //�������\���敪=(*)
                if (l_noLotteryStateGroup.changeAbleDiv)
                {
                    //(*-1) �ύX�\�敪=true �̏ꍇ�Anull ���Z�b�g����B
                    l_noLotteryStateGroup.firstApplyDiv = null;
                }
                else
                {
                    //(*-2) �ύX�\�敪=false �̏ꍇ�A
                    //�T�[�r�X���p�\���o�^�T�[�r�X.get�����\���敪( )�̖߂�l���Z�b�g����B
                    l_noLotteryStateGroup.firstApplyDiv = l_appliRegiService.getInitializeAppliDiv(
                        l_strInstitutionCode,
                        l_stateRequest.branchCode,
                        l_srvMaster.getSrvDiv(),
                        l_strAccountCode);
                }

                //add to list
                l_lisNoLotteryStateGroups.add(l_noLotteryStateGroup);
            }
            //1.8.6 <���򏈗� *2>
            else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
            {
                //���T�[�r�X�\���o�^�I�u�W�F�N�g�����݂��Ȃ��ꍇ
                if (l_appParams == null || l_appParams.length == 0)
                {
                    //1.8.6.1.1 �T�[�r�X���p�ڋq�\���󋵈ꗗ���I�L�T�[�r�X�ꗗ�s( )
                    WEB3AdminSrvRegiLotteryStateGroup l_lotteryStateGroup = new WEB3AdminSrvRegiLotteryStateGroup();

                    //1.8.6.1.2 <�v���p�e�B�Z�b�g *2>
                    //���T�[�r�X�敪=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X�敪( )�̖߂�l
                    l_lotteryStateGroup.serviceDiv = l_srvMaster.getSrvDiv();

                    //���T�[�r�X����=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X����( )�̖߂�l
                    l_lotteryStateGroup.serviceName = l_srvMaster.getSrvName();

                    //���\���o�^ID=null
                    l_lotteryStateGroup.applyRegId = null;

                    //���K�p�J�n��=null
                    l_lotteryStateGroup.trialStartDate = null;

                    //���K�p�I����=null
                    l_lotteryStateGroup.trialEndDate = null;

                    //���o�^�敪=null
                    l_lotteryStateGroup.registDiv = null;

                    //�����p����=null
                    l_lotteryStateGroup.chargeAmt = null;

                    //���\�����I�敪=null
                    l_lotteryStateGroup.applyLotteryDiv = null;

                    //���\����=null
                    l_lotteryStateGroup.applyDate = null;

                    //���ŏI�X�V��=null
                    l_lotteryStateGroup.lastUpdateTime = null;

                    //���ŏI�X�V��=null
                    l_lotteryStateGroup.lastUpdater = null;
                    
					//��Q�Ή� NO_2057
					try
					{
						//�Ǘ��ҍX�V�����`�F�b�N
						l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
						
						//���o�^�\�敪=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.is�\���\( )�̖߂�l
						l_lotteryStateGroup.registAbleDiv = l_srvMaster.isAppliPossible();
						
						//���ύX�\�敪=false
						l_lotteryStateGroup.changeAbleDiv = false;
					}
					catch(WEB3BaseException ex)
					{
						//�X�V�������Ȃ��ׁA�o�^�A�ύX�{�^����\�����Ȃ��B
						//�o�^�\�敪=false
						l_lotteryStateGroup.registAbleDiv = false;
						
						//�ύX�\�敪=false
						l_lotteryStateGroup.changeAbleDiv = false;
					}
                     
                    //��Q�Ή�U01577
					//�������\���敪=(*)
					if (l_lotteryStateGroup.changeAbleDiv)
					{
						//(*-1) �ύX�\�敪=true �̏ꍇ�Anull ���Z�b�g����B
						l_lotteryStateGroup.firstApplyDiv = null;
					}
					else
					{
						//(*-2) �ύX�\�敪=false �̏ꍇ�A
						//�T�[�r�X���p�\���o�^�T�[�r�X.get�����\���敪( )�̖߂�l���Z�b�g����B
						l_lotteryStateGroup.firstApplyDiv = l_appliRegiService.getInitializeAppliDiv(
							l_strInstitutionCode,
							l_stateRequest.branchCode,
							l_srvMaster.getSrvDiv(),
							l_strAccountCode);
					}

                    //add to list
                    l_lisLotteryStateGroups.add(l_lotteryStateGroup);
                }
                else
                {
                    //���T�[�r�X�\���o�^�I�u�W�F�N�g�����݂���ꍇ

                    //1.8.6.1.1 �T�[�r�X���p�ڋq�\���󋵈ꗗ���I�L�T�[�r�X�ꗗ�s( )
                    WEB3AdminSrvRegiLotteryStateGroup l_lotteryStateGroup = new WEB3AdminSrvRegiLotteryStateGroup();

                    //1.8.6.1.2 <�v���p�e�B�Z�b�g *2>
                    //���T�[�r�X�敪=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X�敪( )�̖߂�l
                    l_lotteryStateGroup.serviceDiv = l_srvMaster.getSrvDiv();

                    //���T�[�r�X����=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X����( )�̖߂�l
                    l_lotteryStateGroup.serviceName = l_srvMaster.getSrvName();

                    //���\���o�^ID=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�\���o�^ID( )�̖߂�l
                    l_lotteryStateGroup.applyRegId =
                        WEB3StringTypeUtility.formatNumber(l_appParams[0].getRegistId());

                    //���K�p�J�n��=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�J�n��( )�̖߂�l
                    l_lotteryStateGroup.trialStartDate = l_appParams[0].getAppliStartDate();

                    //���K�p�I����=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�I����( )�̖߂�l
                    l_lotteryStateGroup.trialEndDate = l_appParams[0].getAppliEndDate();

                    //���o�^�敪=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�o�^�敪( )�̖߂�l
                    l_lotteryStateGroup.registDiv = l_appParams[0].getPaymentDiv();

                    //�����p����=�T�[�r�X�\���o�^�I�u�W�F�N�g.get���p����( )�̖߂�l
                    if (!l_appParams[0].getUseAmtIsNull())
                    {
                        l_lotteryStateGroup.chargeAmt =
                           WEB3StringTypeUtility.formatNumber(l_appParams[0].getUseAmt());
                    }

                    //���\�����I�敪=(*1)
                    //(*1-1) �T�[�r�X�\���o�^�I�u�W�F�N�g.get����敪( )="�ʏ�"�̏ꍇ
                    if (WEB3SrvRegiCancelDivDef.USUAL_DEFAULT.equals(l_appParams[0].getCancelDiv()))
                    {
                        //(*1-1-1) �T�[�r�X�\���o�^�I�u�W�F�N�g.get�\�����I�敪( )="�������I"�̏ꍇ
                        if (WEB3SrvRegiAppliLotDivDef.AUTO_ELECTION.equals(l_appParams[0].getAppliLotDiv()))
                        {
                            //(*1-1-1-1)�T�[�r�X�\���o�^�I�u�W�F�N�g.get�������I���������( )�����ݓ��t�̏ꍇ"�\��"���Z�b�g����B
                            if (WEB3DateUtility.compareToDay(l_appParams[0].getCancelLimitDate(), GtlUtils.getTradingSystem().getSystemTimestamp()) >= 0)
                            {
                                l_lotteryStateGroup.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.APPLI;
                            }
                            else
                            {
                                //(*1-1-1-2)�T�[�r�X�\���o�^�I�u�W�F�N�g.get�������I���������( )�����ݓ��t�̏ꍇ"���I�^�{�\��"���Z�b�g����B
                                l_lotteryStateGroup.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI;
                            }
                        }
                        else
                        {
                            //(*1-1-2) �T�[�r�X�\���o�^�I�u�W�F�N�g.get�\�����I�敪( )="�������I"�ȊO�̏ꍇ
                            //�T�[�r�X�\���o�^�I�u�W�F�N�g.get�\�����I�敪( )���Z�b�g����B
                            l_lotteryStateGroup.applyLotteryDiv = l_appParams[0].getAppliLotDiv();
                        }
                    }
                    else if (WEB3SrvRegiCancelDivDef.CANCEL.equals(l_appParams[0].getCancelDiv()))
                    {
                        //(*1-2) �T�[�r�X�\���o�^�I�u�W�F�N�g.get����敪( )="���"�̏ꍇ"���"���Z�b�g����B
                        l_lotteryStateGroup.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.CANCEL;
                    }

                    //���ŏI�X�V��=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�ŏI�X�V��( )�̖߂�l
                    l_lotteryStateGroup.lastUpdateTime = l_appParams[0].getLastUpdatedTimestamp();

                    //���ŏI�X�V��=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�ŏI�X�V��( )�̖߂�l
                    l_lotteryStateGroup.lastUpdater = l_appParams[0].getLastUpdater();

                    //���\����=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�\����( )�̖߂�l
                    l_lotteryStateGroup.applyDate = l_appParams[0].getAppliDate();

					//��Q�Ή� NO_2057
					try
					{
						//�Ǘ��ҍX�V�����`�F�b�N
						l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
						
						//��Q�Ή� NO_2049
						//���ύX�\�敪=(*2)
						Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
						Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);
						
						//���o�^�\�敪
						if(l_srvMaster.isAppliPossible() &&
							WEB3SrvRegiCancelDivDef.USUAL_DEFAULT.equals(l_appParams[0].getCancelDiv()) &&
							WEB3DateUtility.compareToDay(l_datSystemDate, l_appParams[0].getAppliEndDate()) > 0)
						{
							//���ݓ��t���K�p�I�����̏ꍇ�˓o�^�\
							l_lotteryStateGroup.registAbleDiv = true;
						}
						else
						{
							//���ݓ��t���K�p�I�����̏ꍇ�˓o�^�s��
							l_lotteryStateGroup.registAbleDiv = false;
						}
					
						//(*2-1) �T�[�r�X�\���o�^�I�u�W�F�N�g.get����敪( )="�ʏ�"�̏ꍇtrue���Z�b�g����B
						if(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT.equals(l_appParams[0].getCancelDiv()))
						{
							//���ݓ��t���K�p�I�����̏ꍇ�˕ύX�\
							if(WEB3DateUtility.compareToDay(l_datSystemDate, l_appParams[0].getAppliEndDate()) <= 0)
							{
								l_lotteryStateGroup.changeAbleDiv = true;
							}
							else
							//���ݓ��t���K�p�I�����łȂ��ꍇ�˕ύX�s�\
							{
								l_lotteryStateGroup.changeAbleDiv = false;
							}
						}
						//(*2-2) �T�[�r�X�\���o�^�I�u�W�F�N�g.get����敪( )="���"�̏ꍇfalse���Z�b�g����B
						else
						{
							l_lotteryStateGroup.changeAbleDiv = false;
						}
					}
					catch(WEB3BaseException ex)
					{
						//�X�V�������Ȃ��ׁA�o�^�A�ύX�{�^����\�����Ȃ��B
						//�o�^�\�敪=false
						l_lotteryStateGroup.registAbleDiv = false;
						
						//�ύX�\�敪=false
						l_lotteryStateGroup.changeAbleDiv = false;
					}

                    //�������\���敪=(*)
                    if (l_lotteryStateGroup.changeAbleDiv)
                    {
                        //(*-1) �ύX�\�敪=true �̏ꍇ�Anull ���Z�b�g����B
                        l_lotteryStateGroup.firstApplyDiv = null;
                    }
                    else
                    {
                        //(*-2) �ύX�\�敪=false �̏ꍇ�A
                        //�T�[�r�X���p�\���o�^�T�[�r�X.get�����\���敪( )�̖߂�l���Z�b�g����B
                        l_lotteryStateGroup.firstApplyDiv = l_appliRegiService.getInitializeAppliDiv(
                            l_strInstitutionCode,
                            l_stateRequest.branchCode,
                            l_srvMaster.getSrvDiv(),
                            l_strAccountCode);
                    }

                    //add to list
                    l_lisLotteryStateGroups.add(l_lotteryStateGroup);
                }
            }
        }

        //1.9 create���X�|���X( )
        WEB3AdminSrvRegiStateResponse l_response = (WEB3AdminSrvRegiStateResponse)l_request.createResponse();

        WEB3AdminSrvRegiNoLotteryStateGroup[] l_noLotteryStateGroups =
            new WEB3AdminSrvRegiNoLotteryStateGroup[l_lisNoLotteryStateGroups.size()];
        l_lisNoLotteryStateGroups.toArray(l_noLotteryStateGroups);

        WEB3AdminSrvRegiLotteryStateGroup[] l_lotteryStateGroups =
            new WEB3AdminSrvRegiLotteryStateGroup[l_lisLotteryStateGroups.size()];
        l_lisLotteryStateGroups.toArray(l_lotteryStateGroups);

        l_response.noLotDetails = l_noLotteryStateGroups;
        l_response.lotDetails = l_lotteryStateGroups;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
