head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformTransferApplyPostVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �U�֐\���i�X���j�`�[(WEB3AdminInformTransferApplyPostVoucher.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 �Ӑ�(���u) �V�K�쐬 ���f��No.056
Revision History    : 2007/06/22 ����(SCS) �ύX ���f��No.093
Revision History    : 2007/07/11 ����(SCS) �ύX ���f��No.101
*/

package webbroker3.inform;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.HostPostalTransVoucherDao;
import webbroker3.accountopen.data.HostPostalTransVoucherParams;
import webbroker3.accountopen.data.HostPostalTransVoucherRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3TransferRangeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.message.WEB3InformDetailInfoUnit;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�U�֐\���i�X���j�`�[)<BR>
 * �U�֐\���i�X���j�`�[�N���X
 */
public class WEB3AdminInformTransferApplyPostVoucher
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformTransferApplyPostVoucher.class);

    /**
     * (�e��A���s)<BR>
     * �e��A���s�I�u�W�F�N�g
     */
    private VariousInformParams variousInformParams;

    /**
     * @@roseuid 4663A9D501F4
     */
    public WEB3AdminInformTransferApplyPostVoucher()
    {

    }

    /**
     * (�U�֐\���i�X���j�`�[)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �s�I�u�W�F�N�g��this.�e��A���s�ɃZ�b�g����B<BR>
     * @@param l_variousInformParams - (�e��A���s)<BR>
     * �e��A���s�I�u�W�F�N�g
     * @@roseuid 464D3E1A0166
     */
    public WEB3AdminInformTransferApplyPostVoucher(VariousInformParams l_variousInformParams)
    {
        // �P�j�s�I�u�W�F�N�g��this.�e��A���s�ɃZ�b�g����B
        this.variousInformParams = l_variousInformParams;
    }

    /**
     * (�U�֐\���i�X���j�`�[)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j�s�I�u�W�F�N�g��this.�e��A���s�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�j�e��A�����ʃR�[�h != null �̏ꍇ�A<BR>
     * �@@this.�e��A���s�Ɋe��A�����ʃR�[�h���Z�b�g����B<BR>
     * <BR>
     * �R�j�`�[���ʃR�[�h != null �̏ꍇ�A<BR>
     * �@@this.�e��A���s�ɓ`�[���ʃR�[�h���Z�b�g����B<BR>
     * @@param l_variousInform - (�e��A���s)<BR>
     * �e��A���s�I�u�W�F�N�g
     * @@param l_strVoucherRequestNumber - (�`�[���ʃR�[�h)<BR>
     * �`�[���ʃR�[�h
     * @@param l_strVariousInformRequestNumber - (�e��A�����ʃR�[�h)<BR>
     * �e��A�����ʃR�[�h
     * @@roseuid 464AA5EB0011
     */
    public WEB3AdminInformTransferApplyPostVoucher(
        VariousInformParams l_variousInform,
        String l_strVoucherRequestNumber,
        String l_strVariousInformRequestNumber)
    {
        // �P�j�s�I�u�W�F�N�g��this.�e��A���s�ɃZ�b�g����B
        this.variousInformParams = l_variousInform;

        // �Q�j�e��A�����ʃR�[�h != null �̏ꍇ�A
        //  this.�e��A���s�Ɋe��A�����ʃR�[�h���Z�b�g����B
        if (l_strVariousInformRequestNumber != null)
        {
            this.variousInformParams.setRequestNumber(l_strVariousInformRequestNumber);
        }

        // �R�j�`�[���ʃR�[�h != null �̏ꍇ�A
        //�@@this.�e��A���s�ɓ`�[���ʃR�[�h���Z�b�g����B

        if (l_strVoucherRequestNumber != null)
        {
            this.variousInformParams.setOrderRequestNumber(l_strVoucherRequestNumber);
        }
    }

    /**
     * (save�X���o�^�`�[�L���[)<BR>
     * �U�֐\���i�X���j�`�[�iG26�j�L���[�e�[�u���Ƀ��R�[�h���쐬����B<BR>
     * <BR>
     * DB�X�V�d�l�u�U�֐\���i�X���j�`�[�iG26�j�L���[�e�[�u��.xls�v�Q��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 464AA5EB0013
     */
    public void savePostRegistVoucherHost() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " savePostRegistVoucherHost()";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            HostPostalTransVoucherParams l_hostPostalTransVoucherParams = new HostPostalTransVoucherParams();

            // ���ʃR�[�h�i�̔Ԃ����l�j
