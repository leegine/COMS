head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoOccupationChangeRegistVoucherCreated.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �E�ƕύX�\���`�[�쐬(WEB3AccInfoOccupationChangeRegistVoucherCreated.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/18 ���G�� (���u) �V�K�쐬 ���f��160 , �c�a�X�V�d�l041
Revision History : 2007/01/23 ���G�� (���u) �c�a�X�V�d�l045
Revision History : 2007/02/10 �Ӑ� (���u) ���f��188
Revision History : 2007/03/05 �g��i (���u) �c�a�X�V�d�l047
*/
package webbroker3.accountinfo;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountopen.data.TradeConditionVoucherDao;
import webbroker3.accountopen.data.TradeConditionVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DataClassDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3RegistDivDef;
import webbroker3.common.define.WEB3ReportDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AccountInfoMstParams;
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�E�ƕύX�\���`�[�쐬)<BR>
 * �E�ƕύX�\���`�[�쐬�N���X<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AccInfoOccupationChangeRegistVoucherCreated
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoOccupationChangeRegistVoucherCreated.class);

    /**
     * (�ڋq�i�S���X���jRow)<BR>
     * �ڋq�i�S���X���jRow�I�u�W�F�N�g<BR>
     */
    private MainAccountAllRow mainAccountAllRow;

    /**
     * (�E�ƕύX�\���`�[�쐬)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3AccInfoOccupationChangeRegistVoucherCreated()
    {

    }

    /**
     * (�E�ƕύX�\���`�[�쐬)<BR>
     * �ڋq�i�S���X���jRow�𑮐��ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_mainAccountAllRow - �ڋq�i�S���X���jRow�I�u�W�F�N�g
     */
    public WEB3AccInfoOccupationChangeRegistVoucherCreated(
        MainAccountAllRow l_mainAccountAllRow)
    {
        final String STR_METHOD_NAME =
            "WEB3AccInfoOccupationChangeRegistVoucherCreated(MainAccountAllRow)";
        log.entering(STR_METHOD_NAME);

        this.mainAccountAllRow = l_mainAccountAllRow;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�E�ƕύX�`�[)<BR>
     * �E�ƕύX�ɔ�����c�E�d�q��t�E��������`�[�iGI844�j���쐬����B<BR>
     * <BR>
     * <BR>
     * ���`�[�쐬���ڂ́u�`�[�쐬_��c�E�d�q��t�E��������`�[�iGI311�j�e�[�u��.xls�v�Q��<BR>
     * <BR>
     * @@param l_lngChangeRegistID - �ύX�\��ID<BR>
     * @@param l_strOccupationCode - �E�ƃR�[�h<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    public void createOccupationChangeVoucher(
        long l_lngChangeRegistID, String l_strOccupationCode , MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createOccupationChangeVoucher(long, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            MainAccountRow l_mainAccountRow = ((WEB3GentradeMainAccount) l_mainAccount).getMainAccountRow();
            
            TradeConditionVoucherParams l_tradeConditionVoucherParams =
                new TradeConditionVoucherParams();
            //���ʃR�[�horder_request_number   VARCHAR29   NotNull �i�̔Ԃ����l�j
            l_tradeConditionVoucherParams.setOrderRequestNumber(
                WEB3StringTypeUtility.formatNumber(TradeConditionVoucherDao.newPkValue()));

            //�f�[�^�R�[�hrequest_code  VARCHAR25   NotNull ��c�E�d�q��t�E��������F�hGI844�h
            l_tradeConditionVoucherParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_TRADE_CONDITION);

            //�،���ЃR�[�hinstitution_code VARCHAR23   NotNull �ڋq�}�X�^�[�s.�،���ЃR�[�h
            l_tradeConditionVoucherParams.setInstitutionCode(l_mainAccountRow.getInstitutionCode());

            //���X�R�[�hbranch_code    VARCHAR23   NotNull �ڋq�}�X�^�[�s.���X�R�[�h
            l_tradeConditionVoucherParams.setBranchCode(l_mainAccountRow.getBranchCode());

            //�ڋq�R�[�haccount_code   VARCHAR27   NotNull �ڋq�}�X�^�[�s.�����R�[�h
            l_tradeConditionVoucherParams.setAccountCode(l_mainAccountRow.getAccountCode());

            //���҃R�[�htrader_code    VARCHAR25   Null    �ڋq�}�X�^�[�s.���҃R�[�h�iSONAR�j
            l_tradeConditionVoucherParams.setTraderCode(
                l_mainAccountRow.getSonarTraderCode());

            //���ʃR�[�h�i�����J�݌����q�jacc_open_request_number   VARCHAR213  NotNull 0
            l_tradeConditionVoucherParams.setAccOpenRequestNumber("0");

            //�`�[�ʔ�serial_no   VARCHAR23   NotNull 0
            l_tradeConditionVoucherParams.setSerialNo("0");

            //�f�|�^���data_class VARCHAR22   Null    02�F�ύX�R�[�h
            l_tradeConditionVoucherParams.setDataClass(WEB3DataClassDef.CHANGE_RECORD);

            //�d�q��t    ����񍐏�   trading_e_report_div  null
            l_tradeConditionVoucherParams.setTradingEReportDiv(null);

            //    ���M�^�p�񍐏� inv_e_report_div  null
            l_tradeConditionVoucherParams.setInvEReportDiv(null);

            //    ���z���E���ҋ� refund_e_report_div VARCHAR21   Null    �O�F�񏳑�
            l_tradeConditionVoucherParams.setRefundEReportDiv(WEB3ReportDivDef.NOT_ACCEPT);

            //    �\��1 e_report_div1   VARCHAR21   Null    null
            l_tradeConditionVoucherParams.setEReportDiv1(null);

            //    �\��2 e_report_div2   VARCHAR21   Null    null
            l_tradeConditionVoucherParams.setEReportDiv2(null);

            //    �\��3 e_report_div3   VARCHAR21   Null    null
            l_tradeConditionVoucherParams.setEReportDiv3(null);

            //��c�񍐏�   ���  pos_report_term_div null
            l_tradeConditionVoucherParams.setPosReportTermDiv(null);

            //    �s�x  pos_report_cycle_div  null
            l_tradeConditionVoucherParams.setPosReportCycleDiv(null);

            //    �a��� pos_report_certif_depo_div  null
            l_tradeConditionVoucherParams.setPosReportCertifDepoDiv(null);

            //    �v�Z�� pos_report_acc_state_div    null
            l_tradeConditionVoucherParams.setPosReportAccStateDiv(null);

            //����������������敪equity_tax_div     null
            l_tradeConditionVoucherParams.setEquityTaxDiv(null);

            //����������������敪�i���N�jequity_tax_div_next  null
            l_tradeConditionVoucherParams.setEquityTaxDivNext(null);

            //����������������J�ݓ�equity_sp_acc_open_dat null
            l_tradeConditionVoucherParams.setEquitySpAccOpenDat(null);

            //�M�p�����������敪margin_tax_div   null
            l_tradeConditionVoucherParams.setMarginTaxDiv(null);

            //�M�p�����������敪�i���N�jmargin_tax_div_next null
            l_tradeConditionVoucherParams.setMarginTaxDivNext(null);

            //�M�p�����������J�ݓ�margin_sp_acc_open_dat null
            l_tradeConditionVoucherParams.setMarginSpAccOpenDat(null);

            //����Ǘ������J�݋敪sp_mng_acc_open_div    null
            l_tradeConditionVoucherParams.setSpMngAccOpenDiv(null);

            //�����敪status  VARCHAR21   NULL    0�F������
            l_tradeConditionVoucherParams.setStatus(WEB3StatusDef.NOT_DEAL);

            //���M����send_timestamp  DATE    NULL    null
            l_tradeConditionVoucherParams.setSendTimestamp(null);

            //�쐬����created_timestamp   DATE    NotNull ��������
            l_tradeConditionVoucherParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

            //�X�V����last_updated_timestamp  DATE    NotNull ��������
            l_tradeConditionVoucherParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //�E�ƃR�[�hoccupation_div VARCHAR22   NULL    �����F�E�ƃR�[�h
            l_tradeConditionVoucherParams.setOccupationDiv(l_strOccupationCode);

            //�o�^�敪regist_div  VARCHAR21   NULL    1�F�ύX
            l_tradeConditionVoucherParams.setRegistDiv(WEB3RegistDivDef.CHANGE);

            //�g�єԍ�.�Ζ�����ύX�\���h�cmobile_office_info_regist_id  �����F�ύX�\��ID
            l_tradeConditionVoucherParams.setMobileOfficeInfoRegistId(l_lngChangeRegistID);

            WEB3DataAccessUtility.insertRow(l_tradeConditionVoucherParams);
        }
        catch (DataQueryException l_ex) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataNetworkException l_ex) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * is�`�[�쐬<BR>
     * �`�[�쐬�̉ۂ𔻕ʂ���B<BR>
     * <BR>
     * 1�j �������}�X�^ == null�@@���@@�ύX�\�����.�E�Ƌ敪  != null�@@�܂��� <BR>
     *      �������}�X�^ != null�@@���� (�������}�X�^�s.�E�� == null ���� �ύX�\�����.�E�Ƌ敪 != null) �܂���  <BR>
     *      �������}�X�^() != null�@@���� (�������}�X�^�s.�E�� != null ���� !�������}�X�^�s.�E��. <BR>
     *      equals(�ύX�\�����.�E�Ƌ敪)) <BR>
     *<BR>
     *      �̏ꍇ�Atrue��ԋp <BR>
     *<BR>
     * �Q�j�@@�P�j�ȊO�Afalse��ԋp<BR>
     * <BR>
     * @@param l_mobileOfficeInfoRegistParams �g�єԍ�.�Ζ�����ύX�\��params<BR>
     * @@param l_accInfoMaster �������}�X�^
     */
    public static boolean isVoucherCreated(
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams ,
        WEB3AccInfoMaster l_accInfoMaster)
    {
        //1�j �������}�X�^ == null�@@���@@�ύX�\�����.�E�Ƌ敪  != null
        if (l_accInfoMaster == null && l_mobileOfficeInfoRegistParams.getOccupationDiv() != null) 
        {
            return true;
        }

        if (l_accInfoMaster != null)
        {
            String l_strOccupationDiv = ((AccountInfoMstParams)l_accInfoMaster.getDataSourceObject()).getOccupationDiv();

            //�������}�X�^ != null�@@���� (�������}�X�^�s.�E�� == null ���� �ύX�\�����.�E�Ƌ敪 != null)
            if (l_strOccupationDiv == null && l_mobileOfficeInfoRegistParams.getOccupationDiv() != null)
            {
                return true;
            }
            
            //�������}�X�^() != null�@@���� (�������}�X�^�s.�E�� != null 
            //���� !�������}�X�^�s.�E��.equals(�ύX�\�����.�E�Ƌ敪
            if (l_strOccupationDiv != null && !l_strOccupationDiv.equals(l_mobileOfficeInfoRegistParams.getOccupationDiv())) 
            {
                return true;
            }
        }

        return false;
    }
}
@
