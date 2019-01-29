head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInformAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �e��A����tUnitServiceImpl(WEB3AccOpenInformAcceptUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/05/28 �đo�g (���u) �V�K�쐬 ���f�� No.123,No.128�A�c�a�X�V�d�l No.028, No.029
Revision History : 2007/06/05 �đo�g (���u) ���f�� 132�A���f�� 133
Revision History : 2007/06/12 �đo�g (���u) ���f�� No.140�A���f�� 142
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInformAcceptUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.common.define.WEB3VoucherCreateStatusDef;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (�e��A����tUnitServiceImpl)<BR>
 * �e��A����t�P���T�[�r�X�����N���X<BR>
 * �i�g�����U�N�V��������=TX_CREATE_NEW�j<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AccOpenInformAcceptUnitServiceImpl implements WEB3AccOpenInformAcceptUnitService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AccOpenInformAcceptUnitServiceImpl.class);

    /**
     * (update�e��A���e�[�u��)<BR>
     * �e��A����t1�����������{����B<BR>
     * <BR>
     * �P�j�e��A���e�[�u����PK�X�V����B<BR>
     * <BR>
     * ���X�V���e�́ADB�X�V�d�l<BR>
     * �u�����E���z���`�[��t_�e��A���e�[�u��DB�X�V�d�l.xls�v ���Q��<BR>
     * <BR>
     * @@param l_hostAccOpenAcceptParams - (��t�L���[Params)<BR>
     * �����J�ݓ`�[�o�^��t�L���[Params<BR>
     * @@param l_variousInformParams - (�e��A��Params)<BR>
     * �e��A��Params<BR>
     * @@throws WEB3BaseException
     */
    private void updateVariousInform(
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams,
        VariousInformParams l_variousInformParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateVariousInform(HostAccOpenAcceptParams, "
            + "VariousInformParams)";
        log.entering(STR_METHOD_NAME);

        if (l_hostAccOpenAcceptParams == null || l_variousInformParams == null)
        {
            log.debug("�p�����[�^�l�s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�e��A���e�[�u����PK�X�V����B
        //���X�V���e�́ADB�X�V�d�l�u�����E���z���`�[��t_�e��A���e�[�u��DB�X�V�d�l.xls�v ���Q��
        //�`�[�쐬��
        //�����J�ݓ`�[�o�^��t�L���[.��t���ʂ� 1�F��t�����̏ꍇ 3�F��t����
        //�����J�ݓ`�[�o�^��t�L���[.��t���ʂ� 2�F�G���[�̏ꍇ  4�F��t�G���[
        if (WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE.equals(
            l_hostAccOpenAcceptParams.getAcceptStatus()))
        {
            l_variousInformParams.setStatus(WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE);
        }
        else if (WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(
            l_hostAccOpenAcceptParams.getAcceptStatus()))
        {
            l_variousInformParams.setStatus(WEB3VoucherCreateStatusDef.ACCEPT_ERROR);
        }

        //�G���[���R�R�[�h    �����J�ݓ`�[�o�^��t�L���[.�G���[�R�[�h
        l_variousInformParams.setErrorReasonCode(l_hostAccOpenAcceptParams.getErrorCode());

        //�`�[��M����   ���ݎ����i�V�X�e���^�C���X�^���v�j
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        l_variousInformParams.setReceiptTimestamp(l_tsSystemTime);

        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_queryProcesser.doUpdateQuery(l_variousInformParams);
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(), l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(), l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(), l_dqe);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify�e��A����t)<BR>
     * �e��A����t��ʒm����<BR>
     * <BR>
     * �P�jthis.update�e��A���e�[�u��()���R�[�����A�e��A����t1�����������{����B<BR>
     * <BR>
     * [����]<BR>
     * ��t�L���[Params�F ����.��t�L���[Params<BR>
     * �e��A��Params�F ����.�e��A��Params<BR>
     * <BR>
     * �Q�jthis.update��t�L���[()���R�[�����A�����J�ݓ`�[�o�^��t�L���[���X�V����B<BR>
     * <BR>
     * [����]<BR>
     * ��t�L���[Params�F ����.��t�L���[Params<BR>
     * �����敪�F ����.�����敪<BR>
     * <BR>
     * @@param l_hostAccOpenAcceptParams - (��t�L���[Params)<BR>
     * �����J�ݓ`�[�o�^��t�L���[Params<BR>
     * @@param l_variousInformParams - (�e��A��Params)<BR>
     * �e��A��Params<BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 1�F ������<BR>
     * 9�F �G���[<BR>
     * @@throws WEB3BaseException
     */
    public void notifyInformAccept(
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams,
        VariousInformParams l_variousInformParams,
        String l_strProcessDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyContactAccept(HostAccOpenAcceptParams, "
            + "VariousInformParams, String)";
        log.entering(STR_METHOD_NAME);

        //this.update�e��A���e�[�u��()���R�[�����A�e��A����t1�����������{����B
        //[����]
        //��t�L���[Params�F ����.��t�L���[Params
        //�e��A��Params�F ����.�e��A��Params
        this.updateVariousInform(l_hostAccOpenAcceptParams, l_variousInformParams);

        //this.update��t�L���[()���R�[�����A�����J�ݓ`�[�o�^��t�L���[���X�V����B
        //[����]
        //��t�L���[Params�F ����.��t�L���[Params
        //�����敪�F ����.�����敪
        this.updateAcceptQueue(l_hostAccOpenAcceptParams, l_strProcessDiv);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update��t�L���[)<BR>
     * �����J�ݓ`�[�o�^��t�L���[���X�V����<BR>
     * <BR>
     * �P�j�����J�ݓ`�[�o�^��t�L���[��PK�X�V����B<BR>
     * <BR>
     * ���X�V���e�́ADB�X�V�d�l<BR>
     * �u��t_�����J�ݓ`�[�o�^��t�L���[�e�[�u��.xls�v ���Q��<BR>
     * <BR>
     * @@param l_hostAccOpenAcceptParams - (�����J�ݓ`�[�o�^��t�L���[)<BR>
     * �����J�ݓ`�[�o�^��t�L���[<BR>
     * <BR>
     * ���@@�����J�ݓ`�[�o�^��t�L���[Params�N���X�́ADDL��莩����������B<BR>
     * <BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 1�F ������<BR>
     * 9�F �G���[<BR>
     * @@throws WEB3BaseException
     */
    private void updateAcceptQueue(
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams,
        String l_strProcessDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateHostAccept(HostAccOpenAcceptParams, String)";
        log.entering(STR_METHOD_NAME);

        if (l_hostAccOpenAcceptParams == null)
        {
            log.debug("�p�����[�^�l�s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�����J�ݓ`�[�o�^��t�L���[.�����敪 = ����.�����敪
        l_hostAccOpenAcceptParams.setStatus(l_strProcessDiv);
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_queryProcesser.doUpdateQuery(l_hostAccOpenAcceptParams);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(), l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(), l_dqe);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
