head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�XImpl(WEB3AccInfoEquityCommissionCourseRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �d�� (���u) �V�K�쐬
Revesion History : 2006/06/30 �����i���u�j�@@�d�l�ύX�E���f��113
Revesion History : 2006/07/29 �R�c(SCS) �d�l�ύX�E���f��120
Revesion History : 2007/02/13 �Ӑ��i���u�j�@@�d�l�ύX�E���f��193
Revesion History : 2007/02/22 ���(SCS) �d�l�ύX�E���f��199
Revesion History : 2007/03/02 �g��i(���u) FTBug��Ή�
Revesion History : 2008/08/18 �k�v�u(���u) �d�l�ύX�E���f��240,244
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseMaster;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.data.CommAccountSendDao;
import webbroker3.accountinfo.data.CommAccountSendRow;
import webbroker3.accountinfo.data.CommissionCourseMasterRow;
import webbroker3.accountinfo.data.CommissionCourseRegistParams;
import webbroker3.accountinfo.data.CommissionCourseRegistRow;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommissionCourseRegistInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoEquityCommissionCourseRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccInfoCommissionDivDef;
import webbroker3.common.define.WEB3AccountinfoCommisionDivCheckDef;
import webbroker3.common.define.WEB3AppliStartDateDivDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CommCodeChgMstRow;
import webbroker3.gentrade.data.EquityCommAccountCondMstParams;
import webbroker3.gentrade.data.EquityCommAccountCondMstRow;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�XImpl)<BR>
 * ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X�����N���X<BR>
 * @@author�@@�d��
 * @@version 1.0
 */
