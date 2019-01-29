head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondConfigUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ݒ���(WEB3AdminPMProductCondConfigUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.eqtypeadmin.define.WEB3AdminEquityLargeItemDivDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySmallItemDivDef;

/**
 * (���������ݒ���)<BR>
 * <BR>
 * ���������ݒ���N���X<BR>
 * <BR>
 * WEB3AdminPMProductCondConfigUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondConfigUnit extends WEB3AdminPMProductCondConfigCommon
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondConfigUnit.class);

    /**
     * �i�s��R�[�h�j<BR>
     * <BR>
     * �s��R�[�h<BR>
     * <BR>
     * 0�F�@@���̑�(�s�ꋤ��)<BR>
     * 1�F�@@����<BR>
     * 2�F�@@���<BR>
     * 3�F�@@���É�<BR>
     * 6�F�@@����<BR>
     * 8�F�@@�D�y<BR>
     * 9�F�@@NNM<BR>
     * 10�F�@@JASDAQ<BR>
     * <BR>
     * --------<English>-------------------<BR>
     * <BR>
     * MarketCode<BR>
     * <BR>
     * 0: Def.DEFAULT(common to markets)<BR>
     * 1: Def.TOKYO<BR>
     * 2: Def.OSAKA<BR>
     * 3: Def.NAGOYA<BR>
     * 6: Def.FUKUOKA<BR>
     * 8: Def.SAPPORO<BR>
     * 9: Def.NNM<BR>
     * 10: Def.JASDAQ<BR>
     * <BR>
     */
    public String marketCode = null;

    /**
     * �i�o�^�l(����)�j<BR>
     * <BR>
     * �o�^�l(����)<BR>
     * <BR>
     * ����̏��A�區�ڋ敪�ȊO�́A�����񂪃Z�b�g�����B<BR>
     * <BR>
     * ��AP�w�ōŐVDB�f�[�^���Z�b�g�B<BR>
     * <BR>
     * ����̏��A�區�ڋ敪�ɂ��ẮA<BR>
     * �u�y�⑫�����z�������������ݒ�o�^�l�R�[�h��`�v<BR>
     * �Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * bizDateRegistData<BR>
     * <BR>
     * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     * ��Set the latest DB data in AP layer<BR>
     * <BR>
     * Refer to "[Supplement] equity product condition regist data code def list" about
     * specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     */
    public String bizDateRegistData = null;

    /**
     * �i�o�^�l(����)�j<BR>
     * <BR>
     * ����̏��A�區�ڋ敪�ȊO�́A�����񂪃Z�b�g�����B<BR>
     * <BR>
     * ��AP�w�ōŐVDB�f�[�^���Z�b�g�B<BR>
     * <BR>
     * ����̏��A�區�ڋ敪�ɂ��ẮA<BR>
     * �u�y�⑫�����z�������������ݒ�o�^�l�R�[�h��`�v<BR>
     * �Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * nextBizDateRegistData<BR>
     * <BR>
     * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     * ��Set the latest DB data in AP layer<BR>
     * <BR>
     * Refer to "[Supplement] equity product condition regist data code def list" about
     * specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     */
    public String nextBizDateRegistData = null;

    /**
     * �i�o�^�l(�\��)�j<BR>
     * <BR>
     * ����̏��A�區�ڋ敪�ȊO�́A�����񂪃Z�b�g�����B<BR>
     * <BR>
     * ��AP�w�ōŐVDB�f�[�^���Z�b�g�B<BR>
     * <BR>
     * ����̏��A�區�ڋ敪�ɂ��ẮA<BR>
     * �u�y�⑫�����z�������������ݒ�o�^�l�R�[�h��`�v<BR>
     * �Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * scheduleRegistData<BR>
     * <BR>
     * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     * ��Set the latest DB data in AP layer<BR>
     * <BR>
     * Refer to "[Supplement] equity product condition regist data code def list" about
     * specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     */
    public String scheduleRegistData = null;

    /**
     * �i�K�p����From�j<BR>
     * <BR>
     * �\��̓K�p����From<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ��AP�w�ōŐVDB�f�[�^���Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * applyStartDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ��Set the latest DB data in AP layer<BR>
     * <BR>
     */

    public String applyStartDate = null;

    /**
     * �i�K�p����To�j
     * <BR>
     * �\��̓K�p����To<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ��AP�w�ōŐVDB�f�[�^���Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * applyEndDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ��Set the latest DB data in AP layer<BR>
     * <BR>
     */
    public String applyEndDate = null;

    /**
     * �i�ύX��o�^�l(����)�j<BR>
     * <BR>
     * �ύX��o�^�l(����)<BR>
     * <BR>
     * ����̏��A�區�ڋ敪�ȊO�́A�����񂪃Z�b�g�����B<BR>
     * <BR>
     * ��PR�w�ł̓��͒l���Z�b�g�B<BR>
     * <BR>
     * ����̏��A�區�ڋ敪�ɂ��ẮA<BR>
     * �u�y�⑫�����z�������������ݒ�o�^�l�R�[�h��`�v<BR>
     * �Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * aftBizDateRegistData<BR>
     * <BR>
     * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     * ��Set input values in PR layer<BR>
     * <BR>
     * Refer to "[Supplement] equity product condition regist data code def list" about
     * specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     */
    public String aftBizDateRegistData = null;

    /**
     * �i�ύX��o�^�l(����)�j<BR>
     * <BR>
     * �ύX��o�^�l(����)<BR>
     * <BR>
     * ����̏��A�區�ڋ敪�ȊO�́A�����񂪃Z�b�g�����B<BR>
     * <BR>
     * ��PR�w�ł̓��͒l���Z�b�g�B<BR>
     * <BR>
     * ����̏��A�區�ڋ敪�ɂ��ẮA<BR>
     * �u�y�⑫�����z�������������ݒ�o�^�l�R�[�h��`�v<BR>
     * �Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * aftNextBizDateRegistData<BR>
     * <BR>
     * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     * ��Set input values in PR layer<BR>
     * <BR>
     * Refer to "[Supplement] equity product condition regist data code def list" about
     * specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     */
    public String aftNextBizDateRegistData = null;

    /**
     * �i�ύX��o�^�l(�\��)�j<BR>
     * <BR>
     * �ύX��o�^�l(�\��)<BR>
     * <BR>
     * ����̏��A�區�ڋ敪�ȊO�́A�����񂪃Z�b�g�����B<BR>
     * <BR>
     * ��PR�w�ł̓��͒l���Z�b�g�B<BR>
     * <BR>
     * ����̏��A�區�ڋ敪�ɂ��ẮA<BR>
     * �u�y�⑫�����z�������������ݒ�o�^�l�R�[�h��`�v<BR>
     * �Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * aftScheduleRegistData<BR>
     * <BR>
     * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     * ��Set input values in PR layer<BR>
     * <BR>
     * Refer to "[Supplement] equity product condition regist data code def list" about
     * specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     */
    public String aftScheduleRegistData = null;

    /**
     * �i�ύX��K�p����From�j<BR>
     * <BR>
     * �ύX��K�p����From<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ��PR�w�ł̓��͒l���Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * aftApplyStartDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ��Set input values in PR layer<BR>
     * <BR>
     */
    public String aftApplyStartDate = null;

    /**
     * �i�ύX��K�p����To�j<BR>
     * <BR>
     * �ύX��K�p����To<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ��PR�w�ł̓��͒l���Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * aftApplyEndDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ��Set input values in PR layer<BR>
     * <BR>
     */
    public String aftApplyEndDate = null;

	/**
	 * �i�X�V�҃R�[�h�j<BR>
	 * <BR>
	 * �X�V�҃R�[�h<BR>
	 */
	public String lastUpdater = null;


    /**
     * (�R���X�g���N�^)<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
     * @@roseuid 4185DE65011B
     */
    public WEB3AdminPMProductCondConfigUnit()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���͓��e�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�區�ځA�����ڋ敪���ȉ��ɊY������ꍇ�A�`�F�b�N���s���B<BR>
     * �@@�@@[this.�區�ڋ敪 == "�ϑ��ۏ؋���"�̏ꍇ�@@�܂���<BR>
     * �@@�@@�@@this.�����ڋ敪 == ("�����P��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�������x�P��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"��p�|��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"��p�]���P��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�a��،��]���|��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"��l(�I�l)" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"��l" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�����l��(����)" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�����l��(���)")�@@�̏ꍇ]<BR>
     * �@@�@@�@@this.�ύX��o�^�l(����)�Athis.�ύX��o�^�l(����)�Athis.�ύX��o�^�l(�\��)�̓�<BR>
     * �@@�@@�@@���Âꂩ���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�u���͐��l�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�ύX��o�^�l != null�@@���@@�ύX��o�^�l != ���l<BR>
     * �@@�@@�@@�@@�E�ύX��o�^�l != null�@@���@@�ύX��o�^�l < 0<BR>
     * �@@�@@�@@�@@�E�ύX��o�^�l != null�@@���@@�ύX��o�^�l.length > 12<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01441<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�����ڋ敪 == "�������x�P��"�̏ꍇ��<BR>
     * �@@�@@�@@�ύX��o�^�l != null�@@���@@�ύX��o�^�l.length > 7�̏ꍇ�A<BR>
     * �@@�@@�@@�u���͐��l�G���[�v�Ƃ���B<BR>
     * <BR>
     * �@@�P�|�R�jthis.�����ڋ敪 == "������"�̏ꍇ��<BR>
     * �@@�@@�@@�ύX��o�^�l != null�@@���@@�ύX��o�^�l.getbyte().length > 50�̏ꍇ�A<BR>
     * �@@�@@�@@�u���������̓G���[(��������)�v�Ƃ���B<BR>
     * <BR>
     * �Q�j�K�p����From�`�F�b�N<BR>
     * �@@this.�ύX��K�p����From != null�̏ꍇ�A<BR>
     * �@@�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�jthis.�ύX��K�p����From����t�^�ɕϊ��ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͓��t�G���[(�K�p����From)�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01442<BR>
     * <BR>
     * �R�j�K�p����To�`�F�b�N<BR>
     * �@@this.�ύX��K�p����To != null�̏ꍇ�A<BR>
     * �@@�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�jthis.�ύX��K�p����To����t�^�ɕϊ��ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͓��t�G���[(�K�p����To)�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01443<BR>
     * <BR>
     * �@@�R�|�Q�jthis.�ύX��K�p����From  == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���t�����̓G���[(�K�p����From)�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01444<BR>
     * <BR>
     * �@@�R�|�R�jthis.�ύX��K�p����To < �Ɩ����t(*1)�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͓��t�G���[(�K�p����To���������t����)�v��<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01445<BR>
     * <BR>
     * �S�j�K�p���Ԑ������`�F�b�N<BR>
     * �@@this.�ύX��K�p����From != null�@@���@@this.�ύX��K�p����To != null�̏ꍇ�A<BR>
     * �@@�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�S�|�P�jthis.�ύX��K�p����From > this.�ύX��K�p����To�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�K�p����From/To�������G���[�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01446<BR>
     * <BR>
     * �T�j�\��f�[�^�������`�F�b�N<BR>
     * �@@�T�|�P�jthis.�ύX��o�^�l(�\��) == null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�@@(this.�ύX��K�p����From != null�@@�܂��́@@this.�ύX��K�p����To != null)�̏ꍇ�A<BR>
     * �@@�@@�u�ύX��̓o�^�l(�\��)�������́v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�T�|�Q�j�T�|�P�j�ȊO�̏ꍇ�A�ȉ��̃`�F�b�N���s��<BR>
     * �@@�@@(this.�ύX��K�p����From == null�@@���@@this.�ύX��K�p����To == null)�̏ꍇ�A<BR>
     * �@@�@@�u�ύX��̓K�p����From/To�������́v�̗�O���X���[����B<BR>
     * <BR>
     * (*1)�Ɩ����t<BR>
     * �@@TradingSystem.getBizDate()�ɂĎ擾�����Ɩ����t<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 418737C50277
     */
    protected void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);
        TradingSystem l_tradingSystem = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        final String DATE_FORMAT = "yyyyMMdd";

        /*1-1 If either of
         * bizDateRegistData�AnextBizDateRegistData�AscheduleRegistData
         * meets with the following conditions throws exception
         */
        if ((WEB3AdminEquityLargeItemDivDef.DEPOSIT_RATE.equals(largeItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.LOT_SIZE.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.COMPULSIVE_LIMITED_UNIT.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.MARGIN_RATIO.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.ESTIMATION_PRICE.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.SECURITIES_ESTIMATION_RATIO.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.LAST_CLOSING_PRICE.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.BASE_PRICE.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.LOW_COMPULSIVE_PRICE_RANGE.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.HIGH_COMPULSIVE_PRICE_RANGE).equals(smallItemDiv))
        {
            if ((this.aftBizDateRegistData != null
                && (!WEB3StringTypeUtility.isNumber(this.aftBizDateRegistData)
                    || (Double.parseDouble(this.aftBizDateRegistData) < 0)
                    || this.aftBizDateRegistData.length() > 12))
                || (this.aftNextBizDateRegistData != null
                    && (!WEB3StringTypeUtility.isNumber(this.aftNextBizDateRegistData)
                    || Double.parseDouble(this.aftNextBizDateRegistData) < 0
                    || this.aftNextBizDateRegistData.length() > 12))
                || (this.aftScheduleRegistData != null
                    && (!WEB3StringTypeUtility.isNumber(this.aftScheduleRegistData)
                    || Double.parseDouble(this.aftScheduleRegistData) < 0
                    || this.aftScheduleRegistData.length() > 12)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01441,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        // �������x�P�ʂ̏ꍇ
        if (WEB3AdminEquitySmallItemDivDef.COMPULSIVE_LIMITED_UNIT.equals(this.smallItemDiv))
        {
            if (this.aftBizDateRegistData != null && this.aftBizDateRegistData.length() > 7
                || this.aftNextBizDateRegistData != null && this.aftNextBizDateRegistData.length() > 7
                || this.aftScheduleRegistData != null && this.aftScheduleRegistData.length() > 7)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01441,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        // �������̏ꍇ
        if (WEB3AdminEquitySmallItemDivDef.STANDARD_NAME.equals(this.smallItemDiv))
        {
            if (this.aftBizDateRegistData != null && this.aftBizDateRegistData.getBytes().length > 50
                || this.aftScheduleRegistData != null && this.aftScheduleRegistData.getBytes().length > 50)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01990,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        /*  2-1 If applyStartDate is unable to change to
         * Date type throw exception
         */
        if (this.aftApplyStartDate != null)
        {
            if (!WEB3StringTypeUtility.isDateStr(this.aftApplyStartDate, "yyyyMMdd"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01442,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 If applyEndDate is unable to change this.applyStartDate to Date type throw exception
        if (aftApplyEndDate != null)
        {
            if (!WEB3StringTypeUtility.isDateStr(this.aftApplyEndDate, "yyyyMMdd"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01443,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // 3-2 if applyStartDate is  null throw exception
            if (aftApplyStartDate == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01444,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //3-3  If this.applyEndDate < bizDate (*1)
            Date l_dtbizDate = null;
            l_tradingSystem = l_finApp.getTradingSystem();
            l_dtbizDate = l_tradingSystem.getBizDate();
            Date l_applyEndDate = WEB3DateUtility.getDate(aftApplyEndDate, "yyyyMMdd");
            int l_resultcompare = WEB3DateUtility.compare(l_dtbizDate, l_applyEndDate);
            if (l_resultcompare == 1)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01445,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 4-1 if applyStartDate > applyEndDate throw exception
        if ((aftApplyStartDate != null) && (aftApplyEndDate != null))
        {
            Date l_applyStartDate = WEB3DateUtility.getDate(aftApplyStartDate, DATE_FORMAT);
            Date l_applyEndDate = WEB3DateUtility.getDate(aftApplyEndDate, DATE_FORMAT);

            int l_resultcompare = WEB3DateUtility.compare(l_applyStartDate, l_applyEndDate);
            if (l_resultcompare == 1)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01446,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        // �T�j�\��f�[�^�̐������`�F�b�N
        if (this.aftScheduleRegistData == null)
        {
            if (this.aftApplyStartDate != null || this.aftApplyEndDate != null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01978,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        else
        {
            if (this.aftApplyStartDate == null && this.aftApplyEndDate == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01979,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
