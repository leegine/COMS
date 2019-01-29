head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformConditionRegVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���E��c�d�q��t�E��������o�^�`�[(WEB3InformHostConditionRegVoucher.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 �юu�� (���u) �V�K�쐬 ���f��113 DB�ύX�d�l016
Revision History : 2007/10/23 �����F (���u) ���f��121
*/
package webbroker3.inform;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.accountopen.data.HostConditionRegVoucherRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (���E��c�d�q��t�E��������o�^�`�[)<BR>
 * ���E��c�d�q��t�E��������o�^�`�[<BR>
 * <BR>
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3InformConditionRegVoucher
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformConditionRegVoucher.class);

    /**
     * (���E��c�d�q��t�E��������o�^�s)<BR>
     * ���E��c�d�q��t�E��������o�^�s�I�u�W�F�N�g
     */
    private HostConditionRegVoucherParams hostConditionRegVoucherParams;

    /**
     * (�e��A���s)<BR>
     * �e��A���s�I�u�W�F�N�g
     */
    private VariousInformParams variousInformParams;

    /**
     * (���E��c�d�q��t�E��������o�^)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �P�j�s�I�u�W�F�N�g��this.���E��c�d�q��t�E��������o�^�s�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�j�`�[���ʃR�[�h != null �̏ꍇ�A <BR>
     * �@@this.���E��c�d�q��t�E��������o�^�s�ɓ`�[���ʃR�[�h���Z�b�g����B<BR>
     * @@param l_hostConditionRegVoucherParams - (���E��c�d�q��t�E��������o�^�s)<BR>
     * ���E��c�d�q��t�E��������o�^�s�I�u�W�F�N�g<BR>
     * @@param l_strVoucherRequestNumber - (�`�[���ʃR�[�h)<BR>
     * �`�[���ʃR�[�h
     */
    public WEB3InformConditionRegVoucher(
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams,
        String l_strVoucherRequestNumber)
    {
        //�P�j�s�I�u�W�F�N�g��this.���E��c�d�q��t�E��������o�^�s�ɃZ�b�g����B
        this.hostConditionRegVoucherParams = l_hostConditionRegVoucherParams;

        //�Q�j�`�[���ʃR�[�h != null �̏ꍇ�A
        if (l_strVoucherRequestNumber != null)
        {
            //this.���E��c�d�q��t�E��������o�^�s�ɓ`�[���ʃR�[�h���Z�b�g����
            this.hostConditionRegVoucherParams.setOrderRequestNumber(l_strVoucherRequestNumber);
        }
    }

    /**
     * (���E��c�d�q��t�E��������o�^)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �s�I�u�W�F�N�g��this.�e��A���s�ɃZ�b�g����B<BR>
     * @@param l_variousInformParams - (�e��A���s)<BR>
     * �e��A���s�I�u�W�F�N�g
     */
    public WEB3InformConditionRegVoucher(VariousInformParams l_variousInformParams)
    {
        //�s�I�u�W�F�N�g��this.�e��A���s�ɃZ�b�g����B
        this.variousInformParams = l_variousInformParams;
    }

    /**
     * (save���E��c�d�q��t�E��������o�^�`�[�L���[)<BR>
     * ���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u���Ƀ��R�[�h���쐬����B <BR>
     * <BR>
     * DB�X�V�d�l�u���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u��.xls�v<BR>
     * @@throws WEB3BaseException
     */
    public void saveHostConditionRegVoucher() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveHostConditionRegVoucher()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DB�X�V�d�l�u���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u��.xls�v
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
                this.hostConditionRegVoucherParams;

            //�f�[�^�R�[�h
            //���E��c�d�q��t�E��������o�^�F�hGI843�h
            l_hostConditionRegVoucherParams.setRequestCode(
                WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);

            //���҃R�[�h
            //null
            l_hostConditionRegVoucherParams.setTraderCode(null);

            //���ʃR�[�h�i�����J�݌����q�j
            //ALL9 (9999999999999)
            l_hostConditionRegVoucherParams.setAccOpenRequestNumber("9999999999999");

            //�`�[�ʔ�
            //0
            l_hostConditionRegVoucherParams.setSerialNo("0");

            //�d�q��t�@@���M�^�p�񍐏�
            //null
            l_hostConditionRegVoucherParams.setInvEReportDiv(null);

            //�d�q��t�@@���z���E���ҋ�
            //null
            l_hostConditionRegVoucherParams.setRefundEReportDiv(null);

            //�����敪
            //0�F������
            l_hostConditionRegVoucherParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

            //���M����
            //null
            l_hostConditionRegVoucherParams.setSendTimestamp(null);

            //�쐬����
            //��������
            l_hostConditionRegVoucherParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

            //�X�V����
            //��������
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
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (delete���E��c�d�q��t�E��������o�^�`�[�L���[)<BR>
     * ���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u���̃��R�[�h���폜����B<BR>
     * <BR>
     * �m�폜�����n <BR>
     * ���ʃR�[�h = �e��A���s.get�`�[���ʃR�[�h�i�j and <BR>
     * �f�[�^�R�[�h = "GI843" and <BR>
     * �،���ЃR�[�h = �e��A���s.get�،���ЃR�[�h�i�j and <BR>
     * ���X�R�[�h = �e��A���s.get���X�R�[�h�i�j and <BR>
     * �ڋq�R�[�h = �e��A���s.get�ڋq�R�[�h�i�j <BR>
     * @@throws WEB3BaseException
     */
    public void deleteHostConditionRegVoucher() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteHostConditionRegVoucher()";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append("order_request_number = ? ");
        l_sbWhere.append("and request_code = ? ");
        l_sbWhere.append("and institution_code = ? ");
        l_sbWhere.append("and branch_code = ? ");
        l_sbWhere.append("and account_code = ? ");

        List l_lisWhereValues = new ArrayList();

        //���ʃR�[�h = �e��A���s.get�`�[���ʃR�[�h�i�j
        l_lisWhereValues.add(this.variousInformParams.getOrderRequestNumber());

        //�f�[�^�R�[�h = "GI843"
        l_lisWhereValues.add(WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);

        //�،���ЃR�[�h = �e��A���s.get�،���ЃR�[�h�i�j
        l_lisWhereValues.add(this.variousInformParams.getInstitutionCode());

        //���X�R�[�h = �e��A���s.get���X�R�[�h�i�j
        l_lisWhereValues.add(this.variousInformParams.getBranchCode());

        //�ڋq�R�[�h = �e��A���s.get�ڋq�R�[�h�i�j
        l_lisWhereValues.add(this.variousInformParams.getAccountCode());

        Object[] l_objWhereValues = new Object[l_lisWhereValues.size()];
        l_lisWhereValues.toArray(l_objWhereValues);
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(
                HostConditionRegVoucherRow.TYPE,
                l_sbWhere.toString(),
                l_objWhereValues);
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
     * (is�`�[�쐬)<BR>
     * this.���E��c�d�q��t�E��������o�^�s�̈ȉ����ڂ��S��null�̏ꍇ�A<BR>
     * false��ԋp����B <BR>
     * ����ȊO�̏ꍇ��true��ԋp����B <BR>
     * <BR>
     * �P�j�@@this.���E��c�d�q��t�E��������o�^�s�̈ȉ����ڂ��S��null�̏ꍇ�A<BR>
     * false��ԋp����B <BR>
     * <BR>
     * �@@�|����c���񍐏��@@��� <BR>
     * �@@�|����c���񍐏��@@�d�q��t�i�s�x�j <BR>
     * �@@�|����c���񍐏��@@�a��� <BR>
     * �@@�|����c���񍐏��@@�v�Z�� <BR>
     * �@@�|�d�q��t�@@����񍐏� <BR>
     * �@@�|�d�q��t�@@���M�^�p�񍐏� <BR>
     * �@@�|�d�q��t�@@���z���E���ҋ� <BR>
     * �@@�|�i�����j��������@@���� <BR>
     * �@@�|�i�����j��������@@���� <BR>
     * �@@�|�i�����j��������@@�J�ݓ� <BR>
     * �@@�|�i�M�p�j��������@@���� <BR>
     * �@@�|�i�M�p�j��������@@���� <BR>
     * �@@�|�i�M�p�j��������@@�J�ݓ� <BR>
     * �@@�|����Ǘ����� <BR>
     * <BR>
     * �Q�j�@@�P�j�ɊY�����Ȃ��ꍇtrue��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isVoucherMake()
    {
        final String STR_METHOD_NAME = "isVoucherMake()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@this.���E��c�d�q��t�E��������o�^�s�̈ȉ����ڂ��S��null�̏ꍇ
        //false��ԋp����B
        if (this.hostConditionRegVoucherParams.getPosReportTermDiv() == null
            && this.hostConditionRegVoucherParams.getPosReportCycleDiv() == null
            && this.hostConditionRegVoucherParams.getPosReportCertifDepoDiv() == null
            && this.hostConditionRegVoucherParams.getPosReportAccStateDiv() == null
            && this.hostConditionRegVoucherParams.getTradingEReportDiv() == null
            && this.hostConditionRegVoucherParams.getInvEReportDiv() == null
            && this.hostConditionRegVoucherParams.getRefundEReportDiv() == null
            && this.hostConditionRegVoucherParams.getEquityTaxDiv() == null
            && this.hostConditionRegVoucherParams.getEquityTaxDivNext() == null
            && this.hostConditionRegVoucherParams.getEquitySpAccOpenDat() == null
            && this.hostConditionRegVoucherParams.getMarginTaxDiv() == null
            && this.hostConditionRegVoucherParams.getMarginTaxDivNext() == null
            && this.hostConditionRegVoucherParams.getMarginSpAccOpenDat() == null
            && this.hostConditionRegVoucherParams.getSpMngAccOpenDiv() == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            //�Q�j�@@�P�j�ɊY�����Ȃ��ꍇtrue��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }
}

@
