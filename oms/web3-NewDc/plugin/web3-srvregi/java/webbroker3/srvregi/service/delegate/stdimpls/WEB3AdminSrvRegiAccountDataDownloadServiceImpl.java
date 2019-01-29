head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h�T�[�r�XImpl(WEB3AdminSrvRegiAccountDataDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
Revesion History : 2007/04/06 ꎉ�  (���u) �d�l�ύX���f�� 235
Revesion History : 2007/04/12 CInomata(SCS) �d�l�ύX���f�� 237
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.srvregi.WEB3AdminSrvRegiAccountDataDownloadCsv;
import webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataDownloadService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h�T�[�r�XImpl)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h�T�[�r�X�����N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataDownloadServiceImpl implements WEB3AdminSrvRegiAccountDataDownloadService 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountDataDownloadServiceImpl.class);
    
    /**
     * @@roseuid 416F39290167
     */
    public WEB3AdminSrvRegiAccountDataDownloadServiceImpl() 
    {
     
    }
    
    /**
     * �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h�������s���B<BR>
     * <BR>
     * �Q�j�@@get�_�E�����[�h�t�@@�C��()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410700660159
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminSrvRegiDownloadRequest)
        {
            //�Q�j�@@get�_�E�����[�h�t�@@�C��()���R�[������
            l_response = this.getDownloadFile(
                (WEB3AdminSrvRegiDownloadRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = "�p�����[�^�̗ތ^���s���B";
            log.debug(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�_�E�����[�h�t�@@�C���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u(�T�[�r�X���p�Ǘ���)�ڋq�_�E�����[�h�Eget�_�E�����[�h�t�@@�C���v�Q��<BR>
     * ====================================================================
     * �V�[�P���X�}�u(�T�[�r�X���p�Ǘ���)�ڋq�_�E�����[�h�Eget�_�E�����[�h�t�@@�C���v
     * 1.11 get�T�[�r�X�\���o�^�ꗗ()�̖߂�l.size() > get�v���t�@@�����X()�̖߂�l�̏ꍇ�A
     *      ��O�iBUSINESS_ERROR_01957�j���X���[����B
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01957<BR>
     * ====================================================================
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 410706B80157
     */
    protected WEB3AdminSrvRegiDownloadResponse getDownloadFile(WEB3AdminSrvRegiDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminSrvRegiDownloadRequest )";
        log.entering(STR_METHOD_NAME );
        
        //1.1 validate( )
        l_request.validate();
        
        //1.3 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, false);
        
        //1.5 validate���X����(String[])
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.6 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7 �ڋq�f�[�^CSV( )
        WEB3AdminSrvRegiAccountDataDownloadCsv l_accountDataDownloadCsv = 
            new WEB3AdminSrvRegiAccountDataDownloadCsv();
            
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        String l_strAccountCodeInDb = null;
        
        //��Q�Ή� NO_2109
		if (l_request.accountCode != null && l_request.branchCode != null)
		{
			int l_cntAccount = 0;
			
			for (int i = 0; i < l_request.branchCode.length; i++)
			{
				try
				{
					//1.8 get�ڋq(String, String, String)
					WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(l_strInstitutionCode, l_request.branchCode[i], l_request.accountCode);
        
					//1.9 getAccountCode( ) �ڋq�R�[�h7���擾
					l_strAccountCodeInDb = l_mainAccount.getAccountCode();
                    
					//�ڋq�}�X�^�[�ɑ��݂��鐔��count
					l_cntAccount++;
                    
					log.debug("�y�ڋq�R�[�h���݃`�F�b�NOK�z : " + l_request.accountCode);
					break;
				}
				catch (WEB3BaseException l_e)
				{
					log.debug("�y�ڋq�R�[�h���݃`�F�b�N ���X �F"+l_request.branchCode[i]+"�z�Y���Ȃ�");
					continue;
				}
			}
            
			//���͂��ꂽ�ڋq�R�[�h���ڋq�}�X�^�[�ɑ��݂��Ȃ��ꍇ
			if(l_cntAccount == 0)
			{
				log.debug("�y�ڋq�R�[�h���݃`�F�b�NNG�z : " + l_request.accountCode);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01987,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"�ڋq�}�X�^�e�[�u���Ōڋq�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ");
			}
            
		}
        
            
        //1.10 get�T�[�r�X�\���o�^�ꗗ
        WEB3SrvRegiRegistService l_appliRegiService = 
            (WEB3SrvRegiRegistService) Services.getService(WEB3SrvRegiRegistService.class);
        
        Timestamp l_tsTrialStartFrom = null;
        Timestamp l_tsTrialStartTo = null;
        Timestamp l_tsApplyDateFrom = null;
        Timestamp l_tsApplyDateTo = null;
        if (l_request.trialStartFrom != null)
        {
            l_tsTrialStartFrom = new Timestamp(l_request.trialStartFrom.getTime());
        }
        if (l_request.trialStartTo != null)
        {
            l_tsTrialStartTo = new Timestamp(l_request.trialStartTo.getTime());
        }
        if (l_request.applyDateFrom != null)
        {
            l_tsApplyDateFrom = new Timestamp(l_request.applyDateFrom.getTime());
        }
        if (l_request.applyDateTo != null)
        {
            l_tsApplyDateTo = new Timestamp(l_request.applyDateTo.getTime());        
        }
        SrvRegiApplicationParams[] l_appParams =
            l_appliRegiService.getServiceRegistLists(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.serviceDiv,
                l_strAccountCodeInDb,
                l_request.registDiv,
                l_request.applyLotteryDiv,
                l_tsTrialStartFrom,
                l_tsTrialStartTo,
                l_tsApplyDateFrom,
                l_tsApplyDateTo,
                l_request.sortKeys);

        //get�v���t�@@�����X(String)
        //�����Ŏw�肳�ꂽ�ݒ薼�̂̐ݒ�l���V�X�e���v���t�@@�����X�e�[�u������擾����B
        //[this.get�v���t�@@�����X�Ɏw�肷�����]
        //�ݒ薼�́F�@@"DL_REC_COUNT_SERVICE_ACCOUNT_DATA"
        String l_strPreference =
            this.getPreference(WEB3SystemPreferencesNameDef.DL_REC_COUNT_SERVICE_ACCOUNT_DATA);

        //get�T�[�r�X�\���o�^�ꗗ()�̖߂�l.size() > get�v���t�@@�����X()�̖߂�l�̏ꍇ�A
        //��O�iBUSINESS_ERROR_01957�j���X���[����B
        int l_intParamsLength = l_appParams.length;
        int l_intPreference = Integer.parseInt(l_strPreference);
        
        if (l_intParamsLength > l_intPreference)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01957,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y�����錏�����_�E�����[�h�����𒴂��Ă��܂��B");
        }

        //1.11 (*)�擾�����ڋq�f�[�^�̗v�f�����ALoop����
        int l_intRowCount = l_appParams.length;
        for (int i = 0; i < l_intRowCount; i++)
        {
            SrvRegiApplicationParams l_params = l_appParams[i];
            
            //1.11.1 add���׍s( )
            int l_intIndex = l_accountDataDownloadCsv.addRow();
            
            //1.11.2 set�\���o�^ID(int, String)
            l_accountDataDownloadCsv.setRegistId(l_intIndex, WEB3StringTypeUtility.formatNumber(l_params.getRegistId()));
            
            //1.11.3 set�،���ЃR�[�h(int, String)
            l_accountDataDownloadCsv.setInstitutionCode(l_intIndex, l_params.getInstitutionCode());
            
            //1.11.4 set���X�R�[�h(int, String)
            l_accountDataDownloadCsv.setBranchCode(l_intIndex, l_params.getBranchCode());
            
            //1.11.5 set�T�[�r�X�敪(int, String)
            l_accountDataDownloadCsv.setSrvDiv(l_intIndex, l_params.getSrvDiv());
            
            //1.11.6 set�ڋq(int, String, String, String)
            try
            {
                //WEB3-SRVREGI-A-UT-0089
                l_accountDataDownloadCsv.setAccount(
                    l_intIndex, 
                    l_params.getInstitutionCode(),
                    l_params.getBranchCode(),
                    l_params.getAccountCode());//WEB3BaseException

            }
            catch (WEB3BaseException l_e)
            {
                //1.11.7 (*)�ڋq�I�u�W�F�N�g�擾�Ɏ��s�����ꍇ
                //1.11.7.1 delete���׍s(int)
                l_accountDataDownloadCsv.deleteRow(l_intIndex);
                
				log.debug("�y�ڋq���擾�G���[�i���׍폜�j�z"+l_params.getAccountCode().substring(0,6));
                
                //1.11.7.2 continue
                continue;
            }
            
            //1.11.8 set�o�^�敪(int, String)
            l_accountDataDownloadCsv.setPaymentDiv(l_intIndex, l_params.getPaymentDiv());
            
            //1.11.9 set�\�����I�敪(int, String)
            l_accountDataDownloadCsv.setAppliLotDiv(
            		l_intIndex,
            		l_params.getAppliLotDiv(),
					l_params.getCancelDiv(),
					l_params.getCancelLimitDate());
            
            //1.11.10 set�\����(int, Date)
            l_accountDataDownloadCsv.setAppliDate(l_intIndex, l_params.getAppliDate());
            
            //1.11.11 set�K�p�J�n��(int, Date)
            l_accountDataDownloadCsv.setAppliStartDate(l_intIndex, l_params.getAppliStartDate());
            
            //1.11.12 set�K�p�I����(int, Date)
            l_accountDataDownloadCsv.setAppliEndDate(l_intIndex, l_params.getAppliEndDate());
            
            //1.11.13 set���p����(int, double)
            l_accountDataDownloadCsv.setUseAmt(l_intIndex, l_params.getUseAmt());
            
            //1.11/14 set�o����(int, Date)
            l_accountDataDownloadCsv.setPaymentDate(l_intIndex, l_params.getPaymentDate());
            
            //�d�l�ύX�Ή� NO_207
            try
            {
	            //1.11.15 set�o���]��(int, String, String, String)
	            l_accountDataDownloadCsv.setPaymentPower(
	                l_intIndex,
	                l_params.getInstitutionCode(),
	                l_params.getBranchCode(),
                    l_params.getAccountCode());
            }
			catch (WEB3BaseException e)
			{
				//1.11.16 (*)�o���]�͂̎擾�Ɏ��s�����ꍇ
				//1.11.16.1 delete���׍s(int)
				l_accountDataDownloadCsv.deleteRow(l_intIndex);
                
				log.debug("�yset�o���]�̓G���[�i���׍폜�j�z"+l_params.getAccountCode().substring(0,6));
				//1.11.16.2 continue
				continue;
			}
        }
        
        //1.12 getCSV�t�@@�C���s( )
        String[] l_strCsvFileLines = l_accountDataDownloadCsv.getCsvFileLines();
        
        //1.13 �T�[�r�X���p�Ǘ��҃_�E�����[�h���X�|���X( )
        WEB3AdminSrvRegiDownloadResponse l_response = 
            (WEB3AdminSrvRegiDownloadResponse)l_request.createResponse();
        
        //1.14 (*)�v���p�e�B�Z�b�g  
        l_response.lines = l_strCsvFileLines;
        l_response.currentDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�v���t�@@�����X)<BR>
     * �����Ŏw�肳�ꂽ�ݒ薼�̂̐ݒ�l���V�X�e���v���t�@@�����X�e�[�u������擾����B<BR>
     * <BR>
     * �P�j�V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B<BR>
     * <BR>
     * �@@[�擾����]<BR>
     * �@@���́i���ϐ����j = �i�����j�ݒ薼��<BR>
     * <BR>
     * �Q�j�擾�����V�X�e���v���t�@@�����X�e�[�u���̃��R�[�h�̐ݒ�l��ԋp����B<BR>
     * @@param l_strSetName - (�ݒ薼��)<BR>
     * �ݒ薼��
     * @@return String
     * @@throws WEB3BaseException
     */
    private String getPreference(String l_strSetName) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPreference(String) ";
        log.entering(STR_METHOD_NAME);

        //�����Ŏw�肳�ꂽ�ݒ薼�̂̐ݒ�l���V�X�e���v���t�@@�����X�e�[�u������擾����B
        //�@@[�擾����]
        //�@@���́i���ϐ����j = �i�����j�ݒ薼��
        SystemPreferencesRow l_sysPreferenceRow = null;
        try
        {
            l_sysPreferenceRow = SystemPreferencesDao.findRowByPk(l_strSetName);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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

        //�Q�j�擾�����V�X�e���v���t�@@�����X�e�[�u���̃��R�[�h�̐ݒ�l��ԋp����B
        String l_strValue = l_sysPreferenceRow.getValue();

        log.exiting(STR_METHOD_NAME);
        return l_strValue;
    }
}
@
