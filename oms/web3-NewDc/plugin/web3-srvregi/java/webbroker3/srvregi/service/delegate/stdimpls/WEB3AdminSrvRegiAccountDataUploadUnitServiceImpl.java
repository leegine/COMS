head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�ڋq�f�[�^�A�b�v���[�hUnitServiceImpl(WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �A�C��(���u) �V�K�쐬
Revesion History : 2007/06/05 ��іQ(���u) ���f��240,�c�a�X�V�d�l 036
Revesion History : 2007/06/28 �����Q(���u) ���f��276
Revesion History : 2007/07/11 �Ј���(���u) ���f��279 ���f��283
Revesion History : 2007/07/27 ����(���u)�@@���f��293 ���f��296 ���f��297,�c�a�X�V�d�l 039
Revesion History : 2008/02/26 ���g �d�l�ύX ���f��324
Revesion History : 2008/03/03 ���g �d�l�ύX ���f��334
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.common.define.WEB3SrvAppliAttributeProcDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.srvregi.WEB3AdminSrvRegiAccountDataUploadCsv;
import webbroker3.srvregi.WEB3SrvRegiChangeAppliSpec;
import webbroker3.srvregi.WEB3SrvRegiNewAppliSpec;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.data.SrvAppliAttributeDao;
import webbroker3.srvregi.data.SrvAppliAttributePK;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUploadUnitService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�ڋq�f�[�^�A�b�v���[�hUnitServiceImpl)<BR>
 * �T�[�r�X���p�ڋq�f�[�^�A�b�v���[�hUnitService�@@�����N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl 
    implements WEB3AdminSrvRegiAccountDataUploadUnitService 
{
    
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl.class);
    
    /**
     * @@roseuid 416F392C0148
     */
    public WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl() 
    {
     
    }
    
    /**
     * (update�\���o�^)<BR>
     * update�\���o�^�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u(�T�[�r�X���p)�ڋq�f�[�^�A�b�v���[�h�Eupdate�\���o�^�v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strUploadDiv - (�A�b�v���[�h�敪)<BR>
     * �o�^�^�ύX�^���I���ʃA�b�v���[�h<BR>
     * @@param l_registId �\���o�^ID<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * @@param l_tsAppliEndDate - (�K�p�I����)<BR>
     * @@param l_tsAppliDate - (�\����)<BR>
     * @@param l_strAppliLotDiv - (�\�����I�敪)<BR>
     * ���p�^�\���^���I�i�{�\���j�^���I�^���<BR>
     * @@param l_strPaymentDiv - (�o�^�敪)<BR>
     * �����^�L��<BR>
     * @@param l_dblUseAmt - (���p����)<BR>
     * @@param l_tsPaymentDate - (�o����)<BR>
     * @@param l_strPassword - (�Ïؔԍ�)<BR>
     * @@roseuid 41109351035E
     */
    public void updateAppliRegist(
        WEB3GentradeSubAccount l_subAccount,
        String l_strUploadDiv, 
        Long   l_registId, 
        String l_strInstitutionCode, 
        String l_strSrvDiv, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        Timestamp l_tsAppliStartDate, 
        Timestamp l_tsAppliEndDate, 
        Timestamp l_tsAppliDate, 
        String l_strAppliLotDiv, 
        String l_strPaymentDiv, 
		Double l_dblUseAmt, 
        Timestamp l_tsPaymentDate, 
        String l_strPassword) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " updateAppliRegist(" +
            "WEB3GentradeSubAccount , " +
            "String , " +
            "Long   , " +           
            "String , " +
            "String , " +
            "String , " +
            "String , " +
            "Timestamp , " +
            "Timestamp , " +
            "Timestamp , " +
            "String , " +
            "String , " +
            "Double , " +
            "Timestamp , " +
            "String )";
        log.entering(STR_METHOD_NAME );
        
        try
        {
            //1.1 getInstitution(arg0 : �_���r���[::java::lang::String)
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //�g���A�J�E���g�}�l�[�W���擾����
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
        
            //1.2 getMainAccount(arg0 : long, arg1 : �_���r���[::java::lang::String, arg2 : �_���r���[::java::lang::String)
            MainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_institution.getInstitutionId(),
                l_strBranchCode, 
                l_strAccountCode);
                
			//���������b�N����B 
			//�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B  
			WEB3GentradeAccountManager l_gentradeAccMgr = 
				(WEB3GentradeAccountManager) l_finApp.getAccountManager();
				
			l_gentradeAccMgr.lockAccount(
				l_strInstitutionCode,
				l_strBranchCode,
				l_strAccountCode);

            //get�T�[�r�X�}�X�^�[(String, String, boolean)
            //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h
			//�T�[�r�X�敪 = ����.�T�[�r�X�敪
			//is�s���b�N = false
            WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement =
                new WEB3SrvRegiServiceInfoManagement();
            WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
                l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

            WEB3SrvRegiRegistService l_registService = 
                (WEB3SrvRegiRegistService) Services.getService(
                    WEB3SrvRegiRegistService.class);
            //1.4 �����򏈗����V�K�o�^�̏ꍇ�ɁA�]�͂̍S���ƃT�[�r�X�\���o�^�ւ̃f�[�^�}�����s��
            if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv))
            {
                Long l_lngOrderId = null;
                //1.4.1 �����򏈗����\�����I�敪���\�����邢�͓��I�A���A�o�^�敪���L���̏ꍇ�ɁA�o���]�͂��S������B
                if ((WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ELECTION_LABEL.equals(l_strAppliLotDiv)
                        || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_APPLI_LABEL.equals(l_strAppliLotDiv))
                    && WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_DIV_CHARGE_LABEL.equals(l_strPaymentDiv))
                {
                    //get����p�X���[�h�i�⏕����, ����.�Ïؔԍ��j
                    String l_strTradingPassword = l_registService.getTradingPassword(l_subAccount, l_strPassword);
                    
                    //1.4.1.1 submit�]�͍S��(�⏕����, Trader, double, Timestamp, String, String, String)
                    long l_lngReturn = l_registService.submitRemainingPowerRestraint(
                        l_subAccount, 
                        null, 
                        l_dblUseAmt.doubleValue(), 
                        l_tsPaymentDate, 
                        l_strSrvDiv, 
                        null, 
                        l_strTradingPassword);       
                    l_lngOrderId = new Long(l_lngReturn);
                }
                //1.4.2 create�T�[�r�X���p�V�K�\�����e(String, String, String, String, Timestamp, Timestamp, Timestamp, String, Double, Timestamp)
                WEB3SrvRegiNewAppliSpec l_spec = WEB3SrvRegiNewAppliSpec.createSrvRegiNewAppliSpec(
                    l_strInstitutionCode, 
                    l_strSrvDiv, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    l_tsAppliStartDate, 
                    l_tsAppliEndDate, 
                    l_tsAppliDate, 
                    l_strPaymentDiv, 
                    l_dblUseAmt, 
                    l_tsPaymentDate,
                    l_strAppliLotDiv);

                //1.4.3 submit�T�[�r�X�\���o�^(�T�[�r�X���p�V�K�\�����e, Long)
                l_registService.submitServiceRegist(l_spec, l_lngOrderId);

                //�����򏈗����T�[�r�X�}�X�^�[�I�u�W�F�N�g.���ꏈ���敪 = 1
                SrvRegiMasterParams l_srvRegiMasterParams =
                    (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
                String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
                if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
                {
                    //submit�O���A�g���(String, String, String, String, Timestamp, Timestamp, boolean)
                    //�،���ЃR�[�h = ����.�،���ЃR�[�h
                    //���X�R�[�h = ����.���X�R�[�h
                    //�����R�[�h = ����.�����R�[�h
                    //�T�[�r�X�敪 = ����.�f�[�^.�T�[�r�X�敪
                    //�K�p�J�n�� = ����.�K�p�J�n��
                    //�K�p�I���� = ����.�K�p�I����
                    //�V�K�\���敪 = true
                    WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                        (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
                    l_srvRegiOtherOrgService.submitOtherOrgInfo(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode,
                        l_strSrvDiv,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        true);
                }
            }
            
			//1.5 �����򏈗�������.�A�b�v���[�h�敪!=�h�o�^�h�̏ꍇ�A�ȉ��̎葱�������{����B
			if (!WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv))
			{
				// �T�[�r�X���p�\���o�^�T�[�r�X�擾
				WEB3SrvRegiRegistService l_appliRegiService = 
					(WEB3SrvRegiRegistService) Services.getService(WEB3SrvRegiRegistService.class);

				// �T�[�r�X�\���o�^�擾�i�������R�[�h�j
				WEB3GentradeSrvRegiApplication l_srvRegiApp = l_appliRegiService.getServiceRegist(
					l_strInstitutionCode,
					l_strBranchCode,
					l_strSrvDiv,
					l_strAccountCode,
					l_registId.longValue(),
					false);
				
				//1.5.1 get����ID( )
				Long l_lngOrderId = l_srvRegiApp.getOrderId();

				// get�\�����I�敪
				String l_strBeforeAppliLotDiv = l_srvRegiApp.getAppliLotDiv();
				
				//��Q�Ή� NO_2060
				// �����򏈗����������R�[�h�̐\�����I�敪���g�\���h�܂��́A�h���I�h && ����.�\�����I�敪���g���I�h 
				// && �������R�[�h.����ID!=null �̏ꍇ
				if(((WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_APPLI_LABEL.equals(l_strBeforeAppliLotDiv)) || 
					WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ELECTION_LABEL.equals(l_strBeforeAppliLotDiv)) &&
				   (WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_DEFEAT_LABEL.equals(l_strAppliLotDiv)) &&
				   (l_lngOrderId != null))
				{			
                    //get����p�X���[�h�i�⏕����, ����.�Ïؔԍ��j
                    String l_strTradingPassword = l_registService.getTradingPassword(l_subAccount, l_strPassword);

					//1.5.2 submit�]�͉��(�⏕����, long, String)
					l_registService.submitRemainingPowerRelease(
						l_subAccount, 
						l_lngOrderId.longValue(), 
                        l_strTradingPassword);
				}
			}

            //1.6 �����򏈗����ύX�o�^�A���I���ʃA�b�v���[�h�̏ꍇ�ɁA�T�[�r�X�\���o�^�̃f�[�^�ύX���s��
            if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_CHANGE_REGIST_LABEL.equals(l_strUploadDiv)
                || WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL.equals(l_strUploadDiv))
            {
                //1.6.1 create�T�[�r�X���p�ύX�\�����e(long, String, String, String, String, Timestamp, Timestamp, String, Timestamp, String, Double)
                WEB3SrvRegiChangeAppliSpec l_spec = WEB3SrvRegiChangeAppliSpec.createSrvRegiChangeAppliSpec(
                    l_registId.longValue(),
                    l_strInstitutionCode, 
                    l_strSrvDiv, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    l_tsAppliStartDate, 
                    l_tsAppliEndDate, 
                    l_strAppliLotDiv, 
                    l_tsAppliDate, 
                    l_strPaymentDiv, 
					l_dblUseAmt);

                //1.6.2 submit�T�[�r�X�\���ύX(�T�[�r�X���p�ύX�\�����e)
                l_registService.submitServiceRegistChange(l_spec);

                //�����򏈗����T�[�r�X�}�X�^�[�I�u�W�F�N�g.���ꏈ���敪 = 1
                SrvRegiMasterParams l_srvRegiMasterParams =
                    (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
                String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
                if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
                {
                    //submit�O���A�g���(String, String, String, String, Timestamp, Timestamp, boolean)
                    //�،���ЃR�[�h = ����.�،���ЃR�[�h
                    //���X�R�[�h = ����.���X�R�[�h
                    //�����R�[�h = ����.�����R�[�h
                    //�T�[�r�X�敪 = ����.�f�[�^.�T�[�r�X�敪
                    //�K�p�J�n�� = ����.�K�p�J�n��
                    //�K�p�I���� = ����.�K�p�I����
                    //�V�K�\���敪 = false
                    WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                        (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
                    l_srvRegiOtherOrgService.submitOtherOrgInfo(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode,
                        l_strSrvDiv,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        false);
                }
            }
        }
        catch (NotFoundException l_e)
        {
            WEB3BaseException l_webe = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01026,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���o�^�ڋq�G���[.",
                    l_e);
            log.error("���o�^�ڋq�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_webe;
        }


        log.exiting(STR_METHOD_NAME);     
    }

    /**
     * (insert�T�[�r�X�\������)<BR>
     * �P�j�T�[�r�X�\�������e�[�u����insert�������s���B<BR>
     * <BR>
     * �P-�P�j �g���A�J�E���g�}�l�[�W���𗘗p���Čڋq�s���擾����B<BR>
     * �@@�@@�@@�@@[get�ڋq()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@arg0�F�@@����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@arg1�F�@@����.���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@arg2�F�@@����.�ڋq�R�[�h<BR>
     * <BR>
     * �@@�P-�P-�P�j �P-�P�j�̖߂�l����V���̌ڋq�R�[�h���擾����B<BR>
     * <BR>
     * �P-�Q�j �T�[�r�X�\�������s�I�u�W�F�N�g���쐬���A�ȉ���ݒ肷��B<BR>
     * <BR>
     * �@@�@@�T�[�r�X�\�������s.�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@�T�[�r�X�\�������s.���X�R�[�h = ����.�o�^���.���X�R�[�h<BR>
     * �@@�@@�T�[�r�X�\�������s.�����R�[�h = �P-�P-�P�j�Ŏ擾�����ڋq�R�[�h<BR>
     * �@@�@@�T�[�r�X�\�������s.�T�[�r�X�敪 = ����.�T�[�r�X�敪<BR>
     * �@@�@@�T�[�r�X�\�������s.�\�������敪 = ����.�\�����I�敪<BR>
     * �@@�@@�T�[�r�X�\�������s.�K�p����From = ����.�K�p�J�n��<BR>
     * �@@�@@�T�[�r�X�\�������s.�K�p����To = ����.�K�p�I����<BR>
     * �@@�@@�T�[�r�X�\�������s.�X�V�҃R�[�h = �Z�b�V��������擾�����Ǘ��҃R�[�h<BR>
     * �@@�@@�T�[�r�X�\�������s.�쐬���� = ���ݓ���<BR>
     * �@@�@@�T�[�r�X�\�������s.�X�V���� = ���ݓ���<BR>
     * �@@�@@�T�[�r�X�\�������s.�����敪 = '0'<BR>
     * <BR>
     * �@@(�o�^���e�ɂ��Ă�DB�X�V�d�l���Q�Ɗ肢�܂��B)<BR>
     * <BR>
     * �P-�R�j QueryProcessor#doInsertQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[doInsertQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@�P-�R�j�ō쐬�����s�I�u�W�F�N�g<BR>
     * <BR>
     * �P-�S�j QueryProcessor#doInsertQuery()�̖߂�l���擾����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strMainAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_strAppliLotDiv - (�\�����I�敪)<BR>
     * ���p�^�\���^���I�i�{�\���j�^���I�^���<BR>
     * <BR>
     * ���A�b�v���[�h�敪 = 3�F�\������ �̏ꍇ<BR>
     * �����^���p�s��<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     * @@param l_tsAppliEndDate - (�K�p�I����)<BR>
     * �K�p�I����<BR>
     * @@throws WEB3BaseException
     */
    public void insertSrvApplyAttribute(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMainAccountCode,
        String l_strServiceDiv,
        String l_strAppliLotDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertSrvApplyAttribute(String, String, String, String, "
            + "String, Timestamp, Timestamp)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //�g���A�J�E���g�}�l�[�W���𗘗p���Čڋq�s���擾����B
        //[get�ڋq()�ɃZ�b�g����p�����[�^]
        //arg0�F�@@����.�،���ЃR�[�h
        //arg1�F�@@����.���X�R�[�h
        //arg2�F�@@����.�ڋq�R�[�h
        WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
            l_strInstitutionCode, l_strBranchCode, l_strMainAccountCode);

        //�P-�Q�j�̖߂�l����V���̌ڋq�R�[�h���擾����B
        String l_strAccountCode = l_mainAccount.getAccountCode();

        //�T�[�r�X�\�������s�I�u�W�F�N�g���쐬���A�ȉ���ݒ肷��
        SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();

        //�T�[�r�X�\�������s.�،���ЃR�[�h = ����.�،���ЃR�[�h
        l_srvAppliAttributeParams.setInstitutionCode(l_strInstitutionCode);

        //�T�[�r�X�\�������s.���X�R�[�h = ����.�o�^���.���X�R�[�h
        l_srvAppliAttributeParams.setBranchCode(l_strBranchCode);

        //�T�[�r�X�\�������s.�����R�[�h = �P-�Q-�P�j�Ŏ擾�����ڋq�R�[�h
        l_srvAppliAttributeParams.setAccountCode(l_strAccountCode);

        //�T�[�r�X�\�������s.�T�[�r�X�敪 = ����.�T�[�r�X�敪
        l_srvAppliAttributeParams.setSrvDiv(l_strServiceDiv);

        //�T�[�r�X�\�������s.�\�������敪 = ����.�\�����I�敪
        l_srvAppliAttributeParams.setAppliAttribute(l_strAppliLotDiv);

        //�T�[�r�X�\�������s.�K�p����From = ����.�K�p�J�n��
        l_srvAppliAttributeParams.setAppliStartDate(l_tsAppliStartDate);

        //�T�[�r�X�\�������s.�K�p����To = ����.�K�p�I����
        l_srvAppliAttributeParams.setAppliEndDate(l_tsAppliEndDate);

        //�T�[�r�X�\�������s.�X�V�҃R�[�h = �Z�b�V��������擾�����Ǘ��҃R�[�h
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        l_srvAppliAttributeParams.setLastUpdater(l_strAdministratorCode);

        //�T�[�r�X�\�������s.�쐬���� = ���ݓ���
        l_srvAppliAttributeParams.setCreatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        //�T�[�r�X�\�������s.�X�V���� = ���ݓ���
        l_srvAppliAttributeParams.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        //�T�[�r�X�\�������s.�����敪 = '0'
        l_srvAppliAttributeParams.setProcDiv(WEB3SrvAppliAttributeProcDivDef.NOT_PROCESSED);

        //�P-�S�j QueryProcessor#doInsertQuery()���\�b�h���R�[������B
        //[doInsertQuery()�ɃZ�b�g����p�����[�^]
        //arg0�F�@@�P-�R�j�ō쐬�����s�I�u�W�F�N�g
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_srvAppliAttributeParams);

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
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update�T�[�r�X�\������)<BR>
     * �T�[�r�X�\�������e�[�u���̍X�V���s���B<BR>
     * <BR>
     * �P�jMap�I�u�W�F�N�g���쐬���A�ȉ���ݒ肷��B<BR>
     * <BR>
     * �@@�@@�R�������F "appli_attribute" / �l�F ����.�\�������敪<BR>
     * �@@�@@�R�������F "appli_start_date" / �l�F����.�K�p�J�n��<BR>
     * �@@�@@�R�������F "appli_end_date" / �l�F ����.�K�p�I����<BR>
     * �@@�@@�R�������F "last_updater" / �l�F �Z�b�V��������擾�����Ǘ��҃R�[�h<BR>
     * �@@�@@�R�������F "last_updated_timestamp" / �l�F ���ݓ���<BR>
     *     �R�������F "proc_div" / �l�F '0'<BR>
     * <BR>
     * �Q�j QueryProcessor#doUpdateQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[doUpdateQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@arg0�F�i�����j�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�A�T�[�r�X�敪����ɂ���<BR>
     * �@@�@@�@@�@@�T�[�r�X�\�������e�[�u����PrimaryKey�I�u�W�F�N�g<BR>
     * �@@�@@�@@�@@arg1�F �P�j�ō쐬����Map�I�u�W�F�N�g<BR>
     * <BR>
     * (�o�^���e�ɂ��Ă�DB�X�V�d�l���Q�Ɗ肢�܂��B)<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strMainAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_strAppliLotDiv - (�\�����I�敪)<BR>
     * ���p�^�\���^���I�i�{�\���j�^���I�^���<BR>
     * <BR>
     * ���A�b�v���[�h�敪 = 3�F�\������ �̏ꍇ<BR>
     * �����^���p�s��<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     * @@param l_tsAppliEndDate - (�K�p�I����)<BR>
     * �K�p�I����<BR>
     * @@throws WEB3BaseException
     */
    public void updateSrvApplyAttribute(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMainAccountCode,
        String l_strServiceDiv,
        String l_strAppliLotDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateSrvApplyAttribute(String, String, String, String, "
            + "String, Timestamp, Timestamp)";
        log.entering(STR_METHOD_NAME);

        //�P�jMap�I�u�W�F�N�g���쐬���A�ȉ���ݒ肷��B
        Map l_mapChanges = new HashMap();

        //�R�������F "appli_attribute" / �l�F ����.�\�������敪
        l_mapChanges.put("appli_attribute", l_strAppliLotDiv);

        //�R�������F "appli_start_date" / �l�F����.�K�p�J�n��
        l_mapChanges.put("appli_start_date", l_tsAppliStartDate);

        //�R�������F "appli_end_date" / �l�F ����.�K�p�I����
        l_mapChanges.put("appli_end_date", l_tsAppliEndDate);

        //�R�������F "last_updater" / �l�F �Z�b�V��������擾�����Ǘ��҃R�[�h
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        l_mapChanges.put("last_updater", l_strAdministratorCode);

        //�R�������F "last_updated_timestamp" / �l�F ���ݓ���
        l_mapChanges.put("last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());

        //�R�������F "proc_div" / �l�F '0'
        l_mapChanges.put("proc_div", WEB3SrvAppliAttributeProcDivDef.NOT_PROCESSED);

        //�Q�j����.�ڋq�R�[�h����V���̌ڋq�R�[�h���擾����B                           
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);                    
        WEB3GentradeAccountManager l_gentradeAccountManager =                           
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();                   
                                                                                        
        //�g���A�J�E���g�}�l�[�W���𗘗p���Čڋq�s���擾����B                          
        //[get�ڋq()�ɃZ�b�g����p�����[�^]                                             
        //arg0�F�@@����.�،���ЃR�[�h                                                   
        //arg1�F�@@����.���X�R�[�h                                                       
        //arg2�F�@@����.�ڋq�R�[�h                                                       
        WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
            l_strInstitutionCode, l_strBranchCode, l_strMainAccountCode);               
                                                                                        
        String l_strAccountCode = l_mainAccount.getAccountCode();                       
                                                                                        
        //�R�jQueryProcessor#doUpdateQuery()���\�b�h���R�[������B                      
        //[doUpdateQuery()�ɃZ�b�g����p�����[�^]                                       
        //arg0�F�i�����j�،���ЃR�[�h�A���X�R�[�h�A�Q�j�Ŏ擾����7���̌ڋq�R�[�h�A      
        //�T�[�r�X�敪����ɂ����T�[�r�X�\�������e�[�u����PrimaryKey�I�u�W�F�N�g
        //arg1�F �P�j�ō쐬����Map�I�u�W�F�N�g

        SrvAppliAttributeRow l_srvAppliAttributeRow = null;
        try
        {
        	l_srvAppliAttributeRow = SrvAppliAttributeDao.findRowByPk(
        			l_strInstitutionCode, l_strBranchCode, l_strAccountCode, l_strServiceDiv);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_srvAppliAttributeRow.getPrimaryKey(), l_mapChanges);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
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
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (delete�T�[�r�X�\������)<BR>
     * �P�j�T�[�r�X�\�������e�[�u����delete�������s���B<BR>
     * <BR>
     * �P-�P�j �g���A�J�E���g�}�l�[�W���𗘗p���Čڋq�s���擾����B<BR>
     * [get�ڋq()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@����.�،���ЃR�[�h<BR>
     * �@@�@@�@@arg1�F�@@����.���X�R�[�h<BR>
     * �@@�@@�@@arg2�F�@@����.�ڋq�R�[�h<BR>
     * <BR>
     * �P-�Q�j �P-�P�j�̖߂�l����V���̌ڋq�R�[�h���擾����B<BR>
     * <BR>
     * �P-�R�jQueryProcessor.dodeleteQuery()���\�b�h���R�[������B<BR>
     * �@@�@@�@@arg0�F�@@�����Ŏ󂯎������ЃR�[�h�A���X�R�[�h�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�P�|�Q�j�Ŏ擾�����ڋq�R�[�h�A�T�[�r�X�敪������<BR>
     * �@@�@@�@@�@@�@@�@@�@@���������v���C�}���[�L�[�I�u�W�F�N�g <BR>
     * <BR>
     * �P-�S�j QueryProcessor.dodeleteQuery()�̖߂�l���擾����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strMainAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@throws WEB3BaseException
     */
    public int deleteSrvApplyAttribute(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMainAccountCode,
        String l_strServiceDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteSrvApplyAttribute(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //�g���A�J�E���g�}�l�[�W���𗘗p���Čڋq�s���擾����B
        //[get�ڋq()�ɃZ�b�g����p�����[�^]
        //arg0�F�@@����.�،���ЃR�[�h
        //arg1�F�@@����.���X�R�[�h
        //arg2�F�@@����.�ڋq�R�[�h
        WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
            l_strInstitutionCode, l_strBranchCode, l_strMainAccountCode);

        //�P-�Q�j�̖߂�l����V���̌ڋq�R�[�h���擾����B
        String l_strAccountCode = l_mainAccount.getAccountCode();

        int l_intProcessor = 0;
        try
        {
            //QueryProcessor.dodeleteQuery()���\�b�h���R�[������
            //�@@�@@ arg0�F�@@�����Ŏ󂯎������ЃR�[�h�A���X�R�[�h�A�P�|�Q�j
            //     �Ŏ擾�����ڋq�R�[�h�A�T�[�r�X�敪�����ɐ��������v���C�}���[�L�[�I�u�W�F�N�g
            SrvAppliAttributePK l_srvAppliAttributePK =
                new SrvAppliAttributePK(l_strInstitutionCode,l_strBranchCode,l_strAccountCode,l_strServiceDiv);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_intProcessor = l_queryProcessor.doDeleteQuery(
                l_srvAppliAttributePK);
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

        log.exiting(STR_METHOD_NAME);
        return l_intProcessor;
    }
}
@
