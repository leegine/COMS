head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoAccopenConditionRegAcceptVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : ���E��c�d�q��t�E��������o�^�`�[(WEB3AccInfoAccopenConditionRegAcceptVoucher.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 �����F(���u) �V�K�쐬 �d�l�ύX���f��No.279 �c�a�X�V�d�l064
*/
package webbroker3.accountinfo;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (���E��c�d�q��t�E��������o�^�`�[)<BR>
 * ���E��c�d�q��t�E��������o�^�`�[<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AccInfoAccopenConditionRegAcceptVoucher
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoAccopenConditionRegAcceptVoucher.class);

    /**
     * (���E��c�d�q��t�E��������o�^Params)<BR>
     * ���E��c�d�q��t�E��������o�^�s�I�u�W�F�N�g<BR>
     */
    private HostConditionRegVoucherParams hostConditionRegVoucherParams;
    
    /**
     * �R���X�g���N�^ <BR>
     * <BR>
     * �P�j�s�I�u�W�F�N�g��this.���E��c�d�q��t�E��������o�^�s�ɃZ�b�g����B <BR>
     * <BR>
     * �Q�j�`�[���ʃR�[�h != null �̏ꍇ�A <BR>
     * �@@this.���E��c�d�q��t�E��������o�^�s�ɓ`�[���ʃR�[�h���Z�b�g����B <BR>
     */
    public WEB3AccInfoAccopenConditionRegAcceptVoucher(
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams,
        String l_strOrderRequestNumber)
    {
        //�P�j�s�I�u�W�F�N�g��this.���E��c�d�q��t�E��������o�^�s�ɃZ�b�g����B
        this.hostConditionRegVoucherParams = l_hostConditionRegVoucherParams;

        //�Q�j�`�[���ʃR�[�h != null �̏ꍇ�A
        //�@@this.���E��c�d�q��t�E��������o�^�s�ɓ`�[���ʃR�[�h���Z�b�g����B
        if (l_strOrderRequestNumber != null)
        {
            this.hostConditionRegVoucherParams.setOrderRequestNumber(l_strOrderRequestNumber);
        }
    }

    /**
     * (save���E��c�d�q��t�E��������o�^�iGI311�j�L���[)<BR>
     * ���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u���̍X�V���s�Ȃ��B <BR>
     * <BR>
     * �@@�u�d�q��t�T�[�r�X�o�^�E�ύX_���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u���d�l.xls�v�Q�ƁB<BR>
     * @@throws WEB3BaseException
     */
    public void saveHostConditionRegVoucherParams() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveHostConditionRegVoucherParams()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u���̍X�V���s�Ȃ��B
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams = new HostConditionRegVoucherParams();

            //���ʃR�[�h:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setOrderRequestNumber(
                this.hostConditionRegVoucherParams.getOrderRequestNumber());

            //�f�[�^�R�[�h:���E��c�d�q��t�E��������o�^�F�hGI843�h
            l_hostConditionRegVoucherParams.setRequestCode(
                WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);

            //�،���ЃR�[�h:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setInstitutionCode(
                this.hostConditionRegVoucherParams.getInstitutionCode());

            //���X�R�[�h:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setBranchCode(
                this.hostConditionRegVoucherParams.getBranchCode());

            //�ڋq�R�[�h:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setAccountCode(
                this.hostConditionRegVoucherParams.getAccountCode());

            //���҃R�[�h:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setTraderCode(
                this.hostConditionRegVoucherParams.getTraderCode());

            //���ʃR�[�h�i�����J�݌����q�j:ALL9 (9999999999999)
            l_hostConditionRegVoucherParams.setAccOpenRequestNumber("9999999999999");
            
            //�`�[�ʔ�:0
            l_hostConditionRegVoucherParams.setSerialNo("0");

            //����c���񍐏��@@���:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setPosReportTermDiv(
                this.hostConditionRegVoucherParams.getPosReportTermDiv());

            //����c���񍐏��@@�d�q��t�i�s�x�j:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setPosReportCycleDiv(
                this.hostConditionRegVoucherParams.getPosReportCycleDiv());

            //����c���񍐏��@@�a���:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setPosReportCertifDepoDiv(
                this.hostConditionRegVoucherParams.getPosReportCertifDepoDiv());

            //����c���񍐏��@@�v�Z��:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setPosReportAccStateDiv(
                this.hostConditionRegVoucherParams.getPosReportAccStateDiv());

            //�d�q��t�@@����񍐏�:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setTradingEReportDiv(
                this.hostConditionRegVoucherParams.getTradingEReportDiv());

            //�d�q��t�@@���M�^�p�񍐏�:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setInvEReportDiv(
                this.hostConditionRegVoucherParams.getInvEReportDiv());

            //�d�q��t�@@���z���E���ҋ�:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setRefundEReportDiv(
                this.hostConditionRegVoucherParams.getRefundEReportDiv());

            //�i�����j��������@@����:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setEquityTaxDiv(
                this.hostConditionRegVoucherParams.getEquityTaxDiv());

            //�i�����j��������@@����:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setEquityTaxDivNext(
                this.hostConditionRegVoucherParams.getEquityTaxDivNext());

            //�i�����j��������@@�J�ݓ�:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setEquitySpAccOpenDat(
                this.hostConditionRegVoucherParams.getEquitySpAccOpenDat());

            //�i�M�p�j��������@@����:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setMarginTaxDiv(
                this.hostConditionRegVoucherParams.getMarginTaxDiv());

            //�i�M�p�j��������@@����:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setMarginTaxDivNext(
                this.hostConditionRegVoucherParams.getMarginTaxDivNext());

            //�i�M�p�j��������@@�J�ݓ�:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setMarginSpAccOpenDat(
                this.hostConditionRegVoucherParams.getMarginSpAccOpenDat());

            //����Ǘ�����:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            l_hostConditionRegVoucherParams.setSpMngAccOpenDiv(
                this.hostConditionRegVoucherParams.getSpMngAccOpenDiv());

            //�����敪:0�F������
            l_hostConditionRegVoucherParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

            //���M����:null
            l_hostConditionRegVoucherParams.setSendTimestamp(null);

            //�쐬����:��������
            l_hostConditionRegVoucherParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

            //�X�V����:��������
            l_hostConditionRegVoucherParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_hostConditionRegVoucherParams);
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
}
@