public class WEB3AccInfoEquityCommissionCourseRegistServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AccInfoEquityCommissionCourseRegistService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoEquityCommissionCourseRegistServiceImpl.class);
    
    /**
     * @@roseuid 418F39FF032C
     */
    public WEB3AccInfoEquityCommissionCourseRegistServiceImpl() 
    {
     
    }
    
    /**
     * �����ϑ��萔���R�[�X�ύX�\�����������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���q�l��񊔎��ϑ��萔���R�[�X<BR>
     * �ύX�\�����̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���q�l��񊔎��ϑ��萔���R�[�X<BR>
     * �ύX�\���m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�ύX�\��()���R�[������B<BR> 
     * �� �����̃��N�G�X�g�f�[�^���A���q�l��񊔎��ϑ��萔���R�[�X<BR>
     * �ύX�\���������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�ύX�\��()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���q�l��񊔎��ϑ��萔���R�[�X<BR>
     * �ύX�\������m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�\�����()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���q�l��񊔎��ϑ��萔���R�[�X<BR>
     * �ύX�\������������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�\�����()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413D5A83020D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //�����̃��N�G�X�g�f�[�^���A���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�����̓��N�G�X�g�f�[�^�̏ꍇ
        if(l_request instanceof WEB3AccInfoEquityCommissionCourseChangeInputRequest)
        {  
            l_response = this.getInputScreen((WEB3AccInfoEquityCommissionCourseChangeInputRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���m�F���N�G�X�g�̏ꍇ
        else if(l_request instanceof WEB3AccInfoEquityCommissionCourseChangeConfirmRequest)
        {
            l_response = this.validateRegist((WEB3AccInfoEquityCommissionCourseChangeConfirmRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���������N�G�X�g�f�[�^�̏ꍇ
        else if(l_request instanceof WEB3AccInfoEquityCommissionCourseChangeCompleteRequest)
        {
            l_response = this.submitRegist((WEB3AccInfoEquityCommissionCourseChangeCompleteRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������m�F���N�G�X�g�f�[�^�̏ꍇ
        else if(l_request instanceof WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest)
        {
            l_response = this.validateRegistCancel((WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest)l_request);
        }
        
        //�����̃��N�G�X�g�f�[�^���A���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������������N�G�X�g�f�[�^�̏ꍇ
        else if(l_request instanceof WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest)
        {
            l_response = this.submitRegistCancel((WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest)l_request);
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
     * (get���͉��)<BR>
     * �����ϑ��萔���R�[�X�ύX�\�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�����ϑ��萔������ύX�\���jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�����̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputResponse
     * @@roseuid 413D5B3D00F3
     */
    protected WEB3AccInfoEquityCommissionCourseChangeInputResponse getInputScreen(WEB3AccInfoEquityCommissionCourseChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AccInfoEquityCommissionCourseChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //get�ڋq( )     
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) this.getMainAccount();
        
        //get�ϑ��萔���R�[�X�ύX�\��(�ڋq, String)
        WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegist =
            WEB3AccInfoCommissionCourseRegist.getCommissionCourseRegist(
                l_mainAccount, 
                WEB3CommisionProductCodeDef.LISTING_STOCK);
                                
        //(*1) �ύX�\��������ꍇ�iget�ϑ��萔���R�[�X() != null�j
        WEB3AccInfoCommissionCourseChangeInfo[] l_commissionCourseRegistInfo = null;
        if (l_commissionCourseRegist != null)
        {
            //create�萔���R�[�X�ύX�\�����(�ϑ��萔���R�[�X�ύX�\��[])

            WEB3AccInfoCommissionCourseRegistInfoCreatedService 
                l_accInfoCommissionCourseRegistInfoCreatedService = 
                    (WEB3AccInfoCommissionCourseRegistInfoCreatedService) Services.getService(
                    WEB3AccInfoCommissionCourseRegistInfoCreatedService.class);
                    
            l_commissionCourseRegistInfo = 
                l_accInfoCommissionCourseRegistInfoCreatedService.createCommissionCourseRegistInfo(
                    l_commissionCourseRegist);
        }

        //�i*2�j�ȉ��̏����ŏ،��v���t�@@�����X�e�[�u������������B
        //[����]
        //�،����ID�Fget�ڋq.getInstitution().getInstitutionId()
        //�v���t�@@�����X���Faccountinfo.commision.div.check
        //�v���t�@@�����X�l�F1
        List l_lisRecords = new ArrayList();
        try
        {
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" institution_id = ? ");
            l_strWhere.append(" and name = ? ");
            l_strWhere.append(" and value = ? ");

            Object[] l_queryDataContainer = new Object[] {
                l_mainAccount.getInstitution().getInstitutionId() + "",
                WEB3InstitutionPreferencesNameDef.ACCOUNTINFO_COMMISION_DIV_CHECK,
                WEB3AccountinfoCommisionDivCheckDef.CHECK};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                InstitutionPreferencesRow.TYPE,
                l_strWhere.toString(),
                l_queryDataContainer
                );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoEquityCommissionCourseRegistServiceImpl.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoEquityCommissionCourseRegistServiceImpl.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intRecordsCnt = l_lisRecords.size();

        //�i*2�j�Ń��R�[�h���擾�ł����ꍇ
        WEB3AccInfoCommissionCourseMaster[] l_accInfoCommissionCourseMaster = null;
        if (l_intRecordsCnt != 0)
        {
            //get�戵�\�ϑ��萔���R�[�X(String, String, boolean)
            //�،���Ђ���舵���Ă���萔���R�[�X�}�X�^�I�u�W�F�N�g���擾����B
            //[����]
            //�،���ЃR�[�h = get�ڋq.getInstitution().getInstitutionCode()
            //�萔�����i�R�[�h = "��ꊔ��"
            //�M�p�����J�݃t���O = get�ڋq.is�M�p�����J��()
            //��is�M�p�����J��()�̈���
            //�ٍϋ敪 = "�w��Ȃ�"
            l_accInfoCommissionCourseMaster = 
                WEB3AccInfoCommissionCourseMaster.getHandlingPossibleCommissionCourse(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    WEB3CommisionProductCodeDef.LISTING_STOCK,
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT));
        }
        //�i*2�j�Ń��R�[�h���擾�ł��Ȃ������ꍇ
        else
        {
            //get�戵�\�ϑ��萔���R�[�X(�،���ЃR�[�h : String, �萔�����i�R�[�h : String)
            l_accInfoCommissionCourseMaster =
                WEB3AccInfoCommissionCourseMaster.getHandlingPossibleCommissionCourse(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    WEB3CommisionProductCodeDef.LISTING_STOCK);
        }

        int l_intSize = 0;
        if (l_accInfoCommissionCourseMaster != null)  
        {
            l_intSize = l_accInfoCommissionCourseMaster.length;
        }
               
        WEB3AccInfoEquityCommissionCourseChangeInputResponse l_response = 
            (WEB3AccInfoEquityCommissionCourseChangeInputResponse) l_request.createResponse();
                    
        String l_beforeCommCourse = "";
        if (l_commissionCourseRegistInfo != null)
        {
            l_beforeCommCourse =
            l_commissionCourseRegistInfo[l_commissionCourseRegistInfo.length - 1].commissionCourse;
        } else
        {
            l_beforeCommCourse = l_request.beforCommissionCourse;
        }

        List l_lisCommissionCourseMaster = new Vector();

        for (int i = 0; i < l_intSize; i++)
        {
            CommissionCourseMasterRow l_commissionCourseMasterRow = 
                (CommissionCourseMasterRow)l_accInfoCommissionCourseMaster[i].getDataSourceObject(); 
            if (!l_beforeCommCourse.equals(l_commissionCourseMasterRow.getCommissionCourseDiv()))
            {
                l_lisCommissionCourseMaster.add(l_accInfoCommissionCourseMaster[i]);
            }
        }        
        int l_intMasterRowsSize = l_lisCommissionCourseMaster.size();
        
        l_response.changeAbleCommissionCourseList = new String[l_intMasterRowsSize]; 
        l_response.applyStartDateList = new Date[l_intMasterRowsSize]; 
        l_response.changeOfferDeadlineTimeList = new String[l_intMasterRowsSize]; 
        l_response.changeOfferDeadlineDateList = new String[l_intMasterRowsSize];
        l_response.changeApplyStartDateDivList = new String[l_intMasterRowsSize];    
        for (int i = 0; i < l_intMasterRowsSize; i++)
        {   
            CommissionCourseMasterRow l_commissionCourseMasterRow = 
                (CommissionCourseMasterRow)((WEB3AccInfoCommissionCourseMaster)l_lisCommissionCourseMaster.get(i)).getDataSourceObject();      
            l_response.changeAbleCommissionCourseList[i] = l_commissionCourseMasterRow.getCommissionCourseDiv();
            l_response.applyStartDateList[i] = ((WEB3AccInfoCommissionCourseMaster)l_lisCommissionCourseMaster.get(i)).getAppliStartTimestamp();
            l_response.changeOfferDeadlineDateList[i] = l_commissionCourseMasterRow.getRegistEndDaySpec();
            l_response.changeOfferDeadlineTimeList[i] = l_commissionCourseMasterRow.getRegistEndTime();
            // ����2�j�@@�戵�\�ϑ��萔���R�[�X���ҏW����ꗗ�f�[�^�ɂ���
            // �f�[�^�l���h9�F�����w��h�̏ꍇ�A�h9�F1�h�̂悤�ɁA�f�[�^�l�ɑ����āh�F�h�{������ǉ��ŕҏW����B
            // �����́Aget�戵�\�ϑ��萔���R�[�X()�߂�l�̑Ώۗv�f.�ϑ��萔���R�[�X�}�X�^�s.�ύX�K�p�J�n����
            if (WEB3AppliStartDateDivDef.DAYS_DESIGNATED.equals(l_commissionCourseMasterRow.getAppliStartDateDiv()))
            {
                l_response.changeApplyStartDateDivList[i] = WEB3AppliStartDateDivDef.DAYS_DESIGNATED + ":" + l_commissionCourseMasterRow.getAppliStartDayCount();
            }
            else
            {
                l_response.changeApplyStartDateDivList[i] = l_commissionCourseMasterRow.getAppliStartDateDiv(); 
            }
           
        }      
        l_response.commissionCourceChangeList = l_commissionCourseRegistInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�ύX�\��)<BR>
     * �����ϑ��萔���R�[�X�ύX�\���m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�����ϑ��萔������ύX�\���jvalidate�ύX�\���v�Q�ƁB <BR>
     * @@param l_request - ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmResponse
     * @@roseuid 413D5BA60132
     */
    protected WEB3AccInfoEquityCommissionCourseChangeConfirmResponse validateRegist(WEB3AccInfoEquityCommissionCourseChangeConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateRegist(WEB3AccInfoEquityCommissionCourseChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate( )
        l_request.validate();
        
        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //get�ڋq( )     
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) this.getMainAccount();
         
        WEB3AccInfoCommissionCourseMaster l_accInfoCommissionCourseMasternew =
            new WEB3AccInfoCommissionCourseMaster(
                l_mainAccount.getInstitution().getInstitutionCode(),
                WEB3CommisionProductCodeDef.LISTING_STOCK,
                l_request.commissionCourse);

        //�i*1�j�ȉ��̏����ŏ،��v���t�@@�����X�e�[�u������������B
        //[����]
        //�،����ID�Fget�ڋq.getInstitution().getInstitutionId()
        //�v���t�@@�����X���Faccountinfo.commision.div.check
        //�v���t�@@�����X�l�F1
        List l_lisRecords = new ArrayList();
        try
        {
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" institution_id = ? ");
            l_strWhere.append(" and name = ? ");
            l_strWhere.append(" and value = ? ");

            Object[] l_queryDataContainer = new Object[] {
                l_mainAccount.getInstitution().getInstitutionId() + "",
                WEB3InstitutionPreferencesNameDef.ACCOUNTINFO_COMMISION_DIV_CHECK,
                WEB3AccountinfoCommisionDivCheckDef.CHECK};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                InstitutionPreferencesRow.TYPE,
                l_strWhere.toString(),
                l_queryDataContainer
                );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoEquityCommissionCourseRegistServiceImpl.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoEquityCommissionCourseRegistServiceImpl.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intRecordsCnt = l_lisRecords.size();
        //�i*2�j�Ń��R�[�h���擾�ł����ꍇ
        if (l_intRecordsCnt != 0)
        {
            //validate�戵�\�ϑ��萔���R�[�X(boolean, String)
            //���Y�ڋq�̌����J�ݏ󋵂ɂ��A�戵�\�Ȉϑ��萔���R�[�X�ł��邩���`�F�b�N����B
            //[����]
            //�萔���敪 = �ϑ��萔���R�[�X�}�X�^�s.get�萔���敪()
            //�M�p�����J�݃t���O = get�ڋq.is�M�p�����J��()
            //��is�M�p�����J��()�̈���
            //�ٍϋ敪 = "�w��Ȃ�"
            this.validateHandlingPossibleCommCourse(
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT),
                l_accInfoCommissionCourseMasternew.getCommissionDiv());
        }

        WEB3AccInfoEquityCommissionCourseChangeConfirmResponse l_response = 
            (WEB3AccInfoEquityCommissionCourseChangeConfirmResponse) l_request.createResponse();
            
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�ύX�\��)<BR>
     * �����ϑ��萔���R�[�X�ύX�\�������������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�����ϑ��萔������ύX�\���jsubmit�ύX�\���v�Q�ƁB <BR>
     * @@param l_request - ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCompleteResponse
     * @@roseuid 413D5BD80299
     */
    protected WEB3AccInfoEquityCommissionCourseChangeCompleteResponse submitRegist(WEB3AccInfoEquityCommissionCourseChangeCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitRegist(WEB3AccInfoEquityCommissionCourseChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();
        
        //1.2 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //get�ڋq( )     
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) this.getMainAccount();
        
        //getCommonOrderValidator( )
        //1.4getCommonOrderValidator( )
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //1.5validate����p�X���[�h()
        OrderValidationResult l_validationResult = l_gentradeOrderValidator.validateTradingPassword(
            getTrader(),
            getSubAccount(), 
            l_request.password
            );
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�`�F�b�N�G���[�̏ꍇ�͂��O���X���[����");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AccInfoCommissionCourseMaster l_accInfoCommissionCourseMaster = 
            new WEB3AccInfoCommissionCourseMaster(
                l_mainAccount.getInstitution().getInstitutionCode(),
                WEB3CommisionProductCodeDef.LISTING_STOCK,
                l_request.commissionCourse);

        //�i*1�j�ȉ��̏����ŏ،��v���t�@@�����X�e�[�u������������B
        //[����]
        //�،����ID�Fget�ڋq.getInstitution().getInstitutionId()
        //�v���t�@@�����X���Faccountinfo.commision.div.check
        //�v���t�@@�����X�l�F1
        List l_lisRecords = new ArrayList();
        try
        {
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" institution_id = ? ");
            l_strWhere.append(" and name = ? ");
            l_strWhere.append(" and value = ? ");

            Object[] l_queryDataContainer = new Object[] {
                l_mainAccount.getInstitution().getInstitutionId() + "",
                WEB3InstitutionPreferencesNameDef.ACCOUNTINFO_COMMISION_DIV_CHECK,
                WEB3AccountinfoCommisionDivCheckDef.CHECK};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                InstitutionPreferencesRow.TYPE,
                l_strWhere.toString(),
                l_queryDataContainer
                );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoEquityCommissionCourseRegistServiceImpl.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoEquityCommissionCourseRegistServiceImpl.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intRecordsCnt = l_lisRecords.size();
        //�i*2�j�Ń��R�[�h���擾�ł����ꍇ
        if (l_intRecordsCnt != 0)
        {
            //validate�戵�\�ϑ��萔���R�[�X(boolean, String)
            //���Y�ڋq�̌����J�ݏ󋵂ɂ��A�戵�\�Ȉϑ��萔���R�[�X�ł��邩���`�F�b�N����B
            //[����]
            //�萔���敪 = �ϑ��萔���R�[�X�}�X�^�s.get�萔���敪()
            //�M�p�����J�݃t���O = get�ڋq.is�M�p�����J��()
            //��is�M�p�����J��()�̈���
            //�ٍϋ敪 = "�w��Ȃ�"
            this.validateHandlingPossibleCommCourse(
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT),
                l_accInfoCommissionCourseMaster.getCommissionDiv());
        }

        //1.8 createNew�ϑ��萔���R�[�X�ύX�\��
        WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist =
            WEB3AccInfoCommissionCourseRegist.createNewCommissionCourseRegist(
                l_mainAccount,
                l_accInfoCommissionCourseMaster);

        CommissionCourseRegistRow l_commissionCourseRegistRow = 
            (CommissionCourseRegistRow)l_commissionCourseRegist.getDataSourceObject();
     
       //1.10 doInsertQuery(Row)
        QueryProcessor l_processor;
        try
        {
            l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_commissionCourseRegistRow);
        } 

        catch (DataFindException l_ex) 
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
        catch (DataQueryException l_ex) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataNetworkException l_ex) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }     
            
    //1.11 is�萔��No( )
    boolean l_blnIsCommissionNo = 
      this.isCommissionNo(l_commissionCourseRegist, l_mainAccount);

    //1.12 (*1)is�萔��No()�̖߂�l == true�̏ꍇ
    if (l_blnIsCommissionNo)
    {
          //1.13 get�萔��No(�ϑ��萔���R�[�X�ύX�\��)
          //[get�萔��No()�Ɏw�肷�����] 
          // �ϑ��萔���R�[�X�ύX�\���F�@@���������ϑ��萔���R�[�X�ύX�\��
          String l_strCommissionNo = 
              this.getCommissionNo(l_commissionCourseRegist);
          
          //1.14 save�萔��No(String, �ϑ��萔���R�[�X�ύX�\��, �ڋq)
          //[save�萔��No()�Ɏw�肷�����] 
          // �萔��No�F�@@get�o�^No()�̖߂�l 
          // �ϑ��萔���R�[�X�ύX�\���F�@@���������ϑ��萔���R�[�X�ύX�\�� 
          // �ڋq�F�@@get�ڋq()�̖߂�l
          this.saveCommissionNo(
              l_strCommissionNo, 
              l_commissionCourseRegist, 
              l_mainAccount);
    }
        
        //1.15 createResponse( )
        WEB3AccInfoEquityCommissionCourseChangeCompleteResponse l_response = 
            (WEB3AccInfoEquityCommissionCourseChangeCompleteResponse) l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (validate�\�����)<BR>
     * �����ϑ��萔���R�[�X�ύX�\������m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�����ϑ��萔������ύX�\���jvalidate�\������v�Q�ƁB <BR>
     * <BR>
     *  ==========================================================    <BR>
     *          �V�[�P���X�} :  ���q�l���i�����ϑ��萔������ύX�\���jvalidate�\�����                  <BR>
     *          ��̈ʒu     :  1.5  ����\�łȂ��ꍇ�iis����\() == false�j�A   <BR>
     *                             ��O���X���[����B                                            <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_01010                    <BR> 
     *  ==========================================================    <BR>    <BR>
     * @@param l_request - ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413D5C0203B2
     */
    protected WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse validateRegistCancel(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateRegistCancel(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();
        
        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //�ϑ��萔���R�[�X�ύX�\��(long)
        long l_lngRegistId = Long.parseLong(l_request.id);
        WEB3AccInfoCommissionCourseRegist l_accInfoCommissionCourseRegist =
            new WEB3AccInfoCommissionCourseRegist(l_lngRegistId);
        boolean l_blnCancelPossible =l_accInfoCommissionCourseRegist.isCancelPossible();
        if (!l_blnCancelPossible)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01010,
                this.getClass().getName() + "." + STR_METHOD_NAME);           
        }
        
        //createResponse( )
        WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse l_response = 
            (WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse) l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (submit�\�����)<BR>
     * �����ϑ��萔���R�[�X�ύX�\����������������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�����ϑ��萔������ύX�\���jsubmit�\������v�Q�ƁB <BR>
     * <BR>
     *  ==========================================================    <BR>
     *          �V�[�P���X�} :  ���q�l���i�����ϑ��萔������ύX�\���jsubmit�\�����                  <BR>
     *          ��̈ʒu     :  1.8  ����\�łȂ��ꍇ�iis����\() == false�j�A   <BR>
     *                             ��O���X���[����B                                            <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_01010                    <BR> 
     *  ==========================================================    <BR>  
     * <BR>
     * @@param l_request - ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413D5C0203D2
     */
    protected WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse 
        submitRegistCancel(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitRegistCancel(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();
        
        //1.2 validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3 get�⏕����( )     
        WEB3GentradeMainAccount l_mainAccout = (WEB3GentradeMainAccount) this.getMainAccount();
        
        //1.5 getCommonOrderValidator( )
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
        
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
            
        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateTradingPassword(
            getTrader(),
            getSubAccount(), 
            l_request.password
            );
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�`�F�b�N�G���[�̏ꍇ�͂��O���X���[����");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        
        //1.7 �ϑ��萔���R�[�X�ύX�\��(long)
        long l_lngRegistId = Long.parseLong(l_request.id);
        WEB3AccInfoCommissionCourseRegist l_accInfoCommissionCourseRegist =
            new WEB3AccInfoCommissionCourseRegist(l_lngRegistId);
        
        //1.9 (*1) ����\�łȂ��ꍇ�iis����\() == false�j�A��O���X���[����B
        boolean l_blnCancelPossible =l_accInfoCommissionCourseRegist.isCancelPossible();
        if (!l_blnCancelPossible)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01010,
                this.getClass().getName() + "." + STR_METHOD_NAME);           
        }
        
        //1.10 getDataSourceObject( )     
        CommissionCourseRegistParams l_commissionCourseRegistParams = 
            new CommissionCourseRegistParams((CommissionCourseRegistRow)
                l_accInfoCommissionCourseRegist.getDataSourceObject()); 
                              
        l_commissionCourseRegistParams.setDeleteFlag(BooleanEnum.TRUE);
        l_commissionCourseRegistParams.setLastUpdater(l_mainAccout.getAccountCode());
        l_commissionCourseRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        try
        {
            //1.11 doUpdateQuery(arg0 : PrimaryKey, arg1(*2) : Map)
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doUpdateQuery(l_commissionCourseRegistParams);
        }
        catch (DataFindException l_ex) 
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
        catch (DataQueryException l_ex) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataNetworkException l_ex) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
    //1.12 is�萔��No( )
    boolean l_blnIsCommissionNo = 
      this.isCommissionNo(l_accInfoCommissionCourseRegist, l_mainAccout);

    //1.13 (*1)is�萔��No()�̖߂�l == true�̏ꍇ
    if (l_blnIsCommissionNo)
    {
          //1.14 get�萔��No(�ڋq, �ϑ��萔���R�[�X�ύX�\��)
          //[get�萔��No()�Ɏw�肷�����] 
          //�ڋq�F�@@get�ڋq()�̖߂�l  
          //�ϑ��萔���R�[�X�ύX�\���F�@@���������ϑ��萔���R�[�X�ύX�\�� 
          String l_strCommissionNo = 
              this.getCommissionNo(
                  l_mainAccout,
                  l_accInfoCommissionCourseRegist);
          
          //1.15 save�萔��No(String, �ϑ��萔���R�[�X�ύX�\��, �ڋq)
          //[save�萔��No()�Ɏw�肷�����] 
          // �萔��No�F�@@get�萔��No()�̖߂�l 
          // �ϑ��萔���R�[�X�ύX�\���F�@@���������ϑ��萔���R�[�X�ύX�\�� 
          // �ڋq�F�@@get�ڋq()�̖߂�l
          this.saveCommissionNo(
              l_strCommissionNo, 
              l_accInfoCommissionCourseRegist, 
              l_mainAccout);
    }
    
        //createResponse( )
        WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse l_response = 
            (WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse) l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }

     /**
     *�iis�萔��No�j<BR>
     * �X�V�Ώۂ̃��R�[�h�����݂��邩���肷��B<BR>
     * <BR>
     * [�߂�l] <BR>
     * true�F�@@�X�V�Ώۂ̃��R�[�h�����݂���<BR>
     * false�F�@@�X�V�Ώۂ̃��R�[�h�����݂��Ȃ�<BR>
     * <BR>
     * �P�j �w�ϑ��萔���ڋq�����o�^�}�X�^�[�x�����L�����Ō�������B<BR>
     * <BR>
     * �@@�@@[����] <BR>
     *�@@�@@�@@�E�،���ЃR�[�h = ����.�ϑ��萔���R�[�X�ύX�\��.get�،���ЃR�[�h() <BR>
     *�@@�@@�@@�E���XID = ����.�ڋq.getBranch().getBranchId()  <BR>
     *�@@�@@�@@�E����ID = ����.�ڋq.getAccountId()   <BR>
     *�@@�@@�@@�E�萔�����i�R�[�h in �i10�F��ꊔ���A11�FJASDAQ�A30�F���A31�F���X���j<BR>
     *�@@�@@�@@�E�L���� = ����.�ϑ��萔���R�[�X�ύX�\��.get�K�p�J�n��() <BR>
     * <BR>
     * �Q�j �������ʂɂ��߂�l��ݒ肷��B<BR>
     * <BR> �@@
     * �@@�E�擾���R�[�h��0���̏ꍇ�Afalse��ԋp����B <BR>
     * �@@�E�擾���R�[�h��0���ȊO�̏ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * @@param l_commissionCourseRegist - (�ϑ��萔���R�[�X�ύX�\��)<BR>
     * �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g<BR>
     * @@param l_request - (�ڋq)<BR>
     * �ڋq
     * @@return boolean
     * @@throws WEB3BaseException 
     * @@roseuid 
     */
  public boolean isCommissionNo(
    WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist,
    MainAccount l_mainAccount) throws WEB3BaseException
  {
        final String STR_METHOD_NAME = " isCommissionNo()";
        log.entering(STR_METHOD_NAME);

        if (l_commissionCourseRegist == null || l_mainAccount == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //[����] 
        StringBuffer l_strWhere = new StringBuffer();
             
        //�،���ЃR�[�h = ����.�ϑ��萔���R�[�X�ύX�\��.get�،���ЃR�[�h() 
        l_strWhere.append(" institution_code = ? ");
        String l_strInstitutionCode = 
            l_commissionCourseRegist.getInstitutionCode();
        
        //���XID = ����.�ڋq.getBranch().getBranchId() 
        l_strWhere.append(" and branch_id = ? ");
        String l_strBranchId = 
            l_mainAccount.getBranch().getBranchId() + "";
             
        //��ID = ����.�ڋq.getAccountId()  
        l_strWhere.append(" and account_id = ? ");
        String l_strAccountId = l_mainAccount.getAccountId() + "";
        
        //�萔�����i�R�[�h in �i10�F��ꊔ���A11�FJASDAQ�A30�F���A31�F���X���j 
        l_strWhere.append(" and comm_product_code in(?, ?, ?, ?) ");

        //�L���� = ����.�ϑ��萔���R�[�X�ύX�\��.get�K�p�J�n��() 
        l_strWhere.append(" and valid_until_biz_date = ? ");
        Date l_datAppliStartDate = 
            l_commissionCourseRegist.getAppliStartDate();
        String l_strAppliStartDate = 
            WEB3DateUtility.formatDate(l_datAppliStartDate, "yyyyMMdd");
        
        Object[] l_objEquityCommAccountCondMstbindVars = {
            l_strInstitutionCode,
            l_strBranchId,
            l_strAccountId,
            WEB3CommisionProductCodeDef.LISTING_STOCK,
            WEB3CommisionProductCodeDef.JASDAQ,
            WEB3CommisionProductCodeDef.CREDIT,
            WEB3CommisionProductCodeDef.CREDIT_STORE,
            l_strAppliStartDate};
             
        List l_lisEquityAccountCommCondMstRows = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisEquityAccountCommCondMstRows = l_queryProcessor.doFindAllQuery(
                EquityCommAccountCondMstRow.TYPE,
                l_strWhere.toString(),
                null,
                l_objEquityCommAccountCondMstbindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j �������ʂɂ��߂�l��ݒ肷��B
        if (l_lisEquityAccountCommCondMstRows == null || 
            l_lisEquityAccountCommCondMstRows.isEmpty())
        {
            return false;
        }
     
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (get�萔��No)<BR>
     * �u�萔��No�v���擾����B<BR>
     * <BR>
     * �P�j���R�[�h���擾 <BR>
     * �@@�E �w�萔���ڋq�������M�p�x����ȉ��̏����̃��R�[�h���擾����B <BR>
     * �@@���Y���s���Ȃ��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00398<BR>
     * <BR>
     * �@@�@@[����] <BR>
     *�@@�@@�@@�E�،���ЃR�[�h = ����.�ϑ��萔���R�[�X�ύX�\��.get�،���ЃR�[�h() <BR>
     *�@@�@@�@@�E���X�R�[�h = ����.�ڋq.getBranch().getBranchCode() <BR>
     *�@@�@@�@@�E�����R�[�h = ����.�ڋq.getAccountCode() <BR>
     *�@@�@@�@@�E�萔�����i�R�[�h = ����.�ϑ��萔���R�[�X�ύX�\��.get�萔�����i�R�[�h() <BR>
     *�@@�@@�@@�E�K�p�J�n�� = ����.�ϑ��萔���R�[�X�ύX�\��.get�K�p�J�n��()  <BR>
     * <BR>
     * �Q�j �u�萔��No�v��ԋp  <BR>
     * �@@�E�P�j�Ŏ擾�����u�萔��No�v��ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_commissionCourseRegist - (�ϑ��萔���R�[�X�ύX�\��)<BR>
     * �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 413D5C0203D2
     */
    public String getCommissionNo(
        MainAccount l_mainAccount, 
        WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getCommissionNo(MainAccount, " +
            "WEB3AccInfoCommissionCourseRegist, Date)";
        log.entering(STR_METHOD_NAME);
         
        if (l_commissionCourseRegist == null || l_mainAccount == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //�P�j���R�[�h���擾 
            //�w�萔���ڋq�������M�p�x����ȉ��̏����̃��R�[�h���擾����B 
            //[����] 
            //�،���ЃR�[�h = ����.�ϑ��萔���R�[�X�ύX�\��.get�،���ЃR�[�h() 
            String l_strInstitutionCode = 
                l_commissionCourseRegist.getInstitutionCode();
            //���X�R�[�h = ����.�ڋq.getBranch().getBranchCode() 
            String l_strBranchCode = 
                l_mainAccount.getBranch().getBranchCode();
            //�����R�[�h = ����.�ڋq.getAccountCode() 
            String l_strAccountCode = l_mainAccount.getAccountCode(); 
            //�萔�����i�R�[�h = ����.�ϑ��萔���R�[�X�ύX�\��.get�萔�����i�R�[�h()
            String l_strCommissionProductCode = 
                l_commissionCourseRegist.getCommissionProductCode();
            //�K�p�J�n�� = ����.�ϑ��萔���R�[�X�ύX�\��.get�K�p�J�n��()
            Date l_datAppliStartDate = 
                l_commissionCourseRegist.getAppliStartDate();
            String l_strAppliStartDate = 
                WEB3DateUtility.formatDate(l_datAppliStartDate, "yyyyMMdd");
            
            CommAccountSendRow l_commAccountSendRow = 
                CommAccountSendDao.findRowByInstitutionCodeBranchCodeAccountCodeCommProductCodeAppliStartDate(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strCommissionProductCode,
                    l_strAppliStartDate);
            
            if (l_commAccountSendRow == null)
            {
                log.debug(" �Y������f�[�^�����݂��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Y������f�[�^�����݂��܂���B");
            }
            
            //�Q�j �u�萔��No�v��ԋp 
            //�P�j�Ŏ擾�����u�萔��No�v��ԋp����B  
            String l_strCommissionNo = l_commAccountSendRow.getCommissionNo();
            log.debug("�萔��No = " + l_strCommissionNo);
             
            log.exiting(STR_METHOD_NAME);
            return l_strCommissionNo;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
    }

    /**
     * (get�萔��No)<BR>
     * �u�萔��No�v���擾����B<BR>
     * <BR>
     * �P�j ���R�[�h���擾<BR>
     * �@@�E�w�萔���R�[�X�R�[�h�ϊ��}�X�^�x����ȉ��̏����̃��R�[�h���擾����B<BR>
     * �@@���Y���s���Ȃ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00398<BR>
     * <BR>
     *�@@�@@[����]<BR>
     *�@@�@@�@@�E���XID�@@=�@@����.�ϑ��萔���R�[�X�ύX�\��.get���X<BR>
     *�@@�@@�@@�E�萔�����i�R�[�h�@@=�@@����.�ϑ��萔���R�[�X�ύX�\��.get�萔�����i�R�[�h<BR>
     *�@@�@@�@@�E�萔���R�[�X�R�[�h�@@=�@@����.�ϑ��萔���R�[�X�ύX�\��.get�萔���R�[�X�R�[�h<BR>
     *�@@�@@�@@�E�K�p�J�n���@@<=�@@����.�ϑ��萔���R�[�X�ύX�\��.get�K�p�J�n��<BR>
     * <BR>
     *�@@�@@[�擾��]<BR>
     *�@@�@@�@@�E�K�p�J�n�� �~���iDESC�j<BR>
     * <BR>
     * �Q�j �u�萔��No�v���擾<BR>
     *�@@�E�P�j�ɂĕ������R�[�h�擾�����̂ŁA<BR>
     *�@@�@@�@@�P�ԍŏ��̃��R�[�h����u�萔��No�v���擾����B<BR>
     * <BR>
     * �R�j �u�萔��No�v��ԋp<BR>
     *�@@�E�Q�j�Ŏ擾�����u�萔��No�v��ԋp����B<BR>
     * <BR>
     * @@param l_commissionCourseRegist - (�ϑ��萔���R�[�X�ύX�\��)<BR>
     * �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 413D5C0203D2
     */
    public String getCommissionNo(
        WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getCommissionNo(WEB3AccInfoCommissionCourseRegist)";
        log.entering(STR_METHOD_NAME);
         
        if (l_commissionCourseRegist == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
         
        //�P�j ���R�[�h���擾
        //�@@�w�萔���R�[�X�R�[�h�ϊ��}�X�^�x����ȉ��̏����̃��R�[�h���擾����B 
        //[����] 
        StringBuffer l_strWhere = new StringBuffer();
         
        //���XID = ����.����.�ϑ��萔���R�[�X�ύX�\��.get���X
        l_strWhere.append(" branch_id = ? ");
        long l_lngBranchId = 
            l_commissionCourseRegist.getBranch().getBranchId();
         
        //�萔�����i�R�[�h = ����.�ϑ��萔���R�[�X�ύX�\��.get�萔�����i�R�[�h() 
        l_strWhere.append(" and comm_product_code = ? ");
        String l_strCommissionProductCode =
            l_commissionCourseRegist.getCommissionProductCode();
         
        //�萔���R�[�X�R�[�h = ����.�ϑ��萔���R�[�X�ύX�\��.get�萔���R�[�X�R�[�h()
        l_strWhere.append(" and commission_course_div = ? ");
        String l_strCommissionCourseCode = 
            l_commissionCourseRegist.getCommissionCourseCode();
         
        //�K�p�J�n���@@<=�@@����.�ϑ��萔���R�[�X�ύX�\��.get�K�p�J�n��
        l_strWhere.append(" and appli_start_date <= ? ");
        Date l_datAppliStartDate = 
            l_commissionCourseRegist.getAppliStartDate();
        String l_strFomateDate = WEB3DateUtility.formatDate(l_datAppliStartDate, "yyyyMMdd");
         
        //[�擾��] 
        //�K�p�J�n�� �~���iDESC�j 
        String l_strSortKey = "appli_start_date DESC";
        
        Object[] l_objCommCodeChgMstbindVars = {
            new Long(l_lngBranchId),
            l_strCommissionProductCode,
            l_strCommissionCourseCode,
            l_strFomateDate};
        
        List l_lisCommCodeChgMstRows = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisCommCodeChgMstRows = l_queryProcessor.doFindAllQuery(
                CommCodeChgMstRow.TYPE,
                l_strWhere.toString(),
                l_strSortKey,
                null,
                l_objCommCodeChgMstbindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
    
         //���Y���s���Ȃ��ꍇ�A��O���X���[����B 
        if (l_lisCommCodeChgMstRows == null || 
            l_lisCommCodeChgMstRows.isEmpty())
        {
            log.debug(" �Y������f�[�^�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������f�[�^�����݂��܂���B");
        }
        
        //�Q�j �u�萔��No�v���擾
        //�E�P�j�ɂĕ������R�[�h�擾�����̂ŁA�P�ԍŏ��̃��R�[�h����u�萔��No�v���擾����B
        CommCodeChgMstRow l_commCodeChgMstRow = 
            (CommCodeChgMstRow) l_lisCommCodeChgMstRows.get(0);
        String l_strCommissionNo = l_commCodeChgMstRow.getCommissionNo();
        
        //�S�j�u�萔��No�v��ԋp 
        log.exiting(STR_METHOD_NAME);
        return l_strCommissionNo;
    }
    
    /**
     * (save�萔��No)<BR>
     * �w�ϑ��萔���ڋq�����o�^�}�X�^�[.�萔��No�x��update����B<BR>
     * <BR>
     *�@@[DB�X�V�d�l] <BR>
     *�@@�@@�E�y��Trade�z�⑫����.DB�X�V <BR>
     *�@@�@@�@@�@@20.���q�l���^�Ïؔԍ��Ǘ� <BR>
     *�@@�@@�@@�@@06.�����ϑ��萔���R�[�X�ύX�\�� <BR>
     *�@@�@@�@@�@@�u�ϑ��萔���ڋq�����o�^�}�X�^�[�X�V�d�l.xls�v <BR>
     * <BR>
     * �@@�@@[����] <BR>
     *�@@�@@�@@�E�،���ЃR�[�h = ����.�ϑ��萔���R�[�X�ύX�\��.get�،���ЃR�[�h() <BR>
     *�@@�@@�@@�E���XID = ����.�ڋq.getBranch().getBranchId()  <BR>
     *�@@�@@�@@�E����ID = ����.�ڋq.getAccountId()   <BR>
     *�@@�@@�@@�E�萔�����i�R�[�h in �i10�F��ꊔ���A11�FJASDAQ�A30�F���A31�F���X���j<BR>
     *�@@�@@�@@�E�L���� = ����.�ϑ��萔���R�[�X�ύX�\��.get�K�p�J�n��() <BR>
     * <BR>
     * @@param l_strCommissionNo - (�萔��No)<BR>
     * �萔��No<BR>
     * @@param l_commissionCourseRegist - (�ϑ��萔���R�[�X�ύX�\��)<BR>
     * �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g<BR>
     * @@param l_request - (�ڋq)<BR>
     * �ڋq
     * @@throws WEB3BaseException
     * @@roseuid 413D5C0203D2
     */
    public void saveCommissionNo(
        String l_strCommissionNo, 
        WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist,
        MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " saveCommissionNo(Date, String," +
            " WEB3AccInfoCommissionCourseRegist, MainAccount)";
        log.entering(STR_METHOD_NAME);
         
        if (l_commissionCourseRegist == null || l_mainAccount == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
         
        //[����] 
        StringBuffer l_strWhere = new StringBuffer();
         
        //�،���ЃR�[�h = ����.�ϑ��萔���R�[�X�ύX�\��.get�،���ЃR�[�h() 
        l_strWhere.append(" institution_code = ? ");
        String l_strInstitutionCode = 
            l_commissionCourseRegist.getInstitutionCode();
         
        //���XID = ����.�ڋq.getBranch().getBranchId() 
        l_strWhere.append(" and branch_id = ? ");
        String l_strBranchId = 
            l_mainAccount.getBranch().getBranchId() + "";
         
        //��ID = ����.�ڋq.getAccountId()  
        l_strWhere.append(" and account_id = ? ");
        String l_strAccountId = l_mainAccount.getAccountId() + "";
         
        //�萔�����i�R�[�h in �i10�F��ꊔ���A11�FJASDAQ�A30�F���A31�F���X���j 
        l_strWhere.append(" and comm_product_code in(?, ?, ?, ?) ");

        //�L���� = ����.�ϑ��萔���R�[�X�ύX�\��.get�K�p�J�n��() 
        l_strWhere.append(" and valid_until_biz_date = ? ");
        Date l_datAppliStartDate = 
            l_commissionCourseRegist.getAppliStartDate();
        String l_strAppliStartDate = 
            WEB3DateUtility.formatDate(l_datAppliStartDate, "yyyyMMdd");
        
        Object[] l_objEquityCommAccountCondMstbindVars = {
            l_strInstitutionCode,
            l_strBranchId,
            l_strAccountId,
            WEB3CommisionProductCodeDef.LISTING_STOCK,
            WEB3CommisionProductCodeDef.JASDAQ,
            WEB3CommisionProductCodeDef.CREDIT,
            WEB3CommisionProductCodeDef.CREDIT_STORE,
            l_strAppliStartDate};
         
        List l_lisEquityAccountCommCondMstRows = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisEquityAccountCommCondMstRows = l_queryProcessor.doFindAllQuery(
                EquityCommAccountCondMstRow.TYPE,
                l_strWhere.toString(),
                null,
                l_objEquityCommAccountCondMstbindVars);
        }
        catch (DataNetworkException l_ex)
        {
           log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }

        if (l_lisEquityAccountCommCondMstRows == null || 
            l_lisEquityAccountCommCondMstRows.isEmpty())
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        
        int l_intEquityAccountCommCondMstRows = 
            l_lisEquityAccountCommCondMstRows.size();
        for (int i = 0; i < l_intEquityAccountCommCondMstRows; i++)
        {
            //[DB�X�V�d�l] 
            //�y��Trade�z�⑫����.DB�X�V 
            //20.���q�l���^�Ïؔԍ��Ǘ� 
            //06.�����ϑ��萔���R�[�X�ύX�\�� 
            //�u�ϑ��萔���ڋq�����o�^�}�X�^�[�X�V�d�l.xls�v
            EquityCommAccountCondMstRow l_accountCondMstRow = 
                (EquityCommAccountCondMstRow) l_lisEquityAccountCommCondMstRows.get(i);
            EquityCommAccountCondMstParams l_accountCondMstParams = 
                new EquityCommAccountCondMstParams(l_accountCondMstRow);
            
            l_accountCondMstParams.setCommissionNo(l_strCommissionNo);
            l_accountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_accountCondMstParams);
            }
            catch (DataFindException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);    
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);    
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);  
            }     
            
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�戵�\�ϑ��萔���R�[�X)<BR>
     * ���Y�ڋq�̌����J�ݏ󋵂ɂ��A�戵�\�Ȉϑ��萔���R�[�X�ł��邩���`�F�b�N����B<BR>
     * <BR>
     * ����.�M�p�����J�݃t���O == false ���� ����.�萔���敪 == 1 �i�M�p�ڋq�j�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_03108<BR>
     * @@param l_strMarginOpenFlag - (�M�p�����J�݃t���O)<BR>
     * �M�p�����J�݃t���O<BR>
     * @@param l_commissionDiv - (�萔���敪)<BR>
     * �萔���敪<BR>
     * @@throws WEB3BaseException
     */
    public void validateHandlingPossibleCommCourse(boolean l_blnMarginOpenFlag, String l_strCommissionDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingPossibleCommCourse(boolean, String)";
        log.entering(STR_METHOD_NAME);

        if (!l_blnMarginOpenFlag
            && WEB3AccInfoCommissionDivDef.MARGIN_TRADE_COMMISSION.equals(l_strCommissionDiv))
        {
            log.debug("�M�p�����J�݂Ȃ��A���萔���敪�͐M�p�ڋq�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03108,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�M�p�����J�݂Ȃ��A���萔���敪�͐M�p�ڋq�ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
