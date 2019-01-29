head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.31.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioSLProductRegistControlServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����o�^����T�[�r�XImpl(WEB3AdminAioSLProductRegistControlServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 ��іQ(���u) �V�K�쐬 ���f��No.760,�c�a�X�V�d�lNo.151
Revision History : 2007/09/29 ��іQ(���u)  �c�a�X�V�d�lNo.154
Revision History : 2007/10/19 �И���(���u)  �c�a�X�V�d�lNo.155
Revision History : 2007/10/23 ����(���u)�@@�d�l�ύX���f��No.811
Revision History : 2007/10/26 ����(���u)�@@�d�l�ύX���f��No.816,�c�a�X�V�d�lNo.158
*/

package webbroker3.aio;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.aio.data.SecurityProductDao;
import webbroker3.aio.data.SecurityProductPK;
import webbroker3.aio.data.SecurityProductParams;
import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.message.WEB3SLProductSearchConditions;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�S�ۖ����o�^����T�[�r�XImpl)<BR>
 * �S�ۖ����o�^����T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminAioSLProductRegistControlServiceImpl implements WEB3AdminAioSLProductRegistControlService
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductRegistControlServiceImpl.class);

    /**
     * @@roseuid 46EA39FB018A
     */
    public WEB3AdminAioSLProductRegistControlServiceImpl()
    {

    }

    /**
     * (validate�S�ۖ����������)<BR>
     * ��������ŁA��������ԓ��̖��������݂��邩�`�F�b�N���s���B<BR>
     * ���݂���ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �P�j�@@����.�K�p����to ��null�̏ꍇ�A9999/12/31���Z�b�g<BR>
     * <BR>
     * �Q�j�@@����:�S�ۖ������̗v�f���ALoop����<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�S�ۖ����s�̎擾<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�S�ۖ����s.get�K�p����to = null �̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�P�j�@@�S�ۖ����s.get�K�p����from <=�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����.�K�p����from <= 9999/12/31<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02927<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�S�ۖ����s.get�K�p����to != null �̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�P�j�@@�@@�S�ۖ����s.get�K�p����from <= <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����.�K�p����from <= �S�ۖ����s.get�K�p����to<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02927<BR>
     * <BR>
     * <BR>
     * �@@�Q�|�S�j�@@����.�K�p����from <= �S�ۖ����s.get�K�p����from <= <BR>
     * �@@�@@�@@�@@�@@�@@ ����.�K�p����to<BR>
     * �@@�@@�@@�@@�@@�@@�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02927<BR>
     * <BR>
     * �@@�� �S�Ă̗�O�ɂ����āA�u��������A������Ԃ̒S�ۖ����o�^�����݂��܂��B<BR>
     * �@@�G���[���X���[<BR>
     * <BR>
     * @@param l_lisSecurityProductInfos - (�S�ۖ������)<BR>
     * �S�ۖ����o�^���<BR>
     * @@param l_datTargetPeriodFrom - (�K�p����from)<BR>
     * �K�p����from<BR>
     * @@param l_datTargetPeriodTo - (�K�p����to)<BR>
     * �K�p����to<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46D6819601A5
     */
    public void validateSecurityProductSameTerm(
        List l_lisSecurityProductInfos,
        Date l_datTargetPeriodFrom,
        Date l_datTargetPeriodTo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateSecurityProductSameTerm(List, Date, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_lisSecurityProductInfos == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //����.�K�p����to ��null�̏ꍇ�A9999/12/31���Z�b�g
        Date l_datTargetPeriod =
            WEB3DateUtility.getDate(WEB3GentradeTimeDef.MAX_YMD, WEB3GentradeTimeDef.DATE_SPLIT_YMD);
        if (l_datTargetPeriodTo == null)
        {
            l_datTargetPeriodTo = l_datTargetPeriod;
        }

        int l_intSecurityProductInfosSize = l_lisSecurityProductInfos.size();
        //�Q�j�@@����:�S�ۖ������̗v�f���ALoop����
        for (int i = 0; i < l_intSecurityProductInfosSize; i++)
        {
            //�Q�|�P�j�@@�S�ۖ����s�̎擾
            SecurityProductRow l_securityProductRow =
                (SecurityProductRow)l_lisSecurityProductInfos.get(i);

            Date l_datApplyTermFrom = l_securityProductRow.getApplyTermFrom();
            Date l_datApplyTermTo = l_securityProductRow.getApplyTermTo();
            //�Q�|�Q�j�@@�S�ۖ����s.get�K�p����to = null �̏ꍇ�A
            if (l_datApplyTermTo == null)
            {
                //�Q�|�Q�|�P�j�@@�S�ۖ����s.get�K�p����from <= ����.�K�p����from <= 9999/12/31
                //�̏ꍇ�A��O���X���[����B
                if (WEB3DateUtility.compareToDay(l_datTargetPeriodFrom, l_datApplyTermFrom) >= 0
                    && WEB3DateUtility.compareToDay(l_datTargetPeriodFrom, l_datTargetPeriod) <= 0)
                {
                    log.debug("��������A������Ԃ̒S�ۖ����o�^�����݂��܂��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02927,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "��������A������Ԃ̒S�ۖ����o�^�����݂��܂��B");
                }
            }
            //�Q�|�R�j�@@�S�ۖ����s.get�K�p����to != null �̏ꍇ�A
            else
            {
                //�Q�|�R�|�P�j�@@�@@�S�ۖ����s.get�K�p����from <= ����.�K�p����from <= �S�ۖ����s.get�K�p����to
                //�̏ꍇ�A��O���X���[����B
                if (WEB3DateUtility.compareToDay(l_datTargetPeriodFrom, l_datApplyTermFrom) >= 0
                    && WEB3DateUtility.compareToDay(l_datTargetPeriodFrom, l_datApplyTermTo) <= 0)
                {
                    log.debug("��������A������Ԃ̒S�ۖ����o�^�����݂��܂��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02927,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "��������A������Ԃ̒S�ۖ����o�^�����݂��܂��B");
                }
            }

            //�Q�|�S�j�@@����.�K�p����from <= �S�ۖ����s.get�K�p����from <= ����.�K�p����to
            //�̏ꍇ�A��O���X���[����B
            if (WEB3DateUtility.compareToDay(l_datApplyTermFrom, l_datTargetPeriodFrom) >= 0
                && WEB3DateUtility.compareToDay(l_datApplyTermFrom, l_datTargetPeriodTo) <= 0)
            {
                log.debug("��������A������Ԃ̒S�ۖ����o�^�����݂��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02927,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��������A������Ԃ̒S�ۖ����o�^�����݂��܂��B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert�S�ۖ������)<BR>
     * �S�ۖ����e�[�u���ɒS�ۖ�������insrt����B<BR>
     * <BR>
     * �� �ڍׂ�DB�X�V�d�l�u�S�ۖ����o�^_�S�ۖ����e�[�u��.xls�v<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_stockLoanProductInfo - (�����o�^���)<BR>
     * �S�ۖ����o�^���I�u�W�F�N�g<BR>
     * @@param l_strAdministratorCode - (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46D7BD790062
     */
    public void insertSecurityProductInfo(
        String l_strInstitutionCode,
        WEB3SLProductInfoUnit l_stockLoanProductInfo,
        String l_strAdministratorCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertSecurityProductInfo(String, WEB3SLProductInfoUnit, String)";
        log.entering(STR_METHOD_NAME);

        if (l_stockLoanProductInfo == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        SecurityProductParams l_securityProductParams = new SecurityProductParams();

        //�����h�c
        //�����o�^���.����ID
        l_securityProductParams.setProductId(l_stockLoanProductInfo.productId);

        //�،���ЃR�[�h
        //����:�،���ЃR�[�h
        l_securityProductParams.setInstitutionCode(l_strInstitutionCode);

        //�����R�[�h
        //�����o�^���.�����R�[�h
        l_securityProductParams.setProductCode(l_stockLoanProductInfo.productCode);

        //�����^�C�v
        //�����o�^���.�����^�C�v
        ProductTypeEnum l_productTypeEnum =
            (ProductTypeEnum)EnumeratedManager.getInstance().valueFromInt(
                ProductTypeEnum.class,
                Integer.parseInt(l_stockLoanProductInfo.productType));

        l_securityProductParams.setProductType(l_productTypeEnum);

        //�]���|��
        //�����o�^���.�|�� (null�̏ꍇ��0���Z�b�g)
        if (l_stockLoanProductInfo.weight != null)
        {
            l_securityProductParams.setEstimationRatio(Double.parseDouble(l_stockLoanProductInfo.weight));
        }
        else
        {
            l_securityProductParams.setEstimationRatio(0);
        }

        //�K�i�敪
        //�����o�^���.�K�i�敪
        l_securityProductParams.setFitFlg(l_stockLoanProductInfo.qualifiedDiv);

        //�K�p����from
        //�����o�^���.�K�p����from
        l_securityProductParams.setApplyTermFrom(l_stockLoanProductInfo.targetPeriodFrom);

        //�K�p����to
        //�����o�^���.�K�p����to
        //null�̏ꍇ��9999/12/31���Z�b�g
        if (l_stockLoanProductInfo.targetPeriodTo == null)
        {
            l_securityProductParams.setApplyTermTo(
                WEB3DateUtility.getDate(WEB3GentradeTimeDef.MAX_YMD, WEB3GentradeTimeDef.DATE_SPLIT_YMD));
        }
        else
        {
            l_securityProductParams.setApplyTermTo(l_stockLoanProductInfo.targetPeriodTo);
        }

        //���R
        //�����o�^���.���R
        l_securityProductParams.setReason(l_stockLoanProductInfo.reason);

        //�X�V�҃R�[�h
        //����:�Ǘ��҃R�[�h
        l_securityProductParams.setLastUpdater(l_strAdministratorCode);

        //�쐬���t
        //TradingSystem.getSystemTimestamp()
        l_securityProductParams.setCreatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        //�X�V���t
        //TradingSystem.getSystemTimestamp()
        l_securityProductParams.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_securityProductParams);

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
     * (get�S�ۖ����s)<BR>
     * ��L�[����S�ۖ����s���擾����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�S�ۖ����e�[�u�����烌�R�[�h���擾����B<BR>
     * �@@[��������]<BR>
     * �@@����ID = ����:����ID<BR>
     * �@@�K�p����from = ����:�K�p����from<BR>
     * <BR>
     * 2) �擾�����S�ۖ����s��ԋp����B<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_datTargetPeriodFrom - (�K�p����from)<BR>
     * �K�p����from<BR>
     * @@throws WEB3BaseException
     * @@return SecurityProductRow
     * @@roseuid 46DCF0420182
     */
    public SecurityProductRow getSecurityProductRow(
        long l_lngProductId,
        Date l_datTargetPeriodFrom)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSecurityProductRow(long, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_datTargetPeriodFrom == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�j�@@�S�ۖ����e�[�u�����烌�R�[�h���擾����B
        //[��������]
        //����ID = ����:����ID
        //�K�p����from = ����:�K�p����from
        SecurityProductRow l_securityProductRow = null;

        try
        {
            l_securityProductRow =
                SecurityProductDao.findRowByPk(
                    l_lngProductId,
                    new Timestamp(l_datTargetPeriodFrom.getTime()));
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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

        //�擾�����S�ۖ����s��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_securityProductRow;
    }

    /**
     * (compare�ύX���)<BR>
     * �ύX�O�ƕύX��̏�Ԃ��r����B<BR>
     * <BR>
     * �P�j�ȉ��̍��ڑS�āAthis.is���ڕύX�i�j���R�[�����A�S�č��ق��Ȃ��ꍇ�A<BR>
     * �@@�P��ԋp�B<BR>
     * �@@1�ł����ق����݂���ꍇ��0��ԋp����B<BR>
     * <BR>
     * [��r�Ώƍ���]<BR>
     * �E�K�i�敪<BR>
     * �E�|��<BR>
     * �E�K�p����from<BR>
     * �E�K�p����to<BR>
     * �E���R<BR>
     * @@param l_changeBeforeSecurityProductInfo - (�ύX�O�S�ۖ������)<BR>
     * �ύX�O�S�ۖ������<BR>
     * @@param l_changeAfterSecurityProductInfo - (�ύX��S�ۖ������)<BR>
     * �ύX��S�ۖ������<BR>
     * @@return int
     * @@throws WEB3BaseException
     * @@roseuid 46DD19C802A1
     */
    public int compareChangeInfo(
        SecurityProductRow l_changeBeforeSecurityProductInfo,
        WEB3SLProductInfoUnit l_changeAfterSecurityProductInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "compareChangeInfo(SecurityProductRow, WEB3SLProductInfoUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_changeBeforeSecurityProductInfo == null || l_changeAfterSecurityProductInfo == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�ȉ��̍��ڑS�āAthis.is���ڕύX�i�j���R�[�����A
        //�K�i�敪
        boolean l_blnIsFitFlg = this.isItemChange(
            l_changeBeforeSecurityProductInfo.getFitFlg(),
            l_changeAfterSecurityProductInfo.qualifiedDiv);

        //�|��
        boolean l_blnIsWeight = this.isItemChange(
            WEB3StringTypeUtility.formatNumber(l_changeBeforeSecurityProductInfo.getEstimationRatio()),
            l_changeAfterSecurityProductInfo.weight);

        //�K�p����from
        boolean l_blnIsApplyTermFrom = this.isItemChange(
            WEB3DateUtility.formatDate(l_changeBeforeSecurityProductInfo.getApplyTermFrom(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD),
            WEB3DateUtility.formatDate(l_changeAfterSecurityProductInfo.targetPeriodFrom,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //�K�p����to
        boolean l_blnIsApplyTermTo = this.isItemChange(
            WEB3DateUtility.formatDate(l_changeBeforeSecurityProductInfo.getApplyTermTo(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD),
            WEB3DateUtility.formatDate(l_changeAfterSecurityProductInfo.targetPeriodTo,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //���R
        boolean l_blnIsReason = this.isItemChange(
            l_changeBeforeSecurityProductInfo.getReason(),
            l_changeAfterSecurityProductInfo.reason);

        //�S�č��ق��Ȃ��ꍇ�A�P��ԋp�B
        if (!l_blnIsFitFlg
            && !l_blnIsWeight
            && !l_blnIsApplyTermFrom
            && !l_blnIsApplyTermTo
            && !l_blnIsReason)
        {
            log.exiting(STR_METHOD_NAME);
            return 1;
        }

        //1�ł����ق����݂���ꍇ��0��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (update�S�ۖ������)<BR>
     * 1) �����L�[���ɕR�t�����R�[�h���폜����B<BR>
     * <BR>
     * 2) �����̒S�ۖ���Row��insert����B<BR>
     * <BR>
     * ���ڍׂ�DB�X�V�d�l<BR>
     * �@@�u�S�ۖ����ύX_�S�ۖ����e�[�u��.xls�v���Q��<BR>
     * @@param l_searchKeyConditions - (�����L�[���)<BR>
     * �S�ۖ��������L�[���<BR>
     * @@param l_securityProductRow - (�S�ۖ���Row)<BR>
     * �S�ۖ���Row<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DD4BCF03C8
     */
    public void updateSecurityProductInfo(
        WEB3SLProductSearchConditions l_searchKeyConditions,
        SecurityProductRow l_securityProductRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateSecurityProductInfo(WEB3SLProductSearchConditions, SecurityProductRow)";
        log.entering(STR_METHOD_NAME);

        if (l_searchKeyConditions == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            SecurityProductPK l_securityProductPk = new SecurityProductPK(
                l_searchKeyConditions.productId,
                new Timestamp(l_searchKeyConditions.targetPeriodFrom.getTime()));

            // 1) �����L�[���ɕR�t�����R�[�h���폜����B
            l_queryProcessor.doDeleteQuery(l_securityProductPk);

            // 2) �����̒S�ۖ���Row��insert����B
            l_queryProcessor.doInsertQuery(l_securityProductRow);

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
     * (delete�S�ۖ������)<BR>
     * ��L�[��ΏۂɒS�ۖ����e�[�u���̃��R�[�h���폜����B<BR>
     * @@param l_deleteObjectKey - (�폜�ΏۃL�[)<BR>
     * �폜���R�[�h�ΏۃL�[<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DE41620259
     */
    public void deleteSecurityProductInfo(WEB3SLProductSearchConditions l_deleteObjectKey)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "deleteSecurityProductInfo(WEB3SLProductSearchConditions)";
        log.entering(STR_METHOD_NAME);

        if (l_deleteObjectKey == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        try
        {
            SecurityProductPK l_securityProductPK = new SecurityProductPK(
                l_deleteObjectKey.productId,
                new Timestamp(l_deleteObjectKey.targetPeriodFrom.getTime()));

            //��L�[��ΏۂɒS�ۖ����e�[�u���̃��R�[�h���폜����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteQuery(l_securityProductPK);
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
     * (get�S�ۖ������)<BR>
     * ����ID���L�[�ɒS�ۖ����e�[�u���̃��R�[�h���擾���A�ԋp����B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA�S�ۖ����e�[�u���̃��R�[�h������<BR>
     * [��������]<BR>
     * ����ID�F ����:����ID<BR>
     * <BR>
     * �Q�j�擾��������ԋp����B<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@throws WEB3BaseException
     * @@return List
     * @@roseuid 46DE4FFE02BE
     */
    public List getSecurityProductInfo(long l_lngProductId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSecurityProductInfo(long)";
        log.entering(STR_METHOD_NAME);

        List l_lisSecurityProducts = new ArrayList();

        String l_strWhere = "product_id = ?";
        Object[] l_whereValues = {new Long(l_lngProductId)};

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisSecurityProducts = l_queryProcessor.doFindAllQuery(
                SecurityProductRow.TYPE,
                l_strWhere,
                l_whereValues);
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

        //�擾��������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisSecurityProducts;
    }

    /**
     * (is���ڕύX)<BR>
     * ���ڂ��ύX����Ă��邩�𔻕ʂ���B<BR>
     * <BR>
     * �P�j�ύX�O���ڂƕύX�㍀�ڂ��r���A�l���ύX����Ă���ꍇtrue��<BR>
     * �@@�ύX���̏ꍇ��false��ԋp����B<BR>
     * @@param l_strChangeBeforeItem - (�ύX�O����)<BR>
     * �ύX�O����(BR)
     * @@param l_strChangeAfterItem - (�ύX�㍀��)<BR>
     * �ύX�㍀��<BR>
     * @@return boolean
     * @@roseuid 46DE65DA03E5
     */
    public boolean isItemChange(String l_strChangeBeforeItem, String l_strChangeAfterItem)
    {
        final String STR_METHOD_NAME = "isItemChange(String, String)";
        log.entering(STR_METHOD_NAME);

        if (!WEB3Toolkit.isEquals(l_strChangeBeforeItem, l_strChangeAfterItem))
        {
            //�ύX�O���ڂƕύX�㍀�ڂ��r���A�l���ύX����Ă���ꍇtrue��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�ύX���̏ꍇ��false��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
