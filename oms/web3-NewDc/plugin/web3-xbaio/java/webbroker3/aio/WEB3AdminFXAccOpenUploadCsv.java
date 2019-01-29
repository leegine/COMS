head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXAccOpenUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���FX�����J�݃A�b�v���[�hCSV(WEB3AdminFXAccOpenUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 ���_�O (���u) �V�K�쐬
                 : 2006/02/24 ���_�O (���u) �d�l�ύX�E���f��503
                 : 2006/02/27 ���iSRA�j �d�l�ύX�E���f��505
                 : 2006/03/01 ���iSRA�j �����QU02771,2774�Ή�
                 : 2006/03/01 ���iSRA�j �����QU02775�Ή�
                 : 2006/03/02 ���_�O (���u) �d�l�ύX�E���f��486
Revesion History : 2008/09/12 ���m�a (���u) �d�l�ύX�E���f��936~959,���f��979,980,982,986
*/

package webbroker3.aio;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.GftAccountOpenStatusRow;
import webbroker3.aio.define.WEB3AioAgreementDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ���FX�����J�݃A�b�v���[�hCSV)<BR>
 * �Ǘ���FX�����J�݃A�b�v���[�hCSV�N���X<BR>
 * 
 * @@author ���_�O(���u)
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadCsv extends WEB3GentradeCsvUploadDataModel 
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenUploadCsv.class);
    
    /**
     * (�A�b�v���[�h�t�@@�C��ID)<BR>
     * (�A�b�v���[�h�t�@@�C��ID)<BR>
     */
    private String UPLOAD_FILEID = "FX�����J�݃A�b�v���[�h";
    
    /**
     * (���p�҃R�[�h���x��)<BR>
     * ���p�҃R�[�h���x��<BR>
     */
    public static  String USER_CODE_LABEL = "���p�҃R�[�h";
    
    /**
     * (���p�Җ����x��)<BR>
     * ���p�Җ����x��<BR>
     */
    public static  String USER_NAME_LABEL = "���p�Җ�";
    
    /**
     * (���O�C��ID���x��)<BR>
     * ���O�C��ID���x��<BR>
     */
    public static  String LOGINID_LABEL = "���O�C��ID";
    
    /**
     * (���O�C���p�X���[�h���x��)<BR>
     * ���O�C���p�X���[�h���x��<BR>
     */
    public static  String LOGIN_PASSWORD_LABEL = "���O�C���p�X���[�h";
    
    /**
     * (�����p�X���[�h���x��)<BR>
     * �����p�X���[�h���x��<BR>
     */
    public static  String ORDER_PASSWORD_LABEL = "�����p�X���[�h";
    
    /**
     * (���[���A�h���X�P���x��)<BR>
     * ���[���A�h���X�P���x��<BR>
     */
    public static  String MAILADDRESS1_LABEL = "���[���A�h���X�P";
    
    /**
     * (���[���A�h���X�Q���x��)<BR>
     * ���[���A�h���X�Q���x��<BR>
     */
    public static  String MAILADDRESS2_LABEL = "���[���A�h���X�Q";
    
    /**
     * (���Ȏ���敪���x��)<BR>
     * ���Ȏ���敪���x��<BR>
     */
    public static  String SELF_TRUSTDIV_LABEL = "���Ȏ���敪";

    /**
     * (���p�ґ������x��)<BR>
     * ���p�ґ������x��<BR>
     */
    public static String USER_ATTRIBUTE_LABEL = "���p�ґ���";

    /**
     * (���ϕ��@@���x��)<BR>
     * ���ϕ��@@���x��<BR>
     */
    public static String SETTLEMENT_METHOD_LABEL = "���ϕ��@@";

    /**
     * (���X�J�b�g�敪���x��)<BR>
     * ���X�J�b�g�敪���x��<BR>
     */
    public static  String LOSSCUTDIV_LABEL = "���X�J�b�g�敪";
    
    /**
     * (�萔���敪���x��)<BR>
     * �萔���敪���x��<BR>
     */
    public static  String COMMISSIONDIV_LABEL = "�萔���敪";
    
    /**
     * (����\�敪���x��)<BR>
     * ����\�敪���x��<BR>
     */
    public static  String TRADINGDIV_LABEL = "����\�敪";

    /**
     * (�d�q��t���������x��)<BR>
     * �d�q��t���������x��<BR>
     */
    public static String ELECTRON_GRANT_PERMISSION_DAY_LABEL = "�d�q��t������";

    /**
     * (����������m�F�����x��)<BR>
     * ����������m�F�����x��<BR>
     */
    public static String TRADE_OPERATING_MANUAL_CONFIRMATION_DAY_LABEL = "����������m�F��";

    /**
     * (������ԍ����x��)<BR>
     * ������ԍ����x��<BR>
     */
    public static String AGREEMENT_BOOK_NUMBER_LABEL = "������ԍ�";

    /**
     * (���l���x��)<BR>
     * ���l���x��<BR>
     */
    public static  String REMARK_LABEL = "���l";
    
    /**
     * (���i�R�[�h�P���x��)<BR>
     * ���i�R�[�h�P���x��<BR>
     */
    public static  String PRODUCTCODE1_LABEL = "���i�R�[�h�P";
    
    /**
     * (����������ʂP���x��)<BR>
     * ����������ʂP���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER1_LABEL = "����������ʂP";
    
    /**
     * (���i�R�[�h�Q���x��)<BR>
     * ���i�R�[�h�Q���x��<BR>
     */
    public static  String PRODUCTCODE2_LABEL = "���i�R�[�h�Q";
    
    /**
     * (����������ʂQ���x��)<BR>
     * ����������ʂQ���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER2_LABEL = "����������ʂQ";
    
    /**
     * (���i�R�[�h�R���x��)<BR>
     * ���i�R�[�h�R���x��<BR>
     */
    public static  String PRODUCTCODE3_LABEL = "���i�R�[�h�R";
    
    /**
     * (����������ʂR���x��)<BR>
     * ����������ʂR���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER3_LABEL = "����������ʂR";
    
    /**
     * (���i�R�[�h�S���x��)<BR>
     * ���i�R�[�h�S���x��<BR>
     */
    public static  String PRODUCTCODE4_LABEL = "���i�R�[�h�S";
    
    /**
     * (����������ʂS���x��)<BR>
     * ����������ʂS���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER4_LABEL = "����������ʂS";
    
    /**
     * (���i�R�[�h�T���x��)<BR>
     * ���i�R�[�h�T���x��<BR>
     */
    public static  String PRODUCTCODE5_LABEL = "���i�R�[�h�T";
    
    /**
     * (����������ʂT���x��)<BR>
     * ����������ʂT���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER5_LABEL = "����������ʂT";
    
    /**
     * (���i�R�[�h�U���x��)<BR>
     * ���i�R�[�h�U���x��<BR>
     */
    public static  String PRODUCTCODE6_LABEL = "���i�R�[�h�U";
    
    /**
     * (����������ʂU���x��)<BR>
     * ����������ʂU���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER6_LABEL = "����������ʂU";
    
    /**
     * (���i�R�[�h�V���x��)<BR>
     * ���i�R�[�h�V���x��<BR>
     */
    public static  String PRODUCTCODE7_LABEL = "���i�R�[�h�V";
    
    /**
     * (����������ʂV���x��)<BR>
     * ����������ʂV���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER7_LABEL = "����������ʂV";

    /**
     * (���i�R�[�h�W���x��)<BR>
     * ���i�R�[�h�W���x��<BR>
     */
    public static  String PRODUCTCODE8_LABEL = "���i�R�[�h�W";

    /**
     * (����������ʂW���x��)<BR>
     * ����������ʂW���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER8_LABEL = "����������ʂW";

    /**
     * (���i�R�[�h�X���x��)<BR>
     * ���i�R�[�h�X���x��<BR>
     */
    public static  String PRODUCTCODE9_LABEL = "���i�R�[�h�X";

    /**
     * (����������ʂX���x��)<BR>
     * ����������ʂX���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER9_LABEL = "����������ʂX";

    /**
     * (���i�R�[�h�P�O���x��)<BR>
     * ���i�R�[�h�P�O���x��<BR>
     */
    public static  String PRODUCTCODE10_LABEL = "���i�R�[�h�P�O";

    /**
     * (����������ʂP�O���x��)<BR>
     * ����������ʂP�O���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER10_LABEL = "����������ʂP�O";

    /**
     * (���i�R�[�h�P�P���x��)<BR>
     * ���i�R�[�h�P�P���x��<BR>
     */
    public static  String PRODUCTCODE11_LABEL = "���i�R�[�h�P�P";

    /**
     * (����������ʂP�P���x��)<BR>
     * ����������ʂP�P���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER11_LABEL = "����������ʂP�P";

    /**
     * (���i�R�[�h�P�Q���x��)<BR>
     * ���i�R�[�h�P�Q���x��<BR>
     */
    public static  String PRODUCTCODE12_LABEL = "���i�R�[�h�P�Q";

    /**
     * (����������ʂP�Q���x��)<BR>
     * ����������ʂP�Q���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER12_LABEL = "����������ʂP�Q";

    /**
     * (���i�R�[�h�P�R���x��)<BR>
     * ���i�R�[�h�P�R���x��<BR>
     */
    public static  String PRODUCTCODE13_LABEL = "���i�R�[�h�P�R";

    /**
     * (����������ʂP�R���x��)<BR>
     * ����������ʂP�R���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER13_LABEL = "����������ʂP�R";

    /**
     * (���i�R�[�h�P�S���x��)<BR>
     * ���i�R�[�h�P�S���x��<BR>
     */
    public static  String PRODUCTCODE14_LABEL = "���i�R�[�h�P�S";

    /**
     * (����������ʂP�S���x��)<BR>
     * ����������ʂP�S���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER14_LABEL = "����������ʂP�S";

    /**
     * (���i�R�[�h�P�T���x��)<BR>
     * ���i�R�[�h�P�T���x��<BR>
     */
    public static  String PRODUCTCODE15_LABEL = "���i�R�[�h�P�T";

    /**
     * (����������ʂP�T���x��)<BR>
     * ����������ʂP�T���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER15_LABEL = "����������ʂP�T";

    /**
     * (���i�R�[�h�P�U���x��)<BR>
     * ���i�R�[�h�P�U���x��<BR>
     */
    public static  String PRODUCTCODE16_LABEL = "���i�R�[�h�P�U";

    /**
     * (����������ʂP�U���x��)<BR>
     * ����������ʂP�U���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER16_LABEL = "����������ʂP�U";

    /**
     * (���i�R�[�h�P�V���x��)<BR>
     * ���i�R�[�h�P�V���x��<BR>
     */
    public static  String PRODUCTCODE17_LABEL = "���i�R�[�h�P�V";

    /**
     * (����������ʂP�V���x��)<BR>
     * ����������ʂP�V���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER17_LABEL = "����������ʂP�V";

    /**
     * (���i�R�[�h�P�W���x��)<BR>
     * ���i�R�[�h�P�W���x��<BR>
     */
    public static  String PRODUCTCODE18_LABEL = "���i�R�[�h�P�W";

    /**
     * (����������ʂP�W���x��)<BR>
     * ����������ʂP�W���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER18_LABEL = "����������ʂP�W";

    /**
     * (���i�R�[�h�P�X���x��)<BR>
     * ���i�R�[�h�P�X���x��<BR>
     */
    public static  String PRODUCTCODE19_LABEL = "���i�R�[�h�P�X";

    /**
     * (����������ʂP�X���x��)<BR>
     * ����������ʂP�X���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER19_LABEL = "����������ʂP�X";

    /**
     * (���i�R�[�h�Q�O���x��)<BR>
     * ���i�R�[�h�Q�O���x��<BR>
     */
    public static  String PRODUCTCODE20_LABEL = "���i�R�[�h�Q�O";

    /**
     * (����������ʂQ�O���x��)<BR>
     * ����������ʂQ�O���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER20_LABEL = "����������ʂQ�O";

    /**
     * (���i�R�[�h�Q�P���x��)<BR>
     * ���i�R�[�h�Q�P���x��<BR>
     */
    public static  String PRODUCTCODE21_LABEL = "���i�R�[�h�Q�P";

    /**
     * (����������ʂQ�P���x��)<BR>
     * ����������ʂQ�P���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER21_LABEL = "����������ʂQ�P";

    /**
     * (���i�R�[�h�Q�Q���x��)<BR>
     * ���i�R�[�h�Q�Q���x��<BR>
     */
    public static  String PRODUCTCODE22_LABEL = "���i�R�[�h�Q�Q";

    /**
     * (����������ʂQ�Q���x��)<BR>
     * ����������ʂQ�Q���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER22_LABEL = "����������ʂQ�Q";

    /**
     * (���i�R�[�h�Q�R���x��)<BR>
     * ���i�R�[�h�Q�R���x��<BR>
     */
    public static  String PRODUCTCODE23_LABEL = "���i�R�[�h�Q�R";

    /**
     * (����������ʂQ�R���x��)<BR>
     * ����������ʂQ�R���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER23_LABEL = "����������ʂQ�R";

    /**
     * (���i�R�[�h�Q�S���x��)<BR>
     * ���i�R�[�h�Q�S���x��<BR>
     */
    public static  String PRODUCTCODE24_LABEL = "���i�R�[�h�Q�S";

    /**
     * (����������ʂQ�S���x��)<BR>
     * ����������ʂQ�S���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER24_LABEL = "����������ʂQ�S";

    /**
     * (���i�R�[�h�Q�T���x��)<BR>
     * ���i�R�[�h�Q�T���x��<BR>
     */
    public static  String PRODUCTCODE25_LABEL = "���i�R�[�h�Q�T";

    /**
     * (����������ʂQ�T���x��)<BR>
     * ����������ʂQ�T���x��<BR>
     */
    public static  String ORDER_QUANTITY_UPPER25_LABEL = "����������ʂQ�T";

    /**
     * (�Ǘ���FX�����J�݃A�b�v���[�hCSV)<BR>
     * �R���X�g���N�^ <BR>
     * ���@@�A�b�v���[�h���~�̏ꍇ�Ɏg�p����B <BR>
     * <BR>
     * �|�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B <BR>
     * @@param l_lngUploadId - (�A�b�v���[�hID)<BR>
     * @@roseuid 43E2DD230241
     */
    public WEB3AdminFXAccOpenUploadCsv(long l_lngUploadId) 
    {
        super.administratorUploadId = l_lngUploadId;
    }
    
    /**
     * (�Ǘ���FX�����J�݃A�b�v���[�hCSV)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * this.create�J�����w�b�_()���R�[������B <BR>
     * @@roseuid 43E05A3D029D
     */
    public WEB3AdminFXAccOpenUploadCsv() 
    {
        this.createColumnHeader();
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()��<BR>
     * �ăC���X�^���X�ɃZ�b�g����B <BR>
     * <BR>
     * [�J�����w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���p�҃R�[�h���x��<BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���p�Җ����x��<BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���O�C��ID���x�� <BR>
     * �@@�J�����ԍ��F 2 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���O�C���p�X���[�h���x��<BR>
     * �@@�J�����ԍ��F 3 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.�����p�X���[�h���x��<BR>
     * �@@�J�����ԍ��F4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���[���A�h���X�P���x��<BR>
     * �@@�J�����ԍ��F 5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index =  6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���[���A�h���X�Q���x��<BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���Ȏ���敪���x��<BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 8<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���p�ґ������x��<BR>
     * �@@�J�����ԍ��F 8<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 9<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���ϕ��@@���x��<BR>
     * �@@�J�����ԍ��F 9<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 10<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.�萔���敪���x��<BR>
     * �@@�J�����ԍ��F 10<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 11<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���X�J�b�g�敪���x��<BR>
     * �@@�J�����ԍ��F 11<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 12<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����\�敪���x��<BR>
     * �@@�J�����ԍ��F 12<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 13<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.�d�q��t���������x��<BR>
     * �@@�J�����ԍ��F 13<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 14<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������m�F�����x��<BR>
     * �@@�J�����ԍ��F 14<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 15 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.������ԍ����x��<BR>
     * �@@�J�����ԍ��F 15 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 16 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���l���x��<BR>
     * �@@�J�����ԍ��F 16 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 17<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P���x��<BR>
     * �@@�J�����ԍ��F 17<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 18<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP���x��<BR>
     * �@@�J�����ԍ��F 18<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 19<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q���x��<BR>
     * �@@�J�����ԍ��F 19<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 20<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ���x��<BR>
     * �@@�J�����ԍ��F 20<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 21<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�R���x��<BR>
     * �@@�J�����ԍ��F 21<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 22<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂR���x��<BR>
     * �@@�J�����ԍ��F 22<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 23<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�S���x��<BR>
     * �@@�J�����ԍ��F 23<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 24<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂS���x��<BR>
     * �@@�J�����ԍ��F 24<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 25<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�T���x��<BR>
     * �@@�J�����ԍ��F 25<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 26<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂT���x��<BR>
     * �@@�J�����ԍ��F 26<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 27<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�U���x��<BR>
     * �@@�J�����ԍ��F 27<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 28<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂU���x��<BR>
     * �@@�J�����ԍ��F 28<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 29<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�V���x��<BR>
     * �@@�J�����ԍ��F 29<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 30<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂV���x��<BR>
     * �@@�J�����ԍ��F 30<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 31<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�W���x��<BR>
     * �@@�J�����ԍ��F 31<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 32<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂW���x��<BR>
     * �@@�J�����ԍ��F 32<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 33<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�X���x��<BR>
     * �@@�J�����ԍ��F 33<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 34<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂX���x��<BR>
     * �@@�J�����ԍ��F 34<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>        
     * <BR>
     * �|�@@index = 35<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�O���x��<BR>
     * �@@�J�����ԍ��F 35<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 36<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�O���x��<BR>
     * �@@�J�����ԍ��F 36<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>  
     * <BR>
     * �|�@@index = 37<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�P���x��<BR>
     * �@@�J�����ԍ��F 37<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 38<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�P���x��<BR>
     * �@@�J�����ԍ��F 38<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 39<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�Q���x��<BR>
     * �@@�J�����ԍ��F 39<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 40<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�Q���x��<BR>
     * �@@�J�����ԍ��F 40<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 41<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�R���x��<BR>
     * �@@�J�����ԍ��F 41<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 42<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�R���x��<BR>
     * �@@�J�����ԍ��F 42<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR> 
     * <BR>
     * �|�@@index = 43<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�S���x��<BR>
     * �@@�J�����ԍ��F 43<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 44<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�S���x��<BR>
     * �@@�J�����ԍ��F 44<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 45<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�T���x��<BR>
     * �@@�J�����ԍ��F 45<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 46<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�T���x��<BR>
     * �@@�J�����ԍ��F 46<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 47<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�U���x��<BR>
     * �@@�J�����ԍ��F 47<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 48<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�U���x��<BR>
     * �@@�J�����ԍ��F 48<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 49<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�V���x��<BR>
     * �@@�J�����ԍ��F 49<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 50<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�V���x��<BR>
     * �@@�J�����ԍ��F 50<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 51<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�W���x��<BR>
     * �@@�J�����ԍ��F 51<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 52<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�W���x��<BR>
     * �@@�J�����ԍ��F 52<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 53<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�X���x��<BR>
     * �@@�J�����ԍ��F 53<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 54<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�X���x��<BR>
     * �@@�J�����ԍ��F 54<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 55<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�O���x��<BR>
     * �@@�J�����ԍ��F 55<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 56<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�O���x��<BR>
     * �@@�J�����ԍ��F 56<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 57<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�P���x��<BR>
     * �@@�J�����ԍ��F 57<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 58<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�P���x��<BR>
     * �@@�J�����ԍ��F 58<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 59<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�Q���x��<BR>
     * �@@�J�����ԍ��F 59<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 60<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�Q���x��<BR>
     * �@@�J�����ԍ��F 60<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 61<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�R���x��<BR>
     * �@@�J�����ԍ��F 61<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 62<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�R���x��<BR>
     * �@@�J�����ԍ��F 62<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 63<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�S���x��<BR>
     * �@@�J�����ԍ��F 63<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 64<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�S���x��<BR>
     * �@@�J�����ԍ��F 64<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 65<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�T���x��<BR>
     * �@@�J�����ԍ��F 65<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * �|�@@index = 66<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�T���x��<BR>
     * �@@�J�����ԍ��F 66<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null <BR>
     * <BR>
     * @@roseuid 43E0569A00E8
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        List l_lisLabels = new ArrayList();
        
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���p�҃R�[�h���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.USER_CODE_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���p�Җ����x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.USER_NAME_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���O�C��ID���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.LOGINID_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���O�C���p�X���[�h���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.LOGIN_PASSWORD_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.�����p�X���[�h���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_PASSWORD_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���[���A�h���X�P���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.MAILADDRESS1_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���[���A�h���X�Q���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.MAILADDRESS2_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���Ȏ���敪���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.SELF_TRUSTDIV_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���p�ґ������x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.USER_ATTRIBUTE_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���ϕ��@@���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.SETTLEMENT_METHOD_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.�萔���敪���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.COMMISSIONDIV_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���X�J�b�g�敪���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.LOSSCUTDIV_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����\�敪���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.TRADINGDIV_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.�d�q��t���������x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ELECTRON_GRANT_PERMISSION_DAY_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������m�F�����x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.TRADE_OPERATING_MANUAL_CONFIRMATION_DAY_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.������ԍ����x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.AGREEMENT_BOOK_NUMBER_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���l���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.REMARK_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE1_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER1_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE2_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER2_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�R���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE3_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂR���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER3_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�S���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE4_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂS���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER4_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�T���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE5_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂT���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER5_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�U���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE6_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂU���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER6_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�V���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE7_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂV���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER7_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�W���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE8_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂW���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER8_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�X���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE9_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂX���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER9_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�O���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE10_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�O���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER10_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�P���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE11_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�P���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER11_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�Q���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE12_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�Q���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER12_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�R���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE13_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�R���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER13_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�S���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE14_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�S���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER14_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�T���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE15_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�T���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER15_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�U���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE16_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�U���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER16_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�V���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE17_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�V���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER17_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�W���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE18_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�W���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER18_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�X���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE19_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�X���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER19_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�O���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE20_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�O���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER20_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�P���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE21_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�P���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER21_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�Q���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE22_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�Q���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER22_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�R���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE23_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�R���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER23_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�S���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE24_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�S���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER24_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�T���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE25_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�T���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER25_LABEL);

        //CSV�J�������f���̔z��𐶐���
        WEB3GentradeCsvColumnModel[] l_columnModel = new WEB3GentradeCsvColumnModel[l_lisLabels.size()];

        for (int i = 0; i < l_lisLabels.size(); i++)
        {
            l_columnModel[i] = 
                new WEB3GentradeCsvColumnModel(
                    (String) l_lisLabels.get(i),
                    i,
                    WEB3GentradeCsvColumnModel.STRINGTYPE,
                    null);
        }
        
        //set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B
        this.setColumnHeader(l_columnModel);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�A�b�v���[�h�t�@@�C���h�c)<BR>
     * this.�A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR>
     * <BR>
     * ���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c��<BR>
     * �i�[���镶����<BR>
     * @@return String
     * @@roseuid 43E0675E029D
     */
    public String getUploadFileId() 
    {
        return this.UPLOAD_FILEID;
    }
    
    /**
     * (get�����^�C�v)<BR>
     * ProductTypeEnum.���� ��ԋp����B<BR>
     * @@return ProductTypeEnum
     * @@roseuid 43EFF41C0343
     */
    public ProductTypeEnum getProductType() 
    {
        return ProductTypeEnum.CASH;
    }
    
    /**
     * (validate���׍s)<BR>
     * ���׍s�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@���p�҃R�[�h�̃`�F�b�N<BR>
     * �@@get���p�҃R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���p�҃R�[�h()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * �@@<BR>
     * �@@�P�|�P�j�@@���p�҃R�[�h���擾�ł��Ȃ��ꍇ�iget���p�҃R�[�h() == null�j�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02366<BR>
     * <BR>
     * �@@�P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02367<BR>
     * <BR>
     * �@@�P�|�R�j�@@��������8byte�łȂ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02368<BR>
     * <BR>
     * �Q�j�@@���p�Җ��`�F�b�N<BR>
     * �@@get���p�Җ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���p�Җ�()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�j�@@���p�Җ����擾�ł����ꍇ<BR>
     * �@@�@@�Q�|�P�|�P�j�@@��������120byte���傫���ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02369<BR>
     * <BR>
     * �R�j�@@���O�C���h�c�̃`�F�b�N�@@<BR>
     * �@@get���O�C���h�c()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���O�C���h�c()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�R�|�P�j�@@���O�C���h�c���擾�ł��Ȃ��ꍇ�iget���O�C���h�c() == null�j�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02370<BR>
     * <BR>
     * �@@�R�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02371<BR>
     * <BR>
     * �@@�R�|�R�j�@@��������8byte�łȂ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02372<BR>
     * <BR>
     * �S�j�@@���O�C���p�X���[�h�̃`�F�b�N<BR>
     * �@@get���O�C���p�X���[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���O�C���p�X���[�h()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * �@@<BR>
     * �@@�S�|�P�j�@@���O�C���p�X���[�h���擾�ł����ꍇ<BR>
     * �@@�@@�S�|�P�|�P�j�@@��������8byte�ȏ�13byte�ȉ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_01915<BR>
     * <BR>
     * �T�j�@@�����p�X���[�h�̃`�F�b�N<BR>
     * �@@get�����p�X���[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get�����p�X���[�h()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * �@@<BR>
     * �@@�T�|�P�j�@@�����p�X���[�h���擾�ł����ꍇ<BR>
     * �@@�@@�T�|�P�|�P�j�@@��������8byte�ȏ�13byte�ȉ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_01915<BR>
     * <BR>
     * �U�j�@@���[���A�h���X�P�̃`�F�b�N<BR>
     * �@@get���[���A�h���X�P()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���[���A�h���X�P()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�U�|�P�j�@@���[���A�h���X�P���擾�ł����ꍇ<BR>
     * �@@�@@�U�|�P�|�P�j�@@��������50byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_01693<BR>
     * <BR>
     * �V�j�@@���[���A�h���X�Q�̃`�F�b�N<BR>
     * �@@get���[���A�h���X�Q()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���[���A�h���X�Q()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�V�|�P�j�@@���[���A�h���X�Q���擾�ł����ꍇ<BR>
     * �@@�@@�V�|�P�|�P�j�@@��������50byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_01693<BR>
     * <BR>
     * �W�j�@@���Ȏ���敪�̃`�F�b�N<BR>
     * �@@get���Ȏ���敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���Ȏ���敪()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�W�|�P�j�@@��������1byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02373<BR>
     * <BR>
     * �X�j�@@���p�ґ����̃`�F�b�N<BR>
     * �@@get���p�ґ���()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���p�ґ���()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�X�|�P�j�@@���p�ґ������擾�ł����ꍇ <BR>
     * �@@�@@�X�|�P�|�P�j ��������1byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_03111<BR>�@@�@@�@@�@@
     * <BR>
     * �P�O�j�@@���ϕ��@@�̃`�F�b�N<BR>
     * �@@get���ϕ��@@()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���ϕ��@@()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�P�O�|�P�j�@@��������1byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_03112<BR>
     * <BR>
     * �P�P�j�@@�萔���敪�̃`�F�b�N<BR>
     * �@@get�萔���敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get�萔���敪()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�P�P�|�P�j�@@��������2byte���傫���ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N<BR>
     * �@@get���X�J�b�g�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���X�J�b�g�敪()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�P�Q�|�P�j�@@��������2byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02374<BR>
     * <BR>
     * �P�R�j�@@����\�敪�̃`�F�b�N<BR>
     * �@@get����\�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����\�敪()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�P�R�|�P�j�@@��������1byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02376<BR>
     * <BR>
     * �P�S�j�@@�d�q��t�������̃`�F�b�N<BR>
     * �@@get�d�q��t������()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get�d�q��t������()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�P�S�|�P�j�@@�d�q��t���������擾�ł����ꍇ�B<BR>
     *    �@@�P�S�|�P�|�P�j ��������8byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_03113<BR>     
     * <BR>
     * �P�T�j�@@����������m�F���̃`�F�b�N<BR>
     * �@@get����������m�F��()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������m�F��()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�P�T�|�P�j�@@����������m�F�����擾�ł����ꍇ<BR>
     *    �@@�P�T�|�P�|�P�j ��������8byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_03114<BR> 
     * <BR>
     * �P�U�j�@@������ԍ��̃`�F�b�N<BR>
     * �@@get������ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get������ԍ�()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�P�U�|�P�j�@@��������10byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_03115<BR> 
     * <BR>
     * �P�V�j�@@���l�̃`�F�b�N<BR>
     * �@@get���l()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���l()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�P�V�|�P�j�@@���l���擾�ł��Ȃ��ꍇ�iget���l() == null�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02377<BR>
     * <BR>
     * �@@�P�V�|�Q�j�@@��������9byte�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_00487<BR>
     * <BR>
     * �P�W�j�@@���i�R�[�h�P�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�P()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���i�R�[�h�P()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�P�W�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �P�X�j�@@����������ʂP�̃`�F�b�N<BR>
     * �@@get����������ʂP()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂP()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�P�X�|�P�j�@@����������ʂP���擾�ł����ꍇ<BR>
     * �@@�@@�P�X�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�P�X�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �Q�O�j�@@���i�R�[�h�Q�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�Q()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���i�R�[�h�Q()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�Q�O�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �Q�P�j�@@����������ʂQ�̃`�F�b�N<BR>
     * �@@get����������ʂQ()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂQ()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�Q�P�|�P�j�@@����������ʂQ���擾�ł����ꍇ<BR>
     * �@@�@@�Q�P�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�Q�P�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �Q�Q�j�@@���i�R�[�h�R�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�R()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���i�R�[�h�R()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�Q�Q�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �Q�R�j�@@����������ʂR�̃`�F�b�N<BR>
     * �@@get����������ʂR()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂR()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�Q�R�|�P�j�@@����������ʂR���擾�ł����ꍇ<BR>
     * �@@�@@�Q�R�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�Q�R�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �Q�S�j�@@���i�R�[�h�S�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�S()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���i�R�[�h�S()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�Q�S�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �Q�T�j�@@����������ʂS�̃`�F�b�N<BR>
     * �@@get����������ʂS()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂS()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�Q�T�|�P�j�@@����������ʂS���擾�ł����ꍇ<BR>
     * �@@�@@�Q�T�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�Q�T�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �Q�U�j�@@���i�R�[�h�T�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�T()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���i�R�[�h�T()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�Q�U�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �Q�V�j�@@����������ʂT�̃`�F�b�N<BR>
     * �@@get����������ʂT()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂT()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�Q�V�|�P�j�@@����������ʂT���擾�ł����ꍇ<BR>
     * �@@�@@�Q�V�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�Q�V�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �Q�W�j�@@���i�R�[�h�U�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�U()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���i�R�[�h�U()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�Q�W�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �Q�X�j�@@����������ʂU�̃`�F�b�N<BR>
     * �@@get����������ʂU()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂU()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�Q�X�|�P�j�@@����������ʂU���擾�ł����ꍇ<BR>
     * �@@�@@�Q�X�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�Q�X�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �R�O�j�@@���i�R�[�h�V�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�V()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���i�R�[�h�V()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�R�O�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �R�P�j�@@����������ʂV�̃`�F�b�N<BR>
     * �@@get����������ʂV()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂV()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�R�P�|�P�j�@@����������ʂV���擾�ł����ꍇ<BR>
     * �@@�@@�R�P�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�R�P�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �R�Q�j�@@���i�R�[�h�W�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�W()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���i�R�[�h�W()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�R�Q�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �R�R�j�@@����������ʂW�̃`�F�b�N<BR>
     * �@@get����������ʂW()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂW()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�R�R�|�P�j�@@����������ʂW���擾�ł����ꍇ<BR>
     * �@@�@@�R�R�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�R�R�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �R�S�j�@@���i�R�[�h�X�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�X()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�X()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�R�S�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �R�T�j�@@����������ʂX�̃`�F�b�N<BR>
     * �@@get����������ʂX()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂX()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�R�T�|�P�j�@@����������ʂX���擾�ł����ꍇ<BR>
     * �@@�@@�R�T�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�R�T�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �R�U�j�@@���i�R�[�h�P�O�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�P�O()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�P�O()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�R�U�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �R�V�j�@@����������ʂP�O�̃`�F�b�N<BR>
     * �@@get����������ʂP�O()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂP�O()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�R�V�|�P�j�@@����������ʂP�O���擾�ł����ꍇ<BR>
     * �@@�@@�R�V�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�R�V�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �R�W�j�@@���i�R�[�h�P�P�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�P�P()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�P�P()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�R�W�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �R�X�j�@@����������ʂP�P�̃`�F�b�N<BR>
     * �@@get����������ʂP�P()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂP�P()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�R�X�|�P�j�@@����������ʂP�P���擾�ł����ꍇ<BR>
     * �@@�@@�R�X�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�R�X�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �S�O�j�@@���i�R�[�h�P�Q�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�P�Q()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�P�Q()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�S�O�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �S�P�j�@@����������ʂP�Q�̃`�F�b�N<BR>
     * �@@get����������ʂP�Q()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂP�Q()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�S�P�|�P�j�@@����������ʂP�Q���擾�ł����ꍇ<BR>
     * �@@�@@�S�P�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�S�P�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �S�Q�j�@@���i�R�[�h�P�R�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�P�R()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�P�R()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�S�Q�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �S�R�j�@@����������ʂP�R�̃`�F�b�N<BR>
     * �@@get����������ʂP�R()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂP�R()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�S�R�|�P�j�@@����������ʂP�R���擾�ł����ꍇ<BR>
     * �@@�@@�S�R�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�S�R�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �S�S�j�@@���i�R�[�h�P�S�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�P�S()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�P�S()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�S�S�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �S�T�j�@@����������ʂP�S�̃`�F�b�N<BR>
     * �@@get����������ʂP�S()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂP�S()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�S�T�|�P�j�@@����������ʂP�S���擾�ł����ꍇ<BR>
     * �@@�@@�S�T�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�S�T�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �S�U�j�@@���i�R�[�h�P�T�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�P�T()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�P�T()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�S�U�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �S�V�j�@@����������ʂP�T�̃`�F�b�N<BR>
     * �@@get����������ʂP�T()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂP�T()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�S�V�|�P�j�@@����������ʂP�T���擾�ł����ꍇ<BR>
     * �@@�@@�S�V�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�S�V�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �S�W�j�@@���i�R�[�h�P�U�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�P�U()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�P�U()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�S�W�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �S�X�j�@@����������ʂP�U�̃`�F�b�N<BR>
     * �@@get����������ʂP�U()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂP�U()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�S�X�|�P�j�@@����������ʂP�U���擾�ł����ꍇ<BR>
     * �@@�@@�S�X�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�S�X�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �T�O�j�@@���i�R�[�h�P�V�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�P�V()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�P�V()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�T�O�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �T�P�j�@@����������ʂP�V�̃`�F�b�N<BR>
     * �@@get����������ʂP�V()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂP�V()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�T�P�|�P�j�@@����������ʂP�V���擾�ł����ꍇ<BR>
     * �@@�@@�T�P�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�T�P�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �T�Q�j�@@���i�R�[�h�P�W�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�P�W()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�P�W()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�T�Q�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �T�R�j�@@����������ʂP�W�̃`�F�b�N<BR>
     * �@@get����������ʂP�W()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂP�W()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�T�R�|�P�j�@@����������ʂP�W���擾�ł����ꍇ<BR>
     * �@@�@@�T�R�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�T�R�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �T�S�j�@@���i�R�[�h�P�X�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�P�X()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�P�X()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�T�S�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �T�T�j�@@����������ʂP�X�̃`�F�b�N<BR>
     * �@@get����������ʂP�X()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂP�X()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�T�T�|�P�j�@@����������ʂP�X���擾�ł����ꍇ<BR>
     * �@@�@@�T�T�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�T�T�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �T�U�j�@@���i�R�[�h�Q�O�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�Q�O()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�Q�O()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�T�U�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �T�V�j�@@����������ʂQ�O�̃`�F�b�N<BR>
     * �@@get����������ʂQ�O()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂQ�O()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�T�V�|�P�j�@@����������ʂQ�O���擾�ł����ꍇ<BR>
     * �@@�@@�T�V�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�T�V�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �T�W�j�@@���i�R�[�h�Q�P�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�Q�P()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���i�R�[�h�Q�P()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�T�W�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �T�X�j�@@����������ʂQ�P�̃`�F�b�N<BR>
     * �@@get����������ʂQ�P()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂQ�P()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�T�X�|�P�j�@@����������ʂQ�P���擾�ł����ꍇ<BR>
     * �@@�@@�T�X�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�T�X�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �U�O�j�@@���i�R�[�h�Q�Q�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�Q�Q()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�Q�Q()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�U�O�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �U�P�j�@@����������ʂQ�Q�̃`�F�b�N<BR>
     * �@@get����������ʂQ�Q()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂQ�Q()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�U�P�|�P�j�@@����������ʂQ�Q���擾�ł����ꍇ<BR>
     * �@@�@@�U�P�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�U�P�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �U�Q�j�@@���i�R�[�h�Q�R�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�Q�R()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�Q�R()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�U�Q�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �U�R�j�@@����������ʂQ�R�̃`�F�b�N<BR>
     * �@@get����������ʂQ�R()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂQ�R()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�U�R�|�P�j�@@����������ʂQ�R���擾�ł����ꍇ<BR>
     * �@@�@@�U�R�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�U�R�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �U�S�j�@@���i�R�[�h�Q�S�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�Q�S()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�Q�S()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�U�S�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �U�T�j�@@����������ʂQ�S�̃`�F�b�N<BR>
     * �@@get����������ʂQ�S()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂQ�S()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�U�T�|�P�j�@@����������ʂQ�S���擾�ł����ꍇ<BR>
     * �@@�@@�U�T�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�U�T�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * �U�U�j�@@���i�R�[�h�Q�T�̃`�F�b�N<BR>
     * �@@get���i�R�[�h�Q�T()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���i�R�[�h�Q�T()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�U�U�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * �U�V�j�@@����������ʂQ�T�̃`�F�b�N<BR>
     * �@@get����������ʂQ�T()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����������ʂQ�T()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�U�V�|�P�j�@@����������ʂQ�T���擾�ł����ꍇ<BR>
     * �@@�@@�U�V�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * �@@�@@�U�V�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43E088AB01C2
     */
    public void validateDetailsLine(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDetailsLine(int)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���p�҃R�[�h�̃`�F�b�N
        //�@@get���p�҃R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���p�҃R�[�h()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strUserCode = this.getUserCode(l_intLineNumber);
        //�@@�P�|�P�j�@@���p�҃R�[�h���擾�ł��Ȃ��ꍇ�iget���p�҃R�[�h() == null�j�A
        //           ��O���X���[����B 
        if (l_strUserCode == null)
        {
            log.debug("���p�҃R�[�h���擾�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02366,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���p�҃R�[�h���擾�ł��܂���B");
        }
            
        //�@@�P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�A��O���X���[����B 
        if (!WEB3StringTypeUtility.isDigit(l_strUserCode))
        {
            log.debug("���p�҃R�[�h�̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02367,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���p�҃R�[�h�̒l�����p�����ȊO�̒l�ł��B");
        }
            
        //�@@�P�|�R�j�@@��������8byte�łȂ��ꍇ�A��O���X���[����B 
        if (l_strUserCode.length() != 8)
        {
            log.debug("���p�҃R�[�h�̒l��8byte�������ł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02368,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���p�҃R�[�h�̒l��8byte�������ł͂���܂���B");
        }

        //�Q�j�@@���p�Җ��`�F�b�N
        //�@@get���p�Җ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���p�Җ�()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strUserName = this.getUserName(l_intLineNumber);
        //�@@�Q�|�P�j�@@���p�Җ����擾�ł����ꍇ
        //�@@�@@�Q�|�P�|�P�j�@@��������120byte���傫���ꍇ�A��O���X���[����B 
        if (l_strUserName != null && l_strUserName.getBytes().length > 120)
        {
            log.debug("���p�Җ��̕�������120byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02369,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���p�Җ��̕�������120byte���傫���ł��B");
        }
        
        //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
        //�@@get���O�C���h�c()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���O�C���h�c()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strLoginID = this.getLoginId(l_intLineNumber);
        //�@@�R�|�P�j�@@���O�C���h�c���擾�ł��Ȃ��ꍇ�iget���O�C���h�c() == null�j�A
        //           ��O���X���[����B 
        if (l_strLoginID == null)
        {
            log.debug("���O�C���h�c���擾�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02370,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���O�C���h�c���擾�ł��܂���B");
        }

        //�@@�R�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�A��O���X���[����B 
        if (!WEB3StringTypeUtility.isDigit(l_strLoginID))
        {
            log.debug("���O�C���h�c�̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02371,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���O�C���h�c�̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�R�|�R�j�@@��������8byte�łȂ��ꍇ�A��O���X���[����B         
        if (l_strLoginID.length() != 8)
        {
            log.debug("���O�C���h�c�̒l��8byte�������ł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02372,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���O�C���h�c�̒l��8byte�������ł͂���܂���B");
        }
        
        //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
        //�@@get���O�C���p�X���[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���O�C���p�X���[�h()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strLoginPassword = this.getLoginPassword(l_intLineNumber);
        //�@@�S�|�P�j�@@���O�C���p�X���[�h���擾�ł����ꍇ
        //�@@�@@�S�|�P�|�P�j�@@��������8byte�ȏ�13byte�ȉ��łȂ��ꍇ�A
        //                 ��O���X���[����B        
        if (l_strLoginPassword != null 
                && (l_strLoginPassword.getBytes().length < 8 || l_strLoginPassword.getBytes().length > 13))
        {
            log.debug("�p�X���[�h�i������j�̒������s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01915,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�X���[�h�i������j�̒������s���ł��B");
        }
        
        //�T�j�@@�����p�X���[�h�̃`�F�b�N
        //�@@get�����p�X���[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get�����p�X���[�h()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderPassword = this.getOrderPassword(l_intLineNumber);
        //�@@�T�|�P�j�@@�����p�X���[�h���擾�ł����ꍇ
        //�@@�@@�T�|�P�|�P�j�@@��������8byte�ȏ�13byte�ȉ��łȂ��ꍇ�A
        //                 ��O���X���[����B        
        if (l_strOrderPassword != null 
                && (l_strOrderPassword.getBytes().length < 8 || l_strOrderPassword.getBytes().length > 13))
        {
            log.debug("�p�X���[�h�i������j�̒������s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01915,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�X���[�h�i������j�̒������s���ł��B");
        }
        
        //�U�j�@@���[���A�h���X�P�̃`�F�b�N
        //�@@get���[���A�h���X�P()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���[���A�h���X�P()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strMailAddress1 = this.getMailAddress1(l_intLineNumber);
        //�@@�U�|�P�j�@@���[���A�h���X�P���擾�ł����ꍇ
        //�@@�@@�U�|�P�|�P�j�@@��������50byte���傫���ꍇ�A��O���X���[����B
        if (l_strMailAddress1 != null && l_strMailAddress1.getBytes().length > 50)
        {
            log.debug("���[���A�h���X�̌������������z���Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01693,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���[���A�h���X�̌������������z���Ă��܂��B");
        }

        //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
        //�@@get���[���A�h���X�Q()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���[���A�h���X�Q()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strMailAddress2 = this.getMailAddress2(l_intLineNumber);
        //�@@�V�|�P�j�@@���[���A�h���X�Q���擾�ł����ꍇ
        //�@@�@@�V�|�P�|�P�j�@@��������50byte���傫���ꍇ�A��O���X���[����B
        if (l_strMailAddress2 != null && l_strMailAddress2.getBytes().length > 50)
        {
            log.debug("���[���A�h���X�̌������������z���Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01693,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���[���A�h���X�̌������������z���Ă��܂��B");
        }

        //�W�j�@@���Ȏ���敪�̃`�F�b�N
        //�@@get���Ȏ���敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���Ȏ���敪()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strSelfTurstDiv = this.getSelfTrustDiv(l_intLineNumber);
        //�@@�W�|�P�j�@@��������1byte���傫���ꍇ�A��O���X���[����B        
        if (l_strSelfTurstDiv != null && l_strSelfTurstDiv.getBytes().length > 1)
        {
            log.debug("���Ȏ���敪�̒l��1byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02373,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���Ȏ���敪�̒l��1byte���傫���ł��B");
        }

        //�X�j�@@���p�ґ����̃`�F�b�N
        //�@@get���p�ґ���()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���p�ґ���()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strUserAttribute = this.getUserAttribute(l_intLineNumber);
        //�@@�X�|�P�j�@@���p�ґ������擾�ł����ꍇ
        //�@@�@@�X�|�P�|�P�j�@@��������1byte���傫���ꍇ�A��O���X���[����B
        if (l_strUserAttribute != null && l_strUserAttribute.getBytes().length > 1)
        {
        	log.debug("���p�ґ����̕�������1byte���傫���ł��B");
        	log.exiting(STR_METHOD_NAME);
        	throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03111,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���p�ґ����̕�������1byte���傫���ł��B");
        }

        //�P�O�j�@@���ϕ��@@�̃`�F�b�N
        //�@@get���ϕ��@@()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���ϕ��@@()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strSettlementMethod = this.getSettlementMethod(l_intLineNumber);
        //�@@�P�O�|�P�j�@@��������1byte���傫���ꍇ�A��O���X���[����B
        if(l_strSettlementMethod != null && l_strSettlementMethod.getBytes().length > 1)
        {
        	log.debug("���ϕ��@@�̕�������1byte���傫���ł��B");
        	log.exiting(STR_METHOD_NAME);
        	throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03112,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ϕ��@@�̕�������1byte���傫���ł��B");
        }

        //�P�P�j�@@�萔���敪�̃`�F�b�N
        //�@@get�萔���敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get�萔���敪()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strCommissionDiv = this.getCommissionDiv(l_intLineNumber);
        //�@@�P�P�|�P�j�@@��������2byte���傫���ꍇ�A��O���X���[����B
        if (l_strCommissionDiv != null && l_strCommissionDiv.getBytes().length > 2)
        {
            log.debug("�萔���敪�̒l��2byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02375,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�萔���敪�̒l��2byte���傫���ł��B");
        }

        //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
        //�@@get���X�J�b�g�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���X�J�b�g�敪()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strLossCutDiv = this.getLossCutDiv(l_intLineNumber);
        //�@@�P�Q�|�P�j�@@��������2byte���傫���ꍇ�A��O���X���[����B        
        if (l_strLossCutDiv != null && l_strLossCutDiv.getBytes().length > 2)
        {
            log.debug("���X�J�b�g�敪�̒l��2byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02374,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�J�b�g�敪�̒l��2byte���傫���ł��B");
        }

        //�P�R�j�@@����\�敪�̃`�F�b�N
        //�@@get����\�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����\�敪()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strTradingDiv = this.getTradingDiv(l_intLineNumber);
        //�@@�P�R�|�P�j�@@��������1byte���傫���ꍇ�A��O���X���[����B        
        if (l_strTradingDiv != null && l_strTradingDiv.getBytes().length > 1)
        {
            log.debug("����\�敪�̒l��1byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02376,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����\�敪�̒l��1byte���傫���ł��B");
        }

        //�P�S�j�@@�d�q��t�������̃`�F�b�N
        //�@@get�d�q��t������()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get�d�q��t������()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strElectronGrantPermissionDay = this.getElectronGrantPermissionDay(l_intLineNumber);
        //�@@�P�S�|�P�j�@@�d�q��t���������擾�ł����ꍇ
        //    �P�S�|�P�|�P�j ��������8byte���傫���ꍇ�A��O���X���[����B
        if (l_strElectronGrantPermissionDay != null && l_strElectronGrantPermissionDay.getBytes().length > 8)
        {
            log.debug("�d�q��t�������̒l��8byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03113,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q��t�������̒l��8byte���傫���ł��B");
        }

        //�P�T�j�@@����������m�F���̃`�F�b�N
        //�@@get����������m�F��()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������m�F��()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strTradeOperatingManualConfirmationDay = this.getTradeOperatingManualConfirmationDay(l_intLineNumber);
        //�@@�P�T�|�P�j�@@����������m�F�����擾�ł����ꍇ
        //    �P�T�|�P�|�P�j ��������8byte���傫���ꍇ�A��O���X���[����B
        if (l_strTradeOperatingManualConfirmationDay != null && l_strTradeOperatingManualConfirmationDay.getBytes().length > 8)
        {
            log.debug("����������m�F���̕�������8byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03114,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������m�F���̕�������8byte���傫���ł��B");
        }

        //�P�U�j�@@������ԍ��̃`�F�b�N
        //�@@get������ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get������ԍ�()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strAgreementBookNumber = this.getAgreementBookNumber(l_intLineNumber);
        //�@@�P�U�|�P�j�@@��������10byte���傫���ꍇ�A��O���X���[����B
        if (l_strAgreementBookNumber != null && l_strAgreementBookNumber.getBytes().length > 10)
        {
            log.debug("������ԍ��̕�������10byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03115,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������ԍ��̕�������10byte���傫���ł��B");
        }

        //�P�V�j�@@���l�̃`�F�b�N
        //�@@get���l()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���l()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strRemark = this.getRemark(l_intLineNumber);
        //�@@�P�V�|�P�j�@@���l���擾�ł��Ȃ��ꍇ�iget���l() == null�j�A
        //             ��O���X���[����B 
        if (l_strRemark == null)
        {
            log.debug("���l���擾�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���l���擾�ł��܂���B");
        }
        
        //�@@�P�V�|�Q�j�@@��������9byte�łȂ��ꍇ�A��O���X���[����B        
        if (l_strRemark.getBytes().length != 9)
        {
            log.debug("���l�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00487,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���l�̃T�C�Y���s���ł��B");
        }
       
        //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N
        //�@@get���i�R�[�h�P()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�P()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode1 = this.getProductCode1(l_intLineNumber);
        //�@@�P�W�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B        
        if (l_strProductCode1 != null && l_strProductCode1.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }
        
        //�P�X�j�@@����������ʂP�̃`�F�b�N
        //�@@get����������ʂP()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂP()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper1 = this.getOrderQuantityUpper1(l_intLineNumber);
        //�@@�P�X�|�P�j�@@����������ʂP���擾�ł����ꍇ
        //�@@�@@�P�X�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B 
        if (l_strOrderQuantityUpper1 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper1))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }
        //�@@�@@�P�X�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B        
        if (l_strOrderQuantityUpper1 != null && l_strOrderQuantityUpper1.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }
        
        //�Q�O�j�@@���i�R�[�h�Q�̃`�F�b�N
        //�@@get���i�R�[�h�Q()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�Q()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode2 = this.getProductCode2(l_intLineNumber);
        //�@@�Q�O�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode2 != null && l_strProductCode2.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }
        
        //�Q�P�j�@@����������ʂQ�̃`�F�b�N
        //�@@get����������ʂQ()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂQ()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper2 = this.getOrderQuantityUpper2(l_intLineNumber);
        //�@@�Q�P�|�P�j�@@����������ʂQ���擾�ł����ꍇ
        //�@@�@@�Q�P�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B 
        if (l_strOrderQuantityUpper2 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper2))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }
        //�@@�@@�Q�P�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper2 != null && l_strOrderQuantityUpper2.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }
        
        //�Q�Q�j�@@���i�R�[�h�R�̃`�F�b�N
        //�@@get���i�R�[�h�R()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�R()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode3 = this.getProductCode3(l_intLineNumber);
        //�@@�Q�Q�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode3 != null && l_strProductCode3.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }
        
        //�Q�R�j�@@����������ʂR�̃`�F�b�N
        //�@@get����������ʂR()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂR()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper3 = this.getOrderQuantityUpper3(l_intLineNumber);
        //�@@�Q�R�|�P�j�@@����������ʂR���擾�ł����ꍇ
        //�@@�@@�Q�R�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B 
        if (l_strOrderQuantityUpper3 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper3))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�Q�R�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B        
        if (l_strOrderQuantityUpper3 != null && l_strOrderQuantityUpper3.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }
        
        //�Q�S�j�@@���i�R�[�h�S�̃`�F�b�N
        //�@@get���i�R�[�h�S()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�S()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode4 = this.getProductCode4(l_intLineNumber);
        //�@@�Q�S�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode4 != null && l_strProductCode4.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }
        
        //�Q�T�j�@@����������ʂS�̃`�F�b�N
        //�@@get����������ʂS()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂS()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper4 = this.getOrderQuantityUpper4(l_intLineNumber);
        //�@@�Q�T�|�P�j�@@����������ʂS���擾�ł����ꍇ
        //�@@�@@�Q�T�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B 
        if (l_strOrderQuantityUpper4 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper4))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�Q�T�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B        
        if (l_strOrderQuantityUpper4 != null && l_strOrderQuantityUpper4.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }
        
        //�Q�U�j�@@���i�R�[�h�T�̃`�F�b�N
        //�@@get���i�R�[�h�T()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�T()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode5 = this.getProductCode5(l_intLineNumber);
        //�@@�Q�U�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode5 != null && l_strProductCode5.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�Q�V�j�@@����������ʂT�̃`�F�b�N
        //�@@get����������ʂT()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂT()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper5 = this.getOrderQuantityUpper5(l_intLineNumber);
        //�Q�V�|�P�j�@@����������ʂT���擾�ł����ꍇ
        //�@@�Q�V�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B 
        if (l_strOrderQuantityUpper5 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper5))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�Q�V�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper5 != null && l_strOrderQuantityUpper5.getBytes().length > 15)    
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�Q�W�j�@@���i�R�[�h�U�̃`�F�b�N
        //�@@get���i�R�[�h�U()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�U()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode6 = this.getProductCode6(l_intLineNumber);
        //�@@�Q�W�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode6 != null && l_strProductCode6.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�Q�X�j�@@����������ʂU�̃`�F�b�N
        //�@@get����������ʂU()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂU()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper6 = this.getOrderQuantityUpper6(l_intLineNumber);
        //�@@�Q�X�|�P�j�@@����������ʂU���擾�ł����ꍇ
        //�@@�@@�Q�X�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B 
        if (l_strOrderQuantityUpper6 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper6))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�Q�X�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B         
        if (l_strOrderQuantityUpper6 != null && l_strOrderQuantityUpper6.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }
         
        //�R�O�j�@@���i�R�[�h�V�̃`�F�b�N
        //�@@get���i�R�[�h�V()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�V()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode7 = this.getProductCode7(l_intLineNumber);
        //�@@�R�O�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode7 != null && l_strProductCode7.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�R�P�j�@@����������ʂV�̃`�F�b�N
        //�@@get����������ʂV()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂV()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper7 = this.getOrderQuantityUpper7(l_intLineNumber);
        //�@@�R�P�|�P�j�@@����������ʂV���擾�ł����ꍇ
        //�@@�@@�R�P�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B 
        if (l_strOrderQuantityUpper7 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper7))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�R�P�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper7 != null && l_strOrderQuantityUpper7.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�R�Q�j�@@���i�R�[�h�W�̃`�F�b�N
        //�@@get���i�R�[�h�W()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�W()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode8 = this.getProductCode8(l_intLineNumber);
        //�@@�R�Q�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode8 != null && l_strProductCode8.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�R�R�j�@@����������ʂW�̃`�F�b�N
        //�@@get����������ʂW()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂW()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper8 = this.getOrderQuantityUpper8(l_intLineNumber);
        //�@@�R�R�|�P�j�@@����������ʂW���擾�ł����ꍇ
        //�@@�@@�R�R�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper8 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper8))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�R�R�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper8 != null && l_strOrderQuantityUpper8.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�R�S�j�@@���i�R�[�h�X�̃`�F�b�N
        //�@@get���i�R�[�h�X()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�X()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode9 = this.getProductCode9(l_intLineNumber);
        //�@@�R�S�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode9 != null && l_strProductCode9.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�R�T�j�@@����������ʂX�̃`�F�b�N
        //�@@get����������ʂX()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂX()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper9 = this.getOrderQuantityUpper9(l_intLineNumber);
        //�@@�R�T�|�P�j�@@����������ʂX���擾�ł����ꍇ
        //�@@�@@�R�T�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper9 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper9))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�R�T�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper9 != null && l_strOrderQuantityUpper9.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�R�U�j�@@���i�R�[�h�P�O�̃`�F�b�N
        //�@@get���i�R�[�h�P�O()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�P�O()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode10 = this.getProductCode10(l_intLineNumber);
        //�@@�R�U�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode10 != null && l_strProductCode10.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�R�V�j�@@����������ʂP�O�̃`�F�b�N
        //�@@get����������ʂP�O()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂP�O()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper10 = this.getOrderQuantityUpper10(l_intLineNumber);
        //�@@�R�V�|�P�j�@@����������ʂP�O���擾�ł����ꍇ
        //�@@�@@�R�V�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper10 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper10))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�R�V�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper10 != null && l_strOrderQuantityUpper10.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�R�W�j�@@���i�R�[�h�P�P�̃`�F�b�N
        //�@@get���i�R�[�h�P�P()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�P�P()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode11 = this.getProductCode11(l_intLineNumber);
        //�@@�R�W�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode11 != null && l_strProductCode11.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�R�X�j�@@����������ʂP�P�̃`�F�b�N
        //�@@get����������ʂP�P()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂP�P()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper11 = this.getOrderQuantityUpper11(l_intLineNumber);
        //�@@�R�X�|�P�j�@@����������ʂP�P���擾�ł����ꍇ
        //�@@�@@�R�X�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper11 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper11))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�R�X�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper11 != null && l_strOrderQuantityUpper11.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�S�O�j�@@���i�R�[�h�P�Q�̃`�F�b�N
        //�@@get���i�R�[�h�P�Q()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�P�Q()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode12 = this.getProductCode12(l_intLineNumber);
        //�@@�S�O�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode12 != null && l_strProductCode12.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�S�P�j�@@����������ʂP�Q�̃`�F�b�N
        //�@@get����������ʂP�Q()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂP�Q()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper12 = this.getOrderQuantityUpper12(l_intLineNumber);
        //�@@�S�P�|�P�j�@@����������ʂP�Q���擾�ł����ꍇ
        //�@@�@@�S�P�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper12 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper12))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�S�P�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper12 != null && l_strOrderQuantityUpper12.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�S�Q�j�@@���i�R�[�h�P�R�̃`�F�b�N
        //�@@get���i�R�[�h�P�R()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�P�R()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode13 = this.getProductCode13(l_intLineNumber);
        //�@@�S�Q�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode13 != null && l_strProductCode13.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�S�R�j�@@����������ʂP�R�̃`�F�b�N
        //�@@get����������ʂP�R()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂP�R()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper13 = this.getOrderQuantityUpper13(l_intLineNumber);
        //�@@�S�R�|�P�j�@@����������ʂP�R���擾�ł����ꍇ
        //�@@�@@�S�R�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper13 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper13))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�S�R�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper13 != null && l_strOrderQuantityUpper13.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�S�S�j�@@���i�R�[�h�P�S�̃`�F�b�N
        //�@@get���i�R�[�h�P�S()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�P�S()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode14 = this.getProductCode14(l_intLineNumber);
        //�@@�S�S�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode14 != null && l_strProductCode14.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�S�T�j�@@����������ʂP�S�̃`�F�b�N
        //�@@get����������ʂP�S()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂP�S()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper14 = this.getOrderQuantityUpper14(l_intLineNumber);
        //�@@�S�T�|�P�j�@@����������ʂP�S���擾�ł����ꍇ
        //�@@�@@�S�T�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper14 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper14))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�S�T�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper14 != null && l_strOrderQuantityUpper14.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�S�U�j�@@���i�R�[�h�P�T�̃`�F�b�N
        //�@@get���i�R�[�h�P�T()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�P�T()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode15 = this.getProductCode15(l_intLineNumber);
        //�@@�S�U�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode15 != null && l_strProductCode15.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�S�V�j�@@����������ʂP�T�̃`�F�b�N
        //�@@get����������ʂP�T()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂP�T()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper15 = this.getOrderQuantityUpper15(l_intLineNumber);
        //�@@�S�V�|�P�j�@@����������ʂP�T���擾�ł����ꍇ
        //�@@�@@�S�V�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper15 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper15))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�S�V�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper15 != null && l_strOrderQuantityUpper15.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�S�W�j�@@���i�R�[�h�P�U�̃`�F�b�N
        //�@@get���i�R�[�h�P�U()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�P�U()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode16 = this.getProductCode16(l_intLineNumber);
        //�@@�S�W�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode16 != null && l_strProductCode16.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�S�X�j�@@����������ʂP�U�̃`�F�b�N
        //�@@get����������ʂP�U()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂP�U()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper16 = this.getOrderQuantityUpper16(l_intLineNumber);
        //�@@�S�X�|�P�j�@@����������ʂP�U���擾�ł����ꍇ
        //�@@�@@�S�X�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper16 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper16))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�S�X�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper16 != null && l_strOrderQuantityUpper16.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�T�O�j�@@���i�R�[�h�P�V�̃`�F�b�N
        //�@@get���i�R�[�h�P�V()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�P�V()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode17 = this.getProductCode17(l_intLineNumber);
        //�@@�T�O�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode17 != null && l_strProductCode17.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�T�P�j�@@����������ʂP�V�̃`�F�b�N
        //�@@get����������ʂP�V()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂP�V()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper17 = this.getOrderQuantityUpper17(l_intLineNumber);
        //�@@�T�P�|�P�j�@@����������ʂP�V���擾�ł����ꍇ
        //�@@�@@�T�P�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper17 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper17))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�T�P�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper17 != null && l_strOrderQuantityUpper17.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�T�Q�j�@@���i�R�[�h�P�W�̃`�F�b�N
        //�@@get���i�R�[�h�P�W()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�P�W()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode18 = this.getProductCode18(l_intLineNumber);
        //�@@�T�Q�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode18 != null && l_strProductCode18.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�T�R�j�@@����������ʂP�W�̃`�F�b�N
        //�@@get����������ʂP�W()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂP�W()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper18 = this.getOrderQuantityUpper18(l_intLineNumber);
        //�@@�T�R�|�P�j�@@����������ʂP�W���擾�ł����ꍇ
        //�@@�@@�T�R�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper18 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper18))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�T�R�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper18 != null && l_strOrderQuantityUpper18.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�T�S�j�@@���i�R�[�h�P�X�̃`�F�b�N
        //�@@get���i�R�[�h�P�X()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�P�X()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode19 = this.getProductCode19(l_intLineNumber);
        //�@@�T�S�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode19 != null && l_strProductCode19.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�T�T�j�@@����������ʂP�X�̃`�F�b�N
        //�@@get����������ʂP�X()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂP�X()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper19 = this.getOrderQuantityUpper19(l_intLineNumber);
        //�@@�T�T�|�P�j�@@����������ʂP�X���擾�ł����ꍇ
        //�@@�@@�T�T�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper19 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper19))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�T�T�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper19 != null && l_strOrderQuantityUpper19.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�T�U�j�@@���i�R�[�h�Q�O�̃`�F�b�N
        //�@@get���i�R�[�h�Q�O()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�Q�O()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode20 = this.getProductCode20(l_intLineNumber);
        //�@@�T�U�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode20 != null && l_strProductCode20.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�T�V�j�@@����������ʂQ�O�̃`�F�b�N
        //�@@get����������ʂQ�O()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂQ�O()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper20 = this.getOrderQuantityUpper20(l_intLineNumber);
        //�@@�T�V�|�P�j�@@����������ʂQ�O���擾�ł����ꍇ
        //�@@�@@�T�V�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper20 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper20))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�T�V�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper20 != null && l_strOrderQuantityUpper20.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�T�W�j�@@���i�R�[�h�Q�P�̃`�F�b�N
        //�@@get���i�R�[�h�Q�P()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�Q�P()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode21 = this.getProductCode21(l_intLineNumber);
        //�@@�T�W�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode21 != null && l_strProductCode21.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�T�X�j�@@����������ʂQ�P�̃`�F�b�N
        //�@@get����������ʂQ�P()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂQ�P()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper21 = this.getOrderQuantityUpper21(l_intLineNumber);
        //�@@�T�X�|�P�j�@@����������ʂQ�P���擾�ł����ꍇ
        //�@@�@@�T�X�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper21 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper21))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�T�X�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper21 != null && l_strOrderQuantityUpper21.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�U�O�j�@@���i�R�[�h�Q�Q�̃`�F�b�N
        //�@@get���i�R�[�h�Q�Q()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�Q�Q()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode22 = this.getProductCode22(l_intLineNumber);
        //�@@�U�O�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode22 != null && l_strProductCode22.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�U�P�j�@@����������ʂQ�Q�̃`�F�b�N
        //�@@get����������ʂQ�Q()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂQ�Q()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper22 = this.getOrderQuantityUpper22(l_intLineNumber);
        //�@@�U�P�|�P�j�@@����������ʂQ�Q���擾�ł����ꍇ
        //�@@�@@�U�P�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper22 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper22))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�U�P�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper22 != null && l_strOrderQuantityUpper22.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�U�Q�j�@@���i�R�[�h�Q�R�̃`�F�b�N
        //�@@get���i�R�[�h�Q�R()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�Q�R()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode23 = this.getProductCode23(l_intLineNumber);
        //�@@�U�Q�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode23 != null && l_strProductCode23.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�U�R�j�@@����������ʂQ�R�̃`�F�b�N
        //�@@get����������ʂQ�R()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂQ�R()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper23 = this.getOrderQuantityUpper23(l_intLineNumber);
        //�@@�U�R�|�P�j�@@����������ʂQ�R���擾�ł����ꍇ
        //�@@�@@�U�R�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper23 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper23))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�U�R�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper23 != null && l_strOrderQuantityUpper23.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�U�S�j�@@���i�R�[�h�Q�S�̃`�F�b�N
        //�@@get���i�R�[�h�Q�S()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�Q�S()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode24 = this.getProductCode24(l_intLineNumber);
        //�@@�U�S�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode24 != null && l_strProductCode24.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�U�T�j�@@����������ʂQ�S�̃`�F�b�N
        //�@@get����������ʂQ�S()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂQ�S()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper24 = this.getOrderQuantityUpper24(l_intLineNumber);
        //�@@�U�T�|�P�j�@@����������ʂQ�S���擾�ł����ꍇ
        //�@@�@@�U�T�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper24 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper24))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�U�T�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper24 != null && l_strOrderQuantityUpper24.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }

        //�U�U�j�@@���i�R�[�h�Q�T�̃`�F�b�N
        //�@@get���i�R�[�h�Q�T()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get���i�R�[�h�Q�T()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strProductCode25 = this.getProductCode25(l_intLineNumber);
        //�@@�U�U�|�P�j�@@��������7byte���傫���ꍇ�A��O���X���[����B
        if (l_strProductCode25 != null && l_strProductCode25.getBytes().length > 7)
        {
            log.debug("���i�R�[�h�̒l��7byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�R�[�h�̒l��7byte���傫���ł��B");
        }

        //�U�V�j�@@����������ʂQ�T�̃`�F�b�N
        //�@@get����������ʂQ�T()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�@@[get����������ʂQ�T()�Ɏw�肷�����]
        //�@@�s�ԍ��F�@@����.�s�ԍ�
        String l_strOrderQuantityUpper25 = this.getOrderQuantityUpper25(l_intLineNumber);
        //�@@�U�V�|�P�j�@@����������ʂQ�T���擾�ł����ꍇ
        //�@@�@@�U�V�|�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (l_strOrderQuantityUpper25 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper25))
        {
            log.debug("����������ʂ̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l�����p�����ȊO�̒l�ł��B");
        }

        //�@@�@@�U�V�|�P�|�Q�j�@@15byte���傫���ꍇ�A��O���X���[����B
        if (l_strOrderQuantityUpper25 != null && l_strOrderQuantityUpper25.getBytes().length > 15)
        {
            log.debug("����������ʂ̒l��15byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ̒l��15byte���傫���ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�d���s)<BR>
     * �d���f�[�^�����݂��Ȃ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�ǉ��ς݂̖��׃`�F�b�N<BR>
     * <BR>
     * �@@�P�|�P�j�@@get���p�҃R�[�h�i�s�ԍ��j�ɂāA<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�w��s�ԍ��̗��p�҃R�[�h���擾����B<BR> 
     * <BR>
     * �@@�P�|�Q�j�@@�擾�������p�҃R�[�h�ƈ���.�s�ԍ����O�̖��׍s��<BR>
     * �@@�@@�@@�@@�@@�@@���p�҃R�[�h���r����B <BR>
     * �@@�@@�@@�@@�@@�@@�����l�������ꍇ�A��O�u�d���G���[�B�v���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :   BUSINESS_ERROR_02381<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43E193B900B3
     */
    public void validateDuplicateLine(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateLine(int)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�ǉ��ς݂̖��׃`�F�b�N
        //�@@�P�|�P�j�@@get���p�҃R�[�h�i�s�ԍ��j�ɂāA
        //           �w��s�ԍ��̗��p�҃R�[�h���擾����B
        String l_strUserCode = this.getUserCode(l_intLineNumber);
         
        //�@@�P�|�Q�j�@@�擾�������p�҃R�[�h�ƈ���.�s�ԍ����
        //           �O�̖��׍s�̗��p�҃R�[�h���r����B 
        //�@@�@@�@@�@@�@@�@@�����l�������ꍇ�A��O�u�d���G���[�B�v���X���[����B
        for (int i = 0; i < l_intLineNumber; i++)
        {
            if (l_strUserCode.equals(this.getUserCode(i)))
            {
                log.debug("���p�҃R�[�h�d���G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02381,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���p�҃R�[�h�d���G���[�B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get���p�҃R�[�h)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̗��p�҃R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂė��p�҃R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���p�҃R�[�h���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43E1A96E0075
     */
    public String getUserCode(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂė��p�҃R�[�h���擾���ԋp����B         
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.USER_CODE_LABEL));
    }
    
    /**
     * (get���p�Җ�)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̗��p�Җ����擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂė��p�Җ����擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���p�Җ����x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43E31F02032B
     */
    public String getUserName(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂė��p�Җ����擾���ԋp����B         
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.USER_NAME_LABEL));
    }
    
    /**
     * (get���O�C���h�c)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̃��O�C���h�c���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂă��O�C���h�c���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���O�C���h�c���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1CD50393
     */
    public String getLoginId(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂă��O�C���h�c���擾���ԋp����B         
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.LOGINID_LABEL));
    }
    
    /**
     * (get���O�C���p�X���[�h)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̃��O�C���p�X���[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂă��O�C���p�X���[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���O�C���p�X���[�h���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC14EA0181
     */
    public String getLoginPassword(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂă��O�C���p�X���[�h���擾���ԋp����B         
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.LOGIN_PASSWORD_LABEL));
    }
    
    /**
     * (get�����p�X���[�h)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔����p�X���[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ����p�X���[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.�����p�X���[�h���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC15400181
     */
    public String getOrderPassword(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂĔ����p�X���[�h���擾���ԋp����B         
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_PASSWORD_LABEL));
    }
    
    /**
     * (get���[���A�h���X�P)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̃��[���A�h���X�P���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂă��[���A�h���X�P���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���[���A�h���X�P���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43E31F8803D7
     */
    public String getMailAddress1(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂă��[���A�h���X�P���擾���ԋp����B     
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.MAILADDRESS1_LABEL));
    }
    
    /**
     * (get���[���A�h���X�Q)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̃��[���A�h���X�Q���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂă��[���A�h���X�Q���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���[���A�h���X�Q���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1B560170
     */
    public String getMailAddress2(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂă��[���A�h���X�Q���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.MAILADDRESS2_LABEL));
    }
    
    /**
     * (get���Ȏ���敪)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̎��Ȏ���敪���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĎ��Ȏ���敪���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���Ȏ���敪���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1B5A01BE
     */
    public String getSelfTrustDiv(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂĎ��Ȏ���敪���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.SELF_TRUSTDIV_LABEL));
    }

    /**
     * (get���p�ґ���)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̗��p�ґ������擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂė��p�ґ������擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���p�ґ������x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getUserAttribute(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂė��p�ґ������擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.USER_ATTRIBUTE_LABEL));
    }

    /**
     * (get���ϕ��@@)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̌��ϕ��@@���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂČ��ϕ��@@���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���ϕ��@@���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getSettlementMethod(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂČ��ϕ��@@���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.SETTLEMENT_METHOD_LABEL));
    }

    /**
     * (get���X�J�b�g�敪)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̃��X�J�b�g�敪���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂă��X�J�b�g�敪���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���X�J�b�g�敪���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1BA00354
     */
    public String getLossCutDiv(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂă��X�J�b�g�敪���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.LOSSCUTDIV_LABEL));
    }
    
    /**
     * (get�萔���敪)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̎萔���敪���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĎ萔���敪���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.�萔���敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1BA30170
     */
    public String getCommissionDiv(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂĎ萔���敪���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.COMMISSIONDIV_LABEL));
    }
    
    /**
     * (get����\�敪)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̎���\�敪���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĎ���\�敪���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����\�敪���x��)�̖߂�l�B  <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1BA6024B
     */
    public String getTradingDiv(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂĎ���\�敪���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.TRADINGDIV_LABEL));
    }

    /**
     * (get�d�q��t������)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̓d�q��t���������擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂēd�q��t���������擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.�d�q��t���������x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getElectronGrantPermissionDay(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂēd�q��t���������擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminFXAccOpenUploadCsv.ELECTRON_GRANT_PERMISSION_DAY_LABEL));
    }

    /**
     * (get����������m�F��)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̎���������m�F�����擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĎ���������m�F�����擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������m�F�����x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getTradeOperatingManualConfirmationDay(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĎ���������m�F�����擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminFXAccOpenUploadCsv.TRADE_OPERATING_MANUAL_CONFIRMATION_DAY_LABEL));
    }

    /**
     * (get������ԍ�)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̖�����ԍ����擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĖ�����ԍ����擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.������ԍ����x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getAgreementBookNumber(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĖ�����ԍ����擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminFXAccOpenUploadCsv.AGREEMENT_BOOK_NUMBER_LABEL));
    }

    /**
     * (get���l)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔��l���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ��l���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���l���x��)�̖߂�l�B�̖߂�l�B  <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43E31FDA005D
     */
    public String getRemark(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂĔ��l���擾���ԋp����B 
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.REMARK_LABEL));
    }
    
    /**
     * (get���i�R�[�h�P)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�P���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�P���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�P���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1BAA0354
     */
    public String getProductCode1(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂď��i�R�[�h�P���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE1_LABEL));
    }
    
    /**
     * (get����������ʂP)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂP���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂP���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂP���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1BAD00A5
     */
    public String getOrderQuantityUpper1(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂĔ���������ʂP���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER1_LABEL));
    }
    
    /**
     * (get���i�R�[�h�Q)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�Q���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�Q���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�Q���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1C12024B
     */
    public String getProductCode2(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂď��i�R�[�h�Q���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE2_LABEL));
    }
    
    /**
     * (get����������ʂQ)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂQ���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂQ���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂQ���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1C12026A
     */
    public String getOrderQuantityUpper2(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂĔ���������ʂQ���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER2_LABEL));
    }
    
    /**
     * (get���i�R�[�h�R)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�R���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�R���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�R���x��)�̖߂�<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1C140160
     */
    public String getProductCode3(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂď��i�R�[�h�R���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE3_LABEL));
    }
    
    /**
     * (get����������ʂR)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂR���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂR���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂR���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1C140170
     */
    public String getOrderQuantityUpper3(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂĔ���������ʂR���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER3_LABEL));
    }
    
    /**
     * (get���i�R�[�h�S)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�S���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�S���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�S���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1C15021C
     */
    public String getProductCode4(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂď��i�R�[�h�S���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE4_LABEL));
    }
    
    /**
     * (get����������ʂS)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂS���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂS���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂS���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1C15023B
     */
    public String getOrderQuantityUpper4(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂĔ���������ʂS���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER4_LABEL));
    }
    
    /**
     * (get���i�R�[�h�T)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�T���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�T���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�T���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1C1602C8
     */
    public String getProductCode5(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂď��i�R�[�h�T���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE5_LABEL));
    }
    
    /**
     * (get����������ʂT)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂT���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂT���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂT���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1C1602D7
     */
    public String getOrderQuantityUpper5(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂĔ���������ʂT���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER5_LABEL));
    }
    
    /**
     * (get���i�R�[�h�U)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�U���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�U���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�U���x��)�̖߂�l�B  <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1C1702C8
     */
    public String getProductCode6(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂď��i�R�[�h�U���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE6_LABEL));
    }
    
    /**
     * (get����������ʂU)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂU���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂU���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂU���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1C1702E7
     */
    public String getOrderQuantityUpper6(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂĔ���������ʂU���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER6_LABEL));
    }
    
    /**
     * (get���i�R�[�h�V)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�V���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�V���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�V���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1C190047
     */
    public String getProductCode7(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂď��i�R�[�h�V���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE7_LABEL));
    }
    
    /**
     * (get����������ʂV)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂV���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂV���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂV���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC1C190066
     */
    public String getOrderQuantityUpper7(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂĔ���������ʂV���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER7_LABEL));
    }

    /**
     * (get���i�R�[�h�W)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�W���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�W���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�W���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode8(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�W���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE8_LABEL));
    }

    /**
     * (get����������ʂW)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂW���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂW���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂW���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper8(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂW���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER8_LABEL));
    }

    /**
     * (get���i�R�[�h�X)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�X���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�X���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�X���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode9(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�X���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE9_LABEL));
    }

    /**
     * (get����������ʂX)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂX���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂX���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂX���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper9(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂX���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER9_LABEL));
    }
    
    /**
     * (get���i�R�[�h�P�O)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�P�O���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�P�O���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�P�O���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode10(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�P�O���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE10_LABEL));
    }

    /**
     * (get����������ʂP�O)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂP�O���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂP�O���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂP�O���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper10(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂP�O���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER10_LABEL));
    }

    /**
     * (get���i�R�[�h�P�P)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�P�P���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�P�P���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�P�P���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode11(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�P�P���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE11_LABEL));
    }

    /**
     * (get����������ʂP�P)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂP�P���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂP�P���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂP�P���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper11(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂP�P���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER11_LABEL));
    }

    /**
     * (get���i�R�[�h�P�Q)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�P�Q���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�P�Q���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�P�Q���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode12(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�P�Q���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE12_LABEL));
    }

    /**
     * (get����������ʂP�Q)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂP�Q���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂP�Q���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂP�Q���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper12(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂP�Q���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER12_LABEL));
    }

    /**
     * (get���i�R�[�h�P�R)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�P�R���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�P�R���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�P�R���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode13(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�P�R���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE13_LABEL));
    }

    /**
     * (get����������ʂP�R)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂP�R���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂP�R���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂP�R���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper13(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂP�R���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER13_LABEL));
    }

    /**
     * (get���i�R�[�h�P�S)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�P�S���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�P�S���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�P�S���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode14(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�P�S���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE14_LABEL));
    }

    /**
     * (get����������ʂP�S)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂP�S���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂP�S���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂP�S���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper14(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂP�S���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER14_LABEL));
    }

    /**
     * (get���i�R�[�h�P�T)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�P�T���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�P�T���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�P�T���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode15(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�P�T���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE15_LABEL));
    }

    /**
     * (get����������ʂP�T)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂP�T���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂP�T���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂP�T���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper15(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂP�T���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER15_LABEL));
    }

    /**
     * (get���i�R�[�h�P�U)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�P�U���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�P�U���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�P�U���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode16(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�P�U���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE16_LABEL));
    }

    /**
     * (get����������ʂP�U)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂP�U���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂP�U���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂP�U���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper16(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂP�U���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER16_LABEL));
    }

    /**
     * (get���i�R�[�h�P�V)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�P�V���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�P�V���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�P�V���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode17(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�P�V���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE17_LABEL));
    }

    /**
     * (get����������ʂP�V)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂP�V���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂP�V���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂP�V���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper17(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂP�V���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER17_LABEL));
    }

    /**
     * (get���i�R�[�h�P�W)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�P�W���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�P�W���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�P�W���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode18(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�P�W���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE18_LABEL));
    }

    /**
     * (get����������ʂP�W)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂP�W���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂP�W���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂP�W���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper18(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂP�W���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER18_LABEL));
    }

    /**
     * (get���i�R�[�h�P�X)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�P�X���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�P�X���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�P�X���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode19(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�P�X���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE19_LABEL));
    }

    /**
     * (get����������ʂP�X)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂP�X���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂP�X���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂP�X���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper19(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂP�X���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER19_LABEL));
    }

    /**
     * (get���i�R�[�h�Q�O)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�Q�O���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�Q�O���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�Q�O���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode20(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�Q�O���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE20_LABEL));
    }

    /**
     * (get����������ʂQ�O)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂQ�O���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂQ�O���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂQ�O���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper20(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂQ�O���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER20_LABEL));
    }

    /**
     * (get���i�R�[�h�Q�P)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�Q�P���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�Q�P���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�Q�P���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode21(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�Q�P���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE21_LABEL));
    }

    /**
     * (get����������ʂQ�P)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂQ�P���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂQ�P���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂQ�P���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper21(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂQ�P���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER21_LABEL));
    }

    /**
     * (get���i�R�[�h�Q�Q)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�Q�Q���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�Q�Q���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�Q�Q���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode22(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�Q�Q���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE22_LABEL));
    }

    /**
     * (get����������ʂQ�Q)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂQ�Q���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂQ�Q���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂQ�Q���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper22(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂQ�Q���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER22_LABEL));
    }

    /**
     * (get���i�R�[�h�Q�R)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�Q�R���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�Q�R���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�Q�R���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode23(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�Q�R���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE23_LABEL));
    }

    /**
     * (get����������ʂQ�R)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂQ�R���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂQ�R���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂQ�R���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper23(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂQ�R���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER23_LABEL));
    }

    /**
     * (get���i�R�[�h�Q�S)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�Q�S���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�Q�S���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�Q�S���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode24(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�Q�S���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE24_LABEL));
    }

    /**
     * (get����������ʂQ�S)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂQ�S���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂQ�S���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂQ�S���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper24(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂQ�S���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER24_LABEL));
    }

    /**
     * (get���i�R�[�h�Q�T)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̏��i�R�[�h�Q�T���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂď��i�R�[�h�Q�T���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.���i�R�[�h�Q�T���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getProductCode25(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂď��i�R�[�h�Q�T���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE25_LABEL));
    }

    /**
     * (get����������ʂQ�T)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̔���������ʂQ�T���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ���������ʂQ�T���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�Ǘ���FX�����J�݃A�b�v���[�hCSV<BR>
     * �@@�@@�@@�@@�@@.����������ʂQ�T���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper25(int l_intLineNumber)
    {
        //this.get���ڒl()�ɂĔ���������ʂQ�T���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER25_LABEL));
    }

    /**
     * (getFX���O�C��ID������)<BR>
     * ����.�s�ԍ��ɑΉ����閾�׍s��FX���O�C��ID���������擾����B <BR>
     * <BR>
     * �P�j this.get���p�҃R�[�h(����.�s�ԍ�)�ɂė��p�҃R�[�h���擾����B <BR>
     * <BR>
     * �Q�j�@@�擾�������p�҃R�[�h�̑O2����ԋp����B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC6CA90041
     */
    public String getFXHeadOfLoginId(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " getFXHeadOfLoginId(int)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j this.get���p�҃R�[�h(����.�s�ԍ�)�ɂė��p�҃R�[�h���擾����B 
        String l_strUserCode = this.getUserCode(l_intLineNumber);
       
        //�Q�j�@@�擾�������p�҃R�[�h�̑O2����ԋp����B
        log.exiting(STR_METHOD_NAME);    
        return l_strUserCode.substring(0, 2);
    }
    
    /**
     * (get�ڋq�R�[�h)<BR>
     * �w��s�ԍ��ɑΉ����閾�׍s�̌ڋq�R�[�h�i6���j���擾����B <BR>
     * <BR>
     * �P�j�@@this.get���p�҃R�[�h(����.�s�ԍ�)�ɂė��p�҃R�[�h���擾����B<BR>
     * <BR>
     * �Q�j�@@�擾�������p�҃R�[�h�̌�6����ԋp����B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43EC341401BE
     */
    public String getAccountCode(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " getAccountCode(int)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j this.get���p�҃R�[�h(����.�s�ԍ�)�ɂė��p�҃R�[�h���擾����B 
        String l_strUserCode = this.getUserCode(l_intLineNumber);
       
        //�Q�j �擾�������p�҃R�[�h�̌�6����ԋp����B
        log.exiting(STR_METHOD_NAME);    
        return l_strUserCode.substring(l_strUserCode.length() - 6);
    }
    
    /**
     * (get���X�R�[�h)<BR>
     * ����.�s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����B <BR>
     * <BR>
     * �P�j this.getFX���O�C��ID������(����.�s�ԍ�)�ɂ�<BR>
     * �@@�@@�@@FX���O�C��ID���������擾����B<BR>
     * <BR>
     * �Q�j �ȉ��̏����̂��Ƃɉ�Е�FX�V�X�e�������e�[�u�����擾����B<BR>
     * <BR>
     * �@@�E�،���ЃR�[�h == this.get�،���ЃR�[�h()<BR>
     * �@@�EFX���O�C��ID������.substring(1) == �擾����FX���O�C��ID������<BR>
     * <BR>
     * �R�j ��Е�FX�V�X�e������Param.���X�R�[�h��ԋp����B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43E2F60F0379
     */
    public String getBranchCode(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchCode(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j this.getFX���O�C��ID������(����.�s�ԍ�)�ɂ�
        //     FX���O�C��ID���������擾����B
        String l_strFXHeadOfLoginId = this.getFXHeadOfLoginId(l_intLineNumber);

        //�Q�j �ȉ��̏����̂��Ƃɉ�Е�FX�V�X�e�������e�[�u�����擾����B
        //�@@�E�،���ЃR�[�h == this.get�،���ЃR�[�h()
        //�@@�EFX���O�C��ID������.substring(1) == �擾����FX���O�C��ID������
        String l_strWhere = " institution_code = ? and substr(fx_head_of_login_id, 2) = ? ";
        Object[] l_objValue = {this.getInstitutionCode(), l_strFXHeadOfLoginId};
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    CompFxConditionRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataQueryException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." +STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�A�N�Z�X�G���[", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if(l_lisRows == null || l_lisRows.size() == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }

        //�R�j ��Е�FX�V�X�e������Param.���X�R�[�h��ԋp����B
        String l_strBranchCode = ((CompFxConditionParams) l_lisRows.get(0)).getBranchCode();
        log.exiting(STR_METHOD_NAME);    
        return l_strBranchCode;
    }
    
    /**
     * (getGFT�����J�ݏ�)<BR>
     * �s�ԍ��ɑΉ����閾�׍s��GFT�����J�ݏ�Params���擾����B <BR>
     * <BR>
     * �P�jDB���� <BR>
     * �@@�ȉ��̏����ŁAGFT�����J�ݏ󋵃e�[�u������������B <BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h == this.get�،���ЃR�[�h()<BR>
     * �@@���X�R�[�h == this.get���X�R�[�h�i����.�s�ԍ��j<BR>
     * �@@�u�ڋq�R�[�h�v�̑O6�� == this.get�ڋq�R�[�h(����.�s�ԍ�)<BR>
     * �@@���ʃR�[�h == this.get���l(����.�s�ԍ�)<BR>
     * �@@���O�i���j == this.get���p�Җ�(����.�s�ԍ�)<BR>
     * �@@���[���A�h���X == this.get���[���A�h���X�P(����.�s�ԍ�)<BR>
     * �@@�u���O�C��ID�v�̌�8�� == this.get���O�C���h�c�i����.�s�ԍ��j<BR>
     * �@@�����J�ݏ󋵋敪 == "3�F�_�E�����[�h��"<BR>
     * �@@������敪 == "2�F��̍�"<BR>
     * <BR>
     * �@@�P�|�P�j�@@���R�[�h���擾�ł��Ȃ������ꍇ�A�܂���<BR>
     * �@@�@@�@@�@@�@@�����擾�ł����ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :   BUSINESS_ERROR_02392<BR>
     * <BR>
     * �Q�j�@@�擾����GFT�����J�ݏ�Params��ԋp����B<BR>
     * �@@�@@�@@�@@�@@
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return GftAccountOpenStatusParams
     * @@throws WEB3BaseException
     * @@roseuid 43E30BAB031C
     */
    public GftAccountOpenStatusParams getGFTAccountOpenStatus(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getGFTAccountOpenStatus(int)";
        log.entering(STR_METHOD_NAME);        
        //�P�jDB���� 
        //�@@�ȉ��̏����ŁAGFT�����J�ݏ󋵃e�[�u������������B 
        //�@@[����]
        //�@@�،���ЃR�[�h == this.get�،���ЃR�[�h()
        //�@@���X�R�[�h == this.get���X�R�[�h�i����.�s�ԍ��j
        //�@@�u�ڋq�R�[�h�v�̑O6�� == this.get�ڋq�R�[�h(����.�s�ԍ�)
        //�@@���ʃR�[�h == this.get���l(����.�s�ԍ�)
        //�@@���O�i���j == this.get���p�Җ�(����.�s�ԍ�)
        //�@@���[���A�h���X == this.get���[���A�h���X�P(����.�s�ԍ�)
        //�@@�u���O�C��ID�v�̌�8�� == this.get���O�C���h�c�i����.�s�ԍ��j
        //�@@�����J�ݏ󋵋敪 == "3�F�_�E�����[�h��"
        //�@@������敪 == "2�F��̍�"
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and substr(account_code,0,6) = ? ");
        l_sbWhere.append(" and order_request_number = ? ");
        l_sbWhere.append(" and substr(login_id,length(login_id) - 7) = ? ");
        l_sbWhere.append(" and account_open_status_div = ? ");
        l_sbWhere.append(" and agreement_div = ? ");
                            
        ArrayList l_arrValue = new ArrayList();
        l_arrValue.add(this.getInstitutionCode()); 
        l_arrValue.add(this.getBranchCode(l_intLineNumber));
        l_arrValue.add(this.getAccountCode(l_intLineNumber));
        l_arrValue.add(this.getRemark(l_intLineNumber));
        l_arrValue.add(this.getLoginId(l_intLineNumber));
        l_arrValue.add(WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE);
        l_arrValue.add(WEB3AioAgreementDivDef.RECIEVED);
        
        if (this.getUserName(l_intLineNumber) == null)
        {
            l_sbWhere.append(" and last_name is null ");
        }
        else
        {
            l_sbWhere.append(" and last_name = ? ");
            l_arrValue.add(this.getUserName(l_intLineNumber));
        }
        
        if (this.getMailAddress1(l_intLineNumber) == null)
        {
            l_sbWhere.append("and mail_address is null ");
        }
        else
        {
            l_sbWhere.append("and mail_address = ? ");
            l_arrValue.add(this.getMailAddress1(l_intLineNumber));
        }
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    GftAccountOpenStatusRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_arrValue.toArray());
        }
        catch (DataQueryException l_ex)
        {
            log.error("�����ɊY������f�[�^�����݂��Ȃ��A�܂��͕������݂��܂��B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02392,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��A�܂��͕������݂��܂��B");
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�A�N�Z�X�G���[", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�@@�P�|�P�j�@@���R�[�h���擾�ł��Ȃ������ꍇ�A�܂���
        //�@@�@@�@@�@@�@@�����擾�ł����ꍇ�A��O���X���[����B
        if(l_lisRows == null || l_lisRows.size() != 1)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��A�܂��͕������݂��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02392,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��A�܂��͕������݂��܂��B");
        }
        
        //�Q�j�@@�擾����GFT�����J�ݏ�Params��ԋp����B        
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams  = (GftAccountOpenStatusParams) l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);    
        return l_gftAccountOpenStatusParams;
    }
}
@
