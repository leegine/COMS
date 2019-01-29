head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherRegAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݓ`�[�o�^��tUnitServiceImpl(WEB3AccOpenVoucherRegAcceptUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/16 �A�C��(���u) �V�K�쐬
                   2006/07/12 ���� (���u) �d�l�ύX ���f��072�A080
                   2006/09/12 ���r (���u) �d�l�ύX ���f��099
Revesion History : 2007/09/20 �����F (���u) �d�l�ύX ���f��146
Revesion History : 2009/08/13 �đo�g (���u) �d�l�ύX ���f��176
Revesion History : 2009/09/04 �����F (���u) �d�l�ύX ���f��211
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.AccOpenVoucherStatusPK;
import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.accountopen.data.HostAccRegVoucherPK;
import webbroker3.accountopen.data.HostAccRegVoucherRow;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherPK;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherRow;
import webbroker3.accountopen.data.HostAgreeTransVoucherPK;
import webbroker3.accountopen.data.HostAgreeTransVoucherRow;
import webbroker3.accountopen.data.HostBankTransVoucherPK;
import webbroker3.accountopen.data.HostBankTransVoucherRow;
import webbroker3.accountopen.data.HostChargedInfoVoucherPK;
import webbroker3.accountopen.data.HostChargedInfoVoucherRow;
import webbroker3.accountopen.data.HostConditionRegVoucherPK;
import webbroker3.accountopen.data.HostConditionRegVoucherRow;
import webbroker3.accountopen.data.HostContMrgVoucherPK;
import webbroker3.accountopen.data.HostContMrgVoucherRow;
import webbroker3.accountopen.data.HostFDepositVoucherPK;
import webbroker3.accountopen.data.HostFDepositVoucherRow;
import webbroker3.accountopen.data.HostGpRegVoucherPK;
import webbroker3.accountopen.data.HostGpRegVoucherRow;
import webbroker3.accountopen.data.HostInsiderRegVoucherPK;
import webbroker3.accountopen.data.HostInsiderRegVoucherRow;
import webbroker3.accountopen.data.HostMrfAccVoucherPK;
import webbroker3.accountopen.data.HostMrfAccVoucherRow;
import webbroker3.accountopen.data.HostPostalTransVoucherPK;
import webbroker3.accountopen.data.HostPostalTransVoucherRow;
import webbroker3.accountopen.data.HostRealnameRegVoucherPK;
import webbroker3.accountopen.data.HostRealnameRegVoucherRow;
import webbroker3.accountopen.data.HostStockholderRegVoucherPK;
import webbroker3.accountopen.data.HostStockholderRegVoucherRow;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherRegAcceptUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3RegDivDef;
import webbroker3.common.define.WEB3VoucherStatusDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�ݓ`�[�o�^��tUnitServiceImpl)<BR>
 * �����J�ݓ`�[�o�^��t�P���T�[�r�X�����N���X<BR>
 * �i�g�����U�N�V��������=TX_CREATE_NEW�j<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AccOpenVoucherRegAcceptUnitServiceImpl implements WEB3AccOpenVoucherRegAcceptUnitService
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenVoucherRegAcceptUnitServiceImpl.class);

    /**
     * @@roseuid 41B45E74003E
     */
    public WEB3AccOpenVoucherRegAcceptUnitServiceImpl()
    {

    }

    /**
     * (notify�`�[�o�^��t)<BR>
     * �����J�ݓ`�[�o�^��t�P�����������{���A�������ʁi�����ρ^�G���[�j��<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�j�@@�o�^�L���[�f�[�^�擾<BR>
     * �@@��t�L���[.�f�[�^�R�[�h�ɑΉ�����o�^�L���[���ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�o�^�L���[�e�[�u��(*).���ʃR�[�h = ��t�L���[.���ʃR�[�h And�@@<BR>
     * ���o�^�L���[.���ʃR�[�h�́A�`�[�̎��ʃR�[�h�iorder_request_number�j<BR>
     * �@@�o�^�L���[�e�[�u��(*).�f�[�^�R�[�h = ��t�L���[.�f�[�^�R�[�h And<BR>
     * �@@�o�^�L���[�e�[�u��(*).�،���ЃR�[�h = ��t�L���[.�،���ЃR�[�h And<BR>
     * �@@�o�^�L���[�e�[�u��(*).���X�R�[�h = ��t�L���[.���X�R�[�h And<BR>
     * �@@�o�^�L���[�e�[�u��(*).�ڋq�R�[�h = ��t�L���[.�ڋq�R�[�h<BR>
     * <BR>
     * �@@(*) [�f�[�^�R�[�h�ɑΉ�����o�^�L���[�e�[�u����]<BR>
     * �@@GI82A�F�ڋq�o�^��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u��<BR>
     * �@@GI82G�F�_�񏑒�����t�@@���@@�_�񏑒����`�[�iG1151�j�L���[�e�[�u��<BR>
     * �@@GI82C�F�U�֐\���i��s�j��t�@@���@@�U�֐\���i��s�j�`�[�iG26�j�L���[�e�[�u��<BR>
     * �@@GI82H�F�U�֐\���i�X���j��t�@@���@@�U�֐\���i�X���j�`�[�iG26�j�L���[�e�[�u��<BR>
     * �@@GI82B�F�ېU���ӎ�t�@@���@@�ېU���ӓ`�[�iGA300�j�L���[�e�[�u��<BR>
     * �@@GI83G�F�L������t�@@���@@�L�����`�[�iG5401�j�L���[�e�[�u��<BR>
     * �@@GI82E�FMRF������t�@@���@@MRF�����`�[�iGI601)�`�[�L���[�e�[�u��<BR>
     * �@@GI81I�F�����ғo�^��t�@@���@@�����ғo�^�`�[�iG9801�j�L���[�e�[�u�� <BR>
     * �@@GI82D�FGP�����o�^��t�@@���@@GP�����o�^�`�[�iG1220�j�L���[�e�[�u��<BR>
     * �@@GI84I�F���O���E�o�^��t�@@���@@���O���E����o�^�`�[�iG8610�j�L���[�e�[�u�� <BR>
     * �@@GI84H�F�ڋq���̓o�^��t�@@���@@�ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u�� <BR>
     * �@@GI84E�F�ڋq�o�^�i����Ɓj��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u�� <BR>
     * �@@GI85D�F�O�ݗa�������o�^��t�@@���@@�O�ݗa�������o�^�`�[(G43)�L���[�e�[�u�� <BR>
     * �@@GI84C�F���E��c�d�q��t�E��������o�^��t�@@���@@<BR>
     * �@@���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u��<BR>
     * �@@GI86E�F�@@�\�ʒm���o�^��t�@@���@@�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u��<BR>
     * <BR>
     * �@@�擾�ł��Ȃ������ꍇ�́A�����敪.�G���[ ��ԋp����B<BR>
     * <BR>
     * �@@�l�q�e������t�i��t�L���[.�f�[�^�R�[�h == GI82E�FMRF������t�j�̏ꍇ�ŁA<BR>
     * �@@�i�擾�����o�^�L���[.�o�^�敪 != �V�K�j�̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u���X�V�iDB-update�j�B<BR>
     * �@@�����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u�����ADB�X�V�d�l�̒ʂ�X�V����B<BR>
     * <BR>
     * �@@[�X�V�����iPK�j]<BR>
     * �@@�،���ЃR�[�h = ��t�L���[.�،���ЃR�[�h<BR>
     * �@@���ʃR�[�h = �P�j�Ŏ擾�����o�^�L���[.���ʃR�[�h�i�����J�݌����q�j<BR>
     * �@@�f�[�^�R�[�h = ��t�L���[.�f�[�^�R�[�h<BR>
     * �@@�`�[�ʔ� = �P�j�Ŏ擾�����o�^�L���[.�`�[�ʔ�<BR>
     * <BR>
     * �@@�X�V���e�́ADB�X�V�d�l�u��t_�����J�ݓ`�[�쐬�X�e�[�^�X<BR>
     * DB�X�V�d�l.xls�v<BR>
     * �@@�@@�|�i��t�L���[.��t���� == 1�F��t�����j�̏ꍇ�A�u��t�ρv�V�[�g�Q�ƁB<BR>
     * �@@�@@�|�i��t�L���[.��t���� == 2�F�G���[�j�̏ꍇ�A�u��t�G���[�v�V�[�g�Q�ƁB<BR>
     * <BR>
     * �P�j�`�Q�j�̏����ŁA�G���[�����������ꍇ�A�����敪.�G���[ ��ԋp����B<BR>
     * �ȊO�A�����敪.�����ρ@@��ԋp����B<BR>
     * @@param l_accOpenAcceptParams - �����J�ݓ`�[�o�^��t�L���[<BR>
     * <BR>
     * ���@@�����J�ݓ`�[�o�^��t�L���[Params�N���X�́ADDL��莩����������B<BR>
     *
     * @@return String
     * @@roseuid 41A19BD700CD
     */
    public String notifyVoucherRegAccept(HostAccOpenAcceptParams l_accOpenAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " notifyVoucherRegAccept(HostAccOpenAcceptParams)";
        log.entering(STR_METHOD_NAME);

        if (l_accOpenAcceptParams == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        String l_strSerialNo = null;

        String l_strReqCode = l_accOpenAcceptParams.getRequestCode();
        String l_strOrderReqNumber = l_accOpenAcceptParams.getOrderRequestNumber();
        String l_strInstitutionCode = l_accOpenAcceptParams.getInstitutionCode();
        String l_strBranchCode = l_accOpenAcceptParams.getBranchCode();
        String l_strAccountCode = l_accOpenAcceptParams.getAccountCode();
        String l_strAccOpenRequestNumber = null;

        String[] l_acceptReqCodes = 
            new String[]{"GI82A","GI82G","GI82C","GI82H","GI82B","GI83G","GI82E", 
                         "GI81I", "GI82D", "GI84I", "GI84H", "GI84E", "GI85D", "GI84C", "GI86E"};
        String[] l_registReqCodes = 
            new String[]{"GI821","GI827","GI823","GI828","GI822","GI837","GI825", 
                         "GI819", "GI824", "GI849", "GI848", "GI845", "GI854", "GI843", "GI865"};

        String l_strRegistReqCode = null;
        for (int i = 0; i < l_acceptReqCodes.length; i++)
        {
            if (l_acceptReqCodes[i].equals(l_strReqCode))
            {
                l_strRegistReqCode = l_registReqCodes[i];
                break;
            }
        }

        try
        {
            //�P�j�@@�o�^�L���[�f�[�^�擾
            
            //GI82A�F�ڋq�o�^��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u��
            if (WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST_ACCEPT.equals(l_strReqCode))
            {
                HostAccRegVoucherPK l_pk = new HostAccRegVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAccRegVoucherRow l_row =
                    (HostAccRegVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataFindException,DataQueryException,DataNetworkException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            
            //GI82G�F�_�񏑒�����t�@@���@@�_�񏑒����`�[�iG1151�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_CONTRACT_COLLECT_ACCEPT.equals(l_strReqCode))
            {
                HostContMrgVoucherPK l_pk = new HostContMrgVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostContMrgVoucherRow l_row =
                    (HostContMrgVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException

                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            
            //�@@GI82C�F�U�֐\���i��s�j��t�@@���@@�U�֐\���i��s�j�`�[�iG26�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK_ACCEPT.equals(l_strReqCode))
            {
                HostBankTransVoucherPK l_pk = new HostBankTransVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostBankTransVoucherRow l_row =
                    (HostBankTransVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();

            }
            
            //GI82H�F�U�֐\���i�X���j��t�@@���@@�U�֐\���i�X���j�`�[�iG26�j�L���[�e�[�u�� 
            else if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL_ACCEPT.equals(l_strReqCode))
            {
                HostPostalTransVoucherPK l_pk = new HostPostalTransVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostPostalTransVoucherRow l_row =
                    (HostPostalTransVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            
            //GI82B�F�ېU���ӎ�t�@@���@@�ېU���ӓ`�[�iGA300�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_AGREE_TRANSFER_ACCEPT.equals(l_strReqCode))
            {
                HostAgreeTransVoucherPK l_pk = new HostAgreeTransVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAgreeTransVoucherRow l_row =
                    (HostAgreeTransVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            
            //�@@GI83G�F�L������t�@@���@@�L�����`�[�iG5401�j�L���[�e�[�u�� 
            else if (WEB3HostRequestCodeDef.ACCOPEN_CHARGED_INFO_ACCEPT.equals(l_strReqCode))
            {
                HostChargedInfoVoucherPK l_pk = new HostChargedInfoVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostChargedInfoVoucherRow l_row =
                    (HostChargedInfoVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            
            //GI82E�FMRF������t�@@���@@MRF�����`�[�iGI601)�`�[�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_MRF_ACCOUNT_ACCEPT.equals(l_strReqCode))
            {
                HostMrfAccVoucherPK l_pk = new HostMrfAccVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostMrfAccVoucherRow l_row =
                    (HostMrfAccVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                if (!WEB3RegDivDef.NEW.equals(l_row.getRegistDiv()))
                {
                    log.debug("�o�^�L���[.�o�^�敪 != �V�K," + l_row.getRegistDiv());
                    return null;
                }
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }

            //GI81I�F�����ғo�^��t�@@���@@�����ғo�^�`�[�iG9801�j�L���[�e�[�u�� 
            else if (WEB3HostRequestCodeDef.ACCOPEN_INSIDER_REG_ACCEPT.equals(l_strReqCode))
            {
                HostInsiderRegVoucherPK l_pk = new HostInsiderRegVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostInsiderRegVoucherRow l_row =
                    (HostInsiderRegVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();     
            }
            
            //�@@GI82D�FGP�����o�^��t�@@���@@GP�����o�^�`�[�iG1220�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_GP_REG_ACCEPT.equals(l_strReqCode))
            {
                HostGpRegVoucherPK l_pk = new HostGpRegVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostGpRegVoucherRow l_row =
                    (HostGpRegVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();     
            }

            //�@@GI84I�F���O���E�o�^��t�@@���@@���O���E����o�^�`�[�iG8610�j�L���[�e�[�u�� 
            else if (WEB3HostRequestCodeDef.ACCOPEN_STOCKHOLDER_REG_ACCEPT.equals(l_strReqCode))
            {
                HostStockholderRegVoucherPK l_pk = new HostStockholderRegVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostStockholderRegVoucherRow l_row =
                    (HostStockholderRegVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();     
            }
            
            //GI84H�F�ڋq���̓o�^��t�@@���@@�ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u�� 
            else if (WEB3HostRequestCodeDef.ACCOPEN_REALNAME_REG_ACCEPT.equals(l_strReqCode))
            {
                HostRealnameRegVoucherPK l_pk = new HostRealnameRegVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostRealnameRegVoucherRow l_row =
                    (HostRealnameRegVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();     
            }

            //GI84E�F�ڋq�o�^�i����Ɓj��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u�� 
            else if (WEB3HostRequestCodeDef.ACCOPEN_ACC_REG_ACCEPT.equals(l_strReqCode))
            {
                HostAccRegVoucherPK l_pk = new HostAccRegVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAccRegVoucherRow l_row =
                    (HostAccRegVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataFindException,DataQueryException,DataNetworkException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();   
            }
            
            //GI85D�F�O�ݗa�������o�^��t�@@���@@�O�ݗa�������o�^�`�[(G43)�L���[�e�[�u�� 
            else if (WEB3HostRequestCodeDef.F_DEPOSIT_REG_ACCEPT.equals(l_strReqCode))
            {
                HostFDepositVoucherPK l_pk = new HostFDepositVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostFDepositVoucherRow l_row =
                    (HostFDepositVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);//DataFindException,DataQueryException,DataNetworkException
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();   
            }

            //  GI84C�F���E��c�d�q��t�E��������o�^��t�@@��
            //�@@���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REG_ACCEPT.equals(l_strReqCode))
            {
                HostConditionRegVoucherPK l_pk = new HostConditionRegVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostConditionRegVoucherRow l_row =
                    (HostConditionRegVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_pk);
                l_strSerialNo = l_row.getSerialNo();
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            //GI86E�F�@@�\�ʒm���o�^��t�@@���@@�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_AGENCY_INFO_REG_ACCEPT.equals(l_strReqCode))
            {
                HostAgencyNotifyVoucherPK l_voucherPK = new HostAgencyNotifyVoucherPK(
                    l_strOrderReqNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAgencyNotifyVoucherRow l_voucherRow =
                    (HostAgencyNotifyVoucherRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(l_voucherPK);
                l_strSerialNo = l_voucherRow.getSerialNo();
                l_strAccOpenRequestNumber = l_voucherRow.getAccOpenRequestNumber();
            }
            else
            {
                log.debug("��t�L���[.�f�[�^�R�[�h�G���[:" + l_strReqCode);
                log.exiting(STR_METHOD_NAME);
                return WEB3HostStatusDef.DATA_ERROR;
            }

            //(*2) ��t�L���[���AGI84E�F�ڋq�o�^�i����Ɓj��t�̏ꍇ
            //�f�[�^�R�[�h��"GI821"�i�ڋq�o�^��t�j�Ō�������
            if (WEB3HostRequestCodeDef.ACCOPEN_ACC_REG_ACCEPT.equals(l_strReqCode))
            {
                l_strRegistReqCode = "GI821";
            }

            //�Q�j�@@�����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u���X�V�iDB-update�j
            AccOpenVoucherStatusPK l_pkStatus = new AccOpenVoucherStatusPK(
                l_strInstitutionCode,
                l_strAccOpenRequestNumber,//l_strOrderReqNumber,
                l_strRegistReqCode,//l_strReqCode,
                l_strSerialNo);//DataException
            Map l_mapStatus = new Hashtable();
            if (WEB3AcceptStatusDef.OVER.equals(l_accOpenAcceptParams.getAcceptStatus()))
            {
                //��t����
                l_mapStatus.put("voucher_status",WEB3VoucherStatusDef.RECEIVE_COMPLETE);
                l_mapStatus.put("recv_timestamp",GtlUtils.getTradingSystem().getSystemTimestamp());
                l_mapStatus.put("last_updated_timestamp",GtlUtils.getTradingSystem().getSystemTimestamp());
            }
            else if (WEB3AcceptStatusDef.ERROR.equals(l_accOpenAcceptParams.getAcceptStatus()))
            {
                //�G���[
                l_mapStatus.put("voucher_status",WEB3VoucherStatusDef.RECEIVE_ERROR);
                l_mapStatus.put("recv_timestamp",GtlUtils.getTradingSystem().getSystemTimestamp());
                l_mapStatus.put("error_code",l_accOpenAcceptParams.getErrorCode());
                l_mapStatus.put("last_updated_timestamp",GtlUtils.getTradingSystem().getSystemTimestamp());
            }

            Processors.getDefaultProcessor().doUpdateQuery(l_pkStatus, l_mapStatus);//DataException
        }
        catch (DataFindException l_e)
        {
            log.error(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage() + "[" +
                "�f�[�^�R�[�h:" + l_strReqCode +
                ", �،���ЃR�[�h:" + l_strInstitutionCode +
                ", ���X�R�[�h:" + l_strBranchCode +
                ", �����R�[�h:" + l_strAccountCode +
                ", ���ʃR�[�h:" + l_strOrderReqNumber + "]"
                );
            return WEB3HostStatusDef.DATA_ERROR;
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        l_accOpenAcceptParams.setStatus(WEB3HostStatusDef.COMPLETE_PROCESS);
        try
        {
            Processors.getDefaultProcessor().doUpdateQuery(l_accOpenAcceptParams);//DataException
        }
        catch (DataException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
        return WEB3HostStatusDef.COMPLETE_PROCESS;
    }
}
@
