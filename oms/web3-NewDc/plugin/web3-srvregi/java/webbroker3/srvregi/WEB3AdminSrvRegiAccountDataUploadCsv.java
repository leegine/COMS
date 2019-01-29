head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq�f�[�^�A�b�v���[�hCSV(WEB3AdminSrvRegiAccountDataUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �A�C��(���u) �V�K�쐬
Revesion History : 2005/04/05 ���c ��(SRA) �f�o�b�O���O�o�͏C��
Revesion History : 2005/04/06 ���c ��(SRA) validate���͕K�{����()�ɃA�b�v���[�h�敪��null�`�F�b�N��ǉ�
Revesion History : 2005/04/06 ���c ��(SRA) validate���͕K�{����()�ɐ\�����I�敪�̃`�F�b�N��ǉ�
Revesion History : 2005/04/07 ���c ��(SRA) validate���͕K�{����()�ɓo�^�敪�̃`�F�b�N��ǉ�
Revesion History : 2005/04/07 ���c ��(SRA) validate���͕K�{����()�ɐ\���o�^ID�̃`�F�b�N��ǉ�
Revesion History : 2005/04/07 ���c ��(SRA) validate���͕K�{����()�̗��p�����`�F�b�N�̃o�O�C��
Revesion History : 2007/06/05 ����(���u) �d�l�ύX���f��No.238
Revesion History : 2007/06/08 ����(���u) �d�l�ύX���f��No.258 No.260
Revesion History : 2007/06/21 �����Q(���u) �d�l�ύX���f��No.266 No.268
Revesion History : 2007/07/11 �Ј���(���u) �d�l�ύX���f��No.279
Revesion History : 2008/06/02 ���g (���u) �d�l�ύX ���f��No.382,No.388
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.InstitutionPreferencesDao;
import webbroker3.gentrade.data.InstitutionPreferencesPK;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�ڋq�f�[�^�A�b�v���[�hCSV)<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountDataUploadCsv extends WEB3GentradeCsvUploadDataModel
{

    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountDataUploadCsv.class);

    /**
     * (�A�b�v���[�h�敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�A�b�v���[�h�敪�h <BR>
     */
    public static  String UPLOAD_DIV_LABEL = "�A�b�v���[�h�敪";

    /**
     * (�\���o�^ID���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�\���o�^ID�h <BR>
     */
    public static  String REGIST_ID_LABEL = "�\���o�^ID";

    /**
     * (�،���ЃR�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�،���ЃR�[�h�h <BR>
     */
    public static  String INSTITUTION_CODE_LABEL = "�،���ЃR�[�h";

    /**
     * (���X�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���X�R�[�h�h <BR>
     */
    public static  String BRANCH_CODE_LABEL = "���X�R�[�h";

    /**
     * (�T�[�r�X�敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�T�[�r�X�敪�h <BR>
     */
    public static  String SRV_DIV_LABEL = "�T�[�r�X�敪";

    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ڋq�R�[�h�h <BR>
     */
    public static  String ACCOUNT_CODE_LABEL = "�ڋq�R�[�h";

    /**
     * (�ڋq�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ڋq���h <BR>
     */
    public static  String ACCOUNT_NAME_LABEL = "�ڋq��";

    /**
     * (�o�^�敪���x��)<BR>
     * �萔��`�v���p�e�B�@@"�o�^�敪"<BR>
     */
    public static  String PAYMENT_DIV_LABEL = "�o�^�敪";

    /**
     * (�\�����I�敪���x��)<BR>
     * �萔��`�v���p�e�B�@@"�\�����I�敪"<BR>
     */
    public static  String APPLI_LOT_DIV_LABEL = "�\�����I�敪";

    /**
     * (�\�������x��)<BR>
     * �萔��`�v���p�e�B�@@"�\����"<BR>
     */
    public static  String APPLI_DATE_LABEL = "�\����";

    /**
     * (�K�p�J�n�����x��)<BR>
     * �萔��`�v���p�e�B�@@"�K�p�J�n��"<BR>
     */
    public static  String APPLI_START_DATE_LABEL = "�K�p�J�n��";

    /**
     * (�K�p�I�������x��)<BR>
     * �萔��`�v���p�e�B�@@"�K�p�I����"<BR>
     */
    public static  String APPLI_END_DATE_LABEL = "�K�p�I����";

    /**
     * (���p�������x��)<BR>
     * �萔��`�v���p�e�B�@@"���p����"<BR>
     */
    public static  String USE_AMT_LABEL = "���p����";

    /**
     * (�o���]�̓��x��)<BR>
     * �萔��`�v���p�e�B�@@"�o���]��"<BR>
     */
    public static  String PAYMENT_POWER_LABEL = "�o���]��";

    /**
     * (�o�������x��)<BR>
     * �萔��`�v���p�e�B�@@"�o����"<BR>
     */
    public static  String PAYMENT_DATE_LABEL = "�o����";

    /**
     * (�A�b�v���[�h�敪_�V�K�o�^)<BR>
     * �萔��`�v���p�e�B�@@�A�b�v���[�h�敪-�V�K�o�^<BR>
     */
    public static  String UPLOAD_DIV_NEW_REGIST_LABEL = "0";

    /**
     * (�A�b�v���[�h�敪_�ύX�o�^)<BR>
     * �萔��`�v���p�e�B�@@�A�b�v���[�h�敪-�ύX�o�^<BR>
     */
    public static  String UPLOAD_DIV_CHANGE_REGIST_LABEL = "1";

    /**
     * (�A�b�v���[�h�敪_���I���ʃA�b�v���[�h)<BR>
     * �萔��`�v���p�e�B�@@�A�b�v���[�h�敪-���I���ʃA�b�v���[�h<BR>
     */
    public static  String UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL = "2";

    /**
     * (�\������)<BR>
     * �萔��`�v���p�e�B�@@�A�b�v���[�h�敪-�\������<BR>
     */
    public static String APPLI_ATTRIBUTE_LABEL = "3";

    /**
     * (�o�^�敪_�L��)<BR>
     * �萔��`�v���p�e�B�@@�o�^�敪�|�L��<BR>
     */
    public static  String PAYMENT_DIV_CHARGE_LABEL = "0";

    /**
     * (�o�^�敪_����)<BR>
     * �萔��`�v���p�e�B�@@�o�^�敪�|����<BR>
     */
    public static  String PAYMENT_DIV_FREE_LABEL = "1";

    /**
     * (�\�����I�敪_���p�\��)<BR>
     * �萔��`�v���p�e�B�@@�\�����I�敪�|���p�\��<BR>
     */
    public static  String APPLI_LOT_DIV_TRIAL_LABEL = "0";

    /**
     * (�\�����I�敪_�\��)<BR>
     * �萔��`�v���p�e�B�@@�\�����I�敪�|�\��<BR>
     */
    public static  String APPLI_LOT_DIV_APPLI_LABEL = "1";

    /**
     * (�\�����I�敪_���I)<BR>
     * �萔��`�v���p�e�B�@@�\�����I�敪�|���I�i���I�Ȃ��̏ꍇ�́A�{�\���̈Ӂj<BR>
     */
    public static  String APPLI_LOT_DIV_ELECTION_LABEL = "2";

    /**
     * (�\�����I�敪_���I)<BR>
     * �萔��`�v���p�e�B�@@�\�����I�敪�|���I<BR>
     */
    public static  String APPLI_LOT_DIV_DEFEAT_LABEL = "3";

    /**
     * (�\�����I�敪_���)<BR>
     * �萔��`�v���p�e�B�@@�\�����I�敪�|���<BR>
     */
    public static  String APPLI_LOT_DIV_CANCEL_LABEL = "4";
    
    /**
     * (�\�����I�敪_����)<BR>
     * �萔��`�v���p�e�B�@@�\�����I�敪�|����<BR>
     */
    public static String APPLI_LOT_DIV_FREE_LABEL = "1";
    
    /**
     * (�\�����I�敪_���p�s��)<BR>
     * �萔��`�v���p�e�B�@@�\�����I�敪�|���p�s��<BR>
     */
    public static String APPLI_LOT_DIV_USE_NOT_LABEL = "2";

    /**
     * (�\�����I�敪_�����폜)<BR>
     * �萔��`�v���p�e�B�@@�\�����I�敪�|�����폜<BR>
     */
    public static String APPLI_LOT_DIV_ATTRIBUTE_DELETE_LABEL = "3";

    /**
     * (���I�ݒ�_���I��)<BR>
     * ���I�ݒ�_���I����\������B<BR>
     */
    public static  String LOT_DIV_NO_LOT_LABEL = "0";

    /**
     * (�A�b�v���[�h�t�@@�C��ID)<BR>
     */
    public String strUploadFileId = "�ڋq�f�[�^�A�b�v���[�h";

    /**
     * (�ڋq�f�[�^�A�b�v���[�hCSV)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �|this.create�J�����w�b�_()���R�[������B <BR>
     * @@roseuid 410F76370208
     */
    public WEB3AdminSrvRegiAccountDataUploadCsv()
    {
        this.createColumnHeader();
    }

    /**
     * (�ڋq�f�[�^�A�b�v���[�hCSV)<BR>
     * �R���X�g���N�^ <BR>
     * �|�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B<BR>
     * �|this.create�J�����w�b�_()���R�[������B <BR>
     * @@param l_lngUploadId - (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID <BR>
     * <BR>
     * ���@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u����PK<BR>
     * @@roseuid 410F75C10342
     */
    public WEB3AdminSrvRegiAccountDataUploadCsv(long l_lngUploadId)
    {
        this.administratorUploadId = l_lngUploadId;
        this.createColumnHeader();//WEB3-SRVREGI-A-UT-0086
    }

    /**
     * (get�A�b�v���[�h�t�@@�C���h�c)<BR>
     * �ڋq�f�[�^�A�b�v���[�hCSV.�A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR>
     * @@return String
     * @@roseuid 410F1C1003B0
     */
    public String getUploadFileId()
    {
        return this.strUploadFileId;
    }

    /**
     * (get�����^�C�v)<BR>
     * �����^�C�v��ԋp����B<BR>
     * <BR>
     * ProductTypeEnum.���̑���ԋp����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum
     * @@roseuid 410F1C7102A6
     */
    public ProductTypeEnum getProductType()
    {
        return ProductTypeEnum.OTHER;
    }

    /**
     * (get�A�b�v���[�h�敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̃A�b�v���[�h�敪���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂăA�b�v���[�h�敪���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�ڋq�f�[�^�A�b�v���[�hCSV.�A�b�v���[�h�敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return String
     * @@roseuid 410F20E30371
     */
    public String getUploadDiv(int l_intLineNumber)
    {
        String l_strUploadDiv = this.getValue(l_intLineNumber, this.getColumnModel(UPLOAD_DIV_LABEL)).toString();
        return l_strUploadDiv;
    }

    /**
     * (get�\���o�^�h�c)<BR>
     * �ڋq�f�[�^�A�b�v���[�hCSV.�\���o�^�h�c��ԋp����B<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̐\���o�^�h�c���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĐ\���o�^�h�c���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�ڋq�f�[�^�A�b�v���[�hCSV.�\���o�^�h�c���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return String
     * @@roseuid 41201F3B004A
     */
    public String getRegistId(int l_intLineNumber)
    {
		String l_strRegistId = (String) this.getValue(l_intLineNumber, this.getColumnModel(REGIST_ID_LABEL));
        return l_strRegistId;
    }

    /**
     * (get�،���ЃR�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̏،���ЃR�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď،���ЃR�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�ڋq�f�[�^�A�b�v���[�hCSV.�،���ЃR�[�h���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return String
     * @@roseuid 410F1E5A03B0
     */
    public String getInstitutionCode(int l_intLineNumber)
    {
        String l_strInstitutionCode = (String) this.getValue(l_intLineNumber, this.getColumnModel(INSTITUTION_CODE_LABEL));
        return l_strInstitutionCode;
    }

    /**
     * (get���X�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĕ��X�R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�ڋq�f�[�^�A�b�v���[�hCSV.���X�R�[�h���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return String
     * @@roseuid 410F1E5A03DE
     */
    public String getBranchCode(int l_intLineNumber)
    {
        String l_strBranchCode = (String) this.getValue(l_intLineNumber, this.getColumnModel(BRANCH_CODE_LABEL));
        return l_strBranchCode;
    }

    /**
     * (get�T�[�r�X�敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̃T�[�r�X�敪���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂăT�[�r�X�敪���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�ڋq�f�[�^�A�b�v���[�hCSV.�T�[�r�X�敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return String
     * @@roseuid 410F1E5B0025
     */
    public String getSrvDiv(int l_intLineNumber)
    {
        String l_strSrvDiv = (String) this.getValue(l_intLineNumber, this.getColumnModel(SRV_DIV_LABEL));
        return l_strSrvDiv;
    }

    /**
     * (get�ڋq�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌ڋq�R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂČڋq�R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�ڋq�f�[�^�A�b�v���[�hCSV.�ڋq�R�[�h���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return String
     * @@roseuid 410F1E5B0054
     */
    public String getAccountCode(int l_intLineNumber)
    {
        String l_strAccountCode = (String) this.getValue(l_intLineNumber, this.getColumnModel(ACCOUNT_CODE_LABEL));
        return l_strAccountCode;
    }

    /**
     * (get�o�^�敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓o�^�敪���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂēo�^�敪���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�ڋq�f�[�^�A�b�v���[�hCSV.�o�^�敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return String
     * @@roseuid 410F1E5B0083
     */
    public String getPaymentDiv(int l_intLineNumber)
    {
        // [WEB3-SRVREGI-A-FT-0163�̍Ď���]�Ƃ���QA�ɂ��C��
        String l_strPaymentDiv = null;
        Object l_paymentDivValue =this.getValue(l_intLineNumber, this.getColumnModel(PAYMENT_DIV_LABEL));
        if (l_paymentDivValue != null)
        {
            l_strPaymentDiv = l_paymentDivValue.toString();
        }
        return l_strPaymentDiv;
    }

    /**
     * (get�\�����I�敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̐\�����I�敪���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĐ\�����I�敪���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�ڋq�f�[�^�A�b�v���[�hCSV.�\�����I�敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return String
     * @@roseuid 410F1E5B00B2
     */
    public String getAppliLotDiv(int l_intLineNumber)
    {
        String l_strAppliLotDiv = this.getValue(l_intLineNumber, this.getColumnModel(APPLI_LOT_DIV_LABEL)).toString();
        return l_strAppliLotDiv;
    }

    /**
     * (get�\����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̐\�������擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĐ\�������擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�ڋq�f�[�^�A�b�v���[�hCSV.�\�������x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return Date
     * @@roseuid 410F1E5B00F0
     */
    public Date getAppliDate(int l_intLineNumber)
    {
        Date l_dat = (Date)this.getValue(l_intLineNumber, this.getColumnModel(APPLI_DATE_LABEL));
        return l_dat;
    }

    /**
     * (get�K�p�J�n��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓K�p�J�n�����擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂēK�p�J�n�����擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�ڋq�f�[�^�A�b�v���[�hCSV.�K�p�J�n�����x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return Date
     * @@roseuid 410F1E5B011F
     */
    public Date getAppliStartDate(int l_intLineNumber)
    {
		Date l_dat = (Date)this.getValue(l_intLineNumber, this.getColumnModel(APPLI_START_DATE_LABEL));
        return l_dat;
    }

    /**
     * (get�K�p�I����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓K�p�I�������擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂēK�p�I�������擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�ڋq�f�[�^�A�b�v���[�hCSV.�K�p�I�������x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return Date
     * @@roseuid 410F1E5B014E
     */
    public Date getAppliEndDate(int l_intLineNumber)
    {
        Date l_dat = (Date)this.getValue(l_intLineNumber, this.getColumnModel(APPLI_END_DATE_LABEL));
        return l_dat;
    }

    /**
     * (get���p����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̗��p�������擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂė��p�������擾���A�W���f�[�^�^(double)�ɕϊ����ĕԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�ڋq�f�[�^�A�b�v���[�hCSV.���p�������x��)�̖߂�l�B<BR>
     * <BR>
     * ���j�擾�������p����==null�̏ꍇ�Anull��ԋp����B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return double
     * @@roseuid 410F1E5B017D
     */
    public Double getUseAmt(int l_intLineNumber)
    {
		Double l_dblUseAmt = null;

		if ((this.getColumnModel(USE_AMT_LABEL)  != null) &&
			(this.getValue(l_intLineNumber, this.getColumnModel(USE_AMT_LABEL)) != null))
		{
			l_dblUseAmt = new Double(
				this.getValue(l_intLineNumber, this.getColumnModel(USE_AMT_LABEL)).toString());
		}
		return l_dblUseAmt;
    }

    /**
     * (get�o����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̏o�������擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂďo�������擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�ڋq�f�[�^�A�b�v���[�hCSV.�o�������x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return Date
     * @@roseuid 410F1E5B01CB
     */
    public Date getPaymentDate(int l_intLineNumber)
    {
        Date l_dat = (Date)this.getValue(l_intLineNumber, this.getColumnModel(PAYMENT_DATE_LABEL));
        return l_dat;
    }

    /**
     * (validate�A�b�v���[�h�敪)<BR>
     * �A�b�v���[�h�敪�̒l�����������`�F�b�N���s���B<BR>
     * <BR>
     * [Error����]�@@�i�A�b�v���[�h�敪�G���[�j<BR>
     * <BR>
     * �i�A�b�v���[�h�敪 �� �ڋq�A�b�v���[�hCSV.�A�b�v���[�h�敪_�V�K�o�^�j and<BR>
     * �i�A�b�v���[�h�敪 �� �ڋq�A�b�v���[�hCSV.�A�b�v���[�h�敪_�ύX�o�^�j and<BR>
     * �i�A�b�v���[�h�敪 �� �ڋq�A�b�v���[�hCSV.�A�b�v���[�h�敪_���I���ʃA�b�v���[�h�j and<BR>
     * �i�A�b�v���[�h�敪 �� �ڋq�A�b�v���[�hCSV.�A�b�v���[�h�敪_�\�������j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01020<BR>
     * @@param l_strUploadDiv - (�A�b�v���[�h�敪)<BR>
     * �A�b�v���[�h�敪<BR>
     * <BR>
     * 0�F�V�K�o�^<BR>
     * 1�F�ύX�o�^<BR>
     * 2�F���I���ʃA�b�v���[�h<BR>
     * 3�F�\������<BR>
     * @@roseuid 410F256E00F0
     */
    public void validateUploadDiv(String l_strUploadDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateUploadDiv(String )";
        log.entering(STR_METHOD_NAME);

        if (l_strUploadDiv == null
            || !(WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv)
                || WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_CHANGE_REGIST_LABEL.equals(l_strUploadDiv)
                || WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL.equals(l_strUploadDiv)
                || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv)))
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01020,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�A�b�v���[�h�敪�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�T�[�r�X�敪)<BR>
     * �w�肳�ꂽ�T�[�r�X�敪���A�T�[�r�X�o�^����Ă��邩�A�\���\���`�F�b�N����B<BR>
     * <BR>
     * �P�j �T�[�r�X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@(�T�[�r�X���p)���i�G���e�B�e�B.�T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^�[()�ɂ�
     * �@@�T�[�r�X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[�T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^�[()�Ɏw�肷�����]<BR>
     * �@@�،���ЃR�[�h�F�����̏،���ЃR�[�h<BR>
     * �@@�T�[�r�X�敪�F�����̃T�[�r�X�敪<BR>
     * �@@is�s���b�N�Ffalse<BR>
     * <BR>
     * <BR>
     * �Q�j �T�[�r�X���\���\���`�F�b�N����B<BR>
     * <BR>
     * �@@(�T�[�r�X���p)���i�G���e�B�e�B.�T�[�r�X.is�\���\()�ɂ�<BR>
     * �@@�T�[�r�X�\���\���`�F�b�N���A�\���\�łȂ��ꍇ�͗�O���X���[����B<BR>
     * �@@�i�T�[�r�X�敪�G���[�j�@@<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01000<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���w�肷��B<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪���w�肷��B<BR>
     * @@roseuid 41171E890019
     */
    public void validateSrvDiv(String l_strInstitutionCode, String l_strSrvDiv)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSrvDiv(String, String )";
        log.entering(STR_METHOD_NAME );

        //�P�j �T�[�r�X�I�u�W�F�N�g���擾����
        WEB3SrvRegiServiceInfoManagement l_management = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_master = l_management.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

        //�Q�j �T�[�r�X���\���\���`�F�b�N����B
        if (!l_master.isAppliPossible())
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01000,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�T�[�r�X�敪�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�o�^�敪)<BR>
     * �o�^�敪�̒l�����������`�F�b�N���s���B<BR>
     * <BR>
     * [Error����]�i�o�^�敪�G���[�j<BR>
     * <BR>
     * �i�o�^�敪 != �ڋq�A�b�v���[�hCSV.�o�^�敪_�L���j and<BR>
     * �i�o�^�敪 != �ڋq�A�b�v���[�hCSV.�o�^�敪_�����j <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00841<BR>
     * @@param l_strPaymentDiv - (�o�^�敪)<BR>
     * �o�^�敪<BR>
     * <BR>
     * 0�F�L��<BR>
     * 1�F����<BR>
     * @@roseuid 410F25840390
     */
    public void validatePaymentDiv(String l_strPaymentDiv)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePaymentDiv(String, String )";
        log.entering(STR_METHOD_NAME );

        //[Error����]�i�o�^�敪�G���[�j
        // �i�o�^�敪 != �ڋq�A�b�v���[�hCSV.�o�^�敪_�L���j and
        // �i�o�^�敪 != �ڋq�A�b�v���[�hCSV.�o�^�敪_�����j
        if (l_strPaymentDiv == null
            || !(WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_DIV_CHARGE_LABEL.equals(l_strPaymentDiv)
                || WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_DIV_FREE_LABEL.equals(l_strPaymentDiv)))
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00841,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�o�^�敪�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�\�����I�敪)<BR>
     * ���׍s�̐\�����I�敪�̐ݒ�l���Ó����`�F�b�N����B<BR>
     * <BR>
     * 1�j ����.���I�ݒ肪���I�ݒ�_���I���̏ꍇ<BR>
     * <BR>
     * �@@�@@ �i����.�A�b�v���[�h�敪==�A�b�v���[�h�敪_���I���ʃA�b�v���[�h�j�̏ꍇ<BR>
     * �@@�@@�@@��O���X���[����B�i�\�����I�敪�G���[�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     * <BR>
     * �@@�A �i����.�A�b�v���[�h�敪���A�b�v���[�h�敪_���I���ʃA�b�v���[�h�j������<BR>
     * �@@�@@�@@����.�A�b�v���[�h�敪���A�b�v���[�h�敪_�\�������j�Ȃ�<BR>
     * �@@�@@�@@�i����.�\�����I�敪���\�����I�敪_���I�j�ȊO�́A<BR>
     * �@@�@@�@@�R�[�h�l�s���Ƃ��ė�O���X���[����B�i�\�����I�敪�G���[�j<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     * <BR>
     * �@@�B �i����.�A�b�v���[�h�敪 == �A�b�v���[�h�敪_�\�������j�̏ꍇ<BR>
     *      �ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�A�R�[�h�l�s���Ƃ��ė�O���X���[����B 
     *      �i�\�����I�敪�G���[�j<BR>
     *          �i����.�\�����I�敪 == �\�����I�敪_�����j�@@or <BR>
     *          �i����.�\�����I�敪 == �\�����I�敪_���p�s�jor <BR>
     *          �i����.�\�����I�敪 == �\�����I�敪_�����폜�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     * <BR>  
     * 2�j ����.���I�ݒ肪���I�ݒ�_���I���ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@ �i����.�A�b�v���[�h�敪==�A�b�v���[�h�敪_�V�K�o�^�j �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@���̏����ɓ��Ă͂܂�ꍇ�A�R�[�h�l�s���Ƃ��ė�O���X���[����B<BR>
     * �@@�@@�@@�@@�i�\�����I�敪�G���[�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�i����.�\�����I�敪���\�����I�敪_�\���j�@@and<BR>
     * �@@�@@�@@�@@�@@�i����.�\�����I�敪���\�����I�敪_���I�j�@@and<BR>
     * �@@�@@�@@�@@�@@�i����.�\�����I�敪���\�����I�敪_���I�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     * <BR>
     * �@@�A �i����.�A�b�v���[�h�敪==�A�b�v���[�h�敪_�ύX�o�^�j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@���̏����ɓ��Ă͂܂�ꍇ�A�R�[�h�l�s���Ƃ��ė�O���X���[����B�i�\�����I�敪�G���[�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�i����.�\�����I�敪���\�����I�敪_�\���j�@@and<BR>
     * �@@�@@�@@�@@�@@�i����.�\�����I�敪���\�����I�敪_���I�j�@@and<BR>
     * �@@�@@�@@�@@�@@�i����.�\�����I�敪���\�����I�敪_���I�j�@@and<BR>
     * �@@�@@�@@�@@�@@�i����.�\�����I�敪���\�����I�敪_����j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     * <BR>
     * �@@�B �i����.�A�b�v���[�h�敪==�A�b�v���[�h�敪_���I���ʃA�b�v���[�h�j<BR>
     * <BR>
     * �@@�@@�@@���̏����ɓ��Ă͂܂�ꍇ�A�R�[�h�l�s���Ƃ��ė�O���X���[����B<BR> �@@�@@�@@�@@�@@�@@�@@�@@
     *       �i�\�����I�敪�G���[�j<BR>
     * �@@�@@ <BR>
     * �@@�@@�@@�@@�@@�i����.�\�����I�敪���\�����I�敪_���I�j�@@and<BR>
     * �@@�@@�@@�@@�@@�i����.�\�����I�敪���\�����I�敪_���I�j�@@�@@�@@�@@<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     * @@param l_strUploadDiv - (�A�b�v���[�h�敪)<BR>
     * �A�b�v���[�h�敪<BR>
     * <BR>
     * 0�F�V�K�o�^<BR>
     * 1�F�ύX�o�^<BR>
     * 2�F���I���ʃA�b�v���[�h<BR>
     * @@param l_strLotDiv - (���I�ݒ�)<BR>
     * ���I�ݒ�iget���I�ݒ�()�̖߂�l�j���w�肷��B<BR>
     *
     *
     * @@param l_strAppliLotDiv - (�\�����I�敪)<BR>
     * �\�����I�敪<BR>
     *
     * 1�F�\��<BR>
     * 2�F���I�i���I�����̏ꍇ�͖{�\���j<BR>
     * 3�F���I<BR>
     * 4�F���<BR>
     * @@roseuid 410F259C0323
     */
    public void validateAppliLotDiv(String l_strUploadDiv, String l_strLotDiv, String l_strAppliLotDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAppliLotDiv(String, String, String )";
        log.entering(STR_METHOD_NAME );

        //1�j ����.���I�ݒ肪���I�ݒ�_���I���̏ꍇ
        if (WEB3AdminSrvRegiAccountDataUploadCsv.LOT_DIV_NO_LOT_LABEL.equals(l_strLotDiv))
        {
			//�i����.�A�b�v���[�h�敪==�A�b�v���[�h�敪_���I���ʃA�b�v���[�h�j�̏ꍇ
            if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL.equals(l_strUploadDiv))
            {
                WEB3BaseException l_e =
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01022,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("�\�����I�敪�G���[.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
			//�i����.�A�b�v���[�h�敪���A�b�v���[�h�敪_���I���ʃA�b�v���[�h�j������ 
            //����.�A�b�v���[�h�敪���A�b�v���[�h�敪_�\�������j�Ȃ� 
            else if(!WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv))
            {
			    //�i����.�\�����I�敪���\�����I�敪_���I�j�ȊO�́A
			    if (!WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ELECTION_LABEL.equals(l_strAppliLotDiv))
			    {
				    WEB3BaseException l_e =
					    new WEB3BusinessLayerException(
						    WEB3ErrorCatalog.BUSINESS_ERROR_01022,
						    this.getClass().getName() + STR_METHOD_NAME);
				    log.debug("�\�����I�敪�G���[.", l_e);
				    log.exiting(STR_METHOD_NAME);
				    throw l_e;
			  }            	
            }
            // �i����.�A�b�v���[�h�敪 == �A�b�v���[�h�敪_�\�������j�̏ꍇ
            if (WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv))
            {
                // �ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�A�R�[�h�l�s���Ƃ��ė�O���X���[����
                // �i����.�\�����I�敪 == �\�����I�敪_�����j�@@or
                // �i����.�\�����I�敪 == �\�����I�敪_���p�s�jor
                // �i����.�\�����I�敪 == �\�����I�敪_�����폜�j
                if (!(WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_FREE_LABEL.equals(l_strAppliLotDiv)
                    || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_USE_NOT_LABEL.equals(l_strAppliLotDiv)
                    || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ATTRIBUTE_DELETE_LABEL.equals(
                       l_strAppliLotDiv)))
                {
                    WEB3BaseException l_e =
                        new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01022,
                            this.getClass().getName() + STR_METHOD_NAME);
                    log.debug("�\�����I�敪�G���[.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
            }
        }
        else
        {
            //2�j ����.���I�ݒ肪���I�ݒ�_���I���ȊO�̏ꍇ
            if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv))
            {
                //�@@ �i����.�A�b�v���[�h�敪==�A�b�v���[�h�敪_�V�K�o�^�j �̏ꍇ
                if (l_strAppliLotDiv == null
                    || !(WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_APPLI_LABEL.equals(l_strAppliLotDiv)
                        || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ELECTION_LABEL.equals(l_strAppliLotDiv)
                        || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_DEFEAT_LABEL.equals(l_strAppliLotDiv)))
                {
                    WEB3BaseException l_e =
                        new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01022,
                            this.getClass().getName() + STR_METHOD_NAME);
                    log.debug("�\�����I�敪�G���[.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
            }
            else if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_CHANGE_REGIST_LABEL.equals(l_strUploadDiv))
            {
                //�A �i����.�A�b�v���[�h�敪==�A�b�v���[�h�敪_�ύX�o�^�j�̏ꍇ
                if (l_strAppliLotDiv == null
                    || !(WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_APPLI_LABEL.equals(l_strAppliLotDiv)
                        || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ELECTION_LABEL.equals(l_strAppliLotDiv)
                        || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_DEFEAT_LABEL.equals(l_strAppliLotDiv)
                        || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_CANCEL_LABEL.equals(l_strAppliLotDiv)))
                {
                    WEB3BaseException l_e =
                        new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01022,
                            this.getClass().getName() + STR_METHOD_NAME);
                    log.debug("�\�����I�敪�G���[.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

            }
            else if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL.equals(l_strUploadDiv))
            {
                //�B �i����.�A�b�v���[�h�敪==�A�b�v���[�h�敪_���I���ʃA�b�v���[�h�j
                if (l_strAppliLotDiv == null
                    || !(WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ELECTION_LABEL.equals(l_strAppliLotDiv)
                        || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_DEFEAT_LABEL.equals(l_strAppliLotDiv)))
                {
                    WEB3BaseException l_e =
                        new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01022,
                            this.getClass().getName() + STR_METHOD_NAME);
                    log.debug("�\�����I�敪�G���[.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�ڋq)<BR>
     * �ڋq�R�[�h�ɑΉ�����ڋq���o�^����Ă��邩�`�F�b�N����B<BR>
     * <BR>
     * �P�j�،���ЃR�[�h�̌�����2���̐����łȂ��ꍇ�͗�O���X���[����B�i�،���ЃR�[�h�G���[�j<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01023<BR>
     * <BR>
     * �Q�j���X�R�[�h�̌�����3���̐����łȂ��ꍇ�͗�O���X���[����B�i���X�R�[�h�G���[�j<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * �R�j�ڋq�R�[�h�̌�����6���̐����łȂ��ꍇ�͗�O���X���[����B�i�ڋq�R�[�h�G���[�j<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00780<BR>
     * <BR>
     * �S�j�ڋq�I�u�W�F�N�g���擾����<BR>
     * �@@�@@�B �ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�@@�A�J�E���g�}�l�[�W��.getMainAccount(�،���ЃR�[�h, ����.���X�R�[�h, ����.�ڋq�R�[�h)<BR>
     * <BR>
     * �@@�@@�C �ڋq�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�͗�O���X���[����B�i���o�^�ڋq�G���[�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01026<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���w�肷��B<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h���w�肷��B<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h���w�肷��B<BR>
     * @@roseuid 41171F5901EE
     */
    public void validateAccount(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAccount(String, String, String )";
        log.entering(STR_METHOD_NAME );

        //�P�j�،���ЃR�[�h�̌�����2���łȂ��ꍇ�͗�O���X���[����B�i�،���ЃR�[�h�G���[�j
        if (l_strInstitutionCode == null
            || l_strInstitutionCode.length() != 2)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01023,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�،���ЃR�[�h�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //�Q�j���X�R�[�h�̌�����3���łȂ��ꍇ�͗�O���X���[����B�i���X�R�[�h�G���[�j
        if (l_strBranchCode == null
            || l_strBranchCode.length() != 3)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("���X�R�[�h�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //�R�j�ڋq�R�[�h�̌�����6���łȂ��ꍇ�͗�O���X���[����B�i�ڋq�R�[�h�G���[�j
        if (l_strAccountCode == null
            || l_strAccountCode.length() != 6)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�ڋq�R�[�h�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //�S�j�ڋq�I�u�W�F�N�g���擾����
        //WEB3-SRVREGI-A-UT-0090
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //�g���A�J�E���g�}�l�[�W���擾����
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //�B �ڋq�I�u�W�F�N�g���擾����B
            l_accountManager.getMainAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
        }
        catch (WEB3SystemLayerException l_e)
        {
            //�C �ڋq�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�͗�O���X���[����B�i���o�^�ڋq�G���[�j
            WEB3BaseException l_webe =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01026,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���o�^�ڋq�G���[.",
                    l_e);
            log.error("���o�^�ڋq�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_webe;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���t�ݒ�)<BR>
     * �\�����A�K�p�J�n���A�K�p�I�����̐ݒ�l�Ƒ��݊֘A�̃`�F�b�N������B<BR>
     * <BR>
     * �P�j ���t�̗���`�F�b�N<BR>
     * �@@�@@ �\������null�A�܂��͗���łȂ��ꍇ�͗�O���X���[����B�i�\�����G���[�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01027<BR>
     * �@@�A �K�p�J�n����null�A�܂��͗���łȂ��ꍇ�͗�O���X���[����B�i�K�p�J�n���G���[�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01028<BR>
     * �@@�B �K�p�J�n����null�A�܂��͗���łȂ��ꍇ�͗�O���X���[����B�i�K�p�I�����G���[�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01029<BR>
     * <BR>
     * �Q�j ���t�̑��֊֌W�`�F�b�N<BR>
     * �@@�@@ �\�����@@���@@�K�p�J�n���@@�łȂ��ꍇ�͗�O���X���[����B�i�K�p�J�n���s�K�؃G���[�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00840<BR>
     * �@@�A �K�p�J�n���@@���@@�K�p�I�����@@�łȂ��ꍇ�͗�O���X���[����B�i�K�p�I�����s�K�؃G���[�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00839<BR>
     * <BR>
     * �R�j ���I�L��̃T�[�r�X�̏ꍇ�A�\���������I���e�[�u���̐\�����ԓ����`�F�b�N����B<BR>
     * �@@�@@�i�\����00:00:00����\����23:59:59�͈̔͂��\�����ԓ��ł����OK�j<BR>
     * <BR>
     * �@@�@@�@@get�T�[�r�X���I���()���R�[�����āA�\�����ԁi���j���\�������\�����ԁi���j���`�F�b�N����B<BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F�����̏،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�T�[�r�X�敪�F�����̃T�[�r�X�敪<BR>
     * �@@�@@�@@�@@�w������F�����̐\�����iYYYYMMDD�j�Ɏ��ԁi00:00:00�j��t���������̂��Z�b�g<BR>
     * �@@�@@�@@�@@���[���l�F0<BR>
     * <BR>
     * �@@�A�@@get�T�[�r�X���I���()���R�[�����āA�\�����ԁi���j���\�������\�����ԁi���j���`�F�b�N����B<BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F�����̏،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�T�[�r�X�敪�F�����̃T�[�r�X�敪<BR>
     * �@@�@@�@@�@@�w������F�����̐\�����iYYYYMMDD�j�Ɏ��ԁi23:59:59�j��t���������̂��Z�b�g<BR>
     * �@@�@@�@@�@@���[���l�F0<BR>
     * <BR>
     * �@@�B�@@�@@�ƇA���ɖ߂�l��null�̏ꍇ�A�\�������\�����ԊO�Ɣ��f����O���X���[����B<BR>
     * �@@�@@�@@�@@�i�\�����ݒ�G���[�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00993<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���w�肷��B<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪���w�肷��B<BR>
     * @@param l_strLotDiv - (���I�ݒ�)<BR>
     * ���I�ݒ�iget���I�ݒ�()�̖߂�l�j���w�肷��B<BR>
     *
     *
     * @@param l_datAppliDate - (�\����)<BR>
     * ���׍s�̐\����<BR>
     * @@param l_datAppliStartDate - (�K�p�J�n��)<BR>
     * ���׍s�̓K�p�J�n��<BR>
     * @@param l_datAppliEndDate - (�K�p�I����)<BR>
     * ���׍s�̓K�p�I����<BR>
     * @@roseuid 4117536002B9
     */
    public void validateTimestampSetup(
        String l_strInstitutionCode,
        String l_strSrvDiv,
        String l_strLotDiv,
        Date l_datAppliDate,
        Date l_datAppliStartDate,
        Date l_datAppliEndDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateTimestampSetup(String, String, String, Date, Date, Date )";
        log.entering(STR_METHOD_NAME );

        //�P�j ���t�̗���`�F�b�N<BR>
        //�@@ �\������null�A�܂��͗���łȂ��ꍇ�͗�O���X���[����B�i�\�����G���[�j
        if (l_datAppliDate == null)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01027,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�\�����G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //�A �K�p�J�n����null�A�܂��͗���łȂ��ꍇ�͗�O���X���[����B�i�K�p�J�n���G���[�j
        if (l_datAppliStartDate == null)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01028,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�K�p�J�n���G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //�B �K�p�I������null�A�܂��͗���łȂ��ꍇ�͗�O���X���[����B�i�K�p�I�����G���[�j
        if (l_datAppliEndDate == null)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01029,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�K�p�I�����G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //�Q�j ���t�̑��֊֌W�`�F�b�N
        //�@@ �\�����@@���@@�K�p�J�n���@@�łȂ��ꍇ�͗�O���X���[����B�i�K�p�J�n���s�K�؃G���[�j
        if (WEB3DateUtility.compareToDay(l_datAppliDate, l_datAppliStartDate) > 0)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00840,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�K�p�J�n���s�K�؃G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //�A �K�p�J�n���@@���@@�K�p�I�����@@�łȂ��ꍇ�͗�O���X���[����B�i�K�p�I�����s�K�؃G���[�j
        if (WEB3DateUtility.compareToDay(l_datAppliStartDate, l_datAppliEndDate) > 0)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00839,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�K�p�I�����s�K�؃G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //�R�j ���I�L��̃T�[�r�X�̏ꍇ�A�\���������I���e�[�u���̐\�����ԓ����`�F�b�N����B
        if (!WEB3AdminSrvRegiAccountDataUploadCsv.LOT_DIV_NO_LOT_LABEL.equals(l_strLotDiv))
        {
            //�@@�@@get�T�[�r�X���I���()���R�[�����āA�\�����ԁi���j���\�������\�����ԁi���j���`�F�b�N����B
            WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
            Timestamp l_tsAppliDate = new Timestamp(l_datAppliDate.getTime());
            l_tsAppliDate.setHours(0);
            l_tsAppliDate.setMinutes(0);
            l_tsAppliDate.setSeconds(0);
            WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_strSrvDiv, l_tsAppliDate, 0);

            //�A�@@get�T�[�r�X���I���()���R�[�����āA�\�����ԁi���j���\�������\�����ԁi���j���`�F�b�N����B
			if (l_srvRegiServiceLotInfo == null)
			{
	            l_tsAppliDate.setHours(23);
	            l_tsAppliDate.setMinutes(59);
	            l_tsAppliDate.setSeconds(59);
	            l_srvRegiServiceLotInfo =
	                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_strSrvDiv, l_tsAppliDate, 0);
			}

            if (l_srvRegiServiceLotInfo == null)
            {
                //�B�@@�@@�ƇA���ɖ߂�l��null�̏ꍇ�A�\�������\�����ԊO�Ɣ��f����O���X���[����B
                WEB3BaseException l_e =
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00993,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("�\�����ݒ�G���[.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���p����)<BR>
     * ���p�����ɒl���Z�b�g����Ă��邩���`�F�b�N����B<BR>
     * <BR>
     * �o�^�敪���o�^�敪_�L���̏ꍇ�ɁA���p���� �� 0��<BR>
     * �������Z�b�g����Ă��Ȃ��ꍇ�͗�O���X���[����B�i���p���������̓G���[�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01030<BR>
     * @@param l_dblUseAmt - (���p����)<BR>
     * @@param l_strPaymentDiv - (�o�^�敪)<BR>
     * �o�^�敪<BR>
     * <BR>
     * 0�F�L��<BR>
     * 1�F����<BR>
     * @@roseuid 4118374102A5
     */
    public void validateUseAmt(double l_dblUseAmt, String l_strPaymentDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateUseAmt(double, String )";
        log.entering(STR_METHOD_NAME );

        if (WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_DIV_CHARGE_LABEL.equals(l_strPaymentDiv))
        {
            if (l_dblUseAmt <= 0 || Double.isNaN(l_dblUseAmt))
            {
                WEB3BaseException l_e =
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01030,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("���p���������̓G���[.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�o���]��)<BR>
     * �o���]�͂����p�����ȏ゠�邩�`�F�b�N����B<BR>
     * <BR>
     * �P�j �o���]�͂��擾����B<BR>
     *      �|�g���A�J�E���g�}�l�[�W��.get�ڋq( )����ڋq�I�u�W�F�N�g���擾�B<BR>
     *      [get�ڋq�̈���] <BR>
     *�@@     �،���ЃR�[�h������.�،���ЃR�[�h<BR>
     *�@@     ���X�R�[�h������.���X�R�[�h <BR>
     *�@@     �����R�[�h������.�����R�[�h<BR>
     *<BR>
     *      �|�擾�����ڋq�I�u�W�F�N�g.get�⏕����( )����⏕�����I�u�W�F�N�g���擾�B<BR>
     *      [get�⏕�����̈��� <BR>
     *�@@     �⏕�����^�C�v="������������i�a����j"<BR>
     *<BR>
     *      �|����]�̓T�[�r�XImpl.get���̑����i���t�\�z( )�������]�͂��擾�B<BR>
     *      [get���̑����i���t�\�z�̈���] <BR>
     *�@@     �⏕�������擾�����⏕�����I�u�W�F�N�g<BR>
     *�@@     ��n�����o����<BR>
     * <BR>
     * �Q�j �o���]�� �� ���p���� �̏ꍇ�͗�O���X���[����B�i�o���]�̓G���[�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01031<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���w�肷��B<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h���w�肷��B<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h���w�肷��B<BR>
     * @@param l_dblUseAmt - (���p����)<BR>
     * @@param l_tsPaymentDate - (�o����)<BR>
     * @@roseuid 41184CBA025F
     */
    public void validatePaymentPower(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        double l_dblUseAmt,
        Timestamp l_tsPaymentDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePaymentPower(" +
            "String, String, String, double ,Timestamp )";
        log.entering(STR_METHOD_NAME );

        try
        {
            //�P�j �o���]�͂��擾����B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //�|�g���A�J�E���g�}�l�[�W��.get�ڋq( )����ڋq�I�u�W�F�N�g���擾�B
            WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode);
            //�擾�����ڋq�I�u�W�F�N�g.get�⏕����( )����⏕�����I�u�W�F�N�g���擾�B
            SubAccount l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);//NotFoundException

            //����]�̓T�[�r�XImpl.get���̑����i���t�\�z( )�������]�͂��擾�B
            WEB3TPTradingPowerService l_service =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            
            //�d�l�ύX�Ή� NO_209
			double l_dblPaymentPower = l_service.getPaymentTradingPower((WEB3GentradeSubAccount)l_subAccount, l_tsPaymentDate);
            
            //�Q�j �o���]�� �� ���p���� �̏ꍇ�͗�O���X���[����B�i�o���]�̓G���[�j
            if (l_dblPaymentPower < l_dblUseAmt)
            {
                WEB3BaseException l_e =
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01031,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("�o���]�̓G���[.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
        }
        catch (NotFoundException l_e)
        {
            WEB3BaseException l_webe =
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01026,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            log.error("�o�^�ڋq�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_webe;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�o����)<BR>
     * �o�����̃`�F�b�N������B<BR>
     * <BR>
     * �P�j �o������null�A�܂��͗���łȂ��ꍇ�͗�O���X���[����B�i�o�����G���[�j <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01032<BR>
     * <BR>
     * �Q�j �o���� �� ���ݓ��t �̏ꍇ�͗�O���X���[����B�i�o�����G���[�j<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01032<BR>
     * �R�j �o�������c�Ɠ��łȂ��ꍇ�͗�O���X���[����B�i�o�����G���[�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01032<BR>
     * �S�j �c�Ɠ��E���c�Ɠ��̃`�F�b�N <BR>
     * �@@ �T�[�r�X���p������ԊǗ�.get������()>�o�����̏ꍇ�A<BR>
     * �@@��O���X���[����B�i�o�����G���[�j<BR>
     * @@param l_datPaymentDate - (�o����)<BR>
     * ���׍s�̏o����<BR>
     * @@roseuid 411759A90384
     */
    public void validatePaymentDate(Date l_datPaymentDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePaymentDate(Date )";
        log.entering(STR_METHOD_NAME );

        //�P�j�o������null�A�܂��͗���łȂ��ꍇ�͗�O���X���[����B�i�o�����G���[�j
        if (l_datPaymentDate == null)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01032,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�o�����G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //�Q�j �o���� �� ���ݓ��t �̏ꍇ�͗�O���X���[����B�i�o�����G���[�j
        Timestamp l_tsCurrent =
            GtlUtils.getTradingSystem().getSystemTimestamp();
        if (WEB3DateUtility.compareToDay(l_datPaymentDate, l_tsCurrent) <= 0)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01032,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�o�����G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //�R�j �o�������c�Ɠ��łȂ��ꍇ�͗�O���X���[����B�i�o�����G���[�j
        Timestamp l_tsPaymentDate = null;
        if (l_datPaymentDate != null)
        {
            l_tsPaymentDate = new Timestamp(l_datPaymentDate.getTime());
        }
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsPaymentDate)))
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01032,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�o�����G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // �S�j �c�Ɠ��E���c�Ɠ��̃`�F�b�N
        Date l_datBizDate = WEB3SrvRegiTradingTimeManagement.getOrderBizDate();//WEB3SystemLayerException
        if (WEB3DateUtility.compareToDay(l_datBizDate, l_datPaymentDate) >= 0)
        {
            String l_strMessage = "�o�����G���[." + l_tsCurrent.toLocaleString();
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01032,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�A�b�v���[�h����)<BR>
     * �Y�A�b�v���[�h�t�@@�C���Ɋ֘A����A�b�v���[�h���������ׂĎ擾����B <BR>
     * <BR>
     * this.get�A�b�v���[�h����()�ioverload���\�b�h�j���R�[�����A�߂�l��ԋp����B <BR>
     * <BR>
     * [get�A�b�v���[�h����()�Ɏw�肷�����] <BR>
     * �f�[�^�L�[�F�@@�T�[�r�X�敪<BR>
     * @@param l_lngDataKey - (�f�[�^�L�[)<BR>
     * �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�Ǘ��e�[�u���v.�f�[�^�L�[�ɍX�V������e�B<BR>
     *
     * @@return webbroker3.gentrade.data.AdministratorUploadRow[]
     * @@roseuid 4110C73F00B5
     */
    public AdministratorUploadRow[] getUploadAction(long l_lngDataKey)
        throws WEB3SystemLayerException
    {
        AdministratorUploadRow[] l_rows = super.getUploadAction(l_lngDataKey);
        return l_rows;
    }

    /**
     * (get�ŐV�A�b�v���[�h����)<BR>
     * �Y�A�b�v���[�h�t�@@�C���Ɋ֘A���钼�߂̃A�b�v���[�h�������擾����B <BR>
     * <BR>
     * this.get�ŐV�A�b�v���[�h����()�ioverload���\�b�h�j���R�[�����A�߂�l��ԋp����B <BR>
     * <BR>
     * [get�A�b�v���[�h����()�Ɏw�肷�����] <BR>
     * ���l�P�F�@@�T�[�r�X�敪 <BR>
     * @@param l_lngReference - (���l�P)<BR>
     * �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�Ǘ��e�[�u���v.���l�P�ɍX�V������e�B<BR>
     *
     * @@return AdministratorUploadRow
     * @@roseuid 4110C6D701E0
     */
    public AdministratorUploadRow getLatestUploadAction(long l_lngReference)
        throws WEB3SystemLayerException
    {
        AdministratorUploadRow l_row = super.getLatestUploadAction(l_lngReference);
        return l_row;
    }

    /**
     * (create�J�����w�b�_())<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B <BR>
     * <BR>
     * [�J�����w�b�_�z��] <BR>
     * �|�@@index = 0 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�A�b�v���[�hCSV.�A�b�v���[�h�敪���x�� <BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 1 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�A�b�v���[�hCSV.�\���o�^�敪���x�� <BR>
     * �@@�J�����ԍ��F 1 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�A�b�v���[�hCSV.�،���ЃR�[�h���x�� <BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�A�b�v���[�hCSV.���X�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�A�b�v���[�hCSV.�T�[�r�X�敪���x��<BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�A�b�v���[�hCSV.�ڋq�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 5 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 6 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�A�b�v���[�hCSV.�ڋq�����x�� <BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�A�b�v���[�hCSV.�o�^�敪/�\���E���I���x�� <BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 8<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�A�b�v���[�hCSV.�\�������x�� <BR>
     * �@@�J�����ԍ��F 8<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t<BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyymmdd")<BR>
     * <BR>
     * �|�@@index = 9<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�A�b�v���[�hCSV.�K�p�J�n�����x�� <BR>
     * �@@�J�����ԍ��F 9<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t<BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyymmdd")<BR>
     * <BR>
     * �|�@@index = 10<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�A�b�v���[�hCSV.�K�p�I�������x�� <BR>
     * �@@�J�����ԍ��F 10<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t<BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyymmdd")<BR>
     * <BR>
     * �|�@@index = 11<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�A�b�v���[�hCSV.���p�������x�� <BR>
     * �@@�J�����ԍ��F 11<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l(double) <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 12<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�A�b�v���[�hCSV.�o���]�̓��x�� <BR>
     * �@@�J�����ԍ��F 12<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l(double) <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 13<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�A�b�v���[�hCSV.�o�������x�� <BR>
     * �@@�J�����ԍ��F 13<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t<BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyymmdd")<BR>
     * @@roseuid 4110C7E902F5
     */
    private void createColumnHeader()
    {
        /*by db layout->csv file layout->�ڋq�f�[�^�A�b�v���[�h�t�@@�C���d�l.xls, the code are not fit to javaDoc*/
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME );

        final int COLUMN_MAX = 15;

        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //index 0
        l_models[0] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LABEL,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 1
        l_models[1] = new WEB3GentradeCsvColumnModel(
        WEB3AdminSrvRegiAccountDataUploadCsv.REGIST_ID_LABEL,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 2
        l_models[2] = new WEB3GentradeCsvColumnModel(
        WEB3AdminSrvRegiAccountDataUploadCsv.INSTITUTION_CODE_LABEL,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 3
        l_models[3] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.BRANCH_CODE_LABEL,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 4
        l_models[4] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.SRV_DIV_LABEL,
            4,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 5
        l_models[5] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.ACCOUNT_CODE_LABEL,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 6
        l_models[6] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.ACCOUNT_NAME_LABEL,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 7
        l_models[7] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_LABEL,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

		//��Q�Ή� NO_2140
        //index 8
        DateFormat l_dateFormat8 = new SimpleDateFormat("yyyyMMdd");
        l_dateFormat8.setLenient(false);
        
        l_models[8] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_DATE_LABEL,
            8,
            WEB3GentradeCsvColumnModel.DATETYPE,
			l_dateFormat8);
		
		//��Q�Ή� NO_2140
        //index 9
		DateFormat l_dateFormat9 = new SimpleDateFormat("yyyyMMdd");
		l_dateFormat9.setLenient(false);
		
        l_models[9] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_START_DATE_LABEL,
            9,
            WEB3GentradeCsvColumnModel.DATETYPE,
			l_dateFormat9);

		//��Q�Ή� NO_2140
        //index 10
		DateFormat l_dateFormat10 = new SimpleDateFormat("yyyyMMdd");
		l_dateFormat10.setLenient(false);
		
        l_models[10] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_END_DATE_LABEL,
            10,
            WEB3GentradeCsvColumnModel.DATETYPE,
			l_dateFormat10);

        //index 11
        l_models[11] = new WEB3GentradeCsvColumnModel(
        WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_DIV_LABEL,
            11,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 12
        l_models[12] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.USE_AMT_LABEL,
            12,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);

        //index 13
        l_models[13] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_POWER_LABEL,
            13,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);

		//��Q�Ή� NO_2140
        //index 14
		DateFormat l_dateFormat14 = new SimpleDateFormat("yyyyMMdd");
		l_dateFormat14.setLenient(false);
		
        l_models[14] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_DATE_LABEL,
            14,
            WEB3GentradeCsvColumnModel.DATETYPE,
			l_dateFormat14);

        this.setColumnHeader(l_models);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (validate�L�[�w�b�_)<BR>
     * �L�[�w�b�_������̑Ó������`�F�b�N���A���e���v���p�e�B�ɃZ�b�g����B <BR>
     * <BR>
     * �P�j�@@�L�[�w�b�_�w�蕶������ <BR>
     * �@@�L�[�w�b�_.split(�ڋq�f�[�^�A�b�v���[�hCSV.��؂蕶��)�ɂāA<BR>
     * ��؂蕶�����Ƃ�token[]�ɕ�������B <BR>
     * �@@token�̗v�f����15�łȂ��ꍇ�itoken.length != 15�j�A�w�b�_��<BR>
     * �t�H�[�}�b�g���Ⴄ�Ɣ��肵��O���X���[����B �i�t�H�[�}�b�g�G���[�j<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00522<BR>
     * <BR>
     * �Q�j�@@�L�[�w�b�_�Z�b�g <BR>
     * �@@this.set�L�[�w�b�_()�ɂăL�[�w�b�_���Z�b�g����B <BR>
     * <BR>
     * �@@[set�L�[�w�b�_()�Ɏw�肷�����] <BR>
     * �@@�L�[�w�b�_�F�@@token[] <BR>
     * @@param l_strKeyHeaderLine - (�L�[�w�b�_�s)<BR>
     * @@roseuid 41187FE5000D
     */
    public void validateKeyHeader(String l_strKeyHeaderLine)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateKeyHeader(String )";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�L�[�w�b�_�w�蕶������
        String[] l_strKeyHeaders = l_strKeyHeaderLine.split(WEB3GentradeCsvDataModel.regex);
        if (l_strKeyHeaders.length != 15)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00522,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�L�[�w�b�_�̃t�H�[�}�b�g�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //�Q�j�@@�L�[�w�b�_�Z�b�g <BR>
        this.setKeyHeader(l_strKeyHeaders);

        log.exiting(STR_METHOD_NAME);
    }

	/**
	 * �ivalidate���͕K�{���ځj<BR>
	 * �P�j�s�ԍ��ɑΉ����閾�׍s�̓��͕K�{���ڂ��`�F�b�N����B<BR>
	 * <BR>
	 * �P�[�P�j�A�b�v���[�h�敪�̃`�F�b�N<BR>
	 * �@@this.get���ڒl()�ɂĎ擾�����A�b�v���[�h�敪==null�̏ꍇ�A��O���X���[����B<BR>
	 *         class: WEB3BusinessLayerException<BR>
	 *         tag:   BUSINESS_ERROR_01020<BR>
	 * <BR>
     * �P�[�Q�j�،���ЃR�[�h�̃`�F�b�N<BR>
     * �@@this.get���ڒl()�ɂĎ擾�����،���ЃR�[�h==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00827<BR>
     * <BR>
	 * �P�[�R�j���X�R�[�h�̃`�F�b�N<BR>
	 * �@@this.get���ڒl()�ɂĎ擾�������X�R�[�h==null�̏ꍇ�A��O���X���[����B<BR>
	 *         class: WEB3BusinessLayerException<BR>
	 *         tag:   BUSINESS_ERROR_00833<BR>
	 * <BR>
	 * �P�[�S�j�T�[�r�X�敪�̃`�F�b�N<BR>
	 * �@@this.get���ڒl()�ɂĎ擾�����T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B<BR>
	 *         class: WEB3BusinessLayerException<BR>
	 *         tag:   BUSINESS_ERROR_00758<BR>
	 * <BR>
	 * �@@�T�[�r�X�敪�̌�����2���̐����łȂ��ꍇ�A��O���X���[����B<BR>
	 *   class: WEB3BusinessLayerException<BR>                    
	 *   tag:   BUSINESS_ERROR_00831<BR>                          
	 * <BR>                                                             
	 * �P�[�T�j�ڋq�R�[�h�̃`�F�b�N<BR>
	 * �@@this.get���ڒl()�ɂĎ擾�����ڋq�R�[�h==null�̏ꍇ�A��O���X���[����B<BR>
	 *         class: WEB3BusinessLayerException<BR>
	 *         tag:   BUSINESS_ERROR_00835<BR>
	 * <BR>
     * �P�|�U�j�\�����I�敪�̃`�F�b�N<BR>
     * �@@this.get���ڒl()�ɂĎ擾�����\�����I�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01905<BR>
     * <BR>
     * <BR>
     * �Q�jthis.get���ڒl()�ɂĎ擾�����A�b�v���[�h�敪 != '3'(�\������) �̏ꍇ�A<BR>
     * �ȉ��̏������s���B<BR>
     * <BR>
     * �Q�|�P�j�\���o�^ID�̃`�F�b�N
     * �@@this.get���ڒl()�ɂĎ擾�����A�b�v���[�h�敪���g�V�K�o�^�h &&<BR>
     *   this.get���ڒl()�ɂĎ擾�����\���o�^ID==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00832<BR>
     * <BR>
     * �Q�[�Q�j�o�^�敪�̃`�F�b�N<BR>
     *   �A�b�v���[�h�敪�����I�A�b�v���[�h�̏ꍇ &&<BR>
     * �@@this.get���ڒl()�ɂĎ擾�����\���o�^ID==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00841<BR>
     * <BR>
     * �Q�|�R�j���p�����̃`�F�b�N<BR>
     *   this.get���ڒl()�ɂĎ擾�����o�^�敪=�g�L���h &&<BR>
     * �@@this.get���ڒl()�ɂĎ擾�������p����==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01030<BR>
     * <BR>
	 * @@param l_intLineNumber - (�s�ԍ�)<BR>
	 * �s�ԍ����w�肷��B<BR>
	 */
    public void validateDispensableItem(int l_intLineNumber)
		throws WEB3BaseException
    {
		final String STR_METHOD_NAME = " validateDispensableItem(int )";
		log.entering(STR_METHOD_NAME);

		// �P�j�s�ԍ��ɑΉ����閾�׍s�̓��͕K�{���ڂ��`�F�b�N����B

        // �P�[�P�j�A�b�v���[�h�敪�̃`�F�b�N
        // this.get���ڒl()�ɂĎ擾�����A�b�v���[�h�敪==null�̏ꍇ�A��O���X���[����B
        if (this.getValue(l_intLineNumber, this.getColumnModel(UPLOAD_DIV_LABEL)) == null)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01020,
                this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�A�b�v���[�h�敪�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // �P�[�Q�j�،���ЃR�[�h�̃`�F�b�N
        // this.get���ڒl()�ɂĎ擾�����،���ЃR�[�h==null�̏ꍇ�A��O���X���[����B
		if (this.getValue(l_intLineNumber, this.getColumnModel(INSTITUTION_CODE_LABEL)) == null)
		{
			WEB3BaseException l_e =
				new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00827,
					this.getClass().getName() + STR_METHOD_NAME);
			log.debug("�،���ЃR�[�h�����̓G���[.", l_e);
			log.exiting(STR_METHOD_NAME);
			throw l_e;
		}

        // �P�[�R�j���X�R�[�h�̃`�F�b�N
        // this.get���ڒl()�ɂĎ擾�������X�R�[�h==null�̏ꍇ�A��O���X���[����B
		if (this.getValue(l_intLineNumber, this.getColumnModel(BRANCH_CODE_LABEL)) == null)
		{
			WEB3BaseException l_e =
				new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00833,
					this.getClass().getName() + STR_METHOD_NAME);
			log.debug("���X�R�[�h�����̓G���[.", l_e);
			log.exiting(STR_METHOD_NAME);
			throw l_e;
		}

        // �P�[�S�j�T�[�r�X�敪�̃`�F�b�N
        // this.get���ڒl()�ɂĎ擾�����T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B
		if (this.getValue(l_intLineNumber, this.getColumnModel(SRV_DIV_LABEL)) == null)
		{
			WEB3BaseException l_e =
				new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00758,
					this.getClass().getName() + STR_METHOD_NAME);
			log.debug("�T�[�r�X�敪�����̓G���[.", l_e);
			log.exiting(STR_METHOD_NAME);
			throw l_e;
		}

        //�T�[�r�X�敪�̌�����2���̐����łȂ��ꍇ�A��O���X���[����                       
 		String l_strSrvDiv = this.getValue(l_intLineNumber, this.getColumnModel(SRV_DIV_LABEL)).toString();                                                                               
        if (l_strSrvDiv.length() != 2                                                     
             || !WEB3StringTypeUtility.isInteger(l_strSrvDiv))                             
        {                                                                                 
        	WEB3BaseException l_e =                                                       
                new WEB3BusinessLayerException(                                           
                    WEB3ErrorCatalog.BUSINESS_ERROR_00831,                                
                    this.getClass().getName() + STR_METHOD_NAME);                         
            log.debug("�T�[�r�X�敪�G���[.", l_e);                                        
            log.exiting(STR_METHOD_NAME);                                                 
            throw l_e;                                                                    
        }                                                                                 

		// �P�[�T�j�ڋq�R�[�h�̃`�F�b�N
        // this.get���ڒl()�ɂĎ擾�����ڋq�R�[�h==null�̏ꍇ�A��O���X���[����B
		if (this.getValue(l_intLineNumber, this.getColumnModel(ACCOUNT_CODE_LABEL)) == null)
		{
			WEB3BaseException l_e =
				new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00835,
					this.getClass().getName() + STR_METHOD_NAME);
			log.debug("�ڋq�R�[�h�����̓G���[.", l_e);
			log.exiting(STR_METHOD_NAME);
			throw l_e;
		}

        // �P�|�U�j�\�����I�敪�̃`�F�b�N
        // this.get���ڒl()�ɂĎ擾�����\�����I�敪==null�̏ꍇ�A��O���X���[����B
        if (this.getValue(l_intLineNumber, this.getColumnModel(APPLI_LOT_DIV_LABEL)) == null)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01905,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�\�����I�敪���w��G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // �Q�jthis.get���ڒl()�ɂĎ擾�����A�b�v���[�h�敪 != '3'(�\������) �̏ꍇ
        if (!APPLI_ATTRIBUTE_LABEL.equals(this.getValue(l_intLineNumber, this.getColumnModel(UPLOAD_DIV_LABEL))))
        {

            // �Q�|�P�j�\���o�^ID�̃`�F�b�N
            // this.get���ڒl()�ɂĎ擾�����A�b�v���[�h�敪���g�V�K�o�^�h &&
            // this.get���ڒl()�ɂĎ擾�����\���o�^ID==null�̏ꍇ�A��O���X���[����B
            if (!UPLOAD_DIV_NEW_REGIST_LABEL.equals(this.getValue(l_intLineNumber,
                this.getColumnModel(UPLOAD_DIV_LABEL)))
                && this.getValue(l_intLineNumber, this.getColumnModel(REGIST_ID_LABEL)) == null)
            {
                WEB3BaseException l_e =
                    new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00832,
                    this.getClass().getName() + STR_METHOD_NAME);
                log.debug("�\���o�^ID���w��G���[.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }

            // �Q�[�Q�j�o�^�敪�̃`�F�b�N
            // �A�b�v���[�h�敪�����I�A�b�v���[�h�̏ꍇ�A
            // this.get���ڒl()�ɂĎ擾�����o�^�敪==null�̏ꍇ�A��O���X���[����B
            if (!UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL.equals(this.getValue(l_intLineNumber,
                this.getColumnModel(UPLOAD_DIV_LABEL)))
                && this.getValue(l_intLineNumber, this.getColumnModel(PAYMENT_DIV_LABEL)) == null)
            {
                WEB3BaseException l_e =
                    new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00841,
                    this.getClass().getName() + STR_METHOD_NAME);
                log.debug("�o�^�敪�G���[.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }

            // �Q�|�R�j���p�����̃`�F�b�N
            // this.get���ڒl()�ɂĎ擾�����o�^�敪=�g�L���h &&
            // this.get���ڒl()�ɂĎ擾�������p����==null�̏ꍇ�A��O���X���[����B
            if ((PAYMENT_DIV_CHARGE_LABEL.equals(
                this.getValue(l_intLineNumber, this.getColumnModel(PAYMENT_DIV_LABEL))))
                && (this.getValue(l_intLineNumber, this.getColumnModel(USE_AMT_LABEL)) == null))
            {
                WEB3BaseException l_e =
                    new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01030,
                    this.getClass().getName() + STR_METHOD_NAME);
                log.debug("���p���������̓G���[.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
        }
		log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ivalidate�����o�^���t�ݒ�j<BR>
     * �K�p�J�n���A�K�p�I�����̐ݒ�l�Ƒ��݊֘A�̃`�F�b�N������B<BR>
     * �P�j ���t�̑��֊֌W�`�F�b�N  <BR>
     *   �P�|�P�j�K�p�J�n�� != null && �K�p�I���� != null �̏ꍇ<BR>
     *     TO_CHAR(�K�p�J�n��, 'YYYYMMDD')�@@>�@@�K�p�I�����@@�̏ꍇ�͗�O���X���[����B<BR>
     *     �i�K�p���s�K�؃G���[�j <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00839<BR>
     * @@param l_datAppliyStartDate - (�K�p�J�n��)<BR>
     * ���׍s�̓K�p�J�n��<BR>
     * @@param l_datAppliyEndDate - (�K�p�I����)<BR>
     * ���׍s�̓K�p�I����<BR>
     * @@throws WEB3BaseException
     */
    public void validatePaymentFreeRegiDate(Date l_datAppliyStartDate, Date l_datAppliyEndDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePaymentFreeRegiDate(Date,Date)";
        log.entering(STR_METHOD_NAME);

        // �P�|�P�j�K�p�J�n�� != null && �K�p�I���� != null �̏ꍇ
        if ((l_datAppliyStartDate != null) && (l_datAppliyEndDate != null))
        {
            // TO_CHAR(�K�p�J�n��, 'YYYYMMDD')�@@>�@@�K�p�I�����@@�̏ꍇ�͗�O���X���[����B
            if (WEB3DateUtility.compareToDay(l_datAppliyStartDate, l_datAppliyEndDate) > 0)
            {
                log.debug("�K�p���s�K�؃G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00839,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (check���׍s��)<BR>
     * �A�b�v���[�h�������u���X�p�v���t�@@�����X�e�[�u���v<BR>
     * �ɓo�^����Ă���A�b�v���[�h�\�����𒴂��Ă��邩�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j �ȉ��̏����Łu�،���Ѓv���t�@@�����X�e�[�u���v����������B<BR>
     * [��������] <BR>
     * �،���Ђh�c  = ����.�،���Ђh�c and<BR>
     * �v���t�@@�����X�� = srvregi.account.upload.maxcount(�T�[�r�X���p�F�ڋq�A�b�v���[�h�\����) and<BR>
     * �v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �����R�[�h���擾�ł��Ȃ������ꍇ�A�ȉ��̏������s�킸�I������B<BR>
     * <BR>
     * �Q�j ���׍s�����`�F�b�N����B<BR>
     * �،���Ѓv���t�@@�����X�e�[�u��.�v���t�@@�����X�̒l(���l�^�ɕϊ�) < ����.���׍s���̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * �@@�@@class�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@:�@@BUSINESS_ERROR_02418<BR>
     * @@param l_lngInstitutionId - (�،����ID)<BR>
     * ���XID<BR>
     * @@param l_intDetailLine - (���׍s��)<BR>
     * ���׍s��<BR>
     * @@throws WEB3BaseException
     */
    public void checkDetailLines(long l_lngInstitutionId, int l_intDetailLine) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "checkDetailLines(long, int)";
        log.entering(STR_METHOD_NAME);

        //�P�j �ȉ��̏����Łu�،���Ѓv���t�@@�����X�e�[�u���v����������B
        //�،���Ђh�c  = ����.�،���Ђh�c and
        //�v���t�@@�����X�� = srvregi.account.upload.maxcount(�T�[�r�X���p�F�ڋq�A�b�v���[�h�\����) and
        //�v���t�@@�����X���̘A�� = 1
       InstitutionPreferencesPK l_institutionPreferencesPK =
            new InstitutionPreferencesPK(
                l_lngInstitutionId,
                WEB3InstitutionPreferencesNameDef.SRVREGI_ACCOUNT_UPLOAD_MAXCOUNT,
                1);

        InstitutionPreferencesRow l_institutionPreferencesRow = null;
        try
        {
            l_institutionPreferencesRow =
                InstitutionPreferencesDao.findRowByPk(l_institutionPreferencesPK);
        }
        catch (DataFindException l_ex)
        {
            //�����R�[�h���擾�ł��Ȃ������ꍇ�A�ȉ��̏������s�킸�I������B
            return;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j ���׍s�����`�F�b�N����B
        //�،���Ѓv���t�@@�����X�e�[�u��.�v���t�@@�����X�̒l(���l�^�ɕϊ�) < ����.���׍s���̏ꍇ�A��O���X���[����B
        int l_intValue =
            Integer.parseInt(l_institutionPreferencesRow.getValue());
        if (l_intValue < l_intDetailLine)
        {
            WEB3BaseException l_baseException =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02418,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���R�[�h�������������E�l�𒴂��Ă��܂��B");
            log.debug("���R�[�h�������������E�l�𒴂��Ă��܂��B", l_baseException);
            log.exiting(STR_METHOD_NAME);
            throw l_baseException;
        }
    }
}
@
