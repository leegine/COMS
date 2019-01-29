head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.26.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioCashoutInqDownloadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name�@@�@@�@@�@@�@@: �o���\���⍇���_�E�����[�hCSV (WEB3AdminAioCashoutInqDownloadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/28 ꎉ� (���u) �V�K�쐬      
*/

package webbroker3.aio;

import java.text.SimpleDateFormat;
import java.util.Date;

import webbroker3.aio.define.WEB3AioCancelDivDef;
import webbroker3.aio.define.WEB3AioOrderAcceptedDivDef;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FinSaveDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

/**
 * (�o���\���⍇���_�E�����[�hCSV)<BR>
 * �o�������t�@@�C���_�E�����[�h�ō쐬����CSV�t�@@�C���f�[�^�N���X 
 *
 * @@author ꎉ� (���u)
 * @@version 1.0
 */

public class WEB3AdminAioCashoutInqDownloadCsv extends WEB3GentradeCsvDataModel
{

    /**
     * ���O�o�̓I�u�W�F�N�g�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioVirtualAccCashinULCsv.class);
    
    /**
     * (���X���x��)<BR>
     * ���X���x��<BR>
     */
    public static final String BRANCH_LABEL = "���X";
    
    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �ڋq�R�[�h���x��<BR>
     */
    public static final String ACCOUNT_CODE_LABEL = "�ڋq�R�[�h";

    /**
     * (�ڋq�����x��)<BR>
     * �ڋq�����x��<BR>
     */
    public static final String ACCOUNT_NAME_LABEL = "�ڋq��";
    
    /**
     * (�����������x��)<BR>
     * �����������x��<BR>
     */
    public static final String ORDER_DATE_LABEL = "��������";
    
    /**
     * (��n�����x��)<BR>
     * ��n�����x��<BR>
     */
    public static final String DELIVERY_DATE_LABEL = "��n��";

    /**
     * (�o���z���x��)<BR>
     * �o���z���x��<BR>
     */
    public static final String CASHINGOUT_AMOUNT_LABEL = "�o���z";
    
    /**
     * (������ԃ��x��)<BR>
     * ������ԃ��x��<BR>
     */
    public static final String ORDER_STATUS_LABEL = "�������";
    
    /**
     * (����敪���x��)<BR>
     * ����敪���x��<BR>
     */
    public static final String CANCEL_DIV_LABEL = "����敪";
    
    /**
     * (����������x��)<BR>
     * ����������x��<BR>
     */
    public static final String CANCEL_DATE_LABEL = "�������";
    
    /**
     * (�U�����s�R�[�h���x��)<BR>
     * �U�����s�R�[�h���x��<BR>
     */
    public static final String TRANSFER_BANK_CODE_LABEL = "�U�����s�R�[�h";
    
    /**
     * (�U����x�X�R�[�h���x�� )<BR>
     * �U����x�X�R�[�h���x��<BR>
     */
    public static final String TRANSFER_BRANCH_CODE_LABEL = "�U����x�X�R�[�h";
    
    /**
     * (�a���敪���x��)<BR>
     * �a���敪���x��<BR>
     */
    public static final String DEPOSIT_DIV_LABEL = "�a���敪";
    
    /**
     * (�U��������ԍ����x��)<BR>
     * �U��������ԍ����x��<BR>
     */
    public static final String TRANSFER_ACCOUNT_NUMBER_LABEL = "�U��������ԍ�";
    
    /**
     * (�U����������`�l���x��)<BR>
     * �U����������`�l���x��<BR>
     */
    public static final String TRANSFER_ACCOUNT_NAME_LABEL = "�U����������`�l";
    
    /**
     * (�o���\�z�i�����j���x��)<BR>
     * �o���\�z�i�����j���x��<BR>
     */
    public static final String CASHOUT_POSSIBLE_PRICE_TODAY_LABEL = "�o���\�z�i�����j";
    
    /**
     * (�o���\�z�i1�c�Ɠ���j���x��)<BR>
     * �o���\�z�i1�c�Ɠ���j���x��<BR>
     */
    public static final String CASHOUT_POSSIBLE_PRICE_1DAYAFTER_LABEL = "�o���\�z�i�P�c�Ɠ���j";
    
    /**
     * (�o���\�z�i2�c�Ɠ���j���x��)<BR>
     * �o���\�z�i2�c�Ɠ���j���x��<BR>
     */
    public static final String CASHOUT_POSSIBLE_PRICE_2DAYSAFTER_LABEL = "�o���\�z�i2�c�Ɠ���j";
    
    /**
     * (�o���\�z�i3�c�Ɠ���j���x��)<BR>
     * �o���\�z�i3�c�Ɠ���j���x��<BR>
     */
    public static  final String CASHOUT_POSSIBLE_PRICE_3DAYSAFTER_LABEL = "�o���\�z�i3�c�Ɠ���j";
    
    /**
     * (�o���\�z�i4�c�Ɠ���j���x��)<BR>
     * �o���\�z�i4�c�Ɠ���j���x��<BR>
     */
    public static final String CASHOUT_POSSIBLE_PRICE_4DAYSAFTER_LABEL = "�o���\�z�i4�c�Ɠ���j";
    
    /**
     * (�o���\�z�i5�c�Ɠ���j���x��)<BR>
     * �o���\�z�i5�c�Ɠ���j���x��<BR>
     */
    public static final String CASHOUT_POSSIBLE_PRICE_5DAYSAFTER_LABEL = "�o���\�z�i5�c�Ɠ���j";
    
    /**
     * (�������_��t���ϕ\��)<BR>
     * �萔��`�v���p�e�B�@@�������_��t���ϕ\��<BR>
     */
    public static final String ORDER_STATUS_NOT_ACCEPTED = "��t����";
    
    /**
     * (�������_��t�ϕ\��)<BR>
     * �萔��`�v���p�e�B�@@�������_��t�ϕ\��<BR>
     */
    public static final String ORDER_STATUS_ACCEPTED= "��t��";
    
    /**
     * (�������_��t�G���[�\��)<BR>
     * �萔��`�v���p�e�B�@@�������_��t�G���[�\��<BR>
     */
    public static final String ORDER_STATUS_ACCEPTED_ERROR = "��t�G���[";
    
    /**
     * (�������_��t���\��)<BR>
     * �萔��`�v���p�e�B�@@�������_��t���\��<BR>
     */
    public static final String ORDER_STATUS_ACCEPTING = "��t��";
    
    /**
     * (����敪_����ϕ\��)<BR>
     * �萔��`�v���p�e�B�@@����敪_����ϕ\��<BR>
     */
    public static final String CANCEL_DIV_CANCELED = "�����";
    
    /**
     * (�a���敪_���ʗa���\��)<BR>
     * �萔��`�v���p�e�B�@@�a���敪_���ʗa���\��<BR>
     */
    public static final String DEPOSIT_DIV_GENERAL = "���ʗa��";
    
    /**
     * (�a���敪_�����a���\��)<BR>
     * �萔��`�v���p�e�B�@@�a���敪_�����a���\��<BR>
     */
    public static final String DEPOSIT_DIV_ACCOUNT = "�����a��";
    
    /**
     * (�a���敪_���~�a���\��)<BR>
     * �萔��`�v���p�e�B�@@�a���敪_���~�a���\��<BR>
     */
    public static final String DEPOSIT_DIV_SAVING = "���~�a��";
    
    /**
     * (�a���敪_���̑��\��)<BR>
     * �萔��`�v���p�e�B�@@�a���敪_���̑��\��<BR>
     */
    public static final String DEPOSIT_DIV_OTHER = "���̑�";
    
    /**
     * (�o���\���⍇�킹�_�E�����[�hCSV )<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B <BR>
     * �@@�|create�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B <BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B <BR>
     * @@roseuid 4456FB6A02CD
     */
    public WEB3AdminAioCashoutInqDownloadCsv () 
    {
        //super()�ɂăC���X�^���X�𐶐�����B
        super();
        
        //create�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B 
        this.createKeyHeader();
        
        //create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B  
        this.createColumnHeader();

    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * �ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����A<BR>
     * set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * �ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B <BR>
     * <BR>
     * [�J�����w�b�_�z��] <BR>
     * �|�@@index = 0 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.���X���x��  <BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 1 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�ڋq�R�[�h���x��  <BR>
     * �@@�J�����ԍ��F 1 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�ڋq�����x��  <BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�����������x��  <BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t����  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")  <BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.��n�����x�� <BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F CSV�J�������f��.���ڌ^_���t <BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyy/MM/dd") <BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���z���x�� <BR>
     * �@@�J�����ԍ��F 5 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 6 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.������ԃ��x��  <BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.����敪���x�� <BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 8<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.����������x��<BR>
     * �@@�J�����ԍ��F 8<BR>
     * �@@���ڌ^�FCSV�J�������f��.���ڌ^_���t����<BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")<BR>
     * <BR>
     * �|�@@index = 9<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�U�����s�R�[�h���x��  <BR>
     * �@@�J�����ԍ��F 9<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 10<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�U����x�X�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 10<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 11<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�a���敪���x��  <BR>
     * �@@�J�����ԍ��F 11<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 12<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�U��������ԍ����x�� <BR>
     * �@@�J�����ԍ��F 12<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 13<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�U����������`�l���x��  <BR>
     * �@@�J�����ԍ��F 13<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 14<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���\�z�i�����j���x��  <BR>
     * �@@�J�����ԍ��F 14<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 15<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���\�z�i�P�c�Ɠ���j���x��   <BR>
     * �@@�J�����ԍ��F 15<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 16<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���\�z�i2�c�Ɠ���j���x��   <BR>
     * �@@�J�����ԍ��F 16<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 16<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���\�z�i2�c�Ɠ���j���x��   <BR>
     * �@@�J�����ԍ��F 16<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 17<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���\�z�i3�c�Ɠ���j���x��   <BR>
     * �@@�J�����ԍ��F 17<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 18<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���\�z�i4�c�Ɠ���j���x��   <BR>
     * �@@�J�����ԍ��F 18<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 19<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���\�z�i5�c�Ɠ���j���x��   <BR>
     * �@@�J�����ԍ��F 19<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * @@roseuid 4110C7E902F5
     */
    private void createColumnHeader()
    {

        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME );

        final int COLUMN_MAX = 20;

        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //index 0���X���x�� 
        l_models[0] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.BRANCH_LABEL,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 1�ڋq�R�[�h���x�� 
        l_models[1] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.ACCOUNT_CODE_LABEL,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 2�ڋq�����x�� 
        l_models[2] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.ACCOUNT_NAME_LABEL,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 3�����������x��	
        l_models[3] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.ORDER_DATE_LABEL	,
            3,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));

        //index 4��n�����x�� 
        l_models[4] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.DELIVERY_DATE_LABEL,
            4,
            WEB3GentradeCsvColumnModel.DATETYPE,
            new SimpleDateFormat("yyyy/MM/dd"));

        //index 5�o���z���x��
        l_models[5] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHINGOUT_AMOUNT_LABEL,
            5,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);

        //index 6������ԃ��x�� 
        l_models[6] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_LABEL,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 7����敪���x�� 
        l_models[7] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CANCEL_DIV_LABEL,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 8����������x��      
        l_models[8] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CANCEL_DATE_LABEL,
            8,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));

        //index 9�U�����s�R�[�h���x��
        l_models[9] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_BANK_CODE_LABEL,
            9,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 10 �U����x�X�R�[�h���x�� 
        l_models[10] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_BRANCH_CODE_LABEL,
            10,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 11�a���敪���x�� 
        l_models[11] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_LABEL,
            11,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 12�U��������ԍ����x��
        l_models[12] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_ACCOUNT_NUMBER_LABEL,
            12,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 13 �U����������`�l���x�� 
        l_models[13] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_ACCOUNT_NAME_LABEL,
            13,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 14 �o���\�z�i�����j���x�� 
        l_models[14] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_TODAY_LABEL,
            14,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
        
        //index 15 �o���\�z�i�P�c�Ɠ���j���x��
        l_models[15] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_1DAYAFTER_LABEL,
            15,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
        
        //index 16 �o���\�z�i2�c�Ɠ���j���x��
        l_models[16] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_2DAYSAFTER_LABEL,
            16,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
        
        //index 17 �o���\�z�i3�c�Ɠ���j���x��
        l_models[17] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_3DAYSAFTER_LABEL,
            17,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
        
        //index 18 �o���\�z�i4�c�Ɠ���j���x��
        l_models[18] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_4DAYSAFTER_LABEL,
            18,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
        
        //index 19 �o���\�z�i5�c�Ɠ���j���x��
        l_models[19] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_5DAYSAFTER_LABEL,
            19,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);

        this.setColumnHeader(l_models);

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (create�L�[�w�b�_)<BR>
     * �L�[�w�b�_���Z�b�g����B<BR>
     * �@@�ȉ��̒ʂ蕶����̔z��𐶐����A<BR>
     * �@@set�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�L�[�w�b�_�z��]    <BR>
     * <BR>
     * �|�@@index = 0   <BR>
     * �@@���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B<BR>
     * <BR>
     * (*1) ���ݓ��� <BR>
     * TradingSystem.getSystemTimestamp()   <BR>
     * @@roseuid 43D5DF890264
     */
    private void createKeyHeader() 
    {
        final String STR_METHOD_NAME = "createKeyHeader()";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strKeyHeaders = new String[1];
        
        //���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B   
        l_strKeyHeaders[0] = WEB3DateUtility.formatDate(
            GtlUtils.getSystemTimestamp(), "yyyy/MM/dd HH:mm:ss");
        
        //set�L�[�w�b�_
        this.setKeyHeader(l_strKeyHeaders);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set���X)<BR>
     * ���X�R�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.���X�R�[�h���x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@���X�h�c�ɊY�����镔�X�I�u�W�F�N�g.getBranchCode() <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_strBranchId - (���XID)<BR>
     *              ���XID
     * @@roseuid 43D5DF890264
     */
    public void setBranch(int l_intLineNumber, String l_strBranchId) 
    {
        final String STR_METHOD_NAME = "setBranch(int,string)";
        log.entering(STR_METHOD_NAME);
        Long l_lngBranchId = new Long(l_strBranchId);
        AccountManager l_accountManager = GtlUtils.getAccountManager();   
        try
		{
	        Branch l_branch = l_accountManager.getBranch(l_lngBranchId.longValue());
		    String l_strBranchCode = l_branch.getBranchCode();
		    //�P�j�J�������f���擾
		    //�Q�j�l�Z�b�g
		    this.setValue(l_intLineNumber, this.getColumnModel(
		        WEB3AdminAioCashoutInqDownloadCsv.BRANCH_LABEL), l_strBranchCode);
		}
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
	            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
	            this.getClass().getName() + "." + STR_METHOD_NAME,
	            l_ex.getMessage(),
	            l_ex);
        }
       log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�ڋq)<BR>
     *<BR>
     *�P�j�ڋq�I�u�W�F�N�g�擾 <BR>
     * �@@�����h�c�ɊY������ڋq�I�u�W�F�N�g���擾����B<BR>
     *<BR>
     *�Q�j�i�ڋq�R�[�h�j�J�������f���擾  <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     *<BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]   <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�ڋq�R�[�h���x�� <BR>
     * �R�j�i�ڋq�R�[�h�j�l�Z�b�g  <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     *<BR>   
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�J�����F�@@�Q�j�Ŏ擾�����J�������f��<BR>  
     * �@@�l�F�@@�ڋq.getAccountCode()�̍�6byte<BR>
     *<BR>
     * �S�j�i�ڋq���j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     *<BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�ڋq�����x��   <BR>
     *<BR>
     * �T�j�i�ڋq���j�l�Z�b�g  <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     *<BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�S�j�Ŏ擾�����J�������f��  <BR>
     * �@@�l�F�@@�ڋq.get�ڋq�\����()  <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_lngAccountId - (����ID)<BR>
     *              ����ID<BR>
     * @@roseuid 43D5DF890264
     */
    public void setAccount(int l_intLineNumber, long l_lngAccountId) 
    {
        final String STR_METHOD_NAME = "setAccount(int,long)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�ڋq�I�u�W�F�N�g�擾  
        // �����h�c�ɊY������ڋq�I�u�W�F�N�g���擾����B  
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountMgr = l_finApp.getAccountManager();      
        WEB3GentradeMainAccount l_mainAccount = null;
        
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // [set���ڒl()�Ɏw�肷�����]  
        // �s�ԍ��F�@@�����̍s�ԍ�  
        // �J�����F�@@�Q�j�Ŏ擾�����J�������f��  
        // �l�F�@@�ڋq.getAccountCode()�̍�6byte 
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.ACCOUNT_CODE_LABEL), 
            l_mainAccount.getAccountCode().substring(0, 6));
        
        //�@@[set���ڒl()�Ɏw�肷�����]  
        //�@@�s�ԍ��F�@@�����̍s�ԍ�  
        //�@@�J�����F�@@�S�j�Ŏ擾�����J�������f��  
        //�@@�l�F�@@�ڋq.get�ڋq�\����()  
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.ACCOUNT_NAME_LABEL),
            l_mainAccount.getDisplayAccountName());
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set��������)<BR>
     * �����������Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�����������x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *   [����] <BR>
     *   �s�ԍ��F �i�����j�s�ԍ�  <BR>
     *   �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *   �l�F �i�����j�󒍓��� <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_datReceivedDate - (�󒍓���)<BR>
     *              �󒍓���
     * @@roseuid 43D5DF890264
     */
    public void setOrderDate(int l_intLineNumber, Date l_datReceivedDate) 
    {
        final String STR_METHOD_NAME = "setOrderDate(int,date)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.ORDER_DATE_LABEL), l_datReceivedDate);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set��n��)<BR>
     * ��n�����Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.��n�����x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *   [����] <BR>
     *   �s�ԍ��F �i�����j�s�ԍ�  <BR>
     *   �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *   �l�F �i�����j��n�� <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     *            �@@��n��
     * @@roseuid 43D5DF890264
     */
    public void setDeliveryDate(int l_intLineNumber, Date l_datDeliveryDate) 
    {
        final String STR_METHOD_NAME = "setDeliveryDate(int,date)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.DELIVERY_DATE_LABEL), l_datDeliveryDate);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�o���z)<BR>
     * �o���z���Z�b�g����B<BR>
     * <BR>
     * �@@�P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���z���x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�s�ԍ��F �i�����j�s�ԍ�  <BR>
     * �@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F �i�����j�������ʂ̏����_�ȉ���؂�̂Ă��l <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_dblOrderQuantity - (��������)<BR>
     *              ��������
     * @@roseuid 43D5DF890264
     */
    public void setCashoutAmount(int l_intLineNumber, double l_dblOrderQuantity) 
    {
        final String STR_METHOD_NAME = "setCashoutAmount(int,double)";
        log.entering(STR_METHOD_NAME);
        long l_lngTemp =  (long) Math.floor(l_dblOrderQuantity);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHINGOUT_AMOUNT_LABEL), new Long(l_lngTemp));
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�������)<BR>
     * ������Ԃ��Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *   [����]   <BR>
     * �@@���ڃ��x���F �o���\���⍇���_�E�����[�hCSV.������ԃ��x��   <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����] <BR>
     *   �s�ԍ��F �i�����j�s�ԍ�  <BR>
     *   �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *   �l�F  <BR>
     *   �i����:������t��� == "��t����"�j�̏ꍇ  <BR>
     *   �|�o���\���⍇���_�E�����[�hCSV.�������_��t���ϕ\���B<BR>
     *<BR>
     *   �i����:������t��� == "��t��"�j�̏ꍇ  <BR>
     *   �|�o���\���⍇���_�E�����[�hCSV.�������_��t�ϕ\���B<BR>
     *<BR>
     *   �i����:������t��� == "��t�G���["�j�̏ꍇ  <BR>
     *   �|�o���\���⍇���_�E�����[�hCSV.�������_��t�G���[�\���B<BR>
     *<BR>
     *   �i����:������t��� == "��t��"�j�̏ꍇ  <BR>
     *   �|�o���\���⍇���_�E�����[�hCSV.�������_��t���\���B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_strOrderStatus - (������t��� )<BR>
     *              ������t���<BR>
     *              0 �F ��t���� <BR>
     *              1 �F ��t�� <BR>
     *              2 �F ��t�G���[ <BR>
     *              4 �F ��t�� <BR>
     * @@roseuid 43D5DF890264
     */
    public void setOrderStatus(int l_intLineNumber, String l_strOrderStatus) 
    {
        final String STR_METHOD_NAME = "setOrderStatus(int,String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        // 0 �F ��t����
        if(WEB3AioOrderAcceptedDivDef.NOT_ACCEPTED.equals(l_strOrderStatus))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_NOT_ACCEPTED);
        }// 1 �F ��t��
        else if(WEB3AioOrderAcceptedDivDef.ACCEPTED.equals(l_strOrderStatus))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_ACCEPTED);
        }// 2 �F ��t�G���[
        else if(WEB3AioOrderAcceptedDivDef.ACCEPT_ERROR.equals(l_strOrderStatus))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_ACCEPTED_ERROR);
        }// 4 �F ��t��
        else if(WEB3AioOrderAcceptedDivDef.ACCEPTING.equals(l_strOrderStatus))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_ACCEPTING);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set����敪)<BR>
     * ����敪���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]   <BR>
     * �@@���ڃ��x���F �o���\���⍇���_�E�����[�hCSV.����敪���x��	   <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�s�ԍ��F �i�����j�s�ԍ�  <BR>
     * �@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F  <BR>
     * �@@�i����:����敪 == "�����"�j�̏ꍇ  <BR>
     * �@@�|�o���\���⍇���_�E�����[�hCSV.����敪_����ϕ\���B  <BR>
     *<BR>
     * �@@�i����:����敪 == "�����l"�j�̏ꍇ   <BR>
     * �@@�|null<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_strCancelDiv - (����敪)<BR>
     *              ����敪 <BR>
     *              2�F�����  <BR>
     *              0�F�����l <BR>
     * @@roseuid 43D5DF890264
     */
    public void setCancelDiv(int l_intLineNumber, String l_strCancelDiv) 
    {
        final String STR_METHOD_NAME = "setCancelDiv(int,String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        //2�F����� 
        if(WEB3AioCancelDivDef.CANCElED.equals(l_strCancelDiv))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.CANCEL_DIV_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.CANCEL_DIV_CANCELED);
        }//0�F�����l
        else if(WEB3AioCancelDivDef.INITIAL_VALUE.equals(l_strCancelDiv))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.CANCEL_DIV_LABEL), 
                null);
        }

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�������)<BR>
     * ����������Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *   [����]   <BR>
     *   ���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.����������x��   <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *   this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *   [����] <BR>
     *   �s�ԍ��F �i�����j�s�ԍ�  <BR>
     *   �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *   �l�F �i�����j������� <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_datCancelDate - (�������)<BR>
     *              �������<BR>
     * @@roseuid 43D5DF890264
     */
    public void setCancelDate(int l_intLineNumber, Date l_datCancelDate) 
    {
        final String STR_METHOD_NAME = "setCancelDate(int,Date)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CANCEL_DATE_LABEL),l_datCancelDate);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�U�����s�R�[�h )<BR>
     * �U�����s�R�[�h���Z�b�g����B <BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]   <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�U�����s�R�[�h���x��    <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�s�ԍ��F �i�����j�s�ԍ�  <BR>
     * �@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F �i�����j�U�����s�R�[�h <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_strTransferBankCode - (�U�����s�R�[�h)<BR>
     *              �U�����s�R�[�h<BR>
     * @@roseuid 43D5DF890264
     */
    public void setTransferBankCode(int l_intLineNumber, String l_strTransferBankCode) 
    {
        final String STR_METHOD_NAME = "setCansetTransferBankCodecelDate(int,String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_BANK_CODE_LABEL),
            l_strTransferBankCode);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�U����x�X�R�[�h )<BR>
     * �U����x�X�R�[�h���Z�b�g����B <BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *   [����]   <BR>
     *   ���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�U����x�X�R�[�h���x��    <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *   [����] <BR>
     *   �s�ԍ��F �i�����j�s�ԍ�  <BR>
     *   �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *   �l�F �i�����j�x�X�R�[�h <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_strTransferBranchCode - (�x�X�R�[�h)<BR>
     *              �x�X�R�[�h
     * @@roseuid 43D5DF890264
     */
    public void setTransferBranchCode(int l_intLineNumber, String l_strTransferBranchCode) 
    {
        final String STR_METHOD_NAME = "setTransferBranchCode(int,String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_BRANCH_CODE_LABEL),
            l_strTransferBranchCode);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�a���敪)<BR>
     * ������Ԃ��Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *   [����]   <BR>
     *   ���ڃ��x���F �o���\���⍇���_�E�����[�hCSV.�a���敪���x��   <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�s�ԍ��F �i�����j�s�ԍ�  <BR>
     * �@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F  <BR>
     * �@@�i����:�a���敪 == "���ʗa��"�j�̏ꍇ  <BR>
     * �@@�|�o���\���⍇���_�E�����[�hCSV.�a���敪_���ʗa���\���B <BR>
     *<BR>
     * �@@�i����:�a���敪 == "�����a��"�j�̏ꍇ  <BR>
     * �@@�|�o���\���⍇���_�E�����[�hCSV.�a���敪_�����a���\���B<BR>
     *<BR>
     * �@@�i����:�a���敪 == "���̑�"�j�̏ꍇ   <BR>
     * �@@�|�o���\���⍇���_�E�����[�hCSV.�a���敪_���̑��\���B <BR>
     *<BR>
     * �@@�i����:�a���敪 == "���~�a��"�j�̏ꍇ   <BR>
     * �@@�|�o���\���⍇���_�E�����[�hCSV.�a���敪_���~�a���\���B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_strDepositDiv - (�a���敪)<BR>
     *              �a���敪<BR>
     *              1�F ���ʗa��  <BR>
     *              2�F �����a�� <BR>
     *              3�F ���̑� <BR>
     *              4�F ���~�a�� <BR>
     * @@roseuid 43D5DF890264
     */
    public void setDepositDiv(int l_intLineNumber, String l_strDepositDiv) 
    {
        final String STR_METHOD_NAME = "setDepositDiv(int,String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        // 1�F ���ʗa�� 
        if(WEB3FinSaveDivDef.GENERAL_FIN_SAVE.equals(l_strDepositDiv))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_GENERAL);
        }//  2�F �����a�� 
        else if(WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE.equals(l_strDepositDiv))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_ACCOUNT);
        }// 3�F ���̑�
        else if(WEB3FinSaveDivDef.OTHER.equals(l_strDepositDiv))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_OTHER);
        }//  4�F ���~�a�� 
        else if(WEB3FinSaveDivDef.SAVING_FIN_SAVE.equals(l_strDepositDiv))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_SAVING);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�U��������ԍ� )<BR>
     * �U��������ԍ����Z�b�g����B <BR>
     * <BR>
     * �@@�P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *   [����]   <BR>
     *   ���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�U��������ԍ����x��   <BR>
     * <BR>
     * �@@�Q�j�l�Z�b�g<BR>
     *   this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *   [����] <BR>
     *   �s�ԍ��F �i�����j�s�ԍ�  <BR>
     *   �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *   �l�F �i�����j�����ԍ� <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_strAccountNumber - (�����ԍ�)<BR>
     *              �����ԍ�
     * @@roseuid 43D5DF890264
     */
    public void setTransferAccountNumber(int l_intLineNumber, String l_strAccountNumber) 
    {
        final String STR_METHOD_NAME = "setTransferAccountNumber(int,String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_ACCOUNT_NUMBER_LABEL),
            l_strAccountNumber);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�U����������`�l)<BR>
     * �U���於�`�l���Z�b�g����B <BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]   <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�U����������`�l���x�� 	   <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�s�ԍ��F �i�����j�s�ԍ�  <BR>
     * �@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F �i�����j�������`�l <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_strAccountName - (�������`�l)<BR>
     *              �������`�l
     * @@roseuid 43D5DF890264
     */
    public void setTransferAccountName(int l_intLineNumber, String l_strAccountName) 
    {
        final String STR_METHOD_NAME = "setTransferAccountName(int,String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_ACCOUNT_NAME_LABEL),
            l_strAccountName);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�o���\�z�i�����j)<BR>
     * �o���\�z�i�����j���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]   <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���\�z�i�����j���x��	 <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�s�ԍ��F �i�����j�s�ԍ�  <BR>
     * �@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F �i�����j�o���\�z�̏����_�ȉ���؂�̂Ă��l <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_dblCashoutPossiblePrice - (�o���\�z)<BR>
     *              �o���\�z
     * @@roseuid 43D5DF890264
     */
    public void setCashoutPossiblePriceToday(int l_intLineNumber, double l_dblCashoutPossiblePrice) 
    {
        final String STR_METHOD_NAME = "setCashoutPossiblePriceToday(int,double)";
        log.entering(STR_METHOD_NAME);
        long l_lngTemp =  (long) Math.floor(l_dblCashoutPossiblePrice);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_TODAY_LABEL), 
            new Long(l_lngTemp));
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�o���\�z�i�P�c�Ɠ��j)<BR>
     * �o���\�z�i�P�c�Ɠ��j���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]   <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���\�z�i�P�c�Ɠ��j���x��	 <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�s�ԍ��F �i�����j�s�ԍ�  <BR>
     * �@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F �i�����j�o���\�z�̏����_�ȉ���؂�̂Ă��l <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_dblCashoutPossiblePrice - (�o���\�z)<BR>
     *              �o���\�z
     * @@roseuid 43D5DF890264
     */
    public void setCashoutPossiblePrice1DayAfter(int l_intLineNumber, double l_dblCashoutPossiblePrice) 
    {
        final String STR_METHOD_NAME = "setCashoutPossiblePrice1DayAfter(int,double)";
        log.entering(STR_METHOD_NAME);
        long l_lngTemp =  (long) Math.floor(l_dblCashoutPossiblePrice);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_1DAYAFTER_LABEL), 
            new Long(l_lngTemp));
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�o���\�z�i2�c�Ɠ��j)<BR>
     * �o���\�z�i 2�c�Ɠ��j���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]   <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���\�z�i2�c�Ɠ��j���x��	 <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�s�ԍ��F �i�����j�s�ԍ�  <BR>
     * �@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F �i�����j�o���\�z�̏����_�ȉ���؂�̂Ă��l <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_dblCashoutPossiblePrice - (�o���\�z)<BR>
     *              �o���\�z
     * @@roseuid 43D5DF890264
     */
    public void setCashoutPossiblePrice2DaysAfter(int l_intLineNumber, double l_dblCashoutPossiblePrice) 
    {
        final String STR_METHOD_NAME = "setCashoutPossiblePrice2DaysAfter(int,double)";
        log.entering(STR_METHOD_NAME);
        long l_lngTemp =  (long) Math.floor(l_dblCashoutPossiblePrice);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_2DAYSAFTER_LABEL), 
            new Long(l_lngTemp));
        
        log.exiting(STR_METHOD_NAME);

    }
     
    /**
     * (set�o���\�z�i3�c�Ɠ��j)<BR>
     * �o���\�z�i 3�c�Ɠ��j���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]   <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���\�z�i3�c�Ɠ��j���x��	 <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�s�ԍ��F �i�����j�s�ԍ�  <BR>
     * �@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F �i�����j�o���\�z�̏����_�ȉ���؂�̂Ă��l <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_dblCashoutPossiblePrice - (�o���\�z)<BR>
     *              �o���\�z
     * @@roseuid 43D5DF890264
     */
    public void setCashoutPossiblePrice3DaysAfter(int l_intLineNumber, double l_dblCashoutPossiblePrice) 
    {
        final String STR_METHOD_NAME = "setCashoutPossiblePrice3DaysAfter(int,double)";
        log.entering(STR_METHOD_NAME);
        long l_lngTemp =  (long) Math.floor(l_dblCashoutPossiblePrice);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_3DAYSAFTER_LABEL), 
            new Long(l_lngTemp));
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�o���\�z�i4�c�Ɠ��j)<BR>
     * �o���\�z�i 4�c�Ɠ��j���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]   <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���\�z�i4�c�Ɠ��j���x��	 <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�s�ԍ��F �i�����j�s�ԍ�  <BR>
     * �@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F �i�����j�o���\�z�̏����_�ȉ���؂�̂Ă��l <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_dblCashoutPossiblePrice - (�o���\�z)<BR>
     *              �o���\�z
     * @@roseuid 43D5DF890264
     */
    public void setCashoutPossiblePrice4DaysAfter(int l_intLineNumber, double l_dblCashoutPossiblePrice) 
    {
        final String STR_METHOD_NAME = "setCashoutPossiblePrice4DaysAfter(int,double)";
        log.entering(STR_METHOD_NAME);
        long l_lngTemp =  (long) Math.floor(l_dblCashoutPossiblePrice);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_4DAYSAFTER_LABEL), 
            new Long(l_lngTemp));
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�o���\�z�i5�c�Ɠ��j)<BR>
     * �o���\�z�i5�c�Ɠ��j���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]   <BR>
     * �@@���ڃ��x���F�@@�o���\���⍇���_�E�����[�hCSV.�o���\�z�i5�c�Ɠ��j���x��	 <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�s�ԍ��F �i�����j�s�ԍ�  <BR>
     * �@@�J�����F �P�j�Ŏ擾�����J�������f��<BR> 
     * �@@�l�F �i�����j�o���\�z�̏����_�ȉ���؂�̂Ă��l <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�<BR>
     * @@param l_dblCashoutPossiblePrice - (�o���\�z)<BR>
     *              �o���\�z
     * @@roseuid 43D5DF890264
     */
    public void setCashoutPossiblePrice5DaysAfter(int l_intLineNumber, double l_dblCashoutPossiblePrice) 
    {
        final String STR_METHOD_NAME = "setCashoutPossiblePrice5DaysAfter(int,double)";
        log.entering(STR_METHOD_NAME);
        long l_lngTemp =  (long) Math.floor(l_dblCashoutPossiblePrice);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_5DAYSAFTER_LABEL), 
            new Long(l_lngTemp));
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�o���\�z)<BR>
     * �v�Z���ʂ��Z�b�g����B <BR>
     * <BR>
     * �P�j����:�c�Ɠ��敪 == 0 �̏ꍇ�Athis.set�o���\�z�i�����j�i����:�s�ԍ� , ����:�v�Z���ʁj<BR>
     * <BR>
     * �Q�j����:�c�Ɠ��敪 == 1 �̏ꍇ�Athis.set�o���\�z�i1�c�Ɠ��j�i����:�s�ԍ� , ����:�v�Z���ʁj <BR>
     * <BR>
     * �R�j����:�c�Ɠ��敪 == 2 �̏ꍇ�Athis.set�o���\�z�i2�c�Ɠ��j�i����:�s�ԍ� , ����:�v�Z���ʁj <BR>
     *<BR>
     * �S�j����:�c�Ɠ��敪 == 3 �̏ꍇ�Athis.set�o���\�z�i3�c�Ɠ��j�i����:�s�ԍ� , ����:�v�Z���ʁj <BR>
     * <BR>
     * �T�j����:�c�Ɠ��敪 == 4 �̏ꍇ�Athis.set�o���\�z�i4�c�Ɠ��j�i����:�s�ԍ� , ����:�v�Z���ʁj <BR> 
     * <BR>
     * �U�j����:�c�Ɠ��敪 == 5 �̏ꍇ�Athis.set�o���\�z�i5�c�Ɠ��j�i����:�s�ԍ� , ����:�v�Z���ʁj  <BR>
     * <BR>
     * @@param l_dblResult - (�v�Z����)<BR>
     *              �v�Z����<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�
     * @@param l_intBizDateType - (�c�Ɠ��敪)<BR>
     *              �c�Ɠ��敪
     * @@roseuid 43D5DF890264
     */
    public void setCashoutPossiblePrice(double l_dblResult, int l_intLineNumber, int l_intBizDateType) 
    {
        final String STR_METHOD_NAME = "setCashoutPossiblePrice(double,int,int)";
        log.entering(STR_METHOD_NAME);
        //����:�c�Ɠ��敪 == 0 �̏ꍇ
        if(l_intBizDateType == 0)
        {
            this.setCashoutPossiblePriceToday(l_intLineNumber, l_dblResult);
        } 
        else if(1 == l_intBizDateType)
        {
            this.setCashoutPossiblePrice1DayAfter(l_intLineNumber, l_dblResult);
        }
        else if(2 == l_intBizDateType)
        {
            this.setCashoutPossiblePrice2DaysAfter(l_intLineNumber, l_dblResult);
        }
        else if(3 == l_intBizDateType)
        {
            this.setCashoutPossiblePrice3DaysAfter(l_intLineNumber, l_dblResult);
        }
        else if(4 == l_intBizDateType)
        {
            this.setCashoutPossiblePrice4DaysAfter(l_intLineNumber, l_dblResult);
        }
        else if(5 == l_intBizDateType)
        {
            this.setCashoutPossiblePrice5DaysAfter(l_intLineNumber, l_dblResult);
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (is�J�����w�b�_�s�o��)<BR>
     * �i�I�[�o�[���C�h���\�b�h�j <BR>
     * CSV�t�@@�C���ɃJ�����w�b�_�s�̏o�͗v�ۂ𔻒肷��B <BR>
     * <BR>
     * true��ԋp����B <BR>
     * @@return boolean
     * @@roseuid 43D5F38F01B9
     */
    public boolean isColumnHeadOutput() 
    {
        return true;
    }
    
    /**
     * (set�����ڋq�]�͏��)<BR>
     * �����ڋq�̗]�͏����Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�o���\�z�i�����j�`�i5�c�Ɠ��j�܂�Loop�������s���B�i0�`5�j <BR>
     * <BR>
     * �@@�P�|�P�jdouble �v�Z���� = ����:���Y�]�͏��<�����ڋq>�I�u�W�F�N�g. <BR>
     * �@@�@@calc���̑����i���t�\�z<���v��S�����l��>�i i �j; <BR>
     * <BR>
     * �@@�P�|�Q�jthis.set�o���\�z�i �v�Z���� , �s�ԍ� , i �j;<BR>
     * @@param l_tpTradingPowerCalcEquity - (���Y�]�͏��<�����ڋq>�I�u�W�F�N�g)<BR>
     *              ���Y�]�͏��<�����ڋq>�I�u�W�F�N�g<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�
     * @@roseuid 43D5DF890264
     */   
    public void setTPTradingPowerCalcEquity(WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquity, 
        int l_intLineNumber) 
    {
        double l_dblResult = 0.0D;
        
        //�P�j�@@�o���\�z�i�����j�`�i5�c�Ɠ��j�܂�Loop�������s���B�i0�`5�j 
        for (int l_intCount = 0; l_intCount < 6; l_intCount ++)
        {
            //�P�|�P�j�@@double �v�Z���� = ����:���Y�]�͏��<�����ڋq>�I�u�W�F�N�g. 
            // �@@calc���̑����i���t�\�z<���v��S�����l��>�i i �j; 
            if (l_tpTradingPowerCalcEquity != null)
            {
                l_dblResult = l_tpTradingPowerCalcEquity.calcOtherTradingPower(l_intCount);
            }

            //�P�|�Q�j�@@this.set�o���\�z�i �v�Z���� , �s�ԍ� , i �j;
            this.setCashoutPossiblePrice(l_dblResult, l_intLineNumber, l_intCount);
        }
    }
    
    /**
     * (set�M�p�ڋq�]�͏��)<BR>
     * �M�p�ڋq�̗]�͏����Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�o���\�z�i�����j�`�i5�c�Ɠ��j�܂�Loop�������s���B�i0�`5�j <BR>
     * <BR>
     * �@@�P�|�P�j�@@double �v�Z���� = ����:���Y�]�͏��<�M�p�ڋq>�I�u�W�F�N�g. <BR>
     * �@@�@@calc���̑����i���t�\�z<���v��S�����l��>�i i �j; <BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.set�o���\�z�i �v�Z���� , �s�ԍ� , i �j;<BR>
     * @@param l_tpTradingPowerCalcMargin - (���Y�]�͏��<�M�p�ڋq>�I�u�W�F�N�g)<BR>
     *              ���Y�]�͏��<�M�p�ڋq>�I�u�W�F�N�g<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     *              �s�ԍ�
     * @@roseuid 43D5DF890264
     */
    public void setTPTradingPowerCalcMargin(WEB3TPTradingPowerCalcMargin l_tpTradingPowerCalcMargin, 
        int l_intLineNumber) 
    {
        double l_dblResult = 0.0D;
        
        //�P�j�@@�o���\�z�i�����j�`�i5�c�Ɠ��j�܂�Loop�������s���B�i0�`5�j 
        for (int i = 0; i < 6; i++)
        {
            //�P�|�P�j�@@double �v�Z���� = ����:���Y�]�͏��<�M�p�ڋq>�I�u�W�F�N�g. 
            //�@@�@@calc���̑����i���t�\�z<���v��S�����l��>�i i �j; 
            if (l_tpTradingPowerCalcMargin != null)
            {
                l_dblResult = l_tpTradingPowerCalcMargin.calcOtherTradingPower(i);
            }
            
            //�P�|�Q�j�@@this.set�o���\�z�i �v�Z���� , �s�ԍ� , i �j;
            this.setCashoutPossiblePrice(l_dblResult, l_intLineNumber, i);
        }
    }
}
@
