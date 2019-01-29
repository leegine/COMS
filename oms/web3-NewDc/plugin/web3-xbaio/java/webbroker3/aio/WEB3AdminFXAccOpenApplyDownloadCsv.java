head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXAccOpenApplyDownloadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV(WEB3AdminFXAccOpenApplyDownloadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/09 杊��] (���u) �V�K�쐬
                 : 2006/02/24 ���_�O (���u) �d�l�ύX�E���f��486
Revesion History : 2008/09/08 ��  �O (���u) �d�l�ύX�E���f��909~934
Revesion History : 2008/10/06 ���u�� (���u) �d�l�ύX�E���f��1057,1058,1059
*/

package webbroker3.aio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.aio.define.WEB3AdminFXAccOpenApplyDownloadCsvColumnDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV)<BR>
 * �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV�N���X 
 * 
 * @@author 杊��](���u)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenApplyDownloadCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenApplyDownloadCsv.class);

    /**
     * (�ǉ��ύX�敪���x��)<BR>
     * �ǉ��ύX�敪���x��<BR>
     */
    public static  String addModifiedDivLabel = "�ǉ��ύX�敪";
    
    /**
     * (���p�҃R�[�h���x��)<BR>
     * ���p�҃R�[�h���x��<BR>
     */
    public static  String userCodeLabel = "���p�҃R�[�h";
    
    /**
     * (���p�Җ����x��)<BR>
     * ���p�Җ����x��<BR>
     */
    public static  String userlNameLabel = "���p�Җ�";
    
    /**
     * (���O�C��ID���x��)<BR>
     * ���O�C��ID���x��<BR>
     */
    public static  String loginIdLabel = "���O�C��ID";
    
    /**
     * (���O�C���p�X���[�h���x��)<BR>
     * ���O�C���p�X���[�h���x��<BR>
     */
    public static  String loginPasswordLabel = "���O�C���p�X���[�h";
    
    /**
     * (�����p�X���[�h���x��)<BR>
     * �����p�X���[�h���x��<BR>
     */
    public static  String orderPasswordLabel = "�����p�X���[�h";
    
    /**
     * (���[���A�h���X�P���x��)<BR>
     * ���[���A�h���X�P���x��<BR>
     */
    public static  String mailAddress1Label = "���[���A�h���X�P";
    
    /**
     * (���[���A�h���X�Q���x��)<BR>
     * ���[���A�h���X�Q���x��<BR>
     */
    public static  String mailAddress2Label = "���[���A�h���X�Q";
    
    /**
     * (���Ȏ���敪���x��)<BR>
     * ���Ȏ���敪���x��<BR>
     */
    public static  String selfTrustDivLabel = "���Ȏ���敪";

    /**
     * (���p�ґ������x��)<BR>
     * ���p�ґ������x��<BR>
     */
    public static  String userAttributeLabel = "���p�ґ���";

    /**
     * (���ϕ��@@���x��)<BR>
     * ���ϕ��@@���x��<BR>
     */
    public static  String transferMethodLabel = "���ϕ��@@";
    
    /**
     * (���X�J�b�g�敪���x��)<BR>
     * ���X�J�b�g�敪���x��<BR>
     */
    public static  String lossCutDivLabel = "���X�J�b�g�敪";
    
    /**
     * (�萔���敪���x��)<BR>
     * �萔���敪���x��<BR>
     */
    public static  String commissionDivLabel = "�萔���敪";
    
    /**
     * (����\�敪���x��)<BR>
     * ����\�敪���x��<BR>
     */
    public static  String tradingDivLabel = "����\�敪";

    /**
     * (�d�q��t���������x��)<BR>
     * �d�q��t���������x��<BR>
     */
    public static  String reportAcceptDateLabel = "�d�q��t������";

    /**
     * (����������m�F�����x��)<BR>
     * ����������m�F�����x��<BR>
     */
    public static  String tradeInstructionsConfirmDateLabel = "����������m�F��";

    /**
     * (������ԍ����x��)<BR>
     * ������ԍ����x��<BR>
     */
    public static  String contractCodeLabel = "������ԍ�";
    
    /**
     * (���l���x��)<BR>
     * ���l���x��<BR>
     */
    public static  String remarkLabel = "���l";
    
    /**
     * (���i�R�[�h�P���x��)<BR>
     * ���i�R�[�h�P���x��<BR>
     */
    public static  String productCode1Label = "���i�R�[�h�P";
    
    /**
     * (����������ʂP���x��)<BR>
     * ����������ʂP���x��<BR>
     */
    public static  String orderQuantityUpper1Label = "����������ʂP";
    
    /**
     * (���i�R�[�h�Q���x��)<BR>
     * ���i�R�[�h�Q���x��<BR>
     */
    public static  String productCode2Label = "���i�R�[�h�Q";
    
    /**
     * (����������ʂQ���x��)<BR>
     * ����������ʂQ���x��<BR>
     */
    public static  String orderQuantityUpper2Label = "����������ʂQ";
    
    /**
     * (���i�R�[�h�R���x��)<BR>
     * ���i�R�[�h�R���x��<BR>
     */
    public static  String productCode3Label = "���i�R�[�h�R";
    
    /**
     * (����������ʂR���x��)<BR>
     * ����������ʂR���x��<BR>
     */
    public static  String orderQuantityUpper3Label = "����������ʂR";
    
    /**
     * (���i�R�[�h�S���x��)<BR>
     * ���i�R�[�h�S���x��<BR>
     */
    public static  String productCode4Label = "���i�R�[�h�S";
    
    /**
     * (����������ʂS���x��)<BR>
     * ����������ʂS���x��<BR>
     */
    public static  String orderQuantityUpper4Label = "����������ʂS";
    
    /**
     * (���i�R�[�h�T���x��)<BR>
     * ���i�R�[�h�T���x��<BR>
     */
    public static  String productCode5Label = "���i�R�[�h�T";
    
    /**
     * (����������ʂT���x��)<BR>
     * ����������ʂT���x��<BR>
     */
    public static  String orderQuantityUpper5Label = "����������ʂT";
    
    /**
     * (���i�R�[�h�U���x��)<BR>
     * ���i�R�[�h�U���x��<BR>
     */
    public static  String productCode6Label = "���i�R�[�h�U";
    
    /**
     * (����������ʂU���x��)<BR>
     * ����������ʂU���x��<BR>
     */
    public static  String orderQuantityUpper6Label = "����������ʂU";
    
    /**
     * (���i�R�[�h�V���x��)<BR>
     * ���i�R�[�h�V���x��<BR>
     */
    public static  String productCode7Label = "���i�R�[�h�V";
    
    /**
     * (����������ʂV���x��)<BR>
     * ����������ʂV���x��<BR>
     */
    public static  String orderQuantityUpper7Label = "����������ʂV";

    /**
     * (���i�R�[�h�W���x��)<BR>
     * ���i�R�[�h�W���x��<BR>
     */
    public static  String productCode8Label = "���i�R�[�h�W";

    /**
     * (����������ʂW���x��)<BR>
     * ����������ʂW���x��<BR>
     */
    public static  String orderQuantityUpper8Label = "����������ʂW";

    /**
     * (���i�R�[�h�X���x��)<BR>
     * ���i�R�[�h�X���x��<BR>
     */
    public static  String productCode9Label = "���i�R�[�h�X";

    /**
     * (����������ʂX���x��)<BR>
     * ����������ʂX���x��<BR>
     */
    public static  String orderQuantityUpper9Label = "����������ʂX";

    /**
     * (���i�R�[�h�P�O���x��)<BR>
     * ���i�R�[�h�P�O���x��<BR>
     */
    public static  String productCode10Label = "���i�R�[�h�P�O";

    /**
     * (����������ʂP�O���x��)<BR>
     * ����������ʂP�O���x��<BR>
     */
    public static  String orderQuantityUpper10Label = "����������ʂP�O";

    /**
     * (���i�R�[�h�P�P���x��)<BR>
     * ���i�R�[�h�P�P���x��<BR>
     */
    public static  String productCode11Label = "���i�R�[�h�P�P";

    /**
     * (����������ʂP�P���x��)<BR>
     * ����������ʂP�P���x��<BR>
     */
    public static  String orderQuantityUpper11Label = "����������ʂP�P";

    /**
     * (���i�R�[�h�P�Q���x��)<BR>
     * ���i�R�[�h�P�Q���x��<BR>
     */
    public static  String productCode12Label = "���i�R�[�h�P�Q";

    /**
     * (����������ʂP�Q���x��)<BR>
     * ����������ʂP�Q���x��<BR>
     */
    public static  String orderQuantityUpper12Label = "����������ʂP�Q";

    /**
     * (���i�R�[�h�P�R���x��)<BR>
     * ���i�R�[�h�P�R���x��<BR>
     */
    public static  String productCode13Label = "���i�R�[�h�P�R";

    /**
     * (����������ʂP�R���x��)<BR>
     * ����������ʂP�R���x��<BR>
     */
    public static  String orderQuantityUpper13Label = "����������ʂP�R";

    /**
     * (���i�R�[�h�P�S���x��)<BR>
     * ���i�R�[�h�P�S���x��<BR>
     */
    public static  String productCode14Label = "���i�R�[�h�P�S";

    /**
     * (����������ʂP�S���x��)<BR>
     * ����������ʂP�S���x��<BR>
     */
    public static  String orderQuantityUpper14Label = "����������ʂP�S";

    /**
     * (���i�R�[�h�P�T���x��)<BR>
     * ���i�R�[�h�P�T���x��<BR>
     */
    public static  String productCode15Label = "���i�R�[�h�P�T";

    /**
     * (����������ʂP�T���x��)<BR>
     * ����������ʂP�T���x��<BR>
     */
    public static  String orderQuantityUpper15Label = "����������ʂP�T";

    /**
     * (���i�R�[�h�P�U���x��)<BR>
     * ���i�R�[�h�P�U���x��<BR>
     */
    public static  String productCode16Label = "���i�R�[�h�P�U";

    /**
     * (����������ʂP�U���x��)<BR>
     * ����������ʂP�U���x��<BR>
     */
    public static  String orderQuantityUpper16Label = "����������ʂP�U";

    /**
     * (���i�R�[�h�P�V���x��)<BR>
     * ���i�R�[�h�P�V���x��<BR>
     */
    public static  String productCode17Label = "���i�R�[�h�P�V";

    /**
     * (����������ʂP�V���x��)<BR>
     * ����������ʂP�V���x��<BR>
     */
    public static  String orderQuantityUpper17Label = "����������ʂP�V";

    /**
     * (���i�R�[�h�P�W���x��)<BR>
     * ���i�R�[�h�P�W���x��<BR>
     */
    public static  String productCode18Label = "���i�R�[�h�P�W";

    /**
     * (����������ʂP�W���x��)<BR>
     * ����������ʂP�W���x��<BR>
     */
    public static  String orderQuantityUpper18Label = "����������ʂP�W";

    /**
     * (���i�R�[�h�P�X���x��)<BR>
     * ���i�R�[�h�P�X���x��<BR>
     */
    public static  String productCode19Label = "���i�R�[�h�P�X";

    /**
     * (����������ʂP�X���x��)<BR>
     * ����������ʂP�X���x��<BR>
     */
    public static  String orderQuantityUpper19Label = "����������ʂP�X";

    /**
     * (���i�R�[�h�Q�O���x��)<BR>
     * ���i�R�[�h�Q�O���x��<BR>
     */
    public static  String productCode20Label = "���i�R�[�h�Q�O";

    /**
     * (����������ʂQ�O���x��)<BR>
     * ����������ʂQ�O���x��<BR>
     */
    public static  String orderQuantityUpper20Label = "����������ʂQ�O";

    /**
     * (���i�R�[�h�Q�P���x��)<BR>
     * ���i�R�[�h�Q�P���x��<BR>
     */
    public static  String productCode21Label = "���i�R�[�h�Q�P";

    /**
     * (����������ʂQ�P���x��)<BR>
     * ����������ʂQ�P���x��<BR>
     */
    public static  String orderQuantityUpper21Label = "����������ʂQ�P";

    /**
     * (���i�R�[�h�Q�Q���x��)<BR>
     * ���i�R�[�h�Q�Q���x��<BR>
     */
    public static  String productCode22Label = "���i�R�[�h�Q�Q";

    /**
     * (����������ʂQ�Q���x��)<BR>
     * ����������ʂQ�Q���x��<BR>
     */
    public static  String orderQuantityUpper22Label = "����������ʂQ�Q";

    /**
     * (���i�R�[�h�Q�R���x��)<BR>
     * ���i�R�[�h�Q�R���x��<BR>
     */
    public static  String productCode23Label = "���i�R�[�h�Q�R";

    /**
     * (����������ʂQ�R���x��)<BR>
     * ����������ʂQ�R���x��<BR>
     */
    public static  String orderQuantityUpper23Label = "����������ʂQ�R";

    /**
     * (���i�R�[�h�Q�S���x��)<BR>
     * ���i�R�[�h�Q�S���x��<BR>
     */
    public static  String productCode24Label = "���i�R�[�h�Q�S";

    /**
     * (����������ʂQ�S���x��)<BR>
     * ����������ʂQ�S���x��<BR>
     */
    public static  String orderQuantityUpper24Label = "����������ʂQ�S";

    /**
     * (���i�R�[�h�Q�T���x��)<BR>
     * ���i�R�[�h�Q�T���x��<BR>
     */
    public static  String productCode25Label = "���i�R�[�h�Q�T";

    /**
     * (����������ʂQ�T���x��)<BR>
     * ����������ʂQ�T���x��<BR>
     */
    public static  String orderQuantityUpper25Label = "����������ʂQ�T";
    
    /**
     * (�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * this.create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B<BR>
     * @@roseuid 43D5BD8B03DB
     */
    public WEB3AdminFXAccOpenApplyDownloadCsv() 
    {
        this.createColumnHeader();
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����A<BR>
     * set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B <BR>
     * <BR>
     * [�J�����w�b�_�z��] <BR>
     * <BR>
     * - index = 0<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.�ǉ��ύX�敪���x��<BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * - index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���p�҃R�[�h���x��<BR>
     * �@@�J�����ԍ��F 1 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * - index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���p�Җ����x��<BR>
     * �@@�J�����ԍ��F 2 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * - index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���O�C��ID���x��<BR>
     * �@@�J�����ԍ��F 3 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * - index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���O�C���p�X���[�h���x��<BR>
     * �@@�J�����ԍ��F 4 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * - index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.�����p�X���[�h���x��<BR>
     * �@@�J�����ԍ��F5 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * - index = 6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���[���A�h���X�P���x��<BR>
     * �@@�J�����ԍ��F 6 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index =  7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���[���A�h���X�Q���x��<BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 8<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���Ȏ���敪���x��<BR>
     * �@@�J�����ԍ��F 8 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 9<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���p�ґ������x��<BR>
     * �@@�J�����ԍ��F 9<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 10<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���ϕ��@@���x��<BR>
     * �@@�J�����ԍ��F 10<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 11<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.�萔���敪���x��<BR>
     * �@@�J�����ԍ��F 11<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 12<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���X�J�b�g�敪���x��<BR>
     * �@@�J�����ԍ��F 12<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 13<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����\�敪���x��<BR>
     * �@@�J�����ԍ��F 13<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 14<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.�d�q��t���������x��<BR>
     * �@@�J�����ԍ��F 14<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ new SimpleDateFormat("yyyyMMdd") <BR>
     * <BR>
     * - index = 15<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������m�F�����x��<BR>
     * �@@�J�����ԍ��F 15<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ new SimpleDateFormat("yyyyMMdd") <BR>
     * <BR>
     * - index = 16<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.������ԍ����x��<BR>
     * �@@�J�����ԍ��F 16 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 17<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���l���x��<BR>
     * �@@�J�����ԍ��F 17 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 18<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P���x��<BR>
     * �@@�J�����ԍ��F 18<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 19<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP���x��<BR>
     * �@@�J�����ԍ��F 19<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 20<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q���x��<BR>
     * �@@�J�����ԍ��F 20<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 21<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ���x��<BR>
     * �@@�J�����ԍ��F 21<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 22<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�R���x��<BR>
     * �@@�J�����ԍ��F 22<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 23<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂR���x��<BR>
     * �@@�J�����ԍ��F 23<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 24<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�S���x��<BR>
     * �@@�J�����ԍ��F 24<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 25<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂS���x��<BR>
     * �@@�J�����ԍ��F 25<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 26<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�T���x��<BR>
     * �@@�J�����ԍ��F 26<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 27<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂT���x��<BR>
     * �@@�J�����ԍ��F 27<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 28<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�U���x��<BR>
     * �@@�J�����ԍ��F 28<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 29<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂU���x��<BR>
     * �@@�J�����ԍ��F 29<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 30<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�V���x��<BR>
     * �@@�J�����ԍ��F 30<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 31<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂV���x��<BR>
     * �@@�J�����ԍ��F 31<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 32<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�W���x��<BR>
     * �@@�J�����ԍ��F 32<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 33<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂW���x��<BR>
     * �@@�J�����ԍ��F 33<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 34<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�X���x��<BR>
     * �@@�J�����ԍ��F 34<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * - index = 35<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂX���x��<BR>
     * �@@�J�����ԍ��F 35<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 36<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�O���x��<BR>
     * �@@�J�����ԍ��F 36<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 37<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�O���x��<BR>
     * �@@�J�����ԍ��F 37<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 38<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�P���x��<BR>
     * �@@�J�����ԍ��F 38<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 39<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�P���x��<BR>
     * �@@�J�����ԍ��F 39<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 40<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�Q���x��<BR>
     * �@@�J�����ԍ��F 40<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 41<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�Q���x��<BR>
     * �@@�J�����ԍ��F 41<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 42<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�R���x��<BR>
     * �@@�J�����ԍ��F 42<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 43<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�R���x��<BR>
     * �@@�J�����ԍ��F 43<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 44<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�S���x��<BR>
     * �@@�J�����ԍ��F 44<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 45<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�S���x��<BR>
     * �@@�J�����ԍ��F 45<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 46<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�T���x��<BR>
     * �@@�J�����ԍ��F 46<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 47<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�T���x��<BR>
     * �@@�J�����ԍ��F 47<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 48<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�U���x��<BR>
     * �@@�J�����ԍ��F 48<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 49<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�U���x��<BR>
     * �@@�J�����ԍ��F 49<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 50<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�V���x��<BR>
     * �@@�J�����ԍ��F 50<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 51<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�V���x��<BR>
     * �@@�J�����ԍ��F 51<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 52<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�W���x��<BR>
     * �@@�J�����ԍ��F 52<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 53<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�W���x��<BR>
     * �@@�J�����ԍ��F 53<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 54<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�X���x��<BR>
     * �@@�J�����ԍ��F 54<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 55<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�X���x��<BR>
     * �@@�J�����ԍ��F 55<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 56<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�O���x��<BR>
     * �@@�J�����ԍ��F 56<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 57<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�O���x�� <BR>
     * �@@�J�����ԍ��F 57<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 58<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�P���x��<BR>
     * �@@�J�����ԍ��F 58<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 59<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�P���x��<BR>
     * �@@�J�����ԍ��F 59<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 60<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�Q���x��<BR>
     * �@@�J�����ԍ��F 60<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 61<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�Q���x��<BR>
     * �@@�J�����ԍ��F 61<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 62<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�R���x��<BR>
     * �@@�J�����ԍ��F 62<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 63<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�R���x��<BR>
     * �@@�J�����ԍ��F 63<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 64<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�S���x��<BR>
     * �@@�J�����ԍ��F 64<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 65<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�S���x��<BR>
     * �@@�J�����ԍ��F 65<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 66<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�T���x��<BR>
     * �@@�J�����ԍ��F 66<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * - index = 67<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�T���x��<BR>
     * �@@�J�����ԍ��F 67<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * @@roseuid 43D5BF5802A3
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        List l_lisLabels = new ArrayList();
        
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.�ǉ��ύX�敪���x��
        l_lisLabels.add(this.addModifiedDivLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���p�҃R�[�h���x��
        l_lisLabels.add(this.userCodeLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���p�Җ����x��
        l_lisLabels.add(this.userlNameLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���O�C��ID���x��
        l_lisLabels.add(this.loginIdLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���O�C���p�X���[�h���x��
        l_lisLabels.add(this.loginPasswordLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.�����p�X���[�h���x��
        l_lisLabels.add(this.orderPasswordLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���[���A�h���X�P���x��
        l_lisLabels.add(this.mailAddress1Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���[���A�h���X�Q���x��
        l_lisLabels.add(this.mailAddress2Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���Ȏ���敪���x��
        l_lisLabels.add(this.selfTrustDivLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���p�ґ������x��
        l_lisLabels.add(this.userAttributeLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���ϕ��@@���x��
        l_lisLabels.add(this.transferMethodLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.�萔���敪���x��
        l_lisLabels.add(this.commissionDivLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���X�J�b�g�敪���x��
        l_lisLabels.add(this.lossCutDivLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����\�敪���x�� 
        l_lisLabels.add(this.tradingDivLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.�d�q��t���������x��
        l_lisLabels.add(this.reportAcceptDateLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������m�F�����x��
        l_lisLabels.add(this.tradeInstructionsConfirmDateLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.������ԍ����x��
        l_lisLabels.add(this.contractCodeLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���l���x��
        l_lisLabels.add(this.remarkLabel);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P���x��
        l_lisLabels.add(this.productCode1Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP���x��
        l_lisLabels.add(this.orderQuantityUpper1Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q���x��
        l_lisLabels.add(this.productCode2Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ���x��
        l_lisLabels.add(this.orderQuantityUpper2Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�R���x��
        l_lisLabels.add(this.productCode3Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂR���x��
        l_lisLabels.add(this.orderQuantityUpper3Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�S���x��
        l_lisLabels.add(this.productCode4Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂS���x��
        l_lisLabels.add(this.orderQuantityUpper4Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�T���x��
        l_lisLabels.add(this.productCode5Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂT���x��
        l_lisLabels.add(this.orderQuantityUpper5Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�U���x��
        l_lisLabels.add(this.productCode6Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂU���x��
        l_lisLabels.add(this.orderQuantityUpper6Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�V���x��
        l_lisLabels.add(this.productCode7Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂV���x��
        l_lisLabels.add(this.orderQuantityUpper7Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�W���x��
        l_lisLabels.add(this.productCode8Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂW���x��
        l_lisLabels.add(this.orderQuantityUpper8Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�X���x��
        l_lisLabels.add(this.productCode9Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂX���x��
        l_lisLabels.add(this.orderQuantityUpper9Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�O���x��
        l_lisLabels.add(this.productCode10Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�O���x��
        l_lisLabels.add(this.orderQuantityUpper10Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�P���x��
        l_lisLabels.add(this.productCode11Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�P���x��
        l_lisLabels.add(this.orderQuantityUpper11Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�Q���x��
        l_lisLabels.add(this.productCode12Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�Q���x��
        l_lisLabels.add(this.orderQuantityUpper12Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�R���x��
        l_lisLabels.add(this.productCode13Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�R���x��
        l_lisLabels.add(this.orderQuantityUpper13Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�S���x��
        l_lisLabels.add(this.productCode14Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�S���x��
        l_lisLabels.add(this.orderQuantityUpper14Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�T���x��
        l_lisLabels.add(this.productCode15Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�T���x��
        l_lisLabels.add(this.orderQuantityUpper15Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�U���x��
        l_lisLabels.add(this.productCode16Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�U���x��
        l_lisLabels.add(this.orderQuantityUpper16Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�V���x��
        l_lisLabels.add(this.productCode17Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�V���x��
        l_lisLabels.add(this.orderQuantityUpper17Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�W���x��
        l_lisLabels.add(this.productCode18Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�W���x��
        l_lisLabels.add(this.orderQuantityUpper18Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�X���x��
        l_lisLabels.add(this.productCode19Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�X���x��
        l_lisLabels.add(this.orderQuantityUpper19Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�O���x��
        l_lisLabels.add(this.productCode20Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�O���x��
        l_lisLabels.add(this.orderQuantityUpper20Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�P���x��
        l_lisLabels.add(this.productCode21Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�P���x��
        l_lisLabels.add(this.orderQuantityUpper21Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�Q���x��
        l_lisLabels.add(this.productCode22Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�Q���x��
        l_lisLabels.add(this.orderQuantityUpper22Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�R���x��
        l_lisLabels.add(this.productCode23Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�R���x��
        l_lisLabels.add(this.orderQuantityUpper23Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�S���x��
        l_lisLabels.add(this.productCode24Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�S���x��
        l_lisLabels.add(this.orderQuantityUpper24Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�T���x��
        l_lisLabels.add(this.productCode25Label);
        //�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�T���x��
        l_lisLabels.add(this.orderQuantityUpper25Label);
        
        //�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐���
        WEB3GentradeCsvColumnModel[] l_columnModel = new WEB3GentradeCsvColumnModel[l_lisLabels.size()];

        String l_strColumn = null;
        DateFormat l_dateFormat = null;
        int l_intModel = 0;

        for (int i = 0; i < l_lisLabels.size(); i++)
        {
            l_strColumn = (String) l_lisLabels.get(i);
            l_dateFormat = null;
            l_intModel = WEB3GentradeCsvColumnModel.STRINGTYPE;

            if (this.reportAcceptDateLabel.equals(l_strColumn)
                || this.tradeInstructionsConfirmDateLabel.equals(l_strColumn))
            {
                l_intModel = WEB3GentradeCsvColumnModel.DATETYPE;
                l_dateFormat = new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            }

            l_columnModel[i] = 
                new WEB3GentradeCsvColumnModel(
                    l_strColumn,
                    i,
                    l_intModel,
                    l_dateFormat);
        }
        
        //set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B
        this.setColumnHeader(l_columnModel);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�ǉ��ύX�敪)<BR>
     * �ǉ��ύX�敪���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.�ǉ��ύX�敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "I"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DC26012C
     */
    public void setAddModifiedDiv(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setAddModifiedDiv(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.addModifiedDivLabel), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ADDMODIFIEDDIV_I);
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (set���p�҃R�[�h)<BR>
     * ���p�҃R�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���p�҃R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strValue - (�l)<BR>
     * CSV�ɃZ�b�g����l<BR>
     * @@roseuid 43D5DE45016A
     */
    public void setUserCode(int l_intLineNumber, String l_strValue) 
    {
        final String STR_METHOD_NAME = " setUserCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(this.userCodeLabel), l_strValue);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���p�Җ�)<BR>
     * ���p�Җ����Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���p�Җ����x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strValue - (�l)<BR>
     * CSV�ɃZ�b�g����l<BR>
     * @@roseuid 43D5DE8D0032
     */
    public void setUserName(int l_intLineNumber, String l_strValue) 
    {
        final String STR_METHOD_NAME = " setUserName(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(this.userlNameLabel), l_strValue);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���O�C��ID)<BR>
     * ���O�C��ID���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���O�C��ID���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strValue - (�l)<BR>
     * CSV�ɃZ�b�g����l<BR>
     * @@roseuid 43D5DEA80284
     */
    public void setLoginId(int l_intLineNumber, String l_strValue) 
    {
        final String STR_METHOD_NAME = " setLoginId(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(this.loginIdLabel), l_strValue);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set���O�C���p�X���[�h)<BR>
     * ���O�C���p�X���[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���O�C���p�X���[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F null<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DECC013C
     */
    public void setLoginPassword(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setLoginPassword(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(this.loginPasswordLabel), null);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�����p�X���[�h)<BR>
     * �����p�X���[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.�����p�X���[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F null<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DEFA0051
     */
    public void setOrderPassword(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderPassword(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(this.orderPasswordLabel), null);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set���[���A�h���X�P)<BR>
     * ���[���A�h���X�P���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���[���A�h���X�P���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strValue - (�l)<BR>
     * CSV�ɃZ�b�g����l<BR>
     * @@roseuid 43D5DF1D0321
     */
    public void setMailAddress1(int l_intLineNumber, String l_strValue) 
    {
        final String STR_METHOD_NAME = " setMailAddress1(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(this.mailAddress1Label), l_strValue);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set���[���A�h���X�Q)<BR>
     * ���[���A�h���X�Q���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���[���A�h���X�Q���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F null<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DF3901E8
     */
    public void setMailAddress2(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setMailAddress2(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(this.mailAddress2Label), null);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���Ȏ���敪)<BR>
     * ���Ȏ���敪���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���Ȏ���敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "A"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DF590061
     */
    public void setSelfTrustDiv(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setSelfTrustDiv(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.selfTrustDivLabel), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.SELFTRUSTDIV);
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���p�ґ���)<BR>
     * ���p�ґ������Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���p�ґ������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "A"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setUserAttribute(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setUserAttribute(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.userAttributeLabel),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.USERATTRIBUTE);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���ϕ��@@)<BR>
     * ���ϕ��@@���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���ϕ��@@���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "2"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */

    public void setTransferMethod(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setTransferMethod(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.transferMethodLabel),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.TRANSFERMETHOD);

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set���X�J�b�g�敪)<BR>
     * ���X�J�b�g�敪���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���X�J�b�g�敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "01"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DF850207
     */
    public void setLossCutDiv(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setLossCutDiv(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.lossCutDivLabel), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.LOSSCUTDIV);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set�萔���敪)<BR>
     * �萔���敪���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.�萔���敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F  "01"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DF87012C
     */
    public void setCommissionDiv(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setCommissionDiv(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.commissionDivLabel), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.COMMISSIONDIV);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set����\�敪)<BR>
     * ����\�敪���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����\�敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "1"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DF8800DE
     */
    public void setTradingDiv(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setTradingDiv(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.tradingDivLabel), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.TRADINGDIV);
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set�d�q��t������)<BR>
     * �d�q��t���������Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.�d�q��t���������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F ����.�l<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_datReportAcceptDate - (�l)<BR>
     * �l<BR>
     */
    public void setReportAcceptDate(int l_intLineNumber, Date l_datReportAcceptDate)
    {
        final String STR_METHOD_NAME = " setReportAcceptDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(this.reportAcceptDateLabel), l_datReportAcceptDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set����������m�F��)<BR>
     * ����������m�F�����Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������m�F�����x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F ����.�l<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_datConfirmDate - (�l)<BR>
     * �l<BR>
     */
    public void setTradeInstructionsConfirmDate(int l_intLineNumber, Date l_datConfirmDate)
    {
        final String STR_METHOD_NAME = "setTradeInstructionsConfirmDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber,
            this.getColumnModel(this.tradeInstructionsConfirmDateLabel),
            l_datConfirmDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set������ԍ�)<BR>
     * ������ԍ����Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.������ԍ����x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F null<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */

    public void setContractCode(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setContractCode(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.contractCodeLabel),
            null);

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set���l)<BR>
     * ���l���Z�b�g����B <BR>
     *  <BR>
     * �P�j�J�������f���擾 <BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B <BR>
     *  <BR>
     *    [����] <BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���l���x�� <BR>
     *  <BR>
     * �Q�j�l�Z�b�g <BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B <BR>
     *  <BR>
     *    [����] <BR>
     *    �s�ԍ��F ����.�s�ԍ� <BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     *    �l�F ����.�l <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strRemark - (���l)<BR>
     * @@roseuid 43D5DF8803DB
     */
    public void setRemark(int l_intLineNumber, String l_strRemark) 
    {
        final String STR_METHOD_NAME = " setRemark(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(l_intLineNumber, this.getColumnModel(this.remarkLabel), l_strRemark);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���i�R�[�h�P)<BR>
     * ���i�R�[�h�P���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "USD/JPY"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DF890264
     */
    public void setProductCode1(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setProductCode1(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode1Label), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE1);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set����������ʂP)<BR>
     * ����������ʂP���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DF8A0070
     */
    public void setOrderQuantityUpper1(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper1(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper1Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set���i�R�[�h�Q)<BR>
     * ���i�R�[�h�Q���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "EUR/JPY"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DF8A0264
     */
    public void setProductCode2(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setProductCode2(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode2Label), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE2);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set����������ʂQ)<BR>
     * ����������ʂQ���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DF8B0051
     */
    public void setOrderQuantityUpper2(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper2(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper2Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);
        
        log.exiting(STR_METHOD_NAME);
 
    }
    
    /**
     * (set���i�R�[�h�R)<BR>
     * ���i�R�[�h�R���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�R���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "GBP/JPY"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DF8B037E
     */
    public void setProductCode3(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setProductCode3(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode3Label), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE3);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set����������ʂR)<BR>
     * ����������ʂR���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂR���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DF8C0032
     */
    public void setOrderQuantityUpper3(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper3(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper3Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set���i�R�[�h�S)<BR>
     * ���i�R�[�h�S���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�S���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "AUD/JPY"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5DFFE018A
     */
    public void setProductCode4(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setProductCode4(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode4Label), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE4);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set����������ʂS)<BR>
     * ����������ʂS���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂS���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5E00501F7
     */
    public void setOrderQuantityUpper4(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper4(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper4Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set���i�R�[�h�T)<BR>
     * ���i�R�[�h�T���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�T���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "CHF/JPY"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5E0060216
     */
    public void setProductCode5(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setProductCode5(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode5Label), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE5);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set����������ʂT)<BR>
     * ����������ʂT���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂT���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F"300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5E007013C
     */
    public void setOrderQuantityUpper5(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper5(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper5Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set���i�R�[�h�U)<BR>
     * ���i�R�[�h�U���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�U���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "CAD/JPY"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5E007037E
     */
    public void setProductCode6(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setProductCode6(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode6Label), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE6);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set����������ʂU)<BR>
     * ����������ʂU���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂU���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5E008015B
     */
    public void setOrderQuantityUpper6(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper6(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper6Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set���i�R�[�h�V)<BR>
     * ���i�R�[�h�V���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�V���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F  "NZD/JPY"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5E0090032
     */
    public void setProductCode7(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setProductCode7(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode7Label), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE7);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set����������ʂV)<BR>
     * ����������ʂV���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂV���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 43D5E03A0032
     */
    public void setOrderQuantityUpper7(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper7(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper7Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�W)<BR>
     * ���i�R�[�h�W���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�W���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "ZAR/JPY"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode8(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode8(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode8Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE8);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂW)<BR>
     * ����������ʂW���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂW���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper8(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper8(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper8Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�X)<BR>
     * ���i�R�[�h�X���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�X���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "TRY/JPY"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */

    public void setProductCode9(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode9(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode9Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE9);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂX)<BR>
     * ����������ʂX���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂX���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper9(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper9(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper9Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�P�O)<BR>
     * ���i�R�[�h�P�O���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�O���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "NOK/JPY"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode10(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode10(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode10Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE10);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂP�O)<BR>
     * ����������ʂP�O���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�O���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper10(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper10(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper10Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�P�P)<BR>
     * ���i�R�[�h�P�P���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�P���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "HKD/JPY"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode11(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode11(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode11Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE11);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂP�P)<BR>
     * ����������ʂP�P���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�P���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper11(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper11(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper11Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�P�Q)<BR>
     * ���i�R�[�h�P�Q���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�Q���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "SEK/JPY"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode12(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode12(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode12Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE12);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂP�Q)<BR>
     * ����������ʂP�Q���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�Q���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper12(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper12(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper12Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�P�R)<BR>
     * ���i�R�[�h�P�R���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�R���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "MXN/JPY"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode13(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode13(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode13Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE13);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂP�R)<BR>
     * ����������ʂP�R���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�R���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper13(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper13(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper13Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�P�S)<BR>
     * ���i�R�[�h�P�S���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�S���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "PLN/JPY"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode14(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode14(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode14Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE14);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂP�S)<BR>
     * ����������ʂP�S���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�S���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper14(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper14(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper14Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�P�T)<BR>
     * ���i�R�[�h�P�T���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�T���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "EUR/USD"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode15(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode15(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode15Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE15);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂP�T)<BR>
     * ����������ʂP�T���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�T���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper15(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper15(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper15Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�P�U)<BR>
     * ���i�R�[�h�P�U���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�U���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "GBP/USD"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode16(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode16(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode16Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE16);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂP�U)<BR>
     * ����������ʂP�U���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�U���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper16(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper16(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper16Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�P�V)<BR>
     * ���i�R�[�h�P�V���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�V���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "GBP/CHF"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode17(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode17(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode17Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE17);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂP�V)<BR>
     * ����������ʂP�V���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�V���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper17(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper17(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper17Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�P�W)<BR>
     * ���i�R�[�h�P�W���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�W���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "USD/CHF"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode18(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode18(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode18Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE18);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂP�W)<BR>
     * ����������ʂP�W���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�W���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper18(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper18(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper18Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�P�X)<BR>
     * ���i�R�[�h�P�X���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�P�X���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "USD/CAD"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode19(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode19(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode19Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE19);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂP�X)<BR>
     * ����������ʂP�X���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂP�X���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper19(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper19(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper19Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�Q�O)<BR>
     * ���i�R�[�h�Q�O���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�O���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "AUD/USD"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode20(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode20(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode20Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE20);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂQ�O)<BR>
     * ����������ʂQ�O���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�O���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper20(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper20(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper20Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�Q�P)<BR>
     * ���i�R�[�h�Q�P���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�P���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "EUR/CHF"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode21(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode21(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode21Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE21);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂQ�P)<BR>
     * ����������ʂQ�P���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�P���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper21(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper21(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper21Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�Q�Q)<BR>
     * ���i�R�[�h�Q�Q���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�Q���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "EUR/GBP"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode22(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode22(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode22Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE22);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂQ�Q)<BR>
     * ����������ʂQ�Q���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�Q���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper22(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper22(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper22Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�Q�R)<BR>
     * ���i�R�[�h�Q�R���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�R���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "NZD/USD"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode23(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode23(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode23Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE23);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂQ�R)<BR>
     * ����������ʂQ�R���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�R���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper23(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper23(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper23Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�Q�S)<BR>
     * ���i�R�[�h�Q�S���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�S���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "EUR/AUD"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode24(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode24(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode24Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE24);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂQ�S)<BR>
     * ����������ʂQ�S���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�S���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper24(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper24(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper24Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set���i�R�[�h�Q�T)<BR>
     * ���i�R�[�h�Q�T���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.���i�R�[�h�Q�T���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "GBP/AUD"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setProductCode25(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode25(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode25Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE25);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set����������ʂQ�T)<BR>
     * ����������ʂQ�T���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ڃ��x���F �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV.����������ʂQ�T���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�s�ԍ��F ����.�s�ԍ�<BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F "300"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setOrderQuantityUpper25(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper25(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //�Q�j�l�Z�b�g
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper25Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (is�J�����w�b�_�s�o��)<BR>
     * �i�I�[�o�[���C�h���\�b�h�j <BR>
     * CSV�t�@@�C���ɃJ�����w�b�_�s�̏o�͗v�ۂ𔻒肷��B <BR>
     * <BR>
     * false��ԋp����B <BR>
     * @@return boolean
     * @@roseuid 43D5F38F01B9
     */
    public boolean isColumnHeadOutput() 
    {
        return false;
    }
    
    /**
     * (getCSV�t�@@�C���s)<BR>
     * �i�I�[�o�[���C�h���\�b�h�j <BR>
     * CSV�t�@@�C���ɏo�͂���f�[�^���A�s���̔z��ɂĕԋp����B <BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��getCSV�t�@@�C���s()���\�b�h���R�[������B <BR>
     * <BR>
     * �Q�j�擾�����z�񂩂��s�A�u�����N�݂̂̍s�����O����B <BR>
     * <BR>
     * �R�j�Q�j�̌��ʂ�ԋp����B <BR>
     * @@return String[]
     * @@roseuid 43D5F8D602E1
     */
    public String[] getCsvFileLines() 
    {
        final String STR_METHOD_NAME = " getCsvFileLines()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�X�[�p�[�N���X��getCSV�t�@@�C���s()���\�b�h���R�[������B
        String[] l_strCvsFileLines = super.getCsvFileLines();
        
        //�Q�j�擾�����z�񂩂��s�A�u�����N�݂̂̍s�����O����B
        List l_lisResults = new ArrayList();
        
        for (int i = 0; i < l_strCvsFileLines.length; i++)
        {
            if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strCvsFileLines[i]))
            {
                l_lisResults.add(l_strCvsFileLines[i]);
            }
        }
            
        //�R�j�Q�j�̌��ʂ�ԋp����B
        String[] l_strResults = new String[l_lisResults.size()];
        l_lisResults.toArray(l_strResults);
        
        log.exiting(STR_METHOD_NAME);
        return l_strResults;
    }
}
@
