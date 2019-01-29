head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �⍇���Ǘ����⍇�����̓T�[�r�XImpl(WEB3FaqInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.faq.WEB3Faq;
import webbroker3.faq.message.WEB3FaqCompleteRequest;
import webbroker3.faq.message.WEB3FaqCompleteResponse;
import webbroker3.faq.message.WEB3FaqConfirmRequest;
import webbroker3.faq.message.WEB3FaqConfirmResponse;
import webbroker3.faq.message.WEB3FaqInputRequest;
import webbroker3.faq.message.WEB3FaqInputResponse;
import webbroker3.faq.service.delegate.WEB3FaqInputService;
import webbroker3.faq.service.delegate.WEB3FaqNumberService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.MailInfoDao;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�⍇���Ǘ����⍇�����̓T�[�r�XImpl)<BR>
 * �⍇���Ǘ����⍇�����̓T�[�r�X�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3FaqInputServiceImpl implements WEB3FaqInputService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FaqInputServiceImpl.class);   
        
    /**
     * @@roseuid 41C25C8B005D
     */
    public WEB3FaqInputServiceImpl() 
    {
     
    }
    
    /**
     * �⍇�����͏��������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�⍇���Ǘ����⍇�����̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�⍇���Ǘ����⍇���m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�⍇��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�⍇���Ǘ����⍇���������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�⍇��()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41ABFA870312
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3FaqInputRequest)
        {
            l_response = getInputScreen((WEB3FaqInputRequest)l_request);
        }
        else if (l_request instanceof WEB3FaqConfirmRequest)
        {
            l_response = validateFaq((WEB3FaqConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3FaqCompleteRequest)
        {
            l_response = submitFaq((WEB3FaqCompleteRequest)l_request);
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request
                );
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * �⍇�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�⍇���Ǘ��i�⍇�����́jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �⍇���Ǘ����⍇�����̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * 
     * @@return WEB3FaqInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41ABFA870332
     */
    protected WEB3FaqInputResponse getInputScreen(WEB3FaqInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3FaqInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //��t���ԃ`�F�b�N���s���B 
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //���X�|���X�f�[�^�𐶐�����B 
        WEB3FaqInputResponse l_response = (WEB3FaqInputResponse)l_request.createResponse();
        
        //���O�C���Z�b�V�����������h�c���擾����B 
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

        try
        {
            if (l_opLoginSec.isAccountIdSet())
            {
                //�����h�c�iaccountId�j���擾�ł����ꍇ�A���O�C���ς݂Ɣ��f���A
                //�ڋq�̏������X�|���X�ɃZ�b�g����B 
            
                long l_lngAccountId = l_opLoginSec.getAccountId();
            
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                AccountManager l_accountMgr = l_finApp.getAccountManager();

                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_lngAccountId);
                MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            
                //���X�R�[�h�F�@@�ڋq.�ڋq�s.���X�R�[�h
                l_response.branchCode = l_mainAccountRow.getBranchCode();            

                //�ڋq�R�[�h�F�@@�ڋq.get�\���ڋq�R�[�h()
                l_response.accountCode = l_mainAccount.getDisplayAccountCode();
            
                //�ڋq���F�@@�ڋq.get�ڋq�\����()
                l_response.accountName = l_mainAccount.getDisplayAccountName();
            
                //���[���A�h���X�F�@@�ڋq.�ڋq�s.email�A�h���X
                l_response.mailAddress = l_mainAccountRow.getEmailAddress();
            }               
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
                );
        }
        catch (IllegalSessionStateException l_ex)
        {
            log.debug("�����O�C���̏ꍇ",l_ex);
            l_response.branchCode = l_request.branchCode;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�⍇��)<BR>
     * �⍇�����͊m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>  
     * �u�⍇���Ǘ��i�⍇�����́jvalidate�⍇���v�Q�ƁB <BR>
     * <BR>
     * =============================================== <BR>
     *          �V�[�P���X�} : ���⍇������ / �⍇���Ǘ��i�⍇�����́jvalidate�⍇�� <BR>
     *          ��̈ʒu     : 1.6 ���[���A�h���X�Ƃ��ēK�؂Ȓl�łȂ��ꍇ�iisMailAddress() == false�j�A<BR>
     *                         ��O���X���[����B <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00777 <BR>
     * =============================================== <BR>
     * @@param l_request - �⍇���Ǘ����⍇���m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3FaqConfirmResponse
     * @@roseuid 41ABFA870351
     */
    protected WEB3FaqConfirmResponse validateFaq(WEB3FaqConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateFaq(WEB3FaqConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();

        //��t���ԃ`�F�b�N���s���B 
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //���O�C���Z�b�V�����������h�c���擾����B 
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

        //�����h�c���擾�ł��Ȃ������ꍇ�i�����O�C���̏ꍇ�j            
        //�����O�C���̏ꍇ�A���[���A�h���X�̕K�{�`�F�b�N���s���B            
        try
        {
              
            if (!l_opLoginSec.isAccountIdSet())
            {
                l_request.faqInfo.validateMailAddress();
                //���[���A�h���X�Ƃ��ēK�؂ȕ����񂩂𔻒肷��B
                if (!WEB3StringTypeUtility.isMailAddress(l_request.faqInfo.mailAddress))
                {
                    //���[���A�h���X�Ƃ��ēK�؂Ȓl�łȂ��ꍇ�iisMailAddress() == false�j�A��O���X���[����B 
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                        this.getClass().getName() + STR_METHOD_NAME,
                        "���[���A�h���X�Ƃ��ēK�؂ł͂���܂���B�u���[���A�h���X�v = " + l_request.faqInfo.mailAddress);  
                }
            }
            else
            {
                if ((l_request.faqInfo.subject == null) || ( "".equals(l_request.faqInfo.subject)))
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00871,
                        this.getClass().getName() + STR_METHOD_NAME
                        );
                }
            }
                                      
        }
        catch(IllegalSessionStateException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            l_request.faqInfo.validateMailAddress();
            //���[���A�h���X�Ƃ��ēK�؂ȕ����񂩂𔻒肷��B
            if (!WEB3StringTypeUtility.isMailAddress(l_request.faqInfo.mailAddress))
            {
                //���[���A�h���X�Ƃ��ēK�؂Ȓl�łȂ��ꍇ�iisMailAddress() == false�j�A��O���X���[����B 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���[���A�h���X�Ƃ��ēK�؂ł͂���܂���B�u���[���A�h���X�v = " + l_request.faqInfo.mailAddress);  
            }
        }
                
        //���X�|���X�f�[�^�𐶐�����B 
        WEB3FaqConfirmResponse l_response = (WEB3FaqConfirmResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
    
    /**
     * (submit�⍇��)<BR>
     * �⍇�����͊����������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�⍇���Ǘ��i�⍇�����́jsubmit�⍇���v�Q�ƁB <BR>
     * <BR>
     * =============================================== <BR>
     *          �V�[�P���X�} : ���⍇������ / �⍇���Ǘ��i�⍇�����́jsubmit�⍇�� <BR>
     *          ��̈ʒu     : 1.6 ���[���A�h���X�Ƃ��ēK�؂Ȓl�łȂ��ꍇ�iisMailAddress() == false�j�A<BR>
     *                         ��O���X���[����B <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00777 <BR>
     * =============================================== <BR>
     * @@param l_request - �⍇���Ǘ����⍇���������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.faq.message.WEB3FaqCompleteResponse
     * @@roseuid 41ABFA880015
     */
    protected WEB3FaqCompleteResponse submitFaq(WEB3FaqCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitFaq(WEB3FaqCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();

        //��t���ԃ`�F�b�N���s���B 
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //���O�C���Z�b�V�����������h�c���擾����B 
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        try
        {
            if (!l_opLoginSec.isAccountIdSet())
            {
                //�����h�c���擾�ł��Ȃ������ꍇ�i�����O�C���̏ꍇ�j            
                //�����O�C���̏ꍇ�A���[���A�h���X�̕K�{�`�F�b�N���s���B
                l_request.faqInfo.validateMailAddress();
                //���[���A�h���X�Ƃ��ēK�؂ȕ����񂩂𔻒肷��B
                if (!WEB3StringTypeUtility.isMailAddress(l_request.faqInfo.mailAddress))
                {
                    //���[���A�h���X�Ƃ��ēK�؂Ȓl�łȂ��ꍇ�iisMailAddress() == false�j�A��O���X���[����B 
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                        this.getClass().getName() + STR_METHOD_NAME,
                        "���[���A�h���X�Ƃ��ēK�؂ł͂���܂���B�u���[���A�h���X�v = " + l_request.faqInfo.mailAddress);  
                }
            }

        }
        catch (IllegalSessionStateException l_ex)
        {
            log.debug("�����O�C���̏ꍇ",l_ex);
            l_request.faqInfo.validateMailAddress();
            //���[���A�h���X�Ƃ��ēK�؂ȕ����񂩂𔻒肷��B
            if (!WEB3StringTypeUtility.isMailAddress(l_request.faqInfo.mailAddress))
            {
                //���[���A�h���X�Ƃ��ēK�؂Ȓl�łȂ��ꍇ�iisMailAddress() == false�j�A��O���X���[����B 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���[���A�h���X�Ƃ��ēK�؂ł͂���܂���B�u���[���A�h���X�v = " + l_request.faqInfo.mailAddress);  
            }
            
        }


        
        WEB3Faq l_faq = new WEB3Faq();
        
        //�،���ЃR�[�h���Z�b�g����B
        l_faq.setInstitutionCode(l_request.institutionCode);
        

        //�����h�c���擾�ł����ꍇ
        try
        {
            l_opLoginSec.getAccountId(); 
            
            long l_lngAccountId = l_opLoginSec.getAccountId();
        
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_lngAccountId);
            
            //���X�R�[�h���Z�b�g����B
            l_faq.setBranchCode(l_mainAccount.getBranch().getBranchCode());            

            //�ڋq�R�[�h���Z�b�g����B
            l_faq.setAccountCode(l_mainAccount.getAccountCode());                                
        }
        catch (IllegalSessionStateException l_ex)
        {
            //�����h�c���擾�ł��Ȃ������ꍇ
            //���X�R�[�h�� 000�i�F�w��Ȃ��j���Z�b�g����B 
            l_faq.setBranchCode(WEB3BranchCodeDef.DEFAULT);    
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
                );
        }            
    
        //�ڋq���i�����j���Z�b�g����B 
        l_faq.setName(l_request.faqInfo.accountName);
        
        //���[���A�h���X���Z�b�g����B 
        l_faq.setEmailAddress(l_request.faqInfo.mailAddress);
        
        //�������Z�b�g����B
        l_faq.setSubject(l_request.faqInfo.subject);
        
        //�⍇�����e���Z�b�g����B
        l_faq.setFaqText(l_request.faqInfo.faqText);
        
        //�@@�\�h�c���Z�b�g����B 
        l_faq.setTransactionId(l_request.faqInfo.transactionId);
        
        //�⍇���R�[�h���̔Ԃ���B 
        WEB3FaqNumberService l_faqNumberService =
            (WEB3FaqNumberService)Services.getService(WEB3FaqNumberService.class);
        String l_strFaqNumber = l_faqNumberService.getNewFaqNumber(l_request.institutionCode);
        
        //�⍇���Ǘ��s��DB��insert����B
        l_faq.saveNewFaq(l_strFaqNumber);
        
        //���[�����M�e�[�u���ɍs��}������B
        try
        {
            MailProcParams l_mailProcParams = new MailProcParams();
            //�،���ЃR�[�h = �⍇���e�[�u��.�،���ЃR�[�h            
            l_mailProcParams.setInstitutionCode(l_faq.getInstitutionCode());
                     
            //���X�R�[�h = �⍇���e�[�u��.���X�R�[�h
            l_mailProcParams.setBranchCode(l_faq.getBranchCode());
           
            //���M���[���敪 = �⍇���Ǘ��i0301�j
            l_mailProcParams.setSendmailDiv(WEB3SendmailDivDef.FAQ_INPUT);
            
            //����ID = �⍇���e�[�u��.�@@�\�h�c
            l_mailProcParams.setDiscernmentId(l_faq.getTransactionId());
            
            //�����R�[�h = �⍇���e�[�u��.�����R�[�h
            //�i�����R�[�h == null�j�̏ꍇ�A"0000000"���Z�b�g�B
            if (l_faq.getAccountCode() != null)
            {
                l_mailProcParams.setAccountCode(l_faq.getAccountCode());
            }
            else
            {
                l_mailProcParams.setAccountCode("0000000");
            }

            //���[��ID = �⍇���e�[�u��.�⍇���R�[�h�𐔒l�ɕϊ������l�B
            l_mailProcParams.setMailId(Long.parseLong(l_faq.getFaqNumber()));
            
            //�N�����P = �⍇���e�[�u��.�쐬����
            l_mailProcParams.setDate1(l_faq.getFaqDatetime());
            
            //����1 =  �⍇���e�[�u��.�ڋq���i�����j
            l_mailProcParams.setName1(l_faq.getName());
            
            //����2 = �⍇���e�[�u��.�@@�\�h�c
            l_mailProcParams.setName2(l_faq.getTransactionId());

            //�d�q���[�����M�X�e�C�^�X = 0�F�������iEmail�����M�j
            l_mailProcParams.setStatus(WEB3StatusDef.NOT_DEAL);
          
            //email�A�h���X = ���[���e�[�u��.���M��A�h���X
            //�����[���e�[�u���F�@@�،���ЃR�[�h�C���M���[���敪�C���ʂh�c�ɊY������s�̒l���g�p�B
            
            try
            {
                MailInfoRow l_mailInfoRow = (MailInfoRow)MailInfoDao.findRowByPk(
                    l_mailProcParams.institution_code,
                    l_mailProcParams.sendmail_div,
                    l_mailProcParams.discernment_id
                    );
                
                l_mailProcParams.setEmailAddress(l_mailInfoRow.getSendAddress());
            }
            catch (DataFindException l_ex)
            {
                //�Y���f�[�^���Ȃ��ꍇ�́A��O���X���[����B
                log.debug("�Y���f�[�^�����݂��Ȃ��B" + 
                    "�u�،���ЃR�[�h�v = " + l_mailProcParams.institution_code +
                    "�u���M���[���敪�v = " + l_mailProcParams.sendmail_div + 
                    "�u���ʂh�c�v = " + l_mailProcParams.discernment_id);                    
            }
            
            //���Memail�A�h���X = �⍇���e�[�u��.email�A�h���X
            l_mailProcParams.setSendEmailAddress(l_faq.getEmailAddress());
            
            //���� = �⍇���e�[�u��.����
            l_mailProcParams.setSubject(l_faq.getSubject());
            
            //���[���{�� = �⍇���e�[�u��.�⍇�����e
            l_mailProcParams.setMailText(l_faq.getFaqText());
            
            //�폜�t���O = 0:FALSE�i�L���j
            l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
            
            //��������
            Timestamp l_tsProcessDate = GtlUtils.getSystemTimestamp();
            
            //�쐬���� = ��������
            l_mailProcParams.setCreatedTimestamp(l_tsProcessDate);
            
            //�X�V���� = ��������
            l_mailProcParams.setLastUpdatedTimestamp(l_tsProcessDate);
            
                  
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                
            l_queryProcessor.doInsertQuery(l_mailProcParams);
            
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
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
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
		// �⍇����t���[����}������B
		try
		{
			MailInfoRow l_mailInfoRow = (MailInfoRow)MailInfoDao.findRowByPk(
				l_faq.getInstitutionCode(),
				WEB3SendmailDivDef.FAQ_COMPLETE,
				l_faq.getTransactionId()
				); 
					
			MailProcParams l_mailProcParams = new MailProcParams();
			
			//�،���ЃR�[�h = �⍇���e�[�u��.�،���ЃR�[�h            
			l_mailProcParams.setInstitutionCode(l_faq.getInstitutionCode());
                    
			//���X�R�[�h = �⍇���e�[�u��.���X�R�[�h
			l_mailProcParams.setBranchCode(l_faq.getBranchCode());
          
			//���M���[���敪 = �⍇����t�i0302�j
			l_mailProcParams.setSendmailDiv(WEB3SendmailDivDef.FAQ_COMPLETE);
            
			//����ID = �⍇���e�[�u��.�@@�\�h�c
			l_mailProcParams.setDiscernmentId(l_faq.getTransactionId());
            
			//�����R�[�h = �⍇���e�[�u��.�����R�[�h
			//�i�����R�[�h == null�j�̏ꍇ�A"0000000"���Z�b�g�B
			if (l_faq.getAccountCode() != null)
			{
				l_mailProcParams.setAccountCode(l_faq.getAccountCode());
			}
			else
			{
				l_mailProcParams.setAccountCode("0000000");
			}
			
			//���[��ID = �⍇���e�[�u��.�⍇���R�[�h�𐔒l�ɕϊ������l�B
			l_mailProcParams.setMailId(Long.parseLong(l_faq.getFaqNumber()));
            
			//�N�����P = �⍇���e�[�u��.�쐬����
			l_mailProcParams.setDate1(l_faq.getFaqDatetime());
            
			//����1 =  �⍇���e�[�u��.�ڋq���i�����j
			l_mailProcParams.setName1(l_faq.getName());
           
			//����2 = �⍇���e�[�u��.�@@�\�h�c
			l_mailProcParams.setName2(l_faq.getTransactionId());

			//�d�q���[�����M�X�e�C�^�X = 0�F�������iEmail�����M�j
			l_mailProcParams.setStatus(WEB3StatusDef.NOT_DEAL);
		
			//email�A�h���X = �⍇���e�[�u��.email�A�h���X
			l_mailProcParams.setEmailAddress(l_faq.getEmailAddress());
		
			//�폜�t���O = 0:FALSE�i�L���j
			l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
		
			//��������
			Timestamp l_tsProcessDate = GtlUtils.getSystemTimestamp();
           
			//�쐬���� = ��������
			l_mailProcParams.setCreatedTimestamp(l_tsProcessDate);
           
			//�X�V���� = ��������
			l_mailProcParams.setLastUpdatedTimestamp(l_tsProcessDate);
                     
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
               
			l_queryProcessor.doInsertQuery(l_mailProcParams);
					              
		}
		catch (DataFindException l_ex)
		{
			//�Y���f�[�^���Ȃ��ꍇ�́A�⍇����t���[����}�����Ȃ��B
			log.debug("�Y���f�[�^�����݂��Ȃ��ꍇ�A�⍇����t���[���͑}�����Ȃ��B" +
				"�u�،���ЃR�[�h�v = " + l_faq.getInstitutionCode() +
				"�u���M���[���敪�v = " + WEB3SendmailDivDef.FAQ_COMPLETE +
				"�u���ʂh�c�v = " +  l_faq.getTransactionId());
		}
		catch (DataQueryException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
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
				this.getClass().getName() + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
        

        //���X�|���X�f�[�^�𐶐�����B 
        WEB3FaqCompleteResponse l_response = (WEB3FaqCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;   
    }
}
@