//            l_hostPostalTransVoucherParams.setOrderRequestNumber(
//                WEB3StringTypeUtility.formatNumber(HostPostalTransVoucherDao.newPkValue()));
            
            // �`�[���ʃR�[�h�̍X�V��ύX 2007.06.22 SCS����------------- Start
            l_hostPostalTransVoucherParams.setOrderRequestNumber(variousInformParams.getOrderRequestNumber());
            //--------------------------------------------------------- end

            // �f�[�^�R�[�h�i�U�֐\���i�X���j�F�hGI828�h�j
            l_hostPostalTransVoucherParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL);

            // �،���ЃR�[�h�i�e��A��Params.�،���ЃR�[�h�j
            l_hostPostalTransVoucherParams.setInstitutionCode(this.variousInformParams.getInstitutionCode());

            // ���X�R�[�h�i�e��A��Params.���X�R�[�h�j
            l_hostPostalTransVoucherParams.setBranchCode(this.variousInformParams.getBranchCode());

            // �ڋq�R�[�h�i�e��A��Params.�ڋq�R�[�h�j
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)((WEB3GentradeAccountManager)l_finApp.getAccountManager()).getMainAccount(
                    variousInformParams.getInstitutionCode(),
                    variousInformParams.getBranchCode(),
                    variousInformParams.getAccountCode());

            l_hostPostalTransVoucherParams.setAccountCode(l_mainAccount.getAccountCode());

            // ���҃R�[�h�i�e��A��Params.���҃R�[�h�iSONAR�j�j
            l_hostPostalTransVoucherParams.setTraderCode(this.variousInformParams.getSonarTraderCode());

            // ���ʃR�[�h�i�����J�݌����q�j�i�e��A��Params.���ʃR�[�h�j
