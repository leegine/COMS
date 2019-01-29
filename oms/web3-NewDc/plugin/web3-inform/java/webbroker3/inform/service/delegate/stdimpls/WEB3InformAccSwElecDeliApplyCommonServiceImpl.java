head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.45.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyCommonServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ؑցE�d�q��t�\�����ʃT�[�r�XImpl(WEB3InformAccSwElecDeliApplyCommonServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/07/18 �Ј���(���u) ���f���ENo.100
Revesion History : 2007/07/30 �Ј���(���u) ���f���ENo.103
Revesion History : 2007/08/29 ����(���u) ���f���ENo.106
Revesion History : 2007/08/30 ����(���u) ���f���ENo.107
Revesion History : 2007/09/19 �����F(���u) ���f���ENo.109 115
Revesion History : 2007/09/27 ��іQ(���u) ���f���ENo.117
Revesion History : 2007/09/28 �����F(���u) ���f���ENo.118
Revesion History : 2007/10/04 ����(SCS) ���f���ENo.119
Revesion History : 2009/02/16 �đo�g(���u) ���f���ENo.138
*/
package webbroker3.inform.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OnlyMobileOpenDivDef;
import webbroker3.common.define.WEB3PosReportDivDef;
import webbroker3.common.define.WEB3TaxTypeDivDef;
import webbroker3.common.define.WEB3TradingReportDivDef;
import webbroker3.common.define.WEB3VoucherCreateStatusDef;
import webbroker3.gentrade.WEB3GentradeEra;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.define.WEB3InformCapitalGainTaxAccOpenDivDef;
import webbroker3.inform.define.WEB3InformTaxTypeDef;
import webbroker3.inform.define.WEB3InformUserDataDef;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliAppDtInfo;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliApplyInfo;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyCommonService;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�����ؑցE�d�q��t�\�����ʃT�[�r�XImpl)<BR>
 * �����ؑցE�d�q��t�\�����ʃT�[�r�X�����N���X<BR>
 *
 * @@author �Ј���
 * @@version 1.0
 */
public class WEB3InformAccSwElecDeliApplyCommonServiceImpl implements WEB3InformAccSwElecDeliApplyCommonService
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
           WEB3InformAccSwElecDeliApplyCommonServiceImpl.class);

    /**
     * (validate�����ؑցE�d�q��t�\�����)<BR>
     * �����ؑցE�d�q��t�\�����̕ύX���e���`�F�b�N����B <BR>
     * <BR>
     * �P�j �u�ύX���ڗL���v�`�F�b�N <BR>
     *      �@@���`�F�b�N�Ώۍ��ځ� <BR>
     * �@@�@@�@@ �| ���o�C����p�����J�݋敪 <BR>
     * �@@�@@�@@ �| ����񍐏���t�敪 <BR>
     * �@@�@@�@@ �| ����c���񍐏���t�敪 <BR>
     * �@@�@@�@@ �| ����c���񍐏��쐬�����敪 <BR>
     * �@@�@@�@@ �| ����c���񍐏��a��؍쐬�t���O <BR>
     * �@@�@@�@@ �| ����c���񍐏��v�Z���쐬�t���O <BR>
     * �@@�@@�@@ �| �ŋ敪 <BR>
     * �@@�@@�@@ �| �ŋ敪�i���N�j <BR>
     * �@@�@@�@@ �| �M�p����ŋ敪 <BR>
     * �@@�@@�@@ �| �M�p����ŋ敪�i���N�j <BR>
     * �@@�@@�@@ �| ����Ǘ������J�݋敪 <BR>
     * <BR>
     * �P�|�P�j (����)�ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A <BR>
     * �@@�@@�@@�@@ �ꍀ�ڂ����ق��Ȃ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@ �G���[���b�Z�[�W�yBUSINESS_ERROR_02680�i�ύX���ڂ�����܂���B�j�z<BR>
     * ��\���B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02680<BR>
     * <BR>
     * �P�|�Q�j (����)�ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A <BR>
     * �@@�@@�@@�@@ �ꍀ�ڂł����ق�����ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * <BR>
     * �P�|�Q�|�P�j �Y������(����)�ύX����̍��ڂ��A[�S�Ė�����]�̏ꍇ�A<BR>
     * ��O���X���[����B <BR>
     *      �@@      �G���[���b�Z�[�W�yBUSINESS_ERROR_02688(�ύX���ږ����G���[)�z<BR>
     *      ��\���B <BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02688<BR>
     * <BR>
     * �Q�j �u���E��c�ύX���ڗL���v�`�F�b�N <BR>
     *      �@@���`�F�b�N�Ώۍ��ځ� <BR>
     * �@@�@@�@@ �| ����񍐏���t�敪 <BR>
     * �@@�@@�@@ �| ����c���񍐏���t�敪 <BR>
     * �@@�@@�@@ �| ����c���񍐏��쐬�����敪 <BR>
     * �@@�@@�@@ �| ����c���񍐏��a��؍쐬�t���O <BR>
     * �@@�@@�@@ �| ����c���񍐏��v�Z���쐬�t���O <BR>
     * <BR>
     * �Q�|�P�j (����)�ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A <BR>
     *          �ꍀ�ڂł����ق�����ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * <BR>
     * �Q�|�P�|�P�j �Y������(����)�ύX����̍��ڂ� <BR>
     *      �@@      [�S�Ė����́A���́A�S�ē���]�ȊO�̏ꍇ�A��O���X���[����B <BR>
     *      �@@      �G���[���b�Z�[�W�y�r�W�l�X�G���[�w���E��c�ύX���ږ����G���[�x�z<BR>
     *      ��\���B <BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02872<BR>
     * <BR>
     * �R�j �ŋ敪�ύX���ڗL���`�F�b�N <BR>
     *      �@@���`�F�b�N�Ώۍ��ځ� <BR>
     * �@@�@@�@@ �| �ŋ敪 <BR>
     * �@@�@@�@@ �| �ŋ敪�i���N�j <BR>
     * <BR>
     * �R�|�P�j (����)�ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A <BR>
     *          �ꍀ�ڂł����ق�����ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * <BR>
     * �R�|�P�|�P�j �Y������(����)�ύX����̍��ڂ� <BR>
     *      �@@      [�S�Ė����́A���́A�S�ē���]�ȊO�̏ꍇ�A��O���X���[����B <BR>
     *      �@@      �G���[���b�Z�[�W�y�r�W�l�X�G���[�w�ŋ敪�ύX���ږ����G���[�x�z<BR>
     *      ��\���B <BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02873<BR>
     * <BR>
     * �S�j �u�M�p����ŋ敪�ύX���ڗL���v�`�F�b�N <BR>
     *      �@@���`�F�b�N�Ώۍ��ځ� <BR>
     * �@@�@@�@@ �| �M�p����ŋ敪 <BR>
     * �@@�@@�@@ �| �M�p����ŋ敪�i���N�j <BR>
     * <BR>
     * �S�|�P�j (����)�ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A <BR>
     *          �ꍀ�ڂł����ق�����ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * <BR>
     * �S�|�P�|�P�j �Y������(����)�ύX����̍��ڂ� <BR>
     *      �@@      [�S�Ė����́A���́A�S�ē���]�ȊO�̏ꍇ�A��O���X���[����B <BR>
     *      �@@      �G���[���b�Z�[�W�y�r�W�l�X�G���[�w�M�p����ŋ敪�ύX���ږ����G���[�x�z<BR>
     *      ��\���B<BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02874<BR>
     * <BR>
     * �T�j �u������������v�`�F�b�N <BR>
     *      �@@���`�F�b�N�Ώۍ��ڇ@@�� <BR>
     * �@@�@@�@@ �| �ŋ敪 <BR>
     * �@@�@@�@@ �| �M�p����ŋ敪 <BR>
     *      �@@���`�F�b�N�Ώۍ��ڇA�� <BR>
     * �@@�@@�@@ �| �ŋ敪�i���N�j <BR>
     * �@@�@@�@@ �| �M�p����ŋ敪�i���N�j <BR>
     * <BR>
     * �T�|�P�j (����)�ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ڇ@@�����r���A <BR>
     *          �ꍀ�ڂł����ق�����ꍇ�A���A <BR>
     *          (����)�ύX���񂪉��L�̂����ꂩ�ɊY������ꍇ�A��O���X���[����B <BR>
     *      �@@  �G���[���b�Z�[�W�y�r�W�l�X�G���[�w��������G���[�x�z��\���B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02875<BR>
     * <BR>
     * �T�|�P�|�P�j �ŋ敪 == 2�F���� �̏ꍇ�A <BR>
     *              �M�p����ŋ敪 != (1�F��ʁA���́A2�F����A���́Anull)  <BR>
     * <BR>
     * �T�|�P�|�Q�j �ŋ敪 == 3�F������������򒥎� �̏ꍇ�A <BR>
     *              �M�p����ŋ敪 != (1�F��ʁA���́A3�F������������򒥎��A<BR>
     *              ���́Anull)  <BR>
     * <BR>
     * �T�|�Q�j (����)�ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ڇA�����r���A <BR>
     *          �ꍀ�ڂł����ق�����ꍇ�A���A <BR>
     *          (����)�ύX���񂪉��L�̂����ꂩ�ɊY������ꍇ�A��O���X���[����B <BR>
     *          �G���[���b�Z�[�W�y�r�W�l�X�G���[�w��������G���[�x�z��\���B <BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02875<BR>
     * <BR>
     * �T�|�Q�|�P�j �ŋ敪�i���N�j == 2�F���� �̏ꍇ�A <BR>
     *              �M�p����ŋ敪�i���N�j != (1�F��ʁA���́A2�F����A���́Anull)  <BR>
     * <BR>
     * �T�|�Q�|�Q�j �ŋ敪�i���N�j == 3�F������������򒥎� �̏ꍇ�A <BR>
     *              �M�p����ŋ敪�i���N�j != (1�F��ʁA���́A<BR>
     *              3�F������������򒥎��A���́Anull)  <BR>
     * <BR>
     * �U�j �u����Ǘ����������v�`�F�b�N <BR>
     *      �@@���`�F�b�N�Ώۍ��ځ� <BR>
     * �@@�@@�@@ �| �ŋ敪 <BR>
     * �@@�@@�@@ �| �M�p����ŋ敪 <BR>
     * �@@�@@�@@ �| ����Ǘ������J�݋敪 <BR>
     * <BR>
     * �U�|�P�j (����)�ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A<BR>
     *          �ꍀ�ڂł����ق�����ꍇ�A���A <BR>
     *          (����)�ύX���񂪉��L�ɊY������ꍇ�A��O���X���[����B <BR>
     *      �@@  �G���[���b�Z�[�W�y�r�W�l�X�G���[�w����Ǘ������G���[�x�z��\���B<BR>
     * <BR>
     * �U�|�P�|�P�j �ŋ敪 == 1�F��ʁA���A�M�p����ŋ敪 == 1�F��� �̏ꍇ�A<BR>
     *              ����Ǘ������J�݋敪 == 1�F�J�� <BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02876<BR>
     * <BR>
     * �V�j �u���������v�`�F�b�N   <BR>
     *      �@@���`�F�b�N�Ώۍ��ځ� <BR>
     * �@@�@@�@@ �| ���o�C����p�����J�݋敪 <BR>
     * �@@�@@�@@ �| ����񍐏���t�敪 <BR>
     * �@@�@@�@@ �| ����c���񍐏���t�敪 <BR>
     * <BR>
     * �V�|�P�j (����)�ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A<BR>
     *          �ꍀ�ڂł����ق�����ꍇ�A���A <BR>
     *          (����)�ύX���񂪉��L�ɊY������ꍇ�A��O���X���[����B <BR>
     *      �@@  �G���[���b�Z�[�W�y�r�W�l�X�G���[�w�d�q��t���ӃG���[�x�z��\���B <BR>
     * <BR>
     * �V�|�P�|�P�j ���o�C����p�����J�݋敪 == 1�F�J�� �̏ꍇ�A <BR>
     *              ����񍐏���t�敪 == 1�F�d�q�z�z�A���́A<BR>
     *              ����c���񍐏���t�敪 == 9�F�d�q�z�z<BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02877<BR>
     * @@param l_beforeChangedInfo - (�ύX�O���)<BR>
     * �����ؑցE�d�q��t�\�����I�u�W�F�N�g
     * @@param l_changedInfo - (�ύX����)<BR>
     * �����ؑցE�d�q��t�\�����I�u�W�F�N�g
     * @@throws WEB3BaseException
     */
    public void validateAccSwitchElecDeliApplyInfo(
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo,
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccSwitchElecDeliApplyInfo("
            + "WEB3AdminInformAccSwitchElecDeliApplyInfo, "
            + "MainAccount)";

        log.entering(STR_METHOD_NAME);

        if (l_beforeChangedInfo == null)
        {
            log.debug("�ύX���ږ����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02688,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ύX���ږ����G���[�B");
        }

        if (l_changedInfo == null)
        {
            log.debug("�ύX���ږ����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02688,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ύX���ږ����G���[�B");
        }

        // �P�j �u�ύX���ڗL���v�`�F�b�N
        // ���`�F�b�N�Ώۍ��ځ�
        // �| ���o�C����p�����J�݋敪
        // �| ����񍐏���t�敪
        // �| ����c���񍐏���t�敪
        // �| ����c���񍐏��쐬�����敪
        // �| ����c���񍐏��a��؍쐬�t���O
        // �| ����c���񍐏��v�Z���쐬�t���O
        // �| �ŋ敪
        // �| �ŋ敪�i���N�j
        // �| �M�p����ŋ敪
        // �| �M�p����ŋ敪�i���N�j
        // �| ����Ǘ������J�݋敪
        // �P�|�P�j �ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A
        // �@@�@@�@@�ꍀ�ڂ����ق��Ȃ��ꍇ�A��O���X���[����B
        // �@@�@@�@@�G���[���b�Z�[�W�yBUSINESS_ERROR_02680�i�ύX���ڂ�����܂���B�j�z��\���B
        if (WEB3Toolkit.isEquals(l_beforeChangedInfo.mobileAccoutDiv, l_changedInfo.mobileAccoutDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.tradingReportDiv, l_changedInfo.tradingReportDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.positionReportDiv, l_changedInfo.positionReportDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.positionReportCycleDiv, l_changedInfo.positionReportCycleDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.certificateDepositDiv, l_changedInfo.certificateDepositDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.accountStatementDiv, l_changedInfo.accountStatementDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.taxType, l_changedInfo.taxType)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.taxTypeNext, l_changedInfo.taxTypeNext)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.marginTaxType, l_changedInfo.marginTaxType)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.marginTaxTypeNext, l_changedInfo.marginTaxTypeNext)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.capitalGainTaxAccOpenDiv, l_changedInfo.capitalGainTaxAccOpenDiv))
        {
            log.debug("�ύX���ڂ�����܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02680,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ύX���ڂ�����܂���B");
        }

        // �P�|�Q�j �ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A
        // �@@�@@�@@�ꍀ�ڂł����ق�����ꍇ�A�ȉ��̃`�F�b�N���s���B
        // �P�|�Q�|�P�j �Y������(����)�ύX����̍��ڂ��A[�S�Ė�����]�̏ꍇ�A��O���X���[����B
        // �@@�@@�@@�@@�@@�@@�G���[���b�Z�[�W�yBUSINESS_ERROR_02688(�ύX���ږ����G���[)�z��\���B
        if (l_changedInfo.mobileAccoutDiv == null
            && l_changedInfo.tradingReportDiv == null
            && l_changedInfo.positionReportDiv == null
            && l_changedInfo.positionReportCycleDiv == null
            && l_changedInfo.certificateDepositDiv == null
            && l_changedInfo.accountStatementDiv == null
            && l_changedInfo.taxType == null
            && l_changedInfo.taxTypeNext == null
            && l_changedInfo.marginTaxType == null
            && l_changedInfo.marginTaxTypeNext == null
            && l_changedInfo.capitalGainTaxAccOpenDiv == null)
        {
            log.debug("�ύX���ږ����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02688,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ύX���ږ����G���[�B");
        }

        // �Q�j �u���E��c�ύX���ڗL���v�`�F�b�N�A
        //        �| ����񍐏���t�敪
        //        �| ����c���񍐏���t�敪
        //        �| ����c���񍐏��쐬�����敪
        //        �| ����c���񍐏��a��؍쐬�t���O
        //        �| ����c���񍐏��v�Z���쐬�t��
        // �Q�|�P�j �ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A
        // �@@�@@�@@�ꍀ�ڂł����ق�����ꍇ�A�ȉ��̃`�F�b�N���s���B
        // �Q�|�P�|�P�j �Y������(����)�ύX����̍��ڂ�
        // [�S�Ė����́A���́A�S�ē���]�ȊO�̏ꍇ�A��O���X���[����B
        // �G���[���b�Z�[�W�y�r�W�l�X�G���[�w���E��c�ύX���ږ����G���[�x�z��\���B
        if (!(WEB3Toolkit.isEquals(l_beforeChangedInfo.tradingReportDiv, l_changedInfo.tradingReportDiv)
           && WEB3Toolkit.isEquals(l_beforeChangedInfo.positionReportDiv, l_changedInfo.positionReportDiv)
           && WEB3Toolkit.isEquals(l_beforeChangedInfo.positionReportCycleDiv, l_changedInfo.positionReportCycleDiv)
           && WEB3Toolkit.isEquals(l_beforeChangedInfo.certificateDepositDiv, l_changedInfo.certificateDepositDiv)
           && WEB3Toolkit.isEquals(l_beforeChangedInfo.accountStatementDiv, l_changedInfo.accountStatementDiv)))
        {
            if (!((l_changedInfo.tradingReportDiv == null
                && l_changedInfo.positionReportDiv == null
                && l_changedInfo.positionReportCycleDiv == null
                && l_changedInfo.certificateDepositDiv == null
                && l_changedInfo.accountStatementDiv == null)
                || (l_changedInfo.tradingReportDiv != null
                && l_changedInfo.positionReportDiv != null
                && l_changedInfo.positionReportCycleDiv != null
                && l_changedInfo.certificateDepositDiv != null
                && l_changedInfo.accountStatementDiv != null)))
            {
                log.debug("�r�W�l�X�G���[�w���E��c�ύX���ږ����G���[�x�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02872,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�r�W�l�X�G���[�w���E��c�ύX���ږ����G���[�x�B");
            }
        }

        // �R�j �ŋ敪�ύX���ڗL���`�F�b�N�B
        // �@@�@@���`�F�b�N�Ώۍ��ځ�
        //     �| �ŋ敪
        //     �| �ŋ敪�i���N�j
        // �R�|�P�j �ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A
        // �@@�@@�@@�ꍀ�ڂł����ق�����ꍇ�A�ȉ��̃`�F�b�N���s���B
        // �R�|�P�|�P�j �Y������(����)�ύX����̍��ڂ�
        // [�S�Ė����́A���́A�S�ē���]�ȊO�̏ꍇ�A��O���X���[����B
        // �G���[���b�Z�[�W�y�r�W�l�X�G���[�w�ŋ敪�ύX���ږ����G���[�x�z��\���B
        if (!(WEB3Toolkit.isEquals(l_beforeChangedInfo.taxType, l_changedInfo.taxType)
           && WEB3Toolkit.isEquals(l_beforeChangedInfo.taxTypeNext, l_changedInfo.taxTypeNext)))
        {
            if (!((l_changedInfo.taxType == null && l_changedInfo.taxTypeNext == null)
                || (l_changedInfo.taxType != null && l_changedInfo.taxTypeNext != null)))
            {
                log.debug("�r�W�l�X�G���[�w�ŋ敪�ύX���ږ����G���[�x�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02873,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�r�W�l�X�G���[�w�ŋ敪�ύX���ږ����G���[�x�B");
            }
        }

        // �S�j �u�M�p����ŋ敪�ύX���ڗL���v�`�F�b�N
        //        �| �M�p����ŋ敪
        //        �| �M�p����ŋ敪�i���N�j
        // �S�|�P�j �ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A
        // �@@�@@�@@�ꍀ�ڂł����ق�����ꍇ�A�ȉ��̃`�F�b�N���s���B
        // �S�|�P�|�P�j �Y������(����)�ύX����̍��ڂ�
        // [�S�Ė����́A���́A�S�ē���]�ȊO�̏ꍇ�A��O���X���[����B
        // �G���[���b�Z�[�W�y�r�W�l�X�G���[�w�M�p����ŋ敪�ύX���ږ����G���[�x�z��\���B
        if (!(WEB3Toolkit.isEquals(l_beforeChangedInfo.marginTaxType, l_changedInfo.marginTaxType)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.marginTaxTypeNext, l_changedInfo.marginTaxTypeNext)))
        {
            if (!((l_changedInfo.marginTaxType == null && l_changedInfo.marginTaxTypeNext == null)
                || (l_changedInfo.marginTaxType != null && l_changedInfo.marginTaxTypeNext != null)))
            {
                log.debug("�r�W�l�X�G���[�w�M�p����ŋ敪�ύX���ږ����G���[�x�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02874,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�r�W�l�X�G���[�w�M�p����ŋ敪�ύX���ږ����G���[�x�B");
            }
        }

        // �T�j �u������������v�`�F�b�N
        // �@@�@@�@@���`�F�b�N�Ώۍ��ڇ@@��
        // �@@�@@�@@�| �ŋ敪
        // �@@�@@�@@�| �M�p����ŋ敪
        // �@@�@@�@@���`�F�b�N�Ώۍ��ڇA��
        // �@@�@@�@@�| �ŋ敪�i���N�j
        // �@@�@@�@@�| �M�p����ŋ敪�i���N�j
        // �T�|�P�j �ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ڇ@@�����r���A
        // �@@�@@�@@�ꍀ�ڂł����ق�����ꍇ�A���A
        // �@@�@@�@@(����)�ύX���񂪉��L�̂����ꂩ�ɊY������ꍇ�A��O���X���[����B
        // �@@�@@�@@�G���[���b�Z�[�W�y�r�W�l�X�G���[�w��������G���[�x�z��\���B
        // �T�|�P�|�P�j �ŋ敪 == 2�F���� �̏ꍇ�A
        // �@@�@@�@@�@@�@@�M�p����ŋ敪 != (1�F��ʁA���́A2�F����A���́Anull)
        if (!(WEB3Toolkit.isEquals(l_beforeChangedInfo.taxType, l_changedInfo.taxType)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.marginTaxType, l_changedInfo.marginTaxType)))
        {
            if (WEB3InformTaxTypeDef.SPECIAL.equals(l_changedInfo.taxType))
            {
                if (!(WEB3InformTaxTypeDef.NORMAL.equals(l_changedInfo.marginTaxType))
                    && !(WEB3InformTaxTypeDef.SPECIAL.equals(l_changedInfo.marginTaxType))
                    && l_changedInfo.marginTaxType != null)
                {
                    log.debug("�r�W�l�X�G���[�w��������G���[�x�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02875,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�r�W�l�X�G���[�w��������G���[�x�B");
                }
            }

            // �T�|�P�|�Q�j �ŋ敪 == 3�F������������򒥎� �̏ꍇ�A
            // �@@�@@�@@�@@�@@�M�p����ŋ敪 != (1�F��ʁA���́A3�F������������򒥎��A���́Anull)
            if (WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_changedInfo.taxType))
            {
                if (!(WEB3InformTaxTypeDef.NORMAL.equals(l_changedInfo.marginTaxType))
                    && !(WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_changedInfo.marginTaxType))
                    && l_changedInfo.marginTaxType != null)
                {
                    log.debug("�r�W�l�X�G���[�w��������G���[�x�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02875,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�r�W�l�X�G���[�w��������G���[�x�B");
                }
            }
        }

        // �T�|�Q�j �ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ڇ@@�����r���A
        // �@@�@@�@@�ꍀ�ڂł����ق�����ꍇ�A���A
        // �@@�@@�@@(����)�ύX���񂪉��L�̂����ꂩ�ɊY������ꍇ�A��O���X���[����B
        // �@@�@@�@@�G���[���b�Z�[�W�y�r�W�l�X�G���[�w��������G���[�x�z��\���B
        // �T�|�Q�|�P�j �ŋ敪�i���N�j == 2�F���� �̏ꍇ�A
        // �@@�@@�@@�@@�@@�M�p����ŋ敪�i���N�j != (1�F��ʁA���́A2�F����A���́Anull)
        if (!(WEB3Toolkit.isEquals(l_beforeChangedInfo.taxTypeNext, l_changedInfo.taxTypeNext)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.marginTaxTypeNext, l_changedInfo.marginTaxTypeNext)))
        {
            if (WEB3InformTaxTypeDef.SPECIAL.equals(l_changedInfo.taxTypeNext))
            {
                if (!(WEB3InformTaxTypeDef.NORMAL.equals(l_changedInfo.marginTaxTypeNext))
                    && !(WEB3InformTaxTypeDef.SPECIAL.equals(l_changedInfo.marginTaxTypeNext))
                    && l_changedInfo.marginTaxTypeNext != null)
                {
                    log.debug("�r�W�l�X�G���[�w��������G���[�x�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02875,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�r�W�l�X�G���[�w��������G���[�x�B");
                }
            }

            // �T�|�Q�|�Q�j �ŋ敪�i���N�j == 3�F������������򒥎� �̏ꍇ�A
            // �@@�@@�@@�@@�@@�M�p����ŋ敪�i���N�j != (1�F��ʁA���́A3�F������������򒥎��A���́Anull)
            if (WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_changedInfo.taxTypeNext))
            {
                if (!(WEB3InformTaxTypeDef.NORMAL.equals(l_changedInfo.marginTaxTypeNext))
                    && !(WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_changedInfo.marginTaxTypeNext))
                    && l_changedInfo.marginTaxTypeNext != null)
                {
                    log.debug("�r�W�l�X�G���[�w��������G���[�x�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02875,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�r�W�l�X�G���[�w��������G���[�x�B");
                }
            }
        }

        // �U�j �u����Ǘ����������v�`�F�b�N
        // �@@�@@�@@�@@���`�F�b�N�Ώۍ��ځ�
        // �@@�@@�@@�@@�| �ŋ敪
        // �@@�@@�@@�@@�| �M�p����ŋ敪
        // �@@�@@�@@�@@�| ����Ǘ������J�݋敪
        // �U�|�P�j �ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A
        // �@@�@@�@@�ꍀ�ڂł����ق�����ꍇ�A���A
        // �@@�@@�@@(����)�ύX���񂪉��L�̂����ꂩ�ɊY������ꍇ�A��O���X���[����B
        // �@@�@@�@@�G���[���b�Z�[�W�y�r�W�l�X�G���[�w����Ǘ������G���[�x�z��\���B
        // �U�|�P�|�P�j �ŋ敪 == 1�F��ʁA���A�M�p����ŋ敪 == 1�F��� �̏ꍇ�A
        // �@@�@@�@@�@@�@@����Ǘ������J�݋敪 == 1�F�J��
        if (!(WEB3Toolkit.isEquals(l_beforeChangedInfo.taxType, l_changedInfo.taxType)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.marginTaxType, l_changedInfo.marginTaxType)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.capitalGainTaxAccOpenDiv, l_changedInfo.capitalGainTaxAccOpenDiv)))
        {
            if (WEB3InformTaxTypeDef.NORMAL.equals(l_changedInfo.taxType)
                && WEB3InformTaxTypeDef.NORMAL.equals(l_changedInfo.marginTaxType))
            {
                if (WEB3InformCapitalGainTaxAccOpenDivDef.OPEN.equals(l_changedInfo.capitalGainTaxAccOpenDiv))
                {
                    log.debug("�r�W�l�X�G���[�w����Ǘ������G���[�x�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02876,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�r�W�l�X�G���[�w����Ǘ������G���[�x�B");
                }
            }
        }

        // �V�j �u���������v�`�F�b�N
        // �@@�@@�@@�@@���`�F�b�N�Ώۍ��ځ�
        // �@@�@@�@@�@@�| ���o�C����p�����J�݋敪
        // �@@�@@�@@�@@�| ����񍐏���t�敪
        // �@@�@@�@@�@@�| ����c���񍐏���t�敪
        // �V�|�P�j �ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A
        // �@@�@@�@@�ꍀ�ڂł����ق�����ꍇ�A���A
        // �@@�@@�@@(����)�ύX���񂪉��L�̂����ꂩ�ɊY������ꍇ�A��O���X���[����B
        // �@@�@@�@@�G���[���b�Z�[�W�y�r�W�l�X�G���[�w�d�q��t���ӃG���[�x�z��\���B
        // �V�|�P�|�P�j ���o�C����p�����J�݋敪 == 1�F�J�� �̏ꍇ�A
        // �@@�@@�@@�@@�@@����񍐏���t�敪 == 1�F�d�q�z�z�A���́A����c���񍐏���t�敪 == 9�F�d�q�z�z
        if (!(WEB3Toolkit.isEquals(l_beforeChangedInfo.mobileAccoutDiv, l_changedInfo.mobileAccoutDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.tradingReportDiv, l_changedInfo.tradingReportDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.positionReportDiv, l_changedInfo.positionReportDiv)))
        {
            if (WEB3OnlyMobileOpenDivDef.OPEN.equals(l_changedInfo.mobileAccoutDiv))
            {
                if (WEB3TradingReportDivDef.ELECTRON_DISTRIBUTION.equals(l_changedInfo.tradingReportDiv)
                    || WEB3PosReportDivDef.ELECTRON_DISTRIBUTION.equals(l_changedInfo.positionReportDiv))
                {
                    log.debug("�r�W�l�X�G���[�w�d�q��t���ӃG���[�x�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02877,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�r�W�l�X�G���[�w�d�q��t���ӃG���[�x�B");
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�e��A��)<BR>
     * �e��A���V�K�s�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g���� <BR>
     * �@@�e��A��Params�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@���e��A��Params��DDL��莩����������B<BR>
     * <BR>
     * �Q�j�@@�ڋq.getDataSourceObject()�ɂČڋq�s���擾����B<BR>
     * <BR>
     * �R�j�@@�s�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B<BR>
     * �@@�P�j�Ő��������e��A��Params�I�u�W�F�N�g�̃v���p�e�B�ɁA<BR>
     * �@@�@@�@@�ȉ��̒ʂ�Z�b�g���s���B<BR>
     * <BR>
     * �@@�e��A��Params.�،���ЃR�[�h �� �ڋq�s.�،���ЃR�[�h <BR>
     * �@@�e��A��Params.�A����� �� (����)�A����� <BR>
     * �@@�e��A��Params.���X�R�[�h �� �ڋq�s.���X�R�[�h<BR>
     * �@@�e��A��Params.�ڋq�R�[�h �� �ڋq�s.�����R�[�h <BR>
     * �@@�e��A��Params.�ڋq�� �� �ڋq�s.���O�i�c���j <BR>
     * �@@�e��A��Params.�ڋq���[���A�h���X �� �ڋq�s.email�A�h���X<BR>
     * �@@�e��A��Params.�敪�P �� (����)�ύX����.���o�C����p�����J�݋敪<BR>
     * �@@�e��A��Params.�敪�Q �� (����)�ύX����.����񍐏���t�敪 <BR>
     * �@@�e��A��Params.�敪�R �� (����)�ύX����.����c���񍐏���t�敪 <BR>
     * �@@�e��A��Params.�敪�S �� (����)�ύX����.����c���񍐏��쐬�����敪 <BR>
     * �@@�e��A��Params.�敪�T �� (����)�ύX����.����c���񍐏��a��؍쐬�t���O <BR>
     * �@@�e��A��Params.�敪�U �� (����)�ύX����.����c���񍐏��v�Z���쐬�t���O <BR>
     * �@@�e��A��Params.�敪�V �� (����)�ύX����.�ŋ敪 <BR>
     * �@@�e��A��Params.�敪�W �� (����)�ύX����.�ŋ敪�i���N�j<BR>
     * �@@�e��A��Params.�敪�X �� (����)�ύX����.�M�p����ŋ敪 <BR>
     * �@@�e��A��Params.�敪�P�O �� (����)�ύX����.�M�p����ŋ敪�i���N�j<BR>
     * �@@�e��A��Params.�敪�P�P �� (����)�ύX����.����Ǘ������J�݋敪 <BR>
     * �@@�e��A��Params.�敪�P�Q �� �ڋq�s.���o�C����p�����J�݋敪<BR>
     * �@@�e��A��Params.�e�L�X�g�P �� (����)���t���.�\������ <BR>
     * �@@�e��A��Params.�e�L�X�g�Q �� (����)���t���.�J�n�\���<BR>
     * �@@�e��A��Params.�e�L�X�g�R �� (����)���t���.�ŋ敪�J�ݓ�<BR>
     * �@@�e��A��Params.�e�L�X�g�S �� (����)���t���.�M�p����ŋ敪�J�ݓ�<BR>
     * �@@�e��A��Params.�e�L�X�g�T �� �ڋq�s.���o�C����p�����J�݋敪�X�V�҃R�[�h <BR>
     * �@@�e��A��Params.�e�L�X�g�U �� �ڋq�s.���o�C����p�����J�݋敪�X�V���� <BR>
     * �@@��null�ȊO�̏ꍇ�A�fyyyymmddhhmmss�f�^�ɕϊ��B<BR>
     * <BR>
     * �S�j�@@�e��A���I�u�W�F�N�g�ԋp<BR>
     * �@@�s�I�u�W�F�N�g���w�肵�A�e��A���I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g
     * @@param l_changedInfo - (�ύX����)<BR>
     * �ύX����
     * @@param l_dateInfo - (���t���)<BR>
     * ���t���
     * @@param l_strInformType - (�A�����)<BR>
     * �A�����
     * @@return WEB3Inform
     * @@throws WEB3BaseException
     */
    public WEB3Inform createVariousInform(
        MainAccount l_mainAccount,
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo,
        WEB3AdminInformAccSwitchElecDeliAppDtInfo l_dateInfo,
        String l_strInformType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createVariousInform(MainAccount, "
            + "WEB3AdminInformAccSwitchElecDeliApplyInfo, "
            + "WEB3AdminInformAccSwitchElecDeliAppDtInfo, "
            + "String)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null
            || l_changedInfo == null
            || l_dateInfo == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^Null�o���Ȃ��B");
        }

        //�e��A��Params�I�u�W�F�N�g�𐶐�����
        VariousInformParams l_variousInformParams = new VariousInformParams();

        //�ڋq.getDataSourceObject()�ɂĕύX�O�f�[�^���擾����B
        MainAccountRow l_mainAcountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //�e��A��Params.�،���ЃR�[�h �� �ڋq�s.�،���ЃR�[�h
        l_variousInformParams.setInstitutionCode(l_mainAcountRow.getInstitutionCode());

        //�e��A��Params.�A����� �� (����)�A�����
        l_variousInformParams.setInformDiv(l_strInformType);

        //�e��A��Params.���X�R�[�h �� �ڋq�s.���X�R�[�h
        l_variousInformParams.setBranchCode(l_mainAcountRow.getBranchCode());

        //�e��A��Params.�ڋq�R�[�h �� �ڋq�s.�����R�[�h
        l_variousInformParams.setAccountCode(l_mainAcountRow.getAccountCode());

        //�e��A��Params.�ڋq�� �� �ڋq�s.���O�i�c���j
        l_variousInformParams.setAccountName(l_mainAcountRow.getFamilyName());

        //�e��A��Params.�ڋq���[���A�h���X �� �ڋq�s.email�A�h���X
        l_variousInformParams.setEmailAddress(l_mainAcountRow.getEmailAddress());

        //�e��A��Params.�敪�P �� (����)�ύX����.���o�C����p�����J�݋敪
        l_variousInformParams.setExtDiv1(l_changedInfo.mobileAccoutDiv);

        //�e��A��Params.�敪�Q �� (����)�ύX����.����񍐏���t�敪
        l_variousInformParams.setExtDiv2(l_changedInfo.tradingReportDiv);

        //�e��A��Params.�敪�R �� (����)�ύX����.����c���񍐏���t�敪
        l_variousInformParams.setExtDiv3(l_changedInfo.positionReportDiv);

        //�e��A��Params.�敪�S �� (����)�ύX����.����c���񍐏��쐬�����敪
        l_variousInformParams.setExtDiv4(l_changedInfo.positionReportCycleDiv);

        //�e��A��Params.�敪�T �� (����)�ύX����.����c���񍐏��a��؍쐬�t���O
        l_variousInformParams.setExtDiv5(l_changedInfo.certificateDepositDiv);

        //�e��A��Params.�敪�U �� (����)�ύX����.����c���񍐏��v�Z���쐬�t���O
        l_variousInformParams.setExtDiv6(l_changedInfo.accountStatementDiv);

        //�e��A��Params.�敪�V �� (����)�ύX����.�ŋ敪
        l_variousInformParams.setExtDiv7(l_changedInfo.taxType);

        //�e��A��Params.�敪�W �� (����)�ύX����.�ŋ敪�i���N�j
        l_variousInformParams.setExtDiv8(l_changedInfo.taxTypeNext);

        //�e��A��Params.�敪�X �� (����)�ύX����.�M�p����ŋ敪
        l_variousInformParams.setExtDiv9(l_changedInfo.marginTaxType);

        //�e��A��Params.�敪�P�O �� (����)�ύX����.�M�p����ŋ敪�i���N�j
        l_variousInformParams.setExtDiv10(l_changedInfo.marginTaxTypeNext);

        //�e��A��Params.�敪�P�P �� (����)�ύX����.����Ǘ������J�݋敪
        l_variousInformParams.setExtDiv11(l_changedInfo.capitalGainTaxAccOpenDiv);

        //�e��A��Params.�敪�P�Q �� �ڋq�s.���o�C����p�����J�݋敪
        l_variousInformParams.setExtDiv12(l_mainAcountRow.getOnlyMobileOpenDiv());

        //�e��A��Params.�e�L�X�g�P �� (����)���t���.�\������
        l_variousInformParams.setExtText1(WEB3DateUtility.formatDate(
            l_dateInfo.applyDate,
            WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));

        //�e��A��Params.�e�L�X�g�Q �� (����)���t���.�J�n�\���
        l_variousInformParams.setExtText2(WEB3DateUtility.formatDate(
            l_dateInfo.startScheduleDate,
            WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));

        //�e��A��Params.�e�L�X�g�R �� (����)���t���.�ŋ敪�J�ݓ�
        l_variousInformParams.setExtText3(l_dateInfo.taxTypeOpenDate);

        //�e��A��Params.�e�L�X�g�S �� (����)���t���.�M�p����ŋ敪�J�ݓ�
        l_variousInformParams.setExtText4(l_dateInfo.marginTaxTypeOpenDate);

        //�e��A��Params.�e�L�X�g�T �� �ڋq�s.���o�C����p�����J�݋敪�X�V�҃R�[�h
        l_variousInformParams.setExtText5(l_mainAcountRow.getOnlyMblOpnDivLastUpdater());

        //�e��A��Params.�e�L�X�g�U �� �ڋq�s.���o�C����p�����J�݋敪�X�V����
        //null�ȊO�̏ꍇ�A�fyyyymmddhhmmss�f�^�ɕϊ��B
        if (l_mainAcountRow.getOnlyMblOpnDivTimestamp() != null)
        {
            l_variousInformParams.setExtText6(
                WEB3DateUtility.formatDate(l_mainAcountRow.getOnlyMblOpnDivTimestamp(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
        }

        //�e��A���I�u�W�F�N�g�ԋp
        WEB3Inform l_inform = new WEB3Inform(l_variousInformParams);

        log.exiting(STR_METHOD_NAME);
        return l_inform;
    }

    /**
     * (create�����ؑցE�d�q��t�\�����t���)<BR>
     * �����ؑցE�d�q��t�\�����t��񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j �����ؑցE�d�q��t�\�����t���I�u�W�F�N�g�𐶐����� <BR>
     * <BR>
     * �Q�j ������ԊǗ�.get�������ɂĔ��������擾����B<BR>
     * <BR>
     * �R�j �Q�j����ɘa��ϊ��������t���擾����B<BR>
     * �@@�@@�N��.toJapDate()�ɂĕϊ������a��iYYMMDD�`���j������i=index[1])�B<BR>
     * <BR>
     * �S�j �ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �E�����ؑցE�d�q��t�\�����t���.�\������ �� ���ݓ���<BR>
     * ��GtlUtils.getSystemTimestamp()�ɂĎ擾 <BR>
     * �E�����ؑցE�d�q��t�\�����t���.�J�n�\��� �� �Q�j�Ŏ擾�������� <BR>
     * �E�����ؑցE�d�q��t�\�����t���.�ŋ敪�J�ݓ� �� �ȉ��̒ʂ�Z�b�g <BR>
     * �@@�@@�|(����)�ŋ敪 == 2�F����A���́A3�F������������򒥎� �̏ꍇ�A<BR>
     * �@@�@@�R�j�Ŏ擾�����a����� <BR>
     * �E�����ؑցE�d�q��t�\�����t���.�M�p����ŋ敪�J�ݓ� ���ȉ��̒ʂ�Z�b�g <BR>
     * �@@�@@�|(����)�M�p����ŋ敪 == 2�F����A���́A3�F������������򒥎� �̏ꍇ�A<BR>
     * �@@�@@�R�j�Ŏ擾�����a����� <BR>
     * �@@�@@�|��L�ȊO�̏ꍇ�Anull���Z�b�g <BR>
     * <BR>
     * �T�j�@@�������������ؑցE�d�q��t�\�����t���I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_strTaxType - (�ŋ敪)<BR>
     * �ŋ敪
     * @@param l_strMarginTaxType - (�M�p����ŋ敪)<BR>
     * �M�p����ŋ敪
     * @@return WEB3AdminInformAccSwitchElecDeliAppDtInfo
     * @@throws WEB3BaseException
     */
    public WEB3AdminInformAccSwitchElecDeliAppDtInfo createAccSwitchElecDeliAppDtInfo(
        String l_strTaxType,
        String l_strMarginTaxType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createAccSwitchElecDeliAppDtInfo(String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j �����ؑցE�d�q��t�\�����t���I�u�W�F�N�g�𐶐�����
        WEB3AdminInformAccSwitchElecDeliAppDtInfo l_adminInformAccSwitchElecDeliAppDtInfo =
            new WEB3AdminInformAccSwitchElecDeliAppDtInfo();

        //�Q�j ������ԊǗ�.get�������ɂĔ��������擾����B
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //�R�j �Q�j����ɘa��ϊ��������t���擾����B
        //�N��.toJapDate()�ɂĕϊ������a��iYYMMDD�`���j������i=index[1])
        String[] l_strJapDates = WEB3GentradeEra.toJapDate(l_datBizDate);

        //�S�j �ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
        //�����ؑցE�d�q��t�\�����t���.�\������ �� ���ݓ���
        l_adminInformAccSwitchElecDeliAppDtInfo.applyDate =
            GtlUtils.getSystemTimestamp();

        //�����ؑցE�d�q��t�\�����t���.�J�n�\��� �� �Q�j�Ŏ擾��������
        l_adminInformAccSwitchElecDeliAppDtInfo.startScheduleDate = l_datBizDate;

        //�����ؑցE�d�q��t�\�����t���.�ŋ敪�J�ݓ� �� �ȉ��̒ʂ�Z�b�g
        //�|(����)�ŋ敪 == 2�F����A���́A3�F������������򒥎� �̏ꍇ�A�R�j�Ŏ擾�����a�����
        //�|��L�ȊO�̏ꍇ�Anull���Z�b�g
        String l_strTaxTypeOpenDate = null;
        int l_intSpecial = (TaxTypeEnum.SPECIAL).intValue();
        int l_intSpecialWithHold = (TaxTypeEnum.SPECIAL_WITHHOLD).intValue();

        if (String.valueOf(l_intSpecial).equals(l_strTaxType)
            || String.valueOf(l_intSpecialWithHold).equals(l_strTaxType))
        {
            l_strTaxTypeOpenDate = l_strJapDates[1];
        }

        l_adminInformAccSwitchElecDeliAppDtInfo.taxTypeOpenDate = l_strTaxTypeOpenDate;

        //�����ؑցE�d�q��t�\�����t���.�M�p����ŋ敪�J�ݓ� ���ȉ��̒ʂ�Z�b�g
        //�|(����)�M�p����ŋ敪 == 2�F����A���́A3�F������������򒥎� �̏ꍇ�A�R�j�Ŏ擾�����a�����
        //�|��L�ȊO�̏ꍇ�Anull���Z�b�g
        String l_strMarginTaxTypeOpenDate = null;
        if (String.valueOf(l_intSpecial).equals(l_strMarginTaxType)
            || String.valueOf(l_intSpecialWithHold).equals(l_strMarginTaxType))
        {
            l_strMarginTaxTypeOpenDate = l_strJapDates[1];
        }

        l_adminInformAccSwitchElecDeliAppDtInfo.marginTaxTypeOpenDate = l_strMarginTaxTypeOpenDate;

        //�T�j�@@�������������ؑցE�d�q��t�\�����t���I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_adminInformAccSwitchElecDeliAppDtInfo;
    }

    /**
     * (create�����ؑցE�d�q��t�\�����)<BR>
     * �����ؑցE�d�q��t�\����񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j �����ؑցE�d�q��t�\�����I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j �ڋq.getDataSourceObject()�ɂČڋq�s���擾����B<BR>
     * <BR>
     * �R�j �ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�����ؑցE�d�q��t�\�����.���o�C����p�����J�݋敪 ��<BR>
     * �@@�@@�@@�@@�ڋq�s.���o�C����p�����J�݋敪 <BR>
     * �@@�����ؑցE�d�q��t�\�����.����񍐏���t�敪 �� �ڋq�s.����񍐏���t�敪 <BR>
     * �@@�����ؑցE�d�q��t�\�����.����c���񍐏���t�敪 ��<BR>
     * �@@�@@�@@�@@�ڋq�s.����c���񍐏���t�敪 <BR>
     * �@@�����ؑցE�d�q��t�\�����.����c���񍐏��쐬�����敪 ��<BR>
     * �@@�@@�@@�@@�ڋq�s.����c���񍐏��쐬�����敪 <BR>
     * �@@�����ؑցE�d�q��t�\�����.����c���񍐏��a��؍쐬�t���O ��<BR>
     * �@@�@@�@@�@@�ڋq�s.����c���񍐏��a��؍쐬�t���O <BR>
     * �@@�����ؑցE�d�q��t�\�����.����c���񍐏��v�Z���쐬�t���O ��<BR>
     * �@@�@@�@@�@@�ڋq�s.����c���񍐏��v�Z���쐬�t���O <BR>
     * �@@�����ؑցE�d�q��t�\�����.�ŋ敪 �� �ڋq�s.�ŋ敪 <BR>
     * �@@�����ؑցE�d�q��t�\�����.�ŋ敪�i���N�j �� �ڋq�s.�ŋ敪�i���N�j <BR>
     * �@@�����ؑցE�d�q��t�\�����.�M�p����ŋ敪 �� �ڋq�s.�M�p����ŋ敪 <BR>
     * �@@�@@���ڋq�s.�M�p����ŋ敪 == null �̏ꍇ�Anull���Z�b�g <BR>
     * �@@�����ؑցE�d�q��t�\�����.�M�p����ŋ敪�i���N�j ��<BR>
     * �@@�@@�@@�@@�ڋq�s.�M�p����ŋ敪�i���N�j<BR>
     * �@@�@@���ڋq�s.�M�p����ŋ敪�i���N�j == null �̏ꍇ�Anull���Z�b�g <BR>
     * �@@�����ؑցE�d�q��t�\�����.����Ǘ������J�݋敪 ��<BR>
     * �@@�@@�@@�@@�ڋq�s.����Ǘ������J�݋敪<BR>
     * <BR>
     * �S�j �������������ؑցE�d�q��t�\�����I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g
     * @@return WEB3AdminInformAccSwitchElecDeliApplyInfo
     * @@throws WEB3BaseException
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo createAccSwitchElecDeliApplyInfo(
        MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createAccSwitchElecDeliApplyInfo(MainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^Null�o���Ȃ��B");
        }

        //�P�j �����ؑցE�d�q��t�\�����I�u�W�F�N�g�𐶐�����B
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo =
            new WEB3AdminInformAccSwitchElecDeliApplyInfo();

        //�Q�j �ڋq.getDataSourceObject()�ɂČڋq�s���擾����B
        MainAccountRow l_mainAcountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //�����ؑցE�d�q��t�\�����.���o�C����p�����J�݋敪 �� �ڋq�s.���o�C����p�����J�݋敪
        l_changedInfo.mobileAccoutDiv = l_mainAcountRow.getOnlyMobileOpenDiv();

        //�����ؑցE�d�q��t�\�����.����񍐏���t�敪 �� �ڋq�s.����񍐏���t�敪
        l_changedInfo.tradingReportDiv = l_mainAcountRow.getTradingReportDiv();

        //�����ؑցE�d�q��t�\�����.����c���񍐏���t�敪 �� �ڋq�s.����c���񍐏���t�敪
        l_changedInfo.positionReportDiv = l_mainAcountRow.getPositionReportDiv();

        //�����ؑցE�d�q��t�\�����.����c���񍐏��쐬�����敪 �� �ڋq�s.����c���񍐏��쐬�����敪
        l_changedInfo.positionReportCycleDiv = l_mainAcountRow.getPositionReportCycleDiv();

        //�����ؑցE�d�q��t�\�����.����c���񍐏��a��؍쐬�t���O �� �ڋq�s.����c���񍐏��a��؍쐬�t���O
        l_changedInfo.certificateDepositDiv = "" + l_mainAcountRow.getCertificateDepositFlag().intValue();

        //�����ؑցE�d�q��t�\�����.����c���񍐏��v�Z���쐬�t���O �� �ڋq�s.����c���񍐏��v�Z���쐬�t���O
        l_changedInfo.accountStatementDiv = "" + l_mainAcountRow.getAccountStatementFlag().intValue();

        //�����ؑցE�d�q��t�\�����.�ŋ敪 �� �ڋq�s.�ŋ敪
        l_changedInfo.taxType = "" + l_mainAcountRow.getTaxType().intValue();

        //�����ؑցE�d�q��t�\�����.�ŋ敪�i���N�j �� �ڋq�s.�ŋ敪�i���N�j
        l_changedInfo.taxTypeNext = "" + l_mainAcountRow.getTaxTypeNext().intValue();

        if (l_mainAcountRow.getMarginTaxType() != null)
        {
            //�����ؑցE�d�q��t�\�����.�M�p����ŋ敪 �� �ڋq�s.�M�p����ŋ敪
            l_changedInfo.marginTaxType = "" + l_mainAcountRow.getMarginTaxType().intValue();
        }

        if (l_mainAcountRow.getMarginTaxTypeNext() != null)
        {
            //�����ؑցE�d�q��t�\�����.�M�p����ŋ敪�i���N�j �� �ڋq�s.�M�p����ŋ敪�i���N�j
            l_changedInfo.marginTaxTypeNext = "" + l_mainAcountRow.getMarginTaxTypeNext().intValue();
        }

        //�����ؑցE�d�q��t�\�����.����Ǘ������J�݋敪 �� �ڋq�s.����Ǘ������J�݋敪
        l_changedInfo.capitalGainTaxAccOpenDiv = l_mainAcountRow.getSpMngAccOpenDiv();

        //�S�j �������������ؑցE�d�q��t�\�����I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_changedInfo;
    }

    /**
     * (create�����ؑցE�d�q��t�\�����)<BR>
     * �����ؑցE�d�q��t�\����񃁃b�Z�[�W�f�[�^���쐬����B <BR>
     * <BR>
     * �P�j �����ؑցE�d�q��t�\�����I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * �Q�j �ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@�����ؑցE�d�q��t�\�����.���o�C����p�����J�݋敪 �� <BR>
     * �@@�@@�@@�@@�i�����j�e��A���s.�敪�P <BR>
     * �@@�����ؑցE�d�q��t�\�����.����񍐏���t�敪 �� <BR>
     * �@@�@@�@@�@@�i�����j�e��A���s.�敪�Q <BR>
     * �@@�����ؑցE�d�q��t�\�����.����c���񍐏���t�敪 �� <BR>
     * �@@�@@�@@�@@�i�����j�e��A���s.�敪�R <BR>
     * �@@�����ؑցE�d�q��t�\�����.����c���񍐏��쐬�����敪 �� <BR>
     * �@@�@@�@@�@@�i�����j�e��A���s.�敪�S <BR>
     * �@@�����ؑցE�d�q��t�\�����.����c���񍐏��a��؍쐬�t���O �� <BR>
     * �@@�@@�@@�@@�i�����j�e��A���s.�敪�T <BR>
     * �@@�����ؑցE�d�q��t�\�����.����c���񍐏��v�Z���쐬�t���O �� <BR>
     * �@@�@@�@@�@@�i�����j�e��A���s.�敪�U <BR>
     * �@@�����ؑցE�d�q��t�\�����.�ŋ敪    �� <BR>
     * �@@�@@�@@�@@�i�����j�e��A���s.�敪�V <BR>
     * �@@�����ؑցE�d�q��t�\�����.�ŋ敪�i���N�j  �� <BR>
     * �@@�@@�@@�@@�i�����j�e��A���s.�敪�W <BR>
     * �@@�����ؑցE�d�q��t�\�����.�M�p����ŋ敪  �� <BR>
     * �@@�@@�@@�@@�i�����j�e��A���s.�敪�X <BR>
     * �@@�����ؑցE�d�q��t�\�����.�M�p����ŋ敪�i���N�j  �� <BR>
     * �@@�@@�@@�@@�i�����j�e��A���s.�敪�P�O <BR>
     * �@@�����ؑցE�d�q��t�\�����.����Ǘ������J�݋敪 �� <BR>
     * �@@�@@�@@�@@�i�����j�e��A���s.�敪�P�P <BR>
     * <BR>
     * �@@��null�̏ꍇ�Anull���Z�b�g <BR>
     * <BR>
     * �R�j �������������ؑցE�d�q��t�\�����I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_variousInformParams - (�e��A���s)<BR>
     * �e��A���s<BR>
     * @@return WEB3AdminInformAccSwitchElecDeliApplyInfo
     * @@throws WEB3BaseException 
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo createAccSwitchElecDeliApplyInfo(
        VariousInformParams l_variousInformParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAccSwitchElecDeliApplyInfo(VariousInformParams)";
        log.entering(STR_METHOD_NAME);

        if (l_variousInformParams == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j �����ؑցE�d�q��t�\�����I�u�W�F�N�g�𐶐�����B
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_AccSwitchElecDeliApplyInfo =
            new WEB3AdminInformAccSwitchElecDeliApplyInfo();

        //�Q�j �ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
        // �@@�����ؑցE�d�q��t�\�����.���o�C����p�����J�݋敪 �� �i�����j�e��A���s.�敪�P
        l_AccSwitchElecDeliApplyInfo.mobileAccoutDiv = l_variousInformParams.getExtDiv1();
        // �@@�����ؑցE�d�q��t�\�����.����񍐏���t�敪 �� �i�����j�e��A���s.�敪�Q
        l_AccSwitchElecDeliApplyInfo.tradingReportDiv = l_variousInformParams.getExtDiv2();
        // �@@�����ؑցE�d�q��t�\�����.����c���񍐏���t�敪 �� �i�����j�e��A���s.�敪�R
        l_AccSwitchElecDeliApplyInfo.positionReportDiv = l_variousInformParams.getExtDiv3();
        // �@@�����ؑցE�d�q��t�\�����.����c���񍐏��쐬�����敪 �� �i�����j�e��A���s.�敪�S
        l_AccSwitchElecDeliApplyInfo.positionReportCycleDiv = l_variousInformParams.getExtDiv4();
        // �@@�����ؑցE�d�q��t�\�����.����c���񍐏��a��؍쐬�t���O �� �i�����j�e��A���s.�敪�T
        l_AccSwitchElecDeliApplyInfo.certificateDepositDiv = l_variousInformParams.getExtDiv5();
        // �@@�����ؑցE�d�q��t�\�����.����c���񍐏��v�Z���쐬�t���O �� �i�����j�e��A���s.�敪�U
        l_AccSwitchElecDeliApplyInfo.accountStatementDiv = l_variousInformParams.getExtDiv6();
        // �@@�����ؑցE�d�q��t�\�����.�ŋ敪    �� �i�����j�e��A���s.�敪�V
        l_AccSwitchElecDeliApplyInfo.taxType = l_variousInformParams.getExtDiv7();
        // �@@�����ؑցE�d�q��t�\�����.�ŋ敪�i���N�j  �� �i�����j�e��A���s.�敪�W
        l_AccSwitchElecDeliApplyInfo.taxTypeNext = l_variousInformParams.getExtDiv8();
        // �@@�����ؑցE�d�q��t�\�����.�M�p����ŋ敪  �� �i�����j�e��A���s.�敪�X
        l_AccSwitchElecDeliApplyInfo.marginTaxType = l_variousInformParams.getExtDiv9();
        // �@@�����ؑցE�d�q��t�\�����.�M�p����ŋ敪�i���N�j  �� �i�����j�e��A���s.�敪�P�O
        l_AccSwitchElecDeliApplyInfo.marginTaxTypeNext = l_variousInformParams.getExtDiv10();
        // �@@�����ؑցE�d�q��t�\�����.����Ǘ������J�݋敪 �� �i�����j�e��A���s.�敪�P�P
        l_AccSwitchElecDeliApplyInfo.capitalGainTaxAccOpenDiv = l_variousInformParams.getExtDiv11();

        //�R�j �������������ؑցE�d�q��t�\�����I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_AccSwitchElecDeliApplyInfo;
    }

    /**
     * (create���E��c�d�q��t�E��������o�^�s)<BR>
     * ���E��c�d�q��t�E��������o�^�V�K�s�𐶐�����B <BR>
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g���� <BR>
     * �@@���E��c�d�q��t�E��������o�^Params�I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * �@@�����E��c�d�q��t�E��������o�^Params��DDL��莩����������B <BR>
     * <BR>
     * �Q�j�@@�ڋq.getDataSourceObject()�ɂĕύX�O�����擾����B  <BR>
     * <BR>
     * �R�j�@@�s�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B  <BR>
     * �@@�P�j�Ő����������E��c�d�q��t�E��������o�^Params�I�u�W�F�N�g��<BR>
     * �@@�@@�@@�v���p�e�B�ɁA�ȉ��̒ʂ�Z�b�g���s���B <BR>
     * <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.�،���ЃR�[�h ��<BR>
     * �@@�@@�@@�@@ (����)�e��A���s.�،���ЃR�[�h <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.���X�R�[�h ��<BR>
     * �@@�@@�@@�@@ (����)�e��A���s.���X�R�[�h <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.�ڋq�R�[�h ��<BR>
     * �@@�@@�@@�@@ (����)�e��A���s.�ڋq�R�[�h <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@��� ��  <BR>
     * �@@�@@�ڋq�s.����c���񍐏��쐬�����敪 != (����)�e��A���s.�敪�S �̏ꍇ�A<BR>
     * �@@�@@�@@�@@(����)�e��A���s.�敪�S <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�d�q��t�i�s�x�j �� <BR>
     * �@@�@@�ڋq�s.����c���񍐏���t�敪 != (����)�e��A���s.�敪�R �̏ꍇ�A<BR>
     * �@@�@@�@@�@@(����)�e��A���s.�敪�R <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�a��� �� <BR>
     * �@@�@@�ڋq�s.����c���񍐏��a��؍쐬�t���O != (����)�e��A���s.�敪�T �̏ꍇ�A<BR>
     * �@@�@@�@@�@@(����)�e��A���s.�敪�T <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�v�Z�� ��  <BR>
     * �@@�@@�ڋq�s.����c���񍐏��v�Z���쐬�t���O != (����)�e��A���s.�敪�U �̏ꍇ�A<BR>
     * �@@�@@�@@�@@(����)�e��A���s.�敪�U <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.�d�q��t�@@����񍐏� ��  <BR>
     * �@@�@@�ڋq�s.����񍐏���t�敪 != (����)�e��A���s.�敪�Q �̏ꍇ�A<BR>
     * �@@�@@�@@�@@(����)�e��A���s.�敪�Q <BR>
     * �@@�ڋq�s.�ŋ敪 != (����)�e��A���s.�敪�V �̏ꍇ�A�ȉ����Z�b�g����B <BR>
     * �@@�@@�@@���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� �� <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�V �� 0 �̏ꍇ�Anull <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�V �� 1 �̏ꍇ�A0�F��� <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�V �� 2 �̏ꍇ�A1�F����E����Ȃ� <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�V �� 3 �̏ꍇ�A2�F����E���򂠂� <BR>
     * �@@�@@�A���E��c�d�q��t�E��������o�^Params.�i�����j��������@@�J�ݓ� �� <BR>
     * �@@�@@�@@�@@(����)�e��A���s.�e�L�X�g�R <BR>
     * �@@�ڋq�s.�ŋ敪�i���N�j != (����)�e��A���s.�敪�W �̏ꍇ�A<BR>
     * �@@�ȉ����Z�b�g����B <BR>
     * �@@�@@���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� �� <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�W �� 0 �̏ꍇ�Anull <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�W �� 1 �̏ꍇ�A0�F��� <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�W �� 2 �̏ꍇ�A1�F����E����Ȃ� <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�W �� 3 �̏ꍇ�A2�F����E���򂠂� <BR>
     * �@@�ڋq�s.�M�p����ŋ敪 != (����)�e��A���s.�敪�X �̏ꍇ�A<BR>
     * �@@�ȉ����Z�b�g����B <BR>
     * �@@�@@�@@���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@���� ��  <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�X �� 0 �̏ꍇ�Anull <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�X �� 1 �̏ꍇ�A0�F��� <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�X �� 2 �̏ꍇ�A1�F����E����Ȃ� <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�X �� 3 �̏ꍇ�A2�F����E���򂠂� <BR>
     * �@@�@@�A���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@�J�ݓ� �� <BR>
     * �@@�@@�@@�@@(����)�e��A���s.�e�L�X�g�S <BR>
     * �@@�ڋq�s.�M�p����ŋ敪�i���N�j != (����)�e��A���s.�敪�P�O �̏ꍇ�A<BR>
     * �@@�ȉ����Z�b�g����B <BR>
     * �@@�@@���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@���� ��  <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�P�O �� 0 �̏ꍇ�Anull <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�P�O �� 1 �̏ꍇ�A0�F��� <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�P�O �� 2 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@1�F����E����Ȃ� <BR>
     * �@@�@@�@@�|(����)�e��A���s.�敪�P�O �� 3 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@2�F����E���򂠂� <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.����Ǘ����� ��  <BR>
     * �@@�@@�ڋq�s.����Ǘ������J�݋敪 != (����)�e��A���s.�敪�P�P �̏ꍇ�A<BR>
     * �@@�@@�@@�@@(����)�e��A���s.�敪�P�P <BR>
     * <BR>
     * �@@�������ɊY�����Ȃ��ꍇ��null���Z�b�g����B <BR>
     * <BR>
     * �S�j�@@���������s�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_variousInformParams - (�e��A���s)<BR>
     * �e��A���s<BR>
     * @@return HostConditionRegVoucherParams
     * @@throws WEB3BaseException
     */
    public HostConditionRegVoucherParams createHostConditionRegVoucherParams(
        MainAccount l_mainAccount,
        VariousInformParams l_variousInformParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createHostConditionRegVoucherParams(MainAccount, VariousInformParams)";
        log.entering(STR_METHOD_NAME);

        if (l_variousInformParams == null || l_mainAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@�s�I�u�W�F�N�g����
        //�@@���E��c�d�q��t�E��������o�^Params�I�u�W�F�N�g�𐶐�����B
        //�@@�����E��c�d�q��t�E��������o�^Params��DDL��莩����������B
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
            new HostConditionRegVoucherParams();
        
        //�Q�j�@@�ڋq.getDataSourceObject()�ɂĕύX�O�����擾����B
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //�R�j�@@�s�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B
        //�@@�P�j�Ő����������E��c�d�q��t�E��������o�^Params�I�u�W�F�N�g��
        //�v���p�e�B�ɁA�ȉ��̒ʂ�Z�b�g���s���B
        //�@@���E��c�d�q��t�E��������o�^Params.�،���ЃR�[�h ��
        //(����)�e��A���s.�،���ЃR�[�h
        l_hostConditionRegVoucherParams.setInstitutionCode(l_variousInformParams.getInstitutionCode());
 
        //�@@���E��c�d�q��t�E��������o�^Params.���X�R�[�h �� (����)�e��A���s.���X�R�[�h
        l_hostConditionRegVoucherParams.setBranchCode(l_variousInformParams.getBranchCode());

        //�@@���E��c�d�q��t�E��������o�^Params.�ڋq�R�[�h �� (����)�e��A���s.�ڋq�R�[�h
        l_hostConditionRegVoucherParams.setAccountCode(l_variousInformParams.getAccountCode());

        //�@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@��� ��
        //�@@�@@�ڋq�s.����c���񍐏��쐬�����敪 != (����)�e��A���s.�敪�S �̏ꍇ�A
        //    (����)�e��A���s.�敪�S
        if (!l_mainAccountRow.getPositionReportCycleDiv().equals(l_variousInformParams.getExtDiv4()))
        {
            l_hostConditionRegVoucherParams.setPosReportTermDiv(l_variousInformParams.getExtDiv4());
        }

        //�@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�d�q��t�i�s�x�j ��
        //�@@�@@�ڋq�s.����c���񍐏���t�敪 != (����)�e��A���s.�敪�R �̏ꍇ�A
        //    (����)�e��A���s.�敪�R
        if (!l_mainAccountRow.getPositionReportDiv().equals(l_variousInformParams.getExtDiv3()))
        {
            l_hostConditionRegVoucherParams.setPosReportCycleDiv(l_variousInformParams.getExtDiv3());
        }

        //�@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�a��� ��
        //�@@�@@�ڋq�s.����c���񍐏��a��؍쐬�t���O != (����)�e��A���s.�敪�T �̏ꍇ�A
        //    (����)�e��A���s.�敪�T
        String l_strCertificateDepositFlag = String.valueOf(l_mainAccountRow.getCertificateDepositFlag().intValue());
        if (!l_strCertificateDepositFlag.equals(l_variousInformParams.getExtDiv5()))
        {
            l_hostConditionRegVoucherParams.setPosReportCertifDepoDiv(l_variousInformParams.getExtDiv5());
        }

        //�@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�v�Z�� ��
        //�@@�@@�ڋq�s.����c���񍐏��v�Z���쐬�t���O != (����)�e��A���s.�敪�U �̏ꍇ�A
        //    (����)�e��A���s.�敪�U
        String l_strAccountStatementFlag = String.valueOf(l_mainAccountRow.getAccountStatementFlag().intValue());
        if (!l_strAccountStatementFlag.equals(l_variousInformParams.getExtDiv6()))
        {
            l_hostConditionRegVoucherParams.setPosReportAccStateDiv(l_variousInformParams.getExtDiv6());
        }

        //�@@���E��c�d�q��t�E��������o�^Params.�d�q��t�@@����񍐏� ��
        //�@@�@@�ڋq�s.����񍐏���t�敪 != (����)�e��A���s.�敪�Q �̏ꍇ�A(����)�e��A���s.�敪�Q
        if (!l_mainAccountRow.getTradingReportDiv().equals(l_variousInformParams.getExtDiv2()))
        {
            l_hostConditionRegVoucherParams.setTradingEReportDiv(l_variousInformParams.getExtDiv2());
        }

        //�@@�ڋq�s.�ŋ敪 != (����)�e��A���s.�敪�V �̏ꍇ�A�ȉ����Z�b�g����B
        String l_strTaxType = String.valueOf(l_mainAccountRow.getTaxType().intValue());
        if (!l_strTaxType.equals(l_variousInformParams.getExtDiv7()))
        {
            //�@@�@@�@@���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� ��
            //�@@�@@�@@�|(����)�e��A���s.�敪�V �� 0 �̏ꍇ�Anull
            if (WEB3InformTaxTypeDef.OTHER.equals(l_variousInformParams.getExtDiv7()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDiv(null);
            }
            //�@@�@@�@@�|(����)�e��A���s.�敪�V �� 1 �̏ꍇ�A0�F���
            else if (WEB3InformTaxTypeDef.NORMAL.equals(l_variousInformParams.getExtDiv7()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDiv(WEB3TaxTypeDivDef.NORMAL);
            }
            //�@@�@@�@@�|(����)�e��A���s.�敪�V �� 2 �̏ꍇ�A1�F����E����Ȃ�
            else if (WEB3InformTaxTypeDef.SPECIAL.equals(l_variousInformParams.getExtDiv7()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDiv(WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE);
            }
            //�@@�@@�@@�|(����)�e��A���s.�敪�V �� 3 �̏ꍇ�A2�F����E���򂠂�
            else if (WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_variousInformParams.getExtDiv7()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDiv(WEB3TaxTypeDivDef.SPECIAL_SOURCE);
            }

            //�@@�@@�A���E��c�d�q��t�E��������o�^Params.�i�����j��������@@�J�ݓ� �� (����)�e��A���s.�e�L�X�g�R
            l_hostConditionRegVoucherParams.setEquitySpAccOpenDat(l_variousInformParams.getExtText3());
        }

        //�@@�ڋq�s.�ŋ敪�i���N�j != (����)�e��A���s.�敪�W �̏ꍇ�A�ȉ����Z�b�g����B
        String l_strTaxTypeNext = String.valueOf(l_mainAccountRow.getTaxTypeNext().intValue());
        if (!l_strTaxTypeNext.equals(l_variousInformParams.getExtDiv8()))
        {
            //�@@�@@���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� ��
            //�@@�@@�@@�|(����)�e��A���s.�敪�W �� 0 �̏ꍇ�Anull
            if (WEB3InformTaxTypeDef.OTHER.equals(l_variousInformParams.getExtDiv8()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDivNext(null);
            }
            //�@@�@@�@@�|(����)�e��A���s.�敪�W �� 1 �̏ꍇ�A0�F���
            else if (WEB3InformTaxTypeDef.NORMAL.equals(l_variousInformParams.getExtDiv8()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDivNext(WEB3TaxTypeDivDef.NORMAL);
            }
            //�@@�@@�@@�|(����)�e��A���s.�敪�W �� 2 �̏ꍇ�A1�F����E����Ȃ�
            else if (WEB3InformTaxTypeDef.SPECIAL.equals(l_variousInformParams.getExtDiv8()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDivNext(WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE);
            }
            //�@@�@@�@@�|(����)�e��A���s.�敪�W �� 3 �̏ꍇ�A2�F����E���򂠂�
            else if (WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_variousInformParams.getExtDiv8()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDivNext(WEB3TaxTypeDivDef.SPECIAL_SOURCE);
            }
        }

        //�@@�ڋq�s.�M�p����ŋ敪 != (����)�e��A���s.�敪�X �̏ꍇ�A�ȉ����Z�b�g����B
        String l_strMarginTaxType = null;
        if (l_mainAccountRow.getMarginTaxType() != null)
        {
            l_strMarginTaxType = String.valueOf(l_mainAccountRow.getMarginTaxType().intValue());
        }
        if (!WEB3Toolkit.isEquals(l_strMarginTaxType, l_variousInformParams.getExtDiv9()))
        {
            //�@@�@@�@@���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@���� ��
            //�@@    �|(����)�e��A���s.�敪�X �� 0 �̏ꍇ�Anull
            if (WEB3InformTaxTypeDef.OTHER.equals(l_variousInformParams.getExtDiv9()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDiv(null);
            }
            //�@@�@@�@@�|(����)�e��A���s.�敪�X �� 1 �̏ꍇ�A0�F���
            else if (WEB3InformTaxTypeDef.NORMAL.equals(l_variousInformParams.getExtDiv9()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDiv(WEB3TaxTypeDivDef.NORMAL);
            }
            //�@@�@@�@@�|(����)�e��A���s.�敪�X �� 2 �̏ꍇ�A1�F����E����Ȃ�
            else if (WEB3InformTaxTypeDef.SPECIAL.equals(l_variousInformParams.getExtDiv9()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDiv(WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE);
            }
            //�@@�@@�@@�|(����)�e��A���s.�敪�X �� 3 �̏ꍇ�A2�F����E���򂠂�
            else if (WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_variousInformParams.getExtDiv9()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDiv(WEB3TaxTypeDivDef.SPECIAL_SOURCE);
            }
            
            //�@@�@@�A���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@�J�ݓ� �� (����)�e��A���s.�e�L�X�g�S
            l_hostConditionRegVoucherParams.setMarginSpAccOpenDat(l_variousInformParams.getExtText4());
        }

        //�@@�ڋq�s.�M�p����ŋ敪�i���N�j != (����)�e��A���s.�敪�P�O �̏ꍇ�A�ȉ����Z�b�g����B
        String l_strMarginTaxTypeNext = null;
        if (l_mainAccountRow.getMarginTaxTypeNext() != null)
        {
            l_strMarginTaxTypeNext = String.valueOf(l_mainAccountRow.getMarginTaxTypeNext().intValue());
        }
        if (!WEB3Toolkit.isEquals(l_strMarginTaxTypeNext, l_variousInformParams.getExtDiv10()))
        {
            //�@@�@@���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@���� ��
            //�@@�@@�@@�|(����)�e��A���s.�敪�P�O �� 0 �̏ꍇ�Anull
            if (WEB3InformTaxTypeDef.OTHER.equals(l_variousInformParams.getExtDiv10()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDivNext(null);
            }
            //�@@�@@�@@�|(����)�e��A���s.�敪�P�O �� 1 �̏ꍇ�A0�F���
            else if (WEB3InformTaxTypeDef.NORMAL.equals(l_variousInformParams.getExtDiv10()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDivNext(WEB3TaxTypeDivDef.NORMAL);
            }
            //�@@�@@�@@�|(����)�e��A���s.�敪�P�O �� 2 �̏ꍇ�A1�F����E����Ȃ�
            else if (WEB3InformTaxTypeDef.SPECIAL.equals(l_variousInformParams.getExtDiv10()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDivNext(WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE);
            }
            //�@@�@@�@@�|(����)�e��A���s.�敪�P�O �� 3 �̏ꍇ�A2�F����E���򂠂�
            else if (WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_variousInformParams.getExtDiv10()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDivNext(WEB3TaxTypeDivDef.SPECIAL_SOURCE);
            }
        }

        //�@@���E��c�d�q��t�E��������o�^Params.����Ǘ����� ��
        //�@@�@@�ڋq�s.����Ǘ������J�݋敪 != (����)�e��A���s.�敪�P�P �̏ꍇ�A(����)�e��A���s.�敪�P�P
        if (!WEB3Toolkit.isEquals(l_mainAccountRow.getSpMngAccOpenDiv(),
            l_variousInformParams.getExtDiv11()))
        {
            l_hostConditionRegVoucherParams.setSpMngAccOpenDiv(l_variousInformParams.getExtDiv11());
        }

        log.exiting(STR_METHOD_NAME);
        return l_hostConditionRegVoucherParams;
    }

    /**
     * (�g���K���s)<BR>
     * �g���K�𔭍s����B  <BR>
     * <BR>
     * �P�jWEB3MQMessageSpec�𐶐�����B   <BR>
     * <BR>
     *    WEB3MQMessageSpec(�،���ЃR�[�h, �f�[�^�R�[�h,���[�U�f�[�^)  <BR>
     * <BR>
     *    [����]   <BR>
     *    �،���ЃR�[�h�F�@@����.�،���ЃR�[�h  <BR>
     *    �f�[�^�R�[�h�F�@@����.�f�[�^�R�[�h + "T" <BR>
     *    ���[�U�f�[�^�F�@@"�Q�F�q�敪�i�����q�j" <BR>
     * <BR>
     * �Q�jMQ�g���K�𔭍s����B  <BR>
     * <BR>
     *    WEB3MQGatewayServiceImpl.send(MQ���b�Z�[�W���e)  <BR>
     * <BR>
     *    [����]  <BR>
     *    MQ���b�Z�[�W���e�F ��������WEB3MQMessageSpec  <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strDataCode - (�f�[�^�R�[�h)<BR>
     * �f�[�^�R�[�h<BR>
     */
    public void submitMarketTrigger(String l_strInstitutionCode, String l_strDataCode)
    {
        final String STR_METHOD_NAME = "submitMarketTrigger(String, String)";
        log.entering(STR_METHOD_NAME);

        // �P�jWEB3MQMessageSpec�𐶐�����B
        // WEB3MQMessageSpec(�،���ЃR�[�h, �f�[�^�R�[�h,���[�U�f�[�^)
        //    �،���ЃR�[�h�F�@@����.�،���ЃR�[�h
        //    �f�[�^�R�[�h�F�@@����.�f�[�^�R�[�h + "T"
        //    ���[�U�f�[�^�F�@@"�Q�F�q�敪�i�����q�j"
        WEB3MQMessageSpec l_mqMessageSpec =
            new WEB3MQMessageSpec(l_strInstitutionCode,
                l_strDataCode + "T",
                WEB3InformUserDataDef.EXISTING_ACCOUNT);

        // �Q�jMQ�g���K�𔭍s����B
        WEB3MQGatewayService l_mqGatewayService =
            (WEB3MQGatewayService)Services.getService(WEB3MQGatewayService.class);
        log.debug("�g���K�𔭍s����........");
        l_mqGatewayService.send(l_mqMessageSpec);
        log.debug("�g���K�𔭍s����........OK!");
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�`�[�쐬)<BR>
     * �`�[�쐬�\���`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@����.�g���K�[���s�敪 == true �̏ꍇ <BR>
     * �@@�P�|�P�j�@@����.�쐬�󋵂��A0�F���쐬�A3�F��t�����A4�F��t�G���[ <BR>
     * �@@�@@�@@�@@�̂�����ł��Ȃ��ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[ <BR>
     * �@@�@@�@@�@@�@@�@@�u���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B�v<BR>
     * �@@�@@�@@�@@�@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02786<BR>
     * <BR>
     * �Q�j�@@����.�g���K�[���s�敪 == false �̏ꍇ�@@ <BR>
     * �@@�Q�|�P�j�@@����.�쐬�󋵂��A0�F���쐬�A2�F��t���A3�F��t�����A<BR>
     * �@@�@@�@@�@@4�F��t�G���[ �̂�����ł��Ȃ��ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[ <BR>
     * �@@�@@�@@�@@�@@�@@�u�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B�v <BR>
     * �@@�@@�@@�@@�@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02787<BR>
     * @@param l_strMakeStatus - (�쐬��)<BR>
     * �쐬��<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (�g���K�[���s�敪)<BR>
     * �g���K�[���s�敪<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherMake(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateVoucherMake(String, boolean)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@����.�g���K�[���s�敪 == true �̏ꍇ
        if (l_blnSubmitMarketTriggerDiv)
        {
            //�P�|�P�j�@@����.�쐬�󋵂��A0�F���쐬�A3�F��t�����A4�F��t�G���[
            //�̂�����ł��Ȃ��ꍇ
            //��O���X���[ 
            //�u���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B�v
            if (!WEB3VoucherCreateStatusDef.NOT_CREATE.equals(l_strMakeStatus)
                && (!WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strMakeStatus)))
            {
                log.debug("���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02786,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B");
            }
        }
        //�Q�j�@@����.�g���K�[���s�敪 == false �̏ꍇ
        else
        {
            //�Q�|�P�j�@@����.�쐬�󋵂��A0�F���쐬�A2�F��t���A3�F��t�����A
            //4�F��t�G���[ �̂�����ł��Ȃ��ꍇ�A
            //��O���X���[ �u�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B�v
            if ((!WEB3VoucherCreateStatusDef.NOT_CREATE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.INT_ACCEPT.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strMakeStatus)))
            {
                log.debug("�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02787,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�`�[�쐬)<BR>
     * �`�[�쐬�\���`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@����.�g���K�[���s�敪 == true �̏ꍇ <BR>
     * �@@�P�|�P�j�@@����.�쐬�󋵂��A1�F�쐬�� or <BR>
     * �@@�@@(����.�쐬�󋵂��A2�F��t�� and  ����.�J�n�\��� >= ���ݓ��t) �̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[ <BR>
     * �@@�@@�@@�@@�@@�@@�u���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B�v�@@ <BR>
     * �@@�@@�@@�@@�@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02786<BR>
     * <BR>
     * �Q�j�@@����.�g���K�[���s�敪 == false �̏ꍇ�@@ <BR>
     * �@@�Q�|�P�j�@@����.�쐬�󋵂��A0�F���쐬�A2�F��t���A3�F��t�����A<BR>
     * �@@�@@�@@�@@�@@�@@4�F��t�G���[ �̂�����ł��Ȃ��ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[ <BR>
     * �@@�@@�@@�@@�@@�@@�u�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B�v<BR>
     * �@@�@@�@@�@@�@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02787<BR>
     * @@param l_strMakeStatus - (�쐬��)<BR>
     * �쐬��<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (�g���K�[���s�敪)<BR>
     * �g���K�[���s�敪<BR>
     * @@param l_datStartScheduleDate - (�J�n�\���)<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherMake(
            String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv, Date l_datStartScheduleDate)
                throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateVoucherMake(String, boolean, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_datStartScheduleDate == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@����.�g���K�[���s�敪 == true �̏ꍇ
        if (l_blnSubmitMarketTriggerDiv)
        {
            //����.�쐬�󋵂��A1�F�쐬�� or (����.�쐬�󋵂��A2�F��t�� and  ����.�J�n�\��� >= ���ݓ��t) �̏ꍇ�A
            //��O���X���[ 
            //�u���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B�v
            if ((WEB3VoucherCreateStatusDef.CREATE_COMPLETE.equals(l_strMakeStatus)) 
                || (WEB3VoucherCreateStatusDef.INT_ACCEPT.equals(l_strMakeStatus) 
                && (WEB3DateUtility.compareToDay(l_datStartScheduleDate, GtlUtils.getSystemTimestamp()) >= 0)))
            {
                log.debug("���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02786,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B");
            }
        }
        //�Q�j�@@����.�g���K�[���s�敪 == false �̏ꍇ
        else
        {
            //�Q�|�P�j�@@����.�쐬�󋵂��A0�F���쐬�A2�F��t���A3�F��t�����A
            //4�F��t�G���[ �̂�����ł��Ȃ��ꍇ�A
            //��O���X���[ �u�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B�v
            if ((!WEB3VoucherCreateStatusDef.NOT_CREATE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.INT_ACCEPT.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strMakeStatus)))
            {
                log.debug("�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02787,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�`�[�ύX)<BR>
     * �`�[�ύX���\���`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@����.�g���K�[���s�敪 == true �̏ꍇ <BR>
     * �@@�P�|�P�j�@@����.�쐬�󋵂��A0�F���쐬�A4�F��t�G���[ �̂�����ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[ <BR>
     * �@@�@@�@@�@@�@@�@@�u���ɓ`�[���쐬�ς݂̂��߁A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�I�����C�����͓`�[�쐬���s���܂���B�v�@@ <BR>
     * �@@�@@�@@�@@�@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02786<BR>
     * <BR>
     * �Q�j�@@����.�g���K�[���s�敪 == false �̏ꍇ�@@ <BR>
     * �@@�Q�|�P�j�@@����.�쐬�󋵂��A0�F���쐬�A1�F�쐬�ς݁A<BR>
     * �@@�@@�@@�@@4�F��t�G���[ �̂�����ł��Ȃ��ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[ <BR>
     * �@@�@@�@@�@@�@@�@@�u�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B�v�@@<BR>
     * �@@�@@�@@�@@�@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02787<BR>
     * @@param l_strMakeStatus - (�쐬��)<BR>
     * �쐬��<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (�g���K�[���s�敪)<BR>
     * �g���K�[���s�敪<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherChange(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateVoucherChange(String, boolean)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@����:�g���K�[���s�敪 == true �̏ꍇ
        if (l_blnSubmitMarketTriggerDiv)
        {
            // �P�|�P�j�@@�쐬�󋵂��A0�F���쐬�A4�F��t�G���[ �̂�����ł��Ȃ��ꍇ�A
            // ��O���X���[�u���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B�v
            if (!WEB3VoucherCreateStatusDef.NOT_CREATE.equals(l_strMakeStatus)
                && (!WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strMakeStatus)))
            {
                log.debug("���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02786,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B");
            }
        }
        // �Q�j�@@����:�g���K�[���s�敪 == false �̏ꍇ
        else
        {
            // �Q�|�P�j�@@����.�쐬�󋵂��A0�F���쐬�A1�F�쐬�ς݁A4�F��t�G���[ �̂�����ł��Ȃ��ꍇ�A
            // ��O���X���[�u�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B�v
            if ((!WEB3VoucherCreateStatusDef.NOT_CREATE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.CREATE_COMPLETE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strMakeStatus)))
            {
                log.debug("�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02787,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�`�[���)<BR>
     * �`�[����\���`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@����.�g���K�[���s�敪 == true or ����.�쐬�� != 1�F�쐬�� �̏ꍇ�A<BR>
     * �@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@BUSINESS_ERROR_02798<BR>
     * @@param l_strMakeStatus - (�쐬��)<BR>
     * �쐬��<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (�g���K�[���s�敪)<BR>
     * �g���K�[���s�敪<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherCancel(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateVoucherCancel(String, boolean)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@����.�g���K�[���s�敪 == true or ����.�쐬�� != 1�F�쐬�� �̏ꍇ�A
        // ��O���X���[
        if (l_blnSubmitMarketTriggerDiv
            || (!WEB3VoucherCreateStatusDef.CREATE_COMPLETE.equals(l_strMakeStatus)))
        {
            log.debug("�I�����C�����܂��͓`�[���쐬�܂��͓`�[���M�ς݂̏ꍇ�A����s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02798,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�I�����C�����܂��͓`�[���쐬�܂��͓`�[���M�ς݂̏ꍇ�A����s�B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�e��A���ꗗ)<BR>
     * �ȉ��̌�����������擾�����e��A�����R�[�h��List�^�ŕԋp����B <BR>
     * <BR>
     * �P�j�@@�e��A���e�[�u���̌��� <BR>
     * [��������] <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     * �A����ʁF ����.�A����� <BR>
     * ���X�R�[�h�F ����.���X�R�[�h <BR>
     * �ڋq�R�[�h�F ����.�ڋq�R�[�h <BR>
     * ���i�ڋq�R�[�h.length() == 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B <BR>
     * <BR>
     * [�\�[�g����] <BR>
     * �X�V�����i�~���j <BR>
     * <BR>
     * * ���R�[�h��������Ȃ��ꍇ�Anull��ԋp <BR>
     * <BR>
     * �Q�j�������ʂ�ԋp<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strInformType - (�A�����)<BR>
     * �A�����<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getVariousInformList(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strInformType,
        String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getVariousInformList(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        List l_lisRows = null;

        //�P�j�@@�e��A���e�[�u���̌���
        //[��������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //�A����ʁF ����.�A�����
        //���X�R�[�h�F ����.���X�R�[�h
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h
        //���i�ڋq�R�[�h.length() == 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and inform_div = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and account_code like ? || '%' ");

        Object[] l_objQueryContainers =
            {l_strInstitutionCode, l_strInformType, l_strBranchCode, l_strAccountCode};

        //[�\�[�g����]
        //�X�V�����i�~���j
        String l_sortKey = " last_updated_timestamp desc ";
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRows = l_queryProcessor.doFindAllQuery(
                VariousInformRow.TYPE,
                l_sbWhere.toString(),
                l_sortKey,
                null,
                l_objQueryContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." +STR_METHOD_NAME,
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

        //* ���R�[�h��������Ȃ��ꍇ�Anull��ԋp
        if (l_lisRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�������ʂ�ԋp
        log.exiting(STR_METHOD_NAME);
        return l_lisRows;
    }
}
@
