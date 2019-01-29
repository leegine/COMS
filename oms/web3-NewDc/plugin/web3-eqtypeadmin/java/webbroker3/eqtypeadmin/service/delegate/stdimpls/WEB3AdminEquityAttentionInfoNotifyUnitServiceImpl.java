head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ӏ��ʒm�ꌏ�T�[�r�XImpl(WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 ������(���u) �V�K�쐬 ���f��No.219 �c�a�X�V�d�lNo.022,No.023,No.024
Revision History : 2009/01/21 �И���(���u) �d�l�ύX���f��No.232
Revision History : 2009/02/12 �И���(���u) �d�l�ύX���f��No.236
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AttentionInfoCompTakingDivDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3PriceRangeIdDef;
import webbroker3.common.define.WEB3PriceRangeTypeDef;
import webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyParams;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityProductUpdateFlagDef;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.data.InstitutionPreferencesDao;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ӏ��ʒm�ꌏ�T�[�r�XImpl)<BR>
 * ���ӏ��ʒm�ꌏ�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl
    implements WEB3AdminEquityAttentionInfoNotifyUnitService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 49588AEE03C8
     */
    public WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl()
    {

    }

    /**
     * (notify�����l�����)<BR>
     * ���ӏ��i�����l�����j�������s�Ȃ��B<BR>
     * �����̍X�V�������s�Ȃ��Ă���ꍇ�́u1�F�����X�V�ς݁v�� <BR>
     * �����̍X�V�������s�Ȃ��Ă��Ȃ��ꍇ�́u2�F�������X�V�v��ԋp����B <BR>
     * <BR>
     * �P�j�@@�L���[�ɕK�v�ȏ�񂪊i�[����Ă��Ȃ��ꍇ�A�u2�F�������X�V�v��return����B <BR>
     * �@@�@@�@@[����] <BR>
     * �@@�@@�@@�@@���ӏ��ʒm�L���[�e�[�u��.��l��null <BR>
     * <BR>
     * �Q�j�@@�،���Ѓv���t�@@�����X�e�[�u�����擾����B <BR>
     * �@@�@@�@@[��������] <BR>
     * �@@�@@�@@�@@�،���Ђh�c�F�@@�،����.�،���Ђh�c <BR>
     * �@@�@@�@@�@@�v���t�@@�����X���F�@@"attention.info.comp.taking.div" <BR>
     * �@@�@@�@@�@@�v���t�@@�����X���̘A�ԁF�@@"1"�i�Œ�j <BR>
     * <BR>
     * �R�j�@@�Q�j�ŏ،���Ѓv���t�@@�����X�e�[�u�����擾�o���Ȃ������ꍇ���́A <BR>
     * �@@�@@�@@�،���Ѓv���t�@@�����X�e�[�u��.�v���t�@@�����X�̒l��"�捞�܂Ȃ�"�̏ꍇ�A <BR>
     * �@@�@@�@@�u2�F�������X�V�v��return����B <BR>
     * <BR>
     * �S�j�@@�����A������������A�����������UPDQ�I�u�W�F�N�g�̃N���[�����쐬����B <BR>
     * �@@�@@�@@�ȍ~�́A���������I�u�W�F�N�g�ɑ΂��X�V�������s�Ȃ��B <BR>
     * <BR>
     * �T�j�@@����.�D��s��ID��null�̏ꍇ�@@���́A <BR>
     * �@@�@@�@@����.�D��s��ID�ƒ��ӏ��ʒm�L���[�e�[�u��.�s��R�[�h�iSONAR�j�� <BR>
     * �@@�@@�@@�Y������s��ID���������ꍇ�A�������X�V����B <BR>
     * �@@�@@�@@���X�V���e�́ADB�X�V�d�l���w���ӏ��ʒm_�����e�[�u��.xls�x�Q�ƁB <BR>
     * <BR>
     * �U�j�@@��������������X�V����B <BR>
     * �@@�@@�@@���X�V���e�́ADB�X�V�d�l���w���ӏ��ʒm_������������}�X�^�e�[�u��.xls�x�Q�ƁB <BR>
     * <BR>
     * �V�j�@@�����������UPDQ���擾���Ă���ꍇ�A�����������UPDQ���X�V����B <BR>
     * �@@�@@�@@���X�V���e�́ADB�X�V�d�l���w���ӏ��ʒm_������������}�X�^updq�e�[�u��.xls�x�Q�ƁB <BR>
     * <BR>
     * �W�j�@@�u1�F�����X�V�ς݁v��return����B<BR>
     * @@param l_hostAttentionInfoNotifyParams - (���ӏ��ʒm�L���[�e�[�u��)<BR>
     * ���ӏ��ʒm�L���[�e�[�u��<BR>
     * @@param l_eqtypeTradedProductRow - (�����������)<BR>
     * ������������I�u�W�F�N�g<BR>
     * @@param l_eqtypeTradedProductUpdqRow - (�����������updq)<BR>
     * �����������updq<BR>
     * @@param l_productRow - (����)<BR>
     * ����<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4938744503A0
     */
    public String notifyLimitRangeInfo(
        HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams,
        EqtypeTradedProductRow l_eqtypeTradedProductRow,
        EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow,
        ProductRow l_productRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyLimitRangeInfo(HostAttentionInfoNotifyParams, EqtypeTradedProductRow,"
            + " EqtypeTradedProductUpdqRow, ProductRow)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�L���[�ɕK�v�ȏ�񂪊i�[����Ă��Ȃ��ꍇ�A�u2�F�������X�V�v��return����B
        //[����]
        //     ���ӏ��ʒm�L���[�e�[�u��.��l��null
        if (l_hostAttentionInfoNotifyParams.getBasePriceIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AdminEquityProductUpdateFlagDef.PRODUCT_NOT_UPDATE;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Institution l_institution = null;
        try
        {
            l_institution = l_accountManager.getInstitution(l_hostAttentionInfoNotifyParams.getInstitutionCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@�،���Ѓv���t�@@�����X�e�[�u�����擾����B
        //�@@�@@�@@[��������]
        //�@@�@@�@@�@@�،���Ђh�c�F�@@�،����.�،���Ђh�c
        //�@@�@@�@@�@@�v���t�@@�����X���F�@@"attention.info.comp.taking.div"
        //�@@�@@�@@�@@�v���t�@@�����X���̘A�ԁF�@@"1"�i�Œ�j
        InstitutionPreferencesRow l_institutionPreferencesRow = null;
        try
        {
            l_institutionPreferencesRow =
                InstitutionPreferencesDao.findRowByInstitutionIdNameNameSerialNo(
                    l_institution.getInstitutionId(),
                    WEB3InstitutionPreferencesNameDef.ATTENTION_INFO_COMP_TAKING_DIV,
                    1);
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

        //�R�j�@@�Q�j�ŏ،���Ѓv���t�@@�����X�e�[�u�����擾�o���Ȃ������ꍇ���́A
        //   �@@�@@�@@�،���Ѓv���t�@@�����X�e�[�u��.�v���t�@@�����X�̒l��"�捞�܂Ȃ�"�̏ꍇ�A
        //   �@@�@@�@@�u2�F�������X�V�v��return����B
        if (l_institutionPreferencesRow == null
            || WEB3AttentionInfoCompTakingDivDef.DEFAULT.equals(l_institutionPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AdminEquityProductUpdateFlagDef.PRODUCT_NOT_UPDATE;
        }

        //�S�j�@@�����A������������A�����������UPDQ�I�u�W�F�N�g�̃N���[�����쐬����B
        //�ȍ~�́A���������I�u�W�F�N�g�ɑ΂��X�V�������s�Ȃ��B
        ProductParams l_productParams = new ProductParams(l_productRow);
        EqtypeTradedProductParams l_eqtypeTradedProductParams =
            new EqtypeTradedProductParams(l_eqtypeTradedProductRow);

        try
        {
            //�T�j�@@����.�D��s��ID��null�̏ꍇ�@@���́A
            //����.�D��s��ID�ƒ��ӏ��ʒm�L���[�e�[�u��.�s��R�[�h�iSONAR�j��
            //�Y������s��ID���������ꍇ�A�������X�V����B
            //���X�V���e�́ADB�X�V�d�l���w���ӏ��ʒm_�����e�[�u��.xls�x�Q�ƁB
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            if (l_productRow.getPrimaryMarketIdIsNull())
            {
                //�]���P�� = ���ӏ��ʒm�L���[.��l
                l_productParams.setEstimationPrice(l_hostAttentionInfoNotifyParams.getBasePrice());
                //�X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h
                l_productParams.setLastUpdater(l_hostAttentionInfoNotifyParams.getRequestCode());
                //�X�V���t = ���ݓ����iGtlUtils.getSystemTimestamp()�j
                l_productParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                l_processor.doUpdateQuery(l_productParams);
            }
            else
            {
                WEB3GentradeFinObjectManager l_finObjectManager =
                    (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                String l_strSonarMarketCode = l_hostAttentionInfoNotifyParams.getSonarMarketCode();

                Market l_market =
                    l_finObjectManager.getMarketBySONAR(l_institution.getInstitutionCode(), l_strSonarMarketCode);

                if (l_productRow.getPrimaryMarketId() == l_market.getMarketId())
                {
                    //�]���P�� = ���ӏ��ʒm�L���[.��l
                    l_productParams.setEstimationPrice(l_hostAttentionInfoNotifyParams.getBasePrice());
                    //�X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h
                    l_productParams.setLastUpdater(l_hostAttentionInfoNotifyParams.getRequestCode());
                    //�X�V���t = ���ݓ����iGtlUtils.getSystemTimestamp()�j
                    l_productParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_processor.doUpdateQuery(l_productParams);
                }
            }

            //�U�j�@@��������������X�V����B
            //�@@�@@�@@���X�V���e�́ADB�X�V�d�l���w���ӏ��ʒm_������������}�X�^�e�[�u��.xls�x�Q�ƁB
            //��l�i�I�l�j  = ���ӏ��ʒm�L���[.��l
            l_eqtypeTradedProductParams.setLastClosingPrice(l_hostAttentionInfoNotifyParams.getBasePrice());
            //�l���`�F�b�N�敪 = "1:�l���`�F�b�N����"
            l_eqtypeTradedProductParams.setPriceRangeType(WEB3PriceRangeTypeDef.CHECK);
            //�����l���i����l�j =
            //���ӏ��ʒm�L���[.�����l�������null����
            //���ӏ��ʒm�L���[.��l��null�̏ꍇ
            //���ӏ��ʒm�L���[.�����l�����-���ӏ��ʒm�L���[.��l
            //�@@�i�������A�v�Z���ʂ�0�ȉ��̏ꍇ��null���Z�b�g�j
            //��L�ȊO�̏ꍇ�Anull
            if (!l_hostAttentionInfoNotifyParams.getHighPriceRangeIsNull()
                && !l_hostAttentionInfoNotifyParams.getBasePriceIsNull())
            {
                //���ӏ��ʒm�L���[.�����l�����
                double l_dblHighPriceRange = l_hostAttentionInfoNotifyParams.getHighPriceRange();
                BigDecimal l_bdHighPriceRange = new BigDecimal("" + l_dblHighPriceRange);

                //���ӏ��ʒm�L���[.��l
                double l_dblBasePrice = l_hostAttentionInfoNotifyParams.getBasePrice();
                BigDecimal l_bdBasePrice = new BigDecimal("" + l_dblBasePrice);

                double l_dblHighCompulsivePriceRange = l_bdHighPriceRange.subtract(l_bdBasePrice).doubleValue();
                if (GtlUtils.Double.isZero(l_dblHighCompulsivePriceRange) || l_dblHighCompulsivePriceRange < 0)
                {
                    l_eqtypeTradedProductParams.setHighCompulsivePriceRange(null);
                }
                else
                {
                    l_eqtypeTradedProductParams.setHighCompulsivePriceRange(l_dblHighCompulsivePriceRange);
                }
            }
            else
            {
                l_eqtypeTradedProductParams.setHighCompulsivePriceRange(null);
            }

            //�����l���i�����l�j
            //���ӏ��ʒm�L���[.�����l��������null����
            //���ӏ��ʒm�L���[.��l��null�̏ꍇ
            //���ӏ��ʒm�L���[.��l-���ӏ��ʒm�L���[.�����l������
            //�i�������A�v�Z���ʂ�0�ȉ��̏ꍇ��null���Z�b�g�j
            //��L�ȊO�̏ꍇ�Anull
            if (!l_hostAttentionInfoNotifyParams.getLowPriceRangeIsNull()
                && !l_hostAttentionInfoNotifyParams.getBasePriceIsNull())
            {
                //���ӏ��ʒm�L���[.�����l������
                double l_dblLowPriceRange = l_hostAttentionInfoNotifyParams.getLowPriceRange();
                BigDecimal l_bdLowPriceRange = new BigDecimal("" + l_dblLowPriceRange);

                //���ӏ��ʒm�L���[.��l
                double l_dblBasePrice = l_hostAttentionInfoNotifyParams.getBasePrice();
                BigDecimal l_bdBasePrice = new BigDecimal("" + l_dblBasePrice);

                double l_dblLowCompulsivePriceRange = l_bdBasePrice.subtract(l_bdLowPriceRange).doubleValue();
                if (GtlUtils.Double.isZero(l_dblLowCompulsivePriceRange) || l_dblLowCompulsivePriceRange < 0)
                {
                    l_eqtypeTradedProductParams.setLowCompulsivePriceRange(null);
                }
                else
                {
                    l_eqtypeTradedProductParams.setLowCompulsivePriceRange(l_dblLowCompulsivePriceRange);
                }
            }
            else
            {
                l_eqtypeTradedProductParams.setLowCompulsivePriceRange(null);
            }

            //�l���敪���X�V
            if (!l_eqtypeTradedProductParams.getHighCompulsivePriceRangeIsNull()
                && !l_eqtypeTradedProductParams.getLowCompulsivePriceRangeIsNull())
            {
                l_eqtypeTradedProductParams.setPriceRangeUnitType(WEB3PriceRangeIdDef.YEN);
            }
            //�X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h
            l_eqtypeTradedProductParams.setLastUpdater(l_hostAttentionInfoNotifyParams.getRequestCode());
            //�X�V���t = ���ݓ����iGtlUtils.getSystemTimestamp()�j
            l_eqtypeTradedProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            //��l = ���ӏ��ʒm�L���[.��l
            l_eqtypeTradedProductParams.setBasePrice(l_hostAttentionInfoNotifyParams.getBasePrice());
            l_processor.doUpdateQuery(l_eqtypeTradedProductParams);

            //�V�j�@@�����������UPDQ���擾���Ă���ꍇ�A�����������UPDQ���X�V����B
            //�@@�@@�@@���X�V���e�́ADB�X�V�d�l���w���ӏ��ʒm_������������}�X�^updq�e�[�u��.xls�x�Q�ƁB
            if (l_eqtypeTradedProductUpdqRow != null)
            {
                EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                    new EqtypeTradedProductUpdqParams(l_eqtypeTradedProductUpdqRow);
                //��l�i�I�l�j = ���ӏ��ʒm�L���[.��l
                l_eqtypeTradedProductUpdqParams.setLastClosingPrice(l_hostAttentionInfoNotifyParams.getBasePrice());
                //�X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h
                l_eqtypeTradedProductUpdqParams.setLastUpdater(l_hostAttentionInfoNotifyParams.getRequestCode());
                //�X�V���t = ���ݓ����iGtlUtils.getSystemTimestamp()�j
                l_eqtypeTradedProductUpdqParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //��l = ���ӏ��ʒm�L���[.��l
                l_eqtypeTradedProductUpdqParams.setBasePrice(l_hostAttentionInfoNotifyParams.getBasePrice());
                l_processor.doUpdateQuery(l_eqtypeTradedProductUpdqParams);
            }
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

        //�W�j�@@�u1�F�����X�V�ς݁v��return����B
        log.exiting(STR_METHOD_NAME);
        return WEB3AdminEquityProductUpdateFlagDef.PRODUCT_UPDATE;
    }
}
@
