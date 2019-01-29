head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�\���o�^�T�[�r�XImpl(WEB3SrvRegiRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 ���o�� �V�K�쐬
Revesion History : 2005/04/06 ���c ��(SRA) �f�o�b�O���O�o�͏C��
Revesion History : 2007/06/06 ����(���u) �d�l�ύX���f��No.247
Revesion History : 2007/06/19 �����Q(���u) �d�l�ύX���f��No.249
Revesion History : 2007/07/16 �Ј���(���u) �d�l�ύX���f��No.284
Revesion History : 2007/07/24 ����(���u) �d�l�ύX���f��No.292
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AppliAttributeDef;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3SrvUsePeriodDivDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.gentrade.data.SrvRegiApplicationRow;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiCancelUpdateInterceptor;
import webbroker3.srvregi.WEB3SrvRegiChangeAppliSpec;
import webbroker3.srvregi.WEB3SrvRegiNewAppliSpec;
import webbroker3.srvregi.WEB3SrvRegiNewOrderUpdateInterceptor;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiHistoryParams;
import webbroker3.srvregi.data.SrvRegiHistoryRow;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiFreeAttributeApplyDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiKeyItemDef;
import webbroker3.srvregi.define.WEB3SrvRegiRigistDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiSpecialDivDef;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

/**
 * (�T�[�r�X���p�\���o�^�T�[�r�XImpl)<BR>
 * �T�[�r�X���p�\���o�^�T�[�r�X�����N���X<BR>
 * @@author ���o��
 * @@version 1.0
 *
 * <BR>
 * �\�����Ɋւ��鏈�����s�����\�b�h���W�߂��N���X<BR>
 */
public class WEB3SrvRegiRegistServiceImpl implements WEB3SrvRegiRegistService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3SrvRegiRegistServiceImpl.class);

    /**
     * @@roseuid 416F392903C8
     */
    public WEB3SrvRegiRegistServiceImpl()
    {

    }

    /**
     * (validate�K�p����)<BR>
     * �w�肳�ꂽ�K�p���Ԃ����������ǂ����𔻒肷��B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X�\���o�^�v�e�[�u������������B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪 and<BR>
     * �@@���X�R�[�h=����.���X�R�[�h and<BR>
     * �@@�����R�[�h=����.�ڋq�R�[�h and<BR>
     * �@@�K�p�I���������ݓ����̓��t���� and<BR>
     * �@@�L���敪="�L��" and<BR>
     * �@@����敪="�ʏ�" and<BR>
     * �@@�\���o�^ID!=����.�\���o�^ID(*2) and<BR>
     * �@@((�K�p�J�n��������.�K�p�J�n�� and �K�p�J�n��������.�K�p�I����) or<BR>
     * �@@(�K�p�I����������.�K�p�J�n�� and �K�p�I����������.�K�p�I����) or<BR>
     * �@@(�K�p�J�n��������.�K�p�J�n�� and <BR>�K�p�I����������.�K�p�I����))<BR>
     * <BR>
     * (*2) ����.�\���o�^ID��null�̏ꍇ�A���̏������w�肵�Ȃ��B<BR>
     * <BR>
     * 2) �������ʁ�0���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00984<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strMainAccountCode - (�ڋq�R�[�h)<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * @@param l_tsAppliEndDate - (�K�p�I����)<BR>
     * @@param l_registId - (�\���o�^ID)<BR>
     * @@roseuid 411047880125
     */
    public void validateAppliPeriod(String l_strInstitutionCode, String l_strSrvDiv, String l_strBranchCode,
        String l_strMainAccountCode, Timestamp l_tsAppliStartDate, Timestamp l_tsAppliEndDate, Long l_registId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAppliPeriod(String, String, String, String, Timestamp, Timestamp, Long)";
        log.entering(STR_METHOD_NAME);

        try
        {
        	//��Q�Ή��@@NO_U01711
        	//���ݓ��̎擾
			Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
			Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);

            if (l_registId == null)
            {
                log.debug("l_registId == null)");
                //�u�T�[�r�X�\���o�^�v�e�[�u������������B
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();   //DataNetworkException, DataQueryException
                String l_strWhere =
                        "institution_code = ? and " +       //�،���ЃR�[�h=����.�،���ЃR�[�h and
                        "srv_div = ? and " +                //�T�[�r�X�敪=����.�T�[�r�X�敪 and
                        "branch_code = ? and " +            //���X�R�[�h=����.���X�R�[�h and
                        "account_code = ? and " +           //�����R�[�h=����.�ڋq�R�[�h and
                        "appli_end_date >= ? and " +        //�K�p�I���������ݓ���(*1)�̓��t���� and
                        "effective_div = ? and " +          //�L���敪="�L��" and
                        "cancel_div = ? and " +             //����敪="�ʏ�" and
                        //((�K�p�J�n��������.�K�p�J�n�� and �K�p�J�n��������.�K�p�I����) or
                        "((appli_start_date >= ? and appli_start_date <= ?) or " +
                        //(�K�p�I����������.�K�p�J�n�� and �K�p�I����������.�K�p�I����) or
                        "(appli_end_date >= ? and appli_end_date <= ?) or " +
                        //(�K�p�J�n��������.�K�p�J�n�� and <BR>�K�p�I����������.�K�p�I����))
                        "(appli_start_date <= ? and appli_end_date >= ?))";

                Object l_bindVars[] =
                    {   l_strInstitutionCode,
                        l_strSrvDiv,
                        l_strBranchCode,
                        l_strMainAccountCode,
						l_datSystemDate,
                        WEB3EffectiveDivDef.EFFECTIVE,
                        WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate};

                List l_lisRows =
                    l_queryProcesser.doFindAllQuery(
                    SrvRegiApplicationRow.TYPE,
                    l_strWhere,
                    l_bindVars);

                if (l_lisRows.size() > 0)
                {
                    log.debug("�K�p���ԃ`�F�b�N�G���[�B�i�T�[�r�X�\���o�^�`�F�b�N�j");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00984,
                        getClass().getName() + STR_METHOD_NAME);
                }
                log.exiting(STR_METHOD_NAME);
            }
            else
            {
                //�u�T�[�r�X�\���o�^�v�e�[�u������������B
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                String l_strWhere =
                        "institution_code = ? and " +       //�،���ЃR�[�h=����.�،���ЃR�[�h and
                        "srv_div = ? and " +                //�T�[�r�X�敪=����.�T�[�r�X�敪 and
                        "branch_code = ? and " +            //���X�R�[�h=����.���X�R�[�h and
                        "account_code = ? and " +           //�����R�[�h=����.�ڋq�R�[�h and
                        "appli_end_date >= ? and " +        //�K�p�I���������ݓ���(*1)�̓��t���� and
                        "effective_div = ? and " +          //�L���敪="�L��" and
                        "cancel_div = ? and " +             //����敪="�ʏ�" and
                        "regist_id <> ? and " +             //�\���o�^ID!=����.�\���o�^ID(*2) and
                        //((�K�p�J�n��������.�K�p�J�n�� and �K�p�J�n��������.�K�p�I����) or
                        "((appli_start_date >= ? and appli_start_date <= ?) or " +
                        //(�K�p�I����������.�K�p�J�n�� and �K�p�I����������.�K�p�I����) or
                        "(appli_end_date >= ? and appli_end_date <= ?) or " +
                        //(�K�p�J�n��������.�K�p�J�n�� and <BR>�K�p�I����������.�K�p�I����))
                        "(appli_start_date <= ? and appli_end_date >= ?))";

                Object l_bindVars[] =
                    {   l_strInstitutionCode,
                        l_strSrvDiv,
                        l_strBranchCode,
                        l_strMainAccountCode,
						l_datSystemDate,
                        WEB3EffectiveDivDef.EFFECTIVE,
                        WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
                        l_registId,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate};

                List l_lisRows =
                    l_queryProcesser.doFindAllQuery(
                    SrvRegiApplicationRow.TYPE,
                    l_strWhere,
                    l_bindVars);

                if (l_lisRows.size() > 0)
                {
                    log.debug("�K�p���ԃ`�F�b�N�G���[�B�i�T�[�r�X�\���o�^�`�F�b�N�j");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00984,
                        getClass().getName() + STR_METHOD_NAME);
                }
                log.exiting(STR_METHOD_NAME);
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }

    }

	/**
	 * (calc�K�p�I����)<BR>
	 * �w�肳�ꂽ�K�p�J�n������A�K�p�I�������Z�o���ĕԋp����B<BR>
	 * <BR>
	 * �V�[�P���X�}�u�i�T�[�r�X���p�jcalc�K�p�I�����v�Q��<BR>
	 * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
	 * @@param l_strBranchCode - (���X�R�[�h)<BR>
	 * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
	 * @@param l_strAccountCode - (�����R�[�h)<BR>
	 * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
	 * @@param l_lngSrvUsePeriodId - (���p����ID)<BR>
	 * @@param l_strSpecialDiv - (����\���敪)<BR>
	 * @@param l_strFreeAttributeApplyDiv - (���������\���敪)<BR>* 
	 * @@return Timestamp
	 * @@roseuid 41295B860301
	 */
    public Timestamp calcAppliEndDate(String l_strInstitutionCode, String l_strBranchCode,
                          String l_strSrvDiv, String l_strAccountCode, Timestamp l_tsAppliStartDate,
                          long l_lngSrvUsePeriodId, String l_strSpecialDiv, String l_strFreeAttributeApplyDiv)
                                  throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " calcAppliEndDate(String, String, String, String, Timestamp, long)";
		log.entering(STR_METHOD_NAME);

		//1.1 get�T�[�r�X�}�X�^�[
		WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
		WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
			l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

		//1.2 get�T�[�r�X�\���o�^
		WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication = null;
		l_gentradeSrvRegiApplication =
			this.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode,
			WEB3SrvRegiCancelDivDef.USUAL_DEFAULT, WEB3EffectiveDivDef.EFFECTIVE, false);

		//1.3 <���򏈗� *1>
		long TRIAL_OR_FREE = 0;
		Timestamp l_tsAppliEndDate = null;
		String l_strTrialPeriodDiv = null;
        String l_strSrvUsePeriodDiv = null;
        Integer l_intTrialPeriodObj = new Integer(0);
		int l_intTrialPeriod = 0;
        long l_lngSrvUsePeriod = 0;
        int l_intSrvUsePeriod = 0;

		if (l_gentradeSrvRegiApplication != null)
		{
			//1.3.1 get�K�p�I����( )
			log.debug("get�K�p�I����");
			l_tsAppliEndDate = l_gentradeSrvRegiApplication.getAppliEndDate();
		}
		//get�\���v�T�[�r�X
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        //get�T�[�r�X�\���������
        List l_lisServiceApppliAttributeInfo = l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
            l_strInstitutionCode,
            l_strBranchCode,
            l_strAccountCode,
            l_strSrvDiv,
            null);

        //get�T�[�r�X�\��������� != null�@@�̏ꍇ
        String l_strFreeTargetPeriod = null;
        if (l_lisServiceApppliAttributeInfo != null)
        {
            //get�����Ώۊ���
            l_strFreeTargetPeriod = l_srvRegiApplicationRequiredService.getFreeTargetPeriod();
        }

		//����.���������\���敪 == null ���A"0"�@@�̏ꍇ
		if (l_strFreeAttributeApplyDiv == null || 
			WEB3SrvRegiFreeAttributeApplyDivDef.NORMAL_APPLY.equals(l_strFreeAttributeApplyDiv))
        {
            //is���p�\���\
            boolean l_blnTrialAppliPossible = l_srvRegiServiceInfoManagement.isTrialAppliPossible(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strSrvDiv,
                l_strAccountCode);

            //<���򏈗� *3>
            if (l_blnTrialAppliPossible)
            {
                //get���p���ԋ敪
                l_strTrialPeriodDiv = l_srvRegiApplicationRequiredService.getTrialPeriodDiv();
                //get���p����
                l_intTrialPeriodObj = l_srvRegiApplicationRequiredService.getTrialPeriod();
                if (l_intTrialPeriodObj != null)
                {
                    l_intTrialPeriod = l_intTrialPeriodObj.intValue();    
                }
            }

            //���p����ID != 0(���p����)�̏ꍇ
            if (l_lngSrvUsePeriodId != TRIAL_OR_FREE)
            {
                //get�T�[�r�X���p���ԗ���
                WEB3SrvRegiServiceUsePeriodAmt l_srvRegiServiceUsePeriodAmt =
                    l_srvRegiServiceMaster.getSrvUseTermAmt(l_lngSrvUsePeriodId, false);
                
                //get���p���ԋ敪
                l_strSrvUsePeriodDiv = l_srvRegiServiceUsePeriodAmt.getSrvUsePeriodDiv();
                //get���p����
                l_lngSrvUsePeriod = l_srvRegiServiceUsePeriodAmt.getSrvUsePeriod();
                l_intSrvUsePeriod = Integer.parseInt(WEB3StringTypeUtility.formatNumber(l_lngSrvUsePeriod));
            }
        }

		//<�K�p�I�����̎Z�o>
		Calendar l_caleNewAppliEndDate = Calendar.getInstance();
		Calendar l_caleAppliStartDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		int l_maxDay = 0;
		boolean l_maxDayFlag = false;
		
		//�T�[�r�X�\���o�^�I�u�W�F�N�g != null �̏ꍇ
		if (l_gentradeSrvRegiApplication != null)
		{
			//<���򏈗� *1>�œK�p�I�������擾�ł����ꍇ
			if (l_tsAppliEndDate != null)
			{
				log.debug("l_tsAppliEndDate != null");
				String l_stAppliEndDate = formatter.format(l_tsAppliEndDate);
				Date l_datAppliEndDate = WEB3DateUtility.getDate(l_stAppliEndDate, "yyyyMMdd");
				
				//����.���������\���敪 == null ���A"0"�@@�̏ꍇ
				if (l_strFreeAttributeApplyDiv == null || 
					WEB3SrvRegiFreeAttributeApplyDivDef.NORMAL_APPLY.equals(l_strFreeAttributeApplyDiv))
				{
	                l_caleAppliStartDate.setTime(l_datAppliEndDate);
	                l_caleNewAppliEndDate.setTime(l_datAppliEndDate);

	                //���p���Ԃ̉��Z
	                if (l_strTrialPeriodDiv != null && l_intTrialPeriodObj != null)
	                {
	                    log.debug("���p���Ԃ̉��Z");
	                    if (WEB3SrvUsePeriodDivDef.YEAR.equals(l_strTrialPeriodDiv)) // �N
	                    {
	                        log.debug("�N");
	                        l_caleNewAppliEndDate.add(Calendar.YEAR, l_intTrialPeriod);
	                        if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
	                        {
	                            l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
	                            l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
	                            l_maxDayFlag = true;
	                        }
	                    }
	                    else if (WEB3SrvUsePeriodDivDef.MONTH.equals(l_strTrialPeriodDiv)) // ��
	                    {
	                        log.debug("��");
	                        l_caleNewAppliEndDate.add(Calendar.MONTH, l_intTrialPeriod);
	                        if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
	                        {
	                            l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
	                            l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
	                            l_maxDayFlag = true;
	                        }
	                    }
	                    else if (WEB3SrvUsePeriodDivDef.DATE.equals(l_strTrialPeriodDiv)) // ��
	                    {
	                        log.debug("��");
	                        l_caleNewAppliEndDate.add(Calendar.DATE, l_intTrialPeriod);
	                    }

	                    
	                    if (!l_maxDayFlag && l_lngSrvUsePeriodId == TRIAL_OR_FREE)
	                    {
	                        l_caleNewAppliEndDate.add(Calendar.DATE, -1);
	                    }
	                    log.debug("l_datNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
	                    Date l_datNewAppliStartDate = l_caleNewAppliEndDate.getTime();
	                    l_caleAppliStartDate.setTime(l_datNewAppliStartDate);
	                }

	                //�K�p�J�n���̎Z�o
	                l_caleAppliStartDate.add(Calendar.DATE, 1);
	                l_caleNewAppliEndDate.add(Calendar.DATE, 1);
	                
					//���N�G�X�g.���p����ID != '0'(���p�\��)�̏ꍇ
					if (l_lngSrvUsePeriodId != TRIAL_OR_FREE)
					{
		                //���p���Ԃ̉��Z
		                if (WEB3SrvUsePeriodDivDef.YEAR.equals(l_strSrvUsePeriodDiv)) // �N
		                {
		                    log.debug("�N");
		                    l_caleNewAppliEndDate.add(Calendar.YEAR, l_intSrvUsePeriod);
		                    if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
		                    {
		                        l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
		                        l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
		                        l_maxDayFlag = true;
		                    }
		                }
		                else if (WEB3SrvUsePeriodDivDef.MONTH.equals(l_strSrvUsePeriodDiv)) // ��
		                {
		                    log.debug("��");
		                    l_caleNewAppliEndDate.add(Calendar.MONTH, l_intSrvUsePeriod);
		                    if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
		                    {
		                        l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
		                        l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
		                        l_maxDayFlag = true;
		                    }
		                }
		                else if (WEB3SrvUsePeriodDivDef.DATE.equals(l_strSrvUsePeriodDiv)) // ��
		                {
		                    log.debug("��");
		                    l_caleNewAppliEndDate.add(Calendar.DATE, l_intSrvUsePeriod);
		                }
					}
	            }

				else
				{
	                l_caleAppliStartDate.setTime(l_datAppliEndDate);
	                l_caleNewAppliEndDate.setTime(l_datAppliEndDate);

	                //�K�p�J�n���̎Z�o
	                l_caleAppliStartDate.add(Calendar.DATE, 1);
	                l_caleNewAppliEndDate.add(Calendar.DATE, 1);

	                //�Z�o���t�������_�ŗL���ȓK�p�I�����{get�����Ώۊ���()�Ŏ擾�������p����
	                l_caleNewAppliEndDate.add(Calendar.MONTH, Integer.parseInt(l_strFreeTargetPeriod));
	                l_caleNewAppliEndDate.add(Calendar.DATE, -1);
				}
			}
		}

		//�T�[�r�X�\���o�^�I�u�W�F�N�g == null �̏ꍇ
		else
		{
			//�i����.���������\���敪 == null ���A"0"�@@�̏ꍇ�j����
			//���N�G�X�g.���p����ID = 0 �̏ꍇ
			if ((l_strFreeAttributeApplyDiv == null || 
				WEB3SrvRegiFreeAttributeApplyDivDef.NORMAL_APPLY.equals(l_strFreeAttributeApplyDiv)) &&
				(l_lngSrvUsePeriodId == TRIAL_OR_FREE))
			{
	            //get���p����
				int l_trialPeriod = 0;
				if (l_srvRegiApplicationRequiredService.getTrialPeriod() != null)
				{
	            	l_trialPeriod = l_srvRegiApplicationRequiredService.getTrialPeriod().intValue();
				}

		        //�����ݓ��t�̎擾��
		        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
	            l_tsAppliStartDate = l_tsSystemTimestamp;
	            
				String l_stAppliEndDate = formatter.format(l_tsSystemTimestamp);
				Date l_datAppliEndDate = WEB3DateUtility.getDate(l_stAppliEndDate, "yyyyMMdd");
				l_caleAppliStartDate.setTime(l_datAppliEndDate);
				l_caleNewAppliEndDate.setTime(l_datAppliEndDate);

	            if (WEB3SrvUsePeriodDivDef.YEAR.equals(l_strTrialPeriodDiv))                  //�N
	            {
					log.debug("�N");
					l_caleNewAppliEndDate.add(Calendar.YEAR, l_trialPeriod);
					if(l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
					{
						l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
						l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
						l_maxDayFlag = true;
					}
	            }
	            else if (WEB3SrvUsePeriodDivDef.MONTH.equals(l_strTrialPeriodDiv))            //���@@�@@�@@
	            {
					log.debug("��");
					l_caleNewAppliEndDate.add(Calendar.MONTH, l_trialPeriod);
					if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
					{
						l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
						l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
						l_maxDayFlag = true;
					}
	            }
	            else if (WEB3SrvUsePeriodDivDef.DATE.equals(l_strTrialPeriodDiv))            //��
	            {
					log.debug("��");
					l_caleNewAppliEndDate.add(Calendar.DATE, l_trialPeriod);
	            }

				if (!l_maxDayFlag)
				{
					l_caleNewAppliEndDate.add(Calendar.DATE, -1);
				}
				log.debug("l_datNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
				Date l_datNewAppliEndDate = l_caleNewAppliEndDate.getTime();

	            l_tsAppliEndDate = new Timestamp(l_datNewAppliEndDate.getTime());
			}
			
			//����.���������\���敪 == null ���A"0"�@@�̏ꍇ
			else if (l_strFreeAttributeApplyDiv == null || 
			WEB3SrvRegiFreeAttributeApplyDivDef.NORMAL_APPLY.equals(l_strFreeAttributeApplyDiv))
			{
                //���p���Ԃ̉��Z
                if (l_strTrialPeriodDiv != null && l_intTrialPeriodObj != null)
                {
                    log.debug("���p���Ԃ̉��Z");
                    if (WEB3SrvUsePeriodDivDef.YEAR.equals(l_strTrialPeriodDiv)) // �N
                    {
                        log.debug("�N");
                        l_caleNewAppliEndDate.add(Calendar.YEAR, l_intTrialPeriod);
                        if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
                        {
                            l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
                            l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
                            l_maxDayFlag = true;
                        }
                    }
                    else if (WEB3SrvUsePeriodDivDef.MONTH.equals(l_strTrialPeriodDiv)) // ��
                    {
                        log.debug("��");
                        l_caleNewAppliEndDate.add(Calendar.MONTH, l_intTrialPeriod);
                        if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
                        {
                            l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
                            l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
                            l_maxDayFlag = true;
                        }
                    }
                    else if (WEB3SrvUsePeriodDivDef.DATE.equals(l_strTrialPeriodDiv)) // ��
                    {
                        log.debug("��");
                        l_caleNewAppliEndDate.add(Calendar.DATE, l_intTrialPeriod);
                    }

                    if (!l_maxDayFlag)
                    {
                        l_caleNewAppliEndDate.add(Calendar.DATE, -1);
                    }
                    log.debug("l_datNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
                    Date l_datNewAppliStartDate = l_caleNewAppliEndDate.getTime();
                    l_caleAppliStartDate.setTime(l_datNewAppliStartDate);

                    //�K�p�J�n���̎Z�o
                    if (l_strSrvUsePeriodDiv != null)
                    {
                        l_caleAppliStartDate.add(Calendar.DATE, 1);
                        l_caleNewAppliEndDate.add(Calendar.DATE, 1);
                    }
                }
                //���p���Ԃ̉��Z
                if (WEB3SrvUsePeriodDivDef.YEAR.equals(l_strSrvUsePeriodDiv)) // �N
                {
                    log.debug("�N");
                    l_caleNewAppliEndDate.add(Calendar.YEAR, l_intSrvUsePeriod);
                    if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
                    {
                        l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
                        l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
                        l_maxDayFlag = true;
                    }
                }
                else if (WEB3SrvUsePeriodDivDef.MONTH.equals(l_strSrvUsePeriodDiv)) // ��
                {
                    log.debug("��");
                    l_caleNewAppliEndDate.add(Calendar.MONTH, l_intSrvUsePeriod);
                    if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
                    {
                        l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
                        l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
                        l_maxDayFlag = true;
                    }
                }
                else if (WEB3SrvUsePeriodDivDef.DATE.equals(l_strSrvUsePeriodDiv)) // ��
                {
                    log.debug("��");
                    l_caleNewAppliEndDate.add(Calendar.DATE, l_intSrvUsePeriod);
                }
			}
			
			else
			{
                //�K�p�J�n���ɁAget�����Ώۊ���()�Ŏ擾�������p���Ԃ����Z����B
                l_caleNewAppliEndDate.add(Calendar.MONTH, Integer.parseInt(l_strFreeTargetPeriod));
                l_caleNewAppliEndDate.add(Calendar.DATE, -1);
			}
		}
		
		//����\��������\���敪 = 1 �̏ꍇ�B
		if (l_tsAppliEndDate == null && l_strSpecialDiv != null
					&& l_strSpecialDiv.equals(WEB3SrvRegiSpecialDivDef.END_OF_MONTH_DIV))
		{
			l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
			l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
			l_maxDayFlag = true;
		}

		if (l_strSrvUsePeriodDiv != null && !l_maxDayFlag)
		{
			l_caleNewAppliEndDate.add(Calendar.DATE, -1);
		}
		log.debug("l_datNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
		log.exiting(STR_METHOD_NAME);
		Date l_datNewAppliEndDate = l_caleNewAppliEndDate.getTime();
		return new Timestamp(l_datNewAppliEndDate.getTime());
	}

    /**
     * (submit�T�[�r�X�\���o�^)<BR>
     * �T�[�r�X�\���o�^�̍X�V�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�j�T�[�r�X�\���o�^�v�Q��<BR>
     * @@param l_newAppliSpec - (�\�����e)<BR>
     * �T�[�r�X���p�V�K�\�����e<BR>
     * @@param l_orderId - (����ID)<BR>
     * @@roseuid 411228760343
     */
    public void submitServiceRegist(WEB3SrvRegiNewAppliSpec l_newAppliSpec, Long l_orderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitServiceRegist(WEB3SrvRegiNewAppliSpec, Long)";
        log.entering(STR_METHOD_NAME);

        //1.1 get�T�[�r�X�}�X�^�[
        String l_strInstitutionCode = l_newAppliSpec.getInstitutionCode();
        String l_strSrvDiv = l_newAppliSpec.getSrvDiv();

        //1.2 get�T�[�r�X�\���o�^
        String l_strBranchCode = l_newAppliSpec.getBranchCode();
        String l_strAccountCode = l_newAppliSpec.getAccountCode();
        log.debug("get�T�[�r�X�\���o�^");
        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
            this.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode, null, WEB3EffectiveDivDef.EFFECTIVE, true);

        //1.3 <*1 ���򏈗�>
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        if (l_gentradeSrvRegiApplication != null)
        {
            log.debug("set�L���敪");
            //1.3.1 set�L���敪
            l_gentradeSrvRegiApplication.setEffectiveDiv(WEB3EffectiveDivDef.INEFFECTIVE);

            l_gentradeSrvRegiApplication.setLastUpdater(l_administrator.getAdministratorCode());

            //1.3.2 save�T�[�r�X�\���o�^
            l_gentradeSrvRegiApplication.saveSrvRegiApplication();
        }

        //1.4 createNew�T�[�r�X�\���o�^
        WEB3GentradeSrvRegiApplication l_newGentradeSrvRegiApplication =
            WEB3GentradeSrvRegiApplication.createNewSrvRegiApplication(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode);

        //1.4.1 <�p�����[�^�E�Z�b�g>
        log.debug("<�p�����[�^�E�Z�b�g>");
        //���\����=����.�������e.�\����
        if (l_newAppliSpec.getAppliDate() == null)
        {
            l_newGentradeSrvRegiApplication.setAppliDate(l_newAppliSpec.getAppliStartDate());
        }
        else
        {
            l_newGentradeSrvRegiApplication.setAppliDate(l_newAppliSpec.getAppliDate());
        }

        //���K�p�J�n��=����.�������e.�K�p�J�n��
        l_newGentradeSrvRegiApplication.setAppliStartDate(l_newAppliSpec.getAppliStartDate());
        //���K�p�I����=����.�������e.�K�p�I����
        l_newGentradeSrvRegiApplication.setAppliEndDate(l_newAppliSpec.getAppliEndDate());
        //������ID=����.����ID
        l_newGentradeSrvRegiApplication.setOrderId(l_orderId);
        //���o�^�敪==����.�������e.�o�^�敪
        l_newGentradeSrvRegiApplication.setPaymentDiv(l_newAppliSpec.getPaymentDiv());
        //���\�����I�敪=����.�������e.�\�����I�敪
        l_newGentradeSrvRegiApplication.setAppliLotDiv(l_newAppliSpec.getAppliLotDiv());
        //�����p����=����.�������e.���p����
        l_newGentradeSrvRegiApplication.setUseAmt(l_newAppliSpec.getUseAmt());
        //���o����=����.�������e.�o����
        l_newGentradeSrvRegiApplication.setPaymentDate(l_newAppliSpec.getPaymentDate());
        //���������I���������=null
        l_newGentradeSrvRegiApplication.setCancelLimitDate(null);

        l_newGentradeSrvRegiApplication.setLastUpdater(l_administrator.getAdministratorCode());

        //1.4.2 saveNew�T�[�r�X�\���o�^
        l_newGentradeSrvRegiApplication.saveNewSrvRegiApplication();

        //1.5 get�����\���敪
        String l_strInitializeAppliDiv =
            this.getInitializeAppliDiv(l_newAppliSpec.getInstitutionCode(), l_newAppliSpec.getBranchCode(),
            l_newAppliSpec.getSrvDiv(), l_newAppliSpec.getAccountCode());

        //1.6 get�����\���敪( )�̖߂�l��"��"�̏ꍇ
        try
        {
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strInitializeAppliDiv))
            {

                //1.10 �u�\�������Ǘ��e�[�u���v�o�^����
                SrvRegiHistoryParams l_srvRegiHistoryParams = new SrvRegiHistoryParams();
                OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                AccountManager l_accountManager = l_finApp.getAccountManager();

                //�،���ЃR�[�h
                String l_strInstitutionId = l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.INSTITUTION_ID);
                String l_strInstitutionCodeSession =
                    l_accountManager.getInstitution(Long.parseLong(l_strInstitutionId)).getInstitutionCode();
                l_srvRegiHistoryParams.setInstitutionCode(l_strInstitutionCodeSession);

                //���X�R�[�h
                l_srvRegiHistoryParams.setBranchCode(l_newAppliSpec.getBranchCode());

                //�T�[�r�X�敪
                l_srvRegiHistoryParams.setSrvDiv(l_newAppliSpec.getSrvDiv());

                //�����R�[�h
                l_srvRegiHistoryParams.setAccountCode(l_newAppliSpec.getAccountCode());

                //�\����
                l_srvRegiHistoryParams.setRegistDate(l_newAppliSpec.getAppliDate());

                //�\���o�H�敪
                l_srvRegiHistoryParams.setOrderRootDiv(WEB3OrderRootDivDef.ADMIN);

                //�X�V�҃R�[�h
                AdministratorRow l_administratorRow =
                    AdministratorDao.findRowByLoginId(l_opLoginSec.getLoginInfo().getLoginId());
                if (l_administratorRow == null)
                {
                    log.debug("�f�[�^�s�����G���[");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        getClass().getName() + STR_METHOD_NAME
                    );
                }
                l_srvRegiHistoryParams.setLastUpdater(l_administratorRow.getAdministratorCode());

                Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
                l_srvRegiHistoryParams.setCreatedTimestamp(l_tsSystemTimestamp);
                l_srvRegiHistoryParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

                l_queryProcesser.doInsertQuery(l_srvRegiHistoryParams);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (submit�T�[�r�X�\���ύX)<BR>
     * �T�[�r�X�\���o�^�̍X�V�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�j�T�[�r�X�\���ύX�v�Q��<BR>
     * @@param l_changeAppliSpec - (�\�����e)<BR>
     * �T�[�r�X���p�ύX�\�����e�I�u�W�F�N�g<BR>
     * @@roseuid 41119A2F01DA
     */
    public void submitServiceRegistChange(WEB3SrvRegiChangeAppliSpec l_changeAppliSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitServiceRegistChange(WEB3SrvRegiChangeAppliSpec)";
        log.entering(STR_METHOD_NAME);

        //1.1 get�T�[�r�X�\���o�^
        String l_strInstitutionCode = l_changeAppliSpec.getInstitutionCode();
        String l_strBranchCode = l_changeAppliSpec.getBranchCode();
        String l_strSrvDiv = l_changeAppliSpec.getSrvDiv();
        String l_strAccountCode = l_changeAppliSpec.getAccountCode();
        String l_strRegistId = l_changeAppliSpec.getRegistId();
        log.debug("get�T�[�r�X�\���o�^");
        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
            this.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode, Long.parseLong(l_strRegistId), true);

        //1.2  <���򏈗� *1>
        if (WEB3SrvRegiAppliLotDivDef.CANCEL.equals(l_changeAppliSpec.getAppliLotDiv()))
        {
            //1.2.1 set����敪
            log.debug("set����敪");
            l_gentradeSrvRegiApplication.setCancelDiv(WEB3SrvRegiCancelDivDef.CANCEL);
            log.debug("l_gentradeSrvRegiApplication.getCancelDiv() = " + l_gentradeSrvRegiApplication.getCancelDiv());

            //set�ŏI�X�V��
            WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
            l_gentradeSrvRegiApplication.setLastUpdater(l_administrator.getAdministratorCode());

            //save�T�[�r�X�\���o�^()
            l_gentradeSrvRegiApplication.saveSrvRegiApplication();
        }
        else
        // 1.3<���򏈗� *2>
        {
            //1.3.1 get�T�[�r�X�}�X�^�[
            WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
            WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
                l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

            //1.3.2 get�\���v�T�[�r�X
            WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
                l_srvRegiServiceMaster.getAppliRequiredSrv(false);
            if (l_srvRegiApplicationRequiredService == null)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //1.3.3  <���򏈗� *3>
            WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_srvRegiApplicationRequiredService.getLotDiv()))
            {
                log.debug("<���򏈗� *3>");
                //1.3.3.1 set�L���敪
                l_gentradeSrvRegiApplication.setEffectiveDiv(WEB3EffectiveDivDef.INEFFECTIVE);

                //set�ŏI�X�V��
                l_gentradeSrvRegiApplication.setLastUpdater(l_administrator.getAdministratorCode());

                //1.3.3.2 save�T�[�r�X�\���o�^
                l_gentradeSrvRegiApplication.saveSrvRegiApplication();

                //1.3.3.3 createNew�T�[�r�X�\���o�^
                WEB3GentradeSrvRegiApplication l_newGentradeSrvRegiApplication =
                    WEB3GentradeSrvRegiApplication.createNewSrvRegiApplication(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode);

                //1.3.3.4  <�p�����[�^�E�Z�b�g>
                log.debug("<�p�����[�^�E�Z�b�g>");
                //�\����=������s��́u�\�����v
                l_newGentradeSrvRegiApplication.setAppliDate(l_gentradeSrvRegiApplication.getAppliDate());
                //�\�����I�敪=����.�������e.get�\�����I�敪( )
                l_newGentradeSrvRegiApplication.setAppliLotDiv(l_changeAppliSpec.getAppliLotDiv());
                //�K�p�J�n��=����.�������e.get�K�p�J�n��( )
                l_newGentradeSrvRegiApplication.setAppliStartDate(l_changeAppliSpec.getAppliStartDate());
                //�K�p�I����=����.�������e.get�K�p�I����( )
                l_newGentradeSrvRegiApplication.setAppliEndDate(l_changeAppliSpec.getAppliEndDate());
                //�o�^�敪=����.�������e.get�o�^�敪( )
                l_newGentradeSrvRegiApplication.setPaymentDiv(l_changeAppliSpec.getPaymentDiv());
                //���p����=����.�������e.get���p����( )
                l_newGentradeSrvRegiApplication.setUseAmt(l_changeAppliSpec.getUseAmt());
                //�������I���������=null
                l_newGentradeSrvRegiApplication.setCancelLimitDate(null);

                //set�ŏI�X�V��
                l_newGentradeSrvRegiApplication.setLastUpdater(l_administrator.getAdministratorCode());

                //1.3.3.5 saveNew�T�[�r�X�\���o�^
                l_newGentradeSrvRegiApplication.saveNewSrvRegiApplication();
            }
            //1.3.4 <���򏈗� *4>
            if (WEB3ConditionsValueDivDef.HAVE.equals(l_srvRegiApplicationRequiredService.getLotDiv()))
            {
                log.debug("<���򏈗� *4>");
                //1.3.4.1 <�p�����[�^�E�Z�b�g>
                //�\����=����.�������e.get�\����( )
                l_gentradeSrvRegiApplication.setAppliDate(l_changeAppliSpec.getAppliDate());
                //�\�����I�敪=����.�������e.get�\�����I�敪( )
                l_gentradeSrvRegiApplication.setAppliLotDiv(l_changeAppliSpec.getAppliLotDiv());
                //�K�p�J�n��=����.�������e.get�K�p�J�n��( )
                l_gentradeSrvRegiApplication.setAppliStartDate(l_changeAppliSpec.getAppliStartDate());
                //�K�p�I����=����.�������e.get�K�p�I����( )
                l_gentradeSrvRegiApplication.setAppliEndDate(l_changeAppliSpec.getAppliEndDate());
                //�o�^�敪=����.�������e.get�o�^�敪( )
                l_gentradeSrvRegiApplication.setPaymentDiv(l_changeAppliSpec.getPaymentDiv());
                //���p����=����.�������e.get���p����( )
                l_gentradeSrvRegiApplication.setUseAmt(l_changeAppliSpec.getUseAmt());
                //�������I���������=null
                l_gentradeSrvRegiApplication.setCancelLimitDate(null);

                //set�ŏI�X�V��
                l_gentradeSrvRegiApplication.setLastUpdater(l_administrator.getAdministratorCode());

                //1.3.4.2 save�T�[�r�X�\���o�^
                l_gentradeSrvRegiApplication.saveSrvRegiApplication();
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit�]�͍S��)<BR>
     * �T�[�r�X�̗��p�\���ɔ����o���ׁ̈A���o�������P�ʂ��쐬���A<BR>
     * ���̍ۂɍ쐬��������ID��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jsubmit�]�͍S���v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�jsubmit�]�͍S���v): <BR>
     * 1.13<is����t���O( )=false�̏ꍇ�A�]�͎c���G���[�Ƃ��ė�O���X���[����  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01187<BR>
     * ==========================================================<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * �㗝���͎҃I�u�W�F�N�g<BR>
     * @@param l_dblUseAmt ���p����<BR>
     * @@param l_tsPaymentDate - (�o����)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strOrderChannel - (�����`���l��)<BR>
     * @@param l_strPassword - (�Ïؔԍ�)<BR>
     * @@return long
     * @@roseuid 413E63CA026B
     */
    public long submitRemainingPowerRestraint(WEB3GentradeSubAccount l_subAccount, Trader l_trader, double l_dblUseAmt, Timestamp l_tsPaymentDate, String l_strSrvDiv, String l_strOrderChannel, String l_strPassword)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRemainingPowerRestraint(WEB3GentradeSubAccount, Trader, double, Timestamp, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //1.1 <�g�����o�������}�l�[�W���擾>
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();

        //1.2 get���iID
        long l_lngProductId = l_aioOrderManager.getProductId(l_subAccount.getInstitution());

        //1.3 createNewOrderId
        log.debug("createNewOrderId");
        long l_lngOrderId = l_aioOrderManager.createNewOrderId();

        //1.4 ���o���������e
        WEB3AioNewOrderSpec l_aioNewOrderSpec =
            new WEB3AioNewOrderSpec(l_trader, OrderTypeEnum.CASH_OUT, AssetTransferTypeEnum.CASH_OUT, l_lngProductId,
            l_dblUseAmt, null, l_tsPaymentDate, null, new Long(l_lngOrderId));

        //1.5 �T�[�r�X���p�V�K�����X�V�C���^�Z�v�^
        WEB3SrvRegiNewOrderUpdateInterceptor l_srvRegiNewOrderUpdateInterceptor = new WEB3SrvRegiNewOrderUpdateInterceptor();

        //1.6 set�T�[�r�X�敪
        log.debug("set�T�[�r�X�敪");
        l_srvRegiNewOrderUpdateInterceptor.setSrvDiv(l_strSrvDiv);

        //1.7 set��n��
        l_srvRegiNewOrderUpdateInterceptor.setDeliveryDate(l_tsPaymentDate);

        //1.8:�c�Ɠ��v�Z(Timestamp)
        WEB3GentradeBizDate l_datBizDate = new WEB3GentradeBizDate(l_tsPaymentDate);

        //1.9:roll(int)
        Timestamp l_ts = l_datBizDate.roll(-1);

        //1.10 set������
        l_srvRegiNewOrderUpdateInterceptor.setOrderBizDate(l_ts);

        //1.10 set�����o�H�敪
        // (*) ����.�����`���l����Null�̏ꍇ�AWEB3OrderRootDivDef.ADMIN��ݒ肷��B
        // (*) ����.�����`���l���̒l��WEB3ChannelDef.�R�[���Z���^�[�̏ꍇ�A
        //     WEB3OrderRootDivDef.�R�[���Z���^�[��ݒ肷��B
        // (*) ����.�����`���l���̒l��WEB3ChannelDef.�R�[���Z���^�[�łȂ��ꍇ�A
        //     WEB3OrderRootDivDef.PC��ݒ肷��B
        if (l_strOrderChannel == null)
        {
            l_srvRegiNewOrderUpdateInterceptor.setOrderRootDiv(WEB3OrderRootDivDef.ADMIN);
        }
        else if (WEB3ChannelDef.CALL_CENTER.equals(l_strOrderChannel))
        {
            l_srvRegiNewOrderUpdateInterceptor.setOrderRootDiv(WEB3OrderRootDivDef.CALLCENTER);
        }
        else
        {
            l_srvRegiNewOrderUpdateInterceptor.setOrderRootDiv(WEB3OrderRootDivDef.PC);
        }

        //1.11 setThreadLocalPersistenceEventInterceptor
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_srvRegiNewOrderUpdateInterceptor);

        //1.12 validate����]��
        WEB3TPTradingPowerService l_web3TPTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        Object[] l_obj1 = new Object[1];
        Object[] l_obj2 = new Object[1];
        l_obj1[0] = l_srvRegiNewOrderUpdateInterceptor;
        l_obj2[0] = l_aioNewOrderSpec;

        WEB3TPTradingPowerResult l_web3TPTradingPowerResult =
                    l_web3TPTradingPowerService.validateTradingPower(l_subAccount, l_obj1, l_obj2, OrderTypeEnum.CASH_OUT, true);

        //1.13 is����t���O()
        boolean l_blnDecisionFlag = l_web3TPTradingPowerResult.isResultFlg();

        //1.14 <is����t���O( )=false�̏ꍇ�A�]�͎c���G���[�Ƃ��ė�O���X���[����B>
        if (!l_blnDecisionFlag)
        {
            log.debug("�]�͎c���G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01187,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.15 submitNewOrder
        OrderSubmissionResult l_orderSubmissionResult =
            l_aioOrderManager.submitNewOrder(l_subAccount, ProductTypeEnum.CASH, l_aioNewOrderSpec,
            l_lngOrderId, l_strPassword, true);
        if(l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("throw new WEB3SystemLayerException");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lngOrderId;
    }

    /**
     * (submit�]�͉��)<BR>
     * �T�[�r�X�̗��p�\���̎���Ȃǂɔ����]�͉���ׁ̈A<BR>
     * ���o�������P�ʂɎ��������������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jsubmit�]�͉���v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * @@param l_strPassword - (�Ïؔԍ�)<BR>
     * @@roseuid 413E63D302A9
     */
    public void submitRemainingPowerRelease(WEB3GentradeSubAccount l_subAccount, long l_lngOrderId, String l_strPassword) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRemainingPowerRelease(WEB3GentradeSubAccount, long, String)";
        log.entering(STR_METHOD_NAME);

        //1.1  <�g�����o�������}�l�[�W���擾>
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();

        //1.2 CancelOrderSpec
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_lngOrderId);

        //1.3 �T�[�r�X���p����X�V�C���^�Z�v�^
        WEB3SrvRegiCancelUpdateInterceptor l_srvRegiCancelUpdateInterceptor = new WEB3SrvRegiCancelUpdateInterceptor();

        //1.4 setThreadLocalPersistenceEventInterceptor
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_srvRegiCancelUpdateInterceptor);
		
		//��Q�Ή� �]�͉��
        //1.5 submitCancelOrder
        log.debug("submitCancelOrder");
        OrderSubmissionResult l_orderSubmissionResult =
            l_aioOrderManager.submitCancelOrder(l_subAccount, l_cancelOrderSpec, l_strPassword, true);
        if(l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("throw new WEB3SystemLayerException");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                getClass().getName() + STR_METHOD_NAME);
        }
        
		//1.6 �]�͍Čv�Z
		log.debug("�]�͍Čv�Z");
		WEB3TPTradingPowerService l_web3TPTradingPowerService =
			(WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
		l_web3TPTradingPowerService.reCalcTradingPower(l_subAccount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�T�[�r�X�\���o�^)<BR>
     * �w�肳�ꂽ�T�[�r�X�\���o�^���擾���A������T�[�r�X�\���o�^�I�u�W�F�N�g��
     * �쐬���ԋp����B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X�\���o�^�e�[�u���v����������B<BR>
     * �i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B)<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@���X�R�[�h=����.���X�R�[�h and<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪 and<BR>
     * �@@�����R�[�h=����.�����R�[�h and<BR>
     * �@@�\���o�^ID=����.�\���o�^ID and<BR>
     * �@@�L���敪="�L��"<BR>
     * <BR>
     *  1-1) ��������=0���̏ꍇ<BR>
     * �@@�@@null��ԋp����B<BR>
     *  1-2) ��������=1���̏ꍇ<BR>
     * �@@�@@�������ʂ̃T�[�r�X�\���o�^Params�I�u�W�F�N�g�������ɃT�[�r�X�\���o�^�N���X��<BR>
     * �@@�R���X�g���N�^���R�[�����A�T�[�r�X�\���o�^�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * 2) ����.is�s���b�N=true�̏ꍇ<BR>
     * �@@���������T�[�r�X�\���o�^�I�u�W�F�N�g.createForUpdateParams( )���R�[������B<BR>
     * <BR>
     * 3) �T�[�r�X�\���o�^�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@param l_lngRegistId - (�\���o�^ID)<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * true:�s���b�N���s���@@false:�s���b�N���s��Ȃ�<BR>
     * @@return WEB3GentradeSrvRegiApplication
     * @@roseuid 41203F150220
     */
    public WEB3GentradeSrvRegiApplication getServiceRegist(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, long l_lngRegistId, boolean l_blnIsRowLock)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getServiceRegist(String, String, String, String, long, boolean)";
        log.entering(STR_METHOD_NAME);

        //1) �ȉ��̏����Łu�T�[�r�X�\���o�^�e�[�u���v����������B
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            String l_strWhere =
                    "institution_code = ? and " +        //�،���ЃR�[�h=����.�،���ЃR�[�h and
                    "branch_code = ? and " +             //���X�R�[�h=����.���X�R�[�h and
                    "srv_div = ? and " +                 //�T�[�r�X�敪=����.�T�[�r�X�敪 and
                    "account_code = ? and " +            //�����R�[�h=����.�����R�[�h and
                    "regist_id = ? and " +               //�\���o�^ID=����.�\���o�^ID and
                    "effective_div = ?";                 //�L���敪="�L��"

            Object l_bindVars[] =
                {   l_strInstitutionCode,
                    l_strBranchCode,
                    l_strSrvDiv,
                    l_strAccountCode,
                    new Long(l_lngRegistId),
                    WEB3EffectiveDivDef.EFFECTIVE};

            List l_lisRows = null;
            if (l_blnIsRowLock)
            {
                log.debug("�s���b�N���s��");
                l_lisRows =
                    l_queryProcesser.doFindAllQuery(
                    SrvRegiApplicationRow.TYPE,
                    l_strWhere,
                    " FOR UPDATE ",
                    l_bindVars);
            }
            else
            {
                log.debug("�s���b�N���s��Ȃ�");
                l_lisRows =
                    l_queryProcesser.doFindAllQuery(
                    SrvRegiApplicationRow.TYPE,
                    l_strWhere,
                    l_bindVars);
            }

            WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication = null;
            //��������=0���̏ꍇ
            if (l_lisRows.size() == 0)
            {
                log.debug("��������=0���̏ꍇ");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            //��������=1���̏ꍇ
            else
            {
                log.debug("��������=1���̏ꍇ");
                SrvRegiApplicationParams l_srvRegiApplicationParams = (SrvRegiApplicationParams)l_lisRows.get(0);
                l_gentradeSrvRegiApplication =
                    new WEB3GentradeSrvRegiApplication(l_srvRegiApplicationParams);
            }
            if (l_blnIsRowLock)
            {
                log.debug("l_blnIsRowLock");
                l_gentradeSrvRegiApplication.createForUpdateParams();
            }

            log.exiting(STR_METHOD_NAME);
            return l_gentradeSrvRegiApplication;
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }

    /**
     * (get�T�[�r�X�\���o�^)<BR>
     * ���ݗL���ȃT�[�r�X�\���o�^���擾���A���������<BR>
     * �T�[�r�X�\���o�^�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X�\���o�^�e�[�u���v����������B<BR>
     * �i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B)<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h������.�،���ЃR�[�h and<BR>
     * �@@���X�R�[�h������.���X�R�[�h and<BR>
     * �@@�T�[�r�X�敪������.�T�[�r�X�敪 and<BR>
     * �@@�����R�[�h������.�����R�[�h and<BR>
     * �@@�K�p�I���������ݓ��t(*1) and<BR>
     * �@@����敪��(*2) and<BR>
     * �@@�L���敪��(*3)<BR>
     * <BR>
     * [���я�]<BR>
     * �@@�K�p�J�n��<BR>
     * <BR>
     * (*1) ��� = GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̓��t����<BR>
     * <BR>
     * (*2) ����.����敪="�w�薳"�̏ꍇ�A�����Ɋ܂߂Ȃ��B<BR>
     * �@@�@@��L�ȊO�́A�u����敪=����.����敪�v�������Ɋ܂߂�B<BR>
     * <BR>
     * (*3) ����.�L���敪="�w�薳"�̏ꍇ�A�����Ɋ܂߂Ȃ��B<BR>
     * �@@�@@��L�ȊO�́A�u�L���敪=����.�L���敪�v�������Ɋ܂߂�B<BR>
     * <BR>
     * 2) �߂�l�̐���<BR>
     *  2-1) �������ʂ�0���̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     *  2-2) �������ʂ�1���̏ꍇ<BR>
     * �@@�������ʂł���T�[�r�X�\���o�^Params�I�u�W�F�N�g�������ɁA<BR>
     * �@@�T�[�r�X�\���o�^�N���X�̃R���X�g���N�^���R�[������B<BR>
     * <BR>
     *  2-3) �������ʁ�1���̏ꍇ<BR>
     * �@@�������ʂ̈�Ԑ擪�̃��R�[�h���T�[�r�X�\���o�^Params�I�u�W�F�N�g�������ɁA<BR>
     * �@@�T�[�r�X�\���o�^�N���X�̃R���X�g���N�^���R�[������B<BR>
     * <BR>
     * 3) ���������T�[�r�X�\���o�^�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@param l_strCancelDiv - (����敪)<BR>
     * 0:�ʏ�@@1:����@@null:�w�薳<BR>
     * @@param l_strEffectiveDiv - (�L���敪)<BR>
     * 0:�L���@@1:�����@@null:�w�薳<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * true:�s���b�N���s���@@false:�s���b�N���s��Ȃ�<BR>
     * @@return WEB3GentradeSrvRegiApplication
     * @@roseuid 4108ECAD00D5
     */
    public WEB3GentradeSrvRegiApplication getServiceRegist(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, String l_strCancelDiv, String l_strEffectiveDiv, boolean l_blnIsRowLock)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getServiceRegist(String, String, String, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        //1) �ȉ��̏����Łu�T�[�r�X�\���o�^�e�[�u���v����������B
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException

            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
            Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);
            List l_lisRows = null;
            if (l_strCancelDiv == null && l_strEffectiveDiv == null)
            {
                log.debug("l_strCancelDiv == null && l_strEffectiveDiv == null");
                String l_strWhere =
                        "institution_code = ? and " +        //�،���ЃR�[�h=����.�،���ЃR�[�h and
                        "branch_code = ? and " +             //���X�R�[�h=����.���X�R�[�h and
                        "srv_div = ? and " +                 //�T�[�r�X�敪=����.�T�[�r�X�敪 and
                        "account_code = ? and " +            //�����R�[�h=����.�����R�[�h and
                        "appli_end_date >= ?";               //�K�p�I���������ݓ��t(*1) and

                Object l_bindVars[] =
                    {   l_strInstitutionCode,
                        l_strBranchCode,
                        l_strSrvDiv,
                        l_strAccountCode,
                        l_datSystemDate};

                String l_strOrderBy = " appli_start_date ";

                if (l_blnIsRowLock)
                {
                    log.debug("�s���b�N���s��");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        " FOR UPDATE ",
                        l_bindVars);
                }
                else
                {
                    log.debug("�s���b�N���s��Ȃ�");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        null,
                        l_bindVars);
                }
            }
            else if(l_strCancelDiv == null && l_strEffectiveDiv != null)
            {
                log.debug("else if(l_strCancelDiv == null && l_strEffectiveDiv != null)");
                String l_strWhere =
                        "institution_code = ? and " +        //�،���ЃR�[�h=����.�،���ЃR�[�h and
                        "branch_code = ? and " +             //���X�R�[�h=����.���X�R�[�h and
                        "srv_div = ? and " +                 //�T�[�r�X�敪=����.�T�[�r�X�敪 and
                        "account_code = ? and " +            //�����R�[�h=����.�����R�[�h and
                        "appli_end_date >= ? and " +         //�K�p�I���������ݓ��t(*1) and
                        "effective_div = ?";                 //�L���敪��(*3)

                Object l_bindVars[] =
                    {   l_strInstitutionCode,
                        l_strBranchCode,
                        l_strSrvDiv,
                        l_strAccountCode,
                        l_datSystemDate,
                        l_strEffectiveDiv};

                String l_strOrderBy = " appli_start_date ";

                if (l_blnIsRowLock)
                {
                    log.debug("�s���b�N���s��");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        " FOR UPDATE ",
                        l_bindVars);
                }
                else
                {
                    log.debug("�s���b�N���s��Ȃ�");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        null,
                        l_bindVars);
                }
            }
            else if(l_strCancelDiv != null && l_strEffectiveDiv == null)
            {
                log.debug("else if(l_strCancelDiv != null && l_strEffectiveDiv == null)");
                String l_strWhere =
                        "institution_code = ? and " +        //�،���ЃR�[�h=����.�،���ЃR�[�h and
                        "branch_code = ? and " +             //���X�R�[�h=����.���X�R�[�h and
                        "srv_div = ? and " +                 //�T�[�r�X�敪=����.�T�[�r�X�敪 and
                        "account_code = ? and " +            //�����R�[�h=����.�����R�[�h and
                        "appli_end_date >= ? and " +         //�K�p�I���������ݓ��t(*1) and
                        "cancel_div = ?";                    //�L���敪��(*3)

                Object l_bindVars[] =
                    {   l_strInstitutionCode,
                        l_strBranchCode,
                        l_strSrvDiv,
                        l_strAccountCode,
                        l_datSystemDate,
                        l_strCancelDiv};

                String l_strOrderBy = " appli_start_date ";

                if (l_blnIsRowLock)
                {
                    log.debug("�s���b�N���s��");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        " FOR UPDATE ",
                        l_bindVars);
                }
                else
                {
                    log.debug("�s���b�N���s��Ȃ�");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        null,
                        l_bindVars);
                }
            }
            else
            {
                log.debug("else");
                String l_strWhere =
                        "institution_code = ? and " +        //�،���ЃR�[�h=����.�،���ЃR�[�h and
                        "branch_code = ? and " +             //���X�R�[�h=����.���X�R�[�h and
                        "srv_div = ? and " +                 //�T�[�r�X�敪=����.�T�[�r�X�敪 and
                        "account_code = ? and " +            //�����R�[�h=����.�����R�[�h and
                        "appli_end_date >= ? and " +         //�K�p�I���������ݓ��t(*1) and
                        "cancel_div = ? and " +              //����敪��(*2) and
                        "effective_div = ?";                  //�L���敪��(*3)

                Object l_bindVars[] =
                    {   l_strInstitutionCode,
                        l_strBranchCode,
                        l_strSrvDiv,
                        l_strAccountCode,
                        l_datSystemDate,
                        l_strCancelDiv,
                        l_strEffectiveDiv};

                String l_strOrderBy = " appli_start_date ";

                if (l_blnIsRowLock)
                {
                    log.debug("�s���b�N���s��");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        " FOR UPDATE ",
                        l_bindVars);
                }
                else
                {
                    log.debug("�s���b�N���s��Ȃ�");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        null,
                        l_bindVars);
                }
            }

            WEB3GentradeSrvRegiApplication l_WEB3GentradeSrvRegiApplication = null;
            //��������=0���̏ꍇ
            if (l_lisRows.size() == 0)
            {
                log.debug("��������=0���̏ꍇ ");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            //��������=1���̏ꍇ
            else if (l_lisRows.size() == 1)
            {
                log.debug("��������=1���̏ꍇ");
                SrvRegiApplicationParams l_SrvRegiApplicationParams = (SrvRegiApplicationParams)l_lisRows.get(0);
                l_WEB3GentradeSrvRegiApplication =
                    new WEB3GentradeSrvRegiApplication(l_SrvRegiApplicationParams);
            }
            //�������ʁ�1���̏ꍇ
            else
            {
                log.debug("�������ʁ�1���̏ꍇ");
                SrvRegiApplicationParams l_SrvRegiApplicationParams = (SrvRegiApplicationParams)l_lisRows.get(0);
                l_WEB3GentradeSrvRegiApplication =
                    new WEB3GentradeSrvRegiApplication(l_SrvRegiApplicationParams);
            }
            if (l_blnIsRowLock)
            {
                log.debug("l_blnIsRowLock");
                l_WEB3GentradeSrvRegiApplication.createForUpdateParams();
            }

            log.exiting(STR_METHOD_NAME);
            return l_WEB3GentradeSrvRegiApplication;
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

    }

    /**
     * (get�T�[�r�X�\���o�^�ꗗ)<BR>
     * �w�肳�ꂽ�����ɍ��v����T�[�r�X�\���o�^�ꗗ���������A<BR>
     * ���̌��ʂ��T�[�r�X�\���o�^Params�I�u�W�F�N�g�̔z��ɂ��ĕԋp����B<BR>
     * <BR>
     * 1) �\�[�g�����̍쐬<BR>
     * �@@����.�\�[�g������null�ȊO�ł���A���v�f����0�̏ꍇ�A<BR>
     * �@@����.�\�[�g�����̌������A�ȉ����J��Ԃ��B<BR>
     *  1-1) �Ή�����e�[�u���̗񕨗����������^�~���w���t�����Z�b�g����B <BR>
     * <BR>
     * �@@�@@���L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�B <BR>
     * �@@�@@�@@���L�[���ڕ�����(�V���{����)�́A���b�Z�[�W��`�����Q�ƁB <BR>
     * �@@�@@�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q�ƁB <BR>
     * <BR>
     * �@@�@@�@@�@@�E���X�@@�@@�@@�@@�@@�@@�@@�@@=�T�[�r�X�\���o�^�e�[�u��.���X�R�[�h<BR>
     * �@@�@@�@@�@@�E�ڋq�@@�@@�@@�@@�@@�@@�@@�@@=�T�[�r�X�\���o�^�e�[�u��.�����R�[�h<BR>
     * �@@�@@�@@�@@�E�\�����I�敪�@@�@@=�T�[�r�X�\���o�^�e�[�u��.�\�����I�敪<BR>
     * �@@�@@�@@�@@�E�\�����@@�@@�@@�@@�@@�@@ =�T�[�r�X�\���o�^�e�[�u��.�\����<BR>
     * �@@�@@�@@�@@�E�K�p�J�n���@@�@@�@@ =�T�[�r�X�\���o�^�e�[�u��.�K�p�J�n��<BR>
     * �@@�@@�@@�@@�E�K�p�I�����@@�@@�@@ =�T�[�r�X�\���o�^�e�[�u��.�K�p�I����<BR>
     * �@@�@@�@@�@@�E�o�^�敪�@@�@@�@@�@@�@@=�T�[�r�X�\���o�^�e�[�u��.�o�^�敪<BR>
     * �@@�@@�@@�@@�E���p�����@@�@@�@@�@@�@@=�T�[�r�X�\���o�^�e�[�u��.���p����<BR>
     * �@@�@@�@@�@@�E�ŏI�X�V���@@�@@�@@ =�T�[�r�X�\���o�^�e�[�u��.�X�V���t<BR>
     * �@@�@@�@@�@@�E�ŏI�X�V�ҁ@@�@@�@@ =�T�[�r�X�\���o�^�e�[�u��.�X�V�҃R�[�h<BR>
     * <BR>
     * �@@�@@�������^�~���w��́A�\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ肷��B <BR>
     * <BR>
     * 2) �ȉ��̌��������ŁA�u�T�[�r�X�\���o�^�e�[�u���v����������B<BR>
     * �@@[��������]<BR>
     * �@@�@@���،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * �@@�@@���T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * <BR>
     * �@@�@@�����X�R�[�h=����.���X�R�[�h ---------�i�������A����.���X�R�[�h��null<BR>
     * �ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@�������R�[�h=����.�����R�[�h ---------�i�������A����.�����R�[�h��null<BR>
     * �ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@���o�^�敪=����.�o�^�敪 ----------�i�������A����.�o�^�敪��null<BR>
     * �ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@���K�p�J�n��������.�K�p�J�n���i���j -�i�������A����.�K�p�J�n���i���j<BR>
     * ��null�ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@���K�p�J�n��������.�K�p�J�n���i���j -�i�������A����.�K�p�J�n���i���j<BR>
     * ��null�ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@���\����������.�\�����i���j ---------�i�������A����.�\�����i���j��null<BR>
     * �ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@���\����������.�\�����i���j ---------�i�������A����.�\�����i���j��null<BR>
     * �ł͖����ꍇ�Ɍ���j<BR>
     * <BR>
     * �@@�@@���\�����I�敪�E����敪�E�������I�����������(*1)<BR>
     * �@@�@@���K�p�I���������ݓ����̓��t����<BR>
     * �@@�@@���L���敪��"�L��"<BR>
     * <BR>
     * �@@[���я�]<BR>
     * �@@�@@1)�Ő��������\�[�g����(*3)<BR>
     * <BR>
     * (*1)<BR>
     * �@@�����N�G�X�g�f�[�^.�\�����I�敪="���"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�i�\�����I�敪�������Ɋ܂߂Ȃ��j<BR>
     * �@@�@@�@@�@@�@@����敪��"���"<BR>
     * �@@�@@�@@�@@�@@�i�������I����������������Ɋ܂߂Ȃ��j<BR>
     * <BR>
     * �@@�����N�G�X�g�f�[�^.�\�����I�敪="�S��"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�i�\�����I�敪�������Ɋ܂߂Ȃ��j<BR>
     * �@@�@@�@@�@@�@@�i����敪�������Ɋ܂߂Ȃ��j<BR>
     * �@@�@@�@@�@@�@@�i�������I����������������Ɋ܂߂Ȃ��j<BR>
     * <BR>
     * �@@�����N�G�X�g�f�[�^.�\�����I�敪="�\��"�̏ꍇ<BR>
     * �@@�@@�@@�@@ (�i�\�����I�敪�����N�G�X�g�f�[�^.�\�����I�敪�j or<BR>
     * �@@�@@�@@�@@�@@�i�\�����I�敪��"�������I" and<BR>
     * �@@�@@�@@�@@�@@�@@�������I��������������ݓ���(*2)�̓��t�����j) and<BR>
     * �@@�@@�@@�@@�@@����敪��"�ʏ�"<BR>
     * <BR>
     * �@@�����N�G�X�g�f�[�^.�\�����I�敪="���I�^�{�\��"�̏ꍇ<BR>
     * �@@�@@�@@�@@ (�i�\�����I�敪�����N�G�X�g�f�[�^.�\�����I�敪�j or<BR>
     * �@@�@@�@@�@@�@@�i�\�����I�敪��"�������I" and<BR>
     * �@@�@@�@@�@@�@@�@@�������I��������������ݓ���(*2)�̓��t�����j) and<BR>
     * �@@�@@�@@�@@�@@����敪��"�ʏ�"<BR>
     * <BR>
     * �@@������ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�\�����I�敪�����N�G�X�g�f�[�^.�\�����I�敪 and<BR>
     * �@@�@@�@@�@@�@@����敪��"�ʏ�"<BR>
     * �@@�@@�@@�@@�@@�������I���������=null<BR>
     * <BR>
     * (*3)����.�\�[�g����=null�̏ꍇ�A�K�p�J�n���̏������w�肷��B<BR>
     * <BR>
     * 3) 2)�̌������ʂ�ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�i�K�{�j<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪�i�K�{�j<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@param l_strPaymentDiv - (�o�^�敪)<BR>
     * 0:�L���@@1:�����@@2:�S��<BR>
     * @@param l_strAppliLotDiv - (�\�����I�敪)<BR>
     * @@param l_tsAppliStartDateFrom - (�K�p�J�n���i���j)<BR>
     * @@param l_tsAppliStartDateTo - (�K�p�J�n���i���j)<BR>
     * @@param l_tsAppliDateFrom - (�\�����i���j)<BR>
     * @@param l_tsAppliDateTo - (�\�����i���j)<BR>
     * @@param l_sortConds - (�\�[�g����)<BR>
     * �Ώۍ���:<BR>
     * �ᒊ�I���̏ꍇ��<BR>
     * "���X","�ڋq","�K�p�J�n��","�K�p�I����","�o�^�敪","���p����"<BR>
     * ,"�ŏI�X�V��","�ŏI�X�V��"<BR>
     * �ᒊ�I�L�̏ꍇ��<BR>
     * "���X","�ڋq","�\�����I�敪","�\����","�K�p�J�n��","�K�p�I����"<BR>
     * ,"�o�^�敪","���p����","�ŏI�X�V��","�ŏI�X�V��"<BR>
     * @@return webbroker3.gentrade.data.SrvRegiApplicationParams[ ]
     * @@roseuid 41078D740138
     */
    public SrvRegiApplicationParams[] getServiceRegistLists(String l_strInstitutionCode, String[] l_strBranchCodes,
        String l_strSrvDiv, String l_strAccountCode, String l_strPaymentDiv, String l_strAppliLotDiv,
        Timestamp l_tsAppliStartDateFrom, Timestamp l_tsAppliStartDateTo, Timestamp l_tsAppliDateFrom,
        Timestamp l_tsAppliDateTo, WEB3SrvRegiSortKey[] l_sortConds)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getServiceRegistLists(String, String[], String, String, String, String, Timestamp, Timestamp, Timestamp, Timestamp, WEB3SrvRegiSortKey[])";
        log.entering(STR_METHOD_NAME);

        String l_strWhere = null;
        List l_lis = new ArrayList();
        String l_strOrderBy = "";

        if (l_sortConds != null && l_sortConds.length > 0)
        {
            log.debug("l_sortConds != null && l_sortConds.length > 0");
            int l_intLength = l_sortConds.length;
            for (int i = 0; i < l_intLength; i++)
            {
                log.debug("for " + i);
                if (WEB3SrvRegiKeyItemDef.BRANCH_CODE.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("branch_code asc");
                        l_strOrderBy = l_strOrderBy + " branch_code asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("branch_code desc");
                        l_strOrderBy = l_strOrderBy + " branch_code desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.ACCOUNT_CODE.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("account_code asc");
                        l_strOrderBy = l_strOrderBy + " account_code asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("account_code desc");
                        l_strOrderBy = l_strOrderBy + " account_code desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_LOT_DIV.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_lot_div asc");
                        l_strOrderBy = l_strOrderBy + " appli_lot_div asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_lot_div desc");
                        l_strOrderBy = l_strOrderBy + " appli_lot_div desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_DATE.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_date asc");
                        l_strOrderBy = l_strOrderBy + " appli_date asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_date desc");
                        l_strOrderBy = l_strOrderBy + " appli_date desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_START_DATE.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_start_date asc");
                        l_strOrderBy = l_strOrderBy + " appli_start_date asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_start_date desc");
                        l_strOrderBy = l_strOrderBy + " appli_start_date desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_END_DATE.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_end_date asc");
                        l_strOrderBy = l_strOrderBy + " appli_end_date asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_end_date desc");
                        l_strOrderBy = l_strOrderBy + " appli_end_date desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.PAYMENT_DIV.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("payment_div asc");
                        l_strOrderBy = l_strOrderBy + " payment_div asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("payment_div desc");
                        l_strOrderBy = l_strOrderBy + " payment_div desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.USE_AMT.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("use_amt asc");
                        l_strOrderBy = l_strOrderBy + " use_amt asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("use_amt desc");
                        l_strOrderBy = l_strOrderBy + " use_amt desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.LAST_UPDATED_TIMESTAMP.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("last_updated_timestamp  asc");
                        l_strOrderBy = l_strOrderBy + " last_updated_timestamp  asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("last_updated_timestamp  desc");
                        l_strOrderBy = l_strOrderBy + " last_updated_timestamp  desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.LAST_UPDATER.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("last_updater  asc");
                        l_strOrderBy = l_strOrderBy + " last_updater  asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("last_updater  desc");
                        l_strOrderBy = l_strOrderBy + " last_updater  desc";
                    }
                }

                if(i != l_intLength - 1)
                {
                    l_strOrderBy = l_strOrderBy + ", ";
                }
            }
        }
        else
        {
            log.debug("appli_start_date");
            l_strOrderBy = " appli_start_date";

        }
		
		//��Q�Ή� NO_U01711
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
		Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);

        l_strWhere =
                "institution_code = ? and " +        //���،���ЃR�[�h=����.�،���ЃR�[�h
                "srv_div = ? and ";                  //���T�[�r�X�敪=����.�T�[�r�X�敪
        l_lis.add(new String(l_strInstitutionCode));
        l_lis.add(new String(l_strSrvDiv));

        //�����X�R�[�h=����.���X�R�[�h ---------�i�������A����.���X�R�[�h��null�ł͖����ꍇ�Ɍ���j
        if (l_strBranchCodes != null)
        {
            if (l_strBranchCodes.length == 1)
            {
                log.debug("���X�R�[�h=����.���X�R�[�h");
                l_strWhere = l_strWhere + "branch_code = ? and ";
                l_lis.add(l_strBranchCodes[0]);
            }
            else
            {
                log.debug("���X�R�[�h in ����.���X�R�[�h");
                l_strWhere = l_strWhere + "branch_code in ( ";
                int l_int = l_strBranchCodes.length;
                for (int i = 0; i < l_int; i++)
                {
                    l_strWhere = l_strWhere + " ?";
                    l_lis.add(l_strBranchCodes[i]);

                    if (i != l_int - 1)
                    {
                        l_strWhere = l_strWhere + ", ";
                    }
                }
                l_strWhere = l_strWhere + " ) and ";
            }
        }

        //�������R�[�h=����.�����R�[�h ---------�i�������A����.�����R�[�h��null�ł͖����ꍇ�Ɍ���j
        if (l_strAccountCode != null)
        {
            log.debug("�����R�[�h=����.�����R�[�h");
            l_strWhere = l_strWhere + "account_code = ? and ";
            l_lis.add(l_strAccountCode);
        }
        //���o�^�敪=����.�o�^�敪 ----------�i�������A����.�o�^�敪��null�ł͖����ꍇ�Ɍ���j
        if (l_strPaymentDiv != null && !WEB3SrvRegiRigistDivDef.EVERYTHING.equals(l_strPaymentDiv))
        {
            log.debug("�o�^�敪=����.�o�^�敪");
            l_strWhere = l_strWhere + "payment_div = ? and ";
            l_lis.add(l_strPaymentDiv);
        }
        //���K�p�J�n��>=����.�K�p�J�n���i���j -�i�������A����.�K�p�J�n���i���j��null�ł͖����ꍇ�Ɍ���j
        if (l_tsAppliStartDateFrom != null)
        {
            log.debug("�K�p�J�n��>=����.�K�p�J�n���i���j");
            l_strWhere = l_strWhere + "appli_start_date >= ? and ";
            l_lis.add(l_tsAppliStartDateFrom);
        }
        //���K�p�J�n��<=����.�K�p�J�n���i���j -�i�������A����.�K�p�J�n���i���j��null�ł͖����ꍇ�Ɍ���j
        if (l_tsAppliStartDateTo != null)
        {
            log.debug("�K�p�J�n��<=����.�K�p�J�n���i���j");
            l_strWhere = l_strWhere + "appli_start_date <= ? and ";
            l_lis.add(l_tsAppliStartDateTo);
        }
        //���\����>=����.�\�����i���j ---------�i�������A����.�\�����i���j��null�ł͖����ꍇ�Ɍ���j
        if (l_tsAppliDateFrom != null)
        {
            log.debug("�\����>=����.�\�����i���j");
            l_strWhere = l_strWhere + "appli_date >= ? and ";
            l_lis.add(l_tsAppliDateFrom);
        }
        //���\����<=����.�\�����i���j ---------�i�������A����.�\�����i���j��null�ł͖����ꍇ�Ɍ���j
        if (l_tsAppliDateTo != null)
        {
            log.debug("�\����<=����.�\�����i���j");
            l_strWhere = l_strWhere + "appli_date <= ? and ";
            l_lis.add(l_tsAppliDateTo);
        }
        //���\�����I�敪�E����敪�E�������I�����������(*1)
        if (WEB3SrvRegiAppliLotDivDef.CANCEL.equals(l_strAppliLotDiv))
        {
            log.debug("���");
            l_strWhere = l_strWhere + "cancel_div = ? and ";
            l_lis.add(WEB3SrvRegiCancelDivDef.CANCEL);
        }
        else if (WEB3SrvRegiAppliLotDivDef.EVERYTHING.equals(l_strAppliLotDiv))
        {
            log.debug("�\�����I�敪=�S�Ă̏ꍇ");
            l_strWhere = l_strWhere + "";
        }
        else if (WEB3AppliLotDivDef.APPLI.equals(l_strAppliLotDiv))
        {
            log.debug("�\��");
            l_strWhere = l_strWhere + "((appli_lot_div = ?) or " +
                "(appli_lot_div = ? and " +
                "cancel_limit_date >= ?)) and " +
                "cancel_div = ? and ";
            l_lis.add(l_strAppliLotDiv);
            l_lis.add(WEB3AppliLotDivDef.AUTO_ELECTION);
            l_lis.add(l_datSystemDate);
            l_lis.add(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);
        }
        else if (WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_strAppliLotDiv))
        {
            log.debug("���I�^�{�\��");
            l_strWhere = l_strWhere + "((appli_lot_div = ?) or " +
                "(appli_lot_div = ? and " +
                "cancel_limit_date < ?)) and " +
                "cancel_div = ? and ";
            l_lis.add(l_strAppliLotDiv);
            l_lis.add(WEB3AppliLotDivDef.AUTO_ELECTION);
            l_lis.add(l_datSystemDate);
            l_lis.add(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);
        }
        else
        {
            log.debug("else");
            l_strWhere = l_strWhere + "appli_lot_div = ? and " +
                "cancel_div = ? and " +
                "cancel_limit_date is null and ";
            l_lis.add(l_strAppliLotDiv);
            l_lis.add(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);
        }
        //���K�p�I���������ݓ���(*2)�̓��t����
        l_strWhere = l_strWhere + "appli_end_date >= ? and ";
        l_lis.add(l_datSystemDate);
        //���L���敪��"�L��"
        l_strWhere = l_strWhere + "effective_div = ?";
        l_lis.add(new String(WEB3EffectiveDivDef.EFFECTIVE));

        Object[] l_bindVars = new Object[l_lis.size()];
        l_lis.toArray(l_bindVars);
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException

            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                SrvRegiApplicationRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_bindVars);

            List l_lisSrvRegiApplicationParams = new ArrayList();
            int l_intSize = l_lisRows.size();

            for(int i= 0; i < l_intSize; i++)
            {
                log.debug("i = " + i);
                SrvRegiApplicationParams l_applicationParams =
                    new SrvRegiApplicationParams((SrvRegiApplicationRow)l_lisRows.get(i));
                l_lisSrvRegiApplicationParams.add(l_applicationParams);
            }

            SrvRegiApplicationParams[] l_srvRegiApplicationParams = new SrvRegiApplicationParams[l_intSize];

            l_lisSrvRegiApplicationParams.toArray(l_srvRegiApplicationParams);

            log.exiting(STR_METHOD_NAME);
            return l_srvRegiApplicationParams;
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }

    /**
     * (get�T�[�r�X�\���o�^�ꗗ)<BR>
     * ���ݗL���ȃT�[�r�X�\���o�^���擾���A�ԋp����B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X�\���o�^�e�[�u���v����������B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@���X�R�[�h������.���X�R�[�h and<BR>
     * �@@�T�[�r�X�敪������.�T�[�r�X�敪 and<BR>
     * �@@�����R�[�h������.�����R�[�h and<BR>
     * �@@�K�p�I���������ݓ���(*1)�̓��t���� and(*2)<BR>
     * �@@�L���敪��"�L��" and<BR>
     * �@@����敪��"�ʏ�"<BR>
     * [���я�]<BR>
     * �@@�K�p�J�n��(*2)<BR>
     *   �K�p�I����(*3)<BR>
     * (*2)�K�p�I�����敪��true�̏ꍇ�ɏ����ɉ�����B<BR>
     * (*3)�K�p�I�����敪��false�̏ꍇ�ɏ����ɉ�����B<BR>
     * <BR>
     * 2) �߂�l�̐���<BR>
     *  2-1) �������ʂ�0���̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     *  2-2) �������ʁ�0���̏ꍇ�A�������ʂ�ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@param l_blnIsAppliEndDateDiv - (�K�p�I�����敪)<BR> 
     * @@return webbroker3.gentrade.data.SrvRegiApplicationParams[ ]
     * @@roseuid 41130761012E
     */
    public SrvRegiApplicationParams[] getServiceRegistLists(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, boolean l_blnIsAppliEndDateDiv) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getServiceRegistLists(String, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        //1) �ȉ��̏����Łu�T�[�r�X�\���o�^�e�[�u���v����������B
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            
			//��Q�Ή� NO_2049
			List l_lisRows = null;
			
			//�K�p�I���������������Ɋ܂߂�ꍇ            
            if(l_blnIsAppliEndDateDiv){            
	            String l_strWhere =
	                    "institution_code = ? and " +        //�،���ЃR�[�h=����.�،���ЃR�[�h and
	                    "branch_code = ? and " +             //���X�R�[�h=����.���X�R�[�h and
	                    "srv_div = ? and " +                 //�T�[�r�X�敪=����.�T�[�r�X�敪 and
	                    "account_code = ? and " +            //�����R�[�h=����.�����R�[�h and
	                    "appli_end_date >= ? and " +         //�K�p�I���������ݓ���(*1)�̓��t���� and
	                    "effective_div = ? and " +           //�L���敪��"�L��" and
	                    "cancel_div = ? ";                   //����敪��"�ʏ�"
	
				//��Q�Ή� NO_U01711
	            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
				Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);
	
	            Object l_bindVars[] = 
	            	{	l_strInstitutionCode,
	                    l_strBranchCode,
	                    l_strSrvDiv,
	                    l_strAccountCode,
						l_datSystemDate,
	                    WEB3EffectiveDivDef.EFFECTIVE,
	                    WEB3SrvRegiCancelDivDef.USUAL_DEFAULT};
	                    
				//�\�[�g�L�[�i�K�p�J�n���j
	            String l_strOrderBy = " appli_start_date asc";
	            
				l_lisRows =
					l_queryProcesser.doFindAllQuery(
					SrvRegiApplicationRow.TYPE,
					l_strWhere,
					l_strOrderBy,
					null,
					l_bindVars);
            }
            else
			//�K�p�I���������������Ɋ܂߂Ȃ��ꍇ
            {
				String l_strWhere =
						"institution_code = ? and " +        //�،���ЃR�[�h=����.�،���ЃR�[�h and
						"branch_code = ? and " +             //���X�R�[�h=����.���X�R�[�h and
						"srv_div = ? and " +                 //�T�[�r�X�敪=����.�T�[�r�X�敪 and
						"account_code = ? and " +            //�����R�[�h=����.�����R�[�h and
						"effective_div = ? and " +           //�L���敪��"�L��" and
						"cancel_div = ? ";                   //����敪��"�ʏ�"
	
				Object l_bindVars[] =
					{   l_strInstitutionCode,
						l_strBranchCode,
						l_strSrvDiv,
						l_strAccountCode,
						WEB3EffectiveDivDef.EFFECTIVE,
						WEB3SrvRegiCancelDivDef.USUAL_DEFAULT};
	
				//�\�[�g�L�[�i�K�p�I�����j
				String l_strOrderBy = " appli_end_date desc";
				
				l_lisRows =
					l_queryProcesser.doFindAllQuery(
					SrvRegiApplicationRow.TYPE,
					l_strWhere,
					l_strOrderBy,
					null,
					l_bindVars);
            }

            //��������=0���̏ꍇ
            if (l_lisRows.size() == 0)
            {
                log.debug("��������=0���̏ꍇ");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            //�������ʁ�0���̏ꍇ
            else
            {
                log.debug("�������ʁ�0���̏ꍇ");
                SrvRegiApplicationParams[] l_srvRegiApplicationParams = null;
                List l_lisSrvRegiApplicationParams = new ArrayList();

                int l_intSize = l_lisRows.size();
                l_srvRegiApplicationParams = new SrvRegiApplicationParams[l_intSize];
                for (int i = 0; i < l_intSize; i++)
                {
                    log.debug("i = " + i);
                    SrvRegiApplicationParams l_applicationParams =
                        new SrvRegiApplicationParams((SrvRegiApplicationRow)l_lisRows.get(i));
                    l_lisSrvRegiApplicationParams.add(l_applicationParams);
                }

                l_lisSrvRegiApplicationParams.toArray(l_srvRegiApplicationParams);

                log.exiting(STR_METHOD_NAME);
                return l_srvRegiApplicationParams;
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }

    /**
     * (is���p�\)<BR>
     * ���Y�T�[�r�X�����p�\���ǂ������肷��B<BR>
     * <BR>
     * 1) this.get�T�[�r�X�\���o�^( )���R�[�����A�T�[�r�X�\���o�^�I�u�W�F�N�g���擾����B<BR>
     * [get�T�[�r�X�\���o�^( )�̈���]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h=����.���X�R�[�h<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * �@@�����R�[�h=����.�����R�[�h<BR>
     * �@@�\���o�^ID=����.�\���o�^ID<BR>
     * �@@is�s���b�N=false<BR>
     * <BR>
     * 2) �ȉ��̏����ɍ��v����ꍇ�Atrue��ԋp����B<BR>
     * �@@���v���Ȃ��ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * ���擾�����T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�J�n��( )�̓��t����<BR>
     *   �����ݓ��t(*1)�̓��t�����A�����ݓ��t�̓��t����<BR>
     *   ���擾�����T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�I����( )�̓��t�����ł���<BR>
     * <BR>
     * ���T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^�[( )���R�[�����A�T�[�r�X�}�X�^�[�I�u�W�F�N�g��<BR>
     * �@@�擾����B<BR>
     * [get�T�[�r�X�}�X�^�[( )�̈���]<BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( )<BR>
     * �@@�T�[�r�X�敪=this.get�T�[�r�X�敪( )<BR>
     * �@@�|�擾�����T�[�r�X�}�X�^�[�I�u�W�F�N�g.is�񋟒�( )=true�ł���B
     * <BR>
     * ���擾�����T�[�r�X�\���o�^�I�u�W�F�N�g.get�\�����I�敪( )�̒l��<BR>
     * �@@�ȉ��̂����ꂩ�ł���B<BR>
     * �@@�@@���p�^���I�i�{�\���j�^�������I<BR>
     * <BR>
     * (*1) GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@param l_lngRegistId - (�\���o�^ID)<BR>
     * @@return boolean
     * @@roseuid 416B72A802EB
     */
    public boolean isUsePossible(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, long l_lngRegistId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isUsePossible(String, String, String, String, long)";
        log.entering(STR_METHOD_NAME);

        //this.get�T�[�r�X�\���o�^( )���R�[�����A�T�[�r�X�\���o�^�I�u�W�F�N�g���擾����B
        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
            this.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode, l_lngRegistId, false);

        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        //2)
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);
		
		//��Q�Ή�  NO_U01724
		if (WEB3DateUtility.compareToDay(l_gentradeSrvRegiApplication.getAppliStartDate(), l_tsSystemTimestamp) <= 0
            && WEB3DateUtility.compareToDay(l_tsSystemTimestamp, l_gentradeSrvRegiApplication.getAppliEndDate()) <= 0
            && l_srvRegiServiceMaster.isProviding()
            && (WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv())
            || WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv())
            || WEB3AppliLotDivDef.AUTO_ELECTION.equals(l_gentradeSrvRegiApplication.getAppliLotDiv())))
        {
            log.debug("return true");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("return false");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is����\)<BR>
     * ���Y�̃T�[�r�X�\���o�^������\�Ȃ��̂��ǂ����𔻒肷��B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jis����\�v�Q��<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@param l_lngRegistId - (�\���o�^ID)<BR>
     * @@return boolean
     * @@roseuid 416B72A802FA
     */
    public boolean isCancelPossible(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, long l_lngRegistId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isCancelPossible(String, String, String, String, long)";
        log.entering(STR_METHOD_NAME);

        //1.1 <���ݓ����̎擾>
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();

        //1.2 get�T�[�r�X�\���o�^
        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
            this.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode, l_lngRegistId, false);

        //1.3 get����敪
        String l_strCancelDiv = l_gentradeSrvRegiApplication.getCancelDiv();

        //1.4 <����敪�`�F�b�N>
        if (WEB3SrvRegiCancelDivDef.CANCEL.equals(l_strCancelDiv))
        {
            log.debug("����敪�`�F�b�N");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //1.5 get�\�����I�敪
        String l_strAppliLotDiv = l_gentradeSrvRegiApplication.getAppliLotDiv();

        //1.6  <�\�����I�敪�`�F�b�N>
        if (WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_strAppliLotDiv)
         || WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_strAppliLotDiv)
         || WEB3AppliLotDivDef.DEFEAT.equals(l_strAppliLotDiv))
        {
            log.debug("�\�����I�敪�`�F�b�N");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //1.7 <get�\�����I�敪( )="�\��"�̏ꍇ>
        if (WEB3AppliLotDivDef.APPLI.equals(l_strAppliLotDiv))
        {
            log.debug("get�\�����I�敪( )=�\���̏ꍇ");
            //1.7.1 get�T�[�r�X�}�X�^�[
            WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
//            WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
//                l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

            //1.7.2 get�\����
            Timestamp l_tsAppliDate = l_gentradeSrvRegiApplication.getAppliDate();

            //1.7.3 get�T�[�r�X���I���
            WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_strSrvDiv, l_tsAppliDate, 0);

            //1.7.4 get�\�����ԁi���j
            //Timestamp l_tsAppliDateFrom = l_srvRegiServiceLotInfo.getAppliDateFrom();

            //1.7.5 get�\�����ԁi���j
            Timestamp l_tsAppliDateTo = l_srvRegiServiceLotInfo.getAppliDateTo();

            //1.7.6 <�\�����Ԃ̃`�F�b�N>
            if (WEB3DateUtility.compareToSecond(l_tsSystemTimestamp, l_tsAppliDateTo) > 0)
            {
                log.debug("�\�����Ԃ̃`�F�b�N");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        //1.8 <get�\�����I�敪( )="�������I"�̏ꍇ>
        if (WEB3AppliLotDivDef.AUTO_ELECTION.equals(l_strAppliLotDiv))
        {
            log.debug("get�\�����I�敪( )=�������I�̏ꍇ");
            //1.8.1 get�������I���������
            Timestamp l_tsCancelLimitDate = l_gentradeSrvRegiApplication.getCancelLimitDate();
			
			//��Q�Ή�  NO_U01712
            //1.8.2 <�������I����������̃`�F�b�N>
            if (WEB3DateUtility.compareToDay(l_tsCancelLimitDate, l_tsSystemTimestamp) < 0)
            {
                log.debug("�������I����������̃`�F�b�N");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        //1.9 ���b�Z�[�W true
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate����]��)<BR>
     * ����]�͎c�����\�����邩�𔻒肷��B <BR>
     *<BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jvalidate����]�́v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�jvalidate����]�́v): <BR>
     * 1.10<is����t���O( )=false�̏ꍇ�A�]�͎c���G���[�Ƃ��ė�O���X���[����  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01187<BR>
     * ==========================================================<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * @@param l_dblUseAmt - (���p����)<BR>
     * @@param l_tsPaymentDate - (�o����)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strOrderChannel - (�����`���l��)<BR>
     * @@return boolean
     * @@roseuid 416B72A802FA
     */
    public void validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        Trader l_trader,
        double l_dblUseAmt,
        Timestamp l_tsPaymentDate,
        String l_strSrvDiv,
        String l_strOrderChannel)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateTradedPower(WEB3GentradeSubAccount, Trader, double, Timestamp, String, String, String)";
        log.entering(STR_METHOD_NAME);
        //1.1 �g�����o�������}�l�[�W���擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();

        //1.2 get���iID
        Institution l_institution = l_subAccount.getInstitution();
        long l_lngProductId = l_aioOrderManager.getProductId(l_institution);

        //1.3 ���o���������e
        WEB3AioNewOrderSpec l_aioNewOrderSpec =
            new WEB3AioNewOrderSpec(l_trader, OrderTypeEnum.CASH_OUT, AssetTransferTypeEnum.CASH_OUT, l_lngProductId,
            l_dblUseAmt, null, l_tsPaymentDate, null, null);

        //1.4 �T�[�r�X���p�V�K�����X�V�C���^�Z�v�^
        WEB3SrvRegiNewOrderUpdateInterceptor l_srvRegiNewOrderUpdateInterceptor = new WEB3SrvRegiNewOrderUpdateInterceptor();

        //1.5 set�T�[�r�X�敪
        l_srvRegiNewOrderUpdateInterceptor.setSrvDiv(l_strSrvDiv);

        //1.6set��n��
        l_srvRegiNewOrderUpdateInterceptor.setDeliveryDate(l_tsPaymentDate);

        //1.7 set�����o�H�敪
        if (l_strOrderChannel == null)
        {
            l_srvRegiNewOrderUpdateInterceptor.setOrderRootDiv(WEB3OrderRootDivDef.ADMIN);
        }
        else if (WEB3ChannelDef.CALL_CENTER.equals(l_strOrderChannel))
        {
            l_srvRegiNewOrderUpdateInterceptor.setOrderRootDiv(WEB3OrderRootDivDef.CALLCENTER);
        }
        else
        {
            l_srvRegiNewOrderUpdateInterceptor.setOrderRootDiv(WEB3OrderRootDivDef.PC);
        }

        //1.8:�c�Ɠ��v�Z(Timestamp)
        WEB3GentradeBizDate l_datBizDate = new WEB3GentradeBizDate(l_tsPaymentDate);

        //1.9:roll(int)
        Timestamp l_ts = l_datBizDate.roll(-1);

        //1.10 set������
        l_srvRegiNewOrderUpdateInterceptor.setOrderBizDate(l_ts);

        //1.11 validate����]��
        Object[] l_obj1 = new Object[1];
        Object[] l_obj2 = new Object[1];
        l_obj1[0] = l_srvRegiNewOrderUpdateInterceptor;
        l_obj2[0] = l_aioNewOrderSpec;
        WEB3TPTradingPowerService l_web3TPTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        WEB3TPTradingPowerResult l_web3TPTradingPowerResult =
            l_web3TPTradingPowerService.validateTradingPower(l_subAccount, l_obj1, l_obj2, OrderTypeEnum.CASH_OUT, false);

        //1.12is����t���O
        boolean l_blnDecisionFlag = l_web3TPTradingPowerResult.isResultFlg();

        //1.13<is����t���O( )=false�̏ꍇ�A�]�͎c���G���[�Ƃ��ė�O���X���[����B>
        if (!l_blnDecisionFlag)
        {
            log.debug("�]�͎c���G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01187,
                getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�����\���敪)<BR>
     *  �����ɂĎw�肳�ꂽ�T�[�r�X�A�ڋq�̑g�ݍ��킹�ŉߋ��P�x�ł��\��������������<BR>
     * ���肵�A���茋��(*)��ԋp����B <BR>
     * <BR>
     * 1) �ȉ��̏����Łu�\�������Ǘ��e�[�u���v�e�[�u������������B<BR>
     * [��������] <BR>
�@@   *  �،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
�@@   *  ���X�R�[�h=����.���X�R�[�h and <BR>
�@@   *  �T�[�r�X�敪=����.�T�[�r�X�敪 and <BR>
�@@   *  �����R�[�h=����.�����R�[�h <BR>
     * <BR>
     * 2) ���茋�ʂ̕ԋp�B<BR>
     * 2-1) 1)�̌�������=0���̏ꍇ�A"��"��ԋp����B<BR>
     * 2-2) 1)�̌�������>0���̏ꍇ�A"�L"��ԋp����B <BR>
     * <BR>
     *  (*) [�ԋp�l���e]<BR>
     *  �i�T�[�r�X���p�ڋq�T�[�r�X���ꗗ���ʏ��.�����\���敪�A<BR>
     *  �T�[�r�X���p�Ǘ��҃A�b�v���[�h�m�F���X�|���X.�����\���敪�̃R�[�h��`�Ɠ���<BR>
     *  �������Anull�͂��肦�Ȃ��B�j <BR>
     *  0:�� <BR>
     *  1:�L<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@return String<BR>
     * @@roseuid 416B72A802FA
     */
    public String getInitializeAppliDiv(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInitializeAppliDiv(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

            String l_strWhere =
                    "institution_code = ? and " +
                    "branch_code = ? and " +
                    "srv_div = ? and " +
                    "account_code = ? ";

            Object l_bindVars[] =
                {   l_strInstitutionCode,
                    l_strBranchCode,
                    l_strSrvDiv,
                    l_strAccountCode};

            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    SrvRegiHistoryRow.TYPE,
                    l_strWhere,
                    l_bindVars);

            int l_intSize = l_lisRows.size();
            if (l_intSize == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3ConditionsValueDivDef.HAVE_NOT;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3ConditionsValueDivDef.HAVE;
            }

        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

    }
    
	/**
	 * (get�T�[�r�X�\���o�^����Ώ�)<BR>
	 * ����\�ȃT�[�r�X�\���o�^���擾���A
	 * ��������ɃT�[�r�X�\���o�^�I�u�W�F�N�g��ԋp����B<BR>
	 * <BR>
	 * 1) �ȉ��̏����Łu�T�[�r�X�\���o�^�e�[�u���v����������B<BR>
	 * �@@�@@�i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B�j
	 * [��������]<BR>
	 * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
	 * �@@���X�R�[�h������.���X�R�[�h and<BR>
	 * �@@�T�[�r�X�敪������.�T�[�r�X�敪 and<BR>
	 * �@@�����R�[�h������.�����R�[�h and<BR>
	 * 	 �K�p�J�n�������ݓ���(*1)�̓��t���� and<BR>
	 * �@@�K�p�I���������ݓ���(*1)�̓��t���� and<BR>
	 * �@@�L���敪��"�L��" and<BR>
	 * �@@����敪��"�ʏ�"<BR>
	 * <BR>
	 * 1)��� = GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
	 * <BR>
	 * 2) �߂�l�̐���<BR>
	 *  2-1) �������ʂ�0���̏ꍇ�Anull��ԋp����B<BR>
	 * <BR>
	 *  2-2) �������ʂł���T�[�r�X�\���o�^Params�I�u�W�F�N�g�������ɁA
	 *       �T�[�r�X�\���o�^�N���X�̃R���X�g���N�^���R�[������B
	 * <BR>
	 *  2-3) �������ʁ�1���̏ꍇ
	 *       �������ʂ̈�Ԑ擪�̃��R�[�h���T�[�r�X�\���o�^Params�I�u�W�F�N�g�������ɁA
	 *       �T�[�r�X�\���o�^�N���X�̃R���X�g���N�^���R�[������B<BR>
	 * <BR>
	 * 3) ���������T�[�r�X�\���o�^�I�u�W�F�N�g��ԋp����B
	 * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
	 * @@param l_strBranchCode - (���X�R�[�h)<BR>
	 * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
	 * @@param l_strAccountCode - (�����R�[�h)<BR>
	 * @@param l_blnIsRowLock - (is�s���b�N)<BR>
	 * true : �s���b�N���s��   false : �s���b�N���s��Ȃ�<BR>
	 * @@return WEB3GentradeSrvRegiApplication
	 */
	public WEB3GentradeSrvRegiApplication getServiceRegistCancelUnit(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, boolean l_blnIsRowLock)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getServiceRegistLists(String, String, String, String, boolean)";
		log.entering(STR_METHOD_NAME);

		//1) �ȉ��̏����Łu�T�[�r�X�\���o�^�e�[�u���v����������B
		try
		{
			QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
			String l_strWhere =
					"institution_code = ? and " +        //�،���ЃR�[�h=����.�،���ЃR�[�h and
					"branch_code = ? and " +             //���X�R�[�h=����.���X�R�[�h and
					"srv_div = ? and " +                 //�T�[�r�X�敪=����.�T�[�r�X�敪 and
					"account_code = ? and " +            //�����R�[�h=����.�����R�[�h and
					"appli_start_date >= ? and " +       //�K�p�J�n�������ݓ���(*1)�̓��t���� and
					"appli_end_date >= ? and " +         //�K�p�I���������ݓ���(*1)�̓��t���� and
					"effective_div = ? and " +           //�L���敪��"�L��" and
					"cancel_div = ? ";                   //����敪��"�ʏ�"

			//��Q�Ή�  NO_U01711
			Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
			Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);
			
			List l_lisRows = null;

			Object l_bindVars[] =
				{   l_strInstitutionCode,
					l_strBranchCode,
					l_strSrvDiv,
					l_strAccountCode,
					WEB3DateUtility.toDay(l_datSystemDate),
					WEB3DateUtility.toDay(l_datSystemDate),
					WEB3EffectiveDivDef.EFFECTIVE,
					WEB3SrvRegiCancelDivDef.USUAL_DEFAULT};
			
			if (l_blnIsRowLock)
			{
				l_lisRows =
					l_queryProcesser.doFindAllQuery(
					SrvRegiApplicationRow.TYPE,
					l_strWhere,
					" FOR UPDATE ",
					l_bindVars);
			}
			else
			{
				l_lisRows =
					l_queryProcesser.doFindAllQuery(
					SrvRegiApplicationRow.TYPE,
					l_strWhere,
					null,
					l_bindVars);
			}
			
			WEB3GentradeSrvRegiApplication l_WEB3GentradeSrvRegiApplication = null;
			SrvRegiApplicationParams l_SrvRegiApplicationParams = null;
			
			//��������=0���̏ꍇ
			if (l_lisRows.size() == 0)
			{
				return null;
			}
			//��������=1���̏ꍇ
			else if (l_lisRows.size() == 1)
			{
				l_SrvRegiApplicationParams = (SrvRegiApplicationParams)l_lisRows.get(0);
				l_WEB3GentradeSrvRegiApplication =
					new WEB3GentradeSrvRegiApplication(l_SrvRegiApplicationParams);
			}
			//�������ʁ�1���̏ꍇ
			else
			{
				l_SrvRegiApplicationParams = (SrvRegiApplicationParams)l_lisRows.get(0);
				l_WEB3GentradeSrvRegiApplication =
					new WEB3GentradeSrvRegiApplication(l_SrvRegiApplicationParams);
			}
			//For Update�̏ꍇ
			if (l_blnIsRowLock)
			{
				l_WEB3GentradeSrvRegiApplication.createForUpdateParams();
			}

			log.exiting(STR_METHOD_NAME);
			return l_WEB3GentradeSrvRegiApplication;
		}
		catch (DataNetworkException l_ex)
		{
			log.error(l_ex.getMessage(), l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				getClass().getName() + STR_METHOD_NAME,
				l_ex.toString(),
				l_ex);
		}
		catch (DataQueryException l_ex)
		{
			log.error(l_ex.getMessage(), l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				getClass().getName() + STR_METHOD_NAME,
				l_ex.toString(),
				l_ex);
		}
	}

    /**
     * (get����p�X���[�h)
     * ����p�X���[�h���擾����B
     * 
     * OpLoginSecurityService���A���O�C���^�C�v�������擾����B
     * �|���O�C���^�C�v����.������ == ����p�X���[�h�ݒ�i�FTRADING_PWD_ENV�j�̑����l�� 
     *   �h0�FDEFAULT�i����p�X���[�h���ڂ��g�p���Ȃ��j�h�̏ꍇ�A����.�Ïؔԍ���ԋp���� �B
     * �|���O�C���^�C�v����.������ == ����p�X���[�h�ݒ�i�FTRADING_PWD_ENV�j�̑����l�� 
     * �@@�h1�F����p�X���[�h�g�p�h�̏ꍇ�A����.�⏕�������ڋq�I�u�W�F�N�g.����p�X���[�h���擾���A 
     *�@@�ԋp����B��
     *
     * ���ڋq.getTradingPassword()�̖߂�l��WEB3Crypt.decrypt()�ŕ����������� 
     * 
     * @@param l_subAccout (�⏕�����j
     * @@param l_strPassword (�Ïؔԍ��j
     * @@return String
     * 
     * @@author sra518
     */
    public String getTradingPassword (SubAccount l_subAccount, String l_strPassword)
    {
        final String STR_METHOD_NAME = " getPassword(SubAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        //OpLoginSecurityService���A���O�C���^�C�v�������擾����      
        //�T�[�r�X���擾
        OpLoginSecurityService l_securityService = (OpLoginSecurityService)
            Services.getService(OpLoginSecurityService.class);
        OpLoginAdminService l_admService = (OpLoginAdminService) Services.getService(
            OpLoginAdminService.class);
        
        //LoginInfo->LoginType->LoginTypeAttribute 
        LoginInfo l_loginInfo = l_securityService.getLoginInfo();
        Map l_mapAttributes = l_admService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
        
        //���O�C���^�C�v����.����p�X���[�h�ݒ���擾����
        String l_strAttribute =
            (String) l_mapAttributes.get(
                WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);
                
        String l_strResultPassword = null;
        //����p�X���[�h�ݒ聁�h0�FDEFAULT�i����p�X���[�h���ڂ��g�p���Ȃ��j�h�̏ꍇ�A
        //����.�Ïؔԍ���ԋp���� �B
        if (WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
        {
            l_strResultPassword = l_strPassword;
        }
        //����p�X���[�h�ݒ聁�h1�F����p�X���[�h�g�p�h�̏ꍇ�A
        //����.�⏕�������ڋq�I�u�W�F�N�g.����p�X���[�h��ԋp����B
        else if(WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
        {
            WEB3Crypt l_web3Crypt = new WEB3Crypt();
            l_strResultPassword = l_web3Crypt.decrypt(l_subAccount.getMainAccount().getTradingPassword());
        }
        
        log.debug("l_strResultPassword = " + l_strResultPassword);
        return l_strResultPassword;
    }

    /**
     * ���͂������t�Ɏw�肵���������v���X���A�ԋp���܂��B
     *
     * @@param l_dat      ���t
     * @@param l_intMonth ����
     * @@param l_strDiv   �敪
     * @@return �v�Z��̌��ʂ�ԋp����B
     */
    private Date addMonth(Date l_dat, int l_intMonth, String l_strDiv)
    {
        final String STR_METHOD_NAME = " addMonth(Date, int, String)";
        log.entering(STR_METHOD_NAME);

        if (l_dat == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        if ("A".equals(l_strDiv))
        {
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_dat);

            l_cal.add(Calendar.MONTH, l_intMonth);

            log.exiting(STR_METHOD_NAME);
            return l_cal.getTime();
        }
        else
        {
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_dat);

            l_cal.add(Calendar.DATE, -1);
            l_cal.add(Calendar.MONTH, l_intMonth);

            log.exiting(STR_METHOD_NAME);
            return l_cal.getTime();
        }

    }

    /**
     * ���͂������t�Ɏw�肵���N�����v���X���A�ԋp���܂��B
     *
     * @@param l_dat      ���t
     * @@param l_intYear �N��
     * @@param l_strDiv   �敪
     * @@return �v�Z��̌��ʂ�ԋp����B
     */
    private Date addYear(Date l_dat, int l_intYear, String l_strDiv)
    {
        final String STR_METHOD_NAME = " addYear(Date, int, String)";
        log.entering(STR_METHOD_NAME);

        if (l_dat == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        if ("A".equals(l_strDiv))
        {
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_dat);

            l_cal.add(Calendar.YEAR, l_intYear);

            log.exiting(STR_METHOD_NAME);
            return l_cal.getTime();
        }
        else
        {
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_dat);

            l_cal.add(Calendar.DATE, -1);

            l_cal.add(Calendar.YEAR, l_intYear);

            log.exiting(STR_METHOD_NAME);
            return l_cal.getTime();
        }

    }

    /**
     * (get�T�[�r�X�\�������ꗗ)<BR>
     * �w�肳�ꂽ�����ɍ��v����T�[�r�X�\�������ꗗ���������A<BR> 
     * ���̌��ʂ��T�[�r�X�\���o�^Params�I�u�W�F�N�g�̔z��ɂ��ĕԋp����B<BR> 
     * <BR>
     * 1) �\�[�g�����̍쐬<BR>
     * �@@����.�\�[�g������null�ȊO�ł���A���v�f����0�̏ꍇ�A<BR>
     * �@@����.�\�[�g�����̌������A�ȉ����J��Ԃ��B<BR>
     *  1-1) �Ή�����e�[�u���̗񕨗����������^�~���w���t�����Z�b�g����B<BR>
     * <BR>
     * �@@�@@���L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�B<BR>
     * �@@�@@�@@���L�[���ڕ�����(�V���{����)�́A���b�Z�[�W��`�����Q�ƁB<BR>
     * �@@�@@�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q�ƁB<BR>
     * <BR>
     * �@@�@@�@@�@@�E���X     =�T�[�r�X�\�������e�[�u��.���X�R�[�h<BR>
     * �@@�@@�@@�@@�E�ڋq     =�T�[�r�X�\�������e�[�u��.�����R�[�h<BR>
     * �@@�@@�@@�@@�E�\������   =�T�[�r�X�\�������e�[�u��.�\������<BR>
     * �@@�@@�@@�@@�E�K�p�J�n�� =�T�[�r�X�\�������e�[�u��.�K�p����From<BR>
     * �@@�@@�@@�@@�E�K�p�I���� =�T�[�r�X�\�������e�[�u��.�K�p����To<BR>
     * �@@�@@�@@�@@�E�����敪   =�T�[�r�X�\�������e�[�u��.�����敪<BR>
     * �@@�@@�@@�@@�E�ŏI�X�V�� =�T�[�r�X�\�������e�[�u��.�X�V���t<BR>
     * �@@�@@�@@�@@�E�ŏI�X�V�� =�T�[�r�X�\�������e�[�u��.�X�V�҃R�[�h<BR>
     * <BR>
     * �@@�@@�������^�~���w��́A�\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ肷��B<BR>
     * <BR>
     * 2) �ȉ��̌��������ŁA�u�T�[�r�X�\�������e�[�u���v����������B<BR>
     * �@@[��������]<BR>
     * �@@�@@���،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * �@@�@@�����X�R�[�h=����.���X�R�[�h ---------�i�������A����.���X�R�[�h��null�ł͖����ꍇ�Ɍ���j<BR>
     *     ���T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * �@@�@@�������R�[�h=����.�����R�[�h ---------�i�������A����.�����R�[�h��null�ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@���\�������敪= <BR>
     *       ����.�\�����I�敪��'7'�̏ꍇ�A'1'(�����Ώ�)��ݒ�<BR>
     *       ����.�\�����I�敪��'8'�̏ꍇ�A'2'(�\���s��)��ݒ�<BR>
     *       ����.�\�����I�敪��'9'�̏ꍇ�A���̌��������͐ݒ肵�Ȃ�<BR>
     * <BR>
     * �@@�@@���K�p����From�A�K�p����To(����.�K�p����null�ł͂Ȃ��ꍇ�A�ȉ��̌���������ǉ�����B)<BR>
     *     (�K�p����From <= ����.�K�p�� or �K�p����From == null) and (�K�p����To >= ����.�K�p�� 
     *     or �K�p����To == null)<BR>
     * <BR>
     * �@@[���я�]<BR>
     * �@@�@@1)�Ő��������\�[�g����<BR>
     * <BR>
     * 3) 2)�̌������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�i�K�{�j<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�ꗗ<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪�i�K�{�j<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strAppliLotDiv - (�\�����I�敪)<BR>
     * 7:�����Ώہ@@8:�\���s�@@9:�S��<BR>
     * @@param l_tsAppDate - (�K�p��)<BR>
     * �K�p��<BR>
     * @@param l_sortCondition - (�\�[�g����)<BR>
     * �Ώۍ���:<BR> 
     * "���X","�ڋq","�\������","�K�p�J�n��","�K�p�I����","�ŏI�X�V��","�ŏI�X�V��"<BR> 
     * <BR>
     * @@return ���� �T�[�r�X�\������Params[]
     */
    public SrvAppliAttributeParams[] getServiceAttributeLists(String l_strInstitutionCode, String[] l_strBranchCodes,
        String l_strSrvDiv, String l_strAccountCode, String l_strAppliLotDiv, Timestamp l_tsAppDate,
        WEB3SrvRegiSortKey[] l_sortConditions) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getServiceAttributeLists(String, String[], String, String, String, " +
            "Timestamp, WEB3SrvRegiSortKey[])";
        log.entering(STR_METHOD_NAME);

        String l_strWhere = null;
        List l_lisWhere = new ArrayList();
        String l_strOrderBy = "";

        //1) �\�[�g�����̍쐬
        if (l_sortConditions != null && l_sortConditions.length > 0)
        {
            //����.�\�[�g������null�ȊO�ł���A���v�f����0�̏ꍇ�A 
            //����.�\�[�g�����̌������A�ȉ����J��Ԃ��B
            //1-1) �Ή�����e�[�u���̗񕨗����������^�~���w���t�����Z�b�g����B
            int l_intLength = l_sortConditions.length;
            for (int i = 0; i < l_intLength; i++)
            {
                if (WEB3SrvRegiKeyItemDef.BRANCH_CODE.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("branch_code asc");
                        l_strOrderBy = l_strOrderBy + " branch_code asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("branch_code desc");
                        l_strOrderBy = l_strOrderBy + " branch_code desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.ACCOUNT_CODE.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("account_code asc");
                        l_strOrderBy = l_strOrderBy + " account_code asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("account_code desc");
                        l_strOrderBy = l_strOrderBy + " account_code desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_ATTRIBUTE.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("appli_attribute asc");
                        l_strOrderBy = l_strOrderBy + " appli_attribute asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("appli_attribute desc");
                        l_strOrderBy = l_strOrderBy + " appli_attribute desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_START_DATE.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("appli_start_date asc");
                        l_strOrderBy = l_strOrderBy + " appli_start_date asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("appli_start_date desc");
                        l_strOrderBy = l_strOrderBy + " appli_start_date desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_END_DATE.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("appli_end_date asc");
                        l_strOrderBy = l_strOrderBy + " appli_end_date asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("appli_end_date desc");
                        l_strOrderBy = l_strOrderBy + " appli_end_date desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.TRANSACTION_DIV.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("proc_div");
                        l_strOrderBy = l_strOrderBy + " proc_div asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("proc_div desc");
                        l_strOrderBy = l_strOrderBy + " proc_div desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.LAST_UPDATED_TIMESTAMP.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("last_updated_timestamp asc");
                        l_strOrderBy = l_strOrderBy + " last_updated_timestamp asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("last_updated_timestamp desc");
                        l_strOrderBy = l_strOrderBy + " last_updated_timestamp desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.LAST_UPDATER.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("last_updater asc");
                        l_strOrderBy = l_strOrderBy + " last_updater asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("last_updater desc");
                        l_strOrderBy = l_strOrderBy + " last_updater desc";
                    }
                }
                if(i != l_intLength - 1)
                {
                    l_strOrderBy = l_strOrderBy + ", ";
                }
            }
        }

        //2) �ȉ��̌��������ŁA�u�T�[�r�X�\�������e�[�u���v����������B

        //���،���ЃR�[�h=����.�،���ЃR�[�h
        l_strWhere = "institution_code = ? ";
        l_lisWhere.add(new String(l_strInstitutionCode));

        //�����X�R�[�h=����.���X�R�[�h ---------�i�������A����.���X�R�[�h��null�ł͖����ꍇ�Ɍ���j
        if (l_strBranchCodes != null)
        {
            if (l_strBranchCodes.length == 1)
            {
                log.debug("���X�R�[�h=����.���X�R�[�h");
                l_strWhere = l_strWhere + "and branch_code = ? ";
                l_lisWhere.add(l_strBranchCodes[0]);
            }
            else
            {
                log.debug("���X�R�[�h in ����.���X�R�[�h");
                l_strWhere = l_strWhere + "and branch_code in ( ";
                int l_int = l_strBranchCodes.length;
                for (int i = 0; i < l_int; i++)
                {
                    l_strWhere = l_strWhere + " ?";
                    l_lisWhere.add(l_strBranchCodes[i]);

                    if (i != l_int - 1)
                    {
                        l_strWhere = l_strWhere + ", ";
                    }
                }
                l_strWhere = l_strWhere + " ) ";
            }
        }

        //���T�[�r�X�敪=����.�T�[�r�X�敪
        l_strWhere = l_strWhere + "and srv_div = ? ";
        l_lisWhere.add(new String(l_strSrvDiv));

        //�������R�[�h=����.�����R�[�h ---------�i�������A����.�����R�[�h��null�ł͖����ꍇ�Ɍ���j
        if (l_strAccountCode != null)
        {
            log.debug("�����R�[�h=����.�����R�[�h");
            l_strWhere = l_strWhere + "and account_code = ? ";
            l_lisWhere.add(l_strAccountCode);
        }

        //���\�������敪= 
        if (WEB3SrvRegiAppliLotDivDef.FREE_OBJECT.equals(l_strAppliLotDiv))
        {
            //����.�\�����I�敪��'7'�̏ꍇ�A'1'(�����Ώ�)��ݒ�
            log.debug("�\�������敪='1'(�����Ώ�)");
            l_strWhere = l_strWhere + "and appli_attribute = ? ";
            l_lisWhere.add(WEB3AppliAttributeDef.FREE_OBJECT);
        }
        else if (WEB3SrvRegiAppliLotDivDef.CANNOT_APPLI.equals(l_strAppliLotDiv))
        {
            //����.�\�����I�敪��'8'�̏ꍇ�A'2'(�\���s��)��ݒ�
            log.debug("�\�������敪='2'(�\���s��)");
            l_strWhere = l_strWhere + "and appli_attribute = ? ";
            l_lisWhere.add(WEB3AppliAttributeDef.CANNOT_APPLI);
        }

        //���K�p����From�A�K�p����To(����.�K�p����null�ł͂Ȃ��ꍇ�A�ȉ��̌���������ǉ�����B)
        if (l_tsAppDate != null)
        {
            //(�K�p����From <= ����.�K�p�� or �K�p����From == null)
            log.debug("(�K�p����From <= ����.�K�p�� or �K�p����From == null)");
            l_strWhere = l_strWhere + "and ((appli_start_date <= ? or appli_start_date is null) ";
            l_lisWhere.add(l_tsAppDate);

            //and (�K�p����To >= ����.�K�p�� or �K�p����To == null)
            log.debug(" and (�K�p����To >= ����.�K�p�� or �K�p����To == null) ");
            l_strWhere = l_strWhere + "and (appli_end_date >= ? or appli_end_date is null)) ";
            l_lisWhere.add(l_tsAppDate);
        }

        Object[] l_bindVars = new Object[l_lisWhere.size()];
        l_lisWhere.toArray(l_bindVars);
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                SrvAppliAttributeRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_bindVars);

            List l_lisSrvAppliAttributeParams = new ArrayList();
            int l_intSize = l_lisRows.size();
            for(int i= 0; i < l_intSize; i++)
            {
                log.debug("i = " + i);
                SrvAppliAttributeParams l_applicationParams =
                    new SrvAppliAttributeParams((SrvAppliAttributeRow)l_lisRows.get(i));
                l_lisSrvAppliAttributeParams.add(l_applicationParams);
            }

            SrvAppliAttributeParams[] l_srvAppliAttributeParams = new SrvAppliAttributeParams[l_intSize];
            l_lisSrvAppliAttributeParams.toArray(l_srvAppliAttributeParams);

            //2)�̌������ʂ�ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_srvAppliAttributeParams;
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }
}
@