//          l_hostPostalTransVoucherParams.setAccOpenRequestNumber(this.variousInformParams.getRequestNumber());
            
            // ----------- ���ʃR�[�h�i�����J�݌����q�j��ALL9���Z�b�g����悤�C��--------SCS���� ----------
            l_hostPostalTransVoucherParams.setAccOpenRequestNumber("9999999999999");
            // ----------------------------- end ------------------------------------------------------            

            // �`�[�ʔԁi0�j
            l_hostPostalTransVoucherParams.setSerialNo("0");

            // �U�o�͈́i�e��A��Params.�敪�Q�j
            l_hostPostalTransVoucherParams.setTransferRange(this.variousInformParams.getExtDiv2());

            // �w�菤�i�R�[�h�i�e��A��Params.�敪�R�j
            l_hostPostalTransVoucherParams.setProductTypeCodeSpec(this.variousInformParams.getExtDiv3());

            // �w������R�[�h�i�e��A��Params.�����R�[�h�j
            l_hostPostalTransVoucherParams.setProductCodeSpec(this.variousInformParams.getFundCode());

            // �o�^�敪�i�e��A��Params.�敪�S�j
            l_hostPostalTransVoucherParams.setRegistDiv(this.variousInformParams.getExtDiv4());

            // �U�֋敪�i�e��A��Params.�敪�T�j
            l_hostPostalTransVoucherParams.setTransferDiv(this.variousInformParams.getExtDiv5());

            // �U�֎萔���敪�i�e��A��Params.�敪�U�j
            l_hostPostalTransVoucherParams.setTransCommission(this.variousInformParams.getExtDiv6());

            // �ʒ��L���i�e��A��Params.�e�L�X�g�W�j
            l_hostPostalTransVoucherParams.setPostalSaveCode(this.variousInformParams.getExtText8());

            // �ʒ��ԍ��i�e��A��Params.�e�L�X�g�P�O�j
            l_hostPostalTransVoucherParams.setPostalSaveNo(this.variousInformParams.getExtText10());

            // �������`�i�e��A��Params.�e�L�X�g�P�𔼊p�J�i�ɕҏW�����l�j
            l_hostPostalTransVoucherParams.setFinAccountName(
                WEB3StringTypeUtility.to1byteKana(this.variousInformParams.getExtText1()));

            // �����敪�i0�F�������j
            l_hostPostalTransVoucherParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

            // ���M�����inull�j
            l_hostPostalTransVoucherParams.setSendTimestamp(null);

            // �쐬�����i���������j
            l_hostPostalTransVoucherParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

            // �X�V�����i���������j
            l_hostPostalTransVoucherParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            l_queryProcessor.doInsertQuery(l_hostPostalTransVoucherParams);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (delete�X���o�^�`�[�L���[)<BR>
     * �U�֐\���i�X���j�`�[�iG26�j�L���[�e�[�u���̃��R�[�h���폜����B<BR>
     * <BR>
     * �m�폜�����n<BR>
     * ���ʃR�[�h = �e��A���s.get�`�[���ʃR�[�h�i�j and<BR>
     * �f�[�^�R�[�h = "GI828" and<BR>
     * �،���ЃR�[�h = �e��A���s.get�،���ЃR�[�h�i�j and<BR>
     * ���X�R�[�h = �e��A���s.get���X�R�[�h�i�j and<BR>
     * �ڋq�R�[�h = �e��A���s.get�ڋq�R�[�h�i�j<BR>
     * @@throws WEB3BaseException
     * @@roseuid 464D4C4C0137
     */
    public void deletePostRegistVoucherHost() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deletePostRegistVoucherHost()";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            StringBuffer l_sbQuery = new StringBuffer();
            l_sbQuery.append(" order_request_number = ? and ");
            l_sbQuery.append(" request_code = ? and ");
            l_sbQuery.append(" institution_code = ? and ");
            l_sbQuery.append(" branch_code = ? and ");
            l_sbQuery.append(" account_code = ? ");

            Object[] l_objValues = {
                this.variousInformParams.getOrderRequestNumber(),
                WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL,
                this.variousInformParams.getInstitutionCode(),
                this.variousInformParams.getBranchCode(),
                this.variousInformParams.getAccountCode()};

            l_queryProcessor.doDeleteAllQuery(HostPostalTransVoucherRow.TYPE,
                    l_sbQuery.toString(),
                    l_objValues);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�X�����)<BR>
     * �U�֐\���i�X���j�`�[���쐬����ۂ̃p�����[�^�`�F�b�N���s���B<BR>
     * <BR>
     * <BR>
     * �P�j����:�A�����.�敪�Q == A�`C �̏ꍇ�A<BR>
     * <BR>
     * �@@�P�|�P�j����:�A�����.�敪�R != null or ����:�A�����.�R�[�h�P != null<BR>
     * �@@�@@�@@�@@ or ����:�A�����.�R�[�h�Q != null �̏ꍇ�A��O���X���[<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�w�U�o�͈̓G���[�x<BR>
     * <BR>
     * �Q�j����:�A�����.�敪�Q != A�`C�A 1:�S���� �̏ꍇ�A��O���X���[<BR>
     * <BR>
     * �@@�@@�w�U�o�͈̓G���[�x<BR>
     * @@param l_informInfoUnit - (�A�����)<BR>
     * �A�����
     * @@throws WEB3BaseException
     * @@roseuid 4651249900B2
     */
    public void validatePostInfo(WEB3InformDetailInfoUnit l_informInfoUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePostInfo(WEB3InformDetailInfoUnit)";
        log.entering(STR_METHOD_NAME);

        // �P�j����:�A�����.�敪�Q == A�`C �̏ꍇ�A
        if (WEB3TransferRangeDef.SALE_TURNOVER.equals(l_informInfoUnit.div2)
            || WEB3TransferRangeDef.SALE_TURNOVER_2.equals(l_informInfoUnit.div2)
            || WEB3TransferRangeDef.SALE_TURNOVER_3.equals(l_informInfoUnit.div2))
        {
            //�P�|�P�j����:�A�����.�敪�R != null or ����:�A�����.�R�[�h�P != null
            //�@@�@@ or ����:�A�����.�R�[�h�Q != null �̏ꍇ�A��O���X���[
            if (l_informInfoUnit.div3 != null
                || l_informInfoUnit.code1 != null
                || l_informInfoUnit.code2 != null)
            {
                log.debug(STR_METHOD_NAME + "�U�o�͈̓G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02791,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�U�o�͈̓G���[�B");
            }
        }
        else
        {
            // �Q�j����:�A�����.�敪�Q != A�`C�A 1:�S���� �̏ꍇ�A��O���X���[
            if (!WEB3TransferRangeDef.ALL_ACCOUNT.equals(l_informInfoUnit.div2))
            {
                log.debug(STR_METHOD_NAME + "�U�o�͈̓G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02791,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�U�o�͈̓G���[�B");
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
