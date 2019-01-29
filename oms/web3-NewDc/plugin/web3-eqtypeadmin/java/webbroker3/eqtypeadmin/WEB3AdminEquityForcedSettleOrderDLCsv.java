head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderDLCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ϒ����_�E�����[�hCSV�iWEB3AdminEquityForcedSettleOrderDLCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/21 ���n(���u) �V�K�쐬���f��171 191
Revision History : 2008/11/07 �I�O(���u) �d�l�ύX���f��No.211
*/
package webbroker3.eqtypeadmin;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ApproveStatusType;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ForcedSettleReasonType;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityApproveStatusDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityCloseDateDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityContractTypeDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityErrorReasonCodeDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityForcedSettleReasonDivDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityMarginAccruedDaysDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityRepaymentDivDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityTaxTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�������ϒ����_�E�����[�hCSV)<BR>
 * �������ϒ����_�E�����[�hCSV�N���X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderDLCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderDLCsv.class);

    /**
     * (���X�R�[�h���x��)<BR>
     * ���X�R�[�h���x��<BR>
     */
    public String branchCodeLabel = "���X";

    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �ڋq�R�[�h���x��<BR>
     */
    public String accountCodeLabel = "�ڋq";

    /**
     * (�ڋq�����x��)<BR>
     * �ڋq�����x��<BR>
     */
    public String accountNameLabel = "�ڋq��";

    /**
     * (�������ϗ��R���x��)<BR>
     * �������ϗ��R���x��<BR>
     */
    public String forcedSettleReasonLabel = "�������ϗ��R";

    /**
     * (�s�ꖼ���x��)<BR>
     * �s�ꖼ���x��<BR>
     */
    public String marketNameLabel = "�s��";

    /**
     * (�����R�[�h���x��)<BR>
     * �����R�[�h���x��<BR>
     */
    public String productCodeLabel = "����";

    /**
     * (���������x��)<BR>
     * ���������x��<BR>
     */
    public String productNameLabel = "������";

    /**
     * (�ŋ敪���x��)<BR>
     * �ŋ敪���x��<BR>
     */
    public String taxTypeLabel = "����";

    /**
     * (���敪���x��)<BR>
     * ���敪���x��<BR>
     */
    public String contractTypeLabel = "���敪";

    /**
     * (�ٍϋ敪���x��)<BR>
     * �ٍϋ敪���x��<BR>
     */
    public String repaymentDivLabel = "�ٍ�";

    /**
     * (�������x��)<BR>
     * �������x��<BR>
     */
    public String openDateLabel = "����";

    /**
     * (���ϊ������x��)<BR>
     * ���ϊ������x��<BR>
     */
    public String closeDateLabel = "���ϊ���";

    /**
     * (���������x��)<BR>
     * ���������x��<BR>
     */
    public String contractQuantityLabel = "������";

    /**
     * (���P�����x��)<BR>
     * ���P�����x��<BR>
     */
    public String contractPriceLabel = "���P��";

    /**
     * (��������x��)<BR>
     * ��������x��<BR>
     */
    public String contractExecPriceLabel = "�����";

    /**
     * (�ۏ؋������x��)<BR>
     * �ۏ؋������x��<BR>
     */
    public String marginDepositRateLabel = "�ۏ؋��� (%)";

    /**
     * (�Ǐؔ��������x��)<BR>
     * �Ǐؔ��������x��<BR>
     */
    public String additionalMarginDateLabel = "�Ǐؔ�����";

    /**
     * (�o�ߓ������x��)<BR>
     * �o�ߓ������x��<BR>
     */
    public String marginAccruedDaysLabel = "�o�ߓ���(��)";

    /**
     * (�쐬�������x��)<BR>
     * �쐬�������x��<BR>
     */
    public String createdTimestampLabel = "�쐬����";

    /**
     * (�����������x��)<BR>
     * �����������x��<BR>
     */
    public String processTimeLabel = "��������";

    /**
     * (���F��ԃ��x��)<BR>
     * ���F��ԃ��x��<BR>
     */
    public String approveStatusLabel = "���F���";

    /**
     * (���F�҃��x��)<BR>
     * ���F�҃��x��<BR>
     */
    public String approverLabel = "���F��";

    /**
     * (�������ϒ����_�E�����[�hCSV)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * <BR>
     * �P�jsuper()���R�[�����A�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�jcreate�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B<BR>
     * <BR>
     * �R�jcreate�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B<BR>
     * @@roseuid 477312FD0243
     */
    public WEB3AdminEquityForcedSettleOrderDLCsv()
    {
        super();
        this.createKeyHeader();
        this.createColumnHeader();
    }

    /**
     * (create�L�[�w�b�_)<BR>
     * �L�[�w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ蕶����̔z��𐶐����Aset�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�L�[�w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B<BR>
     * <BR>
     * (*1) ���ݓ���<BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * @@roseuid 478326AF022B
     */
    protected void createKeyHeader()
    {
        final String STR_METHOD_NAME = "createKeyHeader()";
        log.entering(STR_METHOD_NAME);
        //�L�[�w�b�_�z��
        //�|�@@index = 0
        //�@@���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B
        String[] l_strKeyHeaders = new String[1];
        l_strKeyHeaders[0] =
            WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
        this.setKeyHeader(l_strKeyHeaders);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B <BR>
     * <BR>
     * �ȉ��̂Ƃ����CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * �P�j�@@���X�R�[�h<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���X�R�[�h���x��<BR>
     * �@@�@@�J�����ԍ��F 0<BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     * �@@�@@���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ڋq�R�[�h���x��<BR>
     * �@@�@@�J�����ԍ��F 1<BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     * �@@�@@���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �R�j�@@�ڋq��<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ڋq�����x��<BR>
     * �@@�@@�J�����ԍ��F 2<BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     * �@@�@@���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �S�j�@@�������ϗ��R<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�������ϗ��R���x��<BR>
     * �@@�@@�J�����ԍ��F 3<BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     * �@@�@@���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �T�j�@@�s�ꖼ<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�s�ꖼ���x��<BR>
     * �@@�@@�J�����ԍ��F 4<BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     * �@@�@@���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �U�j�@@�����R�[�h<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�����R�[�h���x��<BR>
     * �@@�@@�J�����ԍ��F 5<BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     * �@@�@@���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �V�j�@@������<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���������x��<BR>
     * �@@�@@�J�����ԍ��F 6<BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     * �@@�@@���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �W�j�@@�ŋ敪<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ŋ敪���x��<BR>
     * �@@�@@�J�����ԍ��F 7<BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     * �@@�@@���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �X�j�@@���敪<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���敪���x�� <BR>
     * �@@�@@�J�����ԍ��F 8 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �P�O�j�@@�ٍϋ敪<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ٍϋ敪���x�� <BR>
     * �@@�@@�J�����ԍ��F 9 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null <BR>
     * <BR>
     * �P�P�j�@@����<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�������x�� <BR>
     * �@@�@@�J�����ԍ��F 10 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_���t���� <BR>
     * �@@�@@���t�t�H�[�}�b�g�F new SimpleDateFormat("yyyy/M/d")<BR>
     * <BR>
     * �P�Q�j�@@���ϊ���<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���ϊ������x�� <BR>
     * �@@�@@�J�����ԍ��F 11 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     * �@@�@@���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �P�R�j�@@������<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���������x�� <BR>
     * �@@�@@�J�����ԍ��F 12 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null <BR>
     * <BR>
     * �P�S�j�@@���P��<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���P�����x�� <BR>
     * �@@�@@�J�����ԍ��F 13 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null <BR>
     * <BR>
     * �P�T�j�@@�����<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.��������x�� <BR>
     * �@@�@@�J�����ԍ��F 14 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null <BR>
     * <BR>
     * �P�U�j�@@�ۏ؋���<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ۏ؋������x�� <BR>
     * �@@�@@�J�����ԍ��F 15 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null <BR>
     * <BR>
     * �P�V�j�@@�Ǐؔ�����<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�Ǐؔ��������x�� <BR>
     * �@@�@@�J�����ԍ��F 16 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_���t���� <BR>
     * �@@�@@���t�t�H�[�}�b�g�F new SimpleDateFormat("yyyy/M/d")<BR>
     * <BR>
     * �P�W�j�@@�o�ߓ���<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�o�ߓ������x�� <BR>
     * �@@�@@�J�����ԍ��F 17 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null <BR>
     * <BR>
     * �P�X�j�@@�쐬����<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�쐬�������x�� <BR>
     * �@@�@@�J�����ԍ��F 18 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_���t���� <BR>
     * �@@�@@���t�t�H�[�}�b�g�F new SimpleDateFormat("yyyy/M/d H:mm")<BR>
     * <BR>
     * �Q�O�j�@@��������<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�����������x��<BR>
     * �@@�@@�J�����ԍ��F 19 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_���t���� <BR>
     * �@@�@@���t�t�H�[�}�b�g�F new SimpleDateFormat("yyyy/M/d H:mm")<BR>
     * <BR>
     * �Q�P�j�@@���F���<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���F��ԃ��x�� <BR>
     * �@@�@@�J�����ԍ��F 20 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null <BR>
     * <BR>
     * �Q�Q�j�@@���F��<BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���F�҃��x�� <BR>
     * �@@�@@�J�����ԍ��F 21 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null <BR>
     * @@roseuid 478329C6015C
     */
    protected void createColumnHeader()
    {
        final String STR_METHOD_NAME = "createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 22;
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //�P�j�@@���X�R�[�h
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���X�R�[�h���x��
        //�@@�@@�J�����ԍ��F 0
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[0] = new WEB3GentradeCsvColumnModel(
            this.branchCodeLabel,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�Q�j�@@�ڋq�R�[�h
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ڋq�R�[�h���x��
        //�@@�@@�J�����ԍ��F 1
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[1] = new WEB3GentradeCsvColumnModel(
            this.accountCodeLabel,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�R�j�@@�ڋq��
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ڋq�����x��
        //�@@�@@�J�����ԍ��F 2
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[2] = new WEB3GentradeCsvColumnModel(
            this.accountNameLabel,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�S�j�@@�������ϗ��R
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�������ϗ��R���x��
        //�@@�@@�J�����ԍ��F 3
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[3] = new WEB3GentradeCsvColumnModel(
            this.forcedSettleReasonLabel,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�T�j�@@�s�ꖼ
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�s�ꖼ���x��
        //�@@�@@�J�����ԍ��F 4
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[4] = new WEB3GentradeCsvColumnModel(
            this.marketNameLabel,
            4,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�U�j�@@�����R�[�h
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�����R�[�h���x��
        //�@@�@@�J�����ԍ��F 5
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[5] = new WEB3GentradeCsvColumnModel(
            this.productCodeLabel,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�V�j�@@������
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���������x��
        //�@@�@@�J�����ԍ��F 6
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[6] = new WEB3GentradeCsvColumnModel(
            this.productNameLabel,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�W�j�@@�ŋ敪
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ŋ敪���x��
        //�@@�@@�J�����ԍ��F 7
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[7] = new WEB3GentradeCsvColumnModel(
            this.taxTypeLabel,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�X�j�@@���敪
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���敪���x��
        //�@@�@@�J�����ԍ��F 8
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[8] = new WEB3GentradeCsvColumnModel(
            this.contractTypeLabel,
            8,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�P�O�j�@@�ٍϋ敪
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ٍϋ敪���x��
        //�@@�@@�J�����ԍ��F 9
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[9] = new WEB3GentradeCsvColumnModel(
            this.repaymentDivLabel,
            9,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�P�P�j�@@����
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�������x��
        //�@@�@@�J�����ԍ��F 10
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_���t����
        //�@@�@@���t�t�H�[�}�b�g�F new SimpleDateFormat("yyyy/M/d")
        l_models[10] = new WEB3GentradeCsvColumnModel(
            this.openDateLabel,
            10,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD_SHORT));

        //�P�Q�j�@@���ϊ���
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���ϊ������x��
        //�@@�@@�J�����ԍ��F 11
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[11] = new WEB3GentradeCsvColumnModel(
            this.closeDateLabel,
            11,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�P�R�j�@@������
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���������x��
        //�@@�@@�J�����ԍ��F 12
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[12] = new WEB3GentradeCsvColumnModel(
            this.contractQuantityLabel,
            12,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�P�S�j�@@���P��
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���P�����x��
        //�@@�@@�J�����ԍ��F 13
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[13] = new WEB3GentradeCsvColumnModel(
            this.contractPriceLabel,
            13,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�P�T�j�@@�����
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.��������x��
        //�@@�@@�J�����ԍ��F 14
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[14] = new WEB3GentradeCsvColumnModel(
            this.contractExecPriceLabel,
            14,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�P�U�j�@@�ۏ؋���
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ۏ؋������x��
        //�@@�@@�J�����ԍ��F 15
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[15] = new WEB3GentradeCsvColumnModel(
            this.marginDepositRateLabel,
            15,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�P�V�j�@@�Ǐؔ�����
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�Ǐؔ��������x��
        //�@@�@@�J�����ԍ��F 16
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_���t����
        //�@@�@@���t�t�H�[�}�b�g�F new SimpleDateFormat("yyyy/M/d")
        l_models[16] = new WEB3GentradeCsvColumnModel(
            this.additionalMarginDateLabel,
            16,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD_SHORT));

        //�P�W�j�@@�o�ߓ���
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�o�ߓ������x��
        //�@@�@@�J�����ԍ��F 17
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[17] = new WEB3GentradeCsvColumnModel(
            this.marginAccruedDaysLabel,
            17,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�P�X�j�@@�쐬����
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�쐬�������x��
        //�@@�@@�J�����ԍ��F 18
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_���t����
        //�@@�@@���t�t�H�[�}�b�g�F new SimpleDateFormat("yyyy/M/d H:mm")
        l_models[18] = new WEB3GentradeCsvColumnModel(
            this.createdTimestampLabel,
            18,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_YMDHM_SHORT));

        //�Q�O�j�@@��������
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�����������x��
        //�@@�@@�J�����ԍ��F 19
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_���t����
        //�@@�@@���t�t�H�[�}�b�g�F new SimpleDateFormat("yyyy/M/d H:mm")
        l_models[19] = new WEB3GentradeCsvColumnModel(
            this.processTimeLabel,
            19,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_YMDHM_SHORT));

        //�Q�P�j�@@���F���
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���F��ԃ��x��
        //�@@�@@�J�����ԍ��F 20
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[20] = new WEB3GentradeCsvColumnModel(
            this.approveStatusLabel,
            20,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�Q�Q�j�@@���F��
        //�@@[CSV�J�������f���̃R���X�g���N�^�̈���]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���F�҃��x��
        //�@@�@@�J�����ԍ��F 21
        //�@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������
        //�@@�@@���t�t�H�[�}�b�g�F null
        l_models[21] = new WEB3GentradeCsvColumnModel(
            this.approverLabel,
            21,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        this.setColumnHeader(l_models);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���X�R�[�h)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���X�R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.���XID�ɊY�����镔�X.���X�R�[�h<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_lngBranchId - (���XID)<BR>
     * ���XID<BR>
     * @@roseuid 4784326502C8
     */
    public void setBranchCode(int l_intLineNo, long l_lngBranchId)
    {
        final String STR_METHOD_NAME = "setBranchCode(int, long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[����] ���ڃ��x���F �������ϒ����_�E�����[�hCSV.���X�R�[�h���x��
        WEB3GentradeCsvColumnModel l_csvColumnModel =
            this.getColumnModel(this.branchCodeLabel);

        String l_strBranchCode = null;
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Branch l_branch = l_accountMgr.getBranch(l_lngBranchId);
            l_strBranchCode = l_branch.getBranchCode();
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[����] �s�ԍ��F �����̍s�ԍ�
        //   �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��
        //   �@@�@@�l�F ����.���XID�ɊY�����镔�X.���X�R�[�h
        this.setValue(l_intLineNo, l_csvColumnModel, l_strBranchCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq)<BR>
     * �P�j�i�ڋq�R�[�h�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ڋq�R�[�h���x�� <BR>
     * <BR>
     * �Q�j�i�ڋq�R�[�h�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �Q�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.�����R�[�h <BR>
     * <BR>
     * �R�j�i�ڋq���j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ڋq�����x�� <BR>
     * <BR>
     * �S�j�i�ڋq���j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �S�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.�����h�c�ɊY������ڋq.get�ڋq�\����()�@@(*1)<BR>
     * <BR>
     * �@@(*1)�@@�擾�ł��Ȃ��ꍇ�́Anull���Z�b�g����B<BR>
     * <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@roseuid 4784373B031E
     */
    public void setAccount(int l_intLineNo, String l_strAccountCode, long l_lngAccountId)
    {
        final String STR_METHOD_NAME = "setBranchCode(int, long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�i�ڋq�R�[�h�j�J�������f���擾
        //�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //�@@[����]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ڋq�R�[�h���x��
        WEB3GentradeCsvColumnModel l_accountCodeColumnModel =
            this.getColumnModel(this.accountCodeLabel);

        //�Q�j�i�ڋq�R�[�h�j�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //�@@[����]
        //�@@�@@�s�ԍ��F �����̍s�ԍ�
        //�@@�@@�J�����F �Q�j�Ŏ擾�����J�������f��
        //�@@�@@�l�F ����.�����R�[�h
        this.setValue(l_intLineNo, l_accountCodeColumnModel, l_strAccountCode);

        //�R�j�i�ڋq���j�J�������f���擾
        //�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //�@@[����]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ڋq�����x��
        WEB3GentradeCsvColumnModel l_accountNameColumnModel =
            this.getColumnModel(this.accountNameLabel);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        String l_strDisplayAccountName = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_lngAccountId);
            l_strDisplayAccountName = l_mainAccount.getDisplayAccountName();
        }
        catch (NotFoundException l_ex)
        {
            l_strDisplayAccountName = null;
        }
        //�S�j�i�ڋq���j�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //�@@[����]
        //�@@�@@�s�ԍ��F �����̍s�ԍ�
        //�@@�@@�J�����F �S�j�Ŏ擾�����J�������f��
        //�@@�@@�l�F ����.�����h�c�ɊY������ڋq.get�ڋq�\����()�@@(*1)
        //(*1)�@@�擾�ł��Ȃ��ꍇ�́Anull���Z�b�g����B
        this.setValue(l_intLineNo, l_accountNameColumnModel, l_strDisplayAccountName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�������ϗ��R)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�������ϗ��R���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F <BR>
     * �@@�@@�@@[����.�������ϗ��R�敪��"��������"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"���ϊ�������"<BR>
     * <BR>
     * �@@�@@�@@[����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�y�x�j"�A<BR>
     * �@@�@@�@@�@@�܂��́A����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�d�x�j"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"30%����7���ȏ�"<BR>
     * <BR>
     * �@@�@@�@@[����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j"�A<BR>
     * �@@�@@�@@�@@�܂��́A����.�������ϗ��R�敪��"�ۏ؋��ێ�������i��ԁj"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"20%�����������"<BR>
     * <BR>
     * �@@�@@�@@[����.�������ϗ��R�敪��"�蓮��������"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"�蓮��������"<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strForcedSettleReasonType - (�������ϗ��R�敪)<BR>
     * �������ϗ��R�敪<BR>
     * @@roseuid 47844876024C
     */
    public void setForcedSettleReason(int l_intLineNo, String l_strForcedSettleReasonType)
    {
        final String STR_METHOD_NAME = "setForcedSettleReason(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //�@@[����]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�������ϗ��R���x��
        WEB3GentradeCsvColumnModel l_csvColumnModel =
            this.getColumnModel(this.forcedSettleReasonLabel);

        //�Q�j�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //�@@[����]
        //�@@�@@�s�ԍ��F �����̍s�ԍ�
        //�@@�@@�J�����F �P�j�Ŏ擾�����J�������f��
        //�@@�@@�l�F
        //�@@�@@�@@[����.�������ϗ��R�敪��"��������"�̏ꍇ]
        //�@@�@@�@@�@@"���ϊ�������"
        String l_strForcedSettleReason = null;
        if (WEB3ForcedSettleReasonType.FIXED_DATE_COMING.equals(l_strForcedSettleReasonType))
        {
            l_strForcedSettleReason = WEB3AdminEquityForcedSettleReasonDivDef.FIXED_DATE_COMING;
        }
        //�@@�@@�@@[����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�y�x�j"�A
        //�@@�@@�@@�@@�܂��́A����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�d�x�j"�̏ꍇ]
        //�@@�@@�@@�@@"30%����7���ȏ�"
        else if (WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS.equals(
                l_strForcedSettleReasonType)
            || WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS.equals(
                l_strForcedSettleReasonType))
        {
            l_strForcedSettleReason = WEB3AdminEquityForcedSettleReasonDivDef.ADDDEPOSIT_FIRST_DATE_EXCESS;
        }
        //�@@�@@�@@[����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j"�A
        //�@@�@@�@@�@@�܂��́A����.�������ϗ��R�敪��"�ۏ؋��ێ�������i��ԁj"�̏ꍇ]
        //�@@�@@�@@�@@"20%�����������"
        else if (WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL.equals(
                l_strForcedSettleReasonType)
            || WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET.equals(
                l_strForcedSettleReasonType))
        {
            l_strForcedSettleReason = WEB3AdminEquityForcedSettleReasonDivDef.ADDDEPOSIT_SECOND_DATE_EXCESS;
        }
        //�@@�@@�@@[����.�������ϗ��R�敪��"�蓮��������"�̏ꍇ]
        //�@@�@@�@@�@@"�蓮��������"
        else if (WEB3ForcedSettleReasonType.MANUAL_FORCED_SETTLE.equals(
            l_strForcedSettleReasonType))
        {
            l_strForcedSettleReason = WEB3AdminEquityForcedSettleReasonDivDef.MANUAL_FORCED_SETTLE;
        }

        this.setValue(l_intLineNo, l_csvColumnModel, l_strForcedSettleReason);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�s�ꖼ)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�s�ꖼ���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.�s��ID�ɊY������s��.�s�ꖼ<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_lngMarketId - (�s��ID)<BR>
     * �s��ID<BR>
     * @@roseuid 4784496B0053
     */
    public void setMarketName(int l_intLineNo, long l_lngMarketId)
    {
        final String STR_METHOD_NAME = "setMarketName(int, long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //�@@[����]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�s�ꖼ���x��
        WEB3GentradeCsvColumnModel l_csvColumnModel =
            this.getColumnModel(this.marketNameLabel);

        String l_strMarketName = null;
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();
            Market l_market = l_finObjectManager.getMarket(l_lngMarketId);
            l_strMarketName = l_market.getMarketName();
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�Q�j�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //�@@[����]
        //�@@�@@�s�ԍ��F �����̍s�ԍ�
        //�@@�@@�J�����F �P�j�Ŏ擾�����J�������f��
        //�@@�@@�l�F ����.�s��ID�ɊY������s��.�s�ꖼ
        this.setValue(l_intLineNo, l_csvColumnModel, l_strMarketName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set����)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�����R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.�����R�[�h�̍�4byte<BR>
     * <BR>
     * �R�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���������x��<BR>
     * <BR>
     * �S�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �R�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.����ID�ɊY�����銔������.�������@@(*1)<BR>
     * <BR>
     * �@@(*1)�@@�擾�ł��Ȃ��ꍇ�́Anull���Z�b�g����B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@roseuid 478449DF00ED
     */
    public void setProduct(int l_intLineNumber, String l_strProductCode, long l_lngProductId)
    {
        final String STR_METHOD_NAME = "setProduct(int, String, long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //�@@[����]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�����R�[�h���x��
        WEB3GentradeCsvColumnModel l_productCodeCsvColumnModel =
            this.getColumnModel(this.productCodeLabel);

        //�Q�j�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //�@@[����]
        //�@@�@@�s�ԍ��F �����̍s�ԍ�
        //�@@�@@�J�����F �P�j�Ŏ擾�����J�������f��
        //�@@�@@�l�F ����.�����R�[�h�̍�4byte
        this.setValue(l_intLineNumber, l_productCodeCsvColumnModel, l_strProductCode.substring(0, 4));

        //�R�j�J�������f���擾
        //�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //�@@[����]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���������x��
        WEB3GentradeCsvColumnModel l_productNameCsvColumnModel =
            this.getColumnModel(this.productNameLabel);

        String l_strProductName = null;
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            ProductManager l_productManager = l_tradingModule.getProductManager();
            EqTypeProduct l_eqTypeProduct = (EqTypeProduct)l_productManager.getProduct(l_lngProductId);
            EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_eqTypeProduct.getDataSourceObject();
            l_strProductName = l_eqtypeProductRow.getStandardName();
        }
        catch (NotFoundException l_ex)
        {
            l_strProductName = null;
        }
        //�S�j�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //�@@[����]
        //�@@�@@�s�ԍ��F �����̍s�ԍ�
        //�@@�@@�J�����F �R�j�Ŏ擾�����J�������f��
        //�@@�@@�l�F ����.����ID�ɊY�����銔������.�������@@(*1)
        //�@@(*1)�@@�擾�ł��Ȃ��ꍇ�́Anull���Z�b�g����B
        this.setValue(l_intLineNumber, l_productNameCsvColumnModel, l_strProductName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ŋ敪)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ŋ敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F <BR>
     * �@@�@@�@@[����.�ŋ敪��TaxTypeEnum."���"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"���"<BR>
     * �@@�@@�@@[����.�ŋ敪��TaxTypeEnum."����"�܂��́A <BR>
     * �@@�@@�@@�@@TaxTypeEnum."������������򒥎�"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"����"<BR>
     * �@@�@@�@@[�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�@@null���Z�b�g����B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * �ŋ敪<BR>
     * @@roseuid 47844A290040
     */
    public void setTaxType(int l_intLineNo, TaxTypeEnum l_taxType)
    {
        final String STR_METHOD_NAME = "setTaxType(int, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //�@@[����]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ŋ敪���x��
        WEB3GentradeCsvColumnModel l_csvColumnModel =
            this.getColumnModel(this.taxTypeLabel);

        //�Q�j�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //�@@[����]
        //�@@�@@�s�ԍ��F �����̍s�ԍ�
        //�@@�@@�J�����F �P�j�Ŏ擾�����J�������f��
        //�@@�@@�l�F
        //�@@�@@�@@[����.�ŋ敪��TaxTypeEnum."���"�̏ꍇ]
        //�@@�@@�@@�@@"���"
        //�@@�@@�@@[����.�ŋ敪��TaxTypeEnum."����"�܂��́A
        //�@@�@@�@@�@@TaxTypeEnum."������������򒥎�"�̏ꍇ]
        //�@@�@@�@@�@@"����"
        //�@@�@@�@@[�ȊO�̏ꍇ]
        //�@@�@@�@@�@@null���Z�b�g����B
        String l_strTaxType = null;
        if (TaxTypeEnum.NORMAL.equals(l_taxType))
        {
            l_strTaxType = WEB3AdminEquityTaxTypeDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_taxType) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
        {
            l_strTaxType = WEB3AdminEquityTaxTypeDef.SPECIAL;
        }

        this.setValue(l_intLineNo, l_csvColumnModel, l_strTaxType);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���敪)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F <BR>
     * �@@�@@�@@[����.���敪��ContractTypeEnum."����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"�V�K��"<BR>
     * �@@�@@�@@[����.���敪��ContractTypeEnum."����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"�V�K��"<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_contractType - (���敪)<BR>
     * ���敪<BR>
     * @@roseuid 47844A7800FA
     */
    public void setContractType(int l_intLineNo, ContractTypeEnum l_contractType)
    {
        final String STR_METHOD_NAME = "setContractType(int, ContractTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //�@@[����]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���敪���x��
        WEB3GentradeCsvColumnModel l_csvColumnModel =
            this.getColumnModel(this.contractTypeLabel);
        //
        //�Q�j�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //�@@[����]
        //�@@�@@�s�ԍ��F �����̍s�ԍ�
        //�@@�@@�J�����F �P�j�Ŏ擾�����J�������f��
        //�@@�@@�l�F
        //�@@�@@�@@[����.���敪��ContractTypeEnum."����"�̏ꍇ]
        //�@@�@@�@@�@@"�V�K��"
        //�@@�@@�@@[����.���敪��ContractTypeEnum."����"�̏ꍇ]
        //�@@�@@�@@�@@"�V�K��"
        String l_strContractType = null;
        if (ContractTypeEnum.LONG.equals(l_contractType))
        {
            l_strContractType = WEB3AdminEquityContractTypeDef.LONG;
        }
        else if (ContractTypeEnum.SHORT.equals(l_contractType))
        {
            l_strContractType = WEB3AdminEquityContractTypeDef.SHORT;
        }

        this.setValue(l_intLineNo, l_csvColumnModel, l_strContractType);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ٍϋ敪)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ٍϋ敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F <BR>
     * �@@�@@�@@[����.�ٍϋ敪��"1�F���x�M�p"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"���x�M�p"<BR>
     * �@@�@@�@@[����.�ٍϋ敪��"2�F��ʐM�p"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"��ʐM�p"<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strRepaymentDiv - (�ٍϋ敪)<BR>
     * �ٍϋ敪<BR>
     * @@roseuid 47844A9900AB
     */
    public void setRepaymentDiv(int l_intLineNo, String l_strRepaymentDiv)
    {
        final String STR_METHOD_NAME = "setRepaymentDiv(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //�@@[����]
        //�@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ٍϋ敪���x��
        WEB3GentradeCsvColumnModel l_csvColumnModel =
            this.getColumnModel(this.repaymentDivLabel);

        //�Q�j�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //�@@[����]
        //�@@�@@�s�ԍ��F �����̍s�ԍ�
        //�@@�@@�J�����F �P�j�Ŏ擾�����J�������f��
        //�@@�@@�l�F
        //�@@�@@�@@[����.�ٍϋ敪��"1�F���x�M�p"�̏ꍇ]
        //�@@�@@�@@�@@"���x�M�p"
        //�@@�@@�@@[����.�ٍϋ敪��"2�F��ʐM�p"�̏ꍇ]
        //�@@�@@�@@�@@"��ʐM�p"
        String l_strRepayment = null;
        if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentDiv))
        {
            l_strRepayment = WEB3AdminEquityRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS;
        }
        else
        {
            l_strRepayment = WEB3AdminEquityRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN;
        }

        this.setValue(l_intLineNo, l_csvColumnModel, l_strRepayment);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set����)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.����<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_datOpenDate - (����)<BR>
     * ����<BR>
     * @@roseuid 47844AC70195
     */
    public void setOpenDate(int l_intLineNo, Date l_datOpenDate)
    {
        final String STR_METHOD_NAME = "setOpenDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.openDateLabel);

        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_datOpenDate);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���ϊ���)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���ϊ������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F <BR>
     * �@@�@@�@@[����.���ϊ�����"9999/12/31"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"����"<BR>
     * �@@�@@�@@[�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�@@"yyyy/M/d"�Ƀt�H�[�}�b�g�������́B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_datCloseDate - (���ϊ���)<BR>
     * ���ϊ���<BR>
     * @@roseuid 47844B0B0174
     */
    public void setCloseDate(int l_intLineNo, Date l_datCloseDate)
    {
        final String STR_METHOD_NAME = "setCloseDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.closeDateLabel);

        String l_strCloseDate = null;
        if (WEB3GentradeTimeDef.MAX_YMD.equals(
            WEB3DateUtility.formatDate(l_datCloseDate, WEB3GentradeTimeDef.DATE_SPLIT_YMD)))
        {
            l_strCloseDate = WEB3AdminEquityCloseDateDef.INDEFINITE;
        }
        else
        {
            l_strCloseDate =
                WEB3DateUtility.formatDate(l_datCloseDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD_SHORT);
        }
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strCloseDate);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set������)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.������<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strContractQuantity - (������)<BR>
     * ������<BR>
     * @@roseuid 47844B2E0089
     */
    public void setContractQuantity(int l_intLineNo, String l_strContractQuantity)
    {
        final String STR_METHOD_NAME = "setContractQuantity(int, String)";
        log.entering(STR_METHOD_NAME);

        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.contractQuantityLabel);

        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strContractQuantity);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���P��)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���P�����x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.���P��<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strContractPrice - (���P��)<BR>
     * ���P��<BR>
     * @@roseuid 47844B680386
     */
    public void setContractPrice(int l_intLineNo, String l_strContractPrice)
    {
        final String STR_METHOD_NAME = "setContractPrice(int, String)";
        log.entering(STR_METHOD_NAME);

        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.contractPriceLabel);

        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strContractPrice);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�����)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.��������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.�����<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strContractExecPrice - (�����)<BR>
     * �����<BR>
     * @@roseuid 47849CF70336
     */
    public void setContractExecPrice(int l_intLineNo, String l_strContractExecPrice)
    {
        final String STR_METHOD_NAME = "setContractExecPrice(int, String)";
        log.entering(STR_METHOD_NAME);

        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.contractExecPriceLabel);

        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strContractExecPrice);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ۏ؋���)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�ۏ؋������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.�ۏ؋���<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strMarginDepositRate - (�ۏ؋���)<BR>
     * �ۏ؋���<BR>
     * @@roseuid 47844B6A0116
     */
    public void setMarginDepositRate(int l_intLineNo, String l_strMarginDepositRate)
    {
        final String STR_METHOD_NAME = "setMarginDepositRate(int, String)";
        log.entering(STR_METHOD_NAME);

        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.marginDepositRateLabel);

        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strMarginDepositRate);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�Ǐؔ�����)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�Ǐؔ��������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.�Ǐؔ�����<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_datAdditionalMarginDate - (�Ǐؔ�����)<BR>
     * �Ǐؔ�����<BR>
     * @@roseuid 47844BFC019F
     */
    public void setAdditionalMarginDate(int l_intLineNo, Date l_datAdditionalMarginDate)
    {
        final String STR_METHOD_NAME = "setAdditionalMarginDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.additionalMarginDateLabel);

        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_datAdditionalMarginDate);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�o�ߓ���)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�o�ߓ������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F <BR>
     * �@@�@@�@@[����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j"�A<BR>
     * �@@�@@�@@�@@�܂��́A����.�������ϗ��R�敪��"�ۏ؋��ێ�������i��ԁj"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@"�Ǐؖ���"<BR>
     * �@@�@@�@@[�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@����.�o�ߓ���<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strMarginAccruedDays - (�o�ߓ���)<BR>
     * �o�ߓ���<BR>
     * @@param l_strForcedSettleReasonType - (�������ϗ��R�敪)<BR>
     * �������ϗ��R�敪<BR>
     * @@roseuid 47844C4E0239
     */
    public void setMarginAccruedDays(int l_intLineNo,
        String l_strMarginAccruedDays, String l_strForcedSettleReasonType)
    {
        final String STR_METHOD_NAME = "setMarginAccruedDays(int, String, String)";
        log.entering(STR_METHOD_NAME);

        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.marginAccruedDaysLabel);

        //����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j"
        //�܂��́A����.�������ϗ��R�敪��"�ۏ؋��ێ�������i��ԁj"�̏ꍇ
        if (WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL.equals(
                l_strForcedSettleReasonType)
            || WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET.equals(
                l_strForcedSettleReasonType))
        {
            //"�Ǐؖ���"
            this.setValue(
                l_intLineNo,
                l_gentradeCsvColumnModel,
                WEB3AdminEquityMarginAccruedDaysDef.ADDITIONAL_DEPOSIT_NOT);
        }
        else
        {
            //����.�o�ߓ���
            this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strMarginAccruedDays);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�쐬����)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�쐬�������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.�쐬����<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_datCreatedTimestamp - (�쐬����)<BR>
     * �쐬����<BR>
     * @@roseuid 47844C7D01DB
     */
    public void setCreatedTimestamp(int l_intLineNo, Date l_datCreatedTimestamp)
    {
        final String STR_METHOD_NAME = "setCreatedTimestamp(int, Date)";
        log.entering(STR_METHOD_NAME);

        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.createdTimestampLabel);

        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_datCreatedTimestamp);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��������)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.�����������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.��������<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_datProcessTime - (��������)<BR>
     * ��������<BR>
     * @@roseuid 47844CB20360
     */
    public void setProcessTime(int l_intLineNo, Date l_datProcessTime)
    {
        final String STR_METHOD_NAME = "setProcessTime(int, Date)";
        log.entering(STR_METHOD_NAME);

        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.processTimeLabel);

        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_datProcessTime);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���F���)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���F��ԃ��x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F <BR>
     * �@@�@@�@@[����.�������ϗ��R�敪��"�蓮��������"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"�蓮���F��"<BR> 
     * �@@�@@�@@[����.���F��ԋ敪��"0�F�����F"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"�����F"<BR>
     * �@@�@@�@@[����.���F��ԋ敪��"1�F���F��"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"���F��"<BR>
     * �@@�@@�@@[����.���F��ԋ敪��"2�F�񏳔F"�̏ꍇ]<BR>
     * �@@�@@�@@�@@"�۔F��"<BR>
     * �@@�@@�@@[����.���F��ԋ敪��"9�F�G���["�̏ꍇ]<BR>
     * �@@�@@�@@�@@[����.�����G���[���R�R�[�h��"�����c���s���G���["�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@"�����c���s���G���["<BR>
     * �@@�@@�@@�@@[����.�����G���[���R�R�[�h��"������~�����G���["�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@"������~�����G���["<BR>
     * �@@�@@�@@�@@[����.�����G���[���R�R�[�h��"���ϊ��������σG���["�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@"���ϊ��������σG���["<BR><BR>
     * �@@�@@�@@�@@[����.�����G���[���R�R�[�h��"�����E���n�����o�^�σG���["�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@"�����E���n�����o�^�σG���["<BR>
     * �@@�@@�@@�@@[����.�����G���[���R�R�[�h��"���̑��G���["�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@"���̑��G���["<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strApproveStatusType - (���F��ԋ敪)<BR>
     * ���F��ԋ敪<BR>
     * @@param l_strOrderErrorReasonCode - (�����G���[���R�R�[�h)<BR>
     * �����G���[���R�R�[�h<BR>
     * @@param l_strForcedSettleReasonType - (�������ϗ��R�敪)<BR>
     * �������ϗ��R�敪<BR>
     * @@roseuid 47844CCA0247
     */
    public void setApproveStatus(
        int l_intLineNo,
        String l_strApproveStatusType,
        String l_strOrderErrorReasonCode,
        String l_strForcedSettleReasonType)
    {
        final String STR_METHOD_NAME = "setApproveStatus(int, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.approveStatusLabel);
        //�l
        //[����.�������ϗ��R�敪��"�蓮��������"�̏ꍇ]
        String l_strApproveStatus = null;
        if (WEB3ForcedSettleReasonType.MANUAL_FORCED_SETTLE.equals(l_strForcedSettleReasonType))
        {
            l_strApproveStatus = WEB3AdminEquityApproveStatusDef.MANUAL_APPROVED;
        }
        //����.���F��ԋ敪��"0�F�����F"�̏ꍇ
        else if (WEB3ApproveStatusType.UNAPPROVED.equals(l_strApproveStatusType))
        {
            l_strApproveStatus = WEB3AdminEquityApproveStatusDef.UNAPPROVED;
        }
        //����.���F��ԋ敪��"1�F���F��"�̏ꍇ
        else if (WEB3ApproveStatusType.APPROVED.equals(l_strApproveStatusType))
        {
            l_strApproveStatus = WEB3AdminEquityApproveStatusDef.APPROVED;
        }
        //����.���F��ԋ敪��"2�F�񏳔F"�̏ꍇ
        else if (WEB3ApproveStatusType.NON_APPROVED.equals(l_strApproveStatusType))
        {
            l_strApproveStatus = WEB3AdminEquityApproveStatusDef.NON_APPROVED;
        }
        //����.���F��ԋ敪��"9�F�G���["�̏ꍇ
        else if (WEB3ApproveStatusType.ERROR.equals(l_strApproveStatusType))
        {
            //����.�����G���[���R�R�[�h��"�����c���s���G���["�̏ꍇ
            if (WEB3ErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR.equals(l_strOrderErrorReasonCode))
            {
                l_strApproveStatus = WEB3AdminEquityErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR;
            }
            //����.�����G���[���R�R�[�h��"������~�����G���["�̏ꍇ
            else if (WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR.equals(l_strOrderErrorReasonCode))
            {
                l_strApproveStatus = WEB3AdminEquityErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR;
            }
            //����.�����G���[���R�R�[�h��"���ϊ��������σG���["�̏ꍇ
            else if (WEB3ErrorReasonCodeDef.SETTLEDAY_CAME_ERROR.equals(l_strOrderErrorReasonCode))
            {
                l_strApproveStatus = WEB3AdminEquityErrorReasonCodeDef.SETTLEDAY_CAME_ERROR;
            }
            //����.�����G���[���R�R�[�h��"�����E���n�����o�^�σG���["�̏ꍇ
            else if (WEB3ErrorReasonCodeDef.SWAP_MARGIN_REGISTED_ERROR.equals(l_strOrderErrorReasonCode))
            {
                l_strApproveStatus = WEB3AdminEquityErrorReasonCodeDef.SWAP_MARGIN_REGISTED_ERROR;
            }
            //����.�����G���[���R�R�[�h��"���̑��G���["�̏ꍇ
            else if (WEB3ErrorReasonCodeDef.OTHRE_ERROR.equals(l_strOrderErrorReasonCode))
            {
                l_strApproveStatus = WEB3AdminEquityErrorReasonCodeDef.OTHRE_ERROR;
            }
        }

        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strApproveStatus);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���F��)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �������ϒ����_�E�����[�hCSV.���F�҃��x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.���F�҃R�[�h<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strApproverCode - (���F�҃R�[�h)<BR>
     * ���F�҃R�[�h<BR>
     * @@roseuid 47844CF503BD
     */
    public void setApprover(int l_intLineNo, String l_strApproverCode)
    {
        final String STR_METHOD_NAME = "setApprover(int, String)";
        log.entering(STR_METHOD_NAME);

        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.approverLabel);

        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strApproverCode);
        log.exiting(STR_METHOD_NAME);
    }
}
@
